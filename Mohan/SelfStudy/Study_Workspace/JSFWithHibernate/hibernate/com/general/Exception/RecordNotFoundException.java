package com.general.Exception;

public class RecordNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5517037279574829674L;
	
	private String message;

	public RecordNotFoundException(String string) {
		// TODO Auto-generated constructor stub
		this.message=string;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		
		return "Record Not found in Table "+message;
	}
	
}
