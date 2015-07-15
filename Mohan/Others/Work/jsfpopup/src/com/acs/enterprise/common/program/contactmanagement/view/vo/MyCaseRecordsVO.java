/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;


/**
 * This class will be the VO for MyCaseRecords tab of MyTasks Page
 * 
 * @author Wipro
 */
public class MyCaseRecordsVO
        extends EnterpriseBaseVO
{
   

    /** Holds the Case Record Number */
    private String caseRecNo;

    /** Hold the openDate */
    private String openDate;

    /** Hold the priorityCode */
    private String priorityCode;

    /** Holds the priorityCode desc */
    private String priorityCodeDesc;

    /** Hold the status code */
    private String statusCode;

    /** Holds statusCode description */
    private String statusCodeDesc;

    /** Holds the entity type name */
    private String entityType;

    /** Hold the caseType */
    private String caseType;

    /** Hold the CaseDetailsVO */
    private CaseViewVO caseDetailsVO;

    /** Holds the title. */
    private String caseTitle;

    /** Holds The entitySk */
    private String entitySK;

    /** Holds entityDesc */
    private String entityTypeDesc;
    /**Holds entityName**/
    private String entityName;
    

    /**
     * @return Returns the caseRecNo.
     */
    public String getCaseRecNo()
    {
        return caseRecNo;
    }

    /**
     * @param caseRecNo
     *            The caseRecNo to set.
     */
    public void setCaseRecNo(String caseRecNo)
    {
        this.caseRecNo = caseRecNo;
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
     * @return Returns the entityType.
     */
    public String getEntityType()
    {
        return entityType;
    }

    /**
     * @param entityType
     *            The entityType to set.
     */
    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }

    /**
     * @return Returns the openDate.
     */
    public String getOpenDate()
    {
        return openDate;
    }

    /**
     * @param openDate
     *            The openDate to set.
     */
    public void setOpenDate(String openDate)
    {
        this.openDate = openDate;
    }

    /**
     * @return Returns the priorityCode.
     */
    public String getPriorityCode()
    {
        return priorityCode;
    }

    /**
     * @param priorityCode
     *            The priorityCode to set.
     */
    public void setPriorityCode(String priorityCode)
    {
        this.priorityCode = priorityCode;
    }

    /**
     * @return Returns the statusCode.
     */
    public String getStatusCode()
    {
        return statusCode;
    }

    /**
     * @param statusCode
     *            The statusCode to set.
     */
    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    /**
     * @return Returns the caseDetailsVO.
     */
    public CaseViewVO getCaseDetailsVO()
    {
        return caseDetailsVO;
    }

    /**
     * @param caseDetailsVO
     *            The caseDetailsVO to set.
     */
    public void setCaseDetailsVO(CaseViewVO caseDetailsVO)
    {
        this.caseDetailsVO = caseDetailsVO;
    }

    /**
     * @return Returns the caseTitle.
     */
    public String getCaseTitle()
    {
        return caseTitle;
    }

    /**
     * @param caseTitle
     *            The caseTitle to set.
     */
    public void setCaseTitle(String caseTitle)
    {
        this.caseTitle = caseTitle;
    }

    /**
     * @return Returns the entitySK.
     */
    public String getEntitySK()
    {
        return entitySK;
    }

    /**
     * @param entitySK
     *            The entitySK to set.
     */
    public void setEntitySK(String entitySK)
    {
        this.entitySK = entitySK;
    }

    /**
     * @return Returns the priorityCodeDesc.
     */
    public String getPriorityCodeDesc()
    {
        return priorityCodeDesc;
    }

    /**
     * @param priorityCodeDesc
     *            The priorityCodeDesc to set.
     */
    public void setPriorityCodeDesc(String priorityCodeDesc)
    {
        this.priorityCodeDesc = priorityCodeDesc;
    }

    /**
     * @return Returns the statusCodeDesc.
     */
    public String getStatusCodeDesc()
    {
        return statusCodeDesc;
    }

    /**
     * @param statusCodeDesc
     *            The statusCodeDesc to set.
     */
    public void setStatusCodeDesc(String statusCodeDesc)
    {
        this.statusCodeDesc = statusCodeDesc;
    }

    /**
     * @return Returns the entityTypeDesc.
     */
    public String getEntityTypeDesc()
    {
        return entityTypeDesc;
    }

    /**
     * @param entityTypeDesc
     *            The entityTypeDesc to set.
     */
    public void setEntityTypeDesc(String entityTypeDesc)
    {
        this.entityTypeDesc = entityTypeDesc;
    }
    /**
	 * @return Returns the entityName.
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName The entityName to set.
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
