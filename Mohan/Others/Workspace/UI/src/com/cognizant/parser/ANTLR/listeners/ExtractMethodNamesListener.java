package com.cognizant.parser.ANTLR.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
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

public class ExtractMethodNamesListener extends JavaBaseListener {

	public TokenStreamRewriter rewriter;
	BufferedTokenStream tokens;

	Map<String, String> importMap = new HashMap<String, String>();
	Map<String, String> classVarMap = new HashMap<String, String>();
	Map<String, String> localVarMap = new HashMap<String, String>();
	List<String> importValues;
	Map<Integer, Map<String, String>> functionParamKeysMap;
	private boolean methodNeedsExtraction;
	private StringBuffer methodContent;
	private String methodName;

	public ExtractMethodNamesListener(CommonTokenStream tokens,
			String methodName, StringBuffer methodContent,
			List<String> methodImports) {
		this.tokens = tokens;
		rewriter = new TokenStreamRewriter(tokens);
		this.methodContent = methodContent;
		this.methodName = methodName;
		this.importValues = methodImports;
	}

	@Override
	public void enterImportDeclaration(ImportDeclarationContext ctx) {
		// System.out.println("Imorts declaration:"+ctx.getText());
		String importKey = tokens.get(ctx.stop.getTokenIndex() - 1).getText();
		String importValue = ctx.getText().substring(6,
				(ctx.getText().length()) - 1);
		importMap.put(importKey, importValue);
	}

	@Override
	public void enterClassDeclaration(ClassDeclarationContext ctx) {
		System.out.println("Class name :::::::::::::::::::::::::"
				+ ctx.Identifier().getText());
	}

	@Override
	public void exitClassDeclaration(ClassDeclarationContext ctx) {

	}

	@Override
	public void enterPackageDeclaration(
			@NotNull JavaParser.PackageDeclarationContext ctx) {
	}

	@Override
	public void enterClassBody(JavaParser.ClassBodyContext ctx) {

	}

	@Override
	public void enterMethodDeclaration(MethodDeclarationContext ctx) {
		functionParamKeysMap = new HashMap<Integer, Map<String, String>>();
		methodNeedsExtraction = false;

		String methodblock = rewriter.getText().substring(
				ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1);
		// System.out.println("Method Declaration==>"+methodblock);
		// System.out.println(" +++++ Method name :::"+actMethodName);
		if (ctx.Identifier().getText().equalsIgnoreCase(methodName)) {
			methodContent.append(methodblock);
			System.out.println("Method Content==>" + methodContent);
			methodNeedsExtraction = true;
		}
		if (ctx.formalParameters().formalParameterDecls() != null) {

			String methodParams = tokens.getText(ctx.formalParameters()
					.formalParameterDecls());
			List<String> decls = splitMethodParameters(methodParams);
			for (String methodParam : decls) {
				// System.out.println("param "+param);
				methodParam = methodParam.trim();
				int index = methodParam.lastIndexOf(" ");
				String varType = methodParam.substring(0, index);
				varType = varType.replace("final", "").trim();
				String varName = methodParam.substring(index + 1).trim();

				localVarMap.put(varName, varType);
			}
		}
	}

	@Override
	public void enterFieldDeclaration(FieldDeclarationContext ctx) {
		System.out.println("Field declaration:" + ctx.getText());

		String variableDecl = tokens.getText(ctx.type());
		System.out.println("variableDecl:" + variableDecl);

		String variableName = getVariableName(ctx.getText(), variableDecl);
		System.out.println("Variable name :" + variableName);
		if (variableName != null) {
			classVarMap.put(variableName, variableDecl);
		}

	}

	@Override
	public void enterExpression(ExpressionContext ctx) {

		if (methodNeedsExtraction) {
			// System.out.println("Expression -->"+ctx.getText());
			String identifierName = ctx.getText();
			if (classVarMap.containsKey(identifierName)) {

				String classVarType = classVarMap.get(identifierName);
				System.out.println("Found in ClassVarMap:" + identifierName
						+ " :" + classVarType);
				if (importMap.containsKey(classVarType)) {
					fillImportsInMap(importMap.get(classVarType));
				}

			}

			else if (localVarMap.containsKey(identifierName)) {

				String localVarType = localVarMap.get(identifierName);
				System.out.println("Found in localVarMap:" + identifierName
						+ " :" + localVarType);
				if (importMap.containsKey(localVarType)) {
					fillImportsInMap(importMap.get(localVarType));
				}
			} else if (importMap.containsKey(identifierName)) {
				System.out.println("Found in importMap:" + identifierName);
				fillImportsInMap(importMap.get(identifierName));
			}

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

		if (variableName != null) {
			localVarMap.put(variableName, variableDecl);
		}

	}

	@Override
	public void enterMethodBody(MethodBodyContext ctx) {

	}

	@Override
	public void exitMethodBody(MethodBodyContext ctx) {
		methodNeedsExtraction = false;
		// System.out.println("import values==>"+importValues);
	}

	@Override
	public void enterParExpression(ParExpressionContext ctx) {

	}

	@Override
	public void enterStatement(StatementContext ctx) {

	}

	@Override
	public void exitBlockStatement(BlockStatementContext ctx) {

	}

	@Override
	public void enterCompilationUnit(CompilationUnitContext ctx) {
		// System.out.println(ctx.getText());
	}

	@Override
	public void exitImportDeclaration(ImportDeclarationContext ctx) {

	}

	@Override
	public void enterStatementExpression(
			@NotNull JavaParser.StatementExpressionContext ctx) {

	}

	private void fillImportsInMap(String importName) {
		if (importValues == null) {
			importValues = new ArrayList<String>();
		}

		if (!importValues.contains(importName)) {
			importValues.add(importName);
		}

	}
}
