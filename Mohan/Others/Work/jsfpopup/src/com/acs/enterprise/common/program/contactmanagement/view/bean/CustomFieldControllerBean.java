/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.CollationKey;
import java.text.Collator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.acs.enterprise.common.audit.application.exception.GlobalAuditsBusinessException;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.commonentities.view.bean.AuditLogControllerBean;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CustomFieldCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CustomFieldDeleteBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CustomFieldFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CustomFieldUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CustomFieldSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CustomFiedDOtoVOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CustomFieldDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.DropDownVO;
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
 * This class implements and controls all action methods like add, update,
 * cancel for Maintain CustomFields.
 * 
 * @author Wipro
 */
public class CustomFieldControllerBean extends EnterpriseBaseControllerBean {

	/** Creates an instance of the logger. * */
	private static final EnterpriseLogger log = EnterpriseLogFactory
			.getLogger(CustomFieldControllerBean.class.getName());

	/**
	 * This field is used to store whether user has permission.
	 */
	private boolean controlRequired = true;

	private boolean controlRequiredFOrDelete = true;

	/**
	 * A variable of type CustomFieldDataBean to hold the instance of data bean.
	 */
	private transient CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();

	/** Creates instance of ContactMaintenanceDelegate * */
	private transient ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();


	/**
	 * Holds values protected indicator
	 */
	private static Integer value;

	private String loadUserPermission;

	/**
	 * @return Returns the String.
	 */
	public String customFields;

	
	/**
	 * This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermission() {
		String userPermission = "";
		String userid = getUserID();
		if(log.isDebugEnabled())
		{
			log.debug("CustomFieldControllerBean:::::userid::" + userid);
		}
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.MAINTAIN_CUSTOMFIELDS_PAGE,
					userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			

		}

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode", userPermission);
		userPermission = userPermission != null ? userPermission.trim() : "";
		if(log.isDebugEnabled())
		{
			log.debug("userPermission in CustomField -->" + userPermission);
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
			 * FacesContext
			 * .getCurrentInstance().getExternalContext().getRequest();
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

			
		}

		return userID;
	}

	/**
	 * This method is used to get the CustomField Data Bean.
	 * 
	 * @return CustomFieldDataBean : CustomFieldDataBean object.
	 */
	public CustomFieldDataBean getCustomFieldDataBean() {
		
		FacesContext fc = FacesContext.getCurrentInstance();

		CustomFieldDataBean customFieldDataBean = (CustomFieldDataBean) fc
				.getApplication()
				.createValueBinding(
						ContactManagementConstants.BINDING_BEGIN_SEPARATOR
								+ MaintainContactManagementUIConstants.CUSTOMFIELD_BEAN
								+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc);

		return customFieldDataBean;
	}

	/**
	 * This method returns String to editDropDownTable
	 * 
	 * @return String
	 */
	public String editDropDownTable() {
		
		DropDownVO tempDropDownVO = new DropDownVO();
		customFieldDataBean.setEditDropDownFlag(true);
		customFieldDataBean.setEditDropDownMenuFlag(true);
		customFieldDataBean.setEditDropDownInfoFlag(true);

		customFieldDataBean.setAddDropDownFlag(false);
		customFieldDataBean.setShowSuccessMsgFlag(false);

		//int intDropDownRowIndex = getDropDownRowIndex().getRowIndex();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("cfddRowIndex");
		int intDropDownRowIndex = Integer.parseInt(indexCode);
		String index = Integer.toString(intDropDownRowIndex);
		if (index != null) {
			customFieldDataBean.setDropDownIndex(index);
			DropDownVO dropDownVO = (DropDownVO) customFieldDataBean
					.getDropDownList().get(intDropDownRowIndex);

			if (dropDownVO != null) {
				//for fixing defect ESPRD00611024
				customFieldDataBean.setTempDropDownVO(dropDownVO);
				UIComponent component = ContactManagementHelper
						.findComponentInRoot("auditlogDetailsforcfDDId");
				if (component != null) {
					AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
					auditHistoryTable
							.setAuditLogControllerBean(new AuditLogControllerBean());
				}
				try {
					BeanUtils.copyProperties(tempDropDownVO, dropDownVO);
					customFieldDataBean.setOldDropDownItemDesc(dropDownVO
							.getDropDownItemDesc());
				} catch (IllegalAccessException e) {
					if(log.isErrorEnabled())
					{
						log.error(e.getMessage(), e);
					}
				} catch (InvocationTargetException e) {
					if(log.isErrorEnabled())
					{
						log.error(e.getMessage(), e);
					}
				}
			}
		}
		customFieldDataBean.getCustomFieldVO().setDropDownVO(tempDropDownVO);
		customFieldDataBean.setShowCFforAuddropdown(false);

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method returns String to addDropDownTable
	 * 
	 * @return String
	 */
	public String addDropDownTable() {
		
		customFieldDataBean.getCustomFieldVO().setDropDownVO(new DropDownVO());
		customFieldDataBean.setShowDropDownScreenFlag(true);
		customFieldDataBean.setAddDropDownFlag(true);
		customFieldDataBean.setEditDropDownFlag(false);
		customFieldDataBean.setShowSuccessMsgFlag(false);
		/* Samll Save and Big Save */
		customFieldDataBean.setDdSaveFlag(false);
		customFieldDataBean.setDdFlag(true);
		/* End Samll Save and Big Save */
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method returns string to reset AddDropDown table
	 * 
	 * @return String
	 */
	public String resetAddDropDown() {
		
		customFieldDataBean.getCustomFieldVO().setDropDownVO(new DropDownVO());
		customFieldDataBean.setShowSuccessMsgFlag(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method returns string to reset EditDropDown table
	 * 
	 * @return String
	 */
	public String resetEditDropDown() {
		
		DropDownVO dropDownVO = null;
		DropDownVO tempDropDownVO = new DropDownVO();
		String dropDownIndex = customFieldDataBean.getDropDownIndex();
		dropDownVO = (DropDownVO) customFieldDataBean.getDropDownList().get(
				Integer.parseInt(dropDownIndex));
		try {
			BeanUtils.copyProperties(tempDropDownVO, dropDownVO);
		} catch (IllegalAccessException e) {
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		} catch (InvocationTargetException e) {
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		}
		customFieldDataBean.getCustomFieldVO().setDropDownVO(tempDropDownVO);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method returns string to reset AddDropDown table
	 * 
	 * @return String
	 */
	public String saveDropDown() {
		
		DropDownVO dropDownVO = new DropDownVO();
		DropDownVO tempDropDownVO = new DropDownVO();
		tempDropDownVO = customFieldDataBean.getCustomFieldVO().getDropDownVO();
		boolean flag = validateDropDownInfo(tempDropDownVO);
		customFieldDataBean.setShowDescMsgFlag(false);

		customFieldDataBean.setFlagFordd(flag);

		customFieldDataBean.setShowSuccessMsgFlag(false);
		if (flag) {
			if (customFieldDataBean.isAddDropDownFlag()) {
				if (tempDropDownVO != null) {
					List list = customFieldDataBean.getDropDownList();
					for (int i = 0; i < list.size(); i++) {
						dropDownVO = (DropDownVO) list.get(i);
						if (tempDropDownVO.getDropDownItemDesc()
								.equalsIgnoreCase(
										dropDownVO.getDropDownItemDesc())) {
							setErrorMessage(MaintainContactManagementUIConstants.UNIQUE_DESC);
							customFieldDataBean.setShowSuccessMsgFlag(false);
							customFieldDataBean.setShowSucessMessage(false);
							customFieldDataBean.setShowDescMsgFlag(true);
							customFieldDataBean.setShowDDDeletedMessage(false);
							flag = false;
							break;
						}
					}
					if (flag) {
						customFieldDataBean.getDropDownList().add(
								tempDropDownVO);
						tempDropDownVO = new DropDownVO();
						customFieldDataBean.getCustomFieldVO().setDropDownVO(
								tempDropDownVO);
						setErrorMessage(MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG);
						customFieldDataBean.setShowSuccessMsgFlag(true);
						customFieldDataBean.setShowCFDescMsgFlag(false);
						customFieldDataBean.setDdSaveFlag(true);
						customFieldDataBean.setShowDDDeletedMessage(false);
					}
				}
				customFieldDataBean.setDropDownDataFlag(true);
			} else if (customFieldDataBean.isEditDropDownFlag()) {
				tempDropDownVO = new DropDownVO();
				tempDropDownVO = customFieldDataBean.getCustomFieldVO()
						.getDropDownVO();
				tempDropDownVO.setOldDropDownItemDesc(customFieldDataBean
						.getOldDropDownItemDesc());

				if (tempDropDownVO != null) {
					List list = customFieldDataBean.getDropDownList();
					DropDownVO ddVO = (DropDownVO) list.get(Integer
							.parseInt(customFieldDataBean.getDropDownIndex()));
					list.remove(Integer.parseInt(customFieldDataBean
							.getDropDownIndex()));
					for (int i = 0; i < list.size(); i++) {
						dropDownVO = (DropDownVO) list.get(i);
						if (tempDropDownVO.getDropDownItemDesc()
								.equalsIgnoreCase(
										dropDownVO.getDropDownItemDesc())) {
							setErrorMessage(MaintainContactManagementUIConstants.UNIQUE_DESC);
							customFieldDataBean.setShowSuccessMsgFlag(false);
							customFieldDataBean.setShowDescMsgFlag(true);
							customFieldDataBean.setShowDDDeletedMessage(false);
							flag = false;
							customFieldDataBean.getDropDownList().add(
									Integer.parseInt(customFieldDataBean
											.getDropDownIndex()), ddVO);
							break;
						}
					}
					if (flag) {
						customFieldDataBean.getDropDownList().add(
								Integer.parseInt(customFieldDataBean
										.getDropDownIndex()), tempDropDownVO);
						/*
						 * customFieldDataBean.getDropDownList().remove(
						 * Integer.parseInt(customFieldDataBean
						 * .getDropDownIndex()) + 1);
						 */
						setErrorMessage(MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG);
						customFieldDataBean.setShowSuccessMsgFlag(true);
						customFieldDataBean.setDdSaveFlag(true);
						customFieldDataBean.setShowDDDeletedMessage(false);
					}

				}
				customFieldDataBean.setDropDownDataFlag(true);
			}
		}

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * To delete the selected drop down for editing.
	 * 
	 * @author amarnathh
	 */
	public String deleteEditDropDown() {
		if(log.isDebugEnabled())
		{
			log.debug("Size of DropDownList : " + customFieldDataBean.getDropDownList().size());
		}

		/*
		 * String selectedDropDownDesc = customFieldDataBean.getCustomFieldVO()
		 * .getDropDownVO().getDropDownItemDesc();
		 */
		String selectedDropDownDesc = customFieldDataBean
				.getOldDropDownItemDesc();

		List list = customFieldDataBean.getDropDownList();
		for (int i = 0; i < list.size(); i++) {
			DropDownVO dropDownVO = (DropDownVO) list.get(i);
			if (selectedDropDownDesc.equalsIgnoreCase(dropDownVO
					.getDropDownItemDesc())) {
				
				list.remove(i);

				customFieldDataBean.setEditDropDownMenuFlag(false);
				customFieldDataBean.setEditDropDownInfoFlag(false);

				// Setting message for notifing deletion of selected Drop Down
				// from list.
				// setErrorMessage(MaintainContactManagementUIConstants.DEL_SUCCESS_MSG);
				// customFieldDataBean.setShowSucessMessage(true);
				customFieldDataBean.setShowDDDeletedMessage(true);
				Collections.sort(list, null);
				customFieldDataBean.setDropDownList(list);
				break;
			}
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * To cancel the DropDown Screen
	 */
	public String cancelAddDropDown() {
		
		customFieldDataBean.setAddDropDownFlag(false);
		customFieldDataBean.setEditDropDownFlag(false);
		customFieldDataBean.setShowDDDeletedMessage(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * To cancel the DropDown Screen
	 */
	public String cancelEditDropDown() {
		
		customFieldDataBean.setAddDropDownFlag(false);
		customFieldDataBean.setEditDropDownFlag(false);
		customFieldDataBean.setShowDDDeletedMessage(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * To display the DropDown Screen
	 * 
	 * @return String
	 * @param event :
	 *            Based on the flag value.
	 */
	public String showDropDownScreen(ValueChangeEvent event) {
		
		customFieldDataBean.setShowDropDownScreenFlag(false);
		customFieldDataBean.setGetRecords(true);
		customFieldDataBean.setShowDDDeletedMessage(false);

		if (event.getNewValue() != null) {
			if (event.getNewValue().equals(
					MaintainContactManagementUIConstants.STR_FOUR)) {
				customFieldDataBean.setShowDropDownScreenFlag(true);
				customFieldDataBean.setEditDropDownFlag(false);
				customFieldDataBean.setAddDropDownFlag(false);
				customFieldDataBean.setLengthFlag(false);
				showAuditHistoryForDropDown();
			} else {
				customFieldDataBean.setLengthFlag(true);
			}
		} else {
			if (customFieldDataBean.getDropDownList().size() > 0) {
				customFieldDataBean.setShowDropDownScreenFlag(true);
				customFieldDataBean.setEditDropDownFlag(false);
				customFieldDataBean.setAddDropDownFlag(false);
			}
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method returns string to resetEditCustomFields table
	 * 
	 * @return String
	 */
	public String resetEditCustomFields() {
		
		CustomFieldVO customFieldVO = null;
		CustomFieldVO tempCustomFieldVO = new CustomFieldVO();
		int customFieldIndex = customFieldDataBean.getRowIndex();
		customFieldVO = (CustomFieldVO) customFieldDataBean
				.getMaintainCustomFieldsList().get(customFieldIndex);
		DropDownVO tempDropDownVO = new DropDownVO();
		DropDownVO dropDownVo = customFieldDataBean.getTempDropDownVO();
		try {
			BeanUtils.copyProperties(tempCustomFieldVO, customFieldVO);

			if (tempCustomFieldVO.getDataType().equalsIgnoreCase("DD")) {
				customFieldDataBean.setShowDropDownScreenFlag(true);
				customFieldDataBean.setEditDropDownFlag(false);
				customFieldDataBean.setAddDropDownFlag(false);
				customFieldDataBean.setShowDDDeletedMessage(false);

				//for fixing defect ESPRD00611024 starts
				/*int intDropDownRowIndex = Integer.parseInt(customFieldDataBean
						.getDropDownIndex());
				String index = Integer.toString(intDropDownRowIndex);
				if (index != null) {
					customFieldDataBean.setDropDownIndex(index);
					List tempDropDownList = customFieldDataBean
							.getDropDownList();
					tempDropDownList.remove(intDropDownRowIndex);

					tempDropDownList.add(intDropDownRowIndex, dropDownVo);
					DropDownVO dropDownVO = (DropDownVO) customFieldDataBean
							.getDropDownList().get(intDropDownRowIndex);

					customFieldDataBean.setTempDropDownVO(dropDownVO);
					if (dropDownVO != null) {
						BeanUtils.copyProperties(tempDropDownVO, dropDownVO);
						customFieldDataBean.setOldDropDownItemDesc(dropDownVO
								.getDropDownItemDesc());
					}
				}*/
				//for fixing defect ESPRD00611024 ends
				//to fix dropdown list reset issue
				List tempList = new ArrayList();
				tempList.addAll(customFieldDataBean.getTempDropDownList());
				customFieldDataBean.setDropDownList(tempList);
			}
		} catch (IllegalAccessException e) {
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		} catch (InvocationTargetException e) {
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		}
		String dataType = tempCustomFieldVO.getDataType();
		if (!dataType.equalsIgnoreCase("DD")) {
			customFieldDataBean.setShowDropDownScreenFlag(false);
		}
		customFieldDataBean.setCustomFieldVO(tempCustomFieldVO);
		customFieldDataBean.getCustomFieldVO().setDropDownVO(tempDropDownVO);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort CustomFields information based on sorted
	 * column and order.
	 * 
	 * @param event
	 *            ActionEvent
	 * @return String
	 */

	public String sortCustomFields(ActionEvent event) {
		

		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);

		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);

		customFieldDataBean.setImageRender(event.getComponent().getId());
		sortDDComparator(sortColumn, sortOrder, customFieldDataBean
				.getDropDownList());
	/* Below line added for defect ESPRD00795949 to display 1st page by default 
		for Drop down data table on click of sorting event.*/
		customFieldDataBean.setDdstartIndex(0);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * Comparator to the LTC list to sort the list according to the sort order
	 * selected by the user.
	 * 
	 * @param sortColumn -
	 *            Column to be sorted.
	 * @param sortOrder -
	 *            Order by which the column should be started.
	 * @param dataList -
	 *            List to be sorted.
	 */
	private void sortDDComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		
		Comparator comparator = new Comparator() {
			public int compare(Object temp, Object tempOne) {
				DropDownVO dropDownVO1 = (DropDownVO) temp;
				DropDownVO dropDownVO2 = (DropDownVO) tempOne;
				boolean ascending = false;
				if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
						.equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}
				if (MaintainContactManagementUIConstants.DD_ITEM_DESC
						.equals(sortColumn)) {
					if (null == dropDownVO1.getDropDownItemDesc()) {
						dropDownVO1
								.setDropDownItemDesc(MaintainContactManagementUIConstants.NULL);
					}
					if (null == dropDownVO2.getDropDownItemDesc()) {
						dropDownVO2
								.setDropDownItemDesc(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? dropDownVO1
							.getDropDownItemDesc()
							.trim()
							.compareTo(dropDownVO2.getDropDownItemDesc().trim())
							: dropDownVO2.getDropDownItemDesc().trim()
									.compareTo(
											dropDownVO1.getDropDownItemDesc()
													.trim());
				}
				if (MaintainContactManagementUIConstants.DD_SORT_ORDER
						.equals(sortColumn)) {
					if (null == dropDownVO1.getSortOrder()) {
						dropDownVO1
								.setSortOrder(String
										.valueOf(MaintainContactManagementUIConstants.ZERO));
					}
					if (null == dropDownVO2.getSortOrder()) {
						dropDownVO2
								.setSortOrder(String
										.valueOf(MaintainContactManagementUIConstants.ZERO));
					}
					return ascending ? Integer.valueOf(
							dropDownVO1.getSortOrder()).compareTo(
							Integer.valueOf(dropDownVO2.getSortOrder()))
							: Integer.valueOf(dropDownVO2.getSortOrder())
									.compareTo(
											Integer.valueOf(dropDownVO1
													.getSortOrder()));
				}
				return MaintainContactManagementUIConstants.ZERO;
			}

		};

		Collections.sort(dataList, comparator);

	}

	/**
	 * This method is for validating the fields in DropDown Table.
	 * 
	 * @param dropDownVO .
	 * @return boolean.
	 */

	public boolean validateDropDownInfo(DropDownVO dropDownVO) {
		
		boolean flag = true;

		String actionCode = null;
		if (customFieldDataBean.isAddDropDownFlag()) {
			actionCode = MaintainContactManagementUIConstants.ADD;
		} else {
			actionCode = MaintainContactManagementUIConstants.UPDATE;
		}

		if (dropDownVO.getDropDownItemDesc() != null
				&& dropDownVO.getDropDownItemDesc().length() > 0) {

			String desc = dropDownVO.getDropDownItemDesc();
			if (!ContactManagementHelper.validateAlphaNumericSpl(desc)) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.DD_DESC_CHAR,
						MaintainContactManagementUIConstants.ADD_DD_DESC_ID,
						MaintainContactManagementUIConstants.EDIT_DD_DESC_ID,
						actionCode);
			} else if (desc.length() > MaintainContactManagementUIConstants.SIXTY_FOUR) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.DD_DESC_LENGTH,
						MaintainContactManagementUIConstants.ADD_DD_DESC_ID,
						MaintainContactManagementUIConstants.EDIT_DD_DESC_ID,
						actionCode);
			}
		} else {
			flag = false;

			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.DD_DESC_REQ,
					MaintainContactManagementUIConstants.ADD_DD_DESC_ID,
					MaintainContactManagementUIConstants.EDIT_DD_DESC_ID,
					actionCode);

		}

		if (dropDownVO.getSortOrder() != null
				&& dropDownVO.getSortOrder().length() > 0) {

			String sortOrder = (dropDownVO.getSortOrder()).toString();

			if (!EnterpriseCommonValidator.validateNumeric(sortOrder)) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.SORT_ORDER_NUMERIC,
								MaintainContactManagementUIConstants.ADD_SORT_ORDER_ID,
								MaintainContactManagementUIConstants.EDIT_SORT_ORDER_ID,
								actionCode);
			} else if (sortOrder.length() > MaintainContactManagementUIConstants.FOUR) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.SORT_ORDER_LENGTH,
								MaintainContactManagementUIConstants.ADD_SORT_ORDER_ID,
								MaintainContactManagementUIConstants.EDIT_SORT_ORDER_ID,
								actionCode);
			}
		} else {
			flag = false;

			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.SORT_ORDER_REQ,
					MaintainContactManagementUIConstants.ADD_SORT_ORDER_ID,
					MaintainContactManagementUIConstants.EDIT_SORT_ORDER_ID,
					actionCode);

		}
		return flag;
	}

	/**
	 * This operation is used to create new CustomField Record.
	 * 
	 * @return String.
	 */
	public String addCustomFields() {
		
		resetCustomField();

		customFieldDataBean.setShowAddCustomFields(true);
		customFieldDataBean.setCustomFieldVO(new CustomFieldVO());
		customFieldDataBean.getDropDownList().clear();
		customFieldDataBean.setShowEditCustomFields(false);
		customFieldDataBean.setShowDropDownScreenFlag(false);
		customFieldDataBean.setDropDownDataFlag(false);
		customFieldDataBean.setAddDropDownFlag(false);
		customFieldDataBean.setShowSucessMessage(false);
		customFieldDataBean.setShowDataType(false);
		customFieldDataBean.setShowDeletedMessage(false);
		customFieldDataBean.setShowDDDeletedMessage(false);
		customFieldDataBean.setLengthFlag(true);
		customFieldDataBean.loadScope();
		customFieldDataBean.setOldScope("");
		customFieldDataBean.setSortIndicator(false);
		return MaintainContactManagementUIConstants.SUCCESS;

	}

	/**
	 * This operation is used to reset the CustomField details in Add mode.
	 * 
	 * @return String : Navigation message.
	 */
	public String resetCustomField() {
		

		try {
			CustomFieldVO customFieldVO = customFieldDataBean
					.getCustomFieldVO();
			if (customFieldDataBean.isShowAddCustomFields()) {
				customFieldDataBean.setCustomFieldVO(new CustomFieldVO());
			} else {
				CustomFieldVO tempCustomFieldVO = new CustomFieldVO();

				int index = customFieldDataBean.getRowIndex();
				if (index < customFieldDataBean.getMaintainCustomFieldsList()
						.size()) {
					customFieldVO = (CustomFieldVO) customFieldDataBean
							.getMaintainCustomFieldsList().get(index);
				}

				BeanUtils.copyProperties(tempCustomFieldVO, customFieldVO);
				customFieldDataBean.setCustomFieldVO(tempCustomFieldVO);
			}
		} catch (IllegalAccessException e) {
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		} catch (InvocationTargetException e) {
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		}

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This operation is used to get CustomField Record details based on the
	 * primary key value cstmFldSk.
	 * 
	 * @return String.
	 */

	public String getCustomFieldDetails() {
		
		//		int index = getMaintainCFDataTable().getRowIndex();
		CustomFieldVO tempCustomFieldVO = new CustomFieldVO();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map
				.get(ContactManagementConstants.customfield_RowIndex);
		int index = Integer.parseInt(indexCode);
		CustomFieldVO customFieldVO = (CustomFieldVO) customFieldDataBean
				.getMaintainCustomFieldsList().get(index);
		
		

		if (customFieldVO != null) {
			UIComponent component = ContactManagementHelper
					.findComponentInRoot("auditlogDetailsforCustmFldsId");
			if (component != null) {
				AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
				auditHistoryTable
						.setAuditLogControllerBean(new AuditLogControllerBean());
			}

			try {
				BeanUtils.copyProperties(tempCustomFieldVO, customFieldVO);

			} catch (IllegalAccessException e) {
				if(log.isErrorEnabled())
				{
					log.error(e.getMessage(), e);
				}
			} catch (InvocationTargetException e) {
				if(log.isErrorEnabled())
				{
					log.error(e.getMessage(), e);
				}
			}

			List list = new ArrayList();
			list = tempCustomFieldVO.getDropDownList();
			Comparator comp = new CollatorComparator();
			Collections.sort(list, comp);

			//CustomFiedDOtoVOConverter.sortDropDownList(list,"Drop Down Item
			// Description",true);

			customFieldDataBean.setDropDownList(list);

			if (customFieldDataBean.getDropDownList().size() > 0)
			{
			// Below line added for defect ESPRD00795949 to display 1st page by default for Drop down data table. 
				customFieldDataBean.setDdstartIndex(0);
				customFieldDataBean.setShowDropDownScreenFlag(true);
				customFieldDataBean.setDropDownDataFlag(true);
				customFieldDataBean.setAddDropDownFlag(false);
				customFieldDataBean.setEditDropDownFlag(false);
				customFieldDataBean.setLengthFlag(false);
			} else {
				customFieldDataBean.setShowDropDownScreenFlag(false);
				customFieldDataBean.setDropDownDataFlag(false);
				customFieldDataBean.setLengthFlag(true);
			}
			try {
				customFieldDataBean.setLength(tempCustomFieldVO.getLength());
				customFieldDataBean.setOldScope(tempCustomFieldVO.getScope());

				customFieldDataBean.setOldDataType(tempCustomFieldVO
						.getDataType());

				value = contactMaintenanceDelegate
						.validateCustomFields(tempCustomFieldVO
								.getCustomFieldSK());

				List tempScopeList = new ArrayList();

				if (value.intValue() == 1) {
					tempScopeList.clear();

					tempScopeList.add(new SelectItem("G_CR_TB",
							"CORRESPONDENCE"));
					tempScopeList.add(new SelectItem("B", "BOTH"));
					customFieldDataBean.setScopeList(tempScopeList);
					customFieldDataBean.setShowDataType(true);
					customFieldDataBean.setGetRecords(true);
				} else if (value.intValue() == 2) {
					tempScopeList.clear();

					tempScopeList.add(new SelectItem("G_CASE_TB", "CASE"));
					tempScopeList.add(new SelectItem("B", "BOTH"));
					customFieldDataBean.setScopeList(tempScopeList);
					customFieldDataBean.setShowDataType(true);
					customFieldDataBean.setGetRecords(true);
				} else if (value.intValue() == 3) {
					tempScopeList.clear();
					tempScopeList.add(new SelectItem("B", "BOTH"));

					customFieldDataBean.setScopeList(tempScopeList);
					customFieldDataBean.setShowDataType(true);
					customFieldDataBean.setGetRecords(true);
				} else {
					tempScopeList.clear();
					tempScopeList.add(new SelectItem("G_CR_TB",
							"CORRESPONDENCE"));
					tempScopeList.add(new SelectItem("G_CASE_TB", "CASE"));
					tempScopeList.add(new SelectItem("B", "BOTH"));
					customFieldDataBean.setScopeList(tempScopeList);
					customFieldDataBean.setShowDataType(false);
					customFieldDataBean.setGetRecords(true);
				}
				customFieldDataBean.setCustomFieldVO(tempCustomFieldVO);

			} catch (CustomFieldFetchBusinessException e) {
				if(log.isErrorEnabled())
				{
					log.error(e.getMessage(), e);
				}
			}

			//to fix dropdown list reset issue
			List tempList = new ArrayList();
			tempList.addAll(list);
			customFieldDataBean.setTempDropDownList(tempList);
			customFieldDataBean.setCustomFieldVO(tempCustomFieldVO);
			customFieldDataBean.setRowIndex(index);
			customFieldDataBean.setShowEditCustomFields(true);
			customFieldDataBean.setShowAddCustomFields(false);
			customFieldDataBean.setShowSucessMessage(false);
			customFieldDataBean.setShowDeletedMessage(false);
			customFieldDataBean.setShowDDDeletedMessage(false);
			customFieldDataBean.setShowCFforAud(false);
			customFieldDataBean.setSortIndicator(true);
			//showAuditHistoryForCustomField();
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This operation is used to get all the Categories sorted on certain column
	 * and order.
	 * 
	 * @param event :
	 *            ActionEvent object.
	 * @return String
	 */
	public String getAllSortedCustomField(ActionEvent event) {
		
		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);

		customFieldDataBean.setImageRender(event.getComponent().getId());
		customFieldDataBean.setSortIndicator(true);
		customFieldDataBean.setStartIndex(0);
		sortcustomFields(sortColumn, sortOrder, customFieldDataBean
				.getMaintainCustomFieldsList());

		return MaintainContactManagementUIConstants.SUCCESS;

	}

	/**
	 * This operation is used to get all the Categories sorted on certain column
	 * and order.
	 * 
	 * @param sortColumn :
	 *            Column names to sort the columns
	 * @param sortOrder :
	 *            Oder Type to sort the Columns
	 * @param dataList :
	 *            List of the records to Sort
	 */
	private void sortcustomFields(final String sortColumn,
			final String sortOrder, List dataList) {
		

		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CustomFieldVO data1 = (CustomFieldVO) obj1;
				CustomFieldVO data2 = (CustomFieldVO) obj2;
				boolean ascending = false;
				if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
						.equals(sortOrder)) {
					ascending = true;
				}

				if (sortColumn == null) {
					return 0;
				}
				if ("columnDescription".equals(sortColumn)) {

					if (null == data1.getColumnDescription()) {
						data1
								.setColumnDescription(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getColumnDescription()) {
						data2
								.setColumnDescription(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getColumnDescription().trim()
							.compareToIgnoreCase(data2.getColumnDescription().trim())
							: data2.getColumnDescription().trim().compareToIgnoreCase(
									data1.getColumnDescription().trim());
				}
				if ("dataType".equals(sortColumn)) {
					if (null == data1.getDataType()) {
						data1
								.setDataType(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getDataType()) {
						data2
								.setDataType(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getDataType().trim().compareTo(
							data2.getDataType().trim()) : data2.getDataType()
							.trim().compareTo(data1.getDataType().trim());
				}
				if ("length".equals(sortColumn)) {

					if (null == data1.getLength()) {
						data1.setLength("0");
					}
					if (null == data2.getLength()) {
						data2.setLength("0");
					}

					/*
					 * return ascending ? data1.getLength().trim().compareTo(
					 * data2.getLength().trim()) : data2.getLength()
					 * .trim().compareTo(data1.getLength().trim());
					 */

					return ascending ? (new Integer(data1.getLength().trim())
							.compareTo(new Integer(data2.getLength().trim())))
							: (new Integer(data2.getLength().trim())
									.compareTo(new Integer(data1.getLength()
											.trim())));
				}
				if ("scope".equals(sortColumn)) {

					if (null == data1.getScope()) {
						data1
								.setScope(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getScope()) {
						data2
								.setScope(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getScope().trim().compareTo(
							data2.getScope().trim()) : data2.getScope().trim()
							.compareTo(data1.getScope().trim());
				}
				if ("active".equals(sortColumn)) {

					if (null == data1.getActiveInd()) {
						data1
								.setActiveInd(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getActiveInd()) {
						data2
								.setActiveInd(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getActiveInd().trim().compareTo(
							data2.getActiveInd().trim()) : data2.getActiveInd()
							.trim().compareTo(data1.getActiveInd().trim());
				}
				if ("required".equals(sortColumn)) {

					if (null == data1.getRequiredInd()) {
						data1
								.setRequiredInd(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getRequiredInd()) {
						data2
								.setRequiredInd(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getRequiredInd().trim().compareTo(
							data2.getRequiredInd().trim()) : data2
							.getRequiredInd().trim().compareTo(
									data1.getRequiredInd().trim());
				}
				if ("protected".equals(sortColumn)) {

					if (null == data1.getProtectedInd()) {
						data1
								.setProtectedInd(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getProtectedInd()) {
						data2
								.setProtectedInd(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getProtectedInd().trim()
							.compareTo(data2.getProtectedInd().trim()) : data2
							.getProtectedInd().trim().compareTo(
									data1.getProtectedInd().trim());
				}

				return 0;
			}

		};

		Collections.sort(dataList, comparator);
	}

	/**
	 * addCustomField This operation is used to create new CustomField Record.
	 * 
	 * @return String.
	 */
	public String createCustomField() {
		
		CustomFieldVO customFieldVO = customFieldDataBean.getCustomFieldVO();
		CustomFieldVO tempCustomFieldVO = new CustomFieldVO();
		String saveValues = "";
		boolean dropDown = false;
		if (customFieldDataBean.isDdFlag()
				&& !(customFieldDataBean.isDdSaveFlag())) {

			saveValues = saveDropDown();
			dropDown = customFieldDataBean.isFlagFordd();
		}

		boolean flag = validateCustomField(customFieldVO);

		if (customFieldDataBean.isDdFlag()
				&& !(customFieldDataBean.isDdSaveFlag())) {
			if (!dropDown)
				flag = false;
		}
		customFieldDataBean.setDdFlag(false);
		if (flag) {
			CustomFieldDOConverter customFieldDOConverter = new CustomFieldDOConverter();
			CustomFiedDOtoVOConverter customFiedDOtoVOConverter = new CustomFiedDOtoVOConverter();
			CustomField customField = null;
			List dropDownList = null;
			dropDownList = customFieldDataBean.getDropDownList();
			/*
			 * HttpServletRequest renderrequest = (HttpServletRequest)
			 * FacesContext
			 * .getCurrentInstance().getExternalContext().getRequest();
			 * HttpServletResponse renderresponse = null; EnterpriseUserProfile
			 * enterpriseUserProfile = getUserData( renderrequest,
			 * renderresponse); String userId = "TBOINAPALLY";
			 * 
			 * if (enterpriseUserProfile != null &&
			 * !enterpriseUserProfile.getUserId().equals("")) { userId =
			 * enterpriseUserProfile.getUserId(); }
			 */
			String userId = "TBOINAPALLY";
			FacesContext facesContext = FacesContext.getCurrentInstance();
			PortletRequest portletRequest = (PortletRequest) facesContext
					.getExternalContext().getRequest();
			userId = getLoggedInUserID(portletRequest);
			customFieldVO.setAuditUserID(userId);
			customFieldVO.setAddedAuditUserID(userId);

			if (customFieldVO != null) {
				List list = customFieldDataBean.getMaintainCustomFieldsList();
				for (int i = 0; i < list.size(); i++) {
					tempCustomFieldVO = (CustomFieldVO) list.get(i);
					if (tempCustomFieldVO.getColumnDescription()
							.equalsIgnoreCase(
									customFieldVO.getColumnDescription())) {
						setErrorMessage(MaintainContactManagementUIConstants.UNIQUE_DESC);

						customFieldDataBean.setShowSucessMessage(false);
						//customFieldDataBean.setShowDropDownScreenFlag(false);
						customFieldDataBean.setShowCFDescMsgFlag(true);
						customFieldDataBean.setShowDescMsgFlag(false);
						customFieldDataBean.setShowSuccessMsgFlag(false);

						flag = false;
						break;
					}
				}
				if (flag) {
					/** Call Helper Class to convert Vo o DO */
					customField = new CustomField();
					customField = customFieldDOConverter
							.convertCustomFieldVOToDO(customFieldVO,
									dropDownList);
					/** Call Delegate method to create CustomField */
					try {

						if (customField != null) {

							customField = contactMaintenanceDelegate
									.createCustomField(customField);
						}
					} catch (CustomFieldCreateBusinessException e) {
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
								new Object[] {},
								MessageUtil.ENTMESSAGES_BUNDLE, null);
						if(log.isErrorEnabled())
						{
							log.error(e.getMessage(), e);
						}
					} finally {

					}
					if (customField.getCustomFieldSK() != null) {
						customFieldVO = customFiedDOtoVOConverter
								.convertCustomFieldDOToVO(customField);
						if (customFieldVO.getDropDownList().size() > 0) {
							Comparator comp = new CollatorComparator();
							Collections.sort(customFieldVO.getDropDownList(),
									comp);
						}

						/*
						 * if (customFieldVO.getActiveInd().equalsIgnoreCase(
						 * "false")) {
						 * customFieldDataBean.getMaintainCustomFieldsList()
						 * .add(customFieldVO); }
						 */
						customFieldDataBean.getMaintainCustomFieldsList().add(
								customFieldVO);
						customFieldDataBean
								.setCustomFieldVO(new CustomFieldVO());
						//cancelCustomField();
						customFieldDataBean.setShowSucessMessage(true);
						resetCustomField();
						customFieldDataBean.setShowDropDownScreenFlag(false);
						customFieldDataBean.setAddDropDownFlag(false);
						customFieldDataBean.setEditDropDownFlag(false);
						customFieldDataBean
								.setCustomFieldVO(new CustomFieldVO());

					}
					customFieldDataBean.setGetRecords(true);
				}
			}
		} else {
			customFieldDataBean.setShowCFDescMsgFlag(true);
		}

		return MaintainContactManagementUIConstants.SUCCESS;

	}

	/**
	 * addCustomField This operation is used to create new CustomField Record.
	 * 
	 * @return String.
	 */
	public String updateCustomField() {
		
		CustomFieldVO customFieldVO = customFieldDataBean.getCustomFieldVO();
		CustomFieldVO tempCustomFieldVO = customFieldDataBean
				.getCustomFieldVO();
		CustomFieldVO testCustomFieldVO = null;

		/*
		 * HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest();
		 * HttpServletResponse renderresponse = null; EnterpriseUserProfile
		 * enterpriseUserProfile = getUserData( renderrequest, renderresponse);
		 * String userId = "TBOINAPALLY";
		 * 
		 * if (enterpriseUserProfile != null &&
		 * !enterpriseUserProfile.getUserId().equals("")) { userId =
		 * enterpriseUserProfile.getUserId(); }
		 */
		String userId = "TBOINAPALLY";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest) facesContext
				.getExternalContext().getRequest();
		userId = getLoggedInUserID(portletRequest);

		if (validateCustomField(customFieldVO)) {
			CustomFieldDOConverter customFieldDOConverter = new CustomFieldDOConverter();
			CustomField customField = null;
			List dropDownList = null;
			Boolean flag = Boolean.TRUE;
			dropDownList = customFieldDataBean.getDropDownList();

			customFieldVO.setOldScope(customFieldDataBean.getOldScope());
			customFieldVO.setOldDataType(customFieldDataBean.getOldDataType());
			customFieldVO.setAuditUserID(userId);
			customFieldVO.setAddedAuditUserID(userId);

			if (customFieldVO != null) {
				List list = customFieldDataBean.getMaintainCustomFieldsList();

				CustomFieldVO cfVO = (CustomFieldVO) list
						.get(customFieldDataBean.getRowIndex());
				list.remove(customFieldDataBean.getRowIndex());

				for (int i = 0; i < list.size(); i++) {
					testCustomFieldVO = new CustomFieldVO();
					testCustomFieldVO = (CustomFieldVO) list.get(i);

					if (testCustomFieldVO.getColumnDescription()
							.equalsIgnoreCase(
									customFieldVO.getColumnDescription())) {
						setErrorMessage(MaintainContactManagementUIConstants.UNIQUE_DESC);
						customFieldDataBean.setShowSucessMessage(false);
						customFieldDataBean.setEditDropDownFlag(false);
						customFieldDataBean.setAddDropDownFlag(false);
						customFieldDataBean.setShowCFDescMsgFlag(true);
						flag = Boolean.FALSE;
						customFieldDataBean.getMaintainCustomFieldsList().add(
								customFieldDataBean.getRowIndex(), cfVO);
						break;
					}
				}
				if (flag.booleanValue()) {
					/** Call Helper Class to convert Vo o DO */
					customField = new CustomField();
					customField = customFieldDOConverter
							.convertCustomFieldVOToDO(customFieldVO,
									dropDownList);
					
					/** Call Delegate method to create CustomField */
					try {

						if (customField != null) {
							CustomField updatedCustomField = contactMaintenanceDelegate
									.updateCustomField(customField);

							if (updatedCustomField.getCustomFieldSK() != null) {
								if(log.isDebugEnabled())
								{
									log.debug("updatedCustomField.getCustomFieldSK()  ::::::: "
																	+ updatedCustomField.getCustomFieldSK());
								}
								CustomFiedDOtoVOConverter customFiedDOtoVOConverter = new CustomFiedDOtoVOConverter();
								customFieldVO = customFiedDOtoVOConverter
										.convertCustomFieldDOToVO(updatedCustomField);
								tempCustomFieldVO.setDataTypestr(customFieldVO
										.getDataTypestr());
								tempCustomFieldVO
										.setColumnDescription(customFieldVO
												.getColumnDescription());
								tempCustomFieldVO.setScopeValue(customFieldVO
										.getScopeValue());
								tempCustomFieldVO.setLength(customFieldVO
										.getLength());

							}

							if (updatedCustomField != null) {
								CustomFiedDOtoVOConverter customFiedDOtoVOConverter = new CustomFiedDOtoVOConverter();
								List customDropDownList = customFiedDOtoVOConverter
										.convertDropDownDOToVO(updatedCustomField
												.getCustomFieldDropDownValues());
								Comparator comp = new CollatorComparator();
								Collections.sort(customDropDownList, comp);
								tempCustomFieldVO
										.setDropDownList(customDropDownList);
								customFieldDataBean
										.setDropDownList(customDropDownList);

							}
							if (updatedCustomField.getVoidIndicator()
									.toString().equalsIgnoreCase("true")) {
								tempCustomFieldVO.setActiveInd("false");
								tempCustomFieldVO.setActiveFlag("No");
							} else {
								tempCustomFieldVO.setActiveInd("true");
								tempCustomFieldVO.setActiveFlag("Yes");
							}
							if (updatedCustomField.getRequiredValueIndicator()
									.toString().equalsIgnoreCase("true")) {
								tempCustomFieldVO.setRequiredInd("true");
								tempCustomFieldVO.setRequiredFlag("Yes");
							} else {
								tempCustomFieldVO.setRequiredInd("false");
								tempCustomFieldVO.setRequiredFlag("No");
							}
							if (updatedCustomField.getValueProtectedIndicator()
									.toString().equalsIgnoreCase("true")) {
								tempCustomFieldVO.setProtectedInd("true");
								tempCustomFieldVO.setProtectedFlag("Yes");
							} else {
								tempCustomFieldVO.setProtectedInd("false");
								tempCustomFieldVO.setProtectedFlag("No");
							}

							if (flag.booleanValue()) {
								if (tempCustomFieldVO.getScope()
										.equalsIgnoreCase("B")) {
									tempCustomFieldVO.setScopeValue("BOTH");
								} else if (tempCustomFieldVO.getScope()
										.equalsIgnoreCase("G_CASE_TB")) {
									tempCustomFieldVO.setScopeValue("CASE");
								} else if (tempCustomFieldVO.getScope()
										.equalsIgnoreCase("G_CR_TB")) {
									tempCustomFieldVO
											.setScopeValue("CORRESPONDENCE");
								}

								tempCustomFieldVO
										.setVersionNo(tempCustomFieldVO
												.getVersionNo() + 1);
								customFieldDataBean
										.setOldScope(tempCustomFieldVO
												.getScope());
								customFieldDataBean
										.setOldDataType(tempCustomFieldVO
												.getDataType());
								customFieldDataBean.setLength(tempCustomFieldVO
										.getLength());
								/*
								 * String activeInd = tempCustomFieldVO
								 * .getActiveInd();
								 */

								/*
								 * if (activeInd.equalsIgnoreCase("false")) {
								 */
								customFieldDataBean
										.getMaintainCustomFieldsList().add(
												customFieldDataBean
														.getRowIndex(),
												tempCustomFieldVO);

								/*
								 * customFieldDataBean.getMaintainCustomFieldsList().remove(
								 * customFieldDataBean.getRowIndex() + 1);
								 */

								customFieldDataBean.setShowSucessMessage(true);
								customFieldDataBean.setEditDropDownFlag(false);
								customFieldDataBean.setAddDropDownFlag(false);

							}

						}
						customFieldDataBean.setGetRecords(true);
					} catch (CustomFieldUpdateBusinessException e) {
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
								new Object[] {},
								MessageUtil.ENTMESSAGES_BUNDLE, null);
						if(log.isErrorEnabled())
						{
							log.error(e.getMessage(), e);
						}
					} finally {

					}
				}

			}
		}

		return MaintainContactManagementUIConstants.SUCCESS;
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

			UIComponent uiComponent = ContactManagementHelper
					.findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);

		}

		facesContext.addMessage(clientId, fc);
	}

	/**
	 * Validates Custom Field to be not Null.
	 * 
	 * @param customFieldVO .
	 * @return boolean
	 */
	public boolean validateCustomField(CustomFieldVO customFieldVO) {
		
		boolean flag = true;

		String actionCode = null;
		if (customFieldDataBean.isShowAddCustomFields()) {
			actionCode = MaintainContactManagementUIConstants.ADD;
		} else {
			actionCode = MaintainContactManagementUIConstants.UPDATE;
		}

		if (customFieldVO.getColumnDescription() == null
				|| customFieldVO.getColumnDescription().length() == 0) {
			flag = false;
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.COLUMN_DESC_REQ,
					MaintainContactManagementUIConstants.ADD_COLUMN_DESC_ID,
					MaintainContactManagementUIConstants.EDIT_COLUMN_DESC_ID,
					actionCode);

		} else {
			String desc = customFieldVO.getColumnDescription();
			if (!ContactManagementHelper.validateAlphaNumericSpl(desc)) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.COLUMN_DESC_CHAR,
								MaintainContactManagementUIConstants.ADD_COLUMN_DESC_ID,
								MaintainContactManagementUIConstants.EDIT_COLUMN_DESC_ID,
								actionCode);
			} else if (desc.length() > MaintainContactManagementUIConstants.SIXTY_FOUR) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.COLUMN_DESC_LENTH,
								MaintainContactManagementUIConstants.ADD_COLUMN_DESC_ID,
								MaintainContactManagementUIConstants.EDIT_COLUMN_DESC_ID,
								actionCode);
			}
		}
		if (customFieldVO.getDataType()==null
				||customFieldVO.getDataType().length() == 0) {
			flag = false;
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.DATATYPE_REQ,
					MaintainContactManagementUIConstants.ADD_DATATYPE_ID,
					MaintainContactManagementUIConstants.EDIT_DATATYPE_ID,
					actionCode);
		} else if (customFieldVO.getDataType().equals("DD")
				&& customFieldDataBean.getDropDownList() != null) {
			if (customFieldDataBean.getDropDownList().size() == 0) {
				flag = false;
				setErrorMessage(MaintainContactManagementUIConstants.DROPDOWN_REQ);
			}
		}
		
		//<%--Code modified below for fixing the defect ESPRD00762539 on 06-03-2012 --%>
		
        /* if(! customFieldVO.getDataType().equals("DD"))
         {*/
		if (customFieldVO.getLength()==null
				||customFieldVO.getLength().length() == 0) {
			flag = false;
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.LENGTH_REQ,
					MaintainContactManagementUIConstants.ADD_LENGTH_ID,
					MaintainContactManagementUIConstants.EDIT_LENGTH_ID,
					actionCode);
		} 
		
         /*}*/
         
         if (!EnterpriseCommonValidator.validateNumeric(customFieldVO
				.getLength().trim())) {
			flag = false;
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.LENGTH_NUMRIC,
					MaintainContactManagementUIConstants.ADD_LENGTH_ID,
					MaintainContactManagementUIConstants.EDIT_LENGTH_ID,
					actionCode);

		} else {
			if (customFieldVO.getLength().trim().length() > MaintainContactManagementUIConstants.FOUR) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.LENGTH,
						MaintainContactManagementUIConstants.ADD_LENGTH_ID,
						MaintainContactManagementUIConstants.EDIT_LENGTH_ID,
						actionCode);
			} else if (customFieldDataBean.isShowEditCustomFields()) {
				//for fixing defect 673623
				try {
					Integer associatedVal=  contactMaintenanceDelegate
					.validateCustomFields(customFieldVO.getCustomFieldSK());
					if(associatedVal.intValue()!=0){
						int oldLength = Integer.parseInt(customFieldDataBean
								.getLength());
						int newLength = Integer.parseInt(customFieldVO.getLength());

					//	if (value.intValue() != 0) {
							if (newLength < oldLength) {
								flag = false;
								ContactManagementHelper
										.setErrorMessage(
												MaintainContactManagementUIConstants.LENGTH_DEC,
												MaintainContactManagementUIConstants.ADD_LENGTH_ID,
												MaintainContactManagementUIConstants.EDIT_LENGTH_ID,
												actionCode);
							}
					}
				} catch (CustomFieldFetchBusinessException e) {
					// TODO Auto-generated catch block
					if(log.isDebugEnabled())
					{
						log.debug(e);
					}
				}
			}
		}
		//}
		if (customFieldVO.getScope()==null
				||customFieldVO.getScope().length() == 0) {
			flag = false;
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.SCOPE_REQ,
					MaintainContactManagementUIConstants.ADD_SCOPE_ID,
					MaintainContactManagementUIConstants.EDIT_SCOPE_ID,
					actionCode);
		}
		return flag;
	}

	/**
	 * This operation is used to delete ContactManagement Category Record.
	 * 
	 * @return String.
	 */
	public String deleteCustomField() {
		
		/*
		 * HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest();
		 * HttpServletResponse renderresponse = null; EnterpriseUserProfile
		 * enterpriseUserProfile = getUserData( renderrequest, renderresponse);
		 * String userId = "TBOINAPALLY";
		 * 
		 * if (enterpriseUserProfile != null &&
		 * !enterpriseUserProfile.getUserId().equals("")) { userId =
		 * enterpriseUserProfile.getUserId(); }
		 */
		String userId = "TBOINAPALLY";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest) facesContext
				.getExternalContext().getRequest();
		userId = getLoggedInUserID(portletRequest);

		CustomFieldDOConverter customFieldDOConverter = new CustomFieldDOConverter();

		try {

			int index = customFieldDataBean.getRowIndex();
			Boolean isAssociated = Boolean.FALSE;

			CustomFieldVO customFieldVO = (CustomFieldVO) customFieldDataBean
					.getMaintainCustomFieldsList().get(index);
			int dropDownList = customFieldVO.getDropDownList().size();
			List list = null;

			if (value.intValue() == 0) {
				if (dropDownList > 0) {
					list = customFieldVO.getDropDownList();
				}

				CustomField customField = customFieldDOConverter
						.convertCustomFieldVOToDO(customFieldVO, list);

				isAssociated = contactMaintenanceDelegate.deleteCustomField(
						customField, userId);
			} else {
				setErrorMessage(MaintainContactManagementUIConstants.DELETE_RECORD_FAIL);
				customFieldDataBean.setShowSucessMessage(false);
				customFieldDataBean.setEditDropDownFlag(false);
				customFieldDataBean.setAddDropDownFlag(false);
				customFieldDataBean.setShowCFDescMsgFlag(true);
			}

			if (isAssociated.booleanValue()) {
				if (dropDownList > 0) {
					//customFieldDataBean.getDropDownList().remove(index);
					customFieldDataBean.getDropDownList().removeAll(list);
				}

				customFieldDataBean.getMaintainCustomFieldsList().remove(index);

				if (customFieldDataBean.getMaintainCustomFieldsList().size() == 0) {
					customFieldDataBean.setNoData(true);
				}
				customFieldDataBean.setCustomFieldVO(new CustomFieldVO());

				customFieldDataBean.setShowEditCustomFields(false);
				customFieldDataBean.setShowAddCustomFields(false);
				customFieldDataBean.setShowDropDownScreenFlag(false);
				customFieldDataBean.setDropDownDataFlag(false);
				customFieldDataBean.setAddDropDownFlag(false);
				customFieldDataBean.setShowDeletedMessage(true);
				customFieldDataBean.setSortIndicator(false);
				ContactManagementHelper.setErrorMessage(
						"success.customFields.deleteMessage", null,
						"showDeleteMessageID", "Update");
				customFieldDataBean.setShowDDDeletedMessage(false);
			}

		} catch (CustomFieldDeleteBusinessException e) {
			if(log.isDebugEnabled())
			{
				log.debug("Delete record is failed    " + e.getMessage());
			}

			
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
		} finally {

		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This operation is used to cancel any incomplete operation and go to the
	 * landing page.
	 * 
	 * @return String : Navigation message.
	 */
	public String cancelCustomField() {
		
		customFieldDataBean.setCustomFieldVO(new CustomFieldVO());
		customFieldDataBean.setShowEditCustomFields(false);
		customFieldDataBean.setShowAddCustomFields(false);
		customFieldDataBean.setShowDropDownScreenFlag(false);
		customFieldDataBean.setDropDownDataFlag(false);
		customFieldDataBean.setAddDropDownFlag(false);
		customFieldDataBean.setShowDDDeletedMessage(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * showing audit history for Custom Field details.
	 * 
	 * @return String
	 */
	public String showAuditHistoryForCustomField() {
		
		GlobalAuditsDelegate audit;
		final List list = new ArrayList();
		/*ArrayList auditlist = new ArrayList();*/
		try {

			audit = new GlobalAuditsDelegate();
			CustomFieldDOConverter customFieldDOConverter = new CustomFieldDOConverter();
			List dropDownlist = customFieldDataBean.getCustomFieldVO()
					.getDropDownList();
			//Gets Child Domain Object
			CustomField customField = customFieldDOConverter
					.convertCustomFieldVOToDO(customFieldDataBean
							.getCustomFieldVO(), dropDownlist);
			list.add(customField);
			//commented for unused variables
			//HashMap hm = audit.getAuditLogInfo(list);
			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(customField, 0,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
			/*auditlist = (ArrayList) hm.get(customField);*/
			customFieldDataBean.setCfAuditHistoryList(enterpriseResultVO
					.getSearchResults());
			customFieldDataBean.setCfAuditHistoryRender(true);
			customFieldDataBean.setCfAuditOpen(true);
			setRecordRange(enterpriseResultVO);
		} catch (GlobalAuditsBusinessException e) {
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			if(log.isDebugEnabled())
			{
			log.debug("Error in show audit history  " + e);
			}
		} finally {

		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * showing audit history for Custom Field details.
	 * 
	 * @return String
	 */
	public String showAuditHistoryForDropDown() {
		
		final List list = new ArrayList();
		/*ArrayList audlist = new ArrayList();*/
		try {

			CustomFieldDOConverter customFieldDOConverter = new CustomFieldDOConverter();
			//Gets Child Domain Object
			CustomField customField = customFieldDOConverter
					.convertCustomFieldVOToDO(customFieldDataBean
							.getCustomFieldVO(), customFieldDataBean
							.getDropDownList());

			list.add(customField);
			GlobalAuditsDelegate audit = new GlobalAuditsDelegate();
			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(customField, 0,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

			//commented for unused variables
			//HashMap hm = audit.getAuditLogInfo(list);

			/*audlist = (ArrayList) hm.get(customField);*/
			customFieldDataBean.setDdAuditHistoryList(enterpriseResultVO
					.getSearchResults());
			customFieldDataBean.setDdAuditHistoryRender(true);
			customFieldDataBean.setDdAuditOpen(true);
		} catch (GlobalAuditsBusinessException e) {
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			if(log.isDebugEnabled())
			{
				log.debug("Error in show audit history  " + e);
			}
		} finally {

		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * @return Returns the customFields.
	 */
	public String getCustomFields() {
		
		//commented for unused variables
		//long entryTime = System.currentTimeMillis();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		
		if (customFieldDataBean != null) {
			
			log.debug("CustomFieldControllerBean :: getCustomFields() :: customFieldDataBean not null");
			
			customFieldDataBean.getMaintainCustomFieldsList().clear();
			List customFieldsList = new ArrayList();
			CustomFieldSearchCriteriaVO customFieldSearchCriteriaVO = new CustomFieldSearchCriteriaVO();
			 if(!customFieldDataBean.isSortIndicator())
	         {
			try {
	           
	         
	        	
				customFieldSearchCriteriaVO.setAscending(true);
				customFieldsList = contactMaintenanceDelegate
						.getAllCustomFields(customFieldSearchCriteriaVO);
	         
	            
			} catch (CustomFieldFetchBusinessException e) {
				if(log.isErrorEnabled())
				{
					log.error(e.getMessage(), e);
				}
			} finally {
	
			}
			if (customFieldsList != null) {
				CustomFiedDOtoVOConverter customFiedDOtoVOConverter = new CustomFiedDOtoVOConverter();
				
				for (Iterator iter = customFieldsList.iterator(); iter.hasNext();) {
					CustomField customField = (CustomField) iter.next();
	
					CustomFieldVO customFieldVO = customFiedDOtoVOConverter
							.convertCustomFieldDOToVO(customField);
	
					customFieldDataBean.getMaintainCustomFieldsList().add(
							customFieldVO);
	
				}
				customFieldDataBean.setGetRecords(true);
			}
	      }
			boolean value = (customFieldDataBean.getMaintainCustomFieldsList()
					.size() > 0) ? false : true;
			customFieldDataBean.setNoData(value);
			
		} else {
			
			log.error("CustomFieldControllerBean :: getCustomFields() :: customFieldDataBean is null");
			
		}

		//commented for unused variables
		//long exitTime = System.currentTimeMillis();

		return MaintainContactManagementUIConstants.EMPTY_STRING;
	}

	/**
	 * @param customFields
	 *            The customFields to set.
	 */
	public void setCustomFields(String customFields) {
		this.customFields = customFields;
	}

	public static void setErrorMessage(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().setMessageBundle(
				ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE);
		ResourceBundle bundle = resourceBundle(facesContext);
		String errorMsg = bundle.getString(message);
		FacesMessage fm = new FacesMessage(errorMsg);
		facesContext.addMessage(null, fm);
	}

	/**
	 * This Method is used to Map to Resource Bundle.
	 * 
	 * @param facesContext -
	 *            FacesContext.
	 * @return ResourceBundle after mapping
	 */
	public static ResourceBundle resourceBundle(FacesContext facesContext) {
		UIViewRoot root = facesContext.getViewRoot();
		String messageBundle = facesContext.getApplication().getMessageBundle();
		Locale locale = root.getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);

		return bundle;
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
		getCustomFieldDataBean().setCfAuditHistoryRender(true);
		getCustomFieldDataBean().setAuditColumnHistoryRender(false);
		long exitTime = System.currentTimeMillis();
		if(log.isInfoEnabled())
		{
			log.info("CustomFieldControllerBean" + "#" + "showAuditLog" + "#" + (exitTime - entryTime));
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	public String ddShowAuditLog() throws GlobalAuditsBusinessException {
		long entryTime = System.currentTimeMillis();
		getCustomFieldDataBean().setDdAuditHistoryRender(true);
		getCustomFieldDataBean().setDdAuditColumnHistoryRender(false);
		long exitTime = System.currentTimeMillis();
		if(log.isInfoEnabled())
		{
		log.info("CustomFieldControllerBean" + "#" + "showAuditLog" + "#" + (exitTime - entryTime));
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This operation will close audit log.
	 * 
	 * @return String
	 */
	public String closeAuditLog() {
		long entryTime = System.currentTimeMillis();
		
		getCustomFieldDataBean().setCfAuditHistoryRender(false);
		long exitTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("CustomFieldControllerBean" + "#" + "closeAuditLog"
					+ (exitTime - entryTime));
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	public String ddCloseAuditLog() {
		long entryTime = System.currentTimeMillis();
		
		getCustomFieldDataBean().setDdAuditHistoryRender(false);
		long exitTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("CustomFieldControllerBean" + "#" + "closeAuditLog"
					+ (exitTime - entryTime));
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	public void setRecordRange(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		long entryTime = System.currentTimeMillis();
		int listSize;
		
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		
		CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();
		customFieldDataBean.setCount((int) totalRecordCount);

		int noOfPages = customFieldDataBean.getCount()
				/ customFieldDataBean.getItemsPerPage();

		int modNofPages = customFieldDataBean.getCount()
				% customFieldDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}
		

		customFieldDataBean
				.setCurrentPage(ProgramConstants.INITIAL_CURRENT_PAGE);
		customFieldDataBean.setNumberOfPages(noOfPages);
		customFieldDataBean.setStartRecord(ProgramConstants.START_RECORD);
		customFieldDataBean.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = customFieldDataBean.getCount();

		
		if (listSize <= customFieldDataBean.getItemsPerPage()) {
			customFieldDataBean.setEndRecord(listSize);
		}
		if (listSize > customFieldDataBean.getItemsPerPage()) {
			listSize = customFieldDataBean.getItemsPerPage();
		}

		if (customFieldDataBean.getCount() <= customFieldDataBean
				.getItemsPerPage()) {
			customFieldDataBean.setShowNext(false);
		} else {
			customFieldDataBean.setShowNext(true);
		}
		customFieldDataBean.setShowPrevious(false);
		if (log.isDebugEnabled()) {
			log.debug("The no. of pages"
					+ customFieldDataBean.getNumberOfPages());
			log.debug("The current no. of page"
					+ customFieldDataBean.getCurrentPage());
			log.debug("The start record......."
					+ customFieldDataBean.getStartRecord());
			log.debug("The end record......."
					+ customFieldDataBean.getEndRecord());
			log
					.debug("The total count......."
							+ customFieldDataBean.getCount());
		}
		long exitTime = System.currentTimeMillis();
		if(log.isInfoEnabled())
		{
		log.info("DiagnosisCodeControllerBean" + "#" + "setRecordRange" + "#"
				+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}
	}

	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}

	public String showColumnChange() {
		long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
			getCustomFieldDataBean().setAuditColumnHistoryRender(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getCustomFieldDataBean().setSelectedAuditLog(auditLog);
		} catch (Exception e) {
			if(log.isErrorEnabled())
			{
			log.debug("Error in show column change  " + e);
			}
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	public String ddShowColumnChange() {
		//commented for unused variables
		//long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
			getCustomFieldDataBean().setDdAuditColumnHistoryRender(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getCustomFieldDataBean().setDdSelectedAuditLog(auditLog);
			getCustomFieldDataBean().setDdAuditColumnHistoryRender(true);
		} catch (Exception e) {
			if(log.isDebugEnabled())
			{
				log.debug("Error in show column change  " + e);
			}
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
		
		CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();
		EnterpriseBaseDataBean entDataBean = previousPage(customFieldDataBean,
				customFieldDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("DiagnosisCodeControllerBean" + "#" + "previous" + "#"
					+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}

		return ProgramConstants.PREVIOUS;
	}

	public String ddPrevious() {
		long entryTime = System.currentTimeMillis();
		
		CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();
		EnterpriseBaseDataBean entDataBean = previousPage(customFieldDataBean,
				customFieldDataBean.getDdItemsPerPage());
		ddPopulateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("DiagnosisCodeControllerBean" + "#" + "previous" + "#"
					+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}

		return ProgramConstants.PREVIOUS;
	}

	/**
	 * This method will be used for writing the logic for populating search
	 * results for a given search criteria.
	 */
	private void populateList(int currentPage) {
		//	ArrayList searchList = new ArrayList(); // Find bug issue
		CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();
		GlobalAuditsDelegate globalAuditsDelegate = null;
		try {
			globalAuditsDelegate = new GlobalAuditsDelegate();
			CustomFieldDOConverter customFieldDOConverter = new CustomFieldDOConverter();
			List dropDownlist = customFieldDataBean.getCustomFieldVO()
					.getDropDownList();
			CustomField customField = customFieldDOConverter
					.convertCustomFieldVOToDO(customFieldDataBean
							.getCustomFieldVO(), dropDownlist);

			EnterpriseSearchResultsVO enterpriseResultVO = globalAuditsDelegate
					.getAuditLogList(customField, (currentPage - 1)
							* ProgramNumberConstants.INT_TEN,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

			customFieldDataBean.setCfAuditHistoryList(enterpriseResultVO
					.getSearchResults());
			customFieldDataBean.setCfAuditHistoryRender(true);
		} catch (Exception e) {
			if(log.isDebugEnabled())
			{
			log.debug(e);
			}
		}
	}

	private void ddPopulateList(int currentPage) {
		// ArrayList searchList = new ArrayList(); // Find bug issue
		CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();
		GlobalAuditsDelegate globalAuditsDelegate = null;
		try {
			globalAuditsDelegate = new GlobalAuditsDelegate();
			CustomFieldDOConverter customFieldDOConverter = new CustomFieldDOConverter();
			CustomField customField = customFieldDOConverter
					.convertCustomFieldVOToDO(customFieldDataBean
							.getCustomFieldVO(), customFieldDataBean
							.getDropDownList());

			EnterpriseSearchResultsVO enterpriseResultVO = globalAuditsDelegate
					.getAuditLogList(customField, (currentPage - 1)
							* ProgramNumberConstants.INT_TEN,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

			customFieldDataBean.setDdAuditHistoryList(enterpriseResultVO
					.getSearchResults());
			customFieldDataBean.setDdAuditHistoryRender(true);
		} catch (Exception e) {
			if(log.isDebugEnabled())
			{
			log.debug(e);
			}
		}
	}

	public String next() {
		long entryTime = System.currentTimeMillis();
		CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();
		if (log.isDebugEnabled()) {
			log.debug("The no. of pages"
					+ customFieldDataBean.getNumberOfPages());
			log.debug("The current no. of page"
					+ customFieldDataBean.getCurrentPage());
			log.debug("The start record......."
					+ customFieldDataBean.getStartRecord());
			log.debug("The end record......."
					+ customFieldDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(customFieldDataBean,
				customFieldDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("SearchSystemParameterControllerBean" + "#" + "next" + "#"
					+ (exitTime - entryTime));
		}

		return ProgramConstants.NEXT;
	}

	public String ddNext() {
		long entryTime = System.currentTimeMillis();
		CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();
		if (log.isDebugEnabled()) {
			log.debug("The no. of pages"
					+ customFieldDataBean.getNumberOfPages());
			log.debug("The current no. of page"
					+ customFieldDataBean.getCurrentPage());
			log.debug("The start record......."
					+ customFieldDataBean.getStartRecord());
			log.debug("The end record......."
					+ customFieldDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(customFieldDataBean,
				customFieldDataBean.getDdItemsPerPage());
		ddPopulateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("SearchSystemParameterControllerBean" + "#" + "next" + "#"
					+ (exitTime - entryTime));
		}

		return ProgramConstants.NEXT;
	}

	public String closeColumnChange() {
		getCustomFieldDataBean().setAuditColumnHistoryRender(false);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	public String ddCloseColumnChange() {
		getCustomFieldDataBean().setDdAuditColumnHistoryRender(false);
		return ContactManagementConstants.RENDER_SUCCESS;
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
		
		customFieldDataBean.setCustomFieldVO(new CustomFieldVO());
		customFieldDataBean.setShowEditCustomFields(false);
		customFieldDataBean.setShowAddCustomFields(false);
		customFieldDataBean.setShowDropDownScreenFlag(false);
		customFieldDataBean.setDropDownDataFlag(false);
		customFieldDataBean.setAddDropDownFlag(false);
		customFieldDataBean.setShowDDDeletedMessage(false);
		
		Object obj = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		ActionRequest request = (ActionRequest) obj;
		String s = request.getParameter("com.ibm.faces.portlet.page.view");
		
		Object object = FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		ActionResponse response = (ActionResponse) object;

		String redirectCosumerContext=request.getParameter("redirectPortalPageContext");
		
        if(redirectCosumerContext!=null){
                response.sendRedirect(redirectCosumerContext+s);
        }else{
                response.sendRedirect(s);
        }

		FacesContext.getCurrentInstance().responseComplete();

	}

	public static class CollatorComparator implements Comparator {
		Collator collator = Collator.getInstance();

		public int compare(Object obj1, Object obj2) {
			DropDownVO customFieldVO1 = (DropDownVO) obj1;
			DropDownVO customFieldVO2 = (DropDownVO) obj2;
			String val1 = (customFieldVO1.getDropDownItemDesc() == null) ? ""
					: customFieldVO1.getDropDownItemDesc();
			String val2 = (customFieldVO2.getDropDownItemDesc() == null) ? ""
					: customFieldVO2.getDropDownItemDesc();
			CollationKey key1 = collator.getCollationKey(val1);
			CollationKey key2 = collator.getCollationKey(val2);
			return key1.compareTo(key2);
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

	public String doAuditForCF() {
		CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();
		List cfAudList = customFieldDataBean.getMaintainCustomFieldsList();

		try {
			if (cfAudList != null && !(cfAudList.isEmpty())) {
				
				
				// Commented for defect ESPRD00785040
				
				/*Iterator auditKeyIt = cfAudList.iterator();
				if (auditKeyIt != null) {
					List cfEditList = new ArrayList();

					cfEditList.add(createAuditableFeild("Column Description",
							"description"));
					cfEditList.add(createAuditableFeild("Data Type",
							"dataTypeCode"));
					cfEditList.add(createAuditableFeild("Length",
							"totalLengthInBytes"));
					cfEditList.add(createAuditableFeild("Scope", "tableName"));
					cfEditList.add(createAuditableFeild("Active",
							"voidIndicator"));
					cfEditList.add(createAuditableFeild("Required",
							"requiredValueIndicator"));
					cfEditList.add(createAuditableFeild("Protected",
							"valueProtectedIndicator"));

					while (auditKeyIt.hasNext()) {
						CustomFieldVO customFieldVO = (CustomFieldVO) auditKeyIt
								.next();
						if (customFieldVO.getAuditKeyList() != null
								&& !(customFieldVO.getAuditKeyList().isEmpty())) {
							AuditDataFilter.filterAuditKeys(cfEditList,
									customFieldVO);
							
						} else {
							
						}

					}*/
					int cfIndex = customFieldDataBean.getRowIndex();
					if (cfIndex > 0) 
					{
						customFieldDataBean.setCustomFieldVO((CustomFieldVO) cfAudList.get(cfIndex));
					}
				//}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		customFieldDataBean.setShowCFforAud(true);

		return "";

	}

	private AuditableField createAuditableFeild(String feildName,
			String domainAttributeName) {
		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);
		return auditableField;

	}

	public String doAuditforcfDropDown() {
		
		CustomFieldDataBean customFieldDataBean = getCustomFieldDataBean();
		List cfDropDownAudList = customFieldDataBean.getDropDownList();

		try {
			if (cfDropDownAudList != null && !(cfDropDownAudList.isEmpty())) {
				Iterator auditKeyIt = cfDropDownAudList.iterator();
				if (auditKeyIt != null) {
					List cfDropDownEditList = new ArrayList();

					cfDropDownEditList.add(createAuditableFeild(
							"Drop Down Item Description",
							"customFieldPickLastValue"));
					cfDropDownEditList.add(createAuditableFeild("Sort Order",
							"displaySortOrderNumber"));

					while (auditKeyIt.hasNext()) {
						DropDownVO customFieldVO2 = (DropDownVO) auditKeyIt
								.next();
						if (customFieldVO2.getAuditKeyList() != null
								&& !(customFieldVO2.getAuditKeyList().isEmpty())) {
							
							AuditDataFilter.filterAuditKeys(cfDropDownEditList,
									customFieldVO2);
							
						} else {
							
						}

					}

					String ddIndex = customFieldDataBean.getDropDownIndex();
					if (ddIndex != null && !(ddIndex.equalsIgnoreCase(""))) {
						customFieldDataBean.getCustomFieldVO().setDropDownVO(
								(DropDownVO) cfDropDownAudList.get(Integer
										.parseInt(ddIndex)));
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		customFieldDataBean.setShowCFforAuddropdown(true);

		return "";

	}

	/**
	 * @return Returns the loadUserPermission.
	 */
	public String getLoadUserPermission() {
		getUserPermission();
		return loadUserPermission;
	}

	/**
	 * @param loadUserPermission
	 *            The loadUserPermission to set.
	 */
	public void setLoadUserPermission(String loadUserPermission) {
		this.loadUserPermission = loadUserPermission;
	}
}