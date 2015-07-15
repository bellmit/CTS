/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.audit.application.dataaccess.exception.GlobalAuditDataException;
import com.acs.enterprise.common.audit.application.exception.GlobalAuditsBusinessException;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseTypeFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.EDMSDefaultsFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.FilterCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.FilterDeleteBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.FilterFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.FilterUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseFilter;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceFilter;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceFilterXref;
import com.acs.enterprise.common.program.contactmanagement.common.vo.FilterSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CaseTypeDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CategoryDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.FilterDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseFilterVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryFilterVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.FilterVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This is a controller class used for Filters related functionality
 * functionality in the presentation layer.
 */
public class FilterControllerBean extends EnterpriseBaseControllerBean {

	/**
	 * Generating object of EnterpriseLogger.
	 */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(FilterControllerBean.class);

	/**
	 * Holds the FilterDataBean object.
	 */
	private transient FilterDataBean filterDataBean = getFilterDataBean();

	/**
	 * This field is used to store whether user has permission.
	 */
	private boolean controlRequired = true;
	
	private boolean controlRequiredFOrDelete = true;
	
	private String loadUserPermission;


	/**
	 * @return Returns the permission.
	 */
	public boolean isControlRequired() {
		return controlRequired;
	}

	/**
	 * @param permission
	 *            The permission to set.
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}

	/**
	 * @param invokeShowList
	 *            holds boolean value
	 */
	//commented for unused variables
	//private boolean invokeShowList = true;

	/**
	 * This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermission() {
		String userPermission = "";
		String userid = getUserID();
		
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.MAINTAIN_FILTERS_PAGE, userid);
			
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode",userPermission);
		userPermission = userPermission != null ? userPermission.trim() : "";
		
		if (userPermission.equals("r"))
		{
			controlRequired = false;
			controlRequiredFOrDelete = false;
		}
		if (userPermission.equals("u"))
		{
			controlRequired = true;
			controlRequiredFOrDelete = false;
		}
		if (userPermission.equals("d"))
		{
			controlRequired = true;
			controlRequiredFOrDelete = true;
		}
		
		
		
	}

	/**
	 * This method will be used for writing the logic for populating search
	 * results for a given search criteria.
	 */
	private void populateList(int currentPage) {
		//FindBug Issue Resloved
		//ArrayList searchList = new ArrayList();

		GlobalAuditsDelegate globalAuditsDelegate = null;
		try {
			globalAuditsDelegate = new GlobalAuditsDelegate();
			FilterDOConvertor filterDOConvertor = new FilterDOConvertor();
			CorrespondenceFilter correspondenceFilter = filterDOConvertor
					.convertCorrespondenceFilterVOToDO(filterDataBean
							.getFilterVO());
			//TODO: edit the ContactManagementConstants
			EnterpriseSearchResultsVO enterpriseResultVO = globalAuditsDelegate
					.getAuditLogList(correspondenceFilter, (currentPage - 1)
							* ContactManagementConstants.INT_10,
							ContactManagementConstants.NO_OF_RECORD_TO_FETCH);

			filterDataBean.setFilterAuditHistoryList(enterpriseResultVO
					.getSearchResults());
			filterDataBean.setFilterAuditRender(true);

		} catch (Exception e) {
			// e.printStackTrace();
			if(logger.isDebugEnabled())
			{
			logger
					.debug("exception occured on FilterControllerBean.populateList() :"
							+ e);
			logger.debug(e);
			}
		}
	}

	/**
	 * This method is used for going to next page.
	 * 
	 * @return ContactManagementConstants.NEXT_SUCCESS.
	 */
	public String next() {
		long entryTime = System.currentTimeMillis();

		if (logger.isDebugEnabled()) {
			logger
					.debug("The no. of pages"
							+ filterDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ filterDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ filterDataBean.getStartRecord());
			logger.debug("The end record......."
					+ filterDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(filterDataBean,
				filterDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("FilterControllerBean" + "#" + "next" + "#"
					+ (exitTime - entryTime));
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is used for data scrolling
	 * 
	 * @return ContactManagementConstants.PREVIOUS_SUCCESS.
	 */
	public String previous() {
		long entryTime = System.currentTimeMillis();
		

		EnterpriseBaseDataBean entDataBean = previousPage(filterDataBean,
				filterDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("FilterControllerBean" + "#" + "previous" + "#"
					+ "FILTER CODE" + "#" + (exitTime - entryTime));
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method get the User ID
	 * 
	 * @return String
	 */
	private String getUserID() {
		String userID = null;
		try {
			/*HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(
					renderrequest, renderresponse);
			if (enterpriseUserProfile != null) {
				userID = enterpriseUserProfile.getUserId();
			}*/
			FacesContext facesContext = FacesContext.getCurrentInstance();
			PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
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
	 *  
	 */
	/*
	 * private void getAllcategoryList() { List list = null;
	 * ContactMaintenanceDelegate contactMaintenanceDelegate = new
	 * ContactMaintenanceDelegate(); try { list =
	 * contactMaintenanceDelegate.getDistinctCategories(); } catch
	 * (EDMSDefaultsFetchBusinessException e) { logger.error(e.getMessage(), e); } }
	 */

	/**
	 * This operation is used to create new ContactManagement Filter Record.
	 * 
	 * @return String.
	 */
	public String createFilter() {

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		filterDataBean.setShowSucessMessage(false);
		FilterVO filterVO = filterDataBean.getFilterVO();
		if(logger.isDebugEnabled())
		{
		logger.debug("filter type from VO:" + filterVO.getFilterType());
		}
		if (isNameExists(filterVO) && validateFilter(filterVO)) {
			FilterDOConvertor filterDOConvertor = new FilterDOConvertor();
			if (filterVO.getFilterType().equals(
					ContactManagementConstants.FILTER_TYPE_CR)) {
				CorrespondenceFilter correspondenceFilter = null;
				correspondenceFilter = (CorrespondenceFilter) filterDOConvertor
						.convertCorrespondenceFilterVOToDO(filterVO);
				try {
					
					contactMaintenanceDelegate
							.createFilter(correspondenceFilter);
				} catch (FilterCreateBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
				}
			}
			/* For case Creation */
			if (filterVO.getFilterType().equals(
					ContactManagementConstants.FILTER_TYPE_CASE)) {
				CaseFilter caseFilter = null;
				caseFilter = (CaseFilter) filterDOConvertor
						.convertCaseFilterVOToDO(filterVO);
				try {
					
					contactMaintenanceDelegate.createCaseFilter(caseFilter);
				} catch (FilterCreateBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
				}

			}
			filterDataBean.setAssignedList(new ArrayList());
			filterDataBean.setShowNewFilterBar(Boolean.FALSE);

			addFilter();
			filterDataBean.getFilterVOArrayList();
			filterDataBean.setShowSucessMessage(true);
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Used to validate the Filter.
	 * 
	 * @param filterVO :
	 *            The filterVO to set.
	 * @return boolean.
	 */
	private boolean validateFilter(FilterVO filterVO) {
		boolean flag = true;
		
		if (isValidFilter(filterVO)) {
			
			flag = false;
		} else if (filterVO.getFilterScope().equals(
				ContactManagementConstants.EXCLUDE)
				&& isFilterHavingDfltCat(filterVO.getCategoryFilterVO(),
						filterVO.getCaseTypeFilterVO(), filterVO
								.getFilterType())) {
			
			flag = false;
			setErrorMessage(
					ContactManagementConstants.ERR_CANNOT_EXCLUDE_DFLT_CAT,
					new Object[] { ContactManagementConstants.FILTER },
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					null);
		}
		
		return flag;
	}

	/**
	 * Method to check if the category selected is from the default list.
	 * 
	 * @param selectedCategoryList :
	 *            The selectedCategoryList to set.
	 * @return boolean.
	 */
	private boolean isFilterHavingDfltCat(List selectedCategoryList,
			List selectedCaseTypeList, String filterType) {

		boolean flag = false;
		if (filterType
				.equalsIgnoreCase(ContactManagementConstants.FILTER_TYPE_CR)) {
			List dfltCatList = getDfltDistinctCategories();
			if (isDfltCatInExcludeList(selectedCategoryList, dfltCatList)) {
				flag = true;
			}
		}
		if (filterType
				.equalsIgnoreCase(ContactManagementConstants.FILTER_TYPE_CASE)) {
			
			List dfltCaseList = getDfltDistinctCaseTypes();
			if (isDfltCaseInExcludeList(selectedCaseTypeList, dfltCaseList)) {
				flag = true;
			}

		}
		
		return flag;
	}

	/**
	 * Method to get the Default categories from Edms Table.
	 * 
	 * @return List.
	 */
	public List getDfltDistinctCategories() {

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		List dfltCatList = null;
		//	boolean flag = false;
		try {
			dfltCatList = contactMaintenanceDelegate.getDistinctCategories();
		} catch (EDMSDefaultsFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} finally {

		}
		return dfltCatList;
	}

	/**
	 * Method to check if the default category is present in Exclude List.
	 * 
	 * @param categoryFilterVOList :
	 *            The categoryFilterVOList to set.
	 * @param dfltCatList :
	 *            The dfltCatList to set.
	 * @return boolean.
	 */
	private boolean isDfltCatInExcludeList(List categoryFilterVOList,
			List dfltCatList) {
		//CorrespondenceDefault correspondenceDefault = new
		// CorrespondenceDefault();
		//	int j = 0;
		boolean flag = false;
		for (Iterator iter = categoryFilterVOList.iterator(); iter.hasNext();) {
			SelectItem categoryFilterVO = (SelectItem) iter.next();
			for (Iterator iterator = dfltCatList.iterator(); iterator.hasNext();) {

				CorrespondenceCategory category = (CorrespondenceCategory) iterator
						.next();
				if (categoryFilterVO.getValue().toString().equals(
						category.getCategorySK().toString())) {
					flag = true;
				}
			}
		}

		return flag;
	}

	/**
	 * This method used to get default distinct CaseTypes from EDMS table
	 * 
	 * @return List
	 */
	public List getDfltDistinctCaseTypes() {

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		List dfltCaseList = null;
		try {
			dfltCaseList = contactMaintenanceDelegate.getDistinctCaseTypes();
			if(logger.isDebugEnabled())
		{
			logger.debug("dfltCaseList size in ControllerBean:"
					+ dfltCaseList.size());
		}
		} catch (EDMSDefaultsFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{				
			logger.error(e.getMessage(), e);
			}
			
		}

		return dfltCaseList;
	}

	/**
	 * Method to check if the default category is present in Exclude List.
	 * 
	 * @param categoryFilterVOList :
	 *            The categoryFilterVOList to set.
	 * @param dfltCatList :
	 *            The dfltCatList to set.
	 * @return boolean.
	 */
	private boolean isDfltCaseInExcludeList(List caseFilterVOList,
			List dfltCaseList) {
		
		boolean flag = false;
		for (Iterator iter = caseFilterVOList.iterator(); iter.hasNext();) {
			SelectItem caseFilterVO = (SelectItem) iter.next();
			if(logger.isDebugEnabled()){				
			logger.debug("SelectItem value in isDfltCaseInExcludeList:"
					+ caseFilterVO.getValue());
			}
			for (Iterator iterator = dfltCaseList.iterator(); iterator
					.hasNext();) {

				CaseType caseType = (CaseType) iterator.next();
				if (caseFilterVO.getValue().toString().equals(
						caseType.getCaseTypeSK().toString())) {
					flag = true;
				}
			}
		}
		
		return flag;
	}

	/**
	 * Method to check if the default category is present in Exclude List.
	 * 
	 * @return boolean.
	 */
	private boolean isDfltCatRemvFrmInclList(String filterType) {
		List dfltInSelList = new ArrayList();

		boolean returnFlag = true;
		int rowIndex = filterDataBean.getSelectedFilterIndex();
		if (filterType
				.equalsIgnoreCase(ContactManagementConstants.FILTER_TYPE_CR)) {
			boolean flag = false;
			List dfltCatList = getDfltDistinctCategories();
			FilterVO filterVo = (FilterVO) filterDataBean
					.getFilterVOArrayList().get(rowIndex);
			List prevCatList = filterVo.getCategoryFilterVO();
			for (Iterator iter = prevCatList.iterator(); iter.hasNext();) {
				CategoryFilterVO categoryFilterVO = (CategoryFilterVO) iter
						.next();
				for (Iterator iterator = dfltCatList.iterator(); iterator
						.hasNext();) {
					CorrespondenceCategory category = (CorrespondenceCategory) iterator
							.next();
					if (categoryFilterVO.getCategorySK().toString().equals(
							category.getCategorySK().toString())) {
						dfltInSelList.add(category.getCategorySK().toString());
						flag = true;
					}
				}
			}
			if (flag) {
				if (isAllDfltCatIntact(dfltInSelList)) {
					returnFlag = true;
				} else {
					resetEditedFilter();
					setErrorMessage(
							ContactManagementConstants.ERR_CANNOT_REMOVE_CAT_FROM_INCL_LIST,
							new Object[] { ContactManagementConstants.FILTER },
							ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
							null);
					returnFlag = false;
				}
			}
		}
		if (filterType
				.equalsIgnoreCase(ContactManagementConstants.FILTER_TYPE_CASE)) {
			
			boolean caseflag = false;
			List dfltCaseList = getDfltDistinctCaseTypes();
			FilterVO filterVo = (FilterVO) filterDataBean
					.getFilterVOArrayList().get(rowIndex);
			List prevCaseTypeList = filterVo.getCaseTypeFilterVO();
			for (Iterator iter = prevCaseTypeList.iterator(); iter.hasNext();) {
				CaseFilterVO caseFilterVO = (CaseFilterVO) iter.next();
				for (Iterator iterator = dfltCaseList.iterator(); iterator
						.hasNext();) {
					CaseType caseType = (CaseType) iterator.next();
					if (caseFilterVO.getCaseTypeSK().toString().equals(
							caseType.getCaseTypeSK().toString())) {
						dfltInSelList.add(caseType.getCaseTypeSK().toString());
						caseflag = true;
					}
				}
			}
			if (caseflag) {
				if (isAllDfltCaseIntact(dfltInSelList)) {
					returnFlag = true;
				} else {
					resetEditedFilter();
					setErrorMessage(
							ContactManagementConstants.ERR_CANNOT_REMOVE_CAT_FROM_INCL_LIST,
							new Object[] { ContactManagementConstants.FILTER },
							ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
							null);
					returnFlag = false;
				}
			}
		}
		

		return returnFlag;
	}

	/**
	 * @param dfltInSelList :
	 *            The dfltInSelList to set.
	 * @return boolean.
	 */
	private boolean isAllDfltCatIntact(List dfltInSelList) {

		FilterVO filterVO = filterDataBean.getFilterVO();
		List selCatList = filterVO.getCategoryFilterVO();
		boolean flag = false;
		int j = 0;
		for (Iterator iter = dfltInSelList.iterator(); iter.hasNext();) {
			String category = (String) iter.next();
			for (Iterator iterator = selCatList.iterator(); iterator.hasNext();) {
				SelectItem categoryFilterVO = (SelectItem) iterator.next();

				if (category.equals(categoryFilterVO.getValue().toString())) {
					j++;
				}
			}
		}

		if (j == dfltInSelList.size()) {
			flag = true;
		} else if (j == 0
				&& filterVO.getFilterScope().equals(
						ContactManagementConstants.EXCLUDE)) {
			flag = true;
		}

		return flag;
	}

	/**
	 * @param dfltInSelList :
	 *            The dfltInSelList to set.
	 * @return boolean.
	 */
	private boolean isAllDfltCaseIntact(List dfltInSelList) {

		
		FilterVO filterVO = filterDataBean.getFilterVO();
		List selCaseList = filterVO.getCaseTypeFilterVO();
		boolean flag = false;
		int j = 0;
		for (Iterator iter = dfltInSelList.iterator(); iter.hasNext();) {
			String caseType = (String) iter.next();
			for (Iterator iterator = selCaseList.iterator(); iterator.hasNext();) {
				SelectItem caseFilterVO = (SelectItem) iterator.next();

				if (caseType.equals(caseFilterVO.getValue().toString())) {
					j++;
				}
			}
		}

		if (j == dfltInSelList.size()) {
			flag = true;
		} else if (j == 0
				&& filterVO.getFilterScope().equals(
						ContactManagementConstants.EXCLUDE)) {
			flag = true;
		}
		

		return flag;
	}

	/**
	 * Method to find if the filter categories already exists.
	 * 
	 * @param filterVO
	 *            The filterVO to set.
	 * @return boolean.
	 */
	private boolean isFilterDeffExists(FilterVO filterVO) {

		
        //FindBug Issue Resloved
		//List listOfFilterVOs = new ArrayList();
		List listOfFilterVOs =null;
		int size = 0;
		if(ContactManagementConstants.FILTER_TYPE_CR.equalsIgnoreCase(filterVO.getFilterType())
				&& filterVO.getCategoryFilterVO() != null)
		{
			size = filterVO.getCategoryFilterVO().size();
		}else if(filterVO.getCaseTypeFilterVO() != null)
		{
			size = filterVO.getCaseTypeFilterVO().size();
		}
		
		filterVO.getCategoryFilterVO();
		listOfFilterVOs = getFltrsForDplCheck(filterVO.getFilterType(),
							filterVO.getFilterScope(), size);//filterDataBean.getFilterVOArrayList();
		boolean flag = false;
		if (listOfFilterVOs != null) {

			for (Iterator iter = listOfFilterVOs.iterator(); iter.hasNext();) {
				FilterVO filterVOFrmList = (FilterVO) iter.next();
				if (!filterVO.getFilterName().equals(
						filterVOFrmList.getFilterName())
						&& filterVO.equals(filterVOFrmList)) {
					flag = true;
					break;
				}
			}
		}
		

		return flag;
	}

	/**
	 * This method is used to check for duplicate names.
	 * 
	 * @param filterVO
	 *            The filterVO to set.
	 * @return boolean.
	 */
	private boolean isNameExists(FilterVO filterVO) {
		
		String filterName = filterVO.getFilterName();
		String filterType = filterVO.getFilterType();
		String scope = filterVO.getFilterScope();
		boolean flag = true;
		if (filterName == null
				|| filterName.equals(ContactManagementConstants.EMPTY_STRING)) {
			setErrorMessage(ContactManagementConstants.ERR_DESC_CANNOT_EMPTY,
					new Object[] { ContactManagementConstants.FILTER_DESC },
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					"descriptionOfFilter");
			flag = false;
		} else if (isDuplicateName(filterName)) {
			setErrorMessage(
					ContactManagementConstants.ERR_DUPLICATE_FILTER_DESC,
					new Object[] { filterDataBean.getFilterVO().getFilterName() },
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					null);
			
			flag = false;
		}
		if (isFilterTypeExists(filterType)) {
			flag = false;
		}
		if(isFilterScopeExists(scope))
		{
			flag = false;
		}
		
		return flag;
	}

	/**
	 * This operation is used to validate the Filter Record.
	 * 
	 * @param filterVO
	 *            The filterVO to set.
	 * @return boolean.
	 */
	private boolean isValidFilter(FilterVO filterVO) {
		boolean flag = false;
		
		String scope = filterVO.getFilterScope();
		
		String filterType = filterVO.getFilterType();

		if (isFilterTypeExists(filterType)) {
			flag = true;
		} else if (isAssignedListExists(filterVO)) {
			flag = true;
		} else if (isFilterScopeExists(scope)) {
			flag = true;
		}
		
		return flag;
	}

	/**
	 * To check if the filter scope is empty.
	 * 
	 * @param filterVO :
	 *            The filterVO to set.
	 * @return boolean.
	 */
	private boolean isAssignedListExists(FilterVO filterVO) {

		
		List assignedList = filterDataBean.getAssignedList();
		boolean flag = false;
		if (assignedList == null || assignedList.isEmpty()) {
			setErrorMessage(ContactManagementConstants.ERR_CM_ATLEAST_ONE_SEL,
					new Object[] { ContactManagementConstants.FILTER },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);

			flag = true;
		} else if (isFilterDeffExists(filterVO)) {
			setErrorMessage(
					ContactManagementConstants.ERR_DUPLICATE_FILTER_DESC,
					new Object[] { filterVO.getFilterName() },
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					null);

			flag = true;
		}
		

		return flag;
	}

	/**
	 * To check if the filter scope is empty.
	 * 
	 * @param scope :
	 *            The scope to set.
	 * @return boolean.
	 */
	private boolean isFilterScopeExists(String scope) {
		boolean flag = false;
		
		if (StringUtils.isBlank(scope)) {
			/*setErrorMessage(ContactManagementConstants.ERR_FILTER_SCOPE,
					new Object[] { ContactManagementConstants.FILTER },
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					null);*/
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.ACTION },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"actionForFilter");

			flag = true;
		}
		
		return flag;
	}

	/**
	 * To check if the filter type is empty.
	 * 
	 * @param filterType :
	 *            The filterType to set.
	 * @return boolean.
	 */
	private boolean isFilterTypeExists(String filterType) {
		
		boolean flag = false;
		if (StringUtils.isBlank(filterType)) {
			setErrorMessage(ContactManagementConstants.ERR_FILTER_TYPE,
					new Object[] { ContactManagementConstants.FILTER_TYPE },
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					"typeForFilter");

			flag = true;
		}
		
		return flag;
	}

	/**
	 * This operation is used to check the duplicate Filter names.
	 * 
	 * @param filterName
	 *            The filterName to set.
	 * @return boolean.
	 */
	private boolean isDuplicateName(String filterName) {
		
		List filterVOArrayList = filterDataBean.getFilterVOArrayList();
		FilterVO filterVO = null;
		boolean flag = false;
		Iterator iterator = filterVOArrayList.iterator();
		while (iterator.hasNext()) {
			filterVO = (FilterVO) iterator.next();
			if (filterName.equalsIgnoreCase(filterVO.getFilterName())) {
				flag = true;
				break;
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
	private boolean isFilterAssignedToUser(String filterName, String filterType) {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		boolean flag = false;
		
		if (filterType
				.equalsIgnoreCase(ContactManagementConstants.FILTER_TYPE_CR)) {
			try {
				flag = contactMaintenanceDelegate
						.checkFilterAssignedToUser(filterName);
			} catch (FilterUpdateBusinessException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}
		}
		if (filterType
				.equalsIgnoreCase(ContactManagementConstants.FILTER_TYPE_CASE)) {
			
			try {
				flag = contactMaintenanceDelegate
						.checkCaseFilterAssignedToUser(filterName);
			} catch (FilterUpdateBusinessException e) {
				if(logger.isDebugEnabled())
				{
				logger.debug(e);
				}
			}
		}
		
		
		return flag;
	}

	/**
	 * This operation is used to update ContactManagement Filter Record
	 * 
	 * @return String.
	 */
	public String updateFilter() 
	{
		
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		boolean sucessMsgFlag = false;
		filterDataBean.setShowSucessMessage(false);
		FilterVO filterVO = filterDataBean.getFilterVO();
		FilterDOConvertor filterDOConvertor = new FilterDOConvertor();
		
		
		if (isFilterAssignedToUser(filterVO.getFilterName(), filterVO
				.getFilterType())) 
		{
			if(validateFilter(filterVO))
			{
				boolean check = true;
				//List originalSelectedList = filterDataBean.getAssignedList();
				List latestSelectedList = new ArrayList();
				List originalSelectedList = new ArrayList();
				CorrespondenceFilter correspondenceFilter = null;
				correspondenceFilter = (CorrespondenceFilter) filterDOConvertor
						.convertCorrespondenceFilterVOToDO(filterVO);
				CaseFilter caseFilter = null;
				caseFilter = (CaseFilter) filterDOConvertor
						.convertCaseFilterVOToDO(filterVO);
				if (filterVO.getFilterType().equals(
						ContactManagementConstants.FILTER_TYPE_CR)) {
					FilterVO selectedfilterVO = getUpdateFilterVO(correspondenceFilter
							.getFilterName(),ContactManagementConstants.FILTER_TYPE_CR);
					originalSelectedList = selectedfilterVO.getCategoryFilterVO();
					latestSelectedList = filterVO.getCategoryFilterVO();
				} else if (filterVO.getFilterType().equals(
						ContactManagementConstants.FILTER_TYPE_CASE)) {
					
					FilterVO selectedfilterVO = getUpdateFilterVO(caseFilter
							.getFilterName(),ContactManagementConstants.FILTER_TYPE_CASE);
					originalSelectedList = selectedfilterVO.getCaseTypeFilterVO();
					latestSelectedList = filterVO.getCaseTypeFilterVO();
				}
				CategoryFilterVO categoryFilterVO = null;
				boolean Errrormsg = true;

				if (check) {
					if (filterVO.getFilterScope().equals(
							ContactManagementConstants.EXCLUDE)) {
						if (originalSelectedList.size() < latestSelectedList.size()) {
							Errrormsg = false;
							filterDataBean.setShowSucessMessage(false);
							setErrorMessage(
									ContactManagementConstants.CANNOT_UPDATE_FILTER,
									new Object[] { ContactManagementConstants.FILTER },
									ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
									null);
						}
					}
	
					else if (filterVO.getFilterScope().equals("I")) {
						
						
						if (originalSelectedList.size() > latestSelectedList.size()) {
							Errrormsg = false;
							filterDataBean.setShowSucessMessage(false);
							setErrorMessage(
									ContactManagementConstants.CANNOT_UPDATE_FILTER,
									new Object[] { ContactManagementConstants.FILTER },
									ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
									null);
	
						}
	
					}
					SelectItem s;
					SelectItem s1;
					boolean countFlag = false;
					if (originalSelectedList.size() == latestSelectedList.size()) {
						for (int i = 0; i < originalSelectedList.size(); i++) {
							s = (SelectItem) originalSelectedList.get(i);
							for (int k = 0; k < latestSelectedList.size(); k++) {
								s1 = (SelectItem) latestSelectedList.get(k);
								if (!s.getLabel().equalsIgnoreCase(s1.getLabel())) {
									countFlag = true;
									break;
	
								}
							}
						}
						if (countFlag) {
							setErrorMessage(
									ContactManagementConstants.CANNOT_UPDATE_FILTER,
									new Object[] { ContactManagementConstants.FILTER },
									ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
									null);
						}
	
					} else {
						if (Errrormsg) {
							if (filterVO.getFilterType().equals(
									ContactManagementConstants.FILTER_TYPE_CR)) {
								CorrespondenceFilter correspondenceFilter1 = null;
								correspondenceFilter1 = (CorrespondenceFilter) filterDOConvertor
										.convertCorrespondenceFilterVOToDO(filterVO);
	
								try {
									contactMaintenanceDelegate
											.updateFilter(correspondenceFilter1);
								} catch (FilterUpdateBusinessException e) {
									e.printStackTrace();
									sucessMsgFlag = true;
									if(e.getErrorCode() != null && 
											ContactManagementConstants.CATORCASETYPE_ASCTO_EDMS_ERR_MSG
												.equalsIgnoreCase(e.getErrorCode()))
									{
										setErrorMessage(ContactManagementConstants.CATORCASETYPE_ASCTO_EDMS_ERR_MSG, new Object[] {},
					                             ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, null);
									}else{
										setErrorMessage(
											EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
											new Object[] {},
											MessageUtil.ENTMESSAGES_BUNDLE, null);
									}
								}
	
								FilterVO selectedfilterVO = getUpdateFilterVO(correspondenceFilter1
										.getFilterName(),ContactManagementConstants.FILTER_TYPE_CR);
								List categorieFilterVOList = new ArrayList();
								filterDataBean
										.setTempAvailableList(getAvailableCategories());
								categorieFilterVOList = selectedfilterVO
										.getCategoryFilterVO();
								if (categorieFilterVOList != null) {
									List assignedList = getAssignedCategories(categorieFilterVOList);
									selectedfilterVO
											.setCategoryFilterVO(assignedList);
									Iterator iterator = assignedList.iterator();
									while (iterator.hasNext()) {
										SelectItem si = (SelectItem) iterator
												.next();
										removeItem(si.getValue().toString(),
												filterDataBean
														.getTempAvailableList());
									}
								}
								filterDataBean.setFilterVO(selectedfilterVO);
							}
							if (filterVO.getFilterType().equals(
									ContactManagementConstants.FILTER_TYPE_CASE)) {
								CaseFilter caseFilter1 = null;
								caseFilter1 = (CaseFilter) filterDOConvertor
										.convertCaseFilterVOToDO(filterVO);
								try {
									
									contactMaintenanceDelegate
											.updateCaseFilter(caseFilter1);
								} catch (FilterUpdateBusinessException e) {
									if(logger.isErrorEnabled())
									{
									logger.error(e.getMessage(), e);
									}
									sucessMsgFlag = true;
									if(e.getErrorCode() != null && 
											ContactManagementConstants.CATORCASETYPE_ASCTO_EDMS_ERR_MSG
												.equalsIgnoreCase(e.getErrorCode()))
									{
										setErrorMessage(ContactManagementConstants.CATORCASETYPE_ASCTO_EDMS_ERR_MSG, new Object[] {},
					                             ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, null);
									}else{
										setErrorMessage(
											EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
											new Object[] {},
											MessageUtil.ENTMESSAGES_BUNDLE, null);
										if(logger.isDebugEnabled())
										{
										logger
											.debug("FilterUpdateBusinessException occured on FiterControllerBean.updateFilter() :"
													+ e);
										}
									}
								}
								FilterVO selectedfilterVO = getUpdateFilterVO(caseFilter1
										.getFilterName(),ContactManagementConstants.FILTER_TYPE_CASE);
								List caseTypesFilterVOList = new ArrayList();
								filterDataBean
										.setTempAvailableList(getAvailableCaseTypes());
								logger.debug("Available casetypeList size :"
										+ filterDataBean.getTempAvailableList()
												.size());
								caseTypesFilterVOList = selectedfilterVO
										.getCaseTypeFilterVO();
								if (caseTypesFilterVOList != null) {
									List assignedList = getAssignedCaseTypes(caseTypesFilterVOList);
									selectedfilterVO
											.setCaseTypeFilterVO(assignedList);
									Iterator iterator = assignedList.iterator();
									while (iterator.hasNext()) {
										SelectItem si = (SelectItem) iterator
												.next();
										removeItem(si.getValue().toString(),
												filterDataBean
														.getTempAvailableList());
									}
								}
								filterDataBean.setFilterVO(selectedfilterVO);
							}
	
							if (sucessMsgFlag) {
								filterDataBean.setShowSucessMessage(false);
							} else {
								filterDataBean.setShowSucessMessage(true);
							}
						}
					}
				}
			}
		}
	
	else
	{
			
			if (validateFilter(filterVO) && isDfltCatRemvFrmInclList(filterVO.getFilterType())) {
				/*boolean check = true;
				List originalSelectedList = getOriginalList();
				List latestSelectedList = new ArrayList();
				
				if (filterVO.getFilterType().equals(
						ContactManagementConstants.FILTER_TYPE_CR)) {
					latestSelectedList = filterVO.getCategoryFilterVO();
				} else if (filterVO.getFilterType().equals(
						ContactManagementConstants.FILTER_TYPE_CASE)) {
					latestSelectedList = filterVO.getCaseTypeFilterVO();
				}
				CategoryFilterVO categoryFilterVO = null;
				boolean Errrormsg1 = true;

				if (check) {

					if (filterVO.getFilterScope().equals(
							ContactManagementConstants.EXCLUDE)) {
						if (originalSelectedList.size() < latestSelectedList
								.size()) {
							Errrormsg1 = false;
							filterDataBean.setShowSucessMessage(false);
							setErrorMessage(
									ContactManagementConstants.CANNOT_UPDATE_FILTER,
									new Object[] { ContactManagementConstants.FILTER },
									ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
									null);
						}
					} else if (filterVO.getFilterScope().equals("I")) {
						if (originalSelectedList.size() > latestSelectedList
								.size()) {
							Errrormsg1 = false;
							filterDataBean.setShowSucessMessage(false);
							setErrorMessage(
									ContactManagementConstants.CANNOT_UPDATE_FILTER,
									new Object[] { ContactManagementConstants.FILTER },
									ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
									null);

						}

					}
					SelectItem s;
					SelectItem s1;
					boolean countFlag = false;
					if (originalSelectedList.size() == latestSelectedList
							.size()) {
						for (int i = 0; i < originalSelectedList.size(); i++) {
							s = (SelectItem) originalSelectedList.get(i);
							for (int k = 0; k < latestSelectedList.size(); k++) {
								s1 = (SelectItem) latestSelectedList.get(k);
								if (!s.getLabel().equalsIgnoreCase(
										s1.getLabel())) {
									countFlag = true;
									break;

								}
							}
						}
						if (countFlag) {
							filterDataBean.setShowSucessMessage(false);
							Errrormsg1 = false;
							setErrorMessage(
									ContactManagementConstants.CANNOT_UPDATE_FILTER,
									new Object[] { ContactManagementConstants.FILTER },
									ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
									null);
						}

					}
					if (Errrormsg1) {*/

						if (filterVO.getFilterType().equals(
								ContactManagementConstants.FILTER_TYPE_CR)) {

							CorrespondenceFilter correspondenceFilter = null;
							correspondenceFilter = (CorrespondenceFilter) filterDOConvertor
									.convertCorrespondenceFilterVOToDO(filterVO);

							try {
								contactMaintenanceDelegate
										.updateFilter(correspondenceFilter);
							} catch (FilterUpdateBusinessException e) {
								e.printStackTrace();
								sucessMsgFlag = true;
								
								if(e.getErrorCode() != null && 
										ContactManagementConstants.CATORCASETYPE_ASCTO_EDMS_ERR_MSG
											.equalsIgnoreCase(e.getErrorCode()))
								{
									setErrorMessage(ContactManagementConstants.CATORCASETYPE_ASCTO_EDMS_ERR_MSG, new Object[] {},
				                             ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, null);
								}else{
									setErrorMessage(
										EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
										new Object[] {},
										MessageUtil.ENTMESSAGES_BUNDLE, null);
								}
							}
							FilterVO selectedfilterVO = getUpdateFilterVO(correspondenceFilter
									.getFilterName(),ContactManagementConstants.FILTER_TYPE_CR);
							List categorieFilterVOList = new ArrayList();
							filterDataBean
									.setTempAvailableList(getAvailableCategories());
							categorieFilterVOList = selectedfilterVO
									.getCategoryFilterVO();
							if (categorieFilterVOList != null) {
								List assignedList = getAssignedCategories(categorieFilterVOList);
								selectedfilterVO
										.setCategoryFilterVO(assignedList);
								Iterator iterator = assignedList.iterator();
								while (iterator.hasNext()) {
									SelectItem si = (SelectItem) iterator
											.next();
									removeItem(si.getValue().toString(),
											filterDataBean
													.getTempAvailableList());
								}
							}
							filterDataBean.setFilterVO(selectedfilterVO);
						}
						if (filterVO.getFilterType().equals(
								ContactManagementConstants.FILTER_TYPE_CASE)) {
							CaseFilter caseFilter = null;
							caseFilter = (CaseFilter) filterDOConvertor
									.convertCaseFilterVOToDO(filterVO);
							try {
								
								contactMaintenanceDelegate
										.updateCaseFilter(caseFilter);
							} catch (FilterUpdateBusinessException e) {
								if(logger.isErrorEnabled())
								{
								logger.error(e.getMessage(), e);
								}
								sucessMsgFlag = true;
								
								if(e.getErrorCode() != null && 
										ContactManagementConstants.CATORCASETYPE_ASCTO_EDMS_ERR_MSG
											.equalsIgnoreCase(e.getErrorCode()))
								{
									setErrorMessage(ContactManagementConstants.CATORCASETYPE_ASCTO_EDMS_ERR_MSG, new Object[] {}, 
											ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, null);
								}else{
									setErrorMessage(
										EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
										new Object[] {},
										MessageUtil.ENTMESSAGES_BUNDLE, null);
									if(logger.isDebugEnabled())
									{
								logger
										.debug("FilterUpdateBusinessException occured on FiterControllerBean.updateFilter() ELSEELSE :"
												+ e);
									}
								}
							}
							FilterVO selectedfilterVO = getUpdateFilterVO(caseFilter
									.getFilterName(),"CASEFILTER");
							List caseTypesFilterVOList = new ArrayList();
							filterDataBean
									.setTempAvailableList(getAvailableCaseTypes());
							if(logger.isDebugEnabled())
							{
							logger
									.debug("Available casetypeList size ELSEELSE:"
											+ filterDataBean
													.getTempAvailableList()
													.size());
							}
							caseTypesFilterVOList = selectedfilterVO
									.getCaseTypeFilterVO();
							if (caseTypesFilterVOList != null) {
								List assignedList = getAssignedCaseTypes(caseTypesFilterVOList);
								selectedfilterVO
										.setCaseTypeFilterVO(assignedList);
								Iterator iterator = assignedList.iterator();
								while (iterator.hasNext()) {
									SelectItem si = (SelectItem) iterator
											.next();
									removeItem(si.getValue().toString(),
											filterDataBean
													.getTempAvailableList());
								}
							}
							filterDataBean.setFilterVO(selectedfilterVO);
						}

						if (sucessMsgFlag) {
							filterDataBean.setShowSucessMessage(false);
						} else {
							filterDataBean.setShowSucessMessage(true);
						}
					//}
				//}
			}
		}
	return null;
}

	/**
	 * This operation is used to delete ContactManagement Filter Record.
	 * 
	 * @return String.
	 */
	public String deleteFilter() {

		
		int index = filterDataBean.getSelectedFilterIndex();
		FilterVO filterVO = filterDataBean.getFilterVO();
		if (filterVO != null
				&& isFilterAssignedToUser(filterVO.getFilterName(), filterVO
						.getFilterType())) {
			resetEditedFilter();
			setErrorMessage(
					ContactManagementConstants.CANNOT_DELETE_FILTER_WITH_ASSOCIAIONS,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					null);

		}

		else {
			FilterDOConvertor filterDOConvertor = new FilterDOConvertor();
			boolean deleted = false;
			//FindBug Issue Resloved
			//if (filterVO.getFilterType().equals(
			  if (filterVO != null && filterVO.getFilterType().equals(
					ContactManagementConstants.FILTER_TYPE_CR)) {
				CorrespondenceFilter correspondenceFilter = filterDOConvertor
						.convertCorrespondenceFilterVOToDO(filterVO);
				ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
				try {
					deleted = contactMaintenanceDelegate.deleteFilter(
							correspondenceFilter).booleanValue();
				} catch (FilterDeleteBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
				}
			}
			  //FindBug Issue Resloved
			 // if (filterVO.getFilterType().equals(
			if (filterVO != null && filterVO.getFilterType().equals(
					ContactManagementConstants.FILTER_TYPE_CASE)) {
				CaseFilter caseFilter = filterDOConvertor
						.convertCaseFilterVOToDO(filterVO);
				ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
				try {
					
					deleted = contactMaintenanceDelegate.deleteCaseFilter(
							caseFilter).booleanValue();
				} catch (FilterDeleteBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
				}
			}

			if (deleted) {

				filterDataBean.getFilterVOArrayList().remove(index);

				if (filterDataBean.getFilterVOArrayList().size() == 0) {
					filterDataBean.setNoData(true);
				}
				filterDataBean.setFilterVO(new FilterVO());
				filterDataBean.setShowEditFilterBar(Boolean.FALSE);
				filterDataBean.setShowFilterDetails(Boolean.FALSE);
				filterDataBean.setShowNewFilterBar(Boolean.FALSE);
				// DEFECT ID : ESPRD00223583
				setErrorMessage(
						ContactManagementConstants.DEL_MSG,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
						null);
			} 
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to get Contact Management Filter Record details
	 * based on the primary key value 'FilterName '.
	 * 
	 * @return String.
	 */
	public String getFilterDetails() {
		filterDataBean.getTempAvailableList().clear();
		filterDataBean.setShowSucessMessage(false);
		List assignedList = new ArrayList();
		FilterVO filterVO = null;//getSelectedFilterVO();
		FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String filterName = (String) map.get("filterName");
        String filterType = (String) map.get("filterType");
        String indexCode = (String) map.get("rowIndex");
       // commented  for the  defect ESPRD00796737 - to get the correct index for RESET in edit filter
        // int index = Integer.parseInt(indexCode);
     // Added  for the  defect ESPRD00796737 - to set the correct index for RESET in edit filter - START
        if(indexCode != null){
        	filterDataBean.setMaintainfilterIndex(Integer.parseInt(indexCode));
        }
        // Added  for the  defect ESPRD00796737 - to set the correct index for RESET in edit filter - END
        if(filterName == null || StringUtils.isEmpty(filterName.trim()))
        {
        	
        	filterName = filterDataBean.getFilterVO().getFilterName();
        	
            filterType = filterDataBean.getFilterType();
            if("CR".equalsIgnoreCase(filterType))
            {
            	filterType = ContactManagementConstants.FILTER_TYPE_CR;
            }else{
            	filterType = ContactManagementConstants.FILTER_TYPE_CASE;
            }
			
        }
        EnterpriseBaseDomain filterDetails = null;
        
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try 
		{
			filterDetails = contactMaintenanceDelegate.getFilterDetails(filterName,filterType);
		} 
		catch (FilterFetchBusinessException e) 
		{
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
			
		}
		if(filterDetails != null)
		{
			FilterDOConvertor filterDOConvertor = new FilterDOConvertor();
			if(ContactManagementConstants.FILTER_TYPE_CR.equalsIgnoreCase(filterType))
			{
				filterVO = filterDOConvertor.convertCrFilterDOToVO((CorrespondenceFilter) filterDetails);
				List categorieFilterVOList = new ArrayList();
				filterDataBean.setTempAvailableList(getAvailableCategories());
				categorieFilterVOList = filterVO.getCategoryFilterVO();
				if (categorieFilterVOList != null) {
					assignedList = getAssignedCategories(categorieFilterVOList);
					filterVO.setCategoryFilterVO(assignedList);
					Iterator iterator = assignedList.iterator();
					while (iterator.hasNext()) {
						SelectItem si = (SelectItem) iterator.next();
						removeItem(si.getValue().toString(), filterDataBean
								.getTempAvailableList());
					}
				}
				filterDataBean.setFilterType(ContactManagementConstants.FILTER_TYPE_CR);
				filterDataBean.setFilterScope(filterVO.getFilterScope());
				filterDataBean.setShowCategoryList(Boolean.TRUE);
				filterDataBean.setShowCaseList(Boolean.FALSE);
				// Modified  for the  defect ESPRD00796737 - to set the correct index for RESET in edit filter 
				filterDataBean.setSelectedFilterIndex(filterDataBean.getMaintainfilterIndex());  
			
			}else
			{
				filterVO = filterDOConvertor.convertCaseFilterDOToVO((CaseFilter) filterDetails);
				List caseTypesFilterVOList = new ArrayList();
				filterDataBean.setTempAvailableList(getAvailableCaseTypes());
				if(logger.isDebugEnabled())
				{
				logger.debug("Available casetypeList size :"
						+ filterDataBean.getTempAvailableList().size());
				}
				caseTypesFilterVOList = filterVO.getCaseTypeFilterVO();
				if (caseTypesFilterVOList != null) 
				{
					assignedList = getAssignedCaseTypes(caseTypesFilterVOList);
					filterVO.setCaseTypeFilterVO(assignedList);
					Iterator iterator = assignedList.iterator();
					while (iterator.hasNext()) 
					{
						SelectItem si = (SelectItem) iterator.next();
						removeItem(si.getValue().toString(), filterDataBean
								.getTempAvailableList());
					}
				}
				filterDataBean.setFilterType(ContactManagementConstants.FILTER_TYPE_CASE);
				filterDataBean.setFilterScope(filterVO.getFilterScope());
				filterDataBean.setShowCategoryList(Boolean.FALSE);
				filterDataBean.setShowCaseList(Boolean.TRUE);
				// Modified  for the  defect ESPRD00796737 - to set the correct index for RESET in edit filter
				filterDataBean.setSelectedFilterIndex(filterDataBean.getMaintainfilterIndex()); 
			
			}
		}
		
		filterDataBean.setAssignedList(assignedList);
		filterDataBean.setFilterVO(filterVO);
		filterDataBean.setShowFilterDetails(Boolean.TRUE);
		filterDataBean.setShowEditFilterBar(Boolean.TRUE);
		filterDataBean.setShowNewFilterBar(Boolean.FALSE);
		filterDataBean.setTempFilterVO(filterVO);
		filterDataBean.setEditFilter(Boolean.TRUE);
		filterDataBean.setAuditLogFlagforFilter(false);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to get all the Filters sorted on certain column
	 * and order.
	 * 
	 * @param event :
	 *            ActionEvent object.
	 *  
	 */
	public String getAllSortedFilters(ActionEvent event) {

		

		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);
		
		//for fix to display alter sort images
		getFilterDataBean().setImageRender(event.getComponent().getId());

		FilterSearchCriteriaVO filterSearchCriteriaVO = getFilterDataBean()
				.getFilterSearchCriteriaVO();
		getFilterDataBean().setFilterstartIndex(0);
		
		filterSearchCriteriaVO.setSortColumn(sortColumn);
		

		/* Sort By Active */

		if (ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
			

			filterSearchCriteriaVO.setAscending(true);

		}

		if (ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
			

			filterSearchCriteriaVO.setAscending(false);

		}

		getFilterDataBean().setFilterSearchCriteriaVO(filterSearchCriteriaVO);
		
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This operation is used to get the value object of the selected row.
	 * 
	 * @return FilterVO.
	 */
	public FilterVO getSelectedFilterVO() {
		//FindBug Issue Resloved
		//FilterVO filterVO = new FilterVO();
		FilterVO filterVO = null;
		
		/*FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("rowIndex");
		int rowIndex = Integer.parseInt(indexCode);*/
		
		int rowIndex = filterDataBean.getSelectedFilterIndex();
		
		if (filterDataBean.getCancelFilter().equals(Boolean.FALSE)) 
		{
			filterDataBean.setSelectedFilterIndex(rowIndex);
		} else {
			rowIndex = filterDataBean.getSelectedFilterIndex();
		}
		filterVO = (FilterVO) filterDataBean.getFilterVOArrayList().get(
				rowIndex);
		filterDataBean.setCancelFilter(Boolean.FALSE);

		return filterVO;
	}
	
	public FilterVO getUpdateFilterVO(String filterName, String FilterType) {

		FilterVO filterVO = new FilterVO();
		EnterpriseBaseDomain filterDetails = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try 
		{
			filterDetails = contactMaintenanceDelegate.getFilterDetails(filterName,FilterType);
		} 
		catch (FilterFetchBusinessException e) 
		{
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
			
		}
		if(filterDetails != null)
		{
			FilterDOConvertor filterDOConvertor = new FilterDOConvertor();
			if(ContactManagementConstants.FILTER_TYPE_CR.equalsIgnoreCase(FilterType))
			{
				filterVO = filterDOConvertor.convertCrFilterDOToVO((CorrespondenceFilter) filterDetails);
			}else{
				filterVO = filterDOConvertor.convertCaseFilterDOToVO((CaseFilter) filterDetails);
			}
		
		
		}
		filterDataBean.setCancelFilter(Boolean.FALSE);

		return filterVO;
	}

	/**
	 * This operation is used to get the assigned categories for the given
	 * filter.
	 * 
	 * @param categorieFilterVOList
	 *            The categorieFilterVOList to set.
	 * @return List.
	 */
	public List getAssignedCategories(List categorieFilterVOList) 
	{
		List assignedCategories = new ArrayList();
		Iterator iterator = categorieFilterVOList.iterator();
		CategoryFilterVO categoryFilter = null;
		SelectItem si = null;
		String categoryDescription = null;
		Long categorySK;
		while (iterator.hasNext()) 
		{
			categoryFilter = (CategoryFilterVO) iterator.next();
			categorySK = categoryFilter.getCategorySK();
			categoryDescription = getCategoryDescription(categorySK);
			
				if (categoryDescription != null)
				{
					si = new SelectItem();
					si.setLabel(categoryDescription);
					si.setValue(categorySK.toString());
					if(!si.getLabel().toString().equalsIgnoreCase(""))
					{
						assignedCategories.add(si);
					}
				}
		}         
		return assignedCategories;
	}

	/**
	 * This operation is used to get the description for the category.
	 * 
	 * @param categorySK
	 *            The categorySK to set.
	 * @return String.
	 */
	private String getCategoryDescription(Long categorySK) {
		List availableList1 = filterDataBean.getTempAvailableList();
		Iterator iterator = availableList1.iterator();
		SelectItem si = null;
		String str = ContactManagementConstants.EMPTY_STRING;
		while (iterator.hasNext()) {
			si = (SelectItem) iterator.next();

			if (si.getValue().equals(categorySK.toString())) {
				str = si.getLabel();
			}
		}
		return str;
	}

	/**
	 * This operation is used to get the assigned case types for the given
	 * filter.
	 * 
	 * @param caseTypesFilterVOList
	 *            The caseTypesFilterVOList to set.
	 * @return List.
	 */
	public List getAssignedCaseTypes(List caseTypesFilterVOList) {
		

		List assignedCaseTypes = new ArrayList();

		Iterator iterator = caseTypesFilterVOList.iterator();
		CaseFilterVO caseFilter = null;
		SelectItem si = null;
		String caseTypeDescription = null;
		Long caseTypeSK;
		while (iterator.hasNext()) {
			caseFilter = (CaseFilterVO) iterator.next();
			caseTypeSK = caseFilter.getCaseTypeSK();
			caseTypeDescription = getCaseTypeDescription(caseTypeSK);
			if (caseTypeDescription != null) {
				si = new SelectItem();
				si.setLabel(caseTypeDescription);
				si.setValue(caseTypeSK.toString());
				assignedCaseTypes.add(si);
			}
		}

		return assignedCaseTypes;
	}

	/**
	 * This operation is used to get the description for the caseType.
	 * 
	 * @param caseTypeSK
	 *            The caseTypeSK to set.
	 * @return String.
	 */
	private String getCaseTypeDescription(Long caseTypeSK) {
		List availableList1 = getAvailableCaseTypes();
		Iterator iterator = availableList1.iterator();
		SelectItem si = null;
		String str = ContactManagementConstants.EMPTY_STRING;
		while (iterator.hasNext()) {
			si = (SelectItem) iterator.next();

			if (si.getValue().equals(caseTypeSK.toString())) {
				str = si.getLabel();
			}
		}
		return str;
	}

	/**
	 * Add filter.
	 * 
	 * @return String.
	 */
	public String addFilter() {

		filterDataBean.setShowSucessMessage(false);
		FilterVO filterVO = filterDataBean.getFilterVO();
		filterDataBean.setShowNewFilterBar(Boolean.TRUE);
		filterDataBean.setShowEditFilterBar(Boolean.FALSE);
		filterDataBean.setShowFilterDetails(Boolean.TRUE);
		filterDataBean.setAssignedList(new ArrayList());
		filterVO.setCategoryFilterVO(new ArrayList());
		filterDataBean.setFilterVO(filterVO);
		filterDataBean.setTempAvailableList(getAvailableCategories());
		filterDataBean.setEditFilter(Boolean.FALSE);
		filterDataBean.setShowCategoryList(Boolean.FALSE);
		filterDataBean.setShowCaseList(Boolean.FALSE);
		filterDataBean.setAuditLogFlagforFilter(false);
		resetFilter();

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Method used to change the filter Type and the page accordingly.
	 * 
	 * @param vce
	 *            The vce to set.
	 * @return String.
	 */
	public String changeFilterType(ValueChangeEvent vce) {
		

		filterDataBean.setShowSucessMessage(false);
		filterDataBean.getTempAvailableList().clear();
		
		
		//Code added below for fixing the defect ESPRD00778246 on 10-04-2012
		
		List caseTypeFilterVO = filterDataBean.getFilterVO().getCaseTypeFilterVO();
		if(vce.getNewValue()!= null)
		{
			if (vce.getNewValue().toString().equals(
					ContactManagementConstants.FILTER_TYPE_CR)) {
				filterDataBean.setShowCategoryList(Boolean.TRUE);
				filterDataBean.setShowCaseList(Boolean.FALSE);
				filterDataBean.setTempAvailableList(getAvailableCategories());
			} else if (vce.getNewValue().toString().equals(
					ContactManagementConstants.FILTER_TYPE_CASE)) {
				filterDataBean.setShowCategoryList(Boolean.FALSE);
				filterDataBean.setShowCaseList(Boolean.TRUE);
				filterDataBean.setTempAvailableList(getAvailableCaseTypes());
				
				//Code added below for fixing the defect ESPRD00778246 on 10-04-2012
				
				if(filterDataBean.getShowNewFilterBar() == true )
				{
					caseTypeFilterVO.clear();
				}
				else
				{
					filterDataBean.getFilterVO().setCaseTypeFilterVO(caseTypeFilterVO);
				}
			} else {
				filterDataBean.setShowCategoryList(Boolean.FALSE);
				filterDataBean.setShowCaseList(Boolean.FALSE);
				filterDataBean.getTempAvailableList().clear();
			}
		}else
		{
			String filterType= filterDataBean.getFilterType();
		 

			if (filterType.equalsIgnoreCase(
					ContactManagementConstants.FILTER_TYPE_CR)) {
				filterDataBean.setShowCategoryList(Boolean.TRUE);
				filterDataBean.setShowCaseList(Boolean.FALSE);
				filterDataBean.setTempAvailableList(getAvailableCategories());
				filterDataBean.getFilterVO().setFilterType(filterType);
			} else if (filterType.equalsIgnoreCase(
					ContactManagementConstants.FILTER_TYPE_CASE)) {
				filterDataBean.setShowCategoryList(Boolean.FALSE);
				filterDataBean.setShowCaseList(Boolean.TRUE);
				filterDataBean.setTempAvailableList(getAvailableCaseTypes());
				filterDataBean.getFilterVO().setFilterType(filterType);
			} else {
				filterDataBean.setShowCategoryList(Boolean.FALSE);
				filterDataBean.setShowCaseList(Boolean.FALSE);
				filterDataBean.getTempAvailableList().clear();
			}
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Reset filter.
	 * 
	 * @return String.
	 */
	public String resetFilter() {

		long entryTime = System.currentTimeMillis();
		

		filterDataBean.setShowSucessMessage(false);
		FilterVO filterVO = filterDataBean.getFilterVO();
		if (filterDataBean.getEditFilter().equals(Boolean.FALSE)) {
			filterVO.setDescription(null);
			filterVO.setFilterName(null);
			filterVO.setFilterScope(null);
			filterVO.setFilterType(null);
			filterVO.setFilterTypeDataItem(null);
			filterDataBean.setFilterVO(filterVO);
			filterVO.setCategoryFilterVO(new ArrayList());
			filterDataBean.setFilterVO(filterVO);
			filterDataBean.setShowCategoryList(Boolean.FALSE);
			filterDataBean.setShowCaseList(Boolean.FALSE);
		} else {
			filterDataBean.setFilterVO(filterDataBean.getTempFilterVO());
		}

		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
		logger.info("FilterControllerBean" + "#" + "resetFilter" + "#"
				+ (exitTime - entryTime));
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Reset Edited filter.
	 * 
	 * @return String.
	 */
	public String resetEditedFilter() {

		long entryTime = System.currentTimeMillis();
		
		filterDataBean.setShowSucessMessage(false);
		filterDataBean.setCancelFilter(Boolean.TRUE);
		getFilterDetails();
		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
		logger.info("FilterControllerBean" + "#" + "resetFilter" + "#"
				+ (exitTime - entryTime));
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * CancelFilter.
	 */
	public void cancelFilter() {

		long entryTime = System.currentTimeMillis();
		
		filterDataBean.setShowSucessMessage(false);
		filterDataBean.setShowNewFilterBar(Boolean.FALSE);
		filterDataBean.setShowEditFilterBar(Boolean.FALSE);
		filterDataBean.setShowFilterDetails(Boolean.FALSE);
		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
		logger.info("FilterDataBean" + "#" + "cancelFilter" + "#"
				+ (exitTime - entryTime));
		}

	}

	/**
	 * This method get FilterDataBean object.
	 * 
	 * @return FilterDataBean.
	 */
	public final FilterDataBean getFilterDataBean() {

		//	long entryTime = System.currentTimeMillis();
		FacesContext fc = FacesContext.getCurrentInstance();
		FilterDataBean filterDataBean = (FilterDataBean) fc.getApplication()
				.createValueBinding(
						"#{" + ContactManagementConstants.FILTER_DATA_BEAN
								+ "}").getValue(fc);
		//	long exitTime = System.currentTimeMillis();

		return filterDataBean;
	}

	/**
	 * This method returns all the available categories.
	 * 
	 * @return List.
	 */
	public List getAvailableCategories() {


		List listOfCategories = new ArrayList();

		//CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor(); //Find bug issue
		/** Calls Contact Maintainence Delegate to fetch all Categories */
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try
        {
            listOfCategories = contactMaintenanceDelegate.getAllCategoriesList();
        }
        catch (CategoryFetchBusinessException e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }
		List categoryVOList = new ArrayList();
		
		if (listOfCategories != null)
        {
        	
        	for (int i = 0; i <listOfCategories.size(); i++)
            {
            	Object[] categoyDetails = (Object[]) listOfCategories.get(i);
            	categoryVOList.add(new SelectItem(categoyDetails[0].toString(), categoyDetails[1].toString()));
            }
        }
		return categoryVOList;
	}

	/**
	 * This method is used to get the User ID.
	 * 
	 * @return String : User ID
	 */
	/*
	 * public String getUserID() { logger.info("getting UserID is started");
	 * String userId = "specialist006"; HttpServletRequest renderrequest =
	 * (HttpServletRequest) FacesContext
	 * .getCurrentInstance().getExternalContext().getRequest();
	 * HttpServletResponse renderresponse = null; EnterpriseUserProfile eup =
	 * getUserData(renderrequest, renderresponse); logger.debug("Enterprise User
	 * profile is $$ " + eup); if (eup != null && !eup.getUserId().equals("")) {
	 * userId = eup.getUserId();
	 * 
	 * logger.debug("From Enterprise User ID is $$ " + userId); } else {
	 * logger.debug("Enterprise User Profile is null"); } return userId; }
	 */

	/**
	 * This method returns all the available case types.
	 * 
	 * @return List.
	 */
	public List getAvailableCaseTypes() {

		long entryTime = System.currentTimeMillis();

		List caseTypeDOList = new ArrayList();

		CaseTypeDOConverter caseTypeDOConvertor = new CaseTypeDOConverter();
		/** Calls Contact Maintainence Delegate to fetch all Categories */
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try {
			caseTypeDOList = contactMaintenanceDelegate.getActiveCaseTypes();
			if (!caseTypeDOList.isEmpty()) {
				if(logger.isDebugEnabled())
				{
				logger
						.debug("caseTypeDOList size is :"
								+ caseTypeDOList.size());
				}

			}
		} catch (CaseTypeFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		/** Calls Converter class to get list of categoryVO */
		
		Iterator iterator = caseTypeDOList.iterator();
		CaseType caseType = null;
		CaseTypeVO caseTypeVO = null;
		List caseTypeVOList = new ArrayList();
		while (iterator.hasNext()) {

			caseType = (CaseType) iterator.next();
			caseTypeVO = caseTypeDOConvertor.convertCaseTypeDOToVO(caseType);
			if (caseTypeVO.getShortDesc() != null) {
				SelectItem si = new SelectItem();
				si.setLabel(caseTypeVO.getLongDesc());
				si.setValue(caseTypeVO.getCaseTypeSK().toString());
				caseTypeVOList.add(si);
			}
		}

		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
		logger.info("FilterControllerBean" + "#" + "getAvailableCaseTypes"
				+ "#" + (exitTime - entryTime));
		}

		return caseTypeVOList;
	}

	/**
	 * Moves the selcted List.
	 * 
	 * @return String.
	 */
	public String moveSelectedList() {

		
		filterDataBean.setShowSucessMessage(false);
		moveSelected(filterDataBean.getSelectedList(), filterDataBean
				.getTempAvailableList(), filterDataBean.getFilterVO()
				.getCategoryFilterVO());

		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * Moves the selcted List.
	 * 
	 * @return String.
	 */
	public String moveSelectedCaseList() {

		
		filterDataBean.setShowSucessMessage(false);
		moveSelected(filterDataBean.getSelectedList(), filterDataBean
				.getTempAvailableList(), filterDataBean.getFilterVO()
				.getCaseTypeFilterVO());

		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This method is used to move the selected items to a list.
	 * 
	 * @param selectedItems
	 *            The selectedItems to set.
	 * @param availableList
	 *            The availableList to set.
	 * @param selectedList
	 *            The selectedList to set.
	 * @return String.
	 */
	private String moveSelected(List selectedItems, List availableList,
			List selectedList) {
		
		filterDataBean.setShowSucessMessage(false);
		if (selectedItems != null) {
			for (Iterator iter = selectedItems.iterator(); iter.hasNext();) {
				selectedList
						.add(removeItem((String) iter.next(), availableList));

			}
		}

		filterDataBean.setAssignedList(selectedList);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Method to remove an item from the list.
	 * 
	 * @param value
	 *            The value to set.
	 * @param filterList
	 *            The filterList to set.
	 * @return SelectItem.
	 */
	private SelectItem removeItem(String value, List filterList) {

		
		filterDataBean.setShowSucessMessage(false);
		SelectItem result = null;
		int size = filterList.size();
		for (int i = 0; i < size; i++) {
			SelectItem item = (SelectItem) filterList.get(i);
			if (value.equals(item.getValue())) {
				result = (SelectItem) filterList.remove(i);
				break;
			}
		}

		return result;
	}

	/**
	 * Remove the selected List.
	 * 
	 * @return String.
	 */
	public String removeSelectedList() {

		
		filterDataBean.setShowSucessMessage(false);
		removeSelected(filterDataBean.getRemovedList(), filterDataBean
				.getTempAvailableList(), filterDataBean.getFilterVO()
				.getCategoryFilterVO());

		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * Remove the selected List.
	 * 
	 * @return String.
	 */
	public String removeSelectedCaseList() {
		
		filterDataBean.setShowSucessMessage(false);
		removeSelected(filterDataBean.getRemovedList(), filterDataBean
				.getTempAvailableList(), filterDataBean.getFilterVO()
				.getCaseTypeFilterVO());

		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * Remove selected.
	 * 
	 * @param removedItems
	 *            The removedItems to set.
	 * @param availableList
	 *            The availableList to set.
	 * @param selectedList
	 *            The selectedList to set.
	 * @return String.
	 */
	private String removeSelected(List removedItems, List availableList,
			List selectedList) {
		
		filterDataBean.setShowSucessMessage(false);
		if (selectedList != null) {
			for (Iterator iter = removedItems.iterator(); iter.hasNext();) {
				availableList
						.add(removeItem((String) iter.next(), selectedList));

			}
		}
		return ContactManagementConstants.RENDER_SUCCESS;
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
		} else {
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
	 * This operation is used to load the audit logs
	 * 
	 * @return
	 */
	public String showAuditHistory() {

		

		GlobalAuditsDelegate audit;
		//FindBug Issue Resloved
		//CorrespondenceFilter correspondenceFilter = new CorrespondenceFilter();
		CorrespondenceFilter correspondenceFilter = null;
		FilterDataBean filterDataBean = getFilterDataBean();

		try {
			
			audit = new GlobalAuditsDelegate();
			FilterDOConvertor filterDOConvertor = new FilterDOConvertor();

			/* Gets Child Domain Object */

			correspondenceFilter = filterDOConvertor
					.convertCorrespondenceFilterVOToDO(filterDataBean
							.getFilterVO());

			
			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(correspondenceFilter, 0,
							ContactManagementConstants.NO_OF_RECORD_TO_FETCH);

			

			filterDataBean.setFilterAuditHistoryList(enterpriseResultVO
					.getSearchResults());
			filterDataBean.setFilterAuditRender(true);
			/* Added new for Expand */
			filterDataBean.setAuditOpen(true);
			
		} catch (GlobalAuditDataException e) {
			e.printStackTrace();
			
			
		} catch (GlobalAuditsBusinessException e) {
			e.printStackTrace();
			
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}

	public String showColumnChange() {
		//FindBug Issue Resolved
		//long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
			filterDataBean.setFilterAuditRender(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			filterDataBean.setSelectedAuditLog(auditLog);
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	public String closeColumnChange() {
		getFilterDataBean().setFilterAuditRender(false);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @return Returns the invokeShowList.
	 */
	/*public boolean isInvokeShowList() {
		logger.debug("public boolean isInvokeShowList()");
		return invokeShowList;
	}*/
	
	public List getOriginalList()
	{List assignedList = new ArrayList();
	FilterVO filterVO = getSelctedFilter();
	//FindBug Issue Resloved
	//List categorieFilterVOList = new ArrayList();
	List categorieFilterVOList =null;
	//FindBug Issue Resloved
	//List casefilterVOList = new ArrayList();
	List casefilterVOList =null;
	if (filterVO.getFilterType().equalsIgnoreCase(
			ContactManagementConstants.FILTER_TYPE_CR)) {
		categorieFilterVOList = filterVO.getCategoryFilterVO();
		if (categorieFilterVOList != null) {
			assignedList = getAssignedCategories(categorieFilterVOList);
		}
	}

	if (filterVO.getFilterType().equalsIgnoreCase(
			ContactManagementConstants.FILTER_TYPE_CASE)) {
		
		
		
		casefilterVOList = filterVO.getCaseTypeFilterVO();
		if (casefilterVOList != null) {
			assignedList = getAssignedCaseTypes(casefilterVOList);
		}
	}

	return assignedList;
	}
	
	public FilterVO getSelctedFilter()
	{
		//FindBug Issue Resloved
		//FilterVO filterVO = new FilterVO();
		FilterVO filterVO =null;
		List tt = filterDataBean.getFilterVOArrayList();
		filterVO = (FilterVO) tt.get(filterDataBean.getSelectedFilterIndex());
		return filterVO;
	}
	
	/**
	 * @return Returns the controlRequiredFOrDelete.
	 */
	public boolean isControlRequiredFOrDelete() {
		return controlRequiredFOrDelete;
	}
	/**
	 * @param controlRequiredFOrDelete The controlRequiredFOrDelete to set.
	 */
	public void setControlRequiredFOrDelete(boolean controlRequiredFOrDelete) {
		this.controlRequiredFOrDelete = controlRequiredFOrDelete;
	}
	public String doAuditFunctions()
	{
		
		FilterDataBean filterDataBean  = getFilterDataBean();
		/* for defect ESPRD00668865 fix
		 *List filterAudList = filterDataBean.getFilterArrayList();
		
		
		try
		{
			if(filterAudList!=null && !(filterAudList.isEmpty()))
			{
				Iterator auditKeyIt = filterAudList.iterator();
				if(auditKeyIt!=null)
				{
					List filterEditList = new ArrayList();
					
					
					 filterEditList.add(createAuditableFeild("Filter Description","filterName"));
					 filterEditList.add(createAuditableFeild("Action","filterScopeCode"));
					 filterEditList.add(createAuditableFeild("CR Category","categorySK"));
					 filterEditList.add(createAuditableFeild("Case Types","caseTypeSK"));
				    
					 FilterVO filterVO= filterDataBean.getFilterVO();
					 filterVO.setFilterType(filterDataBean.getFilterType());
					 filterVO.setFilterScope(filterDataBean.getFilterScope());
					
					if(filterVO.getAuditKeyList()!=null && !(filterVO.getAuditKeyList().isEmpty()))
					{
						logger.debug("FILTER ==AuditKeyList size::"+filterVO.getAuditKeyList().size());
						AuditDataFilter.filterAuditKeys(filterEditList,filterVO);
						logger.debug("FILTER ======filterVO====After size::==="+filterVO.getAuditKeyList().size());
					}
					else
					{
						logger
								.debug("FILTER ======filterVO====Before Filter Empty===");
					}
				}
			}
			
	}catch (Exception e) 
		{
			// TODO: handle exception
		  logger.debug("In Exception FIlterrrrr");
			e.printStackTrace();
		}*/
		filterDataBean.setAuditLogFlagforFilter(true);
		return  "";
	
	}
	
	private AuditableField createAuditableFeild(String feildName,String domainAttributeName)
	{
		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);
		return auditableField;

	}

	/**
	 * @return Returns the loadUserPermission.
	 */
	public String getLoadUserPermission() {
		getUserPermission();
		return loadUserPermission;
	}
	/**
	 * @param loadUserPermission The loadUserPermission to set.
	 */
	public void setLoadUserPermission(String loadUserPermission) {
		this.loadUserPermission = loadUserPermission;
	}
	
	private List getFltrsForDplCheck(String filterType, String filterScope, int size)
	{
		List filterList = null;
		List filterVOList = new ArrayList();
		try{
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			filterList = contactMaintenanceDelegate.getFltrsForDplCheck(filterType, filterScope, size);
		}catch (Exception exception) {
			// TODO: handle exception
			if(logger.isErrorEnabled())
			{
			logger.error(exception.getMessage(), exception);
			}
		}
		if(filterList != null)
		{
			FilterDOConvertor filterDOConvertor = new FilterDOConvertor();
			Iterator itr = filterList.iterator();
			if(ContactManagementConstants.FILTER_TYPE_CR.equalsIgnoreCase(filterType))
			{
				while(itr.hasNext())
				{
					CorrespondenceFilter crFilter =  (CorrespondenceFilter) itr.next();
					FilterVO filterVO = new FilterVO();
		            filterVO.setDescription(crFilter.getDescription());
		            filterVO.setFilterName(crFilter.getFilterName());
		            filterVO.setFilterScope(crFilter.getFilterScopeCode());
			        filterVO.setFilterType(ContactManagementConstants.FILTER_TYPE_CR);
			        Set selectedCategories = crFilter.getCatFilterXrefs();
			        filterVO.setCategoryFilterVO(filterDOConvertor.getCategoryFilterVOList(selectedCategories));
			        filterVOList.add(filterVO);
				}
			}else if(ContactManagementConstants.FILTER_TYPE_CASE.equalsIgnoreCase(filterType))
			{
				while(itr.hasNext())
				{
					CaseFilter caseFilter =  (CaseFilter) itr.next();
					FilterVO filterVO = new FilterVO();
					filterVO.setDescription(caseFilter.getDescription());
		            filterVO.setFilterName(caseFilter.getFilterName());
		            filterVO.setFilterScope(caseFilter.getFilterScopeCode());
			        filterVO.setFilterType(ContactManagementConstants.FILTER_TYPE_CASE);
			        Set selectedCaseTyppes = caseFilter.getCaseFilterXrefs();
			        filterVO.setCaseTypeFilterVO(filterDOConvertor.getCaseFilterVOList(selectedCaseTyppes));
			        filterVOList.add(filterVO);
				}
			}
		}
		return filterVOList;
	}
}
