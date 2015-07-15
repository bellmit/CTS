/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import com.acs.enterprise.common.base.common.domain.Attachment;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterGenerationRequest;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.program.benefitadministration.common.domain.LineOfBusiness;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;
import com.acs.enterprise.common.program.commonentities.common.domain.Phone;
import com.acs.enterprise.common.program.commonentities.view.vo.PhoneVO;
import com.acs.enterprise.common.program.contactmanagement.common.domain.ActCMCaseEvent;
import com.acs.enterprise.common.program.contactmanagement.common.domain.ActCMCaseStep;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Alert;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseARS;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseAttachCrossReference;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseBCCP;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseCommonEntityCrossRef;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseCorrespondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseDDU;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseDDUUnusualCircumstances;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseEventAppointment;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseLetterRequest;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseLink;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRecord;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldScope;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldTable;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldValue;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CMLogCaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AlertVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCaseVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCorrespondenceVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseEventsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseLetterResponsesXrefVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingTPL;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseStepsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeARSVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeAcuityRateSettingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeAppealRequestVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeBCCPVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeCreditBalanceVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeDDUVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeFCRVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeFPPRRVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeFacilityCensusSubmissionVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeFieldAuditVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeHomeOfficeCostReportingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeNFQAVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeNewARSFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeNonARSFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.caseTypeQuarterlyMQIPReturnsVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class is used to convert all Value Objects into Domain objects of Log
 * Case.
 * 
 * @author Wipro
 */
public class LogCaseVOToDomainConverter
{
    
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(LogCaseVOToDomainConverter.class);

    
	
	
	// Moved to ContactManagementConstants.java
    //public static final String CMLOGCASE_DATA_BEAN = "logCaseDataBean";
	
	

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
    

    /** holds the user ID */
    private CMLogCaseDataBean logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
    private String userID = logCaseDataBean.getLoggedInUserID();

    /**
     * This method is used to convert the CaseDetailsVO to CaseRecord Do
     * 
     * @param caseDetailsVO
     *            holds the CaseDetailsVO object.
     * @return it returns the main CaseRecord DO
     */
    public CaseRecord convertCaseRecordVOToDO(CaseDetailsVO caseDetailsVO)
    {
  
    	 System.err.println("++convertCaseRecordVOToDO is called");
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        CaseRecord caseRecord = new CaseRecord();
        caseRecord.setCaseSK(caseDetailsVO.getCaseSK());
        ContactManagementHelper helper = new ContactManagementHelper();
        System.err.println("CaseSK in VO 2 DO is $$ " + caseRecord.getCaseSK());
        
        String caseType = caseDetailsVO.getCaseType();
        //Map caseTypeSKAndShortDesc = logCaseDataBean.getCaseTypeSKAndShortDesc();
        //String shortDesc = (String)caseTypeSKAndShortDesc.get(caseType);
        String shortDesc =null;
        CaseType caseTypeObj=getCaseTypeFrmDosList(caseType,null);
        if(caseTypeObj!=null){
        	shortDesc=caseTypeObj.getShortDescription();
        }

        /** ****************************************************************** */
        //Following data is required to insert the data in to G_CASE_ARS_TB
        /** ***************************************************************** */
        if ((caseDetailsVO.getCaseTypeAcuityRateSettingVO() != null)&& (
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && "A3".equals(shortDesc))
        {
            caseRecord = convertCaseTypeAcuityRateSettingVOToDO(caseDetailsVO
                    .getCaseTypeAcuityRateSettingVO(), caseRecord);
            
        }       
        else if ((caseDetailsVO.getCaseTypeFieldAuditVO() != null)&& ( 
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode()) && "FA".equals(shortDesc)))
        {
            caseRecord = convertCaseTypeFieldAuditVOToDO(caseDetailsVO
                    .getCaseTypeFieldAuditVO(), caseRecord);
        }
        else if ((caseDetailsVO.getCaseTypeFPPRRVO() != null)&& 
        			("A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("F4".equals(shortDesc)))
        {
        	   logger.debug("Inside caseDetailsVO.getCaseTypeFPPRRVO() != null ");
            caseRecord = convertCaseTypeFPPRRVOToDO(caseDetailsVO
                    .getCaseTypeFPPRRVO(), caseRecord);
        }
        
        else if ((caseDetailsVO.getCaseTypeFacilityCensusSubmissionVO() != null) &&  
        		("A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("F3".equals(shortDesc)))
        {
        	
            caseRecord = convertCaseTypeFacilityCensusSubmissionVOToDO(caseDetailsVO
                    .getCaseTypeFacilityCensusSubmissionVO(), caseRecord);
        }
        
        else if ((caseDetailsVO.getCaseTypeCreditBalanceVO() != null) && ( 
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("CB".equals(shortDesc)))
        {
        	
            caseRecord = convertCaseTypeCreditBalanceVOToDO(caseDetailsVO
                    .getCaseTypeCreditBalanceVO(), caseRecord);
        }
        
        else if ((caseDetailsVO.getCaseTypeFCRVO() != null)&& ( 
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("F2".equals(shortDesc)))
        {
        	
            caseRecord = convertCaseTypeFCRVOToDO(caseDetailsVO
                    .getCaseTypeFCRVO(), caseRecord);
        }
        
        else if ((caseDetailsVO.getCaseTypeHomeOfficeCostReportingVO() != null) && ( 
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("H2".equals(shortDesc)))
        {        	
            caseRecord = convertCaseTypeHomeOfficeCostReportingVOToDO(caseDetailsVO
                    .getCaseTypeHomeOfficeCostReportingVO(), caseRecord);
        }
        
        else if ((caseDetailsVO.getCaseTypeNFQAVO() != null)&& ( 
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("N2".equals(shortDesc)))
        {
        	
            caseRecord = convertCaseTypeNFQAVOToDO(caseDetailsVO
                    .getCaseTypeNFQAVO(), caseRecord);
        }
        
        else if ((caseDetailsVO.getCaseTypeQuarterlyMQIPReturnsVO() != null)&& ( 
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("Q2".equals(shortDesc)))
        {
        	
            caseRecord = convertCaseTypeQuarterlyMQIPReturnsVOToDO(caseDetailsVO
                    .getCaseTypeQuarterlyMQIPReturnsVO(), caseRecord);
        }
        
        else if ((caseDetailsVO.getCaseTypeNewARSFieldVO() != null)&& ( 
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("A4".equals(shortDesc)))
        {
        	
            caseRecord = convertCaseTypeNewARSFieldVOToDO(caseDetailsVO
                    .getCaseTypeNewARSFieldVO(), caseRecord);
        }
        
        else if ((caseDetailsVO.getCaseTypeNonARSFieldVO() != null)&& ( 
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("N3".equals(shortDesc)))
        {
        	
            caseRecord = convertCaseTypeNewNonARSFieldVOToDO(caseDetailsVO
                    .getCaseTypeNonARSFieldVO(), caseRecord);
        }
        
        else if ((caseDetailsVO.getCaseTypeAppealRequestVO() != null)&& ( 
        		"A".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode())) && ("AR".equals(shortDesc)))
        {
        	
            caseRecord = convertCaseTypeAppealRequestVOToDO(caseDetailsVO
                    .getCaseTypeAppealRequestVO(), caseRecord);
        }    
        
        /** ****************************************************************** */
        //Following data is required to insert the data in to G_CASE_TB - Done
        /** ***************************************************************** */

        if (!isNullOrEmptyField(caseDetailsVO.getPriority()))
        {
        	
            caseRecord.setCasePriorityCode(caseDetailsVO.getPriority());
        }
        if (caseDetailsVO.getClosedDays() != null)
        {
        	
            caseRecord.setDaysToCloseNumber(Integer.valueOf(caseDetailsVO
                    .getClosedDays()));
        }       
        if (caseDetailsVO.getCreatedDate() != null)
        {
        	
            caseRecord.setOpenDate(caseDetailsVO.getCreatedDate());
        }
        if (!isNullOrEmptyField(caseDetailsVO.getCaseTitle()))
        {
        	
            caseRecord.setTitle(caseDetailsVO.getCaseTitle());
        }
        
        // Commented by ICS for Defect ID ESPRD00308997
        
  
        
        if (!isNullOrEmptyField(caseDetailsVO.getCreatedBySK()))
        {
        	
            WorkUnit workUnit = new WorkUnit();
            workUnit.setWorkUnitSK(new Long(caseDetailsVO.getCreatedBySK()));
            caseRecord.setCaseCreatedByWorkUnit(workUnit);
        }
        if (!isNullOrEmptyField(caseDetailsVO.getAssignedToWorkUnitSK()))
        {
        	
            WorkUnit workUnit = new WorkUnit();
            EnterpriseUser enterpriseUser = new EnterpriseUser();
            workUnit.setWorkUnitSK(new Long(caseDetailsVO
                    .getAssignedToWorkUnitSK()));
            //Commented for Heap dump fix defect ESPRD00935080
            /*String userID = helper.getUserIDByWUSK(caseDetailsVO
                    .getAssignedToWorkUnitSK());
			logger.debug("--useridWUmap 3::"+userID);*/
			String userID = helper.getUserIDByWUSK2(caseDetailsVO
                    .getAssignedToWorkUnitSK());
			logger.debug("++useridWUmap 3::"+userID);
            enterpriseUser.setUserID(userID);
            workUnit.setEnterpriseUser(enterpriseUser);
            caseRecord.setCaseAssignedToWorkUnit(workUnit);
        }
        if (caseDetailsVO.getCaseType() != null)
        {
            caseRecord
                    .setCaseType(getCaseTypeValue(caseDetailsVO.getCaseType()));
        }
        System.err.println("++caseDetailsVO.getWorkUnit()=="+caseDetailsVO.getWorkUnit());
        if (!isNullOrEmptyField(caseDetailsVO.getWorkUnit()))
        {
            
            LineOfBusinessHierarchy lobHierarchy = new LineOfBusinessHierarchy();
            Map mapOfDeptAndLobHier = logCaseDataBean.getLobDOMap();
            System.err.println("++mapOfDeptAndLobHier=="+mapOfDeptAndLobHier);
            Long lobHierarchySK = (Long) mapOfDeptAndLobHier.get(caseDetailsVO
                    .getWorkUnit());
            System.err.println("++lobHierarchySK=="+lobHierarchySK);
            System.err.println("++logCaseDataBean.getLobHierarchySK()=="+logCaseDataBean.getLobHierarchySK());
            if (lobHierarchySK == null) {
            	lobHierarchySK = logCaseDataBean.getLobHierarchySK();
            }
            System.err.println("++lobHierarchySK=="+lobHierarchySK);
            lobHierarchy.setLineOfBusinessHierarchySK(lobHierarchySK);
            caseRecord.setLobHierarchy(lobHierarchy);
        }
        if (caseDetailsVO.getCaseSensitiveDataInd() != null)
        {
            caseRecord.setSensitiveDataIndicator(Boolean.valueOf(caseDetailsVO
                    .getCaseSensitiveDataInd()));
        }
        if (!isNullOrEmptyField(caseDetailsVO.getStatus()))
        {
            caseRecord.setCaseStatusCode(caseDetailsVO.getStatus());
        }
        if(caseDetailsVO.getStatusDate()!=null){
        	caseRecord.setCaseStatusDate(caseDetailsVO.getStatusDate());
        }
        if (caseDetailsVO.isSupvrRewInd())
        {
            caseRecord.setSupervisorReviewReqIndicator(Boolean.TRUE);
        }
        else
        {
            caseRecord.setSupervisorReviewReqIndicator(Boolean.FALSE);
        }
      
        
     
        
        if (caseDetailsVO.getLineOfBusiness() != null)
        {
      
        	LineOfBusiness business=new LineOfBusiness();
        	business.setLobCode(caseDetailsVO.getLineOfBusiness());
        	caseRecord.setLineOfBusiness(business);
        }
        
        caseRecord.setAuditUserID(userID);
        caseRecord.setAuditTimeStamp(new Date());
        if (caseDetailsVO.getAddedAuditUserID() == null)
        {
            caseRecord.setAddedAuditUserID(userID);
        }
        else
        {
            caseRecord.setAddedAuditUserID(caseDetailsVO.getAddedAuditUserID());
        }
        if (caseDetailsVO.getAddedAuditTimeStamp() == null)
        {
            caseRecord.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            caseRecord.setAddedAuditTimeStamp(caseDetailsVO
                    .getAddedAuditTimeStamp());
        }

        /** ****************************************************************** */
        //Following data is required to insert the data in to G_CASE_DDU
        /** ***************************************************************** */
        if (caseDetailsVO.getCaseTypeDDUVO() != null
                && "D".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode()))
        {
            
            caseRecord = convertCaseTypeDDUVOToDO(caseDetailsVO
                    .getCaseTypeDDUVO(), caseRecord);
        }
       
        /** ****************************************************************** */
        //Following data is required to insert the data in to G_CASE_BCCP
        /** ***************************************************************** */
        if (caseDetailsVO.getCaseTypeBCCPVO() != null
                && "B".equals(caseDetailsVO.getCaseTypeBusinessUnitTypeCode()))
        {
            caseRecord = convertCaseTypeBCCPVOToDO(caseDetailsVO
                    .getCaseTypeBCCPVO(), caseRecord);
        }
        /*
         * Commented for the defect id ESPRD00377773
         * if (caseDetailsVO.getListOfLettersAndResponses() != null
                && !caseDetailsVO.getListOfLettersAndResponses().isEmpty())
        {
            Set setOfCaseLetterXrefs = convertListOfLettersVOs(caseDetailsVO,
                    caseRecord);
            caseRecord.setCaseLetterRequests(setOfCaseLetterXrefs);
           
        }*/
        caseRecord.setVersionNo(caseDetailsVO.getVersionNo());
        Set caseCorrespondences = convertCaseCorrespondenceVOToDO
										(caseDetailsVO.getAssociatedCrList(),caseRecord);
        caseRecord.setCaseCorrespondences(caseCorrespondences);
        //for ESPRD00778912
        Set associatedCaseSet = convertAssociateCaseVOToDO(logCaseDataBean
				.getCaseRecordsAssoWithCaseList(), caseRecord);
		// (caseDetailsVO.getAssociatedCaseList(),caseRecord);
        Set associateCase=convertAssociateCaseVOToDO(caseDetailsVO
				.getAssociatedCaseList(), caseRecord);
		associatedCaseSet.addAll(associateCase);	
        caseRecord.setCurrentCaseRecordLinks(associatedCaseSet);
        
        return caseRecord;
    }
    
    private CaseRecord convertCaseTypeAcuityRateSettingVOToDO(CaseTypeAcuityRateSettingVO caseTypeAcuityRateVO,
            CaseRecord caseRecord)
    {
    	logger.info("inside convertCaseTypeAcuityRateSettingVOToDO");
		CaseARS caseARS = new CaseARS();

		caseARS.setCaseRecordRef(caseRecord.getCaseSK());
//		UC18_ARS_MAJOR_SAVE_23JUN2010
		//caseARS.setAuditUserID(userID);
		//caseARS.setAuditTimeStamp(new Date());
		//caseARS.setAddedAuditUserID(userID);

		//caseARS.setAddedAuditTimeStamp(new Date());
		if(caseTypeAcuityRateVO.getAuditUserID()!=null){
			caseARS.setAuditUserID(caseTypeAcuityRateVO.getAuditUserID());
		}else{
			caseARS.setAuditUserID(userID);
		}
		if(caseTypeAcuityRateVO.getAuditTimeStamp() != null){
			caseARS.setAuditTimeStamp(caseTypeAcuityRateVO.getAuditTimeStamp());
		}else{
			caseARS.setAuditTimeStamp(new Date());
		}
		if(caseTypeAcuityRateVO.getAddedAuditUserID() != null){
			caseARS.setAddedAuditUserID(caseTypeAcuityRateVO.getAddedAuditUserID());
		}else{
			caseARS.setAddedAuditUserID(userID);
		}
		if(caseTypeAcuityRateVO.getAddedAuditTimeStamp() != null){
			caseARS.setAddedAuditTimeStamp(caseTypeAcuityRateVO.getAddedAuditTimeStamp());
		}else{
			caseARS.setAddedAuditTimeStamp(new Date());
		}
		//EOF UC18_ARS_MAJOR_SAVE_23JUN2010
		caseARS.setVersionNo(caseTypeAcuityRateVO.getVersionNo());

		/*
		 * if(caseTypeAcuityRateVO.getRateEffectiveDate()!=null &&
		 * caseTypeAcuityRateVO.getRateEffectiveDate().trim().length() > 0) {
		 * caseARS.setRateSettingDate(ContactManagementHelper.dateConverter(caseTypeAcuityRateVO.getRateEffectiveDate())); }
		 * 
		 * if(caseTypeAcuityRateVO.getStateFiscYearEnd()!= null &&
		 * caseTypeAcuityRateVO.getStateFiscYearEnd().trim().length() > 0) {
		 * caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(caseTypeAcuityRateVO.getStateFiscYearEnd())); }
		 * if(caseTypeAcuityRateVO.getAppealReceived()!=null) {
		 * caseARS.setAppealReceivedIndicator(Boolean.valueOf(caseTypeAcuityRateVO.getAppealReceived())); }
		 * if(caseTypeAcuityRateVO.getPictureDate()!=null) {
		 * caseARS.setCaseARSCRPICDate(caseTypeAcuityRateVO.getPictureDate()); }
		 */

		if (caseTypeAcuityRateVO.getRateEffectiveDate() != null
				&& caseTypeAcuityRateVO.getRateEffectiveDate().trim().length() > 0) {
			String rateEffDate = ContactManagementHelper
					.getValidDateFormat(caseTypeAcuityRateVO
							.getRateEffectiveDate());
			caseARS.setRateSettingDate(ContactManagementHelper
					.dateConverter(rateEffDate));
		}
		if (!isNullOrEmptyField(caseTypeAcuityRateVO.getStateFiscYearEnd())) {
			String fiscalDate = ContactManagementHelper
					.getValidDateFormat(caseTypeAcuityRateVO
							.getStateFiscYearEnd());
			caseARS.setFiscalYearEndDate(ContactManagementHelper
					.dateConverter(fiscalDate));
		}
		if (caseTypeAcuityRateVO.getPictureDate() != null) {
			Map picMap = logCaseDataBean.getPicIDMap();
			String pictDate = (String) picMap.get(caseTypeAcuityRateVO
					.getPictureDate());
			caseARS.setCaseARSCRPICDate(pictDate);
		}
		if (caseTypeAcuityRateVO.getAppealReceived() != null) {
			caseARS.setAppealReceivedIndicator(Boolean
					.valueOf(caseTypeAcuityRateVO.getAppealReceived()));
		}
		caseRecord.setCaseARS(caseARS);
		return caseRecord;
    }
    private CaseRecord convertCaseTypeFPPRRVOToDO(CaseTypeFPPRRVO caseTypeFPPRRVO,
            CaseRecord caseRecord)
    {
    	logger.info("inside convertCaseTypeFPPRRVOToDO");
    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
//      UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());
        
        if(caseTypeFPPRRVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeFPPRRVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeFPPRRVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeFPPRRVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeFPPRRVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeFPPRRVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeFPPRRVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeFPPRRVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        caseARS.setVersionNo(caseTypeFPPRRVO.getVersionNo());
        //EOF UC18_ARS_MAJOR_SAVE_23JUN2010
        
        if(!isNullOrEmptyField(caseTypeFPPRRVO.getStateFiscalYearEnd()))
    	{
        	String stateFiscalDate = ContactManagementHelper
            .getValidDateFormat(caseTypeFPPRRVO.getStateFiscalYearEnd());
        	
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(stateFiscalDate));
        	
    	}      
        if(caseTypeFPPRRVO.getPrivatePayRatesLoadedInd()!=null)
    	{
    		caseARS.setPrivatePayRatesLoadedInd(Boolean.valueOf(caseTypeFPPRRVO.getPrivatePayRatesLoadedInd()));
        	
    	}            
        logger.info("after convertCaseTypeFPPRRVOToDO");
        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    }
    
    private CaseRecord convertCaseTypeFacilityCensusSubmissionVOToDO(CaseTypeFacilityCensusSubmissionVO caseTypeFacilityCensusSubmissionVO, CaseRecord caseRecord)
    {
    	logger.info("inside convertCaseTypeFacilityCensusSubmissionVOToDO");
    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
        //UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());

        if(caseTypeFacilityCensusSubmissionVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeFacilityCensusSubmissionVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeFacilityCensusSubmissionVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeFacilityCensusSubmissionVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeFacilityCensusSubmissionVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeFacilityCensusSubmissionVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeFacilityCensusSubmissionVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeFacilityCensusSubmissionVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        caseARS.setVersionNo(caseTypeFacilityCensusSubmissionVO.getVersionNo());
        //EOf UC18_ARS_MAJOR_SAVE_23JUN2010
        
        if(!isNullOrEmptyField(caseTypeFacilityCensusSubmissionVO.getStateFiscYrEnd()))
    	{
        	String stateFiscalDate = ContactManagementHelper
            .getValidDateFormat(caseTypeFacilityCensusSubmissionVO.getStateFiscYrEnd());
        	
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(stateFiscalDate));
      	}      
       
        if(caseTypeFacilityCensusSubmissionVO.getPictureDate()!=null)
    	{
    		caseARS.setCaseARSCRPICDate(caseTypeFacilityCensusSubmissionVO.getPictureDate());
      	} 
        logger.info("after convertCaseTypeFacilityCensusSubmissionVOToDO");
        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    }
    
    private CaseRecord convertCaseTypeCreditBalanceVOToDO(CaseTypeCreditBalanceVO caseTypeCreditBalanceVO,
            CaseRecord caseRecord)
    { 	
    	logger.info("inside convertCaseTypeCreditBalanceVOToDO");
    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
        //UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());

        if(caseTypeCreditBalanceVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeCreditBalanceVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeCreditBalanceVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeCreditBalanceVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeCreditBalanceVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeCreditBalanceVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeCreditBalanceVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeCreditBalanceVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        
        caseARS.setVersionNo(caseTypeCreditBalanceVO.getVersionNo());
        //EOF UC18_ARS_MAJOR_SAVE_23JUN2010
        
        if(!isNullOrEmptyField(caseTypeCreditBalanceVO.getStateFiscYrEnd()))
    	{
        	String stateFiscalDate = ContactManagementHelper
            .getValidDateFormat(caseTypeCreditBalanceVO.getStateFiscYrEnd());
        	
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(stateFiscalDate));
      	}      
        logger.info("after convertCaseTypeCreditBalanceVOToDO");
        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    }
    
    private CaseRecord convertCaseTypeFCRVOToDO(CaseTypeFCRVO caseTypeFCRVO, CaseRecord caseRecord)
    {  	
    	logger.info("inside convertCaseTypeFCRVOToDO");
    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
        //UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());

        if(caseTypeFCRVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeFCRVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeFCRVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeFCRVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeFCRVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeFCRVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeFCRVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeFCRVO.getAddedAuditTimeStamp());	
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        caseARS.setVersionNo(caseTypeFCRVO.getVersionNo());
        //EOF UC18_ARS_MAJOR_SAVE_23JUN2010
        
        if(caseTypeFCRVO.getStateFiscalYearEnd()!=null)
    	{
//        	String stateFiscalDate = ContactManagementHelper
//            .getValidDateFormat(caseTypeFCRVO.getStateFiscalYearEnd());
        	
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(caseTypeFCRVO.getStateFiscalYearEnd()));
      	}      
        if(caseTypeFCRVO.getFacilityFiscalYearEnd()!=null)
    	{
    		caseARS.setFacilityFiscalYearEndDate(ContactManagementHelper.dateConverter(caseTypeFCRVO.getFacilityFiscalYearEnd()));
      	} 
        if(caseTypeFCRVO.getHomeOfficeIndicator()!=null)
    	{
    		caseARS.setHomeOfficeInd(Boolean.valueOf(caseTypeFCRVO.getHomeOfficeIndicator()));
      	} 
        if(caseTypeFCRVO.getFieldAuditRequired()!=null)
    	{
    		caseARS.setFieldAuditRequiredInd(Boolean.valueOf(caseTypeFCRVO.getFieldAuditRequired()));
      	}  
        if(caseTypeFCRVO.getFieldAuditDate()!=null)
    	{
    		caseARS.setFieldAuditDate(ContactManagementHelper.dateConverter(caseTypeFCRVO.getFieldAuditDate()));
      	}
        if(caseTypeFCRVO.getDeskReviewStartDate()!=null)
    	{
//        	String deskReviewStartDate = ContactManagementHelper
//            .getValidDateFormat(caseTypeFCRVO.getDeskReviewStartDate());
        	
    		caseARS.setDeskReviewDate(ContactManagementHelper.dateConverter(caseTypeFCRVO.getDeskReviewStartDate()));
      	}      
       
        logger.info("after convertCaseTypeFCRVOToDO");

        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    }
    
    private CaseRecord convertCaseTypeHomeOfficeCostReportingVOToDO(CaseTypeHomeOfficeCostReportingVO caseTypeHomeOfficeCostReportingVO, CaseRecord caseRecord)
    {  	
    	logger.info("inside caseTypeHomeOfficeCostReportingVO");
    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
        //UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());
        
        if(caseTypeHomeOfficeCostReportingVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeHomeOfficeCostReportingVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeHomeOfficeCostReportingVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeHomeOfficeCostReportingVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeHomeOfficeCostReportingVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeHomeOfficeCostReportingVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeHomeOfficeCostReportingVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeHomeOfficeCostReportingVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        //EOF UC18_ARS_MAJOR_SAVE_23JUN2010

        caseARS.setVersionNo(caseTypeHomeOfficeCostReportingVO.getVersionNo());
        
        if(caseTypeHomeOfficeCostReportingVO.getStateFiscalYearEnd()!=null)
    	{
        	//COMMENTED FOR ESPRD00383092 
//        	String stateFiscalDate = ContactManagementHelper
//            .getValidDateFormat(caseTypeHomeOfficeCostReportingVO.getStateFiscalYearEnd());
        	
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(caseTypeHomeOfficeCostReportingVO.getStateFiscalYearEnd()));
      	}      
       
        if(caseTypeHomeOfficeCostReportingVO.getFacilityFiscalYearend()!=null)
    	{
    		caseARS.setFacilityFiscalYearEndDate(ContactManagementHelper.dateConverter(caseTypeHomeOfficeCostReportingVO.getFacilityFiscalYearend()));
      	} 
        if(caseTypeHomeOfficeCostReportingVO.getFieldAuditRequired()!=null)
    	{
        	caseARS.setFieldAuditRequiredInd(Boolean.valueOf(caseTypeHomeOfficeCostReportingVO.getFieldAuditRequired()));
      	} 
        if(caseTypeHomeOfficeCostReportingVO.getFieldAuditDate()!=null)
    	{
    		caseARS.setFieldAuditDate(ContactManagementHelper.dateConverter(caseTypeHomeOfficeCostReportingVO.getFieldAuditDate()));
      	}
        logger.info("after caseTypeHomeOfficeCostReportingVO");
        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    }
    
    private CaseRecord convertCaseTypeNFQAVOToDO(CaseTypeNFQAVO caseTypeNFQAVO, CaseRecord caseRecord)
    {	
    	logger.info("inside convertCaseTypeNFQAVOToDO");
    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
        //UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());

        if(caseTypeNFQAVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeNFQAVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeNFQAVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeNFQAVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeNFQAVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeNFQAVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeNFQAVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeNFQAVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        caseARS.setVersionNo(caseTypeNFQAVO.getVersionNo());
        //EOF UC18_ARS_MAJOR_SAVE_23JUN2010
        
        if(!isNullOrEmptyField(caseTypeNFQAVO.getStateFiscalYearEnd()))
    	{
        	String stateFiscalDate = ContactManagementHelper
            .getValidDateFormat(caseTypeNFQAVO.getStateFiscalYearEnd());
        	
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(stateFiscalDate));
      	}      
        logger.info("after convertCaseTypeNFQAVOToDO");
        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    }
    
    private CaseRecord convertCaseTypeQuarterlyMQIPReturnsVOToDO(caseTypeQuarterlyMQIPReturnsVO caseTypeQuarterlyMQIPReturnsVO, CaseRecord caseRecord)
    {   	
    	logger.info("inside convertCaseTypeQuarterlyMQIPReturnsVOToDO");

    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
        //UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());

        if(caseTypeQuarterlyMQIPReturnsVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeQuarterlyMQIPReturnsVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeQuarterlyMQIPReturnsVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeQuarterlyMQIPReturnsVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeQuarterlyMQIPReturnsVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeQuarterlyMQIPReturnsVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeQuarterlyMQIPReturnsVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeQuarterlyMQIPReturnsVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        caseARS.setVersionNo(caseTypeQuarterlyMQIPReturnsVO.getVersionNo());
        //EOF UC18_ARS_MAJOR_SAVE_23JUN2010
        
        if(!isNullOrEmptyField(caseTypeQuarterlyMQIPReturnsVO.getStateFiscalYearEnd()))
    	{
        	String stateFiscalDate = ContactManagementHelper
            .getValidDateFormat(caseTypeQuarterlyMQIPReturnsVO.getStateFiscalYearEnd());   
        	
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(stateFiscalDate));
      	}      
        logger.info("after convertCaseTypeQuarterlyMQIPReturnsVOToDO");

        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    } 
    
    private CaseRecord convertCaseTypeNewARSFieldVOToDO(CaseTypeNewARSFieldVO caseTypeNewARSFieldVO, CaseRecord caseRecord)
    { 	
    	logger.info("inside convertCaseTypeNewARSFieldVOToDO");

    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
        //UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());

        if(caseTypeNewARSFieldVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeNewARSFieldVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeNewARSFieldVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeNewARSFieldVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeNewARSFieldVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeNewARSFieldVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeNewARSFieldVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeNewARSFieldVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        caseARS.setVersionNo(caseTypeNewARSFieldVO.getVersionNo());
        //EFO UC18_ARS_MAJOR_SAVE_23JUN2010
        
        if(!isNullOrEmptyField(caseTypeNewARSFieldVO.getStateFiscalYrEnd()))
    	{
        	String stateFiscalDate = ContactManagementHelper
            .getValidDateFormat(caseTypeNewARSFieldVO.getStateFiscalYrEnd()); 
        	
        	caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(stateFiscalDate));
      	}     
        if(caseTypeNewARSFieldVO.getFacilityFiscalYrEnd()!=null)
    	{
    		caseARS.setFacilityFiscalYearEndDate(ContactManagementHelper.dateConverter(caseTypeNewARSFieldVO.getFacilityFiscalYrEnd()));
      	}
        
        if(caseTypeNewARSFieldVO.getAppealRequest()!=null)
    	{
    		caseARS.setAppealReceivedIndicator(Boolean.valueOf(caseTypeNewARSFieldVO.getAppealRequest()));
      	}
        logger.info("after convertCaseTypeNewARSFieldVOToDO");

        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    } 
    
    private CaseRecord convertCaseTypeNewNonARSFieldVOToDO(CaseTypeNonARSFieldVO caseTypeNonARSFieldVO, CaseRecord caseRecord)
    {	
    	logger.info("inside convertCaseTypeNewNonARSFieldVOToDO");
    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
        //UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());
        
        if(caseTypeNonARSFieldVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeNonARSFieldVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeNonARSFieldVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeNonARSFieldVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeNonARSFieldVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeNonARSFieldVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeNonARSFieldVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeNonARSFieldVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        caseARS.setVersionNo(caseTypeNonARSFieldVO.getVersionNo());
        //EOF UC18_ARS_MAJOR_SAVE_23JUN2010
        
        if(!isNullOrEmptyField(caseTypeNonARSFieldVO.getStateFiscalYrEnd()))
    	{
        	String stateFiscalDate = ContactManagementHelper
            .getValidDateFormat(caseTypeNonARSFieldVO.getStateFiscalYrEnd());
        	
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(stateFiscalDate));
      	}     
        if(caseTypeNonARSFieldVO.getFacilityFiscalYrEnd()!=null)
    	{
    		caseARS.setFacilityFiscalYearEndDate(ContactManagementHelper.dateConverter(caseTypeNonARSFieldVO.getFacilityFiscalYrEnd()));
      	}
        
        if(caseTypeNonARSFieldVO.getAppealRequest()!=null)
    	{
    		caseARS.setAppealReceivedIndicator(Boolean.valueOf(caseTypeNonARSFieldVO.getAppealRequest()));
      	}   
        logger.info("after convertCaseTypeNewNonARSFieldVOToDO");

        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    }
    
    private CaseRecord convertCaseTypeAppealRequestVOToDO(CaseTypeAppealRequestVO caseTypeAppealRequestVO, CaseRecord caseRecord)
    {	
    	logger.info("inside convertCaseTypeAppealRequestVOToDO");
    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
        //UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());
        
        if(caseTypeAppealRequestVO.getAuditUserID()!=null){
        	caseARS.setAuditUserID(caseTypeAppealRequestVO.getAuditUserID());
        }else{
        	caseARS.setAuditUserID(userID);
        }
        if(caseTypeAppealRequestVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeAppealRequestVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeAppealRequestVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeAppealRequestVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeAppealRequestVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeAppealRequestVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        
        caseARS.setVersionNo(caseTypeAppealRequestVO.getVersionNo());
        //EOF UC18_ARS_MAJOR_SAVE_23JUN2010
        
        
        if(caseTypeAppealRequestVO.getStateFiscalYrEnd()!=null)
    	{
        	//Commented for defect ESPRD00443381 starts
			//String stateFiscalDate = ContactManagementHelper
			//            .getValidDateFormat(caseTypeAppealRequestVO.getStateFiscalYrEnd());
        	//ESPRD00443381 ends
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(caseTypeAppealRequestVO.getStateFiscalYrEnd()));
      	}     
        if(caseTypeAppealRequestVO.getRateSettingDate()!=null)
    	{
    		caseARS.setRateSettingDate(ContactManagementHelper.dateConverter(caseTypeAppealRequestVO.getRateSettingDate()));
      	}
        logger.info("after convertCaseTypeAppealRequestVOToDO");
        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    }
    
    private CaseRecord convertCaseTypeFieldAuditVOToDO(CaseTypeFieldAuditVO caseTypeFieldAuditVO, CaseRecord caseRecord)
    {   	
    	logger.info("inside convertCaseTypeFieldAuditVOToDO");
    	CaseARS caseARS = new CaseARS();
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());
//      UC18_ARS_MAJOR_SAVE_23JUN2010
        //caseARS.setAuditUserID(userID);
        //caseARS.setAuditTimeStamp(new Date());
        //caseARS.setAddedAuditUserID(userID);
        //caseARS.setAddedAuditTimeStamp(new Date());
        
        if(caseTypeFieldAuditVO.getAuditUserID()!=null){
        	 caseARS.setAuditUserID(caseTypeFieldAuditVO.getAuditUserID());
        }else{
        	 caseARS.setAuditUserID(userID);
        }
        if(caseTypeFieldAuditVO.getAuditTimeStamp()!=null){
        	caseARS.setAuditTimeStamp(caseTypeFieldAuditVO.getAuditTimeStamp());
        }else{
        	caseARS.setAuditTimeStamp(new Date());
        }
        if(caseTypeFieldAuditVO.getAddedAuditUserID()!=null){
        	caseARS.setAddedAuditUserID(caseTypeFieldAuditVO.getAddedAuditUserID());
        }else{
        	caseARS.setAddedAuditUserID(userID);
        }
        if(caseTypeFieldAuditVO.getAddedAuditTimeStamp()!=null){
        	caseARS.setAddedAuditTimeStamp(caseTypeFieldAuditVO.getAddedAuditTimeStamp());
        }else{
        	caseARS.setAddedAuditTimeStamp(new Date());
        }
        
        caseARS.setVersionNo(caseTypeFieldAuditVO.getVersionNo());
        //EOF UC18_ARS_MAJOR_SAVE_23JUN2010
        
        if(!isNullOrEmptyField(caseTypeFieldAuditVO.getStateFiscalYrEnd()))
    	{
        	String stateFiscalDate = ContactManagementHelper
            .getValidDateFormat(caseTypeFieldAuditVO.getStateFiscalYrEnd());
        	
    		caseARS.setFiscalYearEndDate(ContactManagementHelper.dateConverter(stateFiscalDate));
      	}     
        if(caseTypeFieldAuditVO.getFacilityFiscalYearEnd()!=null)
    	{
    		caseARS.setFacilityFiscalYearEndDate(ContactManagementHelper.dateConverter(caseTypeFieldAuditVO.getFacilityFiscalYearEnd()));
      	}
        if(caseTypeFieldAuditVO.getFieldAuditDate()!=null)
    	{
    		caseARS.setFieldAuditDate(ContactManagementHelper.dateConverter(caseTypeFieldAuditVO.getFieldAuditDate()));
      	}
        if(caseTypeFieldAuditVO.getHomeOfficeInd() !=null)
    	{
    		caseARS.setHomeOfficeInd(Boolean.valueOf(caseTypeFieldAuditVO.getHomeOfficeInd()));
      	}
        logger.info("after convertCaseTypeFieldAuditVOToDO");
        caseRecord.setCaseARS(caseARS);
        return caseRecord;
    }
    
    public List convertCustomFieldVO(CaseRecord caseRecord,
            CustomFieldValueVO fieldValueVO)
    {
    	logger.debug("convertCustomFieldVO() is started");
    logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
    List cfList = logCaseDataBean.getCustomFieldDOList();
  
    DynamicCustomFieldHelper customFieldHelper = new DynamicCustomFieldHelper();
   
    String verNo = null;
    String customSK = null;
    List finalCFValueList = new ArrayList();
    if (cfList != null && !cfList.isEmpty())
    {
    	int size = 0;
    	int valueSKSize = 0;
       
        CustomField field = null;
        size = cfList.size();
        valueSKSize = logCaseDataBean.getCfValueSKMap().size();
        
        logger.debug(" testing ...2... cfList : "+ cfList.size());
        logger.debug(" testing ...3... valueSKSize : "+ valueSKSize);
        
        for (int i = 0; i < size; i++)
        {
//          Added for defect ESPRD00477346 starts
        	 String value = null;
            field = (CustomField) cfList.get(i);
            logger
            .debug("CustomField Sk is $$ "
                    + field.getCustomFieldSK());
            logger.debug("Processing of " + i
            + " CustomField is Started and SK is $$ "
            + field.getDataTypeCode());
            //commented for critical defect ESPRD00597161
            /*if (field.getDataTypeCode().equals("CB") &&  logCaseDataBean.getFormElements().getChildren().get((i*4)+3) instanceof HtmlSelectBooleanCheckbox) {
//            	if (i ==0 ){
//            	
//            		HtmlSelectBooleanCheckbox htmlCheckBox = (HtmlSelectBooleanCheckbox) logCaseDataBean.getFormElements().getChildren().get(i);
//            		value = String.valueOf(htmlCheckBox.isSelected());
//            	} else {
            		HtmlSelectBooleanCheckbox htmlCheckBox = (HtmlSelectBooleanCheckbox) logCaseDataBean.getFormElements().getChildren().get((i*4)+3);
            		value = String.valueOf(htmlCheckBox.isSelected());
//            	}
            }*/// defectESPRD00597161 ends
         
            logger.debug(" testing ...3... fieldValueVO : "+ fieldValueVO);
            if (value == null) {
			// Begin - Modified the code for the PanelGrid Fix
            	//value = customFieldHelper.getCustomFieldValue(i, fieldValueVO);
            	if(field.getDataTypeCode().equals("CB"))
            	{
            		value = String.valueOf(((CustomFieldVO) logCaseDataBean.getCustomFieldVOList().get(i)).getCheckBoxValue());
            	}
            	else
            	{
            		value = ((CustomFieldVO) logCaseDataBean.getCustomFieldVOList().get(i)).getComponentInputData();
            	}
			// End - Modified the code for the PanelGrid Fix				
            }
            logger.debug(" testing ...4... value : "+ value);
            
            if (value != null && !value.equals(""))
            {
                CustomFieldValue fieldValue = null;
                fieldValue = convertCustomFieldValuesVOToDO(value,
                        fieldValueVO, field);
                logger.debug(" testing ...5... value : "+ fieldValue);
                
                fieldValue.setCaseRecord(caseRecord);
                if (!logCaseDataBean.getCfValueSKMap().isEmpty())
                {
                    customSK = field.getCustomFieldSK().toString();
                    verNo = (String) logCaseDataBean
                            .getCfValueSKMap().get(customSK);
                    if (verNo != null)
                    {
                    	int versionNo = 0;
                        versionNo = Integer.valueOf(verNo).intValue();
                        
                        fieldValue.setVersionNo(versionNo);
                    }
                    else
                    {
                        fieldValue.setVersionNo(0);
                    }
                }
                else
                {
                    fieldValue.setVersionNo(0);
                }
                finalCFValueList.add(fieldValue);
            }
            //defect ESPRD00477346 ends
        }
    }
    return finalCFValueList;
    }

    private CustomFieldValue convertCustomFieldValuesVOToDO(String cfValue,
            CustomFieldValueVO customFieldValueVO, CustomField field)
    {
    	logger.debug("convertCustomFieldValuesVOToDO is started");
        CustomFieldValue fieldValue = new CustomFieldValue();
        CustomFieldScope fieldScope = new CustomFieldScope();
        fieldValue.setCustomFieldValue(cfValue);
        fieldValue.setCustomField(field);
        fieldScope.setCustomField(field);
        CustomFieldTable customFieldTable = new CustomFieldTable();
      
        customFieldTable.setTableName("G_CASE_TB");
        
        fieldValue.setAuditUserID(userID);
        fieldValue.setAuditTimeStamp(new Date());
        if (customFieldValueVO.getAddedAuditUserID() == null)
        {
            fieldValue.setAddedAuditUserID(userID);
        }
        else
        {
            fieldValue.setAddedAuditUserID(customFieldValueVO
                    .getAddedAuditUserID());
        }
        if (customFieldValueVO.getAddedAuditTimeStamp() == null)
        {
            fieldValue.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            fieldValue.setAddedAuditTimeStamp(customFieldValueVO
                    .getAuditTimeStamp());
        }
        customFieldTable.setAddedAuditUserID(fieldValue.getAddedAuditUserID());
        customFieldTable.setAuditUserID(fieldValue.getAuditUserID());
        fieldScope.setCustomFieldTable(customFieldTable);
        fieldScope.setAddedAuditUserID(fieldValue.getAddedAuditUserID());
        fieldScope.setAuditUserID(fieldValue.getAuditUserID());
        fieldValue.setCustomFieldScope(fieldScope);
   
        logger
        .debug("In VO to DO converter CF version No in convertCustomFieldValuesVOToDO"
                + customFieldValueVO.getVersionNo());
        return fieldValue;
    }

    /**
     * @param correspondenceRecordVO
     * @return Set
     */
    private Set convertListOfLettersVOs(CaseDetailsVO detailsVO,
            CaseRecord caseRecord)
    {
        logger.info("convertListOfLettersVOs");

        Set setOfCaseLettersDOs = new HashSet();

        List listOfLettersVOs = detailsVO.getListOfLettersAndResponses();

        if (listOfLettersVOs != null)
        {
            for (Iterator iter = listOfLettersVOs.iterator(); iter.hasNext();)
            {
                CaseLetterResponsesXrefVO crLetterXrefVO = (CaseLetterResponsesXrefVO) iter
                        .next();
                if (crLetterXrefVO.getLetterGeneratonRequestSK() != null) {
					CaseLetterRequest caseLetterDO = new CaseLetterRequest();
					caseLetterDO.setAddedAuditUserID(detailsVO
							.getAddedAuditUserID());
					caseLetterDO.setAuditUserID(detailsVO.getAuditUserID());

					LetterGenerationRequest letterGenerationRequestDO = new LetterGenerationRequest();

					letterGenerationRequestDO.setLetterRequestSK(Long
							.valueOf(crLetterXrefVO
									.getLetterGeneratonRequestSK()));
					caseLetterDO
							.setLetterGenerationRequest(letterGenerationRequestDO);
					caseLetterDO.setCaseRecord(caseRecord);
					setOfCaseLettersDOs.add(caseLetterDO);
				}
            }
        }

        return setOfCaseLettersDOs;
    }

    /**
	 * This private method is used to create the CaseType DO by using the
	 * caseTypeSK
	 * 
	 * @param caseTypeSK
	 *            holds the caseTypeSK
	 * @return it returns the CaseType Do object
	 */
    private CaseType getCaseTypeValue(String caseTypeSK)
    {
        CaseType caseType = new CaseType();
        if (caseTypeSK != null
                && !MaintainContactManagementUIConstants.EMPTY_STRING
                        .equals(caseTypeSK))
        {
            caseType.setCaseTypeSK(Long.valueOf(caseTypeSK));
            logger.debug(" In getCaseTypeValues....");
        }
        return caseType;
    }

    /**
     * This method is used to convert the Alert VO to DO
     * 
     * @param alertVO
     *            holds the Alert VO object
     * @param caseRecord
     *            holds the CAseRecord DO
     * @return it returns
     */
    public Alert convertAlertVOToDO(AlertVO alertVO, CaseRecord caseRecord)
    {
        Alert alert = new Alert();
      //  userID="TBOINAPALLY";
        ContactManagementHelper helper = new ContactManagementHelper();
        //Added if Condition for Find_Bugs-FIX
        if(alertVO==null || caseRecord==null){
        	return alert;
        }
        alert.setCaseRecord(caseRecord);
        if (alertVO != null)
        {
        	logger.debug("Alert SK from VO $$ " + alertVO.getAlertSK());
        	alert.setAlertSK(alertVO.getAlertSK());
            logger.debug("Alert SK in VO to DO is $$ " + alert.getAlertSK());
        }
        if (alertVO != null && alertVO.getDueDate() != null)
        {
            alert.setDueDate(alertVO.getDueDate());
        }
        if (!isNullOrEmptyField(alertVO.getAlertType()))
        {
            alert.setAlertTypeCode(alertVO.getAlertType());
        }
        if (!isNullOrEmptyField(alertVO.getDescription()))
        {
            alert.setDescription(alertVO.getDescription());
        }
        if (caseRecord.getCaseType() != null)
        {
            alert.setCaseTypeSK(caseRecord.getCaseType().getCaseTypeSK());
        }
        if(alertVO != null && alertVO.getRcvgUserID()!=null && alertVO.getAlertSK() != null ) {
        	
        	EnterpriseUser enterpriseUser = new EnterpriseUser();
        	enterpriseUser.setUserID(alertVO.getRcvgUserID());
            alert.setEnterpriseUser(enterpriseUser);
        	alert.setReceivingUserID(alertVO.getRcvgUserID());
        	System.err.println("alertVO.getRcvgUserID()"+alertVO.getRcvgUserID());
        	
        }
        if (!isNullOrEmptyField(alertVO.getNotifyViaAlert())&& alertVO.getAlertSK() == null)
        {
        	
            logger.debug("While saving Alert workUnit SK is "
                    + alertVO.getNotifyViaAlert());
            System.err.println("While saving Alert workUnit SK is "+ alertVO.getNotifyViaAlert());
            //Commented for Heap dump fix defect ESPRD00935080
            /*String userID = helper.getUserIDByWUSK(alertVO.getNotifyViaAlert()); 
			logger.debug("--useridWUmap 4::"+userID);*/
            String userID = helper.getUserIDByWUSK2(alertVO.getNotifyViaAlert());
			logger.debug("++useridWUmap 4::"+userID);
            if (userID != null && !userID.equals(""))
            {
            	EnterpriseUser enterpriseUser = new EnterpriseUser();
            	enterpriseUser.setUserID(userID);
                alert.setEnterpriseUser(enterpriseUser);
                alert.setReceivingUserID(userID);
            }
            else
            {
            	EnterpriseUser enterpriseUser = new EnterpriseUser();
            	enterpriseUser.setUserID(alertVO.getRcvgUserID());
                alert.setEnterpriseUser(enterpriseUser);
                alert.setReceivingUserID(logCaseDataBean.getLoggedInUserID());
            }
        }
        //Modified for defect ESPRD00447955 starts
        else if(isNullOrEmptyField(alertVO.getNotifyViaAlert())&& alertVO.getAlertSK() == null){
        	EnterpriseUser enterpriseUser = new EnterpriseUser();
        	enterpriseUser.setUserID(alertVO.getRcvgUserID());
            alert.setEnterpriseUser(enterpriseUser);
        	alert.setReceivingUserID(alertVO.getRcvgUserID());
        }
        //Modified for defect ESPRD00447955 ends
        System.err.println("++Receiving User Id .."+ alert.getReceivingUserID()); 
        if (!isNullOrEmptyField(alertVO.getStatus()))
        {
            alert.setAlertStatusCode(alertVO.getStatus());
        }
        if (!isNullOrEmptyField(alertVO.getStepOrderNo()))
        {
            alert.setStepOrderNumber(Integer.valueOf(alertVO.getStepOrderNo()));
        }
        if (alertVO != null && alertVO.getActCMCaseStepCode() != null)
        {
            alert.setCmCaseStepCode(alertVO.getActCMCaseStepCode());
        }
        if (alertVO != null && alertVO.getActCMCaseEventCode() != null)
        {
            alert.setCmCaseEventCode(alertVO.getActCMCaseEventCode());
        }
        if (alertVO != null && alertVO.getCaseTypeSK() != null)
        {
            alert.setCaseTypeSK(alertVO.getCaseTypeSK());
        }

        if(alertVO != null && alertVO.getCaseStepSeqNum()!=null){ //added for UC-PGM-CRM-018.7_ESPRD00382416_30JAN10

        	alert.setCaseStepSeqNum(alertVO.getCaseStepSeqNum());
        }   //EOF  UC-PGM-CRM-018.7_ESPRD00382416_30JAN10

        if(alertVO != null && alertVO.getCaseEventSeqNum()!=null){//added for UC-PGM-CRM-018.4_ESPRD00382315_16FEB2010 

        	alert.setCaseEventSeqNum(alertVO.getCaseEventSeqNum());
        }        //EOF UC-PGM-CRM-018.4_ESPRD00382315_16FEB2010
        //Added if Condition for Find_Bugs-FIX
        if(alertVO != null){
        alert.setVersionNo(alertVO.getVersionNo());
        }
   
 
        alert.setAuditUserID(userID);
        alert.setAuditTimeStamp(new Date());
        if (alertVO != null && alertVO.getAddedAuditUserID() == null)
        {
            alert.setAddedAuditUserID(userID);
        }
        else
        {
            alert.setAddedAuditUserID(alertVO.getAddedAuditUserID());
        }
        if (alertVO != null && alertVO.getAddedAuditTimeStamp() == null)
        {
            alert.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            alert.setAddedAuditTimeStamp(alertVO.getAddedAuditTimeStamp());
        }
        return alert;
    }

    /**
     * This method is used to convert the Case Attachments VO to Do
     * 
     * @param attachmentVO
     *            holds the AttachmentVO object
     * @param caseRecord
     *            holds the CaseRecord object
     * @return it returns the Attachment DO
     */
    public CaseAttachCrossReference convertCaseAttachmentVOToDO(
            AttachmentVO attachmentVO, CaseRecord caseRecord)
    {
        Attachment attachment = new Attachment();
        CaseAttachCrossReference attachCrossReference = new CaseAttachCrossReference();
        
        //for defect 790505
        if(attachmentVO.getEdmsWrkUnitLevel()!=null){
        	attachment.setEdmsWrkUnitLevel(attachmentVO.getEdmsWrkUnitLevel());
        }
        if(attachmentVO.getEdmsDocType()!=null){
        	attachment.setEdmsDocType(attachmentVO.getEdmsDocType());
        }
        
        if (attachmentVO.getFinalPath() != null
                && !attachmentVO.getFinalPath().equals(""))
        {
        	  System.err.println("The final path in VO to DO is $$ "
                    + attachmentVO.getFinalPath());
//            attachment.setAttachmentFileName(attachmentVO.getFinalPath());
        	  //Modified for File upload in Log Case Attachment
              attachment.setFinalPath(attachmentVO.getFinalPath());

        }
        System.err.println("The final Name in VO to DO is $$ "
                + attachmentVO.getFileName());
        if (attachmentVO.getFileName() != null)
        {
            attachment.setAttachmentFileName(attachmentVO.getFileName());
        }
        if (attachmentVO.getDateAdded() != null)
        {
        	 System.err.println("Attachment created date in VO 2 DO is $$ "
                    + attachmentVO.getDateAdded());
            attachment
                    .setAttachmentCreatedDate(getAttachmentAddedDate(attachmentVO
                            .getDateAdded()));
        }
        if (attachmentVO.getDescription() != null)
        {
        	//Modified for defect ESPRD00644841
        	if(attachmentVO.getDescription().length()>50)
            {
            	attachment.setAttachmentDescription(attachmentVO.getDescription().substring(0,50));
            }else {
        	    attachment.setAttachmentDescription(attachmentVO.getDescription());
            }
        	//defect ends
        }
        if (attachmentVO.getAddedBy() != null)
        {
            attachment.setAttachmentAddedByName(attachmentVO.getAddedBy());
        }
        if (attachmentVO.getAttachmentPageID() != null)
        {
        	 logger.debug("Attachment page ID from UI is $$ " + attachmentVO.getAttachmentPageID());
            attachment.setAttachmentEDMSPageID(attachmentVO
                    .getAttachmentPageID());
        }
        if (attachmentVO.getAttachmentSK() != null)
        {
            attachment.setAttachmentSK(attachmentVO.getAttachmentSK());
        }
        if (attachmentVO.isDbRecord())
        {
            attachCrossReference.setHistoryIndicator(Boolean.TRUE);
        }
        else
        {
            attachCrossReference.setHistoryIndicator(Boolean.FALSE);
        }
        attachment.setAuditUserID(userID);
        attachment.setAuditTimeStamp(new Date());
        if (attachmentVO.getAddedAuditUserID() != null)
        {
            attachment.setAddedAuditUserID(attachmentVO.getAddedAuditUserID());
        }
        else
        {
            attachment.setAddedAuditUserID(userID);
        }
        if (attachmentVO.getAddedAuditTimeStamp() != null)
        {
            attachment.setAddedAuditTimeStamp(attachmentVO
                    .getAddedAuditTimeStamp());
        }
        else
        {
            attachment.setAddedAuditTimeStamp(new Date());
        }
        if (attachmentVO.getCascadeKey() != null) {
        	attachment.setCascadeKey(attachmentVO.getCascadeKey());
        }
        
        if (attachmentVO.isExistDoc()){
        	attachment.setExistDoc(attachmentVO.isExistDoc());
        	System.err.println("InSide LoGCaseVOtoDO--isExistDoc-->"+attachmentVO.isExistDoc());
        }
        attachment.setVersionNo(attachmentVO.getVersionNo());
        attachCrossReference.setAttachment(attachment);
        if (!attachmentVO.isDetachInd())
        {
            attachCrossReference.setDitachedOrAttachedIndicator(Boolean.FALSE);
        }
        else
        {
            attachCrossReference.setDitachedOrAttachedIndicator(Boolean.TRUE);
        }
        attachCrossReference.setCaseRecord(caseRecord);
        attachCrossReference.setAuditUserID(attachment.getAuditUserID());
        attachCrossReference.setAuditTimeStamp(attachment.getAuditTimeStamp());
        attachCrossReference.setAddedAuditUserID(attachment
                .getAddedAuditUserID());
        attachCrossReference.setAddedAuditTimeStamp(attachment
                .getAddedAuditTimeStamp());
        attachCrossReference.setVersionNo(attachmentVO.getCaseCrossRefVersionNo());
        return attachCrossReference;
	
    }

    private Date getAttachmentAddedDate(String addedDate)
    {
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        Date date = null;
        try
        {
            date = new Date(sdf1.parse(addedDate).getTime());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * This method is used to convert the CMRoutingVO to DO
     * 
     * @param cMRoutingVO
     *            holds the CMRoutingVO object
     * @param caseRecord
     *            holds the CaseRecord object
     * @return it returns the CaseRouting object
     */
    public CaseRouting convertCMRoutingVOToDO(CMRoutingVO cMRoutingVO,
            CaseRecord caseRecord)
    {
        logger.debug("$$ convertCMRoutingVOToDO is started $$");
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        CaseRouting caseRouting = new CaseRouting();
        WorkUnit unit = null;
        //commented for unused variables
       // LineOfBusinessHierarchy lobHirchy = null;
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        Map workUnitSKMap = new HashMap();
        Date routingDate = cMRoutingVO.getRoutingDate();
        if(routingDate!=null){
        	caseRouting.setCaseRoutingDate(cMRoutingVO.getRoutingDate());        	
        }else{
        	try
	        {
	            Format formatter;
	            formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
	            String strDate = formatter.format(new Date());
	           
	            Date routeDate = (Date) formatter.parseObject(strDate);
	            caseRouting.setCaseRoutingDate(routeDate); 
	        }
	        catch (ParseException e)
	        {
	            logger.error("Error occured while parsing Date object", e);
	        }
        }
     
        if (!isNullOrEmptyField(cMRoutingVO.getShow()))
        {
            
        	if (cMRoutingVO.getShow().equals("B"))
            {
        		workUnitSKMap = logCaseDataBean.getWorkUnitMap();
                if (!isNullOrEmptyField(cMRoutingVO.getAssignThisRecordTo()))
                {           

                    String workUnitSK = getWorkUnitForUser(cMRoutingVO
                            .getAssignThisRecordTo()).getWorkUnitSK().toString(); //logCaseDataBean.getRoutingVO().getRoutedByWorkUntiSK().toString();
			        caseRouting.setRoutedToWorkUnit(getWorkUnitForUser(workUnitSK));
			       
			        enterpriseUser.setUserID(workUnitSK);
			        unit = getWorkUnitForUser(workUnitSK);
			        unit.setWorkUnitTypeCode(cMRoutingVO.getShow());
					unit.setEnterpriseUser(enterpriseUser);
					caseRouting.setRoutedToWorkUnit(unit);
					caseRecord.setCaseAssignedToWorkUnit(unit);
					//ESPRD00652333 defect
					//lobHirchy = getLobHirchyForUser(cMRoutingVO.getAssignThisRecordTo());
//					lobHirchy = getLobHirchyForUser(cMRoutingVO.getAssignThisRecordTo());
//					caseRecord.setLobHierarchy(lobHirchy);
                    
                }else{
                	/*String workUnitSK = (String) workUnitSKMap.get(cMRoutingVO.getDeptName());*/
					caseRouting
							.setRoutedToWorkUnit(getWorkUnitForUser(cMRoutingVO.getDeptName()));

					enterpriseUser.setUserID(cMRoutingVO.getDeptName());
					unit = getWorkUnitForUser(cMRoutingVO.getDeptName());
					unit.setWorkUnitTypeCode(cMRoutingVO.getShow());
					unit.setEnterpriseUser(enterpriseUser);
					caseRouting.setRoutedToWorkUnit(unit);
					caseRecord.setCaseAssignedToWorkUnit(unit);
					System.err.println(" Business unit cMRoutingVO.getDeptName():  "+cMRoutingVO.getDeptName());
//					lobHirchy = getLobHirchyForUser(cMRoutingVO.getDeptName());
//					caseRecord.setLobHierarchy(lobHirchy);
                }
            }
            
            else if(cMRoutingVO.getShow().equals("U")){
            	if(isNullOrEmptyField(cMRoutingVO.getUserDepartment())){
            		
            		caseRouting.setRoutedToWorkUnit(getWorkUnitForUser(cMRoutingVO.getAssignThisRecordTo()));
            		logCaseDataBean.setShowUserDeptName(true);
            	}
            	else{
            		 caseRouting.setRoutedToWorkUnit(getWorkUnitForUser(cMRoutingVO.getAssignThisRecordTo()));
            		 logCaseDataBean.setShowUserDeptName(false);
            		 logCaseDataBean.setUserDeptName(cMRoutingVO.getUserDepartment());
            	}
               
                enterpriseUser.setUserID(cMRoutingVO.getAssignThisRecordTo());
                unit = caseRouting.getRoutedToWorkUnit();
                unit.setEnterpriseUser(enterpriseUser);
                caseRecord.setCaseAssignedToWorkUnit(unit);
                caseRouting.setCaseRecord(caseRecord);
            }
            else if (cMRoutingVO.getShow().equals("W")) {
            	System.err.println("++cMRoutingVO.getDeptName()"+cMRoutingVO.getDeptName());
				workUnitSKMap = logCaseDataBean.getWorkUnitSKMap();
				if (isNullOrEmptyField(cMRoutingVO.getAssignThisRecordTo())) {
					
					//String workUnitSK = (String) workUnitSKMap.get(cMRoutingVO.getDeptName());
					//System.err.println("++workUnitSK--"+workUnitSK);
					caseRouting
							.setRoutedToWorkUnit(getWorkUnitForUser(cMRoutingVO.getDeptName()));

					enterpriseUser.setUserID(cMRoutingVO.getDeptName());
					unit = getWorkUnitForUser(cMRoutingVO.getDeptName());
					logCaseDataBean.setShowAssignedTo(true);
				} else {
					caseRouting
							.setRoutedToWorkUnit(getWorkUnitForUser(cMRoutingVO
									.getAssignThisRecordTo()));

					enterpriseUser.setUserID(cMRoutingVO
							.getAssignThisRecordTo());
					unit = getWorkUnitForUser(cMRoutingVO
							.getAssignThisRecordTo());
					logCaseDataBean.setShowAssignedTo(false);
					
					//Begin - Commented the code for the defect id ESPRD00681496_23Aug2011
					/*lobHirchy = getLobHirchyForUser(cMRoutingVO.getAssignThisRecordTo());
					caseRecord.setLobHierarchy(lobHirchy);*/
					//End - Commented the code for the defect id ESPRD00681496_23Aug2011					
				}
				unit.setWorkUnitTypeCode(cMRoutingVO.getShow());
				unit.setEnterpriseUser(enterpriseUser);
				caseRouting.setRoutedToWorkUnit(unit);
				caseRecord.setCaseAssignedToWorkUnit(unit);
//				lobHirchy = getLobHirchyForUser(cMRoutingVO.getDeptName());
//				caseRecord.setLobHierarchy(lobHirchy);
			}
            
            
        }
		/* FIND BUGS_FIX*/
		// Begin - Modified the code for the defect id ESPRD00702153_30NOV2011
        if (cMRoutingVO.getRoutedBySK() != null
                && !cMRoutingVO.getRoutedBySK().equals(Long.valueOf(0)))
        {
            caseRouting.setRoutedByWorkUnit(getWorkUnitForUser(cMRoutingVO
                    .getRoutedBySK().toString()));
        }
		// End - Modified the code for the defect id ESPRD00702153_30NOV2011
/*        if (cMRoutingVO.getAddThisRecordToMyWatchlist())
        {
            caseRouting.setWatchListIndicator(Boolean.TRUE);
            
            CaseWatchList caseWatchList = new CaseWatchList();
            caseWatchList.setCaseRecord(caseRecord);
           
            caseWatchList
					.setWorkUnit(getWorkUnitForUser(ContactManagementHelper
							.getUserSK(userID).toString()));
            caseWatchList.setAddedAuditTimeStamp(new Date());
            caseWatchList.setAddedAuditUserID(userID);
            caseWatchList.setAuditTimeStamp(new Date());
            caseWatchList.setAuditUserID(userID);
            watchListSet.add(caseWatchList);
            caseRecord.setCaseWatchLists(watchListSet);
        }
        else
        {
            caseRouting.setWatchListIndicator(Boolean.FALSE);
            
        }
        caseRouting.setVersionNo(cMRoutingVO.getVersionNo());
        caseRouting.setAuditUserID(userID);
        caseRouting.setAuditTimeStamp(new Date());*/
        if (cMRoutingVO.getAddedAuditUserID() == null)
        {
            caseRouting.setAddedAuditUserID(userID);
        }
        else
        {
            caseRouting.setAddedAuditUserID(cMRoutingVO.getAddedAuditUserID());
        }
        if (cMRoutingVO.getAddedAuditTimeStamp() == null)
        {
            caseRouting.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            caseRouting.setAddedAuditTimeStamp(cMRoutingVO
                    .getAddedAuditTimeStamp());
        }
        return caseRouting;
    }

    /**
     * This method is used to conver the CaseTypeBCCPVO to DO
     * 
     * @param caseTypeBCCPVO
     *            holds the CaseTypeBCCPVO object.
     * @param caseRecord
     *            holds the CaseRecord object.
     * @return it returns the CaseRecord DO object.
     */
    public CaseRecord convertCaseTypeBCCPVOToDO(CaseTypeBCCPVO caseTypeBCCPVO,
            CaseRecord caseRecord)
    {
        CaseBCCP caseBCCP = new CaseBCCP();
        caseRecord.setCaseBCCP(caseBCCP);
       
        caseBCCP.setCaseRecord(caseRecord);
        caseBCCP.setCaseRecordRef(caseRecord.getCaseSK());
        if (!isNullOrEmptyField(caseTypeBCCPVO.getApplicationDateStr()))
        {
            caseBCCP.setApplicationDate(ContactManagementHelper.dateConverter(caseTypeBCCPVO.getApplicationDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getBccpID()))
        {
            logger.debug("BCCP ID is $$ " + caseTypeBCCPVO.getBccpID());
            caseBCCP.setBccpID(caseTypeBCCPVO.getBccpID());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getContactPerson()))
        {
            caseBCCP.setContactName(caseTypeBCCPVO.getContactPerson());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getEnrollmentDateStr()))
        {
            caseBCCP.setScreeningEnrollDate(ContactManagementHelper.dateConverter(caseTypeBCCPVO.getEnrollmentDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getAgencySite()))
        {
            caseBCCP.setCaseBCCPScreeningSiteCode(caseTypeBCCPVO
                    .getAgencySite());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getAgencyCaseManager()))
        {
            caseBCCP.setScreeningAgencyCMText(caseTypeBCCPVO
                    .getAgencyCaseManager());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getBiopsyDateStr()))
        {
            caseBCCP.setBiopsyDate(ContactManagementHelper.dateConverter(caseTypeBCCPVO.getBiopsyDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getBiopsyFindingsCd()))
        {
            caseBCCP.setCaseBCCPBiopsyFundingCode(caseTypeBCCPVO
                    .getBiopsyFindingsCd());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getRecommendationCd()))
        {
            caseBCCP.setBccpCaseRecommendCode(caseTypeBCCPVO
                    .getRecommendationCd());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getStFollowUPNum()))
        {
            logger.debug("Follow Up num is $$ "
                    + caseTypeBCCPVO.getStFollowUPNum());
            caseBCCP.setRecommendFOllowupMONumber(Integer
                    .valueOf(caseTypeBCCPVO.getStFollowUPNum()));
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getConsultDateStr()))
        {
            caseBCCP.setConsultantDate(ContactManagementHelper.dateConverter(caseTypeBCCPVO.getConsultDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getFinalStageCd()) && caseTypeBCCPVO.getFinalStageCd().length()<=2)
        {
            caseBCCP.setCaseBCCPFinalCancerStageCode(caseTypeBCCPVO
                    .getFinalStageCd());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getMetastasisAreaCd()) && caseTypeBCCPVO.getMetastasisAreaCd().length()<=2)
        {
            caseBCCP.setBccpFinalMTSTSAreaCode(caseTypeBCCPVO
                    .getMetastasisAreaCd());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getUnstagedReasonCd()))
        {
            caseBCCP.setCaseBCCPUNSTGReasonCode(caseTypeBCCPVO
                    .getUnstagedReasonCd());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getTumorSizeNum()))
        {
            caseBCCP.setFinalTumorCMSizeNumber(Double.valueOf(caseTypeBCCPVO
                    .getTumorSizeNum()));
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getNodesPositiveNum()))
        {
            
            caseBCCP.setFinalCancerNodesPositiveNumber(Integer
                    .valueOf(caseTypeBCCPVO.getNodesPositiveNum()));
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getForm778SignedInd()))
        {
           
            caseBCCP.setForm778SignIndicator(Boolean.valueOf(caseTypeBCCPVO
                    .getForm778SignedInd()));
        }

        if (!isNullOrEmptyField(caseTypeBCCPVO.getTreatmentStartedCd()) && caseTypeBCCPVO.getTreatmentStartedCd().length()<=2)
        {
            caseBCCP.setCaseBCCPTreatmentStartedCode(caseTypeBCCPVO.getTreatmentStartedCd());
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getTreatmentStartDateStr()))
        {
            caseBCCP.setTreatmentStartedDate(ContactManagementHelper.dateConverter(caseTypeBCCPVO.getTreatmentStartDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeBCCPVO.getChemoProjectedEndDateStr()))
        {
        	caseBCCP.setChemoProjectedEndDate(ContactManagementHelper.dateConverter(caseTypeBCCPVO.getChemoProjectedEndDateStr()));
        }

        if (!isNullOrEmptyField(caseTypeBCCPVO.getCaregiverName()))
        {
            caseBCCP.setCaregiverName(caseTypeBCCPVO.getCaregiverName());
        }
        

        caseBCCP.setVersionNo(caseTypeBCCPVO.getVersionNo());
        caseBCCP.setAuditUserID(userID);
        caseBCCP.setAuditTimeStamp(new Date());
        if (caseTypeBCCPVO.getAddedAuditUserID() == null)
        {
            caseBCCP.setAddedAuditUserID(userID);
        }
        else
        {
            caseBCCP.setAddedAuditUserID(caseTypeBCCPVO.getAddedAuditUserID());
        }
        if (caseTypeBCCPVO.getAddedAuditTimeStamp() == null)
        {
            caseBCCP.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            caseBCCP.setAddedAuditTimeStamp(caseTypeBCCPVO
                    .getAddedAuditTimeStamp());
        }
        // Modified for defect ESPRD00690158 starts
        if(!isNullOrEmptyField(caseTypeBCCPVO.getAgencyPhoneNumber())){
        	System.err.println("5++caseTypeBCCPVO.getAgencyPhoneNumber()"+caseTypeBCCPVO.getAgencyPhoneNumber());
        	if(caseTypeBCCPVO.getAgencyPhoneVo()!=null){
        		PhoneVO phoneVO = caseTypeBCCPVO.getAgencyPhoneVo();
        		Phone phone =new Phone();
        		phone.setPhoneSK(phoneVO.getPhoneSK());
                phone.setPhoneNumber(caseTypeBCCPVO.getAgencyPhoneNumber());
                
                
                phone.setOutOfServiceIndicator(Boolean.FALSE);
                phone.setDummyInd("N");
               
                phone.setVersionNo(phoneVO.getVersionNo());
                phone.setAddedAuditTimeStamp(phoneVO.getAddedAuditTimeStamp());
            	phone.setAddedAuditUserID(phoneVO.getAddedAuditUserID()); 	
               
                if(caseTypeBCCPVO.getAgencyPhoneVo().getPhoneNumber().equals(caseTypeBCCPVO.getAgencyPhoneNumber())){
                	phone.setAuditUserID(caseTypeBCCPVO.getAuditUserID());
                	 phone.setAuditTimeStamp(caseTypeBCCPVO.getAuditTimeStamp());
        		}
                else
                {
                	phone.setAuditUserID(userID);
                	phone.setAuditTimeStamp(new Date());
                }
                caseBCCP.setScreeningAgencyPhone(phone);	
        	}
        	else
        	{
        		Phone phone =new Phone();
                phone.setPhoneNumber(caseTypeBCCPVO.getAgencyPhoneNumber());
                phone.setAddedAuditTimeStamp(new Date());
                phone.setAddedAuditUserID(userID); 
                phone.setAuditUserID(userID);
                phone.setOutOfServiceIndicator(Boolean.FALSE);
                phone.setDummyInd("N");
                phone.setAuditTimeStamp(new Date());
                caseBCCP.setScreeningAgencyPhone(phone);	
        	
        	}
        }else{
        	System.err.println("phone number either empty or null 1");
        }
        
        if(!isNullOrEmptyField(caseTypeBCCPVO.getCaregiverPhone())){
        	if(caseTypeBCCPVO.getCareGiverPhoneVo()!=null){
        		PhoneVO phoneVO = caseTypeBCCPVO.getCareGiverPhoneVo();
        		Phone phone =new Phone();
        		phone.setPhoneSK(phoneVO.getPhoneSK());
                phone.setPhoneNumber(caseTypeBCCPVO.getCaregiverPhone());
                
                phone.setOutOfServiceIndicator(Boolean.FALSE);
                phone.setDummyInd("N");
                phone.setVersionNo(phoneVO.getVersionNo());
                phone.setAddedAuditTimeStamp(phoneVO.getAddedAuditTimeStamp());
            	phone.setAddedAuditUserID(phoneVO.getAddedAuditUserID()); 	
                if(caseTypeBCCPVO.getCareGiverPhoneVo().getPhoneNumber().equals(caseTypeBCCPVO.getCaregiverPhone())){
                	 phone.setAuditTimeStamp(caseTypeBCCPVO.getAuditTimeStamp());
                	 phone.setAuditUserID(caseTypeBCCPVO.getAuditUserID());
        		}
                else
                {
                	phone.setAuditUserID(userID);
                	phone.setAuditTimeStamp(new Date());
                }
                caseBCCP.setCaregiverPhone(phone);
        	}else
        	{
        		Phone phone =new Phone();
                phone.setPhoneNumber(caseTypeBCCPVO.getCaregiverPhone());
                phone.setAddedAuditTimeStamp(new Date());
                phone.setAddedAuditUserID(userID); 
                phone.setAuditUserID(userID);
                phone.setOutOfServiceIndicator(Boolean.FALSE);
                phone.setDummyInd("N");
                phone.setAuditTimeStamp(new Date());
                caseBCCP.setCaregiverPhone(phone);	
        	
        	}
        }else{
        	System.err.println("phone number either empty or null 2");
        }
      //defect ESPRD00690158 ends
        logger.debug("CaseSK from caseRecord : "
                + caseBCCP.getCaseRecord().getCaseSK());
        logger.debug("CaseSK from BCCP : " + caseBCCP.getCaseRecordRef());
        return caseRecord;
    
    }

    /**
     * This method is used to conver the CaseTypeARSVO to DO
     * 
     * @param caseTypeARSVO
     *            holds the CaseTypeARSVO object.
     * @param caseRecord
     *            holds the CaseRecord object.
     * @return it returns the CaseRecord DO object.
     */
    public CaseRecord convertCaseTypeARSVOToDO(CaseTypeARSVO caseTypeARSVO,
            CaseRecord caseRecord)
    {
    	logger.info("inside convertCaseTypeARS VO To DO");
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
/*        CaseARS caseARS = new CaseARS();
        
       caseRecord.setCaseARS(caseARS);
        caseARS.setCaseRecord(caseRecord);
        caseARS.setCaseRecordRef(caseRecord.getCaseSK());*/
        
            CaseARS caseARS = new CaseARS();
        
            caseARS.setCaseRecordRef(caseRecord.getCaseSK());
            caseARS.setAuditUserID(userID);
       
            caseARS.setAuditTimeStamp(new Date());
            caseARS.setAddedAuditUserID(userID);
     
            caseARS.setAddedAuditTimeStamp(new Date());
            caseARS.setVersionNo(caseTypeARSVO.getVersionNo());
        
        if (caseTypeARSVO.getAppealReceivedInd() != null)
        {
            caseARS.setAppealReceivedIndicator(Boolean.valueOf(caseTypeARSVO
                    .getAppealReceivedInd()));
        }
        if (caseTypeARSVO.getFieldAuditRequiredInd() != null)
        {
            caseARS.setFieldAuditRequiredInd(Boolean.valueOf(caseTypeARSVO
                    .getFieldAuditRequiredInd()));
        }
        if (caseTypeARSVO.getFieldAuditDateStr() != null)
        { 
            String fieldAuditDate = ContactManagementHelper
            .getValidDateFormat(caseTypeARSVO.getFieldAuditDateStr()); 
            caseARS.setFieldAuditDate(ContactManagementHelper
                    .dateConverter(fieldAuditDate));  
            
        }
        if (caseTypeARSVO.getHomeOfficeIndicator() != null)
        {
            caseARS.setHomeOfficeInd(Boolean.valueOf(caseTypeARSVO
                    .getHomeOfficeIndicator()));
        }
        if (caseTypeARSVO.getFacilityFiscalYearEndDate() != null
                && !caseTypeARSVO.getFacilityFiscalYearEndDate().equals(""))
        {
            String facltyFiscalDate = ContactManagementHelper
                    .getValidDateFormat(caseTypeARSVO.getFacilityFiscalYearEndDate());            
            caseARS.setFacilityFiscalYearEndDate(ContactManagementHelper
                    .dateConverter(facltyFiscalDate));
        }
        if (caseTypeARSVO.getPictureDateStr() != null)
        {
            Map picMap = logCaseDataBean.getPicIDMap();
            String pictDate = (String)picMap.get(caseTypeARSVO.getPictureDateStr());
            caseARS.setCaseARSCRPICDate(pictDate);
        }
        if (caseTypeARSVO.getRateSettingDateStr() != null && !caseTypeARSVO.getRateSettingDateStr().equals(MaintainContactManagementUIConstants.EMPTY_STRING))
        {                       
            String rateSettingDate = ContactManagementHelper
            .getValidDateFormat(caseTypeARSVO.getRateSettingDateStr()); 
            caseARS.setRateSettingDate(ContactManagementHelper
                    .dateConverter(rateSettingDate)); 
        }
        if (caseTypeARSVO.getPrivatePayRatesLoadedInd() != null)
        {
            caseARS.setPrivatePayRatesLoadedInd(Boolean.valueOf(caseTypeARSVO
                    .getPrivatePayRatesLoadedInd()));
        }
       /* if (caseTypeARSVO.getPrivatePayRatesReceivedInd() != null)
        {
            caseARS.setPrivatePayRatesReceivedInd(Boolean.valueOf(caseTypeARSVO
                    .getPrivatePayRatesReceivedInd()));
        }*/
      
        if (caseTypeARSVO.getDeskReviewStartDate() != null
                && !caseTypeARSVO.getDeskReviewStartDate().equals(""))
        {
            String deskReviewStartDate = ContactManagementHelper
                    .getValidDateFormat(caseTypeARSVO.getDeskReviewStartDate());            
            caseARS.setHomeOfficeDeskReviewResultsDate(ContactManagementHelper
                    .dateConverter(deskReviewStartDate));
        } 
        if (caseTypeARSVO.getStateFiscalYearEndDate() != null
                && !caseTypeARSVO.getStateFiscalYearEndDate().equals(""))
        {
            String fiscalDate = ContactManagementHelper
                    .getValidDateFormat(caseTypeARSVO.getStateFiscalYearEndDate());            
            caseARS.setFiscalYearEndDate(ContactManagementHelper
                    .dateConverter(fiscalDate));
        }
        
        /*         
        if (caseTypeARSVO.getHomeOfficeIndicator() != null)
        {
            caseARS.setHomeOfficeInd(Boolean.valueOf(caseTypeARSVO
                    .getHomeOfficeIndicator()));
        }
        caseARS.setVersionNo(caseTypeARSVO.getVersionNo());
        caseARS.setPerformCostReportsXChecksInd(Boolean.FALSE);
        caseARS.setStaffingPatternsUpdateInd(Boolean.FALSE);
        caseARS.setScheduleKReceivedInd(Boolean.FALSE);
        caseARS.setAuditUserID(userID);
        caseARS.setAuditTimeStamp(new Date());
        if (caseTypeARSVO.getAddedAuditUserID() == null)
        {
            caseARS.setAddedAuditUserID(userID);
        }
        else
        {
            caseARS.setAddedAuditUserID(caseTypeARSVO.getAddedAuditUserID());
        }
        if (caseTypeARSVO.getAddedAuditTimeStamp() == null)
        {
            caseARS.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            caseARS.setAddedAuditTimeStamp(caseTypeARSVO
                    .getAddedAuditTimeStamp());
        }*/
       
        caseRecord.setCaseARS(caseARS);
        logger.info("End of convertCaseTypeARS VO To DO");
        return caseRecord;
    }

    /**
     * This method is used to conver the CaseTypeDDUVO to DO
     * 
     * @param caseTypeDDUVO
     *            holds the CaseTypeDDUVO object.
     * @param caseRecord
     *            holds the CaseRecord object.
     * @return it returns the CaseRecord DO object.
     */
    private CaseRecord convertCaseTypeDDUVOToDO(CaseTypeDDUVO caseTypeDDUVO,
            CaseRecord caseRecord)
    {
        logger.info("in DDU Convertor...");
        CaseDDU caseDDU = new CaseDDU();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        caseRecord.setCaseDDU(caseDDU);
    
        caseDDU.setCaseRecord(caseRecord);
        caseDDU.setCaseRecordRef(caseRecord.getCaseSK());
        logger.debug("caseTypeDDUVO.getReviewInitiatedDateStr()==>"+caseTypeDDUVO
                .getReviewInitiatedDateStr());
        if (!isNullOrEmptyField(caseTypeDDUVO.getReviewInitiatedDateStr()))
        {
            caseDDU.setReviewInitiatedDate(ContactManagementHelper.dateConverter(caseTypeDDUVO
                    .getReviewInitiatedDateStr()));
            logger.debug("caseDDU.getReviewInitiatedDate()==>"+caseDDU.getReviewInitiatedDate());
        }
       
		if (!isNullOrEmptyField(caseTypeDDUVO.getPacketReceivedDateStr()))
		{
		caseDDU
		        .setPacketReceivedDate(ContactManagementHelper.dateConverter(caseTypeDDUVO
	                    .getPacketReceivedDateStr()));
		}
		
        if (!isNullOrEmptyField(caseTypeDDUVO.getApprovedBeginDateStr()))
        {
            caseDDU.setApplicationBeginDate(ContactManagementHelper.dateConverter(caseTypeDDUVO
                    .getApprovedBeginDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getAppealDecisionDateStr()))
        {
            caseDDU.setAppealDecisionDate(ContactManagementHelper.dateConverter(caseTypeDDUVO
                    .getAppealDecisionDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getApplicationTypeCd()))
        {
            caseDDU.setCaseDDUApplicationTypeCode(caseTypeDDUVO
                    .getApplicationTypeCd());
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getApplicationDateStr()))
        {
            caseDDU.setApplicationDate(ContactManagementHelper.dateConverter(caseTypeDDUVO.getApplicationDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getSubstantiallyCompletedDateStr()))
        {
            caseDDU.setCompletedDate(ContactManagementHelper.dateConverter(caseTypeDDUVO
                    .getSubstantiallyCompletedDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getAuthorizedRepCd()))
        {
            caseDDU.setCaseDDUAuthorizedRepresentTyCd(caseTypeDDUVO
                    .getAuthorizedRepCd());
        }

        if (!isNullOrEmptyField(caseTypeDDUVO.getReviewRequiredInd()))
        {
        	 logger.debug("caseTypeDDUVO.getReviewRequiredInd():"
                    + caseTypeDDUVO.getReviewRequiredInd());
            caseDDU.setReviewRequiredIndicator(Boolean.valueOf(caseTypeDDUVO
                    .getReviewRequiredInd()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getSchDateOfReviewStr()))
        {
            caseDDU.setScheduleReviewDate(ContactManagementHelper.dateConverter(caseTypeDDUVO.getSchDateOfReviewStr()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getReceivedDateStr()))
        {
            caseDDU.setReceivedDate(ContactManagementHelper.dateConverter(caseTypeDDUVO.getReceivedDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getReceiptDateStr()))
        {
            caseDDU.setReceiptDate(ContactManagementHelper.dateConverter(caseTypeDDUVO.getReceiptDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getEvaluationTypeCd()))
        {
            caseDDU.setCaseDDUEvaluateType(caseTypeDDUVO.getEvaluationTypeCd());
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getMedicalDiagnosisCd()))
        {

            caseDDU.setCaseDDUMedicalDiagnosisCode(caseTypeDDUVO
                    .getMedicalDiagnosisCd());
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getPsychiatricDiagnosisCd()))
        {
            caseDDU.setCaseDDUpsychiatricDiagnosisCode(caseTypeDDUVO
                    .getPsychiatricDiagnosisCd());
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getResponseIndicator()))
        {
        	 logger.debug("caseTypeDDUVO.getResponseIndicator():"
                    + caseTypeDDUVO.getResponseIndicator());
            caseDDU.setResponseIndicator(Boolean.valueOf(caseTypeDDUVO
                    .getResponseIndicator()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getDecisionDateStr()))
        {
            caseDDU.setDecisionDate(ContactManagementHelper.dateConverter(caseTypeDDUVO.getDecisionDateStr()));
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getLevelOfCareCd()))
        {
            caseDDU.setCaseDDULOCCode(caseTypeDDUVO.getLevelOfCareCd());
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getDenialReasonCd()))
        {
            caseDDU.setCaseDDUdenialReasonCode(caseTypeDDUVO
                    .getDenialReasonCd());
        }
        if (!isNullOrEmptyField(caseTypeDDUVO.getCloseCodeCd()))
        {
            caseDDU.setCaseDDUCloseTypeCode(caseTypeDDUVO.getCloseCodeCd());
        }
        caseDDU.setVersionNo(caseTypeDDUVO.getVersionNo());
        caseDDU.setAuditUserID(userID);
        caseDDU.setAuditTimeStamp(new Date());
        if (caseTypeDDUVO.getAddedAuditUserID() == null)
        {
            caseDDU.setAddedAuditUserID(userID);
        }
        else
        {
            caseDDU.setAddedAuditUserID(caseTypeDDUVO.getAddedAuditUserID());
        }
        if (caseTypeDDUVO.getAddedAuditTimeStamp() == null)
        {
            caseDDU.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            caseDDU.setAddedAuditTimeStamp(caseTypeDDUVO
                    .getAddedAuditTimeStamp());
        }
        if (!logCaseDataBean.getSelectedUnUsualList().isEmpty())
        {
        	 logger.debug("unusualSelectedList is not empty while saving DDU");
           //COMMENTED FOR CRITICAL DEFECT ESPRD00538820
		   // Begin - Uncommented the code for the defect id ESPRD00682457_05SEP2011
            caseDDU
                    .setCaseDDUUnusualCircumstances(convertCaseDDUUnusualCircumstances(
                            logCaseDataBean.getSelectedUnUsualList(),
                            caseDDU));
  		   // End - Uncommented the code for the defect id ESPRD00682457_05SEP2011 							
        }
        else
        {
            logger.debug("unusualSelectedList is empty while saving DDU");
        }
        logger.info("End DDU Convertor");
        return caseRecord;
    }

    private Set convertCaseDDUUnusualCircumstances(List unUsualList,
            CaseDDU caseDDU)
    {
        logger.debug("$$ convertCaseDDUUnusualCircumstances is started $$");
        Set unUnusalDOSet = new HashSet();
        if (unUsualList != null && !unUsualList.isEmpty())
        {
           
            Iterator iterator = unUsualList.iterator();
            while (iterator.hasNext())
            {
                CaseDDUUnusualCircumstances circumstances = new CaseDDUUnusualCircumstances();
                SelectItem si = (SelectItem) iterator.next();
               
                circumstances.setCircumstanceTypeCode(si.getValue().toString());
                circumstances.setCaseSK(caseDDU.getCaseRecordRef());
                circumstances.setVersionNo(caseDDU.getVersionNo());
                circumstances.setAuditUserID(userID);
                circumstances.setAuditTimeStamp(new Date());
                if (caseDDU.getAddedAuditUserID() == null)
                {
                    circumstances.setAddedAuditUserID(userID);
                }
                else
                {
                    circumstances.setAddedAuditUserID(caseDDU
                            .getAddedAuditUserID());
                }
                if (caseDDU.getAddedAuditTimeStamp() == null)
                {
                    circumstances.setAddedAuditTimeStamp(new Date());
                }
                else
                {
                    circumstances.setAddedAuditTimeStamp(caseDDU
                            .getAddedAuditTimeStamp());
                }
                unUnusalDOSet.add(circumstances);
            }
        }
        return unUnusalDOSet;
    }

    /**
     * This method is used to convert the CaseEventsVO to DO
     * 
     * @param caseEventsVO
     *            holds the CaseEventsVO object
     * @param caseRecord
     *            holds the CaseRecord object
     * @return it returns the ActCMCaseEvent object
     */
    public ActCMCaseEvent convertCaseEventsVOToDO(CaseEventsVO caseEventsVO,
            CaseRecord caseRecord)
    {
        ActCMCaseEvent actCMCaseEvent = new ActCMCaseEvent();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        actCMCaseEvent.setCaseRecord(caseRecord);
        logger.debug("Case Type Sk in VO to DO is $$ "
                + caseEventsVO.getCaseTypeSK());          
   
        System.err.println("caseEventsVO.getCaseSK(): "+caseEventsVO.getCaseSK());
        if(caseEventsVO.getCaseSK()!= null)
        {
        	actCMCaseEvent.setCaseSK(Long.valueOf(caseEventsVO.getCaseSK()));
        	logger.info("actCMCaseEvent.getCaseSK"+actCMCaseEvent.getCaseSK());
        }
        if(caseEventsVO.getCaseEventSeqNum()!=null)
        {
        	actCMCaseEvent.setCaseEventSeqNum(caseEventsVO.getCaseEventSeqNum());
        	  logger.info("actCMCaseEvent.getCaseEventSeqNum"+actCMCaseEvent.getCaseEventSeqNum());
        	
        }        
    
  
        if (caseRecord.getCaseType().getCaseTypeSK() != null)
        {
        	actCMCaseEvent.setCaseTypeSK(caseRecord.getCaseType().getCaseTypeSK());
        
        }
        if (caseEventsVO.getEventDate() != null)
        {
            actCMCaseEvent.setEventDate(caseEventsVO.getEventDate());
            actCMCaseEvent.setEventDueDate(caseEventsVO.getEventDate());
        }
        logger
        .debug("Event Type Code.in VO..."
                + caseEventsVO.getEventTypeCd());
        if (!isNullOrEmptyField(caseEventsVO.getEventTypeCd()))
        {
            actCMCaseEvent.setCmCaseEventCode(caseEventsVO.getEventTypeCd());

        }
        logger.debug("Event Type Code.in DO..."
                + actCMCaseEvent.getCmCaseEventCode());
        if (!isNullOrEmptyField(caseEventsVO.getDesc()))
        {
            actCMCaseEvent.setDescription(caseEventsVO.getDesc());
        }
        if (caseEventsVO.getEventDate() != null)
        {
            actCMCaseEvent.setEventDate(caseEventsVO.getEventDate());
        }
        if (!isNullOrEmptyField(caseEventsVO.getTime()))
        {
            actCMCaseEvent.setTicklerTimeStamp(contactManagementHelper
                    .dateConverter(caseEventsVO.getTime()));
        }
        if (!isNullOrEmptyField(caseEventsVO.getNotifyViaAlert()))
        {
            actCMCaseEvent
                    .setEnterpriseUser(getEnterpriseUserForUser(caseEventsVO
                            .getNotifyViaAlert()));
        }
        if (!isNullOrEmptyField(caseEventsVO.getAlertBasedOn()))
        {
            actCMCaseEvent.setAlertBasedOnColName(caseEventsVO
                    .getAlertBasedOn());
        }
        if (!isNullOrEmptyField(caseEventsVO.getSendAlertDaysCd()))
        {
            actCMCaseEvent.setSendAlertDaysCode(caseEventsVO
                    .getSendAlertDaysCd());
        }
        // Added for the defect id  ESPRD00334553 
        if (caseEventsVO.getAfterOrBeforeCd() != null) {
        	if (MaintainContactManagementUIConstants.ALERT_BASED_DESC_FOR_VALID_CODE_B_FOR_BEFORE.equals(caseEventsVO.getAfterOrBeforeCd())) {
				actCMCaseEvent.setBeforeAfterCode(MaintainContactManagementUIConstants.ALERT_BASED_VALID_CODE_B_FOR_BEFORE);

			} else if (MaintainContactManagementUIConstants.ALERT_BASED_DESC_FOR_VALID_CODE_A_FOR_AFTER.equals(caseEventsVO.getAfterOrBeforeCd())) {
				actCMCaseEvent.setBeforeAfterCode(MaintainContactManagementUIConstants.ALERT_BASED_VALID_CODE_A_FOR_AFTER);
			}
			
		}
      
        if (!isNullOrEmptyField(caseEventsVO.getStatusCd()))
        {
            actCMCaseEvent.setEventStatusCode(caseEventsVO.getStatusCd());
        }
        if (caseEventsVO.getDispositionDate() != null)
        {
        	 logger
             .debug("In VO to DO of ActCMCaseEvents disposition date is $$ "
                     + caseEventsVO.getDispositionDate());
            actCMCaseEvent
                    .setDispositionDate(caseEventsVO.getDispositionDate());
        }
        if (!isNullOrEmptyField(caseEventsVO.getTemplate()))
        {
            actCMCaseEvent
                    .setLetterTemplate(getLetterTemplateForLetterData(caseEventsVO
                            .getTemplate()));
        }
        actCMCaseEvent.setVersionNo(caseEventsVO.getVersionNo());
//      CaseEvent Appointment fix
        System.err.println("caseEventsVO.getEventTypeCd() testing : "+caseEventsVO.getEventTypeCd());
       
        	if("D".equals(caseEventsVO.getEventTypeCd().trim())){
        	//	if("D".equals(caseEventsVO.getEventTypeCdDesc())){
        		
        	CaseEventAppointment caseEventAppointment  = new CaseEventAppointment();
        	
        	
        	logger.debug("caseEventsVO.getCaseSK(): "+caseEventsVO.getCaseSK());
        	if(caseEventsVO.getCaseSK()!=null){
        		caseEventAppointment.setCaseSK(Long.valueOf(caseEventsVO.getCaseSK()));
        	}
        	
        	if(caseEventsVO.getCaseTypeSK()!= null){
        		caseEventAppointment.setCaseTypeSK(Long.valueOf(caseEventsVO.getCaseTypeSK()));
        	}
        	System.err.println("caseEventsVO.getCaseTypeSK(): "+caseEventsVO.getCaseTypeSK());
        	if(caseEventsVO.getEventTypeCd()!=null){
        		caseEventAppointment.setCmCaseEventCode(caseEventsVO.getEventTypeCd());
        	}
        	System.err.println("caseEventsVO.getEventTypeCd(): "+caseEventsVO.getEventTypeCd());
        	if(caseEventsVO.getCaseEventSeqNum()!=null){
        		caseEventAppointment.setCaseEventSeqNum(caseEventsVO.getCaseEventSeqNum());
        	}
        	if(caseEventsVO.getProviderHospital()!= null && ! caseEventsVO.getProviderHospital().equals("")){
        		caseEventAppointment.setProviderCommonEntitySK(Long.valueOf(caseEventsVO.getProviderHospital()));
        	}
        	System.err.println("caseEventsVO.getProviderCommonEntitySk() "+caseEventsVO.getProviderHospital());
        	if(caseEventsVO.getDiagnosisCode1()!=null && ! caseEventsVO.getDiagnosisCode1().equals("")){
        		caseEventAppointment.setDiagnosticExam1Text(caseEventsVO.getDiagnosisCode1());
        	}
        	System.err.println("caseEventsVO.getDiagnosisCode1() "+caseEventsVO.getDiagnosisCode1());
        	if(caseEventsVO.getDiagnosisCode2()!=null && ! caseEventsVO.getDiagnosisCode2().equals("")){
        		caseEventAppointment.setDiagnosticExam2Text(caseEventsVO.getDiagnosisCode2());
        	}
        	System.err.println("caseEventsVO.getDiagnosisCode2() "+caseEventsVO.getDiagnosisCode2());
        	if(caseEventsVO.getExamCode1()!=null && ! caseEventsVO.getExamCode1().equals("")){
        		caseEventAppointment.setTest1Code(caseEventsVO.getExamCode1());
        	}
        	System.err.println("caseEventsVO.getExamCode1() "+caseEventsVO.getExamCode1());
        	if(caseEventsVO.getExamCode2()!=null && ! caseEventsVO.getExamCode2().equals("")){
        		caseEventAppointment.setTest2Code(caseEventsVO.getExamCode2());
        	}
        	System.err.println("caseEventsVO.getExamCode2(): "+caseEventsVO.getExamCode2());
        	caseEventAppointment.setVersionNo(caseEventsVO.getApptVersionNo());
        	
        	if(caseEventsVO.getCaseEventAppintmentAuditAddedUserId()!=null){
        		caseEventAppointment.setAddedAuditUserID(caseEventsVO.getCaseEventAppintmentAuditAddedUserId());
        	}else{
        		caseEventAppointment.setAddedAuditUserID(userID);
        	}
        	
        	if(caseEventsVO.getCaseEventAppintmentAuditAddedTimestamp()!=null){
        		caseEventAppointment.setAddedAuditTimeStamp(caseEventsVO.getCaseEventAppintmentAuditAddedTimestamp());
        	}else
        	{
        		caseEventAppointment.setAddedAuditTimeStamp(new Date());
        	}
        	
        	if(caseEventsVO.getAuditUserID()!=null){
        		caseEventAppointment.setAuditUserID(caseEventsVO.getAuditUserID());
        	}else{
        		caseEventAppointment.setAuditUserID(userID);
        	}
        	
        	if(caseEventsVO.getAuditTimeStamp()!= null){
        		caseEventAppointment.setAuditTimeStamp(caseEventsVO.getAuditTimeStamp());
        	}
        	else{
        		caseEventAppointment.setAuditTimeStamp(new Date());
        	}
        	
        	if (!isNullOrEmptyField(caseEventsVO.getProviderHospital()) ||
        			caseEventsVO.getDiagnosisCode2()!=null && ! caseEventsVO.getDiagnosisCode2().equals("") || 
					caseEventsVO.getDiagnosisCode1()!=null && ! caseEventsVO.getDiagnosisCode1().equals("") ||
					caseEventsVO.getExamCode1()!=null && ! caseEventsVO.getExamCode1().equals("") ||
					caseEventsVO.getExamCode2()!=null && ! caseEventsVO.getExamCode2().equals("")) {
        
        				actCMCaseEvent.addCaseEventAppointment(caseEventAppointment);
        	}
        }
        
        //EOF 
        
        if (caseEventsVO.getAddedAuditTimeStamp() == null)
        {
            actCMCaseEvent.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            actCMCaseEvent.setAddedAuditTimeStamp(caseEventsVO
                    .getAddedAuditTimeStamp());
        }
        if (caseEventsVO.getAddedAuditUserID() == null)
        {
            actCMCaseEvent.setAddedAuditUserID(userID);
        }
        else
        {
            actCMCaseEvent.setAddedAuditUserID(caseEventsVO
                    .getAddedAuditUserID());
        }
        actCMCaseEvent.setAuditTimeStamp(new Date());
        actCMCaseEvent.setAuditUserID(userID);
        return actCMCaseEvent;
    }

    /**
     * This method is used to convert the CaseStepsVO to DO
     * 
     * @param caseStepsVO
     *            holds the CaseStepsVO object
     * @param caseRecord
     *            holds the CaseRecord object
     * @return it returns the ActCMCaseStep object
     */
    public ActCMCaseStep convertCaseStepsVOToDO(CaseStepsVO caseStepsVO,
            CaseRecord caseRecord)
    {
        ActCMCaseStep actCMCaseStep = new ActCMCaseStep();
        actCMCaseStep.setCaseRecord(caseRecord);
       
        
        if(caseStepsVO.getCaseSK()!= null)
        {
        	actCMCaseStep.setCaseSK(Long.valueOf(caseStepsVO.getCaseSK()));
        	logger.info("actCMCaseStep.getCaseSK in convertCaseStepsVOToDO "+actCMCaseStep.getCaseSK());
        }
        if (caseStepsVO.getCaseStepSeqNum() != null)
        {
            actCMCaseStep.setCaseStepSeqNum(caseStepsVO.getCaseStepSeqNum());
            logger.info("actCMCaseStep.getCaseStepSeqNum in convertCaseStepsVOToDO "+actCMCaseStep.getCaseStepSeqNum());

        }
  
        if (caseRecord.getCaseType().getCaseTypeSK() != null)
        {
           
        	actCMCaseStep.setCaseTypeSK(caseRecord.getCaseType().getCaseTypeSK());
        }
       
        if (!isNullOrEmptyField(caseStepsVO.getOrder()))
        {
            actCMCaseStep.setStepOrderNumber(Integer.valueOf(caseStepsVO
                    .getOrder()));
        }
        logger.debug("Step Order Number ## "
                + actCMCaseStep.getStepOrderNumber());
        if (!isNullOrEmptyField(caseStepsVO.getCaseStepCode()))
        {
            actCMCaseStep.setCmCaseStepCode(caseStepsVO.getCaseStepCode());
        }
        if (!isNullOrEmptyField(caseStepsVO.getDescription()))
        {
           
           
            actCMCaseStep.setCmCaseStepCode(caseStepsVO.getDescription());
            
          
        }
        if (!isNullOrEmptyField(caseStepsVO.getRouteTo()))
        {
            actCMCaseStep.setAssignWorkUnit(getWorkUnitForUser(caseStepsVO
                    .getRouteTo()));
        }
        logger.debug("ACTCaseStep status code $$ " + caseStepsVO.getStatus());
        if (!isNullOrEmptyField(caseStepsVO.getStatus()))
        {
            actCMCaseStep.setStepStatusCode(caseStepsVO.getStatus());
        }
        if (caseStepsVO.getDateStarted() != null)
        {
            actCMCaseStep.setStartDate(caseStepsVO.getDateStarted());
        }
        if (!isNullOrEmptyField(caseStepsVO.getExpectedDaysToComplete()))
        {
            actCMCaseStep.setDaysToCompleteNumber(Integer.valueOf(caseStepsVO
                    .getExpectedDaysToComplete()));
        }
        if (!isNullOrEmptyField(caseStepsVO.getCompletedBasedOn()))
        {
            actCMCaseStep.setCompletBasedOnColName(caseStepsVO
                    .getCompletedBasedOn());
        }
        if (caseStepsVO.getExpectedCompletionDate() != null)
        {
            actCMCaseStep.setExpectedCompletionDate(caseStepsVO
                    .getExpectedCompletionDate());
        }
     // Begin - modified the code for the Defect ESPRD00709364
        if (caseStepsVO.getCompletionDate() != null)
        {
            actCMCaseStep.setCompletionDate(caseStepsVO
                    .getCompletionDate());
        }
     // End - modified the code for the Defect ESPRD00709364
       
        if (!isNullOrEmptyField(caseStepsVO.getNotifyViaAlert()))
        {
            actCMCaseStep.setAlertWorkUnit(getWorkUnitForUser(caseStepsVO.getNotifyViaAlert()));
            
            ContactManagementHelper helper = new ContactManagementHelper();
            //Commented for Heap dump fix defect ESPRD00935080
            /*String userID = helper.getUserIDByWUSK(caseStepsVO.getNotifyViaAlert()); 
			logger.debug("--useridWUmap 5::"+userID);*/
            String userID = helper.getUserIDByWUSK2(caseStepsVO.getNotifyViaAlert());
			logger.debug("++useridWUmap 5::"+userID);
            if (userID != null)
            {
            	EnterpriseUser enterpriseUser = new EnterpriseUser();
            	enterpriseUser.setUserID(userID);
            	actCMCaseStep.setEnterpriseUser(enterpriseUser);
            }
        }
        if (!isNullOrEmptyField(caseStepsVO.getAlertBasedOnValue()))
        {
            actCMCaseStep.setAlertBasedOnColName(caseStepsVO
                    .getAlertBasedOnValue());
        }
        //ESPRD00351152
        if (!isNullOrEmptyField(caseStepsVO.getAlertBasedOnStr()))
        {
            actCMCaseStep.setAlertBasedOnColName(caseStepsVO
                    .getAlertBasedOnStr());
        }
        //EOF ESPRD00351152
        //Modified for defect ESPRD00538641 starts
        System.err.println("++ while saving caseStepsVO.getSendAlertDaysStr()::"+caseStepsVO.getSendAlertDaysStr());
        if (!isNullOrEmptyField(caseStepsVO.getSendAlertDaysStr()))
        {
            actCMCaseStep.setSendAlertDaysCode(caseStepsVO.getSendAlertDaysStr());
        }
        //defect ESPRD00538641 ends
        if (caseStepsVO.getBeforeOrAfterInd() != null)
        {
            actCMCaseStep.setBeforeAfterCode(caseStepsVO.getBeforeOrAfterInd());
        }
        if (!isNullOrEmptyField(caseStepsVO.getTemplate()))
        {
            actCMCaseStep
                    .setLetterTemplate(getLetterTemplateForLetterData(caseStepsVO
                            .getTemplate()));
        }
        actCMCaseStep.setVersionNo(caseStepsVO.getVersionNo());
        if (caseStepsVO.getAddedAuditTimeStamp() == null)
        {
            actCMCaseStep.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            actCMCaseStep.setAddedAuditTimeStamp(caseStepsVO
                    .getAddedAuditTimeStamp());
        }
        if (caseStepsVO.getAddedAuditUserID() == null)
        {
            actCMCaseStep.setAddedAuditUserID(userID);
        }
        else
        {
            actCMCaseStep
                    .setAddedAuditUserID(caseStepsVO.getAddedAuditUserID());
        }
        actCMCaseStep.setAuditTimeStamp(new Date());
        actCMCaseStep.setAuditUserID(userID);
        return actCMCaseStep;
    }

    /**
     * This method is used to convert the set of caseCorrespondence Vo to Dos
     * 
     * @param associatedCRList
     *            holds the existing CaseCorrespondence VOs
     * @param caseRecord
     *            holds the CaseRecord reference
     * @return Set contains list of CaseCorrespondence records
     */
    public Set convertCaseCorrespondenceVOToDO(List associatedCRList,
            CaseRecord caseRecord)
    {
        AssociatedCorrespondenceVO correspondenceVO = null;
        CaseCorrespondence caseCorrespondence = null;
        Set associatedCRSet = new HashSet();
        int crListSize = 0;
        crListSize = associatedCRList.size();
        for (int i = 0; i < crListSize; i++)
        {
            correspondenceVO = (AssociatedCorrespondenceVO) associatedCRList
                    .get(i);
            logger.debug("Existing CR object is $$ " + correspondenceVO);

            if (correspondenceVO != null)
            {
             if(correspondenceVO.getLinkToCR()!=null){//UC-PGM-CRM-019_ESPRD00357942_05DEC09
            	logger.debug("Existing CR link value is $$ "
                        + correspondenceVO.getLinkToCR().booleanValue());
             }//UC-PGM-CRM-019_ESPRD00357942_05DEC09
            	caseCorrespondence = convertCaseCRDOToVO(correspondenceVO,
                					caseRecord);
                
                associatedCRSet.add(caseCorrespondence);
            }
        }
        return associatedCRSet;
    }

    /**
     * This method is used to convert the set of caseLink Vo to Dos
     * 
     * @param existingCaseList
     *            holds the existing CaseLink VOs
     * @param caseRecord
     *            holds the CaseRecord reference
     * @return Set contains list of CaseLink records
     */
    public Set convertAssociateCaseVOToDO(List associatedCaseList,
            CaseRecord caseRecord)
    {
        logger.info("convertAssociateCaseVOToDO==========");
    	AssociatedCaseVO associatedCaseVO = null;
        CaseLink caseLink = null;
        Set associatedCaseSet = new HashSet();
        if(associatedCaseList != null && associatedCaseList.size() > 0){
        	for (int i = 0; i < associatedCaseList.size(); i++)
            {
        		associatedCaseVO = (AssociatedCaseVO) associatedCaseList.get(i);
                if (associatedCaseVO != null)
                {
                        caseLink = convertCaseLinkVOToDO(associatedCaseVO, caseRecord);
                        logger.debug("Converted existing Case records is $$ " + i); 
                        associatedCaseSet.add(caseLink);
                }
            }
        }
        return associatedCaseSet;
    }

    /**
     * It converts the CaseCorrespondence VO to DO records
     * 
     * @param asscCorrespondenceVO
     *            holds the Associate Case Correspondence VO
     * @param record
     *            holds the Caserecord reference
     * @return it returns CaseCorrespondence object
     */
    public CaseCorrespondence convertCaseCRDOToVO(
            AssociatedCorrespondenceVO asscCorrespondenceVO, CaseRecord record)
    {
        CaseCorrespondence caseCorrespondence = new CaseCorrespondence();
        caseCorrespondence.setCaseRecord(record);
        logger.debug("While converting existingCRs CaseRecord is " + record);
        logger.debug("CR SK in convertCaseCRDOToVO is $$ "
                + asscCorrespondenceVO.getCorrespondenceRecordNumber());
        Correspondence correspondence = new Correspondence();
        correspondence.setCorrespondenceSK(Long.valueOf(asscCorrespondenceVO
                .getCorrespondenceRecordNumber()));
        caseCorrespondence.setCorrespondence(correspondence);
        caseCorrespondence.setCorrespondenceSK(Long.valueOf(asscCorrespondenceVO
                .getCorrespondenceRecordNumber()));
        caseCorrespondence.setVoidIndicator(Boolean.FALSE);
        logger.debug("asscCorrespondenceVO.getVersionNo()"+asscCorrespondenceVO.getVersionNo());
        //caseCorrespondence.setVersionNo(asscCorrespondenceVO.getVersionNo());
        logger.debug("asscCorrespondenceVO.getCaseCRVersionNumber()"+asscCorrespondenceVO.getCaseCRVersionNumber());
       /* Changes made to Fix the Defect id ESPRD00380802*/
        if(asscCorrespondenceVO.getCaseCRVersionNumber() != null){
        caseCorrespondence.setVersionNo(asscCorrespondenceVO.getCaseCRVersionNumber().intValue());
        logger.debug("asscCorrespondenceVO.getCaseCRVersionNumber().intValue()"+asscCorrespondenceVO.getCaseCRVersionNumber().intValue());
        
        }
       logger.debug("Getting version Number:::::::"+caseCorrespondence.getVersionNo());
 
        if (asscCorrespondenceVO.getAddedAuditTimeStamp() == null)
        {
            caseCorrespondence.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            caseCorrespondence.setAddedAuditTimeStamp(asscCorrespondenceVO
                    .getAddedAuditTimeStamp());
        }
        if (asscCorrespondenceVO.getAddedAuditUserID() == null)
        {
            caseCorrespondence.setAddedAuditUserID(userID);
        }
        else
        {
            caseCorrespondence.setAddedAuditUserID(asscCorrespondenceVO
                    .getAddedAuditUserID());
        }
        caseCorrespondence.setAuditTimeStamp(new Date());
        caseCorrespondence.setAuditUserID(userID);
        return caseCorrespondence;
    }

    /**
     * It converts the CaseLink VO to DO records
     * 
     * @param associatedCaseVO
     *            holds the Associate Case VO
     * @param caseRecord
     *            holds the Caserecord reference
     * @return it returns CaseLink object
     */
    public CaseLink convertCaseLinkVOToDO(AssociatedCaseVO associatedCaseVO,
            CaseRecord caseRecord)
    {
        logger.info("convertCaseLinkVOToDO=========");
    	CaseLink caseLink = new CaseLink();
        caseLink.setCurrentCaseRecord(caseRecord);
        CaseRecord priorCaseRecord = new CaseRecord();
        priorCaseRecord.setCaseSK(Long.valueOf(associatedCaseVO.getCaseID()));
        caseLink.setPriorCaseRecord(priorCaseRecord);
        if(associatedCaseVO.getCaseID() != null)
        {
        	caseLink.setPriorCaseRecordSK(Long
                .valueOf(associatedCaseVO.getCaseID()));
        }
        caseLink.setCase1ArchIndicator(Boolean.FALSE);
        caseLink.setCase2ArchIndicator(Boolean.FALSE);
        caseLink.setVersionNo(associatedCaseVO.getVersionNo());
        if (associatedCaseVO.getAddedAuditTimeStamp() == null)
        {
            caseLink.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            caseLink.setAddedAuditTimeStamp(associatedCaseVO
                    .getAddedAuditTimeStamp());
        }
        if (associatedCaseVO.getAddedAuditUserID() == null)
        {
            caseLink.setAddedAuditUserID(userID);
        }
        else
        {
            caseLink
                    .setAddedAuditUserID(associatedCaseVO.getAddedAuditUserID());
        }
        caseLink.setAuditTimeStamp(new Date());
        caseLink.setAuditUserID(userID);
        return caseLink;
    }

    /**
     * It converts the Case common entity Xref DO
     * 
     * @param caseRecord
     *            holds the caseRecord reference
     * @return it returns the set of CaseCommonEntityXref objects
     */
    public Set convertCaseCMNEnityXRef(CaseRecord caseRecord)
    {
        CaseCommonEntityCrossRef entityCrossRef = null;
        logger.debug("Entity Type in convertCaseCMNEnityXRef $$ "
                + logCaseDataBean.getEntityType());
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        Set caseCmnEntityXrefSet = new HashSet();
        String entityType = logCaseDataBean.getEntityType();
        if (!"CaseUpdate".equals(logCaseDataBean.getActionCode()))
        {
            if (ContactManagementConstants.ENTITY_TYPE_MEM.equals(entityType))
            {
                entityCrossRef = convertCMNEntityForCase(caseRecord,
                        logCaseDataBean.getCaseRegardingVO()
                                .getCaseRegardingMemberVO(), "P");
            }
            else if (ContactManagementConstants.ENTITY_TYPE_PROV
                    .equals(entityType))
            {
                entityCrossRef = convertCMNEntityForCase(caseRecord,
                        logCaseDataBean.getCaseRegardingVO()
                                .getCaseRegardingProviderVO(), "P");
            }
            else if ("TD"
                    .equals(entityType))
            {
                entityCrossRef = convertCMNEntityForCase(caseRecord,
                        logCaseDataBean.getCaseRegardingVO()
                                .getCaseRegardingTradingPartnerVO(), "P");
            }
            else
            {
                entityCrossRef = convertCMNEntityForCase(caseRecord,
                        logCaseDataBean.getCaseRegardingVO()
                                .getCaseRegardingTPLVO(), "P");
            }
            caseCmnEntityXrefSet.add(entityCrossRef);
            logger.debug("Converting primary Entity details are over");
        }
        // 	Modified FOR DEFECT ESPRD00440707 starts
        //  List inqAbtEntityDataList = logCaseDataBean.getCaseDetailsVO()
        //                .getInqAbtEntityList();
        // ESPRD00440707 ends
        List inqAbtEntityDataList = logCaseDataBean.getInqAbtEntityListBeforeSave();
        if (inqAbtEntityDataList != null && !inqAbtEntityDataList.isEmpty())
        {
        	 logger.debug("Inq Abt Entity data list Size is $$ "
                    + inqAbtEntityDataList.size());
            for (Iterator iter = inqAbtEntityDataList.iterator(); iter
                    .hasNext();)
            {
                CaseRegardingTPL element = (CaseRegardingTPL) iter.next();
                CaseCommonEntityCrossRef crossRef = convertCMNEntityForCase(
                        caseRecord, element, "I");
                caseCmnEntityXrefSet.add(crossRef);
            }
        }
        return caseCmnEntityXrefSet;
    }

    /**
     * @param caseRecord
     * @param regardingVO
     * @param type
     * @return
     */
    public CaseCommonEntityCrossRef convertCMNEntityForCase(
            CaseRecord caseRecord, CaseRegardingTPL regardingVO, String type)
    {
        CaseCommonEntityCrossRef crossRef = new CaseCommonEntityCrossRef();
        crossRef.setCaseRecord(caseRecord);
        CommonEntity commonEntity = new CommonEntity();
        String commonEntitySK = null;
        crossRef.setCommonEntityTypeCode(regardingVO.getEntityType());
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        logger.debug("Common Entity Sk(M) in convertCaseCMNEnityXRef $$ "
                + regardingVO.getCommonEntitySK());
        commonEntitySK = regardingVO.getCommonEntitySK();
        
        if (commonEntitySK == null){
        	if(logCaseDataBean.getCommonEntitySKForMyTask()  != null) {
        		commonEntitySK = (logCaseDataBean.getCommonEntitySKForMyTask()).toString();
        	}
        }
        crossRef.setOverseeingPhysicianIndicator(Boolean.FALSE);
        System.err.println("-- commonEntitySK ::> "+ commonEntitySK);
        if(commonEntitySK != null && commonEntitySK.trim().length()> 0) {        
        	commonEntity.setCommonEntitySK(Long.valueOf(commonEntitySK));
        	crossRef.setCommonEntitySK(Long.valueOf(commonEntitySK));
        	//ESPRD00532683 start
        	if (logCaseDataBean.isPhysicianOverseeingFlag()) {
				if (logCaseDataBean.getCaseDetailsVO() != null
						&& logCaseDataBean.getCaseDetailsVO()
								.getCaseTypeBCCPVO() != null) {
					String physicianOverseeingCare = logCaseDataBean
							.getCaseDetailsVO().getCaseTypeBCCPVO()
							.getPhysicianOverseeingCare();
					System.err.println("++physicianOverseeingCare=="
							+ physicianOverseeingCare);
					if (!isNullOrEmptyField(physicianOverseeingCare)) {
						 if(physicianOverseeingCare.equals(commonEntitySK)){
						 	System.err.println("commonEntitySK record is set with OverseeingPhysicianIndicator:: TRUE");
						 	crossRef.setOverseeingPhysicianIndicator(Boolean.TRUE);
						 }
					}

				}
			}
        	//ESPRD00532683 ends
        }
        crossRef.setCaseCRContactReasonCode(type);
     
        
        crossRef.setVersionNo(logCaseDataBean.getCaseCmnEntyXrefVersionNo());
        
        logger.info("crossRef.getVersionNo." + crossRef.getVersionNo());
      
        if(commonEntitySK != null && commonEntitySK.trim().length()> 0) {
        	crossRef.setCommonEntity(commonEntity);
        }
        if (caseRecord.getAddedAuditTimeStamp() == null)
        {
            crossRef.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            crossRef
                    .setAddedAuditTimeStamp(caseRecord.getAddedAuditTimeStamp());
        }
        if (caseRecord.getAddedAuditUserID() == null)
        {
            crossRef.setAddedAuditUserID(userID);
        }
        else
        {
            crossRef.setAddedAuditUserID(caseRecord.getAddedAuditUserID());
        }
        crossRef.setAuditTimeStamp(new Date());
        crossRef.setAuditUserID(userID);
        return crossRef;
    }

    /**
     * This method is used to get the workUnit object.
     * 
     * @param workUnitSK
     *            holds the workUnit SK.
     * @return it returns the WorkUnit object with SK
     */
    public WorkUnit getWorkUnitForUser(String workUnitSK)
    {
        logger.debug("Work Unit UserID " + workUnitSK);
        WorkUnit workUnit = new WorkUnit();
        workUnit.setWorkUnitSK(new Long(workUnitSK));
        logger.debug("Work Unit UserID (Long)" + workUnit.getWorkUnitSK());
        return workUnit;
    }

    /**
     * This method is used to get the workUnit object for CreatedBy.
     * 
     * @param userID
     *            holds the user ID.
     * @return it returns the CaseRecord object with User
     */
   /* private CaseRecord getWorkUnitForCreatedByUser(String userID)
    {
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        WorkUnit workUnit = new WorkUnit();
        CaseRecord caseRecord = new CaseRecord();
        enterpriseUser.setUserID(userID);
        workUnit.setEnterpriseUser(enterpriseUser);
        caseRecord.setCaseCreatedByWorkUnit(workUnit);
        return caseRecord;
    }*/

    /**
     * This method is used to get the workUnit object for AssignedTo.
     * 
     * @param userID
     *            holds the user ID.
     * @return it returns the CaseRecord object with User
     */
    /*private CaseRecord getWorkUnitForAssignedToUser(String userID)
    {
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        WorkUnit workUnit = new WorkUnit();
        CaseRecord caseRecord = new CaseRecord();
        enterpriseUser.setUserID(userID);
        workUnit.setEnterpriseUser(enterpriseUser);
        caseRecord.setCaseCreatedByWorkUnit(workUnit);
        return caseRecord;
    }*/

    /**
     * This method is used to get the LetterTemplate object for termplateID .
     * 
     * @param templateID
     *            holds the user ID.
     * @return it returns the LetterTemplate object with ID
     */
    private LetterTemplate getLetterTemplateForLetterData(String templateID)
    {
        LetterTemplate letterTemplate = new LetterTemplate();
        letterTemplate.setLetterTemplateKeyData(templateID);

        return letterTemplate;
    }

    /**
     * This private method is used to build the EnterpriseUser object with given
     * userID
     * 
     * @param logInUserID
     *            holds the logInUserID.
     * @return it returns the EnterpriseUser with userID
     */
    private EnterpriseUser getEnterpriseUserForUser(String logInUserID)
    {
        logger.debug("The login UserID is " + logInUserID);
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        enterpriseUser.setUserID(logInUserID);
        return enterpriseUser;
    }
    /**
     * This method is used to get the LineOfBusinessHierarchy object for AssignedTo.
     * 
     * @param lobHirchySK
     *            holds the lobHirchySK.
     * @return it returns the LineOfBusinessHierarchy object 
     */
    private LineOfBusinessHierarchy getLobHirchyForUser(String lobHirchySK)
    {
    	logger.debug(" ----------- Work Unit UserID -----" + lobHirchySK);
        LineOfBusinessHierarchy lobHirchy = new LineOfBusinessHierarchy();
     
        lobHirchy.setLineOfBusinessHierarchySK(new Long(lobHirchySK));
       
        return lobHirchy;
    }
    
    private CaseType getCaseTypeFrmDosList(String caseSk,String shortDesc){
		CaseType caseType=null; 
		List caseTypeDosList=logCaseDataBean.getCaseTypeDOList();
		if (caseTypeDosList != null && caseTypeDosList.size() > 0) {
			for (int i = 0; i < caseTypeDosList.size(); i++) {
				caseType = (CaseType) caseTypeDosList.get(i);
				if (caseSk!=null && caseType
						.getCaseTypeSK().toString().equals(caseSk)) {
					caseType = caseType;
					break;
				}else if(shortDesc!=null && caseType
						.getShortDescription().equals(shortDesc)){
					caseType = caseType;
					break;
				}
			}
		}
		return caseType;
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
	
	/**
	 * Modified for additional issue of Audit delete of User ESPRD00864837
     * This method is used to convert the CaseStepsVO to DO
     * for Delete which should to set the audit timestamp of 
     * existing audit timestamp instead of new date.
     * 
     * @param caseStepsVO
     *            holds the CaseStepsVO object
     * @param caseRecord
     *            holds the CaseRecord object
     * @return it returns the ActCMCaseStep object
     */
    public ActCMCaseStep convertCaseStepsVOToDOForDel(CaseStepsVO caseStepsVO,
            CaseRecord caseRecord)
    {
        ActCMCaseStep actCMCaseStep = new ActCMCaseStep();
        actCMCaseStep.setCaseRecord(caseRecord);
       
        
        if(caseStepsVO.getCaseSK()!= null)
        {
        	actCMCaseStep.setCaseSK(Long.valueOf(caseStepsVO.getCaseSK()));
        	logger.info("actCMCaseStep.getCaseSK in convertCaseStepsVOToDO "+actCMCaseStep.getCaseSK());
        }
        if (caseStepsVO.getCaseStepSeqNum() != null)
        {
            actCMCaseStep.setCaseStepSeqNum(caseStepsVO.getCaseStepSeqNum());
            logger.info("actCMCaseStep.getCaseStepSeqNum in convertCaseStepsVOToDO "+actCMCaseStep.getCaseStepSeqNum());

        }
        
        actCMCaseStep.setVersionNo(caseStepsVO.getVersionNo());
        if (caseStepsVO.getAddedAuditTimeStamp() == null)
        {
            actCMCaseStep.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            actCMCaseStep.setAddedAuditTimeStamp(caseStepsVO
                    .getAddedAuditTimeStamp());
        }
        if (caseStepsVO.getAddedAuditUserID() == null)
        {
            actCMCaseStep.setAddedAuditUserID(userID);
        }
        else
        {
            actCMCaseStep
                    .setAddedAuditUserID(caseStepsVO.getAddedAuditUserID());
        }
        if (caseStepsVO.getAuditTimeStamp() != null)
        {
            actCMCaseStep.setAuditTimeStamp(caseStepsVO.getAuditTimeStamp());
        }
        /*else
        {
            actCMCaseStep
                    .setAddedAuditUserID(caseStepsVO.getAddedAuditUserID());
        }*/
        actCMCaseStep.setAuditUserID(userID);
        
        return actCMCaseStep;
    }
    
    
    /**Modified for additional issue of Audit delete of User ESPRD00864837
     * This method is used to convert the CaseEventsVO to DO
     * for Delete which should to set the audit timestamp of 
     * existing audit timestamp instead of new date.
     * 
     * @param caseEventsVO
     *            holds the CaseEventsVO object
     * @param caseRecord
     *            holds the CaseRecord object
     * @return it returns the ActCMCaseEvent object
     */
    public ActCMCaseEvent convertCaseEventsVOToDOForDel(CaseEventsVO caseEventsVO,
            CaseRecord caseRecord)
    {
        ActCMCaseEvent actCMCaseEvent = new ActCMCaseEvent();
        actCMCaseEvent.setCaseRecord(caseRecord);
        logger.debug("Case Type Sk in VO to DO is $$ "
                + caseEventsVO.getCaseTypeSK());          
   
        System.err.println("caseEventsVO.getCaseSK(): "+caseEventsVO.getCaseSK());
        if(caseEventsVO.getCaseSK()!= null)
        {
        	actCMCaseEvent.setCaseSK(Long.valueOf(caseEventsVO.getCaseSK()));
        	logger.info("actCMCaseEvent.getCaseSK"+actCMCaseEvent.getCaseSK());
        }
        if(caseEventsVO.getCaseEventSeqNum()!=null)
        {
        	actCMCaseEvent.setCaseEventSeqNum(caseEventsVO.getCaseEventSeqNum());
        	  logger.info("actCMCaseEvent.getCaseEventSeqNum"+actCMCaseEvent.getCaseEventSeqNum());
        	
        }        
    
  
        if (caseRecord.getCaseType().getCaseTypeSK() != null)
        {
        	actCMCaseEvent.setCaseTypeSK(caseRecord.getCaseType().getCaseTypeSK());
        
        }
       
        logger
        .debug("Event Type Code.in VO..."
                + caseEventsVO.getEventTypeCd());
        if (!isNullOrEmptyField(caseEventsVO.getEventTypeCd()))
        {
            actCMCaseEvent.setCmCaseEventCode(caseEventsVO.getEventTypeCd());

        }
        logger.debug("Event Type Code.in DO..."
                + actCMCaseEvent.getCmCaseEventCode());
       
        actCMCaseEvent.setVersionNo(caseEventsVO.getVersionNo());
        
        if (caseEventsVO.getAddedAuditTimeStamp() == null)
        {
            actCMCaseEvent.setAddedAuditTimeStamp(new Date());
        }
        else
        {
            actCMCaseEvent.setAddedAuditTimeStamp(caseEventsVO
                    .getAddedAuditTimeStamp());
        }
        if (caseEventsVO.getAddedAuditUserID() == null)
        {
            actCMCaseEvent.setAddedAuditUserID(userID);
        }
        else
        {
            actCMCaseEvent.setAddedAuditUserID(caseEventsVO
                    .getAddedAuditUserID());
        }
        if (caseEventsVO.getAuditTimeStamp() != null)
        {
        	actCMCaseEvent.setAuditTimeStamp(caseEventsVO.getAuditTimeStamp());
        }
        //actCMCaseEvent.setAuditTimeStamp(new Date());
        actCMCaseEvent.setAuditUserID(userID);
        return actCMCaseEvent;
    }
}
