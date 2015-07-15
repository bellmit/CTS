/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

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

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
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
import com.acs.enterprise.common.program.contactmanagement.application.dataaccess.exception.CaseRecordUpdateDataException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseTypeFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRecord;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.Executor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;

/**
 * This class implements the searching existing CaseRecord information.
 * 
 * @author Wipro
 */
public class CaseSearchControllerBean
        extends EnterpriseBaseControllerBean
{
    /** Enterprise Logger for Logging */
    
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(EDMSDefaultsDataBean.class);

    // Moved to ContactManagementConstants.java   
    // public static final String Case_DATABEAN_NAME = "caseSearchDataBean";
    
    public static final String CMLOGCASE_DATA_BEAN = "logCaseDataBean";

    /** Holds memberLogCase. */
    private String memberLogCase;

    private String link2Show;
    
    private String loadValidValue;
    
    public static boolean validateSuccess = false;
    
    //MOdified for ESPRD00759221 defect
    private Set testSet = new HashSet(ContactManagementConstants.DEFAULT_SIZE);
	

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
	 * @return Returns the link2Show.
	 */
    public String getLink2Show() {

    
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		String userid = getUserID();
		try {
			String casesearchValue = fieldAccessControlImpl
					.getFiledAccessPermission(
							ContactManagementConstants.CTMGMT_CASE,
							userid);
			caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
			if ("R".equalsIgnoreCase(casesearchValue)) {
				caseSearchDataBean
						.setDisableAddSearch(ContactManagementConstants.TRUE);
			}
			
			/*String reassignValue = fieldAccessControlImpl
					.getFiledAccessPermission(
							ContactManagementConstants.CTMGMT_REASIGN_CASE,
							userid);*/
			String reassignValue = fieldAccessControlImpl
					.getFiledAccessPermission("/Enterprise/CtMgmtReassignCase",
							userid);
			logger.debug("++userid case type--"+userid);
			logger.debug("++Reassign Case::"+reassignValue);
			if ("R".equalsIgnoreCase(reassignValue)) {
				
				caseSearchDataBean
						.setDisableReassignLink(ContactManagementConstants.TRUE);
				caseSearchDataBean
						.setDisableReassignAllCaseDropdown(ContactManagementConstants.TRUE);
				caseSearchDataBean
						.setDisableReassignToDeptDropdown(ContactManagementConstants.TRUE);

				caseSearchDataBean.setControlRequired(ContactManagementConstants.TRUE);
			}
				caseSearchDataBean.setCursorFocusId("EntityID");
    	caseSearchDataBean.setCursorFocusIdCaseSearch("CaseSearchEntityType");
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			 	
		return link2Show;
	}
	
	 public Map getPermissions(){
	  	Map linksMap = new HashMap();
	
		String userid =getUserID();
		logger.debug("userid::" + userid);
    	FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
    	logger.debug("fieldAccessControlImpl::"+ fieldAccessControlImpl);
    	
    	ArrayList linksList2Pass = new ArrayList();
    	
    	linksList2Pass.add(ContactManagementConstants.SEARCH_CASE_ADD);
    	linksList2Pass.add(ContactManagementConstants.REASSIGN_CASE_PAGE);
    	linksList2Pass.add(ContactManagementConstants.REASSIGN_ALL_CASE_TO);
    	linksList2Pass.add(ContactManagementConstants.REASSIGN_TO_DEPT);
    	try {
    		linksMap =  fieldAccessControlImpl.getActionLinkPermission(linksList2Pass,userid);
    		
    	} catch (SecurityFLSServiceException e) {
    		e.getCause();
    		logger.error("Exception has come:"+e.getMessage());
    	}
    	
		return linksMap;
	 }
	/**
	 * @param link2Show The link2Show to set.
	 */
	public void setLink2Show(String link2Show) {
		this.link2Show = link2Show;
	}
    

    /**
     * A variable of type CaseSearchDataBean to hold the instance of data bean.
     */
  
    
    private transient CaseSearchDataBean caseSearchDataBean;


    /** Holds requestScope of type Map. */
    private Map requestScope;

    /**
     * renders the detail part based on the filter.
     * 
     * @param event
     *            event.
     */
    public String onLobChange(ValueChangeEvent event)
    {
        logger.debug("******** onLobChange *********");
        if (!event.getNewValue().equals(""))
        {
            String selectedLOB = (String) event.getNewValue();
            List listSK = new ArrayList();
            ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
            LineOfBusinessHierarchy lineOfBusinessHierarchy = new LineOfBusinessHierarchy();
            caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
            try
            {
                if (!selectedLOB.equalsIgnoreCase(""))
                {
                    lineOfBusinessHierarchy = contactMaintenanceDelegate
                            .getLobHierarchyRoot(selectedLOB);

                    listSK.add(lineOfBusinessHierarchy
                            .getLineOfBusinessHierarchySK());
                }
            }
            catch (Exception exe)
            {
                logger.error(exe.getMessage(), exe);
                
            }

            List deptList = new ArrayList();
            List userList = new ArrayList();

            if (caseSearchDataBean.getReportingUnit() != null
                    && caseSearchDataBean.getReportingUnit().size() > 0)
            {
                caseSearchDataBean.getReportingUnit().clear();
            }

            if (caseSearchDataBean.getBusinessUnit() != null
                    && caseSearchDataBean.getBusinessUnit().size() > 0)
            {
                caseSearchDataBean.getBusinessUnit().clear();
            }

            if (caseSearchDataBean.getDepartments() != null
                    && caseSearchDataBean.getDepartments().size() > 0)
            {
                caseSearchDataBean.getDepartments().clear();

            }
            caseSearchDataBean.getReportingUnit().add(new SelectItem("", "Please Select One"));
            caseSearchDataBean.getBusinessUnit().add(new SelectItem("", "Please Select One"));
            caseSearchDataBean.getDepartments().add(new SelectItem("", "Please Select One"));

            deptList = fillLobHierarchyDetails(listSK,
                    ContactManagementConstants.TWO);

            try
            {
                if (deptList.size() > 0)
                {
                    userList = contactMaintenanceDelegate.getUsers(deptList);
                 
                    List listOfUsers = new ArrayList();

                    if (userList.size() > 0)
                    {
                        if (caseSearchDataBean.getAssignedTo() != null
                                && caseSearchDataBean.getAssignedTo().size() > 0)
                        {
                            caseSearchDataBean.getAssignedTo().clear();

                        }
                        listOfUsers.add(new SelectItem("", "Please Select One"));
                    }
                    for (Iterator iter = userList.iterator(); iter.hasNext();)
                    {
                        DepartmentUser deptUser = (DepartmentUser) iter.next();

                        EnterpriseUser user = deptUser.getEnterpriseUser();

                        StringBuffer userDesc = new StringBuffer(
                                ContactManagementConstants.EMPTY_STRING);

                        //MOdified for ESPRD00759221 defect starts
                        if (user.getLastName() != null)
                        {
                            userDesc
									.append(user.getLastName())
									.append(ContactManagementConstants.COMMA_SEPARATOR)
									.append(ContactManagementConstants.SPACE_STRING);

                        }
                        /*if (user.getMiddleName() != null)
                        {
                            userDesc.append(user.getMiddleName()
                                    + ContactManagementConstants.SPACE_STRING);

                        }*/
                        if (user.getFirstName() != null)
                        {
                            userDesc
									.append(user.getFirstName())
									.append(ContactManagementConstants.SPACE_STRING)
									.append(ContactManagementConstants.HYPHEN_SEPARATOR)
									.append(ContactManagementConstants.SPACE_STRING);

                        }
                        if (user.getUserID() != null)
                        {
                            userDesc.append(user.getUserID());

                        }
                        /*if (user.getPrefixName() != null)
                        {
                            userDesc.append(user.getPrefixName()
                                    + ContactManagementConstants.SPACE_STRING);

                        }           
                                                
                        if (user.getSuffixName() != null)
                        {
                            userDesc.append(user.getSuffixName()+ ContactManagementConstants.SPACE_STRING);

                        }
                        
                        if (user.getUserWorkUnitSK() != null)
                        {
                            userDesc.append(user.getUserWorkUnitSK());
                        }*/
                        
                        if(testSet.add(user.getUserWorkUnitSK().toString()+ userDesc.toString()))
                        {
                        listOfUsers.add(new SelectItem(user.getUserWorkUnitSK()
                                .toString(), userDesc.toString()));
                        }
                      //for ESPRD00759221 ends
                    }
                     
                    caseSearchDataBean.setAssignedTo(listOfUsers);
                }
            }
            catch (Exception exe)
            {
                logger.debug("Exception caught in onLOBchange--controllerBean");

            }
        }
        else
        {
            getAllHierarchies();
        }

        return MaintainContactManagementUIConstants.SUCCESS;
    }

    public void onReportingUnitChange(ValueChangeEvent event)
    {
        logger.debug("********* onReportingUnitChange ********");
        if (!event.getNewValue().equals(""))
        {
            List listSK = new ArrayList();
            List deptList = new ArrayList();
            ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
            Long slectedReportingUnit = Long.valueOf(event.getNewValue()
                    .toString());
            caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
            if (caseSearchDataBean.getBusinessUnit() != null
                    && caseSearchDataBean.getBusinessUnit().size() > 0)
            {
                caseSearchDataBean.getBusinessUnit().clear();
            }

            if (caseSearchDataBean.getDepartments() != null
                    && caseSearchDataBean.getDepartments().size() > 0)
            {
                caseSearchDataBean.getDepartments().clear();
            }

            caseSearchDataBean.getBusinessUnit().add(new SelectItem("", "Please Select One"));
            caseSearchDataBean.getDepartments().add(new SelectItem("", "Please Select One"));
            try
            {
            	/*FIND BUGS_FIX*/
            	if (slectedReportingUnit != null
                        && !slectedReportingUnit.equals(Long.valueOf(0)))
                {
                    listSK.add(slectedReportingUnit);
                    deptList = fillLobHierarchyDetails(listSK,
                            ContactManagementConstants.THREE);
                }

            }
            catch (Exception exe)
            {
                logger.error(exe.getMessage());
                logger
                        .debug("Exception in onReportingUnitChange--ControllerBean");
            }

            List userList = new ArrayList();

            try
            {
            	 logger.debug("deptlist size onReportingUnitChange"
                        + deptList.size());
                if (deptList.size() > 0)
                {
                    userList = contactMaintenanceDelegate.getUsers(deptList);
                    List listOfUsers = new ArrayList();
                    
                    listOfUsers.add(new SelectItem("", "Please Select One"));
                    for (Iterator iter = userList.iterator(); iter.hasNext();)
                    {
                        DepartmentUser deptUser = (DepartmentUser) iter.next();

                        EnterpriseUser user = deptUser.getEnterpriseUser();

                        StringBuffer userDesc = new StringBuffer(
                                ContactManagementConstants.EMPTY_STRING);

                        //MOdified for ESPRD00759221 defect starts
                        
                        if (user.getLastName() != null)
                        {
                            userDesc
									.append(user.getLastName())
									.append(ContactManagementConstants.COMMA_SEPARATOR)
									.append(ContactManagementConstants.SPACE_STRING);

                        }
                        /*if (user.getMiddleName() != null)
                        {
                            userDesc.append(user.getMiddleName()
                                    + ContactManagementConstants.SPACE_STRING);

                        }*/
                        if (user.getFirstName() != null)
                        {
                            userDesc
									.append(user.getFirstName())
									.append(ContactManagementConstants.SPACE_STRING)
									.append(ContactManagementConstants.HYPHEN_SEPARATOR)
									.append(ContactManagementConstants.SPACE_STRING);

                        }
                        if (user.getUserID() != null)
                        {
                            userDesc.append(user.getUserID());

                        }
                        /*if (user.getPrefixName() != null)
                        {
                            userDesc.append(user.getPrefixName()
                                    + ContactManagementConstants.SPACE_STRING);

                        }                                                
                        
                        if (user.getSuffixName() != null)
                        {
                            userDesc.append(user.getSuffixName()+ ContactManagementConstants.SPACE_STRING);

                        }
                        if (user.getUserWorkUnitSK() != null)
                        {
                            userDesc.append(user.getUserWorkUnitSK());
                        }*/
                        if(testSet.add(user.getUserWorkUnitSK().toString()+ userDesc.toString()))
                        {
                        listOfUsers.add(new SelectItem(user.getUserWorkUnitSK()
                                .toString(), userDesc.toString()));
                        }
                        //for ESPRD00759221 ends
                    }

                    caseSearchDataBean.setAssignedTo(listOfUsers);
                }
            }
            catch (Exception exe)
            {
                logger
                        .debug("Exception in onReportingUnitChange--controllerBean");

            }
        }
        else
        {
            String lob = caseSearchDataBean.getCaseRecordSearchCriteriaVO()
                    .getLineOfBusinessGroup();
            if (lob == null || lob.equalsIgnoreCase(""))
            {
               getAllHierarchies();
            }
        }
    }

    public void onBusinessUnitChange(ValueChangeEvent event)
    {
        logger.debug("********* onBusinessUnitChange ********");
        if (!event.getNewValue().equals(""))
        {
            List listSK = new ArrayList();
            List deptList = new ArrayList();
            ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
            caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
            Long selectedBU = Long.valueOf(event.getNewValue().toString());

            if (caseSearchDataBean.getDepartments() != null
                    && caseSearchDataBean.getDepartments().size() > 0)
            {
                caseSearchDataBean.getDepartments().clear();
            }
            caseSearchDataBean.getDepartments().add(new SelectItem("", "Please Select One"));
            try
            {
                listSK.add(selectedBU);

                deptList = fillLobHierarchyDetails(listSK,
                        ContactManagementConstants.FOUR);
            }
            catch (Exception exe)
            {
                logger
                        .debug("Exception in onbusinessUnitChange--ControllerBean");

            }

            List userList = new ArrayList();

            try
            {
               
                if (deptList.size() > 0)
                {
                    userList = contactMaintenanceDelegate.getUsers(deptList);
                
                    List listOfUsers = new ArrayList();
                    
                    listOfUsers.add(new SelectItem("", "Please Select One"));
                    for (Iterator iter = userList.iterator(); iter.hasNext();)
                    {
                        DepartmentUser deptUser = (DepartmentUser) iter.next();

                        EnterpriseUser user = deptUser.getEnterpriseUser();

                        StringBuffer userDesc = new StringBuffer(
                                ContactManagementConstants.EMPTY_STRING);

                        //MOdified for ESPRD00759221 defect starts
                        if (user.getLastName() != null)
                        {
                            userDesc
									.append(user.getLastName())
									.append(ContactManagementConstants.COMMA_SEPARATOR)
									.append(ContactManagementConstants.SPACE_STRING);

                        }
                        /*if (user.getMiddleName() != null)
                        {
                            userDesc.append(user.getMiddleName()
                                    + ContactManagementConstants.SPACE_STRING);

                        }*/
                        if (user.getFirstName() != null)
                        {
                            userDesc
									.append(user.getFirstName())
									.append(ContactManagementConstants.SPACE_STRING)
									.append(ContactManagementConstants.HYPHEN_SEPARATOR)
									.append(ContactManagementConstants.SPACE_STRING);

                        }
                        if (user.getUserID() != null)
                        {
                            userDesc.append(user.getUserID());

                        }
                        /*if (user.getPrefixName() != null)
                        {
                            userDesc.append(user.getPrefixName()
                                    + ContactManagementConstants.SPACE_STRING);

                        }                       
                                             
                        if (user.getSuffixName() != null)
                        {
                            userDesc.append(user.getSuffixName()+ ContactManagementConstants.SPACE_STRING);

                        }
                        if (user.getUserWorkUnitSK() != null)
                        {
                            userDesc.append(user.getUserWorkUnitSK());
                        }*/
                        if(testSet.add(user.getUserWorkUnitSK().toString()+ userDesc.toString()))
                        {
                        listOfUsers.add(new SelectItem(user.getUserWorkUnitSK()
                                .toString(), userDesc.toString()));
                        }
                        //for ESPRD00759221 ends
                    }

                    caseSearchDataBean.setAssignedTo(listOfUsers);
                }
            }
            catch (Exception exe)
            {
                logger
                        .debug("Exception in onBusinessUnitChange--controllerBean");

            }
        }
        else
        {
            String reportingUnit = caseSearchDataBean
                    .getCaseRecordSearchCriteriaVO().getReportingUnit();
            String lob = caseSearchDataBean.getCaseRecordSearchCriteriaVO()
                    .getLineOfBusinessGroup();

            if ((reportingUnit == null || reportingUnit.equalsIgnoreCase(""))
                    && (lob == null || lob.equalsIgnoreCase("")))
            {
               getAllHierarchies();
            }
        }
    }
    
    public void getLobonBussinessunitChange(ValueChangeEvent event)
    {
        
        
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        List list = new ArrayList();
        HashMap map = new HashMap();
        List LobdropDown= new ArrayList();
        String validValueCodeDesc=null;
          
        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = null;
        ReferenceDataListVO referenceDataListVO = null;
        
        referenceDataListVO = new ReferenceDataListVO();
        referenceServiceDelegate = new ReferenceServiceDelegate();
        
        String businessUnitDesc= (String) event.getNewValue();
        
        if (businessUnitDesc.equalsIgnoreCase("A")){
            
            list.add(getInputCriteria(ReferenceServiceDataConstants.G_CASE_APL_TY_CD_C,
                    FunctionalAreaConstants.GENERAL));
        	
        } else {
        	list.add(getInputCriteria(ReferenceServiceDataConstants.LOB_OTHERS,
                    FunctionalAreaConstants.GENERAL));
        }
         
        referenceDataSearch.setInputList(list);
       
        try {
            referenceDataListVO = referenceServiceDelegate.getReferenceData(referenceDataSearch);
        } catch (ReferenceServiceRequestException e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
          //  log.error(e.getMessage(), e);
        } catch (SystemListNotFoundException systemListNotFoundException) {
            // TODO Auto-generated catch block
          //  log.error(e.getMessage(), e);
        	logger.error(systemListNotFoundException.getMessage(), systemListNotFoundException);
        }
      
        if (referenceDataListVO != null)
        {
        map = referenceDataListVO.getResponseMap();
   
        }
     try{
     
    List casetypeList = new ArrayList();
            if (businessUnitDesc.equalsIgnoreCase("A")) {
                
                LobdropDown = (List) map.get(FunctionalAreaConstants.GENERAL
                        + "#" + ReferenceServiceDataConstants.G_CASE_APL_TY_CD_C);
               
            }

            else {
                LobdropDown = (List) map.get(FunctionalAreaConstants.GENERAL
                        + "#" + ReferenceServiceDataConstants.LOB_OTHERS);
               
            }
            casetypeList.add(new SelectItem("", "Please Select One"));
            for (int i = 0; i < LobdropDown.size(); i++) {
             
                ReferenceServiceVO refVo = (ReferenceServiceVO) LobdropDown.get(i);
                validValueCodeDesc = refVo.getValidValueCode() + "-"
                        + refVo.getShortDescription();
                casetypeList.add(new SelectItem(refVo.getValidValueCode(),
                        validValueCodeDesc));

            }
    
   caseSearchDataBean.setBusinessUnit(casetypeList);
     } catch(Exception e)
     {
      e.printStackTrace();
     }
    
    
    }
    
    
    
    

    /**
     * @param event
     *            takes event as param
     */
    public void onDepartmentChange(ValueChangeEvent event)
    {
        logger.debug("****onDepartmentChange****");
        if (!event.getNewValue().equals(""))
        {
            List listSK = new ArrayList();
            List deptList = new ArrayList();

            ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
            Long selectedDept = Long.valueOf(event.getNewValue().toString());
            caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
           

            if (caseSearchDataBean.getAssignedTo() != null
                    && caseSearchDataBean.getAssignedTo().size() > 0)
            {
                caseSearchDataBean.getAssignedTo().clear();
            }

            try
            {
                listSK.add(selectedDept);
                List dList = new ArrayList(contactMaintenanceDelegate
                        .getLobHierarchyRecord(selectedDept).getDepartments());

                Iterator iterator = dList.iterator();
                if (iterator.hasNext())
                {
                    Department dep = (Department) iterator.next();
                    deptList.add(dep.getWorkUnitSK());
                }
            }
            catch (Exception exe)
            {
                logger
                        .debug("Exception caught in onDeptChange--ControllerBean  &&&&&&");
            }

            List userList = new ArrayList();

            try
            {
                
                if (deptList.size() > 0)
                {
                    userList = contactMaintenanceDelegate.getUsers(deptList);
            
                    List listOfUsers = new ArrayList();
                    listOfUsers.add(new SelectItem("", "Please Select One"));

                    for (Iterator iter = userList.iterator(); iter.hasNext();)
                    {
                        DepartmentUser deptUser = (DepartmentUser) iter.next();

                        EnterpriseUser user = deptUser.getEnterpriseUser();

                        StringBuffer userDesc = new StringBuffer(
                                ContactManagementConstants.EMPTY_STRING);

                        //MOdified for ESPRD00759221 defect starts
                        if (user.getLastName() != null)
                        {
                            userDesc
									.append(user.getLastName())
									.append(ContactManagementConstants.COMMA_SEPARATOR)
									.append(ContactManagementConstants.SPACE_STRING);

                        }
                        /*if (user.getMiddleName() != null)
                        {
                            userDesc.append(user.getMiddleName()
                                    + ContactManagementConstants.SPACE_STRING);

                        }*/
                        if (user.getFirstName() != null)
                        {
                            userDesc
									.append(user.getFirstName())
									.append(ContactManagementConstants.SPACE_STRING)
									.append(ContactManagementConstants.HYPHEN_SEPARATOR)
									.append(ContactManagementConstants.SPACE_STRING);

                        }
                        if (user.getUserID() != null)
                        {
                            userDesc.append(user.getUserID());

                        }
                        /*if (user.getPrefixName() != null)
                        {
                            userDesc.append(user.getPrefixName()
                                    + ContactManagementConstants.SPACE_STRING);

                        }                        
                                                
                        if (user.getSuffixName() != null)
                        {
                            userDesc.append(user.getSuffixName()+ ContactManagementConstants.SPACE_STRING);

                        }
                        if (user.getUserWorkUnitSK() != null)
                        {
                            userDesc.append(user.getUserWorkUnitSK());
                        }*/
                        if(testSet.add(user.getUserWorkUnitSK().toString()+ userDesc.toString()))
                        {
                        listOfUsers.add(new SelectItem(user.getUserWorkUnitSK()
                                .toString(), userDesc.toString()));
                        }
                        //for ESPRD00759221 ends
                    }

                    caseSearchDataBean.setAssignedTo(listOfUsers);

                }
            }
            catch (Exception exe)
            {
                logger
                        .debug("Exception in onDeptChange--controllerBean");

            }
        }
        else
        {
         getUsersList();
        }

    }

    private List fillLobHierarchyDetails(List listSK, Integer level)
    {
        logger.debug("*****  fillLobHierarchyDetails *******");
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        List list = new ArrayList();
        List lobHierarchyList =null;
        Integer levelNum = Integer.valueOf(0);//new Integer(0); Find_Bugs-FIX
        List deptList = new ArrayList();
        List deptListSK = new ArrayList();
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        try
        {
            if (level.equals(ContactManagementConstants.TWO)
                    || level.equals(ContactManagementConstants.THREE))
            {
                lobHierarchyList = contactMaintenanceDelegate
                        .getLobHierarchyDetails(listSK, null);
                Iterator it1 = lobHierarchyList.iterator();
                while (it1.hasNext())
                {
                    LineOfBusinessHierarchy element = (LineOfBusinessHierarchy) it1
                            .next();
                    if (element.getLobHierarchyLevelNumber().equals(
                            ContactManagementConstants.TWO))
                    {
                        caseSearchDataBean.getReportingUnit().add(
                                new SelectItem(element
                                        .getLineOfBusinessHierarchySK()
                                        .toString(), element
                                        .getLobHierarchyDescription()));
                        levelNum = ContactManagementConstants.THREE;
                        list.add(element.getLineOfBusinessHierarchySK());
                    }
                    else if (element.getLobHierarchyLevelNumber().equals(
                            ContactManagementConstants.THREE))
                    {
                        caseSearchDataBean.getBusinessUnit().add(
                                new SelectItem(element
                                        .getLineOfBusinessHierarchySK()
                                        .toString(), element
                                        .getLobHierarchyDescription()));

                        levelNum = ContactManagementConstants.FOUR;
                        list.add(element.getLineOfBusinessHierarchySK());
                    }
                }
            }
            else if (level.equals(ContactManagementConstants.FOUR))
            {
                lobHierarchyList = contactMaintenanceDelegate
                        .getDepartmentDetails(listSK);
                Iterator it2 = lobHierarchyList.iterator();
                while (it2.hasNext())
                {
                    Department element = (Department) it2.next();
                    caseSearchDataBean.getDepartments().add(
                            new SelectItem(element.getLineOfBusinessHierarchy()
                                    .getLineOfBusinessHierarchySK().toString(),
                                    element.getName()));

                    deptList.add(element.getWorkUnitSK());

                    deptListSK.add(element.getLineOfBusinessHierarchy()
                            .getLineOfBusinessHierarchySK());
                }
                caseSearchDataBean.setMaintainGroups(deptListSK);
            }

            if (levelNum.intValue() != 0)
            {
                deptList = fillLobHierarchyDetails(list, levelNum);
            }
        }
        catch (Exception e)
        {
            logger
                    .debug("Exception fillLobHierarchyDetails--controllerBean");
            e.printStackTrace();
        }

        return deptList;
    }

 // Start - This method is added for the Heap Dump Fix
	/**
	* This method is used to search the case records
	* @return
	*/
    public String searchCaseRecords()
    {
    	caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
    	caseSearchDataBean.setSelectedDeptAll("");
    	
    	caseSearchDataBean.setStartRecord(0);
    	caseSearchDataBean.setStartRecordNo(caseSearchDataBean.getStartRecord());
    	caseSearchDataBean.setCurrentPage(1);
    	caseSearchDataBean.setShowPrevious(false);
    	caseSearchDataBean.setShowNext(false);
    	
    	caseSearchDataBean.setReassignFlag(false);
    	searchCase();
    	return "";
    }
	// End - This method is added for the Heap Dump Fix

    /**
     * This method is used to get the CaseRecordSearchcriteriaVo.
     */
    public String searchCase()
    {
    	validateSuccess = true;
    	caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderResponse = null;
		EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
		
		
		//Added for defect ESPRD00340790
		String searchAuthority = null;
		CaseDelegate caseDelegate = new CaseDelegate();
		//boolean caseFilterStatus=false;
		String userId=getUserID();
		logger.debug("getUserID()::"+userId);
		// Commented for CRM_CASE-GAI-Recursive Call-FIX
		// Start
		// CaseFilterStatus is already getting in CaseDAOImpl
		//try{
		//	caseFilterStatus = caseDelegate.caseFilterStatus(userId);	
		//}catch(Exception e){
		//	e.printStackTrace();
		//}
		//logger.debug("++caseFilterStatus::"+caseFilterStatus);
		// End
		if (eup != null){
			searchAuthority=eup.getCaseFilter();
		} 
		if(eup == null || searchAuthority == null || isNullOrEmptyField(searchAuthority)){
			ContactManagementHelper
			.setErrorMessage(MaintainContactManagementUIConstants.NO_AUTHORITY_ADD_CASE);
			caseSearchDataBean.setShowCaseSearchMsgFlag(true);
		}else {
			int count = 0;
			CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
			caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) caseSearchDataBean
					.getCaseRecordSearchCriteriaVO();
			EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
			CaseRecordSearchResultsVO caseRecordSearchResultsVO = new CaseRecordSearchResultsVO();
			CommonCMCaseDetailsVO commonCMCaseDetailsVO = null;
			String caseSK = null;
			String entityType = null;
			String entityID = null;
			// Begin - Added the property for the defect id ESPRD00686628_29Aug2011
			caseSearchDataBean.setRowIndex(0);
			// End - Added the property for the defect id ESPRD00686628_29Aug2011

			if (validate(caseRecordSearchCriteriaVO,false)) {
				caseRecordSearchCriteriaVO.setUserId(userId);
				int size = 0;
				String entityName = "";
				try {
					
					caseRecordSearchCriteriaVO
							.setCaseSK(caseRecordSearchCriteriaVO
									.getCaseRecordNumber());

					if (StringUtils.isNotBlank(caseRecordSearchCriteriaVO
							.getDepartments())) {
						List listSK = new ArrayList();
						listSK.add(caseRecordSearchCriteriaVO.getDepartments());
						caseRecordSearchCriteriaVO.setMaintainGroups(listSK);
					} else {
						caseRecordSearchCriteriaVO
								.setMaintainGroups(caseSearchDataBean
										.getMaintainGroups());
					}
					caseRecordSearchCriteriaVO.setReassignFlag(false); // Added for the ReassingCaseSearch
					caseRecordSearchCriteriaVO.setStartIndex(caseSearchDataBean.getStartRecord()); // Added for the caseSerch Datascroller
					caseRecordSearchCriteriaVO.setRowsPerPage(caseSearchDataBean.getItemsPerPage()); // Added for the caseSerch Datascroller
					
					enterpriseSearchResultsVO = caseDelegate
							.getCaseRecords(caseRecordSearchCriteriaVO);
					
					int recordCount = (int) enterpriseSearchResultsVO.getRecordCount();
					caseSearchDataBean.setCount(Integer.valueOf(String.valueOf(enterpriseSearchResultsVO.getRecordCount())));
					
					// Begin - Added this code for the Heap Dump Fix
					if (caseSearchDataBean.getStartRecord() == 0) {
						caseSearchDataBean.setStartRecordNo(caseSearchDataBean.getStartRecord() + 1);
						caseSearchDataBean.setCurrentPage(1);
						if(enterpriseSearchResultsVO.getRecordCount() <= caseSearchDataBean.getItemsPerPage())
						{
							caseSearchDataBean.setEndRecord(recordCount);
							caseSearchDataBean.setShowNext(false);
							caseSearchDataBean.setShowPrevious(false);
							caseSearchDataBean.setShowNextOne(false);
						}
						else
						{
							caseSearchDataBean.setEndRecord(caseSearchDataBean.getStartRecord() + caseSearchDataBean.getItemsPerPage());
							caseSearchDataBean.setShowNext(true);
							caseSearchDataBean.setShowPrevious(false);
							caseSearchDataBean.setShowNextOne(false);
						}
					}
					if(caseSearchDataBean.getCurrentPage() == 1 && 
							enterpriseSearchResultsVO.getRecordCount() > 2*caseSearchDataBean.getItemsPerPage()) // Added for the dataScroller Fix
					{
						caseSearchDataBean.setShowNextOne(true);
					}
					// End - Added this code for the Heap Dump Fix
					
					if (enterpriseSearchResultsVO.getSearchResults().size()==0) {
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
								new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
								"LOGCASESEARCHERRMESGIDHID");
						// Added for the Defect Id : ESPRD00722628
						caseSearchDataBean.setCaseAsctErrormsg(true);
					}
					
				} catch (CaseRecordFetchBusinessException e) {
					/** If logged in user doesn't have permission to view/edit case record
					 *  WAS layer will be throwing CaseRecordFetchBusinessException exception with
					 *  'err.pgm.cm.no.authority.add.case' error code.
					 * */
					if(ContactManagementConstants.ERR_PGM_CM_NO_AUTHORITY_ADD_CASE.equals(e.getErrorCode())){
						ContactManagementHelper
						.setErrorMessage(MaintainContactManagementUIConstants.NO_AUTHORITY_ADD_CASE);
						caseSearchDataBean.setShowCaseSearchMsgFlag(true);
					}else{
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
					}
					logger.error(e.getMessage(), e);
				} finally {
				
				}
				if (enterpriseSearchResultsVO.getSearchResults() != null) {
					caseSearchDataBean
							.setSearchCaseList(enterpriseSearchResultsVO
									.getSearchResults());
					size = enterpriseSearchResultsVO.getSearchResults().size();
				}

				if (size > 0) {
					List list = enterpriseSearchResultsVO.getSearchResults();
					Map entityMap = caseSearchDataBean.getEntityTypeMap(); // Added for displaying the entityType. - Performance_Fix_UC-PGM-CRM-073_25OCT2011
					if (enterpriseSearchResultsVO.getRecordCount() == 1) {
						logger.debug("CaseSearch results list size is $$ "
								+ list.size());
						CaseRecordSearchResultsVO searchResultsVO = (CaseRecordSearchResultsVO) list
								.get(0);
						//----------

						caseSearchDataBean.setShowResults(true);
						

						if (searchResultsVO.getEntityName() != null
								&& !searchResultsVO
										.getEntityName().equals("")) {
							entityName = searchResultsVO
									.getEntityName();
						} else {
							if (searchResultsVO
									.getEntityFirstName() != null
									&& !searchResultsVO
											.getEntityFirstName()
											.equals("")) {
								entityName = searchResultsVO
										.getEntityFirstName().trim();
							}
							if (searchResultsVO
									.getEntityMidName() != null
									&& !searchResultsVO
											.getEntityMidName().equals("")) {
								entityName = entityName
										+ " "
										+ searchResultsVO
												.getEntityMidName().trim();
							}
							if (searchResultsVO
									.getEntityLastName() != null
									&& !searchResultsVO
											.getEntityLastName().equals("")) {
								entityName = entityName
										+ " "
										+ searchResultsVO
												.getEntityLastName().trim();
							}
						}
						
						if((entityName == null || entityName.equals("")) 
								&& (searchResultsVO.getEntityOrgName() != null && !searchResultsVO.getEntityOrgName().equalsIgnoreCase("")))
						{
							entityName = searchResultsVO.getEntityOrgName();
						}
					
						searchResultsVO.setEntityName(entityName
								.trim());

						if (searchResultsVO.getEntityType() != null
								&& !searchResultsVO
										.getEntityType().equalsIgnoreCase(
												"")) {
							// Begin - Commented for the performance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011					
							/*String entityTypeDesc = ReferenceServiceDelegate
							.getReferenceSearchShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
									new Long(80), searchResultsVO
									.getEntityType());
							
							searchResultsVO
									.setEntityTypeDesc(entityTypeDesc);
							searchResultsVO
									.setEntityTypeDesc(entityTypeDesc);*/
							// End - Commented for the performance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
							// Begin - Added for the Performance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
							if(entityMap.get(searchResultsVO.getEntityType()) != null)
							{
								entityType=entityMap.get(searchResultsVO
												.getEntityType()).toString();
							}
							else
							{
								entityType=searchResultsVO.getEntityType();
							}
							// End - Added for the Performance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
						}

						if (searchResultsVO.getStatus() != null
								&& !searchResultsVO.getStatus()
										.equalsIgnoreCase("")) {
							// Begin - Modified for the performance fix.
							/*String status = ContactManagementHelper
									.setShortDescription(
											FunctionalAreaConstants.GENERAL,
											ReferenceServiceDataConstants.G_CASE_STAT_CD,
											searchResultsVO
													.getStatus());*/
							String status = getDescriptionFromVV(searchResultsVO.getStatus(),
									caseSearchDataBean.getStatus());
							// End - Modified for the performance fix.
							searchResultsVO.setStatus(status);
						}

						if (searchResultsVO.getPriority() != null
								&& !searchResultsVO.getPriority()
										.equalsIgnoreCase("")) {
							// Begin - Modified for the performance fix.
							/*String priority = ContactManagementHelper
									.setShortDescription(
											FunctionalAreaConstants.GENERAL,
											ReferenceServiceDataConstants.G_CASE_PRTY_CD,
											searchResultsVO
													.getPriority());*/
							String priority = getDescriptionFromVV(searchResultsVO.getPriority(),
									caseSearchDataBean.getPriority());
							// End - Modified for the performance fix.
							searchResultsVO.setPriority(priority);
						}

						if (searchResultsVO.getLob() != null
								&& !searchResultsVO.getLob()
										.equalsIgnoreCase("")) {
							// Begin - Modified for the performance fix.
							/*String lob = ContactManagementHelper
									.setShortDescription(
											FunctionalAreaConstants.REFERENCE,
											ReferenceServiceDataConstants.R_LOB_CD,
											searchResultsVO
													.getLob());*/
							
							String lob = getDescriptionFromVV(searchResultsVO.getLob(),
									caseSearchDataBean.getLobVVList());
							// End - Modified for the performance fix.
							searchResultsVO.setLob(lob);
						}
						
						
						//---------
						commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
						if (searchResultsVO.getCaseSK() != null
								&& !searchResultsVO.getCaseSK().equals("")) {
							caseSK = searchResultsVO.getCaseSK().toString();
						}
						entityType = searchResultsVO.getEntityType();
						if (searchResultsVO.getCommonEntitySK() != null
								&& !searchResultsVO.getCommonEntitySK().equals(
										"")) {
							entityID = searchResultsVO.getCommonEntitySK()
									.toString();
						}
						caseSearchDataBean.setShowResults(false);
						commonCMCaseDetailsVO.setCaseSK(caseSK);
						commonCMCaseDetailsVO.setEntityType(entityType);
						commonCMCaseDetailsVO.setEntityID(entityID);
						// commonCMCaseDetailsVO.setName(entityName);
						commonCMCaseDetailsVO.setActionCode("CaseUpdate");
						
						//UC-PGM-CRM-018_ESPRD00392654_25JAN10
						if(caseSearchDataBean.isControlCameFromCMLogCase()){		
							logger.debug(" caseDataBean.isShowtempList "  );
							caseSearchDataBean.getShowCaseTempList().clear();
							caseSearchDataBean.getShowCaseTempList().add(searchResultsVO);	
							logger.debug(" caseDataBean.getShowCaseTempList "  + caseSearchDataBean.getShowCaseTempList().size());							
						}else{
						showCasePortlet(commonCMCaseDetailsVO);
						}
						//showCasePortlet(commonCMCaseDetailsVO);
						//end
						
						caseSearchDataBean.setNoData(false); // Added for the defect id ESPRD00703586_03OCT2011
						//Modified for the Defect_ESPRD00792984
						caseSearchDataBean.getSearchCaseList().clear();
						
						
						
					} else {
						while (count < list.size()) {
							caseSearchDataBean.setShowResults(true);
							caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) list
									.get(count);

							if (caseRecordSearchResultsVO.getEntityName() != null
									&& !caseRecordSearchResultsVO
											.getEntityName().equals("")) {
								entityName = caseRecordSearchResultsVO
										.getEntityName();
							} else {
								if (caseRecordSearchResultsVO
										.getEntityFirstName() != null
										&& !caseRecordSearchResultsVO
												.getEntityFirstName()
												.equals("")) {
									entityName = caseRecordSearchResultsVO
											.getEntityFirstName().trim();
								}
								if (caseRecordSearchResultsVO
										.getEntityMidName() != null
										&& !caseRecordSearchResultsVO
												.getEntityMidName().equals("")) {
									entityName = entityName
											+ " "
											+ caseRecordSearchResultsVO
													.getEntityMidName().trim();
								}
								if (caseRecordSearchResultsVO
										.getEntityLastName() != null
										&& !caseRecordSearchResultsVO
												.getEntityLastName().equals("")) {
									entityName = entityName
											+ " "
											+ caseRecordSearchResultsVO
													.getEntityLastName().trim();
								}
							}
							 /*ESPRD00538044 Start */
							if((entityName == null || entityName.equals("")) 
									&& (caseRecordSearchResultsVO.getEntityOrgName() != null && !caseRecordSearchResultsVO.getEntityOrgName().equalsIgnoreCase("")))
							{
								entityName = caseRecordSearchResultsVO.getEntityOrgName();
							}
							 /*ESPRD00538044 End */
							caseRecordSearchResultsVO.setEntityName(entityName
									.trim());

							if (caseRecordSearchResultsVO.getEntityType() != null
									&& !caseRecordSearchResultsVO
											.getEntityType().equalsIgnoreCase(
													"")) {
								//Modified for defect ESPRD00514360 starts
								/*String entityTypeDesc = ContactManagementHelper
										.setShortDescription(
												FunctionalAreaConstants.CONTACT_MGMT,
												ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
												caseRecordSearchResultsVO
														.getEntityType());*/
								//Start Commonted for the performance issue Performance_Fix_UC-PGM-CRM-073_15NOV2011
								/*String entityTypeDesc = ReferenceServiceDelegate
								.getReferenceSearchShortDescription(
										FunctionalAreaConstants.GENERAL,
										ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
										new Long(80), caseRecordSearchResultsVO
										.getEntityType());*/
								//End Commonted for the performance issue Performance_Fix_UC-PGM-CRM-073_15NOV2011
								//Start Added for the performance issue Performance_Fix_UC-PGM-CRM-073_15NOV2011
								if(caseRecordSearchResultsVO.getEntityType()!=null){
									if(entityMap.get(caseRecordSearchResultsVO.getEntityType()) != null)
									{
										entityType=entityMap.get(caseRecordSearchResultsVO
														.getEntityType()).toString();
										caseRecordSearchResultsVO.setEntityTypeDesc(entityType);
									}
									else
									{
										entityType=caseRecordSearchResultsVO.getEntityType();
										caseRecordSearchResultsVO.setEntityTypeDesc(entityType);
									}
								}
								

							}
							//End Added for the performance issue Performance_Fix_UC-PGM-CRM-073_15NOV2011

							if (caseRecordSearchResultsVO.getStatus() != null
									&& !caseRecordSearchResultsVO.getStatus()
											.equalsIgnoreCase("")) {
								// Begin - Modified for the performance fix.
								/*String status = ContactManagementHelper
										.setShortDescription(
												FunctionalAreaConstants.GENERAL,
												ReferenceServiceDataConstants.G_CASE_STAT_CD,
												caseRecordSearchResultsVO
														.getStatus());*/
								String status = getDescriptionFromVV(caseRecordSearchResultsVO.getStatus(),
										caseSearchDataBean.getStatus());
								// End - Modified for the performance fix.
								caseRecordSearchResultsVO.setStatus(status);
							}

							if (caseRecordSearchResultsVO.getPriority() != null
									&& !caseRecordSearchResultsVO.getPriority()
											.equalsIgnoreCase("")) {
								// Begin - Modified for the performance fix.
								/*String priority = ContactManagementHelper
										.setShortDescription(
												FunctionalAreaConstants.GENERAL,
												ReferenceServiceDataConstants.G_CASE_PRTY_CD,
												caseRecordSearchResultsVO
														.getPriority());*/
								String priority = getDescriptionFromVV(caseRecordSearchResultsVO.getPriority(),
													caseSearchDataBean.getPriority());
								// End - Modified for the performance fix.
								caseRecordSearchResultsVO.setPriority(priority);
							}

							if (caseRecordSearchResultsVO.getLob() != null
									&& !caseRecordSearchResultsVO.getLob()
											.equalsIgnoreCase("")) {
								// Begin - Modified for the performance fix.
								/*String lob = ContactManagementHelper
										.setShortDescription(
												FunctionalAreaConstants.REFERENCE,
												ReferenceServiceDataConstants.R_LOB_CD,
												caseRecordSearchResultsVO
														.getLob());*/
								String lob = getDescriptionFromVV(caseRecordSearchResultsVO.getLob(),
												caseSearchDataBean.getLobVVList());
								// End - Modified for the performance fix.
								caseRecordSearchResultsVO.setLob(lob);
							}
							count++;
						}
						caseSearchDataBean.setNoData(false);
						caseSearchDataBean.setNoDataFlag(true);
						//caseSearchDataBean.setShowCaseSearchMsgFlag(false); // Commented for the defect id ESPRD00736670_21DEC2011
					}
					//Modified for defect ESPRD00672864		
					//caseSearchDataBean.setShowData(true);
					// Added for the Defect Id : ESPRD00722628
					caseSearchDataBean.setCaseAsctErrormsg(false);
				} 
				//Modified for the Defect_ESPRD00792984
				size =  (int) enterpriseSearchResultsVO.getRecordCount();
				if(size==1){
					caseSearchDataBean.setShowData(false);
				}
				if(size>1){
					caseSearchDataBean.setShowData(true);
				}
				
				else {
					caseSearchDataBean.setNoDataFlag(false);
					//Modified for defect ESPRD00672864
					caseSearchDataBean.setNoData(true);
					//caseSearchDataBean.setShowCaseSearchMsgFlag(false); // Commented for the defect id ESPRD00736670_21DEC2011
					//Modified for defect ESPRD00672864
					caseSearchDataBean.setShowData(false);
				}
			} else {
				//caseSearchDataBean.setShowCaseSearchMsgFlag(true); // Commented for the defect id ESPRD00736670_21DEC2011
				//Modified for the Defect_ESPRD00792984
				caseSearchDataBean.getSearchCaseList().clear();
				caseSearchDataBean.setShowData(false);
				
			}
		}
		return "";
	}

    /**
	 * This method is used to get the CaseRecordSearchcriteriaVo.
	 */
    public String searchCaseFromLogCase()
    {
        int count = 0;
        CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) caseSearchDataBean
                .getCaseRecordSearchCriteriaVO();
        EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
        CaseRecordSearchResultsVO caseRecordSearchResultsVO = new CaseRecordSearchResultsVO();
        CaseDelegate caseDelegate = new CaseDelegate();
        String entityName = "";
        int size = 0;

        if (validate(caseRecordSearchCriteriaVO,false))
        {
            try
            {
             
                if (caseRecordSearchCriteriaVO.getCaseRecordNumber() != null
                        && !caseRecordSearchCriteriaVO.getCaseRecordNumber()
                                .equalsIgnoreCase(""))
                {
                    caseRecordSearchCriteriaVO
                            .setCaseSK(caseRecordSearchCriteriaVO
                                    .getCaseRecordNumber());
                }
                enterpriseSearchResultsVO = caseDelegate
                        .getCaseRecords(caseRecordSearchCriteriaVO);

                if (enterpriseSearchResultsVO.getSearchResults() != null)
                {
                    caseSearchDataBean
                            .setSearchCaseList(enterpriseSearchResultsVO
                                    .getSearchResults());
                    size = enterpriseSearchResultsVO.getSearchResults().size();

                    if (size > 0)
                    {
                        List list = enterpriseSearchResultsVO
                                .getSearchResults();
                        while (count < list.size())
                        {
                            caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) list
                                    .get(count);

                            if (caseRecordSearchResultsVO.getEntityName() != null
                                    && !caseRecordSearchResultsVO
                                            .getEntityName().equals(""))
                            {
                                entityName = caseRecordSearchResultsVO
                                        .getEntityName();
                            }
                            else
                            {
                                if (caseRecordSearchResultsVO
                                        .getEntityFirstName() != null
                                        && !caseRecordSearchResultsVO
                                                .getEntityFirstName()
                                                .equals(""))
                                {
                                    entityName = caseRecordSearchResultsVO
                                            .getEntityFirstName().trim();
                                }
                                if (caseRecordSearchResultsVO
                                        .getEntityMidName() != null
                                        && !caseRecordSearchResultsVO
                                                .getEntityMidName().equals(""))
                                {
                                    entityName = entityName
                                            + " "
                                            + caseRecordSearchResultsVO
                                                    .getEntityMidName().trim();
                                }
                                if (caseRecordSearchResultsVO
                                        .getEntityLastName() != null
                                        && !caseRecordSearchResultsVO
                                                .getEntityLastName().equals(""))
                                {
                                    entityName = entityName
                                            + " "
                                            + caseRecordSearchResultsVO
                                                    .getEntityLastName().trim();
                                }
                            }
                            count++;
                        }
                        caseSearchDataBean.setNoData(true);
                        caseSearchDataBean.setNoDataFlag(true);
                        //caseSearchDataBean.setShowCaseSearchMsgFlag(false); // Commented for the defect id ESPRD00736670_21DEC2011
                    }
                    else
                    {
                        caseSearchDataBean.setNoDataFlag(false);
                        caseSearchDataBean.setNoData(false);
                        //caseSearchDataBean.setShowCaseSearchMsgFlag(false); // Commented for the defect id ESPRD00736670_21DEC2011
                    }
                }
            }
            catch (CaseRecordFetchBusinessException e)
            {
            	e.printStackTrace();
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
                logger.error(e.getMessage(), e);
            }catch (Exception e){
            	e.printStackTrace();
            }
            finally
            {
               
            }
        }
        else
        {
            //caseSearchDataBean.setShowCaseSearchMsgFlag(true);  // Commented for the defect id ESPRD00736670_21DEC2011
        }
        return "";
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
            String messageBundle, String componentId)
    {
        logger.debug("********** setErrorMessage ***********");

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
            logger.debug("Component ID " + componentId);

            UIComponent uiComponent = ContactManagementHelper
                    .findComponentInRoot(componentId);
            //validating for Component ID not present in View Root
            if(uiComponent!=null){
            clientId = uiComponent.getClientId(facesContext);
            }
            logger.debug("Client Id " + clientId);
        }

        facesContext.addMessage(clientId, fc);
    }


    public void resetSerachCase()
    {
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        caseSearchDataBean
                .setCaseRecordSearchCriteriaVO(new CaseRecordSearchCriteriaVO());
        PortletSession portletSession = (PortletSession) FacesContext
		.getCurrentInstance().getExternalContext().getSession(false);
        portletSession.setAttribute("CaseRecordSearchCriteriaVO", null);
        caseSearchDataBean.setAdditionalEntityIDTypeList(new ArrayList());
        caseSearchDataBean.setEntityIDTypeList(new ArrayList());
        caseSearchDataBean.setRepUnintFlag(true);
        caseSearchDataBean.setBusUnintFlag(true);
        caseSearchDataBean.setDepartmentsFlag(true);
        caseSearchDataBean.setSearchCaseList(null);
        caseSearchDataBean.setNoData(false);
        //added for defect ESPRD00672864
        caseSearchDataBean.setShowData(false);

		//Start - Added this block for resetting the list, currentPage, start and end records
        caseSearchDataBean.setSearchCaseList(new ArrayList());
        caseSearchDataBean.setShowReassignResultsPage(false);
        caseSearchDataBean.setReassignFlag(false);
        caseSearchDataBean.setStartRecord(0);
        caseSearchDataBean.setEndRecord(caseSearchDataBean.getStartRecord()+caseSearchDataBean.getItemsPerPage());
        caseSearchDataBean.setCurrentPage(1);
        caseSearchDataBean.setSuccess(false);
		//for reset should load default values.
        getUsersList();
		//End - Added this block for resetting the list, currentPage, start and end records
        //to get all default values for business unit, reporting unit and work unit.
        getAllHierarchies();
    }

    /**
     * This operation is used to validate the user values
     * 
     * @param caseRecordSearchCriteriaVO :
     *            CaseRecordSearchCriteriaVO object.
     * @return boolean
     */
    public boolean validate(
            CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO,boolean isFromReassignCase)
    {
        boolean flag = true;
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        ContactManagementHelper helper = new ContactManagementHelper();
        if ((caseRecordSearchCriteriaVO.getEntityId() == null || caseRecordSearchCriteriaVO
                .getEntityId().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getAssignedTo() == null || caseRecordSearchCriteriaVO
                        .getAssignedTo().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getStatus() == null || caseRecordSearchCriteriaVO
                        .getStatus().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getCreatedBy() == null || caseRecordSearchCriteriaVO
                        .getCreatedBy().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getEntityType() == null || caseRecordSearchCriteriaVO
                        .getEntityType().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getCaseRecordNumber() == null || caseRecordSearchCriteriaVO
                        .getCaseRecordNumber().trim().equals(""))
                && (caseRecordSearchCriteriaVO.getStatusDate() == null || caseRecordSearchCriteriaVO
                        .getStatusDate().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getPriority() == null || caseRecordSearchCriteriaVO
                        .getPriority().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getCaseTitle() == null || caseRecordSearchCriteriaVO
                        .getCaseTitle().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getCreatedFrom() == null || caseRecordSearchCriteriaVO
                        .getCreatedFrom().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getAdvCreatedBy() == null || caseRecordSearchCriteriaVO
                        .getAdvCreatedBy().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getLineOfBusinessGroup() == null || caseRecordSearchCriteriaVO
                        .getLineOfBusinessGroup().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getCreatedTo() == null || caseRecordSearchCriteriaVO
                        .getCreatedTo().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getCaseTypeSK() == null || caseRecordSearchCriteriaVO
                        .getCaseTypeSK().equals(Long.valueOf(0)))  /* FIND BUGS_FIX */
                && (caseRecordSearchCriteriaVO.getReportingUnit() == null || caseRecordSearchCriteriaVO
                        .getReportingUnit().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getBusinessUnit() == null || caseRecordSearchCriteriaVO
                        .getBusinessUnit().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getDepartments() == null || caseRecordSearchCriteriaVO
                        .getDepartments().trim().equalsIgnoreCase(""))
                && (caseRecordSearchCriteriaVO.getResponseExists() == null || caseRecordSearchCriteriaVO
                        .getResponseExists().equalsIgnoreCase("")) 
				&& (caseRecordSearchCriteriaVO.getAdditionalEntityID() == null || 
						caseRecordSearchCriteriaVO.getAdditionalEntityID().trim().equalsIgnoreCase("")) 
                && (caseRecordSearchCriteriaVO.getAdditionalEntityIDType() == null || 
                		caseRecordSearchCriteriaVO.getAdditionalEntityIDType().trim().equalsIgnoreCase(""))
				&& (caseRecordSearchCriteriaVO.getAdditionalEntityType() ==null || 
						caseRecordSearchCriteriaVO.getAdditionalEntityType().trim().equalsIgnoreCase("")))
						
        {
            ContactManagementHelper
                    .setErrorMessage(MaintainContactManagementUIConstants.SELECT_COLUMN,"LOGCASESEARCHERRMESGIDHID", "LOGCASESEARCHERRMESGIDHID", null);
            caseSearchDataBean.setShowCaseSearchMsgFlag(true);
            flag = false;
        }
        else
        {
            String entityID = null;
            String entityType = null;
            
            //DEFECT : ESPRD00332708
            String entityIDType=null;

			//ESPRD00336172_FIX_08102009

			String addentityType = null;

			String addentityID = null;
			String addentityIDType = null;
			
			

            if (StringUtils
                    .isNotEmpty(caseRecordSearchCriteriaVO.getEntityId()))
            {
                entityID = caseRecordSearchCriteriaVO.getEntityId().trim();
            }
            if (StringUtils.isNotEmpty(caseRecordSearchCriteriaVO
                    .getEntityType()))
            {
                entityType = caseRecordSearchCriteriaVO.getEntityType().trim();
            }
            
            //DEFECT : ESPRD00332708
            if (StringUtils.isNotEmpty(caseRecordSearchCriteriaVO
                    .getEntityIDType()))
            {
            	entityIDType = caseRecordSearchCriteriaVO.getEntityIDType().trim();
            }
            
			//ESPRD00336172_FIX_08102009

			if (StringUtils.isNotEmpty(caseRecordSearchCriteriaVO
					.getAdditionalEntityType())) {
				addentityType = caseRecordSearchCriteriaVO
						.getAdditionalEntityType().trim();
			}

			if (StringUtils.isNotEmpty(caseRecordSearchCriteriaVO
					.getAdditionalEntityID())) {
				addentityID = caseRecordSearchCriteriaVO
						.getAdditionalEntityID().trim();
			}

			if (StringUtils.isNotEmpty(caseRecordSearchCriteriaVO
					.getAdditionalEntityIDType())) {
				addentityIDType = caseRecordSearchCriteriaVO
						.getAdditionalEntityIDType().trim();
			}
            
            String crNumber = caseRecordSearchCriteriaVO.getCaseRecordNumber();
            String caseTitle = caseRecordSearchCriteriaVO.getCaseTitle();
            String statuDate = caseRecordSearchCriteriaVO.getStatusDate();
            String createdFromDate = caseRecordSearchCriteriaVO
                    .getCreatedFrom();
            String createdToDate = caseRecordSearchCriteriaVO.getCreatedTo();
            caseSearchDataBean.setShowCaseSearchMsgFlag(false);
            String actionCode = null;
            if ((caseRecordSearchCriteriaVO.getCreatedFrom() != null && !caseRecordSearchCriteriaVO
                    .getCreatedFrom().equalsIgnoreCase(""))
                    || (caseRecordSearchCriteriaVO.getAdvCreatedBy() != null && !caseRecordSearchCriteriaVO
                            .getAdvCreatedBy().equalsIgnoreCase(""))
                    || (caseRecordSearchCriteriaVO.getLineOfBusinessGroup() != null && !caseRecordSearchCriteriaVO
                            .getLineOfBusinessGroup().equalsIgnoreCase(""))
                    || (caseRecordSearchCriteriaVO.getCreatedTo() != null && !caseRecordSearchCriteriaVO
                            .getCreatedTo().equalsIgnoreCase(""))
                    || (caseRecordSearchCriteriaVO.getCaseTypeSK() != null && !caseRecordSearchCriteriaVO
                            .getCaseTypeSK().equals(Long.valueOf(0)))  /*FIND BUGS_FIX*/
                    || (caseRecordSearchCriteriaVO.getReportingUnit() != null && !caseRecordSearchCriteriaVO
                            .getReportingUnit().equalsIgnoreCase(""))
                    || (caseRecordSearchCriteriaVO.getBusinessUnit() != null && !caseRecordSearchCriteriaVO
                            .getBusinessUnit().equalsIgnoreCase(""))
                    || (caseRecordSearchCriteriaVO.getDepartments() != null && !caseRecordSearchCriteriaVO
                            .getDepartments().equalsIgnoreCase(""))
                    || (caseRecordSearchCriteriaVO.getResponseExists() != null && !caseRecordSearchCriteriaVO
                            .getResponseExists().equalsIgnoreCase("")))
            {
                caseRecordSearchCriteriaVO.setAdvanceSearch(true);
            }

            if (entityID != null && !entityID.equalsIgnoreCase("")) {
				if (entityType == null || entityType.equalsIgnoreCase("")) {
					flag = false;
					// UC-PGM-CRM-020_ESPRD00433491_12MAR2010
					/*
					 * ContactManagementHelper .setErrorMessage(
					 * MaintainContactManagementUIConstants.ENTITY_TYPE_REQ,
					 * MaintainContactManagementUIConstants.ENTITY_TYPE,
					 * MaintainContactManagementUIConstants.ENTITY_TYPE,
					 * actionCode);
					 */
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_TYPE_REQ,
									MaintainContactManagementUIConstants.ENTITY_TYPE,
									null, actionCode);
					// EOF UC-PGM-CRM-020_ESPRD00433491_12MAR2010
				}
				// for ESPRD00740424
				if(!isFromReassignCase){
				if (entityIDType != null
						&& (entityIDType.equalsIgnoreCase("SY")
								|| entityIDType.equalsIgnoreCase("TJ")
								|| entityIDType.equalsIgnoreCase("XX")
								|| entityIDType.equalsIgnoreCase("TP")
								|| entityIDType.equalsIgnoreCase("MCR")
								|| entityIDType.equalsIgnoreCase("MID") || entityIDType
								.equalsIgnoreCase("RID"))) {
					if (!EnterpriseCommonValidator
							.validateAlphaNumeric(entityID)) {
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.CASE_NUMBER_REQ,
										MaintainContactManagementUIConstants.ADD_ENTITY_ID,
										MaintainContactManagementUIConstants.EDIT_ENTITY_ID,
										actionCode);
						flag = false;
					}
				} else if (!EnterpriseCommonValidator.validateNumeric(entityID)) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.ENTITY_ID_VAL,
									MaintainContactManagementUIConstants.ADD_ENTITY_ID,
									MaintainContactManagementUIConstants.EDIT_ENTITY_ID,
									actionCode);

				} 
            }else 
            {	if ((entityType!=null && entityType.equals("TD"))
						&& !EnterpriseCommonValidator
								.validateAlphaNumeric(entityID)) {
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.CASE_NUMBER_REQ,
									MaintainContactManagementUIConstants.ADD_ENTITY_ID,
									MaintainContactManagementUIConstants.EDIT_ENTITY_ID,
									actionCode);
					flag = false;
				}else{
					//for defect ESPRD00763743
					if (entityType!=null && !entityType.equals("TD") && !EnterpriseCommonValidator.validateNumeric(entityID)) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.ENTITY_ID_VAL,
										MaintainContactManagementUIConstants.ADD_ENTITY_ID,
										MaintainContactManagementUIConstants.EDIT_ENTITY_ID,
										actionCode);

					} 
				}
				// for ESPRD00740424
			}
            }

            if (entityType != null && !entityType.equalsIgnoreCase(""))
            {
                if (entityID == null || entityID.equalsIgnoreCase(""))
                {
                    flag = false;
                    //modified for UC-PGM-CRM-020_ESPRD00433491_12MAR2010
                    /* ContactManagementHelper
                    .setErrorMessage(
                            MaintainContactManagementUIConstants.ENTITY_ID_REQ,
                            MaintainContactManagementUIConstants.ADD_ENTITY_ID,
                            MaintainContactManagementUIConstants.EDIT_ENTITY_ID,
                            actionCode);*/
                    ContactManagementHelper.setErrorMessage(
                    		MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_ID_REQ,
							MaintainContactManagementUIConstants.ADD_ENTITY_ID,null,
							actionCode);
            //EOF UC-PGM-CRM-020_ESPRD00433491_12MAR2010
                }
                
                //DEFECT : ESPRD00332708
             
                
                
                if( validateSuccess)
                {
                
               if (entityIDType == null || entityIDType.equalsIgnoreCase(""))
                {
                	//logger.debug("entityIDType == null  " );
                	flag = false;
                	//modified for UC-PGM-CRM-020_ESPRD00433491_12MAR2010
                    /*ContactManagementHelper
                            .setErrorMessage(
                                    MaintainContactManagementUIConstants.ENTITY_IDTYPE_REQ,
                                    MaintainContactManagementUIConstants.ADD_ENTITY_ID,
                                    MaintainContactManagementUIConstants.EDIT_ENTITY_ID,
                                    actionCode);*/
                	ContactManagementHelper.setErrorMessage(
                    		MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_ID_TYPE_REQ,
                            MaintainContactManagementUIConstants.ENTITY_ID_TYPE_UI_MSG_ID,null,actionCode);
                	//EOF UC-PGM-CRM-020_ESPRD00433491_12MAR2010
                }
               }
            }
			
			//ESPRD00336172_FIX_08102009
			//for ESPRD00740424
			if (addentityID != null && !addentityID.equalsIgnoreCase("")) {

				if (addentityIDType != null //for ESPRD00740424
						&& (addentityIDType.equalsIgnoreCase("SY")
								|| addentityIDType.equalsIgnoreCase("TJ")
								|| addentityIDType.equalsIgnoreCase("XX")
								|| addentityIDType.equalsIgnoreCase("TP")
								|| addentityIDType.equalsIgnoreCase("MCR")
								|| addentityIDType.equalsIgnoreCase("MID") || addentityIDType
								.equalsIgnoreCase("RID"))) {
					if (!EnterpriseCommonValidator
							.validateAlphaNumeric(addentityID)) {
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.CASE_NUMBER_REQ,
										MaintainContactManagementUIConstants.ENTITY_ID_UI_MSG_ID,
										null, actionCode);
						flag = false;
					}
				} else if (!EnterpriseCommonValidator
						.validateNumeric(addentityID)) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.ENTITY_ID_VAL,
									MaintainContactManagementUIConstants.ENTITY_ID_UI_MSG_ID,
									null, actionCode);

				}//for ESPRD00740424

				if (addentityType == null || addentityType.equalsIgnoreCase("")) {

					flag = false;

					// modified for UC-PGM-CRM-020_ESPRD00433491_12MAR2010
					/*
					 * ContactManagementHelper .setErrorMessage(
					 * MaintainContactManagementUIConstants.ENTITY_TYPE_REQ,
					 * MaintainContactManagementUIConstants.ENTITY_TYPE,
					 * MaintainContactManagementUIConstants.ENTITY_TYPE,
					 * actionCode);
					 */
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_TYPE_REQ,
									MaintainContactManagementUIConstants.ADD_CASE_SEARCH_ENTITY_TYPE_ID,
									null, actionCode);

					// EOF UC-PGM-CRM-020_ESPRD00433491_12MAR2010

				}
			}

			if (addentityType != null && !addentityType.equalsIgnoreCase("")) {
				
			if (addentityID == null || addentityID.equalsIgnoreCase("")) {
					flag = false;
				//modfified for UC-PGM-CRM-020_ESPRD00433491_12MAR2010
					/*ContactManagementHelper
							.setErrorMessage(
								MaintainContactManagementUIConstants.ENTITY_ID_REQ,
								MaintainContactManagementUIConstants.ADD_ENTITY_ID,
								MaintainContactManagementUIConstants.EDIT_ENTITY_ID,
								actionCode);*/
					ContactManagementHelper.setErrorMessage(
									MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_ID_REQ,
								MaintainContactManagementUIConstants.ENTITY_ID_UI_MSG_ID,null,actionCode);
					//Eof UC-PGM-CRM-020_ESPRD00433491_12MAR2010
			}
				//DEFECT : ESPRD00332708

			
    if(validateSuccess)
    {
			
				if (addentityIDType == null || addentityIDType.equalsIgnoreCase("")) {
					
				flag = false;
				//modified for UC-PGM-CRM-020_ESPRD00433491_12MAR2010
				/*ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.ENTITY_IDTYPE_REQ,
								MaintainContactManagementUIConstants.ADD_ENTITY_ID,
								MaintainContactManagementUIConstants.EDIT_ENTITY_ID,
								actionCode);*/
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_ID_TYPE_REQ,
						MaintainContactManagementUIConstants.ENTITY_ID_TYPE_UI_MSG_ID_2,null,actionCode);
						
				//EOF UC-PGM-CRM-020_ESPRD00433491_12MAR2010
			}
    }
			}
			
            if (crNumber != null && !crNumber.equals(""))
            {
                if (!EnterpriseCommonValidator.validateNumeric(crNumber))
                {
                    flag = false;
                    ContactManagementHelper
                            .setErrorMessage(
                                    MaintainContactManagementUIConstants.CASE_NUMBER_REQ,
                                    MaintainContactManagementUIConstants.ADD_CASE_NUMBER,
                                    MaintainContactManagementUIConstants.EDIT_CASE_NUMBER,
                                    actionCode);
                }
            }

            if (caseTitle != null && !caseTitle.equals(""))
            {
                if (!ContactManagementHelper.validateAlphaNumeric(caseTitle))
                {
                    flag = false;
                    ContactManagementHelper
                            .setErrorMessage(
                                    MaintainContactManagementUIConstants.CASE_TITLE_VAL,
                                    MaintainContactManagementUIConstants.CASE_TITLE,
                                    MaintainContactManagementUIConstants.CASE_TITLE,
                                    actionCode);
                }

            }

            if (statuDate != null && !statuDate.equalsIgnoreCase(""))
            {
                if (!EnterpriseCommonValidator.validateDate(statuDate))
                {
                    flag = false;
                    ContactManagementHelper
                            .setErrorMessage(
                                    MaintainContactManagementUIConstants.STATUS_DATE_VAL,
                                    MaintainContactManagementUIConstants.STATUS_DATE,
                                    MaintainContactManagementUIConstants.STATUS_DATE,
                                    actionCode);
                }
            }

            if (createdFromDate != null
                    && !createdFromDate.equalsIgnoreCase(""))
            {
                if (!EnterpriseCommonValidator.validateDate(createdFromDate))
                {
                    flag = false;
                    ContactManagementHelper.setErrorMessage(
                            MaintainContactManagementUIConstants.FROM_DATE_VAL,
                            MaintainContactManagementUIConstants.FROM_DATE,
                            MaintainContactManagementUIConstants.FROM_DATE,
                            actionCode);
                }
                else if (statuDate != null && !statuDate.equalsIgnoreCase(""))
                {
                    Date statDate = helper.dateConverter(statuDate);
                    Date fromDate = helper.dateConverter(createdFromDate);
                    if (!EnterpriseCommonValidator.compareGreaterDate(statDate,
                            fromDate))
                    {
                        flag = false;
                        ContactManagementHelper
                                .setErrorMessage(
                                        MaintainContactManagementUIConstants.STATUS_FRDATE_COM,
                                        MaintainContactManagementUIConstants.STATUS_DATE,
                                        MaintainContactManagementUIConstants.STATUS_DATE,
                                        actionCode);
                    }
                }
            }

            if (createdToDate != null && !createdToDate.equalsIgnoreCase(""))
            {
                if (!EnterpriseCommonValidator.validateDate(createdToDate))
                {
                    flag = false;
                    ContactManagementHelper.setErrorMessage(
                            MaintainContactManagementUIConstants.TO_DATE_VAL,
                            MaintainContactManagementUIConstants.TO_DATE,
                            MaintainContactManagementUIConstants.TO_DATE,
                            actionCode);
                }
                else
                {
                    if ((createdFromDate != null && !createdFromDate
                            .equalsIgnoreCase(""))
                            && (createdToDate != null && !createdToDate
                                    .equalsIgnoreCase("")))
                    {
                        Date fromDate = helper.dateConverter(createdFromDate);
                        Date todate = helper.dateConverter(createdToDate);
                        if (EnterpriseCommonValidator.compareGreaterDate(
                                fromDate, todate))
                        {
                        	if (fromDate.equals(todate)
									&& todate.equals(fromDate)) {
                        	
                        	}
                        	else 
                        	{
                            flag = false;
                            ContactManagementHelper
                                    .setErrorMessage(
                                            MaintainContactManagementUIConstants.TO_DATE_COM,
                                            MaintainContactManagementUIConstants.FROM_DATE,
                                            MaintainContactManagementUIConstants.FROM_DATE,
                                            actionCode);
                           }
                    }
                    }
                    if (statuDate != null && !statuDate.equalsIgnoreCase(""))
                    {
                        Date statDate = helper.dateConverter(statuDate);
                        Date toDate = helper.dateConverter(createdToDate);
                        if (!EnterpriseCommonValidator.compareLesserDate(
                                statDate, toDate))
                        {
                            flag = false;
                            ContactManagementHelper
                                    .setErrorMessage(
                                            MaintainContactManagementUIConstants.STATUS_TODATE_COM,
                                            MaintainContactManagementUIConstants.STATUS_DATE,
                                            MaintainContactManagementUIConstants.STATUS_DATE,
                                            actionCode);
                        }
                    }
                }
            }
        }
        //Added for displaying the Page level error messages related to Associated Case in Log Case Page
        if(!(flag && isFromReassignCase))
        {
        	caseSearchDataBean.setSearchCaseValidationFlag(true);
        }
        else{
        	caseSearchDataBean.setSearchCaseValidationFlag(false);
        }
    return flag;
    }

    /**
     * This operation is used to get all the Categories sorted on certain column
     * and order.
     * 
     * @param event :
     *            ActionEvent object.
     * @return String
     */
    public String getAllSortedCaseRecords(ActionEvent event)
    {
        logger.info("********* getAllSortedCaseRecords ********");
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        String sortColumn = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.COLUMN_NAME);
        String sortOrder = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.SORT_ORDER);
        CMLogCaseDataBean logCaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN); 
        logCaseDataBean.setCaseSearchRowIndex(0);
        caseSearchDataBean.setImageRender(event.getComponent().getId());
        
        /*sortCaseSerachRecords(sortColumn, sortOrder, caseSearchDataBean
                .getSearchCaseList());*/
        
		//Start - Redefined the sorting functionality for heap dump fix
        logger.debug(" sortColumn" + sortColumn);
        
        if(sortOrder.equals("asc"))
        {
        	caseSearchDataBean.getCaseRecordSearchCriteriaVO().setAscending(true);
        }
        else
        {
        	caseSearchDataBean.getCaseRecordSearchCriteriaVO().setAscending(false);
        }
        caseSearchDataBean.setStartRecord(0);
        
        if (sortColumn.equalsIgnoreCase("caseSearchcreatedDate")) {
        	
        	caseSearchDataBean.getCaseRecordSearchCriteriaVO().setSortColumn("openDate"); 	
        }
        else if (sortColumn.equalsIgnoreCase("caseSearchentityName")) {
        	
        	caseSearchDataBean.getCaseRecordSearchCriteriaVO().setSortColumn("entityName"); 	
        }
        else if (sortColumn.equalsIgnoreCase("caseSearchentityType")) {
        	
        	caseSearchDataBean.getCaseRecordSearchCriteriaVO().setSortColumn("commonEntityTypeCode"); 	
        }
        else if (sortColumn.equalsIgnoreCase("caseSearchstatus")) {
        	
        	caseSearchDataBean.getCaseRecordSearchCriteriaVO().setSortColumn("caseStatusCode"); 	
        }
        else if (sortColumn.equalsIgnoreCase("caseSearchassignedTo")) {
			
			caseSearchDataBean.getCaseRecordSearchCriteriaVO().setSortColumn("assignedTo"); 	
		}
		else if (sortColumn.equalsIgnoreCase("caseSearchcaseType")) {
			
			caseSearchDataBean.getCaseRecordSearchCriteriaVO().setSortColumn("description"); 	
		}
		else if (sortColumn.equalsIgnoreCase("caseSearchpriority")) {
			
			caseSearchDataBean.getCaseRecordSearchCriteriaVO().setSortColumn("casePriorityCode"); 	
		}
       
        reassignCaseSearch();
		//End - Redefined the sorting functionality for heap dump fix
        
        return "success";

    }

    /**
     * This operation is used to get all the CaseSearch sorted on certain column
     * and order.
     * 
     * @param sortColumn :
     *            Column names to sort the columns
     * @param sortOrder :
     *            Oder Type to sort the Columns
     * @param dataList :
     *            List of the records to Sort
     */
    private void sortCaseSerachRecords(final String sortColumn,
            final String sortOrder, List dataList)
    {
        logger.debug("****** sortCaseSerachRecords********");

        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                CaseRecordSearchResultsVO data1 = (CaseRecordSearchResultsVO) obj1;
                CaseRecordSearchResultsVO data2 = (CaseRecordSearchResultsVO) obj2;
                boolean ascending = false;
                if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
                        .equals(sortOrder))
                {
                    ascending = true;
                }
                else
                {
                    ascending = false;
                }

                if (sortColumn == null)
                {
                    return 0;
                }
                if ("caseSearchcreatedDate".equals(sortColumn))
                {
                    if (null == data1.getCreatedDate())
                    {
                        data1.setCreatedDate(new Date());
                    }
                    if (null == data2.getCreatedDate())
                    {
                        data2.setCreatedDate(new Date());
                    }
                    return ascending ? data1.getCreatedDate().compareTo(
                            data2.getCreatedDate()) : data2.getCreatedDate()
                            .compareTo(data1.getCreatedDate());
                }
                if ("caseSearchentityName".equals(sortColumn))
                {
                	if (null == data1.getEntityName())
                    {
                        data1
                                .setEntityName(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == data2.getEntityName())
                    {
                        data2
                                .setEntityName(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? data1.getEntityName().trim().toUpperCase().compareTo(
                            data2.getEntityName().trim().toUpperCase()) : data2
                            .getEntityName().trim().toUpperCase().compareTo(
                                    data1.getEntityName().trim().toUpperCase());
                }
                if ("caseSearchentityType".equals(sortColumn))
                {

                    if (null == data1.getEntityType())
                    {
                        data1
                                .setEntityType(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == data2.getEntityType())
                    {
                        data2
                                .setEntityType(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? data1.getEntityType().trim().compareTo(
                            data2.getEntityType().trim()) : data2
                            .getEntityType().trim().compareTo(
                                    data1.getEntityType().trim());
                }
                if ("caseSearchstatus".equals(sortColumn))
                {

                    if (null == data1.getStatus())
                    {
                        data1
                                .setStatus(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == data2.getStatus())
                    {
                        data2
                                .setStatus(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? data1.getStatus().trim().compareTo(
                            data2.getStatus().trim()) : data2.getStatus()
                            .trim().compareTo(data1.getStatus().trim());
                }
                if ("caseSearchassignedTo".equals(sortColumn))
                {

                    if (null == data1.getAssignedTo())
                    {
                        data1
                                .setAssignedTo(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == data2.getAssignedTo())
                    {
                        data2
                                .setAssignedTo(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? data1.getAssignedTo().compareToIgnoreCase(
                            data2.getAssignedTo()) : data2.getAssignedTo()
                            .compareToIgnoreCase(data1.getAssignedTo());
                }
                if ("caseSearchcaseType".equals(sortColumn))
                {

                    if (null == data1.getCaseType())
                    {
                        data1
                                .setCaseType(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == data2.getCaseType())
                    {
                        data2
                                .setCaseType(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? data1.getCaseType().trim().compareToIgnoreCase(
                            data2.getCaseType().trim()) : data2.getCaseType()
                            .trim().compareToIgnoreCase(data1.getCaseType().trim());
                }
                if ("caseSearchpriority".equals(sortColumn))
                {

                    if (null == data1.getPriority())
                    {
                        data1
                                .setPriority(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == data2.getPriority())
                    {
                        data2
                                .setPriority(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? data1.getPriority().trim().compareTo(
                            data2.getPriority().trim()) : data2.getPriority()
                            .trim().compareTo(data1.getPriority().trim());
                }
                if ("caseSearchlob".equals(sortColumn))
                {

                    if (null == data1.getLob())
                    {
                        data1.setLob(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == data2.getLob())
                    {
                        data2.setLob(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? data1.getLob().trim().compareTo(
                            data2.getLob().trim()) : data2.getLob().trim()
                            .compareTo(data1.getLob().trim());
                }
                return 0;
            }

        };

        Collections.sort(dataList, comparator);
    }

    /**
     * @return
     */
    public void submitCaseDetails()
    {
        logger.debug("========= inside submitCaseDetails ===========");
        /** Holds facesContext */
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map map = facesContext.getExternalContext().getRequestParameterMap();
        CommonCMCaseDetailsVO commonCMCaseDetailsVO = null;
        String caseSK = null;
        String entityType = null;
        String entityID = null;

        if (map != null)
        {
            caseSK = map.get("caseSK").toString();
            entityType = map.get("entityType").toString();
            entityID = map.get("entityID").toString();
            commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();

            commonCMCaseDetailsVO.setCaseSK(caseSK);
            commonCMCaseDetailsVO.setEntityType(entityType);
            commonCMCaseDetailsVO.setEntityID(entityID);
            commonCMCaseDetailsVO.setActionCode("CaseUpdate");
            showCasePortlet(commonCMCaseDetailsVO);
        }
        
    }

    /**
     * It will opens the Log Case screen.
     * 
     * @param commonCMCaseDetailsVO
     *            holds the case details.
     */
    public void showCasePortlet(CommonCMCaseDetailsVO commonCMCaseDetailsVO)
    {
        logger.debug("========= inside showCasePortlet ===========");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        PortletSession pSession = (PortletSession) facesContext.getExternalContext().getSession(true);
        pSession.setAttribute(ContactManagementConstants.ClearInqEntyData,
                                        ContactManagementConstants.ClearInqEntyData, pSession.APPLICATION_SCOPE);

        facesContext.getExternalContext().getRequestMap().put("CaseDetails",
                commonCMCaseDetailsVO);
    }

    /**
     * @return Returns the requestScope.
     */
    public Map getRequestScope()
    {
        return requestScope;
    }

    /**
     * @param requestScope
     *            The requestScope to set.
     */
    public void setRequestScope(Map requestScope)
    {
        this.requestScope = requestScope;
    }

    /**
     * This method is to retreive the data which is coming from IPC fro Member
     * Module.
     * 
     * @return Returns the memberLogCase.
     */
    public String getMemberLogCase()
    {
        logger.info("Inside getMemberLogCase() method");
        FacesContext fc = FacesContext.getCurrentInstance();
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        if (fc.getExternalContext().getRequestMap() != null)
        {

            String logCase = (String) fc.getExternalContext().getRequestMap()
                    .get("MemberLogCase");

            if (logCase != null)
            {
                int separatorIndex = logCase
                        .indexOf(ContactManagementConstants.COLON_SEPARATOR);

                String currAltId = logCase.substring(0, separatorIndex);
                String entityID = logCase.substring(separatorIndex + 1,
                        logCase.length());
                logger.debug("entityID 11111::::"+entityID);
                logger.debug("currAltId 11111::::" + currAltId);
                CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
                caseRecordSearchCriteriaVO.setEntityType(entityID);
                caseRecordSearchCriteriaVO.setEntityId(currAltId);
                caseRecordSearchCriteriaVO.setEntityIDType("MID");
               
                getCorrEntIDTypeValues(entityID, "Entity");
                caseSearchDataBean.setCaseRecordSearchCriteriaVO(
                        caseRecordSearchCriteriaVO);
                //searchCase();
            }

        }
       
        if (fc.getExternalContext().getRequestMap().get("CaseTrackingAppealSK") != null)
        {
             String caseAppealSK = (String)fc.getExternalContext().getRequestMap().get(
            "CaseTrackingAppealSK");
             if (caseAppealSK != null)
             {
             	 logger
                 .debug("After getting Case ID in getCaseIDFromAppeal, caseSK is --->"
                         + caseAppealSK);
                 CaseRecordSearchCriteriaVO criteriaVO = new CaseRecordSearchCriteriaVO();
                 criteriaVO.setCaseRecordNumber(caseAppealSK);
                 caseSearchDataBean.setCaseRecordSearchCriteriaVO(criteriaVO);
                 searchCase();
             }
        }
        if (fc.getExternalContext().getRequestMap().get("CaseTrackingProviderSk") != null)
        {
             String caseProviderSK = (String)fc.getExternalContext().getRequestMap().get(
            "CaseTrackingProviderSk");
             if (caseProviderSK != null)
             {
                 
                 CaseRecordSearchCriteriaVO criteriaVO = new CaseRecordSearchCriteriaVO();
                 criteriaVO.setCaseRecordNumber(caseProviderSK);
                 caseSearchDataBean.setCaseRecordSearchCriteriaVO(criteriaVO);
                 searchCase();
             }
        }
        return memberLogCase;
    }

    /**
     * @param memberLogCase
     *            The memberLogCase to set.
     */
    public void setMemberLogCase(String memberLogCase)
    {
        this.memberLogCase = memberLogCase;
    }
    /**
     * *********************************************
     * From here added by ICS for GAP9022(Reassign Cases)
     * *********************************************
     */
    /**
     * This method is used to get the BusinessUnitSK for User.
     * 
     * @return String : User ID
     */
    public String getBusinessUnitSKforUser(Long userSK)
    {
        
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        String businessCode = null;
        try
        {
            List deptsList = contactMaintenanceDelegate.getDepartmentUsers(userSK);



            if (deptsList != null)
            {
             
              

                for (Iterator iter = deptsList.iterator(); iter.hasNext();)
                {
                    DepartmentUser deptUser = (DepartmentUser) iter.next();                 
                    String lobHierarchySK = deptUser.getDepartment()
                            .getLineOfBusinessHierarchy()
                            .getLineOfBusinessHierarchySK().toString();
                 
                    LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
                            .getDeptBusinessUnit(new Long(lobHierarchySK));
                    if (businessUnit != null)
                    {
                        businessCode = businessUnit
                                .getLineOfBusinessHierarchySK().toString();
                        
                      
                       
                    }                
                }
                
            }
        }
        catch (LOBHierarchyFetchBusinessException lobExp)
        {
            logger.error(lobExp.getMessage(), lobExp);
        }
        
        return businessCode;
    }
    
    // Begin - Unused methods commented as a part of code cleanup - Performance_Fix_UC-PGM-CRM-073_25OCT2011    
    /**
     * This method is used to retreive all users and Departments for Reassign
     * all
     */
    /*public List getAllUsersForReassign(List lNameFnameList)
    {
        List deptUsers = null;
        List reassignDeptList = new ArrayList();
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
     
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        Set depts = new HashSet();
        Set users = new HashSet();
        try
        {
            deptUsers = contactMaintenanceDelegate.getAllDepartmentUsers();
            logger.debug("after contact maintenance delegate call :"
                    + deptUsers);
            
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            e.printStackTrace();
        }

        Iterator it2 = deptUsers.iterator();

        while (it2.hasNext())
        {
            DepartmentUser element = (DepartmentUser) it2.next();

            depts.add(element.getDepartment());
            users.add(element.getEnterpriseUser());
        }
        for (Iterator iter = users.iterator(); iter.hasNext();)
        {
            EnterpriseUser user = (EnterpriseUser) iter.next();
            
            if (lNameFnameList != null
                    && !(lNameFnameList.contains(user.getFirstName().trim()
                            + " " + user.getLastName().trim()))) 
            {
            reassignDeptList.add(new SelectItem(user.getUserWorkUnitSK()
                    .toString().trim(), user.getLastName() + ", "
                    + user.getFirstName() + " - "
                    + user.getUserID()));
            }else
            {
                logger.debug("matching Fname and Lname" + user.getFirstName().trim()); 
            
            }

        }
        for (Iterator iter = depts.iterator(); iter.hasNext();)
        {
            
            Department dept = (Department) iter.next();
            if (lNameFnameList != null
                    && !(lNameFnameList.contains(dept.getName())))
            {
            reassignDeptList.add(new SelectItem(dept.getWorkUnitSK().toString()
                    .trim(), dept.getName()));
            }else
            {
                logger.debug("=Name="+ dept.getName());
                       
            
            }

        }
        logger
        .debug("******* in getAllDeptUsersForReassign()  reassignDeptList : "
                + reassignDeptList.size());
       
        caseSearchDataBean
                .setReassignAll(reassignDeptList);
        caseSearchDataBean.setAllDeptUsersForReassign(reassignDeptList);
        sortReassignedToList(
                MaintainContactManagementUIConstants.SORT_TYPE_ASC,
                reassignDeptList);
        
        return reassignDeptList;

    }*/
    // End - Unused methods commented as a part of code cleanup - Performance_Fix_UC-PGM-CRM-073_25OCT2011    
    /**
     * This method is used to change depts in dropdown on Change of departments.
     * 
     * @param event
     *            the event
     * @param inputField
     *            List listSK.
     */
    public String onChangeReassignAll() {
		logger.info("++Inside the method:onChangeReassignAll");
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		caseSearchDataBean.setShowReassignResultsPage(true); // Modified for
																// Performance_Fix_UC-PGM-CRM-073_25OCT2011
		//for POC from CRM.
		caseSearchDataBean.setReassignCaseResultTable(0);
		//Added for defect CR ESPRD00798895 for Pagination Issue
		caseSearchDataBean.setStartRecord(0);
    	caseSearchDataBean.setCurrentPage(1);
    	caseSearchDataBean.setShowPrevious(false);
		chkReassignAllValueNSearch();

		/*
		 * if(allDeptReassign != null){ searchList =
		 * caseSearchDataBean.getSearchCaseList(); Iterator itr2 =
		 * searchList.iterator(); while(itr2.hasNext()){
		 * caseRecordSearchResultsVO = (CaseRecordSearchResultsVO)itr2.next();
		 * List deptList = caseRecordSearchResultsVO.getDeptList();
		 * 
		 * for (Iterator iter = deptList.iterator(); iter.hasNext();) {
		 * SelectItem item = (SelectItem) iter.next();
		 * if(item.getValue().equals(allDeptReassign.trim())){
		 * 
		 * caseRecordSearchResultsVO.setSelectedDept(allDeptReassign.trim());
		 * break; }else{ caseRecordSearchResultsVO.setSelectedDept(""); } }
		 * caseSearchList.add(caseRecordSearchResultsVO); }
		 * caseSearchDataBean.setSearchCaseList(caseSearchList);
		 * caseSearchDataBean.setNoData(true);
		 * caseSearchDataBean.setNoDataFlag(true);
		 * caseSearchDataBean.setShowCaseSearchMsgFlag(false);
		 * 
		 * logger.info("exit from method : onChangeReassignAll"); }
		 */
		return ContactManagementConstants.EMPTY_STRING;
	} 
    
	// Start - This method is added for redefining the search method
	/**
	* This method is used to search the case records
	* @return
	*/
    public String reassignCaseRecordsSearch()
    {
    	caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
    	caseSearchDataBean.setSelectedDeptAll("");
    	
    	caseSearchDataBean.setStartRecord(0);
    	caseSearchDataBean.setStartRecordNo(caseSearchDataBean.getStartRecord());
    	caseSearchDataBean.setCurrentPage(1);
    	caseSearchDataBean.setShowPrevious(false);
    	caseSearchDataBean.setShowNext(false);
    	caseSearchDataBean.setReassignFlag(true); // Added for the ReassingCaseSearch
    	reassignCaseSearch();
    	return "";
    }
	// End - This method is added for redefining the search method
    
	//Start - This method is redefined for the Heap Dump fix
    //New method is implemented with same signature of below method.
    //Hence this is commented.
    //for CR 798895 for Bulk reassign
    /*public String reassignCaseSearch() {
    	validateSuccess = false;
    	caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
    	//Added By Infinite for the defect ESPRD00342016
		HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderResponse = null;
		EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
		 
		
		
		 Added For the Defect ID ESPRD00359076 
		String searchAuthority = "";
		if (eup != null){
			searchAuthority=eup.getCaseFilter();
		} 
		if(eup == null || searchAuthority == null || isNullOrEmptyField(searchAuthority)){
			ContactManagementHelper
			.setErrorMessage(MaintainContactManagementUIConstants.NO_AUTHORITY_ADD_CASE);
			caseSearchDataBean.setShowCaseSearchMsgFlag(true);
		}else {
			caseSearchDataBean.setReassignCaseResultTable(0);// Modified for the HeapDump issue.
															//added for defect ID ESPRD00300194
			int count = 0;
			CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
			caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) caseSearchDataBean
					.getCaseRecordSearchCriteriaVO();
			if(caseSearchDataBean.getSelectedDeptAll() == null)
			{
				caseSearchDataBean.setSelectedDeptAll("");
			}
			EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
			CaseRecordSearchResultsVO caseRecordSearchResultsVO = new CaseRecordSearchResultsVO();
			CaseDelegate caseDelegate = new CaseDelegate();
			CommonCMCaseDetailsVO commonCMCaseDetailsVO = null;
			String caseSK = null;
			String entityType = null;
			String entityID = null;
			
			
			if (validate(caseRecordSearchCriteriaVO,true)) {
				String userId=getUserID();
				logger.debug("getUserID()::"+userId);
				caseRecordSearchCriteriaVO.setUserId(userId);
				int size = 0;
				String entityName = "";
			
				try {

					caseRecordSearchCriteriaVO
							.setCaseSK(caseRecordSearchCriteriaVO
									.getCaseRecordNumber());
					caseRecordSearchCriteriaVO.setPageNavigatingFrom("RC");
					caseRecordSearchCriteriaVO.setUserId(getUserID());
					if (StringUtils.isNotBlank(caseRecordSearchCriteriaVO
							.getDepartments())) {
						List listSK = new ArrayList();
						listSK.add(caseRecordSearchCriteriaVO.getDepartments());
						caseRecordSearchCriteriaVO.setMaintainGroups(listSK);
					} else {
						caseRecordSearchCriteriaVO
								.setMaintainGroups(caseSearchDataBean
										.getMaintainGroups());
					}

					caseRecordSearchCriteriaVO.setReassignFlag(true); // Added for the ReassingCaseSearch
					caseRecordSearchCriteriaVO.setStartIndex(caseSearchDataBean.getStartRecord());
					caseRecordSearchCriteriaVO.setRowsPerPage(caseSearchDataBean.getItemsPerPage());
					
					enterpriseSearchResultsVO = caseDelegate
							.getCaseRecords(caseRecordSearchCriteriaVO);
					
					int recordCount = (int) enterpriseSearchResultsVO.getRecordCount();
					caseSearchDataBean.setCount(Integer.valueOf(String.valueOf(enterpriseSearchResultsVO.getRecordCount())));
					
					// Begin - Modified the code for the Heap Dump Fix
					if (caseSearchDataBean.getStartRecord() == 0) {
						caseSearchDataBean.setStartRecordNo(caseSearchDataBean.getStartRecord() + 1);
						caseSearchDataBean.setCurrentPage(1);
						if(enterpriseSearchResultsVO.getRecordCount() < caseSearchDataBean.getItemsPerPage())
						{
							caseSearchDataBean.setEndRecord(recordCount);
							caseSearchDataBean.setShowNext(false);
							caseSearchDataBean.setShowNextOne(false);
							caseSearchDataBean.setShowPrevious(false);
						}
						else
						{
							caseSearchDataBean.setEndRecord(caseSearchDataBean.getStartRecord() + caseSearchDataBean.getItemsPerPage());
							caseSearchDataBean.setShowNext(true);
							caseSearchDataBean.setShowPrevious(false);
							caseSearchDataBean.setShowNextOne(false);
						}
					}
					if(caseSearchDataBean.getCurrentPage() == 1&& 
							enterpriseSearchResultsVO.getRecordCount() > 2*caseSearchDataBean.getItemsPerPage()) // Modified for the DataScroller Fix
					{
						caseSearchDataBean.setShowNextOne(true);
					}
					// End - Modified the code for the Heap Dump Fix
										
				} catch (CaseRecordFetchBusinessException e) {
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
					logger.error(e.getMessage(), e);
				}

				if (enterpriseSearchResultsVO.getSearchResults() == null) {
					
					setErrorMessage(
							EnterpriseMessageConstants.AUTHORITY_ERROR_CODE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
					caseSearchDataBean.setShowCaseSearchMsgFlag(true);
					caseSearchDataBean.setShowReassignResultsPage(false);
					return "";
				} else {
					
					//defect  ESPRD00611227 starts
					List searchResults=enterpriseSearchResultsVO.getSearchResults();
					CaseRecordSearchResultsVO caseRecordSearchResultsV=null;
					if (searchResults!= null) {
						List searchResultsUpdated=new ArrayList();
						String entityTypeS=null;
						String shortDesc=null;
						Iterator it=searchResults.iterator();
						// Begin - Commented for the Performance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
						while(it.hasNext()){
							caseRecordSearchResultsV=(CaseRecordSearchResultsVO)it.next();
							entityTypeS=caseRecordSearchResultsV.getEntityType();
							if(entityTypeS!=null && !entityTypeS.equals("")){
							shortDesc = ReferenceServiceDelegate
							.getReferenceSearchShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
									new Long(80),
									entityTypeS);
							caseRecordSearchResultsV.setEntityType(shortDesc);
							searchResultsUpdated.add(caseRecordSearchResultsV);
							}
						}
						// End - Commented for the Performance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
						//defect  ESPRD00611227 ends
						caseSearchDataBean
						.setSearchCaseList(enterpriseSearchResultsVO
								.getSearchResults());
						size = enterpriseSearchResultsVO.getSearchResults()
						.size();
					}
				}

				
				if (size > 0) {
					List list = enterpriseSearchResultsVO.getSearchResults();
					Map entityMap = caseSearchDataBean.getEntityTypeMap(); // Added for displaying the entityType. - 						// Begin - Performance_Fix_UC-PGM-CRM-073_25OCT2011
					if (list.size() == -1) {
						logger.debug("CaseSearch results list size is $$ "
								+ list.size());
						CaseRecordSearchResultsVO searchResultsVO = (CaseRecordSearchResultsVO) list
								.get(0);
						;
						commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
						if (searchResultsVO.getCaseSK() != null
								&& !searchResultsVO.getCaseSK().equals("")) {
							caseSK = searchResultsVO.getCaseSK().toString();
						}
						entityType = searchResultsVO.getEntityType();
						// Begin - Added for the Performance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011			
						if(entityMap.get(searchResultsVO.getEntityType()) != null)
						{
							entityType = entityMap.get(searchResultsVO
											.getEntityType()).toString();
						}
						else
						{
							entityType = searchResultsVO.getEntityType();
						}
						// End - Added for the Performance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011						
						if (searchResultsVO.getCommonEntitySK() != null
								&& !searchResultsVO.getCommonEntitySK().equals(
										"")) {
							entityID = searchResultsVO.getCommonEntitySK()
									.toString();
						}
						commonCMCaseDetailsVO.setCaseSK(caseSK);
						commonCMCaseDetailsVO.setEntityType(entityType);
						commonCMCaseDetailsVO.setEntityID(entityID);
						commonCMCaseDetailsVO.setActionCode("CaseUpdate");
						caseSearchDataBean.setShowReassignResultsPage(false);//added
																			 // for
																			 // defect
																			 // ID
																			 // ESPRD00300194
						caseSearchDataBean.setNoDataFlag(false);//added for
																// defect ID
																// ESPRD00300194
						caseSearchDataBean.setNoData(false);//added for defect
															// ID ESPRD00300194
						showCasePortlet(commonCMCaseDetailsVO);
					} else {
						ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
						Long userBusinessSk = null;
						List businessUnitsList = new ArrayList();
						logger
						.debug("searchResults size=======>"
								+ list.size());
						// Begin - Commented for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
						try {
							businessUnitsList = contactMaintenanceDelegate
									.getLobHierarchyDetails(new ArrayList(),
											ContactManagementConstants.THREE);
						} catch (LOBHierarchyFetchBusinessException e1) {
							setErrorMessage(
									EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
									new Object[] {},
									MessageUtil.ENTMESSAGES_BUNDLE, null);
							caseSearchDataBean
									.setShowReassignResultsPage(false);
							logger
							.debug("Exception in reassignCaseSearch() of controller bean");
						}
						// End - Commented for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
						List lnameFnameList = new ArrayList();
						while (count < list.size()) {
							caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) list
									.get(count);
							lnameFnameList.add(caseRecordSearchResultsVO
									.getAssignedTo().trim());
							count++;
						}
						count = 0;
						getAllDeptUsersForReassign(); // Added new mthod for the Performance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
						// Commented the below line of code for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
						//List allDepartmentUsersList = getAllDepartmentUsersList(); //ESPRD00531002_UC-PGM-CRM-073_18oct2010
						//Modified for defect ESPRD00531256 starts
						//Moved the code outside loop for performance
						//getAllUsersForReassign2(allDepartmentUsersList); // Commented the line for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
						//defect ESPRD00531256 ends
						while (count < list.size()) {
							caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) list
									.get(count);
							
							// Begin - Commented for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
							List deptListTemp = new ArrayList();
							
							//                   modified for defectID ESPRD00300194
							
							String strBusinessUnitSKforUser = getBusinessUnitSKforUser(caseRecordSearchResultsVO
									.getUserWorkUnitSK());
							if (strBusinessUnitSKforUser != null) {
								userBusinessSk = new Long(
										strBusinessUnitSKforUser);
							}
							// EOF ESPRD00300194
							List userList = null;
							try {
								if (userBusinessSk != null) {
									userList = contactMaintenanceDelegate
											.getAllUsersOfBusinessUnit(userBusinessSk);
								}
							} catch (LOBHierarchyFetchBusinessException e2) {
								setErrorMessage(
										EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
										new Object[] {},
										MessageUtil.ENTMESSAGES_BUNDLE, null);
								caseSearchDataBean
										.setShowReassignResultsPage(false);
								logger
										.debug("Exception reassignCaseSearch() ");
							}
							//	                   modified for defectID ESPRD00300194
							catch (Exception e) {
								logger
										.debug("Exception in reassignCaseSearch()"
												+ e.getMessage());
							}
							if (userList != null) {

								EnterpriseUser user = null; //ESPRD00531002_UC-PGM-CRM-073_18oct2010
								
								for (Iterator iter = userList.iterator(); iter
										.hasNext();) {
									user = (EnterpriseUser) iter.next(); //ESPRD00531002_UC-PGM-CRM-073_18oct2010
									
									if (caseRecordSearchResultsVO
											.getUserWorkUnitSK() != null) {
										//commented for defect ESPRD00640829(this condition is not required)
										if (!caseRecordSearchResultsVO
												.getUserWorkUnitSK()
												.equals(
														user
																.getUserWorkUnitSK())) {
										if (caseRecordSearchResultsVO
												.getUserWorkUnitSK()
												.equals(
														user
																.getUserWorkUnitSK())) {
											logger.debug("--"+user.getUserWorkUnitSK().toString().trim());
											logger.debug("--"+user.getLastName());
										}
											deptListTemp
													.add(new SelectItem(
															user
																	.getUserWorkUnitSK()
																	.toString()
																	.trim(),
															user.getLastName()
																	+ ", "
																	+ user
																			.getFirstName()
																	+ " - "
																	+ user
																			.getUserWorkUnitSK()
																			.toString()));
											logger
											.info("Each user to whom we can assign the case"
													+ user
															.getUserWorkUnitSK());

										}
									}
								}
							}
							//EOF defectID ESPRD00300194
							// End - Commented for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
							

							//Modified for defect ESPRD00342016 by infinite team 
							//modified for ESPRD00531002_UC-PGM-CRM-073_18oct2010
							// Begin - Modified the code for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
							String assignedUserforReassign = ContactManagementConstants.EMPTYSTRING;
							if(caseRecordSearchResultsVO.getAssignedTo()!=null){
								assignedUserforReassign = getAssignedUSer(caseRecordSearchResultsVO
    									.getAssignedToUser().toString());
								assignedUserforReassign = getAssignedUser(caseRecordSearchResultsVO
    									.getAssignedToUser().toString(),allDepartmentUsersList);
								String user = caseRecordSearchResultsVO
										.getAssignedToUser().toString();
								if(user != null)
								{
								caseRecordSearchResultsVO.setAssignedTo(caseRecordSearchResultsVO.getAssignedTo());
								//}
							// End - Modified the code for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011								
							}
							
							
							caseRecordSearchResultsVO
							.setAssignedTo(assignedUserforReassign);
												
							caseRecordSearchResultsVO
									.setDeptList(getAllUsersForReassign(
											lnameFnameList,
											assignedUserforReassign));
							//Modified for defect ESPRD00531256 starts
							//Note from query side we are getting the filtered userlist
							caseRecordSearchResultsVO
									.setDeptList(getAllUsersForReassign(
											lnameFnameList,
											assignedUserforReassign,
											allDepartmentUsersList));
							logger.debug("-------caseRecordSearchResultsVO.getDeptList()---"+caseRecordSearchResultsVO.getDeptList());
							// Begin - Modified the code for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
							if(caseRecordSearchResultsVO.getDeptList()!=null && caseRecordSearchResultsVO.getDeptList().size()>0){
								caseRecordSearchResultsVO.setDeptList(getDepartmentList(caseRecordSearchResultsVO.getDeptList()));								
							}else{
								List deptList = new ArrayList();
								deptList.add(new SelectItem("",""));
								caseRecordSearchResultsVO.setDeptList(deptList);
							}
							// End - Modified the code for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
							//defect ESPRD00531256 ends
							//EOF ESPRD00531002_UC-PGM-CRM-073_18oct2010
							//ESPRD00334555 ends
							logger.info("Cr Assigned user---->"
									+ caseRecordSearchResultsVO
											.getUserWorkUnitSK());
							if (caseRecordSearchResultsVO.getEntityName() != null
									&& !caseRecordSearchResultsVO
											.getEntityName().equals("")) {
								entityName = caseRecordSearchResultsVO
										.getEntityName();
							} else {
								if (caseRecordSearchResultsVO
										.getEntityFirstName() != null
										&& !caseRecordSearchResultsVO
												.getEntityFirstName()
												.equals("")) {
									entityName = caseRecordSearchResultsVO
											.getEntityFirstName().trim();
								}
								if (caseRecordSearchResultsVO
										.getEntityMidName() != null
										&& !caseRecordSearchResultsVO
												.getEntityMidName().equals("")) {
									entityName = entityName
											+ " "
											+ caseRecordSearchResultsVO
													.getEntityMidName().trim();
								}
								if (caseRecordSearchResultsVO
										.getEntityLastName() != null
										&& !caseRecordSearchResultsVO
												.getEntityLastName().equals("")) {
									entityName = entityName
											+ " "
											+ caseRecordSearchResultsVO
													.getEntityLastName().trim();
								}
							}
							 ESPRD00538044 Start 
							if((entityName == null || entityName.equals("")) 
									&& (caseRecordSearchResultsVO.getEntityOrgName() != null && !caseRecordSearchResultsVO.getEntityOrgName().equalsIgnoreCase("")))
							{
								entityName = caseRecordSearchResultsVO.getEntityOrgName();
								logger.debug("++organization name::"+entityName);
							}
							 ESPRD00538044 End 
							caseRecordSearchResultsVO.setEntityName(entityName
									.trim());

							if (caseRecordSearchResultsVO.getEntityType() != null
									&& !caseRecordSearchResultsVO
											.getEntityType().equalsIgnoreCase(
													"")) {
							// Begin - Modified the code for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
								String entityTypeDesc = ContactManagementHelper
										.setShortDescription(
												FunctionalAreaConstants.CONTACT_MGMT,
												ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
												caseRecordSearchResultsVO
														.getEntityType());
								if (entityTypeDesc != null
										&& entityTypeDesc.length() > 0) {
									caseRecordSearchResultsVO
											.setEntityTypeDesc(entityTypeDesc);
								} else {
								
								if(entityMap.get(caseRecordSearchResultsVO.getEntityType()) != null)
								{
									caseRecordSearchResultsVO
											.setEntityTypeDesc(entityMap.get(caseRecordSearchResultsVO
													.getEntityType()).toString());
								}
								else
								{
									caseRecordSearchResultsVO.setEntityTypeDesc(caseRecordSearchResultsVO
											.getEntityType());
								}
								//}
							// End - Modified the code for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011								
							}

							if (caseRecordSearchResultsVO.getStatus() != null
									&& !caseRecordSearchResultsVO.getStatus()
											.equalsIgnoreCase("")) {
								// Begin - Modified for the performance fix.
								String status = ContactManagementHelper
										.setShortDescription(
												FunctionalAreaConstants.GENERAL,
												ReferenceServiceDataConstants.G_CASE_STAT_CD,
												caseRecordSearchResultsVO
														.getStatus());
								String status = getDescriptionFromVV(caseRecordSearchResultsVO.getStatus(),
													caseSearchDataBean.getStatus());
								// End - Modified for the performance fix.
								caseRecordSearchResultsVO.setStatus(status);
							}

							if (caseRecordSearchResultsVO.getPriority() != null
									&& !caseRecordSearchResultsVO.getPriority()
											.equalsIgnoreCase("")) {
								// Begin - Modified for the performance fix.
								String priority = ContactManagementHelper
										.setShortDescription(
												FunctionalAreaConstants.GENERAL,
												ReferenceServiceDataConstants.G_CASE_PRTY_CD,
												caseRecordSearchResultsVO
														.getPriority());
								String priority = getDescriptionFromVV(caseRecordSearchResultsVO.getPriority(),
														caseSearchDataBean.getPriority());
								// End - Modified for the performance fix.
								caseRecordSearchResultsVO.setPriority(priority);
							}

							if (caseRecordSearchResultsVO.getLob() != null
									&& !caseRecordSearchResultsVO.getLob()
											.equalsIgnoreCase("")) {
								// Begin - Modified for the performance fix.
								String lob = ContactManagementHelper
										.setShortDescription(
												FunctionalAreaConstants.REFERENCE,
												ReferenceServiceDataConstants.R_LOB_CD,
												caseRecordSearchResultsVO
														.getLob());
								String lob = getDescriptionFromVV(caseRecordSearchResultsVO.getLob(),
												caseSearchDataBean.getLobVVList());
								// End - Modified for the performance fix.
								caseRecordSearchResultsVO.setLob(lob);
							}
							if(caseSearchDataBean.getSelectedDeptAll() != null)
							{
								caseRecordSearchResultsVO.setSelectedDept(caseSearchDataBean.getSelectedDeptAll());
							}
							count++;
						}
						caseSearchDataBean.setNoData(true);
						caseSearchDataBean.setNoDataFlag(true);
						caseSearchDataBean.setShowCaseSearchMsgFlag(true); // Modified for the defect id ESPRD00736670_21DEC2011
						caseSearchDataBean.setShowReassignResultsPage(true);
						caseSearchDataBean.setSuccess(false);
					}
				} else {
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
					caseSearchDataBean.setShowCaseSearchMsgFlag(true);
					caseSearchDataBean.setShowReassignResultsPage(false);
					caseSearchDataBean.setNoDataFlag(false);
					caseSearchDataBean.setNoData(false);
					caseSearchDataBean.setSuccess(false);
				}
			} else {
				caseSearchDataBean.setShowReassignResultsPage(false);
				caseSearchDataBean.setShowCaseSearchMsgFlag(true);
				caseSearchDataBean.setSuccess(false);
			}
		}
		return "";

	}*/
  //End - This method is redefined for the Heap Dump fix
    
    /**
	 * This method is invoked to get the search results as per the search
	 * criteria provided by the user with validating the search authority and
	 * criteria
	 * */
	public String reassignCaseSearch() {
		validateSuccess = Boolean.FALSE;
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderResponse = null;
		EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
		String searchAuthority = ContactManagementConstants.EMPTY_STRING;
		if (eup != null) {
			searchAuthority = eup.getCaseFilter();
		}
		if (eup == null || searchAuthority == null
				|| isNullOrEmptyField(searchAuthority)) {
			ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.NO_AUTHORITY_ADD_CASE);
			caseSearchDataBean.setShowCaseSearchMsgFlag(Boolean.TRUE);
		} else {
			caseSearchDataBean.setReassignCaseResultTable(0);
			CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = null;
			caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) caseSearchDataBean
					.getCaseRecordSearchCriteriaVO();
			if (caseSearchDataBean.getSelectedDeptAll() == null) {
				caseSearchDataBean.setSelectedDeptAll("");
			}
			if (validate(caseRecordSearchCriteriaVO, Boolean.TRUE)) {
				String userId = getLoggedInUser();
				caseRecordSearchCriteriaVO.setUserId(userId);
				caseRecordSearchCriteriaVO.setCaseSK(caseRecordSearchCriteriaVO
						.getCaseRecordNumber());
				caseRecordSearchCriteriaVO.setPageNavigatingFrom("RC");
				caseRecordSearchCriteriaVO.setUserId(userId);
				if (StringUtils.isNotBlank(caseRecordSearchCriteriaVO
						.getDepartments())) {
					List listSK = new ArrayList();
					listSK.add(caseRecordSearchCriteriaVO.getDepartments());
					caseRecordSearchCriteriaVO.setMaintainGroups(listSK);
				} else {
					caseRecordSearchCriteriaVO
							.setMaintainGroups(caseSearchDataBean
									.getMaintainGroups());
				}

				caseRecordSearchCriteriaVO.setReassignFlag(Boolean.TRUE);
				caseRecordSearchCriteriaVO.setStartIndex(caseSearchDataBean
						.getStartRecord());
				caseRecordSearchCriteriaVO.setRowsPerPage(caseSearchDataBean
						.getItemsPerPage());

				chkReassignAllValueNSearch();

			} else {
				caseSearchDataBean.setShowReassignResultsPage(Boolean.FALSE);
				caseSearchDataBean.setShowCaseSearchMsgFlag(Boolean.TRUE);
				caseSearchDataBean.setSuccess(Boolean.FALSE);
			}
		}
		return ContactManagementConstants.EMPTY_STRING;

	}
    // Begin - Unused methods commented as a part of code cleanup - Performance_Fix_UC-PGM-CRM-073_25OCT2011    
    //added overloaded methods for ESPRD00531002_UC-PGM-CRM-073_18oct2010
    /**
     * @param lNameFnameList contains combination of lastName firstName list
     * @param  assignedUserforReassign for assignedUserforReassign
     * @param deptUsers is list of all department users
     * @return list of all users for Reassign  
     */
    /*public List getAllUsersForReassign(List lNameFnameList,String assignedUserforReassign, List deptUsers)
    {
		
		List reassignDeptList = new ArrayList();
		List reassignAllDeptsList = new ArrayList();
	
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		ContactMaintenanceDelegate contactMaintenanceDelegate =new ContactMaintenanceDelegate();
		Set depts = new HashSet();
		//Set users = new HashSet();  
		if(deptUsers!=null){
			logger.debug("+++++++++++deptUsers=="+deptUsers.size());
			Iterator it2 = deptUsers.iterator();
			DepartmentUser element = null;
			while (it2.hasNext()) {
				element = (DepartmentUser) it2.next();
				if(element.getDepartment().getLineOfBusinessHierarchy()!=null && element.getDepartment().getLineOfBusinessHierarchy().getVoidIndicator()!=null && !element.getDepartment().getLineOfBusinessHierarchy().getVoidIndicator().booleanValue())
					depts.add(element.getDepartment());
				if(element.getEnterpriseUser().getAccountActiveIndicator()!=null && element.getEnterpriseUser().getAccountActiveIndicator().booleanValue())
					users.add(element.getEnterpriseUser());
			}
			EnterpriseUser user = null;
			StringBuffer sbName = null;
			int a=0;
			List activUserList = new ArrayList();
			try {
				activUserList = contactMaintenanceDelegate.getActiveUsers();
			} catch (EnterpriseBaseBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Iterator iter = activUserList.iterator(); iter.hasNext();) {
				user = (EnterpriseUser) iter.next();
				a++;
				if (lNameFnameList != null
						&& !(lNameFnameList.contains(String.valueOf(user.getFirstName()).trim()
								+ " " + String.valueOf(user.getLastName()).trim())) ) {
					
					sbName= new StringBuffer();
                    if(user.getLastName()!=null && !MaintainContactManagementUIConstants.EMPTY_STRING.equals(user.getLastName())){
                    	sbName.append(user.getLastName()).append(",").append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
                    }
                    sbName.append(user.getFirstName()).append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE).append(MaintainContactManagementUIConstants.HYPHEN);
                    sbName.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE).append(user.getUserWorkUnitSK());
                    
					reassignAllDeptsList.add(new SelectItem(user
							.getUserWorkUnitSK().toString(), sbName.toString()));
					if (user.getCaseFilter() != null && !(assignedUserforReassign.equals(user.getUserWorkUnitSK().toString()))) {
						reassignDeptList.add(new SelectItem(user
								.getUserWorkUnitSK().toString(), sbName.toString()));
						
					}
				} 

			}
			logger.debug("++a=="+a);
			Department dept = null;
			int b=0;
			for (Iterator iter = depts.iterator(); iter.hasNext();) {
				b++;
				dept = (Department) iter.next();
				if (lNameFnameList != null
						&& !(lNameFnameList.contains(dept.getName()))) {

					reassignDeptList.add(new SelectItem(dept.getWorkUnitSK()
							.toString(), dept.getName()));

					reassignAllDeptsList.add(new SelectItem(dept.getWorkUnitSK()
							.toString(), dept.getName()));
					
				} 

			}
			logger.debug("++b=="+b);
		}
		
		sortReassignedToList(
                MaintainContactManagementUIConstants.SORT_TYPE_ASC,
                reassignDeptList);
        sortReassignedToList(
                MaintainContactManagementUIConstants.SORT_TYPE_ASC,
                reassignAllDeptsList);
		caseSearchDataBean.setReassignAll(reassignDeptList);
		caseSearchDataBean.setAllDeptUsersForReassign(reassignAllDeptsList);

		return reassignDeptList;

	}*/
    // End - Unused methods commented as a part of code cleanup - Performance_Fix_UC-PGM-CRM-073_25OCT2011    
    /**
     * 
     * @param userId 
     * @param deptUsers is list of all department users
     * @return String which contains AssignedUser information
     */
    public String  getAssignedUser(String userId,List deptUsers)
    {
		logger.debug("Inside getAssignedUSer(String userId,List deptUsers)");
		String assigntToUSer = " ";
		
		if(deptUsers!=null){
			caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
			Set depts = new HashSet();
			Set users = new HashSet();
			/*try {
				deptUsers = contactMaintenanceDelegate.getAllDepartmentUsers();
			} catch (LOBHierarchyFetchBusinessException e) {
				logger.error(e.getMessage(), e);
			}*/
			Iterator it2 = deptUsers.iterator();
			DepartmentUser element = null;
			while (it2.hasNext()) {
				element = (DepartmentUser) it2.next();
				depts.add(element.getDepartment());
				users.add(element.getEnterpriseUser());
			}
			EnterpriseUser user = null;
			for (Iterator iter = users.iterator(); iter.hasNext();) {
				user = (EnterpriseUser) iter.next();
				try {
					if (userId != null) {
						if (userId.equals(user.getUserWorkUnitSK().toString()
								.trim())) {
							assigntToUSer = user.getUserWorkUnitSK().toString();
							break;
						}
					}
				} catch (Exception e) {
					logger.debug(" inside Exception getAllDeptUsersForReassign(String,List) ");

				}
			}
		}

		
		return assigntToUSer;
}
    /**
     * 
     * @return list of all department users
     */
    public List getAllDepartmentUsersList() {
    	logger.debug(" inside  getAllDepartmentUsersList() ");
		List deptUsers = null;
		try {
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			deptUsers = contactMaintenanceDelegate.getAllDepartmentUsers();
		} catch (LOBHierarchyFetchBusinessException e) {
			logger.debug(" inside Exception getAllDepartmentUsersList() ");
			logger.error(e.getMessage(), e);
		}
		return deptUsers;
	}
    //EOf ESPRD00531002_UC-PGM-CRM-073_18oct2010
    
    // Begin - Unused methods commented as a part of code cleanup - Performance_Fix_UC-PGM-CRM-073_25OCT2011
    // Added Method for the defect ESPRD00342016 by infinite
    /*public List getAllUsersForReassign(List lNameFnameList,String assignedUserforReassign)
    {
		
		List deptUsers = null;
		List reassignDeptList = new ArrayList();
		List reassignAllDeptsList = new ArrayList();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
	
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		Set depts = new HashSet();
		Set users = new HashSet();
		try {
			deptUsers = contactMaintenanceDelegate.getAllDepartmentUsers();
		} catch (LOBHierarchyFetchBusinessException e) {
			e.printStackTrace();
		}

		Iterator it2 = deptUsers.iterator();

		while (it2.hasNext()) {
			DepartmentUser element = (DepartmentUser) it2.next();
			depts.add(element.getDepartment());
			users.add(element.getEnterpriseUser());
		}
		for (Iterator iter = users.iterator(); iter.hasNext();) {
			EnterpriseUser user = (EnterpriseUser) iter.next();
			if (lNameFnameList != null
					&& !(lNameFnameList.contains(user.getFirstName().trim()
							+ " " + user.getLastName().trim())) ) {
				reassignAllDeptsList.add(new SelectItem(user
						.getUserWorkUnitSK().toString().trim(), user
						.getLastName()
						+ ", "
						+ user.getFirstName()
						+ " - "
						+ user.getUserWorkUnitSK().toString()));
				if (user.getCaseFilter() != null && !(assignedUserforReassign.equals(user.getUserWorkUnitSK().toString()))) {
					reassignDeptList.add(new SelectItem(user
							.getUserWorkUnitSK().toString().trim(), user
							.getLastName()
							+ ", "
							+ user.getFirstName()
							+ " - "
							+ user.getUserWorkUnitSK().toString()));
					
				}
			} 

		}
		for (Iterator iter = depts.iterator(); iter.hasNext();) {

			Department dept = (Department) iter.next();
			if (lNameFnameList != null
					&& !(lNameFnameList.contains(dept.getName()))) {

				reassignDeptList.add(new SelectItem(dept.getWorkUnitSK()
						.toString().trim(), dept.getName()));
				//Added by Madhu
				reassignAllDeptsList.add(new SelectItem(dept.getWorkUnitSK()
						.toString().trim(), dept.getName()));
				
			} 

		}
		sortReassignedToList(
                MaintainContactManagementUIConstants.SORT_TYPE_ASC,
                reassignDeptList);
        sortReassignedToList(
                MaintainContactManagementUIConstants.SORT_TYPE_ASC,
                reassignAllDeptsList);
		caseSearchDataBean.setReassignAll(reassignDeptList);
		caseSearchDataBean.setAllDeptUsersForReassign(reassignAllDeptsList);

		return reassignDeptList;

	}*/
//  Added Method for the defect ESPRD00342016 by infinite
// End - Unused methods commented as a part of code cleanup - Performance_Fix_UC-PGM-CRM-073_25OCT2011    
    
    public String  getAssignedUSer(String userId)
    {
		logger.debug("Inside AssignedUser");
		String assigntToUSer = " ";
		List deptUsers = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		Set depts = new HashSet();
		Set users = new HashSet();
		try {
			deptUsers = contactMaintenanceDelegate.getAllDepartmentUsers();
		} catch (LOBHierarchyFetchBusinessException e) {
			logger.error(e.getMessage(), e);
		}
		Iterator it2 =null;
		if (deptUsers != null){
		it2 = deptUsers.iterator();
		}
		if(it2 != null){
		while (it2.hasNext()) {
			DepartmentUser element = (DepartmentUser) it2.next();
			depts.add(element.getDepartment());
			users.add(element.getEnterpriseUser());
		}
		}
		for (Iterator iter = users.iterator(); iter.hasNext();) {
			EnterpriseUser user = (EnterpriseUser) iter.next();
			try {
				if (userId != null) {
					if (userId.equals(user.getUserWorkUnitSK().toString()
							.trim())) {
						assigntToUSer = user.getUserWorkUnitSK().toString();
					}
				}
			} catch (Exception e) {
				logger.debug(" inside Exception getAllDeptUsersForReassign ");

			}
		}
		return assigntToUSer;
}
    //Ended Defect ESPRD00342016 Code Modification by Infinite
    
  //Start - This method is redefined for the Heap Dump fix
    public String reassignCaseRecord(){
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
    	List caseSearchList = caseSearchDataBean.getSearchCaseList();
    	
    	CaseRecord caseRecord = null;
    	List caseRecordList = new ArrayList();
    	String userId = getLoggedInUser();
    	CaseDelegate caseDelegate = new CaseDelegate();
    	WorkUnit workUnit = null;
    	try{
			// Begin modified the code for the defect id ESPRD00736673_21DEC2011
    		CaseRecordSearchResultsVO caseRecordSearchResultsVO1 = null;
    		CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = null;
    		caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) caseSearchDataBean.getCaseRecordSearchCriteriaVO();
    		//FOR HEAP-DUMP ISSUE
    		List searchList = new ArrayList();
    		List searchList1 = new ArrayList();
    		//FOR HEAP-DUMP ISSUE
    		String allDeptReassign = caseSearchDataBean.getSelectedDeptAll();
    		if(allDeptReassign != null){ 
    			EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
        		caseRecordSearchCriteriaVO.setRowsPerPage(caseSearchDataBean.getCount());
        		enterpriseSearchResultsVO = caseDelegate.getCaseRecords(caseRecordSearchCriteriaVO);
    			//FOR HEAP-DUMP ISSUE
    			/*List caseRecordList1 = new ArrayList();*/
    			searchList = enterpriseSearchResultsVO.getSearchResults();
    			Iterator itr = searchList.iterator();
    			while(itr.hasNext())
    			{
    				CaseRecordSearchResultsVO caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) itr.next();
    				caseRecordSearchResultsVO.setDeptList(getDepartmentList(caseRecordSearchResultsVO.getDeptList()));	
    				List userList = caseRecordSearchResultsVO.getDeptList();
    				if(userList != null && userList.size() > 0)
    				{
    					for(Iterator iter = userList.iterator(); iter.hasNext();)
    					{
    						SelectItem userData = (SelectItem) iter.next();
    						if(caseSearchDataBean.getSelectedDeptAll()!=null && 
     		           				!caseSearchDataBean.getSelectedDeptAll().equalsIgnoreCase("") && 
     		           			caseSearchDataBean.getSelectedDeptAll()!="")
     		           		{
     		           			if(caseSearchDataBean.getSelectedDeptAll().equalsIgnoreCase(userData.getValue().toString()))
     			           		{
     			           			if(userData.getLabel()!=null && 
     			           					!userData.getLabel().equalsIgnoreCase("") && 
     										userData.getLabel()!="")
     			           			{
     			           			caseRecordSearchResultsVO.setSelectedDept(caseSearchDataBean.getSelectedDeptAll());
     			           			caseRecordSearchResultsVO.setAssignedTo(userData.getLabel());
     			           			}
     			           		}
     		           		}
    					}
    					searchList1.add(caseRecordSearchResultsVO);
    				}
    			//FOR HEAP-DUMP ISSUE
	    			/*CaseRecordSearchCriteriaVO caseRecordSearchCr1 = (CaseRecordSearchCriteriaVO) caseSearchDataBean
	    					.getCaseRecordSearchCriteriaVO();
	    			caseRecordSearchCr1.setRowsPerPage(0);
	    			caseRecordSearchCr1.setStartIndex(0);
	    			
	    				if(allDeptReassign != null &&
	    						!allDeptReassign.equalsIgnoreCase(ContactManagementConstants.EMPTY_STRING)){
	
	    					caseDelegate.reassignAllCaseRecordss(caseRecordSearchCr1,allDeptReassign.trim(),userId);
	    			}*/
    			}
    			for (Iterator iter = searchList1.iterator(); iter.hasNext();) {
    				CaseRecordSearchResultsVO caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) iter.next();
    				caseRecord = caseDelegate.getCaseDetails(caseRecordSearchResultsVO.getCaseSK());
    				caseRecord.setCaseSK(caseRecordSearchResultsVO.getCaseSK());
    				if(caseRecordSearchResultsVO.getSelectedDept() != null &&
    						!caseRecordSearchResultsVO.getSelectedDept().equalsIgnoreCase(ContactManagementConstants.EMPTY_STRING)){
    					workUnit = new WorkUnit();
    				workUnit.setWorkUnitSK(new Long(caseRecordSearchResultsVO.getSelectedDept()));
    				caseRecord.setCaseAssignedToWorkUnit(workUnit);

    				caseRecordList.add(caseRecord);
    				}
    			}
    				caseDelegate.reassignCaseRecordss(caseRecordList);

    				reassignCaseSearch();
					// Added for the defect id ESPRD00736670_21DEC2011
    				setErrorMessage(
    						MaintainContactManagementUIConstants.REASSIGN_SUCCESS_MSG,
    	                    null, MessageUtil.ENTMESSAGES_BUNDLE, null);
    				//caseSearchDataBean.setSuccess(true);  // Commented for the defect id ESPRD00736670_21DEC2011
    				caseSearchDataBean.setShowCaseSearchMsgFlag(true);  // Added for the defect id ESPRD00736670_21DEC2011
    		}
    		// End - modified the code for the defect id ESPRD00736673_21DEC2011
    	if(caseSearchList != null && allDeptReassign == null){
    		logger.debug(" caseSearchList : "+ caseSearchList.size());
    		for (Iterator iter = caseSearchList.iterator(); iter.hasNext();) {
				CaseRecordSearchResultsVO caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) iter.next();
//				Modified for Defect Id ESPRD00334555 starts

				caseRecord = caseDelegate.getCaseDetails(caseRecordSearchResultsVO.getCaseSK());
				
				
				caseRecord.setCaseSK(caseRecordSearchResultsVO.getCaseSK());
				if(caseRecordSearchResultsVO.getSelectedDept() != null &&
						!caseRecordSearchResultsVO.getSelectedDept().equalsIgnoreCase(ContactManagementConstants.EMPTY_STRING)){
					workUnit = new WorkUnit();
				workUnit.setWorkUnitSK(new Long(caseRecordSearchResultsVO.getSelectedDept()));
				caseRecord.setCaseAssignedToWorkUnit(workUnit);

				caseRecordList.add(caseRecord);
			}
		}
    		
    		caseDelegate.reassignCaseRecordss(caseRecordList);
 		
    		// added for defect ESPRD00542358 starts
    		List reassignedCaseList = caseSearchDataBean.getSearchCaseList();
    		Iterator itr3 = reassignedCaseList.iterator();
    		while(itr3.hasNext()){
    			CaseRecordSearchResultsVO caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) itr3.next();
    			logger.debug("===caseRecordSearchResultsVO.getSelectedDept()::"+caseRecordSearchResultsVO.getSelectedDept());
    			/*if(caseRecordSearchResultsVO.getSelectedDept()!=null && !caseRecordSearchResultsVO.getSelectedDept().equals(MaintainContactManagementUIConstants.EMPTY_STRING))
    				caseRecordSearchResultsVO.setAssignedTo(caseRecordSearchResultsVO.getSelectedDept());*/
    			
    			List userList = null;
                String [] splitData = null;
                String finalString = null;
                userList = caseRecordSearchResultsVO.getDeptList();
                if(userList != null && userList.size() >0)
                {
 	               for (Iterator iter = userList.iterator(); iter.hasNext();)
 	               {
 	               	SelectItem userData = (SelectItem) iter.next();
 	               	logger.debug("userData1::::::"+userData.getValue());
 		           		if(caseRecordSearchResultsVO.getSelectedDept()!=null && 
 		           				!caseRecordSearchResultsVO.getSelectedDept().equalsIgnoreCase("") && 
 		           			caseRecordSearchResultsVO.getSelectedDept()!="")
 		           		{
 		           			if(caseRecordSearchResultsVO.getSelectedDept().equalsIgnoreCase(userData.getValue().toString()))
 			           		{
 		           				logger.debug("Found the match:::::::::::");
 			           			if(userData.getLabel()!=null && 
 			           					!userData.getLabel().equalsIgnoreCase("") && 
 										userData.getLabel()!="")
 			           			{
 			           				
// 			           				splitData = userData.getLabel().split("-");
 			           			caseRecordSearchResultsVO.setAssignedTo(userData.getLabel());
 			           			}
 			           			
 			           		}
 		           		}
 	               }
                }
    		}
			// Added for the defect id ESPRD00736670_21DEC2011
    		setErrorMessage(
					MaintainContactManagementUIConstants.REASSIGN_SUCCESS_MSG,
                    null, MessageUtil.ENTMESSAGES_BUNDLE, null);
            //defects ESPRD00542358 ends
    		//Added for  defect ID ESPRD00361531 start   
    		if(caseSearchDataBean.isShowReassignResultsPage())
     		  {
    				//caseSearchDataBean.setSuccess(true);  // Commented for the defect id ESPRD00736670_21DEC2011
    			caseSearchDataBean.setShowCaseSearchMsgFlag(true); // Added for the defect id ESPRD00736670_21DEC2011
     		  }
    	}    	
    	}catch (CaseRecordUpdateDataException e) {
    		e.printStackTrace(); 
    		logger.error(e.getMessage(), e);
    		 setErrorMessage(
                    EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
    		 caseSearchDataBean.setShowReassignResultsPage(false);
    		 caseSearchDataBean.setShowCaseSearchMsgFlag(true); // Added for the defect id ESPRD00736670_21DEC2011
		}catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
    }
  //End - This method is redefined for the Heap Dump fix    
    
    /**
	 * @return String
	 */
    private String getLoggedInUser()
    {
        logger.info("getLoggedInUser");
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        //retrieving the logged in userid from databean if exists
        //else then from EnterpriseUserProfile
        String loggedInUser = caseSearchDataBean.getUserId();
        if(isNullOrEmptyField(loggedInUser)){
        	loggedInUser=getUserID();
        }

        logger.debug("User Id is " + loggedInUser);
        
        return loggedInUser;
    }
    /**
	 * This method is used to get the User ID.
	 * 
	 * @return String : User ID
	 */
    public String getUserID()
    {
        logger.info("getUserID");
        
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
        String userId = "robert1";

        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);

        if (eup != null && !isNullOrEmptyField(eup.getUserId()))
        {
            userId = eup.getUserId();
            caseSearchDataBean.setUserId(userId);
        }
        logger.debug("the useriD in controller is  :" + userId);
        
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
    private boolean isNullOrEmptyField(String inputField)
    {
        return (inputField == null || ContactManagementConstants.EMPTY_STRING
                .equalsIgnoreCase(inputField.trim()));
    }
    /**
	 * This method is used to get the Current Timestamp.
	 * 
	 * @return Timestamp : Current Timestamp.
	 */
   /* private Timestamp getTimeStamp()
    {
        logger.info("getTimeStamp");

        Calendar cal = Calendar.getInstance();

        return new Timestamp(cal.getTimeInMillis());
    }*/
	

	public boolean validateCorresFor(
			CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO) {
		boolean isValid = true;

		if( caserecordSearchCriteriaVO != null && !caserecordSearchCriteriaVO.getEntityId().equals("") ){

			if(!ContactManagementHelper.validateNumeric(caserecordSearchCriteriaVO.getEntityId()))
			{
			   isValid = true;
			   String id="ID";
               setErrorMessage(
                		EnterpriseMessageConstants.ERR_SW_INTEGER,
            					new Object[] {id},
            					MessageUtil.ENTMESSAGES_BUNDLE,
            					null);	
			}
		}
	
		if( caserecordSearchCriteriaVO != null && !caserecordSearchCriteriaVO.getAdditionalEntityID().equals("")){
			
			
			
						
			if(!ContactManagementHelper.validateNumeric(caserecordSearchCriteriaVO.getAdditionalEntityID()))
			{
			   isValid = true;
			   String id="ID";
               setErrorMessage(
                		EnterpriseMessageConstants.ERR_SW_INTEGER,
            					new Object[] {id},
            					MessageUtil.ENTMESSAGES_BUNDLE,
            					null);	
              
			}
		}
		
		
		return isValid;
	}
	
	
	public String doCaseSearchEntityAction() {

		
	
		logger.info("doCorrSearchEntityAction method called");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding("#{requestScope}").getValue(
						facesContext);

	
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = null;
		caseRecordSearchCriteriaVO = caseSearchDataBean.getCaseRecordSearchCriteriaVO();
		//ESPRD0033432_20112009
		caseRecordSearchCriteriaVO.setTarget("CaseRegarding");
		caseRecordSearchCriteriaVO.setAdditionalEntityID("");
		caseRecordSearchCriteriaVO.setAdditionalEntityIDType("");
		caseRecordSearchCriteriaVO.setAdditionalEntityType("");
		
		
		
		//Defectt ID ESPRD00332708 starts
		
	
		boolean isValid = true;
		
		
		//	Defectt ID ESPRD00332708 End
	
			
		if (isValid) {
			logger.debug("befor ipc for regarding");
			requestScope.put("CaseSearchVO",caseRecordSearchCriteriaVO);
		}

		
		
		return "success";
	}
	/**
	 * This method is used to call the entity search portlet on click on the search entity link of 
	 * Additional Case Entities section
	 * 
	 * @return
	 */
	public String doAdditionalCaseSearchEntityAction() {

		
		logger.info("doCorrSearchEntityAction method called");
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding("#{requestScope}").getValue(facesContext);


		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = null;
		caseRecordSearchCriteriaVO = caseSearchDataBean
				.getCaseRecordSearchCriteriaVO();
		
		
		caseRecordSearchCriteriaVO.setTarget("AdditionalCaseEntities");
		caseRecordSearchCriteriaVO.setEntityId("");
		caseRecordSearchCriteriaVO.setEntityIDType("");
		caseRecordSearchCriteriaVO.setEntityType("");
		
		
		//Defectt ID ESPRD00332708 starts
		
			boolean isValid = true;
		

		
		if (isValid) {
			logger.debug("befor ipc");
			//Modified for defect ESPRD00624909
			requestScope.put("CaseSearchVO", caseRecordSearchCriteriaVO);//UC-PGM-CRM-033_ESPRD00624909_09jun2011
			//requestScope.put("CaseSearchVO2", caseRecordSearchCriteriaVO);
		}

		return "success";
	}
	

	
	 /**
     * renders the detail part based on the filter.
     * 
     * @param event
     *            event.
     */
   
    public String onEntityTypeChange(ValueChangeEvent event)
    {
    	logger.debug("****onEntityTypeChange****");	

    	
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);

        if (!("".equals(event.getNewValue()))) {
			String selectedEntity = (String) event.getNewValue();
			logger.debug("****selectedEntity****" + selectedEntity);
			/*
			 * Changes made to remove the sesion object so that based on the
			 * Entity Type Entity ID Type drop down will display
			 */
			PortletSession portletSession = (PortletSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);
		        portletSession.setAttribute("CaseRecordSearchCriteriaVO", null);	
		        
			getCorrEntIDTypeValues(selectedEntity, "Entity");
			

		}//UC-PGM-CRM-020_ESPRD00433491_12MAR2010
		else{
			if(caseSearchDataBean.getEntityIDTypeList()!=null){
				caseSearchDataBean.getEntityIDTypeList().clear();
			}
		}//EOF UC-PGM-CRM-020_ESPRD00433491_12MAR2010
	
		return "success";
		
    }

	 /**
     * renders the detail part based on the filter.
     * 
     * @param event
     *            event.
     */
   
    public String onAdditionalEntityTypeChange(ValueChangeEvent event)
    {

    	
		

    	
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);

        if (!"".equals(event.getNewValue())) {//UC-PGM-CRM-033_ESPRD00624909_09jun2011
			String selectedEntity = (String) event.getNewValue();
			
			/*
			 * Changes made to remove the sesion object so that based on the
			 * Entity Type Entity ID Type drop down will display
			 */
			PortletSession portletSession = (PortletSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(false);
	        portletSession.setAttribute("CaseRecordSearchCriteriaVO", null);
			getCorrEntIDTypeValues(selectedEntity, "Additional");
			

		}//UC-PGM-CRM-020_ESPRD00433491_12MAR2010
		else{
			if(caseSearchDataBean.getAdditionalEntityIDTypeList()!=null 
					&& !caseSearchDataBean.getAdditionalEntityIDTypeList().isEmpty()){
				caseSearchDataBean.getAdditionalEntityIDTypeList().clear();
			}
		}		//EOF UC-PGM-CRM-020_ESPRD00433491_12MAR2010
		
		return "success";
		
    }
    
    /**
	 * Populating Entity ID Type values in Correspondence For section
	 * @param entityType
	 */
	public void getCorrEntIDTypeValues(String entityType, String entityFrame)
	{		
	
	    caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();

		/** ENTITY TYPE -- MEMBER */

		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_MEM,
				entityType)) {
			logger.info("Inside if loop for member ");

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_MEM);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type
			 * Member
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")){
					caseSearchDataBean
							.setEntityIDTypeList(cmEntityDOConvertor
									.getEntIDTypeReferenceData(
											FunctionalAreaConstants.GENERAL,
											sysListNumber));
				} else {
					caseSearchDataBean
					.setAdditionalEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				}
			}

		}

		/** ENTITY TYPE -- PROVIDER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_PROV,
				entityType)) {
			logger.info("Inside if loop for provider ");
			
			Long sysListNumber = new Long("1017");
			//commemted for the Cr ESPRD00703521
			/*ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_PROVIDER)*/
			
			/**
			 * Populates the Entity ID Type dropdown for Entity Type
			 * Provider
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.PROVIDER, sysListNumber) != null) {
				if (entityFrame.equals("Entity")){
					caseSearchDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.PROVIDER,
										sysListNumber));
				} else {
					caseSearchDataBean
					.setAdditionalEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.PROVIDER,
									sysListNumber));				
				}
					
			}

		}

		/** ENTITY TYPE -- UNENROLLED PROVIDER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_UNPROV,
				entityType)) {
			logger.debug("Inside if loop for unenrolled  provider ");
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_UNPROV);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type
			 * Unenrolled Provider
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")){
					caseSearchDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
				} else {
					caseSearchDataBean
						.setAdditionalEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));					
				}
			}

		}

		/** ENTITY TYPE -- TPL CARRIER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_TPL,
				entityType)) {
			logger.info("Inside if loop for TPL ");
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_TPL);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type TPL
			 * Carrier
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")){	
					caseSearchDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
				} else {
					caseSearchDataBean
						.setAdditionalEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				}
			}

		}

		/** ENTITY TYPE -- DISTRICT OFFICE */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_DO,
				entityType)) {
			logger.info("Inside if loop for DO ");
			

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_DO);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type
			 * District Office
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")){	
					caseSearchDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
				} else {
					caseSearchDataBean
						.setAdditionalEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));					
				}
			}

		} else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_CT,
				entityType)) {
			logger.info("Inside if loop for CT ");
			
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CT);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type
			 * County
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")){	
					caseSearchDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
				} else {
					caseSearchDataBean
						.setAdditionalEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				}
			}

		}
		//ESPRD00658417 defect Start

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_TD, entityType))
        {
			long SystemListTD=Long.valueOf("0181").longValue();
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, SystemListTD) != null) {
				if (entityFrame.equals("Entity")){	
					caseSearchDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										SystemListTD));
				} else {
					caseSearchDataBean
						.setAdditionalEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									SystemListTD));					
				}
			}
        }
		//ESPRD00658417 defect End
		else {
			/** ENTITY TYPE -- UNENROLLED MEMBER and other Entities */

			logger.info("Inside if loop for Unenrolled member ");

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type
			 * Other
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")){		
					caseSearchDataBean
						.setEntityIDTypeList(cmEntityDOConvertor
								.getEntIDTypeReferenceData(
										FunctionalAreaConstants.GENERAL,
										sysListNumber));
				} else {
					caseSearchDataBean
					.setAdditionalEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				}
			}

		}
	}
	
	 /**
     * This method is used to retreive valid values in the Provider dropdown
     * 
     * @return List
     */
    public final List getProvTypeReferenceData()
    {
        logger.debug("getProvTypeReferenceData");            
        List referenceList = new ArrayList();
        Map referenceMap = null;

        int referenceListSize = 0;
        List provTypeList = new ArrayList();

        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

        InputCriteria inputCriteriaEntityTyp = new InputCriteria();
        inputCriteriaEntityTyp
                .setFunctionalArea(FunctionalAreaConstants.PROVIDER);
        inputCriteriaEntityTyp
                .setElementName(ReferenceServiceDataConstants.P_TY_CD);

        referenceList.add(inputCriteriaEntityTyp);

        referenceDataSearch.setInputList(referenceList);

        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);

            referenceMap = referenceDataListVO.getResponseMap();

            referenceList = (List) referenceMap
                    .get(FunctionalAreaConstants.PROVIDER
                            + ProgramConstants.HASH_SEPARATOR
                            + ReferenceServiceDataConstants.P_TY_CD);
            referenceListSize = referenceList.size();
            for (int i = 0; i < referenceListSize; i++)
            {
                ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                        .get(i);

                String codesAndDesc = refVo.getValidValueCode() + "-"
                        + refVo.getShortDescription();
                provTypeList.add(new SelectItem(refVo.getValidValueCode(),
                        codesAndDesc));

            }

        }
        catch (ReferenceServiceRequestException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (SystemListNotFoundException e)
        {
            logger.error(e.getMessage(), e);
        }
       
       
        return provTypeList;
    }
    private String entityDetails;
    
	/**
	 * @return Returns the entityDetails.
	 */
	public String getEntityDetails() {
		/** Loading IPC data from Search Correpondence to Entity Search */
		PortletSession portletSession = (PortletSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		Object corrSrchCrtVOobj = portletSession
			.getAttribute("CaseRecordSearchCriteriaVO");
		logger.debug(" in condition..before " + corrSrchCrtVOobj);
		if (corrSrchCrtVOobj != null
				&& corrSrchCrtVOobj instanceof CaseRecordSearchCriteriaVO) {
			
			
			caseSearchDataBean.setCaseRecordSearchCriteriaVO((CaseRecordSearchCriteriaVO)corrSrchCrtVOobj);
			/* Defect ESPRD00448067 fix starts */
			if (caseSearchDataBean.getCaseRecordSearchCriteriaVO()
					.getEntityType() != null) {
				getCorrEntIDTypeValues(caseSearchDataBean
						.getCaseRecordSearchCriteriaVO().getEntityType(),
						"Entity");
			} else if (caseSearchDataBean.getCaseRecordSearchCriteriaVO()
					.getAdditionalEntityType() != null) {
				getCorrEntIDTypeValues(caseSearchDataBean
						.getCaseRecordSearchCriteriaVO().getAdditionalEntityType(),
						"Additional");
			}
		  /* Defect ESPRD00448067 fix End */
		}
		return "success";
	}
	/**
	 * @param entityDetails The entityDetails to set.
	 */
	public void setEntityDetails(String entityDetails) {
		this.entityDetails = entityDetails;
	}
	
	
	 public String  getAssigntToUSer(String userId)
	    {

	    logger.debug(" Inside getAssignttoUSer");
        String assigntToUSer = null;
        List deptUsers = null;

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
     
        Set depts = new HashSet();
        Set users = new HashSet();
        try {
            deptUsers = contactMaintenanceDelegate.getAllDepartmentUsers();
            logger.debug("after contact maintenance delegate call :"
                    + deptUsers);
            
        } catch (LOBHierarchyFetchBusinessException e) {
            e.printStackTrace();
        }
        Iterator it2 =null;
       if(deptUsers != null){
       it2 = deptUsers.iterator();
       }
       if(it2 !=null){ 
       while (it2.hasNext()) {
            DepartmentUser element = (DepartmentUser) it2.next();

            depts.add(element.getDepartment());

            users.add(element.getEnterpriseUser());
          }
        }
        for (Iterator iter = users.iterator(); iter.hasNext();)

        {

            EnterpriseUser user = (EnterpriseUser) iter.next();

            try {

                 if (userId != null) {

                    if (userId.equals(user.getUserWorkUnitSK().toString().trim())) {

                        
                        if (user.getMiddleName() != null)

                        {
                             assigntToUSer = user.getLastName().trim() + " ,"
                                    + user.getMiddleName().charAt(0) + ","
                                    + user.getFirstName().trim() + "-"
                                    + user.getUserID();
                             //   + user.getUserWorkUnitSK().toString();  Defect ESPRD00532001

                        } else {
                            
                           
                            assigntToUSer = user.getLastName().trim() + " ,"
                                    + user.getFirstName().trim() + "-"
                                    + user.getUserID();
                            		//+ user.getUserWorkUnitSK().toString(); Defect ESPRD00532001

                        }

                    }
                }

            } catch (Exception e) {
                
                e.printStackTrace();

            }

        }

        

        return assigntToUSer;

    }
	
/**
 * @return Returns the allDeptUsersForReassign.
 */

/**
 * This method is used to validate the requirefield of entityType, entity id ,
 * and entity id type of case regarding Entites if any one of the field is selected
 *  
 * @param caseRecordSearchCriteriaVO
 * @return
 */
/*private boolean validateEntityForCaseRegarding(CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO) {

	boolean isValid = true;		
	String entityID=caseRecordSearchCriteriaVO.getEntityId();
	String entityIDType=caseRecordSearchCriteriaVO.getEntityIDType();
	String entityType=caseRecordSearchCriteriaVO.getEntityType();
	if (((entityID != null && entityID.length() > MaintainContactManagementUIConstants.ZERO)
			|| (entityIDType != null && entityIDType.length() > MaintainContactManagementUIConstants.ZERO)
			|| (entityType != null && entityType.length() > MaintainContactManagementUIConstants.ZERO))) {
		
		if(entityID.length() == MaintainContactManagementUIConstants.ZERO){
			
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.ENTITY_ID_REQ,
        					MaintainContactManagementUIConstants.CASE_SEARCH_ENTITYID__ID,
        					MaintainContactManagementUIConstants.CASE_SEARCH_ENTITYID__ID,
        					null);	
			isValid = false;
		} else if( caseRecordSearchCriteriaVO != null && !caseRecordSearchCriteriaVO.getEntityId().equals("") ){

			if(!ContactManagementHelper.validateNumeric(caseRecordSearchCriteriaVO.getEntityId()))
			{
			   isValid = false;	               
               ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.ENTITY_ID_VAL,
					MaintainContactManagementUIConstants.CASE_SEARCH_ENTITYID__ID,
					MaintainContactManagementUIConstants.CASE_SEARCH_ENTITYID__ID,
        					null);	
			}
		}
		
		if(entityIDType.length() == MaintainContactManagementUIConstants.ZERO){
			
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.ENTITY_IDTYPE_REQ,
        					MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_ID_TYPE_ID,
        					MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_ID_TYPE_ID,
        					null);	
			isValid = false;
			
		}
		if(entityType.length() == MaintainContactManagementUIConstants.ZERO){
			
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.ENTITY_TYPE_REQ,
        					MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_TYPE_ID,
        					MaintainContactManagementUIConstants.CASE_SEARCH_ENTITY_TYPE_ID,
        					null);	
			isValid = false;
			
		}
	}
	
	return isValid;
	
}*/


/**
 * This method is used to validate the requirefield of entityType, entity id ,
 * and entity id type of addtional case Entites if any one of the field is selected 
 * 
 * @param caseRecordSearchCriteriaVO
 * @return
 */
/*private boolean validateEntityForAdditionalCaseEnitities(CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO) {
	
	boolean isValid = true;		
	String entityID=caseRecordSearchCriteriaVO.getAdditionalEntityID();
	String entityIDType=caseRecordSearchCriteriaVO.getAdditionalEntityIDType();
	String entityType=caseRecordSearchCriteriaVO.getAdditionalEntityType();
	if (((entityID != null && entityID.length() > MaintainContactManagementUIConstants.ZERO)
			|| (entityIDType != null && entityIDType.length() > MaintainContactManagementUIConstants.ZERO)
			|| (entityType != null && entityType.length() > MaintainContactManagementUIConstants.ZERO))) {
		
		if(entityID.length() == MaintainContactManagementUIConstants.ZERO){
			
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.ENTITY_ID_REQ,
        					MaintainContactManagementUIConstants.ADD_CASE_SEARCH_ENTITYID__ID,
        					MaintainContactManagementUIConstants.ADD_CASE_SEARCH_ENTITYID__ID,
        					null);	
			isValid = false;
		} else 	if( caseRecordSearchCriteriaVO != null && !caseRecordSearchCriteriaVO.getAdditionalEntityID().equals("")){
											
				if(!ContactManagementHelper.validateNumeric(caseRecordSearchCriteriaVO.getAdditionalEntityID()))
				{
				   isValid = false;					   
	               ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.ENTITY_ID_VAL,
						MaintainContactManagementUIConstants.ADD_CASE_SEARCH_ENTITYID__ID,
						MaintainContactManagementUIConstants.ADD_CASE_SEARCH_ENTITYID__ID,
            					null);	
				}					
				
			}
		if(entityIDType.length() == MaintainContactManagementUIConstants.ZERO){
			
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.ENTITY_IDTYPE_REQ,
        				MaintainContactManagementUIConstants.ADD_CASE_SEARCH_ENTITY_ID_TYPE_ID,
        				MaintainContactManagementUIConstants.ADD_CASE_SEARCH_ENTITY_ID_TYPE_ID,
        					null);	
			isValid = false;
			
		}
		if(entityType.length() == MaintainContactManagementUIConstants.ZERO){
			
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.ENITY_TYPE_REQUIRED,
        					MaintainContactManagementUIConstants.ADD_CASE_SEARCH_ENTITY_TYPE_ID,
        					MaintainContactManagementUIConstants.ADD_CASE_SEARCH_ENTITY_TYPE_ID,
        					null);	
			isValid = false;
			
		}
	}
	
	return isValid;
	
}*/

/**
 * This method fetches the InputCriteria object with the parmeters set for
 * functional area and reference name.
 * 
 * @param referenceDataConstant -
 *            Element name.
 * @param functionalArea -
 *            Functional Area code.
 * @return - InputCriteria
 */
private InputCriteria getInputCriteria(String referenceDataConstant, String functionalArea) {
    
	logger.info(" inside getInputCriteria ");
    InputCriteria inputCriteria = new InputCriteria();
    inputCriteria.setFunctionalArea(functionalArea);
    inputCriteria.setElementName(referenceDataConstant);
    
    return inputCriteria;
}


	/**
	 * Method to sort Reassign To List
	 * @param sortOrder
	 * @param dataList
	 */
	private void sortReassignedToList(
	        final String sortOrder, List dataList)
	{
	    Comparator comparator = new Comparator() {
	        public int compare(Object obj1, Object obj2) {
	            SelectItem data1 = (SelectItem) obj1;
	            SelectItem data2 = (SelectItem) obj2;
	            boolean ascending = false;
	            if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
	                    .equals(sortOrder)) {
	                ascending = true;
	            } else {
	                ascending = false;
	            }
	            if (null == data1.getLabel()) {
	                data1.setLabel("");
	            }
	            if (null == data2.getLabel()) {
	                data2.setLabel("");
	            }
	            return ascending ? data1.getLabel().compareTo(data2.getLabel())
	                    : data2.getLabel().compareTo(data1.getLabel());
	
	        }
	
	    };
	
	    Collections.sort(dataList, comparator);
	}



	 /**
     * This Method loads all the Valied Values.
     */
	//************************Performance Fix by Implementing Multi Thread Concept starts*****************
	public String getLoadValidValue() {
		
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
    	caseSearchDataBean.setCursorFocusId("EntityID");
	        List list = new ArrayList();
	        HashMap map = new HashMap();
	        caseSearchDataBean.setStatus( new ArrayList());
	        List responseList = new ArrayList();

	        ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
	        String businessUnitDesc = null; 
	       
	        String userId = getUserID();  
	        try {
	        	List buinessUnitDescs=delegate.getBuisnessUnitsDescs(userId);
	        	
			if (buinessUnitDescs != null) {
				if (buinessUnitDescs.size() == 1) {
	        			businessUnitDesc = (String)buinessUnitDescs.get(0);
	        			
				} else {
					businessUnitDesc = ContactManagementConstants.AllOthers;
				}
			} else {
	        		businessUnitDesc = ContactManagementConstants.AllOthers;
	        		
	        	}
	        } catch (LOBHierarchyFetchBusinessException lOBHierarchyFetchBusinessException){
			logger.error(lOBHierarchyFetchBusinessException.getMessage(),
					lOBHierarchyFetchBusinessException);
	        }
	        
	        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
	        ReferenceServiceDelegate referenceServiceDelegate = null;
	        ReferenceDataListVO referenceDataListVO = null;

		list.add(getInputCriteria(ReferenceServiceDataConstants.G_CASE_STAT_CD,
				FunctionalAreaConstants.GENERAL));

		list.add(getInputCriteria(ReferenceServiceDataConstants.G_CASE_PRTY_CD,
				FunctionalAreaConstants.GENERAL));
	       
	        //   Added by ICS CR 1875 (UC - 020)
	        
		// Added if Condition for Find_Bugs-FIX
		if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_BCCP)) {

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_BCCP,
					FunctionalAreaConstants.GENERAL));

		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_DDU)) {

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
					FunctionalAreaConstants.GENERAL));

		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_MCS)) {

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_MCS,
					FunctionalAreaConstants.GENERAL));

		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_Appeals)) {

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_APPL,
					FunctionalAreaConstants.GENERAL));

		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_Provider_Relations)
				|| businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_ProvRel)) {

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_PROVREL,
					FunctionalAreaConstants.GENERAL));

		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_ARS)) {

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ARS,
					FunctionalAreaConstants.GENERAL));

		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_Medicaid_Legal_Services)) { // Added
			// Valid
			// Value

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_PROVREL,
	        		FunctionalAreaConstants.GENERAL));
	    	
	       }
	       //else { // Commented for the Performance Fix 
		// and will not affect to the present functionality -
		// Performance_Fix_UC-PGM-CRM-073_25OCT2011

		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
	            		FunctionalAreaConstants.GENERAL));
	       //}// Commented for the Performance Fix 
		// and will not affect to the present functionality -
		// Performance_Fix_UC-PGM-CRM-073_25OCT2011

		// end

		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD,
				FunctionalAreaConstants.GENERAL));

		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_WORK_UNIT_TY_CD,
	                FunctionalAreaConstants.GENERAL));
	        
	        list.add(getInputCriteria(ReferenceServiceDataConstants.ENTITYIDTYPE_M,
	                FunctionalAreaConstants.GENERAL));
	        
		if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_ARS)) {

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_APL_TY_CD_C,
					FunctionalAreaConstants.GENERAL));
		} else {
			list.add(getInputCriteria(ReferenceServiceDataConstants.LOB_OTHERS,
					FunctionalAreaConstants.GENERAL));
		}

		list.add(getInputCriteria(ReferenceServiceDataConstants.R_LOB_CD,
				FunctionalAreaConstants.GENERAL));

	        //   End by ICS CR 1875 (UC - 020)
	        
	        referenceServiceDelegate = new ReferenceServiceDelegate();
	        referenceDataListVO = new ReferenceDataListVO();

	        referenceDataSearch.setInputList(list);

	        try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);
	        } catch (ReferenceServiceRequestException e) {
	            // TODO Auto-generated catch block
	            logger.error(e.getMessage(), e);
	        } catch (SystemListNotFoundException e) {
	            // TODO Auto-generated catch block
	            logger.error(e.getMessage(), e);
	        }

	        map = referenceDataListVO.getResponseMap();
		Class[] argtypes = new Class[] { Map.class, String.class, String.class };
		Executor[] executor = new Executor[8];

		executor[0] = call(this, "getValidData", new Object[] { map,
				ReferenceServiceDataConstants.G_CASE_STAT_CD,
				FunctionalAreaConstants.GENERAL }, argtypes);
		/*
		 * caseSearchDataBean.setStatus(getValidData(map,
		 * ReferenceServiceDataConstants.G_CASE_STAT_CD,
		 * FunctionalAreaConstants.GENERAL));
		 */

		executor[1] = call(this, "getValidData", new Object[] { map,
				ReferenceServiceDataConstants.G_CASE_PRTY_CD,
				FunctionalAreaConstants.GENERAL }, argtypes);
		/*
		 * caseSearchDataBean.setPriority(getValidData(map,
		 * ReferenceServiceDataConstants.G_CASE_PRTY_CD,
		 * FunctionalAreaConstants.GENERAL));
		 */

	        //    ADDED BY ICS GAP-1875(UC-020)
		if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_BCCP)) {
			executor[2] = call(this, "getValidData", new Object[] { map,
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_BCCP,
					FunctionalAreaConstants.GENERAL }, argtypes);

			/*
			 * caseSearchDataBean.setEntityType(getValidData(map,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_BCCP,
			 * FunctionalAreaConstants.GENERAL));
			 */
		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_DDU)) {
			executor[2] = call(this, "getValidData", new Object[] { map,
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseSearchDataBean.setEntityType(getValidData(map,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
			 * FunctionalAreaConstants.GENERAL));
			 */
		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_MCS)) {
			executor[2] = call(this, "getValidData", new Object[] { map,
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_MCS,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseSearchDataBean.setEntityType(getValidData(map,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_MCS,
			 * FunctionalAreaConstants.GENERAL));
			 */
		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_Appeals)) {
			executor[2] = call(this, "getValidData", new Object[] { map,
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_APPL,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseSearchDataBean.setEntityType(getValidData(map,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_APPL,
			 * FunctionalAreaConstants.GENERAL));
			 */
		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_Provider_Relations)
				|| businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_ProvRel)) {
			executor[2] = call(this, "getValidData", new Object[] { map,
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_PROVREL,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseSearchDataBean.setEntityType(getValidData(map,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_PROVREL,
			 * FunctionalAreaConstants.GENERAL));
			 */
		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_ARS)) {
			executor[2] = call(this, "getValidData", new Object[] { map,
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ARS,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseSearchDataBean.setEntityType(getValidData(map,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ARS,
			 * FunctionalAreaConstants.GENERAL));
			 */

		} else if (businessUnitDesc != null
				&& businessUnitDesc
						.equalsIgnoreCase(ContactManagementConstants.businessUnit_Medicaid_Legal_Services)) { // Added
			executor[2] = call(this, "getValidData", new Object[] { map,
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_PROVREL,
					FunctionalAreaConstants.GENERAL }, argtypes); // Valid
			/*
			 * caseSearchDataBean.setEntityType(getValidData(map,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_PROVREL,
			 * FunctionalAreaConstants.GENERAL));
			 */

		} else {
			executor[2] = call(this, "getValidData", new Object[] { map,
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
					FunctionalAreaConstants.GENERAL }, argtypes);
			/*
			 * caseSearchDataBean.setEntityType(getValidData(map,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
			 * FunctionalAreaConstants.GENERAL));
			 */
		}
		// Added the below line of code for Perfomance Fix -
		// Performance_Fix_UC-PGM-CRM-073_25OCT2011

		executor[3] = call(this, "getValidDataNamesDesc", new Object[] { map,
				ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
				FunctionalAreaConstants.GENERAL }, argtypes);
		/*
		 * caseSearchDataBean.setEntityTypeMap(getValidDataNamesDesc(map,
		 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
		 * FunctionalAreaConstants.GENERAL));
		 */

		executor[4] = call(this, "getValidData", new Object[] { map,
				ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD,
				FunctionalAreaConstants.GENERAL }, argtypes);
		/*
		 * caseSearchDataBean.setBusinessGroup(getValidData(map,
		 * ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD,
		 * FunctionalAreaConstants.GENERAL));
		 */

		executor[5] = call(this, "getValidData", new Object[] { map,
				ReferenceServiceDataConstants.G_WORK_UNIT_TY_CD,
				FunctionalAreaConstants.GENERAL }, argtypes);
		/*
		 * caseSearchDataBean.setDepartments(getValidData(map,
		 * ReferenceServiceDataConstants.G_WORK_UNIT_TY_CD,
		 * FunctionalAreaConstants.GENERAL));
		 */

		executor[6] = call(this, "getValidDataNames", new Object[] { map,
				ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD,
				FunctionalAreaConstants.GENERAL }, argtypes);
		/*
		 * caseSearchDataBean.setBusinessGroupList(getValidDataNames(map,
		 * ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_TY_CD,
		 * FunctionalAreaConstants.GENERAL));
		 */

		executor[7] = call(this, "getValidData", new Object[] { map,
				ReferenceServiceDataConstants.R_LOB_CD,
				FunctionalAreaConstants.GENERAL }, argtypes);
		/*
		 * caseSearchDataBean.setLobVVList(getValidData(map,
		 * ReferenceServiceDataConstants.R_LOB_CD,
		 * FunctionalAreaConstants.GENERAL));
		 */

		try {
			caseSearchDataBean.setStatus((List) executor[0].get());
			caseSearchDataBean.setPriority((List) executor[1].get());
			caseSearchDataBean.setEntityType((List) executor[2].get());
			caseSearchDataBean.setEntityTypeMap((Map) executor[3].get());
			caseSearchDataBean.setBusinessGroup((List) executor[4].get());
			caseSearchDataBean.setDepartments((List) executor[5].get());
			caseSearchDataBean.setBusinessGroupList((Map) executor[6].get());
			caseSearchDataBean.setLobVVList((List) executor[7].get());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseList.add(new SelectItem(
				MaintainContactManagementUIConstants.TRUE,
				MaintainContactManagementUIConstants.YESLABEL));
		responseList.add(new SelectItem(
				MaintainContactManagementUIConstants.FALSE,
				MaintainContactManagementUIConstants.NOLABEL));
	        caseSearchDataBean.setResponseExistsList(responseList);
	        getUsersList();
	        //getAllHierarchies();
	        //caseType();
	        caseSearchDataBean.setValidValueFlag(false);
		return loadValidValue;
	}
	//************************Performance Fix by Implementing Multi Thread Concept ENDS*****************
	/**
	 * @param loadValidValue The loadValidValue to set.
	 */
	public void setLoadValidValue(String loadValidValue) {
		
	}
	
	/**
     * @param map -
     *            Map object containing the area code and element name.
     * @param referenceDataConstant -
     *            Element name.
     * @param functionalArea -
     *            Functional Area code.
     * @return List - with valid values populated.
     */
    private List getValidData(Map map, String referenceDataConstant, String functionalArea) {
        List validList = new ArrayList();
        String validValueCodeDesc = null;
        List validValues = (List) map.get(functionalArea + "#" + referenceDataConstant);
        validList.add(new SelectItem("", "Please Select One"));
        int validValuesSize = validValues.size();
        for (int i = 0; i < validValuesSize; i++) {
            ReferenceServiceVO refVo = (ReferenceServiceVO) validValues.get(i);
            validValueCodeDesc = refVo.getValidValueCode() + "-" + refVo.getShortDescription();
            validList.add(new SelectItem(refVo.getValidValueCode(), validValueCodeDesc));
            
        }
        return validList;
    }
    
    private Map getValidDataNames(Map map, String referenceDataConstant, String funcitionalArea) {
    	List validValues = (List) map.get(funcitionalArea+ '#'+ referenceDataConstant);
    	Map mapBusiness = new HashMap();
    	for (int i = 0; i < validValues.size();i ++){
    		ReferenceServiceVO refVO = (ReferenceServiceVO) validValues.get(i);
    		mapBusiness.put(refVO.getShortDescription(),refVO.getValidValueCode());
    	}
    	return mapBusiness;
    }
	
	// Begin - Added new method for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
	/**
	* This method is used to get the entityType code and short description
	* @return mapBusiness
	*/
    private Map getValidDataNamesDesc(Map map, String referenceDataConstant, String funcitionalArea) {
    	List validValues = (List) map.get(funcitionalArea+ '#'+ referenceDataConstant);
    	Map mapBusiness = new HashMap();
    	for (int i = 0; i < validValues.size();i ++){
    		ReferenceServiceVO refVO = (ReferenceServiceVO) validValues.get(i);
    		mapBusiness.put(refVO.getValidValueCode(),refVO.getValidValueCode()+"-"+refVO.getShortDescription());
    	}
    	return mapBusiness;
    }
	// End - Added new method for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011
    /**
     * @return Returns the facesContext.
     */
    public void getUsersList() {
        logger.debug(" ********* getUsersList ********");
        caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
//        CaseDelegate caseDelegate = new CaseDelegate();
        CMDelegate cMDelegate = new CMDelegate();
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        List userData = null;
        List users = new ArrayList();
        List users1 = new ArrayList();
        int size = 0;
        String desc = null;
        users.add(new SelectItem("", "Please Select One"));
        users1.add(new SelectItem("", "Please Select One"));
        HashMap usersMap = new HashMap();
        try {
        	caseSearchDataBean.getAssignedTo().clear();
        	caseSearchDataBean.getCreatedBy().clear();
        	caseSearchDataBean.getAdvCreatedBy().clear();
        	//Implementing API for EnterpriseUser object
            userData = cMDelegate.getUserDetails();//caseDelegate.getAllUsers();
            caseSearchDataBean.getAssignedTo().add(new SelectItem("", "Please Select One"));
            caseSearchDataBean.getCreatedBy().add(new SelectItem("", "Please Select One"));
            caseSearchDataBean.getAdvCreatedBy().add(new SelectItem("", "Please Select One"));
            if (!userData.isEmpty()) {
                size = userData.size();
                for (int i = 0; i < size; i++) {
					Object[] userDetails = (Object[]) userData.get(i);
					assignEmptyIfNull(userDetails);
					// if (enterpriseUser.getFirstName() != null) {
					// Added By ICS
					desc = userDetails[0].toString()
							+ ContactManagementConstants.COMMA_SEPARATOR
							+ ContactManagementConstants.SPACE_STRING
							+ userDetails[1].toString()
							+ ContactManagementConstants.SPACE_STRING
							+ ContactManagementConstants.HYPHEN_SEPARATOR
							+ ContactManagementConstants.SPACE_STRING
							+ userDetails[2].toString();
					// Ended By ICS
					caseSearchDataBean.getAssignedTo().add(
							new SelectItem(userDetails[3].toString(), desc));
					caseSearchDataBean.getCreatedBy().add(
							new SelectItem(userDetails[3].toString(), desc));
					caseSearchDataBean.getAdvCreatedBy().add(
							new SelectItem(userDetails[2].toString(), desc));
					usersMap.put(userDetails[3].toString(), desc);
					// }
				}
            }

            caseSearchDataBean.setUsersMap(usersMap);
        } catch (CaseRecordFetchBusinessException e) {
            logger.error(e.getMessage(), e);
        } catch (EnterpriseBaseBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    /**
     * This method is used to retreive values in the ReportingUnit and
     * BusinessUnit dropdown
     */
    public void getAllHierarchies() {
        logger.debug("****** getAllHierarchies *******");
        List repUnitsList = null;
        List businessUnitsList = null;
        List deptUnitsList = null;
        List emptyList = new ArrayList();

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        try {
			repUnitsList = contactMaintenanceDelegate.getLobHierarchyDetails(
					emptyList, ContactManagementConstants.TWO);
			caseSearchDataBean.setReportingUnit(new ArrayList());
			caseSearchDataBean.getReportingUnit().clear();
			caseSearchDataBean.getReportingUnit().add(new SelectItem("", "Please Select One"));
			Iterator itr1 = repUnitsList.iterator();
			while (itr1.hasNext()) {
				LineOfBusinessHierarchy repUnit = (LineOfBusinessHierarchy) itr1
						.next();
				caseSearchDataBean.getReportingUnit().add(
						new SelectItem(repUnit.getLineOfBusinessHierarchySK()
								.toString(), repUnit
								.getLobHierarchyDescription()));

			}
			
			businessUnitsList = contactMaintenanceDelegate
					.getLobHierarchyDetails(emptyList,
							ContactManagementConstants.THREE);
			caseSearchDataBean.setBusinessUnit(new ArrayList());
			caseSearchDataBean.getBusinessUnit().clear();
			caseSearchDataBean.getBusinessUnit().add(new SelectItem("", "Please Select One"));
			Iterator itr2 = businessUnitsList.iterator();
			while (itr2.hasNext()) {
				LineOfBusinessHierarchy businUnit = (LineOfBusinessHierarchy) itr2
						.next();
				caseSearchDataBean.getBusinessUnit().add(
						new SelectItem(businUnit.getLineOfBusinessHierarchySK()
								.toString(), businUnit
								.getLobHierarchyDescription()));

			}

			deptUnitsList = contactMaintenanceDelegate.getLobHierarchyDetails(
					emptyList, ContactManagementConstants.FOUR);
			caseSearchDataBean.getDepartments().clear();
			caseSearchDataBean.getDepartments().add(new SelectItem("", "Please Select One"));
			Iterator itr3 = deptUnitsList.iterator();
			while (itr3.hasNext()) {
				LineOfBusinessHierarchy deptUnit = (LineOfBusinessHierarchy) itr3
						.next();
				caseSearchDataBean.getDepartments().add(
						new SelectItem(deptUnit.getLineOfBusinessHierarchySK()
								.toString(), deptUnit
								.getLobHierarchyDescription()));
			}
		} catch (LOBHierarchyFetchBusinessException lobExe) {
            lobExe.getMessage();
        } catch (Exception exe) {
        	  logger.debug("Exception caught in getAllHierarchies--DataBean" + exe.getMessage());


          
        }

    }
    
    
    /**
     * @return Returns the caseType.
     */
    public List caseType() {

    	String userID = getUserID();
    	
    	caseSearchDataBean.setCaseType(new ArrayList());
    	caseSearchDataBean.getCaseType().clear();
        CaseType caseType = null;
        List caseList = null;
        
       // Map caseTypeSKAndShortDes = new HashMap();   /* FIND BUGS_FIX*/
      //  Map caseTypeAndBusinessUnit = new HashMap();  /* FIND BUGS_FIX*/
        ContactMaintenanceDelegate maintenanceDelegate = new ContactMaintenanceDelegate();
        try
        {
        	
        	caseSearchDataBean.getCaseType().add(new SelectItem("", "Please Select One"));
            caseList = maintenanceDelegate.getCaseTypes(userID);
           
            if (caseList != null && !caseList.isEmpty())
            {
             logger.debug("Total Case Type List size is $$ " + caseList.size());
            	int caseTypeListSize = 0;
                caseTypeListSize = caseList.size();
                for (int i = 0; i < caseTypeListSize; i++)
                {
                    caseType = (CaseType) caseList.get(i);
                    if (caseType != null
                            && caseType.getCaseTypeSK() != null
                            && (caseType.getDescription() != null && !caseType
                                    .getDescription().equals("")))
                    {
                        
                    	caseSearchDataBean.getCaseType().add(new SelectItem(caseType
                                .getCaseTypeSK(), caseType
                                .getDescription()));

                    }
                }
                sortCaseType(caseSearchDataBean.getCaseType());
            }
           
        }
        catch (CaseTypeFetchBusinessException e)
        {
            logger.error("Error occured at getCaseTypes()");
        }
             return null;
    }
    
    
    private void sortCaseType(List caseList) 
    {
	 
		Comparator selectItemComparator = new Comparator() 
		{
			public int compare(Object obj1, Object obj2) 
			{
				String first = null;
				String second = null;

				SelectItem s1 = (SelectItem) obj1;
				SelectItem s2 = (SelectItem) obj2;
				
				boolean ascending = true;

				if (s1.getLabel() != null && s2.getLabel() != null) 
				{
					first = s1.getLabel().toLowerCase();
					second = s2.getLabel().toLowerCase();
					

					return first.compareTo(second);
				}
				return 0;
				
			}

		};
		
		Collections.sort(caseList, selectItemComparator);
	}
    public List getDepartmentList(List dynamiclistVo)
    {
    	List deptListTemp = new ArrayList();
    	if(dynamiclistVo != null && dynamiclistVo.size() >0)
        { 
    		List userList = null;
        	logger.debug("no.of users to whom we can assign the case"+dynamiclistVo.size()); 
        	for (Iterator iter = dynamiclistVo.iterator(); iter.hasNext();)
            {
        		Object[] userData = (Object[]) iter.next();
        		String userDesc = ContactManagementConstants.EMPTY_STRING;
        		String userID = null;
                
                if(userData[2] != null)
                {
               		userDesc = userData[2].toString();
                }
                if((userData[2] != null) &&
                		(userData[1] != null))
                {
                	userDesc = userDesc 
								+ ContactManagementConstants.COMMA_SEPARATOR
								+ ContactManagementConstants.SPACE_STRING;	
                }
                if(userData[1] != null)
                {
                	userDesc = userDesc + userData[1];
                }
                if(userData[3] != null)
                {
                	userID = userData[3].toString(); 
                }
                
                /*if(!getUserID().equalsIgnoreCase(userID))
                {
                	if(userDesc!=null && userDesc.length()>0 && userData[0] != null && userID != null)
                    {
                		
                   		SelectItem selectItem = new SelectItem(userData[0].toString().trim(),
                   				userDesc + ContactManagementConstants.HYPHEN_SEPARATOR + userID);
                   		logger.debug("++selectItem--"+selectItem);
                   		deptListTemp.add(selectItem);
                		}
                    }*/
                if(userDesc!=null && userDesc.length()>0 && userData[0] != null)
                {
                	logger.debug("++userData[0]"+userData[0]);
            		if(userID != null)
            		{
                		
                       		SelectItem selectItem = new SelectItem(userData[0].toString().trim(),
                       				userDesc + ContactManagementConstants.HYPHEN_SEPARATOR + userID);
                       		deptListTemp.add(selectItem);
                		
            		}else{
            			
                       		SelectItem selectItem = new SelectItem(userData[0].toString().trim(),userDesc);
                       		deptListTemp.add(selectItem);
                		}
            		}

                }
            }
    	
    	return deptListTemp;
    }
    
    // Begin - Unused methods commented as a part of code cleanup - Performance_Fix_UC-PGM-CRM-073_25OCT2011    
    /*public List getAllUsersForReassign2(List deptUsers)
    {
		
		//List reassignDeptList = new ArrayList();
		List reassignAllDeptsList = new ArrayList();
	
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		ContactMaintenanceDelegate contactMaintenanceDelegate =new ContactMaintenanceDelegate();
		Set depts = new HashSet();
		//Set users = new HashSet();  
		if(deptUsers!=null){
			logger.debug("++++++++++deptUsers=="+deptUsers.size());
			Iterator it2 = deptUsers.iterator();
			DepartmentUser element = null;
			while (it2.hasNext()) {
				element = (DepartmentUser) it2.next();
				if(element.getDepartment().getLineOfBusinessHierarchy()!=null && element.getDepartment().getLineOfBusinessHierarchy().getVoidIndicator()!=null && !element.getDepartment().getLineOfBusinessHierarchy().getVoidIndicator().booleanValue())
					depts.add(element.getDepartment());
				if(element.getEnterpriseUser().getAccountActiveIndicator()!=null && element.getEnterpriseUser().getAccountActiveIndicator().booleanValue())
					users.add(element.getEnterpriseUser());
			}
			EnterpriseUser user = null;
			StringBuffer sbName = null;
			int a=0;
			List activUserList = new ArrayList();
			try {
				activUserList = contactMaintenanceDelegate.getActiveUsers();
			} catch (EnterpriseBaseBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Iterator iter = activUserList.iterator(); iter.hasNext();) {
				user = (EnterpriseUser) iter.next();
				a++;
				if (lNameFnameList != null
						&& !(lNameFnameList.contains(String.valueOf(user.getFirstName()).trim()
								+ " " + String.valueOf(user.getLastName()).trim())) ) {
					
					sbName= new StringBuffer();
                    if(user.getLastName()!=null && !MaintainContactManagementUIConstants.EMPTY_STRING.equals(user.getLastName())){
                    	sbName.append(user.getLastName()).append(",").append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
                    }
                    sbName.append(user.getFirstName()).append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE).append(MaintainContactManagementUIConstants.HYPHEN);
                    sbName.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE).append(user.getUserID() getUserWorkUnitSK()); // Defect ESPRD00544111
                    
					reassignAllDeptsList.add(new SelectItem(user
							.getUserWorkUnitSK().toString(), sbName.toString()));
					if (user.getCaseFilter() != null && !(assignedUserforReassign.equals(user.getUserWorkUnitSK().toString()))) {
						reassignDeptList.add(new SelectItem(user
								.getUserWorkUnitSK().toString(), sbName.toString()));
						
					}
				} 

			}
			logger.debug("++a=="+a);
			Department dept = null;
			int b=0;
			for (Iterator iter = depts.iterator(); iter.hasNext();) {
				b++;
				dept = (Department) iter.next();
				if (lNameFnameList != null
						&& !(lNameFnameList.contains(dept.getName()))) {

					reassignDeptList.add(new SelectItem(dept.getWorkUnitSK()
							.toString(), dept.getName()));

					reassignAllDeptsList.add(new SelectItem(dept.getWorkUnitSK()
							.toString(), dept.getName()));
					
				} 

			}
			logger.debug("++b=="+b);
		}
		
//		sortReassignedToList(
//                MaintainContactManagementUIConstants.SORT_TYPE_ASC,
//                reassignDeptList);
        sortReassignedToList(
                MaintainContactManagementUIConstants.SORT_TYPE_ASC,
                reassignAllDeptsList);
//		caseSearchDataBean.setReassignAll(reassignDeptList);
		caseSearchDataBean.setAllDeptUsersForReassign(reassignAllDeptsList);

		return reassignAllDeptsList;

	}*/
    // End - Unused methods commented as a part of code cleanup - Performance_Fix_UC-PGM-CRM-073_25OCT2011    
    
  // Begin - Added this block of code for the Datascroller
    /**
     * This method is used to display the next page
     * @return next
     */
    public String nextOne(){
		CaseSearchDataBean caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		caseSearchDataBean.setEndRecord(caseSearchDataBean.getEndRecord() + caseSearchDataBean.getItemsPerPage() + 10);
		caseSearchDataBean.setStartRecord(caseSearchDataBean.getEndRecord() - (caseSearchDataBean.getItemsPerPage()));
		caseSearchDataBean.setStartRecordNo(caseSearchDataBean.getStartRecord() + 1); //modified for the defect ESPRD00709107_10OCT2011
		if(caseSearchDataBean.isReassignFlag() == true)
		{
			reassignCaseSearch();
		}
		else
		{
			searchCase();
		}
		if (caseSearchDataBean.getEndRecord() < caseSearchDataBean.getCount()) {
			caseSearchDataBean.setShowNext(true);
			caseSearchDataBean.setShowPrevious(true);
			caseSearchDataBean.setShowNextOne(false);
		} else {
			caseSearchDataBean.setEndRecord(caseSearchDataBean.getCount());
			caseSearchDataBean.setShowNext(false);
			caseSearchDataBean.setShowPrevious(true);
			caseSearchDataBean.setShowNextOne(false);
		}
		caseSearchDataBean.setCurrentPage(caseSearchDataBean.getCurrentPage()+2);
		return ProgramConstants.NEXT;
	}
	  // End - Added this block of code for the Data scroller
    
	// Begin - this block of code is re-modified for the Data scroller Fix
    // Begin - This block of code is defined for the user defined data scroller
    /**
     * This method is used to display the next page
     * @return next
     */
    public String next(){
		CaseSearchDataBean caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		caseSearchDataBean.setEndRecord(caseSearchDataBean.getEndRecord() + caseSearchDataBean.getItemsPerPage());
		caseSearchDataBean.setStartRecord(caseSearchDataBean.getEndRecord() - (caseSearchDataBean.getItemsPerPage()));
		caseSearchDataBean.setStartRecordNo(caseSearchDataBean.getStartRecord() + 1);

		if(caseSearchDataBean.isReassignFlag() == true)
		{
			reassignCaseSearch();
		}
		else
		{
			searchCase();
		}
		if (caseSearchDataBean.getEndRecord() < caseSearchDataBean.getCount()) {
			caseSearchDataBean.setShowNext(true);
			caseSearchDataBean.setShowPrevious(true);
			caseSearchDataBean.setShowNextOne(false);
		} else {
			caseSearchDataBean.setEndRecord(caseSearchDataBean.getCount());
			caseSearchDataBean.setShowNext(false);
			caseSearchDataBean.setShowPrevious(true);
			caseSearchDataBean.setShowNextOne(false);
		}
		caseSearchDataBean.setCurrentPage(caseSearchDataBean.getCurrentPage()+1);
		return ProgramConstants.NEXT;
	}
    
    /**
     * This method is used to display the previous page
     * @return previous
     */
    public String previous(){
		CaseSearchDataBean caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		int numberOfPages;
		if(caseSearchDataBean.getCount()%caseSearchDataBean.getItemsPerPage() > 0)
			numberOfPages = caseSearchDataBean.getCount()/caseSearchDataBean.getItemsPerPage()+1;
		else
			numberOfPages = caseSearchDataBean.getCount()/caseSearchDataBean.getItemsPerPage();
		if(caseSearchDataBean.getCurrentPage() == numberOfPages)
		{
			int endRecord = caseSearchDataBean.getCount()%caseSearchDataBean.getItemsPerPage();
			caseSearchDataBean.setEndRecord(caseSearchDataBean.getEndRecord()-endRecord);
			//Modified for the defect ESPRD00874242
			if(endRecord==0){
				caseSearchDataBean.setEndRecord(caseSearchDataBean.getEndRecord()-10);
			}
			caseSearchDataBean.setStartRecord(caseSearchDataBean.getEndRecord()-caseSearchDataBean.getItemsPerPage());
			caseSearchDataBean.setStartRecordNo(caseSearchDataBean.getStartRecord() + 1);
		}
		else {
			caseSearchDataBean.setEndRecord(caseSearchDataBean.getEndRecord()-caseSearchDataBean.getItemsPerPage());
			caseSearchDataBean.setStartRecord(caseSearchDataBean.getEndRecord()-caseSearchDataBean.getItemsPerPage());
			caseSearchDataBean.setStartRecordNo(caseSearchDataBean.getStartRecord() + 1);
		}
		if(caseSearchDataBean.isReassignFlag() == true)
		{
			reassignCaseSearch();
		}
		else
		{
			searchCase();
		}
		
		if (caseSearchDataBean.getStartRecord() > 1 ) {
			caseSearchDataBean.setShowNext(true);
			caseSearchDataBean.setShowPrevious(true);
			caseSearchDataBean.setShowNextOne(false);
		} else {
			caseSearchDataBean.setShowNext(true);
			caseSearchDataBean.setShowPrevious(false);
			caseSearchDataBean.setShowNextOne(true);
		}
		if (caseSearchDataBean.getCurrentPage() != 1) {
			caseSearchDataBean.setCurrentPage(caseSearchDataBean.getCurrentPage()-1);
		}
		//for the defect ESPRD00874242
		/*if the no of records in between 10 and 20 then on click of previous 
		 * page number 3 was displaying to the user. */ 
		if (caseSearchDataBean.getCount() <= (2 * caseSearchDataBean
				.getItemsPerPage())) {
			caseSearchDataBean.setShowNextOne(false);
		}
		return ProgramConstants.PREVIOUS;
	}
    
    /**
     * This method is used to display the previous page for the defect ESPRD00874242
     * @return previous
     */
    public String previousOne(){
    	CaseSearchDataBean caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		
    	int endRecord = caseSearchDataBean.getCount()%caseSearchDataBean.getItemsPerPage();
		caseSearchDataBean.setEndRecord(caseSearchDataBean.getEndRecord()-endRecord);
		caseSearchDataBean.setEndRecord(caseSearchDataBean.getEndRecord()-10);
		if(endRecord==0){
			caseSearchDataBean.setEndRecord(caseSearchDataBean.getEndRecord()-10);
		}
		caseSearchDataBean.setStartRecord(caseSearchDataBean.getEndRecord()-caseSearchDataBean.getItemsPerPage());
		caseSearchDataBean.setStartRecordNo(caseSearchDataBean.getStartRecord() + 1);
		if(caseSearchDataBean.isReassignFlag() == true)
		{
			reassignCaseSearch();
		}
		else
		{
			searchCase();
		}
		
		if (caseSearchDataBean.getStartRecord() > 1 ) {
			caseSearchDataBean.setShowNext(true);
			caseSearchDataBean.setShowPrevious(true);
			caseSearchDataBean.setShowNextOne(false);
		} else {
			caseSearchDataBean.setShowNext(true);
			caseSearchDataBean.setShowPrevious(false);
			caseSearchDataBean.setShowNextOne(true);
		}
		if (caseSearchDataBean.getCurrentPage() != 1) {
			caseSearchDataBean.setCurrentPage(caseSearchDataBean.getCurrentPage()-2);
		}
		
		return ProgramConstants.PREVIOUS;
	}
    //End - This block of code is defined for the user defined data scroller
	// End - this block of code is modified again for the Data scroller Fix
    
	// Begin - Added new method for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011	
    /**
	 * This method is used to retreive all users and Departments for Reassign
	 * all
	 */
	protected void getAllDeptUsersForReassign() {
		ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
		CaseSearchDataBean caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		/**
		 * Modified during the CR ESPRD00798895 as Case Filter needs to be applied for the Active users
		 * to the Reassign All drop down of Reassign Case page
		 */
		caseSearchDataBean.setAllDeptUsersForReassign(contactManagementHelper
				.getAllActiveUsersAndDeptsCase());
	}
	// End - Added new method for the Perfomance Fix - Performance_Fix_UC-PGM-CRM-073_25OCT2011		
	//************************Performance Fix by Implementing Multi Thread Concept starts*****************
    private Executor call(Object object, String methodName, Object[] args,
			Class[] argtypes) {
		Executor e = new Executor(object, methodName, args, argtypes);
		e.start();
		return e;
	}
  //************************Performance Fix by Implementing Multi Thread Concept ENDS*****************
    
    /**
     * To get the description based on code.
     * 
     * @param code
     *            Holds the code valus.
     * @param vvList
     *            Holds the List of valid values.
     * @return String
     */
    // Begin - Added new method for the performance fix.
	public String getDescriptionFromVV(String code, List vvList) {
		//for ESPRD00761618 defect starts.
		String desc = ContactManagementConstants.EMPTY_STRING;
		try {
			if (vvList == null) {
				if (code != null)
					return code;
				else
					return ContactManagementConstants.EMPTY_STRING;
			}
			
			String valueStr = ContactManagementConstants.EMPTY_STRING;
			int size = vvList.size();
			for (int i = 0; i < size; i++) {
				SelectItem selectItem = (SelectItem) vvList.get(i);
				valueStr = "";
				if (selectItem != null) {
					valueStr = (String) selectItem.getValue();
					if (valueStr != null && valueStr.equals(code)) {
						desc = selectItem.getLabel();
						break;
					}
				}
			}
		} catch (Exception excep) {
			excep.printStackTrace();
		}
		//for ESPRD00761618 defect ends.
		return desc;
	}
    // End - Added new method for the performance fix.
    
    /** The recieved array of object holds any
	 *  null value then it will be replaced with
	 *  empty string('').
	 * */
	private void assignEmptyIfNull(Object[] object){
		if(object!=null && object.length!=0){
			for(int i=0;i<object.length;i++){
				if(object[i]==null){
					object[i]=ContactManagementConstants.EMPTY_STRING;
				}
			}
		}
	}
    
    /**
	 * This method is invoked to render advance options section in search case
	 * */
	public String getAdvOptionsPlus() {

		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		caseSearchDataBean.setAdvOptionsFlag(Boolean.TRUE);
		caseSearchDataBean.setPlus("minus");
		//getUsersList();
		getAllHierarchies();
		caseType();

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is invoked to hide advance options section in search case
	 * */
	public String getAdvOptionsMinus() {

		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		caseSearchDataBean.setAdvOptionsFlag(Boolean.FALSE);
		
		//caseSearchDataBean.getAssignedTo().clear();
        //caseSearchDataBean.getCreatedBy().clear();
        //caseSearchDataBean.getAdvCreatedBy().clear();
        caseSearchDataBean.getReportingUnit().clear();
        caseSearchDataBean.getBusinessUnit().clear();
        caseSearchDataBean.getDepartments().clear();
        caseSearchDataBean.getCaseType().clear();

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	//for CR 798895 for Bulk reassign starts.
	/**
	 * for CR 798895 for Bulk reassign. To populate short description of all the
	 * columns displayed in data table of search results.
	 * 
	 * @param searchRsltLst
	 *            search Resultant List
	 * */
	private void showShortDscrptn(List searchRsltLst) {
		if (searchRsltLst != null && !searchRsltLst.isEmpty()) {
			CaseRecordSearchResultsVO caseRecordSearchResultsVO = null;
			int count = 0;
			Map entityMap = caseSearchDataBean.getEntityTypeMap();

			if (caseSearchDataBean.getAllDeptUsersForReassign() == null
					|| caseSearchDataBean.getAllDeptUsersForReassign()
							.isEmpty()) {
				getAllDeptUsersForReassign();
			}
			String entityName = null;
			while (count < searchRsltLst.size()) {
				caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) searchRsltLst
						.get(count);
				if (caseRecordSearchResultsVO.getDeptList() != null
						&& caseRecordSearchResultsVO.getDeptList().size() > 0) {
					caseRecordSearchResultsVO
							.setDeptList(getDepartmentList(caseRecordSearchResultsVO
									.getDeptList()));
				} else {
					List deptList = new ArrayList();
					deptList.add(new SelectItem("", ""));
					caseRecordSearchResultsVO.setDeptList(deptList);
				}

				if (caseRecordSearchResultsVO.getEntityName() != null
						&& !caseRecordSearchResultsVO.getEntityName()
								.equals("")) {
					entityName = caseRecordSearchResultsVO.getEntityName();
				} else {
					if (caseRecordSearchResultsVO.getEntityFirstName() != null
							&& !caseRecordSearchResultsVO.getEntityFirstName()
									.equals("")) {
						entityName = caseRecordSearchResultsVO
								.getEntityFirstName().trim();
					}
					if (caseRecordSearchResultsVO.getEntityMidName() != null
							&& !caseRecordSearchResultsVO.getEntityMidName()
									.equals("")) {
						entityName = entityName
								+ " "
								+ caseRecordSearchResultsVO.getEntityMidName()
										.trim();
					}
					if (caseRecordSearchResultsVO.getEntityLastName() != null
							&& !caseRecordSearchResultsVO.getEntityLastName()
									.equals("")) {
						entityName = entityName
								+ " "
								+ caseRecordSearchResultsVO.getEntityLastName()
										.trim();
					}
				}
				if ((entityName == null || entityName.equals(""))
						&& (caseRecordSearchResultsVO.getEntityOrgName() != null && !caseRecordSearchResultsVO
								.getEntityOrgName().equalsIgnoreCase(""))) {
					entityName = caseRecordSearchResultsVO.getEntityOrgName();
					logger.debug("++organization name::" + entityName);
				}
				caseRecordSearchResultsVO.setEntityName(entityName.trim());

				if (caseRecordSearchResultsVO.getEntityType() != null
						&& !caseRecordSearchResultsVO.getEntityType()
								.equalsIgnoreCase("")) {

					if (entityMap
							.get(caseRecordSearchResultsVO.getEntityType()) != null) {
						caseRecordSearchResultsVO.setEntityTypeDesc(entityMap
								.get(caseRecordSearchResultsVO.getEntityType())
								.toString());
					} else {
						caseRecordSearchResultsVO
								.setEntityTypeDesc(caseRecordSearchResultsVO
										.getEntityType());
					}
				}

				if (caseRecordSearchResultsVO.getStatus() != null
						&& !caseRecordSearchResultsVO.getStatus()
								.equalsIgnoreCase("")) {
					String status = getDescriptionFromVV(
							caseRecordSearchResultsVO.getStatus(),
							caseSearchDataBean.getStatus());
					caseRecordSearchResultsVO.setStatus(status);
				}

				if (caseRecordSearchResultsVO.getPriority() != null
						&& !caseRecordSearchResultsVO.getPriority()
								.equalsIgnoreCase("")) {
					String priority = getDescriptionFromVV(
							caseRecordSearchResultsVO.getPriority(),
							caseSearchDataBean.getPriority());
					caseRecordSearchResultsVO.setPriority(priority);
				}

				if (caseRecordSearchResultsVO.getLob() != null
						&& !caseRecordSearchResultsVO.getLob()
								.equalsIgnoreCase("")) {
					String lob = getDescriptionFromVV(caseRecordSearchResultsVO
							.getLob(), caseSearchDataBean.getLobVVList());
					caseRecordSearchResultsVO.setLob(lob);
				}
				if (caseSearchDataBean.getSelectedDeptAll() != null) {
					caseRecordSearchResultsVO
							.setSelectedDept(caseSearchDataBean
									.getSelectedDeptAll());
					caseRecordSearchResultsVO
							.setSelectedDeptDesc(getDescriptionFromVV(
									caseSearchDataBean.getSelectedDeptAll(),
									caseSearchDataBean
											.getAllDeptUsersForReassign()));
				}
				count++;
			}

			caseSearchDataBean.setNoData(true);
			caseSearchDataBean.setNoDataFlag(true);
			caseSearchDataBean.setShowCaseSearchMsgFlag(true);
			caseSearchDataBean.setShowReassignResultsPage(true);
			caseSearchDataBean.setSuccess(false);
		}

	}

	/**
	 * for CR 798895 for Bulk reassign.Search with selected criteria and
	 * validating reassign all drop down if selected returns case records those
	 * can be assigned to selected value if not only those which statisfy the
	 * criteria and populate fields with short description.
	 * **/
	private void chkReassignAllValueNSearch() {
		String allDeptReassign = caseSearchDataBean.getSelectedDeptAll();
		CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = caseSearchDataBean
				.getCaseRecordSearchCriteriaVO();
		CaseRecordSearchResultsVO caseRecordSearchResultsVO = null;
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		CaseDelegate caseDelegate = new CaseDelegate();
		List caseSearchList = null;
		caseRecordSearchCriteriaVO
				.setReasgnToUsrWrkUnt(ContactManagementConstants.EMPTY_STRING);
		caseRecordSearchCriteriaVO
				.setReasgnToWrkUnt(ContactManagementConstants.EMPTY_STRING);
		int recordsPerPage = ContactManagementConstants.INT_10;

		if (!isNullOrEmptyField(allDeptReassign)) {
			caseRecordSearchCriteriaVO.setRowsPerPage(recordsPerPage);
			caseRecordSearchCriteriaVO.setReassignAllFlag(Boolean.TRUE);
			caseRecordSearchCriteriaVO.setReassignFlag(Boolean.FALSE);
			String userLabel = getDescriptionFromVV(allDeptReassign,
					caseSearchDataBean.getAllDeptUsersForReassign());
			if (!isNullOrEmptyField(userLabel)
					&& userLabel
							.indexOf(MaintainContactManagementUIConstants.HYPHEN) != -1) {
				caseRecordSearchCriteriaVO
						.setReasgnToUsrWrkUnt(allDeptReassign);
			} else {
				caseRecordSearchCriteriaVO.setReasgnToWrkUnt(allDeptReassign);
			}
		} else {
			System.out.println("else part if rea assign all not selected ");
			caseRecordSearchCriteriaVO.setRowsPerPage(recordsPerPage);
			caseRecordSearchCriteriaVO.setReassignAllFlag(Boolean.FALSE);
			caseRecordSearchCriteriaVO.setReassignFlag(Boolean.TRUE);
		}

		caseRecordSearchCriteriaVO.setStartIndex(caseSearchDataBean
				.getStartRecord());

		try {
			enterpriseSearchResultsVO = caseDelegate
					.getCaseRecords(caseRecordSearchCriteriaVO);
		} catch (CaseRecordFetchBusinessException e) {
			if(logger.isErrorEnabled()){
				logger.error(e.getMessage());
			}
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
		}

		if (enterpriseSearchResultsVO != null
				&& enterpriseSearchResultsVO.getSearchResults() == null) {

			caseSearchDataBean
					.setCount(MaintainContactManagementUIConstants.ZERO);
			setErrorMessage(EnterpriseMessageConstants.AUTHORITY_ERROR_CODE,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			caseSearchDataBean.setShowCaseSearchMsgFlag(true);
			caseSearchDataBean.setShowReassignResultsPage(false);
		} else if (enterpriseSearchResultsVO != null) {
			int recordCount = (int) enterpriseSearchResultsVO.getRecordCount();
			caseSearchDataBean.setCount(Integer.valueOf(String
					.valueOf(enterpriseSearchResultsVO.getRecordCount())));

			caseSearchDataBean.setItemsPerPage(recordsPerPage);
			if (caseSearchDataBean.getStartRecord() == 0) {
				caseSearchDataBean.setStartRecordNo(caseSearchDataBean
						.getStartRecord() + 1);
				caseSearchDataBean.setCurrentPage(1);
				if (enterpriseSearchResultsVO.getRecordCount() < caseSearchDataBean
						.getItemsPerPage()) {
					caseSearchDataBean.setEndRecord(recordCount);
					caseSearchDataBean.setShowNext(false);
					caseSearchDataBean.setShowNextOne(false);
					caseSearchDataBean.setShowPrevious(false);
				} else {
					caseSearchDataBean.setEndRecord(caseSearchDataBean
							.getStartRecord()
							+ caseSearchDataBean.getItemsPerPage());
					caseSearchDataBean.setShowNext(true);
					caseSearchDataBean.setShowPrevious(false);
					caseSearchDataBean.setShowNextOne(false);
				}
			}
			if (caseSearchDataBean.getCurrentPage() == 1
					&& enterpriseSearchResultsVO.getRecordCount() > 2 * caseSearchDataBean
							.getItemsPerPage()) // Modified for the DataScroller
			// Fix
			{
				caseSearchDataBean.setShowNextOne(true);
			}

			if (enterpriseSearchResultsVO != null) {
				caseSearchList = enterpriseSearchResultsVO.getSearchResults();
				if (caseSearchList != null && !caseSearchList.isEmpty()) {
					caseSearchDataBean.setShowReassignResultsPage(Boolean.TRUE);
					showShortDscrptn(caseSearchList);
					caseSearchDataBean.setSearchCaseList(caseSearchList);
				} else {
					caseSearchDataBean.getSearchCaseList().clear();
					caseSearchDataBean
							.setCount(MaintainContactManagementUIConstants.ZERO);
					caseSearchDataBean.setNoDataFlag(false);
					caseSearchDataBean.setNoData(false);
					caseSearchDataBean.setSuccess(false);
					// caseSearchDataBean
					// .setShowReassignResultsPage(Boolean.FALSE);
					if (!caseSearchDataBean.isShowCaseSearchMsgFlag()) {
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
								new Object[] {},
								MessageUtil.ENTMESSAGES_BUNDLE, null);
						caseSearchDataBean
								.setShowCaseSearchMsgFlag(Boolean.TRUE);
						caseSearchDataBean
								.setShowReassignResultsPage(Boolean.FALSE);
					}
				}
			}
		}
	}

	/**
	 * for CR 798895 for Bulk reassign. This method is invoked to reassign
	 * individual case records to selected assigned to value or reassign all is
	 * selected then bulk assignment will be update.Hence for each re assign a
	 * routing is inserted and case table is updated. To reassign the case
	 * records from search results to selected reassign value.
	 */
	public void reassignCaseRecords() {
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		List searchList = caseSearchDataBean.getSearchCaseList();
		if (searchList == null || searchList.isEmpty()) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			caseSearchDataBean.setShowCaseSearchMsgFlag(Boolean.TRUE);
			return;
		}
		String selectReassign = caseSearchDataBean.getSelectedDeptAll();
		String userId = getLoggedInUser();
		boolean reassignSuccess = Boolean.FALSE;
		boolean isIndividualAssign = Boolean.FALSE;
		CaseDelegate caseDelegate = new CaseDelegate();
		CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) caseSearchDataBean
				.getCaseRecordSearchCriteriaVO();
		caseRecordSearchCriteriaVO.setUserId(userId);
		if (!isNullOrEmptyField(selectReassign)) {
			String userLabel = getDescriptionFromVV(selectReassign,
					caseSearchDataBean.getAllDeptUsersForReassign());
			if (!isNullOrEmptyField(userLabel)) {
				caseRecordSearchCriteriaVO.setReassignFlag(Boolean.TRUE);
				caseRecordSearchCriteriaVO.setReassignAllFlag(Boolean.TRUE);
				caseRecordSearchCriteriaVO
						.setStartIndex(MaintainContactManagementUIConstants.ZERO);
				caseRecordSearchCriteriaVO.setRowsPerPage(caseSearchDataBean
						.getCount());
				if (userLabel
						.indexOf(MaintainContactManagementUIConstants.HYPHEN) != -1) {
					caseRecordSearchCriteriaVO
							.setReasgnToUsrWrkUnt(selectReassign);
				} else {
					caseRecordSearchCriteriaVO
							.setReasgnToWrkUnt(selectReassign);
				}
				try {
					caseDelegate.reassignAllCaseRecordss(
							caseRecordSearchCriteriaVO, selectReassign, userId);
					reassignSuccess = Boolean.TRUE;
				} catch (CaseRecordUpdateDataException e) {
					if(logger.isErrorEnabled()){
						logger.error(e.getMessage());
					}
				}
			}
		} else {
			isIndividualAssign = Boolean.TRUE;
			reassignSuccess = assignIndividual();
		}
		caseSearchDataBean.setShowCaseSearchMsgFlag(Boolean.TRUE);
		if (reassignSuccess) {
			chkReassignAllValueNSearch();
			if (caseSearchDataBean.isNoDataFlag() && !isIndividualAssign) {
				setErrorMessage(
						ContactManagementConstants.RE_ASSIGN_SUCCESS_COUNT_MSG,
						new Object[] { ContactManagementConstants.SEVENTY_FIVE_THOUSAND },
						MessageUtil.ENTMESSAGES_BUNDLE, null);
			} else {
				setErrorMessage(
						MaintainContactManagementUIConstants.REASSIGN_SUCCESS_MSG,
						null, MessageUtil.ENTMESSAGES_BUNDLE, null);
			}
		} else {
			if (isIndividualAssign) {
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
						new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			} else {
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
						new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			}
		}

	}

	/**
	 * for CR 798895 for Bulk reassign. This method is to assign individual case
	 * record as per the user/workunit selected for each record per
	 * page(10).Hence it will update G_CASE_TB and insert a record in
	 * G_CASE_ROUTNG_TB.
	 * */
	private boolean assignIndividual() {
		boolean isAssigned = Boolean.FALSE;
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		List searchList = caseSearchDataBean.getSearchCaseList();
		CaseDelegate caseDelegate = new CaseDelegate();
		List caseRecordList = new ArrayList();
		String userId = getLoggedInUser();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		// if individual reassign selected.
		CaseRecord caseRecord = null;
		CaseRouting caseRouting = null;
		WorkUnit workUnit = null;
		Set existingCaseRecords = null;
		Long caseSk = null;
		WorkUnit userWorkUnit = null;
		Date today = new Date();
		try {
			userWorkUnit = contactMaintenanceDelegate.getUserWorkUnit(userId);
		} catch (LOBHierarchyFetchBusinessException e1) {
			if(logger.isErrorEnabled()){
				logger.error(e1.getMessage());
			}
		}
		for (Iterator iter = searchList.iterator(); iter.hasNext();) {
			CaseRecordSearchResultsVO caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) iter
					.next();
			if (caseRecordSearchResultsVO.getSelectedDept() != null
					&& !caseRecordSearchResultsVO.getSelectedDept().trim()
							.equalsIgnoreCase(
									ContactManagementConstants.EMPTY_STRING)) {

				caseSk = caseRecordSearchResultsVO.getCaseSK();
				if (caseSk != null) {
					try {
						caseRecord = caseDelegate
								.getCaseRecord(caseRecordSearchResultsVO
										.getCaseSK());
					} catch (CaseRecordFetchBusinessException e) {
						if(logger.isErrorEnabled()){
							logger.error(e.getMessage());
						}
					}

					caseRecord.setCaseSK(caseRecordSearchResultsVO.getCaseSK());
					Long routedToWorkUnitSK = Long
							.valueOf(caseRecordSearchResultsVO
									.getSelectedDept());
					caseRouting = new CaseRouting();

					caseRouting
							.setCaseSK(caseRecordSearchResultsVO.getCaseSK());
					caseRouting.setRoutedToWorkUnitSK(routedToWorkUnitSK);
					caseRouting.setWatchListIndicator(Boolean.FALSE);
					caseRouting.setRoutedByWorkUnit(userWorkUnit);
					caseRouting.setCaseRoutingDate(today);

					workUnit = new WorkUnit();
					workUnit.setWorkUnitSK(routedToWorkUnitSK);
					caseRouting.setRoutedToWorkUnit(workUnit);

					caseRouting.setAddedAuditTimeStamp(today);
					caseRouting.setAddedAuditUserID(userId);
					caseRouting.setAuditTimeStamp(today);
					caseRouting.setAuditUserID(userId);
					caseRouting.setCaseRecord(caseRecord);

					existingCaseRecords = caseRecord.getCaseRoutings();

					if (existingCaseRecords != null) {
						existingCaseRecords.add(caseRouting);
						caseRecord.setCaseRoutings(existingCaseRecords);
					} else {
						Set addNewRouting = new HashSet();
						addNewRouting.add(caseRouting);
						caseRecord.setCaseRoutings(addNewRouting);
					}

					caseRecord.setCaseAssignedToWorkUnit(workUnit);
					caseRecord.setAuditUserID(userId);
					caseRecord.setAuditTimeStamp(today);

					caseRecordList.add(caseRecord);
				}
			}
		}
		try {
			if (!caseRecordList.isEmpty()) {
				caseDelegate.reassignCaseRecordss(caseRecordList);
				isAssigned = Boolean.TRUE;
			}
		} catch (CaseRecordUpdateDataException e) {
			if(logger.isErrorEnabled()){
				logger.error(e.getMessage());
			}
		}
		return isAssigned;
	}

	/**
	 * for CR 798895 for Bulk reassign. This method is invoked if the user
	 * chooses to save the current page and continue to next/previous page.
	 * */
	public void reassignIndivualCaseNContinue() {
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.Case_DATABEAN_NAME);
		boolean isAssign = assignIndividual();
		if (!isNullOrEmptyField(caseSearchDataBean.getContinueType())) {
			if (caseSearchDataBean.getContinueType().equalsIgnoreCase(
					ContactManagementConstants.N)) {
				next();
			} else if (caseSearchDataBean.getContinueType().equalsIgnoreCase(
					"P")) {
				previous();
			} else if (caseSearchDataBean.getContinueType().equalsIgnoreCase(
					ContactManagementConstants.NO)) {
				nextOne();
			}
		}
		if (isAssign) {
			setErrorMessage(
					MaintainContactManagementUIConstants.REASSIGN_SUCCESS_MSG,
					null, MessageUtil.ENTMESSAGES_BUNDLE, null);
		}
		caseSearchDataBean
				.setContinueType(ContactManagementConstants.EMPTY_STRING);
	}
	
	//for CR 798895 for Bulk reassign ends.
 }