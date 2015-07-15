/* Infinite added for UC-PGM-CRM-018.10, CR 2401
 * 
 */


/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * This class will be the CaseType Field Audit Persistent Data Object that are used in
 * the Log Case.
 * 
 * @author ICS
 */
public class CaseTypeFieldAuditVO 
	extends EnterpriseBaseVO 
{
	
  


	/** This will holds the facilityFiscalYearEnd */
    private String 	facilityFiscalYearEnd;

	
	/** This will holds the facilityIndicator */
    private String facilityIndicator;
	
	
    /** This will holds the stateFiscalYrEnd */
    private String stateFiscalYrEnd;
    
    /** This will holds the daysToComplete */
    private String daysToComplete;
    
    /** This will holds the peerGroupOne */
    private String peerGroupOne;
    
    /** This will holds the peerGroupTwo */
    private String peerGroupTwo;
    
    /** This will holds the homeOfficeInd */
    private String homeOfficeInd;
    
    /** This will holds the fieldAuditDate */
    private String fieldAuditDate;
    
    /** This will holds the reconsiderationReceived */
    private String reconsiderationReceived;
    
    

	/**
	 * Constructor
	 */
	public CaseTypeFieldAuditVO() 
	{
		super();		
	}
	
	
	/**
	 * @return Returns the daysToComplete.
	 */
	public String getDaysToComplete() 
	{
		return daysToComplete;
	}
	/**
	 * @param daysToComplete The daysToComplete to set.
	 */
	public void setDaysToComplete(String daysToComplete) 
	{
		this.daysToComplete = daysToComplete;
	}
	/**
	 * @return Returns the fieldAuditDate.
	 */
	public String getFieldAuditDate() 
	{
		return fieldAuditDate;
	}
	/**
	 * @param fieldAuditDate The fieldAuditDate to set.
	 */
	public void setFieldAuditDate(String fieldAuditDate) 
	{
		this.fieldAuditDate = fieldAuditDate;
	}
	/**
	 * @return Returns the homeOfficeInd.
	 */
	public String getHomeOfficeInd() 
	{
		return homeOfficeInd;
	}
	/**
	 * @param homeOfficeInd The homeOfficeInd to set.
	 */
	public void setHomeOfficeInd(String homeOfficeInd) 
	{
		this.homeOfficeInd = homeOfficeInd;
	}
	/**
	 * @return Returns the peerGroupOne.
	 */
	public String getPeerGroupOne() 
	{
		return peerGroupOne;
	}
	/**
	 * @param peerGroupOne The peerGroupOne to set.
	 */
	public void setPeerGroupOne(String peerGroupOne) 
	{
		this.peerGroupOne = peerGroupOne;
	}
	/**
	 * @return Returns the peerGroupTwo.
	 */
	public String getPeerGroupTwo() 
	{
		return peerGroupTwo;
	}
	/**
	 * @param peerGroupTwo The peerGroupTwo to set.
	 */
	public void setPeerGroupTwo(String peerGroupTwo) 
	{
		this.peerGroupTwo = peerGroupTwo;
	}
	/**
	 * @return Returns the reconsiderationReceived.
	 */
	public String getReconsiderationReceived() 
	{
		return reconsiderationReceived;
	}
	/**
	 * @param reconsiderationReceived The reconsiderationReceived to set.
	 */
	public void setReconsiderationReceived(String reconsiderationReceived) 
	{
		this.reconsiderationReceived = reconsiderationReceived;
	}

	/**
	 * @return Returns the stateFiscalYrEnd.
	 */
	public String getStateFiscalYrEnd() {
		return stateFiscalYrEnd;
	}
	/**
	 * @param stateFiscalYrEnd The stateFiscalYrEnd to set.
	 */
	public void setStateFiscalYrEnd(String stateFiscalYrEnd) {
		this.stateFiscalYrEnd = stateFiscalYrEnd;
	}
	
	
	/**
	 * @return Returns the facilityIndicator.
	 */
	public String getFacilityIndicator() {
		return facilityIndicator;
	}
	/**
	 * @param facilityIndicator The facilityIndicator to set.
	 */
	public void setFacilityIndicator(String facilityIndicator) {
		this.facilityIndicator = facilityIndicator;
	}
	
	/**
	 * @return Returns the facilityFiscalYearEnd.
	 */
	public String getFacilityFiscalYearEnd() {
		return facilityFiscalYearEnd;
	}
	/**
	 * @param facilityFiscalYearEnd The facilityFiscalYearEnd to set.
	 */
	public void setFacilityFiscalYearEnd(String facilityFiscalYearEnd) {
		this.facilityFiscalYearEnd = facilityFiscalYearEnd;
	}
}