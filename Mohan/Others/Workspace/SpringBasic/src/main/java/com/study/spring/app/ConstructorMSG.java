package com.study.spring.app;

public class ConstructorMSG {
	
	String message;
	int messageCode;

	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}

	public ConstructorMSG(String message) {
		this.message=message;
		
	}

	public ConstructorMSG(String message,int messageCode) {
		this.message=message;
		this.messageCode=messageCode;
		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String printMessage()
	{
		return "Constructor Injected Message:"+message;
	}
	
	public String printMessages()
	{
		return "Constructor Injected Message:"+message+":"+messageCode;
	}

}
