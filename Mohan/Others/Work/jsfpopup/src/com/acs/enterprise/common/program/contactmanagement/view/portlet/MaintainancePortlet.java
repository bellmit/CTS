/*
 * Created on Jul 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.acs.enterprise.common.view.vo.CommonMemberDetailsVO;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLRecoverySearchResultVO;
import com.ibm.faces20.portlet.FacesPortlet;
//import javax.servlet.ServletRequest;
//import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
//import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;

/**
 * @author nagjutr
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MaintainancePortlet  extends FacesPortlet{
	
	/**
	 * Generating object of EnterpriseLogger.
	 */
	/*private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(getClass().getName());*/
	private static final EnterpriseLogger logger = EnterpriseLogFactory
	.getLogger(MaintainancePortlet.class);

	private static final String MAINTAINANCE_CURRENT_PAGE = "MAINTAINANCE_CURRENT_PAGE";

	/**
	 * This method is to destroy.
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * When the user clicks on Confirgure mode from admninstration console, this
	 * method will be inovked.
	 */
	public void doConfigure(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		super.doConfigure(request, response);
	}

	/**
	 * When the user clicks on Edit mode from admninstration console, this
	 * method will be inovked.
	 */
	public void doEdit(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		super.doEdit(request, response);
	}

	/**
	 * When the user clicks on Help Icon of Portlet menu, this method will be
	 * inovked.
	 */
	 // Commented for Defect:ESPRD00723971
	/*public void doHelp(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		
		String currentMaintanancePage = String.valueOf(request.getPortletSession().getAttribute(MAINTAINANCE_CURRENT_PAGE,PortletSession.APPLICATION_SCOPE));
		
		if("/jsp/maintaincasetypes/maintaincasetypes.jsp".equals(currentMaintanancePage)){
			
			request.getPortletSession().setAttribute(
					"com.ibm.faces.portlet.page.help", "/jsp/maintaincasetypes/Maintain_Case_Type_Help.jsp");
			
		}else if("/jsp/globalCaseSearch/globalCaseSearch.jsp".equals(currentMaintanancePage)){
			
			request.getPortletSession().setAttribute(
					"com.ibm.faces.portlet.page.help", "/jsp/globalCaseSearch/global_Case_Search_Help.jsp");
		}
		
		super.doHelp(request, response);
	}
   */
	/**
	 * When the user clicks any link or button or any action on the page, this
	 * method will be invoked.After completing this method, it goes to the
	 * corresponding do method from where thw user performed action.
	 * 
	 * @param request
	 *            request object
	 * @param response
	 *            response bject
	 * @throws PortletException
	 *             throws an exception when there is any error in the portlet.
	 */
	public void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		// Commented for Defect:ESPRD00723971
		/*String currentPage = (String) request
				.getParameter("javax.servlet.include.path_info");
		if ("/jsp/maintaincallscripts/maintainCallScripts.jsp"
				.equals(currentPage)) {
			setUserPermission(request,
					ContactManagementConstants.MAINTAIN_CALL_SCRIPTS_PAGE);
		} else if ("/jsp/maintainedmsdefaults/maintainEdmsDefaults.jsp"
				.equals(currentPage)) {
			setUserPermission(request,
					ContactManagementConstants.MAINTAIN_EDMS_DEFAULTS_PAGE);
		} else if ("/jsp/maintainfilter/maintainFilter.jsp".equals(currentPage)) {
			setUserPermission(request,
					ContactManagementConstants.MAINTAIN_FILTERS_PAGE);
		}
		if("/jsp/maintaincasetypes/maintaincasetypes.jsp".equals(currentPage)){
			
			request.getPortletSession().setAttribute(MAINTAINANCE_CURRENT_PAGE,"/jsp/maintaincasetypes/maintaincasetypes.jsp",PortletSession.APPLICATION_SCOPE);
		
		}else if("/jsp/globalCaseSearch/globalCaseSearch.jsp".equals(currentPage)){
			
			request.getPortletSession().setAttribute(MAINTAINANCE_CURRENT_PAGE,"/jsp/globalCaseSearch/globalCaseSearch.jsp",PortletSession.APPLICATION_SCOPE);
		
		}else{
			request.getPortletSession().setAttribute(MAINTAINANCE_CURRENT_PAGE,"",PortletSession.APPLICATION_SCOPE);
		}
       */
		super.doView(request, response);
	}

	/**
	 * This method gets the permission level for the logged in user
	 *
	 */
	// Commented for Defect:ESPRD00723971
	/*public void setUserPermission(RenderRequest request, String currentPage) {
		String userid = "";
		PortletSession psin = request.getPortletSession();
		userid = (String) psin.getAttribute("LOGGED_IN_USERID");
		logger.info("MaintainancePortlet:userid::"+ userid);
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		String userPermission = "";
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					currentPage, userid);
		} catch (SecurityFLSServiceException e) {
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";
		logger.info("MaintainancePortlet:userPermission::"+ userPermission);
		request.setAttribute("displayMode", userPermission);
	}
*/
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException {
		super.processAction(request, response);
		logger.info("Source processAction called");
		String actionName = request.getParameter(ContactManagementConstants.SEND_LTR_REQ_SK);
		logger.info("actionName" + actionName);
		if (FacesContext.getCurrentInstance() != null
				&& FacesContext.getCurrentInstance().getExternalContext() != null
				&& FacesContext.getCurrentInstance().getExternalContext()
						.getRequestMap() != null) {
        	Map contextMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
        	if(contextMap.containsKey("MyTaskClaimCaseSK") ||contextMap.containsKey("MyTaskCaseSk") ){
        		request.removeAttribute("mytaskcorrespondenceSk");
        	}
		}
		System.out.print(" ::::: > "
				+ request.getAttribute("CaseEntityDetails"));
		String caseEntityDetails = (String) request
				.getAttribute("CaseEntityDetails");
		if (caseEntityDetails != null) {
			response.setEvent("EntityToCaseEntityDetails", caseEntityDetails);
		}
		String maintainEntity = (String) request.getAttribute("MaintainEntity");
		if (maintainEntity != null) {
			response.setEvent("CaseEntitySearchToMaintainEntity",
					maintainEntity);
		}
		System.out.println(" End of ProcessAction....MaintainancePortlet.");
		
		// inq about search entity to log case
		String inqAbtCaseEntityDetails = (String) request
				.getAttribute("inqAbtCaseEntityDetails");
		System.out.println(" inqAbtCaseEntityDetails :> "
				+ inqAbtCaseEntityDetails);
		if (inqAbtCaseEntityDetails != null) {
			response.setEvent("inqAbtCaseEntityDetails",
					inqAbtCaseEntityDetails);
		}
		String inquiryEntitySearch_logCR = (String) request
				.getAttribute("inquiryaboutEntityData");
		if (inquiryEntitySearch_logCR != null) {
			response.setEvent("InqAbtEntitySearchToLogCR",
					inquiryEntitySearch_logCR);
		}
		String inquiryEntitySearch_MaintainEntity = (String) request
				.getAttribute("MaintainEntity");
		if (inquiryEntitySearch_MaintainEntity != null) {
			response.setEvent("InqAbtEntitySearchToMaintainEntity",
					inquiryEntitySearch_MaintainEntity);
		}
		String inquiryaboutAddEntity = (String) request
		.getAttribute("MaintainEntity");
		System.out.println(" inquiryaboutAddEntity : "+ (String) request
		.getAttribute("MaintainEntity"));
		if (inquiryaboutAddEntity != null) {
			System.out.println(" Inside inquiryaboutAddEntity..");
			response.setEvent("InquiryAboutCaseEntityAddEntity",
					inquiryaboutAddEntity);
		}
		String correspondenceSk = (String) request
				.getAttribute("mytaskcorrespondenceSk");
		if (correspondenceSk != null) {
			logger.debug(" correspondenceSk : " + correspondenceSk);
			response.setEvent("MyTasksMyCRsToLogCR", correspondenceSk);
		}
		String myTaskCrSK = (String) request
				.getAttribute("myTaskClaimCorrespondenceSk");
		if (myTaskCrSK != null) {
			logger.debug(" myTaskCrSK : " + myTaskCrSK);
			response.setEvent("MyTasksGroupCRsToClaimLogCR", myTaskCrSK);
		}
		CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) request
				.getAttribute("MyTaskCaseSk");
		if (commonCMCaseDetailsVO != null) {
			logger.debug(" CaseSK : " + commonCMCaseDetailsVO.getCaseSK());
			response.setEvent("MyTasksMyCaseRecordsToLogCase",
					commonCMCaseDetailsVO);
		}
		String MyTaskClaimCaseSK = (String) request
				.getAttribute("MyTaskClaimCaseSK");
		if (MyTaskClaimCaseSK != null) {
			logger.debug(" MyTaskClaimCaseSK : " + MyTaskClaimCaseSK);
			response.setEvent("MyTasksGroupCaseRecordsToClaimLogCase",
					MyTaskClaimCaseSK);
		}
		String MyTaskLtrReqSK = (String) request
				.getAttribute(ContactManagementConstants.LTR_REQ_SK);
		if (MyTaskLtrReqSK != null) {
			logger.debug(" MyTaskLtrReqSK : " + MyTaskLtrReqSK);
			response
					.setEvent("MyTasksAlertsToLetterGeneration", MyTaskLtrReqSK);
		}
		TPLRecoverySearchResultVO tplRecoverySearchResultVO = (TPLRecoverySearchResultVO) request
				.getAttribute("sendVO1");
		if (tplRecoverySearchResultVO != null) {
			System.out.println("tplRecoverySearchResultVO :> "+ tplRecoverySearchResultVO.getRecoveryCaseID());
			response.setEvent("MyTasksAlertsToTPLRecovery",
					tplRecoverySearchResultVO);
		}
		String MyTaskSAid = (String) request.getAttribute("sourceSID");
		if (MyTaskSAid != null) {
			logger.debug(" MyTaskSAid : " + MyTaskSAid);
			response.setEvent("MyTasksAlertsToSA", MyTaskSAid);
		}
		CommonCMCaseDetailsVO globalCaseSearch = (CommonCMCaseDetailsVO) request
				.getAttribute("GlobalCaseSearchResult");
		if (globalCaseSearch != null) {
			response.setEvent("GlobalCaseSearchToLogCase", globalCaseSearch);
		}
		String resultVOCM = (String) request.getAttribute("resultVO");
		if (resultVOCM != null) {
			logger.debug(" resultVOCM : " + resultVOCM);
			response.setEvent("GlobalCaseSearchtoTPLHIPPDetails", resultVOCM);
		}
		TPLRecoverySearchResultVO sendVO1 = (TPLRecoverySearchResultVO) request.getAttribute("sendVO2");
		if (sendVO1 != null) {
			logger.debug(" sendVO1 : " + sendVO1);
			response.setEvent("GlobalCaseSearchtoTPLRecoveryCaseDetails",
					sendVO1);
		}
		//Modified the parameter from MSQSearch to MemberDetail for fixing wiring issue from Mytask to MSQ Portlet
		CommonMemberDetailsVO commonMemberDetailsVO = (CommonMemberDetailsVO) request
				.getAttribute("MemberDetail");
		if (commonMemberDetailsVO != null) {
			response.setEvent("MyTaskstoTPLMSQ", commonMemberDetailsVO);
		}
		String logcaseInquiry = (String) request.getAttribute("inqAbtCaseEntityDetails");
		System.out.println(" LogCaseinqAbtCaseEntityDetailsDataType : "+ logcaseInquiry);
		if (logcaseInquiry != null) {
			System.out.println(" Inside LogCaseinqAbtCaseEntityDetailsDataType..");
			response.setEvent("LogCaseinqAbtCaseEntityDetailsDataType",
					logcaseInquiry);
		}
	}
}
