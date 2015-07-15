/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;


/**
 * This class will be the Case Details Persistent Value Object that are used in
 * the Log Case.
 * 
 * @author Wipro
 */
public class CaseDetailsVO  extends AuditaleEnterpriseBaseVO //CR_ESPRD00373565_LogCase_04AUG2010
        //extends EnterpriseBaseVO
{
       
    
    /** holds the CaseSK */
    private Long caseSK;

    /** holds the Step */
    private String step;

    /** holds the Create By */
    private String createdBy;

    /** holds the CreatedBy SK */
    private String createdBySK;

    /** holds the AssignedTworkUnitSK */
    private String assignedToWorkUnitSK;

    /** holds the Create By */
    private String assignedTo;

    /** holds the Create Date */
    private Date createdDate;

    /** holds the Create Date as String */
    private String createdDateStr;

    /** holds the reporting Unit */
    private String reportingUnit;

    /** holds the Reporting UnitSK */
    private Long reportingUnitSK;

    /** holds the Business Unit */
    private String businessUnit;

    /** holds the businessUnitSK */
    private Long businessUnitSK;

    /** holds the work Unit */
    private String workUnit;

    /** holds the workUnitSK */
    private Long workUnitSK;

    /** holds the Status */
    private String status;
    
    /** holds the status date */
    private Date statusDate;
    
    /** holds the previous status */
    private String previousStatus;

    /** holds the daysopened */
    private String daysopened;

    /** holds the priority */
    private String priority;

    /** holds the Case title */
    private String caseTitle;

    /** holds the LOB */
    private String lineOfBusiness;

    /** holds the weather Superwiser approval requried or not */
    private boolean supvrRewInd;
    
    /** holds the workunit SK of department */
    private String deptSuperWorkUnitSK;

    /** holds the case Type */
    private String caseType;

    /** holds the list of inqAbtEntityes */
    private List inqAbtEntityList = new ArrayList(); 
   
    /** Holds noteSet value */
    private String noteSet;
    
    /** holds the caseType businessUnitCode */
    private String caseTypeBusinessUnitTypeCode;
    
    
//  Infinite Added for Defect ESPRD00329300
    
    /** holds the Case Type MemberAppealVO */
    private CaseTypeMemberAppealVO caseTypeMemberAppealVO = new CaseTypeMemberAppealVO();
 
   
    /* Infinite added for UC-PGM-CRM-018.10, CR 2401
	 * start Here
	 */
  
    /** holds the Case Type ARS VO */
    private CaseTypeARSVO caseTypeARSVO = new CaseTypeARSVO();

    /** holds the Case Type NFQA VO */
    private CaseTypeNFQAVO  caseTypeNFQAVO= new CaseTypeNFQAVO();
    
     /** holds the case Type Field Audit VO */
    private CaseTypeFieldAuditVO caseTypeFieldAuditVO = new CaseTypeFieldAuditVO();
    
     /** holds the case Type Facility Census Submission VO */
    private CaseTypeFacilityCensusSubmissionVO caseTypeFacilityCensusSubmissionVO = new CaseTypeFacilityCensusSubmissionVO();
 
    /** holds the case Type FCR VO */
    private CaseTypeFCRVO caseTypeFCRVO = new CaseTypeFCRVO();
    
    /** holds the case Type  NonARS VO */
    private CaseTypeNonARSFieldVO caseTypeNonARSFieldVO = new CaseTypeNonARSFieldVO();
    
    /** holds the case Type New ARS VO */
    private CaseTypeNewARSFieldVO caseTypeNewARSFieldVO = new CaseTypeNewARSFieldVO();
    
    /** holds the case Type CaseType FPPRR VO */
    private CaseTypeFPPRRVO caseTypeFPPRRVO = new CaseTypeFPPRRVO();

       
    /** holds the case Type Appeal Request VO */
    private CaseTypeAppealRequestVO caseTypeAppealRequestVO = new CaseTypeAppealRequestVO();
    
    
    /** holds the case Type QuarterlyMQIPReturns VO */
    private caseTypeQuarterlyMQIPReturnsVO caseTypeQuarterlyMQIPReturnsVO = new caseTypeQuarterlyMQIPReturnsVO();

    
    /** holds the case Type CaseTypeCreditBalance VO */
    private CaseTypeCreditBalanceVO caseTypeCreditBalanceVO = new CaseTypeCreditBalanceVO();
    
    
    /** holds the Case Type AcuityRateSetting VO */
    private CaseTypeAcuityRateSettingVO caseTypeAcuityRateSettingVO = new CaseTypeAcuityRateSettingVO();

    /** holds the Case Type HomeOfficeCostReporting VO */
    private CaseTypeHomeOfficeCostReportingVO caseTypeHomeOfficeCostReportingVO = new CaseTypeHomeOfficeCostReportingVO();
    
    /**
     * 
     */
 
    private List associatedCrList = new ArrayList();
       
    /**
     * 
     */
    private List associatedCaseList = new ArrayList();
    
    /** End Here **/
    
    /** holds the daysopened */
    private String closedDays;    
    
    
    /* Infinite added for UC-PGM-CRM-018.10, CR 2401
	 * start Here
	 */
    
    
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
	 * @return Returns the caseTypeAcuityRateSettingVO.
	 */
	public CaseTypeAcuityRateSettingVO getCaseTypeAcuityRateSettingVO() {
		return caseTypeAcuityRateSettingVO;
	}
	/**
	 * @param caseTypeAcuityRateSettingVO The caseTypeAcuityRateSettingVO to set.
	 */
	public void setCaseTypeAcuityRateSettingVO(
			CaseTypeAcuityRateSettingVO caseTypeAcuityRateSettingVO) {
		this.caseTypeAcuityRateSettingVO = caseTypeAcuityRateSettingVO;
	}
	
	/**
	 * @return Returns the caseTypeNFQAVO.
	 */
	public CaseTypeNFQAVO getCaseTypeNFQAVO() {
		return caseTypeNFQAVO;
	}
	/**
	 * @param caseTypeNFQAVO The caseTypeNFQAVO to set.
	 */
	public void setCaseTypeNFQAVO(CaseTypeNFQAVO caseTypeNFQAVO) {
		this.caseTypeNFQAVO = caseTypeNFQAVO;
	}
	
	/**
	 * @return Returns the caseTypeAppealRequestVO.
	 */
	public CaseTypeAppealRequestVO getCaseTypeAppealRequestVO() {
		return caseTypeAppealRequestVO;
	}
	/**
	 * @param caseTypeAppealRequestVO The caseTypeAppealRequestVO to set.
	 */
	public void setCaseTypeAppealRequestVO(
			CaseTypeAppealRequestVO caseTypeAppealRequestVO) {
		this.caseTypeAppealRequestVO = caseTypeAppealRequestVO;
	}
	
		
	/**
	 * @return Returns the caseTypeFCRVO.
	 */
	public CaseTypeFCRVO getCaseTypeFCRVO() {
		return caseTypeFCRVO;
	}
	/**
	 * @param caseTypeFCRVO The caseTypeFCRVO to set.
	 */
	public void setCaseTypeFCRVO(CaseTypeFCRVO caseTypeFCRVO) {
		this.caseTypeFCRVO = caseTypeFCRVO;
	}
	/**
	 * @return Returns the caseTypeFacilityCensusSubmissionVO.
	 */
	public CaseTypeFacilityCensusSubmissionVO getCaseTypeFacilityCensusSubmissionVO() {
		return caseTypeFacilityCensusSubmissionVO;
	}
	/**
	 * @param caseTypeFacilityCensusSubmissionVO The caseTypeFacilityCensusSubmissionVO to set.
	 */
	public void setCaseTypeFacilityCensusSubmissionVO(
			CaseTypeFacilityCensusSubmissionVO caseTypeFacilityCensusSubmissionVO) {
		this.caseTypeFacilityCensusSubmissionVO = caseTypeFacilityCensusSubmissionVO;
	}
	
	/**
	 * @return Returns the caseTypeNewARSFieldVO.
	 */
	public CaseTypeNewARSFieldVO getCaseTypeNewARSFieldVO() {
		return caseTypeNewARSFieldVO;
	}
	/**
	 * @param caseTypeNewARSFieldVO The caseTypeNewARSFieldVO to set.
	 */
	public void setCaseTypeNewARSFieldVO(
			CaseTypeNewARSFieldVO caseTypeNewARSFieldVO) {
		this.caseTypeNewARSFieldVO = caseTypeNewARSFieldVO;
	}

	/**
	 * @return Returns the caseTypeFieldAuditVO.
	 */
	public CaseTypeFieldAuditVO getCaseTypeFieldAuditVO() {
		return caseTypeFieldAuditVO;
	}
	/**
	 * @param caseTypeFieldAuditVO The caseTypeFieldAuditVO to set.
	 */
	public void setCaseTypeFieldAuditVO(
			CaseTypeFieldAuditVO caseTypeFieldAuditVO) {
		this.caseTypeFieldAuditVO = caseTypeFieldAuditVO;
	}
	
	
	
	
	/**
	 * @return Returns the caseTypeHomeOfficeCostReportingVO.
	 */
	public CaseTypeHomeOfficeCostReportingVO getCaseTypeHomeOfficeCostReportingVO() {
		return caseTypeHomeOfficeCostReportingVO;
	}
	/**
	 * @param caseTypeHomeOfficeCostReportingVO The caseTypeHomeOfficeCostReportingVO to set.
	 */
	public void setCaseTypeHomeOfficeCostReportingVO(
			CaseTypeHomeOfficeCostReportingVO caseTypeHomeOfficeCostReportingVO) {
		this.caseTypeHomeOfficeCostReportingVO = caseTypeHomeOfficeCostReportingVO;
	}
		
	/**
	 * @return Returns the caseTypeFPPRRVO.
	 */
	public CaseTypeFPPRRVO getCaseTypeFPPRRVO() {
		return caseTypeFPPRRVO;
	}
	/**
	 * @param caseTypeFPPRRVO The caseTypeFPPRRVO to set.
	 */
	public void setCaseTypeFPPRRVO(CaseTypeFPPRRVO caseTypeFPPRRVO) {
		this.caseTypeFPPRRVO = caseTypeFPPRRVO;
	}
	/**
	 * @return Returns the caseTypeCreditBalanceVO.
	 */
	public CaseTypeCreditBalanceVO getCaseTypeCreditBalanceVO() {
		return caseTypeCreditBalanceVO;
	}
	/**
	 * @param caseTypeCreditBalanceVO The caseTypeCreditBalanceVO to set.
	 */
	public void setCaseTypeCreditBalanceVO(
			CaseTypeCreditBalanceVO caseTypeCreditBalanceVO) {
		this.caseTypeCreditBalanceVO = caseTypeCreditBalanceVO;
	}
	/**
	 * @return Returns the caseTypeNonARSFieldVO.
	 */
	public CaseTypeNonARSFieldVO getCaseTypeNonARSFieldVO() {
		return caseTypeNonARSFieldVO;
	}
	/**
	 * @param caseTypeNonARSFieldVO The caseTypeNonARSFieldVO to set.
	 */
	public void setCaseTypeNonARSFieldVO(
			CaseTypeNonARSFieldVO caseTypeNonARSFieldVO) {
		this.caseTypeNonARSFieldVO = caseTypeNonARSFieldVO;
	}
	/**
	 * @return Returns the caseTypeQuarterlyMQIPReturnsVO.
	 */
	public caseTypeQuarterlyMQIPReturnsVO getCaseTypeQuarterlyMQIPReturnsVO() {
		return caseTypeQuarterlyMQIPReturnsVO;
	}
	/**
	 * @param caseTypeQuarterlyMQIPReturnsVO The caseTypeQuarterlyMQIPReturnsVO to set.
	 */
	public void setCaseTypeQuarterlyMQIPReturnsVO(
			caseTypeQuarterlyMQIPReturnsVO caseTypeQuarterlyMQIPReturnsVO) {
		this.caseTypeQuarterlyMQIPReturnsVO = caseTypeQuarterlyMQIPReturnsVO;
	}

	
	/** End Here **/
	
	
	
	
   /** holds the case Type BCCP VO */
    private CaseTypeBCCPVO caseTypeBCCPVO = new CaseTypeBCCPVO();

   
    /** holds the Case Type DDU VO */
    private CaseTypeDDUVO caseTypeDDUVO = new CaseTypeDDUVO();

    /** holds the Case Id */
    private String caseId;

    /** holds the caseSensitiveDataInd */
    private String caseSensitiveDataInd;
    /**
     * Holds the reference to listOfLettersAndResponses.
     */
    private List listOfLettersAndResponses = new ArrayList();
    
    
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
     * @return Returns the step.
     */
    public String getStep()
    {
        return step;
    }

    /**
     * @param step
     *            The step to set.
     */
    public void setStep(String step)
    {
        this.step = step;
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
     * @return Returns the workUnit.
     */
    public String getWorkUnit()
    {
        return workUnit;
    }

    /**
     * @param workUnit
     *            The workUnit to set.
     */
    public void setWorkUnit(String workUnit)
    {
        this.workUnit = workUnit;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
    	// Modified for BRC BR4260,CON0185.0001.01(DDU Case Status:  Default = Pending)
    	FacesContext context= FacesContext.getCurrentInstance();
        HttpSession httpSession =(HttpSession)context.getExternalContext().getSession(true);
    	String dduStatus=(String)httpSession.getAttribute("DDUstatus");
    	if(dduStatus!=null && dduStatus.equals("PE")){
    		this.status = dduStatus;
    		httpSession.removeAttribute("DDUstatus");
    	}else if(dduStatus!=null && dduStatus.equals("PD")){
    		this.status = dduStatus;
    		httpSession.removeAttribute("DDUstatus");
    	}
    	else if(dduStatus!=null && dduStatus.equals("OTH")){
    		this.status = "";
    		httpSession.removeAttribute("DDUstatus");
    	}
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
     * @return Returns the createdBySK.
     */
    public String getCreatedBySK()
    {
        return createdBySK;
    }

    /**
     * @param createdBySK
     *            The createdBySK to set.
     */
    public void setCreatedBySK(String createdBySK)
    {
        this.createdBySK = createdBySK;
    }

    /**
     * @return Returns the assignedToWorkUnitSK.
     */
    public String getAssignedToWorkUnitSK()
    {
        return assignedToWorkUnitSK;
    }

    /**
     * @param assignedToWorkUnitSK
     *            The assignedToWorkUnitSK to set.
     */
    public void setAssignedToWorkUnitSK(String assignedToWorkUnitSK)
    {
        this.assignedToWorkUnitSK = assignedToWorkUnitSK;
    }

    /**
     * @return Returns the businessUnitSK.
     */
    public Long getBusinessUnitSK()
    {
        return businessUnitSK;
    }

    /**
     * @param businessUnitSK
     *            The businessUnitSK to set.
     */
    public void setBusinessUnitSK(Long businessUnitSK)
    {
        this.businessUnitSK = businessUnitSK;
    }

    /**
     * @return Returns the reportingUnitSK.
     */
    public Long getReportingUnitSK()
    {
        return reportingUnitSK;
    }

    /**
     * @param reportingUnitSK
     *            The reportingUnitSK to set.
     */
    public void setReportingUnitSK(Long reportingUnitSK)
    {
        this.reportingUnitSK = reportingUnitSK;
    }

    /**
     * @return Returns the workUnitSK.
     */
    public Long getWorkUnitSK()
    {
        return workUnitSK;
    }

    /**
     * @param workUnitSK
     *            The workUnitSK to set.
     */
    public void setWorkUnitSK(Long workUnitSK)
    {
        this.workUnitSK = workUnitSK;
    }

    /**
     * @return Returns the caseTypeBusinessUnitTypeCode.
     */
    public String getCaseTypeBusinessUnitTypeCode()
    {
        return caseTypeBusinessUnitTypeCode;
    }

    /**
     * @param caseTypeBusinessUnitTypeCode
     *            The caseTypeBusinessUnitTypeCode to set.
     */
    public void setCaseTypeBusinessUnitTypeCode(
            String caseTypeBusinessUnitTypeCode)
    {
        this.caseTypeBusinessUnitTypeCode = caseTypeBusinessUnitTypeCode;
    }

    /**
     * @return Returns the caseSK.
     */
    public Long getCaseSK()
    {
        return caseSK;
    }

    /**
     * @param caseSK
     *            The caseSK to set.
     */
    public void setCaseSK(Long caseSK)
    {
        this.caseSK = caseSK;
    }

    /**
     * @return Returns the noteSet.
     */
    public String getNoteSet()
    {
        return noteSet;
    }

    /**
     * @param noteSet The noteSet to set.
     */
    public void setNoteSet(String noteSet)
    {
        this.noteSet = noteSet;
    }
    /**
     * @return Returns the inqAbtEntityList.
     */
    public List getInqAbtEntityList()
    {
        return inqAbtEntityList;
    }
    /**
     * @param inqAbtEntityList The inqAbtEntityList to set.
     */
    public void setInqAbtEntityList(List inqAbtEntityList)
    {
        this.inqAbtEntityList = inqAbtEntityList;
    }
    /**
     * @return Returns the previousStatus.
     */
    public String getPreviousStatus()
    {
        return previousStatus;
    }
    /**
     * @param previousStatus The previousStatus to set.
     */
    public void setPreviousStatus(String previousStatus)
    {
        this.previousStatus = previousStatus;
    }
    /**
     * @return Returns the deptSuperWorkUnitSK.
     */
    public String getDeptSuperWorkUnitSK()
    {
        return deptSuperWorkUnitSK;
    }
    /**
     * @param deptSuperWorkUnitSK The deptSuperWorkUnitSK to set.
     */
    public void setDeptSuperWorkUnitSK(String deptSuperWorkUnitSK)
    {
        this.deptSuperWorkUnitSK = deptSuperWorkUnitSK;
    }
    /**
     * @return Returns the listOfLettersAndResponses.
     */
    public List getListOfLettersAndResponses()
    {
        return listOfLettersAndResponses;
    }
    /**
     * @param listOfLettersAndResponses The listOfLettersAndResponses to set.
     */
    public void setListOfLettersAndResponses(List listOfLettersAndResponses)
    {
        this.listOfLettersAndResponses = listOfLettersAndResponses;
    }
    
    /**
     * @return Returns the supvrRewInd.
     */
    public boolean isSupvrRewInd()
    {
        return supvrRewInd;
    }
    /**
     * @param supvrRewInd The supvrRewInd to set.
     */
    public void setSupvrRewInd(boolean supvrRewInd)
    {
        this.supvrRewInd = supvrRewInd;
    }
    /**
     * @return Returns the statusDate.
     */
    public Date getStatusDate()
    {
        return statusDate;
    }
    /**
     * @param statusDate The statusDate to set.
     */
    public void setStatusDate(Date statusDate)
    {
        this.statusDate = statusDate;
    }
    
    
    //Added by ICS GAP -6493
    
    /** holds the edmsWorkunit level */
    private String edmsWorkunitLevel;
    
    /** holds the edmsWorkunit level */
    private String edmsDocType;
   
	/**
	 * @return Returns the edmsDocType.
	 */
	public String getEdmsDocType() {
		return edmsDocType;
	}
	/**
	 * @param edmsDocType The edmsDocType to set.
	 */
	public void setEdmsDocType(String edmsDocType) {
		this.edmsDocType = edmsDocType;
	}
	/**
	 * @return Returns the edmsWorkunitLevel.
	 */
	public String getEdmsWorkunitLevel() {
		return edmsWorkunitLevel;
	}
	/**
	 * @param edmsWorkunitLevel The edmsWorkunitLevel to set.
	 */
	public void setEdmsWorkunitLevel(String edmsWorkunitLevel) {
		this.edmsWorkunitLevel = edmsWorkunitLevel;
	}
	
	//	END by ICS GAP -6493
		
		
	    
	/**
	 * @return Returns the caseTypeMemberAppealVO.
	 */
	public CaseTypeMemberAppealVO getCaseTypeMemberAppealVO() {
		return caseTypeMemberAppealVO;
	}
	/**
	 * @param caseTypeMemberAppealVO The caseTypeMemberAppealVO to set.
	 */
	public void setCaseTypeMemberAppealVO(
			CaseTypeMemberAppealVO caseTypeMemberAppealVO) {
		this.caseTypeMemberAppealVO = caseTypeMemberAppealVO;
	}
	
	/**
	 * @return Returns the associatedCrList.
	 */
	public List getAssociatedCrList() {
		return associatedCrList;
	}
	/**
	 * @param associatedCrList The associatedCrList to set.
	 */
	public void setAssociatedCrList(List associatedCrList) {
		this.associatedCrList = associatedCrList;
	}
	/**
	 * @return Returns the associatedCaseList.
	 */
	public List getAssociatedCaseList() {
		return associatedCaseList;
	}
	/**
	 * @param associatedCaseList The associatedCaseList to set.
	 */
	public void setAssociatedCaseList(List associatedCaseList) {
		this.associatedCaseList = associatedCaseList;
	}
	
	
	/**
	 * @return Returns the closedDays.
	 */
	public String getClosedDays() {
		return closedDays;
	}
	/**
	 * @param closedDays The closedDays to set.
	 */
	public void setClosedDays(String closedDays) {
		this.closedDays = closedDays;
	}
	
	/** casestatusdate should update only
	 *  when user changes the case status.
	 * */
	private Date previousStatusDate;


	/**
	 * @return the previousStatusDate
	 */
	public Date getPreviousStatusDate() {
		return previousStatusDate;
	}

	/**
	 * @param previousStatusDate the previousStatusDate to set
	 */
	public void setPreviousStatusDate(Date previousStatusDate) {
		this.previousStatusDate = previousStatusDate;
	}
	
	
}
