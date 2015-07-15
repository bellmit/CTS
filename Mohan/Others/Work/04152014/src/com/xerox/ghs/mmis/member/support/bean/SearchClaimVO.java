package com.xerox.ghs.mmis.member.support.bean;

import java.text.ParseException;
import java.util.Date;

/**
 * 
 * This class holds the value objects for 
 * searching claim details of a member
 *
 */
public class SearchClaimVO {
	
	private Date beginDate;
	private Date endDate;
	private String claimType;
	private String strFromDate;
	private String strToDate;
	private String firstName;
	private String lastName;
	private String claimStatus;
	private String claimAmount;
	
	
	
	public String getStrFromDate() {
		return strFromDate;
	}
	public void setStrFromDate(String strFromDate) throws ParseException {
		this.strFromDate = strFromDate;
		
		if (strFromDate != "")
        {
           this.beginDate = new Date(strFromDate);
          
       }
		
	}
	public String getStrToDate() {
		return strToDate;
	}
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
		if (strToDate != null)
        {
           this.endDate = new Date(strToDate);
       }
		
		
	}
	public Date getBeginDate() {
		
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {

		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}
	
	

}
