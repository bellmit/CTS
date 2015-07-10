package com.cognizant.rif.exceptions;

public class PatternNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String msg;


	public PatternNotFoundException(String msg) {
		super();
		this.msg = msg;
	}
	

}
