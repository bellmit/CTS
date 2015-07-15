/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldValue;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CMLogCaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeARSVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeBCCPVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeDDUVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class contains all the validation methods used in LogCase
 * 
 * @author wipro
 */
public class LogCaseValidationHelper
{
    ContactManagementHelper helper = new ContactManagementHelper();

    
    private static final EnterpriseLogger log = EnterpriseLogFactory
    .getLogger(LogCaseValidationHelper.class);
    
	// Moved to ContactManagementConstants.java
    // public static final String CMLOGCASE_DATA_BEAN = "logCaseDataBean";
	
	private CMLogCaseDataBean logCaseDataBean;

    /**
     * This method will return the reference of the data bean.
     * 
     * @return relatedDataBean
     */
    
    public Object getDataBean(String dataBeanName)

    {
        FacesContext fc = FacesContext.getCurrentInstance();
        String valueBindingStr = "#{" + dataBeanName + "}";
        Object dataBeanObj = null;
        dataBeanObj = fc.getApplication().getVariableResolver()
                .resolveVariable(fc, dataBeanName);
        if (dataBeanObj == null)

        {

            dataBeanObj = fc.getApplication().createValueBinding(
                    valueBindingStr).getValue(fc);

        }
        return dataBeanObj;

    }
   

    /**
     * 
     * @param caseTypeBCCPVO
     * @return boolean
     */
    public boolean validateBCCP(CaseTypeBCCPVO caseTypeBCCPVO)
    {
        log.debug("Inside validateBCCP");
        boolean flag = false;
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getBccpID())
                && !ContactManagementHelper.validateNumeric(caseTypeBCCPVO
                        .getBccpID()))
        {

            flag = true;
            ContactManagementHelper.setErrorMessage(
                    MaintainContactManagementUIConstants.BCCP_ID_NUMERC,
                    MaintainContactManagementUIConstants.CASETYPE_BCCP_ID,
                    null, null);
        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getContactPerson())
                && !ContactManagementHelper.validateAlpha(caseTypeBCCPVO
                        .getContactPerson()))
        {
            flag = true;
            ContactManagementHelper.setErrorMessage(
                    MaintainContactManagementUIConstants.CONTACT_PERSON_CHAR,
                    MaintainContactManagementUIConstants.CONTACT_PERSON_ID,
                    null, null);

        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getApplicationDateStr()))
        {
            Date appliDate = helper.dateConverter(caseTypeBCCPVO
                    .getApplicationDateStr());
            if (appliDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_APPLICATION_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_APPLICATION_DATE,
                                null, null);
            }
            else
            {
                caseTypeBCCPVO.setApplicationDate(appliDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getEnrollmentDateStr()))
        {
            Date enrolDate = helper.dateConverter(caseTypeBCCPVO
                    .getEnrollmentDateStr());
            if (enrolDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_ENROLL_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_ENROLLMENT_DATE,
                                null, null);
            }
            else
            {
                caseTypeBCCPVO.setEnrollmentDate(enrolDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getAgencyCaseManager())
                && !ContactManagementHelper.validateAlpha(caseTypeBCCPVO
                        .getAgencyCaseManager()))
        {
            flag = true;
            ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.CASE_AGENCY_MANGER_INVALID,
                            MaintainContactManagementUIConstants.CASE_AGENCY_MANGER,
                            null, null);

        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getAgencyPhoneNumber())
                && !ContactManagementHelper.validateNumeric(caseTypeBCCPVO
                        .getAgencyPhoneNumber()))
        {
            flag = true;
            ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.CASE_AGENCY_PHNO_INVALID,
                            MaintainContactManagementUIConstants.CASE_AGENCY_PHNO,
                            null, null);

        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getBiopsyDateStr()))
        {
            Date biopDate = helper.dateConverter(caseTypeBCCPVO
                    .getBiopsyDateStr());
            if (biopDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_BIOPSY_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_BIOPSY_DATE,
                                null, null);
            }
            else
            {
                caseTypeBCCPVO.setBiopsyDate(biopDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getStFollowUPNum())
                && !ContactManagementHelper.validateNumeric(caseTypeBCCPVO
                        .getStFollowUPNum()))
        {

            flag = true;
            ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.CASE_ST_FOLLOWUP_INVALID,
                            MaintainContactManagementUIConstants.CASE_ST_FOLLOWUP,
                            null, null);
        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getConsultDateStr()))
        {
            Date consulDate = helper.dateConverter(caseTypeBCCPVO
                    .getConsultDateStr());
            if (consulDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_CONSULT_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_CONSULT_DATE,
                                null, null);
            }
            else
            {
                caseTypeBCCPVO.setConsultDate(consulDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getMetastasisAreaCd())
                && !ContactManagementHelper.validateAlpha(caseTypeBCCPVO
                        .getMetastasisAreaCd()))
        {
            flag = true;
            ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.CASE_METASTATIS_AREA_INVALID,
                            MaintainContactManagementUIConstants.CASE_METASTATIS_AREA,
                            null, null);

        }

        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getUnstagedReasonCd())
                && !ContactManagementHelper.validateAlpha(caseTypeBCCPVO
                        .getUnstagedReasonCd()))
        {
            flag = true;
            ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.CASE_UNSTATED_REASON_INVALID,
                            MaintainContactManagementUIConstants.CASE_UNSTATED_REASON,
                            null, null);

        }
        
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getNodesPositiveNum())
                && !ContactManagementHelper.validateNumeric(caseTypeBCCPVO
                        .getNodesPositiveNum()))
        {
            flag = true;
            ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.CASE_NODES_POSITIVE_INVALID,
                            MaintainContactManagementUIConstants.CASE_NODES_POSITIVE,
                            null, null);

        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getTreatmentStartDateStr()))

        {
            Date treatDate = helper.dateConverter(caseTypeBCCPVO
                    .getTreatmentStartDateStr());
            if (treatDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_TREATMENT_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_TREATMENT_DATE,
                                null, null);
            }
            else
            {
                caseTypeBCCPVO.setTreatmentStartDate(treatDate);
            }
        }
        if (StringUtils
                .isNotEmpty(caseTypeBCCPVO.getChemoProjectedEndDateStr()))

        {
            Date chemoDate = helper.dateConverter(caseTypeBCCPVO
                    .getChemoProjectedEndDateStr());
            if (chemoDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_CHEMOPROJECT_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_CHEMOPROJECT_DATE,
                                null, null);
            }
            else
            {
                caseTypeBCCPVO.setChemoProjectedEndDate(chemoDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getCaregiverName())
                && !ContactManagementHelper.validateAlpha(caseTypeBCCPVO
                        .getCaregiverName()))
        {
            flag = true;
            ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.CASE_CAREGIVER_NAME_INVALID,
                            MaintainContactManagementUIConstants.CASE_CAREGIVER_NAME,
                            null, null);

        }
        if (StringUtils.isNotEmpty(caseTypeBCCPVO.getCaregiverPhone())
                && !ContactManagementHelper.validateNumeric(caseTypeBCCPVO
                        .getCaregiverPhone()))
        {
            flag = true;
            ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.CASE_CAREGIVER_PHONE_INVALID,
                            MaintainContactManagementUIConstants.CASE_CAREGIVER_PHONE,
                            null, null);
        }
        return flag;
    }

    /**
     * 
     * @param caseTypeDDUVO
     * @return boolean
     */
    public boolean validateDDU(CaseTypeDDUVO caseTypeDDUVO)
    {
        boolean flag = false;
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        log.debug("Inside ValidateDDU");
        if (StringUtils.isNotEmpty(caseTypeDDUVO.getApplicationDateStr()))
        {
            Date appDate = helper.dateConverter(caseTypeDDUVO
                    .getApplicationDateStr());
            if (appDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_DDU_APPLNDATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_DDU_APPLNDATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setApplicationDate(appDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeDDUVO
                .getSubstantiallyCompletedDateStr()))
        {
            Date subDate = helper.dateConverter(caseTypeDDUVO
                    .getSubstantiallyCompletedDateStr());
            if (subDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_SUBCOMPLETION_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_SUBCOMPLETION_DATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setSubstantiallyCompletedDate(subDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeDDUVO.getPacketReceivedDateStr()))
        {
            Date packDate = helper.dateConverter(caseTypeDDUVO
                    .getPacketReceivedDateStr());
            if (packDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_PACKETRECIEVE_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_PACKETRECIEVE_DATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setPacketReceivedDate(packDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeDDUVO.getReviewInitiatedDateStr()))
        {
            Date revDate = helper.dateConverter(caseTypeDDUVO
                    .getReviewInitiatedDateStr());
            if (revDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_REVIEWINTIATE_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_REVIEWINTIATE_DATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setReviewInitiatedDate(revDate);
            }
        }
        if (logCaseDataBean.isShowReviewReq()
                && StringUtils.isEmpty(caseTypeDDUVO.getSchDateOfReviewStr()))
        {
            flag = true;
            ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.CASE_REVIEWSCH_DATE_REQUIRED,
                            MaintainContactManagementUIConstants.CASE_REVIEWSCH_DATE,
                            null, null);
        }
        if (StringUtils.isNotEmpty(caseTypeDDUVO.getSchDateOfReviewStr()))
        {
            Date schDateOfDate = helper.dateConverter(caseTypeDDUVO
                    .getSchDateOfReviewStr());
            if (schDateOfDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_REVIEWSCH_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_REVIEWSCH_DATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setSchDateOfReview(schDateOfDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeDDUVO.getReceivedDateStr()))
        {
            Date receivDate = helper.dateConverter(caseTypeDDUVO
                    .getReceivedDateStr());
            if (receivDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_REVIEWRECV_DATE_INVLAID,
                                MaintainContactManagementUIConstants.CASE_REVIEWRECV_DATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setReceivedDate(receivDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeDDUVO.getReceiptDateStr()))
        {
            Date receiDate = helper.dateConverter(caseTypeDDUVO
                    .getReceiptDateStr());
            if (receiDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_REVRECPT_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_REVRECPT_DATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setReceiptDate(receiDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeDDUVO.getDecisionDateStr()))
        {
            Date decisionDate = helper.dateConverter(caseTypeDDUVO
                    .getDecisionDateStr());
            if (decisionDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_DECISION_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_DECISION_DATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setDecisionDate(decisionDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeDDUVO.getApprovedBeginDateStr()))
        {
            Date approvDate = helper.dateConverter(caseTypeDDUVO
                    .getApprovedBeginDateStr());
            if (approvDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_APPBEG_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_APPBEG_DATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setApprovedBeginDate(approvDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeDDUVO.getAppealDecisionDateStr()))
        {
            Date appDecisionDate = helper.dateConverter(caseTypeDDUVO
                    .getAppealDecisionDateStr());
            if (appDecisionDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_APPLDESN_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_APPLDESN_DATE,
                                null, null);
            }
            else
            {
                caseTypeDDUVO.setAppealDecisionDate(appDecisionDate);
            }

        }
        return flag;
    }

    /**
     * 
     * @param caseTypeARSVO
     * @return boolean
     */
    public boolean validateARS(CaseTypeARSVO caseTypeARSVO)
    {
        log.debug("Inside ValidateARS");
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        boolean flag = false;
        if (StringUtils.isNotEmpty(caseTypeARSVO.getRateSettingDateStr()))
        {
           
            Date rateDate = helper.dateConverter(caseTypeARSVO
                    .getRateSettingDateStr());
            if (rateDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_RATESETTING_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_RATESETTING_DATE,
                                null, null);
            }
            else
            {
                caseTypeARSVO.setRateSettingDate(rateDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeARSVO.getFieldAuditDateStr()))
        {
            
            Date fieldDate = helper.dateConverter(caseTypeARSVO
                    .getFieldAuditDateStr());
            if (fieldDate == null)
            {
                flag = true;
                ContactManagementHelper
                        .setErrorMessage(
                                MaintainContactManagementUIConstants.CASE_FIELDAUDIT_DATE_INVALID,
                                MaintainContactManagementUIConstants.CASE_FIELDAUDIT_DATE,
                                null, null);
            }
            else
            {
                caseTypeARSVO.setFieldAuditDate(fieldDate);
            }
        }
        if (StringUtils.isNotEmpty(caseTypeARSVO.getPictureDateStr()))
        {
            
            String picDateStr = logCaseDataBean.getPicIDMap().get(caseTypeARSVO.getPictureDateStr()).toString();
           
            Date picDate = helper.dateConverter(picDateStr);
            
            if (picDate != null)
            {
                caseTypeARSVO.setPictureDate(picDate);
            }
        }
        return flag;
    }

    /**
     * 
     * @param fieldValueVO
     * @return boolean
     */
    public boolean validateCustonField(CustomFieldValueVO fieldValueVO)
    {
        log.debug("Inside validateCustonField()");
      //commented for unused variables
        // boolean flag = false;
        DynamicCustomFieldHelper customFieldHelper = new DynamicCustomFieldHelper();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        List cfList = logCaseDataBean.getCustomFieldDOList();
        CustomField field = null;
        String value = null;
        if (cfList != null && !cfList.isEmpty())
        {
        	int cfListSize = 0;
            cfListSize = cfList.size();
			// Begin - Modified the code for the PanelGrid Fix
            List valuesList = new ArrayList(logCaseDataBean.getCustomFieldVOList());
            for (int i = 0; i < cfListSize; i++)
            {
                field = (CustomField) cfList.get(i);
                log.debug("CustomField Sk is $$ " + field.getCustomFieldSK());
                if (field.getRequiredValueIndicator().booleanValue())
                {
                    CustomFieldVO customFieldVO = (CustomFieldVO) valuesList.get(i);
                    
                    if(!field.getDataTypeCode().equals("CB"))
                    {
                    	if(isNullOrEmptyField(customFieldVO.getComponentInputData()))
	                    {
	                    	ContactManagementHelper.setErrorMessageForCF(EnterpriseMessageConstants.ERR_SW_REQUIRED,
	                    			"cfDataListId");
	                    	return true;
	                    }
                    }
                }
            }
			// End - Modified the code for the PanelGrid Fix			
        }
        return false;
    }

    /**
     * 
     * @param attachmentVO
     * @return boolean
     */
    public boolean validateAttachments(AttachmentVO attachmentVO)
    {
        log.debug("Inside validateAttachments()");
        boolean flag = false;
        if (StringUtils.isNotEmpty(attachmentVO.getFileName()))
        {
            flag = true;
            ContactManagementHelper.setErrorMessage(
                    MaintainContactManagementUIConstants.ATTACH_MESSAGE,
                    MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
                    null, null);
        }
       
        return flag;
    }
    
    /**
	 * This method is used to check if the input field is null or is equal to an
	 * empty string.
	 * 
	 * @param inputField
	 *            String inputField.
	 * @return boolean : true if input field is null or equal to an empty string
	 *         else false.
	 */
	protected boolean isNullOrEmptyField(String inputField) {
		return (inputField == null || ContactManagementConstants.EMPTY_STRING
				.equalsIgnoreCase(inputField.trim()));
	}
}
