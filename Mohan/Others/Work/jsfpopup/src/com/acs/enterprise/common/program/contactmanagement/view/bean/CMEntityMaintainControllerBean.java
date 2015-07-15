/*
 * Created on Nov 1, 2007
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.CmnSpecEntyXref;
import com.acs.enterprise.common.program.commonentities.common.domain.SpecificEntity;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonContactControllerBean;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.bean.EAddressControllerBean;
import com.acs.enterprise.common.program.commonentities.view.bean.PhoneControllerBean;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonContactVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.EAddressVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NameVO;
import com.acs.enterprise.common.program.commonentities.view.vo.PhoneVO;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMEntityDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.SpecificEntitySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.SpecificEntitySearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CMEntityDetailVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.helper.ValidatorConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.mmis.provider.common.delegate.ProviderInformationDelegate;
import com.acs.enterprise.mmis.provider.common.domain.Provider;
import com.acs.enterprise.mmis.provider.information.application.exception.ProviderUpdateFailureException;
import com.acs.enterprise.ui.javax.faces.component.addressHistory.AddressHistoryTable;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;

/**
 * This class is used for Add and update the specifice and common entities.
 */
public class CMEntityMaintainControllerBean extends
		EnterpriseBaseControllerBean {
	/**
	 * Generating object of EnterpriseLogger.
	 */
	/*
	 * private transient EnterpriseLogger logger = EnterpriseLogFactory
	 * .getLogger(CMEntityMaintainControllerBean.class);
	 */
	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CMEntityMaintainControllerBean.class);

	/** Creates reference of data Bean */
	private CMEntityMaintainDataBean cmEntityMaintainDataBean = getCmEntityMaintainDataBean();

	/**
	 * This field is used to store whether user has permission.
	 */
	private boolean controlRequired = true; // Newly added

	private AddressHistoryTable addressTable;

	/**
	 * This ecreates constructor of the class.
	 */
	/*
	 * public CMEntityMaintainControllerBean() { super(); logger.debug("inside
	 * CMEntityMaintainControllerBean constructor"); getUserPermission(); //
	 * Newly added }
	 */

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
	 * @param addressTable
	 *            The addressTable to set.
	 */
	public void setAddressTable(AddressHistoryTable addressTable) {
		this.addressTable = addressTable;
	}

	/**
	 * @return Returns the addressTable.
	 */
	public AddressHistoryTable getAddressTable() {
		return addressTable;
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
	public String getUserID() {
		String userID = null;
		/*
		 * try {
		 * 
		 * HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest();
		 * HttpServletResponse renderresponse = null; EnterpriseUserProfile
		 * enterpriseUserProfile = getUserData( renderrequest, renderresponse);
		 * 
		 * if (enterpriseUserProfile != null) { userID =
		 * enterpriseUserProfile.getUserId(); } HttpSession session =
		 * (HttpSession) FacesContext
		 * .getCurrentInstance().getExternalContext().getSession(true);
		 * session.setAttribute("LOGGED_IN_USERID", userID); } catch (Exception
		 * e) { e.getCause(); e.getMessage(); logger.debug("Exception has
		 * come:"); }
		 */
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest) facesContext
				.getExternalContext().getRequest();
		userID = getLoggedInUserID(portletRequest);

		return userID;
	}

	/**
	 * Gets reference of CMEntityDataBean.
	 * 
	 * @author Wipro.
	 * @return CMEntityDataBean
	 */
	public CMEntityMaintainDataBean getCmEntityMaintainDataBean() {

		

		FacesContext fc = FacesContext.getCurrentInstance();
		CMEntityMaintainDataBean cmEntityMaintainDataBean = (CMEntityMaintainDataBean) fc
				.getApplication().createValueBinding(
						"#{" +ContactManagementConstants.CMMAINTAINENTITY_BEAN_NAME + "}")
				.getValue(fc);

		return cmEntityMaintainDataBean;
	}

	/**
	 * This Method Navigates to Differnt Entity pages Based on the Entity
	 * selected in Maintain entity page .
	 */

	public String showRespectiveEntity() {
		

		String newValue = (String) cmEntityMaintainDataBean
				.getCmEnityDetailVO().getEntityType();

		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_UNPROV, newValue)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.TRUE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
			cmEntityMaintainDataBean.setCursorFocusId("entTypUNProv");
			cmEntityMaintainDataBean
					.getCmEnityDetailVO()
					.setOrganizationType(
							ContactManagementConstants.DFLT_ORG_TYPE_FOR_MAINTAIN_ENTITY);
		}

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_DO, newValue)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.TRUE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
			cmEntityMaintainDataBean.setCursorFocusId("entTypeDO");

		} else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_CT, newValue)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.TRUE);
			cmEntityMaintainDataBean.setCursorFocusId("entTypeCT");

		} else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_BO, newValue)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.TRUE);
			cmEntityMaintainDataBean.setCursorFocusId("entTypeBO");
		}
		// Below else if is added for CR : ESPRD00808886
		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_SC, newValue)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.TRUE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			cmEntityMaintainDataBean.setCursorFocusId("entTypeDC");
		}

		else {
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.TRUE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
			cmEntityMaintainDataBean.setCursorFocusId("entTydropdownMem");
		}

		FacesContext.getCurrentInstance().renderResponse();
		cmEntityMaintainDataBean.getCmEnityDetailVO().setEntityType(newValue);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method populate CountyName for selected CountyCode
	 */
	public String getCountryName() {
		
		String newValue = (String) cmEntityMaintainDataBean
				.getCmEnityDetailVO().getCountyCode();
		if(logger.isDebugEnabled())
		{
			logger.debug("newValue" + newValue);
		}
		FacesContext.getCurrentInstance().renderResponse();
		cmEntityMaintainDataBean.getCmEnityDetailVO().setCountyCode(newValue);

		String countyName = ReferenceServiceDelegate
				.getReferenceSearchLongDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_COUNTY_CD, new Long(
								1007), newValue);
		StringTokenizer county = new StringTokenizer(countyName, "-");
		while (county.hasMoreTokens()) {
			String countryname = county.nextToken();
			cmEntityMaintainDataBean.getCmEnityDetailVO().setCountyName(
					countryname);
		}
		if(logger.isDebugEnabled())
		{
		logger.debug("countyName:::::: "+ cmEntityMaintainDataBean.getCmEnityDetailVO().getCountyName());
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method Returns to Entity search page from Eniy add page
	 */
	public void cancelViewEntity() {
		
		cmEntityMaintainDataBean.setRenderMaintainEntity(Boolean.TRUE);
		cmEntityMaintainDataBean.setRenderViewEntity(Boolean.FALSE);
		cmEntityMaintainDataBean.setRenderEntityTypeDropDown(Boolean.TRUE);

	}

	/**
	 * This method resets the Maintain Entity Page.
	 * 
	 * @return String
	 */
	public String resetUpdateMaintainEntity() {
		
		/** Clears the values in the cmEntityDetailVO */
		String cmEntityID = cmEntityMaintainDataBean.getCmEnityDetailVO()
				.getCmEntityID();
		CMEntityDetailVO cmEntityDetailVO = null;
		cmEntityDetailVO = getSpecificEntityDetails(cmEntityID);
		if (cmEntityDetailVO != null) {

			cmEntityMaintainDataBean.setCmEnityDetailVO(cmEntityDetailVO);
		}

		CommonContactControllerBean commonContactControllerBean = new CommonContactControllerBean();
		commonContactControllerBean.cancelContact();
		//commonContactControllerBean.cancelContactType();
		PhoneControllerBean phoneControllerBean = new PhoneControllerBean();
		phoneControllerBean.cancelPhone();

		addressTable.cancelAddEditAddress();

		EAddressControllerBean eaddContrlBean = new EAddressControllerBean();
		eaddContrlBean.cancelEAddress();
		getCommonEntityDataBean().setEaddrSaveMsg(false);
		getCommonEntityDataBean().setContactSaveMsg(false);
		getCommonEntityDataBean().setPhoneSaveMsg(false);

		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This method resets the Maintain Entity Page.
	 * 
	 * @return String
	 */
	public String resetMaintainEntity() {
		
		/** Clears the values in the cmEntityDetailVO */
		cmEntityMaintainDataBean.setShowValidatedMessage(false);
		cmEntityMaintainDataBean.setCmEnityDetailVO(new CMEntityDetailVO());
		cmEntityMaintainDataBean.setRenderDefnComm(Boolean.TRUE);
		cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
		cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);

		/** Reset Common entity Value */
		ContactHelper.getCommonEntityDataBean().setCommonEntityVO(
				new CommonEntityVO());
		CommonContactControllerBean commonContactControllerBean = new CommonContactControllerBean();
		commonContactControllerBean.cancelContact();
		
		//Below one line code is added for removing the success message after clicking reset - 03/05/2012
		
		getCommonEntityDataBean().setContactSaveMsg(false);
		
		// commonContactControllerBean.cancelContactType();
		PhoneControllerBean phoneControllerBean = new PhoneControllerBean();
		phoneControllerBean.cancelPhone();
		
		//Below one line code is added for removing the success message after clicking reset - 03/05/2012
		
		getCommonEntityDataBean().setPhoneSaveMsg(false);
		
		addressTable.cancelAddEditAddress();

		EAddressControllerBean eaddContrlBean = new EAddressControllerBean();
		eaddContrlBean.cancelEAddress();
		
		//Below one line code is added for removing the success message after clicking reset - 03/05/2012
		
		getCommonEntityDataBean().setEaddrSaveMsg(false);

		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This method is to call the smalll saves in Big save.
	 * 
	 * @return boolean
	 */

	public boolean doLittleSaves() {

		ContactHelper contactHelper = null;
		boolean validAddress = true;
		boolean littleSaveValid = true;

		CommonEntityDataBean commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();
		if (addressTable.isAddMode() || addressTable.isEditMode()) {

			validAddress = addressTable.getFieldSet().validate();
			if(logger.isInfoEnabled())
			{
				logger.info("validAddress::" + validAddress);
			}
		}

		boolean validContact = true;
		if (commonEntityDataBean.isNewContactRender()
				|| commonEntityDataBean.isContactEdit()) {
			CommonContactControllerBean commonContactControllerBean = new CommonContactControllerBean();
			commonContactControllerBean.saveContact();
			validContact = commonEntityDataBean.isCommonContactFlag();
			
		}

		boolean validPhone = true;
		if (commonEntityDataBean.isPhoneRendered()
				|| commonEntityDataBean.isEditPhoneRendered()) {
			PhoneControllerBean phoneControllerBean = new PhoneControllerBean();
			phoneControllerBean.savePhone();
			validPhone = commonEntityDataBean.isCommonPhoneFalg();
			if(logger.isInfoEnabled())
			{
				logger.info("validPhone::" + validPhone);
			}

		}

		boolean validEaddress = true;
		if (commonEntityDataBean.isEaddressRendered()
				|| commonEntityDataBean.isEditEAddress()) {
			EAddressControllerBean eAddressControllerBean = new EAddressControllerBean();
			eAddressControllerBean.saveEAddress();
			validEaddress = commonEntityDataBean.isCommonEAddressFlag();
			if(logger.isInfoEnabled())
			{
				logger.info("validEaddress::" + validEaddress);
			}

		}

		if (!validAddress || !validContact || !validPhone || !validEaddress) {
			littleSaveValid = false;
		}
		
		return littleSaveValid;
		/* End Small Save and Big Save */

	}

	/**
	 * This method used take the Entity Information to Case Search Entity of
	 * Case.
	 * 
	 * @param specificEntity
	 *            takes specificEntity as param
	 */
	private void showAfterCreateInCase(SpecificEntity specificEntity) {
		
		String entType = null;
		String entID = null;
		String caseDetails = null;
		if (specificEntity.getSpecificEntitySK() != null) {
			entType = specificEntity.getSpecificEntityTypeCode();

			entID = specificEntity.getSpecificEntitySK().toString();

			caseDetails = entID + ":" + entType;
			if(logger.isInfoEnabled())
			{
				logger.info("Entity details" + caseDetails);
			}
			/** Code for IPC */
			FacesContext fc = FacesContext.getCurrentInstance();

			fc.getExternalContext().getRequestMap().put("MaintainSearchId",
					caseDetails);
		}

		else {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}

		/** Re set the Source Portlet name to null again */
		getCmEntityMaintainDataBean().setSourcePorletName(null);
		getCmEntityMaintainDataBean()
				.setCmEnityDetailVO(new CMEntityDetailVO());
		getCmEntityMaintainDataBean().setDuplicateCMEnityDetailVO(
				new CMEntityDetailVO());

		/** Set the rendering variable to false again */
		getCmEntityMaintainDataBean().setRenderCancelCommandLink(false);
		getCmEntityMaintainDataBean().setRenderCancelOutputLinkCaseEnitySrch(
				false);
		getCmEntityMaintainDataBean()
				.setRenderCancelOutputLinkInqCaseEnitySrch(false);

		resetMaintainEntity();
	}

	/**
	 * This method Gets the list of Duplicae Entities.
	 * 
	 * @return String
	 */
	public String getDuplicateEntities() {

		
		CommonEntityDataBean commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();
		String goToPage = ContactManagementConstants.RENDER_SUCCESS;
		// String goToPage=ContactManagementConstants.GO_TO_VIEW_ENTITY;
		CMEntityDetailVO cmEntityDetailVO = cmEntityMaintainDataBean
				.getCmEnityDetailVO();
		/* big save and little save */
		boolean littleSaveValid = doLittleSaves();
		if(logger.isInfoEnabled())
		{
			logger.info("littleSaveValid::" + littleSaveValid);
		}
		/* End big save and little save */
		boolean validateFlag = validateEntityBeforeAdd(cmEntityDetailVO);
		if(logger.isInfoEnabled())
		{
			logger.info("validateFlag::" + validateFlag);
		}

		if (validateFlag && littleSaveValid) {
			SpecificEntitySearchCriteriaVO specificEntitySearchCriteriaVO = getSearchCriteriaForDupEnt(cmEntityDetailVO);
			EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
			CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();

			if (specificEntitySearchCriteriaVO != null) {

				/** Call Delegate method */

				try {
					enterpriseSearchResultsVO = cmEntityDelegate
							.getDuplicateEntityList(specificEntitySearchCriteriaVO);
				}

				catch (CMEntityFetchBusinessException e) {
					e.printStackTrace();

				}

				/** Duplicates entities present */
				if (enterpriseSearchResultsVO != null
						&& enterpriseSearchResultsVO.getSearchResults() != null
						&& !enterpriseSearchResultsVO.getSearchResults()
								.isEmpty()) {

					ArrayList dupEntResultsList = enterpriseSearchResultsVO
							.getSearchResults();
					ArrayList finalDupEntList = new ArrayList();
					/** To Show Valid value with Desc in the Search Results */
					for (Iterator iter = dupEntResultsList.iterator(); iter
							.hasNext();) {
						SpecificEntitySearchResultsVO specfEntityResultVO = (SpecificEntitySearchResultsVO) iter
								.next();
						String entTypeWithDesc = getValidValueDescFromCode(specfEntityResultVO
								.getEntityType());

						specfEntityResultVO.setEntityType(entTypeWithDesc);
						finalDupEntList.add(specfEntityResultVO);

					}

					cmEntityMaintainDataBean.setShowMessage(true);
					cmEntityMaintainDataBean
							.setDuplicateEntityList(finalDupEntList);
					cmEntityMaintainDataBean.setRenderViewEntity(Boolean.TRUE);
					cmEntityMaintainDataBean
							.setRenderMaintainEntity(Boolean.FALSE);
					boolean orgFlag = false;
					boolean distOffCodeFlag = false;
					if (specificEntitySearchCriteriaVO.getEntityType() != null
							&& specificEntitySearchCriteriaVO
									.getEntityType()
									.equalsIgnoreCase(
											ContactManagementConstants.ENTITY_TYPE_CT))

					{
						cmEntityMaintainDataBean
								.setRenderViewEntity(Boolean.FALSE);
						cmEntityMaintainDataBean
								.setRenderMaintainEntity(Boolean.TRUE);
						setErrorMessage(
								MaintainContactManagementUIConstants.COUNTRY_ALREADY_EXISTS,
								new Object[] {},
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);
					} else if (specificEntitySearchCriteriaVO.getEntityType() != null
							&& specificEntitySearchCriteriaVO
									.getEntityType()
									.equalsIgnoreCase(
											ContactManagementConstants.DISTRICT_OFF_CODE))

					{
						
						cmEntityMaintainDataBean
								.setRenderViewEntity(Boolean.FALSE);
						cmEntityMaintainDataBean
								.setRenderMaintainEntity(Boolean.TRUE);
						for (Iterator iter = dupEntResultsList.iterator(); iter
								.hasNext();) {
							SpecificEntitySearchResultsVO specfEntityResultVO = (SpecificEntitySearchResultsVO) iter
									.next();
							if (specfEntityResultVO.getDistrictOfficeCode()
									.equalsIgnoreCase(
											cmEntityDetailVO
													.getDistrictOfficeCode())) {
								//DefectId "ESPRD00624966" fixed.
								distOffCodeFlag = true;
								//break;
							}
							//else
							// if(specfEntityResultVO.getOrganisationName().equalsIgnoreCase(cmEntityDetailVO.getOrganizationName()))
							if (specfEntityResultVO.getOrganisationName()
									.equalsIgnoreCase(
											cmEntityDetailVO
													.getOrganizationName())) {
								orgFlag = true;
								// break;
							}
						}
						if (distOffCodeFlag) {
							
							setErrorMessage(
									MaintainContactManagementUIConstants.DIST_CODE_ALREADY_EXISTS,
									new Object[] {},
									ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
									null);
						}/* else if(orgFlag) */
						if (orgFlag) {

							
							setErrorMessage(
									MaintainContactManagementUIConstants.DIST_ORG_ALREADY_EXISTS,
									new Object[] {},
									ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
									null);
						}

					}
					/*
					 * else {
					 * 
					 * setErrorMessage(
					 * MaintainContactManagementUIConstants.ENTITY_ALREADY_EXISTS,
					 * new Object[] {},
					 * ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					 * null); }
					 */

				}
				/** No Duplicates There */
				else {
					/** Check if its from Entity Portlet */
					
					//"FindBugs" issue fixed
					//SpecificEntity specificEntity = new SpecificEntity();
					SpecificEntity specificEntity = null ;
					/** Call Create Method */
					specificEntity = createCMEntity();

					/**
					 * If not from different portlet -- show in same page Add
					 * Entity In update mode
					 */
					if (isNullOrEmptyField(getCmEntityMaintainDataBean()
							.getSourcePorletName())) {

						showAfterCreateInAddEntity(specificEntity);
					}

					/**
					 * If we came to Entity Page from Correspondence --need to
					 * go for after adding
					 */
					else if (StringUtils
							.equalsIgnoreCase(getCmEntityMaintainDataBean()
									.getSourcePorletName(),
									ContactManagementConstants.CORRESPONDENCE)) {

						showAfterCreateInCorrespondence(specificEntity);
					} else if (StringUtils
							.equalsIgnoreCase(getCmEntityMaintainDataBean()
									.getSourcePorletName(),
									ContactManagementConstants.INQUIRY_ENTITY)) {

						showInquiryEntityInCorrespondence(specificEntity);
					} else if (StringUtils
							.equalsIgnoreCase(getCmEntityMaintainDataBean()
									.getSourcePorletName(),
									"FromSearchCaseEntity")) {

						showAfterCreateInCase(specificEntity);
					}

					else if (StringUtils
							.equalsIgnoreCase(getCmEntityMaintainDataBean()
									.getSourcePorletName(),
									"FromInquiryCaseEntity")) {

						showInquiryEntityInCase(specificEntity);
					}

				}

			}

		}

		commonEntityDataBean.setContactSaveMsg(false);
		commonEntityDataBean.setPhoneSaveMsg(false);
		commonEntityDataBean.setAddressSaveMsg(false);
		commonEntityDataBean.setEaddrSaveMsg(false);
		return goToPage;
	}

	/**
	 * This method shows the Details of Enity After creating in Maintian Entity
	 * page.
	 * 
	 * @param specificEntity
	 *            Takes specificEntity as param.
	 */
	private void showAfterCreateInAddEntity(SpecificEntity specificEntity) {
		
		CMEntityDetailVO cmEntityDetVO = null;
		if (specificEntity.getSpecificEntitySK() != null) {
			cmEntityDetVO = getSpecificEntityDetails(specificEntity
					.getSpecificEntitySK().toString());
		}

		if (cmEntityDetVO != null) {

			cmEntityMaintainDataBean.setCmEnityDetailVO(cmEntityDetVO);

			/*
			 * setErrorMessage(ContactManagementConstants.INF_CM_SAVE_SUCCESS,
			 * new Object[] {ContactManagementConstants.CM_ENTITY},
			 * ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
			 * null);
			 */

			setErrorMessage(ContactManagementConstants.INF_CM_SAVE_SUCCESS_MSG,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);

			cmEntityMaintainDataBean.setFileSavedFlag("true");
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();
			commonEntityDataBean.setSmallSaveFlag("false");
		}

	}

	/**
	 * This method Shows the portlet in Correspondence after creating entity.
	 * 
	 * @param specificEntity
	 *            takes specificEntity as param
	 */
	private void showAfterCreateInCorrespondence(SpecificEntity specificEntity) {
		
		String entType = null;
		String entID = null;
		String correspondenceDetails = null;
		if (specificEntity.getSpecificEntitySK() != null) {
			entType = specificEntity.getSpecificEntityTypeCode();

			entID = specificEntity.getSpecificEntitySK().toString();

			correspondenceDetails = entID + ":" + entType;

			/** Code for IPC */
			FacesContext fc = FacesContext.getCurrentInstance();

			fc.getExternalContext().getRequestMap().put("correspondenceEntity",
					correspondenceDetails);
		}

		else {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}

		/** Re set the Source Portlet name to null again */
		getCmEntityMaintainDataBean().setSourcePorletName(null);
		getCmEntityMaintainDataBean()
				.setCmEnityDetailVO(new CMEntityDetailVO());
		getCmEntityMaintainDataBean().setDuplicateCMEnityDetailVO(
				new CMEntityDetailVO());

		/** Set the rendering variable to false again */

		getCmEntityMaintainDataBean().setRenderCancelCommandLink(false);
		getCmEntityMaintainDataBean().setRenderCancelOutputLinkCaseEnitySrch(
				false);
		getCmEntityMaintainDataBean()
				.setRenderCancelOutputLinkInqCaseEnitySrch(false);

		resetMaintainEntity();
	}

	/**
	 * This method used take the Entity Information to Inquiry Entity Secton of
	 * Case.
	 * 
	 * @param specificEntity
	 *            takes specificEntity as param
	 */
	private void showInquiryEntityInCase(SpecificEntity specificEntity) {
		String entType = null;
		String entID = null;
		String caseDetails = null;
		if (specificEntity.getSpecificEntitySK() != null) {
			entType = specificEntity.getSpecificEntityTypeCode();

			entID = specificEntity.getSpecificEntitySK().toString();

			caseDetails = entID + ":" + entType;

			/** Code for IPC */
			FacesContext fc = FacesContext.getCurrentInstance();

			fc.getExternalContext().getRequestMap().put(
					"MaintainInquirySearchId", caseDetails);
		}

		else {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}

		/** Re set the Source Portlet name to null again */
		getCmEntityMaintainDataBean().setSourcePorletName(null);
		getCmEntityMaintainDataBean()
				.setCmEnityDetailVO(new CMEntityDetailVO());
		getCmEntityMaintainDataBean().setDuplicateCMEnityDetailVO(
				new CMEntityDetailVO());

		/** Set the rendering variable to false again */
		getCmEntityMaintainDataBean().setRenderCancelCommandLink(false);
		getCmEntityMaintainDataBean().setRenderCancelOutputLinkCaseEnitySrch(
				false);
		getCmEntityMaintainDataBean()
				.setRenderCancelOutputLinkInqCaseEnitySrch(false);
		resetMaintainEntity();
	}

	/**
	 * This method used take the Entity Information to Inquiry Entity Secton of
	 * Correspondence.
	 * 
	 * @param specificEntity
	 *            takes specificEntity as param
	 */
	private void showInquiryEntityInCorrespondence(SpecificEntity specificEntity) {
		String entType = null;
		String entID = null;
		String correspondenceDetails = null;
		if (specificEntity.getSpecificEntitySK() != null) {
			entType = specificEntity.getSpecificEntityTypeCode();

			entID = specificEntity.getSpecificEntitySK().toString();

			correspondenceDetails = entID + ":" + entType;
			if(logger.isInfoEnabled())
			{
				logger.info("correspondenceDetails" + correspondenceDetails);
			}

			/** Code for IPC */
			FacesContext fc = FacesContext.getCurrentInstance();

			fc.getExternalContext().getRequestMap().put(
					"inquiryaboutEntityData", correspondenceDetails);
		}

		else {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		}

		/** Re set the Source Portlet name to null again */
		getCmEntityMaintainDataBean().setSourcePorletName(null);
		getCmEntityMaintainDataBean()
				.setCmEnityDetailVO(new CMEntityDetailVO());
		getCmEntityMaintainDataBean().setDuplicateCMEnityDetailVO(
				new CMEntityDetailVO());

		/** Set the rendering variable to false again */
		getCmEntityMaintainDataBean().setRenderCancelCommandLink(false);
		getCmEntityMaintainDataBean().setRenderCancelOutputLinkCaseEnitySrch(
				false);
		getCmEntityMaintainDataBean()
				.setRenderCancelOutputLinkInqCaseEnitySrch(false);
		resetMaintainEntity();
	}

	/**
	 * @author wipro This Method creates a maintain Entity.
	 * @return SpecificEntity
	 */

	public SpecificEntity createCMEntity() {

		CMEntityDetailVO cmEntityDetailVO = null;
		SpecificEntity specificEntity = new SpecificEntity();
		CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();
		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();

		cmEntityDetailVO = cmEntityMaintainDataBean.getCmEnityDetailVO();

		if (cmEntityDetailVO != null) {
			/** Call Convertor */
			specificEntity = cmEntityDOConvertor
					.convertCMEntityVOToDO(cmEntityDetailVO);

		}
		/** Call Delegate */

		/** Call Delegate method to create CallScript */
		try {

			if (specificEntity != null) {
				specificEntity = cmEntityDelegate
						.createSpecificEntity(specificEntity);

				specificEntity = cmEntityDelegate
						.getSpecificEntityDetails(specificEntity
								.getSpecificEntitySK());

			}

		} catch (CMEntityCreateBusinessException e) {

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		} catch (CMEntityFetchBusinessException e) {

			setErrorMessage(ContactManagementConstants.SAVE_FAILURE,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}

		return specificEntity;
	}

	/**
	 * @author wipro This Method updates a maintain Entity.
	 * @return String
	 */

	public String updateCMEntity() {

		CommonEntityDataBean commonEntityDataBean = new CommonEntityDataBean();
		if (cmEntityMaintainDataBean.isShowSuccessMessage()) {
			/* clear the Success message for the duplicate entity Save */
			cmEntityMaintainDataBean.setShowSuccessMessage(false);
		}

		if (commonEntityDataBean.isEaddrSaveMsg()) {
			commonEntityDataBean.setEaddrSaveMsg(false);
		}
		CMEntityDetailVO cmEntityDetailVO = cmEntityMaintainDataBean
				.getCmEnityDetailVO();
		commonEntityDataBean = ContactHelper.getCommonEntityDataBean();
		cmEntityMaintainDataBean.setShowValidatedMessage(false);
		commonEntityDataBean.setEaddrSaveMsg(false);

		/* Small Save and Big Save */
		boolean littleSaveValid = doLittleSaves();
		/* End Small Save and Big Save */

		boolean validateFlag = validateEntityBeforeAdd(cmEntityDetailVO);

		if (validateFlag && littleSaveValid) {
			boolean duplicate = false;
			boolean flag = false;
			SpecificEntity specificEntity = new SpecificEntity();
			CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();
			CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();

			/**
			 * Check if the Entity Type is UP and if he is a Valid enrolled
			 * provider
			 */
			//"FindBugs" issue fixed starts
			/*if (cmEntityDetailVO.getEntityType().equalsIgnoreCase(
					ContactManagementConstants.ENTITY_TYPE_UNPROV)
					&& !cmEntityMaintainDataBean.isValidUP()) {

				cmEntityDetailVO.setEntityID(null);
			}*/
			if (cmEntityDetailVO != null 
					&& cmEntityDetailVO.getEntityType().equalsIgnoreCase(
					ContactManagementConstants.ENTITY_TYPE_UNPROV)
					&& !cmEntityMaintainDataBean.isValidUP()) {

				cmEntityDetailVO.setEntityID(null);
			}

			if (cmEntityDetailVO != null) {

				/** Call Convertor */
				specificEntity = cmEntityDOConvertor
						.convertCMEntityVOToDO(cmEntityDetailVO);
			}

			try {

				if (cmEntityDetailVO != null
						&& cmEntityDetailVO.getEntityType().equalsIgnoreCase(
						ContactManagementConstants.ENTITY_TYPE_DO)) {

					String code = "";
					if(logger.isInfoEnabled())
					{
						logger.info("Entity Type:::"+ specificEntity.getSpecificEntityTypeCode());
					}
					code = cmEntityDelegate
							.getDuplicateEntities(specificEntity);
					if(logger.isInfoEnabled())
					{
						logger.info("code from District:::" + code);
					}
					if (ContactManagementConstants.DISTRICT_OFFICE_UNIQUE_CODE
							.equalsIgnoreCase(code)) {
						setErrorMessage(
								MaintainContactManagementUIConstants.DIST_CODE_ALREADY_EXISTS,
								new Object[] {},
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);
						duplicate = true;

					} else if (ContactManagementConstants.DISTRICT_OFFICE_ORG_NAME
							.equalsIgnoreCase(code)) {

						setErrorMessage(
								MaintainContactManagementUIConstants.DIST_ORG_ALREADY_EXISTS,
								new Object[] {},
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);
						duplicate = true;
					}
				}

				if (cmEntityDetailVO != null 
						&& cmEntityDetailVO.getEntityType().equalsIgnoreCase(
						ContactManagementConstants.ENTITY_TYPE_CT)) {
					//"FindBugs" issue fixed ends

					String code = "";
					
					code = cmEntityDelegate
							.getDuplicateEntities(specificEntity);
					if(logger.isInfoEnabled())
					{
						logger.info("code from County::" + code);
					}

					if (ContactManagementConstants.COUNTY_AND_COUNTY_CODE
							.equalsIgnoreCase(code)) {
						setErrorMessage(
								MaintainContactManagementUIConstants.COUNTY_CODE_ALREADY_EXISTS,
								new Object[] {},
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);
						duplicate = true;

					} else if (ContactManagementConstants.COUNTY_AND_COUNTY_NAME
							.equalsIgnoreCase(code)) {
						setErrorMessage(
								MaintainContactManagementUIConstants.COUNTY_NAME_ALREADY_EXISTS,
								new Object[] {},
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);
						duplicate = true;
					}

				}

			} catch (CMEntityFetchBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}

			/** Call Delegate method to create CallScript */
			try {

				if (!duplicate && specificEntity != null) {

					specificEntity = cmEntityDelegate
							.updateSpecificEntity(specificEntity);
					specificEntity = cmEntityDelegate
							.getSpecificEntityDetails(specificEntity
									.getSpecificEntitySK());

					flag = true;

				}
				/** Update provider table */
				
				/* As per requirement unnecessary update to provider table should not happen. 
				Thats the reason commenting below if condition to avoid unnecessary update
				 to provider table for the defect ESPRD00796033 */


				/*if (flag && cmEntityMaintainDataBean.isValidUP()) 
				{

					flag = updateProviderWithSpEntSK(specificEntity
							.getSpecificEntitySK());
				}*/

				if (flag) {

					cmEntityDetailVO = cmEntityDOConvertor
							.convertCmEntityDOToVO(specificEntity,
									controlRequired);
					String validValDesc = getValidValueDescFromCode(cmEntityDetailVO
							.getEntityType());
					cmEntityDetailVO.setEntityTypeWithDesc(validValDesc);
					cmEntityMaintainDataBean
							.setCmEnityDetailVO(cmEntityDetailVO);

					setErrorMessage(
							ContactManagementConstants.INF_CM_SAVE_SUCCESS_MSG,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							null);
					cmEntityMaintainDataBean.setFileSavedFlag("true");
					commonEntityDataBean.setSmallSaveFlag("false");
				}

			} catch (CMEntityUpdateBusinessException e) {
				e.printStackTrace();
				setErrorMessage(
						ContactManagementConstants.ERR_SW_UPDATE_FAILURE,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						null);
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}
			//Added for UC-PGM-CRM-34.CR-2196
			catch (CMEntityFetchBusinessException e) {
				setErrorMessage(
						ContactManagementConstants.ERR_SW_SEVERE_FAILURE,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						null);
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}

			}
		}

		commonEntityDataBean.setContactSaveMsg(false);
		commonEntityDataBean.setPhoneSaveMsg(false);
		commonEntityDataBean.setAddressSaveMsg(false);

		commonEntityDataBean.setEditEAddress(false);

		if (commonEntityDataBean.isEditEaddressflag()) {

			commonEntityDataBean.getCommonEntityVO().setEaddressVO(
					commonEntityDataBean.getTempEAddressVO());
			commonEntityDataBean.setEditEAddress(true);
			commonEntityDataBean.setEaddressRendered(true);
		}

		commonEntityDataBean.setEditEAddress(false);
		commonEntityDataBean.setEaddrSaveMsg(false);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method Updates the rpovider table with the Unenrolled Specific
	 * entity Sk.
	 * 
	 * @param specificEntiySK
	 *            Takes specificEntiySK as param
	 * @return boolean
	 */
	private boolean updateProviderWithSpEntSK(Long specificEntiySK) {
		
		boolean ind = false;
		String entityIDType = cmEntityMaintainDataBean.getCmEnityDetailVO()
				.getEntityIDType();
		String altEntityID = cmEntityMaintainDataBean.getCmEnityDetailVO()
				.getEntityID();
		Provider provider = null;

		SpecificEntity unenrolledSpEntity = new SpecificEntity();
		ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

		/** Get the provider Do with the details */
		try {
			provider = providerInformationDelegate.getProvider(altEntityID,
					entityIDType);
			/*
			 * Change done for ES2 DO changes
			 */

			if (provider != null) {
				unenrolledSpEntity.setSpecificEntitySK(specificEntiySK);

				providerInformationDelegate.updateProvider(provider);
				ind = true;

			}
		}

		catch (ProviderUpdateFailureException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		} catch (EnterpriseBaseBusinessException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}

		return ind;
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
	 * This operation is used to find the component by passing id.
	 * 
	 * @param base :
	 *            View root component of the jsp.
	 * @param id :
	 *            Id of the component from jsp.
	 * @return UIComponent object.
	 */
	public UIComponent findComponent(UIComponent base, String id) {
		
		UIComponent result = null;

		// Is the "base" component itself the match we are looking for?
		if (id.equals(base.getId())) {
			result = base;
		}

		else {
			// Search through our facets and children
			UIComponent component = null;

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

		return result;
	}

	/**
	 * This method is defined to retrieve the specificEntity details along with
	 * commonEntity details
	 * 
	 * @param specificEntitySK -
	 *            given specificEnitySK
	 * @return CMEntityDetailVO
	 */
	public CMEntityDetailVO getSpecificEntityDetails(String specificEntitySK)

	{
		//"FindBugs" issue fixed
		//SpecificEntity specificEntity = new SpecificEntity();
		SpecificEntity specificEntity = null ;
		CMEntityDetailVO cmEntityDetailVO = new CMEntityDetailVO();
		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
		CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();

		if (specificEntitySK != null) {
			try {
				specificEntity = cmEntityDelegate
						.getSpecificEntityDetails(new Long(specificEntitySK));

				cmEntityDetailVO = cmEntityDOConvertor.convertCmEntityDOToVO(
						specificEntity, controlRequired);

				/**
				 * This methods does the needed rendering or setting values for
				 * Update page
				 */
				setMaintainEntityReqForUpdate(cmEntityDetailVO);

				/**
				 * This method returns Entity Type Description from the
				 * respective code
				 */

				String validValDesc = getValidValueDescFromCode(cmEntityDetailVO
						.getEntityType());
				cmEntityDetailVO.setEntityTypeWithDesc(validValDesc);

				cmEntityMaintainDataBean.setCmEnityDetailVO(cmEntityDetailVO);

			} catch (CMEntityFetchBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}
		}

		return cmEntityDetailVO;

	}

	/**
	 * This method is defined to retrieve the specificEntity details along with
	 * commonEntity details
	 * 
	 * @param specificEntitySK -
	 *            given specificEnitySK
	 * @return CMEntityDetailVO
	 */
	public CMEntityDetailVO getSpecificEntityDetailsForContinue(
			String specificEntitySK)

	{
		//"FindBugs" issue fixed
		//SpecificEntity specificEntity = new SpecificEntity();
		SpecificEntity specificEntity = null ; 
		CMEntityDetailVO cmEntityDetailVO = new CMEntityDetailVO();
		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
		//	CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();
		// //Find bug issue

		if (specificEntitySK != null) {
			try {
				specificEntity = cmEntityDelegate
						.getSpecificEntityDetails(new Long(specificEntitySK));
				if (specificEntity.getSpecificEntityTypeCode() != null
						&& specificEntity.getSpecificEntityTypeCode().length() > 0) {
					cmEntityDetailVO.setEntityType(specificEntity
							.getSpecificEntityTypeCode());
				}
				if (specificEntity.getName() != null) {
					NameVO nameVO = new NameVO();
					nameVO
							.setFirstName(specificEntity.getName()
									.getFirstName());
					nameVO.setMiddleName(specificEntity.getName()
							.getMiddleName());
					nameVO.setLastName(specificEntity.getName().getLastName());
					nameVO.setSuffixName(specificEntity.getName()
							.getSuffixName());
					/** Sets the version no */
					nameVO
							.setVersionNo(specificEntity.getName()
									.getVersionNo());
					cmEntityDetailVO.setNameVO(nameVO);

				}
				if (specificEntity.getSpecificEntitySK() != null) {
					cmEntityDetailVO.setCmEntityID(specificEntity
							.getSpecificEntitySK().toString());
				}
				if (specificEntity.getOrganizationName() != null
						&& specificEntity.getOrganizationName().length() > 0) {
					cmEntityDetailVO.setOrganizationName(specificEntity
							.getOrganizationName());
				}
				if (specificEntity.getSsnNumber() != null
						&& specificEntity.getSsnNumber().length() > 0) {
					cmEntityDetailVO
							.setSsnNumber(specificEntity.getSsnNumber());
				}
				if (specificEntity.getTaxID() != null
						&& specificEntity.getTaxID().length() > 0) {
					cmEntityDetailVO
							.setEmployeeIdentificationNumber(specificEntity
									.getTaxID());

				}
				if (specificEntity.getProviderTypeCode() != null
						&& specificEntity.getProviderTypeCode().length() > 0) {
					cmEntityDetailVO.setProviderType(specificEntity
							.getProviderTypeCode());
				}
				if (specificEntity.getLob() != null) {
					if (specificEntity.getLob().getLobCode() != null
							&& specificEntity.getLob().getLobCode().length() > 0) {
						cmEntityDetailVO.setLineOfBusiness(specificEntity
								.getLob().getLobCode());
					}
				}

				/**
				 * This methods does the needed rendering or setting values for
				 * Update page
				 */
				setMaintainEntityReqForUpdate(cmEntityDetailVO);

				/**
				 * This method returns Entity Type Description from the
				 * respective code
				 */

				String validValDesc = getValidValueDescFromCode(cmEntityDetailVO
						.getEntityType());
				cmEntityDetailVO.setEntityTypeWithDesc(validValDesc);

			} catch (CMEntityFetchBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}
		}

		return cmEntityDetailVO;

	}

	/**
	 * This method is used to show Details of View Entity.
	 * 
	 * @return String
	 */
	public String getViewEntityDetails() {
		
		//"FindBugs" issue fixed
		//CMEntityDetailVO cmDupEntiyDetailVO = new CMEntityDetailVO();
		CMEntityDetailVO cmDupEntiyDetailVO = null;
		String entityID = null;

		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
		if (indexCode == null) {
			indexCode = "0";
		}
		int index = Integer.parseInt(indexCode);
		cmEntityMaintainDataBean.setStartIndexForViewEntity((index/10)*10);

		/** If List MaintainCallScriptList in dataBean is not Null */
		if (cmEntityMaintainDataBean.getDuplicateEntityList() != null
				&& cmEntityMaintainDataBean.getDuplicateEntityList().size() > 0) {
			SpecificEntitySearchResultsVO specEntSearchResVO = (SpecificEntitySearchResultsVO) cmEntityMaintainDataBean
					.getDuplicateEntityList().get(index);
			entityID = specEntSearchResVO.getEntityID().toString();

		}

		if (entityID != null) {
			/** Call Delegate */

			cmDupEntiyDetailVO = getSpecificEntityDetailsForContinue(entityID);

			cmEntityMaintainDataBean
					.setDuplicateCMEnityDetailVO(cmDupEntiyDetailVO);

			/** Show the Detail Section */
			cmEntityMaintainDataBean.setRenderViewEntityDetail(true);

		}

		showAuditHistory();
		cmEntityMaintainDataBean.getDuplicateCMEnityDetailVO()
				.setAuditLogRender(false);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method cancels the View Entity Block.
	 * 
	 * @return String
	 */
	public String cancelViewEntityDetails() {
		
		/** Close the BLock of View Entity */
		cmEntityMaintainDataBean.setRenderViewEntityDetail(false);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method Creates a clone of CMEntityDetailVO.
	 * 
	 * @param cmEntityDetailVO
	 *            Takes cmEntityDetailVO
	 * @return CMEntityDetailVO
	 */
	/*
	 * private CMEntityDetailVO getCloneOfCMEntityDetailVO( CMEntityDetailVO
	 * cmEntityDetailVO) { logger.info("Inside getCloneOfCMEntityDetailVO");
	 * CMEntityDetailVO cloneCMEntityDetailVO = null; try {
	 * cloneCMEntityDetailVO = (CMEntityDetailVO) cmEntityDetailVO.clone(); }
	 * catch (CloneNotSupportedException e) { logger.error(e.getMessage(), e); }
	 * cloneCMEntityDetailVO = (cloneCMEntityDetailVO == null) ? new
	 * CMEntityDetailVO() : cloneCMEntityDetailVO; logger.debug("Inside clone
	 * method ----" + cloneCMEntityDetailVO.getEntityType()); return
	 * cloneCMEntityDetailVO; }
	 */
	/**
	 * This Method Calls CreateCMEnity and navigates respectively based on the
	 * Source Portlet.
	 * 
	 * @return String
	 */
	public String continueWithADD() {

		String gotoPage = ContactManagementConstants.RENDER_SUCCESS;
		//"FindBugs" issue fixed 
		//SpecificEntity specificEntity = new SpecificEntity();
		SpecificEntity specificEntity =null ;
		/** Call Create Method */
		specificEntity = createCMEntity();

		/**
		 * If not from different portlet -- show in same page Add Entity In
		 * update mode
		 */
		if(logger.isInfoEnabled())
		{
			logger.info("cmEntityMaintainDataBean====" +cmEntityMaintainDataBean.getSourcePorletName());
		}
		if (isNullOrEmptyField(cmEntityMaintainDataBean.getSourcePorletName())) {

			setErrorMessage(ContactManagementConstants.INF_CM_SAVE_SUCCESS_MSG,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
			viewEntToMaintainEntity(specificEntity.getSpecificEntitySK()
					.toString());
		}

		/**
		 * If we came to Entity Page from Correspondence --need to go for after
		 * adding
		 */
		else if (StringUtils.equalsIgnoreCase(cmEntityMaintainDataBean
				.getSourcePorletName(),
				ContactManagementConstants.CORRESPONDENCE)) {

			showAfterCreateInCorrespondence(specificEntity);
		} else if (StringUtils.equalsIgnoreCase(cmEntityMaintainDataBean
				.getSourcePorletName(),
				ContactManagementConstants.INQUIRY_ENTITY)) {

			showInquiryEntityInCorrespondence(specificEntity);
			// Modified for the defect ESPRD00743657 start
		}else if (StringUtils      
				.equalsIgnoreCase(getCmEntityMaintainDataBean()
						.getSourcePorletName(),
						"FromSearchCaseEntity")) {
			
			showAfterCreateInCase(specificEntity);
		}

		else if (StringUtils
				.equalsIgnoreCase(getCmEntityMaintainDataBean()
						.getSourcePorletName(),
						"FromInquiryCaseEntity")) {

			
			showInquiryEntityInCase(specificEntity);
		}
			// Modified for the defect ESPRD00743657 end
		return gotoPage;
	}

	/**
	 * This mehod Takes from View entity To Maintain Entity after adding entity.
	 * 
	 * @param specificEntSk
	 *            takes specificEntSk as param.
	 */
	private void viewEntToMaintainEntity(String specificEntSk) {
		
		CMEntityDetailVO cmEntityDetailVO = null;
		/** Call the getDetails mehod to getthe details of a specific record */
		if (specificEntSk != null) {
			cmEntityDetailVO = getSpecificEntityDetails(specificEntSk);
		}

		if (cmEntityDetailVO != null) {
			// gotoPage = ContactManagementConstants.GO_TO_MAINTAIN_ENTITY;
			cmEntityMaintainDataBean.setCmEnityDetailVO(cmEntityDetailVO);
			cmEntityMaintainDataBean.setRenderMaintainEntity(Boolean.TRUE);
			cmEntityMaintainDataBean.setRenderViewEntity(Boolean.FALSE);
		}

	}

	/**
	 * This method Doesnt Add Record but Continues with same and navigates to
	 * Update Page .
	 * 
	 * @return String
	 */
	public String continueWithRecord() {

		String gotoPage = ContactManagementConstants.RENDER_SUCCESS;
		SpecificEntity specificEntity = null;
		/** Get the reference of CMEntityDetailVO that is binded in ViewEniy page */
		CMEntityDetailVO dupCMEntityDetailVO = cmEntityMaintainDataBean
				.getDuplicateCMEnityDetailVO();

		/**
		 * If not from different portlet -- show in same page Add Entity In
		 * update mode
		 */
		if (isNullOrEmptyField(getCmEntityMaintainDataBean()
				.getSourcePorletName())) {

			viewEntToMaintainEntity(dupCMEntityDetailVO.getCmEntityID());
		}

		/**
		 * If we came to Entity Page from Correspondence --need to go for after
		 * adding
		 */
		else if (StringUtils.equalsIgnoreCase(getCmEntityMaintainDataBean()
				.getSourcePorletName(),
				ContactManagementConstants.CORRESPONDENCE)) {
			specificEntity = getSpecificEntityDO(dupCMEntityDetailVO
					.getCmEntityID());
			showAfterCreateInCorrespondence(specificEntity);
		} else if (StringUtils.equalsIgnoreCase(getCmEntityMaintainDataBean()
				.getSourcePorletName(),
				ContactManagementConstants.INQUIRY_ENTITY)) {
			specificEntity = getSpecificEntityDO(dupCMEntityDetailVO
					.getCmEntityID());
			showInquiryEntityInCorrespondence(specificEntity);
			
			// Modified for the defect ESPRD00743642 Starts
		}  else if (StringUtils
				.equalsIgnoreCase(getCmEntityMaintainDataBean()
						.getSourcePorletName(),
						"FromSearchCaseEntity")) {
			specificEntity = getSpecificEntityDO(dupCMEntityDetailVO
					.getCmEntityID());
			showAfterCreateInCase(specificEntity);
		}

		else if (StringUtils
				.equalsIgnoreCase(getCmEntityMaintainDataBean()
						.getSourcePorletName(),
						"FromInquiryCaseEntity")) {

			specificEntity = getSpecificEntityDO(dupCMEntityDetailVO
					.getCmEntityID());
			showInquiryEntityInCase(specificEntity);
		}
		// Modified for the defect ESPRD00743642 ends
		return gotoPage;

	}

	/**
	 * This method returns Specific Entity Domain Object .
	 * 
	 * @param specificEntSK
	 *            Takes specificEntSK as param .
	 * @return SpecificEntity
	 */
	private SpecificEntity getSpecificEntityDO(String specificEntSK) {
		SpecificEntity specificEntity = new SpecificEntity();
		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();

		if (specificEntSK != null) {
			try {
				specificEntity = cmEntityDelegate
						.getSpecificEntityDetails(new Long(specificEntSK));
			} catch (CMEntityFetchBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}

		}

		return specificEntity;

	}

	/**
	 * This variable is Used For IPC to Maintain Enity . The Geter method is
	 * Called in maintainEnity Portlet.
	 */
	private boolean receiveEntity = false;

	/**
	 * @return Returns the receiveEntity.
	 */
	public boolean isReceiveEntity() {

		FacesContext fc = FacesContext.getCurrentInstance();
		getUserPermission(); // Moved here from the constructor
		if (fc.getExternalContext().getRequestMap() != null) {

			/** Added for Ipc from SearchEntity to Maintain entity */
			String entityId = (String) fc.getExternalContext().getRequestMap()
					.get("maintainEntityId");

			if (entityId != null
					&& !entityId
							.equalsIgnoreCase(ContactManagementConstants.SEARCH_ENTITY)) {
				clearCommonEntityDataBean();
				if (cmEntityMaintainDataBean.isShowSuccessMessage()) {
					/* clear duplicate entity creation success message */
					cmEntityMaintainDataBean.setShowSuccessMessage(false);
				}
				cmEntityMaintainDataBean.setContactHidden("plus");
				cmEntityMaintainDataBean.setPhRecordHidden("plus");
				cmEntityMaintainDataBean.setEaddressHidden("plus");
				cmEntityMaintainDataBean.setAddressHidden("plus");
				cmEntityMaintainDataBean.setAuditLogFlag(false);
				UIComponent component = ContactManagementHelper
						.findComponentInRoot("commonEntityaudit");
				if (component != null) {
					AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
					auditHistoryTable.getAuditLogControllerBean()
							.setPlusMinusFlag(false);
				}
				cmEntityMaintainDataBean.getCmEnityDetailVO().getAuditKeyList()
						.clear();
				cmEntityMaintainDataBean.setRenderViewEntity(Boolean.FALSE);
				cmEntityMaintainDataBean.setRenderMaintainEntity(Boolean.TRUE);
				getSpecificEntityDetails(entityId);
				//for fixing defect ESPRD00658277 (by default add/edit mode of
				// address section to close)
				UIComponent component1 = ContactManagementHelper
						.findComponentInRoot("addrTable");
				if (component1 != null) {
					AddressHistoryTable table = (AddressHistoryTable) component1;
					if (table != null) {
						table.cancelAddEditAddress();
					}
				}
				//for fixing defect ESPRD00658277 (by default add/edit mode of
				// address section to close)

				getCmEntityMaintainDataBean().setRenderCancelOutputLink(false);
				getCmEntityMaintainDataBean()
						.setRenderCorrespondenceSave(false);
				getCmEntityMaintainDataBean().setRenderCorresInquiryEntitySave(
						false);
				getCmEntityMaintainDataBean().setRenderCaseSave(false);
				getCmEntityMaintainDataBean().setRenderCaseInquiryEntitySave(
						false);
				getCmEntityMaintainDataBean().setRenderCORRCancelOutputLink(
						false);
			}

			else if (entityId != null
					&& entityId
							.equalsIgnoreCase(ContactManagementConstants.SEARCH_ENTITY)) {

				clearMaintainDataBeanSeesion();
				//for fixing defect ESPRD00658277 (by default add/edit mode of
				// address section to close)
				UIComponent component1 = ContactManagementHelper
						.findComponentInRoot("addrTable");
				if (component1 != null) {
					AddressHistoryTable table = (AddressHistoryTable) component1;
					if (table != null) {
						table.cancelAddEditAddress();
					}
				}
				//for fixing defect ESPRD00658277 (by default add/edit mode of
				// address section to close)

				cmEntityMaintainDataBean.setContactHidden("plus");
				cmEntityMaintainDataBean.setPhRecordHidden("plus");
				cmEntityMaintainDataBean.setEaddressHidden("plus");
				cmEntityMaintainDataBean.setAddressHidden("plus");
				cmEntityMaintainDataBean.setCursorFocusId("entTydropdownMem");
				getCmEntityMaintainDataBean().setRenderCancelOutputLink(false);
				getCmEntityMaintainDataBean().setRenderCorrespondenceSave(true);
				getCmEntityMaintainDataBean().setRenderCorresInquiryEntitySave(
						false);
				getCmEntityMaintainDataBean().setRenderCaseSave(false);
				getCmEntityMaintainDataBean().setRenderCaseInquiryEntitySave(
						false);
				getCmEntityMaintainDataBean().setRenderCORRCancelOutputLink(
						false);
			}

			/** Added for IPC from CoressPondence entity Search to Add Entity */

			String fromPageName = (String) fc.getExternalContext()
					.getRequestMap().get("MaintainEntity");
			if(logger.isInfoEnabled())
			{
				logger.info("fromPageName=====" + fromPageName);
			}

			if (fromPageName != null) {
				if (ContactManagementConstants.CORRESPONDENCE
						.equalsIgnoreCase(fromPageName)) {
					clearMaintainDataBeanSeesion();
					getCmEntityMaintainDataBean().setSourcePorletName(
							fromPageName);

					/**
					 * Cancel link will now take back page to Search entity Cr
					 * or that of case
					 */
					getCmEntityMaintainDataBean().setRenderCancelCommandLink(
							true);
					getCmEntityMaintainDataBean()
							.setRenderCancelOutputLinkCaseEnitySrch(false);
					getCmEntityMaintainDataBean()
							.setRenderCancelOutputLinkInqCaseEnitySrch(false);
					getCmEntityMaintainDataBean().setRenderCorrespondenceSave(
							true);
					getCmEntityMaintainDataBean()
							.setRenderCorresInquiryEntitySave(false);
					getCmEntityMaintainDataBean().setRenderCaseSave(false);
					getCmEntityMaintainDataBean()
							.setRenderCaseInquiryEntitySave(false);

					/** Cancel link will now take back page to Search entitty */
					getCmEntityMaintainDataBean().setRenderCancelOutputLink(
							false);

					getCmEntityMaintainDataBean().setContinueParamName(
							"send.Correspondence.Entity");
					getCmEntityMaintainDataBean().setContinueParamValue(
							"sendCorrespondenceEntity");
					cmEntityMaintainDataBean.setCursorFocusId("entTydropdownMem");
					
				} else if (ContactManagementConstants.INQUIRY_ENTITY
						.equalsIgnoreCase(fromPageName)) {
					clearMaintainDataBeanSeesion();
					getCmEntityMaintainDataBean().setSourcePorletName(
							fromPageName);
					getCmEntityMaintainDataBean().setUrlMappingValue(
							"/wps/myportal/AddCorrespondence");
					getCmEntityMaintainDataBean().setContinueParamName(
							"send.InquiryaboutEntity.Data");
					getCmEntityMaintainDataBean().setContinueParamValue(
							"sendinquiryaboutEntityData");
					/** Cancel link will now take back page to Search entitty */
					getCmEntityMaintainDataBean()
							.setRenderCORRCancelOutputLink(true);
					getCmEntityMaintainDataBean().setRenderCancelOutputLink(
							true);
					getCmEntityMaintainDataBean().setRenderCorrespondenceSave(
							false);
					getCmEntityMaintainDataBean()
							.setRenderCorresInquiryEntitySave(true);
					getCmEntityMaintainDataBean().setRenderCaseSave(false);
					getCmEntityMaintainDataBean()
							.setRenderCaseInquiryEntitySave(false);
					cmEntityMaintainDataBean.setCursorFocusId("entTydropdownMem");
					getCmEntityMaintainDataBean().setInquirySave(true);
				} else if ("FromSearchCaseEntity"
						.equalsIgnoreCase(fromPageName)) {
					clearMaintainDataBeanSeesion();
					getCmEntityMaintainDataBean().setSourcePorletName(
							fromPageName);
					getCmEntityMaintainDataBean().setUrlMappingValue(
							"/wps/myportal/FromSearchCase");

					/** Cancel link will now take back page to Search Case Entity */
					getCmEntityMaintainDataBean()
							.setRenderCORRCancelOutputLink(false);
					getCmEntityMaintainDataBean().setRenderCancelOutputLink(
							true);
					getCmEntityMaintainDataBean().setRenderCorrespondenceSave(
							false);
					getCmEntityMaintainDataBean()
							.setRenderCorresInquiryEntitySave(false);
					getCmEntityMaintainDataBean().setRenderCaseSave(true);
					getCmEntityMaintainDataBean()
							.setRenderCaseInquiryEntitySave(false);
					getCmEntityMaintainDataBean()
							.setRenderCancelOutputLinkCaseEnitySrch(true);
					getCmEntityMaintainDataBean()
							.setRenderCancelOutputLinkInqCaseEnitySrch(false);
					cmEntityMaintainDataBean.setCursorFocusId("entTydropdownMem");
					// Modified for the defect ESPRD00743642 Starts
					getCmEntityMaintainDataBean().setContinueParamName(
							"send.Search.Id");
					getCmEntityMaintainDataBean().setContinueParamValue(
							"sendSearchId");
					getCmEntityMaintainDataBean().setCaseSave(true); 
					// Modified for the defect ESPRD00743642 ends
					
				} else if ("FromInquiryCaseEntity"
						.equalsIgnoreCase(fromPageName)) {
					clearMaintainDataBeanSeesion();
					getCmEntityMaintainDataBean().setSourcePorletName(
							fromPageName);

					/**
					 * Cancel link will now take back page to Inquiry Case
					 * Entity
					 */
					getCmEntityMaintainDataBean()
							.setRenderCORRCancelOutputLink(false);
					getCmEntityMaintainDataBean().setRenderCancelOutputLink(
							true);
					getCmEntityMaintainDataBean().setRenderCorrespondenceSave(
							false);
					getCmEntityMaintainDataBean()
							.setRenderCorresInquiryEntitySave(false);
					getCmEntityMaintainDataBean().setRenderCaseSave(false);
					getCmEntityMaintainDataBean()
							.setRenderCaseInquiryEntitySave(true);
					getCmEntityMaintainDataBean()
							.setRenderCancelOutputLinkCaseEnitySrch(false);
					getCmEntityMaintainDataBean()
							.setRenderCancelOutputLinkInqCaseEnitySrch(true);
					cmEntityMaintainDataBean.setCursorFocusId("entTydropdownMem");
					getCmEntityMaintainDataBean().setUrlMappingValue(
							"/wps/myportal/FromLogCase");
					getCmEntityMaintainDataBean().setContinueParamName(
							"send.InquirySearch.Id");
					getCmEntityMaintainDataBean().setContinueParamValue(
							"sendInquirySearchId");
					getCmEntityMaintainDataBean().setInquiryCaseSave(true); // Modified for the defect ESPRD00743642
					
				} else if ("TPLHIPP".equalsIgnoreCase(fromPageName)) {
					clearMaintainDataBeanSeesion();
					getCmEntityMaintainDataBean().setSourcePorletName(
							fromPageName);

					/**
					 * Cancel link will now take back page to Inquiry Case
					 * Entity
					 */
					getCmEntityMaintainDataBean()
							.setRenderHIPPCancelOutputLink(true);
					getCmEntityMaintainDataBean().setRenderCancelOutputLink(
							true);
					getCmEntityMaintainDataBean().setRenderCorrespondenceSave(
							false);
					getCmEntityMaintainDataBean()
							.setRenderCorresInquiryEntitySave(false);
					getCmEntityMaintainDataBean().setRenderCaseSave(false);
					getCmEntityMaintainDataBean()
							.setRenderCaseInquiryEntitySave(true);
					cmEntityMaintainDataBean.setCursorFocusId("entTydropdownMem");
					getCmEntityMaintainDataBean().setUrlMappingValue(
							"/wps/myportal/TPLHIPPDetail");
				}
				if(logger.isInfoEnabled())
				{
					logger.info("++getCmEntityMaintainDataBean().getRenderCancelOutputLink()=="
								+ getCmEntityMaintainDataBean()
										.isRenderCancelOutputLink());
					logger.info("++getCmEntityMaintainDataBean().getUrlMappingValue()=="
								+ getCmEntityMaintainDataBean()
										.getUrlMappingValue());
				}
			}else
			{
				// If condition Added for Defect ESPRD00740011
				if(getCmEntityMaintainDataBean().isInquirySave())
				{
					getCmEntityMaintainDataBean().setRenderCancelOutputLink(false);
					getCmEntityMaintainDataBean()
							.setRenderCorrespondenceSave(false);
					
					getCmEntityMaintainDataBean().setRenderCorresInquiryEntitySave(
							true);
					getCmEntityMaintainDataBean().setRenderCaseSave(false);
					getCmEntityMaintainDataBean().setRenderCaseInquiryEntitySave(
							false);
					getCmEntityMaintainDataBean().setRenderCORRCancelOutputLink(
							false);
					
					getCmEntitySearchDataBean().setCallFromSrchEDMS(false);
					
					// Modified for the defect ESPRD00743642 Starts
				}else if(getCmEntityMaintainDataBean().isCaseSave())
				 {
					getCmEntityMaintainDataBean().setRenderCorrespondenceSave(false);
					
					getCmEntityMaintainDataBean().setRenderCorresInquiryEntitySave(false);
					getCmEntityMaintainDataBean().setRenderCaseSave(true);
					getCmEntityMaintainDataBean().setRenderCaseInquiryEntitySave(false);
					getCmEntityMaintainDataBean().setRenderCORRCancelOutputLink(false);
					getCmEntitySearchDataBean().setCallFromSrchEDMS(false);
					
				 }else if(getCmEntityMaintainDataBean().isInquiryCaseSave())
				 {
						getCmEntityMaintainDataBean().setRenderCorrespondenceSave(false);
						
						getCmEntityMaintainDataBean().setRenderCorresInquiryEntitySave(false);
						getCmEntityMaintainDataBean().setRenderCaseSave(false);
						getCmEntityMaintainDataBean().setRenderCaseInquiryEntitySave(true);
						getCmEntityMaintainDataBean().setRenderCORRCancelOutputLink(false);
						getCmEntitySearchDataBean().setCallFromSrchEDMS(false);
						
						// Modified for the defect ESPRD00743642 Ends
						
				}else{		
					// Code added to fix defect ESPRD00607035
				getCmEntityMaintainDataBean().setRenderCancelOutputLink(false);
				getCmEntityMaintainDataBean()
						.setRenderCorrespondenceSave(true);
				
				getCmEntityMaintainDataBean().setRenderCorresInquiryEntitySave(
						false);
				getCmEntityMaintainDataBean().setRenderCaseSave(false);
				getCmEntityMaintainDataBean().setRenderCaseInquiryEntitySave(
						false);
				getCmEntityMaintainDataBean().setRenderCORRCancelOutputLink(
						false);
				
				getCmEntitySearchDataBean().setCallFromSrchEDMS(false);
				}
				
			}

		}

		else {
			if(logger.isDebugEnabled())
			{
				logger.debug("Req map  null");
			}
		}

		return receiveEntity;
	}

	/**
	 * @param receiveEntity
	 * 
	 * The receiveEntity to set.
	 */
	public void setReceiveEntity(boolean receiveEntity) {
		this.receiveEntity = receiveEntity;
	}

	/**
	 * This Method is used to clear all the values of CommonEntity Section from
	 * Session.
	 */
	public void clearCommonEntityDataBean() {
		/** Get Common Entity data Bean */
		CommonEntityDataBean commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();

		CommonEntityVO commonEntityVO = commonEntityDataBean
				.getCommonEntityVO();

		/** Clear the List of Coomon Entity Records */
		if (commonEntityVO.getEaddressVOList() != null
				&& commonEntityVO.getEaddressVOList().size() > 0) {

			commonEntityDataBean.getCommonEntityVO().getEaddressVOList()
					.clear();

		}
		if (commonEntityVO.getPhoneVOList() != null
				&& commonEntityVO.getPhoneVOList().size() > 0) {

			commonEntityDataBean.getCommonEntityVO().getPhoneVOList().clear();

		}

		if (commonEntityVO.getContactVOList() != null
				&& commonEntityVO.getContactVOList().size() > 0) {

			commonEntityDataBean.getCommonEntityVO().getContactVOList().clear();

		}

		if (commonEntityVO.getAddressVOList() != null
				&& commonEntityVO.getAddressVOList().size() > 0) {
			commonEntityDataBean.getCommonEntityVO().getAddressVOList().clear();

		}

		if (commonEntityVO.getAddressHistoryVOList() != null) {

			commonEntityDataBean.getCommonEntityVO().getAddressHistoryVOList()
					.clear();

		}

		/** Clear the success message */

		commonEntityDataBean.setContactSaveMsg(false);
		commonEntityDataBean.setPhoneSaveMsg(false);
		commonEntityDataBean.setEaddrSaveMsg(false);
		commonEntityDataBean.setAddressSaveMsg(false);

	}

	/**
	 * This mehod clears data from ui
	 */
	public void clearMaintainDataBeanSeesion() {
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("CMEntityMaintainDataBean");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("commonEntityDataBean");

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("cmEnityDetailVO");

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("duplicateCMEnityDetailVO");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("nameVO");

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("commonEntityVO");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("commonContactVO");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("contactVOList");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("addressVO");

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("eaddressVO");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("eaddressVOList");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("phoneVO");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("phoneVOList");

		cmEntityMaintainDataBean.setShowMessage(false);
		cmEntityMaintainDataBean.setShowSuccessMessage(false);

	}

	/**
	 * This method sets the variables for rendering the needed in Update Mode of
	 * Maintain entity page.
	 * 
	 * @param cmEntityDetailVO
	 *            Takes CMEntityDetailVO as param
	 */
	private void setMaintainEntityReqForUpdate(CMEntityDetailVO cmEntityDetailVO) {

		/** Render Entity Type Text Insted of drop down */
		cmEntityMaintainDataBean.setRenderEntityTypeDropDown(Boolean.FALSE);
		cmEntityMaintainDataBean.setRenderEntityTypeText(Boolean.TRUE);

		/** Render Save link */
		cmEntityMaintainDataBean.setRenderAddEntitySave(Boolean.FALSE);
		cmEntityMaintainDataBean.setRenderUpdateEntitySave(Boolean.TRUE);

		String entityType = cmEntityDetailVO.getEntityType();

		/**
		 * If Entity Type is Unenrolled provider set the Variables needed for
		 * the same
		 */
		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_UNPROV, entityType)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.TRUE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
			/** IF org Type is BOTH -- Show Org name n name feilds */
			if (StringUtils.equalsIgnoreCase(
					ContactManagementConstants.ORG_TYPE_BOTH, cmEntityDetailVO
							.getOrganizationType())) {

				cmEntityMaintainDataBean.setRenderOrgName(Boolean.TRUE);
				cmEntityMaintainDataBean.setRenderName(Boolean.TRUE);

			}
			/** IF org Type is Individual-- Show only name feilds */
			if (StringUtils.equalsIgnoreCase(
					ContactManagementConstants.ORG_TYPE_INDIVIDUAL,
					cmEntityDetailVO.getOrganizationType())) {

				cmEntityMaintainDataBean.setRenderOrgName(Boolean.FALSE);
				cmEntityMaintainDataBean.setRenderName(Boolean.TRUE);

			}
			/** IF org Type is GROUP-- Show only ORG Name */
			if (StringUtils.equalsIgnoreCase(
					ContactManagementConstants.ORG_TYPE_GROUP, cmEntityDetailVO
							.getOrganizationType())) {

				cmEntityMaintainDataBean.setRenderOrgName(Boolean.TRUE);
				cmEntityMaintainDataBean.setRenderName(Boolean.FALSE);

			}
		}
		/**
		 * If Entity Type is Disricy office set the Variables needed for the
		 * same
		 */
		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_DO, entityType)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.TRUE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
		}

		/**
		 * If Entity Type is County Code set the Variables needed for the same
		 */
		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_CT, entityType)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.TRUE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
		}

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_BO, entityType)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.TRUE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
		}
		// Below else if condition is added for CR : ESPRD00808886
		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_SC, entityType)) {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.FALSE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.TRUE);
		}

		/** If Entity Type Other than above set the Variables needed for the same */

		else {
			cmEntityMaintainDataBean.setRenderUnenrProv(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDistOffice(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderCounty(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderDefnComm(Boolean.TRUE);
			cmEntityMaintainDataBean
					.setRenderBillingOrganizatione(Boolean.FALSE);
			// Below line is added for CR : ESPRD00808886
			cmEntityMaintainDataBean.setRenderDynamicContentInformation(Boolean.FALSE);
		}

	}

	/**
	 * This Method Checks the Organization type and renders the req feilds based
	 * on the type . selected in Maintain entity page .
	 * 
	 * @param event
	 *            Takes The Event as Paramater.
	 */
	public void checkOrganizaionType(ValueChangeEvent event) {
		
		String newValue = (String) event.getNewValue();

		/** IF org Type is BOTH -- Show Org name n name feilds */
		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ORG_TYPE_BOTH, newValue)) {

			cmEntityMaintainDataBean.setRenderOrgName(Boolean.TRUE);
			cmEntityMaintainDataBean.setRenderName(Boolean.TRUE);
			cmEntityMaintainDataBean.getCmEnityDetailVO().setNameVO(
					new NameVO());
			/*
			 * cmEntityMaintainDataBean.getCmEnityDetailVO().setOrganizationName(
			 * null);
			 */

		}
		/** IF org Type is Individual-- Show only name feilds */
		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ORG_TYPE_INDIVIDUAL, newValue)) {

			cmEntityMaintainDataBean.setRenderOrgName(Boolean.FALSE);
			cmEntityMaintainDataBean.setRenderName(Boolean.TRUE);
			cmEntityMaintainDataBean.getCmEnityDetailVO().setNameVO(
					new NameVO());
			/*
			 * cmEntityMaintainDataBean.getCmEnityDetailVO().setOrganizationName(
			 * null);
			 */

		}
		/** IF org Type is GROUP-- Show only ORG Name */
		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ORG_TYPE_GROUP, newValue)) {
			if(logger.isInfoEnabled())
			{
				logger.info("org name" +cmEntityMaintainDataBean.getCmEnityDetailVO().getOrganizationName());
			}

			cmEntityMaintainDataBean.setRenderOrgName(Boolean.TRUE);
			cmEntityMaintainDataBean.setRenderName(Boolean.FALSE);
			cmEntityMaintainDataBean.getCmEnityDetailVO().setNameVO(
					new NameVO());

		}

	}

	/**
	 * This method Validates Entity Page before Adding Entity.
	 * 
	 * @param cmEntityDetailVO
	 *            Takes CMEntityDetailVO as param.
	 * @return boolean value
	 */
	private boolean validateEntityBeforeAdd(CMEntityDetailVO cmEntityDetailVO) {
		
		boolean flagEnt = true;

		/** Validate for Enity Type Unenrolled member and other */
		if (cmEntityMaintainDataBean.getRenderDefnComm().booleanValue()) {
			flagEnt = validateCommonEntType(cmEntityDetailVO);
		}

		/** Validate for Enity Type Unenrolled Provider */
		if (cmEntityMaintainDataBean.getRenderUnenrProv().booleanValue()) {

			flagEnt = validateUPEntType(cmEntityDetailVO);
		}

		/** Validate for Enity Type District office */
		if (cmEntityMaintainDataBean.getRenderDistOffice().booleanValue()) {

			flagEnt = validateDOEntType(cmEntityDetailVO);

		}
		/** Validate for Enity Type county code */
		if (cmEntityMaintainDataBean.getRenderCounty().booleanValue()) {

			flagEnt = validateCTEntType(cmEntityDetailVO);

		}

		/** Validate for Enity Type BillingOrganizatione */
		if (cmEntityMaintainDataBean.getRenderBillingOrganizatione()
				.booleanValue()) {

			flagEnt = validateBOEntType(cmEntityDetailVO);

		}
		// Below else if condition is added for CR : ESPRD00808886
		if(cmEntityMaintainDataBean.getRenderDynamicContentInformation().booleanValue())
		{
			flagEnt = validateSCEntType(cmEntityDetailVO);
		}

		return flagEnt;
	}

	/**
	 * This method Validates Entity Page for Entity Type Um and other.
	 * 
	 * @param cmEntityDetailVO
	 *            Takes CMEntityDetailVO as param.
	 * @return boolean value
	 */
	private boolean validateUPEntType(CMEntityDetailVO cmEntityDetailVO)

	{

		boolean flagFName = true;
		boolean flagLName = true;
		boolean flagVar = true;

		/** Checks if Entity type drop Down is empty for UP entity type page */
		if (isNullOrEmptyField(cmEntityDetailVO.getEntityType())) {

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ENTITY_TYPE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ENTITY_DD_UP);
			flagVar = false;

		}

		/** Checks if Org Type drop Down is empty for UP entity type page */
		else if (isNullOrEmptyField(cmEntityDetailVO.getOrganizationType())) {

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ORG_TYPE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ORGANIZATION_TYPE);
			flagVar = false;

		}

		else if (cmEntityDetailVO.getOrganizationType().equalsIgnoreCase(
				ContactManagementConstants.ORG_TYPE_BOTH)
				|| cmEntityDetailVO.getOrganizationType().equalsIgnoreCase(
						ContactManagementConstants.ORG_TYPE_INDIVIDUAL))

		{
			/** Checks if First Name is empty for UP entity type page */

			if (cmEntityDetailVO.getNameVO().getFirstName() == null
					|| cmEntityDetailVO.getNameVO().getFirstName().trim()
							.length() == 0) {

				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_REQUIRED,
						new Object[] { ContactManagementConstants.FIRST_NAME },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						"ffnameUP");
				flagVar = false;
				flagFName = false;
			}

			/** Checks if Last Name is empty for UP entity type page */

			if (cmEntityDetailVO.getNameVO().getLastName() == null
					|| cmEntityDetailVO.getNameVO().getLastName().trim()
							.length() == 0) {

				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_REQUIRED,
						new Object[] { ContactManagementConstants.LAST_NAME },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						"llnameUP");
				flagVar = false;
				flagLName = false;

			}

			if (cmEntityDetailVO.getOrganizationType().equalsIgnoreCase(
					ContactManagementConstants.ORG_TYPE_BOTH))

			{
				if (isNullOrEmptyField(cmEntityDetailVO.getOrganizationName())) {
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_REQUIRED,
							new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							ContactManagementConstants.ORG_NAME_COMPID);
					flagVar = false;
				} else if (validateOrgNam(cmEntityDetailVO
						.getOrganizationName())) {
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_INVALID,
							new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							ContactManagementConstants.ORG_NAME_COMPID);
					flagVar = false;
				}

			}
		}

		else if (cmEntityDetailVO.getOrganizationType().equalsIgnoreCase(
				ContactManagementConstants.ORG_TYPE_GROUP)) {
			if (isNullOrEmptyField(cmEntityDetailVO.getOrganizationName())) {
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_REQUIRED,
						new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						ContactManagementConstants.ORG_NAME_COMPID);
				flagVar = false;
			} else if (validateOrgNam(cmEntityDetailVO.getOrganizationName())) {
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_INVALID,
						new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						ContactManagementConstants.ORG_NAME_COMPID);
				flagVar = false;
			}

			cmEntityDetailVO.getNameVO().setFirstName(
					ContactManagementConstants.EMPTY_STRING);
			cmEntityDetailVO.getNameVO().setLastName(
					ContactManagementConstants.EMPTY_STRING);
			cmEntityDetailVO.getNameVO().setSuffixName(
					ContactManagementConstants.EMPTY_STRING);
			cmEntityDetailVO.getNameVO().setMiddleName(
					ContactManagementConstants.EMPTY_STRING);
		}

		/** Checks if Name fields are Invalid */
		if (flagFName || flagLName) {

			if (validateEntityName(cmEntityDetailVO.getNameVO(),
					ContactManagementConstants.FIRST_NAME_UP,
					ContactManagementConstants.LAST_NAME_UP,
					ContactManagementConstants.MIDDLE_NAME_UP,
					ContactManagementConstants.SUFFIX_NAME_UP) == false) {
				flagVar = false;
			}
		}

		/** Checks if ssn field are Invalid */
		if (!isNullOrEmptyField(cmEntityDetailVO.getSsnNumber())
				&& !EnterpriseCommonValidator.validateSSN(cmEntityDetailVO
						.getSsnNumber())) {

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SSN_INVALID,
					new Object[] { ContactManagementConstants.SSN_NUMBER },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"ssnInp");
			flagVar = false;
		}

		/** Checks if EIN field are Invalid */
		if (!isNullOrEmptyField(cmEntityDetailVO
				.getEmployeeIdentificationNumber())
				&& !validateEIN(cmEntityDetailVO
						.getEmployeeIdentificationNumber())) {

			/*
			 * for fixing defect ESPRD00605333
			 * 
			 * setErrorMessage(EnterpriseMessageConstants.ERR_SW_INTEGER, new
			 * Object[] { ContactManagementConstants.EIN_NUMBER },
			 * MessageUtil.ENTMESSAGES_BUNDLE, "einInp");
			 */
			setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
					new Object[] { ContactManagementConstants.EIN_NUMBER },
					MessageUtil.ENTMESSAGES_BUNDLE, "einInp");
			flagVar = false;
		}

		return flagVar;

	}

	/**
	 * This method Validates Entity Page for Entity Type Unenrolled provider.
	 * 
	 * @param cmEntityDetailVO
	 *            Takes CMEntityDetailVO as param.
	 * @return boolean value
	 */
	private boolean validateCommonEntType(CMEntityDetailVO cmEntityDetailVO)

	{
		boolean flagIndCom = true;
		boolean flagFirstNameCom = true;
		boolean flagLastNameCom = true;
		/**
		 * Checks if Entity type drop Down is empty for UM and Other entity type
		 * page
		 */
		if (isNullOrEmptyField(cmEntityDetailVO.getEntityType())) {

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ENTITY_TYPE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ENTITY_DD_OTHER);
			flagIndCom = false;

		}

		/** Checks if First Name is empty for UM and Other entity type page */

		if (isNullOrEmptyField(cmEntityDetailVO.getNameVO().getFirstName())) {

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.FIRST_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.FIRST_NAME_UM);
			flagIndCom = false;
			flagFirstNameCom = false;

		}

		/** Checks if Last Name is empty for UP entity type page */
		if (isNullOrEmptyField(cmEntityDetailVO.getNameVO().getLastName())) {

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.LAST_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.LAST_NAME_UM);
			flagIndCom = false;
			flagLastNameCom = false;

		}

		/** Checks if Name fields are Invalid */
		if (flagFirstNameCom || flagLastNameCom) {
			if (validateEntityName(cmEntityDetailVO.getNameVO(),
					ContactManagementConstants.FIRST_NAME_UM,
					ContactManagementConstants.LAST_NAME_UM,
					ContactManagementConstants.MIDDLE_NAME_UM,
					ContactManagementConstants.SUFFIX_NAME_UM) == false) {
				flagIndCom = false;
			}
		}

		/** Checks if ssn field are Invalid */
		if (!isNullOrEmptyField(cmEntityDetailVO.getSsnNumber())
				&& !validateSSN(cmEntityDetailVO.getSsnNumber())) {

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SSN_INVALID,
					new Object[] { ContactManagementConstants.SSN_NUMBER },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"ssnInput");
			flagIndCom = false;
		}

		/** Checks if EIN field are Invalid */
		if (!isNullOrEmptyField(cmEntityDetailVO
				.getEmployeeIdentificationNumber())
				&& !validateEIN(cmEntityDetailVO
						.getEmployeeIdentificationNumber())) {

			/*
			 * for fixing defect ESPRD00605333
			 * 
			 * setErrorMessage(EnterpriseMessageConstants.ERR_SW_INTEGER, new
			 * Object[] { ContactManagementConstants.EIN_NUMBER },
			 * MessageUtil.ENTMESSAGES_BUNDLE, "einInput");
			 */
			setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
					new Object[] { ContactManagementConstants.EIN_NUMBER },
					MessageUtil.ENTMESSAGES_BUNDLE, "einInput");
			flagIndCom = false;
		}

		return flagIndCom;

	}

	/**
	 * This method Validates Entity Page for Entity Type District office.
	 * 
	 * @param cmEntityDetailVO
	 *            Takes CMEntityDetailVO as param.
	 * @return boolean value
	 */
	private boolean validateDOEntType(CMEntityDetailVO cmEntityDetailVO)

	{

		boolean flagIndDO = true;
		/** Checks if Entity type drop Down is empty for common entity type page */
		if (isNullOrEmptyField(cmEntityDetailVO.getEntityType())) {
			/** Sets error msg for Enity Type In common page */

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ENTITY_TYPE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ENTITY_DD_DO);
			flagIndDO = false;

		}

		/** Checks if District office code is null */
		if (isNullOrEmptyField(cmEntityDetailVO.getDistrictOfficeCode())) {
			/** Sets error msg for Enity Type In common page */

			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.DISTRICT_OFFICE_CODE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.DISTRICT_OFFICE_CODE_COMPID);
			flagIndDO = false;

		}
		/** Checks if Organization Name is null */
		if (isNullOrEmptyField(cmEntityDetailVO.getOrganizationName())) {
			/** Sets error msg for Enity Type In common page */

			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ORG_NAME_DO_COMPID);
			flagIndDO = false;

		}

		else if (validateOrgNam(cmEntityDetailVO.getOrganizationName())) {
			setErrorMessage(
					ProgramConstants.INVALID_PHONE_DETAILS_FORMAT,
					new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ORG_NAME_DO_COMPID);
			flagIndDO = false;
		}

		return flagIndDO;

	}

	/**
	 * This method Validates Entity Page for Entity Type County.
	 * 
	 * @param cmEntityDetailVO
	 *            Takes CMEntityDetailVO as param.
	 * @return boolean value
	 */
	private boolean validateCTEntType(CMEntityDetailVO cmEntityDetailVO)

	{

		boolean flagIndCT = true;
		/** Checks if Entity type drop Down is empty for common entity type page */
		if (isNullOrEmptyField(cmEntityDetailVO.getEntityType())) {
			/** Sets error msg for Enity Type In common page */

			setErrorMessage(
					ContactManagementConstants.ENTITY_TYPE_REQUIRED,
					new Object[] {},
					"com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMSearchEntity",
					null);
			flagIndCT = false;

		}

		/** Checks if County code is null */
		if (isNullOrEmptyField(cmEntityDetailVO.getCountyCode())) {
			/** Sets error msg for Enity Type In common page */

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.COUNTY_CODE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.COUNTY_CODE_COMPID);
			flagIndCT = false;

		}
		/** Checks if County Name is null */
		if (isNullOrEmptyField(cmEntityDetailVO.getCountyName())) {
			/** Sets error msg for Enity Type In common page */

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.COUNTY_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.COUNTY_COMPID);
			flagIndCT = false;

		}

		else if (validateOrgNam(cmEntityDetailVO.getCountyName())) {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_INVALID,
					new Object[] { ContactManagementConstants.COUNTY_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.COUNTY_COMPID);
			flagIndCT = false;
		}

		return flagIndCT;

	}

	/**
	 * This method Validates Entity Page for Entity Type BillingOrganizatione .
	 * 
	 * @param cmEntityDetailVO
	 *            Takes CMEntityDetailVO as param.
	 * @return boolean value
	 */
	private boolean validateBOEntType(CMEntityDetailVO cmEntityDetailVO)

	{
		
		boolean flagIndBO = true;
		/** Checks if Entity type drop Down is empty for common entity type page */
		if (isNullOrEmptyField(cmEntityDetailVO.getEntityType())) {
			/** Sets error msg for Enity Type In common page */

			setErrorMessage(
					ContactManagementConstants.ENTITY_TYPE_REQUIRED,
					new Object[] {},
					"com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMSearchEntity",
					null);
			flagIndBO = false;

		}

		if (isNullOrEmptyField(cmEntityDetailVO.getOrganizationName())) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ORG_NAME_COMPID);

			flagIndBO = false;
		} else if (validateOrgNam(cmEntityDetailVO.getOrganizationName())) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_INVALID,
					new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ORG_NAME_COMPID);
			flagIndBO = false;
		}

		/** Checks if EIN field are Invalid */
		if (!isNullOrEmptyField(cmEntityDetailVO
				.getEmployeeIdentificationNumber())
				&& !validateEIN(cmEntityDetailVO
						.getEmployeeIdentificationNumber())) {

			/*
			 * setErrorMessage(EnterpriseMessageConstants.ERR_SW_INTEGER, new
			 * Object[] { ContactManagementConstants.EIN_NUMBER },
			 * MessageUtil.ENTMESSAGES_BUNDLE, "einInpforBO");
			 */
			setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
					new Object[] { ContactManagementConstants.EIN_NUMBER },
					MessageUtil.ENTMESSAGES_BUNDLE, "einInpforBO");
			flagIndBO = false;
		}

		return flagIndBO;

	}
	
	// Below method is added for CR : ESPRD00808886
	private boolean validateSCEntType(CMEntityDetailVO cmEntityDetailVO)
	{
		boolean flagIndSC = true;
		/** Checks if Entity type drop Down is empty for common entity type page */
		if (isNullOrEmptyField(cmEntityDetailVO.getEntityType())) {
			/** Sets error msg for Enity Type In common page */

			setErrorMessage(
					ContactManagementConstants.ENTITY_TYPE_REQUIRED,
					new Object[] {},
					"com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMSearchEntity",
					null);
			flagIndSC = false;

		}

		if (isNullOrEmptyField(cmEntityDetailVO.getOrganizationName())) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"orgNCD11");

			flagIndSC = false;
		} else if (validateOrgNam(cmEntityDetailVO.getOrganizationName())) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_INVALID,
					new Object[] { ContactManagementConstants.ORGANIZATION_NAME },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"orgNCD11");
			flagIndSC = false;
		}

		return flagIndSC;
	}

	/**
	 * This method Validates Name .
	 * 
	 * @param nameVO
	 *            Takes NameVO as param.
	 * @return boolean value
	 */
	private boolean validateEntityName(NameVO nameVO, String fName,
			String lName, String mName, String sName) {
		boolean flagInd = true;

		/** Validate First Name */
		if (!isNullOrEmptyField(nameVO.getFirstName())
				&& !validateAlphaNumericSpclChar(nameVO.getFirstName())) {

			setErrorMessage(
			//ContactManagementConstants.FIRST_NAME_MSG,
					ContactManagementConstants.DATA_IN_CORRECT_FORMAT,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					fName);
			flagInd = false;
		}

		/** Validate Last Name */
		if (!isNullOrEmptyField(nameVO.getLastName())
				&& !validateAlphaNumericSpclChar(nameVO.getLastName())) {

			setErrorMessage(
			//ContactManagementConstants.LAST_NAME_MSG,
					ContactManagementConstants.DATA_IN_CORRECT_FORMAT,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					lName);
			flagInd = false;
		}

		/** Validate Middle Name */
		if (!isNullOrEmptyField(nameVO.getMiddleName())
				&& !validateAlphaNumeric(nameVO.getMiddleName())) {

			setErrorMessage(ContactManagementConstants.DATA_IN_CORRECT_FORMAT,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					mName);
			flagInd = false;
		}

		/** Validate Suffix Name */
		if (!isNullOrEmptyField(nameVO.getSuffixName())
				&& !validateAlphaNumeric(nameVO.getSuffixName())) {

			setErrorMessage(ContactManagementConstants.DATA_IN_CORRECT_FORMAT,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					sName);
			flagInd = false;
		}

		return flagInd;
	}

	/**
	 * This method will validate the web address.
	 * 
	 * @param expression
	 *            Web Address.
	 * @return boolean : true if the expression matches the pattern
	 *         http://ww.wss.com/ format
	 */
	public static boolean validateAlphaNumeric(String expression) {

		Pattern p = Pattern.compile(ProgramConstants.ALPHANUMERIC_PATTERN);
		Matcher m = p.matcher(expression);

		return m.matches();
	}

	/**
	 * This Method validates the Entity ID for Unenrolled Provider . It Checks
	 * with the Provider Table if the entity is present .
	 */
	public void validateProviderEntityID() {
		CMEntityDetailVO cmEntityDetailVO = cmEntityMaintainDataBean
				.getCmEnityDetailVO();
		String entityIDType = cmEntityDetailVO.getEntityIDType();
		String entityID = cmEntityDetailVO.getEntityID();
		/*
		 * Long specEntySK = new Long(Long.parseLong(cmEntityDetailVO
		 * .getCmEntityID()));
		 */
		boolean flag = false;

		/** Validte for not null Entity Id and Entity Id type * */
		if (checkNotNullProvInput(entityIDType, entityID))

		{
			CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
			ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

			try {
				CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();
				Long provCmnEntySK = providerInformationDelegate
						.checkProviderCommonEntitySK(entityID, entityIDType);

				if (provCmnEntySK == null) {
					setErrorMessage(
							ContactManagementConstants.INVALID_PROVIDER_ID,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							null);
					cmEntityMaintainDataBean.setShowValidatedMessage(false);

				}

				else {

					CmnSpecEntyXref cmnSpecEntyXref = cmEntityDOConvertor
							.convertCmnSpecEntyXrefVOToDO(cmEntityDetailVO,
									provCmnEntySK);

					flag = cmEntityDelegate
							.createCmnEntySpcEntyXref(cmnSpecEntyXref);
					if (flag) {
						cmEntityMaintainDataBean.setShowValidatedMessage(true);
						cmEntityMaintainDataBean
								.setProvSysIDAfterValidation(provCmnEntySK);
						cmEntityMaintainDataBean.setValidUP(true);
					}
				}
			} catch (Exception e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}
		}

	}

	/**
	 * This method takes the ValidValue code for Entity Type and return the
	 * respective Desc.
	 * 
	 * @param entityType
	 *            Takes the valid value code for Entity Type
	 * @return String
	 */
	private String getValidValueDescFromCode(String entityType) {
		
		CMEntityDOConvertor cmEnityDOConvertor = new CMEntityDOConvertor();
		//"FindBugs" issue fixed
		//List entityList = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		List entityList =null;
		int entSize = 0;
		String entityDes = entityType;
		entityList = cmEnityDOConvertor.getSpecEntityTypeReferenceData();
		if (entityList != null) {
			entSize = entityList.size();
		}
		for (int i = 0; i < entSize; i++) {
			SelectItem item = (SelectItem) entityList.get(i);

			String value = null;
			if (item != null) {
				value = (String) item.getValue();
			}

			if (StringUtils.equalsIgnoreCase(value, entityType)) {
				entityDes = item.getLabel();
			}
		}

		return entityDes;
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
	private boolean isNullOrEmptyField(String inputField) {
		
		return (inputField == null || ContactManagementConstants.EMPTY_STRING
				.equalsIgnoreCase(inputField.trim()));
	}

	/**
	 * This operation will be used to sort the set of records.
	 * 
	 * @param event
	 *            Takes event as param
	 * @return String
	 */
	public String sortDuplicateEnt(ActionEvent event) {

		
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);

		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);

		List searchList = new ArrayList();

		CMEntityDetailVO cmentDetVO = cmEntityMaintainDataBean
				.getCmEnityDetailVO();
		SpecificEntitySearchCriteriaVO specificEntitySearchCriteriaVO = getSearchCriteriaForDupEnt(cmentDetVO);

		cmEntityMaintainDataBean.setImageRender(event.getComponent().getId());

		/* Sort By Enity Id */

		if (ContactManagementConstants.ORDER_BY_ENTITYIDTYPE.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_ENTITYIDTYPE);
			specificEntitySearchCriteriaVO.setAscending(true);

		}

		if (ContactManagementConstants.ORDER_BY_ENTITYIDTYPE.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_ENTITYIDTYPE);
			specificEntitySearchCriteriaVO.setAscending(false);

		}
		/* Sorting by Name */

		if (ContactManagementConstants.ORDER_BY_FIRSTNAME.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_FIRSTNAME);
			specificEntitySearchCriteriaVO.setAscending(true);

		}

		if (ContactManagementConstants.ORDER_BY_FIRSTNAME.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_FIRSTNAME);
			specificEntitySearchCriteriaVO.setAscending(false);

		}
		/* Sort by LOB */

		if (ContactManagementConstants.ORDER_BY_LASTNAME.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_LASTNAME);
			specificEntitySearchCriteriaVO.setAscending(true);

		}

		if (ContactManagementConstants.ORDER_BY_LASTNAME.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_LASTNAME);
			specificEntitySearchCriteriaVO.setAscending(false);

		}

		/* Sort By Address */

		if (ContactManagementConstants.ORDER_BY_ENTITYID.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_ENTITYID);
			specificEntitySearchCriteriaVO.setAscending(true);

		}

		if (ContactManagementConstants.ORDER_BY_ENTITYID.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_ENTITYID);
			specificEntitySearchCriteriaVO.setAscending(false);

		}

		/* Sort By City */

		if (ContactManagementConstants.ORDER_BY_ORG.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_ORG);
			specificEntitySearchCriteriaVO.setAscending(true);

		}

		if (ContactManagementConstants.ORDER_BY_ORG.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {

			specificEntitySearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_ORG);
			specificEntitySearchCriteriaVO.setAscending(false);

		}
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
		try {

			enterpriseSearchResultsVO = cmEntityDelegate
					.getDuplicateEntityList(specificEntitySearchCriteriaVO);

		} catch (CMEntityFetchBusinessException e) {
			if(logger.isDebugEnabled())
			{
				logger.debug(e);
			}

		}
		if (enterpriseSearchResultsVO != null) {

			searchList = enterpriseSearchResultsVO.getSearchResults();

		}

		if (searchList.isEmpty()) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
		} else {
			cmEntityMaintainDataBean.setDuplicateEntityList(searchList);
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method sets the Critetria to SpecificEntitySearchCriteriaVO .
	 * 
	 * @param cmEntityDetailVO
	 *            Takes cmEntityDetailVO as param
	 * @return SpecificEntitySearchCriteriaVO
	 */
	private SpecificEntitySearchCriteriaVO getSearchCriteriaForDupEnt(
			CMEntityDetailVO cmEntityDetailVO) {
		
		SpecificEntitySearchCriteriaVO specificEntitySearchCriteriaVO = new SpecificEntitySearchCriteriaVO();

		/** Set the specificEntitySearchCriteriaVO to get the Duplicates List */
		String entityType = cmEntityDetailVO.getEntityType();
		if (entityType != null) {
			specificEntitySearchCriteriaVO.setEntityType(entityType);
		}

		NameVO nameVO = cmEntityDetailVO.getNameVO();
		if (nameVO != null) {
			specificEntitySearchCriteriaVO.setFirstName(nameVO.getFirstName());
			specificEntitySearchCriteriaVO.setLastName(nameVO.getLastName());
		}

		String organizationName = cmEntityDetailVO.getOrganizationName();
		if (organizationName != null) {
			specificEntitySearchCriteriaVO
					.setOrganisationName(organizationName);
		}

		String countyName = cmEntityDetailVO.getCountyName();
		if (countyName != null) {
			specificEntitySearchCriteriaVO.setOrganisationName(countyName);
		}

		String countyCode = cmEntityDetailVO.getCountyCode();
		if (countyCode != null) {
			specificEntitySearchCriteriaVO.setCountyCode(countyCode);
		}

		String districtOffCode = cmEntityDetailVO.getDistrictOfficeCode();
		if (districtOffCode != null) {
			specificEntitySearchCriteriaVO
					.setDistrictOfficeCode(districtOffCode);

		}
		return specificEntitySearchCriteriaVO;

	}

	/**
	 * This method validate the SSN number
	 * 
	 * @param expression :
	 *            SSN number
	 * @return boolean : true if the expression matches the pattern NNN-NN-NNNN
	 *         or NNNNNNNNN
	 */
	public static boolean validateSSN(String expression) {

		Pattern p;
		Matcher m;
		String ssnFormatFirst = ValidatorConstants.SSN_FORMAT1_PATTERN;
		String ssnFormatTwo = ValidatorConstants.SSN_FORMAT2_PATTERN;
		boolean result;
		if (expression.indexOf('-') == ProgramNumberConstants.INT_THREE) {
			p = Pattern.compile(ssnFormatFirst);
			m = p.matcher(expression);
			result = m.matches();
		} else {
			p = Pattern.compile(ssnFormatTwo);
			m = p.matcher(expression);
			result = m.matches();
		}

		return result;
	}

	/**
	 * This method holds the valid pattern for the Tax ID format.
	 * 
	 * @param expression
	 *            is the parameter that holds the value that is to be check for
	 *            the validity of the format.
	 * @return true if the match is valid.
	 */
	public static boolean validateEIN(String expression) {

		Pattern p;
		Matcher m;
		boolean result;

		p = Pattern.compile(ValidatorConstants.TAX2_PATTERN);
		m = p.matcher(expression);
		result = m.matches();

		return result;
	}

	/**
	 * This method Checks Entiy Id and Entity ID Type for Not Null before
	 * Validate.
	 * 
	 * @param entIDType
	 *            Takes alt id of provider as param
	 * @param entID
	 *            Takes entID as param
	 * @return boolean
	 */
	public boolean checkNotNullProvInput(String entIDType, String entID) {
		
		boolean flag = true;
		if (isNullOrEmptyField(entIDType)) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ENTITY_ID_TYPE_LABEL },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ENTITY_ID_TYPE);
			flag = false;
		}

		if (isNullOrEmptyField(entID)) {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ENTITY_ID },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					ContactManagementConstants.ENTITY_ID_SEARCH);
			flag = false;
		}

		return flag;

	}

	/**
	 * This method validates the format of organization name.
	 * 
	 * @param str
	 *            Takes str as parameter
	 * @return boolean.
	 */
	private boolean validateOrgNam(String str) {
		
		boolean flag = false;
		String lettersUpCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_'-1234567890 ,.&";
		String letters = "abcdefghijklmnopqrstuvwxyz_'-1234567890 ,.&";
		int strSize = 0;

		if (str.length() > 1) {
			strSize = str.length();
			for (int i = 0; i < strSize; i++) {
				String c = str.substring(i, i + 1);

				if (!(letters.indexOf(c) >= 0 || lettersUpCase.indexOf(c) >= 0)) {
					flag = true;
				}
			}
		}

		return flag;

	}

	/**
	 * @param cmEntityMaintainDataBean
	 *            The cmEntityMaintainDataBean to set.
	 */
	public void setCmEntityMaintainDataBean(
			CMEntityMaintainDataBean cmEntityMaintainDataBean) {
		this.cmEntityMaintainDataBean = cmEntityMaintainDataBean;
	}

	/**
	 * This mehod performs the IPC from Maintain Ent back o Search Cr ent
	 * /Search Case Ent.
	 */
	public void cancel() {
		
		FacesContext fc = FacesContext.getCurrentInstance();

		/** Go back to Searcg Correspondence Entity page */
		String toPageName = ContactManagementConstants.CORRESPONDENCE;
		ActionRequest request = (ActionRequest) fc.getExternalContext()
				.getRequest();
		String pageFrm = null;
		if (request != null) {

			if (request.getPortletSession().getAttribute("pageFrm") != null) {
				pageFrm = request.getPortletSession().getAttribute("pageFrm")
						.toString();
				//request.getPortletSession().removeAttribute("pageFrm");
			}else{
				// Code added to fix defect ESPRD00607035
				fc.getExternalContext().getRequestMap().put("SearchEntityPage",
						toPageName);
				//Code added to fix the defect : ESPRD00730879....start
				clearMaintainDataBeanSeesion();
				cmEntityMaintainDataBean.setCmEnityDetailVO(new CMEntityDetailVO());
				cmEntityMaintainDataBean.setRenderMaintainEntity(Boolean.TRUE);
				cmEntityMaintainDataBean.setRenderAddEntitySave(Boolean.TRUE);
				cmEntityMaintainDataBean.setRenderUpdateEntitySave(Boolean.FALSE);
				//....ESPRD00730879....end.
			}
		}

		if (pageFrm != null && !(pageFrm.equalsIgnoreCase(""))) {
			if ("SearchEntity".equalsIgnoreCase(pageFrm)) {

				//SearchEntityPage
				fc.getExternalContext().getRequestMap().put("SearchEntityPage",
						toPageName);
			} else if ("crsEntitySearch".equalsIgnoreCase(pageFrm)) {
				fc.getExternalContext().getRequestMap().put(
						"SearchCorrespondenceEntity", toPageName);
			}
		}

		/** Set the rendering variable to false again */

		getCmEntityMaintainDataBean().setRenderCancelCommandLink(false);
		getCmEntityMaintainDataBean().setRenderCancelOutputLinkCaseEnitySrch(
				false);
		getCmEntityMaintainDataBean()
				.setRenderCancelOutputLinkInqCaseEnitySrch(false);
		
		//Code added to fix the defect : ESPRD00730879
		cmEntityMaintainDataBean.setRenderEntityTypeDropDown(Boolean.TRUE);
	}

	/**
	 * Gets reference of CMEntitySearchDataBean.
	 * 
	 * @author Wipro.
	 * @return CMEntitySearchDataBean
	 */
	public CMEntitySearchDataBean getCmEntitySearchDataBean() {

		
		FacesContext fc = FacesContext.getCurrentInstance();
		CMEntitySearchDataBean cmEntitySearchDataBean = (CMEntitySearchDataBean) fc
				.getApplication().createValueBinding(
						"#{" + ContactManagementConstants.CMSEARCHENTITY_BEAN_NAME + "}")
				.getValue(fc);

		return cmEntitySearchDataBean;
	}

	private boolean checkOrganizationFlag = true;

	/**
	 * This Method Checks the Organization type and renders the req feilds based
	 * on the type . selected in Maintain entity page .
	 * 
	 * @param event
	 *            Takes The Event as Paramater.
	 */
	private boolean ajaxcheckOrganizaionType = true;

	/**
	 * @return Returns the ajaxcheckOrganizaionType.
	 */
	public boolean isAjaxcheckOrganizaionType() {

		String newValue = null;
		Map map = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		if (map != null && map.get("orgty") != null) {
			newValue = map.get("orgty").toString();

		}
		if (newValue != null && !(newValue.equalsIgnoreCase(""))
				&& checkOrganizationFlag) {

			cmEntityMaintainDataBean.getCmEnityDetailVO().setOrganizationType(
					newValue);
			/** IF org Type is BOTH -- Show Org name n name feilds */
			if (StringUtils.equalsIgnoreCase(
					ContactManagementConstants.ORG_TYPE_BOTH, newValue)) {

				cmEntityMaintainDataBean.setRenderOrgName(Boolean.TRUE);
				cmEntityMaintainDataBean.setRenderName(Boolean.TRUE);
				cmEntityMaintainDataBean.getCmEnityDetailVO().setNameVO(
						new NameVO());
				cmEntityMaintainDataBean.getCmEnityDetailVO()
						.setOrganizationName(null);

			}
			/** IF org Type is Individual-- Show only name feilds */
			if (StringUtils.equalsIgnoreCase(
					ContactManagementConstants.ORG_TYPE_INDIVIDUAL, newValue)) {

				cmEntityMaintainDataBean.setRenderOrgName(Boolean.FALSE);
				cmEntityMaintainDataBean.setRenderName(Boolean.TRUE);
				cmEntityMaintainDataBean.getCmEnityDetailVO().setNameVO(
						new NameVO());
				cmEntityMaintainDataBean.getCmEnityDetailVO()
						.setOrganizationName(null);

			}
			/** IF org Type is GROUP-- Show only ORG Name */
			if (StringUtils.equalsIgnoreCase(
					ContactManagementConstants.ORG_TYPE_GROUP, newValue)) {

				cmEntityMaintainDataBean.setRenderOrgName(Boolean.TRUE);
				cmEntityMaintainDataBean.setRenderName(Boolean.FALSE);
				cmEntityMaintainDataBean.getCmEnityDetailVO().setNameVO(
						new NameVO());
				cmEntityMaintainDataBean.getCmEnityDetailVO()
						.setOrganizationName(null);

			}
			checkOrganizationFlag = false;

		}
		
		return ajaxcheckOrganizaionType;
	}

	/**
	 * @param ajaxcheckOrganizaionType
	 *            The ajaxcheckOrganizaionType to set.
	 */
	public void setAjaxcheckOrganizaionType(boolean ajaxcheckOrganizaionType) {
		this.ajaxcheckOrganizaionType = ajaxcheckOrganizaionType;
	}

	/** AUDIT LOGGING * */

	/**
	 * showing audit child history for Pricing details.
	 * 
	 * @return String
	 */
	public String showAuditHistory() {
		//	long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate audit;
		try {
			audit = new GlobalAuditsDelegate();
			CMEntityDOConvertor contactHelper = new CMEntityDOConvertor();
			SpecificEntity specificEntity = contactHelper
					.convertCMEntityVOToDO(getCmEntityMaintainDataBean()
							.getDuplicateCMEnityDetailVO());

			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(specificEntity, 0,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
			getCmEntityMaintainDataBean().setAuditHistoryList(
					enterpriseResultVO.getSearchResults());
			getCmEntityMaintainDataBean().setAuditHistoryRender(true);
			/* Added new for Expand */
			getCmEntityMaintainDataBean().setAuditOpen(true);
			setRecordRange(enterpriseResultVO);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			{
				logger.debug("Error in show child audit history  " + e);
			}
		}

		/*
		 * long exitTime = System.currentTimeMillis(); if
		 * (logger.isInfoEnabled()) {
		 * logger.info("CMEntityMaintainControllerBean" + "#" +
		 * "showAuditHistory" + "#" + (exitTime - entryTime)); }
		 */
		return ProgramConstants.RETURN_SUCCESS;
	}

	public String showColumnChange() {
		//	long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
			getCmEntityMaintainDataBean().setAuditColumnHistoryRender(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getCmEntityMaintainDataBean().setSelectedAuditLog(auditLog);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			{
				logger.debug("Error in show column change  " + e);
			}
		}
		return ProgramConstants.RETURN_SUCCESS;
	}

	public String closeColumnChange() {
		//	long entryTime = System.currentTimeMillis();
		try {
			getCmEntityMaintainDataBean().setAuditColumnHistoryRender(false);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			{
				logger.debug("Error in close column change  " + e);
			}
		}
		return ProgramConstants.RETURN_SUCCESS;
	}

	public void setRecordRange(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		//	long entryTime = System.currentTimeMillis();
		int listSize;
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		if(logger.isDebugEnabled())
		{
			logger.debug("Total no of records-->" + totalRecordCount);
		}
		CMEntityMaintainDataBean commonEntityDataBean = getCmEntityMaintainDataBean();
		commonEntityDataBean.setCount((int) totalRecordCount);

		int noOfPages = commonEntityDataBean.getCount()
				/ commonEntityDataBean.getItemsPerPage();

		int modNofPages = commonEntityDataBean.getCount()
				% commonEntityDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}
		if(logger.isDebugEnabled())
		{
			logger.debug("Number Of pages: " + noOfPages);
		}

		commonEntityDataBean
				.setCurrentPage(ProgramConstants.INITIAL_CURRENT_PAGE);
		commonEntityDataBean.setNumberOfPages(noOfPages);
		commonEntityDataBean.setStartRecord(ProgramConstants.START_RECORD);
		commonEntityDataBean.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = commonEntityDataBean.getCount();
		if(logger.isDebugEnabled())
		{
			logger.debug("Total records count is : " + listSize);
		}
		if (listSize <= commonEntityDataBean.getItemsPerPage()) {
			commonEntityDataBean.setEndRecord(listSize);
		}
		//"FindBugs" issue fixed
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
		/*
		 * long exitTime = System.currentTimeMillis();
		 * logger.info("CMEntityMaintainControllerBean" + "#" + "setRecordRange" +
		 * "#" + "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		 */
	}

	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}

	public static boolean validateAlphaNumericSpclChar(String expression) {

		Pattern p = Pattern
				.compile(ProgramConstants.ALPHANUMERIC_SPCLCHAR_PATTERN);
		Matcher m = p.matcher(expression);

		return m.matches();
	}

	public String doAuditKeyListOperation() {

		CommonEntityDataBean commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();

		if (cmEntityMaintainDataBean.getCmEnityDetailVO().getEntityType() != null) {
			List editableEntity = new ArrayList();
			if (cmEntityMaintainDataBean.getCmEnityDetailVO().getEntityType()
					.equalsIgnoreCase("CT")) {
				editableEntity.add(createAuditableFeild("County Code",
						"countyCode"));
			} else {
				editableEntity.add(createAuditableFeild("Organization Type",
						"orginalTypeCode"));
				editableEntity.add(createAuditableFeild("Provider Type",
						"providerTypeCode"));
				editableEntity.add(createAuditableFeild("NPI", "npiNumber"));
				editableEntity.add(createAuditableFeild("Prefix",
						"namePrefixCode"));
				//Below Code added for ESPRD00793042 to display audit details for Entity Type
				editableEntity.add(createAuditableFeild("Entity Type",
								"specificEntityTypeCode"));
				//end....
				editableEntity.add(createAuditableFeild("First Name",
						"firstName"));
				editableEntity.add(createAuditableFeild("MI.", "middleName"));
				editableEntity
						.add(createAuditableFeild("Last Name", "lastName"));
				editableEntity
						.add(createAuditableFeild("Suffix", "suffixName"));
				editableEntity.add(createAuditableFeild("Organization Name",
						"organizationName"));
				editableEntity.add(createAuditableFeild("SSN", "ssnNumber"));
				editableEntity.add(createAuditableFeild("EIN", "taxID"));
				editableEntity.add(createAuditableFeild("Line Of Business",
						"lob"));
				//editableEntity.add(createAuditableFeild("County
				// Code","countyCode"));
				//editableEntity.add(createAuditableFeild("County Name",
				// "organizationName"));
				editableEntity.add(createAuditableFeild("District Office Code",
						"districtOfficeCode"));
			}

			CMEntityDetailVO cmentitydetailVO = cmEntityMaintainDataBean
					.getCmEnityDetailVO();

			if (cmentitydetailVO.getAuditKeyList() != null
					&& !(cmentitydetailVO.getAuditKeyList().isEmpty())) {
				if(logger.isDebugEnabled())
				{
					logger.debug("======cmentitydetailVO====Before Filter==="+ cmentitydetailVO.getAuditKeyList().size());
				}
				AuditDataFilter.filterAuditKeys(editableEntity,
						cmentitydetailVO);
				if(logger.isDebugEnabled())
				{
					logger.debug("======cmentitydetailVO====After Filter==="+cmentitydetailVO.getAuditKeyList().size());
				}
				
				
				
				//Below Code added for ESPRD00793042 to set AuditkeyList details to AuditHistoryTable for Entity.
				UIComponent component = ContactManagementHelper.findComponentInRoot("commonEntityaudit");
				
				if (component != null) 
				{
					AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
					auditHistoryTable.setValue(cmentitydetailVO.getAuditKeyList());
					auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(false);
				}
				//ends...
				
			} else {
				if(logger.isDebugEnabled())
				{
					logger.debug("======cmentitydetailVO====Before Filter Empty===");
				}
			}

		}
		List phoneList = commonEntityDataBean.getCommonEntityVO()
				.getPhoneVOList();
		//ResourceBundle bundle = null;
		try {
			if (phoneList != null && !(phoneList.isEmpty())) {

				Iterator auditKeyIt = phoneList.iterator();
				if (auditKeyIt != null) {
					//bundle =
					// ResourceBundle.getBundle(ProgramConstants.PHONE_PROPERTIES);
					List editablePhone = new ArrayList();
					editablePhone.add(createAuditableFeild("Phone Type",
							"phoneUsageTypeCode"));
					editablePhone.add(createAuditableFeild("Begin Date",
							"beginDate"));
					editablePhone.add(createAuditableFeild("End Date",
							"endDate"));
					editablePhone.add(createAuditableFeild(
							"Significance Type Code", "phoneUsageTypeSigCode"));
					editablePhone.add(createAuditableFeild("Status Code",
							"phoneUsageStatCode"));
					editablePhone.add(createAuditableFeild("Phone #",
							"phoneNumber"));
					editablePhone.add(createAuditableFeild("Ext",
							"phoneExtension"));
					editablePhone.add(createAuditableFeild("Out of Service",
							"outOfServiceIndicator"));
					editablePhone.add(createAuditableFeild("Country Code",
							"countryCode"));
					editablePhone
							.add(createAuditableFeild("International Phone #",
									"internationalPhoneNumber"));
					while (auditKeyIt.hasNext()) {
						PhoneVO phoneVO = (PhoneVO) auditKeyIt.next();

						if (phoneVO.getAuditKeyList() != null
								&& !(phoneVO.getAuditKeyList().isEmpty())) {
							
							AuditDataFilter.filterAuditKeys(editablePhone,
									phoneVO);
							
						} else {
							
						}

					}
					String phoneindex = commonEntityDataBean.getPhoneVOIndex();
					if (phoneindex != null
							&& !(phoneindex.equalsIgnoreCase(""))) {
						commonEntityDataBean.getCommonEntityVO().setPhoneVO(
								(PhoneVO) phoneList.get(Integer
										.parseInt(phoneindex)));
						if(logger.isDebugEnabled())
						{
							logger.debug("phoneindex" + phoneindex	+ "=================After Filter===AuditKey Size=========PhoneVO==========="
																	+ commonEntityDataBean.getCommonEntityVO().getPhoneVO().getAuditKeyList().size());
						}
					}
				}
			}

			List contactList = commonEntityDataBean.getCommonEntityVO()
					.getContactVOList();

			if (contactList != null && !(contactList.isEmpty())) {

				Iterator contsctIt = contactList.iterator();
				if (contsctIt != null) {
					List editableContact = new ArrayList();
					editableContact.add(createAuditableFeild("Contact Type",
							"representativeCrossReferenceCode"));
					editableContact.add(createAuditableFeild("Begin Date",
							"beginDate"));
					editableContact.add(createAuditableFeild("End Date",
							"endDate"));
					editableContact.add(createAuditableFeild("Significance",
							"representativeSignificantCode"));
					editableContact.add(createAuditableFeild("Status",
							"representativeXReferenceStatusCode"));
					editableContact.add(createAuditableFeild("Title",
							"titleName"));
					editableContact.add(createAuditableFeild("Prefix",
							"namePrefixCode"));
					editableContact.add(createAuditableFeild("First Name",
							"firstName"));
					editableContact.add(createAuditableFeild("Middle Name",
							"middleName"));
					editableContact.add(createAuditableFeild("Last Name",
							"lastName"));

					editableContact.add(createAuditableFeild("Suffix",
							"suffixName"));
					editableContact
							.add(createAuditableFeild("SSN", "ssnNumber"));
					editableContact.add(createAuditableFeild("Gender",
							"genderCode"));
					editableContact.add(createAuditableFeild("Date of Birth",
							"dateOfBirth"));
					editableContact.add(createAuditableFeild("Date of Death",
							"dateOfDeath"));
					//	bundle =
					// ResourceBundle.getBundle(ProgramConstants.COMMON_CONTACT_PROPERTIES);
					while (contsctIt.hasNext()) {
						CommonContactVO commonContactVO = (CommonContactVO) contsctIt
								.next();
						if (commonContactVO.getAuditKeyList() != null
								&& !(commonContactVO.getAuditKeyList()
										.isEmpty())) {
							
							AuditDataFilter.filterAuditKeys(editableContact,
									commonContactVO);
							
						} else {
							
						}
					}
					int contactindex = commonEntityDataBean
							.getSelectedConatctIndex();
					if (contactindex >= 0) {
						commonEntityDataBean.getCommonEntityVO()
								.setCommonContactVO(
										(CommonContactVO) contactList
												.get(contactindex));
						if(logger.isDebugEnabled())
						{
							logger.debug(contactindex+ "=================Filter===AuditKey Size===========Contact========="	+ commonEntityDataBean
																															.getCommonEntityVO()
																															.getCommonContactVO()
																															.getAuditKeyList().size());
						}
					}
				}
			}

			/*
			 * this filter is done while creating the audit key list for address
			 * section List addressList =
			 * commonEntityDataBean.getCommonEntityVO().getAddressVOList();
			 * 
			 * if(addressList!=null && !(addressList.isEmpty())){
			 * 
			 * Iterator addressIt = addressList.iterator(); if(addressIt!=null){
			 * List editableAddress = new ArrayList();
			 * editableAddress.add(createAuditableFeild("Address
			 * Type","addressUsageTypeCode"));
			 * editableAddress.add(createAuditableFeild("Begin
			 * Date","beginDate"));
			 * editableAddress.add(createAuditableFeild("End Date","endDate"));
			 * editableAddress.add(createAuditableFeild("Significance Type
			 * Code","addressUsageSigCode"));
			 * editableAddress.add(createAuditableFeild("Status
			 * Code","statusCode"));
			 * editableAddress.add(createAuditableFeild("Change
			 * Reason","changeReasonCode"));
			 * editableAddress.add(createAuditableFeild("Address Line
			 * 1","addressLine1"));
			 * editableAddress.add(createAuditableFeild("Address Line
			 * 2","addressLine2"));
			 * editableAddress.add(createAuditableFeild("City","cityName"));
			 * editableAddress.add(createAuditableFeild("State","stateCode"));
			 * 
			 * editableAddress.add(createAuditableFeild("Zip Code","zipCode5"));
			 * editableAddress.add(createAuditableFeild("Ext","zipCode4"));
			 * //bundle =
			 * ResourceBundle.getBundle(ProgramConstants.ADDRESS_PROPERTIES);
			 * while(addressIt.hasNext()){ AddressVO addressVO =
			 * (AddressVO)addressIt.next(); if(addressVO.getAuditKeyList()!=null &&
			 * !(addressVO.getAuditKeyList().isEmpty())){
			 * AuditDataFilter.filterAuditKeys(editableAddress,addressVO);
			 * }else{ logger.debug("======addressVO====Before Filter AuditKeys
			 * Empty==="); } } String address_index =
			 * commonEntityDataBean.getAddressVOIndex(); if(address_index!=null &&
			 * !(address_index.equalsIgnoreCase(""))){
			 * 
			 * commonEntityDataBean.getCommonEntityVO().setAddressVO((AddressVO)addressList.get(Integer.parseInt(address_index))); } } }
			 */

			List eaddressList = commonEntityDataBean.getCommonEntityVO()
					.getEaddressVOList();

			if (eaddressList != null && !(eaddressList.isEmpty())) {

				Iterator eaddressIt = eaddressList.iterator();
				if (eaddressIt != null) {
					//bundle =
					// ResourceBundle.getBundle(ProgramConstants.EADDRESS_PROPERTIES);
					List editableEAddress = new ArrayList();
					editableEAddress.add(createAuditableFeild(
							"E-Address Type Code", "eAddressUsageTypeCode"));
					editableEAddress.add(createAuditableFeild("Begin Date",
							"beginDate"));
					editableEAddress.add(createAuditableFeild("End Date",
							"endDate"));
					editableEAddress.add(createAuditableFeild(
							"Significance Type Code", "eAddressUsageSigCode"));
					editableEAddress.add(createAuditableFeild("Status Code",
							"eaddressUsageStatusCode"));
					editableEAddress.add(createAuditableFeild("E-Address",
							"eAddressText"));
					editableEAddress.add(createAuditableFeild(
							"Bounced Address", "bounceAddressIndicator"));
					while (eaddressIt.hasNext()) {
						EAddressVO eaddress = (EAddressVO) eaddressIt.next();
						if (eaddress.getAuditKeyList() != null
								&& !(eaddress.getAuditKeyList().isEmpty())) {
							
							AuditDataFilter.filterAuditKeys(editableEAddress,
									eaddress);
							
						} else {
							
						}
					}
				}
				String eaddress_index = commonEntityDataBean
						.getEaddressVOIndex();
				if (eaddress_index != null
						&& !(eaddress_index.equalsIgnoreCase(""))) {
					commonEntityDataBean.getCommonEntityVO().setEaddressVO(
							(EAddressVO) eaddressList.get(Integer
									.parseInt(eaddress_index)));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		cmEntityMaintainDataBean.setAuditLogFlag(true);

		return "";
	}

	private AuditableField createAuditableFeild(String feildName,
			String domainAttributeName) {

		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);

		return auditableField;

	}

	private String replaceGtLtChar;

	/**
	 * @return Returns the replaceGtLtChar.
	 */
	public String getReplaceGtLtChar() {
		String strreplace = null;
		if (!isNullOrEmptyField(cmEntityMaintainDataBean.getCmEnityDetailVO()
				.getNameVO().getFirstName())) {
			
			strreplace = cmEntityMaintainDataBean.getCmEnityDetailVO()
					.getNameVO().getFirstName();
			strreplace = strreplace.replaceAll("&lt;", "<");
			strreplace = strreplace.replaceAll("&gt;", ">");
			cmEntityMaintainDataBean.getCmEnityDetailVO().getNameVO()
					.setFirstName(strreplace);
		}
		if (!isNullOrEmptyField(cmEntityMaintainDataBean.getCmEnityDetailVO()
				.getNameVO().getLastName())) {
			
			strreplace = cmEntityMaintainDataBean.getCmEnityDetailVO()
					.getNameVO().getLastName();
			strreplace = strreplace.replaceAll("&lt;", "<");
			strreplace = strreplace.replaceAll("&gt;", ">");
			cmEntityMaintainDataBean.getCmEnityDetailVO().getNameVO()
					.setLastName(strreplace);
		}

		return "";
	}

	/**
	 * @param replaceGtLtChar
	 *            The replaceGtLtChar to set.
	 */
	public void setReplaceGtLtChar(String replaceGtLtChar) {
		this.replaceGtLtChar = replaceGtLtChar;
	}

	public CommonEntityDataBean getCommonEntityDataBean() {

		
		FacesContext fc = FacesContext.getCurrentInstance();
		CommonEntityDataBean commonEntityDataBean = (CommonEntityDataBean) fc
				.getApplication().createValueBinding(
						"#{" + CommonEntityDataBean.BEAN_NAME + "}").getValue(
						fc);

		return commonEntityDataBean;
	}

}

