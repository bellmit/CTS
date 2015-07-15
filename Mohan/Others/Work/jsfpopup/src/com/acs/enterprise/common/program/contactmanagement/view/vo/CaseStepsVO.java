/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;


/**
 * This class will be the Case Steps Details Persistent Data Object that are
 * used in the Log Case.
 * 
 * @author Wipro
 */
public class CaseStepsVO extends AuditaleEnterpriseBaseVO
        //extends EnterpriseBaseVO //CR_ESPRD00373565_LogCase_04AUG2010
{
   
    /** This will holds the order */
    private String order;

    /** This will holds the description */
    private String description;

    /** This will holds the routeTo */
    private String routeTo;

    /** This will holds the status */
    private String status;

    /** This will holds the dateStarted */
    private Date dateStarted;

    /** This will holds the dateStarted of type string */
    private String dateStartedStr;

    /** This will holds the expected Days To Complete */
    private String expectedDaysToComplete;

    /** This will holds the completed Based On */
    private String completedBasedOn;

    /** This will holds the completedBasedOnValue */
    private String completedBasedOnValue;

    /** This will holds the expectedCompletionDate of type string */
    private String expectedCompletionDateStr;

    /** This will holds the expectedCompletionDate */
    private Date expectedCompletionDate;

    /** This will holds the completionDate */
    private Date completionDate;

    /** This will holds the completionDate Of Type String */
    private String completionDateStr;

    /** This will holds the notifyViaAlert */
    private String notifyViaAlert;

    /** This will holds the alertBasedOn */
    private String alertBasedOn;

    /** This will holds the alert BasedOn Value */
    private String alertBasedOnValue;

    /** This will holds the send alertDays */
    private String sendAlertDays;

    /** This will holds the beforeOrAfterInd */
    private String beforeOrAfterInd;

    /** This will holds the template */
    private String template;

    /** This will holds the caseStepCode */
    private String caseStepCode;

    /** This will holds the caseSK */
    private String caseSK;

    /** This will holds the caseTypeSK */
    private String caseTypeSK;
    
    /** used for dummy */
    private String dummy = "dummy";
    
    /** Used for Status Description*/
    
    private String statusDescription;
    
    private String notifyAlertDescription;
    
    private String caseStepsDescription;
    
    private String routeToDescription;
    
    private String completionBasedOnDescription;    
     
    
    
    /** This will holds the caseStepSeqNum */
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
     * @return Returns the caseSK.
     */
    public String getCaseSK()
    {
        return caseSK;
    }

    /**
     * @param caseSK
     *            The caseSK to set.
     */
    public void setCaseSK(String caseSK)
    {
        this.caseSK = caseSK;
        
    }

    /**
     * @return Returns the caseTypeSK.
     */
    public String getCaseTypeSK()
    {
        return caseTypeSK;
    }

    /**
     * @param caseTypeSK
     *            The caseTypeSK to set.
     */
    public void setCaseTypeSK(String caseTypeSK)
    {
        this.caseTypeSK = caseTypeSK;
    }

    /**
     * @return Returns the caseStepCode.
     */
    public String getCaseStepCode()
    {
        return caseStepCode;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param caseStepCode
     *            The caseStepCode to set.
     */
    public void setCaseStepCode(String caseStepCode)
    {
        this.caseStepCode = caseStepCode;
    }

    /**
     * @return Returns the alertBasedOn.
     */
    public String getAlertBasedOn()
    {
        return alertBasedOn;
    }

    /**
     * @param alertBasedOn
     *            The alertBasedOn to set.
     */
    public void setAlertBasedOn(String alertBasedOn)
    {
        this.alertBasedOn = alertBasedOn;
    }

    /**
     * @return Returns the alertBasedOnValue.
     */
    public String getAlertBasedOnValue()
    {
        return alertBasedOnValue;
    }

    /**
     * @param alertBasedOnValue
     *            The alertBasedOnValue to set.
     */
    public void setAlertBasedOnValue(String alertBasedOnValue)
    {
        this.alertBasedOnValue = alertBasedOnValue;
    }

    /**
     * @return Returns the beforeOrAfterInd.
     */
    public String getBeforeOrAfterInd()
    {
        return beforeOrAfterInd;
    }

    /**
     * @param beforeOrAfterInd
     *            The beforeOrAfterInd to set.
     */
    public void setBeforeOrAfterInd(String beforeOrAfterInd)
    {
        this.beforeOrAfterInd = beforeOrAfterInd;
    }

    /**
     * @return Returns the completedBasedOn.
     */
    public String getCompletedBasedOn()
    {
        return completedBasedOn;
    }

    /**
     * @param completedBasedOn
     *            The completedBasedOn to set.
     */
    public void setCompletedBasedOn(String completedBasedOn)
    {
        this.completedBasedOn = completedBasedOn;
    }

    /**
     * @return Returns the completedBasedOnValue.
     */
    public String getCompletedBasedOnValue()
    {
        return completedBasedOnValue;
    }

    /**
     * @param completedBasedOnValue
     *            The completedBasedOnValue to set.
     */
    public void setCompletedBasedOnValue(String completedBasedOnValue)
    {
        this.completedBasedOnValue = completedBasedOnValue;
    }

    /**
     * @return Returns the dateStarted.
     */
    public Date getDateStarted()
    {
        return dateStarted;
    }

    /**
     * @param dateStarted
     *            The dateStarted to set.
     */
    public void setDateStarted(Date dateStarted)
    {
        this.dateStarted = dateStarted;
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
     * @return Returns the expectedCompletionDate.
     */
    public Date getExpectedCompletionDate()
    {
        return expectedCompletionDate;
    }

    /**
     * @param expectedCompletionDate
     *            The expectedCompletionDate to set.
     */
    public void setExpectedCompletionDate(Date expectedCompletionDate)
    {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    /**
     * @return Returns the expectedCompletionDateStr.
     */
    public String getExpectedCompletionDateStr()
    {
        return expectedCompletionDateStr;
    }

    /**
     * @param expectedCompletionDateStr
     *            The expectedCompletionDateStr to set.
     */
    public void setExpectedCompletionDateStr(String expectedCompletionDateStr)
    {
        this.expectedCompletionDateStr = expectedCompletionDateStr;
    }

    /**
     * @return Returns the expectedDaysToComplete.
     */
    public String getExpectedDaysToComplete()
    {
        return expectedDaysToComplete;
    }

    /**
     * @param expectedDaysToComplete
     *            The expectedDaysToComplete to set.
     */
    public void setExpectedDaysToComplete(String expectedDaysToComplete)
    {
        this.expectedDaysToComplete = expectedDaysToComplete;
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
     * @return Returns the order.
     */
    public String getOrder()
    {
        return order;
    }

    /**
     * @param order
     *            The order to set.
     */
    public void setOrder(String order)
    {
        this.order = order;
    }

    /**
     * @return Returns the routeTo.
     */
    public String getRouteTo()
    {
        return routeTo;
    }

    /**
     * @param routeTo
     *            The routeTo to set.
     */
    public void setRouteTo(String routeTo)
    {
        this.routeTo = routeTo;
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
     * @return Returns the template.
     */
    public String getTemplate()
    {
        return template;
    }

    /**
     * @param template
     *            The template to set.
     */
    public void setTemplate(String template)
    {
        this.template = template;
    }

    /**
     * @return Returns the completionDateStr.
     */
    public String getCompletionDateStr()
    {
        return completionDateStr;
    }

    /**
     * @param completionDateStr
     *            The completionDateStr to set.
     */
    public void setCompletionDateStr(String completionDateStr)
    {
        this.completionDateStr = completionDateStr;
    }

    /**
     * @return Returns the completionDate.
     */
    public Date getCompletionDate()
    {
        return completionDate;
    }

    /**
     * @param completionDate
     *            The completionDate to set.
     */
    public void setCompletionDate(Date completionDate)
    {
        this.completionDate = completionDate;
    }

    /**
     * @return Returns the dateStartedStr.
     */
    public String getDateStartedStr()
    {
        return dateStartedStr;
    }

    /**
     * @param dateStartedStr
     *            The dateStartedStr to set.
     */
    public void setDateStartedStr(String dateStartedStr)
    {
        this.dateStartedStr = dateStartedStr;
    }

    /**
     * @return Returns the sendAlertDays.
     */
    public String getSendAlertDays()
    {
        return sendAlertDays;
    }

    /**
     * @param sendAlertDays
     *            The sendAlertDays to set.
     */
    public void setSendAlertDays(String sendAlertDays)
    {
        this.sendAlertDays = sendAlertDays;
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
	 * @return Returns the statusDescriptio.
	 */
	public String getStatusDescription() {
		return statusDescription;
	}
	/**
	 * @param statusDescriptio The statusDescriptio to set.
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	
	/**
	 * @return Returns the notifyAlertDescription.
	 */
	public String getNotifyAlertDescription() {
		return notifyAlertDescription;
	}
	/**
	 * @param notifyAlertDescription The notifyAlertDescription to set.
	 */
	public void setNotifyAlertDescription(String notifyAlertDescription) {
		this.notifyAlertDescription = notifyAlertDescription;
	}
	
	
	/**
	 * @return Returns the caseStepsDescription.
	 */
	public String getCaseStepsDescription() {
		return caseStepsDescription;
	}
	/**
	 * @param caseStepsDescription The caseStepsDescription to set.
	 */
	public void setCaseStepsDescription(String caseStepsDescription) {
		this.caseStepsDescription = caseStepsDescription;
	}
	/**
	 * @return Returns the completionBasedOnDescription.
	 */
	public String getCompletionBasedOnDescription() {
		return completionBasedOnDescription;
	}
	/**
	 * @param completionBasedOnDescription The completionBasedOnDescription to set.
	 */
	public void setCompletionBasedOnDescription(
			String completionBasedOnDescription) {
		this.completionBasedOnDescription = completionBasedOnDescription;
	}
	/**
	 * @return Returns the routeToDescription.
	 */
	public String getRouteToDescription() {
		return routeToDescription;
	}
	/**
	 * @param routeToDescription The routeToDescription to set.
	 */
	public void setRouteToDescription(String routeToDescription) {
		this.routeToDescription = routeToDescription;
	}
	
	//CR_ESPRD00463663_LogCase_Changes_22MAY2010
	private String caseStepSeqNumStr = null;

	/**
	 * @return Returns the caseStepSeqNumStr.
	 */
	public String getCaseStepSeqNumStr() {
		return caseStepSeqNumStr;
	}
	/**
	 * @param caseStepSeqNumStr The caseStepSeqNumStr to set.
	 */
	public void setCaseStepSeqNumStr(String caseStepSeqNumStr) {
		this.caseStepSeqNumStr = caseStepSeqNumStr;
	}
	//EOF CR_ESPRD00463663_LogCase_Changes_22MAY2010
	//Added for defect ESPRD00538641 starts
	private String sendAlertDaysStr;
	/**
	 * @return Returns the sendAlertDaysStr.
	 */
	public String getSendAlertDaysStr() {
		return sendAlertDaysStr;
	}
	/**
	 * @param sendAlertDaysStr The sendAlertDaysStr to set.
	 */
	public void setSendAlertDaysStr(String sendAlertDaysStr) {
		this.sendAlertDaysStr = sendAlertDaysStr;
	}
	private String alertBasedOnStr;
	
	/**
	 * @return Returns the alertBasedOnStr.
	 */
	public String getAlertBasedOnStr() {
		return alertBasedOnStr;
	}
	/**
	 * @param alertBasedOnStr The alertBasedOnStr to set.
	 */
	public void setAlertBasedOnStr(String alertBasedOnStr) {
		this.alertBasedOnStr = alertBasedOnStr;
	}
//	defect ESPRD00538641 ends

	//defect ESPRD00538641 ends
}
