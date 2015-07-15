/*
 * Created on Mar 15, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */
 
package com.acs.enterprise.common.program.commonentities.view.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
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
//import com.acs.enterprise.common.program.benefitadministration.util.helper.BenefitPlanConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.Representative;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonContactTypeVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonContactVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;

/**
 * This controllerbean is used to manage CommonContact Information.
 */
public class CommonContactControllerBean extends EnterpriseBaseControllerBean {

	/**
	 * To hold commonEntityDataBean.
	 */
	private CommonEntityDataBean commonEntityDataBean = ContactHelper.getCommonEntityDataBean();

	/**
	 * To hold EnterpriseLogger.
	 */

	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(getClass().getName());

	/**
     * This field is used to store whether user has permission.
     */
    private boolean controlRequired = true; // Newly added
    
	
	/**
	 * @return Returns the controlRequired.
	 */
	public boolean isControlRequired() {
		return controlRequired;
	}
	/**
	 * @param controlRequired The controlRequired to set.
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}

	/**
     * Newly Added
     * This method gets the permission level for the logged in user
     *
     */	
	public void getUserPermission() {
		String userPermission = "";
		String userid = getUserID();		
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(ContactManagementConstants.MAINTAIN_ENTITY_PAGE, userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode",userPermission);
		logger.debug("userPermission in contact -->"+userPermission);
		// check the permission level for the user and set it in a boolean variable.  This will be used for Buttons/Links
		if(userPermission.equals("r")) {
			controlRequired = false;		
		}
	}
    
    /**
     * Newly Added
     * This method get the User ID
     * 
     * @return String
     */
    private String getUserID() {
    	String userId = ContactManagementConstants.TEST_USERID;
		try {

			HttpServletRequest renderrequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(renderrequest, renderresponse);

			if (enterpriseUserProfile != null) {
				userId = enterpriseUserProfile.getUserId();
			}
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("LOGGED_IN_USERID", userId);			
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			logger.debug("Exception has come:");
		}

        
	return userId;
	}


	/**
	 * To operation contains the logic to display UI components to enter new
	 * Contact Details.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String addContact() {
		logger.debug("commonContactControllerbean..addContact");
		if (commonEntityDataBean.getEntityTypeList() == null
				|| commonEntityDataBean.getEntityTypeList().size() == 0) {
			getReferenceData();
			logger.debug("commonContactControllerbean..save contact....13");
		}
		//getReferenceData();
		//logger.debug("commonContactControllerbean..after calling getReferenceData");

		commonEntityDataBean.getCommonEntityVO().setCommonContactVO(
				new CommonContactVO());
		commonEntityDataBean.setNewContactRender(true);
		commonEntityDataBean.setContactSaveMsg(false);
		
		logger.debug("commonContactControllerbean..save contact....14");
		/**
		 * Below code is commented as Add contact type section has been removed
		 * for ES2
		 */
		// commonEntityDataBean.setNewContactTypeRender(false);
		commonEntityDataBean.setContactEdit(false);
		//commonEntityDataBean.setContactSaveMsg(false);
		
		/*Big save and Little Save*/
		commonEntityDataBean.setCommonContactFlag(false);
		/*End Big save and Little Save*/
		
		commonEntityDataBean.setContactFlag(true);
		commonEntityDataBean.setCurrentPageId("contactfoucsid");
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Below code is commented as Add contact type section has been removed for
	 * ES2
	 */
	/**
	 * To operation contains the logic to display UI components to enter new
	 * Contact Type Details. This method is Called when user clicks on the Add
	 * Contact Type button.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	/*
	 * public void addContactType() { if
	 * (commonEntityDataBean.getEntityTypeList() == null ||
	 * commonEntityDataBean.getEntityTypeList().size() == 0) {
	 * getReferenceData(); } CommonContactTypeVO commonContactTypeVO = new
	 * CommonContactTypeVO();
	 * commonContactTypeVO.setVoidIndicator(BenefitPlanConstants.NO);
	 * //commonContactTypeVO.setEditFlg(false);
	 * commonEntityDataBean.getCommonEntityVO().getCommonContactVO()
	 * .setCommonContactTypeVO(commonContactTypeVO);
	 * commonEntityDataBean.setNewContactTypeRender(true);
	 * commonEntityDataBean.setContactTypeEdit(false);
	 * commonEntityDataBean.setSaveContactTypeChk(true);
	 * commonEntityDataBean.setDisableContactType(false);
	 * commonEntityDataBean.setAddcontactVoidIndicatorRender(true); }
	 */

	/**
	 * This operation is to save the CommonContactVO to request scope, this
	 * method invkoes the validateContact method of commonEntityValidator. If
	 * the CommonContactVO is valid then validateContact() sets the
	 * CommonContactVO to commonEntityDatatbean. And commonEntityDataBean will
	 * be save to request scope using tstate tag.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String saveContact() {

		logger.info("in saveContact");
		boolean valid = false;
		CommonContactVO commonContactVO = commonEntityDataBean
				.getCommonEntityVO().getCommonContactVO();
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
		valid = commonEntityValidator.validateContact(commonContactVO);
		/* Big save and Little Save */
		commonEntityDataBean.setCommonContactFlag(valid);
		/* End Big save and Little Save */

		if (valid) {
				
			//This method will perform contact save.
			if (!commonEntityDataBean.isContactEdit()) {

				logger.debug("it is not edit contact");
				if (commonEntityDataBean.getCommonEntityVO().getContactVOList() == null) {
					logger
							.debug("contact vo list is empty....create new arraylist");
					commonEntityDataBean.getCommonEntityVO().setContactVOList(
							new ArrayList());
				}

				/*
				 * Following setter method is to set the representative's
				 * CommonEntity Currently this information is not available, so
				 * setting a new Object as g_rep_tb.G_REP_CMN_ENTY_SK is not
				 * null.
				 */
				CommonEntityVO repCommonEntityVO = new CommonEntityVO();
				//repCommonEntityVO.setCommonEntityType("M");
				// repCommonEntityVO.setVoidIndicator(new Boolean(false));
				commonContactVO.setRepCommonEntityVO(repCommonEntityVO);
				/* End of setting representative's commonentity */

				commonContactVO.setEntityTypeDesc(getDescriptionFromVV(
						commonContactVO.getEntityType(), commonEntityDataBean
								.getEntityTypeList()));
				commonEntityDataBean.getCommonEntityVO().getContactVOList()
						.add(0, commonContactVO);
				commonContactVO.setContactTypeDesc(getDescriptionFromVV(commonContactVO
						  .getContactType(), commonEntityDataBean.getContactTypeList()));
				commonContactVO.getNameVO().setSuffixNameDesc(getDescriptionFromVV(commonContactVO
						  .getNameVO().getSuffixName(), commonEntityDataBean.getNameSuffixList()));

                              
				commonContactVO.setStatusDesc(getDescriptionFromVV(commonContactVO
						  .getStatus(), commonEntityDataBean.getStatusList()));
				commonEntityDataBean.setSaveContactChk(false);
				commonEntityDataBean.setContactEdit(false);
				commonEntityDataBean.setContactSaveMsg(true);
				commonEntityDataBean.setSmallSaveFlag("true");

			} else {
				//This method will perform contact edit
				
				//Below code is commented to fix the defect - ESPRD00879942

				/*CommonContactVO tempContactVO = (CommonContactVO) commonEntityDataBean
						.getCommonEntityVO().getContactVOList().get(
								commonEntityDataBean.getSelectedConatctIndex());*/
				CommonContactVO tempContactVO = commonEntityDataBean.getTempCommonContactVO();

				logger.info("ContactSK--->" + tempContactVO.getContactSK());
				/*commonContactVO.setContactSK(tempContactVO.getContactSK());
				commonEntityDataBean.getCommonEntityVO().getContactVOList()
						.remove(commonEntityDataBean.getSelectedConatctIndex());*/
				
				// Below code is added for the defect - ESPRD00879942 to End dated the old record and adding new record in edit mode.
				
				commonContactVO.setContactSK(null);
				commonContactVO.setBeginDate(ContactHelper.getDateInMMDDYYYYFormat(new Date()));
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				Date newDate = cal.getTime();
				tempContactVO.setEndDate(ContactHelper.dateConverter(newDate));
				logger.info("EndDate Value" + tempContactVO.getEndDate());
				
			
				if (null !=tempContactVO && tempContactVO.getEndDate() != null
						&& tempContactVO.getEndDate().trim().length() > 0) {

					if (ContactHelper.dateConverter(
							tempContactVO.getEndDate()).compareTo(
							ContactHelper.dateConverter(tempContactVO
									.getBeginDate())) < 0) {

						commonEntityValidator
								.setErrorMessage(
										ProgramConstants.END_DATE_LESSTHAN_BEGINDATE,
										new Object[] {},
										ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
										"endDateCnt"); 
						commonEntityDataBean.setCommonContactFlag(false);
						return ContactManagementConstants.RENDER_SUCCESS;
					}

				}

				/*
				 * Following setter method is to set the representative's
				 * CommonEntity Currently this information is not available, so
				 * setting a new Object as g_rep_tb.G_REP_CMN_ENTY_SK is not
				 * null.
				 */
				CommonEntityVO repCommonEntityVO = new CommonEntityVO();
				//repCommonEntityVO.setCommonEntityType("M");
				//  repCommonEntityVO.setVoidIndicator(false);
				commonContactVO.setRepCommonEntityVO(repCommonEntityVO);
				/* End of setting representative's commonentity */

				commonContactVO.setEntityTypeDesc(getDescriptionFromVV(
						commonContactVO.getEntityType(), commonEntityDataBean
								.getEntityTypeList()));

			/*	commonEntityDataBean.getCommonEntityVO().getContactVOList()
						.add(commonEntityDataBean.getSelectedConatctIndex(),
								commonContactVO);*/
				commonContactVO.setContactTypeDesc(getDescriptionFromVV(commonContactVO
						  .getContactType(), commonEntityDataBean.getContactTypeList()));
				commonContactVO.getNameVO().setSuffixNameDesc(getDescriptionFromVV(commonContactVO
						  .getNameVO().getSuffixName(), commonEntityDataBean.getNameSuffixList()));

                              
				commonContactVO.setStatusDesc(getDescriptionFromVV(commonContactVO
						  .getStatus(), commonEntityDataBean.getStatusList()));
				
				commonEntityDataBean.getCommonEntityVO().getContactVOList()
				.set(commonEntityDataBean.getSelectedConatctIndex(), tempContactVO);
				commonEntityDataBean.getCommonEntityVO().getContactVOList().add(commonContactVO);
				
				commonEntityDataBean.setContactEdit(false);
				commonEntityDataBean.setContactSaveMsg(true);
				commonEntityDataBean.setSmallSaveFlag("true");
				//contactDataTable.setFirst(0);
				commonEntityDataBean.setSaveContactChk(false);
				commonEntityDataBean.setShowSuccesMessage(false);

			}
			/*Big Save & Little Save*/
			//addContact();
			commonEntityDataBean.setNewContactRender(false);
			/*Big Save & Little Save*/
//			To fix defect ESPRD00576234
			commonEntityDataBean.setCurrentPageId("contactfoucsid");
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	

	

	/**
	 * Below code is commented as Add contact type section has been removed for
	 * ES2
	 */

	/**
	 * This operation is to save the CommonContactTypeVO to request scope, this
	 * method invkoes the validateContactType() method of commonEntityValidator.
	 * If the CommonContactVO is valid then validateContactType() sets the
	 * CommonContactTypeVO to commonEntityDatatbean. And commonEntityDataBean
	 * will be save to request scope using tstate tag.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	/*
	 * public void saveContactType() { boolean valid = false;
	 * CommonContactTypeVO contactTypeVO = commonEntityDataBean
	 * .getCommonEntityVO().getCommonContactVO() .getCommonContactTypeVO();
	 * String contactType = contactTypeVO.getContactType(); String significance =
	 * contactTypeVO.getSignificance(); String voidInd =
	 * contactTypeVO.getVoidIndicator();
	 * contactTypeVO.setContactTypeDesc(getDescriptionFromVV(contactTypeVO
	 * .getContactType(), commonEntityDataBean.getContactTypeList()));
	 * contactTypeVO.setSignificanceDesc(getDescriptionFromVV(contactTypeVO
	 * .getSignificance(), commonEntityDataBean.getContactSigList())); if
	 * (voidInd == null) { voidInd = "1";
	 * contactTypeVO.setVoidIndicator(BenefitPlanConstants.NO); } ArrayList
	 * contactVOList = commonEntityDataBean.getCommonEntityVO()
	 * .getCommonContactVO().getCommonContactTypeVOList(); CommonEntityValidator
	 * commonEntityValidator = new CommonEntityValidator(); if
	 * (commonEntityDataBean.isContactTypeEdit()) { while editing the record if
	 * ("0".equals(voidInd) || commonEntityDataBean.isDisableContactType()) {
	 * ArrayList contactTypeVOList = commonEntityDataBean
	 * .getCommonEntityVO().getCommonContactVO() .getCommonContactTypeVOList();
	 * if (contactTypeVOList != null && contactTypeVOList.size() > 0) {
	 * CommonContactTypeVO prevCommonConatctTypeVO = (CommonContactTypeVO)
	 * commonEntityDataBean .getCommonEntityVO().getCommonContactVO()
	 * .getCommonContactTypeVOList().get( commonEntityDataBean
	 * .getSelectedConatctTypIndex()); logger.debug("contactTypeVO.void" +
	 * contactTypeVO.getVoidIndicator());
	 * contactTypeVO.setContactType(prevCommonConatctTypeVO .getContactType());
	 * contactTypeVO.setSignificance(prevCommonConatctTypeVO
	 * .getSignificance());
	 * contactTypeVO.setContactTypeDesc(getDescriptionFromVV(
	 * contactTypeVO.getContactType(),
	 * commonEntityDataBean.getContactTypeList()));
	 * contactTypeVO.setSignificanceDesc(getDescriptionFromVV(
	 * contactTypeVO.getSignificance(),
	 * commonEntityDataBean.getContactSigList())); if ("0".equals(voidInd)) {
	 * end date would system date SimpleDateFormat dateFormat = new
	 * SimpleDateFormat( "MM/dd/yyyy"); try { valid = commonEntityValidator
	 * .validateContactType(contactTypeVO); if (!valid) { commonEntityDataBean
	 * .setSaveContactTypeChk(true); return; } if (new Date().before(dateFormat
	 * .parse(contactTypeVO.getBeginDate()))) { contactTypeVO
	 * .setEndDate(prevCommonConatctTypeVO .getEndDate()); } else {
	 * contactTypeVO.setEndDate(ContactHelper .dateConverter(new Date())); } }
	 * catch (Exception e) { logger.debug("Date parse error"); } } else {
	 * contactTypeVO.setEndDate(prevCommonConatctTypeVO .getEndDate()); }
	 * contactTypeVO .setStrEndDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(contactTypeVO .getEndDate()));
	 * contactTypeVO.setBeginDate(prevCommonConatctTypeVO .getBeginDate());
	 * contactTypeVO.setStrBeginDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(prevCommonConatctTypeVO .getBeginDate()));
	 * commonEntityDataBean.getCommonEntityVO()
	 * .getCommonContactVO().getCommonContactTypeVOList() .remove(
	 * commonEntityDataBean .getSelectedConatctTypIndex());
	 * contactTypeVO.setStrBeginDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(contactTypeVO .getBeginDate())); contactTypeVO
	 * .setStrEndDate(ContactHelper .getDateInMMDDYYYYFormat(contactTypeVO
	 * .getEndDate())); commonEntityDataBean.getCommonEntityVO()
	 * .getCommonContactVO().getCommonContactTypeVOList() .add(
	 * commonEntityDataBean .getSelectedConatctTypIndex(), contactTypeVO);
	 * commonEntityDataBean.setContactTypeSaveMsg(true); addContactType();
	 * commonEntityDataBean.setSaveContactTypeChk(false); } } //end of void IF
	 * else { valid = commonEntityValidator .validateContactType(contactTypeVO);
	 * if (valid) { CommonContactTypeVO selectedContactTypeVO =
	 * (CommonContactTypeVO) commonEntityDataBean
	 * .getCommonEntityVO().getCommonContactVO()
	 * .getCommonContactTypeVOList().get( commonEntityDataBean
	 * .getSelectedConatctTypIndex()); boolean checkContactType =
	 * duplicateContactTypeCheck( contactVOList, contactType,
	 * commonEntityDataBean .getSelectedConatctTypIndex()); if
	 * (!checkContactType) { if (!selectedContactTypeVO.getContactType().equals(
	 * contactType)) {
	 *//**
	    * To check if new Begin date is less then or equal to previous Begin
	    * date added on Aug 25th 2007
	    */
	/*
	 * if (ContactHelper .dateConverter(contactTypeVO.getBeginDate())
	 * .compareTo( ContactHelper .dateConverter(selectedContactTypeVO
	 * .getBeginDate())) <= 0) {
	 * setErrorMessage("error.Contact_Type_New_Begin_Dt_Invalid"); } else { long
	 * timeInMillis = ContactHelper .dateConverter(
	 * contactTypeVO.getBeginDate()) .getTime(); Calendar cal =
	 * Calendar.getInstance(); cal.setTimeInMillis(timeInMillis); //
	 * cal.add(Calendar.DATE, -1); // subtract a day Date myNewObject =
	 * cal.getTime(); // if previous end date is > new Begin date then //
	 * previousend= new begindate-1 if (ContactHelper .dateConverter(
	 * selectedContactTypeVO .getEndDate()) .compareTo( ContactHelper
	 * .dateConverter(contactTypeVO .getBeginDate())) > 0) {
	 * selectedContactTypeVO .setEndDate(ContactHelper
	 * .dateConverter(myNewObject)); } commonEntityDataBean .getCommonEntityVO()
	 * .getCommonContactVO() .getCommonContactTypeVOList() .remove(
	 * commonEntityDataBean .getSelectedConatctTypIndex());
	 * selectedContactTypeVO .setStrBeginDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(selectedContactTypeVO .getBeginDate()));
	 * selectedContactTypeVO .setStrEndDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(selectedContactTypeVO .getEndDate()));
	 * selectedContactTypeVO .setContactTypeDesc(getDescriptionFromVV(
	 * selectedContactTypeVO .getContactType(), commonEntityDataBean
	 * .getContactTypeList())); selectedContactTypeVO
	 * .setSignificanceDesc(getDescriptionFromVV( selectedContactTypeVO
	 * .getSignificance(), commonEntityDataBean .getContactSigList()));
	 * commonEntityDataBean .getCommonEntityVO() .getCommonContactVO()
	 * .getCommonContactTypeVOList() .add( commonEntityDataBean
	 * .getSelectedConatctTypIndex(), selectedContactTypeVO);
	 * contactTypeVO.setStrBeginDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(contactTypeVO .getBeginDate()));
	 * contactTypeVO.setStrEndDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(contactTypeVO .getEndDate()));
	 * commonEntityDataBean.getCommonEntityVO() .getCommonContactVO()
	 * .getCommonContactTypeVOList().add(0, contactTypeVO); commonEntityDataBean
	 * .setSelectedConatctTypIndex(0); commonEntityDataBean
	 * .setContactTypeSaveMsg(true); addContactType(); commonEntityDataBean
	 * .setSaveContactTypeChk(false); } } else { commonEntityDataBean
	 * .getCommonEntityVO() .getCommonContactVO() .getCommonContactTypeVOList()
	 * .remove( commonEntityDataBean .getSelectedConatctTypIndex());
	 * contactTypeVO.setStrBeginDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(contactTypeVO .getBeginDate()));
	 * contactTypeVO.setStrEndDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(contactTypeVO .getEndDate()));
	 * commonEntityDataBean .getCommonEntityVO() .getCommonContactVO()
	 * .getCommonContactTypeVOList() .add( commonEntityDataBean
	 * .getSelectedConatctTypIndex(), contactTypeVO);
	 * commonEntityDataBean.setContactTypeSaveMsg(true); addContactType();
	 * commonEntityDataBean.setSaveContactTypeChk(false); } } else {
	 * logger.info("CONTACT TYPE ALREADY ADDED");
	 * setErrorMessage("error.Contact_Type_duplicate_found"); } } //end of
	 * errFlg IF else { commonEntityDataBean.setSaveContactTypeChk(true); } }
	 * //end of void check else } else { // this block would execute while
	 * adding new record valid =
	 * commonEntityValidator.validateContactType(contactTypeVO); if (valid) {
	 * boolean checkContactType = duplicateContactTypeCheck( contactVOList,
	 * contactType, -1); if (!checkContactType) { ArrayList contactTypeList =
	 * commonEntityDataBean .getCommonEntityVO().getCommonContactVO()
	 * .getCommonContactTypeVOList(); if (contactTypeList == null ||
	 * contactTypeList.size() == 0) { contactTypeList = new ArrayList(); }
	 * contactTypeVO.setStrBeginDate(ContactHelper
	 * .getDateInMMDDYYYYFormat(contactTypeVO .getBeginDate())); contactTypeVO
	 * .setStrEndDate(ContactHelper .getDateInMMDDYYYYFormat(contactTypeVO
	 * .getEndDate())); contactTypeList.add(0, contactTypeVO);
	 * commonEntityDataBean.getCommonEntityVO()
	 * .getCommonContactVO().setCommonContactTypeVOList( contactTypeList);
	 * commonEntityDataBean.setContactTypeEdit(true);
	 * commonEntityDataBean.setContactTypeSaveMsg(true); addContactType();
	 * commonEntityDataBean.setSaveContactTypeChk(false); } else {
	 * logger.info("CONTACT TYPE ALREADY ADDED");
	 * setErrorMessage("error.Contact_Type_duplicate_found"); } } // end of
	 * errFlg IF else { commonEntityDataBean.setSaveContactTypeChk(true); } } //
	 * end of else //commonEntityDataBean.setSaveContactTypeChk(false); }
	 */

	/**
	 * This method has the logic to clear all the UI components of Contact Type,
	 * if the Contact type Data is newly added value, else it will restore the
	 * old saved value of this particular Contact type.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	/*
	 * public void resetContactType() { CommonContactTypeVO contactTypeVO = new
	 * CommonContactTypeVO(); //boolean flg //
	 * =commonEntityDataBean.getCommonEntityVO().getCommonContactVO().getCommonContactTypeVO().isEditFlg();
	 * boolean flg = commonEntityDataBean.isContactTypeEdit(); if (flg &&
	 * (commonEntityDataBean.getCommonEntityVO()
	 * .getCommonContactVO().getCommonContactTypeVOList() != null &&
	 * (commonEntityDataBean .getCommonEntityVO().getCommonContactVO()
	 * .getCommonContactTypeVOList().size() > 0))) { contactTypeVO =
	 * (CommonContactTypeVO) commonEntityDataBean
	 * .getCommonEntityVO().getCommonContactVO()
	 * .getCommonContactTypeVOList().get(
	 * commonEntityDataBean.getSelectedConatctTypIndex()); }
	 * commonEntityDataBean.getCommonEntityVO().getCommonContactVO()
	 * .setCommonContactTypeVO(contactTypeVO);
	 * //commonEntityDataBean.setNewContactTypeRender(false); }
	 */

	/**
	 * This method has the logic to clear all the UI components of Contact, if
	 * the Contact Data is newly added value, else it will restore the old saved
	 * value of this particular Contact.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String resetContact() {

        logger.debug("CommonContactControllerBean::resetContact()");		
		CommonContactVO commonContactVO = new CommonContactVO();
		//FindBug Issue Resolved
		//ArrayList contactVOlist=new ArrayList();
		ArrayList contactVOlist=null;
		/*
		 * commonContactTypeVO() removed in ES2
		 */
		//contactVO.setCommonContactTypeVO(new CommonContactTypeVO());
		boolean flg = commonEntityDataBean.isContactEdit();
		int index;
		index=commonEntityDataBean.getSelectedConatctIndex();

		if (flg
				&& (commonEntityDataBean.getCommonEntityVO().getContactVOList() != null && commonEntityDataBean
						.getCommonEntityVO().getContactVOList().size() > 0)) {
			
			contactVOlist=(ArrayList)commonEntityDataBean.getCommonEntityVO().getContactVOList();
             index=commonEntityDataBean.getSelectedConatctIndex();
			/*contactVO = (CommonContactVO) commonEntityDataBean
					.getCommonEntityVO().getContactVOList().get(
							commonEntityDataBean.getSelectedConatctIndex());*/
			CommonContactVO contactVO=commonEntityDataBean.getTempCommonContactVO();
			CommonContactVO tempContactVO = new CommonContactVO();
			if(contactVO!=null){
				tempContactVO.setAddedAuditTimeStamp(contactVO.getAddedAuditTimeStamp());
				tempContactVO.setAddedAuditUserID(contactVO.getAddedAuditUserID());
				tempContactVO.setAuditTimeStamp(contactVO.getAuditTimeStamp());
				tempContactVO.setAuditUserID(contactVO.getAuditUserID());
				tempContactVO.setBeginDate(contactVO.getBeginDate());
				tempContactVO.setCommonContactTypeVO(contactVO.getCommonContactTypeVO());				
				tempContactVO.setCommonContactTypeVOList(contactVO.getCommonContactTypeVOList());
				tempContactVO.setCommonEntityRepCrossReferenceVersionNo(contactVO.getCommonEntityRepCrossReferenceVersionNo());
				tempContactVO.setCommonEntityTypeCode(contactVO.getCommonEntityTypeCode());
				tempContactVO.setContactSK(contactVO.getContactSK());
				tempContactVO.setContactType(contactVO.getContactType());
				tempContactVO.setContactTypeDesc(contactVO.getContactTypeDesc());
				tempContactVO.setDateOfBirth(contactVO.getDateOfBirth());
				tempContactVO.setDateOfDeath(contactVO.getDateOfDeath());
				tempContactVO.setDbRecord(contactVO.isDbRecord());
				tempContactVO.setEndDate(contactVO.getEndDate());
				tempContactVO.setEntityType(contactVO.getEntityType());
				tempContactVO.setEntityTypeDesc(contactVO.getEntityTypeDesc());
				tempContactVO.setExistingContactType(contactVO.getExistingContactType());
				tempContactVO.setGender(contactVO.getGender());
				//tempContactVO.setNameVO(contactVO.getNameVO());
				
				tempContactVO.getNameVO().setFirstName(contactVO.getNameVO().getFirstName());
				tempContactVO.getNameVO().setLastName(contactVO.getNameVO().getLastName());
				tempContactVO.getNameVO().setAddedAuditTimeStamp(contactVO.getNameVO().getAddedAuditTimeStamp());
				tempContactVO.getNameVO().setAddedAuditUserID(contactVO.getNameVO().getAddedAuditUserID());
				tempContactVO.getNameVO().setAuditTimeStamp(contactVO.getNameVO().getAuditTimeStamp());
				tempContactVO.getNameVO().setAuditUserID(contactVO.getNameVO().getAuditUserID());
				tempContactVO.getNameVO().setBusinessName(contactVO.getNameVO().getBusinessName());
				tempContactVO.getNameVO().setDbRecord(contactVO.getNameVO().isDbRecord());
				tempContactVO.getNameVO().setMiddleName(contactVO.getNameVO().getMiddleName());
				tempContactVO.getNameVO().setPhoneticFirstName(contactVO.getNameVO().getPhoneticFirstName());
				tempContactVO.getNameVO().setPhoneticLastName(contactVO.getNameVO().getPhoneticLastName());
				tempContactVO.getNameVO().setSortName(contactVO.getNameVO().getSortName());
				tempContactVO.getNameVO().setSuffixName(contactVO.getNameVO().getSuffixName());
				tempContactVO.getNameVO().setSuffixNameDesc(contactVO.getNameVO().getSuffixNameDesc());
				tempContactVO.getNameVO().setTitleName(contactVO.getNameVO().getTitleName());
				tempContactVO.getNameVO().setVersionNo(contactVO.getNameVO().getVersionNo());
				
				tempContactVO.setPrefix(contactVO.getPrefix());
				tempContactVO.setRepCommonEntityVO(contactVO.getRepCommonEntityVO());
				tempContactVO.setSignificance(contactVO.getSignificance());
				tempContactVO.setSSN(contactVO.getSSN());
				tempContactVO.setStatus(contactVO.getStatus());
				tempContactVO.setTitle(contactVO.getTitle());
				tempContactVO.setVersionNo(contactVO.getVersionNo());
				tempContactVO.setStatusDesc(contactVO.getStatusDesc());
				
				
			}
				contactVOlist.set(index,tempContactVO);
				commonEntityDataBean.getCommonEntityVO().setCommonContactVO(tempContactVO);				
				
						
			
		}else{
		commonEntityDataBean.getCommonEntityVO().setCommonContactVO(commonContactVO);
		}
//		To fix defect ESPRD00576234
		commonEntityDataBean.setCurrentPageId("contactfoucsid");
		return ContactManagementConstants.RENDER_SUCCESS;


	}

	/**
	 * This operation has the logic to cancel/hide the contact Details.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String cancelContact() {
		logger.debug("CommonContactControllerBean::cancelContact()");
        resetContact();  
		CommonContactVO commonContactVO = new CommonContactVO();
		commonEntityDataBean.getCommonEntityVO().setCommonContactVO(commonContactVO);
		commonEntityDataBean.setSaveContactChk(false);
		commonEntityDataBean.setNewContactRender(false);
		commonEntityDataBean.setContactEdit(false);
		//To fix defect ESPRD00576234
		commonEntityDataBean.setCurrentPageId("contactfoucsid");
		return ContactManagementConstants.RENDER_SUCCESS;
		/**
		 * Below code commented as part of ES2
		 */
		// commonEntityDataBean.setSaveContactTypeChk(false);
	}

	/**
	 * This operation has the logic to cancel/hide the contact Type Details.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	/*
	 * public void cancelContactType() {
	 * commonEntityDataBean.setNewContactTypeRender(false);
	 * commonEntityDataBean.setSaveContactTypeChk(false); }
	 */
	/**
	 * This method has the logic to display the UI components for detailed
	 * display of Contact information. Based on the selected index of contact
	 * Grid, this method would fetch the ContactVO form the contactVO List of
	 * commonEntityVO and it would get populated in the UI fields.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String displayDetailedContactInfo() {
		logger.debug("edit Contact  ");
		if (commonEntityDataBean.getEntityTypeList() == null
				|| commonEntityDataBean.getEntityTypeList().size() == 0) {
			getReferenceData();
		}
		//getReferenceData();
		//logger.debug("edit Contact - after calling getReferenceData");
		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = "0";
		int x = 0;
		//logger.debug("detailed contact info...1");
		if (map != null && map.size() > x) {
			//logger.debug("detailed contact info...2");
			indexCode = (String) map.get(ContactManagementConstants.INDEX_CODE);
			if (indexCode == null || indexCode.trim().length() == x) {
				indexCode = "0";
			}
		}
		// logger.debug("detailed contact info...3");
		int index = Integer.parseInt(indexCode);
		commonEntityDataBean.getCommonEntityVO().getContactVOList().get(index);
		CommonContactVO contactVO = (CommonContactVO) commonEntityDataBean
				.getCommonEntityVO().getContactVOList().get(index);
		commonEntityDataBean.setSelectedConatctIndex(index);		
		
		CommonContactVO tempContactVO = new CommonContactVO();
		if(contactVO!=null){
			UIComponent component = ContactManagementHelper.findComponentInRoot("contactAuditId");
			if(component!=null){
				AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
				auditHistoryTable.setAuditLogControllerBean(new AuditLogControllerBean());
				
				
			}
			tempContactVO.setAuditKeyList(contactVO.getAuditKeyList());
		tempContactVO.setAddedAuditTimeStamp(contactVO.getAddedAuditTimeStamp());
		tempContactVO.setAddedAuditUserID(contactVO.getAddedAuditUserID());
		tempContactVO.setAuditTimeStamp(contactVO.getAuditTimeStamp());
		tempContactVO.setAuditUserID(contactVO.getAuditUserID());
		tempContactVO.setBeginDate(contactVO.getBeginDate());
		tempContactVO.setCommonContactTypeVO(contactVO.getCommonContactTypeVO());			
		tempContactVO.setCommonContactTypeVOList(contactVO.getCommonContactTypeVOList());
		tempContactVO.setCommonEntityRepCrossReferenceVersionNo(contactVO.getCommonEntityRepCrossReferenceVersionNo());
		tempContactVO.setCommonEntityTypeCode(contactVO.getCommonEntityTypeCode());
		tempContactVO.setContactSK(contactVO.getContactSK());
		tempContactVO.setContactType(contactVO.getContactType());
		tempContactVO.setContactTypeDesc(contactVO.getContactTypeDesc());
		tempContactVO.setDateOfBirth(contactVO.getDateOfBirth());
		tempContactVO.setDateOfDeath(contactVO.getDateOfDeath());
		tempContactVO.setDbRecord(contactVO.isDbRecord());
		tempContactVO.setEndDate(contactVO.getEndDate());
		tempContactVO.setEntityType(contactVO.getEntityType());
		tempContactVO.setEntityTypeDesc(contactVO.getEntityTypeDesc());
		tempContactVO.setExistingContactType(contactVO.getExistingContactType());
		tempContactVO.setGender(contactVO.getGender());
		//tempContactVO.setNameVO(contactVO.getNameVO());
		
		tempContactVO.getNameVO().setFirstName(contactVO.getNameVO().getFirstName());
		tempContactVO.getNameVO().setLastName(contactVO.getNameVO().getLastName());
		tempContactVO.getNameVO().setAddedAuditTimeStamp(contactVO.getNameVO().getAddedAuditTimeStamp());
		tempContactVO.getNameVO().setAddedAuditUserID(contactVO.getNameVO().getAddedAuditUserID());
		tempContactVO.getNameVO().setAuditTimeStamp(contactVO.getNameVO().getAuditTimeStamp());
		tempContactVO.getNameVO().setAuditUserID(contactVO.getNameVO().getAuditUserID());
		tempContactVO.getNameVO().setBusinessName(contactVO.getNameVO().getBusinessName());
		tempContactVO.getNameVO().setDbRecord(contactVO.getNameVO().isDbRecord());
		tempContactVO.getNameVO().setMiddleName(contactVO.getNameVO().getMiddleName());
		tempContactVO.getNameVO().setPhoneticFirstName(contactVO.getNameVO().getPhoneticFirstName());
		tempContactVO.getNameVO().setPhoneticLastName(contactVO.getNameVO().getPhoneticLastName());
		tempContactVO.getNameVO().setSortName(contactVO.getNameVO().getSortName());
		tempContactVO.getNameVO().setSuffixName(contactVO.getNameVO().getSuffixName());
		tempContactVO.getNameVO().setSuffixNameDesc(contactVO.getNameVO().getSuffixNameDesc());
		tempContactVO.getNameVO().setTitleName(contactVO.getNameVO().getTitleName());
		tempContactVO.getNameVO().setVersionNo(contactVO.getNameVO().getVersionNo());
		
		tempContactVO.setPrefix(contactVO.getPrefix());
		tempContactVO.setRepCommonEntityVO(contactVO.getRepCommonEntityVO());		
		tempContactVO.setSignificance(contactVO.getSignificance());
		tempContactVO.setSSN(contactVO.getSSN());
		tempContactVO.setStatus(contactVO.getStatus());
		tempContactVO.setTitle(contactVO.getTitle());
		tempContactVO.setVersionNo(contactVO.getVersionNo());
		tempContactVO.setStatusDesc(contactVO.getStatusDesc());
		
		commonEntityDataBean.setTempCommonContactVO(tempContactVO);
		
		}		
		
		commonEntityDataBean.getCommonEntityVO().setCommonContactVO(contactVO);			
		commonEntityDataBean.setNewContactRender(true);
		commonEntityDataBean.setContactEdit(true);
		commonEntityDataBean.setSaveContactChk(true);
		commonEntityDataBean.setNewContactTypeRender(false);
		commonEntityDataBean.setContactSaveMsg(false);
		commonEntityDataBean.setContactFlag(false);
		showContactAuditHistory();
		// logger.debug("detailed contact info...4....leaving");
		/**
		 * Below code is commented as Add contact type section has been removed
		 * for ES2
		 */
		//contactTypDataTable.setFirst(0);
//		To fix defect ESPRD00576234
		commonEntityDataBean.setCurrentPageId("contactfoucsid");
		return ContactManagementConstants.RENDER_SUCCESS;
		}

	/**
	 * Below code is commented as Add contact type section has been removed for
	 * ES2
	 */
	/**
	 * This method has the logic to display the UI components for detailed
	 * display of Contact Type information. Based on the selected index of
	 * contact Type Grid, this method would fetch the contactTypeVO from
	 * ContactVO.contactTypeList and that VO would get populated in the UI
	 * fields.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	/*
	 * public void displayDetailedContactTypeInfo() { FacesContext fc =
	 * FacesContext.getCurrentInstance(); Map map =
	 * fc.getExternalContext().getRequestParameterMap(); String indexCode = "0";
	 * if (map != null && map.size() > 0) { indexCode = (String)
	 * map.get(BenefitPlanConstants.INDEX_CODE); if (indexCode == null ||
	 * indexCode.trim().length() == 0) { indexCode = "0"; } } int index =
	 * Integer.parseInt(indexCode); CommonContactTypeVO contactTypeVO =
	 * (CommonContactTypeVO) commonEntityDataBean
	 * .getCommonEntityVO().getCommonContactVO()
	 * .getCommonContactTypeVOList().get(index);
	 * commonEntityDataBean.setSelectedConatctTypIndex(index);
	 * commonEntityDataBean.getCommonEntityVO().getCommonContactVO()
	 * .setCommonContactTypeVO(contactTypeVO); Date sysDate = new Date(); try {
	 * DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM); sysDate =
	 * df.parse(df.format(sysDate)); } catch (ParseException pe) {
	 * logger.error("Error During Parsing date in ContactControllerbean" + pe);
	 * pe.printStackTrace(); } if
	 * (ContactHelper.dateConverter(contactTypeVO.getEndDate()).compareTo(
	 * sysDate) < 0) { commonEntityDataBean.setDisableContactType(true); } else {
	 * commonEntityDataBean.setDisableContactType(false); }
	 * commonEntityDataBean.setNewContactTypeRender(true);
	 * commonEntityDataBean.setContactTypeEdit(true);
	 * commonEntityDataBean.setSaveContactTypeChk(true);
	 * commonEntityDataBean.setAddcontactVoidIndicatorRender(false); }
	 */

	/**
	 * To render the maincontact div.
	 */

	public void showContact() {
		commonEntityDataBean.setMainContactRender(true);
	}

	/**
	 * To set the error message.
	 * 
	 * @param message
	 *            The message to set.
	 */
	/*
	 * private void setErrorMessage(String message) { FacesContext fc =
	 * FacesContext.getCurrentInstance(); fc.getApplication().setMessageBundle(
	 * ProgramConstants.COMMON_CONTACT_PROPERTIES); ResourceBundle bundle =
	 * resourceBundle(fc); String errorMsg = bundle.getString(message);
	 * FacesMessage fm = new FacesMessage(errorMsg); fc.addMessage(null, fm); }
	 */
	/**
	 * This method is used for populating Resource Bundle.
	 * 
	 * @param facesContext
	 *            The facesContext to get Context values.
	 * @return ResourceBundle
	 */
	/*
	 * private ResourceBundle resourceBundle(FacesContext facesContext) {
	 * facesContext = FacesContext.getCurrentInstance(); UIViewRoot root =
	 * facesContext.getViewRoot(); String messageBundle =
	 * facesContext.getApplication().getMessageBundle(); Locale locale =
	 * root.getLocale(); ResourceBundle bundle =
	 * ResourceBundle.getBundle(messageBundle, locale); return bundle; }
	 */

	/**
	 * This method is used for populating Resource Bundle.
	 * 
	 * @param e
	 *            The event to handle.
	 */
	public void voidChange(ValueChangeEvent e) {

		CommonContactTypeVO contactType = commonEntityDataBean
				.getCommonEntityVO().getCommonContactVO()
				.getCommonContactTypeVO();
		commonEntityDataBean.getCommonEntityVO().getCommonContactVO()
				.setCommonContactTypeVO(contactType);

	}

	/**
	 * to sort the selected column.
	 * 
	 * @param event
	 *            The event is to handle the ActionEvent.
	 */
	public String sortContact(ActionEvent event) {

		ArrayList contactVOList = (ArrayList) commonEntityDataBean
				.getCommonEntityVO().getContactVOList();
		if (contactVOList != null) {
			logger.info("contactVOList size " + contactVOList.size());
		}
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
        logger.debug("sortColumn::"+sortColumn);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);
		logger.debug("sortOrder::"+sortOrder);
		
		
		commonEntityDataBean.setImageRender(event.getComponent().getId());
		commonEntityDataBean.setContactstartindex(0);
		Comparator comparator = new Comparator() {

			public int compare(Object obj1, Object obj2) {
				CommonContactVO commonContactVO1 = (CommonContactVO) obj1;
				CommonContactVO commonContactVO2 = (CommonContactVO) obj2;
				boolean ascending = false;
				if ("asc".equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}

				if ("Status".equals(sortColumn)) {

					return ascending ? (commonContactVO1.getStatusDesc().compareTo
							(commonContactVO2.getStatusDesc())) : (commonContactVO2.getStatusDesc()
							.compareTo(commonContactVO1.getStatusDesc()));
				}
				
				if ("Contact Type".equals(sortColumn)) {

					logger.debug(" inside Contact Type sorting ");
					return ascending ? (commonContactVO1.getContactType().compareTo
							(commonContactVO2.getContactType())) : (commonContactVO2.getContactType())
							.compareTo(commonContactVO1.getContactType());
				}
				
				if ("Begin Date".equals(sortColumn)) {

					return ascending ? (ContactHelper.dateConverter(commonContactVO1
							.getBeginDate()).compareTo(ContactHelper
							.dateConverter(commonContactVO2.getBeginDate())))
							: (ContactHelper.dateConverter(commonContactVO2
									.getBeginDate()).compareTo(ContactHelper
									.dateConverter(commonContactVO1.getBeginDate())));
				}
				
				if ("End Date".equals(sortColumn)) {

					return ascending ? (ContactHelper.dateConverter(commonContactVO1
							.getEndDate()).compareTo(ContactHelper
							.dateConverter(commonContactVO2.getEndDate())))
							: (ContactHelper.dateConverter(commonContactVO2
									.getEndDate()).compareTo(ContactHelper
									.dateConverter(commonContactVO1.getEndDate())));
				}
				
				
				if ("First Name".equals(sortColumn)) {
					return ascending ? (commonContactVO1.getNameVO()
							.getFirstName().compareToIgnoreCase(commonContactVO2
							.getNameVO().getFirstName())) : (commonContactVO2
							.getNameVO().getFirstName()
							.compareToIgnoreCase(commonContactVO1.getNameVO()
									.getFirstName()));
				}

				if ("Middle Name".equals(sortColumn)) {
					
					 if (null == commonContactVO1.getNameVO().getMiddleName())
	                    {
					 	commonContactVO1.getNameVO().setMiddleName(ContactManagementConstants.EMPTY_STRING);
	                    }
	                    if (null == commonContactVO2.getNameVO().getMiddleName())
	                    {
	                    	commonContactVO2.getNameVO().setMiddleName(ContactManagementConstants.EMPTY_STRING);
	                    }

					return ascending ? (commonContactVO1.getNameVO()
							.getMiddleName().compareToIgnoreCase(commonContactVO2
							.getNameVO().getMiddleName())) : (commonContactVO2
							.getNameVO().getMiddleName()
							.compareToIgnoreCase(commonContactVO1.getNameVO()
									.getMiddleName()));
				}

				if ("Last Name".equals(sortColumn)) {

					return ascending ? (commonContactVO1.getNameVO()
							.getLastName().compareToIgnoreCase(commonContactVO2
							.getNameVO().getLastName())) : (commonContactVO2
							.getNameVO().getLastName()
							.compareToIgnoreCase(commonContactVO1.getNameVO()
									.getLastName()));
				}
			

				if ("Suffix".equals(sortColumn)) {
					
					
					 if (null == commonContactVO1.getNameVO().getSuffixNameDesc())
	                    {
					 	commonContactVO1.getNameVO().setSuffixNameDesc(ContactManagementConstants.EMPTY_STRING);
	                    }
	                    if (null == commonContactVO2.getNameVO().getSuffixNameDesc())
	                    {
	                    	commonContactVO2.getNameVO().setSuffixNameDesc(ContactManagementConstants.EMPTY_STRING);
	                    }
					
					
					return ascending ? (commonContactVO1.getNameVO().getSuffixNameDesc()
							.compareTo(commonContactVO2.getNameVO().getSuffixNameDesc()))
							: (commonContactVO2.getNameVO().getSuffixNameDesc()
									.compareTo(commonContactVO1.getNameVO().getSuffixNameDesc()));

				}

				return 0;
			}

		};
		//FindBug Issue Resolved
		//Collections.sort(contactVOList, comparator);
		if (contactVOList != null) {
			Collections.sort(contactVOList, comparator);
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Below code is commented as Add contact type section has been removed for
	 * ES2
	 */
	/**
	 * To sort the selected columns for contact Type grid.
	 * 
	 * @param event
	 *            The event to handle.
	 */
	/*
	 * public void sortContactType(ActionEvent event) { ArrayList
	 * contactTypeVOList = commonEntityDataBean.getCommonEntityVO()
	 * .getCommonContactVO().getCommonContactTypeVOList(); if (contactTypeVOList !=
	 * null) { logger.debug("contactVOList size " + contactTypeVOList.size()); }
	 * final String sortColumn = (String) event.getComponent().getAttributes()
	 * .get(BenefitPlanConstants.COLUMN_NAME); final String sortOrder = (String)
	 * event.getComponent().getAttributes()
	 * .get(BenefitPlanConstants.SORT_ORDER); Comparator comparator = new
	 * Comparator() { public int compare(Object obj1, Object obj2) {
	 * CommonContactTypeVO commonContactTypeVO1 = (CommonContactTypeVO) obj1;
	 * CommonContactTypeVO commonContactTypeVO2 = (CommonContactTypeVO) obj2;
	 * boolean ascending = false; if ("asc".equals(sortOrder)) { ascending =
	 * true; } else { ascending = false; } if (sortColumn == null) { return 0; }
	 * if ("Void".equals(sortColumn)) { return ascending ?
	 * (commonContactTypeVO1.getVoidIndicator()
	 * .compareTo(commonContactTypeVO2.getVoidIndicator())) :
	 * (commonContactTypeVO2.getVoidIndicator() .compareTo(commonContactTypeVO1
	 * .getVoidIndicator())); } if ("Contact Type".equals(sortColumn)) { return
	 * ascending ? (commonContactTypeVO1.getContactType()
	 * .compareTo(commonContactTypeVO2.getContactType())) :
	 * (commonContactTypeVO2.getContactType() .compareTo(commonContactTypeVO1
	 * .getContactType())); } if ("Significance".equals(sortColumn)) { return
	 * ascending ? (commonContactTypeVO1.getSignificance()
	 * .compareTo(commonContactTypeVO2.getSignificance())) :
	 * (commonContactTypeVO2.getSignificance() .compareTo(commonContactTypeVO1
	 * .getSignificance())); } if ("Begin Date".equals(sortColumn)) { return
	 * ascending ? (commonContactTypeVO1.getBeginDate()
	 * .compareTo(commonContactTypeVO2.getBeginDate())) :
	 * (commonContactTypeVO2.getBeginDate() .compareTo(commonContactTypeVO1
	 * .getBeginDate())); } if ("End Date".equals(sortColumn)) { return
	 * ascending ? (commonContactTypeVO1.getEndDate()
	 * .compareTo(commonContactTypeVO2.getEndDate())) :
	 * (commonContactTypeVO2.getEndDate() .compareTo(commonContactTypeVO1
	 * .getEndDate())); } return 0; } }; Collections.sort(contactTypeVOList,
	 * comparator); }
	 */

	/**
	 * To get the validvalues.
	 */

	private void getReferenceData() {
		logger.debug("Inside getReferenceData method");
		InputCriteria inputCriteria = null;
		List list = null;
		/*Storing valid values to separate lists and set them to bean*/
		ArrayList contactSigList = new ArrayList();
		ArrayList genderList = new ArrayList();
		ArrayList statusList = new ArrayList();
		ArrayList suffixList = new ArrayList();
		ArrayList prefixList = new ArrayList();
		ArrayList entityList = new ArrayList();
		ArrayList contactTypeList = new ArrayList();
		//FindBug Issue Resolved
		//HashMap map = new HashMap();
		HashMap map =null;
		String select = "Please Select One";
		String selectIndex = "";
		ReferenceDataSearchVO referenceDataSearch = null;
		ReferenceServiceDelegate referenceServiceDelegate = null;
		ReferenceDataListVO referenceDataListVO = null;

		//filling the drop down of claim type code
		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_CMN_ENTY_TY_CD);
		list = new ArrayList();
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_REP_XREF_TY_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_CMN_ENTY_REP_SIG_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria.setElementName(ReferenceServiceDataConstants.G_GENDER_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_NAM_PREFX_CD);
		list.add(inputCriteria);
		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_ADR_USG_STAT_CD);
		list.add(inputCriteria);
		
		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.SUFFIX_NAME);
		list.add(inputCriteria);

		referenceDataSearch = new ReferenceDataSearchVO();
		referenceDataSearch.setInputList(list);
		referenceServiceDelegate = new ReferenceServiceDelegate();

		//		                 Pass the list to the delegate
		logger.debug("bfor callling delegate");
		referenceDataListVO = new ReferenceDataListVO();
		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);
		} catch (ReferenceServiceRequestException e) {
			logger.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			logger.error(e.getMessage(), e);
		}

		logger.debug("hashMap size is = "
				+ referenceDataListVO.getResponseMap().size());

		//for displaying retrieved values
		map = referenceDataListVO.getResponseMap();

		//fetching ENTITY TYPE
		list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
				+ ReferenceServiceDataConstants.G_CMN_ENTY_TY_CD);
		int cmnSize = list.size();

		for (int i = 0; i < cmnSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
			/*commonEntityDataBean.getEntityTypeList().add(
					new SelectItem(refVo.getValidValueCode(), refVo
							.getValidValueCode()
							+ "-" + refVo.getShortDescription()))*/;
			entityList.add(	new SelectItem(refVo.getValidValueCode(), refVo
					.getValidValueCode()
					+ "-" + refVo.getShortDescription()));

		}

		//		fetching CONTACT TYPE
		list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
				+ ReferenceServiceDataConstants.G_REP_XREF_TY_CD);
		int repSize = list.size();

		/*commonEntityDataBean.getContactTypeList().add(
				new SelectItem(selectIndex, select));*/
		contactTypeList.add(new SelectItem(selectIndex, select));
		for (int i = 0; i < repSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
			/*commonEntityDataBean.getContactTypeList().add(
					new SelectItem(refVo.getValidValueCode(), refVo
							.getValidValueCode()
							+ "-" + refVo.getShortDescription()));*/
			contactTypeList.add(new SelectItem(refVo.getValidValueCode(), refVo
					.getValidValueCode()
					+ "-" + refVo.getShortDescription()));

		}

		//		fetching rep sig TYPE
		list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
				+ ReferenceServiceDataConstants.G_CMN_ENTY_REP_SIG_CD);
		int sigSize = list.size();
		/*commonEntityDataBean.getContactSigList().add(
				new SelectItem(selectIndex, select));*/
		contactSigList.add(new SelectItem(selectIndex, select));
		for (int i = 0; i < sigSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
			/*commonEntityDataBean.getContactSigList().add(
					new SelectItem(refVo.getValidValueCode(), refVo
							.getValidValueCode()
							+ "-" + refVo.getShortDescription()));*/
			contactSigList.add(new SelectItem(refVo.getValidValueCode(), refVo
					.getValidValueCode()
					+ "-" + refVo.getShortDescription()));

		}
		
		logger.debug("entering status values..........");
		list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
				+ ReferenceServiceDataConstants.G_ADR_USG_STAT_CD);
		int statSize = list.size();
		logger.debug("status values..........");
		/*commonEntityDataBean.getStatusList().add(
				new SelectItem(selectIndex, select));*/
		statusList.add(new SelectItem(selectIndex, select));
		for (int i = 0; i < statSize; i++) {
			logger.debug("inside status for loop values..........");
			String statusValuesandDesc = "";
			
			ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
			statusValuesandDesc = refVo.getValidValueCode()	+ "-" + refVo.getShortDescription();
			logger.debug("statusValuesandDesc Value is-----"+statusValuesandDesc);
			/*commonEntityDataBean.getStatusList().add(
					new SelectItem(refVo.getValidValueCode(), refVo
							.getValidValueCode()
							+ "-" + refVo.getShortDescription()));*/
			statusList.add(new SelectItem(refVo.getValidValueCode(), refVo
					.getValidValueCode()
					+ "-" + refVo.getShortDescription()));

		}
		logger.debug("after status values..........");
		//		fetching gender code
		list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
				+ ReferenceServiceDataConstants.G_GENDER_CD);
		int genderSize = list.size();
		logger.debug("GenderSize frm getReferenceData -Contact :"+genderSize);
		/*commonEntityDataBean.getGenderList().add(
				new SelectItem(selectIndex, select));*/
		genderList.add(new SelectItem(selectIndex, select));
		for (int i = 0; i < genderSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
			/*commonEntityDataBean.getGenderList().add(
					new SelectItem(refVo.getValidValueCode(), refVo
							.getValidValueCode()
							+ "-" + refVo.getShortDescription()));*/
			genderList.add(
					new SelectItem(refVo.getValidValueCode(), refVo
							.getValidValueCode()
							+ "-" + refVo.getShortDescription()));

		}

		//		fetching name prefix code
		list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
				+ ReferenceServiceDataConstants.G_NAM_PREFX_CD);
		int namSize = list.size();
		/*commonEntityDataBean.getNamePrefixList().add(
				new SelectItem(selectIndex, select));*/
		prefixList.add(new SelectItem(selectIndex, select));
		for (int i = 0; i < namSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
			String refCode = refVo.getValidValueCode();
			//prefix value after major save
			//if (refCode != null && refCode.trim().length() > 2) {
			//	refCode = refCode.substring(0, 1);
			//}
			/*commonEntityDataBean.getNamePrefixList().add(
					new SelectItem(refCode, refVo.getShortDescription()));*/
			prefixList.add(new SelectItem(refCode, refVo.getShortDescription()));

		}
		//		fetching name suffix code
		list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
				+ ReferenceServiceDataConstants.SUFFIX_NAME);
		int suffixSize = list.size();
		/*commonEntityDataBean.getNameSuffixList().add(
				new SelectItem(selectIndex, select));*/
		suffixList.add(new SelectItem(selectIndex, select));
		for (int i = 0; i < suffixSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
			/*commonEntityDataBean.getNameSuffixList().add(
					new SelectItem(refVo.getValidValueCode(),refVo.getShortDescription()));*/
			//for fixing defect ESPRD660815
			//suffixList.add(new SelectItem(refVo.getValidValueCode(),refVo.getShortDescription()));
			suffixList.add(new SelectItem(refVo.getValidValueCode()
					,refVo.getValidValueCode()+"-"+refVo.getShortDescription()));

		}
		
		/**Setting the valid values lists to commonEntityDataBean*/
		commonEntityDataBean.setEntityTypeList(entityList);
		commonEntityDataBean.setContactTypeList(getEntityTypeReferenceDataForContacttype());
		commonEntityDataBean.setContactSigList(contactSigList);
		commonEntityDataBean.setStatusList(statusList);
		commonEntityDataBean.setGenderList(genderList);
		commonEntityDataBean.setNamePrefixList(prefixList);
		commonEntityDataBean.setNameSuffixList(suffixList);
	
	
	}
	 /**
     * This operation is used to get the reference data for all Entity Type.
     *
     * @return List
     */
    public List getEntityTypeReferenceDataForContacttype()
    {
        logger.info("getReferenceData");
        long entryTime = System.currentTimeMillis();
       Long userSK = getUserSK(getUserID());
        String businessUnitDesc = getBusinessUnitforUser(userSK);
        logger.debug("businessUnitDesc::::::::::::::"+businessUnitDesc);
        String select = "Please Select One";
		String selectIndex = "";

        List referenceList = new ArrayList();
        Map referenceMap = null;

        int referenceListSize = 0;
        List contactTypeList = new ArrayList();
        contactTypeList.add(new SelectItem(selectIndex, select));
        
        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        //FindBug Issue Resolved
        //ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
        ReferenceDataListVO referenceDataListVO =null;
        InputCriteria inputCriteriaEntityTyp = new InputCriteria();
        inputCriteriaEntityTyp
                .setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteriaEntityTyp.setListNumber(
                ContactManagementHelper.getSystemlistForCorrBU(
                		ContactManagementConstants.CONTACTTYPE,businessUnitDesc));
        logger.debug(" systemlistnumber:::::::"+inputCriteriaEntityTyp.getListNumber());

        referenceList.add(inputCriteriaEntityTyp);

        referenceDataSearch.setInputList(referenceList);

        try
        {
        	logger.debug(" inside Try block");
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);

            referenceMap = referenceDataListVO.getResponseMap();

            referenceList = (List) referenceMap
                    .get(FunctionalAreaConstants.GENERAL
                            + ProgramConstants.HASH_SEPARATOR
                            + String
                            .valueOf(ContactManagementHelper
                                    .getSystemlistForCorrBU(ContactManagementConstants.CONTACTTYPE,
                                    		businessUnitDesc)));
            referenceListSize = referenceList.size();
            logger.debug("referenceListSize--->" + referenceListSize);
            for (int i = 0; i < referenceListSize; i++)
            {
                ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                        .get(i);
                logger.debug((i + 1) + " vv code 1 = "
                        + refVo.getValidValueCode());
                logger.debug((i + 1) + " vv description 1= "
                        + refVo.getShortDescription());
                logger.debug((i + 1) + " lob code 1= " + refVo.getLobCode());
                String codesAndDesc = refVo.getValidValueCode() + "-"
                        + refVo.getShortDescription();
                contactTypeList.add(new SelectItem(refVo.getValidValueCode(),
                        codesAndDesc));

            }
        }
        catch (ReferenceServiceRequestException e)
        {
        	logger.debug(" inside ReferenceServiceRequestException");
        	e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        catch (SystemListNotFoundException e)
        {
        	logger.debug(" inside SystemListNotFoundException");
        	e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        long exitTime = System.currentTimeMillis();
        logger.info("CMEntityDataBean" + "#" + "getReferenceData" + "#"
                + (exitTime - entryTime));
        return contactTypeList;
    }
    /**
     * This method is used to get the UserSK given the userId.
     *
     * @param userId :
     *            String User Id.
     * @return Long : UserSK.
     */
    private Long getUserSK(String userId)
    {
        logger.info("getUserSK");

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        Long userSK = null;

        try
        {
            userSK = contactMaintenanceDelegate.getWorkUnitID(userId);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
        }

        return userSK;
    }

    public String getBusinessUnitforUser(Long userSK)
    {
        logger.info("setDepartmentsList");

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        String businessUnitDesc= null;
        DeptUserBasicInfo deptUserBasicInfo=new DeptUserBasicInfo();
        try
        {
           // List deptsList = contactMaintenanceDelegate.getDepartmentUsers(userSK);
        	deptUserBasicInfo.setUserEnterpriseSK(userSK);
       	    deptUserBasicInfo.setDataReqested(ProgramConstants.DUBI_LOB_Hierarchy_SK);
         	List deptsList= contactMaintenanceDelegate.getDeptUserBasicInfo(deptUserBasicInfo).getLobSKlist();

            if (deptsList != null)
            {
            	logger.debug("Dept list size" + deptsList.size());
                for (Iterator iter = deptsList.iterator(); iter.hasNext();)
                {
                   /* DepartmentUser deptUser = (DepartmentUser) iter.next();
                    String lobHierarchySK = deptUser.getDepartment()
                            .getLineOfBusinessHierarchy()
                            .getLineOfBusinessHierarchySK().toString();*/
                	Long lobSk= (Long)iter.next();
                	 
                	 String lobHierarchySK=lobSk.toString();
                    LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
                            .getDeptBusinessUnit(new Long(lobHierarchySK));
                	
                	
                    if (businessUnit != null)
                    {
                        logger.info("businessUnitDesc===="+businessUnitDesc);
                    	if (businessUnitDesc == null)
                        {
                            businessUnitDesc = businessUnit.getLobHierarchyDescription();
                        }
                        else if (!businessUnitDesc.equalsIgnoreCase(businessUnit.getLobHierarchyDescription()))
                        {
                            businessUnitDesc=ContactManagementConstants.AllOthers;
                            logger.info("businessUnitDesc-----"+businessUnitDesc);
                            break;
                        }
                    }
                }

            }
        }
        catch (LOBHierarchyFetchBusinessException lobExp)
        {
            logger.error(lobExp.getMessage(), lobExp);
        }


        return businessUnitDesc;
    }

	/**
	 * ContactType duplicate check.
	 * 
	 * @param contactTypeVOList
	 *            The contactTypeVOList to get the CommonContactTypeVOs
	 * @param currentContactType
	 *            The currentContactType to get the CommonContactTypeVO
	 * @param selectedIndex
	 *            The selectedIndex to get the selectedIndex
	 * @return boolean
	 */
	/*
	 * private boolean duplicateContactTypeCheck(ArrayList contactTypeVOList,
	 * String currentContactType, int selectedIndex) { boolean contactTypeFlg =
	 * false; if (contactTypeVOList != null && currentContactType != null) { int
	 * size = contactTypeVOList.size(); for (int cnt = 0; cnt < size; cnt++) {
	 * CommonContactTypeVO contactTYpeVO = (CommonContactTypeVO)
	 * contactTypeVOList .get(cnt); if
	 * (currentContactType.equals(contactTYpeVO.getContactType()) && cnt !=
	 * selectedIndex) { contactTypeFlg = true; } } } return contactTypeFlg; }
	 */

	/**
	 * To get the description based on code.
	 * 
	 * @param code
	 *            Holds the code valus.
	 * @param vvList
	 *            Holds the List of valid values.
	 * @return String
	 */
	private String getDescriptionFromVV(String code, List vvList) {
		String desc = "";
		String valueStr = "";
		if (vvList != null && !(vvList.isEmpty())) {
			int vvSize = vvList.size();
			for (int i = 0; i < vvSize; i++) {
				SelectItem selectItem = (SelectItem) vvList.get(i);
				valueStr = "";
				if (selectItem != null) {
					valueStr = (String) selectItem.getValue();
					if (valueStr != null && valueStr.equals(code)) {
						desc = selectItem.getLabel();
						if(desc == "Please Select One" )
						{
						  desc = " ";  
						}
						break;
					}
				}
			}
		}
		return desc;
	}

	/**
	 * This method is used to show audit child history for Common Contact
	 * details.
	 * 
	 * @return ProgramConstants.RETURN_SUCCESS : String. Navigation message
	 */
	public String showContactAuditHistory() {
		GlobalAuditsDelegate audit;
		//FindBug Issue Resolved
		//List list = new ArrayList();
		try {
			logger.debug("in show showContactAuditHistory history");
			audit = new GlobalAuditsDelegate();
			ContactHelper contactHelper = new ContactHelper();
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();
			
			Representative rep = contactHelper
					.convertCommonContactVOToDomain(commonEntityDataBean
							.getCommonEntityVO().getCommonContactVO());

			logger.info("Representative:" + rep);
			
			final EnterpriseSearchResultsVO enterpriseResultVO = audit
			.getAuditLogList(rep, 0,
					ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
			logger.info("Contact List Size:" + enterpriseResultVO.getSearchResults().size());
			commonEntityDataBean.setContactAuditHistList(enterpriseResultVO.getSearchResults());
			commonEntityDataBean.setContactHistRndr(false);
		} catch (Exception e) {
			logger.debug("Error in show Parent audit history  " + e);
		}

		return "";
	}

	public String showColumnChange() {
		//FindBug Issue Resolved
		//long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
  		    getCommonEntityDataBean().setContactHistRndr(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getCommonEntityDataBean().setSelectedCntAuditLog(auditLog);
		} catch (Exception e) {
			logger.debug("Error in show column change  " + e);
		}
		return ProgramConstants.RETURN_SUCCESS;
	}
	
	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}
	public CommonEntityDataBean getCommonEntityDataBean() {
		return commonEntityDataBean;
	}

	/**
	 * @param commonEntityDataBean
	 *            The commonEntityDataBean to set.
	 */
	public void setCommonEntityDataBean(
			CommonEntityDataBean commonEntityDataBean) {
		this.commonEntityDataBean = commonEntityDataBean;
	}
	
	public String closeColumnChange() {
		commonEntityDataBean.setContactHistRndr(false);
		return ProgramConstants.RETURN_SUCCESS;
	}
	
	public void setRecordRange(EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		long entryTime = System.currentTimeMillis();
		int listSize;
		logger.debug("Inside setRecordRange ");
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		logger.debug("Total no of records-->" + totalRecordCount);
		CommonEntityDataBean commonEntityDataBean = getCommonEntityDataBean();
		commonEntityDataBean.setCount((int) totalRecordCount);
		
		int noOfPages = commonEntityDataBean.getCount()
				/ commonEntityDataBean.getItemsPerPage();

		int modNofPages = commonEntityDataBean.getCount()
				% commonEntityDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}
		logger.debug("Number Of pages: " + noOfPages);

		commonEntityDataBean
				.setCurrentPage(ProgramConstants.INITIAL_CURRENT_PAGE);
		commonEntityDataBean.setNumberOfPages(noOfPages);
		commonEntityDataBean.setStartRecord(ProgramConstants.START_RECORD);
		commonEntityDataBean
				.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = commonEntityDataBean.getCount();

		logger.debug("Total records count is : " + listSize);
		if (listSize <= commonEntityDataBean.getItemsPerPage()) {
			commonEntityDataBean.setEndRecord(listSize);
		}
		//FindBug Issue Resolved
		/*if (listSize > commonEntityDataBean.getItemsPerPage()) {
			listSize = commonEntityDataBean.getItemsPerPage();
		}*/

		if (commonEntityDataBean.getCount() <= commonEntityDataBean
				.getItemsPerPage()) {
			commonEntityDataBean.setShowNext(false);
		} else {
			commonEntityDataBean.setShowNext(true);
		}
		commonEntityDataBean.setShowPrevious(false);
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ commonEntityDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ commonEntityDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ commonEntityDataBean.getStartRecord());
			logger.debug("The end record......."
					+ commonEntityDataBean.getEndRecord());
			logger.debug("The total count......."
					+ commonEntityDataBean.getCount());
		}
		long exitTime = System.currentTimeMillis();
		logger.info("DiagnosisCodeControllerBean" + "#" + "setRecordRange"
				+ "#" + "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
	}

	public String next() {
		logger.info("NEXT");
		long entryTime = System.currentTimeMillis();
		CommonEntityDataBean commonEntityDataBean = getCommonEntityDataBean();
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ commonEntityDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ commonEntityDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ commonEntityDataBean.getStartRecord());
			logger.debug("The end record......."
					+ commonEntityDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(commonEntityDataBean,
				commonEntityDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("SearchSystemParameterControllerBean" + "#" + "next"
					+ "#" + (exitTime - entryTime));
		}

		return ProgramConstants.NEXT;
	}

	/**
	 * This method will be used for navigating to previous page in the
	 * pagination.
	 * 
	 * @return String.
	 */
	public String previous() {
		logger.info("PREVIOUS");
		long entryTime = System.currentTimeMillis();
		logger.debug("Inside previous method");
		CommonEntityDataBean commonEntityDataBean = getCommonEntityDataBean();
		EnterpriseBaseDataBean entDataBean = previousPage(
				commonEntityDataBean, commonEntityDataBean
						.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("DiagnosisCodeControllerBean" + "#" + "previous" + "#"
					+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}

		return ProgramConstants.PREVIOUS;
	}
	/**
	 * This method will be used for writing the logic for populating search
	 * results for a given search criteria.
	 */
	private void populateList(int currentPage) {
		//FindBug Issue Resolved
		//ArrayList searchList = new ArrayList();
		GlobalAuditsDelegate audit = null;
		try {
			audit = new GlobalAuditsDelegate();

			ContactHelper contactHelper = new ContactHelper();
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();
			
			Representative rep = contactHelper
					.convertCommonContactVOToDomain(commonEntityDataBean
							.getCommonEntityVO().getCommonContactVO());

			logger.info("Representative:" + rep);
			
			EnterpriseSearchResultsVO enterpriseResultVO = audit
			.getAuditLogList(rep, (currentPage-1)
					* ProgramNumberConstants.INT_TEN,
					ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

			commonEntityDataBean.setContactAuditHistList(enterpriseResultVO.getSearchResults());

			//commonEntityDataBean.set.setAuditParentHistoryRender(true);
		} catch (Exception e) {
			logger.debug(e);
		}
	}


}
