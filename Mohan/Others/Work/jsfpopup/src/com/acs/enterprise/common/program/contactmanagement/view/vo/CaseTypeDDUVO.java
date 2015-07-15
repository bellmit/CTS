/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * This class will be the CaseType DDU Persistent Value Object that are used in
 * the Log Case.
 * 
 * @author Wipro
 */
public class CaseTypeDDUVO
        extends EnterpriseBaseVO
{
    
    /** This will holds the applicationType */
    private String applicationTypeCd;

    /** This will holds the applicationType of type string */
    private String applicationDateStr;

    /** This will holds the applicationDate */
    private Date applicationDate;

    /** This will holds the substantiallyCompletedDate of type string */
    private String substantiallyCompletedDateStr;

    /** This will holds the substantiallyCompletedDate */
    private Date substantiallyCompletedDate;

    /** This will holds the authorizedRep */
    private String authorizedRepCd;

    /** This will holds the packetReceivedDate */
    private Date packetReceivedDate;

    /** This will holds the packetReceivedDate of type String */
    private String packetReceivedDateStr;

    /** This will holds the reviewRequired */
    private String reviewRequiredInd;
    
    /** This will holds the Review Initiated Date as String */
    private String reviewInitiatedDateStr;
    
    /** This will holds the Review Initiated Date as Date */
    private Date reviewInitiatedDate;
    
    /** This will holds the Scheduled Date of review as String */
    private String schDateOfReviewStr;
    
    /** This will holds the Scheduled Date of review as Date */
    private Date schDateOfReview;

    /** This will holds the receivedDate */
    private Date receivedDate;

    /** This will holds the receivedDate of type String */
    private String receivedDateStr;

    /** This will holds the receiptDate */
    private Date receiptDate;

    /** This will holds the receiptDate of type String */
    private String receiptDateStr;

    /** This will holds the evaluationType */
    private String evaluationTypeCd;

    /** This will holds the medicalDiagnosis */
    private String medicalDiagnosisCd;

    /** This will holds the psychiatricDiagnosis */
    private String psychiatricDiagnosisCd;

    /** This will holds the responseIndicator */
    private String responseIndicator;

    /** This will holds the decisionDate */
    private Date decisionDate;

    /** This will holds the decisionDate of type String */
    private String decisionDateStr;

    /** This will holds the approvedBeginDate */
    private Date approvedBeginDate;

    /** This will holds the approvedBeginDate of type String */
    private String approvedBeginDateStr;

    /** This will holds the appealDecisionDate */
    private Date appealDecisionDate;

    /** This will holds the appealDecisionDate of type String */
    private String appealDecisionDateStr;

    /** This will holds the levelOfCare */
    private String levelOfCareCd;

    /** This will holds the denialReason */
    private String denialReasonCd;

    /** This will holds the closeCode */
    private String closeCodeCd;
    
    /** holds the selected UnUsualCircumstances from the available list */
    private List unusualAvaiSelectedList = new ArrayList();
    
    /** holds the selected UnUsualCircumstances in the selected list */
    private List unusualSelectedList = new ArrayList();
    
    
    /**
     * @return Returns the reviewRequiredInd.
     */
    public String getReviewRequiredInd()
    {
        return reviewRequiredInd;
    }

    /**
     * @param reviewRequiredInd
     *            The reviewRequiredInd to set.
     */
    public void setReviewRequiredInd(String reviewRequiredInd)
    {
        this.reviewRequiredInd = reviewRequiredInd;
       
    }
    
    /**
     * @return Returns the appealDecisionDate.
     */
    public Date getAppealDecisionDate()
    {
        return appealDecisionDate;
    }

    /**
     * @param appealDecisionDate
     *            The appealDecisionDate to set.
     */
    public void setAppealDecisionDate(Date appealDecisionDate)
    {
        this.appealDecisionDate = appealDecisionDate;
    }

    /**
     * @return Returns the appealDecisionDateStr.
     */
    public String getAppealDecisionDateStr()
    {
        return appealDecisionDateStr;
    }

    /**
     * @param appealDecisionDateStr
     *            The appealDecisionDateStr to set.
     */
    public void setAppealDecisionDateStr(String appealDecisionDateStr)
    {
        this.appealDecisionDateStr = appealDecisionDateStr;
    }

    /**
     * @return Returns the applicationDate.
     */
    public Date getApplicationDate()
    {
        return applicationDate;
    }

    /**
     * @param applicationDate
     *            The applicationDate to set.
     */
    public void setApplicationDate(Date applicationDate)
    {
        this.applicationDate = applicationDate;
    }

    /**
     * @return Returns the applicationDateStr.
     */
    public String getApplicationDateStr()
    {
        return applicationDateStr;
    }

    /**
     * @param applicationDateStr
     *            The applicationDateStr to set.
     */
    public void setApplicationDateStr(String applicationDateStr)
    {
        this.applicationDateStr = applicationDateStr;
    }

    /**
     * @return Returns the applicationTypeCd.
     */
    public String getApplicationTypeCd()
    {
        return applicationTypeCd;
    }

    /**
     * @param applicationTypeCd
     *            The applicationTypeCd to set.
     */
    public void setApplicationTypeCd(String applicationTypeCd)
    {
        this.applicationTypeCd = applicationTypeCd;
    }

    /**
     * @return Returns the approvedBeginDate.
     */
    public Date getApprovedBeginDate()
    {
        return approvedBeginDate;
    }

    /**
     * @param approvedBeginDate
     *            The approvedBeginDate to set.
     */
    public void setApprovedBeginDate(Date approvedBeginDate)
    {
        this.approvedBeginDate = approvedBeginDate;
    }

    /**
     * @return Returns the approvedBeginDateStr.
     */
    public String getApprovedBeginDateStr()
    {
        return approvedBeginDateStr;
    }

    /**
     * @param approvedBeginDateStr
     *            The approvedBeginDateStr to set.
     */
    public void setApprovedBeginDateStr(String approvedBeginDateStr)
    {
        this.approvedBeginDateStr = approvedBeginDateStr;
    }

    /**
     * @return Returns the authorizedRepCd.
     */
    public String getAuthorizedRepCd()
    {
        return authorizedRepCd;
    }

    /**
     * @param authorizedRepCd
     *            The authorizedRepCd to set.
     */
    public void setAuthorizedRepCd(String authorizedRepCd)
    {
        this.authorizedRepCd = authorizedRepCd;
    }

    /**
     * @return Returns the closeCodeCd.
     */
    public String getCloseCodeCd()
    {
        return closeCodeCd;
    }

    /**
     * @param closeCodeCd
     *            The closeCodeCd to set.
     */
    public void setCloseCodeCd(String closeCodeCd)
    {
        this.closeCodeCd = closeCodeCd;
    }

    /**
     * @return Returns the decisionDate.
     */
    public Date getDecisionDate()
    {
        return decisionDate;
    }

    /**
     * @param decisionDate
     *            The decisionDate to set.
     */
    public void setDecisionDate(Date decisionDate)
    {
        this.decisionDate = decisionDate;
    }

    /**
     * @return Returns the decisionDateStr.
     */
    public String getDecisionDateStr()
    {
        return decisionDateStr;
    }

    /**
     * @param decisionDateStr
     *            The decisionDateStr to set.
     */
    public void setDecisionDateStr(String decisionDateStr)
    {
        this.decisionDateStr = decisionDateStr;
    }

    /**
     * @return Returns the denialReasonCd.
     */
    public String getDenialReasonCd()
    {
        return denialReasonCd;
    }

    /**
     * @param denialReasonCd
     *            The denialReasonCd to set.
     */
    public void setDenialReasonCd(String denialReasonCd)
    {
        this.denialReasonCd = denialReasonCd;
    }

    /**
     * @return Returns the evaluationTypeCd.
     */
    public String getEvaluationTypeCd()
    {
        return evaluationTypeCd;
    }

    /**
     * @param evaluationTypeCd
     *            The evaluationTypeCd to set.
     */
    public void setEvaluationTypeCd(String evaluationTypeCd)
    {
        this.evaluationTypeCd = evaluationTypeCd;
    }

    /**
     * @return Returns the levelOfCareCd.
     */
    public String getLevelOfCareCd()
    {
        return levelOfCareCd;
    }

    /**
     * @param levelOfCareCd
     *            The levelOfCareCd to set.
     */
    public void setLevelOfCareCd(String levelOfCareCd)
    {
        this.levelOfCareCd = levelOfCareCd;
    }

    /**
     * @return Returns the medicalDiagnosisCd.
     */
    public String getMedicalDiagnosisCd()
    {
        return medicalDiagnosisCd;
    }

    /**
     * @param medicalDiagnosisCd
     *            The medicalDiagnosisCd to set.
     */
    public void setMedicalDiagnosisCd(String medicalDiagnosisCd)
    {
        this.medicalDiagnosisCd = medicalDiagnosisCd;
    }

    /**
     * @return Returns the packetReceivedDate.
     */
    public Date getPacketReceivedDate()
    {
        return packetReceivedDate;
    }

    /**
     * @param packetReceivedDate
     *            The packetReceivedDate to set.
     */
    public void setPacketReceivedDate(Date packetReceivedDate)
    {
        this.packetReceivedDate = packetReceivedDate;
    }

    /**
     * @return Returns the packetReceivedDateStr.
     */
    public String getPacketReceivedDateStr()
    {
        return packetReceivedDateStr;
    }

    /**
     * @param packetReceivedDateStr
     *            The packetReceivedDateStr to set.
     */
    public void setPacketReceivedDateStr(String packetReceivedDateStr)
    {
        this.packetReceivedDateStr = packetReceivedDateStr;
    }

    /**
     * @return Returns the psychiatricDiagnosisCd.
     */
    public String getPsychiatricDiagnosisCd()
    {
        return psychiatricDiagnosisCd;
    }

    /**
     * @param psychiatricDiagnosisCd
     *            The psychiatricDiagnosisCd to set.
     */
    public void setPsychiatricDiagnosisCd(String psychiatricDiagnosisCd)
    {
        this.psychiatricDiagnosisCd = psychiatricDiagnosisCd;
    }

    /**
     * @return Returns the receiptDate.
     */
    public Date getReceiptDate()
    {
        return receiptDate;
    }

    /**
     * @param receiptDate
     *            The receiptDate to set.
     */
    public void setReceiptDate(Date receiptDate)
    {
        this.receiptDate = receiptDate;
    }

    /**
     * @return Returns the receiptDateStr.
     */
    public String getReceiptDateStr()
    {
        return receiptDateStr;
    }

    /**
     * @param receiptDateStr
     *            The receiptDateStr to set.
     */
    public void setReceiptDateStr(String receiptDateStr)
    {
        this.receiptDateStr = receiptDateStr;
    }

    /**
     * @return Returns the receivedDate.
     */
    public Date getReceivedDate()
    {
        return receivedDate;
    }

    /**
     * @param receivedDate
     *            The receivedDate to set.
     */
    public void setReceivedDate(Date receivedDate)
    {
        this.receivedDate = receivedDate;
    }

    /**
     * @return Returns the receivedDateStr.
     */
    public String getReceivedDateStr()
    {
        return receivedDateStr;
    }

    /**
     * @param receivedDateStr
     *            The receivedDateStr to set.
     */
    public void setReceivedDateStr(String receivedDateStr)
    {
        this.receivedDateStr = receivedDateStr;
    }

    /**
     * @return Returns the responseIndicator.
     */
    public String getResponseIndicator()
    {
        return responseIndicator;
    }

    /**
     * @param responseIndicator
     *            The responseIndicator to set.
     */
    public void setResponseIndicator(String responseIndicator)
    {
        this.responseIndicator = responseIndicator;
    }

    /**
     * @return Returns the substantiallyCompletedDate.
     */
    public Date getSubstantiallyCompletedDate()
    {
        return substantiallyCompletedDate;
    }

    /**
     * @param substantiallyCompletedDate
     *            The substantiallyCompletedDate to set.
     */
    public void setSubstantiallyCompletedDate(Date substantiallyCompletedDate)
    {
        this.substantiallyCompletedDate = substantiallyCompletedDate;
    }

    /**
     * @return Returns the substantiallyCompletedDateStr.
     */
    public String getSubstantiallyCompletedDateStr()
    {
        return substantiallyCompletedDateStr;
    }

    /**
     * @param substantiallyCompletedDateStr
     *            The substantiallyCompletedDateStr to set.
     */
    public void setSubstantiallyCompletedDateStr(
            String substantiallyCompletedDateStr)
    {
        this.substantiallyCompletedDateStr = substantiallyCompletedDateStr;
    }
    /**
     * @return Returns the reviewInitiatedDate.
     */
    public Date getReviewInitiatedDate()
    {
        return reviewInitiatedDate;
    }
    /**
     * @param reviewInitiatedDate The reviewInitiatedDate to set.
     */
    public void setReviewInitiatedDate(Date reviewInitiatedDate)
    {
        this.reviewInitiatedDate = reviewInitiatedDate;
    }
    /**
     * @return Returns the reviewInitiatedDateStr.
     */
    public String getReviewInitiatedDateStr()
    {
        return reviewInitiatedDateStr;
    }
    /**
     * @param reviewInitiatedDateStr The reviewInitiatedDateStr to set.
     */
    public void setReviewInitiatedDateStr(String reviewInitiatedDateStr)
    {
        this.reviewInitiatedDateStr = reviewInitiatedDateStr;
    }
    /**
     * @return Returns the schDateOfReview.
     */
    public Date getSchDateOfReview()
    {
        return schDateOfReview;
    }
    /**
     * @param schDateOfReview The schDateOfReview to set.
     */
    public void setSchDateOfReview(Date schDateOfReview)
    {
        this.schDateOfReview = schDateOfReview;
    }
    /**
     * @return Returns the schDateOfReviewStr.
     */
    public String getSchDateOfReviewStr()
    {
        return schDateOfReviewStr;
    }
    /**
     * @param schDateOfReviewStr The schDateOfReviewStr to set.
     */
    public void setSchDateOfReviewStr(String schDateOfReviewStr)
    {
        this.schDateOfReviewStr = schDateOfReviewStr;
    }
    /**
     * @return Returns the unusualAvaiSelectedList.
     */
    public List getUnusualAvaiSelectedList()
    {
        return unusualAvaiSelectedList;
    }
    /**
     * @param unusualAvaiSelectedList The unusualAvaiSelectedList to set.
     */
    public void setUnusualAvaiSelectedList(List unusualAvaiSelectedList)
    {
        this.unusualAvaiSelectedList = unusualAvaiSelectedList;
    }
    /**
     * @return Returns the unusualSelectedList.
     */
    public List getUnusualSelectedList()
    {
        return unusualSelectedList;
    }
    /**
     * @param unusualSelectedList The unusualSelectedList to set.
     */
    public void setUnusualSelectedList(List unusualSelectedList)
    {
        this.unusualSelectedList = unusualSelectedList;
    }
    // Added for the Defect ESPRD00784025 
    /** This will holds the Scheduled Date of review as String */
    private String schDateOfReviewStrForUpdateMode;
    /**
     * @return Returns the schDateOfReviewStrForUpdateMode.
     */
   public String getSchDateOfReviewStrForUpdateMode() {
		return schDateOfReviewStrForUpdateMode;
	}
    /**
    * @param unusualAvaiSelectedList The schDateOfReviewStrForUpdateMode to set.
    */
	public void setSchDateOfReviewStrForUpdateMode(
			String schDateOfReviewStrForUpdateMode) {
		this.schDateOfReviewStrForUpdateMode = schDateOfReviewStrForUpdateMode;
	}
    
}
