package com.xerox.ghs.mmis.reference.model;

import java.io.Serializable;

public class ReferenceServiceVO implements Serializable{
	
	
	private String validValueCode;
	private String longDescription;
	private String shortDescription;
	
	public String getValidValueCode() {
		return validValueCode;
	}
	public void setValidValueCode(String validValueCode) {
		this.validValueCode = validValueCode;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	@Override
	public String toString() {
		return "{\"validValueCode\"=" + validValueCode
				+ ", \"longDescription\"=" + longDescription
				+ ", \"shortDescription\"=" + shortDescription + "\"}";
	}
	
	
	 

}
