/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
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
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Alert;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.common.vo.AlertVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.AlertControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.AlertDataBean;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;


/**
 * @author Wipro This class will be used for converting CategoryVO to
 *         CorrespondenceCategory domain object and vice versa.
 */
public class AlertDOConvertor
        extends DataTransferObjectConverter
{
    /** Enterprise Logger for Logging. */
    /*private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(AlertDOConvertor.class);*/
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(AlertDOConvertor.class);
	
	/**
     * Holds the Bean name
     */
    
    private AlertDataBean alertDataBean;
    
    

    /**
     * Creates constructor of AlertDOConvertor class.
     */
    public AlertDOConvertor()
    {
        super();
        
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
     * @param alertVO
     *            The alertVO to set.
     * @return Alert
     */

    public Alert convertAlertVOToDO(AlertVO alertVO)
    {
        
        Alert alert = new Alert();
        DateFormat dateFormat = new SimpleDateFormat(
                ContactManagementConstants.DATE_FORMAT, Locale.getDefault());

        // alert.setCorrespondence() will be set in correspondence
        if (alertVO.getAlertSK() != null)
        {
            alert.setAlertSK(Long.valueOf(alertVO.getAlertSK()));
            if(logger.isDebugEnabled())
            {
            	logger.debug(" &&AlertSk :"+ Long.valueOf(alertVO.getAlertSK()));
            }
        }
        alert.setAlertStatusCode(alertVO.getStatus());
        alert.setAlertTypeCode(alertVO.getAlertType());
        alert.setDescription(alertVO.getDescription());
        if(logger.isDebugEnabled())
        {
        	logger.debug("&&  AlertStatusCode :" + alertVO.getStatus());
        	logger.debug("&&  AlertTypeCode :"   + alertVO.getAlertType());
        }

        try
        {
            if(alertVO.getDueDate() != null)
            {
            	alert.setDueDate(dateFormat.parse(alertVO.getDueDate()));
            }
        	
        }
        catch (ParseException e)
        {
        	if(logger.isErrorEnabled())
                {
        			logger.error(e.getMessage(), e);
                }
        }

      //commented for unused variables
       // WorkUnit workUnit = new WorkUnit();
	//commented for unused variables

        //EnterpriseUser notifyToUser = new EnterpriseUser();
        //setUserName(alertVO.getUserWorkUnitDesc(), notifyToUser);
        //workUnit.setEnterpriseUser(notifyToUser);
        //notifyToUser.setWorkUnit(workUnit);

        //workUnit.setWorkUnitSK(Long.valueOf(alertVO.getUserWorkUnitSK()));
        if(logger.isInfoEnabled())
        {
        	logger.info("userid------"+alertVO.getUserId());
        	logger.info("sk--------"+alertVO.getUserWorkUnitSK());
        }
        EnterpriseUser user = new EnterpriseUser();
        user.setUserID(alertVO.getUserId());
        if(alertVO.getUserWorkUnitSK() != null){
        	user.setUserWorkUnitSK(Long.valueOf(alertVO.getUserWorkUnitSK()));
        }
        alert.setEnterpriseUser(user);
        
        //alert.setUserWorkUnit(workUnit);
        alert.setEnterpriseUser(user);
        alert.setReceivingUserID(alertVO.getUserId());
        alert.setAuditUserID(getLoggedInUserID());
        alert.setAddedAuditUserID(getLoggedInUserID());
        alert.setVersionNo(alertVO.getVersionNo());
          //  logger.debug(" AlertUserWorkUnit :" + workUnit);  //commented for unused variables
        if(logger.isDebugEnabled())
        {
        	logger.debug(" AlertReceivingUserID :" + alertVO.getUserId());
        	logger.debug(" AlertAuditUserID :" + getLoggedInUserID());
        }
        return alert;
    }

    /**
     * @param alert
     *            The alert to set.
     * @return AlertVO
     */
    public AlertVO convertAlertDOToVO(Alert alert)
    {
        alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
        AlertVO alertVO = new AlertVO();
        DateFormat dateFormat = new SimpleDateFormat(
                ContactManagementConstants.DATE_FORMAT, Locale.getDefault());

        alertVO.setAlertSK(alert.getAlertSK().toString());
        alertVO.setAlertType(alert.getAlertTypeCode());
        alertVO.setDescription(alert.getDescription());
        if(alert.getDueDate() != null)
        {
        	alertVO.setDueDate(dateFormat.format(alert.getDueDate()));
        }
        alertVO.setDtDueDate(alert.getDueDate());
        alertVO.setStatus(alert.getAlertStatusCode());
        alertVO.setUserId(alert.getEnterpriseUser().getUserID());
        alertVO.setUserWorkUnitSK(alert.getEnterpriseUser().getUserWorkUnitSK()
                .toString());

        String userName = getNotifyToUserName(alert.getEnterpriseUser());
        alertVO.setUserWorkUnitDesc(userName);
        alertVO.setVersionNo(alert.getVersionNo());
        alertVO.setDbRecord(true);

        if (isListNullorEmpty(alertDataBean.getRefAlertStatusList())
                || isListNullorEmpty(alertDataBean.getRefAlertStatusList()))
        {
            getReferenceData();
        }

        setDescForAlertValidValues(alertVO);

        alertVO.setAuditUserID(alert.getAuditUserID());
        alertVO.setAddedAuditUserID(alert.getAddedAuditUserID());
        alertVO.setAuditTimeStamp(alert.getAuditTimeStamp());
        alertVO.setAddedAuditTimeStamp(alert.getAddedAuditTimeStamp());
        alertVO.setVersionNo(alert.getVersionNo());
        createVOAuditKeysList(alert,alertVO);
        
        
        return alertVO;
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
    	       	
				//For fixing defect ESPRD00620746
    	       	List editableAlert = new ArrayList();
				editableAlert.add(createAuditableFeild("Due Date", "dueDate"));
				editableAlert.add(createAuditableFeild("Alert Type", "alertTypeCode"));
			    editableAlert.add(createAuditableFeild("Description", "description"));
				editableAlert.add(createAuditableFeild("Notify via Alert", "receivingUserID"));
				editableAlert.add(createAuditableFeild("Status", "alertStatusCode"));
				if(logger.isDebugEnabled())
				{
					logger.debug("======LineItemAuditsDelegate====resultList====Size======="+resultList.size());
				}
    	       	auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);

				AuditDataFilter.filterAuditKeys(editableAlert,auditaleEnterpriseBaseVO);
    	       	if(logger.isDebugEnabled())
    	       	{
    	       		logger.debug("======LineItemAuditsDelegate====resultList==after ==Size======="+auditaleEnterpriseBaseVO.getAuditKeyList().size());
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
//  For fixing defect ESPRD00620746
    private AuditableField createAuditableFeild(String feildName,String domainAttributeName){
		
		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);
			
		return auditableField;
    }
//  For fixing defect ESPRD00620746
    /**
     * @param workUnit
     *            The workUnit to set.
     * @return String
     */
    private String getNotifyToUserName(EnterpriseUser user)
    {
        
        String name = ContactManagementConstants.EMPTY_STRING;

        /*if (workUnit != null)
        {
            EnterpriseUser user = workUnit.getEnterpriseUser();
        */
            if (user != null)
            {
                name = user.getFirstName()
                        + ContactManagementConstants.SPACE_STRING
                        + user.getLastName();
            }
            // Added for Find_Bugs-FIX
            if (user != null && !isNullOrEmptyField(user.getLastName()))
	        {
	        	name = user.getLastName();
			}
            if(user != null && !isNullOrEmptyField(user.getLastName()) &&
            		!isNullOrEmptyField(user.getFirstName()))
            {
            	name = name 
                + ContactManagementConstants.COMMA_SEPARATOR
                + ContactManagementConstants.SPACE_STRING;
            }
	        if (user != null && !isNullOrEmptyField(user.getFirstName()))
	        {
	        	name = name + user.getFirstName();
	        }
        
        
        //}

        return name;
    }

    /* *//**
          * @param workUnit
          *            The workUnit to set.
          * @param workUnitTypeCode
          *            The workUnitTypeCode to set.
          * @return String
          */
    /*
     * private String getName(WorkUnit workUnit, String workUnitTypeCode) {
     * logger.info("getName"); String name =
     * ContactManagementConstants.EMPTY_STRING; if (workUnit != null &&
     * "U".equalsIgnoreCase(workUnitTypeCode)) { EnterpriseUser user =
     * workUnit.getEnterpriseUser(); name = user.getFirstName() +
     * ContactManagementConstants.SPACE_STRING + user.getLastName(); } if
     * (workUnit != null && "W".equalsIgnoreCase(workUnitTypeCode)) { Department
     * department = workUnit.getDepartment(); name = department.getName(); }
     * return name; }
     */

    /**
     * @param name
     *            The name to set.
     * @param user
     *            The user to set.
     */
    private void setUserName(String name, EnterpriseUser user)
    {
        
        String nameSeparator = ContactManagementConstants.SPACE_STRING;
        int indexOfSeparator = name.indexOf(nameSeparator);

        String firstName = name.substring(0, indexOfSeparator);
        String lastName = name.substring(indexOfSeparator + 1, name.length());

        user.setFirstName(firstName);
        user.setLastName(lastName);
    }

    /**
     * @param alertVO
     *            The alertVO to set.
     */
    public static void setUserNameFromList(AlertVO alertVO)
    {
        /*EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(AlertDOConvertor.class);*/
        AlertDOConvertor alertDOConvertor = new AlertDOConvertor();
        AlertDataBean alertDataBean = (AlertDataBean) alertDOConvertor.getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
        
        String notifyViaAlertDescVal = "";
        String notifyViaAlert = alertVO.getUserWorkUnitSK();
        List listOfUsers = alertDataBean.getListOfUsersToNotify();
        String tempNotifyViaAlertDesc = getDescriptionFromVV(notifyViaAlert,
                listOfUsers);
        if(tempNotifyViaAlertDesc != null)
        {
	        String[] notifyViaAlertDesc =tempNotifyViaAlertDesc.split("-");
	        for (int i = 0 ; i < notifyViaAlertDesc.length ; i++) 
	        {
	            notifyViaAlertDescVal = notifyViaAlertDesc[0];
	        }
        }
        alertVO.setUserWorkUnitDesc(notifyViaAlertDescVal);
    }

    /**
     * @param alertVO
     *            The alertVO to set.
     */
    public static void setDescForAlertValidValues(AlertVO alertVO)
    {
        /*EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(AlertDOConvertor.class);*/

       

        String alertType = alertVO.getAlertType();
        String alertStatus = alertVO.getStatus();
        AlertDOConvertor alertDOConvertor = new AlertDOConvertor();
        //AlertDataBean alertDataBean = getAlertDataBean();
        AlertDataBean alertDataBean = (AlertDataBean) alertDOConvertor.getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
        List listOfAlertTypes = alertDataBean.getRefAlertTypeList();
        List listOfAlertStatus = alertDataBean.getRefAlertStatusList();

        String alertTypeDesc = getDescriptionFromVV(alertType, listOfAlertTypes);
        String alertStatusDesc = getDescriptionFromVV(alertStatus,listOfAlertStatus);
        alertVO.setAlertTypeDesc(alertTypeDesc);
        alertVO.setStatusDesc(alertStatusDesc);
    }

    /**
     * This operation is used to get the reference data.
     */
    public  void getReferenceData()
    {
        /*EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(AlertDOConvertor.class);*/

        
        alertDataBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
        Long userSK = getUserSK(getLoggedInUserID());
        String businessUnitDesc = getBusinessUnitforUser(userSK);
        List referenceList = new ArrayList();
        Map referenceMap = null;
        List refAlertTypeList=null;

        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
        
        createInputCriterias(referenceList,businessUnitDesc);

        referenceDataSearch.setInputList(referenceList);

        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);

            referenceMap = referenceDataListVO.getResponseMap();    
            if(logger.isInfoEnabled())
            {
            	logger.info("businessUnitDesc="+businessUnitDesc);
            }
            if (referenceMap != null)
            {
            	if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_BCCP))
            	{
                 refAlertTypeList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_ALERT_TY_CD_BCCP);
            	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_DDU))
            	{
                 refAlertTypeList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_ALERT_TY_CD_DDU);
            	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_MCS))
            	{
                 refAlertTypeList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_ALERT_TY_CD_MCS);
            	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_Medicaid_Legal_Services))
            	{
                 refAlertTypeList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_ALERT_TY_CD_Medicaid_Legal_Services);
            	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_Provider_Relations))
            	{
                 refAlertTypeList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_ALERT_TY_CD_Provider_Relations);
            	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_ARS))
            	{
                 refAlertTypeList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_ALERT_TY_CD_ARS);
            	}else
            	{
            		refAlertTypeList = getReferenceList(referenceMap,
                            FunctionalAreaConstants.CONTACT_MGMT,
                            ReferenceServiceDataConstants.G_ALERT_TY_CD_All_Others);
            	}

                List refAlertStatusList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_ALERT_STAT_CD);

                alertDataBean.setRefAlertTypeList(refAlertTypeList);
                alertDataBean.setRefAlertStatusList(refAlertStatusList);
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
    }

    /**
     * This method is used to created the input criteria to get the reference
     * date.
     * 
     * @param referenceList :
     *            List of Reference Data.
     */
    private static void createInputCriterias(List referenceList,String businessUnitDesc)
    {  
       /* EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(AlertDOConvertor.class);*/

        
        InputCriteria ipCrtAlertType = new InputCriteria();
        if(businessUnitDesc!= null )
        {
	        if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_BCCP))
	    	{
		        ipCrtAlertType.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		        ipCrtAlertType
		                .setElementName(ReferenceServiceDataConstants.G_ALERT_TY_CD_BCCP);
	    	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_DDU))
	    	{
		        ipCrtAlertType.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		        ipCrtAlertType
		                .setElementName(ReferenceServiceDataConstants.G_ALERT_TY_CD_DDU);
	    	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_MCS))
	    	{
		        ipCrtAlertType.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		        ipCrtAlertType
		                .setElementName(ReferenceServiceDataConstants.G_ALERT_TY_CD_MCS);
	    	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_Medicaid_Legal_Services))
	    	{
		        ipCrtAlertType.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		        ipCrtAlertType
		                .setElementName(ReferenceServiceDataConstants.G_ALERT_TY_CD_Medicaid_Legal_Services);
	    	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_Provider_Relations))
	    	{
		        ipCrtAlertType.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		        ipCrtAlertType
		                .setElementName(ReferenceServiceDataConstants.G_ALERT_TY_CD_Provider_Relations);
	    	}else if(businessUnitDesc.equals(ContactManagementConstants.businessUnit_ARS))
	    	{
		        ipCrtAlertType.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		        ipCrtAlertType
		                .setElementName(ReferenceServiceDataConstants.G_ALERT_TY_CD_ARS);
	    	}else
	    	{
	    		ipCrtAlertType.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		        ipCrtAlertType
		                .setElementName(ReferenceServiceDataConstants.G_ALERT_TY_CD_All_Others);
	    	}
        }
        InputCriteria ipCrtAlertStatus = new InputCriteria();
        ipCrtAlertStatus
                .setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
        ipCrtAlertStatus
                .setElementName(ReferenceServiceDataConstants.G_ALERT_STAT_CD);

        referenceList.add(ipCrtAlertType);
        referenceList.add(ipCrtAlertStatus);
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
        /*EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(AlertDOConvertor.class);*/

       

        List referenceList = null;
        List tempRevenueTypeList = new ArrayList();
        int referenceListSize = 0;

        referenceList = (List) referenceMap.get(functionalAreaConstant
                + ProgramConstants.HASH_SEPARATOR + elementName);

        if (referenceList != null)

        {
            referenceListSize = referenceList.size();
            for (int i = 0; i < referenceListSize; i++)
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
     * This method is used to get the Alert Data Bean.
     * 
     * @return AlertDataBean : AlertDataBean object.
     */
   /* public static AlertDataBean getAlertDataBean()
    {
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(AlertDOConvertor.class);

        logger.info("getAlertDataBean");

        FacesContext fc = FacesContext.getCurrentInstance();
        return ((AlertDataBean) fc.getApplication().createValueBinding(
                ContactManagementConstants.BINDING_BEGIN_SEPARATOR
                        + AlertDataBean.BEAN_NAME
                        + ContactManagementConstants.BINDING_END_SEPARATOR)
                .getValue(fc));
    }*/

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
        /*EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(AlertDOConvertor.class);*/

        

        String desc = ProgramConstants.EMPTY_STRING;
        String valueStr = ProgramConstants.EMPTY_STRING;
        if (vvList != null && !vvList.isEmpty())
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
     * This method is used to get the User ID.
     * 
     * @return String : User ID
     */
    /*public String getUserID()
    {
        logger.info("getUserID");

        String userId = "USERID1";
        // FacesContext fc = FacesContext.getCurrentInstance();
        AlertControllerBean alertControllerBean = new AlertControllerBean();

        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = alertControllerBean.getUserData(
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

    /* *//**
          * This method is used to get the Current Timestamp.
          * 
          * @return Timestamp : Current Timestamp.
          */
    /*
     * private Timestamp getTimeStamp() { logger.info("getTimeStamp"); Calendar
     * cal = Calendar.getInstance(); return new
     * Timestamp(cal.getTimeInMillis()); }
     */
    /**
     * This method is used to check if the input field is null or is equal to an
     * empty string.
     * 
     * @param inputField :
     *            String inputField.
     * @return boolean : true if input field is null or equal to an empty string
     *         else false.
     */
    private static boolean isNullOrEmptyField(String inputField)
    {
        /*EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(AlertDOConvertor.class);*/
        

        return (inputField == null || ContactManagementConstants.EMPTY_STRING
                .equalsIgnoreCase(inputField.trim()));
    }

    /**
     * This method is used to check if the input list is null or empty.
     * 
     * @param list :
     *            List list.
     * @return boolean : true or false.
     */
    private boolean isListNullorEmpty(List list)
    {
        

        return (list == null || list.isEmpty());
    }
    /**
     * This method is used to get the UserSK given the userId.
     * 
     * @param userId :
     *            String User Id.
     * @return Long : UserSK.
     */
    private  Long getUserSK(String userId)
    {
        
       

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        Long userSK = null;

        try
        {
            userSK = contactMaintenanceDelegate.getWorkUnitID(userId);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
        	if(logger.isErrorEnabled())
        	{
        		logger.error(e.getMessage(), e);
        	}
        }
        
        return userSK;
    }

   
    public String getBusinessUnitforUser(Long userSK)
    {
              
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        String businessUnitDesc= null;
        DeptUserBasicInfo deptUserBasicInfo=new DeptUserBasicInfo();
        try
        {
            //List deptsList = contactMaintenanceDelegate.getDepartmentUsers(userSK);
        	// Code Added For CM-API
        	deptUserBasicInfo.setUserEnterpriseSK(userSK);
       	    deptUserBasicInfo.setDataReqested(ProgramConstants.DUBI_LOB_Hierarchy_SK);
         	List deptsList= contactMaintenanceDelegate.getDeptUserBasicInfo(deptUserBasicInfo).getLobSKlist();

            if (deptsList != null)
            {
            	// Find_Bugs-FIX
            	if(logger.isDebugEnabled())
            	{
            		logger.debug("Dept list size" + deptsList.size());
            	}
            	String businessCode = null;
              

                for (Iterator iter = deptsList.iterator(); iter.hasNext();)
                {
                  
                	// Code Added For CM-API
                	/* DepartmentUser deptUser = (DepartmentUser) iter.next();                 
                    String lobHierarchySK = deptUser.getDepartment()
                            .getLineOfBusinessHierarchy()
                            .getLineOfBusinessHierarchySK().toString();*/
                	Long lobSk= (Long)iter.next();
                	String lobHierarchySK=lobSk.toString();
                    
                    LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
                            .getDeptBusinessUnit(new Long(lobHierarchySK));
                    if (businessUnit != null)
                    {
                        businessCode = businessUnit
                                .getLineOfBusinessHierarchySK().toString();
                        
                        if (businessCode != null)
                        {
                            businessUnitDesc=businessUnit.getLobHierarchyDescription();
                            
                            break;
                        }
                    }                
                }
            }
        }
        catch (LOBHierarchyFetchBusinessException lobExp)
        {
        	if(logger.isErrorEnabled())
        	{
        		logger.error(lobExp.getMessage(), lobExp);
        	}
        }
        
        
        return businessUnitDesc;
     }

}
