/*
 * Created on Oct 30, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;


import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
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
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CategorySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CategoryDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CorrespondenceDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.Executor;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;

//added by ics
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;

//end

/**
 * @author mohamabb TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * @author prashag TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchCorrespondenceDataBean extends EnterpriseBaseDataBean {

	
	/**
	 * List to hold listOfCategoryDOs
	 */
	private List listOfCategoryDOs = new ArrayList();

	/**
	 * to make onload cursor focus.
	 */
	private String cursorFocusId;

	/**
	 * @return Returns the cursorFocusId.
	 */
	public String getCursorFocusId() {
		return cursorFocusId;
	}

	/**
	 * @param cursorFocusId
	 *            The cursorFocusId to set.
	 */
	public void setCursorFocusId(String cursorFocusId) {
		this.cursorFocusId = cursorFocusId;
	}

	/**
	 * holds SearchAssocCR
	 */
	private boolean SearchAssocCR = false;

	/**
	 * List to hold values of Dropdown Entity Type.
	 */
	private List entityTypeVVList = new ArrayList();

	/**
	 * List to hold values of Dropdown Provider Type.
	 */
	private List provTypeVVList = new ArrayList();

	/**
	 * List to hold values of Dropdown Status.
	 */
	private List statusVVList = new ArrayList();

	/**
	 * List to hold values of Dropdown Priority.
	 */
	private List priorityVVList = new ArrayList();

	/**
	 * List to hold values of Dropdown Resolution.
	 */
	private List resolnVVList = new ArrayList();

	/**
	 * List to hold values of Dropdown Subject.
	 */
	private List subjectVVList = new ArrayList();

	/**
	 * List to hold values of Dropdown Source.
	 */
	private List sourceVVList = new ArrayList();

	/**
	 * List to hold values of Dropdown Category.
	 */
	private List categoryList = new ArrayList();

	/**
	 * List to hold values of Dropdown catListToBeSentInSearchCritVO. It Hold
	 * list of cat assig to user same as categoryList Butwhile we sent the list
	 * inSearch Criteraia vo if no cat selected we use this one.
	 */
	private List catListToBeSentInSearchCritVO = new ArrayList();

	/**
	 * List to hold values of Dropdown Reporting Unit.
	 */
	private List listOfRepUnits = new ArrayList();

	/**
	 * List to hold values of Dropdown Business Unit.
	 */
	private List listOfRefBusUnits = new ArrayList();

	/**
	 * List to hold values of Dropdown Departments.
	 */
	private List listOfRefDeptUnits = new ArrayList();

	/**
	 * List to hold values of Dropdown CreatedBy.
	 */
	private List createdByList = new ArrayList();

	/**
	 * List to hold values of Dropdown CreatedBy.
	 */
	private List assignedToList = new ArrayList();

	/**
	 * List to hold temporary values of Dropdown CreatedBy.
	 */
	private List tempAssignedToList = new ArrayList();

	/**
	 * List to hold values of Dropdown Line Of Business.
	 */
	private List lobVVList = new ArrayList();

	/**
	 * List to hold existingResults results values.
	 */
	private List existingResults = new ArrayList();

	/**
	 * List to hold search results values.
	 */
	private List searchResults = new ArrayList();

	/**
	 * List to hold search results values.
	 */
	private List associatedSearchResults = new ArrayList();

	/** Holds class name. */
	private String advancedOptionsHidden = "plus";

	/** Holds searchCorrespondenceListSize. */
	private int searchCorrespondenceListSize;

	/**
	 * List to hold reassignDepartmentsList values.
	 */
	private List reassignDepartmentsList = new ArrayList();

	/** To hold selectedDeptAll. */
	private String selectedDeptAll;

	/**
	 * This field is used to store searchSelectAll.
	 */
	private boolean searchSelectAll = false;

	/**
	 * This field is used to store searchDeSelectAll.
	 */
	private boolean searchDeSelectAll = false;

	/**
	 * List to hold reassignAllDeptUsersList values.
	 */
	private List reassignAllDeptUsersList = new ArrayList();

	/**
	 * entityTypeMap to hold the entityType short desc.
	 */
	private Map entityTypeMap = Collections.EMPTY_MAP;

	/**
	 * statusMap to hold the status short desc.
	 */
	private Map statusMap = Collections.EMPTY_MAP;

	private Map subjectMap = Collections.EMPTY_MAP;

	private boolean renderReassignBtn = false;
	
	// Below flag is added for CR-ESPRD00798895
	/**
	 * sortFlag to hold the sortFlag value.
	 */
	private boolean sortFlag = false;

	/**
	 * @return the sortFlag
	 */
	public boolean isSortFlag() {
		return sortFlag;
	}

	/**
	 * @param sortFlag the sortFlag to set
	 */
	public void setSortFlag(boolean sortFlag) {
		this.sortFlag = sortFlag;
	}

	/**
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults
	 *            The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
	}

	/**
	 * Boolean value for showing the result set
	 */
	private boolean showResultsDiv = false;

	/** Enterprise Logger for Logging */

	private static EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(SearchCorrespondenceDataBean.class);

	/**
	 * This field is used to store correspondenceSearchCriteriaVO.
	 */
	private CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO;

	/**
	 * This field is used to retrieve correspondenceSearchResultsVO.
	 */
	private CorrespondenceSearchResultsVO correspondenceSearchResultsVO;

	/**
	 * This field is used to store categorySearchCriteriaVO.
	 */
	private CategorySearchCriteriaVO categorySearchCriteriaVO = new CategorySearchCriteriaVO();

	/**
	 * Renders the images for sorting.
	 */
	private String imageRender;

	private boolean advOptionsFl = false;

	private String validValues;

	private boolean loadValidValues;



	/**
	 * This is the SearchCorrespondenceDataBean Constructor
	 */
	public SearchCorrespondenceDataBean() {
		super();
		/*
		 * logger.debug("***************SearchCorrespondenceDataBean");
		 * getReferenceData();
		 */
	}

	/**
	 * @return Returns the categorySearchCriteriaVO.
	 */
	public CategorySearchCriteriaVO getCategorySearchCriteriaVO() {
		return categorySearchCriteriaVO;
	}

	/**
	 * @param categorySearchCriteriaVO
	 *            The categorySearchCriteriaVO to set.
	 */
	public void setCategorySearchCriteriaVO(
			CategorySearchCriteriaVO categorySearchCriteriaVO) {
		this.categorySearchCriteriaVO = categorySearchCriteriaVO;
	}

	/**
	 * Fetches the reference data.
	 */
	public void getReferenceData() {

		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx
				.getExternalContext().getRequest();

		String status = (String) request.getAttribute("sc");

		if ("true".equals(status)) {
			return;
		}
		request.setAttribute("sc", "true");

		String userId = getUserID();
		
		ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
		String businessUnitDesc = null;
		try {
			List buinessUnitDescs = delegate.getBuisnessUnitsDescs(userId);
			if (buinessUnitDescs.size() > 1) {
				businessUnitDesc = ContactManagementConstants.AllOthers;
			} else {
				businessUnitDesc = (String) buinessUnitDescs.get(0);
			}

			Executor[] arr = new Executor[4];

			Executor executor = new Executor(this, "getStatReferenceData",
					new String[] { businessUnitDesc });
			executor.start();
			arr[0] = executor;
			executor = new Executor(this, "getEntityTypeReferenceData",
					new String[] { businessUnitDesc });
			executor.start();
			arr[1] = executor;
			executor = new Executor(this, "getPriorityReferenceData");
			executor.start();
			arr[2] = executor;
			executor = new Executor(this, "getAllCategories",
					new String[] { userId });
			executor.start();
			arr[3] = executor;

			this.statusVVList = (List) arr[0].get();
			this.entityTypeVVList = (List) arr[1].get();
			this.priorityVVList = (List) arr[2].get();
			arr[3].get();

			CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);
			if(logger.isInfoEnabled())
			{
				logger.info("ENTITY TYPE -- UNENROLLED MEMBER - sysListNumber: " + sysListNumber);
			}

			/**
			 * Populates the Entity ID Type dropdown for Entity Type Other
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if(logger.isInfoEnabled())
				{
				logger.info("UNENROLLED MEMBER : setEntityIDTypeList with sysListNumber: "	+ sysListNumber);
				}

				setEntityIDTypeList(cmEntityDOConvertor
						.getEntIDTypeReferenceData(
								FunctionalAreaConstants.GENERAL, sysListNumber));
			}

			sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
					ContactManagementConstants.ENTITYIDTYPE,
					ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);
			if(logger.isInfoEnabled())
			{
			logger.info(":getInqEntIDTypeValues:: ENTITY TYPE -- UNENROLLED MEMBER - sysListNumber: " + sysListNumber);
			}

			/** Populates the Entity ID Type dropdown for Entity Type Other */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				setInqEntityIDTypeList(cmEntityDOConvertor
						.getEntIDTypeReferenceData(
								FunctionalAreaConstants.GENERAL, sysListNumber));
			}
		} catch (LOBHierarchyFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		

		/** Get categories for he user */

	}

	public void getAdvOptionsPlus() {

		CorrespondenceDOConvertor correspondenceDOConvertor = new CorrespondenceDOConvertor();
		Long userSK = correspondenceDOConvertor.getUserSK(getUserID());
		String businessUnitDesc = correspondenceDOConvertor
				.getBusinessUnitforUser(userSK);

		this.resolnVVList = getResolnReferenceData(businessUnitDesc);
		this.subjectVVList = getSubjectReferenceData(businessUnitDesc);
		this.sourceVVList = getSourceReferenceData(businessUnitDesc);
		this.lobVVList = getLobReferenceData();
		this.provTypeVVList = getProvTypeReferenceData();
		/** Get categories for he user */

		//getAllCategories(getUserID());
		getAllUsers();
		getAllHierarchies();
		//getAllDeptUsers();
		this.advOptionsFl = true;

	}

	public void getAdvOptionsMinus() {

		this.advOptionsFl = false;

	}

	/**
	 * this method is to get the all active users
	 *  
	 */
	private void getAllDeptUsers() {
		
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		//"FindBugs" issue fixed
		//List userList = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		List userList = null;
		Map userDescMap = new HashMap();
        List sortUserDesc = new ArrayList();
		//FindBugs issue Fixed
		//Set testSet = new HashSet(ContactManagementConstants.DEFAULT_SIZE);
		try {
			//userList = contactMaintenanceDelegate.getAllDepartmentUsers();
			userList = contactMaintenanceDelegate.getAllInternalUser();
			userList= new ArrayList(new HashSet(userList));
			if(logger.isDebugEnabled())
			{
				logger.info("User List after Delegate" + userList.size());
			}
			List listOfUsers = new ArrayList();
			List sortList = new ArrayList();

		    // Code Commented for CM-API
			/*for (Iterator iter = userList.iterator(); iter.hasNext();) {
				DepartmentUser dUser = (DepartmentUser) iter.next();
				EnterpriseUser user = (EnterpriseUser) dUser
						.getEnterpriseUser();

				StringBuffer userDesc = new StringBuffer(
						ContactManagementConstants.EMPTY_STRING);

				if (user.getLastName() != null) {
					userDesc.append(user.getLastName());
				}
				if (user.getLastName() != null && user.getFirstName() != null) {
					userDesc.append(ContactManagementConstants.COMMA_SEPARATOR
							+ ContactManagementConstants.SPACE_STRING);
				}
				if (user.getFirstName() != null) {
					userDesc.append(user.getFirstName());
				}
				
				 * if (user.getUserID() != null) {
				 * userDesc.append(ContactManagementConstants.HYPHEN_SEPARATOR +
				 * user.getUserID()); }
				 

				if (userDesc != null && userDesc.length() > 0
						&& user.getUserWorkUnitSK() != null
						&& user.getUserID() != null) {
					if (!sortList
							.contains((userDesc
									+ ContactManagementConstants.HYPHEN_SEPARATOR + user
									.getUserID()))) {
						SelectItem selectItem = new SelectItem(user
								.getUserWorkUnitSK().toString(), userDesc
								.toString()
								+ ContactManagementConstants.HYPHEN_SEPARATOR
								+ user.getUserID());
						sortList
								.add((userDesc.toString()
										+ ContactManagementConstants.HYPHEN_SEPARATOR + user
										.getUserID()));
						listOfUsers.add(selectItem);
					}
				}*/

				/*
				 * listOfUsers.add(new SelectItem(user.getUserWorkUnitSK()
				 * .toString(), userDesc.toString()));
				 */
			// Code added for CM-API
			for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
				Object[] userData = (Object[]) iterator.next();
				String userDesc = null;
				if (userData[3] != null
						&& !isNullOrEmptyField(userData[3].toString())) {
					userDesc = userData[3].toString();
				}
				if ((userData[3] != null && !isNullOrEmptyField(userData[3]
						.toString()))
						&& (userData[2] != null && !isNullOrEmptyField(userData[2]
								.toString()))) {
					userDesc = userDesc
							+ ContactManagementConstants.COMMA_SEPARATOR
							+ ContactManagementConstants.SPACE_STRING;
				}
				if (userData[2] != null
						&& !isNullOrEmptyField(userData[2].toString())) {
					userDesc = userDesc + userData[2].toString();
				}
				
			    
				StringBuffer userLabel = new StringBuffer().append(userDesc)
						.append(ContactManagementConstants.HYPHEN_SEPARATOR)
						.append(userData[1].toString());
				
			if(userLabel != null )
			{
			   sortUserDesc.add(userLabel.toString());
			   sortUserDesc =new ArrayList(new HashSet(sortUserDesc));
			   userDescMap.put(userLabel.toString(), userData[0].toString());
			}
				   
			}
    for (Iterator itr = sortUserDesc.iterator(); itr.hasNext();)
    {
    	Object obj = itr.next();
    	listOfUsers.add(new SelectItem(userDescMap.get(obj.toString()).toString(), obj.toString()));
    }

			setAssignedToList(listOfUsers);
			setTempAssignedToList(listOfUsers);
			assignedToComparator(getAssignedToList());
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

	/**
	 * This operation is used to get the reference data for all Entity Type.
	 * 
	 * @return List
	 */
	public final List getEntityTypeReferenceData(String businessUnitDesc) {
		
		long entryTime = System.currentTimeMillis();

		if (businessUnitDesc != null) {

			if (businessUnitDesc.startsWith("Prov")) {
				businessUnitDesc = "ProviderRelations";
			}
		}

		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		List entityList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBugs issue Fixed
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		//inputCriteriaEntityTyp
		//        .setElementName(ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD);

		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.ENTITYTYPE,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			if (referenceDataListVO != null) {
				referenceMap = referenceDataListVO.getResponseMap();
			}
			//"FindBugs" issue fixed starts
			if(referenceMap!=null){
			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String
									.valueOf(ContactManagementHelper
											.getSystemlistForCorrBU(
													ContactManagementConstants.ENTITYTYPE,
													businessUnitDesc)));
			}//"FindBugs" issue fixed ends
			referenceListSize = referenceList.size();
			if(logger.isDebugEnabled())
			{
				logger.debug("referenceListSize--->" + referenceListSize);
			}
			entityTypeMap.clear();
			entityTypeMap = new HashMap();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);
				
				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				entityList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));
				entityTypeMap.put(refVo.getValidValueCode(), codesAndDesc);
			}
		} catch (ReferenceServiceRequestException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}
		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
		logger.info("CMEntityDataBean" + "#" + "getReferenceData" + "#"
				+ (exitTime - entryTime));
		}
		return entityList;
	}

	/**
	 * This method is used to retreive valid values in the status dropdown
	 * 
	 * @return List
	 */
	public final List getStatReferenceData(String businessUnitDesc) {

		
		long entryTime = System.currentTimeMillis();

		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		List statList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBugs issue Fixed
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);

		//inputCriteriaEntityTyp
		//        .setElementName(ReferenceServiceDataConstants.G_CR_STAT_CD);
		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.CORR_STATUS,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			if (referenceDataListVO != null) {
				referenceMap = referenceDataListVO.getResponseMap();
			}
			//"FindBugs" issue fixed starts
			if(referenceMap!=null){
			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String
									.valueOf(ContactManagementHelper
											.getSystemlistForCorrBU(
													ContactManagementConstants.CORR_STATUS,
													businessUnitDesc)));
			}//"FindBugs" issue fixed ends
			referenceListSize = referenceList.size();
			statusMap.clear();
			statusMap = new HashMap();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				statList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));
				statusMap.put(refVo.getValidValueCode(), codesAndDesc);

			}

		} catch (ReferenceServiceRequestException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
		logger.info("CMEntityDataBean" + "#" + "getReferenceData" + "#"
				+ (exitTime - entryTime));
		}

		return statList;
	}

	/**
	 * This method is used to retreive valid values in the Priority dropdown
	 * 
	 * @return List
	 */

	public final List getPriorityReferenceData() {
		
		long entryTime = System.currentTimeMillis();

		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		List priorityList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBugs issue Fixed
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		inputCriteriaEntityTyp
				.setElementName(ReferenceServiceDataConstants.G_CR_PRTY_CD);

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			if (referenceDataListVO != null) {
				referenceMap = referenceDataListVO.getResponseMap();
			}
			//"FindBugs" issue fixed starts
			if(referenceMap!=null){
			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ ReferenceServiceDataConstants.G_CR_PRTY_CD);
			}//"FindBugs" issue fixed ends
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				priorityList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
		logger.info("CMEntityControllerBean" + "#" + "getProvTypeReferenceData"
				+ "#" + (exitTime - entryTime));
		}

		return priorityList;
	}

	/**
	 * This method is used to retreive valid values in the Provider dropdown
	 * 
	 * @return List
	 */
	public final List getProvTypeReferenceData() {
		
		long entryTime = System.currentTimeMillis();

		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		List provTypeList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBugs issue Fixed
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.PROVIDER);
		inputCriteriaEntityTyp
				.setElementName(ReferenceServiceDataConstants.PROV_TY_CD);

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			if (referenceDataListVO != null) {
				referenceMap = referenceDataListVO.getResponseMap();
			}
			//"FindBugs" issue fixed starts
			if(referenceMap!=null){
			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.PROVIDER
							+ ProgramConstants.HASH_SEPARATOR
							+ ReferenceServiceDataConstants.PROV_TY_CD);
			}//"FindBugs" issue fixed ends
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				provTypeList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
			logger.info("CMEntityControllerBean" + "#" + "getProvTypeReferenceData"
				+ "#" + (exitTime - entryTime));
		}

		return provTypeList;
	}

	/**
	 * This method is used to retreive valid values in the Resolution dropdown
	 * 
	 * @return List
	 */
	public final List getResolnReferenceData(String businessUnitDesc) {
		//logger.info("getProvTypeReferenceData");
		//  long entryTime = System.currentTimeMillis();

		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		List resolnList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBugs issue Fixed
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		//inputCriteriaEntityTyp
		//        .setElementName(ReferenceServiceDataConstants.G_CR_RESLTN_CD);

		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(
						ContactManagementConstants.CORR_RESOLUTION,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			if (referenceDataListVO != null) {
				referenceMap = referenceDataListVO.getResponseMap();
			}
			//"FindBugs" issue fixed starts
			if(referenceMap!=null){
			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String
									.valueOf(ContactManagementHelper
											.getSystemlistForCorrBU(
													ContactManagementConstants.CORR_RESOLUTION,
													businessUnitDesc)));
			}//"FindBugs" issue fixed ends
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				resolnList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		//long exitTime = System.currentTimeMillis();
		return resolnList;

	}

	/**
	 * This method is used to retreive valid values in the Subject dropdown
	 * 
	 * @return List
	 */
	public final List getSubjectReferenceData(String businessUnitDesc) {

		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		List subjectList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBugs issue Fixed
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		//inputCriteriaEntityTyp
		//      .setElementName(ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);

		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU("SUBJECT", businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			if (referenceDataListVO != null) {
				referenceMap = referenceDataListVO.getResponseMap();
			}
			//"FindBugs" issue fixed starts
			if(referenceMap!=null) {
			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String.valueOf(ContactManagementHelper
									.getSystemlistForCorrBU("SUBJECT",
											businessUnitDesc)));
			}//"FindBugs" issue fixed ends
			referenceListSize = referenceList.size();

			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				subjectList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		return subjectList;

	}

	/**
	 * This method is used to retreive valid values in the Source dropdown
	 * 
	 * @return List
	 */
	public final List getSourceReferenceData(String businessUnitDesc) {
		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		List srcList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBugs issue Fixed
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		//inputCriteriaEntityTyp
		//        .setElementName(ReferenceServiceDataConstants.G_CRSPD_SRC_CD);

		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.CORR_SOURCE,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			if (referenceDataListVO != null) {
				referenceMap = referenceDataListVO.getResponseMap();
			}
			//"FindBugs" issue fixed starts
			if(referenceMap!=null){
			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String
									.valueOf(ContactManagementHelper
											.getSystemlistForCorrBU(
													ContactManagementConstants.CORR_SOURCE,
													businessUnitDesc)));
			}//"FindBugs" issue fixed ends
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				srcList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		//        long exitTime = System.currentTimeMillis();
		/*
		 * logger.info("CMEntityControllerBean" + "#" +
		 * "getProvTypeReferenceData" + "#" + (exitTime - entryTime));
		 */

		return srcList;
	}

	/**
	 * This operation is used to get all the Categories. *
	 * 
	 * @param userID :
	 *            The User ID to set.
	 */
	public void getAllCategories(String userID) {
		logger.debug("getAllCategories in search cR ");
		logger.debug("user id ------>" + userID);
		List listOfCategoryDOs = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			listOfCategoryDOs = contactMaintenanceDelegate
					.getAllCategoriesForList(userID);

		} catch (CategoryFetchBusinessException e) {
			logger.error(e.getMessage(), e);
		}

		if (listOfCategoryDOs != null) {
			logger.debug("Size of listOfCategoryDOs ********"
					+ listOfCategoryDOs.size());
			CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();
			Map categoryMap = new HashMap();
			List sortCategory = new ArrayList();
			Iterator iter = listOfCategoryDOs.iterator();
			while (iter.hasNext()) {
				CorrespondenceCategory categoryDO = (CorrespondenceCategory) iter
						.next();
				CategoryVO categoryVO = categoryDOConvertor
						.convertCategoryDOToVOForCatList(categoryDO);
				/** Add to the List that needs to be sent in search Crit Vo */
				catListToBeSentInSearchCritVO.add(categoryVO.getCategorySK());
				logger.debug("%%%%Size of catListToBeSentInSearchCritVO------>"
						+ catListToBeSentInSearchCritVO.size());

				/** Add to the List that needs to be displayed in dropdown */
				sortCategory.add(categoryVO.getCategoryDesc());
				categoryMap.put(categoryVO.getCategoryDesc(), categoryVO
						.getCategorySK().toString());
				/*
				 * categoryList.add(new SelectItem(categoryVO.getCategorySK()
				 * .toString(), categoryVO.getCategoryDesc()));
				 */
				logger.debug("%%%%Size of categoryList------>"
						+ categoryList.size());

			}
			Collections.sort(sortCategory);
			iter = sortCategory.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				categoryList.add(new SelectItem(
						categoryMap.get(obj).toString(), obj.toString()));
			}
		}

		else {
			logger
					.debug("************* listOfCategoryDOs null ************** ");
		}

	}

	/**
	 * This method is used to retreive valid values in the Line Of Business
	 * dropdown
	 * 
	 * @return List
	 */
	public final List getLobReferenceData() {

		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		List lobList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBugs issue Fixed
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteriaEntityTyp
				.setElementName(ReferenceServiceDataConstants.R_LOB_CD);

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			if (referenceDataListVO != null) {
				referenceMap = referenceDataListVO.getResponseMap();
			}
			//"FindBugs" issue fixed starts
			if(referenceMap!=null){
			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.GENERAL
							+ ProgramConstants.HASH_SEPARATOR
							+ ReferenceServiceDataConstants.R_LOB_CD);
			}//"FindBugs" issue fixed ends
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);
				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();

				lobList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			logger.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return lobList;

	}

	/**
	 * This method is used to retreive values in the createdBy dropdown
	 */
	//Method access specifier changed from private to public for the defect - ESPRD00808853
	
	public void getAllUsers() {

		logger.debug("getAllUsers");
		CMDelegate cMDelegate = new CMDelegate();
		//"FindBugs" issue fixed
		//List userList = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		List userList = null;
		try {

			userList = cMDelegate.getUserDetails();

			List listOfUsers = new ArrayList();
			if (userList == null) {
				logger.info("userList is null");
			} else {
				logger.info("userList size===" + userList.size());
				for (Iterator iter = userList.iterator(); iter.hasNext();) {
					Object[] userDetails = (Object[]) iter.next();
					//EnterpriseUser user = (EnterpriseUser) iter.next();

					StringBuffer userDesc = new StringBuffer(
							ContactManagementConstants.EMPTY_STRING);

					/*
					 * if (user.getPrefixName() != null) {
					 * userDesc.append(user.getPrefixName() +
					 * ContactManagementConstants.SPACE_STRING);
					 *  }
					 */
					
					//Code Modified below for the defect - ESPRD00754752-start

					if (userDetails[0] != null || !(userDetails[0] == ContactManagementConstants.EMPTY_STRING)) {
						userDesc.append(userDetails[0]
								+ ContactManagementConstants.COMMA_SEPARATOR
								+ ContactManagementConstants.SPACE_STRING);

					}

					if (userDetails[1] != null || !(userDetails[1] == ContactManagementConstants.EMPTY_STRING)) {
						userDesc.append(userDetails[1]
								+ ContactManagementConstants.HYPHEN_SEPARATOR);

					}

					if (userDetails[2] != null || !(userDetails[2] == ContactManagementConstants.EMPTY_STRING)) {
						userDesc.append(userDetails[2]);
						/*
						 * userDesc.append(user.getUserWorkUnitSK());
						 * userDesc.append(ContactManagementConstants.EMPTY_STRING);
						 */

					}
					/*
					 * if (user.getMiddleName() != null) {
					 * userDesc.append(user.getMiddleName() +
					 * ContactManagementConstants.SPACE_STRING);
					 *  }
					 */

					/*
					 * if (user.getSuffixName() != null) {
					 * userDesc.append(user.getSuffixName());
					 *  }
					 */

					if(userDetails[3] != null || !(userDetails[3] == ContactManagementConstants.EMPTY_STRING))
					{
						listOfUsers.add(new SelectItem(userDetails[3].toString(),userDesc.toString()));
					}
					//Code Modified below for the defect - ESPRD00754752-End
				}
				logger.info("listOfUsers======" + listOfUsers.size());
			}
			setCreatedByList(listOfUsers);
			setAssignedToList(listOfUsers);
			setTempAssignedToList(listOfUsers);
			assignedToComparator(getAssignedToList());
			assignedToComparator(getTempAssignedToList());
			assignedToComparator(getCreatedByList());
		} catch (Exception exe) {
			logger.debug("Exception caught in getAllUsers--databean");
		}

	}

	/**
	 * This method is used to retreive values in the ReportingUnit and
	 * BusinessUnit dropdown
	 */
	public void getAllHierarchies() {

		//"FindBugs" issue fixed
		//List repUnitsList = new ArrayList();
		List repUnitsList = null;
		//"FindBugs" issue fixed
		//List businessUnitsList = new ArrayList();
		List businessUnitsList = null;
		//"FindBugs" issue fixed
		//List deptUnitsList = new ArrayList();
		List deptUnitsList =null;
		List emptyList = new ArrayList();

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			repUnitsList = contactMaintenanceDelegate.getLobHierarchyDetails(
					emptyList, ContactManagementConstants.TWO);
			this.getListOfRepUnits().clear();
			Iterator itr1 = repUnitsList.iterator();
			while (itr1.hasNext()) {
				LineOfBusinessHierarchy repUnit = (LineOfBusinessHierarchy) itr1
						.next();
				this.getListOfRepUnits().add(
						new SelectItem(repUnit.getLineOfBusinessHierarchySK()
								.toString(), repUnit
								.getLobHierarchyDescription()));

			}
			businessUnitsList = contactMaintenanceDelegate
					.getLobHierarchyDetails(emptyList,
							ContactManagementConstants.THREE);
			this.getListOfRefBusUnits().clear();
			Iterator itr2 = businessUnitsList.iterator();
			while (itr2.hasNext()) {
				LineOfBusinessHierarchy businUnit = (LineOfBusinessHierarchy) itr2
						.next();
				this.getListOfRefBusUnits().add(
						new SelectItem(businUnit.getLineOfBusinessHierarchySK()
								.toString(), businUnit
								.getLobHierarchyDescription()));

			}

			deptUnitsList = contactMaintenanceDelegate
					.getDepartmentDetails(new ArrayList());
			this.getListOfRefDeptUnits().clear();
			Iterator itr3 = deptUnitsList.iterator();
			while (itr3.hasNext()) {
				Department deptUnit = (Department) itr3.next();

				if (deptUnit.getLineOfBusinessHierarchy() != null) {
					this.getListOfRefDeptUnits().add(
							new SelectItem(deptUnit
									.getLineOfBusinessHierarchy()
									.getLineOfBusinessHierarchySK().toString(),
									deptUnit.getName()));

				}
			}
		} catch (Exception exe) {
			logger.debug("Exception caught in getAllHierarchies");
		}

	}

	/**
	 * @return Returns the correspondenceSearchCriteriaVO.
	 */
	public CorrespondenceSearchCriteriaVO getCorrespondenceSearchCriteriaVO() {
		return correspondenceSearchCriteriaVO;
	}

	/**
	 * @param correspondenceSearchCriteriaVO
	 *            The correspondenceSearchCriteriaVO to set.
	 */
	public void setCorrespondenceSearchCriteriaVO(
			CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO) {
		this.correspondenceSearchCriteriaVO = correspondenceSearchCriteriaVO;
	}

	/**
	 * @return List
	 */
	public List getEntityTypeVVList() {
		return entityTypeVVList;
	}

	/**
	 * @param entityTypeVVList
	 *            The entityTypeVVList to set.
	 */
	public void setEntityTypeVVList(List entityTypeVVList) {
		this.entityTypeVVList = entityTypeVVList;
	}

	/**
	 * @return Returns the provTypeVVList.
	 */
	public List getProvTypeVVList() {
		return provTypeVVList;
	}

	/**
	 * @param provTypeVVList
	 *            The provTypeVVList to set.
	 */
	public void setProvTypeVVList(List provTypeVVList) {
		this.provTypeVVList = provTypeVVList;
	}

	/**
	 * @return Returns the priorityVVList.
	 */
	public List getPriorityVVList() {
		return priorityVVList;
	}

	/**
	 * @param priorityVVList
	 *            The priorityVVList to set.
	 */
	public void setPriorityVVList(List priorityVVList) {
		this.priorityVVList = priorityVVList;
	}

	/**
	 * @return Returns the resolnVVList.
	 */
	public List getResolnVVList() {
		return resolnVVList;
	}

	/**
	 * @param resolnVVList
	 *            The resolnVVList to set.
	 */
	public void setResolnVVList(List resolnVVList) {
		this.resolnVVList = resolnVVList;
	}

	/**
	 * @return Returns the subjectVVList.
	 */
	public List getSubjectVVList() {
		return subjectVVList;
	}

	/**
	 * @param subjectVVList
	 *            The subjectVVList to set.
	 */
	public void setSubjectVVList(List subjectVVList) {
		this.subjectVVList = subjectVVList;
	}

	/**
	 * @return Returns the sourceVVList.
	 */
	public List getSourceVVList() {
		return sourceVVList;
	}

	/**
	 * @param sourceVVList
	 *            The sourceVVList to set.
	 */
	public void setSourceVVList(List sourceVVList) {
		this.sourceVVList = sourceVVList;
	}

	/**
	 * @return Returns the categoryList.
	 */
	public List getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList
	 *            The categoryList to set.
	 */
	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * @return Returns the catListToBeSentInSearchCritVO.
	 */
	public List getCatListToBeSentInSearchCritVO() {
		return catListToBeSentInSearchCritVO;
	}

	/**
	 * @param catListToBeSentInSearchCritVO
	 *            The catListToBeSentInSearchCritVO to set.
	 */
	public void setCatListToBeSentInSearchCritVO(
			List catListToBeSentInSearchCritVO) {
		this.catListToBeSentInSearchCritVO = catListToBeSentInSearchCritVO;
	}

	/**
	 * @return Returns the lobVVList.
	 */
	public List getLobVVList() {
		return lobVVList;
	}

	/**
	 * @param lobVVList
	 *            The lobVVList to set.
	 */
	public void setLobVVList(List lobVVList) {
		this.lobVVList = lobVVList;
	}

	/**
	 * @return Returns the listOfRefBusUnits.
	 */
	public List getListOfRefBusUnits() {
		return listOfRefBusUnits;
	}

	/**
	 * @param listOfRefBusUnits
	 *            The listOfRefBusUnits to set.
	 */
	public void setListOfRefBusUnits(List listOfRefBusUnits) {
		this.listOfRefBusUnits = listOfRefBusUnits;
	}

	/**
	 * @return Returns the listOfRefDeptUnits.
	 */
	public List getListOfRefDeptUnits() {
		return listOfRefDeptUnits;
	}

	/**
	 * @param listOfRefDeptUnits
	 *            The listOfRefDeptUnits to set.
	 */
	public void setListOfRefDeptUnits(List listOfRefDeptUnits) {
		this.listOfRefDeptUnits = listOfRefDeptUnits;
	}

	/**
	 * @return Returns the listOfRepUnits.
	 */
	public List getListOfRepUnits() {
		return listOfRepUnits;
	}

	/**
	 * @param listOfRepUnits
	 *            The listOfRepUnits to set.
	 */
	public void setListOfRepUnits(List listOfRepUnits) {
		this.listOfRepUnits = listOfRepUnits;
	}

	/**
	 * @return Returns the createdByList.
	 */
	public List getCreatedByList() {
		return createdByList;
	}

	/**
	 * @param createdByList
	 *            The createdByList to set.
	 */
	public void setCreatedByList(List createdByList) {
		this.createdByList = createdByList;
	}

	/**
	 * @return Returns the assignedToList.
	 */
	public List getAssignedToList() {
		return assignedToList;
	}

	/**
	 * @param assignedToList
	 *            The assignedToList to set.
	 */
	public void setAssignedToList(List assignedToList) {
		this.assignedToList = assignedToList;
	}

	/**
	 * @return Returns the showResultsDiv.
	 */
	public boolean isShowResultsDiv() {
		return showResultsDiv;
	}

	/**
	 * @param showResultsDiv
	 *            The showResultsDiv to set.
	 */
	public void setShowResultsDiv(boolean showResultsDiv) {

		this.showResultsDiv = showResultsDiv;

	}

	/**
	 * @return Returns the statusVVList.
	 */
	public List getStatusVVList() {
		return statusVVList;
	}

	/**
	 * @param statusVVList
	 *            The statusVVList to set.
	 */
	public void setStatusVVList(List statusVVList) {
		this.statusVVList = statusVVList;
	}

	/**
	 * @return Returns the listOfCategoryDOs.
	 */
	public List getListOfCategoryDOs() {
		return listOfCategoryDOs;
	}

	/**
	 * @param listOfCategoryDOs
	 *            The listOfCategoryDOs to set.
	 */
	public void setListOfCategoryDOs(List listOfCategoryDOs) {
		this.listOfCategoryDOs = listOfCategoryDOs;
	}

	/**
	 * @return Returns the advancedOptionsHidden.
	 */
	public String getAdvancedOptionsHidden() {
		return advancedOptionsHidden;
	}

	/**
	 * @param advancedOptionsHidden
	 *            The advancedOptionsHidden to set.
	 */
	public void setAdvancedOptionsHidden(String advancedOptionsHidden) {
		this.advancedOptionsHidden = advancedOptionsHidden;
	}

	/**
	 * @return Returns the correspondenceSearchResultsVO.
	 */
	public CorrespondenceSearchResultsVO getCorrespondenceSearchResultsVO() {
		return correspondenceSearchResultsVO;
	}

	/**
	 * @param correspondenceSearchResultsVO
	 *            The correspondenceSearchResultsVO to set.
	 */
	public void setCorrespondenceSearchResultsVO(
			CorrespondenceSearchResultsVO correspondenceSearchResultsVO) {
		this.correspondenceSearchResultsVO = correspondenceSearchResultsVO;
	}

	/**
	 * @return Returns the associatedSearchResults.
	 */
	public List getAssociatedSearchResults() {
		return associatedSearchResults;
	}

	/**
	 * @param associatedSearchResults
	 *            The associatedSearchResults to set.
	 */
	public void setAssociatedSearchResults(List associatedSearchResults) {
		this.associatedSearchResults = associatedSearchResults;
	}

	/**
	 * @return Returns the existingResults.
	 */
	public List getExistingResults() {
		return existingResults;
	}

	/**
	 * @param existingResults
	 *            The existingResults to set.
	 */
	public void setExistingResults(List existingResults) {
		this.existingResults = existingResults;
	}

	/**
	 * @return Returns the imageRender.
	 */
	public String getImageRender() {
		return imageRender;
	}

	/**
	 * @param imageRender
	 *            The imageRender to set.
	 */
	public void setImageRender(String imageRender) {
		this.imageRender = imageRender;
	}

	/**
	 * This method is used to get the User ID.
	 * 
	 * @return String : User ID
	 */
	public String getUserID() {

		String userId = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest) facesContext
				.getExternalContext().getRequest();
		userId = getLoggedInUserID(portletRequest);

		/*
		 * String userId = "USERID1";
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

		return userId;
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
	 * @return Returns the searchCorrespondenceListSize.
	 */
	public int getSearchCorrespondenceListSize() {
		return searchCorrespondenceListSize;
	}

	/**
	 * @param searchCorrespondenceListSize
	 *            The searchCorrespondenceListSize to set.
	 */
	public void setSearchCorrespondenceListSize(int searchCorrespondenceListSize) {
		this.searchCorrespondenceListSize = searchCorrespondenceListSize;
	}

	/**
	 * @return Returns the reassignDepartmentsList.
	 */
	public List getReassignDepartmentsList() {
		return reassignDepartmentsList;
	}

	/**
	 * @param reassignDepartmentsList
	 *            The reassignDepartmentsList to set.
	 */
	public void setReassignDepartmentsList(List reassignDepartmentsList) {
		this.reassignDepartmentsList = reassignDepartmentsList;
	}

	/**
	 * @return Returns the selectedDeptAll.
	 */
	public String getSelectedDeptAll() {
		return selectedDeptAll;
	}

	/**
	 * @param selectedDeptAll
	 *            The selectedDeptAll to set.
	 */
	public void setSelectedDeptAll(String selectedDeptAll) {
		this.selectedDeptAll = selectedDeptAll;
	}

	/**
	 * @return Returns the searchSelectAll.
	 */
	public boolean isSearchSelectAll() {
		return searchSelectAll;
	}

	/**
	 * @param searchSelectAll
	 *            The searchSelectAll to set.
	 */
	public void setSearchSelectAll(boolean searchSelectAll) {
		this.searchSelectAll = searchSelectAll;
	}

	/**
	 * @return Returns the searchDeSelectAll.
	 */
	public boolean isSearchDeSelectAll() {
		return searchDeSelectAll;
	}

	/**
	 * @param searchDeSelectAll
	 *            The searchDeSelectAll to set.
	 */
	public void setSearchDeSelectAll(boolean searchDeSelectAll) {
		this.searchDeSelectAll = searchDeSelectAll;
	}

	/**
	 * @return Returns the reassignAllDeptUsersList.
	 */
	public List getReassignAllDeptUsersList() {
		return reassignAllDeptUsersList;
	}

	/**
	 * @param reassignAllDeptUsersList
	 *            The reassignAllDeptUsersList to set.
	 */
	public void setReassignAllDeptUsersList(List reassignAllDeptUsersList) {
		this.reassignAllDeptUsersList = reassignAllDeptUsersList;
	}

	/**
	 * @return Returns the searchAssocCR.
	 */
	public boolean isSearchAssocCR() {
		return SearchAssocCR;
	}

	/**
	 * @param searchAssocCR
	 *            The searchAssocCR to set.
	 */
	public void setSearchAssocCR(boolean searchAssocCR) {
		SearchAssocCR = searchAssocCR;
	}

	public boolean isAdvOptionsFl() {
		return this.advOptionsFl;
	}

	//	added by ics
	/**
	 * @return Returns the caserecordSearchCriteriaVO.
	 */
	public CaseRecordSearchCriteriaVO getCaserecordSearchCriteriaVO() {
		return caserecordSearchCriteriaVO;
	}

	/**
	 * @param caserecordSearchCriteriaVO
	 *            The caserecordSearchCriteriaVO to set.
	 */
	public void setCaserecordSearchCriteriaVO(
			CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO) {
		this.caserecordSearchCriteriaVO = caserecordSearchCriteriaVO;
	}

	private CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO;

	//end

	private List entityIDTypeList = new ArrayList();

	/**
	 * @return Returns the entityIDTypeList.
	 */
	public List getEntityIDTypeList() {
		return entityIDTypeList;
	}

	/**
	 * @param entityIDTypeList
	 *            The entityIDTypeList to set.
	 */
	public void setEntityIDTypeList(List entityIDTypeList) {
		this.entityIDTypeList = entityIDTypeList;
	}

	private List inqEntityIDTypeList = new ArrayList();

	/**
	 * @return Returns the inqEntityIDTypeList.
	 */
	public List getInqEntityIDTypeList() {
		return inqEntityIDTypeList;
	}

	/**
	 * @param inqEntityIDTypeList
	 *            The inqEntityIDTypeList to set.
	 */
	public void setInqEntityIDTypeList(List inqEntityIDTypeList) {
		this.inqEntityIDTypeList = inqEntityIDTypeList;
	}

	/**
	 * @return Returns the loadValidValues.
	 */
	public boolean isLoadValidValues() {
		return loadValidValues;
	}

	/**
	 * @param loadValidValues
	 *            The loadValidValues to set.
	 */
	public void setLoadValidValues(boolean loadValidValues) {
		this.loadValidValues = loadValidValues;
	}

	/**
	 * @return Returns the validValues.
	 */
	public String getValidValues() {
		if (!isLoadValidValues()) {
			logger.info("calling getReferenceData");
			getReferenceData();
			setLoadValidValues(true);
		}
		cursorFocusId = "entityType";
		return validValues;
	}

	/**
	 * @param validValues
	 *            The validValues to set.
	 */
	public void setValidValues(String validValues) {
		this.validValues = validValues;
	}

	/**
	 * @return Returns the entityTypeMap.
	 */
	public Map getEntityTypeMap() {
		return entityTypeMap;
	}

	/**
	 * @param entityTypeMap
	 *            The entityTypeMap to set.
	 */
	public void setEntityTypeMap(Map entityTypeMap) {
		this.entityTypeMap = entityTypeMap;
	}

	/**
	 * @return Returns the statusMap.
	 */
	public Map getStatusMap() {
		return statusMap;
	}

	/**
	 * @param statusMap
	 *            The statusMap to set.
	 */
	public void setStatusMap(Map statusMap) {
		this.statusMap = statusMap;
	}
	/**
	 * @return Returns the subjectMap.
	 */
	public Map getSubjectMap() {
		return subjectMap;
	}

	/**
	 * @param subjectMap
	 *            The subjectMap to set.
	 */
	public void setSubjectMap(Map subjectMap) {
		this.subjectMap = subjectMap;
	}

	/**
	 * @return Returns the renderReassignBtn.
	 */
	public boolean isRenderReassignBtn() {
		return renderReassignBtn;
	}

	/**
	 * @param renderReassignBtn
	 *            The renderReassignBtn to set.
	 */
	public void setRenderReassignBtn(boolean renderReassignBtn) {
		this.renderReassignBtn = renderReassignBtn;
	}

	public String assignedToComparator(List dataList) {
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				String first = null;
				String second = null;
				SelectItem data1 = (SelectItem) obj1;
				SelectItem data2 = (SelectItem) obj2;
				boolean ascending = true;

				if (null == data1.getLabel()) {
					data1.setLabel(ContactManagementConstants.EMPTY_STRING);
				}
				if (null == data2.getLabel()) {
					data2.setLabel(ContactManagementConstants.EMPTY_STRING);
				}

				first = data1.getLabel().toLowerCase();
				second = data2.getLabel().toLowerCase();
				logger.debug("First in desc=======" + first);
				logger.debug("second in desc=======" + second);

				return ascending ? first.compareTo(second) : second
						.compareTo(first);

			}
		};
		Collections.sort(dataList, comparator);
		return "";
	}

	/**
	 * @return Returns the tempAssignedToList.
	 */
	public List getTempAssignedToList() {
		return tempAssignedToList;
	}

	/**
	 * @param tempAssignedToList
	 *            The tempAssignedToList to set.
	 */
	public void setTempAssignedToList(List tempAssignedToList) {
		this.tempAssignedToList = tempAssignedToList;
	}

	/**
	 * This method is Used for getting Logged in User Id from Principal
	 * 
	 * @param portletRequest
	 * @return
	 */
	public String getLoggedInUserID(PortletRequest portletRequest) {
		String userId = null;
		Principal principal = null;
		/*
		 * FacesContext facesContext = FacesContext.getCurrentInstance();
		 * PortletRequest portletRequest =
		 * (PortletRequest)facesContext.getExternalContext().getRequest();
		 */
		if (portletRequest != null) {

			principal = portletRequest.getUserPrincipal();
		}
		if (principal == null) {
			userId = "guestUser";
		} else {
			userId = principal.getName();
		}

		return userId;
	}
	
	//heap dump issue fix
	private int startIndexForSrchRslts=0;
	
	private int startIndexForAscSrchRslts=0;
	
	/**
	 * @return Returns the startIndexForAscSrchRslts.
	 */
	public int getStartIndexForAscSrchRslts() {
		return startIndexForAscSrchRslts;
	}
	/**
	 * @param startIndexForAscSrchRslts The startIndexForAscSrchRslts to set.
	 */
	public void setStartIndexForAscSrchRslts(int startIndexForAscSrchRslts) {
		this.startIndexForAscSrchRslts = startIndexForAscSrchRslts;
	}
	/**
	 * @return Returns the startIndexForSrchRslts.
	 */
	public int getStartIndexForSrchRslts() {
		return startIndexForSrchRslts;
	}
	/**
	 * @param startIndexForSrchRslts The startIndexForSrchRslts to set.
	 */
	public void setStartIndexForSrchRslts(int startIndexForSrchRslts) {
		this.startIndexForSrchRslts = startIndexForSrchRslts;
	}
	
	// Code added for CR:ESPRD00798895 for pagination:
	
	private String continueType;

	public String getContinueType() {
		return continueType;
	}

	public void setContinueType(String continueType) {
		this.continueType = continueType;
	}
	
	 /**
     * Holds boolean value of noDataFlag
     */
    private boolean noDataFlag = true;

	public boolean isNoDataFlag() {
		return noDataFlag;
	}

	public void setNoDataFlag(boolean noDataFlag) {
		this.noDataFlag = noDataFlag;
	}
	
	private boolean showNextOne = false;
	
	public boolean isShowNextOne() {
		return showNextOne;
	}

	public void setShowNextOne(boolean showNextOne) {
		this.showNextOne = showNextOne;
	}
	/** holds the total number of records per page. * */
    private int itemsPerPage = ProgramNumberConstants.INT_TEN;

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	/** variable to hold the start Record number for the current page. */
    private int startRecordNo;
	

	
	public int getStartRecordNo() {
		return startRecordNo;
	}

	public void setStartRecordNo(int startRecordNo) {
		this.startRecordNo = startRecordNo;
	}
	
	//pagination issue 
	boolean paginationFlag;

	public boolean isPaginationFlag() {
		return paginationFlag;
	}

	public void setPaginationFlag(boolean paginationFlag) {
		this.paginationFlag = paginationFlag;
	}
	
	/**
	 * Code is added below for not retaining the error messages 
	 * on Log Correspondence page for the Defect ESPRD00852896
	 */
	
	private boolean searchCRValidationFlag;

	/**
	 * @return the searchCRValidationFlag
	 */
	public boolean isSearchCRValidationFlag() {
		return searchCRValidationFlag;
	}

	/**
	 * @param searchCRValidationFlag the searchCRValidationFlag to set
	 */
	public void setSearchCRValidationFlag(boolean searchCRValidationFlag) {
		this.searchCRValidationFlag = searchCRValidationFlag;
	}
	
}