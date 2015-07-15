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
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.EntitySearchCriteriaVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.ibm.faces20.portlet.FacesPortlet;

/**
 * This class is for Search Case Portlet.
 * 
 * @wipro
 */
public class SearchCasePortlet
        extends FacesPortlet
{
   
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(SearchCasePortlet.class);

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
    	if(logger.isInfoEnabled()){
    	logger.info("In process Action.....");
    	}
     
        String actionName = (String) request
                .getParameter("com.ibm.portal.propertybroker.action");
                
                
//        String actionEntity = (String) request.getParameter("EntSrchCrsSrch_ACTION");
//        System.err.println("In SearchCasePortlet:actionName: " + actionEntity);
//
//        //UC-PGM-CRM-033_ESPRD00624909_09jun2011
//        //if (actionEntity != null && actionEntity.equalsIgnoreCase("EntSrchCrsSrch_TARGET_ACTION")) {
//        if (actionEntity != null && (actionEntity.equalsIgnoreCase("EntSrchCrsSrch_TARGET_ACTION") || actionEntity.equalsIgnoreCase("EntSrchCrsSrch_SOURCE_ACTION") )) {
//        	Map dataMap= new HashMap();
//        	//dataMap = (Map) request.getAttribute("entitySearchMap2");//UC-PGM-CRM-033_ESPRD00624909_09jun2011
//        	System.err.println("request.getAttribute(entitySearchMap2)"+request.getAttribute("entitySearchMap2"));
//        	System.err.println("request.getAttribute(EntitySearchMap2)"+request.getAttribute("EntitySearchMap2"));
//        	if(request.getAttribute("entitySearchMap2")!=null){
//        		dataMap= (Map) request.getAttribute("entitySearchMap2");
//        	}else
//        	if(request.getAttribute("EntitySearchMap2")!=null){
//        		dataMap= (Map) request.getAttribute("EntitySearchMap2");
//        	}
//        	//System.err.println("DAtaaMap:: "+dataMap);
//        	//EOf UC-PGM-CRM-033_ESPRD00624909_09jun2011
//        	CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
//        	//ESPRD0033432_20112009
//        	String target =(String)dataMap.get("target");
//        	System.err.println(" (String)dataMap.get ::> "+ (String)dataMap.get("EntityID"));
//        	String search =(String)dataMap.get("search");
//        	System.err.println(" search ::> "+ search);
//        	//ESPRD0033432_20112009
//        
//        	if (target != null && target.equalsIgnoreCase("CaseRegarding")){
//	        	caserecordSearchCriteriaVO.setEntityType((String)dataMap.get("EntityType"));
//	        	caserecordSearchCriteriaVO.setEntityIDType((String)dataMap.get("EntityIDType")); 
//	        	caserecordSearchCriteriaVO.setEntityId((String)dataMap.get("EntityID"));
//        	}else {
//	        	caserecordSearchCriteriaVO.setAdditionalEntityType((String)dataMap.get("EntityType"));
//	        	caserecordSearchCriteriaVO.setAdditionalEntityIDType((String)dataMap.get("EntityIDType")); 
//	        	caserecordSearchCriteriaVO.setAdditionalEntityID((String)dataMap.get("EntityID"));
//        	}
//        	request.getPortletSession().setAttribute("CaseRecordSearchCriteriaVO", caserecordSearchCriteriaVO);
//        }
//        if (actionName != null)
//        {
//            if (actionName.equals("receiveMemberLogCaseAction"))
//            {
//                logger.debug("Inside  SearchCasePortlet : received");
//
//                String logCase = (String) request.getParameter("MemberLogCase");
//
//                logger.debug("Printing MemberLogCase--------->" + logCase);
//
//                request.getPortletSession().setAttribute("MemberLogCase",
//                        logCase);
//
//                logger.debug("success");
//            }
//        }
//        String actionName1 = (String) request
//                   .getParameter("recieve.CaseTracking.AppealSk");
//        logger.debug("In SearchCase:actionName: " + actionName1);
//
//        if (actionName1 != null)
//      {
//
//        if (actionName1.equals("recieveCaseTrackingAppealSk"))
//        {
//        logger.debug("Inside  SearchCase : received");
//
//        String caseSk  = (String) request
//                .getAttribute("CaseTrackingAppealSK");
//
//        logger.debug("Printing appealCaseSk--------->"
//                + caseSk);
//
//        request.getPortletSession().setAttribute(
//                "CaseTrackingAppealSK", caseSk);
//
//        logger.debug("success");
//       }
//     }
//        String actionName2 = (String) request
//        .getParameter("recieve.CaseTracking.ProviderSk");
//       logger.debug("In SearchCase:ProvideractionName: " + actionName2);
//
//    if (actionName2 != null)
//    {
//
//       if (actionName2.equals("recieveCaseTrackingProviderSk"))
//       {
//           logger.debug("Inside  SearchCase providerSK: received");
//
//         String casePrvSk  = (String) request
//           .getAttribute("CaseTrackingProviderSk");
//
//       logger.debug("Printing casePrvSk--------->"
//           + casePrvSk);
//
//          request.getPortletSession().setAttribute(
//                           "CaseTrackingProviderSk", casePrvSk);
//
//              logger.debug("success2");
//           }
//     }
    
     
        super.processAction(request, response);
		CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
				.getAttribute("CaseDetails");
		if (commonCMCaseDetailsVO != null) {
			response.setEvent("SearchCaseCMCaseSearchDataType",
					commonCMCaseDetailsVO);
    }

		CaseRecordSearchCriteriaVO caseEntitySearch = (CaseRecordSearchCriteriaVO) request
				.getAttribute("CaseSearchVO");
		if (caseEntitySearch != null) {
			response
					.setEvent("CaseDetailsToCaseEntitySearch", caseEntitySearch);
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

        String logCase = (String) request.getPortletSession().getAttribute(
                "MemberLogCase");

        if (logCase != null)
        {

            request.setAttribute("MemberLogCase", logCase);

            request.getPortletSession().removeAttribute("MemberLogCase");
        }
        String appealCaseSk = (String) request.getPortletSession()
        .getAttribute("CaseTrackingAppealSK");

       if (appealCaseSk != null)
       {
       request.setAttribute("CaseTrackingAppealSK", appealCaseSk);

       request.getPortletSession().removeAttribute("CaseTrackingAppealSK");
       }
       
       String providerCaseSk = (String) request.getPortletSession()
       .getAttribute("CaseTrackingProviderSk");

      if (providerCaseSk != null)
      {
      request.setAttribute("CaseTrackingProviderSk", providerCaseSk);
      request.getPortletSession().removeAttribute("CaseTrackingProviderSk");
      }
      
      String memCaseSk = (String)request.getPortletSession()
      .getAttribute("CaseTrackingMemberSK");
      if(memCaseSk != null)
      {
          request.setAttribute("CaseTrackingMemberSK", memCaseSk);
          request.getPortletSession().removeAttribute("CaseTrackingMemberSK");
          
      }

  
        super.doView(request, response);
    }

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processEvent(javax.portlet.EventRequest,
	 *      javax.portlet.EventResponse)
	 */
	public void processEvent(EventRequest request, EventResponse response)
			throws PortletException, java.io.IOException {
		String actionName = null;
		if(logger.isDebugEnabled()){
		logger.debug(" search case processEvent ");
		}
		Event event = request.getEvent();
		if (event.getName().toString().equals("EntitySearchToSearchCase")) {
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
			request.getPortletSession().setAttribute(
					"CaseRecordSearchCriteriaVO", caserecordSearchCriteriaVO);
		}
		if (event.getName().toString().equals("FromTPLMSQToContactCaseDetails")) {
			String tplMSQToContactCaseDetails = (String) event.getValue();
			if (tplMSQToContactCaseDetails != null) {
				request.getPortletSession().setAttribute("MemberLogCase",
						tplMSQToContactCaseDetails);
			}
			actionName="receiveMemberLogCaseAction";
		}
		if (event.getName().toString().equals(
				"fromMemberDetailsMaintenanceToCase")) {
			String memDetCase = (String) request.getEvent().getValue();
			if(memDetCase!=null){
			request.setAttribute("MemberLogCase",
					memDetCase);
			}
			actionName = "receiveMemberLogCaseAction";
		}
		if (event.getName().toString().equals("TRGAddAppealToSearchCase")) {
			String addAppeal = (String) request.getEvent().getValue();
			if(addAppeal!=null){
			request.setAttribute("CaseTrackingAppealSK",
					addAppeal);
			}
			actionName="recieveCaseTrackingAppealSk";
		}
		
		if (event.getName().toString().equals("ProcessEntitySearchToSearchCase")) {
			EntitySearchCriteriaVO entitySearchCriteriaVO = (EntitySearchCriteriaVO) request.getEvent().getValue();
			CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();

			request.setAttribute("EntitySearchMap2",
					entitySearchCriteriaVO.getMap());
			actionName = "EntSrchCrsSrch_TARGET_ACTION";
		}
		
		if (event.getName().toString().equals("SearchCaseCaseTrackingAppealDatatype")) {
			String searchCase = (String) request.getEvent().getValue();
			if(searchCase!=null){
			request.setAttribute("CaseTrackingAppealSK",
					searchCase);
			actionName="recieveCaseTrackingAppealSk";
			}
	}
		actionUrl(request, response, actionName);
}
	
	/* WSRP Changes
     * New method to accommodate the actionName 
     * 
     */
    public void actionUrl(EventRequest request, EventResponse response, String actionName){
    	if(logger.isInfoEnabled()){
    	logger.info("In process Action.....");
    	}
        
        /*String actionName = (String) request
                .getParameter("com.ibm.portal.propertybroker.action");*/
                
                
//        String actionEntity = (String) request.getParameter("EntSrchCrsSrch_ACTION");
//        System.err.println("In SearchCasePortlet:actionName: " + actionEntity);

        //UC-PGM-CRM-033_ESPRD00624909_09jun2011
        //if (actionEntity != null && actionEntity.equalsIgnoreCase("EntSrchCrsSrch_TARGET_ACTION")) {
        if (actionName != null && (actionName.equalsIgnoreCase("EntSrchCrsSrch_TARGET_ACTION") || actionName.equalsIgnoreCase("EntSrchCrsSrch_SOURCE_ACTION") )) {
        	Map dataMap= new HashMap();
        	//dataMap = (Map) request.getAttribute("entitySearchMap2");//UC-PGM-CRM-033_ESPRD00624909_09jun2011
        	if(request.getAttribute("entitySearchMap2")!=null){
        		dataMap= (Map) request.getAttribute("entitySearchMap2");
        	}else
        	if(request.getAttribute("EntitySearchMap2")!=null){
        		dataMap= (Map) request.getAttribute("EntitySearchMap2");
        	}
        	//System.err.println("DAtaaMap:: "+dataMap);
        	//EOf UC-PGM-CRM-033_ESPRD00624909_09jun2011
        	CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
        	//ESPRD0033432_20112009
        	String target =(String)dataMap.get("target");
        	String search =(String)dataMap.get("search");
        	//ESPRD0033432_20112009
        
        	if (target != null && target.equalsIgnoreCase("CaseRegarding")){
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
        if (actionName != null)
        {
            if (actionName.equals("receiveMemberLogCaseAction"))
            {
                String logCase = (String) request.getAttribute("MemberLogCase");

                request.getPortletSession().setAttribute("MemberLogCase",
                        logCase);
            }
        }
        /*String actionName1 = (String) request
                   .getParameter("recieve.CaseTracking.AppealSk");
        logger.debug("In SearchCase:actionName: " + actionName1);*/

        if (actionName != null)
      {

        if (actionName.equals("recieveCaseTrackingAppealSk"))
        {
        String caseSk  = (String) request
                .getAttribute("CaseTrackingAppealSK");


        request.getPortletSession().setAttribute(
                "CaseTrackingAppealSK", caseSk);

       }
     }
       /* String actionName2 = (String) request
        .getParameter("recieve.CaseTracking.ProviderSk");
       logger.debug("In SearchCase:ProvideractionName: " + actionName2);*/

    if (actionName != null)
    {

       if (actionName.equals("recieveCaseTrackingProviderSk"))
       {

         String casePrvSk  = (String) request
           .getAttribute("CaseTrackingProviderSk");


          request.getPortletSession().setAttribute(
                           "CaseTrackingProviderSk", casePrvSk);

           }
     }

    }
    
	
    
}
