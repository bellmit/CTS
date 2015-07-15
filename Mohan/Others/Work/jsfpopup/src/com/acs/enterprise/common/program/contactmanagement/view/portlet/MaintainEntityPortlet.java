/*
 * Created on Jul 10, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.ibm.faces20.portlet.FacesPortlet;
//import com.ibm.faces.webapp.FacesGenericPortlet;

//RAD7 Imports

/**
 * @author vijaymai TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class MaintainEntityPortlet
        extends FacesPortlet
{
    /**
     * Generating object of EnterpriseLogger.
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(getClass().getName());

    /**
     * This is constructor of MaintainEntityPortlet.
     */
    public MaintainEntityPortlet()
    {
        super();
        logger.debug("Inside the constructor - MaintainEntityPortlet");
    }

    /**
     * Calls Do Process
     * 
     * @param request
     *            Takes request as param
     * @param response
     *            Takes response as param
     * @throws PortletException
     *             Throws PortletException
     */

    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException
    {

        logger.debug("Inside process action  of maintain entity  class");
        // Reterive source action name

        String actionName = (String) request
                .getParameter("receive.MaintainEntity.Id");
        logger.debug("In MaintainEntityPortlet:actionName: " + actionName);

        if (actionName != null && actionName.equals("receiveMaintainEntityId"))
        {
            logger.debug("Inside  MaintainEntityPortlet:");

            String maintainEntityId = (String) request
                    .getAttribute("maintainEntityId");

            logger.debug("Printing maintainEntityId--------->"
                    + maintainEntityId);

            request.getPortletSession().setAttribute("maintainEntityId",
                    maintainEntityId);
            request.getPortletSession().setAttribute("pageFrm","SearchEntity");

            logger.debug("success");
        }

        /** Code for ipc from search Correspondence Entity to Mainain Entity */

        String actionFromPage = (String) request
                .getParameter("Receive.From.Page");
        logger.debug("In MaintainEntityPortlet:actionFromPage: "
                + actionFromPage);

        if (actionFromPage != null && actionFromPage.equals("receiveFromPage"))
        {
            logger.debug("Inside  MaintainEntityPortlet: ipc 2 ");

            String fromPage = (String) request.getAttribute("MaintainEntity");

            logger.debug("Printing fromPage name --------->" + fromPage);

            request.getPortletSession()
                    .setAttribute("MaintainEntity", fromPage);
            request.getPortletSession().setAttribute("pageFrm","crsEntitySearch");
            logger.debug("success1--------");
        }

        // Otherwise invoke default behaviour
        super.processAction(request, response);
        String logCR_PrimaryEntity = (String)request.getAttribute("correspondenceEntity");
		if (logCR_PrimaryEntity != null) {
			response.setEvent("MaintainEntityToCRLog_Primary", logCR_PrimaryEntity);
		}
		String logCR_InquiryEntity = (String)request.getAttribute("inquiryaboutEntityData");
		if (logCR_InquiryEntity != null) {
			response.setEvent("MaintainEntityToCRLog_Inquiry", logCR_InquiryEntity);
		}
		String mntEntitySearch = (String)request.getAttribute("SearchEntityPage");
		if (mntEntitySearch != null) {
			response.setEvent("MaintainEntityToEntitySearch", mntEntitySearch);
		}
		String mntEntityLogCase = (String)request.getAttribute("MaintainSearchId");
		if (mntEntityLogCase != null) {
			response.setEvent("MaintainEntityToInquiryLogCase", mntEntityLogCase);
		}
		String mntEntityInqLogCase = (String)request.getAttribute("MaintainInquirySearchId");
		if (mntEntityInqLogCase != null) {
			response.setEvent("MaintainEntityToLogCase", mntEntityInqLogCase);
		}
		String mntEntityCREntitySearch = (String)request.getAttribute("SearchCorrespondenceEntity");
		if (mntEntityCREntitySearch != null) {
			response.setEvent("MaintainEntityToCREntitySearch", mntEntityCREntitySearch);
		}
    }

    /**
     * Calls Do view
     * 
     * @param request
     *            Takes request as param
     * @param response
     *            Takes response as param
     * @throws PortletException
     *             Throws PortletException
     * @throws IOException
     *             Throws ioException
     */

    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {
        logger.debug("Inside do view of maintain entity portlet class");

        String maintainEntityId = (String) request.getPortletSession()
                .getAttribute("maintainEntityId");
        if (maintainEntityId != null)
        {
            logger.debug("Inside do view of Cancel");

            request.getPortletSession().removeAttribute("maintainEntityId");
            request.setAttribute("maintainEntityId", maintainEntityId);
        }

        /** Added for oher ipc from search correspondence portlet */
        String fromPageName = (String) request.getPortletSession()
                .getAttribute("MaintainEntity");
        if (fromPageName != null)
        {
            logger.debug("Inside do view of Cancel ---for fromPageName");

            request.getPortletSession().removeAttribute("MaintainEntity");
            request.setAttribute("MaintainEntity", fromPageName);
        }
        
        /*
    	 * Security related changes
    	 */
        String currentPage = (String) request.getParameter("javax.servlet.include.path_info");
        if ("/jsp/maintainentity/maintainEntity.jsp".equals(currentPage)) {
    		//Commented by ICS for I1 Smoke Test Issue
			//setUserPermission(request);
        }
        /*
    	 * end of Security related changes
    	 */
        
        super.doView(request, response);

    }
    
    /**
     * This method gets the permission level for the logged in user
     *
     */
    public void setUserPermission(RenderRequest request) {
    	String userid="";
		PortletSession psin = request.getPortletSession();
		userid = (String) psin.getAttribute("LOGGED_IN_USERID");
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		String userPermission = "";
		try {			
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(ContactManagementConstants.MAINTAIN_ENTITY_PAGE, userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";

		// set the permission level.  This will be used to grey out the page
		request.setAttribute("displayMode", userPermission);
	}
    public void actionUrl(EventRequest request, EventResponse response,
			String actionName) {
		/*String actionName = (String) request
				.getParameter("receive.MaintainEntity.Id");*/
		logger.debug("In MaintainEntityPortlet:actionName: " + actionName);

		if (actionName != null && actionName.equals("receiveMaintainEntityId")) {
			logger.debug("Inside  MaintainEntityPortlet:");

			String maintainEntityId = (String) request
					.getAttribute("maintainEntityId");

			logger.debug("Printing maintainEntityId--------->"
					+ maintainEntityId);

			request.getPortletSession().setAttribute("maintainEntityId",
					maintainEntityId);
			request.getPortletSession().setAttribute("pageFrm", "SearchEntity");

			logger.debug("success");
		}

		/** Code for ipc from search Correspondence Entity to Mainain Entity */

		/*String actionFromPage = (String) request
				.getParameter("Receive.From.Page");
		logger.debug("In MaintainEntityPortlet:actionFromPage: "
				+ actionFromPage);*/

		if (actionName != null && actionName.equals("receiveFromPage")) {
			logger.debug("Inside  MaintainEntityPortlet: ipc 2 ");

			String fromPage = (String) request.getAttribute("MaintainEntity");

			logger.debug("Printing fromPage name --------->" + fromPage);

			request.getPortletSession()
					.setAttribute("MaintainEntity", fromPage);
			request.getPortletSession().setAttribute("pageFrm",
					"crsEntitySearch");
			logger.debug("success1--------");
		}
}
	/**
					* Process an action request.
					* @see javax.portlet.Portlet#processEvent(javax.portlet.EventRequest, javax.portlet.EventResponse)
					*/
					public void processEvent(EventRequest request, 
							EventResponse response) throws 
							PortletException, java.io.IOException {
						String actionName= null;
						System.out.println(" Inside MaintainEntityPortlet ProcessEvent.....");
						Event inquiryEntitySearchEvent = request.getEvent();
						if(inquiryEntitySearchEvent.getName().toString().equals("MaintainEntityFromInqAbtEntitySearch")) {
							String inquiryEntitySearch = (String)inquiryEntitySearchEvent.getValue();
							System.out.println(" correspondence Search : "+inquiryEntitySearch);
							if(inquiryEntitySearch != null){
								request.setAttribute("MaintainEntity", inquiryEntitySearch);
							}
							actionName="receiveFromPage";
						}
						Event crEntitySearchEvent = request.getEvent();
						if(crEntitySearchEvent.getName().toString().equals("MaintainEntityFromCREntitySearch")) {
							String CREntitySearch = (String) crEntitySearchEvent.getValue();
							System.out.println(" correspondence Search : "+CREntitySearch);
							if(CREntitySearch != null){
								request.setAttribute("MaintainEntity", CREntitySearch);
								}
							actionName="receiveFromPage";
						}
						Event entitySrchEvent = request.getEvent();
						if(entitySrchEvent.getName().toString().equals("MaintainEntityFromEntitySearch")) {
							String mntEntitySrch = (String)entitySrchEvent.getValue();
							if(mntEntitySrch != null){
								//request.getPortletSession().setAttribute("maintainEntityId", mntEntitySrch);
								request.setAttribute("maintainEntityId",mntEntitySrch);
							}
							actionName="receiveMaintainEntityId";
						}
						Event  CaseEntitySrchEvent= request.getEvent();
						if(CaseEntitySrchEvent.getName().toString().equals("MaintainEntityFromCaseEntitySearch")) {
							String CaseEntitySearch = (String)CaseEntitySrchEvent.getValue();
							if(CaseEntitySearch != null){
								request.setAttribute("MaintainEntity", CaseEntitySearch);
							}
							actionName="receiveFromPage";
						}
						Event InqCaseEntitySrchEvent = request.getEvent();
						if(InqCaseEntitySrchEvent.getName().toString().equals("MaintainEntityFromInqAbtCaseEntitySearch")) {
								String InqCaseEntitySrch = (String)InqCaseEntitySrchEvent.getValue();
								if(InqCaseEntitySrch != null){
									request.setAttribute("MaintainEntity", InqCaseEntitySrch);
								}
								actionName="receiveFromPage";
						}
						Event TPLHIPPSrchEvent = request.getEvent();
						if(TPLHIPPSrchEvent.getName().toString().equals("MaintainEntityFromTPLHIPP")) {
							String TPLHIPPSrch = (String)TPLHIPPSrchEvent.getValue();
							if(TPLHIPPSrch != null){
								request.getPortletSession().setAttribute("MaintainEntity", TPLHIPPSrch);
							}
							actionName="receiveFromPage";
						}
						Event maintainEntityFromSearchCountyBilling = request.getEvent();
						if(maintainEntityFromSearchCountyBilling.getName().toString().equals("ContactMaintainEntityFromSearchCountyBilling")) {
							String contactMaintainEntityFromSearchCountyBilling= (String)maintainEntityFromSearchCountyBilling.getValue();
							if(contactMaintainEntityFromSearchCountyBilling!=null){
								request.setAttribute("maintainEntityId", contactMaintainEntityFromSearchCountyBilling);
							}
							actionName="receiveMaintainEntityId";
						}
			Event tplRecoveryToContactMaintainEntity = request.getEvent();
			if(tplRecoveryToContactMaintainEntity.getName().toString().equals("FromTPLRecoveryToContactMaintainEntity")) {
				String tplRecoveryToContactmaintainentity = (String)tplRecoveryToContactMaintainEntity.getValue();
				if(tplRecoveryToContactmaintainentity!=null)
				{
				request.setAttribute("maintainEntityId", tplRecoveryToContactmaintainentity);
				}
				actionName="receiveMaintainEntityId";
				
			}
			actionUrl(request, response, actionName);
	
	}
    
//  RAD7 Code
}
