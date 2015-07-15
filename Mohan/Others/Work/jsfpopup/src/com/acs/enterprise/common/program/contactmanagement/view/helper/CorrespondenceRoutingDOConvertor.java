/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
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
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.RoutingControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.RoutingDataBean;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro This class will be used for converting CategoryVO to
 *         CorrespondenceCategory domain object and vice versa.
 */
public class CorrespondenceRoutingDOConvertor
        extends DataTransferObjectConverter
{
    /** Enterprise Logger for Logging. */
    private EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CorrespondenceRoutingDOConvertor.class);

    /**
     * This is the CorrespondenceRoutingDOConvertor Constructor
     */
    public CorrespondenceRoutingDOConvertor()
    {

        super();
        
    }

    /**
     * @param cmRoutingVO
     *            The cmRoutingVO to set.
     * @return CorrespondenceRouting
     */
    public CorrespondenceRouting convertRoutingVOToDO(CMRoutingVO cmRoutingVO)
    {
        

        CorrespondenceRouting correspondenceRouting = new CorrespondenceRouting();
       /* DateFormat dateFormat = new SimpleDateFormat(
                ContactManagementConstants.DATE_FORMAT_TIME, Locale
                        .getDefault());*/ // Find bug issue

        // correspondenceRouting.setCorrespondenceSK() - will be set in the
        // correspondence converter.
        // correspondenceRouting.setCorrespondence() - will be set in the
        // correspondence converter.
        //"FindBugs" issue fixed
        //correspondenceRouting.setDaysAssignedCount(new Long(cmRoutingVO.getDaysAssigned()));
        correspondenceRouting.setDaysAssignedCount(Long.valueOf(cmRoutingVO.getDaysAssigned()));
               

        WorkUnit routedByWorkUnit = new WorkUnit();

        EnterpriseUser routedByUser = new EnterpriseUser();
        //setUserName(cmRoutingVO.getRoutedByName(), routedByUser);
        //routedByUser.setWorkUnit(routedByWorkUnit);

        routedByWorkUnit.setEnterpriseUser(routedByUser);
        routedByWorkUnit.setWorkUnitTypeCode("U");
        routedByWorkUnit.setWorkUnitSK(Long
                .valueOf(cmRoutingVO.getRoutedBySK()));
        if(logger.isDebugEnabled())
        {
        	logger.debug("routedByWorkUnit.getWorkUnitSK()" + routedByWorkUnit.getWorkUnitSK());
        }

        correspondenceRouting.setRoutedByWorkUnit(routedByWorkUnit);
        /*
         * if ("U".equalsIgnoreCase(cmRoutingVO.getGroupType())) {
         * routedToWorkUnit.setWorkUnitSK(Long.valueOf(cmRoutingVO
         * .getAssignThisRecordToSK()));
         * correspondenceRouting.setRoutedToWorkUnitSK(Long
         * .valueOf(cmRoutingVO.getAssignThisRecordToSK()));
         * correspondenceRouting.setRoutedToWorkUnit(routedToWorkUnit); } else {
         * WorkUnit routedToWorkUnit = new WorkUnit();
         * routedToWorkUnit.setWorkUnitSK(Long
         * .valueOf(cmRoutingVO.getDeptSK()));
         * correspondenceRouting.setRoutedToWorkUnitSK(Long
         * .valueOf(cmRoutingVO.getDeptSK()));
         * correspondenceRouting.setRoutedToWorkUnit(routedToWorkUnit); }
         */
        if(logger.isDebugEnabled())
        {
        	logger.debug("cmRoutingVO.getRoutedToSK()" + cmRoutingVO.getRoutedToSK());
        }

        WorkUnit routedToWorkUnit = new WorkUnit();
        routedToWorkUnit.setWorkUnitSK(Long
                .valueOf(cmRoutingVO.getRoutedToSK()));

        if ("U".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
            EnterpriseUser routedToUser = new EnterpriseUser();
            //setUserName(cmRoutingVO.getRoutedToName(), routedToUser);
            routedToWorkUnit.setEnterpriseUser(routedToUser);
            //routedToUser.setWorkUnit(routedToWorkUnit);
        }
        else if ("W".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
        	if(logger.isInfoEnabled())
        	{
        		logger.info("cmRoutingVO.getAssignThisRecordToSK()"+cmRoutingVO.getAssignThisRecordToSK());
        	}
        	if(cmRoutingVO.getAssignThisRecordToSK()!= null &&
            		cmRoutingVO.getAssignThisRecordToSK().equals("") && cmRoutingVO.getRoutedToSK() != null)
            {
                EnterpriseUser routedToUser = new EnterpriseUser();
               	//"FindBugs" issue fixed
                //routedToUser.setUserWorkUnitSK(new Long(Long.parseLong(cmRoutingVO.getRoutedToSK())));
                routedToUser.setUserWorkUnitSK(Long.valueOf(Long.parseLong(cmRoutingVO.getRoutedToSK())));
                //setUserName(cmRoutingVO.getRoutedToName(), routedToUser);
                routedToWorkUnit.setEnterpriseUser(routedToUser);
            }
            else if(cmRoutingVO.getRoutedToSK() != null)
            {
                Department department = new Department();
               	//"FindBugs" issue fixed 
                //department.setWorkUnitSK(new Long(Long.parseLong(cmRoutingVO.getRoutedToSK())));
                department.setWorkUnitSK(Long.valueOf(Long.parseLong(cmRoutingVO.getRoutedToSK())));
               	//department.setName(cmRoutingVO.getRoutedToName());
               	routedToWorkUnit.setDepartment(department);
            }
            //department.setWorkUnit(routedToWorkUnit);
        }
        else if ("B".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
            Department department = new Department();
            department.setName(cmRoutingVO.getRoutedToName());
            routedToWorkUnit.setDepartment(department);
        }

        routedToWorkUnit.setWorkUnitTypeCode(cmRoutingVO.getGroupType());
        correspondenceRouting.setRoutedToWorkUnitSK(routedToWorkUnit
                .getWorkUnitSK());
        correspondenceRouting.setRoutedToWorkUnit(routedToWorkUnit);
        if(logger.isDebugEnabled())
        {
        logger.debug("routedToWorkUnit.getWorkUnitSK()"
                + routedToWorkUnit.getWorkUnitSK());
        }
        if(logger.isInfoEnabled())
        {
        logger.info("cmRoutingVO.getDateRouted()====="+cmRoutingVO.getDateRouted());
        }
        
        correspondenceRouting.setCorrespondenceRoutingDate(cmRoutingVO.getDateRouted());
        correspondenceRouting.setWatchlistIndicator(Boolean.valueOf(cmRoutingVO
                .isAddToMyWatchList()));
        correspondenceRouting.setVersionNo(cmRoutingVO.getVersionNo());
        correspondenceRouting.setAuditUserID(getLoggedInUserID());
        correspondenceRouting.setAddedAuditUserID(getLoggedInUserID());
        correspondenceRouting.setAuditTimeStamp(cmRoutingVO.getAuditTimeStamp());
        correspondenceRouting.setAddedAuditTimeStamp(cmRoutingVO.getAddedAuditTimeStamp());
        
        return correspondenceRouting;
    }

    /**
     * @param crspdRouting
     *            The crspdRouting to set.
     * @return CMRoutingVO
     */
    public CMRoutingVO convertRoutingDOToVO(CorrespondenceRouting crspdRouting)
    {
        

        CMRoutingVO cmRoutingVO = new CMRoutingVO();
        DateFormat dateFormat = new SimpleDateFormat(
                ContactManagementConstants.DATE_FORMAT_TIME, Locale
                        .getDefault());

        cmRoutingVO.setRoutedByName(getName(crspdRouting.getRoutedByWorkUnit(),
                "U"));
        cmRoutingVO.setRoutedBySK(crspdRouting.getRoutedByWorkUnit()
                .getWorkUnitSK().toString());
        EnterpriseUser user = crspdRouting.getRoutedByWorkUnit().getEnterpriseUser();
        cmRoutingVO.setRoutedByUserID(user.getUserID());
        cmRoutingVO.setGroupType(crspdRouting.getRoutedToWorkUnit()
                .getWorkUnitTypeCode());
        if(logger.isDebugEnabled())
        {
        	logger.debug("Group Type" + cmRoutingVO.getGroupType() + "<<");
        }

        if ("U".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
        	EnterpriseUser routeToUser = crspdRouting.getRoutedToWorkUnit().getEnterpriseUser();
        	
        	cmRoutingVO.setAssignThisRecordToSK(crspdRouting
                    .getRoutedToWorkUnit().getWorkUnitSK().toString());
            cmRoutingVO.setAssThisRecToName(getName(crspdRouting
                    .getRoutedToWorkUnit(), "U"));
            if(routeToUser != null)
            {
            	cmRoutingVO.setAssignThisRecordToUserID(routeToUser.getUserID());
            }
            cmRoutingVO.setRoutedToSK(crspdRouting.getRoutedToWorkUnit()
                    .getWorkUnitSK().toString());
            cmRoutingVO.setRoutedToName(cmRoutingVO.getAssThisRecToName());
            
            cmRoutingVO.setRoutedToUserID(cmRoutingVO.getAssignThisRecordToUserID());
        }
        else if ("W".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
            
            cmRoutingVO.setDeptSK(crspdRouting.getRoutedToWorkUnit()
                    .getWorkUnitSK().toString());

            cmRoutingVO.setDeptName(getName(crspdRouting.getRoutedToWorkUnit(),
                    "W"));

            cmRoutingVO.setRoutedToSK(crspdRouting.getRoutedToWorkUnit()
                    .getWorkUnitSK().toString());
            cmRoutingVO.setRoutedToName(cmRoutingVO.getDeptName());
        }
        else if ("B".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
           
            cmRoutingVO.setDeptSK(crspdRouting.getRoutedToWorkUnit()
                    .getWorkUnitSK().toString());

            cmRoutingVO.setDeptName(getName(crspdRouting.getRoutedToWorkUnit(),
                    "B"));

            cmRoutingVO.setRoutedToSK(crspdRouting.getRoutedToWorkUnit()
                    .getWorkUnitSK().toString());
            cmRoutingVO.setRoutedToName(cmRoutingVO.getDeptName());
        }

        if(logger.isInfoEnabled())
        {
        	logger.info("getCorrespondenceRoutingDate()"+crspdRouting.getCorrespondenceRoutingDate());
        }
        cmRoutingVO.setDateRouted(crspdRouting.getCorrespondenceRoutingDate());
        cmRoutingVO.setDateRoutedString(dateFormat.format(crspdRouting.getCorrespondenceRoutingDate()));
        cmRoutingVO.setAddToMyWatchList(crspdRouting.getWatchlistIndicator()
                .booleanValue());

        if (getRoutingDataBean().getRefListOfWorkUnitTypes() == null
                || getRoutingDataBean().getRefListOfWorkUnitTypes().isEmpty())
        {
            getReferenceData();
        }

        setDescForRoutingValidValues(cmRoutingVO);
       
        if(crspdRouting.getDaysAssignedCount()!=null) {
        cmRoutingVO.setDaysAssigned(crspdRouting.getDaysAssignedCount()
                .longValue());
        }
        cmRoutingVO.setDbRecord(true);
        if(logger.isDebugEnabled())
        {
        	logger.debug("after crspdRouting.getVersionNo()"+crspdRouting.getVersionNo());
        }
        
        cmRoutingVO.setVersionNo(crspdRouting.getVersionNo());
        if(logger.isDebugEnabled())
        {
        	logger.debug("after crspdRouting.getAuditUserID()"+crspdRouting.getAuditUserID());
        }
        cmRoutingVO.setAuditUserID(crspdRouting.getAuditUserID());
        if(logger.isDebugEnabled())
        {
        	logger.debug("after crspdRouting.getAddedAuditUserID()"+crspdRouting.getAddedAuditUserID());
        }
        cmRoutingVO.setAddedAuditUserID(crspdRouting.getAddedAuditUserID());
        if(logger.isDebugEnabled())
        {
        	logger.debug("after crspdRouting.getAuditTimeStamp()"+crspdRouting.getAuditTimeStamp());
        }
        cmRoutingVO.setAuditTimeStamp(crspdRouting.getAuditTimeStamp());
        if(logger.isDebugEnabled())
        {
        	logger.debug("after crspdRouting.getAddedAuditTimeStamp()"+crspdRouting.getAddedAuditTimeStamp());
        }
        cmRoutingVO.setAddedAuditTimeStamp(crspdRouting
                .getAddedAuditTimeStamp());
      
       createVOAuditKeysList(crspdRouting,cmRoutingVO);
       createVOAuditKeysList(crspdRouting.getRoutedToWorkUnit(),cmRoutingVO);
       createVOAuditKeysList(crspdRouting.getRoutedToWorkUnit().getEnterpriseUser(), cmRoutingVO);

        return cmRoutingVO;
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
        FacesContext fc = FacesContext.getCurrentInstance();
        RoutingControllerBean routingControllerBean = new RoutingControllerBean();

        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = routingControllerBean.getUserData(
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
     * This operation is used to get the reference data.
     */
    public static void getReferenceData()
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRouting.class);

        

        List referenceList = new ArrayList();
        Map referenceMap = null;

        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        //"FindBugs" issue fixed.
        //ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
        ReferenceDataListVO referenceDataListVO =null;
        createInputCriterias(referenceList);

        referenceDataSearch.setInputList(referenceList);

        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);

            referenceMap = referenceDataListVO.getResponseMap();

            if (referenceMap != null)
            {
                List refWorkUnitTypeList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.GENERAL,
                        ReferenceServiceDataConstants.G_WORK_UNIT_TY_CD);
                // String buSelectMenuValue= new String("Business Units");
                // String buSelectMenuLabel= new String("B-Business Unit");
                // refWorkUnitTypeList.add(new
                // SelectItem(buSelectMenuValue,buSelectMenuLabel));

                getRoutingDataBean().setRefListOfWorkUnitTypes(
                        refWorkUnitTypeList);
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
    }

    /**
     * This method is used to created the input criteria to get the reference
     * date.
     * 
     * @param referenceList :
     *            List of Reference Data.
     */
    private static void createInputCriterias(List referenceList)
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRouting.class);

        

        InputCriteria ipCrtWorkUnitType = new InputCriteria();
        ipCrtWorkUnitType
                .setFunctionalArea(FunctionalAreaConstants.GENERAL);
        ipCrtWorkUnitType
                .setElementName(ReferenceServiceDataConstants.G_WORK_UNIT_TY_CD);

        referenceList.add(ipCrtWorkUnitType);
    }

    /**
     * This method is used to get the reference service list from the reference
     * map based on the fuctional area constant and elementName
     * 
     * @param referenceMap :
     *            Map of reference list.
     * @param functionalAreaConstant :
     *            Reference Service Functional Area Constants to get.
     * @param elementName :
     *            Reference Service Element to get.
     * @return tempRevenueTypeList : Temporary Reference Service List.
     */
    private static List getReferenceList(Map referenceMap,
            String functionalAreaConstant, String elementName)
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRouting.class);

        

        List referenceList = null;
        List tempRevenueTypeList = new ArrayList();

        referenceList = (List) referenceMap.get(functionalAreaConstant
                + ProgramConstants.HASH_SEPARATOR + elementName);

        if (referenceList != null)
        {
            for (int i = 0; i < referenceList.size(); i++)
            {
                ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                        .get(i);
                tempRevenueTypeList.add(new SelectItem(refVo
                        .getValidValueCode(), refVo.getValidValueCode()
                        + ProgramConstants.HYPHEN_SEPARATOR
                        + refVo.getShortDescription()));
            }
        }

        return tempRevenueTypeList;
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
     *            String inputField
     * @return boolean : true if input field is null or equal to an empty string
     *         else false
     */
    private boolean isNullOrEmptyField(String inputField)
    {
        

        return (inputField == null || ContactManagementConstants.EMPTY_STRING
                .equalsIgnoreCase(inputField.trim()));
    }

    /**
     * @param cmRoutingVO
     *            The cmRoutingVO to set.
     */
    public static void setDescForRoutingValidValues(CMRoutingVO cmRoutingVO)
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRoutingDOConvertor.class);

        

        RoutingDataBean routingDataBean = getRoutingDataBean();

        String workUnitType = cmRoutingVO.getGroupType();

        List listOfWorkUnitTypes = routingDataBean.getRefListOfWorkUnitTypes();
        
        String workUnitTypeDesc = getDescriptionFromVV(workUnitType,
                listOfWorkUnitTypes);
        
        cmRoutingVO.setGroupTypeDesc(workUnitTypeDesc);
        
        // return;
    }

    /**
     * @param cmRoutingVO
     *            The cmRoutingVO to set.
     */
    public static void setUserNameFromList(CMRoutingVO cmRoutingVO)
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRoutingDOConvertor.class);

        

        String assignedToUserSK = cmRoutingVO.getAssignThisRecordToSK();
        if(logger.isDebugEnabled())
        {
        	logger.debug("assignedToUserSK " + assignedToUserSK);
        }
        List userList = null;
        if("U".equalsIgnoreCase(cmRoutingVO.getGroupType()))
        {
        	userList = getRoutingDataBean().getListOfUsers();
        }else{
        	userList = getRoutingDataBean().getListOfDeptUsers();
        }

        String assignedToUserName = getDescriptionFromVV(assignedToUserSK,
                userList);
        if(logger.isDebugEnabled())
        {
        	logger.debug("assignedToUserName " + assignedToUserName);
        }

        cmRoutingVO.setAssThisRecToName(assignedToUserName);
    }

    /**
     * @param cmRoutingVO
     *            The cmRoutingVO to set.
     */
    public static void setDeptNameFromList(CMRoutingVO cmRoutingVO)
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRoutingDOConvertor.class);

        

        String assignedToDeptSK = cmRoutingVO.getDeptSK();
        if(logger.isDebugEnabled())
        {
        	logger.debug("assignedToDeptSK " + assignedToDeptSK);
        }

        List deptList = getRoutingDataBean().getListOfDepts();

        String assignedToDeptName = getDescriptionFromVV(assignedToDeptSK,
                deptList);
        if(logger.isDebugEnabled())
        {
        	logger.debug("assignedToDeptName " + assignedToDeptName);
        }

        cmRoutingVO.setDeptName(assignedToDeptName);
    }
    
    /**
     * @param cmRoutingVO
     *            The cmRoutingVO to set.
     */
    public static void setUserDeptNameFromList(CMRoutingVO cmRoutingVO)
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRoutingDOConvertor.class);

        

        String assignedToDeptSK = cmRoutingVO.getDeptSK();
        if(logger.isDebugEnabled())
        {
        	logger.debug("assignedToDeptSK " + assignedToDeptSK);
        }

        List userDeptList = getRoutingDataBean().getListOfUserDepts();

        String assignedToDeptName = getDescriptionFromVV(assignedToDeptSK,
                userDeptList);
        if(logger.isDebugEnabled())
        {
        	logger.debug("assignedToDeptName " + assignedToDeptName);
        }

        cmRoutingVO.setDeptName(assignedToDeptName);
    }

    /**
     * To get the description based on code provided.
     * 
     * @param code :
     *            Holds the code value
     * @param vvList :
     *            Holds the List of valid values
     * @return String : Description of the code provided.
     */
    public static String getDescriptionFromVV(String code, List vvList)
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRoutingDOConvertor.class);

       

        String desc = ProgramConstants.EMPTY_STRING;
        String valueStr = ProgramConstants.EMPTY_STRING;
        if (vvList != null && vvList.size() > 0)
        {
            for (Iterator iter = vvList.iterator(); iter.hasNext();)
            {
                SelectItem selectItem = (SelectItem) iter.next();
                valueStr = (String) selectItem.getValue();
                if (valueStr != null && valueStr.equalsIgnoreCase(code))
                {
                    desc = selectItem.getLabel();
                    break;
                }
            }
        }
        return desc;
    }

    /**
     * @param workUnit
     *            The workUnit to set.
     * @param workUnitTypeCode
     *            The workUnitTypeCode to set.
     * @return String
     */
    private String getName(WorkUnit workUnit, String workUnitTypeCode)
    {
        

        String name = ContactManagementConstants.EMPTY_STRING;

        if (workUnit != null && "U".equalsIgnoreCase(workUnitTypeCode))
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
        if (workUnit != null && "W".equalsIgnoreCase(workUnitTypeCode))
        {
            Department department = workUnit.getDepartment();
            name = department.getName();
        }

        if (workUnit != null && "B".equalsIgnoreCase(workUnitTypeCode))
        {
            Department department = workUnit.getDepartment();
            name = department.getName();
        }
        return name;
    }

    /**
     * @param name
     *            The name to set.
     * @param user
     *            The user to set.
     */
    private void setUserName(String name, EnterpriseUser user)
    {
       

        String nameSeparator = ContactManagementConstants.COMMA_SEPARATOR;
        int indexOfSeparator = name.indexOf(nameSeparator);
        String firstName = ContactManagementConstants.EMPTY_STRING;

        String lastName = name.substring(0, indexOfSeparator);
        
        
        if(name.matches(".*-.*"))
        {
        	int firstNameIndexOfSeperator = name.indexOf("-");
        	
        	firstName = name.substring(indexOfSeparator + 1, firstNameIndexOfSeperator);
        }
        else
        {
        	firstName = name.substring(indexOfSeparator + 1, name.length());
        }
        
        
        user.setFirstName(firstName);
        user.setLastName(lastName);
        
    }

    /**
     * This method is used to get the Routing Data Bean.
     * 
     * @return RoutingDataBean : RoutingDataBean object.
     */
    public static RoutingDataBean getRoutingDataBean()
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRoutingDOConvertor.class);

        

        FacesContext fc = FacesContext.getCurrentInstance();
        return ((RoutingDataBean) fc.getApplication().createValueBinding(
                ContactManagementConstants.BINDING_BEGIN_SEPARATOR
                        + ContactManagementConstants.ROUTING_DATA_BEAN_NAME
                        + ContactManagementConstants.BINDING_END_SEPARATOR)
                .getValue(fc));
    }
    
    /**
     * 
     * @param cmRoutingVO
     */
    public static void setUserNameForBusinessUnit(CMRoutingVO cmRoutingVO)
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceRoutingDOConvertor.class);

        

        String assignedToUserSK = cmRoutingVO.getAssignThisRecordToSK();
        if(logger.isDebugEnabled())
        {
        	logger.debug("assignedToUserSK " + assignedToUserSK);
        }

        List userList = getRoutingDataBean().getListOfDeptUsers();

        String assignedToUserName = getDescriptionFromVV(assignedToUserSK,
                userList);
        if(logger.isDebugEnabled())
        {
        logger.debug("assignedToUserName " + assignedToUserName);
        }

        cmRoutingVO.setAssThisRecToName(assignedToUserName);
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
    	       	if(logger.isDebugEnabled())
    	       	{
    	       		logger.debug("======LineItemAuditsDelegate====resultList====Size======="+resultList.size());
    	       	}
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

}
