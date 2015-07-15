package com.xerox.ghs.mmis.provider.model;

/**
 * This Class hold the search criteria for Check Member Eligibility
 *  
 */

public class MemberSearch {

	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getServiceFrom() {
		return serviceFrom;
	}
	public void setServiceFrom(String serviceFrom) {
		this.serviceFrom = serviceFrom;
	}
	public String getServiceTo() {
		return serviceTo;
	}
	public void setServiceTo(String serviceTo) {
		this.serviceTo = serviceTo;
	}
	private	String	claimType;
	private	String	dob;
	private	String	firstName;
	private	String	lastName;
	private	String	memberId;
	private	String	serviceFrom;
	private	String	serviceTo;
	
	
}
