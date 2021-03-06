/*
 * Created on Oct 12, 2007
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CMRoutingVO;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.application.dataaccess.exception.EnterpriseBaseDataException;
import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.cots.CommonLetter.view.bean.LettersAndResponsesControllerBean;
import com.acs.enterprise.common.cots.CommonLetter.view.bean.LettersAndResponsesDataBean;
import com.acs.enterprise.common.cots.lettergeneration.vo.LetterGenerationInputVO;
import com.acs.enterprise.common.cots.util.GlobalLetterConstants;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.application.exception.SystemParameterNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.delegate.SystemParameterDelegate;
import com.acs.enterprise.common.program.administration.common.domain.SystemParameter;
import com.acs.enterprise.common.program.administration.common.domain.SystemParameterDetail;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.application.exception.CommonEntityNotFoundException;
import com.acs.enterprise.common.program.commonentities.common.delegate.CommonEntityDelegate;
import com.acs.enterprise.common.program.commonentities.common.domain.Address;
import com.acs.enterprise.common.program.commonentities.common.domain.AddressUsage;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntityType;
import com.acs.enterprise.common.program.commonentities.common.domain.Note;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.common.domain.SpecificEntity;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonNotesControllerBean;
import com.acs.enterprise.common.program.commonentities.view.exception.CommonEntityUIException;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.application.dataaccess.exception.CorrespondenceUpdateDataException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CallscriptFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CustomFieldFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMEntityDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMProcessDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CallScript;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseCorrespondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRecord;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CategorySubjectXref;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldValue;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.common.domain.SubjectAuxillary;
import com.acs.enterprise.common.program.contactmanagement.common.vo.AlertVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CategorySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceRequestVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.process.exception.CorrespondenceRecordProcessException;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CategoryDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CorrespondenceDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.DynamicCustomFieldHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.Executor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AlternateIdentifiersVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCorrespondenceVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForMemberVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForProviderVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForTradingPartnerVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceLetterResponsesXrefVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceRecordVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.EventInquiryLoggingConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
//import com.acs.enterprise.common.util.logger.EnterpriseEventFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.mmis.member.common.application.exception.MemberBusinessException;
import com.acs.enterprise.mmis.member.common.domain.Member;
import com.acs.enterprise.mmis.member.common.vo.MemberBasicInfo;
import com.acs.enterprise.mmis.member.information.common.delegate.MemberInformationDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.application.exception.TPLCarrierBusinessException;
import com.acs.enterprise.mmis.operations.tpladministration.common.delegate.TPLCarrierDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.delegate.TPLPolicyDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLCarrier;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLPolicyHolder;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLCarrierNoteVO;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLPolicyHolderDetailsVO;
import com.acs.enterprise.mmis.provider.common.delegate.ProviderInformationDelegate;
import com.acs.enterprise.mmis.provider.common.domain.Provider;
import com.acs.enterprise.mmis.provider.common.vo.ProviderBasicInfo;
import com.acs.enterprise.mmis.provider.common.vo.ProviderInformationRequestVO;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;
import com.acs.enterprise.mmis.provider.common.domain.TradingPartner;
import com.acs.enterprise.mmis.provider.enrollment.common.delegate.ProviderEnrollmentDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;

/**
 * @author Wipro
 */
public class CorrespondenceControllerBean extends EnterpriseBaseControllerBean {

	private int focusInqHidVal = 0;

	/**
	 * @return Returns the focusInqHidVal.
	 */
	public int getFocusInqHidVal() {
		return focusInqHidVal;
	}

	/**
	 * @param focusInqHidVal
	 *            The focusInqHidVal to set.
	 */
	public void setFocusInqHidVal(int focusInqHidVal) {
		this.focusInqHidVal = focusInqHidVal;
	}

	/** Enterprise Logger for Logging */
	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CorrespondenceControllerBean.class);

	/**
	 * This field is used to store receiveMessage.
	 */
	private boolean receiveMessage = false;

	/**
	 * This field is used to store securityfield.
	 */
	private String securityfield = "";

	/**
	 * This field is used to get CommonEntityDataBean.
	 */
	private CommonEntityDataBean commonEntityDataBean = ContactHelper
			.getCommonEntityDataBean();

	/** A variable hold the instance of correspondenceDataBean */
	private CorrespondenceDataBean correspondenceDataBean = getCorrespondenceDataBean();

	/** A variable hold the instance of searchCorrespondenceDataBean */
	private SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
	
	 //Code added for CR:ESPRD00798895 to hold searchCrControllerbean instance.
	/** A variable hold the instance of searchCorrespondenceControllerBean */
	private SearchCorrespondenceControllerBean searchCorrespondenceControllerBean = getSearchCorrespondenceControllerBean();
	
	
	

	/**
	 * This field is used to store whether user has permission.
	 */

	private boolean ctrlReqForReassignCorr = true;

	//commented for unused variables
	//private static String portletName = getPortletName();

	//commented for unused variables
	//private EnterpriseEventFactory eventFactory = EnterpriseEventFactory
			//.getInstance();

	/**
	 * This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermission() {
		String userPermission = "";
		String userid = getUserIDForSecurity();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.LOG_CORRESPONDENCE_PAGE, userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";

		/*
		 * HttpSession session = (HttpSession)
		 * FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		 * session.setAttribute("LOG_C_PERM", userPermission);
		 */

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode", userPermission);
		if(logger.isDebugEnabled())
		{
			logger.debug(" User Permission for Correspondence" + userPermission);
		}

		// check the permission level for the user and set it in a boolean
		// variable. This will be used for Buttons/Links
		if (userPermission.equals("r")) {
			correspondenceDataBean.setControlRequired(false);
		}
	}

	/**
	 * This method get the User ID
	 * 
	 * @return String
	 */
	private String getUserIDForSecurity() {
		String userID = null;
		try {
			/*
			 * HttpServletRequest renderrequest = (HttpServletRequest)
			 * FacesContext.getCurrentInstance().getExternalContext().getRequest();
			 * HttpServletResponse renderresponse = null; EnterpriseUserProfile
			 * enterpriseUserProfile = getUserData(renderrequest,
			 * renderresponse); if (enterpriseUserProfile != null) { userID =
			 * enterpriseUserProfile.getUserId(); }
			 */
			FacesContext facesContext = FacesContext.getCurrentInstance();
			PortletRequest portletRequest = (PortletRequest) facesContext
					.getExternalContext().getRequest();
			userID = getLoggedInUserID(portletRequest);
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("LOGGED_IN_USERID", userID);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			
		}

		return userID;
	}

	// private SearchCorrespondenceDataBean searchCorrespondenceDataBean =
	// getSearchCorrespondenceDataBean();

	/**
	 * show the next page
	 * 
	 * @return
	 */
	public String next() {
		long entryTime = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ correspondenceDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ correspondenceDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ correspondenceDataBean.getStartRecord());
			logger.debug("The end record......."
					+ correspondenceDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(correspondenceDataBean,
				correspondenceDataBean.getItemsPerPage());

		int start = (entDataBean.getCurrentPage() - 1)
				* ProgramNumberConstants.INT_TEN;
		EnterpriseSearchResultsVO resultVO = retrieveData(start,
				ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
		correspondenceDataBean.setAuditParentHistoryList(resultVO
				.getSearchResults());

		correspondenceDataBean.setAuditParentHistoryList(resultVO
				.getSearchResults());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("SearchSystemParameterControllerBean" + "#" + "next"
					+ "#" + (exitTime - entryTime));
		}

		return ProgramConstants.NEXT;
	}

	/**
	 * show the previous page
	 * 
	 * @return
	 */
	public String previous() {
		long entryTime = System.currentTimeMillis();
		
		EnterpriseBaseDataBean entDataBean = previousPage(
				correspondenceDataBean, correspondenceDataBean
						.getItemsPerPage());

		int start = (entDataBean.getCurrentPage() - 1)
				* ProgramNumberConstants.INT_TEN;
		EnterpriseSearchResultsVO resultVO = retrieveData(start,
				ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
		correspondenceDataBean.setAuditParentHistoryList(resultVO
				.getSearchResults());

		correspondenceDataBean.setAuditParentHistoryList(resultVO
				.getSearchResults());

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("DiagnosisCodeControllerBean" + "#" + "previous" + "#"
					+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}

		return ProgramConstants.PREVIOUS;
	}

	/**
	 * show the column value details of the selected audit log
	 * 
	 * @return
	 */
	public String showColValHistory() {
		GlobalAuditsDelegate audit;
		try {
			
			audit = new GlobalAuditsDelegate();
			AuditLog auditLog = audit.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));

			correspondenceDataBean.setSelectedAuditLog(auditLog);
			correspondenceDataBean.setAuditParentHistoryRender(true);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			{
				logger.debug("Error in show Column Value  " + e);
			}
		}
		return "success";
	}

	/**
	 * 
	 * @return
	 */
	public String closeColValHistory() {
		correspondenceDataBean.setAuditParentHistoryRender(false);
		return "success";
	}

	/**
	 * converts CorrespondenceRecordVO to Correspondence
	 * 
	 * @param correspondenceRecordVO
	 * @return
	 */
	private Correspondence convert(CorrespondenceRecordVO correspondenceRecordVO) {
		CorrespondenceDOConvertor correspondenceConvertor = new CorrespondenceDOConvertor();
		Correspondence correspondence = correspondenceConvertor
				.convertCorrespondenceVOToDO(correspondenceDataBean
						.getCorrespondenceRecordVO());

		return correspondence;
	}

	/**
	 * retrieve the param value
	 * 
	 * @param name
	 * @return
	 */
	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}

	/**
	 * retrieve audit logs from the database
	 * 
	 * @param start
	 * @param noOfRecord
	 * @return
	 */
	private EnterpriseSearchResultsVO retrieveData(int start, int noOfRecord) {
		long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate audit;
		try {
			
			audit = new GlobalAuditsDelegate();
			Correspondence correspondence = convert(correspondenceDataBean
					.getCorrespondenceRecordVO());

			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(correspondence, start, noOfRecord);
			return enterpriseResultVO;
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("UpdateSystemParameterControllerBean" + "#" + "#"
					+ (exitTime - entryTime));
		}

		return null;
	}

	/**
	 * set the record range based on the retrieve data from the database that is
	 * stored in the EnterpriseSearchResultsVO
	 * 
	 * @param enterpriseSearchResultsVO
	 */
	public void setRecordRange(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		long entryTime = System.currentTimeMillis();
		int listSize;
		
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		if(logger.isDebugEnabled())
		{
			logger.debug("Total no of records-->" + totalRecordCount);
		}
		correspondenceDataBean.setCount((int) totalRecordCount);

		int noOfPages = correspondenceDataBean.getCount()
				/ correspondenceDataBean.getItemsPerPage();

		int modNofPages = correspondenceDataBean.getCount()
				% correspondenceDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}
		if(logger.isDebugEnabled())
		{
		logger.debug("Number Of pages: " + noOfPages);
		}

		correspondenceDataBean
				.setCurrentPage(ProgramConstants.INITIAL_CURRENT_PAGE);
		correspondenceDataBean.setNumberOfPages(noOfPages);
		correspondenceDataBean.setStartRecord(ProgramConstants.START_RECORD);
		correspondenceDataBean
				.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = correspondenceDataBean.getCount();
		if(logger.isDebugEnabled())
		{
		logger.debug("Total records count is : " + listSize);
		}
		if (listSize <= correspondenceDataBean.getItemsPerPage()) {
			correspondenceDataBean.setEndRecord(listSize);
		}
		if (listSize > correspondenceDataBean.getItemsPerPage()) {
			listSize = correspondenceDataBean.getItemsPerPage();
		}

		if (correspondenceDataBean.getCount() <= correspondenceDataBean
				.getItemsPerPage()) {
			correspondenceDataBean.setShowNext(false);
		} else {
			correspondenceDataBean.setShowNext(true);
		}
		correspondenceDataBean.setShowPrevious(false);
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ correspondenceDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ correspondenceDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ correspondenceDataBean.getStartRecord());
			logger.debug("The end record......."
					+ correspondenceDataBean.getEndRecord());
			logger.debug("The total count......."
					+ correspondenceDataBean.getCount());
		}
		long exitTime = System.currentTimeMillis();
		if(logger.isInfoEnabled())
		{
		logger.info("DiagnosisCodeControllerBean" + "#" + "setRecordRange"
				+ "#" + "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}
	}

	/**
	 * This method is used to get the receiveMessage.
	 * 
	 * @return boolean : Returns the receiveMessage.
	 */
	public boolean isReceiveMessage() {
		
		getSrchDocRepLinkPer();
		//Moved from construcor
		correspondenceDataBean.setMessageFlag(true);
		if (!correspondenceDataBean.isLoadUserPermissions()) {
			getUserPermission();
			correspondenceDataBean.setLoadUserPermissions(true);
		}

		FacesContext fc = FacesContext.getCurrentInstance();

		if ((fc.getExternalContext().getRequestMap() != null)
				&& (correspondenceDataBean.isMessageFlag())) {

			String correspondenceEntity = (String) fc.getExternalContext()
					.getRequestMap().get("correspondenceEntity");
			if(logger.isDebugEnabled())
			{
			logger.debug("correspondenceEntity:" + correspondenceEntity);
			}

			String inquiryaboutEntityData = (String) fc.getExternalContext()
					.getRequestMap().get("inquiryaboutEntityData");
			if(logger.isDebugEnabled())
			{
			logger.debug("inquiryaboutEntityData:" + inquiryaboutEntityData);
			}
			
			String correspondenceSK = (String) fc.getExternalContext()
					.getRequestMap().get("correspondenceSk");
			if(logger.isDebugEnabled())
			{
			logger.debug("correspondenceSk:" + correspondenceSK);
			}

			/** Added for My Task */
			String myTaskCorrespondenceSK = (String) fc.getExternalContext()
					.getRequestMap().get("mytaskcorrespondenceSk");
			if(logger.isDebugEnabled())
			{
			logger.debug("mytaskcorrespondenceSk:" + myTaskCorrespondenceSK);
			}

			String myTaskClaimCorrespondenceSK = (String) fc
					.getExternalContext().getRequestMap().get(
							"myTaskClaimCorrespondenceSk");
			if(logger.isDebugEnabled())
			{
			logger.debug("myTaskClaimCorrespondenceSK:"
					+ myTaskClaimCorrespondenceSK);
			}

			/** Added for letter Generation */
			String letterGenerationDet = (String) fc.getExternalContext()
					.getRequestMap().get("LetterGenerationSKCreated");
			if(logger.isDebugEnabled())
			{
			logger.debug("LetterGenerationSKCreated:" + letterGenerationDet);
			}

			/** added for Attachment */
			Object attamentFrmSession = (Object) fc.getExternalContext()
					.getRequestMap().get("logCREDMSSearchResultsList");
			if(logger.isDebugEnabled())
			{
			logger.debug("attamentFrmSession:" + attamentFrmSession);
			}
			/** Added for Assocaite Case */
			String caseDet = (String) fc.getExternalContext().getRequestMap()
					.get("AssociateCaseData");
			if(logger.isDebugEnabled())
			{
			logger.debug("AssociateCaseData:" + caseDet);
			}

			Map myMap = (Map) fc.getExternalContext().getRequestMap().get(
					"myMap");
			if(logger.isDebugEnabled())
			{
			logger.debug("Map:" + myMap);
			}

			if (correspondenceEntity != null) {
				if(logger.isDebugEnabled())
				{
				logger.debug("CRN is add "
						+ correspondenceDataBean.getCorrespondenceRecordVO()
								.getCorrespondenceRecordNumber());
				}

				clearCorrespondenceState();

				openCorrespondenceInAddMode(correspondenceEntity);
				correspondenceDataBean.setMessageFlag(false);
				correspondenceDataBean.setSearchForCR(false);
				correspondenceDataBean.setAssociatePlusMinusFlag(true);
			} else if (inquiryaboutEntityData != null) {
				if (!ContactManagementConstants.STATUS_CLOSED
						.equalsIgnoreCase(correspondenceDataBean
								.getCorrespondenceRecordVO()
								.getCorrespondenceDetailsVO().getStatusCode())) {
					getInquiryAboutDetails(inquiryaboutEntityData);
					fc.getExternalContext().getRequestMap().remove(
							"inquiryaboutEntityData");

				}

				/** Added for notes */
				restoreCommonEntitiesRefs();
				correspondenceDataBean.setSearchForCR(false);
				correspondenceDataBean.setAssociatePlusMinusFlag(true);
			} else if (correspondenceSK != null) {
				if(logger.isDebugEnabled())
				{
				logger.debug("CRN is upd "
						+ correspondenceDataBean.getCorrespondenceRecordVO()
								.getCorrespondenceRecordNumber());
				}

				clearCorrespondenceState();
				openCorrespondenceInUpdateMode(correspondenceSK);
				correspondenceDataBean.setMessageFlag(false);
				correspondenceDataBean.setSearchForCR(false);
				correspondenceDataBean.setAssociatePlusMinusFlag(true);
			} else if (myTaskCorrespondenceSK != null) {
				clearCorrespondenceState();

				openCorrespondenceInUpdateMode(myTaskCorrespondenceSK);
				correspondenceDataBean.setMessageFlag(false);
				correspondenceDataBean.setSearchForCR(false);
				correspondenceDataBean.setAssociatePlusMinusFlag(true);
			} else if (myTaskClaimCorrespondenceSK != null) {
				clearCorrespondenceState();

				openCorrespondenceInUpdateMode(myTaskClaimCorrespondenceSK);
				correspondenceDataBean.setMessageFlag(false);
				correspondenceDataBean.setSearchForCR(false);
				correspondenceDataBean.setAssociatePlusMinusFlag(true);
			}

			/** Added for Letter Generation */
			else if (letterGenerationDet != null) {
				

				int colonSeparatorIndex = letterGenerationDet
						.indexOf(ContactManagementConstants.COLON_SEPARATOR);

				String letterGenSK = letterGenerationDet.substring(0,
						colonSeparatorIndex);
				if(logger.isDebugEnabled())
				{
				logger.debug("letter generation skl ----->" + letterGenSK);
				}
				String crspdSK = letterGenerationDet.substring(
						colonSeparatorIndex + 1, letterGenerationDet.length());
				if(logger.isDebugEnabled())
				{
				logger.debug("correspondence  sk ----->" + crspdSK);
				}

				setLetterGenerationDetails(letterGenSK);
				if(logger.isDebugEnabled())
				{
				logger.debug("letterGenSK " + letterGenSK);
				}
				//Uncommented for DEFECT_ESPRD00773223
				openCorrespondenceInUpdateMode(crspdSK);
				/** Added for saving notes and common contacts refs */
				restoreCommonEntitiesRefs();
				correspondenceDataBean.setSearchForCR(false);
				correspondenceDataBean.setAssociatePlusMinusFlag(true);
				if(logger.isDebugEnabled())
				{
				logger.debug("letterGenSK " + letterGenSK);
				}
			}

			/** Added for attachment */
			else if (attamentFrmSession != null) {
				
				// CorrespondenceDataBean dataBean =
				// getCorrespondenceDataBean();
				/*
				 * String crSk = dataBean.getCorrespondenceRecordVO()
				 * .getCorrespondenceRecordNumber(); logger.debug("The
				 * Correspondnce Sk in isReceiveMessage () is :" + crSk);
				 */
				// openCorrespondenceInUpdateMode(crSk);
				/** Added for saving notes and common contacts refs */

				AttachmentControllerBean controllerBean = getAttachmentControllerBean();
				controllerBean.getSearchRepositoryResultsList();

				getCommonNotesControllerBean().showNotes();
				restoreCommonEntitiesRefs();
				correspondenceDataBean.setSearchForCR(false);
				correspondenceDataBean.setAssociatePlusMinusFlag(true);
				/*
				 * request.getPortletSession().setAttribute(
				 * "logCREDMSSearchResultsList",
				 * request.getAttribute("logCREDMSSearchResultsList"));
				 */
			} else if (caseDet != null) {
				if(logger.isDebugEnabled())
				{
				logger.debug("caseDet in is recieve:" + caseDet);
				}

				// Start - Added the code for the defect ESPRD00688792
				int separatorIndex = caseDet
				.indexOf("$");

				String caseSk = caseDet.substring(separatorIndex + 1,
				caseDet.length());
				getCorrespondenceDataBean().setCaseSK(caseSk);
				getCorrespondenceDataBean().setCaseFlag(true);
				clearCorrespondenceState();
				openCorrespondenceInAddMode(caseDet.substring(0, separatorIndex));
				correspondenceDataBean.setSearchForCR(false);
				correspondenceDataBean.setAssociatePlusMinusFlag(true);
				correspondenceDataBean.setNavigateTOLogCase(true);
				// End - Added the code for the defect ESPRD00688792

				} else if (myMap != null) {
				
				SearchCorrespondenceControllerBean searchCorrespondenceControllerBean = new SearchCorrespondenceControllerBean();

				searchCorrespondenceControllerBean.fetchDataintoCorr(myMap);
			}
		}
		
		/**
		 * Code is added below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
		
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		if(searchCorrespondenceDataBean.isSearchCRValidationFlag())
		{
			correspondenceDataBean.setLogCRErrMsgFlag(true);
			searchCorrespondenceDataBean.setSearchCRValidationFlag(false);
		}
		
		return receiveMessage;
	}

	/**
	 * @param correspondenceEntity
	 *            The correspondenceEntity to set.
	 */
	protected void openCorrespondenceInAddMode(String correspondenceEntity) {

		correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().getAuditKeyList().clear();
		getCorrespondenceForDetails(correspondenceEntity);
		populateInitialData();
		correspondenceDataBean.setCursorFocus("entityTypSrch");
		correspondenceDataBean.setUpdateMode(false);
		commonEntityDataBean.setNotesSaveMsg(false);
		correspondenceDataBean.setAuditLogFlag(false);
		getCommonNotesControllerBean().showNotes();
	    
		//Below code added for defect ESPRD00897359
		correspondenceDataBean.setCorrespondenceEntity(correspondenceEntity);
		

		/** Added for saving notes and common contacts refs */
		copyCommonEntitiesRefs();

		/** Clear old search result from session - Start */
		if (getSearchCorrespondenceDataBean().getSearchResults() != null
				&& getSearchCorrespondenceDataBean().getSearchResults().size() > 0) {
			getSearchCorrespondenceDataBean().getSearchResults().clear();
			getSearchCorrespondenceDataBean().setShowResultsDiv(false);
		}
		/** close associate correpondence edit section */
		correspondenceDataBean.setRenderShowAssocRec(false);
		/** Clear old search result from session - End */
		correspondenceDataBean.setAlertsHidden("plus");
		correspondenceDataBean.setRoutingHidden("plus");
		correspondenceDataBean.setLetNrespHidden("plus");
		correspondenceDataBean.setAttachmentsHidden("plus");
		correspondenceDataBean.setCallScriptHidden("plus");
		getCorrespondenceDataBean().getCrNotesSetTempList().clear();

	}

	/**
	 * @param letterGenSK
	 *            The letterGenSK to set.
	 * @return String
	 */
	protected String setLetterGenerationDetails(String letterGenSK) {

		if (!isNullOrEmptyField(letterGenSK))

		{
			CorrespondenceLetterResponsesXrefVO letterGenerationVO = new CorrespondenceLetterResponsesXrefVO();
			if(logger.isDebugEnabled())
			{
			logger.debug("letterGenSK 1 " + letterGenSK);
			}
			letterGenerationVO.setLetterGeneratonRequestSK(letterGenSK);
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getListOfLettersAndResponses().clear();
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getListOfLettersAndResponses().add(letterGenerationVO);
			if(logger.isDebugEnabled())
			{
			logger.debug("Size of leter  list  in  setLetterGenerationDetails"
					+ correspondenceDataBean.getCorrespondenceRecordVO()
							.getListOfLettersAndResponses().size());
			}
			correspondenceDataBean.getCorrespondenceRecordVO()
					.setResponseExistIndicator(ContactManagementConstants.Y);
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @param correspondenceSK
	 *            The correspondenceSK to set.
	 */

	protected void openCorrespondenceInUpdateMode(String correspondenceSK) {
		
		logger.debug("Start openCorrespondenceInUpdateMode");
		CMDelegate cmDelegate = new CMDelegate();
		
		Correspondence correspondence = null;
		correspondenceDataBean.setAuditLogFlag(false);
		correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().getAuditKeyList().clear();
		AttachmentDataBean attachmentDataBean = getAttachmentDataBean();

		/** Clear search result from session - Start */
		if (getSearchCorrespondenceDataBean().getSearchResults() != null
				&& getSearchCorrespondenceDataBean().getSearchResults().size() > 0) {
			getSearchCorrespondenceDataBean().getSearchResults().clear();
			getSearchCorrespondenceDataBean().setShowResultsDiv(false);
		}
		
		/** Clear search result from session - End */
		try {
			
			correspondence = cmDelegate.getCorrespondenceDetails(Long
					.valueOf(correspondenceSK));
		} catch (CorrespondenceRecordFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		correspondenceDataBean.setUpdateMode(true);
		getCorrespondenceDataBean().getCrNotesSetTempList().clear();
		CorrespondenceDOConvertor crDOConvertor = new CorrespondenceDOConvertor();

		getReferenceData();
		
		//code fix for ESPRD00683016
		getAllCategoriesForList(getUserID());
		

		CorrespondenceRecordVO crRecordVO = crDOConvertor
				.convertCorrespondenceDOToVO(correspondence);

		//Code added for defect - ESPRD00825861 to set the list size
		if (null != attachmentDataBean.getAttachmentList())
		{
			attachmentDataBean.setAttachmentListSizeforCr(attachmentDataBean.getAttachmentList()
					.size());
		}
		//Code Ended for defect - ESPRD00825861
		
		correspondenceDataBean.setCorrespondenceRecordVO(crRecordVO);
		if (correspondenceDataBean.isRenderCrspdProviderForVO()
				&& crRecordVO.getCorrespondenceForProviderVO()
						.getEntityTypeCode().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_PROV)) {
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().setEntityTypeCodeForNote(
							crRecordVO.getCorrespondenceForProviderVO()
									.getCurrAltID());
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().setNameForNote(
							crRecordVO.getCorrespondenceForProviderVO()
									.getName());
		} else if (correspondenceDataBean
				.isRenderCrspdUnEnrolledProviderForVO()
				&& crRecordVO.getCorrespondenceForVO().getEntityTypeCode()
						.equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_UNPROV)) {
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().setEntityTypeCodeForNote(
							crRecordVO.getCorrespondenceForVO()
									.getEntitySysId());
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().setNameForNote(
							crRecordVO.getCorrespondenceForVO().getName());

		} else if (correspondenceDataBean.isRenderCrspdMemberForVO()
				&& crRecordVO.getCorrespondenceForMemberVO()
						.getEntityTypeCode().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_MEM)) {
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().setEntityTypeCodeForNote(
							crRecordVO.getCorrespondenceForMemberVO()
									.getCurrAltID());
			correspondenceDataBean
					.getCorrespondenceRecordVO()
					.getCorrespondenceForVO()
					.setNameForNote(
							crRecordVO.getCorrespondenceForMemberVO().getName());

		}
		//ADDED FOR THE Correspondence ESPRD00436016
		else if (correspondenceDataBean.isRenderCrspdTrdPartForVO()
				&& crRecordVO.getCorrespondenceForTradingPartnerVO()
						.getEntityTypeCode().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_TD)) {
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().setEntityTypeCodeForNote(
							crRecordVO.getCorrespondenceForTradingPartnerVO()
									.getEntitySysId());
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().setNameForNote(
							crRecordVO.getCorrespondenceForTradingPartnerVO()
									.getName());

		} else {
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().setEntityTypeCodeForNote(
							crRecordVO.getCorrespondenceForVO()
									.getEntitySysId());
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().setNameForNote(
							crRecordVO.getCorrespondenceForVO().getName());

		}
		//CR2365
		// logEventInquiry(crRecordVO.getCorrespondenceForProviderVO().getCurrAltID());
		//code fix for ESPRD00683016
		//getAllCategoriesForList(getUserID());

		String categorySK = crRecordVO.getCorrespondenceDetailsVO()
				.getCategorySK();
		if(logger.isInfoEnabled())
		{
		logger.info("categorySK--------" + categorySK);
		}

		List listOfCategoryDOs = correspondenceDataBean.getListOfCategoryDOs();
		CorrespondenceCategory correspondenceCategory = getSelectedCategory(
				categorySK, listOfCategoryDOs);

		if (correspondenceCategory != null) {
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			String categoryTypeCode = correspondenceCategory
					.getCategoryTypeCode();
			if(logger.isInfoEnabled())
			{
			logger.info("categoryTypeCode======" + categoryTypeCode);
			}
			String entityTypeCode = null;
			String subjectCode = crRecordVO.getCorrespondenceDetailsVO()
					.getSubjectCode();
			try {
				Map catRefreshDataMap = contactMaintenanceDelegate
						.getCategoryRefreshData(entityTypeCode, Long
								.valueOf(categorySK), subjectCode);

				if (catRefreshDataMap != null && catRefreshDataMap.size() > 0) {
					
					CallScript callScript = (CallScript) catRefreshDataMap
							.get(ContactManagementConstants.CALLSCRIPT_DESC);
					if (callScript != null) {
						if(logger.isInfoEnabled())
						{
						logger.info("callScript.getCallScriptSK()=="
								+ callScript.getCallScriptSK());
						}
						correspondenceDataBean.getCorrespondenceRecordVO()
								.setCallScriptDesc(callScript.getDescription());
						correspondenceDataBean
								.getCorrespondenceRecordVO()
								.setCallScriptSK(
										callScript.getCallScriptSK().toString());
						correspondenceDataBean.getCorrespondenceRecordVO()
								.setCallScriptText(
										(callScript.getCex() == null) ? null
												: callScript.getCex()
														.getDescriptionClob());
					}

					Set catSubjXrefs = (Set) catRefreshDataMap
							.get(ContactManagementConstants.CAT_SUBXREFS);
					if (catSubjXrefs != null && catSubjXrefs.size() > 0) {
						
						List categorySubjectsList = correspondenceDataBean
								.getCategorySubjectValues();
						categorySubjectsList.clear();
						for (Iterator iterator = catSubjXrefs.iterator(); iterator
								.hasNext();) {
							CategorySubjectXref catSubjAux = (CategorySubjectXref) iterator
									.next();
							SubjectAuxillary subjectAux = catSubjAux
									.getSubjectAuxillary();
							categorySubjectsList.add(new SelectItem(subjectAux
									.getSubjectCode(), getDescriptionFromVV(
									subjectAux.getSubjectCode(),
									correspondenceDataBean
											.getSubjectValidValues())));
						}
					}

					List cfList = (List) catRefreshDataMap
							.get(ContactManagementConstants.CSTMFLD_DETAILS);
					correspondenceDataBean.getCustomFieldDOList().clear();
					if (cfList != null && !cfList.isEmpty()) {
						
						boolean flag = false;
						correspondenceDataBean.setCustomFieldDOList(cfList);
						correspondenceDataBean.setRenderCustomFlds(true);
						List valueList = contactMaintenanceDelegate
								.getCustomFieldValues(
										new Long(correspondenceSK), "G_CR_TB");
						if(logger.isDebugEnabled())
						{
						logger
								.debug("while getting C values cfvalueList is $$ "
										+ valueList);
						}
						/** panel grid issue fix*/
	                    Map map = new HashMap();
	    				DynamicCustomFieldHelper customFieldHelper = new DynamicCustomFieldHelper();
	    				for (int i = 0; i < valueList.size(); i++) {
	    					CustomFieldValueVO customFieldValueVO = correspondenceDataBean
	    							.getCustomFieldValueVO();
	    					map.put(((CustomFieldValue) valueList.get(i))
	    							.getCustomFieldSK(), valueList.get(i));
	    					customFieldValueVO
	    							.setVersionNo(((CustomFieldValue) valueList.get(i))
	    									.getVersionNo());
	    				}
	    				/** panel grid issue fix*/
						crDOConvertor
								.setCFValueToCustomField(valueList, cfList);
						if (correspondenceDataBean.isCrClosed()) {
							flag = true;
						}
						//showDynamic(cfList, categorySK, flag);/** panel grid issue fix*/
						correspondenceDataBean.setCustomFieldVOList(customFieldHelper
	    						.showDynamicFields(cfList, correspondenceDataBean
	    								.getCustomFieldValueVO().isCrClosed(), map,
	    								correspondenceDataBean.isUpdateMode()));
					} else {
						correspondenceDataBean.setRenderCustomFlds(false);
					}
				}
			} catch (CategoryFetchBusinessException e) {
				
				e.printStackTrace();
			} catch (CustomFieldFetchBusinessException e) {
				
				e.printStackTrace();
			}
			if(logger.isDebugEnabled())
			{
			logger.debug("categoryTypeCode-->" + categoryTypeCode);
			}
			if (categoryTypeCode != null
					&& categoryTypeCode
							.equals(ContactManagementConstants.CATEGORYSK_MS)) {
				correspondenceDataBean.setRenderClientServices(true);
				String entityType = crRecordVO.getCorrespondenceForVO()
						.getEntityTypeCode();
				if(logger.isDebugEnabled())
				{
				logger.debug("entityTypeCode-->" + entityType);
				}

				if (ContactManagementConstants.ENTITY_TYPE_UNMEM
						.equals(entityType)
						|| ContactManagementConstants.ENTITY_TYPE_MEM
								.equals(entityType)) {
					correspondenceDataBean.setRenderLangSpoken(true);
				}
			}
		}
		if (ContactManagementConstants.STATUS_REVIEW_REQUIRED
				.equalsIgnoreCase(correspondenceDataBean
						.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getStatusCode())) {
			correspondenceDataBean.setUnderReview(true);
		} else {
			correspondenceDataBean.setUnderReview(false);
		}
		getCommonNotesControllerBean().showNotes();
		populateNoteSet(correspondence);

		/*
		 * Enable/Disable Save, Reset, Additional Correspondence Link if the CR
		 * Is not assigned to the user currently viewing the CR.
		 */

		correspondenceDataBean.setCrAssignedTo(isCRAssignedToLoggedInUser());

		setSupervisorAppReqFor();

		/** Added for saving notes and common contacts refs */
		copyCommonEntitiesRefs();

		/*
		 * Security related changes Add Routing Assignment/Add Attachment/Add
		 * Alert buttons should be enabled/disabled in logcorrespondence.jsp
		 * page based on the user permissions. As there is an existing boolean
		 * value crClosed in the data bean, user permission is set to that
		 * boolean variable.
		 */
		correspondenceDataBean.setCrClosed(!correspondenceDataBean
				.isControlRequired());
		correspondenceDataBean.setRenderShowAssocRec(false);
		/* End of Security related changes */

		setCorrespondenceClosedIndicator();
		correspondenceDataBean.setAlertsHidden("plus");
		correspondenceDataBean.setRoutingHidden("plus");
		correspondenceDataBean.setLetNrespHidden("plus");
		correspondenceDataBean.setAttachmentsHidden("plus");
		correspondenceDataBean.setCallScriptHidden("plus");

		if(correspondenceDataBean.isUpdateMode()|| correspondenceDataBean.isCrClosed()){
        	correspondenceDataBean.setCursorFocus("test33");
        }else{
        	correspondenceDataBean.setCursorFocus("contactListForOth");
        }
	}

	/**
	 * @param categorySk
	 *            categorySk to set
	 * @param correspondenceSK
	 *            correspondenceSK to set
	 */
	public void getCFElementsInUpdate(String categorySk, String correspondenceSK) {
		List cfList = null;
		List valueList = null;
		boolean flag = false;
		ContactMaintenanceDelegate maintenanceDelegate = new ContactMaintenanceDelegate();
		CorrespondenceDOConvertor converter = new CorrespondenceDOConvertor();

		try {
			cfList = maintenanceDelegate.getCustomFieldForCategory(new Long(
					categorySk));
			if (cfList != null && !cfList.isEmpty()) {
				correspondenceDataBean.setCustomFieldDOList(cfList);
				correspondenceDataBean.setRenderCustomFlds(true);
				valueList = maintenanceDelegate.getCustomFieldValues(new Long(
						correspondenceSK), "G_CR_TB");
				if(logger.isDebugEnabled())
				{
				logger.debug("while getting C values cfvalueList is $$ "
						+ valueList);
				}
				/** panel grid issue fix*/
				Map map = new HashMap();
				DynamicCustomFieldHelper customFieldHelper = new DynamicCustomFieldHelper();
				for (int i = 0; i < valueList.size(); i++) {
					CustomFieldValueVO customFieldValueVO = correspondenceDataBean
							.getCustomFieldValueVO();
					map.put(((CustomFieldValue) valueList.get(i))
							.getCustomFieldSK(), valueList.get(i));
					customFieldValueVO
							.setVersionNo(((CustomFieldValue) valueList.get(i))
									.getVersionNo());
				}
				/** panel grid issue fix*/
				converter.setCFValueToCustomField(valueList, cfList);
				if (correspondenceDataBean.isCrClosed()) {
					flag = true;
				}
				//showDynamic(cfList, categorySk, flag);/** panel grid issue fix*/
				correspondenceDataBean.setCustomFieldVOList(customFieldHelper
						.showDynamicFields(cfList, correspondenceDataBean
								.getCustomFieldValueVO().isCrClosed(), map,
								correspondenceDataBean.isUpdateMode()));
			} else {
				correspondenceDataBean.setRenderCustomFlds(false);
			}
		} catch (CustomFieldFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * to set SupervisorAppReqFor
	 */
	protected void setSupervisorAppReqFor() {

		CorrespondenceDetailsVO crDetailsVO = correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO();
		
			//code fix for ESPRD00683016
			setSupervisorAppReqForUser(crDetailsVO);
			if (crDetailsVO.getSprvsrRevwReqdIndicator().booleanValue()
					&& !isCorrespondenceApprovedOrClosed(crDetailsVO
							.getStatusCode())) {
				if (!crDetailsVO.isSupervisorInd()) {
					removeClosedValFromStatus();
				}
				setSupervisorAppReqForCategory(crDetailsVO);
			}

	}

	/**
	 * @param crDetailsVO
	 *            The crDetailsVO to set.
	 */
	protected void setSupervisorAppReqForUser(
			CorrespondenceDetailsVO crDetailsVO) {
		

		correspondenceDataBean.setAutomaticAlertForUserReq(false);

		//commented for unused variables
		//String createdBySK = crDetailsVO.getCreatedBySK();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			EnterpriseUser user = contactMaintenanceDelegate
					.getEnterpriseUser(getLoggedInUser());

			//code fix for ESPRD00683016
			if (crDetailsVO.getSprvsrRevwReqdIndicator().booleanValue()
					&& !isCorrespondenceApprovedOrClosed(crDetailsVO
							.getStatusCode())) {
			if (user.getSupervisorReviewReqIndicator().booleanValue()) {
				correspondenceDataBean.setAutomaticAlertForUserReq(true);
			}
			}else if (user.getSupervisorReviewReqIndicator().booleanValue()) {
				crDetailsVO.setSprvsrRevwReqdIndicator(Boolean.TRUE);
			}
			if (user.getSupervisorIndicator() != null) {
				crDetailsVO.setSupervisorInd(user.getSupervisorIndicator()
						.booleanValue());
			}
		} catch (CMEntityFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @param crDetailsVO
	 *            The crDetailsVO to set.
	 */
	protected void setSupervisorAppReqForCategory(
			CorrespondenceDetailsVO crDetailsVO) {
		

		correspondenceDataBean.setAutomaticAlertForCategoryReq(false);

		String categorySK = crDetailsVO.getCategorySK();

		List listOfCategoryDOs = correspondenceDataBean.getListOfCategoryDOs();
		if (listOfCategoryDOs != null) {
			for (Iterator iter = listOfCategoryDOs.iterator(); iter.hasNext();) {
				CorrespondenceCategory category = (CorrespondenceCategory) iter
						.next();
				if (category.getCategorySK().toString().equalsIgnoreCase(
						categorySK)
						&& category.getSupervisorReviewReqIndicator()
								.booleanValue()) {
					correspondenceDataBean
							.setAutomaticAlertForCategoryReq(true);
				}
			}
		}

	}

	/**
	 * @param statusCode
	 *            The statusCode to set.
	 * @return boolean
	 */
	protected boolean isCorrespondenceApprovedOrClosed(String statusCode) {
		

		boolean crApprovedorClosed = false;

		crApprovedorClosed = (ContactManagementConstants.CR_STATUS_APPROVED
				.equalsIgnoreCase(statusCode) || ContactManagementConstants.STATUS_CLOSED
				.equalsIgnoreCase(statusCode));

		return crApprovedorClosed;
	}

	/**
	 * @param correspondenceEntity
	 *            The correspondenceEntity to set.
	 */
	private void getCorrespondenceForDetails(String correspondenceEntity) {
		

		int separatorIndex = correspondenceEntity
				.indexOf(ContactManagementConstants.COLON_SEPARATOR);

		String entityId = correspondenceEntity.substring(0, separatorIndex);
		String entityType = correspondenceEntity.substring(separatorIndex + 1,
				correspondenceEntity.length());
		String businessUnitName = correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
				.getBusinessUnitName();
		if(logger.isInfoEnabled())
		{
		logger.info("entityID--------->" + entityId);
		logger.info("entityType--------->" + entityType);
		}
		if (ContactManagementConstants.ENTITY_TYPE_PROV.equals(entityType)) {
			CorrespondenceForProviderVO crspdProviderVO = getCorrespondenceForProviderVO(
					entityId, true);

			setEntityCallScriptDetails(crspdProviderVO);

			correspondenceDataBean.getCorrespondenceRecordVO()
					.setCorrespondenceForProviderVO(crspdProviderVO);
			setDefaultCRDetValues();
			correspondenceDataBean.setRenderCrspdForVO(false);
			correspondenceDataBean.setRenderCrspdProviderForVO(true);
			correspondenceDataBean.setRenderCrspdMemberForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledProviderForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledMemberForVO(false);
			correspondenceDataBean.setRenderCrspdTPLForVO(false);
			correspondenceDataBean.setRenderCrspdTrdPartForVO(false);
		} else if ("UA".equals(entityType)) {
			CorrespondenceForVO correspondenceForVO = getCorrespondenceForUnApprovedProvider(
					entityId, true);

			setEntityCallScriptDetails(correspondenceForVO);

			correspondenceDataBean.getCorrespondenceRecordVO()
					.setCorrespondenceForVO(correspondenceForVO);
			setDefaultCRDetValues();
			correspondenceDataBean.setRenderCrspdForVO(false);
			correspondenceDataBean.setRenderCrspdProviderForVO(false);
			correspondenceDataBean.setRenderCrspdMemberForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledProviderForVO(true);
			correspondenceDataBean.setRenderCrspdUnEnrolledMemberForVO(false);
			correspondenceDataBean.setRenderCrspdTPLForVO(false);
			correspondenceDataBean.setRenderCrspdTrdPartForVO(false);
		} else if (ContactManagementConstants.ENTITY_TYPE_MEM
				.equals(entityType)) {
			CorrespondenceForMemberVO crspdForMemberVO = getCorrespondenceForMemberVO(
					entityId, true);

			setEntityCallScriptDetails(crspdForMemberVO);

			correspondenceDataBean.getCorrespondenceRecordVO()
					.setCorrespondenceForMemberVO(crspdForMemberVO);
			setDefaultCRDetValues();
			correspondenceDataBean.setRenderCrspdForVO(false);
			correspondenceDataBean.setRenderCrspdProviderForVO(false);
			correspondenceDataBean.setRenderCrspdMemberForVO(true);
			correspondenceDataBean.setRenderCrspdUnEnrolledProviderForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledMemberForVO(false);
			correspondenceDataBean.setRenderCrspdTPLForVO(false);
			correspondenceDataBean.setRenderCrspdTrdPartForVO(false);
		} else if (ContactManagementConstants.ENTITY_TYPE_TPL
				.equals(entityType)) {
			if(logger.isInfoEnabled())
			{
			logger.info("in TPL page------>" + entityType);
			logger.info("entityId in TPL page------>" + entityId);
			}
			CorrespondenceForVO correspondenceForVO = getCorrespondenceForTPLVO(
					entityId, true);
			correspondenceForVO.setEntityTypeCode(entityType);
			setEntityCallScriptDetails(correspondenceForVO);

			correspondenceDataBean.getCorrespondenceRecordVO()
					.setCorrespondenceForVO(correspondenceForVO);
			setDefaultCRDetValues();
			correspondenceDataBean.setRenderCrspdForVO(false);
			correspondenceDataBean.setRenderCrspdProviderForVO(false);
			correspondenceDataBean.setRenderCrspdMemberForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledProviderForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledMemberForVO(false);
			correspondenceDataBean.setRenderCrspdTPLForVO(true);
			correspondenceDataBean.setRenderCrspdTrdPartForVO(false);

			correspondenceDataBean.setTplPolicyHolder(false);//CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010

		} else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
				.equals(entityType)) {
			if(logger.isErrorEnabled())
			{
			logger.info("in unenrolled provider page------>" + entityType
					+ " entity ID---> " + entityId);
			}

			CorrespondenceForVO correspondenceForVO = getCorrespondenceForUnEnrolledProviderVO(
					entityId, true);
			setEntityCallScriptDetails(correspondenceForVO);

			correspondenceDataBean.getCorrespondenceRecordVO()
					.setCorrespondenceForVO(correspondenceForVO);
			setDefaultCRDetValues();
			correspondenceDataBean.setRenderCrspdForVO(false);
			correspondenceDataBean.setRenderCrspdProviderForVO(false);
			correspondenceDataBean.setRenderCrspdMemberForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledProviderForVO(true);
			correspondenceDataBean.setRenderCrspdUnEnrolledMemberForVO(false);
			correspondenceDataBean.setRenderCrspdTrdPartForVO(false);
			

		} else if (ContactManagementConstants.ENTITY_TYPE_UNMEM
				.equals(entityType)) {
			if(logger.isInfoEnabled())
			{
			logger.info("in unenrolled member page------>" + entityType);
			}

			CorrespondenceForVO correspondenceForVO = getCorrespondenceForVO(
					entityId, true);
			setEntityCallScriptDetails(correspondenceForVO);

			correspondenceDataBean.getCorrespondenceRecordVO()
					.setCorrespondenceForVO(correspondenceForVO);
			setDefaultCRDetValues();
			correspondenceDataBean.setRenderCrspdForVO(false);
			correspondenceDataBean.setRenderCrspdProviderForVO(false);
			correspondenceDataBean.setRenderCrspdMemberForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledProviderForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledMemberForVO(true);
			correspondenceDataBean.setRenderCrspdTPLForVO(false);
			correspondenceDataBean.setRenderCrspdTrdPartForVO(false);
		} //added for CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
		else if (ContactManagementConstants.ENTITY_TYPE_TP.equals(entityType)) {

			CorrespondenceForVO correspondenceForVO = getCorrespondenceForTPLPolicyHolder(
					entityId, true);

			if (correspondenceForVO != null) {
				correspondenceForVO.setEntityTypeCode(entityType);
				setEntityCallScriptDetails(correspondenceForVO);

				correspondenceDataBean.getCorrespondenceRecordVO()
						.setCorrespondenceForVO(correspondenceForVO);
				setDefaultCRDetValues();
				correspondenceDataBean.setRenderCrspdForVO(false);
				correspondenceDataBean.setRenderCrspdProviderForVO(false);
				correspondenceDataBean.setRenderCrspdMemberForVO(false);
				correspondenceDataBean
						.setRenderCrspdUnEnrolledProviderForVO(false);
				correspondenceDataBean
						.setRenderCrspdUnEnrolledMemberForVO(false);
				correspondenceDataBean.setRenderCrspdTPLForVO(true);
				correspondenceDataBean.setTplPolicyHolder(true);
				correspondenceDataBean.setRenderCrspdTrdPartForVO(false);

			}

		} //EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
		// ADDED FOR THE Correspondence ESPRD00436016
		else if (ContactManagementConstants.ENTITY_TYPE_TD.equals(entityType)) {
			if(logger.isDebugEnabled())
			{
			logger
					.debug(" getCorrespondenceForDetails ContactManagementConstants.ENTITY_TYPE_TD entityId :"
							+ entityType);
			}
			CorrespondenceForTradingPartnerVO corrTradingPartnerVO = getCorrespondenceForTradingPartner(
					entityId, false);
			
			/*
			 * CorrespondenceForVO correspondenceForVO=new CorrespondenceForVO
			 * ();
			 * correspondenceForVO.setEntityTypeCode(corrTradingPartnerVO.getCommonEntitySK());
			 * setEntityCallScriptDetails(correspondenceForVO);
			 */
			
			correspondenceDataBean.getCorrespondenceRecordVO()
					.setCorrespondenceForTradingPartnerVO(corrTradingPartnerVO);
			setDefaultCRDetValues();
			
			correspondenceDataBean.setRenderCrspdForVO(false);
			correspondenceDataBean.setRenderCrspdProviderForVO(false);
			correspondenceDataBean.setRenderCrspdMemberForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledProviderForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledMemberForVO(false);
			correspondenceDataBean.setRenderCrspdTPLForVO(false);
			correspondenceDataBean.setTplPolicyHolder(false);
			correspondenceDataBean.setRenderCrspdTrdPartForVO(true);
			

		} //EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
		else {
			CorrespondenceForVO correspondenceForVO = getCorrespondenceForVO(
					entityId, true);

			setEntityCallScriptDetails(correspondenceForVO);

			correspondenceDataBean.getCorrespondenceRecordVO()
					.setCorrespondenceForVO(correspondenceForVO);
			setDefaultCRDetValues();
			correspondenceDataBean.setRenderCrspdForVO(true);
			correspondenceDataBean.setRenderCrspdProviderForVO(false);
			correspondenceDataBean.setRenderCrspdMemberForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledProviderForVO(false);
			correspondenceDataBean.setRenderCrspdUnEnrolledMemberForVO(false);
			correspondenceDataBean.setRenderCrspdTPLForVO(false);
			correspondenceDataBean.setRenderCrspdTrdPartForVO(false);
		}
		if (businessUnitName != null) {
			if (businessUnitName
					.equals(ContactManagementConstants.CLIENT_SERVICES)) {
				correspondenceDataBean.setRenderClientServices(true);
				if (ContactManagementConstants.ENTITY_TYPE_UNMEM
						.equals(entityType)
						|| ContactManagementConstants.ENTITY_TYPE_MEM
								.equals(entityType)) {
					correspondenceDataBean.setRenderLangSpoken(true);
				}
			}

		}
	}

	//CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
	public CorrespondenceForVO getCorrespondenceForTPLPolicyHolder(
			String entityId, boolean callingEntity) {
		CorrespondenceForVO correspondenceForVO = null;
		//CaseDelegate caseDelegate = new CaseDelegate();
		//TPLPolicyHolder tplPolicyHolder;
		TPLPolicyDelegate tplPolicyDelegate = new TPLPolicyDelegate();
		TPLPolicyHolderDetailsVO tplPolicyHolderDetailsVO;
		try {

			/*tplPolicyHolder = caseDelegate
					.getTPLPloicyHolder(new Long(entityId));*/
			tplPolicyHolderDetailsVO = tplPolicyDelegate
					.getTPLPloicyHolderForCase(new Long(entityId));
			/*if (tplPolicyHolder != null) {
				correspondenceForVO = CorrespondenceDOConvertor
						.getCrspdForTplPolicyHolderVO(tplPolicyHolder);
			}*/
			if (tplPolicyHolderDetailsVO != null) {
				correspondenceForVO = CorrespondenceDOConvertor
						.getCrspdForTplPolicyHolderDetailsVO(tplPolicyHolderDetailsVO);
			}
		} catch (EnterpriseBaseBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.fillInStackTrace());
			}
		}
		return correspondenceForVO;
	}

	//EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
	/**
	 * To set Deafault values for Correspondence Details in Add Mode
	 */
	protected void setDefaultCRDetValues() {
		correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().setPriorityCode("M");
		correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().setStatusCode("O");
		correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().setAnLobCode("MED");
		if (correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().getStatusCode().equalsIgnoreCase(
						"O")) {
			DateFormat dateFormat = new SimpleDateFormat(
					ContactManagementConstants.DATE_FORMAT);

			Calendar calendar = Calendar.getInstance();
			Date statusDate = calendar.getTime();
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceDetailsVO().setStatusDate(
							dateFormat.format(statusDate));
		}
	}

	/**
	 * @param crspdForVO
	 *            The crspdForVO to set.
	 */
	protected void setEntityCallScriptDetails(CorrespondenceForVO crspdForVO) {

		String entityTypeCode = crspdForVO.getEntityTypeCode();
		Long categorySK = null;
		String subjectCode = null;

		setCallScriptDetails(entityTypeCode, categorySK, subjectCode);

	}

	/**
	 * This is a ValueChangeListener method. It gets the Call Script details
	 * based on Subject selected.
	 * 
	 * @param event
	 *            The event to set.
	 */
	public void setSubjectCallScriptDetails(ValueChangeEvent event) {
		if(logger.isInfoEnabled())
		{
		logger.info("setSubjectCallScriptDetails=======" + event);
		}
		if (event == null || isNullOrEmptyField(event.getNewValue().toString())) {
			return;
		}

		String entityTypeCode = null;
		Long categorySK = null;
		String subjectCode = (String) event.getNewValue();

		setCallScriptDetails(entityTypeCode, categorySK, subjectCode);

		/*
		 * FacesContext faces = FacesContext.getCurrentInstance();
		 * faces.renderResponse();
		 */

		//return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @param entityTypeCode
	 *            The entityTypeCode to set.
	 * @param categorySK
	 *            The categorySK to set.
	 * @param subjectCode
	 *            The subjectCode to set.
	 */
	protected void setCallScriptDetails(String entityTypeCode, Long categorySK,
			String subjectCode) {
		
		if(logger.isDebugEnabled())
		{
			logger.debug("entityTypeCode " + entityTypeCode);
		}

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			CallScript callScript = contactMaintenanceDelegate.getCallScript(
					entityTypeCode, categorySK, subjectCode);

			if (callScript != null) {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.setCallScriptDesc(callScript.getDescription());
				correspondenceDataBean.getCorrespondenceRecordVO()
						.setCallScriptSK(
								callScript.getCallScriptSK().toString());
				correspondenceDataBean.getCorrespondenceRecordVO()
						.setCallScriptText(
								(callScript.getCex() == null) ? null
										: callScript.getCex()
												.getDescriptionClob());
				if(logger.isDebugEnabled())
				{

				logger.debug("SK "
						+ correspondenceDataBean.getCorrespondenceRecordVO()
								.getCallScriptSK());
				logger.debug("desc "
						+ correspondenceDataBean.getCorrespondenceRecordVO()
								.getCallScriptDesc());
				logger.debug("text "
						+ correspondenceDataBean.getCorrespondenceRecordVO()
								.getCallScriptText());
				}
			}
		} catch (CallscriptFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * @param inquiryaboutEntityData
	 *            The inquiryaboutEntityData to set.
	 */
	protected void getInquiryAboutDetails(String inquiryaboutEntityData) {
		
		int separatorIndex = inquiryaboutEntityData
				.indexOf(ContactManagementConstants.COLON_SEPARATOR);
		String entityId = inquiryaboutEntityData.substring(0, separatorIndex);
		String entityType = inquiryaboutEntityData.substring(
				separatorIndex + 1, inquiryaboutEntityData.length());
		correspondenceDataBean.setInqAbtForProvider(true);
		//Added for the defect ESPRD00873143 to remove the success message
		correspondenceDataBean.setCrSaved(false);
		String entityID = null;
		if (correspondenceDataBean.isRenderCrspdProviderForVO()) {
			entityID = correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForProviderVO().getCmEntityID();
		} else if (correspondenceDataBean.isRenderCrspdMemberForVO()) {
			entityID = correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForMemberVO().getEntityId();
		} else if (correspondenceDataBean.isRenderCrspdTrdPartForVO()) {
			entityID = correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForTradingPartnerVO().getEntityId();
		} else {
			entityID = correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceForVO().getEntityId();
		}
		boolean recordExists = false;
		if (ContactManagementConstants.ENTITY_TYPE_PROV.equals(entityType)) {
			CorrespondenceForProviderVO crspdProviderVO = getCorrespondenceForProviderVO(
					entityId, false);
			List inqAbtList = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
					.getListOfEnquiryAboutEntities();
			/*
			 * String entityID =
			 * correspondenceDataBean.getCorrespondenceRecordVO()
			 * .getCorrespondenceForProviderVO().getEntityId(); if(entityID ==
			 * null) { logger.info("entityID is null"); entityID =
			 * correspondenceDataBean.getCorrespondenceRecordVO()
			 * .getCorrespondenceForVO().getEntitySysId(); }
			 */
			if (crspdProviderVO.getCmEntityID().equalsIgnoreCase(entityID)) {
				recordExists = true;
			} else if (inqAbtList != null && inqAbtList.size() > 0) {
				for (Iterator itr = inqAbtList.iterator(); itr.hasNext();) {
					CorrespondenceForVO correspondenceForVO = (CorrespondenceForVO) itr
							.next();
					if ((crspdProviderVO.getCmEntityID()
							.equalsIgnoreCase(correspondenceForVO
									.getCmEntityID()))
							|| entityID.equalsIgnoreCase(correspondenceForVO
									.getCmEntityID())) {
						recordExists = true;
						break;

					}
				}
				if (!recordExists) {
					correspondenceDataBean.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO()
							.getListOfEnquiryAboutEntities().add(
									crspdProviderVO);
				}
			} else {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(crspdProviderVO);
			}
		} else if (ContactManagementConstants.ENTITY_TYPE_MEM
				.equals(entityType)) {
			CorrespondenceForMemberVO crspdForMemberVO = getCorrespondenceForMemberVO(
					entityId, false);
			List inqAbtList = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
					.getListOfEnquiryAboutEntities();
			/*
			 * String entityID =
			 * correspondenceDataBean.getCorrespondenceRecordVO()
			 * .getCorrespondenceForMemberVO().getEntityId();
			 */
			if (crspdForMemberVO.getEntityId().equalsIgnoreCase(entityID)) {
				recordExists = true;
			} else if (inqAbtList != null && inqAbtList.size() > 0) {
				for (Iterator itr = inqAbtList.iterator(); itr.hasNext();) {

					CorrespondenceForVO correspondenceForVO = (CorrespondenceForVO) itr
							.next();
					if (crspdForMemberVO.getEntityId().equalsIgnoreCase(
							correspondenceForVO.getEntityId())
							|| entityID.equalsIgnoreCase(correspondenceForVO
									.getEntityId())) {
						recordExists = true;
						break;
					}
				}
				if (!recordExists) {
					correspondenceDataBean.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO()
							.getListOfEnquiryAboutEntities().add(
									crspdForMemberVO);
				}
			} else {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(crspdForMemberVO);
			}
		} else if (ContactManagementConstants.ENTITY_TYPE_TPL
				.equals(entityType)) {
			

			CorrespondenceForVO correspondenceForTPLVO = getCorrespondenceForTPLVO(
					entityId, false);
			List inqAbtList = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
					.getListOfEnquiryAboutEntities();
			/*
			 * String entityID =
			 * correspondenceDataBean.getCorrespondenceRecordVO()
			 * .getCorrespondenceForVO().getEntityId();
			 */
			if(logger.isDebugEnabled())
			{
			logger.debug("inqAbtList:::::::::::: " + inqAbtList.size());
			}
			if (correspondenceForTPLVO.getEntityId().equalsIgnoreCase(entityID)) {
				recordExists = true;
			} else if (inqAbtList != null && inqAbtList.size() > 0) {
				for (Iterator itr = inqAbtList.iterator(); itr.hasNext();) {
					CorrespondenceForVO correspondenceForVO = (CorrespondenceForVO) itr
							.next();
					if ((correspondenceForTPLVO.getCmEntityID()
							.equalsIgnoreCase(correspondenceForVO.getEntityId()))
							|| entityID.equalsIgnoreCase(correspondenceForVO
									.getEntityId())) {
						recordExists = true;
						break;

					}
				}
				if (!recordExists) {
					correspondenceDataBean.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO()
							.getListOfEnquiryAboutEntities().add(
									correspondenceForTPLVO);
				}
			} else {
				correspondenceForTPLVO.setEntityId(correspondenceForTPLVO
						.getCmEntityID());
				correspondenceForTPLVO.setName(correspondenceForTPLVO
						.getCarrierName());
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(
								correspondenceForTPLVO);
			}

		} else if ((ContactManagementConstants.ENTITY_TYPE_TP
				.equals(entityType))) {

			
			CorrespondenceForVO correspondenceForTPVO = getCorrespondenceForTPLPolicyHolder(
					entityId, false);
			List inqAbtList = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
					.getListOfEnquiryAboutEntities();
			if(logger.isDebugEnabled())
			{
			logger.debug("inqAbtList:::::::::::: " + inqAbtList.size());
			}
			/*
			 * String entityID =
			 * correspondenceDataBean.getCorrespondenceRecordVO()
			 * .getCorrespondenceForVO().getEntityId();
			 */
			if (correspondenceForTPVO.getEntityId().equalsIgnoreCase(entityID)) {
				recordExists = true;
			} else if (inqAbtList != null && inqAbtList.size() > 0) {
				for (Iterator itr = inqAbtList.iterator(); itr.hasNext();) {
					CorrespondenceForVO correspondenceForVO = (CorrespondenceForVO) itr
							.next();
					if ((correspondenceForTPVO.getCmEntityID()
							.equalsIgnoreCase(correspondenceForVO.getEntityId()))
							|| entityID.equalsIgnoreCase(correspondenceForVO
									.getEntityId())) {
						recordExists = true;
						break;

					}
				}
				if (!recordExists) {
					correspondenceDataBean.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO()
							.getListOfEnquiryAboutEntities().add(
									correspondenceForTPVO);
				}
			} else {
				correspondenceForTPVO.setEntityId(correspondenceForTPVO
						.getCmEntityID());
				correspondenceForTPVO.setName(correspondenceForTPVO
						.getPolicyHolderName());
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(
								correspondenceForTPVO);
			}
		} else if ((ContactManagementConstants.ENTITY_TYPE_UNPROV
				.equals(entityType))) {
			correspondenceDataBean.setCorrespondenceForInquiry(true);
			ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
			Long pSysID = null;
			try {
				pSysID = providerInformationDelegate.getProviderSysID(new Long(
						entityId));
			} catch (EnterpriseBaseBusinessException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}
			CorrespondenceForVO correspondenceForVO = null;
			if (pSysID != null) {
				if(logger.isInfoEnabled())
				{
				logger
						.info("Correspondence For Provider Entity Found"
								+ pSysID);
				}
				correspondenceForVO = getCorrespondenceForUnApprovedProvider(
						pSysID.toString(), false);
			} else {
				correspondenceForVO = getCorrespondenceForUnEnrolledProviderVO(
						entityId, false);
			}
			/*
			 * CorrespondenceForVO correspondenceForVO = getCorrespondenceForVO(
			 * entityId, false);
			 */
			List inqAbtList = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
					.getListOfEnquiryAboutEntities();
			/*
			 * String entityID =
			 * correspondenceDataBean.getCorrespondenceRecordVO()
			 * .getCorrespondenceForVO().getEntityId();
			 */
			if (correspondenceForVO.getCmEntityID().equalsIgnoreCase(entityID)) {
				recordExists = true;
			} else if (inqAbtList != null && inqAbtList.size() > 0) {
				for (Iterator itr = inqAbtList.iterator(); itr.hasNext();) {

					CorrespondenceForVO correspondenceForVO1 = (CorrespondenceForVO) itr
							.next();
					if (correspondenceForVO.getCmEntityID().equalsIgnoreCase(
							correspondenceForVO1.getCmEntityID())
							|| entityID.equalsIgnoreCase(correspondenceForVO1
									.getCmEntityID())) {
						recordExists = true;
						break;
					}
				}
				if (!recordExists) {
					correspondenceDataBean.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO()
							.getListOfEnquiryAboutEntities().add(
									correspondenceForVO);
				}
			} else {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(
								correspondenceForVO);
			}

		}
		//ADDED FOR THE Correspondence ESPRD00436016
		else if ((ContactManagementConstants.ENTITY_TYPE_TD.equals(entityType))) {
			
			CorrespondenceForTradingPartnerVO correspondenceForTDVO = getCorrespondenceForTradingPartner(
					entityId, false);
			List inqAbtList = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
					.getListOfEnquiryAboutEntities();
			if(logger.isDebugEnabled())
			{
			logger.debug("inqAbtList:::::::::::: " + inqAbtList.size());

			/*
			 * String entityID =
			 * correspondenceDataBean.getCorrespondenceRecordVO()
			 * .getCorrespondenceForTradingPartnerVO().getEntityId();
			 */
			
			logger.debug("entityID:::::::::::: " + entityID);
			
			}

			if (inqAbtList != null && inqAbtList.size() > 0) {
				for (Iterator itr = inqAbtList.iterator(); itr.hasNext();) {
					CorrespondenceForVO correspondenceForVO = (CorrespondenceForVO) itr
							.next();
					if ((correspondenceForTDVO.getCmEntityID()
							.equalsIgnoreCase(correspondenceForVO.getEntityId()))
							|| entityID.equalsIgnoreCase(correspondenceForVO
									.getEntityId())) {
						recordExists = true;
						break;

					}
				}
				if (!recordExists) {
					correspondenceDataBean.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO()
							.getListOfEnquiryAboutEntities().add(
									correspondenceForTDVO);
				}
			} else if (correspondenceForTDVO.getEntityId().equalsIgnoreCase(
					entityID)) {
				recordExists = true;
			} else {
				correspondenceForTDVO.setEntityId(correspondenceForTDVO
						.getCmEntityID());
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(
								correspondenceForTDVO);
			}

		}

		else {
			correspondenceDataBean.setCorrespondenceForInquiry(true);
			CorrespondenceForVO correspondenceForVO = getCorrespondenceForVO(
					entityId, false);
			List inqAbtList = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
					.getListOfEnquiryAboutEntities();
			/*
			 * String entityID =
			 * correspondenceDataBean.getCorrespondenceRecordVO()
			 * .getCorrespondenceForVO().getEntityId();
			 */
			if (correspondenceForVO.getEntityId().equalsIgnoreCase(entityID)) {
				recordExists = true;
			} else if (inqAbtList != null && inqAbtList.size() > 0) {
				for (Iterator itr = inqAbtList.iterator(); itr.hasNext();) {

					CorrespondenceForVO correspondenceForVO1 = (CorrespondenceForVO) itr
							.next();
					if (correspondenceForVO.getEntityId().equalsIgnoreCase(
							correspondenceForVO1.getEntityId())
							|| entityID.equalsIgnoreCase(correspondenceForVO1
									.getEntityId())) {
						recordExists = true;
						break;
					}
				}
				if (!recordExists) {
					correspondenceDataBean.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO()
							.getListOfEnquiryAboutEntities().add(
									correspondenceForVO);
				}
			} else {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(
								correspondenceForVO);
			}
		}
		if (recordExists) {
		/**
		 * Code is added below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
			correspondenceDataBean.setLogCRErrMsgFlag(true);
			setErrorMessage(
					ContactManagementConstants.DUPLICATE_ADD_ENTITY_ERR_MSG,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					null);
		}
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForVO
	 */
	protected CorrespondenceForVO getCorrespondenceForVO(String entityId,
			boolean callingEntity) {
		

		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
		SpecificEntity specificEntity = null;

		try {
			specificEntity = cmEntityDelegate.getSpecificEntityDetails(Long
					.valueOf(entityId));
		} catch (CMEntityFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		CorrespondenceForVO correspondenceForVO = CorrespondenceDOConvertor
				.getCrspdForVO(specificEntity);

		

		if (!correspondenceDataBean.isCorrespondenceForInquiry()) {
			CorrespondenceDOConvertor.getContactsForEntities(Long
					.valueOf(correspondenceForVO.getCmEntityID()));
			correspondenceForVO.setContact(getCorrespondenceDataBean()
					.getCorrespondenceRecordVO().getCorrespondenceForVO()
					.getContact());
			correspondenceDataBean.setCorrespondenceForInquiry(false);
		}

		if (callingEntity && specificEntity != null) {
			CommonEntity commonEntity = specificEntity.getCommonEntity();
			CorrespondenceDOConvertor.setCommonContactList(commonEntity);
		}

		return correspondenceForVO;
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForVO
	 */
	protected CorrespondenceForVO getCorrespondenceForTPLVO(String entityId,
			boolean callingEntity) {
		

		CMDelegate cmDelegate = new CMDelegate();
		TPLCarrier tplCarrier = null;
		TPLCarrierDelegate tplCarrierDelegate= new TPLCarrierDelegate();
		TPLCarrierNoteVO tplCarrierNoteVO = null;
		CorrespondenceForVO correspondenceForVO=null;

		try {
			// cmDelegate replaced by tplCarrierDelegate for  implement TPL-API
			//tplCarrier = cmDelegate.getTPLCarrier(entityId);
			tplCarrierNoteVO= tplCarrierDelegate.getCarrierDetailsCRM(entityId);
		} catch (TPLCarrierBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		/*CorrespondenceForVO correspondenceForVO = CorrespondenceDOConvertor
				.getCrspdForTPLVO(tplCarrier);*/
		if(tplCarrierNoteVO != null)
		{
		 correspondenceForVO = CorrespondenceDOConvertor
		.getCrspdForTPLVO(tplCarrierNoteVO);
		}

		
		CorrespondenceDOConvertor.getContactsForEntities(Long
				.valueOf(correspondenceForVO.getCmEntityID()));
		correspondenceForVO.setContact(getCorrespondenceDataBean()
				.getCorrespondenceRecordVO().getCorrespondenceForVO()
				.getContact());
		/*
		 * if (callingEntity && specificEntity != null) { CommonEntity
		 * commonEntity = specificEntity.getCommonEntity();
		 * CorrespondenceDOConvertor.setCommonContactList(commonEntity); }
		 */

		return correspondenceForVO;
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForVO
	 */
	protected CorrespondenceForVO getCorrespondenceForUnEnrolledProviderVO(
			String entityId, boolean callingEntity) {
		
		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
		CMDelegate cmDelegate = new CMDelegate();
		SpecificEntity specificEntity = null;
		String specialityCode = null;
		Boolean specificEntityExists = Boolean.FALSE;

		try {
			specificEntity = cmEntityDelegate.getSpecificEntityDetails(Long
					.valueOf(entityId));
		} catch (CMEntityFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		CorrespondenceForVO correspondenceForVO = CorrespondenceDOConvertor
				.getCrspdForVO(specificEntity);

		try {
			
			specialityCode = cmDelegate.getSpecialityCode(Long
					.valueOf(entityId));
			if(logger.isInfoEnabled())
			{
			logger.info("specialityCode in controller----->" + specialityCode);
			}
			/*
			 * specificEntityExists = cmDelegate.checkSpecificEntityExists(Long
			 * .valueOf(entityId)); logger.info("specificEntityExists in
			 * controller----->" + specificEntityExists);
			 */
			if(logger.isInfoEnabled())
			{
			logger.info("CmEntityID in controller----->"
					+ correspondenceForVO.getCmEntityID());
			}
			if (correspondenceForVO.getCmEntityID() != null) {
				specificEntityExists = cmDelegate.checkWithSpecificEntity(Long
						.valueOf(correspondenceForVO.getCmEntityID()));
			}
			if(logger.isDebugEnabled())
			{
			logger.debug("specificEntityExists--> " + specificEntityExists);
			}
		} catch (CorrespondenceRecordFetchBusinessException crf) {
			if(logger.isErrorEnabled())
			{
			logger.error(crf.getMessage(), crf);
			}
		}

		if (specialityCode != null) {
			correspondenceForVO.setSpecialityCode(specialityCode);
		}
		if (specificEntityExists.equals(Boolean.TRUE)) {
			correspondenceForVO.setStatus("Application submitted");
		}
		
		//      calling contact list
		CorrespondenceDOConvertor
				.getContactsForEntities(Long.valueOf(entityId));
		correspondenceForVO.setContact(getCorrespondenceDataBean()
				.getCorrespondenceRecordVO().getCorrespondenceForVO()
				.getContact());
		if (callingEntity && specificEntity != null) {
			CommonEntity commonEntity = specificEntity.getCommonEntity();
			CorrespondenceDOConvertor.setCommonContactList(commonEntity);
		}

		return correspondenceForVO;
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForMemberVO
	 */
	protected CorrespondenceForMemberVO getCorrespondenceForMemberVO(
			String entityId, boolean callingEntity) {
		

		Long memberSysId = Long.valueOf(entityId);

		MemberInformationDelegate memberInformationDelegate = new MemberInformationDelegate();
		CMDelegate cMDelegate = new CMDelegate();
		//Member member = null;
		CommonEntity cmResidentAddress = null;
		CommonEntity cmMailingAddress = null;
		MemberBasicInfo memberBasicInfo= null;

		try {
			//member = cMDelegate.getMemberDetailForCR(memberSysId);
			memberBasicInfo= memberInformationDelegate.getMemberDetailsForCR(memberSysId);

			if (memberBasicInfo == null) {
				
			}
			cmResidentAddress = memberInformationDelegate.getRecentAddress(
					memberSysId, "R");
			cmMailingAddress = memberInformationDelegate.getRecentAddress(
					memberSysId, "M");
		} catch (MemberBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		CorrespondenceForMemberVO crspdForMemberVO = CorrespondenceDOConvertor
				.getCrspdMemberVO(memberBasicInfo);

		crspdForMemberVO.setResAddress(getAddress(cmResidentAddress));
		crspdForMemberVO.setMailAddress(getAddress(cmMailingAddress));

		if (callingEntity && memberBasicInfo.getCmnEntySK() != null) {
			
			CorrespondenceDOConvertor.setAlternateIds(memberBasicInfo);

			//CommonEntity commonEntity = member.getCommonEntity();
			//CorrespondenceDOConvertor.setCommonContactList(commonEntity);
			try {
				CommonEntityDelegate commonEntityDelegate = new CommonEntityDelegate();
				CommonEntity commonEntity = commonEntityDelegate.getEntity(memberBasicInfo.getCmnEntySK());

				if (commonEntity != null) {
					CorrespondenceDOConvertor.setCommonContactList(commonEntity);
				}
			} catch (CommonEntityNotFoundException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(),e);
				}
				
     	} catch (EnterpriseBaseBusinessException e) {
				// TODO Auto-generated catch block
     		if(logger.isErrorEnabled())
     		{
     		logger.error(e.getMessage(),e);
     		}
			}
		}

		return crspdForMemberVO;
	}

	//ADDED FOR THE Correspondence ESPRD00436016
	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForTradingPartnerVO
	 */
	protected CorrespondenceForTradingPartnerVO getCorrespondenceForTradingPartner(
			String entityId, boolean callingEntity) {
		CorrespondenceForTradingPartnerVO tradPartVO = null;
		CMDelegate cmDelegate = new CMDelegate();
		TradingPartner tradePartner = null;
		ProviderBasicInfo providerBasicInfo=null;
		ProviderEnrollmentDelegate providerEnrollmentDelegate= new ProviderEnrollmentDelegate();
		
		try {
			if (entityId != null) {
				Long eID = new Long(entityId);
				//tradePartner = cmDelegate.getTradingPartner(null, eID);
				providerBasicInfo=providerEnrollmentDelegate.getTradingPartnerDetailsForCommonEntitySk(null, eID);
				
				if (providerBasicInfo != null) {
					tradPartVO = CorrespondenceDOConvertor
							.getCrspdTradingPartnerVO(providerBasicInfo, entityId);
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (EnterpriseBaseDataException e) {
			e.printStackTrace();
		}
		return tradPartVO;
	}

	//END FOR THE Correspondence ESPRD00436016

	/**
	 * @param cmAddress
	 *            The cmAddress to set.
	 * @return String
	 */
	protected String getAddress(CommonEntity cmAddress) {
		

		if (cmAddress == null) {
			
			return ContactManagementConstants.EMPTY_STRING;
		}

		StringBuffer addr = new StringBuffer(
				ContactManagementConstants.DEFAULT_SIZE);
		Set setOfAddressUsage = cmAddress.getAddressUsage();

		if (setOfAddressUsage != null) {
			for (Iterator iter = setOfAddressUsage.iterator(); iter.hasNext();) {
				AddressUsage addrUsage = (AddressUsage) iter.next();
				Address address = addrUsage.getAddress();

				addr.append(address.getAddressLine1());

				if (address.getAddressLine2() != null && !address.getAddressLine2().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					//addr.append("\n");
					addr.append(MaintainContactManagementUIConstants.BR_TAG);
					addr.append(address.getAddressLine2());
				}
				if (address.getAddressLine3() != null && !address.getAddressLine3().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					//addr.append("\n");
					addr.append(MaintainContactManagementUIConstants.BR_TAG);
					addr.append(address.getAddressLine3());
				}
				if (address.getAddressLine4() != null && !address.getAddressLine4().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					//addr.append("\n");
					addr.append(MaintainContactManagementUIConstants.BR_TAG);
					addr.append(address.getAddressLine4());
				}
				// Below code modified for defect ESPRD00892307 to display the address as per UIS
				/*addr.append("\n");
				if (address.getTownCode() != null) {
					addr.append(address.getTownCode());
				}

				if (address.getCountyCode() != null) {
					addr.append(address.getCountyCode());
				}*/
				addr.append(MaintainContactManagementUIConstants.BR_TAG);
				if (address.getCityName() != null && !address.getCityName().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(address.getCityName());
					addr.append(ContactManagementConstants.COMMA_SEPARATOR);
                    addr.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
				}
				if (address.getStateCode() != null && !address.getStateCode().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(address.getStateCode());
					addr.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
				}
				if (address.getCountryCode() != null && !address.getCountryCode().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(address.getCountryCode());
					addr.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
				}
				if (address.getZipCode5() != null && !address.getZipCode5().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(address.getZipCode5());
				}
				if (address.getZipCode4() != null && !address.getZipCode4().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(MaintainContactManagementUIConstants.HYPHEN);
					addr.append(address.getZipCode4());
				}

				break;
			}
		}

		return addr.toString();
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForProviderVO
	 */
	protected CorrespondenceForProviderVO getCorrespondenceForProviderVO(
			String entityId, boolean callingEntity) {
		

		ProviderInformationRequestVO providerInfRequestVO = new ProviderInformationRequestVO();

		providerInfRequestVO.setProviderSysID(Long.valueOf(entityId));
		providerInfRequestVO.setAlternateIdInfoRequired(true);
		providerInfRequestVO.setEnrollmentStatusRequired(true);
		providerInfRequestVO.setRepresentativeContactRequired(true);
		providerInfRequestVO.setBoardCertifiedSpecialityRequired(true);
		providerInfRequestVO.setProviderTypesRequired(true);
		//Changes added due to change in CommonEntity.hbm.xml
		//providerInfRequestVO.setProviderBankruptcy(true);

		ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

		Provider provider = providerInformationDelegate
				.getProviderDetails(providerInfRequestVO);

		CorrespondenceForProviderVO crspdProviderVO = CorrespondenceDOConvertor
				.getCrspdProviderVO(provider);

		if (callingEntity && provider != null) {
			CorrespondenceDOConvertor.setAlternateIds(provider);

			CommonEntity commonEntity = provider.getCommonEntity();
			CorrespondenceDOConvertor.setCommonContactList(commonEntity);

		}

		return crspdProviderVO;
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForProviderVO
	 */
	protected CorrespondenceForVO getCorrespondenceForUnApprovedProvider(
			String entityId, boolean callingEntity) {
		

		ProviderInformationRequestVO providerInfRequestVO = new ProviderInformationRequestVO();

		providerInfRequestVO.setProviderSysID(Long.valueOf(entityId));
		providerInfRequestVO.setAlternateIdInfoRequired(true);
		providerInfRequestVO.setEnrollmentStatusRequired(true);
		providerInfRequestVO.setRepresentativeContactRequired(true);
		providerInfRequestVO.setBoardCertifiedSpecialityRequired(true);
		providerInfRequestVO.setProviderTypesRequired(true);

		ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

		Provider provider = providerInformationDelegate
				.getProviderDetails(providerInfRequestVO);

		CorrespondenceForVO correspondenceForVO = CorrespondenceDOConvertor
				.getCrspdForUnApprovedProvider(provider);

		if (callingEntity && provider != null) {
			CorrespondenceDOConvertor.setAlternateIds(provider);

			CommonEntity commonEntity = provider.getCommonEntity();
			CorrespondenceDOConvertor.setCommonContactList(commonEntity);

		}

		return correspondenceForVO;
	}

	/**
	 * This method is used to set the receiveMessage.
	 * 
	 * @param receiveMessage :
	 *            The receiveMessage to set.
	 */
	public void setReceiveMessage(boolean receiveMessage) {
		this.receiveMessage = receiveMessage;
	}

	/**
	 * @return boolean
	 */
	protected boolean isCRAssignedToLoggedInUser() {
		

		boolean crAssignedTo = true;

		String crAssignedToWUSK = correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
				.getAssignedToWorkUnitSK();

		String logInUser = getLoggedInUser();
		String logInUserSK = getUserSK(logInUser).toString();
		if(logger.isInfoEnabled())
		{
		logger.info("logInUserSK----->" + logInUserSK);
		logger.info("crAssignedToWUSK----->" + crAssignedToWUSK);
		}

		if (ContactManagementConstants.WORK_UNIT
				.equalsIgnoreCase(correspondenceDataBean
						.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getWorkUnitTypeCode())) {
			crAssignedTo = false;
			if(logger.isInfoEnabled())
			{
			logger.info("crAssignedTo if workUnit is W----->" + crAssignedTo);
			}
		}

		if (crAssignedToWUSK != null && crAssignedTo) {
			if (crAssignedToWUSK.equalsIgnoreCase(logInUserSK)) {
				crAssignedTo = true;

				// Disable save for the CR assigned to User if the status is
				// closed.
				/*
				 * if (ContactManagementConstants.STATUS_CLOSED
				 * .equalsIgnoreCase(correspondenceDataBean
				 * .getCorrespondenceRecordVO()
				 * .getCorrespondenceDetailsVO().getStatusCode())) {
				 * crAssignedTo = false; logger.info("crAssignedTo----->" +
				 * crAssignedTo); }
				 */
			}
		}

		// Check if logged in user is supervisor, if so enable save.
		if (!crAssignedTo
				&& correspondenceDataBean.getCorrespondenceRecordVO() != null
				&& correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO() != null
				&& correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getDepartment() != null
				&& !correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getDepartment().equals(
								ContactManagementConstants.EMPTY_STRING)) {
			EnterpriseUser supervisor = getSupervisorForDept();
			
			if (supervisor != null) {
				
				String supervisorSK = supervisor.getUserWorkUnitSK().toString();
				
				if (supervisorSK.equalsIgnoreCase(logInUserSK)) {
					crAssignedTo = true;
				}
			}
		}

		return crAssignedTo;
	}

	/**
	 *  
	 */
	protected void populateInitialData() {
		

		CategorySearchCriteriaVO categorySearchCriteriaVO = new CategorySearchCriteriaVO();
		categorySearchCriteriaVO.setVoidindicator(false);

		String createdBySK = setCreatedByAndAssignedTo();
		if(logger.isInfoEnabled())
		{
		logger.info("createdBySK------>" + createdBySK);
		}

		//		commented For BR4208(CON0104.0001.01) as part of ES2
		//getAllCategories(getLoggedInUser());
		//getAllCategories(getUserID());
		getAllCategoriesForList(getUserID());
		//		implemented For BR4208(CON0104.0001.01) as part of ES2
		// getAllCategories(categorySearchCriteriaVO);

		setCROpenedDate();

		setDepartmentsList(createdBySK);

		getReferenceData();
		
		//Added for defect ESPRD00784386
		
		setSupervisorAppReqFor();

		if (correspondenceDataBean.getCorrespondenceRecordVO() != null
				&& correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO() != null
				&& correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getSprvsrRevwReqdIndicator() != null
				&& correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getSprvsrRevwReqdIndicator().booleanValue()
				&& !correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().isSupervisorInd()) {
			removeClosedValFromStatus();
		}

	}

	/**
	 *  
	 */
	protected void setCROpenedDate() {
		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);

		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		Date openedDate = calendar.getTime();

		correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().setOpenDate(
						dateFormat.format(openedDate));

	}

	/**
	 * @return String
	 */
	protected String setCreatedByAndAssignedTo() {

		String userId = getLoggedInUser();
		
		List workUnitList = null;

		//   CorrespondenceDOConvertor convertor = new
		// CorrespondenceDOConvertor(); // Find bug issue

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		WorkUnit userWorkUnit = null;
		String createdBySK = ContactManagementConstants.EMPTY_STRING;
		String createdByUserID = ContactManagementConstants.EMPTY_STRING;
		try {
			//code modified below on 07/03/2012.
			//userWorkUnit = contactMaintenanceDelegate.getUserWorkUnit(userId);
			DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
			deptUserBasicInfo.setDataReqested("userIdForWorkUnit");	
			deptUserBasicInfo.setUserID(userId);
			workUnitList = contactMaintenanceDelegate.getWorkUnitUserDetails(deptUserBasicInfo);
			
			DeptUserBasicInfo deptUserBasicInfoResult = null;
			if(workUnitList!=null && !(workUnitList.isEmpty()))
			{
				
				for(int i=0; i < workUnitList.size(); i++)
				{
					deptUserBasicInfoResult = (DeptUserBasicInfo)workUnitList.get(i);
					
				}
				
				
			}
			/*if (userWorkUnit != null && userWorkUnit.getWorkUnitSK() != null) {
				createdBySK = userWorkUnit.getWorkUnitSK().toString();
				if (userWorkUnit.getEnterpriseUser() != null) {
					createdByUserID = userWorkUnit.getEnterpriseUser()
							.getUserID();
				}
			}
			String createdByName = getName(userWorkUnit, "U");*/
			
			if (deptUserBasicInfoResult != null && deptUserBasicInfoResult.getWorkUnitSK() != null) {
				createdBySK = deptUserBasicInfoResult.getWorkUnitSK().toString();
				if (deptUserBasicInfoResult.getUserID()!= null) 
				{
					createdByUserID = deptUserBasicInfoResult.getUserID();
				}
			}
			String createdByName = getNameNewAPI(deptUserBasicInfoResult, "U");

			CorrespondenceDetailsVO correspondenceDetailsVO = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO();

			correspondenceDetailsVO.setCreatedByName(createdByName);
			correspondenceDetailsVO.setCreatedBySK(createdBySK);
			correspondenceDetailsVO.setCreatedByUserID(createdByUserID);
			StringBuffer userLabel = new StringBuffer().append(createdByName)
					.append(ContactManagementConstants.HYPHEN_SEPARATOR)
					.append(createdByUserID);
			correspondenceDetailsVO.setAssignedToWorkUnitName(userLabel
					.toString());
			correspondenceDetailsVO.setAssignedToWorkUnitSK(createdBySK);
			if(logger.isDebugEnabled())
			{
			logger.debug("==AssignedToWorkUnitSK------->"
					+ correspondenceDataBean.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO()
							.getAssignedToWorkUnitSK());
			}
			checkSupervisorReviewReq(userWorkUnit, correspondenceDetailsVO);

		} catch (Exception e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		// TODO one call get WorkUnit with the EnterpriseUser
		//String createdBySK = getUserSK(userId).toString();
		//String createdByName = getUserName(Long.valueOf(createdBySK));

		//CorrespondenceDetailsVO correspondenceDetailsVO =
		// correspondenceDataBean.getCorrespondenceRecordVO().getCorrespondenceDetailsVO();

		// Get workUnit
		/*
		 * if (isNullOrEmptyField(correspondenceDetailsVO.getStatusCode())) { }
		 */

		/*
		 * WorkUnit workUnit = new WorkUnit(); EnterpriseUser user =
		 * workUnit.getEnterpriseUser(); Boolean supervisorApprReq =
		 * user.getSupervisorReviewReqIndicator();
		 * correspondenceDetailsVO.setSprvsrRevwReqdIndicator(supervisorApprReq);
		 * if (supervisorApprReq.booleanValue()) {
		 * correspondenceDataBean.setAutomaticAlertForUserReq(true);
		 * removeClosedValFromStatus(); }
		 */

		return createdBySK;
	}

	/**
	 * @param userWorkUnit
	 *            The userWorkUnit to set.
	 * @param correspondenceDetailsVO
	 *            The correspondenceDetailsVO to set.
	 */
	private void checkSupervisorReviewReq(WorkUnit userWorkUnit,
			CorrespondenceDetailsVO correspondenceDetailsVO) {

		if (userWorkUnit != null) {
			EnterpriseUser user = userWorkUnit.getEnterpriseUser();
			Boolean supervisorApprReq = user.getSupervisorReviewReqIndicator();
			correspondenceDetailsVO
					.setSprvsrRevwReqdIndicator(supervisorApprReq);
			if (user.getSupervisorIndicator() != null) {
				correspondenceDetailsVO.setSupervisorInd(user
						.getSupervisorIndicator().booleanValue());
			}
			if (supervisorApprReq.booleanValue()) {
				correspondenceDataBean.setAutomaticAlertForUserReq(true);
				//removeClosedValFromStatus();
			}
		}

	}

	/**
	 *  
	 */
	protected void removeClosedValFromStatus() {

		List statusList = correspondenceDataBean.getStatValidValues();

		if (statusList != null) {
			for (Iterator iter = statusList.iterator(); iter.hasNext();) {
				SelectItem item = (SelectItem) iter.next();
				String itemValue = (String) item.getValue();
				if (itemValue.equalsIgnoreCase("C")) {
					iter.remove();
					break;
				}
			}
		}

	}

	/**
	 *  
	 */
	private void addClosedValToStatus() {

		List statusList = correspondenceDataBean.getStatValidValues();

		SelectItem item = new SelectItem();
		item.setValue(ContactManagementConstants.STATUS_CLOSED);
		item.setLabel(ContactManagementConstants.STATUS_CLOSED_DESC);

		statusList.add(item);

	}

	/**
	 * @return String
	 */
	public String saveCorrespondence() //throws ContactManagementUIException
	{
		

		boolean flag = false;
		commonEntityDataBean.setNoteFlag(false);
		CorrespondenceRecordVO correspondenceRecordVO = correspondenceDataBean
				.getCorrespondenceRecordVO();
		AttachmentDataBean attachmentDataBean = getAttachmentDataBean();

		if (requiredFldsAvbl(correspondenceRecordVO)) {
			//Changing date format MMDDYYYY or MM-DD-YYYY to MM/DD/YYYY

			boolean validRecDate=false;
			String recivedDate = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
					.getReceivedDate();
			//fixed for defect ESPRD00721402
			if(recivedDate!=null){
				validRecDate = EnterpriseCommonValidator
								.isValidDate(recivedDate);
			}
			if(logger.isInfoEnabled())
			{
			logger.info(" saveCorrespondence validRecDate :" + validRecDate);
			}
			//Changing date format MMDDYYYY or MM-DD-YYYY to MM/DD/YYYY
			if (recivedDate != null && validRecDate) {
				if (correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getReceivedDate() != null) {
					correspondenceDataBean
							.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO()
							.setReceivedDate(
									ContactHelper
											.convertDate(correspondenceDataBean
													.getCorrespondenceRecordVO()
													.getCorrespondenceDetailsVO()
													.getReceivedDate()));
				}
			}
			if (ContactManagementConstants.STATUS_SUP_REVIEW
					.equalsIgnoreCase(correspondenceDataBean
							.getCorrespondenceRecordVO()
							.getCorrespondenceDetailsVO().getStatusCode())) {
				sendAutomaticAlertIfReq();
			}

			setDaysToClose(correspondenceRecordVO);
			// TODO in update if CR is closed protect all
			CorrespondenceDOConvertor correspondenceDOConvertor = new CorrespondenceDOConvertor();

			CorrespondenceRequestVO requestVO = new CorrespondenceRequestVO();
			 if(correspondenceDataBean.isRenderCrspdProviderForVO())
	            {
	            	requestVO.setPrvmemEntityID(correspondenceDataBean.getCorrespondenceRecordVO().getCorrespondenceForProviderVO().getCurrAltID());
	            }else if (correspondenceDataBean.isRenderCrspdMemberForVO())
	            {
	            	requestVO.setPrvmemEntityID(correspondenceDataBean.getCorrespondenceRecordVO().getCorrespondenceForMemberVO().getCurrAltID());
	            }

			CustomFieldValueVO customFieldValueVO = correspondenceDataBean
					.getCustomFieldValueVO();
			List cfList = correspondenceDOConvertor.convertCustomFieldVO(
					correspondenceRecordVO, customFieldValueVO,
					correspondenceDataBean.getCustomFieldDOList());
			if (cfList != null && !cfList.isEmpty()) {
				if(logger.isDebugEnabled())
				{
				logger
						.debug("After DO convertion of CustomField, List size is $$ "
								+ cfList.size());
				}

				requestVO.setListOfCustomFieldValues(cfList);
			} else {
				if(logger.isDebugEnabled())
				{
				logger
						.debug("$$ After DO convertion of CustomField, List size is empty $$ ");
				}
			}

			//  Updating Cascade Key that EDMS uses to save attachments into
			// Document Management Systems.
			
			AttachmentDataBean dataBean = getAttachmentDataBean();

			List attachmentList = dataBean.getAttachmentList();
			if (attachmentList != null && !attachmentList.isEmpty()) {

				String cascadeOne = getSystemParameter(ContactManagementConstants.CASCADE_ONE_CODE);
				if(logger.isDebugEnabled())
				{
				logger.debug("Cascade One:" + cascadeOne);
				}
				String cascadeThree = getSystemParameter(ContactManagementConstants.CASCADE_THREE_CODE);
				if(logger.isDebugEnabled())
				{
				logger.debug("Cascade Three:" + cascadeThree);
				}

				if ((cascadeOne == null || cascadeOne.length() == 0)) {
					cascadeOne = "ACS_NH";
				}

				if (cascadeThree == null || cascadeThree.length() == 0) {

					cascadeThree = "UPLOAD";
					/*
					 * setErrorMessage(
					 * ContactManagementConstants.ERR_SW_SEVERE_FAILURE, new
					 * Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
					 * //throw new
					 * ContactManagementUIException(ContactManagementConstants.ERR_SW_SEVERE_FAILURE,
					 * "Could not retrieve Cascade Keys.");
					 */}

				// Set the Cascade Key for the attachments
				for (int i = 0; i < attachmentList.size(); i++) 
				{
					AttachmentVO attachmentVo = (AttachmentVO) attachmentList
							.get(i);

					StringBuffer cascadeBuffer = new StringBuffer();
					cascadeBuffer.append(cascadeOne).append("/").append(
							attachmentVo.getEdmsWrkUnitLevel()).append("/")
							.append(cascadeThree).append("/").append(
									attachmentVo.getEdmsDocType());
					String cascadeKey = cascadeBuffer.toString();
					logger.debug("cascadeKey = " + cascadeKey);
					logger.debug("attachmentVo.getCascadeKey() " + attachmentVo.getCascadeKey());
					if(attachmentVo.getCascadeKey() == null || "".equals(attachmentVo.getCascadeKey()))
					{
						attachmentVo.setCascadeKey(cascadeKey);
						logger.debug("Setting Cascade key ");
					}
					else
					{
						logger.debug("Cascade key is not empty");
					}
				}
			}
			

			/**
			 * Added- for any Entity Type which is not there in G_CMN_ENTY_TB
			 * change it to Other Checks If the Entity Type is there in
			 * G_CMN_ENTY_TB
			 */
			/*
			 * boolean converReq = isConvertEntTypeNeeded(correspondenceRecordVO
			 * .getCorrespondenceForVO().getEntityTypeCode());
			 *//** Conversion of Ent Type is needed */
			/*
			 * if (converReq) { logger .debug("Ent Type does exsis in
			 * G_CMN_ENTY_TB --Set to Other"); correspondenceRecordVO
			 * .getCorrespondenceForVO() .setEntityTypeCode(
			 * ContactManagementConstants.ENTITY_TYPE_CODE_OTHER); }
			 */
			Correspondence correspondence = correspondenceDOConvertor
					.convertCorrespondenceVOToDO(correspondenceRecordVO);
			CMProcessDelegate cmProcessDelegate = new CMProcessDelegate();
			CMDelegate cmDelegate = new CMDelegate();
           
			// Below extra condition is added for defect ESPRD00938954 to save the note.
			 			
			if ((getCommonEntityDataBean().getNoteList() != null
					&& getCommonEntityDataBean().getNoteList().size() > 0) || getCommonEntityDataBean().isNewNotesRender()) {
				
				addNoteSet(correspondence);

			}
			/*
			 * else { logger.debug("Notes are not available from Notes
			 * portlet"); if
			 * (getCorrespondenceDataBean().getCrNotesSetTempList().size() > 0) {
			 * int size = getCorrespondenceDataBean()
			 * .getCrNotesSetTempList().size(); logger .debug("Notes is
			 * available from temp list and size is $$ " + size); for (int i =
			 * 0; i < size; i++) { correspondence .setNoteSet((NoteSet)
			 * getCorrespondenceDataBean() .getCrNotesSetTempList().get(i)); } } }
			 */

			requestVO.setAuditUserID(ContactManagementConstants.TEST_USERID);
			requestVO
					.setAddedAuditUserID(ContactManagementConstants.TEST_USERID);
			requestVO.setCorrespondenceRecord(correspondence);
			if (correspondenceDataBean.getListOfAssoCrsToDelete() != null
					&& correspondenceDataBean.getListOfAssoCrsToDelete().size() > 0) {
				if(logger.isInfoEnabled())
				{
				logger.info("getListOfAssoCrsToDelete size=="
						+ correspondenceDataBean.getListOfAssoCrsToDelete()
								.size());
				}
				requestVO.setListOfAssocCrToDelete(correspondenceDataBean
						.getListOfAssoCrsToDelete());
			}
			if (correspondenceDataBean.isUpdateMode()) {
				if(logger.isDebugEnabled())
				{
				logger.debug("prev priority Code---->"
						+ correspondenceDataBean.getPrevPriorityCode());
				logger.debug("Curr priority Code---->"
						+ correspondence.getPriorityCode());
				}
				if (!(correspondence.getPriorityCode()
						.equals(correspondenceDataBean.getPrevPriorityCode()))) {
					correspondence.setPriorityDate(new Date());
					if(logger.isDebugEnabled())
					{
					logger.debug("priority Dt Set=======>"
							+ correspondence.getPriorityDate());
					}
				}
			}
			if (!correspondenceDataBean.isUpdateMode()) {

				correspondence.setPriorityDate(new Date());
				if(logger.isDebugEnabled())
				{
				logger.debug("priority Dt Set=======>"
						+ correspondence.getPriorityDate());
				}

			}

			requestVO.setCorrespondenceRecord(correspondence);

			try {
				
				//Code added for defect ESPRD00825861 to check for docfinity call
				boolean chkDocfinityforCr = attachmentDataBean.getAttachmentAddOrUpdateforCr();
				if (attachmentDataBean.getAttachmentList() != null) 
				{
					chkDocfinityforCr = attachmentDataBean.getAttachmentAddOrUpdateforCr() ? true
							: attachmentDataBean.getAttachmentListSizeforCr() < attachmentDataBean
									.getAttachmentList().size();
				}
				requestVO.setCheckDocfinityCall(chkDocfinityforCr);
				//Code Ended for defect ESPRD00825861
				requestVO = cmProcessDelegate.createCorrespondence(requestVO);

				flag = true;
				
				
				if (requestVO != null
						&& requestVO.getCorrespondenceRecord() != null) {
					if(logger.isDebugEnabled())
					{
					logger
							.debug("requestVO.correspondence.getCorrespondenceSK() "
									+ requestVO.getCorrespondenceRecord()
											.getCorrespondenceSK());
					}
					if (requestVO.getCorrespondenceRecord()
							.getCorrespondenceSK().equals("0")) {
						flag = false;
						
					}
				}
			} catch (CorrespondenceRecordProcessException e) {
				if(logger.isInfoEnabled())
				{
				logger.info("++ e.getErrorCode()--" + e.getErrorCode());
				}
				correspondenceDataBean.setCrSaved(false);
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
				/**
				 * Code is added below for not retaining the error messages 
				 * on Log Correspondence page for the Defect ESPRD00852896
				 */
				correspondenceDataBean.setLogCRErrMsgFlag(true);
				//Modified for ESPRD00389115_UC-GLB-LCK-02.1
				if (e.getErrorCode() != null
						&& (EnterpriseMessageConstants.ERR_GLB_DATA_CHANGED_TRY_AGAIN
								.equals(e.getErrorCode()) || "err-sw-edit-failure"
								.equals(e.getErrorCode()))) {
					setErrorMessage(
							EnterpriseMessageConstants.ERR_GLB_DATA_CHANGED_TRY_AGAIN,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							null);
				} else {
					setErrorMessage(
							ContactManagementConstants.ERR_SW_SEVERE_FAILURE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);

				}
			}

			correspondence = requestVO.getCorrespondenceRecord();

			if (flag) {

				/*
				 * setErrorMessage(
				 * ContactManagementConstants.INF_CORRESPONDENCE_SAVE_SUCCESS,
				 * new Object[] { ContactManagementConstants.CORRESPONDENCE },
				 * ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
				 * null);
				 */
				correspondenceDataBean.setCrSaved(true);

				CorrespondenceRecordVO corrRecordVO = new CorrespondenceRecordVO();
				try {

					correspondence = cmDelegate
							.getCorrespondenceDetails(correspondence
									.getCorrespondenceSK());
				}
				/*
				 * Author: Infinite CR 2196 Date : 07/01/2009
				 */

				catch (CorrespondenceRecordFetchBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
					/**
	 				 * Code is added below for not retaining the error messages 
					 * on Log Correspondence page for the Defect ESPRD00852896
					 */
					correspondenceDataBean.setLogCRErrMsgFlag(true);
					setErrorMessage(
							ContactManagementConstants.ERR_SW_SEVERE_FAILURE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);

				}
				getCorrespondenceDataBean().getCrNotesSetTempList().clear();
				corrRecordVO = correspondenceDOConvertor
						.convertCorrespondenceDOToVO(correspondence);

				correspondenceDataBean.setCorrespondenceRecordVO(corrRecordVO);

				/** Get the custom field details */

				/*
				 * getCustomFields(corrRecordVO.getCorrespondenceDetailsVO()
				 * .getCategorySK());
				 */

				/*
				 * Enable/Disable Save, Reset, Additional Correspondence Link if
				 * the CR Is not assigned to the user currently viewing the CR.
				 */
				//setCreatedByAndAssignedTo();
				correspondenceDataBean
						.setCrAssignedTo(isCRAssignedToLoggedInUser());

				setSupervisorAppReqFor();

				setCallScriptDetails(null, Long.valueOf(corrRecordVO.getCorrespondenceDetailsVO().getCategorySK()), corrRecordVO
						.getCorrespondenceDetailsVO().getSubjectCode());

				populateNoteSet(correspondence);

				correspondenceDataBean.setUpdateMode(true);

				setReviewRequiredIndicator();

				setCorrespondenceClosedIndicator();

				if (correspondence != null
						&& correspondence.getCorrespondenceSK() != null) {
					getCFElementsInUpdate(corrRecordVO
							.getCorrespondenceDetailsVO().getCategorySK(),
							correspondence.getCorrespondenceSK().toString());
				}

				getCommonNotesControllerBean().showNotes();

				/** To show Current Note */
				if (!correspondenceDataBean.getCrNotesList().isEmpty()) {
					correspondenceDataBean
							.setCrNotesList(getCommonEntityDataBean()
									.getCommonEntityVO().getNoteSetVO()
									.getNotesList());
					getCommonNotesControllerBean().showNotes();
					getCommonEntityDataBean().getCommonEntityVO()
							.getCommonNotesVO().setCurrentNote(
									((CommonNotesVO) correspondenceDataBean
											.getCrNotesList().get(0))
											.getNoteText());
				}
				if (correspondenceDataBean.isCaseFlag()) {
					
					// Start - Added the code for the defect ESPRD00688792
	
					try{
						
	                    boolean assctCrs=true;
	                    CaseCorrespondence caseCorrespondence=null;
	                    Long caseSK=new Long(correspondenceDataBean.getCaseSK());
	                    Long correspondenceSK=correspondence.getCorrespondenceSK();
	                    
	                    /*System.out.println("correspondencesk $$$$$$$$$$$$"+correspondenceSK);
	                    if(caseSK!=null && correspondenceSK!=null){
	                    	assctCrs=true;
	                    	//caseCorrespondence=cmDelegate.getCaseCorrespondence(caseSK,correspondenceSK);
	                    	if(caseCorrespondence!=null)
	                    		assctCrs=false;
	                    }*/
	                    //cmDelegate.getCaseCorrespondence()
	                    
	                     caseCorrespondence=new CaseCorrespondence();
	                     CaseDelegate caseDelegate = new CaseDelegate();
	                     CaseRecord caseRecord = caseDelegate.getCaseDetails(caseSK);
	                     if(caseRecord!=null){
	                    	 
	                    	 caseCorrespondence.setCaseRecord(caseRecord);
	                    
		                    if(correspondenceDataBean.getCaseSK()!=null)
		                    	caseCorrespondence.setCaseSK(caseSK);
		                    if(correspondence!=null)
		                    	caseCorrespondence.setCorrespondence(correspondence);
		                    if(correspondence.getCorrespondenceSK()!=null)
		                    	caseCorrespondence.setCorrespondenceSK(correspondence.getCorrespondenceSK());
		                    caseCorrespondence.setVoidIndicator(Boolean.FALSE);
		                    caseCorrespondence.setAddedAuditTimeStamp(new Date());
		                    caseCorrespondence.setAddedAuditUserID(correspondence.getAddedAuditUserID());
		                    caseCorrespondence.setAuditTimeStamp(new Date());
		                    caseCorrespondence.setAuditUserID(correspondence.getAuditUserID());
		                    Set caseCorrespondenceSet=caseRecord.getCaseCorrespondences();
		                    
		                    Iterator caseCorrIt=caseCorrespondenceSet.iterator();
		                    CaseCorrespondence existedCaseCorrespondence=null;
		                    while(caseCorrIt.hasNext()){
		                    	
		                    	existedCaseCorrespondence=(CaseCorrespondence)caseCorrIt.next();
		                    	if(existedCaseCorrespondence!=null 
		                    			&& existedCaseCorrespondence.getCorrespondenceSK()!=null 
		                    			&& existedCaseCorrespondence.getCorrespondenceSK()
		                    			.equals(correspondence.getCorrespondenceSK())){
		                    		
		                    		assctCrs=false;
		                    		break;
		                    	}
		                    }
		                    
		                    if(assctCrs){
		                    	
			                    caseCorrespondenceSet.add(caseCorrespondence);
			                    
			                    caseRecord.setCaseCorrespondences(caseCorrespondenceSet);
			                   
			                    caseDelegate.updateCaseForGroup(caseRecord);
			                    
		                    }
	                    //cmDelegate.createCaseCorrespondence(caseCorrespondence);
	                     }//case record not null
	                	}catch(Exception e){
	                		
	                		e.printStackTrace();
	                	}
					
					//showCasePortlet(corrRecordVO);
				}// End - Added the code for the defect ESPRD00688792

			}
			FacesContext fc = FacesContext.getCurrentInstance();

			PortletSession pSession = (PortletSession) fc.getExternalContext()
					.getSession(true);
			pSession.setAttribute(ContactManagementConstants.RefreshMyTaskData,
					ContactManagementConstants.RefreshMyTaskData,
					pSession.APPLICATION_SCOPE);
		} else {
			correspondenceDataBean.setCrSaved(false);
		}
		correspondenceDataBean.setInputHiddenId("#");
		commonEntityDataBean.setSmallSaveFlag("false");
		// Added following line for defect 681457
		correspondenceDataBean.setLoadLetterData(true);
		//Below code added for the defect ESPRD00802065 not to display Filter pop up message
		commonEntityDataBean.setCommonNoteSaved(false);
		
		//Code added for defect ESPRD00825861
		attachmentDataBean.setAttachmentAddOrUpdateforCr(Boolean.FALSE);
		//Code Ended for defect ESPRD00825861
		return "success";
	}

	/**
	 * @throws Exception
	 */
	private String getSystemParameter(String cascadeKey) //throws
														 // ContactManagementUIException
	{
		String cascadeVal = "";
		SystemParameterDelegate systemParameterDelegate = new SystemParameterDelegate();
		SystemParameterDetail systemParameterDetail = null;
		//	Date today = new Date(); // Find bug issue
		SystemParameter systemParameter = null;
		Set systemParameterDetailSet = null;

		try {
			systemParameter = systemParameterDelegate.getSystemParameterDetail(
					new Long(cascadeKey),
					ContactManagementConstants.FUNCTIONAL_AREA_CODE);

		} catch (SystemParameterNotFoundException sytemParmNotFoundExec) {
			if(logger.isErrorEnabled())
			{
			logger.error("SystemParameterNotFoundException :"
					+ " saveCorrespondence()", sytemParmNotFoundExec);
			}
			setErrorMessage(ContactManagementConstants.ERR_SW_SEVERE_FAILURE,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			// throw new
			// ContactManagementUIException(ContactManagementConstants.ERR_SW_SEVERE_FAILURE,
			// "Could not retrieve Cascade Keys.");
		}

		if (systemParameter != null) {
			systemParameterDetailSet = systemParameter
					.getSystemParameterDetail();
			if (systemParameterDetailSet != null) {
				systemParameterDetail = (SystemParameterDetail) systemParameterDetailSet
						.iterator().next();
				cascadeVal = systemParameterDetail.getValueData();
				if(logger.isDebugEnabled())
				{
				logger.debug("cascadeKey:" + cascadeKey + " val:" + cascadeVal);
				}
			}
		}

		return cascadeVal;
	}

	/**
	 * This method used for Ipc of Case
	 * 
	 * @param crRecordVO
	 *            crRecordVO to set
	 * @return String
	 */
	public String showCasePortlet(CorrespondenceRecordVO crRecordVO) {
		

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getRequestMap().put(
				"CorrespondenceResultsList", crRecordVO);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * to set Review Required Indicator
	 */
	protected void setReviewRequiredIndicator() {
		

		if (ContactManagementConstants.STATUS_REVIEW_REQUIRED
				.equalsIgnoreCase(correspondenceDataBean
						.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getStatusCode())) {
			correspondenceDataBean.setUnderReview(true);
		} else if (ContactManagementConstants.CR_STATUS_APPROVED
				.equalsIgnoreCase(correspondenceDataBean
						.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getStatusCode())) {
			correspondenceDataBean.setUnderReview(false);
			//addClosedValToStatus();
		} else {
			correspondenceDataBean.setUnderReview(false);
		}

	}

	/**
	 * to set Correspondence Closed Indicator
	 */
	protected void setCorrespondenceClosedIndicator() {
		

		if (ContactManagementConstants.STATUS_CLOSED
				.equalsIgnoreCase(correspondenceDataBean
						.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getStatusCode())) {
			correspondenceDataBean.setCrClosed(true);
		} else {
			correspondenceDataBean.setCrClosed(false);
		}

	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 */
	protected void setDaysToClose(CorrespondenceRecordVO correspondenceRecordVO) {
		

		if (ContactManagementConstants.STATUS_CLOSED
				.equalsIgnoreCase(correspondenceRecordVO
						.getCorrespondenceDetailsVO().getStatusCode())
				&& isNullOrEmptyField(correspondenceRecordVO.getDaysToClose())) {
			correspondenceRecordVO
					.setDaysToClose(ContactManagementConstants.EMPTY_STRING
							+ correspondenceRecordVO
									.getCorrespondenceDetailsVO().getDaysOpen());
			correspondenceDataBean.setRenderDaysOpenFlag(false);
		}

	}

	/**
	 * @return String
	 */
	public String resetCorrespondence() {
		
		commonEntityDataBean.setNoteFlag(false);
		boolean updateMode = correspondenceDataBean.isUpdateMode();
		

		if (updateMode) {
			
			String correspondenceSK = correspondenceDataBean
					.getCorrespondenceRecordVO()
					.getCorrespondenceRecordNumber();
			if(logger.isDebugEnabled())
			{
			logger.debug("correspondenceSK " + correspondenceSK);
			}
			correspondenceDataBean.setAssociatePlusMinusFlag(true);
			clearCorrespondenceState();
			openCorrespondenceInUpdateMode(correspondenceSK);
		} else {
			
			//String correspondenceEntity = getEntityForCorrespondence();
			//Below code added for defect ESPRD00897359
			String correspondenceEntity =correspondenceDataBean.getCorrespondenceEntity();
			clearCorrespondenceState();
			correspondenceDataBean.setAssociatePlusMinusFlag(true);
			openCorrespondenceInAddMode(correspondenceEntity);
		}
		
		
		return "success";
	}

	/**
	 * @return String
	 */
	public String additionalCorrespondence() {
		

		String correspondenceEntity = getEntityForCorrespondence();
		int separatorIndex = correspondenceEntity
				.indexOf(ContactManagementConstants.COLON_SEPARATOR);
		String entityId = correspondenceEntity.substring(0, separatorIndex);
		String entityType = correspondenceEntity.substring(separatorIndex + 1,
				correspondenceEntity.length());
		if(logger.isInfoEnabled())
		{
		logger.info("entityId========" + entityId);
		logger.info("entityType=======" + entityType);
		}
		if (ContactManagementConstants.ENTITY_TYPE_UNPROV.equals(entityType)) {
			ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
			Long pSysID = null;
			try {
				pSysID = providerInformationDelegate.getProviderSysID(Long
						.valueOf(entityId));
				
			} catch (EnterpriseBaseBusinessException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}

			if (pSysID != null) {
				correspondenceEntity = pSysID.toString() + ":" + "UA";
			}
		}
		clearCorrespondenceState();

		openCorrespondenceInAddMode(correspondenceEntity);

		return "success";
	}

	/**
	 * @return String
	 */
	protected String getEntityForCorrespondence() {
		

		CorrespondenceForVO correspondenceForVO = null;
		String correspondenceEntity = ContactManagementConstants.EMPTY_STRING;

		if (correspondenceDataBean.isRenderCrspdForVO()
				|| correspondenceDataBean.isRenderCrspdTPLForVO()
				|| correspondenceDataBean.isRenderCrspdUnEnrolledMemberForVO()
				|| correspondenceDataBean
						.isRenderCrspdUnEnrolledProviderForVO()) {
			
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceForVO();
		} else if (correspondenceDataBean.isRenderCrspdMemberForVO()) {
			
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceForMemberVO();
		} else if (correspondenceDataBean.isRenderCrspdProviderForVO()) {
			
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO()
					.getCorrespondenceForProviderVO();
		}
		//ADDED FOR THE Correspondence ESPRD00436016
		else if (correspondenceDataBean.isRenderCrspdTrdPartForVO()) {
			
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO()
					.getCorrespondenceForTradingPartnerVO();
		}

		correspondenceEntity = correspondenceForVO.getEntitySysId()
				+ ContactManagementConstants.COLON_SEPARATOR
				+ correspondenceForVO.getEntityTypeCode();
		if(logger.isDebugEnabled())
		{
		logger.debug("correspondenceEntity for addnl crspd "
				+ correspondenceEntity);
		}

		return correspondenceEntity;
	}

	/**
	 * @return String
	 */
	public String getInquiringAboutEntityDetails() {
		

		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
		if (indexCode == null) {
			indexCode = "0";
		}
		int index = Integer.parseInt(indexCode);
		correspondenceDataBean.setStartIndexForInqAbt((index/10)*10);
		correspondenceDataBean.setInquiringAboutIndex(index);

		

		CorrespondenceForVO correspondenceForVO = (CorrespondenceForVO) correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
				.getListOfEnquiryAboutEntities().get(index);

		if (ContactManagementConstants.ENTITY_TYPE_PROV
				.equalsIgnoreCase(correspondenceForVO.getEntityTypeCode())) {
			CorrespondenceForProviderVO correspondenceForProviderVO = (CorrespondenceForProviderVO) correspondenceForVO;
			correspondenceDataBean
					.setCorrespondenceForProviderVO(correspondenceForProviderVO);
			correspondenceDataBean.setRenderInqAbtForProviderVO(true);
			correspondenceDataBean.setRenderInqAbtForMemberVO(false);
			correspondenceDataBean.setRenderInqAbtForVO(false);
			correspondenceDataBean.setRenderInqAbtForTradingPartnerVO(false);
		} else if (ContactManagementConstants.ENTITY_TYPE_MEM
				.equalsIgnoreCase(correspondenceForVO.getEntityTypeCode())) {
			CorrespondenceForMemberVO correspondenceForMemberVO = (CorrespondenceForMemberVO) correspondenceForVO;
			correspondenceDataBean
					.setCorrespondenceForMemberVO(correspondenceForMemberVO);
			correspondenceDataBean.setRenderInqAbtForMemberVO(true);
			correspondenceDataBean.setRenderInqAbtForProviderVO(false);
			correspondenceDataBean.setRenderInqAbtForVO(false);
			correspondenceDataBean.setRenderInqAbtForTradingPartnerVO(false);
		} //ADDED FOR THE Correspondence ESPRD00436016
		else if (ContactManagementConstants.ENTITY_TYPE_TD
				.equalsIgnoreCase(correspondenceForVO.getEntityTypeCode())) {
			CorrespondenceForTradingPartnerVO correspondenceForTradingPartnerVO = (CorrespondenceForTradingPartnerVO) correspondenceForVO;
			correspondenceDataBean
					.setCorrespondenceForTradingPartnerVO(correspondenceForTradingPartnerVO);
			correspondenceDataBean.setRenderInqAbtForTradingPartnerVO(true);
			correspondenceDataBean.setRenderInqAbtForMemberVO(false);
			correspondenceDataBean.setRenderInqAbtForProviderVO(false);
			correspondenceDataBean.setRenderInqAbtForVO(false);
			//correspondenceDataBean.setRenderInqAbtForTradingPartnerVO(false);
		} else {
			correspondenceDataBean.setCorrespondenceForVO(correspondenceForVO);
			correspondenceDataBean.setRenderInqAbtForVO(true);
			correspondenceDataBean.setRenderInqAbtForMemberVO(false);
			correspondenceDataBean.setRenderInqAbtForProviderVO(false);
			correspondenceDataBean.setRenderInqAbtForTradingPartnerVO(false);
		}

		return "success";
	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 * @return boolean
	 */
	protected boolean requiredFldsAvbl(
			CorrespondenceRecordVO correspondenceRecordVO) {
		
		boolean fldsAvbl = true;

		if (isNullOrEmptyField(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getDepartment())) {
			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.WORKUNIT },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"deptName");
		}
		if (isNullOrEmptyField(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getSourceCode())) {
			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { "Source" },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"srcName");
		}
		if (isNullOrEmptyField(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getCategorySK())) {
			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.CATEGORY },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"catName");
		}
		if (isNullOrEmptyField(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getSubjectCode())) {
			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.SUBJECT },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"subName");
		}
		if (isNullOrEmptyField(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getPriorityCode())) {

			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { "Priority" },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"priorityType");
		}
		if (isNullOrEmptyField(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getStatusCode())) {
			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.STATUS },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"statusType");
		}
		if (correspondenceRecordVO.getCorrespondenceDetailsVO()
				.getReceivedDate() != null
				&& correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getReceivedDate().trim().length() > 0) {
			if (!EnterpriseCommonValidator.validateDate(correspondenceRecordVO
					.getCorrespondenceDetailsVO().getReceivedDate())) {
				fldsAvbl = false;
				setErrorMessage(
						EnterpriseMessageConstants.ERR_INVALID_DATEFORMAT,
						new Object[] { ContactManagementConstants.RECEIVED_DATE },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						"crRecDate");
			}
		}
		/** To Check if Notes is added are not */
		/*
		 * if (correspondenceDataBean.getCrNotesList().isEmpty()) { fldsAvbl =
		 * false; setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
		 * new Object[] {ContactManagementConstants.NOTES},
		 * MessageUtil.ENTMESSAGES_BUNDLE, null); }
		 */

		/**
		 * validating TCN
		 */
		// Code modified for defect ESPRD00838200 to remove the success message on validating TCN field on major save.
		if (correspondenceRecordVO.getCorrespondenceDetailsVO().getTcn() != null
				&& correspondenceRecordVO.getCorrespondenceDetailsVO().getTcn()
						.trim().length() > 0) {

			/*fldsAvbl = EnterpriseCommonValidator
					.validateAlphaNumeric(correspondenceRecordVO
							.getCorrespondenceDetailsVO().getTcn());*/
			if (!EnterpriseCommonValidator
					.validateAlphaNumeric(correspondenceRecordVO
							.getCorrespondenceDetailsVO().getTcn())) {
				fldsAvbl = false;
				setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
						new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
						"crTCN");
			}
		}
		/** validation for custom fields */

		if (validateCustFlds()) {
			fldsAvbl = false;
			/*
			 * setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED, new
			 * Object[] { "Custom Fields" }, MessageUtil.ENTMESSAGES_BUNDLE,
			 * "customFldValues");
			 */
		}

		/**
		 * Implementing BigSave - SmallSave
		 */
		if (getRoutingDataBean().isRenderAddRouting()
				&& !correspondenceDataBean.isRoutingSaved())

		{
			getRoutingControllerBean().saveRouting();
			if (!correspondenceDataBean.isRoutingValidated()) {
				fldsAvbl = false;
			}
		}
		if(logger.isDebugEnabled())
		{
		logger.debug("isRenderAddAlert--->"
				+ getAlertDataBean().isRenderAddAlert());
		logger
				.debug("isAlertSaved--->"
						+ correspondenceDataBean.isAlertSaved());
		}
		if ((getAlertDataBean().isRenderAddAlert() && !correspondenceDataBean
				.isAlertSaved())
				|| (getAlertDataBean().isRenderEditAlert() && !correspondenceDataBean
						.isAlertSaved())) {
			
			getAlertControllerBean().saveAlert();
			if (!correspondenceDataBean.isAlertValidated()) {
				
				fldsAvbl = false;
			}
		}

		if (correspondenceRecordVO.getCorrespondenceDetailsVO().getStatusCode() != null
				&& correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getStatusCode().equals("C")) {

			FacesContext fc = FacesContext.getCurrentInstance();

			LettersAndResponsesDataBean lettersAndResponsesDataBean = (LettersAndResponsesDataBean) fc
					.getApplication()
					.createValueBinding(
							ContactManagementConstants.BINDING_BEGIN_SEPARATOR
									+ "LettersAndResponsesDataBean"
									+ ContactManagementConstants.BINDING_END_SEPARATOR)
					.getValue(fc);

			List letterGenerationRequestsList = lettersAndResponsesDataBean
					.getLetterGenerationRequests();
			LetterGenerationInputVO letterGenerationInputVO = null;
			if (letterGenerationRequestsList != null
					&& letterGenerationRequestsList.size() > 0) {
				Iterator itr = letterGenerationRequestsList.iterator();
				while (itr.hasNext()) {
					letterGenerationInputVO = (LetterGenerationInputVO) itr
							.next();
					if (letterGenerationInputVO != null) {
						String ltrReqDispCD = letterGenerationInputVO
								.getLtrReqDispCD();
						if (GlobalLetterConstants.LTR_REQ_STATUS_CODE_NEW
								.equals(ltrReqDispCD)
								|| GlobalLetterConstants.LTR_REQ_STATUS_CODE_INREVIEW
										.equals(ltrReqDispCD)
								|| GlobalLetterConstants.LTR_REQ_STATUS_CODE_APPROVED
										.equals(ltrReqDispCD)) {
							fldsAvbl = false;
							setErrorMessage(
									EnterpriseMessageConstants.ERR_CANT_CLOSE_CR,
									new Object[] { ContactManagementConstants.STATUS },
									MessageUtil.ENTMESSAGES_BUNDLE,
									"statusType");
							break;
						}
					}
				}
			}
		}
		
		// Below code added for the defect - ESPRD00843786 to fix notes missing issue.
		if (commonEntityDataBean.isSaveNotesChk()) {
			CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
			boolean valid = false;
			valid = commonEntityValidator.validateNotes(commonEntityDataBean
					.getCommonEntityVO().getCommonNotesVO());
			if (!valid) {
				fldsAvbl = false;
			}
		} 
		/**
		 * Code is added below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
		
		if(fldsAvbl == false)
		{
			correspondenceDataBean.setLogCRErrMsgFlag(true);
		}
		else
		{
			correspondenceDataBean.setLogCRErrMsgFlag(false);
		}
		return fldsAvbl;
	}

	/**
	 * This operation is used to get the reference data.
	 */
	public void getReferenceData() {
		

		List referenceList = new ArrayList();
		Map referenceMap = null;

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try {
			String businessUnitDesc = null;
			List buinessUnitDescs = contactMaintenanceDelegate
					.getBuisnessUnitsDescs(getUserID());
			if( buinessUnitDescs != null && !buinessUnitDescs.isEmpty() ) {
				if (buinessUnitDescs.size() > 1) {
					businessUnitDesc = ContactManagementConstants.AllOthers;
				} else {
					businessUnitDesc = (String) buinessUnitDescs.get(0);
				}
			}
			correspondenceDataBean.setBusUnit(businessUnitDesc);
		} catch (LOBHierarchyFetchBusinessException lobExp) {
			if(logger.isErrorEnabled())
			{
			logger.error(lobExp.getMessage(), lobExp);
			}
		}
		String busUnit = correspondenceDataBean.getBusUnit();
		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

		createInputCriterias(referenceList, busUnit);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			if (referenceMap != null) {

				Class[] argtypes = new Class[] { Map.class, String.class,
						String.class };
				Executor[] executor = new Executor[10];
				/*
				 * List refStatusList = getReferenceList(referenceMap,
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * String.valueOf(ContactManagementHelper
				 * .getSystemlistForCorrBU(
				 * ContactManagementConstants.CORR_STATUS, busUnit)));
				 */
				executor[0] = call(this, "getReferenceList", new Object[] {
						referenceMap,
						FunctionalAreaConstants.CONTACT_MGMT,
						String.valueOf(ContactManagementHelper
								.getSystemlistForCorrBU(
										ContactManagementConstants.CORR_STATUS,
										busUnit)) }, argtypes);
				/*
				 * List refSubjectList = getReferenceList(referenceMap,
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);
				 */

				executor[1] = call(this, "getReferenceList", new Object[] {
						referenceMap, FunctionalAreaConstants.CONTACT_MGMT,
						ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK },
						argtypes);
				/*
				 * List refSourceList = getReferenceList(referenceMap,
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * //ReferenceServiceDataConstants.G_CRSPD_SRC_CD);
				 * String.valueOf(ContactManagementHelper
				 * .getSystemlistForCorrBU(
				 * ContactManagementConstants.CORR_SOURCE, busUnit)));
				 */
				executor[2] = call(this, "getReferenceList", new Object[] {
						referenceMap,
						FunctionalAreaConstants.CONTACT_MGMT,
						String.valueOf(ContactManagementHelper
								.getSystemlistForCorrBU(
										ContactManagementConstants.CORR_SOURCE,
										busUnit)) }, argtypes);
				/*
				 * List refResolutionList = getReferenceList( referenceMap,
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * //ReferenceServiceDataConstants.G_CR_RESLTN_CD); String
				 * .valueOf(ContactManagementHelper .getSystemlistForCorrBU(
				 * ContactManagementConstants.CORR_RESOLUTION, busUnit)));
				 */
				executor[3] = call(
						this,
						"getReferenceList",
						new Object[] {
								referenceMap,
								FunctionalAreaConstants.CONTACT_MGMT,
								String
										.valueOf(ContactManagementHelper
												.getSystemlistForCorrBU(
														ContactManagementConstants.CORR_RESOLUTION,
														busUnit)) }, argtypes);
				/*
				 * List refPriorityList = getReferenceList(referenceMap,
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * ReferenceServiceDataConstants.G_CR_PRTY_CD);
				 */
				executor[4] = call(this, "getReferenceList", new Object[] {
						referenceMap, FunctionalAreaConstants.CONTACT_MGMT,
						ReferenceServiceDataConstants.G_CR_PRTY_CD }, argtypes);

				/*
				 * List refLobList = getReferenceList(referenceMap,
				 * FunctionalAreaConstants.REFERENCE,
				 * ReferenceServiceDataConstants.R_LOB_CD);
				 */
				executor[5] = call(this, "getReferenceList", new Object[] {
						referenceMap, FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.R_LOB_CD }, argtypes);
				/*
				 * List refDentalApptList = getReferenceList(referenceMap,
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * ReferenceServiceDataConstants.G_DENTAL_APPT_TY_CD);
				 */
				executor[6] = call(this, "getReferenceList", new Object[] {
						referenceMap, FunctionalAreaConstants.CONTACT_MGMT,
						ReferenceServiceDataConstants.G_DENTAL_APPT_TY_CD },
						argtypes);
				/*
				 * List refReferredToList = getReferenceList(referenceMap,
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * ReferenceServiceDataConstants.G_REFERRED_TO_TY_CD);
				 */
				if (busUnit != null && busUnit.equalsIgnoreCase("ARS")) {
					executor[7] = call(
							this,
							"getReferenceList",
							new Object[] {
									referenceMap,
									FunctionalAreaConstants.CONTACT_MGMT,
									ReferenceServiceDataConstants.G_REFERRED_TO_TY_CD_ARS },
							argtypes);
				} else {
					executor[7] = call(
							this,
							"getReferenceList",
							new Object[] {
									referenceMap,
									FunctionalAreaConstants.CONTACT_MGMT,
									ReferenceServiceDataConstants.G_REFERRED_TO_TY_CD },
							argtypes);
				}

				//correspondenceDataBean.setStatValidValues(refStatusList);
				correspondenceDataBean.setStatValidValues((List) executor[0]
						.get());
				//correspondenceDataBean.setSubjectValidValues(refSubjectList);
				correspondenceDataBean.setSubjectValidValues((List) executor[1]
						.get());
				searchCorrespondenceDataBean.getSubjectMap().clear();
				Map subjectMap = new HashMap();
				List subjectVVList = correspondenceDataBean
						.getSubjectValidValues();
				if (subjectVVList != null && subjectVVList.size() > 0) {
					for (Iterator iter = subjectVVList.iterator(); iter
							.hasNext();) {
						SelectItem selectItem = (SelectItem) iter.next();
						subjectMap.put(selectItem.getValue(), selectItem
								.getLabel());
					}
					searchCorrespondenceDataBean.setSubjectMap(subjectMap);
				}
				Map statusMap = new HashMap();
				List statusVVList = correspondenceDataBean.getStatValidValues();
				if (statusVVList != null && statusVVList.size() > 0) {
					for (Iterator iter = statusVVList.iterator(); iter
							.hasNext();) {
						SelectItem selectItem = (SelectItem) iter.next();
						statusMap.put(selectItem.getValue(), selectItem
								.getLabel());
					}
					searchCorrespondenceDataBean.setStatusMap(statusMap);
				}
				// correspondenceDataBean.setSourceVVList(refSourceList);
				correspondenceDataBean
						.setSourceVVList((List) executor[2].get());
				//correspondenceDataBean.setResolnVVList(refResolutionList);
				correspondenceDataBean
						.setResolnVVList((List) executor[3].get());
				//correspondenceDataBean.setPriorityVVList(refPriorityList);
				correspondenceDataBean.setPriorityVVList((List) executor[4]
						.get());
				//correspondenceDataBean.setLobVVList(refLobList);
				correspondenceDataBean.setLobVVList((List) executor[5].get());
				// ADDED for client services
				//correspondenceDataBean.setDentalApptMadeByList(refDentalApptList);
				correspondenceDataBean
						.setDentalApptMadeByList((List) executor[6].get());
				//correspondenceDataBean.setReferredToList(refReferredToList);
				correspondenceDataBean.setReferredToList((List) executor[7]
						.get());
				// getAllCategories(getUserID());
				correspondenceDataBean.setReferredToVOList(new ArrayList(
						correspondenceDataBean.getReferredToList()));

			}
		} catch (ReferenceServiceRequestException e) {
			
			e.printStackTrace();
		} catch (SystemListNotFoundException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	private Executor call(Object object, String methodName, Object[] args,
			Class[] argtypes) {
		Executor e = new Executor(object, methodName, args, argtypes);
		e.start();
		return e;
	}

	/**
	 * This method is used to created the input criteria to get the reference
	 * date.
	 * 
	 * @param referenceList :
	 *            List of Reference Data.
	 * @param busUnit :
	 *            busUnit to set
	 */
	protected void createInputCriterias(List referenceList, String busUnit) {
		

		InputCriteria ipCrtStatusCode = new InputCriteria();
		ipCrtStatusCode.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		/*
		 * ipCrtStatusCode
		 * .setElementName(ReferenceServiceDataConstants.G_CR_STAT_CD);
		 */
		ipCrtStatusCode.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.CORR_STATUS,
						busUnit));

		InputCriteria ipCrtSubjectCode = new InputCriteria();
		ipCrtSubjectCode
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		ipCrtSubjectCode
				.setElementName(ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);

		InputCriteria ipCrtSourceCode = new InputCriteria();
		ipCrtSourceCode.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		ipCrtSourceCode.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.CORR_SOURCE,
						busUnit));

		InputCriteria ipCrtResolutionCode = new InputCriteria();
		ipCrtResolutionCode
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		/*
		 * ipCrtResolutionCode
		 * .setElementName(ReferenceServiceDataConstants.G_CR_RESLTN_CD);
		 */
		ipCrtResolutionCode.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(
						ContactManagementConstants.CORR_RESOLUTION, busUnit));

		InputCriteria ipCrtPriorityCode = new InputCriteria();
		ipCrtPriorityCode
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		ipCrtPriorityCode
				.setElementName(ReferenceServiceDataConstants.G_CR_PRTY_CD);

		InputCriteria ipCrtLobCode = new InputCriteria();
		ipCrtLobCode.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		ipCrtLobCode.setElementName(ReferenceServiceDataConstants.R_LOB_CD);

		InputCriteria ipCrtDentalApptMadeBy = new InputCriteria();
		ipCrtDentalApptMadeBy
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		ipCrtDentalApptMadeBy
				.setElementName(ReferenceServiceDataConstants.G_DENTAL_APPT_TY_CD);

		InputCriteria ipCrtReferredTo = new InputCriteria();
		ipCrtReferredTo.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		ipCrtReferredTo
				.setElementName(ReferenceServiceDataConstants.G_REFERRED_TO_TY_CD);

		referenceList.add(ipCrtStatusCode);
		referenceList.add(ipCrtSubjectCode);
		referenceList.add(ipCrtSourceCode);
		referenceList.add(ipCrtResolutionCode);
		referenceList.add(ipCrtPriorityCode);
		referenceList.add(ipCrtLobCode);
		referenceList.add(ipCrtDentalApptMadeBy); //ADDED for Client Services
		referenceList.add(ipCrtReferredTo); //ADDED for Client Services

	}

	/**
	 * This method is used to get the reference service list from the reference
	 * map based on the fuctional area constant and elementName
	 * 
	 * @param referenceMap :
	 *            Map of reference list.
	 * @param functionalAreaConstant :
	 *            Reference Service Functional Area Constants to get.
	 * @param elementName :
	 *            Reference Service Element to get.
	 * @return tempRevenueTypeList : Temporary Reference Service List.
	 */
	protected List getReferenceList(Map referenceMap,
			String functionalAreaConstant, String elementName) {
		

		List referenceList = null;
		List tempRevenueTypeList = new ArrayList();

		referenceList = (List) referenceMap.get(functionalAreaConstant
				+ ProgramConstants.HASH_SEPARATOR + elementName);

		if (referenceList != null) {
			int referenceListSize = referenceList.size();

			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);
				tempRevenueTypeList.add(new SelectItem(refVo
						.getValidValueCode(), refVo.getValidValueCode()
						+ ProgramConstants.HYPHEN_SEPARATOR
						+ refVo.getShortDescription()));
			}
		}

		return tempRevenueTypeList;
	}

	public void getAllCategoriesForList(String userID) {

		

		List listOfCategoryDOs = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		List categoryList = correspondenceDataBean.getCategoryList();
		categoryList.clear();

		try {
			listOfCategoryDOs = contactMaintenanceDelegate
					.getAllCategoriesForList(userID);
			correspondenceDataBean.setListOfCategoryDOs(listOfCategoryDOs);
		} catch (CategoryFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		setCategoryList(listOfCategoryDOs);

		return;
	}

	/**
	 * This operation is used to get all the Categories. *
	 * 
	 * @param userID :
	 *            The User ID to set.
	 */
	public void getAllCategories(String userID) {
		

		List listOfCategoryDOs = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			listOfCategoryDOs = contactMaintenanceDelegate
					.getAllCategories(userID);

			correspondenceDataBean.setListOfCategoryDOs(listOfCategoryDOs);
		} catch (CategoryFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		setCategoryList(listOfCategoryDOs);

		return;
	}

	public void setCategoryList(List listOfCategoryDOs) {
		

		List categoryList = correspondenceDataBean.getCategoryList();
		categoryList.clear();

		if (listOfCategoryDOs != null) {
			CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();

			Map categoryMap = new HashMap();
			List sortCategory = new ArrayList();
			for (Iterator iter = listOfCategoryDOs.iterator(); iter.hasNext();) {
				CorrespondenceCategory categoryDO = (CorrespondenceCategory) iter
						.next();
				CategoryVO categoryVO = categoryDOConvertor
						.convertLogCorrCategoryDOToVO(categoryDO);

				if (categoryDO != null) {

					sortCategory.add(categoryVO.getCategoryDesc());
					categoryMap.put(categoryVO.getCategoryDesc(), categoryVO
							.getCategorySK().toString());

				}
			}
			Collections.sort(sortCategory);
			for (Iterator iter = sortCategory.iterator(); iter.hasNext();) {
				Object obj = iter.next();
				categoryList.add(new SelectItem(
						categoryMap.get(obj).toString(), obj.toString()));
			}
		}

		return;
	}

	/**
	 * @param categorySearchCriteriaVO
	 *            categorySearchCriteriaVO to set
	 */
	public void getAllCategories(
			CategorySearchCriteriaVO categorySearchCriteriaVO) {
		

		List listOfCategoryDOs = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		List categoryList = correspondenceDataBean.getCategoryList();
		categoryList.clear();

		try {
			listOfCategoryDOs = contactMaintenanceDelegate
					.getAllCategories(categorySearchCriteriaVO);

			correspondenceDataBean.setListOfCategoryDOs(listOfCategoryDOs);
		} catch (CategoryFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		if (listOfCategoryDOs != null) {
			CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();

			for (Iterator iter = listOfCategoryDOs.iterator(); iter.hasNext();) {
				CorrespondenceCategory categoryDO = (CorrespondenceCategory) iter
						.next();
				CategoryVO categoryVO = categoryDOConvertor
						.convertLogCorrCategoryDOToVO(categoryDO);
				if (categoryDO != null) {
					if (!categoryDO.getVoidIndicator().booleanValue()) {
						categoryList.add(new SelectItem(categoryVO
								.getCategorySK().toString(), categoryVO
								.getCategoryDesc()));
					}

				}

			}
		}

		return;
	}

	/**
	 * @return String
	 * @throws EnterpriseBaseDataException
	 */

	public String getSubjectsForCategory(ValueChangeEvent event)
			throws EnterpriseBaseDataException {
		try {
			

			if (event.getNewValue() != null) {
				String categorySK = (String) event.getNewValue();

				if (isNullOrEmptyField(categorySK)) {
					return ContactManagementConstants.EDMS_RENDER_SUCCESS;
				}
				String businessUnitSK = "";

				List listOfCategoryDOs = correspondenceDataBean
						.getListOfCategoryDOs();
				CorrespondenceCategory correspondenceCategory = getSelectedCategory(
						categorySK, listOfCategoryDOs);
				if (correspondenceCategory != null) {
					ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
					//businessUnitSK =
					// contactMaintenanceDelegate.getCategoryTypeCode(categorySK);
					businessUnitSK = correspondenceCategory
							.getCategoryTypeCode();

					String entityType = correspondenceDataBean
							.getCorrespondenceRecordVO()
							.getCorrespondenceForVO().getEntityTypeCode();

					/*
					 * Get Call Script based on Category. Hence Entity Type Code
					 * and Subject Code should be passed as null.
					 */
					String entityTypeCode = null;
					String subjectCode = null;

					Map catRefreshDataMap = contactMaintenanceDelegate
							.getCategoryRefreshData(entityTypeCode, Long
									.valueOf(categorySK), subjectCode);

					if (catRefreshDataMap != null
							&& catRefreshDataMap.size() > 0) {
						CallScript callScript = (CallScript) catRefreshDataMap
								.get(ContactManagementConstants.CALLSCRIPT_DESC);
						if (callScript != null) {
							correspondenceDataBean.getCorrespondenceRecordVO()
									.setCallScriptDesc(
											callScript.getDescription());
							correspondenceDataBean.getCorrespondenceRecordVO()
									.setCallScriptSK(
											callScript.getCallScriptSK()
													.toString());
							correspondenceDataBean
									.getCorrespondenceRecordVO()
									.setCallScriptText(
											(callScript.getCex() == null) ? null
													: callScript
															.getCex()
															.getDescriptionClob());
						}

						Set catSubjXrefs = (Set) catRefreshDataMap
								.get(ContactManagementConstants.CAT_SUBXREFS);
						if (correspondenceDataBean.getCategorySubjectValues() != null) {
							correspondenceDataBean.getCategorySubjectValues()
									.clear();
						}
						if (catSubjXrefs != null && catSubjXrefs.size() > 0) {
							List categorySubjectsList = correspondenceDataBean
									.getCategorySubjectValues();
							categorySubjectsList.clear();
							for (Iterator iterator = catSubjXrefs.iterator(); iterator
									.hasNext();) {
								CategorySubjectXref catSubjAux = (CategorySubjectXref) iterator
										.next();

								SubjectAuxillary subjectAux = catSubjAux
										.getSubjectAuxillary();
								categorySubjectsList
										.add(new SelectItem(
												subjectAux.getSubjectCode(),
												getDescriptionFromVV(
														subjectAux
																.getSubjectCode(),
														correspondenceDataBean
																.getSubjectValidValues())));
								sortSubjectSequence(categorySubjectsList);
							}
						}

						List cfList = (List) catRefreshDataMap
								.get(ContactManagementConstants.CSTMFLD_DETAILS);
						correspondenceDataBean.getCustomFieldDOList().clear();
						correspondenceDataBean.getCustomFieldVOList().clear();
						if (cfList != null && !cfList.isEmpty()) {
							DynamicCustomFieldHelper customFieldHelper=new DynamicCustomFieldHelper();
							correspondenceDataBean.setCustomFieldDOList(cfList);
							correspondenceDataBean.setRenderCustomFlds(true);

							//showDynamic(cfList, categorySK, false);
							correspondenceDataBean.setCustomFieldVOList(customFieldHelper
			                		.showDynamicFields(cfList
			                		, false
									, null,correspondenceDataBean.isUpdateMode()));
						} else {
							correspondenceDataBean.setRenderCustomFlds(false);
						}
					}

					/*
					 * setCallScriptDetails(entityTypeCode,
					 * Long.valueOf(categorySK), subjectCode);
					 * getsubjects(categorySK); getCustomFields(categorySK);
					 */
					setPriorityAndSupAppReq(correspondenceCategory);
					if(logger.isDebugEnabled())
					{
					logger.debug("After Category Priority Code "
							+ correspondenceDataBean
									.getCorrespondenceRecordVO()
									.getCorrespondenceDetailsVO()
									.getPriorityCode());
					logger.debug("After Category SAR "
							+ correspondenceDataBean
									.getCorrespondenceRecordVO()
									.getCorrespondenceDetailsVO()
									.getSprvsrRevwReqdIndicator());
					}

					if (categorySK != null) {
						if (businessUnitSK != null
								&& businessUnitSK
										.equals(ContactManagementConstants.CATEGORYSK_MS)) {
							correspondenceDataBean
									.setRenderClientServices(true);
							if (ContactManagementConstants.ENTITY_TYPE_UNMEM
									.equals(entityType)
									|| ContactManagementConstants.ENTITY_TYPE_MEM
											.equals(entityType)) {
								correspondenceDataBean
										.setRenderLangSpoken(true);
							}
						} else {
							correspondenceDataBean
									.setRenderClientServices(false);
						}
					}
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//for fixing defect ESPRD00576206
		/*correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().setLtdEngProficiency("Yes");*/
		
		//Added for fixing the Defect ID : ESPRD00754735 from yes to no for Limited English Proficiency in Log Correspondence...
		correspondenceDataBean.getCorrespondenceRecordVO()
		.getCorrespondenceDetailsVO().setLtdEngProficiency("No");
		
		/*correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().setVipPresent("Yes");
		*/
		
		//Added for fixing the Defect ID : ESPRD00754735 from yes to no for VIP Radio Button in Log Correspondence...
		correspondenceDataBean.getCorrespondenceRecordVO()
		.getCorrespondenceDetailsVO().setVipPresent("No");
		
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * Method for getting Custom Fields
	 * 
	 * @param categorySK
	 *            categorySK to set
	 */
	public void getCustomFields(String categorySK) {

		ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
		List cfList = null;
		if (categorySK != null
				&& !categorySK.equals(ContactManagementConstants.EMPTY_STRING)) {
			try {
				cfList = delegate.getCustomFieldForCategory(Long
						.valueOf(categorySK));
			} catch (CustomFieldFetchBusinessException e) {
				e.printStackTrace();

			} finally {
				
			}
		}
		correspondenceDataBean.getCustomFieldDOList().clear();
		if (cfList != null && !cfList.isEmpty()) {
			correspondenceDataBean.setCustomFieldDOList(cfList);
			correspondenceDataBean.setRenderCustomFlds(true);
			//Panel Grid Issue fix showDynamic(cfList, categorySK, false);
		} else {
			correspondenceDataBean.setRenderCustomFlds(false);
		}

	}

	/**
	 * @param list
	 *            list to set
	 * @param categorySK
	 *            categorySK to set
	 * @param flag
	 *            flag to set
	 *//* Panel Grid Heap Dump Issue fix
	private void showDynamic(List list, String categorySK, boolean flag) {
		logger.debug("showDynamic is started");

		CustomField field = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application app = facesContext.getApplication();

		int cfListSize = 0;
		String bindingValue = null;
		String required = null;
		List children = correspondenceDataBean.getFormElements().getChildren();
		boolean isUpdateMode = correspondenceDataBean.isUpdateMode();
		logger.info("isUpdateMode====" + isUpdateMode);
		children.clear();
		logger.debug("From grid childrens are $$ " + children);
		DynamicCustomFieldHelper customFieldHelper = new DynamicCustomFieldHelper();
		if (list != null && !list.isEmpty()) {
			logger.debug("After getting CF list size is $$ " + list.size());

			cfListSize = list.size();
			for (int i = 0; i < cfListSize; i++) {
				field = (CustomField) list.get(i);
				logger
						.debug("CustomField Sk is $$ "
								+ field.getCustomFieldSK());
				if (field.getDataTypeCode().equals("CB")) {
					bindingValue = customFieldHelper
							.generateBindingValueForChkBox(i,
									"CorrespondenceDataBean");
				} else {
					bindingValue = customFieldHelper.generateBindingValue(i,
							"CorrespondenceDataBean");
				}
				logger.debug("Binding value for element " + i + " is "
						+ bindingValue);
				//correspondenceDataBean.getCustomFieldValueVO().get
				if (field.getRequiredValueIndicator().booleanValue()) {
					required = "*";
				} else {
					required = "";
				}
				if (field.getDataTypeCode().equals("D")) {
					logger
							.debug("Your selected component is Date and Label is "
									+ field.getDescription());
					customFieldHelper.getRequiredText(children, categorySK,
							"r2" + i, required);
					customFieldHelper.getLabelComponent(children, categorySK,
							"t2" + i, field.getDescription(), categorySK
									+ "calendar" + i);
					customFieldHelper.getSpace(children, categorySK, "o" + i);
					customFieldHelper.getDateComponent(children, categorySK,
							"calendar" + i, field, app, bindingValue, flag,
							isUpdateMode);
				} else if (field.getDataTypeCode().equals("N")
						|| field.getDataTypeCode().equals("T")
						|| field.getDataTypeCode().equals("DA")) {
					customFieldHelper.getRequiredText(children, categorySK,
							"r2" + i, required);
					customFieldHelper.getLabelComponent(children, categorySK,
							"t3" + i, field.getDescription(), categorySK
									+ "input" + i);
					customFieldHelper.getSpace(children, categorySK, "o" + i);
					customFieldHelper.getInputComponent(children, categorySK,
							"input" + i, field, app, bindingValue, flag,
							isUpdateMode);
				} else if (field.getDataTypeCode().equals("DD")) {
					customFieldHelper.getRequiredText(children, categorySK,
							"r2" + i, required);
					customFieldHelper.getLabelComponent(children, categorySK,
							"t3" + i, field.getDescription(), categorySK
									+ "select" + i);
					customFieldHelper.getSpace(children, categorySK, "o" + i);
					customFieldHelper.getDropDownComponent(children,
							categorySK, "select" + i, field, app, bindingValue,
							flag, isUpdateMode);
				} else if (field.getDataTypeCode().equals("CB")) {
					customFieldHelper.getRequiredText(children, categorySK,
							"r2" + i, required);
					customFieldHelper.getLabelComponent(children, categorySK,
							"t3" + i, field.getDescription(), categorySK + "cb"
									+ i);
					customFieldHelper.getSpace(children, categorySK, "o" + i);
					customFieldHelper.getCheckBoxComponent(children,
							categorySK, "cb" + i, field, app, bindingValue,
							flag, isUpdateMode);
				}
			}
		}
	}*/

	/**
	 * Method to validate the Custom fields.
	 * 
	 * @return boolean.
	 */
	protected boolean validateCustFlds() {

		/** panel grid issue fix*/
		boolean flag = false;
        DynamicCustomFieldHelper customFieldHelper = new DynamicCustomFieldHelper();
        List cfList = correspondenceDataBean.getCustomFieldDOList();
        CustomField field = null;
        String value = null;
        if (cfList != null && !cfList.isEmpty())
        {
        	int cfListSize = 0;
            cfListSize = cfList.size();
			// Begin - Modified the code for the PanelGrid Fix
            List valuesList = new ArrayList(correspondenceDataBean.getCustomFieldVOList());
            for (int i = 0; i < cfListSize; i++)
            {
                field = (CustomField) cfList.get(i);
                if (field.getRequiredValueIndicator().booleanValue())
                {
                    CustomFieldVO customFieldVO = (CustomFieldVO) valuesList.get(i);
                    
                    if(!field.getDataTypeCode().equals("CB"))
                    {
                    	if(customFieldVO.getComponentInputData() == null || customFieldVO.getComponentInputData().equals(""))
	                    {
	                    	setErrorMessage(
	                    	 		EnterpriseMessageConstants.ERR_SW_REQUIRED,
	                                new Object[] {},
	                                MessageUtil.ENTMESSAGES_BUNDLE,
									"cfDataListId");
	                    	return true;
	                    }
                    }
                }
            }
			// End - Modified the code for the PanelGrid Fix			
        }

		return flag;
	}

	/**
	 * @param categorySK
	 *            The categorySK to set.
	 */
	public void getsubjects(String categorySK) {
		List listOfCategoryDOs = correspondenceDataBean.getListOfCategoryDOs();

		CorrespondenceCategory correspondenceCategory = getSelectedCategory(
				categorySK, listOfCategoryDOs);

		setCategorySubjectsList(correspondenceCategory);
	}

	/**
	 * @param categorySK
	 *            The categorySK to set.
	 */
	protected void setPriorityAndSupAppReq(CorrespondenceCategory selCategory) {
		
		//Added as part of this defect ESPRD00784386 - Additional related issue fixed.
		
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try 
		{
			EnterpriseUser user = contactMaintenanceDelegate
					.getEnterpriseUser(getLoggedInUser());

		/*
		 * List listOfCategories =
		 * correspondenceDataBean.getListOfCategoryDOs();
		 * 
		 * if (listOfCategories != null) { logger.debug("Categories not null");
		 * 
		 * CorrespondenceCategory selCategory = null;
		 * 
		 * for (Iterator iter = listOfCategories.iterator(); iter.hasNext();) {
		 * CorrespondenceCategory category = (CorrespondenceCategory) iter
		 * .next();
		 * 
		 * if (categorySK.equalsIgnoreCase(category.getCategorySK()
		 * .toString())) { selCategory = category; logger.debug("Found
		 * Category");
		 * 
		 * break; } }
		 * 
		 * logger.debug("Category Priority Code " +
		 * selCategory.getPriorityCode()); logger.debug("Category SAR " +
		 * selCategory.getSupervisorReviewReqIndicator());
		 * 
		 * correspondenceDataBean.getCorrespondenceRecordVO()
		 * .getCorrespondenceDetailsVO().setPriorityCode(
		 * selCategory.getPriorityCode());
		 * 
		 * if (!correspondenceDataBean.isAutomaticAlertForUserReq()) {
		 * correspondenceDataBean.getCorrespondenceRecordVO()
		 * .getCorrespondenceDetailsVO() .setSprvsrRevwReqdIndicator(
		 * selCategory.getSupervisorReviewReqIndicator()); }
		 * 
		 * if (selCategory.getSupervisorReviewReqIndicator().booleanValue()) {
		 * correspondenceDataBean.setAutomaticAlertForCategoryReq(true);
		 * removeClosedValFromStatus(); } }
		 * 
		 * if (listOfCategories == null) { logger.debug("Categories null"); }
		 */
		if(logger.isDebugEnabled())
		{
		logger.debug("Category Priority Code " + selCategory.getPriorityCode());
		logger.debug("Category SAR "
				+ selCategory.getSupervisorReviewReqIndicator());
		}

		correspondenceDataBean.getCorrespondenceRecordVO()
				.getCorrespondenceDetailsVO().setPriorityCode(
						selCategory.getPriorityCode());

			Boolean supervisorInd = user.getSupervisorIndicator();
		
			if (!correspondenceDataBean.isAutomaticAlertForUserReq()) 
			{
				if(selCategory.getSupervisorReviewReqIndicator() != null 
						&& selCategory.getSupervisorReviewReqIndicator().booleanValue() )
				{
					correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setSprvsrRevwReqdIndicator(
								selCategory.getSupervisorReviewReqIndicator());
				}
				/* Below condition is added for not to set the SprvsrRevwReqdIndicator value to false when SupresrvisorReview Indicator is true  for the logged in user
				  but SupresrvisorReview Indicator is false for the selected category :Defect ESPRD00870774 */
				else if(null != user && !user.getSupervisorReviewReqIndicator())
				{
					correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceDetailsVO().setSprvsrRevwReqdIndicator(false);
					
				}
			}

			if (selCategory.getSupervisorReviewReqIndicator().booleanValue())
			   {
					correspondenceDataBean.setAutomaticAlertForCategoryReq(true);
					if(supervisorInd != null && !supervisorInd.booleanValue())
					{
						removeClosedValFromStatus();
					}
					
				}
		}
	
		catch (CMEntityFetchBusinessException e) 
		{
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @param categorySK
	 *            The categorySK to set.
	 * @param listOfCategoryDOs
	 *            The listOfCategoryDOs to set.
	 * @return CorrespondenceCategory
	 */
	private CorrespondenceCategory getSelectedCategory(String categorySK,
			List listOfCategoryDOs) {

		if (listOfCategoryDOs == null) {
			return null;
		}

		for (Iterator iter = listOfCategoryDOs.iterator(); iter.hasNext();) {
			CorrespondenceCategory correspondenceCategory = (CorrespondenceCategory) iter
					.next();

			if (correspondenceCategory.getCategorySK().toString()
					.equalsIgnoreCase(categorySK)) {
				return correspondenceCategory;
			}
		}

		return null;
	}

	/**
	 * @param correspondenceCategory
	 *            The correspondenceCategory to set.
	 */
	private void setCategorySubjectsList(
			CorrespondenceCategory correspondenceCategory) {
		

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		Set catSubjXrefs = null;
		if (correspondenceCategory != null) {
			List categorySubjectsList = correspondenceDataBean
					.getCategorySubjectValues();

			categorySubjectsList.clear();

			// Adding explicit method to pick subjects

			try {
				catSubjXrefs = contactMaintenanceDelegate
						.getCatSubjXrefs(correspondenceCategory);

			} catch (Exception e) {
				e.printStackTrace();
			}

			//Set catSubjXrefs = correspondenceCategory.getCatSubjXrefs();

			if (catSubjXrefs != null) {
				for (Iterator iterator = catSubjXrefs.iterator(); iterator
						.hasNext();) {
					CategorySubjectXref catSubjAux = (CategorySubjectXref) iterator
							.next();

					SubjectAuxillary subjectAux = catSubjAux
							.getSubjectAuxillary();
					categorySubjectsList.add(new SelectItem(subjectAux
							.getSubjectCode(), getDescriptionFromVV(subjectAux
							.getSubjectCode(), correspondenceDataBean
							.getSubjectValidValues())));

				}
			}
		}

	}

	/**
	 * This method is used to create the depts lists based on UserID.
	 * 
	 * @param userSK :
	 *            The userSK to set.
	 */
	public void setDepartmentsList(String userSK) {
		
	
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		DeptUserBasicInfo deptUserBasicInfo=new DeptUserBasicInfo();
   	    List deptsList=new ArrayList();
   	   
		try {
			if (userSK != null && !("".equals(userSK))) {
				
				
				 /*deptsList = contactMaintenanceDelegate
						.getDepartmentUsers(Long.valueOf(userSK));*/
				// code added for CM-API start
				deptUserBasicInfo.setUserEnterpriseSK(Long.valueOf(userSK));
		   	    deptUserBasicInfo.setDataReqested(ProgramConstants.DUBI_Dept_Name_SK);
				deptUserBasicInfo= contactMaintenanceDelegate.getDeptUserBasicInfo(deptUserBasicInfo);
               if(deptUserBasicInfo != null)
                {
                	deptsList= deptUserBasicInfo.getDeptNameSKList();
                }
            // code added for CM-API end
               if(logger.isDebugEnabled())
               {
				logger.debug("Dept list size" + deptsList.size());
               }

				if (deptsList != null) {
					List listOfDepartments = correspondenceDataBean
							.getDepartmentsList();

					listOfDepartments.clear();
					for (Iterator iter = deptsList.iterator(); iter.hasNext();) {
						//DepartmentUser deptUser = (DepartmentUser) iter.next();
						
						// code added for CM-API start
						Object[] deptData = (Object[]) iter.next();
						
						String name= deptData[0].toString();
						Long deptsk= (Long) deptData[1];
						Long lobSk= (Long) deptData[2];
						

						Map mapOfDeptAndLobHier = correspondenceDataBean
								.getMapOfDeptAndLobHier();
						
						mapOfDeptAndLobHier.put(deptsk.toString(), lobSk.toString());
						listOfDepartments.add(new SelectItem(deptsk.toString(), name));
						
						 // code added for CM-API end
						
						 // code Commented for CM-API 

					/*	mapOfDeptAndLobHier.put(deptUser.getDepartmentSK()
								.toString(), deptUser.getDepartment()
								.getLineOfBusinessHierarchy()
								.getLineOfBusinessHierarchySK().toString());
		
						listOfDepartments.add(new SelectItem(deptUser
								.getDepartmentSK().toString(), deptUser
								.getDepartment().getName()));*/
					}
					if (ContactManagementConstants.INT_ONE == listOfDepartments
							.size()) {
						SelectItem item = (SelectItem) listOfDepartments.get(0);
						String deptSK = (String) item.getValue();
						autoPopulateDepartment(deptSK);
					}
					 if (listOfDepartments != null) {
						sortDept(listOfDepartments);
					}
				}
			}
		} catch (LOBHierarchyFetchBusinessException lobExp) {
			if(logger.isErrorEnabled())
			{
			logger.error(lobExp.getMessage(), lobExp);
			}
		}

	}

	/**
	 * @param deptSK :
	 *            Department SK to be auto populated.
	 */
	private void autoPopulateDepartment(String deptSK) {
		

		CorrespondenceDetailsVO correspondenceDetailsVO = correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO();

		
		correspondenceDetailsVO.setDepartment(deptSK);
		correspondenceDataBean.setAutoPopulatedDept(true);
		setReportingAndBusinessUnit(deptSK);

	}

	/**
	 * @param event
	 *            The event to set.
	 */

	public String getReportingAndBusinessUnit(ValueChangeEvent event) {
		

		if (event != null) {
			if (event.getNewValue() != null) {
				if (isNullOrEmptyField(event.getNewValue().toString())) {
					// return ContactManagementConstants.RENDER_SUCCESS;
					return ContactManagementConstants.EDMS_RENDER_SUCCESS;
				}

				String departmentSK = (String) event.getNewValue();

				

				setReportingAndBusinessUnit(departmentSK);
			}
		}
		/*
		 * FacesContext faces = FacesContext.getCurrentInstance();
		 * faces.renderResponse();
		 */
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;

	}

	/**
	 * @param departmentSK :
	 *            Department SK for which Reporting and Business Unit is to be
	 *            set.
	 */
	private void setReportingAndBusinessUnit(String departmentSK) {

		Map mapOfDeptAndLobHier = correspondenceDataBean
				.getMapOfDeptAndLobHier();

		

		String lobHierarchySK = (String) mapOfDeptAndLobHier.get(departmentSK);

		

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			Map rUnitAndBUnitMap = contactMaintenanceDelegate
					.getRUnitAndBUnit(Long.valueOf(lobHierarchySK));
			LineOfBusinessHierarchy reportingUnit = (LineOfBusinessHierarchy) rUnitAndBUnitMap
					.get("RUnit");
			LineOfBusinessHierarchy businessUnit = (LineOfBusinessHierarchy) rUnitAndBUnitMap
					.get("BUnit");

			

			if (reportingUnit != null) {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setReportingUnitName(
								reportingUnit.getLobHierarchyDescription());
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setReportingUnitSK(
								reportingUnit.getLineOfBusinessHierarchySK()
										.toString());
			} else {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setReportingUnitName(
								ContactManagementConstants.EMPTY_STRING);
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setReportingUnitSK(
								ContactManagementConstants.EMPTY_STRING);
			}
			if (businessUnit != null) {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setBusinessUnitName(
								businessUnit.getLobHierarchyDescription());
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setBusinessUnitSK(
								businessUnit.getLineOfBusinessHierarchySK()
										.toString());
			} else {
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setBusinessUnitName(
								ContactManagementConstants.EMPTY_STRING);
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setBusinessUnitSK(
								ContactManagementConstants.EMPTY_STRING);
			}
		} catch (LOBHierarchyFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * This is a ValueChangeListener method. It sets the date when the status of
	 * CR is changed.
	 * 
	 * @param event
	 *            The event to set.
	 */
	public String setStatusDate(ValueChangeEvent event) {

		if (event.getNewValue() != null) {
			String statusCode = event.getNewValue().toString();

			if (isNullOrEmptyField(statusCode)) {
				return ContactManagementConstants.EDMS_RENDER_SUCCESS;
			}

			Boolean allResponseGenerated = Boolean.TRUE;

			if (!correspondenceDataBean.getCorrespondenceRecordVO()
					.getListOfLettersAndResponses().isEmpty()) {
				allResponseGenerated = checkResponseStatus(statusCode);
			}

			if (allResponseGenerated.booleanValue()) {
				DateFormat dateFormat = new SimpleDateFormat(
						ContactManagementConstants.DATE_FORMAT);

				Calendar calendar = Calendar.getInstance();
				Date statusDate = calendar.getTime();
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().setStatusDate(
								dateFormat.format(statusDate));
			}
		}
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * This method is used to check the status of all the Letters and Responses
	 * created for this CR.
	 * 
	 * @param statusCode :
	 *            String Status Code.
	 * @return Boolean : true/false.
	 */
	private Boolean checkResponseStatus(String statusCode) {

		Boolean allResponseGenerated = Boolean.TRUE;

		if (ContactManagementConstants.STATUS_CLOSED
				.equalsIgnoreCase(statusCode)
				&& correspondenceDataBean.isUpdateMode()) {
			CMDelegate cmDelegate = new CMDelegate();
			Long correspondenceSK = Long.valueOf(correspondenceDataBean
					.getCorrespondenceRecordVO()
					.getCorrespondenceRecordNumber());
			try {
				allResponseGenerated = cmDelegate
						.verifyResponseStatus(correspondenceSK);
			} catch (CorrespondenceRecordFetchBusinessException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
				allResponseGenerated = Boolean.FALSE;
			}
		}

		if (!allResponseGenerated.booleanValue()) {
			setErrorMessage(
					ContactManagementConstants.ERR_CM_CR_RESPONSE_NOT_GENERATED,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					null);
		}

		return allResponseGenerated;
	}

	/**
	 * This method is used to get the UserSK given the userId.
	 * 
	 * @param userId :
	 *            String User Id.
	 * @return Long : UserSK.
	 */
	protected Long getUserSK(String userId) {
		
		Long userSK = null;
		if (correspondenceDataBean.getUserSk() == null) {
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			try {
				userSK = contactMaintenanceDelegate.getWorkUnitID(userId);
			} catch (LOBHierarchyFetchBusinessException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}
			correspondenceDataBean.setUserSk(userSK);
		} else {
			userSK = correspondenceDataBean.getUserSk();
		}
		return userSK;
	}

	/**
	 * @return String
	 */
	protected String getLoggedInUser() {
		

		String loggedInUser = getUserID();
		if(logger.isDebugEnabled())
		{
		logger.debug("User Id is " + loggedInUser);
		}

		return loggedInUser;
	}

	/**
	 * This method is used to get the User ID.
	 * 
	 * @return String : User ID
	 */
	public String getUserID() {
		
		String userId = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest) facesContext
				.getExternalContext().getRequest();
		userId = getLoggedInUserID(portletRequest);
		/*
		 * String userId = "USERID1";
		 * 
		 * if(correspondenceDataBean.getUserID() == null) { HttpServletRequest
		 * renderRequest = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest();
		 * HttpServletResponse renderResponse = null;
		 * 
		 * EnterpriseUserProfile eup = getUserData(renderRequest,
		 * renderResponse);
		 * 
		 * if (eup != null && !isNullOrEmptyField(eup.getUserId())) { userId =
		 * eup.getUserId(); } logger.debug("the useriD in controller is :" +
		 * userId); correspondenceDataBean.setUserID(userId); }else{ userId =
		 * correspondenceDataBean.getUserID(); }
		 */

		return userId;
	}

	/**
	 * This method is used to check if the input field is null or is equal to an
	 * empty string.
	 * 
	 * @param inputField :
	 *            String inputField.
	 * @return boolean : true if input field is null or equal to an empty string
	 *         else false.
	 */
	protected boolean isNullOrEmptyField(String inputField) {
		return (inputField == null || ContactManagementConstants.EMPTY_STRING
				.equalsIgnoreCase(inputField.trim()));
	}

	/**
	 *  
	 */
	public void sortCR() {

	}

	/**
	 *  
	 */
	public String sortAssocCR(ActionEvent event) {

		
		AssociatedCorrespondenceVO associatedCorrespondenceVO = correspondenceDataBean
				.getAssociatedCorrespondenceVO();

		ArrayList listOfAssociatedCRs = (ArrayList) correspondenceDataBean
				.getCorrespondenceRecordVO().getListOfAssociatedCRs();

		if (listOfAssociatedCRs != null) {
			if(logger.isDebugEnabled())
			{

			logger.debug("listOfAssociatedCRs size "
					+ listOfAssociatedCRs.size());
			}
		}

		correspondenceDataBean.setImageRender(event.getComponent().getId());

		final String sortColumn = (String) event.getComponent().getAttributes()
				.get("columnName");

		
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get("sortOrder");
		

		Comparator comparator = new Comparator() {

			public int compare(Object obj1, Object obj2) {
				AssociatedCorrespondenceVO data1 = (AssociatedCorrespondenceVO) obj1;
				AssociatedCorrespondenceVO data2 = (AssociatedCorrespondenceVO) obj2;
				boolean ascending = false;
				if ("asc".equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}

				if ("assocCRN".equals(sortColumn)) {
					
					if (null == data1.getCorrespondenceRecordNumber()) {
						data1
								.setCorrespondenceRecordNumber(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getCorrespondenceRecordNumber()) {
						data2
								.setCorrespondenceRecordNumber(ContactManagementConstants.EMPTY_STRING);
					}
					/*
					 * return ascending ? (data1.getCrn()
					 * .compareTo(data2.getCrn())) : (data2
					 * .getCrn().compareTo(data1 .getCrn()));
					 */
					Integer firstCrn = Integer.valueOf(data1
							.getCorrespondenceRecordNumber());
					Integer secondCrn = Integer.valueOf(data2
							.getCorrespondenceRecordNumber());
					return ascending ? firstCrn.compareTo(secondCrn)
							: secondCrn.compareTo(firstCrn);
				}
				if ("assocOD".equals(sortColumn)) {
					if (null == data1.getOpenDate()) {
						data1
								.setOpenDate(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getOpenDate()) {
						data2
								.setOpenDate(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? (dateConverter(data1.getOpenDate())
							.compareTo(dateConverter(data2.getOpenDate())))
							: (dateConverter(data2.getOpenDate())
									.compareTo(dateConverter(data1
											.getOpenDate())));
				}
				if ("assocStatus".equals(sortColumn)) {
					if (null == data1.getStatus()) {
						data1
								.setStatus(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getStatus()) {
						data2
								.setStatus(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? (data1.getStatus().compareTo(data2
							.getStatus())) : (data2.getStatus().compareTo(data1
							.getStatus()));
				}

				if ("assocCat".equals(sortColumn)) {
					if (null == data1.getCategory()) {
						data1
								.setCategory(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getCategory()) {
						data2
								.setCategory(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? (data1.getCategory().compareTo(data2
							.getCategory())) : (data2.getCategory()
							.compareTo(data1.getCategory()));
				}
				if ("assocSubj".equals(sortColumn)) {
					if (null == data1.getSubject()) {
						data1
								.setSubject(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getSubject()) {
						data2
								.setSubject(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? (data1.getSubject().compareTo(data2
							.getSubject())) : (data2.getSubject()
							.compareTo(data1.getSubject()));
				}
				if ("assocCont".equals(sortColumn)) {
					if (null == data1.getContact()) {
						data1
								.setContact(ContactManagementConstants.EMPTY_STRING);
					}
					if (null == data2.getContact()) {
						data2
								.setContact(ContactManagementConstants.EMPTY_STRING);
					}
					return ascending ? (data1.getContact()
							.compareToIgnoreCase(data2.getContact())) : (data2
							.getContact().compareToIgnoreCase(data1
							.getContact()));
				}

				return 0;
			}

		};

		Collections.sort(listOfAssociatedCRs, comparator);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @return String
	 */
	public String searchForCR() {
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		correspondenceDataBean.setInputHiddenId("logCrAssocSviewID1");
		correspondenceDataBean.setSearchForCR(true);
		correspondenceDataBean.setRenderSearchCRPage(true);
		searchCorrespondenceDataBean.setSearchAssocCR(true);
		return "success";
	}

	/**
	 * @return String
	 */
	public String cancelSearchForCR() {
		
		commonEntityDataBean.setNoteFlag(false);
		correspondenceDataBean.setInputHiddenId("log_div_routing");
		List existingCrsList = correspondenceDataBean
				.getCorrespondenceRecordVO().getExistingCRLists();
		correspondenceDataBean.setSelAllExisRecs(false);
		correspondenceDataBean.setSearchForCR(false);
		correspondenceDataBean.setRenderSearchCRPage(false);
		correspondenceDataBean.setAssociatePlusMinusFlag(true);
		//Code added for defect - ESPRD00902694: Not to display the search results grid
		searchCorrespondenceDataBean.setShowResultsDiv(false);
		searchCorrespondenceDataBean.getSearchResults().clear();

		if (existingCrsList != null && existingCrsList.size() > 0) {
			Iterator existingCrsListItr = existingCrsList.iterator();
			while (existingCrsListItr.hasNext()) {
				AssociatedCorrespondenceVO existingCRVO = (AssociatedCorrespondenceVO) existingCrsListItr
						.next();
				
				existingCRVO.setLinkToCR(Boolean.FALSE);
			}
		}
		List listOfAssociatedCRs = correspondenceDataBean
				.getCorrespondenceRecordVO().getListOfAssociatedCRs();
		if (listOfAssociatedCRs != null && listOfAssociatedCRs.size() > 0) {
			Iterator listOfAssociatedCRsItr = listOfAssociatedCRs.iterator();
			while (listOfAssociatedCRsItr.hasNext()) {
				AssociatedCorrespondenceVO associatedCRVO = (AssociatedCorrespondenceVO) listOfAssociatedCRsItr
						.next();
				if (!associatedCRVO.isExisting()) {
					listOfAssociatedCRsItr.remove();
				}
			}
		}
		return "success";
	}

	/**
	 *  
	 */
	protected void sendAutomaticAlertIfReq() {
		

		if (correspondenceDataBean.isAutomaticAlertForUserReq()
				&& correspondenceDataBean.isAutomaticAlertForCategoryReq()) {
			sendAutomaticAlertForUserAndCategory();
		} else {
			if (correspondenceDataBean.isAutomaticAlertForUserReq()) {
				sendAutomaticAlertForUser();
			}
			if (correspondenceDataBean.isAutomaticAlertForCategoryReq()) {
				sendAutomaticAlertForCategory();
			}
		}
		if(logger.isDebugEnabled())
		{
		logger.debug("Size of alerts list "
				+ getAlertDataBean().getListOfAlertVOs().size());
		}

	}

	/**
	 *  
	 */
	private void sendAutomaticAlertForUser() {
		

		AlertVO alertVO = getAlertToSend();
		if (alertVO != null) {
			alertVO
					.setDescription(ContactManagementConstants.AUTOMATIC_ALERT_DESC_FOR_USER);
			getAlertDataBean().getListOfAlertVOs().add(alertVO);
			if(logger.isDebugEnabled())
			{
			logger.debug("Size of alerts list B "
					+ getAlertDataBean().getListOfAlertVOs().size());
			}
		}

	}

	/**
	 *  
	 */
	private void sendAutomaticAlertForCategory() {
		

		AlertVO alertVO = getAlertToSend();
		if (alertVO != null) {
			alertVO
					.setDescription(ContactManagementConstants.AUTOMATIC_ALERT_DESC_FOR_CATEGORY);
			getAlertDataBean().getListOfAlertVOs().add(alertVO);
			if(logger.isDebugEnabled())
			{
			logger.debug("Size of alerts list C "
					+ getAlertDataBean().getListOfAlertVOs().size());
			}
		}

	}

	/**
	 *  
	 */
	private void sendAutomaticAlertForUserAndCategory() {
		

		AlertVO alertVO = getAlertToSend();
		if (alertVO != null) {
			alertVO
					.setDescription(ContactManagementConstants.AUTOMATIC_ALERT_DESC_FOR_BOTH);
			getAlertDataBean().getListOfAlertVOs().add(alertVO);
			if(logger.isDebugEnabled())
			{
			logger.debug("Size of alerts list U "
					+ getAlertDataBean().getListOfAlertVOs().size());
			}
		}

	}

	/**
	 * @return AlertVO
	 */
	private AlertVO getAlertToSend() {
		

		AlertVO alertVO = null;
		EnterpriseUser supervisor = getSupervisorForDept();

		if (supervisor != null) {
			alertVO = new AlertVO();
			alertVO.setUserId(supervisor.getUserID());
			alertVO
					.setUserWorkUnitSK(supervisor.getUserWorkUnitSK()
							.toString());
			alertVO.setUserWorkUnitDesc(supervisor.getFirstName()
					+ ContactManagementConstants.SPACE_STRING
					+ supervisor.getLastName());

			DateFormat dateFormat = new SimpleDateFormat(
					ContactManagementConstants.DATE_FORMAT);

			alertVO
					.setAlertType(ContactManagementConstants.AUTOMATIC_ALERT_TYPE_APPROVAL);
			alertVO
					.setAlertTypeDesc(ContactManagementConstants.AUTOMATIC_ALERT_TYPE_APPROVAL_DESC);
			alertVO.setStatus(ContactManagementConstants.ALERT_STATUS_OPEN);
			alertVO
					.setStatusDesc(ContactManagementConstants.ALERT_STATUS_OPEN_DESC);

			Calendar calendar = Calendar.getInstance();
			calendar.roll(Calendar.DAY_OF_MONTH, 1);
			alertVO.setDueDate(dateFormat.format(calendar.getTime()));
			if(logger.isDebugEnabled())
			{
			logger
					.debug("Due Date for automatic alert "
							+ alertVO.getDueDate());
			}

			alertVO.setAddedAuditUserID(getUserID());
			alertVO.setAuditUserID(getUserID());
			alertVO.setDbRecord(false);
		}

		return alertVO;
	}

	/**
	 * @return EnterpriseUser
	 */
	protected EnterpriseUser getSupervisorForDept() {
		

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		EnterpriseUser supervisor = null;
		Long departmentSK = Long.valueOf(correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
				.getDepartment());
		try {
			Department department = contactMaintenanceDelegate
					.getSupervisorToDept(departmentSK);

			
			supervisor = department.getSupervisorUserWorkUnit();
			
		} catch (LOBHierarchyFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		return supervisor;
	}

	/**
	 * This method is used to add Note Set to the Correspondence Domain object.
	 * 
	 * @param correspondence :
	 *            Correspondence object
	 */
	protected void addNoteSet(Correspondence correspondence) {
		
		logger.debug("Start addNoteSet");
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
		try {
			NoteSet noteSet = commonEntityValidator.getNoteSetDO();
			if (noteSet != null) {
				
				noteSet.setNoteSourceName("G_CR_TB");
				correspondence.setNoteSet(noteSet);
				if(logger.isInfoEnabled())
				{
				logger.info("setting noteset source name in add noteset---> "
						+ correspondence.getNoteSet().getNoteSourceName());
				}
			}
			else {
				logger.debug("noteSet is Null");
			}
		} catch (CommonEntityUIException commonEntityUIException) {
			if(logger.isErrorEnabled())
			{
			logger.error(commonEntityUIException.getMessage(),
					commonEntityUIException);
			}
		}

	}

	/**
	 * This method is used to populate NoteSet.
	 * 
	 * @param correspondence :
	 *            Correspondence object
	 */
	public void populateNoteSet(Correspondence correspondence) {
		// logger.info("populateNoteSet");
		

		NoteSet noteSet = correspondence.getNoteSet();
		commonEntityDataBean.setNoteSet(noteSet);
		
		if(logger.isInfoEnabled())
		{
		logger.info("correspondence.getNoteSet() "
				+ correspondence.getNoteSet());
		}

		NoteSetVO noteSetVO = new NoteSetVO();

		
		//CommonEntityVO commonEntityVO = new CommonEntityVO(); //Find bug
		// issue
		ContactHelper contactHelper = new ContactHelper();

		if (noteSet != null) {
			

			noteSetVO = contactHelper.convertNoteSetDomainToVO(noteSet);
			if (commonEntityDataBean != null)

			{
				commonEntityDataBean.setNoteList(noteSetVO.getNotesList());
				commonEntityDataBean.setTempNoteList(noteSetVO.getNotesList());
				sortListSequence(commonEntityDataBean.getNoteList());
				commonEntityDataBean.getCommonEntityVO()
						.setNoteSetVO(noteSetVO);
			}
			//Below Code added for the defect ESPRD00795893 to display/render notes record count.
			commonEntityDataBean.setRenderedreccordsnum(true);

		}
		else {
			logger.error("noteSet is null::");
		}

		if (commonEntityDataBean.getCommonEntityVO().getNoteSetVO() == null) 
		{
			commonEntityDataBean.getCommonEntityVO().setNoteSetVO(
					new NoteSetVO());
			//Below Code added for the defect ESPRD00795893 to render notes record count.
			commonEntityDataBean.setRenderedreccordsnum(false);
		}

		if (commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
				.getNotesList() != null) {
			int notesCount = commonEntityDataBean.getCommonEntityVO()
					.getNoteSetVO().getNotesList().size();

		}

		commonEntityDataBean.setNotesSaveMsg(false);
		
		//Added for the defect ESPRD00542028
		commonEntityDataBean.setStartRecord(1);
		commonEntityDataBean.setEndRecord(noteSetVO.getNotesList().size());
		commonEntityDataBean.setCount(noteSetVO.getNotesList().size());

	}

	/**
	 * @return String
	 */
	public String deleteInquiringAbout() {
		
		int index = correspondenceDataBean.getInquiringAboutIndex();
		boolean actvtyExists = false;
		CorrespondenceForVO correspondenceForVO = (CorrespondenceForVO) correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
				.getListOfEnquiryAboutEntities().get(index);
		String entityID = correspondenceForVO.getCmEntityID();
		
		if (entityID != null) {
			CMDelegate cMDelegate = new CMDelegate();
			try {
				actvtyExists = cMDelegate.chkEntyAsctoActivity(Long
						.valueOf(entityID));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if (actvtyExists) {
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.ADDITIONAL_CASE_ASSOCIATED_CANNOT_REMOVE,
							null, null, null);
			correspondenceDataBean.setInputHiddenId("#");
		} else {
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getCorrespondenceDetailsVO()
					.getListOfEnquiryAboutEntities().remove(index);
			cancelInquiringAbout();
			this.setFocusInqHidVal(1);
		}

		return "success";
	}

	/**
	 * @return String
	 */
	public String cancelInquiringAbout() {
	
		correspondenceDataBean.setCorrespondenceForVO(null);
		correspondenceDataBean.setCorrespondenceForProviderVO(null);
		correspondenceDataBean.setCorrespondenceForMemberVO(null);
		//ADDED FOR THE Correspondence ESPRD00436016
		correspondenceDataBean.setCorrespondenceForTradingPartnerVO(null);
		correspondenceDataBean.setRenderInqAbtForVO(false);
		correspondenceDataBean.setRenderInqAbtForMemberVO(false);
		correspondenceDataBean.setRenderInqAbtForProviderVO(false);
		//ADDED FOR THE Correspondence ESPRD00436016
		correspondenceDataBean.setRenderInqAbtForTradingPartnerVO(false);
		this.setFocusInqHidVal(1);
		return "success";
	}
	
	//New method added on 07/03/2012
	
	protected String getNameNewAPI(DeptUserBasicInfo deptUserBasic, String workUnitTypeCode) 
	{
		
		String name = ContactManagementConstants.EMPTY_STRING;
		if (deptUserBasic != null && "U".equalsIgnoreCase(workUnitTypeCode)) 
		{
			if (!isNullOrEmptyField(deptUserBasic.getLastName()))
			{
				name = deptUserBasic.getLastName();
			}
			if (!isNullOrEmptyField(deptUserBasic.getLastName())
					&& !isNullOrEmptyField(deptUserBasic.getFirstName())) 
			{
				name = name + ContactManagementConstants.COMMA_SEPARATOR
						+ ContactManagementConstants.SPACE_STRING;
			}
			if (!isNullOrEmptyField(deptUserBasic.getFirstName()))
			{
				name = name + deptUserBasic.getFirstName();
			}

		}
		if (deptUserBasic != null && "W".equalsIgnoreCase(workUnitTypeCode)) {
		
			name = deptUserBasic.getDeptName();
		}
		if (deptUserBasic != null && "B".equalsIgnoreCase(workUnitTypeCode)) {
			
			name = deptUserBasic.getDeptName();
		}

		return name;
	}
	

	/**
	 * @param workUnit
	 *            The workUnit to set.
	 * @param workUnitTypeCode
	 *            The workUnitTypeCode to set.
	 * @return String
	 */
	protected String getName(WorkUnit workUnit, String workUnitTypeCode) {
		

		String name = ContactManagementConstants.EMPTY_STRING;

		if (workUnit != null && "U".equalsIgnoreCase(workUnitTypeCode)) {
			EnterpriseUser user = workUnit.getEnterpriseUser();
			if (!isNullOrEmptyField(user.getLastName())) {
				name = user.getLastName();
			}
			if (!isNullOrEmptyField(user.getLastName())
					&& !isNullOrEmptyField(user.getFirstName())) {
				name = name + ContactManagementConstants.COMMA_SEPARATOR
						+ ContactManagementConstants.SPACE_STRING;
			}
			if (!isNullOrEmptyField(user.getFirstName())) {
				name = name + user.getFirstName();
			}

		}
		if (workUnit != null && "W".equalsIgnoreCase(workUnitTypeCode)) {
			Department department = workUnit.getDepartment();
			name = department.getName();
		}
		if (workUnit != null && "B".equalsIgnoreCase(workUnitTypeCode)) {
			Department department = workUnit.getDepartment();
			name = department.getName();
		}

		return name;
	}

	/**
	 * show Existing Correspondence Records
	 */
	public void showExistingCorrespondenceRecords(Map statusMap) {
		

		CorrespondenceForVO correspondenceForVO = null;

		if (correspondenceDataBean.isRenderCrspdForVO()) {
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceForVO();
		} else if (correspondenceDataBean.isRenderCrspdMemberForVO()) {
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceForMemberVO();
		} else if (correspondenceDataBean.isRenderCrspdProviderForVO()) {
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO()
					.getCorrespondenceForProviderVO();
		} else if (correspondenceDataBean.isRenderCrspdUnEnrolledMemberForVO()) {
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceForVO();
		} else if (correspondenceDataBean
				.isRenderCrspdUnEnrolledProviderForVO()) {
			
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceForVO();
			
		} else if (correspondenceDataBean.isRenderCrspdTPLForVO()) {
			
			correspondenceForVO = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceForVO();
			
		}
		if(logger.isDebugEnabled())
		{
		logger.debug("Common Entity SK " + correspondenceForVO.getCmEntityID());
		logger.debug("Entity Type " + correspondenceForVO.getEntityTypeCode());
		}

		CorrespondenceDOConvertor convertor = new CorrespondenceDOConvertor();
		convertor.getAssociatedCorrespondence(Long.valueOf(correspondenceForVO
				.getCmEntityID()), correspondenceForVO.getEntityTypeCode(),
				Integer.valueOf("90"));
		CorrespondenceRecordVO correspondenceRecordVO = getCorrespondenceDataBean()
				.getCorrespondenceRecordVO();

		if (correspondenceRecordVO != null) {
			List listOfAsscCRs = correspondenceRecordVO
					.getListOfAssociatedCRs();
			List listOfExistingCRs = getCorrespondenceDataBean()
					.getCorrespondenceRecordVO().getExistingCRLists();

			if (listOfAsscCRs != null && listOfExistingCRs != null) {
				if(logger.isDebugEnabled())
				{
				logger.debug("list size of exisisting list  *******  "
						+ listOfExistingCRs.size());
				logger.debug("list size of ass list  *******  "
						+ listOfAsscCRs.size());
				}

				for (Iterator iter = listOfAsscCRs.iterator(); iter.hasNext();) {
					AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) iter
							.next();
					for (Iterator iterator = listOfExistingCRs.iterator(); iterator
							.hasNext();) {
						AssociatedCorrespondenceVO existingCR = (AssociatedCorrespondenceVO) iterator
								.next();
						if (existingCR
								.getCorrespondenceRecordNumber()
								.equalsIgnoreCase(
										asscCRVO
												.getCorrespondenceRecordNumber())
								|| existingCR
										.getCorrespondenceRecordNumber()
										.equalsIgnoreCase(
												correspondenceRecordVO
														.getCorrespondenceRecordNumber())) {
							iterator.remove();
							

						} else if (statusMap != null && statusMap.size() > 0) {
							existingCR.setSubject((String) statusMap
									.get(existingCR.getSubject()));
						}
					}

					correspondenceRecordVO
							.setExistingCRLists(listOfExistingCRs);
					
				}
			}
		}

	}

	/**
	 * @return String
	 */
	public String addToOrRemoveFromExistingCRs() {
		

		int index = getSearchCorrespondenceDataBean().getStartIndexForAscSrchRslts();
		getSearchCorrespondenceDataBean().setSearchSelectAll(false);
		getSearchCorrespondenceDataBean().setSearchDeSelectAll(false);
		correspondenceDataBean.setStartIndexForAsc(0);
		CorrespondenceSearchResultsVO resultsVO = (CorrespondenceSearchResultsVO) getSearchCorrespondenceDataBean()
				.getSearchResults().get(index);

		if (recordNotAssociatedEarlier(resultsVO)) {
			
			if (resultsVO.isCheckBox()) {
				if(logger.isDebugEnabled())
				{
				logger.debug("CheckBox " + resultsVO.isCheckBox() + " for CR "
						+ resultsVO.getCorrespondenceNumber());
				}
				//commented for unused variables
				//String crNum = resultsVO.getCorrespondenceNumber().toString();
				AssociatedCorrespondenceVO associatedCorrespondenceVO = getAssociatedCRVO(resultsVO);
				resultsVO.setCheckBox(true);

				correspondenceDataBean.getCorrespondenceRecordVO()
						.getListOfAssociatedCRs().add(
								associatedCorrespondenceVO);
				if(logger.isDebugEnabled())
				{
				logger.debug("CorrespondenceRecordNumber added--> "
						+ associatedCorrespondenceVO
								.getCorrespondenceRecordNumber());
				}
			} else {
				List associateCRList = correspondenceDataBean
						.getCorrespondenceRecordVO().getListOfAssociatedCRs();
				String crn = resultsVO.getCorrespondenceNumber().toString();
				if (associateCRList != null) {
					for (Iterator iter = associateCRList.iterator(); iter
							.hasNext();) {
						AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) iter
								.next();
						if (asscCRVO.getCorrespondenceRecordNumber()
								.equalsIgnoreCase(crn)) {
							associateCRList.remove(asscCRVO);
							break;
						}
					}
				}
			}
			//			to get the size of the list
			if (correspondenceDataBean.getCorrespondenceRecordVO()
					.getExistingCRLists() != null) {
				correspondenceDataBean
						.setExistingCRListsSize(correspondenceDataBean
								.getCorrespondenceRecordVO()
								.getExistingCRLists().size());
			}
			if(logger.isDebugEnabled())
			{
			logger.debug("Existing CR List size----"
					+ correspondenceDataBean.getExistingCRListsSize());
			}
			sortExistingCROnDate(correspondenceDataBean
					.getCorrespondenceRecordVO().getExistingCRLists());

		}

		return "success";
	}

	/**
	 * @return String
	 */
	public String addToOrRemoveFromAssociatedCRs() {
		

		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
		if (indexCode == null) {
			indexCode = "0";
		}
		int index = Integer.parseInt(indexCode);
		correspondenceDataBean.setStartIndexForexistAsc((index/10)*10);

		correspondenceDataBean.setSelAllExisRecs(false);
		correspondenceDataBean.setDeSelAllExisRecs(false);

		AssociatedCorrespondenceVO resultsVO = (AssociatedCorrespondenceVO) correspondenceDataBean
				.getCorrespondenceRecordVO().getExistingCRLists().get(index);
		if(logger.isDebugEnabled())
		{
		logger.debug("LinkToCR---> " + resultsVO.getLinkToCR());
		}
		if (resultsVO.getLinkToCR().equals(Boolean.TRUE)) {
			if (recordNotAssociatedEarlier(resultsVO)) {
				if(logger.isDebugEnabled())
				{
				logger.debug("Adding Existing to Assoc CR---> "
						+ resultsVO.getLinkToCR());
				}
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getListOfAssociatedCRs().add(resultsVO);
			}
		} else {
			if(logger.isDebugEnabled())
			{
			logger.debug("Removing Assoc CR---> " + resultsVO.getLinkToCR());
			}
			List associateCRList = correspondenceDataBean
					.getCorrespondenceRecordVO().getListOfAssociatedCRs();
			String crn = resultsVO.getCorrespondenceRecordNumber().toString();
			if (associateCRList != null) {
				for (Iterator iter = associateCRList.iterator(); iter.hasNext();) {
					AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) iter
							.next();
					if (asscCRVO.getCorrespondenceRecordNumber()
							.equalsIgnoreCase(crn)) {
						associateCRList.remove(asscCRVO);
						break;
					}
				}
			}
		}

		return "success";
	}

	public String addOrRemoveSearchCRsFromAssociatedCRs() {
		

		getSearchCorrespondenceDataBean().setSearchSelectAll(false);
		getSearchCorrespondenceDataBean().setSearchDeSelectAll(false);
		correspondenceDataBean.setStartIndexForAsc(0);
		List searchResultsList = getSearchCorrespondenceDataBean()
				.getSearchResults();

		if (searchResultsList != null) {
			for (Iterator iter = searchResultsList.iterator(); iter.hasNext();) {
				CorrespondenceSearchResultsVO searchResultsVO = (CorrespondenceSearchResultsVO) iter
						.next();

				if (searchResultsVO.isCheckBox()) {
					if (recordNotAssociatedEarlier(searchResultsVO)) {
						AssociatedCorrespondenceVO associatedCorrespondenceVO = getAssociatedCRVO(searchResultsVO);
						correspondenceDataBean.getCorrespondenceRecordVO()
								.getListOfAssociatedCRs().add(
										associatedCorrespondenceVO);

						
					}
				} else {
					List tempAssociateCRList = correspondenceDataBean
							.getCorrespondenceRecordVO()
							.getListOfAssociatedCRs();
					
					if (tempAssociateCRList != null) {
						for (Iterator iterator = tempAssociateCRList.iterator(); iterator
								.hasNext();) {
							AssociatedCorrespondenceVO tempassocCRVO = (AssociatedCorrespondenceVO) iterator
									.next();

							if (!tempassocCRVO.isDbAssocRecord()) {
								String crn = tempassocCRVO
										.getCorrespondenceRecordNumber()
										.toString();
								
								if (searchResultsVO.getCorrespondenceNumber()
										.toString().equalsIgnoreCase(crn)) {
									
									if (!searchResultsVO.isCheckBox()
											&& tempassocCRVO.getLinkToCR()
													.equals(Boolean.FALSE)) {
										tempAssociateCRList
												.remove(tempassocCRVO);
										
										break;
									}
								}
							}
						}
						//correspondenceDataBean.getCorrespondenceRecordVO().setListOfAssociatedCRs(tempAssociateCRList);
						
					}
				}
			}
			//			to get the size of the list
			if (correspondenceDataBean.getCorrespondenceRecordVO()
					.getExistingCRLists() != null) {
				correspondenceDataBean
						.setExistingCRListsSize(correspondenceDataBean
								.getCorrespondenceRecordVO()
								.getExistingCRLists().size());
			}
			if(logger.isDebugEnabled())
			{
			logger.debug("Existing CR List size----"
					+ correspondenceDataBean.getExistingCRListsSize());
			}
		}

		return "success";
	}

	/**
	 * @return String
	 */
	public String addOrRemoveExistingCRsFromAssociatedCRs() {
		

		correspondenceDataBean.setSelAllExisRecs(false);
		correspondenceDataBean.setDeSelAllExisRecs(false);

		List existingCRList = correspondenceDataBean
				.getCorrespondenceRecordVO().getExistingCRLists();

		if (existingCRList != null) {
			for (Iterator iter = existingCRList.iterator(); iter.hasNext();) {
				AssociatedCorrespondenceVO existingCRVO = (AssociatedCorrespondenceVO) iter
						.next();
				if (existingCRVO.getLinkToCR().equals(Boolean.TRUE)) {
					if (recordNotAssociatedEarlier(existingCRVO)) {
						
						correspondenceDataBean.getCorrespondenceRecordVO()
								.getListOfAssociatedCRs().add(existingCRVO);
					}
				} else {

					
					for (Iterator iterator = existingCRList.iterator(); iterator
							.hasNext();) {
						AssociatedCorrespondenceVO existCRVO = (AssociatedCorrespondenceVO) iterator
								.next();

						if (existCRVO.getLinkToCR().equals(Boolean.FALSE)) {
							List assocCRList = correspondenceDataBean
									.getCorrespondenceRecordVO()
									.getListOfAssociatedCRs();

							String crn = existCRVO
									.getCorrespondenceRecordNumber().toString();

							for (Iterator itr = assocCRList.iterator(); itr
									.hasNext();) {
								AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) itr
										.next();
								if (asscCRVO.getCorrespondenceRecordNumber()
										.equalsIgnoreCase(crn)) {
									assocCRList.remove(asscCRVO);
									break;
								}
							}
						}
					}
				}
			}
		}

		return "success";
	}

	/**
	 * @param resultsVO
	 *            The resultsVO to set.
	 * @return boolean
	 */
	private boolean recordNotAssociatedEarlier(
			CorrespondenceSearchResultsVO resultsVO) {
		

		boolean notAssociated = true;

		String crn = resultsVO.getCorrespondenceNumber().toString();

		List listOfAssociatedCRs = correspondenceDataBean
				.getCorrespondenceRecordVO().getListOfAssociatedCRs();

		if (listOfAssociatedCRs != null) {
			for (Iterator iter = listOfAssociatedCRs.iterator(); iter.hasNext();) {
				AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) iter
						.next();
				if (asscCRVO.getCorrespondenceRecordNumber().equalsIgnoreCase(
						crn)) {
					notAssociated = false;
					break;
				}
			}
		}

		return notAssociated;
	}

	/**
	 * @param resultsVO
	 *            The resultsVO to set.
	 * @return boolean
	 */
	private boolean recordNotAssociatedEarlier(
			AssociatedCorrespondenceVO resultsVO) {
		

		boolean notAssociated = true;

		String crn = resultsVO.getCorrespondenceRecordNumber().toString();

		List listOfAssociatedCRs = correspondenceDataBean
				.getCorrespondenceRecordVO().getListOfAssociatedCRs();

		if (listOfAssociatedCRs != null) {
			for (Iterator iter = listOfAssociatedCRs.iterator(); iter.hasNext();) {
				AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) iter
						.next();
				if (asscCRVO.getCorrespondenceRecordNumber().equalsIgnoreCase(
						crn)) {
					notAssociated = false;
					break;
				}
			}
		}

		return notAssociated;
	}

	/**
	 * @param resultsVO
	 *            The resultsVO to set.
	 * @return AssociatedCorrespondenceVO
	 */
	private AssociatedCorrespondenceVO getAssociatedCRVO(
			CorrespondenceSearchResultsVO resultsVO) {

		AssociatedCorrespondenceVO associatedCorrespondenceVO = new AssociatedCorrespondenceVO();

		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);

		associatedCorrespondenceVO.setCorrespondenceRecordNumber(resultsVO
				.getCorrespondenceNumber().toString());

		if (resultsVO.getCreatedOn() != null) {
			associatedCorrespondenceVO.setOpenDate(dateFormat.format(resultsVO
					.getCreatedOn()));
		}
		associatedCorrespondenceVO.setSubject(resultsVO.getSubjectCode());
		Map subjectMap = searchCorrespondenceDataBean.getSubjectMap();
		if (subjectMap != null && subjectMap.size() > 0
				&& subjectMap.containsKey(resultsVO.getSubjectCode())) {
			associatedCorrespondenceVO.setSubject((String) subjectMap
					.get(resultsVO.getSubjectCode()));
		}
		associatedCorrespondenceVO.setCategory(resultsVO
				.getCategoryDescription());
		associatedCorrespondenceVO.setStatus(resultsVO.getStatus());

		String firstName = (resultsVO.getRepFirstName() != null) ? resultsVO
				.getRepFirstName() : ContactManagementConstants.EMPTY_STRING;

		String lastName = (resultsVO.getRepLastName() != null) ? resultsVO
				.getRepLastName() : ContactManagementConstants.EMPTY_STRING;

		associatedCorrespondenceVO.setContact(firstName
				+ ContactManagementConstants.SPACE_STRING + lastName);
		associatedCorrespondenceVO.setLinkToCR(Boolean.FALSE);

		return associatedCorrespondenceVO;
	}

	/**
	 *  
	 */
	protected void copyCommonEntitiesRefs() {
		

		correspondenceDataBean.setCrNotesList(getCommonEntityDataBean()
				.getCommonEntityVO().getNoteSetVO().getNotesList());
		if(logger.isDebugEnabled())
		{
		logger.debug("here "
				+ correspondenceDataBean.getCrNotesList().hashCode());
		logger.debug("here "
				+ getCommonEntityDataBean().getCommonEntityVO().getNoteSetVO()
						.getNotesList().hashCode());
		}

		correspondenceDataBean.setCrContactList(getCommonEntityDataBean()
				.getCommonEntityVO().getContactVOList());

		/** To show Current Note */
		if (!correspondenceDataBean.getCrNotesList().isEmpty()) {
			getCommonEntityDataBean().getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(
							((CommonNotesVO) correspondenceDataBean
									.getCrNotesList().get(0)).getNoteText());
		}

	}

	/**
	 *  
	 */
	protected void restoreCommonEntitiesRefs() {
		
		if(logger.isDebugEnabled())
		{
		logger.debug("hereagain "
				+ correspondenceDataBean.getCrNotesList().hashCode());
		logger.debug("hereagain "
				+ getCommonEntityDataBean().getCommonEntityVO().getNoteSetVO()
						.getNotesList().hashCode());
		}

		if (correspondenceDataBean.getCrNotesList().isEmpty()) {
			
		} else {
			
		}

		getCommonEntityDataBean().getCommonEntityVO().getNoteSetVO()
				.setNotesList(
						(ArrayList) (correspondenceDataBean.getCrNotesList()));

		getCommonEntityDataBean().getCommonEntityVO().setContactVOList(
				(ArrayList) correspondenceDataBean.getCrContactList());

		/** To show Current Note */
		if (!correspondenceDataBean.getCrNotesList().isEmpty()) {
			getCommonEntityDataBean().getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(
							((CommonNotesVO) correspondenceDataBean
									.getCrNotesList().get(0)).getNoteText());
		}

	}

	/**
	 * This method is to create an instance of Data Bean.
	 * 
	 * @return Returns CorrespondenceDataBean
	 */
	public CorrespondenceDataBean getCorrespondenceDataBean() {

		FacesContext fc = FacesContext.getCurrentInstance();
		correspondenceDataBean = (CorrespondenceDataBean) fc.getApplication()
				.getVariableResolver().resolveVariable(fc,
						ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		if (correspondenceDataBean == null) {
			CorrespondenceDataBean correspondenceDataBean = (CorrespondenceDataBean) fc
					.getApplication().createValueBinding(
							"#{" + ContactManagementConstants.CORRESPONDENCE_BEAN_NAME+ "}")
					.getValue(fc);
		}

		return correspondenceDataBean;
	}

	/**
	 * This method will return the reference of the data bean.
	 * 
	 * @return SystemParameterDataBean
	 */
	protected SearchCorrespondenceDataBean getSearchCorrespondenceDataBean() {

		SearchCorrespondenceDataBean searchCorrespondenceDataBean;

		FacesContext fc = FacesContext.getCurrentInstance();
		searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) fc
				.getApplication().getVariableResolver().resolveVariable(fc,
						ContactManagementConstants.SEARCHCORRESPONDENCE_BEAN_NAME);
		if (searchCorrespondenceDataBean == null) {
			searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) fc
					.getApplication()
					.createValueBinding(
							"#{" + ContactManagementConstants.SEARCHCORRESPONDENCE_BEAN_NAME+ "}")
					.getValue(fc);
		}

		return searchCorrespondenceDataBean;
	}
	
	 //code added for CR:ESPRD00798895
	/**
	 * To get the SearchCorrespondenceControllerBean.
	 * 
	 * @return SearchCorrespondenceControllerBean.
	 */
	protected SearchCorrespondenceControllerBean getSearchCorrespondenceControllerBean() {
		SearchCorrespondenceControllerBean searchCorrespondenceControllerBean;
		FacesContext fc = FacesContext.getCurrentInstance();
		return ((SearchCorrespondenceControllerBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ "cs_searchCorrespondenceControllerBean"
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));
		
	}

	/**
	 * To get the commonNotesControllerBean.
	 * 
	 * @return CommonNotesControllerBean.
	 */
	protected CommonNotesControllerBean getCommonNotesControllerBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if(logger.isDebugEnabled())
		{
		logger.debug("Class is "
				+ fc.getApplication().createValueBinding(
						"#{" + "commonNotesControllerBean" + "}").getValue(fc)
						.getClass());
		}

		CommonNotesControllerBean commonNotesControllerBean = (CommonNotesControllerBean) fc
				.getApplication().createValueBinding(
						"#{" + "commonNotesControllerBean" + "}").getValue(fc);

		return commonNotesControllerBean;
	}

	/**
	 * This method is used to get the Routing Controller Bean.
	 * 
	 * @return RoutingControllerBean : RoutingControllerBean object.
	 */
	public RoutingControllerBean getRoutingControllerBean() {
		

		FacesContext fc = FacesContext.getCurrentInstance();

		return ((RoutingControllerBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ "RoutingControllerBean"
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));
	}

	/**
	 * This method is used to get the Alert Controller Bean.
	 * 
	 * @return AlertControllerBean : AlertControllerBean object.
	 */
	public AlertControllerBean getAlertControllerBean() {
		

		FacesContext fc = FacesContext.getCurrentInstance();

		return ((AlertControllerBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ "AlertControllerBean"
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));

	}

	/**
	 * To get the AttachmentControllerBean.
	 * 
	 * @return AttachmentControllerBean.
	 */
	protected AttachmentControllerBean getAttachmentControllerBean() {
		

		FacesContext fc = FacesContext.getCurrentInstance();
		if(logger.isDebugEnabled())
		{
		logger.debug("Class is "
				+ fc.getApplication().createValueBinding(
						"#{" + "AttachmentControllerBean" + "}").getValue(fc)
						.getClass());
		}

		AttachmentControllerBean attachmentControllerBean = (AttachmentControllerBean) fc
				.getApplication().createValueBinding(
						"#{" + "AttachmentControllerBean" + "}").getValue(fc);

		return attachmentControllerBean;
	}

	/**
	 * This method is used to get the Alert Data Bean.
	 * 
	 * @return AlertDataBean : AlertDataBean object.
	 */
	public AlertDataBean getAlertDataBean() {
		

		FacesContext fc = FacesContext.getCurrentInstance();

		return ((AlertDataBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ ContactManagementConstants.ALERT_DATA_BEAN_NAME
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));
	}

	/**
	 * This method is used to get the RoutingDataBean.
	 * 
	 * @return RoutingDataBean : RoutingDataBean object.
	 */
	public RoutingDataBean getRoutingDataBean() {
		
		FacesContext fc = FacesContext.getCurrentInstance();

		return ((RoutingDataBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ ContactManagementConstants.ROUTING_DATA_BEAN_NAME
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));
	}

	/**
	 * To get the commonentityDatatBean.
	 * 
	 * @return CommonEntityDataBean.
	 */
	public static CommonEntityDataBean getCommonEntityDataBean() {

		// getCommonEntityDataBean");

		FacesContext fc = FacesContext.getCurrentInstance();
		CommonEntityDataBean commonEntityDataBean = (CommonEntityDataBean) fc
				.getApplication().createValueBinding(
						"#{" + ProgramConstants.COMMONENTITYDATABEAN + "}")
				.getValue(fc);
		return commonEntityDataBean;
	}

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
	protected void setErrorMessage(String errorName, Object[] arguments,
			String messageBundle, String componentId) {
		

		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		Locale locale = root.getLocale();
		String clientId = null;

		facesContext.getApplication().setMessageBundle(messageBundle);
		String errorMsg = MessageUtil.format(messageBundle, errorName,
				arguments, locale);
		FacesMessage fc = new FacesMessage();
		fc.setDetail(errorMsg);

		if (componentId != null) {
			UIComponent uiComponent = findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);
		}

		facesContext.addMessage(clientId, fc);
	}

	/**
	 * This operation is used to find the component by passing id.
	 * 
	 * @param base :
	 *            View root component of the jsp.
	 * @param id :
	 *            Id of the component from jsp.
	 * @return UIComponent object.
	 */
	public UIComponent findComponent(UIComponent base, String id) {
		

		// Is the "base" component itself the match we are looking for?
		if (id.equals(base.getId())) {
			return base;
		}

		// Search through our facets and children
		UIComponent component = null;
		UIComponent result = null;
		Iterator cmpIterator = base.getFacetsAndChildren();

		while (cmpIterator.hasNext() && (result == null)) {
			component = (UIComponent) cmpIterator.next();
			if (id.equals(component.getId())) {
				result = component;
				break;
			}
			result = findComponent(component, id);
			if (result != null) {
				break;
			}
		}

		return result;
	}

	/**
	 * This operation is used to find the component in root by passing id.
	 * 
	 * @param id :
	 *            String object.
	 * @return UIComponent : UIComponent object.
	 */
	public UIComponent findComponentInRoot(String id) {
		

		UIComponent component = null;
		FacesContext context = FacesContext.getCurrentInstance();

		if (context != null) {
			UIComponent root = context.getViewRoot();
			component = findComponent(root, id);
		}

		return component;
	}

	/**
	 * Navigates to Letter response page.
	 * 
	 * @return String
	 */
	public String newResponse() {
		

		/** Save the Correspondence b4 going to Leter n response Page */
		saveCorrespondence();

		String correspondenceSK = correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceRecordNumber();

		if (!isNullOrEmptyField(correspondenceSK))

		{
			String entId = "";
			String entName = "";
			if(logger.isDebugEnabled())
			{
			logger.debug("Inside crrrrr bean ==========corr sk "
					+ correspondenceSK);
			}
			/** Get Entity Id and name if Entity Type is member */
			if (correspondenceDataBean.isRenderCrspdMemberForVO()) {
				entId = correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceForMemberVO().getCmEntityID();

				entName = correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceForMemberVO().getName();
			}

			/** Get Entity Id and name if Entity Type is Prov */
			if (correspondenceDataBean.isRenderCrspdProviderForVO()) {
				entId = correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceForProviderVO().getCmEntityID();
				entName = correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceForMemberVO().getName();
			}

			/** Get Entity Id and name if Entity Type is Prov */
			if (correspondenceDataBean.isRenderCrspdForVO()) {
				entId = correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceForVO().getCmEntityID();
				entName = correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceForMemberVO().getName();
			}

			String correspondenceDetails = correspondenceSK + ":" + entId + "-"
					+ entName;
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().getRequestMap().put("LetterGeneration",
					correspondenceDetails);

		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 *  
	 */
	protected void clearCorrespondenceDataBeanState() {
		
		correspondenceDataBean.setSelAllExisRecs(false);
		if (correspondenceDataBean.getAssocCRList() != null) {
			correspondenceDataBean.getAssocCRList().clear();
		}
		correspondenceDataBean.setAssociatedCorrespondenceVO(null);
		correspondenceDataBean.setAutomaticAlertForCategoryReq(false);
		correspondenceDataBean.setAutomaticAlertForUserReq(false);

		if (correspondenceDataBean.getCategoryList() != null) {
			correspondenceDataBean.getCategoryList().clear();
		}
		correspondenceDataBean.getCategorySubjectValues().clear();
		correspondenceDataBean.setCorrespondenceForMemberVO(null);
		correspondenceDataBean.setCorrespondenceForProviderVO(null);
		//ADDED FOR THE Correspondence ESPRD00436016
		correspondenceDataBean.setCorrespondenceForTradingPartnerVO(null);
		correspondenceDataBean.setCorrespondenceForVO(null);
		correspondenceDataBean.setCrAssignedTo(true);
		correspondenceDataBean.setCrClosed(false);
		correspondenceDataBean.setCrSaved(false);
		if (correspondenceDataBean.getCrContactList() != null) {
			correspondenceDataBean.getCrContactList().clear();
		}
		if (correspondenceDataBean.getContactList() != null) {
			correspondenceDataBean.getContactList().clear();
		}
		if (correspondenceDataBean.getCrContactTypeList() != null) {
			correspondenceDataBean.getCrContactTypeList().clear();
		}
		if (correspondenceDataBean.getCrNotesList() != null) {
			correspondenceDataBean.getCrNotesList().clear();
		}
		if (correspondenceDataBean.getDepartmentsList() != null) {
			correspondenceDataBean.getDepartmentsList().clear();
		}
		correspondenceDataBean.setExistCR(false);
		if (correspondenceDataBean.getExistCRList() != null) {
			correspondenceDataBean.getExistCRList().clear();
		}
		correspondenceDataBean.setLinkToCR(false);
		if (correspondenceDataBean.getListOfAlternateIds() != null) {
			correspondenceDataBean.getListOfAlternateIds().clear();
		}
		if (correspondenceDataBean.getListOfCategoryDOs() != null) {
			correspondenceDataBean.getListOfCategoryDOs().clear();
		}
		if (correspondenceDataBean.getListOfContacts() != null) {
			correspondenceDataBean.getListOfContacts().clear();
		}
		if (correspondenceDataBean.getLobVVList() != null) {
			correspondenceDataBean.getLobVVList().clear();
		}
		if (correspondenceDataBean.getMapOfDeptAndLobHier() != null) {
			correspondenceDataBean.getMapOfDeptAndLobHier().clear();
		}
		correspondenceDataBean.setNoDataAssocCR(true);
		correspondenceDataBean.setNoDataExistCR(true);
		if (correspondenceDataBean.getPriorityVVList() != null) {
			correspondenceDataBean.getPriorityVVList().clear();
		}
		correspondenceDataBean.setRenderCrspdForVO(false);
		correspondenceDataBean.setRenderCrspdMemberForVO(false);
		correspondenceDataBean.setRenderCrspdProviderForVO(false);
		correspondenceDataBean.setRenderInqAbtForMemberVO(false);
		correspondenceDataBean.setRenderInqAbtForProviderVO(false);
		correspondenceDataBean.setRenderInqAbtForVO(false);
		if (correspondenceDataBean.getResolnVVList() != null) {
			correspondenceDataBean.getResolnVVList().clear();
		}
		correspondenceDataBean.setSearchForCR(false);
		correspondenceDataBean.setShowResultsDiv(false);
		if (correspondenceDataBean.getSourceVVList() != null) {
			correspondenceDataBean.getSourceVVList().clear();
		}
		if (correspondenceDataBean.getStatValidValues() != null) {
			correspondenceDataBean.getStatValidValues().clear();
		}
		if (correspondenceDataBean.getSubjectValidValues() != null) {
			correspondenceDataBean.getSubjectValidValues().clear();
		}
		if (correspondenceDataBean.getSelectedList() != null) {
			correspondenceDataBean.getSelectedList().clear();
		}

		correspondenceDataBean.setUnderReview(false);
		correspondenceDataBean.setUpdateMode(false);
		correspondenceDataBean.setAlertSaved(false);
		correspondenceDataBean.setRoutingSaved(false);
		correspondenceDataBean.setMessageFlag(true);
		correspondenceDataBean
				.setCorrespondenceRecordVO(new CorrespondenceRecordVO());
		correspondenceDataBean.setCustomFieldValueVO(new CustomFieldValueVO());
		correspondenceDataBean.setRenderClientServices(false);
		correspondenceDataBean.setRenderCustomFlds(false);
		correspondenceDataBean.setRenderDaysOpenFlag(false);
		correspondenceDataBean.setRenderDaystoCloseFlag(false);
		correspondenceDataBean.setInputHiddenId("#");
		correspondenceDataBean.setBusUnit(null);
		/**
		 * Code is added below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
		correspondenceDataBean.setLogCRErrMsgFlag(false);
		//CorrespondenceRecordVO correspondenceRecordVO =
		// correspondenceDataBean.getCorrespondenceRecordVO();
		//clearCorrespondenceRecordVOState(correspondenceRecordVO);
	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 */
	private void clearCorrespondenceRecordVOState(
			CorrespondenceRecordVO correspondenceRecordVO) {
		

		/*
		 * correspondenceRecordVO.setAddedAuditTimeStamp()
		 * correspondenceRecordVO.setAddedAuditUserID()
		 * correspondenceRecordVO.setAssociatedCorrespondenceVO()
		 * correspondenceRecordVO.setAuditTimeStamp()
		 * correspondenceRecordVO.setAuditUserID()
		 * correspondenceRecordVO.setCallScriptDesc()
		 * correspondenceRecordVO.setCallScriptSK()
		 * correspondenceRecordVO.setCallScriptText()
		 * correspondenceRecordVO.setCorrespondenceDetailsVO()
		 * correspondenceRecordVO.setCorrespondenceForMemberVO()
		 * correspondenceRecordVO.setCorrespondenceForProviderVO()
		 * correspondenceRecordVO.setCorrespondenceForVO()
		 * correspondenceRecordVO.setCorrespondenceRecordNumber()
		 * correspondenceRecordVO.setDaysToClose()
		 * correspondenceRecordVO.setDbRecord()
		 * correspondenceRecordVO.setEdmsDocTypeCode()
		 * correspondenceRecordVO.setExistingCRLists()
		 * correspondenceRecordVO.setListOfAssociatedCRs()
		 * correspondenceRecordVO.setListOfAttachments()
		 * correspondenceRecordVO.setListOfCmRoutingVOs()
		 * correspondenceRecordVO.setListOfCrAlerts()
		 * correspondenceRecordVO.setListOfLettersAndResponses()
		 * correspondenceRecordVO.setReceivedDate()
		 * correspondenceRecordVO.setResponseAllClosedIndicator()
		 * correspondenceRecordVO.setResponseExistIndicator()
		 * correspondenceRecordVO.setResponseHasFileIndicator()
		 * correspondenceRecordVO.setSensitiveDataIndicator()
		 * correspondenceRecordVO.setSprvsrReviewedWorkUnit()
		 * correspondenceRecordVO.setVersionNo(0);
		 */

	}

	/**
	 *  
	 */
	protected void clearCorrespondenceState() {
		

		clearCorrespondenceDataBeanState();
		commonEntityDataBean.setNoteSet(null);
		commonEntityDataBean.setNoteList(null);
		
		//Code added for defect - ESPRD00920192 for row highlight to display the highlighted row in notes section
		commonEntityDataBean.setItemSelectedRowIndeNotes(-1);

		
		 //Below line of code is added for Defect ESPRD00843786 to fix missing notes issue. 
        commonEntityDataBean.setSaveNotesChk(false);
		
		commonEntityDataBean.getCommonNoteSearchVO().setBeginDate(null);
	    commonEntityDataBean.getCommonNoteSearchVO().setEndDate(null);
	    commonEntityDataBean.getCommonNoteSearchVO().setStrBeginDate(null);
	    commonEntityDataBean.getCommonNoteSearchVO().setStrEndDate(null);
	    commonEntityDataBean.getCommonNoteSearchVO().setUserId("");
	    commonEntityDataBean.getCommonNoteSearchVO().setUsageTypeCode("");

		CommonNotesVO commonNotesVO = null;
		commonNotesVO = new CommonNotesVO();
		commonEntityDataBean.getCommonEntityVO()
				.setCommonNotesVO(commonNotesVO);
		commonEntityDataBean.setSmallSaveFlag("false");
		
		//Below code added for defect ESPRD00893433 to clear the NoteSetVO value of older Correspondence Record 
		NoteSetVO noteSetVO=null;
		noteSetVO= new NoteSetVO();
		if(null != commonEntityDataBean.getCommonEntityVO() )
		{
		commonEntityDataBean.getCommonEntityVO().setNoteSetVO(noteSetVO);
		}

		getRoutingControllerBean().clearRoutingDataBeanState();
		getAlertControllerBean().clearAlertDataBeanState();
		getAttachmentControllerBean().clearAttachmentDataBeanState();
		getLettersAndResponsesControllerBean()
				.clearLettersAndResponsesDataBeanState();
		 //Below Code added for the defect ESPRD00795893 do display record count. 
		commonEntityDataBean.setRenderedreccordsnum(false);
		//Below code added for the defect ESPRD00802065 not to display Filter pop up message
		commonEntityDataBean.setCommonNoteSaved(false);
	}

	/**
	 * To get the description based on code provided.
	 * 
	 * @param code :
	 *            Holds the code value
	 * @param vvList :
	 *            Holds the List of valid values
	 * @return String : Description of the code provided.
	 */
	private String getDescriptionFromVV(String code, List vvList) {
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceControllerBean.class);

		

		String desc = ProgramConstants.EMPTY_STRING;
		String valueStr = ProgramConstants.EMPTY_STRING;
		if (vvList != null && vvList.size() > 0) {
			for (Iterator iter = vvList.iterator(); iter.hasNext();) {
				SelectItem selectItem = (SelectItem) iter.next();
				valueStr = (String) selectItem.getValue();
				if (valueStr != null && valueStr.equalsIgnoreCase(code)) {
					desc = selectItem.getLabel();
					break;
				}
			}
		}

		return desc;
	}

	/**
	 * This operation will show audit log.
	 * 
	 * @return String
	 */
	public String showAuditLog() {
		

		correspondenceDataBean.setAuditLogRendered(true);
		correspondenceDataBean.setAuditParentHistoryRender(false);
		EnterpriseSearchResultsVO resultVO = retrieveData(0, 0);

		setRecordRange(resultVO);
		correspondenceDataBean.setAuditParentHistoryList(resultVO
				.getSearchResults());
		

		return "success";

	}

	/**
	 * This operation will close audit log.
	 * 
	 * @return String
	 */
	public String closeAuditLog() {
		
		correspondenceDataBean.setAuditLogRendered(false);
		return "success";
	}

	/**
	 * showing audit Parent history for Pricing details.
	 * 
	 * @return String
	 */
	//    public String showParentAuditHistory()
	//    {
	//    	showAuditLog();
	//// GlobalAuditsDelegate audit;
	//// final List list = new ArrayList();
	//// try
	//// {
	//// logger.debug("in show Parentaudit history");
	//// audit = new GlobalAuditsDelegate();
	//// CorrespondenceDOConvertor correspondenceDOConvertor = new
	// CorrespondenceDOConvertor();
	//// Correspondence correspondence = correspondenceDOConvertor
	//// .convertCorrespondenceVOToDO(correspondenceDataBean
	//// .getCorrespondenceRecordVO());
	//// list.add(correspondence);
	//// HashMap hm = audit.getAuditLogInfo(list);
	//// ArrayList audlist = (ArrayList) hm.get(correspondence);
	//// correspondenceDataBean.setAuditParentHistoryList(audlist);
	//// correspondenceDataBean.setAuditParentHistoryRender(true);
	////
	//// }
	//// catch (Exception e)
	//// {
	//// logger.debug("Error in show Parent audit history " + e);
	//// }
	//        return ContactManagementConstants.RENDER_SUCCESS;
	//    }

	/**
	 * This Method checks if the Entity Type for which Cr has to be added does
	 * exsists in G_CMN_ENTY_TB If its thr it returns false else false .
	 * 
	 * @param entityTypeCode
	 *            Takes entityTypeCode as param .
	 * @return boolean
	 */
	private boolean isConvertEntTypeNeeded(String entityTypeCode) {
		boolean entTypeExsists = true;
		List entTypeList = new ArrayList();
		int entTypeListSize = 0;
		/** Create ref of ContactMaintenanceDelegate */
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		/** Get the list of all entities */
		try {
			entTypeList = contactMaintenanceDelegate.getAllEntityTypes();
			if (entTypeList != null) {
				entTypeListSize = entTypeList.size();
				
			}
		} catch (CallscriptFetchBusinessException e) {
			e.printStackTrace();

		}

		for (int i = 0; i < entTypeListSize; i++) {
			CommonEntityType commonEntityType = (CommonEntityType) entTypeList
					.get(i);
			if (StringUtils.equalsIgnoreCase(commonEntityType
					.getCommonEntityTypeCode(), entityTypeCode)) {
				
				entTypeExsists = false;
				break;
			}

		}

		return entTypeExsists;

	}

	 /**
	 * reassigns the Correspondences.
	 */
	//Added for CR:ESPRD00798895
	public String reassignCorrespondence() {
		String userId = getLoggedInUser();
		Boolean flag = Boolean.FALSE;
		boolean reassignSuccess = Boolean.FALSE;
		boolean isIndividualAssign = Boolean.FALSE;
		String selectReassign = searchCorrespondenceDataBean.getSelectedDeptAll();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();


		try {
			// Code added for CR:ESPRD00798895 to bulk update the reassigned CR.
			if (!isNullOrEmptyField(selectReassign)) {
				CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = searchCorrespondenceDataBean
						.getCorrespondenceSearchCriteriaVO();

				reassignSuccess = contactMaintenanceDelegate
						.reassignAllCRRecords(correspondenceSearchCriteriaVO,
								selectReassign, userId);
				reassignSuccess = Boolean.TRUE;
			} else {
				isIndividualAssign = Boolean.TRUE;
				reassignSuccess = getSearchCorrespondenceControllerBean()
						.assignIndividualCR();

			}
			if (reassignSuccess) {
				getSearchCorrespondenceControllerBean().getCorrespondence();
				if (!searchCorrespondenceDataBean.isNoDataFlag()
						&& !isIndividualAssign) {
					setErrorMessage(
							ContactManagementConstants.RE_ASSIGN_SUCCESS_COUNT_MSG,
							new Object[] { ContactManagementConstants.SEVENTY_FIVE_THOUSAND },
							MessageUtil.ENTMESSAGES_BUNDLE, null);
				} else {
					setErrorMessage(
							MaintainContactManagementUIConstants.REASSIGN_SUCCESS_MSG,
							null, MessageUtil.ENTMESSAGES_BUNDLE, null);

				}
			} else {
				if (isIndividualAssign) {
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
					// searchCorrespondenceDataBean.setShowResultsDiv(false);
				} else {
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
					// searchCorrespondenceDataBean.setShowResultsDiv(false);
				}
			}
		} catch (CorrespondenceUpdateDataException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			searchCorrespondenceDataBean.setShowResultsDiv(false);

		}

		return ContactManagementConstants.EMPTY_STRING;
	}

	/**
	 * Moves the selected List in client services.
	 */
	//Note: Changed return type for Navigation Rule
	public String moveSelectedList() {
		
		//correspondenceDataBean.setShowSucessMessage(false);
		moveSelected(correspondenceDataBean.getSelectedList(),
				correspondenceDataBean.getReferredToList(),
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getSelectedReferredToList());

		// return ContactManagementConstants.RENDER_SUCCESS;
		return "success";

	}

	public void moveSelectedList(ActionEvent e) {
		
		//correspondenceDataBean.setShowSucessMessage(false);
		moveSelected(correspondenceDataBean.getSelectedList(),
				correspondenceDataBean.getReferredToList(),
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getSelectedReferredToList());

		// return ContactManagementConstants.RENDER_SUCCESS;
		//return "success";

	}

	/**
	 * This method is used to move the selected items to a list in client
	 * services.
	 * 
	 * @param selectedItems
	 *            The selectedItems to set.
	 * @param availableList
	 *            The availableList to set.
	 * @param selectedList
	 *            The selectedList to set.
	 */
	private void moveSelected(List selectedItems, List availableList,
			List selectedList) {
		
		//correspondenceDataBean.setShowSucessMessage(false);
		if (selectedItems != null) {
			for (Iterator iter = selectedItems.iterator(); iter.hasNext();) {
				selectedList
						.add(removeItem((String) iter.next(), availableList));

			}
		}
		
		correspondenceDataBean.setAssignedList(selectedList);
		//return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Remove the selected List in client services.
	 */
	// Note: Changed return type for Navigation Rule
	public String removeSelectedList() {
		
		//correspondenceDataBean.setShowSucessMessage(false);
		removeSelected(correspondenceDataBean.getRemovedList(),
				correspondenceDataBean.getReferredToList(),
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getSelectedReferredToList());
		
		//   return ContactManagementConstants.RENDER_SUCCESS;
		return "success";
	}

	public void removeSelectedList(ActionEvent e) {
		
		//correspondenceDataBean.setShowSucessMessage(false);
		removeSelected(correspondenceDataBean.getRemovedList(),
				correspondenceDataBean.getReferredToList(),
				correspondenceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO()
						.getSelectedReferredToList());
		
		//   return ContactManagementConstants.RENDER_SUCCESS;
		//return "success";
	}

	/**
	 * Remove selected in client services.
	 * 
	 * @param removedItems
	 *            The removedItems to set.
	 * @param availableList
	 *            The availableList to set.
	 * @param selectedList
	 *            The selectedList to set.
	 */
	private void removeSelected(List removedItems, List availableList,
			List selectedList) {
		
		//correspondenceDataBean.setShowSucessMessage(false);
		if (selectedList != null) {
			for (Iterator iter = removedItems.iterator(); iter.hasNext();) {
				availableList
						.add(removeItem((String) iter.next(), selectedList));

			}
		}
		
		//return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Method to remove an item from the list in client services.
	 * 
	 * @param value
	 *            The value to set.
	 * @param referredList
	 *            referredList to set
	 * @return SelectItem.
	 */
	private SelectItem removeItem(String value, List referredList) {
		
		//filterDataBean.setShowSucessMessage(false);
		SelectItem result = null;
		int size = referredList.size();
		for (int i = 0; i < size; i++) {
			SelectItem item = (SelectItem) referredList.get(i);
			if (value.equals(item.getValue())) {
				result = (SelectItem) referredList.remove(i);
				break;
			}
		}
		
		return result;
	}

	/**
	 * select All Submit
	 */
	public String selectAllSubmit() {
		
		List searchResults = getSearchCorrespondenceDataBean()
				.getSearchResults();
		getSearchCorrespondenceDataBean().setSearchSelectAll(true);
		getSearchCorrespondenceDataBean().setSearchDeSelectAll(false);
		List tempSearchResultsList = new ArrayList();
		CorrespondenceSearchResultsVO resultsVO = null;
		correspondenceDataBean.setStartIndexForAsc(0);

		if (searchResults != null && searchResults.size() > 0) {
			
			for (Iterator iterator = searchResults.iterator(); iterator
					.hasNext();) {
				
				resultsVO = (CorrespondenceSearchResultsVO) iterator.next();
				resultsVO.setCheckBox(true);
				tempSearchResultsList.add(resultsVO);
				/*
				 * if (recordNotAssociatedEarlier(resultsVO)) { if
				 * (resultsVO.isCheckBox()) { AssociatedCorrespondenceVO
				 * associatedCorrespondenceVO = getAssociatedCRVO(resultsVO);
				 * correspondenceDataBean.getCorrespondenceRecordVO()
				 * .getListOfAssociatedCRs().add( associatedCorrespondenceVO); } }
				 */
			}
			
			getSearchCorrespondenceDataBean().getSearchResults().clear();
			getSearchCorrespondenceDataBean().setSearchResults(
					tempSearchResultsList);
		}

		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * deselect All Submit
	 */
	public String deSelectAllSubmit() {
		
		List searchResults = getSearchCorrespondenceDataBean()
				.getSearchResults();
		List tempSearchResultsList = new ArrayList();
		getSearchCorrespondenceDataBean().setSearchSelectAll(false);
		getSearchCorrespondenceDataBean().setSearchDeSelectAll(true);
		CorrespondenceSearchResultsVO resultsVO = null;
		correspondenceDataBean.setStartIndexForAsc(0);

		if (searchResults != null && searchResults.size() > 0) {
			
			//   List tempRemoveList = new ArrayList(); // Find bug issue
			for (Iterator iterator = searchResults.iterator(); iterator
					.hasNext();) {
				
				resultsVO = (CorrespondenceSearchResultsVO) iterator.next();
				resultsVO.setCheckBox(false);
				tempSearchResultsList.add(resultsVO);
				/*
				 * List listOfAssociatedCRs = correspondenceDataBean
				 * .getCorrespondenceRecordVO() .getListOfAssociatedCRs();
				 * 
				 * String crn = resultsVO.getCorrespondenceNumber() .toString();
				 * 
				 * for (Iterator iter = listOfAssociatedCRs.iterator(); iter
				 * .hasNext();) { AssociatedCorrespondenceVO asscCRVO =
				 * (AssociatedCorrespondenceVO) iter .next(); if
				 * (asscCRVO.getCorrespondenceRecordNumber()
				 * .equalsIgnoreCase(crn)) {
				 * listOfAssociatedCRs.remove(asscCRVO); break; } }
				 */
			}
			
			getSearchCorrespondenceDataBean().setSearchResults(
					tempSearchResultsList);
		}
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * select All ExistingSubmit
	 */
	//  Note: Changed return type for Navigation Rule
	public String selectAllExistingSubmit() {
		
		// correspondenceDataBean.setDeSelAllExisRecs(false);
		//boolean selected = correspondenceDataBean.isSelAllExisRecs();
		List existingList = correspondenceDataBean.getCorrespondenceRecordVO()
				.getExistingCRLists();
		List tempexistingList = new ArrayList();

		/*
		 * if (selected) {
		 */
		if (existingList != null && existingList.size() > 0) {
			
			for (Iterator iterator = existingList.iterator(); iterator
					.hasNext();) {
				
				AssociatedCorrespondenceVO existCRVO = (AssociatedCorrespondenceVO) iterator
						.next();
				existCRVO.setLinkToCR(Boolean.TRUE);
				tempexistingList.add(existCRVO);
				/*
				 * if (existCRVO.getLinkToCR().equals(Boolean.TRUE) &&
				 * recordNotAssociatedEarlier(existCRVO)) {
				 * correspondenceDataBean.getCorrespondenceRecordVO()
				 * .getListOfAssociatedCRs().add( existCRVO); }
				 */
			}
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getExistingCRLists().clear();
			correspondenceDataBean.getCorrespondenceRecordVO()
					.setExistingCRLists(tempexistingList);
			correspondenceDataBean.setSelAllExisRecs(true);
			correspondenceDataBean.setDeSelAllExisRecs(false);
		}
		/* } */
		return "success";
	}

	/**
	 * deSelect All ExistingSubmit
	 */
	//  Note: Changed return type for Navigation Rule
	public String deSelectAllExistingSubmit() {
		
		// correspondenceDataBean.setSelAllExisRecs(false);
		//boolean deSelected = correspondenceDataBean.isDeSelAllExisRecs();
		List existingList = correspondenceDataBean.getCorrespondenceRecordVO()
				.getExistingCRLists();
		List tempexistingList = new ArrayList();

		/*
		 * if (deSelected) {
		 */
		if (existingList != null && existingList.size() > 0) {
			
			for (Iterator iterator = existingList.iterator(); iterator
					.hasNext();) {
				AssociatedCorrespondenceVO existCRVO = (AssociatedCorrespondenceVO) iterator
						.next();
				
				existCRVO.setLinkToCR(Boolean.FALSE);
				tempexistingList.add(existCRVO);

				/*
				 * if (existCRVO.getLinkToCR().equals(Boolean.FALSE)) { List
				 * assocCRList = correspondenceDataBean
				 * .getCorrespondenceRecordVO() .getListOfAssociatedCRs();
				 * 
				 * String crn = existCRVO.getCorrespondenceRecordNumber()
				 * .toString();
				 * 
				 * for (Iterator iter = assocCRList.iterator(); iter
				 * .hasNext();) { AssociatedCorrespondenceVO asscCRVO =
				 * (AssociatedCorrespondenceVO) iter .next(); if
				 * (asscCRVO.getCorrespondenceRecordNumber()
				 * .equalsIgnoreCase(crn)) { assocCRList.remove(asscCRVO);
				 * break; } } }
				 */
			}
			correspondenceDataBean.getCorrespondenceRecordVO()
					.setExistingCRLists(tempexistingList);
			correspondenceDataBean.setSelAllExisRecs(false);
			correspondenceDataBean.setDeSelAllExisRecs(true);
		}
		/* } */
		return "success";
	}

	/**
	 * This method is used to get the Current Timestamp.
	 * 
	 * @return Timestamp : Current Timestamp.
	 */
	private Timestamp getTimeStamp() {
		

		Calendar cal = Calendar.getInstance();

		return new Timestamp(cal.getTimeInMillis());
	}

	public String showAssocCorrespondence() {
		
		AssociatedCorrespondenceVO tempAssociatedCorrespondenceVO = new AssociatedCorrespondenceVO();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
		if (indexCode == null) {
			indexCode = "0";
		}
		int rowIndex = Integer.parseInt(indexCode);
		correspondenceDataBean.setStartIndexForAsc((rowIndex/10)*10);
		//String recIndex = Integer.toString(rowIndex);
		
		if (indexCode != null) {
			correspondenceDataBean.setAssocRecIndex(indexCode);
			AssociatedCorrespondenceVO associatedCorrespondenceVO = (AssociatedCorrespondenceVO) correspondenceDataBean
					.getCorrespondenceRecordVO().getListOfAssociatedCRs().get(
							rowIndex);
			if (associatedCorrespondenceVO != null) {
				if(logger.isDebugEnabled())
				{
				logger.debug("associatedCorrespondenceVO---> "
						+ associatedCorrespondenceVO
								.getCorrespondenceRecordNumber());
				}

				try {
					BeanUtils.copyProperties(tempAssociatedCorrespondenceVO,
							associatedCorrespondenceVO);
				} catch (IllegalAccessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error("error in showAssocCorrespondence");
					}
				} catch (InvocationTargetException e) {
					if(logger.isErrorEnabled())
					{
					logger.error("error in showAssocCorrespondence");
					}
				}
				if(logger.isDebugEnabled())
				{
				logger.debug("tempAssociatedCorrespondenceVO---> "
						+ tempAssociatedCorrespondenceVO
								.getCorrespondenceRecordNumber());
				}
				correspondenceDataBean
						.setAssociatedCorrespondenceVO(tempAssociatedCorrespondenceVO);
				correspondenceDataBean.setRenderShowAssocRec(true);
			}
		}
		return "success";
	}

	/**
	 * This method is usd to cancel Assoc Correspondence show table
	 * 
	 * @return String
	 */
	public String cancelShowAssocCorrespondence() {
		correspondenceDataBean.setRenderShowAssocRec(false);
		return "success";
	}

	/**
	 * this method is usd to delete Associted Record
	 * 
	 * @return String
	 */
	public String deleteAssocRecord() {
		
		AssociatedCorrespondenceVO associatedCorrespondenceVO = (AssociatedCorrespondenceVO) correspondenceDataBean
				.getCorrespondenceRecordVO().getListOfAssociatedCRs().get(
						Integer.parseInt(correspondenceDataBean
								.getAssocRecIndex()));
		if (associatedCorrespondenceVO.isExisting()) {
			Long crn = new Long(Long.parseLong(associatedCorrespondenceVO
					.getCorrespondenceRecordNumber()));
			correspondenceDataBean.getListOfAssoCrsToDelete().add(crn);
		}
		correspondenceDataBean.getCorrespondenceRecordVO()
				.getListOfAssociatedCRs().remove(
						Integer.parseInt(correspondenceDataBean
								.getAssocRecIndex()));
		if(logger.isInfoEnabled())
		{
		logger.info("getListOfAssoCrsToDelete size is "
				+ correspondenceDataBean.getListOfAssoCrsToDelete().size());
		}

		correspondenceDataBean.setRenderShowAssocRec(false);
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * sortExistingCROnDate
	 * 
	 * @param listOfCrVO
	 */
	public void sortExistingCROnDate(List listOfCrVO) {
		

		Comparator comparator = new Comparator() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 *      java.lang.Object)
			 */
			public int compare(Object obj1, Object obj2) {
				

				int returnValue = 0;

				AssociatedCorrespondenceVO associatedCrVo1 = (AssociatedCorrespondenceVO) obj1;
				AssociatedCorrespondenceVO associatedCrVo2 = (AssociatedCorrespondenceVO) obj2;

				DateFormat dateFormat = new SimpleDateFormat(
						ContactManagementConstants.DATE_FORMAT, Locale
								.getDefault());

				try {
					Date date1 = dateFormat
							.parse(associatedCrVo1.getOpenDate());
					Date date2 = dateFormat
							.parse(associatedCrVo2.getOpenDate());

					returnValue = date2.compareTo(date1);
				} catch (ParseException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					
					}
				}

				return returnValue;
			}
		};

		if (getCorrespondenceDataBean().getExistCRList() != null) {
			Collections.sort(getCorrespondenceDataBean()
					.getCorrespondenceRecordVO().getExistingCRLists(),
					comparator);
		}

	}

	//  Note: Changed return type for Navigation Rule
	public String getAssociateOptionsPlus() {
		commonEntityDataBean.setNoteFlag(false);
		correspondenceDataBean.setInputHiddenId("log_div_routing");
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = getSearchCorrespondenceDataBean();
		Map subjectMap = searchCorrespondenceDataBean.getSubjectMap();
		if (searchCorrespondenceDataBean != null) {
			searchCorrespondenceDataBean
					.setCorrespondenceSearchCriteriaVO(new CorrespondenceSearchCriteriaVO());
		}
		if (!correspondenceDataBean.isUpdateMode()) {
			showExistingCorrespondenceRecords(subjectMap);
		}
		correspondenceDataBean.setSearchForCR(true);
		correspondenceDataBean.setAssociatePlusMinusFlag(false);
		searchCorrespondenceDataBean.setSearchAssocCR(true);
		correspondenceDataBean.setRenderSearchCRPage(false);
		return "success";

	}

	//  Note: Changed return type for Navigation Rule
	public String getAssociateOptionsMinus() {
		correspondenceDataBean.setInputHiddenId("log_div_routing");
		commonEntityDataBean.setNoteFlag(false);
		correspondenceDataBean.setSearchForCR(false);
		correspondenceDataBean.setAssociatePlusMinusFlag(true);
		getSearchCorrespondenceDataBean().setSearchAssocCR(false);
		return "success";
	}

	/**
	 * Method to invoke value change from Client Services page
	 *  
	 */

	private String invokeValueChange;

	/**
	 * @return Returns the invokeValueChange.
	 */
	public String getInvokeValueChange() {

		if (correspondenceDataBean.getTargetId() != null
				&& !(correspondenceDataBean.getTargetId().equalsIgnoreCase(""))
				&& correspondenceDataBean.getTargetId().equals("R")) {
			if(logger.isDebugEnabled())
			{
			logger.debug("calling moveSelectedList -->"
					+ correspondenceDataBean.getTargetId());
			}
			moveSelectedList();
		} else if (correspondenceDataBean.getTargetId() != null
				&& !(correspondenceDataBean.getTargetId().equalsIgnoreCase(""))
				&& correspondenceDataBean.getTargetId().equals("L")) {
			if(logger.isDebugEnabled())
			{
			logger.debug("calling removeSelectedList -->"
					+ correspondenceDataBean.getTargetId());
			}
			removeSelectedList();
		}
		return invokeValueChange;
	}

	/**
	 * @param invokeValueChange
	 *            The invokeValueChange to set.
	 */
	public void setInvokeValueChange(String invokeValueChange) {
		this.invokeValueChange = invokeValueChange;
	}

	//Note: Added for DEFECT ESPRD00158685
	public String sortAssociatedIdentifiers(ActionEvent event) {

		ArrayList listOfAlternateIds = (ArrayList) correspondenceDataBean
				.getListOfAlternateIds();
		if (listOfAlternateIds != null) {
			if(logger.isInfoEnabled())
			{
			logger.info("ListOfAlternateIds size " + listOfAlternateIds.size());
			}
		}
		//For fixing defect ESPRD00576206
		correspondenceDataBean.setImageRender(event.getComponent().getId());
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);
		

		Comparator comparator = new Comparator() {

			public int compare(Object obj1, Object obj2) {
				AlternateIdentifiersVO alternateIdentifiersVO1 = (AlternateIdentifiersVO) obj1;
				AlternateIdentifiersVO alternateIdentifiersVO2 = (AlternateIdentifiersVO) obj2;
				boolean ascending = false;
				if ("asc".equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}
				if ("Type".equals(sortColumn)) {

					return ascending ? (alternateIdentifiersVO1
							.getAlternateIDTypeCode()
							.compareTo(alternateIdentifiersVO2
									.getAlternateIDTypeCode()))
							: (alternateIdentifiersVO2.getAlternateIDTypeCode()
									.compareTo(alternateIdentifiersVO1
											.getAlternateIDTypeCode()));
				}

				if ("Alternate ID".equals(sortColumn)) {

					return ascending ? (alternateIdentifiersVO1
							.getAlternateID().compareTo(alternateIdentifiersVO2
							.getAlternateID())) : (alternateIdentifiersVO2
							.getAlternateID().compareTo(alternateIdentifiersVO1
							.getAlternateID()));
				}

				if ("Line Of Business".equals(sortColumn)) {

					if (alternateIdentifiersVO1.getLineOfBusiness() == null) {
						alternateIdentifiersVO1
								.setLineOfBusiness(ProgramConstants.EMPTY_STRING);
					}
					if (alternateIdentifiersVO2.getLineOfBusiness() == null) {
						alternateIdentifiersVO2
								.setLineOfBusiness(ProgramConstants.EMPTY_STRING);
					}

					return ascending ? (alternateIdentifiersVO1
							.getLineOfBusiness()
							.compareTo(alternateIdentifiersVO2
									.getLineOfBusiness()))
							: (alternateIdentifiersVO2.getLineOfBusiness()
									.compareTo(alternateIdentifiersVO1
											.getLineOfBusiness()));
				}
				return 0;
			}

		};
		Collections.sort(listOfAlternateIds, comparator);
		return "success";
	}

	//	For fixing defect ESPRD00576206 start
	public String sortInqAbtEntity(ActionEvent event) {
		ArrayList listOfEnquiryAboutEntities = (ArrayList) correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceDetailsVO()
				.getListOfEnquiryAboutEntities();
		if (listOfEnquiryAboutEntities != null) {
			if(logger.isInfoEnabled())
			{
			logger.info("ListOfAlternateIds size "
					+ listOfEnquiryAboutEntities.size());
			}
		}
		correspondenceDataBean.setImageRender(event.getComponent().getId());
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);
		

		Comparator comparator = new Comparator() {

			public int compare(Object obj1, Object obj2) {
				CorrespondenceForVO correspondenceForVO1 = (CorrespondenceForVO) obj1;
				CorrespondenceForVO correspondenceForVO2 = (CorrespondenceForVO) obj2;
				boolean ascending = false;
				if ("Ascending".equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}
				if ("Entity ID".equals(sortColumn)) {

					return ascending ? (correspondenceForVO1.getEntityId()
							.compareTo(correspondenceForVO2.getEntityId()))
							: (correspondenceForVO2.getEntityId()
									.compareTo(correspondenceForVO1
											.getEntityId()));
				}

				if ("Name".equals(sortColumn)) {

					return ascending ? (correspondenceForVO1.getName()
							.compareToIgnoreCase(correspondenceForVO2.getName()))
							: (correspondenceForVO2.getName()
									.compareToIgnoreCase(correspondenceForVO1
											.getName()));
				}

				if ("Entity Type".equals(sortColumn)) {

					/*
					 * if(correspondenceForVO1.getEntityTypeDesc()==null) {
					 * correspondenceForVO1.setEntityTypeDesc(ProgramConstants.EMPTY_STRING); }
					 * if(correspondenceForVO2.getEntityTypeDesc()==null) {
					 * correspondenceForVO2.setEntityTypeDesc(ProgramConstants.EMPTY_STRING); }
					 */

					return ascending ? (correspondenceForVO1
							.getEntityTypeDesc().compareTo(correspondenceForVO2
							.getEntityTypeDesc())) : (correspondenceForVO2
							.getEntityTypeDesc().compareTo(correspondenceForVO1
							.getEntityTypeDesc()));
				}
				return 0;
			}

		};
		Collections.sort(listOfEnquiryAboutEntities, comparator);
		return "success";
	}

	//	For fixing defect ESPRD00576206 end
	public String moveSingleCRToAssociateCRs(
			CorrespondenceSearchResultsVO resultsVO) {
		getSearchCorrespondenceDataBean().setSearchSelectAll(false);
		getSearchCorrespondenceDataBean().setSearchDeSelectAll(false);
		if (recordNotAssociatedEarlier(resultsVO)) {
			AssociatedCorrespondenceVO associatedCorrespondenceVO = getAssociatedCRVO(resultsVO);
			correspondenceDataBean.getCorrespondenceRecordVO()
					.getListOfAssociatedCRs().add(associatedCorrespondenceVO);
		}
		return "success";
	}

	//CR2365
	/*
	 * private void logEventInquiry(String providerId) { String tableName =
	 * EventInquiryLoggingConstants.TABLE_PROVIDER; String userId = getUserID();
	 * 
	 * HashMap keyData = new HashMap();
	 * keyData.put(EventInquiryLoggingConstants.COLUMN_PROVIDER_ALT_ID,
	 * providerId);
	 * 
	 * try { EventLogInquiry inquiry =
	 * eventFactory.createInquiryEvent(portletName, userId, tableName, keyData);
	 * 
	 * if (inquiry != null) { logger.logEvent(inquiry); } } catch(Exception e) {
	 * logger.error("Failed to create EventLogInquiry", e); } }
	 */

	//CR2365
	private static String getPortletName() {
		try {
			Map requestMap = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestMap();
			PortletConfig portletConfig = (PortletConfig) requestMap
					.get(EventInquiryLoggingConstants.PORTLET_CONFIG);
			return portletConfig.getPortletName();
		} catch (Exception e) {
			return "";
		}
	}

	private void getSrchDocRepLinkPer() {

		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		String feildAccessSrchDoc = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			String userID = getUserIDForSecurity();
			Map map = (Map) facesContext.getApplication().createValueBinding(
					"#{requestScope}").getValue(facesContext);
			if (map.get("feildAccessSrchDoc") != null) {
				feildAccessSrchDoc = map.get("feildAccessSrchDoc").toString();
				map.put("feildAccessSrchDoc", feildAccessSrchDoc);

			}
			if (feildAccessSrchDoc == null
					|| (feildAccessSrchDoc.equalsIgnoreCase(""))) {

				feildAccessSrchDoc = fieldAccessControlImpl
						.getFiledAccessPermission(
								MaintainContactManagementUIConstants.SECURITY_FOR_SRCH_DOC_REPOSITORY,
								userID);
				map.put("feildAccessSrchDoc", feildAccessSrchDoc);
			}

		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (feildAccessSrchDoc != null
					&& !(feildAccessSrchDoc.equalsIgnoreCase(""))) {

				if (!(feildAccessSrchDoc.equalsIgnoreCase("v"))) {

					correspondenceDataBean.setSrchDocRepSecurity(true);
					if(logger.isDebugEnabled())
					{
					logger.debug("SrchDocRepSecurity set to true---->"
							+ correspondenceDataBean.isSrchDocRepSecurity());
					}
				} else {
					correspondenceDataBean.setSrchDocRepSecurity(false);
					if (logger == null) System.out.println("Error....!");
					if(logger.isDebugEnabled())
					{
					logger.debug("SrchDocRepSecurity set to false :1 ---->"
							+ correspondenceDataBean.isSrchDocRepSecurity());
					}
				}
			} else {
				correspondenceDataBean.setSrchDocRepSecurity(false);
				if(logger.isDebugEnabled())
				{
				logger.debug("SrchDocRepSecurity set to false ::2 ---->"
						+ correspondenceDataBean.isSrchDocRepSecurity());
				}
			}
		}

	}

	/**
	 * @return Returns the securityfield.
	 */
	public String getSecurityfield() {
		//getUserPermForReassignCorr();
		return securityfield;
	}

	/**
	 * @param securityfield
	 *            The securityfield to set.
	 */
	public void setSecurityfield(String securityfield) {
		this.securityfield = securityfield;
	}

	/**
	 * This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermForReassignCorr() {
		String userPermission = "";
		String userid = getUserIDForSecurity();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.REASSIGN_CORRESPONDENCE_PAGE,
					userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode", userPermission);

		// check the permission level for the user and set it in a boolean
		// variable. This will be used for Buttons/Links
		if (userPermission.equals("r")) {
			ctrlReqForReassignCorr = false;
		}
	}

	/**
	 * @return Returns the ctrlReqForReassignCorr.
	 */
	public boolean isCtrlReqForReassignCorr() {
		return ctrlReqForReassignCorr;
	}

	/**
	 * @param ctrlReqForReassignCorr
	 *            The ctrlReqForReassignCorr to set.
	 */
	public void setCtrlReqForReassignCorr(boolean ctrlReqForReassignCorr) {
		this.ctrlReqForReassignCorr = ctrlReqForReassignCorr;
	}

	/**
	 * To get the LettersAndResponsesControllerBean.
	 * 
	 * @return LettersAndResponsesControllerBean.
	 */
	protected LettersAndResponsesControllerBean getLettersAndResponsesControllerBean() {
		

		FacesContext fc = FacesContext.getCurrentInstance();
		if(logger.isDebugEnabled())
		{
		logger.debug("Class is "
				+ fc.getApplication().createValueBinding(
						"#{" + "LettersAndResponsesControllerBean" + "}")
						.getValue(fc).getClass());
		}

		LettersAndResponsesControllerBean lettAndResControllerBean = (LettersAndResponsesControllerBean) fc
				.getApplication().createValueBinding(
						"#{" + "LettersAndResponsesControllerBean" + "}")
				.getValue(fc);

		return lettAndResControllerBean;
	}

	/**
	 * 
	 * @return
	 */
	public String saveNotesForCr() {
		
		boolean valid = false;
		String correspondenceSk = correspondenceDataBean
				.getCorrespondenceRecordVO().getCorrespondenceRecordNumber();
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
		//   ContactManagementHelper helper = new ContactManagementHelper(); //
		// FInd bug issue
		boolean flag = commonEntityDataBean.isSaveNoteflag();
		/** Disable Filter Link */
		commonEntityDataBean.setFilterEnabled(true);
		/** end of Disable Filter Link */
		if (flag) {
			valid = commonEntityValidator.validateNotes(commonEntityDataBean
					.getCommonEntityVO().getCommonNotesVO());
			if (valid) {
				ContactHelper contactHelper = new ContactHelper();
				if (correspondenceSk != null) {
					NoteSet noteSet = commonEntityDataBean.getNoteSet();
					CMDelegate cMDelegate = new CMDelegate();
					if (noteSet == null) {
						noteSet = contactHelper.getNoteSetForCR(noteSet);
						try {
							noteSet = cMDelegate.createNotesForCR(noteSet,
									new Long(correspondenceSk));
						} catch (Exception e) {
							
							e.printStackTrace();
							// TODO: handle exception
						}
						if (noteSet != null) {
							
							commonEntityDataBean.setNoteSet(noteSet);
							NoteSetVO noteSetVO = new NoteSetVO();
							noteSetVO = contactHelper
									.convertNoteSetDomainToVO(noteSet);
							if (commonEntityDataBean != null)

							{
								commonEntityDataBean.getCommonEntityVO()
										.setNoteSetVO(noteSetVO);
								commonEntityDataBean
										.getCommonEntityVO()
										.getCommonNotesVO()
										.setCurrentNote(
												((CommonNotesVO) commonEntityDataBean
														.getNoteList().get(0))
														.getNoteText());
								commonEntityDataBean.setNoteSet(noteSet);
							}

						}
					} else if (noteSet.getNoteSetSK() != null) {
						
						CommonNotesVO commonNotesVO = commonEntityDataBean
								.getCommonEntityVO().getCommonNotesVO();
						Note note = contactHelper
								.convertCommonNoteVOToDomain(commonNotesVO);
						note.setNoteSet(noteSet);
						try {
							note = cMDelegate.createNote(note);
							commonNotesVO = contactHelper
									.convertNoteDomainToVO(note, noteSet);
							commonEntityDataBean.getNoteList().add(
									commonNotesVO);
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}
						commonEntityDataBean.getCommonEntityVO()
								.getCommonNotesVO().setCurrentNote(
										commonNotesVO.getNoteText());
					}
					sortListByDate(commonEntityDataBean.getCommonEntityVO()
							.getNoteSetVO().getNotesList());
					commonEntityDataBean.setNotesSaveMsg(true);
					commonEntityDataBean.setCommonNoteSaved(true);
					commonEntityDataBean.setNewNotesRender(false);
					commonEntityDataBean.setSaveNoteflag(false);
				} else {
					setErrorMessage(
							ContactManagementConstants.NOTE_SAVE_PRIOR_CR_MSG,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);

				}
			} else {
				if (commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
						.getNotesList() != null
						&& commonEntityDataBean.getCommonEntityVO()
								.getNoteSetVO().getNotesList().size() > 0) {
					((CommonNotesVO) commonEntityDataBean.getCommonEntityVO()
							.getNoteSetVO().getNotesList().get(0))
							.setChecked(commonEntityDataBean
									.getCommonEntityVO().getCommonNotesVO()
									.getChecked());
					commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
							.setCurrentNote(
									((CommonNotesVO) commonEntityDataBean
											.getCommonEntityVO().getNoteSetVO()
											.getNotesList().get(0))
											.getNoteText());
				}

			}

		}
		commonEntityDataBean.setSaveNotesChk(false);
		return "success";
	}

	/**
	 * 
	 * @param notesList
	 */
	private void sortListByDate(List notesList) {
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CommonNotesVO commonNotesVO1 = (CommonNotesVO) obj1;
				CommonNotesVO commonNotesVO2 = (CommonNotesVO) obj2;
				commonNotesVO1.setChecked(false);
				commonNotesVO2.setChecked(false);
				boolean ascending = false;
				if (commonNotesVO1.getStrBeginDate() != null
						&& commonNotesVO2.getStrBeginDate() != null) {
					return ascending ? (commonNotesVO1.getStrBeginDate()
							.compareTo(commonNotesVO2.getStrBeginDate()))
							: (commonNotesVO2.getStrBeginDate()
									.compareTo(commonNotesVO1.getStrBeginDate()));
				}
				return 0;
			}

		};
		Collections.sort(notesList, comparator);
	}

	private void sortListSequence(List notesList) {
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CommonNotesVO commonNotesVO1 = (CommonNotesVO) obj1;
				CommonNotesVO commonNotesVO2 = (CommonNotesVO) obj2;
				commonNotesVO1.setChecked(false);
				commonNotesVO2.setChecked(false);
				boolean ascending = false;
				if (commonNotesVO1.getNoteSequenceNumber() != null
						&& commonNotesVO2.getNoteSequenceNumber() != null) {
					return ascending ? (commonNotesVO1.getNoteSequenceNumber()
							.compareTo(commonNotesVO2.getNoteSequenceNumber()))
							: (commonNotesVO2.getNoteSequenceNumber()
									.compareTo(commonNotesVO1
											.getNoteSequenceNumber()));
				}
				return 0;
			}

		};
		Collections.sort(notesList, comparator);
	}

	/**
	 * Gets AttachmentDataBean
	 * 
	 * @return AttachmentDataBean
	 */
	protected AttachmentDataBean getAttachmentDataBean() {
		

		FacesContext fc = FacesContext.getCurrentInstance();
		return ((AttachmentDataBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ ContactManagementConstants.ATTACHMENT_BEAN_NAME
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));
	}

	public String doAuditKeyListOperation() {
		
		//List alertList = getAlertDataBean().getListOfAlertVOs();
		List attchmtList = getAttachmentDataBean().getAttachmentList();
		List routList = getRoutingDataBean().getListOfCMRoutingVOs();
		LettersAndResponsesDataBean lettersAndResponsesDataBean = getLettersDataBeanFromContext();
		List lettersList = lettersAndResponsesDataBean
				.getLetterGenerationRequests();

		//for defect ESPRD00660921
    	String crspdsk=correspondenceDataBean
        .getCorrespondenceRecordVO().getCorrespondenceRecordNumber();
    	if(crspdsk!=null){
    		openCorrespondenceInUpdateMode(crspdsk);
    	}
		//logger.debug("alertList size --->"+alertList.size());
		try {

			CorrespondenceDetailsVO correspondenceDetailsVO = correspondenceDataBean
					.getCorrespondenceRecordVO().getCorrespondenceDetailsVO();
			/* commented for unused variables
			 * CorrespondenceRecordVO correspondenceRecordVO = correspondenceDataBean
					.getCorrespondenceRecordVO();*/
			if (correspondenceDetailsVO != null) {

				List editablecorrespondence = new ArrayList();
				//editablecorrespondence.add(createAuditableFeild("Correspondence
				// Record Number","correspondenceSK"));
				//editablecorrespondence.add(createAuditableFeild("Category","categorySK"));
				editablecorrespondence.add(createAuditableFeild("Source",
						"sourceCode"));
				editablecorrespondence.add(createAuditableFeild("Priority",
						"priorityCode"));
				editablecorrespondence.add(createAuditableFeild("Subject",
						"subjectCode"));
				editablecorrespondence.add(createAuditableFeild("Status",
						"statusCode"));
				editablecorrespondence.add(createAuditableFeild("Resolution",
						"resolutionCode"));
				editablecorrespondence.add(createAuditableFeild(
						"Line Of Business", "lobCode"));
				editablecorrespondence.add(createAuditableFeild(
						"Received Date", "receivedDate"));
				editablecorrespondence.add(createAuditableFeild("TCN",
						"transactionNumber"));
				editablecorrespondence.add(createAuditableFeild("SA Nbr",
						"authorizationNumber"));
				editablecorrespondence.add(createAuditableFeild("VIP",
						"clientServiceVIPIndicator"));
				editablecorrespondence.add(createAuditableFeild(
						"Dental Appointment Made By",
						"clientServiceDentalApptmtCode"));
				editablecorrespondence.add(createAuditableFeild(
						"Limited English Proficiency",
						"clientServiceGlobalIndicator"));

				if (correspondenceDetailsVO.getAuditKeyList() != null
						&& !(correspondenceDetailsVO.getAuditKeyList()
								.isEmpty())) {
					if(logger.isDebugEnabled())
					{
					logger
							.debug("======correspondenceDetailsVO====Before Filter==="
									+ correspondenceDetailsVO.getAuditKeyList()
											.size());
					}
					AuditDataFilter.filterAuditKeys(editablecorrespondence,
							correspondenceDetailsVO);

					//for defect ESPRD00660921
					UIComponent component = ContactManagementHelper
					.findComponentInRoot("CorrespondenceId");
					if (component != null) {

						AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
						auditHistoryTable.setValue(correspondenceDetailsVO.getAuditKeyList());
						auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
								false);

					}
					if(logger.isDebugEnabled())
					{
					logger
							.debug("======correspondenceDetailsVO====After Filter==="
									+ correspondenceDetailsVO.getAuditKeyList()
											.size());
					}
				} else {
					
				}
				/*
				 * if (correspondenceRecordVO.getAuditKeyList() != null &&
				 * !(correspondenceRecordVO.getAuditKeyList().isEmpty())) {
				 * logger.debug("======correspondenceRecordVO====Before
				 * Filter==="+ correspondenceRecordVO.getAuditKeyList().size());
				 * AuditDataFilter.filterAuditKeys(editablecorrespondence,correspondenceRecordVO);
				 * logger.debug("======correspondenceRecordVO====After
				 * Filter===" +
				 * correspondenceRecordVO.getAuditKeyList().size()); } else {
				 * logger .debug("======correspondenceRecordVO====Before Filter
				 * AuditKeys Empty==="); }
				 */
			}

			/*
			 * if(alertList!=null && !(alertList.isEmpty())) { Iterator
			 * auditKeyIt = alertList.iterator(); if(auditKeyIt!=null) { List
			 * editableAlert = new ArrayList();
			 * editableAlert.add(createAuditableFeild("Due Date", "dueDate"));
			 * editableAlert.add(createAuditableFeild("Alert Type",
			 * "alertTypeCode"));
			 * editableAlert.add(createAuditableFeild("Description",
			 * "description")); editableAlert.add(createAuditableFeild("Notify
			 * via Alert", "receivingUserID"));
			 * editableAlert.add(createAuditableFeild("Status",
			 * "alertStatusCode"));
			 * 
			 * while(auditKeyIt.hasNext()) { AlertVO alertVO =
			 * (AlertVO)auditKeyIt.next();
			 * 
			 * if(alertVO.getAuditKeyList()!=null &&
			 * !(alertVO.getAuditKeyList().isEmpty())) {
			 * logger.debug("======alertVO====Before
			 * Filter==="+alertVO.getAuditKeyList().size());
			 * AuditDataFilter.filterAuditKeys(editableAlert,alertVO);
			 * logger.debug("======alertVO====After
			 * Filter==="+alertVO.getAuditKeyList().size()); } else {
			 * logger.debug("======alertVO====Before Filter Empty==="); } }
			 * //*********Heap DUmp************ FacesContext context =
			 * FacesContext.getCurrentInstance(); Map map =
			 * context.getExternalContext().getRequestParameterMap(); String
			 * strIndex = (String) map.get("rowindex"); int alertIndex
			 * =Integer.parseInt(strIndex);
			 * System.err.println("++alertIndex::"+alertIndex);
			 * if(alertIndex >= 0) {
			 * getAlertDataBean().setAlertVO((AlertVO)alertList.get(alertIndex));
			 * logger.debug("alertIndex"+alertIndex+"=================After
			 * Filter===AuditKey
			 * Size=========AlertVO==========="+getAlertDataBean().getAlertVO().getAuditKeyList().size()); } } }
			 */
			if(logger.isDebugEnabled())
			{
			logger.debug("attchmtList size --->" + attchmtList.size());
			}
			if (attchmtList != null && !(attchmtList.isEmpty())) {
				Iterator attchmtIt = attchmtList.iterator();
				if (attchmtIt != null) {
					List editableAttchmt = new ArrayList();

					editableAttchmt.add(createAuditableFeild("Date Added",
							"attachmentCreatedDate"));
					editableAttchmt.add(createAuditableFeild("Added By",
							"attachmentAddedByName"));
					editableAttchmt.add(createAuditableFeild("File Name",
							"attachmentFileName"));
					editableAttchmt.add(createAuditableFeild("Description",
							"attachmentDescription"));

					while (attchmtIt.hasNext()) {
						AttachmentVO attachmentVO = (AttachmentVO) attchmtIt
								.next();

						if (attachmentVO.getAuditKeyList() != null
								&& !(attachmentVO.getAuditKeyList().isEmpty())) {
							
							AuditDataFilter.filterAuditKeys(editableAttchmt,
									attachmentVO);
							
						}
					}
					
					// Code added for the Defect ESPRD00794237 to set the filtered audit key data list
					getAttachmentDataBean().getAttachmentVO().setAuditKeyList(getAttachmentDataBean().getAttachmentVO().getAuditKeyList());
					
					
					int attacmtIndex = getAttachmentDataBean().getAttachmentIndex();
					

					if (attacmtIndex >= 0) {
						getAttachmentDataBean().setAttachmentVO(
								(AttachmentVO) attchmtList.get(attacmtIndex));
						if(logger.isDebugEnabled())
						{
						logger
								.debug("attacmtindex"
										+ attacmtIndex
										+ "=================After Filter===AuditKey Size=========attachmentVO==========="
										+ getAttachmentDataBean()
												.getAttachmentVO()
												.getAuditKeyList().size());
						}
					}
					
					/*Below code added for the Defect ID :ESPRD00794237 to set the auditKeylist value 
					auditHistoryTable to display auidt data in  Attachment section */
					UIComponent component = ContactManagementHelper.findComponentInRoot(ContactManagementConstants.AuditID_Attachment);
					if(component!=null)
					{
						AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
						auditHistoryTable.setValue(getAttachmentDataBean().getAttachmentVO().getAuditKeyList());
					}
					//end
					
					
				}

			}
			
			if (routList != null && !(routList.isEmpty())) {
				Iterator routeIt = routList.iterator();
				if (routeIt != null) {
					List editableroute = new ArrayList();

					editableroute.add(createAuditableFeild("Created by",
							"routedByWorkUnit"));
					editableroute.add(createAuditableFeild("Assigned to",
							"routedToWorkUnit"));
					editableroute.add(createAuditableFeild("Reporting Unit",
							"lobHierarchyDescription"));
					//editableroute.add(createAuditableFeild("User Work Unit
					// Name", "workUnitSK"));
					//editableroute.add(createAuditableFeild("Work Unit Name",
					// "workUnitSK"));
					//editableroute.add(createAuditableFeild("Show",
					// "workUnitTypeCode"));
					//editableroute.add(createAuditableFeild("Business Unit",
					// "name"));
					editableroute.add(createAuditableFeild(
							"Add this Record to my Watchlist",
							"watchlistIndicator"));

					while (routeIt.hasNext()) {
						CMRoutingVO cmRoutingVO = (CMRoutingVO) routeIt.next();

						if (cmRoutingVO.getAuditKeyList() != null
								&& !(cmRoutingVO.getAuditKeyList().isEmpty())) {
							
							AuditDataFilter.filterAuditKeys(editableroute,
									cmRoutingVO);
							
						}
					}
					
					// Code added for the Defect ESPRD00794237 to set the filtered audit key data list
					getRoutingDataBean().getCmRoutingVO().setAuditKeyList(getRoutingDataBean().getCmRoutingVO().getAuditKeyList());
					
					
					int routetoIndex = getRoutingDataBean()
							.getRoutingRowIndex();
					

					if (routetoIndex >= 0) {
						getRoutingDataBean().setCmRoutingVO(
								(CMRoutingVO) routList.get(routetoIndex));
						if(logger.isDebugEnabled())
						{
						logger
								.debug("routetoIndex"
										+ routetoIndex
										+ "=================After Filter===AuditKey Size=========attachmentVO==========="
										+ getRoutingDataBean().getCmRoutingVO()
												.getAuditKeyList().size());
						}
					}
					
					/*Below code added for the Defect ID :ESPRD00794237 to set the auditKeylist value 
					auditHistoryTable to display auidt data in  Routing section */
					UIComponent component = ContactManagementHelper.findComponentInRoot(ContactManagementConstants.AuditID_Routing);
					if(component!=null)
					{
						AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
						auditHistoryTable.setValue(getRoutingDataBean().getCmRoutingVO().getAuditKeyList());
					}
					//end
					
					
				}
			}
			if (lettersList != null) {
				
				if (lettersList != null && lettersList.size() > 0) {
					Iterator auditKeyIt = lettersList.iterator();
					List editableLetters = new ArrayList();
					editableLetters.add(createAuditableFeild(
							"Notify via Alert", "notifyAlertUser"));
					editableLetters.add(createAuditableFeild("Alert Based On",
							"alertBasedOnColName"));
					editableLetters.add(createAuditableFeild(
							"Send Alert # of Days", "sendAlertDaysCode"));
					editableLetters.add(createAuditableFeild("Explanation",
							"explanationText"));
					while (auditKeyIt.hasNext()) {
						LetterGenerationInputVO letterGenerationInputVO = (LetterGenerationInputVO) auditKeyIt
								.next();
						if (letterGenerationInputVO.getAuditKeyList() != null
								&& !(letterGenerationInputVO.getAuditKeyList()
										.isEmpty())) {
							AuditDataFilter.filterAuditKeys(editableLetters,
									letterGenerationInputVO);
						}
						String letersindex = lettersAndResponsesDataBean
								.getSelectedIndex();
						if (letersindex != null
								&& letersindex.trim().length() > 0) {
							lettersAndResponsesDataBean
									.setLetterGenerationInputVO((LetterGenerationInputVO) lettersList
											.get(Integer.parseInt(letersindex)));
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		correspondenceDataBean.setAuditLogFlag(true);

		return "";
	}

	private AuditableField createAuditableFeild(String feildName,
			String domainAttributeName) {

		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);

		return auditableField;

	}

	/**
	 * This method get LettersDataBean object.
	 * 
	 * @return LettersAndResponsesDataBean.
	 */
	public final LettersAndResponsesDataBean getLettersDataBeanFromContext() {

		
		FacesContext fc = FacesContext.getCurrentInstance();
		LettersAndResponsesDataBean letterTemplateDataBean = (LettersAndResponsesDataBean) fc
				.getApplication().createValueBinding(
						"#{" + GlobalLetterConstants.LETTER_RESPONSES_DATA_BEAN
								+ "}").getValue(fc);
		return letterTemplateDataBean;
	}

	private void sortSubjectSequence(List sujectList) {
		Comparator selectItemComparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				String first = null;
				String second = null;

				SelectItem s1 = (SelectItem) obj1;
				SelectItem s2 = (SelectItem) obj2;

				//commented for unused variables
				//boolean ascending = true;

				if (s1.getLabel() != null && s2.getLabel() != null) {
					first = s1.getLabel().toLowerCase();
					second = s2.getLabel().toLowerCase();

					return first.compareTo(second);
				}
				return 0;
			}

		};
		Collections.sort(sujectList, selectItemComparator);
	}
	
	 private void sortDept(List listOfDepartments) 
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

						return first.compareToIgnoreCase(second);
					}
					return 0;
				}

			};
			Collections.sort(listOfDepartments, selectItemComparator);
		}

	/**
	 * This method is used to convert String object to Date object.
	 * 
	 * @param begdate
	 *            This contains the begin Date.
	 * @return Date
	 */
	private static Date dateConverter(String begdate) {

		Date beginDate = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat(
				ProgramConstants.DATE_FORMAT, Locale.getDefault());
		if (begdate != null) {
			if (begdate.indexOf('/') != -1) {
				try {
					beginDate = new Date(sdf1.parse(begdate).getTime());

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return beginDate;
	}
}