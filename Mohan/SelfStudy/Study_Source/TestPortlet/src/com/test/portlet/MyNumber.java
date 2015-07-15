package com.test.portlet;

import java.io.Serializable;

public class MyNumber implements Serializable {	
	private static final long serialVersionUID = -782884720297392929L;
	private Integer myNumber;
	
	public MyNumber(Integer myNumber) {
		super();
		this.myNumber = myNumber;
	}

	public Integer getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(Integer myNumber) {
		this.myNumber = myNumber;
	}		
}
