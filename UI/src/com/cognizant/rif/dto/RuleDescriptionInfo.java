package com.cognizant.rif.dto;

import java.util.List;

public class RuleDescriptionInfo {
	
	private String ruleID;
	private String className;
	private String packageName;
	private List<String> internalMethodtoExtract;
	private List<String> externalMethodtoExtract;
	private MetaDataInfo metaDataInfo;
	
	public String getRuleID() {
		return ruleID;
	}
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public List<String> getInternalMethodtoExtract() {
		return internalMethodtoExtract;
	}
	public void setInternalMethodtoExtract(List<String> internalMethodtoExtract) {
		this.internalMethodtoExtract = internalMethodtoExtract;
	}
	public List<String> getExternalMethodtoExtract() {
		return externalMethodtoExtract;
	}
	public void setExternalMethodtoExtract(List<String> externalMethodtoExtract) {
		this.externalMethodtoExtract = externalMethodtoExtract;
	}
	public MetaDataInfo getMetaDataInfo() {
		return metaDataInfo;
	}
	public void setMetaDataInfo(MetaDataInfo metaDataInfo) {
		this.metaDataInfo = metaDataInfo;
	}

}
