package com.xerox.ghs.mmis.member.support.bean;

/**
 * 
 * This class holds the value objects for member claims
 * displayed in member landing page
 *
 */
public class MemberClaim {

	
	
	private String fromDate;
	private String toDate;
	private String providerName;	
	private String claimType;
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	
	
	
	

}

