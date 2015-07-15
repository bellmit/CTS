/*
 * Created on Oct 12, 2007
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
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

import com.acs.enterprise.common.audit.application.dataaccess.exception.GlobalAuditDataException;
import com.acs.enterprise.common.audit.application.exception.GlobalAuditsBusinessException;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;

import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;


import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseTypeFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.EDMSDefaultsCRDeleteException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.EDMSDefaultsFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.EDMSDefaultsUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;

import com.acs.enterprise.common.program.contactmanagement.common.domain.CategorySubjectXref;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;

import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EDMSDefaults;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.SubjectAuxillary;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.EDMSDefaultsDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.vo.EDMSDefaultsVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;



/**
 * @author Wipro
 */
public class EDMSDefaultsControllerBean
        extends EnterpriseBaseControllerBean
{

    /** Enterprise Logger for Logging */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(EDMSDefaultsControllerBean.class);

    /**
     * A variable of type EDMSDefaultsDataBean to hold the instance of data
     * bean.
     */
    private transient EDMSDefaultsDataBean edmsDefaultsDataBean = getEDMSDefaultsDataBean();

    private String initialData = " ";
    
    private boolean editEdmsDefaults = false;
    
    /**
     * This field is used to store whether user has permission.
     */
    private boolean controlRequired = true;
    

    /**
     * Constructor of CallScriptControllerBean.
     */
    public EDMSDefaultsControllerBean()
    {
        super();
        edmsDefaultsDataBean = getEDMSDefaultsDataBean();
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
     * This method gets the permission level for the logged in user
     *
     */	
    public void getUserPermission() {
		String userPermission = "";
		String userid = getUserID();
		if(logger.isDebugEnabled())
		{
			logger.debug(" EDMSDefaultsControllerBean():userid:::::::" + userid);
		}
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
                    ContactManagementConstants.MAINTAIN_EDMS_DEFAULTS_PAGE,
                    userid);
			if(logger.isDebugEnabled())
			{
				logger.debug(" EDMSDefaultsControllerBean:userPermission::::::" + userPermission);
			}
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode",userPermission);
		userPermission = userPermission != null ? userPermission.trim() : "";
				
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
     * This method is to create an instance of Data Bean.
     * 
     * @return Returns EDMSDefaultsDataBean
     */
    private EDMSDefaultsDataBean getEDMSDefaultsDataBean()
    {
    	
        FacesContext fc = FacesContext.getCurrentInstance();
        EDMSDefaultsDataBean edmsDefaultsDataBean = (EDMSDefaultsDataBean) fc
                .getApplication().createValueBinding(
                        "#{" +ContactManagementConstants.EDMSDEFAULTSDATABEAN+ "}").getValue(
                        fc);
        return edmsDefaultsDataBean;
    }

    /**
     * This is a ValueChangeListener method. It gets the subjets list based on
     * categorySk selected.
     * 
     * @param event :
     *            The event to set.
     * @return String.
     */

    public String getSubjectsForCategory(ValueChangeEvent event)
    {
    	ArrayList subjectList = new ArrayList();
    	List arr = new ArrayList();
        edmsDefaultsDataBean.setShowSucessMessage(false);
        edmsDefaultsDataBean.setShowErrorMessage(false);

        if (event.getNewValue() != null & !event.getNewValue().toString().trim().equalsIgnoreCase(ContactManagementConstants.EMPTY_STRING))
        {
            
            String value = event.getNewValue().toString().trim();

           
            getsubjects(value);
            getUsersList(value);
            
            
            EDMSDefaultsVO defaultsVO = edmsDefaultsDataBean
                    .getEdmsDefaultsVO();
            defaultsVO
                    .setUserWorkUnitSkStr(ContactManagementConstants.EMPTY_STRING);
            
            edmsDefaultsDataBean.setEdmsDefaultsVO(defaultsVO);
           
            
            /*FacesContext faces = FacesContext.getCurrentInstance();
            faces.renderResponse();*/
        }else
        {
        	edmsDefaultsDataBean.setCategorySubjectValues(subjectList);
        	edmsDefaultsDataBean.setRouteToList(arr);
        
        }

      

        return ContactManagementConstants.RENDER_SUCCESS;
    }
    
    /**
     * This is a ValueChangeListener method. It gets the Case type details list based on
     * caseTypeSk selected.
     * 
     * @param event :
     *            The event to set.
     * @return String.
     */

    public String getCaseTypeDetails(ValueChangeEvent event)
    {
       
        edmsDefaultsDataBean.setShowSucessMessage(false);
        edmsDefaultsDataBean.setShowErrorMessage(false);

        if (event.getNewValue() != null)
        {
            logger.debug("**** Iam in getSubjectsForCategory **** ");
            String value = event.getNewValue().toString();
            
            getCaseTypeUsersList(value);

            EDMSDefaultsVO defaultsVO = edmsDefaultsDataBean
                    .getEdmsDefaultsVO();
            defaultsVO
                    .setCaseUserWorkUnitSkStr(ContactManagementConstants.EMPTY_STRING);

            edmsDefaultsDataBean.setEdmsDefaultsVO(defaultsVO);
            

            /*FacesContext faces = FacesContext.getCurrentInstance();
            faces.renderResponse();*/
        }

      

        return ContactManagementConstants.CATEGORY_FOR_SUBJ;
    }

    /**
     * This method is used to get the subjects list based on the categorySK.
     * 
     * @param categorySK :
     *            The categorySK to set.
     */
    public void getsubjects(String categorySK)
    {
       
        ArrayList subjectList = new ArrayList();
        
        CorrespondenceCategory correspondenceCategory = null;
        if(logger.isInfoEnabled())
        {
        	logger.info("getSubjectCode===="+edmsDefaultsDataBean.getEdmsDefaultsVO().getSubjectCode());
        }
        
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        try{
	        CorrespondenceCategory crCatogory = new CorrespondenceCategory();
	        crCatogory.setCategorySK(Long.valueOf(categorySK));
	        Set catSubjXrefs = contactMaintenanceDelegate.getCatSubjXrefs(crCatogory);
	        if(catSubjXrefs != null && catSubjXrefs.size() > 0)
	        {
	        	if(logger.isInfoEnabled())
	        	{
	        		logger.info("catSubjXrefs.size()=="+catSubjXrefs.size());
	        	}
	        	Iterator iterateSet = catSubjXrefs.iterator();
	            while (iterateSet.hasNext())
	            {
	                CategorySubjectXref catSubjAux = (CategorySubjectXref) iterateSet
	                        .next();
	                SubjectAuxillary subjectAux = catSubjAux
	                        .getSubjectAuxillary();
	                String shortDesc = edmsDefaultsDataBean.getDescriptionFromVV(subjectAux
	                        .getSubjectCode(), edmsDefaultsDataBean
	                        .getSubjectValidValues());
	                
	                if(shortDesc != null && !shortDesc.trim().equals(""))
					{
	                    subjectList.add(new SelectItem(subjectAux
	                            .getSubjectCode(), shortDesc));
					}
	            }
	        }
	        edmsDefaultsDataBean.sortSelectItemSequence(subjectList);
	        edmsDefaultsDataBean.setCategorySubjectValues(subjectList);
        }catch (CategoryFetchBusinessException e) {
        	
        	e.printStackTrace();
		}
    }

    /**
     * This method is used to create the users list.
     * 
     * @param value :
     *            The value to set.
     */
    public void getUsersList(String value)
    {
        CMDelegate delegate = new CMDelegate();
        ArrayList arr = new ArrayList();
        List users = null;
//      Infinite UC-068 Defect3311
        List departments = null;
        List deptList = null;
       

        if (value != null
                && !value.trim().equals(ContactManagementConstants.EMPTY_STRING))
        {
            try
            {
                users = delegate.getAllUsers(Long.valueOf(value));
                ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
               // departments = delegate.getAllDepartments(Long.valueOf(value));
              //Code Added below for new API on 07/03/2012.
                DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
    			deptUserBasicInfo.setDataReqested("categorySKForDepartment");	
    			deptUserBasicInfo.setCategorySK(Long.valueOf(value));
    			departments = contactMaintenanceDelegate.getDepartmentInfo(deptUserBasicInfo);
            }
            catch (CorrespondenceRecordFetchBusinessException e)
            {
            	if(logger.isErrorEnabled())
            	{
                logger.error(e.getMessage(), e);
            	}
            }
          //Code Added below for new API on 07/03/2012.
            catch (Exception e)
            {
            	if(logger.isErrorEnabled())
            	{
                logger.error(e.getMessage(), e);
            	}
            }
        
	        if (users != null && !users.isEmpty())
	        {
	            for (Iterator iter = users.iterator(); iter.hasNext();)
	            {
	            	Object[] userData = (Object[]) iter.next();
	            	String lastName = null;
	                if(userData[3] != null)
	                {
	                	lastName = userData[3].toString();
	                }
	            	if (userData[2] != null && userData[2].toString() != null)
	                {
	                    arr.add(new SelectItem(userData[0].toString(),
	                    		lastName
	                			+ ContactManagementConstants.COMMA_SEPARATOR
	                			+ ContactManagementConstants.SPACE_STRING
								+ userData[2].toString()
	                                    + ContactManagementConstants.HYPHEN_SEPARATOR
	                                    + userData[1].toString()));
	                }
	            }
	        }
	        if (departments != null && !departments.isEmpty())
	        {
	        	if(logger.isInfoEnabled())
	        	{
	        	logger.info("departments size====="+departments.size());
	        	}
	            for (Iterator iter = departments.iterator(); iter.hasNext();)
	            {
	               /* Department dept = (Department) iter.next();
	                if (dept.getName() != null)
	                {
	                	arr.add(new SelectItem(dept.getWorkUnitSK().toString(),
	                			dept.getName()));
	                }*/
	            	
	            	//Code Added below for new API on 07/03/2012.
	            	
	            	DeptUserBasicInfo dept = (DeptUserBasicInfo) iter.next();
	                if (dept.getDeptName() != null)
	                {
	                	arr.add(new SelectItem(dept.getWorkUnitSK().toString(),
	                			dept.getDeptName()));
	                }
	            }
	        }
        }
       
        getDepartmentsList(edmsDefaultsDataBean.getEdmsDefaultsVO()
                .getUserWorkUnitSkStr());
        edmsDefaultsDataBean.sortSelectItemSequence(arr);
        edmsDefaultsDataBean.setRouteToList(arr);
    }
    
    /**
     * This method is used to create the users list.
     * 
     * @param value :
     *            The value to set.
     */
    public void getCaseTypeUsersList(String value)
    {
       
        ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
        ArrayList arr = new ArrayList();
        List users = null;
//      Infinite UC-068 Defect3311
        CMDelegate cmDelegate = new CMDelegate();
        List departments = null;
        
    

        if (value != null
                && !value.equals(ContactManagementConstants.EMPTY_STRING))
        {
            try
            {
                users = delegate.getAllCaseTypeUsers(Long.valueOf(value));
                departments = cmDelegate.getAllDepartments(Long.valueOf(value));
              //Code Added below for new API on 07/03/2012.
                DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
    			deptUserBasicInfo.setDataReqested("categorySKForDepartment");	
    			deptUserBasicInfo.setCategorySK(Long.valueOf(value));
    			departments = delegate.getDepartmentInfo(deptUserBasicInfo);
            }
            catch (CaseTypeFetchBusinessException e)
            {
            	if(logger.isErrorEnabled())
            	{
                logger.error(e.getMessage(), e);
            	}
            }
            catch (CorrespondenceRecordFetchBusinessException e)
            {
            	if(logger.isErrorEnabled())
            	{
                logger.error(e.getMessage(), e);
            	}
            }
          //Code Added below for new API on 07/03/2012.
            catch (Exception e)
            {
            	if(logger.isErrorEnabled())
            	{
                logger.error(e.getMessage(), e);
            	}
            }
        }
        if (users != null && !users.isEmpty())
        {
            for (Iterator iter = users.iterator(); iter.hasNext();)
            {
                EnterpriseUser usr = (EnterpriseUser) iter.next();
               
                if (usr.getFirstName() != null)
                {
                    arr.add(new SelectItem(usr.getUserWorkUnitSK().toString(),
                            usr.getFirstName()
                                    + ContactManagementConstants.SPACE_STRING
                                    + usr.getLastName()));
                    
                }
            }
        }

        if (users == null || (users.isEmpty()))
        {
        	if(logger.isDebugEnabled())
        	{
            logger.debug("the users Lis is null");
        	}
        }
        if (departments != null && !departments.isEmpty())
        {

            for (Iterator iter = departments.iterator(); iter.hasNext();)
            {
                /*Department dept = (Department) iter.next();
                if (dept.getName() != null)
                {
                	arr.add(new SelectItem(dept.getName()));
                }*/
            	
            	//Code Added below for new API on 07/03/2012.
            	
            	DeptUserBasicInfo dept = (DeptUserBasicInfo) iter.next();
                if (dept.getDeptName() != null)
                {
                	arr.add(new SelectItem(dept.getDeptName()));
                }
            }
        }
        
        if (departments == null || departments.isEmpty())
        {
        	if(logger.isDebugEnabled())
        	{
        	 logger.debug("the department List is null");
        	}
        }
        getDepartmentsList(edmsDefaultsDataBean.getEdmsDefaultsVO()
                .getCaseUserWorkUnitSkStr());
        edmsDefaultsDataBean.setCaseRouteToList(arr);
      
    }

    /**
     * This method is used to render case or CR depending on the value selected.
     * 
     * @param event :
     *            The event to set.
     */
    public void getCaseOrCRDetails(ValueChangeEvent event)
    {
    	
        if(event != null)
        {
	    	String value = event.getNewValue().toString();
	        EDMSDefaultsDataBean edmsDefaultsDataBean = getEDMSDefaultsDataBean();
	        edmsDefaultsDataBean.setShowSucessMessage(false);
	        edmsDefaultsDataBean.setShowErrorMessage(false);
	        
	       
	        if (value.trim().equals(ContactManagementConstants.EDMS_CASE))
	        {
	           
	            
	            edmsDefaultsDataBean.setRenderCorrespondence(false);
	            edmsDefaultsDataBean.setRenderCase(true);
	            
	            
	            getDepartmentsList(edmsDefaultsDataBean.getEdmsDefaultsVO().getCaseUserWorkUnitSkStr());
	        }
	        if (value.equals(ContactManagementConstants.EDMS_CRSPD))
	        {
	            
	            edmsDefaultsDataBean.setRenderCase(false);
	            edmsDefaultsDataBean.setRenderCorrespondence(true);
	            getDepartmentsList(edmsDefaultsDataBean.getEdmsDefaultsVO().getUserWorkUnitSkStr());
	            
	        }
	
	        if (value.equals(ContactManagementConstants.NO_DEFAULT))
	        {
	        	
	            edmsDefaultsDataBean.setRenderCase(false);
	            edmsDefaultsDataBean.setRenderCorrespondence(false);
	        }
        }
      
        
        /*FacesContext faces = FacesContext.getCurrentInstance();
        faces.renderResponse();*/
    }

    /**
     * Opens the EDMSDefaultsDetails record in edit mode.
     * 
     * @return String.
     */
    public String editEDMSDefaults()
    {
        
    	if(!editEdmsDefaults){
       
        EDMSDefaultsDataBean edmsDefaultsDataBean = getEDMSDefaultsDataBean();
        
        edmsDefaultsDataBean.setShowSucessMessage(false);
        edmsDefaultsDataBean.setShowErrorMessage(false);

        FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
		int rowIndex = Integer.parseInt(indexCode);
		EDMSDefaultsVO edmsDefaultsVO = (EDMSDefaultsVO) edmsDefaultsDataBean
        .getEdmsDefaultsList().get(rowIndex);
		edmsDefaultsDataBean.setRowIndex(rowIndex);
		
		//added...Modified for EDMS datatable row selection...
		edmsDefaultsDataBean.setEdmsDefaultsRowIndex(rowIndex);
		//end
		
		UIComponent component = ContactManagementHelper.findComponentInRoot("edmsDefaultAuditId");
		if(component!=null)
		{
			AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(false);
			
		}
        

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(true);
         session.setAttribute("OriginalEDMSDefaultsVO", edmsDefaultsVO);
        if(logger.isDebugEnabled())
        {
        logger.debug("in Edit : categorySk :" + edmsDefaultsVO.getCategorySK()
                + ": CategoryDesc : " + edmsDefaultsVO.getCategoryDesc()
                + ": CaseTypeSk : " + edmsDefaultsVO.getCaseTypeSk()
                + ": CaseType : " + edmsDefaultsVO.getCaseType());
        }
        
        if (edmsDefaultsVO.getCategoryDesc() != null
                && !edmsDefaultsVO.getCategoryDesc().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {
            edmsDefaultsVO
                    .setEdmsDefaultTypeFromUI(ContactManagementConstants.EDMS_CRSPD);
            getsubjects(edmsDefaultsVO.getCategorySK());
            
            getUsersList(edmsDefaultsVO.getCategorySK());
            getDepartmentsList(edmsDefaultsVO.getUserWorkUnitSkStr());
            
            edmsDefaultsDataBean.setRenderCase(false);
            edmsDefaultsDataBean.setRenderCorrespondence(true);

        }
        

        if (edmsDefaultsVO.getCaseType() != null
                && !edmsDefaultsVO.getCaseType().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {
            edmsDefaultsVO
                    .setEdmsDefaultTypeFromUI(ContactManagementConstants.EDMS_CASE);

            edmsDefaultsDataBean.setRenderProtected(false);
            
            getCaseTypeUsersList(edmsDefaultsVO.getCaseTypeSk());
            
            getDepartmentsList(edmsDefaultsVO.getCaseUserWorkUnitSkStr());
            
            edmsDefaultsDataBean.setRenderCorrespondence(false);
            edmsDefaultsDataBean.setRenderCase(true);
        }
        
        if ((edmsDefaultsVO.getCategoryDesc() == null || edmsDefaultsVO
                .getCategoryDesc().equals(
                        ContactManagementConstants.EMPTY_STRING))
                && (edmsDefaultsVO.getCaseType() == null || edmsDefaultsVO
                        .getCaseType().equals(
                                ContactManagementConstants.EMPTY_STRING)))
        {
            
            edmsDefaultsVO
                    .setEdmsDefaultTypeFromUI(ContactManagementConstants.NO_DEFAULT);
            edmsDefaultsVO
                    .setCategorySK(ContactManagementConstants.EMPTY_STRING);
            edmsDefaultsVO
                    .setCaseTypeSk(ContactManagementConstants.EMPTY_STRING);
            edmsDefaultsVO
                    .setUserWorkUnitSkStr(ContactManagementConstants.EMPTY_STRING);
            edmsDefaultsVO
            .setCaseUserWorkUnitSkStr(ContactManagementConstants.EMPTY_STRING);
            edmsDefaultsDataBean.getDepartmentsList().clear();
            edmsDefaultsDataBean.setRenderProtected(false);
            edmsDefaultsDataBean.getRouteToList().clear();
            edmsDefaultsDataBean.getCaseRouteToList().clear();
            edmsDefaultsDataBean.getCategorySubjectValues().clear();
            edmsDefaultsDataBean.setRenderCase(false);
            edmsDefaultsDataBean.setRenderCorrespondence(false);
        }
        
        
        

        if (edmsDefaultsDataBean.getDepartmentsList() != null
                && edmsDefaultsDataBean.getDepartmentsList().size() == 1)
        {
            
            SelectItem item = (SelectItem) edmsDefaultsDataBean
                    .getDepartmentsList().get(0);
            if(logger.isDebugEnabled())
            {
            logger.debug("****** in if of Depts list item value is *******"
                    + item.getValue());

            logger
                    .debug("****** in if of Depts list item Label value is *******"
                            + item.getLabel());
            }

            edmsDefaultsDataBean.getEdmsDefaultsVO().setDeptWorkUnitSK(
                    (String) item.getValue());

            logger.debug("get Dept SK "
                    + edmsDefaultsDataBean.getEdmsDefaultsVO()
                            .getDeptWorkUnitSK());
        }
        
        edmsDefaultsVO.setAuditTimeStamp(edmsDefaultsVO.getAuditTimeStamp());
        edmsDefaultsVO.setAuditUserID(edmsDefaultsVO.getAuditUserID());
        edmsDefaultsVO.setAddedAuditTimeStamp(edmsDefaultsVO.getAddedAuditTimeStamp());
        edmsDefaultsVO.setAddedAuditUserID(edmsDefaultsVO.getAddedAuditUserID());
        edmsDefaultsDataBean.setEdmsDefaultsVO(getVoClone(edmsDefaultsVO));
       
        edmsDefaultsDataBean.setEdmsDefaultsVO(edmsDefaultsVO);
        edmsDefaultsDataBean.setRenderEditEDMSDefaults(true);
        edmsDefaultsDataBean.setAuditLogFlag(false);
       
     //   showAuditHistory();
        editEdmsDefaults = true;
    	}
        return ContactManagementConstants.RENDER_SUCCESS;
    }
    
	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}
	
	public String showColumnChange() {
		
		GlobalAuditsDelegate auditDelegate = null;
		try {
			edmsDefaultsDataBean.setAuditColumnHistoryRender(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long.valueOf((String) getParamValue("auditLogSK")));
			edmsDefaultsDataBean.setSelectedAuditLog(auditLog);
		} catch (Exception e) {
			if(logger.isErrorEnabled())
			{
			logger.debug("Error in show column change  " + e);
			}
		}
		return ProgramConstants.RETURN_SUCCESS;
	}
    
    /**
     * FOR AUDIT
     * @param edmsDefaultsVO
     * @return
     */
    public String showAuditHistory()
    {
        
        GlobalAuditsDelegate audit;
        final List list = new ArrayList();
        EDMSDefaults edmsdefaults = new EDMSDefaults();
        EDMSDefaultsDataBean edmsDefaultsDataBean = getEDMSDefaultsDataBean();
        
        try
        {
           
            audit = new GlobalAuditsDelegate();
            EDMSDefaultsDOConverter edmsdefaultsDOConverter = new EDMSDefaultsDOConverter();
            
            /* Gets Child Domain Object */


            edmsdefaults = edmsdefaultsDOConverter.convertEDMSDefaultsVOToDO(edmsDefaultsDataBean.getEdmsDefaultsVO());
            
            

            list.add(edmsdefaults);
            HashMap hm = audit.getAuditLogInfo(list);

            
            
            final EnterpriseSearchResultsVO enterpriseResultVO = audit.getAuditLogList(edmsdefaults,
					0, ContactManagementConstants.NO_OF_RECORD_TO_FETCH);
            

            edmsDefaultsDataBean.setEdmsDefaultsAuditHistoryList(enterpriseResultVO.getSearchResults());
            if(logger.isInfoEnabled())
            {
            logger.info("==> inside EDMSDefaultsControllerBean.showAuditHistory() enterpriseReusltVO.getSearchResults().size()="+enterpriseResultVO.getSearchResults().size());
            }
            edmsDefaultsDataBean.setEdmsDefaultsAuditRender(true);
            /* Added new for Expand */
            edmsDefaultsDataBean.setAuditOpen(true);
            
        }
        catch (GlobalAuditDataException e)
        {
        	e.printStackTrace();
            
        }
        catch (GlobalAuditsBusinessException e)
        {
        	e.printStackTrace();
            
        }
        catch(Exception e){
        	e.printStackTrace();
        }

      
        return ProgramConstants.RETURN_SUCCESS;
    }
    
    /**
     * This method is used to create a Copy of the EDMSDefaultsVO.
     * 
     * @param edmsDefaultsVO :
     *            The edmsDefaultsVO to set.
     * @return EDMSDefaultsVO.
     */
    public EDMSDefaultsVO getVoClone(EDMSDefaultsVO edmsDefaultsVO)
    {
       
        EDMSDefaultsVO defltTempVo = new EDMSDefaultsVO();
        defltTempVo.setCaseType(edmsDefaultsVO.getCaseType());
        defltTempVo.setCaseTypeSk(edmsDefaultsVO.getCaseTypeSk());
        defltTempVo.setStatusCode(edmsDefaultsVO.getStatusCode());
        defltTempVo.setSubjectCode(edmsDefaultsVO.getSubjectCode());
        defltTempVo.setCategoryDesc(edmsDefaultsVO.getCategoryDesc());
        defltTempVo.setAnEDMSType(edmsDefaultsVO.getAnEDMSType());
        defltTempVo.setCategorySK(edmsDefaultsVO.getCategorySK());
        defltTempVo.setCategoryVO(edmsDefaultsVO.getCategoryVO());
        defltTempVo.setDeptWorkUnitSK(edmsDefaultsVO.getDeptWorkUnitSK());
      
        defltTempVo.setDeptName(edmsDefaultsVO.getDeptName());

        defltTempVo.setDocumentType(edmsDefaultsVO.getDocumentType());
        defltTempVo.setEdmsDefaultTypeFromUI(edmsDefaultsVO
                .getEdmsDefaultTypeFromUI());
        defltTempVo.setUserWorkUnitSkStr(edmsDefaultsVO.getUserWorkUnitSkStr());
        defltTempVo.setCaseUserWorkUnitSkStr(edmsDefaultsVO.getCaseUserWorkUnitSkStr());
        defltTempVo.setEdmsWorkUnitVO(edmsDefaultsVO.getEdmsWorkUnitVO());
        defltTempVo.setStatus(edmsDefaultsVO.getStatus());
        defltTempVo.setSubject(edmsDefaultsVO.getSubject());
        edmsDefaultsVO.setAuditTimeStamp(edmsDefaultsVO.getAuditTimeStamp());
        edmsDefaultsVO.setAuditUserID(edmsDefaultsVO.getAuditUserID());
        edmsDefaultsVO.setAddedAuditTimeStamp(edmsDefaultsVO.getAddedAuditTimeStamp());
        edmsDefaultsVO.setAddedAuditUserID(edmsDefaultsVO.getAddedAuditUserID());
      
        return defltTempVo;
    }

    /**
     * This method is used to create the depts lists based on UserID.
     * 
     * @param userID :
     *            The userID to set.
     */
    public void getDepartmentsList(String userID)
    {
        if(logger.isDebugEnabled())
        {
        logger.debug("userID:::::::::::::: "+userID);
        }
      	List deptArr = new ArrayList();
          List deptsList = null;
          ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
          DeptUserBasicInfo deptUserBasicInfo=new DeptUserBasicInfo();
          if (userID != null
                  && !userID.trim().equals(ContactManagementConstants.EMPTY_STRING))
          {
        	  deptUserBasicInfo.setUserEnterpriseSK(Long.valueOf(userID));
         	    deptUserBasicInfo.setDataReqested(ProgramConstants.DUBI_Dept_Name_SK);
              try
              {
            	  deptUserBasicInfo= contactMaintenanceDelegate.getDeptUserBasicInfo(deptUserBasicInfo);
                  if(deptUserBasicInfo != null)
                   {
                	  deptsList= deptUserBasicInfo.getDeptNameSKList();
                   }
                  
              }
              catch (LOBHierarchyFetchBusinessException lobExp)
              {
                  lobExp.printStackTrace();
              }
              
          }
          deptArr.add(new SelectItem(" ","Please Select one"));
          
          if (deptsList != null && !deptsList.isEmpty())
          {
              for (Iterator iter = deptsList.iterator(); iter.hasNext();)
              {
                /*  DepartmentUser deptUser = (DepartmentUser) iter.next();
                  if (deptUser.getDepartment() != null
                          && deptUser.getDepartment().getName() != null)
                  {
                      deptArr.add(new SelectItem(deptUser.getDepartmentSK()
                              .toString(), deptUser.getDepartment().getName()));
                  }*/
            	  
            	   Object[] deptData = (Object[]) iter.next();
              	   String name= deptData[0].toString();
					Long deptsk= (Long) deptData[1];
					
					deptArr.add(new SelectItem(deptsk.toString(), name));
              }
          }
          edmsDefaultsDataBean.setDepartmentsList(deptArr);
          if (deptsList != null && deptsList.size() == 1)
          {
          	/*for(Iterator i= deptsList.iterator(); i.hasNext();)
          	{
          		 DepartmentUser deptUser = (DepartmentUser) i.next();
          		 logger.debug("Department name "+  deptUser.getDepartment().getName());
          	}*/
              SelectItem item = (SelectItem) deptArr.get(0);
              edmsDefaultsDataBean.getEdmsDefaultsVO().setDeptWorkUnitSK(
                      (String) item.getValue());
              deptArr.remove(0);
              edmsDefaultsDataBean.setRenderProtected(true);
          }
          else
          {
            edmsDefaultsDataBean.setRenderProtected(false);
              
          }
       }

    /**
     * This operation is used to get all the Filters sorted on certain column
     * and order.
     * 
     * @param event :
     *            ActionEvent object.
     */
    public String sort(ActionEvent event)
    {
       
        

        String sortColumn = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.COLUMN_NAME);
        String sortOrder = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.SORT_ORDER);
        edmsDefaultsDataBean.setEdmsStartIndex(0);

        edmsDefaultsDataBean.setImageRender(event.getComponent().getId());

        edmsDefaultsComparator(sortColumn, sortOrder, edmsDefaultsDataBean
                .getEdmsDefaultsList());

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
    public String edmsDefaultsComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
        
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                String first = null;
                String second = null;
                EDMSDefaultsVO data1 = (EDMSDefaultsVO) obj1;
                EDMSDefaultsVO data2 = (EDMSDefaultsVO) obj2;
                boolean ascending = false;
                if (ContactManagementConstants.SORT_ASC.equals(sortOrder))
                {
                    ascending = true;
                }
                else
                {
                    ascending = false;
                }
              //for Fixing defect:ESPRD00584127 
                edmsDefaultsDataBean.setAscendingOrder(ascending);
                edmsDefaultsDataBean.setColumnName(sortColumn);
                
                if (sortColumn == null)
                {
                    return 0;
                }

                if ("documentType".equals(sortColumn))
                {
                    if (null == data1.getDocumentType())
                    {
                        data1
                                .setDocumentType(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getDocumentType())
                    {
                        data2
                                .setDocumentType(ContactManagementConstants.EMPTY_STRING);
                    }
                    first = data1.getDocumentType().toLowerCase();
                    second = data2.getDocumentType().toLowerCase();
                    

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);

                    /*
                     * Integer firstCrn =
                     * Integer.valueOf(data1.getDocumentType()); Integer
                     * secondCrn = Integer.valueOf(data2.getDocumentType());
                     * return ascending ? firstCrn.compareTo(secondCrn) :
                     * secondCrn.compareTo(firstCrn);
                     */
                }
                

                if ("crCategory".equals(sortColumn))
                {
                    
                    if (null == data1.getCategoryDesc())
                    {
                        data1
                                .setCategoryDesc(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getCategoryDesc())
                    {
                        data2
                                .setCategoryDesc(ContactManagementConstants.EMPTY_STRING);
                    }

                    first = data1.getCategoryDesc().toLowerCase();
                    second = data2.getCategoryDesc().toLowerCase();
                    

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);
                }
                if ("crSubject".equals(sortColumn))
                {

                    if (null == data1.getSubject())
                    {
                        data1
                                .setSubject(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getSubject())
                    {
                        data2
                                .setSubject(ContactManagementConstants.EMPTY_STRING);
                    }

                    first = data1.getSubject().toLowerCase();
                    second = data2.getSubject().toLowerCase();
                    

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);
                }

                if ("crStatus".equals(sortColumn))
                {
                    if (null == data1.getStatus())
                    {
                        data1
                                .setStatus(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getStatus())
                    {
                        data2
                                .setStatus(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getStatus().compareTo(
                            data2.getStatus()) : data2.getStatus().compareTo(
                            data1.getStatus());
                }

                if ("caseType".equals(sortColumn))
                {
                    if (null == data1.getCaseType())
                    {
                        data1
                                .setCaseType(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getCaseType())
                    {
                        data2
                                .setCaseType(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getCaseType().compareTo(
                            data2.getCaseType()) : data2.getCaseType()
                            .compareTo(data1.getCaseType());
                }
                if ("routeTo".equals(sortColumn))
                {
                	if (null == data1.getRouteToString())
                    {
                        data1.setRouteToString(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getRouteToString())
                    {
                        data2.setRouteToString(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getRouteToString().compareToIgnoreCase(
                            data2.getRouteToString()) : data2.getRouteToString().compareToIgnoreCase(
                                    data1.getRouteToString());
                }
                if ("department".equals(sortColumn))
                {
                    if (null == data1.getDeptName())
                    {
                        data1
                                .setDeptName(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getDeptName())
                    {
                        data2
                                .setDeptName(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getDeptName().compareTo(
                            data2.getDeptName()) : data2.getDeptName()
                            .compareTo(data1.getDeptName());
                }
                return 0;
            }
        };
        Collections.sort(dataList, comparator);
        return "";
    }

    /**
     * Value change method for getting the Depts based on userId
     * 
     * @param event :
     *            The event to set.
     */
    public void getDeptForResUser(ValueChangeEvent event)
    {
      
        String userID = event.getNewValue().toString();
        edmsDefaultsDataBean.setShowSucessMessage(false);
        edmsDefaultsDataBean.setShowErrorMessage(false);
        //	createDepartmentsList(userID);
        if (userID != null
                && !userID.equals(ContactManagementConstants.EMPTY_STRING))
        {
            getDepartmentsList(userID);
        }
      
        /*FacesContext faces = FacesContext.getCurrentInstance();
        faces.renderResponse();*/
        
    }

    /**
     * This method is used to validate the Correspondence List.
     * 
     * @return boolean: Returns a flag.
     */
    public boolean validateCorrespondenceList()
    {
       
        boolean flag = true;
        long entryTime = System.currentTimeMillis();
        
        String categorySK = edmsDefaultsDataBean.getEdmsDefaultsVO()
                .getCategorySK();
        String userWorkUnitSK = edmsDefaultsDataBean.getEdmsDefaultsVO()
                .getUserWorkUnitSkStr();
        String deptWorkUnitSK=edmsDefaultsDataBean.getEdmsDefaultsVO().getDeptWorkUnitSK();

        if (categorySK == null
                || ContactManagementConstants.EMPTY_STRING.equals(categorySK
                        .trim()))
        {
            flag = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.CATEGORY},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
                    ContactManagementConstants.CATEGORY_FOR_SUBJECTS);
        }
        if (edmsDefaultsDataBean.getEdmsDefaultsVO().getStatusCode() == null
                || edmsDefaultsDataBean.getEdmsDefaultsVO().getStatusCode().toString().trim()
                        .equalsIgnoreCase(ContactManagementConstants.EMPTY_STRING))
        {
            flag = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.STATUS},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
                    ContactManagementConstants.CR_STATUS);
        }

        if (edmsDefaultsDataBean.getEdmsDefaultsVO().getSubjectCode() == null
                || edmsDefaultsDataBean.getEdmsDefaultsVO().getSubjectCode().toString().trim()
                        .equals(ContactManagementConstants.EMPTY_STRING))
        {
            flag = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.SUBJECT},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
                    ContactManagementConstants.EDMS_SUBJECT);
        }

        if (userWorkUnitSK == null
                || ContactManagementConstants.EMPTY_STRING
                        .equals(userWorkUnitSK.trim()))
        {
            flag = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.ROUTE_TO},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
                    ContactManagementConstants.CR_ROUTE_TO);
        }
        
      if(edmsDefaultsDataBean.getDepartmentsList().size() >1)
	  {  
        if (deptWorkUnitSK == null
                || ContactManagementConstants.EMPTY_STRING
                        .equals(deptWorkUnitSK.trim()))
        {
            flag = false;
            edmsDefaultsDataBean.setShowErrorMessage(true);
            edmsDefaultsDataBean.setShowSucessMessage(false);
            
        }
      }

        long exitTime = System.currentTimeMillis();
        if(logger.isInfoEnabled())
        {
        logger.info("EdmsDefaultsControllerBean" + "#"
                + "validateCategoriesList" + "#" + "edms defaults" + "#"
                + (exitTime - entryTime));
        }
      
        return flag;
    }
    
    /**
     * This method is used to validate the Case Type.
     * 
     * @return boolean: Returns a flag.
     */
    public boolean validateCaseType()
    {
      
        boolean flag = true;
        long entryTime = System.currentTimeMillis();
        
        String caseTypeSk = edmsDefaultsDataBean.getEdmsDefaultsVO()
                .getCaseTypeSk();
        String userWorkUnitSK = edmsDefaultsDataBean.getEdmsDefaultsVO()
                .getCaseUserWorkUnitSkStr();

        if (caseTypeSk == null
                || ContactManagementConstants.EMPTY_STRING.equals(caseTypeSk
                        .trim()))
        {
            flag = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {"Case Type"},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "caseType");
        }
        if (edmsDefaultsDataBean.getEdmsDefaultsVO().getStatusCode() == null
                || edmsDefaultsDataBean.getEdmsDefaultsVO().getStatusCode()
                        .equals(ContactManagementConstants.EMPTY_STRING))
        {
            flag = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.STATUS},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "caseStatus");
        }

        if (userWorkUnitSK == null
                || ContactManagementConstants.EMPTY_STRING
                        .equals(userWorkUnitSK.trim()))
        {
            flag = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.ROUTE_TO},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "caseRouteTo");
        }

        long exitTime = System.currentTimeMillis();
        if(logger.isInfoEnabled())
        {
        logger.info("EdmsDefaultsControllerBean" + "#"
                + "validateCategoriesList" + "#" + "edms defaults" + "#"
                + (exitTime - entryTime));
        }
      
        return flag;
    }

    /**
     * This method used for setting error display messages.
     * 
     * @param errorName :
     *            String errorName.
     * @param arguments :
     *            Array of Object. Parameters to be passed to the message.
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
        
        // UIComponent result = null;
        UIComponent dataReturn = null;
        // Is the "base" component itself the match we are looking for?
        if (id.equals(base.getId()))
        {
            dataReturn = base;
        }
        else
        {
            // Search through our facets and children
            UIComponent component = null;
            Iterator cmpIterator = base.getFacetsAndChildren();

            while (cmpIterator.hasNext() && (dataReturn == null))
            {
                component = (UIComponent) cmpIterator.next();
                if (id.equals(component.getId()))
                {
                    dataReturn = component;
                    break;
                }
                dataReturn = findComponent(component, id);
                if (dataReturn != null)
                {
                    break;
                }
            }
        }

        return dataReturn;
    }
    
    /**
     * Updates the EDMSDefaultsDetails.
     * 
     * @return String : For navigation.
     */
    public String updateEDMSDefaults()
    {
    	
    	
    	if(edmsDefaultsDataBean.getDepartmentsList().size() == 1) {
    		SelectItem item = (SelectItem) edmsDefaultsDataBean.getDepartmentsList().get(0);
            edmsDefaultsDataBean.getEdmsDefaultsVO().setDeptWorkUnitSK(
                    (String) item.getValue());
    	}
      
        String retrnType = ContactManagementConstants.EMPTY_STRING;
        //	Boolean flag = new Boolean(false);
        Boolean flag = Boolean.FALSE;
        EDMSDefaultsDOConverter edmsDefaultsHelper = new EDMSDefaultsDOConverter();
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        EDMSDefaults edmsDocumentDefaults = new EDMSDefaults();
        if(logger.isDebugEnabled())
        {
        logger.debug("... in Update CaseTypeSk :"
                + edmsDefaultsDataBean.getEdmsDefaultsVO().getCaseTypeSk()
                + ":Category Sk :"
                + edmsDefaultsDataBean.getEdmsDefaultsVO().getCategorySK()
                + " : Edms Type :"
                + edmsDefaultsDataBean.getEdmsDefaultsVO()
                        .getEdmsDefaultTypeFromUI());
        }
        
        if ((edmsDefaultsDataBean.getEdmsDefaultsVO()
                .getEdmsDefaultTypeFromUI().equals(
                        ContactManagementConstants.NO_DEFAULT) && (edmsDefaultsDataBean
                .getEdmsDefaultsVO().getCategorySK() != null || edmsDefaultsDataBean
                .getEdmsDefaultsVO().getCaseTypeSk() != null))
                /*|| (edmsDefaultsDataBean.getEdmsDefaultsVO()
                        .getEdmsDefaultTypeFromUI().equals(
                                ContactManagementConstants.EDMS_CRSPD) && edmsDefaultsDataBean
                        .getEdmsDefaultsVO().getCaseTypeSk() != null)
                || ((edmsDefaultsDataBean.getEdmsDefaultsVO()
                        .getEdmsDefaultTypeFromUI().equals(
                                ContactManagementConstants.EDMS_CASE) && edmsDefaultsDataBean
                        .getEdmsDefaultsVO().getCategorySK() != null))*/)
        {
        	if(logger.isDebugEnabled())
        	{
            logger.debug(".... before getEDMSDefaultsDetails call : getAnEDMSType : " + edmsDefaultsDataBean
                    .getEdmsDefaultsVO().getAnEDMSType());
        	}
            
            EDMSDefaults defaultsDo = getEDMSDefaultsDetails(edmsDefaultsDataBean.getEdmsDefaultsVO().getAnEDMSType());
            
            if (defaultsDo != null)
            {
            	if(logger.isDebugEnabled())
            	{
                logger.debug(".... after  getEDMSDefaultsDetails call : defaultsDo :" + defaultsDo.getEdmsTypeCode());
            	}
                try
                {
                   
                    flag = contactMaintenanceDelegate
                            .deleteEdmsDefaults(defaultsDo);
                   
                }
                catch (EDMSDefaultsCRDeleteException e)
                {
                    e.printStackTrace();
                    
                }
            }
            else  if (defaultsDo == null)
            {
            	flag=Boolean.TRUE;
            }
        }

        if ((edmsDefaultsDataBean.getEdmsDefaultsVO()
                .getEdmsDefaultTypeFromUI().equals(
                        ContactManagementConstants.EDMS_CRSPD) && validateCorrespondenceList())
                || (edmsDefaultsDataBean.getEdmsDefaultsVO()
                        .getEdmsDefaultTypeFromUI().equals(
                                ContactManagementConstants.EDMS_CASE) && validateCaseType()))
        {

            /**
             * Calls method to set the EdmsDefaults Object with Values entered
             * in UI
             */
        	       	
           if(logger.isDebugEnabled())
           {
        	   logger.debug("... In update before Do conversion .." + edmsDefaultsDataBean
                            .getEdmsDefaultsVO().getAnEDMSType());
           }
            edmsDocumentDefaults = edmsDefaultsHelper
                    .convertEDMSDefaultsVOToDO(edmsDefaultsDataBean.getEdmsDefaultsVO());

            if (edmsDocumentDefaults != null)
            {
                try
                {
                    
                    flag = contactMaintenanceDelegate
                            .updateEDMSDefaults(edmsDocumentDefaults);
                    if(logger.isDebugEnabled())
                    {
                    	logger.debug("after updateEDMSDefaults delegate flag :" + flag.booleanValue());
                    }
                }
                catch (EDMSDefaultsUpdateBusinessException e)
                {
                	setErrorMessage(EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
	                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
                	e.printStackTrace();

                    
                }
            }
        }

        if (flag.booleanValue())
        {
            edmsDefaultsDataBean.setShowSucessMessage(true);
            edmsDefaultsDataBean.setSaveFlag(true);
            edmsDefaultsDataBean.setRenderDataTable(true);
            edmsDefaultsDataBean.setShowErrorMessage(false);

            edmsDefaultsDataBean.createEdmsDfltDataTableList();
            
            
            //for Fixing defect:ESPRD00584127 start
            String sortOrder=null;
            if(edmsDefaultsDataBean.isAscendingOrder()){
            	sortOrder=ContactManagementConstants.SORT_ASC;
            }else{
            	sortOrder=ContactManagementConstants.SORT_DESC;
            }	
            edmsDefaultsComparator(edmsDefaultsDataBean.getColumnName(), sortOrder, edmsDefaultsDataBean
                    .getEdmsDefaultsList());
          //for Fixing defect:ESPRD00584127 end
            showEditedEdmsDefault();

            retrnType = ContactManagementConstants.RENDER_SUCCESS;
        }
      
        
        return retrnType;
    }

    /**
     * This method is used to get the EDMSDefaults record based on the
     * edmsTypeCode passed.
     * 
     * @param edmsTypeCode :
     *            The edmsTypeCode to set.
     * @return EDMSDefaults : Returns the record of type EDMSDefaults based on
     *         the edmsTypeCode passed.
     */
    public EDMSDefaults getEDMSDefaultsDetails(String edmsTypeCode)
    {
    	
        
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        EDMSDefaults edmsDocumentDefaults = new EDMSDefaults();
        EDMSDefaultsVO edmsDefaultsVO=new EDMSDefaultsVO();
        try
        {
            edmsDocumentDefaults = contactMaintenanceDelegate
                    .getEDMSDefaultsDetails(edmsTypeCode);
            if(edmsDocumentDefaults != null)
            {
	            edmsDefaultsVO.setAddedAuditTimeStamp(edmsDocumentDefaults.getAddedAuditTimeStamp());
	            
	            edmsDefaultsVO.setAddedAuditUserID(edmsDocumentDefaults.getAddedAuditUserID());
	            edmsDefaultsVO.setAuditTimeStamp(edmsDocumentDefaults.getAuditTimeStamp());
	            edmsDefaultsVO.setAuditUserID(edmsDocumentDefaults.getAuditUserID());
            }
            
            
        }
        catch (EDMSDefaultsFetchBusinessException e)
        {
            //e.printStackTrace();
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }
        finally
        {
          
        }
        
        return edmsDocumentDefaults;
    }

    /**
     * This method is used to delete the edmsDocumentDefaults record from
     * database.
     * 
     * @param edmsDocumentDefaults :
     *            The edmsDocumentDefaults to set.
     * @return boolean .
     */
    public boolean deleteEdmsDefaults(EDMSDefaults edmsDocumentDefaults)
    {
       
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        	
        Boolean flag = Boolean.FALSE;
        try
        {
            flag = contactMaintenanceDelegate
                    .deleteEdmsDefaults(edmsDocumentDefaults);
        }
        catch (EDMSDefaultsCRDeleteException e)
        {
            //e.printStackTrace();
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }
        finally
        {
          
        }
        return flag.booleanValue();
    }

    /**
     * This method is used to reset the values. 
     * @return String : For navigation.
     */
    public String reset()
    {
        
       
        
        EDMSDefaultsDataBean edmsDefaultsDataBean = getEDMSDefaultsDataBean(); 
        
        edmsDefaultsDataBean.setShowSucessMessage(false);
        edmsDefaultsDataBean.setShowErrorMessage(false);

        EDMSDefaultsVO edmsDefaultsVO = (EDMSDefaultsVO) edmsDefaultsDataBean
                .getEdmsDefaultsList().get(edmsDefaultsDataBean.getRowIndex());
        if(logger.isDebugEnabled())
        {
        logger.debug("---- EdmsDefaultTypeFromUI : " + edmsDefaultsVO.getEdmsDefaultTypeFromUI());
        }
        if (edmsDefaultsVO.getEdmsDefaultTypeFromUI() != null)
        {
            if (edmsDefaultsVO.getEdmsDefaultTypeFromUI().equals(
                    ContactManagementConstants.EDMS_CRSPD))
            {

                getsubjects(edmsDefaultsVO.getCategorySK());

                getUsersList(edmsDefaultsVO.getCategorySK());
                getDepartmentsList(edmsDefaultsVO.getUserWorkUnitSkStr());
                
                
                HttpSession session = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(
						true);
		      edmsDefaultsDataBean.setEdmsDefaultsVO((EDMSDefaultsVO) session
				.getAttribute("OriginalEDMSDefaultsVO"));
		     
		     
		     if(logger.isDebugEnabled())
		     {
		      logger.debug("Category SK Old value : "+ ((EDMSDefaultsVO) session
						.getAttribute("OriginalEDMSDefaultsVO"))
						.getCategorySK());
		      logger.debug("After setting back Category SK to original value : "
						+ edmsDefaultsDataBean.getEdmsDefaultsVO()
								.getCategorySK());
		     }

		      String originalCategorySK = ((EDMSDefaultsVO) session
				     .getAttribute("OriginalEDMSDefaultsVO"))
				     .getCategorySK();
		      getsubjects(originalCategorySK);
		      getUsersList(originalCategorySK);
                
               

                edmsDefaultsDataBean.setRenderCase(false);
                edmsDefaultsDataBean.setRenderCorrespondence(true);
                
            }
            
            if (edmsDefaultsVO.getEdmsDefaultTypeFromUI().equals(
                    ContactManagementConstants.EDMS_CASE))
            {
                getCaseTypeUsersList(edmsDefaultsVO.getCaseTypeSk());
                getDepartmentsList(edmsDefaultsVO.getCaseUserWorkUnitSkStr());
                
               
               

                edmsDefaultsDataBean.setRenderCorrespondence(false);
                edmsDefaultsDataBean.setRenderCase(true);
                
            }

            if (edmsDefaultsVO.getEdmsDefaultTypeFromUI().equals(
                    ContactManagementConstants.NO_DEFAULT))
            {
                edmsDefaultsDataBean.setRenderCase(false);
                edmsDefaultsDataBean.setRenderCorrespondence(false);
            }
          //  edmsDefaultsDataBean.setEdmsDefaultsVO(getVoClone(edmsDefaultsVO));
            edmsDefaultsDataBean.setRenderEditEDMSDefaults(true);
        }
      
        edmsDefaultsDataBean.createEdmsDfltDataTableList();
        showEditedEdmsDefault();
        return ContactManagementConstants.RENDER_SUCCESS;
    }
    
	private void populateList(int currentPage) {
	//	ArrayList searchList = new ArrayList();  //FInd bug issue

		GlobalAuditsDelegate globalAuditsDelegate = null;
		try {
			globalAuditsDelegate = new GlobalAuditsDelegate();
			EDMSDefaultsDOConverter edmsConverter = new EDMSDefaultsDOConverter();
			EDMSDefaults edms = edmsConverter.convertEDMSDefaultsVOToDO(edmsDefaultsDataBean.getEdmsDefaultsVO());
			
			EnterpriseSearchResultsVO enterpriseResultVO = globalAuditsDelegate.getAuditLogList(edms,
					(currentPage-1) * ContactManagementConstants.INT_10, ContactManagementConstants.NO_OF_RECORD_TO_FETCH);

			edmsDefaultsDataBean.setEdmsDefaultsAuditHistoryList(enterpriseResultVO.getSearchResults());
			edmsDefaultsDataBean.setEdmsDefaultsAuditRender(true);
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
		EnterpriseBaseDataBean entDataBean = nextPage(edmsDefaultsDataBean,
				edmsDefaultsDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();

        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method is used for data scrolling 
     * @return ContactManagementConstants.PREVIOUS_SUCCESS.
     */
    public String previous()
    {	
		edmsDefaultsDataBean = getEDMSDefaultsDataBean();
		
		EnterpriseBaseDataBean entDataBean = previousPage(
				edmsDefaultsDataBean, edmsDefaultsDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

    	
    	return ContactManagementConstants.PREVIOUS_SUCCESS;
    }

    /**
     * This method is used to cancel the update operation.
     * @return retrnType.
     */
    public String cancel()
    {
        
        String retrnType = ContactManagementConstants.RENDER_SUCCESS;
        edmsDefaultsDataBean.getEdmsDefaultsList().clear();
        edmsDefaultsDataBean.createEdmsDfltDataTableList();
        edmsDefaultsDataBean.setShowSucessMessage(false);
        edmsDefaultsDataBean.setRenderEditEDMSDefaults(false);
        edmsDefaultsDataBean.setSaveFlag(false);
        edmsDefaultsDataBean.setShowErrorMessage(false);
        return retrnType;
    }

    /**
     * This method is used to bind the Resource bundle. 	
     * @param facesContext : 
     * 			The facesContext to set.
     * @return ResourceBundle.
     */
    public static ResourceBundle resourceBundle(FacesContext facesContext)
    {
        
        facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        String messageBundle = facesContext.getApplication().getMessageBundle();
        Locale locale = root.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);
      
        return bundle;
    }

    /**
     * @param initialData The initialData to set.
     */
    public void setInitialData(String initialData)
    {
        this.initialData = initialData;
    }
    
	public String closeColumnChange() {
		getEDMSDefaultsDataBean().setAuditColumnHistoryRender(false);
		return ContactManagementConstants.RENDER_SUCCESS;
    }
	
	/**
	 * 
	 * @return
	 */
	private String showEditedEdmsDefault()
	{
        
        
        EDMSDefaultsDataBean edmsDefaultsDataBean = getEDMSDefaultsDataBean();
        
       
		EDMSDefaultsVO edmsDefaultsVO = (EDMSDefaultsVO) edmsDefaultsDataBean
        .getEdmsDefaultsList().get(edmsDefaultsDataBean.getRowIndex());
		
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(true);
         session.setAttribute("OriginalEDMSDefaultsVO", edmsDefaultsVO);
        if(logger.isDebugEnabled())
        {
        logger.debug("in Edit : categorySk :" + edmsDefaultsVO.getCategorySK()
                + ": CategoryDesc : " + edmsDefaultsVO.getCategoryDesc()
                + ": CaseTypeSk : " + edmsDefaultsVO.getCaseTypeSk()
                + ": CaseType : " + edmsDefaultsVO.getCaseType());
        }
        
        if (edmsDefaultsVO.getCategoryDesc() != null
                && !edmsDefaultsVO.getCategoryDesc().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {
            edmsDefaultsVO
                    .setEdmsDefaultTypeFromUI(ContactManagementConstants.EDMS_CRSPD);
            getsubjects(edmsDefaultsVO.getCategorySK());
            
            getUsersList(edmsDefaultsVO.getCategorySK());
            getDepartmentsList(edmsDefaultsVO.getUserWorkUnitSkStr());
            
            edmsDefaultsDataBean.setRenderCase(false);
            edmsDefaultsDataBean.setRenderCorrespondence(true);

        }
        

        if (edmsDefaultsVO.getCaseType() != null
                && !edmsDefaultsVO.getCaseType().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {
            edmsDefaultsVO
                    .setEdmsDefaultTypeFromUI(ContactManagementConstants.EDMS_CASE);

            edmsDefaultsDataBean.setRenderProtected(false);
            
            getCaseTypeUsersList(edmsDefaultsVO.getCaseTypeSk());
            
            getDepartmentsList(edmsDefaultsVO.getCaseUserWorkUnitSkStr());
            
            edmsDefaultsDataBean.setRenderCorrespondence(false);
            edmsDefaultsDataBean.setRenderCase(true);
        }
        
        if ((edmsDefaultsVO.getCategoryDesc() == null || edmsDefaultsVO
                .getCategoryDesc().equals(
                        ContactManagementConstants.EMPTY_STRING))
                && (edmsDefaultsVO.getCaseType() == null || edmsDefaultsVO
                        .getCaseType().equals(
                                ContactManagementConstants.EMPTY_STRING)))
        {
            
            edmsDefaultsVO
                    .setEdmsDefaultTypeFromUI(ContactManagementConstants.NO_DEFAULT);
            edmsDefaultsVO
                    .setCategorySK(ContactManagementConstants.EMPTY_STRING);
            edmsDefaultsVO
                    .setCaseTypeSk(ContactManagementConstants.EMPTY_STRING);
            edmsDefaultsVO
                    .setUserWorkUnitSkStr(ContactManagementConstants.EMPTY_STRING);
            edmsDefaultsVO
            .setCaseUserWorkUnitSkStr(ContactManagementConstants.EMPTY_STRING);
            edmsDefaultsDataBean.getDepartmentsList().clear();
            edmsDefaultsDataBean.setRenderProtected(false);
            edmsDefaultsDataBean.getRouteToList().clear();
            edmsDefaultsDataBean.getCaseRouteToList().clear();
            edmsDefaultsDataBean.getCategorySubjectValues().clear();
            edmsDefaultsDataBean.setRenderCase(false);
            edmsDefaultsDataBean.setRenderCorrespondence(false);
        }
        
        
        

        if (edmsDefaultsDataBean.getDepartmentsList() != null
                && edmsDefaultsDataBean.getDepartmentsList().size() == 1)
        {
            
            SelectItem item = (SelectItem) edmsDefaultsDataBean
                    .getDepartmentsList().get(0);
            if(logger.isDebugEnabled())
            {
            logger.debug("****** in if of Depts list item value is *******"
                    + item.getValue());

            logger
                    .debug("****** in if of Depts list item Label value is *******"
                            + item.getLabel());
            }

            edmsDefaultsDataBean.getEdmsDefaultsVO().setDeptWorkUnitSK(
                    (String) item.getValue());
            if(logger.isDebugEnabled())
            {
            logger.debug("get Dept SK "
                    + edmsDefaultsDataBean.getEdmsDefaultsVO()
                            .getDeptWorkUnitSK());
            }
        }
        edmsDefaultsVO.setAuditTimeStamp(edmsDefaultsVO.getAuditTimeStamp());
        edmsDefaultsVO.setAuditUserID(edmsDefaultsVO.getAuditUserID());
        edmsDefaultsVO.setAddedAuditTimeStamp(edmsDefaultsVO.getAddedAuditTimeStamp());
        edmsDefaultsVO.setAddedAuditUserID(edmsDefaultsVO.getAddedAuditUserID());
        
        edmsDefaultsDataBean.setEdmsDefaultsVO(getVoClone(edmsDefaultsVO));
       
        edmsDefaultsDataBean.setEdmsDefaultsVO(edmsDefaultsVO);
        edmsDefaultsDataBean.setRenderEditEDMSDefaults(true);
        
        showAuditHistory();
       

        return ContactManagementConstants.RENDER_SUCCESS;
    
	}
	/**
	 * @return Returns the editEdmsDefaults.
	 */
	public boolean isEditEdmsDefaults() {
		return editEdmsDefaults;
	}
	/**
	 * @param editEdmsDefaults The editEdmsDefaults to set.
	 */
	public void setEditEdmsDefaults(boolean editEdmsDefaults) {
		this.editEdmsDefaults = editEdmsDefaults;
	}
	public String doAuditKeyListOperation() {
		
        
        try{
        	     List edmsDefaultList= edmsDefaultsDataBean.getEdmsDefaultsList();
		
				
		if(edmsDefaultList!=null && !(edmsDefaultList.isEmpty())){
			
			Iterator edmsDefaultIt = edmsDefaultList.iterator();
			if(edmsDefaultIt!=null){
				List editableEdmsDefault = new ArrayList();
				editableEdmsDefault.add(createAuditableFeild("EDMS Type","edmsDefaultTypeCode"));
				editableEdmsDefault.add(createAuditableFeild("Category","categorySK"));
				editableEdmsDefault.add(createAuditableFeild("Subject","subjectCode"));
				editableEdmsDefault.add(createAuditableFeild("Status","statusCode"));
				editableEdmsDefault.add(createAuditableFeild("Route To","defaultRouteToWorkUnit"));
				editableEdmsDefault.add(createAuditableFeild("Work Unit","deptartmentWorkUnit"));
				while(edmsDefaultIt.hasNext()){

					EDMSDefaultsVO edmsDefaultsVO=  (EDMSDefaultsVO)edmsDefaultIt.next();
					if(edmsDefaultsVO.getAuditKeyList()!=null && !(edmsDefaultsVO.getAuditKeyList().isEmpty())){
					
					AuditDataFilter.filterAuditKeys(editableEdmsDefault,edmsDefaultsVO);
					
					}else{
						
					}
				}
				int edmsDefaultindex = edmsDefaultsDataBean.getRowIndex();
				if(edmsDefaultindex>=0){
					edmsDefaultsDataBean.setEdmsDefaultsVO((EDMSDefaultsVO)edmsDefaultList.get(edmsDefaultindex));
					
					
				}
				
			}
		}
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		
	edmsDefaultsDataBean.setAuditLogFlag(true);
	
		return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	
	private AuditableField createAuditableFeild(String feildName,String domainAttributeName){

		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);
		
			
		return auditableField;

}
}