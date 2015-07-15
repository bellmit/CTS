package com.cognizant.rif.exceptions;

public class FormatterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String msg;


	public FormatterException(String msg) {
		super();
		this.msg = msg;
	}
	
	

}
