package com.cognizant.rif.dto;

public class TokenDelimiter {
	
	int startIndex;
	
	int endIndex;

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public TokenDelimiter(int startIndex, int endIndex) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	
	/*
	 *  Returns 0 if destination TokenIndexes are subset of this
	 *  Returns 1 if destination TokenIndexes are superset of this
	 *  Returns 2 if they are equal
	 *  otherwise -1
	 */
	public int compareIndexes (TokenDelimiter dest) {
		
		
		if((this.startIndex < dest.startIndex) && (this.endIndex > dest.endIndex)) {
			return 0;
		} 
		
		
		if((this.startIndex > dest.startIndex) && (this.endIndex < dest.endIndex)) {
			return 1;
		}
		
		if((this.startIndex == dest.startIndex) && (this.endIndex == dest.endIndex)) {
			return 2;
		} 
		
		return -1;
	}
	

}
