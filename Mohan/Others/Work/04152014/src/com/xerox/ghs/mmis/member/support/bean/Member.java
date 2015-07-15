package com.xerox.ghs.mmis.member.support.bean;

/**
 * Member Details
 */
public class Member {
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	
	private	String	memberId;
	private	String	dob;
	private	String	firstName;
	private	String	lastName;
	private	String	gender;
	private	String	serviceFrom;
	private	String	serviceTo;

}
