package com.cognizant.rif.rules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.cognizant.javagrammar.JavaLexer;
import com.cognizant.javagrammar.JavaParser;
import com.cognizant.parser.ANTLR.listeners.ExtractKeywordListener;
import com.cognizant.parser.ANTLR.listeners.RuleExtractionListener;
import com.cognizant.rif.common.logging.LogUtil;
import com.cognizant.rif.dto.CodePart;
import com.cognizant.rif.exceptions.PatternNotFoundException;
import com.cognizant.rif.vo.RuleReportVO;

public class RuleConditionsExtractor {

	/**
	 * Class level variable that contains the name of the class
	 */
	private static final String CLASS_NAME = "ConditionsExtractor";

	/**
	 * Class level variable that contains the logger name
	 */
	private static final String LOG_NAME = "EXTRACT_LOG";

	private static String CONDITIONS_FILE_NAME = "Conditions.txt";
	private static String PATTERN_MATCH_FILE_NAME = "Pattern.txt";
	static int parsedFilecount = 0;
	private static int ruleIdCount=0;
    private static int errorIdCount=0;

	public static void outputFileName(String condFname, String pattFname) {
		CONDITIONS_FILE_NAME = condFname;
		PATTERN_MATCH_FILE_NAME = pattFname;
	}

	public static Properties loadPropertyData(String path) throws Exception {
		File file = new File(path);
		Properties properties = new Properties();
		InputStream input = new FileInputStream(file);
		properties.load(input);

		return properties;
	}

	private static void getFileNamesList(File fileDir, List<File> javaFiles) {
		File[] files = fileDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory())
				getFileNamesList(files[i], javaFiles);
			else {
				if (files[i].getAbsolutePath().toLowerCase().endsWith(".java")) {
					if (!files[i].getName().endsWith("Exception.java")
							&& !files[i].getName().endsWith("VO.java")
							&& !files[i].getName().startsWith("Test")
							&& !files[i].getName().endsWith("Constants.java")) {
						javaFiles.add(files[i]);
					}
				}
			}
		}
	}

	public static void extractAllDataKeyWordSearch(String sourcePath,
			String destinationPath,
			List<String> ruleIdList,RuleReportVO ruleRepVO) throws Exception {

		List<File> filesList = new ArrayList<File>();
		//Properties propertyData = loadPropertyData(path);
		//Collection<Object> keyData = propertyData.values();
		getFileNamesList(new File(sourcePath), filesList);
		BufferedWriter conditionWriter = null;
		BufferedWriter patternMatchWriter = null;
		Map<String, Map<String, List<CodePart>>> nearestMatchingsIfsForMethod = null;
		Map<String, Map<String, Map<String, List<CodePart>>>> nearestMatchingsIfsForClass = new HashMap<String, Map<String, Map<String, List<CodePart>>>>();

		try {
			int fileCount = 1;
			for (File fileName : filesList) {

				System.out.println("Total Files completed are= " + fileCount
						+ " Out of (" + filesList.size() + ")");
				fileCount = fileCount + 1;

				if (LogUtil.isDebugEnabled(LOG_NAME)) {
					LogUtil.debug(
							CLASS_NAME,
							"extractAllDataMessagesBased",
							"Class picked for Key extraction="
									+ fileName.getAbsolutePath(), LOG_NAME);
				}

				ANTLRInputStream input = new ANTLRInputStream(
						new FileInputStream(fileName));
				JavaLexer lexer = new JavaLexer(input);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				JavaParser parser = new JavaParser(tokens);
				long startTime = System.currentTimeMillis();
				ParseTree tree = parser.compilationUnit();
				long endTime = System.currentTimeMillis();
				System.out.println("Parse Unit took ms:"
						+ (endTime - startTime));
				ParseTreeWalker walker = new ParseTreeWalker();
				nearestMatchingsIfsForMethod = new HashMap<String, Map<String, List<CodePart>>>();

				for (String ruleID:ruleIdList) {
					//String ruleIDkey = String.valueOf(ruleIdSet.getKey());
					String outputPatternFileName = destinationPath + "\\"
							+ ruleID + "_" + PATTERN_MATCH_FILE_NAME;

					if (LogUtil.isDebugEnabled(LOG_NAME)) {
						LogUtil.debug(CLASS_NAME, "extractKeywords",
								"method call...", LOG_NAME);
					}

					String filePath = CONDITIONS_FILE_NAME;
					// outputPatternFileName.replace(PATTERN_MATCH_FILE_NAME,
					// "NearestIF.txt");;
					String patternPath = outputPatternFileName;
					File file = new File(filePath);
					File patternFile = new File(patternPath);
					conditionWriter = new BufferedWriter(new FileWriter(file));
					patternMatchWriter = new BufferedWriter(new FileWriter(
							patternFile, true));
					ExtractKeywordListener extractor = new ExtractKeywordListener(
							tokens, getRuleIDArrays(ruleID),
							conditionWriter, patternMatchWriter,
							new HashMap<String, String>(),
							nearestMatchingsIfsForMethod, ruleID);

					try {
						walker.walk(extractor, tree);

					} catch (PatternNotFoundException ex) {
						// This is not a exception
						// System.out.println("Exception==>"+ex);
					}
					if (patternMatchWriter != null)
						patternMatchWriter.close();
					if (patternMatchWriter != null)
						conditionWriter.close();
					
					//Setting vo object
					ruleRepVO.setPatternPath(patternPath);
				}
				endTime = System.currentTimeMillis();
				// System.out.println("Filename==>"+fileName.getName()+"==>Listener took ms:"+(endTime-startTime));

				// Rule extraction code starts here
				System.out.println("FileName full path -->"
						+ fileName.getAbsolutePath());

				// To Test

				if (!(nearestMatchingsIfsForMethod.isEmpty())) {
					nearestMatchingsIfsForClass.put(fileName.getAbsolutePath(),
							nearestMatchingsIfsForMethod);
				}

				if (!(nearestMatchingsIfsForMethod.isEmpty())) {
					System.out.println("All matching ifs for this class-->"
							+ nearestMatchingsIfsForMethod);

					// TODO To take this as a parameter
					String FILE_SOURCE_BASE_URL = destinationPath+"//";

					RuleExtractionListener extractor1 = new RuleExtractionListener(
							FILE_SOURCE_BASE_URL, sourcePath, tokens,
							nearestMatchingsIfsForMethod,ruleRepVO);
					walker.walk(extractor1, tree);

				}
				// End here
			}
			if (LogUtil.isDebugEnabled(LOG_NAME)) {

				LogUtil.debug(CLASS_NAME, "Statistics:",
						"All matching ifs for all classes size-->"
								+ nearestMatchingsIfsForClass.size(), LOG_NAME);
			}

			System.out.println("All matching ifs for all classes-->"
					+ nearestMatchingsIfsForClass);
			// /conditionWriter.append((CharSequence)
			// nearestMatchingsIfsForClass);
		} finally {
			if (conditionWriter != null) {
				conditionWriter.close();
			}
			if (patternMatchWriter != null) {
				patternMatchWriter.close();
			}
		}

	}

	public static void extractAllDataMessagesBased(String sourcePath,
            String destinationPath, Map<String, String> messageMap,Properties errorMessage,Properties ruletoErrPath,RuleReportVO ruleReportVo)
            throws Exception 
            {
	     	
			List<File> filesList=new ArrayList<File>();	    
			Properties propertyData=errorMessage;
			HashMap inversePropertyData=loadPropertyData(propertyData);
			
			Properties ruletoErrProp=ruletoErrPath;
			
			
			Collection<Object> keyData= propertyData.values();
			getFileNamesList(new File(sourcePath),filesList);
		    
		     
		    BufferedWriter conditionWriter = null;
		    BufferedWriter patternMatchWriter = null;
		    Map<String, Map<String, List<CodePart>>> nearestMatchingsIfsForMethod = null;
		    Map<String, Map<String, Map<String, List<CodePart>>>> nearestMatchingsIfsForClass = 
		    		new HashMap<String, Map<String, Map<String, List<CodePart>>>>();
		    
		     try{
				    	 	int fileCount=1;
		                    for (File fileName : filesList) {
		                	  
		                	  System.out.println("Total Files completed are= "+fileCount+" Out of ("+filesList.size()+")");
		                	  fileCount=fileCount+1;
		                	  
		                         if(LogUtil.isDebugEnabled(LOG_NAME)){
		                                LogUtil.debug(CLASS_NAME, "extractAllDataMessagesBased", "Class picked for Key extraction="     + fileName.getAbsolutePath(), LOG_NAME);
		                         }
		                         	                         
		                         ANTLRInputStream input = new ANTLRInputStream(
		                                       new FileInputStream(fileName));
		                         JavaLexer lexer = new JavaLexer(input);
		                         CommonTokenStream tokens = new CommonTokenStream(lexer);
		                         JavaParser parser = new JavaParser(tokens);
		                         long startTime=System.currentTimeMillis();
		                         ParseTree tree = parser.compilationUnit();
		                         long endTime=System.currentTimeMillis();
		                         System.out.println("Parse Unit took ms:"+(endTime-startTime));
		                         ParseTreeWalker walker = new ParseTreeWalker();
		                         int count=0;
		                         nearestMatchingsIfsForMethod = new HashMap<String,Map<String, List<CodePart>>>();
		                         
		                         
		                         for(Entry<String, String> set: messageMap.entrySet()){
		         					count++;
		         					//System.out.println("Total Inputs Messages completed are= "+count+" Out of ("+messageMap.size()+")");
		         					List<String> list=new ArrayList<String>();
		         					
		         					String key=set.getKey();
		         					String value=set.getValue();
		         					String outputPatternFileName=identifyFileNameMessageBased(value,inversePropertyData,destinationPath);
		         					String ruleId=identifyRuleIdMessagebased(value,inversePropertyData,ruletoErrProp);
		         					list.add(key);
		         					list.add(value);
		         					list.add(identifyErrorIdMessagebased(value, inversePropertyData, ruletoErrProp));
		         					
		         					if(LogUtil.isDebugEnabled(LOG_NAME)){
		         						LogUtil.debug(CLASS_NAME, "extractKeywords","method call...", LOG_NAME);  
		         					}
		         				     
		         				    
		         			         String filePath = CONDITIONS_FILE_NAME; 
		         			        		 //outputPatternFileName.replace(PATTERN_MATCH_FILE_NAME, "NearestIF.txt");;
		         			         String patternPath =outputPatternFileName;
		         			         //Setting vo object
		         			         ruleReportVo.setPatternPath(patternPath);
		         			         
		         			         File file = new File(filePath);
		         			         File patternFile = new File(patternPath);
		         			         conditionWriter = new BufferedWriter(new FileWriter(file));
		         			         patternMatchWriter = new BufferedWriter(new FileWriter(patternFile,true));
		         			        
		         			    
		                         ExtractKeywordListener extractor = new ExtractKeywordListener(
		                                       tokens,list , conditionWriter,patternMatchWriter,messageMap, nearestMatchingsIfsForMethod,ruleId);
		                         
		                         try
		                         {
		                        	 walker.walk(extractor, tree);
		       	 
		                         }
		                         catch(PatternNotFoundException ex)
		                         {
		                        	 //This is not a exception
		                        	 //System.out.println("Exception==>"+ex);
		                         }
		                         if(patternMatchWriter !=null)
	         			        	 patternMatchWriter.close();
		                         if(patternMatchWriter !=null)
		                        	 conditionWriter.close();
		                  }
                          endTime=System.currentTimeMillis();
	                      //System.out.println("Filename==>"+fileName.getName()+"==>Listener took ms:"+(endTime-startTime));    
		                         
	                	//Rule extraction code starts here
	                	 System.out.println("FileName full path -->"+fileName.getAbsolutePath());
	                	 
	                	 //To Test
	                	 
	                	 
	                	 if(!(nearestMatchingsIfsForMethod.isEmpty())){
	                		 
	                		 for(Entry<String, Map<String, List<CodePart>>> set:nearestMatchingsIfsForMethod.entrySet())
	                		 {
	                			 //System.out.println("Key==>"+set.getKey()+"Value==>"+set.getValue());
	                			 Map<String,List<CodePart>> codemap=set.getValue();
	                			
	                			 
	                		 }
	                	     nearestMatchingsIfsForClass.put(fileName.getAbsolutePath(), nearestMatchingsIfsForMethod);
	                	 }
	                	 	
	                	 if( !(nearestMatchingsIfsForMethod.isEmpty())) {
	                		 System.out.println("All matching ifs for this class-->"+nearestMatchingsIfsForMethod);
	                		  
	                		 
	                		 //TODO To take this as a parameter
	                		 String FILE_SOURCE_BASE_URL = destinationPath+"//";;
	                		 
	                		 RuleExtractionListener extractor1 = new RuleExtractionListener
	                				 (FILE_SOURCE_BASE_URL, sourcePath,
	                					tokens, nearestMatchingsIfsForMethod,ruleReportVo);
	                		walker.walk(extractor1, tree);
	                		
	                		 
	                	            	 
	                	 
		                 }
	                	//End here 
				     }	
		                    
		             System.out.println("All matching ifs for all classes-->"+nearestMatchingsIfsForClass);
		             ///conditionWriter.append((CharSequence) nearestMatchingsIfsForClass);
		     }
		     finally
		     {
		            if(conditionWriter != null) {
		                  conditionWriter.close();
		            }
		            if(patternMatchWriter !=null)
		            {
		                  patternMatchWriter.close();
		            }
		     }

		     
            }
	
	
	public static List<String> getRuleIDArrays(String ruleid) {
		String regex = "\\.";
		Pattern pat = Pattern.compile(regex);
		Matcher m1 = pat.matcher(ruleid);
		List<String> ruleidList = new ArrayList<String>();
		ruleidList.add(ruleid);
		while (m1.find()) {
			// System.out.println("1"+m1.group());
			ruleidList.add(m1.replaceAll("_"));
			ruleidList.add(m1.replaceAll(" "));
			ruleidList.add(m1.replaceAll(""));

		}
		//Rule pattern
		ruleidList.add("PRV*.*.*");
		
		return ruleidList;
	}
	
	private static HashMap loadPropertyData(Properties propertyData) {
		HashMap<String,String> inverseMap=new HashMap<String,String>();
		
		for(Entry<Object, Object> set: propertyData.entrySet()){
			String key=String.valueOf(set.getKey());
			String value=String.valueOf(set.getValue());
			if(value.endsWith(".")){
				value=value.substring(0,value.length()-1);
			}
			inverseMap.put(value, key);
		}
		return inverseMap;
	}
	
	private static String identifyFileNameMessageBased(String value,
			HashMap propertyData,String destinationPath) {
			String outputFileName=null;
			if(value !=null && value.endsWith(".")){
				value=value.substring(0,value.length()-1);
			}
			outputFileName=(String)(propertyData.get(value));				
			if(outputFileName==null){
				System.err.println("Not able to Identify the File Name for key="+value);
			}
			
	        String patternPath =destinationPath +"\\"+outputFileName+"_"+PATTERN_MATCH_FILE_NAME;

		return patternPath;
	}
	
	private static String identifyRuleIdMessagebased(String value,
			HashMap inversePropertyData, Properties ruletoErrProp) {
				String ruleid=null;
				if(value !=null && value.endsWith(".")){
					value=value.substring(0,value.length()-1);
				}
				String errorId=(String) inversePropertyData.get(value);
			if (ruletoErrProp != null && ruletoErrProp.containsKey(errorId))
					ruleid=(String)ruletoErrProp.get(errorId);
				//System.out.println("Rule Id==>"+ruleid);
				if(ruleid!=null && !ruleid.isEmpty())
				{
					ruleIdCount++;
					return ruleid;
				}
				errorIdCount++;
				return errorId;
			}

			private static String identifyErrorIdMessagebased(String value,
					HashMap inversePropertyData, Properties ruletoErrProp) {
						if(value !=null && value.endsWith(".")){
							value=value.substring(0,value.length()-1);
						}
						String errorId=(String) inversePropertyData.get(value);
					return errorId;
			}




class ParsedDataVO {
	ParseTree tree;
	CommonTokenStream token;

	public ParseTree getTree() {
		return tree;
	}

	public void setTree(ParseTree tree) {
		this.tree = tree;
	}

	public void setTokens(CommonTokenStream token) {
		this.token = token;
	}

	public CommonTokenStream getTokens() {
		return token;
	}

}
}