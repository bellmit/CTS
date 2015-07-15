/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * This class will be the CaseType ARS Persistent Data Object that are used in
 * the Log Case.
 * 
 * @author Wipro
 */
public class CaseTypeARSVO
        extends EnterpriseBaseVO
{
   
    /** This will holds the appealReceivedInd */
    private String appealReceivedInd;

    /** This will holds the fieldAuditRequired */
    private String fieldAuditRequiredInd;

    /** This will holds the homeOfficeIndicator */
    private String homeOfficeIndicator;

    /** This will holds the pictureDate */
    private Date pictureDate;

    /** This will holds the pictureDate of Type string */
    private String pictureDateStr;

    /** This will holds the rateSettingDate of type string */
    private String rateSettingDateStr;

    /** This will holds the rateSettingDate */
    private Date rateSettingDate;
    
    
   /* Infinite added for UC-PGM-CRM-018.10, CR 1841 
    
   */ /** This will holds the stateFiscalYearEndDate */
    private String stateFiscalYearEndDate;

    
//  Infinite Added for Defect ESPRD00344256
    
     /** This will holds the facilityFiscalYearEndDate */
    private String facilityFiscalYearEndDate;
    
    
    /** This will holds the deskReviewStartDate */
    private String deskReviewStartDate;
    
        /** This will holds the privatePayRatesLoaded */
    private String privatePayRatesLoadedInd;

    /** This will holds the privatePayRatesReceived */
    private String privatePayRatesReceivedInd;

    /** This will holds the yrEnd */
    private String yrEnd;

    /** This will holds the fiscalYearEndDate */
    private Date fiscalYearEndDate;

    /** This will holds the fiscalYearEndDate of type String */
    private String fiscalYearEndDateStr;

    
    
    /** This will holds the Audit date */
    private String fieldAuditDateStr;

    /** This will holds the Audit date */
    private Date fieldAuditDate;

    /** This will holds the performCostReportsXChecksInd */
    private String performCostReportsXChecksInd;

    /** This will holds the staffingPatternsUpdateInd */
    private String staffingPatternsUpdateInd;

    /** This will holds the scheduleKReceivedInd */
    private String scheduleKReceivedInd;
    
    /** used for dummy */
    private String dummy = "dummy";
    
   
    /**
     * @return Returns the fieldAuditDate.
     */
    public Date getFieldAuditDate()
    {
        return fieldAuditDate;
    }

    /**
     * @param fieldAuditDate
     *            The fieldAuditDate to set.
     */
    public void setFieldAuditDate(Date fieldAuditDate)
    {
        this.fieldAuditDate = fieldAuditDate;
       
    }

    /**
     * @return Returns the fieldAuditDateStr.
     */
    public String getFieldAuditDateStr()
    {
        return fieldAuditDateStr;
    }

    /**
     * @param fieldAuditDateStr
     *            The fieldAuditDateStr to set.
     */
    public void setFieldAuditDateStr(String fieldAuditDateStr)
    {
        this.fieldAuditDateStr = fieldAuditDateStr;
    }

    /**
     * @return Returns the homeOfficeIndicator.
     */
    public String getHomeOfficeIndicator()
    {
        return homeOfficeIndicator;
    }

    /**
     * @param homeOfficeIndicator
     *            The homeOfficeIndicator to set.
     */
    public void setHomeOfficeIndicator(String homeOfficeIndicator)
    {
        this.homeOfficeIndicator = homeOfficeIndicator;
    }

    /**
     * @return Returns the pictureDate.
     */
    public Date getPictureDate()
    {
        return pictureDate;
    }

    /**
     * @param pictureDate
     *            The pictureDate to set.
     */
    public void setPictureDate(Date pictureDate)
    {
        this.pictureDate = pictureDate;
    }

    /**
     * @return Returns the pictureDateStr.
     */
    public String getPictureDateStr()
    {
        return pictureDateStr;
    }

    /**
     * @param pictureDateStr
     *            The pictureDateStr to set.
     */
    public void setPictureDateStr(String pictureDateStr)
    {
        this.pictureDateStr = pictureDateStr;
    }

    /**
     * @return Returns the rateSettingDate.
     */
    public Date getRateSettingDate()
    {
        return rateSettingDate;
    }

    /**
     * @param rateSettingDate
     *            The rateSettingDate to set.
     */
    public void setRateSettingDate(Date rateSettingDate)
    {
        this.rateSettingDate = rateSettingDate;
    }

    /**
     * @return Returns the rateSettingDateStr.
     */
    public String getRateSettingDateStr()
    {
        return rateSettingDateStr;
    }

    /**
     * @param rateSettingDateStr
     *            The rateSettingDateStr to set.
     */
    public void setRateSettingDateStr(String rateSettingDateStr)
    {
        this.rateSettingDateStr = rateSettingDateStr;
    }

    
   
    /**
     * Infinite added for UC-PGM-CRM-018.10, CR 1841  
     * @return the stateFiscalYearEndDate.
     */
    public String getStateFiscalYearEndDate()
    {
        return stateFiscalYearEndDate;
    }

    /**
     * @param stateFiscalYearEndDate
     *            The stateFiscalYearEndDate to set.
     */
    public void setStateFiscalYearEndDate(String stateFiscalYearEndDate)
    {
        this.stateFiscalYearEndDate = stateFiscalYearEndDate;
    }
    
    

/**
     * @param yrEnd
     *            The yrEnd to set.
     */
    public void setYrEnd(String yrEnd)
    {
        this.yrEnd = yrEnd;
    }

    /**
     * @return Returns the fiscalYearEndDate.
     */
    public Date getFiscalYearEndDate()
    {
        return fiscalYearEndDate;
    }

    /**
     * @param fiscalYearEndDate
     *            The fiscalYearEndDate to set.
     */
    public void setFiscalYearEndDate(Date fiscalYearEndDate)
    {
        this.fiscalYearEndDate = fiscalYearEndDate;
    }

    /**
     * @return Returns the fiscalYearEndDateStr.
     */
    public String getFiscalYearEndDateStr()
    {
        return fiscalYearEndDateStr;
    }

    /**
     * @param fiscalYearEndDateStr
     *            The fiscalYearEndDateStr to set.
     */
    public void setFiscalYearEndDateStr(String fiscalYearEndDateStr)
    {
        this.fiscalYearEndDateStr = fiscalYearEndDateStr;
    }

    /**
     * @return Returns the appealReceivedInd.
     */
    public String getAppealReceivedInd()
    {
        return appealReceivedInd;
    }

    /**
     * @param appealReceivedInd
     *            The appealReceivedInd to set.
     */
    public void setAppealReceivedInd(String appealReceivedInd)
    {
        this.appealReceivedInd = appealReceivedInd;
    }

    /**
     * @return Returns the fieldAuditRequiredInd.
     */
    public String getFieldAuditRequiredInd()
    {
        return fieldAuditRequiredInd;
    }

    /**
     * @param fieldAuditRequiredInd
     *            The fieldAuditRequiredInd to set.
     */
    public void setFieldAuditRequiredInd(String fieldAuditRequiredInd)
    {
        this.fieldAuditRequiredInd = fieldAuditRequiredInd;
    }

    /**
     * @return Returns the privatePayRatesLoadedInd.
     */
    public String getPrivatePayRatesLoadedInd()
    {
        return privatePayRatesLoadedInd;
    }

    /**
     * @param privatePayRatesLoadedInd
     *            The privatePayRatesLoadedInd to set.
     */
    public void setPrivatePayRatesLoadedInd(String privatePayRatesLoadedInd)
    {
        this.privatePayRatesLoadedInd = privatePayRatesLoadedInd;
    }

    /**
     * @return Returns the privatePayRatesReceivedInd.
     */
    public String getPrivatePayRatesReceivedInd()
    {
        return privatePayRatesReceivedInd;
    }

    /**
     * @param privatePayRatesReceivedInd
     *            The privatePayRatesReceivedInd to set.
     */
    public void setPrivatePayRatesReceivedInd(String privatePayRatesReceivedInd)
    {
        this.privatePayRatesReceivedInd = privatePayRatesReceivedInd;
    }

    /**
     * @return Returns the performCostReportsXChecksInd.
     */
    public String getPerformCostReportsXChecksInd()
    {
        return performCostReportsXChecksInd;
    }

    /**
     * @param performCostReportsXChecksInd
     *            The performCostReportsXChecksInd to set.
     */
    public void setPerformCostReportsXChecksInd(
            String performCostReportsXChecksInd)
    {
        this.performCostReportsXChecksInd = performCostReportsXChecksInd;
    }

    /**
     * @return Returns the scheduleKReceivedInd.
     */
    public String getScheduleKReceivedInd()
    {
        return scheduleKReceivedInd;
    }

    /**
     * @param scheduleKReceivedInd
     *            The scheduleKReceivedInd to set.
     */
    public void setScheduleKReceivedInd(String scheduleKReceivedInd)
    {
        this.scheduleKReceivedInd = scheduleKReceivedInd;
    }

    /**
     * @return Returns the staffingPatternsUpdateInd.
     */
    public String getStaffingPatternsUpdateInd()
    {
        return staffingPatternsUpdateInd;
    }

    /**
     * @param staffingPatternsUpdateInd
     *            The staffingPatternsUpdateInd to set.
     */
    public void setStaffingPatternsUpdateInd(String staffingPatternsUpdateInd)
    {
        this.staffingPatternsUpdateInd = staffingPatternsUpdateInd;
    }
    /**
     * @return Returns the dummy.
     */
    public String getDummy()
    {
        return dummy;
    }
    /**
     * @param dummy The dummy to set.
     */
    public void setDummy(String dummy)
    {
        this.dummy = dummy;
    }
    
/**
 * @return Returns the facilityFiscalYearEndDate.
 */
public String getFacilityFiscalYearEndDate() {
	return facilityFiscalYearEndDate;
}
/**
 * @param facilityFiscalYearEndDate The facilityFiscalYearEndDate to set.
 */
public void setFacilityFiscalYearEndDate(String facilityFiscalYearEndDate) {
	this.facilityFiscalYearEndDate = facilityFiscalYearEndDate;
}


	/**
	 * @return Returns the deskReviewStartDate.
	 */
	public String getDeskReviewStartDate() {
		return deskReviewStartDate;
	}
	/**
	 * @param deskReviewStartDate The deskReviewStartDate to set.
	 */
	public void setDeskReviewStartDate(String deskReviewStartDate) {
		this.deskReviewStartDate = deskReviewStartDate;
	}
}
