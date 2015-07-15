/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
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
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.cots.edms.application.common.vo.EnterpriseEDMSSearchCriteriaVO;
import com.acs.enterprise.common.cots.edms.application.common.vo.EnterpriseEDMSSearchResultsVO;
import com.acs.enterprise.common.cots.lettergeneration.vo.CommonLetterInputVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.EntitySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceRecordVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.acs.enterprise.common.view.vo.WireEventDataCarrierVO;
import com.ibm.faces20.portlet.FacesPortlet;
//import com.ibm.faces.webapp.FacesGenericPortlet;


/**
 * This class is for Log Case Portlet.this class is executed. In this class, it
 * forwards the corresponding page based on the menu item selected from the
 * Contact Management Menu.
 * 
 * @wipro
 */
public class LogCasePortlet
        extends FacesPortlet
{
    
	private static final EnterpriseLogger log = EnterpriseLogFactory.getLogger(LogCasePortlet.class);

   

    /**
     * This method is to destroy.
     */
    public void destroy()
    {
        super.destroy();
    }

    /**
     * When the user clicks on Confirgure mode from admninstration console, this
     * method will be inovked.
     */
    public void doConfigure(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {
        super.doConfigure(request, response);
    }

    /**
     * When the user clicks on Edit mode from admninstration console, this
     * method will be inovked.
     */
    public void doEdit(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {
        super.doEdit(request, response);
    }

    /**
     * When the user clicks on Help Icon of Portlet menu, this method will be
     * inovked.
     */
    public void doHelp(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {
    	if(log.isDebugEnabled()){
    	log.debug("Inside LogcasePortlet doHelp :");
    	}
		
    	String actionURL = "/jsp/LogCase/log_case_Help.jsp";
    	
    	request.getPortletSession().setAttribute(
					"com.ibm.faces.portlet.page.help",
					actionURL);			
		
        super.doHelp(request, response);
    }

    /**
     * This method is to initialize the portlet.
     */
    public void init(PortletConfig portletConfig)
            throws PortletException
    {
        super.init(portletConfig);
    }

    /**
     * Every time when the user performs an action, this method will be invoked.
     */
    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {
    	if(log.isDebugEnabled()){
        log.debug("doView.LogCasePortlet is started");
    	}

		String caseEntity = (String) request.getPortletSession()
				.getAttribute("MaintainSearchId");
		if (caseEntity != null) {
			request.setAttribute("MaintainSearchId", caseEntity);
			request.getPortletSession().removeAttribute("MaintainSearchId");
		}
		
		String caseInquiryEntity = (String) request.getPortletSession()
		.getAttribute("MaintainInquirySearchId");
		
		  
		if (caseInquiryEntity != null)
		{
		request.setAttribute("MaintainInquirySearchId", caseInquiryEntity);
		request.getPortletSession().removeAttribute("MaintainInquirySearchId");
		}

        Object edmsSearchResultsObj = request.getPortletSession().getAttribute(
                "logCaseEDMSSearchResultsList");

	  // Infinite Computer Solutions FOR CR-1825 
        String currentPage = (String) request.getParameter("javax.servlet.include.path_info");
        
        if (edmsSearchResultsObj != null)
        {
            request.setAttribute("logCaseEDMSSearchResultsList",
                    edmsSearchResultsObj);
            request.getPortletSession().removeAttribute(
                    "logCaseEDMSSearchResultsList");
        }

        Object caseDetailsObj = request.getPortletSession().getAttribute(
                "CaseDetails");

        if (caseDetailsObj != null)
        {
            
            request.setAttribute("CaseDetails", caseDetailsObj);
            request.getPortletSession().removeAttribute("CaseDetails");
        }

        Object globalCaseDetailsObj = request.getPortletSession().getAttribute(
                "GlobalCaseSearchResult");

        if (globalCaseDetailsObj != null)
        {
           
            request
                    .setAttribute("GlobalCaseSearchResult",
                            globalCaseDetailsObj);
            request.getPortletSession().removeAttribute(
                    "GlobalCaseSearchResult");
        }
        /**
         * Added By Madhurima Associate Correspondence results
         */
        Object crResultsObj = request.getPortletSession().getAttribute(
                "CorrespondenceResultsList");
        {
            if (crResultsObj != null)
            {
                
                request.setAttribute("CorrespondenceResultsList", crResultsObj);
                request.getPortletSession().removeAttribute(
                        "CorrespondenceResultsList");
            }
        }
        String caseEntityDetails = (String) request.getPortletSession()
                .getAttribute("CaseEntityDetails");
        if (caseEntityDetails != null)
        {
            request.setAttribute("CaseEntityDetails", caseEntityDetails);
            request.getPortletSession().removeAttribute("CaseEntityDetails");
        }
        String inqAbtCaseEntityDetails = (String) request.getPortletSession()
                .getAttribute("inqAbtCaseEntityDetails");
        if (inqAbtCaseEntityDetails != null)
        {
            request.setAttribute("inqAbtCaseEntityDetails",
                    inqAbtCaseEntityDetails);
            request.getPortletSession().removeAttribute(
                    "inqAbtCaseEntityDetails");
        }

        
        Object myTaskCaseDetailsObj = request.getPortletSession().getAttribute(
                "MyTaskCaseSk");
        if (myTaskCaseDetailsObj != null)
        {
            request.setAttribute("MyTaskCaseSk", myTaskCaseDetailsObj);
            request.getPortletSession().removeAttribute("MyTaskCaseSk");
        }
       
        String myTaskClaimCaseDetails = (String) request.getPortletSession()
                .getAttribute("MyTaskClaimCaseSK");
        if (myTaskClaimCaseDetails != null)
        {
            
            request.setAttribute("MyTaskClaimCaseSK", myTaskClaimCaseDetails);
            request.getPortletSession().removeAttribute("MyTaskClaimCaseSK");
        }
        /** LetterGeneration */
        String ltrGenSk = (String) request.getPortletSession().getAttribute(
                "LetterGenerationSKAdded");
        if (ltrGenSk != null)
        {
            request.setAttribute("LetterGenerationSKAdded", ltrGenSk);
            request.getPortletSession().removeAttribute("LetterGenerationSKAdded");
        }  
        //Added for the defect ESPRD00891861
        /** LetterGeneration to LogCase*/
        String letterGenSk = (String) request.getPortletSession().getAttribute(
                "LetterGenerationForCase");
        if (letterGenSk != null)
        {
            request.setAttribute("LetterGenerationForCase", letterGenSk);
            request.getPortletSession().removeAttribute("LetterGenerationForCase");
        } 
        //Infinite Computer Solutions FOR CR-1825 
       
     if("/jsp/LogCase/LogCase.jsp".equalsIgnoreCase(currentPage)){
        	
    		setUserPermission(request,currentPage);
    		
    	}
        super.doView(request, response);
    }

	// Infinite Computer Solutions FOR CR-1825
	public void setUserPermission(RenderRequest request, String currentPage) {
	}
    /**
     * When the user clicks any link or button or any action on the page, this
     * method will be invoked.After completing this method, it goes to the
     * corresponding do* method from where the user performed action.
     * 
     * @param request
     *            request object
     * @param response
     *            response bject
     * @throws PortletException
     *             throws an exception when there is any error in the portlet.
     */
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException
    {
    	if(log.isDebugEnabled()){
    	log.debug("-------processAction--------");
    	}

//		String actionName10 = (String) request
//				.getParameter("receive.MaintainSearch.Id");
//		log.debug("actionName10" + actionName10);
//		if (actionName10 != null
//				&& actionName10.equals("receiveMaintainSearchId")) {
//
//			String MaintainSearchId = (String) request
//					.getAttribute("MaintainSearchId");
//			request.getPortletSession().setAttribute("MaintainSearchId",
//					MaintainSearchId);
//
//		}
//		log.debug("success");
//    	
//		
//		  String actionName11 = (String) request.getParameter("receive.MaintainInquirySearch.Id");
//		 
//		  if (actionName11 != null && actionName11.equals("receiveMaintainInquirySearchId"))
//			  {
//	
//			      String MaintainInquirySearchId = (String) request.getAttribute("MaintainInquirySearchId");
//			      request.getPortletSession().setAttribute("MaintainInquirySearchId",MaintainInquirySearchId);
//			
//			    
//			  }
//		 
//		
//    	//ESPRD00333432
//    	
//    	 String actionEntity = (String) request.getParameter("EntSrchtoLogCase_ACTION");
//    	 log.debug("In SearchCasePortlet:actionName" + actionEntity);
//
//
//         if (actionEntity != null && actionEntity.equalsIgnoreCase("EntSrchtoLogCase_TARGET_ACTION")) {
//         	Map dataMap = new HashMap();
//         	dataMap = (Map) request.getAttribute("SrchLogCaseMap");
//         	
//         	if (dataMap != null) {
//         	
//         	CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
//         	log.debug(" (String)dataMap.get ::> "+ (String)dataMap.get("EntityID"));
//         	
//         	String target =(String)dataMap.get("target");
//         		log.debug(" target ::> "+ target);
//         
//         	if (target.equalsIgnoreCase("CaseRegarding")){
// 	        	caserecordSearchCriteriaVO.setEntityType((String)dataMap.get("EntityType"));
// 	        	caserecordSearchCriteriaVO.setEntityIDType((String)dataMap.get("EntityIDType")); 
// 	        	caserecordSearchCriteriaVO.setEntityId((String)dataMap.get("EntityID"));
//         	}else {
// 	        	caserecordSearchCriteriaVO.setAdditionalEntityType((String)dataMap.get("EntityType"));
// 	        	caserecordSearchCriteriaVO.setAdditionalEntityIDType((String)dataMap.get("EntityIDType")); 
// 	        	caserecordSearchCriteriaVO.setAdditionalEntityID((String)dataMap.get("EntityID"));
//         	}
//         	request.getPortletSession().setAttribute("CaseRecordSearchCriteriaVO", caserecordSearchCriteriaVO);
//         }
//         }
//    	
//    	
//    	
//    	
//        String actionName1 = (String) request
//                .getParameter("receive.EDMSSearchResults.Action");
//        log.debug("Action Name from EDMS Search === " + actionName1);
//
//        if (actionName1 != null)
//        {
//            if ("receiveEDMSSearchResultsAction".equals(actionName1))
//            {
//                log.debug("logCaseEDMSSearchResultsList -----"
//                        + request.getAttribute("logCaseEDMSSearchResultsList"));
//
//                request.getPortletSession().setAttribute(
//                        "logCaseEDMSSearchResultsList",
//                        request.getAttribute("logCaseEDMSSearchResultsList"));
//            }
//        }
//        String actionName2 = (String) request
//                .getParameter("recive.CaseDetails.Action");
//        log.debug("Action name for Case Search details === " + actionName2);
//        if (actionName2 != null)
//        {
//            if ("receiveCaseDetailsAction".equals(actionName2))
//            {
//                log.debug("Case Details from Case Search ###  "
//                        + request.getAttribute("CaseDetails"));
//                request.getPortletSession().setAttribute("CaseDetails",
//                        request.getAttribute("CaseDetails"));
//            }
//        }
//        String actionName6 = (String) request
//                .getParameter("receive.GlobalCaseSearch.Results");
//        log.debug("Action name for Global Case Search details === "
//                + actionName6);
//        if (actionName6 != null)
//        {
//            if ("receiveGlobalCaseSearchResults".equals(actionName6))
//            {
//                log.debug("Case Details from Global Case Search ###  "
//                        + request.getAttribute("GlobalCaseSearchResult"));
//                request.getPortletSession().setAttribute(
//                        "GlobalCaseSearchResult",
//                        request.getAttribute("GlobalCaseSearchResult"));
//            }
//        }
//
//        String actionName3 = (String) request
//                .getParameter("recive.case.entity.details");
//        log.debug("Action name for Entity Search details === " + actionName3);
//        if (actionName3 != null)
//        {
//            if ("receiveCaseEntityDetails".equals(actionName3))
//            {
//                log.debug("Case Entity Details from Entity Search ###  "
//                        + request.getAttribute("CaseEntityDetails"));
//                request.getPortletSession().setAttribute("CaseEntityDetails",
//                        request.getAttribute("CaseEntityDetails"));
//            }
//        }
//
//        String actionName5 = (String) request
//                .getParameter("recive.inqAbt.case.entity.details");
//        log.debug("Action name for inqAbt Case Entity Search details === "
//                + actionName5);
//        if (actionName5 != null)
//        {
//            if ("receiveInqAbtCaseEntityDetailsAction".equals(actionName5))
//            {
//                log
//                        .debug("Inq About Case Entity Details from Entity Search ###  "
//                                + request
//                                        .getAttribute("inqAbtCaseEntityDetails"));
//                request.getPortletSession().setAttribute(
//                        "inqAbtCaseEntityDetails",
//                        request.getAttribute("inqAbtCaseEntityDetails"));
//            }
//        }
//
//       
//        String actionName4 = (String) request
//                .getParameter("receive.mytask.case.sk");
//        log.debug("Action name for MytaskCasesk===" + actionName4);
//        if (actionName4 != null)
//        {
//            if ("receiveMyTaskCaseSk".equals(actionName4))
//            {
//                log.debug("CaseSk from MyTask ###  "
//                        + request.getAttribute("MyTaskCaseSk"));
//                request.getPortletSession().setAttribute("MyTaskCaseSk",
//                        request.getAttribute("MyTaskCaseSk"));
//            }
//        }
//       
//        String actionName9 = (String) request
//                .getParameter("receive.mytask.claimcase.sk");
//        log.debug("Action name for MytaskCasesk===" + actionName9);
//        if (actionName9 != null)
//        {
//            if ("receiveMyTaskClaimCaseSk".equals(actionName9))
//            {
//                log.debug("CaseSk from MyTaskclaim ###  "
//                        + request.getAttribute("MyTaskClaimCaseSK"));
//                request.getPortletSession().setAttribute("MyTaskClaimCaseSK",
//                        request.getAttribute("MyTaskClaimCaseSK"));
//            }
//        }
//       
//        String actionName7 = (String) request
//                .getParameter("receive.letterGeneration.skCase");
//        log.debug("ActionName from LetterGeneration:" + actionName7);
//        if (actionName7 != null)
//        {
//            if ("receiveLetterGenerationSkForCase".equals(actionName7))
//            {
//                log.debug("letterSk from ltrGen ###  "
//                        + request.getAttribute("LetterGenerationSKAdded"));
//                request.getPortletSession().setAttribute(
//                        "LetterGenerationSKAdded",
//                        request.getAttribute("LetterGenerationSKAdded"));
//            }
//        }
//        
//        String actionName8 = (String) request
//                .getParameter("receive.CorrespondenceResults.Action");
//        log.debug("ActionName from Correspondence:" + actionName8);
//        if (actionName8 != null)
//        {
//            if ("receiveCorrespondenceResultsAction".equals(actionName8))
//            {
//                log.debug("crResults from Cr###  "
//                        + request.getAttribute("CorrespondenceResultsList"));
//                request.getPortletSession().setAttribute("CorrespondenceResultsList",
//                        request.getAttribute("CorrespondenceResultsList"));
//            }
//        }
//        
//        String actionName = (String) request.getParameter("receive.logcase.sk");
//       
//        	log.debug("receive.logcase.sk:" + actionName);
//        	if (actionName != null)
//			{
//			    if ("receive.logcase.sk".equals(actionName))
//			    {
//			    	
//			    	
//
//			    }
//			}
//        
//     	 
// 		actionName = request.getParameter(ContactManagementConstants.SEND_LTR_REQ_SK);
// 		log.info("----- actionName --------" + actionName);
// 		
// 		if (actionName != null && actionName.equalsIgnoreCase(
// 				ContactManagementConstants.LTR_REQ_SK_ACTION_NAME)) {
// 			String ltrReqSK = null;
// 			PortletSession session = request.getPortletSession(false);
// 			if (session != null) {
// 				ltrReqSK = (String)session.getAttribute(ContactManagementConstants.LTR_REQ_SK_PARAM);
// 				session.removeAttribute(ContactManagementConstants.LTR_REQ_SK_PARAM,
// 						PortletSession.PORTLET_SCOPE);
// 			}
//
// 			log.info("ltrReqSK in processAction = " + ltrReqSK);
// 			
// 			if (ltrReqSK != null) {
// 				request.setAttribute(ContactManagementConstants.LTR_REQ_SK, ltrReqSK);
// 			}
// 		}
// 		
        
        
        super.processAction(request, response);

		CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
				.getAttribute("AddAppealDetails");
		if (commonCMCaseDetailsVO != null) {
			response.setEvent("LogCaseToAddAppeals", commonCMCaseDetailsVO);
		}

		CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) request
				.getAttribute("CaseSearchVO");
		if (caseRecordSearchCriteriaVO != null) {
			response.setEvent("LogCaseToEntitySearch",
					caseRecordSearchCriteriaVO);
		}
		//Added for IPC for Defect ESPRD00802462
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = (CorrespondenceSearchCriteriaVO) request
				.getAttribute("CrsSearchCrtVO");
		if (correspondenceSearchCriteriaVO != null) {
			response.setEvent("CRSearchToEntitySearch",
					correspondenceSearchCriteriaVO);
		}

		CommonLetterInputVO inputVO = (CommonLetterInputVO) request
				.getAttribute("CommonLetterInput");
		if (inputVO != null) {
			response.setEvent("LogCaseToLetterGenerationVO", inputVO);
		}

		String associateCaseData = (String) request
				.getAttribute("AssociateCaseData");
		if (associateCaseData != null) {
			response.setEvent("LogCaseToLogCorrespondence", associateCaseData);
		}

		// Needs to work with the below commented portion, if any failures.
		// Initialize the fields in the class as per your requirement
		String logCaseToLetterGenerationCaseDataEvent = (String)request.getAttribute("letterRequestSK");
		if(logCaseToLetterGenerationCaseDataEvent!=null)
		{
		response.setEvent("LogCaseToLetterGenerationCaseData",logCaseToLetterGenerationCaseDataEvent);
		}
		//		
		EnterpriseEDMSSearchCriteriaVO edmsSearchresultsVO = (EnterpriseEDMSSearchCriteriaVO) request
				.getAttribute("EDMSSearchCriteria");
		if (edmsSearchresultsVO != null) {
			response.setEvent("LogCaseToEDMS", edmsSearchresultsVO);
		}
	
		//WSRP Changes
		CorrespondenceRecordVO corrRecVO = (CorrespondenceRecordVO)request.getAttribute("CorrespondenceResultsList");
		
		if (corrRecVO != null){
			response.setEvent("LogCorrespondenceToLogCase", edmsSearchresultsVO);
			
		}
   
   
    }
    public void actionUrl(EventRequest request, EventResponse response,
			String actionName) {
//		actionName = (String) request
//				.getParameter("receive.MaintainSearch.Id");
    	if(log.isDebugEnabled()){
		log.debug("actionName10" + actionName);
    	}
		if (actionName != null
				&& actionName.equals("receiveMaintainSearchId")) {

			String MaintainSearchId = (String) request
					.getAttribute("MaintainSearchId");
			request.getPortletSession().setAttribute("MaintainSearchId",
					MaintainSearchId);

		}
    	
		
		  //String actionName11 = (String) request.getParameter("receive.MaintainInquirySearch.Id");
		 
		  if (actionName != null && actionName.equals("receiveMaintainInquirySearchId"))
			  {
	
			      String MaintainInquirySearchId = (String) request.getAttribute("MaintainInquirySearchId");
			      request.getPortletSession().setAttribute("MaintainInquirySearchId",MaintainInquirySearchId);
			
			    
			  }
		 
		
    	//ESPRD00333432
    	/*
    	 String actionEntity = (String) request.getParameter("EntSrchtoLogCase_ACTION");
    	 log.debug("In SearchCasePortlet:actionName" + actionEntity);*/


         if (actionName != null && actionName.equalsIgnoreCase("EntSrchtoLogCase_TARGET_ACTION")) {
         	Map dataMap = new HashMap();
         	dataMap = (Map) request.getAttribute("SrchLogCaseMap");
         	
         	if (dataMap != null) {
         	
         	CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
         	
         	String target =(String)dataMap.get("target");
         
         	if (target.equalsIgnoreCase("CaseRegarding")){
 	        	caserecordSearchCriteriaVO.setEntityType((String)dataMap.get("EntityType"));
 	        	caserecordSearchCriteriaVO.setEntityIDType((String)dataMap.get("EntityIDType")); 
 	        	caserecordSearchCriteriaVO.setEntityId((String)dataMap.get("EntityID"));
         	}else {
 	        	caserecordSearchCriteriaVO.setAdditionalEntityType((String)dataMap.get("EntityType"));
 	        	caserecordSearchCriteriaVO.setAdditionalEntityIDType((String)dataMap.get("EntityIDType")); 
 	        	caserecordSearchCriteriaVO.setAdditionalEntityID((String)dataMap.get("EntityID"));
         	}
         	request.getPortletSession().setAttribute("CaseRecordSearchCriteriaVO", caserecordSearchCriteriaVO);
         	
         }
         }
         //Added for IPC for Defect ESPRD00802462 
         if (actionName != null && actionName.equalsIgnoreCase("EntSrchtoLogCaseCR_TARGET_ACTION")) {
          	Map dataMap = new HashMap();
          	dataMap = (Map) request.getAttribute("EntSrchLogCaseCRMap");
          	
          	if (dataMap != null) {
          	          	
          	request.getPortletSession().setAttribute("DataMap", dataMap);
          }
          }
    	
    	
    	
    	
      /*   actionName = (String) request
                .getParameter("receive.EDMSSearchResults.Action");
        log.debug("Action Name from EDMS Search === " + actionName);*/

        if (actionName != null)
        {
            if ("receiveEDMSSearchResultsAction".equals(actionName))
            {

                request.getPortletSession().setAttribute(
                        "logCaseEDMSSearchResultsList",
                        request.getAttribute("logCaseEDMSSearchResultsList"));
            }
        }
        /*actionName = (String) request
                .getParameter("recive.CaseDetails.Action");
        log.debug("Action name for Case Search details === " + actionName);*/
        if (actionName != null)
        {
            if ("receiveCaseDetailsAction".equals(actionName))
            {
                request.getPortletSession().setAttribute("CaseDetails",
                        request.getAttribute("CaseDetails"));
            }
        }
       /* actionName = (String) request
                .getParameter("receive.GlobalCaseSearch.Results");
        log.debug("Action name for Global Case Search details === "
                + actionName);*/
        if (actionName != null)
        {
            if ("receiveGlobalCaseSearchResults".equals(actionName))
            {
                request.getPortletSession().setAttribute(
                        "GlobalCaseSearchResult",
                        request.getAttribute("GlobalCaseSearchResult"));
            }
        }

       /* actionName = (String) request
                .getParameter("recive.case.entity.details");
        log.debug("Action name for Entity Search details === " + actionName);*/
        if (actionName != null)
        {
            if ("receiveCaseEntityDetails".equals(actionName))
            {
                request.getPortletSession().setAttribute("CaseEntityDetails",
                        request.getAttribute("CaseEntityDetails"));
            }
        }

       /* actionName = (String) request
                .getParameter("recive.inqAbt.case.entity.details");
        log.debug("Action name for inqAbt Case Entity Search details === "
                + actionName);*/
        if (actionName != null)
        {
            if ("receiveInqAbtCaseEntityDetailsAction".equals(actionName))
            {
                request.getPortletSession().setAttribute(
                        "inqAbtCaseEntityDetails",
                        request.getAttribute("inqAbtCaseEntityDetails"));
            }
        }

       
        /*actionName = (String) request
                .getParameter("receive.mytask.case.sk");
        log.debug("Action name for MytaskCasesk===" + actionName);*/
        if (actionName != null)
        {
            if ("receiveMyTaskCaseSk".equals(actionName))
            {
                request.getPortletSession().setAttribute("MyTaskCaseSk",
                        request.getAttribute("MyTaskCaseSk"));
            }
        }
       /*
        actionName = (String) request
                .getParameter("receive.mytask.claimcase.sk");
        log.debug("Action name for MytaskCasesk===" + actionName);*/
        if (actionName != null)
        {
            if ("receiveMyTaskClaimCaseSk".equals(actionName))
            {
                request.getPortletSession().setAttribute("MyTaskClaimCaseSK",
                        request.getAttribute("MyTaskClaimCaseSK"));
            }
        }
       
       /* actionName = (String) request
                .getParameter("receive.letterGeneration.skCase");
        log.debug("ActionName from LetterGeneration:" + actionName);*/
        if (actionName != null)
        {
            if ("receiveLetterGenerationSkForCase".equals(actionName))
            {
                request.getPortletSession().setAttribute(
                        "LetterGenerationSKAdded",
                        request.getAttribute("LetterGenerationSKAdded"));
            }
        }
        
       /* actionName = (String) request
                .getParameter("receive.CorrespondenceResults.Action");
        log.debug("ActionName from Correspondence:" + actionName);*/
        if (actionName != null)
        {
            if ("receiveCorrespondenceResultsAction".equals(actionName))
            {
                request.getPortletSession().setAttribute("CorrespondenceResultsList",
                        request.getAttribute("CorrespondenceResultsList"));
            }
        }
        //Added for the defect ESPRD00891861
        if (actionName != null)
        {
            if ("FromLetterGenerationToCase".equals(actionName))
            {
                request.getPortletSession().setAttribute("LetterGenerationForCase",
                        request.getAttribute("LetterGenerationForCase"));
            }
        }
        
      //  String actionName = (String) request.getParameter("receive.logcase.sk");
        if(log.isDebugEnabled()){
        	log.debug("receive.logcase.sk:" + actionName);
        }
        	if (actionName != null)
			{
			    if ("receive.logcase.sk".equals(actionName))
			    {
			    	
			    	

			    }
			}
        
     	 
 		/*actionName = request.getParameter(ContactManagementConstants.SEND_LTR_REQ_SK);
 		log.info("----- actionName --------" + actionName);
 		*/
 		if (actionName != null && actionName.equalsIgnoreCase(
 				ContactManagementConstants.LTR_REQ_SK_ACTION_NAME)) {
 			String ltrReqSK = null;
 			PortletSession session = request.getPortletSession(false);
 			if (session != null) {
 				ltrReqSK = (String)session.getAttribute(ContactManagementConstants.LTR_REQ_SK_PARAM);
 				session.removeAttribute(ContactManagementConstants.LTR_REQ_SK_PARAM,
 						PortletSession.PORTLET_SCOPE);
 			}
 			
 			if (ltrReqSK != null) {
 				request.setAttribute(ContactManagementConstants.LTR_REQ_SK, ltrReqSK);
 			}
 		}
		}
	public void processEvent(EventRequest request, EventResponse response)
			throws PortletException, IOException {
		String actionName = null;
		Event event = request.getEvent();
		if(log.isDebugEnabled()){
		log.debug(" LogCase :: event.getName() :> "
				+ event.getName().toString());
		}
		if (event.getName().toString().equals("CaseEntityDetails")) {
			String maintainSearchId = (String) request.getEvent().getValue();

			if (maintainSearchId != null) {
				request.setAttribute("CaseEntityDetails",
						maintainSearchId);
			}
			actionName="receiveCaseEntityDetails";
		}

		if (event.getName().toString().equals("CMCaseSearchDataType")) {
			CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
					.getEvent().getValue();

			if (commonCMCaseDetailsVO != null) {
				request.setAttribute("CaseDetails",
						commonCMCaseDetailsVO);
			}
		}

		if (event.getName().toString().equals("ContactMaintainEntityToLogCase")) {
			String maintainInquirySearchId = (String) request.getEvent()
					.getValue();

			if (maintainInquirySearchId != null) {
				request.setAttribute(
						"MaintainInquirySearchId", maintainInquirySearchId);
			}
			actionName="receiveMaintainInquirySearchId";
		}

		if (event.getName().toString().equals("EntitySearchToLogCase")) {
			EntitySearchCriteriaVO entitySearchCriteriaVO = (EntitySearchCriteriaVO) event
					.getValue();

			Map dataMap = entitySearchCriteriaVO.getMap();

			CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
			String target = (String) dataMap.get("target");
			String search = (String) dataMap.get("search");

			if (target != null && target.equalsIgnoreCase("CaseRegarding")) {
				caserecordSearchCriteriaVO.setEntityType((String) dataMap
						.get("EntityType"));
				caserecordSearchCriteriaVO.setEntityIDType((String) dataMap
						.get("EntityIDType"));
				caserecordSearchCriteriaVO.setEntityId((String) dataMap
						.get("EntityID"));
			} else {
				caserecordSearchCriteriaVO
						.setAdditionalEntityType((String) dataMap
								.get("EntityType"));
				caserecordSearchCriteriaVO
						.setAdditionalEntityIDType((String) dataMap
								.get("EntityIDType"));
				caserecordSearchCriteriaVO
						.setAdditionalEntityID((String) dataMap.get("EntityID"));
			}
			request.setAttribute(
					"CaseRecordSearchCriteriaVO", caserecordSearchCriteriaVO);
			actionName="EntSrchtoLogCase_TARGET_ACTION";
		}

		if (event.getName().toString()
				.equals("inqAbtCaseEntityDetailsDataType")) {
			String maintainInquirySearchId = (String) request.getEvent()
					.getValue();

			if (maintainInquirySearchId != null) {
				request.setAttribute(
						"inqAbtCaseEntityDetails", maintainInquirySearchId);
			}
			actionName="receiveInqAbtCaseEntityDetailsAction";
		}

		if (event.getName().toString().equals("TRGLogCaseCMCaseSearchDataType")) {
			CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
					.getEvent().getValue();

			if (commonCMCaseDetailsVO != null) {
				request.setAttribute("CaseDetails",
						commonCMCaseDetailsVO);
			}
			actionName="receiveCaseDetailsAction";
		}

		if (event.getName().toString().equals("EDMSToLogCase")) {
			EnterpriseEDMSSearchResultsVO enterpriseEDMSSearchResultsVO = (EnterpriseEDMSSearchResultsVO) request
					.getEvent().getValue();
			if(enterpriseEDMSSearchResultsVO!=null){
			request.setAttribute(
					"logCaseEDMSSearchResultsList",
					enterpriseEDMSSearchResultsVO);
			}
			 actionName="receiveEDMSSearchResultsAction";

		}

		if (event.getName().toString().equals("LetterGenerationToLogCase")) {
			String letterGenerationSKAdded = (String) request.getEvent()
					.getValue();
			if(letterGenerationSKAdded!=null){
			request.setAttribute("LetterGenerationSKAdded",
					letterGenerationSKAdded);
			}
			actionName="receiveLetterGenerationSkForCase";

		}

		if (event.getName().toString().equals("MytaskToLogCase")) {
			CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
					.getEvent().getValue();
			if(commonCMCaseDetailsVO!=null){
			request.setAttribute("MyTaskCaseSk",
					commonCMCaseDetailsVO);
			}
			 actionName="receiveMyTaskCaseSk";

		}
		if (event.getName().toString().equals(
				"MyTasksGroupCaseRecordsToLogCase")) {
			String myTaskClaimCaseSK = (String) request.getEvent().getValue();
			if(myTaskClaimCaseSK!=null){
			request.setAttribute("MyTaskClaimCaseSK",
					myTaskClaimCaseSK);
			}
			actionName="receiveMyTaskClaimCaseSk";
		}
		if (event.getName().toString().equals("GlobalCasetoLogCase")) {
			CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
					.getEvent().getValue();
			if(commonCMCaseDetailsVO!=null){
			request.setAttribute("GlobalCaseSearchResult",
					commonCMCaseDetailsVO);
			}
			actionName="receiveGlobalCaseSearchResults";
		}
		if (event.getName().toString().equals("CorrespondenceLogToLogCase")) {
			CorrespondenceRecordVO crRecordVO = (CorrespondenceRecordVO) request
					.getEvent().getValue();
			if(crRecordVO!=null){
			request.setAttribute(
					"CorrespondenceResultsList", crRecordVO);
			}
			 actionName="receiveCorrespondenceResultsAction";

		}
		if (event.getName().toString().equals(
				"fromPGM_IN_PAInquirytoPGM_IN_LogCaseProcessEvent")) {
			CommonCMCaseDetailsVO caseDetCase = (CommonCMCaseDetailsVO) request
					.getEvent().getValue();
			if(caseDetCase!=null){
			request.setAttribute("CaseDetails", caseDetCase);
			}
			actionName="receiveCaseDetailsAction";
		}

		Event letterGenToNewLogCaseEvent = request.getEvent();
		if (letterGenToNewLogCaseEvent.getName().toString().equals(
				"LetterGenToNewLogCase")) {
			String fromletterGenToLogCase = (String) letterGenToNewLogCaseEvent
					.getValue();
			if (fromletterGenToLogCase != null) {
				request.setAttribute(
						"LetterGenerationForCase", fromletterGenToLogCase);
			}
			actionName="FromLetterGenerationToCase";

		}

		Event fromLogCaseToEntySearchEvent = request.getEvent();
		if (fromLogCaseToEntySearchEvent.getName().toString().equals(
				"LogCaseToEntitySearch")) {
			CaseRecordSearchCriteriaVO fromLogCaseEntySearch = (CaseRecordSearchCriteriaVO) fromLogCaseToEntySearchEvent
					.getValue();
			if (fromLogCaseEntySearch != null) {
				request.setAttribute("CaseSearchVO",
						fromLogCaseEntySearch);
			}
			actionName="EntSrch";
		}
		Event maintainEntityToinquiryLogcase = request.getEvent();
		if (maintainEntityToinquiryLogcase.getName().toString().equals(
				"TRGMaintainEntityToInquiryLogCase")) {
			String fromletterGenToLogCase = (String) maintainEntityToinquiryLogcase
					.getValue();
			if (fromletterGenToLogCase != null) {
				request.setAttribute("MaintainSearchId",
						fromletterGenToLogCase);
			}
			actionName="receiveMaintainSearchId";
		}
		
		if (event.getName().toString().equals("LogCaseFromEntitysearch")) {
			EntitySearchCriteriaVO entitySearchCriteriaVO = (EntitySearchCriteriaVO) request.getEvent().getValue();
			if(entitySearchCriteriaVO!=null){
			request.setAttribute("SrchLogCaseMap",
					entitySearchCriteriaVO.getMap());
			}
			/*WireEventDataCarrierVO entitySearch = (WireEventDataCarrierVO) request.getEvent().getValue();
			if(entitySearch!=null){
			request.setAttribute("SrchLogCaseMap",
					entitySearch.getMapData());
			}*/
			 actionName="EntSrchtoLogCase_TARGET_ACTION";

		}
		if (event.getName().toString().equals("EntitySearchToLogCaseAssociatedCR")) {
			WireEventDataCarrierVO entitySearch = (WireEventDataCarrierVO) request.getEvent().getValue();
			if(entitySearch!=null){
			request.setAttribute("EntSrchLogCaseCRMap",
					entitySearch.getMapData());
			}
			 actionName="EntSrchtoLogCaseCR_TARGET_ACTION";

		}

		if (event.getName().toString().equals("TRGLogCaseCMCaseSearchDataType")) {
			CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
				.getEvent().getValue();
			if(commonCMCaseDetailsVO!=null){
			request.setAttribute("MyTaskCaseSk",
					commonCMCaseDetailsVO);
			}
			actionName="receiveCaseDetailsAction";
		}
		actionUrl(request, response, actionName);
	}

}
