package com.cognizant.rif.dto;

import java.util.List;
import java.util.Map;

public class RuleDescriptor {
	
	String ruleName;
	String rulePackageName;
	String ruleClassName;
	String ruleId;
	String ruleText;
	String ruleDescription;
	List<String> ruleMethods;
	String ruleReturnField;
	String ruleExtractClassName;
	Map<String, String> ruleContextParams;
	
	public String getRuleExtractClassName() {
		return ruleExtractClassName;
	}

	public void setRuleExtractClassName(String ruleExtractClassName) {
		this.ruleExtractClassName = ruleExtractClassName;
	}

	public String getRuleReturnField() {
		return ruleReturnField;
	}

	public void setRuleReturnField(String ruleReturnField) {
		this.ruleReturnField = ruleReturnField;
	}

	public List<String> getRuleMethods() {
		return ruleMethods;
	}

	public void setRuleMethods(List<String> ruleMethods) {
		this.ruleMethods = ruleMethods;
	}

	List<String> importsList;
	
	public String getRulePackageName() {
		return rulePackageName;
	}

	public void setRulePackageName(String rulePackageName) {
		this.rulePackageName = rulePackageName;
	}
	
	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleClassName() {
		return ruleClassName;
	}

	public void setRuleClassName(String ruleClassName) {
		this.ruleClassName = ruleClassName;
	}

	public List<String> getImportsList() {
		return importsList;
	}

	public void setImportsList(List<String> importsList) {
		this.importsList = importsList;
	}

	public Map<String, String> getRuleContextParams() {
		return ruleContextParams;
	}

	public void setRuleContextParams(Map<String, String> ruleContextParams) {
		this.ruleContextParams = ruleContextParams;
	}
	
	public String getRuleText() {
		return ruleText;
	}

	public void setRuleText(String ruleText) {
		this.ruleText = ruleText;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	
	

}
