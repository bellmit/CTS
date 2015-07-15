/*
 * Created on Jul 10, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.cots.edms.application.common.vo.EnterpriseEDMSSearchCriteriaVO;
import com.acs.enterprise.common.cots.edms.application.common.vo.EnterpriseEDMSSearchResultsVO;
import com.acs.enterprise.common.cots.lettergeneration.vo.CommonLetterInputVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceRecordVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.WireEventDataCarrierVO;
import com.ibm.faces20.portlet.FacesPortlet;
//import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
//import com.acs.enterprise.common.view.vo.WireEventDataCarrierVO;
//import com.ibm.faces.webapp.FacesGenericPortlet;

/**
 * @author wipro
 */
public class LogCorrespondencePortlet
        extends FacesPortlet
{

    /** Enterprise Logger for Logging. */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(LogCorrespondencePortlet.class);

    /**
     * @param request
     *            The request to set. *
     * @param response
     *            The response to set.
     * @throws PortletException
     *             it will throw PortletException.
     */
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException
    {

        // Reterive source action name
        String actionName = (String) request
                .getParameter("receive.Correspondence.Entity");
        logger.debug("In LogCorrespondence:actionName: " + actionName);

        if (actionName != null)
        {

            if (actionName.equals("receiveCorrespondenceEntity"))
            {
                logger.debug("Inside  MaintainEntityPortlet:");

                String correspondenceEntity = (String) request
                        .getAttribute("correspondenceEntity");

                logger.debug("Printing correspondenceEntity--------->"
                        + correspondenceEntity);

                request.getPortletSession().setAttribute(
                        "correspondenceEntity", correspondenceEntity);

                logger.debug("success");
            }
        }

        /*String actionNameInquiry = (String) request
                .getParameter("receive.inquiryabout.EntityData");
        logger.debug("In LogCorrespondence:actionNameInquiry: "
                + actionNameInquiry);*/

        if (actionName != null)
        {

            if (actionName.equals("receiveinquiryaboutEntityData"))
            {
                logger.debug("Inside  InquiryAboutPortlet:");

                String inquiryaboutEntityData = (String) request
                        .getAttribute("inquiryaboutEntityData");

                logger.debug("Printing inquiryaboutEntityData--------->"
                        + inquiryaboutEntityData);

                request.getPortletSession().setAttribute(
                        "inquiryaboutEntityData", inquiryaboutEntityData);

                logger.debug("success");
            }
        }
        /*String actionNameCorrespondenceSK = (String) request
                .getParameter("receive.Correspondence.Sk");
        logger.debug("In LogCorrespondence:actionNameInquiry: "
                + actionNameCorrespondenceSK);*/

        if (actionName != null)
        {

            if (actionName.equals("receiveCorrespondenceSk"))
            {
                logger.debug("Inside LogCorrespondence::");

                String correspondenceSk = (String) request
                        .getAttribute("correspondenceSk");

                logger.debug("Printing correspondenceSk--------->"
                        + correspondenceSk);

                request.getPortletSession().setAttribute("correspondenceSk",
                        correspondenceSk);

                logger.debug("success");
            }
        }

        /** Added for My Task */
      /*  String actionNameMyTaskCorrespondenceSK = (String) request
                .getParameter("receive.mytask.correspondence.sk");
        logger.debug("In LogCorrespondence:actionNameMyTaskCorrespondenceSK: "
                        + actionNameMyTaskCorrespondenceSK);
*/
        if (actionName != null)
        {

            if (actionName
                    .equals("receiveMyTaskCorrespondenceSk"))
            {
                logger.debug("Inside LogCorrespondence::MyTask");

                String mytaskcorrespondenceSk = (String) request
                        .getAttribute("mytaskcorrespondenceSk");

                logger.debug("Printing mytaskcorrespondenceSk--------->"
                        + mytaskcorrespondenceSk);

                request.getPortletSession().setAttribute(
                        "mytaskcorrespondenceSk", mytaskcorrespondenceSk);

                logger.debug("success --Mytask --log Correspondance");
            }
        }

       /* String actionNameClaimccSk = (String) request
                .getParameter("receiveMyTask.ClaimCorrespondence.Sk");
        logger.debug("Action Name from actionNameClaimccSk === "
                + actionNameClaimccSk);*/

        if (actionName != null)
        {
            if ("receiveMyTaskClaimCorrespondenceSk"
                    .equals(actionName))
            {
                logger.debug("actionNameClaimccSk -----"
                        + request.getAttribute("myTaskClaimCorrespondenceSk"));

                request.getPortletSession().setAttribute(
                        "myTaskClaimCorrespondenceSk",
                        request.getAttribute("myTaskClaimCorrespondenceSk"));
            }
        }

        /** Added for Attachments */

       /* String actionName1 = (String) request
                .getParameter("receive.EDMSSearchResults.Action");
        logger.debug("Action Name from EDMS Search === " + actionName1);*/

        if (actionName != null)
        {
            if ("receiveEDMSSearchResultsAction".equals(actionName))
            {
                logger.debug("logCREDMSSearchResultsList -----"
                        + request.getAttribute("logCREDMSSearchResultsList"));

                request.getPortletSession().setAttribute(
                        "logCREDMSSearchResultsList",
                        request.getAttribute("logCREDMSSearchResultsList"));
            }
        }

        /** Added for letter Generation */

       /* String actionNameLetterGenerationSK = (String) request
                .getParameter("receive.letterGeneration.Sk");
        logger.debug("In LogCorrespondence:actionNameLetterGenerationSK: "
                        + actionNameLetterGenerationSK);*/

        if (actionName != null)
        {

            if (actionName
                    .equals("receiveLetterGenerationSk"))
            {
                logger.debug("Inside LogCorrespondence::Leter Generation ");

                String letterGenerationSk = (String) request
                        .getAttribute("LetterGenerationSKCreated");

                logger.debug("Printing letterGenerationSk--------->"
                        + letterGenerationSk);

                request.getPortletSession().setAttribute(
                        "LetterGenerationSKCreated", letterGenerationSk);

                logger.debug("success --letter Generation --log Correspondance");
            }
        }
        /**Added for AssociateCase&Correspondence*/
   /*     String actionNameCaseData = (String) request
                .getParameter("receive.CaseData.Action");
        logger.debug("In LogCorrespondence:actionNameCaseData: "
                + actionNameCaseData);*/
        if (actionName != null)
        {
            if (actionName.equals("receiveCaseDataAction"))
            {
                logger.debug("Inside LogCorrespondence::AssociateCase ");

                String caseData = (String) request
                        .getAttribute("AssociateCaseData");

                logger
                        .debug("Printing letterGenerationSk--------->"
                                + caseData);

                request.getPortletSession().setAttribute("AssociateCaseData",
                        caseData);

                logger.debug("success --AssociateCase --log Correspondance");
            }
        }

        /** Added for Letter Request SK */
	//	actionName = request.getParameter(ContactManagementConstants.SEND_LTR_REQ_SK);
		logger.info("----- actionName --------" + actionName);
		if (actionName != null && actionName.equalsIgnoreCase(
				ContactManagementConstants.LTR_REQ_SK_ACTION_NAME)) {
			String ltrReqSK = null;
			PortletSession session = request.getPortletSession(false);
			if (session != null) {
				ltrReqSK = (String)session.getAttribute(ContactManagementConstants.LTR_REQ_SK_PARAM);
				session.removeAttribute(ContactManagementConstants.LTR_REQ_SK_PARAM,
						PortletSession.PORTLET_SCOPE);
			}

			logger.info("ltrReqSK in processAction = " + ltrReqSK);
			if (ltrReqSK != null) {
				request.setAttribute(ContactManagementConstants.LTR_REQ_SK, ltrReqSK);
			}
		}
		
		//String entSrchActionName = request.getParameter("SrcEntLogCrs_ACTION");
		logger
				.debug("LogCorrespondencePortlet :: processAction action Name in Target Portlet "
						+ actionName);
		
		if (actionName != null
				&& actionName.equals("LogCrsSrchEntity_TARGET_ACTION")) {
		
			Map dataMap = (Map) request.getAttribute("srcEntLogMap");		
			logger
					.debug("LogCorrespondencePortlet :: processAction Receiving Map object "
							+ dataMap);
		
			if (dataMap != null) {												
				request.getPortletSession().setAttribute("myMap",
                        dataMap);
			}
		}

        // Otherwise invoke default behaviour
        super.processAction(request, response);
		EnterpriseEDMSSearchCriteriaVO enterpriseEDMSSearchCriteriaVO = 
			(EnterpriseEDMSSearchCriteriaVO) request.getAttribute("EDMSSearchCriteria");
		if (enterpriseEDMSSearchCriteriaVO != null) {
			response.setEvent("LogCorrespondenceToDocRepository", enterpriseEDMSSearchCriteriaVO);
    }
		CommonLetterInputVO commonLetterInputVO = 
			(CommonLetterInputVO) request.getAttribute("CommonLetterInput");
		if (commonLetterInputVO != null) {
			response.setEvent("LogCorrespondenceToLetGeneration_VO", commonLetterInputVO);
		}
		String letterRequestSK = 
			(String) request.getAttribute("letterRequestSK");
		if (letterRequestSK != null) {
			response.setEvent("LogCorrespondenceToLetGeneration_String", letterRequestSK);
		}
		CorrespondenceRecordVO correspondenceRecordVO = 
			(CorrespondenceRecordVO) request.getAttribute("CorrespondenceResultsList");
		if (correspondenceRecordVO != null) {
			response.setEvent("LogCorrespondenceToLogCase", correspondenceRecordVO);
		}
	
	//WSRP Changes
		CorrespondenceSearchCriteriaVO  corresSearchCritVO= (CorrespondenceSearchCriteriaVO)request.getAttribute("CrsSearchCrtVO");
		if (corresSearchCritVO != null){
			response.setEvent("LogCRToEntitySearch", corresSearchCritVO);
		}
	}

    /**
     * @param request
     *            The request to set. *
     * @param response
     *            The response to set.
     * @throws PortletException
     *             it will throw PortletException.
     * @throws IOException
     *             it will throw IOException.
     */
    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {

        String correspondenceEntity = (String) request.getPortletSession()
                .getAttribute("correspondenceEntity");

        if (correspondenceEntity != null)
        {
            logger.debug("Inside do view of Cancel");

            request.setAttribute("correspondenceEntity", correspondenceEntity);

            request.getPortletSession().removeAttribute("correspondenceEntity");
        }

        String inquiryaboutEntityData = (String) request.getPortletSession()
                .getAttribute("inquiryaboutEntityData");

        if (inquiryaboutEntityData != null)
        {
            logger.debug("Inside do view of Cancel");

            request.setAttribute("inquiryaboutEntityData",
                    inquiryaboutEntityData);

            request.getPortletSession().removeAttribute(
                    "inquiryaboutEntityData");
        }
        String correspondenceSk = (String) request.getPortletSession()
                .getAttribute("correspondenceSk");
        if (correspondenceSk != null)
        {
            logger.debug("Inside do view of Cancel");

            request.setAttribute("correspondenceSk", correspondenceSk);

            request.getPortletSession().removeAttribute("correspondenceSk");
        }

        /** Added for My Task */
        String mytaskcorrespondenceSk = (String) request.getPortletSession()
                .getAttribute("mytaskcorrespondenceSk");
        if (mytaskcorrespondenceSk != null)
        {
            logger.debug("Inside do view of Cancel");

            request.setAttribute("mytaskcorrespondenceSk",
                    mytaskcorrespondenceSk);

            request.getPortletSession().removeAttribute(
                    "mytaskcorrespondenceSk");
        }

        String myTaskClaimCorrespondenceSk = (String) request
                .getPortletSession()
                .getAttribute("myTaskClaimCorrespondenceSk");
        logger.debug("Inside log portlet -----cliam crspd  sk --------"
                + myTaskClaimCorrespondenceSk);
        if (myTaskClaimCorrespondenceSk != null)
        {
            logger.debug("Inside do view of Cancel---claim");

            request.setAttribute("myTaskClaimCorrespondenceSk",
                    myTaskClaimCorrespondenceSk);

            request.getPortletSession().removeAttribute(
                    "myTaskClaimCorrespondenceSk");
        }

        /** Added for Attachemnt */

        Object edmsSearchResultsObj = request.getPortletSession().getAttribute(
                "logCREDMSSearchResultsList");
        logger.debug("In Do view of Attachment");
        if (edmsSearchResultsObj != null)
        {
            logger.debug("Inside do view --- edmsSearchResultsObj not null");
            request.setAttribute("logCREDMSSearchResultsList",
                    edmsSearchResultsObj);
            request.getPortletSession().removeAttribute(
                    "logCREDMSSearchResultsList");
        }

        /** Added for letter Generation */
        String letterGSk = (String) request.getPortletSession().getAttribute(
                "LetterGenerationSKCreated");
        if (letterGSk != null)
        {
            logger.debug("Inside do view of Cancel --- letter generation ");

            request.setAttribute("LetterGenerationSKCreated", letterGSk);

            request.getPortletSession().removeAttribute(
                    "LetterGenerationSKCreated");
        }

        /**Added for AssociateCase*/
        String caseData = (String) request.getPortletSession().getAttribute(
                "AssociateCaseData");
        if (caseData != null)
        {
            logger.debug("Inside do view of AssocaiteCase- LogCorrespondence ");

            request.setAttribute("AssociateCaseData", caseData);

            request.getPortletSession().removeAttribute("AssociateCaseData");
        }
        
        Map myMap = (Map) request.getPortletSession().getAttribute(
		        "myMap");
		if (myMap != null)
		{
		    logger.debug("Inside do view of map- LogCorrespondence ");
		
		    request.setAttribute("myMap", myMap);
		
		    request.getPortletSession().removeAttribute("myMap");
		}

        /*
    	 * Security related changes
    	 */
        String currentPage = (String) request.getParameter("javax.servlet.include.path_info");
        if ("/jsp/logcorrespondence/logCorrespondence.jsp".equals(currentPage)) {
    		setUserPermission(request);
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
    	String userPermission = "";
		PortletSession psin = request.getPortletSession();

		//FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();

		try {
			userid = (String) psin.getAttribute("LOGGED_IN_USERID");
			userPermission = (String) psin.getAttribute("LOG_C_PERM");
			//userPermission = fieldAccessControlImpl.getFiledAccessPermission(ContactManagementConstants.LOG_CORRESPONDENCE_PAGE, userid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";

		// set the permission level.  This will be used to grey out the page
		request.setAttribute("displayMode", userPermission);
	}
    public void actionUrl(EventRequest request, EventResponse response,
			String actionName) {
/*
 String actionName = (String) request
                .getParameter("receive.Correspondence.Entity");*/
        logger.debug("In LogCorrespondence:actionName: " + actionName);

        if (actionName != null)
        {

            if (actionName.equals("receiveCorrespondenceEntity"))
            {
                logger.debug("Inside  MaintainEntityPortlet:");

                String correspondenceEntity = (String) request
                        .getAttribute("correspondenceEntity");

                logger.debug("Printing correspondenceEntity--------->"
                        + correspondenceEntity);

                request.getPortletSession().setAttribute(
                        "correspondenceEntity", correspondenceEntity);

                logger.debug("success");
            }
        }

       /* actionName = (String) request
                .getParameter("receive.inquiryabout.EntityData");
        logger.debug("In LogCorrespondence:actionNameInquiry: "
                + actionName);*/

        if (actionName != null)
        {

            if (actionName.equals("receiveinquiryaboutEntityData"))
            {
                logger.debug("Inside  InquiryAboutPortlet:");

                String inquiryaboutEntityData = (String) request
                        .getAttribute("inquiryaboutEntityData");

                logger.debug("Printing inquiryaboutEntityData--------->"
                        + inquiryaboutEntityData);

                request.getPortletSession().setAttribute(
                        "inquiryaboutEntityData", inquiryaboutEntityData);

                logger.debug("success");
            }
        }
      /*   actionName = (String) request
                .getParameter("receive.Correspondence.Sk");
        logger.debug("In LogCorrespondence:actionNameInquiry: "
                + actionName);*/

        if (actionName != null)
        {

            if (actionName.equals("receiveCorrespondenceSk"))
            {
                logger.debug("Inside LogCorrespondence::");

                String correspondenceSk = (String) request
                        .getAttribute("correspondenceSk");

                logger.debug("Printing correspondenceSk--------->"
                        + correspondenceSk);

                request.getPortletSession().setAttribute("correspondenceSk",
                        correspondenceSk);

                logger.debug("success");
            }
        }

        /** Added for My Task */
      /*  String actionNameMyTaskCorrespondenceSK = (String) request
                .getParameter("receive.mytask.correspondence.sk");
        logger.debug("In LogCorrespondence:actionNameMyTaskCorrespondenceSK: "
                        + actionNameMyTaskCorrespondenceSK);*/

        if (actionName != null)
        {

            if (actionName
                    .equals("receiveMyTaskCorrespondenceSk"))
            {
                logger.debug("Inside LogCorrespondence::MyTask");

                String mytaskcorrespondenceSk = (String) request
                        .getAttribute("mytaskcorrespondenceSk");

                logger.debug("Printing mytaskcorrespondenceSk--------->"
                        + mytaskcorrespondenceSk);

                request.getPortletSession().setAttribute(
                        "mytaskcorrespondenceSk", mytaskcorrespondenceSk);

                logger.debug("success --Mytask --log Correspondance");
            }
        }

       /* String actionNameClaimccSk = (String) request
                .getParameter("receiveMyTask.ClaimCorrespondence.Sk");
        logger.debug("Action Name from actionNameClaimccSk === "
                + actionNameClaimccSk);*/

        if (actionName != null)
        {
            if ("receiveMyTaskClaimCorrespondenceSk"
                    .equals(actionName))
            {
                logger.debug("actionNameClaimccSk -----"
                        + request.getAttribute("myTaskClaimCorrespondenceSk"));

                request.getPortletSession().setAttribute(
                        "myTaskClaimCorrespondenceSk",
                        request.getAttribute("myTaskClaimCorrespondenceSk"));
            }
        }

        /** Added for Attachments */

    /*    String actionName1 = (String) request
                .getParameter("receive.EDMSSearchResults.Action");
        logger.debug("Action Name from EDMS Search === " + actionName1);*/

        if (actionName != null)
        {
            if ("receiveEDMSSearchResultsAction".equals(actionName))
            {
                logger.debug("logCREDMSSearchResultsList -----"
                        + request.getAttribute("logCREDMSSearchResultsList"));

                request.getPortletSession().setAttribute(
                        "logCREDMSSearchResultsList",
                        request.getAttribute("logCREDMSSearchResultsList"));
            }
        }

        /** Added for letter Generation */
/*
        String actionNameLetterGenerationSK = (String) request
                .getParameter("receive.letterGeneration.Sk");
        logger.debug("In LogCorrespondence:actionNameLetterGenerationSK: "
                        + actionNameLetterGenerationSK);*/

        if (actionName != null)
        {

            if (actionName
                    .equals("receiveLetterGenerationSk"))
            {
                logger.debug("Inside LogCorrespondence::Leter Generation ");

                String letterGenerationSk = (String) request
                        .getAttribute("LetterGenerationSKCreated");

                logger.debug("Printing letterGenerationSk--------->"
                        + letterGenerationSk);

                request.getPortletSession().setAttribute(
                        "LetterGenerationSKCreated", letterGenerationSk);

                logger.debug("success --letter Generation --log Correspondance");
            }
        }
        /**Added for AssociateCase&Correspondence*/
        /*String actionNameCaseData = (String) request
                .getParameter("receive.CaseData.Action");
        logger.debug("In LogCorrespondence:actionNameCaseData: "
                + actionNameCaseData);*/
        if (actionName != null)
        {
            if (actionName.equals("receiveCaseDataAction"))
            {
                logger.debug("Inside LogCorrespondence::AssociateCase ");

                String caseData = (String) request
                        .getAttribute("AssociateCaseData");

                logger
                        .debug("Printing letterGenerationSk--------->"
                                + caseData);

                request.getPortletSession().setAttribute("AssociateCaseData",
                        caseData);

                logger.debug("success --AssociateCase --log Correspondance");
            }
        }

        /** Added for Letter Request SK */
	//	actionName = request.getParameter(ContactManagementConstants.SEND_LTR_REQ_SK);
		logger.info("----- actionName --------" + actionName);
		if (actionName != null && actionName.equalsIgnoreCase(
				ContactManagementConstants.LTR_REQ_SK_ACTION_NAME)) {
			String ltrReqSK = null;
			PortletSession session = request.getPortletSession(false);
			if (session != null) {
				ltrReqSK = (String)session.getAttribute(ContactManagementConstants.LTR_REQ_SK_PARAM);
				session.removeAttribute(ContactManagementConstants.LTR_REQ_SK_PARAM,
						PortletSession.PORTLET_SCOPE);
			}

			logger.info("ltrReqSK in processAction = " + ltrReqSK);
			if (ltrReqSK != null) {
				request.setAttribute(ContactManagementConstants.LTR_REQ_SK, ltrReqSK);
			}
		}
		
		//Modified the existing code for wiring issue between Entity Search to Log Correspondence....verifying for actionName instead for entSrchActionName  
		
		//String entSrchActionName = request.getParameter("SrcEntLogCrs_ACTION");
		/*logger
				.debug("LogCorrespondencePortlet :: processAction action Name in Target Portlet "
						+ entSrchActionName);*/
		
		if (actionName != null
				&& actionName.equals("LogCrsSrchEntity_TARGET_ACTION")) {
		
			Map dataMap = (Map) request.getAttribute("srcEntLogMap");		
			logger
					.debug("LogCorrespondencePortlet :: processAction Receiving Map object "
							+ dataMap);
		
			if (dataMap != null) {												
				request.getPortletSession().setAttribute("myMap",
                        dataMap);
			}
		}
		}

	public void processEvent(EventRequest request, EventResponse response)
				throws PortletException, IOException {
			String actionName =null;
			Event event = request.getEvent();
			if (event.getName().toString().equals("LogCorrespondenceFromEntity")) {
				String correspondenseEntity = (String) request.getEvent()
						.getValue();
				logger.debug(" correspondenseEntity :  " + correspondenseEntity);
				if (correspondenseEntity != null) {
					request.setAttribute(
							"correspondenceEntity", correspondenseEntity);
				}
				 actionName="receiveCorrespondenceEntity";
	
			} else if (event.getName().toString().equals(
					"LogCRFromCorrespondenceSearch")) {
				String searchCorrespondence = (String) event.getValue();
				logger.debug(" correspondence Search : " + searchCorrespondence);
				if (searchCorrespondence != null) {
					request.setAttribute("correspondenceSk",
							searchCorrespondence);
				}
				actionName="receiveCorrespondenceSk";
			} else if (event.getName().toString().equals(
					"LogCRFromInqAbtEntitySearch")) {
				String inquiryEntitySearch = (String) event.getValue();
				logger.debug(" correspondence Search : " + inquiryEntitySearch);
				if (inquiryEntitySearch != null) {
					request.setAttribute(
							"inquiryaboutEntityData", inquiryEntitySearch);
				}
				 actionName="receiveinquiryaboutEntityData";
	
			} else if (event.getName().toString().equals("CRLogFromEntitySearch")) {
				
				//Modified the existing code for wiring issue between Entity Search to Log Correspondence...Using WireEventDataCarrierVO instead for HashMap as it was incompatible type...
				
				//HashMap searchEntityMap = (HashMap) event.getValue();
				WireEventDataCarrierVO mapData = (WireEventDataCarrierVO) event.getValue();
				HashMap searchEntityMap = mapData.getMapData();
				if (searchEntityMap != null) {
					request.setAttribute("srcEntLogMap",
							searchEntityMap);
				}
				actionName="LogCrsSrchEntity_TARGET_ACTION";
			} else if (event.getName().toString().equals(
					"CRLogFromMaintainEntity_Primary")) {
				String maintainEntity = (String) event.getValue();
				if (maintainEntity != null) {
					request.setAttribute(
							"correspondenceEntity", maintainEntity);
				}
				actionName="receiveCorrespondenceEntity";
	
		/*	} else if (event.getName().toString().equals("LogCRToEntitySearch")) {
				String searchEntity = (String) event.getValue();
	
				if (searchEntity != null) {
					request.getPortletSession().setAttribute("CrsSearchCrtVO",
							searchEntity);
				}*/
			} else if (event.getName().toString().equals(
					"CRLogFromMaintainEntity_Inquiry")) {
				String maintainEntity = (String) event.getValue();
				if (maintainEntity != null) {
					
					/*Code Modified Below to display newly added 
					 *Entity details in Inquiring About Section in Log correspondence Portlet for Defect - ESPRD00831559*/
					
					/*request.getPortletSession().setAttribute(
							"inquiryaboutEntityData", maintainEntity);*/
					
					request.setAttribute("inquiryaboutEntityData", maintainEntity);
				}
				actionName="receiveinquiryaboutEntityData";
			} else if (event.getName().toString().equals("LogCRFromMyTasksMyCRs")) {
				String myTaskMyCR = (String) event.getValue();
				logger.debug("Value of MyTaskSK::::" + myTaskMyCR);
				if (myTaskMyCR != null) {
					request.setAttribute(
							"mytaskcorrespondenceSk", myTaskMyCR);
				}
				actionName="receiveMyTaskCorrespondenceSk";
			} else if (event.getName().toString().equals(
					"ClaimLogCRFromMyTaskGroupCR")) {
				String myTaskGroupCR = (String) event.getValue();
				logger.debug("Value of MyTaskGroupSK::::" + myTaskGroupCR);
				if (myTaskGroupCR != null) {
					request.setAttribute(
							"myTaskClaimCorrespondenceSk", myTaskGroupCR);
				}
				actionName="receiveMyTaskClaimCorrespondenceSk";
			} else if (event.getName().toString().equals("CRLogFromLetterGen")) {
				String letterGeneration = (String) event.getValue();
				logger.debug("Value of letterGenerationSK::::" + letterGeneration);
				if (letterGeneration != null) {
					request.getPortletSession().setAttribute(
							"LetterGenerationSKCreated", letterGeneration);
				}
			} else if (event.getName().toString().equals("CRLogFromDocRepository")) {
				//Object documentRepository = event.getValue();
				EnterpriseEDMSSearchResultsVO documentRepository = (EnterpriseEDMSSearchResultsVO) event.getValue();
				if (documentRepository != null) {
					request.setAttribute(
							"logCREDMSSearchResultsList", documentRepository);
				}
				 actionName="receiveEDMSSearchResultsAction";
	
			} else if (event.getName().toString().equals(
					"LogCorrespondenceFromLogCase")) {
				String logCaseObject = (String) event.getValue();
				if (logCaseObject != null) {
					request.setAttribute("AssociateCaseData",
							logCaseObject);
				}
				actionName="receiveCaseDataAction";
			} 

			if(event.equals("TRGLetterGenerationToCorrespondenceSK")) {
				String  receiveCorrespondenceSk = (String) event.getValue();
				if (receiveCorrespondenceSk != null) {
					request.setAttribute("correspondenceSk",
							receiveCorrespondenceSk);
				}
			//	actionName="receiveCaseDataAction";
			}
			
			actionUrl(request, response, actionName);

	}
}