/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

/**
 * @author Wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.audit.application.dataaccess.exception.GlobalAuditDataException;
import com.acs.enterprise.common.audit.application.exception.GlobalAuditsBusinessException;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
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
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CallscriptCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CallscriptDeleteBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CallscriptFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CallscriptUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CallScript;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CallScriptSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CategorySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CallScriptDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CallScriptCategoryVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CallScriptEntityVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CallScriptSubjectVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CallScriptVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;
//added to display Audit table

//import security.common.domain.User;

/**
 * This is a controller class used for Contact Management Call script related
 * functionality in the presentation layer.
 * 
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class CallScriptControllerBean
        extends EnterpriseBaseControllerBean
{

    /**
     * Generating object of EnterpriseLogger.
     */
    private static final EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CallScriptControllerBean.class);

    /** Creates reference of data Bean */
    private CallScriptDataBean callScriptDataBean = getCallScriptDataBean();

    /** Creates instance of ContactMaintenanceDelegate * */
    private ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
    
    /**
     * This field is used to store whether user has permission.
     */
    private boolean controlRequired = true; // Newly added
    
	private boolean controlRequiredFOrDelete = true;
    /**
     * This field is used to disable or enable the description taxt box
     */
    private Boolean showActiveScreen = Boolean.TRUE;
    /**
     * This field is set to true only once so that multiple calling to the getter method can be avoided
     */
	private boolean invokeShowActiveScreen = true;
	
	private String loadUserPermissions;

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
       /* public void getUserPermission() {
    	String userPermission = "";
		String userid = getUserID();
		logger.debug("  CallScriptControllerBean:userid:::::::" + userid);
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();

		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
                    ContactManagementConstants.MAINTAIN_CALL_SCRIPTS_PAGE,
                    userid);
			logger.debug("  CallScriptControllerBean:userPermission::::::" + userPermission);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.debug("error on  CallScriptControllerBean.getUserPermission :"
					+ e);
		}
		HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        request.setAttribute("displayMode", userPermission);
        userPermission = userPermission != null ? userPermission.trim() : "";
		
		// check the permission level for the user and set it in a boolean variable.  This will be used for Buttons/Links
		if(userPermission.equals("r")) {
			controlRequired = false;		
		}
	}*/
    
    /**
     * This method get the User ID
     * 
     * @return String
     */
    private String getUserID() {
		String userID = null;
		try {
			/*HttpServletRequest renderrequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(renderrequest, renderresponse);
			if (enterpriseUserProfile != null) {
				userID = enterpriseUserProfile.getUserId();
			}	*/	
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
     * This operation is used to create new ContactManagement CallScript Record
     * 
     * @return String
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String createCallScript()
    {
    	String description = callScriptDataBean.getCallScriptVO().getDescription();
       
	 	
    /** This method Validates for not null Description */
    if (validateCallScripts(false))
    {
        CallScriptDOConvertor callScriptDOConvertor = new CallScriptDOConvertor();
        //FindBug Issue Resolved
        //CallScript callScript = new CallScript();
        CallScript callScript = null;
        /** Calls method to set the CallScriptVo with Values entered in UI */
        CallScriptVO callScriptVO = setCallScriptVO();
        
		/** Call Helper Class to convert Vo o DO */
        callScript = callScriptDOConvertor.convertCallScriptVOToDO(callScriptVO);
        /** Call Delegate method to create CallScript */
        try
        {
            if (callScript != null)
            {
            	callScript = contactMaintenanceDelegate.createCallScript(callScript);
            }

            if (callScript.getCallScriptSK() != null)
            {
            	getAllEnityType();
                
                getAllCategory();
                
                getSubDataTableList();
                
            	callScriptVO = callScriptDOConvertor.convertCallScriptDOToVO(callScript);
            	
            	List catListforEditCallScript = new ArrayList();
		        if (callScriptDataBean.getAllCategoryList() != null
		                && callScriptDataBean.getAllCategoryList().size() > 0)
		        {
		            catListforEditCallScript = showAssignedCategories(
		                    callScriptDataBean.getAllCategoryList(), callScriptVO
		                            .getCategory());
		        }
		        
		        /** Add the category List to the Respective List in Data Bean */
		        callScriptDataBean.setCategoryList(catListforEditCallScript);
		        
		        List entListforEditCallScript = new ArrayList();
		        if (callScriptDataBean.getAllEntityTypeList() != null
		                && callScriptDataBean.getAllEntityTypeList().size() > 0)
		        {
		            entListforEditCallScript = showAssignedEntities(callScriptDataBean
		                    .getAllEntityTypeList(), callScriptVO);
		        }
		        /** Add the Entity List to the Respective List in Data Bean */
		        callScriptDataBean.setEntityList(entListforEditCallScript);
		        
		        List subListforEditCallScript = new ArrayList();
		        if (callScriptDataBean.getAllSubjectList() != null
		                && callScriptDataBean.getAllSubjectList().size() > 0)
		        {

		            subListforEditCallScript = showAssignedSubjects(callScriptDataBean
		                    .getAllSubjectList(), callScriptVO.getSubject());
		        }
		        /** Add the Subject List to the Respective List in Data Bean */
		        callScriptDataBean.setSubjectList(subListforEditCallScript);
                
                callScriptDataBean.getMaintainCallScriptList().add(
                        callScriptVO);
                callScriptDataBean.setCallScriptVO(new CallScriptVO());
                clearDataTablesListAfterAdd(description);
				CallScriptVO tempCallScriptVO = new CallScriptVO();
                BeanUtils.copyProperties(tempCallScriptVO, callScriptVO);
                callScriptDataBean.setCallScriptVO(tempCallScriptVO);
    			callScriptDataBean.setShowSucessMessage(true);
    			callScriptDataBean.setShowEditCallScripts(true);
    			// Below code is modified for fixing the Defect ESPRD00876267 on 31/12/2012
    			callScriptDataBean.setEditCallScritFlag(true);
                callScriptDataBean.setShowAddCallScripts(false);
                callScriptDataBean.setRenderAddorEdit(true);
        }}

        catch (CallscriptCreateBusinessException e)
        {
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
            if(logger.isErrorEnabled())
            {
            	logger.error(e.getMessage(), e);
            }
        } catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        finally
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("Value of :") ;
        	}
        }

    }
    return ContactManagementConstants.RENDER_SUCCESS;
    
    }

    /**
     * This method Sets CallScript Vo and the Associaion of Entity , Category
     * and Subjects with Call Script.
     * 
     * @return :CallScriptVO * Returns CallScriptVO with values entered in UI
     */
    public CallScriptVO setCallScriptVO()
    {
    	    
        //FindBug Issue Resolved
        //List allCategoryList = new ArrayList(
          //      ContactManagementConstants.DEFAULT_SIZE);
        List allCategoryList =null;
        
        //FindBug Issue Resolved
        //List allEntityList = new ArrayList(
          //      ContactManagementConstants.DEFAULT_SIZE);
        List allEntityList =null;
        
        //FindBug Issue Resolved
        //List allSubList = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
        List allSubList =null;
        
        //FindBug Issue Resolved
        //List assignedCategoryList = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
        List assignedCategoryList =null;
        
        //FindBug Issue Resolved
        //List assignedEntityList = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
        List assignedEntityList =null;
        
        //FindBug Issue Resolved
        //List assignedSubList = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
        List assignedSubList =null;
        
        callScriptDataBean = getCallScriptDataBean();
        CallScriptVO callScriptVO = callScriptDataBean.getCallScriptVO();
        
        /** POPULATING CATEGORY LIST */

        /** Get the List of categories associated to callscript */
        allCategoryList = callScriptDataBean.getCategoryList();
        /**
         * Calls associateCategory method which returns list of categories
         * asigned to call script
         */
        assignedCategoryList = associateCategory(allCategoryList);

        /** Set the Category List to CallScriptVO */
        if (assignedCategoryList != null && !assignedCategoryList.isEmpty())
        {
            callScriptVO.setCategory(assignedCategoryList);
            if(logger.isDebugEnabled())
            {
            	logger.debug("size of cat associated to cs in contr bean" + callScriptVO.getCategory().size());
            }
        }

        /** POPULATING ENTITY LIST */

        allEntityList = callScriptDataBean.getEntityList();
        /**
         * Calls associateEntityType method which returns list of entittes
         * asigned to call script
         */
        assignedEntityList = associateEntityType(allEntityList);

        /** Set the Entity List to CallScriptVO */
        if (assignedEntityList != null && !assignedEntityList.isEmpty())
        {
            callScriptVO.setEntType(assignedEntityList);
        }

        /** POPULATING SUBJECT LIST */

        allSubList = callScriptDataBean.getSubjectList();
        /**
         * Calls associateSubject method which returns list of subjects asigned
         * to call script
         */
        assignedSubList = associateSubject(allSubList);

        /** Set the Category List to CallScriptVO */
        if (assignedSubList != null && !assignedSubList.isEmpty())
        {
            callScriptVO.setSubject(assignedSubList);
            if(logger.isDebugEnabled())
            {
            	logger.debug("size of sub associated to cs in contr bean" + callScriptVO.getSubject().size());
            }
        }

        

        return callScriptVO;

    }

    /**
     * This operation is used to update ContactManagement CallScript Record
     * 
     * @return String
     */
    public String updateCallScript()
    {

    	CallScriptDOConvertor callScriptDOConvertor = new CallScriptDOConvertor();
        CallScriptSearchCriteriaVO callScriptSearchCriteriaVO = new CallScriptSearchCriteriaVO();
        //FindBug Issue Resolved
        //CallScript callScript = new CallScript();
        CallScript callScript =null;
        callScriptDataBean = getCallScriptDataBean();
       
	 	
        /** This method Validates for not null Description */
        if (validateCallScripts(true))
        {
            /** Calls method to set the CallScriptVo with Values entered in UI */
            CallScriptVO callScriptVO = setCallScriptVO();
            int versionNo=callScriptDataBean.getCallVersionNo();

            /** Call Helper Class to convert Vo o DO */
            callScript = callScriptDOConvertor
                    .convertCallScriptVOToDO(callScriptVO);
            
            //FindBug Issue starts
            /*if(versionNo == callScriptVO.getVersionNo())
            {
            	callScript.setVersionNo(versionNo);
            }else
            {
            	callScript.setVersionNo(versionNo);
            }*/
            //FindBug Issue ends
            if(callScript!=null){
            callScript.setVersionNo(versionNo);
        	}
            /** Call Delegate method to create CallScript */
            try
            {

            	callScript = contactMaintenanceDelegate
						.updateCallScript(callScript);
				callScriptDataBean.setFlagIndicator(true);

            }

            catch (CallscriptUpdateBusinessException e)
            {
            	if(logger.isErrorEnabled())
            	{
            		logger.error(e.getMessage(), e);
            	}
                setErrorMessage(EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
                callScriptDataBean.setFlagIndicator(false);
            }
            if (callScriptDataBean.isFlagIndicator())
            {
            	callScriptDataBean
                        .getAllCallScripts(callScriptSearchCriteriaVO);
                callScriptDataBean.setShowSucessMessage(true);
                callScriptVO = callScriptDOConvertor
		                .convertCallScriptDOToVO(callScript);
		        callScriptDataBean.setCallScriptVO(callScriptVO);
		        getAllEnityType();
		        getAllCategory();
		        getSubDataTableList();
		        List catListforEditCallScript = new ArrayList();
		        if (callScriptDataBean.getAllCategoryList() != null
		                && callScriptDataBean.getAllCategoryList().size() > 0)
		        {
		            catListforEditCallScript = showAssignedCategories(
		                    callScriptDataBean.getAllCategoryList(), callScriptVO
		                            .getCategory());
		        }
		        
		        /** Add the category List to the Respective List in Data Bean */
		        callScriptDataBean.setCategoryList(catListforEditCallScript);
		        if(callScriptDataBean.getCategoryList().size()>0)
		        {
		        	String description = callScriptDataBean.getCallScriptVO().getDescription();
		        	 for (int j = 0; j < callScriptDataBean.getCategoryList().size(); j++)
		             {
		        	 	CallScriptCategoryVO assignedCategoriesVO = (CallScriptCategoryVO) callScriptDataBean.getCategoryList().get(j);
		                 if(assignedCategoriesVO.getAssignedCallScript()!=null)
		                 {
		                 	if(!assignedCategoriesVO.getAssignedCallScript().equalsIgnoreCase(description))
			                 {
			                 	assignedCategoriesVO.setIncludecallScript(false);
			                 }
		                 	else
		                 	{
		                 		assignedCategoriesVO.setIncludecallScript(true);
		                 	}
		                 }
		                 else
		                 {
		                 	assignedCategoriesVO.setIncludecallScript(false);
		                 }
		                 
		             }
		        	
		        }
		        List entListforEditCallScript = new ArrayList();
		        if (callScriptDataBean.getAllEntityTypeList() != null
		                && callScriptDataBean.getAllEntityTypeList().size() > 0)
		        {
		            entListforEditCallScript = showAssignedEntities(callScriptDataBean
		                    .getAllEntityTypeList(), callScriptVO);
		        }
		        /** Add the Entity List to the Respective List in Data Bean */
		        callScriptDataBean.setEntityList(entListforEditCallScript);
		        List subListforEditCallScript = new ArrayList();
		        if (callScriptDataBean.getAllSubjectList() != null
		                && callScriptDataBean.getAllSubjectList().size() > 0)
		        {

		            subListforEditCallScript = showAssignedSubjects(callScriptDataBean
		                    .getAllSubjectList(), callScriptVO.getSubject());
		        }
		        /** Add the Subject List to the Respective List in Data Bean */
		        callScriptDataBean.setSubjectList(subListforEditCallScript);
		       if(callScriptDataBean.getSubjectList().size()>0)
		        {
		        	String description = callScriptDataBean.getCallScriptVO().getDescription();
		        	 for (int j = 0; j < callScriptDataBean.getSubjectList().size(); j++)
		             {
		                 CallScriptSubjectVO assignedSubjectsVO = (CallScriptSubjectVO) callScriptDataBean.getSubjectList().get(j);
		                 if(assignedSubjectsVO.getAssignedCallScript()!=null)
		                 {
		                 	if(!assignedSubjectsVO.getAssignedCallScript().equalsIgnoreCase(description))
			                 {
			                 	assignedSubjectsVO.setIncludecallScript(false);
			                 }
		                 	else
		                 	{
		                 		assignedSubjectsVO.setIncludecallScript(true);
		                 	}
		                 }
		                 else
		                 {
		                 	assignedSubjectsVO.setIncludecallScript(false);
		                 }
		                 
		             }
		        	
		        }
		       
		        callScriptDataBean.setShowEditCallScripts(true);
		        callScriptDataBean.setShowAddCallScripts(false);
		        /** loads the collapsible audit history */
		        showAuditHistory();
            }
            
        }
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This operation is used to get Contact Management CallScript Record
     * details based on the primary key value callScriptSK'
     * 
     * @return String
     */
    public String getCallScriptDetails()
    {
    
    	//heamp dump panel tab issue fix
		//callScriptDataBean.getHtmlPanelTabbedPane().setSelectedIndex(0);
		callScriptDataBean.setSelectedIndex(0);

        getCallScriptDataBean().setRenderAddorEdit(true);
        List catListforEditCallScript = new ArrayList();
        List entListforEditCallScript = new ArrayList();
        List subListforEditCallScript = new ArrayList();
        CallScriptVO callscriptOldVO = new CallScriptVO();
        CallScriptVO callscriptNewVO = new CallScriptVO();
        callScriptDataBean.setEditCallScritFlag(true);

        callScriptDataBean.setShowSucessMessage(false);
        FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
		if (indexCode == null) {
			indexCode = "0";
		}
        int index = Integer.parseInt(indexCode);
       
        callScriptDataBean.setEditCallScritRowIndex(index);
        if(logger.isDebugEnabled())
        {
        	logger.debug("Index --->" + index);
        }

        /** If List MaintainCallScriptList in dataBean is not Null */
        if (callScriptDataBean.getMaintainCallScriptList() != null
                && callScriptDataBean.getMaintainCallScriptList().size() > 0)
        {
            callscriptOldVO = (CallScriptVO) callScriptDataBean
                    .getMaintainCallScriptList().get(index);

        }

        /** Populate the Common Part of Vo */

        if (callscriptOldVO.getCallScriptStatus().equalsIgnoreCase(
                ContactManagementConstants.Y)
                || callscriptOldVO.getCallScriptStatus().equalsIgnoreCase(
                        ContactManagementConstants.YES))
        {
            callscriptNewVO.setCallScriptStatus(ContactManagementConstants.YES);
        }
        else
        {
            callscriptNewVO.setCallScriptStatus(ContactManagementConstants.NO);
            /** By ICS, DefectId:ESPRD00059988*/
            callscriptNewVO.setInactive(true);
            
        }
        callscriptNewVO.setCallScriptSK(callscriptOldVO.getCallScriptSK());
        callscriptNewVO.setDescription(callscriptOldVO.getDescription());
        callscriptNewVO.setCallScriptFullText(callscriptOldVO
                .getCallScriptFullText());
        callscriptNewVO.setCallScriptCexSk(callscriptOldVO.getCallScriptCexSk());
        callscriptNewVO.setCallScriptCexVersionNo(callscriptOldVO.getCallScriptCexVersionNo());
        /** Set Audit Information */
        callscriptNewVO.setAuditUserID(callscriptOldVO.getAuditUserID());
        callscriptNewVO.setAuditTimeStamp(callscriptOldVO.getAuditTimeStamp());
        callscriptNewVO.setAddedAuditUserID(callscriptOldVO
                .getAddedAuditUserID());
        callscriptNewVO.setAddedAuditTimeStamp(callscriptOldVO
                .getAddedAuditTimeStamp());

        /** Add he temp Vo to the Data Bean */
        callScriptDataBean.setCallScriptVO(callscriptNewVO);
        if(logger.isDebugEnabled())
        {
        	logger.debug("Calls full text value " + callscriptNewVO.getCallScriptFullText());
        	logger.debug(" **** getCallScriptDetails getCallScriptSK: "	+ callscriptOldVO.getCallScriptSK());
        	logger.debug(" **** getCallScriptDetails getVersionNo: " + callscriptOldVO.getVersionNo());
        }
		
		callscriptNewVO.setVersionNo(callscriptOldVO.getVersionNo());
		callScriptDataBean.setCallVersionNo(callscriptOldVO.getVersionNo());
		
        
        /**
         * Calls getAllEnityType method to populate all Entities in the data
         * Table
         */
		
        getAllEnityType();
        
        
        /**
         * Calls getAllCategory method to populate all Categories in the data
         * Table
         */
        getAllCategory();
        /**
         * Calls getSubDataTableList method to populate all Subjects in the data
         * Table
         */
        getSubDataTableList();
        /** Populate the Data Table for Entity Tab */

        /** CATEGORY DATA TABLE */

        /** Get the list of all Categories assigned to this Call script */

        if (callScriptDataBean.getAllCategoryList() != null
                && callScriptDataBean.getAllCategoryList().size() > 0)
        {

            catListforEditCallScript = showAssignedCategories(
                    callScriptDataBean.getAllCategoryList(), callscriptOldVO
                            .getCategory());
        }

        /** Add the category List to the Respective List in Data Bean */
        callScriptDataBean.setCategoryList(catListforEditCallScript);

        /** ENTITY TYPE DATA TABLE */

        /** Get the list of all Entity Types assigned to this Call script */

        if (callScriptDataBean.getAllEntityTypeList() != null
                && callScriptDataBean.getAllEntityTypeList().size() > 0)
        {
            entListforEditCallScript = showAssignedEntities(callScriptDataBean
                    .getAllEntityTypeList(), callscriptOldVO);
        }
        /** Add the Entity List to the Respective List in Data Bean */
        callScriptDataBean.setEntityList(entListforEditCallScript);

        /** SUBJECT TYPE DATA TABLE */
        /** Get the list of all subjects assigned to this Call script */

        if (callScriptDataBean.getAllSubjectList() != null
                && callScriptDataBean.getAllSubjectList().size() > 0)
        {

            subListforEditCallScript = showAssignedSubjects(callScriptDataBean
                    .getAllSubjectList(), callscriptOldVO.getSubject());
        }
        /** Add the Subject List to the Respective List in Data Bean */
        callScriptDataBean.setSubjectList(subListforEditCallScript);

        /** Render the Edit Block */
        callScriptDataBean.setShowEditCallScripts(true);
        callScriptDataBean.setShowAddCallScripts(false);
        callScriptDataBean.setAuditLogFlag(false);
        /** loads the collapsible audit history */

        
     //   showAuditHistory();
		callScriptDataBean.setInputHiddenId("editCallScriptFocus");

        

        return ContactManagementConstants.RENDER_SUCCESS;

    }
    private boolean csChkBxVal=false;
    private boolean csCatChkBxVal=false;
    private boolean csSubChkBxVal=false;
    /**
     * This method is used to select the CheckBox for Assigned Categories.
     * 
     * @param allCategories
     *            Takes List of All categories
     * @param assignedCategories
     *            Takes List of all assignedCategories
     * @return List
     */
    public List showAssignedCategories(List allCategories,
            List assignedCategories)
    {
    	int allCategoriesSize = 0;
        int assignedCategoriesSize = 0;

        if (allCategories != null && assignedCategories != null)
        {
            allCategoriesSize = allCategories.size();
            assignedCategoriesSize = assignedCategories.size();

        }

        for (int i = 0; i < allCategoriesSize; i++)
        {
            CallScriptCategoryVO allCategoriesVo = (CallScriptCategoryVO) allCategories
                    .get(i);

            for (int j = 0; j < assignedCategoriesSize; j++)
            {
                CallScriptCategoryVO assignedCategoriesVO = (CallScriptCategoryVO) assignedCategories
                        .get(j);

                if (allCategoriesVo.getCategoryVO().getCategorySK().compareTo(
                        assignedCategoriesVO.getCategoryVO().getCategorySK()) == 0)
                {

                    allCategoriesVo.setIncludecallScript(true);
                    allCategoriesVo
                            .setStatus(ContactManagementConstants.STATUS_ASSIGNED);
                    break;

                }
            }

        }
        
        return allCategories;

    }

    /**
     * This method is used to select the CheckBox for Assigned Entities.
     * 
     * @param allEntities
     *            Takes List of All Entities
     * @param assignedEntities
     *            Takes List of all assignedEntities
     * @return List
     */
    public List showAssignedEntities(List allEntities, CallScriptVO callscriptOldVO)
    {
    	
        List assignedEntities = callscriptOldVO.getEntType();
        int allEntitiesSize = 0;
        int assignedEntitiesSize = 0;

        if (allEntities != null && assignedEntities != null)
        {
            allEntitiesSize = allEntities.size();
            assignedEntitiesSize = assignedEntities.size();
            if(logger.isDebugEnabled())
            {
            	logger.debug("assignedEntitiesSize*******" + assignedEntitiesSize);
            }
        }

        for (int i = 0; i < allEntitiesSize; i++)
        {
            CallScriptEntityVO allEntitiesVo = (CallScriptEntityVO) allEntities
                    .get(i);
            for (int j = 0; j < assignedEntitiesSize; j++)
            {
                CallScriptEntityVO assignedEntitiesVO = (CallScriptEntityVO) assignedEntities
                        .get(j);
                if (StringUtils.equalsIgnoreCase(allEntitiesVo
                        .getEntityTypeCode(), assignedEntitiesVO
                        .getEntityTypeCode()))
                {
                	
                    allEntitiesVo.setIncludecallScript(true);
                    allEntitiesVo
                            .setStatus(ContactManagementConstants.STATUS_ASSIGNED);
                    allEntitiesVo.setAssignedCallScript(callscriptOldVO.getDescription());
                    break;

                }
            }

        }
        
        return allEntities;

    }

    /**
     * This method is used to select the CheckBox for Assigned Entities.
     * 
     * @param allSubjects
     *            Takes List of All Subjects
     * @param assignedSubjects
     *            Takes List of all assignedSubjects
     * @return List
     */
    public List showAssignedSubjects(List allSubjects, List assignedSubjects)
    {
    	
        int allSubjectsSize = 0;
        int assignedSubjectsSize = 0;

        if (allSubjects != null && assignedSubjects != null)
        {
            allSubjectsSize = allSubjects.size();
            assignedSubjectsSize = assignedSubjects.size();
            if(logger.isDebugEnabled())
            {
            	logger.debug("assignedSubjectsSize*******" + assignedSubjectsSize);
            }
        }

        for (int i = 0; i < allSubjectsSize; i++)
        {
            CallScriptSubjectVO allSubjectsVo = (CallScriptSubjectVO) allSubjects
                    .get(i);

            for (int j = 0; j < assignedSubjectsSize; j++)
            {
                CallScriptSubjectVO assignedSubjectsVO = (CallScriptSubjectVO) assignedSubjects
                        .get(j);

                if (StringUtils.equalsIgnoreCase(
                        allSubjectsVo.getSubjectCode(), assignedSubjectsVO
                                .getSubjectCode()))
                {
                    

                    allSubjectsVo.setIncludecallScript(true);
                    allSubjectsVo
                            .setStatus(ContactManagementConstants.STATUS_ASSIGNED);
                    break;

                }
            }

        }

        
        return allSubjects;
    }

    /**
     * This method returns the List of EntityTypes assigned to a specific
     * CallScript.
     * 
     * @param entList :
     *            Takes List of All Entities as parameter.
     * @return associatedEntityList : List of Assigned Entity Types
     */
    public List associateEntityType(List entList)
    {

        List associatedEntList = new ArrayList();

        int entListSize = 0;

        /** While Creating Call Script */
        if (entList != null)
        {
            entListSize = entList.size();
        }

        /** Iterate throught list */
        if (entList != null && entListSize > 0)
        {
            /** For ADD CALLSCRIPT Block */
            for (int i = 0; i < entListSize; i++)
            {
                CallScriptEntityVO callScriptEntityVO = (CallScriptEntityVO) entList
                        .get(i);

                if (callScriptEntityVO.getIncludecallScript())
                {
                    callScriptEntityVO
                            .setStatus(ContactManagementConstants.STATUS_NONE);
                    associatedEntList.add(callScriptEntityVO);

                }
            }
        }
        
        return associatedEntList;
    }

    /**
     * This method returns the List of Subjects Assigned to the specific Call
     * Script.
     * 
     * @param subList
     *            Takes List of All Subjects as parameter.
     * @return associatedSubList : List of Subjects assigned to call Script.
     */
    public List associateSubject(List subList)
    {
    	
        List associatedSubList = new ArrayList();
        List deassociateSubList = new ArrayList();
        List finalSubList = new ArrayList();
        List oldAssignedSubList = null;
        int oldAssignedSubListSize = 0;
        int subListSize = 0;
        
        // Below code is modified for fixing the Defect ESPRD00876267 on 31/12/2012
        
        CallScriptVO callScriptVO = null;
        if(callScriptDataBean.isShowEditCallScripts())
        {
        	callScriptVO = (CallScriptVO) callScriptDataBean.getMaintainCallScriptList().get(callScriptDataBean.getEditCallScritRowIndex());
        }
        else
        {
        	callScriptVO=callScriptDataBean.getCallScriptVO();
        }
        if (callScriptVO.getSubject() != null
                && callScriptVO.getSubject().size() > 0)
        {

            oldAssignedSubList = callScriptVO.getSubject();
            oldAssignedSubListSize = oldAssignedSubList.size();
        }

        /** While Creating Call Script */
        if (subList != null)
        {
            subListSize = subList.size();
        }

        /** Iterate throught list */
        if (subList != null && subListSize > 0)
        {
            /** For ADD CALLSCRIPT Block */
            for (int i = 0; i < subListSize; i++)
            {

                CallScriptSubjectVO callScriptSubjectVO = (CallScriptSubjectVO) subList
                        .get(i);

                if (callScriptSubjectVO.getIncludecallScript())
                {

                    callScriptSubjectVO
                            .setStatus(ContactManagementConstants.STATUS_NONE);
                    associatedSubList.add(callScriptSubjectVO);

                }
                /* Store the Unchecked records */
                else
                {

                    if (callScriptDataBean.isEditCallScritFlag()
                            && oldAssignedSubListSize > 0)
                    {
                        callScriptSubjectVO
                                .setStatus(ContactManagementConstants.STATUS_NONE);
                        callScriptSubjectVO = getDeassocitedSubList(
                                callScriptSubjectVO, oldAssignedSubList);

                        if (callScriptSubjectVO.getStatus().equalsIgnoreCase(
                                ContactManagementConstants.STATUS_DELETED))
                        {
                            deassociateSubList.add(callScriptSubjectVO);
                        }
                    }

                }

            }

            finalSubList.addAll(associatedSubList);
            boolean flag = (callScriptDataBean.isEditCallScritFlag()
                    && deassociateSubList != null && !deassociateSubList
                    .isEmpty());
           if (flag)
            {
                finalSubList.addAll(deassociateSubList);
            }
            
        }

        
        return finalSubList;
    }

    /**
     * This method returns the List of Categories assigned to Call Script.
     * 
     * @param catList
     *            Takes List of Categories as parameter.
     * @return associatedCatList : List of Categories Assigned.
     */
    public List associateCategory(List catList)
    {
    	
        List associatedCatList = new ArrayList();
        List deassociateCatList = new ArrayList();
        List finalCatList = new ArrayList();
        List oldAssignedCaTList = null;
        int oldAssignedCaTListSize = 0;
        int catListSize = 0;
        CallScriptVO callScriptVO = null;
        
        
       //If condition added for the defect - ESPRD00857740 to add/update the call script successfully 
       
       if(callScriptDataBean.isShowEditCallScripts()){
       callScriptVO = (CallScriptVO) callScriptDataBean
                .getMaintainCallScriptList().get(
                        callScriptDataBean.getEditCallScritRowIndex());
       }
       else
       {
       callScriptVO=callScriptDataBean.getCallScriptVO();
       }
       
        if (callScriptVO.getCategory() != null
                && callScriptVO.getCategory().size() > 0)
        {

            oldAssignedCaTList = callScriptVO.getCategory();
            oldAssignedCaTListSize = oldAssignedCaTList.size();
        }

        /** While Creating Call Script */
        if (catList != null)
        {
            catListSize = catList.size();
        }

        /** Iterate throught list */
        if (catList != null && catListSize > 0)
        {
            /** For ADD CALLSCRIPT Block */
            for (int i = 0; i < catListSize; i++)
            {

                CallScriptCategoryVO callScriptCategoryVO = (CallScriptCategoryVO) catList
                        .get(i);

                if (callScriptCategoryVO.getIncludecallScript())
                {

                    callScriptCategoryVO
                            .setStatus(ContactManagementConstants.STATUS_NONE);
                    associatedCatList.add(callScriptCategoryVO);

                }
                /* Store the Unchecked records */
                else
                {

                    if (callScriptDataBean.isEditCallScritFlag()
                            && oldAssignedCaTListSize > 0)
                    {
                        callScriptCategoryVO
                                .setStatus(ContactManagementConstants.STATUS_NONE);
                        callScriptCategoryVO = getDeassocitedCatList(
                                callScriptCategoryVO, oldAssignedCaTList);

                        if (callScriptCategoryVO.getStatus().equalsIgnoreCase(
                                ContactManagementConstants.STATUS_DELETED))
                        {
                            deassociateCatList.add(callScriptCategoryVO);
                        }
                    }

                }

            }

            finalCatList.addAll(associatedCatList);
            boolean flag = (callScriptDataBean.isEditCallScritFlag()
                    && deassociateCatList != null && !deassociateCatList
                    .isEmpty());
            if (flag)
            {
                finalCatList.addAll(deassociateCatList);
            }

        }
        if(logger.isDebugEnabled())
        {
        	logger.debug("size of finalCatList----------->" + finalCatList.size());
        }
        
        return finalCatList;
    }

    /**
     * This Method returns List of categories that are Deassociated.
     * 
     * @param callScriptCategoryVO
     *            Takes The callScriptCategoryVO as param .
     * @param oldAssigCatList
     *            takes List of oldAssigCatList .
     * @return callScriptCategoryVO Returns callScriptCategoryVO of Categories
     *         that is deassigned
     */
    public CallScriptCategoryVO getDeassocitedCatList(
            CallScriptCategoryVO callScriptCategoryVO, List oldAssigCatList)
    {
    	
        for (Iterator iter = oldAssigCatList.iterator(); iter.hasNext();)
        {
            CallScriptCategoryVO deAssocallScriptCategoryVO = (CallScriptCategoryVO) iter
                    .next();
            if (deAssocallScriptCategoryVO.getCategoryVO().getCategorySK()
                    .compareTo(
                            callScriptCategoryVO.getCategoryVO()
                                    .getCategorySK()) == 0)
            {
                callScriptCategoryVO
                        .setStatus(ContactManagementConstants.STATUS_DELETED);
            }

        }

        
        return callScriptCategoryVO;
    }

    /**
     * This Method returns List of categories that are Deassociated.
     * 
     * @param callScriptSubjectVO
     *            Takes The callScriptSubjectVO as param .
     * @param oldAssigSubList
     *            takes List ofoldAssigSubList .
     * @return CallScriptSubjectVO Returns CallScriptSubjectVO that is
     *         deassigned
     */
    public CallScriptSubjectVO getDeassocitedSubList(
            CallScriptSubjectVO callScriptSubjectVO, List oldAssigSubList)
    {

    	for (Iterator iter = oldAssigSubList.iterator(); iter.hasNext();)
        {
            CallScriptSubjectVO deAssoCallScriptSubVO = (CallScriptSubjectVO) iter
                    .next();
            if (deAssoCallScriptSubVO.getSubjectCode().equalsIgnoreCase(
                    callScriptSubjectVO.getSubjectCode()))
            {
                callScriptSubjectVO
                        .setStatus(ContactManagementConstants.STATUS_DELETED);
            }

        }

        
        return callScriptSubjectVO;
    }

    /**
     * This Method returns List of Entities that are Deassociated.
     * 
     * @param callScriptEntityVO
     *            Takes The callScriptEntityVO as param .
     * @param oldAssigEntList
     *            Takes List of oldAssigEntList .
     * @return CallScriptEntityVO Returns CallScriptEntityVO that is deassigned
     */
    public CallScriptEntityVO getDeassocitedEntList(
            CallScriptEntityVO callScriptEntityVO, List oldAssigEntList)
    {
        for (Iterator iter = oldAssigEntList.iterator(); iter.hasNext();)
        {
            CallScriptEntityVO deAssoCallScriptEntVO = (CallScriptEntityVO) iter
                    .next();
            if (deAssoCallScriptEntVO.getEntityTypeCode().equalsIgnoreCase(
                    callScriptEntityVO.getEntityTypeCode()))
            {
                callScriptEntityVO
                        .setStatus(ContactManagementConstants.STATUS_DELETED);
            }

        }

        
        return callScriptEntityVO;
    }

    /**
     * This method Renders the Add Block on clicking the Add Call script Method.
     * 
     * @return String
     */
    public String addCallScripts()
    {

        //heap dump panel tab issue fix
		//callScriptDataBean.getHtmlPanelTabbedPane().setSelectedIndex(0);
		callScriptDataBean.setSelectedIndex(0);

        /**
         * Calls getAllEnityType method to populate all Entities in the data
         * Table
         */
        getAllEnityType();
        /**
         * Calls getAllCategory method to populate all Categories in the data
         * Table
         */
        getAllCategory();
        /**
         * Calls getSubDataTableList method to populate all Subjects in the data
         * Table
         */
        getSubDataTableList();
        /** Populate the Data Table for Entity Tab */

        if (callScriptDataBean.getAllEntityTypeList() != null
                && callScriptDataBean.getAllEntityTypeList().size() > 0)
        {
            callScriptDataBean.setEntityList(callScriptDataBean
                    .getAllEntityTypeList());
        }

        /** Populate the Data Table for Category Tab */
        if (callScriptDataBean.getAllCategoryList() != null
                && callScriptDataBean.getAllCategoryList().size() > 0)
        {
            callScriptDataBean.setCategoryList(callScriptDataBean
                    .getAllCategoryList());
        }

        /** Populate the Data Table for Subject Tab */
        if (callScriptDataBean.getAllSubjectList() != null
                && callScriptDataBean.getAllSubjectList().size() > 0)
        {
        	callScriptDataBean.setSubjectList(callScriptDataBean
                    .getAllSubjectList());
        }
        callScriptDataBean.setCallScriptVO(new CallScriptVO());
        callScriptDataBean.setShowAddCallScripts(true);
        callScriptDataBean.setShowEditCallScripts(false);
        callScriptDataBean.setShowSucessMessage(false);
		callScriptDataBean.setInputHiddenId("editCallScriptFocus");
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This Method returns List of All Entities.
     */
    public void getAllEnityType()
    {

    	
        /** Get all entities from VV -- bug ESPRD00003200 */
        //FindBug Issue Resolved
        //List entityTypeVVList = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
        List entityTypeVVList =null;
        List callScriptEntityVOList = new ArrayList();
        List callScriptEntityDOList = new ArrayList();
        //FindBug Issue Resolved
        //List finalEntityList = new ArrayList(ContactManagementConstants.DEFAULT_SIZE);
        List finalEntityList =null;
        CallScriptDOConvertor callScriptDOConvertor = new CallScriptDOConvertor();

        try
        {
            callScriptEntityDOList = contactMaintenanceDelegate
                    .getAllCallScriptEntityTypes();
        }

        catch (CallscriptFetchBusinessException e)
        {
        	if(logger.isErrorEnabled())
        	{
        		logger.error(e.getMessage(), e);
        	}
        }

        /** Calls Converter class to get list of allentityVO */

        entityTypeVVList = callScriptDOConvertor.getAllEntityVVList();

        /** Calls Converter class to get list of allCallScriptEntityVO */

        if (callScriptEntityDOList != null && !callScriptEntityDOList.isEmpty())
        {
            callScriptEntityVOList = callScriptDOConvertor
                    .getAllCallScriptEntityVOList(callScriptEntityDOList);
        }

        /**
         * calls getFinalEntityTypeList Method to get the Final Enity List
         * displayed in UI
         */

        finalEntityList = getFinalEntityTypeList(callScriptEntityVOList,
                entityTypeVVList);
        /**
         * Stores List of allEntityTypes to List in Data Bean.
         */
        if (finalEntityList != null && !finalEntityList.isEmpty())
        {
            callScriptDataBean.setAllEntityTypeList(finalEntityList);
            if(logger.isDebugEnabled())
            {
            	logger.debug("inside getAllEnityType ---*******" + callScriptDataBean.getAllEntityTypeList().size());
            }
        }

        

    }

    /**
     * This Method returns List of subjects from subject Auxiliary Table.
     * 
     * @return entityTypeVOList
     */
    public List getSubjectsFromTable()
    {
    	
        List subjectVOList = new ArrayList();
        List subjectDOList = new ArrayList();

        CallScriptDOConvertor callScriptDOConvertor = new CallScriptDOConvertor();

        try
        {
            subjectDOList = contactMaintenanceDelegate.getAllSubjects();

        }

        catch (CallscriptFetchBusinessException e)
        {

        	if(logger.isErrorEnabled())
        	{
        		logger.error(e.getMessage(), e);
        	}
        }

        /** Calls Converter class to get list of callScriptentityVO */
        if (subjectDOList != null && !subjectDOList.isEmpty())
        {
            subjectVOList = callScriptDOConvertor
                    .getAllCallScriptSubVOList(subjectDOList);

        }
        
        return subjectVOList;
    }

    /**
     * This Method Compares allEntity List and allcallScriptEntityList to fetch
     * the Final List.
     * 
     * @param callScriptEntList
     *            Takes callScriptEntList as first parameter.
     * @param entList
     *            Takes entList as second parameter.
     * @return List Returns List of Final Entity Types
     */
    public List getFinalEntityTypeList(List callScriptEntList, List entList)
    {
    	
        int callScriptEntListSize = 0;
        int entListSize = 0;
        if (entList != null && callScriptEntList != null)
        {
            callScriptEntListSize = callScriptEntList.size();
            entListSize = entList.size();

        }
        for (int i = 0; i < entListSize; i++)
        {
            CallScriptEntityVO entityVo = (CallScriptEntityVO) entList.get(i);
            entityVo.setStatus(ContactManagementConstants.STATUS_NEW);
            entityVo.setRowIndexCount(i);
            for (int j = 0; j < callScriptEntListSize; j++)
            {
                CallScriptEntityVO callscriptEntityVO = (CallScriptEntityVO) callScriptEntList
                        .get(j);
                if (StringUtils.equalsIgnoreCase(entityVo.getEntityTypeCode(),
                        callscriptEntityVO.getEntityTypeCode()))
                {
                  
                    entityVo.setAssignedCallScript(callscriptEntityVO
                            .getAssignedCallScript());
                    entityVo
                            .setStatus(ContactManagementConstants.STATUS_ASSIGNED);
                    break;

                }
            }

        }
        
        return entList;
    }

    /**
     * This Method is used to get List of Category Vo.
     */
    public void getAllCategory()
    {
    	
        List categoryVOList = new ArrayList();
        List categoryDOList = new ArrayList();

        CallScriptDOConvertor callScriptDOConvertor = new CallScriptDOConvertor();

        CategorySearchCriteriaVO categorySearchCriteriaVO = new CategorySearchCriteriaVO();
        categorySearchCriteriaVO.setVoidindicator(false);

        try
        {

            categoryDOList = contactMaintenanceDelegate
                    .getAllCategories(categorySearchCriteriaVO);

        }
        catch (CategoryFetchBusinessException e)
        {
        	if(logger.isErrorEnabled())
        	{
        		logger.error(e.getMessage(), e);
        	}
        }

        /** Calls Converter class to get list of categoryVO */
        if (categoryDOList != null && !categoryDOList.isEmpty())
        {
            categoryVOList = callScriptDOConvertor
                    .getAllCallScriptCatVOList(categoryDOList);

        }

        /**
         * Stores the All categories List to list in data Bean.
         */
        if (categoryVOList != null && !categoryVOList.isEmpty())
        {
            callScriptDataBean.setAllCategoryList(categoryVOList);
        }

        

    }

    /**
     * This Method cancels Edit Call Script Block .
     */
    public void cancelEditCallScripts()
    {
    	
        callScriptDataBean.setShowAddCallScripts(false);
        callScriptDataBean.setShowEditCallScripts(false);
        

    }

    /**
     * This Method cancels Edit Call Script Block .
     */
    public void cancelAddCallScripts()
    {
    	
        callScriptDataBean.setShowAddCallScripts(false);
        callScriptDataBean.setShowEditCallScripts(false);
        
    }

    /**
     * This method Resets the Call Scripts in Add Call Script Block .
     * 
     * @return String
     */
    public String resetCallScripts()
    {
    	
        CallScriptVO callScriptVO = callScriptDataBean.getCallScriptVO();
        callScriptVO.setCallScriptSK(null);
        callScriptVO.setCallScriptStatus(ContactManagementConstants.YES);
        callScriptVO.setCallScriptFullText(null);
        callScriptVO.setDescription(null);
        callScriptDataBean.setCallScriptVO(callScriptVO);
        clearDataTablesList();

        /**
         * Calls getAllEnityType method to populate all Entities in the data
         * Table
         */
        getAllEnityType();
        /**
         * Calls getAllCategory method to populate all Categories in the data
         * Table
         */
        getAllCategory();
        /**
         * Calls getSubDataTableList method to populate all Subjects in the data
         * Table
         */
        getSubDataTableList();
        /** Populate the Data Table for Entity Tab */

        if (callScriptDataBean.getAllEntityTypeList() != null
                && callScriptDataBean.getAllEntityTypeList().size() > 0)
        {
            callScriptDataBean.setEntityList(callScriptDataBean
                    .getAllEntityTypeList());
        }

        /** Populate the Data Table for Category Tab */
        if (callScriptDataBean.getAllCategoryList() != null
                && callScriptDataBean.getAllCategoryList().size() > 0)
        {
            callScriptDataBean.setCategoryList(callScriptDataBean
                    .getAllCategoryList());
        }

        /** Populate the Data Table for Subject Tab */
        if (callScriptDataBean.getAllSubjectList() != null
                && callScriptDataBean.getAllSubjectList().size() > 0)
        {
        	
            callScriptDataBean.setSubjectList(callScriptDataBean
                    .getAllSubjectList());
        }
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method Resets the Call Scripts in Edit Call Script Block .
     * 
     * @return String
     */
    public String resetEditCallScripts()
    {
    	
        List catListforEditCallScript = new ArrayList();
        List entListforEditCallScript = new ArrayList();
        List subListforEditCallScript = new ArrayList();
        int index = callScriptDataBean.getEditCallScritRowIndex();
        CallScriptVO callScriptVO = (CallScriptVO) callScriptDataBean
                .getMaintainCallScriptList().get(index);
        callScriptDataBean.setCallScriptVO(callScriptVO);
        /**
         * Calls getAllEnityType method to populate all Entities in the data
         * Table
         */
        getAllEnityType();
        /**
         * Calls getAllCategory method to populate all Categories in the data
         * Table
         */
        getAllCategory();
        /**
         * Calls getSubDataTableList method to populate all Subjects in the data
         * Table
         */
        getSubDataTableList();
        /** Populate the Data Table for Entity Tab */

        /** CATEGORY DATA TABLE */

        /** Get the list of all Categories assigned to this Call script */

        if (callScriptDataBean.getAllCategoryList() != null
                && callScriptDataBean.getAllCategoryList().size() > 0)
        {

            catListforEditCallScript = showAssignedCategories(
                    callScriptDataBean.getAllCategoryList(), callScriptVO
                            .getCategory());
        }

        /** Add the category List to the Respective List in Data Bean */
        callScriptDataBean.setCategoryList(catListforEditCallScript);

        /** ENTITY TYPE DATA TABLE */

        /** Get the list of all Entity Types assigned to this Call script */

        if (callScriptDataBean.getAllEntityTypeList() != null
                && callScriptDataBean.getAllEntityTypeList().size() > 0)
        {
            entListforEditCallScript = showAssignedEntities(callScriptDataBean
                    .getAllEntityTypeList(), callScriptVO);
        }
        /** Add the Entity List to the Respective List in Data Bean */
        callScriptDataBean.setEntityList(entListforEditCallScript);

        /** SUBJECT TYPE DATA TABLE */
        /** Get the list of all subjects assigned to this Call script */

        if (callScriptDataBean.getAllSubjectList() != null
                && callScriptDataBean.getAllSubjectList().size() > 0)
        {

            subListforEditCallScript = showAssignedSubjects(callScriptDataBean
                    .getAllSubjectList(), callScriptVO.getSubject());
        }
        /** Add the Subject List to the Respective List in Data Bean */
        callScriptDataBean.setSubjectList(subListforEditCallScript);

        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * Validates Call script to be not Null.
     * 
     * @author Wipro
     * @return String
     */
    public boolean validateCallScripts(boolean isUpdate)
    {
    	boolean flag = true;
        
        String callScriptDesc = callScriptDataBean.getCallScriptVO()
                .getDescription();
        String callScriptText = callScriptDataBean.getCallScriptVO()
                .getCallScriptFullText();
        if (callScriptDesc == null
                || ContactManagementConstants.EMPTY_STRING
                        .equals(callScriptDesc.trim()))
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("condition 1 failed");
        	}
            flag = false;
            setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.DESCRIPTION},
                    ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
                    ContactManagementConstants.CALLSCRIPT_DESC);
            callScriptDataBean.setDataFlag(false);

        }
        else
        {
        	if (!EnterpriseCommonValidator
                    .validateAlphaSpecialChars(callScriptDesc.trim()))
            {
                flag = false;
               
                callScriptDataBean.setDataFlag(false);
                setErrorMessage(EnterpriseMessageConstants.ERR_SW_INVALID,
                        new Object[] {ContactManagementConstants.DESCRIPTION},
                        MessageUtil.ENTMESSAGES_BUNDLE,
                        ContactManagementConstants.CALLSCRIPT_DESC);
            }
            else
            {
            	
                boolean descflag = true;
                descflag = validateDesc(callScriptDataBean.getCallScriptVO(),
                        isUpdate);
                if(logger.isDebugEnabled())
                {
                	logger.debug("Desc flag value is:" + descflag);
                }
                if (!descflag)
                {
                    flag = false;
                    callScriptDataBean.setDataFlag(false);
                }
            }

        }
        if (callScriptText == null
                || ContactManagementConstants.EMPTY_STRING
                        .equals(callScriptText.trim()))
        {
            flag = false;
           
            setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.CALLSCRIPT_TEXT},
                    ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "callscrptext");
            callScriptDataBean.setDataFlag(false);
        }
        
        return flag;
    }

    /**
     * This method validates short and long description
     * 
     * @param oldcaseStepVO
     */
    private boolean validateDesc(CallScriptVO newCallScriptVO, boolean isUpdate)
    {
        boolean flag = true;
        if(logger.isDebugEnabled())
        {
        	logger.debug("inside validateDesc");
        }
        if (isUpdate)
        {
            int index = callScriptDataBean.getEditCallScritRowIndex();
            if(logger.isDebugEnabled())
            {
            	logger.debug("Index value in Validate Description : " + index);
            }
            List callScriptOldList = callScriptDataBean
                    .getMaintainCallScriptList();
            //List tempList = new ArrayList();
            //tempList = callScriptOldList;
            //tempList.remove(index);
            for (Iterator iter = callScriptOldList.iterator(); iter.hasNext();)
            {
                CallScriptVO callScriptVO = (CallScriptVO) iter.next();
                if (callScriptVO.getDescription() != null)
                {
                    if((callScriptVO.getDescription().equalsIgnoreCase(
                            newCallScriptVO.getDescription())) 
                    		&&(!callScriptVO.getCallScriptSK().equals(newCallScriptVO.getCallScriptSK())))
                    {
                    	flag = false;
                    	callScriptDataBean.setShowCallScriptDesc(true);
                        callScriptDataBean.setShowSucessMessage(false);
                        logger.debug("Description already exists in Update");
                       	ContactManagementHelper
							.setErrorMessage(MaintainContactManagementUIConstants.CALLSCRIPTDESC_SHOULD_UNIQUE);
                       
                       	break;
                    }
                }
            }
        }
        else
        {
            List callScriptOldList = callScriptDataBean
                    .getMaintainCallScriptList();
            for (Iterator iter = callScriptOldList.iterator(); iter.hasNext();)
            {
                CallScriptVO callScriptVO = (CallScriptVO) iter.next();
                if (callScriptVO.getDescription() != null)
                {
                    if (callScriptVO.getDescription().equalsIgnoreCase(
                            newCallScriptVO.getDescription()))

                    {
                        flag = false;
                        
                        callScriptDataBean.setShowCallScriptDesc(true);
                        callScriptDataBean.setShowSucessMessage(false);
                       
                        ContactManagementHelper
                                .setErrorMessage(MaintainContactManagementUIConstants.CALLSCRIPTDESC_SHOULD_UNIQUE);
                        break;
                    }
                }

            }
        }
       
        return flag;
    }

    /**
     * Gets reference of CallScriptDataBean.
     * 
     * @author Wipro.
     * @return DiagnosisCodeDataBean
     */
    public CallScriptDataBean getCallScriptDataBean()
    {

       

        FacesContext fc = FacesContext.getCurrentInstance();
        CallScriptDataBean callScriptDataBean = (CallScriptDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + ContactManagementConstants.CALLSCRIPT_BEAN_NAME + "}").getValue(fc);

        
        return callScriptDataBean;
    }

    /**
     * This operation will be used to get the reference data.
     * 
     * @return subValidValueList Returns List of Subject valid values.
     */
    private List getReferenceData()
    {
    	InputCriteria inputCriteria = null;
        List list = new ArrayList();
        int listSize = 0;
        List subValidValueList = new ArrayList();
        //FindBug Issue Resolved
        //HashMap map = new HashMap();
        HashMap map =null;
        ReferenceDataSearchVO referenceDataSearch = null;
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        ReferenceDataListVO referenceDataListVO = null;

        //	filling the drop down of Subjectst
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);
        list.add(inputCriteria);

        referenceDataSearch = new ReferenceDataSearchVO();
        referenceDataSearch.setInputList(list);
        //FindBug Issue Resolved
       // referenceServiceDelegate = new ReferenceServiceDelegate();
        
        
        //		Pass the list to the delegate
       
        //FindBug Issue Resolved
       // referenceDataListVO = new ReferenceDataListVO();
        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);
            if(logger.isDebugEnabled())
            {
            	logger.debug("hashMap size is = " + referenceDataListVO.getResponseMap().size());
            }
            	map = referenceDataListVO.getResponseMap();
            list = (List) map.get(FunctionalAreaConstants.CONTACT_MGMT
                    + ProgramConstants.HASH_SEPARATOR
                    + ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);
            listSize = list.size();
            for (int i = 0; i < listSize; i++)
            {
                ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
                subValidValueList.add(refVo);

            }
            if(logger.isDebugEnabled())
            {
            	logger.debug("size  of subvvList" + subValidValueList.size());
            }
        }
        catch (ReferenceServiceRequestException e)
        {
        	if(logger.isErrorEnabled())
        	{
                 	logger.error(e.getMessage(), e);
        	}
        }
        catch (SystemListNotFoundException e)
        {
        	if(logger.isErrorEnabled())
        	{
        		logger.error(e.getMessage(), e);
        	}
        }
        finally
        {
            
        }
        return subValidValueList;
    }

    /**
     * This method holds the list that needs to be displayed in the Subject
     * datatable .
     */
    public void getSubDataTableList()
    {
    	List subRefValueList = null;
        List allSubList = null;
        int subRefValueListSize = 0;
        int allSubjectListSize = 0;
        /**
         * Calls getReferenceData method and sets the list of subject valid
         * Values to data bean list
         */
        callScriptDataBean.setSubjectValidvalues(getReferenceData());

        subRefValueList = callScriptDataBean.getSubjectValidvalues();
        allSubList = getSubjectsFromTable();

        List finalSubList = new ArrayList();
        if (subRefValueList != null)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("size of subRefValueList" + subRefValueList.size());
        	}
            subRefValueListSize = subRefValueList.size();
        }
        if (allSubList != null)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("size of allSubList" + allSubList.size());
        	}
            allSubjectListSize = allSubList.size();
        }
        for (int i = 0; i < subRefValueListSize; i++)
        {
            /*logger.debug("**** inside firts for loop ******");*/
            CallScriptSubjectVO callScriptSubjectVOFinal = new CallScriptSubjectVO();
            
            ReferenceServiceVO referenceServiceVO = (ReferenceServiceVO) subRefValueList
                    .get(i);
            boolean indFalg = (referenceServiceVO != null
                    && referenceServiceVO.getShortDescription() != null && !ContactManagementConstants.EMPTY_STRING
                    .equals(referenceServiceVO.getShortDescription().trim()));
            if (indFalg)
            {
            	
                callScriptSubjectVOFinal
                        .setSubjectDescription(referenceServiceVO
                                .getShortDescription());
                callScriptSubjectVOFinal.setSubjectCode(referenceServiceVO
                        .getValidValueCode());
                callScriptSubjectVOFinal.setRowsubIndexCount(i);
                for (int j = 0; j < allSubjectListSize; j++)
                {

                    CallScriptSubjectVO callScriptSubjectVO = (CallScriptSubjectVO) allSubList
                            .get(j);
                    boolean flag = (callScriptSubjectVO != null
                            && referenceServiceVO.getValidValueCode()
                                    .equalsIgnoreCase(
                                            callScriptSubjectVO
                                                    .getSubjectCode()) && callScriptSubjectVO
                            .getAssignedCallScript() != null);

                    if (flag)
                    {

                        callScriptSubjectVOFinal
                                .setAssignedCallScript(callScriptSubjectVO
                                        .getAssignedCallScript());
                        

                        break;
                    }
                    
                }
                finalSubList.add(callScriptSubjectVOFinal);
            }

        }

        /**
         * Stores The List to List in Data Bean.
         */
        if (finalSubList != null && !finalSubList.isEmpty())
        {
            callScriptDataBean.setAllSubjectList(finalSubList);
        }
        if(logger.isDebugEnabled())
        {
        	logger.debug("size of final sub list returned" + finalSubList.size());
        }
        
    }

    /**
     * This Method sorts the Category List . calls CategoryComparator method.
     * 
     * @param event
     *            Takes event as parameter
     */
    public void sortCategory(ActionEvent event)
    {
    	String sortColumn = (String) event.getComponent().getAttributes().get(
                "columnName");
        String sortOrder = (String) event.getComponent().getAttributes().get(
                "sortOrder");
        callScriptDataBean.setImageRender(event.getComponent().getId());
        if(logger.isDebugEnabled())
        {
        	logger.debug("The Sort column is--->" + sortColumn);
        	logger.debug("the sort order is--->" + sortOrder);
        }
        /**
         * calls EntityTypeComparator method which performs comparison between
         * rows
         */
        categoryComparator(sortColumn, sortOrder, callScriptDataBean
                .getCategoryList());

        
    }

    /**
     * This Method Sorts The data table of Category Section.
     * 
     * @param sortColumn
     *            Takes sortColumn as Parameter
     * @param sortOrder
     *            Takes sortOrder as Parameter
     * @param dataList
     *            Takes dataList as Parameter
     */
    private void categoryComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
    	     
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                CallScriptCategoryVO data1 = (CallScriptCategoryVO) obj1;
                CallScriptCategoryVO data2 = (CallScriptCategoryVO) obj2;
                boolean ascending = false;

                ascending = (ContactManagementConstants.SORT_ASC
                        .equalsIgnoreCase(sortOrder)) ? true : false;

                /** sorts Include Call Script Column */
                if (ContactManagementConstants.CALLSCRIPT_INCLUDE
                        .equals(sortColumn))
                {
                    String bool1 = ContactManagementConstants.EMPTY_STRING
                            + data1.getIncludecallScript();
                    String bool2 = ContactManagementConstants.EMPTY_STRING
                            + data2.getIncludecallScript();

                    return ascending ? bool1.compareTo(bool2) : bool2
                            .compareTo(bool1);
                }
                /** Sorts Category Column */
                if (ContactManagementConstants.CALLSCRIPT_CATVALUE
                        .equals(sortColumn))
                {
                    String str1 = data1.getCategoryVO().getCategoryDesc();
                    String str2 = data2.getCategoryVO().getCategoryDesc();
                    return ascending ? str1.compareTo(str2) : str2
                            .compareTo(str1);
                }
                /** Sorts Assigned call Script Column */
                if (ContactManagementConstants.CALLSCRIPT_ASSIGCSVALUE
                        .equals(sortColumn))
                {

                    if (data1.getAssignedCallScript() == null)
                    {
                        data1
                                .setAssignedCallScript(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (data2.getAssignedCallScript() == null)
                    {
                        data2
                                .setAssignedCallScript(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getAssignedCallScript().compareTo(
                            data2.getAssignedCallScript()) : data2
                            .getAssignedCallScript().compareTo(
                                    data1.getAssignedCallScript());

                }

                return 0;

            }

        };
        Collections.sort(dataList, comparator);
        
    }

    /**
     * This Method sorts the Entity List . calls EntityTypeComparator method.
     * 
     * @param event
     *            Takes event as parameter
     */
    public void sortEntityType(ActionEvent event)
    {
    	String sortColumn = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.COLUMN_NAME);
        String sortOrder = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.SORT_ORDER);
        callScriptDataBean.setImageRender(event.getComponent().getId());
        if(logger.isDebugEnabled())
        {
        	logger.debug("The Sort column is--->" + sortColumn);
        	logger.debug("the sort order is--->" + sortOrder);
        }
        /**
         * calls EntityTypeComparator method which performs comparison between
         * rows
         */
        entityTypeComparator(sortColumn, sortOrder, callScriptDataBean
                .getEntityList());
        
    }

    /**
     * This Method Sorts The data table of Entity Section.
     * 
     * @param sortColumn
     *            Takes sortColumn as Parameter
     * @param sortOrder
     *            Takes sortOrder as Parameter
     * @param dataList
     *            Takes dataList as Parameter
     */
    private void entityTypeComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
    	
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                CallScriptEntityVO data1 = (CallScriptEntityVO) obj1;
                CallScriptEntityVO data2 = (CallScriptEntityVO) obj2;
                boolean ascending = false;

                ascending = (ContactManagementConstants.SORT_ASC
                        .equalsIgnoreCase(sortOrder)) ? true : false;
                /** sorts Include Call Script Column */
                if (ContactManagementConstants.CALLSCRIPT_INCLUDE
                        .equals(sortColumn))
                {
                    String bool1 = ContactManagementConstants.EMPTY_STRING
                            + data1.getIncludecallScript();
                    String bool2 = ContactManagementConstants.EMPTY_STRING
                            + data2.getIncludecallScript();

                    return ascending ? bool1.compareTo(bool2) : bool2
                            .compareTo(bool1);
                }
                /** Sorts Entity Column */
                if (ContactManagementConstants.CALLSCRIPT_ENTITYVAL
                        .equals(sortColumn))
                {

                    if (data1.getEntityDescription() == null)
                    {
                        data1
                                .setEntityDescription(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (data2.getEntityDescription() == null)
                    {
                        data2
                                .setEntityDescription(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getEntityDescription().compareTo(
                            data2.getEntityDescription()) : data2
                            .getEntityDescription().compareTo(
                                    data1.getEntityDescription());
                }
                /** Sorts Assigned call Script Column */
                if (ContactManagementConstants.CALLSCRIPT_ASSIGCSVALUE
                        .equals(sortColumn))
                {

                    if (data1.getAssignedCallScript() == null)
                    {
                        data1
                                .setAssignedCallScript(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (data2.getAssignedCallScript() == null)
                    {
                        data2
                                .setAssignedCallScript(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getAssignedCallScript().compareTo(
                            data2.getAssignedCallScript()) : data2
                            .getAssignedCallScript().compareTo(
                                    data1.getAssignedCallScript());

                }

                return 0;

            }

        };
        Collections.sort(dataList, comparator);
        
    }

    /**
     * This Method sorts the Subject List . calls EntityTypeComparator method.
     * 
     * @param event
     *            Takes event as parameter
     */
    public void sortSubjects(ActionEvent event)
    {
    	
        String sortColumn = (String) event.getComponent().getAttributes().get(
                "columnName");
        String sortOrder = (String) event.getComponent().getAttributes().get(
                "sortOrder");
        callScriptDataBean.setImageRender(event.getComponent().getId());
        if(logger.isDebugEnabled())
        {
        	logger.debug("The Sort column is--->" + sortColumn);
        	logger.debug("the sort order is--->" + sortOrder);
        }
        /**
         * calls EntityTypeComparator method which performs comparison between
         * rows
         */
        subjectComparator(sortColumn, sortOrder, callScriptDataBean
                .getSubjectList());
        
    }

    /**
     * This Method Sorts The data table of Entity Section.
     * 
     * @param sortColumn
     *            Takes sortColumn as Parameter
     * @param sortOrder
     *            Takes sortOrder as Parameter
     * @param dataList
     *            Takes dataList as Parameter
     */
    private void subjectComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
    	
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                CallScriptSubjectVO data1 = (CallScriptSubjectVO) obj1;
                CallScriptSubjectVO data2 = (CallScriptSubjectVO) obj2;
                boolean ascending = false;

                ascending = (ContactManagementConstants.SORT_ASC
                        .equalsIgnoreCase(sortOrder)) ? true : false;
                /** sorts Include Call Script Column */
                if (ContactManagementConstants.CALLSCRIPT_INCLUDE
                        .equals(sortColumn))
                {
                    String bool1 = ContactManagementConstants.EMPTY_STRING
                            + data1.getIncludecallScript();
                    String bool2 = ContactManagementConstants.EMPTY_STRING
                            + data2.getIncludecallScript();

                    return ascending ? bool1.compareTo(bool2) : bool2
                            .compareTo(bool1);
                }
                /** Sorts Subject Column */
                if (ContactManagementConstants.CALLSCRIPT_SUBVAL
                        .equals(sortColumn))
                {

                    String str1 = data1.getSubjectDescription();
                    String str2 = data2.getSubjectDescription();
                    return ascending ? str1.compareTo(str2) : str2
                            .compareTo(str1);
                }
                /** Sorts Assigned call Script Column */
                if (ContactManagementConstants.CALLSCRIPT_ASSIGCSVALUE
                        .equals(sortColumn))
                {

                    if (data1.getAssignedCallScript() == null)
                    {
                        data1
                                .setAssignedCallScript(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (data2.getAssignedCallScript() == null)
                    {
                        data2
                                .setAssignedCallScript(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getAssignedCallScript().compareTo(
                            data2.getAssignedCallScript()) : data2
                            .getAssignedCallScript().compareTo(
                                    data1.getAssignedCallScript());

                }

                return 0;

            }

        };
        Collections.sort(dataList, comparator);

        
    }

    /**
     * This operation is used to reset the Entity,Subject,and Category data
     * table. list.
     */
    private void clearDataTablesList()
    {
    	List listOfEnity = callScriptDataBean.getEntityList();
        List listOfSub = callScriptDataBean.getSubjectList();
        List listOfCat = callScriptDataBean.getCategoryList();

        for (Iterator iter = listOfEnity.iterator(); iter.hasNext();)
        {
            CallScriptEntityVO callScriptEntityVO = (CallScriptEntityVO) iter
                    .next();
            callScriptEntityVO.setIncludecallScript(false);
        }

        for (Iterator iter = listOfSub.iterator(); iter.hasNext();)
        {
            CallScriptSubjectVO callScriptSubjectVO = (CallScriptSubjectVO) iter
                    .next();
            callScriptSubjectVO.setIncludecallScript(false);
        }

        for (Iterator iter = listOfCat.iterator(); iter.hasNext();)
        {
            CallScriptCategoryVO callScriptCategoryVO = (CallScriptCategoryVO) iter
                    .next();
            callScriptCategoryVO.setIncludecallScript(false);
        }
       
        
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
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("Component ID " + componentId);
        	}
            UIComponent uiComponent = findComponentInRoot(componentId);
            clientId = uiComponent.getClientId(facesContext);
            if(logger.isDebugEnabled())
            {
            	logger.debug("Client Id " + clientId);
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
    	UIComponent result = null;

        // Is the "base" component itself the match we are looking for?
        if (id.equals(base.getId()))
        {
            result = base;

        }

        else
        {
            // Search through our facets and children
            UIComponent component = null;

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
        }

        
        return result;
    }

    /**
     * This operation is used to delete CallScript.
     * 
     * @return String : Navigation message.
     */
    public String deleteCallscript()
    {
    	int index = callScriptDataBean.getEditCallScritRowIndex();
        CallScriptVO callScriptVO = (CallScriptVO) callScriptDataBean
                .getMaintainCallScriptList().get(index);
        // Code added for the defect ESPRD00795452 to fix the audit delete issue.
        String userId = getUserID();
        if(logger.isDebugEnabled())
        {
        	logger.debug("size of ass cat ============" + callScriptVO.getCategory().size());
        	logger.debug("size of ass ent ============" + callScriptVO.getEntType().size());
        	logger.debug("size of ass sub ============" + callScriptVO.getSubject().size());
        }

        boolean indicator = ((callScriptVO.getCategory() != null && callScriptVO
                .getCategory().size() > 0)
                || (callScriptVO.getSubject() != null && callScriptVO
                        .getSubject().size() > 0) || (callScriptVO.getEntType() != null && callScriptVO
                .getEntType().size() > 0));
        if (indicator)
        {
        	
            setErrorMessage(
                    ContactManagementConstants.CANNOT_DELETE_CALLSCRIPT_WITH_ASSOCIAIONS,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);

        }

        else
        {
        	
            CallScriptDOConvertor callScriptDOConvertor = new CallScriptDOConvertor();
            CallScript callScriptDO = callScriptDOConvertor
                    .convertCallScriptVOToDO(callScriptVO);
            ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

            Boolean deleted = Boolean.FALSE;

            try
            {
            	// Code added for the defect ESPRD00795452 to fix the audit delete issue.
                //deleted = contactMaintenanceDelegate.deleteCallScript(callScriptDO, callScriptDO.getAuditUserID());
            	deleted = contactMaintenanceDelegate.deleteCallScript(callScriptDO, userId);
            }
            catch (CallscriptDeleteBusinessException e)
            {
            	if(logger.isErrorEnabled())
            	{
            		logger.error(e.getMessage(), e);
            	}
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
            }

            if (deleted.booleanValue())
            {

                callScriptDataBean.getMaintainCallScriptList().remove(index);

                if (callScriptDataBean.getMaintainCallScriptList().size() == 0)
                {
                    callScriptDataBean.setNoData(true);
                }
                callScriptDataBean.setCallScriptVO(new CallScriptVO());
                clearDataTablesList();
                callScriptDataBean.setShowEditCallScripts(false);
                callScriptDataBean.setShowAddCallScripts(false);
				setErrorMessage(
                        EnterpriseMessageConstants.CALL_SCRIPT_DEL_SUCCESS,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

            }
            else
            {
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_DELETE_FAILURE,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
            }
        }

        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This Method disables the Tab contents for Inactive CallScript
     * 
     * @param event
     *            Takes the Event Performed on Value change.
     */
    public void inactiveCallScript(ValueChangeEvent event)
    {
    	
        /*
         * String callScriptStatus = callScriptDataBean.getCallScriptVO()
         * .getCallScriptStatus();
         */

        String newValue = (String) event.getNewValue();
        //FindBug Issue Resolved
       if (StringUtils.equalsIgnoreCase(ContactManagementConstants.NO,
                newValue))
        {
            callScriptDataBean.getCallScriptVO().setInactive(true);
			//callScriptDataBean.getCallScriptVO().setInactive(false);

        }
        else
        {
            callScriptDataBean.getCallScriptVO().setInactive(false);
        }
     }
    /**
     * showing Audit Details for CallScript.
     * 
     * @return String
     */
    public String showAuditHistory()
    {
    	
        GlobalAuditsDelegate audit;
        //FindBug Issue Resolved
        //CallScript callScript = new CallScript();
        CallScript callScript =null;
        CallScriptDataBean callScriptDataBean = getCallScriptDataBean();

        try
        {
        	
            audit = new GlobalAuditsDelegate();
            
            CallScriptDOConvertor callScriptDOConvertor = new CallScriptDOConvertor();
            /* Gets Child Domain Object */

            callScript = callScriptDOConvertor.convertCallScriptVOToDO(callScriptDataBean.getCallScriptVO());


            final EnterpriseSearchResultsVO enterpriseResultVO = audit.getAuditLogList(callScript,
					0, ContactManagementConstants.NO_OF_RECORD_TO_FETCH);

            logger.info("<><><> CallScriptControllerBean.showAuditHistory enterpriseResultVO.getSearchResults size = " +enterpriseResultVO.getSearchResults().size()+ "<><><>");
            callScriptDataBean.setCallScriptAuditHistoryList(enterpriseResultVO.getSearchResults());

            /* Added new for Expand */
            callScriptDataBean.setAuditOpen(true);
        }
        catch (GlobalAuditsBusinessException e)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("Error in show child audit history  " + e);
        	}
        }
        catch (GlobalAuditDataException e)
        {
        	e.printStackTrace();
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("Error in show child audit history  " + e);
        	}
        }
        catch (Exception e) {
        	if(logger.isInfoEnabled())
        	{
        		logger.info("<><><> Exception occured on CallScriptControllerBean.showAuditHistory() "+ e +" <><><>");
        	}
		}

        
        
        return ProgramConstants.RETURN_SUCCESS;
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
			callScriptDataBean.setCallScriptAuditRender(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			callScriptDataBean.setSelectedAuditLog(auditLog);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			{
				logger.debug("Error in show column change  " + e);
			}
		}
		return ProgramConstants.RETURN_SUCCESS;
	}
	
	/**
	 * This method will be used for writing the logic for populating search
	 * results for a given search criteria.
	 */
	private void populateList(int currentPage) {
		//FindBug Issue Resolved
		//ArrayList searchList = new ArrayList();

		GlobalAuditsDelegate globalAuditsDelegate = null;
		try {
			globalAuditsDelegate = new GlobalAuditsDelegate();
			CallScriptDOConvertor callScriptDOConvertor = new CallScriptDOConvertor();
			CallScript callScript = callScriptDOConvertor
					.convertCallScriptVOToDO(callScriptDataBean
							.getCallScriptVO());
			
			EnterpriseSearchResultsVO enterpriseResultVO = globalAuditsDelegate.getAuditLogList(callScript,
					(currentPage-1) * ContactManagementConstants.INT_10, ContactManagementConstants.NO_OF_RECORD_TO_FETCH);

			callScriptDataBean.setCallScriptAuditHistoryList(enterpriseResultVO.getSearchResults());
			callScriptDataBean.setCallScriptAuditRender(true);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			{
				logger.debug(e);
			}
		}
	}
	

    /**
     * This method is used for going to next page.
     * 
     * @return ContactManagementConstants.NEXT_SUCCESS.
     */
    public String next()
    {	
    	long entryTime = System.currentTimeMillis();

		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ callScriptDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ callScriptDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ callScriptDataBean.getStartRecord());
			logger.debug("The end record......."
					+ callScriptDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(callScriptDataBean,
				callScriptDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("SearchSystemParameterControllerBean" + "#" + "next"
					+ "#" + (exitTime - entryTime));
		}

        return ContactManagementConstants.NEXT_SUCCESS;
    }
	
    /**
     * This method is used for data scrolling 
     * @return ContactManagementConstants.PREVIOUS_SUCCESS.
     */
    public String previous()
    {
		long entryTime = System.currentTimeMillis();
		
		EnterpriseBaseDataBean entDataBean = previousPage(callScriptDataBean, callScriptDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("DiagnosisCodeControllerBean" + "#" + "previous" + "#"
					+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}
    	
    	return ContactManagementConstants.PREVIOUS_SUCCESS;
    }
    /**
     * This operation is used to get all the Callscripts sorted on certain
     * column and order.
     * 
     * @param event :
     *            ActionEvent object.
     */
    public void getAllSortedCallScripts(ActionEvent event)
    {
    	
        String sortColumn = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.COLUMN_NAME);
        String sortOrder = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.SORT_ORDER);

        CallScriptSearchCriteriaVO callScriptSearchCriteriaVO = callScriptDataBean
                .getCallScriptSearchCriteriaVO();
        callScriptDataBean.setCsRowIndex(0);
        callScriptDataBean.setImageRender(event.getComponent().getId());
       
        callScriptSearchCriteriaVO.setSortColumn(sortColumn);
        if(logger.isDebugEnabled())
        {
        	logger.debug("sort col----------------" + callScriptSearchCriteriaVO.getSortColumn());
        }

        /* Sort By Active */

        if (ContactManagementConstants.SORT_ASC.equals(sortOrder))
        {
        	
            callScriptSearchCriteriaVO.setAscending(true);

        }

        if (ContactManagementConstants.SORT_DESC.equals(sortOrder))
        {
        	
            callScriptSearchCriteriaVO.setAscending(false);

        }

        callScriptDataBean
                .setCallScriptSearchCriteriaVO(callScriptSearchCriteriaVO);
        if(logger.isDebugEnabled())
        {
        	logger.debug("sort order---------------" + callScriptSearchCriteriaVO.isAscending());
        }

        

    }

    /**
     * @return Returns the contactMaintenanceDelegate.
     */
    public ContactMaintenanceDelegate getContactMaintenanceDelegate()
    {
        return contactMaintenanceDelegate;
    }

    /**
     * @param contactMaintenanceDelegate
     *            The contactMaintenanceDelegate to set.
     */
    public void setContactMaintenanceDelegate(
            ContactMaintenanceDelegate contactMaintenanceDelegate)
    {
        this.contactMaintenanceDelegate = contactMaintenanceDelegate;
    }

    /**
     * @param callScriptDataBean
     *            The callScriptDataBean to set.
     */
    public void setCallScriptDataBean(CallScriptDataBean callScriptDataBean)
    {
        this.callScriptDataBean = callScriptDataBean;
    }
    
	public String closeColumnChange() {
		getCallScriptDataBean().setCallScriptAuditRender(false);
		return ContactManagementConstants.RENDER_SUCCESS;
    }
    

	/**
     * This Method disables the Tab contents for Inactive CallScript
     * 
	 * @return Returns the showActiveScreen.
	 */
	public Boolean getShowActiveScreen() {

		if(invokeShowActiveScreen) {
			
			Map requestMap = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String activeStatus = (String) requestMap.get("activeradioval");
			
			if (activeStatus != null) {
			//FindBug Issue Resolved starts	
				if (StringUtils.equalsIgnoreCase(ContactManagementConstants.NO,
						activeStatus)) {
					callScriptDataBean.getCallScriptVO().setInactive(true);
				//	callScriptDataBean.getCallScriptVO().setInactive(false);

				} else {
					callScriptDataBean.getCallScriptVO().setInactive(false);
				}
				

			}
			invokeShowActiveScreen = false;
		}

		return showActiveScreen;
	}

	/**
	 * @param showActiveScreen
	 *            The showActiveScreen to set.
	 */
	public void setShowActiveScreen(Boolean showActiveScreen) {
		this.showActiveScreen = showActiveScreen;
    }
	private String csEntIndexFlag;
	
	private String csCatIndexFlag;
	
	private String csSubIndexFlag;
	
	/**
	 * @return Returns the csEntIndexFlag.
	 */
	public String getCsEntIndexFlag() {
		return csEntIndexFlag;
	}
	/**
	 * @param csEntIndexFlag The csEntIndexFlag to set.
	 */
	public void setCsEntIndexFlag(String csEntIndexFlag) {
		this.csEntIndexFlag = csEntIndexFlag;
	}
	public String csCategoryAssociation()
    {
	 	
	 	int index=0;
	 	List categoryList = getCallScriptDataBean().getCategoryList();
	 	
	 	if(this.getCsCatIndexFlag()!=null || !this.getCsCatIndexFlag().equalsIgnoreCase(""))
	 	{
	 		getCallScriptDataBean().setShowSucessMessage(false);
	 		index = Integer.parseInt(this.getCsCatIndexFlag());
	 		CallScriptCategoryVO callScriptCategoryVO = (CallScriptCategoryVO)categoryList.get(index);
	 		callScriptCategoryVO.setIncludecallScript(this.isCsCatChkBxVal());
		 	
		 	if(getCallScriptDataBean().isRenderAddorEdit()==false)
		 	{
			 		if(this.isCsCatChkBxVal())
			 		{
			 			callScriptCategoryVO.setAssignedCallScript("");
				 		callScriptCategoryVO.setIncludecallScript(false);
				 		callScriptCategoryVO.setStatus(ContactManagementConstants.STATUS_NEW);
			 		}else if(this.isCsCatChkBxVal()==false)
			 		{
			 			callScriptCategoryVO.setAssignedCallScript(getCallScriptDataBean().getCallScriptVO().getDescription());
				 		callScriptCategoryVO.setIncludecallScript(true);
				 		callScriptCategoryVO.setStatus(ContactManagementConstants.STATUS_ASSIGNED);
			 		}
		 	}
		 	else if(getCallScriptDataBean().isRenderAddorEdit()==true)
		 	{
		 		if(callScriptCategoryVO.getIncludecallScript()==true)
		 		{
		 			callScriptCategoryVO.setAssignedCallScript("");
		 			callScriptCategoryVO.setIncludecallScript(false);
		 			callScriptCategoryVO.setStatus(ContactManagementConstants.STATUS_NEW);
		 		}
		 		else if(callScriptCategoryVO.getIncludecallScript()==false)
		 		{
				 		callScriptCategoryVO.setAssignedCallScript(getCallScriptDataBean().getCallScriptVO().getDescription());
				 		callScriptCategoryVO.setIncludecallScript(true);
				 		callScriptCategoryVO.setStatus(ContactManagementConstants.STATUS_ASSIGNED);
				 		
		 		}
		 	}
	 	}
	 	
	 	List modifiedList = copyDescAssnCSEnt(categoryList,"category");
	 	getCallScriptDataBean().setCategoryList(modifiedList);
        return ContactManagementConstants.RENDER_SUCCESS;
    }
	public String csEntityAssociation()
    {
	 	
	 	int index=0;
	 	List tempList = getCallScriptDataBean().getEntityList();
	 	if(this.getCsEntIndexFlag()!=null || !this.getCsEntIndexFlag().equalsIgnoreCase(""))
	 	{
	 		getCallScriptDataBean().setShowSucessMessage(false);
	 		index = Integer.parseInt(this.getCsEntIndexFlag());
	 		CallScriptEntityVO callScriptEntityVO = (CallScriptEntityVO)tempList.get(index);
		 	callScriptEntityVO.setIncludecallScript(this.isCsChkBxVal());
		 	
		 	if(getCallScriptDataBean().isRenderAddorEdit()==false)
		 	{
			 		if(this.isCsChkBxVal())
			 		{
			 			callScriptEntityVO.setAssignedCallScript("");
				 		callScriptEntityVO.setIncludecallScript(false);
				 		callScriptEntityVO.setStatus(ContactManagementConstants.STATUS_NEW);
			 		}else if(this.isCsChkBxVal()==false)
			 		{
			 			callScriptEntityVO.setAssignedCallScript(getCallScriptDataBean().getCallScriptVO().getDescription());
				 		callScriptEntityVO.setIncludecallScript(true);
				 		callScriptEntityVO.setStatus(ContactManagementConstants.STATUS_ASSIGNED);
			 		}
		 	}
		 	else if(getCallScriptDataBean().isRenderAddorEdit()==true)
		 	{
		 		if(callScriptEntityVO.getIncludecallScript()==true)
		 		{
		 			callScriptEntityVO.setAssignedCallScript("");
		 			callScriptEntityVO.setIncludecallScript(false);
			 		callScriptEntityVO.setStatus(ContactManagementConstants.STATUS_NEW);
		 		}
		 		else if(callScriptEntityVO.getIncludecallScript()==false)
		 		{
				 		callScriptEntityVO.setAssignedCallScript(getCallScriptDataBean().getCallScriptVO().getDescription());
				 		callScriptEntityVO.setIncludecallScript(true);
				 		callScriptEntityVO.setStatus(ContactManagementConstants.STATUS_ASSIGNED);
		 		}
		 	}
	 	}
	 	List modifiedList = copyDescAssnCSEnt(tempList,"entityType");
	 	getCallScriptDataBean().setEntityList(modifiedList);
        return ContactManagementConstants.RENDER_SUCCESS;
    
    }
	
	private void clearDataTablesListAfterAdd(String description)
    {
		
        List listOfEnity = callScriptDataBean.getEntityList();
        List listOfSub = callScriptDataBean.getSubjectList();
        List listOfCat = callScriptDataBean.getCategoryList();

        for (Iterator iter = listOfEnity.iterator(); iter.hasNext();)
        {
            CallScriptEntityVO callScriptEntityVO = (CallScriptEntityVO) iter
                    .next();
            //FindBug Issue Resolved
            //if(!description.trim().equalsIgnoreCase("")&&description!=null)
            if(description!=null && !description.trim().equalsIgnoreCase(""))
            {
            	if(callScriptEntityVO.getAssignedCallScript()!=null)
            	{
            		if(callScriptEntityVO.getAssignedCallScript().equals(description))
	            	{
	            		callScriptEntityVO.setIncludecallScript(true);
	            	}
            	}
            	else
            	{
            		callScriptEntityVO.setIncludecallScript(false);
            	}
            }
        }

        for (Iterator iter = listOfSub.iterator(); iter.hasNext();)
        {
            CallScriptSubjectVO callScriptSubjectVO = (CallScriptSubjectVO) iter
                    .next();
            //FindBug Issue Resolved
            //if(!description.trim().equalsIgnoreCase("")&&description!=null)
            if(description!=null && !description.trim().equalsIgnoreCase(""))
            {
            	if(callScriptSubjectVO.getAssignedCallScript()!=null)
            	{
            		if(callScriptSubjectVO.getAssignedCallScript().equals(description))
	            	{
	            		callScriptSubjectVO.setIncludecallScript(true);
	            	}
            	}
            	else
            	{
            		callScriptSubjectVO.setIncludecallScript(false);
            	}
            }
        }

        for (Iterator iter = listOfCat.iterator(); iter.hasNext();)
        {
            CallScriptCategoryVO callScriptCategoryVO = (CallScriptCategoryVO) iter
                    .next();
            //FindBug Issue Resolved
            //if(!description.trim().equalsIgnoreCase("")&&description!=null)
            if(description!=null && !description.trim().equalsIgnoreCase(""))
            {
            	if(callScriptCategoryVO.getAssignedCallScript()!=null)
            	{
            		if(callScriptCategoryVO.getAssignedCallScript().equals(description))
	            	{
	            		callScriptCategoryVO.setIncludecallScript(true);
	            	}
            	}
            	else
            	{
            		callScriptCategoryVO.setIncludecallScript(false);
            	}
            }
        }
    }
	/**
	 * @return Returns the csChkBxVal.
	 */
	public boolean isCsChkBxVal() {
		return csChkBxVal;
	}
	/**
	 * @param csChkBxVal The csChkBxVal to set.
	 */
	public void setCsChkBxVal(boolean csChkBxVal) {
		this.csChkBxVal = csChkBxVal;
	}
	/**
	 * @return Returns the csCatIndexFlag.
	 */
	public String getCsCatIndexFlag() {
		return csCatIndexFlag;
	}
	/**
	 * @param csCatIndexFlag The csCatIndexFlag to set.
	 */
	public void setCsCatIndexFlag(String csCatIndexFlag) {
		this.csCatIndexFlag = csCatIndexFlag;
	}
	/**
	 * @return Returns the csSubIndexFlag.
	 */
	public String getCsSubIndexFlag() {
		return csSubIndexFlag;
	}
	/**
	 * @param csSubIndexFlag The csSubIndexFlag to set.
	 */
	public void setCsSubIndexFlag(String csSubIndexFlag) {
		this.csSubIndexFlag = csSubIndexFlag;
	}
	/**
	 * @return Returns the csCatChkBxVal.
	 */
	public boolean isCsCatChkBxVal() {
		return csCatChkBxVal;
	}
	/**
	 * @param csCatChkBxVal The csCatChkBxVal to set.
	 */
	public void setCsCatChkBxVal(boolean csCatChkBxVal) {
		this.csCatChkBxVal = csCatChkBxVal;
	}
	
	//csSubAssociation
	public String csSubAssociation()
    {
	 	
	 	int index=0;
	 	List subList = getCallScriptDataBean().getSubjectList();
	 	if(this.getCsSubIndexFlag()!=null || !this.getCsSubIndexFlag().equalsIgnoreCase(""))
	 	{
	 		getCallScriptDataBean().setShowSucessMessage(false);
	 		index = Integer.parseInt(this.getCsSubIndexFlag());
	 		CallScriptSubjectVO callScriptSubjectVO = (CallScriptSubjectVO)subList.get(index);
	 		callScriptSubjectVO.setIncludecallScript(this.isCsSubChkBxVal());
		 	
		 	if(getCallScriptDataBean().isRenderAddorEdit()==false)
		 	{
			 		if(this.isCsSubChkBxVal())
			 		{
			 			callScriptSubjectVO.setAssignedCallScript("");
			 			callScriptSubjectVO.setIncludecallScript(false);
			 			callScriptSubjectVO.setStatus(ContactManagementConstants.STATUS_NEW);
			 		}else if(this.isCsSubChkBxVal()==false)
			 		{
			 			callScriptSubjectVO.setAssignedCallScript(getCallScriptDataBean().getCallScriptVO().getDescription());
			 			callScriptSubjectVO.setIncludecallScript(true);
			 			callScriptSubjectVO.setStatus(ContactManagementConstants.STATUS_ASSIGNED);
			 		}
		 	}
		 	else if(getCallScriptDataBean().isRenderAddorEdit()==true)
		 	{
		 		if(callScriptSubjectVO.getIncludecallScript()==true)
		 		{
		 			callScriptSubjectVO.setAssignedCallScript("");
		 			callScriptSubjectVO.setIncludecallScript(false);
		 			callScriptSubjectVO.setStatus(ContactManagementConstants.STATUS_NEW);
		 		}
		 		else if(callScriptSubjectVO.getIncludecallScript()==false)
		 		{
		 				callScriptSubjectVO.setAssignedCallScript(getCallScriptDataBean().getCallScriptVO().getDescription());
		 				callScriptSubjectVO.setIncludecallScript(true);
				 		callScriptSubjectVO.setStatus(ContactManagementConstants.STATUS_ASSIGNED);
		 		}
		 	}
	 	}
	 	List modifiedList = copyDescAssnCSEnt(subList,"subject");
	 	getCallScriptDataBean().setSubjectList(modifiedList);
        return ContactManagementConstants.RENDER_SUCCESS;
    
    }
	/**
	 * @return Returns the csSubChkBxVal.
	 */
	public boolean isCsSubChkBxVal() {
		return csSubChkBxVal;
	}
	/**
	 * @param csSubChkBxVal The csSubChkBxVal to set.
	 */
	public void setCsSubChkBxVal(boolean csSubChkBxVal) {
		this.csSubChkBxVal = csSubChkBxVal;
	}
	public List copyDescAssnCSEnt(List commonList, String tabName)
    {
        List modifiedList = new ArrayList();

        int commonListSize = 0;
        if (commonList != null)
        {
        	commonListSize = commonList.size();
        }
        if(tabName.equals("entityType"))
        {
        	if (commonList != null && commonListSize > 0)
            {
                for (int i = 0; i < commonListSize; i++)
                {
                    CallScriptEntityVO callScriptEntityVO = (CallScriptEntityVO) commonList.get(i);

                    if (callScriptEntityVO.getIncludecallScript())
                    {
                        callScriptEntityVO
                                .setAssignedCallScript(callScriptDataBean.getCallScriptVO().getDescription());
                        modifiedList.add(callScriptEntityVO);

                    }
                    else if (callScriptEntityVO.getIncludecallScript()==false)
                    {
                        modifiedList.add(callScriptEntityVO);

                    }
                }
            }
        }
        else if(tabName.equals("category"))
        {
        	if (commonList != null && commonListSize > 0)
            {
                for (int i = 0; i < commonListSize; i++)
                {
                    CallScriptCategoryVO callScriptCategoryVO = (CallScriptCategoryVO) commonList.get(i);

                    if (callScriptCategoryVO.getIncludecallScript())
                    {
                    	callScriptCategoryVO
                                .setAssignedCallScript(callScriptDataBean.getCallScriptVO().getDescription());
                        modifiedList.add(callScriptCategoryVO);

                    }
                    else if (callScriptCategoryVO.getIncludecallScript()==false)
                    {
                        modifiedList.add(callScriptCategoryVO);

                    }
                }
            }
        }
        else if(tabName.equals("subject"))
        {
        	if (commonList != null && commonListSize > 0)
            {
                for (int i = 0; i < commonListSize; i++)
                {
                	CallScriptSubjectVO callScriptSubjectVO = (CallScriptSubjectVO) commonList.get(i);

                    if (callScriptSubjectVO.getIncludecallScript())
                    {
                    	callScriptSubjectVO
                                .setAssignedCallScript(callScriptDataBean.getCallScriptVO().getDescription());
                        modifiedList.add(callScriptSubjectVO);

                    }
                    else if (callScriptSubjectVO.getIncludecallScript()==false)
                    {
                        modifiedList.add(callScriptSubjectVO);

                    }
                }
            }
        }
        return modifiedList;
    }
	public void getUserPermissions() {
		String userPermission = "";
		String userid = getUserID();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try 
		{
			
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.MAINTAIN_CALL_SCRIPTS_PAGE, userid);
		} 
		catch (SecurityFLSServiceException e)
		{
			e.printStackTrace();
			if(logger.isDebugEnabled())
			{
				logger.debug("error on CallscriptControllerBean.getUserPermission :" + e);
			}
		}
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode",userPermission);
		userPermission = userPermission != null ? userPermission.trim() : "";
		if(logger.isDebugEnabled())
		{
			logger.debug("userPermission in callscript -->"+userPermission);
		}
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
	public String doAuditKeyListOperation() {
				
        try{
		List callscriptList= callScriptDataBean.getMaintainCallScriptList();
				
		if(callscriptList!=null && !(callscriptList.isEmpty())){
			
			Iterator callsctIt = callscriptList.iterator();
			if(callsctIt!=null){
				List editableCallScript = new ArrayList();
				editableCallScript.add(createAuditableFeild("Active","statusCode"));
				editableCallScript.add(createAuditableFeild("Call Script ID","callScriptSK"));
				editableCallScript.add(createAuditableFeild("Description","description"));
				//editableCallScript.add(createAuditableFeild("Supervisor Approval Required","supervisorReviewReqIndicator"));
				while(callsctIt.hasNext()){
					CallScriptVO  callScriptVO = (CallScriptVO)callsctIt.next();
					if(callScriptVO.getAuditKeyList()!=null && !(callScriptVO.getAuditKeyList().isEmpty())){
					
					AuditDataFilter.filterAuditKeys(editableCallScript,callScriptVO);
					
					}else{
						
					}
				}
				int categoryindex = callScriptDataBean.getEditCallScritRowIndex();
				if(categoryindex>=0){
					callScriptDataBean.setCallScriptVO((CallScriptVO)callscriptList.get(categoryindex));
					if(logger.isDebugEnabled())
					{
						logger.debug(categoryindex+"=================Filter===AuditKey Size===========Contact========="+callScriptDataBean.getCallScriptVO().getAuditKeyList().size());
					}
				}
				    // Code added for Defect ID :ESPRD00785090 ,in order to display Audit table in Edit Call Script Section....start

				UIComponent component = findComponentInRoot("callScriptAuditId");
		          
							if(component!=null)
							{
								logger.debug("Inside doAuditKeyListOperation ()-component if not null");
								AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
								auditHistoryTable.setValue(callScriptDataBean.getCallScriptVO().getAuditKeyList());
								auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(false);
		            
							}
              
          // Code added for Defect ID :ESPRD00785090 , in order to display Audit table in Edit Call Script Section....end
		          
			}
		}
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		
		callScriptDataBean.setAuditLogFlag(true);
	
		return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	
	private AuditableField createAuditableFeild(String feildName,String domainAttributeName){

		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);
		
			
		return auditableField;

}
	/**
	 * @return Returns the loadUserPermissions.
	 */
	public String getLoadUserPermissions() {
     	getUserPermissions();
		return loadUserPermissions;
	}
	/**
	 * @param loadUserPermissions The loadUserPermissions to set.
	 */
	public void setLoadUserPermissions(String loadUserPermissions) {
		this.loadUserPermissions = loadUserPermissions;
	}
}
