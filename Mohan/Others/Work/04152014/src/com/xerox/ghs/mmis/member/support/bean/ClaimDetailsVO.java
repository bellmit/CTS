package com.xerox.ghs.mmis.member.support.bean;

import java.util.Date;

/**
 * 
 * This class holds the value objects for claim details of a member 
 * 
 *
 */
public class ClaimDetailsVO {
	
	private Date beginDate;
	private Date endDate;
	private String memberName;
	private String provider;
	private String claimNumber;
	private String status;
	private String claimPaidOn;
	private String submittedCharges;
	
	
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
	
	public String getClaimPaidOn() {
		return claimPaidOn;
	}
	public void setClaimPaidOn(String claimPaidOn) {
		this.claimPaidOn = claimPaidOn;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSubmittedCharges() {
		return submittedCharges;
	}
	public void setSubmittedCharges(String submittedCharges) {
		this.submittedCharges = submittedCharges;
	}
	
	

}
