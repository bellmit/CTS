package com.cognizant.rif.junit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.cognizant.javagrammar.JavaLexer;
import com.cognizant.javagrammar.JavaParser;
import com.cognizant.parser.ANTLR.listeners.ExtractMethodDetailsListener;
import com.cognizant.junit.bean.ExtractClassVO;
import com.cognizant.junit.bean.MethodComposite;
import com.cognizant.junit.bean.TemplateEntry;

/**
 * @author 396662
 *
 */
public class JunitGenerator {
	
	/**
	 * To generate Junit based on Pojo class
	 */
	public String generate(String pojofile)
	{
		ExtractClassVO exclassVO=new ExtractClassVO();
		File file = new File(pojofile);
    	extractJunitParams(file,exclassVO);
    	//System.out.println(file.getParent());
    	String jPath=file.getParent();
    	jPath =jPath  + "\\" + exclassVO.getClassName() + "Test.java";
        junitGenerator(jPath,exclassVO);
       
        return jPath;
	}

	
	private static void extractJunitParams(File fileName,ExtractClassVO exClassVO)
    {
    	System.out.println("Extracting POJO for Junit data... ");

    	ANTLRInputStream input;
		try {
			input = new ANTLRInputStream(new FileInputStream(
					fileName));
		
		JavaLexer lexer = new JavaLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JavaParser parser = new JavaParser(tokens);
		ParseTree tree = parser.compilationUnit();
		ParseTreeWalker walker = new ParseTreeWalker();
		ExtractMethodDetailsListener extractor = new ExtractMethodDetailsListener(tokens, exClassVO);
		walker.walk(extractor, tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Imports==>"+exClassVO.getImportList());
		System.out.println("Local Var==>"+exClassVO.getLocalVarMap());
		
		
		
    }
    
    private void getContextObjectVariable(
			Map<String, Map<String, String>> localVarMap, List<String> ctxObjs,String methodName) {
    	if(localVarMap!=null && localVarMap.containsKey(methodName))
    	{
    	for(Entry<String, String> entry:localVarMap.get(methodName).entrySet())
    	{
    		ctxObjs.add(entry.getKey());
    	}
		//System.out.println("Before removing: "+ctxObjs);
		ctxObjs.remove(0);
		ctxObjs.remove(ctxObjs.size()-1);
		//System.out.println("After removing: "+ctxObjs);
    	}
	}
	private void junitGenerator(String fPath,ExtractClassVO exClassVo)
    {
    	/*
         *   first, get and initialize an engine
         */

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty("resource.loader", "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        ve.init();

        /*
         *   organize our data 
         */
        String methodName="execute";
		System.out.println("Pojo Class data");
		System.out.println("Method signature:"+exClassVo.getMethodSignature().get(methodName));
		System.out.println("Local Variables:"+getLocalVariableDeclarations(exClassVo.getLocalVarMap(),methodName));
		List<String> ctxObjs= new ArrayList<>();
		getContextObjectVariable(exClassVo.getLocalVarMap(),ctxObjs,methodName);
		System.out.println("Context Object:"+ctxObjs);
    	
        
        String className=exClassVo.getClassName();
        System.out.println("className:::>"+className);
		String packageName=exClassVo.getPackageName();
		List<MethodComposite> methodList=new ArrayList<>();
		List<MethodComposite> privateMethodList=new ArrayList<>();
		List<MethodComposite> fieldList =new ArrayList<>();
		MethodComposite methodComp=new MethodComposite();
		methodComp.setName(methodName);
		methodComp.setSignature(exClassVo.getMethodSignature().get(methodName));
		methodComp.setParamNames(getLocalVariableDeclarations(exClassVo.getLocalVarMap(),methodName));
        methodComp.setRifContextObj(ctxObjs);
		methodList.add(methodComp);
        
        /*
         *  add that list to a VelocityContext
         */

        VelocityContext context = new VelocityContext();
        
		TemplateEntry te=new TemplateEntry(className, packageName, methodList, privateMethodList, fieldList);
        context.put("entry",te);
        context.put("today", new Date());
        context.put("authorName", 396662);
        context.put("imports",exClassVo.getImportList());
        //System.out.println(te.getClassName());
        //System.out.println(context.get("entry"));
        /*
         *   get the Template  
         */

      
        
        
        Template t = ve.getTemplate( "/junit.vm" );
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        System.out.println( writer.toString() );
        
        /*
         * Write to output file
         */
        try {
        	
        	File file=new File(fPath);
        	System.out.println("JunitPath:>"+fPath);
        	BufferedWriter bw=new BufferedWriter(new FileWriter(file));

        	bw.write(writer.toString());
        	bw.close();
        	
        	try{
    			//JacobeFormatter formatter = JacobeFormatter.getInstance();
    			//formatter.formatGeneratedCode(file.getPath());
    			//formatter.deleteJacobeFiles(new File(file.getParent()));
    		} catch(Exception e){
    			e.printStackTrace();
    		}
        } catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
    }
	
	private void junitGenerator(String fPath,ExtractClassVO exClassVo,Map<String,String> ruleContextParams,String ruleId,String ruleInvokeCode)
    {
		
    	/*
         *   first, get and initialize an engine
         */

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty("resource.loader", "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        ve.init();

        /*
         *   organize our data 
         */
        String methodName="execute";
		System.out.println("Pojo Class data");
		System.out.println("Method signature:"+exClassVo.getMethodSignature().get(methodName));
		System.out.println("Local Variables:"+getLocalVariableDeclarations(exClassVo.getLocalVarMap(),methodName));
		
        
        String className=exClassVo.getClassName();
        System.out.println("className:::>"+className);
		String packageName=exClassVo.getPackageName();
		List<MethodComposite> methodList=new ArrayList<>();
		List<MethodComposite> privateMethodList=new ArrayList<>();
		List<MethodComposite> fieldList =new ArrayList<>();
		MethodComposite methodComp=new MethodComposite();
		methodComp.setName(methodName);
		methodComp.setSignature(exClassVo.getMethodSignature().get(methodName));
		methodComp.setParamNames(getLocalVariableDeclarations(ruleContextParams));
        methodComp.setRuleInvokeCode(ruleInvokeCode);
		methodList.add(methodComp);
        
        /*
         *  add that list to a VelocityContext
         */

        VelocityContext context = new VelocityContext();
        
		TemplateEntry te=new TemplateEntry(className, packageName, methodList, privateMethodList, fieldList);
        context.put("entry",te);
        context.put("today", new Date());
        context.put("authorName", 396662);
        context.put("imports",exClassVo.getImportList());
        context.put("ruleId",ruleId);
        //System.out.println(te.getClassName());
        //System.out.println(context.get("entry"));
        /*
         *   get the Template  
         */

      
        
        
        Template t = ve.getTemplate( "/junit_1.0.vm" );
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        System.out.println( writer.toString() );
        
        /*
         * Write to output file
         */
        try {
        	
        	File file=new File(fPath);
        	System.out.println("JunitPath:>"+fPath);
        	BufferedWriter bw=new BufferedWriter(new FileWriter(file));

        	bw.write(writer.toString());
        	bw.close();
        	
        	try{
    			//JacobeFormatter formatter = JacobeFormatter.getInstance();
    			//formatter.formatGeneratedCode(file.getPath());
    			//formatter.deleteJacobeFiles(new File(file.getParent()));
    		} catch(Exception e){
    			e.printStackTrace();
    		}
        } catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
    }
    
	public List<String> getLocalVariableDeclarations (Map<String, Map<String, String>> localVarMap,String methodName){
		
		StringBuffer localDeclaration = new StringBuffer();
		List<String> localVariables =new ArrayList<>();
		boolean firstTime = true;
		if(localVarMap!=null && localVarMap.containsKey(methodName))
		{
			
			for(String varName : localVarMap.get(methodName).keySet()){
				localDeclaration = new StringBuffer();
				String varType = localVarMap.get(methodName).get(varName);
				/*if(!firstTime){
					localDeclaration.append("\t\t\t");
				}*/
				firstTime = false;
				localDeclaration.append(varType);
				localDeclaration.append(" ");
				localDeclaration.append(varName);
				localDeclaration.append(" = ");
				if(varType.equals("boolean") || varType.equals("Boolean")){
					
					localDeclaration.append("false;");
					
				}
				else if(varType.equals("long") || varType.equals("Long")) {
					localDeclaration.append("0L;");
				}
				else if(varType.equals("int") || varType.equals("Integer")) {
					localDeclaration.append("0;");
				}
				else if(varType.equals("double") || varType.equals("Double")) {
					localDeclaration.append("0d;");
				}
				else if(varType.equals("short") || varType.equals("Short")) {
					localDeclaration.append("0;");
				}		
				else
				{
					localDeclaration.append("null;");
				}
				localVariables.add(localDeclaration.toString());
			}	
			
		}
		return localVariables;
	}

	/**
	 * To generate Junit based on invocation code
	 */
	public String generate(String pojofile,
			Map<String, String> ruleContextParams, String ruleId,
			String ruleInvokeCode) {
		// TODO Auto-generated method stub
		ExtractClassVO exclassVO=new ExtractClassVO();
		File file = new File(pojofile);
    	extractJunitParams(file,exclassVO);
    	//System.out.println(file.getParent());
    	String jPath=file.getParent();
    	jPath =jPath  + "\\Test" + exclassVO.getClassName() + ".java";
        junitGenerator(jPath,exclassVO,ruleContextParams,ruleId,ruleInvokeCode);
        
        return jPath;
	}
	
	private List<String> getLocalVariableDeclarations (Map<String, String> ruleContextParams){
		
		StringBuffer localDeclaration = new StringBuffer();
		boolean firstTime = true;
		List<String> localVariables =new ArrayList<>();
		if(ruleContextParams!=null)
		{
			
			for(String varName : ruleContextParams.keySet()){
				localDeclaration = new StringBuffer();
				String varType = ruleContextParams.get(varName);
				if(!firstTime){
					localDeclaration.append("\t\t\t");
				}
				firstTime = false;
				localDeclaration.append(varType);
				localDeclaration.append(" ");
				localDeclaration.append(varName);
				localDeclaration.append(" = ");
				if(varType.equals("boolean") || varType.equals("Boolean")){
					
					localDeclaration.append("false;");
					
				}
				else if(varType.equals("long") || varType.equals("Long")) {
					localDeclaration.append("0L;");
				}
				else if(varType.equals("int") || varType.equals("Integer")) {
					localDeclaration.append("0;");
				}
				else if(varType.equals("double") || varType.equals("Double")) {
					localDeclaration.append("0d;");
				}
				else if(varType.equals("short") || varType.equals("Short")) {
					localDeclaration.append("0;");
				}		
				else
				{
					localDeclaration.append("null;");
				}
				localVariables.add(localDeclaration.toString());
			}	
			
		}
		return localVariables;
	}
}
