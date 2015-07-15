package com.study.spring.app;

public class Message {
	
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String printMessage()
	{
		return "Welcome to Spring,"+this.message;
	}

}
