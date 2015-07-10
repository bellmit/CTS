package com.cognizant.parser.ANTLR.listeners;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.NotNull;

import com.cognizant.rif.common.logging.LogUtil;
import com.cognizant.javagrammar.JavaBaseListener;
import com.cognizant.javagrammar.JavaParser;

public class ExtractConstantsListener extends JavaBaseListener{
	
	//public TokenStreamRewriter rewriter;
	//BufferedTokenStream tokens;
	BufferedWriter messageWriter=null;
	StringBuffer messageText=null;
	String nestedIf;
	JavaParser parser;
	String className;
	Map<String,String> messageMap;
	public Map<String,String> foundConstantsMap=new HashMap<String,String>();
	List<String> messages=new ArrayList<String>();
    private static final String CLASS_NAME = "ExtractConstantsListener";
    private static final String LOG_NAME = "EXTRACT_LOG";
    public List<String> keysToBeRemoved=new ArrayList<String>();
    BufferedTokenStream tokens;
    String fileName=null;
	public ExtractConstantsListener(BufferedTokenStream tokens,JavaParser parser,BufferedWriter messageWriter,Map<String,String> messageMap, List<String> messages,String fileName){

		this.messageWriter = messageWriter;
		this.parser=parser;	
		this.messageMap=messageMap;
		this.messages=messages;
		this.tokens=tokens;
		this.fileName=fileName;
	}
	@Override
	public void enterClassDeclaration(@NotNull JavaParser.ClassDeclarationContext ctx) {
		messageText = new StringBuffer();
		if(ctx.Identifier()!=null){
			className=ctx.Identifier().getText();
			//System.out.println("Extracting constants : "+className);
		}
		
	}
	
	@Override
	public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx){
		
	}
	
	@Override
	public void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx){
		
	}
	

	
	@Override public void enterInterfaceMethodOrFieldDecl(@NotNull JavaParser.InterfaceMethodOrFieldDeclContext ctx) {
		TokenStream tokens = parser.getTokenStream();
		String stmt=ctx.getText();
		String varName=null;
		if(ctx.Identifier()!=null)
			varName=ctx.Identifier().getText();
		
		String dataType=null;
		if ( ctx.type()!=null ) {
			dataType=tokens.getText(ctx.type());
		}

		try{
			if("String".equals(dataType)){
				String[] data=stmt.split("=");
				if(data.length>=2){
				//String name=data[0].trim();
				String varValue=data[1].trim();				
				varValue=varValue.replaceAll("\"", "");
				int index=varValue.lastIndexOf(";");
				
				if(index!=-1 && index==(varValue.length()-1)){
					varValue=varValue.substring(0,varValue.length()-1);
				}
					//Constant File Search case
				if(messages!=null){
					for(String messageValue:messages){
						if(varValue!=null && varValue.equalsIgnoreCase(messageValue)){
								
								if(!foundConstantsMap.containsKey(fileName+"."+varName)){
									foundConstantsMap.put(fileName+"."+varName,messageValue);
								}
								
								if(LogUtil.isDebugEnabled(LOG_NAME)){
									LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Match Found At file"+className, LOG_NAME);
									LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Match varName,varValue,dataType="+fileName+"."+varName+" , "+messageValue+" , "+dataType, LOG_NAME);
								}
							}
					}				
				}
					//Property File Search Case
					for(String messageKey:messageMap.keySet()){
					if(varValue!=null && varValue.equalsIgnoreCase(messageKey)){
							//change from varName to name
							if(!foundConstantsMap.containsKey(varName)){
								foundConstantsMap.put(fileName+"."+varName,messageMap.get(messageKey));
								keysToBeRemoved.add(messageKey);
							}
							
							if(LogUtil.isDebugEnabled(LOG_NAME)){
								LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Match Found At file"+className, LOG_NAME);
								LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Match varName,varValue,dataType="+fileName+"."+varName+" , "+varValue+" , "+dataType, LOG_NAME);
							}
						}
						else if(varValue!=null)
						{
							
							String vName=getMessageAppendNameExist(messageKey,varValue);
							if(vName!=null)
							{
								if(!foundConstantsMap.containsKey(fileName+"."+vName)){
									foundConstantsMap.put(fileName+"."+varName,messageMap.get(messageKey));
								}
								
								if(LogUtil.isDebugEnabled(LOG_NAME)){
									LogUtil.debug(CLASS_NAME, "enterInterfaceMethodOrFieldDecl", "Append mode Match Found At file"+className, LOG_NAME);
									LogUtil.debug(CLASS_NAME, "enterInterfaceMethodOrFieldDecl", "Append Match varName,varValue,dataType="+varName+" , "+varValue+" , "+dataType, LOG_NAME);
								}
							}
						}
					}
				}
			}
		}catch(Exception ex){
			if(LogUtil.isDebugEnabled(LOG_NAME)){
				LogUtil.error(CLASS_NAME, "enterFieldDeclaration", "Error while parsig the field data varName,dataType="+fileName+"."+varName+" , "+dataType, LOG_NAME);
			}
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public void enterFieldDeclaration(@NotNull JavaParser.FieldDeclarationContext ctx) { 
		TokenStream tokens = parser.getTokenStream();
		String varName=tokens.getText(ctx.variableDeclarators());
		String dataType=tokens.getText(ctx.type());
		try{
			if("String".equals(dataType)){
				String[] data=varName.split("=");
				if(data.length>=2){
				String name=data[0].trim();
				String varValue=data[1].trim();
				varValue=varValue.replaceAll("\"", "");
				
				//Constant File Search case
				if(messages!=null){
					for(String messageValue:messages){
						if(varValue!=null && varValue.equalsIgnoreCase(messageValue)){
								
								if(!foundConstantsMap.containsKey(fileName+"."+name)){
									foundConstantsMap.put(fileName+"."+name,messageValue);
								}
								
								if(LogUtil.isDebugEnabled(LOG_NAME)){
									LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Match Found At file"+className, LOG_NAME);
									LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Match varName,varValue,dataType="+fileName+"."+name+" , "+messageValue+" , "+dataType, LOG_NAME);
								}
							}
					}
				}
				//Property File Search Case
					for(String messageKey:messageMap.keySet()){
						if(varValue!=null && varValue.equalsIgnoreCase(messageKey)){
								//Changed on 16 -9-2014
								if(!foundConstantsMap.containsKey(fileName+"."+name)){
									foundConstantsMap.put(fileName+"."+name,messageMap.get(messageKey));
									keysToBeRemoved.add(messageKey);
								}
									
								if(LogUtil.isDebugEnabled(LOG_NAME)){
									LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Match Found At file"+className, LOG_NAME);
									LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Match varName,varValue,dataType="+fileName+"."+name+" , "+varValue+" , "+dataType, LOG_NAME);
								}
						}
						else if(varValue!=null)
						{
							
							String vName=getMessageAppendNameExist(messageKey,varValue);
							if(vName!=null)
							{
								if(!foundConstantsMap.containsKey(fileName+"."+vName)){
									foundConstantsMap.put(fileName+"."+varName,messageMap.get(messageKey));
								}
								
								if(LogUtil.isDebugEnabled(LOG_NAME)){
									LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Append mode Match Found At file"+className, LOG_NAME);
									LogUtil.debug(CLASS_NAME, "enterFieldDeclaration", "Append Match varName,varValue,dataType="+varName+" , "+varValue+" , "+dataType, LOG_NAME);
								}
								}
						}
					}
				}
			}
		}catch(Exception ex){
			if(LogUtil.isDebugEnabled(LOG_NAME)){
				LogUtil.error(CLASS_NAME, "enterFieldDeclaration", "Error while parsig the field data varName,dataType="+varName+" , "+dataType, LOG_NAME);
			}
		}		
	}	
	
	private String getMessageAppendNameExist(String key,String varValue)
	{
		//TODO: manual hardcoded fix need to modify
		String err="error";
		String errdot="error.";
		String errCmn="error.common";
		String errInvalid="error.invalid";
		String dotreq=".required";
		String req="required";
		String vName=null;
		
		if(key.startsWith(errCmn))
		{
			key=key.substring(errCmn.length());
		}else if(key.startsWith(errInvalid))
		{
			key=key.substring(errInvalid.length());
		}else if(key.startsWith(errdot))
		{
			key=key.substring(errdot.length());
		}else if(key.startsWith(err))
		{
			key=key.substring(err.length());
		}
		//System.out.println("Error trim Key==>"+key);
		if(key.endsWith(dotreq))
		{
			key=key.substring(0, key.indexOf(dotreq));
		}
		else if(key.endsWith(req))
		{
			key=key.substring(0,key.indexOf(req));
		}
		
		Pattern pat=Pattern.compile(key, Pattern.CASE_INSENSITIVE + Pattern.LITERAL);
		//System.out.println("Req trim Key==>"+key);
		if(pat.matcher(varValue).find())
		{
			vName=varValue;
		}
		else
		{
			if(key.contains("invalid"))
			{
				key=key.replace("invalid","");
				pat=Pattern.compile(key, Pattern.CASE_INSENSITIVE + Pattern.LITERAL);
				//System.out.println("Req trim Key==>"+key);
				if(pat.matcher(varValue).find())
				{
					vName=varValue;
				}
			}
		}
		//System.out.println("vName==>"+vName);
		return vName;
	}

}
