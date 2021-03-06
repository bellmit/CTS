package com.cognizant.parser.ANTLR.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.misc.NotNull;

import com.cognizant.javagrammar.JavaBaseListener;
import com.cognizant.javagrammar.JavaParser;
import com.cognizant.javagrammar.JavaParser.BlockStatementContext;
import com.cognizant.javagrammar.JavaParser.ClassBodyDeclarationContext;
import com.cognizant.javagrammar.JavaParser.ClassDeclarationContext;
import com.cognizant.javagrammar.JavaParser.CompilationUnitContext;
import com.cognizant.javagrammar.JavaParser.ImportDeclarationContext;
import com.cognizant.javagrammar.JavaParser.MethodBodyContext;
import com.cognizant.javagrammar.JavaParser.MethodDeclarationContext;
import com.cognizant.javagrammar.JavaParser.ParExpressionContext;
import com.cognizant.javagrammar.JavaParser.StatementContext;
import com.cognizant.rif.common.logging.LogUtil;
import com.cognizant.rif.dto.CodePart;
import com.cognizant.rif.exceptions.PatternNotFoundException;
import com.cognizant.rif.utilities.CommonUtils;

public class ExtractKeywordListener extends JavaBaseListener {

	/**
	 * Class level variable that contains the name of the class
	 */
	private static final String CLASS_NAME = "ExtractKeywordListener";

	/**
	 * Class level variable that contains the logger name
	 */
	private static final String LOG_NAME = "EXTRACT_LOG";
	private int count = 0;

	// Stream to write invocation code in POJO
	private TokenStreamRewriter rewriter;
	BufferedTokenStream tokens;

	String nestedIf;
	File myFile = null;
	List<String> methodNameList;
	List<String> searchPatterns;

	BufferedWriter conditionWriter;
	BufferedWriter patternMatchWriter;
	StringBuffer conditionText;
	StringBuffer patternText;
	boolean patternMatchedInMethod;
	Map<String, Boolean> patternMatchFlagsForMethod;

	Map<String, CodePart> lastMatchingIfForPattern;
	Map<String, List<CodePart>> allMatchingIfStmtsForPattern;
	Map<String, List<CodePart>> nearestMatchingIfsForPattern;
	Map<String, Map<String, List<CodePart>>> nearestMatchingsIfsForMethod;

	String actMethodName = null;
	String actMethodFrMap = null;
	String actClassName = null;
	String ruleID = null;
	Map<String, String> messageMap = new HashMap<String, String>();
	// Javadoc match Map
	Map<Integer, String> matchedindex = new HashMap<Integer, String>();
	Pattern pattern;

	private String className;
	public boolean ruleIdentified = false;

	public ExtractKeywordListener(
			BufferedTokenStream tokens,
			List<String> searchPatterns,
			BufferedWriter conditionWriter,
			BufferedWriter patternMatchWriter,
			Map<String, String> messageMap,
			Map<String, Map<String, List<CodePart>>> nearestMatchingsIfsForMethod) {
		this.tokens = tokens;
		rewriter = new TokenStreamRewriter(tokens);
		this.searchPatterns = searchPatterns;
		this.conditionWriter = conditionWriter;
		this.patternMatchWriter = patternMatchWriter;
		this.messageMap = messageMap;
		this.nearestMatchingsIfsForMethod = nearestMatchingsIfsForMethod;

	}

	public ExtractKeywordListener(
			BufferedTokenStream tokens,
			List<String> searchPatterns,
			BufferedWriter conditionWriter,
			BufferedWriter patternMatchWriter,
			Map<String, String> messageMap,
			Map<String, Map<String, List<CodePart>>> nearestMatchingsIfsForMethod,
			String ruleID) {
		this.tokens = tokens;
		rewriter = new TokenStreamRewriter(tokens);
		this.searchPatterns = searchPatterns;
		this.conditionWriter = conditionWriter;
		this.patternMatchWriter = patternMatchWriter;
		this.messageMap = messageMap;
		this.nearestMatchingsIfsForMethod = nearestMatchingsIfsForMethod;
		this.ruleID = ruleID;
	}

	@Override
	public void enterClassDeclaration(ClassDeclarationContext ctx) {

		boolean patternFound = false;
		conditionText = new StringBuffer();
		patternText = new StringBuffer();
		// System.out.println("Class Data ==>"+tokens.getText());
		if (ctx == null || ctx.Identifier() == null) {
			return;
		}
		// System.out.println("Class name==>"+ ctx.Identifier().getText());
		if (LogUtil.isDebugEnabled(LOG_NAME)) {
			LogUtil.debug(CLASS_NAME, "", "Class name==>"
					+ ctx.Identifier().getText(), LOG_NAME);
		}
		// getJavaDocMatch();

		className = ctx.Identifier().getText();

		if (className.toLowerCase().indexOf("processfacade") != -1
				|| className.toLowerCase().indexOf("processdelegate") != -1) {
			conditionText
			.append("\nProcessCalls References are found. Class : "
					+ ctx.Identifier().getText() + "\n");
			patternText.append("\nProcessCalls References are found. Class : "
					+ ctx.Identifier().getText() + "\n");
		}

		conditionText.append("\n Class name-->" + ctx.Identifier().getText()
				+ "\n");

		if (searchPatterns != null) {

			for (String searchKeyword : searchPatterns) {

				String actualPropertyValue = "";
				if (messageMap != null && messageMap.get(searchKeyword) != null) {
					actualPropertyValue = " [" + messageMap.get(searchKeyword)
							+ "] ";
				}
				// System.out.println("SearchKeyword ==> "+searchKeyword+" = "+rewriter.getText().toUpperCase().contains(searchKeyword.toUpperCase()));
				if (searchKeyword != null) {
					try {
						String keywordWOQuote = Pattern.quote(searchKeyword);
						pattern = Pattern.compile(keywordWOQuote,
								Pattern.CASE_INSENSITIVE);
						System.out.println(pattern.compile(keywordWOQuote));
						// Need to add pattern for finding keyword
						// if(rewriter.getText().indexOf(searchKeyword)!=-1) {
						if (pattern.matcher(rewriter.getText()).find()) {
							conditionText
							.append("Pattern Matched for searchPattern-->"
									+ searchKeyword
									+ actualPropertyValue + "\n");
							actClassName = ctx.Identifier().getText();
							patternText.append("\n Class name-->" + className
									+ "\n");
							patternText
							.append("Pattern Matched for searchPattern-->"
									+ searchKeyword
									+ actualPropertyValue + "\n");

							// System.out.println("Pattern Matched for searchPattern=="+searchKeyword+actualPropertyValue);
							if (LogUtil.isDebugEnabled(LOG_NAME)) {
								LogUtil.debug(CLASS_NAME,
										"enterClassDeclaration",
										"Pattern Matched for searchPattern="
												+ searchKeyword
												+ actualPropertyValue, LOG_NAME);
							}
							patternFound = true;
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		if (!patternFound)
			throw new PatternNotFoundException("Keyword not match");
	}

	@Override
	public void exitClassDeclaration(ClassDeclarationContext ctx) {
		try {
			// To test need to remove
			if (LogUtil.isDebugEnabled(LOG_NAME)) {
				LogUtil.debug(CLASS_NAME, "exitClassDeclaration",
						"NearestMatch==>" + nearestMatchingsIfsForMethod,
						LOG_NAME);

			}

			conditionWriter.write(conditionText.toString());
			patternMatchWriter.write(patternText.toString());
			if (LogUtil.isDebugEnabled(LOG_NAME)) {
				// LogUtil.debug(CLASS_NAME,"exitClassDeclaration"
				// ,conditionWriter.toString(), LOG_NAME);
				// LogUtil.debug(CLASS_NAME,ctx.Identifier().getText(),"Skip count==>"+count,
				// LOG_NAME);
			}
		} catch (IOException ie) {
			System.out.println("exitclass" + ie.toString());
			LogUtil.error(CLASS_NAME, "exitClassDeclaration", ie.toString(),
					LOG_NAME);
			// ie.printStackTrace();
		}
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

		actMethodName = ctx.Identifier().getText();
		actMethodFrMap = ctx.Identifier().getText();
		;
		// System.out.println(" +++++ Method name :::"+actMethodName);
	}

	@Override
	public void enterMethodBody(MethodBodyContext ctx) {
		patternMatchFlagsForMethod = new HashMap<String, Boolean>();
		patternMatchedInMethod = false;

		lastMatchingIfForPattern = new HashMap<String, CodePart>();
		allMatchingIfStmtsForPattern = new HashMap<String, List<CodePart>>();
		nearestMatchingIfsForPattern = new HashMap<String, List<CodePart>>();

		if (searchPatterns != null) {
			String methodText = rewriter.getText().substring(
					ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1);

			if (methodText.toLowerCase().indexOf("processfacade") != -1
					|| methodText.toLowerCase().indexOf("processdelegate") != -1) {
				conditionText
				.append("\nProcessCalls References are found. Class : ["
						+ className
						+ "] Method :["
						+ actMethodName
						+ "]\n");
				patternText
				.append("\nProcessCalls References are found. Class : ["
						+ className
						+ "] Method :["
						+ actMethodName
						+ "]\n");
				conditionText.append("Line Number==>" + ctx.start.getLine()
						+ "\n");
				patternText.append("Line Number==>" + ctx.start.getLine()
						+ "\n");
			}

			for (String searchKeyword : searchPatterns) {
				String actualPropertyValue = "";
				if (messageMap != null && messageMap.get(searchKeyword) != null) {
					actualPropertyValue = " [" + messageMap.get(searchKeyword)
							+ "] ";
				}

				if (searchKeyword != null) {
					String keywordWOQuote = Pattern.quote(searchKeyword);
					pattern = Pattern.compile(keywordWOQuote,
							Pattern.CASE_INSENSITIVE);

					if (pattern.matcher(methodText).find()) {
						Matcher match = pattern.matcher(methodText);
						match.find();
						patternMatchedInMethod = true;
						patternMatchFlagsForMethod.put(searchKeyword, true);

						if (actClassName != null) {
							patternText.append("\nClass name==>" + actClassName
									+ "\n");
							actClassName = null;
						}
						if (actMethodName != null) {
							patternText.append("Method name==>" + actMethodName
									+ "\n");
							actMethodName = null;
						}
						patternText
						.append("Pattern Matched for searchPattern==>"
								+ searchKeyword + actualPropertyValue
								+ "\n");
					}
				}

			}
		}
	}

	@Override
	public void exitMethodBody(MethodBodyContext ctx) {
		if (patternMatchedInMethod && !nearestMatchingIfsForPattern.isEmpty()
				&& nearestMatchingsIfsForMethod != null) {

			if (nearestMatchingsIfsForMethod.containsKey(actMethodFrMap)) {
				if (LogUtil.isDebugEnabled(LOG_NAME)) {
					LogUtil.debug(CLASS_NAME, "exitMethodBody:", actMethodFrMap
							+ "==>", LOG_NAME);
				}
				nearestMatchingsIfsForMethod.get(actMethodFrMap).putAll(
						nearestMatchingIfsForPattern);
			} else {
				nearestMatchingsIfsForMethod.put(actMethodFrMap,
						nearestMatchingIfsForPattern);
			}
			patternText.append("\nNearest Matching If map\n");
			patternText.append("\n" + nearestMatchingIfsForPattern);
			actMethodFrMap = null;
		}
		if (patternMatchedInMethod && nearestMatchingIfsForPattern.isEmpty()) {
			count++;
			if (LogUtil.isDebugEnabled(LOG_NAME)) {
				LogUtil.debug(CLASS_NAME, "Statistics:", ruleID
						+ "==>POJO not generated", LOG_NAME);
			}

		}

	}

	@Override
	public void enterParExpression(ParExpressionContext ctx) {

	}

	@Override
	public void enterStatement(StatementContext ctx) {
		if (patternMatchedInMethod) {
			boolean isIfStmt = (ctx.getText().indexOf("if") == 0);

			for (String searchKeyword : searchPatterns) {

				if (patternMatchFlagsForMethod.get(searchKeyword) != null) {

					if (patternMatchFlagsForMethod.get(searchKeyword)
							.booleanValue()) {

						if (isIfStmt) {

							conditionText.append("From statement handler"
									+ "\n");
							conditionText.append("Line no==>"
									+ ctx.start.getLine() + "\n");
							conditionText.append(rewriter.getText().substring(
									ctx.start.getStartIndex(),
									ctx.stop.getStopIndex() + 1)
									+ "\n");

							String actualPropertyValue = "";
							if (messageMap != null
									&& messageMap.get(searchKeyword) != null) {
								actualPropertyValue = " ["
										+ messageMap.get(searchKeyword) + "] ";
							}

							CodePart lastMatchingCodePart = lastMatchingIfForPattern
									.get(searchKeyword);

							if (lastMatchingCodePart != null) {

								if (lastMatchingCodePart.getBlockText()
										.indexOf(ctx.getText()) == -1) {
									if (lastMatchingCodePart.isElseIf()) {
										List<CodePart> allMatchingIfStmts = allMatchingIfStmtsForPattern
												.get(searchKeyword);
										int length = allMatchingIfStmts.size();
										for (int j = length - 1; j >= 0; j--) {
											if (allMatchingIfStmts
													.get(j)
													.getBlockText()
													.indexOf(
															lastMatchingCodePart
															.getBlockText()) > 0) {
												patternText
												.append("MATCHING IFELSEIF START: "
														+ searchKeyword
														+ "\n");
												if (actClassName != null
														&& actMethodName != null) {
													patternText
													.append("\nClass name==>"
															+ actClassName
															+ "\n");
													patternText
													.append("Method name==>"
															+ actMethodName
															+ "\n");
												}
												System.out
												.println("MATCHING IF : "
														+ searchKeyword
														+ "\n");
												// System.out.println(ifConditionList.get(j));

												CommonUtils
												.addValueToTargetMap(
														ruleID,
														allMatchingIfStmts
														.get(j),
														nearestMatchingIfsForPattern);

												patternText
												.append("Line Number==>"
														+ tokens.get(
																allMatchingIfStmts
																.get(j)
																.getStartIndex())
																.getLine()
																+ "\n");
												System.out
												.println("Line Number==>"
														+ tokens.get(
																allMatchingIfStmts
																.get(j)
																.getStartIndex())
																.getLine()
																+ "\n");

												patternText
												.append(tokens.getText(
														tokens.get(allMatchingIfStmts
																.get(j)
																.getStartIndex()),
																tokens.get(allMatchingIfStmts
																		.get(j)
																		.getEndIndex()))
																		+ "\n");
												System.out
												.println(tokens.getText(
														tokens.get(allMatchingIfStmts
																.get(j)
																.getStartIndex()),
																tokens.get(allMatchingIfStmts
																		.get(j)
																		.getEndIndex()))
																		+ "\n");
												patternText
												.append("MATCHING IF END: "
														+ searchKeyword
														+ "\n");
												break;
											}
										}

									} else {

										patternText
										.append("MATCHING IF START: "
												+ searchKeyword + "\n");

										CommonUtils.addValueToTargetMap(ruleID,
												lastMatchingCodePart,
												nearestMatchingIfsForPattern);
										if (actClassName != null
												&& actMethodName != null) {
											patternText
											.append("\nClass name==>"
													+ actClassName
													+ "\n");
											patternText.append("Method name==>"
													+ actMethodName + "\n");
										}
										System.out.println("MATCHING IF : "
												+ searchKeyword + "\n");

										patternText
										.append("Line Number==>"
												+ tokens.get(
														lastMatchingCodePart
														.getStartIndex())
														.getLine()
														+ "\n");

										System.out
										.println("Line Number==>"
												+ tokens.get(
														lastMatchingCodePart
														.getStartIndex())
														.getLine()
														+ "\n");

										patternText.append(tokens.getText(
												tokens.get(lastMatchingCodePart
														.getStartIndex()),
														tokens.get(lastMatchingCodePart
																.getEndIndex()))
																+ "\n");

										System.out.println(tokens.getText(
												tokens.get(lastMatchingCodePart
														.getStartIndex()),
														tokens.get(lastMatchingCodePart
																.getEndIndex()))
																+ "\n");

										patternText.append("MATCHING IF END: "
												+ searchKeyword + "\n");
									}
									lastMatchingIfForPattern.put(searchKeyword,
											null);
								}

							}

							if (searchKeyword != null) {
								String keywordWOQuote = Pattern
										.quote(searchKeyword);
								pattern = Pattern.compile(keywordWOQuote,
										Pattern.CASE_INSENSITIVE);
								if (pattern.matcher(ctx.getText()).find()) {

									CodePart codePart = null;
									// We need to check for else closer to if
									// condition
									// TODO ( Last 3 tokens generally
									// matches..Need to double check this )
									StringBuffer strBufferToChkforElseIf = new StringBuffer();
									for (int i = 3; i >= 1; i--) {
										strBufferToChkforElseIf.append(tokens
												.get(ctx.start.getTokenIndex()
														- i).getText());
									}
									String elseIfChkStr = strBufferToChkforElseIf
											.toString().trim();
									System.out.println("elseIfChkStr-->"
											+ elseIfChkStr);

									codePart = new CodePart(
											ctx.start.getTokenIndex(),
											ctx.stop.getTokenIndex(),
											ctx.getText(), false,
											ctx.start.getLine());
									codePart.setReadableText(tokens
											.getText(ctx));

									if (elseIfChkStr.equals("else")) {

										codePart.setElseIf(true);
									} else {
										CommonUtils.addValueToTargetMap(
												searchKeyword, codePart,
												allMatchingIfStmtsForPattern);
									}

									lastMatchingIfForPattern.put(searchKeyword,
											codePart);

								}

							}
						}

						else {

							if (searchKeyword != null) {
								CodePart lastMatchingCodePart = lastMatchingIfForPattern
										.get(searchKeyword);

								if (lastMatchingCodePart != null
										&& lastMatchingCodePart.getBlockText()
										.indexOf(ctx.getText()) == -1) {

									patternText
									.append("MATCHING LAST IF START: "
											+ searchKeyword + "\n");

									CommonUtils.addValueToTargetMap(ruleID,
											lastMatchingCodePart,
											nearestMatchingIfsForPattern);
									if (actClassName != null
											&& actMethodName != null) {
										patternText.append("\nClass name==>"
												+ actClassName + "\n");
										patternText.append("Method name==>"
												+ actMethodName + "\n");
									}
									System.out.println("MATCHING IF : "
											+ searchKeyword + "\n");

									patternText.append("Line Number==>"
											+ tokens.get(
													lastMatchingCodePart
													.getStartIndex())
													.getLine() + "\n");

									System.out.println("Line Number==>"
											+ tokens.get(
													lastMatchingCodePart
													.getStartIndex())
													.getLine() + "\n");

									patternText.append(tokens.getText(tokens
											.get(lastMatchingCodePart
													.getStartIndex()), tokens
													.get(lastMatchingCodePart
															.getEndIndex()))
															+ "\n");

									System.out.println(tokens.getText(tokens
											.get(lastMatchingCodePart
													.getStartIndex()), tokens
													.get(lastMatchingCodePart
															.getEndIndex()))
															+ "\n");

									patternText.append("MATCHING IF END: "
											+ searchKeyword + "\n");

									lastMatchingIfForPattern.put(searchKeyword,
											null);

								}
							}

						}

					}
				}
			}
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

	@Override
	public void enterStatementExpression(
			@NotNull JavaParser.StatementExpressionContext ctx) {

	}

	@Override
	public void enterClassBodyDeclaration(ClassBodyDeclarationContext ctx) {

	}

	private void getJavaDocMatch() {
		// TODO Auto-generated method stub

		try {
			matchedindex = new HashMap<Integer, String>();
			// Need to modify regex Pattern as StackOverFlow Error
			String regex = "/\\*\\*([^\\*]|\\*(?!/))*?.*?\\*/";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(tokens.getText());

			// System.out.println(matcher.matches());
			if (tokens.getText() != null) {
				while (matcher.find()) { // find the next match

					if (LogUtil.isDebugEnabled(LOG_NAME)) {
						// System.out.println("(matcher.group()==>"+(matcher.group()));
						LogUtil.debug(CLASS_NAME, "Pattern==>",
								" " + matcher.group(), LOG_NAME);
					}
					for (String searchKeyword : searchPatterns) {
						if (searchKeyword != null) {
							if (matcher.group().contains(searchKeyword)) {
								/*
								 * System.out.println(
								 * "Keyword matched in Javadoc ==>" +
								 * matcher.group() + "\" starting at index " +
								 * matcher.start() + " and ending at index " +
								 * matcher.end());
								 */
								matchedindex
								.put(matcher.end(), matcher.group());
								break;
							}
						}
					}

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.toString() + "");
			if (LogUtil.isDebugEnabled(LOG_NAME)) {
				// System.out.println("Javadoc exception==>" + ex.toString());
				LogUtil.debug(CLASS_NAME, "Pattern==>",
						" " + ex.getStackTrace(), LOG_NAME);
			}
		}

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
}
