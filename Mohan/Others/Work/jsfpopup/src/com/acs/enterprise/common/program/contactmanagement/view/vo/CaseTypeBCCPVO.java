/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.program.commonentities.view.vo.PhoneVO;

/**
 * This class will be the CaseType BCCP Persistent Data Object that are used in
 * the Log Case.
 * 
 * @author Wipro
 */
public class CaseTypeBCCPVO
        extends EnterpriseBaseVO
{
   
    /** This will holds the applicationDate */
    private Date applicationDate;

    /** This will holds the applicationDate of type string */
    private String applicationDateStr;

    /** This will holds the bccpID */
    private String bccpID;

    /** This will holds the contactPerson */
    private String contactPerson;

    /** This will holds the physicianOverseeingCare */
    private String physicianOverseeingCare;

    /** This will holds the enrollmentDate */
    private Date enrollmentDate;

    /** This will holds the enrollmentDate of type string */
    private String enrollmentDateStr;

    /** This will holds the agencySite */
    private String agencySite;

    /** This will holds the agencyCaseManager */
    private String agencyCaseManager;

    /** This will holds the agencyPhoneNumber */
    private String agencyPhoneNumber;

    /** This will holds the biopsyDate */
    private Date biopsyDate;

    /** This will holds the biopsyDate of type string */
    private String biopsyDateStr;

    /** This will holds the biopsyFindings */
    private String biopsyFindingsCd;

    /** This will holds the recommendation */
    private String recommendationCd;

    /** This will holds the stFollowUP */
    private String stFollowUPNum;

    /** This will holds the consultDate */
    private Date consultDate;

    /** This will holds the consultDate of type string */
    private String consultDateStr;

    /** This will holds the stage */
    private String finalStageCd;

    /** This will holds the metastatisArea */
    private String metastasisAreaCd;

    /** This will holds the unstagedReason */
    private String unstagedReasonCd;

    /** This will holds the tumorSize */
    private String tumorSizeNum;

    /** This will holds the nodesPositive */
    private String nodesPositiveNum;

    /** This will holds the treatmentStarted */
    private String treatmentStartedCd;

    /** This will holds the treatmentStartedDate */
    private Date treatmentStartDate;

    /** This will holds the treatmentStartDate of type String */
    private String treatmentStartDateStr;

    /** This will holds the chemoProjectEndDate */
    private Date chemoProjectedEndDate;

    /** This will holds the chemoProjectEndDate of type String */
    private String chemoProjectedEndDateStr;

    /** This will holds the caregiverName */
    private String caregiverName;

    /** This will holds the caregiverPhone */
    private String caregiverPhone;

    /** This will holds the form778Signed */
    private String form778SignedInd;
    
    /** used for dummy */
    private String dummy = "dummy";
    
    
    
   

    /**
     * @return Returns the agencyCaseManager.
     */
    public String getAgencyCaseManager()
    {
        return agencyCaseManager;
    }

    /**
     * @param agencyCaseManager
     *            The agencyCaseManager to set.
     */
    public void setAgencyCaseManager(String agencyCaseManager)
    {
        this.agencyCaseManager = agencyCaseManager;
    }

    /**
     * @return Returns the agencyPhoneNumber.
     */
    public String getAgencyPhoneNumber()
    {
        return agencyPhoneNumber;
    }

    /**
     * @param agencyPhoneNumber
     *            The agencyPhoneNumber to set.
     */
    public void setAgencyPhoneNumber(String agencyPhoneNumber)
    {
        this.agencyPhoneNumber = agencyPhoneNumber;
    }

    /**
     * @return Returns the agencySite.
     */
    public String getAgencySite()
    {
        return agencySite;
    }

    /**
     * @param agencySite
     *            The agencySite to set.
     */
    public void setAgencySite(String agencySite)
    {
        this.agencySite = agencySite;
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
     * @return Returns the bccpID.
     */
    public String getBccpID()
    {
        return bccpID;
    }

    /**
     * @param bccpID
     *            The bccpID to set.
     */
    public void setBccpID(String bccpID)
    {
        this.bccpID = bccpID;
    }

    /**
     * @return Returns the biopsyDate.
     */
    public Date getBiopsyDate()
    {
        return biopsyDate;
    }

    /**
     * @param biopsyDate
     *            The biopsyDate to set.
     */
    public void setBiopsyDate(Date biopsyDate)
    {
        this.biopsyDate = biopsyDate;
    }

    /**
     * @return Returns the biopsyDateStr.
     */
    public String getBiopsyDateStr()
    {
        return biopsyDateStr;
    }

    /**
     * @param biopsyDateStr
     *            The biopsyDateStr to set.
     */
    public void setBiopsyDateStr(String biopsyDateStr)
    {
        this.biopsyDateStr = biopsyDateStr;
    }

    /**
     * @return Returns the biopsyFindingsCd.
     */
    public String getBiopsyFindingsCd()
    {
        return biopsyFindingsCd;
    }

    /**
     * @param biopsyFindingsCd
     *            The biopsyFindingsCd to set.
     */
    public void setBiopsyFindingsCd(String biopsyFindingsCd)
    {
        this.biopsyFindingsCd = biopsyFindingsCd;
    }

    /**
     * @return Returns the caregiverName.
     */
    public String getCaregiverName()
    {
        return caregiverName;
    }

    /**
     * @param caregiverName
     *            The caregiverName to set.
     */
    public void setCaregiverName(String caregiverName)
    {
        this.caregiverName = caregiverName;
    }

    /**
     * @return Returns the caregiverPhone.
     */
    public String getCaregiverPhone()
    {
        return caregiverPhone;
    }

    /**
     * @param caregiverPhone
     *            The caregiverPhone to set.
     */
    public void setCaregiverPhone(String caregiverPhone)
    {
        this.caregiverPhone = caregiverPhone;
    }

    /**
     * @return Returns the chemoProjectedEndDate.
     */
    public Date getChemoProjectedEndDate()
    {
        return chemoProjectedEndDate;
    }

    /**
     * @param chemoProjectedEndDate
     *            The chemoProjectedEndDate to set.
     */
    public void setChemoProjectedEndDate(Date chemoProjectedEndDate)
    {
        this.chemoProjectedEndDate = chemoProjectedEndDate;
    }

    /**
     * @return Returns the chemoProjectedEndDateStr.
     */
    public String getChemoProjectedEndDateStr()
    {
        return chemoProjectedEndDateStr;
    }

    /**
     * @param chemoProjectedEndDateStr
     *            The chemoProjectedEndDateStr to set.
     */
    public void setChemoProjectedEndDateStr(String chemoProjectedEndDateStr)
    {
        this.chemoProjectedEndDateStr = chemoProjectedEndDateStr;
    }

    /**
     * @return Returns the consultDate.
     */
    public Date getConsultDate()
    {
        return consultDate;
    }

    /**
     * @param consultDate
     *            The consultDate to set.
     */
    public void setConsultDate(Date consultDate)
    {
        this.consultDate = consultDate;
    }

    /**
     * @return Returns the consultDateStr.
     */
    public String getConsultDateStr()
    {
        return consultDateStr;
    }

    /**
     * @param consultDateStr
     *            The consultDateStr to set.
     */
    public void setConsultDateStr(String consultDateStr)
    {
        this.consultDateStr = consultDateStr;
    }

    /**
     * @return Returns the contactPerson.
     */
    public String getContactPerson()
    {
        return contactPerson;
    }

    /**
     * @param contactPerson
     *            The contactPerson to set.
     */
    public void setContactPerson(String contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    /**
     * @return Returns the enrollmentDate.
     */
    public Date getEnrollmentDate()
    {
        return enrollmentDate;
    }

    /**
     * @param enrollmentDate
     *            The enrollmentDate to set.
     */
    public void setEnrollmentDate(Date enrollmentDate)
    {
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * @return Returns the enrollmentDateStr.
     */
    public String getEnrollmentDateStr()
    {
        return enrollmentDateStr;
    }

    /**
     * @param enrollmentDateStr
     *            The enrollmentDateStr to set.
     */
    public void setEnrollmentDateStr(String enrollmentDateStr)
    {
        this.enrollmentDateStr = enrollmentDateStr;
    }

    /**
     * @return Returns the finalStageCd.
     */
    public String getFinalStageCd()
    {
        return finalStageCd;
    }

    /**
     * @param finalStageCd
     *            The finalStageCd to set.
     */
    public void setFinalStageCd(String finalStageCd)
    {
        this.finalStageCd = finalStageCd;
    }

    /**
     * @return Returns the form778SignedInd.
     */
    public String getForm778SignedInd()
    {
        return form778SignedInd;
    }

    /**
     * @param form778SignedInd
     *            The form778SignedInd to set.
     */
    public void setForm778SignedInd(String form778SignedInd)
    {
        this.form778SignedInd = form778SignedInd;
    }

    /**
     * @return Returns the metastasisAreaCd.
     */
    public String getMetastasisAreaCd()
    {
        return metastasisAreaCd;
    }

    /**
     * @param metastasisAreaCd
     *            The metastasisAreaCd to set.
     */
    public void setMetastasisAreaCd(String metastasisAreaCd)
    {
        this.metastasisAreaCd = metastasisAreaCd;
    }

    /**
     * @return Returns the nodesPositiveNum.
     */
    public String getNodesPositiveNum()
    {
        return nodesPositiveNum;
    }

    /**
     * @param nodesPositiveNum
     *            The nodesPositiveNum to set.
     */
    public void setNodesPositiveNum(String nodesPositiveNum)
    {
        this.nodesPositiveNum = nodesPositiveNum;
    }

    /**
     * @return Returns the physicianOverseeingCare.
     */
    public String getPhysicianOverseeingCare()
    {
        return physicianOverseeingCare;
    }

    /**
     * @param physicianOverseeingCare
     *            The physicianOverseeingCare to set.
     */
    public void setPhysicianOverseeingCare(String physicianOverseeingCare)
    {
        this.physicianOverseeingCare = physicianOverseeingCare;
    }

    /**
     * @return Returns the recommendationCd.
     */
    public String getRecommendationCd()
    {
        return recommendationCd;
    }

    /**
     * @param recommendationCd
     *            The recommendationCd to set.
     */
    public void setRecommendationCd(String recommendationCd)
    {
        this.recommendationCd = recommendationCd;
    }

    /**
     * @return Returns the treatmentStartDate.
     */
    public Date getTreatmentStartDate()
    {
        return treatmentStartDate;
    }

    /**
     * @param treatmentStartDate
     *            The treatmentStartDate to set.
     */
    public void setTreatmentStartDate(Date treatmentStartDate)
    {
        this.treatmentStartDate = treatmentStartDate;
    }

    /**
     * @return Returns the treatmentStartDateStr.
     */
    public String getTreatmentStartDateStr()
    {
        return treatmentStartDateStr;
    }

    /**
     * @param treatmentStartDateStr
     *            The treatmentStartDateStr to set.
     */
    public void setTreatmentStartDateStr(String treatmentStartDateStr)
    {
        this.treatmentStartDateStr = treatmentStartDateStr;
    }

    /**
     * @return Returns the treatmentStartedCd.
     */
    public String getTreatmentStartedCd()
    {
        return treatmentStartedCd;
    }

    /**
     * @param treatmentStartedCd
     *            The treatmentStartedCd to set.
     */
    public void setTreatmentStartedCd(String treatmentStartedCd)
    {
        this.treatmentStartedCd = treatmentStartedCd;
    }

    /**
     * @return Returns the tumorSizeNum.
     */
    public String getTumorSizeNum()
    {
        return tumorSizeNum;
    }

    /**
     * @param tumorSizeNum
     *            The tumorSizeNum to set.
     */
    public void setTumorSizeNum(String tumorSizeNum)
    {
        this.tumorSizeNum = tumorSizeNum;
    }

    /**
     * @return Returns the unstagedReasonCd.
     */
    public String getUnstagedReasonCd()
    {
        return unstagedReasonCd;
    }

    /**
     * @param unstagedReasonCd
     *            The unstagedReasonCd to set.
     */
    public void setUnstagedReasonCd(String unstagedReasonCd)
    {
        this.unstagedReasonCd = unstagedReasonCd;
    }

    /**
     * @return Returns the stFollowUPNum.
     */
    public String getStFollowUPNum()
    {
        return stFollowUPNum;
    }

    /**
     * @param stFollowUPNum
     *            The stFollowUPNum to set.
     */
    public void setStFollowUPNum(String stFollowUPNum)
    {
        this.stFollowUPNum = stFollowUPNum;
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
    private PhoneVO agencyPhoneVo = null;
    private PhoneVO careGiverPhoneVo = null;
	
	/**
	 * @return Returns the agencyPhoneVo.
	 */
	public PhoneVO getAgencyPhoneVo() {
		return agencyPhoneVo;
	}
	/**
	 * @param agencyPhoneVo The agencyPhoneVo to set.
	 */
	public void setAgencyPhoneVo(PhoneVO agencyPhoneVo) {
		this.agencyPhoneVo = agencyPhoneVo;
	}
	/**
	 * @return Returns the careGiverPhoneVo.
	 */
	public PhoneVO getCareGiverPhoneVo() {
		return careGiverPhoneVo;
	}
	/**
	 * @param careGiverPhoneVo The careGiverPhoneVo to set.
	 */
	public void setCareGiverPhoneVo(PhoneVO careGiverPhoneVo) {
		this.careGiverPhoneVo = careGiverPhoneVo;
	}
}
