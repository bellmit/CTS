/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CorrespondenceDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CorrespondenceRoutingDOConvertor;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;

/**
 * @author Wipro This class is a controller bean used for Add/update operation
 *         in Routing functionality.
 */
public class RoutingControllerBean
        extends EnterpriseBaseControllerBean
{
    /** Instance of the Enterprise Logger */
    /*private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(RoutingControllerBean.class);*/
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(RoutingControllerBean.class);

    /**
     * A variable of type RoutingDataBean to hold the instance of data bean.
     */
    //private RoutingDataBean routingDataBean = getRoutingDataBean();
	private RoutingDataBean routingDataBean;
    
    /**
     * A variable of type correspondenceDataBean to hold the instance of data bean.
     */
    //private CorrespondenceDataBean correspondenceDataBean = getCorrespondenceDataBean();
	private CorrespondenceDataBean correspondenceDataBean;
    
    /**
     * This field is used to store whether user has permission.
     */
    private boolean controlRequired = true;
    /**
     * This field is used to store whether user has selected user,business unit or work unit
     */
    private Boolean showUorD = Boolean.FALSE;
    /**
     * This field is used to call the getShowUorD() method only once per request
     */
    private boolean invokeShowUorD =  true;
    /**
     * This field is set when the user has selected any user from the dropdown
     */
    private Boolean showUserDept = Boolean.FALSE;
    /**
     * This field is set when the user has selected any department from the dropdown
     */
    private Boolean showDeptUser = Boolean.FALSE;
    /**
     * This field is used to call the getShowUserDept() method only once per request
     */
    private boolean invokeShowUser =  true;
    
    /**
     * This field is used for security.
     */
    private String link2Show;
    
    /**
     * This field is used to call the getShowDeptUser() method only once per request
     */
    private boolean invokeShowDept =  true;
    
    private String intialData;
    
    
    private CommonEntityDataBean commonEntityDataBean;
   
    /**
     * This is the RoutingControllerBean Constructor
     */
    public RoutingControllerBean()
    {

        super();
       /* getUserPermission();
        logger.debug("Inside RoutingControllerBean constuctor");*/
    }
    
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
     * This method gets the permission level for the logged in user
     *
     */	
    public void getUserPermission() {
    	String userPermission = "";
		String userid = getUserIDForSecurity();		
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(ContactManagementConstants.LOG_CORRESPONDENCE_PAGE, userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode",userPermission);
		
		// check the permission level for the user and set it in a boolean variable.  This will be used for Buttons/Links
		if(userPermission.equals("r")) {
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
			/*HttpServletRequest renderrequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(renderrequest, renderresponse);
			if (enterpriseUserProfile != null) {
				userID = enterpriseUserProfile.getUserId();
			}*/
			FacesContext facesContext = FacesContext.getCurrentInstance();
			PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
			userID = getLoggedInUserID(portletRequest);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
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
    public String addRouting()
    {
        
        commonEntityDataBean = (CommonEntityDataBean) getDataBean(ContactManagementConstants.COMMON_ENTITY_DATA_BEAN_NAME);
        commonEntityDataBean.setNoteFlag(false);
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        if (routingDataBean.isRenderAddRouting())
        {
            setErrorMessage(
                    ContactManagementConstants.ERR_REF_ONE_ADD_AT_A_TIME,
                    new Object[] {ContactManagementConstants.ROUTING},
                    ProgramConstants.PROGRAM_MESSAGES_BUNDLE, "routingErr");
        }
        else
        {
            cancelIncompleteAction();
            CorrespondenceRoutingDOConvertor.getReferenceData();

            String recordType = "Correspondence";

            if (ContactManagementConstants.CORRESPONDENCE
                    .equalsIgnoreCase(recordType))
            {
                addCrspdRoutingRec();
            }
            else
            {
                addCaseRoutingRec();
            }
        }
        routingDataBean.setShowSucessMessage(false);
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * @return String
     */
    public String saveRouting()
    {
        
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
        correspondenceDataBean.setCrSaved(false);
        routingDataBean.setShowSucessMessage(false);
        correspondenceDataBean.setInputHiddenId("log_div_routing");
        String sortOrder="descending";
        String sortColumn="Date";
        if (validateRouting())
        {
            setRoutingDate();
            setDaysAssigned();

            CMRoutingVO cmRoutingVO = routingDataBean.getCmRoutingVO();
            if(logger.isDebugEnabled())
            {
            logger.debug("cmRoutingVO.getAssignedToName() "
                    + cmRoutingVO.getAssignedToName());
            logger.debug("cmRoutingVO.getAssignedToSK() "
                    + cmRoutingVO.getAssignedToSK());

            //cmRoutingVO.setRoutedByName(cmRoutingVO.getAssignedToName());
            //cmRoutingVO.setRoutedBySK(cmRoutingVO.getAssignedToSK());

            logger.debug("cmRoutingVO.getGroupType() "
                    + cmRoutingVO.getGroupType());
            }

            if ("U".equalsIgnoreCase(cmRoutingVO.getGroupType()))
            {
            	if(logger.isDebugEnabled())
            	{
                logger.debug("cmRoutingVO.getAssignThisRecordToSK() "
                        + cmRoutingVO.getAssignThisRecordToSK());
            	}
                CorrespondenceRoutingDOConvertor
                        .setUserNameFromList(cmRoutingVO);
                
                                
                CorrespondenceRoutingDOConvertor
                .setUserDeptNameFromList(cmRoutingVO);
                cmRoutingVO.setRoutedToSK(cmRoutingVO.getAssignThisRecordToSK());                
                cmRoutingVO.setRoutedToName(cmRoutingVO.getAssThisRecToName());
                cmRoutingVO.setRoutedToUserID(cmRoutingVO.getRoutedToUserID());
                if(logger.isDebugEnabled())
                {
                logger.debug("RoutedToName  in u:"+cmRoutingVO.getRoutedToName());
                logger.debug("RoutedToSK  in u:"+cmRoutingVO.getRoutedToSK());
                logger.debug("RoutedToUserID  in u:"+cmRoutingVO.getRoutedToUserID());
                }
                /*if(cmRoutingVO.getDeptSK()!= null)
                {
                    getCorrespondenceDataBean().getCorrespondenceRecordVO()
                    .getCorrespondenceDetailsVO().setDepartment(cmRoutingVO.getDeptSK());
                }*/
                
            }
            else if ("B".equalsIgnoreCase(cmRoutingVO.getGroupType()))
            {
               // logger.debug("cmRoutingVO.getDeptSK() "
                //        + cmRoutingVO.getDeptSK());
                CorrespondenceRoutingDOConvertor
                	.setDeptNameFromList(cmRoutingVO);
                /*cmRoutingVO.setRoutedToSK(cmRoutingVO.getDeptSK());               
                cmRoutingVO.setRoutedToName(cmRoutingVO.getDeptName());*/
                if(!isNullOrEmptyField(cmRoutingVO.getAssignThisRecordToSK()))
                {
                	CorrespondenceRoutingDOConvertor
		        		.setUserNameForBusinessUnit(cmRoutingVO);
                	cmRoutingVO.setRoutedToSK(cmRoutingVO
                            .getAssignThisRecordToSK());
                    cmRoutingVO.setRoutedToName(cmRoutingVO
                            .getAssThisRecToName());
                    if(logger.isDebugEnabled())
                    {
                    logger.debug("getAssignThisRecordToSK in if loop B:"+cmRoutingVO.getAssignThisRecordToSK());
                    }
                }
                else
                {                  
                    cmRoutingVO.setRoutedToSK(cmRoutingVO.getDeptSK());                      
                    cmRoutingVO.setRoutedToName(cmRoutingVO.getDeptName());
                    if(logger.isDebugEnabled())
                    {
                    logger.debug("RoutedToName  in w in else :"+cmRoutingVO.getRoutedToName());
                    logger.debug("RoutedToSK  in w in else:"+cmRoutingVO.getRoutedToSK());
                    }
                }
                //CorrespondenceRoutingDOConvertor.setUserNameFromList(cmRoutingVO);
                
            }
            else if ("W".equalsIgnoreCase(cmRoutingVO.getGroupType()))
            {
              //  logger.debug("cmRoutingVO.getDeptSK() "
              //          + cmRoutingVO.getDeptSK());
                //"FindBugs" issue resolved
            	//String depSK = cmRoutingVO.getDeptSK(); 
                CorrespondenceRoutingDOConvertor
                        .setDeptNameFromList(cmRoutingVO);
                CorrespondenceRoutingDOConvertor
                .setUserNameFromList(cmRoutingVO);
                if(logger.isDebugEnabled())
                {
                logger.debug("cmRoutingVO.getAssignThisRecordToSK in W:"+cmRoutingVO.getAssignThisRecordToSK());
                logger.debug("cmRoutingVO.getAssThisRecToName():"+cmRoutingVO.getAssThisRecToName());
                }
                if(!isNullOrEmptyField(cmRoutingVO.getAssignThisRecordToSK()))
                {
                    cmRoutingVO.setRoutedToSK(cmRoutingVO
                            .getAssignThisRecordToSK());
                    cmRoutingVO.setRoutedToName(cmRoutingVO
                            .getAssThisRecToName());
                    /*if(cmRoutingVO.getDeptSK()!= null)
                    {
                        getCorrespondenceDataBean().getCorrespondenceRecordVO()
                        .getCorrespondenceDetailsVO().setDepartment(cmRoutingVO.getDeptSK());
                    }*/
                    if(logger.isDebugEnabled())
                    {
                    logger.debug("cmRoutingVO.getAssignThisRecordToSK in if loop W:"+cmRoutingVO.getAssignThisRecordToSK());
                    }
                }
                else
                {                  
                    cmRoutingVO.setRoutedToSK(cmRoutingVO.getDeptSK());                      
                    cmRoutingVO.setRoutedToName(cmRoutingVO.getDeptName());
                 //   logger.debug("RoutedToName  in w in else :"+cmRoutingVO.getRoutedToName());
                 //   logger.debug("RoutedToSK  in w in else:"+cmRoutingVO.getRoutedToSK());
                    
                }
                
              //  logger.debug("RoutedToName  in w:"+cmRoutingVO.getRoutedToName());
               // logger.debug("RoutedToSK  in w:"+cmRoutingVO.getRoutedToSK());
            }

            cmRoutingVO.setAssignedToName(cmRoutingVO.getRoutedToName());
          //  logger.debug("AssignedToName  :"+cmRoutingVO.getRoutedToName());
            cmRoutingVO.setAssignedToSK(cmRoutingVO.getRoutedToSK());
            if(logger.isDebugEnabled())
            {
            logger.debug("AssignedToSK  :"+cmRoutingVO.getAssignedToSK());
            }
            correspondenceDataBean.getCorrespondenceRecordVO()
                    .getCorrespondenceDetailsVO().setAssignedToWorkUnitName(
                            cmRoutingVO.getAssignedToName());
            correspondenceDataBean.getCorrespondenceRecordVO()
                    .getCorrespondenceDetailsVO().setAssignedToWorkUnitSK(
                            cmRoutingVO.getAssignedToSK());

            CorrespondenceRoutingDOConvertor
                    .setDescForRoutingValidValues(cmRoutingVO);
            routingDataBean.getListOfCMRoutingVOs().add(cmRoutingVO);

            //routingDataBean.setCmRoutingVO(new CMRoutingVO());
            routingDataBean.setRenderAddRouting(false);
            routingDataBean.setRenderEditRouting(true);
            routingDataBean.setShowSucessMessage(true);
            routingDataBean.setRenderNoData(false);
            correspondenceDataBean.setRoutingSaved(true);
        }
        //Note:Added for DEFECT ESPRD00061087
        routingComparator(sortColumn, sortOrder, routingDataBean
                .getListOfCMRoutingVOs());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * 
     */
    private void setRoutingDate()
    {
        Calendar calendar = Calendar.getInstance();
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        Date todaysDate = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat(
                ContactManagementConstants.DATE_FORMAT_TIME, Locale
                        .getDefault());

        routingDataBean.getCmRoutingVO().setDateRouted(todaysDate);
        routingDataBean.getCmRoutingVO().setDateRoutedString(dateFormat.format(todaysDate));
        
    }

    /**
     * @return String
     */
    public String resetRouting()
    {
    	correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        CMRoutingVO cmRoutingVO = routingDataBean.getCmRoutingVO();
        
        // Below if condition added to work reset functionality as per requirement for the Defect ESPRD00865868
        
        if(null != correspondenceDataBean  && correspondenceDataBean.isRenderClientServices())
    	  {
        	cmRoutingVO.setGroupType("W");

            String categorySK = correspondenceDataBean
            .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
            .getCategorySK();

            if (!isNullOrEmptyField(categorySK))
            {
                getListOfDepts(categorySK);
            }
           
            routingDataBean.setRenderUserNames(false);
            routingDataBean.setRenderDeptNames(true);
            cmRoutingVO.setDeptSK(ContactManagementConstants.EMPTY_STRING);
            cmRoutingVO.setBusineesUnit(ContactManagementConstants.EMPTY_STRING);
            cmRoutingVO.setAssignedToUserID(ContactManagementConstants.EMPTY_STRING);
            cmRoutingVO.setAssignThisRecordToSK(ContactManagementConstants.EMPTY_STRING);
            cmRoutingVO.setAssThisRecToName(ContactManagementConstants.EMPTY_STRING);
            cmRoutingVO.setAddToMyWatchList(false);
            routingDataBean.setRenderBuDeptNames(false);
            routingDataBean.setRenderUserDeptNames(false);
            routingDataBean.setRenderDeptUserNames(false);
            routingDataBean.setRenderBunitNames(false);
        
    	 	
    	  }
        else
        {
        	cmRoutingVO.setGroupType(ContactManagementConstants.EMPTY_STRING);
            //cmRoutingVO.setGroupType("B");
            cmRoutingVO.setAssignedToUserID(ContactManagementConstants.EMPTY_STRING);
            cmRoutingVO
                    .setAssignThisRecordToSK(ContactManagementConstants.EMPTY_STRING);
            cmRoutingVO
                    .setAssThisRecToName(ContactManagementConstants.EMPTY_STRING);
            cmRoutingVO.setAddToMyWatchList(false);

            cmRoutingVO.setDeptSK(ContactManagementConstants.EMPTY_STRING);
            cmRoutingVO.setBusineesUnit(ContactManagementConstants.EMPTY_STRING);
            routingDataBean.setRenderDeptUserNames(false);
            routingDataBean.setRenderUserNames(true);
            routingDataBean.setRenderDeptNames(false);
            routingDataBean.setRenderBuDeptNames(false);
            routingDataBean.setRenderUserDeptNames(false);
            routingDataBean.setRenderBunitNames(false);
        }
        List listOfUsers = routingDataBean.getListOfUsers();
        listOfUsers.clear();
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * @return String
     */
    public String cancelRouting()
    {
        
        correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);

        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        routingDataBean.setCmRoutingVO(new CMRoutingVO());
        routingDataBean.setRenderDeptNames(false);
        routingDataBean.setRenderDeptUserNames(false);
        routingDataBean.setRenderUserNames(true);
        routingDataBean.setRenderDeptNames(false);
        routingDataBean.setRenderBuDeptNames(false);
        routingDataBean.setRenderUserDeptNames(false);
        routingDataBean.setRenderBunitNames(false);
        routingDataBean.setRenderBunitNames(false);
        List listOfUsers = routingDataBean.getListOfUsers();
        listOfUsers.clear();
    
        correspondenceDataBean.setInputHiddenId("log_div_routing");
        routingDataBean.setRenderAddRouting(false);
        routingDataBean.setRenderEditRouting(false);
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    
    /**
     * @return String
     */
    public String closeRouting()
    {
        
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        routingDataBean.setRenderEditRouting(false);
        routingDataBean.setShowSucessMessage(false);
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }
	
    /**
     * @return String
     */
    public String getRoutingDetails()
    {
       
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
		if (indexCode == null) {
			indexCode = "0";
		}
        int rowIndex = Integer.parseInt(indexCode);
        routingDataBean.setRoutingRowIndex(rowIndex);
        correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
        CMRoutingVO routingVO = (CMRoutingVO) routingDataBean
                .getListOfCMRoutingVOs().get(rowIndex);
        
        CMRoutingVO tempRoutingVO = getCloneCMRoutingVO(routingVO);
        
        UIComponent component = ContactManagementHelper.findComponentInRoot("RouteAuditId");
		if(component!=null)
		{
			AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(false);
			
		}
		routingVO.setAuditKeyList(tempRoutingVO.getAuditKeyList());


        String createdByName = correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getCreatedByName();
        String createdBySK = correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getCreatedBySK();
        String createdByUserID = correspondenceDataBean
		        .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
		        .getCreatedByUserID();
        String reportingUnit = correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getReportingUnitName();

        tempRoutingVO.setCreatedByName(createdByName);
        tempRoutingVO.setCreatedBySK(createdBySK);
        tempRoutingVO.setCreatedByUserID(createdByUserID);
        tempRoutingVO.setReportingUnit(reportingUnit);

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        try
        {
            boolean groupTypeUser = ("U".equalsIgnoreCase(tempRoutingVO
                    .getGroupType()));
            
            // workunitlist is added on 07/03/2012 for new API
            
            List workUnitList = null;

            String workUnitSK = groupTypeUser ? tempRoutingVO
                    .getAssignThisRecordToSK() : tempRoutingVO.getDeptSK();
                    
            // Below code is commented on 07/03/2012

           /* WorkUnit workUnit = contactMaintenanceDelegate.getWorkUnit(Long
                    .valueOf(workUnitSK));*/
                    //Below code is added on 07/03/2012 for new API
                    
                    DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
        			deptUserBasicInfo.setDataReqested("workUnitSKForWorkUnit");	
        			deptUserBasicInfo.setWorkUnitSK(Long.valueOf(workUnitSK));
        			workUnitList = contactMaintenanceDelegate.getWorkUnitUserDetails(deptUserBasicInfo);
        			
        			DeptUserBasicInfo deptUserBasicInfoResult = null;
        			if(workUnitList!=null && !(workUnitList.isEmpty()))
        			{
        				
        				for(int i=0; i < workUnitList.size(); i++)
        				{
        					
        					deptUserBasicInfoResult = (DeptUserBasicInfo)workUnitList.get(i);
        					
        				}
        				
        				
        			}

            if (groupTypeUser)
            {
            	/*tempRoutingVO.setAssThisRecToName(getUserFullName(workUnit.getEnterpriseUser()));*/
            	tempRoutingVO.setAssThisRecToName(getUserFullNameNewAPI(deptUserBasicInfoResult));
            	
                routingDataBean.setRenderUserNames(true);
                routingDataBean.setRenderDeptNames(false);
                routingDataBean.setRenderUserDeptNames(true);
                routingDataBean.setRenderDeptUserNames(false);
                routingDataBean.setRenderBuDeptNames(false);

            }
            else if ("B".equalsIgnoreCase(tempRoutingVO.getGroupType()))
            {
                /*tempRoutingVO.setDeptName(workUnit.getDepartment().getName());*/
            	if(deptUserBasicInfoResult!=null)
            	{
            		
            		tempRoutingVO.setDeptName(deptUserBasicInfoResult.getDeptName());
            	}
                routingDataBean.setRenderDeptNames(true);
                routingDataBean.setRenderUserNames(false);
                routingDataBean.setRenderUserDeptNames(false);
                routingDataBean.setRenderDeptUserNames(false);
                routingDataBean.setRenderBuDeptNames(true);
            }
            else
            {
                /*tempRoutingVO.setDeptName(workUnit.getDepartment().getName());*/
            	if(deptUserBasicInfoResult!=null)
            	{
            		
            		tempRoutingVO.setDeptName(deptUserBasicInfoResult.getDeptName());
            	}
                routingDataBean.setRenderDeptNames(true);
                routingDataBean.setRenderUserNames(false);
                routingDataBean.setRenderUserDeptNames(false);
                routingDataBean.setRenderDeptUserNames(true);
                routingDataBean.setRenderBuDeptNames(false);
            }

            tempRoutingVO.setAssignedToSK(correspondenceDataBean
                    .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                    .getAssignedToWorkUnitSK());
            tempRoutingVO.setAssignedToName(correspondenceDataBean
                    .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                    .getAssignedToWorkUnitName());
            
            /*tempRoutingVO.setAssignedToUserID(getCorrespondenceDataBean()
                    .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                    .getAssignedToUserID());*/
        }
        catch (Exception e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }

        routingDataBean.setRenderEditRouting(true);
        routingDataBean.setShowSucessMessage(false);
        routingDataBean.setCmRoutingVO(tempRoutingVO);
        
        
        showAuditLog();
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }
    
    //New method added below for ndew API on 07/03/2012.
    
    private String getUserFullNameNewAPI(DeptUserBasicInfo deptUserBasic)
    {
        String userDesc = ContactManagementConstants.EMPTY_STRING;
        
        if(deptUserBasic!=null)
        {	
        	
	        if (!isNullOrEmptyField(deptUserBasic.getLastName()))
	        {
	        	userDesc = deptUserBasic.getLastName();
	        	
	        }
	        if(!isNullOrEmptyField(deptUserBasic.getLastName()) &&
	        		!isNullOrEmptyField(deptUserBasic.getFirstName()))
	        {
	        	userDesc = userDesc 
	            + ContactManagementConstants.COMMA_SEPARATOR
	            + ContactManagementConstants.SPACE_STRING;
	        	
	        }
	        if (!isNullOrEmptyField(deptUserBasic.getFirstName()))
	        {
	        	userDesc = userDesc + deptUserBasic.getFirstName();
	        	
	        }
        }
        
        return userDesc;
    }

    /**
     * 
     */
    private void addCrspdRoutingRec()
    {
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        // List listOfRoutingVOs = routingDataBean.getListOfCMRoutingVOs();
        correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
    	correspondenceDataBean.setCrSaved(false);
        if (!requiredValuesAvbl())
        {
            return;
        }

        CMRoutingVO cmRoutingVO = new CMRoutingVO();

        routingDataBean.setCmRoutingVO(cmRoutingVO);
        routingDataBean.setRenderDeptNames(false);
        routingDataBean.setRenderDeptUserNames(false);
        routingDataBean.setRenderUserNames(true);
        routingDataBean.setRenderBuDeptNames(false);
        routingDataBean.setRenderUserDeptNames(false);
        routingDataBean.setRenderBunitNames(false);
        List listOfUsers = routingDataBean.getListOfUsers();
        listOfUsers.clear();
        populateInitialDetails(cmRoutingVO);

        String loggedInUser = getLoggedInUser();
        String loggedInUserSK = null;
        WorkUnit userWorkUnit = getUserSK(loggedInUser);
       /* if(userWorkUnit != null)
        {
        	cmRoutingVO.setRoutedBySK(userWorkUnit.getWorkUnitSK().toString());
        	cmRoutingVO.setRoutedByName(getName(userWorkUnit));
        	cmRoutingVO.setRoutedByUserID(loggedInUser);
        }*/
        
        DeptUserBasicInfo deptUserBasic = getUserSKNewAPI(loggedInUser);
        if(deptUserBasic != null)
        {
        	
        	cmRoutingVO.setRoutedBySK(deptUserBasic.getWorkUnitSK().toString());
        	//cmRoutingVO.setRoutedByName(getName(userWorkUnit));
        	cmRoutingVO.setRoutedByName(getNameNewAPI(deptUserBasic));
        	cmRoutingVO.setRoutedByUserID(loggedInUser);
        }
        
        //commented for unused variables
        //boolean canRoute = true;
        //boolean canRoute = (isRecordAssignedToUser(cmRoutingVO
          //      .getAssignedToSK(), loggedInUserSK, cmRoutingVO.getGroupType()) && isRecordNotAlreadyRouted());

        /* commented for unused variables
         * if (canRoute)
        {
            String categorySK = correspondenceDataBean
                    .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                    .getCategorySK();

            if (!isNullOrEmptyField(categorySK))
            {
                getListOfUsers(categorySK);
            }
        }*/
      //DEFECT:576206
          
          if(correspondenceDataBean.isRenderClientServices())
          	  {
          	 	routingDataBean.getCmRoutingVO().setGroupType("W");

                  String categorySK = correspondenceDataBean
                  .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                  .getCategorySK();

                  if (!isNullOrEmptyField(categorySK))
                  {
                      getListOfDepts(categorySK);
                      //getListOfUsers(categorySK);
                  }
                  else
                  {
                      logger.error("Please select Category first");
                  }

                  routingDataBean.setRenderUserNames(false);
                  routingDataBean.setRenderDeptNames(true);
                  routingDataBean.setRenderBuDeptNames(false);
                  routingDataBean.setRenderUserDeptNames(false);
                  routingDataBean.setRenderDeptUserNames(false);
                  routingDataBean.setRenderBunitNames(false);
              
          	 	
          	  }
        
        routingDataBean.setRenderAddRouting(true);
    }
    //New method added below for new API on 07/03/2012
    
    private String getNameNewAPI(DeptUserBasicInfo deptUserBasic)
    {
       

        String name = ContactManagementConstants.EMPTY_STRING;
        if(deptUserBasic != null)
        {
        	
        	if (!isNullOrEmptyField(deptUserBasic.getLastName()))
            {
            	name = deptUserBasic.getLastName();
            }
            if(!isNullOrEmptyField(deptUserBasic.getLastName()) &&
            		!isNullOrEmptyField(deptUserBasic.getFirstName()))
            {
            	name = name 
                + ContactManagementConstants.COMMA_SEPARATOR
                + ContactManagementConstants.SPACE_STRING;
            }
            if (!isNullOrEmptyField(deptUserBasic.getFirstName()))
            {
            	name = name + deptUserBasic.getFirstName();
            }
       }
       return name;
    }
   
    //New method added for new API on 07/03/2012
    
    private DeptUserBasicInfo getUserSKNewAPI(String user)
    {
    	
    	ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        WorkUnit workUnit = null;
        List workUnitList = null;
        DeptUserBasicInfo deptUserBasicInfoResult = null;
        try
        {
           	DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
			deptUserBasicInfo.setDataReqested("userIdForWorkUnit");	
			deptUserBasicInfo.setUserID(user);
			workUnitList = contactMaintenanceDelegate.getWorkUnitUserDetails(deptUserBasicInfo);
			
			if(workUnitList!=null && !(workUnitList.isEmpty()))
			{
				
				for(int i=0; i < workUnitList.size(); i++)
				{
					deptUserBasicInfoResult = (DeptUserBasicInfo)workUnitList.get(i);
					
				}
			}
        }
        catch (Exception e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }
        
        return deptUserBasicInfoResult;
    }

    /**
     * @return boolean
     */
    private boolean requiredValuesAvbl()
    {
        
        
        boolean reqdValsAvbl = true;

        if (isNullOrEmptyField(correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getDepartment()))
        {
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.WORKUNIT},
                    MessageUtil.ENTMESSAGES_BUNDLE, "routingErr");

            reqdValsAvbl = false;
        }
        if (isNullOrEmptyField(correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getCategorySK()))
        {
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.CATEGORY},
                    MessageUtil.ENTMESSAGES_BUNDLE, "routingErr");

            reqdValsAvbl = false;
        }
        
        return reqdValsAvbl;
    }

    /**
     * @param cmRoutingVO
     *            The cmRoutingVO to set.
     */
    private void populateInitialDetails(CMRoutingVO cmRoutingVO)
    {
       
        correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
        String createdByName = correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getCreatedByName();
        String createdBySK = correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getCreatedBySK();
        
        String createdByUserID = correspondenceDataBean
		        .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
		        .getCreatedByUserID();
       
        String assignedToName = correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getAssignedToWorkUnitName();
        String assignedToSK = correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getAssignedToWorkUnitSK();
        String reportingUnit = correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getReportingUnitName();

        cmRoutingVO.setCreatedByName(createdByName);
        cmRoutingVO.setCreatedBySK(createdBySK);
        cmRoutingVO.setCreatedByUserID(createdByUserID);
        cmRoutingVO.setAssignedToName(assignedToName);
        cmRoutingVO.setAssignedToSK(assignedToSK);
        cmRoutingVO.setReportingUnit(reportingUnit);
        
        return;
    }

    /**
     * @param user
     *            The user to set.
     * @return Long
     */
    private WorkUnit getUserSK(String user)
    {
    	
    	ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        WorkUnit workUnit = null;

        try
        {
        	workUnit = contactMaintenanceDelegate.getUserWorkUnit(user);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }
        
        return workUnit;
    }

    /**
     * @param assignedTo
     *            The assignedTo to set.
     * @param loggedInUser
     *            The loggedInUser to set.
     * @param groupType
     *            The groupType to set.
     * @return boolean
     */
    private boolean isRecordAssignedToUser(String assignedTo,
            String loggedInUser, String groupType)
    {
       
        
        boolean recAssigned = false;

        recAssigned = assignedTo.equalsIgnoreCase(loggedInUser);

        if (!recAssigned)
        {
            if (!"U".equalsIgnoreCase(groupType))
            {
                setErrorMessage(
                        ContactManagementConstants.ERR_CM_CR_NOT_ASSGN_TO_USER_CANNOT_ROUTE,
                        new Object[] {},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        "routingErr");
            }
            else
            {
                setErrorMessage(
                        ContactManagementConstants.ERR_CM_CR_ASSGN_TO_DEPT_CANNOT_ROUTE,
                        new Object[] {},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        "routingErr");
            }
        }
        
        return recAssigned;
    }

    /**
     * @return boolean
     */
    private boolean isRecordNotAlreadyRouted()
    {
       
       
        boolean recordNotAlreadyRouted = true;
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        List listOfRoutingVOs = routingDataBean.getListOfCMRoutingVOs();

        if (listOfRoutingVOs != null)
        {
            for (Iterator iter = listOfRoutingVOs.iterator(); iter.hasNext();)
            {
                CMRoutingVO cmRoutingVO = (CMRoutingVO) iter.next();

                if (!cmRoutingVO.isDbRecord())
                {
                    recordNotAlreadyRouted = false;

                    setErrorMessage(
                            ContactManagementConstants.ERR_CM_CR_ALREADY_ROUTED_CANNOT_ROUTE,
                            new Object[] {},
                            ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                            "routingErr");

                    break;
                }

            }
        }
        
        return recordNotAlreadyRouted;
    }

    /**
     * @param catSK
     *            The catSK to set.
     */
    private void getListOfUsers(String catSK)
    {
       
      
        Long categorySK = Long.valueOf(catSK);
        CMDelegate cmDelegate = new CMDelegate();
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        try
        {
            List tempListOfUsers = cmDelegate.getAllUsers(categorySK);
            //"FindBugs" issue resolved starts here
            /*logger.debug("getListOfUsers size:" + tempListOfUsers.size());
            List listOfUsers = routingDataBean.getListOfUsers();
            listOfUsers.clear();*/
            if (tempListOfUsers != null)
            {
            
            List listOfUsers = routingDataBean.getListOfUsers();
            listOfUsers.clear();

            /*if (tempListOfUsers != null)
            {*/
            //"FindBugs" issue resolved ends here
                for (Iterator iter = tempListOfUsers.iterator(); iter.hasNext();)
                {
                	Object[] userData = (Object[]) iter.next();
                    String name = ContactManagementConstants.EMPTY_STRING;
                    
                   
                    if (userData[3] != null && !isNullOrEmptyField(userData[3].toString()))
                    {
                    	name = userData[3].toString();
                    }
                    if((userData[3] != null && !isNullOrEmptyField(userData[3].toString())) &&
                    		(userData[2] != null && !isNullOrEmptyField(userData[2].toString())))
                    {
                    	name = name 
                        + ContactManagementConstants.COMMA_SEPARATOR
                        + ContactManagementConstants.SPACE_STRING;
                    }
                    if (userData[2] != null && !isNullOrEmptyField(userData[2].toString()))
                    {
                    	name = name + userData[2].toString();
                    }
                   
                    String userWrkUtSk = userData[0].toString();
                    
                    StringBuffer userLabel = new StringBuffer().append(
                    		name).append(
                            ContactManagementConstants.HYPHEN_SEPARATOR).append(
                            		userData[1].toString());

                    listOfUsers.add(new SelectItem(userData[0].toString(), userLabel.toString()));
                }
            }
        }
        catch (CorrespondenceRecordFetchBusinessException e)
        {
        	if(logger.isDebugEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }
        
    }

    /**
     * 
     */
    private void addCaseRoutingRec()
    {
    	if(logger.isInfoEnabled())
    	{
        logger.info("addCaseRoutingRec");
    	}
    }

    /**
     * This method is used to get the User ID.
     * 
     * @return String : User ID
     */
    public String getUserID()
    {
       
      
        String userId = "USERID1";
        //"FindBugs" issue resolved
        //FacesContext fc = FacesContext.getCurrentInstance();

        /*HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);

        if (eup != null && !isNullOrEmptyField(eup.getUserId()))
        {
            userId = eup.getUserId();
        }*/
        FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		userId = getLoggedInUserID(portletRequest);
        
        return userId;
    }

    /**
     * @return String
     */
    private String getLoggedInUser()
    {
        
       
        String loggedInUser = getUserID();

       

        return loggedInUser;
    }

    /**
     * @param listOfRoutingVO
     *            The listOfRoutingVO to set.
     */
    public void sortRoutingInfoOnDate(List listOfRoutingVO)
    {
       
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        Comparator comparator = new Comparator()
        {
            /*
             * (non-Javadoc)
             * 
             * @see java.util.Comparator#compare(java.lang.Object,
             *      java.lang.Object)
             */
            public int compare(Object obj1, Object obj2)
            {
                

                int returnValue = 0;

                CMRoutingVO cmRoutingVO1 = (CMRoutingVO) obj1;
                CMRoutingVO cmRoutingVO2 = (CMRoutingVO) obj2;

               /* DateFormat dateFormat = new SimpleDateFormat(
                        ContactManagementConstants.DATE_FORMAT, Locale
                                .getDefault());*/ // FInd bug issue

               Date date1 = cmRoutingVO1.getDateRouted();
               Date date2 = cmRoutingVO2.getDateRouted();

                    returnValue = date2.compareTo(date1);
                return returnValue;
            }
        };

        if (routingDataBean.getListOfCMRoutingVOs() != null)
        {
            Collections.sort(routingDataBean.getListOfCMRoutingVOs(),
                    comparator);
        }
        
    }

    /**
     * @param event
     *            The catSK to event.
     */

    public String sortRouting(ActionEvent event)
    {
        
      
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        String sortColumn = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.COLUMN_NAME);
        String sortOrder = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.SORT_ORDER);
        routingDataBean.setImageRender(event.getComponent().getId());
        routingComparator(sortColumn, sortOrder, routingDataBean
                .getListOfCMRoutingVOs());
         
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
    private void routingComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
       
       
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                String first = null;
                String second = null;
                CMRoutingVO data1 = (CMRoutingVO) obj1;
                CMRoutingVO data2 = (CMRoutingVO) obj2;
                String routingDate = "Date";
                String routBy = "Routed By";
                String routTo = "Routed To";
                String watchListind = "Watchlist";
                boolean ascending = false;
                
                //Note:Added for DEFECT ESPRD00061087
                if ("ascending".equalsIgnoreCase(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}
               
				if (sortColumn == null) {
					return 0;
				}
                
                if (routingDate.equals(sortColumn))
                {

                    if (null == data1.getDateRouted())
                    {
                        data1.setDateRouted(new Date());
                    }
                    if (null == data2.getDateRouted())
                    {
                        data2.setDateRouted(new Date());
                    }
                    return ascending ? data1.getDateRouted().compareTo(
                            data2.getDateRouted()) : data2.getDateRouted()
                            .compareTo(data1.getDateRouted());
                }
                        
                if (routBy.equals(sortColumn))
                {
                   
                    if (null == data1.getRoutedByName())
                    {
                        data1
                                .setRoutedByName(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getRoutedByName())
                    {
                        data2
                                .setRoutedByName(ContactManagementConstants.EMPTY_STRING);
                    }                  

                    return ascending ? data1.getRoutedByName().trim().compareTo(
                            data2.getRoutedByName().trim()) : data2.getRoutedByName().trim()
                            .compareTo(data1.getRoutedByName().trim());
                }
                if (routTo.equals(sortColumn))
                {
                    
                    if (null == data1.getRoutedToName())
                    {
                        data1
                                .setRoutedToName(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getRoutedToName())
                    {
                        data2
                                .setRoutedToName(ContactManagementConstants.EMPTY_STRING);
                    }                   

                    return ascending ? data1.getRoutedToName().trim().compareTo(
                            data2.getRoutedToName().trim()) : data2.getRoutedToName().trim()
                            .compareTo(data1.getRoutedToName().trim());
                }
                if(watchListind.equals(sortColumn))
                {
                    
                    if(false == data1.isAddToMyWatchList())
                    {
                        first = "false";
                    }
                    else 
                    {
                        first = "true";
                    }
                    if(false == data1.isAddToMyWatchList())
                    {
                        second = "false";
                    }
                    else 
                    {
                        second = "true";
                    }
                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);
                }          
                  
                return 0;
            }
        };
        Collections.sort(dataList, comparator);
        
    }     
          
        
        
    

    /**
     * 
     */
    private void setDaysAssigned()
    {
        
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
      // Find bug issue
        /*  DateFormat dateFormat = new SimpleDateFormat(ContactManagementConstants.DATE_FORMAT_TIME, Locale
                        .getDefault());*/
        List listOfRoutingVOs = routingDataBean.getListOfCMRoutingVOs();
        if (listOfRoutingVOs == null)
        {
            return;
        }

        CMRoutingVO cmRoutingVO = routingDataBean.getCmRoutingVO();

        long days = 0;
        CMRoutingVO targetRoutingVO = null;

        for (Iterator iter = listOfRoutingVOs.iterator(); iter.hasNext();)
        {
            CMRoutingVO tempRoutingVO = (CMRoutingVO) iter.next();
            Date currentRoutingDate = cmRoutingVO.getDateRouted();
            
            Date earlierRoutingDate = tempRoutingVO.getDateRouted();
            long tempDays = (currentRoutingDate.getTime() - earlierRoutingDate
                    .getTime())
                    / (1000 * 60 * 60 * 24);
            
            if (tempDays > days)
            {
                days = tempDays;
            }
            targetRoutingVO = tempRoutingVO;
       

        	targetRoutingVO.setDaysAssigned(days);
       }
        
    }

    /**
     * @return boolean
     */
    private boolean validateRouting()
    {
        
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
        CMRoutingVO cmRoutingVO = routingDataBean.getCmRoutingVO();
        boolean validGroupType = true;
       
        if (isNullOrEmptyField(cmRoutingVO.getGroupType()) && isNullOrEmptyField(cmRoutingVO
                .getAssignThisRecordToSK()))
        {
        	if(logger.isDebugEnabled())
        	{
        	logger.debug("grouptype  in  show validate" + cmRoutingVO.getGroupType());
        	logger.debug("assgRecToAdd   validate" + cmRoutingVO
                    .getAssignThisRecordToSK());
        	}
        	validGroupType = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] { ContactManagementConstants.SHOW },
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "showroutAdd");
            if (!("B".equalsIgnoreCase(cmRoutingVO.getGroupType())))
            {
            	setErrorMessage(
                    EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.ASSIGN_THIS_RECORD_TO},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "assgRecToAdd");
            }
        }
        else if ("U".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
            validGroupType = !(isNullOrEmptyField(cmRoutingVO
                    .getAssignThisRecordToSK()));
            if (!validGroupType)
            {
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.ASSIGN_THIS_RECORD_TO},
                        ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "assgRecToAdd");
            }
            else if(routingDataBean.isRenderUserDeptNames())
            {
	         //   logger.debug("getRoutingDataBean().isRenderUserDeptNames() is true"+cmRoutingVO
	           //         .getDeptSK());
            	validGroupType = !(isNullOrEmptyField(cmRoutingVO
	                    .getDeptSK()));
            	
	            if (!validGroupType)
	            {
	                setErrorMessage(
	                        EnterpriseMessageConstants.ERR_SW_REQUIRED,
	                        new Object[] {ContactManagementConstants.USER_WORK_UNIT_NAME},
	                        ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "userDeptNameAdd");
	            }
            }
        }

        else if ("B".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
            validGroupType = !(isNullOrEmptyField(cmRoutingVO.getBusineesUnit()));
            if (!validGroupType)
            {
                routingDataBean.setRenderDeptNames(false);
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.BUSINESS_UNIT},
                        ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "showBunitAdd");
            }else if(isNullOrEmptyField(cmRoutingVO.getDeptSK()))
            {
                validGroupType = false;
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.WORK_UNIT_NAME},
                        ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "deptNameAdd");
            }
        }
        else if ("W".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
            validGroupType = !(isNullOrEmptyField(cmRoutingVO.getDeptSK()));
            if (!validGroupType)
            {
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.WORK_UNIT_NAME},
                        ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "deptNameAdd");
            }
        }
       correspondenceDataBean.setRoutingValidated(validGroupType);
       if(logger.isDebugEnabled())
       {
     logger.debug("validated value in Routing-->"+correspondenceDataBean.isRoutingValidated());
       }
       /**
		 * Code is added below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
       if(validGroupType == false)
       {
    	   correspondenceDataBean.setLogCRErrMsgFlag(true);
       }
       else
       {
    	   correspondenceDataBean.setLogCRErrMsgFlag(false);
       }
        
        return validGroupType;
    }

    /**
     * This operation is used to the get the Clone of CMRoutingVO which is
     * selected for edit operation.
     * 
     * @param routingVO :
     *            routingVO selected for edit operation.
     * @return CMRoutingVO : Cloned CMRoutingVO object.
     */
    private CMRoutingVO getCloneCMRoutingVO(CMRoutingVO routingVO)
    {
        CMRoutingVO tempRoutingVO = null;
        
        try
        {
            tempRoutingVO = (CMRoutingVO) routingVO.clone();
        }
        catch (CloneNotSupportedException e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }

        tempRoutingVO = (tempRoutingVO == null) ? new CMRoutingVO()
                : tempRoutingVO;
        
        return tempRoutingVO;
    }

    /**
     * @param event
     *            The event to set.
     * @return String
     */

    public String showUsersOrDepts(ValueChangeEvent event)
    {
        
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
        if (isNullOrEmptyField(event.getNewValue().toString()))
        {
            return ContactManagementConstants.RENDER_SUCCESS;
        }

        String workUnitType = (String) event.getNewValue();
        if(logger.isDebugEnabled())
        {
       logger.debug("WorkUnittype is >>>>>>>>>>>>>:" + workUnitType);
        }
        
        if (ContactManagementConstants.USERS.equalsIgnoreCase(workUnitType))
        {
            String categorySK = correspondenceDataBean
                    .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                    .getCategorySK();
            if(logger.isDebugEnabled())
            {
           logger.debug("categorySK  in show :" + categorySK);
            }
            if (!isNullOrEmptyField(categorySK))
            {
                getListOfUsers(categorySK);
            }
            else
            {
               
            }
            routingDataBean.setRenderUserNames(true);
            routingDataBean.setRenderDeptNames(false);
            routingDataBean.setRenderBuDeptNames(false);
            routingDataBean.setRenderUserDeptNames(false);
            routingDataBean.setRenderDeptUserNames(false);
            routingDataBean.setRenderBunitNames(false);
            
        }

       else  
        {
            if("W".equalsIgnoreCase(workUnitType))
            {
                String categorySK = correspondenceDataBean
                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                .getCategorySK();

                if (!isNullOrEmptyField(categorySK))
                {
                    getListOfDepts(categorySK);
                    //getListOfUsers(categorySK);
                }
                else
                {
                   
                }

                routingDataBean.setRenderUserNames(false);
                routingDataBean.setRenderDeptNames(true);
                routingDataBean.setRenderBuDeptNames(false);
                routingDataBean.setRenderUserDeptNames(false);
                routingDataBean.setRenderDeptUserNames(false);
                routingDataBean.setRenderBunitNames(false);
            }
            if ("B".equalsIgnoreCase(workUnitType))
            {
            	
                ContactMaintenanceDelegate cmtDelegate = new ContactMaintenanceDelegate();
                
                List businessUnitsList = new ArrayList();
               //"FindBugs" issue resolved
                List bUnitList =null;
                //List bUnitList = new ArrayList(0);
                try
				{
                	bUnitList = cmtDelegate.getLobHierarchyDetails(businessUnitsList,
                								ContactManagementConstants.THREE);
                	Iterator itr2 = bUnitList.iterator();
                    while (itr2.hasNext())
                    {
                        LineOfBusinessHierarchy businUnit = (LineOfBusinessHierarchy) itr2
                                .next();
                        businessUnitsList.add(
                                new SelectItem(businUnit.getLineOfBusinessHierarchySK()
                                        .toString(), businUnit
                                        .getLobHierarchyDescription()));
                    }
                }catch(Exception e)
				{
                	e.printStackTrace();
				}
                
                routingDataBean.setBusinessUnitsList(businessUnitsList);
                
                routingDataBean.setRenderUserNames(false);            
                routingDataBean.setRenderDeptNames(false);
                routingDataBean.setRenderBuDeptNames(false);
                routingDataBean.setRenderBunitNames(true);
                routingDataBean.setRenderUserDeptNames(false);
                routingDataBean.setRenderDeptUserNames(false);
                routingDataBean.getCmRoutingVO().setGroupType("B");
            }
        }
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * @param bUnitSK
     *            The bUnit to set
     */
    protected void getListOfBuDepts(String bUnitSK)
    {
        
        List listSK = new ArrayList();
        // List lobHierarchyList = new ArrayList();
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        Long businessUnitSK = Long.valueOf(bUnitSK);
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        List refListOfDepts = null;
        try
        {
        	//below code commented on 07/03/2012 for new API.
           /*listSK.add(businessUnitSK);

            List refListOfDepts = contactMaintenanceDelegate
                    .getDepartmentDetails(listSK);*/
        	
        	//below code added on 07/03/2012 for new API.        	
        	
        	DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
			deptUserBasicInfo.setDataReqested("lobHierarchySKForDepartment");	
			deptUserBasicInfo.setLobHierarchySK(businessUnitSK);
			refListOfDepts = contactMaintenanceDelegate.getDepartmentInfo(deptUserBasicInfo);
			
            //"FindBugs" issue resolved starts here
            /*logger.debug("ListofBuDepts  size :" + refListOfDepts.size());
            List listOfDepts = routingDataBean.getListOfDepts();
            listOfDepts.clear();*/
            if (refListOfDepts != null)
            {
            
            List listOfDepts = routingDataBean.getListOfDepts();
            listOfDepts.clear();
            /*if (refListOfDepts != null)
            {*/
            //"FindBugs" issue resolved ends here
                for (Iterator it2 = refListOfDepts.iterator(); it2.hasNext();)
                {
                	//Below code commented on 07/03/2012
                	
                   /* Department dept = (Department) it2.next();
                   logger.debug("work unit sk: " + dept.getWorkUnitSK()
                            + "dept name : " + dept.getName());
                    listOfDepts.add(new SelectItem(dept.getWorkUnitSK()
                            .toString(), dept.getName()));*/
                	
                	//Below code added on 07/03/2012
                	
                	DeptUserBasicInfo dept = (DeptUserBasicInfo) it2.next();
                   
                     listOfDepts.add(new SelectItem(dept.getWorkUnitSK()
                             .toString(), dept.getDeptName()));
                }
            }
        }
        catch (Exception e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }
    }

    /**
     * @param catSK
     *            The catSK to set.
     */
    protected void getListOfDepts(String catSK)
    {
        
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        Long categorySK = Long.valueOf(catSK);
        CMDelegate cmDelegate = new CMDelegate();

        try
        {
            List refListOfDepts = routingDataBean.getRefListOfDepts();
            refListOfDepts = cmDelegate.getAllDepartments(categorySK);
            //"FindBugs" issue resolved starts here
	        /*logger.debug("refListOfDepts in getlist of depts"
	                    + refListOfDepts.size());
	          List listOfDepts = routingDataBean.getListOfDepts();
	          listOfDepts.clear();*/
            if (refListOfDepts != null)
            {
            
            List listOfDepts = routingDataBean.getListOfDepts();
            listOfDepts.clear();

            /*if (refListOfDepts != null)
            {*/
            //"FindBugs" issue resolved ends here
                Map deptNameMap = new HashMap();
                List deptNameList = new ArrayList();
            	for (Iterator iter = refListOfDepts.iterator(); iter.hasNext();)
                {
                    Department dept = (Department) iter.next();
                    deptNameMap.put(dept.getName(),dept.getWorkUnitSK());
                    deptNameList.add(dept.getName());
                    /*listOfDepts.add(new SelectItem(dept.getWorkUnitSK()
                            .toString(), dept.getName()));*/
                }
            	Collections.sort(deptNameList,getStringComerator());
            	for (Iterator itr = deptNameList.iterator(); itr.hasNext();)
                {
                	Object obj = itr.next();
                	listOfDepts.add(new SelectItem(deptNameMap.get(obj).toString(), obj.toString()));
                }
            	deptNameMap.clear();
                deptNameList.clear();
            }
        }
        catch (CorrespondenceRecordFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
        }
        
    }

    /**
     * @param event
     *            The event to set.
     * @return String
     */
    public String showUserDepts(ValueChangeEvent event)
    {
        
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        DeptUserBasicInfo deptUserBasicInfo=new DeptUserBasicInfo();
        if (isNullOrEmptyField(event.getNewValue().toString()))
        {
            return ContactManagementConstants.RENDER_SUCCESS;
        }

        String userSK = (String) event.getNewValue();

        Long userWorkUnitSK = Long.valueOf(userSK);
        deptUserBasicInfo.setUserEnterpriseSK(userWorkUnitSK);
   	    deptUserBasicInfo.setDataReqested(ProgramConstants.DUBI_Dept_Name_SK);
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        try
        {
          /*  List tempListOfUserDepts = contactMaintenanceDelegate
                    .getDepartmentUsers(userWorkUnitSK);
*/
        	List tempListOfUserDepts = new ArrayList();
        	deptUserBasicInfo= contactMaintenanceDelegate.getDeptUserBasicInfo(deptUserBasicInfo);
            if(deptUserBasicInfo != null)
             {
            	tempListOfUserDepts= deptUserBasicInfo.getDeptNameSKList();
             }
            List listOfUserDepts = routingDataBean.getListOfUserDepts();
            //"FindBugs" issue resolved
            if(listOfUserDepts!=null)
            {
            	listOfUserDepts.clear();
            }

            if (tempListOfUserDepts != null)
            {
                for (Iterator iter = tempListOfUserDepts.iterator(); iter
                        .hasNext();)
                {
                   // DepartmentUser deptUser = (DepartmentUser) iter.next();
                	Object[] deptData = (Object[]) iter.next();
                	String name= deptData[0].toString();
					Long deptsk= (Long) deptData[1];
					
					listOfUserDepts.add(new SelectItem(deptsk.toString(), name));

                 /*   listOfUserDepts.add(new SelectItem(deptUser
                            .getDepartmentSK().toString(), deptUser.getDepartment()
                            .getName()));*/
                }
            }
            routingDataBean.setRenderUserNames(true);
            routingDataBean.setRenderDeptNames(false);
            routingDataBean.setRenderBuDeptNames(false);
            if(listOfUserDepts!=null 
            		&& listOfUserDepts.size()!=0)
            {
            	if(listOfUserDepts.size()==1)
            	{            
            		routingDataBean.setRenderCompForOne(true);
            		//Added to implement Defect ESPRD00232457
            		Object[] deptUser = (Object[]) tempListOfUserDepts.get(0);
	                Long deptsk= (Long)deptUser[1];
            		routingDataBean.setWorkUnitValue(deptUser[0].toString()); 
	                
	                routingDataBean.getCmRoutingVO().setDeptSK(deptsk.toString());
            		
            	} 
            	else
            	{
            		routingDataBean.setWorkUnitValue("Please Select One");
            		routingDataBean.setRenderCompForOne(false);
            	}
            	routingDataBean.setRenderUserDeptNames(true);
            }
            else
            {
            	routingDataBean.setRenderUserDeptNames(false);
            }
            routingDataBean.setRenderDeptUserNames(false);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }
        
        return ContactManagementConstants.RENDER_SUCCESS;
     }

    /**
     * @param event
     *            The event to set.
     * @return String
     */
    public String showDeptUsers(ValueChangeEvent event)
    {
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
        if (isNullOrEmptyField(event.getNewValue().toString()))
        {
            return ContactManagementConstants.RENDER_SUCCESS;
        }
        try
        {
            String deptSK = (String) event.getNewValue();
            List listOfDeptUsers = routingDataBean.getListOfDeptUsers();
            //"FindBugs" issue fixed
            if(listOfDeptUsers!=null)
            {
             listOfDeptUsers.clear();
            }
            Map userDescMap = new HashMap();
            List sortUserDesc = new ArrayList();
		    if(deptSK != null)
		    {
		    	//"FindBugs" issue fixed
		    	//Long departmentSK = new Long(Long.parseLong(deptSK));
		    	Long departmentSK = Long.valueOf(Long.parseLong(deptSK));
		    	String catSK = correspondenceDataBean.getCorrespondenceRecordVO()
		    							.getCorrespondenceDetailsVO().getCategorySK();
		    	if(catSK != null)
		    	{
		    	Long categorySK = Long.valueOf(Long.parseLong(catSK));
		    	
		        /*Commented the below line and added code for defect ESPRD00793297 by calling another method with additional 
		          parameter:categorySK to filter the active internal deptUsersList .*/
		       // List deptUsersList = contactMaintenanceDelegate.getUsersForDepartment(departmentSK,);
		    	List deptUsersList = contactMaintenanceDelegate.getUsrsForDept(departmentSK,categorySK);
		       
		        for (Iterator iterator = deptUsersList.iterator(); iterator
		                .hasNext();)
		        {
		            Object[] userData = (Object[]) iterator.next();
		            String userDesc = null;
		            if (userData[3]!= null && !isNullOrEmptyField(userData[3].toString()))
		            {
		            	userDesc = userData[3].toString();
		            }
		            if((userData[3] != null && !isNullOrEmptyField(userData[3].toString()))
		            		&&	(userData[2] != null && !isNullOrEmptyField(userData[2].toString())))
		            {
		            	userDesc = userDesc 
		                + ContactManagementConstants.COMMA_SEPARATOR
		                + ContactManagementConstants.SPACE_STRING;
		            }
		            if (userData[2] != null && !isNullOrEmptyField(userData[2].toString()))
		            {
		            	userDesc = userDesc + userData[2].toString();
		            }
		            
		            StringBuffer userLabel = new StringBuffer().append(
		            		userDesc).append(
		                    ContactManagementConstants.HYPHEN_SEPARATOR).append(
		                    		userData[1].toString());
		            sortUserDesc.add(userLabel.toString());
		            userDescMap.put(userLabel.toString(),userData[0].toString());
		        }
		        Collections.sort(sortUserDesc,getStringComerator());
		        for (Iterator itr = sortUserDesc.iterator(); itr.hasNext();)
		        {
		        	Object obj = itr.next();
		        	listOfDeptUsers.add(new SelectItem(userDescMap.get(obj.toString()).toString(), obj.toString()));
		        }
		        userDescMap.clear();
		        sortUserDesc.clear();
		        routingDataBean.setRenderUserNames(false);
		        routingDataBean.setRenderDeptNames(true);
		        routingDataBean.setRenderBuDeptNames(false);
		        routingDataBean.setRenderUserDeptNames(false);
		        if(listOfDeptUsers!=null && listOfDeptUsers.size()!=0)
		        {
		        	routingDataBean.setRenderDeptUserNames(true);
		        }
		        else
		        {
		        	routingDataBean.setRenderDeptUserNames(false);
		        }
		    }
		   }
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error("e.getMessage(), e");
        	}
        }
        routingDataBean.setRenderUserNames(false);
        routingDataBean.setRenderDeptNames(true);
        routingDataBean.setRenderUserDeptNames(false);
        //routingDataBean.setRenderDeptUserNames(false);
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * @param user
     *            The user to set.
     * @return String
     */
    private String getUserFullName(EnterpriseUser user)
    {
        String userDesc = ContactManagementConstants.EMPTY_STRING;
        
        if (!isNullOrEmptyField(user.getLastName()))
        {
        	userDesc = user.getLastName();
        }
        if(!isNullOrEmptyField(user.getLastName()) &&
        		!isNullOrEmptyField(user.getFirstName()))
        {
        	userDesc = userDesc 
            + ContactManagementConstants.COMMA_SEPARATOR
            + ContactManagementConstants.SPACE_STRING;
        }
        if (!isNullOrEmptyField(user.getFirstName()))
        {
        	userDesc = userDesc + user.getFirstName();
        }
        
        return userDesc;
    }

    /**
     * This method is used to get the Routing Data Bean.
     * 
     * @return RoutingDataBean : RoutingDataBean object.
     */
    /*public RoutingDataBean getRoutingDataBean()
    {
        logger.info("getRoutingDataBean");
       
        FacesContext fc = FacesContext.getCurrentInstance();
        
        return ((RoutingDataBean) fc.getApplication().createValueBinding(
                ContactManagementConstants.BINDING_BEGIN_SEPARATOR
                        + RoutingDataBean.BEAN_NAME
                        + ContactManagementConstants.BINDING_END_SEPARATOR)
                .getValue(fc));
    }*/

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
            String messageBundle, String componentId)
    {
        
       

        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        Locale locale = root.getLocale();
        String clientId = null;

        facesContext.getApplication().setMessageBundle(messageBundle);
        String errorMsg = MessageUtil.format(messageBundle, errorName,
                arguments, locale);
        FacesMessage fc = new FacesMessage();
        fc.setDetail(errorMsg);

        if (componentId != null)
        {
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
    private boolean isNullOrEmptyField(String inputField)
    {
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
    public UIComponent findComponent(UIComponent base, String id)
    {
       
        
        // Is the "base" component itself the match we are looking for?
        if (id.equals(base.getId()))
        {
            return base;
        }

        // Search through our facets and children
        UIComponent component = null;
        UIComponent result = null;
        Iterator cmpIterator = base.getFacetsAndChildren();

        while (cmpIterator.hasNext() && (result == null))
        {
            component = (UIComponent) cmpIterator.next();
            if (id.equals(component.getId()))
            {
                result = component;
                break;
            }
            result = findComponent(component, id);
            if (result != null)
            {
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
    public UIComponent findComponentInRoot(String id)
    {
        

        

        UIComponent component = null;
        FacesContext context = FacesContext.getCurrentInstance();

        if (context != null)
        {
            UIComponent root = context.getViewRoot();
            component = findComponent(root, id);
        }
        
        return component;
    }

    /**
     * This method is to create an instance of Data Bean.
     * 
     * @return Returns EDMSDefaultsDataBean
     */
    /*protected CorrespondenceDataBean correspondenceDataBean
    {
        routingDataBean = (RoutingDataBean) getDataBean(ROUTING_DATA_BEAN);
        FacesContext fc = FacesContext.getCurrentInstance();
        CorrespondenceDataBean correspondenceDataBean = (CorrespondenceDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + CorrespondenceDataBean.BEAN_NAME + "}")
                .getValue(fc);
        
        return correspondenceDataBean;
    }*/

    /**
     * This operation is used to cancel incomplete user action.
     */
    private void cancelIncompleteAction()
    {
        
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        if (routingDataBean.isRenderAddRouting()
                || routingDataBean.isRenderEditRouting())
        {
            this.cancelRouting();
        }
        
        return;
    }

    /**
     * 
     */
    public void clearRoutingDataBeanState()
    {
       
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        routingDataBean.setCmRoutingVO(new CMRoutingVO());
        routingDataBean.getListOfCMRoutingVOs().clear();
        routingDataBean.getListOfDepts().clear();
        routingDataBean.getListOfDeptUsers().clear();
        routingDataBean.getListOfUserDepts().clear();
        routingDataBean.getListOfUsers().clear();
        routingDataBean.getRefListOfDepts().clear();
        routingDataBean.getRefListOfWorkUnitTypes().clear();
        routingDataBean.setRenderAddRouting(false);
        routingDataBean.setRenderDeptNames(false);
        routingDataBean.setRenderDeptUserNames(false);
        routingDataBean.setRenderEditRouting(false);
        routingDataBean.setRenderNoData(true);
        routingDataBean.setRenderUserDeptNames(false);
        routingDataBean.setRenderUserNames(true);
        routingDataBean.setShowSucessMessage(false);
        routingDataBean.setRenderBuDeptNames(false);

        
    }

          
    
    public String showAuditLog()
    {
        
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
            routingDataBean.setRoutingAuditRender(true);
            routingDataBean.setRoutingAuditOpen(true);
        routingDataBean.setColumnValueRender(false);
        
        EnterpriseSearchResultsVO resultVO = retrieveData(0, 0);
        setRecordRange(resultVO);
        routingDataBean.setRoutingAuditHistoryList(resultVO.getSearchResults());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }
    
    /**
     * show the next page
     * @return
     */
    public String next(){
	//	long entryTime = System.currentTimeMillis();
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ routingDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ routingDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ routingDataBean.getStartRecord());
			logger.debug("The end record......."
					+ routingDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(routingDataBean,
				routingDataBean.getItemsPerPage());

		int start = (entDataBean.getCurrentPage()-1) * ProgramNumberConstants.INT_TEN;
		EnterpriseSearchResultsVO resultVO = retrieveData(start, ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

		routingDataBean.setRoutingAuditHistoryList(resultVO
				.getSearchResults());
		
		/*long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("SearchSystemParameterControllerBean" + "#" + "next"
					+ "#" + (exitTime - entryTime));
		}*/

		return ProgramConstants.NEXT;
    }
    
    /**
     * show the previous page
     * @return
     */
    public String previous() {
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
		
		EnterpriseBaseDataBean entDataBean = previousPage(
				routingDataBean, routingDataBean
						.getItemsPerPage());
		
		int start = (entDataBean.getCurrentPage()-1) * ProgramNumberConstants.INT_TEN;
		EnterpriseSearchResultsVO resultVO = retrieveData(start, ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

		routingDataBean.setRoutingAuditHistoryList(resultVO
				.getSearchResults());

		return ProgramConstants.PREVIOUS;
	}
	
    /**
     * show the column value details of the selected audit log
     * @return
     */
    public String showColValHistory()
    {
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        GlobalAuditsDelegate audit;
        try
        {
            
            audit = new GlobalAuditsDelegate();

            AuditLog auditLog = audit.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
            routingDataBean.setSelectedAuditLog(auditLog);
            routingDataBean.setColumnValueRender(true);
        }
        catch (Exception e)
        {
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
    public String closeColValHistory()
    {
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
        routingDataBean.setColumnValueRender(false);
        return ContactManagementConstants.RENDER_SUCCESS;
    }
    
    /**
     * 
     * @param alertVo
     * @return
     */
	private CorrespondenceRouting convert(CMRoutingVO routingVO ) {
		CorrespondenceRoutingDOConvertor crRoutingConvertor = new CorrespondenceRoutingDOConvertor();
		correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		CorrespondenceRouting crRouting = crRoutingConvertor
				.convertRoutingVOToDO(routingVO);

		 CorrespondenceDOConvertor correspondenceConvertor = new CorrespondenceDOConvertor();
			Correspondence correspondence = correspondenceConvertor.convertCorrespondenceVOToDO(
					correspondenceDataBean.getCorrespondenceRecordVO());
			
		crRouting.setCorrespondence(correspondence);
		crRouting.setCorrespondenceSK(correspondence.getCorrespondenceSK());
		
		return crRouting;
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
	 * @param start
	 * @param noOfRecord
	 * @return
	 */
    private EnterpriseSearchResultsVO retrieveData(int start, int noOfRecord){
	//	long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate audit;
		routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
		try {
			
			audit = new GlobalAuditsDelegate();
			 CorrespondenceRouting routing = convert(routingDataBean
                    .getCmRoutingVO());
			 
			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(routing, start,
							noOfRecord);
			return enterpriseResultVO;
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		/*long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("UpdateSystemParameterControllerBean" + "#" + "#"
					+ (exitTime - entryTime));
		}*/
		
		return null;
    }
    
    /**
     * set the record range based on the retrieve data
     * from the database that is stored in the EnterpriseSearchResultsVO
     * @param enterpriseSearchResultsVO
     */
    public void setRecordRange(EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
        routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
	//	long entryTime = System.currentTimeMillis();
		int listSize;
		
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		
		routingDataBean.setCount((int) totalRecordCount);
		
		int noOfPages = routingDataBean.getCount()
				/ routingDataBean.getItemsPerPage();

		int modNofPages = routingDataBean.getCount()
				% routingDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}
	//	logger.debug("Number Of pages: " + noOfPages);

		routingDataBean
				.setCurrentPage(ProgramConstants.INITIAL_CURRENT_PAGE);
		routingDataBean.setNumberOfPages(noOfPages);
		routingDataBean.setStartRecord(ProgramConstants.START_RECORD);
		routingDataBean
				.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = routingDataBean.getCount();

 	
		if (listSize <= routingDataBean.getItemsPerPage()) {
			routingDataBean.setEndRecord(listSize);
		}
		if (listSize > routingDataBean.getItemsPerPage()) {
			//"FindBugs" issue resolved
			//listSize = routingDataBean.getItemsPerPage();
			routingDataBean.getItemsPerPage();
		}

		if (routingDataBean.getCount() <= routingDataBean
				.getItemsPerPage()) {
			routingDataBean.setShowNext(false);
		} else {
			routingDataBean.setShowNext(true);
		}
		routingDataBean.setShowPrevious(false);
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ routingDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ routingDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ routingDataBean.getStartRecord());
			logger.debug("The end record......."
					+ routingDataBean.getEndRecord());
			logger.debug("The total count......."
					+ routingDataBean.getCount());
		}
		/*long exitTime = System.currentTimeMillis();
		logger.info("DiagnosisCodeControllerBean" + "#" + "setRecordRange"
				+ "#" + "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));*/
	}
    
    
    
    
    
    
    
    
    
    
    

    /**
     * @param routingDataBean
     *            The routingDataBean to set.
     */
    public void setRoutingDataBean(RoutingDataBean routingDataBean)
    {
        this.routingDataBean = routingDataBean;
    }
   
    /**
	 * @return Returns the showUorD.
	 */
	public Boolean getShowUorD() {
	    routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
	    correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		if(invokeShowUorD) {
		String workUnitType = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		Map requestMap = null;
		if (fc != null)
			requestMap = fc.getExternalContext().getRequestParameterMap();
		
			if (requestMap != null && requestMap.get("showroutAdd") != null) {
				workUnitType = requestMap.get("showroutAdd").toString();
				if (isNullOrEmptyField(workUnitType))
		        {
		            return showUorD;
		        }
		
		       
		        if (ContactManagementConstants.USERS.equalsIgnoreCase(workUnitType))
		        {
		            String categorySK = correspondenceDataBean
		                    .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
		                    .getCategorySK();
		           // logger.debug("categorySK  in show :" + categorySK);
		            if (!isNullOrEmptyField(categorySK))
		            {
		                getListOfUsers(categorySK);
		            }
		            else
		            {
		                
		            }
		
		            routingDataBean.setRenderUserNames(true);
		            routingDataBean.setRenderDeptNames(false);
		            routingDataBean.setRenderBuDeptNames(false);
		            routingDataBean.setRenderUserDeptNames(false);
		            routingDataBean.setRenderDeptUserNames(false);
		            
		        }
		       else  
		        {
		            if("W".equalsIgnoreCase(workUnitType))
		            {
		                String categorySK = correspondenceDataBean
		                .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
		                .getCategorySK();
		
		                if (!isNullOrEmptyField(categorySK))
		                {
		                    getListOfDepts(categorySK);
		                    getListOfUsers(categorySK);
		                }
		                else
		                {
		                   
		                }
		
		                routingDataBean.setRenderUserNames(false);
		                routingDataBean.setRenderDeptNames(true);
		                routingDataBean.setRenderBuDeptNames(false);
		                routingDataBean.setRenderUserDeptNames(false);
		                routingDataBean.setRenderDeptUserNames(false);
		            }
		            if ("B".equalsIgnoreCase(workUnitType))
		            {
		                
		                String businessUnitSK = correspondenceDataBean
		                        .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
		                        .getBusinessUnitSK();
		               
		                if (!isNullOrEmptyField(businessUnitSK))
		                {
		
		                    getListOfBuDepts(businessUnitSK);
		                }
		                else
		                {
		                    
		                }
		                routingDataBean.setRenderUserNames(false);            
		                routingDataBean.setRenderDeptNames(false);
		                routingDataBean.setRenderBuDeptNames(true);
		                routingDataBean.setRenderUserDeptNames(false);
		                routingDataBean.setRenderDeptUserNames(false);
		                routingDataBean.getCmRoutingVO().setGroupType("B");
		                
		            }
		              
		        }
			}
			 invokeShowUorD = false;
        }
       
		return showUorD;
	}
	/**
	 * @param showUorD The showUorD to set.
	 */
	public void setShowUorD(Boolean showUorD) {
		this.showUorD = showUorD;
	}
	/**
	 * @return Returns the showDeptUser.
	 */
	public Boolean getShowDeptUser() {
	    routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
	    correspondenceDataBean = (CorrespondenceDataBean) getDataBean(ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		if(invokeShowDept) {
		String deptSK = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		Map requestMap = null;
		
		if (fc != null)
			
			requestMap = fc.getExternalContext().getRequestParameterMap();
		
			if (requestMap != null && requestMap.get("deptNameAdd") != null) {
				deptSK = requestMap.get("deptNameAdd").toString();
				
				//if ("W".equalsIgnoreCase(routingDataBean.getCmRoutingVO().getGroupType()))
				//{
					
					
            String catSK = correspondenceDataBean
                    .getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
                    .getCategorySK();
           
            
            Long categorySK = Long.valueOf(catSK);
            CMDelegate cmDelegate = new CMDelegate();

            if (isNullOrEmptyField(deptSK))
            {
            	
                return showDeptUser;
            }

            try
            {
            	
                List refListOfDepts = routingDataBean.getRefListOfDepts();

                refListOfDepts = cmDelegate.getAllDepartments(categorySK);

                List listOfDeptUsers = routingDataBean.getListOfDeptUsers();
                //"FindBugs" issue fixed
                if(listOfDeptUsers!=null)
                {
                	listOfDeptUsers.clear();
                }
                if (refListOfDepts != null)
                {
                    
                    for (Iterator iter = refListOfDepts.iterator(); iter
                            .hasNext();)
                    {
                        Department dept = (Department) iter.next();
                        
                        if (dept.getWorkUnitSK().toString().equals(deptSK))
                        {
                            Set setOfUsers = dept.getDepartmentUser();
                            
                           // logger
                            //        .debug("setofUsers size:"
                             //               + setOfUsers.size());
                            for (Iterator iterator = setOfUsers.iterator(); iterator
                                    .hasNext();)
                            {
                                DepartmentUser deptUser = (DepartmentUser) iterator
                                        .next();

                                String userDesc = getUserFullName(deptUser
                                        .getEnterpriseUser());

                            //    logger.debug("userFullName of DeptUser :"
                             //           + userDesc);

                                listOfDeptUsers.add(new SelectItem(deptUser
                                        .getUserEnterpriseSK().toString(),
                                        userDesc));
                            }
                        }
                    }

                }
               
                routingDataBean.setRenderUserNames(false);
                routingDataBean.setRenderDeptNames(true);
                routingDataBean.setRenderBuDeptNames(false);
                routingDataBean.setRenderUserDeptNames(false);
                if(listOfDeptUsers!=null 
                		&& listOfDeptUsers.size()!=0)
                {
                	routingDataBean.setRenderDeptUserNames(true);
                }
                else
                {
                	routingDataBean.setRenderDeptUserNames(false);
                }
                
            }
            catch (CorrespondenceRecordFetchBusinessException e)
            {
            	if(logger.isErrorEnabled())
            	{
                logger.error("e.getMessage(), e");
            	}
            }
        }
        /*else 
        {
        
            routingDataBean.setRenderUserNames(false);
            routingDataBean.setRenderDeptNames(true);
            routingDataBean.setRenderUserDeptNames(false);
            routingDataBean.setRenderDeptUserNames(false);
        }*/
			invokeShowDept = false;
			}
		return showDeptUser;
	}
	/**
	 * @param showDeptUser The showDeptUser to set.
	 */
	public void setShowDeptUser(Boolean showDeptUser) {
		this.showDeptUser = showDeptUser;
	}
	/**
	 * @return Returns the showUserDept.
	 */
	public Boolean getShowUserDept() {
	    routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
		if(invokeShowUser) {
		String userSK = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		Map requestMap = null;
		if (fc != null)
			requestMap = fc.getExternalContext().getRequestParameterMap();
		
			if (requestMap != null && requestMap.get("assgRecToAdd") != null) {
				userSK = requestMap.get("assgRecToAdd").toString();
				if (isNullOrEmptyField(userSK))
		        {
		            return showUserDept;
		        }
		
		        Long userWorkUnitSK = Long.valueOf(userSK);
		
		        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		
		        try
		        {
		            List tempListOfUserDepts = contactMaintenanceDelegate
		                    .getDepartmentUsers(userWorkUnitSK);
		
		            List listOfUserDepts = routingDataBean.getListOfUserDepts();
		            //"FindBugs" issue fixed
		            if(listOfUserDepts!=null)
		            { 
		            	listOfUserDepts.clear();
		            }
		            if (tempListOfUserDepts != null)
		            {
		                for (Iterator iter = tempListOfUserDepts.iterator(); iter
		                        .hasNext();)
		                {
		                    DepartmentUser deptUser = (DepartmentUser) iter.next();
		
		                    listOfUserDepts.add(new SelectItem(deptUser
		                            .getDepartmentSK().toString(), deptUser.getDepartment()
		                            .getName()));
		                }
		            }
		            routingDataBean.setRenderUserNames(true);
		            routingDataBean.setRenderDeptNames(false);
		            routingDataBean.setRenderBuDeptNames(false);
		            if(listOfUserDepts!=null 
		            		&& listOfUserDepts.size()!=0)
		            {
		            	routingDataBean.setRenderUserDeptNames(true);
		            }
		            else
		            {
		            	routingDataBean.setRenderUserDeptNames(false);
		            }
		            routingDataBean.setRenderDeptUserNames(false);
		        }
		        catch (LOBHierarchyFetchBusinessException e)
		        {
		        	if(logger.isErrorEnabled())
		        	{
		            logger.error(e.getMessage(), e);
		        	}
		        }
			}
			invokeShowUser = false;
		}
			return showUserDept;
	}
	/**
	 * @param showUserDept The showUserDept to set.
	 */
	public void setShowUserDept(Boolean showUserDept) {
		this.showUserDept = showUserDept;
	}
	/**
	 * @return Returns the intialData.
	 */
	public String getIntialData() {
		
		getUserPermission();
		return intialData;
	}
	/**
	 * @param intialData The intialData to set.
	 */
	public void setIntialData(String intialData) {
		this.intialData = intialData;
	}
	
	/**
	 * @return Returns the link2Show.
	 */
	public String getLink2Show() {
		Map links2Hide = getPermissions();
		//"FindBugs" issue fixed starts
		//Set keys = links2Hide.keySet();
		//Iterator keyitr = keys.iterator();
		routingDataBean = (RoutingDataBean) getDataBean(ContactManagementConstants.ROUTING_DATA_BEAN_NAME);
		 
		/*while( keyitr. hasNext() )
		{
			String linkPermission=(String) keyitr.next();
			if ( linkPermission != null )
			{
				linkPermission = linkPermission.trim();	
				if( linkPermission.equals(ContactManagementConstants.LOG_CASE_ADD_ROUTING))
				{
					if(links2Hide.get(linkPermission).toString().equalsIgnoreCase("R"))
					{
						routingDataBean.setDisableAddRouting(ContactManagementConstants.TRUE);
					}
				}
				
			}
		}*/
		
		Set entry=links2Hide.entrySet();
		Iterator entryIt=entry.iterator();
		while(entryIt.hasNext()){
			Map.Entry entryMap=(Map.Entry)entryIt.next();
			
			String linkPermission=(String)entryMap.getKey();
			if ( linkPermission != null )
			{
				linkPermission = linkPermission.trim();	
				if( linkPermission.equals(ContactManagementConstants.LOG_CASE_ADD_ROUTING))
				{
					if("R".equalsIgnoreCase(entryMap.getValue().toString()))
					{
						routingDataBean.setDisableAddRouting(ContactManagementConstants.TRUE);
					}
				}
			}
		}
		//"FindBugs" issue fixed ends
		return link2Show;
		
	}
	/**
	 * @param link2Show The link2Show to set.
	 */
	public void setLink2Show(String link2Show) {
		
	}
	
	public Map getPermissions()
	 {
		Map linksMap = new HashMap();
  	String userid =getUserID(); //userID();
  	
  	FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
    
  	
	
  	ArrayList linksList2Pass = new ArrayList();
  	
  	linksList2Pass.add(ContactManagementConstants.LOG_CASE_ADD_ROUTING);
  	try {
  		linksMap =  fieldAccessControlImpl.getActionLinkPermission(linksList2Pass,userid);
  		
  	} catch (SecurityFLSServiceException e) {
  		//e.getCause();
  		e.printStackTrace();
  		
  	}
  	//linksMap.put(ContactManagementConstants.LOG_CASE_ADD_ROUTING,"R");
  	return linksMap;
	 }
	
	/**
	 * 
	 * @param workUnit
	 * @return
	 */
	private String getName(WorkUnit workUnit)
    {
        

        String name = ContactManagementConstants.EMPTY_STRING;
        if(workUnit != null)
        {
        	EnterpriseUser user = workUnit.getEnterpriseUser();
        	if (!isNullOrEmptyField(user.getLastName()))
            {
            	name = user.getLastName();
            }
            if(!isNullOrEmptyField(user.getLastName()) &&
            		!isNullOrEmptyField(user.getFirstName()))
            {
            	name = name 
                + ContactManagementConstants.COMMA_SEPARATOR
                + ContactManagementConstants.SPACE_STRING;
            }
            if (!isNullOrEmptyField(user.getFirstName()))
            {
            	name = name + user.getFirstName();
            }
       }
       return name;
    }
	
	public String getDeptsForBsnsUnit(ValueChangeEvent event)
	{
		String businessUnitSK = (String) event.getNewValue();
       
        if (!isNullOrEmptyField(businessUnitSK))
        {
            getListOfBuDepts(businessUnitSK);
            routingDataBean.setRenderDeptNames(true);
        }
        else
        {
           
        }
        
       
        return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	private Comparator getStringComerator()
	{
		Comparator comparator = new Comparator()
        {
            /*
             * (non-Javadoc)
             * 
             * @see java.util.Comparator#compare(java.lang.Object,
             *      java.lang.Object)
             */
            public int compare(Object obj1, Object obj2)
            {
                
                int returnValue = 0;
                String string1 = (String) obj1;
                String string2 = (String) obj2;
                returnValue = string1.compareToIgnoreCase(string2);
                return returnValue;
            }
        };
        return comparator;
	}
}
