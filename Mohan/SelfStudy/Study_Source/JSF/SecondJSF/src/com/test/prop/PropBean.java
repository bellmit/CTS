package com.test.prop;

import java.util.Date;

import javax.faces.context.FacesContext;

public class PropBean {
	private String userName;
	private Date currentDate;
	public String getUserName() {
		if(userName==null) {
			userName="Prop Bean User";
		}
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCurrentDate() {		
		if(currentDate==null) {
			currentDate=new Date();
		}
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}	
}
