/*
 * Created on Apr 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;
import java.util.HashMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.EntitySearchCriteriaVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.WireEventDataCarrierVO;
import com.ibm.faces20.portlet.FacesPortlet;

/**
 * @author praveeng
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SearchEntityPortlet extends FacesPortlet {

	/** Enterprise Logger for Logging. */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(SearchEntityPortlet.class);
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
			throws PortletException, IOException {
		//UC-PGM-CRM-033_ESPRD00624909_09jun2011
		//System.err.println("from serchentti doviw:::");
		if(request.getPortletSession().getAttribute("EntitySearchMap2")!=null){
			request.setAttribute("EntitySearchMap2",request.getPortletSession().getAttribute("EntitySearchMap2"));
			request.getPortletSession().setAttribute("EntitySearchMap2",null);
			//System.err.println("---------------------.");
		}
		//EOf UC-PGM-CRM-033_ESPRD00624909_09jun2011
		super.doView(request, response);
	}

	
	/**
	 * When the user clicks on Help Icon of Portlet menu, this method will be
	 * inovked.
	 */
	public void doHelp(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		
		
			request.getPortletSession().setAttribute(
					"com.ibm.faces.portlet.page.help", "/jsp/searchentity/searchEntityHelp.jsp");
		
		
		super.doHelp(request, response);
	}

	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

		
//		String actionName = request.getParameter("SrchCREntSrch_ACTION");
//		logger
//				.debug("SearchEntityPortlet :: processAction Receiving actionName from Search Correspondence"
//						+ actionName);
//		//UC-PGM-CRM-033_ESPRD00624909_09jun2011
//		if(request.getPortletSession().getAttribute("EntitySearchMap2")!=null){
//			request.setAttribute("EntitySearchMap2",request.getPortletSession().getAttribute("EntitySearchMap2"));
//			request.getPortletSession().setAttribute("EntitySearchMap2",null);
//		}
//		//EOf UC-PGM-CRM-033_ESPRD00624909_09jun2011
//
//		if (actionName != null && actionName.equals("SrchCrEntSrch_TARGET_ACTION")) {
//
//			if (request.getAttribute("SearchCrsToSrchEntityVO") != null) {
//
//				//UC-PGM-CRM-033_ESPRD00624909_09jun2011
//				/*CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = (CorrespondenceSearchCriteriaVO) request
//						.getAttribute("SearchCrsToSrchEntityVO");*/
//				Object reqObject =  request.getAttribute("SearchCrsToSrchEntityVO");
//				CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = null;
//				CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO =null;
//				if(reqObject instanceof CorrespondenceSearchCriteriaVO){
//					correspondenceSearchCriteriaVO= (CorrespondenceSearchCriteriaVO)reqObject;
//				}else if(reqObject instanceof CaseRecordSearchCriteriaVO){
//					caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO)reqObject;
//				}
//				//System.err.println("CaseRecordSearchCriteriaVO: "+caseRecordSearchCriteriaVO);
//				if(caseRecordSearchCriteriaVO!=null){
//					request.getPortletSession().setAttribute(
//							"CaseRecordSearchCriteriaVO",
//							caseRecordSearchCriteriaVO);
//					request.getPortletSession().setAttribute(
//							"portletName","Search Case");
//				}
//				//EOf UC-PGM-CRM-033_ESPRD00624909_09jun2011
//				if (correspondenceSearchCriteriaVO != null) {
//					request.getPortletSession().setAttribute(
//							"CorrespondenceSearchCriteriaVO",
//							correspondenceSearchCriteriaVO);
//					if(correspondenceSearchCriteriaVO.isReassginCr())
//					{
//						request.getPortletSession().setAttribute(
//								"portletName","reassignCorrespondence");
//					}else if(correspondenceSearchCriteriaVO.isFromEDMSflag()){
//						request.getPortletSession().setAttribute(
//								"portletName","EntitySearchFromEDMS");
//					}else{
//						request.getPortletSession().setAttribute(
//							"portletName","Search Correspondence");
//					}
//				}
//			}
//		}
//		
//		/*  ESPRD00333432 - MERGE LEFT CODE TAKEN BACK  */
//		
//		String actionName0 = request.getParameter("ACTION_NAME");
//		
//		System.err.println("SearchEntityPortlet :: processAction Receiving actionName from Search Case"
//						+ actionName0);
//String actionName2 = request.getParameter("ACTION_NAME2");
//		
//		System.err.println("SearchEntityPortlet :: processAction Receiving actionName from Search Case"
//						+ actionName2);
//		if ((actionName0 != null && actionName0.equals("MyName"))||(actionName2 != null && actionName2.equals("MyName2"))) {
//		
//			if (request.getAttribute("MyParam") != null) {
//				
//				CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO =null;
//				caserecordSearchCriteriaVO=(CaseRecordSearchCriteriaVO) request
//						.getAttribute("MyParam");
//				caserecordSearchCriteriaVO=(CaseRecordSearchCriteriaVO) request
//				.getAttribute("MyParam2");
//				
//				if (( caserecordSearchCriteriaVO != null) && 
//						( (caserecordSearchCriteriaVO.getEntityType()!=null && !caserecordSearchCriteriaVO.getEntityType().trim().equals("")) ||
//						(caserecordSearchCriteriaVO.getEntityIDType()!=null && !caserecordSearchCriteriaVO.getEntityIDType().trim().equals("") ) ||
//						(caserecordSearchCriteriaVO.getEntityId()!=null && !caserecordSearchCriteriaVO.getEntityId().trim().equals(""))) ) {
//					caserecordSearchCriteriaVO.setEntityType(caserecordSearchCriteriaVO.getEntityType());
//					caserecordSearchCriteriaVO.setEntityIDType(caserecordSearchCriteriaVO.getEntityIDType());
//					caserecordSearchCriteriaVO.setEntityId(caserecordSearchCriteriaVO.getEntityId());
//					logger.info(" EntityId 111111111  "  + caserecordSearchCriteriaVO.getEntityId());
//					logger.info(" EntityIDType22222222  "  + caserecordSearchCriteriaVO.getEntityIDType());
//					logger.info(" EntityType3333333 "  + caserecordSearchCriteriaVO.getEntityType());
//					request.getPortletSession().setAttribute("CaseRecordSearchCriteriaVO",caserecordSearchCriteriaVO);
//					request.getPortletSession().setAttribute("portletName","Search Case");
//					request.getPortletSession().setAttribute("typeOfEntity","Entity");
//		
//				}else if((caserecordSearchCriteriaVO != null) && 						
//						((caserecordSearchCriteriaVO.getAdditionalEntityType()!=null && !caserecordSearchCriteriaVO.getAdditionalEntityType().trim().equals("")) || 
//						(caserecordSearchCriteriaVO.getAdditionalEntityIDType()!=null && !caserecordSearchCriteriaVO.getAdditionalEntityIDType().trim().equals(""))|| 
//						(caserecordSearchCriteriaVO.getAdditionalEntityID()!=null && !caserecordSearchCriteriaVO.getAdditionalEntityID().trim().equals("")) )){
//					
//					caserecordSearchCriteriaVO.setAdditionalEntityID(caserecordSearchCriteriaVO.getAdditionalEntityID());
//					caserecordSearchCriteriaVO.setAdditionalEntityIDType(caserecordSearchCriteriaVO.getAdditionalEntityIDType());
//					caserecordSearchCriteriaVO.setAdditionalEntityType(caserecordSearchCriteriaVO.getAdditionalEntityType());
//				
//										
//					request.getPortletSession().setAttribute("CaseRecordSearchCriteriaVO",caserecordSearchCriteriaVO);
//					request.getPortletSession().setAttribute("portletName","Search Case");
//					request.getPortletSession().setAttribute("typeOfEntity","");				
//				}
////				ESPRD0033432_20112009
//				else{
//					request.getPortletSession().setAttribute("CaseRecordSearchCriteriaVO",caserecordSearchCriteriaVO);
//					request.getPortletSession().setAttribute("portletName","Search Case");
//					request.getPortletSession().setAttribute("typeOfEntity","");
//				}
//				
//			}
//		}
//
//		
//		/* END */
//		
//		String actionName6 = request.getParameter("ENTYSRCHACTION_NAME");
//		logger.info(" actionName6  "   +  actionName6);
//		logger
//				.debug("SearchEntityPortlet :: processAction Receiving actionName from Search Case"
//						+ actionName6);
//		logger.info(" actionName6     "   +  actionName6);
//		if (actionName6 != null && actionName6.equals("EntSrch")) {
//		
//			if (request.getAttribute("CaseSearchVO") != null) {
//				
//				CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO =null;
//				caserecordSearchCriteriaVO=(CaseRecordSearchCriteriaVO) request
//						.getAttribute("CaseSearchVO");
//				
//				if (( caserecordSearchCriteriaVO != null) && 
//						( (caserecordSearchCriteriaVO.getEntityType()!=null && !caserecordSearchCriteriaVO.getEntityType().trim().equals("")) ||
//						(caserecordSearchCriteriaVO.getEntityIDType()!=null && !caserecordSearchCriteriaVO.getEntityIDType().trim().equals("") ) ||
//						(caserecordSearchCriteriaVO.getEntityId()!=null && !caserecordSearchCriteriaVO.getEntityId().trim().equals(""))) ) {
//					caserecordSearchCriteriaVO.setEntityType(caserecordSearchCriteriaVO.getEntityType());
//					caserecordSearchCriteriaVO.setEntityIDType(caserecordSearchCriteriaVO.getEntityIDType());
//					caserecordSearchCriteriaVO.setEntityId(caserecordSearchCriteriaVO.getEntityId());
//					logger.info(" EntityId 111111111  "  + caserecordSearchCriteriaVO.getEntityId());
//					logger.info(" EntityIDType22222222  "  + caserecordSearchCriteriaVO.getEntityIDType());
//					logger.info(" EntityType3333333 "  + caserecordSearchCriteriaVO.getEntityType());
//					request.getPortletSession().setAttribute("CaseRecordSearchCriteriaVO",caserecordSearchCriteriaVO);
//					request.getPortletSession().setAttribute("portletName","Log Case");
//					request.getPortletSession().setAttribute("typeOfEntity","Entity");
//		
//				}else if((caserecordSearchCriteriaVO != null) && 						
//						((caserecordSearchCriteriaVO.getAdditionalEntityType()!=null && !caserecordSearchCriteriaVO.getAdditionalEntityType().trim().equals("")) || 
//						(caserecordSearchCriteriaVO.getAdditionalEntityIDType()!=null && !caserecordSearchCriteriaVO.getAdditionalEntityIDType().trim().equals(""))|| 
//						(caserecordSearchCriteriaVO.getAdditionalEntityID()!=null && !caserecordSearchCriteriaVO.getAdditionalEntityID().trim().equals("")) )){
//					
//					caserecordSearchCriteriaVO.setAdditionalEntityID(caserecordSearchCriteriaVO.getAdditionalEntityID());
//					caserecordSearchCriteriaVO.setAdditionalEntityIDType(caserecordSearchCriteriaVO.getAdditionalEntityIDType());
//					caserecordSearchCriteriaVO.setAdditionalEntityType(caserecordSearchCriteriaVO.getAdditionalEntityType());
//				
//										
//					request.getPortletSession().setAttribute("CaseRecordSearchCriteriaVO",caserecordSearchCriteriaVO);
//					request.getPortletSession().setAttribute("portletName","Log Case");
//					request.getPortletSession().setAttribute("typeOfEntity","");				
//				} else{
//					request.getPortletSession().setAttribute("CaseRecordSearchCriteriaVO",caserecordSearchCriteriaVO);
//					request.getPortletSession().setAttribute("portletName","Log Case");
//					request.getPortletSession().setAttribute("typeOfEntity","");
//				}
//				
//			}
//		}
//
//		
//		
//		
//		String myaction = request.getParameter("LogCrsSrchEntity_ACTION_NAME");
//
//		logger
//				.debug("SearchEntityPortlet :: processAction Receiving actionName from Log Correspondence"
//						+ myaction);
//
//		if (myaction != null && myaction.equals("LogCrsSrchEnt_TARGET_ACTION")) {
//
//			if (request.getAttribute("crsSrchCriVO") != null) {
//
//				CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = (CorrespondenceSearchCriteriaVO) request
//						.getAttribute("crsSrchCriVO");
//
//				logger
//						.debug("SearchEntityPortlet :: processAction CorrespondenceSearchCriteriaVO from Log cores"
//								+ correspondenceSearchCriteriaVO);
//
//				if (correspondenceSearchCriteriaVO != null) {
//					request.getPortletSession().setAttribute(
//							"CorrespondenceSearchCriteriaVO",
//							correspondenceSearchCriteriaVO);
//					request.getPortletSession().setAttribute(
//							"portletName",
//							"Correspondence");
//				}
//			}
//		}    		

		// Otherwise invoke default behaviour
		super.processAction(request, response);
		
		HashMap crLogMap = (HashMap) request.getAttribute("srcEntityMap");
		if (crLogMap != null) {
			
			WireEventDataCarrierVO mapData = new WireEventDataCarrierVO(); 
			mapData.setMapData(crLogMap);	
			response.setEvent("EntitySearchToCRLog", mapData);
		}
		HashMap crSearchMap = (HashMap) request.getAttribute("EntitySearchMap");
		if (crSearchMap != null) {
			
			WireEventDataCarrierVO mapData = new WireEventDataCarrierVO(); 
			mapData.setMapData(crSearchMap);
			response.setEvent("EntitySearchToCRSearch", mapData);
		}
		// Code Added for WSRP IPC FIX from Entity Search to Reassign Correspondence
		
		HashMap reassignCRSearchMap = (HashMap) request.getAttribute("EntitySearchMap1");
		if (reassignCRSearchMap != null) {
			WireEventDataCarrierVO mapData = new WireEventDataCarrierVO(); 
			mapData.setMapData(reassignCRSearchMap);
			response.setEvent("SRCSearchEntityReassignCR", mapData);
		}
		
//		EntitySearchCriteriaVO entitySearchCriteriaVO = (EntitySearchCriteriaVO)request
//				.getAttribute("EntitySearchMap2");
//		if (entitySearchCriteriaVO != null) {
//			response.setEvent("EntitySearchToSearchCase", crSearchMap);
//		}
		//Commented for the defect ESPRD00802462
		/*HashMap caseLogMap = (HashMap) request
				.getAttribute("EntSrchLogCaseMap");
		if (caseLogMap != null) {
			WireEventDataCarrierVO wireEventDataCarrierVO = new WireEventDataCarrierVO();
			wireEventDataCarrierVO.setMapData(caseLogMap);
			response.setEvent("EntitySearchToLogCase", wireEventDataCarrierVO);
		}*/
		//Added for IPC for Defect ESPRD00802462 starts
		HashMap caseLogMap = (HashMap) request
				.getAttribute("EntSrchLogCaseCRMap");
		if (caseLogMap != null) {
			WireEventDataCarrierVO wireEventDataCarrierVO = new WireEventDataCarrierVO();
			wireEventDataCarrierVO.setMapData(caseLogMap);
			response.setEvent("EntitySearchToCRLogcaseSearch", wireEventDataCarrierVO);
		}
		
		EntitySearchCriteriaVO entitySearchCriteriaVO2 = (EntitySearchCriteriaVO) request.getAttribute("EntSrchLogCaseMap");
		if (entitySearchCriteriaVO2 != null) {
			
			response.setEvent("EntitySearchToLogCase", entitySearchCriteriaVO2);
		}
		//Added for IPC for Defect ESPRD00802462 ends
		String maintainEntity = (String) request
				.getAttribute("maintainEntityId");
		if (maintainEntity != null) {
			response.setEvent("EntitySearchToMaintainEntity", maintainEntity);
		}
		String tplCarrierDetails = (String) request
				.getAttribute("tplCarrierId");
		if (tplCarrierDetails != null) {
			response.setEvent("EntitySearchToTPLCarrierDetails",
					tplCarrierDetails);
		}
		String providerMaintainance = (String) request
				.getAttribute("providerEntityId");
		if (providerMaintainance != null) {
			response.setEvent("EntitySearchToProviderMnts",
					providerMaintainance);
		}
		
		// Following code has been commented for Defect # ESPRD00779982 - No longer used this event from Entity Search to Member Details
		
		/*String memberDetails = (String) request
				.getAttribute("EntityMemberDetail");
		if (memberDetails != null) {
			response.setEvent("EntitySearchToMemberDetails", memberDetails);
		}*/
		
		
		
		String entityMemberDetails = (String) request
				.getAttribute("EntityMemberDetail");
		if (entityMemberDetails != null) {
			response.setEvent(
					"ContactEntitySearchEntityMemDetailsToMemInfMnts",
					entityMemberDetails);
		}
		
		HashMap contactEntitySearchToSearchDocumentRepositoryMap = (HashMap) request
				.getAttribute("EntitySearchMap3");
		if (contactEntitySearchToSearchDocumentRepositoryMap != null) {
			WireEventDataCarrierVO wireEventDataCarrierVO = new WireEventDataCarrierVO();
			wireEventDataCarrierVO.setMapData(contactEntitySearchToSearchDocumentRepositoryMap);
			
			response.setEvent(
					"ContactEntitySearchToSearchDocumentRepositoryEvent",
					wireEventDataCarrierVO);
		}
		String contactEntitySearchToTradingPartnerMaintenance = (String) request
				.getAttribute("tradingPartnerMID");
		if (contactEntitySearchToTradingPartnerMaintenance != null) {
			response.setEvent("ContactEntitySearchToTradingPartnerMaintenance",
					contactEntitySearchToTradingPartnerMaintenance);
		}
		String contactEntitySearchToTradingPartnerEnrollmentApplication = (String) request
				.getAttribute("tradingPartnerAppID");
		if (contactEntitySearchToTradingPartnerEnrollmentApplication != null) {
			response.setEvent(
					"ContactEntitySearchToTradingPartnerEnrollmentApplication",
					contactEntitySearchToTradingPartnerEnrollmentApplication);
		}
		String contactEntitySearchToProviderEnrollmentApplication = (String) request
				.getAttribute("providerSysID");
		if (contactEntitySearchToProviderEnrollmentApplication != null) {
			response.setEvent(
					"ContactEntitySearchToProviderEnrollmentApplication",
					contactEntitySearchToProviderEnrollmentApplication);
		}
		String contactEntitySearchtoTPLPolicyDetail = (String) request
				.getAttribute("CarrierID");
		if (contactEntitySearchtoTPLPolicyDetail != null) {
			response.setEvent("ContactEntitySearchtoTPLPolicyDetail",
					contactEntitySearchtoTPLPolicyDetail);
		}
		String CMEntitySearchToPrvMnt = (String) request
				.getAttribute("providerEntityId");
		if (CMEntitySearchToPrvMnt != null) {
			response.setEvent("ContactEntitySearchToProviderMnt",
					CMEntitySearchToPrvMnt);
		}
		EntitySearchCriteriaVO entitySearchCriteriaVO = (EntitySearchCriteriaVO) request.getAttribute("EntitySearchMap2");
		if (entitySearchCriteriaVO != null) {
			
			response.setEvent("PublishEntitySearchToSearchCase", entitySearchCriteriaVO);
		}
		String tpEnrollment = (String) request.getAttribute("tradingPartnerAppID");
		if (tpEnrollment != null) {
			response.setEvent("ContactEntitysearchToTPEnrollment", tpEnrollment);
		}
		
		/*Below IPC code was added for fixing the defect - ESPRD00779990.
    	 * Later we got an alternative solution to navigate to external enrollment page.
    	 * As IPC Code is not required , below  code is commented.
    	 */
		
		/*String entitySearchToProviderEnrollmentPortlet = (String) request.getAttribute("providerSysIDforEnrol");
		
		if(entitySearchToProviderEnrollmentPortlet != null)
		{
			response.setEvent("EntitySearchToPrvEnrollmentPortlet",entitySearchToProviderEnrollmentPortlet);
		}*/
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
		
		
		
		Event event = request.getEvent();
		
		if (event.getName().toString().equals("EntitySearchFromLogCR")) {
			CorrespondenceSearchCriteriaVO crSearchCriteriaVO = (CorrespondenceSearchCriteriaVO) event
					.getValue();

			if (crSearchCriteriaVO != null) {
				request.setAttribute(
						"CorrespondenceSearchCriteriaVO", crSearchCriteriaVO);
			}
			
			actionName = "LogCrsSrchEnt_TARGET_ACTION";
		} else if (event.getName().toString().equals("EntitySearchFromReassignCR")
				|| event.getName().toString()
						.equals("EntitySearchFromCRSearch")
				|| event.getName().toString().equals("EntitySearchFromLogCR")) {
			CorrespondenceSearchCriteriaVO crSearchCriteriaVO = (CorrespondenceSearchCriteriaVO) event
					.getValue();
		
			if (crSearchCriteriaVO != null) {
				request.setAttribute(
						"CorrespondenceSearchCriteriaVO", crSearchCriteriaVO);
			}
			
			actionName = "SrchCrEntSrch_TARGET_ACTION";
		} else if (event.getName().toString().equals(
				"EntitySearchFromCaseDetails")
				|| event.getName().toString().equals("EntitySearchFromLogCase")) {
			CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) event
					.getValue();
		
			if (caseRecordSearchCriteriaVO != null) {
				request.setAttribute(
						"CaseRecordSearchCriteriaVO",
						caseRecordSearchCriteriaVO);
			}
			actionName = "EntSrch";
		} else if (event.getName().toString().equals(
				"EntitySearchFromMaintainEntity")) {
			String maintainEntity = (String) event.getValue();
			if (maintainEntity != null) {
				request.setAttribute("MaintainEntity",
						maintainEntity);
			}
		} else if (event.getName().toString().equals(
				"FromSearchDocumentRepositoryToContactEntitySearch")) {
			CorrespondenceSearchCriteriaVO documentRepository = (CorrespondenceSearchCriteriaVO) event
					.getValue();
			if (documentRepository != null) {
				request.setAttribute(
						"SearchCrsToSrchEntityVO", documentRepository);
			}
			actionName = "SrchCrEntSrch_TARGET_ACTION_FromEDMS";
		} else if (event.getName().toString().equals(
				"CaseEntitySearchFromcaseDetails")) {
			CaseRecordSearchCriteriaVO caseDetails = (CaseRecordSearchCriteriaVO) event
					.getValue();
			if (caseDetails != null) {
				request.setAttribute(
						"CaseRecordSearchCriteriaVO", caseDetails);
			}
			actionName = "SrchCaseEntSrch_TARGET_ACTION";
		}
		
		actionUrl(request, response, actionName);
		
	}
	
	public void actionUrl(EventRequest request, EventResponse response,
			String actionName) {
		// String actionName = request.getParameter("SrchCREntSrch_ACTION");
		
		// UC-PGM-CRM-033_ESPRD00624909_09jun2011
		if (request.getPortletSession().getAttribute("EntitySearchMap2") != null) {
			request.setAttribute("EntitySearchMap2", request
					.getPortletSession().getAttribute("EntitySearchMap2"));
			request.getPortletSession().setAttribute("EntitySearchMap2", null);
		}
		// EOf UC-PGM-CRM-033_ESPRD00624909_09jun2011

		if (actionName != null
				&& actionName.equals("SrchCrEntSrch_TARGET_ACTION")) {

			if (request.getAttribute("CorrespondenceSearchCriteriaVO") != null) {

				// UC-PGM-CRM-033_ESPRD00624909_09jun2011
				/*
				 * CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO
				 * = (CorrespondenceSearchCriteriaVO) request
				 * .getAttribute("SearchCrsToSrchEntityVO");
				 */
				Object reqObject = request
						.getAttribute("CorrespondenceSearchCriteriaVO");
				CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = null;
				CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = null;
				if (reqObject instanceof CorrespondenceSearchCriteriaVO) {
					correspondenceSearchCriteriaVO = (CorrespondenceSearchCriteriaVO) reqObject;
				} else if (reqObject instanceof CaseRecordSearchCriteriaVO) {
					caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) reqObject;
				}
				// System.err.println("CaseRecordSearchCriteriaVO: "+caseRecordSearchCriteriaVO);
				if (caseRecordSearchCriteriaVO != null) {
					request.getPortletSession().setAttribute(
							"CaseRecordSearchCriteriaVO",
							caseRecordSearchCriteriaVO);
					request.getPortletSession().setAttribute("portletName",
							"Search Case");
				}
				// EOf UC-PGM-CRM-033_ESPRD00624909_09jun2011
				if (correspondenceSearchCriteriaVO != null) {
					request.getPortletSession().setAttribute(
							"CorrespondenceSearchCriteriaVO",
							correspondenceSearchCriteriaVO);
					if (correspondenceSearchCriteriaVO.isReassginCr()) {
						request.getPortletSession().setAttribute("portletName",
								"reassignCorrespondence");
					} else if (correspondenceSearchCriteriaVO.isFromEDMSflag()) {
						request.getPortletSession().setAttribute("portletName",
								"EntitySearchFromEDMS");
					} else if (correspondenceSearchCriteriaVO.isFromLogCase()) {
						request.getPortletSession().setAttribute("portletName",
						"LogCaseAssociatedCR");
					}else {
						request.getPortletSession().setAttribute("portletName",
								"Search Correspondence");
					}
				}
			}
		}

		/* ESPRD00333432 - MERGE LEFT CODE TAKEN BACK */


		if (actionName != null && actionName.equals("SrchCaseEntSrch_TARGET_ACTION")) {
			
			if (request.getAttribute("CaseRecordSearchCriteriaVO") != null) {

				CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO = null;
				caserecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) request
						.getAttribute("CaseRecordSearchCriteriaVO");

				if ((caserecordSearchCriteriaVO != null)
						&& ((caserecordSearchCriteriaVO.getEntityType() != null && !caserecordSearchCriteriaVO
								.getEntityType().trim().equals(""))
								|| (caserecordSearchCriteriaVO
										.getEntityIDType() != null && !caserecordSearchCriteriaVO
										.getEntityIDType().trim().equals("")) || (caserecordSearchCriteriaVO
								.getEntityId() != null && !caserecordSearchCriteriaVO
								.getEntityId().trim().equals("")))) {
					caserecordSearchCriteriaVO
							.setEntityType(caserecordSearchCriteriaVO
									.getEntityType());
					caserecordSearchCriteriaVO
							.setEntityIDType(caserecordSearchCriteriaVO
									.getEntityIDType());
					caserecordSearchCriteriaVO
							.setEntityId(caserecordSearchCriteriaVO
									.getEntityId());
					
					request.getPortletSession().setAttribute(
							"CaseRecordSearchCriteriaVO",
							caserecordSearchCriteriaVO);
					request.getPortletSession().setAttribute("portletName",
							"Search Case");
					request.getPortletSession().setAttribute("typeOfEntity",
							"Entity");

				} else if ((caserecordSearchCriteriaVO != null)
						&& ((caserecordSearchCriteriaVO
								.getAdditionalEntityType() != null && !caserecordSearchCriteriaVO
								.getAdditionalEntityType().trim().equals(""))
								|| (caserecordSearchCriteriaVO
										.getAdditionalEntityIDType() != null && !caserecordSearchCriteriaVO
										.getAdditionalEntityIDType().trim()
										.equals("")) || (caserecordSearchCriteriaVO
								.getAdditionalEntityID() != null && !caserecordSearchCriteriaVO
								.getAdditionalEntityID().trim().equals("")))) {

					caserecordSearchCriteriaVO
							.setAdditionalEntityID(caserecordSearchCriteriaVO
									.getAdditionalEntityID());
					caserecordSearchCriteriaVO
							.setAdditionalEntityIDType(caserecordSearchCriteriaVO
									.getAdditionalEntityIDType());
					caserecordSearchCriteriaVO
							.setAdditionalEntityType(caserecordSearchCriteriaVO
									.getAdditionalEntityType());

					request.getPortletSession().setAttribute(
							"CaseRecordSearchCriteriaVO",
							caserecordSearchCriteriaVO);
					request.getPortletSession().setAttribute("portletName",
							"Search Case");
					request.getPortletSession()
							.setAttribute("typeOfEntity", "");
				}
				// ESPRD0033432_20112009
				else {
					request.getPortletSession().setAttribute(
							"CaseRecordSearchCriteriaVO",
							caserecordSearchCriteriaVO);
					request.getPortletSession().setAttribute("portletName",
							"Search Case");
					request.getPortletSession()
							.setAttribute("typeOfEntity", "");
				}

			}
		}

		if (actionName != null && actionName.equals("EntSrch")) {
			if (request.getAttribute("CaseRecordSearchCriteriaVO") != null) {

				CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO = null;
				caserecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) request
						.getAttribute("CaseRecordSearchCriteriaVO");

				if ((caserecordSearchCriteriaVO != null)
						&& ((caserecordSearchCriteriaVO.getEntityType() != null && !caserecordSearchCriteriaVO
								.getEntityType().trim().equals(""))
								|| (caserecordSearchCriteriaVO
										.getEntityIDType() != null && !caserecordSearchCriteriaVO
										.getEntityIDType().trim().equals("")) || (caserecordSearchCriteriaVO
								.getEntityId() != null && !caserecordSearchCriteriaVO
								.getEntityId().trim().equals("")))) {
					caserecordSearchCriteriaVO
							.setEntityType(caserecordSearchCriteriaVO
									.getEntityType());
					caserecordSearchCriteriaVO
							.setEntityIDType(caserecordSearchCriteriaVO
									.getEntityIDType());
					caserecordSearchCriteriaVO
							.setEntityId(caserecordSearchCriteriaVO
									.getEntityId());
					logger.info(" EntityId 111111111  "
							+ caserecordSearchCriteriaVO.getEntityId());
					logger.info(" EntityIDType22222222  "
							+ caserecordSearchCriteriaVO.getEntityIDType());
					logger.info(" EntityType3333333 "
							+ caserecordSearchCriteriaVO.getEntityType());
					request.getPortletSession().setAttribute(
							"CaseRecordSearchCriteriaVO",
							caserecordSearchCriteriaVO);
					request.getPortletSession().setAttribute("portletName",
							"Log Case");
					request.getPortletSession().setAttribute("typeOfEntity",
							"Entity");

				} else if ((caserecordSearchCriteriaVO != null)
						&& ((caserecordSearchCriteriaVO
								.getAdditionalEntityType() != null && !caserecordSearchCriteriaVO
								.getAdditionalEntityType().trim().equals(""))
								|| (caserecordSearchCriteriaVO
										.getAdditionalEntityIDType() != null && !caserecordSearchCriteriaVO
										.getAdditionalEntityIDType().trim()
										.equals("")) || (caserecordSearchCriteriaVO
								.getAdditionalEntityID() != null && !caserecordSearchCriteriaVO
								.getAdditionalEntityID().trim().equals("")))) {

					caserecordSearchCriteriaVO
							.setAdditionalEntityID(caserecordSearchCriteriaVO
									.getAdditionalEntityID());
					caserecordSearchCriteriaVO
							.setAdditionalEntityIDType(caserecordSearchCriteriaVO
									.getAdditionalEntityIDType());
					caserecordSearchCriteriaVO
							.setAdditionalEntityType(caserecordSearchCriteriaVO
									.getAdditionalEntityType());

					request.getPortletSession().setAttribute(
							"CaseRecordSearchCriteriaVO",
							caserecordSearchCriteriaVO);
					request.getPortletSession().setAttribute("portletName",
							"Log Case");
					request.getPortletSession()
							.setAttribute("typeOfEntity", "");
				} else {
					request.getPortletSession().setAttribute(
							"CaseRecordSearchCriteriaVO",
							caserecordSearchCriteriaVO);
					request.getPortletSession().setAttribute("portletName",
							"Log Case");
					request.getPortletSession()
							.setAttribute("typeOfEntity", "");
				}

			}
		}
		//Modified the existing code for wiring issue between Entity Search to Log Correspondence
		
		if (actionName != null && actionName.equals("LogCrsSrchEnt_TARGET_ACTION")) {

			if (request.getAttribute("CorrespondenceSearchCriteriaVO") != null) {

				CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = (CorrespondenceSearchCriteriaVO) request
						.getAttribute("CorrespondenceSearchCriteriaVO");

				if (correspondenceSearchCriteriaVO != null) {
					request.getPortletSession().setAttribute(
							"CorrespondenceSearchCriteriaVO",
							correspondenceSearchCriteriaVO);
					request.getPortletSession().setAttribute("portletName",
							"Correspondence");
				}
			}
		}
		if(actionName != null && actionName.equals("SrchCrEntSrch_TARGET_ACTION_FromEDMS")){
			CorrespondenceSearchCriteriaVO searchCrsToSrchEntityVO = (CorrespondenceSearchCriteriaVO) request.getAttribute("SearchCrsToSrchEntityVO");
			System.out.println("CorrespondenceSearchCriteriaVO---------from EDMS"+searchCrsToSrchEntityVO.isFromEDMSflag());
			request.getPortletSession().setAttribute(
					"searchCrsToSrchEntityVO",
					searchCrsToSrchEntityVO);
			if(searchCrsToSrchEntityVO.isFromEDMSflag()){
				request.getPortletSession().setAttribute("portletName",
				"EntitySearchFromEDMS");				
			}
		}
	}

}
