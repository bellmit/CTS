/* Infinite added for UC-PGM-CRM-018.10, CR 2401
 * 
 */

/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * This class will be the CaseType Appeal Request Persistent Data Object that are used in
 * the Log Case.
 * 
 * @author ICS
 */
public class CaseTypeAppealRequestVO 
	extends EnterpriseBaseVO 
{
	

	/** This will holds the rateSettingDate */
    private String 	rateSettingDate;
	
    /** This will holds the stateFiscalYrEnd */
    private String stateFiscalYrEnd;
    
    /** This will holds the facilityLevelAppeal */
    private String facilityLevelAppeal;
    
    /** This will holds the numNFsInvolved */
    private String numNFsInvolved;
    
    /** This will holds the ratesRecalculated */
    private String ratesRecalculated;
    
    
	
	
	/**
	 * @return Returns the facilityLevelAppeal.
	 */
	public String getFacilityLevelAppeal() 
	{
		return facilityLevelAppeal;
	}
	/**
	 * @param facilityLevelAppeal The facilityLevelAppeal to set.
	 */
	public void setFacilityLevelAppeal(String facilityLevelAppeal) 
	{
		this.facilityLevelAppeal = facilityLevelAppeal;
	}
	/**
	 * @return Returns the numNFsInvolved.
	 */
	public String getNumNFsInvolved() 
	{
		return numNFsInvolved;
	}
	/**
	 * @param numNFsInvolved The numNFsInvolved to set.
	 */
	public void setNumNFsInvolved(String numNFsInvolved) 
	{
		this.numNFsInvolved = numNFsInvolved;
	}
	/**
	 * @return Returns the ratesRecalculated.
	 */
	public String getRatesRecalculated() 
	{
		return ratesRecalculated;
	}
	/**
	 * @param ratesRecalculated The ratesRecalculated to set.
	 */
	public void setRatesRecalculated(String ratesRecalculated) 
	{
		this.ratesRecalculated = ratesRecalculated;
	}
	
	
	/**
	 * @return Returns the rateSettingDate.
	 */
	public String getRateSettingDate() {
		return rateSettingDate;
	}
	/**
	 * @param rateSettingDate The rateSettingDate to set.
	 */
	public void setRateSettingDate(String rateSettingDate) {
		this.rateSettingDate = rateSettingDate;
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
}
