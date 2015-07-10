package com.cognizant.rif.dto;

public class CodePart extends TokenDelimiter {

	String blockText;
	boolean isElseIf;
	
	String readableText;
	int lineNumber;
	
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getReadableText() {
		return readableText;
	}

	public void setReadableText(String readableText) {
		this.readableText = readableText;
	}

	public CodePart(int startIndex, int endIndex, String blockText,
			boolean isElseIf, int lineNumber) {
		super(startIndex, endIndex);
		this.blockText = blockText;
		this.isElseIf = isElseIf;
		this.lineNumber = lineNumber;
	}

	public String getBlockText() {
		return blockText;
	}

	public void setBlockText(String blockText) {
		this.blockText = blockText;
	}

	public boolean isElseIf() {
		return isElseIf;
	}

	public void setElseIf(boolean isElseIf) {
		this.isElseIf = isElseIf;
		
	}

	@Override
	public String toString() {
		return "CodePart [blockText(Line Number="+lineNumber+"\n" + readableText + "\n, isElseIf=" + isElseIf
				+ ", startIndex=" + startIndex + ", endIndex=" + endIndex + "]";
	}
	
	
	
	
	
	
}
