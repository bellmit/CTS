package com.myvalid;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


public class CustomBean {
	private Integer inputNumber;
	private Integer inputNumber2;
	
	public Integer getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(Integer inputNumber) {
		System.err.println("SETTER CALLED");
		this.inputNumber = inputNumber;
	}

	public Integer getInputNumber2() {
		return inputNumber2;
	}

	public void setInputNumber2(Integer inputNumber2) {
		this.inputNumber2 = inputNumber2;
	}

	public String processRequest() {
		return "success";
	}
	
	public void someValidate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		Integer integer=(Integer)object;
		if(integer.intValue()>100) {
			throw new ValidatorException(new FacesMessage("Too Much 2"));			 
		}		 
	}
}
