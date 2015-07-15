
package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.acs.enterprise.common.audit.application.exception.GlobalAuditsBusinessException;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.benefitadministration.application.exception.LineOfBusinessNotFoundException;
import com.acs.enterprise.common.program.benefitadministration.common.delegate.LineOfBusinessDelegate;
import com.acs.enterprise.common.program.benefitadministration.common.domain.LineOfBusiness;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyDeleteBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyDuplicateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LobHierarchyDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LobHierarchyVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;


/**
 * @author Wipro This is a controller class used for Contact Management Groups
 *         related functionality in the presentation layer.
 */
public class LobHierarchyControlBean
        extends EnterpriseBaseControllerBean
{
    /**
     * EnterpriseLogger Name for Logging.
     */
    private static final EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(LobHierarchyControlBean.class);
    
    
    /**
	 * This field is used to store whether user has permission.
	 */
	private boolean controlRequired = true;
	private boolean controlRequiredFOrDelete = true;

	private String loadUserPermission;
    

	/**
	 * This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermission() {
		String userPermission = "";
		String userid = getUserID();
		logger
				.debug(" LobHierarchyControlBean:userid::"+ userid);
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.LOB_HIERARCHY_PAGE, userid);
			logger
			.debug(" LobHierarchyControlBean:userPermission"+ userPermission);
			
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			 logger.debug("error on LobHierarchyControlBean.getUserPermission :"
						+ e);
			
		}
		
		HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode",userPermission);
		userPermission = userPermission != null ? userPermission.trim() : "";
        // check the permission level for the user and set it in a boolean
		// variable. This will be used for Buttons/Links
		logger.debug("userPermission in LOB -->"+userPermission);
		if (userPermission.equals("r")) {
		    
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
	 * This method get the User ID
	 * 
	 * @return String
	 */
	private String getUserID() {
		String userID = null;
		/*try {
			HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(
					renderrequest, renderresponse);
			if (enterpriseUserProfile != null) {
				userID = enterpriseUserProfile.getUserId();
			}
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("LOGGED_IN_USERID", userID);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			logger.debug("Exception has come:");
			logger.debug("Exception on LobHierarchyControlBean.getUserID() :" + e);
		}*/
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		userID = getLoggedInUserID(portletRequest);

		return userID;
	}
    
    
    /**
     * This method is used for populating lobHierarchyDataBean object.
     * 
     * @return lobHierarchyDataBean object
     */
    public LobHierarchyDataBean getLobHierarchyDataBean()
    {
        logger
                .info("<<LobHierarchyControlBean> inside getlobHierarchyDataBean Method >>");

        FacesContext fc = FacesContext.getCurrentInstance();
        LobHierarchyDataBean lobHierarchyDataBean = (LobHierarchyDataBean) fc
                .getApplication()
                .createValueBinding(
                        ContactManagementConstants.BINDING_BEGIN_SEPARATOR
                                + ContactManagementConstants.LOB_HIRARCY_DATA_BEAN_NAME
                                + ContactManagementConstants.BINDING_END_SEPARATOR)
                .getValue(fc);
        return lobHierarchyDataBean;
    }

    /**
     * This method is used for populating LobHieracVO object.
     * 
     * @param identity
     *            holds identiry of particular object.
     * @return LobHieracVO
     */
    public LobHierarchyVO setLOB(String identity)
    {
        logger.debug("Entered into setLOB method of LobHierarchyControlBean");
        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        LobHierarchyVO lobHieracVO = new LobHierarchyVO();
        LobHierarchyVO lobHieracParentVO = new LobHierarchyVO();
        String idn = "";
        try
        {

            FacesContext context = FacesContext.getCurrentInstance();
            Map map = context.getExternalContext().getRequestParameterMap();
            idn = (String) map.get(identity);
            String[] str = idn
                    .split(ContactManagementConstants.HYPHEN_SEPARATOR);
            lobHieracParentVO.setLevelNumber(new Integer(str[1]));
            lobHieracParentVO.setHierachySK(new Long(str[2]));
            lobHieracVO.setLobCode(str[0]);
            LobHierarchyDOConverter lobHierarchyDOConverter = new LobHierarchyDOConverter();
            LineOfBusinessHierarchy lineOfBusinessHierarchy = new ContactMaintenanceDelegate()
                    .getLobHierarchyRecord(lobHieracParentVO.getHierachySK());
            lobHieracParentVO.setLobDesc(lineOfBusinessHierarchy
                    .getLobHierarchyDescription());
            Iterator it = lineOfBusinessHierarchy.getDepartments().iterator();
            while (it.hasNext())
            {
                Department department = (Department) it.next();
                lobHieracVO.setSupervisorName(department
                        .getSupervisorUserWorkUnitSK());

            }
            lobHieracVO.setLobHieracParentVO(lobHieracParentVO);
            lobHierarchyDOConverter.convertLobDotoVO(lineOfBusinessHierarchy,lobHieracVO);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        }
        finally
        {
        }
        lobHierarchyDataBean.setLobHieracVO(lobHieracVO);
        lobHierarchyDataBean.setFlag(true);
        lobHierarchyDataBean.setIdName(idn);
        lobHierarchyDataBean.setShowSucessMessage(Boolean.FALSE);
        lobHierarchyDataBean.setShowDeletedMessage(false);
        logger.debug("Exited from setLOB method of LobHierarchyControlBean");
        return lobHieracVO;
    }

    /**
     * This method is used for adding Business Unit.
     * 
     * @return String,denotes next navigation page
     */
    public String addBusinessUnit()
    {
        logger.debug("Entered into addBusinessUnit method of LobHierarchyControlBean");
        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        try
        {
            createLOBHierarchy();
        }
        catch (LOBHierarchyDuplicateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            lobHierarchyDataBean.setShowSucessMessage(Boolean.FALSE);
            setErrorMessage(ContactManagementConstants.ERR_DUPLICATE_BUSUNIT,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
        }
        logger
                .debug("Exited from addBusinessUnit method of LobHierarchyControlBean");
        return ContactManagementConstants.EDIT_REPORT_PAGE;

    }

    /**
     * This method is used for adding Departent .
     * 
     * @return String,denotes next navigation page
     */
    public String addDepartMent()
    {
        logger.debug("Entered into addDepartMent method of LobHierarchyControlBean");
        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        
        try
        {
        	createDepartmentLOBHierarchy();
        }
        catch (LOBHierarchyDuplicateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            lobHierarchyDataBean.setShowSucessMessage(Boolean.FALSE);
            setErrorMessage(ContactManagementConstants.ERR_DUPLICATE_DEPT,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
        }
        logger
                .debug("Exited from addDepartMent method of LobHierarchyControlBean");
        return ContactManagementConstants.EDIT_BUSINESS_PAGE;
    }

    /**
     * This method is used for adding Reporting Unit .
     * 
     * @return String,denotes next navigation page
     */
    public String addReportUnit()
    {
        logger.debug("Entered into addReportUnit method of LobHierarchyControlBean");
        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();

        try
        {
            createLOBHierarchy();
        }
        catch (LOBHierarchyDuplicateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            lobHierarchyDataBean.setShowSucessMessage(Boolean.FALSE);
            setErrorMessage(ContactManagementConstants.ERR_DUPLICATE_REPUNIT,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
        }
        logger
                .debug("Exited from addReportUnit method of LobHierarchyControlBean");
        return ContactManagementConstants.EDIT_LOB_PAGE;
    }

    /**
     * This method is used to create LobHierarchy record i.e. Creating Reporting
     * Unit, Business Unit and Department.
     * 
     * @return Boolean, denotes the status of the creation of record.
     */
    private Boolean createLOBHierarchy()
            throws LOBHierarchyDuplicateBusinessException
    {
        logger
                .debug("Entered into createLOBHierarchy method of LobHierarchyControlBean");

        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
        Boolean success = Boolean.FALSE;
        try
        {

            LobHierarchyDOConverter lobHierarchyDOConverter = new LobHierarchyDOConverter();
            LineOfBusinessHierarchy lineOfBusinessHierarchy = lobHierarchyDOConverter
                    .convertVOtoDO(lobHieracVO);

            LineOfBusinessDelegate lineOfBusinessDelegate = new LineOfBusinessDelegate();
            LineOfBusiness lineOfBusiness = lineOfBusinessDelegate
                    .getLineOfBusinessDetail(lobHieracVO.getLobCode());
            
           
            
            lineOfBusinessHierarchy.setLineOfBusiness(lineOfBusiness);
            if (validate(lobHieracVO.getLobDesc(), lineOfBusinessHierarchy
                    .getLobHierarchyLevelNumber().intValue()))
            {

                success = new ContactMaintenanceDelegate()
                        .createLOBHierarchy(lineOfBusinessHierarchy);
            }
            lobHierarchyDOConverter.convertLobDotoVO(lineOfBusinessHierarchy,lobHieracVO);
        }
        catch (LineOfBusinessNotFoundException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        }
        catch (LOBHierarchyDuplicateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            throw new LOBHierarchyDuplicateBusinessException(e);
        }
        catch (LOBHierarchyCreateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        }
        finally
        {
        }
        lobHierarchyDataBean.setFlag(true);
        lobHierarchyDataBean.setShowSucessMessage(success);
        lobHierarchyDataBean.setShowDeletedMessage(false);
        getLobHierarchyPlanTree().setTreeFlag(true);
        getLobHierarchyPlanTree().getModel();
        stateSession();
        logger
                .debug("Exited from createLOBHierarchy method of LobHierarchyControlBean");
        return success;
    }
    
    /**
     * This method is used to create LobHierarchy record i.e. 
     * Department.
     * 
     * @return Boolean, denotes the status of the creation of record.
     */
    private Boolean createDepartmentLOBHierarchy()
            throws LOBHierarchyDuplicateBusinessException
    {
        logger.debug("Entered into createLOBHierarchy method of LobHierarchyControlBean");
        boolean testFlag = false;

        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
        Boolean success = Boolean.FALSE;
        lobHierarchyDataBean.setShowSucessMessage(success);
        try {
            Long supervisor = lobHieracVO.getSupervisorName();
            //FindBugs issue Fixed
            //int levelNumber = lobHieracVO.getLobHieracParentVO()
                    //.getLevelNumber().intValue();
            LobHierarchyDOConverter lobHierarchyDOConverter = new LobHierarchyDOConverter();
            LineOfBusinessHierarchy lineOfBusinessHierarchy = lobHierarchyDOConverter
                    .convertVOtoDO(lobHieracVO);
            if (supervisor == null) {
                setErrorMessage(
                        ContactManagementConstants.SUPERVISOR_UNIT_REQUIRED,
                        new Object[] { "supervisorNames" },
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        "supervisorNames");
                testFlag = true;
            } else {

                LineOfBusinessDelegate lineOfBusinessDelegate = new LineOfBusinessDelegate();
                LineOfBusiness lineOfBusiness = lineOfBusinessDelegate
                        .getLineOfBusinessDetail(lobHieracVO.getLobCode());
                lineOfBusinessHierarchy.setLineOfBusiness(lineOfBusiness);

            }
            if (validate(lobHieracVO.getLobDesc(), lineOfBusinessHierarchy
                    .getLobHierarchyLevelNumber().intValue()) && !testFlag) {

                success = new ContactMaintenanceDelegate()
                        .createLOBHierarchy(lineOfBusinessHierarchy);
            }
            
            lobHierarchyDOConverter.convertLobDotoVO(lineOfBusinessHierarchy,lobHieracVO);
        } catch (LineOfBusinessNotFoundException e) {
            logger.error(e.getMessage(), e);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        } catch (LOBHierarchyDuplicateBusinessException e) {
            logger.error(e.getMessage(), e);
            throw new LOBHierarchyDuplicateBusinessException(e);
        } catch (LOBHierarchyCreateBusinessException e) {
            logger.error(e.getMessage(), e);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        } finally {
        }
        lobHierarchyDataBean.setFlag(true);
        lobHierarchyDataBean.setShowSucessMessage(success);
        lobHierarchyDataBean.setShowDeletedMessage(false);
        getLobHierarchyPlanTree().setTreeFlag(true);
        getLobHierarchyPlanTree().getModel();
        stateSession();
        logger
                .debug("Exited from createLOBHierarchy method of LobHierarchyControlBean");
        return success;}

    /**
     * This method is used for Updating Reporting Unit .
     * 
     * @return String,denotes next navigation page
     */
    public String updateReportUnit()
    {
        logger
                .info("<<LobHierarchyControlBean>>inside updateReportUnit Mehtod >>");
        try
        {
            updateLobHeirarchy();
        }
        catch (LOBHierarchyDuplicateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(ContactManagementConstants.ERR_DUPLICATE_REPUNIT,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
        }
        return ContactManagementConstants.EDIT_REPORT_PAGE;
    }

    /**
     * This method is used for Updating Business Unit .
     * 
     * @return String,denotes next navigation page
     */
    public String updateBusinessUnit()
    {
        logger
                .info("<<LobHierarchyControlBean>>inside updateBusinessUnit Mehtod >>");

        try
        {
            updateLobHeirarchy();
        }
        catch (LOBHierarchyDuplicateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(ContactManagementConstants.ERR_DUPLICATE_BUSUNIT,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
        }
        return ContactManagementConstants.EDIT_BUSINESS_PAGE;
    }

    /**
     * This method is used for Updating DepartMent .
     * 
     * @return String,denotes next navigation page
     */
    public String updateDepartMent()
    {
        logger
                .info("<<LobHierarchyControlBean>>inside updateDepartMent Mehtod >>");

        try
        {
        	updateLobHeirarchyWorkunit();
        }
        catch (LOBHierarchyDuplicateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(ContactManagementConstants.ERR_DUPLICATE_DEPT,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
        }
        return ContactManagementConstants.EDIT_DEPART_PAGE;
    }

    /**
     * This method is used for Updating Lob Heirarchy.
     * 
     * @return Boolean,denotes the success of the update
     */
    private Boolean updateLobHeirarchy()
            throws LOBHierarchyDuplicateBusinessException
    {
        logger
                .info("<<LobHierarchyControlBean>>inside updateLobHeirarchy Mehtod >>");

        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
        LobHierarchyDOConverter lobHierarchyDOConverter = new LobHierarchyDOConverter();
        Boolean success = Boolean.FALSE;
        try
        {
        	Long supervisor=lobHieracVO.getSupervisorName();
            String lobDesc = lobHieracVO.getLobHieracParentVO().getLobDesc();

            int levelNumber = lobHieracVO.getLobHieracParentVO()
                    .getLevelNumber().intValue();
            if (validate(lobDesc, levelNumber))
            {
                LineOfBusinessHierarchy lineOfBusinessHierarchy = new ContactMaintenanceDelegate()
                        .getLobHierarchyRecord(lobHieracVO
                                .getLobHieracParentVO().getHierachySK());
                lineOfBusinessHierarchy.setLobHierarchyDescription(lobDesc);
                LineOfBusinessDelegate lineOfBusinessDelegate = new LineOfBusinessDelegate();
                LineOfBusiness lineOfBusiness = lineOfBusinessDelegate
                        .getLineOfBusinessDetail(lobHieracVO.getLobCode());
                lineOfBusinessHierarchy.setLineOfBusiness(lineOfBusiness);
                lineOfBusinessHierarchy.setAuditTimeStamp(new Date());
				lineOfBusinessHierarchy.setAuditUserID(getUserID());
                if (levelNumber == ContactManagementConstants.FOUR.intValue())
                {
                    Iterator it = lineOfBusinessHierarchy.getDepartments()
                            .iterator();
                    while (it.hasNext())
                    {
                        Department department = (Department) it.next();

                        department.setName(lobDesc);
                        department.setSupervisorUserWorkUnitSK(lobHieracVO
                                .getSupervisorName());
                    }
                }
                success = new ContactMaintenanceDelegate()
                        .updateLOBHierarchy(lineOfBusinessHierarchy);
                lobHierarchyDOConverter.convertLobDotoVO(lineOfBusinessHierarchy,lobHieracVO);
            }
           
        }
        catch (LineOfBusinessNotFoundException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_EDIT_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        }
        catch (LOBHierarchyDuplicateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            throw new LOBHierarchyDuplicateBusinessException(e);
        }
        catch (LOBHierarchyUpdateBusinessException lhue)
        {
            logger.error(lhue.getMessage(), lhue);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_EDIT_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

        }
        catch (LOBHierarchyFetchBusinessException e)
        {

            logger.error(e.getMessage(), e);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_EDIT_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        }

        lobHierarchyDataBean.setShowSucessMessage(success);
        lobHierarchyDataBean.setShowDeletedMessage(false);
        
        lobHierarchyDataBean.setAuditLogRendered(false);
        getLobHierarchyPlanTree().setTreeFlag(true);
        getLobHierarchyPlanTree().getModel();
		showAuditHistory();
        return success;
    }
    
    /**
     * This method is used for Updating Lob Heirarchy. Workunit
     * 
     * @return Boolean,denotes the success of the update
     */
    private Boolean updateLobHeirarchyWorkunit()
            throws LOBHierarchyDuplicateBusinessException
    {
        logger
                .info("<<LobHierarchyControlBean>>inside updateLobHeirarchyWorkunit Mehtod >>");

        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
        LobHierarchyDOConverter lobHierarchyDOConverter = new LobHierarchyDOConverter();
        Boolean success = Boolean.FALSE;
        try
        {
        	Long supervisor=lobHieracVO.getSupervisorName();
            String lobDesc = lobHieracVO.getLobHieracParentVO().getLobDesc();

            int levelNumber = lobHieracVO.getLobHieracParentVO()
                    .getLevelNumber().intValue();
            if(supervisor==null)
            {
            	setErrorMessage(
                        ContactManagementConstants.SUPERVISOR_UNIT_REQUIRED,
                        new Object[] {"supervisorNames"},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        "supervisorNames");
            }
          if(validate(lobDesc, levelNumber))
          {
            if (supervisor != null  )
            {
                LineOfBusinessHierarchy lineOfBusinessHierarchy = new ContactMaintenanceDelegate()
                        .getLobHierarchyRecord(lobHieracVO
                                .getLobHieracParentVO().getHierachySK());
                lineOfBusinessHierarchy.setLobHierarchyDescription(lobDesc);
                LineOfBusinessDelegate lineOfBusinessDelegate = new LineOfBusinessDelegate();
                LineOfBusiness lineOfBusiness = lineOfBusinessDelegate
                        .getLineOfBusinessDetail(lobHieracVO.getLobCode());
                lineOfBusinessHierarchy.setLineOfBusiness(lineOfBusiness);
                lineOfBusinessHierarchy.setAuditTimeStamp(new Date());
				lineOfBusinessHierarchy.setAuditUserID(getUserID());
                if (levelNumber == ContactManagementConstants.FOUR.intValue())
                {
                    Iterator it = lineOfBusinessHierarchy.getDepartments()
                            .iterator();
                    while (it.hasNext())
                    {
                        Department department = (Department) it.next();

                        department.setName(lobDesc);
                        department.setSupervisorUserWorkUnitSK(lobHieracVO
                                .getSupervisorName());
                        department.setAuditTimeStamp(new Date());
                        department.setAuditUserID(getUserID());
                    }
                }
                success = new ContactMaintenanceDelegate()
                        .updateLOBHierarchy(lineOfBusinessHierarchy);
                lobHierarchyDOConverter.convertLobDotoVO(lineOfBusinessHierarchy,lobHieracVO);
            }
        }
          
        }
        catch (LineOfBusinessNotFoundException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_EDIT_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        }
        catch (LOBHierarchyDuplicateBusinessException e)
        {
            logger.error(e.getMessage(), e);
            throw new LOBHierarchyDuplicateBusinessException(e);
        }
        catch (LOBHierarchyUpdateBusinessException lhue)
        {
            logger.error(lhue.getMessage(), lhue);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_EDIT_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

        }
        catch (LOBHierarchyFetchBusinessException e)
        {

            logger.error(e.getMessage(), e);
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_EDIT_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        }

        lobHierarchyDataBean.setShowSucessMessage(success);
        lobHierarchyDataBean.setShowDeletedMessage(false);
        
        lobHierarchyDataBean.setAuditLogRendered(false);
        
        getLobHierarchyPlanTree().setTreeFlag(true);
        getLobHierarchyPlanTree().getModel();
        
		showAuditHistory();
        return success;
    }   

    /**
     * This method is used for Deleteing Reprting Unit .
     * 
     * @return String,denotes next navigation page
     */
    public String deleteReportUnit()
    {
        logger
                .info("<<LobHierarchyControlBean>> inside deleteReportUnit Method >>");
        //FindBugs issue Fixed
        //LineOfBusinessHierarchy lineOfBusinessHierarchy = new LineOfBusinessHierarchy();
        LineOfBusinessHierarchy lineOfBusinessHierarchy = null;

        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
        Boolean success = Boolean.FALSE;
        try
        {
            lineOfBusinessHierarchy = new ContactMaintenanceDelegate()
                    .getLobHierarchyRecord(lobHieracVO.getLobHieracParentVO()
                            .getHierachySK());
            
            if(lineOfBusinessHierarchy!=null){
            	lineOfBusinessHierarchy.setAuditUserID(getLoggedInUserID());	
            success = new ContactMaintenanceDelegate()
                    .deleteLOBHierarchy(lineOfBusinessHierarchy);
            }
            lobHierarchyDataBean.setFlag(false);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(
                    EnterpriseMessageConstants.ERR_SW_COULD_NOT_BE_FOUND,
                    new Object[] {ContactManagementConstants.LOB_HIERARCHY_NAME},
                    MessageUtil.ENTMESSAGES_BUNDLE, null);
        }
        catch (LOBHierarchyDeleteBusinessException lhue)
        {
            logger.error(lhue.getMessage(), lhue);
            setErrorMessage(ContactManagementConstants.ERR_RU_DELETE_FAILURE,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
        }

        lobHierarchyDataBean.setShowSucessMessage(success);
        lobHierarchyDataBean.setShowDeletedMessage(success.booleanValue());
        stateSession();
        getLobHierarchyPlanTree().setTreeFlag(true);
        getLobHierarchyPlanTree().getModel();
        return ContactManagementConstants.EDIT_REPORT_PAGE;
    }

    /**
     * This method is used for Deleteing Business Unit .
     * 
     * @return String,denotes next navigation page
     */
    public String deleteBusinessUnit()
    {

        logger
                .info("<<LobHierarchyControlBean>> inside deleteBusinessUnit Method >>");
        //FindBugs issue Fixed
      // LineOfBusinessHierarchy lineOfBusinessHierarchy = new LineOfBusinessHierarchy();
        LineOfBusinessHierarchy lineOfBusinessHierarchy = null;

        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
        Boolean success = Boolean.FALSE;
       
        try
        {
            lineOfBusinessHierarchy = new ContactMaintenanceDelegate()
                    .getLobHierarchyRecord(lobHieracVO.getLobHieracParentVO()
                            .getHierachySK());

            if(lineOfBusinessHierarchy!=null){
            	lineOfBusinessHierarchy.setAuditUserID(getLoggedInUserID());
            success = new ContactMaintenanceDelegate()
                    .deleteLOBHierarchy(lineOfBusinessHierarchy);
            }
            lobHierarchyDataBean.setFlag(false);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(
                    EnterpriseMessageConstants.ERR_SW_COULD_NOT_BE_FOUND,
                    new Object[] {ContactManagementConstants.LOB_HIERARCHY_NAME},
                    MessageUtil.ENTMESSAGES_BUNDLE, null);
            
        }
        catch (LOBHierarchyDeleteBusinessException lhue)
        {
            logger.error(lhue.getMessage(), lhue);
            setErrorMessage(ContactManagementConstants.ERR_BU_DELETE_FAILURE,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
            
        }
        stateSession();
        lobHierarchyDataBean.setShowSucessMessage(success);
        lobHierarchyDataBean.setShowDeletedMessage(success.booleanValue());
        getLobHierarchyPlanTree().setTreeFlag(true);
        getLobHierarchyPlanTree().getModel();
        
        return ContactManagementConstants.EDIT_BUSINESS_PAGE;
    }

    /**
     * This method is used for Deleteing DepartMent.
     * 
     * @return String,denotes next navigation page
     */
    public String deleteDepartMent()
    {

        logger
                .info("<<LobHierarchyControlBean>>inside deleteDepartMent Method >>");
        //FindBugs issue Fixed
        //LineOfBusinessHierarchy lineOfBusinessHierarchy = new LineOfBusinessHierarchy();
        LineOfBusinessHierarchy lineOfBusinessHierarchy = null;
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
        Boolean success = Boolean.FALSE;
        try
        {
            List deptWorkunits = new ArrayList();
            lineOfBusinessHierarchy = contactMaintenanceDelegate
                    .getLobHierarchyRecord(lobHieracVO.getLobHieracParentVO()
                            .getHierachySK());
            if (lineOfBusinessHierarchy != null)
            {
                Set depts = lineOfBusinessHierarchy.getDepartments();

                for (Iterator iter = depts.iterator(); iter.hasNext();)
                {
                    Department element = (Department) iter.next();
                    deptWorkunits.add(element.getWorkUnitSK());
                }
            }
            List deptUsers = contactMaintenanceDelegate.getUsers(deptWorkunits);
            if (deptUsers.size() > 1)
            {
                logger.debug("Department has users");
                setErrorMessage(ContactManagementConstants.ERR_DEPT_HAS_USERS,
                        new Object[] {},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        null);
            }
            else
            {
            	lineOfBusinessHierarchy.setAuditUserID(getLoggedInUserID());
                success = contactMaintenanceDelegate
                        .deleteLOBHierarchy(lineOfBusinessHierarchy);
                lobHierarchyDataBean.setFlag(false);
            }
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
            setErrorMessage(
                    EnterpriseMessageConstants.ERR_SW_COULD_NOT_BE_FOUND,
                    new Object[] {ContactManagementConstants.LOB_HIERARCHY_NAME},
                    MessageUtil.ENTMESSAGES_BUNDLE, null);
        }
        catch (LOBHierarchyDeleteBusinessException lhue)
        {
            logger.error(lhue.getMessage(), lhue);
            setErrorMessage(ContactManagementConstants.ERR_DEPT_DELETE_FAILURE,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
        }
        stateSession();
        lobHierarchyDataBean.setShowSucessMessage(success);
        lobHierarchyDataBean.setShowDeletedMessage(success.booleanValue());
        getLobHierarchyPlanTree().setTreeFlag(true);
        getLobHierarchyPlanTree().getModel();
        
        return ContactManagementConstants.EDIT_DEPART_PAGE;
    }

    /**
     * This method is used for Populate EditLob where we can Add the Report .
     * 
     * @return String,denotes next navigation page
     */
    public String addReport()
    {
        String identity = "ind";
        setLOB(identity);
        stateSession();
        return ContactManagementConstants.EDIT_LOB_PAGE;
    }

    /**
     * This method is used to Navigate to Add Report Unit Page
     * 
     * @return String,denotes next navigation page
     */
    public String addReportPage()
    {
        logger
                .info("<<LobHierarchyControlBean>>inside addReportPage Method >>");
        getLobHierarchyDataBean().setShowSucessMessage(Boolean.FALSE);
        getLobHierarchyDataBean().setShowDeletedMessage(false);
        stateSession();
        return ContactManagementConstants.ADD_REPORT_PAGE;
    }

    /**
     * This method is used to Navigate to Add Business Unit Page
     * 
     * @return String,denotes next navigation page
     */
    public String addBusinessPage()
    {

        logger
                .info("<<LobHierarchyControlBean>>inside addBusinessPage Method >>");

        getLobHierarchyDataBean().setShowSucessMessage(Boolean.FALSE);
        getLobHierarchyDataBean().setShowDeletedMessage(false);
        stateSession();
        return ContactManagementConstants.ADD_BUSINESS_PAGE;
    }

    /**
     * This method is used to Navigate to Add Department Page.
     * 
     * @return String,denotes next navigation page
     */
    public String addDepartPage()
    {

        logger
                .info("<<LobHierarchyControlBean>>inside addDepartPage Method >>");

        getLobHierarchyDataBean().setShowSucessMessage(Boolean.FALSE);
        getLobHierarchyDataBean().setShowDeletedMessage(false);
        stateSession();
        return ContactManagementConstants.ADD_DEPART_PAGE;
    }

    /**
     * This method is used to Navigate to Edit Lob Hierarchy invoked from edit
     * Reporting Unit, Business Unit and Department.
     */
    private void editLOBHierarchy()
    {
        getLobHierarchyDataBean().setFlag(false);
        getLobHierarchyDataBean().setShowAuditLogPage(true);

        String identity = "ind";
        setLOB(identity);
        stateSession();
    }

    /**
     * This method is used to Navigate to Edit Report Unit Page
     * 
     * @return String,denotes next navigation page
     */

    public String editReport()
    {
        logger.info("<<LobHierarchyControlBean>>inside editReport Method >>");
        editLOBHierarchy();
        return ContactManagementConstants.EDIT_REPORT_PAGE;
    }

    /**
     * This method is used to Navigate to Edit Business Unit Page
     * 
     * @return String,denotes next navigation page
     */
    public String editBusiness()
    {
        logger.info("<<LobHierarchyControlBean>>inside editBusiness Method >>");
        editLOBHierarchy();
        return ContactManagementConstants.EDIT_BUSINESS_PAGE;
    }

    /**
     * This method is used to Navigate Edit DepartMent
     * 
     * @return String,denotes next navigation page
     */
    public String editDepart()
    {
        logger.info("<<LobHierarchyControlBean>>inside editDepart Method >>");
        editLOBHierarchy();
        return ContactManagementConstants.EDIT_DEPART_PAGE;
    }

    /**
     * This method is used to Cancel
     * 
     * @return String,denotes next navigation page
     */
    public String cancel()
    {

        String name = "Success";
		getLobHierarchyDataBean().setShowSucessMessage(Boolean.FALSE);
        logger.info("<<LobHierarchyControlBean>>inside cancel Method >>");
        getLobHierarchyDataBean().setFlag(true);
        return name;
    }

    /**
     * This method is used to maintain the state.
     */
    public void stateSession()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        TreeModel model = (TreeModel) session
                .getAttribute(ContactManagementConstants.MODEL_OBJECT_NAME);
        session.setAttribute(ContactManagementConstants.LOB_PLAN_TREE_STATE,
                model.getTreeState());
    }

    /**
     * This operation is used to validate the lobHierarchy record before saving
     * it to database.
     * 
     * @param lobDesc
     *            lobDesc of string type
     * @param levelNumber
     *            levelNumber of int type
     * @return boolean
     */
    public boolean validate(String lobDesc, int levelNumber)
    {
        logger.info("INside validate");
        boolean flag = true;

        if (lobDesc == null
                || ContactManagementConstants.EMPTY_STRING.equals(lobDesc
                        .trim()))
        {
            if (levelNumber == ContactManagementConstants.TWO.intValue())
            {
            	//Code modified below for DEFECT ESPRD00722464 on 01/19/2012-Start
            	
            	 setErrorMessage(
                         ContactManagementConstants.REPORTING_UNIT_REQUIRED,
                         new Object[] {"reporting_name"},
                         ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                         ContactManagementConstants.REPORTING_NAME);
                flag = false;
            }
            else if (levelNumber == ContactManagementConstants.THREE.intValue())
            {
            	 setErrorMessage(
                         ContactManagementConstants.BUSINESS_UNIT_REQUIRED,
                         new Object[] {"LobHierarchyName2"},
                         ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                         ContactManagementConstants.LOB_HIERARCHY_NAME2);
            	
            	 //Code modified below for DEFECT ESPRD00722464 on 01/19/2012-End
                flag = false;
                                
            }
            else if (levelNumber == ContactManagementConstants.FOUR.intValue())
            {
                setErrorMessage(
                        ContactManagementConstants.DEPARTMENT_UNIT_REQUIRED,
                        new Object[] {"LobHierarchyName"},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        ContactManagementConstants.LOB_HIERARCHY_NAME);
                flag = false;
            }
        }
        else if (!testSpacebnWrds(lobDesc
                .trim()))
        {
            if (levelNumber == ContactManagementConstants.TWO.intValue())
            {
                setErrorMessage(ContactManagementConstants.SPL_REPORTING_UNIT,
                        new Object[] {},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        ContactManagementConstants.REPORTING_NAME);
                flag = false;
            }
            else if (levelNumber == ContactManagementConstants.THREE.intValue())
            {
                setErrorMessage(ContactManagementConstants.SPL_BUSINESS_UNIT,
                        new Object[] {},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        ContactManagementConstants.LOB_HIERARCHY_NAME2);
                flag = false;
            }
            else if (levelNumber == ContactManagementConstants.FOUR.intValue())
            {
                setErrorMessage(ContactManagementConstants.SPL_FIELD_NAME_UNIT,
                        new Object[] {},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        ContactManagementConstants.LOB_HIERARCHY_NAME);
                flag = false;
            }
        }
        return flag;
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
        logger.info("setErrorMessage");
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

            UIComponent uiComponent = findComponentInRoot(componentId);
            clientId = uiComponent.getClientId(facesContext);

            logger.debug("Client Id " + clientId);
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

        logger.info("findComponentInRoot");

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
        logger.info("findComponent");
        UIComponent result = null;

        // Is the "base" component itself the match we are looking for?
        if (id.equals(base.getId()))
        {
            result = base;
        }
        else
        {

            // Search through our facets and children
            Iterator cmpIterator = base.getFacetsAndChildren();

            while (cmpIterator.hasNext() && (result == null))
            {
                UIComponent component = (UIComponent) cmpIterator.next();
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
    
    /** AUDIT LOGGING * */

    /**
     * This operation will show audit log.
     * 
     * @return String
     * @throws GlobalAuditsBusinessException
     */
    public String showAuditLog() throws GlobalAuditsBusinessException {
        long entryTime = System.currentTimeMillis();
        getLobHierarchyDataBean().setAuditLogRendered(true);
        getLobHierarchyDataBean().setAuditColumnHistoryRender(false);
		showParentAuditHistory();
        long exitTime = System.currentTimeMillis();
        logger.info("UpdateLobHierarchyControllerBean" + "#" + "showAuditLog" + "#" + (exitTime - entryTime));
        return ProgramConstants.RETURN_SUCCESS;
    }

    /**
     * This operation will close audit log.
     * 
     * @return String
     */
    public String closeAuditLog() {	
        long entryTime = System.currentTimeMillis();
        logger.debug("Inside renderDiv()");
        getLobHierarchyDataBean().setAuditLogRendered(false);
        long exitTime = System.currentTimeMillis();
        if (logger.isInfoEnabled()) {
            logger.info("UpdateLobHierarchyControllerBean" + "#" + "closeAuditLog" + (exitTime - entryTime));
        }
        return ProgramConstants.RETURN_SUCCESS;
    }

	public void setRecordRange(EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		long entryTime = System.currentTimeMillis();
		int listSize;
		logger.debug("Inside setRecordRange ");
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		logger.debug("Total no of records-->" + totalRecordCount);
		LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
		lobHierarchyDataBean.setCount((int) totalRecordCount);
		
		int noOfPages = lobHierarchyDataBean.getCount()
				/ lobHierarchyDataBean.getItemsPerPage();

		int modNofPages = lobHierarchyDataBean.getCount()
				% lobHierarchyDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}
		logger.debug("Number Of pages: " + noOfPages);

		lobHierarchyDataBean
				.setCurrentPage(ProgramConstants.INITIAL_CURRENT_PAGE);
		lobHierarchyDataBean.setNumberOfPages(noOfPages);
		lobHierarchyDataBean.setStartRecord(ProgramConstants.START_RECORD);
		lobHierarchyDataBean
				.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = lobHierarchyDataBean.getCount();

		logger.debug("Total records count is : " + listSize);
		if (listSize <= lobHierarchyDataBean.getItemsPerPage()) {
			lobHierarchyDataBean.setEndRecord(listSize);
		}
		//FindBugs issue Fixed
/*		if (listSize > lobHierarchyDataBean.getItemsPerPage()) {
			listSize = lobHierarchyDataBean.getItemsPerPage();
		}
*/
		if (lobHierarchyDataBean.getCount() <= lobHierarchyDataBean
				.getItemsPerPage()) {
			lobHierarchyDataBean.setShowNext(false);
		} else {
			lobHierarchyDataBean.setShowNext(true);
		}
		lobHierarchyDataBean.setShowPrevious(false);
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ lobHierarchyDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ lobHierarchyDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ lobHierarchyDataBean.getStartRecord());
			logger.debug("The end record......."
					+ lobHierarchyDataBean.getEndRecord());
			logger.debug("The total count......."
					+ lobHierarchyDataBean.getCount());
		}
		long exitTime = System.currentTimeMillis();
		logger.info("LOBControllerBean" + "#" + "setRecordRange"
				+ "#" + "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
	}
	
	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}

	public String showColumnChange() {
		//FindBugs issue Fixed
		//long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
			getLobHierarchyDataBean().setAuditColumnHistoryRender(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getLobHierarchyDataBean().setSelectedAuditLog(auditLog);
		} catch (Exception e) {
			logger.debug("Error in show column change  " + e);
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	/**
	 * This method will be used for navigating to previous page in the
	 * pagination.
	 * 
	 * @return String.
	 */
	public String previous() {
		long entryTime = System.currentTimeMillis();
		logger.debug("Inside previous method");
		LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
		EnterpriseBaseDataBean entDataBean = previousPage(
				lobHierarchyDataBean, lobHierarchyDataBean
						.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("LOBControllerBean" + "#" + "previous" + "#"
					+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}

		return ProgramConstants.PREVIOUS;
	}
	

	/**
	 * This method will be used for writing the logic for populating search
	 * results for a given search criteria.
	 */
	private void populateList(int currentPage) {
		//FindBugs issue Fixed
		//ArrayList searchList = new ArrayList();
		LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
		GlobalAuditsDelegate globalAuditsDelegate = null;
		try {
			globalAuditsDelegate = new GlobalAuditsDelegate();
			LobHierarchyDOConverter lobHierarchyDOConverter = new LobHierarchyDOConverter();
			LineOfBusinessHierarchy lineOfBusinessHierarchy = lobHierarchyDOConverter.convertVOtoDO(lobHierarchyDataBean.getLobHieracVO());

			EnterpriseSearchResultsVO enterpriseResultVO = globalAuditsDelegate
					.getAuditLogList(lineOfBusinessHierarchy, (currentPage-1)
							* ProgramNumberConstants.INT_TEN,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

			lobHierarchyDataBean.setAuditHistoryList(enterpriseResultVO
							.getSearchResults());
			lobHierarchyDataBean.setAuditLogRendered(true);
		} catch (Exception e) {
			logger.debug(e);
		}
	}

	public String next() {
		long entryTime = System.currentTimeMillis();
		LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ lobHierarchyDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ lobHierarchyDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ lobHierarchyDataBean.getStartRecord());
			logger.debug("The end record......."
					+ lobHierarchyDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(lobHierarchyDataBean,
				lobHierarchyDataBean.getItemsPerPage());
		populateList(entDataBean.getCurrentPage());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("LOB controller" + "#" + "next"
					+ "#" + (exitTime - entryTime));
		}

		return ProgramConstants.NEXT;
	}
	
	public String showAuditHistory() {
		long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate audit;
		final List list = new ArrayList();
		try {
			logger.debug("in show child audit history");
			audit = new GlobalAuditsDelegate();
			
			LobHierarchyDOConverter lobHierarchyDOConverter = new LobHierarchyDOConverter();
			LineOfBusinessHierarchy lineOfBusinessHierarchy = lobHierarchyDOConverter.convertVOtoDO(getLobHierarchyDataBean().getLobHieracVO());

			list.add(lineOfBusinessHierarchy);
			//FindBugs issue Fixed
			//HashMap hm = audit.getAuditLogInfo(list);
			final EnterpriseSearchResultsVO enterpriseResultVO = audit.getAuditLogList(lineOfBusinessHierarchy,
					0, ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);			
			getLobHierarchyDataBean().setAuditHistoryList(enterpriseResultVO.getSearchResults());
			getLobHierarchyDataBean().setAuditHistoryRender(true);
			/* Added new for Expand */
//			getLobHierarchyDataBean().setAuditOpen(true);
			setRecordRange(enterpriseResultVO);
		} catch (Exception e) {
			logger.debug("Error in show audit history  " + e);
		}

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("CategoryControllerBean" + "#"
					+ "showAuditHistory" + "#" + (exitTime - entryTime));
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	/**
	 * showing audit Parent history for Pricing details.
	 * 
	 * @return String
	 */
	public String showParentAuditHistory() {
		long entryTime = System.currentTimeMillis();
		getLobHierarchyDataBean().setAuditParentHistoryRender(true);
		GlobalAuditsDelegate audit;
		try {
			logger.debug("in show Parentaudit history");
			audit = new GlobalAuditsDelegate();

			LobHierarchyDOConverter lobHierarchyDOConverter = new LobHierarchyDOConverter();
			LineOfBusinessHierarchy lineOfBusinessHierarchy = lobHierarchyDOConverter.convertVOtoDO(getLobHierarchyDataBean().getLobHieracVO());

			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(lineOfBusinessHierarchy.getParentLOBHierarchy(), 0,
							ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
			
			getLobHierarchyDataBean().setAuditParentHistoryList(enterpriseResultVO.getSearchResults());

			setRecordRange(enterpriseResultVO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error in show Parent audit history  " + e);
		}

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("UpdateSystemParameterControllerBean" + "#" + "#"
					+ (exitTime - entryTime));
		}
		return ProgramConstants.RETURN_SUCCESS;
	}
	
	public String showParentColumnChange() {
		//FindBugs issue Fixed
		//long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate auditDelegate = null;
		try {
			getLobHierarchyDataBean().setAuditParentColumnHistoryRender(true);
			auditDelegate = new GlobalAuditsDelegate();
			AuditLog auditLog = auditDelegate.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
			getLobHierarchyDataBean().setSelectedParentAuditLog(auditLog);
		} catch (Exception e) {
			logger.debug("Error in show column change  " + e);
		}
		return ProgramConstants.RETURN_SUCCESS;
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
    
    public boolean testSpacebnWrds(String expression)
    {
 	String ALPHANUMERIC_PATTERN = "[A-Z a-z0-9]*";    
 	Pattern p = Pattern.compile(ALPHANUMERIC_PATTERN);
        Matcher m = p.matcher(expression);
        return m.matches();
    }
    
    public String cancelEditUnit()
    {

        String name = "Success";
        logger.info("<<LobHierarchyControlBean>>inside cancel Method >>");
        getLobHierarchyDataBean().setFlag(false);
        getLobHierarchyDataBean().setShowAuditLogPage(false);
        
        return name;
    }
    
    protected LobHierarchyPlanTree getLobHierarchyPlanTree()
    {
    	LobHierarchyPlanTree lobHierarchyPlanTree;
        FacesContext fc = FacesContext.getCurrentInstance();
        lobHierarchyPlanTree = (LobHierarchyPlanTree)fc.getApplication().getVariableResolver().resolveVariable(fc,"lobHierarchyPlanTree");
        lobHierarchyPlanTree = (LobHierarchyPlanTree) fc.getApplication().createValueBinding(
                        "#{" + "lobHierarchyPlanTree" + "}").getValue(fc);
        return lobHierarchyPlanTree;
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
		logger.debug("inside doAuditKeyListOperation");
		LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
		try {
	        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
			if (lobHieracVO != null) {
					List editablelobHirarchy = new ArrayList();
					editablelobHirarchy.add(createAuditableFeild("Name:","lobHierarchyDescription"));
				//	editablelobHirarchy.add(createAuditableFeild("Supervisor:","supervisorUserWorkUnitSK"));

					if (lobHieracVO.getAuditKeyList() != null
							&& !(lobHieracVO.getAuditKeyList().isEmpty())) {
						logger.debug("======lobHieracVO====Before Filter for RU==="+ lobHieracVO.getAuditKeyList().size());
						AuditDataFilter.filterAuditKeys(editablelobHirarchy,lobHieracVO);
						logger.debug("======lobHieracVO====After Filter For RU==="
								+ lobHieracVO.getAuditKeyList().size());
					} else {
						logger
								.debug("======lobHieracVO====Before Filter AuditKeys Empty===");
					}
				}
			
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
		}

		lobHierarchyDataBean.setAuditLogFlag(true);
		lobHierarchyDataBean.setAuditLogFlagBU(false);
		lobHierarchyDataBean.setAuditLogFlagDept(false);

		return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	public String doAuditKeyListOperationForBU() {
		logger.debug("inside doAuditKeyListOperation");
		LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
		
		

		try {
			
			
	        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
			if (lobHieracVO != null) {
				
					List editablelobHirarchy = new ArrayList();
					editablelobHirarchy.add(createAuditableFeild("Name:","lobHierarchyDescription"));
				//	editablelobHirarchy.add(createAuditableFeild("Supervisor:","supervisorUserWorkUnitSK"));

					if (lobHieracVO.getAuditKeyList() != null
							&& !(lobHieracVO.getAuditKeyList().isEmpty())) {
						logger.debug("======lobHieracVO====Before Filter for BU==="+ lobHieracVO.getAuditKeyList().size());
						AuditDataFilter.filterAuditKeys(editablelobHirarchy,lobHieracVO);
						logger.debug("======lobHieracVO====After Filter for BU==="
								+ lobHieracVO.getAuditKeyList().size());
					} else {
						logger
								.debug("======lobHieracVO====Before Filter AuditKeys Empty===");
					}
				}
			
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
		}

		lobHierarchyDataBean.setAuditLogFlag(false);
		lobHierarchyDataBean.setAuditLogFlagBU(true);
		lobHierarchyDataBean.setAuditLogFlagDept(false);

		return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	public String doAuditKeyListOperationForDept() {
		logger.debug("inside doAuditKeyListOperation");
		LobHierarchyDataBean lobHierarchyDataBean = getLobHierarchyDataBean();
		
		

		try {
	        LobHierarchyVO lobHieracVO = lobHierarchyDataBean.getLobHieracVO();
			if (lobHieracVO != null) {
					List editablelobHirarchy = new ArrayList();
					editablelobHirarchy.add(createAuditableFeild("Name","lobHierarchyDescription"));
					editablelobHirarchy.add(createAuditableFeild("Supervisor","supervisorUserWorkUnitSK"));

					if (lobHieracVO.getAuditKeyList() != null
							&& !(lobHieracVO.getAuditKeyList().isEmpty())) {
						AuditDataFilter.filterAuditKeys(editablelobHirarchy,lobHieracVO);
					} 
				}
			// Code added for Defect ID :ESPRD00791530 ,in order to display proper Audit table in Edit work Unit Section....start
				UIComponent component = findComponentInRoot("CategoryAuditId");
		          
				if(component!=null)
				{
					AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
					auditHistoryTable.setValue(lobHierarchyDataBean.getLobHieracVO().getAuditKeyList());
					auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(false);
        
				}
				// Code added for Defect ID :ESPRD00791530 ,in order to display proper Audit table in Edit work Unit Section....end
				
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
		}

		lobHierarchyDataBean.setAuditLogFlag(false);
		lobHierarchyDataBean.setAuditLogFlagBU(false);
		lobHierarchyDataBean.setAuditLogFlagDept(true);

		return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	private AuditableField createAuditableFeild(String feildName,String domainAttributeName){

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
}
