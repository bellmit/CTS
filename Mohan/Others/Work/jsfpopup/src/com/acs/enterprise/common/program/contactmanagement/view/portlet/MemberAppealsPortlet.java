/*
 * Created on Jul 10, 2007 TODO To change the template for this generated file
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
import com.acs.enterprise.common.view.vo.CommonMemberDetailsVO;
import com.acs.enterprise.common.view.vo.WireEventDataCarrierVO;
import com.acs.enterprise.mmis.operations.common.vo.ClaimInquirySearchResultsVO;
import com.ibm.faces20.portlet.FacesPortlet;

/**
 * @author Wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class MemberAppealsPortlet
        extends FacesPortlet
{
    /** Creates an instance of the logger. * */
    private transient EnterpriseLogger log = EnterpriseLogFactory
            .getLogger(getClass().getName());

    

    /**
     * calls process Action
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
//        String actionURL = "";
//        String actionURL0 = "";
//        request.getPortletSession().setAttribute(
//                MaintainContactManagementUIConstants.SES_OBJ,
//                MaintainContactManagementUIConstants.TRUE);
//
//      
//        final String actionName = (String) request
//                .getParameter(MaintainContactManagementUIConstants.COM_IBM_PORT_PROPBROK_ACT);
//        log.debug("In MemberAppealsPortlet:actionName: " + actionName);
//
//        if (actionName != null)
//        {
//            log.debug("Action name not null");
//            if (actionName
//                    .equals(MaintainContactManagementUIConstants.REC_MEM_APL_SRCH_ACT))
//            {
//                log.debug("action Name exixts");
//
//                request
//                        .getPortletSession()
//                        .setAttribute(
//                                MaintainContactManagementUIConstants.MEM_APPLS_SRCH,
//                                request
//                                        .getAttribute(MaintainContactManagementUIConstants.MEM_APPLS_SRCH));
//                actionURL = MaintainContactManagementUIConstants.MEM_APLS_JSP_PATH;
//                log.debug(MaintainContactManagementUIConstants.SUCCESS);
//            }
//            
//            if (actionName
//                    .equals("tplMSQReset"))
//            {
//                log.debug("action Name exixts");
//
//                request
//                        .getPortletSession()
//                        .setAttribute(
//                                "MSQResetSearch",
//                                request
//                                        .getAttribute("MSQResetSearch"));
//                actionURL = MaintainContactManagementUIConstants.MEM_APLS_JSP_PATH;
//                log.debug(MaintainContactManagementUIConstants.SUCCESS);
//            }
//        }
//
//        if (!StringUtils.isEmpty(actionURL))
//        {
//            request
//                    .getPortletSession()
//                    .setAttribute(
//                            MaintainContactManagementUIConstants.COM_IBM_FACES_PORT_PG_VIEW,
//                            actionURL);
//        }
//        /* Added for defect ESPRD00412572 implementation */
//
//        String actionName1 = (String) request
//                .getParameter("SERVICE_AUTH_TARGET_APPEALID");
//        System.err.println("ActionName from LetterGeneration:" + actionName1);
//        if (actionName1 != null) {
//            if ("receive.ServiceAuthAppidAction".equals(actionName1)) {
//
//                Long renderParam = (Long) request
//                        .getAttribute("receServauthAppid");
//                System.err.println(" renderParam " + renderParam);
//
//                request.getPortletSession().setAttribute("receServauthAppid",
//                        request.getAttribute("receServauthAppid"));
//
//                actionURL0 = MaintainContactManagementUIConstants.MEM_APLS_JSP_PATH;
//				/* FIND BUGS FIX */
//                if (!renderParam.equals(Long.valueOf(0)) && renderParam != Long.valueOf(0)) {
//                    System.err.println("  renderParam1111111 " + renderParam);
//                    request
//                            .getPortletSession()
//                            .setAttribute(
//                                    MaintainContactManagementUIConstants.COM_IBM_FACES_PORT_PG_VIEW,
//                                    actionURL0);
//		 		}
//			
//		 	}
//		 }
		super.processAction(request, response);
		String cMMemberSummary = (String) request
				.getAttribute("CMMemberSummary");
		if (cMMemberSummary != null) {
			response.setEvent("CMMemberSummaryType", cMMemberSummary);
                }
		ArrayList dentalTcnInfo = (ArrayList) request
		.getAttribute("dentalTcnInfo");		
		if (dentalTcnInfo != null) {
			WireEventDataCarrierVO dentalTcnInfo1 = new WireEventDataCarrierVO();
			dentalTcnInfo1.setWireData(dentalTcnInfo);
			response.setEvent("DentalTcnInfo", dentalTcnInfo1);
            }
		
		ArrayList instiTcnInfo = (ArrayList) request
		.getAttribute("instiTcnInfo");			
		if (instiTcnInfo != null) {
			WireEventDataCarrierVO instiTcnInfo1 = new WireEventDataCarrierVO();
			instiTcnInfo1.setWireData(instiTcnInfo);
			response.setEvent("InstitutionalTcnInfo", instiTcnInfo1);
        }
       
		ArrayList profTcnInfo = (ArrayList) request
		.getAttribute("profTcnInfo");
		if (profTcnInfo != null) {
			WireEventDataCarrierVO profTcnInfo1 = new WireEventDataCarrierVO();
			profTcnInfo1.setWireData(profTcnInfo);
			response.setEvent("ProfessionalTcnInfo", profTcnInfo1);
    }

		ArrayList voidReplaceTcnInfo = (ArrayList) request
		.getAttribute("voidReplaceTcnInfo");		
		if (voidReplaceTcnInfo != null) {
			WireEventDataCarrierVO voidReplaceTcnInfo1 = new WireEventDataCarrierVO();
			voidReplaceTcnInfo1.setWireData(voidReplaceTcnInfo);
			response.setEvent("VoidReplaceTcnInfo", voidReplaceTcnInfo1);
		}
		CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
				.getAttribute("CaseDetails");
		if (commonCMCaseDetailsVO != null) {
			response.setEvent("CaseTrackingAppealDatatype",
					commonCMCaseDetailsVO);
		}
		String recieveVOFromCM = (String) request.getAttribute("InternalServiceAuthID");
		if (recieveVOFromCM != null) {
			response.setEvent(
					"MemberAppealsServiceAuthInquiry",
					recieveVOFromCM);
		}
		String recieveVOFromCM1 = (String) request.getAttribute("recieveVO");
		if (recieveVOFromCM1 != null) {
			response.setEvent(
					"MemberAppealsInquirytoServiceAuthorizationDetail",
					recieveVOFromCM1);
		}
		String crspdGenAppealSK = (String) request.getAttribute("CrspdGenAppealSK");
		if (crspdGenAppealSK != null) {
			response.setEvent(
					"MemAppealInqTOCRSearch",
					crspdGenAppealSK);
		}
		CommonCMCaseDetailsVO commonCMCaseDetailsVO1 = (CommonCMCaseDetailsVO) request
		.getAttribute("CaseDetails");
		if (commonCMCaseDetailsVO1 != null) {
			response.setEvent(
					"MemberAppealsCMCaseSearchDataType",
					commonCMCaseDetailsVO1);
		}
		
		ClaimInquirySearchResultsVO claimInquirySearchResultsVO = (ClaimInquirySearchResultsVO)request.getAttribute("objInvokeDetails");
		if (claimInquirySearchResultsVO != null) {
			response.setEvent("MemberAppealsToClaimsInquirySearch", claimInquirySearchResultsVO);
		}
		//Added for the Defect ESPRD00873448
		String caseAppealSK = (String) request.getAttribute("CaseTrackingAppealSK");
		if (caseAppealSK != null) {
			response.setEvent("CaseTrackingAppealDatatype",caseAppealSK);
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
    	 
    	String currentPage = "/jsp/appeals/memberAppeals.jsp";
	
        final String jsp = (String) (request.getPortletSession()
                .getAttribute(MaintainContactManagementUIConstants.COM_IBM_FACES_PORT_PG_VIEW));
        final String sessionObject = (String) (request.getPortletSession()
                .getAttribute(MaintainContactManagementUIConstants.SES_OBJ));

        final Object memAppealsObj = request.getPortletSession().getAttribute(
                MaintainContactManagementUIConstants.MEM_APPLS_SRCH);
        
        final Object memberAppealsObj = request.getPortletSession().getAttribute(
                "MSQResetSearch");
        //for defect ESPRD00805886
  	  	//IPC issue fix from Service Authorization to Member.
        String AppealNo = (String)request.getPortletSession().getAttribute("receServauthAppid");

        if (memAppealsObj != null)
        {
            request.setAttribute(
                    MaintainContactManagementUIConstants.MEM_APPLS_SRCH,
                    memAppealsObj);
            request.getPortletSession().removeAttribute(
                    MaintainContactManagementUIConstants.MEM_APPLS_SRCH);
        }
        
        if (memberAppealsObj != null)
        {
            request.setAttribute(
            		"MSQResetSearch",memberAppealsObj);
            request.getPortletSession().removeAttribute(
            		"MSQResetSearch");
        }
        /* Added for defect ESPRD00412572 Implementation */

        if (AppealNo != null) {
            
            request.setAttribute("receServauthAppid", AppealNo);
            request.getPortletSession().removeAttribute("receServauthAppid");
        }
        if(log.isDebugEnabled()){
        log.debug("sessionObject---" + sessionObject);
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
    public void doHelp(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {
	request.getPortletSession().setAttribute("com.ibm.faces.portlet.page.help","/jsp/appeals/Member_Appeals_Help.jsp");
	super.doHelp(request,response);
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
			if (sampleEvent.getName().toString().equals("MemberAppealsSearchType")) {
				CommonMemberDetailsVO memberDetailsVO = (CommonMemberDetailsVO) sampleEvent
						.getValue();
				if(memberDetailsVO!=null){
				request.setAttribute("MemberAppealsSearch", memberDetailsVO);
				}
				actionName="";
}
			if (sampleEvent.getName().toString().equals("MSQResetType")) {
				CommonMemberDetailsVO memberDetailsVO = (CommonMemberDetailsVO) sampleEvent
						.getValue();
				request.setAttribute("MSQResetSearch", memberDetailsVO);
			}
	
			if (sampleEvent.getName().toString().equals(
					"ProcessEventFromMemberSearch")) {
				CommonMemberDetailsVO memberDetailsVO = (CommonMemberDetailsVO) sampleEvent
						.getValue();
				if(memberDetailsVO!=null){
				request.setAttribute("MemberAppealsSearch", memberDetailsVO);
				}
				 actionName="receiveMemberAppealsSearchAction";

			}
			if (sampleEvent.getName().toString().equals(
					"ProcessMSQResetSearchFromMemSearchEvent")) {
				CommonMemberDetailsVO memberDetailsVO = (CommonMemberDetailsVO) sampleEvent
						.getValue();
				if(memberDetailsVO!=null){
				request.setAttribute("MSQResetSearch", memberDetailsVO);
				}
				 actionName="tplMSQReset";

			}
			if (sampleEvent.getName().toString().equals(
					"fromMemberDetailsMaintenanceToMemAppealsInq")) {
				CommonMemberDetailsVO memberInq = (CommonMemberDetailsVO) sampleEvent
						.getValue();
				if (memberInq != null) {
					request.setAttribute("MemberAppealsSearch", memberInq);
				}
				actionName="receiveMemberAppealsSearchAction";
			}
			if(sampleEvent.getName().toString().equals("fromServiceAuthorizationDetailToMemberAppealsInquiryProcessEvent")) {
				String memberInq1 = (String) sampleEvent.getValue();
				if(memberInq1 != null){
					request.setAttribute("receServauthAppid", memberInq1);
				}
				actionName="receive.ServiceAuthAppidAction";
			}
			Event fromTPLMSQToMemberAppealsInquiry = request.getEvent();
			if(fromTPLMSQToMemberAppealsInquiry.getName().toString().equals("TPLMSQToMemberAppealsInquiry")) {
				CommonMemberDetailsVO commonMemberDetailsVO1 = (CommonMemberDetailsVO)fromTPLMSQToMemberAppealsInquiry.getValue();
				if(commonMemberDetailsVO1!=null)
				{
				request.setAttribute("MemberAppealsSearch", commonMemberDetailsVO1);
				}
				actionName="receiveMemberAppealsSearchAction";
			}
			
	    /*    final String actionName = (String) request
            .getParameter(MaintainContactManagementUIConstants.COM_IBM_PORT_PROPBROK_ACT);*/
	        
			processActionName(request, response, actionName);
	}
		
		
	    public void processActionName(EventRequest request, EventResponse response,String actionName) {     
	    	 String actionURL = "";
	         String actionURL0 = "";
	         
	         if (request
                     .getAttribute(MaintainContactManagementUIConstants.MEM_APPLS_SRCH) != null) {
                 request
                 .getPortletSession()
                 .setAttribute(
                         MaintainContactManagementUIConstants.MEM_APPLS_SRCH,
                         request
                                 .getAttribute(MaintainContactManagementUIConstants.MEM_APPLS_SRCH));
	         } else if (request.getAttribute("MSQResetSearch") != null){
                 request
                 .getPortletSession()
                 .setAttribute(
                		 MaintainContactManagementUIConstants.MEM_APPLS_SRCH,
                         request
                                 .getAttribute("MSQResetSearch"));
	         } else if (request.getAttribute("receServauthAppid") != null) {
                 request.getPortletSession().setAttribute("receServauthAppid",
                         request.getAttribute("receServauthAppid"));
	         }
	         
	         
	         
	         
	    	 if (actionName != null)
	         {
	    		 if(log.isDebugEnabled()){
	             log.debug("Action name not null");
	    		 }
	             if (actionName
	                     .equals(MaintainContactManagementUIConstants.REC_MEM_APL_SRCH_ACT))
	             {

	                 request
	                         .getPortletSession()
	                         .setAttribute(
	                                 MaintainContactManagementUIConstants.MEM_APPLS_SRCH,
	                                 request
	                                         .getAttribute(MaintainContactManagementUIConstants.MEM_APPLS_SRCH));
	                 actionURL = MaintainContactManagementUIConstants.MEM_APLS_JSP_PATH;
	                 log.debug(MaintainContactManagementUIConstants.SUCCESS);
	             }
	             
	             if (actionName
	                     .equals("tplMSQReset"))
	             {
	                 request
	                         .getPortletSession()
	                         .setAttribute(
	                                 "MSQResetSearch",
	                                 request
	                                         .getAttribute("MSQResetSearch"));
	                 actionURL = MaintainContactManagementUIConstants.MEM_APLS_JSP_PATH;
	                 log.debug(MaintainContactManagementUIConstants.SUCCESS);
	             }
	         }
	    	 actionURL = MaintainContactManagementUIConstants.MEM_APLS_JSP_PATH;
	         if (!StringUtils.isEmpty(actionURL))
	         {
	             request
	                     .getPortletSession()
	                     .setAttribute(
	                             MaintainContactManagementUIConstants.COM_IBM_FACES_PORT_PG_VIEW,
	                             actionURL);
	         }
	         /* Added for defect ESPRD00412572 implementation */

	         /*String actionName1 = (String) request
	                 .getParameter("SERVICE_AUTH_TARGET_APPEALID");
	         System.out.println("ActionName from LetterGeneration:" + actionName1);*/
	         if (actionName != null) {
	             if ("receive.ServiceAuthAppidAction".equals(actionName)) {
	            	//for defect ESPRD00805886
	           	  	//IPC issue fix from Service Authorization to Member.
	            	 String renderParam = (String) request
	                         .getAttribute("receServauthAppid");

	                 request.getPortletSession().setAttribute("receServauthAppid",
	                         request.getAttribute("receServauthAppid"));

	                 actionURL0 = MaintainContactManagementUIConstants.MEM_APLS_JSP_PATH;
	 				/* FIND BUGS FIX */
	                 //for defect ESPRD00805886
	           	  	 //IPC issue fix from Service Authorization to Member.
	                 if (renderParam!=null) {
	                     request
	                             .getPortletSession()
	                             .setAttribute(
	                                     MaintainContactManagementUIConstants.COM_IBM_FACES_PORT_PG_VIEW,
	                                     actionURL0);
	 		 		}
	 			
	 		 	}
	 		 }
	        }
}
