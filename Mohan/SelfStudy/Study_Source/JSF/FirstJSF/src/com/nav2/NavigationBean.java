package com.nav2;

import java.util.Calendar;

public class NavigationBean {

	private Integer inputNumber;
	
	public Integer getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(Integer inputNumber) {
		this.inputNumber = inputNumber;
	}

	public String fetchNextByInputNumber() {		
		if(this.inputNumber%2==1) {
			return "success";	
		} else {
			return "failure";
		}
	}

	public String fetchNext() {
		int minute=Calendar.getInstance().get(Calendar.MINUTE);
		if(minute%2==1) {
			return "success";	
		} else {
			return "failure";
		}
	}
}
