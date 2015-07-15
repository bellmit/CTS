package com.cognizant.parser.ANTLR.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.misc.NotNull;

import com.cognizant.javagrammar.JavaBaseListener;
import com.cognizant.javagrammar.JavaParser;
import com.cognizant.javagrammar.JavaParser.BlockStatementContext;
import com.cognizant.javagrammar.JavaParser.ClassDeclarationContext;
import com.cognizant.javagrammar.JavaParser.CompilationUnitContext;
import com.cognizant.javagrammar.JavaParser.ExpressionContext;
import com.cognizant.javagrammar.JavaParser.FieldDeclarationContext;
import com.cognizant.javagrammar.JavaParser.ImportDeclarationContext;
import com.cognizant.javagrammar.JavaParser.LocalVariableDeclarationContext;
import com.cognizant.javagrammar.JavaParser.MethodBodyContext;
import com.cognizant.javagrammar.JavaParser.MethodDeclarationContext;
import com.cognizant.javagrammar.JavaParser.ParExpressionContext;
import com.cognizant.javagrammar.JavaParser.StatementContext;
import com.cognizant.javagrammar.JavaParser.StatementExpressionContext;
import com.cognizant.rif.common.logging.LogUtil;
import com.cognizant.rif.dto.CodePart;
import com.cognizant.rif.rules.RuleExternalizer;
import com.cognizant.rif.vo.RuleReportVO;

/**
 * @author 183417
 * 
 */
public class RuleExtractionListener extends JavaBaseListener {

	private final String EXTRACT_RULE_START = "EXTRACT_START";
	private final String EXTRACT_RULE_END = "EXTRACT_END";
	private final String BATCH_START = "BATCH_START";
	private final String BATCH_END = "BATCH_END";

	/**
	 * Class level variable that contains the name of the class
	 */
	private static final String CLASS_NAME = "RuleExtractionListener";

	/**
	 * Class level variable that contains the logger name
	 */
	private static final String LOG_NAME = "EXTRACT_LOG";

	public TokenStreamRewriter rewriter;
	BufferedTokenStream tokens;

	BufferedWriter conditionWriter;
	String actPkgName;
	String actClassName;

	String actMethodName;
	Map<String, String> importMap = new HashMap<String, String>();
	Map<String, String> classVarMap = new HashMap<String, String>();
	Map<String, String> localVarMap = new HashMap<String, String>();

	List<Integer> startExtractTokens;
	List<Integer> endExtractTokens;
	List<String> ruleIDList;
	List<Integer> startBatchTokens;
	List<Integer> endBatchTokens;
	Map<Integer, List<String>> importValuesMap;
	Map<Integer, Map<String, String>> functionParamKeysMap;
	Map<Integer, List<String>> localCallsInCodeExtract;

	boolean methodNeedsExtraction;
	boolean methodHasBatchRule;

	// boolean return
	int startIndex, endIndex;
	List<String> booleanLocalVarSkip = new ArrayList<String>();
	List<String> localVartoSkip = new ArrayList<>();
	private String returnBoolean;
	String fileSourceBaseURL, rootDirectory;
	Map<String, Map<String, List<CodePart>>> conditionsToBeExtractedForMethod;
	int counter = 0;
	private RuleReportVO ruleRepVo;

	public RuleExtractionListener(String fileSourceBaseURL,
			String rootDirectory, BufferedTokenStream tokens)
					throws FileNotFoundException {
		this(fileSourceBaseURL, rootDirectory, tokens, null, null);
	}
	
	public RuleExtractionListener(String fileSourceBaseURL,
			String rootDirectory, BufferedTokenStream tokens,RuleReportVO ruleRepVO)
					throws FileNotFoundException {
		this(fileSourceBaseURL, rootDirectory, tokens, null, ruleRepVO);
	}

	public RuleExtractionListener(
			String fileSourceBaseURL,
			String rootDirectory,
			BufferedTokenStream tokens,
			Map<String, Map<String, List<CodePart>>> conditionsToBeExtractedForMethod,
			RuleReportVO ruleRepVO)
					throws FileNotFoundException {
		this.tokens = tokens;
		rewriter = new TokenStreamRewriter(tokens);
		this.fileSourceBaseURL = fileSourceBaseURL;
		this.rootDirectory = rootDirectory;
		this.conditionsToBeExtractedForMethod = conditionsToBeExtractedForMethod;
		this.ruleRepVo=ruleRepVO;
		// System.setOut(new PrintStream(new File("SysOut.txt")));
	}

	@Override
	public void enterImportDeclaration(ImportDeclarationContext ctx) {
		// System.out.println("Imorts declaration:"+ctx.getText());
		String importKey = tokens.get(ctx.stop.getTokenIndex() - 1).getText();
		String importValue = ctx.getText().substring(6,
				(ctx.getText().length()) - 1);
		// System.out.println("Import Type ="+ importKey);
		// System.out.println("full import ="+ importValue);
		importMap.put(importKey, importValue);
	}

	@Override
	public void enterClassDeclaration(ClassDeclarationContext ctx) {
		actClassName = ctx.Identifier().getText();
	}

	@Override
	public void exitClassDeclaration(ClassDeclarationContext ctx) {

		// To write rule invoke code in file
		File extractFile = new File(fileSourceBaseURL+ctx.Identifier().getText() + ".java");
		FileWriter writer = null;
		try {
			 System.out.println("Extracted File path"+extractFile.getAbsolutePath());
			writer = new FileWriter(extractFile);

			writer.write(rewriter.getText());
			writer.close();
			ruleRepVo.setInvokeCodePath(extractFile.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getStackTrace());
		}

	}

	@Override
	public void enterPackageDeclaration(
			@NotNull JavaParser.PackageDeclarationContext ctx) {

		actPkgName = tokens.getText(ctx.qualifiedName());

	}

	@Override
	public void enterClassBody(JavaParser.ClassBodyContext ctx) {
	}

	@Override
	public void enterMethodDeclaration(MethodDeclarationContext ctx) {
		startExtractTokens = new ArrayList<Integer>();
		endExtractTokens = new ArrayList<Integer>();
		startBatchTokens = new ArrayList<Integer>();
		endBatchTokens = new ArrayList<Integer>();
		importValuesMap = new HashMap<Integer, List<String>>();
		functionParamKeysMap = new HashMap<Integer, Map<String, String>>();
		localCallsInCodeExtract = new HashMap<Integer, List<String>>();
		methodNeedsExtraction = false;
		methodHasBatchRule = false;
		ruleIDList = new ArrayList<>();
		actMethodName = ctx.Identifier().getText();
		// System.out.println(" +++++ Method name :::" + actMethodName);
		localVarMap = new HashMap<String, String>();
		// System.out.println("MethodDeclarationContext:"+ctx.getText());
		if (ctx.formalParameters().formalParameterDecls() != null) {

			String methodParams = tokens.getText(ctx.formalParameters()
					.formalParameterDecls());
			List<String> decls = splitMethodParameters(methodParams);
			for (String methodParam : decls) {
				// System.out.println("param "+methodParam);
				methodParam = methodParam.trim();
				int index = methodParam.lastIndexOf(" ");
				if (index != -1) {
					String varType = methodParam.substring(0, index);
					varType = varType.replace("final", "").trim();
					String varName = methodParam.substring(index + 1).trim();
					localVarMap.put(varName, varType);
				}

			}
		}

	}

	@Override
	public void enterFieldDeclaration(FieldDeclarationContext ctx) {
		// System.out.println("Field declaration:" + ctx.getText());

		String variableDecl = tokens.getText(ctx.type());
		// System.out.println("variableDecl:" + variableDecl);

		String variableName = getVariableName(ctx.getText(), variableDecl);
		// System.out.println("Variable name :" + variableName);
		if (variableName != null) {
			classVarMap.put(variableName, variableDecl);
		}

	}

	/**
	 * @param declStatement
	 *            does not contain any white space
	 * @param variableDeclar
	 *            may contain white space
	 * @return
	 */
	private String getVariableName(String declStatement, String variableDeclar) {

		String varDeclWithoutWS = variableDeclar.replace(" ", "");
		String varDeclWithEscapeSpecial = varDeclWithoutWS.replace("[", "\\[")
				.replace("]", "\\]");
		String patternToSearch = varDeclWithEscapeSpecial
				+ "[a-zA-Z_$][a-zA-Z\\d_$]*";

		Pattern pattern = Pattern.compile(patternToSearch);

		Matcher matcher = pattern.matcher(declStatement);
		if (matcher.find()) {
			// System.out.println("matcher group:"+matcher.group());
			return matcher.group().replaceFirst(varDeclWithEscapeSpecial, "");
			// String matchedIdentifier = matcher.group().substring(0,
			// matcher.group().length()-2);
			// System.out.println("Matched identifier="+matchedIdentifier);
		}

		return null;
	}

	private List<String> splitMethodParameters(String methodParams) {
		int start = 0;
		List<String> decls = new ArrayList<String>();
		boolean withinGeneric = false;
		for (int end = 0; end < methodParams.length(); end++) {
			char c = methodParams.charAt(end);
			switch (c) {
			case ',':
				if (!withinGeneric) {
					decls.add(methodParams.substring(start, end));
					start = end + 1;
				}
				break;
			case '<':
				withinGeneric = true;
				break;
			case '>':
				withinGeneric = false;
				break;
			}
		}
		if (start < methodParams.length()) {
			decls.add(methodParams.substring(start));
		}
		return decls;
	}

	@Override
	public void enterLocalVariableDeclaration(
			LocalVariableDeclarationContext ctx) {

		String variableDecl = tokens.getText(ctx.type());
		String variableName = getVariableName(ctx.getText(), variableDecl);

		if (methodNeedsExtraction
				&& startIndex < ctx.getStart().getStartIndex()
				&& endIndex > ctx.getStop().getStopIndex()) {

			booleanLocalVarSkip = new ArrayList<String>();
			System.out.println("Local Variable==>"+variableName);
			if (variableDecl.equals("boolean")) {
				booleanLocalVarSkip.add(variableName);
				// System.out.println("Variable to skip ==>"+variableName);
			}
			localVartoSkip.add(variableName);
		}
		
		if (variableName != null) {
			localVarMap.put(variableName, variableDecl);
		}

	}

	@Override
	public void enterMethodBody(MethodBodyContext ctx) {

		// String methodText = tokens.getText(ctx);

		if (conditionsToBeExtractedForMethod == null) {
			for (int i = ctx.getStart().getTokenIndex(); i < ctx.getStop()
					.getTokenIndex(); i++) {

				if (tokens.get(i).getText().indexOf(BATCH_START) != -1) {
					startBatchTokens.add(i);
					methodHasBatchRule = true;
					// System.out.println("Start batch Token matched  Token no="+
					// i);
					startIndex = tokens.get(i).getStartIndex();
				} else if (tokens.get(i).getText().indexOf(EXTRACT_RULE_START) != -1) {

					// startExtractToken = i;
					startExtractTokens.add(i);
					methodNeedsExtraction = true;
					// System.out.println("start EXTRACT Token matched Token no="+
					// i);
					if (!methodHasBatchRule) {
						startIndex = tokens.get(i).getStartIndex();
					}
				} else if (tokens.get(i).getText().indexOf(EXTRACT_RULE_END) != -1) {
					// endExtractToken = i;
					endExtractTokens.add(i);
					// System.out.println("End EXTRACT Token matched  Token no="+
					// i);
					if (!methodHasBatchRule) {
						endIndex = tokens.get(i).getStopIndex();
					}
				} else if (tokens.get(i).getText().indexOf(BATCH_END) != -1) {
					// endExtractToken = i;
					endBatchTokens.add(i);
					endIndex = tokens.get(i).getStopIndex();
					// System.out.println("End batch Token matched  Token no="
					// + i);

				}

				// System.out.println("Token ("+i+")="+
				// tokens.get(i).getText());
			}
		} else {
			// TODO Assumption of not batch rule needs to be changed
			methodHasBatchRule = false;

			Map<String, List<CodePart>> codePartListForPattern = conditionsToBeExtractedForMethod
					.get(actMethodName);
			// System.out.println("Auto extraction in Enter methodbody(0)-->"+
			// codePartListForPattern);
			if (codePartListForPattern != null
					&& !codePartListForPattern.isEmpty()) {

				for (Entry<String, List<CodePart>> codeParts : codePartListForPattern
						.entrySet()) {

					// System.out.println("Auto extraction in Enter methodbody-->"+
					// actMethodName);
					methodNeedsExtraction = true;
					String ruleid = codeParts.getKey();
					if (LogUtil.isDebugEnabled(LOG_NAME)) {
						LogUtil.debug(CLASS_NAME, "enterMethodBody",
								"methodname==>" + actMethodName, LOG_NAME);
						LogUtil.debug(CLASS_NAME, "enterMethodBody",
								"Rule id-->" + ruleid, LOG_NAME);
					}

					// TODO to check for redundant/Subset indexes
					for (CodePart codePart : codeParts.getValue()) {
						startExtractTokens.add(codePart.getStartIndex());
						endExtractTokens.add(codePart.getEndIndex());
						ruleIDList.add(ruleid);
					}

				}
			}

		}

	}

	@Override
	public void exitMethodBody(MethodBodyContext ctx) {
		if (methodNeedsExtraction) {

			Map<String, Object> ruleMethodExtract = null;
			List<Map<String, Object>> ruleMethodExtractList = new ArrayList<Map<String, Object>>();
			String extractText = null;
			String ruleId = null;

			// Rule extraction call
			RuleExternalizer extr = new RuleExternalizer(fileSourceBaseURL,
					rootDirectory);
			if (methodHasBatchRule) {
				// System.out.println("Batch Rule Extraction");
				for (int i = 0; i < startBatchTokens.size(); i++) {
					for (int j = 0; j < startExtractTokens.size(); j++) {
						// System.out.println("Start Token==>"+startExtractTokens.get(j));
						if (startExtractTokens.get(j) > startBatchTokens.get(i)
								&& endExtractTokens.get(j) < endBatchTokens
								.get(i)) {
							ruleId = extr.getRuleId(tokens
									.get(startExtractTokens.get(j)));

							extractText = tokens.getText(
									tokens.get(startExtractTokens.get(j)),
									tokens.get(endExtractTokens.get(j)));
							// System.out.println("Extracted Text:" +
							// extractText);
							// System.out.println("Imports:"+importValuesMap.get(j));
							// System.out.println("Parameters:"+functionParamKeysMap.get(j));
							ruleMethodExtract = new HashMap<String, Object>();
							ruleMethodExtract.put("extractText", extractText);
							ruleMethodExtract.put("importsList",
									importValuesMap.get(j));
							ruleMethodExtract.put("ruleContextParams",
									functionParamKeysMap.get(j));
							ruleMethodExtract.put("ruleId", ruleId);
							ruleMethodExtract.put("returnBoolean",
									returnBoolean);
							// System.out.println("RuleID==>"+ruleMethodExtract.get("ruleId"));
							ruleMethodExtractList.add(ruleMethodExtract);

						}
					}
					// System.out.println("list Size ===>"+ruleMethodExtractList.size());
					String invokeCode = extr
							.externalizeBatchRule(ruleMethodExtractList,actClassName,ruleRepVo);
					// System.out.println("Batch Invoke Code==>"+invokeCode);
					rewriter.replace(startBatchTokens.get(i),
							endBatchTokens.get(i), invokeCode);
				}
			} else {
				// System.out.println("Simple Rule");
				for (int i = 0; i < startExtractTokens.size(); i++) {
					extractText = tokens.getText(
							tokens.get(startExtractTokens.get(i)),
							tokens.get(endExtractTokens.get(i)));
					if (LogUtil.isDebugEnabled(LOG_NAME)) {
						LogUtil.debug(CLASS_NAME, "exitMethodBody",
								"ClassName==>" + actClassName, LOG_NAME);
						LogUtil.debug(CLASS_NAME, "exitMethodBody",
								"MethodName==>" + actMethodName, LOG_NAME);
						LogUtil.debug(CLASS_NAME, "exitMethodBody",
								"Rule id List==>" + ruleIDList, LOG_NAME);
					}

					// TODO To change this hard coding
					if (conditionsToBeExtractedForMethod != null) {
						ruleId = ruleIDList.get(i);
						// "RULE_SUD_"+ counter++;
					} else {
						ruleId = extr.getRuleId(tokens.get(startExtractTokens
								.get(i)));
					}
					// TODO HotFix need to verify startExtracttoken with
					// ctx.start range
					// Added for method overloading issue
					//if (importValuesMap.get(i) != null) {
						System.out.println("Extracted Text:" + extractText);
						System.out.println("Imports:" + importValuesMap.get(i));
						System.out.println("Parameters:"
								+ functionParamKeysMap.get(i));

						if (LogUtil.isDebugEnabled(LOG_NAME)) {
							LogUtil.debug(CLASS_NAME, "exitMethodBody",
									"Rule Id-->" + ruleId, LOG_NAME);
							LogUtil.debug(CLASS_NAME, "exitMethodBody",
									"Local methods List for the code block-->"
											+ localCallsInCodeExtract.get(i),
											LOG_NAME);
						}

						String invokeCode = extr.externalizeRule(extractText,
								importValuesMap.get(i),
								functionParamKeysMap.get(i), ruleId,
								returnBoolean, actClassName,ruleRepVo);

						// Invoke rule pojo change
						// System.out.println("invoke code==>"+invokeCode);
						rewriter.replace(startExtractTokens.get(i),
								endExtractTokens.get(i), invokeCode);
						// System.out.println("rewriter"+rewriter.getText());
					//} 
					if (LogUtil.isDebugEnabled(LOG_NAME)) {
						LogUtil.debug(CLASS_NAME, "exitMethodBody",
								"ClassName==>" + actClassName, LOG_NAME);
						LogUtil.debug(CLASS_NAME, "exitMethodBody",
								"Ruleid==>" + ruleId, LOG_NAME);

						LogUtil.debug(CLASS_NAME, "exitMethodBody",
								"Index==>" + i, LOG_NAME);
					
					}
				}
			}

			returnBoolean = null;
		}

	}

	@Override
	public void enterParExpression(ParExpressionContext ctx) {

	}

	@Override
	public void enterStatement(StatementContext ctx) {
	}

	@Override
	public void enterStatementExpression(StatementExpressionContext ctx) {
		if (methodNeedsExtraction) {
			// System.out.println("Expression -->"+ctx.getText());
			for (int i = 0; i < startExtractTokens.size(); i++) {

				if (ctx.start.getTokenIndex() >= startExtractTokens.get(i)
						&& ctx.stop.getTokenIndex() <= endExtractTokens.get(i)) {

					String methodName = extractSelfMethodCall(ctx.getText());
					if (methodName != null) {
						String methodNameWithClassWithPkg = null;

						if (actPkgName != null) {
							methodNameWithClassWithPkg = actPkgName + "."
									+ actClassName + "." + methodName;
						} else {
							methodNameWithClassWithPkg = actClassName + "."
									+ methodName;
						}
						if (LogUtil.isDebugEnabled(LOG_NAME)) {
							LogUtil.debug(CLASS_NAME, "extractSelfMethodCall",
									"Internal Method==>" + methodName, LOG_NAME);
						}
						System.out.println("Internal Method==>"
								+ methodNameWithClassWithPkg);
						fillValuesInMap(i, methodNameWithClassWithPkg,
								localCallsInCodeExtract);
					}

					// break;
				}
			}
		}
	}

	@Override
	public void enterExpression(ExpressionContext ctx) {

		if (methodNeedsExtraction) {
			 //System.out.println("Expression -->"+ctx.getText());
			for (int i = 0; i < startExtractTokens.size(); i++) {

				if (ctx.start.getTokenIndex() >= startExtractTokens.get(i)
						&& ctx.stop.getTokenIndex() <= endExtractTokens.get(i)) {

					//System.out.println("Expression(2):" + ctx.getText());
					String identifierName = ctx.getText();
					if (classVarMap.containsKey(identifierName)) {

						String classVarType = classVarMap.get(identifierName);
						 System.out.println("Found in ClassVarMap:" +
						 identifierName+" :"+classVarType);
						fillParametersInMap(i, identifierName, classVarType);
						if (importMap.containsKey(classVarType)) {
							fillValuesInMap(i, importMap.get(classVarType),
									importValuesMap);
						}

						if (returnBoolean == null)
							returnBoolean = identifyReturnBoolean(classVarType,
									identifierName);

					}

					else if (localVarMap.containsKey(identifierName)) {

						String localVarType = localVarMap.get(identifierName);
						 System.out.println("Found in localVarMap:" +
						 identifierName+" :"+localVarType);
						//to skip localvar inside extract block
						if(!localVartoSkip.contains(identifierName))
						{
							fillParametersInMap(i, identifierName, localVarType);
						}
						if (importMap.containsKey(localVarType)) {
							fillValuesInMap(i, importMap.get(localVarType),
									importValuesMap);
						}
						if (returnBoolean == null)
							returnBoolean = identifyReturnBoolean(localVarType,
									identifierName);
					} else if (importMap.containsKey(identifierName)) {
						// System.out.println("Found in importMap:" +
						// identifierName);
						fillValuesInMap(i, importMap.get(identifierName),
								importValuesMap);
					}

					// break;
				}
			}
		}

	}

	private String identifyReturnBoolean(String varType, String identifierName) {
		if (varType.equals("boolean")
				&& !booleanLocalVarSkip.contains(identifierName))
			return identifierName;
		return null;
	}

	private void fillValuesInMap(Integer sNo, String value,
			Map<Integer, List<String>> stringListForSno) {

		if (stringListForSno.containsKey(sNo)) {

			List<String> importList = stringListForSno.get(sNo);
			if (!importList.contains(value)) {
				importList.add(value);
				stringListForSno.put(sNo, importList);
			}
		} else {
			List<String> newList = new ArrayList<String>();
			newList.add(value);
			stringListForSno.put(sNo, newList);
		}
	}

	private void fillParametersInMap(Integer sNo, String paramType,
			String paramName) {

		if (functionParamKeysMap.containsKey(sNo)) {

			Map<String, String> paramMap = functionParamKeysMap.get(sNo);
			if (!paramMap.containsKey(paramType)) {
				paramMap.put(paramType, paramName);
				functionParamKeysMap.put(sNo, paramMap);
			}

		} else {
			Map<String, String> newMap = new HashMap<String, String>();
			newMap.put(paramType, paramName);
			functionParamKeysMap.put(sNo, newMap);
		}
	}

	@Override
	public void exitBlockStatement(BlockStatementContext ctx) {

	}

	@Override
	public void enterCompilationUnit(CompilationUnitContext ctx) {
		// System.out.println(ctx.getText());
	}

	@Override
	public void exitCompilationUnit(CompilationUnitContext ctx) {

	}

	@Override
	public void exitImportDeclaration(ImportDeclarationContext ctx) {

	}

	private String extractSelfMethodCall(String stmt) {
		// System.out.println("stmt-->"+stmt);
		String temp = stmt;
		if (stmt.contains("=")) {
			int index = stmt.indexOf("=");
			temp = stmt.substring(index + 1);
		}
		// System.out.println("self temp "+temp);
		String PATTERN = "[a-zA-Z0-9_]+\\(([a-zA-Z0-9\"\'\\.\\(\\)_\\s+])*([,a-zA-Z0-9\"\'\\.\\(\\)\\[\\]\\{\\}_\\s+])*\\)";
		Pattern pat = Pattern.compile(PATTERN);
		Matcher m = pat.matcher(temp);

		if (m.find()) {

			String matchMethod = m.group();

			int index = matchMethod.indexOf("(");
			// System.out.println("Private method call--> " +
			// temp.substring(0,index));
			return matchMethod.substring(0, index).trim();

		}
		return null;
	}

}
