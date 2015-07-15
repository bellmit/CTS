/*
 * Created on Feb 20, 2008 TODO To change the template for this generated file
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
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.acs.enterprise.common.view.vo.CommonProviderDetailsVO;
import com.acs.enterprise.common.view.vo.WireEventDataCarrierVO;
import com.acs.enterprise.mmis.operations.common.vo.ClaimInquirySearchResultsVO;
import com.ibm.faces20.portlet.FacesPortlet;
//import com.ibm.faces.webapp.FacesGenericPortlet;

/**
 * @author sivngan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class ProviderAppealsPortlet
        extends FacesPortlet
{
    
	private static final EnterpriseLogger log = EnterpriseLogFactory
    .getLogger(ProviderAppealsPortlet.class);

    
	/**
     * calls process Action
     * 
     * @param request
     *            ActionRequest
     * @param response
     *            ActionResponse
     * @throws PortletException
     *             if Portlet Exception occurs
     */
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException
    {
        String actionURL = "";
        request.getPortletSession().setAttribute(
                MaintainContactManagementUIConstants.SES_OBJ,
                MaintainContactManagementUIConstants.TRUE);

      
        final String actionName = (String) request
                .getParameter(MaintainContactManagementUIConstants.REC_PROV_APLS_ACT);
        if(log.isDebugEnabled()){
        log.debug("In ProviderAppealsPortlet:actionName: " + actionName);
        }

        if (actionName != null)
        {
            if (actionName
                    .equals(MaintainContactManagementUIConstants.REC_PROV_APL_ACT_NAME))
            {

                request
                        .getPortletSession()
                        .setAttribute(
                                MaintainContactManagementUIConstants.PROV_APLS,
                                request
                                        .getAttribute(MaintainContactManagementUIConstants.PROV_APLS));
                actionURL = MaintainContactManagementUIConstants.PROV_APLS_JSP_PATH;
            }
        }

        if (!StringUtils.isEmpty(actionURL))
        {
            request
                    .getPortletSession()
                    .setAttribute(
                            MaintainContactManagementUIConstants.COM_IBM_FACES_PORT_PG_VIEW,
                            actionURL);
        }
        
        super.processAction(request, response);
        ArrayList dentalTcnInfo = (ArrayList) request
		.getAttribute("dentalTcnInfo");
		if (dentalTcnInfo != null) {
			WireEventDataCarrierVO dentalTcnInfo2 = new WireEventDataCarrierVO();
			dentalTcnInfo2.setWireData(dentalTcnInfo);
			response.setEvent("ProviderAppealsDentalTcnInfo", dentalTcnInfo2);
    }

		 ArrayList instiTcnInfo = (ArrayList) request
			.getAttribute("instiTcnInfo");
		if (instiTcnInfo != null) {
			WireEventDataCarrierVO instiTcnInfo2 = new WireEventDataCarrierVO();
			instiTcnInfo2.setWireData(instiTcnInfo);
			response.setEvent("ProviderAppealsInstitutionalTcnInfo",
					instiTcnInfo2);
		}
		
		 ArrayList profTcnInfo = (ArrayList) request
			.getAttribute("profTcnInfo");
		if (profTcnInfo != null) {
			WireEventDataCarrierVO profTcnInfo2 = new WireEventDataCarrierVO();
			profTcnInfo2.setWireData(profTcnInfo);
			response.setEvent("ProviderAppealsProfessionalTcnInfo",
					profTcnInfo2);
		}
		
		 ArrayList voidReplaceTcnInfo = (ArrayList) request
			.getAttribute("voidReplaceTcnInfo");
		if (voidReplaceTcnInfo != null) {
			WireEventDataCarrierVO voidReplaceTcnInf2 = new WireEventDataCarrierVO();
			voidReplaceTcnInf2.setWireData(voidReplaceTcnInfo);
			response
					.setEvent("ProviderAppealsVoidReplaceTcnInfo", voidReplaceTcnInf2);
		}
		String recieveVO = (String) request.getAttribute("recieveVO");
		if (recieveVO != null) {
			response.setEvent("MemberAppealsServiceAuthInquiry", recieveVO);
		}
		String recieveVOFromCM = (String) request.getAttribute("recieveVO");
		if (recieveVOFromCM != null) {
			response.setEvent(
					"ProviderAppealsInquirytoServiceAuthorizationDetail",
					recieveVOFromCM);
		}
		 CommonCMCaseDetailsVO ccMCCaseDetVO = (CommonCMCaseDetailsVO) request.getAttribute("CaseDetails");
	     if (ccMCCaseDetVO != null) {
	        response.setEvent("PGM_IN_PAInquirytoPGM_IN_LogCasePublishEvent", ccMCCaseDetVO);
	     }
	     String recieveVOFromCM1 = (String) request.getAttribute("InternalServiceAuthID");
			if (recieveVOFromCM1 != null) {
				response.setEvent(
						"ProviderAppealsServiceAuthInquiry",
						recieveVOFromCM1);
			}
			ClaimInquirySearchResultsVO claimInquirySearchResultsVO = (ClaimInquirySearchResultsVO)request.getAttribute("objInvokeDetails");
			if (claimInquirySearchResultsVO != null) {
				response.setEvent("ProviderAppealsToClaimInquirySearch", claimInquirySearchResultsVO);
			}



}
    /**
     * calls do view method
     * 
     * @param request
     *            RenderRequest
     * @param response
     *            RenderResponse
     * @throws PortletException
     *             if Portlet Exception occurs
     * @throws IOException
     *             if IOException occurs
     */
    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {
    
    	String currentPage = "/jsp/appeals/providerAppeals.jsp";

    	
    	
        final String jsp = (String) (request.getPortletSession()
                .getAttribute(MaintainContactManagementUIConstants.COM_IBM_FACES_PORT_PG_VIEW));
        final String sessionObject = (String) (request.getPortletSession()
                .getAttribute(MaintainContactManagementUIConstants.SES_OBJ));

        final Object provAppealsObj = request.getPortletSession().getAttribute(
                MaintainContactManagementUIConstants.PROV_APLS);

        if (provAppealsObj != null)
        {
            request.setAttribute(
                    MaintainContactManagementUIConstants.PROV_APLS,
                    provAppealsObj);
            request.getPortletSession().removeAttribute(
                    MaintainContactManagementUIConstants.PROV_APLS);
        }

        if (sessionObject != null
                && sessionObject
                        .equals(MaintainContactManagementUIConstants.TRUE))
        {
            request.getPortletSession().removeAttribute(
                    MaintainContactManagementUIConstants.SES_OBJ);

            super.doView(request, response);
        }
        else
        {
            super.doView(request, response);
        }

    }
    
    /**
     * This method is used to set the user permission
     * 
     * @param request
     * @param currentPage
     */
    public void setUserPermission(RenderRequest request, String currentPage) {
		
    	String userPermission = "r";
    	
		request.setAttribute("displayMode", userPermission);
	}
	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processEvent(javax.portlet.EventRequest,
	 *      javax.portlet.EventResponse)
	 */
	public void processEvent(EventRequest request, EventResponse response)
			throws PortletException, java.io.IOException {
		String actionName=null;
		Event sampleEvent = request.getEvent();
		if (sampleEvent.getName().toString().equals("ProviderAppealsDatatype")) {
			CommonProviderDetailsVO providerDetailsVO = (CommonProviderDetailsVO) sampleEvent
					.getValue();
			if (providerDetailsVO != null) {
				request.setAttribute("ProviderAppeals", providerDetailsVO);
			}
			actionName="receiveProviderAppealsAction";
		}
		if(sampleEvent.getName().toString().equals("fromProviderInquiryToProviderAppealsInquiryProcessEvent")) {
			CommonProviderDetailsVO providerDetailsVO1= (CommonProviderDetailsVO) sampleEvent.getValue();
			if(providerDetailsVO1!=null){
			request.setAttribute("ProviderAppeals", providerDetailsVO1);
			}
			actionName="receiveProviderAppealsAction";
		}
		if(sampleEvent.getName().toString().equals("LogCaseAddAppealDetailsDataType")) {
			CommonCMCaseDetailsVO commonCMCaseDetailsVO= (CommonCMCaseDetailsVO) sampleEvent.getValue();
			if(commonCMCaseDetailsVO!=null){
			request.setAttribute("AddAppealDetails", commonCMCaseDetailsVO);
			}
			actionName="receiveAddAppealDetailsAction";	
		}
		actionUrl(request, response, actionName);
	}
	public void actionUrl(EventRequest request, EventResponse response,
			String actionName) {
		String actionURL = "";
		request.getPortletSession().setAttribute(
				MaintainContactManagementUIConstants.SES_OBJ,
				MaintainContactManagementUIConstants.TRUE);

		/*
		 * final String actionName = (String) request
		 * .getParameter(MaintainContactManagementUIConstants
		 * .REC_PROV_APLS_ACT);
		 */
		if(log.isDebugEnabled()){
		log.debug("In ProviderAppealsPortlet:actionName: " + actionName);
		}

		if (actionName != null) {
			if (actionName
					.equals(MaintainContactManagementUIConstants.REC_PROV_APL_ACT_NAME)) {

				request
						.getPortletSession()
						.setAttribute(
								MaintainContactManagementUIConstants.PROV_APLS,
								request
										.getAttribute(MaintainContactManagementUIConstants.PROV_APLS));
				actionURL = MaintainContactManagementUIConstants.PROV_APLS_JSP_PATH;
			}
		}

		if (!StringUtils.isEmpty(actionURL)) {
			request
					.getPortletSession()
					.setAttribute(
							MaintainContactManagementUIConstants.COM_IBM_FACES_PORT_PG_VIEW,
							actionURL);
		}
	}

	
}
