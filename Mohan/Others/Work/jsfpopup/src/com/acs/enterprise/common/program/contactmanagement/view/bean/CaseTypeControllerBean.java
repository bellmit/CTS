/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.audit.application.exception.GlobalAuditsBusinessException;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.lineitemaudit.common.AuditKey;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.delegate.SystemListDelegate;
import com.acs.enterprise.common.program.administration.common.domain.SystemList;
import com.acs.enterprise.common.program.administration.common.domain.SystemListDetail;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseTypeCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseTypeDeleteBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseTypeFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseTypeUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CustomFieldFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseTypeSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CaseTypeDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CaseTypeVOToDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.Executor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeEventVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeLetterTemplateVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeStepVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldAssignmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;

/**
 * This class will be the Controller bean for Maintain Case Type
 * 
 * @author Wipro
 */
public class CaseTypeControllerBean extends EnterpriseBaseControllerBean {
	/** Instance of the Enterprise Logger */
	
	private static final EnterpriseLogger logger = EnterpriseLogFactory
	.getLogger(CaseTypeControllerBean.class);

	/**
	 * Generating object of EnterpriseLogger.
	 */

	
	private CaseTypeDataBean caseTypeDataBean;
	
	private String link2Show;
	
	private String loadValidValues;
	
	/**
	 * for defect ESPRD00805369. Holds the boolean value that indicate whether
	 * validation is passed or not before Big Save
	 */
	private boolean validateFlag;
	
	// Moved to ContactManagementConstants.java
	//public static final String CASE_TYPE_DATA_BEAN= "CaseTypeDataBean";

	

	/**
	 * Gets reference of CaseTypeDataBean.
	 * 
	 * @return caseTypeDataBean : CaseTypeDataBean
	 */

	

//  Added by Infinite while Performance Issues...
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
	 * This operation is used to display the add case type block.
	 * 
	 * @return String
	 */
	public String showAddCaseType() {
	    
	
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	//CFR
	    caseTypeDataBean.setMaintainCaseTypeFocusId("addEditCaseTypes");	    //EOF CFR
	    caseTypeDataBean.setFirstFieldFocusID("casetypesDesc");//CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
	caseTypeDataBean.setShowAddCaseTypes(ContactManagementConstants.TRUE);
	caseTypeDataBean.setShowEditCaseTypes(ContactManagementConstants.FALSE);
	caseTypeDataBean.setShowEditCaseSteps(ContactManagementConstants.FALSE);
	caseTypeDataBean.setShowAddCaseSteps(ContactManagementConstants.FALSE);
	caseTypeDataBean.setShowAddCaseEvents(ContactManagementConstants.FALSE);
	caseTypeDataBean.setShowDeleteMessage(ContactManagementConstants.FALSE);
	caseTypeDataBean.setShowDeleteFlag(ContactManagementConstants.FALSE);
	caseTypeDataBean.setShowDescOrderFlag(ContactManagementConstants.FALSE);
	caseTypeDataBean.setShowSucessMessage(ContactManagementConstants.FALSE);
	caseTypeDataBean.setShowTypeDescFlag(ContactManagementConstants.FALSE);
	caseTypeDataBean
			.setShowEditCaseEvents(ContactManagementConstants.FALSE);

	caseTypeDataBean.setNoCaseStepsData(ContactManagementConstants.FALSE);
	caseTypeDataBean.setNoCaseEventsData(ContactManagementConstants.FALSE);

	CaseTypeDOConverter caseTypeDOConvertor = new CaseTypeDOConverter();
	ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
	loadTemplatesData(caseTypeDOConvertor, contactMaintenanceDelegate);
	loadCustomFieldsData(caseTypeDOConvertor, contactMaintenanceDelegate);

	if (isNotNullAndNotEmptyList(caseTypeDataBean.getMaintainCaseTypeList())) {
		caseTypeDataBean
				.setNoCaseTypesData(ContactManagementConstants.TRUE);
	}
	
	caseTypeDataBean.setCaseTypeVO(new CaseTypeVO());
//	//Modified for defect ESPRD00371403 starts
//	try {
//		Set activeCaseStepSet = new HashSet(getAvailActiveCaseSteps());
//		List caseTypeStepsListVO = new ArrayList();
//		List caseTypeStepVOs = caseTypeDOConvertor
//				.getActiveCaseStepsVO(activeCaseStepSet);
//		logger.debug("caseTypeStepVOs  size is:"
//				+ caseTypeStepVOs.size());
////		for (Iterator iter = caseTypeStepVOs.iterator(); iter.hasNext();) {
////			CaseTypeStepVO caseTypeStepVO = (CaseTypeStepVO) iter.next();
////			if (StringUtils.isNotEmpty(caseTypeStepVO.getDescription())) {
////				String stepDesc = ContactManagementHelper
////						.setShortDescription(
////								FunctionalAreaConstants.GENERAL,
////								ReferenceServiceDataConstants.G_CM_CASE_STEP_CD,
////								caseTypeStepVO.getDescription());
////				caseTypeStepVO.setDescValue(stepDesc);
////			}
////			if (StringUtils.isNotEmpty(caseTypeStepVO.getStepOrderNum())) {
////				String orderDesc = ContactManagementHelper
////						.setShortDescription(
////								FunctionalAreaConstants.GENERAL2,
////								ReferenceServiceDataConstants.G_STEP_ORDER_NUM,
////								caseTypeStepVO.getStepOrderNum());
////				caseTypeStepVO.setStepOrderNumDesc(orderDesc);
////			}
////
////			caseTypeStepsListVO.add(caseTypeStepVO);
////		}
//		caseTypeDataBean.setCaseTypeStepsList(caseTypeStepsListVO);
//		caseTypeDataBean.setNoCaseStepsData(true);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	//defect ESPRD00371403 ends
	
	caseTypeDataBean.setCaseTypeEventVO(new CaseTypeEventVO());
	caseTypeDataBean.getCaseTypeStepsList().clear();
	caseTypeDataBean.getCaseTypeEventsList().clear();
	//Added for the defect ESPRD00853112
	caseTypeDataBean.getTemplateDropDownList().clear();
	return ContactManagementConstants.RENDER_SUCCESS;}

	/**
	 * This operation is used to create new CaseType Record.
	 * 
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String addCaseType() 
	{
		if(logger.isDebugEnabled())
		{
		logger.debug("Got call to add caseType method  ");
		}
		
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		CaseTypeVO caseTypeVO = caseTypeDataBean.getCaseTypeVO();
		//Modified for the defect ESPRD00853112 Starts
		if (caseTypeDataBean.isShowAddCaseSteps()) {
			addCaseStep();
			if (!validateFlag) {
				return ContactManagementConstants.EMPTY_STRING;
			}
		} else if (caseTypeDataBean.isShowEditCaseSteps()) {
			saveEditCaseStep();
			if (!validateFlag) {
				return ContactManagementConstants.EMPTY_STRING;
			}
		}
		if (caseTypeDataBean.isShowAddCaseEvents()) {
			addCaseEvent();
			if (!validateFlag) {
				return ContactManagementConstants.EMPTY_STRING;
			}
		} else if (caseTypeDataBean.isShowEditCaseEvents()) {
			saveEditCaseEvent();
			if (!validateFlag) {
				return ContactManagementConstants.EMPTY_STRING;
			}
		}
		//Defect ESPRD00853112 ends
		if (validateCaseTypeVO(caseTypeVO,
				MaintainContactManagementUIConstants.ADD)) {
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			CaseTypeVOToDOConverter converter = new CaseTypeVOToDOConverter();
			Set casesteps = new HashSet();

			List stepList = caseTypeDataBean.getCaseTypeStepsList();
			if (isNotNullAndNotEmptyList(stepList)) {
				for (Iterator iter = stepList.iterator(); iter.hasNext();) {
					casesteps.add((CaseTypeStepVO) iter.next());
				}

			}

			caseTypeDataBean.getCaseTypeVO().setCaseSteps(casesteps);
			Set caseevents = new HashSet();
			List eventList = caseTypeDataBean.getCaseTypeEventsList();
			if (isNotNullAndNotEmptyList(eventList)) {
				for (Iterator iter = eventList.iterator(); iter.hasNext();) {
					caseevents.add((CaseTypeEventVO) iter.next());
				}
			}
			caseTypeDataBean.getCaseTypeVO().setCaseEvents(caseevents);
			if (caseTypeVO.getCaseSteps() != null
					&& caseTypeVO.getCaseEvents() != null)
			{
				if(logger.isDebugEnabled())
				{
				logger.debug("casestep size in Mainvo:"
						+ caseTypeVO.getCaseSteps().size());
				logger.debug("caseevent size in Mainvo:"
						+ caseTypeVO.getCaseEvents().size());
				}
				
			}
			/**
			 * Getting Selected Custom Fields
			 */

			List tempList = caseTypeDataBean.getMaintainCaseCustomFieldsList();
			//heap dump issue fix
			/*for (Iterator iter = tempList.iterator(); iter.hasNext();) {
				CustomFieldVO element = (CustomFieldVO) iter.next();
				

			}*/
			//heap dump issue fix
			
			List selectedCustomList = getSelectedCustomFields(tempList);
			
			caseTypeVO.setListOfCustomFields(selectedCustomList);
			caseTypeVO.setCustomFieldAssignmentVO(selectedCustomList);
			List templateList = caseTypeDataBean.getMaintainCaseTemplatesList();
			List selectedTemplateList = getSelectedTemplates(templateList);
			caseTypeVO.setListOfTemplates(selectedTemplateList);
			CaseType caseTypeDo = converter.convertCaseTypeVOToDO(caseTypeVO);
			CaseType caseType = null;//heap dump issue fix new CaseType();

				
			try {
				if(logger.isDebugEnabled()){
				logger.debug("Intry block of AddCaseType");
				}
				caseType = contactMaintenanceDelegate
						.createCaseType(caseTypeDo);
				//Commented for the defect ESPRD00853112
				/*ContactManagementHelper
						.setErrorMessage(MaintainContactManagementUIConstants.CASETYPE_SAVE_SUCCESS);*/
				caseTypeDataBean.setShowSucessMessage(true);
				if (caseType.getCaseTypeSK() != null) {
					CaseTypeDOConverter caseTypeDOConverter = new CaseTypeDOConverter();
					caseTypeVO = caseTypeDOConverter
							.convertCaseTypeDOToVO(caseType);
					if (StringUtils.isNotEmpty(caseTypeVO
							.getBusnUnitCaseTypeCode())) {
						
						
						
						// Added by ICS 
						// Modify Defect ESPRD00723971 Starts
						/*String buDesc =  ContactManagementHelper.setShortDescription(FunctionalAreaConstants.GENERAL,
				                ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD,caseTypeVO.getBusnUnitCaseTypeCode());*/
	                	
						
					    caseTypeVO.setBusnUnitTypeCodeDesc(getDescriptionFromVV(caseTypeVO.getBusnUnitCaseTypeCode(), 
					    								caseTypeDataBean.getBussinessUnits()));
					    // Modify Defect ESPRD00723971 Ends
						// End
						
						
					}
					caseTypeDataBean.getMaintainCaseTypeList().add(caseTypeVO);
					caseTypeDataBean.setCaseTypeVO(caseTypeVO);
					//Modified for defect ESPRD00357599 starts
					caseTypeDataBean.setShowEditCaseTypes(true);
					caseTypeDataBean.setShowAddCaseTypes(false);
					caseTypeDataBean.setEditCaseTypeRowIndex(caseTypeDataBean.getMaintainCaseTypeList().size()-1);
					FacesContext fc = FacesContext.getCurrentInstance();
					HttpSession httpSession=(HttpSession)fc.getExternalContext().getSession(true);
					httpSession.setAttribute("EditCaseTypeRowIndex",String.valueOf(caseTypeDataBean.getMaintainCaseTypeList().size()-1));

					getCaseTypeDetails();
					caseTypeDataBean.setShowSucessMessage(true);

				}
				//Commented for the defect ESPRD00853112
				//ContactManagementHelper.setErrorMessage("success.saved");
				caseTypeDataBean.setShowSucessMessage(true);
				caseTypeDataBean.setShowEventActMsg(false);
				caseTypeDataBean.setShowStepActMsg(false);

				caseTypeDataBean.setCaseTypeStepVO(new CaseTypeStepVO());
				caseTypeDataBean.setCaseTypeEventVO(new CaseTypeEventVO());
				
			} catch (CaseTypeCreateBusinessException e) {
				logger.error("Error at adding Case Type");
				ContactManagementHelper
						.setErrorMessage(MaintainContactManagementUIConstants.SAVE_FAILURE);
				caseTypeDataBean.setShowSucessMessage(false);

			} 
			caseTypeDataBean.setWarnBeforeExitStatus("saveOrUpdate");
		}
		if(logger.isInfoEnabled()){
			logger.info("End of the Add case Type Method ");
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to update CaseType Record.
	 * 
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String updateCaseType() {
		if(logger.isDebugEnabled()){
		logger.debug("***Got call to updateCaseType ");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		//CFR
		caseTypeDataBean.setMaintainCaseTypeFocusId("deleteCaseTypId"); 
		//EOF CFR
		caseTypeDataBean.setShowAddCaseTypes(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEditCaseTypes(ContactManagementConstants.TRUE);
		try {
		if (caseTypeDataBean.isShowAddCaseSteps()) {
			addCaseStep();
			//for defect ESPRD00805369
				if (!validateFlag) {
					if (logger.isDebugEnabled()) {
						logger
								.debug("save case steps in maintain case type validation fails");
					}
					return ContactManagementConstants.EMPTY_STRING;
				}
		} else if (caseTypeDataBean.isShowEditCaseSteps()) {
			saveEditCaseStep();
			//for defect ESPRD00805369
				if (!validateFlag) {
					if (logger.isDebugEnabled()) {
						logger
								.debug("update case steps in maintain case type validation fails");
					}
					return ContactManagementConstants.EMPTY_STRING;
				}
		}
		if (caseTypeDataBean.isShowAddCaseEvents()) {
			addCaseEvent();
			//for defect ESPRD00805369
				if (!validateFlag) {
					if (logger.isDebugEnabled()) {
						logger
								.debug("save case event in maintain case type validation fails");
					}
					return ContactManagementConstants.EMPTY_STRING;
				}
		} else if (caseTypeDataBean.isShowEditCaseEvents()) {
			saveEditCaseEvent();
			//for defect ESPRD00805369
				if (!validateFlag) {
					if (logger.isDebugEnabled()) {
						logger
								.debug("update case events in maintain case type validation fails");
					}
					return ContactManagementConstants.EMPTY_STRING;
				}
		}
		Set casesteps = new HashSet();
		List caseStepList = caseTypeDataBean.getCaseTypeStepsList();
		if (isNotNullAndNotEmptyList(caseTypeDataBean.getCaseTypeStepsList())) {
			for (Iterator iter = caseStepList.iterator(); iter.hasNext();) {
				CaseTypeStepVO caseStepVO = (CaseTypeStepVO) iter.next();
				
				casesteps.add(caseStepVO);
			}

		}
		caseTypeDataBean.getCaseTypeVO().setCaseSteps(casesteps);
		List caseEventList = caseTypeDataBean.getCaseTypeEventsList();
		Set caseevents = new HashSet();
		if (isNotNullAndNotEmptyList(caseEventList)) {
			for (Iterator iter = caseEventList.iterator(); iter.hasNext();) {
				CaseTypeEventVO caseEventVO = (CaseTypeEventVO) iter.next();
				caseevents.add(caseEventVO);
			}
		}
		caseTypeDataBean.getCaseTypeVO().setCaseEvents(caseevents);
		CaseTypeVO oldCaseTypeVO = caseTypeDataBean.getCaseTypeVO();
		if(logger.isDebugEnabled()){
		logger.debug("oldCaseTypeVO casetypesk in update:"
				+ oldCaseTypeVO.getCaseTypeSK());
		}
		Long caseTypeSk = oldCaseTypeVO.getCaseTypeSK();
		if (oldCaseTypeVO.getCaseSteps() != null) {
			if(logger.isDebugEnabled()){
			logger.debug("Casestep size in update from oldvo"
					+ oldCaseTypeVO.getCaseSteps().size());
			}
		}
		if (oldCaseTypeVO.getCaseEvents() != null) {
			if(logger.isDebugEnabled()){
			logger.debug("CaseEvents size in update from oldvo"
					+ oldCaseTypeVO.getCaseEvents().size());
			}
		}
		if (validateCaseTypeVO(oldCaseTypeVO,
				MaintainContactManagementUIConstants.UPDATE)) {
			CaseTypeVO newCaseTypeVO = new CaseTypeVO();
			try {
				BeanUtils.copyProperties(newCaseTypeVO, oldCaseTypeVO);
			} catch (IllegalAccessException e) {
				if(logger.isErrorEnabled()){
				logger.error("IllegalAccessException at UpdateCasetype()");
				}
			} catch (InvocationTargetException e) {
				if(logger.isErrorEnabled()){
				logger.error("InvocationTargetException at UpdateCasetype()");
				}
			}
			if (newCaseTypeVO.getCaseEvents() != null) {
				if(logger.isDebugEnabled()){
				logger.debug("CaseEvents size in update from newvo"
						+ newCaseTypeVO.getCaseEvents().size());
				}
			}
			
			List templateList = caseTypeDataBean.getMaintainCaseTemplatesList();
			List selectedTemplateList = getSelectedTemplates(templateList);
			newCaseTypeVO.setListOfTemplates(selectedTemplateList);
			/**
			 * Getting Associated customfields
			 */
			List tempList = caseTypeDataBean.getMaintainCaseCustomFieldsList();
			List selectedCustomList = getSelectedCustomFields(tempList);
			newCaseTypeVO.setListOfCustomFields(selectedCustomList);
			newCaseTypeVO.setCustomFieldAssignmentVO(selectedCustomList);
			CaseTypeVOToDOConverter caseTypeVOToDOConverter = new CaseTypeVOToDOConverter();
			CaseType caseTypeDO = new CaseType();
			caseTypeDO = caseTypeVOToDOConverter
					.convertCaseTypeVOToDO(newCaseTypeVO);
			Map delObj = new HashMap();
			List caseStepDelList = new ArrayList();
			List caseEventDelList = new ArrayList();
			List caseTypeTemplateDelList = new ArrayList();
			List caseTypeCustomFieldDelList = new ArrayList();

			
			if (!caseTypeDataBean.getCaseEventNoIncludeList().isEmpty()
					&& caseTypeDataBean.getCaseEventNoIncludeList().size() > 0) {
				List caseEventDelListVO = caseTypeDataBean
						.getCaseEventNoIncludeList();
				caseEventDelList = caseTypeVOToDOConverter.getCaseEventsDo(
						caseEventDelListVO, caseTypeDO);
				
				caseTypeDataBean.setCaseEventNoIncludeList(new ArrayList());

			}
			if (!caseTypeDataBean.getCaseStepNoIncludeList().isEmpty()
					&& caseTypeDataBean.getCaseStepNoIncludeList().size() > 0) {
				
				List caseStepDelListVO = caseTypeDataBean
						.getCaseStepNoIncludeList();
				
				caseStepDelList = caseTypeVOToDOConverter.getCaseStepsDo(
						caseStepDelListVO, caseTypeDO);
				

				caseTypeDataBean.setCaseStepNoIncludeList(new ArrayList());
			}
			if (!caseTypeDataBean.getUnSelectedTemplates().isEmpty()
					&& caseTypeDataBean.getUnSelectedTemplates().size() > 0) {
				
				List templateDeleteList = getUnselectedCaseTypeTemplates(caseTypeDataBean
						.getUnSelectedTemplates());
				
				caseTypeTemplateDelList = caseTypeVOToDOConverter
						.getCaseTypeTempalteDo(templateDeleteList, caseTypeDO);
				
			}
			if (!caseTypeDataBean.getUnSelectedCustomFields().isEmpty()
					&& caseTypeDataBean.getUnSelectedCustomFields().size() > 0) {
				List customDeleteList = getUnselectedCaseTypeCustomFields(caseTypeDataBean
						.getUnSelectedCustomFields());
				
				Set customFieldAssignSet = caseTypeVOToDOConverter
						.getCustomAssignmentsDOs(customDeleteList, caseTypeDO);
				caseTypeCustomFieldDelList = caseTypeVOToDOConverter
						.convertCustAssignmentsSetToList(customFieldAssignSet);
				
			}
			
			delObj.put("CaseTypeStep", caseStepDelList);
			delObj.put("CaseTypeEvent", caseEventDelList);
			delObj.put("CaseTypeTemplate", caseTypeTemplateDelList);
			delObj.put("CaseTypeCustomFieldAssignment",
					caseTypeCustomFieldDelList);
			caseTypeDO.setDeletedObjects(delObj);

			
			ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
			
				boolean status = delegate.updateCaseType(caseTypeDO)
						.booleanValue();
				caseTypeDataBean.setUpdateFlag(status);
				if(logger.isDebugEnabled()){
				logger.debug("Update STATUS  :::" + status);
				}
				
				if (caseTypeDataBean.isUpdateFlag()) {
					int index = caseTypeDataBean.getEditCaseTypeRowIndex();
					if (StringUtils.isNotEmpty(newCaseTypeVO
							.getBusnUnitCaseTypeCode())) {
						
						
						
						// Added by ICS
						// Modify Defect ESPRD00723971 Starts
						/*String buDesc =  ContactManagementHelper.setShortDescription(FunctionalAreaConstants.GENERAL,
				                ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD,newCaseTypeVO.getBusnUnitCaseTypeCode());
					   
					    newCaseTypeVO.setBusnUnitTypeCodeDesc(buDesc);*/
					    
					    newCaseTypeVO.setBusnUnitTypeCodeDesc(getDescriptionFromVV(newCaseTypeVO.getBusnUnitCaseTypeCode(), 
								caseTypeDataBean.getBussinessUnits()));
								// Modify Defect ESPRD00723971 Ends
					    // End
					}

					caseTypeDataBean.getMaintainCaseTypeList().set(index,
							newCaseTypeVO);
					
					caseTypeDataBean.setShowEventActMsg(false);
					caseTypeDataBean.setShowStepActMsg(false);
					//Commented for the defect ESPRD00853112
					/*ContactManagementHelper
							.setErrorMessage(MaintainContactManagementUIConstants.CASETYPE_SAVE_SUCCESS);*/

					getCaseTypeDetails();
				}
				caseTypeDataBean.setShowSucessMessage(true);
		}else{
			//if successfully update a case type and user modified the same record which 
			//causes validation fail then only validation error msg should display
			//and success msg should be cleared.
			caseTypeDataBean.setShowSucessMessage(Boolean.FALSE);
		}
		} catch (CaseTypeUpdateBusinessException e1) {
			if(logger.isErrorEnabled()){
				logger.error(" In Update Case Type CaseTypeUpdateBusinessException ");
			}
				
			} catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error(" In Update Case Type Exception ");
				}
				
			}

			
		caseTypeDataBean.setWarnBeforeExitStatus("saveOrUpdate");
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method checks for ActualCm caseEvents
	 *  
	 */

	private boolean getActCMCaseEvents(CaseTypeEventVO caseTypeEventDelVO,
			Long caseTypeSk) {

		boolean flag = false;
		
		String eventTypeCode = caseTypeEventDelVO
				.getDfltCaseTypeEventTypeCode();
		
		List actCMCaseEventsList = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try {
			
			actCMCaseEventsList = contactMaintenanceDelegate
					.getActCMCaseEvents(eventTypeCode, caseTypeSk);
			
			if (actCMCaseEventsList!=null && !actCMCaseEventsList.isEmpty()) {
				
				flag = true;
			}

		} catch (CaseTypeFetchBusinessException e) {
			if(logger.isDebugEnabled()){
			logger.debug(e);
			}
		} 
		return flag;

	}

	/**
	 * 
	 * @param caseType
	 * @return
	 */
	private boolean getActCMCaseSteps(CaseTypeStepVO caseTypeStepDelVO,
			Long caseTypeSk) {
		if(logger.isDebugEnabled()){
		logger.debug("Inside getActCMCaseSteps");
		}
		boolean flag = false;
		
		String stepOrder = caseTypeStepDelVO.getStepOrderNum();
		Integer stepOrderNum = Integer.valueOf(stepOrder);
		
		String caseStepCode = caseTypeStepDelVO.getDescription();
	
		List actCMCaseStepssList = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try {
			
			actCMCaseStepssList = contactMaintenanceDelegate.getActCMCaseSteps(
					caseStepCode, caseTypeSk, stepOrderNum);
			if (actCMCaseStepssList!=null && !actCMCaseStepssList.isEmpty()) {
				
				flag = true;
			}

		} catch (CaseTypeFetchBusinessException e) {
			if(logger.isDebugEnabled()){
			logger.debug(e);
			}
		} 
		return flag;

	}

	/**
	 * Method to check if Filter is assigned to user.
	 * 
	 * @param filterName :
	 *            filterName to set.
	 * @return boolean.
	 */
	private Boolean isCaseTypeAssoiciatedCase(CaseType caseType) {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		Boolean isAssociated = Boolean.FALSE;
		try {
			isAssociated = contactMaintenanceDelegate
					.chkCaseTypeAssociatedToAnyCase(caseType);
		} catch (CaseTypeFetchBusinessException e) {
			if(logger.isErrorEnabled()){
			logger.error(e.getMessage(), e);
			}
		}
		return isAssociated;
	}

	/**
	 * This operation is used to delete CaseType Record.
	 * 
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String deleteCaseType() {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	    //CFR
	    caseTypeDataBean.setMaintainCaseTypeFocusId("deleteCaseTypId"); 
	    //EOF CFR
		String userID = caseTypeDataBean.getUserID();
		if(logger.isDebugEnabled()){
		logger.debug("Inside Delete casetype");
		}
		CaseTypeVO caseTypeVO = new CaseTypeVO();
		Boolean isDeleted = Boolean.FALSE;
		int index = caseTypeDataBean.getEditCaseTypeRowIndex();
		if (isNotNullAndNotEmptyList(caseTypeDataBean.getMaintainCaseTypeList())) {
			caseTypeVO = (CaseTypeVO) caseTypeDataBean
					.getMaintainCaseTypeList().get(index);

		}
		// Performance Delete Start
		List tempList = caseTypeDataBean.getMaintainCaseCustomFieldsList();
		List selectedCustomList = getSelectedCustomFields(tempList);
		caseTypeVO.setListOfCustomFields(selectedCustomList);
		caseTypeVO.setCustomFieldAssignmentVO(selectedCustomList);
		// Performance Delete End
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		CaseTypeVOToDOConverter caseTypeVOToDOConverter = new CaseTypeVOToDOConverter();
		CaseType caseType = caseTypeVOToDOConverter
				.convertCaseTypeVOToDO(caseTypeVO);
		Boolean isAssociated = isCaseTypeAssoiciatedCase(caseType);
		if (isAssociated.booleanValue()) {
			
			caseTypeDataBean.setShowDeleteFlag(true);
			caseTypeDataBean.setShowDeleteMessage(false);
			caseTypeDataBean.setShowSucessMessage(false);
			ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.DELETE_CASETYPE_RECORD_FAIL);
		} else {
			try {
					if(userID != null && !userID.equals(ContactManagementConstants.EMPTY_STRING)){ // Modified for the Defect ESPRD795461
							isDeleted = contactMaintenanceDelegate.deleteCaseType(caseType,
									userID);
							if (isDeleted.booleanValue()) {
								caseTypeDataBean.getMaintainCaseTypeList().remove(index);
								caseTypeDataBean.setShowDeleteMessage(true);
								caseTypeDataBean.setShowSucessMessage(false);
								caseTypeDataBean.setShowDeleteFlag(false);
								caseTypeDataBean.setShowEditCaseTypes(false);
								caseTypeDataBean.setShowAddCaseTypes(false);
			
							}
							caseTypeDataBean.setCaseTypeVO(new CaseTypeVO());
					}
			} catch (CaseTypeDeleteBusinessException e) {
				if(logger.isDebugEnabled()){
				logger.debug("inside catch block");
				}
				caseTypeDataBean.setShowDeleteMessage(false);
				caseTypeDataBean.setShowSucessMessage(false);
				caseTypeDataBean.setShowDeleteFlag(false);
			} 
		}

		return null;
	}

	/**
	 * This operation is used to get CaseType Record details
	 * 
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String getCaseTypeDetails() {
		if(logger.isInfoEnabled()){
		logger.info("Got call to getCaseTypeDetails()");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		//CFR
		caseTypeDataBean.setMaintainCaseTypeFocusId("addEditCaseTypes");		//EOF CFR
		caseTypeDataBean.setFirstFieldFocusID("casetypesDesc");//CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
		
		if(caseTypeDataBean.isRowDetailsFlag()){
		caseTypeDataBean.setShowAddCaseTypes(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEditCaseTypes(ContactManagementConstants.TRUE);
		caseTypeDataBean.setNoCaseTypesData(ContactManagementConstants.TRUE);
		caseTypeDataBean.setShowTypeDescFlag(ContactManagementConstants.FALSE);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession httpSession=(HttpSession)fc.getExternalContext().getSession(true);

		String indx = "";
		if(fc.getExternalContext().getRequestParameterMap().get(
				"rowIndex")!=null){
			indx = fc.getExternalContext().getRequestParameterMap().get(
			"rowIndex").toString();
		}
		String updateIndex=(String)httpSession.getAttribute("EditCaseTypeRowIndex");
		if(indx==null || indx.equals("") && updateIndex!=null){
			indx=updateIndex;
			httpSession.removeAttribute("EditCaseTypeRowIndex");
		}
		
		httpSession.setAttribute("EditCaseTypeRowIndex",indx);
		int index = Integer.parseInt(indx);
		
		caseTypeDataBean.setEditCaseTypeRowIndex(index);
		
		CaseTypeVO caseTypeOldVO = null;
		/** If List MaintainCaseTypeList in dataBean is not Null */
		if (isNotNullAndNotEmptyList(caseTypeDataBean.getMaintainCaseTypeList())) {
			caseTypeOldVO = (CaseTypeVO) caseTypeDataBean
					.getMaintainCaseTypeList().get(index);
		}
		// Begin - Commented for the defect id  ESPRD00723971_15NOV2011
		//modification for ESPRD00797636 starts.
		CaseTypeVO caseTypeNewVO = new CaseTypeVO();
		try {

			BeanUtils.copyProperties(caseTypeNewVO, caseTypeOldVO);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {

			logger.error(e.getMessage(), e);
		}
		// End - Commented for the defect id  ESPRD00723971_15NOV2011
		//modification for ESPRD00797636 ends.
		caseTypeDataBean.setCaseTypeVO(caseTypeNewVO); // Modified for the defect id ESPRD00723971_15NOV2011
		if (caseTypeOldVO.getCaseTypeStatusCode().equalsIgnoreCase(
				ContactManagementConstants.NO)) {
			caseTypeDataBean.setCaseTypeNoActive(true);
		} else {
			caseTypeDataBean.setCaseTypeNoActive(false);
		}
		
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try {
			
			CaseType casetypeDo = contactMaintenanceDelegate
					.getCaseTypeDetails(caseTypeOldVO.getCaseTypeSK()); // Modified for the defect id ESPRD00723971_15NOV2011

			CaseTypeDOConverter caseTypeDOConvertor = new CaseTypeDOConverter();
			loadTemplatesData(caseTypeDOConvertor, contactMaintenanceDelegate);
			loadCustomFieldsData(caseTypeDOConvertor, contactMaintenanceDelegate);
			/**
			 * Getting CaseSteps
			 */
			if (!casetypeDo.getCaseTypeSteps().isEmpty()) {
				List caseTypeStepsListVO = new ArrayList();
				Set caseTypeStepVOs = caseTypeDOConvertor
						.getCaseStepsVO(casetypeDo.getCaseTypeSteps());
				caseTypeDataBean.getCaseTypeVO().setCaseSteps(caseTypeStepVOs);
				
				String complBasedOnDesc=null;
				for (Iterator iter = caseTypeStepVOs.iterator(); iter.hasNext();) {
					CaseTypeStepVO caseTypeStepVO = (CaseTypeStepVO) iter
							.next();
					//ESPRD00527652_UC-PGM-CRM-48_28SEP2010
					if(caseTypeStepVO.getAutomaticRouteTo()!=null){
						caseTypeStepVO.setAutomaticRouteToDesc(getDescriptionFromVV(caseTypeStepVO.getAutomaticRouteTo(),caseTypeDataBean.getCaseStepAutRoutoToList()));
					}
					//EOF ESPRD00527652_UC-PGM-CRM-48_28SEP2010
					if(caseTypeStepVO.getDfltCmpltnBasedOnColName()!=null && caseTypeStepVO.getDfltCmpltnBasedOnColName().length()>0){
						complBasedOnDesc = getDescriptionFromVV(
								caseTypeStepVO.getDfltCmpltnBasedOnColName(),caseTypeDataBean.getCompletionBasedOnListAll());
						if(complBasedOnDesc!=null && complBasedOnDesc.length()>0){
							caseTypeStepVO.setDfltCmpltnBasedOnDesc(complBasedOnDesc);	
						}else{
							caseTypeStepVO.setDfltCmpltnBasedOnDesc(caseTypeStepVO.getDfltCmpltnBasedOnColName());
						}
						
					}
					//heap dump issue fix
					//CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
					/*try{
						//doAuditKeyListOperationForCaseTypeStepVO(caseTypeStepVO);
					}catch(Exception e){
						
					}//EOf CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
*/					//heap dump issue fix
					if (StringUtils.isNotEmpty(caseTypeStepVO.getDescription())) {
					// Modify Defect ESPRD00723971 Starts
						/*String stepDesc = ContactManagementHelper
								.setShortDescription(
										FunctionalAreaConstants.GENERAL,
										ReferenceServiceDataConstants.G_CM_CASE_STEP_CD,
										caseTypeStepVO.getDescription());*/
						caseTypeStepVO.setDescValue(getDescriptionFromVV(caseTypeStepVO.getDescription(),
													caseTypeDataBean.getDescnCaseStepList()));
													// Modify Defect ESPRD00723971 Ends
					}
					if (StringUtils
							.isNotEmpty(caseTypeStepVO.getStepOrderNum())) {
							// Modify Defect ESPRD00723971 Starts
						/*String orderDesc = ContactManagementHelper
								.setShortDescription(
										FunctionalAreaConstants.GENERAL2,
										ReferenceServiceDataConstants.G_STEP_ORDER_NUM,
										caseTypeStepVO.getStepOrderNum());*/
						caseTypeStepVO.setStepOrderNumDesc(getDescriptionFromVV(caseTypeStepVO.getStepOrderNum(),
								caseTypeDataBean.getOrderCaseStepList()));
								// Modify Defect ESPRD00723971 Ends
					}
					//ESPRD00527655_UC-PGM-CRM-48_29SEP2010
					if(caseTypeStepVO.getDfltAlertBasedOnColName()!=null && caseTypeStepVO.getDfltAlertBasedOnColName().length()>0){
						caseTypeStepVO.setDfltAlertBasedOnDesc(getDescriptionFromVV(
										caseTypeStepVO.getDfltAlertBasedOnColName(),caseTypeDataBean.getDfltAlertBasedOnListAll()));
						
					}else{
						caseTypeStepVO.setDfltAlertBasedOnDesc(ContactManagementConstants.EMPTYSTRING);
					}
					//EOf ESPRD00527655_UC-PGM-CRM-48_29SEP2010
					caseTypeStepsListVO.add(caseTypeStepVO);
				}
				caseTypeDataBean.setCaseTypeStepsList(caseTypeStepsListVO);
			}else{
				caseTypeDataBean.setCaseTypeStepsList(new ArrayList());
				caseTypeDataBean.setNoCaseStepsData(false);
			}
			if (!casetypeDo.getCaseTypeEvents().isEmpty()) {
				/**
				 * Getting CaseEvents
				 */
				List caseTypeEventsListVO = new ArrayList();
				Set caseTypeEventsListVo = caseTypeDOConvertor
						.getCaseEventsVO(casetypeDo.getCaseTypeEvents());
				
//				ESPRD00527888_UC-PGM-CRM-48_29SEP2010
				try{
				CaseTypeEventVO tempEventVo=null;
				String desc=null;
				String alerBasedOnDesc = null;
				caseTypeDataBean.getCaseTypeVO().setCaseEvents(caseTypeEventsListVo);
				
				for(Iterator voItr = caseTypeEventsListVo.iterator();voItr.hasNext();){
					tempEventVo = (CaseTypeEventVO)voItr.next();
					desc = getDescriptionFromVV(tempEventVo.getDfltCaseTypeEventTypeCode(),caseTypeDataBean.getCaseEventTypeListAll());
					if(desc!=null && desc.length()>0){
						tempEventVo.setDfltCaseTypeEventTypeCodeDesc(desc);
					}else{
						tempEventVo.setDfltCaseTypeEventTypeCodeDesc(tempEventVo.getDfltCaseTypeEventTypeCode());
					}
			          if(tempEventVo.getDfltAlertBasedOnColName()!=null && tempEventVo.getDfltAlertBasedOnColName().length()>0){
						alerBasedOnDesc = getDescriptionFromVV(tempEventVo.getDfltAlertBasedOnColName(),caseTypeDataBean.getEventDfltAlertBasedOnListAll());
			          	tempEventVo.setDfltAlertBasedOnDesc(alerBasedOnDesc);
					}
			         
			          caseTypeEventsListVO.add(tempEventVo);
				}
				}catch(Exception exception){
					if(logger.isErrorEnabled()){
					logger.error(exception.getMessage(), exception);
					}
				}
				//EOf ESPRD00527888_UC-PGM-CRM-48_29SEP2010
				caseTypeDataBean.setCaseTypeEventsList(caseTypeEventsListVO);
			}else{
				caseTypeDataBean.setCaseTypeEventsList(new ArrayList());
				caseTypeDataBean.setNoCaseEventsData(false);
			}

			/**
			 * Getting Assigned templates
			 */
			List catListforEditTemplate = new ArrayList();
			//Added for the defect ESPRD00853112
			caseTypeDataBean.getTemplateDropDownList().clear();
			caseTypeDataBean.getTemplateDropDownList().add(new SelectItem(MaintainContactManagementUIConstants.EMPTY_STRING,MaintainContactManagementUIConstants.EMPTY_STRING));
			List caseTypeTemplateVO = caseTypeDOConvertor
					.getCaseTypeTemplates(casetypeDo.getCaseTypeTemplates());
			
			
			int templateSize = caseTypeTemplateVO.size();
			caseTypeDataBean.getCaseTypeVO().setCaseTypeTemplateVO(caseTypeTemplateVO);
			if (!caseTypeTemplateVO.isEmpty() && templateSize > 0) {
				Iterator iter = caseTypeTemplateVO.iterator();
				while (iter.hasNext()) {
					CaseTypeLetterTemplateVO caseTypeTemplate = (CaseTypeLetterTemplateVO) iter
							.next();
					if (caseTypeTemplate.getLetterTemplateKeyData() == null) {
						caseTypeDataBean
								.getTemplateDropDownList()
								.add(
										new SelectItem(
												MaintainContactManagementUIConstants.EMPTY_STRING,
												MaintainContactManagementUIConstants.EMPTY_STRING));
					}

					else {
						caseTypeDataBean.getTemplateDropDownList().add(
								new SelectItem(caseTypeTemplate
										.getLetterTemplateKeyData(),
										caseTypeTemplate.getLetterTemplate()
												.getName()));
						
					}
				}
			}

	
			if (caseTypeDataBean.getMaintainCaseTemplatesList() != null
					&& caseTypeDataBean.getMaintainCaseTemplatesList().size() > 0) {
				

				catListforEditTemplate = showAssignedTemplates(caseTypeDataBean
						.getMaintainCaseTemplatesList(), caseTypeTemplateVO);
			}
			caseTypeDataBean
					.setMaintainCaseTemplatesList(catListforEditTemplate);

			/**
			 * Getting Assigned Customfields
			 */

			List catListforEditCustomField = new ArrayList();
			
			List caseTypeCustomFieldAssignVO = caseTypeDOConvertor
					.getCustomFieldAssignmentVO(casetypeDo
							.getCustomFieldAssignments());
			caseTypeDataBean.getCaseTypeVO().setCustomFieldAssignmentVO(caseTypeCustomFieldAssignVO);	
			if (caseTypeDataBean.getMaintainCaseCustomFieldsList() != null
					&& caseTypeDataBean.getMaintainCaseCustomFieldsList()
							.size() > 0) {

				
				catListforEditCustomField = showAssignedCustomFields(
						caseTypeDataBean.getMaintainCaseCustomFieldsList(),
						caseTypeCustomFieldAssignVO);
			}
			caseTypeDataBean
					.setMaintainCaseCustomFieldsList(catListforEditCustomField);

			caseTypeDataBean.setShowDeleteMessage(false);
			caseTypeDataBean.setShowDeleteFlag(false);
			caseTypeDataBean.setShowSucessMessage(false);
			caseTypeDataBean.setShowAddCaseEvents(false);
			caseTypeDataBean.setShowAddCaseSteps(false);
			caseTypeDataBean.setShowEventsSuccessMsg(false);
			//Added for the defect ESPRD00853112
			caseTypeDataBean.setShowStepSuccessMsg(false);
			caseTypeDataBean.setShowStepDeleteMsg(false);
			caseTypeDataBean.setShowEventsDeleteMsg(false);
			caseTypeDataBean.setShowEditCaseSteps(false);
			caseTypeDataBean.setShowEditCaseEvents(false);
			if (caseTypeDataBean.getCaseTypeStepsList().size() > 0) {
				caseTypeDataBean.setNoCaseStepsData(true);
			}
			if (caseTypeDataBean.getCaseTypeEventsList().size() > 0) {
				caseTypeDataBean.setNoCaseEventsData(true);
			}

		} catch (Exception e) {
			if(logger.isDebugEnabled()){
			logger.debug("Exception:" + e);
			}
		}
		caseTypeDataBean.setRowDetailsFlag(false);
		}
			return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method Used to return Assigned templates with include checkbox
	 * selected
	 * 
	 * @param allTempaltes
	 *            represents List
	 * @param assignedTemplates
	 *            represents List
	 * @return allTempaltes represents List
	 */
	public List showAssignedTemplates(List allTempaltes, List assignedTemplates) {
		if(logger.isDebugEnabled()){
		logger.debug("Inside showAssignedTempaltes ");
		}
		int allTemplatesSize = 0;
		int assignedTempaltesSize = 0;

		if (allTempaltes != null && assignedTemplates != null) {
			allTemplatesSize = allTempaltes.size();
			assignedTempaltesSize = assignedTemplates.size();

		}

		for (int i = 0; i < allTemplatesSize; i++) {
			CaseTypeLetterTemplateVO allTemplatesVo = (CaseTypeLetterTemplateVO) allTempaltes
					.get(i);
			allTemplatesVo.setIncludeTemplate(false);
			for (int j = 0; j < assignedTempaltesSize; j++) {
				CaseTypeLetterTemplateVO assignedTemplatesVO = (CaseTypeLetterTemplateVO) assignedTemplates
						.get(j);
				String keydata = allTemplatesVo.getLetterTemplateKeyData();
				
				if (keydata.equalsIgnoreCase(assignedTemplatesVO
						.getLetterTemplateKeyData()))

				{
					allTemplatesVo.setIncludeTemplate(true);
					allTemplatesVo.setCaseTypeSK(assignedTemplatesVO
							.getCaseTypeSK());
					allTemplatesVo.setVersionNo(assignedTemplatesVO
							.getVersionNo());
					
					//heap dump issue fix
					//if (allTemplatesVo.getCaseTypeSK() != null) {
						
					//}
					//heap dump issue fix
					
					break;

				}
			}

		}
		return allTempaltes;

	}

	/**
	 * This method Used to return Assigned templates with include checkbox
	 * selected
	 * 
	 * @param allTempaltes
	 *            represents List
	 * @param assignedTemplates
	 *            represents List
	 * @return allTempaltes represents List
	 */
	public List showAssignedCustomFields(List allCustomFields,
			List assignedCustomFields) {
		if(logger.isDebugEnabled()){
		logger.debug("Inside showAssignedTempaltes ");
		}
		int allCustomFieldsSize = 0;
		int assignedCustomFieldsSize = 0;

		if (allCustomFields != null && assignedCustomFields != null) {
			allCustomFieldsSize = allCustomFields.size();
			assignedCustomFieldsSize = assignedCustomFields.size();

		}

		for (int i = 0; i < allCustomFieldsSize; i++) {
			CustomFieldVO allCustomFieldsVO = (CustomFieldVO) allCustomFields
					.get(i);
			allCustomFieldsVO.setCustomFieldSelected(false);
			for (int j = 0; j < assignedCustomFieldsSize; j++) {
				CustomFieldAssignmentVO assignedCustomFieldsVO = (CustomFieldAssignmentVO) assignedCustomFields
						.get(j);
				String customFieldSK = allCustomFieldsVO.getCustomFieldSK()
						.toString();
				if (customFieldSK.equalsIgnoreCase(assignedCustomFieldsVO
						.getCustomFieldSK().toString()))

				{
					allCustomFieldsVO.setCustomFieldSelected(true);
					allCustomFieldsVO.setVersionNo(assignedCustomFieldsVO
							.getVersionNo());
					if (assignedCustomFieldsVO.getCaseType() != null) {
						allCustomFieldsVO.setCaseType(assignedCustomFieldsVO
								.getCaseType());
					}
					
					break;

				}
			}

		}
		return allCustomFields;

	}

	/**
	 * This operation is used for getting CaseStep details
	 * 
	 * @return String
	 */
	public String getCaseStepDetails() {
		if(logger.isDebugEnabled()){
		logger.debug(" *********** getCaseStepDetails ********");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean.setMaintainCaseTypeFocusId("maintainCaseTypeEditCasesteps");//Added for Page Focus Fix
		if(caseTypeDataBean.isRowDetailsFlag()){
		caseTypeDataBean.setShowAddCaseSteps(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEditCaseSteps(ContactManagementConstants.TRUE);
		caseTypeDataBean.setNoCaseStepsData(ContactManagementConstants.TRUE);
		caseTypeDataBean.setShowDescOrderFlag(ContactManagementConstants.FALSE);
		caseTypeDataBean.setIncudeflag(ContactManagementConstants.FALSE);
		caseTypeDataBean
				.setShowStepSuccessMsg(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowStepDeleteMsg(ContactManagementConstants.FALSE);
		

		
		FacesContext fc = FacesContext.getCurrentInstance();
		String indx = fc.getExternalContext().getRequestParameterMap().get(
				"rowIndex").toString();
		int index = new Integer(indx).intValue();
		caseTypeDataBean.setEditCaseStepRowIndex(index);
		
		//modification for ESPRD00797636 starts.
		CaseTypeStepVO caseTypeStepOldVO = null;
		CaseTypeStepVO caseTypeStepNewVO = new CaseTypeStepVO(); // Commented for the defect id ESPRD00723971_15NOV2011
		if (isNotNullAndNotEmptyList(caseTypeDataBean.getCaseTypeStepsList())) {
			
			caseTypeStepOldVO = (CaseTypeStepVO) caseTypeDataBean
					.getCaseTypeStepsList().get(index);

		}
		// Begin - Commented for the defect id  ESPRD00723971_15NOV2011
		try {

			BeanUtils.copyProperties(caseTypeStepNewVO, caseTypeStepOldVO);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException  getCaseStepDetailsMethod ");
		} catch (InvocationTargetException e) {
			logger
					.error("InvocationTargetException at getCaseStepDetailsMethod");

		}
		// End - Commented for the defect id  ESPRD00723971_15NOV2011
		caseTypeDataBean.setFirstFieldFocusID("descid");//CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
		caseTypeDataBean.setCaseTypeStepVO(caseTypeStepNewVO); // Modified for the defect id ESPRD00723971_15NOV2011
		//modification for ESPRD00797636 ends.
		caseTypeDataBean.setShowSucessMessage(false);
		caseTypeDataBean.setRowDetailsFlag(false);
		}
		return "EditCaseStep";
	}

	

	/**
	 * This operation is used for loading templates data
	 * 
	 * @param caseTypeDOConvertor :
	 *            CaseTypeDOConverter
	 * @param contactMaintenanceDelegate :
	 *            ContactMaintenanceDelegate
	 */
	private void loadTemplatesData(CaseTypeDOConverter caseTypeDOConvertor,
			ContactMaintenanceDelegate contactMaintenanceDelegate) {
		if(logger.isDebugEnabled()){
		logger.debug(" loadTemplatesData from Controllerbean");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		try {
			
			List templatesDOList = contactMaintenanceDelegate.getAllActiveLetterTemplates();// contactMaintenanceDelegate.getAllTemplates();//Modified for defect ESPRD00534028
			
           /* FIND BUGS_FIX*/
			List templatesVOList = new ArrayList();
			if (templatesDOList != null && !templatesDOList.isEmpty()) {
				for (Iterator iter = templatesDOList.iterator(); iter.hasNext();) {
					LetterTemplate template = (LetterTemplate) iter.next();
					CaseTypeLetterTemplateVO letterVO = caseTypeDOConvertor
							.convertLetterTemplateDOtoVO(template);
					templatesVOList.add(letterVO);
				}
				caseTypeDataBean.setMaintainCaseTemplatesList(templatesVOList);
				caseTypeDataBean.setNoTemplatesData(true);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
			logger.debug("Exception :" + e);
			}
		} 
	}

	/**
	 * This operation is used for loading custom fields data
	 * 
	 * @param caseTypeDOConvertor :
	 *            CaseTypeDOConverter
	 * @param contactMaintenanceDelegate :
	 *            ContactMaintenanceDelegate
	 */
	private void loadCustomFieldsData(CaseTypeDOConverter caseTypeDOConvertor,
			ContactMaintenanceDelegate contactMaintenanceDelegate) {
		if(logger.isDebugEnabled()){
		logger.debug("CaseTypeControllerBean >>loadCustomFieldsData()");
		}
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		List customFieldsDOList = null;
		List customFieldsVOList = new ArrayList();
		try {
			
		  
			customFieldsDOList = contactMaintenanceDelegate
					.getAllCustomFields(MaintainContactManagementUIConstants.CASE_TABLE_ID);
			if(customFieldsDOList != null){
				if(logger.isDebugEnabled()){
			logger.debug("customFieldsDOList size:" + customFieldsDOList);
				}
			}
		} catch (CustomFieldFetchBusinessException e1) {
			if(logger.isErrorEnabled()){
			logger
					.error("CustomFieldFetchBusinessException at loadCustomFieldsData ");
			}
		} 
		if(customFieldsDOList != null){
			if(logger.isDebugEnabled()){
		logger.debug("Custom Fields List Size :: " + customFieldsDOList.size());
			}
		}
		if (isNotNullAndNotEmptyList(customFieldsDOList)) {
			for (Iterator iter = customFieldsDOList.iterator(); iter.hasNext();) {
				final CustomField cfDO = (CustomField) iter.next();
				final CustomFieldVO cfVO = caseTypeDOConvertor
						.convertCustomFieldDOToVO(cfDO);
				customFieldsVOList.add(cfVO);
			}
			caseTypeDataBean
					.setNocustomfieldsData(ContactManagementConstants.TRUE);
			caseTypeDataBean
					.setMaintainCaseCustomFieldsList(customFieldsVOList);
		} else {
			caseTypeDataBean
					.setNocustomfieldsData(ContactManagementConstants.FALSE);
			caseTypeDataBean.setMaintainCaseCustomFieldsList(new ArrayList());
		}

	}
	
	/**
	 * This method Resets the Add Case Type
	 * 
	 * @return String
	 */
	
	public String resetAddCaseTypes() {
		if(logger.isDebugEnabled()){
		logger.debug("Inside resetAddCaseTypes");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean.setCaseTypeVO(new CaseTypeVO());
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method Resets the Case Type in Edit Call Script Block .
	 * 
	 * @return String
	 */
	 public String resetEditCaseTypes() {
		 if(logger.isInfoEnabled()){
		 logger.info("inside resetEditcasetypes");
		 }
		 caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	     CaseTypeVO caseTypeVO = null;
		 CaseTypeVO tempCaseTypeVO = new CaseTypeVO();
		 int caseTypeIndex = caseTypeDataBean.getEditCaseTypeRowIndex();
		 
		 caseTypeVO = (CaseTypeVO) caseTypeDataBean
					.getMaintainCaseTypeList().get(caseTypeIndex);
		 
		try {
			if(logger.isInfoEnabled()){
			logger.info("Inside try of resetEdit");
			}
			BeanUtils.copyProperties(tempCaseTypeVO, caseTypeVO);
		}
		catch (IllegalAccessException e) {
			if(logger.isErrorEnabled()){
			logger.error(e.getMessage(), e);
			}
		}
		catch (InvocationTargetException e) {
			if(logger.isErrorEnabled()){
			logger.error(e.getMessage(), e);
			}
		}
		caseTypeDataBean.setCaseTypeVO(tempCaseTypeVO);
		return ContactManagementConstants.RENDER_SUCCESS;
	}
	

	/**
	 * This Method cancels Add Case Type Block .
	 */
	public void cancelAddCaseTypes() {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean.setShowAddCaseTypes(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEditCaseTypes(ContactManagementConstants.FALSE);

	}

	/**
	 * This Method cancels Edit Case Type Block .
	 */
	public void cancelEditCaseTypes() {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);

		caseTypeDataBean.setShowEditCaseTypes(ContactManagementConstants.FALSE);

		//CFR
		caseTypeDataBean.setMaintainCaseTypeFocusId("cancelCaseTypId"); //EOF CFR
	}

	/** **********************CASE STEPS************************************ */
	/**
	 * This operation is used to create new CaseStep Record.
	 * 
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String addCaseStep() {
		if(logger.isDebugEnabled()){
		logger.debug("Got call to add case step method ");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		CaseTypeStepVO oldCaseStepVO = caseTypeDataBean.getCaseTypeStepVO();
		//CFR
		//caseTypeDataBean.setMaintainCaseTypeFocusId("addEditCaseTypesSteps");//commented for Page Focus Fix
		//EOF CFR
		caseTypeDataBean.setShowStepSuccessMsg(false);
		caseTypeDataBean.setShowStepDeleteMsg(false);
		if(oldCaseStepVO.getCaseTypeStepInclude().equals("No")){
			return "";
		}
		if (validateCaseStepVO(oldCaseStepVO,
				MaintainContactManagementUIConstants.ADD)) {
			setCaseStepDfltSendAlertDaysBeforeOrAfterColData(oldCaseStepVO);
			CaseTypeStepVO newCaseStepVO = new CaseTypeStepVO();
			try {
				BeanUtils.copyProperties(newCaseStepVO, oldCaseStepVO);
			} catch (IllegalAccessException e) {
				if(logger.isErrorEnabled()){
				logger.error("IllegalAccessException at addCaseStep");
				}

			} catch (InvocationTargetException e) {
				if(logger.isErrorEnabled()){
				logger.error("InvocationTargetException at addCaseStep");
				}

			}
			// Modify Defect ESPRD00723971 Starts
			/*String stepDesc = ContactManagementHelper.setShortDescription(
					FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_CM_CASE_STEP_CD,
					newCaseStepVO.getDescription());
			newCaseStepVO.setDescValue(stepDesc);*/
			newCaseStepVO.setDescValue(getDescriptionFromVV(newCaseStepVO.getDescription(),
					caseTypeDataBean.getDescnCaseStepList()));
			
			
			
			// Added by ICS
			/*String orderDesc = ContactManagementHelper.setShortDescription(
					FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_STEP_ORDER_NUM,
					newCaseStepVO.getStepOrderNum());
			newCaseStepVO.setStepOrderNumDesc(orderDesc);*/
			newCaseStepVO.setStepOrderNumDesc(getDescriptionFromVV(newCaseStepVO.getStepOrderNum(),
					caseTypeDataBean.getOrderCaseStepList()));
					// Modify Defect ESPRD00723971 Ends
			// End
			
			//Added by ICS for Gap 15668
			Map users = caseTypeDataBean.getUserMap();
			String userName = (String) users.get(newCaseStepVO
					.getDfltNotfyAlertUserId());
			newCaseStepVO.setDfltNotfyAlertUserName(userName);
			//Ends
			//ESPRD00527652_UC-PGM-CRM-48_28SEP2010
			if(newCaseStepVO.getAutomaticRouteTo()!=null){
				//Modify DEFECT_ESPRD00723971 Starts
			//	newCaseStepVO.setAutomaticRouteToDesc((String)caseTypeDataBean.getUsersWithWorkUnits().get(newCaseStepVO.getAutomaticRouteTo()));	
			
				newCaseStepVO.setAutomaticRouteToDesc(getDescriptionFromVV(newCaseStepVO.getAutomaticRouteTo(), 
						caseTypeDataBean.getCaseStepAutRoutoToList()));
						//Modify DEFECT_ESPRD00723971 Ends
			}
			
//			ESPRD00527655_UC-PGM-CRM-48_29SEP2010
			String alertBasedOnDesc = null;
			if(newCaseStepVO.getDfltAlertBasedOnColName()!=null
					&& newCaseStepVO.getDfltAlertBasedOnColName().length()>0){
				alertBasedOnDesc = getDescriptionFromVV(
						newCaseStepVO.getDfltAlertBasedOnColName(),caseTypeDataBean.getDfltAlertBasedOnList());
				if(alertBasedOnDesc!=null && alertBasedOnDesc.length()>0){
					newCaseStepVO.setDfltAlertBasedOnDesc(alertBasedOnDesc);
				}else{
					newCaseStepVO.setDfltAlertBasedOnDesc(newCaseStepVO.getDfltAlertBasedOnColName());
				}
				
			}else{
				newCaseStepVO.setDfltAlertBasedOnDesc(ContactManagementConstants.EMPTYSTRING);
			}
			String complBasedOnDesc =null;
			if(newCaseStepVO.getDfltCmpltnBasedOnColName()!=null && newCaseStepVO.getDfltCmpltnBasedOnColName().length()>0){
				complBasedOnDesc = getDescriptionFromVV(
						newCaseStepVO.getDfltCmpltnBasedOnColName(),caseTypeDataBean.getCompletionBasedOnList());
				if(complBasedOnDesc!=null && complBasedOnDesc.length()>0){
					newCaseStepVO.setDfltCmpltnBasedOnDesc(complBasedOnDesc);	
				}else{
					newCaseStepVO.setDfltCmpltnBasedOnDesc(newCaseStepVO.getDfltCmpltnBasedOnColName());
				}
				
			}else{
				newCaseStepVO.setDfltCmpltnBasedOnDesc(ContactManagementConstants.EMPTYSTRING);
			}
			//EOf ESPRD00527655_UC-PGM-CRM-48_29SEP2010
			
			//EOF ESPRD00527652_UC-PGM-CRM-48_28SEP2010
			caseTypeDataBean.getCaseTypeStepsList().add(newCaseStepVO);

			caseTypeDataBean.setCaseTypeStepVO(new CaseTypeStepVO());
			caseTypeDataBean.setShowStepSuccessMsg(true);
			caseTypeDataBean.setShowStepDeleteMsg(false);
			//Added for the defect ESPRD00853112
			caseTypeDataBean.setShowAddCaseSteps(false);
			/**
			 * Enable casestep datatable
			 */
			caseTypeDataBean
					.setNoCaseStepsData(ContactManagementConstants.TRUE);
			caseTypeDataBean
					.setNoCaseTypesData(ContactManagementConstants.TRUE);

		}
		return "addCaseType";
	}

	/**
	 * This operation is used to create new CaseEvent Record.
	 * 
	 * @param null
	 * @return String.
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String addCaseEvent() {
		if(logger.isDebugEnabled()){
		logger.debug("Inside add Case event");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		CaseTypeEventVO oldcaseEventVO = caseTypeDataBean.getCaseTypeEventVO();
		//CFR
		//caseTypeDataBean.setMaintainCaseTypeFocusId("addEditCaseTypesEvents");//commented for Page Focus Fix
		//EOF CFR
		caseTypeDataBean.setShowEventsSuccessMsg(false);
		caseTypeDataBean.setShowEventsDeleteMsg(false);
		if(oldcaseEventVO.getCaseTypeEventInclude().equals("No")){
			return "";
		}
		if (validateCaseEvent(oldcaseEventVO,
				MaintainContactManagementUIConstants.ADD)) {
			setEventDfltSendAlertDaysBeforeOrAfterColData(oldcaseEventVO);
			CaseTypeEventVO newcaseEventVO = new CaseTypeEventVO();

			try {
				BeanUtils.copyProperties(newcaseEventVO, oldcaseEventVO);
			} catch (IllegalAccessException e) {
				if(logger.isDebugEnabled()){
				logger.debug(e);
				}
			} catch (InvocationTargetException e) {
				if(logger.isDebugEnabled()){
				logger.debug(e);
				}
			}
			//Added by ICS for Gap 15668
			Map users = caseTypeDataBean.getUserMap();
			String userName = (String) users.get(newcaseEventVO
					.getDfltNotfyAlertUserId());
			newcaseEventVO.setDfltNotfyAlertUserName(userName);
			//Ends
			try{
			newcaseEventVO.setDfltCaseTypeEventTypeCodeDesc(getDescriptionFromVV(
							newcaseEventVO.getDfltCaseTypeEventTypeCode(),caseTypeDataBean.getCaseEventTypeList()));
			newcaseEventVO.setDfltAlertBasedOnDesc(getDescriptionFromVV(
					newcaseEventVO.getDfltAlertBasedOnColName(),caseTypeDataBean.getEventDfltAlertBasedOnList()));
			}catch(Exception exception){
				if(logger.isErrorEnabled()){
				logger.error(exception.getMessage(), exception);
				}
			}
			caseTypeDataBean.getCaseTypeEventsList().add(newcaseEventVO);
			/**
			 * Set default VO after add
			 */
			caseTypeDataBean.setShowEventsSuccessMsg(true);
			caseTypeDataBean.setShowEventsDeleteMsg(false);
			//Added for the defect ESPRD00853112
			caseTypeDataBean.setShowAddCaseEvents(false);
			caseTypeDataBean.setCaseTypeEventVO(new CaseTypeEventVO());
			/**
			 * Enable caseEvents data table
			 */
			caseTypeDataBean
					.setNoCaseEventsData(ContactManagementConstants.TRUE);
			caseTypeDataBean
					.setNoCaseTypesData(ContactManagementConstants.TRUE);
		}
		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This method is used for validating CaseEventVO
	 * 
	 * @param caseEventVO
	 *            represents CaseTypeEventVO
	 * @param actionCode
	 *            represents tring
	 * @return flag represents boolean
	 */
	private boolean validateCaseEvent(CaseTypeEventVO caseEventVO,
			String actionCode) {
		boolean flag = true;
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		if(logger.isDebugEnabled()){
		logger.debug("Inside ValidateCaseEvent");
		}
		if (caseEventVO.getDfltCaseTypeEventTypeCode() == null
				|| caseEventVO.getDfltCaseTypeEventTypeCode().equalsIgnoreCase(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {
			flag = false;
			caseTypeDataBean.setShowTypeCodeFlag(false);
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.EVENT_TYPE_ORDER_REQ,
					MaintainContactManagementUIConstants.EVENT_TYPE_CODE, MaintainContactManagementUIConstants.EVENT_TYPE_CODE,
					actionCode);
		} else {
			if (actionCode
					.equalsIgnoreCase(MaintainContactManagementUIConstants.ADD)) {
				boolean eventFlag = false;
				eventFlag = validateEventTypeCode(caseEventVO, actionCode);
				if (!eventFlag) {
					flag = false;
				}
			}
		}
		
		if(caseEventVO.getDfltNotfyAlertUserId() != null && 
				!caseEventVO.getDfltNotfyAlertUserId().trim().equals(""))
		{
			
			if(caseEventVO.getDfltSendAlertDaysCode() == null 
					|| caseEventVO.getDfltSendAlertDaysCode().trim().equals(ProgramConstants.EMPTY_STRING))
			{
				flag = false;
				ContactManagementHelper
				.setErrorMessage(
						MaintainContactManagementUIConstants.EVENT_DEFAULT_SEND_ALERT_OF_DAY_REQ,
						"cesendalertlabel", null, null);
			}
				
				
			if(caseEventVO.getDfltAlertBasedOnColName()==null 
					|| caseEventVO.getDfltAlertBasedOnColName().trim().equals(ProgramConstants.EMPTY_STRING))
			{
				
				flag = false;
				ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.EVENT_DEFAULT_ALERT_BASED_ON_REQ,
							"cealertbasedmenu2", null, null);
			}
			
			if (caseEventVO.getDfltSendAlertDaysCode() != null
					&& !caseEventVO.getDfltSendAlertDaysCode().trim().equals(
							ProgramConstants.EMPTY_STRING)) {

				if (caseEventVO.getDfltBeforeAfterCode() == null || caseEventVO.getDfltBeforeAfterCode().trim().equals(ProgramConstants.EMPTY_STRING)) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.BEFORE_AFTER_REQ,
									"cesendalertlabel",null, null);
				}

			} else {
				caseEventVO.setDfltBeforeAfterCode(null);
			}
		}
		//Added for the defect ESPRD00853112
		if(!flag){
		caseTypeDataBean.setMaintainCaseTypeFocusId("maintainCaseTypeCaseEvents");
		}
		//for defect ESPRD00805369
		validateFlag=flag;
		return flag;
	}

	/**
	 * This method used for validating EventTypeCode
	 * 
	 * @param stepvo
	 * @param actionCode
	 * @return
	 */
	private boolean validateEventTypeCode(CaseTypeEventVO newCaseEventVO,
			String actionCode) {
		boolean flag = true;
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		List caseEventOldList = caseTypeDataBean.getCaseTypeEventsList();
		for (Iterator iter = caseEventOldList.iterator(); iter.hasNext();) {
			CaseTypeEventVO caseEventVO = (CaseTypeEventVO) iter.next();
			if (caseEventVO.getDfltCaseTypeEventTypeCode().equalsIgnoreCase(
					newCaseEventVO.getDfltCaseTypeEventTypeCode())) {
				flag = false;
				caseTypeDataBean.setShowTypeCodeFlag(true);
				
				ContactManagementHelper
						.setErrorMessage(MaintainContactManagementUIConstants.EVENT_TYPE_CODE_CANT_SAME);
				break;
			}

		}
		return flag;
	}

	/**
	 * This method is used for validating CaseStepVO
	 * 
	 * @param stepvo
	 *            represents CaseTypeStepVO
	 * @param actionCode
	 *            represents String
	 * @return flag represents boolean
	 */
	public boolean validateCaseStepVO(CaseTypeStepVO stepvo, String actionCode) {
		boolean flag = true;
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		if (stepvo.getStepOrderNum() == null
				|| stepvo.getStepOrderNum().equalsIgnoreCase(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {
			
			flag = false;
			caseTypeDataBean.setShowDescOrderFlag(false);
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.STEP_ORDER_REQ,
					MaintainContactManagementUIConstants.STEP_ORDER_ID, MaintainContactManagementUIConstants.STEP_ORDER_ID,
					actionCode);
		}

		if (stepvo.getDescription() == null
				|| stepvo.getDescription().equalsIgnoreCase(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {
			
			flag = false;
			caseTypeDataBean.setShowDescOrderFlag(false);
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.STEP_DESC_ORDER_REQ,
					MaintainContactManagementUIConstants.STEP_DESC_ID, MaintainContactManagementUIConstants.STEP_DESC_ID,
					actionCode);
		} else {
			if (actionCode
					.equalsIgnoreCase(MaintainContactManagementUIConstants.ADD)) {
				boolean stepFlag = true;
				stepFlag = validateStepOrderDesc(stepvo, actionCode);
				if (!stepFlag) {
					flag = false;
				}
			}
		}

		if(stepvo.getDfltNotfyAlertUserId() != null && 
				!stepvo.getDfltNotfyAlertUserId().trim().equals(""))
		{
			
			if(stepvo.getDfltSendAlertDaysCode() == null 
					|| stepvo.getDfltSendAlertDaysCode().trim().equals(ProgramConstants.EMPTY_STRING))
			{
				flag = false;
				ContactManagementHelper
				.setErrorMessage(
						MaintainContactManagementUIConstants.STEP_DEFAULT_SEND_ALERT_OF_DAY_REQ,
						"cesendalertmen3", null, null);
			}
				
				
			if(stepvo.getDfltAlertBasedOnColName()==null 
					|| stepvo.getDfltAlertBasedOnColName().trim().equals(ProgramConstants.EMPTY_STRING))
			{
				
				flag = false;
				ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.STEP_DEFAULT_ALERT_BASED_ON_REQ,
							"cealertbasedmenu", null, null);
			}
			
			if (stepvo.getDfltSendAlertDaysCode() != null
					&& !stepvo.getDfltSendAlertDaysCode().trim().equals(
							ProgramConstants.EMPTY_STRING)) {

				if (stepvo.getDfltBeforeAfterCode() == null || stepvo.getDfltBeforeAfterCode().trim().equals(ProgramConstants.EMPTY_STRING)) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.BEFORE_AFTER_REQ,
									"cesendalertmen3",null, null);
				}

			} else {
				stepvo.setDfltBeforeAfterCode(null);
			}
		}
		//Added for the defect ESPRD00853112
		if(!flag){
		caseTypeDataBean.setMaintainCaseTypeFocusId("maintainCaseTypeCasesteps");
		}
		//for defect ESPRD00805369
		validateFlag=flag;
		return flag;
	}

	/**
	 * This method validate unique ordernumber and desc
	 * 
	 * @param newCaseStepVO
	 * @param actionCode
	 * @return
	 */
	private boolean validateStepOrderDesc(CaseTypeStepVO newCaseStepVO,
			String actionCode) {
		boolean flag = true;
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		List caseStepOldList = caseTypeDataBean.getCaseTypeStepsList();
		for (Iterator iter = caseStepOldList.iterator(); iter.hasNext();) {
			CaseTypeStepVO caseStepVO = (CaseTypeStepVO) iter.next();
			if (caseStepVO.getStepOrderNum().equalsIgnoreCase(
					newCaseStepVO.getStepOrderNum())
					&& caseStepVO.getDescription().equalsIgnoreCase(
							newCaseStepVO.getDescription())) {
				flag = false;
				caseTypeDataBean.setShowDescOrderFlag(true);
				
				ContactManagementHelper
						.setErrorMessage(MaintainContactManagementUIConstants.STEP_DESC_ORDER_CANT_SAME);
				break;
			}

		}

		return flag;
	}

	/**
	 * This method is used for validating CaseTypeVO
	 * 
	 * @param casetypevo
	 *            represents CaseTypeVO
	 * @param actionCode
	 *            represents String
	 * @return flag represents boolean
	 */
	public boolean validateCaseTypeVO(CaseTypeVO casetypevo, String actionCode) {
		boolean flag = true;
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		if (casetypevo.getShortDesc() == null
				|| casetypevo.getShortDesc().equals(
						ContactManagementConstants.EMPTY_STRING)) {
			flag = false;
			caseTypeDataBean.setShowTypeDescFlag(false);
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.CT_SHORT_DESC_REQ,
					MaintainContactManagementUIConstants.CT_SHORT_DESC_ID,
					MaintainContactManagementUIConstants.CT_SHORT_DESC_ID, actionCode);
		} else {
			String desc = casetypevo.getShortDesc();
			if (!EnterpriseCommonValidator
					.validateAlphaSpecialChars(desc.trim())) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.TYPE_SHORT_DESC_CHAR,
								MaintainContactManagementUIConstants.CT_SHORT_DESC_ID,
								MaintainContactManagementUIConstants.CT_SHORT_DESC_ID, actionCode);
			} else if (desc.length() > MaintainContactManagementUIConstants.SIXTY_FOUR) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.TYPE_SHORT_DESC_LENGTH,
								MaintainContactManagementUIConstants.CT_SHORT_DESC_ID,
								MaintainContactManagementUIConstants.CT_SHORT_DESC_ID, actionCode);
			}
		}
		if (casetypevo.getLongDesc() == null
				|| casetypevo.getLongDesc().equals(
						ContactManagementConstants.EMPTY_STRING)) {
			flag = false;
			caseTypeDataBean.setShowTypeDescFlag(false);
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.CT_LONG_DESC_REQ,
					MaintainContactManagementUIConstants.CT_LONG_DESC_ID, MaintainContactManagementUIConstants.CT_LONG_DESC_ID,
					actionCode);
		} else {

			String longDesc = casetypevo.getLongDesc();
			if (!EnterpriseCommonValidator
					.validateAlphaSpecialChars(longDesc.trim())) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.TYPE_LONG_DESC_CHAR,
								MaintainContactManagementUIConstants.CT_LONG_DESC_ID,
								MaintainContactManagementUIConstants.CT_LONG_DESC_ID, actionCode);
			} else if (longDesc.length() > MaintainContactManagementUIConstants.SIXTY_FOUR) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.TYPE_LONG_DESC_LENGTH,
								MaintainContactManagementUIConstants.CT_LONG_DESC_ID,
								MaintainContactManagementUIConstants.CT_LONG_DESC_ID, actionCode);
			}
		}
		if (actionCode
				.equalsIgnoreCase(MaintainContactManagementUIConstants.ADD)
				&& flag) {
			boolean typeFlag = true;
			typeFlag = validateDesc(casetypevo, actionCode);
			
			if (!typeFlag) {
				flag = false;
			}
		}
		 else if(actionCode
				.equalsIgnoreCase(MaintainContactManagementUIConstants.UPDATE)
				&& flag) {
			boolean typeFlag1 = true;
			typeFlag1 = validateDesc2(casetypevo, actionCode);
			if (!typeFlag1) {
				flag = false;
			}
		}
		//Added for the defect ESPRD00853112
		if(!flag){
			caseTypeDataBean.setMaintainCaseTypeFocusId("addEditCaseTypes");
		}
		return flag;
	}

	/**
	 * This method validates short and long description
	 * 
	 * @param oldcaseStepVO
	 */
	private boolean validateDesc(CaseTypeVO newCaseTypeVO, String actionCode) {
		boolean flag = true;
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		List caseTypeOldList = caseTypeDataBean.getMaintainCaseTypeList();
		for (Iterator iter = caseTypeOldList.iterator(); iter.hasNext();) {
			CaseTypeVO caseTypeVO = (CaseTypeVO) iter.next();
			if (caseTypeVO.getLongDesc() != null
					&& caseTypeVO.getShortDesc() != null) {
				
				if (caseTypeVO.getLongDesc().equalsIgnoreCase(
						caseTypeVO.getShortDesc())
						 && newCaseTypeVO.getShortDesc().equalsIgnoreCase(
						 		newCaseTypeVO.getLongDesc())) {
					flag = false;
					caseTypeDataBean.setShowTypeDescFlag(true);
					
					ContactManagementHelper
							.setErrorMessage(MaintainContactManagementUIConstants.TYPE_SHORT_LONGDESC_CANT_SAME);
					break;
				}if( caseTypeVO.getShortDesc().equalsIgnoreCase(newCaseTypeVO.getShortDesc())){
					flag = false;
					caseTypeDataBean.setShowTypeDescFlag(true);
					ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.TYPE_SHORT_LONGDESC_CANT_SAME);
					break;
				}
				//For Defect ESPRD00614024 Starts
				if( caseTypeVO.getLongDesc().equalsIgnoreCase(newCaseTypeVO.getLongDesc())){
					flag = false;
					caseTypeDataBean.setShowTypeDescFlag(true);
					ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.TYPE_SHORT_LONGDESC_CANT_SAME);
					break;
				}
				//For Defect ESPRD00614024 Ends
			}

		}
		return flag;
	}

	private boolean validateDesc2(CaseTypeVO newCaseTypeVO, String actionCode) {
		boolean flag = true;
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		List caseTypeOldList = caseTypeDataBean.getMaintainCaseTypeList();
		int index=0;
		if(logger.isDebugEnabled()){
		logger.debug("++caseTypeDataBean.getEditCaseTypeRowIndex()--"+caseTypeDataBean.getEditCaseTypeRowIndex());
		}
		for (Iterator iter = caseTypeOldList.iterator(); iter.hasNext();) {
			
			
			CaseTypeVO caseTypeVO = (CaseTypeVO) iter.next();
			if(caseTypeDataBean.getEditCaseTypeRowIndex()!=index)
			{
			if (caseTypeVO.getLongDesc() != null
					&& caseTypeVO.getShortDesc() != null) {
				
				if (caseTypeVO.getLongDesc().equalsIgnoreCase(
						caseTypeVO.getShortDesc())
						 && newCaseTypeVO.getShortDesc().equalsIgnoreCase(
						 		newCaseTypeVO.getLongDesc())) {
					flag = false;
					caseTypeDataBean.setShowTypeDescFlag(true);
					
					ContactManagementHelper
							.setErrorMessage(MaintainContactManagementUIConstants.TYPE_SHORT_LONGDESC_CANT_SAME);
					break;
				} else if( caseTypeVO.getShortDesc().equalsIgnoreCase(newCaseTypeVO.getShortDesc())){
					flag = false;
					caseTypeDataBean.setShowTypeDescFlag(true);
					ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.TYPE_SHORT_LONGDESC_CANT_SAME);
					break;
				}
				//For Defect ESPRD00614024 Starts
				else if( caseTypeVO.getLongDesc().equalsIgnoreCase(newCaseTypeVO.getLongDesc())){
					flag = false;
					caseTypeDataBean.setShowTypeDescFlag(true);
					ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.TYPE_SHORT_LONGDESC_CANT_SAME);
					break;
				}		
				//For Defect ESPRD00614024 Ends
			}
		}
			index++;
		}
		return flag;
	}

	/**
	 * This openration is used for setting data for
	 * DfltSendAlertDaysBeforeOrAfter column in case step VO
	 * 
	 * @param oldcaseStepVO :
	 *            CaseTypeStepVO
	 */
	private void setCaseStepDfltSendAlertDaysBeforeOrAfterColData(
			CaseTypeStepVO oldcaseStepVO) {

		String dfltSendAlertDaysCode = ContactManagementConstants.EMPTY_STRING;
		String defaultBeforeAfterCode = ContactManagementConstants.EMPTY_STRING;
		String dfltSendAlertDaysBeforeOrAfter = ContactManagementConstants.EMPTY_STRING;

		if (isNotNullAndNotEmpty(oldcaseStepVO.getDfltSendAlertDaysCode())) {

			dfltSendAlertDaysCode = oldcaseStepVO.getDfltSendAlertDaysCode();

		}
		if (isNotNullAndNotEmpty(oldcaseStepVO.getDfltBeforeAfterCode())) {
			if (oldcaseStepVO.getDfltBeforeAfterCode().equals(
					ContactManagementConstants.DFLT_BEFORE_CODE_VALUE)) {
				defaultBeforeAfterCode = ContactManagementConstants.DFLT_BEFORE_CODE;
			} else {
				defaultBeforeAfterCode = ContactManagementConstants.DFLT_AFTER_CODE;
			}
		}
		dfltSendAlertDaysBeforeOrAfter = dfltSendAlertDaysCode + " "
				+ defaultBeforeAfterCode;

		oldcaseStepVO
				.setDfltSendAlertDaysBeforeOrAfter(dfltSendAlertDaysBeforeOrAfter);
		if(logger.isDebugEnabled()){
		logger.debug("DfltSendAlertDaysBeforeOrAfter from Vo:"
				+ oldcaseStepVO.getDfltSendAlertDaysBeforeOrAfter());
		}
		/**
		 * Set Include
		 */
		String include = oldcaseStepVO.getCaseTypeStepInclude();
		if (include.equals(ContactManagementConstants.YES)) {
			oldcaseStepVO.setImgInclude(true);
		} else {
			oldcaseStepVO.setImgInclude(false);
		}

	}

	/**
	 * This operation is used enable to show add case steps block
	 * 
	 * @return String
	 */
	public String showAddCaseStep() {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	    if(logger.isDebugEnabled()){
		logger.debug("Got call to showCaseStep method ");
	    }
		caseTypeDataBean.setShowAddCaseSteps(ContactManagementConstants.TRUE);
		caseTypeDataBean.setIncudeflag(true);
		caseTypeDataBean.setShowStepSuccessMsg(false);
		caseTypeDataBean.setShowStepDeleteMsg(false);
		caseTypeDataBean.getCaseTypeStepVO().setCaseTypeStepInclude("Yes");
		caseTypeDataBean.setShowEditCaseSteps(ContactManagementConstants.FALSE);
		//Added for the defect ESPRD00853112
		caseTypeDataBean.setShowSucessMessage(false);
		caseTypeDataBean.setShowAddCaseTypes(caseTypeDataBean
				.isShowAddCaseTypes());
		caseTypeDataBean.setShowEditCaseTypes(caseTypeDataBean
				.isShowEditCaseTypes());
		caseTypeDataBean.setCaseTypeStepVO(new CaseTypeStepVO()); // Added for the defect id ESPRD00723971_14NOV2011
		caseTypeDataBean.setFirstFieldFocusID("tabone:descid");//added for the defect esprd00712689
		//CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to create new CaseStep Record.
	 * 
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String saveEditCaseStep() {
		if(logger.isDebugEnabled()){
		logger.debug("Got call to saveEdit casestep method ");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean.setShowAddCaseTypes(caseTypeDataBean
				.isShowAddCaseTypes());
		caseTypeDataBean.setShowEditCaseTypes(caseTypeDataBean
				.isShowEditCaseTypes());

		caseTypeDataBean.setShowAddCaseSteps(ContactManagementConstants.FALSE);
		//Commented for the defect ESPRD00853112
		//caseTypeDataBean.setShowEditCaseSteps(ContactManagementConstants.TRUE);
		CaseTypeStepVO caseStepVO = caseTypeDataBean.getCaseTypeStepVO();
		
		setCaseStepDfltSendAlertDaysBeforeOrAfterColData(caseStepVO);
		if (validateCaseStepVO(caseStepVO,
				MaintainContactManagementUIConstants.UPDATE)) {
			// Begin - Commented for the defect id ESPRD00723971_15NOV2011
			//CaseTypeStepVO caseStepVO = new CaseTypeStepVO();
			/*try {
				BeanUtils.copyProperties(caseStepVO, caseStepVO);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException at updateCaseStep");
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException at updateCaseStep");
			}*/
			// End - Commented for the defect id ESPRD00723971_15NOV2011
			if (caseStepVO.getCaseTypeStepInclude().equalsIgnoreCase("No")) {
				deleteCaseStep();

			} else {
			// Modify Defect ESPRD00723971 Starts
				/*String stepDesc = ContactManagementHelper.setShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CM_CASE_STEP_CD,
						caseStepVO.getDescription()); // Modified the object for the defect id ESPRD00723971_15NOV2011
				caseStepVO.setDescValue(stepDesc);*/ // Modified the object for the defect id ESPRD00723971_15NOV2011
				caseStepVO.setDescValue(getDescriptionFromVV(caseStepVO.getDescription(),
						caseTypeDataBean.getDescnCaseStepList()));
				
				
				
				// Added by ICS
				/*String orderDesc = ContactManagementHelper.setShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_STEP_ORDER_NUM,
						caseStepVO.getStepOrderNum()); // Modified the object for the defect id ESPRD00723971_15NOV2011
				caseStepVO.setStepOrderNumDesc(orderDesc);*/ // Modified the object for the defect id ESPRD00723971_15NOV2011
				caseStepVO.setStepOrderNumDesc(getDescriptionFromVV(caseStepVO.getStepOrderNum(),
						caseTypeDataBean.getOrderCaseStepList()));
						
						// Modify Defect ESPRD00723971 Ends
				// End
				
				// Begin - Modified the object for the defect id ESPRD00723971_15NOV2011
				//Added by ICS for Gap 15668
				Map users = caseTypeDataBean.getUserMap();
				String userName = (String) users.get(caseStepVO
						.getDfltNotfyAlertUserId());
				caseStepVO.setDfltNotfyAlertUserName(userName);
				//Ends
				//ESPRD00527652_UC-PGM-CRM-48_28SEP2010
				if(caseStepVO.getAutomaticRouteTo()!=null){
						//Modify DEFECT_ESPRD00723971 Starts
				//	caseStepVO.setAutomaticRouteToDesc((String)caseTypeDataBean.getUsersWithWorkUnits().get(caseStepVO.getAutomaticRouteTo()));
					caseStepVO.setAutomaticRouteToDesc(getDescriptionFromVV(caseStepVO.getAutomaticRouteTo(), 
							caseTypeDataBean.getCaseStepAutRoutoToList()));
								//Modify DEFECT_ESPRD00723971 Ends
				}
				// Begin - Modified the object for the defect id ESPRD00723971_15NOV2011				
				//EOf ESPRD00527652_UC-PGM-CRM-48_28SEP2010

				/* Fixes for defect id ESPRD00431831 Starts */
				
				/* Getting the Edit case Steps */
				CaseTypeStepVO caseTypeStepsVOForDel = (CaseTypeStepVO) caseTypeDataBean
				.getCaseTypeStepsList().get(caseTypeDataBean.getEditCaseStepRowIndex());
				/* Comparing the old and new case Steps code for a particaular case record  */
				if(caseTypeStepsVOForDel.getStepSeqNum() != null
						&& (!caseTypeStepsVOForDel.getDescription().equals(caseStepVO.getDescription()))) {
					/* 
					 * Adding the particular case steps to the delete list to delete the 
					 * exisiting record and inserting the updated case steps record as the 
					 * table G_DFLT_CASE_STEP_TB contain composite key of  which one column is 
					 * case steps code , on change of code it is inserting new one , this code 
					 * will remove if G_CASE_STEP_CD column will remove from the primary key of
					 * this table 
					 * 
					 */
					if(caseTypeDataBean.getCaseStepNoIncludeList() != null){
					caseTypeDataBean.getCaseStepNoIncludeList().add(caseTypeStepsVOForDel);
					}
				}
				/* Fixes for defect id ESPRD00431831 Starts */
//				ESPRD00527655_UC-PGM-CRM-48_29SEP2010

				String alertBasedOnDesc = null;
								// Begin - Modified the object for the defect id ESPRD00723971_15NOV2011
				if(caseStepVO.getDfltAlertBasedOnColName()!=null
						&& caseStepVO.getDfltAlertBasedOnColName().length()>0){
					alertBasedOnDesc = getDescriptionFromVV(
							caseStepVO.getDfltAlertBasedOnColName(),caseTypeDataBean.getDfltAlertBasedOnList());
					if(alertBasedOnDesc!=null && alertBasedOnDesc.length()>0){
						caseStepVO.setDfltAlertBasedOnDesc(alertBasedOnDesc);
					}else{
						caseStepVO.setDfltAlertBasedOnDesc(caseStepVO.getDfltAlertBasedOnColName());
					}
					
				}else{
					caseStepVO.setDfltAlertBasedOnDesc(ContactManagementConstants.EMPTYSTRING);
				}
				
				String complBasedOnDesc =null;
				if(caseStepVO.getDfltCmpltnBasedOnColName()!=null && caseStepVO.getDfltCmpltnBasedOnColName().length()>0){
					complBasedOnDesc = getDescriptionFromVV(
							caseStepVO.getDfltCmpltnBasedOnColName(),caseTypeDataBean.getCompletionBasedOnList());
					if(complBasedOnDesc!=null && complBasedOnDesc.length()>0){
						caseStepVO.setDfltCmpltnBasedOnDesc(complBasedOnDesc);	
					}else{
						caseStepVO.setDfltCmpltnBasedOnDesc(caseStepVO.getDfltCmpltnBasedOnColName());
					}
					
				}else{
					caseStepVO.setDfltCmpltnBasedOnDesc(ContactManagementConstants.EMPTYSTRING);
				}
				//EOf ESPRD00527655_UC-PGM-CRM-48_29SEP2010

				caseTypeDataBean.getCaseTypeStepsList().add(
						caseTypeDataBean.getEditCaseStepRowIndex(),
						caseStepVO);
				caseTypeDataBean.getCaseTypeStepsList().remove(
						caseTypeDataBean.getEditCaseStepRowIndex() + 1);
				caseTypeDataBean.setShowStepSuccessMsg(true);
				caseTypeDataBean.setShowStepDeleteMsg(false);
				caseTypeDataBean.setShowStepActMsg(false);
			}
			//Added for the defect ESPRD00853112
			caseTypeDataBean.setShowEditCaseSteps(ContactManagementConstants.FALSE);
			// End - Modified the object for the defect id ESPRD00723971_15NOV2011
			//caseTypeDataBean.setCaseTypeStepVO(new CaseTypeStepVO());//After save Edit mode should display
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to delete CaseType Record.
	 * 
	 * @return String.
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String deleteCaseStep() {
		if(logger.isDebugEnabled()){
		logger.debug("Inside deleteCasestep");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		int index = caseTypeDataBean.getEditCaseStepRowIndex();
		
		CaseTypeStepVO caseStepVo = (CaseTypeStepVO) caseTypeDataBean
				.getCaseTypeStepsList().get(index);
		Long caseTypeSk = caseTypeDataBean.getCaseTypeVO().getCaseTypeSK();
		
		boolean stepFlag = getActCMCaseSteps(caseStepVo, caseTypeSk);
		if (!stepFlag) {
			caseTypeDataBean.getCaseStepNoIncludeList().add(caseStepVo);
			caseTypeDataBean.getCaseTypeStepsList().remove(index);
			caseTypeDataBean.setShowStepActMsg(false);
			caseTypeDataBean.setShowStepDeleteMsg(true);
		} else {
			if(logger.isDebugEnabled()){
			logger.debug("CaseStep is associated to ActualCM");
			}
			ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.CASE_STEP_EXISTS_ACTCM);
			caseTypeDataBean.setShowStepActMsg(true);
		}
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method Resets the Case Step in Add Call Script Block .
	 * 
	 * @return String
	 */
	public String resetCaseStep() {
		if(logger.isDebugEnabled()){
		logger.debug("**************resetCaseStep");
		}
		CaseTypeStepVO resetvo = new CaseTypeStepVO();
		resetvo.setDescription("");
		resetvo.setDfltDaysToCmplCnt("");
		resetvo.setStepOrderNum("");
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean.setCaseTypeStepVO(resetvo);
		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This operation is used to cancel Add Case Step Block .
	 */
	public void cancelAddCaseSteps() {
		if(logger.isDebugEnabled()){
		logger.debug("Inside cancelAddCaseSteps ");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean.setShowAddCaseSteps(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEditCaseSteps(ContactManagementConstants.FALSE);
	}

	/**
	 * This operation is used to cancel Edit Case Step Block .
	 */
	public void cancelEditCaseSteps() {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	    if(logger.isDebugEnabled()){
		logger.debug("Inside cancelEditCaseSteps");
	    }
		caseTypeDataBean.setShowAddCaseSteps(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEditCaseSteps(ContactManagementConstants.FALSE);

	}

	/**
	 * This operation is used for setting data for
	 * DfltSendAlertDaysBeforeOrAfter property in Case EventVO table
	 * 
	 * @param caseEventVO :
	 *            CaseEventVO value object
	 */

	private void setEventDfltSendAlertDaysBeforeOrAfterColData(
			CaseTypeEventVO caseEventVO) {
		String dfltSendAlertDaysCode = ContactManagementConstants.EMPTY_STRING;
		String defaultBeforeAfterCode = ContactManagementConstants.EMPTY_STRING;
		String dfltSendAlertDaysBeforeOrAfter = ContactManagementConstants.EMPTY_STRING;
		if (isNotNullAndNotEmpty(caseEventVO.getDfltSendAlertDaysCode())) {
			dfltSendAlertDaysCode = caseEventVO.getDfltSendAlertDaysCode();

		}
		if (isNotNullAndNotEmpty(caseEventVO.getDfltBeforeAfterCode())) {
			if (caseEventVO.getDfltBeforeAfterCode().equals(
					ContactManagementConstants.DFLT_BEFORE_CODE_VALUE)) {
				defaultBeforeAfterCode = ContactManagementConstants.DFLT_BEFORE_CODE;
			} else {
				defaultBeforeAfterCode = ContactManagementConstants.DFLT_AFTER_CODE;
			}
		}
		dfltSendAlertDaysBeforeOrAfter = dfltSendAlertDaysCode
				+ ContactManagementConstants.SPACE_STRING
				+ defaultBeforeAfterCode;

		caseEventVO
				.setDfltSendAlertDaysBeforeOrAfter(dfltSendAlertDaysBeforeOrAfter);

	}

	/***************************************************************************
	 * This method is used to check whethre that list is not null or not an
	 * empty data
	 * 
	 * @param list :
	 *            List
	 * @return boolean : true if list not null and having data else false
	 **************************************************************************/
	private boolean isNotNullAndNotEmptyList(List list) {
		return (list != null && (!list.isEmpty()));
	}

	/***************************************************************************
	 * This method is used to check whethre that set is not null or not an empty
	 * data
	 * 
	 * @param set :
	 *            Set
	 * @return boolean : true if set not null and having data else false
	 **************************************************************************/
	/*private boolean isNotNullAndNotEmptySet(Set set) {
		return (set != null && (!set.isEmpty()));
	}*/

	/**
	 * This method is used to check if the input field is null or is equal to an
	 * empty string.
	 * 
	 * @param inputField :
	 *            String inputField
	 * @return boolean : true if input field is null or equal to an empty string
	 *         else false
	 */
	private boolean isNotNullAndNotEmpty(String inputField) {

		return (inputField != null && !ContactManagementConstants.EMPTY_STRING
				.equalsIgnoreCase(inputField.trim()));
	}

	/**
	 * This operation is used to show CaseEvent entry form .
	 * 
	 * @param null
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String showAddCaseEvent() {
		if(logger.isDebugEnabled()){
		logger.debug("Got call to showAddCaseEvent");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean.setShowAddCaseTypes(caseTypeDataBean
				.isShowAddCaseTypes());
		caseTypeDataBean.setShowEditCaseTypes(caseTypeDataBean
				.isShowEditCaseTypes());
		caseTypeDataBean.setShowAddCaseEvents(ContactManagementConstants.TRUE);
		caseTypeDataBean.setShowTypeCodeFlag(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEventsSuccessMsg(ContactManagementConstants.FALSE);//Added for Success message should not display when click on Add Case Event
		caseTypeDataBean.setIncudeflag(true);
		caseTypeDataBean.getCaseTypeEventVO().setCaseTypeEventInclude("Yes");
		caseTypeDataBean
				.setShowEditCaseEvents(ContactManagementConstants.FALSE);
		//Added for the defect ESPRD00853112
		caseTypeDataBean.setShowSucessMessage(false);
		caseTypeDataBean.setCaseTypeEventVO(new CaseTypeEventVO()); // Modified the object for the defet id ESPRD00723971_15NOV2011
		caseTypeDataBean.setFirstFieldFocusID("tabtwo:typeCode");//added for the defect esprd00712689
		//CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
		return null;
	}

	/**
	 * This operation is used to delete CaseEvent Record.
	 * 
	 * @param null
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String deleteCaseEvent() {
		if(logger.isDebugEnabled()){
		logger.debug("Inside deleteCaseEvent");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		int index = caseTypeDataBean.getEditCaseEventRowIndex();
		if(logger.isDebugEnabled()){
		logger.debug("Index in delete:" + index);
		}
		CaseTypeEventVO caseTypeEventVO = (CaseTypeEventVO) caseTypeDataBean
				.getCaseTypeEventsList().get(index);
		CaseTypeVO caseTypeVO = caseTypeDataBean.getCaseTypeVO();
		boolean eventFlag = getActCMCaseEvents(caseTypeEventVO, caseTypeVO
				.getCaseTypeSK());
		if(logger.isDebugEnabled()){
		logger.debug("Flag from actcaseevents in delete:" + eventFlag);
		}
		if (!eventFlag) {
			
			caseTypeDataBean.getCaseEventNoIncludeList().add(caseTypeEventVO);
			caseTypeDataBean.getCaseTypeEventsList().remove(index);
			caseTypeDataBean.setShowEventActMsg(false);
			caseTypeDataBean.setShowEventsDeleteMsg(true);
		} else {
			ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.CASE_STEP_EXISTS_ACTCM);
			caseTypeDataBean.setShowEventActMsg(true);
		}
		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This operation is used to get CaseEvent Record details
	 * 
	 * @param null
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String getCaseEventDetails() {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	    if(logger.isInfoEnabled()){
		logger.info("Got call to get CaseEventDetails ");
	    }
		if(caseTypeDataBean.isRowDetailsFlag()){
		caseTypeDataBean.setShowAddCaseEvents(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEditCaseEvents(ContactManagementConstants.TRUE);
		caseTypeDataBean.setNoCaseEventsData(ContactManagementConstants.TRUE);
		caseTypeDataBean.setShowTypeCodeFlag(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEventsSuccessMsg(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowEventsDeleteMsg(ContactManagementConstants.FALSE);

		
		FacesContext fc = FacesContext.getCurrentInstance();
		String indx = fc.getExternalContext().getRequestParameterMap().get(
				"rowIndex").toString();
		int index = new Integer(indx).intValue();
		caseTypeDataBean.setEditCaseEventRowIndex(index);
		CaseTypeEventVO caseTypeEventOldVO = null;
		//modification for ESPRD00797636 starts.
		CaseTypeEventVO caseTypeEventNewVO = new CaseTypeEventVO(); // Commented for the defect id ESPRD00723971_15NOV2011
		if (isNotNullAndNotEmptyList(caseTypeDataBean.getCaseTypeEventsList())) {
			caseTypeEventOldVO = (CaseTypeEventVO) caseTypeDataBean
					.getCaseTypeEventsList().get(index);
		}
		//modification for ESPRD00797636 starts.
		// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011
		try {
			BeanUtils.copyProperties(caseTypeEventNewVO, caseTypeEventOldVO);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException at getCaseEventDetails ");
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException at getCaseEventDetails ");
		}
		// End - Commented the code for the defect id ESPRD00723971_15NOV2011
		//modification for ESPRD00797636 ends.
		caseTypeDataBean.setMaintainCaseTypeFocusId("maintainCaseTypeEditCaseEvents");//Added for Page Focus Fix
		caseTypeDataBean.setFirstFieldFocusID("typeCode");//CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
		caseTypeDataBean.setCaseTypeEventVO(caseTypeEventNewVO);
		caseTypeDataBean.setShowSucessMessage(false);
		caseTypeDataBean.setRowDetailsFlag(false);
		}
		return "EditCaseEvents";

	}

	/**
	 * This operation is used to create new CaseEvent Record.
	 * 
	 * @return String.
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String saveEditCaseEvent() {
		if(logger.isDebugEnabled()){
		logger.debug("Got call to update CaseEvent");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean.setShowAddCaseTypes(caseTypeDataBean
				.isShowAddCaseTypes());
		caseTypeDataBean.setShowEditCaseTypes(caseTypeDataBean
				.isShowEditCaseTypes());
		caseTypeDataBean.setShowAddCaseEvents(ContactManagementConstants.FALSE);
		//Commented for the defect ESPRD00853112
		//caseTypeDataBean.setShowEditCaseEvents(ContactManagementConstants.TRUE);
		CaseTypeEventVO caseEventVO = caseTypeDataBean.getCaseTypeEventVO();
		setEventDfltSendAlertDaysBeforeOrAfterColData(caseEventVO);
		
		if (validateCaseEvent(caseEventVO,
				MaintainContactManagementUIConstants.UPDATE)) {
		// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011				
			//CaseTypeEventVO caseEventVO = new CaseTypeEventVO();
			/*try {
				BeanUtils.copyProperties(caseEventVO, caseEventVO);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException at updateCaseEvent ");
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException at updateCaseEvent ");
			}*/
		// End - Commented the code for the defect id ESPRD00723971_15NOV2011			
			if (caseEventVO.getCaseTypeEventInclude().equalsIgnoreCase("No")) {
				deleteCaseEvent();
			} else {
				//Added by ICS for Gap 15668
				// Begin - Modified the object for the defect id ESPRD00723971_15NOV2011
				Map users = caseTypeDataBean.getUserMap();
				String userName = (String) users.get(caseEventVO
						.getDfltNotfyAlertUserId());
				caseEventVO.setDfltNotfyAlertUserName(userName);
				//Ends
								
				/* Fixes for defect id ESPRD00431831 Starts */
				
				/* Getting the Edit case event */
				CaseTypeEventVO caseTypeEventVOForDel = (CaseTypeEventVO) caseTypeDataBean
				.getCaseTypeEventsList().get(caseTypeDataBean.getEditCaseEventRowIndex());
				/* Comparing the old and new case event code for a particaular case record  */
				if(caseTypeEventVOForDel.getCaseEventSeqNum() != null
						&& (!caseTypeEventVOForDel.getDfltCaseTypeEventTypeCode().equals(caseEventVO.getDfltCaseTypeEventTypeCode()))) {
					/* 
					 * Adding the particular case event to the delete list to delete the 
					 * exisiting record and inserting the updated case event record as the 
					 * table G_DFLT_CASE_EVENT_TB contain composite key of  which one column is 
					 * case event code , on change of code it is inserting new one , this code 
					 * will remove if G_CASE_EVENT_CD column will remove from the primary key of
					 * this table 
					 * 
					 */
					if(caseTypeDataBean.getCaseEventNoIncludeList() != null) {
					caseTypeDataBean.getCaseEventNoIncludeList().add(caseTypeEventVOForDel);
					}
				// End - Modified the object for the defect id ESPRD00723971_15NOV2011					
				}
				/* Fixes for defect id ESPRD00431831 Starts */
try{
	// Begin - Modified the object for the defect id ESPRD00723971_15NOV2011
	caseEventVO.setDfltCaseTypeEventTypeCodeDesc(getDescriptionFromVV(
					caseEventVO.getDfltCaseTypeEventTypeCode(),caseTypeDataBean.getCaseEventTypeList()));
	caseEventVO.setDfltAlertBasedOnDesc(getDescriptionFromVV(
			caseEventVO.getDfltAlertBasedOnColName(),caseTypeDataBean.getEventDfltAlertBasedOnList()));
	// End - Modified the object for the defect id ESPRD00723971_15NOV2011
}catch(Exception e){
	
}
				caseTypeDataBean.getCaseTypeEventsList().add(
						caseTypeDataBean.getEditCaseEventRowIndex(), caseEventVO);
				caseTypeDataBean.getCaseTypeEventsList().remove(
						caseTypeDataBean.getEditCaseEventRowIndex() + 1);
				caseTypeDataBean.setShowStepSuccessMsg(false);
				caseTypeDataBean.setShowStepDeleteMsg(false);
				caseTypeDataBean.setShowEventActMsg(false);
				caseTypeDataBean.setShowEventsSuccessMsg(true);
				caseTypeDataBean.setShowEventsDeleteMsg(false);
			}
			//Added for the defect ESPRD00853112
			caseTypeDataBean.setShowEditCaseEvents(ContactManagementConstants.FALSE);
			/**
			 * Set default VO
			 */
			//caseTypeDataBean.setCaseTypeEventVO(new CaseTypeEventVO());//After save Edit mode should display
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method Resets the Case Event in Add Call Script Block .
	 * 
	 * @return String
	 */
	public String resetCaseEvent() {
		if(logger.isDebugEnabled()){
		logger.debug("***************resetCaseEvent");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		if (caseTypeDataBean.isShowAddCaseEvents()) {
			caseTypeDataBean.setCaseTypeEventVO(new CaseTypeEventVO());
		} else {
			CaseTypeEventVO caseTypeEventVO = null;
			CaseTypeEventVO tempCaseTypeEventVO = new CaseTypeEventVO();
			int caseEventIndex = caseTypeDataBean.getEditCaseEventRowIndex();
			caseTypeEventVO = (CaseTypeEventVO) caseTypeDataBean
					.getCaseEventTypeList().get(caseEventIndex);

			try {
				BeanUtils.copyProperties(tempCaseTypeEventVO, caseTypeEventVO);
			} catch (IllegalAccessException e) {
				if(logger.isErrorEnabled()){
				logger.error(e.getMessage(), e);
				}
			} catch (InvocationTargetException e) {

				if(logger.isErrorEnabled()){
					logger.error(e.getMessage(), e);
					}
			}
			caseTypeDataBean.setCaseTypeEventVO(tempCaseTypeEventVO);
		}
		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This Method cancels Edit Case Event Block .
	 */
	public void cancelEditCaseEvents() {
		if(logger.isDebugEnabled()){
		logger.debug("*********cancelEditCaseEvents");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean
				.setShowEditCaseEvents(ContactManagementConstants.FALSE);
		caseTypeDataBean.setShowAddCaseEvents(ContactManagementConstants.FALSE);

	}

	/**
	 * This Method cancels Edit Case Event Block .
	 */
	public void cancelAddCaseEvents() {
		if(logger.isDebugEnabled()){
		logger.debug("*********cancelEditCaseEvents");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		caseTypeDataBean.setShowAddCaseEvents(ContactManagementConstants.FALSE);
		caseTypeDataBean
				.setShowEditCaseEvents(ContactManagementConstants.FALSE);

	}

	/**
	 * This operation is used for sorting case types data
	 * 
	 * @param event :
	 *            ActionEvent object
	 * @return String
	 */
	public String getAllSortedCaseTypes(ActionEvent event) {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);
		caseTypeDataBean.setImageRender(event.getComponent().getId());
		//By default focus should come to first page after sort.
		caseTypeDataBean.setStartIndexForCaseType(0);
		caseTypeDataBean.setMaintainCaseTypeFocusId("maintaincasetypepagefocus");//Added for Page Focus Fix
		sortCaseTypes(sortColumn, sortOrder, caseTypeDataBean
				.getMaintainCaseTypeList());
		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This operation is used for sorting case steps data
	 * 
	 * @param event :
	 *            ActionEvent object
	 * @return String
	 */
	public String getAllSortedCaseSteps(ActionEvent event) {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	    //CFR
	   // caseTypeDataBean.setMaintainCaseTypeFocusId("addEditCaseTypesSteps");
	    //commented for the defect esprd00712642
	    //EFO CFR
		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);
		caseTypeDataBean.setImageRender(event.getComponent().getId());
		//By default focus should come to first page after sort.
		caseTypeDataBean.setStartIndexForCaseTypeSteps(0);
		/*sortCaseSteps(sortColumn, sortOrder, caseTypeDataBean
				.getMaintainCaseStepList());*/
		sortCaseSteps(sortColumn, sortOrder, caseTypeDataBean
				.getCaseTypeStepsList());
		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This operation is used for sorting case events data *
	 * 
	 * @param event :
	 *            ActionEvent object
	 * @return String
	 */
	public String getAllSortedCaseEvents(ActionEvent event) {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);
		caseTypeDataBean.setImageRender(event.getComponent().getId());
		//By default focus should come to first page after sort.
		caseTypeDataBean.setStartIndexForCaseTypeEvents(0);
		sortCaseEvents(sortColumn, sortOrder, caseTypeDataBean
				.getCaseTypeEventsList()); 
		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This operation is used for sorting custom fields data
	 * 
	 * @param event :
	 *            ActionEvent object
	 * @return String
	 */
	public String getAllSortedCustomFields(ActionEvent event) {
	    caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	    //commented for the defect esprd00712642
	    //caseTypeDataBean.setMaintainCaseTypeFocusId("addEditCaseTypesCustomfields");//CFR
		//modified for defect ESPRD00533966 starts
		/*String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);*/
		String sortColumn = (String) event.getComponent().getAttributes().get("coloumnDesc");
		//defect ESPRD00533966 ends
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);
		caseTypeDataBean.setImageRender(event.getComponent().getId());
		//By default focus should come to first page after sort.
		caseTypeDataBean.setMaintainCFDataTable(0);
		sortCustomFields(sortColumn, sortOrder, caseTypeDataBean
				.getMaintainCaseCustomFieldsList());
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used for sorting Templates data
	 * 
	 * @param event :
	 *            ActionEvent object
	 * @return String
	 */
	public String getAllSortedCaseTemplates(ActionEvent event) {
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		//CFR
	    //commented for the defect esprd00712642
		//caseTypeDataBean.setMaintainCaseTypeFocusId("addEditCaseTypesTemplates");//EOF CFR
		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);
		caseTypeDataBean.setImageRender(event.getComponent().getId());
		//By default focus should come to first page after sort.
		caseTypeDataBean.setMaintainTemplatesDataTable(0);
		
		sortTemplates(sortColumn, sortOrder, caseTypeDataBean
				.getMaintainCaseTemplatesList());
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used for sorting templates data
	 * 
	 * @param sortColumn
	 *            Represents String
	 * @param sortOrder
	 *            Represents String
	 * @param maintainCaseCustomFieldsList
	 *            Represents List
	 */
	private void sortTemplates(final String sortColumn, final String sortOrder,
			List maintainCaseTemplatesList) {
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CaseTypeLetterTemplateVO data1 = (CaseTypeLetterTemplateVO) obj1;
				CaseTypeLetterTemplateVO data2 = (CaseTypeLetterTemplateVO) obj2;

				boolean ascending = (ContactManagementConstants.SORT_ASC
						.equalsIgnoreCase(sortOrder)) ? true : false;
				
				if ("t_include".equals(sortColumn)) {
					return compareTO(ascending, String.valueOf(data1.isIncludeTemplate()), String.valueOf(data2
							.isIncludeTemplate()));
				}
				
				if ("t_template".equals(sortColumn)) {
					if (null == data1.getTemplate()) {
						data1
								.setTemplate(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getTemplate()) {
						data2
								.setTemplate(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1.getTemplate(), data2
							.getTemplate());
				}
				if ("t_templateDesc".equals(sortColumn)) {
					if (null == data1.getTemplateDescription()) {
						data1
								.setTemplateDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getTemplateDescription()) {
						data2
								.setTemplateDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1.getTemplateDescription(),
							data2.getTemplateDescription());
				}
				return 0;
			}

		};

		Collections.sort(maintainCaseTemplatesList, comparator);

	}

	/**
	 * This operation is used for sorting custom fields data
	 * 
	 * @param sortColumn :
	 *            String
	 * @param sortOrder :
	 *            String
	 * @param maintainCaseCustomFieldsList
	 *            :List
	 */
	private void sortCustomFields(final String sortColumn,
			final String sortOrder, List maintainCaseCustomFieldsList) {
		final Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CustomFieldVO data1 = (CustomFieldVO) obj1;
				CustomFieldVO data2 = (CustomFieldVO) obj2;

				boolean ascending = (ContactManagementConstants.SORT_ASC
						.equalsIgnoreCase(sortOrder)) ? true : false;
				
				if ("activeValue".equals(sortColumn)) {
					return compareTO(ascending, String.valueOf(data1.isCustomFieldSelected()),
							String.valueOf(data2.isCustomFieldSelected()));
				}
				// Commented for the Defect ID: ESPRD00734527
//				if ("ce_typeValue".equals(sortColumn)) {
//					return compareTO(ascending, data1.getColumnDescription(),
//							data2.getColumnDescription());
//				}
				// Added for the Defect Id : ESPRD00734527 
				// Start
				if ("ce_typeValue".equals(sortColumn)) {
					if (null == data1.getColumnDescription()) {
						data1
								.setColumnDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getColumnDescription()) {
						data2
								.setColumnDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getColumnDescription().compareToIgnoreCase(
							data2.getColumnDescription()) : data2.getColumnDescription().compareToIgnoreCase(
							data1.getColumnDescription());
				}
				// End
				if ("cf_dataTypeValue".equals(sortColumn)) {
					if (null == data1.getDataType()) {
						data1
								.setDataType(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDataType()) {
						data2
								.setDataType(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1.getDataType(), data2
							.getDataType());
				}
				if ("cf_fieldLen".equals(sortColumn)) {
					logger.debug("ascending::::::"+ascending);
					/*return compareTO(ascending, data1.getLength(), data2
							.getLength());*/
					return compareTONumbers(ascending, new Long(data1.getLength()), new Long(data2
							.getLength()));
				}
				return 0;
			}

		};
		Collections.sort(maintainCaseCustomFieldsList, comparator);

	}

	/**
	 * This operation is used for sorting case steps data
	 * 
	 * @param sortColumn
	 *            :String
	 * @param sortOrder :
	 *            String
	 * @param maintainCaseStepList
	 *            :List
	 */
	private void sortCaseSteps(final String sortColumn, final String sortOrder,
			final List getCaseTypeStepsList){ 

		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CaseTypeStepVO data1 = (CaseTypeStepVO) obj1;
				CaseTypeStepVO data2 = (CaseTypeStepVO) obj2;

				boolean ascending = (ContactManagementConstants.SORT_ASC
						.equalsIgnoreCase(sortOrder)) ? true : false;

				if ("cs_casesteporder".equals(sortColumn)) {
//					ESPRD00529633_UC-PGM-CRM-48_13oct2010
					/*return compareTO(ascending, data1.getStepOrderNum(), data2
							.getStepOrderNum());
					*/
					return compareTwoIntegers(ascending, data1.getStepOrderNum(), data2
							.getStepOrderNum());
					//EOf ESPRD00529633_UC-PGM-CRM-48_13oct2010

				}

				if ("cs_daysToComplete".equals(sortColumn)) {
					if (null == data1.getDfltDaysToCmplCnt()) {
						data1
								.setDfltDaysToCmplCnt(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltDaysToCmplCnt()) {
						data2
								.setDfltDaysToCmplCnt(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1.getDfltDaysToCmplCnt(),
							data2.getDfltDaysToCmplCnt());
				}

				if ("cs_descriptionValue".equals(sortColumn)) {
					if (null == data1.getDescription()) {
						data1
								.setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDescription()) {
						data2
								.setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1.getDescription(), data2
							.getDescription());
				}
				if ("cs_automaticRouteTo".equals(sortColumn)) {
					if (null == data1.getAutomaticRouteTo()) {
						data1
								.setAutomaticRouteTo(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getAutomaticRouteTo()) {
						data2
								.setAutomaticRouteTo(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1.getAutomaticRouteTo(),
							data2.getAutomaticRouteTo());
				}

				if ("cs_completeBasedOn".equals(sortColumn)) {
					if (null == data1.getDfltCmpltnBasedOnColName()) {
						data1
								.setDfltCmpltnBasedOnColName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltCmpltnBasedOnColName()) {
						data2
								.setDfltCmpltnBasedOnColName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1
							.getDfltCmpltnBasedOnColName(), data2
							.getDfltCmpltnBasedOnColName());
				}

				if ("cs_notifyalert".equals(sortColumn)) {
					if (null == data1.getDfltNotfyAlertUserId()) {
						data1
								.setDfltNotfyAlertUserId(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltNotfyAlertUserId()) {
						data2
								.setDfltNotfyAlertUserId(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending,
							data1.getDfltNotfyAlertUserId(), data2
									.getDfltNotfyAlertUserId());
				}

				if ("cs_alertBased".equals(sortColumn)) {
					if (null == data1.getDfltAlertBasedOnColName()) {
						data1
								.setDfltAlertBasedOnColName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltAlertBasedOnColName()) {
						data2
								.setDfltAlertBasedOnColName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1
							.getDfltAlertBasedOnColName(), data2
							.getDfltAlertBasedOnColName());
				}
				if ("cs_dfltSendAlertBased".equals(sortColumn)) {
					if (null == data1.getDfltSendAlertDaysCode()) {
						data1
								.setDfltSendAlertDaysCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltSendAlertDaysCode()) {
						data2
								.setDfltSendAlertDaysCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1
							.getDfltSendAlertDaysCode(), data2
							.getDfltSendAlertDaysCode());
				}
				if ("cs_dfltTemlateAlert".equals(sortColumn)) {
					if (null == data1.getDfltCotsLtrTmpltKeyData()) {
						data1
								.setDfltCotsLtrTmpltKeyData(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltCotsLtrTmpltKeyData()) {
						data2
								.setDfltCotsLtrTmpltKeyData(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1
							.getDfltCotsLtrTmpltKeyData(), data2
							.getDfltCotsLtrTmpltKeyData());
				}
				return 0;
			}
		};
		Collections.sort(getCaseTypeStepsList, comparator);
	}

	/**
	 * This operation is used for comparing two strings
	 * 
	 * @param ascending :
	 *            boolean
	 * @param data1
	 *            :String
	 * @param data2
	 *            :String
	 * @return int
	 */
	private int compareTO(boolean ascending, String data1, String data2) {
		String bool1 = ContactManagementConstants.EMPTY_STRING + data1;
		String bool2 = ContactManagementConstants.EMPTY_STRING + data2;
		return ascending ? bool1.compareToIgnoreCase(bool2) : bool2.compareToIgnoreCase(bool1);

	}
	private int compareTONumbers(boolean ascending, Long data1, Long data2) {
		Long bool1 = data1;
		Long bool2 = data2;
	//	String bool2 = ContactManagementConstants.EMPTY_STRING + data2;
		return ascending ? bool1.compareTo(bool2) : bool2.compareTo(bool1);

	}
//	ESPRD00529633_UC-PGM-CRM-48_13oct2010

	/**
	 * This operation is used for comparing two integers which are in string forms
	 * 
	 * @param ascending :
	 *            boolean
	 * @param data1
	 *            :Integer in String form
	 * @param data2
	 *            :Integer in String form
	 * @return int
	 */
	private int compareTwoIntegers(boolean ascending, String data1, String data2) {
		Integer bool1 = null;
		Integer bool2 = null;
		if(data1==null || ContactManagementConstants.EMPTY_STRING.equals(data1)){
			bool1 =  Integer.valueOf(0); /*FIND BUGS_FIX*/
		}else{
			bool1 = new Integer(data1);
		}
		if(data2==null || ContactManagementConstants.EMPTY_STRING.equals(data2)){
			bool2 = Integer.valueOf(0); /*FIND BUGS_FIX*/
		}else{
			bool2 = new Integer(data2); 
		}		
		
		return ascending ? bool1.compareTo(bool2) : bool2.compareTo(bool1);

	}
	//EOf ESPRD00529633_UC-PGM-CRM-48_13oct2010


	/**
	 * This operation is used for sorting casetypes data
	 * 
	 * @param sortColumn :
	 *            String
	 * @param sortOrder :
	 *            String
	 * @param maintainCaseTypeList
	 *            :List
	 */
	private void sortCaseTypes(final String sortColumn, final String sortOrder,
			List maintainCaseTypeList) {
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CaseTypeVO data1 = (CaseTypeVO) obj1;
				CaseTypeVO data2 = (CaseTypeVO) obj2;

				boolean ascending = false;
				ascending = (ContactManagementConstants.SORT_ASC
						.equalsIgnoreCase(sortOrder)) ? true : false;

				if ("ct_activeValue".equals(sortColumn)) {
					if (null == data1.getCaseTypeStatusCode()) {
						data1
								.setCaseTypeStatusCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getCaseTypeStatusCode()) {
						data2
								.setCaseTypeStatusCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1.getCaseTypeStatusCode(),
							data2.getCaseTypeStatusCode());
				}
                // Commented for the Defect ID: ESPRD00734102
//				if ("ct_shortDesc".equals(sortColumn)) {
//					return compareTO(ascending, data1.getShortDesc(), data2
//							.getShortDesc());
//				}
				// Added for the Defect ID: ESPRD00734102
				// Start
				if ("ct_shortDesc".equals(sortColumn)) {
					if (null == data1.getShortDesc()) {
						data1
								.setShortDesc(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getShortDesc()) {
						data2
								.setShortDesc(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getShortDesc().compareToIgnoreCase(
							data2.getShortDesc()) : data2.getShortDesc().compareToIgnoreCase(
							data1.getShortDesc());
				}
				// End
				// Commented for the Defect ID: ESPRD00734102
//				if ("ct_longDesc".equals(sortColumn)) {
//					return compareTO(ascending, data1.getLongDesc(), data2
//							.getLongDesc());
//				}
				// Added for the Defect ID: ESPRD00734102
				// Start
				if ("ct_longDesc".equals(sortColumn)) {
					if (null == data1.getLongDesc()) {
						data1
								.setLongDesc(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getLongDesc()) {
						data2
								.setLongDesc(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getLongDesc().compareToIgnoreCase(
							data2.getLongDesc()) : data2.getLongDesc().compareToIgnoreCase(
							data1.getLongDesc());
				}
				// End
				if ("ct_caseBussUnit".equals(sortColumn)) {
					if (null == data1.getBusnUnitCaseTypeCode()) {
						data1
								.setBusnUnitCaseTypeCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getBusnUnitCaseTypeCode()) {
						data2
								.setBusnUnitCaseTypeCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending,
							data1.getBusnUnitCaseTypeCode(), data2
									.getBusnUnitCaseTypeCode());
				}

				if ("ct_supApproval".equals(sortColumn)) {
					if (null == data1.getSprvsrRevwReqdInd()) {
						data1
								.setSprvsrRevwReqdInd(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getSprvsrRevwReqdInd()) {
						data2
								.setSprvsrRevwReqdInd(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1.getSprvsrRevwReqdInd(),
							data2.getSprvsrRevwReqdInd());
				}

				return 0;
			}

		};
		Collections.sort(maintainCaseTypeList, comparator);

	}

	/**
	 * This Operaiton Is used for sorting case events data
	 * 
	 * @param sortColumn
	 *            :String
	 * @param sortOrder :
	 *            String
	 * @param maintainCaseEventsList
	 *            :List
	 */
	private void sortCaseEvents(final String sortColumn,
			final String sortOrder, List getCaseTypeEventsList) {

		final Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CaseTypeEventVO data1 = (CaseTypeEventVO) obj1;
				CaseTypeEventVO data2 = (CaseTypeEventVO) obj2;

				boolean ascending = false;
				ascending = (ContactManagementConstants.SORT_ASC
						.equalsIgnoreCase(sortOrder)) ? true : false;

				if ("ce_typeValue".equals(sortColumn)) {
					if (null == data1.getDfltCaseTypeEventTypeCode()) {
						data1
								.setDfltCaseTypeEventTypeCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltCaseTypeEventTypeCode()) {
						data2
								.setDfltCaseTypeEventTypeCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1
							.getDfltCaseTypeEventTypeCode(), data2
							.getDfltCaseTypeEventTypeCode());
				}
				if ("ce_notifyalert".equals(sortColumn)) {
					if (null == data1.getDfltNotfyAlertUserId()) {
						data1
								.setDfltNotfyAlertUserId(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltNotfyAlertUserId()) {
						data2
								.setDfltNotfyAlertUserId(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending,
							data1.getDfltNotfyAlertUserId(), data2
									.getDfltNotfyAlertUserId());
				}
				if ("ce_alertBased".equals(sortColumn)) {
					if (null == data1.getDfltAlertBasedOnColName()) {
						data1
								.setDfltAlertBasedOnColName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltAlertBasedOnColName()) {
						data2
								.setDfltAlertBasedOnColName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1
							.getDfltAlertBasedOnColName(), data2
							.getDfltAlertBasedOnColName());
				}
				if ("ce_dfltSendAlertBased".equals(sortColumn)) {
					if (null == data1.getDfltSendAlertDaysCode()) {
						data1
								.setDfltSendAlertDaysCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltSendAlertDaysCode()) {
						data2
								.setDfltSendAlertDaysCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1
							.getDfltSendAlertDaysCode(), data2
							.getDfltSendAlertDaysCode());
				}
				if ("ce_dfltTemplate".equals(sortColumn)) {
					if (null == data1.getDfltCotsLtrTmltKeyData()) {
						data1
								.setDfltCotsLtrTmltKeyData(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDfltCotsLtrTmltKeyData()) {
						data2
								.setDfltCotsLtrTmltKeyData(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return compareTO(ascending, data1
							.getDfltCotsLtrTmltKeyData(), data2
							.getDfltCotsLtrTmltKeyData());
				}
				return 0;
			}

		};
		Collections.sort(getCaseTypeEventsList , comparator);

	}

	/**
	 * showing Audit Details for CseType
	 * 
	 * @return String
	 */
	public String showAuditHistory() {
		if(logger.isDebugEnabled()){
		logger.debug("Got call to showAuditHistory method ");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);

		GlobalAuditsDelegate audit;
		//Commented for Heap Dump Issue
		final List list = new ArrayList();
		//final List list = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		CaseType caseType = null;//heap dump issue fix new CaseType();
		try {
			audit = new GlobalAuditsDelegate();
			CaseTypeVOToDOConverter convertor = new CaseTypeVOToDOConverter();
	

			caseType = convertor.convertCaseTypeVOToDO(caseTypeDataBean
					.getCaseTypeVO());

			list.add(caseType);
			HashMap hm = audit.getAuditLogInfo(list);

			final ArrayList audlist = (ArrayList) hm.get(caseType);

			caseTypeDataBean.setCaseTypeAuditHistoryList(audlist);
			caseTypeDataBean
					.setCaseTypeAuditRender(ContactManagementConstants.TRUE);
		
			caseTypeDataBean.setAuditOpen(ContactManagementConstants.TRUE);
		} catch (GlobalAuditsBusinessException e) {
			if(logger.isDebugEnabled()){
			logger.debug("Error in show child audit history  " + e);
			}
		}

		return ProgramConstants.RETURN_SUCCESS;
	}

	/**
	 * This operation is used for getting availcasesteps data
	 */
	private List getAvailActiveCaseSteps() {

		List availActiveCaseSteps = null;
		CaseDelegate delegate = new CaseDelegate();
		try {
			availActiveCaseSteps = delegate.getAvailCaseSteps();
		} catch (CaseRecordFetchBusinessException e1) {
			if(logger.isDebugEnabled()){
				logger.debug("Error in getAvailActiveCaseSteps  " + e1);
				}
		}
	
		return availActiveCaseSteps;}
	
	/**
	 * @param usersList
	 *            The usersList to set.
	 * 
	 * public void setUsersList(List usersList) { this.usersList = usersList; }
	 */
	/**
	 * Custom Fields Selected list in Add
	 * 
	 * @author vantim
	 * @param customList
	 *            Represnts List
	 * @return selectedCustomList represents List TODO To change the template
	 *         for this generated type comment go to Window - Preferences - Java -
	 *         Code Style - Code Templates
	 */
	public List getSelectedCustomFields(List customList) {
		if(logger.isDebugEnabled()){
		logger.debug("Inside CustomFields List");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		List selectedCustomList = new ArrayList();
		List unSelectedCustomList = new ArrayList();
		int customListSize = 0;
		if (customList != null) {
			customListSize = customList.size();
		}
		//heap dump issue fix
		CustomFieldVO customFieldVO =null;
		/** Iterate throught list */
		if (customList != null && customListSize > 0) {
			/** For ADD customField Block */
			for (int i = 0; i < customListSize; i++) {

				//heap dump issue fix
				//CustomFieldVO customFieldVO = (CustomFieldVO) customList.get(i);
				customFieldVO = (CustomFieldVO) customList.get(i);
				
				if (customFieldVO.isCustomFieldSelected()) {
                    
					selectedCustomList.add(customFieldVO);

				} else {
					
					unSelectedCustomList.add(customFieldVO);
					caseTypeDataBean.setUnSelectedCustomFields(
							unSelectedCustomList);
					
				}

			}
		}
		return selectedCustomList;
	}

	/**
	 * This method used to getSelectedTempaltes
	 * 
	 * @param tempList
	 *            represents List
	 * @return selectedTempList represents List
	 */
	public List getSelectedTemplates(List tempList) {
		if(logger.isDebugEnabled()){
		logger.debug("Inside getselected templates");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		List selectedTempList = new ArrayList();
		List unSelectedTempList = new ArrayList();
		int tempSize = 0;
		if (tempList != null) {
			tempSize = tempList.size();

		}
		//heap dump issue fix
		CaseTypeLetterTemplateVO caseTemplateVO = null;
		if (tempList != null && tempSize > 0) {
			for (int i = 0; i < tempSize; i++) {
				caseTemplateVO = (CaseTypeLetterTemplateVO) tempList
						.get(i);

				if (caseTemplateVO.isIncludeTemplate()) {

					selectedTempList.add(caseTemplateVO);
				} else {
					unSelectedTempList.add(caseTemplateVO);
					caseTypeDataBean.setUnSelectedTemplates(
							unSelectedTempList);
					
				}
			}
		}
		return selectedTempList;
	}

	public List getUnselectedCaseTypeTemplates(List tempList) {
		logger.debug("Inside getunselectedCaseType");
		List unSelectDelTempList = new ArrayList();
		int tempSize = 0;
		if (tempList != null) {
			tempSize = tempList.size();
		}
		//heap dump issue fix
		CaseTypeLetterTemplateVO caseTemplateVO =null;
		if (tempList != null && tempSize > 0) {
			for (int i = 0; i < tempSize; i++) {
				caseTemplateVO = (CaseTypeLetterTemplateVO) tempList
						.get(i);
				if (caseTemplateVO.getCaseTypeSK() != null) {
					logger.debug("Inside if block casetypeSk not null");
					unSelectDelTempList.add(caseTemplateVO);
//					break;//commented to allow multiple delete
				}

			}
		}
		return unSelectDelTempList;
	}

	public List getUnselectedCaseTypeCustomFields(List customList) {
		if(logger.isDebugEnabled()){
		logger.debug("Inside getunselectedCaseType");
		}
		List unSelectDelCustomList = new ArrayList();
		int tempSize = 0;
		if (customList != null) {
			tempSize = customList.size();
		}
		//heap dump issue fix
		CustomFieldVO customVO = null;
		if (customList != null && tempSize > 0) {
			for (int i = 0; i < tempSize; i++) {
				customVO = (CustomFieldVO) customList.get(i);
				if (customVO.getCaseType() != null) {
					
					unSelectDelCustomList.add(customVO);

				}

			}
		}
		return unSelectDelCustomList;
	}

	/**
	 * This method used for setting error display messages.
	 * 
	 * @param errorName :
	 *            String errorName.
	 * @param arguments :
	 *            Array of Object. Parameters to be passed to the message
	 * @param messageBundle :
	 *            String messageBundle.
	 * @param componentId :
	 *            String componentId.
	 */
	/*private void setErrorMessage(String errorName, Object[] arguments,
			String messageBundle, String componentId) {
		logger.debug("********** setErrorMessage ***********");

		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		Locale locale = root.getLocale();
		String clientId = null;

		facesContext.getApplication().setMessageBundle(messageBundle);
		String errorMsg = MessageUtil.format(messageBundle, errorName,
				arguments, locale);
		FacesMessage fc = new FacesMessage();
		fc.setDetail(errorMsg);

		if (componentId != null) {
			logger.debug("Component ID " + componentId);

			UIComponent uiComponent = ContactManagementHelper
					.findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);

			logger.debug("Client Id " + clientId);
		}

		facesContext.addMessage(clientId, fc);
	}*/

	/**
	 * This Method disables the Tab contents for Inactive CallScript
	 * 
	 * @param event
	 *            Takes the Event Performed on Value change.
	 */
	public void inactiveCaseType(ValueChangeEvent event) {
		if(logger.isDebugEnabled()){
		logger.debug(" Inside inactiveCaseType");
		}
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		

		String newValue = (String) event.getNewValue();

		if (StringUtils.equalsIgnoreCase(ContactManagementConstants.NO,
				newValue)) {
			caseTypeDataBean.setCaseTypeNoActive(true);

		} else {
			caseTypeDataBean.setCaseTypeNoActive(false);
		}
	}

	
	

	

	private String userID() {
		String userID = null;
		
		try {

			HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(
					renderrequest, renderresponse);

			if (enterpriseUserProfile != null) {
				userID = enterpriseUserProfile.getUserId();
			}
		} catch (Exception e1) {
			if(logger.isDebugEnabled()){
			logger.debug("Exception in UserId fun" + e1.getMessage());
			}
		}

		return userID;

	}

	/**
	 * @param link2Show
	 *            The link2Show to set.
	 */
	public void setLink2Show(String link2Show) {
		this.link2Show = link2Show;
	}

	public Map getPermissions() {
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		Map linksMap = new HashMap();
		String userid = userID();
		ArrayList linksList2Pass = new ArrayList();
		
		linksList2Pass.add(ContactManagementConstants.MGMT_CASE_TYPE_SAVE);
		linksList2Pass.add(ContactManagementConstants.MGMT_CASE_TYPE_RESET);
		linksList2Pass.add(ContactManagementConstants.MGMT_CASE_TYPE_DELETE);
		linksList2Pass.add(ContactManagementConstants.MGMT_CASE_TYPE_ADD_CASE_EVENT);
		linksList2Pass.add(ContactManagementConstants.MGMT_CASE_TYPE_EDIT_CASE_EVENT_SAVE);
		linksList2Pass.add(ContactManagementConstants.MGMT_CASE_TYPE_EDIT_CASE_STEP_SAVE);
	
		linksList2Pass.add(ContactManagementConstants.MAINTAIN_ADD_CASETYPE);
       linksList2Pass.add(ContactManagementConstants.MAINTAIN_RESET_CASETYPE);
   	   linksList2Pass.add(ContactManagementConstants.MAINTAIN_ADDSAVE_CASETYPE);
   	   linksList2Pass.add(ContactManagementConstants.MAINTAIN_ADDCASESTEP_CASETYPE);
	   try {
			linksMap = fieldAccessControlImpl.getActionLinkPermission(
					linksList2Pass, userid);

		} catch (SecurityFLSServiceException e) {
			if(logger.isErrorEnabled()){
			logger.error("Exception has come:" + e.getMessage());
			}
		}
		
		return linksMap;
	}

	public String getLink2Show() {
		
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	   	String userid = userID();
	  	try{
		   /*	String returnValue = fieldAccessControlImpl.getFiledAccessPermission(ContactManagementConstants.CTMGMT_MAINTAIN_CASETYPE,userid);*/
	   		String returnValue = fieldAccessControlImpl
					.getFiledAccessPermission(
							"/Enterprise/CtMgmtMaintCaseType", userid);	 
	   		if(logger.isDebugEnabled()){
	   		logger.debug("++userid case type--"+userid);
			logger.debug("++caseType::"+returnValue);
	   		}
	   	if("R".equalsIgnoreCase(returnValue)){
	   		//for inquiry mode UI fields should be disabled added for ESPRD00802214
	   		caseTypeDataBean.setDisableUIFields(Boolean.TRUE);
	   		caseTypeDataBean.setDisableSaveCasTyp(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setDisableResetCasTyp(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setDisableDeleteCasTyp(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setDisableAddCaseEvent(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setDisableEditAddCaseEventSave(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setDisableEditCaseStepSave(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setDisableAddCaseType(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setDisableaddCaseTypereset(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setDisableaddSave(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setDisableAddcaseStep(ContactManagementConstants.TRUE);
	   		caseTypeDataBean.setControlRequired(ContactManagementConstants.TRUE);
	   	}else if("U".equalsIgnoreCase(returnValue)){
	   		//for non inquiry mode fields should be enable added for ESPRD00802214
	   		caseTypeDataBean.setDisableUIFields(Boolean.FALSE);
	   		caseTypeDataBean.setDisableDeleteCasTyp(ContactManagementConstants.TRUE);
	   	}
	   	}catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
	   		if(logger.isErrorEnabled()){
				logger.error("SecurityFLSServiceException has come:" + e.getMessage());
				}
		}
		
	   	caseTypeDataBean.setRowDetailsFlag(true);		
		return link2Show;
	}
	
	/**
	 * @return Returns the loadValidValues.
	 * 
	 */
	//************************Performance Fix by Implementing Multi Thread Concept starts*****************
	public String getLoadValidValues() {

		String userId = getLoggedInUser();
		//String userId = "TBOINAPALLY";
		List list = new ArrayList();  // Added new list for the defect id ESPRD00723971_15NOV2011
		if(logger.isDebugEnabled()){
        logger.debug("Got call to  loadAllValiedValues");
		}
        String businessUnit = getUserBusinessUnit(userId);
        if(logger.isDebugEnabled()){
        logger.debug("++businessUnit==="+businessUnit);
        }
        List caseEventTypeListAll = new ArrayList();
        List eventDfltAlertBasedOnListAll = new ArrayList();
        List dfltAlertBasedOnListAll = new ArrayList();
        List completionBasedOnListAll = new ArrayList();
        caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN); // Added for the defect id ESPRD00723971
		// Begin - Added new code for the defect id ESPRD00723971_15NOV2011
        Map mapOfValidValues = null;
        Class[] argtypes = new Class[] { Map.class, String.class,
				String.class };
		Executor[] executor = new Executor[10];
        list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
   			 ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD));
        list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
      			 ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD));
        list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
     			 ReferenceServiceDataConstants.G_CM_CASE_STEP_CD));
		// End - Added new code for the defect id ESPRD00723971_15NOV2011
        if (businessUnit != null && businessUnit.startsWith("DDU")) {
	 		// Begin - Modified the code for the defect id ESPRD00723971_15NOV2011
        	 list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
        			 ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU));
        	 
			//caseTypeDataBean.setCaseEventTypeList(list7);
	 		// End - Modified the code for the defect id ESPRD00723971_15NOV2011			
	 		
        	 // Begin - Added the new code for the defect id ESPRD00723971_15NOV2011
			list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
       			 ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH));
	 		// End - Added the new code for the defect id ESPRD00723971_15NOV2011
			
			// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011
			/*final List list71 = setValiedData(FunctionalAreaConstants.GENERAL,

					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH);*/
					//caseEventTypeListAll.addAll(list7);
					//caseEventTypeListAll.addAll(list71);
					//caseTypeDataBean.setCaseEventTypeListAll(caseEventTypeListAll);
			
			
			/*final List list21 = setValiedData(FunctionalAreaConstants.GENERAL,

			ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU);
			logger.debug("List21 Size for defalutalert based on:"
					+ list21.size());*/
			//caseTypeDataBean.setEventDfltAlertBasedOnList(list21);
			/*list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
       			 ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU));
			final List list25 = setValiedData(FunctionalAreaConstants.GENERAL,

					ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH);*/
			// End - Commented the code for the defect id ESPRD00723971_15NOV2011					
			
			// Begin - Added the new code for the defect id ESPRD00723971_15NOV2011			
			list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
	       			 ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU));
			list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
	       			 ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH));
			// End - Added the new code for the defect id ESPRD00723971_15NOV2011								 
			
			// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011					
			/*eventDfltAlertBasedOnListAll.addAll(list21);
			eventDfltAlertBasedOnListAll.addAll(list25);*/
					//caseTypeDataBean.setEventDfltAlertBasedOnListAll(eventDfltAlertBasedOnListAll);

			/*final List list2 = setValiedData(FunctionalAreaConstants.GENERAL,

			ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU);
			logger
					.debug("List2 Size for defalutalert based on:"
							+ list2.size());
			caseTypeDataBean.setDfltAlertBasedOnList(list2);
			
			final List list22 = setValiedData(FunctionalAreaConstants.GENERAL,

					ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH);*/
					/*logger.debug("List2 Size for defalutalert based on:"
									+ list2.size());*/
			// End - Commented the code for the defect id ESPRD00723971_15NOV2011					
			
			// Begin - Added the new code for the defect id ESPRD00723971_15NOV2011
			list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
	       			 ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU));
			list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
	       			 ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH));
			// End - Added the new code for the defect id ESPRD00723971_15NOV2011

			// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011
			/*dfltAlertBasedOnListAll.addAll(list2);
			dfltAlertBasedOnListAll.addAll(list22);
					caseTypeDataBean.setDfltAlertBasedOnListAll(dfltAlertBasedOnListAll);*/

			// need to ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU 
			//need to be replace ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM
			//once G_CMPLTN_BASED_ON_COL_NAM_DDU IS added with systemlist value=0005
			/*final List list6 = setValiedData(FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM);
			logger.debug("List6 Size for completion basedon" + list6.size());
			caseTypeDataBean.setCompletionBasedOnList(list6);
			
			final List list61 = setValiedData(FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM);*/
			//logger.debug("List6 Size for completion basedon" + list6.size());
			// End - Commented the code for the defect id ESPRD00723971_15NOV2011			
			
			// Begin - Added the new code for the defect id ESPRD00723971_15NOV2011
			list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
	       			 ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU));
			list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
	       			 ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM));
			// End - Added the new code for the defect id ESPRD00723971_15NOV2011					 
			
			// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011			
			/*completionBasedOnListAll.addAll(list6);
			completionBasedOnListAll.addAll(list61);
			caseTypeDataBean.setCompletionBasedOnListAll(completionBasedOnListAll);*/
			// End - Commented the code for the defect id ESPRD00723971_15NOV2011						

			// Begin - Added the new code for the defect id ESPRD00723971_15NOV2011			
			mapOfValidValues = getResponseMap(list);
			executor[0] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseTypeDataBean.setCaseEventTypeList(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU,
			 * FunctionalAreaConstants.GENERAL));
			 */

			executor[1] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
					FunctionalAreaConstants.GENERAL }, argtypes);
			// caseEventTypeListAll.addAll(caseTypeDataBean.getCaseEventTypeList());
			/*
			 * caseEventTypeListAll.addAll(getLogDescData(mapOfValidValues,
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
			 * FunctionalAreaConstants.GENERAL));
			 */

			executor[2] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseTypeDataBean.setCaseEventTypeList(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// eventDfltAlertBasedOnListAll.addAll(caseTypeDataBean.getCaseEventTypeList());
			executor[3] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * eventDfltAlertBasedOnListAll.addAll(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// caseTypeDataBean.setEventDfltAlertBasedOnList(caseTypeDataBean.getCaseEventTypeList());
			executor[4] = call(
					this,
					"getLogDescData",
					new Object[] {
							mapOfValidValues,
							ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU,
							FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseTypeDataBean.setCaseEventTypeList(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// dfltAlertBasedOnListAll.addAll(caseTypeDataBean.getCaseEventTypeList());
			executor[5] = call(
					this,
					"getLogDescData",
					new Object[] {
							mapOfValidValues,
							ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH,
							FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * dfltAlertBasedOnListAll.addAll(getLogDescData(mapOfValidValues,
			 * ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// caseTypeDataBean.setDfltAlertBasedOnList(caseTypeDataBean.getCaseEventTypeList());
			executor[6] = call(
					this,
					"getLogDescData",
					new Object[] {
							mapOfValidValues,
							ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU,
							FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseTypeDataBean.setCaseEventTypeList(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// completionBasedOnListAll.addAll(caseTypeDataBean.getCaseEventTypeList());
			executor[7] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * completionBasedOnListAll.addAll(getLogDescData(mapOfValidValues,
			 * ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// caseTypeDataBean.setCompletionBasedOnList(caseTypeDataBean.getCaseEventTypeList());
			// End - Added the new code for the defect id
			// ESPRD00723971_15NOV2011
		} else {
			// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011
			if(logger.isDebugEnabled()){
			logger.debug("++businessUnit is OTHERS");
			}
			/*final List list7 = setValiedData(FunctionalAreaConstants.GENERAL,

			ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH);
			logger.debug("List7 Size caseEventType" + list7.size());

			caseTypeDataBean.setCaseEventTypeList(list7);
			
			final List list71 = setValiedData(FunctionalAreaConstants.GENERAL,

					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU);
					caseEventTypeListAll.addAll(list7);
					caseEventTypeListAll.addAll(list71);
					

					caseTypeDataBean.setCaseEventTypeListAll(caseEventTypeListAll);*/
			// End - Commented the code for the defect id ESPRD00723971_15NOV2011
			
			// Begin - Added the new code for the defect id ESPRD00723971_15NOV2011					
					list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
		        			 ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU));
					list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
		       			 ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH));
			// End - Added the new code for the defect id ESPRD00723971_15NOV2011						 
			
			// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011
			//		      added by infinite for defect ESPRD00357594 starts
			/*final List list21 = setValiedData(FunctionalAreaConstants.GENERAL,

			ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH);
			logger.debug("List21 Size for defalutalert based on:"
					+ list21.size());
			caseTypeDataBean.setEventDfltAlertBasedOnList(list21);
			
			final List list25 = setValiedData(FunctionalAreaConstants.GENERAL,

					ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU);
					logger.debug("List21 Size for defalutalert based on:"
							+ list21.size());
			eventDfltAlertBasedOnListAll.addAll(list21);
			eventDfltAlertBasedOnListAll.addAll(list25);
					caseTypeDataBean.setEventDfltAlertBasedOnListAll(eventDfltAlertBasedOnListAll);*/
			//ESPRD00357594 ends
			// End - Commented the code for the defect id ESPRD00723971_15NOV2011
			
			// Begin - Added the new code for the defect id ESPRD00723971_15NOV2011			
					list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
			       			 ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU));
					list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
			       			 ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH));
			// End - Added the new code for the defect id ESPRD00723971_15NOV2011
			
			// Begin - Commented  the code for the defect id ESPRD00723971_15NOV2011
			/*final List list2 = setValiedData(FunctionalAreaConstants.GENERAL,

			ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH);
			logger
					.debug("List2 Size for defalutalert based on:"
							+ list2.size());
			caseTypeDataBean.setDfltAlertBasedOnList(list2);
				
			final List list22 = setValiedData(FunctionalAreaConstants.GENERAL,

					ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU);
					logger.debug("List2 Size for defalutalert based on:"
									+ list2.size());
			
					dfltAlertBasedOnListAll.addAll(list2);
					dfltAlertBasedOnListAll.addAll(list22);
					caseTypeDataBean.setDfltAlertBasedOnListAll(dfltAlertBasedOnListAll);*/
			// End - Commented  the code for the defect id ESPRD00723971_15NOV2011
			
			// Begin - Added the new code for the defect id ESPRD00723971_15NOV2011
					list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
			       			 ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU));
					list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
			       			 ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH));
			// End - Added the new code for the defect id ESPRD00723971_15NOV2011
			
			// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011
			/*final List list6 = setValiedData(FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM);
			logger.debug("List6 Size for completion basedon" + list6.size());
			caseTypeDataBean.setCompletionBasedOnList(list6);
			
			final List list61 = setValiedData(FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM);
			// logger.debug("List6 Size for completion basedon" + list6.size());
			
			completionBasedOnListAll.addAll(list6);
			completionBasedOnListAll.addAll(list61);
			caseTypeDataBean.setCompletionBasedOnListAll(completionBasedOnListAll);*/
			// End - Commented the code for the defect id ESPRD00723971_15NOV2011
			
			// Begin - Added the code for the defect id ESPRD00723971_15NOV2011
			list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
	       			 ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU));
			list.add(getInputCriteria(FunctionalAreaConstants.GENERAL,
	       			 ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM));					 
			 mapOfValidValues = getResponseMap(list);
			executor[0] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseTypeDataBean.setCaseEventTypeList(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// caseEventTypeListAll.addAll(caseTypeDataBean.getCaseEventTypeList());
			executor[1] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseEventTypeListAll.addAll(getLogDescData(mapOfValidValues,
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU,
			 * FunctionalAreaConstants.GENERAL));
			 */
			executor[2] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseTypeDataBean.setCaseEventTypeList(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// eventDfltAlertBasedOnListAll.addAll(caseTypeDataBean.getCaseEventTypeList());
			executor[3] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * eventDfltAlertBasedOnListAll.addAll(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// caseTypeDataBean.setEventDfltAlertBasedOnList(caseTypeDataBean.getCaseEventTypeList());
			executor[4] = call(
					this,
					"getLogDescData",
					new Object[] {
							mapOfValidValues,
							ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH,
							FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseTypeDataBean.setCaseEventTypeList(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// dfltAlertBasedOnListAll.addAll(caseTypeDataBean.getCaseEventTypeList());
			executor[5] = call(
					this,
					"getLogDescData",
					new Object[] {
							mapOfValidValues,
							ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU,
							FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * dfltAlertBasedOnListAll.addAll(getLogDescData(mapOfValidValues,
			 * ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// caseTypeDataBean.setDfltAlertBasedOnList(caseTypeDataBean.getCaseEventTypeList());
			executor[6] = call(this, "getLogDescData", new Object[] {
					mapOfValidValues,
					ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseTypeDataBean.setCaseEventTypeList(getLogDescData(mapOfValidValues
			 * , ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// completionBasedOnListAll.addAll(caseTypeDataBean.getCaseEventTypeList());
			executor[7] = call(
					this,
					"getLogDescData",
					new Object[] {
							mapOfValidValues,
							ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU,
							FunctionalAreaConstants.GENERAL }, argtypes);
				/*completionBasedOnListAll.addAll(getLogDescData(mapOfValidValues,
						ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU,
						FunctionalAreaConstants.GENERAL));*/
				//caseTypeDataBean.setCompletionBasedOnList(caseTypeDataBean.getCaseEventTypeList());
			// End - Added the code for the defect id ESPRD00723971_15NOV2011
		}
        
        // Added by ICS for CR 1875(UC-048)
		
        // Begin - Commened the code for the defect id ESPRD00723971_15NOV2011		
        /*final List list2 = setValiedData(FunctionalAreaConstants.GENERAL,ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD);
        //End of adding by ICS for CR 1875(UC-048)
        logger.debug("List Size for BusinessUnit code" + list.size());
        caseTypeDataBean = (CaseTypeDataBean) getDataBean(CASE_TYPE_DATA_BEAN);
        caseTypeDataBean.setBussinessUnits(list);*/
		// End - Commened the code for the defect id ESPRD00723971_15NOV2011
       
       
        
		// Begin - Added the code for the defect id ESPRD00723971_15NOV2011
        executor[8] = call(this, "getLogDescData", new Object[] {
        		mapOfValidValues,
				ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD,
				FunctionalAreaConstants.GENERAL },
					argtypes);
        /*caseTypeDataBean.setBussinessUnits(getLogDescData(mapOfValidValues,
				ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD,
				FunctionalAreaConstants.GENERAL));*/
		// End - Added the code for the defect id ESPRD00723971_15NOV2011        
      
        
        // Added by ICS for defect ESPRD00330528
        final List list3 = setValiedData(FunctionalAreaConstants.GENERAL,
                ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD);
        if (list3 != null && !list3.isEmpty()) {
			if (list3 != null && list3.size() > 1) {
				Collections.sort(list3, new SortComparator());
				caseTypeDataBean.setDfltAlertSendBasedOnList(list3);
				caseTypeDataBean.setEventDfltSendAlertList(list3);
			}
		} else {
			caseTypeDataBean.setDfltAlertSendBasedOnList(list3);
			caseTypeDataBean.setEventDfltSendAlertList(list3);
		}
        //End
        /**
         * Valid value for casestep
         */
 		// Begin - Commented the code for the defect id ESPRD00723971_15NOV2011
        /*final List list4 = setValiedData(FunctionalAreaConstants.GENERAL,
                ReferenceServiceDataConstants.G_CM_CASE_STEP_CD);
        logger.debug("List4 Size for description of Casestep" + list4.size());
        caseTypeDataBean.setDescnCaseStepList(list4);*/
 		// End - Commented the code for the defect id ESPRD00723971_15NOV2011    
 		
        // Begin - Added the code for the defect id ESPRD00723971_15NOV2011   
        executor[9] = call(this, "getLogDescData", new Object[] {
        		mapOfValidValues,
				ReferenceServiceDataConstants.G_CM_CASE_STEP_CD,
				FunctionalAreaConstants.GENERAL },
					argtypes);
        /*caseTypeDataBean.setDescnCaseStepList(getLogDescData(mapOfValidValues,
				ReferenceServiceDataConstants.G_CM_CASE_STEP_CD,
				FunctionalAreaConstants.GENERAL));*/
 		// End - Added the code for the defect id ESPRD00723971_15NOV2011    
        
        
        try {
			caseTypeDataBean.setCaseEventTypeList((List) executor[0].get());
			caseEventTypeListAll.addAll(caseTypeDataBean.getCaseEventTypeList());
	        caseEventTypeListAll.addAll((List) executor[1].get());
	        caseTypeDataBean.setCaseEventTypeListAll(caseEventTypeListAll);
	        
	        caseTypeDataBean.setEventDfltAlertBasedOnList((List) executor[2].get());
	        eventDfltAlertBasedOnListAll.addAll(caseTypeDataBean.getEventDfltAlertBasedOnList());
	        eventDfltAlertBasedOnListAll.addAll((List) executor[3].get());
	        caseTypeDataBean.setEventDfltAlertBasedOnListAll(eventDfltAlertBasedOnListAll);
	      
	        caseTypeDataBean.setDfltAlertBasedOnList((List) executor[4].get());
	        dfltAlertBasedOnListAll.addAll(caseTypeDataBean.getDfltAlertBasedOnList());
	        dfltAlertBasedOnListAll.addAll((List) executor[5].get());
	        caseTypeDataBean.setDfltAlertBasedOnListAll(dfltAlertBasedOnListAll);
	        		
	        caseTypeDataBean.setCompletionBasedOnList((List) executor[6].get());
	        completionBasedOnListAll.addAll(caseTypeDataBean.getCompletionBasedOnList());
	        completionBasedOnListAll.addAll((List) executor[7].get());
	        caseTypeDataBean.setCompletionBasedOnListAll(completionBasedOnListAll);
	        
	        caseTypeDataBean.setBussinessUnits((List) executor[8].get());
	        caseTypeDataBean.setDescnCaseStepList((List) executor[9].get());
	        //caseTypeDataBean.setEventDfltAlertBasedOnList((List) executor[9].get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(logger.isErrorEnabled()){
				logger.error(" Exception has come due to get load valid values");
			}
		}
        
		//Commented for the defect ESPRD00853112
        //caseTypeDataBean.getTemplateDropDownList().add(new SelectItem("", "    "));
        // End of Adding by ICS for CR 1875(UC-048)
        
        // getUsersList(); 	//Modify DEFECT_ESPRD00723971 
        getAllUsers();
        getAllCaseTypes();
        getSystemList();
        caseTypeDataBean.setValiValueFlag(false);

    
		
		return loadValidValues;
	}
	//************************Performance Fix by Implementing Multi Thread Concept ENDS*****************
	 /**
     * This method used to getUserList
     */
    /*private void getUsersList()

    {
        logger.debug("Inside GetusersList in ControllerBean");
        CaseDelegate delegate = new CaseDelegate();
        List userlist = new ArrayList();
        List userids = new ArrayList();
        List eventuserids = new ArrayList();
        List users = null;
        List tempList = new ArrayList();
        String userName = null;
        //Added for Gap 15668
        Map usersMap = new HashMap();
        //ends
        caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
        try
        {
           
            logger.debug("in the Tryblock of getusersList");
            users = delegate.getAllUsers();
            logger.debug("UsersList size :" + users.size());

            if (!(users.isEmpty()))
            {
                userlist.add(new SelectItem(ContactManagementConstants.EMPTYSTRING,ContactManagementConstants.EMPTYSTRING));
                userids.add(new SelectItem(ContactManagementConstants.EMPTYSTRING,ContactManagementConstants.EMPTYSTRING));
                eventuserids.add(new SelectItem(ContactManagementConstants.EMPTYSTRING,ContactManagementConstants.EMPTYSTRING));
                caseTypeDataBean.getUsersWithWorkUnits().clear();//ESPRD00527652_UC-PGM-CRM-48_28SEP2010
                StringBuffer sbName =null;
                for (Iterator iter = users.iterator(); iter.hasNext();)
                {
                    EnterpriseUser usr = (EnterpriseUser) iter.next();
                    userName = ContactManagementConstants.EMPTYSTRING;
                    logger.debug(" UserID   $$  "+usr.getUserID());
                    sbName = new StringBuffer(ContactManagementConstants.EMPTYSTRING);
                    if(usr.getUserID() != null)
                    {
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
                        }//ESPRD00527856_UC-PGM-CRM-48_01oct2010
                    	 
                    	if(usr.getLastName() != null && !usr.getLastName().equalsIgnoreCase(ContactManagementConstants.EMPTYSTRING)){
                        	sbName.append(usr.getLastName()).append(", ");	
                        }
                        if(usr.getFirstName()!= null && !usr.getFirstName().equalsIgnoreCase(ContactManagementConstants.EMPTYSTRING)){
                        	sbName.append(usr.getFirstName()).append(" - ");
                        }
                        sbName.append(usr.getUserID());
                        userName= sbName.toString();
                      
                        if(!userName.equalsIgnoreCase(ContactManagementConstants.EMPTYSTRING))
                        {                            
                            userlist.add(new SelectItem(usr.getUserWorkUnitSK()
                                    .toString(), userName));
                            usersMap.put(usr.getUserID(),userName);
                            //ESPRD00527652_UC-PGM-CRM-48_28SEP2010
                            caseTypeDataBean.getUsersWithWorkUnits().put(
                            		usr.getUserWorkUnitSK().toString(),userName);  //EOF ESPRD00527652_UC-PGM-CRM-48_28SEP2010
                            userids.add(new SelectItem(usr.getUserID(),userName));//usr.getUserID()+" - "+userName));
                            eventuserids.add(new SelectItem(usr.getUserID(),userName));//usr.getUserID()+" - "+userName));
                            tempList.add(new SelectItem(usr.getUserWorkUnitSK(),userName));//usr.getUserID()+" - "+userName));
                                   
                        }
                    }                    
                }
                caseTypeDataBean.setCaseStepAutRoutoToList(userlist);
                caseTypeDataBean.setUserWorkUnitList(users);
                logger.debug("CaseStepAutoRouteList in databean:"
                        + caseTypeDataBean.getCaseStepAutRoutoToList().size());
                caseTypeDataBean.setDfltNotifyAlertList(userids);
                logger.debug("Dfltnotifyalert list in databean:"
                        + caseTypeDataBean.getDfltNotifyAlertList().size());
                caseTypeDataBean.setEventDfltNotifyAlertList(eventuserids);
                caseTypeDataBean.setUserMap(usersMap);
            }

        }
        catch (CaseRecordFetchBusinessException e)
        {
        	e.printStackTrace();
            logger.error("CaseRecordFetchBusinessException at getUsersList");
        }
   	 
    }
    
    */
	/**
	 * This operation is used to get all the call types
	 * 
	 * @return String.
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String getAllCaseTypes() 
	{
		if(logger.isDebugEnabled()){
	     logger.debug(" Got call to getAllCaseTypes ");
		}
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		CaseTypeSearchCriteriaVO caseTypeSearchCriteriaVO = new CaseTypeSearchCriteriaVO();
		List caseTypeDoList = new ArrayList(
				ContactManagementConstants.DEFAULT_SIZE);
		 CaseTypeDOConverter caseTypeDOConvertor = new CaseTypeDOConverter();
		 List caseTypesList = new ArrayList();
		 caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		try 
		{
		
			caseTypeDoList = contactMaintenanceDelegate
					.getAllCaseTypes(caseTypeSearchCriteriaVO);
			if(logger.isDebugEnabled()){
			logger.debug("::::::::caseTypeDoList.size()::::::::"+caseTypeDoList.size());
			}
	   
		if (!caseTypeDoList.isEmpty()) 
		{
			for (Iterator iter = caseTypeDoList.iterator(); iter.hasNext();) 
			{
				CaseType casetype = (CaseType) iter.next();
				CaseTypeVO caseTypeVO = new CaseTypeVO();
				  
				 caseTypeVO = caseTypeDOConvertor
						.convertCaseTypeDOToVO(casetype);
				 //CR_ESPRD00373565_MaintainCaseTypes_16JUL2010
				 /*try{
				 contactManagementHelper.createVOAuditKeysList(casetype,caseTypeVO);
				 doAuditKeyListOperation(caseTypeVO);
				 }catch(Exception e){
				 	logger.error("Exception while doing audit key list operation :getAllCaseTypes() :",e);
				 }*/
				 //EOf CR_ESPRD00373565_MaintainCaseTypes_16JUL2010
				
				// Modify Defect ESPRD00723971 Starts
				/*if (StringUtils.isNotEmpty(caseTypeVO.getBusnUnitCaseTypeCode()))
				 {
				    String buDesc =  ContactManagementHelper.setShortDescription(FunctionalAreaConstants.GENERAL2,
			                ReferenceServiceDataConstants.G_BUSN_UNIT_CD,caseTypeVO.getBusnUnitCaseTypeCode());
				   
				    caseTypeVO.setBusnUnitTypeCodeDesc(buDesc);
				   
				 }*/
				 // Modify Defect ESPRD00723971 Ends
				//Modified for defect ESPRD00400854 starts
				if(caseTypeVO.getBusnUnitCaseTypeCode()!=null){
					caseTypeVO.setBusnUnitTypeCodeDesc(getDescriptionFromVV(caseTypeVO.getBusnUnitCaseTypeCode(),caseTypeDataBean.getBussinessUnits()));
				}
				//defect ESPRD00400854 ends
				caseTypesList.add(caseTypeVO);
			}
			caseTypeDataBean.setMaintainCaseTypeList(caseTypesList);
			
			caseTypeDataBean.setNoCaseTypesData(true);

		   }
		   //commented for code cleanup during Heap dump fix
		   /*loadCustomFieldsData(caseTypeDOConvertor, contactMaintenanceDelegate);
		   loadTemplatesData(caseTypeDOConvertor, contactMaintenanceDelegate);*/
		   String userID = getLoggedInUser();
		   if(logger.isDebugEnabled()){
		   logger.debug("login user from getLoggedInUser:" + userID);
		   }
		   caseTypeDataBean.setUserID(userID);
		}
		catch (CaseTypeFetchBusinessException e) 
		{
			if(logger.isErrorEnabled()){
			logger.error("CaseTypeFetchBusinessException at getAllCaseTypes ");
			}
		}
		
		return MaintainContactManagementUIConstants.SUCCESS;
	}
	
	/**
     * It will get the system lists.
     *
     */	
	 private void getSystemList()
	    {
	     	SystemListDelegate listDelegate = new SystemListDelegate();
	        List pdList = new ArrayList();
	        pdList.add(new SelectItem("","")); 
	        caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
	        try 
			{   /*FINd BUGS_FIX*/
				SystemList systemList = listDelegate.getSystemListDetail(Long.valueOf(0002), "G1");
				  if (systemList != null)
		            {
		                Set sysDetSet = systemList.getSystemListDetails();
		                int i = 0;
		                
		                if (sysDetSet != null && !sysDetSet.isEmpty())
		                {
		                    for (Iterator iter = sysDetSet.iterator(); iter.hasNext();)
		                    {
		                        i++;
		                        SystemListDetail element = (SystemListDetail) iter.next();                  
		                       
		                        pdList.add(new SelectItem(element.getStartingValue(), element.getStartingValue()));
		                        }
		                }
		            }
				  //ESPRD00529633_UC-PGM-CRM-48_13oct2010
				  Comparator comparator =  new Comparator(){

					public int compare(Object obj1, Object obj2) {
						SelectItem sitem1 = (SelectItem)obj1;
						SelectItem sitem2 = (SelectItem)obj2;
						
						return compareTwoIntegers(true, sitem1.getLabel(), sitem2.getLabel());
					}
				  	
				  };
				  Collections.sort(pdList,comparator);
				  //EOF ESPRD00529633_UC-PGM-CRM-48_13oct2010
				  caseTypeDataBean.setOrderCaseStepList(pdList);
			} 
	        catch (SystemListNotFoundException e) 
			{
	        	if(logger.isErrorEnabled()){
	    			logger.error("SystemListNotFoundException at getSystemList ");
	    			}
			}
	     }
	 
	 /**
	     * This opeartion is used to load valid data
	     * 
	     * @param functionlString
	     *            :String
	     * @param referenceDataConstants :
	     *            String
	     * @return list : Returns the List of valid values.
	     */
	    public List setValiedData(String functionlString,
	            String referenceDataConstants)
	    {
	    	if(logger.isDebugEnabled()){
	        logger.debug("Got call to setValiedData ");
	    	}
	        List list = new ArrayList();
	        HashMap map = new HashMap();
	        List itemslist = new ArrayList();

	        final ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
	        final ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
	        ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

	      
	        InputCriteria inputCriteria = new InputCriteria();
	        inputCriteria.setFunctionalArea(functionlString);

	        inputCriteria.setElementName(referenceDataConstants);

	        list.add(inputCriteria);

	        referenceDataSearch.setInputList(list);

	        try
	        {

	            referenceDataListVO = referenceServiceDelegate
	                    .getReferenceData(referenceDataSearch);
	            
	            map = referenceDataListVO.getResponseMap();

	            list = (List) map.get(functionlString + "#" + referenceDataConstants);
	            int lstSize = list.size();
	            itemslist.add(new SelectItem("", ""));
	            for (int i = 0; i < lstSize; i++)
	            {
	                ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
	                String validValueCodeDesc = refVo.getValidValueCode() + "-"
	                        + refVo.getShortDescription();
	                
	                if(referenceDataConstants.equals(ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD)){
	                	itemslist.add(new SelectItem(refVo.getValidValueCode(), refVo
		                        .getShortDescription()));
	                }else{
	                	itemslist.add(new SelectItem(refVo.getValidValueCode(), validValueCodeDesc));
	                }

	            }

	        }

	        catch (ReferenceServiceRequestException e)
	        {
	        	if(logger.isErrorEnabled()){
	            logger.error("ReferenceServiceRequestException at setValiedData ");
	        	}
	        }
	        catch (SystemListNotFoundException e)
	        {
	        	if(logger.isErrorEnabled()){
	            logger.error("SystemListNotFoundException at setValiedData ");
	        	}
	        }

	      
	        return itemslist;

	    }
	    
	   
		
		/**
	     * This method is used to get the User ID.
	     * 
	     * @return String : User ID
	     */
	    public String getLoggedInUser()
	    {
	    	if(logger.isInfoEnabled()){
	        logger.info("getting UserID is started");
	    	}
//	        String userId = "DDUUSER19";
	        String userId = "ARSUSER19";
	        HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
	                .getCurrentInstance().getExternalContext().getRequest();
	        HttpServletResponse renderresponse = null;
	        EnterpriseUserProfile eup = getUserData(renderrequest, renderresponse);
	        if(logger.isDebugEnabled()){
	        logger.debug("Enterprise User profile is $$ " + eup);
	        }
	        if (eup != null && !eup.getUserId().equals(""))
	        {
	            userId = eup.getUserId();
	        }
	        else
	        {
	        	if(logger.isDebugEnabled()){
	            logger.debug("Enterprise User Profile is null");
	        	}
	        }
	        
	        return userId;
	    }
	    
	    /**
		 * This method is used to get the Buisness Unit Description.
		 * 
		 * @param selectedValue
		 */
		
		public String getUserBusinessUnit(String userId) {

			ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
			String businessUnitDesc = null;

			try {

				List buinessUnitDescs = delegate.getBuisnessUnitsDescs(userId);
				if (buinessUnitDescs != null) {
					if (buinessUnitDescs.size() == 1) {
						businessUnitDesc = (String) buinessUnitDescs.get(0);

					} else {
						businessUnitDesc = ContactManagementConstants.AllOthers;
					}
				} else {
					businessUnitDesc = ContactManagementConstants.AllOthers;

				}

			} catch (LOBHierarchyFetchBusinessException e) {
				if(logger.isErrorEnabled()){
				logger.error(e.getMessage(), e);
				}
			} catch (Exception e) {
				if(logger.isErrorEnabled()){
				logger.error(e.getMessage(), e);
				}
			}
			return businessUnitDesc;
		}
	    
		   //CR_ESPRD00373565_MaintainCaseTypes_16JUL2010
		  public String doAuditKeyListOperation(){
			  CaseTypeVO caseTypeVO=caseTypeDataBean.getCaseTypeVO();
			  if(logger.isInfoEnabled()){
			  logger.info(">>---------> Inside caseTypeVO caseTypeVO doAuditKeyListOperation:");  
			  }
	 	    	if(auditableCaseTypeDetails==null || auditableCaseTypeDetails.isEmpty())
	 	    	{
	 	    		getAuditableCaseTypeDetails();
	 	    	}
	 			if(caseTypeVO.getAuditKeyList()!=null && !caseTypeVO.getAuditKeyList().isEmpty())
	 			{
	 				AuditDataFilter.filterAuditKeys(auditableCaseTypeDetails,caseTypeVO);
	 			}  
	 			List auditlist = caseTypeVO.getAuditKeyList();
	 			Iterator Auditfield = auditlist.iterator();
	 			while(Auditfield.hasNext()){
	 				AuditKey audit = (AuditKey)Auditfield.next();
	 			}
	 			if(logger.isDebugEnabled()){
	 			logger.debug(">>------caseTypeVO--casetype-->"+caseTypeVO.getAuditKeyList().size()); 
	 			}
	 		
	 			doAuditKeyListOperationForCaseTypeCustomFieldVO();
	 			doAuditKeyListOperationForCaseTypeStepVO();
	 			doAuditKeyListOperationForCaseTypeEventVO();
	 			doAuditKeyListOperationForCaseTypeTemplateVO();
	 			
	 			caseTypeVO=caseTypeDataBean.getCaseTypeVO();
	 			
				 UIComponent component = findComponentInRoot("caseTypeVOAuditId");
					if(component!=null)
					{
						AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
						auditHistoryTable.setValue(caseTypeVO.getAuditKeyList());
					}
					
	 			caseTypeDataBean.setEnableMaintainCaseTypeAuditLog(true);
	 			return "SUCCESS";
	     }
	     
	     static List auditableCaseTypeDetails;

		/**
		 * @return Returns the auditableCaseTypeDetails.
		 */
		public List getAuditableCaseTypeDetails() {
			auditableCaseTypeDetails = new ArrayList();
			auditableCaseTypeDetails.add(createAuditableField("Active","statusTypeCode"));
			auditableCaseTypeDetails.add(createAuditableField("Short Description","shortDescription"));
			auditableCaseTypeDetails.add(createAuditableField("Long Description","description"));
			auditableCaseTypeDetails.add(createAuditableField("Business Unit","businessUnitCaseTypeCode"));
			auditableCaseTypeDetails.add(createAuditableField("Supervisor Approval Required","supervisorReviewReqIndicator"));
			
			return auditableCaseTypeDetails;
		}

		 private AuditableField createAuditableField(String fieldName,String domainAttributeName){
	 	AuditableField auditableField = new AuditableField();
	 	auditableField.setFieldName(fieldName);
	 	auditableField.setDomainAttributeName(domainAttributeName);
	 	return auditableField;
	 }
		//EOF CR_ESPRD00373565_MaintainCaseTypes_16JUL2010
		 
		 //CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
		 
		 public void enableMaintainCaseTypeAuditLog(){
		 	caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		 	caseTypeDataBean.setEnableMaintainCaseTypeAuditLog(true);
		 }
		 
		 static List auditableCaseTypeStepDetails;
		 static List auditableCaseTypeCustomFieldsDetails;
		 static List auditableCaseTypeTemplatesDetails;
		 static List auditableCaseTypeEventDetails;
	
		 	  
		  public String doAuditKeyListOperationForCaseTypeStepVO(){
			  if(logger.isDebugEnabled()){
			  	logger.debug(">>---------> Inside CaseTypeStepVO caseTypeStepVO doAuditKeyListOperationForCaseTypeStepVO:");
			  }
			  	 CaseTypeVO caseTypeVO=caseTypeDataBean.getCaseTypeVO();
			  	 Set caseTypeStepsAssignVO = caseTypeVO.getCaseSteps();
				 Iterator caseStepsVOIt=caseTypeStepsAssignVO.iterator();
				 while(caseStepsVOIt.hasNext()){
					CaseTypeStepVO caseStepVO =( CaseTypeStepVO)caseStepsVOIt.next();

		 	    	if(auditableCaseTypeStepDetails==null || auditableCaseTypeStepDetails.isEmpty())
		 	    	{
		 	    		getAuditableCaseTypeStepDetails();
		 	    	}
		 			if(caseStepVO.getAuditKeyList()!=null && !caseStepVO.getAuditKeyList().isEmpty())
		 			{
		 				AuditDataFilter.filterAuditKeys(auditableCaseTypeStepDetails,caseStepVO);
		 			} 
		 			 caseTypeVO.getAuditKeyList().addAll(caseStepVO.getAuditKeyList());
				 }
				 caseTypeDataBean.setCaseTypeVO(caseTypeVO);	
				 if(logger.isDebugEnabled()){
				 logger.debug(">>------caseTypeStepVO--caseTypeStep-->"+caseTypeVO.getAuditKeyList().size());
				 }
				 caseTypeDataBean.setEnableMaintainCaseTypeAuditLog(true);
				 return "SUCCESS";
		     }
		  
			public List getAuditableCaseTypeStepDetails() {
				auditableCaseTypeStepDetails = new ArrayList();
				auditableCaseTypeStepDetails.add(createAuditableField("Description","cmCaseStepCode"));
				auditableCaseTypeStepDetails.add(createAuditableField("Order ","stepOrderNumber"));
//				auditableCaseTypeStepDetails.add(createAuditableField("Automatic Route To","workUnitSK"));
				auditableCaseTypeStepDetails.add(createAuditableField("Default Days To Complete ","defaultDaysToCompleteCount"));
				auditableCaseTypeStepDetails.add(createAuditableField("Completion Based On","defaultCompltBasedOnColName"));
//				auditableCaseTypeStepDetails.add(createAuditableField("Default Notify via Alert ","userID"));
				auditableCaseTypeStepDetails.add(createAuditableField("Default Alert Based On","defaultAlertBasedOnColName"));
				auditableCaseTypeStepDetails.add(createAuditableField("Default Send Alert  Days","defaultSendAlertDaysCode"));
				auditableCaseTypeStepDetails.add(createAuditableField("Alert Days Before After","defaultBeforeAfterCode"));
//				auditableCaseTypeStepDetails.add(createAuditableField("Default Template ","letterTemplateKeyData"));
				
				return auditableCaseTypeStepDetails;
			}
			//EOf CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
			
				 
			 public String doAuditKeyListOperationForCaseTypeCustomFieldVO(){
				 CaseTypeVO caseTypeVO=caseTypeDataBean.getCaseTypeVO();
				 if(logger.isDebugEnabled()){
				 logger.debug("doAuditKeyListOperationForCaseTypeCustomFieldVO:");
				 }
				 List caseTypeCustomFieldAssignVO = caseTypeVO.getCustomFieldAssignmentVO();
				 Iterator customFldIt=caseTypeCustomFieldAssignVO.iterator();
				 while(customFldIt.hasNext()){
					 CustomFieldAssignmentVO customAssignmentVO =( CustomFieldAssignmentVO)customFldIt.next();

					 try{
						 if(auditableCaseTypeCustomFieldsDetails==null || auditableCaseTypeCustomFieldsDetails.isEmpty())
						 {
							 getAuditableCaseTypeCstmFldDetails();
						 }
						 if(customAssignmentVO.getAuditKeyList()!=null && !customAssignmentVO.getAuditKeyList().isEmpty())
						 {
							 AuditDataFilter.filterAuditKeys(auditableCaseTypeCustomFieldsDetails,customAssignmentVO);
						 } 
					 }catch (Exception e){
						 if(logger.isErrorEnabled()){
							 logger.error(" Exception has come due to doAuditKeyListOperationForCaseTypeCustomFieldVO");
						 }
					 }
			 			
					 caseTypeVO.getAuditKeyList().addAll(customAssignmentVO.getAuditKeyList());
				 }
				 caseTypeDataBean.setCaseTypeVO(caseTypeVO);
				 if(logger.isDebugEnabled()){
				 logger.debug(">>------customAssignmentVO--customField-->"+caseTypeVO.getAuditKeyList().size());
				 }
				 caseTypeDataBean.setEnableMaintainCaseTypeAuditLog(true);
				 return "SUCCESS";
			 }

			 public List getAuditableCaseTypeCstmFldDetails() {
				 auditableCaseTypeCustomFieldsDetails = new ArrayList();
				 if(logger.isDebugEnabled()){
				 logger.debug("------coming inside getAuditableCaseTypeCstmFldDetails------");
				 }
				 auditableCaseTypeCustomFieldsDetails.add(createAuditableField("subjectAreaSK","subjectAreaSK"));
				 return auditableCaseTypeCustomFieldsDetails;
			 }
			 
			 
			
			 public String doAuditKeyListOperationForCaseTypeEventVO(){
				 if(logger.isDebugEnabled()){
			  	logger.debug(">>---------> Inside CaseTypeEventVO caseTypeEventVO doAuditKeyListOperationForCaseTypeEventVO:");
				 }
			  	
			  	CaseTypeVO caseTypeVO=caseTypeDataBean.getCaseTypeVO();
			  	 Set caseTypeEventsAssignVO = caseTypeVO.getCaseEvents();
				 Iterator caseEventsVOIt=caseTypeEventsAssignVO.iterator();
				 while(caseEventsVOIt.hasNext()){
					 CaseTypeEventVO caseEventVO =(CaseTypeEventVO)caseEventsVOIt.next();

			    	if(auditableCaseTypeEventDetails==null || auditableCaseTypeEventDetails.isEmpty())
			    	{
			    		getAuditableCaseTypeEventDetails();
			    	}
					if(caseEventVO.getAuditKeyList()!=null && !caseEventVO.getAuditKeyList().isEmpty())
					{
						AuditDataFilter.filterAuditKeys(auditableCaseTypeEventDetails,caseEventVO);
					}  
					caseTypeVO.getAuditKeyList().addAll(caseEventVO.getAuditKeyList());
				 }
				 caseTypeDataBean.setCaseTypeVO(caseTypeVO);
				 if(logger.isDebugEnabled()){
				 logger.debug(">>------caseTypeEventVO--caseTypeEvent-->"+caseTypeVO.getAuditKeyList().size());
				 }
				 caseTypeDataBean.setEnableMaintainCaseTypeAuditLog(true);
				 return "SUCCESS";
		    }
			  
				public List getAuditableCaseTypeEventDetails() {
					auditableCaseTypeEventDetails = new ArrayList();
					auditableCaseTypeEventDetails.add(createAuditableField("Type","cmCaseEventCode"));
//					auditableCaseTypeEventDetails.add(createAuditableField("Default Notify via Alert ","userID"));
					auditableCaseTypeEventDetails.add(createAuditableField("Default Alert Based On ","defaultAlertBasedOnColName"));
					auditableCaseTypeEventDetails.add(createAuditableField("Default Send Alert Days","defaultSendAlertDaysCode"));
					auditableCaseTypeEventDetails.add(createAuditableField("Send Alert Days Before After","defaultBeforeAfterCode"));
//					auditableCaseTypeEventDetails.add(createAuditableField("Default Template ","letterTemplateKeyData"));
					return auditableCaseTypeEventDetails;
				}
				
				 public String doAuditKeyListOperationForCaseTypeTemplateVO(){
					 CaseTypeVO caseTypeVO=caseTypeDataBean.getCaseTypeVO();
					 if(logger.isDebugEnabled()){
					 logger.debug("doAuditKeyListOperationForCaseTypeTemplateVO:");
					 }
					 List caseTypeTemplateAssignVO = caseTypeVO.getCaseTypeTemplateVO();
					 Iterator templateVOIt = caseTypeTemplateAssignVO.iterator();
					 while(templateVOIt.hasNext()){
						 CaseTypeLetterTemplateVO caseTemplateVO=( CaseTypeLetterTemplateVO)templateVOIt.next();

						 try{
							 if(auditableCaseTypeTemplatesDetails==null || auditableCaseTypeTemplatesDetails.isEmpty())
							 {
								 getAuditableCaseTypeTemplateDetails();
							 }
							 if(caseTemplateVO.getAuditKeyList()!=null && !caseTemplateVO.getAuditKeyList().isEmpty())
							 {
								 AuditDataFilter.filterAuditKeys(auditableCaseTypeTemplatesDetails,caseTemplateVO);
							 } 
						 }catch (Exception e){
							 if(logger.isErrorEnabled()){
								 logger.error(" Exception has come due to doAuditKeyListOperationForCaseTypeTemplateVO");
							 }
						 }
				 			
						 caseTypeVO.getAuditKeyList().addAll(caseTemplateVO.getAuditKeyList());
					 }
					 caseTypeDataBean.setCaseTypeVO(caseTypeVO);
					 if(logger.isDebugEnabled()){
					 logger.debug(">>------caseTemplateVO--templateField-->"+caseTypeVO.getAuditKeyList().size());
					 }
					 
					 caseTypeDataBean.setEnableMaintainCaseTypeAuditLog(true);
					 return "SUCCESS";
				 }

				 public List getAuditableCaseTypeTemplateDetails() {
					 auditableCaseTypeTemplatesDetails = new ArrayList();
					 auditableCaseTypeTemplatesDetails.add(createAuditableField("letterTemplateKeyData","letterTemplateKeyData"));
					 return auditableCaseTypeTemplatesDetails;
				 }
							 
			 
			 /**
			     * This operation is used to find the component in root by passing id.
			     * 
			     * @param id :
			     *            String object.
			     * @return UIComponent : UIComponent object.
			     */

			    public UIComponent findComponentInRoot(String id) {
			    	if(logger.isInfoEnabled()){
			        logger.info("findComponentInRoot");
			    	}

			        UIComponent component = null;
			        FacesContext context = FacesContext.getCurrentInstance();

			        if (context != null) {
			            UIComponent root = context.getViewRoot();
			            component = findComponent(root, id);
			        }
			        return component;
			    }
			    
			    /**
			     * This operation is used to find the component by passing id.
			     * 
			     * @param base :
			     *            View root component of the jsp.
			     * @param id :
			     *            Id of the component from jsp.
			     * @return UIComponent object.
			     */

			    public UIComponent findComponent(UIComponent base, String id) {
			    	if(logger.isInfoEnabled()){
			        logger.info("findComponent");
			    	}
			        // Is the "base" component itself the match we are looking for?

			        if (id.equals(base.getId())) {
			            return base;
			        }

			        // Search through our facets and children

			        UIComponent component = null;
			        UIComponent result = null;
			        Iterator cmpIterator = base.getFacetsAndChildren();

			        while (cmpIterator.hasNext() && (result == null)) {
			            component = (UIComponent) cmpIterator.next();

			            if (id.equals(component.getId())) {
			                result = component;
			                break;
			            }
			            result = findComponent(component, id);
			            if (result != null) {
			                break;
			            }
			        }
			        return result;
			    }
			    
			    
			    public void redirect() throws IOException {
			    	if(logger.isInfoEnabled()){
			    	logger.info("************ cancelCaseType *********");
			    	}
					caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
					
					caseTypeDataBean.setCaseTypeVO(new CaseTypeVO());
					caseTypeDataBean.setCaseTypeStepVO(new CaseTypeStepVO());
					caseTypeDataBean.setCaseTypeEventVO(new CaseTypeEventVO());
					caseTypeDataBean.setCustomFieldVO(new CustomFieldVO());
					caseTypeDataBean.setCaseTypeTemplatesVO(new CaseTypeLetterTemplateVO());
					caseTypeDataBean.setCustomFieldVO(new CustomFieldVO());
					
					caseTypeDataBean.setShowAddCaseTypes(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowEditCaseTypes(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowEditCaseSteps(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowAddCaseSteps(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowAddCaseEvents(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowDeleteMessage(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowDeleteFlag(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowDescOrderFlag(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowSucessMessage(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowTypeDescFlag(ContactManagementConstants.FALSE);
					caseTypeDataBean.setShowEditCaseEvents(ContactManagementConstants.FALSE);

					Object obj = FacesContext.getCurrentInstance().getExternalContext()
							.getRequest();
					ActionRequest request = (ActionRequest) obj;
					String s = request.getParameter("com.ibm.faces.portlet.page.view");
					if(logger.isDebugEnabled()){
					logger.debug("s=======" + s);
					}
					Object object = FacesContext.getCurrentInstance().getExternalContext()
							.getResponse();
					ActionResponse response = (ActionResponse) object;

					String redirectCosumerContext=request.getParameter("redirectPortalPageContext");
			        if(redirectCosumerContext!=null){
			                response.sendRedirect(redirectCosumerContext+ s);
			        }else{
			                response.sendRedirect(s);
			        }
					FacesContext.getCurrentInstance().responseComplete();

				}
			    
			    
			    public InputCriteria getInputCriteria(String functionalArea,
						String referenceDataConstant) {
					InputCriteria inputCriteria = new InputCriteria();
					inputCriteria.setFunctionalArea(functionalArea);
					inputCriteria.setElementName(referenceDataConstant);
					return inputCriteria;
				}
			    
			    public Map getResponseMap(List inputList){
			    	ReferenceDataSearchVO referenceDataSearch = null;
					ReferenceServiceDelegate referenceServiceDelegate = null;
					ReferenceDataListVO referenceDataListVO = null;
			    	referenceDataSearch = new ReferenceDataSearchVO();
					referenceDataSearch.setInputList(inputList);
					referenceServiceDelegate = new ReferenceServiceDelegate();

					//Pass the list to the delegate
					referenceDataListVO = new ReferenceDataListVO();
					try {
						referenceDataListVO = referenceServiceDelegate
								.getReferenceData(referenceDataSearch);
					} catch (ReferenceServiceRequestException e) {
						if(logger.isErrorEnabled()){
							logger.error(" ReferenceServiceRequestException  "+ e);
						}
					} catch (SystemListNotFoundException e) {
						if(logger.isErrorEnabled()){
							logger.error(" SystemListNotFoundException  " +e);
						}
					}
					return referenceDataListVO.getResponseMap();
			    }
			    
			    public List getLogDescData(Map map, String referenceDataConstant,
						String functionalArea) {
					List validList = new ArrayList();
					validList.add(new SelectItem("", ""));
					List validValuesList = (List) map.get(functionalArea + "#"
							+ referenceDataConstant);
					int validValuesSize = validValuesList.size();
					for (int i = 0; i < validValuesSize; i++) {
						ReferenceServiceVO refVo = (ReferenceServiceVO) validValuesList
								.get(i);

						validList.add(new SelectItem(refVo.getValidValueCode(), refVo.getValidValueCode()+"-"+refVo
								.getShortDescription()));
					}
					return validList;
				}
			  //************************Performance Fix by Implementing Multi Thread Concept starts*****************
			    private Executor call(Object object, String methodName, Object[] args,
						Class[] argtypes) {
					Executor e = new Executor(object, methodName, args, argtypes);
					e.start();
					return e;
				}
			  //************************Performance Fix by Implementing Multi Thread Concept ENDS*****************
			    
			    	//Modify DEFECT_ESPRD00723971 Starts
			    private void getAllUsers() {
			    	if(logger.isInfoEnabled()){
			    	logger.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAllUsers");
			    	}

					CMDelegate cMDelegate = new CMDelegate();
					
					List userList = new ArrayList();
					Map userMap = new HashMap();
					List dataList = new ArrayList();
					caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);

					try {

						userList = cMDelegate.getUserDetails();
						
						List listOfUsers = new ArrayList();
						if (userList == null) {
							if(logger.isInfoEnabled()){
							logger.info("userList is null");
							}
						} else {

							for (Iterator iter = userList.iterator(); iter.hasNext();) {
								Object[] userDetails = (Object[]) iter.next();

								StringBuffer userDesc = new StringBuffer(
										ContactManagementConstants.EMPTY_STRING);

								if (userDetails[0] != null) {
									userDesc.append(userDetails[0]
											+ ContactManagementConstants.COMMA_SEPARATOR
											+ ContactManagementConstants.SPACE_STRING);

								}

								if (userDetails[1] != null) {
									userDesc.append(userDetails[1]
											+ ContactManagementConstants.HYPHEN_SEPARATOR);

								}

								if (userDetails[2] != null) {
									userDesc.append(userDetails[2]);

								}

								listOfUsers.add(new SelectItem(userDetails[3].toString(),
										userDesc.toString()));
								dataList.add(new SelectItem(userDetails[2].toString(),
										userDesc.toString()));
								userMap.put(userDetails[2].toString(),
										userDesc.toString());
							}

						}
						caseTypeDataBean.setCaseStepAutRoutoToList(listOfUsers);
		                caseTypeDataBean.setUserWorkUnitList(userList);
		                caseTypeDataBean.setDfltNotifyAlertList(dataList);
//		                caseTypeDataBean.setEventDfltNotifyAlertList(dataList);
		                caseTypeDataBean.setUserMap(userMap);
		                listOfUsers=null;
					} catch (Exception exe) {
						if(logger.isDebugEnabled()){
						logger.debug("Exception in getAllUsers--databean");
						}
					}
					userList=null;
					dataList=null;
					if(logger.isInfoEnabled()){
					logger.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getAllUsers");
					}
				}
				
					//Modify DEFECT_ESPRD00723971 Ends
			    
			    // Modify Defect ESPRD00723971 Starts
			    public String getDescriptionFromVV(String code, List vvList) {
					String desc = "";
					String valueStr = "";
					int size = vvList.size();
					for (int i = 0; i < size; i++) {
						SelectItem selectItem = (SelectItem) vvList.get(i);
						valueStr = "";
						if (selectItem != null) {
							valueStr = (String) selectItem.getValue();
							if (valueStr != null && valueStr.equals(code)) {
								desc = selectItem.getLabel();
								break;
							}
						}
					}
					return desc;
				}
				// Modify Defect ESPRD00723971 Ends
}