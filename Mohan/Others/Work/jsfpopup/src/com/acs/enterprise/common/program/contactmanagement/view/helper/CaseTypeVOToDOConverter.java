/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeEvent;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeStep;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeTemplate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldAssignment;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldScope;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldTable;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CaseTypeDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeEventVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeLetterTemplateVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeStepVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldAssignmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class will be the VO to DO Converter for Maintain Case Type
 * 
 * @author Wipro
 */
public class CaseTypeVOToDOConverter {

	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CaseTypeVOToDOConverter.class);
	
	// Moved to ContactManagementConstants.java
	//public static final String CASE_TYPE_DATA_BEAN = "CaseTypeDataBean";

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
	 * Holds loggedin Userid From Databean
	 */
	private CaseTypeDataBean caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);

	private String userID = caseTypeDataBean.getUserID();

	/**
	 * This method is used to convert Case Type Step Value Object to Case Type
	 * Step Domain object.
	 * 
	 * @param caseTypeStepVO :
	 *            CaseTypeStepVO Value Object
	 * @return caseTypeStep : CaseTypeStep Domain Object
	 */
	public CaseTypeStep convertCaseTypeStepVOToDO(CaseTypeStepVO caseTypeStepVO) {
		CaseTypeStep caseTypeStepDO = new CaseTypeStep();

		if (caseTypeStepVO != null) {

			if (caseTypeStepVO.getDescription() != null
					&& !caseTypeStepVO.getDescription().equalsIgnoreCase(
							MaintainContactManagementUIConstants.EMPTY_STRING)) {
				caseTypeStepDO.setCmCaseStepCode(caseTypeStepVO
						.getDescription());
				logger.debug("Description from CaseStepdo:"
						+ caseTypeStepDO.getCmCaseStepCode());
			}
			if (caseTypeStepVO.getStepOrderNum() != null
					&& !caseTypeStepVO.getStepOrderNum().equalsIgnoreCase(
							MaintainContactManagementUIConstants.EMPTY_STRING)) {
				logger.debug("num" + caseTypeStepVO.getStepOrderNum());
				caseTypeStepDO.setStepOrderNumber(Integer
						.valueOf(caseTypeStepVO.getStepOrderNum()));
			}
			if (caseTypeStepVO.getAutomaticRouteTo() != null
					&& !caseTypeStepVO.getAutomaticRouteTo().equalsIgnoreCase(
							MaintainContactManagementUIConstants.EMPTY_STRING)) {

				WorkUnit workUnit = new WorkUnit();
				workUnit.setWorkUnitSK(new Long(caseTypeStepVO
						.getAutomaticRouteTo()));
				caseTypeStepDO.setDefaultRouteToWorkUnit(workUnit);
				logger.debug("automaticRoutoTo"
						+ caseTypeStepVO.getAutomaticRouteTo());
			}
			if (caseTypeStepVO.getDfltDaysToCmplCnt() != null
					&& !caseTypeStepVO.getDfltDaysToCmplCnt().equalsIgnoreCase(
							MaintainContactManagementUIConstants.EMPTY_STRING)) {
				logger.debug("caseTypeStepVO.getDfltDaysToCmplCnt()   "
						+ caseTypeStepVO.getDfltDaysToCmplCnt());
				caseTypeStepDO.setDefaultDaysToCompleteCount(new Integer(
						caseTypeStepVO.getDfltDaysToCmplCnt()));
			}
			if (caseTypeStepVO.getDfltCmpltnBasedOnColName() != null
					&& !caseTypeStepVO
							.getDfltCmpltnBasedOnColName()
							.equalsIgnoreCase(
									MaintainContactManagementUIConstants.EMPTY_STRING)) {
				caseTypeStepDO.setDefaultCompltBasedOnColName(caseTypeStepVO
						.getDfltCmpltnBasedOnColName());
			}
			if (caseTypeStepVO.getDfltNotfyAlertUserId() != null
					&& !caseTypeStepVO
							.getDfltNotfyAlertUserId()
							.equalsIgnoreCase(
									MaintainContactManagementUIConstants.EMPTY_STRING)) {

				CaseTypeDataBean caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
				Long userSk = Long.valueOf(0); /* FIND BUGS_FIX */
				int listSize = caseTypeDataBean.getUserWorkUnitList().size();

				for (int i = 0; i < listSize; i++) {
					//Modify DEFECT_ESPRD00723971 Starts
					Object[] userDetails = (Object[]) caseTypeDataBean
							.getUserWorkUnitList().get(i);
					if (caseTypeStepVO.getDfltNotfyAlertUserId()
							.equalsIgnoreCase(userDetails[2].toString())) {
						userSk = (Long) userDetails[3];
						//Modify DEFECT_ESPRD00723971 Ends
					}
				}

				EnterpriseUser enterpriseUser = new EnterpriseUser();
				WorkUnit workUnit = new WorkUnit();
				workUnit.setWorkUnitSK(userSk);
				enterpriseUser.setWorkUnit(workUnit);
				enterpriseUser.setUserID(caseTypeStepVO
						.getDfltNotfyAlertUserId());
				caseTypeStepDO.setEnterpriseUser(enterpriseUser);
			}
			if (caseTypeStepVO.getDfltAlertBasedOnColName() != null
					&& !caseTypeStepVO
							.getDfltAlertBasedOnColName()
							.equalsIgnoreCase(
									MaintainContactManagementUIConstants.EMPTY_STRING)) {

				caseTypeStepDO.setDefaultAlertBasedOnColName(caseTypeStepVO
						.getDfltAlertBasedOnColName());

			}

			if (caseTypeStepVO.getDfltSendAlertDaysCode() != null
					&& !caseTypeStepVO
							.getDfltSendAlertDaysCode()
							.equalsIgnoreCase(
									MaintainContactManagementUIConstants.EMPTY_STRING)) {

				caseTypeStepDO.setDefaultSendAlertDaysCode(caseTypeStepVO
						.getDfltSendAlertDaysCode());
			}
			if (caseTypeStepVO.getDfltBeforeAfterCode() != null
					&& !caseTypeStepVO
							.getDfltBeforeAfterCode()
							.equalsIgnoreCase(
									MaintainContactManagementUIConstants.EMPTY_STRING)) {

				caseTypeStepDO.setDefaultBeforeAfterCode(caseTypeStepVO
						.getDfltBeforeAfterCode());
			}

			if (caseTypeStepVO.getDfltCotsLtrTmpltKeyData() != null
					&& !caseTypeStepVO.getDfltCotsLtrTmpltKeyData()
							.equalsIgnoreCase("")) {
				caseTypeStepDO
						.setLetterTemplate(getLetterTemplateForLetterData(caseTypeStepVO
								.getDfltCotsLtrTmpltKeyData()));
			}
			caseTypeStepDO.setVersionNo(caseTypeStepVO.getVersionNo());

			if (caseTypeStepVO.getStepSeqNum() != null) {
				caseTypeStepDO.setStepSeqNum(caseTypeStepVO.getStepSeqNum());
				logger.info("caseTypeStepDO.getStepSeqNum"
						+ caseTypeStepDO.getStepSeqNum());
			}

		}

		/*
		 * if(caseTypeStepVO.getAuditUserID() ==null) {
		 * caseTypeStepDO.setAuditUserID(userID); } else {
		 * caseTypeStepDO.setAuditUserID(caseTypeStepVO.getAuditUserID()); }
		 */

		if (caseTypeStepVO != null && caseTypeStepVO.getAddedAuditUserID() == null) {
			caseTypeStepDO.setAddedAuditUserID(userID);
		} else {
			caseTypeStepDO.setAddedAuditUserID(caseTypeStepVO
					.getAddedAuditUserID());
		}

		caseTypeStepDO.setAuditUserID(userID);

		return caseTypeStepDO;

	}

	/**
	 * This method is used to convert Case Type Step Value Object to Case Type
	 * Step Domain object.
	 * 
	 * @param caseTypeEventVO :
	 *            CaseTypeEventVO
	 * @return caseTypeEventDO : CaseTypeEvent
	 */
	public CaseTypeEvent convertCaseTypeEventVOToDO(
			CaseTypeEventVO caseTypeEventVO) {
		logger.info("convertCaseTypeEventVOToDO");

		CaseTypeEvent caseTypeEventDO = new CaseTypeEvent();
		if (caseTypeEventVO.getDfltCaseTypeEventTypeCode() != null
				&& !caseTypeEventVO
						.getDfltCaseTypeEventTypeCode()
						.equalsIgnoreCase(
								MaintainContactManagementUIConstants.EMPTY_STRING)) {
			caseTypeEventDO.setCmCaseEventCode(caseTypeEventVO
					.getDfltCaseTypeEventTypeCode());
		}
		if (caseTypeEventVO.getDfltNotfyAlertUserId() != null
				&& !caseTypeEventVO.getDfltNotfyAlertUserId().equalsIgnoreCase(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {

			CaseTypeDataBean caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);//getCaseTypeDataBean();
			//String userSk = null; 
			int listSize = caseTypeDataBean.getUserWorkUnitList().size();
			Long userSk = Long.valueOf(0);
			for (int i = 0; i < listSize; i++) {
				//Modify DEFECT_ESPRD00723971 Starts
				Object[] userDetails = (Object[]) caseTypeDataBean
						.getUserWorkUnitList().get(i);
				if (caseTypeEventVO.getDfltNotfyAlertUserId().equalsIgnoreCase(userDetails[2].toString())) {
						userSk = (Long) userDetails[3];
						//Modify DEFECT_ESPRD00723971 Ends
				}
			}

			EnterpriseUser enterpriseUser = new EnterpriseUser();
			WorkUnit workUnit = new WorkUnit();
			workUnit.setWorkUnitSK(userSk);
			enterpriseUser.setWorkUnit(workUnit);
			enterpriseUser.setUserID(caseTypeEventVO.getDfltNotfyAlertUserId());
			caseTypeEventDO.setEnterpriseUser(enterpriseUser);
		}
		if (caseTypeEventVO.getDfltAlertBasedOnColName() != null
				&& !caseTypeEventVO
						.getDfltAlertBasedOnColName()
						.equalsIgnoreCase(
								MaintainContactManagementUIConstants.EMPTY_STRING)) {
			caseTypeEventDO.setDefaultAlertBasedOnColName(caseTypeEventVO
					.getDfltAlertBasedOnColName());
		}
		if (caseTypeEventVO.getDfltSendAlertDaysCode() != null
				&& !caseTypeEventVO
						.getDfltSendAlertDaysCode()
						.equalsIgnoreCase(
								MaintainContactManagementUIConstants.EMPTY_STRING)) {

			caseTypeEventDO.setDefaultSendAlertDaysCode(caseTypeEventVO
					.getDfltSendAlertDaysCode());
		}

		if (caseTypeEventVO.getDfltBeforeAfterCode() != null
				&& !caseTypeEventVO.getDfltBeforeAfterCode().equalsIgnoreCase(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {
			caseTypeEventDO.setDefaultBeforeAfterCode(caseTypeEventVO
					.getDfltBeforeAfterCode());
		}

		if (caseTypeEventVO.getDfltCotsLtrTmltKeyData() != null
				&& !caseTypeEventVO.getDfltCotsLtrTmltKeyData()
						.equalsIgnoreCase("")) {
			caseTypeEventDO
					.setLetterTemplate(getLetterTemplateForLetterData(caseTypeEventVO
							.getDfltCotsLtrTmltKeyData()));
		}

		caseTypeEventDO.setVersionNo(caseTypeEventVO.getVersionNo());
		logger.debug("Version number fromVotoDo caseTypeEventDO:"
				+ caseTypeEventDO.getVersionNo());

		if (caseTypeEventVO.getCaseEventSeqNum() != null) {
			caseTypeEventDO.setCaseEventSeqNum(caseTypeEventVO
					.getCaseEventSeqNum());
			logger.info("caseTypeEventDO.getCaseEventSeqNum"
					+ caseTypeEventDO.getCaseEventSeqNum());
		}

		/*
		 * if(caseTypeEventVO.getAuditUserID() == null) {
		 * caseTypeEventDO.setAuditUserID(userID); } else {
		 * caseTypeEventDO.setAuditUserID(caseTypeEventVO.getAuditUserID()); }
		 */
		if (caseTypeEventVO.getAddedAuditUserID() == null) {
			caseTypeEventDO.setAddedAuditUserID(userID);
		} else {
			caseTypeEventDO.setAddedAuditUserID(caseTypeEventVO
					.getAddedAuditUserID());
		}

		caseTypeEventDO.setAuditUserID(userID);
		return caseTypeEventDO;
	}

	/**
	 * This method used to convertCustomFieldVotoDo
	 * 
	 * @param customFieldVO
	 *            represents CustomFieldVO
	 * @return customField represents CustomField
	 */
	public CustomField convertCustomFieldVoToDo(CustomFieldVO customFieldVO) {
		logger.debug("Inside CustomFieldVo to Do");
		CustomField customField = new CustomField();
		if (customFieldVO.getColumnDescription() != null) {
			customField.setDescription(customFieldVO.getColumnDescription());
		}
		if (customFieldVO.getLength() != null) {

			customField.setTotalLengthInBytes(Integer.valueOf(customFieldVO
					.getLength()));
		}
		if (customFieldVO.getDataType() != null) {

			customField.setDataTypeCode(customFieldVO.getDataType());
		}
		if (customFieldVO.getRequiredInd() != null) {

			customField.setRequiredValueIndicator(Boolean.valueOf(customFieldVO
					.getRequiredInd()));
		}
		if (customFieldVO.getProtectedInd() != null) {

			customField.setValueProtectedIndicator(Boolean
					.valueOf(customFieldVO.getProtectedInd()));
		}
		if (customFieldVO.getCustomFieldSK() != null) {

			customField.setCustomFieldSK(customFieldVO.getCustomFieldSK());
			logger.debug("whensk not null votodo"
					+ customField.getCustomFieldSK());
		}
		customField.setVersionNo(customFieldVO.getVersionNo());
		logger.debug("Version number fromVotoDo customField:"
				+ customField.getVersionNo());
		/*
		 * if (customFieldVO.getAuditUserID() == null) {
		 * customField.setAuditUserID(userID); } else {
		 * customField.setAuditUserID(customFieldVO.getAuditUserID()); }
		 */
		if (customFieldVO.getAddedAuditUserID() == null) {
			customField.setAddedAuditUserID(userID);
		} else {
			customField
					.setAddedAuditUserID(customFieldVO.getAddedAuditUserID());
		}
		customField.setAuditUserID(userID);
		return customField;
	}

	/**
	 * This method converts Template VotoDo
	 * 
	 * @param caseTypeLetterTemplateVO
	 *            Represents CaseTypeLetterTemplateVO
	 * @param caseType
	 *            Represents CaseType
	 * @return caseTypeTemplate Represents CaseTypeTemplate
	 */
	public CaseTypeTemplate convertTemplateVoToDo(
			CaseTypeLetterTemplateVO caseTypeLetterTemplateVO) {
		CaseTypeTemplate caseTypeTemplate = new CaseTypeTemplate();

		if (caseTypeLetterTemplateVO.getLetterTemplateKeyData() != null) {
			caseTypeTemplate.setLetterTemplateKeyData(caseTypeLetterTemplateVO
					.getLetterTemplateKeyData());

		}
		caseTypeTemplate.setVersionNo(caseTypeLetterTemplateVO.getVersionNo());

		/*
		 * if (caseTypeLetterTemplateVO.getAuditUserID() == null) {
		 * caseTypeTemplate.setAuditUserID(userID); } else {
		 * caseTypeTemplate.setAuditUserID(caseTypeLetterTemplateVO.getAuditUserID()); }
		 */
		if (caseTypeLetterTemplateVO.getAuditTimeStamp() == null) {
			caseTypeTemplate.setAuditTimeStamp(new Date());
		} else {
			caseTypeTemplate.setAuditTimeStamp(caseTypeLetterTemplateVO
					.getAuditTimeStamp());
		}
		if (caseTypeLetterTemplateVO.getAddedAuditUserID() == null) {
			caseTypeTemplate.setAddedAuditUserID(userID);
		} else {
			caseTypeTemplate.setAddedAuditUserID(caseTypeLetterTemplateVO
					.getAddedAuditUserID());
		}
		if (caseTypeLetterTemplateVO.getAddedAuditTimeStamp() == null) {
			caseTypeTemplate.setAddedAuditTimeStamp(new Date());
		} else {
			caseTypeTemplate.setAddedAuditTimeStamp(caseTypeLetterTemplateVO
					.getAddedAuditTimeStamp());
		}
		caseTypeTemplate.setAuditUserID(userID);
		return caseTypeTemplate;
	}

	/**
	 * This operation is used for converting CaseType Vo to CaseType DO
	 * 
	 * @param caseTypeVO
	 *            Represents CaseTypeVO
	 * @return caseTypeDo represents CaseType
	 */
	public CaseType convertCaseTypeVOToDO(CaseTypeVO caseTypeVO) {

		logger.debug("Got call to convertCaseTypeVOToDO ");

		CaseType caseTypeDo = new CaseType();
		CaseTypeEvent caseTypeEventDo = new CaseTypeEvent();
		CaseTypeStep caseTypeStepDo = new CaseTypeStep();
		CaseTypeStepVO caseTypeStepVO = new CaseTypeStepVO();
		CaseTypeEventVO caseTypeEventVO = new CaseTypeEventVO();
		caseTypeDo.setValuesProtectedIndicator(Boolean.valueOf(caseTypeVO
				.getValuesProtectedIndicator()));
		caseTypeDo.setVersionNo(caseTypeVO.getVersionNo());
		logger.debug("Version Number from casetype:"
				+ caseTypeDo.getVersionNo());

		caseTypeDo.setShortDescription(caseTypeVO.getShortDesc());
		caseTypeDo.setDescription(caseTypeVO.getLongDesc());
		if (caseTypeVO.getBusnUnitCaseTypeCode() != null
				&& !caseTypeVO.getBusnUnitCaseTypeCode().equalsIgnoreCase(
						MaintainContactManagementUIConstants.EMPTY_STRING))
			caseTypeDo.setBusinessUnitCaseTypeCode(caseTypeVO
					.getBusnUnitCaseTypeCode());
		if (caseTypeVO.getCaseTypeSK() != null) {
			logger.debug("CasetypeSk from vo in VotodoConversion:"
					+ caseTypeVO.getCaseTypeSK());
			caseTypeDo.setCaseTypeSK(caseTypeVO.getCaseTypeSK());
		}

		if (caseTypeStepVO.getStepSeqNum() != null) {
			caseTypeStepDo.setStepSeqNum(caseTypeStepVO.getStepSeqNum());
			logger.info("caseTypeStepDo.getStepSeqNum"
					+ caseTypeStepDo.getStepSeqNum());
		}

		if (caseTypeEventVO.getCaseEventSeqNum() != null) {
			caseTypeEventDo.setCaseEventSeqNum(caseTypeEventVO
					.getCaseEventSeqNum());

		}

		if (caseTypeVO.getSprvsrRevwReqdInd() != null
				&& caseTypeVO.getSprvsrRevwReqdInd().equalsIgnoreCase("Yes")
				&& !caseTypeVO.getSprvsrRevwReqdInd().equalsIgnoreCase(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {
			caseTypeDo.setSupervisorReviewReqIndicator(Boolean
					.valueOf(ContactManagementConstants.TRUE));
		} else {
			caseTypeDo.setSupervisorReviewReqIndicator(Boolean
					.valueOf(ContactManagementConstants.FALSE));
		}
		if (caseTypeVO.getCaseTypeStatusCode() != null
				&& !caseTypeVO.getCaseTypeStatusCode().equalsIgnoreCase(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {
			logger.debug("caseTypeVO.getCaseTypeStatusCode():"
					+ caseTypeVO.getCaseTypeStatusCode());

			if (caseTypeVO.getCaseTypeStatusCode().equalsIgnoreCase("Yes")) {
				caseTypeDo.setStatusTypeCode("Y");
			} else {
				caseTypeDo.setStatusTypeCode("N");
			}
			logger.debug("caseTypeDo.setStatusTypeCode"
					+ caseTypeDo.getStatusTypeCode());
		} else {
			caseTypeDo.setStatusTypeCode("Y");
		}

		Set casestepvos = caseTypeVO.getCaseSteps();

		Set casestepdos = new HashSet();

		if (casestepvos != null && !casestepvos.isEmpty()) {
			logger.debug("Total case steps " + casestepvos.size());
			for (Iterator iter = casestepvos.iterator(); iter.hasNext();) {

				CaseTypeStepVO vo = (CaseTypeStepVO) iter.next();
				CaseTypeStep dostep = convertCaseTypeStepVOToDO(vo);
				logger.debug("Casestepcode in Casetypestep:"
						+ dostep.getCmCaseStepCode());
				logger.debug("OrderNumber in Casetypestep:  "
						+ dostep.getStepOrderNumber());
				dostep.setCaseType(caseTypeDo);
				casestepdos.add(dostep);

			}
		}
		caseTypeDo.setCaseTypeSteps(casestepdos);
		logger.debug(" DO steps list :" + caseTypeDo.getCaseTypeSteps().size());

		Set caseeventvos = caseTypeVO.getCaseEvents();
		Set caseeventdos = new HashSet();
		if (caseeventvos != null && !caseeventvos.isEmpty()) {
			logger.debug("Total case events " + caseeventvos.size());
			for (Iterator iter = caseeventvos.iterator(); iter.hasNext();) {

				CaseTypeEventVO vo = (CaseTypeEventVO) iter.next();
				CaseTypeEvent eventdo = convertCaseTypeEventVOToDO(vo);
				logger.debug("CmCaseEventCode in eventdo:"
						+ eventdo.getCmCaseEventCode());
				eventdo.setCaseType(caseTypeDo);
				caseeventdos.add(eventdo);
			}
		}
		caseTypeDo.setCaseTypeEvents(caseeventdos);
		System.err.println("++caseTypeVO.getListOfCustomFields()::"
				+ caseTypeVO.getListOfCustomFields());
		 if (caseTypeVO.getListOfCustomFields() != null) {

			Set setOfCustomAssignmentDOs = getCustomAssignmentsDOs(caseTypeVO
					.getCustomFieldAssignmentVO(), caseTypeDo);
			if (setOfCustomAssignmentDOs != null
					&& !setOfCustomAssignmentDOs.isEmpty()
					&& setOfCustomAssignmentDOs.size() > 0) {
				caseTypeDo.setCustomFieldAssignments(setOfCustomAssignmentDOs);
			} else {
				caseTypeDo.setCustomFieldAssignments(new HashSet());
			}

			/*
			 * logger.debug("InsidecasetypeVotodo: if list not null");
			 * logger.debug("CustomfieldSize from CasetypeVo:" +
			 * caseTypeVO.getListOfCustomFields().size());
			 * caseTypeDo.setCustomFieldAssignments(getCustomAssignmentsDOs(
			 * caseTypeVO.getListOfCustomFields(), caseTypeDo)); if
			 * (caseTypeDo.getCustomFieldAssignments() != null) {
			 * logger.debug("caseTypeDo setCustomFieldAssignments size:" +
			 * caseTypeDo.getCustomFieldAssignments().size()); }
			 */
		}

		List templateVOs = caseTypeVO.getListOfTemplates();
		Set templateDOs = new HashSet();
		if (templateVOs != null && !templateVOs.isEmpty()) {

			for (Iterator iter = templateVOs.iterator(); iter.hasNext();) {

				CaseTypeLetterTemplateVO vo = (CaseTypeLetterTemplateVO) iter
						.next();
				CaseTypeTemplate templatedo = convertTemplateVoToDo(vo);

				templatedo.setCaseType(caseTypeDo);
				templateDOs.add(templatedo);
			}
		}
		caseTypeDo.setCaseTypeTemplates(templateDOs);
		if (caseTypeVO.getAddedAuditUserID() == null) {
			caseTypeDo.setAddedAuditUserID(userID);
		} else {
			caseTypeDo.setAddedAuditUserID(caseTypeVO.getAddedAuditUserID());
		}

		caseTypeDo.setAuditUserID(userID);

		caseTypeDo.setAuditTimeStamp(getTimeStamp());
		if (caseTypeVO.getAddedAuditTimeStamp() == null) {
			caseTypeDo.setAddedAuditTimeStamp(getTimeStamp());
		} else {
			caseTypeDo.setAddedAuditTimeStamp(caseTypeVO
					.getAddedAuditTimeStamp());
		}

		return caseTypeDo;
	}

	/**
	 * This method is used to get the Current Timestamp.
	 * 
	 * @return Timestamp : Current Timestamp.
	 */
	private Timestamp getTimeStamp() {
		Calendar cal = Calendar.getInstance();
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * This method is used to check if the input field is null or is equal to an
	 * empty string.
	 * 
	 * @param inputField :
	 *            String inputField
	 * @return boolean : true if input field is null or equal to an empty string
	 *         else false
	 */
	/*
	 * private boolean isNullOrEmptyField(String inputField) {
	 * 
	 * return (inputField == null || ContactManagementConstants.EMPTY_STRING
	 * .equalsIgnoreCase(inputField.trim())); }
	 */

	  public Set getCustomAssignmentsDOs(List listOfCustomFieldsVO,
			CaseType caseType) {
		logger
				.info("In getCustomAssignmentsDOs:"
						+ listOfCustomFieldsVO.size());
		Set setOfCustomAssignmentDOs = new HashSet();

		for (Iterator iter = listOfCustomFieldsVO.iterator(); iter.hasNext();) {
			CustomFieldVO customFieldVO = (CustomFieldVO) iter.next();
			CustomFieldAssignment customFieldAssignmentDO = convertCustomFieldVOtODO(
					customFieldVO, caseType);
			setOfCustomAssignmentDOs.add(customFieldAssignmentDO);
		}

		/*
		 * for (Iterator iter = listOfCustomFieldsVO.iterator();
		 * iter.hasNext();) { CustomFieldVO customFieldVO = (CustomFieldVO)
		 * iter.next(); CustomField customField =
		 * convertCustomFieldVoToDo(customFieldVO);
		 * 
		 * CustomFieldAssignment customFieldAssignment =
		 * convertCustomFieldAssignmentVoToDo( customField, caseType);
		 * logger.debug("CustomFieldSK : " +
		 * customFieldAssignment.getCustomFieldSK());
		 * setOfCustomAssignmentDOs.add(customFieldAssignment);
		 */

		if (setOfCustomAssignmentDOs.size() > 0) {
			logger.debug("inside setOfCustomAssignmentDOs:"
					+ setOfCustomAssignmentDOs.size());
		} else {
			logger.debug("Customassignment set is null");
		}

		return setOfCustomAssignmentDOs;
	}

	public CustomFieldAssignment convertCustomFieldAssignmentVoToDo(
			CustomField customField, CaseType caseType) {
		CustomFieldAssignmentVO customFieldAssignmentVO = new CustomFieldAssignmentVO();
		logger.debug("Inside CustomFieldAssignmentVO to Do");
		CustomFieldAssignment customFieldAssignment = new CustomFieldAssignment();

		if (customField.getCustomFieldSK() != null) {
			customFieldAssignmentVO.setCustomFieldSK(customField
					.getCustomFieldSK());
		}
		if (customField.getRequiredValueIndicator() != null) {
			customFieldAssignmentVO.setRequiredIndicator(customField
					.getRequiredValueIndicator());
		}
		if (customField.getValueProtectedIndicator() != null) {
			customFieldAssignmentVO.setModifyAfterSaveIndicator(customField
					.getValueProtectedIndicator());
		}
		customFieldAssignmentVO.setDisplaySortOrderNum(Integer.valueOf(1));
		/*		 * FIND							 * BUGS_FIX	 */

		if (caseType != null) {
			customFieldAssignment.setCaseType(caseType);
			customFieldAssignment.setSubjectAreaSK(caseType.getCaseTypeSK());

		}

		if (customFieldAssignmentVO.getCustomFieldSK() != null) {
			customFieldAssignment.setCustomFieldSK(customFieldAssignmentVO
					.getCustomFieldSK());
		}
		if (customFieldAssignmentVO.getSubjectAreaSK() != null) {
			customFieldAssignment.setSubjectAreaSK(customFieldAssignmentVO
					.getSubjectAreaSK());
		}

		customFieldAssignment.setTableName("G_CASE_TB");
		if (customFieldAssignmentVO.getDisplaySortOrderNum() != null) {
			customFieldAssignment
					.setDisplaySortOrderNum(customFieldAssignmentVO
							.getDisplaySortOrderNum());
		}
		if (customFieldAssignmentVO.getRequiredIndicator() != null) {
			customFieldAssignment.setRequiredIndicator(customFieldAssignmentVO
					.getRequiredIndicator());
		}
		if (customFieldAssignmentVO.getModifyAfterSaveIndicator() != null) {
			customFieldAssignment
					.setModifyAfterSaveIndicator(customFieldAssignmentVO
							.getModifyAfterSaveIndicator());
		}
		//Modified for defect ESPRD00682341 starts
		customFieldAssignment.setVersionNo(customField.getVersionNo());
		System.err.println("++customField.getVersionNo()::"
				+ customField.getVersionNo());
		System.err.println("++customFieldAssignmentVO.getVersionNo()::"
				+ customFieldAssignmentVO.getVersionNo());
		//    	customFieldAssignment.setVersionNo(customFieldAssignmentVO.getVersionNo());
		//defect ESPRD00682341 ends
		logger.debug("Version number fromVotoDo customFieldAssignment:"
				+ customFieldAssignment.getVersionNo());

		customFieldAssignment.setAuditUserID(customField.getAuditUserID());
		customFieldAssignment
				.setAuditTimeStamp(customField.getAuditTimeStamp());
		customFieldAssignment.setAddedAuditUserID(customField
				.getAddedAuditUserID());
		customFieldAssignment.setAddedAuditTimeStamp(customField
				.getAddedAuditTimeStamp());
		logger.debug("End of CustomFieldAssignmentVOto Do");
		return customFieldAssignment;
	}

	public List convertCustAssignmentsSetToList(Set setOfObjects) {
		logger.debug("Set to List convertor");

		List listOfObjects = new ArrayList();
		for (Iterator iter = setOfObjects.iterator(); iter.hasNext();) {
			CustomFieldAssignment customFieldAssignment = (CustomFieldAssignment) iter
					.next();
			listOfObjects.add(customFieldAssignment);
		}
		return listOfObjects;
	}

	/**
	 * This operation is used for getting casestep dOs from casetypeVO
	 * 
	 * @param stepdos :
	 *            Set of caseStep Value Objects
	 * @return stepvos :Set set of case step Vos
	 */
	public List getCaseStepsDo(List caseStepDelVOS, CaseType caseTypeDO) {
		List stepDelDOS = new ArrayList();
		logger.debug("getCasestepsVo in Converter");

		for (Iterator iter = caseStepDelVOS.iterator(); iter.hasNext();) {
			CaseTypeStepVO stepVo = (CaseTypeStepVO) iter.next();

			CaseTypeStep stepdo = convertCaseTypeStepVOToDO(stepVo);
			stepdo.setCaseType(caseTypeDO);
			stepDelDOS.add(stepdo);

		}
		return stepDelDOS;
	}

	/**
	 * This operation is used for getting casestep dOs from casetypeVO
	 * 
	 * @param caseEventDelVOS :
	 *            List of caseEventDel Value Objects
	 * @return caseEventDelDOS :List of case Event Vos
	 */
	public List getCaseEventsDo(List caseEventDelVOS, CaseType caseTypeDO) {
		List eventDelDOS = new ArrayList();
		logger.debug("getCasestepsVo in Converter");

		for (Iterator iter = caseEventDelVOS.iterator(); iter.hasNext();) {
			CaseTypeEventVO eventVo = (CaseTypeEventVO) iter.next();

			CaseTypeEvent eventdo = convertCaseTypeEventVOToDO(eventVo);
			eventdo.setCaseType(caseTypeDO);
			eventDelDOS.add(eventdo);

		}
		return eventDelDOS;
	}

	/**
	 * This operation is used for getting CaseTypeTempalte dOs from
	 * CaseTypeTempalte VOs
	 * 
	 * @param CaseTypeTempalte
	 *            Vos : Set of CaseTypeTempalte Value Objects
	 * @return CaseTypeTempalte Vos :List of CaseTypeTempalte Vos
	 */
	public List getCaseTypeTempalteDo(List caseTypeTemplateDelVOS,
			CaseType caseTypeDO) {
		List tempalteDelDOS = new ArrayList();
		logger.debug("getCasestepsVo in Converter");

		for (Iterator iter = caseTypeTemplateDelVOS.iterator(); iter.hasNext();) {
			CaseTypeLetterTemplateVO templateVo = (CaseTypeLetterTemplateVO) iter
					.next();

			CaseTypeTemplate tempaltedo = convertTemplateVoToDo(templateVo);
			tempaltedo.setCaseType(caseTypeDO);
			tempalteDelDOS.add(tempaltedo);

		}
		return tempalteDelDOS;
	}

	/**
	 * This method is used to get the LetterTemplate object for termplateID .
	 * 
	 * @param templateID
	 *            holds the template ID.
	 * @return it returns the LetterTemplate object with ID
	 */
	private LetterTemplate getLetterTemplateForLetterData(String templateID) {
		LetterTemplate letterTemplate = new LetterTemplate();
		letterTemplate.setLetterTemplateKeyData(templateID);
		return letterTemplate;
	}
	
	 public CustomFieldAssignment convertCustomFieldVOtODO(
			CustomFieldVO customFieldVO, CaseType caseType) {
		CustomFieldAssignment customFieldAssignment = new CustomFieldAssignment();
		CustomFieldScope customFieldScope = new CustomFieldScope();
		CustomFieldTable customFieldTable = new CustomFieldTable();
		CustomField customField = new CustomField();

		customField.setCustomFieldSK(customFieldVO.getCustomFieldSK());
		customFieldScope.setCustomFieldSK(customFieldVO.getCustomFieldSK());
		customFieldScope.setCustomField(customField);

		customFieldTable.setTableName("G_CASE_TB");
		customFieldScope.setTableName("G_CASE_TB");
		customFieldScope.setCustomFieldTable(customFieldTable);

		customFieldAssignment.setCustomFieldScope(customFieldScope);
		customFieldAssignment.setTableName("G_CASE_TB");

		if (customFieldVO.getCustomFieldSK() != null) {
			customFieldAssignment.setCustomFieldSK(customFieldVO
					.getCustomFieldSK());
		}
		if (customFieldVO.getRequiredInd() != null) {
			customFieldAssignment.setRequiredIndicator(Boolean
					.valueOf(customFieldVO.getRequiredInd()));
		}
		if (customFieldVO.getProtectedInd() != null) {
			customFieldAssignment.setModifyAfterSaveIndicator(Boolean
					.valueOf(customFieldVO.getProtectedInd()));
		}
		customFieldAssignment.setDisplaySortOrderNum(Integer.valueOf(1));

		if (caseType != null) {
			customFieldAssignment.setCaseType(caseType);
			customFieldAssignment.setSubjectAreaSK(caseType.getCaseTypeSK());

		}

		customFieldAssignment.setVersionNo(customFieldVO.getVersionNo());
		logger.debug("Version number fromVotoDo customFieldAssignment:"
				+ customFieldAssignment.getVersionNo());

		if (customFieldVO.getAuditUserID() == null) {
			customFieldAssignment.setAuditUserID(userID);
		} else {
			customFieldAssignment.setAuditUserID(customFieldVO.getAuditUserID());
		}
		if (customFieldVO.getAddedAuditUserID() == null) {
			customFieldAssignment.setAddedAuditUserID(userID);
		} else {
			customFieldAssignment
					.setAddedAuditUserID(customFieldVO.getAddedAuditUserID());
		}
		customFieldAssignment.setAuditTimeStamp(customFieldVO
				.getAuditTimeStamp());
		customFieldAssignment.setAddedAuditTimeStamp(customFieldVO
				.getAddedAuditTimeStamp());

		return customFieldAssignment;
	}

}