/*
 * Created on Mar 15, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */
  

package com.acs.enterprise.common.program.commonentities.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.benefitadministration.util.helper.BenefitPlanConstants;
import com.acs.enterprise.common.program.commonentities.common.delegate.CommonEntityProcessDelegate;
import com.acs.enterprise.common.program.commonentities.common.domain.Address;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
import com.acs.enterprise.common.program.commonentities.view.vo.AddressDataVO;
import com.acs.enterprise.common.program.commonentities.view.vo.AddressRequestVO;
import com.acs.enterprise.common.program.commonentities.view.vo.AddressResponseDataVO;
import com.acs.enterprise.common.program.commonentities.view.vo.AddressResponseVO;
import com.acs.enterprise.common.program.commonentities.view.vo.AddressVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This controller bean is called by address JSP this controll bean contains
 * method specific to address validation.
 */
public class AddressControllerBean extends EnterpriseBaseControllerBean {

	/**
	 * Reference for CommonEntityDataBean.
	 */
	private CommonEntityDataBean commonEntityDataBean = null;

	/**
	 * To Hold Enterprise Logger.
	 */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(getClass().getName());
	
	/**
     * This field is used to store whether user has permission.
     */
    private boolean controlRequired = true;

	/**
	 * Constructor.
	 */
	public AddressControllerBean() {
		super();
		commonEntityDataBean = ContactHelper.getCommonEntityDataBean();
        getUserPermission();
	}

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
		logger.debug("userPermission in Address -->"+userPermission);
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
		String userID = null;
		try {

			HttpServletRequest renderrequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(renderrequest, renderresponse);

			if (enterpriseUserProfile != null) {
				userID = enterpriseUserProfile.getUserId();
			}
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("LOGGED_IN_USERID", userID);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			logger.debug("Exception has come:");
		}
		
		return userID;
	}

	/**
	 * This method is for showing AddressHistory information.
	 */
	public void showAddressHistory() {

		final String methodName = "showAddressHistory()";
		logger.debug("Entered --->" + methodName);

		commonEntityDataBean.setAddressHistoryRendered(true);

		commonEntityDataBean.setAddressHistorydisp(true);

		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get(BenefitPlanConstants.INDEX_CODE);
		int x = 0;
		if (indexCode == null || indexCode.trim().length() == x) {
			indexCode = "0";
		}
		int index = Integer.parseInt(indexCode);

		if (commonEntityDataBean.getCommonEntityVO().getAddressHistoryVOList() != null
				&& commonEntityDataBean.getCommonEntityVO()
						.getAddressHistoryVOList().size() > 0) {
			AddressVO addressVO = (AddressVO) commonEntityDataBean
					.getCommonEntityVO().getAddressHistoryVOList().get(index);
			commonEntityDataBean.getCommonEntityVO().setAddressVO(addressVO);
		}
	}

	/**
	 * This method is for Canceling Address History Summary.
	 */
	public void cancelAddressHistorySummary() {
		final String methodName = "cancelAddressHistroySummary()";
		if (logger.isDebugEnabled()) {
			logger.debug("Entered ----" + methodName);
		}
		if (commonEntityDataBean != null) {

			//commonEntityDataBean.setAddressRendered(false);
			commonEntityDataBean.setAddressSummaryRendered(false);
			commonEntityDataBean.setAddressHistorydisp(false);

		} else {
			logger.debug("commonEntityDataBean Is Null");
		}
	}

	/**
	 * This method for viewing AddressHistoryTable.
	 */

	public void viewAddressHistory() {

		final String methodName = "viewAddressHistory()";
		logger.debug("Entered _______" + methodName);
		commonEntityDataBean = ContactHelper.getCommonEntityDataBean();
		if (!commonEntityDataBean.isAddressHistoryRendered()) {
			commonEntityDataBean.setAddressHistoryRendered(true);
		}
		commonEntityDataBean.setAddressHistRendered(true);
		commonEntityDataBean.setAddressHistorydisp(false);
	}

	/**
	 * This method for closing addressHistory Table.
	 */
	public void closeAddressHistory() {
		final String methodName = "closeAddressHistory()";
		if (logger.isDebugEnabled()) {
			logger.debug("Entered $$" + methodName);
		}
		if (commonEntityDataBean != null) {
			commonEntityDataBean.setAddressHistRendered(false);
			commonEntityDataBean.setAddressHistorydisp(false);
		} else {
			logger.debug("commonEntityDataBean  Is null");
		}

	}

	/**
	 * This method is for sorting.
	 * 
	 * @param event
	 *            holds event.
	 */

	public void sortHistory(ActionEvent event) {
		final String methodName = "sort(ActionEvent event)";
		logger.debug("Entered " + methodName);
		ArrayList addressHistoryVOList = commonEntityDataBean
				.getCommonEntityVO().getAddressHistoryVOList();
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(BenefitPlanConstants.COLUMN_NAME);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(BenefitPlanConstants.SORT_ORDER);
		Comparator comparator = new Comparator() {

			public int compare(Object objectOne, Object objectTwo) {
				AddressVO addressVO1 = (AddressVO) objectOne;
				AddressVO addressVO2 = (AddressVO) objectTwo;
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
				String AddressType = "AddressType";
				if (AddressType.equals(sortColumn)) {

					return ascending ? (addressVO1.getAddressTypeDesc()
							.compareTo(addressVO2.getAddressTypeDesc()))
							: (addressVO2.getAddressTypeDesc()
									.compareTo(addressVO1.getAddressTypeDesc()));
				}
				String StreetAddress = "Street Address";
				if (StreetAddress.equals(sortColumn)) {

					return ascending ? (addressVO1.getAddressline1()
							.compareTo(addressVO2.getAddressline1()))
							: (addressVO2.getAddressline1()
									.compareTo(addressVO1.getAddressline1()));
				}
				String City = "City";
				if ("City".equals(sortColumn)) {

					return ascending ? (addressVO1.getCity()
							.compareTo(addressVO2.getCity())) : (addressVO2
							.getCity().compareTo(addressVO1.getCity()));
				}
				String State = "State";
				if ("State".equals(sortColumn)) {

					return ascending ? (addressVO1.getState()
							.compareTo(addressVO2.getState())) : (addressVO2
							.getState().compareTo(addressVO1.getState()));
				}
				String County = "county";
				if (County.equals(sortColumn)) {

					return ascending ? (addressVO1.getCounty()
							.compareTo(addressVO2.getCounty())) : (addressVO2
							.getCounty().compareTo(addressVO1.getCounty()));
				}
				String ZipCode = "Zip Code";
				if ("Zip Code".equals(sortColumn)) {

					return ascending ? (addressVO1.getZipCode5()
							.compareTo(addressVO2.getZipCode5())) : (addressVO2
							.getZipCode5().compareTo(addressVO1.getZipCode5()));
				}
				String ActiveDate = "Active Date";
				if (ActiveDate.equals(sortColumn)) {
					return ascending ? (ContactHelper.dateConverter(addressVO1
							.getBeginDateStr()).compareTo(ContactHelper
							.dateConverter(addressVO2.getBeginDateStr())))
							: (ContactHelper.dateConverter(addressVO2
									.getBeginDateStr())
									.compareTo(ContactHelper
											.dateConverter(addressVO1
													.getBeginDateStr())));
				}
				return returnValue;
			}

		};
		Collections.sort(addressHistoryVOList, comparator);
		logger.debug("Exit ...." + methodName);
	}

	/**
	 * @return ArrayList.
	 */
	public List next() {
		return null;
	}

	/**
	 * @return null
	 */
	public List previous() {
		return null;
	}

	/**
	 * This method is for facesContext for Address.
	 * 
	 * @return commonEntityDataBean
	 */

	private CommonEntityDataBean getCommonEntityDataBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		CommonEntityDataBean commonEntityDataBean = (CommonEntityDataBean) fc
				.getApplication().createValueBinding("#{commonEntityDataBean}")
				.getValue(fc);
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

	/**
	 * This method is used to display the error messages.
	 * 
	 * @param message
	 *            holds the error message.
	 * @param propertiesFile
	 *            holds the propertiesFile.
	 * @param componentName
	 *            holds the componentName.
	 */

	public void setErrorMessage(String message, String propertiesFile,
			String componentName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().setMessageBundle(propertiesFile);
		ResourceBundle bundle = resourceBundle(fc);
		String errorMsg = bundle.getString(message);
		FacesMessage fm = new FacesMessage(errorMsg);
		fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		fc.addMessage(componentName, fm);
	}

	/**
	 * This method is used for populating Resource Bundle.
	 * 
	 * @param facesContext
	 *            holds the facesContext.
	 * @return ResourceBundle
	 */
	public static ResourceBundle resourceBundle(FacesContext facesContext) {
		facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		String messageBundle = facesContext.getApplication().getMessageBundle();
		Locale locale = root.getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);
		return bundle;
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
					if(desc == "Please Select One" )
					{
					  desc = " ";  
					}
					break;
				}
			}
		}
		return desc;
	}

	public String showAddressAuditHistory() {
		GlobalAuditsDelegate audit;
	//	List list = new ArrayList(); Find bug issue
		try {
			logger.debug("in show showPhoneAuditHistory history");
			audit = new GlobalAuditsDelegate();
			ContactHelper contactHelper = new ContactHelper();
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();

			AddressVO addressVO = commonEntityDataBean.getCommonEntityVO()
					.getAddressVO();

			Address address = contactHelper.getAddressFromAddressVO(addressVO);
			final EnterpriseSearchResultsVO enterpriseResultVO = audit
			.getAuditLogList(address, 0,
					ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
			logger.info("Address List Size:" + enterpriseResultVO.getSearchResults().size());
			commonEntityDataBean.setAddressAuditHistList(enterpriseResultVO.getSearchResults());
			commonEntityDataBean.setAddressHistRndr(false);
		} catch (Exception e) {
			logger.info("Address - Error in show Parent audit history : " + e.toString());
			logger.debug("Error in show Parent audit history  " + e);
		}
		return "";
	}
	public String closeColumnChange() {
		commonEntityDataBean.setAddressHistRndr(false);
		return ProgramConstants.RETURN_SUCCESS;
	}
	public String showColumnChange() {
		long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
  		    getCommonEntityDataBean().setAddressHistRndr(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getCommonEntityDataBean().setSelectedAdrAuditLog(auditLog);
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
		if (listSize > commonEntityDataBean.getItemsPerPage()) {
			listSize = commonEntityDataBean.getItemsPerPage();
		}

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

	public String next1() {
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
	public String previous1() {
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
		//ArrayList searchList = new ArrayList(); Find bug issue
		GlobalAuditsDelegate audit = null;
		try {
			audit = new GlobalAuditsDelegate();

			ContactHelper contactHelper = new ContactHelper();
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();
			
			AddressVO addressVO = commonEntityDataBean.getCommonEntityVO()
			.getAddressVO();

			Address address = contactHelper.getAddressFromAddressVO(addressVO);

			
			EnterpriseSearchResultsVO enterpriseResultVO = audit
			.getAuditLogList(address, (currentPage-1)
					* ProgramNumberConstants.INT_TEN,
					ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

			commonEntityDataBean.setAddressAuditHistList(enterpriseResultVO.getSearchResults());
			logger.info("Address List Size:" + enterpriseResultVO.getSearchResults().size());

			//commonEntityDataBean.set.setAuditParentHistoryRender(true);


		} catch (Exception e) {
			logger.debug(e);
		}
	}
}
