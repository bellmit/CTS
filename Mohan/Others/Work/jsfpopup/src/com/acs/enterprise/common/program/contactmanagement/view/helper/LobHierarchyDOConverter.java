/*
 * Created on Jan 3, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.LobHierarchyControlBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LobHierarchyVO;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro This calss is used to convert the LobHierarchy value object to
 *         Domain object and vice versa.
 */
public class LobHierarchyDOConverter
        extends DataTransferObjectConverter
{

    /**
     * EnterpriseLogger for logging
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(LobHierarchyDOConverter.class);

    /**
     * Constructor for LobHierarchyDOConverter
     */
    public LobHierarchyDOConverter()
    {
        super();
        logger.debug("In side LobHierarchyDOConverter");
    }

    /**
     * This method is used to convert the LobHierarchy value object to Domain
     * object.
     * 
     * @param lobHieracVO
     *            lobHieracVO of type LobHierarchyVO
     * @return LineOfBusinessHierarchy
     */
    public LineOfBusinessHierarchy convertVOtoDO(LobHierarchyVO lobHieracVO)
    {

        LineOfBusinessHierarchy lineOfBusinessHierarchy = new LineOfBusinessHierarchy();
        //FindBugs issue Fixed
        //LineOfBusinessHierarchy parentLOBHierarchy = new LineOfBusinessHierarchy();
        LineOfBusinessHierarchy parentLOBHierarchy = null;

        try
        {
            if (lobHieracVO.getLobHieracParentVO() != null)
            {
                parentLOBHierarchy = new ContactMaintenanceDelegate()
                        .getLobHierarchyRecord(lobHieracVO
                                .getLobHieracParentVO().getHierachySK());
                if(parentLOBHierarchy!=null){
                	lineOfBusinessHierarchy
                    	.setParentLOBHierarchy(parentLOBHierarchy);
                	//"FindBugs" issue fixed
                	/*lineOfBusinessHierarchy.setLobHierarchyLevelNumber(new Integer(
                			parentLOBHierarchy.getLobHierarchyLevelNumber()
                            .intValue() + 1));*/
                	lineOfBusinessHierarchy.setLobHierarchyLevelNumber(Integer.valueOf(
                			parentLOBHierarchy.getLobHierarchyLevelNumber()
                            .intValue() + 1));
                }
                
            }
        }
        catch (LOBHierarchyFetchBusinessException e)
        {

            logger.error(e.getMessage(), e);
        }
        String desc = lobHieracVO.getLobDesc();
        lineOfBusinessHierarchy.setLobHierarchyDescription(desc);

        Integer levelNumber = lineOfBusinessHierarchy
                .getLobHierarchyLevelNumber();
        if (levelNumber != null
                && levelNumber.intValue() == ContactManagementConstants.FOUR
                        .intValue())
        {
            logger.debug("Converting Department object");
            Department department = new Department();
            department.setAddedAuditTimeStamp(getTimeStamp());
            department.setAddedAuditUserID(getLoggedInUserID());
            department.setAuditTimeStamp(getTimeStamp());
            department.setAuditUserID(getLoggedInUserID());
            department.setVersionNo(lobHieracVO.getVersionNo());
            department.setName(desc);
            department.setSupervisorUserWorkUnitSK(lobHieracVO
                    .getSupervisorName());
            department.setLineOfBusinessHierarchy(lineOfBusinessHierarchy);
            Set departSet = new HashSet();
            departSet.add(department);
            lineOfBusinessHierarchy.setDepartments(departSet);
        }

        lineOfBusinessHierarchy.setVoidIndicator(Boolean.FALSE);
        lineOfBusinessHierarchy.setAuditUserID(getLoggedInUserID());
        lineOfBusinessHierarchy.setAuditTimeStamp(getTimeStamp());
        lineOfBusinessHierarchy.setAddedAuditUserID(getLoggedInUserID());
        lineOfBusinessHierarchy.setAddedAuditTimeStamp(getTimeStamp());
        lineOfBusinessHierarchy.setVersionNo(lobHieracVO.getVersionNo());
        

        return lineOfBusinessHierarchy;
    }
    
    public LineOfBusinessHierarchy convertLobDotoVO(LineOfBusinessHierarchy lineOfBusinessHierarchy,LobHierarchyVO lobHieracVO)
    {

       

        try
        {
        	createVOAuditKeysList(lineOfBusinessHierarchy,lobHieracVO);
        	 Iterator it = lineOfBusinessHierarchy.getDepartments().iterator();
             while (it.hasNext())
             {
                 Department department = (Department) it.next();
                 createVOAuditKeysList(department,lobHieracVO);

             }
        	
        	
        	
        }
        catch (Exception e)
        {

            logger.error(e.getMessage(), e);
        }
       
        

        return lineOfBusinessHierarchy;
    }
    
    


    
    private void createVOAuditKeysList(EnterpriseBaseDomain enterpriseBaseDomain,AuditaleEnterpriseBaseVO  auditaleEnterpriseBaseVO){
    	
    	  List fullAuditList = new ArrayList();
    	  
    	  if(auditaleEnterpriseBaseVO.getAuditKeyList()!= null){
    	  	fullAuditList= auditaleEnterpriseBaseVO.getAuditKeyList();
    	  }
          
    	  
    	  
           LineItemAuditsDelegate auditDelegate;
    	try {
    		auditDelegate = new LineItemAuditsDelegate();
    		List resultList = auditDelegate.getAuditKeyList(enterpriseBaseDomain);
           
    	       if(resultList!=null){
    	       	fullAuditList.addAll(resultList);
    	       	
    	       	logger.debug("======LineItemAuditsDelegate====resultList====Size======="+resultList.size());
    	       	auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);
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
     * This method is used to get the User ID.
     * 
     * @return String : User ID
     */
    /*public String getUserID()
    {
        logger.info("getUserID");

        String userId = ContactManagementConstants.TEST_USERID;
        LobHierarchyControlBean lobHieraControlBean = new LobHierarchyControlBean();

        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = lobHieraControlBean.getUserData(
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
     * This method is used to check if the input field is null or is equal to an
     * empty string.
     * 
     * @param inputField :
     *            String inputField
     * @return boolean : true if input field is null or equal to an empty string
     *         else false
     */
    private boolean isNullOrEmptyField(String inputField)
    {
        logger.info("isNullOrEmptyField");

        return (inputField == null || ContactManagementConstants.EMPTY_STRING
                .equalsIgnoreCase(inputField.trim()));
    }

    /**
     * This method is used to get the Current Timestamp.
     * 
     * @return Timestamp : Current Timestamp.
     */
    private Timestamp getTimeStamp()
    {
        logger.info("getTimeStamp");

        Calendar cal = Calendar.getInstance();

        return new Timestamp(cal.getTimeInMillis());
    }

}
