/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Alert;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.vo.AlertVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.AlertDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.common.view.validator.DateValidator;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;

/**
 * @author Wipro This class is a controller bean used for Add/update operation
 *         in Routing functionality.
 */
public class AlertControllerBean extends EnterpriseBaseControllerBean {
	/** Instance of the Enterprise Logger */
	/*
	 * private transient EnterpriseLogger logger = EnterpriseLogFactory
	 * .getLogger(AlertControllerBean.class);
	 */
	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(AlertControllerBean.class);

	
	/**
	 * A variable of type AlertDataBean to hold the instance of data bean.
	 */
	//private AlertDataBean alertDataBean = getAlertDataBean();
	private AlertDataBean alertDataBean;

	//private CorrespondenceDataBean correspondenceDataBean =
	// getCorrespondenceDataBean();
	private CorrespondenceDataBean correspondenceDataBean;

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
	 * This field is used to store whether user has permission.
	 */
	private boolean controlRequired = true;

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

	/**
	 * This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermission() {
		String userPermission = "";
		String userid = getUserIDForSecurity();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.LOG_CORRESPONDENCE_PAGE, userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode", userPermission);

		// check the permission level for the user and set it in a boolean
		// variable. This will be used for Buttons/Links
		if (userPermission.equals("r")) {
			controlRequired = false;
		}
	}

	/**
	 * This method get the User ID
	 * 
	 * @return String
	 */
	private String getUserIDForSecurity() {
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
			
		}

		return userID;
	}

	/**
	 * @return String
	 */
	public String addAlert() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		correspondenceDataBean.setCrSaved(false);
		/*
		 * if (alertDataBean.isRenderAddAlert()) {
		 * logger.debug("isRenderAddAlert");
		 * 
		 * setErrorMessage(
		 * ContactManagementConstants.ERR_REF_ONE_ADD_AT_A_TIME, new Object[]
		 * {ContactManagementConstants.ALERT},
		 * ProgramConstants.PROGRAM_MESSAGES_BUNDLE, null);
		 *  } else {
		 * 
		 * logger.debug("else in addAlert");
		 */
		alertDataBean.setAlertVO(new AlertVO());
		cancelIncompleteAction();
		AlertDOConvertor alertDOConvertor = new AlertDOConvertor();
		alertDOConvertor.getReferenceData();

		addCrspdAlertRec();
		// Added for DEFECT ESPRD00772108 - For Notify Alert
		alertDataBean.setRenderAddAlert(true);
		

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is used to set Default user .
	 */
	private void setDefaultUserToNotify() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		//String userId = getLoggedInUser();

		Long userSK = getUserSK(getLoggedInUser());
		if(logger.isDebugEnabled())
		{
			logger.debug("UserSK " + userSK);
		}
		/*
		 * if (userSK != null &&
		 * !alertDataBean.getListOfUsersToNotify().contains(userSK)) {
		 * SelectItem selectItem = new SelectItem();
		 * 
		 * String userName = getUserName(userSK); StringBuffer userLabel = new
		 * StringBuffer().append( userSK.toString()).append(
		 * ContactManagementConstants.SPACE_STRING).append(userName);
		 * 
		 * selectItem.setValue(userSK.toString());
		 * selectItem.setLabel(userLabel.toString());
		 * 
		 * alertDataBean.getListOfUsersToNotify().add(selectItem); }
		 */
		alertDataBean.getAlertVO().setUserWorkUnitSK(userSK.toString());

	}

	/**
	 * @param user :
	 *            The user to set.
	 * @return Long
	 */
	private Long getUserSK(String user) {

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		Long userSK = null;

		try {
			userSK = contactMaintenanceDelegate.getWorkUnitID(user);
			if(logger.isDebugEnabled())
			{
				logger.debug("  &&&&&userSK :" + userSK);
			}
		} catch (LOBHierarchyFetchBusinessException e) {
			if(logger.isErrorEnabled()){
				logger.error(e.getMessage(), e);
			}
		}

		return userSK;
	}

	/**
	 * This methos is used to add CrAlert Rec.
	 */
	private void addCrspdAlertRec() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		/*
		 * if (!requiredValuesAvbl()) { return; }
		 */

		String categorySK = correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().getCategorySK();

		if (!isNullOrEmptyField(categorySK)) {
			getListOfUsers(categorySK);
			setDefaultUserToNotify();
		}
          
		// Commented for DEFECT ESPRD00772108 - For Notify Alert
		// alertDataBean.setRenderAddAlert(true);

	}

	/**
	 * This method is used o get list of users.
	 * 
	 * @param catSK
	 *            The catSK to set.
	 */
	private void getListOfUsers(String catSK) {
		
		Long categorySK = Long.valueOf(catSK);
		if(logger.isDebugEnabled())
		{
			logger.debug("&&&&&&&&&&&&&categorySK  :" + categorySK);
		}
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		CMDelegate cmDelegate = new CMDelegate();

		try {
			List tempListOfUsers = cmDelegate.getNotifyViaUsers(categorySK);

			List listOfUsers = alertDataBean.getListOfUsersToNotify();
			listOfUsers.clear();

			if (tempListOfUsers != null) {
				for (Iterator iter = tempListOfUsers.iterator(); iter.hasNext();) {
					Object[] user = (Object[]) iter.next();

					/*logger.debug("user id " + user[1]);*/

					String userDesc = ContactManagementConstants.EMPTY_STRING;

					if (user[2] != null
							&& !isNullOrEmptyField(user[2].toString())) {
						userDesc = (String) user[2];
					}
					if ((user[2] != null && !isNullOrEmptyField(user[2]
							.toString()))
							&& (user[3] != null && !isNullOrEmptyField(user[3]
									.toString()))) {
						userDesc = userDesc
								+ ContactManagementConstants.COMMA_SEPARATOR
								+ ContactManagementConstants.SPACE_STRING;
					}
					if (user[3] != null
							&& !isNullOrEmptyField(user[3].toString())) {
						userDesc = userDesc + user[3].toString();
					}
					StringBuffer userLabel = new StringBuffer()
							.append(userDesc)
							.append(ContactManagementConstants.HYPHEN_SEPARATOR)
							.append(user[1]);
					listOfUsers.add(new SelectItem(user[0].toString(),
							userLabel.toString()));

				}
			}
		} catch (CorrespondenceRecordFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * @return String
	 */
	private String getLoggedInUser() {
		
		String loggedInUser = getUserID();
		if(logger.isDebugEnabled())
		{
			logger.debug("User Id is " + loggedInUser);
		}
		return loggedInUser;
	}

	/**
	 * This method is used to get the User ID.
	 * 
	 * @return String : User ID
	 */
	public String getUserID() {
		

		/*
		 * String userId = "USERID1"; FacesContext fc =
		 * FacesContext.getCurrentInstance();
		 * 
		 * HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest();
		 * HttpServletResponse renderResponse = null;
		 * 
		 * EnterpriseUserProfile eup = getUserData(renderRequest,
		 * renderResponse);
		 * 
		 * if (eup != null && !isNullOrEmptyField(eup.getUserId())) { userId =
		 * eup.getUserId(); }
		 */

		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest) facesContext
				.getExternalContext().getRequest();
		String userId = getLoggedInUserID(portletRequest);

		return userId;
	}

	/**
	 * @param user
	 *            The user to set.
	 * @return String
	 */
	private String getUserFullName(EnterpriseUser user) {

		String userDesc = ContactManagementConstants.EMPTY_STRING;

		if (!isNullOrEmptyField(user.getLastName())) {
			userDesc = user.getLastName();
		}
		if (!isNullOrEmptyField(user.getLastName())
				&& !isNullOrEmptyField(user.getFirstName())) {
			userDesc = userDesc + ContactManagementConstants.COMMA_SEPARATOR
					+ ContactManagementConstants.SPACE_STRING;
		}
		if (!isNullOrEmptyField(user.getFirstName())) {
			userDesc = userDesc + user.getFirstName();
		}

		return userDesc;
	}

	/**
	 * @return String
	 */

	public String saveAlert() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		alertDataBean.setShowSucessMessage(false);
		correspondenceDataBean.setInputHiddenId("log_div_alerts");
		AlertVO alertVO = alertDataBean.getAlertVO();
		String sortOrder = "descending";
		String sortColumn = "Due Date";
		if (validateAlert()) {
			/**
			 * Code is added below for not retaining the error messages 
			 * on Log Correspondence page for the Defect ESPRD00852896
			 */
			correspondenceDataBean.setLogCRErrMsgFlag(false);
			// Changing date format MMDDYYYY or MM-DD-YYYY to MM/DD/YYYY
			alertVO.setDueDate(ContactHelper.convertDate(alertVO.getDueDate()));

			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

			try {
				WorkUnit workUnit = contactMaintenanceDelegate.getWorkUnit(Long
						.valueOf(alertVO.getUserWorkUnitSK()));
				if(logger.isDebugEnabled())
				{
					logger.debug("&&&&&&&&workUnit   :" + workUnit);
					logger.debug("&&&&&&&&userworkUnitSk   :" + Long.valueOf(alertVO.getUserWorkUnitSK()));
				}
				alertVO.setUserId(workUnit.getEnterpriseUser().getUserID());

			} catch (LOBHierarchyFetchBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}

			AlertDOConvertor.setUserNameFromList(alertVO);
			AlertDOConvertor.setDescForAlertValidValues(alertVO);
			alertDataBean.getListOfAlertVOs().add(alertVO);

			alertDataBean.setAlertVO(new AlertVO());
			//for ESPRD00726567
			//setDefaultUserToNotify();
			alertDataBean.setShowSucessMessage(true);
			alertDataBean.setRenderNoData(false);
			correspondenceDataBean.setAlertSaved(true);

			alertsComparator(sortColumn, sortOrder, alertDataBean
					.getListOfAlertVOs());
		}
		/**
		 * Code is added below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
		else
		{
			correspondenceDataBean.setLogCRErrMsgFlag(true);
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @param alertVO
	 *            The alertVO to set.
	 */
	private void changeCRStatusForAutoAlert(AlertVO alertVO) {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);

		if (ContactManagementConstants.AUTOMATIC_ALERT_TYPE_APPROVAL
				.equalsIgnoreCase(alertVO.getAlertType())) {
			// Change CR Status based on Alert Status for Alert Type Approval

			if (ContactManagementConstants.ALERT_STATUS_APPROVED
					.equalsIgnoreCase(alertVO.getStatus())) {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setStatusCode(
								ContactManagementConstants.CR_STATUS_APPROVED);

				AlertVO alertVOToSend = getAlertToSend();
				alertVOToSend
						.setAlertType(ContactManagementConstants.ALERT_TYPE_CODE_AU);
				alertVOToSend.setAlertTypeDesc(AlertDOConvertor
						.getDescriptionFromVV(
								ContactManagementConstants.ALERT_TYPE_CODE_AU,
								alertDataBean.getRefAlertTypeList()));

				alertVOToSend
						.setStatus(ContactManagementConstants.ALERT_STATUS_REVIEW_APPROVED);
				alertVOToSend
						.setStatusDesc(AlertDOConvertor
								.getDescriptionFromVV(
										ContactManagementConstants.ALERT_STATUS_REVIEW_APPROVED,
										alertDataBean.getRefAlertStatusList()));

				alertVOToSend
						.setDescription(ContactManagementConstants.AUTOMATIC_ALERT_REVIEWED_DESC);
				alertDataBean.getListOfAlertVOs().add(alertVOToSend);
			}
			if (ContactManagementConstants.ALERT_STATUS_DENIED
					.equalsIgnoreCase(alertVO.getStatus())) {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setStatusCode(
								ContactManagementConstants.CR_STATUS_OPEN);
				AlertVO alertVOToSend = getAlertToSend();
				alertVOToSend
						.setAlertType(ContactManagementConstants.ALERT_TYPE_CODE_AU);
				alertVOToSend.setAlertTypeDesc(AlertDOConvertor
						.getDescriptionFromVV(
								ContactManagementConstants.ALERT_TYPE_CODE_AU,
								alertDataBean.getRefAlertTypeList()));
				alertVOToSend
						.setStatus(ContactManagementConstants.ALERT_STATUS_REVIEW_DENIED);
				alertVOToSend
						.setStatusDesc(AlertDOConvertor
								.getDescriptionFromVV(
										ContactManagementConstants.ALERT_STATUS_REVIEW_DENIED,
										alertDataBean.getRefAlertStatusList()));
				alertVOToSend
						.setDescription(ContactManagementConstants.AUTOMATIC_ALERT_DENIED_DESC);
				alertDataBean.getListOfAlertVOs().add(alertVOToSend);
			}
		}
	}

	/*
	 * Method returns AlertVO
	 */
	private AlertVO getAlertToSend() {
		
		AlertVO alertVo = new AlertVO();
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.roll(Calendar.DAY_OF_MONTH, 1);
		alertVo.setDueDate(dateFormat.format(calendar.getTime()));

		/*
		 * Correspondence correspondence=new CorrespondenceDOConvertor().
		 * convertCorrespondenceVOToDO(correspondenceDataBean.
		 * getCorrespondenceRecordVO()); WorkUnit assignToWorkUnit =
		 * correspondence.getCorrespondenceAssignToWorkUnit(); EnterpriseUser
		 * enterpriseUser=assignToWorkUnit.getEnterpriseUser();
		 */
		String assignedToWorkUnitName = correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
				.getAssignedToWorkUnitName();
		if(logger.isInfoEnabled())
		{
			logger.info("assignedToWorkUnitName========" + assignedToWorkUnitName);
		}
		if (assignedToWorkUnitName != null
				&& StringUtils.isNotEmpty(assignedToWorkUnitName)) {
			int index = assignedToWorkUnitName.indexOf("-");
			alertVo.setUserId(assignedToWorkUnitName.substring(index + 1,
					assignedToWorkUnitName.length()));
			alertVo.setUserWorkUnitSK(correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
					.getAssignedToWorkUnitSK());
			alertVo.setUserWorkUnitDesc(assignedToWorkUnitName.substring(0,
					index));
		}
		alertVo.setAddedAuditUserID(getUserID());
		alertVo.setAuditUserID(getUserID());
		alertVo.setDbRecord(false);

		return alertVo;
	}

	/**
	 *  
	 */
	private void checkForAlertStatus() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		String alertStatus = alertDataBean.getAlertVO().getStatus();

		if (ContactManagementConstants.ALERT_STATUS_COMPLETED
				.equalsIgnoreCase(alertStatus)
				|| ContactManagementConstants.ALERT_STATUS_APPROVED
						.equalsIgnoreCase(alertStatus)
				|| ContactManagementConstants.ALERT_STATUS_DENIED
						.equalsIgnoreCase(alertStatus)
				|| ContactManagementConstants.ALERT_STATUS_VOID
						.equalsIgnoreCase(alertStatus)) {
			alertDataBean.setRowEnabled(false);
		}
		// Added for defect ESPRD00725542
		else alertDataBean.setRowEnabled(true);
								
	}
	

	/**
	 * @return String
	 */
	public String resetAlert() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		AlertVO alertVO = alertDataBean.getAlertVO();

		alertVO.setDueDate(ContactManagementConstants.EMPTY_STRING);
		alertVO.setAlertType(ContactManagementConstants.EMPTY_STRING);
		alertVO.setAlertTypeDesc(ContactManagementConstants.EMPTY_STRING);
		alertVO.setDescription(ContactManagementConstants.EMPTY_STRING);
		alertVO.setUserWorkUnitSK(ContactManagementConstants.EMPTY_STRING);
		alertVO.setUserWorkUnitDesc(ContactManagementConstants.EMPTY_STRING);
		alertVO.setStatus(ContactManagementConstants.ALERT_STATUS_OPEN);
		alertVO.setStatusDesc(ContactManagementConstants.ALERT_STATUS_OPEN_DESC);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @return String
	 */
	public String cancelAlert() {
		
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		correspondenceDataBean.setInputHiddenId("log_div_alerts");
		if (!correspondenceDataBean.isAlertSaved()) {
			resetAlert();
		}
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		alertDataBean.setRenderAddAlert(false);
		alertDataBean.setRenderEditAlert(false);
		alertDataBean.setShowSucessMessage(false);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @return String
	 */
	public String updateAlert() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		correspondenceDataBean.setCrSaved(false);
		int rowIndex = alertDataBean.getRowIndex();
        // Commented for Defect ESPRD00772108 - For Notify Alert
		//AlertDOConvertor.setDescForAlertValidValues(alertDataBean.getAlertVO());
		alertDataBean.getListOfAlertVOs().set(rowIndex,
				alertDataBean.getAlertVO());
		// Added for Defect ESPRD00772108 - For Notify Alert 
		AlertVO alertVO = alertDataBean.getAlertVO();
		if(logger.isInfoEnabled())
		{
			logger.info("alertDataBean.getAlertVO()" + alertDataBean.getAlertVO().getUserId());
		}
		// Added for DEFECT ESPRD00772108 - For Notify Alert - STARTED
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			WorkUnit workUnit = contactMaintenanceDelegate.getWorkUnit(Long
					.valueOf(alertVO.getUserWorkUnitSK()));
			if(logger.isDebugEnabled())
			{
				logger.debug("&&&&&&&&workUnit   :" + workUnit);
				logger.debug("&&&&&&&&userworkUnitSk   :" + Long.valueOf(alertVO.getUserWorkUnitSK()));
			}
			alertVO.setUserId(workUnit.getEnterpriseUser().getUserID());

		} catch (LOBHierarchyFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}
		AlertDOConvertor.setUserNameFromList(alertVO);
		AlertDOConvertor.setDescForAlertValidValues(alertDataBean.getAlertVO());
		// Added for DEFECT ESPRD00772108 - For Notify Alert - ENDED
		changeCRStatusForAutoAlert(alertDataBean.getAlertVO());

		//alertDataBean.setAlertVO(new AlertVO());
		alertDataBean.setRenderEditAlert(true);
		alertDataBean.setRenderAddAlert(false);
		alertDataBean.setShowSucessMessage(true);
		correspondenceDataBean.setAlertSaved(true);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @return String
	 */
	public String getAlertDetails() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		cancelIncompleteAction();
		// ADDED FOR THE DEFECT ESPRD00772108 - For Notify Alert
		addCrspdAlertRec();
		//getReferenceData();

		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
		if (indexCode == null) {
			indexCode = "0";
		}
		int rowIndex = Integer.parseInt(indexCode);
		alertDataBean.setStartIndexForAlert((rowIndex/10)*10);

		AlertVO alertVO = (AlertVO) alertDataBean.getListOfAlertVOs().get(
				rowIndex);

		AlertVO tempAlertVO = getCloneAlertVO(alertVO);

		UIComponent component = ContactManagementHelper
				.findComponentInRoot("alertAuditId");
		if (component != null) {
			AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
					false);

		}
		alertVO.setAuditKeyList(tempAlertVO.getAuditKeyList());

		alertDataBean.setAlertVO(tempAlertVO);

		Long userSK = getUserSK(getLoggedInUser());

		checkForAlertStatus();

		if (!tempAlertVO.getUserWorkUnitSK()
				.equalsIgnoreCase(userSK.toString())) {
			boolean statusProtected = false;
			Boolean isSupervisor = Boolean.FALSE;

			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

			if (tempAlertVO.getAlertType().equalsIgnoreCase("A")) {
				String deptSK = correspondenceDataBean
						.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getDepartment();
				if(logger.isDebugEnabled())
				{
					logger.debug("&&&&&&&&&&&&&& deptSK  :" + deptSK);
				}
				try {
					isSupervisor = contactMaintenanceDelegate
							.isUserSupervisorToDept(userSK, Long
									.valueOf(deptSK));
					if(logger.isDebugEnabled())
					{
						logger.debug("&&&&&&&&&&&&&&&&&&&&isSupervisor"	+ isSupervisor);
					}
				} catch (LOBHierarchyFetchBusinessException e) {
					if(logger.isErrorEnabled())
					{
						logger.error(e.getMessage(), e);
					}
				} catch (NumberFormatException e) {
					if(logger.isErrorEnabled())
					{
						logger.error(e.getMessage(), e);
					}
				}

				statusProtected = !(isSupervisor.booleanValue());
			}

			alertDataBean.setStatusProtected(statusProtected);
		}

		alertDataBean.setRenderEditAlert(true);
		alertDataBean.setShowSucessMessage(false);
		alertDataBean.setRowIndex(rowIndex);

		//showAuditLog();

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @return boolean
	 */
	private boolean validateAlert() {
		
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		boolean validAlertDueDate = true;
		boolean validAlertType = true;
		boolean validAlertDesc = true;
		boolean validNotifyAlertToUser = true;
		boolean validAlertStatus = true;
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		validAlertDueDate = verifyAlertDueDate(alertDataBean.getAlertVO()
				.getDueDate());

		validAlertType = !(isNullOrEmptyField(alertDataBean.getAlertVO()
				.getAlertType()));
		if (!validAlertType) {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ALERT_TYPE },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
					ContactManagementConstants.ALERT_TYPE_ID);
		}

		validAlertDesc = verifyDescription(alertDataBean.getAlertVO()
				.getDescription());

		validNotifyAlertToUser = !(isNullOrEmptyField(alertDataBean
				.getAlertVO().getUserWorkUnitSK()));
		if (!validNotifyAlertToUser) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.NOTIFY_VIA_ALERT },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
					ContactManagementConstants.NOTIFY_VIA_ALERT_ID);
		}

		validAlertStatus = !(isNullOrEmptyField(alertDataBean.getAlertVO()
				.getStatus()));
		correspondenceDataBean.setAlertValidated(validAlertDueDate
				&& validAlertType && validAlertDesc && validNotifyAlertToUser
				&& validAlertStatus);

		return (validAlertDueDate && validAlertType && validAlertDesc
				&& validNotifyAlertToUser && validAlertStatus);
	}

	/**
	 * *
	 * 
	 * @param dueDate
	 *            The dueDate to set.
	 * @return boolean
	 */
	private boolean verifyAlertDueDate(String dueDate) {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		boolean validAlertDueDate = true;

		if (dueDate == null
				|| ProgramConstants.EMPTY_STRING.equalsIgnoreCase(dueDate
						.trim())) {
			validAlertDueDate = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.DUE_DATE },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
					ContactManagementConstants.ALERT_DUE_DATE_ID);
		} else if (!EnterpriseCommonValidator.validateDate(dueDate)) {
			validAlertDueDate = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE,
					new Object[] { ContactManagementConstants.DUE_DATE },
					MessageUtil.ENTMESSAGES_BUNDLE,
					ContactManagementConstants.ALERT_DUE_DATE_ID);
		} else {
			DateFormat dateFormat = new SimpleDateFormat(
					ProgramConstants.DATE_FORMAT, Locale.getDefault());

			try {
				Calendar calendar = Calendar.getInstance();
				Date currentDate = dateFormat.parse(dateFormat.format(calendar
						.getTime()));
				Date alertDueDate = dateFormat.parse(dueDate);

				if (!(DateValidator.compareGreaterDate(alertDueDate,
						currentDate) || DateValidator.compareEqualDate(
						alertDueDate, currentDate))) {
					validAlertDueDate = false;

					setErrorMessage(
							ContactManagementConstants.ERR_CM_DUE_DATE_AFTER_SYS_DATE,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
							ContactManagementConstants.ALERT_DUE_DATE_ID);
				} else {
					alertDataBean.getAlertVO().setDtDueDate(alertDueDate);
				}
			} catch (ParseException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}
		}

		return validAlertDueDate;
	}

	/**
	 * This method is used to verify the description entered for the Category.
	 * 
	 * @param alertDesc :
	 *            Alert Description.
	 * @return boolean : true if valid description else false.
	 */
	private boolean verifyDescription(String alertDesc) {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		boolean validAlertDesc = true;

		if (isNullOrEmptyField(alertDesc)) {
			validAlertDesc = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.DESCRIPTION },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
					ContactManagementConstants.ALERT_DESC_ID);
		} else if (!EnterpriseCommonValidator
				.validateAlphaSpecialCharsForAlert(alertDesc.trim())) {
			validAlertDesc = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_INVALID,
					new Object[] { ContactManagementConstants.DESCRIPTION },
					MessageUtil.ENTMESSAGES_BUNDLE,
					ContactManagementConstants.ALERT_DESC_ID);
		}

		return validAlertDesc;
	}

	/**
	 * This operation is used to the get the Clone of AlertVO which is selected
	 * for edit operation.
	 * 
	 * @param alertVO :
	 *            AlertVO selected for edit operation.
	 * @return AlertVO : Cloned AlertVO object.
	 */
	private AlertVO getCloneAlertVO(AlertVO alertVO) {

		AlertVO tempAlertVO = null;

		try {
			tempAlertVO = (AlertVO) alertVO.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}

		tempAlertVO = (tempAlertVO == null) ? new AlertVO() : tempAlertVO;

		return tempAlertVO;
	}

	/**
	 * This operation is used to cancel incomplete user action.
	 */
	private void cancelIncompleteAction() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);

		if (alertDataBean.isRenderAddAlert()
				|| alertDataBean.isRenderEditAlert()) {
			this.cancelAlert();
		}

	}

	/**
	 * @return boolean
	 */
	private boolean requiredValuesAvbl() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		boolean reqdValsAvbl = true;

		if (isNullOrEmptyField(correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
				.getCategorySK())) {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.CATEGORY },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "alertErr");

			reqdValsAvbl = false;
		}

		return reqdValsAvbl;
	}

	/**
	 * This method is used to get the Alert Data Bean.
	 * 
	 * @return AlertDataBean : AlertDataBean object.
	 */
	/*
	 * public AlertDataBean getAlertDataBean() {
	 * logger.info("getAlertDataBean");
	 * 
	 * FacesContext fc = FacesContext.getCurrentInstance();
	 * 
	 * 
	 * return ((AlertDataBean) fc.getApplication().createValueBinding(
	 * ContactManagementConstants.BINDING_BEGIN_SEPARATOR +
	 * AlertDataBean.BEAN_NAME +
	 * ContactManagementConstants.BINDING_END_SEPARATOR) .getValue(fc)); }
	 */

	/**
	 * This method is to create an instance of Data Bean.
	 * 
	 * @return Returns EDMSDefaultsDataBean
	 */
	/*
	 * private CorrespondenceDataBean getCorrespondenceDataBean() {
	 * 
	 * FacesContext fc = FacesContext.getCurrentInstance();
	 * CorrespondenceDataBean correspondenceDataBean = (CorrespondenceDataBean)
	 * fc .getApplication().createValueBinding( "#{" +
	 * CorrespondenceDataBean.BEAN_NAME + "}") .getValue(fc);
	 * 
	 * 
	 * return correspondenceDataBean; }
	 */

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

		return component;
	}

	/**
	 * @param userSK
	 *            The userSK to set.
	 * @return String
	 */
	
	//Code commented below for new API on 07/03/2012.
	
	/*public String getUserName(Long userSK) {
		logger.info("getUserName");

		String userName = ContactManagementConstants.EMPTY_STRING;

		try {
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			logger.debug("&&&&&&&&&&&&&&&userSK for dept" + userSK);
			WorkUnit workUnit = contactMaintenanceDelegate.getWorkUnit(userSK);
			userName = getName(workUnit, "U");
		} catch (LOBHierarchyFetchBusinessException e) {
			logger.error(e.getMessage(), e);
		}

		return userName;
	}*/
	
	//Code Added below for new API on 07/03/2012.
	
	public String getUserName(Long userSK){
		
		String userName = ContactManagementConstants.EMPTY_STRING;
		List userData = null;
		int size = 0;
		try
		{
			DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
			deptUserBasicInfo.setDataReqested("workUnitSKForWorkUnit");
			deptUserBasicInfo.setWorkUnitSK(userSK);
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			userData = contactMaintenanceDelegate.getWorkUnitUserDetails(deptUserBasicInfo);
			DeptUserBasicInfo deptUserBasicInfoResult = null;
			if(! userData.isEmpty())
			{
				size = userData.size();
				
				for(int i=0; i < size; i++)
				{
					deptUserBasicInfoResult = (DeptUserBasicInfo)userData.get(i);
					
				}
				userName =	getNameNewAPI(deptUserBasicInfoResult,"U");
				
			}
	}
		catch (Exception e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}
		return userName;
	}
	//New method for new API added on 07/03/2012
	
	private String getNameNewAPI(DeptUserBasicInfo deptUserBasicInfo, String workUnitTypeCode) {
		

		String name = ContactManagementConstants.EMPTY_STRING;

		if (deptUserBasicInfo != null && "U".equalsIgnoreCase(workUnitTypeCode)) {
			
			name = deptUserBasicInfo.getFirstName()
					+ ContactManagementConstants.SPACE_STRING
					+ deptUserBasicInfo.getLastName();
		}
		if (deptUserBasicInfo != null && "W".equalsIgnoreCase(workUnitTypeCode)) {
			
			name = deptUserBasicInfo.getDeptName();
		}

		return name;
	}
	

	/**
	 * @param workUnit
	 *            The workUnit to set.
	 * @param workUnitTypeCode
	 *            The workUnitTypeCode to set.
	 * @return String
	 */
	private String getName(WorkUnit workUnit, String workUnitTypeCode) {
		
		String name = ContactManagementConstants.EMPTY_STRING;

		if (workUnit != null && "U".equalsIgnoreCase(workUnitTypeCode)) {
			EnterpriseUser user = workUnit.getEnterpriseUser();
			name = user.getFirstName()
					+ ContactManagementConstants.SPACE_STRING
					+ user.getLastName();
		}
		if (workUnit != null && "W".equalsIgnoreCase(workUnitTypeCode)) {
			Department department = workUnit.getDepartment();
			name = department.getName();
		}

		return name;
	}

	/**
	 *  
	 */
	public void clearAlertDataBeanState() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		alertDataBean.getListOfAlertVOs().clear();
		alertDataBean.getListOfUsersToNotify().clear();
		alertDataBean.getRefAlertStatusList().clear();
		alertDataBean.getRefAlertTypeList().clear();
		alertDataBean.setRenderAddAlert(false);
		alertDataBean.setRenderEditAlert(false);
		alertDataBean.setRenderNoData(true);
		alertDataBean.setRowEnabled(true);
		alertDataBean.setShowSucessMessage(false);
		alertDataBean.setStatusProtected(false);
		alertDataBean.setAlertVO(new AlertVO());

	}

	public String showAuditLog() {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		alertDataBean.setColumnValueRender(false);
		alertDataBean.setAlertAuditRender(true);
		alertDataBean.setAlertAuditOpen(true);

		EnterpriseSearchResultsVO resultVO = retrieveData(0, 0);
		setRecordRange(resultVO);
		alertDataBean.setAlertAuditHistoryList(resultVO.getSearchResults());

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * show the next page
	 * 
	 * @return
	 */
	public String next() {
		//	long entryTime = System.currentTimeMillis();
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages" + alertDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ alertDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ alertDataBean.getStartRecord());
			logger
					.debug("The end record......."
							+ alertDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(alertDataBean,
				alertDataBean.getItemsPerPage());

		int start = (entDataBean.getCurrentPage() - 1)
				* ProgramNumberConstants.INT_TEN;
		EnterpriseSearchResultsVO resultVO = retrieveData(start,
				ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

		alertDataBean.setAlertAuditHistoryList(resultVO.getSearchResults());

		/*
		 * long exitTime = System.currentTimeMillis(); if
		 * (logger.isInfoEnabled()) {
		 * logger.info("SearchSystemParameterControllerBean" + "#" + "next" +
		 * "#" + (exitTime - entryTime)); }
		 */

		return ProgramConstants.NEXT;
	}

	/**
	 * show the previous page
	 * 
	 * @return
	 */
	public String previous() {
		//	long entryTime = System.currentTimeMillis();
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		
		EnterpriseBaseDataBean entDataBean = previousPage(alertDataBean,
				alertDataBean.getItemsPerPage());

		int start = (entDataBean.getCurrentPage() - 1)
				* ProgramNumberConstants.INT_TEN;
		EnterpriseSearchResultsVO resultVO = retrieveData(start,
				ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

		alertDataBean.setAlertAuditHistoryList(resultVO.getSearchResults());

		/*
		 * long exitTime = System.currentTimeMillis(); if
		 * (logger.isInfoEnabled()) { logger.info("DiagnosisCodeControllerBean" +
		 * "#" + "previous" + "#" + "DIAGNOSIS CODE" + "#" + (exitTime -
		 * entryTime)); }
		 */

		return ProgramConstants.PREVIOUS;
	}

	/**
	 * show the column value details of the selected audit log
	 * 
	 * @return
	 */
	public String showColValHistory() {

		GlobalAuditsDelegate audit;
		try {
			
			audit = new GlobalAuditsDelegate();
			alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
			AuditLog auditLog = audit.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			alertDataBean.setSelectedAuditLog(auditLog);
			alertDataBean.setColumnValueRender(true);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			{
			logger.debug("Error in show Column Value  " + e);
			}
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String closeColValHistory() {
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		alertDataBean.setColumnValueRender(false);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * 
	 * @param alertVo
	 * @return
	 */
	private Alert convert(AlertVO alertVo) {
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		AlertDOConvertor alertDOConvertor = new AlertDOConvertor();
		//FindBug Issue Resolved
		//CorrespondenceDOConvertor correspondenceDOConvertor = new
		// CorrespondenceDOConvertor();
		Alert alert = alertDOConvertor.convertAlertVOToDO(alertDataBean
				.getAlertVO());

		return alert;
	}

	/**
	 * retrieve the param value
	 * 
	 * @param name
	 * @return
	 */
	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}

	/**
	 * retrieve audit logs from the database
	 * 
	 * @param start
	 * @param noOfRecord
	 * @return
	 */
	private EnterpriseSearchResultsVO retrieveData(int start, int noOfRecord) {
		//	long entryTime = System.currentTimeMillis();
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		GlobalAuditsDelegate audit;
		try {
			
			audit = new GlobalAuditsDelegate();
			Alert alert = convert(alertDataBean.getAlertVO());

			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(alert, start, noOfRecord);
			return enterpriseResultVO;
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled())
			{
				logger.debug("Error in show Parent audit history  " + e);
			}
		}

		/*
		 * long exitTime = System.currentTimeMillis(); if
		 * (logger.isInfoEnabled()) {
		 * logger.info("UpdateSystemParameterControllerBean" + "#" + "#" +
		 * (exitTime - entryTime)); }
		 */

		return null;
	}

	/**
	 * set the record range based on the retrieve data from the database that is
	 * stored in the EnterpriseSearchResultsVO
	 * 
	 * @param enterpriseSearchResultsVO
	 */
	public void setRecordRange(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		//	long entryTime = System.currentTimeMillis();
		int listSize;
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		if(logger.isDebugEnabled())
		{
			logger.debug("Inside setRecordRange ");
		}
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		if(logger.isDebugEnabled())
		{
			logger.debug("Total no of records-->" + totalRecordCount);
		}
		alertDataBean.setCount((int) totalRecordCount);

		int noOfPages = alertDataBean.getCount()
				/ alertDataBean.getItemsPerPage();

		int modNofPages = alertDataBean.getCount()
				% alertDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}
		if(logger.isDebugEnabled())
		{
			logger.debug("Number Of pages: " + noOfPages);
		}
		alertDataBean.setCurrentPage(ProgramConstants.INITIAL_CURRENT_PAGE);
		alertDataBean.setNumberOfPages(noOfPages);
		alertDataBean.setStartRecord(ProgramConstants.START_RECORD);
		alertDataBean.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = alertDataBean.getCount();
		if(logger.isDebugEnabled())
		{
			logger.debug("Total records count is : " + listSize);
		}
		if (listSize <= alertDataBean.getItemsPerPage()) {
			alertDataBean.setEndRecord(listSize);
		}
		//FindBug Issue Resolved
		/*
		 * if (listSize > alertDataBean.getItemsPerPage()) { listSize =
		 * alertDataBean.getItemsPerPage(); }
		 */

		if (alertDataBean.getCount() <= alertDataBean.getItemsPerPage()) {
			alertDataBean.setShowNext(false);
		} else {
			alertDataBean.setShowNext(true);
		}
		alertDataBean.setShowPrevious(false);
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages" + alertDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ alertDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ alertDataBean.getStartRecord());
			logger
					.debug("The end record......."
							+ alertDataBean.getEndRecord());
			logger.debug("The total count......." + alertDataBean.getCount());
		}
		/*
		 * long exitTime = System.currentTimeMillis();
		 * logger.info("DiagnosisCodeControllerBean" + "#" + "setRecordRange" +
		 * "#" + "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		 */
	}

	/**
	 * @param alertDataBean
	 *            The alertDataBean to set.
	 */
	public void setAlertDataBean(AlertDataBean alertDataBean) {

		this.alertDataBean = alertDataBean;
	}

	/**
	 * This Method to sort the records.
	 * 
	 * @param event
	 *            The MyCrsList to sort.
	 * @return String.
	 */
	public String sort(ActionEvent event) {
		
		alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
		String sortColumn = (String) event.getComponent().getAttributes().get(
				"columnName");
		String sortOrder = (String) event.getComponent().getAttributes().get(
				"sortOrder");
		alertDataBean.setImageRender(event.getComponent().getId());
		if(logger.isDebugEnabled())
		{
			logger.debug("The Sort column of MY CR is--->" + sortColumn);
			logger.debug("the sort order of MY CR is--->" + sortOrder);
		}
		alertsComparator(sortColumn, sortOrder, alertDataBean
				.getListOfAlertVOs());

		return ContactManagementConstants.RENDER_SUCCESS;
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
	private void alertsComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				String first = null;
				String second = null;
				AlertVO data1 = (AlertVO) obj1;
				AlertVO data2 = (AlertVO) obj2;
				boolean ascending = false;
				if ("Ascending".equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}

				if ("Status".equals(sortColumn)) {
					
					if (null == data1.getStatusDesc()) {
						data1
								.setStatusDesc(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getStatusDesc()) {
						data2
								.setStatusDesc(ContactManagementConstants.EMPTY_STRING);
					}

					first = data1.getStatusDesc().toLowerCase();
					second = data2.getStatusDesc().toLowerCase();
					if(logger.isDebugEnabled())
					{
						logger.debug("First in desc" + first);
						logger.debug("second in desc" + second);
					}
					return ascending ? first.compareTo(second) : second
							.compareTo(first);
				}

				if ("Due Date".equals(sortColumn)) {

					if (null == data1.getDtDueDate()) {
						data1.setDtDueDate(new Date(12 / 31 / 9999));
						try {
							data1.setDtDueDate(data1.getDtDueDate());
						} catch (Exception e) {
							if(logger.isDebugEnabled())
							{
								logger.debug("exception is" + e);
							}
						}
					}
					if (null == data2.getDtDueDate()) {
						data2.setDtDueDate(new Date(12 / 31 / 9999));
						try {
							data2.setDtDueDate(data2.getDtDueDate());
						} catch (Exception e) {
							if(logger.isDebugEnabled())
							{
							logger.debug("exception is" + e);
							}
						}
					}

					return ascending ? data1.getDtDueDate().compareTo(
							data2.getDtDueDate()) : data2.getDtDueDate()
							.compareTo(data1.getDtDueDate());

				}

				if ("Alert Type".equals(sortColumn)) {
					if (null == data1.getAlertTypeDesc()) {
						data1
								.setAlertTypeDesc(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getAlertTypeDesc()) {
						data2
								.setAlertTypeDesc(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? data1.getAlertTypeDesc().compareTo(
							data2.getAlertTypeDesc()) : data2
							.getAlertTypeDesc().compareTo(
									data1.getAlertTypeDesc());
				}
				
				//compareToIgnoreCase is used below in place of compareTo for fixing the defect ESPRD00784654
				
				if ("Description".equals(sortColumn)) {
					if (null == data1.getDescription()) {
						data1
								.setDescription(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getDescription()) {
						data2
								.setDescription(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? data1.getDescription().compareToIgnoreCase(
							data2.getDescription()) : data2.getDescription()
							.compareToIgnoreCase(data1.getDescription());
				}
				if ("Notify via Alert".equals(sortColumn)) {
					if (null == data1.getUserWorkUnitDesc()) {
						data1
								.setUserWorkUnitDesc(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getUserWorkUnitDesc()) {
						data2
								.setUserWorkUnitDesc(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? data1.getUserWorkUnitDesc().compareToIgnoreCase(
							data2.getUserWorkUnitDesc()) : data2
							.getUserWorkUnitDesc().compareToIgnoreCase(
									data1.getUserWorkUnitDesc());
				}

				return 0;
			}
		};
		Collections.sort(dataList, comparator);

	}

}