/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * This class will be the Associated Case Details Persistent Value Object that
 * are used in the Log Case.
 * 
 * @author Vijaymai
 */
public class AssociatedCaseVO extends AuditaleEnterpriseBaseVO 
// extends EnterpriseBaseVO //CR_ESPRD00373565_LogCase_04AUG2010
{
    /** holds the caseID */
    private String caseID;

    /** holds the created date as String */
    private String createdDateStr;

    /** holds the created date */
    private Date createdDate;

    /** holds the status */
    private String status;

    /** holds the caseType */
    private String caseType;

    /** holds the caseLink */
    private boolean caseLink;

    /** used for dummy */
    private String dummy = "dummy";

    private String statusstr;

    public String getStatusstr() {
		return statusstr;
	}

	public void setStatusstr(String statusstr) {
		this.statusstr = statusstr;
	}

	/**
     * @return Returns the caseID.
     */
    public String getCaseID()
    {
        return caseID;
    }

    /**
     * @param caseID
     *            The caseID to set.
     */
    public void setCaseID(String caseID)
    {
        this.caseID = caseID;
        
    }

    
   
	/**
	 * @return Returns the caseLink.
	 */
	public boolean isCaseLink() {
		return caseLink;
	}
	/**
	 * @param caseLink The caseLink to set.
	 */
	public void setCaseLink(boolean caseLink) {
		this.caseLink = caseLink;
	}
    /**
     * @return Returns the caseType.
     */
    public String getCaseType()
    {
        return caseType;
    }

    /**
     * @param caseType
     *            The caseType to set.
     */
    public void setCaseType(String caseType)
    {
        this.caseType = caseType;
    }

    /**
     * @return Returns the createdDate.
     */
    public Date getCreatedDate()
    {
        return createdDate;
    }

    /**
     * @param createdDate
     *            The createdDate to set.
     */
    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    /**
     * @return Returns the createdDateStr.
     */
    public String getCreatedDateStr()
    {
        return createdDateStr;
    }

    /**
     * @param createdDateStr
     *            The createdDateStr to set.
     */
    public void setCreatedDateStr(String createdDateStr)
    {
        this.createdDateStr = createdDateStr;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * @return Returns the dummy.
     */
    public String getDummy()
    {
        return dummy;
    }

    /**
     * @param dummy
     *            The dummy to set.
     */
    public void setDummy(String dummy)
    {
        this.dummy = dummy;
    }
//  UC-PGM-CRM-018_ESPRD00388742_29JAN10
    /** This field holds the BusinessUnit valid value code of the casetype */
    private String businessUnitCaseTypeCode;
   
	/**
	 * @return Returns the businessUnitCaseTypeCode.
	 */
	public String getBusinessUnitCaseTypeCode() {
		return businessUnitCaseTypeCode;
	}
	/**
	 * @param businessUnitCaseTypeCode The businessUnitCaseTypeCode to set.
	 */
	public void setBusinessUnitCaseTypeCode(String businessUnitCaseTypeCode) {
		this.businessUnitCaseTypeCode = businessUnitCaseTypeCode;
	}
	 //EOF UC-PGM-CRM-018_ESPRD00388742_29JAN10
}
