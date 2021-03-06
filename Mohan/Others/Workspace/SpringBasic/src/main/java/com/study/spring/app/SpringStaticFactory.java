package com.study.spring.app;

public class SpringStaticFactory {

	static SpringStaticFactory staticFactory=new SpringStaticFactory();
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static SpringStaticFactory getInstance()
	{
		System.out.println("Factory method call");
		return staticFactory;
	}

	public String printMessage()
	{
		return "staticFactory Method :"+message;
	}
}
