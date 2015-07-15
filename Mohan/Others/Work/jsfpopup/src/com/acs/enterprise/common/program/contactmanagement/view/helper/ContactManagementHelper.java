/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpSession;

import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.domain.SystemList;
import com.acs.enterprise.common.program.administration.common.domain.SystemListDetail;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
//import com.acs.enterprise.common.program.contactmanagement.view.bean.CMLogCaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseDetailsVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.util.config.ConfigurationLoader;
import com.acs.enterprise.common.util.helper.DateUtility;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.ibm.faces.util.StringUtil;

/**
 * This class contains all the helper methods used in ContactManagementPortlet
 * project
 * 
 * @author wipro
 */
public class ContactManagementHelper
{

    /** Creates an instance of the logger. * */
	static final EnterpriseLogger log = EnterpriseLogFactory.getLogger(ContactManagementHelper.class.getName());

	 /** Holds common message to log at begin of every method. */
	// Moved to ContactManagementConstants.java
    //protected static final String BEGINMETHOD = "Begin of the ContactManagementHelper";
	// Moved to ContactManagementConstants.java
	//protected static final String ENDMETHOD = "End of the ContactManagementHelper";

    /** Holds the value for Repesentative VO */
    private static HashMap caseDetailMap = new HashMap();

    
	// Moved to ContactManagementConstants.java
    // public static final String CMLOGCASE_DATA_BEAN = "logCaseDataBean";
    
    //private CMLogCaseDataBean logCaseDataBean;
	

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
     * This method displays error messages in the jsp pages.
     * 
     * @param message
     *            String.
     */

    public static void setErrorMessage(String message)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext
                .getApplication()
                .setMessageBundle(
                        "com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactManagementMessages");
        ResourceBundle bundle = resourceBundle(facesContext);
        String errorMsg = bundle.getString(message);
        FacesMessage fm = new FacesMessage(errorMsg);
        facesContext.addMessage(null, fm);
    }

    /**
     * This Method is used to Map to Resource Bundle.
     * 
     * @param facesContext -
     *            FacesContext.
     * @return ResourceBundle after mapping
     */
    public static ResourceBundle resourceBundle(FacesContext facesContext)
    {
        UIViewRoot root = facesContext.getViewRoot();
        String messageBundle = facesContext.getApplication().getMessageBundle();
        Locale locale = root.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);

        return bundle;
    }

    /**
     * This method sets error messages to the context to display in the jsp
     * pages.
     * 
     * @param message
     *            String :It is the error message to be displayed.
     * @param addID
     *            String : It is the add component ID
     * @param editID
     *            String : It is the edit component ID
     * @param action
     *            String : It is "action" add or "update"
     */
    public static void setErrorMessage(String message, String addID,
            String editID, String action)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + " setErrorMessage");
    	}
        UIComponent fieldName = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().setMessageBundle(
                MaintainContactManagementUIConstants.MAINTAIN_CM_UI_MESSAGES);
        ResourceBundle bundle = resourceBundle(facesContext);
        String errorMsg = bundle.getString(message);
        FacesMessage fm = new FacesMessage(errorMsg);
      
        if (action == null || "Add".equalsIgnoreCase(action))
        {
            
            if (addID != null)
            {
                fieldName = (UIComponent) ContactManagementHelper
                        .findComponentInRoot(addID);
            }
        }
        else if ("Update".equalsIgnoreCase(action))
        {           
            if (editID != null)
            {
                fieldName = (UIComponent) ContactManagementHelper
                        .findComponentInRoot(editID);
            }
        }
        if (fieldName != null)
        {
            facesContext.addMessage(fieldName.getClientId(facesContext), fm);
        }
        else
        {
            facesContext.addMessage(null, fm);
        }
    }
    
    /**
     * This method sets error messages to the context to display in the jsp
     * pages with respect Dynamic Table Fields's Validation
     * 
     * @param message
     *            String :It is the error message to be displayed.
     * @param addID
     *            String : It is the add component ID
     * @param editID
     *            String : It is the edit component ID
     * @param action
     *            String : It is "action" add or "update"
     */
    public static void setErrorMessage1(String message, String addID,
            String editID, String action)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + " setErrorMessage");
    	}
        UIComponent fieldName = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().setMessageBundle(
                MaintainContactManagementUIConstants.MAINTAIN_CM_UI_MESSAGES);

        String errorMsg = message;
        FacesMessage fm = new FacesMessage(errorMsg);
 
        if (action == null || "Add".equalsIgnoreCase(action))
        {
            
            if (addID != null)
            {
                fieldName = (UIComponent) ContactManagementHelper
                        .findComponentInRoot(addID);
            }
        }
        else if ("Update".equalsIgnoreCase(action))
        {           
            if (editID != null)
            {
                fieldName = (UIComponent) ContactManagementHelper
                        .findComponentInRoot(editID);
            }
        }
        if("bccpID".equals(addID))
        {
        	fieldName = (UIComponent) ContactManagementHelper
            .findComponentInRoot(addID);
        }
        if (fieldName != null)
        {
            facesContext.addMessage(fieldName.getClientId(facesContext), fm);
        }
        else
        {
            facesContext.addMessage(null, fm);
        }
    }
    /**
     * Method used find the component in root by passing id.
     * 
     * @param id -
     *            String object.
     * @return UIComponent object.
     */

    public static UIComponent findComponentInRoot(String id)
    {
        UIComponent ret = null;
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null)
        {
            UIComponent root = context.getViewRoot();
            ret = findComponent(root, id);
        }
        return ret;
    }

    /**
     * @param base -
     *            View root component of the jsp.
     * @param id -
     *            Id of the component from jsp.
     * @return UIComponent object.
     */

    public static UIComponent findComponent(UIComponent base, String id)
    {

        if (id.equals(base.getId()))
        {
            return base;
        }
 

        UIComponent kid = null;
        UIComponent result = null;

        Iterator kids = base.getFacetsAndChildren();
        while (kids.hasNext() && (result == null))
        {
            kid = (UIComponent) kids.next();
            if (id.equals(kid.getId()))
            {
                result = kid;
                break;
            }
            result = findComponent(kid, id);
            if (result != null)
            {
                break;
            }
        }
        return result;
    }

    /**
     * This method returns open ended date where the value is '31 Dec 9999'
     * 
     * @return openEndedDate
     */
    public static Date getOpenEndedDate()
    {
        Date openEndedDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(9999, 11, 31);
        openEndedDate = calendar.getTime();
        return openEndedDate;
    }

    /**
     * This method returns open begin date where the value is '01 Jan 0001'
     * 
     * @return openEndedDate
     */
    public static Date getOpenBeginDate()
    {
        Date openEndedDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(0001, 0, 1);
        openEndedDate = calendar.getTime();
        return openEndedDate;
    }

    /**
     * This method is used to convert String object to Date object
     * 
     * @param strdate
     *            String String This contains the Date.
     * @return Date
     */
    public static Date dateConverter(String strdate)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + " dateConverter");
    	}
        Date date = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        if (strdate != null)
        {
            if (EnterpriseCommonValidator.validateDate(strdate))
            {
                String valDt = getValidDateFormat(strdate);
                try
                {
                    date = new Date(sdf1.parse(valDt).getTime());
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                date = null;
            }
        }
        return date;
    }

    /**
     * This method returns the valid date format MM/DD/YY
     * 
     * @param inputDate
     *            String
     * @return String
     */
    public static String getValidDateFormat(String inputDate)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + " getValidDateFormat");
    	}
        String outputDate = inputDate;

        if (!(outputDate.indexOf('/') > 0))
        {
            if (outputDate.indexOf('-') > 0)
            {
                inputDate = inputDate.replace('-', '/');
            }
            else
            {
                inputDate = inputDate.substring(0,
                        MaintainContactManagementUIConstants.TWO)
                        + "/"
                        + inputDate.substring(
                                MaintainContactManagementUIConstants.TWO,
                                MaintainContactManagementUIConstants.FOUR)
                        + "/"
                        + inputDate.substring(
                                MaintainContactManagementUIConstants.FOUR,
                                MaintainContactManagementUIConstants.EIGHT);
            }
        }
        return inputDate;
    }

    /**
     * This method is used to convert Date object to String object
     * 
     * @param date
     *            Date This contains the date
     * @return String
     */
    public static String dateConverter(Date date)
    {
        Format formatter;
        formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }

    /**
     * This method is to get the short description for the given code from
     * Reference Service Delegate.
     * 
     * @param functionalArea
     *            Holds Functional area code
     * @param elementName
     *            Holds Domain name
     * @param value
     *            Holds valid value code
     * @return String short Descriptino of valid value code
     */
    public static String setShortDescription(String functionalArea,
            String elementName, String value)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "setShortDescription");
    	}
    	String validvalueDesc = null;
        try
        {
            validvalueDesc = ReferenceServiceDelegate
                    .getReferenceSearchShortDescription(functionalArea,
                            elementName, null, value);
        }
        catch (Throwable throwable)
        {  
        	if(log.isErrorEnabled())
        	{
        		log.error(throwable.getMessage(), throwable);
        	}
        }
        if (validvalueDesc == null || validvalueDesc.equalsIgnoreCase(""))
        {
            validvalueDesc = value;
        }
        return validvalueDesc;
    }

    /**
     * This method is to get the short description for the given code from
     * Valid Value Delegate.
     * 
     * @param functionalArea
     *            Holds Functional area code
     * @param elementName
     *            Holds Domain name
     * @param value
     *            Holds valid value code
     * @return String short Descriptino of valid value code
     */
   /* public static String setLongDescription(String functionalArea,
			String elementName, String value) {
    	log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "setLongDescription");
    	String validvalueDesc = null;
		ValidValueDomain validDomainName = null;
		try {
			
			ValidValueDelegate validDelegate = new ValidValueDelegate();
			validDomainName = validDelegate.getValidValueDetails(elementName);
			if (validDomainName != null) {
				Set assosiatedValidValues = validDomainName
						.getAssociatedValidValues();
				Iterator iterator = assosiatedValidValues.iterator();
				while (iterator.hasNext()) {
					ValidValue validValue = (ValidValue) iterator.next();
					if (validValue.getValidValueCode().equalsIgnoreCase(value)) {
						
						validvalueDesc = validValue.getLongDescription();
						break;
					}
				}
			}
		} catch (Throwable t) {
		}
		if (validvalueDesc == null || validvalueDesc.equalsIgnoreCase("")) {
			validvalueDesc = value;
		}
		log.info(ENDMETHOD + "setLongDescription");
		return validvalueDesc;
	}*/
    
    /**
     * This method validates the alphabetic data.
     * 
     * @param expression :
     *            Any pure alpha data.
     * @return boolean : true if the expression matches the pattern a-z or A-Z.
     */
    public static boolean validateAlpha(String expression)
    {
        Pattern p = Pattern.compile(ContactManagementConstants.ALPHA_PATTERN);
        Matcher m = p.matcher(expression);
        return m.matches();
    }

    /**
     * This method validates numeric data
     * 
     * @param expression
     * @return
     */
    public static boolean validateNumeric(String expression)
    {
        Pattern p = Pattern
                .compile(MaintainContactManagementUIConstants.NUMERIC_PATTERN);
        Matcher m = p.matcher(expression);
        return m.matches();
    }

    /**
     * This method validates the alphabetic data.
     * 
     * @param expression :
     *            Any pure alpha data.
     * @return boolean : true if the expression matches the pattern a-z or A-Z.
     */
    public static boolean validateAlphaNumeric(String expression)
    {
        Pattern p = Pattern
                .compile(ContactManagementConstants.ALPHANUMERIC_PATTERN);
        Matcher m = p.matcher(expression);
        return m.matches();
    }

    //Commented for Heap dump fix defect ESPRD00935080
    /**
     * it will rweturn the user name for the given user SK
     * 
     * @param userSK
     * @return user name
     */
   /* public String getUserName(String userSK)
    {
        String userName = null;
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        Map userMap = logCaseDataBean.getUserMap();
        if (!userMap.isEmpty())
        {
        	if(log.isDebugEnabled())
        	{
        		log.debug("UserSK from controller bean is $$ " + userSK);
        	}
            userName = (String) userMap.get(userSK);
            if(log.isDebugEnabled())
            {
            	log.debug("User Name in helper is $$ " + userName);
            }
        }
        return userName;
    }*/

    //Commented for Heap dump fix defect ESPRD00935080
    /**
     * it will rweturn the user name for the given user SK
     * 
     * @param userSK
     * @return user name
     */
  public String getUserNameByID(String userID)
    {
        String userName = null;
        //logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        //Map userMap = logCaseDataBean.getUserIDsMap();
        Map userIDMap = new HashMap();
        List userData = null;
		String firstName = null;
		String lastName = null;
		String name = null;
        CMDelegate cMDelegate = new CMDelegate();
        try {
			userData =cMDelegate.getUserDetails();// caseDelegate.getAllUsers();
			if (!userData.isEmpty()) {
				int size = 0;

				size = userData.size();
				

				StringBuffer sbName = null;
				for (int i = 0; i < size; i++) {
					Object[] userDetails = (Object[]) userData.get(i);
					firstName = userDetails[1].toString();
					lastName = userDetails[0].toString();
						
						sbName = new StringBuffer();
						if (lastName != null
								&& !MaintainContactManagementUIConstants.EMPTY_STRING
										.equals(lastName)) {
							sbName
									.append(lastName)
									.append(",")
									.append(
											MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
						}
						sbName
								.append(firstName)
								.append(
										MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
								.append(
										MaintainContactManagementUIConstants.HYPHEN);
						sbName
								.append(
										MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
								.append(userDetails[2].toString());
						name = sbName.toString();
					
						userIDMap.put(userDetails[2].toString(), name);
						
				
						}
			}



		} catch (Exception e) {
			log.error("Exception occured at getUsersList()" + e);
		}
        if (!userIDMap.isEmpty())
        {
        	if(log.isDebugEnabled())
        	{
        	 log.debug("UserID from controller bean is $$ " + userID);
        	}
            userName = (String) userIDMap.get(userID);
            if(log.isDebugEnabled())
            {
            log.debug("User Name in helper is $$ " + userName);
            }
            userIDMap=null;
        }
        return userName;
    }

    /**
     * it will rweturn the user ID for the given user WU SK
     * 
     * @param wuSK
     * @return user ID
     */
    //Commented for Heap dump fix defect ESPRD00935080
   /*public String getUserIDByWUSK(String wuSK)
    {
        String userID = null;
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        Map userIDMap = logCaseDataBean.getUserIDAndWUMap();
        if (!userIDMap.isEmpty())
        {
        	if(log.isDebugEnabled())
        	{
        		log.debug("WorkUnitSK from controller bean is $$ " + wuSK);
        	}
            userID = (String) userIDMap.get(wuSK);
            if(log.isDebugEnabled())
            {
            	log.debug("User ID in helper is $$ " + userID);
            }
        }
        return userID;
    }*/

   /**
    * it will rweturn the user ID for the given user WU SK
    * 
    * @param wuSK
    * @return user ID
    */
  public String getUserIDByWUSK2(String wuSK)
   {
       String userID = null;
       //logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
       //Map userIDMap = logCaseDataBean.getUserIDAndWUMap();
       if (wuSK!=null)
       {
       	if(log.isDebugEnabled())
       	{
       		log.debug("WorkUnitSK from controller bean is $$ " + wuSK);
       	}
       	CaseDelegate caseDelegate = new CaseDelegate();
           try {
				userID = caseDelegate.getUserId(wuSK);
			} catch (EnterpriseBaseBusinessException e) {
				e.printStackTrace();
			}
           if(log.isDebugEnabled())
           {
           	log.debug("User ID in helper is $$ " + userID);
           }
       }
       return userID;
   }
    /**
     * This method sets your error messages to the context to display in the jsp
     * pages without using ResourceBundle.
     * 
     * @param message :
     *            error message to display in JSP.
     */
    public static void setMessage(String message)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(message);
        facesContext.addMessage(null, fm);
    }

    /**
     * This method will check for the value in the SystemList.
     * 
     * @param value
     *            String value to be checked
     * @param systemList
     *            List SystemList
     * @return booean: true when value is present, else return false
     */
    public boolean validateSystemList(String value, List systemList)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "validateSystemList");
    	}
    	ReferenceServiceVO referenceServiceVO = null;
    	
        if (systemList.size() > 0)
        {
            for (int i = 0; i < systemList.size(); i++)
            {
                referenceServiceVO = (ReferenceServiceVO) systemList.get(i);
                
                if (value != null
                        && value.equals(referenceServiceVO.getValidValueCode()))
                {
                    return true;
                }
            }
        }
        else
        {
            return true;
        }
        return false;
    }

    /**
     * @param element
     *            Takes short description as one param
     * @param list
     *            Takes the list of systemlist as param
     * @return boolean
     */
    public boolean chkCaseTypeShortDesc(String element, List list)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "chkCaseTypeShortDesc");
    	}
    	SystemList systemList = null;
        Set systemListDetailSet = null;
        boolean flag = false;
        if (!list.isEmpty())
        {
            for (int i = 0; i < list.size(); i++)
            {
                systemList = (SystemList) list.get(i);
                if (systemList != null)
                {
                    systemListDetailSet = systemList.getSystemListDetails();
                   
                    flag = chkElementExistsInSystemList(element,
                            systemListDetailSet);
                    if (flag)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method compares if the short esc exsist in the set .
     * 
     * @param element
     *            takes the short desc as parm
     * @param systemDets
     *            Takes the set of systemdets as param
     * @return boolean
     */
    public boolean chkElementExistsInSystemList(String element, Set systemDets)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "chkElementExistsInSystemList");
    	}
        SystemListDetail listDetail = null;

        if (!systemDets.isEmpty())
        {
            Iterator iterator = systemDets.iterator();
            while (iterator.hasNext())
            {
                listDetail = (SystemListDetail) iterator.next();
               
                if (element != null
                        && element.equals(listDetail.getStartingValue()))
                {
                    
                    return true;
                }
            }
        }
        if(log.isInfoEnabled())
        {
        	log.info(ContactManagementConstants.ENDMETHOD_ContactManagementHelper + "chkElementExistsInSystemList");
        }
        return false;
    }

    /**
     * This method gets the Days of Holidays from systemList.s
     * 
     * @param createdDate
     *            Takes createdDate as one param
     * @param closingDate
     *            Takes the closingDate as param
     * @param noOfdays
     *            Takes noOfdays as param
     * @param list
     *            Takes list of system list
     * @return boolean
     */
    public int getClosingDaysAfterExclHolidays(Date createdDate,
            Date closingDate, int noOfdays, List list)
    {
    	if(log.isInfoEnabled())
    	{
    	log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "getClosingDaysAfterExclHolidays");
    	}
        SystemList systemList = null;
        Set systemListDetailSet = null;
        int daysExclHolidys = 0;
        if (!list.isEmpty())
        {
            for (int i = 0; i < list.size(); i++)
            {
                systemList = (SystemList) list.get(i);
                if (systemList != null)
                {
                    systemListDetailSet = systemList.getSystemListDetails();
                   
                    daysExclHolidys = chkifAnyHolidayThere(createdDate,
                            closingDate, noOfdays, systemListDetailSet);

                }
            }
        }
        if(log.isInfoEnabled())
        {
        	log.info(ContactManagementConstants.ENDMETHOD_ContactManagementHelper + "getClosingDaysAfterExclHolidays");
        }
        return daysExclHolidys;
    }

    /**
     * This method compares if the short esc exsist in the set .
     * 
     * @param createdDate
     *            Takes createdDate as one param
     * @param closingDate
     *            Takes the closingDate as param
     * @param noOfdays
     *            Takes noOfdays as param
     * @param systemDets
     *            Takes set of systemListDetailSet
     * @return boolean
     */
    private int chkifAnyHolidayThere(Date createdDate, Date closingDate,
            int noOfDays, Set systemDets)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "chkifAnyHolidayThere");
    	}
    	
        SystemListDetail listDetail = null;
        DateFormat dateFormat = new SimpleDateFormat(
                ContactManagementConstants.DATE_FORMAT);
        if (!systemDets.isEmpty())
        {
            Iterator iterator = systemDets.iterator();
            while (iterator.hasNext())
            {
                listDetail = (SystemListDetail) iterator.next();
          
                try
                {
                    Date curHolidaydate = dateFormat.parse(listDetail
                            .getStartingValue());
                    
                    if (((curHolidaydate.after(createdDate) || curHolidaydate
                            .equals(createdDate)) && (curHolidaydate
                            .before(closingDate) || curHolidaydate
                            .equals(closingDate))))
                    {
                       
                        noOfDays--;
                        

                    }

                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
            
        }
       
        /** Check for sat n sun */
        if (noOfDays > 1)
        {
            
            Calendar calendarBegin = new GregorianCalendar();
            Calendar calendarEnd = new GregorianCalendar();

            calendarBegin.setTime(createdDate);
            calendarEnd.setTime(closingDate);           

            while (calendarBegin.before(calendarEnd))
            {
                calendarBegin.add(Calendar.DATE, 1);
                //commented for unused variables
                //Date incDate = calendarBegin.getTime();
                
                int fieldValFoeWeekEnd = calendarBegin
                        .get(Calendar.DAY_OF_WEEK);

                if (fieldValFoeWeekEnd == MaintainContactManagementUIConstants.ONE
                        || fieldValFoeWeekEnd == MaintainContactManagementUIConstants.SEVEN)
                {
                    
                    noOfDays--;
                }
                
            }
        }
        if(log.isInfoEnabled())
        {
        	log.info(ContactManagementConstants.ENDMETHOD_ContactManagementHelper + "chkifAnyHolidayThere");
        }
        return noOfDays;
    }

    /**
     * This method gets the formatted routingdate .
     * 
     * @param routingVO
     *            Takes routingVO as param
     * @param date
     *            Takes routingVO as date
     */
    public void getFormatedRoutingDate(CMRoutingVO routingVO, Date date)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "getFormatedRoutingDate");
    	}
        try
        {
            Format formatter;
            formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            String strDate = formatter.format(date);
           
            Date modifiedDate = (Date) formatter.parseObject(strDate);
            
            routingVO.setRoutingDateStr(strDate);
            routingVO.setRoutingDate(modifiedDate);
            
        }
        catch (ParseException e)
        {
        	if(log.isErrorEnabled())
        	{
        		log.error("Error occured while parsing Date object", e);
        	}
        }
        if(log.isInfoEnabled())
        {
        	log.info(ContactManagementConstants.ENDMETHOD_ContactManagementHelper + "getFormatedRoutingDate");
        }
    }

     
       public void getFormatedAttachmentDate(AttachmentVO attachmentVO, Date date) {      
           Format formatter;
           formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
           String strDate = formatter.format(date);
          
           attachmentVO.setDateAdded(strDate);

       }
       

    /**
     * This method gives the differnec in days.
     * 
     * @param createdDate
     *            takes createdDate
     * @return String
     */
    public String getDaysDefference(Date createdDate)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "getDaysDefference");
    	}
    	if(log.isDebugEnabled())
    	{
    		log.debug("Your given date is $$ " + createdDate);
    	}
        Date sysDate = new Date();
        String strDays = null;
        long days = 0;
        DateUtility dateUtility = new DateUtility();
        days = dateUtility.getDaysDifference(sysDate, createdDate);
        
        if (days != 0)
        {
            strDays = Long.toString(days);
        }
        else if (days == 0)
        {
            strDays = Long.toString(days);
        }
        return strDays;
    }

    /**
     * @return Returns the caseDetailVO.
     */
    public static CaseDetailsVO getCaseDetailMap(String userid)
    {
        CaseDetailsVO detailsVO = null;
        if (caseDetailMap.containsKey(userid))
        {
            detailsVO = (CaseDetailsVO) caseDetailMap.get(userid);
        }
        return detailsVO;
    }

    /**
     * @param caseDetailMap
     *            The caseDetailMap to set.
     */
    public static void setCaseDetailMap(String userid, CaseDetailsVO detailsVO)
    {
        caseDetailMap.put(userid, detailsVO);
    }



  /**
     * This method is used to get new SSN number.
     * 
     * @param ssn
     *            String
     * @return new PhoneNumber String
     */
    public static String getPhoneFormat(String phoneNumber)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "getPhoneFormat");
    	}
        String newPhoneNumber = null;
        newPhoneNumber = phoneNumber.substring(0,
                MaintainContactManagementUIConstants.THREE);
        newPhoneNumber = newPhoneNumber
                .concat(MaintainContactManagementUIConstants.HYPHEN);
        newPhoneNumber = newPhoneNumber.concat(phoneNumber.substring(
                MaintainContactManagementUIConstants.THREE,
                MaintainContactManagementUIConstants.SIX));
        newPhoneNumber = newPhoneNumber
                .concat(MaintainContactManagementUIConstants.HYPHEN);
        newPhoneNumber = newPhoneNumber.concat(phoneNumber.substring(
                MaintainContactManagementUIConstants.SIX,
                MaintainContactManagementUIConstants.TEN));
        if(log.isInfoEnabled())
        {
        	log.info(ContactManagementConstants.ENDMETHOD_ContactManagementHelper + "getPhoneFormat");
        }
        return newPhoneNumber;
    }

    /**
     * gets SystemList Numbers for Correspondence Business Units based on user
     * login
     */
    public static Long getSystemlistForCorrBU(String constantField,
            String bussUnit)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "getSystemlistForCorrBU");
    	}
        String buKey = constantField + "_" + bussUnit;

        Properties buProperties = ConfigurationLoader.getInstance()
                .getSystemListNumbersForBusinessUnitsProperties();
        Long propVal = null;
        if (buProperties.getProperty(buKey) != null)
        {
            propVal = Long.valueOf(buProperties.getProperty(buKey));
        }
        else
        {
            buKey = constantField + ContactManagementConstants.CORR_ALLOTHERS; 
            propVal = Long.valueOf(buProperties.getProperty(buKey));
        }

        return propVal;
    }

    /**
     * gets SystemList Numbers for Correspondence Entity based on user selected
     * entity
     */
    public static Long getSystemlistForEntyIdType(String entyIdType,
            String entyType)
    {
        String entyIDKey = entyIdType + "_" + entyType;
        
        Properties entyIDProperties = ConfigurationLoader.getInstance()
                .getSystemListNumberProperties();
        Long propVal = null;
        if (entyIDProperties.getProperty(entyIDKey) != null)
        {
            propVal = Long.valueOf(entyIDProperties.getProperty(entyIDKey));
        }

        return propVal;
    }
    public void sortCorrespondenceAttachmentsComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
    	if(log.isInfoEnabled())
    	{
    		log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + "sortCorrespondenceAttachmentsComparator");
    	}
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                AttachmentVO data1 = (AttachmentVO) obj1;
                AttachmentVO data2 = (AttachmentVO) obj2;

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
                if (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_DATEADDED
                        .equals(sortColumn)){
                	// Modification for defect ESPRD00748270 - Start 
                	Date dateadded3=ContactManagementHelper.getOpenEndedDate();
				    Date dateadded4=ContactManagementHelper.getOpenEndedDate();
				   
                
                	DateFormat dateFormat = new SimpleDateFormat(
                    "MM/dd/yyyy hh:mm a");
                	 
					try {
						if (null == data1.getDateAdded()) {
							data1
									.setDateAdded(MaintainContactManagementUIConstants.EMPTY_STRING);
						} else {
							dateadded3 = dateFormat.parse(data1.getDateAdded());
						}
						if (null == data2.getDateAdded()) {
							data2
									.setDateAdded(MaintainContactManagementUIConstants.EMPTY_STRING);
						} else {
							dateadded4 = dateFormat.parse(data2.getDateAdded());
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ascending ? dateadded3.compareTo(
							dateadded4) : dateadded4
							.compareTo(dateadded3);
					// Modification for defect ESPRD00748270 - End 		
				}
                if (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_ADDEDBY
                        .equals(sortColumn))
                {
                    if (null == data1.getAddedBy())
                    {
                        data1
                                .setAddedBy(MaintainContactManagementUIConstants.EMPTY_STRING);
                    }
                    if (null == data2.getAddedBy())
                    {
                        data2
                                .setAddedBy(MaintainContactManagementUIConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getAddedBy().trim().compareTo(
                            data2.getAddedBy().trim()) : data2.getAddedBy()
                            .trim().compareTo(data1.getAddedBy().trim());
                }
                if (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_FILENAME
                        .equals(sortColumn))
                {
                    if (null == data1.getFileName())
                    {
                        data1
                                .setFileName(MaintainContactManagementUIConstants.EMPTY_STRING);
                    }
                    if (null == data2.getFileName())
                    {
                        data2
                                .setFileName(MaintainContactManagementUIConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getFileName().trim().compareTo(
                            data2.getFileName().trim()) : data2.getFileName()
                            .trim().compareTo(data1.getFileName().trim());
                }
                if (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_DESC
                        .equals(sortColumn))
                {
                    if (null == data1.getDescription())
                    {
                        data1
                                .setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
                    }
                    if (null == data2.getDescription())
                    {
                        data2
                                .setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getDescription().trim().compareTo(
                            data2.getDescription().trim()) : data2
                            .getDescription().trim().compareTo(
                                    data1.getDescription().trim());
                }
                return 0;
            }
        };
        Collections.sort(dataList, comparator);
    }
    
    public static String getKey(String key, PortletSession session)
    {

        if (session != null)
        {
           
        }
       
        return getKey(key, session.getId());

    }

    public static String getKey(String key, HttpSession session)
    {

       
        if (session != null)
        {
           
        }
       
        return getKey(key, session.getId());
    }

    public static String getKey(String key, String sessionId)
    {
        return getKey(key, sessionId, null);
    }

    public static String getKey(String key, String sessionId, String windowId)
    {
        StringBuffer _key = new StringBuffer(key.length() + 50);
        _key.append(sessionId);
        _key.append('.');
        if (windowId != null)
        {
            _key.append(windowId);
            _key.append('.');
        }
        _key.append(key);
        return _key.toString();
    }
    
    /**
     * @param time :
     *            Verifies time is in valid format or not.
     * @return boolean : returns true if time is valid else false.
     */
    public boolean isvalidTimeFormat(String time)
    {
        Pattern p = Pattern.compile(MaintainContactManagementUIConstants.TIME_PATTERN);
        Matcher m = p.matcher(time);
        boolean valid = m.matches();

        if (!valid)
        {
            return valid;
        }
        return valid;
    }
    
    /**
     * This method is used to convert Date object to String object which contain time
     * 
     * @param date
     *            Date This contains the date
     * @return String
     */
    public static String timeConverter(Date date)
    {
        Format formatter;
        formatter = new SimpleDateFormat("hh:mm");
        String strDate = formatter.format(date);
        
        return strDate;
    }
    
    /**
	 * To get the description based on code.
	 * 
	 * @param code
	 *            Holds the code valus.
	 * @param vvList
	 *            Holds the List of valid values.
	 * @return String
	 */
	public String getDescriptionFromVV(String code, List vvList) {
		String desc = "";
		String valueStr = "";
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
		return desc;
	}
	
	 /**
     * To get the description based on code.
     * 
     * @param code
     *            Holds the code valus.
     * @param vvList
     *            Holds the List of valid values.
     * @return String
     */
    public String getDescription(String code, List vvList)
    {
        String desc = ProgramConstants.EMPTY_STRING;
        String valueStr = ProgramConstants.EMPTY_STRING;
        int size = 0;
        //Added if Condition for Find_Bugs-FIX
        if(vvList != null){
        size = vvList.size();
        }
        if (vvList != null && size > 0)
        {
            for (int i = 0; i < size; i++)
            {
                SelectItem selectItem = (SelectItem) vvList.get(i);
                valueStr = ProgramConstants.EMPTY_STRING;
                if (selectItem != null)
                {
                    valueStr = (String) selectItem.getValue();
                    if (valueStr != null && valueStr.equals(code))
                    {
                    	int index = selectItem.getLabel().indexOf("-");
                    	String descriptionWithCode = selectItem.getLabel();
                        desc = descriptionWithCode.substring(index+2);
                        break;
                    }
                }
            }
        }
        return desc;
    }
    
    /**
     * This method is used to get the UserSK given the userId.
     *
     * @param userId :
     *            String User Id.
     * @return Long : UserSK.
     */
  /*  public static Long getUserSK(String userId)
    {
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        Long userSK = null;
        try
        {
            userSK = contactMaintenanceDelegate.getWorkUnitID(userId);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            log.error(e.getMessage(), e);
        }
        
        return userSK;
    }*/
    
//  CR_ESPRD00373565_MaintainCaseTypes_16JUL2010
   
    /** adding for audit */
	 public AuditaleEnterpriseBaseVO createVOAuditKeysList(EnterpriseBaseDomain enterpriseBaseDomain,
  		AuditaleEnterpriseBaseVO  auditaleEnterpriseBaseVO){    
		 
  	List fullAuditList = new ArrayList();   
  	LineItemAuditsDelegate auditDelegate;
  	try {
  		auditDelegate = new LineItemAuditsDelegate();
  		if(auditaleEnterpriseBaseVO.getAuditKeyList()!= null)
	    	{
	    	 	fullAuditList= auditaleEnterpriseBaseVO.getAuditKeyList();
	    	}       	
	    	List resultList = auditDelegate.getAuditKeyList(enterpriseBaseDomain);
	    	//Added if Condition for Find_Bugs-FIX
	    	if(resultList!=null)
          {
        	 
        	  fullAuditList.addAll(resultList);    	      	
  	        
  	       	auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);
  	    }
  	} catch (LineItemAuditsBusinessException e) {
  		e.printStackTrace();
  	}
  	catch (Exception e) {
  		e.printStackTrace();
  	}
  	return auditaleEnterpriseBaseVO;
  }
  /** end */
   
	
  
	
    //EOf CR_ESPRD00373565_MaintainCaseTypes_16JUL2010

	 public static boolean validateAlphaNumericSpl(String expression)
	 {
    	
        Pattern p = Pattern
                .compile(ContactManagementConstants.ALPHANUMERICANDSPL_PATTERN);
        Matcher m = p.matcher(expression);
       
        return m.matches();
	 }
	 
	 /**
	  * 
	  * @return
	  */
	 public List getAllActiveUsersAndDepts()
	 {
	 	List getAllActiveUsersAndDepts = new ArrayList();
		List reassignAllList = new ArrayList();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try
		{
			getAllActiveUsersAndDepts = contactMaintenanceDelegate.getAllActiveUsersAndDepts();
		    
		}
		catch (EnterpriseBaseBusinessException e)
		{
			if(log.isErrorEnabled())
			{
				log.error("error while retreving deptUsers=="+e);
			}
		}
		if(getAllActiveUsersAndDepts != null && getAllActiveUsersAndDepts.size() > 0)
		{
			for (Iterator iter = getAllActiveUsersAndDepts.iterator(); iter.hasNext();)
			{
				Object[] userData = (Object[]) iter.next();
				String userDesc = ContactManagementConstants.EMPTY_STRING;
				String userID = null;
				if(userData[2] != null)
				{
					userDesc = userData[2].toString();
				}
				if((userData[2] != null) && (userData[1] != null))
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
					if(userDesc != null && !StringUtil.isEmpty(userDesc))
					{
						userDesc = userDesc + ContactManagementConstants.HYPHEN_SEPARATOR + userID;
					}else{
						userDesc = userID;
					}
				}
				if(userDesc!=null && userDesc.length()>0 && userData[0] != null)
				{
					SelectItem selectItem = new SelectItem(userData[0].toString().trim(),userDesc);
					reassignAllList.add(selectItem);
				}
			}
		}
		sortDeptsAndUsersList(reassignAllList);
		return reassignAllList;
	 }
	 
	 /**
	     *Modified during the CR ESPRD00798895 as Case Filter needs to be applied for the Active users
		 * to the Reassign All drop down of Reassign Case page
	     * This method returns Active Users with Case Filter and Active Department
	     * 
	     * @return reassignAllList
	     */
	 public List getAllActiveUsersAndDeptsCase()
	 {
	 	List getAllActiveUsersAndDepts = new ArrayList();
		List reassignAllList = new ArrayList();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try
		{
			getAllActiveUsersAndDepts = contactMaintenanceDelegate.getAllActiveUsersAndDeptsCase();
		    
		}
		catch (EnterpriseBaseBusinessException e)
		{
			if(log.isErrorEnabled())
			{
				log.error("error while retreving deptUsers=="+e);
			}
		}
		if(getAllActiveUsersAndDepts != null && getAllActiveUsersAndDepts.size() > 0)
		{
			for (Iterator iter = getAllActiveUsersAndDepts.iterator(); iter.hasNext();)
			{
				Object[] userData = (Object[]) iter.next();
				String userDesc = ContactManagementConstants.EMPTY_STRING;
				String userID = null;
				if(userData[2] != null)
				{
					userDesc = userData[2].toString();
				}
				if((userData[2] != null) && (userData[1] != null))
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
					if(userDesc != null && !StringUtil.isEmpty(userDesc))
					{
						userDesc = userDesc + ContactManagementConstants.HYPHEN_SEPARATOR + userID;
					}else{
						userDesc = userID;
					}
				}
				if(userDesc!=null && userDesc.length()>0 && userData[0] != null)
				{
					SelectItem selectItem = new SelectItem(userData[0].toString().trim(),userDesc);
					reassignAllList.add(selectItem);
				}
			}
		}
		sortDeptsAndUsersList(reassignAllList);
		return reassignAllList;
	 }
	 
	 /***
	  * 
	  * @return active departments
	  */
	 public List getAllActiveDepts()
	 {
	 	List getAllActiveAndDepts = new ArrayList();
		List activeDepts = new ArrayList();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try
		{
			getAllActiveAndDepts = contactMaintenanceDelegate.getAllActiveDepts();
			
			
		}
		catch (EnterpriseBaseBusinessException e)
		{
			if(log.isErrorEnabled())
			{
				log.error("error while retreving deptUsers=="+e);
			}
		}
		if(getAllActiveAndDepts != null && getAllActiveAndDepts.size() > 0)
		{
			if(log.isInfoEnabled())
			{
				log.info("getAllActiveAndDepts.size()--"+getAllActiveAndDepts.size());
			}
			try{
			for (Iterator iter = getAllActiveAndDepts.iterator(); iter.hasNext();)
			{
				Object[] userData = (Object[]) iter.next();
				String userDesc = ContactManagementConstants.EMPTY_STRING;
				//commented for unused variables
				//String userID = null;
				if(userData[1] != null)
				{
					userDesc = userDesc + userData[1];
					
				}
				if(userDesc!=null && userDesc.length()>0 && userData[0] != null)
				{
					SelectItem selectItem = new SelectItem(userData[0].toString().trim(),userDesc);
					activeDepts.add(selectItem);
				}
			}}catch(Exception e){
				e.printStackTrace();
			}
		}
		sortDeptsAndUsersList(activeDepts);
		return activeDepts;
	 }
	 
	 /**
	  * 
	  * @param deptUsersList
	  */
	 private void sortDeptsAndUsersList(List deptUsersList) 
	 {
		Comparator selectItemComparator = new Comparator() 
		{
			public int compare(Object obj1, Object obj2) 
			{
				String first = null;
				String second = null;

				SelectItem s1 = (SelectItem) obj1;
				SelectItem s2 = (SelectItem) obj2;

				//commented for unused variables
				//boolean ascending = true;

				if (s1.getLabel() != null && s2.getLabel() != null) 
				{
					first = s1.getLabel().toLowerCase();
					second = s2.getLabel().toLowerCase();

					return first.compareTo(second);
				}
				return 0;
			}

		};
		Collections.sort(deptUsersList, selectItemComparator);
	 }
	 
	//Defect Fix:  ESPRD00675155
	 public List sortDate(List dateList) 
	    {
		 
			Comparator selectItemComparator = new Comparator() 
			{
				public int compare(Object obj1, Object obj2) 
				{
					Date first = null;
					Date second = null;

					SelectItem s1 = (SelectItem) obj1;
					SelectItem s2 = (SelectItem) obj2;
					
					//commented for unused variables
					//boolean ascending = true;
					if (s1.getLabel() != null && !s1.getLabel().equals("") && s2.getLabel() != null && !s2.getLabel().equals("")) 
					{
						first = dateConverterWithoutValidation(s1.getLabel());
						second = dateConverterWithoutValidation(s2.getLabel());
						if(first!=null && second!=null)
							return first.compareTo(second);
					}
					return 0;
					
				}

			};
			
			Collections.sort(dateList, selectItemComparator);
			return dateList;
		}
	 /**
	  * 
	  * If date is coming from system list date validation not required
	  * @return
	  */
	//Defect Fix:  ESPRD00675155
	 public static Date dateConverterWithoutValidation(String strdate)
	    {
		 if(log.isInfoEnabled())
		 {
	    	log.info(ContactManagementConstants.BEGINMETHOD_ContactManagementHelper + " dateConverterWithoutValidation");
		 }
	        Date date = null;
	        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
	        
	        if (strdate != null)
	        {
	        	String valDt = getValidDateFormat(strdate);
                try
                {
                    date = new Date(sdf1.parse(valDt).getTime());
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
	        }
	        return date;
	    }
	 
	 // Begin - Added the mthod for the PanelGrid Fix
	 
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
		public static void setErrorMessageForCF(String errorName, String id) {

			FacesContext facesContext = FacesContext.getCurrentInstance();
			UIComponent fieldName = null;
			UIViewRoot root = facesContext.getViewRoot();
			Locale locale = root.getLocale();			
	        facesContext.getApplication().setMessageBundle(
	        		MessageUtil.ENTMESSAGES_BUNDLE);
	        String messageBundle = facesContext.getApplication().getMessageBundle();
	        ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);
	        String errorMsg = bundle.getString(errorName);
	        fieldName = (UIComponent) ContactManagementHelper
            .findComponentInRoot(id);
			FacesMessage fc = new FacesMessage();
			fc.setDetail(errorMsg);		
			facesContext.addMessage(fieldName.getClientId(facesContext), fc);
		}
		 // End - Added the mthod for the PanelGrid Fix	
		
	/**
	 * This method is used to Hit the RPM to check the quick links have the
	 * permissions to display or not for the user,for defect ESPRD00873448
	 * */
		public Map getPagePermissions(String userId){
			
			FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
			Map linksMap = null;		
			ArrayList<String> linksList2Pass = new ArrayList<String>();
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_EDMS);
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_CORRES_GEN);
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_SA);
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_PRV_APPEALS);
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_MEM_APPEALS);
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_CASE_TRACKING);
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_CLAIMS_INQ);
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_BENEFIT_PLAN);
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_EVENT_TRACK);
			linksList2Pass.add(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_FIN_ENTITY);
			
			try {
				linksMap =  fieldAccessControlImpl.getActionLinkPermission(linksList2Pass,userId);
			} catch (SecurityFLSServiceException e) {
				e.getCause();
			}
			return linksMap;
	    }
		//Defect ESPRD00873448 Ends
}	