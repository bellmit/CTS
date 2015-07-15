/*
 * Created on Oct 30, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.contactmanagement.application.dataaccess.exception.CorrespondenceUpdateDataException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CategorySubjectXref;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.common.domain.SubjectAuxillary;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CRAdvanceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CorrespondenceDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.helper.ValidatorConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;

/**
 * @author mohamabb TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchCorrespondenceControllerBean extends
		EnterpriseBaseControllerBean {

	/**
	 * Declared for validation of the Search Criteria.
	 */
	private boolean flag = true;

	/** Enterprise Logger for Logging */

	private static EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(SearchCorrespondenceControllerBean.class);

	/** Holds memberCorrespondence. */
	private String memberCorrespondence;

	/**
	 * This field is used to store whether user has permission.
	 */
	private boolean controlRequired = true;

	/**
	 * This is the SearchCorrespondenceControllerBean Constructor
	 */

	private Set testSet = new HashSet(ContactManagementConstants.DEFAULT_SIZE);

	private String loadEntityData;

	private boolean isDateErrOccurred = false;

	private String loadSearchCRPermission;

	private String loadReAssignCRPermission;

	private boolean ctrlReqForReassignCorr = true;

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
		String userid = getUserID();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.LOG_CORRESPONDENCE_PAGE, userid);
		} catch (SecurityFLSServiceException e) {
			
			e.printStackTrace();
		}

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode", userPermission);
		userPermission = userPermission != null ? userPermission.trim() : "";
		
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
		/*
		 * try { HttpServletRequest renderrequest = (HttpServletRequest)
		 * FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 * HttpServletResponse renderresponse = null; EnterpriseUserProfile
		 * enterpriseUserProfile = getUserData(renderrequest, renderresponse);
		 * if (enterpriseUserProfile != null) { userID =
		 * enterpriseUserProfile.getUserId(); } HttpSession session =
		 * (HttpSession)
		 * FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		 * session.setAttribute("LOGGED_IN_USERID", userID); } catch (Exception
		 * e) { e.getCause(); e.getMessage(); logger.debug("Exception has
		 * come:"); }
		 */
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			PortletRequest portletRequest = (PortletRequest) facesContext
					.getExternalContext().getRequest();
			
			userID = getLoggedInUserID(portletRequest);
			HttpSession session = (HttpSession) facesContext
					.getExternalContext().getSession(true);
			session.setAttribute("LOGGED_IN_USERID", userID);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			
		}

		return userID;
	}

	/**
	 * renders the detail part based on the filter.
	 * 
	 * @param event
	 *            event.
	 */
	//  Note: Changed return type for Navigation Rule
	public String onCategoryChange(ValueChangeEvent event) {
		

		if (event != null
				&& event.getNewValue() != null
				&& !ProgramConstants.EMPTY_STRING.equals(event.getNewValue()
						.toString().trim())) {
			String selectedCategory = (String) event.getNewValue();
			if(logger.isDebugEnabled())
			{
				logger.debug("****selectedCategory****" + selectedCategory);
			}
			getsubjects(selectedCategory);
		}
		//getSearchCorrespondenceDataBean().getSubjectReferenceData();
		return "success";
	}

	/**
	 * renders the detail part based on the filter.
	 * 
	 * @param event
	 *            event.
	 */
	//Note: Changed return type for Navigation Rule
	public String onEntityTypeChange(ValueChangeEvent event) {

		

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();

		if (!event.getNewValue().equals("")) {
			String selectedEntity = (String) event.getNewValue();
			if(logger.isDebugEnabled())
			{
			logger.debug("****selectedEntity****" + selectedEntity);
			}

			getCorrEntIDTypeValues(selectedEntity);

			if ("P".equals(selectedEntity)) {
				
				searchCorrespondenceDataBean
						.setProvTypeVVList(getProvTypeReferenceData());

			}
		}
		//Note: Added for Navigation Rule
		return "success";

	}

	/**
	 * renders the detail part based on the filter.
	 * 
	 * @param event
	 *            event.
	 */

	public void onLobChange(ValueChangeEvent event) {

		

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		List listSK = new ArrayList();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		if (!event.getNewValue().equals("")) {
			String selectedLOB = (String) event.getNewValue();
			
			//FindBug Issue Resolved
			//LineOfBusinessHierarchy lineOfBusinessHierarchy = new
			// LineOfBusinessHierarchy();
			LineOfBusinessHierarchy lineOfBusinessHierarchy = null;
			try {
				lineOfBusinessHierarchy = contactMaintenanceDelegate
						.getLobHierarchyRoot(selectedLOB);

				if (lineOfBusinessHierarchy != null) {
					listSK.add(lineOfBusinessHierarchy
							.getLineOfBusinessHierarchySK());
				}

			} catch (Exception exe) {
				exe.printStackTrace();
			}
			//"FindBugs" issue fixed
			//List deptList = new ArrayList();
			List deptList = null;
			//List userList = new ArrayList();
			List userList = null;

			if (searchCorrespondenceDataBean.getListOfRepUnits() != null
					&& searchCorrespondenceDataBean.getListOfRepUnits().size() > 0) {
				searchCorrespondenceDataBean.getListOfRepUnits().clear();
			}

			if (searchCorrespondenceDataBean.getListOfRefBusUnits() != null
					&& searchCorrespondenceDataBean.getListOfRefBusUnits()
							.size() > 0) {
				searchCorrespondenceDataBean.getListOfRefBusUnits().clear();
			}

			if (searchCorrespondenceDataBean.getListOfRefDeptUnits() != null
					&& searchCorrespondenceDataBean.getListOfRefDeptUnits()
							.size() > 0) {
				searchCorrespondenceDataBean.getListOfRefDeptUnits().clear();

			}

			deptList = fillLobHierarchyDetails(listSK,
					ContactManagementConstants.TWO);

			try {
				if(logger.isDebugEnabled())
				{
					logger.debug("deptlist size onLobChange" + deptList.size());
				}
				if (!deptList.isEmpty()) {
					userList = contactMaintenanceDelegate.getUsers(deptList);
					List listOfUsers = new ArrayList();

					if (userList != null) {
						for (Iterator iter = userList.iterator(); iter
								.hasNext();) {
							DepartmentUser deptUser = (DepartmentUser) iter
									.next();

							EnterpriseUser user = deptUser.getEnterpriseUser();

							StringBuffer userDesc = new StringBuffer(
									ContactManagementConstants.EMPTY_STRING);

							if (user.getLastName() != null) {
								userDesc
										.append(user.getLastName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getMiddleName() != null) {
								userDesc
										.append(user.getMiddleName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getFirstName() != null) {
								userDesc
										.append(user.getFirstName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getPrefixName() != null) {
								userDesc
										.append(user.getPrefixName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getSuffixName() != null) {
								userDesc.append(user.getSuffixName());

							}
							if (testSet.add(user.getUserWorkUnitSK().toString()
									+ userDesc.toString())) {
								listOfUsers.add(new SelectItem(user
										.getUserWorkUnitSK().toString(),
										userDesc.toString()));
							}
						}
					}//if userList not null check end

					searchCorrespondenceDataBean.setAssignedToList(listOfUsers);
					searchCorrespondenceDataBean
							.assignedToComparator(searchCorrespondenceDataBean
									.getAssignedToList());
				}
			}
			//FindBug Issue Resolved
			/*
			 * catch (Exception exe) { logger.debug("Exception caught in
			 * onLOBchange--controllerBean");
			 *  }
			 */
			catch (LOBHierarchyFetchBusinessException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param listSK
	 *            the listsk to set
	 * @param level
	 *            the levelof lob
	 * @return List
	 */
	private List fillLobHierarchyDetails(List listSK, Integer level) {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		List list = new ArrayList();
		//"FindBugs" issue fixed
		//List lobHierarchyList = new ArrayList();
		List lobHierarchyList = null;
		//"FindBugs" issue fixed
		//Integer levelNum = new Integer(0);
		Integer levelNum = Integer.valueOf(0);
		List deptList = new ArrayList();
		List deptListSK = new ArrayList();

		try {

			if (level.equals(ContactManagementConstants.TWO)
					|| level.equals(ContactManagementConstants.THREE)) {

				lobHierarchyList = contactMaintenanceDelegate
						.getLobHierarchyDetails(listSK, null);
				Iterator it1 = lobHierarchyList.iterator();
				while (it1.hasNext()) {

					LineOfBusinessHierarchy element = (LineOfBusinessHierarchy) it1
							.next();
					if (element.getLobHierarchyLevelNumber().equals(
							ContactManagementConstants.TWO)) {
						getSearchCorrespondenceDataBean().getListOfRepUnits()
								.add(
										new SelectItem(element
												.getLineOfBusinessHierarchySK()
												.toString(), element
												.getLobHierarchyDescription()));
						levelNum = ContactManagementConstants.THREE;
						list.add(element.getLineOfBusinessHierarchySK());
					} else if (element.getLobHierarchyLevelNumber().equals(
							ContactManagementConstants.THREE)) {

						getSearchCorrespondenceDataBean()
								.getListOfRefBusUnits().add(
										new SelectItem(element
												.getLineOfBusinessHierarchySK()
												.toString(), element
												.getLobHierarchyDescription()));
						levelNum = ContactManagementConstants.FOUR;
						list.add(element.getLineOfBusinessHierarchySK());
					}
				}
			} else if (level.equals(ContactManagementConstants.FOUR)) {

				//Below code modified for new API on 03/07/2012 - Start
				
				/*lobHierarchyList = contactMaintenanceDelegate
						.getDepartmentDetails(listSK);*/
				DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
				
				//Code changed below for fixing the DEFECT ESPRD00773742 on 29-03-2012
				
				/*deptUserBasicInfo.setDataReqested("lobHierarchySKForDepartment");
				deptUserBasicInfo.setLobSKlist(listSK);*/
				
				deptUserBasicInfo.setDataReqested(ProgramConstants.LOB_Hierarchy_SK_List_FOR_DEPARTMENT_OBJ);
				deptUserBasicInfo.setLobHierarchySKList(listSK);
				lobHierarchyList = contactMaintenanceDelegate.getDepartmentInfo(deptUserBasicInfo);
				Iterator it2 = lobHierarchyList.iterator();
				while (it2.hasNext()) {
					/*Department element = (Department) it2.next();*/
					DeptUserBasicInfo element = (DeptUserBasicInfo) it2.next();
					/*getSearchCorrespondenceDataBean().getListOfRefDeptUnits()
							.add(
									new SelectItem(element
											.getLineOfBusinessHierarchy()
											.getLineOfBusinessHierarchySK()
											.toString(), element.getName()));*/
					
					getSearchCorrespondenceDataBean().getListOfRefDeptUnits().add(
							new SelectItem(element.getLobHierarchySK().toString(),element.getDeptName()));

					deptList.add(element.getWorkUnitSK());
					/*deptListSK.add(element.getLineOfBusinessHierarchy()
							.getLineOfBusinessHierarchySK());*/
					deptListSK.add(element.getLobHierarchySK());
					
					//Below code modified for new API on 03/07/2012 - End
				}
				getSearchCorrespondenceDataBean()
						.getCorrespondenceSearchCriteriaVO()
						.getAdvSearchCriteria().setMaintainGroups(deptListSK);
			}
            //"FindBugs" issue fixed
			/*if (!new Integer(0).equals(levelNum)) {
				deptList = fillLobHierarchyDetails(list, levelNum);
			}*/
			if (!Integer.valueOf(0).equals(levelNum)) {
				deptList = fillLobHierarchyDetails(list, levelNum);
			}
		}
		//FindBUg Issue Resolved
		/*
		 * catch (Exception e) { logger .debug("Exception caught in
		 * fillLobHierarchyDetails--controllerBean"); }
		 */
		catch (LOBHierarchyFetchBusinessException e) {
			e.printStackTrace();
		}

		return deptList;
	}

	/**
	 * @param event
	 *            Takes ValueChangeEvent as param
	 */
	public void onReportingUnitChange(ValueChangeEvent event) {

		

		if (!event.getNewValue().equals("")) {
			SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
			List listSK = new ArrayList();
			List deptList = new ArrayList();
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			if (!event.getNewValue().toString().equals("")) {
				Long selectedRU = Long.valueOf(event.getNewValue().toString());

				

				if (searchCorrespondenceDataBean.getListOfRefBusUnits() != null
						&& searchCorrespondenceDataBean.getListOfRefBusUnits()
								.size() > 0) {
					searchCorrespondenceDataBean.getListOfRefBusUnits().clear();
				}

				if (searchCorrespondenceDataBean.getListOfRefDeptUnits() != null
						&& searchCorrespondenceDataBean.getListOfRefDeptUnits()
								.size() > 0) {
					searchCorrespondenceDataBean.getListOfRefDeptUnits()
							.clear();
				}

				try {

					listSK.add(selectedRU);

					deptList = fillLobHierarchyDetails(listSK,
							ContactManagementConstants.THREE);
				} catch (Exception exe) {
					exe.printStackTrace();
				}
			}
			//FindBug Issue Resolved
			//List userList = new ArrayList();
			List userList = null;
			try {
				
				if (!deptList.isEmpty()) {
					userList = contactMaintenanceDelegate.getUsers(deptList);
					
					List listOfUsers = new ArrayList();

					if (userList != null) {
						for (Iterator iter = userList.iterator(); iter
								.hasNext();) {
							DepartmentUser deptUser = (DepartmentUser) iter
									.next();

							EnterpriseUser user = deptUser.getEnterpriseUser();

							StringBuffer userDesc = new StringBuffer(
									ContactManagementConstants.EMPTY_STRING);

							if (user.getLastName() != null) {
								userDesc
										.append(user.getLastName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getMiddleName() != null) {
								userDesc
										.append(user.getMiddleName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getFirstName() != null) {
								userDesc
										.append(user.getFirstName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getPrefixName() != null) {
								userDesc
										.append(user.getPrefixName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getSuffixName() != null) {
								userDesc.append(user.getSuffixName());

							}
							if (testSet.add(user.getUserWorkUnitSK().toString()
									+ userDesc.toString())) {
								listOfUsers.add(new SelectItem(user
										.getUserWorkUnitSK().toString(),
										userDesc.toString()));
							}
						}
					}//userList not null check ends

					searchCorrespondenceDataBean.setAssignedToList(listOfUsers);
					searchCorrespondenceDataBean
							.assignedToComparator(searchCorrespondenceDataBean
									.getAssignedToList());
				}
			}
			//FindBug Issue Resolved
			/*
			 * catch (Exception exe) { logger .debug("Exception caught in
			 * onReportingUnitChange--controllerBean"); }
			 */
			catch (LOBHierarchyFetchBusinessException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param event
	 *            takes event as param
	 */
	public void onBusinessUnitChange(ValueChangeEvent event) {

		

		if (!event.getNewValue().equals("")) {
			SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
			List listSK = new ArrayList();
			List deptList = new ArrayList();

			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			Long selectedBU = Long.valueOf(event.getNewValue().toString());

			

			if (searchCorrespondenceDataBean.getListOfRefDeptUnits() != null
					&& searchCorrespondenceDataBean.getListOfRefDeptUnits()
							.size() > 0) {
				searchCorrespondenceDataBean.getListOfRefDeptUnits().clear();
			}

			try {
				listSK.add(selectedBU);

				deptList = fillLobHierarchyDetails(listSK,
						ContactManagementConstants.FOUR);
			} catch (Exception exe) {
				exe.printStackTrace();

			}
			//FindBug Issue Resolved
			//List userList = new ArrayList();
			List userList = null;
			try {
				
				if (!deptList.isEmpty()) {
					userList = contactMaintenanceDelegate.getUsers(deptList);
					List listOfUsers = new ArrayList();

					if (userList != null) {
						for (Iterator iter = userList.iterator(); iter
								.hasNext();) {
							DepartmentUser deptUser = (DepartmentUser) iter
									.next();

							EnterpriseUser user = deptUser.getEnterpriseUser();

							StringBuffer userDesc = new StringBuffer(
									ContactManagementConstants.EMPTY_STRING);

							if (user.getLastName() != null) {
								userDesc
										.append(user.getLastName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getMiddleName() != null) {
								userDesc
										.append(user.getMiddleName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getFirstName() != null) {
								userDesc
										.append(user.getFirstName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getPrefixName() != null) {
								userDesc
										.append(user.getPrefixName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getSuffixName() != null) {
								userDesc.append(user.getSuffixName());

							}
							if (testSet.add(user.getUserWorkUnitSK().toString()
									+ userDesc.toString())) {
								listOfUsers.add(new SelectItem(user
										.getUserWorkUnitSK().toString(),
										userDesc.toString()));
							}
						}
					}//userList not null check ends

					searchCorrespondenceDataBean.setAssignedToList(listOfUsers);
					searchCorrespondenceDataBean
							.assignedToComparator(searchCorrespondenceDataBean
									.getAssignedToList());
				}
			}
			//FindBug Issue Resolved
			/*
			 * catch (Exception exe) { logger .debug("Exception caught in
			 * onBusinessUnitChange--controllerBean"); }
			 */
			catch (LOBHierarchyFetchBusinessException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param event
	 *            takes event as param
	 */
	public void onDepartmentChange(ValueChangeEvent event) {
		

		if (!event.getNewValue().equals("")) {
			SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
			List listSK = new ArrayList();
			List deptList = new ArrayList();

			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			Long selectedDept = Long.valueOf(event.getNewValue().toString());

			

			if (searchCorrespondenceDataBean.getAssignedToList() != null
					&& searchCorrespondenceDataBean.getAssignedToList().size() > 0) {
				searchCorrespondenceDataBean.getAssignedToList().clear();
			}

			try {
				listSK.add(selectedDept);
				List dList = new ArrayList(contactMaintenanceDelegate
						.getLobHierarchyRecord(selectedDept).getDepartments());
				Iterator iterator = dList.iterator();
				if (iterator.hasNext()) {
					Department dep = (Department) iterator.next();
					deptList.add(dep.getWorkUnitSK());
				}
			} catch (Exception exe) {
				exe.printStackTrace();
			}
			//FindBug Issue Resolved
			//List userList = new ArrayList();
			List userList = null;
			try {
				
				if (!deptList.isEmpty()) {
					userList = contactMaintenanceDelegate.getUsers(deptList);
					List listOfUsers = new ArrayList();

					if (userList != null) {
						for (Iterator iter = userList.iterator(); iter
								.hasNext();) {
							DepartmentUser deptUser = (DepartmentUser) iter
									.next();

							EnterpriseUser user = deptUser.getEnterpriseUser();

							StringBuffer userDesc = new StringBuffer(
									ContactManagementConstants.EMPTY_STRING);

							if (user.getLastName() != null) {
								userDesc
										.append(user.getLastName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getMiddleName() != null) {
								userDesc
										.append(user.getMiddleName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getFirstName() != null) {
								userDesc
										.append(user.getFirstName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getPrefixName() != null) {
								userDesc
										.append(user.getPrefixName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getSuffixName() != null) {
								userDesc.append(user.getSuffixName());

							}
							if (testSet.add(user.getUserWorkUnitSK().toString()
									+ userDesc.toString())) {
								listOfUsers.add(new SelectItem(user
										.getUserWorkUnitSK().toString(),
										userDesc.toString()));
							}
						}
					}//userList not null check ends

					searchCorrespondenceDataBean.setAssignedToList(listOfUsers);
					searchCorrespondenceDataBean
							.assignedToComparator(searchCorrespondenceDataBean
									.getAssignedToList());
				}
			}
			//FindBug Issue Resolved
			/*
			 * catch (Exception exe) { logger .debug("Exception caught in
			 * onDeptChange--controllerBean"); }
			 */
			catch (LOBHierarchyFetchBusinessException exe) {
				exe.printStackTrace();
			}
		}

	}

	/**
	 * This method will return the reference of the data bean.
	 * 
	 * @return SystemParameterDataBean
	 */
	protected SearchCorrespondenceDataBean getSearchCorrespondenceDataBean() {

		SearchCorrespondenceDataBean searchCorrespondenceDataBean;

		FacesContext fc = FacesContext.getCurrentInstance();

		searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) fc
				.getApplication().getVariableResolver().resolveVariable(fc,
						ContactManagementConstants.SEARCHCORRESPONDENCE_BEAN_NAME);

		if (searchCorrespondenceDataBean == null) {

			searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) fc

			.getApplication().createValueBinding(

			"#{" + ContactManagementConstants.SEARCHCORRESPONDENCE_BEAN_NAME + "}")

			.getValue(fc);

		}

		return searchCorrespondenceDataBean;

	}
	//Method added for CR:ESPRD00798895
	/**
	 * This method will fetch results based on Input search Criteria.
	 * 
	 * @return success
	 */
	public String searhCorrespondence()
    {
    	SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
    	
    	searchCorrespondenceDataBean.setSortFlag(false);
    	searchCorrespondenceDataBean.setStartRecord(0);
    	searchCorrespondenceDataBean.setStartRecordNo(searchCorrespondenceDataBean.getStartRecord());
    	searchCorrespondenceDataBean.setCurrentPage(1);
    	searchCorrespondenceDataBean.setShowPrevious(false);
    	searchCorrespondenceDataBean.setShowNext(false);
        getCorrespondence();
    	
    	return "";
    }

	/**
	 * This method will fetch results based on Input search Criteria.
	 * 
	 * @return success
	 */
	public String getCorrespondence() {

		long entryTime = System.currentTimeMillis();
		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		searchCorrespondenceDataBean.setSearchSelectAll(false);
		searchCorrespondenceDataBean.setSearchDeSelectAll(false);
		
		//heap dump issue fix
		/*if (searchCorrespondenceDataBean.getSearchDataTable() != null) {
		searchCorrespondenceDataBean.getSearchDataTable().setFirst(0);
		}*/
		searchCorrespondenceDataBean.setStartIndexForSrchRslts(0);
		//for fixing defect ESPRD00577524
		/*if (searchCorrespondenceDataBean.getHtmlSearchResults() != null) {
			searchCorrespondenceDataBean.getHtmlSearchResults().setFirst(0);
		}*/
		searchCorrespondenceDataBean.setStartIndexForAscSrchRslts(0);

		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = new CorrespondenceSearchCriteriaVO();
		correspondenceSearchCriteriaVO = searchCorrespondenceDataBean
				.getCorrespondenceSearchCriteriaVO();
		
		
	  // Code Added for CR:ESPRD00798895 for pagination
		correspondenceSearchCriteriaVO.setStartIndex(searchCorrespondenceDataBean.getStartRecord());
	  correspondenceSearchCriteriaVO.setRowsPerPage(searchCorrespondenceDataBean.getItemsPerPage()); 
		
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		CMDelegate cmDelegate = new CMDelegate();
		//commented for unused variables
		//ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		//commented for unused variables
		//List businessUnitsList = new ArrayList();
		if(logger.isDebugEnabled())
		{
		logger.debug("EntityID   $$  "
				+ correspondenceSearchCriteriaVO.getEntityID());
		logger.debug("EntityType   $$  "
				+ correspondenceSearchCriteriaVO.getEntityType());
		}
		validateSearch(correspondenceSearchCriteriaVO);
		correspondenceSearchCriteriaVO.setUserID(getUserID());
		correspondenceSearchCriteriaVO.setAssocSrch(false);
		boolean assocSrch = searchCorrespondenceDataBean.isSearchAssocCR();
		Object obj = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		ActionRequest request = null;
		RenderRequest renderRequest = null;
		String reassignCorrespondence = null;
		boolean isReassignPage=Boolean.FALSE;
		if (obj instanceof ActionRequest) {
			request = (ActionRequest) obj;
			reassignCorrespondence = request
					.getParameter("reassignCorrespondence");
		} else {
			renderRequest = (RenderRequest) obj;
			reassignCorrespondence = renderRequest
					.getParameter("reassignCorrespondence");
		}
		if(logger.isInfoEnabled())
		{
		logger.info("-------------searchCrsIdenfication-----------------"
				+ reassignCorrespondence);
		}
		if (reassignCorrespondence != null
				&& (reassignCorrespondence
						.equalsIgnoreCase("reassignCorrespondence")))
		{
			//added for CR:ESPRD00798895
			isReassignPage=Boolean.TRUE;
			correspondenceSearchCriteriaVO.setReassginCr(true);
			 getCorrespondenceForReassign(correspondenceSearchCriteriaVO) ;
			assocSrch = true;
		}

		if (flag) {
			boolean contactboolean = false;

			try {
				if (correspondenceSearchCriteriaVO != null
						&& correspondenceSearchCriteriaVO.getCrn() != null
						&& !correspondenceSearchCriteriaVO.getCrn().equals(
								"null")
						&& correspondenceSearchCriteriaVO.getCrn().trim()
								.length() != 0) {
					Long crRecordNo = Long
							.valueOf(correspondenceSearchCriteriaVO.getCrn());
					correspondenceSearchCriteriaVO
							.setCorrespondenceRecordNumber(crRecordNo);
				} else {
					correspondenceSearchCriteriaVO
							.setCorrespondenceRecordNumber(null);
				}
				if (correspondenceSearchCriteriaVO != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria() != null) {
					if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
							.getReportingUnit() == null) {
						correspondenceSearchCriteriaVO.getAdvSearchCriteria()
								.setReportingUnit("");
					}
					//DEFECT(FIX) ID:ESPRD00665628 USECASE:UC-PGM-CRM-015
					//DEFECT(FIX) ID:ESPRD00665635 USECASE:UC-PGM-CRM-074
					//commented for unused variables
					//CRAdvanceSearchCriteriaVO crAdvanceSearchCriteriaVO = new CRAdvanceSearchCriteriaVO();
					if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
							.getContact() != null
							&& !correspondenceSearchCriteriaVO
									.getAdvSearchCriteria().getContact()
									.equals("null")
							&& correspondenceSearchCriteriaVO
									.getAdvSearchCriteria().getContact().trim()
									.length() > 0
							&& correspondenceSearchCriteriaVO
									.getAdvSearchCriteria().getContact()
									.indexOf("'") >= 0) {
						String contact = correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getContact();
						contact = contact.replaceAll("'", "''");
						correspondenceSearchCriteriaVO.getAdvSearchCriteria()
								.setContact(contact);
						contactboolean = true;
					}
					//END
					if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
							.getDepartment() != null
							&& !correspondenceSearchCriteriaVO
									.getAdvSearchCriteria().getDepartment()
									.equals("null")
							&& correspondenceSearchCriteriaVO
									.getAdvSearchCriteria().getDepartment()
									.trim().length() > 0) {
						if(logger.isDebugEnabled())
						{
						logger
								.debug("Inside getCorrespondence Setting Dept"
										+ correspondenceSearchCriteriaVO
												.getAdvSearchCriteria()
												.getDepartment());
						}
						List listSK = new ArrayList();
						listSK.add(correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getDepartment());
						correspondenceSearchCriteriaVO.getAdvSearchCriteria()
								.setMaintainGroups(listSK);
					}
				}
				/**
				 * Newly added for category --if no categoy selected send the
				 * enire list
				 */

				if (isNullOrEmptyField(correspondenceSearchCriteriaVO
						.getCategory())) {
					
					correspondenceSearchCriteriaVO
							.setCategoryList(searchCorrespondenceDataBean
									.getCatListToBeSentInSearchCritVO());
					if(logger.isDebugEnabled())
					{
					logger.debug("size of cat list in search crit vo "
							+ correspondenceSearchCriteriaVO.getCategoryList()
									.size());
					}
				}

				/** If category is seleced at the category to he list */
				else {
					
					correspondenceSearchCriteriaVO.getCategoryList().add(
							correspondenceSearchCriteriaVO.getCategory());
				}

				if (assocSrch) {
					correspondenceSearchCriteriaVO.setAssocSrch(true);
				}

				/*if (obj instanceof ActionRequest) {
					request = (ActionRequest) obj;
					reassignCorrespondence = request
							.getParameter("reassignCorrespondence");
				} else {
					renderRequest = (RenderRequest) obj;
					reassignCorrespondence = renderRequest
							.getParameter("reassignCorrespondence");
				}
				if(logger.isInfoEnabled())
				{
				logger
						.info("-------------searchCrsIdenfication-----------------"
								+ reassignCorrespondence);
				}*/
				//commented for CR:ESPRD00798895
				/*if (reassignCorrespondence != null
						&& (reassignCorrespondence
								.equalsIgnoreCase("reassignCorrespondence"))) {
					correspondenceSearchCriteriaVO.setReassginCr(true);
				}*/
			     if(!searchCorrespondenceDataBean.isSortFlag())
			     {
				correspondenceSearchCriteriaVO
						.setSortColumn(ContactManagementConstants.ORDERBY_CRN);
				correspondenceSearchCriteriaVO.setAscending(false);
			     }
			    if(correspondenceSearchCriteriaVO != null)
			    {
			    	enterpriseSearchResultsVO = cmDelegate.getCorrespondence(correspondenceSearchCriteriaVO);
			    }
				
				
				//Code Added for CR:ESPRD00798895 for pagination
				 if(enterpriseSearchResultsVO != null)
				 {
				 int recordCount = (int)  enterpriseSearchResultsVO.getRecordCount();
				 searchCorrespondenceDataBean.setCount(Integer.valueOf(String.valueOf(enterpriseSearchResultsVO.getRecordCount())));
                 
                 if (searchCorrespondenceDataBean.getStartRecord() == 0) {
                 	searchCorrespondenceDataBean.setStartRecordNo(searchCorrespondenceDataBean.getStartRecord() + 1);
                 	searchCorrespondenceDataBean.setCurrentPage(1);
 					if(enterpriseSearchResultsVO.getRecordCount() < searchCorrespondenceDataBean.getItemsPerPage())
 					{
 						searchCorrespondenceDataBean.setEndRecord(recordCount);
 						searchCorrespondenceDataBean.setShowNext(false);
 						searchCorrespondenceDataBean.setShowPrevious(false);
 						searchCorrespondenceDataBean.setShowNextOne(false);
 					}
 					else
 					{
 						searchCorrespondenceDataBean.setEndRecord(searchCorrespondenceDataBean.getStartRecord() + searchCorrespondenceDataBean.getItemsPerPage());
 						searchCorrespondenceDataBean.setShowNext(true);
 						searchCorrespondenceDataBean.setShowPrevious(false);
 						searchCorrespondenceDataBean.setShowNextOne(false);
 					}
 					
 				}
 				if(searchCorrespondenceDataBean.getCurrentPage() == 1 && 
 						enterpriseSearchResultsVO.getRecordCount() > 2*searchCorrespondenceDataBean.getItemsPerPage()) // Added for the dataScroller Fix
 				{
 					searchCorrespondenceDataBean.setShowNextOne(true);
 					
 				}
				 }
 				
 				// CR:ESPRD00798895 END
				
				
				//DEFECT(FIX) ID:ESPRD00665628 USECASE:UC-PGM-CRM-015
				//DEFECT(FIX) ID:ESPRD00665635 USECASE:UC-PGM-CRM-074
				if (contactboolean) {
					String contact = correspondenceSearchCriteriaVO
							.getAdvSearchCriteria().getContact();
					contact = contact.replaceAll("''", "'");
					correspondenceSearchCriteriaVO.getAdvSearchCriteria()
							.setContact(contact);
				}
				//END
				List searchResults = null;
				if (enterpriseSearchResultsVO != null) {
					searchResults = enterpriseSearchResultsVO
							.getSearchResults();
				}
				if (searchResults != null) {
					searchCorrespondenceDataBean
							.setSearchCorrespondenceListSize(enterpriseSearchResultsVO
									.getSearchResults().size());
					searchCorrespondenceDataBean.setShowResultsDiv(true);
					searchCorrespondenceDataBean.setRenderReassignBtn(false);
					searchCorrespondenceDataBean.setNoDataFlag(Boolean.FALSE);
					
					// CR:ESPRD00798895
					
					if (isReassignPage && (searchCorrespondenceDataBean
							.getReassignAllDeptUsersList().size() == 0
							&& searchCorrespondenceDataBean
									.getReassignAllDeptUsersList().isEmpty())) {
						getAllDeptUsersForReassign();
					}
				}
				

				else {
					setErrorMessage(
							EnterpriseMessageConstants.AUTHORITY_CR_ERROR_CODE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
					searchCorrespondenceDataBean.setShowResultsDiv(false);
					searchCorrespondenceDataBean.setRenderReassignBtn(false);

				}
				if (searchResults != null && searchResults.size() == 0 ) {
					
					if (isReassignPage)
					{
						searchCorrespondenceDataBean.setNoDataFlag(Boolean.TRUE);
						
					}
					else{
						setErrorMessage(
								EnterpriseMessageConstants.ERR_GLB_NO_SEARCH_RESULT_FOUND,
								new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
								null);
						searchCorrespondenceDataBean.setShowResultsDiv(false);
						searchCorrespondenceDataBean.setRenderReassignBtn(false);
					}
				}
				if(logger.isDebugEnabled())
				{
				logger.debug("U R in Associated Search-->"
						+ searchCorrespondenceDataBean.isSearchAssocCR());
				}
				if (searchResults != null && searchResults.size() == 1
						&& (!assocSrch)) {

					
					Correspondence correspondence = (Correspondence) enterpriseSearchResultsVO
							.getSearchResults().get(0);

					String correspondenceSk = correspondence
							.getCorrespondenceSK().toString();

					showDetailPortletForCrspd(correspondenceSk);
					//clearing results in case values are persisted from
					// previous search
					searchCorrespondenceDataBean.getSearchResults().clear();
					//Uncommented the below line for defect - ESPRD00839561: Not to display the invalid search result grid on search CR page.
					searchCorrespondenceDataBean.setShowResultsDiv(false);
					searchCorrespondenceDataBean.setNoDataFlag(Boolean.TRUE);

				}
				if ((searchResults != null && searchResults.size() > 1)
						|| (searchResults != null && searchResults.size() > 0 && assocSrch)) {
					
					searchCorrespondenceDataBean.setShowResultsDiv(true);
					searchCorrespondenceDataBean.setNoDataFlag(Boolean.FALSE);
					if (isReassignPage)
					{
						
						Iterator itr = searchResults.iterator();
						Map userListMap = new HashMap();
						ContactManagementHelper helper=new ContactManagementHelper();
						searchCorrespondenceDataBean.setRenderReassignBtn(true);
						String reassignAllSelect=searchCorrespondenceDataBean.getSelectedDeptAll();
						while (itr.hasNext()) {
							List userList = null;
							List deptListTemp = new ArrayList();
							CorrespondenceSearchResultsVO crResultVo = (CorrespondenceSearchResultsVO) itr
									.next();
							//Code added for CR:ESPRD00798895
							if(reassignAllSelect!=null && !ContactManagementConstants.EMPTY_STRING.equals(reassignAllSelect.trim())){
								crResultVo.setReassignAllDesc(helper.getDescriptionFromVV(reassignAllSelect, searchCorrespondenceDataBean
										.getReassignAllDeptUsersList()));
								}
							
							if (crResultVo != null
									&& null != crResultVo.getLobHierarchySK()) {
								if (!userListMap.containsKey(crResultVo
										.getLobHierarchySK().toString())) 
								{
									userList = crResultVo.getDeptList();

									List sortList = new ArrayList();
									if (userList != null && userList.size() > 0) {
										
										for (Iterator iter = userList
												.iterator(); iter.hasNext();) {
											Object[] userData = (Object[]) iter
													.next();
											String userDesc = ContactManagementConstants.EMPTY_STRING;
											String userID = null;
											if (userData[2] != null) {
												userDesc = userData[2]
														.toString();
											}
											if ((userData[2] != null)
													&& (userData[1] != null)) {
												userDesc = userDesc
														+ ContactManagementConstants.COMMA_SEPARATOR
														+ ContactManagementConstants.SPACE_STRING;
											}
											if (userData[1] != null) {
												userDesc = userDesc
														+ userData[1];
											}
											if (userData[3] != null) {
												userID = userData[3].toString();
											}
											if (userDesc != null
													&& userDesc.length() > 0
													&& userData[0] != null) {
												if (userID != null) {
													if (!sortList
															.contains((userDesc
																	+ ContactManagementConstants.HYPHEN_SEPARATOR + userID))) {
														SelectItem selectItem = new SelectItem(
																userData[0]
																		.toString()
																		.trim(),
																userDesc
																		+ ContactManagementConstants.HYPHEN_SEPARATOR
																		+ userID);
														sortList
																.add((userDesc
																		+ ContactManagementConstants.HYPHEN_SEPARATOR + userID));
														deptListTemp
																.add(selectItem);
													}
												} else {
													if (!sortList
															.contains((userDesc))) {
														SelectItem selectItem = new SelectItem(
																userData[0]
																		.toString()
																		.trim(),
																userDesc);
														sortList.add(userDesc);
														deptListTemp
																.add(selectItem);
													}
												}
											}
										}
										crResultVo.getDeptList().clear();
									}
									userListMap.put(crResultVo
											.getLobHierarchySK().toString(),
											deptListTemp);
								} else {
									deptListTemp = (List) userListMap
											.get(crResultVo.getLobHierarchySK()
													.toString());
								}
							}
							sortListSequence(deptListTemp);
							crResultVo.setDeptList(deptListTemp);
						}
						userListMap.clear();
						//for CR CR:ESPRD00798895
//						getAllDeptUsersForReassign();
					} else if (assocSrch && searchResults.size() == 1) {
						FacesContext context = FacesContext
								.getCurrentInstance();
						CorrespondenceControllerBean controllerBean = (CorrespondenceControllerBean) context
								.getApplication().createValueBinding(
										"#{CorrespondenceControllerBean}")
								.getValue(context);

						controllerBean
								.moveSingleCRToAssociateCRs((CorrespondenceSearchResultsVO) searchResults
										.get(0));
						searchCorrespondenceDataBean.setShowResultsDiv(false);
					}

					if (searchResults != null && searchResults.size() > 0) {
						Iterator searchResultsItr = searchResults.iterator();
						Map entityTypeMap = searchCorrespondenceDataBean
								.getEntityTypeMap();
						Map statusMap = searchCorrespondenceDataBean
								.getStatusMap();
						Map subjectMap = searchCorrespondenceDataBean
								.getSubjectMap();

						while (searchResultsItr.hasNext()) {
							CorrespondenceSearchResultsVO searchResultVO = (CorrespondenceSearchResultsVO) searchResultsItr
									.next();
							if (searchResultVO != null) {
								if (entityTypeMap.containsKey(searchResultVO
										.getEntityType())) {
									searchResultVO
											.setEntityType(entityTypeMap.get(
													searchResultVO
															.getEntityType())
													.toString());
								}
								if (statusMap.containsKey(searchResultVO
										.getStatus())) {
									searchResultVO.setStatus(statusMap.get(
											searchResultVO.getStatus())
											.toString());
								}
								if (subjectMap.containsKey(searchResultVO
										.getSubjectCode())) {
									searchResultVO.setSubjectCode(subjectMap
											.get(
													searchResultVO
															.getSubjectCode())
											.toString());
								}
								if (searchResultVO.getCreatedOn() != null) {
									searchResultVO.setCreatedOnstr(dateFormat
											.format(searchResultVO
													.getCreatedOn()));
								}

							}
						}
					}
					searchCorrespondenceDataBean
							.setSearchResults(searchResults);
					//searchCorrespondenceDataBean.setShowResultsDiv(true);
				}
			} catch (CorrespondenceRecordFetchBusinessException exe) {
				/*
				 * Author: Infinite CR 2196 Date : 31/12/2008
				 */
				exe.printStackTrace();
				if(logger.isErrorEnabled())
				{
				logger.error(exe.getMessage(), exe);
				}
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
						new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
				//searchCorrespondenceDataBean.setShowResultsDiv(false);
				searchCorrespondenceDataBean.setNoDataFlag(Boolean.TRUE);
			
			}
			/*
			 * catch (LOBHierarchyFetchBusinessException e) { setErrorMessage(
			 * EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE, new Object[]
			 * {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			 * searchCorrespondenceDataBean.setShowResultsDiv(false); logger
			 * .debug("Exception caught in getCorrespondece() of controller
			 * bean******************");
			 *  }
			 */

		} else {
			searchCorrespondenceDataBean.setNoDataFlag(Boolean.TRUE);
			searchCorrespondenceDataBean.setShowResultsDiv(false);
			long exitTime = System.currentTimeMillis();
			if(logger.isInfoEnabled())
			{
			logger.info(ProgramConstants.SERACH_SYSPARAM + ProgramConstants.SYM
					+ ProgramConstants.SRCH_SYS_PARAM + ProgramConstants.SYM
					+ (exitTime - entryTime));
			}
		}
		//Code added for the defect-ESPRD00808853 to fill the AssignedTo and createdBy drop down values.
		searchCorrespondenceDataBean.getAllUsers();
		
		searchCorrespondenceDataBean.setPaginationFlag(false);
		return "success";
	}

	/**
	 * Resets the correspondenceSearchCriteriaVO.
	 */
	//Note: Changed return type for Navigation Rule
	public String resetCorrespondence() {
		

		CorrespondenceDOConvertor correspondenceDOConvertor = new CorrespondenceDOConvertor();
		String businessUnitDesc = ContactManagementConstants.CORR_ALLOTHERS;

		Long userSK = correspondenceDOConvertor.getUserSK(getUserID());
		businessUnitDesc = correspondenceDOConvertor
				.getBusinessUnitforUser(userSK);

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		searchCorrespondenceDataBean.setShowResultsDiv(false);
		searchCorrespondenceDataBean.setRenderReassignBtn(false);
		
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = new CorrespondenceSearchCriteriaVO();
		CRAdvanceSearchCriteriaVO advanceSearchCriteriaVO = new CRAdvanceSearchCriteriaVO();
		correspondenceSearchCriteriaVO
				.setAdvSearchCriteria(advanceSearchCriteriaVO);
		searchCorrespondenceDataBean
				.setCorrespondenceSearchCriteriaVO(correspondenceSearchCriteriaVO);
		//searchCorrespondenceDataBean.getProvTypeVVList().clear();
		searchCorrespondenceDataBean
				.setSubjectVVList(searchCorrespondenceDataBean
						.getSubjectReferenceData(businessUnitDesc));
		searchCorrespondenceDataBean
				.setSourceVVList(searchCorrespondenceDataBean
						.getSourceReferenceData(businessUnitDesc));
		searchCorrespondenceDataBean
				.setResolnVVList(searchCorrespondenceDataBean
						.getResolnReferenceData(businessUnitDesc));
		searchCorrespondenceDataBean.setLobVVList(searchCorrespondenceDataBean
				.getLobReferenceData());
		searchCorrespondenceDataBean.getAllHierarchies();
		searchCorrespondenceDataBean
				.setSelectedDeptAll(ContactManagementConstants.EMPTY_STRING);
		//Code added for the defect ESPRD00808853 to fill the AssignedTo and createdBy drop down values.
		searchCorrespondenceDataBean.getAllUsers();
		//Note: Changed return type for Navigation Rule
		return "success";
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
	protected void setErrorMessage(String errorName, Object[] arguments,
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
			if (uiComponent != null) {
				clientId = uiComponent.getClientId(facesContext);
			}

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
	 * @return String.
	 */
	public String getCorrespondenceDetails() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String crspdSK = (String) map.get("crspdSK");
		if(logger.isDebugEnabled())
		{
		logger.debug("CRSPD SK " + crspdSK);
		}

		showDetailPortletForCrspd(crspdSK);

		return "success";
	}

	/**
	 * This method is used to get the details for Correspondance.
	 * 
	 * @param correspondenceSk
	 *            Takes correspondenceSk as param
	 */
	public void showDetailPortletForCrspd(String correspondenceSk) {
		FacesContext fc = FacesContext.getCurrentInstance();
		if(logger.isDebugEnabled())
		{
		logger.debug("Entity Id:" + correspondenceSk);
		}

		fc.getExternalContext().getRequestMap().put("correspondenceSk",
				correspondenceSk);

		PortletSession pSession = (PortletSession) fc.getExternalContext()
				.getSession(true);
		pSession.setAttribute(ContactManagementConstants.ClearInqEntyData,
				ContactManagementConstants.ClearInqEntyData,
				pSession.APPLICATION_SCOPE);

		//  resetCorrespondence();
		//  getSearchCorrespondenceDataBean().getSearchResults().clear();
		//  getSearchCorrespondenceDataBean().setShowResultsDiv(false);

	}

	/**
	 * This method will validate the search criteria.
	 * 
	 * @param correspondenceSearchCriteriaVO
	 *            the correspondenceSearchCriteriaVO to set
	 */
	protected void validateSearch(
			CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO) {
		/**
		 *  below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		if ((correspondenceSearchCriteriaVO.getEntityID() == null || correspondenceSearchCriteriaVO
				.getEntityID().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getInqEntityID() == null || correspondenceSearchCriteriaVO
						.getInqEntityID().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getInqEntityIDType() == null || correspondenceSearchCriteriaVO
						.getInqEntityIDType().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getStatus() == null || correspondenceSearchCriteriaVO
						.getStatus().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getEntityIDType() == null || correspondenceSearchCriteriaVO
						.getEntityIDType().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getCategory() == null
						|| correspondenceSearchCriteriaVO.getCategory()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getCategory().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getEntityType() == null
						|| correspondenceSearchCriteriaVO.getEntityType()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getEntityType().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getCrn() == null
						|| correspondenceSearchCriteriaVO.getCrn().equals("") || correspondenceSearchCriteriaVO
						.getCrn().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getStatusDate() == null
						|| correspondenceSearchCriteriaVO.getStatusDate()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getStatusDate().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getPriority() == null
						|| correspondenceSearchCriteriaVO.getPriority()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getPriority().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getInqEntityType() == null
						|| correspondenceSearchCriteriaVO.getInqEntityType()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getInqEntityType().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getPayerID() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getPayerID()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getPayerID().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedBY() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedBY()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedBY().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getSubjectCode() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getSubjectCode()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getSubjectCode().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getReportingUnit() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getReportingUnit()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getReportingUnit().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getContact() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getContact()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getContact().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getSource() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getSource()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getSource().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getResolutionCode() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getResolutionCode()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getResolutionCode().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getBusinessUnit() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getBusinessUnit()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getBusinessUnit().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedFromDate() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedFromDate()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedFromDate().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getLineOfBusinessGroup() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria()
								.getLineOfBusinessGroup().equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getLineOfBusinessGroup().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getDepartment() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getDepartment()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getDepartment().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedToDate() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedToDate().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getResponseExistIndicator() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria()
								.getResponseExistIndicator().equalsIgnoreCase(
										"") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getResponseExistIndicator()
						.trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getAssignedTo() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getAssignedTo()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getAssignedTo().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria().getProviderType()== null 
						|| correspondenceSearchCriteriaVO.getAdvSearchCriteria().getProviderType().equalsIgnoreCase("") 
						|| correspondenceSearchCriteriaVO.getAdvSearchCriteria().getProviderType().trim().length()== 0)

		) 
		//Code added for fixing the defect ESPRD00758521 on 20-02-2012
		{

			
			flag = false;
			setErrorMessage(
					ContactManagementConstants.ERR_EMPTY_SEARCH_INVALID,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					null);

		} else {
			Date crtdFromDate = null;
			Date statsDate = null;
			Date crtdToDate = null;
			String payerID = null;
			String contact = null;

			String entityID = (String) correspondenceSearchCriteriaVO
					.getEntityID();
			String inqID = (String) correspondenceSearchCriteriaVO
					.getInqEntityID();
			String entityType = (String) correspondenceSearchCriteriaVO
					.getEntityType();
			String entityIDType = (String) correspondenceSearchCriteriaVO
					.getEntityIDType();
			String inqentityType = (String) correspondenceSearchCriteriaVO
					.getInqEntityType();
			String inqentityIDType = (String) correspondenceSearchCriteriaVO
					.getInqEntityIDType();

			if (correspondenceSearchCriteriaVO.getStatusDate() != null) {
				String statusDate = (String) correspondenceSearchCriteriaVO
						.getStatusDate();
				statsDate = dateConverter(statusDate);
			}
			if (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null) {
				String createdFromDate = correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedFromDate();
				crtdFromDate = dateConverter(createdFromDate);

				String createdToDate = correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedToDate();
				crtdToDate = dateConverter(createdToDate);

				payerID = correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getPayerID();
				contact = correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getContact();
			}

			if (entityID != null
					&& !ContactManagementConstants.EMPTY_STRING
							.equalsIgnoreCase(entityID.trim())
					&& inqID != null
					&& !ContactManagementConstants.EMPTY_STRING
							.equalsIgnoreCase(inqID.trim())) {
				if(logger.isDebugEnabled())
				{
				logger
						.debug("both entityType and Inquriring About Id are entered---->"
								+ entityID + "and" + inqID);
				}
				flag = false;
				setErrorMessage(
						ContactManagementConstants.ERR_ENTITY_TYPE_OR_INQID,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
						null);
			} else {

				if (entityID != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(entityID.trim())
						|| entityType != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(entityType.trim())
						|| entityIDType != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(entityIDType.trim())) {
                    
                     // Code added to validate EntityIDType "TP" as per Defect ESPRD00740424
					if(entityID!=null){
		        	if(entityIDType !=null && (entityIDType.equalsIgnoreCase("SY")||
		        			entityIDType.equalsIgnoreCase("TJ")||
		        			entityIDType.equalsIgnoreCase("XX")||
		        			entityIDType.equalsIgnoreCase("TP") ||
		        			entityIDType.equalsIgnoreCase("MCR")||
		        			entityIDType.equalsIgnoreCase("MID")||
		        			entityIDType.equalsIgnoreCase("RID")) )
		        	{
		        	if(!EnterpriseCommonValidator.validateAlphaNumeric(entityID)){
		        	setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
							new Object[] {ContactManagementConstants.ID },
							MessageUtil.ENTMESSAGES_BUNDLE,
							ContactManagementConstants.ID);
		        	flag = false;
		        	}
		         }else if(!EnterpriseCommonValidator.validateNumeric(entityID))
				 {
		        	 setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
		 					new Object[] { ContactManagementConstants.ID },
		 					MessageUtil.ENTMESSAGES_BUNDLE,
		 					ContactManagementConstants.ID);
		        	flag = false;
		        	}
		        
				//}	
						//verifyEntityID(entityID);
					}

					if (entityType == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(entityType.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Entity Type" },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);

					}

					if (entityIDType == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(entityIDType.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Entity ID Type " },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);

					}

					if (entityID == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(entityID.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Entity ID" },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);

					}
				}

				if (inqID != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(inqID.trim())
						|| inqentityType != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(inqentityType.trim())
						|| inqentityIDType != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(inqentityIDType.trim())) {
					 // Code added to validate EntityIDType "TP" as per Defect ESPRD00740424
					if (inqID != null) {
						if(inqentityIDType !=null && (inqentityIDType.equalsIgnoreCase("SY")||
								inqentityIDType.equalsIgnoreCase("TJ")||
								inqentityIDType.equalsIgnoreCase("XX")||
								inqentityIDType.equalsIgnoreCase("TP") ||
								inqentityIDType.equalsIgnoreCase("MCR")||
								inqentityIDType.equalsIgnoreCase("MID")||
								inqentityIDType.equalsIgnoreCase("RID")))
			        	{
			        	if(!EnterpriseCommonValidator.validateAlphaNumeric(inqID)){
			        		setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
			    					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			        	flag = false;
			        	}
			         }else if(!EnterpriseCommonValidator.validateNumeric(inqID))
					 {
			        	 setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
			 					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			        	flag = false;
			        	}
					}

					if (inqentityType == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(inqentityType.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Inquiry Entity Type " },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);

					}

					if (inqentityIDType == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(inqentityIDType.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Inquiry Entity ID Type " },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);

					}

					if (inqID == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(inqID.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Inquiry Entity ID" },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);

					}
				}
			}

			if (correspondenceSearchCriteriaVO.getStatusDate() != null
					&& correspondenceSearchCriteriaVO.getStatusDate().trim()
							.length() > 0) {
				verifyStatusDate(correspondenceSearchCriteriaVO.getStatusDate());
			}

			if (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null) {
				if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedFromDate() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedFromDate()
								.trim().length() > 0 && !isDateErrOccurred) {
					verifyCreatedFromDate(correspondenceSearchCriteriaVO
							.getAdvSearchCriteria().getCreatedFromDate());
				}

				if (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate()
								.trim().length() > 0 && !isDateErrOccurred) {
					verifyCreatedToDate(correspondenceSearchCriteriaVO
							.getAdvSearchCriteria().getCreatedToDate());
				}

				if ((correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedFromDate() != null && correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedFromDate().trim()
						.length() > 0)
						&& (correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate() != null && correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate()
								.trim().length() > 0)) {

					boolean flag1 = EnterpriseCommonValidator
							.compareLesserDate(crtdFromDate, crtdToDate);
					if (!flag1) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_FROM_LESS_THAN_TO,

								new Object[] {
										ContactManagementConstants.FROM_DATE,
										ContactManagementConstants.TO_DATE },
								MessageUtil.ENTMESSAGES_BUNDLE,

								null);

					}

				}
			}

			if ((correspondenceSearchCriteriaVO.getStatusDate() != null && correspondenceSearchCriteriaVO
					.getStatusDate().trim().length() > 0)
					&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null
							&& correspondenceSearchCriteriaVO
									.getAdvSearchCriteria()
									.getCreatedFromDate() != null && correspondenceSearchCriteriaVO
							.getAdvSearchCriteria().getCreatedFromDate().trim()
							.length() > 0)) {

				boolean flag1 = EnterpriseCommonValidator.compareGreaterDate(
						crtdFromDate, statsDate);
				if (flag1) {
					flag = false;

					setErrorMessage(
							ContactManagementConstants.ERR_SW_FIRST_LESS_THAN_OR_EQUAL_TO_SECOND,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							null);

				}
			}

			if (flag
					&& (correspondenceSearchCriteriaVO.getStatusDate() != null && correspondenceSearchCriteriaVO
							.getStatusDate().trim().length() > 0)
					&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null
							&& correspondenceSearchCriteriaVO
									.getAdvSearchCriteria().getCreatedToDate() != null && correspondenceSearchCriteriaVO
							.getAdvSearchCriteria().getCreatedToDate().trim()
							.length() > 0)) {

				boolean flag1 = EnterpriseCommonValidator.compareLesserDate(
						crtdToDate, statsDate);
				if (flag1) {
					flag = false;
					setErrorMessage(
							ContactManagementConstants.ERR_CREATED_FROM_AND_CREATED_TO_AND_STATUS,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
							null);
				}

			}
			if (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null) {
				if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getPayerID() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getPayerID().trim()
								.length() > 0) {
					boolean flag2 = EnterpriseCommonValidator
							.validateAlphaNumeric(payerID);
					if (!flag2) {
						flag = false;
						/*
						 * setErrorMessage(
						 * ContactManagementConstants.ERR_PAYER_ID_INVALID, new
						 * Object[] {},
						 * ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
						 * null);
						 */
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_INTEGER,
								new Object[] { "ID" },
								MessageUtil.ENTMESSAGES_BUNDLE, null);

					}
				}

				if (correspondenceSearchCriteriaVO.getCrn() != null
						&& correspondenceSearchCriteriaVO.getCrn().trim()
								.length() > 0) {
					String crRecNo = correspondenceSearchCriteriaVO.getCrn();
					boolean flag2 = EnterpriseCommonValidator
							.validateNumeric(crRecNo);
					if (!flag2) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_DATA_INCORRECT,
								new Object[] {},
								MessageUtil.ENTMESSAGES_BUNDLE, null);

					}
				}
				if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getContact() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getContact().trim()
								.length() > 0) {
					boolean flag3 = EnterpriseCommonValidator
							.validateAlphaSpecialChars(contact);
					if (!flag3) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_DATA_INCORRECT,
								new Object[] {},
								MessageUtil.ENTMESSAGES_BUNDLE, null);

					}
				}
			}

		}
		/**
		 *  below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
		if(!flag)
		{
			searchCorrespondenceDataBean.setSearchCRValidationFlag(true);
		}
		else
		{
			searchCorrespondenceDataBean.setSearchCRValidationFlag(false);
		}

	}

	/**
	 * @return Returns the flag.
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            The flag to set.
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @param statusDate
	 *            This is used to verify whether or not statusDate is in
	 *            expected date format.
	 */

	private void verifyStatusDate(String statusDate)

	{

		
		if (!EnterpriseCommonValidator.validateDate(statusDate))

		{
			flag = false;

			isDateErrOccurred = true;

			setDateErrMsg();

			/*
			 * setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE,
			 * 
			 * new Object[] { ContactManagementConstants.STATUS_DATE },
			 * 
			 * MessageUtil.ENTMESSAGES_BUNDLE,
			 * 
			 * ContactManagementConstants.STATUS_DATE);
			 */

		}

	}

	/**
	 * @param createdFromDate
	 *            This is used to verify whether or not createdFromDate is in
	 *            expected date format.
	 */
	private void verifyCreatedFromDate(String createdFromDate)

	{

		

		if (!EnterpriseCommonValidator.validateDate(createdFromDate))

		{
			flag = false;

			isDateErrOccurred = true;

			setDateErrMsg();

			/*
			 * setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE,
			 * 
			 * new Object[] { "date" },
			 * 
			 * MessageUtil.ENTMESSAGES_BUNDLE,
			 * 
			 * ContactManagementConstants.CREATED_FROM_DATE);
			 */

			/*
			 * setErrorMessage( EnterpriseMessageConstants.ERR_DATA_INCORRECT,
			 * new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, "createdFrm");
			 */

		}

	}

	/**
	 * @param createdToDate
	 *            This is used to verify whether or not createdToDate is in
	 *            expected date format.
	 */
	private void verifyCreatedToDate(String createdToDate)

	{

		

		if (!EnterpriseCommonValidator.validateDate(createdToDate))

		{
			flag = false;

			isDateErrOccurred = true;

			setDateErrMsg();

			/*
			 * setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE,
			 * 
			 * new Object[] { "date" },
			 * 
			 * MessageUtil.ENTMESSAGES_BUNDLE,
			 * 
			 * ContactManagementConstants.CREATED_TO_DATE);
			 */

			/*
			 * setErrorMessage( EnterpriseMessageConstants.ERR_DATA_INCORRECT,
			 * new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, "createdTo");
			 */

		}

	}

	/**
	 * @param entityID
	 *            This is used to verify whether or not entityID is in valid
	 *            number format.
	 */
	private void verifyEntityID(String entityID) {

		

		if (!EnterpriseCommonValidator.validateNumeric(entityID)) {
			flag = false;
			/*
			 * for Fixing Defect ESPRD00612227
			 * setErrorMessage(EnterpriseMessageConstants.ERR_SW_INTEGER, new
			 * Object[] { ContactManagementConstants.ID },
			 * MessageUtil.ENTMESSAGES_BUNDLE, ContactManagementConstants.ID);
			 */
			setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
					new Object[] { ContactManagementConstants.ID },
					MessageUtil.ENTMESSAGES_BUNDLE,
					ContactManagementConstants.ID);
		}

	}

	/**
	 * @param inquiryID
	 *            This is used to verify whether or not inquiryID is in valid
	 *            number format.
	 */
	private void verifyInqID(String inquiryID) {

		

		if (!EnterpriseCommonValidator.validateNumeric(inquiryID)) {
			flag = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
		}

	}

	/**
	 * This method is used to convert String object to Date object
	 * 
	 * @param begdate
	 *            This contains the begin Date.
	 * @return Date
	 */
	public static Date dateConverter(String begdate) {

		Date beginDate = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat(
				ProgramConstants.DATE_FORMAT);
		if (begdate != null) {
			if (begdate.indexOf('/') != -1) {
				try {
					beginDate = new Date(sdf1.parse(begdate).getTime());

				} catch (ParseException e) {

					e.getMessage();
				}
			}
		}

		return beginDate;
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
		//FindBug Issue Resolved
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
			//"FindBugs" issue fixed
			if(referenceMap!=null){
			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.PROVIDER
							+ ProgramConstants.HASH_SEPARATOR
							+ ReferenceServiceDataConstants.PROV_TY_CD);
			}
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
		logger.info("SearchCorrespondenceControllerBean" + "#"
				+ "getProvTypeReferenceData" + "#" + (exitTime - entryTime));
		}

		return provTypeList;
	}

	/**
	 * This method is used to get the subjects list based on the categorySK.
	 * 
	 * @param categorySK
	 *            The categorySK to set.
	 */
	public void getsubjects(String categorySK) {
		ArrayList subjectList = new ArrayList();

		
		CorrespondenceCategory correspondenceCategory = null;
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		if (searchCorrespondenceDataBean.getListOfCategoryDOs() != null) {

			Iterator iterator = searchCorrespondenceDataBean
					.getListOfCategoryDOs().iterator();
			while (iterator.hasNext()) {
				correspondenceCategory = (CorrespondenceCategory) iterator
						.next();

				if (correspondenceCategory.getCategorySK().toString().equals(
						categorySK)) {

					Set catSubjXrefs = correspondenceCategory.getCatSubjXrefs();

					Iterator iterateSet = catSubjXrefs.iterator();

					while (iterateSet.hasNext()) {
						CategorySubjectXref catSubjAux = (CategorySubjectXref) iterateSet
								.next();

						SubjectAuxillary subjectAux = catSubjAux
								.getSubjectAuxillary();

						subjectList
								.add(new SelectItem(
										subjectAux.getSubjectCode(), subjectAux
												.getSubjectCode()));

					}
				}
			}

		}
		if(logger.isInfoEnabled())
		{
			logger.info("subjectList======" + subjectList.size());
		}
		if (subjectList.size() > 0) {
			searchCorrespondenceDataBean.setSubjectVVList(subjectList);
		}

	}

	/**
	 * This method is used to sort SearchResults.
	 * 
	 * @param event
	 *            the action event return String
	 */

	public String sortSearchResults(ActionEvent event) {
		
		
		// Below code commented and new code added  for CR-ESPRD00798895 for sorting functionality 
		/*
		 * SearchCorrespondenceDataBean searchCorrespondenceDataBean =
		 * getSearchCorrespondenceDataBean(); CorrespondenceSearchCriteriaVO
		 * correspondenceSearchCriteriaVO = getSearchCorrespondenceDataBean()
		 * .getCorrespondenceSearchCriteriaVO(); EnterpriseSearchResultsVO
		 * enterpriseSearchResultsVO = null; CMDelegate cmDelegate = new
		 * CMDelegate(); DateFormat dateFormat = new SimpleDateFormat(
		 * ContactManagementConstants.DATE_FORMAT); final String sortColumn =
		 * (String) event.getComponent().getAttributes()
		 * .get(ContactManagementConstants.COLUMN_NAME);
		 * 
		 * 
		 * final String sortOrder = (String)
		 * event.getComponent().getAttributes()
		 * .get(ContactManagementConstants.SORT_ORDER);
		 * 
		 * 
		 * //auto focus to first page by default.
		 * searchCorrespondenceDataBean.setStartIndexForSrchRslts(0);
		 * 
		 * // List searchList = new ArrayList();
		 * 
		 * searchCorrespondenceDataBean.setImageRender(event.getComponent()
		 * .getId());
		 * 
		 * //Sort By Status
		 * 
		 * if (ContactManagementConstants.ORDER_BY_STATUS.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDER_BY_STATUS);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * }
		 * 
		 * if (ContactManagementConstants.ORDER_BY_STATUS.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDER_BY_STATUS);
		 * correspondenceSearchCriteriaVO.setAscending(false);
		 * 
		 * } // Sort By CRN
		 * 
		 * if (ContactManagementConstants.ORDERBY_CRN.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_CRN);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * }
		 * 
		 * if (ContactManagementConstants.ORDERBY_CRN.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_CRN);
		 * correspondenceSearchCriteriaVO.setAscending(false);
		 * 
		 * }
		 * 
		 * // Sort By LOB
		 * 
		 * if (ContactManagementConstants.ORDERBY_LOB_CODE.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_LOB_CODE);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * }
		 * 
		 * if (ContactManagementConstants.ORDERBY_LOB_CODE.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_LOB_CODE);
		 * correspondenceSearchCriteriaVO.setAscending(false);
		 * 
		 * }
		 * 
		 * // Sort By EntityType
		 * 
		 * if (ContactManagementConstants.ORDERBY_ENTITY_TYPE.equals(sortColumn)
		 * && ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_CMNENTITY_TYPE);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * }
		 * 
		 * if (ContactManagementConstants.ORDERBY_ENTITY_TYPE.equals(sortColumn)
		 * && ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_CMNENTITY_TYPE);
		 * correspondenceSearchCriteriaVO.setAscending(false);
		 * 
		 * }
		 * 
		 * //Sort By Category
		 * 
		 * if (ContactManagementConstants.ORDERBY_CATEGORY.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_CATEGORY);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * }
		 * 
		 * if (ContactManagementConstants.ORDERBY_CATEGORY.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_CATEGORY);
		 * correspondenceSearchCriteriaVO.setAscending(false);
		 * 
		 * }
		 * 
		 * //Sort By EntityName
		 * 
		 * if
		 * (ContactManagementConstants.ORDERBY_ENTITY_NAME.equals(sortColumn)) {
		 * System.out.println("Entity name sorting");
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_ENTITY_NAME);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * 
		 * 
		 * ArrayList crSearchResults = (ArrayList)
		 * getSearchCorrespondenceDataBean() .getSearchResults();
		 * 
		 * if (crSearchResults != null) { if(logger.isDebugEnabled()) {
		 * logger.debug("crSearchResults size " + crSearchResults.size()); } }
		 * 
		 * Comparator comparator = new Comparator() { public int compare(Object
		 * obj1, Object obj2) { CorrespondenceSearchResultsVO data1 =
		 * (CorrespondenceSearchResultsVO) obj1; CorrespondenceSearchResultsVO
		 * data2 = (CorrespondenceSearchResultsVO) obj2; boolean ascending =
		 * false; if ("asc".equals(sortOrder)) { ascending = true; } else {
		 * ascending = false; } if (null == data1.getEntityName()) { data1
		 * .setEntityName(ContactManagementConstants.EMPTY_STRING); } if (null
		 * == data2.getEntityName()) { data2
		 * .setEntityName(ContactManagementConstants.EMPTY_STRING); } return
		 * ascending ? (data1.getEntityName()
		 * .compareToIgnoreCase(data2.getEntityName())) :
		 * (data2.getEntityName().compareToIgnoreCase(data1 .getEntityName()));
		 * } }; //FindBug Issue Resolved //Collections.sort(crSearchResults,
		 * comparator); if (crSearchResults != null) {
		 * Collections.sort(crSearchResults, comparator); } return
		 * ContactManagementConstants.RENDER_SUCCESS; }
		 * 
		 * //Sort By AssignedTo
		 * 
		 * if (ContactManagementConstants.ORDERBY_ASSIGNED_TO.equals(sortColumn)
		 * && ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_ASSIGNED_TO);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * }
		 * 
		 * if (ContactManagementConstants.ORDERBY_ASSIGNED_TO.equals(sortColumn)
		 * && ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_ASSIGNED_TO);
		 * correspondenceSearchCriteriaVO.setAscending(false);
		 * 
		 * }
		 * 
		 * // Sort By Created
		 * 
		 * if (ContactManagementConstants.ORDERBY_OPEN_DT.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_OPEN_DT);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * }
		 * 
		 * if (ContactManagementConstants.ORDERBY_OPEN_DT.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_OPEN_DT);
		 * correspondenceSearchCriteriaVO.setAscending(false);
		 * 
		 * }
		 * 
		 * //Sort By Business Unit
		 * 
		 * if
		 * (ContactManagementConstants.ORDERBY_BUSINESSUNIT.equals(sortColumn)
		 * && ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_BUSINESSUNIT);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * }
		 * 
		 * if
		 * (ContactManagementConstants.ORDERBY_BUSINESSUNIT.equals(sortColumn)
		 * && ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_BUSINESSUNIT);
		 * correspondenceSearchCriteriaVO.setAscending(false);
		 * 
		 * }
		 * 
		 * // Sort By Subject Code for defect ESPRD00608131
		 * 
		 * if (ContactManagementConstants.ORDERBY_SUBJ_CODE.equals(sortColumn)
		 * && ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_SUBJ_CODE);
		 * correspondenceSearchCriteriaVO.setAscending(true);
		 * 
		 * }
		 * 
		 * if (ContactManagementConstants.ORDERBY_SUBJ_CODE.equals(sortColumn)
		 * && ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * 
		 * correspondenceSearchCriteriaVO
		 * .setSortColumn(ContactManagementConstants.ORDERBY_SUBJ_CODE);
		 * correspondenceSearchCriteriaVO.setAscending(false);
		 * 
		 * }
		 * 
		 * try {
		 * 
		 * enterpriseSearchResultsVO = cmDelegate
		 * .getCorrespondence(correspondenceSearchCriteriaVO); //FindBug Issue
		 * Resolved //List searchResults = new ArrayList(); List searchResults =
		 * null; searchResults = enterpriseSearchResultsVO.getSearchResults();
		 * if (searchResults != null && searchResults.size() > 0) { Iterator
		 * searchResultsItr = searchResults.iterator(); Map entityTypeMap =
		 * searchCorrespondenceDataBean .getEntityTypeMap(); Map statusMap =
		 * searchCorrespondenceDataBean.getStatusMap(); Map subjectMap =
		 * searchCorrespondenceDataBean.getSubjectMap(); while
		 * (searchResultsItr.hasNext()) { CorrespondenceSearchResultsVO
		 * searchResultVO = (CorrespondenceSearchResultsVO) searchResultsItr
		 * .next(); if (searchResultVO != null) { if
		 * (entityTypeMap.containsKey(searchResultVO .getEntityType())) {
		 * searchResultVO.setEntityType(entityTypeMap.get(
		 * searchResultVO.getEntityType()).toString()); } if
		 * (statusMap.containsKey(searchResultVO.getStatus())) {
		 * searchResultVO.setStatus(statusMap.get(
		 * searchResultVO.getStatus()).toString()); } if
		 * (subjectMap.containsKey(searchResultVO .getSubjectCode())) {
		 * searchResultVO .setSubjectCode(subjectMap.get(
		 * searchResultVO.getSubjectCode()) .toString()); } if
		 * (searchResultVO.getCreatedOn() != null) {
		 * searchResultVO.setCreatedOnstr(dateFormat
		 * .format(searchResultVO.getCreatedOn())); }
		 * 
		 * } }
		 * 
		 * //for fixing defect ESPRD00576305
		 * 
		 * while(searchResultsItr.hasNext()) { CorrespondenceSearchResultsVO
		 * searchResultVO =
		 * (CorrespondenceSearchResultsVO)searchResultsItr.next();
		 * if(searchResultVO != null){ if (searchResultVO.getCreatedOn() !=
		 * null) {
		 * searchResultVO.setCreatedOnstr(dateFormat.format(searchResultVO
		 * .getCreatedOn())); } } }
		 * 
		 * } searchCorrespondenceDataBean.setSearchResults(searchResults);
		 * 
		 * searchCorrespondenceDataBean.setShowResultsDiv(true);
		 * 
		 * } catch (CorrespondenceRecordFetchBusinessException exe) {
		 * setErrorMessage(
		 * ContactManagementConstants.ERR_SW_COULD_NOT_BE_FOUND, new Object[] {
		 * "Record(s)" }, MessageUtil.ENTMESSAGES_BUNDLE, null);
		 * searchCorrespondenceDataBean.setShowResultsDiv(false);
		 * 
		 * 
		 * }
		 */

		// return "success";
		
		
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = getSearchCorrespondenceDataBean()
				.getCorrespondenceSearchCriteriaVO();
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		CMDelegate cmDelegate = new CMDelegate();
		logger.debug("INSIDE  SORTING METHOD ");
		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		logger.debug("Sort column--->" + sortColumn);

		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);
		logger.debug("Sort Order---->" + sortOrder);

		searchCorrespondenceDataBean.setSortFlag(true);

		// auto focus to first page by default.
		searchCorrespondenceDataBean.setStartIndexForSrchRslts(0);

		// List searchList = new ArrayList();

		searchCorrespondenceDataBean.setImageRender(event.getComponent()
				.getId());

		if (sortOrder.equals("asc")) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setAscending(true);
		} else {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setAscending(false);
		}
		searchCorrespondenceDataBean.setStartRecord(0);

		// Sort By Status

		if (ContactManagementConstants.ORDER_BY_STATUS.equals(sortColumn)) {
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_STATUS);
		}

		// Sort By CRN

		if (ContactManagementConstants.ORDERBY_CRN.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_CRN);
		}

		// Sort By LOB

		if (ContactManagementConstants.ORDERBY_LOB_CODE.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_LOB_CODE);
		}

		// Sort By EntityType

		if (ContactManagementConstants.ORDERBY_ENTITY_TYPE.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(
							ContactManagementConstants.ORDERBY_CMNENTITY_TYPE);
		}

		// Sort By Category

		if (ContactManagementConstants.ORDERBY_CATEGORY.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_CATEGORY);
		}

		// Sort By EntityName

		if (ContactManagementConstants.ORDERBY_ENTITY_NAME.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(
							ContactManagementConstants.ORDERBY_ENTITY_NAME);
			// correspondenceSearchCriteriaVO.setAscending(true);

		}

		// Sort By AssignedTo

		if (ContactManagementConstants.ORDERBY_ASSIGNED_TO.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(
							ContactManagementConstants.ORDERBY_ASSIGNED_TO);
		}

		// Sort By Created

		if (ContactManagementConstants.ORDERBY_OPEN_DT.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_OPEN_DT);
		}

		// Sort By Business Unit

		if (ContactManagementConstants.ORDERBY_BUSINESSUNIT.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(
							ContactManagementConstants.ORDERBY_BUSINESSUNIT);

		}
		// Sort By Subject Code 

		if (ContactManagementConstants.ORDERBY_SUBJ_CODE.equals(sortColumn)) {
			searchCorrespondenceDataBean
					.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_SUBJ_CODE);
		}

		getCorrespondence();

		return "success";
}

	/**
	 * This method is used to get the memberCorrespondence.
	 * 
	 * @return String : Returns the memberCorrespondence.
	 */
	public String getMemberCorrespondence() {
		FacesContext fc = FacesContext.getCurrentInstance();

		if (fc.getExternalContext().getRequestMap() != null) {

			String correspondence = (String) fc.getExternalContext()
					.getRequestMap().get("MemberCorrespondence");

			if (correspondence != null) {
				int separatorIndex = correspondence
						.indexOf(ContactManagementConstants.COLON_SEPARATOR);

				String entityId = correspondence.substring(0, separatorIndex);
				String entityType = correspondence.substring(
						separatorIndex + 1, correspondence.length());
				if(logger.isDebugEnabled())
				{
				logger.debug("entityId " + entityId);
				logger.debug("entityType " + entityType);
				}

				getCorrEntIDTypeValues(entityType);
				CorrespondenceSearchCriteriaVO crspdSearchCriteriaVO = getSearchCorrespondenceDataBean()
						.getCorrespondenceSearchCriteriaVO();
				crspdSearchCriteriaVO.setEntityID(entityId);
				crspdSearchCriteriaVO.setEntityType(entityType);
				crspdSearchCriteriaVO.setEntityIDType("MID");
				crspdSearchCriteriaVO
						.setAdvSearchCriteria(new CRAdvanceSearchCriteriaVO());

				//getCorrespondence();
			}

		}
		if (fc.getExternalContext().getRequestMap().get("CrspdGenAppealSK") != null) {
			
			String appealEntity = (String) fc.getExternalContext()
					.getRequestMap().get("CrspdGenAppealSK");
			if (appealEntity != null) {
				int separatorIndex = appealEntity
						.indexOf(ContactManagementConstants.COLON_SEPARATOR);
				String entityId = appealEntity.substring(0, separatorIndex);
				String entityType = appealEntity.substring(separatorIndex + 1,
						appealEntity.length());
				if(logger.isDebugEnabled())
				{
					logger.debug("entityId from AppealEntity:" + entityId);
					logger.debug("entityType from AppealEntity:" + entityType);
				}
				CorrespondenceSearchCriteriaVO crspdSearchCriteriaVO = getSearchCorrespondenceDataBean()
						.getCorrespondenceSearchCriteriaVO();
				crspdSearchCriteriaVO.setEntityID(entityId);
				crspdSearchCriteriaVO.setEntityType(entityType);
				crspdSearchCriteriaVO
						.setAdvSearchCriteria(new CRAdvanceSearchCriteriaVO());

				getCorrespondence();
			}
		}

		return memberCorrespondence;
	}

	/**
	 * This method is used to set the memberCorrespondence.
	 * 
	 * @param memberCorrespondence :
	 *            The memberCorrespondence to set.
	 */
	public void setMemberCorrespondence(String memberCorrespondence) {
		this.memberCorrespondence = memberCorrespondence;
	}

	/**
	 * This method is used to getEntityIDFromAppeal.
	 * 
	 * @return: String
	 */
	public String getEntityIDFromAppeal() {

		
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		//FindBug Issue Resolved
		//FacesContext fc = FacesContext.getCurrentInstance();
		
		String returnMsg = "";
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();

		if (map == null) {
			
			returnMsg = MaintainContactManagementUIConstants.FAILURE;
		} else {
			
			String entityID = map.get("entityId").toString();
			String entityType = map.get("entityType").toString();
			if(logger.isDebugEnabled())
			{
			logger.debug("EntityID  in getEntityIDFromAppeal  $$  " + entityID);
			}
			if (entityID != null) {
				if(logger.isDebugEnabled())
				{
				logger
						.debug("After getting Entity ID in getCaseIDFromAppeal, caseSK is --->"
								+ entityID + "     " + entityType);
				}

				CorrespondenceSearchCriteriaVO criteriaVO = new CorrespondenceSearchCriteriaVO();
				criteriaVO.setEntityID(entityID);
				criteriaVO.setEntityType(entityType);
				searchCorrespondenceDataBean
						.setCorrespondenceSearchCriteriaVO(criteriaVO);
				getCorrespondence();
			}
		}

		return "success";

	}

	/**
	 * This method is used to check if the input field is null or is equal to an
	 * empty string.
	 * 
	 * @param inputField
	 *            String inputField.
	 * @return boolean : true if input field is null or equal to an empty string
	 *         else false.
	 */
	protected boolean isNullOrEmptyField(String inputField) {
		return (inputField == null || ContactManagementConstants.EMPTY_STRING
				.equalsIgnoreCase(inputField.trim()));
	}

	/**
	 * This method is used to get the depts for Reassign Correspondence
	 * 
	 * @param listSK
	 *            List listSK.
	 */
	private List getDeptsForReassign(Long categorySK, Long lobHierarchySk) {
		List lobHierarchyList = new ArrayList();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		if(logger.isDebugEnabled())
		{
		logger.debug(" in getDeptsForReassign : categorySK : " + categorySK
				+ ": lobHierarchySk :" + lobHierarchySk);
		}

		List reassignDeptList = new ArrayList();
		Set depts = new HashSet();
		Set users = new HashSet();
		try {

			/*
			 * lobHierarchyList = contactMaintenanceDelegate
			 * .getAllDepartmentUsers(categorySK, lobHierarchySk);
			 */
			//Updated by Infinite - MMIS I2 Merge
			lobHierarchyList = contactMaintenanceDelegate
					.getAllDepartmentUsers();
		} catch (LOBHierarchyFetchBusinessException e) {

			e.printStackTrace();
		}
		if (lobHierarchyList != null) {
			if(logger.isDebugEnabled())
			{
			logger.debug("after ContactmaintenanceDelegate call :"
					+ lobHierarchyList.size());
			}

			Iterator it2 = lobHierarchyList.iterator();

			while (it2.hasNext()) {
				DepartmentUser element = (DepartmentUser) it2.next();

				depts.add(element.getDepartment());
				users.add(element.getEnterpriseUser());
			}
			for (Iterator iter = users.iterator(); iter.hasNext();) {
				EnterpriseUser user = (EnterpriseUser) iter.next();
				reassignDeptList.add(new SelectItem(user.getUserWorkUnitSK()
						.toString().trim(), user.getLastName() + ", "
						+ user.getFirstName() + " - "
						+ user.getUserWorkUnitSK().toString()));

			}
			for (Iterator iter = depts.iterator(); iter.hasNext();) {
				Department dept = (Department) iter.next();
				reassignDeptList.add(new SelectItem(dept.getWorkUnitSK()
						.toString().trim(), dept.getName()));

			}
			if(logger.isDebugEnabled())
			{
			logger.debug("******* reassignDeptList size : "
					+ reassignDeptList.size());
			}
		}

		return reassignDeptList;
	}

	/**
	 * This method is used to get the depts for Reassign Correspondence
	 * 
	 * @param listSK
	 *            List listSK.
	 */

	private void getDeptsForReassign(Long categorySK) {

		//FindBug Issue Resolved
		//List lobHierarchyList = new ArrayList();
		List lobHierarchyList = null;
		CMDelegate cmDelegate = new CMDelegate();
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();

		if (searchCorrespondenceDataBean.getReassignDepartmentsList() != null
				&& searchCorrespondenceDataBean.getReassignDepartmentsList()
						.size() > 0) {

			searchCorrespondenceDataBean.getReassignDepartmentsList().clear();
		}

		try {
			lobHierarchyList = cmDelegate.getAllDepartments(categorySK);
			if (lobHierarchyList != null) {
				Iterator it2 = lobHierarchyList.iterator();
				while (it2.hasNext()) {
					Department element = (Department) it2.next();
					getSearchCorrespondenceDataBean()
							.getReassignDepartmentsList().add(
									new SelectItem(element.getWorkUnitSK()
											.toString(), element.getName()));
					Set setOfUsers = element.getDepartmentUser();
					for (Iterator iterator = setOfUsers.iterator(); iterator
							.hasNext();) {
						DepartmentUser deptUser = (DepartmentUser) iterator
								.next();

						String userDesc = getUserFullName(deptUser
								.getEnterpriseUser());

						getSearchCorrespondenceDataBean()
								.getReassignDepartmentsList().add(
										new SelectItem(deptUser
												.getUserEnterpriseSK()
												.toString(), userDesc));
					}
				}
			}
			//FindBUg Issue Resolved
			//int temp = getSearchCorrespondenceDataBean()
			//      .getReassignDepartmentsList().size();

		} catch (CorrespondenceRecordFetchBusinessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to change depts in dropdown on Change of departments.
	 * 
	 * @param event
	 *            the event
	 * @param inputField
	 *            List listSK.
	 */
	public String onChangeReassignAll() {
		getCorrespondence();
		return ContactManagementConstants.EMPTY_STRING;
	}

	/**
	 * This method is used to retreive all users and Departments for Reassign
	 * all
	 */
	protected void getAllDeptUsersForReassign() 
	{
		ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		searchCorrespondenceDataBean
				.setReassignAllDeptUsersList(contactManagementHelper
						.getAllActiveUsersAndDepts());

		//sortListSequence(reassignDeptList);
	}

	/**
	 * @param user
	 *            The user to set.
	 * @return String
	 */
	private String getFullUserName(EnterpriseUser user) {
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
	 * @param user
	 *            The user to set.
	 * @return String
	 */
	private String getUserFullName(EnterpriseUser user) {
		String userDesc = ContactManagementConstants.EMPTY_STRING;

		if (!isNullOrEmptyField(user.getFirstName())) {
			userDesc += user.getFirstName()
					+ ContactManagementConstants.SPACE_STRING;
		}
		if (!isNullOrEmptyField(user.getLastName())) {
			userDesc += user.getLastName()
					+ ContactManagementConstants.SPACE_STRING;
		}

		return userDesc;
	}

	/**
	 * This method is used to get the User ID.
	 * 
	 * @return String : User ID
	 */
	public String getUserID() {
		

		String userId = null;

		/*
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
		userId = getLoggedInUserID(portletRequest);
		

		return userId;
	}

	/**
	 * This method is used to get the BusinessUnitSK for User.
	 * 
	 * @return String : User ID
	 */
	public String getBusinessUnitSKforUser(Long userSK) {

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		String businessCode = null;
		try {
			List deptsList = contactMaintenanceDelegate
					.getDepartmentUsers(userSK);

			if (deptsList != null) {

				for (Iterator iter = deptsList.iterator(); iter.hasNext();) {
					DepartmentUser deptUser = (DepartmentUser) iter.next();
					String lobHierarchySK = deptUser.getDepartment()
							.getLineOfBusinessHierarchy()
							.getLineOfBusinessHierarchySK().toString();

					LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
							.getDeptBusinessUnit(new Long(lobHierarchySK));
					if (businessUnit != null) {
						businessCode = businessUnit
								.getLineOfBusinessHierarchySK().toString();

					}
				}

			}
		} catch (LOBHierarchyFetchBusinessException lobExp) {
			if(logger.isErrorEnabled())
			{
			logger.error(lobExp.getMessage(), lobExp);
			}
		}

		return businessCode;
	}

	//  Note: Changed return type for Navigation Rule

	public String getAdvOptionsPlus() {

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		searchCorrespondenceDataBean.getAdvOptionsPlus();

		return "success";
	}

	//  Note: Changed return type for Navigation Rule
	public String getAdvOptionsMinus() {

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		searchCorrespondenceDataBean.getAdvOptionsMinus();

		return "success";
	}

	/**
	 * 
	 * @param correspondenceSearchCriteriaVO
	 * @return
	 */
	public boolean validateCorresFor(
			CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO) {

		boolean isValid = true;
		//Commented to implement Defect ESPRD00251263
		/*
		 * if
		 * (isNullOrEmptyField(correspondenceSearchCriteriaVO.getEntityType()) &&
		 * isNullOrEmptyField(correspondenceSearchCriteriaVO .getEntityID())) {
		 * isValid = false; setErrorMessage(
		 * ContactManagementConstants.ERR_ENTTYPE_OR_ENTID_REQ, new Object[] {},
		 * ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE, null); }
		 * 
		 * if (isValid) {
		 */
		
		if (null != correspondenceSearchCriteriaVO.getEntityType()
				&& null != correspondenceSearchCriteriaVO.getEntityIDType()
				&& "TD".equalsIgnoreCase(correspondenceSearchCriteriaVO
						.getEntityType())
				&& "TP".equalsIgnoreCase(correspondenceSearchCriteriaVO
						.getEntityIDType())) {

			if (!ContactManagementHelper
					.validateAlphaNumeric(correspondenceSearchCriteriaVO
							.getEntityID())) {
				isValid = false;
				String id = "ID";

				setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
						new Object[] { id }, MessageUtil.ENTMESSAGES_BUNDLE,
						null);

			}

		} else if (!ContactManagementHelper
				.validateNumeric(correspondenceSearchCriteriaVO.getEntityID())) {
			isValid = false;
			String id = "ID";
			/*
			 * For Fixing Defect ESPRD00612227 setErrorMessage(
			 * EnterpriseMessageConstants.ERR_SW_INTEGER, new Object[] {id},
			 * MessageUtil.ENTMESSAGES_BUNDLE, null);
			 */
			setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
					new Object[] { id }, MessageUtil.ENTMESSAGES_BUNDLE, null);
			/*
			 * MaintainContactManagementUIConstants.CASE_AGENCY_PHNO_INVALID,
			 * MaintainContactManagementUIConstants.CASE_AGENCY_PHNO, null,
			 * null);
			 */
		}

		//}
		return isValid;
	}

	/**
	 * 
	 * @param correspondenceSearchCriteriaVO
	 * @return
	 */
	public boolean validateInqAbt(
			CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO) {

		boolean isValid = true;

		/*
		 * if (isNullOrEmptyField(correspondenceSearchCriteriaVO
		 * .getInqEntityType()) &&
		 * isNullOrEmptyField(correspondenceSearchCriteriaVO .getInqEntityID())) {
		 * isValid = false; setErrorMessage(
		 * ContactManagementConstants.ERR_ENTTYPE_OR_ENTID_REQ, new Object[] {},
		 * ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE, null); }
		 * 
		 * if (isValid) {
		 */
		 // Code added to validate EntityIDType "TP" as per Defect ESPRD00740424
		if (null != correspondenceSearchCriteriaVO.getInqEntityType()
				&& null != correspondenceSearchCriteriaVO.getInqEntityIDType()
				&& "TD".equalsIgnoreCase(correspondenceSearchCriteriaVO
						.getInqEntityType())
				&& "TP".equalsIgnoreCase(correspondenceSearchCriteriaVO
						.getInqEntityIDType())) {

			if (!ContactManagementHelper
					.validateAlphaNumeric(correspondenceSearchCriteriaVO
							.getInqEntityID())) {
				isValid = false;
				String id = "ID";

				setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
						new Object[] { id }, MessageUtil.ENTMESSAGES_BUNDLE,
						null);

			}

		}else if (!ContactManagementHelper
				.validateNumeric(correspondenceSearchCriteriaVO
						.getInqEntityID())) {
			isValid = false;
			setErrorMessage(ContactManagementConstants.ERR_INVALID_FORMAT,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					null);
			/*
			 * MaintainContactManagementUIConstants.CASE_AGENCY_PHNO_INVALID,
			 * MaintainContactManagementUIConstants.CASE_AGENCY_PHNO, null,
			 * null);
			 */
		}

		//}
		return isValid;
	}

	/**
	 * 
	 * @return
	 */
	public String doCorrSearchEntityAction() {

		

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = searchCorrespondenceDataBean
				.getCorrespondenceSearchCriteriaVO();

		boolean isValid = validateCorresFor(correspondenceSearchCriteriaVO);

		if (isValid) {
			
			CorrespondenceSearchCriteriaVO newCorrespondenceSearchCriteriaVO = new CorrespondenceSearchCriteriaVO();

			Object obj = FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			ActionRequest request = null;
			RenderRequest renderRequest = null;
			String reassignCorrespondence = null;
			if (obj instanceof ActionRequest) {
				request = (ActionRequest) obj;
				reassignCorrespondence = request
						.getParameter("reassignCorrespondence");
			} else {
				renderRequest = (RenderRequest) obj;
				reassignCorrespondence = renderRequest
						.getParameter("reassignCorrespondence");
			}
			if (reassignCorrespondence != null
					&& (reassignCorrespondence
							.equalsIgnoreCase("reassignCorrespondence"))) {
				newCorrespondenceSearchCriteriaVO.setReassginCr(true);
			}
			newCorrespondenceSearchCriteriaVO
					.setEntityType(correspondenceSearchCriteriaVO
							.getEntityType());
			newCorrespondenceSearchCriteriaVO
					.setEntityIDType(correspondenceSearchCriteriaVO
							.getEntityIDType());
			newCorrespondenceSearchCriteriaVO
					.setEntityID(correspondenceSearchCriteriaVO.getEntityID());

			FacesContext facesContext = FacesContext.getCurrentInstance();

			Map requestScope = (Map) facesContext.getApplication()
					.createValueBinding("#{requestScope}").getValue(
							facesContext);

			requestScope.put("CrsSearchCrtVO",
					newCorrespondenceSearchCriteriaVO);

		}

		return "success";
	}

	/**
	 * 
	 * @return
	 */
	public String doInqSearchEntityAction() {

		

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = searchCorrespondenceDataBean
				.getCorrespondenceSearchCriteriaVO();

		boolean isValid = validateInqAbt(correspondenceSearchCriteriaVO);

		if (isValid) {
			

			CorrespondenceSearchCriteriaVO newCorrespondenceSearchCriteriaVO = new CorrespondenceSearchCriteriaVO();

			newCorrespondenceSearchCriteriaVO
					.setInqEntityType(correspondenceSearchCriteriaVO
							.getInqEntityType());
			newCorrespondenceSearchCriteriaVO
					.setInqEntityIDType(correspondenceSearchCriteriaVO
							.getInqEntityIDType());
			newCorrespondenceSearchCriteriaVO
					.setInqEntityID(correspondenceSearchCriteriaVO
							.getInqEntityID());
			Object obj = FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			ActionRequest request = null;
			RenderRequest renderRequest = null;
			String reassignCorrespondence = null;
			if (obj instanceof ActionRequest) {
				request = (ActionRequest) obj;
				reassignCorrespondence = request
						.getParameter("reassignCorrespondence");
			} else {
				renderRequest = (RenderRequest) obj;
				reassignCorrespondence = renderRequest
						.getParameter("reassignCorrespondence");
			}
			if (reassignCorrespondence != null
					&& (reassignCorrespondence
							.equalsIgnoreCase("reassignCorrespondence"))) {
				newCorrespondenceSearchCriteriaVO.setReassginCr(true);
			}

			FacesContext facesContext = FacesContext.getCurrentInstance();

			Map requestScope = (Map) facesContext.getApplication()
					.createValueBinding("#{requestScope}").getValue(
							facesContext);

			requestScope.put("CrsSearchCrtVO",
					newCorrespondenceSearchCriteriaVO);

		}

		return "success";

	}

	/**
	 * Loading data from Entity Search
	 *  
	 */
	public void getDataFromSrchEnt() {

		

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();

		PortletSession portletSession = (PortletSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);

		if (portletSession != null
				&& portletSession.getAttribute("DataMap") != null) {
			Map dataMap = (Map) portletSession.getAttribute("DataMap");

			portletSession.removeAttribute("DataMap");
			if(logger.isDebugEnabled())
			{

			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt entityType "
							+ dataMap.get("EntityType"));
			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt entityIDType "
							+ dataMap.get("EntityIDType"));
			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt entityID "
							+ dataMap.get("EntityID"));
			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt commonEntitySK "
							+ dataMap.get("CommonEntitySK"));
			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt Identification "
							+ dataMap.get("Identification"));
			}

			

			String identification = dataMap.get("Identification").toString();

			if (identification != null && identification.equals("C")) {
				String entityType = dataMap.get("EntityType").toString();
				if (entityType != null && !entityType.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO().setEntityType(
									entityType);
					getCorrEntIDTypeValues(entityType);
				}

				String entityIDType = dataMap.get("EntityIDType").toString();
				if (entityIDType != null && !entityIDType.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setEntityIDType(entityIDType);
				}

				String entityID = dataMap.get("EntityID").toString();
				if (entityID != null && !entityID.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO().setEntityID(
									entityID);
				}

				Object commonEntitySk = dataMap.get("CommonEntitySK");
				if (commonEntitySk != null) {
					String corrEntitySK = commonEntitySk.toString();
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setCorrEntitySK(corrEntitySK);
				}
			}

			else if (identification != null && identification.equals("I")) {
				String entityType = dataMap.get("EntityType").toString();
				if (entityType != null && !entityType.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setInqEntityType(entityType);
					getInqEntIDTypeValues(entityType);
				}

				String entityIDType = dataMap.get("EntityIDType").toString();
				if (entityIDType != null && !entityIDType.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setInqEntityIDType(entityIDType);
				}

				String entityID = dataMap.get("EntityID").toString();
				if (entityID != null && !entityID.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setInqEntityID(entityID);
				}

				Object commonEntitySk = dataMap.get("CommonEntitySK");
				if (commonEntitySk != null) {
					String inqEntitySK = commonEntitySk.toString();
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setInqEntitySK(inqEntitySK);
				}
			}
		}

	}

	/**
	 * Method is called when Entity Type changes
	 * 
	 * @param event
	 */
	//Note: Changed return type for Navigation Rule
	public String onInqEntityTypeChange(ValueChangeEvent event) {

		

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		String newValue = event.getNewValue().toString();
		

		if (!newValue.equals("")) {
			getInqEntIDTypeValues(newValue);
		}
		if ("P".equals(newValue)) {
			
			searchCorrespondenceDataBean
					.setProvTypeVVList(getProvTypeReferenceData());

		}

		return "success";
	}

	/**
	 * Populating Entity ID Type values in Correspondence For section
	 * 
	 * @param entityType
	 */
	public void getCorrEntIDTypeValues(String entityType) {
		

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();

		CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();

		/** ENTITY TYPE -- MEMBER */

		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_MEM, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_MEM);

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type Member
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		}

		/** ENTITY TYPE -- PROVIDER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_PROV, entityType)) {
			

			//for CR ESPRD00703521
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							"G_REP",
							"PROV_PSTN_CD");

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type Provider
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.PROVIDER, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.PROVIDER,
										sysListNumber));
			} else {
				
			}
		}

		/** ENTITY TYPE -- UNENROLLED PROVIDER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_UNPROV, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_UNPROV);

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type Unenrolled
			 * Provider
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		}

		/** ENTITY TYPE -- TPL CARRIER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_TPL, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_TPL);

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type TPL Carrier
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		}

		/** ENTITY TYPE -- DISTRICT OFFICE */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_DO, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_DO);

		

			/**
			 * Populates the Entity ID Type dropdown for Entity Type District
			 * Office
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		} else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_CT, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CT);

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type County
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		}
		// Trading Partner
		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_TD, entityType)) {
			
			/*
			 * Long sysListNumber =
			 * ContactManagementHelper.getSystemlistForEntyIdType(
			 * ContactManagementConstants.ENTITYIDTYPE,
			 * ContactManagementConstants.ENTITY_TYPE_CT);
			 */
			Long sysListNumber = new Long("0181");
			
			

			List entIDTypeReferenceData = cmEntityDOConvertor
					.getEntIDTypeReferenceData(FunctionalAreaConstants.GENERAL,
							sysListNumber);

			/**
			 * Populates the Entity ID Type dropdown for Entity Type County
			 */
			if (entIDTypeReferenceData != null) {
				

				searchCorrespondenceDataBean
						.setEntityIDTypeList(entIDTypeReferenceData);
			} else {
				
			}
			
		}

		else {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type Other
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		}
	}

	/**
	 * Populating Entity ID Type values in Inquiring About ID section
	 * 
	 * @param entityType
	 */
	public void getInqEntIDTypeValues(String entityType) {
		

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();

		CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();

		/** ENTITY TYPE -- MEMBER */

		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_MEM, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_MEM);

			

			/** Populates the Entity ID Type dropdown for Entity Type Member */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setInqEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		}

		/** ENTITY TYPE -- PROVIDER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_PROV, entityType)) {
		

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							"G_REP",
							"PROV_PSTN_CD");

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type Provider
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.PROVIDER, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setInqEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.PROVIDER,
										sysListNumber));
			} else {
				
			}
		}

		/** ENTITY TYPE -- UNENROLLED PROVIDER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_UNPROV, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_UNPROV);

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type Unenrolled
			 * Provider
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setInqEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
			
			}
		}

		/** ENTITY TYPE -- TPL CARRIER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_TPL, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_TPL);

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type TPL Carrier
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setInqEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		}

		/** ENTITY TYPE -- DISTRICT OFFICE */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_DO, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_DO);

			

			/**
			 * Populates the Entity ID Type dropdown for Entity Type District
			 * Office
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setInqEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		} else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_CT, entityType)) {
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CT);

			

			/** Populates the Entity ID Type dropdown for Entity Type County */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				

				searchCorrespondenceDataBean
						.setInqEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		}
		// Trading Partner
		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_TD, entityType)) {
				
			/*
			 * Long sysListNumber =
			 * ContactManagementHelper.getSystemlistForEntyIdType(
			 * ContactManagementConstants.ENTITYIDTYPE,
			 * ContactManagementConstants.ENTITY_TYPE_CT);
			 */
			Long sysListNumber = new Long("0181");
			

			List entIDTypeReferenceData = cmEntityDOConvertor
					.getEntIDTypeReferenceData(FunctionalAreaConstants.GENERAL,
							sysListNumber);

			/**
			 * Populates the Entity ID Type dropdown for Entity Type County
			 */
			if (entIDTypeReferenceData != null) {
				

				searchCorrespondenceDataBean
						.setInqEntityIDTypeList(entIDTypeReferenceData);
			} else {
				
			}
			
		} else {
			
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);

			

			/** Populates the Entity ID Type dropdown for Entity Type Other */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				searchCorrespondenceDataBean
						.setInqEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
			} else {
				
			}
		}
	}

	/**
	 * Loading data from Entity Search to Search Correspondence
	 * 
	 * @param dataMap
	 */
	public void fetchDataintoCorr(Map dataMap) {
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();

		if (dataMap != null) {
			if(logger.isDebugEnabled())
			{
			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt entityType "
							+ dataMap.get("EntityType"));
			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt entityIDType "
							+ dataMap.get("EntityIDType"));
			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt entityID "
							+ dataMap.get("EntityID"));
			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt commonEntitySK "
							+ dataMap.get("CommonEntitySK"));
			logger
					.debug("SearchCorrespondenceControllerBean :: getDataFromSrchEnt Identification "
							+ dataMap.get("Identification"));
			}

			String identification = dataMap.get("Identification").toString();

			if (identification != null && identification.equals("C")) {
				String entityType = dataMap.get("EntityType").toString();
				if (entityType != null && !entityType.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO().setEntityType(
									entityType);
					getCorrEntIDTypeValues(entityType);
				}

				String entityIDType = dataMap.get("EntityIDType").toString();
				if (entityIDType != null && !entityIDType.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setEntityIDType(entityIDType);
				}

				String entityID = dataMap.get("EntityID").toString();
				if (entityID != null && !entityID.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO().setEntityID(
									entityID);
				}

				Object commonEntitySk = dataMap.get("CommonEntitySK");
				if (commonEntitySk != null) {
					String corrEntitySK = commonEntitySk.toString();
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setCorrEntitySK(corrEntitySK);
				}
			}

			else if (identification != null && identification.equals("I")) {
				String entityType = dataMap.get("EntityType").toString();
				if (entityType != null && !entityType.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setInqEntityType(entityType);
					getInqEntIDTypeValues(entityType);
				}

				String entityIDType = dataMap.get("EntityIDType").toString();
				if (entityIDType != null && !entityIDType.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setInqEntityIDType(entityIDType);
				}

				String entityID = dataMap.get("EntityID").toString();
				if (entityID != null && !entityID.equals("")) {
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setInqEntityID(entityID);
				}

				Object commonEntitySk = dataMap.get("CommonEntitySK");
				if (commonEntitySk != null) {
					String inqEntitySK = commonEntitySk.toString();
					searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO()
							.setInqEntitySK(inqEntitySK);
				}
			}
		}
	}

	private boolean EntityTypeChangeFlag = true;

	private boolean ajaxEntityTypeChange = true;

	/**
	 * @return Returns the ajaxEntityTypeChange.
	 */
	public boolean isAjaxEntityTypeChange() {

		
		if (EntityTypeChangeFlag) {

			SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
			String selectedEntity = null;
			Map map = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			if (map != null && map.get("entityType") != null) {
				selectedEntity = map.get("entityType").toString();
				
			}
			if (selectedEntity != null
					&& !(selectedEntity.trim().equalsIgnoreCase(""))
					&& !(selectedEntity.trim().equalsIgnoreCase("on"))) {
				getCorrEntIDTypeValues(selectedEntity);
				
				if ("P".equals(selectedEntity)) {
					
					searchCorrespondenceDataBean
							.setProvTypeVVList(getProvTypeReferenceData());

				} else {

					searchCorrespondenceDataBean
							.setProvTypeVVList(new ArrayList());
				}

			}
			EntityTypeChangeFlag = false;
			//
			
		}
		return ajaxEntityTypeChange;
	}

	/**
	 * @param ajaxEntityTypeChange
	 *            The ajaxEntityTypeChange to set.
	 */
	public void setAjaxEntityTypeChange(boolean ajaxEntityTypeChange) {
		this.ajaxEntityTypeChange = ajaxEntityTypeChange;
	}

	private boolean ajaxLobChangeFlag = true;

	private boolean ajaxLobChange = true;

	/**
	 * @return Returns the ajaxLobChange.
	 */
	public boolean isAjaxLobChange() {

		
		if (ajaxLobChangeFlag) {

			SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
			List listSK = new ArrayList();
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

			String selectedLOB = null;
			Map map = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			if (map != null && map.get("lob") != null) {
				selectedLOB = map.get("lob").toString();
				
			}
			if (selectedLOB != null && !(selectedLOB.trim().equalsIgnoreCase(""))
					&& !(selectedLOB.trim().equalsIgnoreCase("on"))) {

				
				/*
				 * searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
				 * .getAdvSearchCriteria().setLineOfBusinessGroup(selectedLOB);
				 */
				//FindBUg Issue Resolved
				//LineOfBusinessHierarchy lineOfBusinessHierarchy = new
				// LineOfBusinessHierarchy();
				LineOfBusinessHierarchy lineOfBusinessHierarchy = null;
				try {
					
					lineOfBusinessHierarchy = contactMaintenanceDelegate
							.getLobHierarchyRoot(selectedLOB);

					if (lineOfBusinessHierarchy != null) {
						listSK.add(lineOfBusinessHierarchy
								.getLineOfBusinessHierarchySK());
					}

				} catch (Exception exe) {
					exe.printStackTrace();
				}
				//FindBug Issue Resolved
				//List deptList = new ArrayList();
				List deptList = null;
				//FindBug Issue Resolved
				//List userList = new ArrayList();
				List userList = null;
				if (searchCorrespondenceDataBean.getListOfRepUnits() != null
						&& searchCorrespondenceDataBean.getListOfRepUnits()
								.size() > 0) {
					searchCorrespondenceDataBean.getListOfRepUnits().clear();
				}

				if (searchCorrespondenceDataBean.getListOfRefBusUnits() != null
						&& searchCorrespondenceDataBean.getListOfRefBusUnits()
								.size() > 0) {
					searchCorrespondenceDataBean.getListOfRefBusUnits().clear();
				}

				if (searchCorrespondenceDataBean.getListOfRefDeptUnits() != null
						&& searchCorrespondenceDataBean.getListOfRefDeptUnits()
								.size() > 0) {
					searchCorrespondenceDataBean.getListOfRefDeptUnits()
							.clear();

				}

				deptList = fillLobHierarchyDetails(listSK,
						ContactManagementConstants.TWO);

				try {
					
					if (!deptList.isEmpty()) {
						userList = contactMaintenanceDelegate
								.getUsers(deptList);
						List listOfUsers = new ArrayList();

						for (Iterator iter = userList.iterator(); iter
								.hasNext();) {
							DepartmentUser deptUser = (DepartmentUser) iter
									.next();

							EnterpriseUser user = deptUser.getEnterpriseUser();

							StringBuffer userDesc = new StringBuffer(
									ContactManagementConstants.EMPTY_STRING);

							if (user.getLastName() != null) {
								userDesc
										.append(user.getLastName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getMiddleName() != null) {
								userDesc
										.append(user.getMiddleName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getFirstName() != null) {
								userDesc
										.append(user.getFirstName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getPrefixName() != null) {
								userDesc
										.append(user.getPrefixName())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (user.getSuffixName() != null) {
								userDesc.append(user.getSuffixName());

							}
							if (user.getUserWorkUnitSK() != null) {
								userDesc
										.append(user.getUserWorkUnitSK())
										.append(
												ContactManagementConstants.SPACE_STRING);

							}
							if (testSet.add(user.getUserWorkUnitSK().toString()
									+ userDesc.toString())) {
								listOfUsers.add(new SelectItem(user
										.getUserWorkUnitSK().toString(),
										userDesc.toString()));
							}
						}
						
						searchCorrespondenceDataBean
								.setAssignedToList(listOfUsers);
						searchCorrespondenceDataBean
								.assignedToComparator(searchCorrespondenceDataBean
										.getAssignedToList());
					}
				}
				//FindBug Issue Resolved
				/*
				 * catch (Exception exe) { logger .debug("Exception caught in
				 * isAjaxLobChange--controllerBean"); }
				 */
				catch (LOBHierarchyFetchBusinessException e) {
					e.printStackTrace();
				}

			}/*
			  * else {
			  * searchCorrespondenceDataBean.setAssignedToList(searchCorrespondenceDataBean.getTempAssignedToList());
			  * searchCorrespondenceDataBean.assignedToComparator(searchCorrespondenceDataBean.getAssignedToList());
			  * logger.debug("searchCorrespondenceDataBean.getAssignedToList()
			  * "+searchCorrespondenceDataBean.getAssignedToList().size());
			  *  }
			  */

			/*
			 * if(searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO().getAdvSearchCriteria().getReportingUnit()!=
			 * null) {
			 * if(searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO().getAdvSearchCriteria().getReportingUnit().toString().equalsIgnoreCase("
			 * ")) {
			 * searchCorrespondenceDataBean.setAssignedToList(searchCorrespondenceDataBean.getTempAssignedToList()); } }
			 */
			ajaxLobChangeFlag = false;
			

		}
		return ajaxLobChange;
	}

	/**
	 * @param ajaxLobChange
	 *            The ajaxLobChange to set.
	 */
	public void setAjaxLobChange(boolean ajaxLobChange) {
		this.ajaxLobChange = ajaxLobChange;
	}

	private boolean ajaxReportingUnitChangeFlag = true;

	private boolean ajaxReportingUnitChange = true;

	/**
	 * @return Returns the ajaxReportingUnitChange.
	 */
	public boolean isAjaxReportingUnitChange() {

		
		try {
			if (ajaxReportingUnitChangeFlag) {

				String newValue = null;
				Map map = FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap();
				if (map != null && map.get("reportingUnit") != null) {
					newValue = map.get("reportingUnit").toString();
					
				}
				if (newValue != null && !(newValue.trim().equalsIgnoreCase(""))
						&& !(newValue.trim().equalsIgnoreCase("on"))) {
					SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
					List listSK = new ArrayList();
					List deptList = new ArrayList();
					ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
					if (newValue != null && !(newValue.trim().equalsIgnoreCase(""))) {
						Long selectedRU = Long.valueOf(newValue);
						
						/*
						 * searchCorrespondenceDataBean
						 * .getCorrespondenceSearchCriteriaVO()
						 * .getAdvSearchCriteria().setReportingUnit(newValue);
						 */
						if (searchCorrespondenceDataBean.getListOfRefBusUnits() != null
								&& searchCorrespondenceDataBean
										.getListOfRefBusUnits().size() > 0) {
							searchCorrespondenceDataBean.getListOfRefBusUnits()
									.clear();
						}

						if (searchCorrespondenceDataBean
								.getListOfRefDeptUnits() != null
								&& searchCorrespondenceDataBean
										.getListOfRefDeptUnits().size() > 0) {
							searchCorrespondenceDataBean
									.getListOfRefDeptUnits().clear();
						}

						try {

							listSK.add(selectedRU);

							deptList = fillLobHierarchyDetails(listSK,
									ContactManagementConstants.THREE);
						} catch (Exception exe) {
							exe.printStackTrace();
						}
					}
					//FindBug Issue Resolved
					//List userList = new ArrayList();
					List userList = null;
					try {
						
						if (!deptList.isEmpty()) {
							userList = contactMaintenanceDelegate
									.getUsers(deptList);
							
							List listOfUsers = new ArrayList();

							if (userList != null) {
								for (Iterator iter = userList.iterator(); iter
										.hasNext();) {
									DepartmentUser deptUser = (DepartmentUser) iter
											.next();

									EnterpriseUser user = deptUser
											.getEnterpriseUser();

									StringBuffer userDesc = new StringBuffer(
											ContactManagementConstants.EMPTY_STRING);

									if (user.getLastName() != null) {
										userDesc
												.append(user.getLastName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getMiddleName() != null) {
										userDesc
												.append(user.getMiddleName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getFirstName() != null) {
										userDesc
												.append(user.getFirstName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getPrefixName() != null) {
										userDesc
												.append(user.getPrefixName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getSuffixName() != null) {
										userDesc.append(user.getSuffixName());

									}
									if (user.getUserWorkUnitSK() != null) {
										userDesc
												.append(
														user
																.getUserWorkUnitSK())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (testSet.add(user.getUserWorkUnitSK()
											.toString()
											+ userDesc.toString())) {
										listOfUsers.add(new SelectItem(
												user.getUserWorkUnitSK()
														.toString(), userDesc
														.toString()));
									}
								}

							}
							searchCorrespondenceDataBean
									.setAssignedToList(listOfUsers);
							searchCorrespondenceDataBean
									.assignedToComparator(searchCorrespondenceDataBean
											.getAssignedToList());
						}
					}
					//FindBug Issue Resolved
					/*
					 * catch (Exception exe) { logger .debug("Exception caught
					 * in isAjaxReportingUnitChange--controllerBean"); }
					 */
					catch (LOBHierarchyFetchBusinessException exe) {
						exe.printStackTrace();

					}

				} else {

					getSearchCorrespondenceDataBean().setAssignedToList(
							getSearchCorrespondenceDataBean()
									.getTempAssignedToList());
				}

				ajaxReportingUnitChangeFlag = false;
				

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ajaxReportingUnitChange;
	}

	/**
	 * @param ajaxReportingUnitChange
	 *            The ajaxReportingUnitChange to set.
	 */
	public void setAjaxReportingUnitChange(boolean ajaxReportingUnitChange) {
		this.ajaxReportingUnitChange = ajaxReportingUnitChange;
	}

	private boolean departmentChangeFlag = true;

	private boolean ajaxDepartmentChange = true;

	/**
	 * @return Returns the ajaxDepartmentChange.
	 */
	public boolean isAjaxDepartmentChange() {

		
		try {
			if (departmentChangeFlag) {

				String selectedDepartment = null;
				Map map = FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap();
				if (map != null && map.get("department") != null) {
					selectedDepartment = map.get("department").toString();
					
				}

				if (selectedDepartment != null
						&& !(selectedDepartment.trim().equalsIgnoreCase(""))
						&& !(selectedDepartment.trim().equalsIgnoreCase("on"))) {
					SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
					List listSK = new ArrayList();
					List deptList = new ArrayList();

					ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
					Long selectedDept = Long.valueOf(selectedDepartment);

					

					if (searchCorrespondenceDataBean.getAssignedToList() != null
							&& searchCorrespondenceDataBean.getAssignedToList()
									.size() > 0) {
						searchCorrespondenceDataBean.getAssignedToList()
								.clear();
					}

					try {
						listSK.add(selectedDept);
						List dList = new ArrayList(contactMaintenanceDelegate
								.getLobHierarchyRecord(selectedDept)
								.getDepartments());
						Iterator iterator = dList.iterator();
						if (iterator.hasNext()) {
							Department dep = (Department) iterator.next();
							deptList.add(dep.getWorkUnitSK());
						}
					} catch (Exception exe) {
						exe.printStackTrace();
					}
					//FindBug Issue Resolved
					//List userList = new ArrayList();
					List userList = null;
					try {
						
						if (!deptList.isEmpty()) {
							userList = contactMaintenanceDelegate
									.getUsers(deptList);
							List listOfUsers = new ArrayList();

							if (userList != null) {
								for (Iterator iter = userList.iterator(); iter
										.hasNext();) {
									DepartmentUser deptUser = (DepartmentUser) iter
											.next();

									EnterpriseUser user = deptUser
											.getEnterpriseUser();

									StringBuffer userDesc = new StringBuffer(
											ContactManagementConstants.EMPTY_STRING);

									if (user.getLastName() != null) {
										userDesc
												.append(user.getLastName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getMiddleName() != null) {
										userDesc
												.append(user.getMiddleName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getFirstName() != null) {
										userDesc
												.append(user.getFirstName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getPrefixName() != null) {
										userDesc
												.append(user.getPrefixName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getSuffixName() != null) {
										userDesc.append(user.getSuffixName());

									}
									if (user.getUserWorkUnitSK() != null) {
										userDesc
												.append(
														user
																.getUserWorkUnitSK())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (testSet.add(user.getUserWorkUnitSK()
											.toString()
											+ userDesc.toString())) {
										listOfUsers.add(new SelectItem(
												user.getUserWorkUnitSK()
														.toString(), userDesc
														.toString()));
									}
								}

							}
							searchCorrespondenceDataBean
									.setAssignedToList(listOfUsers);
							searchCorrespondenceDataBean
									.assignedToComparator(searchCorrespondenceDataBean
											.getAssignedToList());
						}
					}
					//FindBug Issue Resolved
					/*
					 * catch (Exception exe) { logger .debug("Exception caught
					 * in isAjaxDepartmentChange--controllerBean"); }
					 */
					catch (LOBHierarchyFetchBusinessException exe) {
						exe.printStackTrace();
					}

				}
				departmentChangeFlag = false;
				

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ajaxDepartmentChange;
	}

	/**
	 * @param ajaxDepartmentChange
	 *            The ajaxDepartmentChange to set.
	 */
	public void setAjaxDepartmentChange(boolean ajaxDepartmentChange) {
		this.ajaxDepartmentChange = ajaxDepartmentChange;
	}

	private boolean businessUnitChangeFlag = true;

	private boolean ajaxBusinessUnitChange = true;

	/**
	 * @return Returns the ajaxBusinessUnitChange.
	 */
	public boolean isAjaxBusinessUnitChange() {

		
		try {
			if (businessUnitChangeFlag) {

				String selectedBusinessUnit = null;
				Map map = FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap();
				if (map != null && map.get("businessUnit") != null) {
					selectedBusinessUnit = map.get("businessUnit").toString();
					if(logger.isInfoEnabled())
					{
					logger
							.info("selectedBusinessUnit::"
									+ selectedBusinessUnit);
					}
				}
				if (selectedBusinessUnit != null
						&& !(selectedBusinessUnit.trim().equalsIgnoreCase(""))
						&& !(selectedBusinessUnit.trim().equalsIgnoreCase("on"))) {
					SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
					List listSK = new ArrayList();
					List deptList = new ArrayList();
					businessUnitChangeFlag = false;
					ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
					Long selectedBU = Long.valueOf(selectedBusinessUnit);

					

					if (searchCorrespondenceDataBean.getListOfRefDeptUnits() != null
							&& searchCorrespondenceDataBean
									.getListOfRefDeptUnits().size() > 0) {
						searchCorrespondenceDataBean.getListOfRefDeptUnits()
								.clear();
					}

					try {
						listSK.add(selectedBU);

						deptList = fillLobHierarchyDetails(listSK,
								ContactManagementConstants.FOUR);
					} catch (Exception exe) {
						exe.printStackTrace();

					}
					//FindBug Issue Resolved
					//List userList = new ArrayList();
					List userList = null;
					try {
						
						if (!deptList.isEmpty()) {
							userList = contactMaintenanceDelegate
									.getUsers(deptList);
							List listOfUsers = new ArrayList();

							if (userList != null) {
								for (Iterator iter = userList.iterator(); iter
										.hasNext();) {
									DepartmentUser deptUser = (DepartmentUser) iter
											.next();

									EnterpriseUser user = deptUser
											.getEnterpriseUser();

									StringBuffer userDesc = new StringBuffer(
											ContactManagementConstants.EMPTY_STRING);

									if (user.getLastName() != null) {
										userDesc
												.append(user.getLastName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getMiddleName() != null) {
										userDesc
												.append(user.getMiddleName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getFirstName() != null) {
										userDesc
												.append(user.getFirstName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}

									if (user.getPrefixName() != null) {
										userDesc
												.append(user.getPrefixName())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (user.getSuffixName() != null) {
										userDesc.append(user.getSuffixName());

									}
									if (user.getUserWorkUnitSK() != null) {
										userDesc
												.append(
														user
																.getUserWorkUnitSK())
												.append(
														ContactManagementConstants.SPACE_STRING);

									}
									if (testSet.add(user.getUserWorkUnitSK()
											.toString()
											+ userDesc.toString())) {
										listOfUsers.add(new SelectItem(
												user.getUserWorkUnitSK()
														.toString(), userDesc
														.toString()));
									}
								}

							}//userList not null check ends
							searchCorrespondenceDataBean
									.setAssignedToList(listOfUsers);
							searchCorrespondenceDataBean
									.assignedToComparator(searchCorrespondenceDataBean
											.getAssignedToList());
						}
					}
					//FindBug Issue Resolved
					/*
					 * catch (Exception exe) { logger .debug("Exception caught
					 * in isAjaxBusinessUnitChange--controllerBean");
					 *  }
					 */
					catch (LOBHierarchyFetchBusinessException exe) {
						exe.printStackTrace();

					}

				}
				

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ajaxBusinessUnitChange;
	}

	/**
	 * @param ajaxBusinessUnitChange
	 *            The ajaxBusinessUnitChange to set.
	 */
	public void setAjaxBusinessUnitChange(boolean ajaxBusinessUnitChange) {
		this.ajaxBusinessUnitChange = ajaxBusinessUnitChange;
	}

	private boolean inqEntityTypeChangeFalg = true;

	private boolean ajaxInqEntityTypeChange = true;

	/**
	 * @return Returns the ajaxInqEntityTypeChange.
	 */
	public boolean isAjaxInqEntityTypeChange() {

		
		if (inqEntityTypeChangeFalg) {

			String selectedEntity = null;
			Map map = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			if (map != null && map.get("inqEntityType") != null) {
				selectedEntity = map.get("inqEntityType").toString();
				
			}

			
			if (selectedEntity != null
					&& !(selectedEntity.trim().equalsIgnoreCase(""))) {
				getInqEntIDTypeValues(selectedEntity);
			}

		}
		inqEntityTypeChangeFalg = false;
		return ajaxInqEntityTypeChange;
	}

	/**
	 * @param ajaxInqEntityTypeChange
	 *            The ajaxInqEntityTypeChange to set.
	 */
	public void setAjaxInqEntityTypeChange(boolean ajaxInqEntityTypeChange) {
		this.ajaxInqEntityTypeChange = ajaxInqEntityTypeChange;
	}

	/**
	 * @return Returns the loadEntityData.
	 */
	public String getLoadEntityData() {
		
		//getUserPermission();
		getDataFromSrchEnt();
		return loadEntityData;
	}

	/**
	 * @param loadEntityData
	 *            The loadEntityData to set.
	 */
	public void setLoadEntityData(String loadEntityData) {
		this.loadEntityData = loadEntityData;
	}

	private void setDateErrMsg() {
		setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE_LINE1,
				new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

		setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE_LINE2,
				new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

		setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE_LINE3,
				new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

		setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE_LINE4,
				new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
	}

	private void sortListSequence(List deptUsersList) {
		Comparator selectItemComparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				String first = null;
				String second = null;

				SelectItem s1 = (SelectItem) obj1;
				SelectItem s2 = (SelectItem) obj2;

				boolean ascending = true;

				if (s1.getLabel() != null && s2.getLabel() != null) {
					first = s1.getLabel().toLowerCase();
					second = s2.getLabel().toLowerCase();

					return first.compareTo(second);
				}
				return 0;
			}

		};
		Collections.sort(deptUsersList, selectItemComparator);
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String sortReassignSearchResults(ActionEvent event) {

		
		// Below code commented and new code added  for CR-ESPRD00798895 for sorting functionality 
		/*SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = getSearchCorrespondenceDataBean()
				.getCorrespondenceSearchCriteriaVO();
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		CMDelegate cmDelegate = new CMDelegate();
		
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		

		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);
		

		searchCorrespondenceDataBean.setImageRender(event.getComponent()
				.getId());
		searchCorrespondenceDataBean.setStartIndexForSrchRslts(0);
		 Sort By Status 
		if (ContactManagementConstants.ORDER_BY_STATUS.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_STATUS);
			correspondenceSearchCriteriaVO.setAscending(true);
		}

		if (ContactManagementConstants.ORDER_BY_STATUS.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_STATUS);
			correspondenceSearchCriteriaVO.setAscending(false);
		}

		 Sort By CRN 
		if (ContactManagementConstants.ORDERBY_CRN.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_CRN);
			correspondenceSearchCriteriaVO.setAscending(true);
		}
		if (ContactManagementConstants.ORDERBY_CRN.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_CRN);
			correspondenceSearchCriteriaVO.setAscending(false);
		}

		 Sort By LOB 
		if (ContactManagementConstants.ORDERBY_LOB_CODE.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_LOB_CODE);
			correspondenceSearchCriteriaVO.setAscending(true);
		}
		if (ContactManagementConstants.ORDERBY_LOB_CODE.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_LOB_CODE);
			correspondenceSearchCriteriaVO.setAscending(false);
		}

		
		 * Sort By EntityType for fixing Defect ESPRD00642022 starts
		 
		
		 * if (ContactManagementConstants.ORDERBY_ENTITY_TYPE.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * logger.debug("Inside if loop for EntityType sort Asc");
		 * correspondenceSearchCriteriaVO.setSortColumn(ContactManagementConstants.ORDERBY_ENTITY_TYPE);
		 * correspondenceSearchCriteriaVO.setAscending(true); } if
		 * (ContactManagementConstants.ORDERBY_ENTITY_TYPE.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * logger.debug("Inside if loop for EntityType sort DESC");
		 * correspondenceSearchCriteriaVO.setSortColumn(ContactManagementConstants.ORDERBY_ENTITY_TYPE);
		 * correspondenceSearchCriteriaVO.setAscending(false); }
		 
		if (ContactManagementConstants.ORDERBY_ENTITY_TYPE.equals(sortColumn)) {
		
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_ENTITY_TYPE);
			correspondenceSearchCriteriaVO.setAscending(true);

			

			ArrayList crSearchResults = (ArrayList) getSearchCorrespondenceDataBean()
					.getSearchResults();

			Comparator comparator = new Comparator() {
				public int compare(Object obj1, Object obj2) {
					CorrespondenceSearchResultsVO data1 = (CorrespondenceSearchResultsVO) obj1;
					CorrespondenceSearchResultsVO data2 = (CorrespondenceSearchResultsVO) obj2;
					boolean ascending = false;
					if ("asc".equals(sortOrder)) {
						ascending = true;
					} else {
						ascending = false;
					}
					if (null == data1.getEntityType()) {
						data1
								.setEntityType(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getEntityType()) {
						data2
								.setEntityType(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? (data1.getEntityType()
							.compareToIgnoreCase(data2.getEntityType()))
							: (data2.getEntityType().compareToIgnoreCase(data1
									.getEntityType()));
				}
			};
			//FindBug Issue Resolved
			//Collections.sort(crSearchResults, comparator);
			if (crSearchResults != null) {
				Collections.sort(crSearchResults, comparator);
			}
			return "success";
		}
		//for fixing Defect ESPRD00642022 ends

		 Sort By Category 
		if (ContactManagementConstants.ORDERBY_CATEGORY.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_CATEGORY);
			correspondenceSearchCriteriaVO.setAscending(true);
		}
		if (ContactManagementConstants.ORDERBY_CATEGORY.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_CATEGORY);
			correspondenceSearchCriteriaVO.setAscending(false);
		}

		 Sort By EntityName 
		
		 * if (ContactManagementConstants.ORDERBY_ENTITY_NAME.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
		 * logger.debug("Inside if loop for Category sort Asc");
		 * correspondenceSearchCriteriaVO.setSortColumn(ContactManagementConstants.ORDERBY_ENTITY_NAME);
		 * correspondenceSearchCriteriaVO.setAscending(true); } if
		 * (ContactManagementConstants.ORDERBY_ENTITY_NAME.equals(sortColumn) &&
		 * ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
		 * logger.debug("Inside if loop for Category sort DESC");
		 * correspondenceSearchCriteriaVO.setSortColumn(ContactManagementConstants.ORDERBY_ENTITY_NAME);
		 * correspondenceSearchCriteriaVO.setAscending(false); }
		 
		if (ContactManagementConstants.ORDERBY_ENTITY_NAME.equals(sortColumn)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_ENTITY_NAME);
			correspondenceSearchCriteriaVO.setAscending(true);

			

			ArrayList crSearchResults = (ArrayList) getSearchCorrespondenceDataBean()
					.getSearchResults();

			Comparator comparator = new Comparator() {
				public int compare(Object obj1, Object obj2) {
					CorrespondenceSearchResultsVO data1 = (CorrespondenceSearchResultsVO) obj1;
					CorrespondenceSearchResultsVO data2 = (CorrespondenceSearchResultsVO) obj2;
					boolean ascending = false;
					if ("asc".equals(sortOrder)) {
						ascending = true;
					} else {
						ascending = false;
					}
					if (null == data1.getEntityName()) {
						data1
								.setEntityName(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getEntityName()) {
						data2
								.setEntityName(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? (data1.getEntityName()
							.compareToIgnoreCase(data2.getEntityName()))
							: (data2.getEntityName().compareToIgnoreCase(data1
									.getEntityName()));
				}
			};
			//FindBug Issue Resolved
			//Collections.sort(crSearchResults, comparator);
			if (crSearchResults != null) {
				Collections.sort(crSearchResults, comparator);
			}
			return "success";
		}
		//for fixing Defect ESPRD00642022 ends

		 Sort By AssignedTo 
		if (ContactManagementConstants.ORDERBY_ASSIGNED_TO.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_ASSIGNED_TO);
			correspondenceSearchCriteriaVO.setAscending(true);
		}
		if (ContactManagementConstants.ORDERBY_ASSIGNED_TO.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_ASSIGNED_TO);
			correspondenceSearchCriteriaVO.setAscending(false);
		}

		 Sort By Created 
		if (ContactManagementConstants.ORDERBY_OPEN_DT.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_OPEN_DT);
			correspondenceSearchCriteriaVO.setAscending(true);
		}

		if (ContactManagementConstants.ORDERBY_OPEN_DT.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_OPEN_DT);
			correspondenceSearchCriteriaVO.setAscending(false);
		}

		 Sort By Business Unit 
		if (ContactManagementConstants.ORDERBY_BUSINESSUNIT.equals(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_BUSINESSUNIT);
			correspondenceSearchCriteriaVO.setAscending(true);
		}
		if (ContactManagementConstants.ORDERBY_BUSINESSUNIT.equals(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equals(sortOrder)) {
			
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDERBY_BUSINESSUNIT);
			correspondenceSearchCriteriaVO.setAscending(false);
		}

		try {
			
			enterpriseSearchResultsVO = cmDelegate
					.getCorrespondence(searchCorrespondenceDataBean
							.getCorrespondenceSearchCriteriaVO());
			//FindBug Issue Resolved
			//List searchResults = new ArrayList();
			List searchResults = null;
			searchResults = enterpriseSearchResultsVO.getSearchResults();
			searchCorrespondenceDataBean.setSearchResults(searchResults);
			
			Iterator itr = searchResults.iterator();
			Map userListMap = new HashMap();
			while (itr.hasNext()) {
				List userList = null;
				List deptListTemp = new ArrayList();
				CorrespondenceSearchResultsVO crResultVo = (CorrespondenceSearchResultsVO) itr
						.next();
				//FindBug Issue Resolved
				//logger.info("crResultVo.getUserWorkUnitSK=========="+crResultVo.getLobHierarchySK());
				if (crResultVo != null
						&& crResultVo.getLobHierarchySK() != null) {
					
					if (!userListMap.containsKey(crResultVo.getLobHierarchySK()
							.toString())) {
						userList = crResultVo.getDeptList();

						List sortList = new ArrayList();
						if (userList != null && userList.size() > 0) {
							
							for (Iterator iter = userList.iterator(); iter
									.hasNext();) {
								Object[] userData = (Object[]) iter.next();
								String userDesc = ContactManagementConstants.EMPTY_STRING;
								String userID = null;

								if (userData[2] != null) {
									userDesc = userData[2].toString();
								}
								if ((userData[2] != null)
										&& (userData[1] != null)) {
									userDesc = userDesc
											+ ContactManagementConstants.COMMA_SEPARATOR
											+ ContactManagementConstants.SPACE_STRING;
								}
								if (userData[1] != null) {
									userDesc = userDesc + userData[1];
								}
								if (userData[3] != null) {
									userID = userData[3].toString();
								}

								if (!getUserID().equalsIgnoreCase(userID)
										&& !crResultVo.getLobHierarchySK()
												.equals(userData[0])) {
									if (userDesc != null
											&& userDesc.length() > 0
											&& userData[0] != null
											&& userID != null) {
										if (!sortList
												.contains((userDesc
														+ ContactManagementConstants.HYPHEN_SEPARATOR + userID))) {
											SelectItem selectItem = new SelectItem(
													userData[0].toString()
															.trim(),
													userDesc
															+ ContactManagementConstants.HYPHEN_SEPARATOR
															+ userID);
											sortList
													.add((userDesc
															+ ContactManagementConstants.HYPHEN_SEPARATOR + userID));
											deptListTemp.add(selectItem);
										}
									}
								}
							}
							crResultVo.getDeptList().clear();
						}
						userListMap.put(crResultVo.getLobHierarchySK()
								.toString(), deptListTemp);
					} else {
						deptListTemp = (List) userListMap.get(crResultVo
								.getLobHierarchySK().toString());
					}
				}
				sortListSequence(deptListTemp);
				//FindBug Issue Resolved
				//crResultVo.setDeptList(deptListTemp);
				if (crResultVo != null && deptListTemp != null) {
					crResultVo.setDeptList(deptListTemp);
				}

				Map entityTypeMap = searchCorrespondenceDataBean
						.getEntityTypeMap();
				Map statusMap = searchCorrespondenceDataBean.getStatusMap();
				Map subjectMap = searchCorrespondenceDataBean.getSubjectMap();
				//FindBugs Issue Start
				
				 * if(entityTypeMap.containsKey(crResultVo.getEntityType())){
				 * crResultVo.setEntityType
				 * (entityTypeMap.get(crResultVo.getEntityType()).toString()); }
				 * if(statusMap.containsKey(crResultVo.getStatus())){
				 * crResultVo.setStatus
				 * (statusMap.get(crResultVo.getStatus()).toString()); }
				 * if(subjectMap.containsKey(crResultVo.getSubjectCode())){
				 * crResultVo.setSubjectCode
				 * (subjectMap.get(crResultVo.getSubjectCode()).toString()); }
				 
				//FindBugs Issue end
				if (null != crResultVo) {
					if (entityTypeMap.containsKey(crResultVo.getEntityType())) {
						crResultVo.setEntityType(entityTypeMap.get(
								crResultVo.getEntityType()).toString());
					}
					if (statusMap.containsKey(crResultVo.getStatus())) {
						crResultVo.setStatus(statusMap.get(
								crResultVo.getStatus()).toString());
					}
					if (subjectMap.containsKey(crResultVo.getSubjectCode())) {
						crResultVo.setSubjectCode(subjectMap.get(
								crResultVo.getSubjectCode()).toString());
					}
				}
			}
			userListMap.clear();
			getAllDeptUsersForReassign();
			searchCorrespondenceDataBean.setShowResultsDiv(true);
		} catch (CorrespondenceRecordFetchBusinessException exe) {
			setErrorMessage(
					ContactManagementConstants.ERR_SW_COULD_NOT_BE_FOUND,
					new Object[] { "Record(s)" },
					MessageUtil.ENTMESSAGES_BUNDLE, null);
			searchCorrespondenceDataBean.setShowResultsDiv(false);
			

		}
		return "success";*/
		
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = getSearchCorrespondenceDataBean()
				.getCorrespondenceSearchCriteriaVO();
		logger.debug("INSIDE  SORTING METHOD ");
		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		logger.debug("Sort column--->" + sortColumn);

		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);
		logger.debug("Sort Order---->" + sortOrder);
		
		searchCorrespondenceDataBean.setSortFlag(true);

		//auto focus to first page by default.
		searchCorrespondenceDataBean.setStartIndexForSrchRslts(0);

		// List searchList = new ArrayList();

		searchCorrespondenceDataBean.setImageRender(event.getComponent()
				.getId());
		
		 if(sortOrder.equals("asc"))
	        {
			 searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO().setAscending(true);
	        }
	        else
	        {
	        	searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO().setAscending(false);
	        }
		 searchCorrespondenceDataBean.setStartRecord(0);
	
		 // Sort By Status 

		if (ContactManagementConstants.ORDER_BY_STATUS.equals(sortColumn)) {
			correspondenceSearchCriteriaVO
					.setSortColumn(ContactManagementConstants.ORDER_BY_STATUS);
		}
		// Sort By CRN 

		if (ContactManagementConstants.ORDERBY_CRN.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_CRN);
		}

		if (ContactManagementConstants.ORDERBY_LOB_CODE.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_LOB_CODE);
		}

		// Sort By EntityType 

		if (ContactManagementConstants.ORDERBY_ENTITY_TYPE.equals(sortColumn)) 
		{
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_CMNENTITY_TYPE);
		}

		// Sort By Category 

		if (ContactManagementConstants.ORDERBY_CATEGORY.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_CATEGORY);
		}

		// Sort By EntityName 

		if (ContactManagementConstants.ORDERBY_ENTITY_NAME.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_ENTITY_NAME);
		}

		// Sort By AssignedTo 

		if (ContactManagementConstants.ORDERBY_ASSIGNED_TO.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_ASSIGNED_TO);
		}

		// Sort By Created 

		if (ContactManagementConstants.ORDERBY_OPEN_DT.equals(sortColumn)) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_OPEN_DT);
		}

		if (ContactManagementConstants.ORDERBY_BUSINESSUNIT.equals(sortColumn)
				) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_BUSINESSUNIT);
		}

		if (ContactManagementConstants.ORDERBY_SUBJ_CODE.equals(sortColumn)
				) {
			searchCorrespondenceDataBean.getCorrespondenceSearchCriteriaVO()
					.setSortColumn(ContactManagementConstants.ORDERBY_SUBJ_CODE);
		}

		getCorrespondence();
		
		return "success";
	}

	/**
	 * @return Returns the loadSearchCRPermission.
	 */
	public String getLoadSearchCRPermission() {
		getUserPermission();
		return loadSearchCRPermission;
	}

	/**
	 * @param loadSearchCRPermission
	 *            The loadSearchCRPermission to set.
	 */
	public void setLoadSearchCRPermission(String loadSearchCRPermission) {
		this.loadSearchCRPermission = loadSearchCRPermission;
	}

	/**
	 * @return Returns the loadReAssignCRPermission.
	 */
	public String getLoadReAssignCRPermission() {

		String userPermission = "";
		String userid = null;
		try {
			userid = getUserIDForSecurity();
			FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();

			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.REASSIGN_CORRESPONDENCE_PAGE,
					userid);
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
			ctrlReqForReassignCorr = false;
		}
		return loadReAssignCRPermission;
	}

	/**
	 * @param loadReAssignCRPermission
	 *            The loadReAssignCRPermission to set.
	 */
	public void setLoadReAssignCRPermission(String loadReAssignCRPermission) {
		this.loadReAssignCRPermission = loadReAssignCRPermission;
	}

	/**
	 * @return Returns the ctrlReqForReassignCorr.
	 */
	public boolean isCtrlReqForReassignCorr() {
		return ctrlReqForReassignCorr;
	}

	/**
	 * @param ctrlReqForReassignCorr
	 *            The ctrlReqForReassignCorr to set.
	 */
	public void setCtrlReqForReassignCorr(boolean ctrlReqForReassignCorr) {
		this.ctrlReqForReassignCorr = ctrlReqForReassignCorr;
	}
	
	//Method added for CR:ESPRD00798895 for pagination
	public String next() {
		logger.info("next");

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();

		searchCorrespondenceDataBean.setEndRecord(searchCorrespondenceDataBean
				.getEndRecord()
				+ searchCorrespondenceDataBean.getItemsPerPage());
		searchCorrespondenceDataBean
				.setStartRecord(searchCorrespondenceDataBean.getEndRecord()
						- (searchCorrespondenceDataBean.getItemsPerPage()));
		searchCorrespondenceDataBean
				.setStartRecordNo(searchCorrespondenceDataBean.getStartRecord() + 1);

		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = searchCorrespondenceDataBean
				.getCorrespondenceSearchCriteriaVO();
	
		// pagination issue new
		if (correspondenceSearchCriteriaVO.isReassignAllFlag()
				&& searchCorrespondenceDataBean.getEndRecord() > searchCorrespondenceDataBean
						.getItemsPerPage()) {
			searchCorrespondenceDataBean.setPaginationFlag(true);
		}
		getCorrespondence();
		
		if (searchCorrespondenceDataBean.getEndRecord() < searchCorrespondenceDataBean
				.getCount()) {
			searchCorrespondenceDataBean.setShowNext(true);
			searchCorrespondenceDataBean.setShowPrevious(true);
			searchCorrespondenceDataBean.setShowNextOne(false);
		} else {
			searchCorrespondenceDataBean
					.setEndRecord(searchCorrespondenceDataBean.getCount());
			searchCorrespondenceDataBean.setShowNext(false);
			searchCorrespondenceDataBean.setShowPrevious(true);
			searchCorrespondenceDataBean.setShowNextOne(false);
		}

		searchCorrespondenceDataBean
				.setCurrentPage(searchCorrespondenceDataBean.getCurrentPage() + 1);
		return ContactManagementConstants.NEXT_SUCCESS;
	}
	
	//Method added for CR:ESPRD00798895 for pagination
	 public String nextOne() {

		logger.debug("Inside nextOne:");
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();

		searchCorrespondenceDataBean.setEndRecord(searchCorrespondenceDataBean
				.getEndRecord()
				+ searchCorrespondenceDataBean.getItemsPerPage() + 10);
		searchCorrespondenceDataBean
				.setStartRecord(searchCorrespondenceDataBean.getEndRecord()
						- (searchCorrespondenceDataBean.getItemsPerPage()));
		searchCorrespondenceDataBean
				.setStartRecordNo(searchCorrespondenceDataBean.getStartRecord() + 1); 

		if (searchCorrespondenceDataBean.getEndRecord() < searchCorrespondenceDataBean
				.getCount()) {
			searchCorrespondenceDataBean.setShowNext(true);
			searchCorrespondenceDataBean.setShowPrevious(true);
			searchCorrespondenceDataBean.setShowNextOne(false);
		} else {
			searchCorrespondenceDataBean
					.setEndRecord(searchCorrespondenceDataBean.getCount());
			searchCorrespondenceDataBean.setShowNext(false);
			searchCorrespondenceDataBean.setShowPrevious(true);
			searchCorrespondenceDataBean.setShowNextOne(false);
		}
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = searchCorrespondenceDataBean
				.getCorrespondenceSearchCriteriaVO();
		
		 // pagination issue new
		if (correspondenceSearchCriteriaVO.isReassignAllFlag()
				&& searchCorrespondenceDataBean.getEndRecord() > searchCorrespondenceDataBean
						.getItemsPerPage()) {
			searchCorrespondenceDataBean.setPaginationFlag(true);
		}
    
	
			getCorrespondence();
		
		searchCorrespondenceDataBean
				.setCurrentPage(searchCorrespondenceDataBean.getCurrentPage() + 2);
		return ProgramConstants.NEXT;
	}
	    
	//Method added for CR:ESPRD00798895 for pagination 
	    /**
	     * This operation will be used for navigating to previous page in the
	     * pagination.
	     *
	     * @return String : String ProgramConstants.SEARCH_SUCCESS Navigation
	     *         message
	     */
	    public String previous() {

		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		int numberOfPages;
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = searchCorrespondenceDataBean
				.getCorrespondenceSearchCriteriaVO();

		if (searchCorrespondenceDataBean.getCount()
				% searchCorrespondenceDataBean.getItemsPerPage() > 0)
			numberOfPages = searchCorrespondenceDataBean.getCount()
					/ searchCorrespondenceDataBean.getItemsPerPage() + 1;
		else
			numberOfPages = searchCorrespondenceDataBean.getCount()
					/ searchCorrespondenceDataBean.getItemsPerPage();
		if (searchCorrespondenceDataBean.getCurrentPage() == numberOfPages) {
			int endRecord = searchCorrespondenceDataBean.getCount()
					% searchCorrespondenceDataBean.getItemsPerPage();
			searchCorrespondenceDataBean
					.setEndRecord(searchCorrespondenceDataBean.getEndRecord()
							- endRecord);
			searchCorrespondenceDataBean
					.setStartRecord(searchCorrespondenceDataBean.getEndRecord()
							- searchCorrespondenceDataBean.getItemsPerPage());
			searchCorrespondenceDataBean
					.setStartRecordNo(searchCorrespondenceDataBean
							.getStartRecord() + 1);
		} else {
			searchCorrespondenceDataBean
					.setEndRecord(searchCorrespondenceDataBean.getEndRecord()
							- searchCorrespondenceDataBean.getItemsPerPage());
			searchCorrespondenceDataBean
					.setStartRecord(searchCorrespondenceDataBean.getEndRecord()
							- searchCorrespondenceDataBean.getItemsPerPage());
			searchCorrespondenceDataBean
					.setStartRecordNo(searchCorrespondenceDataBean
							.getStartRecord() + 1);
		}
		 // pagination issue new
		if (correspondenceSearchCriteriaVO.isReassignAllFlag()
				&& searchCorrespondenceDataBean.getStartRecordNo() > searchCorrespondenceDataBean
						.getItemsPerPage()) {
			searchCorrespondenceDataBean.setPaginationFlag(true);
		}
    
	
			getCorrespondence();
		

		if (searchCorrespondenceDataBean.getStartRecord() > 1) {
			searchCorrespondenceDataBean.setShowNext(true);
			searchCorrespondenceDataBean.setShowPrevious(true);
			searchCorrespondenceDataBean.setShowNextOne(false);
		} else {
			searchCorrespondenceDataBean.setShowNext(true);
			searchCorrespondenceDataBean.setShowPrevious(false);
			searchCorrespondenceDataBean.setShowNextOne(true);
		}
		if (searchCorrespondenceDataBean.getCurrentPage() != 1) {
			searchCorrespondenceDataBean
					.setCurrentPage(searchCorrespondenceDataBean
							.getCurrentPage() - 1);
		}
		if (searchCorrespondenceDataBean.getCount() < 2 * searchCorrespondenceDataBean
				.getItemsPerPage()) {
			searchCorrespondenceDataBean.setShowNextOne(false);

		}
		return ContactManagementConstants.PREVIOUS_SUCCESS;
	} 
	    
	  //Method added for CR:ESPRD00798895 to check selected value from dropdown is User or wrorkunit
	    
	    /**This method is invoked to set search criteria to 
	     * get the search results for reassign.
	     * */
	    private void getCorrespondenceForReassign(    		
			CorrespondenceSearchCriteriaVO searchCriteriaVO) 
	    {
			SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
			List searchList = null;
			List crspdSearchList = new ArrayList();
			CorrespondenceSearchResultsVO searchResultsVO = null;
			String allDeptSelectedValue = searchCorrespondenceDataBean.getSelectedDeptAll();
			searchCriteriaVO.setReasgnToUsrWrkUnt(ContactManagementConstants.EMPTY_STRING);
			searchCriteriaVO.setReasgnToWrkUnt(ContactManagementConstants.EMPTY_STRING);
			
			if (!isNullOrEmptyField(allDeptSelectedValue)) 
			{
				searchCorrespondenceDataBean.setRenderReassignBtn(true);
				ContactManagementHelper helper=new ContactManagementHelper();
				String valueDesc=helper.getDescriptionFromVV(allDeptSelectedValue, searchCorrespondenceDataBean.getReassignAllDeptUsersList());
				searchCriteriaVO.setReassignAllFlag(Boolean.TRUE);
				
				//pagination issue 
				if(!searchCorrespondenceDataBean.isPaginationFlag())
				{
					searchCriteriaVO.setStartIndex(ContactManagementConstants.ZERO);
					searchCorrespondenceDataBean.setStartIndexForSrchRslts(0);
					searchCorrespondenceDataBean.setStartRecord(0);	
					
				}
				
				if(valueDesc.indexOf(ContactManagementConstants.HYPEN)!=ContactManagementConstants.MINUSONE){
					//selected value is user
					searchCriteriaVO.setReasgnToUsrWrkUnt(allDeptSelectedValue);
				}else{
					//selected value is work unit
					searchCriteriaVO.setReasgnToWrkUnt(allDeptSelectedValue);
				}
				searchCriteriaVO.setReassginCr(Boolean.FALSE);
				
				searchCorrespondenceDataBean.setSearchResults(crspdSearchList);
				searchCorrespondenceDataBean.setShowResultsDiv(true);
				
				
			}else{
				//empty value selection in reassigning all drop down.
				searchCriteriaVO.setReassginCr(Boolean.TRUE);
				searchCriteriaVO.setReassignAllFlag(Boolean.FALSE);
				searchCriteriaVO.setReasgnToUsrWrkUnt(ContactManagementConstants.EMPTY_STRING);
				searchCriteriaVO.setReasgnToWrkUnt(ContactManagementConstants.EMPTY_STRING);
			}
		
	}
	    
	  //Method added for CR:ESPRD00798895 - on click of pagination to save record and continue with.. 
		
		public void reassignPageSave() {
			
			SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
			
			boolean isAssign = assignIndividualCR();
			
			if (!isNullOrEmptyField(searchCorrespondenceDataBean.getContinueType())) 
			{
				if (searchCorrespondenceDataBean.getContinueType().equalsIgnoreCase
				(
						ContactManagementConstants.N)) {
					next();
				} 
				else if (searchCorrespondenceDataBean.getContinueType().equalsIgnoreCase(
						ContactManagementConstants.P)) 
				{
					previous();
				} 
				else if (searchCorrespondenceDataBean.getContinueType().equalsIgnoreCase(
						ContactManagementConstants.NO)) 
				{
					nextOne();
				}
			}
			if (isAssign) {
				setErrorMessage(
						MaintainContactManagementUIConstants.REASSIGN_SUCCESS_MSG,
						null, MessageUtil.ENTMESSAGES_BUNDLE, null);
			}
			searchCorrespondenceDataBean
					.setContinueType(ContactManagementConstants.EMPTY_STRING);
		}
		
		 //Code added for CR:ESPRD00798895 - to assign individual CR record as per user/work unit
		/** This method is to assign
		 * individual CR record as per the user/workunit 
		 * selected for each record per page(10).
		 * */
		public boolean assignIndividualCR() {
		boolean isAssigned = Boolean.FALSE;
		Date today = new Date();
		Set crSKSet = new HashSet();
		List correspondenceRoutings = new ArrayList();
		List searchList = new ArrayList();
		String userId = getUserID();
		Boolean flag = Boolean.FALSE;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		searchCorrespondenceDataBean.setRenderReassignBtn(true);
		WorkUnit userWorkUnit = null;

		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		

		try {
			userWorkUnit = contactMaintenanceDelegate.getUserWorkUnit(userId);
			searchList = searchCorrespondenceDataBean.getSearchResults();

			Iterator itr2 = searchList.iterator();
			while (itr2.hasNext()) {
				CorrespondenceRouting crRouting = new CorrespondenceRouting();
				CorrespondenceSearchResultsVO correspondenceToBeReassigned = (CorrespondenceSearchResultsVO) itr2
						.next();

				String assignedTo = correspondenceToBeReassigned
						.getAssignedTo();
				Long toBeRoutedToworkUnitValue = new Long(0);
				if (correspondenceToBeReassigned.getSelectedDept() != null
						&& !correspondenceToBeReassigned
								.getSelectedDept()
								.equals(ContactManagementConstants.EMPTY_STRING)
						&& !crSKSet.contains(correspondenceToBeReassigned
								.getCorrespondenceNumber())) {
					toBeRoutedToworkUnitValue = Long
							.valueOf(correspondenceToBeReassigned
									.getSelectedDept());

					crRouting.setCorrespondenceSK(correspondenceToBeReassigned
							.getCorrespondenceNumber());
					crRouting.setRoutedToWorkUnitSK(toBeRoutedToworkUnitValue);
					crRouting.setAddedAuditTimeStamp(today);
					crRouting.setAddedAuditUserID(getUserID());
					crRouting.setAuditTimeStamp(today);
					crRouting.setAuditUserID(getUserID());
					crRouting.setWatchlistIndicator(Boolean.FALSE);
					crRouting.setRoutedByWorkUnit(userWorkUnit);
					WorkUnit workUnit = new WorkUnit();
					workUnit.setWorkUnitSK(Long
							.valueOf(correspondenceToBeReassigned
									.getSelectedDept()));
					crRouting.setRoutedToWorkUnit(workUnit);
					crRouting.setCorrespondenceRoutingDate(today);
					correspondenceRoutings.add(crRouting);
				}

			}
			flag = contactMaintenanceDelegate
					.reassignCorrespondences(correspondenceRoutings);
			isAssigned = Boolean.TRUE;
		} catch (CorrespondenceUpdateDataException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			searchCorrespondenceDataBean.setShowResultsDiv(false);

		} catch (LOBHierarchyFetchBusinessException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			searchCorrespondenceDataBean.setShowResultsDiv(false);

		}

		if (flag.booleanValue()) {

			FacesContext fc = FacesContext.getCurrentInstance();

			PortletSession pSession = (PortletSession) fc.getExternalContext()
					.getSession(true);
			pSession.setAttribute(ContactManagementConstants.RefreshMyTaskData,
					ContactManagementConstants.RefreshMyTaskData,
					pSession.APPLICATION_SCOPE);

			searchList = searchCorrespondenceDataBean.getSearchResults();
			Iterator itr3 = searchList.iterator();
			while (itr3.hasNext()) {
				CorrespondenceSearchResultsVO correspondenceToBeReassigned = (CorrespondenceSearchResultsVO) itr3
						.next();

				List userList = null;
				String[] splitData = null;
				// commented for unused variables
				// String finalString = null;
				userList = correspondenceToBeReassigned.getDeptList();
				if (userList != null && userList.size() > 0) {

					for (Iterator iter = userList.iterator(); iter.hasNext();) {
						SelectItem userData = (SelectItem) iter.next();

						if (correspondenceToBeReassigned.getSelectedDept() != null
								&& !correspondenceToBeReassigned
										.getSelectedDept().equalsIgnoreCase("")
								&& correspondenceToBeReassigned
										.getSelectedDept() != "") {
							if (correspondenceToBeReassigned.getSelectedDept()
									.equalsIgnoreCase(
											userData.getValue().toString())) {

								if (userData.getLabel() != null
										&& !userData.getLabel()
												.equalsIgnoreCase("")
										&& userData.getLabel() != "") {

									splitData = userData.getLabel().split("-");
									correspondenceToBeReassigned
											.setAssignedTo(splitData[0]);
								}

							}
						}

					}
				}

			}
		
		}
		return isAssigned;
	}
		
		
}