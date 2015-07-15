/**
 * Copyright Notice: 2014 Xerox Corporation.
 */

package com.xerox.enterprise.ghs.mmis.rif.core;

import java.util.Date;

/**
 * RulesMetaData POJO holds all the rule related informations.
 * @author 368432
 *
 */
public class RuleMetadata {
	
	// RuleId of the rule from Catalog sheet
	private String ruleId;
	
	// RuleName of the rule 
	private String ruleName;
	
	//Type of the rule POJO/Blaze
	private int ruleType;
	
	//Effective start date of the rule
	private Date effStartDate;
	
	//Effective end date of the rule
	private Date effEndDate;
	
	//SLA of Red Threshold in milliseconds
	private int slaRedThreshold;
	
	//SLA of Amber Threshold in milliseconds
	private int slaAmberThreshold;
	
	//Implement type for the rule
	private String implement;
	
	//MITAMapping of the rule
	private String MITAMapping;

	/**
	 * @return the ruleId
	 */
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(final String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(final String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * @return the ruleType
	 */
	public int getRuleType() {
		return ruleType;
	}

	/**
	 * @param ruleType the ruleType to set
	 */
	public void setRuleType(final int ruleType) {
		this.ruleType = ruleType;
	}

	/**
	 * @return the effStartDate
	 */
	public Date getEffStartDate() {
		return effStartDate;
	}

	/**
	 * @param effStartDate the effStartDate to set
	 */
	public void setEffStartDate(final Date effStartDate) {
		this.effStartDate = effStartDate;
	}

	/**
	 * @return the effEndDate
	 */
	public Date getEffEndDate() {
		return effEndDate;
	}

	/**
	 * @param effEndDate the effEndDate to set
	 */
	public void setEffEndDate(final Date effEndDate) {
		this.effEndDate = effEndDate;
	}

	/**
	 * @return the slaRedThreshold
	 */
	public int getSlaRedThreshold() {
		return slaRedThreshold;
	}

	/**
	 * @param slaRedThreshold the slaRedThreshold to set
	 */
	public void setSlaRedThreshold(final int slaRedThreshold) {
		this.slaRedThreshold = slaRedThreshold;
	}

	/**
	 * @return the slaAmberThreshold
	 */
	public int getSlaAmberThreshold() {
		return slaAmberThreshold;
	}

	/**
	 * @param slaAmberThreshold the slaAmberThreshold to set
	 */
	public void setSlaAmberThreshold(final int slaAmberThreshold) {
		this.slaAmberThreshold = slaAmberThreshold;
	}

	/**
	 * @return the implement
	 */
	public String getImplement() {
		return implement;
	}

	/**
	 * @param implement the implement to set
	 */
	public void setImplement(final String implement) {
		this.implement = implement;
	}

	/**
	 * @return the mITAMapping
	 */
	public String getMITAMapping() {
		return MITAMapping;
	}

	/**
	 * @param mITAMapping the mITAMapping to set
	 */
	public void setMITAMapping(final String mITAMapping) {
		MITAMapping = mITAMapping;
	}
	
}
