/*
 * Created on Jan 21, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;
import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * @author arcndnd TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class CaseRecordVO
        extends EnterpriseBaseVO
{
    /** used for dummy */
    private String dummy = "dummy";

    

    /** holds the Step */
    private String step;

    /** holds the Create By */
    private String createdBy;

    /** holds the Create By */
    private String assignedTo;

    /** holds the Create Date */
    private Date createdDate;

    /** holds the Create Date as String */
    private String createdDateStr;

    /** holds the reporting Unit */
    private String reportingUnit;

    /** holds the Business Unit */
    private String businessUnit;

    /** holds the work Unit */
    private String workUnit;

    /** holds the Status */
    private String status;

    /** holds the daysopened */
    private String daysopened;

    /** holds the priority */
    private String priority;

    /** holds the Case title */
    private String caseTitle;

    /** holds the LOB */
    private String lineOfBusiness;

    /** holds the weather Superwiser approval requried or not */
    private String supvrApdInd;

    /** holds the case Type */
    private String caseType;

    /** holds the Case Id */
    private String caseId;

    /** holds the caseSensitiveDataInd */
    private String caseSensitiveDataInd;

    /** holds the cmCaseEventCode */
    private String cmCaseEventCode;

    /**
     * @return Returns the caseTypeARSVO.
     */
    public CaseTypeARSVO getCaseTypeARSVO()
    {
        return caseTypeARSVO;
    }

    /**
     * @param caseTypeARSVO
     *            The caseTypeARSVO to set.
     */
    public void setCaseTypeARSVO(CaseTypeARSVO caseTypeARSVO)
    {
        this.caseTypeARSVO = caseTypeARSVO;
    }

    /**
     * @return Returns the caseTypeBCCPVO.
     */
    public CaseTypeBCCPVO getCaseTypeBCCPVO()
    {
        return caseTypeBCCPVO;
    }

    /**
     * @param caseTypeBCCPVO
     *            The caseTypeBCCPVO to set.
     */
    public void setCaseTypeBCCPVO(CaseTypeBCCPVO caseTypeBCCPVO)
    {
        this.caseTypeBCCPVO = caseTypeBCCPVO;
    }

    /**
     * @return Returns the caseTypeDDUVO.
     */
    public CaseTypeDDUVO getCaseTypeDDUVO()
    {
        return caseTypeDDUVO;
    }

    /**
     * @param caseTypeDDUVO
     *            The caseTypeDDUVO to set.
     */
    public void setCaseTypeDDUVO(CaseTypeDDUVO caseTypeDDUVO)
    {
        this.caseTypeDDUVO = caseTypeDDUVO;
    }

    /** holds the case Type BCCP VO */
    private CaseTypeBCCPVO caseTypeBCCPVO;

    /** holds the Case Type ARS VO */
    private CaseTypeARSVO caseTypeARSVO;

    /** holds the Case Type DDU VO */
    private CaseTypeDDUVO caseTypeDDUVO;

    /** holds list  of alertVO */
    private List alertVO;

    /** holds list  of caseStepsVO */
    private List caseStepsVO;

    /** holds list  of cMRoutingVO */
    private List cMRoutingVO;

    /** holds list  of caseEventsVO */
    private List caseEventsVO;

    /** holds list  of alertVO */
    private CaseDetailsVO caseDetailsVO;

    /** holds list  of attachmentVO*/
    private List attachmentVO;

   

    /**
     * @return Returns the alertVO.
     */
    public List getAlertVO()
    {
        return alertVO;
    }

    /**
     * @param alertVO
     *            The alertVO to set.
     */
    public void setAlertVO(List alertVO)
    {
        this.alertVO = alertVO;
    }

    /**
     * @return Returns the assignedTo.
     */
    public String getAssignedTo()
    {
        return assignedTo;
    }

    /**
     * @param assignedTo
     *            The assignedTo to set.
     */
    public void setAssignedTo(String assignedTo)
    {
        this.assignedTo = assignedTo;
    }

    /**
     * @return Returns the attachmentVO.
     */
    public List getAttachmentVO()
    {
        return attachmentVO;
    }

    /**
     * @param attachmentVO
     *            The attachmentVO to set.
     */
    public void setAttachmentVO(List attachmentVO)
    {
        this.attachmentVO = attachmentVO;
    }

    /**
     * @return Returns the businessUnit.
     */
    public String getBusinessUnit()
    {
        return businessUnit;
    }

    /**
     * @param businessUnit
     *            The businessUnit to set.
     */
    public void setBusinessUnit(String businessUnit)
    {
        this.businessUnit = businessUnit;
    }

    /**
     * @return Returns the caseDetailsVO.
     */
    public CaseDetailsVO getCaseDetailsVO()
    {
        return caseDetailsVO;
    }

    /**
     * @param caseDetailsVO
     *            The caseDetailsVO to set.
     */
    public void setCaseDetailsVO(CaseDetailsVO caseDetailsVO)
    {
        this.caseDetailsVO = caseDetailsVO;
    }

    /**
     * @return Returns the caseEventsVO.
     */
    public List getCaseEventsVO()
    {
        return caseEventsVO;
    }

    /**
     * @param caseEventsVO
     *            The caseEventsVO to set.
     */
    public void setCaseEventsVO(List caseEventsVO)
    {
        this.caseEventsVO = caseEventsVO;
    }

    /**
     * @return Returns the caseId.
     */
    public String getCaseId()
    {
        return caseId;
    }

    /**
     * @param caseId
     *            The caseId to set.
     */
    public void setCaseId(String caseId)
    {
        this.caseId = caseId;
    }

    /**
     * @return Returns the caseSensitiveDataInd.
     */
    public String getCaseSensitiveDataInd()
    {
        return caseSensitiveDataInd;
    }

    /**
     * @param caseSensitiveDataInd
     *            The caseSensitiveDataInd to set.
     */
    public void setCaseSensitiveDataInd(String caseSensitiveDataInd)
    {
        this.caseSensitiveDataInd = caseSensitiveDataInd;
    }

    /**
     * @return Returns the caseStepsVO.
     */
    public List getCaseStepsVO()
    {
        return caseStepsVO;
    }

    /**
     * @param caseStepsVO
     *            The caseStepsVO to set.
     */
    public void setCaseStepsVO(List caseStepsVO)
    {
        this.caseStepsVO = caseStepsVO;
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
     * @return Returns the cMRoutingVO.
     */
    public List getCMRoutingVO()
    {
        return cMRoutingVO;
    }

    /**
     * @param routingVO
     *            The cMRoutingVO to set.
     */
    public void setCMRoutingVO(List routingVO)
    {
        cMRoutingVO = routingVO;
    }

    /**
     * @return Returns the createdBy.
     */
    public String getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @param createdBy
     *            The createdBy to set.
     */
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
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
     * @return Returns the daysopened.
     */
    public String getDaysopened()
    {
        return daysopened;
    }

    /**
     * @param daysopened
     *            The daysopened to set.
     */
    public void setDaysopened(String daysopened)
    {
        this.daysopened = daysopened;
    }

    /**
     * @return Returns the lineOfBusiness.
     */
    public String getLineOfBusiness()
    {
        return lineOfBusiness;
    }

    /**
     * @param lineOfBusiness
     *            The lineOfBusiness to set.
     */
    public void setLineOfBusiness(String lineOfBusiness)
    {
        this.lineOfBusiness = lineOfBusiness;
    }

    /**
     * @return Returns the priority.
     */
    public String getPriority()
    {
        return priority;
    }

    /**
     * @param priority
     *            The priority to set.
     */
    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    /**
     * @return Returns the reportingUnit.
     */
    public String getReportingUnit()
    {
        return reportingUnit;
    }

    /**
     * @param reportingUnit
     *            The reportingUnit to set.
     */
    public void setReportingUnit(String reportingUnit)
    {
        this.reportingUnit = reportingUnit;
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
     * @return Returns the step.
     */
    public String getStep()
    {
        return step;
    }

    /**
     * @param step The step to set.
     */
    public void setStep(String step)
    {
        this.step = step;
    }

    /**
     * @return Returns the supvrApdInd.
     */
    public String getSupvrApdInd()
    {
        return supvrApdInd;
    }

    /**
     * @param supvrApdInd The supvrApdInd to set.
     */
    public void setSupvrApdInd(String supvrApdInd)
    {
        this.supvrApdInd = supvrApdInd;
    }

    /**
     * @return Returns the workUnit.
     */
    public String getWorkUnit()
    {
        return workUnit;
    }

    /**
     * @param workUnit The workUnit to set.
     */
    public void setWorkUnit(String workUnit)
    {
        this.workUnit = workUnit;
    }

    /**
     * @return Returns the cmCaseEventCode.
     */
    public String getCmCaseEventCode()
    {
        return cmCaseEventCode;
    }

    /**
     * @param cmCaseEventCode The cmCaseEventCode to set.
     */
    public void setCmCaseEventCode(String cmCaseEventCode)
    {
        this.cmCaseEventCode = cmCaseEventCode;
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
}
