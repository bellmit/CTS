package com.cognizant.rif.rules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.cognizant.javagrammar.JavaLexer;
import com.cognizant.javagrammar.JavaParser;
import com.cognizant.parser.ANTLR.listeners.ExtractConstantsListener;

public class MessageConstantExtractor {
	static List<String> constantFiles=new ArrayList<String>();
	

   private static final String CLASS_NAME = "MessageExtractor";
   /**
    * Class level variable that contains the logger name
    */
   
   private static final String LOG_NAME = "MESSAGE_EXTRACT_LOG";
   
   private static final String MESSAGE_FILE_NAME = "MessageIdResults.txt";
   
	public static void extractConstants(Map<String,String> constantsInput, List<String> constantFile,Map<String,String> messageMap,List<String> messages, String destinationPath) 
	throws IOException, FileNotFoundException {

		
		BufferedWriter messageWriter = null;
		String filePath = destinationPath +"\\"+MESSAGE_FILE_NAME;
		File file = new File(filePath);
		messageWriter = new BufferedWriter(new FileWriter(file));		
		ExtractConstantsListener extractor=null;
		List<String> keysToBeRemoved=new ArrayList<String>();
		for(String inputFile:constantFile){
			File fileName = new File(inputFile);
			ANTLRInputStream input = new ANTLRInputStream(
					new FileInputStream(fileName));
			JavaLexer lexer = new JavaLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(tokens);
			ParseTree tree = parser.compilationUnit();
			ParseTreeWalker walker = new ParseTreeWalker();
			String displayName=fileName.getName().replace(".java", "");
			extractor = new ExtractConstantsListener(
					tokens,parser, messageWriter, messageMap,messages,displayName);
			walker.walk(extractor, tree);
			constantsInput.putAll(extractor.foundConstantsMap);
			keysToBeRemoved.addAll(extractor.keysToBeRemoved);
		}		
		if(extractor!=null){
			messageMap.keySet().removeAll(keysToBeRemoved);
		}
		
		for(Entry<String, String> propertyValueData: messageMap.entrySet()){
			constantsInput.put(propertyValueData.getKey(), propertyValueData.getValue());
		}	
	}
	

	public static void extractPropertyKeys(
			List<String> propertyFiles,List<String> messages,Map<String,String> messagesMap) throws Exception {	
		Properties properties;
		InputStream input;
		File file;

		
		for(String filePath:propertyFiles){
			//System.out.println("File Path:"+filePath);
			file = new File(filePath);
			properties = new Properties();
			input = new FileInputStream(file);
			properties.load(input);
			for(String message:messages){
				if(message.endsWith(".")){
					message=message.substring(0,message.length()-1);
				}
//				if(properties.containsValue(messages[i])){
					for (Object key:properties.keySet()){
						String strValue=properties.getProperty(key.toString());
						if(strValue.endsWith(".")){
							strValue=strValue.substring(0,strValue.length()-1);
						}
		
						if(message.equals(strValue)){
							if(!messagesMap.containsKey(key.toString())){
								messagesMap.put(key.toString(), strValue);
								System.out.println("Found Key="+key+"Found Value="+strValue);
							}
						}						
					}
//				}
			}
		}				
	}	
}
