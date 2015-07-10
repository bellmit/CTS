package com.cognizant.rif.utilities;

import java.util.Map;

public class RuleTemplateUtility {
	
	
	static final String exceptionMsg="throw new RIFPOJORulesException(\"Unable to initialize the required rules objects for rule \"+ruleId);";
	static final String logMsg="LOG.log(Level.ERROR,\"Input Object ? is  null during intialization for the rule \"+ruleId);";
	
	public static String getLocalVariableDeclarations (Map<String, String> ruleContextParams){
		
		StringBuffer localDeclaration = new StringBuffer();
		boolean firstTime = true;
		if(ruleContextParams!=null)
		{
			
			for(String varName : ruleContextParams.keySet()){
				
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
					
					localDeclaration.append("false;\n");
					
				}
				else if(varType.equals("long") || varType.equals("Long")) {
					localDeclaration.append("0L;\n");
				}
				else if(varType.equals("int") || varType.equals("Integer")) {
					localDeclaration.append("0;\n");
				}
				else if(varType.equals("double") || varType.equals("Double")) {
					localDeclaration.append("0d;\n");
				}
				else if(varType.equals("short") || varType.equals("Short")) {
					localDeclaration.append("0;\n");
				}		
				else
				{
					localDeclaration.append("null;\n");
				}
			}	
			
		}
		return localDeclaration.toString();
	}
	
	
	/*public static String getAssignmentStatementsFromContext (Map<String, String> ruleContextParams, String objName){
		StringBuffer assignMentStmts = new StringBuffer();
		boolean firstTime = true;
		for(String varName : ruleContextParams.keySet()){
			System.out.println("Varname==>"+varName);
			System.out.println("VarType==>"+ruleContextParams.get(varName));
			System.out.println("Object==>"+objName);
			String varType = ruleContextParams.get(varName);
			if(!firstTime){
				assignMentStmts.append("\t\t\t\t");
			}
			firstTime = false;
			if(varType.equals("boolean") || varType.equals("Boolean")){
				
				assignMentStmts.append(varName);
				assignMentStmts.append(" = ");
				assignMentStmts.append("(");
				assignMentStmts.append("Boolean");
				assignMentStmts.append(") ");
				assignMentStmts.append(objName);
				assignMentStmts.append(";\n");
				
			}
			else if(varType.equals("long") || varType.equals("Long")) {
				assignMentStmts.append(varName);
				assignMentStmts.append(" = ");
				assignMentStmts.append("(");
				assignMentStmts.append("Long");
				assignMentStmts.append(") ");
				assignMentStmts.append(objName);
				assignMentStmts.append(";\n");
			}
			else if(varType.equals("int") || varType.equals("Integer")) {
				assignMentStmts.append(varName);
				assignMentStmts.append(" = ");
				assignMentStmts.append("(");
				assignMentStmts.append("Integer");
				assignMentStmts.append(") ");
				assignMentStmts.append(objName);
				assignMentStmts.append(";\n");
			}
			else if(varType.equals("double") || varType.equals("Double")) {
				assignMentStmts.append(varName);
				assignMentStmts.append(" = ");
				assignMentStmts.append("(");
				assignMentStmts.append("Double");
				assignMentStmts.append(") ");
				assignMentStmts.append(objName);
				assignMentStmts.append(";\n");
			}
			else if(varType.equals("short") || varType.equals("Short")) {
				assignMentStmts.append(varName);
				assignMentStmts.append(" = ");
				assignMentStmts.append("(");
				assignMentStmts.append("Short");
				assignMentStmts.append(") ");
				assignMentStmts.append(objName);
				assignMentStmts.append(";\n");
			}		
			else
			{
				assignMentStmts.append("if (");
				assignMentStmts.append(objName);
				assignMentStmts.append(" instanceof ");
				assignMentStmts.append(varType);
				assignMentStmts.append(") {");
				assignMentStmts.append("\n\t\t\t\t\t");
				assignMentStmts.append(varName);
				assignMentStmts.append(" = ");
				assignMentStmts.append("(");
				assignMentStmts.append(varType);
				assignMentStmts.append(") ");
				assignMentStmts.append(objName);
				assignMentStmts.append(";\n");
				assignMentStmts.append("\t\t\t\t");
				assignMentStmts.append("}\n");
			}
		}
		
		
		return assignMentStmts.toString();
	}*/

	//TO test for List
	public static String getAssignmentStatementsFromContext (Map<String, String> ruleContextParams,String objName){
		StringBuffer assignMentStmts = new StringBuffer();
		boolean firstTime = true;
		int i=0;
		if(ruleContextParams!=null)
		{
			for(String varName : ruleContextParams.keySet())
			{
				//System.out.println("Varname==>"+varName);
				//System.out.println("VarType==>"+ruleContextParams.get(varName));
				String input=objName+".get("+i+++")";
				//System.out.println("input==>"+input);
				String varType = ruleContextParams.get(varName);
				if(!firstTime){
					assignMentStmts.append("\t\t\t\t");
				}
				firstTime = false;
				
				assignMentStmts.append(varName);
				assignMentStmts.append(" = ");
				assignMentStmts.append("(");
				assignMentStmts.append(getVariableCastObject(varType));
				assignMentStmts.append(") ");
				assignMentStmts.append(input);
				assignMentStmts.append(";\n");
				assignMentStmts.append(getNULLCheckCode(varName,varType)+"\n");
				
			}
		}
		
		return assignMentStmts.toString();
	}
	
	
	private static String getVariableCastObject(String varType) {
		// TODO Auto-generated method stub
		String castObj=null;
		switch (varType) {
		case "boolean":
			castObj = "Boolean";
			break;
		case "int":
			castObj = "Integer";
			break;
		case "long":
			castObj = "Long";
			break;
		case "short":
			castObj = "Short";
			break;
		case "double":
			castObj = "Double";
			break;

		default:
			castObj = varType; 
			break;
		}
		return castObj;
	}


	public int getSizeOfObjectsInContext(Map<String, String> ruleContextParams){
		
		if(ruleContextParams != null) {
			return ruleContextParams.keySet().size();
		}
		
		return 0;
	}
	
	public static String getAddObjectsToContext (Map<String, String> ruleContextParams, String contextName,String object,String ruleId){
		StringBuffer addObjectStmts = new StringBuffer();
		boolean firstTime = true;
		if(ruleContextParams!=null)
		{
			for(String varName : ruleContextParams.keySet()){
				if(!firstTime){
					addObjectStmts.append("\t\t\t\t");
				}
				firstTime = false;
				addObjectStmts.append(object);
				addObjectStmts.append(".add(");
				addObjectStmts.append(varName);
				addObjectStmts.append(");\n");
				
				
			}
		}
		addObjectStmts.append("contextName");
		addObjectStmts.append(".addObjects(");
		addObjectStmts.append(ruleId+","+object);
		
		return addObjectStmts.toString();
	}
	
	
	public static String getAddObjectsToContext (Map<String, String> ruleContextParams, String contextName){
		StringBuffer addObjectStmts = new StringBuffer();
		boolean firstTime = true;
		if(ruleContextParams!=null)
		{
			for(String varName : ruleContextParams.keySet()){
				if(!firstTime){
					addObjectStmts.append("\t\t\t\t");
				}
				firstTime = false;
				addObjectStmts.append(contextName);
				addObjectStmts.append(".addObject(");
				addObjectStmts.append(varName);
				addObjectStmts.append(");\n");
				
				
			}
		}		
		
		return addObjectStmts.toString();
	}
	
	private static String getNULLCheckCode(String varName,String varType)
	{
		StringBuilder nullCheckStr=new StringBuilder();
		if(!isPrimitiveType(varType))
		{
			nullCheckStr.append("if("+varName+"==null){\n");
			/*nullCheckStr.append("if(LOG.isEnabledFor(Level.ERROR)){\n");*/
			nullCheckStr.append(logMsg.replace("?", varName)+"\n");
			/*nullCheckStr.append("}\n");*/
			nullCheckStr.append(exceptionMsg+"\n");
			nullCheckStr.append("}\n");
		}
		System.out.println(nullCheckStr);
		
		return nullCheckStr.toString();
	}


	private static boolean isPrimitiveType(String varType) {
		// TODO Auto-generated method stub
		if(varType.equals("boolean") || varType.equals("long") || 
				varType.equals("int") || varType.equals("double") 
				|| varType.equals("short") )
		{
			return true;
		}
		return false;
	}
}
