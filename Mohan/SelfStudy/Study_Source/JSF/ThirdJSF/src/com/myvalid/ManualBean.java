package com.myvalid;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ManualBean {
	private String inputNumber;
	private List<String> errorMessages=new ArrayList<String>();
	
	public String getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(String inputNumber) {		
		this.inputNumber = inputNumber;
	}
	
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public String processRequest() {
		if(getInputNumber()==null || getInputNumber().length()==0) {
			errorMessages.add("<h3 style='color:red'>Input Number Required</h3>");
		}
		if(errorMessages.size()>0) {
			return null;
		} else {
			return "success";
		}
	}
	
	public String processRequest2() {

		if(getInputNumber()==null || getInputNumber().length()==0) {
			FacesMessage facesMessage=new FacesMessage("Input Number Required");
			FacesContext.getCurrentInstance().addMessage("form1:inputNumber3", facesMessage);			
			return null;
		} else {
			return "success";
		}	
	}
}
