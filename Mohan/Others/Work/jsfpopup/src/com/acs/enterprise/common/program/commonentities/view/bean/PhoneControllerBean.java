/*
 * Created on Mar 15, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.commonentities.view.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.acs.enterprise.common.program.benefitadministration.util.helper.BenefitPlanConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.Phone;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.PhoneVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CMEntityMaintainControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;

/**
 * This class is a controller bean to Phone jsp.
 */
public class PhoneControllerBean extends EnterpriseBaseControllerBean {

	/**
	 * Holds commonEntityDataBean.
	 */
	private CommonEntityDataBean commonEntityDataBean = ContactHelper
			.getCommonEntityDataBean();

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
	 * @param controlRequired
	 *            The controlRequired to set.
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}

	/**
	 * Newly Added This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermission() {
		String userPermission = "";
		String userid = getUserID();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.MAINTAIN_ENTITY_PAGE, userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode", userPermission);
		logger.debug("userPermission in PhoneRecord -->" + userPermission);

		// check the permission level for the user and set it in a boolean
		// variable. This will be used for Buttons/Links
		if (userPermission.equals("r")) {
			controlRequired = false;
		}
	}

	/**
	 * Newly Added This method get the User ID
	 * 
	 * @return String
	 */
	private String getUserID() {
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
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("LOGGED_IN_USERID", userID);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			logger.debug("Exception has come:");
		}

		return userID;
	}

	/** Enterprise Logger for Logging . */
	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(PhoneControllerBean.class.getName());

	/**
	 * This method has the logic to display the table to enter new phone data
	 * information.
	 */
	public String addPhone() {

		PhoneVO phoneVO = new PhoneVO();
		commonEntityDataBean.getCommonEntityVO().setPhoneVO(phoneVO);
		logger.info("Inside Addphone");
		createValidValues();

		if (phoneVO != null) {
			phoneVO.setBeginDate(null);
			phoneVO.setEndDate(null);
			phoneVO.setPhoneNumber(null);
			phoneVO.setEntityType(null);
			phoneVO.setSignificance(null);
			phoneVO.setStatus(null);
			phoneVO.setUsageTypeCode(null);
			phoneVO.setCountryCode("USA");
			phoneVO.setExtension(null);
			phoneVO.setOutOfService(false);
			phoneVO.setInternationalPhoneNo(null);
			phoneVO.setPhoneSK(null);

		}

		commonEntityDataBean.setPhoneVOIndex(null);
		commonEntityDataBean.setPhoneRendered(true);
		commonEntityDataBean.setViewingSaved(false);
		commonEntityDataBean.setEditPhoneRendered(false);
		commonEntityDataBean.setPhoneSaved(true);
		commonEntityDataBean.setPhoneVoidIndicatorRender(false);
		commonEntityDataBean.setAddphoneVoidIndicatorRender(true);
		commonEntityDataBean.setDisablePhoneRecord(false);
		commonEntityDataBean.setDisableBeginDate(false);
		commonEntityDataBean.setPhoneSaveMsg(false);
		commonEntityDataBean.setPhoneFlag(true);
		/* Big Save and Little Save */
		commonEntityDataBean.setCommonPhoneFalg(false);
		/* End Big Save and Little Save */
		//		To Fix Defect ESPRD00576234
		commonEntityDataBean.setCurrentPageId("phoneRecordfocusid");

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method has the logic to clear all the UI components of Phone if the
	 * Phone Data has newly entered value, else it will restore the old saved
	 * value of this particular Phone.
	 */
	public String resetPhone() {
		logger.debug(" Inside Reset Method=========");
		PhoneVO phoneVOset = null;
		//---FindBug issue Resolved---
		/* ArrayList phoneVOlist = new ArrayList(); */
		ArrayList phoneVOlist = null;
		//---FindBug issue Resolved---
		/* boolean flg = commonEntityDataBean.isPhoneRendered(); */
		int index;
		String indexStr = commonEntityDataBean.getPhoneVOIndex();
		if (indexStr != null && !indexStr.equals("")) {
			logger.debug("Indside if condition");
			index = Integer.parseInt(indexStr);
			phoneVOlist = (ArrayList) commonEntityDataBean.getCommonEntityVO()
					.getPhoneVOList();
			PhoneVO phonevo = commonEntityDataBean.getTempPhoneVO();
			PhoneVO tempphonevo = new PhoneVO();
			if (phonevo != null) {
				tempphonevo.setAuditKeyList(phonevo.getAuditKeyList());
				tempphonevo.setAddedAuditTimeStamp(phonevo
						.getAddedAuditTimeStamp());
				tempphonevo.setAddedAuditUserID(phonevo.getAddedAuditUserID());
				tempphonevo.setAuditTimeStamp(phonevo.getAuditTimeStamp());
				tempphonevo.setAuditUserID(phonevo.getAuditUserID());
				tempphonevo.setBeginDate(phonevo.getBeginDate());
				tempphonevo.setBeginDateStr(phonevo.getBeginDateStr());
				tempphonevo.setCommonEntityTypeCode(phonevo
						.getCommonEntityTypeCode());
				tempphonevo.setCountryCode(phonevo.getCountryCode());
				tempphonevo.setEndDate(phonevo.getEndDate());
				tempphonevo.setEndDateStr(phonevo.getEndDateStr());
				tempphonevo.setEntityType(phonevo.getEntityType());
				tempphonevo.setExistingUsageTypeCode(phonevo
						.getExistingUsageTypeCode());
				tempphonevo.setExtension(phonevo.getExtension());
				tempphonevo.setInternationalPhoneNo(phonevo
						.getInternationalPhoneNo());
				//checking the outofservice and outofservicestr
				if (phonevo.getOutOfServicestr() != null) {

					if (phonevo.getOutOfServicestr().equalsIgnoreCase(
							ProgramConstants.YES)) {
						tempphonevo.setOutOfService(true);
					} else if (phonevo.getOutOfServicestr().equalsIgnoreCase(
							ProgramConstants.NO)) {
						tempphonevo.setOutOfService(false);
					}
				}
				tempphonevo.setOutOfServicestr(phonevo.getOutOfServicestr());
				tempphonevo.setPhoneNumber(phonevo.getPhoneNumber());
				tempphonevo.setPhoneSK(phonevo.getPhoneSK());
				tempphonevo.setPhoneType(phonevo.getPhoneType());
				tempphonevo.setPhoneUsageSequenceNumber(phonevo
						.getPhoneUsageSequenceNumber());
				tempphonevo.setPhoneUsageSK(phonevo.getPhoneUsageSK());
				tempphonevo.setPhoneUsageVersionNo(phonevo
						.getPhoneUsageVersionNo());
				tempphonevo.setSignificance(phonevo.getSignificance());
				tempphonevo.setStatus(phonevo.getStatus());
				tempphonevo.setStatusstr(phonevo.getStatusstr());
				tempphonevo.setUsageTypeCode(phonevo.getUsageTypeCode());
				tempphonevo.setUsageTypeDesc(phonevo.getUsageTypeDesc());
				tempphonevo.setVersionNo(phonevo.getVersionNo());

				tempphonevo.setUsageList(commonEntityDataBean
						.getCommonEntityVO().getPhoneVO().getUsageList());
				tempphonevo
						.setSignificanceList(commonEntityDataBean
								.getCommonEntityVO().getPhoneVO()
								.getSignificanceList());
				tempphonevo.setCountryCodeList(commonEntityDataBean
						.getCommonEntityVO().getPhoneVO().getCountryCodeList());
				tempphonevo.setStatusList(commonEntityDataBean
						.getCommonEntityVO().getPhoneVO().getStatusList());

			}
			phoneVOlist.set(index, tempphonevo);
			commonEntityDataBean.getCommonEntityVO().setPhoneVO(tempphonevo);
		} else {
			logger.debug("Inside else condition");
			phoneVOset = clearPhoneVO(commonEntityDataBean.getCommonEntityVO()
					.getPhoneVO());
			commonEntityDataBean.getCommonEntityVO().setPhoneVO(phoneVOset);

		}
		commonEntityDataBean.setPhoneRendered(true);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method will construct new phoneVO with list that are fetched from
	 * valid values and returns new constructed VO.
	 * 
	 * @param phoneVO
	 *            This will contain List ofphoneVo
	 * @return newphoneVO This will contain List of newPhoneVO
	 */

	private PhoneVO clearPhoneVO(PhoneVO phoneVO) {
		PhoneVO newPhoneVO = new PhoneVO();
		//newPhoneVO.setEntityTypeCodeList(phoneVO.getEntityTypeCodeList());
		newPhoneVO.setSignificanceList(phoneVO.getSignificanceList());
		newPhoneVO.setUsageList(phoneVO.getUsageList());
		newPhoneVO.setCountryCodeList(phoneVO.getCountryCodeList());
		newPhoneVO.setStatusList(phoneVO.getStatusList());
		newPhoneVO.setCountryCode("USA");
		return newPhoneVO;
	}

	/**
	 * This method is called to clear and cancel the exisiting data in the Phone
	 * Details table.
	 */
	public String cancelPhone() {
		logger.debug("Inside cancelPhone");
		if (commonEntityDataBean != null) {
			resetPhone();
			commonEntityDataBean.setPhoneRendered(false);
			commonEntityDataBean.setEditPhoneRendered(false);
			commonEntityDataBean.setPhoneVoidIndicatorRender(false);
			//To Fix Defect ESPRD00576234
			commonEntityDataBean.setCurrentPageId("phoneRecordfocusid");
		} else {
			logger.debug("commonEntityDataBean  Is null");
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method checks for duplicate eaddress.
	 * 
	 * @param currentPhoneVO
	 *            holds currentPhone data.
	 * @return boolean : true or false
	 */
	public boolean isDuplicatePhone(PhoneVO currentPhoneVO) {
		logger.debug("public boolean isDuplicatePhone(PhoneVO currentPhoneVO)");
		boolean returnVal = false;
		if (currentPhoneVO != null
				&& commonEntityDataBean.getCommonEntityVO().getPhoneVOList() != null) {
			logger.debug("If block");

			String indexStr = commonEntityDataBean.getPhoneVOIndex();
			if (indexStr != null) {
				if (!indexStr.equals("")) {
					List tempPhoneVOList = new ArrayList(commonEntityDataBean
							.getCommonEntityVO().getPhoneVOList());
					tempPhoneVOList.remove(Integer.parseInt(indexStr));

					for (int i = 0; i < tempPhoneVOList.size(); i++) {
						PhoneVO phoneVO = (PhoneVO) tempPhoneVOList.get(i);
						if (phoneVO.getUsageTypeCode().equalsIgnoreCase(
								currentPhoneVO.getUsageTypeCode())
								&& phoneVO.getPhoneNumber().equalsIgnoreCase(
										currentPhoneVO.getPhoneNumber())) {
							returnVal = true;
							new CommonEntityValidator().setErrorMessage(
									ProgramConstants.PHONE_DUPLICATE,
									new Object[] {},
									ProgramConstants.PHONE_PROPERTIES, null);
							break;
						}
					}
				}
			} else {
				logger.debug("Else block");
				for (int i = 0; i < commonEntityDataBean.getCommonEntityVO()
						.getPhoneVOList().size(); i++) {
					PhoneVO phoneVO = (PhoneVO) commonEntityDataBean
							.getCommonEntityVO().getPhoneVOList().get(i);
					if (phoneVO.getUsageTypeCode().equalsIgnoreCase(
							currentPhoneVO.getUsageTypeCode())
							&& phoneVO.getPhoneNumber().equalsIgnoreCase(
									currentPhoneVO.getPhoneNumber())) {
						returnVal = true;
						new CommonEntityValidator().setErrorMessage(
								ProgramConstants.PHONE_DUPLICATE,
								new Object[] {},
								ProgramConstants.PHONE_PROPERTIES, null);
						break;
					}
				}
			}
		}
		return returnVal;
	}

	/**
	 * This operation is to save the PhoneVO to request scope, this method
	 * invokes the validatePhone method of commonEntityValidator. If the PhoneVO
	 * is valid then ValidatePhone() sets the PhoneVO to CommonEntityVO in the
	 * commonEntityDatatbean. And commonEntityDataBean will be save to request
	 * scope using tstate tag.
	 * 
	 * @param phoneVO
	 */
	public String savePhone() {
		logger.debug("public String savePhone()");
		PhoneVO phoneVO = null;
		CommonEntityVO commonEntityVO = null;
		ArrayList phoneVOList = null;
		boolean isPhoneValid = true;
		boolean addNewRow = true;
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
		//FindBug issue Resolved
		//ContactHelper contactHelper = new ContactHelper();
		phoneVO = commonEntityDataBean.getCommonEntityVO().getPhoneVO();
		if (!(commonEntityDataBean.isDisablePhoneRecord())) {
			isPhoneValid = commonEntityValidator.validatePhone(phoneVO);
		}
		/* Big Save and Little Save */
		commonEntityDataBean.setCommonPhoneFalg(isPhoneValid);

		/* End Big Save and Little Save */
		//---FindBud issue Resolved---
		/*
		 * if (isPhoneValid && commonEntityDataBean != null &&
		 * commonEntityDataBean.getCommonEntityVO() != null) {
		 */
		if (isPhoneValid && commonEntityDataBean.getCommonEntityVO() != null) {
			commonEntityVO = commonEntityDataBean.getCommonEntityVO();
			if (commonEntityDataBean.getCommonEntityVO().getPhoneVO() != null) {
				phoneVOList = (ArrayList) commonEntityVO.getPhoneVOList();
				if (phoneVOList == null) {
					phoneVOList = new ArrayList();
				}
				phoneVO.setBeginDateStr(ContactHelper
						.getDateInMMDDYYYYFormat(phoneVO.getBeginDate()));
				phoneVO.setEndDateStr(ContactHelper
						.getDateInMMDDYYYYFormat(phoneVO.getEndDate()));
				phoneVO.setUsageTypeDesc(getDescriptionFromVV(phoneVO
						.getUsageTypeCode(), commonEntityDataBean
						.getCommonEntityVO().getPhoneVO().getUsageList()));

				phoneVO.setStatusstr((getDescriptionFromVV(phoneVO.getStatus(),
						commonEntityDataBean.getCommonEntityVO().getPhoneVO()
								.getStatusList())));
				if (commonEntityDataBean.getPhoneVOIndex() != null) {
					int index = Integer.parseInt(commonEntityDataBean
							.getPhoneVOIndex());
					logger.debug("index  : " + index);

					PhoneVO updatePhone = (PhoneVO) commonEntityVO
							.getPhoneVOList().get(index);
					// Getting back from tempPhone (back up)
					phoneVO.setPhoneSK(updatePhone.getPhoneSK());
					commonEntityDataBean.setSmallSaveFlag("true");
					addNewRow = false;
				}
				boolean validatePhoneFliag = isDuplicatePhone(phoneVO);
				logger.debug("validatePhoneFliag--------------"
						+ validatePhoneFliag);
				if (addNewRow && !validatePhoneFliag) {
					logger.debug("ADD PHONE");
					// If adding new phone
					phoneVO.setPhoneSK(null);
					phoneVOList.add(phoneVO);
					commonEntityDataBean.setPhoneSaveMsg(true);
					commonEntityDataBean.setPhoneRendered(false);
					commonEntityDataBean.getCommonEntityVO().setPhoneVO(
							new PhoneVO());
				} else if (!addNewRow && !validatePhoneFliag) {
					logger.debug("EDIT PHONE");
					// 	If editing existing phone
					if (commonEntityDataBean.getPhoneVOIndex() != null) {
						// PhoneVO tempPhoneVO =
						// commonEntityDataBean.getTempPhoneVO();
						int index = Integer.parseInt(commonEntityDataBean
								.getPhoneVOIndex());
						//	Defect ESPRD00674368 Start
						//  commonEntityDataBean.getCommonEntityVO().getPhoneVOList().set(index,
						// phoneVO);

						PhoneVO oldUpdatePhone = (PhoneVO) commonEntityVO
								.getPhoneVOList().get(index);

						phoneVO.setPhoneSK(null);

						phoneVO.setBeginDate(ContactHelper
								.getDateInMMDDYYYYFormat(new Date()));
						phoneVO.setBeginDateStr(ContactHelper
								.getDateInMMDDYYYYFormat(new Date()));
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, -1);
						System.out.println("Done Date Addition");
						Date newDate = cal.getTime();
						oldUpdatePhone.setEndDate(ContactHelper
								.dateConverter(newDate));
						System.out.println("Done Date Addition New"
								+ oldUpdatePhone.getEndDate());
						oldUpdatePhone.setEndDateStr(ContactHelper
								.getDateInMMDDYYYYFormat(newDate));
						if (oldUpdatePhone.getEndDate() != null
								&& oldUpdatePhone.getEndDate().trim().length() > 0) {

							if (ContactHelper.dateConverter(
									oldUpdatePhone.getEndDate()).compareTo(
									ContactHelper.dateConverter(oldUpdatePhone
											.getBeginDate())) < 0) {

								System.out
										.println("Inside save Phone date check error**************");
								commonEntityValidator
										.setErrorMessage(
												ProgramConstants.END_DATE_LESSTHAN_BEGINDATE,
												new Object[] {},
												ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
												"phEnddate"); //PhEndDt1
								commonEntityDataBean.setCommonPhoneFalg(false);
								return ContactManagementConstants.RENDER_SUCCESS;
							}

						}
						commonEntityVO.getPhoneVOList().set(index,
								oldUpdatePhone);
						commonEntityVO.getPhoneVOList().add(phoneVO);

						// Defect ESPRD00674368 End
					}
					commonEntityDataBean.setPhoneSaveMsg(true);
					commonEntityDataBean.setPhoneRendered(false);
					commonEntityDataBean.setPhoneVOIndex(null);
					commonEntityDataBean.setEditPhoneRendered(false);
				} else {
					logger.debug("duplicate phone entry");
					// If Duplicate entry found in both the cases.
					/* Big Save and Little Save */
					commonEntityDataBean.setCommonPhoneFalg(false);
					/* End Big Save and Little Save */
					commonEntityDataBean.setPhoneRendered(true);
					commonEntityDataBean.setPhoneSaved(true);
				}
				//	sortPhone(phoneVOList);
				commonEntityVO.setPhoneVOList(phoneVOList);
				commonEntityDataBean.setCommonEntityVO(commonEntityVO);
				/**
				 * navigate to add new phone record
				 */
				if ((phoneVO.getOutOfService())) {
					phoneVO.setOutOfServicestr(ProgramConstants.YES);
				} else {
					phoneVO.setOutOfServicestr(ProgramConstants.NO);
				}

				createValidValues();

				commonEntityDataBean.setViewingSaved(false);
				commonEntityDataBean.setSmallSaveFlag("true");
				commonEntityDataBean.setPhoneVoidIndicatorRender(false);
				commonEntityDataBean.setDisablePhoneRecord(false);
				commonEntityDataBean.setDisableBeginDate(false);
				commonEntityDataBean.setAddphoneVoidIndicatorRender(true);
				commonEntityDataBean.setCommonPhoneSaved(true);
			}
		}
		commonEntityDataBean.setEaddrSaveMsg(false);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	//private PhoneVO tempPhoneVO;

	/**
	 * To handle Edit Phone events.
	 */

	public String editPhone() {
		logger.info("editPhone===");
		final String methodNAME = "editPhone()";
		ArrayList phoneVOList = null;
		PhoneVO phoneVO = null;
		PhoneVO tempPhoneVO = null;
		//commonEntityDataBean.setPhoneFlag(false);
		commonEntityDataBean.setPhoneRendered(true);
		commonEntityDataBean.setPhoneFlag(false);

		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get(ProgramConstants.INDEX_CODE);

		int index = Integer.parseInt(indexCode);
		commonEntityDataBean.setPhoneVOIndex(indexCode);
		logger.debug("index value is>>> - Phone Edit " + index);

		phoneVOList = (ArrayList) commonEntityDataBean.getCommonEntityVO()
				.getPhoneVOList();
		logger.debug("phoneVOList" + phoneVOList);
		//---FindBug issue Resolved---
		/* logger.debug("phoneVOList.size() :::: " + phoneVOList.size()); */
		if (phoneVOList != null) {
			logger.debug("phoneVOList.size()    :::: " + phoneVOList.size());
			UIComponent component = ContactManagementHelper
					.findComponentInRoot("phoneAuditId");
			if (component != null) {
				AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
				//auditHistoryTable.setAuditLogControllerBean(new
				// AuditLogControllerBean());
				auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
						false);

			}
			tempPhoneVO = (PhoneVO) phoneVOList.get(index);
			phoneVO = new PhoneVO();
			phoneVO.setAuditKeyList(tempPhoneVO.getAuditKeyList());
			phoneVO
					.setAddedAuditTimeStamp(tempPhoneVO
							.getAddedAuditTimeStamp());
			phoneVO.setAddedAuditUserID(tempPhoneVO.getAddedAuditUserID());
			phoneVO.setAuditTimeStamp(tempPhoneVO.getAuditTimeStamp());
			phoneVO.setAuditUserID(tempPhoneVO.getAuditUserID());
			phoneVO.setBeginDate(tempPhoneVO.getBeginDate());
			phoneVO.setBeginDateStr(tempPhoneVO.getBeginDateStr());
			phoneVO.setCommonEntityTypeCode(tempPhoneVO
					.getCommonEntityTypeCode());
			phoneVO.setCountryCode(tempPhoneVO.getCountryCode());
			//phoneVO.setDbRecord(tempPhoneVO.get);
			phoneVO.setEndDate(tempPhoneVO.getEndDate());
			phoneVO.setEndDateStr(tempPhoneVO.getEndDateStr());
			phoneVO.setEntityType(tempPhoneVO.getEntityType());
			phoneVO.setExistingUsageTypeCode(tempPhoneVO
					.getExistingUsageTypeCode());
			phoneVO.setExtension(tempPhoneVO.getExtension());
			phoneVO.setInternationalPhoneNo(tempPhoneVO
					.getInternationalPhoneNo());
			//checking the outofservice and outofservicestr
			if (tempPhoneVO.getOutOfServicestr().equalsIgnoreCase(
					ProgramConstants.YES)) {
				phoneVO.setOutOfService(true);
			} else if (tempPhoneVO.getOutOfServicestr().equalsIgnoreCase(
					ProgramConstants.NO)) {
				phoneVO.setOutOfService(false);
			}
			//phoneVO.setOutOfService(tempPhoneVO.getOutOfService());
			phoneVO.setOutOfServicestr(tempPhoneVO.getOutOfServicestr());
			phoneVO.setPhoneNumber(tempPhoneVO.getPhoneNumber());
			phoneVO.setPhoneSK(tempPhoneVO.getPhoneSK());
			phoneVO.setPhoneType(tempPhoneVO.getPhoneType());
			phoneVO.setPhoneUsageSequenceNumber(tempPhoneVO
					.getPhoneUsageSequenceNumber());
			phoneVO.setPhoneUsageSK(tempPhoneVO.getPhoneUsageSK());
			phoneVO
					.setPhoneUsageVersionNo(tempPhoneVO
							.getPhoneUsageVersionNo());
			phoneVO.setSignificance(tempPhoneVO.getSignificance());
			phoneVO.setStatus(tempPhoneVO.getStatus());
			phoneVO.setStatusstr(tempPhoneVO.getStatusstr());
			phoneVO.setUsageTypeCode(tempPhoneVO.getUsageTypeCode());
			phoneVO.setUsageTypeDesc(tempPhoneVO.getUsageTypeDesc());
			phoneVO.setVersionNo(tempPhoneVO.getVersionNo());
			commonEntityDataBean.getCommonEntityVO().setPhoneVO(phoneVO);
			createValidValues();
			commonEntityDataBean.setPhoneVO(tempPhoneVO);

			tempPhoneVO.setUsageList(commonEntityDataBean.getCommonEntityVO()
					.getPhoneVO().getUsageList());
			tempPhoneVO.setSignificanceList(commonEntityDataBean
					.getCommonEntityVO().getPhoneVO().getSignificanceList());
			tempPhoneVO.setCountryCodeList(commonEntityDataBean
					.getCommonEntityVO().getPhoneVO().getCountryCodeList());
			tempPhoneVO.setStatusList(commonEntityDataBean.getCommonEntityVO()
					.getPhoneVO().getStatusList());

			commonEntityDataBean.setTempPhoneVO(tempPhoneVO);
			Date sysDate = new Date();
			commonEntityDataBean.setDisablePhoneRecord(false);
			if (sysDate.after(ContactHelper.dateConverter(tempPhoneVO
					.getEndDate()))) {
				commonEntityDataBean.setDisablePhoneRecord(true);
			}

			commonEntityDataBean.setViewingSaved(true);
			commonEntityDataBean.setPhoneRendered(true);
			commonEntityDataBean.setEditPhoneRendered(true);
			commonEntityDataBean.setPhoneRendered(true);
			commonEntityDataBean.setPhoneSaveMsg(false);
			commonEntityDataBean.setDisableBeginDate(true);
			// showPhoneAuditHistory();
		}
		logger.info("Existing method --" + methodNAME);
		//		To Fix Defect ESPRD00576234
		commonEntityDataBean.setCurrentPageId("phoneRecordfocusid");
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * To sort.
	 * 
	 * @param event
	 *            Holds the ActionEvent.
	 */

	public String sort(ActionEvent event) {

		logger.debug("In side Sort method " + event);

		ArrayList phoneVOList = (ArrayList) commonEntityDataBean
				.getCommonEntityVO().getPhoneVOList();
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(BenefitPlanConstants.COLUMN_NAME);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(BenefitPlanConstants.SORT_ORDER);
		commonEntityDataBean.setImageRender(event.getComponent().getId());

		//by default sort display 1st page
		commonEntityDataBean.setStartIndexForPhone(0);
		boolean ascending = false;

		if ("asc".equals(sortOrder)) {
			ascending = true;
		}

		logger.debug("sortColumn:-  " + sortColumn);
		logger.debug("sortOrder:- " + sortOrder);

		ContactHelper helper = new ContactHelper();
		helper.sortPhone(phoneVOList, sortColumn, ascending);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method fo creating valid values.
	 */
	public void createValidValues() {

		logger.debug("Inside Creste valid Values ---phone controller bean");
		ArrayList entityTypeCodeList = new ArrayList();
		ArrayList significanceList = new ArrayList();
		ArrayList usageList = new ArrayList();
		ArrayList countryCodeList = new ArrayList();
		ArrayList statusList = new ArrayList();
		InputCriteria inputCriteria = null;
		String select = "Please Select One";
		String selectIndex = "";

		List list = null;

		//FindBugs Issue Resolved
		//HashMap map = new HashMap();
		HashMap map = null;
		ReferenceDataSearchVO referenceDataSearch = null;
		ReferenceServiceDelegate referenceServiceDelegate = null;
		ReferenceDataListVO referenceDataListVO = null;

		//filling the drop down of claim type code

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_PHONE_TY_SIG_CD);
		list = new ArrayList();
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria.setElementName(ReferenceServiceDataConstants.G_PHN_TY_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_CMN_ENTY_TY_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria.setElementName(ReferenceServiceDataConstants.G_CNTRY_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_PHONE_USG_STAT_CD);
		list.add(inputCriteria);

		referenceDataSearch = new ReferenceDataSearchVO();
		referenceDataSearch.setInputList(list);
		referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBugs Issue Resolved
		//referenceDataListVO = new ReferenceDataListVO();

		try

		{

			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);
			//for displaying retrieved values
			map = referenceDataListVO.getResponseMap();
			if (map != null) {
				list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
						+ ReferenceServiceDataConstants.G_PHONE_TY_SIG_CD);
				int phoneSize = list.size();
				significanceList.add(new SelectItem(selectIndex, select));
				ReferenceServiceVO refVo;
				for (int i = 0; i < phoneSize; i++)

				{

					refVo = (ReferenceServiceVO) list.get(i);
					significanceList.add(new SelectItem(refVo
							.getValidValueCode(), refVo.getValidValueCode()
							+ "-" + refVo.getShortDescription()));

				}

				/*
				 * commonEntityDataBean.getCommonEntityVO().getEaddressVO()
				 * .setSignificanceList(significanceList);
				 */
				list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"

				+ ReferenceServiceDataConstants.G_PHN_TY_CD);
				int phTypeSize = list.size();
				usageList.add(new SelectItem(selectIndex, select));
				for (int i = 0; i < phTypeSize; i++)

				{

					refVo = (ReferenceServiceVO) list.get(i);
					usageList.add(new SelectItem(refVo.getValidValueCode(),
							refVo.getValidValueCode() + "-"
									+ refVo.getShortDescription()));

				}

				list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
						+ ReferenceServiceDataConstants.G_CMN_ENTY_TY_CD);
				int cmnSize = list.size();
				entityTypeCodeList.add(new SelectItem(selectIndex, select));
				for (int i = 0; i < cmnSize; i++)

				{
					refVo = (ReferenceServiceVO) list.get(i);
					entityTypeCodeList.add(new SelectItem(refVo
							.getValidValueCode(), refVo.getValidValueCode()
							+ "-" + refVo.getShortDescription()));

				}

				/*
				 * list = (List) map.get(FunctionalAreaConstants.GENERAL + "#" +
				 * ReferenceServiceDataConstants.G_CNTRY_CD); int cntrySize =
				 * list.size(); countryCodeList.add(new SelectItem(selectIndex,
				 * select)); for (int i = 0; i < cntrySize; i++) { refVo =
				 * (ReferenceServiceVO) list.get(i); countryCodeList.add(new
				 * SelectItem(refVo.getValidValueCode(),
				 * refVo.getValidValueCode() + "-" +
				 * refVo.getShortDescription())); }
				 */
				list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
						+ ReferenceServiceDataConstants.G_CNTRY_CD);
				int cntrySize = list.size();
				countryCodeList.add(new SelectItem(selectIndex, select));
				for (int i = cntrySize - 1; i >= 0; i--) {
					refVo = (ReferenceServiceVO) list.get(i);
					countryCodeList.add(new SelectItem(refVo
							.getValidValueCode(), refVo.getValidValueCode()
							+ "-" + refVo.getShortDescription()));
				}

				list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
						+ ReferenceServiceDataConstants.G_PHONE_USG_STAT_CD);
				int cmnEntSize = list.size();
				statusList.add(new SelectItem(selectIndex, select));
				for (int i = 0; i < cmnEntSize; i++) {
					refVo = (ReferenceServiceVO) list.get(i);
					statusList.add(new SelectItem(refVo.getValidValueCode(),
							refVo.getValidValueCode() + "-"
									+ refVo.getShortDescription()));
				}

				commonEntityDataBean.getCommonEntityVO().getPhoneVO()
						.setEntityTypeCodeList(entityTypeCodeList);

				commonEntityDataBean.getCommonEntityVO().getPhoneVO()
						.setSignificanceList(significanceList);
				commonEntityDataBean.getCommonEntityVO().getPhoneVO()
						.setUsageList(usageList);
				commonEntityDataBean.getCommonEntityVO().getPhoneVO()
						.setCountryCodeList(countryCodeList);
				commonEntityDataBean.getCommonEntityVO().getPhoneVO()
						.setStatusList(statusList);
			}
		} catch (ReferenceServiceRequestException e) {
			logger.error(e.getMessage());
		} catch (SystemListNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * This method will fetch the lob description for the selected lob.
	 * 
	 * @param e
	 *            This stores the action Event
	 */
	public void onChange(ValueChangeEvent e) {

		String countryCodeStatus = (String) e.getNewValue();

		if (countryCodeStatus != null && !countryCodeStatus.equals("")) {
			if (!countryCodeStatus.equals(ProgramConstants.COUNTRY_CODE_VALUE)) {
				commonEntityDataBean.setCountryCodeStatus(true);

			}
		}
	}

	private boolean ajaxChangeFlag = true;

	private boolean onAjaxChange = true;

	/**
	 * @return Returns the onAjaxChange.
	 */
	public boolean isOnAjaxChange() {
		logger.info("Entered into isOnAjaxChange()");
		String countryCodeStatus = null;
		Map map = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		if (map != null && map.get("cntryCd4") != null) {
			countryCodeStatus = map.get("cntryCd4").toString();
			logger.info("countryCodeStatus::" + countryCodeStatus);
		}
		if (countryCodeStatus != null && !countryCodeStatus.equals("")
				&& ajaxChangeFlag) {
			if (!countryCodeStatus.equals(ProgramConstants.COUNTRY_CODE_VALUE)) {
				commonEntityDataBean.setCountryCodeStatus(true);
				ajaxChangeFlag = false;
			}
		}
		logger.info("Exit of isOnAjaxChange()");
		return onAjaxChange;
	}

	/**
	 * @param onAjaxChange
	 *            The onAjaxChange to set.
	 */
	public void setOnAjaxChange(boolean onAjaxChange) {
		this.onAjaxChange = onAjaxChange;
	}

	/*
	 * Change done for ES2 DO changes
	 */
	/**
	 * methods for rendering void indicator.
	 * 
	 * @param event :
	 *            ValueChangeEvent object
	 */
	public void showVoidIndicator(ValueChangeEvent event) {
		/**
		 * this method no longer used as part of ES2
		 */
		/*
		 * long entryTime = System.currentTimeMillis(); if
		 * (logger.isDebugEnabled()) { logger.debug("void ind" +
		 * commonEntityDataBean.getCommonEntityVO().getPhoneVO()
		 * .getVoidIndicator()); logger.debug("value " + event.getNewValue()); }
		 * if (event != null && event.getNewValue() != null &&
		 * event.getNewValue().equals(BenefitPlanConstants.Y)) {
		 * commonEntityDataBean.setPhoneVoidIndicatorRender(true);
		 * commonEntityDataBean.getCommonEntityVO().getPhoneVO()
		 * .setVoidIndicator(BenefitPlanConstants.Y); } else {
		 * commonEntityDataBean.setPhoneVoidIndicatorRender(false); }
		 * logger.debug("BOOL " +
		 * commonEntityDataBean.isPhoneVoidIndicatorRender()); long exitTime =
		 * System.currentTimeMillis(); logger.info("PhoneControllerBean" + "#" +
		 * "showVoidIndicator" + "#" + (exitTime - entryTime));
		 */
	}

	/**
	 * This method is used to show audit child history for Provider Type
	 * details.
	 * 
	 * @return ProgramConstants.RETURN_SUCCESS : String. Navigation message
	 */
	public String showPhoneAuditHistory() {
		GlobalAuditsDelegate audit;
		//FindBug issue Resolved
		//List list = new ArrayList();
		try {
			logger.debug("in show showPhoneAuditHistory history");
			audit = new GlobalAuditsDelegate();
			ContactHelper contactHelper = new ContactHelper();
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();

			PhoneVO phoneVO = commonEntityDataBean.getCommonEntityVO()
					.getPhoneVO();

			Phone phone = contactHelper.getPhoneFromPhoneVO(phoneVO);
			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(phone, 0,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
			logger.debug("Phone List Size:"
					+ enterpriseResultVO.getSearchResults().size());
			commonEntityDataBean.setPhoneAuditHistList(enterpriseResultVO
					.getSearchResults());
			commonEntityDataBean.setPhoneHistRndr(false);
		} catch (Exception e) {
			logger.debug("Phone - Error in show Parent audit history : "
					+ e.toString());
			logger.debug("Error in show Parent audit history  " + e);
		}

		return "";
	}

	public String closeColumnChange() {
		commonEntityDataBean.setPhoneHistRndr(false);
		return ProgramConstants.RETURN_SUCCESS;
	}

	public String showColumnChange() {
		//--- isuue Resolved---
		/* long entryTime = System.currentTimeMillis(); */
		GlobalAuditsDelegate auditDelegate = null;
		try {
			getCommonEntityDataBean().setPhoneHistRndr(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getCommonEntityDataBean().setSelectedPhnAuditLog(auditLog);
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

	/**
	 * @return Returns the commonEntityDataBean.
	 */
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

	private String updateEntityStr;

	/**
	 * @return Returns the updateEntityStr.
	 */
	public String getUpdateEntityStr() {

		if (commonEntityDataBean.isUpdateEntityFlag()) {

			CMEntityMaintainControllerBean bean = new CMEntityMaintainControllerBean();
			bean.updateCMEntity();
			commonEntityDataBean.setUpdateEntityFlag(false);
		}
		return updateEntityStr;
	}

	/**
	 * @param updateEntityStr
	 *            The updateEntityStr to set.
	 */
	public void setUpdateEntityStr(String updateEntityStr) {
		this.updateEntityStr = updateEntityStr;
	}

	public void setRecordRange(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
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
		commonEntityDataBean.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = commonEntityDataBean.getCount();

		logger.debug("Total records count is : " + listSize);
		if (listSize <= commonEntityDataBean.getItemsPerPage()) {
			commonEntityDataBean.setEndRecord(listSize);
		}
		//---FindBug issues resolved---
		/*
		 * if (listSize > commonEntityDataBean.getItemsPerPage()) { listSize =
		 * commonEntityDataBean.getItemsPerPage(); }
		 */

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
		logger.debug("NEXT");
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
		logger.debug("PREVIOUS");
		long entryTime = System.currentTimeMillis();
		logger.debug("Inside previous method");
		logger.debug("\n\nprevious\n\n");
		CommonEntityDataBean commonEntityDataBean = getCommonEntityDataBean();
		EnterpriseBaseDataBean entDataBean = previousPage(commonEntityDataBean,
				commonEntityDataBean.getItemsPerPage());
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

		//FindBug issue Resolved
		//ArrayList searchList = new ArrayList();
		GlobalAuditsDelegate audit = null;
		try {
			audit = new GlobalAuditsDelegate();

			ContactHelper contactHelper = new ContactHelper();
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();

			PhoneVO phoneVO = commonEntityDataBean.getCommonEntityVO()
					.getPhoneVO();

			Phone phone = contactHelper.getPhoneFromPhoneVO(phoneVO);

			EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(phone, (currentPage - 1)
							* ProgramNumberConstants.INT_TEN,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

			logger.debug("Contact List Size:"
					+ enterpriseResultVO.getSearchResults().size());
			commonEntityDataBean.setPhoneAuditHistList(enterpriseResultVO
					.getSearchResults());

		} catch (Exception e) {
			logger.debug(e);
		}
	}

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

		logger.debug("code:::::::::   " + code);
		logger.debug(" List size ::::::" + vvList.size());
		String desc = "";
		String valueStr = "";
		int size = vvList.size();
		for (int i = 0; i < size; i++) {
			logger.debug("inside For Loop)");
			SelectItem selectItem = (SelectItem) vvList.get(i);
			valueStr = "";
			if (selectItem != null) {
				valueStr = (String) selectItem.getValue();
				if (valueStr != null && valueStr.equals(code)) {
					desc = selectItem.getLabel();
					logger.debug("desc::::::::: =====  " + desc);
					if (desc == "Please Select One") {
						desc = " ";
					}
					logger.debug("desc:::::::::   " + desc);
					break;

				}
			}
		}
		return desc;
	}

}