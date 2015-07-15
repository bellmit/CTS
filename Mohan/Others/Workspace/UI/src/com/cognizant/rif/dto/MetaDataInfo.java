package com.cognizant.rif.dto;

public class MetaDataInfo {
	private String ruleID;
	private String ruleName;
	private int type;
	private String implementationClass;
	private String startDate;
	private String endDate;
	private String mitaMapping;	
	private int redThresholdInMilliSeconds;
	private int amberThresholdInMilliSeconds;
    
	public String getRuleID() {
		return ruleID;
	}
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getImplementationClass() {
		return implementationClass;
	}
	public void setImplementationClass(String implementationClass) {
		this.implementationClass = implementationClass;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMitaMapping() {
		return mitaMapping;
	}
	public void setMitaMapping(String mitaMapping) {
		this.mitaMapping = mitaMapping;
	}
	public int getRedThresholdInMilliSeconds() {
		return redThresholdInMilliSeconds;
	}
	public void setRedThresholdInMilliSeconds(int redThresholdInMilliSeconds) {
		this.redThresholdInMilliSeconds = redThresholdInMilliSeconds;
	}
	public int getAmberThresholdInMilliSeconds() {
		return amberThresholdInMilliSeconds;
	}
	public void setAmberThresholdInMilliSeconds(int amberThresholdInMilliSeconds) {
		this.amberThresholdInMilliSeconds = amberThresholdInMilliSeconds;
	}
}
