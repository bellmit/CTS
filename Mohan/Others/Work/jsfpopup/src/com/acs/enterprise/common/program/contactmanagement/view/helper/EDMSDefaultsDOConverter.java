/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseDefault;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceDefault;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EDMSDefaults;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.EDMSDefaultsControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.EDMSDefaultsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.EdmsWorkUnitVO;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro This is a helper class which has methods to convert value
 *         object to domain object and vice versa
 */

public class EDMSDefaultsDOConverter
        extends DataTransferObjectConverter
{
    /** Enterprise Logger for Logging */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(EDMSDefaultsDOConverter.class);

    /**
     * constructor for EDMSDefaultsDOConverter.
     */
    public EDMSDefaultsDOConverter()
    {
        super();
        
    }
    
    public EDMSDefaults convertEDMSDefaultsVOToDO(EDMSDefaultsVO edmsDefaultsVO)
    {
       // EDMSDefaultsControllerBean defaultsControllerBean = new EDMSDefaultsControllerBean(); // Find bug issue
        
     //   EDMSDefaults returningEdmsDefaults = new EDMSDefaults(); // Find bug issue

        EDMSDefaults edmsDocumentDefaults = new EDMSDefaults();
        boolean retFlag = false;

       
        edmsDocumentDefaults.setEdmsTypeCode(edmsDefaultsVO.getAnEDMSType());

        edmsDocumentDefaults.setEdmsDefaultTypeCode(edmsDefaultsVO
                .getEdmsDefaultTypeFromUI());
        edmsDocumentDefaults.setVersionNo(edmsDefaultsVO.getVersionNo());
        edmsDocumentDefaults.setAddedAuditTimeStamp(edmsDefaultsVO.getAddedAuditTimeStamp());
       // edmsDocumentDefaults.setAddedAuditUserID(edmsDefaultsVO.getAddedAuditUserID());
        edmsDocumentDefaults.setAddedAuditUserID(getLoggedInUserID());
        edmsDocumentDefaults.setAuditTimeStamp(edmsDefaultsVO.getAuditTimeStamp());
        //edmsDocumentDefaults.setAuditUserID(edmsDefaultsVO.getAuditUserID());
        edmsDocumentDefaults.setAuditUserID(getLoggedInUserID());
        
        WorkUnit wrkUnit = new WorkUnit();

        if (edmsDefaultsVO.getEdmsDefaultTypeFromUI().equals(
                ContactManagementConstants.EDMS_CASE))
        {
            
            convertCaseDefaultVotoDo(edmsDocumentDefaults, edmsDefaultsVO);
            wrkUnit.setWorkUnitSK(Long.valueOf(edmsDefaultsVO
                    .getCaseUserWorkUnitSkStr()));
        }
        
        if (edmsDefaultsVO.getEdmsDefaultTypeFromUI().equals(
                ContactManagementConstants.EDMS_CRSPD))
        {
            
            convertCRDefaultVotoDo(edmsDocumentDefaults, edmsDefaultsVO);
            wrkUnit.setWorkUnitSK(Long.valueOf(edmsDefaultsVO
                    .getUserWorkUnitSkStr()));
        }
        
        Department department = new Department();
        
        if (edmsDefaultsVO.getDeptWorkUnitSK() != null
                && !edmsDefaultsVO.getDeptWorkUnitSK().trim().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {                  	
           department.setWorkUnitSK(Long.valueOf(edmsDefaultsVO
                        .getDeptWorkUnitSK()));
           edmsDocumentDefaults.setDeptartmentWorkUnit(department);
                    
        }

       
        edmsDocumentDefaults.setDefaultRouteToWorkUnit(wrkUnit);
        
        return edmsDocumentDefaults;
    }
    
    /**
     * Method to convert the case Default from vo to Do.
     * @param edmsDocumentDefaults.
     * @param edmsDefaultsVO.
     */
    private void convertCaseDefaultVotoDo(EDMSDefaults edmsDocumentDefaults,
            EDMSDefaultsVO edmsDefaultsVO)
    {
        CaseDefault caseDefault = new CaseDefault();
        CaseType caseType = new CaseType();
        
        caseDefault.setVersionNo(caseDefault.getVersionNo());
        
        if (edmsDefaultsVO.getCaseTypeSk() != null
                && !edmsDefaultsVO.getCaseTypeSk().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {
            caseType
                    .setCaseTypeSK(Long.valueOf(edmsDefaultsVO.getCaseTypeSk()));
            
        }
        if (edmsDefaultsVO.getCaseTypeVO() != null
                && edmsDefaultsVO.getCaseTypeVO().getCaseTypeSK() != null)
        {
            caseDefault.setEdmsTypeCode(edmsDefaultsVO.getAnEDMSType());
            caseDefault.setVersionNo(edmsDefaultsVO.getCaseTypeVO()
                    .getVersionNo());
        }
        caseType.setShortDescription(edmsDefaultsVO.getCaseType());
        caseType.setStatusTypeCode(edmsDefaultsVO.getStatusCode());
        
        caseDefault.setCaseType(caseType);
        
        caseDefault.setEdmsTypeCodeRef(edmsDocumentDefaults);
        caseDefault.setStatusCode(edmsDefaultsVO.getStatusCode());
        caseDefault.setAddedAuditUserID(getLoggedInUserID());
        caseDefault.setAddedAuditTimeStamp(getTimeStamp());
        caseDefault.setAuditUserID(getLoggedInUserID());
        caseDefault.setAuditTimeStamp(getTimeStamp());
       

        edmsDocumentDefaults.setCaseDefault(caseDefault);
        
        /** To set the Cr values null */
        
        edmsDocumentDefaults.setCorrespondenceDefault(null);
        /*edmsDefaultsVO.setCategoryDesc(ContactManagementConstants.EMPTY_STRING);
        edmsDefaultsVO.setCategorySK(ContactManagementConstants.EMPTY_STRING);*/
        if(logger.isDebugEnabled())
        {
        	logger.debug(".... End of convertCaseDefaultVotoDo .. version No :" + edmsDefaultsVO.getVersionNo());
        }
    }
    

    /**
     * This method is used to convert the No default record to CR.
     * 
     * @param edmsDocumentDefaults :
     *            The edmsDocumentDefaults to set.
     * @param edmsDefaultsVO :
     *            The edmsDocumentDefaults to set.
     */
    public void convertCRDefaultVotoDo(EDMSDefaults edmsDocumentDefaults,
            EDMSDefaultsVO edmsDefaultsVO)
    {
        CorrespondenceDefault correspondenceDefault = new CorrespondenceDefault();
        CorrespondenceCategory correspondenceCategory = new CorrespondenceCategory();
        correspondenceDefault.setVersionNo(correspondenceDefault.getVersionNo());
        if (edmsDefaultsVO.getCategorySK() != null
                && !edmsDefaultsVO.getCategorySK().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {
            correspondenceCategory.setCategorySK(Long.valueOf(edmsDefaultsVO
                    .getCategorySK()));
           
        }
        if (edmsDefaultsVO.getCategoryVO() != null
                && edmsDefaultsVO.getCategoryVO().getCategorySK() != null)
        {
            correspondenceDefault.setEdmsTypeCode(edmsDefaultsVO
                    .getAnEDMSType());
            correspondenceDefault.setVersionNo(edmsDefaultsVO.getCategoryVO()
                    .getVersionNo());
        }
        correspondenceDefault.setCategory(correspondenceCategory);
        correspondenceDefault.setSubjectCode(edmsDefaultsVO.getSubjectCode());
        correspondenceDefault.setStatusCode(edmsDefaultsVO.getStatusCode());
       
        correspondenceDefault.setEdmsTypeCodeRef(edmsDocumentDefaults);
        
        correspondenceDefault.setAddedAuditTimeStamp(getTimeStamp());
        correspondenceDefault.setAddedAuditUserID(getLoggedInUserID());
        correspondenceDefault.setAuditTimeStamp(getTimeStamp());
        correspondenceDefault.setAuditUserID(getLoggedInUserID());
        edmsDocumentDefaults.setCorrespondenceDefault(correspondenceDefault);
        
        
        /** For setting the case values false */
        
        edmsDocumentDefaults.setCaseDefault(null);
       /* edmsDefaultsVO.setCaseTypeSk(ContactManagementConstants.EMPTY_STRING);
        edmsDefaultsVO.setCaseType(ContactManagementConstants.EMPTY_STRING);*/
        if(logger.isDebugEnabled())
        {
        	logger.debug(".... end of convertCRDefaultVotoDo ..Version no is " + edmsDefaultsVO.getVersionNo());
        }
        
    }

    /**
     * This method is used to convert the DO to VO.
     * 
     * @param edmsDocumentDefaults :
     *            The edmsDocumentDefaults to set.
     * @return EDMSDefaultsVO Returns the EDMS defaults VO.
     */
    public EDMSDefaultsVO convertEDMSDefaultsDOToVO(
            EDMSDefaults edmsDocumentDefaults)
    {
    	
        EDMSDefaultsVO edmsDefaultsVO = new EDMSDefaultsVO();
        //"FindBugs" issue fixed
        //CorrespondenceDefault correspondenceDefault = new CorrespondenceDefault();
        CorrespondenceDefault correspondenceDefault = null;
        CaseDefault caseDefault = new CaseDefault();
        //"FindBugs" issue fixed
        //WorkUnit wrkunt = new WorkUnit();
        WorkUnit wrkunt = null;

        EdmsWorkUnitVO edmsWorkUnitVO = new EdmsWorkUnitVO();
        edmsDefaultsVO.setAnEDMSType(edmsDocumentDefaults.getEdmsTypeCode());
        //edmsDefaultsVO.setDocumentType(edmsDocumentDefaults.getEdmsDefaultTypeCode());
        
        
         edmsDefaultsVO.setDocumentType(edmsDocumentDefaults
                .getEdmsDefaultTypeCode());
        edmsDefaultsVO.setVersionNo(edmsDocumentDefaults.getVersionNo());
        wrkunt = edmsDocumentDefaults.getDefaultRouteToWorkUnit();
        
//      by ICS coded Hard code for time 
        
       
        edmsDefaultsVO.setAuditTimeStamp(edmsDocumentDefaults.getAuditTimeStamp());
        if(logger.isDebugEnabled())
        {
        	logger.debug("Audit timestamp::"+edmsDefaultsVO.getAuditTimeStamp());
        }
        edmsDefaultsVO.setAuditUserID(edmsDocumentDefaults.getAuditUserID());
        if(logger.isDebugEnabled())
        {
        	logger.debug("Audit User ID::"+edmsDefaultsVO.getAuditUserID());
        }
        edmsDefaultsVO.setAddedAuditTimeStamp(edmsDocumentDefaults.getAddedAuditTimeStamp());
        if(logger.isDebugEnabled())
        {
        	logger.debug("Audit Added Timestamp::"+edmsDefaultsVO.getAddedAuditTimeStamp());
        }
        edmsDefaultsVO.setAddedAuditUserID(edmsDocumentDefaults.getAddedAuditUserID());
        if(logger.isDebugEnabled())
        {
        	logger.debug("Audit Added User Id::"+edmsDefaultsVO.getAddedAuditUserID());
        }
      
        
        if (edmsDocumentDefaults.getDeptartmentWorkUnit() != null)
        {
            edmsDefaultsVO.setDeptName(edmsDocumentDefaults
                    .getDeptartmentWorkUnit().getName());
            edmsDefaultsVO.setDeptWorkUnitSK(edmsDocumentDefaults
                    .getDeptartmentWorkUnit().getWorkUnitSK().toString());

        }

       /* if (wrkunt.getEnterpriseUser() != null)
        {
            edmsWorkUnitVO.setUserName(wrkunt.getEnterpriseUser()
                    .getFirstName()
                    + " " + wrkunt.getEnterpriseUser().getLastName());
            edmsDefaultsVO.setRouteToString(edmsWorkUnitVO.getUserName());
            edmsDefaultsVO.setEdmsWorkUnitVO(edmsWorkUnitVO);
        }*/ if (wrkunt.getEnterpriseUser() != null)
        {
            edmsWorkUnitVO.setUserName(wrkunt.getEnterpriseUser()
                    .getLastName()
                    + "," + wrkunt.getEnterpriseUser().getFirstName()+"-"+wrkunt.getEnterpriseUser().getUserID() );
            edmsDefaultsVO.setRouteToString(edmsWorkUnitVO.getUserName());
            edmsDefaultsVO.setEdmsWorkUnitVO(edmsWorkUnitVO);
        }

        else if(wrkunt.getDepartment() != null)
        {
            edmsWorkUnitVO.setUserName(wrkunt.getDepartment()
                    .getName());
            edmsDefaultsVO.setRouteToString(edmsWorkUnitVO.getUserName());
            edmsDefaultsVO.setEdmsWorkUnitVO(edmsWorkUnitVO);
        }
        caseDefault = edmsDocumentDefaults.getCaseDefault();
        if (caseDefault != null && caseDefault.getCaseType() != null)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("----> Do to Vo , caseDefault : case type : case type SK "
                           	 	+ caseDefault.getCaseType().getShortDescription()
                           	 	+ ":" + caseDefault.getCaseType().getCaseTypeSK());
        	}
            CaseTypeVO caseTypeVO = new CaseTypeVO();
            
            edmsDefaultsVO.setCaseType(caseDefault.getCaseType()
                    .getShortDescription());
            edmsDefaultsVO.setStatusCode(caseDefault.getStatusCode());
            edmsDefaultsVO.setCaseTypeSk(caseDefault.getCaseType()
                    .getCaseTypeSK().toString().trim());
            caseTypeVO.setCaseTypeSK(caseDefault.getCaseType()
                    .getCaseTypeSK());
            caseTypeVO.setVersionNo(caseDefault.getVersionNo());
            edmsDefaultsVO.setCaseTypeVO(caseTypeVO);
            if (wrkunt.getEnterpriseUser() != null)
            {
                edmsDefaultsVO.setCaseUserWorkUnitSkStr(wrkunt.getEnterpriseUser()
                        .getUserWorkUnitSK().toString());
                
            }else if(wrkunt.getDepartment()!= null)
            {
            	
            	 edmsDefaultsVO.setCaseUserWorkUnitSkStr(wrkunt.getDepartment()
						.getWorkUnitSK().toString());
                     
            
            }
            
        }

        correspondenceDefault = edmsDocumentDefaults.getCorrespondenceDefault();

        if (correspondenceDefault != null)
        {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategorySK(correspondenceDefault.getCategory()
                    .getCategorySK());
            
            categoryVO.setVersionNo(correspondenceDefault.getVersionNo());
            
            edmsDefaultsVO.setCategoryVO(categoryVO);
            edmsDefaultsVO.setStatusCode(correspondenceDefault.getStatusCode());

            edmsDefaultsVO.setSubjectCode(correspondenceDefault
                    .getSubjectCode());
            
            if (wrkunt.getEnterpriseUser() != null)
            {
                edmsDefaultsVO.setUserWorkUnitSkStr(wrkunt.getEnterpriseUser()
                        .getUserWorkUnitSK().toString());
            }else if(wrkunt.getDepartment()!= null)
                {
                	
                	 edmsDefaultsVO.setUserWorkUnitSkStr(wrkunt.getDepartment().getWorkUnitSK().
                            toString());	
            
            }
            

        }
        
       createVOAuditKeysList(edmsDocumentDefaults,edmsDefaultsVO);
       createVOAuditKeysList(edmsDocumentDefaults.getCorrespondenceDefault(),edmsDefaultsVO);
       createVOAuditKeysList(edmsDocumentDefaults.getCaseDefault(),edmsDefaultsVO);
       createVOAuditKeysList(edmsDocumentDefaults.getDefaultRouteToWorkUnit().getEnterpriseUser(),edmsDefaultsVO);
       createVOAuditKeysList(edmsDocumentDefaults.getDefaultRouteToWorkUnit().getDepartment(),edmsDefaultsVO);
        
        if(logger.isDebugEnabled())
        {
        logger.debug("getAuditTimeStamp === "+edmsDocumentDefaults.getAuditTimeStamp());
        logger.debug("getAuditUserID  555555555 "+edmsDocumentDefaults.getAuditUserID());
        logger.debug("getAddedAuditTimeStamp *** "+edmsDocumentDefaults.getAddedAuditTimeStamp());
        logger.debug("getAddedAuditUserID --------"+edmsDocumentDefaults.getAddedAuditUserID()); 
        }
        
        return edmsDefaultsVO;
    }
    

    
    private void createVOAuditKeysList(EnterpriseBaseDomain enterpriseBaseDomain,AuditaleEnterpriseBaseVO  auditaleEnterpriseBaseVO){
    	
    	  List fullAuditList = new ArrayList();
    	  
    	  if(auditaleEnterpriseBaseVO.getAuditKeyList()!= null){
    	  	fullAuditList= auditaleEnterpriseBaseVO.getAuditKeyList();
    	  }
          
    	  
    	  
           LineItemAuditsDelegate auditDelegate;
    	try {
    		auditDelegate = new LineItemAuditsDelegate();
    		if (enterpriseBaseDomain != null)
    		{
    		List resultList = auditDelegate.getAuditKeyList(enterpriseBaseDomain);
    	
           
    	       if(resultList!=null){
    	       	fullAuditList.addAll(resultList);
    	       	if(logger.isDebugEnabled())
    	       	{
    	       		logger.debug("======LineItemAuditsDelegate====resultList====Size======="+resultList.size());
    	       	}
    	       	auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);
    	       }
    		}
    	       
    	} catch (LineItemAuditsBusinessException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
          
     
    }


    /**
     * This method is used to convert the DO List coming from database to Vo
     * List.
     * 
     * @param edmsDocumentDefaultsList :
     *            The edmsDocumentDefaultsList to set.
     * @return edmsDefaultsVOList : Returns the edmsDefaultsVO list.
     */
    public List convertEDMSDefaultsDOListToVO(List edmsDocumentDefaultsList)
    {
    	
        EDMSDefaults edmsDocumentDefaults;
        //"FindBugs" issue fixed
        //EDMSDefaultsVO edmsDefaultsVO = new EDMSDefaultsVO();
        EDMSDefaultsVO edmsDefaultsVO = null ;
        List edmsDefaultsVOList = new ArrayList();
        Iterator it = edmsDocumentDefaultsList.iterator();

        while (it.hasNext())
        {
            edmsDocumentDefaults = (EDMSDefaults) it.next();
            
            edmsDefaultsVO = convertEDMSDefaultsDOToVO(edmsDocumentDefaults);
            
            
            edmsDefaultsVO.getAddedAuditUserID();
            edmsDefaultsVO.getAddedAuditTimeStamp();
            edmsDefaultsVO.getAuditUserID();
            
            
            
            edmsDefaultsVOList.add(edmsDefaultsVO);
        }
        
        return edmsDefaultsVOList;
    }

    /**
     * This method will get userid from Security.
     * 
     * @return String.
     */
    /*public String getUserID()
    {
        String userId = ContactManagementConstants.TEST_USERID;
        //FacesContext fc = FacesContext.getCurrentInstance();
        EDMSDefaultsControllerBean edmsDefaultsControllerBean = new EDMSDefaultsControllerBean();

        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = edmsDefaultsControllerBean.getUserData(
                renderRequest, renderResponse);

        if (eup != null && !isNullOrEmptyField(eup.getUserId()))
        {
            userId = eup.getUserId();
        }

        return userId;
    }*/
    
    public String getLoggedInUserID() {
		String userId = null;
		Principal principal = null ;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		if(portletRequest != null){
			
		 principal= portletRequest.getUserPrincipal();
		}
		if (principal == null) {
			userId = "guestUser";
		}
		else{
			userId = principal.getName();
		}
		
		return userId;
	}

    /**
     * This method is used to get the Current Timestamp.
     * 
     * @return Timestamp : Current Timestamp.
     */
    private Timestamp getTimeStamp()
    {
        

        Calendar cal = Calendar.getInstance();

        return new Timestamp(cal.getTimeInMillis());
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
}
