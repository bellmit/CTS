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
import com.acs.enterprise.common.program.commonentities.common.domain.EAddress;
//import com.acs.enterprise.common.program.commonentities.common.domain.Representative;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonContactVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.EAddressVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;

/**
 * This is EAddress Controller bean.
 */
public class EAddressControllerBean extends EnterpriseBaseControllerBean {
	/**
	 * CommonEntityDataBean Reference.
	 */
	private CommonEntityDataBean commonEntityDataBean = ContactHelper
			.getCommonEntityDataBean();

	/**
	 * This field is used to store whether user has permission.
	 */
	private boolean controlRequired = true; // Newly added

	/** Enterprise Logger for Logging. */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(getClass().getName());

	/**
	 * This method checks for duplicate eaddress.
	 * 
	 * @param currentEAddressVO
	 *            holds currentEAddress info.
	 * @return boolean : true or false
	 */
	public boolean isDuplicateEAddress(EAddressVO currentEAddressVO,
			EAddressVO oldEAddressVO) {
		logger.info("isDuplicateEAddress=========");
		boolean returnVal = true;

		logger.info("currentEAddressVO.getSignificance()"
				+ currentEAddressVO.getSignificance());
		logger.info("oldEAddressVO.getSignificance()"
				+ oldEAddressVO.getSignificance());
		logger.info("1--------"
				+ !currentEAddressVO.getUsageTypeCode().equalsIgnoreCase(
						oldEAddressVO.getUsageTypeCode()));
		logger.info("2-----"
				+ !currentEAddressVO.getSignificance().equalsIgnoreCase(
						oldEAddressVO.getSignificance()));
		logger.info("3------------"
				+ (currentEAddressVO.getBouncedAddressStr() == oldEAddressVO
						.getBouncedAddressStr()));
		logger.info("4---------"
				+ currentEAddressVO.getBouncedAddressStr().equalsIgnoreCase(
						oldEAddressVO.getBouncedAddressStr()));
		if ((!currentEAddressVO.getUsageTypeCode().equalsIgnoreCase(
				oldEAddressVO.getUsageTypeCode()))
				|| (!currentEAddressVO.getBeginDate().equals(
						oldEAddressVO.getBeginDate()))
				|| (!currentEAddressVO.getEndDate().equals(
						oldEAddressVO.getEndDate()))
				|| (!currentEAddressVO.getSignificance().equalsIgnoreCase(
						oldEAddressVO.getSignificance()))
				|| (!currentEAddressVO.getStatus().equalsIgnoreCase(
						oldEAddressVO.getStatus()))
				|| (!currentEAddressVO.getEaddress().equalsIgnoreCase(
						oldEAddressVO.getEaddress()))
				|| (!((currentEAddressVO.getBouncedAddressStr() == oldEAddressVO
						.getBouncedAddressStr()) || (currentEAddressVO
						.getBouncedAddressStr().equalsIgnoreCase(oldEAddressVO
						.getBouncedAddressStr()))))) {
			returnVal = false;
			commonEntityDataBean.setCommonEAddressFlag(false);
		}
		return returnVal;
	}

	/**
	 * This operation is to save theEAddressVO to request scope, this method
	 * invokes the validateEAddress method of commonEntityValidator. If the
	 * EAddressVO is valid then ValidateEAddress() sets the EAddressVO to
	 * CommonEntityVO in the commonEntityDatatbean. And commonEntityDataBean
	 * will be save to request scope using tstate tag.
	 * 
	 * @param eAddressVO
	 * @return
	 */
	public String saveEAddress() {
		final String methodName = "saveEAddress";
		EAddressVO eAddressVO = null;
		ArrayList eAddressList = null;
		boolean isEAddressValid = true;
		commonEntityDataBean.setEaddressSaved(false);
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
		CommonEntityVO commonEntityVO = commonEntityDataBean
				.getCommonEntityVO();

		if (commonEntityVO != null && commonEntityVO.getEaddressVO() != null) {
			isEAddressValid = commonEntityValidator
					.validateEAddress(commonEntityVO.getEaddressVO());
			/* Big Save and Little Save */
			commonEntityDataBean.setCommonEAddressFlag(isEAddressValid);
			/* End Big Save and Little Save */
			logger.info("isEAddressValid==========" + isEAddressValid);
			if (isEAddressValid) {
				eAddressVO = commonEntityVO.getEaddressVO();
				eAddressVO.setBeginDate(ContactHelper
						.getDateInMMDDYYYYFormat(new Date()));
				eAddressVO.setBeginDateStr(ContactHelper
						.getDateInMMDDYYYYFormat(new Date()));
				eAddressVO.setEndDateStr(ContactHelper
						.getDateInMMDDYYYYFormat(eAddressVO.getEndDate()));
				eAddressVO.setStatusStr(getDescriptionFromVV(eAddressVO
						.getStatus(), getCommonEntityDataBean()
						.getStatusEaddList()));
				if (eAddressVO.getUsageTypeCode() != null
						&& !eAddressVO.getUsageTypeCode().equals(
								ProgramConstants.EMPTY_STRING))
					eAddressVO.setUsageTypeCodeStr(getDescriptionFromVV(
							eAddressVO.getUsageTypeCode(),
							getCommonEntityDataBean().getUsageList()));
				else
					eAddressVO
							.setUsageTypeCodeStr(ProgramConstants.EMPTY_STRING);
				if (eAddressVO.isBouncedAddress()) {
					eAddressVO.setBouncedAddressStr(ProgramConstants.YES);
				} else {
					eAddressVO.setBouncedAddressStr(ProgramConstants.NO);
				}
				eAddressList = (ArrayList) commonEntityVO.getEaddressVOList();
				if (eAddressList == null) {
					eAddressList = new ArrayList();
				}
				logger.info("the value of for isEditEaddress()"
						+ commonEntityDataBean.isEditEAddress());
				if (commonEntityDataBean.isEditEAddress()) {
					int index = Integer.parseInt(commonEntityDataBean
							.getEaddressVOIndex());
					EAddressVO oldEAddressVO = (EAddressVO) eAddressList
							.get(index);
					if (!isDuplicateEAddress(eAddressVO, oldEAddressVO)) {
						logger.info("address modified------");
						eAddressVO.setEAddressSK(null);
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, -1);
						logger.info("Done Date Addition");
						Date newDate = cal.getTime();
						oldEAddressVO.setEndDate(ContactHelper
								.dateConverter(newDate));
						logger.info("Done Date Addition New"
								+ oldEAddressVO.getEndDate());
						oldEAddressVO.setEndDateStr(ContactHelper
								.getDateInMMDDYYYYFormat(newDate));
						if (oldEAddressVO.getEndDate() != null
								&& oldEAddressVO.getEndDate().trim().length() > 0) {

							//if
							// (oldEAddressVO.getEndDate().compareTo(oldEAddressVO.getBeginDate())
							// > 0)

							if (ContactHelper.dateConverter(
									oldEAddressVO.getEndDate()).compareTo(
									ContactHelper.dateConverter(oldEAddressVO
											.getBeginDate())) < 0) {

								logger
										.info("Inside save eaddress date check error**************");
								commonEntityValidator
										.setErrorMessage(
												ProgramConstants.END_DATE_LESSTHAN_BEGINDATE,
												new Object[] {},
												ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
												"edate");
								commonEntityDataBean
										.setCommonEAddressFlag(false);
								return ContactManagementConstants.RENDER_SUCCESS;
							}

						}
						commonEntityVO.getEaddressVOList().set(index,
								oldEAddressVO);
						commonEntityVO.getEaddressVOList().add(eAddressVO);
						commonEntityDataBean.setEaddrSaveMsg(true);
					} else {
						commonEntityVO.getEaddressVOList().set(index,
								eAddressVO);
					}
				} else {
					//for fixing defect ESPRD00655062
					eAddressVO.setEAddressSK(null);
					List eAddressLis = commonEntityVO.getEaddressVOList();
					
					//if condition added below for fixinf the defect ESPRD00783819 on 03/05/2012
					
					if(eAddressLis != null)
					{
					Iterator addressIterator = eAddressLis.iterator();
					boolean isExist = false;
					while (addressIterator.hasNext()) {
						EAddressVO oldEAddressVO = (EAddressVO) addressIterator
								.next();
						if (isDuplicateEAddress(eAddressVO, oldEAddressVO)) {
							isExist = true;
							break;
						}
					}
					if (!isExist) {
						commonEntityVO.getEaddressVOList().add(eAddressVO);
						commonEntityDataBean.setEaddrSaveMsg(true);
					} else {
						commonEntityValidator.setErrorMessage(
								ProgramConstants.EADDRESS_DUPLICATE,
								new Object[] {},
								ProgramConstants.EADDRESS_PROPERTIES, null);
						commonEntityDataBean.setEaddrSaveMsg(false);
						return ContactManagementConstants.RENDER_SUCCESS;
					}
					}
					
					//else block added below for fixing the defect ESPRD00783819 on 03/05/2012
					
					else
					{
						eAddressList.add(eAddressVO);
						commonEntityDataBean.setEaddrSaveMsg(true);
					}
					//commented for fixing defect ESPRD00655062
					/*
					 * eAddressVO.setEAddressSK(null);
					 * commonEntityVO.getEaddressVOList().add(eAddressVO);
					 * commonEntityDataBean.setEaddrSaveMsg(true);
					 */
				}
				commonEntityVO.setEaddressVOList(eAddressList);
				commonEntityDataBean.setCommonEntityVO(commonEntityVO);
				commonEntityDataBean.setEaddressSaved(true);
				commonEntityDataBean.setViewingSaved(false);
				commonEntityDataBean.setSavedEndDateGreater(false);
				commonEntityDataBean.getCommonEntityVO().setEaddressVO(
						new EAddressVO());
				commonEntityDataBean.setEaddressRendered(false);
				commonEntityDataBean.setAddeaddressVoidIndicatorRender(true);
				createValidValues();
				commonEntityDataBean.setViewingSaved(false);
				commonEntityDataBean.setEditEAddress(false);
				commonEntityDataBean.setEaddressVOIndex(null);
				commonEntityDataBean.setVoidIndicatorForRender(false);
				commonEntityDataBean.setDisableEAddress(false);
				commonEntityDataBean.setSmallSaveFlag("true");
				commonEntityDataBean.setCommonEAddressSaved(true);
			}
		}
		//		To Fix Defect ESPRD00576234
		commonEntityDataBean.setPhoneSaveMsg(false);
		commonEntityDataBean.setCurrentPageId("commonEAddressfocusid");
		logger.debug("Exit " + methodName);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method has the logic to clear all the UI components of EAddress if
	 * the EAddress Data has newly entered value, else it will restore the old
	 * saved value of this particular EAddress.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String resetEAddress() {

		logger.info("Entered into resetEAddress()");
		final String methodName = "resetEAddress()";

		EAddressVO eAddressVOToset = null;
		int index;
		String indexStr = null;
		Date sysDate = null;
		ArrayList eaddressList = null;
		EAddressVO eaddressVO = null;
		indexStr = commonEntityDataBean.getEaddressVOIndex();
		if (indexStr != null && !indexStr.equals("")) {

			index = Integer.parseInt(indexStr);
			logger.debug("indexStr:::::::::" + index);
			commonEntityDataBean.setSavedEndDateGreater(false);
			eaddressList = (ArrayList) commonEntityDataBean.getCommonEntityVO()
					.getEaddressVOList();
			//eaddressVO = (EAddressVO) eaddressList.get(index);
			eaddressVO = commonEntityDataBean.getTempEAddressVO();
			sysDate = new Date();
			if (eaddressVO != null) {
				if (sysDate.compareTo(ContactHelper.dateConverter(eaddressVO
						.getEndDate())) > 0) {
					commonEntityDataBean.setSavedEndDateGreater(true);
				}
			}
			EAddressVO tempEAddressVO = new EAddressVO();

			//---FindBug issue Resolved---
			if (eaddressVO != null) {

				tempEAddressVO.setAddedAuditTimeStamp(eaddressVO
						.getAddedAuditTimeStamp());
				tempEAddressVO.setAddedAuditUserID(eaddressVO
						.getAddedAuditUserID());
				tempEAddressVO
						.setAuditTimeStamp(eaddressVO.getAuditTimeStamp());
				tempEAddressVO.setAuditUserID(eaddressVO.getAuditUserID());
				tempEAddressVO.setBeginDate(eaddressVO.getBeginDate());
				tempEAddressVO.setBeginDateStr(eaddressVO.getBeginDateStr());
				tempEAddressVO.setBouncedAddress(eaddressVO.isBouncedAddress());
				tempEAddressVO.setBouncedAddressStr(eaddressVO
						.getBouncedAddressStr());
				tempEAddressVO.setCommonEntityType(eaddressVO
						.getCommonEntityType());
				tempEAddressVO.setEaddress(eaddressVO.getEaddress());
				tempEAddressVO.setEAddressSK(eaddressVO.getEAddressSK());
				tempEAddressVO.setEndDate(eaddressVO.getEndDate());
				tempEAddressVO.setEndDateStr(eaddressVO.getEndDateStr());
				tempEAddressVO.setEntityType(eaddressVO.getEntityType());
				tempEAddressVO.setEntityTypeCodeList(eaddressVO
						.getEntityTypeCodeList());
				tempEAddressVO.setEAddressUsageSequenceNumber(eaddressVO
						.getEAddressUsageSequenceNumber());
				tempEAddressVO.setEAddressUsageSK(eaddressVO
						.getEAddressUsageSK());
				tempEAddressVO.setEAddressUsageVersionNo(eaddressVO
						.getEAddressUsageVersionNo());
				tempEAddressVO.setExistingUsageTypeCode(eaddressVO
						.getExistingUsageTypeCode());
				tempEAddressVO.setSignificance(eaddressVO.getSignificance());
				tempEAddressVO.setSignificanceList(eaddressVO
						.getSignificanceList());
				tempEAddressVO.setStatus(eaddressVO.getStatus());
				tempEAddressVO.setUsageTypeCodeStr(eaddressVO
						.getUsageTypeCodeStr());
				tempEAddressVO.setStatusStr(eaddressVO.getStatusStr());
				tempEAddressVO.setStatusList(eaddressVO.getStatusList());
				tempEAddressVO.setUsageList(eaddressVO.getUsageList());
				tempEAddressVO.setUsageTypeCode(eaddressVO.getUsageTypeCode());
				tempEAddressVO.setVersionNo(eaddressVO.getVersionNo());
				tempEAddressVO.setVoidIndicator(eaddressVO.getVoidIndicator());
			}
			eaddressList.set(index, tempEAddressVO);
			//commonEntityDataBean.getCommonEntityVO().setEaddressVOList(eaddressList);
			commonEntityDataBean.getCommonEntityVO().setEaddressVO(
					tempEAddressVO);
		} else {

			eAddressVOToset = clearEAddressVO(commonEntityDataBean
					.getCommonEntityVO().getEaddressVO());
			commonEntityDataBean.getCommonEntityVO().setEaddressVO(
					eAddressVOToset);
		}

		commonEntityDataBean.setEaddressRendered(true);

		/*
		 * if (commonEntityDataBean.getCommonEntityVO().getEaddressVO()
		 * .getVoidIndicator().equalsIgnoreCase(BenefitPlanConstants.Y)) {
		 * commonEntityDataBean.setVoidIndicatorForRender(true); } else {
		 * commonEntityDataBean.setVoidIndicatorForRender(false); }
		 */
		logger.debug("Exit " + methodName);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method will clear and close the EAddress entry table.
	 */
	public String cancelEAddress() {

		final String methodName = "cancelEAddress()";
		logger.debug("CommonContactControllerBean::cancelContact()");
		resetEAddress();
		//to fix DEFECT_ESPRD00677540
		//EAddressVO eAddressVO = null;
		commonEntityDataBean.getCommonEntityVO()
				.setEaddressVO(new EAddressVO());
		commonEntityDataBean.setEditEAddress(false);
		commonEntityDataBean.setEaddressRendered(false);
		/* Defect Fixed */
		commonEntityDataBean.setEditEaddressflag(false);
		//To Fix Defect ESPRD00576234
		commonEntityDataBean.setCurrentPageId("commonEAddressfocusid");
		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This method will clear and close the EAddress entry table.
	 */
	public String editEAddress() {

		final String methodName = "editEAddress()";
		if (logger.isDebugEnabled()) {
			logger.debug("Entered " + methodName);
		}
		ArrayList eaddressList = null;
		EAddressVO eaddressVO = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map
				.get(ContactManagementConstants.INDEX_CODE);
		if (indexCode == null) {
			indexCode = "0";
		}
		int index = Integer.parseInt(indexCode);
		commonEntityDataBean.setEaddressVOIndex(indexCode);
		commonEntityDataBean.setSavedEndDateGreater(false);
		commonEntityDataBean.setEaddressVOIndex(index + "");
		eaddressList = (ArrayList) commonEntityDataBean.getCommonEntityVO()
				.getEaddressVOList();
		eaddressVO = (EAddressVO) eaddressList.get(index);

		EAddressVO tempEAddressVO = new EAddressVO();
		if (eaddressVO != null) {
			UIComponent component = ContactManagementHelper
					.findComponentInRoot("eaddressAuditId");
			if (component != null) {
				AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
				auditHistoryTable
						.setAuditLogControllerBean(new AuditLogControllerBean());

			}
			tempEAddressVO.setAuditKeyList(eaddressVO.getAuditKeyList());
			tempEAddressVO.setAddedAuditTimeStamp(eaddressVO
					.getAddedAuditTimeStamp());
			tempEAddressVO
					.setAddedAuditUserID(eaddressVO.getAddedAuditUserID());
			tempEAddressVO.setAuditTimeStamp(eaddressVO.getAuditTimeStamp());
			tempEAddressVO.setAuditUserID(eaddressVO.getAuditUserID());
			tempEAddressVO.setBeginDate(eaddressVO.getBeginDate());
			tempEAddressVO.setBeginDateStr(eaddressVO.getBeginDateStr());
			tempEAddressVO.setBouncedAddress(eaddressVO.isBouncedAddress());
			tempEAddressVO.setBouncedAddressStr(eaddressVO
					.getBouncedAddressStr());
			tempEAddressVO
					.setCommonEntityType(eaddressVO.getCommonEntityType());
			tempEAddressVO.setEaddress(eaddressVO.getEaddress());
			tempEAddressVO.setEAddressSK(eaddressVO.getEAddressSK());
			tempEAddressVO.setEndDate(eaddressVO.getEndDate());
			tempEAddressVO.setEndDateStr(eaddressVO.getEndDateStr());
			tempEAddressVO.setEntityType(eaddressVO.getEntityType());
			tempEAddressVO.setEntityTypeCodeList(eaddressVO
					.getEntityTypeCodeList());
			tempEAddressVO.setEAddressUsageSequenceNumber(eaddressVO
					.getEAddressUsageSequenceNumber());
			tempEAddressVO.setEAddressUsageSK(eaddressVO.getEAddressUsageSK());
			tempEAddressVO.setEAddressUsageVersionNo(eaddressVO
					.getEAddressUsageVersionNo());
			tempEAddressVO.setExistingUsageTypeCode(eaddressVO
					.getExistingUsageTypeCode());
			tempEAddressVO.setSignificance(eaddressVO.getSignificance());
			tempEAddressVO
					.setSignificanceList(eaddressVO.getSignificanceList());
			tempEAddressVO.setStatus(eaddressVO.getStatus());
			tempEAddressVO
					.setUsageTypeCodeStr(eaddressVO.getUsageTypeCodeStr());
			tempEAddressVO.setStatusStr(eaddressVO.getStatusStr());
			tempEAddressVO.setStatusList(eaddressVO.getStatusList());
			tempEAddressVO.setUsageList(eaddressVO.getUsageList());
			tempEAddressVO.setUsageTypeCode(eaddressVO.getUsageTypeCode());
			tempEAddressVO.setVersionNo(eaddressVO.getVersionNo());
			tempEAddressVO.setVoidIndicator(eaddressVO.getVoidIndicator());
			commonEntityDataBean.setTempEAddressVO(eaddressVO);
			//			To Fix Defect ESPRD00576234
			commonEntityDataBean.setCurrentPageId("commonEAddressfocusid");
		}

		//commented for defect ESPRD00502270

		/*
		 * Date sysDate = new Date();
		 * commonEntityDataBean.setDisableEAddress(false); if
		 * (sysDate.after(ContactHelper.dateConverter(eaddressVO.getEndDate()))) {
		 * commonEntityDataBean.setDisableEAddress(true); }
		 */

		createValidValues();
		EAddressVO addressVOVV = commonEntityDataBean.getCommonEntityVO()
				.getEaddressVO();

		if (addressVOVV != null) {
			tempEAddressVO.setSignificanceList(addressVOVV
					.getSignificanceList());
			tempEAddressVO.setUsageList(addressVOVV.getUsageList());
			tempEAddressVO.setStatusList(addressVOVV.getStatusList());
			commonEntityDataBean.setVoidIndicatorForRender(eaddressVO
					.getVoidIndicator().equalsIgnoreCase(
							ContactManagementConstants.Y) ? true : false);
		}
		commonEntityDataBean.getCommonEntityVO().setEaddressVO(tempEAddressVO);

		commonEntityDataBean.setEaddressRendered(true);
		commonEntityDataBean.setViewingSaved(true);
		commonEntityDataBean.setEditEAddress(true);
		commonEntityDataBean.setAddeaddressVoidIndicatorRender(false);
		commonEntityDataBean.setEaddrSaveMsg(false);
		commonEntityDataBean.setEAddressFlag(false);
		commonEntityDataBean.setEditEaddressflag(true);

		logger.debug("Exit " + methodName);
		showEAddressAuditHistory();
		commonEntityDataBean.setCurrentPageId("commonEAddressfocusid");
		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This method has the logic to display the table to enter new EAddress
	 * information.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String addEAddress() {

		//EAddressVO eAddressVO = null;
		final String methodName = "addEAddress()";

		if (logger.isDebugEnabled()) {
			logger.debug("Entered " + methodName);
		}

		if (commonEntityDataBean != null) {
			createValidValues();
			commonEntityDataBean.setViewingSaved(false);
			commonEntityDataBean.setEditEAddress(false);
			commonEntityDataBean.setEaddressRendered(true);
			commonEntityDataBean.setEaddressVOIndex(null);
			commonEntityDataBean.setVoidIndicatorForRender(false);

			/*
			 * eAddressVO =
			 * commonEntityDataBean.getCommonEntityVO().getEaddressVO(); if
			 * (eAddressVO != null) { eAddressVO.setBeginDate(null);
			 * eAddressVO.setEndDate(null); eAddressVO.setEaddress(null);
			 * eAddressVO.setSignificance(null); //
			 * eAddressVO.setEntityType(null);
			 * eAddressVO.setUsageTypeCode(null);
			 * eAddressVO.setBouncedAddress(false);
			 * eAddressVO.setVoidIndicator(null);
			 * eAddressVO.setEAddressSK(null); eAddressVO.setStatus(null); }
			 */
			commonEntityDataBean.getCommonEntityVO().setEaddressVO(
					new EAddressVO());
			commonEntityDataBean.getCommonEntityVO().getEaddressVO()
					.setBouncedAddress(false);
			commonEntityDataBean.getCommonEntityVO().getEaddressVO()
					.setVoidIndicator(ContactManagementConstants.N);
			commonEntityDataBean.setEaddressRendered(true);
			commonEntityDataBean.setAddeaddressVoidIndicatorRender(true);
			commonEntityDataBean.setDisableEAddress(false);
			commonEntityDataBean.setEaddrSaveMsg(false);
			commonEntityDataBean.setEditEaddressflag(false);
			/* Big Save and Little Save */
			commonEntityDataBean.setCommonEAddressFlag(false);
			/* End Big Save and Little Save */

			commonEntityDataBean.setEAddressFlag(true);
			//		To Fix Defect ESPRD00576234
			commonEntityDataBean.setCurrentPageId("commonEAddressfocusid");

		} else {
			logger.debug("commonEntityDataBean  Is null");
		}
		logger.debug("Exit " + methodName);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is for sorting.
	 * 
	 * @param event
	 *            This will take actionEvent from the context
	 */
	public String sort(ActionEvent event) {
		final String methodName = "sort(ActionEvent event)";
		logger.debug("Entered " + methodName);
		ArrayList eaddressVOList = (ArrayList) commonEntityDataBean
				.getCommonEntityVO().getEaddressVOList();
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);
		logger.debug("sortOrder:- " + sortOrder);
		commonEntityDataBean.setImageRender(event.getComponent().getId());
		
		//by default sort should display from 1st page
		commonEntityDataBean.setStartIndexForEAdrs(0);
		
		Comparator comparator = new Comparator() {

			public int compare(Object obj1, Object obj2) {
				EAddressVO eaddressVO1 = (EAddressVO) obj1;
				EAddressVO eaddressVO2 = (EAddressVO) obj2;
				boolean ascending = false;
				String asc = "asc";
				int returnValue = 0;
				if (asc.equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return returnValue;
				}
				String EAddress = "E-Address";
				if (EAddress.equals(sortColumn)) {

					return ascending ? (eaddressVO1.getEaddress()
							.compareTo(eaddressVO2.getEaddress()))
							: (eaddressVO2.getEaddress().compareTo(eaddressVO1
									.getEaddress()));
				}
				String beginDate = "Begin Date";
				if (beginDate.equals(sortColumn)) {

					return ascending ? (ContactHelper.dateConverter(eaddressVO1
							.getBeginDateStr()).compareTo(ContactHelper
							.dateConverter(eaddressVO2.getBeginDateStr())))
							: (ContactHelper.dateConverter(eaddressVO2
									.getBeginDateStr()).compareTo(ContactHelper
									.dateConverter(eaddressVO1
											.getBeginDateStr())));
				}
				String endDate = "End Date";
				if (endDate.equals(sortColumn)) {

					return ascending ? (ContactHelper.dateConverter(eaddressVO1
							.getEndDateStr()).compareTo(ContactHelper
							.dateConverter(eaddressVO2.getEndDateStr())))
							: (ContactHelper.dateConverter(eaddressVO2
									.getEndDateStr())
									.compareTo(ContactHelper
											.dateConverter(eaddressVO1
													.getEndDateStr())));
				}

				/*
				 * if ("Void".equals(sortColumn)) {
				 * 
				 * return ascending ? (eaddressVO1.getVoidIndicator()
				 * .compareTo(eaddressVO2.getVoidIndicator())) :
				 * (eaddressVO2.getVoidIndicator()
				 * .compareTo(eaddressVO1.getVoidIndicator())); }
				 */
				String BouncedAddress = "Bounced Address";
				if ("Bounced Address".equals(sortColumn)) {
					return ascending ? (eaddressVO1.getBouncedAddressStr()
							.compareTo(eaddressVO2.getBouncedAddressStr()))
							: (eaddressVO2.getBouncedAddressStr()
									.compareTo(eaddressVO1
											.getBouncedAddressStr()));
				}

				String Status = "Status";
				if ("Status".equals(sortColumn)) {
					return ascending ? (eaddressVO1.getStatusStr()
							.compareTo(eaddressVO2.getStatusStr()))
							: (eaddressVO2.getStatusStr().compareTo(eaddressVO1
									.getStatusStr()));
				}

				String EAddressType = "E-Address Type";
				if ("E-Address Type".equals(sortColumn)) {
					return ascending ? (eaddressVO1.getUsageTypeCodeStr()
							.compareTo(eaddressVO2.getUsageTypeCodeStr()))
							: (eaddressVO2.getUsageTypeCodeStr()
									.compareTo(eaddressVO1
											.getUsageTypeCodeStr()));
				}
				/*
				 * if ("Bounced Address".equals(sortColumn)) { return ascending ?
				 * (eaddressVO1.getBouncedAddress()
				 * .compareTo(eaddressVO2.getBouncedAddress())) :
				 * (eaddressVO2.getBouncedAddress()
				 * .compareTo(eaddressVO1.getBouncedAddress())); }
				 */
				/*
				 * if ("Significance".equals(sortColumn)) {
				 * 
				 * return ascending ? (eaddressVO1.getSignificance()
				 * .compareTo(eaddressVO2.getSignificance())) :
				 * (eaddressVO2.getSignificance()
				 * .compareTo(eaddressVO1.getSignificance())); } if ("Usage
				 * Type".equals(sortColumn)) {
				 * 
				 * return ascending ? (eaddressVO1.getUsageTypeCode()
				 * .compareTo(eaddressVO2.getUsageTypeCode())) :
				 * (eaddressVO2.getUsageTypeCode()
				 * .compareTo(eaddressVO1.getUsageTypeCode())); }
				 */

				return returnValue;
			}

		};
		Collections.sort(eaddressVOList, comparator);
		logger.debug("Exit " + methodName);
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

	/**
	 * Newly Added This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermission() {
		String userPermission = "";
		String userid = userID();
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
		logger.debug("userPermission in Eaddress -->" + userPermission);

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

	/**
	 * This method contains logic to change the enddate if any exists with same
	 * EntityType significance and usageCode.
	 * 
	 * @param eAddressVOList
	 *            This holds list values
	 * @param eAddressVO
	 *            This will hold eAddress ValueObjects
	 * @return ArrayList This will return eAddressVOList
	 */
	private ArrayList addEAddressVOToList(ArrayList eAddressVOList,
			EAddressVO eAddressVO) {
		EAddressVO listEAddressVO = null;
		boolean isequal = false;
		Iterator itr = eAddressVOList.iterator();
		while (itr.hasNext()) {
			listEAddressVO = (EAddressVO) itr.next();
			if (isequal) {
				long timeInMillis = ContactHelper.dateConverter(
						eAddressVO.getBeginDate()).getTime();
				Calendar cal = Calendar.getInstance();
				Calendar cal1 = Calendar.getInstance();
				cal.setTimeInMillis(timeInMillis);
				//now make use of calendar.add(int fieldId, int value)
				// to manipulate your date.
				if (!commonEntityDataBean.isEditEAddress())

				{
					cal.add(Calendar.DATE, -1);
					cal1.add(Calendar.DATE, 1);
					logger.info("Done Date Addition");
				}
				// subtract a day
				Date newDate = cal.getTime();
				listEAddressVO.setEndDate(ContactHelper.dateConverter(newDate));
				listEAddressVO.setEndDateStr(ContactHelper
						.getDateInMMDDYYYYFormat(newDate));

			}
		}
		eAddressVOList.add(eAddressVO);
		return eAddressVOList;
	}

	/**
	 * This method will construct new EAddressVO with list that are fetched from
	 * valid values and returns new constructed VO.
	 * 
	 * @param eaddressVO
	 *            This will contain List of eAddressVo
	 * @return newEAddressVO This will contain List of newEAddressVO
	 */
	private EAddressVO clearEAddressVO(EAddressVO eaddressVO) {
		EAddressVO newEAddressVO = new EAddressVO();
		/**
		 * commented as part of ES2 change
		 */
		// newEAddressVO.setEntityTypeCodeList(eaddressVO.getEntityTypeCodeList());
		newEAddressVO.setSignificanceList(eaddressVO.getSignificanceList());
		newEAddressVO.setUsageList(eaddressVO.getUsageList());
		newEAddressVO.setStatusList(eaddressVO.getStatusList());
		return newEAddressVO;
	}

	/**
	 * Thiw will return valid Values.
	 */
	public void createValidValues() {

		InputCriteria inputCriteria = null;

		List list = null;

		//---FindBug issue resolved---

		/* HashMap map = new HashMap(); */
		HashMap map = null;

		String select = "Please Select One";

		String selectIndex = "";

		ReferenceDataSearchVO referenceDataSearch = null;

		ReferenceServiceDelegate referenceServiceDelegate = null;

		ReferenceDataListVO referenceDataListVO = null;

		//filling the drop down of claim type code

		inputCriteria = new InputCriteria();

		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);

		inputCriteria

		.setElementName(ReferenceServiceDataConstants.G_E_ADR_TY_SIG_CD);

		list = new ArrayList();

		list.add(inputCriteria);

		inputCriteria = new InputCriteria();

		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);

		inputCriteria

		.setElementName(ReferenceServiceDataConstants.G_E_ADR_USG_TY_CD);

		list.add(inputCriteria);

		inputCriteria = new InputCriteria();

		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);

		inputCriteria

		.setElementName(ReferenceServiceDataConstants.G_CMN_ENTY_TY_CD);

		list.add(inputCriteria);
		//      added for status list for E-Address common entity

		inputCriteria = new InputCriteria();

		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);

		inputCriteria

		.setElementName(ReferenceServiceDataConstants.G_E_ADR_USG_TY_CD);

		list.add(inputCriteria);

		inputCriteria = new InputCriteria();

		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);

		inputCriteria

		.setElementName(ReferenceServiceDataConstants.G_EADR_USG_STAT_CD);

		list.add(inputCriteria);

		referenceDataSearch = new ReferenceDataSearchVO();

		referenceDataSearch.setInputList(list);

		referenceServiceDelegate = new ReferenceServiceDelegate();

		//                               Pass the list to the delegate
		//---Find Bug issues Resolved---
		//referenceDataListVO = new ReferenceDataListVO();

		try

		{
			//FindBugs Issue Resolved
			//ArrayList sigList = new ArrayList();
			//ArrayList usageList = new ArrayList();
			//ArrayList statusList = new ArrayList();
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			//for displaying retrieved values

			map = referenceDataListVO.getResponseMap();

			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ ReferenceServiceDataConstants.G_E_ADR_TY_SIG_CD);

			int sigSize = list.size();
			commonEntityDataBean.getSigList().clear();
			commonEntityDataBean.getSigList().add(
					new SelectItem(selectIndex, select));
			for (int i = 0; i < sigSize; i++)

			{
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				commonEntityDataBean.getSigList().add(
						new SelectItem(refVo.getValidValueCode(), refVo
								.getValidValueCode()
								+ "-" + refVo.getShortDescription()));
			}

			/*
			 * commonEntityDataBean.getCommonEntityVO().getEaddressVO()
			 * .setSignificanceList(sigList);
			 */

			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ ReferenceServiceDataConstants.G_E_ADR_USG_TY_CD);
			int usgSize = list.size();
			commonEntityDataBean.getUsageList().clear();
			commonEntityDataBean.getUsageList().add(
					new SelectItem(selectIndex, select));
			for (int i = 0; i < usgSize; i++)

			{

				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				commonEntityDataBean.getUsageList().add(
						new SelectItem(refVo.getValidValueCode(), refVo
								.getValidValueCode()
								+ "-" + refVo.getShortDescription()));

			}

			/*
			 * commonEntityDataBean.getCommonEntityVO().getEaddressVO()
			 * .setUsageList(usageList);
			 */

			/*
			 * list = (List) map.get(FunctionalAreaConstants.GENERAL + "#" +
			 * ReferenceServiceDataConstants.G_CMN_ENTY_TY_CD); for (int i = 0;
			 * i < list.size(); i++) { ReferenceServiceVO refVo =
			 * (ReferenceServiceVO) list.get(i); entityTypeList.add( new
			 * SelectItem(refVo.getValidValueCode(), refVo .getValidValueCode() +
			 * "-" + refVo.getShortDescription())); } logger.debug("entered into
			 * create valid values of eaddress controller bean...11");
			 * commonEntityDataBean.getCommonEntityVO().getEaddressVO()
			 * .setEntityTypeCodeList(entityTypeList);
			 */
			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"

			+ ReferenceServiceDataConstants.G_EADR_USG_STAT_CD);
			int size = list.size();
			commonEntityDataBean.getStatusEaddList().clear();
			commonEntityDataBean.getStatusEaddList().add(
					new SelectItem(selectIndex, select));
			for (int i = 0; i < size; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				commonEntityDataBean.getStatusEaddList().add(
						new SelectItem(refVo.getValidValueCode(), refVo
								.getValidValueCode()
								+ "-" + refVo.getShortDescription()));
			}
			/*
			 * commonEntityDataBean.getCommonEntityVO().getEaddressVO()
			 * .setStatusList(statusList);
			 */
		} catch (ReferenceServiceRequestException e) {
			logger.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * methods for rendering void indicator.
	 * 
	 * @param event :
	 *            ValueChangeEvent object
	 */
	public void showVoidIndicator(ValueChangeEvent event) {
		long entryTime = System.currentTimeMillis();
		commonEntityDataBean.setVoidIndicatorForRender(false);
		if (event != null && event.getNewValue() != null
				&& event.getNewValue().equals(ContactManagementConstants.Y)) {
			commonEntityDataBean.setVoidIndicatorForRender(true);
		}
		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("EAddressControllerBean" + "#" + "showVoidIndicator"
					+ "#" + (exitTime - entryTime));
		}
	}

	/**
	 * This method is used to show audit child history for Provider Type
	 * details.
	 * 
	 * @return ProgramConstants.RETURN_SUCCESS : String. Navigation message
	 */

	/**
	 * This method is used to show audit child history for Provider Type
	 * details.
	 * 
	 * @return ProgramConstants.RETURN_SUCCESS : String. Navigation message
	 */
	public String showEAddressAuditHistory() {
		GlobalAuditsDelegate audit;
		try {
			logger.info("showEAddressAuditHistory");
			audit = new GlobalAuditsDelegate();
			ContactHelper contactHelper = new ContactHelper();
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();
			EAddressVO eaddressVO = commonEntityDataBean.getCommonEntityVO()
					.getEaddressVO();

			EAddress eaddress = contactHelper
					.getEAddressFromEAddressVO(eaddressVO);

			logger.info("eaddress:" + eaddress);
			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(eaddress, 0,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
			logger.info("EmailAdd List Size:"
					+ enterpriseResultVO.getSearchResults().size());
			commonEntityDataBean
					.setAuditEAddressAuditHistList(enterpriseResultVO
							.getSearchResults());
			commonEntityDataBean.setAuditEAddressHistRndr(false);
		} catch (Exception e) {
			logger.info("EAddress - Error in show Parent audit history : "
					+ e.toString());
			logger.debug("Error in show Parent audit history  " + e);
		}

		return "";
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

	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}

	public String showColumnChange() {
		//FindBugs Issue Resolved
		//long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
			getCommonEntityDataBean().setAuditEAddressHistRndr(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getCommonEntityDataBean().setSelectedEAdrAuditLog(auditLog);
		} catch (Exception e) {
			logger.debug("Error in show column change  " + e);
		}
		return ProgramConstants.RETURN_SUCCESS;
	}

	public String closeColumnChange() {
		commonEntityDataBean.setAuditEAddressHistRndr(false);
		return ProgramConstants.RETURN_SUCCESS;
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
		//FindBugs Issue Resolved
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
		logger.info("\n\nprevious\n\n");
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

			EAddressVO eaddressVO = commonEntityDataBean.getCommonEntityVO()
					.getEaddressVO();

			EAddress eaddress = contactHelper
					.getEAddressFromEAddressVO(eaddressVO);

			EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(eaddress, (currentPage - 1)
							* ProgramNumberConstants.INT_TEN,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

			logger.info("Contact List Size:"
					+ enterpriseResultVO.getSearchResults().size());
			commonEntityDataBean
					.setAuditEAddressAuditHistList(enterpriseResultVO
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
		String desc = ProgramConstants.EMPTY_STRING;
		String valueStr = ProgramConstants.EMPTY_STRING;
		//---FindBug issue Resolved---
		/*
		 * int size = vvList.size(); if (vvList != null && size > 0)
		 */
		if (vvList != null && vvList.size() > 0) {
			int size = vvList.size();
			for (int i = 0; i < size; i++) {
				SelectItem selectItem = (SelectItem) vvList.get(i);
				valueStr = ProgramConstants.EMPTY_STRING;
				if (selectItem != null) {
					valueStr = (String) selectItem.getValue();
					if (valueStr != null && valueStr.equals(code)) {
						desc = selectItem.getLabel();
						break;
					}
				}
			}
		}
		return desc;
	}

}