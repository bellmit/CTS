/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/** Vo for MyAlerts */
public class MyAlertsVO
        extends EnterpriseBaseVO
{
    /**
     * Constructor for MyAlertsVO
     */
    public MyAlertsVO()
    {
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(MyAlertsVO.class);
        logger.debug("MyAlertsVO constructor");
    }

    /** Hold the Correspondence SK */
    private String correspondenceSk;

    /** Hold the Correspondence SK */
    private String caseSk;

	private String  letterReqSk;
    /** Hold the Alert Type */
    private String alertType;

    /** Holds Alert Type description */
    private String alertTypeDesc;

    /** Hold the description */
    private String description;

    /** Hold the due date */
    private String dueDate;

    /** Hold the scope */
    private String scope;
    
    /** Hold the scope */
    private String scopeDesc;
    
    /** Hold the Entity Name */
    private String entityName;
    	
    /** Holds the CRDetailsVO */
    private CRDetailsVO crDetailsVO;

    /** Holds the CaseDetailsVO */
    private CaseViewVO caseDetailsVO;
    
    //added for defect ESPRD00359439
    /** Holds the caseStepSeqNum */
    private Integer caseStepSeqNum;

	/**
	 * @return Returns the caseStepSeqNum.
	 */
	public Integer getCaseStepSeqNum() {
		return caseStepSeqNum;
	}
	/**
	 * @param caseStepSeqNum The caseStepSeqNum to set.
	 */
	public void setCaseStepSeqNum(Integer caseStepSeqNum) {
		this.caseStepSeqNum = caseStepSeqNum;
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
     * @return Returns the caseSk.
     */
    public String getCaseSk()
    {
        return caseSk;
    }

    /**
     * @param caseSk
     *            The caseSk to set.
     */
    public void setCaseSk(String caseSk)
    {
        this.caseSk = caseSk;
    }

	/**
	 * @return Returns the entityName.
	 */
	public String getEntityName() 
	{
		return entityName;
	}
	
	/**
	 * @param entityName The entityName to set.
	 */
	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}
    /**
     * @return Returns the correspondenceSk.
     */
    public String getCorrespondenceSk()
    {
        return correspondenceSk;
    }

    /**
     * @param correspondenceSk
     *            The correspondenceSk to set.
     */
    public void setCorrespondenceSk(String correspondenceSk)
    {
        this.correspondenceSk = correspondenceSk;
    }

    /**
     * @return Returns the alertType.
     */
    public String getAlertType()
    {
        return alertType;
    }

    /**
     * @param alertType
     *            The alertType to set.
     */
    public void setAlertType(String alertType)
    {
        this.alertType = alertType;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return Returns the dueDate.
     */
    public String getDueDate()
    {
        return dueDate;
    }

    /**
     * @param dueDate
     *            The dueDate to set.
     */
    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }

    /**
     * @return Returns the scope.
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * @param scope
     *            The scope to set.
     */
    public void setScope(String scope)
    {
        this.scope = scope;
    }

    /**
     * @return Returns the crDetailsVO.
     */
    public CRDetailsVO getCrDetailsVO()
    {
        return crDetailsVO;
    }

    /**
     * @param crDetailsVO
     *            The crDetailsVO to set.
     */
    public void setCrDetailsVO(CRDetailsVO crDetailsVO)
    {
        this.crDetailsVO = crDetailsVO;
    }

    /**
     * @return Returns the alertTypeDesc.
     */
    public String getAlertTypeDesc()
    {
        return alertTypeDesc;
    }

    /**
     * @param alertTypeDesc
     *            The alertTypeDesc to set.
     */
    public void setAlertTypeDesc(String alertTypeDesc)
    {
        this.alertTypeDesc = alertTypeDesc;
    }
    
    /**
     * @return Returns the letterReqSk.
     */
    public String getLetterReqSk()
    {
        return letterReqSk;
    }
    
    /**
     * @param letterReqSk The letterReqSk to set.
     */
    public void setLetterReqSk(String letterReqSk)
    {
        this.letterReqSk = letterReqSk;
    }
    //MyTask_Alerts_ESPRD00423256_9MAR2010
    private String tplCaseUserID = null;
 
	/**
	 * @return Returns the tplCaseUserID.
	 */
	public String getTplCaseUserID() {
		return tplCaseUserID;
	}
	/**
	 * @param tplCaseUserID The tplCaseUserID to set.
	 */
	public void setTplCaseUserID(String tplCaseUserID) {
		this.tplCaseUserID = tplCaseUserID;
	} // EOF MyTask_Alerts_ESPRD00423256_9MAR2010
	
//	MyTask_Alerts_ESPRD00432772_10MAR2010
	private String serviceAuthorizationID =null;

	/**
	 * @return Returns the serviceAuthorizationID.
	 */
	public String getServiceAuthorizationID() {
		return serviceAuthorizationID;
	}
	/**
	 * @param serviceAuthorizationID The serviceAuthorizationID to set.
	 */
	public void setServiceAuthorizationID(String serviceAuthorizationID) {
		this.serviceAuthorizationID = serviceAuthorizationID;
	}	//EOf MyTask_Alerts_ESPRD00432772_10MAR2010
	
	//added for UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010
	private String alertSKStr;
	/**
	 * @return Returns the alertSKStr.
	 */
	public String getAlertSKStr() {
		return alertSKStr;
	}
	/**
	 * @param alertSKStr The alertSKStr to set.
	 */
	public void setAlertSKStr(String alertSKStr) {
		this.alertSKStr = alertSKStr;
	}
	
	private String status;

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	private String openDateStr;

	/**
	 * @return Returns the openDateStr.
	 */
	public String getOpenDateStr() {
		return openDateStr;
	}
	/**
	 * @param openDateStr The openDateStr to set.
	 */
	public void setOpenDateStr(String openDateStr) {
		this.openDateStr = openDateStr;
	}
	
	private String createdBy;
	
	/**
	 * @return Returns the createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy The createdBy to set.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}//EOF UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010
	
//	UC-PGM-CRM-053_ESPRD00431860_26MAR2010
	
	private String msqID = null;
	private String memberID = null;

	/**
	 * @return Returns the memberID.
	 */
	public String getMemberID() {
		return memberID;
	}
	/**
	 * @param memberID The memberID to set.
	 */
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	/**
	 * @return Returns the msqID.
	 */
	public String getMsqID() {
		return msqID;
	}
	/**
	 * @param msqID The msqID to set.
	 */
	public void setMsqID(String msqID) {
		this.msqID = msqID;
	}
	//EOF UC-PGM-CRM-053_ESPRD00431860_26MAR2010
	/**
	 * @return Returns the scopeDesc.
	 */
	public String getScopeDesc() {
		return scopeDesc;
	}
	/**
	 * @param scopeDesc The scopeDesc to set.
	 */
	public void setScopeDesc(String scopeDesc) {
		this.scopeDesc = scopeDesc;
	}
}
