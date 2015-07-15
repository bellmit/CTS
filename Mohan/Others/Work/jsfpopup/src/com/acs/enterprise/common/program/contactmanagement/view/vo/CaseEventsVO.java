/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * This class will be the Case Events Details Persistent Data Object that are
 * used in the Log Case.
 * 
 * @author Wipro
 */
public class CaseEventsVO  extends AuditaleEnterpriseBaseVO 
        //extends EnterpriseBaseVO  //CR_ESPRD00373565_LogCase_04AUG2010
{

    /** This will holds the desc */
    private String desc;

    /** This will holds the eventDate of Type String */
    private String eventDateStr;

    /** This will holds the eventDate */
    private Date eventDate;

    /** This will holds the time */
    private String time;

    /** This will holds the amPM */
    private String amPM;

    /** This will holds the status */
    private String statusCd;
    
    /** This will holds the caseEventsDescription */
    private String caseEventsDescription;
    
    /** This will holds the alertBasedOnDescription */
    private String alertBasedOnDescription;
    
    /** This will holds the statusCdDescription */
    private String  statusCdDescription; 

    /** This will holds the status desc*/
    private String statusCdDesc;

    /** This will holds the dispositionDate of type string */
    private String dispositionDateStr;

    /** This will holds the dispositionDate */
    private Date dispositionDate;

    /** This will holds the notifyViaAlert */
    private String notifyViaAlert;

    /** This will holds the notifyViaAlert name*/
    private String notifyViaAlertName;

    /** This will holds the alertBasedOn */
    private String alertBasedOn;

    /** This will holds the alertBasedOn desc*/
    private String alertBasedOnDesc;

    /** This will holds the sendAlertDaysCdDesc desc*/
    private String sendAlertDaysCdDesc; // added
    
    /** This will holds the sendAlertDaysCdDescription desc*/
    private String sendAlertDaysCdDescription; // added
   
    /** This will holds the sendAlertDays */
    private String sendAlertDaysCd;

    /** This will holds the afterOrBefore */
    private String afterOrBeforeCd;

    /** This will holds the template */
    private String template;

    /** This will holds the createDate of type String */
    private String createDateStr;

    /** This will holds the createDate */
    private Date createDate;

    /** This will holds the eventType */
    private String eventTypeCd;

    /** This will holds the eventType desc*/
    private String eventTypeCdDesc;

    /** used for dummy */
    private String dummy = "dummy";

    /** holds the Diagnosis code1 */
    private String diagnosisCode1;

    /** holds the Diagnosis code2 */
    private String diagnosisCode2;

    /** holds the Exam code1 */
    private String examCode1;

    /** holds the Exam code2 */
    private String examCode2;

    /** holds the provider/hospital */
    private String providerHospital;

    /** holds the caseEventSeqNum */
    private Integer caseEventSeqNum;
    
    /** holds Disposal date disable flag */
    private boolean dateDispositionFlag = false;
    
   
    
    

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

    /** This will holds the caseSK */
    private String caseSK;

    /** This will holds the caseTypeSK */
    private String caseTypeSK;

    /**
     * @return Returns the afterOrBeforeCd.
     */
    public String getAfterOrBeforeCd()
    {
        return afterOrBeforeCd;
    }

    /**
     * @param afterOrBeforeCd
     *            The afterOrBeforeCd to set.
     */
    public void setAfterOrBeforeCd(String afterOrBeforeCd)
    {
        this.afterOrBeforeCd = afterOrBeforeCd;
    }

    /**
     * @return Returns the eventTypeCd.
     */
    public String getEventTypeCd()
    {
        return eventTypeCd;
    }

    /**
     * @param eventTypeCd
     *            The eventTypeCd to set.
     */
    public void setEventTypeCd(String eventTypeCd)
    {
        this.eventTypeCd = eventTypeCd;
    }

    /**
     * @return Returns the sendAlertDaysCd.
     */
    public String getSendAlertDaysCd()
    {
        return sendAlertDaysCd;
    }

    /**
     * @param sendAlertDaysCd
     *            The sendAlertDaysCd to set.
     */
    public void setSendAlertDaysCd(String sendAlertDaysCd)
    {
        this.sendAlertDaysCd = sendAlertDaysCd;
    }

    /**
     * @return Returns the statusCd.
     */
    public String getStatusCd()
    {
        return statusCd;
    }

    /**
     * @param statusCd
     *            The statusCd to set.
     */
    public void setStatusCd(String statusCd)
    {
        this.statusCd = statusCd;
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
     * @return Returns the amPM.
     */
    public String getAmPM()
    {
        return amPM;
    }

    /**
     * @param amPM
     *            The amPM to set.
     */
    public void setAmPM(String amPM)
    {
        this.amPM = amPM;
    }

    /**
     * @return Returns the createDate.
     */
    public Date getCreateDate()
    {
        return createDate;
    }

    /**
     * @param createDate
     *            The createDate to set.
     */
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    /**
     * @return Returns the createDateStr.
     */
    public String getCreateDateStr()
    {
        return createDateStr;
    }

    /**
     * @param createDateStr
     *            The createDateStr to set.
     */
    public void setCreateDateStr(String createDateStr)
    {
        this.createDateStr = createDateStr;
    }

    /**
     * @return Returns the desc.
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc
     *            The desc to set.
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return Returns the dispositionDate.
     */
    public Date getDispositionDate()
    {
        return dispositionDate;
    }

    /**
     * @param dispositionDate
     *            The dispositionDate to set.
     */
    public void setDispositionDate(Date dispositionDate)
    {
        this.dispositionDate = dispositionDate;
    }

    /**
     * @return Returns the dispositionDateStr.
     */
    public String getDispositionDateStr()
    {
    	//COMMENTED FOR THE DEFECT ESPRD00667570
    	/*FacesContext context= FacesContext.getCurrentInstance();
        HttpSession httpSession =(HttpSession)context.getExternalContext().getSession(true);
  	 	String DisDate =(String)httpSession.getAttribute("DispositionDate");
  	 	System.err.println("---------DisDate "+DisDate);
  	 	if(DisDate!=null){
  	 		dispositionDateStr=DisDate;
  	 	}*/
    	//COMMENTED END FOR THE DEFECT ESPRD00667570
        return dispositionDateStr;
    }

    /**
     * @param dispositionDateStr
     *            The dispositionDateStr to set.
     */
    public void setDispositionDateStr(String dispositionDateStr)
    {
        this.dispositionDateStr = dispositionDateStr;
    }

    /**
     * @return Returns the eventDate.
     */
    public Date getEventDate()
    {
        return eventDate;
    }

    /**
     * @param eventDate
     *            The eventDate to set.
     */
    public void setEventDate(Date eventDate)
    {
        this.eventDate = eventDate;
    }

    /**
     * @return Returns the eventDateStr.
     */
    public String getEventDateStr()
    {
        return eventDateStr;
    }

    /**
     * @param eventDateStr
     *            The eventDateStr to set.
     */
    public void setEventDateStr(String eventDateStr)
    {
        this.eventDateStr = eventDateStr;
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
     * @return Returns the time.
     */
    public String getTime()
    {
        return time;
    }

    /**
     * @param time
     *            The time to set.
     */
    public void setTime(String time)
    {
        this.time = time;
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
     * @return Returns the diagnosisCode1.
     */
    public String getDiagnosisCode1()
    {
        return diagnosisCode1;
    }

    /**
     * @param diagnosisCode1 The diagnosisCode1 to set.
     */
    public void setDiagnosisCode1(String diagnosisCode1)
    {
        this.diagnosisCode1 = diagnosisCode1;
    }

    /**
     * @return Returns the diagnosisCode2.
     */
    public String getDiagnosisCode2()
    {
        return diagnosisCode2;
    }

    /**
     * @param diagnosisCode2 The diagnosisCode2 to set.
     */
    public void setDiagnosisCode2(String diagnosisCode2)
    {
        this.diagnosisCode2 = diagnosisCode2;
    }

    /**
     * @return Returns the examCode1.
     */
    public String getExamCode1()
    {
        return examCode1;
    }

    /**
     * @param examCode1 The examCode1 to set.
     */
    public void setExamCode1(String examCode1)
    {
        this.examCode1 = examCode1;
    }

    /**
     * @return Returns the examCode2.
     */
    public String getExamCode2()
    {
        return examCode2;
    }

    /**
     * @param examCode2 The examCode2 to set.
     */
    public void setExamCode2(String examCode2)
    {
        this.examCode2 = examCode2;
    }

    /**
     * @return Returns the providerHospital.
     */
    public String getProviderHospital()
    {
        return providerHospital;
    }

    /**
     * @param providerHospital The providerHospital to set.
     */
    public void setProviderHospital(String providerHospital)
    {
        this.providerHospital = providerHospital;
    }

    /**
     * @return Returns the alertBasedOnDesc.
     */
    public String getAlertBasedOnDesc()
    {
        return alertBasedOnDesc;
    }

    /**
     * @param alertBasedOnDesc The alertBasedOnDesc to set.
     */
    public void setAlertBasedOnDesc(String alertBasedOnDesc)
    {
        this.alertBasedOnDesc = alertBasedOnDesc;
    }

    /**
     * @return Returns the eventTypeCdDesc.
     */
    public String getEventTypeCdDesc()
    {
        return eventTypeCdDesc;
    }

    /**
     * @param eventTypeCdDesc The eventTypeCdDesc to set.
     */
    public void setEventTypeCdDesc(String eventTypeCdDesc)
    {
        this.eventTypeCdDesc = eventTypeCdDesc;
    }

    /**
     * @return Returns the notifyViaAlertName.
     */
    public String getNotifyViaAlertName()
    {
        return notifyViaAlertName;
    }

    /**
     * @param notifyViaAlertName The notifyViaAlertName to set.
     */
    public void setNotifyViaAlertName(String notifyViaAlertName)
    {
        this.notifyViaAlertName = notifyViaAlertName;
    }

    /**
     * @return Returns the statusCdDesc.
     */
    public String getStatusCdDesc()
    {
        return statusCdDesc;
    }

    /**
     * @param statusCdDesc The statusCdDesc to set.
     */
    public void setStatusCdDesc(String statusCdDesc)
    {
        this.statusCdDesc = statusCdDesc;
    }
	/**
	 * @return Returns the caseEventsDescription.
	 */
	public String getCaseEventsDescription() {
		return caseEventsDescription;
	}
	/**
	 * @param caseEventsDescription The caseEventsDescription to set.
	 */
	public void setCaseEventsDescription(String caseEventsDescription) {
		this.caseEventsDescription = caseEventsDescription;
	}
	/**
	 * @return Returns the alertBasedOnDescription.
	 */
	public String getAlertBasedOnDescription() {
		return alertBasedOnDescription;
	}
	/**
	 * @param alertBasedOnDescription The alertBasedOnDescription to set.
	 */
	public void setAlertBasedOnDescription(String alertBasedOnDescription) {
		this.alertBasedOnDescription = alertBasedOnDescription;
	}
	/**
	 * @return Returns the statusCdDescription.
	 */
	public String getStatusCdDescription() {
		return statusCdDescription;
	}
	/**
	 * @param statusCdDescription The statusCdDescription to set.
	 */
	public void setStatusCdDescription(String statusCdDescription) {
		this.statusCdDescription = statusCdDescription;
	}
	/**
	 * @return Returns the sendAlertDaysCdDesc.
	 */
	public String getSendAlertDaysCdDesc() {
		return sendAlertDaysCdDesc;
	}
	/**
	 * @param sendAlertDaysCdDesc The sendAlertDaysCdDesc to set.
	 */
	public void setSendAlertDaysCdDesc(String sendAlertDaysCdDesc) {
		this.sendAlertDaysCdDesc = sendAlertDaysCdDesc;
	}
	/**
	 * @return Returns the sendAlertDaysCdDescription.
	 */
	public String getSendAlertDaysCdDescription() {
		return sendAlertDaysCdDescription;
	}
	/**
	 * @param sendAlertDaysCdDescription The sendAlertDaysCdDescription to set.
	 */
	public void setSendAlertDaysCdDescription(String sendAlertDaysCdDescription) {
		this.sendAlertDaysCdDescription = sendAlertDaysCdDescription;
	}
	/**
	 * @return Returns the dateDispositionFlag.
	 */
	public boolean isDateDispositionFlag() {
		return dateDispositionFlag;
	}
	/**
	 * @param dateDispositionFlag The dateDispositionFlag to set.
	 */
	public void setDateDispositionFlag(boolean dateDispositionFlag) {
		this.dateDispositionFlag = dateDispositionFlag;
	}
	//CR_ESPRD00463663_LogCase_Changes_22MAY2010
	private String caseEventSeqNumStr = null;

	/**
	 * @return Returns the caseEventSeqNumStr.
	 */
	public String getCaseEventSeqNumStr() {
		return caseEventSeqNumStr;
	}
	/**
	 * @param caseEventSeqNumStr The caseEventSeqNumStr to set.
	 */
	public void setCaseEventSeqNumStr(String caseEventSeqNumStr) {
		this.caseEventSeqNumStr = caseEventSeqNumStr;
	}
	//EOF CR_ESPRD00463663_LogCase_Changes_22MAY2010
	private String caseEventAppintmentAuditAddedUserId = null;
	/**
	 * @return Returns the caseEventAppintmentAuditAddedUserId.
	 */
	public String getCaseEventAppintmentAuditAddedUserId() {
		return caseEventAppintmentAuditAddedUserId;
	}
	/**
	 * @param caseEventAppintmentAuditAddedUserId The caseEventAppintmentAuditAddedUserId to set.
	 */
	public void setCaseEventAppintmentAuditAddedUserId(
			String caseEventAppintmentAuditAddedUserId) {
		this.caseEventAppintmentAuditAddedUserId = caseEventAppintmentAuditAddedUserId;
	}
	private Date caseEventAppintmentAuditAddedTimestamp ;
	/**
	 * @return Returns the caseEventAppintmentAuditAddedUserId.
	 */
	public Date getCaseEventAppintmentAuditAddedTimestamp() {
		return caseEventAppintmentAuditAddedTimestamp;
	}
	/**
	 * @param caseEventAppintmentAuditAddedUserId The caseEventAppintmentAuditAddedUserId to set.
	 */
	public void setCaseEventAppintmentAuditAddedTimestamp(
			Date caseEventAppintmentAuditAddedTimestamp) {
		this.caseEventAppintmentAuditAddedTimestamp = caseEventAppintmentAuditAddedTimestamp;
	}
	private String providerCommonEntitySk =null;
	
	public String getProviderCommonEntitySk() {
		return providerCommonEntitySk;
	}
	/**
	 * @param caseEventAppintmentAuditAddedUserId The caseEventAppintmentAuditAddedUserId to set.
	 */
	public void setProviderCommonEntitySk(String providerCommonEntitySk) {
		this.providerCommonEntitySk = providerCommonEntitySk;
	}
	private int apptVersionNo;
	
	
	public int getApptVersionNo() {
		return apptVersionNo;
	}
	/**
	 * @param caseEventAppintmentAuditAddedUserId The caseEventAppintmentAuditAddedUserId to set.
	 */
	public void setApptVersionNo(int apptVersionNo) {
		this.apptVersionNo = apptVersionNo;
	}
	
}
