/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acs.enterprise.common.audit.application.exception.GlobalAuditsBusinessException;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryDeleteBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CustomFieldFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.EDMSDefaultsFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.FilterFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CategorySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.MaitainCategoryVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CategoryDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryCustomFieldsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryLetterTemplateVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategorySubjectVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;

/**
 * @author Wipro This class is a controller bean used for Add/update operation
 *         in Maintenance Category functionality.
 */
public class CategoryControllerBean extends EnterpriseBaseControllerBean {
	/** Instance of the Enterprise Logger */
	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CategoryControllerBean.class);

	/**
	 * This field is used to store whether user has permission.
	 */
	private boolean controlRequired = true;

	private boolean controlRequiredFOrDelete = true;

	private String loadCategoryData;

	private boolean catDetailFalg = true;

	/**
	 * This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermission() {
		String userPermission = "";
		String userid = getUserID();
		if(logger.isDebugEnabled())
		{
			logger.debug("CategoryControllerBean:::::userid::" + userid);
		}
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.MAINTAIN_CATEGORY_PAGE, userid);
			if(logger.isDebugEnabled())
			{
				logger.debug("CategoryControllerBean::::::::userPermission::" + userPermission);
			}
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(logger.isDebugEnabled())
			{
				logger.debug("error on CategoryControllerBean.getUserPermission :" + e);
			}
		}

		/*
		 * HttpServletRequest request = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest();
		 * request.setAttribute("displayMode",userPermission);
		 */
		userPermission = userPermission != null ? userPermission.trim() : "";
		if(logger.isDebugEnabled())
		{
			logger.debug("userPermission in MaintainCategory -->" + userPermission);
		}

		// check the permission level for the user and set it in a boolean
		// variable. This will be used for Buttons/Links
		if (userPermission.equals("r")) {
			controlRequired = false;
			controlRequiredFOrDelete = false;
		}
		if (userPermission.equals("u")) {
			controlRequired = true;
			controlRequiredFOrDelete = false;
		}
		if (userPermission.equals("d")) {
			controlRequired = true;
			controlRequiredFOrDelete = true;
		}
	}

	/**
	 * This method get the User ID
	 * 
	 * @return String
	 */
	private String getUserID() {
		String userID = null;
		try {
			/*
			 * HttpServletRequest renderrequest = (HttpServletRequest)
			 * FacesContext.getCurrentInstance().getExternalContext().getRequest();
			 * HttpServletResponse renderresponse = null; EnterpriseUserProfile
			 * enterpriseUserProfile = getUserData(renderrequest,
			 * renderresponse); if (enterpriseUserProfile != null) { userID =
			 * enterpriseUserProfile.getUserId(); }
			 */
			FacesContext facesContext = FacesContext.getCurrentInstance();
			PortletRequest portletRequest = (PortletRequest) facesContext
					.getExternalContext().getRequest();
			userID = getLoggedInUserID(portletRequest);
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("LOGGED_IN_USERID", userID);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			if(logger.isDebugEnabled())
			{
				logger.debug("Exception has come:");
			}
		}

		return userID;
	}

	/**
	 * A variable of type CategoryDataBean to hold the instance of data bean.
	 */
	private CategoryDataBean categoryDataBean = getCategoryDataBean();

	/**
	 * This operation is used to show the add Category block.
	 * 
	 * @return String : Navigation message.
	 */
	public String addCategory() {
		
		if (categoryDataBean.isRenderAddCategory()) {
			setErrorMessage(
					ContactManagementConstants.ERR_REF_ONE_ADD_AT_A_TIME,
					new Object[] { ContactManagementConstants.CATEGORY },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		} else {
			cancelIncompleteAction();
			getReferenceData();
			categoryDataBean.setCategoryVO(new CategoryVO());
			createListOfCategorySubVOs();
			loadCustomFiledAndTemplateData();
			categoryDataBean.setRenderAddCategory(true);
		}
		categoryDataBean.setShowSucessMessage(false);
		categoryDataBean.setShowDeletedMessage(false);
		categoryDataBean.setRenderCustomFieldDetails(false);
		categoryDataBean.setRenderSubjectDetails(false);
		categoryDataBean.setAuditLogFlag(false);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to get Contact Management Category details for
	 * edit operation.
	 * 
	 * @return String : Navigation message.
	 */
	public String getCategoryDetails() {
		if (catDetailFalg) {
			cancelIncompleteAction();
			getReferenceData();
			createListOfCategorySubVOs();
			loadCustomFiledAndTemplateData();
			//createListOfCategoryCustomFieldsVO();
			//createListOfCategoryTemplatesVO();

			categoryDataBean.setShowSucessMessage(false);
			categoryDataBean.setShowDeletedMessage(false);
			categoryDataBean.setAuditLogFlag(false);

			FacesContext fc = FacesContext.getCurrentInstance();
			Map map = fc.getExternalContext().getRequestParameterMap();
			String indexCode = (String) map
					.get(ContactManagementConstants.category_RowIndex);
			int index = Integer.parseInt(indexCode);
			MaitainCategoryVO maitainCategoryVO = (MaitainCategoryVO) categoryDataBean
					.getListOfCategoryVOs().get(index);
			if (maitainCategoryVO != null
					&& maitainCategoryVO.getCategorySK() != null) {
				CorrespondenceCategory categoryDO = null;
				ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
				CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();
				try {
					categoryDO = contactMaintenanceDelegate
							.getMaintainCateogryDetails(maitainCategoryVO
									.getCategorySK());
					CategoryVO categoryVO = categoryDOConvertor
							.convertCategoryDOToVOForCategory(categoryDO);
					categoryDataBean.setTempCategoryVO(categoryVO);

					CategoryVO tempCategoryVO = getCloneCategoryVO(categoryVO);

					String oldCategoryDesc = categoryVO.getCategoryDesc();
					HttpSession session = (HttpSession) FacesContext
							.getCurrentInstance().getExternalContext()
							.getSession(false);
					session.setAttribute("OLD_CAT_DESC", oldCategoryDesc);

					markExistingSubjsAsSelected(tempCategoryVO);
					markExistingCustomFieldsAsSelected(tempCategoryVO);
					markExistingTemplatesAsSelected(tempCategoryVO);

					tempCategoryVO.getListOfDeletedSubjects().clear();
					tempCategoryVO.getListOfDeletedCustomFields().clear();
					tempCategoryVO.getListOfDeletedTemplates().clear();

					/** Set Audit Information */
					tempCategoryVO.setAuditUserID(categoryVO.getAuditUserID());
					tempCategoryVO.setAddedAuditUserID(categoryVO
							.getAddedAuditUserID());

					categoryDataBean.setCategoryVO(tempCategoryVO);
					categoryDataBean.setCategoryRowIndex(index);
					categoryDataBean.setRenderEditCategory(true);
					/** For Audit */
					categoryDataBean.setCategoryAuditRender(false);
					categoryDataBean.setAuditOpen(false);
					categoryDataBean.setRenderSubjectDetails(false);
					categoryDataBean.setRenderCustomFieldDetails(false);
				} catch (CategoryFetchBusinessException e) {
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_GENERIC_FAILURE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
				}
			}

			//showAuditHistory();
			catDetailFalg = false;
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to create Contact Management Category.
	 * 
	 * @return String : Navigation message.
	 */
	public String createCategory() {
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();
		categoryDataBean.setShowSucessMessage(false);

		if (validateCategory()) {
			associateSubjects();
			associateCustomFields();
			associateTemplates();
			boolean inactive = (categoryVO.getActiveIndicator()
					.equalsIgnoreCase(ContactManagementConstants.NO)) ? true
					: false;
			categoryVO.setInactive(inactive);

			try {

				ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

				Boolean isDuplicateCategoryDesc = contactMaintenanceDelegate
						.checkDuplicateCategoryDescription(categoryVO
								.getCategoryDesc());
				CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();

				if (!isDuplicateCategoryDesc.booleanValue()) {
					CorrespondenceCategory categoryDO = categoryDOConvertor
							.convertCategoryVOToDO(categoryVO);

					customFieldsClone(categoryDO);

					categoryDO = contactMaintenanceDelegate
							.createCategory(categoryDO);

					if (categoryDO.getCategorySK() == null) {
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
								new Object[] {},
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);
					} else {
						CategoryVO editCategoryVO = categoryDOConvertor
								.convertCategoryDOToVOForCategory(categoryDO);

						CategoryVO tempCategoryVO = getCloneCategoryVO(editCategoryVO);

						String oldCategoryDesc = editCategoryVO
								.getCategoryDesc();
						HttpSession session = (HttpSession) FacesContext
								.getCurrentInstance().getExternalContext()
								.getSession(false);
						session.setAttribute("OLD_CAT_DESC", oldCategoryDesc);

						markExistingSubjsAsSelected(tempCategoryVO);
						markExistingCustomFieldsAsSelected(tempCategoryVO);
						markExistingTemplatesAsSelected(tempCategoryVO);

						tempCategoryVO.getListOfDeletedSubjects().clear();
						tempCategoryVO.getListOfDeletedCustomFields().clear();
						tempCategoryVO.getListOfDeletedTemplates().clear();

						/** Set Audit Information */
						tempCategoryVO.setAuditUserID(categoryVO
								.getAuditUserID());
						tempCategoryVO.setAddedAuditUserID(categoryVO
								.getAddedAuditUserID());

						categoryDataBean.setCategoryVO(tempCategoryVO);
                       /* Below line of code added for not to display delete category
                        on click of Reset link to fix defect ESPRD00802847*/
						categoryDataBean.setTempCategoryVO(tempCategoryVO);
						categoryDataBean.setRenderEditCategory(true);
						categoryDataBean.setRenderAddCategory(false);
						categoryDataBean.setRenderSubjectDetails(false);
						categoryDataBean.setRenderCustomFieldDetails(false);
						categoryDataBean.setShowSucessMessage(true);
						categoryDataBean.setCategorySort(false);
//						List listOfCategoryVOs = categoryDataBean.getListOfCategoryVOs();
//						listOfCategoryVOs.add(tempCategoryVO);
//						categoryDataBean.setListOfCategoryVOs(listOfCategoryVOs);
						CategorySearchCriteriaVO categorySearchCriteriaVO = categoryDataBean
						.getCategorySearchCriteriaVO();
						categoryDataBean.setListOfCategoryVOs(categoryDataBean.getAllCategories(categorySearchCriteriaVO));
						//Added to resolve issue no:36 
					
					}
				} else {
					// DEFECT : UC_PGM_CRM_031.DEFECT_ESPRD00235849_03JUNE09
					setErrorMessage(
							ContactManagementConstants.ERR_DUP_CATEGORY,
							new Object[] { ContactManagementConstants.CATEGORY },
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							ContactManagementConstants.CAT_DESC);

				}

			} catch (CategoryCreateBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						null);
			}

		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to update Contact Management Category.
	 * 
	 * @return String : Navigation message.
	 */
	public String updateCategory() {
		
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();

		if (validateCategory()
				&& !isInactiveCategoryAssWithCRorEDMSorFilter(categoryVO)) {
			boolean inactive = (categoryVO.getActiveIndicator()
					.equalsIgnoreCase(ContactManagementConstants.NO)) ? true
					: false;

			categoryVO.setInactive(inactive);
			try {
				associateSubjects();
				associateCustomFields();
				associateTemplates();
				ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

				/*
				 * FOR DEFECT ID :: ESPRD00061877, DESC :: Checking for
				 * duplicate category descrption is required only if user
				 * changes the description
				 */
				Boolean isDuplicateCategoryDesc = Boolean.FALSE;

				HttpSession session = (HttpSession) FacesContext
						.getCurrentInstance().getExternalContext().getSession(
								false);
				String oldCategoryDesc = (String) session
						.getAttribute("OLD_CAT_DESC");

				if (!categoryVO.getCategoryDesc().equalsIgnoreCase(
						oldCategoryDesc))
					isDuplicateCategoryDesc = contactMaintenanceDelegate
							.checkDuplicateCategoryDescription(categoryVO
									.getCategoryDesc());

				CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();
				if (!isDuplicateCategoryDesc.booleanValue()) {
					CorrespondenceCategory categoryDO = categoryDOConvertor
							.convertCategoryVOToDO(categoryVO);
					//"FindBugs" issue fixed
					//Boolean updated = Boolean.FALSE;

					/*hash map performance issue code change
					 * Map mapOfDeletedSubjects = contactMaintenanceDelegate
							.isEDMSCRDefaultAssociatedWithCatSubj(categoryDO
									.getCategorySK(), categoryDataBean
									.getMapOfDeletedSubjects());*/
					Map mapOfDeletedSubjects = contactMaintenanceDelegate
					.isEDMSCRDefaultAssociatedWithCatSubj(categoryDO
							.getCategorySK(), mapOfDeletedSubject(categoryVO));

					boolean associated = isDeletedSubjectAsscWithEDMS(mapOfDeletedSubjects);

					if (!associated) {
						categoryDO = contactMaintenanceDelegate
								.updateCategory(categoryDO);

						CategoryVO editCategoryVO = categoryDOConvertor
								.convertCategoryDOToVOForCategory(categoryDO);

						CategoryVO tempCategoryVO = getCloneCategoryVO(editCategoryVO);

						markExistingSubjsAsSelected(tempCategoryVO);
						markExistingCustomFieldsAsSelected(tempCategoryVO);
						markExistingTemplatesAsSelected(tempCategoryVO);

						tempCategoryVO.getListOfDeletedSubjects().clear();
						tempCategoryVO.getListOfDeletedCustomFields().clear();
						tempCategoryVO.getListOfDeletedTemplates().clear();

						/** Set Audit Information */
						tempCategoryVO.setAuditUserID(categoryVO
								.getAuditUserID());
						tempCategoryVO.setAddedAuditUserID(categoryVO
								.getAddedAuditUserID());
						categoryDataBean.setCategoryVO(tempCategoryVO);
						//resetDataTablesList();
						categoryDataBean.setRenderEditCategory(true);
						categoryDataBean.setRenderAddCategory(false);
						categoryDataBean.setShowSucessMessage(true);

					}
				} else {
					setErrorMessage(
							ContactManagementConstants.ERR_DUP_CATEGORY,
							new Object[] { ContactManagementConstants.CATEGORY },
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							null);
				}
			} catch (CategoryUpdateBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						null);
			} catch (EDMSDefaultsFetchBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						null);
			} catch (CategoryCreateBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						null);
			}

		}

		;
		categoryDataBean.setRenderSubjectDetails(false);
		categoryDataBean.setRenderCustomFieldDetails(false);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is used to check if the Category is deactivated and if so is
	 * it still associated with EDMS or CR or Filter.
	 * 
	 * @param categoryVO :
	 *            CategoryVO object.
	 * @return boolean : true or false.
	 */
	private boolean isInactiveCategoryAssWithCRorEDMSorFilter(
			CategoryVO categoryVO) {
		
		boolean inactive = (categoryVO.getActiveIndicator()
				.equalsIgnoreCase(ContactManagementConstants.NO)) ? true
				: false;

		boolean isAssWithCRorEDMSorFilter = false;

		if (inactive) {
			boolean isAssociatedWithEDMS = isCategoryAsscWithEDMS(categoryVO);
			boolean isAssociatedWithCR = checkCRAssociatedToCategory(categoryVO);
			boolean isAssociatedWithFilter = checkCRAssociatedToFilter(categoryVO);

			isAssWithCRorEDMSorFilter = (isAssociatedWithEDMS
					|| isAssociatedWithCR || isAssociatedWithFilter);
		}

		;
		return isAssWithCRorEDMSorFilter;
	}

	/**
	 * This method is used to check if Category is defined as default to one of
	 * the EDMS Document types.
	 * 
	 * @param categoryVO :
	 *            CategoryVO Value Object.
	 * @return boolean : true if associated else false.
	 */
	private boolean isCategoryAsscWithEDMS(CategoryVO categoryVO) {
		
		Boolean isAssociated = Boolean.FALSE;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			isAssociated = contactMaintenanceDelegate
					.isEDMSCRDefaultAssociatedWithCategory(categoryVO
							.getCategorySK());
		} catch (EDMSDefaultsFetchBusinessException e1) {
			if(logger.isErrorEnabled())
			{
				logger.error(e1.getMessage(), e1);
			}
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}

		if (isAssociated.booleanValue()) {
			setErrorMessage(
					ContactManagementConstants.ERR_CM_CATEGORY_CANNOT_DEACTIVATE,
					new Object[] { categoryVO.getCategoryDesc() },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}

		;
		return isAssociated.booleanValue();
	}

	/**
	 * This method is used to check if Category is associated with CR.
	 * 
	 * @param categoryVO :
	 *            CategoryVO Value Object.
	 * @return boolean : true if associated else false.
	 */
	private boolean checkCRAssociatedToCategory(CategoryVO categoryVO) {
		
		Boolean isAssociated = Boolean.FALSE;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			isAssociated = contactMaintenanceDelegate
					.checkCRAssociatedToCategory(categoryVO.getCategorySK());
		} catch (CategoryFetchBusinessException e1) {
			if(logger.isErrorEnabled())
			{
				logger.error(e1.getMessage(), e1);
			}
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}

		if (isAssociated.booleanValue()) {
			setErrorMessage(
					ContactManagementConstants.ERR_CM_CATEGORY_ASSC_WITH_CR,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}

		;
		return isAssociated.booleanValue();
	}

	/**
	 * This method is used to check if Category is associated with Filter.
	 * 
	 * @param categoryVO :
	 *            CategoryVO object.
	 * @return boolean : true or false.
	 */
	private boolean checkCRAssociatedToFilter(CategoryVO categoryVO) {
		
		boolean isAssociated = false;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			isAssociated = contactMaintenanceDelegate
					.checkCategoryAssociatedToFilter(categoryVO.getCategorySK());
		} catch (FilterFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}

		if (isAssociated) {
			setErrorMessage(
					ContactManagementConstants.ERR_CM_CATEGORY_ASSC_WITH_FILTER,
					new Object[] { categoryVO.getCategoryDesc() },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}
		;
		return isAssociated;
	}

	/**
	 * This method is used to check if any one of the deleted subjects is
	 * associated with EDMS Defaults.
	 * 
	 * @param mapOfDeletedSubjects :
	 *            Map of DeletedSubjects.
	 * @return boolean : true if any one of the deleted subjects is associated
	 *         with EDMS Defaults else false.
	 */
	private boolean isDeletedSubjectAsscWithEDMS(Map mapOfDeletedSubjects) {
		
		Set setOfMapEntry = mapOfDeletedSubjects.entrySet();

		boolean atleastOneAssociated = false;

		for (Iterator iter = setOfMapEntry.iterator(); iter.hasNext();) {
			Map.Entry mapEntry = (Map.Entry) iter.next();
			String subjectCode = (String) mapEntry.getKey();
			Boolean associated = (Boolean) mapEntry.getValue();

			String subjectDesc = getSubjectDescription(subjectCode);

			if (associated.booleanValue()) {
				atleastOneAssociated = true;
				setErrorMessage(
						ContactManagementConstants.ERR_CM_SUBJECT_ASSC_WITH_EDMS,
						new Object[] { subjectDesc },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						null);
			}
		}
		;
		return atleastOneAssociated;
	}

	/**
	 * This method is used to get the Subject description for the given Subject
	 * code.
	 * 
	 * @param subjectCode :
	 *            String subject code.
	 * @return String : Subject description.
	 */
	private String getSubjectDescription(String subjectCode) {
		
		List listOfCategorySubVOs = categoryDataBean.getListOfCategorySubVOs();

		String subjectDesc = null;

		for (Iterator iter = listOfCategorySubVOs.iterator(); iter.hasNext();) {
			CategorySubjectVO categorySubjectVO = (CategorySubjectVO) iter
					.next();
			String subCode = categorySubjectVO.getSubjectCode();

			if (subCode.equals(subjectCode)) {
				subjectDesc = categorySubjectVO.getSubjectDesc();
			}
		}
		;
		return subjectDesc;
	}

	/**
	 * This operation is used to delete existing Contact Management Category.
	 * 
	 * @return String : Navigation message.
	 */
	public String deleteCategory() {
		
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();

		CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();
		CorrespondenceCategory categoryDO = categoryDOConvertor
				.convertCategoryVOToDO(categoryVO);
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		Boolean deleted = Boolean.FALSE;

		try {
			/*
			 * Boolean isAssociatedWithEDMS = contactMaintenanceDelegate
			 * .isEDMSCRDefaultAssociatedWithCategory(categoryDO
			 * .getCategorySK());
			 */

			Boolean isAssociatedWithCR = contactMaintenanceDelegate
					.checkCRAssociatedToCategory(categoryDO.getCategorySK());

			/* NOTE : COMMENTED FOR THE DEFECT ID ESPRD00256561 */
			/*
			 * boolean isAssociatedWithFilter = contactMaintenanceDelegate
			 * .checkCategoryAssociatedToFilter(categoryDO.getCategorySK());
			 */

			/*
			 * if (isAssociatedWithEDMS.booleanValue()) { setErrorMessage(
			 * ContactManagementConstants.ERR_CM_CATEGORY_ASSC_WITH_EDMS, new
			 * Object[] { categoryDO.getDescription() },
			 * ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
			 * null); }
			 */
			if (isAssociatedWithCR.booleanValue()) {

				categoryDataBean.setShowDeletedMessage(false);
				setErrorMessage(
						ContactManagementConstants.ERR_CM_CATEGORY_ASSC_WITH_CR,
						new Object[] { categoryDO.getDescription() },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						null);
			}

			/* NOTE : COMMENTED FOR THE DEFECT ID ESPRD00256561 */
			/*
			 * if (isAssociatedWithFilter) { setErrorMessage(
			 * ContactManagementConstants.ERR_CM_CATEGORY_ASSC_WITH_FILTER, new
			 * Object[] { categoryDO.getDescription() },
			 * ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
			 * null); }
			 */
			/*
			 * if (!(isAssociatedWithEDMS.booleanValue() ||
			 * isAssociatedWithCR.booleanValue() || isAssociatedWithFilter))
			 */

			if (!(isAssociatedWithCR.booleanValue()))

			{
				deleted = contactMaintenanceDelegate.deleteCategory(categoryDO);

				if (deleted.booleanValue()) {
					// Code added for Defect ESPRD00735571
					int index = categoryDataBean.getCategoryRowIndex();
					categoryDataBean.getListOfCategoryVOs().remove(index);

					if (categoryDataBean.getListOfCategoryVOs().size() == 0) {
						categoryDataBean.setRenderNoDataCategory(true);
					}
					categoryDataBean.setCategoryVO(new CategoryVO());
					resetDataTablesList();
					categoryDataBean.setRenderEditCategory(false);
					categoryDataBean.setRenderAddCategory(false);
					categoryDataBean.setShowDeletedMessage(true);
				} else {
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							null);
				}
			}
		} catch (CategoryDeleteBusinessException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		} /*
		   * catch (EDMSDefaultsFetchBusinessException e) {
		   * logger.error(e.getMessage(), e);
		   * setErrorMessage(EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
		   * new Object[] {},
		   * ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, null); }
		   */catch (CategoryFetchBusinessException e) {
			   if(logger.isErrorEnabled())
			   {
				   logger.error(e.getMessage(), e);
			   }
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		} /*
		   * catch (FilterFetchBusinessException e) {
		   * logger.error(e.getMessage(), e);
		   * setErrorMessage(EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
		   * new Object[] {},
		   * ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, null); }
		   */

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to cancel the Category details Add/Edit operation.
	 * 
	 * @return String : Navigation message.
	 */
	public String cancelCategory() {
		
		categoryDataBean.setRenderAddCategory(false);
		categoryDataBean.setRenderEditCategory(false);
		this.resetCategory();

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to cancel the CustomField/Subject details Add/Edit
	 * operation.
	 * 
	 * @return String : Navigation message.
	 */
	public String cancelSubCategory() {
		
		categoryDataBean.setRenderSubjectDetails(false);
		categoryDataBean.setRenderCustomFieldDetails(false);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to reset the Category details in Add mode.
	 * 
	 * @return String : Navigation message.
	 */
	public String resetCategory() {
		
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();
		List listOfSelectedSubjects = categoryVO.getListOfSubjects();
		List listOfSelectedCustomFields = categoryVO.getListOfCustomFields();
		List listOfSelectedTemplates = categoryVO.getListOfTemplates();

		categoryVO.setCategoryDesc(null);
		categoryVO.setActiveIndicator(ContactManagementConstants.YES);
		categoryVO.setSupervisorAppReqInd(ContactManagementConstants.NO);
		categoryVO.setPriority(null);
		categoryVO.setNumOfDaysBeforeEscToHigh(null);
		categoryVO.setNumOfDaysBeforeEscToMed(null);
		categoryVO.setNumOfDaysBeforeEscToUrg(null);
		categoryVO.setNumOfDaysToKeep(null);
		categoryVO.setInactive(false);
		categoryVO.setCategoryTypeCode(null);
		listOfSelectedSubjects.clear();
		listOfSelectedCustomFields.clear();
		listOfSelectedTemplates.clear();
		categoryDataBean.setRenderSubjectDetails(false);
		categoryDataBean.setRenderCustomFieldDetails(false);

		resetDataTablesList();
		;
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to reset the Category details in Edit mode.
	 * 
	 * @return String : Navigation message.
	 */
	public String resetEditedCategory() {
		
		//int index = categoryDataBean.getCategoryRowIndex();

		CategoryVO categoryVO = categoryDataBean.getTempCategoryVO();

		CategoryVO tempCategoryVO = getCloneCategoryVO(categoryVO);

		categoryDataBean.setCategoryVO(tempCategoryVO);
		markExistingSubjsAsSelected(tempCategoryVO);
		markExistingCustomFieldsAsSelected(tempCategoryVO);
		markExistingTemplatesAsSelected(tempCategoryVO);
		categoryDataBean.setRenderSubjectDetails(false);
		categoryDataBean.setRenderCustomFieldDetails(false);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to reset the Subject and Custome Fields data table
	 * list.
	 */
	private void resetDataTablesList() {
			
		List listOfCategorySubVOs = categoryDataBean.getListOfCategorySubVOs();
		List listOfCategoryCustomFldVOs = categoryDataBean
				.getListOfCategoryCustomFldVOs();
		List listOfCategoryTemplateVOs = categoryDataBean
				.getListOfCategoryTemplateVOs();

		for (Iterator iter = listOfCategorySubVOs.iterator(); iter.hasNext();) {
			CategorySubjectVO categorySubjectVO = (CategorySubjectVO) iter
					.next();
			categorySubjectVO
					.setIncludeIndicator(ContactManagementConstants.NO);
			 // code Added for Defect ESPRD00795947 - start
			categorySubjectVO.setIncludeIndicatorforimage(false);
			// code Added for Defect ESPRD00795947 - END
		}

		for (Iterator iter = listOfCategoryCustomFldVOs.iterator(); iter
				.hasNext();) {
			CategoryCustomFieldsVO categoryCustomFieldsVO = (CategoryCustomFieldsVO) iter
					.next();
			
			//Below code is modified for changing the activeIncludeIndicator variable to String type for the Defect ESPRD00802214.
			
			/*categoryCustomFieldsVO.setIncludeIndicator(false);*/
			categoryCustomFieldsVO.setIncludeIndicator(ContactManagementConstants.NO);
			categoryCustomFieldsVO.setIncludeIndicatorImage(false);
		}
		for (Iterator iter = listOfCategoryTemplateVOs.iterator(); iter
				.hasNext();) {
			CategoryLetterTemplateVO categoryTemplatesVO = (CategoryLetterTemplateVO) iter
					.next();
			categoryTemplatesVO.setIncludeTemplate(false);
		}

		;
	}

	/**
	 * This operation is used to create list of CategorySubjectVO object based
	 * on the Subject Valid Value List - refSubjectList.
	 */
	private void createListOfCategorySubVOs() {
		
		List subjectValidValueList = categoryDataBean.getRefSubjectList();
		List listOfCategorySubVOs = categoryDataBean.getListOfCategorySubVOs();

		/*
		 * Remove already present valid values if any present and get again as
		 * some more valid values for Subjects could have been added at runtime.
		 */
		listOfCategorySubVOs.clear();

		for (Iterator iter = subjectValidValueList.iterator(); iter.hasNext();) {
			ReferenceServiceVO refServiceVO = (ReferenceServiceVO) iter.next();

			// as per Mehdi's suggestion removed the condition for active system
			// lists
			CategorySubjectVO categorySubjectVO = new CategorySubjectVO();
			categorySubjectVO.setSubjectCode(refServiceVO.getValidValueCode());
			//For Fixing Defect ESPRD00586331
			//categorySubjectVO.setSubjectDesc(refServiceVO.getLongDescription());
			categorySubjectVO.setSubjectDesc(refServiceVO.getValidValueCode()
					+ "-" + refServiceVO.getShortDescription());

			listOfCategorySubVOs.add(categorySubjectVO);

			categoryDataBean.setRenderNoDataSub(false);
		}

		;
	}

	/**
	 * This operation is used to create list of CategoryCustomFieldsVO object.
	 */
	private void createListOfCategoryCustomFieldsVO() {
		

		List listOfCustomFields = null;
		List listOfCategoryCustomFieldsVO = categoryDataBean
				.getListOfCategoryCustomFldVOs();

		/*
		 * Remove already present Custom Fields if any present and get again as
		 * some more Custom Fields could have been added at runtime.
		 */
		listOfCategoryCustomFieldsVO.clear();

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			listOfCustomFields = contactMaintenanceDelegate
					.getAllCustomFields(ContactManagementConstants.G_CR_TB);

			if (listOfCustomFields != null) {
				for (Iterator iter = listOfCustomFields.iterator(); iter
						.hasNext();) {
					CustomField customField = (CustomField) iter.next();
					CategoryCustomFieldsVO categoryCustomFieldsVO = new CategoryCustomFieldsVO();

					/*logger.info("customField.getCustomFieldSK() "
							+ customField.getCustomFieldSK());*/
					categoryCustomFieldsVO.setCustomFieldSK(customField
							.getCustomFieldSK());

					categoryCustomFieldsVO.setColumnDesc(customField
							.getDescription());
					categoryCustomFieldsVO.setDataType(getDescriptionFromVV(
							customField.getDataTypeCode(), categoryDataBean
									.getRefCustomFieldDataTypeList()));
					if (customField.getTotalLengthInBytes() != null) {
						categoryCustomFieldsVO.setFieldLength(customField
								.getTotalLengthInBytes().intValue());
					}
					if (customField.getRequiredValueIndicator() != null) {
						categoryCustomFieldsVO.setCfRequired(customField
								.getRequiredValueIndicator().booleanValue());
					}
					if (customField.getValueProtectedIndicator() != null) {
						categoryCustomFieldsVO.setCfProtectedOnSave(customField
								.getValueProtectedIndicator().booleanValue());
					}

					listOfCategoryCustomFieldsVO.add(categoryCustomFieldsVO);
				}

				categoryDataBean.setRenderNoDataCF(false);
			}
		} catch (CustomFieldFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}
		;
	}

	/**
	 * This operation is used to create list of CategoryCustomFieldsVO object.
	 */
	private void createListOfCategoryTemplatesVO() {
		
		List listOfTemplates = null;
		List listOfCategoryTemplatesVO = categoryDataBean
				.getListOfCategoryTemplateVOs();

		/*
		 * Remove already present Templates if any present and get again as some
		 * more Tempaltes could have been added at runtime.
		 */
		listOfCategoryTemplatesVO.clear();

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			listOfTemplates = contactMaintenanceDelegate.getAllTemplates();
			//	logger.debug("Category Templates Size from DB:"
			//			+ listOfTemplates.size());
			if (listOfTemplates != null) {
				if(logger.isDebugEnabled())
				{
					logger.debug("Category Templates Size from DB:"	+ listOfTemplates.size());
				}
				for (Iterator iter = listOfTemplates.iterator(); iter.hasNext();) {
					LetterTemplate templateDO = (LetterTemplate) iter.next();
					CategoryLetterTemplateVO categoryTempaltesVO = new CategoryLetterTemplateVO();
					categoryTempaltesVO.setLetterTemplateKeyData(templateDO
							.getLetterTemplateKeyData());
					/*
					 * logger.debug("TempKeydata while loading templates:" +
					 * categoryTempaltesVO.getLetterTemplateKeyData());
					 */
					if (templateDO.getName() != null) {
						categoryTempaltesVO.setTemplate(templateDO.getName());
					}
					if (templateDO.getDescription() != null) {
						categoryTempaltesVO.setTemplateDescription(templateDO
								.getDescription());
					}
					//String req = templateDO.getDefaultSupervisorRevwReqInd()
					//		.toString();
					//String opt = templateDO.getOptionalTextAllowIndicator()
					//		.toString();
					/*
					 * logger .debug("Version number in Doconvertor
					 * caseTypeLetterTemplateVO:" +
					 * categoryTempaltesVO.getVersionNo());
					 */
					if (templateDO.getDefaultSupervisorRevwReqInd() != null
							&& templateDO.getDefaultSupervisorRevwReqInd()
									.booleanValue()) {
						categoryTempaltesVO.setSupervisorApprRqd(true);
					} else {
						categoryTempaltesVO.setSupervisorApprRqd(false);
					}
					if (templateDO.getOptionalTextAllowIndicator() != null
							&& templateDO.getOptionalTextAllowIndicator()
									.booleanValue()) {
						categoryTempaltesVO.setOptionalText(true);
					} else {
						categoryTempaltesVO.setOptionalText(false);

					}

					/*logger.debug("Requiredindicator fromDo when req ia y :"
							+ categoryTempaltesVO.getSupervisorApprRqd());

					logger.debug("Required indicator from vo:"
							+ categoryTempaltesVO.getSupervisorApprRqd());
					logger.debug("Option Indicator fromVo:"
							+ categoryTempaltesVO.getOptionalText());*/

					listOfCategoryTemplatesVO.add(categoryTempaltesVO);
				}
				categoryDataBean.setRenderNoDataTemplate(true);
				if(logger.isDebugEnabled())
				{
					logger.debug("listOfCategoryTemplatesVO size :"	+ listOfCategoryTemplatesVO.size());
					logger.debug("Nodata value is:"	+ categoryDataBean.isRenderNoDataTemplate());
				}

			} else {
				categoryDataBean.setRenderNoDataTemplate(false);
			}
		} catch (Exception e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}

		;

	}

	/**
	 * This operation is used to validate the Category details.
	 * 
	 * @return boolean : true if valid Category else false.
	 */
	private boolean validateCategory() {
		boolean validCategoryDesc = verifyDescription();
		boolean validDays = validateDays();
		//	boolean validateBusinessUnit = validateBusinessUnit();

		return (validCategoryDesc && validDays);
	}

	/**
	 * This method is used to verify the description entered for the Category.
	 * 
	 * @return boolean : true if valid description else false.
	 */
	private boolean verifyDescription() {
		boolean validCategoryDesc = true;

		String categoryDesc = getCategoryDataBean().getCategoryVO()
				.getCategoryDesc();

		if (isNullOrEmptyField(categoryDesc)) {
			validCategoryDesc = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.DESCRIPTION },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.CAT_DESC);
		} else if (!EnterpriseCommonValidator
				.validateAlphaSpecialChars(categoryDesc.trim())) {
			validCategoryDesc = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_INVALID,
					new Object[] { ContactManagementConstants.DESCRIPTION },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.CAT_DESC);
		}

		;
		return validCategoryDesc;
	}

	/**
	 * This operation is used to validate the days fields present in the
	 * defaults tabs.
	 * 
	 * @return boolean : true if valid days else false.
	 */
	private boolean validateDays() {
		
		boolean validDaysToKeep = true;
		boolean validDaysToMedium = true;
		boolean validDaysToHigh = true;
		boolean validDaysToUrgent = true;

		String daysToKeep = getCategoryDataBean().getCategoryVO()
				.getNumOfDaysToKeep();
		String daysToMedium = getCategoryDataBean().getCategoryVO()
				.getNumOfDaysBeforeEscToMed();
		String daysToHigh = getCategoryDataBean().getCategoryVO()
				.getNumOfDaysBeforeEscToHigh();
		String daysToUrgent = getCategoryDataBean().getCategoryVO()
				.getNumOfDaysBeforeEscToUrg();

		validDaysToKeep = isValidDays(
				daysToKeep,
				ContactManagementConstants.DAYS_TO_KEEP_CLOSED_ITEMS_IN_WATCHLIST,
				ContactManagementConstants.DAYS_TO_KEEP_ID);
		validDaysToMedium = isValidDays(daysToMedium,
				ContactManagementConstants.DAYS_BEFORE_ESCALATING_TO_MED,
				ContactManagementConstants.DAYS_TO_ESC_MED_ID);
		validDaysToHigh = isValidDays(daysToHigh,
				ContactManagementConstants.DAYS_BEFORE_ESCALATING_TO_HIGH,
				ContactManagementConstants.DAYS_TO_ESC_HIGH_ID);
		validDaysToUrgent = isValidDays(daysToUrgent,
				ContactManagementConstants.DAYS_BEFORE_ESCALATING_TO_URG,
				ContactManagementConstants.DAYS_TO_ESC_URG_ID);

		;
		return (validDaysToKeep && validDaysToMedium && validDaysToHigh && validDaysToUrgent);
	}

	/**
	 * This operation is used to check if the given days are valid or not.
	 * 
	 * @param days :
	 *            days to be checked.
	 * @param fieldName :
	 *            Name of the UI Component.
	 * @param fieldId :
	 *            Id of the UI Component.
	 * @return boolean : true if valid days else false.
	 */
	private boolean isValidDays(String days, String fieldName, String fieldId) {
		boolean validDays = true;

		if (!isNullOrEmptyField(days)) {
			validDays = EnterpriseCommonValidator.validateNumeric(days);
			if (!validDays) {
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_INTEGER,
						new Object[] { fieldName },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						fieldId);
			}
		}

		;
		return validDays;
	}

	/**
	 * This operation is used to associate selected subjects to the Category VO.
	 * In Add mode this operation adds the selected subjects to the Category
	 * VO's selected subjects list. In Edit mode this operation also remove any
	 * subjects which are deselected and does not add already added subjects.
	 */
	private void associateSubjects() {
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();
		List listOfCategorySubVOs = categoryDataBean.getListOfCategorySubVOs();
		//hash map performance issue code change
		//categoryDataBean.getMapOfDeletedSubjects().clear();

		for (Iterator iter = listOfCategorySubVOs.iterator(); iter.hasNext();) {
			CategorySubjectVO categorySubjectVO = (CategorySubjectVO) iter
					.next();

			List listOfSelectedSubject = categoryVO.getListOfSubjects();

			boolean alreadyExists = false;

			if (categoryDataBean.isRenderEditCategory()) {
				alreadyExists = removeDeselectedSubjects(categorySubjectVO,
						listOfSelectedSubject);
			}

			if (ContactManagementConstants.YES
					.equalsIgnoreCase(categorySubjectVO.getIncludeIndicator())
					&& !alreadyExists) {
				CategorySubjectVO catSubVO = getCloneCategorySubjectVO(categorySubjectVO);

				listOfSelectedSubject.add(catSubVO);
				categorySubjectVO
						.setIncludeIndicator(ContactManagementConstants.NO);
			}
		}
	}

	/**
	 * This operation is used to associate selected Custom Fields to the
	 * Category VO. In Add mode this operation adds the selected Custom Fields
	 * to the Category VO's selected Custom Fields list. In Edit mode this
	 * operation also remove any Custom Fields which are deselected and does not
	 * add already added Custom Fields.
	 */
	private void associateCustomFields() {
	
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();
		List listOfCategoryCustFieldVOs = categoryDataBean
				.getListOfCategoryCustomFldVOs();
		//categoryDataBean.getMapOfDeletedCustomFields().clear();

		for (Iterator iter = listOfCategoryCustFieldVOs.iterator(); iter
				.hasNext();) {
			CategoryCustomFieldsVO categoryCustomFieldsVO = (CategoryCustomFieldsVO) iter
					.next();

			List listOfSelectedCustomFields = categoryVO
					.getListOfCustomFields();

			boolean alreadyExists = false;

			if (categoryDataBean.isRenderEditCategory()) {
				alreadyExists = removeDeselectedCustomFields(
						categoryCustomFieldsVO, listOfSelectedCustomFields);
			}
			//Below code is modified for changing the activeIncludeIndicator variable to String type for the Defect ESPRD00802214.
			/*if (categoryCustomFieldsVO.isIncludeIndicator() && !alreadyExists)*/ 
			if (ContactManagementConstants.YES
					.equalsIgnoreCase(categoryCustomFieldsVO.getIncludeIndicator()) && !alreadyExists){
				CategoryCustomFieldsVO catCustomFieldVO = getCloneCategoryCustomFieldVO(categoryCustomFieldsVO);

				/*logger.info("catCustomFieldVO.getCustomFieldSK "
						+ catCustomFieldVO.getCustomFieldSK());
				logger.info("catCustomFieldVO.getColumnDesc() "
						+ catCustomFieldVO.getColumnDesc());
				logger.info("catCustomFieldVO.getVersionNo() "
						+ catCustomFieldVO.getVersionNo());*/

				listOfSelectedCustomFields.add(catCustomFieldVO);
				/*categoryCustomFieldsVO.setIncludeIndicator(false);*/
				categoryCustomFieldsVO.setIncludeIndicator(ContactManagementConstants.NO);
			}
		}

		;

	}

	/**
	 * This operation is used to associate selected Custom Fields to the
	 * Category VO. In Add mode this operation adds the selected Custom Fields
	 * to the Category VO's selected Custom Fields list. In Edit mode this
	 * operation also remove any Custom Fields which are deselected and does not
	 * add already added Custom Fields.
	 */
	private void associateTemplates() {
		
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();
		List listOfCategoryTemplates = categoryDataBean
				.getListOfCategoryTemplateVOs();
		//categoryDataBean.getMapOfDeletedTemplates().clear();
		for (Iterator iter = listOfCategoryTemplates.iterator(); iter.hasNext();) {
			CategoryLetterTemplateVO categoryTemplateVO = (CategoryLetterTemplateVO) iter
					.next();

			List listOfSelectedTemplates = categoryVO.getListOfTemplates();
			/*logger.debug("listOfSelectedTempaltes Size in AssociateTempalte:"
					+ listOfSelectedTemplates.size());*/
			boolean alreadyExists = false;
			if (categoryDataBean.isRenderEditCategory()) {

				/*logger.debug("In Editmode Associate template");
				logger
						.debug("Version numberin Edit before calling removeDeselectedTemplates:"
								+ categoryTemplateVO.getVersionNo());*/
				alreadyExists = removeDeselectedTemplates(categoryTemplateVO,
						listOfSelectedTemplates);

			}
			if (categoryTemplateVO.isIncludeTemplate() && !alreadyExists) {
				/*logger.debug("if include true in Associate");*/
				CategoryLetterTemplateVO catTemplateVO = getCloneCategoryTemplateVO(categoryTemplateVO);
				/*logger.debug("catTemplateVO.getLetterTemplateKeyData"
						+ catTemplateVO.getLetterTemplateKeyData());
				logger.debug("catTemplateVO.getVersionNo:"
						+ catTemplateVO.getVersionNo());*/
				listOfSelectedTemplates.add(catTemplateVO);
				categoryTemplateVO.setIncludeTemplate(false);
			}
		}
		;
		
	}

	/**
	 * This operation is used to remove any subjects which are deselected from
	 * the list of selected subjects.
	 * 
	 * @param categorySubjectVO :
	 *            CategorySubjectVO object.
	 * @param listOfSelectedSubject :
	 *            List of already selected subjects.
	 * @return boolean : true if the element already existed else false.
	 */
	private boolean removeDeselectedSubjects(
			CategorySubjectVO categorySubjectVO, List listOfSelectedSubject) {
		
		boolean alreadyExists = listOfSelectedSubject
				.contains(categorySubjectVO);

		List listOfDeletedSubjects = categoryDataBean.getCategoryVO()
				.getListOfDeletedSubjects();

		if (alreadyExists
				&& categorySubjectVO.getIncludeIndicator().equalsIgnoreCase(
						ContactManagementConstants.NO)) {
			/*hash map performance issue code change
			 * Map mapOfDeletedSubjects = categoryDataBean
					.getMapOfDeletedSubjects();*/
			listOfSelectedSubject.remove(categorySubjectVO);
			listOfDeletedSubjects.add(categorySubjectVO);
			/*hash map performance issue code change
			 * mapOfDeletedSubjects.put(categorySubjectVO.getSubjectCode(),
					Boolean.FALSE);*/
		}
		if (alreadyExists
				&& ContactManagementConstants.YES
						.equalsIgnoreCase(categorySubjectVO
								.getIncludeIndicator())) {
			categorySubjectVO
					.setIncludeIndicator(ContactManagementConstants.NO);
		}
		return alreadyExists;
	}

	/**
	 * This operation is used to remove any custom fields which are deselected
	 * from the list of selected custom fields.
	 * 
	 * @param categoryCustomFieldsVO :
	 *            CategorySubjectVO object.
	 * @param listOfSelectedCustomFields :
	 *            List of already selected custom fields.
	 * @return boolean : true if the element already existed else false.
	 */
	private boolean removeDeselectedCustomFields(
			CategoryCustomFieldsVO categoryCustomFieldsVO,
			List listOfSelectedCustomFields) {
	
		boolean alreadyExists = listOfSelectedCustomFields
				.contains(categoryCustomFieldsVO);

		List listOfDeletedCustomFields = categoryDataBean.getCategoryVO()
				.getListOfDeletedCustomFields();
		//Below code is modified for changing the activeIncludeIndicator variable to String type for the Defect ESPRD00802214.
		/*if (alreadyExists && !categoryCustomFieldsVO.isIncludeIndicator())*/
		if (alreadyExists && !(ContactManagementConstants.YES.equalsIgnoreCase(categoryCustomFieldsVO.getIncludeIndicator()))){
			/*hash map performance issue code change
			 * Map mapOfDeletedCustomFields = categoryDataBean
					.getMapOfDeletedCustomFields();*/
			if(logger.isInfoEnabled())
			{
				logger.info("categoryCustomFieldsVO customSk1$$ " + categoryCustomFieldsVO.getCustomFieldSK());
				logger.info("categoryCustomFieldsVO versionno1 $$ " + categoryCustomFieldsVO.getVersionNo());
			}

			int index = listOfSelectedCustomFields
					.indexOf(categoryCustomFieldsVO);
			if(logger.isInfoEnabled())
			{
				logger.info("index " + index);
			}
			categoryCustomFieldsVO = (CategoryCustomFieldsVO) listOfSelectedCustomFields
					.get(index);
			if(logger.isInfoEnabled())
			{
				logger.info("categoryCustomFieldsVO customSk2## "	+ categoryCustomFieldsVO.getCustomFieldSK());
				logger.info("categoryCustomFieldsVO versionno2## "	+ categoryCustomFieldsVO.getVersionNo());
			}

			listOfSelectedCustomFields.remove(categoryCustomFieldsVO);
			listOfDeletedCustomFields.add(categoryCustomFieldsVO);
			/* hash map performance issue code change
			 * mapOfDeletedCustomFields.put(categoryCustomFieldsVO
					.getCustomFieldSK(), Boolean.FALSE);*/
		}
		//Below code is modified for changing the activeIncludeIndicator variable to String type for the Defect ESPRD00802214.
		/*if (alreadyExists && categoryCustomFieldsVO.isIncludeIndicator()) {
			categoryCustomFieldsVO.setIncludeIndicator(false);*/
		if (alreadyExists && ContactManagementConstants.YES.equalsIgnoreCase(categoryCustomFieldsVO.getIncludeIndicator())) {
			categoryCustomFieldsVO.setIncludeIndicator(ContactManagementConstants.NO);
		}
		;
		return alreadyExists;
	}

	/**
	 * This operation is used to remove Templates which are deselected from the
	 * list of selected Templates.
	 * 
	 * @param categoryTemplateVO :
	 *            categoryTemplateVO object.
	 * @param listOfSelectedTemplates :
	 *            List of already selected template.
	 * @return boolean : true if the element already existed else false.
	 */
	private boolean removeDeselectedTemplates(
			CategoryLetterTemplateVO categoryTemplateVO,
			List listOfSelectedTemplates) {
		boolean alreadyExists = listOfSelectedTemplates
				.contains(categoryTemplateVO);
		if (logger.isDebugEnabled())
		{
			logger.debug("alreadyExists in removeDeselectedTemplates" + alreadyExists);
		}
		List listOfDeletedTemplates = categoryDataBean.getCategoryVO()
				.getListOfDeletedTemplates();
		if (alreadyExists && !categoryTemplateVO.isIncludeTemplate()) {
			if (logger.isDebugEnabled())
			{
				
			/*hash map performance issue code change
			 * Map mapOfDeletedTemplates = categoryDataBean
					.getMapOfDeletedTemplates();*/
				logger.debug("CategoryTemplateVo tempalteKeyData:1"	+ categoryTemplateVO.getLetterTemplateKeyData());
				logger.debug("CategoryTemplateVo Version Number:1"	+ categoryTemplateVO.getVersionNo());
			}

			int index = listOfSelectedTemplates.indexOf(categoryTemplateVO);
			if (logger.isDebugEnabled())
			{
				logger.debug("Index in removeDeselected templates:" + index);
			}
			categoryTemplateVO = (CategoryLetterTemplateVO) listOfSelectedTemplates
					.get(index);
			if (logger.isDebugEnabled())
			{
				logger.debug("CategoryTemplateVo tempalteKeyData:2"
					+ categoryTemplateVO.getLetterTemplateKeyData());
				logger.debug("CategoryTemplateVo Version Number:2"
					+ categoryTemplateVO.getVersionNo());
			}

			listOfSelectedTemplates.remove(categoryTemplateVO);
			// Below code is modified for defect ESPRD00897244 to deassociate the template from category.
			listOfDeletedTemplates.add(categoryTemplateVO);
			//listOfSelectedTemplates.add(categoryTemplateVO);
			if (logger.isDebugEnabled())
			{
				logger.debug("deletedtemplate Size from CategoryVo in removeDeselect:" + listOfDeletedTemplates.size());
			}
			/*hash map performance issue code change
			 * mapOfDeletedTemplates.put(categoryTemplateVO
					.getLetterTemplateKeyData(), Boolean.FALSE);*/

		}
		if (alreadyExists && categoryTemplateVO.isIncludeTemplate()) {
			categoryTemplateVO.setIncludeTemplate(false);
		}
		;
		return alreadyExists;
	}

	/**
	 * This operation is used to the get the Clone of CategoryVO which is
	 * selected for edit operation.
	 * 
	 * @param categoryVO :
	 *            CategoryVO selected for edit operation.
	 * @return CategoryVO : Cloned CategoryVO object.
	 */
	private CategoryVO getCloneCategoryVO(CategoryVO categoryVO) {
		
		CategoryVO tempCategoryVO = null;

		try {
			tempCategoryVO = (CategoryVO) categoryVO.clone();
		} catch (CloneNotSupportedException e) {
			if (logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}

		tempCategoryVO = (tempCategoryVO == null) ? new CategoryVO()
				: tempCategoryVO;
		;
		return tempCategoryVO;
	}

	/**
	 * This operation is used to the get the Clone of CategorySubjectVO which is
	 * to be included in the list of selected Subjects.
	 * 
	 * @param categorySubjectVO :
	 *            CategorySubjectVO selected.
	 * @return CategorySubjectVO : Cloned CategorySubjectVO object.
	 */
	private CategorySubjectVO getCloneCategorySubjectVO(
			CategorySubjectVO categorySubjectVO) {
		

		CategorySubjectVO catSubVO = null;

		try {
			catSubVO = (CategorySubjectVO) categorySubjectVO.clone();

		} catch (CloneNotSupportedException e) {
			if (logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}

		catSubVO = (catSubVO == null) ? new CategorySubjectVO() : catSubVO;
		;
		return catSubVO;
	}

	/**
	 * This operation is used to the get the Clone of CategoryCustomFieldsVO
	 * which is to be included in the list of selected Custom Fields.
	 * 
	 * @param categoryCustomFieldsVO :
	 *            CategoryCustomFieldsVO selected.
	 * @return CategoryCustomFieldsVO : Cloned CategoryCustomFieldsVO object.
	 */
	private CategoryCustomFieldsVO getCloneCategoryCustomFieldVO(
			CategoryCustomFieldsVO categoryCustomFieldsVO) {
		
		CategoryCustomFieldsVO catCustomFieldVO = null;

		try {
			catCustomFieldVO = (CategoryCustomFieldsVO) categoryCustomFieldsVO
					.clone();

			if (catCustomFieldVO != null) {
				if (logger.isInfoEnabled())
				{
					logger.info("catCustomFieldVO.getCustomFieldSK "+ catCustomFieldVO.getCustomFieldSK());
					logger.info("catCustomFieldVO.getColumnDesc() "	+ catCustomFieldVO.getColumnDesc());
				}
			} else {
				if (logger.isInfoEnabled())
				{
					logger.info("catCustomFieldVO " + catCustomFieldVO);
				}
			}

		} catch (CloneNotSupportedException e) {
			if (logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}

		catCustomFieldVO = (catCustomFieldVO == null) ? new CategoryCustomFieldsVO()
				: catCustomFieldVO;
		;
		return catCustomFieldVO;
	}

	/**
	 * This operation is used to the get the Clone of CategoryLetterTemplateVO
	 * which is to be included in the list of selected Custom Fields.
	 * 
	 * @param categoryTemplatesVO :
	 *            categoryTemplatesVO selected.
	 * @return CategoryLetterTemplateVO : Cloned CategoryLetterTemplateVO
	 *         object.
	 */
	private CategoryLetterTemplateVO getCloneCategoryTemplateVO(
			CategoryLetterTemplateVO categoryTemplatesVO) {
		
		CategoryLetterTemplateVO catTemplateVO = null;

		try {
			catTemplateVO = (CategoryLetterTemplateVO) categoryTemplatesVO
					.clone();
			
				if (catTemplateVO != null) {
					if (logger.isInfoEnabled())
					{
						logger.info("catTemplateVO.getLetterTemplateKeyData InClone "+ catTemplateVO.getLetterTemplateKeyData());
						logger.info("catTemplateVO.getTemplateDescription InClone"	+ catTemplateVO.getTemplateDescription());
					}
					if (logger.isDebugEnabled())
					{
						logger.debug("catTemplateVO.getCategorySK InClone"	+ catTemplateVO.getCategorySK());
					}
					} else {
						if (logger.isInfoEnabled())
						{
							logger.info("catTemplateVO " + catTemplateVO);
						}
				}
			

		} catch (CloneNotSupportedException e) {
			if (logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}

		catTemplateVO = (catTemplateVO == null) ? new CategoryLetterTemplateVO()
				: catTemplateVO;
		;
		return catTemplateVO;
	}

	/**
	 * This operation is used to mark the existing Subjects in the Category
	 * categoryVO as selected in the Subjects list.
	 * 
	 * @param categoryVO :
	 *            CategoryVO for which the list is being created.
	 */
	private void markExistingSubjsAsSelected(CategoryVO categoryVO) {
		
		List listOfCategorySubVOs = categoryDataBean.getListOfCategorySubVOs();
		List listOfSelectedSubVOs = categoryVO.getListOfSubjects();

		for (Iterator iter = listOfCategorySubVOs.iterator(); iter.hasNext();) {
			CategorySubjectVO categorySubjectVO = (CategorySubjectVO) iter
					.next();

			boolean doesContains = listOfSelectedSubVOs
					.contains(categorySubjectVO);

			if (doesContains) {
				categorySubjectVO
						.setIncludeIndicator(ContactManagementConstants.YES);
				categorySubjectVO.setIncludeIndicatorforimage(true);
			} else {
				categorySubjectVO
						.setIncludeIndicator(ContactManagementConstants.NO);
				categorySubjectVO.setIncludeIndicatorforimage(false);
			}
		}

		;
	}

	/**
	 * @param categoryVO
	 *            CategoryVO for which the list is being created.
	 */
	private void markExistingCustomFieldsAsSelected(CategoryVO categoryVO) {
		
		List listOfCatCustomFieldVOs = categoryDataBean
				.getListOfCategoryCustomFldVOs();
		List listOfSelectedCustomFldsVOs = categoryVO.getListOfCustomFields();

		for (Iterator iter = listOfCatCustomFieldVOs.iterator(); iter.hasNext();) {
			CategoryCustomFieldsVO categoryCustomFieldsVO = (CategoryCustomFieldsVO) iter
					.next();

			boolean doesContains = listOfSelectedCustomFldsVOs
					.contains(categoryCustomFieldsVO);

			if (doesContains) {
				//Below code is modified for changing the activeIncludeIndicator variable to String type for the Defect ESPRD00802214.
				/*categoryCustomFieldsVO.setIncludeIndicator(true);*/
				categoryCustomFieldsVO.setIncludeIndicator(ContactManagementConstants.YES);
				categoryCustomFieldsVO.setIncludeIndicatorImage(true);
			} else {
				/*categoryCustomFieldsVO.setIncludeIndicator(false);*/
				categoryCustomFieldsVO.setIncludeIndicator(ContactManagementConstants.NO);
				categoryCustomFieldsVO.setIncludeIndicatorImage(false);
			}
		}
		// TODO remove
		for (Iterator iter = listOfSelectedCustomFldsVOs.iterator(); iter
				.hasNext();) {
			CategoryCustomFieldsVO cat = (CategoryCustomFieldsVO) iter.next();
			/*logger.info("CF SK " + cat.getCustomFieldSK());
			logger.info("CF version " + cat.getVersionNo());*/
		}

		;
	}

	/**
	 * @param categoryVO :
	 *            CategoryVO for which the list is being created.
	 */
	private void markExistingTemplatesAsSelected(CategoryVO categoryVO) {
		List listOfCatTempalteVOs = categoryDataBean
				.getListOfCategoryTemplateVOs();
		if (logger.isDebugEnabled())
		{
			logger.debug("Total Tempaltes" + listOfCatTempalteVOs.size());
		}
		List listOfSelectedTemplateVOs = categoryVO.getListOfTemplates();
		if (logger.isDebugEnabled())
		{
			logger.debug("Selected Tempaltes:" + listOfSelectedTemplateVOs.size());
		}

		for (Iterator iter = listOfCatTempalteVOs.iterator(); iter.hasNext();) {
			CategoryLetterTemplateVO categoryTemplatesVO = (CategoryLetterTemplateVO) iter
					.next();

			boolean doesContains = listOfSelectedTemplateVOs
					.contains(categoryTemplatesVO);
			/*logger.debug("doesContains :" + doesContains);*/
			if (doesContains) {
				categoryTemplatesVO.setIncludeTemplate(true);
			} else {
				categoryTemplatesVO.setIncludeTemplate(false);
			}
		}
		// TODO remove
		for (Iterator iter = listOfSelectedTemplateVOs.iterator(); iter
				.hasNext();) {
			CategoryLetterTemplateVO cat = (CategoryLetterTemplateVO) iter
					.next();
			/*logger.info("Tempalte version Inside Mark " + cat.getVersionNo());
			logger.debug("CategorySk Inside MArk:" + cat.getCategorySK());*/
		}
		
		/*
		 * if (listOfCatTempalteVOs != null && listOfSelectedTemplateVOs !=
		 * null) { allTemplatesSize = listOfCatTempalteVOs.size();
		 * assignedTempaltesSize = listOfSelectedTemplateVOs.size(); } for (int
		 * i = 0; i < allTemplatesSize; i++) { CategoryLetterTemplateVO
		 * allTemplatesVo =
		 * (CategoryLetterTemplateVO)listOfCatTempalteVOs.get(i); for (int j =
		 * 0; j < assignedTempaltesSize; j++) { CategoryLetterTemplateVO
		 * assignedTemplatesVO =
		 * (CategoryLetterTemplateVO)listOfSelectedTemplateVOs.get(j); String
		 * keydata = allTemplatesVo.getLetterTemplateKeyData();
		 * logger.debug("KeyData:" + keydata); if
		 * (keydata.equalsIgnoreCase(assignedTemplatesVO
		 * .getLetterTemplateKeyData())) {
		 * allTemplatesVo.setIncludeTemplate(true); break; } } }
		 */

		;
	}

	/**
	 * This operation is used to get all the Categories sorted on certain column
	 * and order.
	 * 
	 * @param event :
	 *            ActionEvent object.
	 */
	public void getAllSortedCategories(ActionEvent event) {
		
		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		categoryDataBean.setCategorySort(true);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);
		categoryDataBean.setStartIndexForCatgry(0);
		if (logger.isDebugEnabled())
		{
			logger.debug("sortColumn::::::::::" + sortColumn);
			logger.debug("sortColumn::::::::::" + sortOrder);
		}
		categoryDataBean.setImageRender(event.getComponent().getId());

		CategorySearchCriteriaVO categorySearchCriteriaVO = categoryDataBean
				.getCategorySearchCriteriaVO();

		categorySearchCriteriaVO.setSortColumn(sortColumn);

		if (ContactManagementConstants.ASCENDING.equalsIgnoreCase(sortOrder)) {
			
			categorySearchCriteriaVO.setAscending(true);
		}
		if (ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
			
			categorySearchCriteriaVO.setAscending(false);

		}

		categoryComparator(sortColumn, sortOrder, categoryDataBean
				.getListOfCategoryVOs());
		//For Fixing Defect ESPRD00587673
		categoryDataBean.setFocusId("sapLabelValue");

	}

	/**
	 * This Method Sorts The data table of Codes & Indicator Section.
	 * 
	 * @param sortColumn
	 *            Takes sortColumn as Parameter
	 * @param sortOrder
	 *            Takes sortOrder as Parameter
	 * @param dataList
	 *            Takes dataList as Parameter
	 */
	public String categoryComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		if (logger.isDebugEnabled())
		{
			logger.debug("sortColumn::::::::::::::::::::: " + sortColumn);
		}
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				String first = null;
				String second = null;
				MaitainCategoryVO data1 = (MaitainCategoryVO) obj1;
				MaitainCategoryVO data2 = (MaitainCategoryVO) obj2;
				boolean ascending = false;
				if (ContactManagementConstants.ASCENDING.equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}

				if ("description".equals(sortColumn)) {
					if (null == data1.getCategoryDesc()) {
						data1
								.setCategoryDesc(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getCategoryDesc()) {
						data2
								.setCategoryDesc(ContactManagementConstants.EMPTY_STRING);
					}
					first = data1.getCategoryDesc().toLowerCase();
					second = data2.getCategoryDesc().toLowerCase();
					if (logger.isDebugEnabled())
					{
						logger.debug("First in desc&&&&&&&&&" + first);
						logger.debug("second in desc%%%%%%%%%%%%" + second);
					}

					return ascending ? data1.getCategoryDesc().toLowerCase()
							.compareTo(data2.getCategoryDesc().toLowerCase())
							: data2.getCategoryDesc().toLowerCase().compareTo(
									data1.getCategoryDesc().toLowerCase());

				}
				if ("voidIndicator".equals(sortColumn)) {
					
					if (null == data1.getActiveIndicator()) {
						data1
								.setActiveIndicator(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getActiveIndicator()) {
						data2
								.setActiveIndicator(ContactManagementConstants.EMPTY_STRING);
					}

					first = data1.getActiveIndicator().toLowerCase();
					second = data2.getActiveIndicator().toLowerCase();
					if (logger.isDebugEnabled())
					{
						logger.debug("First in desc=======" + first);
						logger.debug("second in desc=======" + second);
					}

					return ascending ? first.compareTo(second) : second
							.compareTo(first);
				}
				
				if ("categoryTypeCode".equals(sortColumn))

				{
					if (null == data1.getCategoryTypeCode()) {
						data1
								.setCategoryTypeCode(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getCategoryTypeCode()) {
						data2
								.setCategoryTypeCode(ContactManagementConstants.EMPTY_STRING);
					}

					first = data1.getCategoryTypeCode().toLowerCase();
					second = data2.getCategoryTypeCode().toLowerCase();
					if (logger.isDebugEnabled())
					{
						logger.debug("First in desc&&&&&&&&&" + first);
						logger.debug("second in desc%%%%%%%%%%%%" + second);
					}

					return ascending ? first.compareTo(second) : second
							.compareTo(first);
				}

				

				if ("supervisorReviewReqIndicator".equals(sortColumn)) {
					
					if (null == data1.getSupervisorAppReqInd()) {
						data1
								.setSupervisorAppReqInd(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getSupervisorAppReqInd()) {
						data2
								.setSupervisorAppReqInd(ContactManagementConstants.EMPTY_STRING);
					}

					first = data1.getSupervisorAppReqInd().toLowerCase();
					second = data2.getSupervisorAppReqInd().toLowerCase();
					return ascending ? first.compareTo(second) : second
							.compareTo(first);
				}

				return 0;
			}
		};
		Collections.sort(dataList, comparator);
		return "";
	}

	/**
	 * This operation is used to sort the Subjects List.
	 * 
	 * @param event :
	 *            ActionEvent object.
	 */
	public void sortSubjects(ActionEvent event) {
		
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);

		getCategoryDataBean().setImageRender(event.getComponent().getId());

		Comparator comparator = new Comparator() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 *      java.lang.Object)
			 */
			public int compare(Object obj1, Object obj2) {
				
				CategorySubjectVO catSubVO1 = (CategorySubjectVO) obj1;
				CategorySubjectVO catSubVO2 = (CategorySubjectVO) obj2;

				boolean ascending = false;
				int returnValue = 0;

				ascending = (ContactManagementConstants.ASCENDING
						.equalsIgnoreCase(sortOrder)) ? true : false;

				if (ContactManagementConstants.INCLUDE
						.equalsIgnoreCase(sortColumn)) {
					String bool1 = ContactManagementConstants.EMPTY_STRING
							+ catSubVO1.getIncludeIndicator();
					String bool2 = ContactManagementConstants.EMPTY_STRING
							+ catSubVO2.getIncludeIndicator();

					returnValue = ascending ? bool2.compareTo(bool1) : bool1
							.compareTo(bool2);
				} else if (ContactManagementConstants.SUBJECT
						.equalsIgnoreCase(sortColumn)) {
					String str1 = catSubVO1.getSubjectDesc().trim();
					String str2 = catSubVO2.getSubjectDesc().trim();

					returnValue = ascending ? str1.compareTo(str2) : str2
							.compareTo(str1);
				}

				return returnValue;
			}
		};

		if (getCategoryDataBean().getListOfCategorySubVOs() != null) {
			Collections.sort(getCategoryDataBean().getListOfCategorySubVOs(),
					comparator);
		}

		;
	}

	/**
	 * This operation is used to sort the Custom Fields List.
	 * 
	 * @param event :
	 *            ActionEvent object.
	 */
	public void sortCustomFields(ActionEvent event) {
		

		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);

		getCategoryDataBean().setImageRender(event.getComponent().getId());

		Comparator comparator = new Comparator() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 *      java.lang.Object)
			 */
			public int compare(Object obj1, Object obj2) {
				
				CategoryCustomFieldsVO catCustomFieldVO1 = (CategoryCustomFieldsVO) obj1;
				CategoryCustomFieldsVO catCustomFieldVO2 = (CategoryCustomFieldsVO) obj2;

				boolean ascending = false;
				int returnValue = 0;

				ascending = (ContactManagementConstants.ASCENDING
						.equalsIgnoreCase(sortOrder)) ? true : false;

				if (ContactManagementConstants.INCLUDE
						.equalsIgnoreCase(sortColumn)) {
					//Below code is modified for changing the activeIncludeIndicator variable to String type for the Defect ESPRD00802214.
					/*String bool1 = ContactManagementConstants.EMPTY_STRING
							+ catCustomFieldVO1.isIncludeIndicator();
					String bool2 = ContactManagementConstants.EMPTY_STRING
							+ catCustomFieldVO2.isIncludeIndicator();*/
					String bool1 = ContactManagementConstants.EMPTY_STRING
					+ catCustomFieldVO1.getIncludeIndicator();
					String bool2 = ContactManagementConstants.EMPTY_STRING
					+ catCustomFieldVO2.getIncludeIndicator();

					returnValue = ascending ? bool2.compareTo(bool1) : bool1
							.compareTo(bool2);
				} else if (ContactManagementConstants.COL_DESC
						.equalsIgnoreCase(sortColumn)) {
					String str1 = catCustomFieldVO1.getColumnDesc()
							.toLowerCase().trim();
					String str2 = catCustomFieldVO2.getColumnDesc()
							.toLowerCase().trim();

					returnValue = ascending ? str1.compareTo(str2) : str2
							.compareTo(str1);
				} else if (ContactManagementConstants.DATA_TYPE
						.equalsIgnoreCase(sortColumn)) {
					String str1 = catCustomFieldVO1.getDataType().toLowerCase()
							.trim();
					String str2 = catCustomFieldVO2.getDataType().toLowerCase()
							.trim();

					returnValue = ascending ? str1.compareTo(str2) : str2
							.compareTo(str1);
				} else if (ContactManagementConstants.FIELD_LEN
						.equalsIgnoreCase(sortColumn)) {
					//"FindBugs" issue fixed
					//Integer integer1 = new
					// Integer(catCustomFieldVO1.getFieldLength());
					Integer integer1 = Integer.valueOf(catCustomFieldVO1
							.getFieldLength());
					//"FindBugs" issue fixed
					/*
					 * Integer integer2 = new
					 * Integer(catCustomFieldVO2.getFieldLength());
					 */
					Integer integer2 = Integer.valueOf(catCustomFieldVO2
							.getFieldLength());

					returnValue = ascending ? integer1.compareTo(integer2)
							: integer2.compareTo(integer1);
				} else if (ContactManagementConstants.REQUIRED
						.equalsIgnoreCase(sortColumn)) {
					String bool1 = ContactManagementConstants.EMPTY_STRING
							+ catCustomFieldVO1.isCfRequired();
					String bool2 = ContactManagementConstants.EMPTY_STRING
							+ catCustomFieldVO2.isCfRequired();

					returnValue = ascending ? bool2.compareTo(bool1) : bool1
							.compareTo(bool2);
				} else if (ContactManagementConstants.PROC_ON_SAVE
						.equalsIgnoreCase(sortColumn)) {
					String bool1 = ContactManagementConstants.EMPTY_STRING
							+ catCustomFieldVO1.isCfProtectedOnSave();
					String bool2 = ContactManagementConstants.EMPTY_STRING
							+ catCustomFieldVO2.isCfProtectedOnSave();

					returnValue = ascending ? bool2.compareTo(bool1) : bool1
							.compareTo(bool2);
				}

				return returnValue;
			}
		};

		if (getCategoryDataBean().getListOfCategoryCustomFldVOs() != null) {
			Collections.sort(getCategoryDataBean()
					.getListOfCategoryCustomFldVOs(), comparator);
		}

		;
	}

	/**
	 * This operation is used to sort the Template List.
	 * 
	 * @param event :
	 *            ActionEvent object.
	 */
	public void sortTemplate(ActionEvent event) {
		
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);
		getCategoryDataBean().setImageRender(event.getComponent().getId());
		if (logger.isDebugEnabled())
		{
			logger.debug("sortColumn:::::::::::: " + sortColumn);
		}

		Comparator comparator = new Comparator() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 *      java.lang.Object)
			 */
			public int compare(Object obj1, Object obj2) {
				
				CategoryLetterTemplateVO catLetterTemplateVO1 = (CategoryLetterTemplateVO) obj1;
				CategoryLetterTemplateVO catLetterTemplateVO2 = (CategoryLetterTemplateVO) obj2;

				boolean ascending = false;
				int returnValue = 0;
				String str1 = "";
				String str2 = "";

				ascending = (ContactManagementConstants.ASCENDING
						.equalsIgnoreCase(sortOrder)) ? true : false;

				if ("Include Template".equals(sortColumn)) {
					str1 = ContactManagementConstants.EMPTY_STRING
							+ catLetterTemplateVO1.isIncludeTemplate();
					str2 = ContactManagementConstants.EMPTY_STRING
							+ catLetterTemplateVO2.isIncludeTemplate();
					if (logger.isDebugEnabled())
					{
						logger.debug("str1..........." + str1);
						logger.debug("str2..........." + str2);
					}

					returnValue = ascending ? str1.compareTo(str2) : str2
							.compareTo(str1);

				} else if (ContactManagementConstants.TEMPLATE_NAME
						.equalsIgnoreCase(sortColumn)) {

					if (catLetterTemplateVO1.getTemplate() != null) {
						str1 = catLetterTemplateVO1.getTemplate().toLowerCase()
								.trim();
					}
					if (catLetterTemplateVO2.getTemplate() != null) {
						str2 = catLetterTemplateVO2.getTemplate().toLowerCase()
								.trim();
					}

					returnValue = ascending ? str1.compareTo(str2) : str2
							.compareTo(str1);

				} else if (ContactManagementConstants.TEMPLATE_DESC
						.equalsIgnoreCase(sortColumn)) {

					if (catLetterTemplateVO1.getTemplateDescription() != null) {
						str1 = catLetterTemplateVO1.getTemplateDescription()
								.toLowerCase().trim();
					}
					if (catLetterTemplateVO2.getTemplateDescription() != null) {
						str2 = catLetterTemplateVO2.getTemplateDescription()
								.toLowerCase().trim();
					}

					returnValue = ascending ? str1.compareTo(str2) : str2
							.compareTo(str1);

				} else if (ContactManagementConstants.TEMPLATE_OPTIONTEXT
						.equalsIgnoreCase(sortColumn)) {

					str1 = ContactManagementConstants.EMPTY_STRING
							+ catLetterTemplateVO1.getOptionalText();
					str2 = ContactManagementConstants.EMPTY_STRING
							+ catLetterTemplateVO2.getOptionalText();
					returnValue = ascending ? str1.compareTo(str2) : str2
							.compareTo(str1);

				} else if (ContactManagementConstants.TEMPLATE_SUPERVISOR
						.equalsIgnoreCase(sortColumn)) {

					str1 = ContactManagementConstants.EMPTY_STRING
							+ catLetterTemplateVO1.getSupervisorApprRqd();
					str2 = ContactManagementConstants.EMPTY_STRING
							+ catLetterTemplateVO2.getSupervisorApprRqd();
					returnValue = ascending ? str1.compareTo(str2) : str2
							.compareTo(str1);

				}

				return returnValue;
			}
		};

		if (getCategoryDataBean().getListOfCategoryTemplateVOs() != null) {
			Collections.sort(getCategoryDataBean()
					.getListOfCategoryTemplateVOs(), comparator);
		}

		;
	}

	/**
	 * This method is used to set the categoryDataBean.
	 * 
	 * @param categoryDataBean :
	 *            The categoryDataBean to set.
	 */
	private void setCategoryDataBean(CategoryDataBean categoryDataBean) {
		this.categoryDataBean = categoryDataBean;
	}

	/**
	 * This method is used to get the Category Data Bean.
	 * 
	 * @return CategoryDataBean : CategoryDataBean object.
	 */
	public CategoryDataBean getCategoryDataBean() {
		
		FacesContext fc = FacesContext.getCurrentInstance();

		;
		return ((CategoryDataBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ ContactManagementConstants.CATEGORY_BEAN_NAME
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));

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
	private void setErrorMessage(String errorName, Object[] arguments,
			String messageBundle, String componentId) {
	
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
			UIComponent uiComponent = findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);
		}

		facesContext.addMessage(clientId, fc);

		;
	}

	/**
	 * This method is used to check if the input field is null or is equal to an
	 * empty string.
	 * 
	 * @param inputField :
	 *            String inputField.
	 * @return boolean : true if input field is null or equal to an empty string
	 *         else false.
	 */
	private boolean isNullOrEmptyField(String inputField) {
		return (inputField == null || ContactManagementConstants.EMPTY_STRING
				.equalsIgnoreCase(inputField.trim()));
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
		
		UIComponent component = null;
		UIComponent result = null;

		// Is the "base" component itself the match we are looking for?
		if (id.equals(base.getId())) {
			result = base;
		} else {
			// Search through our facets and children
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
		}
		;
		return result;
	}

	/**
	 * This operation is used to find the component in root by passing id.
	 * 
	 * @param id :
	 *            String object.
	 * @return UIComponent : UIComponent object.
	 */
	public UIComponent findComponentInRoot(String id) {
		

		UIComponent component = null;
		FacesContext context = FacesContext.getCurrentInstance();

		if (context != null) {
			UIComponent root = context.getViewRoot();
			component = findComponent(root, id);
		}
		;
		return component;
	}

	/**
	 * This operation is used to get the reference data.
	 */
	public void getReferenceData() {
		
		List referenceList = new ArrayList();
		Map referenceMap = null;

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//"FindBugs" issue fixed
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;
		createInputCriterias(referenceList);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			if (referenceMap != null) {
				List refSubjectList = getReferenceList(referenceMap,
						FunctionalAreaConstants.CONTACT_MGMT,
						ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);

				List refPriorityList = getReferenceList(referenceMap,
						FunctionalAreaConstants.CONTACT_MGMT,
						ReferenceServiceDataConstants.G_CR_PRTY_CD);

				List refCustomFieldDataTypeList = getReferenceList(
						referenceMap, FunctionalAreaConstants.CONTACT_MGMT,
						ReferenceServiceDataConstants.G_CF_DATA_TY_CD);

				categoryDataBean.setRefSubjectList(refSubjectList);
				categoryDataBean.setRefPriorityList(refPriorityList);
				categoryDataBean
						.setRefCustomFieldDataTypeList(refCustomFieldDataTypeList);
			}
		} catch (ReferenceServiceRequestException e) {
			if (logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e) {
			if (logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}

		finally {
			;
		}
	}

	/**
	 * This method is used to created the input criteria to get the reference
	 * date.
	 * 
	 * @param referenceList :
	 *            List of Reference Data.
	 */
	private void createInputCriterias(List referenceList) {
		
		InputCriteria ipCrtSubCode = new InputCriteria();
		ipCrtSubCode.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		ipCrtSubCode
				.setElementName(ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);

		InputCriteria ipCrtPriorityCode = new InputCriteria();
		ipCrtPriorityCode
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		ipCrtPriorityCode
				.setElementName(ReferenceServiceDataConstants.G_CR_PRTY_CD);

		InputCriteria ipCrtCustomFieldDataCode = new InputCriteria();
		ipCrtCustomFieldDataCode
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		ipCrtCustomFieldDataCode
				.setElementName(ReferenceServiceDataConstants.G_CF_DATA_TY_CD);

		referenceList.add(ipCrtSubCode);
		referenceList.add(ipCrtPriorityCode);
		referenceList.add(ipCrtCustomFieldDataCode);

		;
	}

	/**
	 * This method is used to get the reference service list from the reference
	 * map based on the fuctional area constant and elementName
	 * 
	 * @param referenceMap :
	 *            Map of reference list.
	 * @param functionalAreaConstant :
	 *            Reference Service Functional Area Constants to get.
	 * @param elementName :
	 *            Reference Service Element to get.
	 * @return tempRevenueTypeList : Temporary Reference Service List.
	 */
	private List getReferenceList(Map referenceMap,
			String functionalAreaConstant, String elementName) {
		
		List referenceList = null;

		if (ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK.equals(elementName)) {
			referenceList = (List) referenceMap.get(functionalAreaConstant
					+ ProgramConstants.HASH_SEPARATOR + elementName);

			return referenceList;
		}

		List tempRevenueTypeList = new ArrayList();

		referenceList = (List) referenceMap.get(functionalAreaConstant
				+ ProgramConstants.HASH_SEPARATOR + elementName);

		if (referenceList != null) {
			int size = referenceList.size();
			for (int i = 0; i < size; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);
				tempRevenueTypeList.add(new SelectItem(refVo
						.getValidValueCode(), refVo.getValidValueCode()
						+ ProgramConstants.HYPHEN_SEPARATOR
						+ refVo.getShortDescription()));
			}
		}
		;
		return tempRevenueTypeList;
	}

	/**
	 * This operation is used to cancel incomplete user action.
	 */
	private void cancelIncompleteAction() {
		
		if (categoryDataBean.isRenderAddCategory()
				|| categoryDataBean.isRenderEditCategory()) {
			this.cancelCategory();
		}

		;
	}

	/**
	 * showing Audit Details for CallScript.
	 * 
	 * @return String
	 */
	public String showAuditHistory() {
		long entryTime = System.currentTimeMillis();

		GlobalAuditsDelegate audit;
		final List list = new ArrayList();
		try {
			if (logger.isDebugEnabled())
			{
				logger.debug("in show child audit history");
			}
			audit = new GlobalAuditsDelegate();

			CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();
			CorrespondenceCategory categoryDO = categoryDOConvertor
					.convertCategoryVOToDO(categoryDataBean.getCategoryVO());

			list.add(categoryDO);
			//"FindBugs" issue fixed
			//HashMap hm = audit.getAuditLogInfo(list);
			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(categoryDO, 0,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
			getCategoryDataBean().setCategoryAuditHistoryList(
					enterpriseResultVO.getSearchResults());
			getCategoryDataBean().setCategoryHistoryRender(true);
			/* Added new for Expand */
			getCategoryDataBean().setAuditOpen(true);
			setRecordRange(enterpriseResultVO);
		} catch (Exception e) {
			if (logger.isDebugEnabled())
			{
				logger.debug("Error in show audit history  " + e);
			}
		}

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("CategoryControllerBean" + "#" + "showAuditHistory"
					+ "#" + (exitTime - entryTime));
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	;

	/**
	 * To get the description based on code provided.
	 * 
	 * @param code :
	 *            Holds the code value
	 * @param vvList :
	 *            Holds the List of valid values
	 * @return String : Description of the code provided.
	 */
	private String getDescriptionFromVV(String code, List vvList) {

				
		String desc = ProgramConstants.EMPTY_STRING;
		String valueStr = ProgramConstants.EMPTY_STRING;
		if (vvList != null && vvList.size() > 0) {
			for (Iterator iter = vvList.iterator(); iter.hasNext();) {
				SelectItem selectItem = (SelectItem) iter.next();
				valueStr = (String) selectItem.getValue();
				if (valueStr != null && valueStr.equalsIgnoreCase(code)) {
					desc = selectItem.getLabel();
					break;
				}
			}
		}
		;
		return desc;
	}

	/**
	 * This operation is used to get Category Subject details for edit
	 * operation.
	 * 
	 * @return String : Navigation message.
	 */
	public String getSubjectDetails() {
		
		categoryDataBean.setSubjectVO(null);
		CategorySubjectVO subjectVO = new CategorySubjectVO();
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String indicatorValue = (String) map
				.get(ContactManagementConstants.INCLUDE);
		//	boolean includeIndicatorValue = indicatorValue.equals("true");

		String subjectValue = (String) map
				.get(ContactManagementConstants.SUBJECT);

		String subjectCodeValue = (String) map
				.get(ContactManagementConstants.SUBJECT_CODE_VAL);

		subjectVO.setIncludeIndicator(indicatorValue);
		subjectVO.setSubjectDesc(subjectValue);
		subjectVO.setSubjectCode(subjectCodeValue);
		categoryDataBean.setSubjectVO(subjectVO);
		categoryDataBean.setRenderSubjectDetails(true);
		categoryDataBean.setShowEditSucessMessage(false);
		//for fixing defect ESPRD00601585
		categoryDataBean.setTabIndex(0);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to Save Category Subject include details for edit
	 * operation.
	 * 
	 * @return String : Navigation message.
	 */
	public String saveSubjectStatus() {
		

		categoryDataBean.setShowEditSucessMessage(false);
		CategorySubjectVO subjectVO = null;
		subjectVO = categoryDataBean.getSubjectVO();
		if (logger.isInfoEnabled())
		{
			logger.info("subjectVO==========" + subjectVO.getIncludeIndicator());
		}
		List listOfCategorySubVOs = categoryDataBean.getListOfCategorySubVOs();
		List changedListOfCategorySubVOs = new ArrayList();
		if (listOfCategorySubVOs != null && listOfCategorySubVOs.size() > 0) {
			CategorySubjectVO indSubjectVO = null;
			for (Iterator iter = listOfCategorySubVOs.iterator(); iter
					.hasNext();) {
				indSubjectVO = (CategorySubjectVO) iter.next();
				if (indSubjectVO.getSubjectDesc().equals(
						subjectVO.getSubjectDesc()))
				{
					indSubjectVO.setIncludeIndicator(subjectVO.getIncludeIndicator());
					// code Added for Defect ESPRD00795947 - start
				    if(indSubjectVO.getIncludeIndicator().equalsIgnoreCase("Yes")) 
				    {
					indSubjectVO.setIncludeIndicatorforimage(true);
				    }else
				    {
				    	indSubjectVO.setIncludeIndicatorforimage(false);
				    }
				 // code Added for Defect ESPRD00795947 - END
					indSubjectVO.setAuditUserID(getUserID());
					indSubjectVO.setAuditTimeStamp(new Date());

				}

				changedListOfCategorySubVOs.add(indSubjectVO);
			}
			categoryDataBean
					.setListOfCategorySubVOs(changedListOfCategorySubVOs);

			categoryDataBean.setShowEditSucessMessage(true);
			categoryDataBean.setRenderSubjectDetails(true);

		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to get Custom Field details for edit operation.
	 * 
	 * @return String : Navigation message.
	 */
	public String getCustomFieldDetails() {
	
		CategoryCustomFieldsVO categoryCustomFieldsVO = new CategoryCustomFieldsVO();
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();

		Long customFieldSK = new Long((String) map
				.get(ContactManagementConstants.CUSTOM_FIELD));
		//Below code is modified for changing the activeIncludeIndicator variable to String type for the Defect ESPRD00802214.
		/*boolean includeIndicatorValue = ((String) map
				.get(ContactManagementConstants.INCLUDE)).equals("true");*/
		String includeIndicatorValue = ((String) map.get(ContactManagementConstants.INCLUDE));
		String colmDesc = (String) map.get(ContactManagementConstants.COL_DESC);
		String dataType = (String) map
				.get(ContactManagementConstants.DATA_TYPE);
		int fieldLength = Integer.parseInt((String) map
				.get(ContactManagementConstants.FIELD_LEN));
		boolean cfRequired = ((String) map
				.get(ContactManagementConstants.REQUIRED)).equals("true");
		boolean cfProtectedOnSave = ((String) map
				.get(ContactManagementConstants.PROC_ON_SAVE)).equals("true");

		categoryCustomFieldsVO.setCustomFieldSK(customFieldSK);
		categoryCustomFieldsVO.setIncludeIndicator(includeIndicatorValue);
		//categoryCustomFieldsVO.setColumnDesc(colmDesc);
		//for fixing defect 673618
		if (colmDesc.indexOf("\"") != -1) {
			categoryCustomFieldsVO
					.setColumnDesc(colmDesc.replaceAll("\"", "'"));
		} else {
			categoryCustomFieldsVO.setColumnDesc(colmDesc);
		}
		categoryCustomFieldsVO.setDataType(dataType);
		categoryCustomFieldsVO.setFieldLength(fieldLength);
		categoryCustomFieldsVO.setCfRequired(cfRequired);
		categoryCustomFieldsVO.setCfProtectedOnSave(cfProtectedOnSave);

		categoryDataBean.setCategoryCustomFieldsVO(categoryCustomFieldsVO);
		categoryDataBean.setRenderCustomFieldDetails(true);
		categoryDataBean.setShowEditSucessMessage(false);
		//for fixing defect ESPRD00601585
		categoryDataBean.setTabIndex(1);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to Save Custom Field include details for edit
	 * operation.
	 * 
	 * @return String : Navigation message.
	 */
	public String saveCustomFieldStatus() {
		categoryDataBean.setShowEditSucessMessage(false);
		CategoryCustomFieldsVO categoryCustomFieldsVO = null;
		categoryCustomFieldsVO = categoryDataBean.getCategoryCustomFieldsVO();
		List listOfCategoryCustomFldVOs = categoryDataBean
				.getListOfCategoryCustomFldVOs();

		if (listOfCategoryCustomFldVOs != null
				&& listOfCategoryCustomFldVOs.size() > 0) {
			List changedListOfCategoryCustomFldVOs = new ArrayList();
			CategoryCustomFieldsVO ctgryCustomFieldVO = null;
			for (Iterator iter = listOfCategoryCustomFldVOs.iterator(); iter
					.hasNext();) {
				ctgryCustomFieldVO = (CategoryCustomFieldsVO) iter.next();
				if (ctgryCustomFieldVO.getCustomFieldSK().equals(
						categoryCustomFieldsVO.getCustomFieldSK())) {
					//Below code is modified for changing the activeIncludeIndicator variable to String type for the Defect ESPRD00802214.
					/*ctgryCustomFieldVO
							.setIncludeIndicator(categoryCustomFieldsVO
									.isIncludeIndicator());*/
					ctgryCustomFieldVO
					.setIncludeIndicator(categoryCustomFieldsVO
							.getIncludeIndicator());
					if(ctgryCustomFieldVO.getIncludeIndicator().equalsIgnoreCase(ContactManagementConstants.YES))
							{
								ctgryCustomFieldVO.setIncludeIndicatorImage(true);
							}
					else
					{
						ctgryCustomFieldVO.setIncludeIndicatorImage(false);
					}
				}

				changedListOfCategoryCustomFldVOs.add(ctgryCustomFieldVO);
			}
			categoryDataBean
					.setListOfCategoryCustomFldVOs(changedListOfCategoryCustomFldVOs);
			categoryDataBean.setShowEditSucessMessage(true);
			categoryDataBean.setRenderCustomFieldDetails(true);

		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	private void customFieldsClone(CorrespondenceCategory categoryDO) {
		if (categoryDO.getCustomFields() != null
				&& categoryDO.getCustomFields().size() > 0) {
			HashSet customFieldsSet = (HashSet) categoryDO.getCustomFields();
			HashSet temcustomFieldsSet = (HashSet) customFieldsSet.clone();
			;
			categoryDO.setTempCustomFieldsAssignments(temcustomFieldsSet);
			categoryDO.getCustomFields().clear();
		}

	}

	/** AUDIT LOGGING * */

	/**
	 * This operation will show audit log.
	 * 
	 * @return String
	 * @throws GlobalAuditsBusinessException
	 */
	public String showAuditLog() throws GlobalAuditsBusinessException {
		long entryTime = System.currentTimeMillis();
		getCategoryDataBean().setCategoryAuditRender(true);
		getCategoryDataBean().setAuditColumnHistoryRender(false);
		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled())
		{
			logger.info("CategoryControllerBean" + "#" + "showAuditLog" + "#" + (exitTime - entryTime));
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation will close audit log.
	 * 
	 * @return String
	 */
	public String closeAuditLog() {
		long entryTime = System.currentTimeMillis();
		if (logger.isDebugEnabled())
		{
			logger.debug("Inside renderDiv()");
		}
		getCategoryDataBean().setCategoryAuditRender(false);
		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("CategoryControllerBean" + "#" + "closeAuditLog"
					+ (exitTime - entryTime));
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	public void setRecordRange(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		long entryTime = System.currentTimeMillis();
		int listSize;
		if (logger.isDebugEnabled())
		{
			logger.debug("Inside setRecordRange ");
		}
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		if (logger.isDebugEnabled())
		{
			logger.debug("Total no of records-->" + totalRecordCount);
		}
		CategoryDataBean categoryDataBean = getCategoryDataBean();
		categoryDataBean.setCount((int) totalRecordCount);

		int noOfPages = categoryDataBean.getCount()
				/ categoryDataBean.getItemsPerPage();

		int modNofPages = categoryDataBean.getCount()
				% categoryDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}
		if (logger.isDebugEnabled())
		{
			logger.debug("Number Of pages: " + noOfPages);
		}

		categoryDataBean.setCurrentPage(ProgramConstants.INITIAL_CURRENT_PAGE);
		categoryDataBean.setNumberOfPages(noOfPages);
		categoryDataBean.setStartRecord(ProgramConstants.START_RECORD);
		categoryDataBean.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = categoryDataBean.getCount();
		if (logger.isDebugEnabled())
		{
			logger.debug("Total records count is : " + listSize);
		}
		if (listSize <= categoryDataBean.getItemsPerPage()) {
			categoryDataBean.setEndRecord(listSize);
		}
		//"FindBugs" issue fixed
		/*
		 * if (listSize > categoryDataBean.getItemsPerPage()) { listSize =
		 * categoryDataBean.getItemsPerPage(); }
		 */

		if (categoryDataBean.getCount() <= categoryDataBean.getItemsPerPage()) {
			categoryDataBean.setShowNext(false);
		} else {
			categoryDataBean.setShowNext(true);
		}
		categoryDataBean.setShowPrevious(false);
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ categoryDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ categoryDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ categoryDataBean.getStartRecord());
			logger.debug("The end record......."
					+ categoryDataBean.getEndRecord());
			logger
					.debug("The total count......."
							+ categoryDataBean.getCount());
		}
		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
			logger.info("CategoryControllerBean" + "#" + "setRecordRange" + "#"	+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}
	}

	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}

	public String showColumnChange() {
		//"FindBugs" issue fixed
		//long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
			getCategoryDataBean().setAuditColumnHistoryRender(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getCategoryDataBean().setSelectedAuditLog(auditLog);
		} catch (Exception e) {
			logger.debug("Error in show column change  " + e);
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method will be used for navigating to previous page in the
	 * pagination.
	 * 
	 * @return String.
	 */
	public String previous() {
		long entryTime = System.currentTimeMillis();
		
		CategoryDataBean categoryDataBean = getCategoryDataBean();
		EnterpriseBaseDataBean entDataBean = previousPage(categoryDataBean,
				categoryDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("CategoryControllerBean" + "#" + "previous" + "#"
					+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}

		return ProgramConstants.PREVIOUS;
	}

	/**
	 * This method will be used for writing the logic for populating search
	 * results for a given search criteria.
	 */
	private void populateList(int currentPage) {
		//	ArrayList searchList = new ArrayList(); //FInd bug issue
		CategoryDataBean categoryDataBean = getCategoryDataBean();
		GlobalAuditsDelegate globalAuditsDelegate = null;
		try {
			globalAuditsDelegate = new GlobalAuditsDelegate();
			CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();
			CorrespondenceCategory categoryDO = categoryDOConvertor
					.convertCategoryVOToDO(categoryDataBean.getCategoryVO());

			EnterpriseSearchResultsVO enterpriseResultVO = globalAuditsDelegate
					.getAuditLogList(categoryDO, (currentPage - 1)
							* ProgramNumberConstants.INT_TEN,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

			categoryDataBean.setCategoryAuditHistoryList(enterpriseResultVO
					.getSearchResults());
			categoryDataBean.setCategoryAuditRender(true);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			{
				logger.debug(e);
			}
		}
	}

	public String next() {
		long entryTime = System.currentTimeMillis();
		CategoryDataBean systemParameterDataBean = getCategoryDataBean();
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ systemParameterDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ systemParameterDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ systemParameterDataBean.getStartRecord());
			logger.debug("The end record......."
					+ systemParameterDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(systemParameterDataBean,
				systemParameterDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("CategoryControllerBean" + "#" + "next" + "#"
					+ (exitTime - entryTime));
		}

		return ProgramConstants.NEXT;
	}

	public String closeColumnChange() {
		getCategoryDataBean().setAuditColumnHistoryRender(false);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to validate the Business units (To check for
	 * null). Business Unit is mandatory field.
	 * 
	 * @return boolean : true if business unit is not null else false.
	 */
	private boolean validateBusinessUnit() {
	
		boolean validCategoryTypeCode = true;

		String categoryTypeCode = getCategoryDataBean().getCategoryVO()
				.getCategoryTypeCode();
		if(logger.isDebugEnabled())
		{
			logger.debug("Type Code value : " + categoryTypeCode);
		}
		if (isNullOrEmptyField(categoryTypeCode)) {
			validCategoryTypeCode = false;

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.BUSINESS_UNIT },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.CAT_TYPECODE);
		}
		if(logger.isDebugEnabled())
		{
			logger.debug("Type code " + validCategoryTypeCode);
		}
		return validCategoryTypeCode;
	}

	/**
	 * @return Returns the controlRequired.
	 */
	public boolean isControlRequired() {
		return controlRequired;
	}

	/**
	 * @param controlRequired
	 *            The controlRequired to set.
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}

	public void redirect() throws IOException {
		categoryDataBean.setRenderAddCategory(false);
		categoryDataBean.setRenderEditCategory(false);
		categoryDataBean.setShowDeletedMessage(false);
		
		Object obj = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		ActionRequest request = (ActionRequest) obj;
		String s = request.getParameter("com.ibm.faces.portlet.page.view");
		if(logger.isInfoEnabled())
		{
			logger.info("s=======" + s);
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

	/**
	 * @return Returns the loadCategoryData.
	 */
	public String getLoadCategoryData() {
		getUserPermission();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		if (!categoryDataBean.isCategorySort()) {
		
			List listOfCategoryDOs = null;
			List listOfCategoryVOs = categoryDataBean.getListOfCategoryVOs();
			//for fixing Defect ESPRD00586331 starts
			List newListOfCategoryVos = new ArrayList();
			listOfCategoryVOs.clear();
			try {
				listOfCategoryVOs = contactMaintenanceDelegate
						.getMaintainCategoryList();
				//"FindBugs" issue fixed
				// $$$$$$$$$$$$$$$$"+listOfCategoryVOs.size());
				MaitainCategoryVO maitainCategoryVO = null;
				String typeCode = null;
				String shortDesc = null;
				if (listOfCategoryVOs != null) {
					Iterator it = listOfCategoryVOs.iterator();
					while (it.hasNext()) {
						maitainCategoryVO = (MaitainCategoryVO) it.next();
						

						typeCode = maitainCategoryVO.getCategoryTypeCode();
						if (typeCode != null) {
							shortDesc = ReferenceServiceDelegate
									.getReferenceSearchShortDescription(
											FunctionalAreaConstants.GENERAL,
											ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_CD,
											new Long(159), typeCode);

							maitainCategoryVO.setCategoryTypeCode(shortDesc);
						}
						newListOfCategoryVos.add(maitainCategoryVO);
					}//while ends
				}//if ends
				//categoryDataBean.setListOfCategoryVOs(listOfCategoryVOs);
				//for fixing Defect ESPRD00586331 ends
				categoryDataBean.setListOfCategoryVOs(newListOfCategoryVos);
			} catch (CategoryFetchBusinessException e) {

				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_GENERIC_FAILURE,
						new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			}
			/*
			 * if (listOfCategoryDOs != null) { CategoryDOConvertor
			 * categoryDOConvertor = new CategoryDOConvertor();
			 * 
			 * for (Iterator iter = listOfCategoryDOs.iterator();
			 * iter.hasNext();) { CorrespondenceCategory categoryDO =
			 * (CorrespondenceCategory) iter .next(); CategoryVO categoryVO =
			 * categoryDOConvertor
			 * .convertCategoryDOToVOForCategory(categoryDO);
			 * listOfCategoryVOs.add(categoryVO); } }
			 */

			//"FindBugs" issue fixed
			//categoryDataBean.setRenderNoDataCategory(listOfCategoryVOs.isEmpty());
			if (listOfCategoryVOs != null) {
				categoryDataBean.setRenderNoDataCategory(listOfCategoryVOs
						.isEmpty());
			}
		}
		return loadCategoryData;
	}

	/**
	 * @param loadCategoryData
	 *            The loadCategoryData to set.
	 */
	public void setLoadCategoryData(String loadCategoryData) {
		this.loadCategoryData = loadCategoryData;
	}

	private void loadCustomFiledAndTemplateData() {
		
		Map categoryData = null;
		List listOfCustomFields = null;
		List listOfCategoryCustomFieldsVO = categoryDataBean
				.getListOfCategoryCustomFldVOs();
		listOfCategoryCustomFieldsVO.clear();

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			categoryData = contactMaintenanceDelegate
					.getCustomFieldAndTemplateData(ContactManagementConstants.G_CR_TB);

			if (categoryData != null) {
				listOfCustomFields = (List) categoryData
						.get("listOfCustomFields");
				if (listOfCustomFields != null) {
					for (Iterator iter = listOfCustomFields.iterator(); iter
							.hasNext();) {
						Object[] customFieldData = (Object[]) iter.next();
						CategoryCustomFieldsVO categoryCustomFieldsVO = new CategoryCustomFieldsVO();

						
						categoryCustomFieldsVO
								.setCustomFieldSK((Long) customFieldData[0]);

						//categoryCustomFieldsVO.setColumnDesc(customFieldData[1].toString());
						categoryCustomFieldsVO
								.setColumnDescDisp(customFieldData[1]
										.toString());
						String desc = customFieldData[1].toString();
						if (desc.indexOf("'") != -1) {
							categoryCustomFieldsVO.setColumnDesc(desc
									.replaceAll("'", "\""));
						} else {
							categoryCustomFieldsVO.setColumnDesc(desc);
						}
						categoryCustomFieldsVO
								.setDataType(getDescriptionFromVV(
										customFieldData[2].toString(),
										categoryDataBean
												.getRefCustomFieldDataTypeList()));
						if (customFieldData[3] != null) {
							categoryCustomFieldsVO
									.setFieldLength(((Integer) customFieldData[3])
											.intValue());
						}
						if (customFieldData[4] != null) {
							categoryCustomFieldsVO
									.setCfRequired(((Boolean) customFieldData[4])
											.booleanValue());
						}
						if (customFieldData[5] != null) {
							categoryCustomFieldsVO
									.setCfProtectedOnSave(((Boolean) customFieldData[5])
											.booleanValue());
						}

						listOfCategoryCustomFieldsVO
								.add(categoryCustomFieldsVO);
					}
					categoryDataBean.setRenderNoDataCF(false);
				}

				

				List listOfTemplates = null;
				List listOfCategoryTemplatesVO = categoryDataBean
						.getListOfCategoryTemplateVOs();
				listOfCategoryTemplatesVO.clear();

				listOfTemplates = (List) categoryData.get("listOfTemplates");
				if (listOfTemplates != null) {
					if(logger.isDebugEnabled())
					{
						logger.debug("Category Templates Size from DB:"	+ listOfTemplates.size());
					}
					for (Iterator iter = listOfTemplates.iterator(); iter
							.hasNext();) {
						Object[] templatedata = (Object[]) iter.next();
						CategoryLetterTemplateVO categoryTempaltesVO = new CategoryLetterTemplateVO();
						categoryTempaltesVO
								.setLetterTemplateKeyData(templatedata[0]
										.toString());

						if (templatedata[1] != null) {
							categoryTempaltesVO.setTemplate(templatedata[1]
									.toString());
						}
						if (templatedata[2] != null) {
							categoryTempaltesVO
									.setTemplateDescription(templatedata[2]
											.toString());
						}
						if (templatedata[3] != null
								&& ((Boolean) templatedata[3]).booleanValue()) {
							categoryTempaltesVO.setSupervisorApprRqd(true);
						} else {
							categoryTempaltesVO.setSupervisorApprRqd(false);
						}
						/*
						 * if (templateDO.getOptionalTextAllowIndicator() !=
						 * null &&
						 * templateDO.getOptionalTextAllowIndicator().booleanValue()) {
						 * categoryTempaltesVO.setOptionalText(true); } else {
						 * categoryTempaltesVO.setOptionalText(false);
						 *  }
						 */

						/*logger.debug("Requiredindicator fromDo when req ia y :"
								+ categoryTempaltesVO.getSupervisorApprRqd());

						logger.debug("Required indicator from vo:"
								+ categoryTempaltesVO.getSupervisorApprRqd());
						logger.debug("Option Indicator fromVo:"
								+ categoryTempaltesVO.getOptionalText());*/

						listOfCategoryTemplatesVO.add(categoryTempaltesVO);
					}
					categoryDataBean.setRenderNoDataTemplate(true);
					if(logger.isDebugEnabled())
					{
						logger.debug("listOfCategoryTemplatesVO size :"	+ listOfCategoryTemplatesVO.size());
						logger.debug("Nodata value is:"	+ categoryDataBean.isRenderNoDataTemplate());
					}

				} else {
					categoryDataBean.setRenderNoDataTemplate(false);
				}
			}
		} catch (CustomFieldFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @return Returns the controlRequiredFOrDelete.
	 */
	public boolean isControlRequiredFOrDelete() {
		return controlRequiredFOrDelete;
	}

	/**
	 * @param controlRequiredFOrDelete
	 *            The controlRequiredFOrDelete to set.
	 */
	public void setControlRequiredFOrDelete(boolean controlRequiredFOrDelete) {
		this.controlRequiredFOrDelete = controlRequiredFOrDelete;
	}

	public String doAuditKeyListOperation() {
		
		try {
			//List catList = categoryDataBean.getListOfCategoryVOs();
			//logger.debug("catList::::::::::" + catList.size());

			//if (catList != null && !(catList.isEmpty())) {

			//Iterator catsctIt = catList.iterator();
			CategoryVO categoryVO = categoryDataBean.getCategoryVO();
			if (categoryVO != null) {
				List editablecategory = new ArrayList();
				editablecategory.add(createAuditableFeild("Active",
						"voidIndicator"));
				editablecategory.add(createAuditableFeild("Description",
						"description"));
				editablecategory.add(createAuditableFeild("Business Unit",
						"categoryTypeCode"));
				editablecategory.add(createAuditableFeild(
						"Supervisor Approval Required",
						"supervisorReviewReqIndicator"));
				editablecategory.add(createAuditableFeild("Priority",
						"priorityCode"));
				editablecategory.add(createAuditableFeild(
						"Days to Keep Closed Items in Watchlist",
						"categoryDeletionDays"));
				editablecategory.add(createAuditableFeild(
						"Number of Days Before Escalating to Medium",
						"categoryPriorityDay1"));
				editablecategory.add(createAuditableFeild(
						"Number of Days Before Escalating to High",
						"categoryPriorityDay2"));
				editablecategory.add(createAuditableFeild(
						"Number of Days Before Escalating to Urgent",
						"categoryPriorityDay3"));

				if (categoryVO.getAuditKeyList() != null
						&& !(categoryVO.getAuditKeyList().isEmpty())) {
					if(logger.isDebugEnabled())
					{
						logger.debug("======CategoryVO====Before Filter==="	+ categoryVO.getAuditKeyList().size());
					}
					AuditDataFilter.filterAuditKeys(editablecategory,
							categoryVO);
					if(logger.isDebugEnabled())
					{
						logger.debug("======CategoryVO====After Filter==="	+ categoryVO.getAuditKeyList().size());
					}

				} else {
					if(logger.isDebugEnabled())
					{
						logger.debug("======CategoryVO====Before Filter AuditKeys Empty===");
					}
				}

			}

			

			// Defect ESPRD00674584
			doAuditKeyListOperationForSubject();
			doAuditKeyListOperationForCustomFields();
			doAuditKeyListOperationForTemplate();

			UIComponent component = findComponentInRoot("CategoryAuditId");
			if (component != null) {
				AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
				if (categoryVO.getAuditKeyList() != null) {
					
					auditHistoryTable.setValue(categoryVO.getAuditKeyList());
				}
			}
			// Defect ESPRD00674584
			

		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
		}

		categoryDataBean.setAuditLogFlag(true);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	// Defect ESPRD00674584

	private void doAuditKeyListOperationForSubject() {
		
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();
		List listSubject = categoryVO.getListOfSubjects();
		if (listSubject != null && !listSubject.isEmpty()) {
			List auditableSubjectDetails = new ArrayList(2);
			//auditableSubjectDetails.add(createAuditableFeild("includeIndicator","includeIndicator"));
			auditableSubjectDetails.add(createAuditableFeild("subjectCode",
					"subjectCode"));

			Iterator itr = listSubject.iterator();
			while (itr.hasNext()) {
				CategorySubjectVO categorySubjectVO = (CategorySubjectVO) itr
						.next();
				if (categorySubjectVO.getAuditKeyList() != null
						&& !categorySubjectVO.getAuditKeyList().isEmpty()) {
					
					AuditDataFilter.filterAuditKeys(auditableSubjectDetails,
							categorySubjectVO);
					
				}
				categoryVO.getAuditKeyList().addAll(
						categorySubjectVO.getAuditKeyList());
			}

		}
	}

	// Defect ESPRD00674584

	private void doAuditKeyListOperationForCustomFields() {
		
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();
		List listCustomFields = categoryVO.getListOfCustomFields();
		if (listCustomFields != null && !listCustomFields.isEmpty()) {
			List auditableCustomFieldsDetails = new ArrayList(1);
			//auditableCustomFieldsDetails.add(createAuditableFeild("includeIndicator","includeIndicator"));
			auditableCustomFieldsDetails.add(createAuditableFeild(
					"subjectAreaSK", "subjectAreaSK"));

			Iterator itr = listCustomFields.iterator();
			while (itr.hasNext()) {
				CategoryCustomFieldsVO categoryCustomFieldsVO = (CategoryCustomFieldsVO) itr
						.next();
				if (categoryCustomFieldsVO.getAuditKeyList() != null
						&& !categoryCustomFieldsVO.getAuditKeyList().isEmpty()) {
					
					AuditDataFilter.filterAuditKeys(
							auditableCustomFieldsDetails,
							categoryCustomFieldsVO);
					
				}
				categoryVO.getAuditKeyList().addAll(
						categoryCustomFieldsVO.getAuditKeyList());
			}

		}
	}

	// Defect ESPRD00674584

	private void doAuditKeyListOperationForTemplate() {
		
		CategoryVO categoryVO = categoryDataBean.getCategoryVO();
		List listTemplates = categoryVO.getListOfTemplates();
		if (listTemplates != null && !listTemplates.isEmpty()) {
			List auditableTemplatesDetails = new ArrayList(1);
			auditableTemplatesDetails.add(createAuditableFeild(
					"letterTemplateKeyData", "letterTemplateKeyData"));

			Iterator itr = listTemplates.iterator();
			while (itr.hasNext()) {
				CategoryLetterTemplateVO categoryLetterTemplateVO = (CategoryLetterTemplateVO) itr
						.next();
				if (categoryLetterTemplateVO.getAuditKeyList() != null
						&& !categoryLetterTemplateVO.getAuditKeyList()
								.isEmpty()) {
					
					AuditDataFilter.filterAuditKeys(auditableTemplatesDetails,
							categoryLetterTemplateVO);
					
				}
				categoryVO.getAuditKeyList().addAll(
						categoryLetterTemplateVO.getAuditKeyList());
			}

		}
	}

	private AuditableField createAuditableFeild(String feildName,
			String domainAttributeName) {

		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);

		return auditableField;

	}
	
	//hash map performance issue code change
	private Map mapOfDeletedSubject(CategoryVO categoryVO){
		Map deletedSubjects=new HashMap();
		List listOfDeletedSubjects =categoryVO.getListOfDeletedSubjects();
		Iterator deledSubIt=listOfDeletedSubjects.iterator();
		while(deledSubIt.hasNext()){
			CategorySubjectVO categorySubjectVO=(CategorySubjectVO)deledSubIt.next();
			deletedSubjects.put(categorySubjectVO.getSubjectCode(),
					Boolean.FALSE);
		}
		return deletedSubjects;
	}
}