/*
 * Created on Feb 15, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.acs.enterprise.common.view.vo.WireEventDataCarrierVO;
import com.acs.enterprise.mmis.operations.common.vo.ClaimInquirySearchResultsVO;
import com.ibm.faces20.portlet.FacesPortlet;

/**
 * @author Wipro
 */
public class AddAppealsPortlet
        extends FacesPortlet
{
    /** Creates an instance of the logger. * */
    /*private transient EnterpriseLogger log = EnterpriseLogFactory
            .getLogger(getClass().getName());*/
	private static final EnterpriseLogger log = EnterpriseLogFactory
    .getLogger(AddAppealsPortlet.class);

    /**
     * Constructor AddAppealsPortlet.
     */
    public AddAppealsPortlet()
    {
        super();
    }

    public void actionUrl(EventRequest request, EventResponse response,
			String actionName) {

        // Reterive source action name
       /* String actionName = (String) request
                .getParameter(MaintainContactManagementUIConstants.REC_ADD_APL_DTL_ACT);
        log.debug("In AddAppealsPortlet:actionName: " + actionName);*/
        //request.getPortletSession().setAttribute("sessionObject", "true");
        if (actionName != null)
        {
            if (actionName
                    .equals(MaintainContactManagementUIConstants.REC_ADD_APL_DTL_ACT_NAME))
            {

                request
                        .getPortletSession()
                        .setAttribute(
                                MaintainContactManagementUIConstants.ADD_APL_DET,
                                request
                                        .getAttribute(MaintainContactManagementUIConstants.ADD_APL_DET));

            }

        }
}
	public void processEvent(EventRequest request, EventResponse response)
	throws PortletException, IOException {
		String actionName= null;
		Event event = request.getEvent();
		if (event.getName().toString().equals("CaseEntityDetails")) {
			String maintainSearchId = (String) request.getEvent().getValue();
			 if (log.isDebugEnabled()) {
				log.debug(" maintainSearchId :  " + maintainSearchId);
			}
		
			if (maintainSearchId != null) {
				request.setAttribute("CaseEntityDetails",
						maintainSearchId);
			}
			actionName="receiveCaseEntityDetails";
		} else if (event.getName().toString().equals("LogCaseAddAppealDetailsDataType")) {
			CommonCMCaseDetailsVO commonCMCaseDetailsVO1 = (CommonCMCaseDetailsVO) event
					.getValue();
		
			if (commonCMCaseDetailsVO1 != null) {
				request.setAttribute("AddAppealDetails",
						commonCMCaseDetailsVO1);
			}
			actionName="receiveAddAppealDetailsAction";
		}
		actionUrl(request, response, actionName);
	}    
    /**
     * mehtod calls do view method
     * 
     * @param request
     *            RenderRequest
     * @param response
     *            RenderResponse
     * @throws PortletException
     *             if any Portlet Exception occurs
     * @throws IOException
     *             if any IO Exception occurs
     */
    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {

        String code = null;

        if(log.isDebugEnabled()){
        log.debug("In do view of Add Appeals Portlet--->");
        }
        Object addAppealsObj = request.getPortletSession().getAttribute(
                MaintainContactManagementUIConstants.ADD_APL_DET);

        if (addAppealsObj != null)
        {
            request.setAttribute(
                    MaintainContactManagementUIConstants.ADD_APL_DET,
                    addAppealsObj);

            if (addAppealsObj instanceof CommonCMCaseDetailsVO)
            {
                CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) addAppealsObj;
                code = commonCMCaseDetailsVO.getEntityType();
                request.getPortletSession().setAttribute("code", code);
            }

            request.getPortletSession().removeAttribute(
                    MaintainContactManagementUIConstants.ADD_APL_DET);
        }

        /** Code for Dynamic Portlet title change */
        /*
         * String path = (String) request .getPortletSession() .getAttribute(
         * MaintainContactManagementUIConstants.COM_IBM_FACES_PORT_PG_VIEW);
         */

        String path = (String) request
                .getParameter("javax.servlet.include.path_info");
        if (code == null)
        {
            code = (String) request.getPortletSession().getAttribute("code");
        }
        String title = "";
      //ipc related DEFECT_ESPRD00770754_UIS-PGM-CRM-065_21-03-12
        /*if ((MaintainContactManagementUIConstants.ADD_APLS_JSP_PATH
                .equals(path)))
        {*/
            if (code != null)
            {
                if (code
                        .equalsIgnoreCase(MaintainContactManagementUIConstants.ENT_TYPE_M))
                {
                    response
                            .setTitle(MaintainContactManagementUIConstants.MEM_ADD_APL_TITLE);
                    title = MaintainContactManagementUIConstants.MEM_ADD_APL_TITLE;
                }
                else if (code
                        .equalsIgnoreCase(MaintainContactManagementUIConstants.ENT_TYPE_P))
                {
                    response
                            .setTitle(MaintainContactManagementUIConstants.PROV_ADD_APL_TITLE);
                    title = MaintainContactManagementUIConstants.PROV_ADD_APL_TITLE;
                }else  {
                	
            response
                    .setTitle(MaintainContactManagementUIConstants.OTHER_ADD_APL_TITLE);
            title = MaintainContactManagementUIConstants.OTHER_ADD_APL_TITLE;
        }
                
            }
        //}
        if(log.isDebugEnabled()){
        log.debug("portlet title-->" + title);
        }
        super.doView(request, response);
    }
    //NFR_ESPRD00432533_UC-PGM-CRM-067_06MAY2010
    public void doHelp(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
    	if(log.isDebugEnabled()){
		log.debug("Provider/Member AddAppealsPortlet doHelp::::::::::::");
    	}
		//NFR_ESPRD00432533_UC-PGM-CRM-067_07MAY2010
		Object entityType = request.getPortletSession().getAttribute("Appeal_EntityType",PortletSession.APPLICATION_SCOPE);
		String actionURL;
		if(MaintainContactManagementUIConstants.ENT_TYPE_M.equals(entityType)){
			actionURL = "/jsp/appeals/Add_Appeals_Help.jsp";
			request.getPortletSession().setAttribute(
					"com.ibm.faces.portlet.page.help", actionURL);
		}else if(MaintainContactManagementUIConstants.ENT_TYPE_P.equals(entityType)){
			actionURL = "/jsp/appeals/ProviderAdd_Appeals_Help.jsp";
			request.getPortletSession().setAttribute(
					"com.ibm.faces.portlet.page.help", actionURL);
		}
		//EOF NFR_ESPRD00432533_UC-PGM-CRM-067_07MAY2010
		
		super.doHelp(request, response);
	}

	/**
		     * This method calls process action
		     * 
		     * @param request
		     *            ActionRequest
		     * @param response
		     *            ActionResponse
		     * @throws PortletException
		     *             if any portlet exception occurs
		     */
		    public void processAction(ActionRequest request, ActionResponse response)
		            throws PortletException
		    {
		
		        // Reterive source action name
		        String actionName = (String) request
		                .getParameter(MaintainContactManagementUIConstants.REC_ADD_APL_DTL_ACT);
		        //request.getPortletSession().setAttribute("sessionObject", "true");
		        if (actionName != null)
		        {
		            if (actionName
		                    .equals(MaintainContactManagementUIConstants.REC_ADD_APL_DTL_ACT_NAME))
		            {
		                request
		                        .getPortletSession()
		                        .setAttribute(
		                                MaintainContactManagementUIConstants.ADD_APL_DET,
		                                request
		                                        .getAttribute(MaintainContactManagementUIConstants.ADD_APL_DET));
		
		            }
		
		        }
		
		        // Otherwise invoke default behaviour
		        super.processAction(request, response);
		        if(log.isDebugEnabled()){
		        log.debug(" Add Appeal portlet...");
		        }
				CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
						.getAttribute("CaseDetails");
				if (commonCMCaseDetailsVO != null) {
					response.setEvent("LogCaseCMCaseSearchDataType",
							commonCMCaseDetailsVO);
				}
				String InternalServiceAuthID = (String) request
						.getAttribute("InternalServiceAuthID");
				if (InternalServiceAuthID != null) {
					response.setEvent("AddAppealToServicsAuthInquiry",
							InternalServiceAuthID);
				}
				ArrayList invokeDetails = (ArrayList) request
						.getAttribute("invokeDetails");
				if (invokeDetails != null) {
					WireEventDataCarrierVO invokeDetails1 = new WireEventDataCarrierVO();
					invokeDetails1.setWireData(invokeDetails);
					response.setEvent("AddAppealToInstitonalClaims", invokeDetails1);
				}
				// wsrp changes
				String recieveVOFromCM = (String) request.getAttribute("recieveVO");
				if (recieveVOFromCM != null) {
					response.setEvent("AddAppealsToServiceAuthDetail", recieveVOFromCM);
				}
				
				ArrayList professionalTCN = (ArrayList) request
						.getAttribute("profTcnInfo");
				if (professionalTCN != null) {
					WireEventDataCarrierVO professionalTCN1 = new WireEventDataCarrierVO();
					professionalTCN1.setWireData(professionalTCN);
					response.setEvent("AddAppealsToProfessionalTcn", professionalTCN1);
				}
				
				ArrayList voidReplace = (ArrayList) request
						.getAttribute("voidReplaceTcnInfo");
				if (voidReplace != null) {
					WireEventDataCarrierVO voidReplace1 = new WireEventDataCarrierVO();
					voidReplace1.setWireData(voidReplace);
					response.setEvent("AddAppealsToVoidReplaceTcn", voidReplace1);
				}
				
				ArrayList dentalclaim = (ArrayList) request
						.getAttribute("dentalTcnInfo");
				if (dentalclaim != null) {
					WireEventDataCarrierVO dentalclaim1 = new WireEventDataCarrierVO();
					dentalclaim1.setWireData(dentalclaim);
					response.setEvent("AddAppealToDentalClaim", dentalclaim1);
				}
				
				String crSearch = (String) request.getAttribute("CrspdGenAppealSK"); 
				if (crSearch != null) {
					response.setEvent("AddAppealsToCRSearch", crSearch);
				}
				
				String logCase = (String) request.getAttribute("voidReplaceTcnInfo");
				if (logCase != null) {
					response.setEvent("LogCaseToAddAppeals", logCase);
				}
				CommonCMCaseDetailsVO addAppealsToLogCase = (CommonCMCaseDetailsVO) request
				.getAttribute("CaseDetails");
				if (addAppealsToLogCase != null) {
					response.setEvent("SRCAddAppealsToLogCase", addAppealsToLogCase);
				}
				
				//WSRP Changes
				String caseTrackingAppealSK = (String) request.getAttribute("CaseTrackingAppealSK");
				if (caseTrackingAppealSK != null) {
					response.setEvent("AddAppealToSearchCase", caseTrackingAppealSK);
				}
		
			ClaimInquirySearchResultsVO claimInquirySearchResultsVO = (ClaimInquirySearchResultsVO)request.getAttribute("objInvokeDetails");
			if (claimInquirySearchResultsVO != null) {
				response.setEvent("AppealToClaimInquirySearch", claimInquirySearchResultsVO);
			}

	}
}
