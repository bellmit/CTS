/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;

/**
 * This class will be the VO for Maintain Case Type Event
 * 
 * @author Wipro
 */
public class CaseTypeEventVO extends AuditaleEnterpriseBaseVO 
       // extends EnterpriseBaseVO //CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
{
	
	/**
	 * Flag for caseEventSeqNum 
	 */
	 private Integer caseEventSeqNum = null; 
	  
    /**
     * Flag for caseTypeEvent include
     */
    private String caseTypeEventInclude = ContactManagementConstants.YES;

    /**
     * Flag for imgage include
     */
    private boolean eventImgInclude = true;

    /**
     * reference is used to combine dfltBeforeAfterCode & dfltSendAlertDaysCode
     * for
     */
    private String dfltSendAlertDaysBeforeOrAfter;

    /**
     * Holds the case type event type code.
     */
    private String dfltCaseTypeEventTypeCode;

    /**
     * @return Returns the dfltCaseTypeEventTypeCode.
     */
    public String getDfltCaseTypeEventTypeCode()
    {
        return dfltCaseTypeEventTypeCode;
        
    }

    /**
     * @param thedfltCaseTypeEventTypeCode
     *            The dfltCaseTypeEventTypeCode to set.
     */
    public void setDfltCaseTypeEventTypeCode(String thedfltCaseTypeEventTypeCode)
    {
    	
        dfltCaseTypeEventTypeCode = thedfltCaseTypeEventTypeCode;
    }

    /**
     * Holds the Notify alert user id.
     */
    private String dfltNotfyAlertUserId;
    
    /**
     * Holds the Notify alert user name.
     */
    private String dfltNotfyAlertUserName = null;

    /**
     * @return Returns the dfltNotfyAlertUserId.
     */
    public String getDfltNotfyAlertUserId()
    {
        return dfltNotfyAlertUserId;
    }

    /**
     * @param thedfltNotfyAlertUserId
     *            The dfltNotfyAlertUserId to set.
     */
    public void setDfltNotfyAlertUserId(String thedfltNotfyAlertUserId)
    {
        dfltNotfyAlertUserId = thedfltNotfyAlertUserId;
    }

    /**
     * Holds the default alert based table name
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private String dfltAlertBasedOnTblName;

    /**
     * @return Returns the dfltAlertBasedOnTblName.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public String getDfltAlertBasedOnTblName()
    {
        return dfltAlertBasedOnTblName;
    }

    *//**
     * @param thedfltAlertBasedOnTblName
     *            The dfltAlertBasedOnTblName to set.
     *//*
    public void setDfltAlertBasedOnTblName(String thedfltAlertBasedOnTblName)
    {
        dfltAlertBasedOnTblName = thedfltAlertBasedOnTblName;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.
    

    /**
     * Holds the default alert based column name
     */
    private String dfltAlertBasedOnColName;

    /**
     * @return Returns the dfltAlertBasedOnColName.
     */
    public String getDfltAlertBasedOnColName()
    {
        return dfltAlertBasedOnColName;
    }

    /**
     * @param thedfltAlertBasedOnColName
     *            The dfltAlertBasedOnColName to set.
     */
    public void setDfltAlertBasedOnColName(String thedfltAlertBasedOnColName)
    {
        dfltAlertBasedOnColName = thedfltAlertBasedOnColName;
    }

    /**
     * Holds the Send alert days code.
     */
    private String dfltSendAlertDaysCode;

    /**
     * @return Returns the dfltSendAlertDaysCode.
     */
    public String getDfltSendAlertDaysCode()
    {
        return dfltSendAlertDaysCode;
    }

    /**
     * @param thedfltSendAlertDaysCode
     *            The dfltSendAlertDaysCode to set.
     */
    public void setDfltSendAlertDaysCode(String thedfltSendAlertDaysCode)
    {
        dfltSendAlertDaysCode = thedfltSendAlertDaysCode;
    }

    /**
     * Holds the default before after code.
     */
    private String dfltBeforeAfterCode;

    /**
     * @return Returns the dfltBeforeAfterCode.
     */
    public String getDfltBeforeAfterCode()
    {
        return dfltBeforeAfterCode;
    }

    /**
     * @param thedfltBeforeAfterCode
     *            The dfltBeforeAfterCode to set.
     */
    public void setDfltBeforeAfterCode(String thedfltBeforeAfterCode)
    {
        dfltBeforeAfterCode = thedfltBeforeAfterCode;
    }

    /**
     * Holds the default cots letter tempate key data.
     */
    private String dfltCotsLtrTmltKeyData;

    /**
     * @return Returns the dfltCotsLtrTmltKeyData.
     */
    public String getDfltCotsLtrTmltKeyData()
    {
        return dfltCotsLtrTmltKeyData;
    }

    /**
     * @param thedfltCotsLtrTmltKeyData
     *            The dfltCotsLtrTmltKeyData to set.
     */
    public void setDfltCotsLtrTmltKeyData(String thedfltCotsLtrTmltKeyData)
    {
        dfltCotsLtrTmltKeyData = thedfltCotsLtrTmltKeyData;
    }

    /**
     * default Days unit Due num
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private String dfltDaysUntilDueNum;

    /**
     * @return Returns the dfltDaysUntilDueNum.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public String getDfltDaysUntilDueNum()
    {
        return dfltDaysUntilDueNum;
    }

    *//**
     * @param thedfltDaysUntilDueNum
     *            The dfltDaysUntilDueNum to set.
     *//*
    public void setDfltDaysUntilDueNum(String thedfltDaysUntilDueNum)
    {
        dfltDaysUntilDueNum = thedfltDaysUntilDueNum;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.
    
    /**
     * Holds the Available case event sk
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private String availCaseEventSK;

    /**
     * @return Returns the availCaseEventSK.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public String getAvailCaseEventSK()
    {
        return availCaseEventSK;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.
    
    /**
     * @return Returns the caseTypeEventInclude.
     */
    public String getCaseTypeEventInclude()
    {
        return caseTypeEventInclude;
    }

    /**
     * @param caseTypeEventInclude
     *            The caseTypeEventInclude to set.
     */
    public void setCaseTypeEventInclude(String caseTypeEventInclude)
    {
        this.caseTypeEventInclude = caseTypeEventInclude;
    }

    /**
     * @return Returns the eventImgInclude.
     */
    public boolean isEventImgInclude()
    {
        return eventImgInclude;
    }

    /**
     * @param eventImgInclude
     *            The eventImgInclude to set.
     */
    public void setEventImgInclude(boolean eventImgInclude)
    {
        this.eventImgInclude = eventImgInclude;
    }

    /**
     * @param theavailCaseEventSK
     *            The availCaseEventSK to set.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public void setAvailCaseEventSK(String theavailCaseEventSK)
    {
        availCaseEventSK = theavailCaseEventSK;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.

    /**
     * @return Returns the dfltSendAlertDaysBeforeOrAfter.
     */
    public String getDfltSendAlertDaysBeforeOrAfter()
    {
        return dfltSendAlertDaysBeforeOrAfter;
    }

    /**
     * @param dfltSendAlertDaysBeforeOrAfter
     *            The dfltSendAlertDaysBeforeOrAfter to set.
     */
    public void setDfltSendAlertDaysBeforeOrAfter(
            String dfltSendAlertDaysBeforeOrAfter)
    {
        this.dfltSendAlertDaysBeforeOrAfter = dfltSendAlertDaysBeforeOrAfter;
    }
	/**
	 * @return Returns the dfltNotfyAlertUserName.
	 */
	public String getDfltNotfyAlertUserName() {
		return dfltNotfyAlertUserName;
	}
	/**
	 * @param dfltNotfyAlertUserName The dfltNotfyAlertUserName to set.
	 */
	public void setDfltNotfyAlertUserName(String dfltNotfyAlertUserName) {
		this.dfltNotfyAlertUserName = dfltNotfyAlertUserName;
	}
	
	
    /**
	 * @return Returns the caseEventSeqNum.
	 */
	public Integer getCaseEventSeqNum() {
		return caseEventSeqNum;
	}
	/**
	 * @param caseEventSeqNum The caseEventSeqNum to set.
	 */
	public void setCaseEventSeqNum(Integer caseEventSeqNum) {
		this.caseEventSeqNum = caseEventSeqNum;
	}
	//ESPRD00527888_UC-PGM-CRM-48_29SEP2010
	private String dfltCaseTypeEventTypeCodeDesc="";

	
	/**
	 * @return Returns the dfltCaseTypeEventTypeCodeDesc.
	 */
	public String getDfltCaseTypeEventTypeCodeDesc() {
		return dfltCaseTypeEventTypeCodeDesc;
	}
	/**
	 * @param dfltCaseTypeEventTypeCodeDesc The dfltCaseTypeEventTypeCodeDesc to set.
	 */
	public void setDfltCaseTypeEventTypeCodeDesc(
			String dfltCaseTypeEventTypeCodeDesc) {
		this.dfltCaseTypeEventTypeCodeDesc = dfltCaseTypeEventTypeCodeDesc;
	}
	//EOf ESPRD00527888_UC-PGM-CRM-48_29SEP2010
	
	private String dfltAlertBasedOnDesc = "";
	
	
	/**
	 * @return Returns the dfltAlertBasedOnDesc.
	 */
	public String getDfltAlertBasedOnDesc() {
		return dfltAlertBasedOnDesc;
	}
	/**
	 * @param dfltAlertBasedOnDesc The dfltAlertBasedOnDesc to set.
	 */
	public void setDfltAlertBasedOnDesc(String dfltAlertBasedOnDesc) {
		this.dfltAlertBasedOnDesc = dfltAlertBasedOnDesc;
	}
}
