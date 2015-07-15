/* Infinite added for UC-PGM-CRM-018.10, CR 2401
 * 
 */

/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;



import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * This class will be the CaseType ARS Persistent Data Object that are used in
 * the Log Case.
 * 
 * 
 */
public class CaseTypeNFQAVO
        extends EnterpriseBaseVO
{
   
    
    
    /** This holds the state fiscal tear */
     private String stateFiscalYearEnd;
     
     
     /** This holds the noOfReturnsStandard */
     private String noOfReturnsStandard;
     
     
     /** This holds the noOfReturnsOther */
     private String noOfReturnsOther;
     
     
     /** This holds the totalAmountofFilings */
     private String totalAmountOfFilings;
     
     
     /** This holds the totalCollected */
     private String totalCollected;
     
     
     
	
	/**
	 * @return Returns the noOfReturnsOther.
	 */
	public String getNoOfReturnsOther() {
		return noOfReturnsOther;
	}
	/**
	 * @param noOfReturnsOther The noOfReturnsOther to set.
	 */
	public void setNoOfReturnsOther(String noOfReturnsOther) {
		this.noOfReturnsOther = noOfReturnsOther;
	}
	/**
	 * @return Returns the noOfReturnsStandard.
	 */
	public String getNoOfReturnsStandard() {
		return noOfReturnsStandard;
	}
	/**
	 * @param noOfReturnsStandard The noOfReturnsStandard to set.
	 */
	public void setNoOfReturnsStandard(String noOfReturnsStandard) {
		this.noOfReturnsStandard = noOfReturnsStandard;
	}
	/**
	 * @return Returns the stateFiscalYearEnd.
	 */
	public String getStateFiscalYearEnd() {
		return stateFiscalYearEnd;
	}
	/**
	 * @param stateFiscalYearEnd The stateFiscalYearEnd to set.
	 */
	public void setStateFiscalYearEnd(String stateFiscalYearEnd) {
		this.stateFiscalYearEnd = stateFiscalYearEnd;
	}
	/**
	 * @return Returns the totalAmountOfFilings.
	 */
	public String getTotalAmountOfFilings() {
		return totalAmountOfFilings;
	}
	/**
	 * @param totalAmountOfFilings The totalAmountOfFilings to set.
	 */
	public void setTotalAmountOfFilings(String totalAmountOfFilings) {
		this.totalAmountOfFilings = totalAmountOfFilings;
	}
	/**
	 * @return Returns the totalCollected.
	 */
	public String getTotalCollected() {
		return totalCollected;
	}
	/**
	 * @param totalCollected The totalCollected to set.
	 */
	public void setTotalCollected(String totalCollected) {
		this.totalCollected = totalCollected;
	}
}
 