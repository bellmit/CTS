/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;

import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.ActCMCaseStep;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeEvent;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeStep;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeTemplate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldAssignment;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;

import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeEventVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeLetterTemplateVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeStepVO;

import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldAssignmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class will be the DO to VO Converter for Maintain Case Type
 * 
 * @author Wipro
 */
public class CaseTypeDOConverter
        extends DataTransferObjectConverter
{

    
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(CaseTypeDOConverter.class);
	
       

    /**
     * This operation is used for converting caseType Do to VO
     * 
     * @param caseType :
     *            CaseTypeDO Domain object
     * @return caseTypeVO :caseType value Object
     */
    public CaseTypeVO convertCaseTypeDOToVO(CaseType caseType)
    {
    	if(logger.isInfoEnabled()){
    	logger.info("Inside CasetypeDOTOVO conversion");
    	}
        CaseTypeVO caseTypeVO = new CaseTypeVO();
        //commented for code cleanup during Heap dump fix
        //CaseTypeEventVO  caseTypeEventVO = new CaseTypeEventVO();
        //CaseTypeStepVO caseTypeStepVO  = new CaseTypeStepVO();
        //CaseTypeEvent caseTypeEventDo = new CaseTypeEvent();
        //CaseTypeStep caseTypeStepDo= new CaseTypeStep();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        
        if ((caseType.getCaseTypeSK()) != null)
        {
            caseTypeVO.setCaseTypeSK(caseType.getCaseTypeSK());

        }
      
        /*if ((caseTypeStepDo.getStepSeqNum()) != null)
        {
        	caseTypeStepVO.setStepSeqNum(caseTypeStepDo.getStepSeqNum());
            logger.info("caseTypeStepVO.getStepSeqNum"+caseTypeStepVO.getStepSeqNum());
        }*/
        
        
     
        /*if ((caseTypeEventDo.getCaseEventSeqNum()) != null)
        {
        	caseTypeEventVO.setCaseEventSeqNum(caseTypeEventDo.getCaseEventSeqNum());
        	logger.info("caseTypeEventVO.getCaseEventSeqNum"+caseTypeEventVO.getCaseEventSeqNum());
        }*/
       
        if (caseType.getStatusTypeCode() != null)
        {
        	if(caseType.getStatusTypeCode().equalsIgnoreCase("Y"))
        	{
        	    caseTypeVO.setCaseTypeStatusCode("Yes"); 
        	}
        	else
        	{
        	    caseTypeVO.setCaseTypeStatusCode("No");  
        	}
        	
        }
       
        caseTypeVO.setValuesProtectedIndicator(String.valueOf(caseType
                .getValuesProtectedIndicator()));

        if (caseType.getBusinessUnitCaseTypeCode() != null)
        {
            caseTypeVO.setBusnUnitCaseTypeCode(caseType
                    .getBusinessUnitCaseTypeCode());
        }

        if (caseType.getDescription() != null)
        {
            caseTypeVO.setLongDesc(caseType.getDescription());
        }
        if (caseType.getShortDescription() != null)
        {
            caseTypeVO.setShortDesc(caseType.getShortDescription());
        }

        if (String.valueOf(caseType
                .getSupervisorReviewReqIndicator()) != null)
        {
           String s1 = String.valueOf(caseType.getSupervisorReviewReqIndicator());
           if(logger.isDebugEnabled()){
           logger.debug("S1:" + s1);
           }
           if(s1.equalsIgnoreCase("true"))
           {
           	caseTypeVO.setSprvsrRevwReqdInd("Yes");
           }
           else if(s1.equalsIgnoreCase("false"))
           {
           	caseTypeVO.setSprvsrRevwReqdInd("No");
           }
         
        }
        
        caseTypeVO.setVersionNo(caseType.getVersionNo());
        caseTypeVO.setAuditUserID(caseType.getAuditUserID());
        caseTypeVO.setAddedAuditUserID(caseType.getAddedAuditUserID());
        caseTypeVO.setAuditTimeStamp(caseType.getAuditTimeStamp());
        caseTypeVO.setAddedAuditTimeStamp(caseType.getAddedAuditTimeStamp());
        contactManagementHelper.createVOAuditKeysList(caseType,caseTypeVO);
        return caseTypeVO;

    }

    /**
     * This operation is used for getting casestep VOs from casetypeVO
     * 
     * @param stepdos :
     *            Set of caseStep Value Objects
     * @return stepvos :Set set of case step Vos
     */
    public Set getCaseStepsVO(Set stepdos)
    {
        Set stepvos = new HashSet();
        if(logger.isDebugEnabled()){
        logger.debug("getCasestepsVo in Converter");
        }
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        for (Iterator iter = stepdos.iterator(); iter.hasNext();)
        {
            CaseTypeStep stepdo = (CaseTypeStep) iter.next();

            CaseTypeStepVO stepvo = convertCaseTypeStepDOToVO(stepdo);

//          CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
            if (stepdo != null) {
				contactManagementHelper.createVOAuditKeysList(stepdo, stepvo);

				if (stepdo.getDefaultRouteToWorkUnit() != null) {
					contactManagementHelper.createVOAuditKeysList(stepdo
							.getDefaultRouteToWorkUnit(), stepvo);
				}
				if (stepdo.getEnterpriseUser() != null) {
					contactManagementHelper.createVOAuditKeysList(stepdo
							.getEnterpriseUser(), stepvo);
				}
				if (stepdo.getLetterTemplate() != null) {
					contactManagementHelper.createVOAuditKeysList(stepdo
							.getLetterTemplate(), stepvo);
				}
				
				stepvo = (CaseTypeStepVO)contactManagementHelper.createVOAuditKeysList(stepdo, stepvo);
				
			}
            
            //EOF CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
            stepvos.add(stepvo);

        }
        return stepvos;
    }

    /**
     * This operation is used for getting case event VOs from casetypeVO
     * 
     * @param eventdos :
     *            Set
     * @return eventvos :Set set of case event vos
     */
    public Set getCaseEventsVO(Set eventdos)
    {
       Set eventvos = new HashSet();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        
        for (Iterator iter = eventdos.iterator(); iter.hasNext();)
        {
            CaseTypeEvent eventdo = (CaseTypeEvent) iter.next();
            CaseTypeEventVO eventvo = convertCaseTypeEventDOToVO(eventdo);
  
            //CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
            try{
			if (eventdo != null) {
				contactManagementHelper.createVOAuditKeysList(eventdo, eventvo);
				if (eventdo.getEnterpriseUser() != null) {
					contactManagementHelper.createVOAuditKeysList(eventdo
							.getEnterpriseUser(), eventvo);
				}

				if (eventdo.getLetterTemplate() != null) {
					contactManagementHelper.createVOAuditKeysList(eventdo
							.getLetterTemplate(), eventvo);
				}
				
				eventvo = (CaseTypeEventVO)contactManagementHelper.createVOAuditKeysList(eventdo, eventvo);
			}
//			doAuditKeyListOperationForCaseTypeEventVO(eventvo);
            }catch(Exception e){
            	if(logger.isErrorEnabled()){
            	logger.error("Exception while preparing Audit keys: getCaseEventsVO(Set): ",e);
            	}
            }
			//EOF CR_ESPRD00373565_MaintainCaseTypes_06AUG2010

            eventvos.add(eventvo);
        }

        return eventvos;
    }
    /**
     * This operation is used for getting case event VOs from casetypeVO
     * 
     * @param eventdos :
     *            Set
     * @return eventvos :Set set of case event vos
     */
    public List getCaseTypeTemplates(Set templateDOs)
    {
    	if(logger.isDebugEnabled()){
        logger.debug("Inside getCasetype Tempaltes");
    	}
        List templateVOs = new ArrayList();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        for (Iterator iter = templateDOs.iterator(); iter.hasNext();)
        {
            CaseTypeTemplate caseTypeTemplate = (CaseTypeTemplate) iter.next();
            CaseTypeLetterTemplateVO caseTypeLetterTemplateVO = convertLetterTemplateDOtoVO(caseTypeTemplate);
            
            if (caseTypeTemplate != null) {
				contactManagementHelper.createVOAuditKeysList(caseTypeTemplate, caseTypeLetterTemplateVO);

				/*if (caseTypeTemplate.getDefaultRouteToWorkUnit() != null) {
					contactManagementHelper.createVOAuditKeysList(stepdo
							.getDefaultRouteToWorkUnit(), stepvo);
				}
				if (caseTypeTemplate.getEnterpriseUser() != null) {
					contactManagementHelper.createVOAuditKeysList(stepdo
							.getEnterpriseUser(), stepvo);
				}*/
				if (caseTypeTemplate.getLetterTemplate() != null) {
					contactManagementHelper.createVOAuditKeysList(caseTypeTemplate
							.getLetterTemplate(), caseTypeLetterTemplateVO);
				}
			}
            
            templateVOs.add(caseTypeLetterTemplateVO);
        }

        return templateVOs;
    }
    
    /**
     * This operation is used for getting Custom Field VOs from customFieldDOs
     * 
     * @param eventdos :
     *            Set
     * @return eventvos :Set set of case event vos
     */
    public List getCustomFieldVOs(Set customFieldDOs)
    {
    	if(logger.isDebugEnabled()){
        logger.debug("Inside getCustomFieldVOs");
    	}
        List customFieldVOs = new ArrayList();
        for (Iterator iter = customFieldDOs.iterator(); iter.hasNext();)
        {
            CustomField customField = (CustomField) iter.next();
            CustomFieldVO customFieldVO = convertCustomFieldDOToVO(customField);
            customFieldVOs.add(customFieldVO);
        }

        return customFieldVOs;
    }
    
    public List getCustomFieldAssignmentVO(Set customFieldAssignmentDOS)
    {
      
        List customAssignmentList = new ArrayList();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        for (Iterator iter = customFieldAssignmentDOS.iterator(); iter.hasNext();)
        {
            CustomFieldAssignment customFieldAssignment = (CustomFieldAssignment) iter.next();
			//for fixing ESPRD00703524
			if(customFieldAssignment != null && customFieldAssignment.getTableName().equalsIgnoreCase("G_CASE_TB")){
				CustomFieldAssignmentVO customAssignmentVO = convertAssignmentDOTOVO(customFieldAssignment);
				if (customFieldAssignment != null) {
					customAssignmentVO = (CustomFieldAssignmentVO)contactManagementHelper.createVOAuditKeysList(customFieldAssignment, customAssignmentVO);

					/*if (customFieldAssignment.getDefaultRouteToWorkUnit() != null) {
						contactManagementHelper.createVOAuditKeysList(stepdo
								.getDefaultRouteToWorkUnit(), stepvo);
					}
					if (customFieldAssignment.getEnterpriseUser() != null) {
						contactManagementHelper.createVOAuditKeysList(stepdo
								.getEnterpriseUser(), stepvo);
					}
					if (customFieldAssignment.getLetterTemplate() != null) {
						contactManagementHelper.createVOAuditKeysList(customField
								.getLetterTemplate(), caseTypeLetterTemplateVO);
					}*/
				}
				
				customAssignmentList.add(customAssignmentVO); 
			}
        }
        return customAssignmentList;
    }

    /**
     * 
     * @param caseTypeStepDO
     * @return
     */
    public CaseTypeLetterTemplateVO convertLetterTemplateDOtoVO(CaseTypeTemplate caseTypeTemplate)
    {
    	if(logger.isDebugEnabled()){
        logger.debug("Inside convertLetterTemplateDOtoVO");
    	}
        CaseTypeLetterTemplateVO caseTypeLetterTemplateVO = new CaseTypeLetterTemplateVO();
        caseTypeLetterTemplateVO.setLetterTemplateKeyData(caseTypeTemplate.getLetterTemplateKeyData());
        if(caseTypeTemplate.getCaseTypeSK() != null)
        {   
       
        caseTypeLetterTemplateVO.setCaseTypeSK(caseTypeTemplate.getCaseTypeSK());
        }
        caseTypeLetterTemplateVO.setVersionNo(caseTypeTemplate.getVersionNo());
        if(caseTypeTemplate.getLetterTemplate() != null)
        {    
            caseTypeLetterTemplateVO.setLetterTemplate(caseTypeTemplate.getLetterTemplate());
       
        }
        caseTypeLetterTemplateVO.setAddedAuditUserID(caseTypeTemplate.getAddedAuditUserID());
        caseTypeLetterTemplateVO.setAuditUserID( caseTypeTemplate.getAuditUserID());
        return caseTypeLetterTemplateVO;
    }
    /**
     * This method is used to convert Case Type Step Value Object to Case Type
     * Step Domain object.
     * 
     * @param caseTypeStepDO :
     *            CaseTypeStep domain object
     * @return caseTypeStepVO : CaseTypeStepVO
     */
    public CaseTypeStepVO convertCaseTypeStepDOToVO(CaseTypeStep caseTypeStepDO)
    {

        CaseTypeStepVO caseTypeStepVO = new CaseTypeStepVO();
        caseTypeStepVO.setCaseTypeStepInclude("Yes");
        caseTypeStepVO.setVersionNo(caseTypeStepDO.getVersionNo());

       if(caseTypeStepDO.getLetterTemplate() != null)
       {
           caseTypeStepVO.setDfltCotsLtrTmpltKeyData(caseTypeStepDO.getLetterTemplate()
                   .getLetterTemplateKeyData());
       }
    
       if(caseTypeStepDO.getStepSeqNum() != null)
       {
           caseTypeStepVO.setStepSeqNum(caseTypeStepDO.getStepSeqNum());
       }
      
        if (caseTypeStepDO.getDefaultRouteToWorkUnit() != null)
        {
            caseTypeStepVO.setAutomaticRouteTo(caseTypeStepDO.getDefaultRouteToWorkUnit()
                    .getWorkUnitSK().toString());
        }

        if (caseTypeStepDO.getStepOrderNumber() != null)
        {
            caseTypeStepVO.setStepOrderNum(caseTypeStepDO.getStepOrderNumber()
                    .toString());
        }

        if (caseTypeStepDO.getDefaultDaysToCompleteCount() != null)
        {
            caseTypeStepVO.setDfltDaysToCmplCnt(caseTypeStepDO
                    .getDefaultDaysToCompleteCount().toString());
        }

        setcaseStepDfltSendAlertDaysBeforeOrAfterColData(caseTypeStepDO,
                caseTypeStepVO);
       if (caseTypeStepDO.getDefaultAlertBasedOnColName() != null)
       {
       	caseTypeStepVO.setDfltAlertBasedOnColName(caseTypeStepDO
       			.getDefaultAlertBasedOnColName());
       }
       if (caseTypeStepDO.getEnterpriseUser() != null)
       {
       	caseTypeStepVO.setDfltNotfyAlertUserId(caseTypeStepDO
                   .getEnterpriseUser().getUserID());
    	//Added for Gap 15668
       	caseTypeStepVO.setDfltNotfyAlertUserName(getUserName(
       			caseTypeStepDO.getEnterpriseUser()));
       	//ends
       }
       if(caseTypeStepDO.getCmCaseStepCode() != null)
       {
           caseTypeStepVO.setDescription(caseTypeStepDO
                   .getCmCaseStepCode());
       }
       if(caseTypeStepDO.getDefaultCompltBasedOnColName() != null)
       {
           caseTypeStepVO.setDfltCmpltnBasedOnColName(caseTypeStepDO
                   .getDefaultCompltBasedOnColName());
       }
       
        caseTypeStepVO.setAddedAuditUserID(caseTypeStepDO.getAddedAuditUserID());
        caseTypeStepVO.setAuditUserID(caseTypeStepDO.getAuditUserID());
        caseTypeStepVO.setAddedAuditTimeStamp(caseTypeStepDO
                .getAddedAuditTimeStamp());
        caseTypeStepVO.setAuditTimeStamp(caseTypeStepDO
                .getAuditTimeStamp());
       //need to add defaultnotifyviaalert
        return caseTypeStepVO;

    }
//   Added for Gap 15668 Starts
    public String getUserName(EnterpriseUser usr) {
//    	String userName = ""; ESPRD00527856_UC-PGM-CRM-48_01oct2010
    	StringBuffer userName =new StringBuffer(ContactManagementConstants.EMPTYSTRING);
    	if(usr.getUserID() != null)
        {
	    	/* ESPRD00527856_UC-PGM-CRM-48_01oct2010
	    	 if (usr.getFirstName() != null && !usr.getFirstName().equalsIgnoreCase(""))
	        {
	            userName = usr.getFirstName();
	        }
	        if (usr.getMiddleName() != null && !usr.getMiddleName().equalsIgnoreCase(""))
	        {
	            if(!userName.equalsIgnoreCase(""))
	                userName = userName+" "+usr.getMiddleName();
	            else
	                userName = usr.getMiddleName();
	        }
	        if (usr.getLastName() != null && !usr.getLastName().equalsIgnoreCase(""))
	        {
	            if(!userName.equalsIgnoreCase(""))
	                userName = userName+" "+usr.getLastName();
	            else
	                userName = usr.getLastName();
	        }*/
    		 if(usr.getLastName() != null && !usr.getLastName().equalsIgnoreCase(ContactManagementConstants.EMPTYSTRING)){
            	userName.append(usr.getLastName()).append(", ");	
            }
            if(usr.getFirstName()!= null && !usr.getFirstName().equalsIgnoreCase(ContactManagementConstants.EMPTYSTRING)){
            	userName.append(usr.getFirstName()).append(" - ");
            }
            userName.append(usr.getUserID());
        }
        return userName.toString();
    }
    //Ends
    /**
     * This Operation is used for setting data for casestep
     * DfltSendAlertDaysBeforeOrAfterColData field data
     * 
     * @param caseTypeStepDO :
     *            CaseTypeStep
     * @param caseTypeStepVO :
     *            CaseTypeStepVOt
     */
    private void setcaseStepDfltSendAlertDaysBeforeOrAfterColData(
            CaseTypeStep caseTypeStepDO, CaseTypeStepVO caseTypeStepVO)
    {
        String dfltSendAlertDaysCode = ContactManagementConstants.EMPTY_STRING;
        String defaultBeforeAfterCode = ContactManagementConstants.EMPTY_STRING;
        String dfltSendAlertDaysBeforeOrAfter = ContactManagementConstants.EMPTY_STRING;

        if (caseTypeStepDO.getDefaultSendAlertDaysCode() != null
                && caseTypeStepDO.getDefaultSendAlertDaysCode() != ContactManagementConstants.EMPTY_STRING)
        {
            dfltSendAlertDaysCode = caseTypeStepDO
                    .getDefaultSendAlertDaysCode();
            caseTypeStepVO.setDfltSendAlertDaysCode(dfltSendAlertDaysCode);
        }
        if (caseTypeStepDO.getDefaultBeforeAfterCode() != null
                && caseTypeStepDO.getDefaultBeforeAfterCode() != ContactManagementConstants.EMPTY_STRING)
        {

            caseTypeStepVO.setDfltBeforeAfterCode(caseTypeStepDO
                    .getDefaultBeforeAfterCode());
            if (caseTypeStepDO.getDefaultBeforeAfterCode().equals(
                    ContactManagementConstants.DFLT_BEFORE_CODE_VALUE))
            {
                defaultBeforeAfterCode = ContactManagementConstants.DFLT_BEFORE_CODE;
            }

            else
            {
                defaultBeforeAfterCode = ContactManagementConstants.DFLT_AFTER_CODE;
            }
        }
        dfltSendAlertDaysBeforeOrAfter = dfltSendAlertDaysCode + ContactManagementConstants.SPACE_STRING 
                + defaultBeforeAfterCode;
        caseTypeStepVO
                .setDfltSendAlertDaysBeforeOrAfter(dfltSendAlertDaysBeforeOrAfter);
        caseTypeStepVO
                .setDfltSendAlertDaysBeforeOrAfter(dfltSendAlertDaysBeforeOrAfter);

    }

    /**
     * This method is used to convert Case Type Step Value Object to Case Type
     * Step Domain object.
     * 
     * @param caseTypeEventDO :
     *            CaseTypeEvent
     * @return caseTypeEventVO : CaseTypeEventVO
     */
    public CaseTypeEventVO convertCaseTypeEventDOToVO(
            CaseTypeEvent caseTypeEventDO)
    {
        CaseTypeEventVO caseTypeEventVO = new CaseTypeEventVO();
        caseTypeEventVO.setVersionNo(caseTypeEventDO.getVersionNo());
        caseTypeEventVO.setCaseTypeEventInclude("Yes");  
       if(caseTypeEventDO.getCmCaseEventCode() !=null)
       {     
           caseTypeEventVO.setDfltCaseTypeEventTypeCode(caseTypeEventDO
                   .getCmCaseEventCode());
       }
        if (caseTypeEventDO.getEnterpriseUser() != null)
        {
            caseTypeEventVO.setDfltNotfyAlertUserId(caseTypeEventDO
                    .getEnterpriseUser().getUserID());
            caseTypeEventVO.setDfltNotfyAlertUserName(getUserName(caseTypeEventDO
                    .getEnterpriseUser()));
        }

        if (caseTypeEventDO.getLetterTemplate() != null)
        {
            caseTypeEventVO.setDfltCotsLtrTmltKeyData(caseTypeEventDO
                    .getLetterTemplate().getLetterTemplateKeyData());
        }
        if(caseTypeEventDO.getDefaultAlertBasedOnColName() !=null)
        {
            caseTypeEventVO.setDfltAlertBasedOnColName(caseTypeEventDO
                    .getDefaultAlertBasedOnColName());
        }
        // Added code for CaseType Major Save
        if(caseTypeEventDO.getCaseEventSeqNum() != null)
        {
        	caseTypeEventVO.setCaseEventSeqNum(caseTypeEventDO.getCaseEventSeqNum());
        }
        // End
        setcaseEventDfltSendAlertDaysBeforeOrAfterColData(caseTypeEventDO,
                caseTypeEventVO);
        caseTypeEventVO.setAddedAuditUserID(caseTypeEventDO.getAddedAuditUserID());
        caseTypeEventVO.setAuditUserID(caseTypeEventDO.getAuditUserID());
        
        return caseTypeEventVO;

    }

    /**
     * This Operation is used for setting data for case event
     * DfltSendAlertDaysBeforeOrAfterColData field data
     * 
     * @param caseTypeEventVO :
     *            CaseTypeEventVO Value Object
     * @param caseTypeEventDO :
     *            CaseTypeEvent Domain Object
     */
    private void setcaseEventDfltSendAlertDaysBeforeOrAfterColData(
            CaseTypeEvent caseTypeEventDO, CaseTypeEventVO caseTypeEventVO)
    {

        String dfltSendAlertDaysCode = ContactManagementConstants.EMPTY_STRING;
        String defaultBeforeAfterCode = ContactManagementConstants.EMPTY_STRING;
        String dfltSendAlertDaysBeforeOrAfter = ContactManagementConstants.EMPTY_STRING;

        if (caseTypeEventDO.getDefaultSendAlertDaysCode() != null
                && caseTypeEventDO.getDefaultSendAlertDaysCode() != ContactManagementConstants.EMPTY_STRING)
        {
            dfltSendAlertDaysCode = caseTypeEventDO
                    .getDefaultSendAlertDaysCode();
            caseTypeEventVO.setDfltSendAlertDaysCode(dfltSendAlertDaysCode);
        }
        if (caseTypeEventDO.getDefaultBeforeAfterCode() != null
                && caseTypeEventDO.getDefaultBeforeAfterCode() != ContactManagementConstants.EMPTY_STRING)
        {

            caseTypeEventVO.setDfltBeforeAfterCode(caseTypeEventDO
                    .getDefaultBeforeAfterCode());
            if (caseTypeEventDO.getDefaultBeforeAfterCode().equals(
                    ContactManagementConstants.DFLT_BEFORE_CODE_VALUE))
            {

                defaultBeforeAfterCode = ContactManagementConstants.DFLT_BEFORE_CODE;
            }
            else
            {
                defaultBeforeAfterCode = ContactManagementConstants.DFLT_AFTER_CODE;
            }
        }
        dfltSendAlertDaysBeforeOrAfter = dfltSendAlertDaysCode + ContactManagementConstants.SPACE_STRING
                + defaultBeforeAfterCode;

        caseTypeEventVO
                .setDfltSendAlertDaysBeforeOrAfter(dfltSendAlertDaysBeforeOrAfter);

    }
    /**
     * This operation is used for cinverting CaseTypeLetterTemplate DO to
     * CaseTypeLetterTemplate VO
     * 
     * @param templateDO
     *            :LetterTemplate
     * @return caseTypeLetterTemplateVO: CaseTypeLetterTemplateVO
     */
    public CaseTypeLetterTemplateVO convertLetterTemplateDOtoVO(
            LetterTemplate templateDO)
    {
    	
        CaseTypeLetterTemplateVO caseTypeLetterTemplateVO = new CaseTypeLetterTemplateVO();
        caseTypeLetterTemplateVO.setTemplate(templateDO.getName());
        caseTypeLetterTemplateVO.setLetterTemplateKeyData(templateDO.getLetterTemplateKeyData());
        
        caseTypeLetterTemplateVO.setTemplateDescription(templateDO
                .getDescription());
         String req = "";
    	 String opt = ""; 
    	 if (templateDO.getDefaultSupervisorRevwReqInd() != null)
         { 
         req = templateDO.getDefaultSupervisorRevwReqInd().toString();
         }
         if (templateDO.getOptionalTextAllowIndicator() != null)
         {
         opt = templateDO.getOptionalTextAllowIndicator().toString();
         }
           
        if (req != null && req.equalsIgnoreCase("Y"))
        {
        	 
        	 caseTypeLetterTemplateVO.setSupervisorApprRqd(true);
        	 
        	 
        }
        else if (req != null && req.equalsIgnoreCase("N"))
        {
        	 caseTypeLetterTemplateVO.setSupervisorApprRqd(false);
        	   
        }
       
      
        if (opt != null && opt.equalsIgnoreCase("Y"))
        {
      	    
        	 caseTypeLetterTemplateVO.setOptionalText(true);
        }
        else if (opt != null && opt.equalsIgnoreCase("N"))
        {	
        caseTypeLetterTemplateVO.setOptionalText(false);
       
        }
       return caseTypeLetterTemplateVO;
    }
    
    

    /**
     * This operation is used for converting CustomField VO to DO
     * 
     * @param cfDO :
     *            CustomField
     * @return cfVO : CustomFieldVO value object
     */
    public CustomFieldVO convertCustomFieldDOToVO(CustomField cfDO)
    {
      
        CustomFieldVO cfVO = new CustomFieldVO();
        if (cfDO.getCustomFieldSK() != null)
        { 
        	cfVO.setCustomFieldSK(cfDO.getCustomFieldSK());
        	
        }
      
        if(cfDO.getDescription()!= null){
        	cfVO.setColumnDescription(cfDO.getDescription());
        }
        if(cfDO.getValueProtectedIndicator()!= null){
        	cfVO.setProtectedInd(cfDO.getValueProtectedIndicator().toString());
        }
        if(cfDO.getVoidIndicator() != null){
        	cfVO.setActiveInd(cfDO.getVoidIndicator().toString());
        }
        if(cfDO.getDataTypeCode()!= null){
        	cfVO.setDataType(cfDO.getDataTypeCode());
        }
         if (cfDO.getTotalLengthInBytes() != null)
        {
            cfVO.setLength(cfDO.getTotalLengthInBytes().toString());
        }
        if (cfDO.getRequiredValueIndicator() != null)
        {	
        	cfVO.setRequiredInd(cfDO.getRequiredValueIndicator().toString());
        }
               
        return cfVO;
      }
    public CustomFieldAssignmentVO convertAssignmentDOTOVO(CustomFieldAssignment cfAssignment)
    {
        CustomFieldAssignmentVO cfAssignmentVO = new CustomFieldAssignmentVO();
        if(cfAssignment.getCustomFieldSK() != null)
        {  
        cfAssignmentVO.setCustomFieldSK(cfAssignment.getCustomFieldSK());
        }
        
        if(cfAssignment.getTableName() != null)
        {   
        cfAssignmentVO.setCustomFieldTableID(cfAssignment.getTableName());
        }
        cfAssignmentVO.setVersionNo(cfAssignment.getVersionNo());
        
        if(cfAssignment.getCaseType() != null)
        {
            cfAssignmentVO.setCaseType(cfAssignment.getCaseType());
        }
        if(cfAssignment.getSubjectAreaSK() != null)
        {
            cfAssignmentVO.setSubjectAreaSK(cfAssignment.getSubjectAreaSK());
        }
        cfAssignmentVO.setAddedAuditUserID(cfAssignment.getAddedAuditUserID());
         cfAssignmentVO.setAuditUserID(cfAssignment.getAuditUserID());
        return cfAssignmentVO;
    }
    
    public List getActiveCaseStepsVO(Set stepdos)
    {
        List stepvos = new ArrayList();
        if(logger.isDebugEnabled()){
        logger.debug("getCasestepsVo in Converter");
        }
        
        for (Iterator iter = stepdos.iterator(); iter.hasNext();)
        {
            ActCMCaseStep stepdo = (ActCMCaseStep) iter.next();

            CaseTypeStepVO stepvo = convertCaseTypeStepDOToVO(stepdo);

            stepvos.add(stepvo);

        }
        return stepvos;
    }
    
    public CaseTypeStepVO convertCaseTypeStepDOToVO(ActCMCaseStep actCMCaseStep)
    {

        CaseTypeStepVO caseTypeStepVO = new CaseTypeStepVO();
        caseTypeStepVO.setCaseTypeStepInclude("Yes");

        

       if(actCMCaseStep.getLetterTemplate() != null)
       {
           caseTypeStepVO.setDfltCotsLtrTmpltKeyData(actCMCaseStep.getLetterTemplate()
                   .getLetterTemplateKeyData());
          
       }
      
       if(actCMCaseStep.getCaseStepSeqNum() != null)
       {
           caseTypeStepVO.setStepSeqNum(actCMCaseStep.getCaseStepSeqNum());
           
       }
      


        if (actCMCaseStep.getStepOrderNumber() != null)
        {
            caseTypeStepVO.setStepOrderNum(actCMCaseStep.getStepOrderNumber()
                    .toString());
        }

        if (actCMCaseStep.getDaysToCompleteNumber() != null)
        {
            caseTypeStepVO.setDfltDaysToCmplCnt(actCMCaseStep
                    .getDaysToCompleteNumber().toString());
        }


       if (actCMCaseStep.getEnterpriseUser() != null)
       {
       	caseTypeStepVO.setDfltNotfyAlertUserId(actCMCaseStep
                   .getEnterpriseUser().getUserID());
    	//Added for Gap 15668
       	caseTypeStepVO.setDfltNotfyAlertUserName(getUserName(
       			actCMCaseStep.getEnterpriseUser()));
       	//ends
       }
       if(actCMCaseStep.getCmCaseStepCode() != null)
       {
           caseTypeStepVO.setDescription(actCMCaseStep
                   .getCmCaseStepCode());
       }

       
        caseTypeStepVO.setAddedAuditUserID(actCMCaseStep.getAddedAuditUserID());
        caseTypeStepVO.setAuditUserID(actCMCaseStep.getAuditUserID());
        caseTypeStepVO.setAddedAuditTimeStamp(actCMCaseStep
                .getAddedAuditTimeStamp());
        caseTypeStepVO.setAuditTimeStamp(actCMCaseStep
                .getAuditTimeStamp());
     
        return caseTypeStepVO;

    }
   
  }
