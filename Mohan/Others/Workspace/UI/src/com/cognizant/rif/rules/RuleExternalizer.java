package com.cognizant.rif.rules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.cognizant.javagrammar.JavaLexer;
import com.cognizant.javagrammar.JavaParser;
import com.cognizant.parser.ANTLR.listeners.ExtractMethodNamesListener;
import com.cognizant.rif.common.logging.LogUtil;
import com.cognizant.rif.dto.MetaDataInfo;
import com.cognizant.rif.dto.RuleDescriptionInfo;
import com.cognizant.rif.dto.RuleDescriptor;
import com.cognizant.rif.formatter.JacobeFormatter;
import com.cognizant.rif.junit.JunitGenerator;
import com.cognizant.rif.utilities.MetaData;
import com.cognizant.rif.utilities.RuleTemplateUtility;
import com.cognizant.rif.utilities.ZipUtil;
import com.cognizant.rif.vo.RuleReportVO;


/**
 * @author 396662
 * 
 */
public class RuleExternalizer {
	
	private static String FILE_SOURCE_BASE_URL;
	private static String ROOT_DIRECTORY;
	public static String RULE_POJO_TEMPLATE="/rule_pojo.vm";
	public static String RULE_INVOKE_TEMPLATE="/invoke_rule.vm";
	private final String RULE_EXTERNALIZE_XML="velocity\\RulesExternalize.xml";
	
    private static final String CLASS_NAME = "RuleExternalizer";
    private static final String LOG_NAME = "EXTRACT_LOG";
	
	
	public RuleExternalizer(String fileSourceBaseURL, String rootDirectory) {
		RuleExternalizer.FILE_SOURCE_BASE_URL=fileSourceBaseURL;
		RuleExternalizer.ROOT_DIRECTORY=rootDirectory;
		
	}

	/**
	 * To externalize Simple Rule
	 * 
	 * @param extractText
	 * @param importsList
	 * @param ruleContextParams
	 * @param methodListMap
	 * @param object 
	 * @return
	 */
	public String externalizeRule(String extractText, List<String> importsList,
			Map<String, String> ruleContextParams,String ruleId, String returnBoolean,String extractClassName,RuleReportVO ruleRepVo) {
		if(LogUtil.isDebugEnabled(LOG_NAME)){
            LogUtil.debug(CLASS_NAME, "externalizeRule", "POJO Rule Extraction Initiate", LOG_NAME);
		}
		
		/*
		 * first, get and initialize an engine
		 */

		VelocityEngine ve = new VelocityEngine();
		ve.setProperty("resource.loader", "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		ve.init();

		/*
		 * organize our data
		 */

		RuleDescriptor ruleDescriptor = new RuleDescriptor();

		String ruleInvokeCode = null;

		try {
			if(LogUtil.isDebugEnabled(LOG_NAME)){
	            LogUtil.debug(CLASS_NAME, "externalizeRule", "Getting rule details from XML file", LOG_NAME);
			}
			// Getting rule details from XML file
			RuleDescriptionInfo ruleDescInfo=new RuleDescriptionInfo();
			
			ruleDescInfo=getRuleDescriptionInfo(ruleId);
			
			List<String> methodList = new ArrayList<String>();	
			
			
			ruleDescriptor.setImportsList(importsList);
			ruleDescriptor.setRuleContextParams(ruleContextParams);
			ruleDescriptor.setRuleText(extractText);
			ruleDescriptor.setRuleId(ruleId);
			ruleDescriptor.setRuleName(ruleId);
			ruleDescriptor.setRulePackageName(ruleDescInfo.getPackageName());
			ruleDescriptor.setRuleClassName(ruleDescInfo.getClassName());
			ruleDescriptor.setRuleReturnField(returnBoolean);			
			ruleDescriptor.setRuleMethods(methodList);
			ruleDescriptor.setRuleExtractClassName(extractClassName);
			//System.out.println("Method list==>" + methodList.size());

			RuleTemplateUtility ruleTempUtility = new RuleTemplateUtility();

			/*
			 * add that list to a VelocityContext
			 */

			VelocityContext context = new VelocityContext();
			List<RuleDescriptor> ruleDescriptorList = new ArrayList<RuleDescriptor>();
			ruleDescriptorList.add(ruleDescriptor);
			context.put("ruleDescriptorList", ruleDescriptorList);
			context.put("ruleDescriptor", ruleDescriptor);
			context.put("ruleTempUtility", ruleTempUtility);
			context.put("isBatchRule", false);

			String filepath = getClassPath(ruleDescriptor.getRulePackageName(),
							ruleDescriptor.getRuleClassName());
			ruleRepVo.setPojoPath(filepath);
			//System.out.println("File path==>"+filepath);
			File file = new File(filepath);
			
			BufferedWriter ruleExtractionWriter = new BufferedWriter(
					new FileWriter(file));

			
			System.out.println("=========================================================================");
			System.out.println("Output file path==>"+file.getAbsolutePath());
			System.out.println("=========================================================================");
			
			
			if(LogUtil.isDebugEnabled(LOG_NAME)){
	            LogUtil.debug(CLASS_NAME, "externalizeRule", "Generating POJO...", LOG_NAME);
	            LogUtil.debug(CLASS_NAME, "externalizeRule", "POJO file path==>"+file.getAbsolutePath(), LOG_NAME);
			}
			
			System.out.println("Rule Path"+RULE_POJO_TEMPLATE);
			System.out.println("Rule Path"+RuleExternalizer.class.getClassLoader().getResource(RULE_POJO_TEMPLATE));
			
			
			/*
			 * get the Template
			 */
			Template t = ve.getTemplate(RULE_POJO_TEMPLATE);
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			
			//Writing rule to POJO class
			ruleExtractionWriter.write(writer.toString());
			ruleExtractionWriter.close();
			//System.out.println(writer.toString());
			if(LogUtil.isDebugEnabled(LOG_NAME)){
	            LogUtil.debug(CLASS_NAME, "externalizeRule", "POJO Created", LOG_NAME);
			}

			// Adding metadata info in XML
			//String mitaMapping = "Provider";// TODO to update the parameter
			MetaDataInfo metaInfo = ruleDescInfo.getMetaDataInfo(); 
					/*getMetaDataInfo(
					ruleDescriptor.getRuleClassName(), mitaMapping,
					ruleDescriptor.getRuleId(), ruleDescriptor.getRuleName());
			MetaData.updateMetaDataInfo(metaInfo);*/
			if(LogUtil.isDebugEnabled(LOG_NAME)){
	            LogUtil.debug(CLASS_NAME, "externalizeRule", "Updated Rules Metadata File", LOG_NAME);
			}
			
			{
				// get the invoke code
				t = ve.getTemplate(RULE_INVOKE_TEMPLATE);
				writer = new StringWriter();
				t.merge(context, writer);
	
				if(LogUtil.isDebugEnabled(LOG_NAME)){
		            LogUtil.debug(CLASS_NAME, "externalizeRule", "Rule invoke code generated", LOG_NAME);
				}
				
				//System.out.println(writer.toString());
				ruleInvokeCode = writer.toString();
			}
			
			//Code Beautifier
			formatCode(file.getAbsolutePath());
			
			{
				//Generate Junit code
				//To get invoke rule code for testCase
				String testCaseInvokeCode=null;
				t = ve.getTemplate("/invoke_rule_testCase.vm");
				writer = new StringWriter();
				t.merge(context, writer);
				testCaseInvokeCode= writer.toString();
				
				JunitGenerator jgen=new JunitGenerator();
				String junitPath=jgen.generate(file.getAbsolutePath(),ruleContextParams,ruleId,testCaseInvokeCode);
				ruleRepVo.setJunitPath(junitPath);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
	        LogUtil.error(CLASS_NAME, "externalizeRule", "Exception Description==>"+e, LOG_NAME);
			e.printStackTrace();
		} catch (Exception ex) {
			LogUtil.error(CLASS_NAME, "externalizeRule", "Exception Description==>"+ex, LOG_NAME);
			System.out.println("Exception==>" + ex.toString());
			ex.printStackTrace();
		}
		return ruleInvokeCode;

	}

	/**
	 * To externalize Batch Rules
	 * 
	 * @param rules
	 * @param methodListMap
	 * @return
	 */
	public String externalizeBatchRule(List<Map<String, Object>> rules,String extractClassName,RuleReportVO ruleRepVO) {
		
		if(LogUtil.isDebugEnabled(LOG_NAME)){
            LogUtil.debug(CLASS_NAME, "externalizeBatchRule", "Batch Rule Extraction Initiate", LOG_NAME);
		}
		/*
		 * first, get and initialize an engine
		 */

		VelocityEngine ve = new VelocityEngine();
		ve.setProperty("resource.loader", "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		ve.init();

		/*
		 * organize our data
		 */

		RuleDescriptor ruleDescriptor = null;

		String ruleInvokeCode = null;

		try {
			String ruleId;
			String extractText = null;
			String returnBoolean=null;
			List<String> importsList = null;
			Map<String, String> ruleContextParams = null;
			List<RuleDescriptor> ruleDescriptorList = null;
			ruleDescriptorList = new ArrayList<RuleDescriptor>();
			List<String> methodList = null;
			RuleDescriptionInfo ruleDescInfo;

			for (Map<String, Object> rule : rules) {

				ruleDescriptor = new RuleDescriptor();
				ruleDescInfo=new RuleDescriptionInfo();
				ruleId = (String) rule.get("ruleId");
				extractText = (String) rule.get("extractText");
				importsList = (List<String>) rule.get("importsList");
				ruleContextParams = (Map<String, String>) rule.get("ruleContextParams");
				returnBoolean=(String)rule.get("returnBoolean");
				System.out.println("ruleId==> " + ruleId);
				//System.out.println("ruleContextParams==>" + ruleContextParams.size());
				
				// to get externalize methods
				methodList = new ArrayList<String>();
				
				if(LogUtil.isDebugEnabled(LOG_NAME)){
		            LogUtil.debug(CLASS_NAME, "externalizeBatchRule", 
		            		"Getting rule details from XML file", LOG_NAME);
				}
				// Getting rule details from XML file
				ruleDescInfo=getRuleDescriptionInfo(ruleId);
				
				//need to remove code once Internal method identification completed automatically
				if(ruleDescInfo.getInternalMethodtoExtract()!=null 
						&& ruleDescInfo.getInternalMethodtoExtract().size()>0)
					getMethodDetails(ruleDescInfo.getInternalMethodtoExtract(),methodList,importsList);
				
				if(ruleDescInfo.getExternalMethodtoExtract()!=null 
						&& ruleDescInfo.getExternalMethodtoExtract().size()>0)
					getMethodDetails(ruleDescInfo.getExternalMethodtoExtract(),methodList,importsList);
				
				
				ruleDescriptor.setImportsList(importsList);
				ruleDescriptor.setRuleContextParams(ruleContextParams);
				ruleDescriptor.setRuleText(extractText);
				ruleDescriptor.setRuleId(ruleId);
				ruleDescriptor.setRuleName(ruleId);
				//ruleDescriptor.setRuleDescription();
				ruleDescriptor.setRulePackageName(ruleDescInfo.getPackageName());
				ruleDescriptor.setRuleClassName(ruleDescInfo.getClassName());
				ruleDescriptor.setRuleReturnField(returnBoolean);
				ruleDescriptor.setRuleExtractClassName(extractClassName);
				ruleDescriptor.setRuleMethods(methodList);
				//System.out.println("Method list size==>" + methodList.size());

				ruleDescriptorList.add(ruleDescriptor);
				
				// Adding metadata info in XML
				/*MetaDataInfo metaInfo = ruleDescInfo.getMetaDataInfo();
				MetaData.updateMetaDataInfo(metaInfo);
				if(LogUtil.isDebugEnabled(LOG_NAME)){
		            LogUtil.debug(CLASS_NAME, "externalizeBatchRule", "Updated Rules Metadata File", LOG_NAME);
				}*/

			}

			RuleTemplateUtility ruleTempUtility = new RuleTemplateUtility();

			/*
			 * add that list to a VelocityContext
			 */

			VelocityContext context = new VelocityContext();

			context.put("ruleDescriptorList", ruleDescriptorList);
			context.put("ruleDescriptor", ruleDescriptor);
			context.put("ruleTempUtility", ruleTempUtility);
			context.put("isBatchRule", true);

			Template t;
			StringWriter writer = new StringWriter();
			BufferedWriter ruleExtractionWriter = null;
			// Iteration to create pojos
			List<File> fileList=new ArrayList<>();
			for (RuleDescriptor ruleDesc : ruleDescriptorList) {

				String filepath = getClassPath(ruleDesc.getRulePackageName(),ruleDesc.getRuleClassName());
				System.out.println("filepath==> " + filepath);
				
				File file = new File(filepath);
				fileList.add(file);
				ruleExtractionWriter = new BufferedWriter(new FileWriter(file));
				context.put("ruleDescriptor", ruleDesc);
				
				if(LogUtil.isDebugEnabled(LOG_NAME)){
					LogUtil.debug(CLASS_NAME, "externalizeBatchRule", "Generating POJO...", LOG_NAME);
		            LogUtil.debug(CLASS_NAME, "externalizeBatchRule", "POJO file path==>"+file.getAbsolutePath(), LOG_NAME);
				}

				/*
				 * get the Template
				 */
				t = ve.getTemplate(RULE_POJO_TEMPLATE);
				writer = new StringWriter();
				t.merge(context, writer);
				ruleExtractionWriter.write(writer.toString());
				// System.out.println( writer.toString() );

				if (ruleExtractionWriter != null)
					ruleExtractionWriter.close();
				if(LogUtil.isDebugEnabled(LOG_NAME)){
					LogUtil.debug(CLASS_NAME, "externalizeBatchRule", ruleDesc.getRuleClassName()+" POJO Created", LOG_NAME);
		            
				}
				
				//Code Beautifier
				formatCode(file.getAbsolutePath());
				
			}

			//Zip POJO Files
			File zipFile=ZipUtil.getZipFile(fileList);
			System.out.println("ZipFile==>"+zipFile);
			ruleRepVO.setPojoPath(zipFile.getAbsolutePath());
			ruleRepVO.setRuleBatchMode(true);		
			t = ve.getTemplate(RULE_INVOKE_TEMPLATE);
			writer = new StringWriter();
			t.merge(context, writer);

			if(LogUtil.isDebugEnabled(LOG_NAME)){
				LogUtil.debug(CLASS_NAME, "externalizeBatchRule", "Invocation code generated", LOG_NAME);
	            
			}
			 System.out.println( writer.toString() );
			ruleInvokeCode = writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogUtil.error(CLASS_NAME, "externalizeBatchRule", "Exception Description==>"+e, LOG_NAME);
			e.printStackTrace();
		} catch (Exception ex)
		{
			LogUtil.error(CLASS_NAME, "externalizeBatchRule", "Exception Description==>"+ex, LOG_NAME);
			ex.printStackTrace();
		}
		return ruleInvokeCode;

	}
	
	
	/**
	 * Method to retrieve method extract details
	 * @param methodtoExtract
	 * @param methodList
	 * @param importsList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void getMethodDetails(List<String> methodtoExtract,
			List<String> methodList, List<String> importsList) throws FileNotFoundException, IOException {
		
		if(LogUtil.isDebugEnabled(LOG_NAME)){
			LogUtil.debug(CLASS_NAME, "getMethodDetails", "Getting Method Details...", LOG_NAME);
            
		}
		
		String classFile =null;
		//String methodName;
		for(String methodName:methodtoExtract)
		{
			classFile=methodName.substring(0,methodName.lastIndexOf("."));
			methodName=methodName.substring(methodName.lastIndexOf(".")+1);
			
			if(LogUtil.isDebugEnabled(LOG_NAME)){
				LogUtil.debug(CLASS_NAME, "externalizeBatchRule", "Retrieving Method:"+methodName+"from Class:"+classFile, LOG_NAME);
	            
			}
			classFile=classFile+".java";
			List<File> fileList=new ArrayList<File>();
			getFileObject(new File(ROOT_DIRECTORY),classFile,fileList);
			if(fileList.size()==0){
				throw new FileNotFoundException("File Not Found. fileName :"+classFile);
			}
				
			for (File file:fileList){
				ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(
						file));
				JavaLexer lexer = new JavaLexer(input);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				JavaParser parser = new JavaParser(tokens);
				ParseTree tree = parser.compilationUnit();
				ParseTreeWalker walker = new ParseTreeWalker();				

				StringBuffer methodContent = new StringBuffer();
				List<String> methodImports = new ArrayList<String>();
				
				//ExtractMethodNamesListener ext =new ExtractMethodNamesListener(tokens,methodMap);
				ExtractMethodNamesListener ext =new ExtractMethodNamesListener(tokens,methodName,methodContent,methodImports);
				walker.walk(ext, tree);	
				if(!methodList.contains(methodContent.toString())){
					methodList.add(methodContent.toString());	
				}

				importsList.addAll(methodImports);
			}
		}
		
	}

	/**
	 * Method to get rule info from XML file for externalize
	 * @param ruleID
	 * @return
	 * @throws Exception
	 */
	//TODO need to modify to support manual & automate extract
	private RuleDescriptionInfo getRuleDescriptionInfo(String ruleID) throws Exception {
		
		if(LogUtil.isDebugEnabled(LOG_NAME)){
			LogUtil.debug(CLASS_NAME, "getRuleDescriptionInfo", 
					"Retrieving Rule details from XML.Ruleid==>"+ruleID, LOG_NAME);  
		}
		//System.out.println("<<<getRuleDescriptionInfo method Call>>>");
		SAXParserFactory saxParserFactory = null;
		RuleDescriptionInfo ruleDescInfo=new RuleDescriptionInfo();
		
		 String implClass=ruleID;
		 String packageName="com.xerox.enterprise.ghs.mmis.rif.rules.pojo";
		 String className=ruleID.replace(".", "_");
		 ruleDescInfo.setPackageName(packageName);
		 ruleDescInfo.setClassName(className);
		 return ruleDescInfo;
	}

	

	/**
	 * To get rule id from EXTRACT comment 
	 * 
	 * @param token
	 * @return
	 */
	public String getRuleId(Token token) {
		if(LogUtil.isDebugEnabled(LOG_NAME)){
			LogUtil.debug(CLASS_NAME, "getRuleId", 
					"getting ruleId..", LOG_NAME);  
		}

		//System.out.println("getRuleProperty==>Token=>" + token.getText());
		String comments[] = token.getText().split(" ");
		String ruleId = null;

		if (comments.length > 1)
			ruleId = comments[1];
		//System.out.println("rule id==> " + ruleId);

		return ruleId.trim();
	}

	/**
	 * Retrieves POJO class path
	 * 
	 * @param packageName
	 * @param className
	 * @return
	 */
	private String getClassPath(String packageName, String className) {
		if(LogUtil.isDebugEnabled(LOG_NAME)){
			LogUtil.debug(CLASS_NAME, "getClassPath", 
					"getting ClassPath..", LOG_NAME);  
		}

		String path = null;
		//commented to place POJO in server folder
		//path = packageName.replace(".", "\\");
		//new File(FILE_SOURCE_BASE_URL + path).mkdirs();
		//String fullPath =FILE_SOURCE_BASE_URL + path + "\\" + className + ".java";
		String fullPath =FILE_SOURCE_BASE_URL  + "\\" + className + ".java";
		File file=new File(fullPath);
		int count=1;
		while(file.exists())
		{
			//fullPath =FILE_SOURCE_BASE_URL + path + "\\" + className +"_"+count++ + ".java";
			fullPath =FILE_SOURCE_BASE_URL + "\\" + className +"_"+count++ + ".java";
			file=new File(fullPath);
		}
		//System.out.println("Class Path ==> " + fullPath);

		return fullPath;
	}
	
	/**To find file from root directory
	 * @param rootDir
	 * @param fileName
	 * @param list
	 */
	private void getFileObject(File rootDir,String fileName, List<File> list) {
		/*if(LogUtil.isDebugEnabled(LOG_NAME)){
			LogUtil.debug(CLASS_NAME, "getFileObject", 
					"getting FileObject ...", LOG_NAME);  
		}*/
		File[] files = rootDir.listFiles();
		for (int i = 0; i < files.length; i++) {			
			if (files[i].isDirectory())
				getFileObject(files[i],fileName,list);
			else {
				String name=files[i].getAbsolutePath().replaceAll("\\\\",".");									
				if(name.indexOf(fileName)!=-1){
					list.add(files[i]);
				}
			}
		}
		
	}
	
	
	//POJOFormatter
	private void formatCode(String sourceCodePath) {
		try{
			JacobeFormatter formatter = JacobeFormatter.getInstance();
			formatter.formatGeneratedCode(sourceCodePath);
			formatter.deleteJacobeFiles(new File(sourceCodePath));
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
