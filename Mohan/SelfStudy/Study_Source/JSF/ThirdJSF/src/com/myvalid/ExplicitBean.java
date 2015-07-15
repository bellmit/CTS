package com.myvalid;


public class ExplicitBean {
	private Integer inputNumber;
	
	public Integer getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(Integer inputNumber) {
		this.inputNumber = inputNumber;
	}

	public String processRequest() {
		return "success";
	}
	
}
