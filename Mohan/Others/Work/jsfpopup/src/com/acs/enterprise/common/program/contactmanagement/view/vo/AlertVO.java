/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;


import java.util.Date;

/**
 * This class will be the Case Details Persistent Value Object that are used in
 * the Log Case.
 * 
 * @author Wipro
 */
public class AlertVO extends AuditaleEnterpriseBaseVO
       // extends EnterpriseBaseVO //CR_ESPRD00373565_LOGCASE_23JUL2010
{
    /** This will holds the alertType */
    private String alertType;
    
    /** holds the alert type desc */
    private String alertTypeDesc;

    /** This will holds the description */
    private String description;

    /** This will holds the dueDate */
    private Date dueDate;

    /** This will holds the dueDate of type String */
    private String dueDateStr;

    /** This will holds the userWorkUnitSK */
    private Double userWorkUnitSK;

    /** This will holds the status */
    private String status;
    
    /** This will holds the status desc */
    private String statusDesc;

    /** This will holds the notifyViaAlert */
    private String notifyViaAlert;
    
    /** holds the notify user */
    private String notifyUserName;
    
    /** holds the AlertSK */
    private Long alertSK;
    
    /** holds the receiving user id */
    private String rcvgUserID;
    
    /** used for dummy */
    private String dummy;
    
    /** holds the actCMCaseEventCode */
    private String actCMCaseEventCode;
    
    /** holds the actCMCaseStepCode */
    private String actCMCaseStepCode;
    
    /** holds the step order no */
    private String stepOrderNo;
    
    /** holds the casetypeSK */
    private Long caseTypeSK;
    
    /**
     * Default Constructor.
     */
    public AlertVO()
    {
        super();
        dummy = "dummy";
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
       // logger.debug("setAlertType().");
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
    public Date getDueDate()
    {
        return dueDate;
    }

    /**
     * @param dueDate
     *            The dueDate to set.
     */
    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    /**
     * @return Returns the dueDateStr.
     */
    public String getDueDateStr()
    {
        return dueDateStr;
    }

    /**
     * @param dueDateStr
     *            The dueDateStr to set.
     */
    public void setDueDateStr(String dueDateStr)
    {
        this.dueDateStr = dueDateStr;
    }

    /**
     * @return Returns the notifyViaAlert.
     */
    public String getNotifyViaAlert()
    {
        return notifyViaAlert;
    }

    /**
     * @param notifyViaAlert
     *            The notifyViaAlert to set.
     */
    public void setNotifyViaAlert(String notifyViaAlert)
    {
        this.notifyViaAlert = notifyViaAlert;
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
     * @return Returns the userWorkUnitSK.
     */
    public Double getUserWorkUnitSK()
    {
        return userWorkUnitSK;
    }

    /**
     * @param userWorkUnitSK
     *            The userWorkUnitSK to set.
     */
    public void setUserWorkUnitSK(Double userWorkUnitSK)
    {
        this.userWorkUnitSK = userWorkUnitSK;
    }
    /**
     * @return Returns the alertSK.
     */
    public Long getAlertSK()
    {
        return alertSK;
    }
    /**
     * @param alertSK The alertSK to set.
     */
    public void setAlertSK(Long alertSK)
    {
        this.alertSK = alertSK;
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
     * @return Returns the alertTypeDesc.
     */
    public String getAlertTypeDesc()
    {
        return alertTypeDesc;
    }
    /**
     * @param alertTypeDesc The alertTypeDesc to set.
     */
    public void setAlertTypeDesc(String alertTypeDesc)
    {
        this.alertTypeDesc = alertTypeDesc;
    }
    /**
     * @return Returns the notifyUserName.
     */
    public String getNotifyUserName()
    {
        return notifyUserName;
    }
    /**
     * @param notifyUserName The notifyUserName to set.
     */
    public void setNotifyUserName(String notifyUserName)
    {
        this.notifyUserName = notifyUserName;
    }
    /**
     * @return Returns the statusDesc.
     */
    public String getStatusDesc()
    {
        return statusDesc;
    }
    /**
     * @param statusDesc The statusDesc to set.
     */
    public void setStatusDesc(String statusDesc)
    {
        this.statusDesc = statusDesc;
    }
    /**
     * @return Returns the rcvgUserID.
     */
    public String getRcvgUserID()
    {
        return rcvgUserID;
    }
    /**
     * @param rcvgUserID The rcvgUserID to set.
     */
    public void setRcvgUserID(String rcvgUserID)
    {
        this.rcvgUserID = rcvgUserID;
    }
    /**
     * @return Returns the actCMCaseEventCode.
     */
    public String getActCMCaseEventCode()
    {
        return actCMCaseEventCode;
    }
    /**
     * @param actCMCaseEventCode The actCMCaseEventCode to set.
     */
    public void setActCMCaseEventCode(String actCMCaseEventCode)
    {
        this.actCMCaseEventCode = actCMCaseEventCode;
    }
    /**
     * @return Returns the actCMCaseStepCode.
     */
    public String getActCMCaseStepCode()
    {
        return actCMCaseStepCode;
    }
    /**
     * @param actCMCaseStepCode The actCMCaseStepCode to set.
     */
    public void setActCMCaseStepCode(String actCMCaseStepCode)
    {
        this.actCMCaseStepCode = actCMCaseStepCode;
    }
    /**
     * @return Returns the stepOrderNo.
     */
    public String getStepOrderNo()
    {
        return stepOrderNo;
    }
    /**
     * @param stepOrderNo The stepOrderNo to set.
     */
    public void setStepOrderNo(String stepOrderNo)
    {
        this.stepOrderNo = stepOrderNo;
    }
    /**
     * @return Returns the caseTypeSK.
     */
    public Long getCaseTypeSK()
    {
        return caseTypeSK;
    }
    /**
     * @param caseTypeSK The caseTypeSK to set.
     */
    public void setCaseTypeSK(Long caseTypeSK)
    {
        this.caseTypeSK = caseTypeSK;
    }
//  UC-PGM-CRM-018.7_ESPRD00382416_30JAN10
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
	 //EOF UC-PGM-CRM-018.7_ESPRD00382416_30JAN10
	//added for UC-PGM-CRM-018.4_ESPRD00382315_16FEB2010
	private Integer caseEventSeqNum;
	
	/**
	 * @return Returns the ecaseStepSeqNum.
	 */
	public Integer getCaseEventSeqNum() {
		return caseEventSeqNum;
	}
	/**
	 * @param ecaseStepSeqNum The ecaseStepSeqNum to set.
	 */
	public void setCaseEventSeqNum(Integer caseEventSeqNum) {
		this.caseEventSeqNum = caseEventSeqNum;
	}//EOF UC-PGM-CRM-018.4_ESPRD00382315_16FEB2010
}
