/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.cots.CommonLetter.view.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.portlet.ActionRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.cots.CommonLetter.view.exception.LetterResponseUIException;
import com.acs.enterprise.common.cots.edms.util.EDMSURLGeneratorImpl;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterGenerationRequest;
import com.acs.enterprise.common.cots.lettergeneration.delegate.LetterGenerationDelegate;
import com.acs.enterprise.common.cots.lettergeneration.helper.LetterGenerationInputDOConverter;
import com.acs.enterprise.common.cots.lettergeneration.vo.CommonLetterInputVO;
import com.acs.enterprise.common.cots.lettergeneration.vo.LetterGenerationInputVO;
import com.acs.enterprise.common.cots.lettergeneration.vo.LetterGenerationReceiverVO;
import com.acs.enterprise.common.cots.util.GlobalLetterConstants;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CMLogCaseControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CMLogCaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CorrespondenceControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CorrespondenceDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;

/**
 * Created on Jul 20, 2008
 * 
 * @author Wipro This class is a controller bean used for Letters and Responses
 *         functionality.
 */
public class LettersAndResponsesControllerBean extends
		EnterpriseBaseControllerBean {
	/**
	 * logger
	 */
	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(LettersAndResponsesControllerBean.class);

	/**
	 * Holds initialized string
	 */
	private String initialized = "success";

	/**
	 * This field is used to store whether user has permission.
	 */
	private boolean controlRequired = true;

	/**
	 * This method initializes the databean
	 * 
	 * @return Returns the isInitialized.
	 */
	public String getInitialized() {
		initPage();
		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * This method sets the initilzation status
	 * 
	 * @param isInitialized
	 *            The isInitialized to set.
	 */
	public void setInitialized(String isInitialized) {
		this.initialized = GlobalLetterConstants.SUCCESS;
	}

	/**
	 * 
	 *  
	 */
	private void initPage() {
		logger.info("Inside initPage....");
		getAssociatedLetterGenerationRequests();
	}

	/**
	 * Default Constructor
	 */
	public LettersAndResponsesControllerBean() {
		super();
//		getUserPermission();
		logger.info("Inside LettersAndResponsesControllerBean constructor");

	}

	/**
	 * @return Returns the controlRequired.
	 */
	public boolean isControlRequired() {
		return controlRequired;
	}

	/**
	 * @param controlRequired
	 *            The controlRequired to set.
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}
	
    //Removed static modified as part of heapdump - static item issue
	public final String CMLOGCASE_DATA_BEAN = "logCaseDataBean";
	
//  Added by Infinite while Performance Issues...
    /**
     * This method will return the reference of the data bean.
     * 
     * @return relatedDataBean
     */
	
	//Below two methods are added for open the IPC new tab
	public String invokeCreateLetter() {

		
		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		lrDataBean
				.setCreateLetter("logCrspdlettNrespSubview:logCrspdlettersNrespSubview:createLetterBtn");
		
		return GlobalLetterConstants.RENDER_SUCCESS;
	}
	public String invokeCreateLetterCase() {

		/*LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		lrDataBean.setCreateLetterCase("caseLettersAndResponses:logCrspdlettersNrespSubview:createLetterBtn");
		return "";*/
		logger.info("********* invokeCreateLetter() called ********** ");
		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		logger.info("isCreateLetter ####  "+lrDataBean.isCreateLtrCase());
		lrDataBean.setCreateLtrCase(true);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//lrDataBean.setViewLetterReq("newServiceAuthorization:basicSAInfo:lettersandresponses:invokeViewLetterRequest");
		handleNavigationPath(facesContext);
		logger.info("********* returning SUCCESS ********** ");
		return "success";
	}
	
	public void handleNavigationPath(FacesContext facesContext)
	{	
		logger.info("********* in handleNavigationPath START********** ");
		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		String outCome = "/jsp/rightClickProgressLetters.jsp";
		UIViewRoot viewRoot = (UIViewRoot) facesContext.getViewRoot();		   
		lrDataBean.setPreView(viewRoot.getViewId());
		viewRoot.setViewId(outCome); 
        facesContext.setViewRoot(viewRoot);  
        logger.info("********* in handleNavigationPath END********** ");
	}
	
	    
    public Object getDataBean(String dataBeanName)

    {
        FacesContext fc = FacesContext.getCurrentInstance();
        String valueBindingStr = "#{" + dataBeanName + "}";
        Object dataBeanObj = null;
        dataBeanObj = fc.getApplication().getVariableResolver()
                .resolveVariable(fc, dataBeanName);
        if (dataBeanObj == null)

        {

            dataBeanObj = fc.getApplication().createValueBinding(
                    valueBindingStr).getValue(fc);

        }
        return dataBeanObj;

    }
	/**
	 * This method gets the permission level for the logged in user
	 *  
	 */
	public void getUserPermission() {
		String userPermission = "";
		String userid = getUserID();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.LETTERS_AND_RESPONSES_PAGE,
					userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			logger.error("SecurityFLSServiceException " + e.getMessage());
		}
		userPermission = userPermission != null ? userPermission.trim() : "";

		// check the permission level for the user and set it in a boolean
		// variable. This will be used for Buttons/Links
		if (userPermission.equals("r")) {
			controlRequired = false;
		}
	}

	/**
	 * This method get the User ID
	 * 
	 * @return String
	 */
	private String getUserID() {
		String userID = null;
		try {

			HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(
					renderrequest, renderresponse);

			if (enterpriseUserProfile != null) {
				userID = enterpriseUserProfile.getUserId();
			}
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("LOGGED_IN_USERID", userID);
		} catch (Exception e) {
			e.getCause();
			logger.error("user id Exception " + e.getMessage());
		}

		return userID;
	}

	/**
	 * This method is the action method to get the letter request
	 * 
	 * @return String
	 */
	public String getAssociatedLetterGenerationRequests() {

		logger.info("Inside getAssociatedLetterGenerationRequests");
		try {
			CorrespondenceControllerBean correspondenceControllerBean = new CorrespondenceControllerBean();
			CorrespondenceDataBean correspondanceDataBean = correspondenceControllerBean
					.getCorrespondenceDataBean();
			LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();

			if (lrDataBean.getLetterGenerationInputVO() != null) {
				logger.info ("PAGE ID -> " + lrDataBean.getLetterGenerationInputVO().getEdmsPageURL());
			}
		/*	Commented for pagination defect ESPRD00632595 
		if(lrDataBean.getLetterGenerationRequests()!=null && !lrDataBean.getLetterGenerationRequests().isEmpty()){
				logger.info ("LetterGenerationRequests() not NULL  -> ");
				lrDataBean.getLetterGenerationRequests().clear();
			} */
			String recvAttrib = null;
			logger.debug("CRNRecordVO = " + correspondanceDataBean.getCorrespondenceRecordVO());
			if (correspondanceDataBean.getCorrespondenceRecordVO() != null
					&& correspondanceDataBean.getCorrespondenceRecordVO()
							.getCorrespondenceRecordNumber() != null) {
				logger.debug("CRNRecordNumber = " + correspondanceDataBean.getCorrespondenceRecordVO()
						.getCorrespondenceRecordNumber());
				lrDataBean.setFuncSK(new Long(correspondanceDataBean
						.getCorrespondenceRecordVO()
						.getCorrespondenceRecordNumber()));
				lrDataBean
						.setFuncArea(GlobalLetterConstants.FUNCTIONAL_AREA_CD_CORRESPONDENCE);
				lrDataBean.setLetterCategory(correspondanceDataBean
						.getCorrespondenceRecordVO()
						.getCorrespondenceDetailsVO().getCategorySK());
			}
			logger.info("Func Area" + lrDataBean.getFuncArea());
			logger.info("Func SK" + lrDataBean.getFuncSK());
			logger.info("Letter Category" + lrDataBean.getLetterCategory());

			//			if (lrDataBean.getLetterGenerationRequests() == null) {
			LetterGenerationDelegate delegate = new LetterGenerationDelegate();
			List resultList = null;
			List letterRequestVOList = new ArrayList();

			if (lrDataBean.getFuncArea() == null) {
				recvAttrib = getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_AREA);
				lrDataBean.setFuncArea(recvAttrib);

			}

			if (lrDataBean.getFuncSK() == null) {
				recvAttrib = getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_SK);
				// lrDataBean.setFuncSK(Long.valueOf(recvAttrib));
			}

			if (lrDataBean.getLetterCategory() == null) {
				// recvAttrib =
				// getReqParamFromCtx(GlobalLetterConstants.STR_LETTER_CATEGORY);
				// lrDataBean.setLetterCategory(recvAttrib);
			}
			if (lrDataBean.getFuncSK() != null) {
				resultList = delegate
						.getLetterRequestList(
								GlobalLetterConstants.FUNCTIONAL_AREA_CD_CORRESPONDENCE,
								lrDataBean.getFuncSK());
				logger.debug("LetterRequestList Size:" + resultList.size());
				lrDataBean.setLetterRequestList(resultList);
			}
			if (resultList != null && (!resultList.isEmpty())) {
				letterRequestVOList = this.convertLetterRequestDOToVO(
						resultList, lrDataBean);
			}
			List tempLetterRequestVOList = new ArrayList();
			List ltrGenReqList = new ArrayList();
			if (letterRequestVOList.size() > 0) {
				Iterator iter = letterRequestVOList.iterator();
				while (iter.hasNext()) {
					LetterGenerationInputVO letterGenerationInputVO = (LetterGenerationInputVO) iter
							.next();
					Set ltrReceiverSet = letterGenerationInputVO
							.getLetterReceiver();
					logger.debug("ltrReceiverSet----" + ltrReceiverSet.size());
					String receName = "";
					String receiverNames = "";
					int count = 0;
					Iterator itrLtrReceiver = ltrReceiverSet.iterator();
					while (itrLtrReceiver.hasNext()) {
						LetterGenerationReceiverVO letterGenerationReceiverVO = (LetterGenerationReceiverVO) itrLtrReceiver
								.next();
						String typeCode = letterGenerationReceiverVO
								.getLetterRequestTypeCode();
						if (typeCode != null && typeCode.equalsIgnoreCase("to")) {
							logger.info("getLetterRequestTypeCode():::"
									+ typeCode);
							String firstName = (String) letterGenerationReceiverVO
									.getFirstName();
							String lastName = (String) letterGenerationReceiverVO
									.getLastName();
							logger.debug("full name====="
									+ letterGenerationReceiverVO.getFirstName()
									+ " "
									+ letterGenerationReceiverVO.getLastName());
							if (firstName == null) {
								firstName = "";
							}

							if (lastName == null) {
								lastName = "";
							}
							receName = letterGenerationReceiverVO
									.getFirstName()
									+ " "
									+ letterGenerationReceiverVO.getLastName();
							logger.debug("receName BEFORE :::::" + receName);
							if("".equals(receName.trim()))
							{
								receName = letterGenerationReceiverVO.getDbaName();
								logger.debug("receName:::::" + receName);
							}
							
							
							if (count == 0) {
								receiverNames = receiverNames + receName;
								count++;
							} else {
								receiverNames = receiverNames + " " + receName;
							}

						}

					}
					logger.info("receiverNames:::" + receiverNames);
					letterGenerationInputVO.setReceiverName(receiverNames);
					tempLetterRequestVOList = eleminateDuplicateRecordsBasedOnLetterReq(
							letterGenerationInputVO.getLetterReqSK(),
							letterRequestVOList, tempLetterRequestVOList);
					ltrGenReqList.add(letterGenerationInputVO);
				}
			}
			ArrayList ltrgenVOList = new ArrayList();
			logger.info("tempLetterRequestVOList = " + tempLetterRequestVOList);
			/*if (tempLetterRequestVOList != null) {
				logger.info("PAGEURL = " + lrDataBean.getLetterGenerationInputVO().getEdmsPageURL());
				if (lrDataBean.getLetterGenerationInputVO().getEdmsPageURL() != null) {
					tempLetterRequestVOList.set(lrDataBean.getSelectedLetterRequestRowIndex(), lrDataBean.getLetterGenerationInputVO());
				}
			}*/
			ltrgenVOList.addAll(tempLetterRequestVOList);
			Comparator comparator = new Comparator() {
				public int compare(Object obj1, Object obj2) {
					LetterGenerationInputVO ltrgenVO1 = (LetterGenerationInputVO) obj1;
					LetterGenerationInputVO ltrgenVO2 = (LetterGenerationInputVO) obj2;
					/*String fisrtObj = null;
					String secondObj = null;
					fisrtObj = ltrgenVO1.getCreatedDt();
					secondObj = ltrgenVO2.getCreatedDt();
					if (fisrtObj == null) {
						fisrtObj = GlobalLetterConstants.EMPTY_STRING;
					}
					if (secondObj == null) {
						secondObj = GlobalLetterConstants.EMPTY_STRING;
					}
					return (secondObj).compareTo(fisrtObj);*/
					boolean ascending = false;
				      Integer int1 = new Integer(ltrgenVO1.getLetterReqSK());
				      Integer int2 = new Integer(ltrgenVO2.getLetterReqSK());
				      return ascending ? (int1).compareTo(int2) : (int2
				        .compareTo(int1));
				}
			};
			Collections.sort(ltrgenVOList, comparator);
			if(ltrgenVOList!=null && !ltrgenVOList.isEmpty()
					&& lrDataBean.isRenderEditSection() == true 
					&& lrDataBean.getLetterGenerationInputVO().getEdmsPageURL() != null){
				logger.info("PAGEURL = " + lrDataBean.getLetterGenerationInputVO().getEdmsPageURL());
				logger.info("Open Letter ID = " + lrDataBean.getLetterGenerationInputVO().getLetterReqSK());
					for(int m=0;m < ltrgenVOList.size(); m++){
						LetterGenerationInputVO ltrGenInputVO = (LetterGenerationInputVO)ltrgenVOList.get(m);
						logger.info(" Letter ID = " + ltrGenInputVO.getLetterReqSK());
						if(ltrGenInputVO.getLetterReqSK()!=null 
								&& ltrGenInputVO.getLetterReqSK().equals(lrDataBean.getLetterGenerationInputVO().getLetterReqSK())){
							ltrgenVOList.set(m,lrDataBean.getLetterGenerationInputVO());
						}
				}
			}
			
			// Following code is to fix Audit Log issue where aud_user_id & add_aud_user_id etc fields 
			// also were displayed in Audit list. It is part of Defect 620746.
			if(ltrgenVOList != null && ltrgenVOList.size() > 0) {
				
				Iterator auditKeyIt = ltrgenVOList.iterator();
				List editableLetters = new ArrayList();
				editableLetters.add(createAuditableField("Notify via Alert","notifyAlertUser"));
				editableLetters.add(createAuditableField("Alert Based On","alertBasedOnColName"));
				editableLetters.add(createAuditableField("Send Alert # of Days","sendAlertDaysCode"));
				editableLetters.add(createAuditableField("Explanation","explanationText"));
				
				while(auditKeyIt.hasNext()){
					LetterGenerationInputVO ltrGenInputVO = (LetterGenerationInputVO)auditKeyIt.next();
					if(ltrGenInputVO.getAuditKeyList() != null && !(ltrGenInputVO.getAuditKeyList().isEmpty())) {
						AuditDataFilter.filterAuditKeys(editableLetters, ltrGenInputVO);
					}
				}
				
				String letersindex = lrDataBean.getSelectedIndex();
				if(letersindex!=null && letersindex.trim().length()>0) {
					lrDataBean.setLetterGenerationInputVO((LetterGenerationInputVO)
							ltrgenVOList.get(Integer.parseInt(letersindex)));
				}
			}
			
			lrDataBean.setLetterGenerationRequests(ltrgenVOList);
			if(lrDataBean.isRenderEditSection() == true){
				LetterGenerationInputVO letterGenerationInputVO = null;
				letterGenerationInputVO = getClone(getSelectedLetterRequestVO(lrDataBean));
				lrDataBean.setLetterGenerationInputVO(letterGenerationInputVO);
			}
			//lrDataBean.setLetterGenerationRequests(tempLetterRequestVOList);
			//lrDataBean.setLetterGenerationRequests(letterRequestVOList);
			//			}
			logger
					.info("#### End Inside getAssociatedLetterGenerationRequests");

		} catch (Exception e) {
			logger.error("Exception " + e.getMessage());
			return GlobalLetterConstants.RENDER_SUCCESS;
		}
		return GlobalLetterConstants.RENDER_SUCCESS;
	}


	/**
	 * @param letterReqSK
	 * @param letterRequestVOList
	 */
	private List eleminateDuplicateRecordsBasedOnLetterReq(String letterReqSK,
			List letterRequestVOList, List tempLetterRequestVOList) {

		logger.info("::::::::inside method:::::::::::::");
		Iterator itrLetterReq = letterRequestVOList.iterator();
		while (itrLetterReq.hasNext()) {
			LetterGenerationInputVO letterGenerationInputVO = (LetterGenerationInputVO) itrLetterReq
					.next();
			if (null != letterGenerationInputVO.getLetterReqSK()
					&& letterGenerationInputVO.getLetterReqSK().equals(
							letterReqSK)) {
				logger.info("in side if :::::::::");
				if (!tempLetterRequestVOList.isEmpty()) {
					logger.info("tempLetterRequestVOList size::::"
							+ tempLetterRequestVOList.size());
					Iterator itrRequestVOList = tempLetterRequestVOList
							.iterator();
					boolean flag = false;
					while (itrRequestVOList.hasNext()) {
						LetterGenerationInputVO tempLetterGenerationInputVO = (LetterGenerationInputVO) itrRequestVOList
								.next();
						if (tempLetterGenerationInputVO.getLetterReqSK()
								.equals(
										letterGenerationInputVO
												.getLetterReqSK())) {
							flag = true;
						}
					}
					if (!flag) {
						tempLetterRequestVOList.add(letterGenerationInputVO);
					}
				} else {
					tempLetterRequestVOList.add(letterGenerationInputVO);
				}
			}
		}
		return tempLetterRequestVOList;
		// TODO Auto-generated method stub
	}

	/**
	 * This method is the action method to Create a new Letter
	 */
	public String createLetter() {
		logger.info("LettersAndResponsesControllerBean createLetter:START");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String preView=null;
		String funcArea = getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_SK);
		String funcAreaCD = getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_AREA);
		String letterCategory = getReqParamFromCtx(GlobalLetterConstants.STR_LETTER_CATEGORY);
		String memberID = getReqParamFromCtx("MEMBER_ID");
		//CR_ESPRD00463663_LogCase_Changes_22MAY2010
		String logCaseEventSeqNum = getReqParamFromCtx("LogCaseEventSeqNum");
		String logCaseStepSeqNum = getReqParamFromCtx("LogCaseStepSeqNum");
		preView = getReqParamFromCtx("preView");
		if(preView!=null)
		{
			UIViewRoot viewRoot = (UIViewRoot) facesContext.getViewRoot();
			logger.info(" viewRoot "+ viewRoot);
			viewRoot.setViewId(preView); 
			facesContext.setViewRoot(viewRoot);
		}
		logger.info("logCaseEventSeqNum : " + logCaseEventSeqNum);
		logger.info("logCaseStepSeqNum : " + logCaseStepSeqNum);//EOF CR_ESPRD00463663_LogCase_Changes_22MAY2010
		logger.info("funcArea : " + funcArea);
		logger.info("funcAreaCD : " + funcAreaCD);
		logger.info("letterCategory : " + letterCategory);
		logger.info(" MEMBER_ID >>>  " + memberID);
		if(letterCategory == null || letterCategory.equals("")){
			logger.info("letterCategory is NULL");
			letterCategory = "";//getDataBeanFromContext().getLetterCategory();
			logger.info("letterCategory : " + letterCategory);
		}
		CommonLetterInputVO inputVO = new CommonLetterInputVO();
		inputVO.setFunctionalAreaCode(funcAreaCD);
		inputVO.setFunctionalAreaSK(funcArea);
		inputVO.setLetterCategory(letterCategory);
		inputVO.setMemberID(memberID);
		//CR_ESPRD00463663_LogCase_Changes_22MAY2010
		if(logCaseStepSeqNum!=null && logCaseStepSeqNum.trim().length()>0){
			
			inputVO.setSentSeqNum(logCaseStepSeqNum);
			
		}else if(logCaseEventSeqNum != null && logCaseEventSeqNum.trim().length()>0){
			
			inputVO.setSentSeqNum(logCaseEventSeqNum);
			
		}//EOF CR_ESPRD00463663_LogCase_Changes_22MAY2010

		Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding("#{requestScope}").getValue(facesContext);
		requestScope.put(GlobalLetterConstants.COMMON_LTR_INPUT, inputVO);

		getDataBeanFromContext().setRenderEditSection(false);
		getDataBeanFromContext().setSortLetterAndRespFlag(false);
		//logger.info("End Inside createLetter");
		logger.info("LettersAndResponsesControllerBean createLetter:END");
		return GlobalLetterConstants.SUCCESS;
	}

	private LetterGenerationInputVO letterGenerationInputVOPre = null;

	/**
	 * Method to open the letter request in Edit mode
	 * 
	 * @return String
	 * @throws LetterResponseUIException
	 *             exception
	 */
	public String openLetterRequestInEditMode()
			throws LetterResponseUIException {

		logger.info("LettersAndResponsesControllerBean openLetterRequestInEditMode:START");
		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		//for ESPRD00741245
		CMLogCaseDataBean logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//Modified for the defect ESPRD00880370
		logCaseDataBean.setPageFocusId("caseLettersHeader");
		logCaseDataBean.setCursorFocusValue("LOGCASELTRSPNSNOTIFY1");
		lrDataBean.setSortLetterAndRespFlag(true);

		List deptUserList = new ArrayList();

		// Infinite implementation for Row Level Clicking CR 1545
		FacesContext context = FacesContext.getCurrentInstance();
		Map rowMap = context.getExternalContext().getRequestParameterMap();
		int rowIndex = Integer.parseInt((String) rowMap.get("rowNum"));
		logger.debug("rowIndex = " + rowIndex);
		lrDataBean.setRenderEditSection(true);
		lrDataBean.setSelectedIndex((String) rowMap.get("rowNum"));
		// updating the selected row index
		lrDataBean.setSelectedLetterRequestRowIndex(rowIndex);
		// End of Infinite implementation for Row Level Clicking CR 1545
		/*LetterGenerationInputVO ltrGenerationInputVO = lrDataBean
		.getLetterGenerationInputVO();*/
		/*LetterGenerationInputVO ltrGenerationInputVO = 
				(LetterGenerationInputVO)lrDataBean.getLetterGenerationRequests().get(rowIndex);
		if (ltrGenerationInputVO.getDueDateOffsetNum() == null
				|| ltrGenerationInputVO.getDueDateOffsetNum().intValue()==0) {
			lrDataBean.setDuedateoffsetnumRender(false);
		} else {
			lrDataBean.setDuedateoffsetnumRender(true);

		}*/

		letterGenerationInputVOPre = new LetterGenerationInputVO();
		LetterGenerationInputVO letterGenerationInputVO = null;

		letterGenerationInputVO = getClone(getSelectedLetterRequestVO(lrDataBean));
		if (letterGenerationInputVO.getDueDateOffsetNum() == null
				|| letterGenerationInputVO.getDueDateOffsetNum().intValue()==0) {
			lrDataBean.setDuedateoffsetnumRender(false);
		} else {
			lrDataBean.setDuedateoffsetnumRender(true);

		}
		UIComponent component = ContactManagementHelper.findComponentInRoot("logCrspdLtrRespDetailsAuditId");
		if(component!=null)
		{
			AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
			auditHistoryTable.setValue(letterGenerationInputVO.getAuditKeyList());			
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(false);
			
		}
		//to display audit log details for edited letter record.
		UIComponent casecomponent = ContactManagementHelper.findComponentInRoot("ViewCaseLettersWithCaseAuditId");
		if(casecomponent!=null)
		{
			AuditHistoryTable auditHistoryTable = (AuditHistoryTable)casecomponent ;
			auditHistoryTable.setValue(letterGenerationInputVO.getAuditKeyList());
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(false);
			
		}
		logger.info("ltrGenerationInputVO.getDueDateOffsetNum() = " + letterGenerationInputVO.getDueDateOffsetNum());
		if (letterGenerationInputVO.getDueDateOffsetNum() == null
				|| letterGenerationInputVO.getDueDateOffsetNum().intValue()==0) {
			lrDataBean.setDuedateoffsetnumRender(false);
		} else {
			lrDataBean.setDuedateoffsetnumRender(true);

		}
		try {
			EDMSURLGeneratorImpl edmsURLGenerator = new EDMSURLGeneratorImpl();
			if (letterGenerationInputVO.getEdmsPageId() != null) {
				letterGenerationInputVO.setEdmsPageURL(edmsURLGenerator
						.getURL(letterGenerationInputVO.getEdmsPageId()));
			}
		} catch (Exception e) {
			logger.error("EDMS Exception " + e.getMessage());
			throw new LetterResponseUIException(e);

		}

		try {

			logger.debug("lrDataBean.getAlertNumberOfDays() = " + lrDataBean.getAlertNumberOfDays());
			if (lrDataBean.getAlertNumberOfDays() == null) {
				populateReferenceData(lrDataBean);

			}
		} catch (LetterResponseUIException e) {

			logger.error("Exception occured" + e.getMessage());
			throw new LetterResponseUIException(e.getErrorCode(), e
					.getMessage());

		}

		try {

			/**
			 * below code fetches the user departments for a user who created
			 * the letter request
			 */

			if (letterGenerationInputVO.getLetterReqSK() != null) {

				/**
				 * Following to add letter request sk in session scope, for View
				 * Letter Request functionality
				 */
				logger.debug("letterGenerationInputVO.getLetterReqSK() = "
						+ letterGenerationInputVO.getLetterReqSK());
				// Setting Letter Request SK in session scope and is retrieved
				// in processAction ()
				ActionRequest request = (ActionRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				request.getPortletSession(false).setAttribute("ltrReqSK",
						letterGenerationInputVO.getLetterReqSK(),
						PortletSession.PORTLET_SCOPE);

				/** END OF CODE FOR View Letter Request */

				List notifyViaAlertList = new ArrayList();
				// DEFECT 368956
				//deptUserList = new ContactMaintenanceDelegate ().getUsersOrdered(letterGenerationInputVO.getAddedAuditUserID());
				deptUserList = new ContactMaintenanceDelegate ().getAllUsersOrdered(letterGenerationInputVO.getAddedAuditUserID());
				logger.debug("deptUserList.size = " + deptUserList.size());
				notifyViaAlertList = getNotifyUserList(deptUserList);
				logger.debug("notifyViaAlertList.size = " + notifyViaAlertList.size());

				/**
				 * If there are associated departments then fetch all the
				 * department users
				 */

				
				if (notifyViaAlertList == null) {
					logger.debug("notifyViaAlertList is NULL");
					notifyViaAlertList = new ArrayList ();
				}
				
				
				lrDataBean.setNotifyViaAlert(notifyViaAlertList);
				/*Set ltrReqSet = new HashSet(lrDataBean.getLetterGenerationRequests());
			    lrDataBean.getLetterGenerationRequests().clear();
			    lrDataBean.setLetterGenerationRequests(new ArrayList(ltrReqSet));*/
			}
		} catch (Exception e) {
			logger.error("Exception occured " + e.getMessage());
			throw new LetterResponseUIException(e);

		}

		/*
		 * PortletSession portletSession =
		 * (PortletSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 * portletSession.setAttribute("ltrGenInputVOTemp",
		 * letterGenerationInputVO);
		 */
		logger.info ("EDMS Page Id = " + letterGenerationInputVO.getEdmsPageURL());
		lrDataBean.setLetterGenerationInputVO(letterGenerationInputVO);

		lrDataBean.setShowSucessMessage(false);
		logger.info("LettersAndResponsesControllerBean openLetterRequestInEditMode:END");
		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * This method gets the copy of the object passed as argument
	 * 
	 * @param requestVO
	 *            LetterGenerationInputVO
	 * @return LetterGenerationInputVO requestVO
	 */
	public LetterGenerationInputVO getClone(LetterGenerationInputVO requestVO) {
		LetterGenerationInputVO newLtrReq = new LetterGenerationInputVO();

		if (requestVO.getUserID() != null) {
			newLtrReq.setUserID(requestVO.getUserID());
		}
		if (requestVO.getUserWorkUnitSK() != null) {
			newLtrReq.setUserWorkUnitSK(requestVO.getUserWorkUnitSK());
		}
		// It is primitive type
		newLtrReq.setVersionNo(requestVO.getVersionNo());
		if (requestVO.getAuditUserID() != null) {
			newLtrReq.setAuditUserID(new String(requestVO.getAuditUserID()));
		}
		if (requestVO.getAddedAuditUserID() != null) {
			newLtrReq.setAddedAuditUserID(new String(requestVO
					.getAddedAuditUserID()));
		}
		if (requestVO.getAddedAuditTimeStamp() != null) {
			// This will not be modified by user
			newLtrReq
					.setAddedAuditTimeStamp(requestVO.getAddedAuditTimeStamp());
		}
		if (requestVO.getAltrBsdOnColNm() != null) {
			newLtrReq.setAltrBsdOnColNm(new String(requestVO
					.getAltrBsdOnColNm()));
		}
		if (requestVO.getSendAlertDaysCd() != null) {
			newLtrReq.setSendAlertDaysCd(new String(requestVO
					.getSendAlertDaysCd()));
		}
		if (requestVO.getAlertBfrAftrCd() != null) {
			newLtrReq.setAlertBfrAftrCd(new String(requestVO
					.getAlertBfrAftrCd()));
		}
		if (requestVO.getDueDateOffsetNum() != null) {
			newLtrReq.setDueDateOffsetNum(new Integer(requestVO
					.getDueDateOffsetNum().toString()));

		}
		if (requestVO.getEdmsPageURL() != null) {
			newLtrReq.setEdmsPageURL(new String(requestVO.getEdmsPageURL()));
		}
		if (requestVO.getExplnText() != null) {
			newLtrReq.setExplnText(new String(requestVO.getExplnText()));
		}
		if (requestVO.getLetterID() != null) {
			newLtrReq.setLetterID(new String(requestVO.getLetterID()));
		}
		if (requestVO.getLtrReqDispCD() != null) {
			newLtrReq.setLtrReqDispCD(new String(requestVO.getLtrReqDispCD()));
		}
		if (requestVO.getNotifyAlrtUserId() != null) {
			newLtrReq.setNotifyAlrtUserId(new String(requestVO
					.getNotifyAlrtUserId()));
		}
		if (requestVO.getEdmsPageId() != null) {
			newLtrReq.setEdmsPageId(new String(requestVO.getEdmsPageId()));
		}
		if (requestVO.getReplyReceivedDate() != null) {
			newLtrReq.setReplyReceivedDate((Date) requestVO
					.getReplyReceivedDate().clone());
		}
		if (requestVO.getRevwgSprvsrWorkUnitSK() != null) {
			newLtrReq.setRevwgSprvsrWorkUnitSK(new String(requestVO
					.getRevwgSprvsrWorkUnitSK()));
		}
		if (requestVO.getSprvsrRevwDt() != null) {
			newLtrReq.setSprvsrRevwDt((Date) requestVO.getSprvsrRevwDt()
					.clone());
		}
		// This is primimitive
		newLtrReq.setSprvsrRevwReqdInd(requestVO.getSprvsrRevwReqdInd());
		/*
		 * if (requestVO.getStatus() != null) { newLtrReq.setStatus(new
		 * String(requestVO.getStatus())); }
		 */
		if (requestVO.getLtrReqDispCD() != null) {
			newLtrReq.setLtrReqDispCD(new String(requestVO.getLtrReqDispCD()));
		}
		if (requestVO.getCreatedDt() != null)
		{
			if (requestVO.getStatusDesc() != null) 
			{
				newLtrReq.setStatusDesc(new String(requestVO.getStatusDesc()));
			}
			newLtrReq.setCreatedDt(new String(requestVO.getCreatedDt()));
		}
		if (requestVO.getDueDate() != null) {
			newLtrReq.setDueDate((Date) requestVO.getDueDate().clone());
		}
		if (requestVO.getToBeSentDate() != null) {
			newLtrReq.setToBeSentDate((Date) requestVO.getToBeSentDate()
					.clone());
		}
		if (requestVO.getStrDueDate() != null) {
			newLtrReq.setStrDueDate(new String(requestVO.getStrDueDate()));
		}
		if (requestVO.getStrRcvdDate() != null) {
			newLtrReq.setStrRcvdDate(new String(requestVO.getStrRcvdDate()));
		}
		if (requestVO.getStrSentDate() != null) {
			newLtrReq.setStrSentDate(new String(requestVO.getStrSentDate()));
		}
		newLtrReq.setSupApprovalReq(requestVO.isSupApprovalReq());
		if (requestVO.getTemplateName() != null) {
			newLtrReq.setTemplateName(new String(requestVO.getTemplateName()));
		}
		if (requestVO.getTemplateId() != null) {
			newLtrReq.setTemplateId(new String(requestVO.getTemplateId()));
		}
		if (requestVO.getLetterReqSK() != null) {
			newLtrReq.setLetterReqSK(new String(requestVO.getLetterReqSK()));
		}
		if (requestVO.getReceiverName() != null) {
			newLtrReq.setReceiverName(new String(requestVO.getReceiverName()));
		}
		if (requestVO.getReturnCode() != null) {
			newLtrReq.setReturnCode(new String(requestVO.getReturnCode()));
			logger
					.debug("getClone:getReturnCode::"
							+ requestVO.getReturnCode());
		} else {
			newLtrReq.setReturnCode(null);
		}
		/*Alert issue Start*/
		if (requestVO.getAlert() != null && requestVO.getAlert().size()>0) 
		{
			newLtrReq.setAlert(requestVO.getAlert());
		}
		/*Alert issue End*/
		if (requestVO.getAuditKeyList() != null) 
		{
			newLtrReq.setAuditKeyList(requestVO.getAuditKeyList());
		}
		if (requestVO.getDisplayNotifyAlrtUserId() != null) 
		{
			newLtrReq.setDisplayNotifyAlrtUserId(requestVO.getDisplayNotifyAlrtUserId());
		}
		return newLtrReq;
	}

	/**
	 * This method accepts the department list containing DepartmentUser
	 * objects, and return the List containing SelectItem objects.
	 * 
	 * @param deptUserList
	 * @return List userList to notify
	 */
	private List getNotifyUserList(List deptUserList) {
		logger.info("Inside getNotifyUserList");       
		List notifyUserList = new ArrayList();
		Iterator iter = deptUserList.iterator();
		SelectItem slctItem = null;
		Set employeeIds = new HashSet();
		
		 StringBuffer sbName=null;//ESPRD00516792_UC-PGM-CRM-13.4_31AUG2010
		 String firstName = null;
		 String lastName = null;//EOF ESPRD00516792_UC-PGM-CRM-13.4_31AUG2010
		while (iter.hasNext()) {
			
			Object[] userData = (Object[])  iter.next();			
			slctItem = new SelectItem();
			if ((userData[0] != null)&& (!(employeeIds.contains(userData[0].toString()))))// checking for duplicate
			{
				//ESPRD00516792_UC-PGM-CRM-13.4_31AUG2010
				if(userData[2]!=null)
				{
				lastName = userData[2].toString();
				}
				if(userData[1]!=null)
				{
				firstName = userData[1].toString();
				}
				  sbName= new StringBuffer();
                  if(lastName!=null && !MaintainContactManagementUIConstants.EMPTY_STRING.equals(lastName)){
                  	sbName.append(lastName).append(",").append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
                  }
                  sbName.append(firstName).append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE).append(MaintainContactManagementUIConstants.HYPHEN);
                  sbName.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE).append(userData[0].toString());

                  slctItem.setLabel(sbName.toString());
				
                  slctItem.setValue(userData[0].toString());
                  
				
				//slctItem.setLabel(userData[0].toString());
				//slctItem.setValue(userData[0].toString());
//				EOF ESPRD00516792_UC-PGM-CRM-13.4_31AUG2010

				// adding to Set to eliminate duplicate Ids
				employeeIds.add(userData[0].toString());
				notifyUserList.add(slctItem);

			}

		}
		    Comparator comparator = new Comparator() 
		    {		    	
			public int compare(Object obj1, Object obj2) 
			{
				SelectItem slctItem1 = (SelectItem) obj1;
				SelectItem slctItem2= (SelectItem) obj2;
				String fisrtObj = null;
				String secondObj = null;
				fisrtObj = slctItem1.getLabel();
				secondObj = slctItem2.getLabel();
				if (fisrtObj == null) {
					fisrtObj = GlobalLetterConstants.EMPTY_STRING;
				}
				if (secondObj == null) {
					secondObj = GlobalLetterConstants.EMPTY_STRING;
				}
				return (fisrtObj).compareTo(secondObj);
			}
		};
		Collections.sort(notifyUserList, comparator);
		
		System.out.println("END of getNotifyUserList:::::::::");
		
		return notifyUserList;
	}

	/**
	 * Method to reset the letter request
	 * 
	 * @return String
	 */
	public String resetLetterRequestDetails() {
		logger.info("LettersAndResponsesControllerBean resetLetterRequestDetails:START");
		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		// Setting following flag to false so that everytime getInitialized ()
		// code is loaded
		lrDataBean.setSortLetterAndRespFlag(true);
		lrDataBean.setShowSucessMessage(false);
		LetterGenerationInputVO ltrGenerationInputVO = lrDataBean
				.getLetterGenerationInputVO();

		if (ltrGenerationInputVO.getDueDateOffsetNum() == null
				|| ltrGenerationInputVO.getDueDateOffsetNum().intValue()==0) {
			lrDataBean.setDuedateoffsetnumRender(false);
		} else {
			lrDataBean.setDuedateoffsetnumRender(true);

		}

		if (letterGenerationInputVOPre != null) {
			logger
					.info("------------letterGenerationInputVOPre.getExplnText()----------------"
							+ letterGenerationInputVOPre.getExplnText());
			ltrGenerationInputVO.setDueDateOffsetNum(letterGenerationInputVOPre
					.getDueDateOffsetNum());
			ltrGenerationInputVO.setStrDueDate(letterGenerationInputVOPre
					.getStrDueDate());
			ltrGenerationInputVO.setStrRcvdDate(letterGenerationInputVOPre
					.getStrRcvdDate());
			ltrGenerationInputVO.setNotifyAlrtUserId(letterGenerationInputVOPre
					.getNotifyAlrtUserId());
			ltrGenerationInputVO.setAltrBsdOnColNm(letterGenerationInputVOPre
					.getAltrBsdOnColNm());
			ltrGenerationInputVO.setSendAlertDaysCd(letterGenerationInputVOPre
					.getSendAlertDaysCd());
			ltrGenerationInputVO.setAlertBfrAftrCd(letterGenerationInputVOPre
					.getAlertBfrAftrCd());
			ltrGenerationInputVO.setExplnText(letterGenerationInputVOPre
					.getExplnText());
		}

		/*
		 * PortletSession portletSession =
		 * (PortletSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 * LetterGenerationInputVO letterGenerationVO
		 * =(LetterGenerationInputVO)portletSession.getAttribute("ltrGenInputVOTemp");
		 *  // LetterGenerationInputVO letterGenerationVO =
		 * (LetterGenerationInputVO) lrDataBean //
		 * .getLetterGenerationRequests().get( //
		 * lrDataBean.getSelectedLetterRequestRowIndex());
		 *  // logger.info ("SelectedLetterRequestRowIndex = " +
		 * lrDataBean.getSelectedLetterRequestRowIndex());
		 * logger.info("letterGenerationVO = " + letterGenerationVO);
		 * logger.info("letterGenerationVO.getExplnText() = " +
		 * letterGenerationVO.getExplnText());
		 * logger.info("letterGenerationVO.getStrDueDate() = " +
		 * letterGenerationVO.getStrDueDate());
		 * logger.info("letterGenerationVO.getNotifyAlrtUserId() = " +
		 * letterGenerationVO.getNotifyAlrtUserId()); LetterGenerationInputVO
		 * tempLetterGenerationVO = new LetterGenerationInputVO();
		 *  // It is primitive type
		 * tempLetterGenerationVO.setVersionNo(letterGenerationVO.getVersionNo());
		 * if (letterGenerationVO.getAuditUserID() != null) {
		 * tempLetterGenerationVO.setAuditUserID(new String(letterGenerationVO
		 * .getAuditUserID())); } if (letterGenerationVO.getAddedAuditUserID() !=
		 * null) { tempLetterGenerationVO.setAddedAuditUserID(new String(
		 * letterGenerationVO.getAddedAuditUserID())); } if
		 * (letterGenerationVO.getAddedAuditTimeStamp() != null) { // This will
		 * not be modified by user
		 * tempLetterGenerationVO.setAddedAuditTimeStamp(letterGenerationVO
		 * .getAddedAuditTimeStamp()); } if
		 * (letterGenerationVO.getAltrBsdOnColNm() != null) {
		 * tempLetterGenerationVO.setAltrBsdOnColNm(new String(
		 * letterGenerationVO.getAltrBsdOnColNm())); } if
		 * (letterGenerationVO.getSendAlertDaysCd() != null) {
		 * tempLetterGenerationVO.setSendAlertDaysCd(new String(
		 * letterGenerationVO.getSendAlertDaysCd())); } if
		 * (letterGenerationVO.getAlertBfrAftrCd() != null) {
		 * tempLetterGenerationVO.setAlertBfrAftrCd(new String(
		 * letterGenerationVO.getAlertBfrAftrCd())); } if
		 * (letterGenerationVO.getDueDateOffsetNum() != null) {
		 * tempLetterGenerationVO.setDueDateOffsetNum(new Integer(
		 * letterGenerationVO.getDueDateOffsetNum().toString()));
		 *  } if (letterGenerationVO.getEdmsPageURL() != null) {
		 * tempLetterGenerationVO.setEdmsPageURL(new String(letterGenerationVO
		 * .getEdmsPageURL())); } if (letterGenerationVO.getExplnText() != null) {
		 * tempLetterGenerationVO.setExplnText(new String(letterGenerationVO
		 * .getExplnText())); } if (letterGenerationVO.getLetterID() != null) {
		 * tempLetterGenerationVO.setLetterID(new String(letterGenerationVO
		 * .getLetterID())); } if (letterGenerationVO.getLtrReqDispCD() != null) {
		 * tempLetterGenerationVO.setLtrReqDispCD(new String(
		 * letterGenerationVO.getLtrReqDispCD())); } if
		 * (letterGenerationVO.getNotifyAlrtUserId() != null) {
		 * tempLetterGenerationVO.setNotifyAlrtUserId(new String(
		 * letterGenerationVO.getNotifyAlrtUserId())); } if
		 * (letterGenerationVO.getEdmsPageId() != null) {
		 * tempLetterGenerationVO.setEdmsPageId(new String(letterGenerationVO
		 * .getEdmsPageId())); } if (letterGenerationVO.getReplyReceivedDate() !=
		 * null) { tempLetterGenerationVO .setReplyReceivedDate((Date)
		 * letterGenerationVO .getReplyReceivedDate().clone()); } if
		 * (letterGenerationVO.getRevwgSprvsrWorkUnitSK() != null) {
		 * tempLetterGenerationVO.setRevwgSprvsrWorkUnitSK(new String(
		 * letterGenerationVO.getRevwgSprvsrWorkUnitSK())); } if
		 * (letterGenerationVO.getSprvsrRevwDt() != null) {
		 * tempLetterGenerationVO.setSprvsrRevwDt((Date) letterGenerationVO
		 * .getSprvsrRevwDt().clone()); } // This is primitive
		 * tempLetterGenerationVO.setSprvsrRevwReqdInd(letterGenerationVO
		 * .getSprvsrRevwReqdInd());
		 * 
		 * if (letterGenerationVO.getStatus() != null) {
		 * tempLetterGenerationVO.setStatus(new
		 * String(letterGenerationVO.getStatus())); }
		 * 
		 * if (letterGenerationVO.getLtrReqDispCD() != null) {
		 * tempLetterGenerationVO.setLtrReqDispCD(new String(
		 * letterGenerationVO.getLtrReqDispCD())); } if
		 * (letterGenerationVO.getCreatedDt() != null) if
		 * (letterGenerationVO.getStatusDesc() != null) {
		 * tempLetterGenerationVO.setStatusDesc(new String(
		 * letterGenerationVO.getStatusDesc())); } {
		 * tempLetterGenerationVO.setCreatedDt(new String(letterGenerationVO
		 * .getCreatedDt())); } if (letterGenerationVO.getDueDate() != null) {
		 * tempLetterGenerationVO.setDueDate((Date) letterGenerationVO
		 * .getDueDate().clone()); } if (letterGenerationVO.getToBeSentDate() !=
		 * null) { tempLetterGenerationVO.setToBeSentDate((Date)
		 * letterGenerationVO .getToBeSentDate().clone()); } if
		 * (letterGenerationVO.getStrDueDate() != null) {
		 * tempLetterGenerationVO.setStrDueDate(new String(letterGenerationVO
		 * .getStrDueDate())); } if (letterGenerationVO.getStrRcvdDate() !=
		 * null) { tempLetterGenerationVO.setStrRcvdDate(new
		 * String(letterGenerationVO .getStrRcvdDate())); } if
		 * (letterGenerationVO.getStrSentDate() != null) {
		 * tempLetterGenerationVO.setStrSentDate(new String(letterGenerationVO
		 * .getStrSentDate())); }
		 * tempLetterGenerationVO.setSupApprovalReq(letterGenerationVO
		 * .isSupApprovalReq()); if (letterGenerationVO.getTemplateName() !=
		 * null) { tempLetterGenerationVO.setTemplateName(new String(
		 * letterGenerationVO.getTemplateName())); } if
		 * (letterGenerationVO.getTemplateId() != null) {
		 * tempLetterGenerationVO.setTemplateId(new String(letterGenerationVO
		 * .getTemplateId())); } if (letterGenerationVO.getLetterReqSK() !=
		 * null) { tempLetterGenerationVO.setLetterReqSK(new
		 * String(letterGenerationVO .getLetterReqSK())); } if
		 * (letterGenerationVO.getReturnCode() != null) {
		 * tempLetterGenerationVO.setReturnCode(new String(letterGenerationVO
		 * .getReturnCode())); logger.debug("getClone:getReturnCode::" +
		 * letterGenerationVO.getReturnCode()); } else {
		 * tempLetterGenerationVO.setReturnCode(null); }
		 */
		if (lrDataBean.getSelectedLetterRequestRowIndex() >= 0) {
			lrDataBean
					.setLetterGenerationInputVO((LetterGenerationInputVO) lrDataBean
							.getLetterGenerationRequests()
							.get(lrDataBean.getSelectedLetterRequestRowIndex()));
		}

		lrDataBean.setShowSucessMessage(false);
		lrDataBean.setRenderEditSection(true);
		//logger.info("resetLetterRequestDetails End");
		logger.info("LettersAndResponsesControllerBean resetLetterRequestDetails:END");
		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * This method Cancel the letter request
	 * 
	 * @return String
	 */
	public String cancelLetterRequestDetails() {
		logger.info("LettersAndResponsesControllerBean cancelLetterRequestDetails:START");
		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		//for ESPRD00741245
		CMLogCaseDataBean logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setPageFocusId("caseLettersHeader");
		logCaseDataBean.setCursorFocusValue("letterRequest");
		//logger.info("INSIDE cancelLetterRequestDetails ### lrDataBean ### "
				//+ lrDataBean);
		resetLetterRequestDetails();
		lrDataBean.setRenderEditSection(false);
		lrDataBean.setShowSucessMessage(false);

		// Setting following flag to false so that everytime getInitialized ()
		// code is loaded
		lrDataBean.setSortLetterAndRespFlag(true);
		logger.info("LettersAndResponsesControllerBean cancelLetterRequestDetails:END");
		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * This method sorts the letter requests
	 * 
	 * @param event
	 *            ActionEvent
	 * @return String
	 */
	public String getAllSortedLetterRequests(ActionEvent event) {
		logger.info("getAllSortedLetterRequests");
		LettersAndResponsesDataBean dataBean = getDataBeanFromContext();

		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(GlobalLetterConstants.COLUMN_NAME);

		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(GlobalLetterConstants.SORT_ORDER);

		dataBean.setImageRender(event.getComponent().getId());
		//By default focus should come to first page after sort.
		dataBean.setLettersRowIndex(0);
		if (dataBean.getLetterGenerationRequests() != null) {
			/*
			 * Collections.sort(dataBean.getLetterGenerationRequests(), new
			 * LetterRequestComparator(sortColumn, sortOrder));
			 */
			ArrayList ltrgenVOList = (ArrayList) dataBean
					.getLetterGenerationRequests();
			Comparator comparator = new Comparator() {
				public int compare(Object obj1, Object obj2) {

					LetterGenerationInputVO ltrgenVO1 = (LetterGenerationInputVO) obj1;
					LetterGenerationInputVO ltrgenVO2 = (LetterGenerationInputVO) obj2;
					boolean ascending = false;
					if (GlobalLetterConstants.ASCENDING.equals(sortOrder)) {
						ascending = true;
					} else {
						ascending = false;
					}
					if (sortColumn == null) {
						return 0;
					}
					String fisrtObj = null;
					String secondObj = null;
					if (GlobalLetterConstants.CREATED_DT.equals(sortColumn)) {
						fisrtObj = ltrgenVO1.getCreatedDt();
						secondObj = ltrgenVO2.getCreatedDt();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}

					if (GlobalLetterConstants.TEMPLATE.equals(sortColumn)) {
						fisrtObj = ltrgenVO1.getTemplateName();
						secondObj = ltrgenVO2.getTemplateName();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}

					if (GlobalLetterConstants.DATE_SENT.equals(sortColumn)) {
						fisrtObj = ltrgenVO1.getStrSentDate();
						secondObj = ltrgenVO2.getStrSentDate();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}
					if (GlobalLetterConstants.DUE_DT.equals(sortColumn)) {

						fisrtObj = ltrgenVO1.getStrDueDate();
						secondObj = ltrgenVO2.getStrDueDate();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}

					if (GlobalLetterConstants.DATE_RECEIVED.equals(sortColumn)) {

						fisrtObj = ltrgenVO1.getStrRcvdDate();
						secondObj = ltrgenVO2.getStrRcvdDate();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}

					if (GlobalLetterConstants.NOTIFY_VIA_ALERT
							.equalsIgnoreCase(sortColumn)) {
						fisrtObj = ltrgenVO1.getNotifyAlrtUserId();
						secondObj = ltrgenVO2.getNotifyAlrtUserId();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}

					if (GlobalLetterConstants.ALERT_BASED_ON.equals(sortColumn)) {

						fisrtObj = ltrgenVO1.getAltrBsdOnColNm();
						secondObj = ltrgenVO2.getAltrBsdOnColNm();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}
					if (GlobalLetterConstants.RECIPIENT_NAME.equals(sortColumn)) {

						fisrtObj = ltrgenVO1.getReceiverName();
						secondObj = ltrgenVO2.getReceiverName();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}

					if (GlobalLetterConstants.LETER_REQ_ID.equals(sortColumn)) {

						Integer int1 = new Integer(ltrgenVO1.getLetterReqSK());
						Integer int2 = new Integer(ltrgenVO2.getLetterReqSK());

						return ascending ? (int1).compareTo(int2) : (int2
								.compareTo(int1));
					}

					if (GlobalLetterConstants.STATUS.equals(sortColumn)) {
						fisrtObj = ltrgenVO1.getStatusDesc();
						secondObj = ltrgenVO2.getStatusDesc();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}

					if (GlobalLetterConstants.SUPV_APPR_REQ.equals(sortColumn)) {
						fisrtObj = new Boolean(ltrgenVO1.isSupApprovalReq())
								.toString();
						secondObj = new Boolean(ltrgenVO2.isSupApprovalReq())
								.toString();
						if (fisrtObj == null) {
							fisrtObj = GlobalLetterConstants.EMPTY_STRING;
						}
						if (secondObj == null) {
							secondObj = GlobalLetterConstants.EMPTY_STRING;
						}

						return ascending ? (fisrtObj).compareTo(secondObj)
								: (secondObj.compareTo(fisrtObj));
					}

					return 0;
				}

			};
			Collections.sort(ltrgenVOList, comparator);
			dataBean.setLetterGenerationRequests(ltrgenVOList);
			dataBean.setSortLetterAndRespFlag(true);
		}

		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * This method save the updated letter request
	 * 
	 * @throws LetterResponseUIException
	 */
	public void saveLetterRequest() {

		logger.info("LettersAndResponsesControllerBean saveLetterRequest:START");
		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		// Setting following flag to false so that everytime getInitialized ()
		// code is loaded
		lrDataBean.setSortLetterAndRespFlag(false);
		lrDataBean.setShowSucessMessage(false);
		/*LetterGenerationInputVO ltrGenerationInputVO = lrDataBean
				.getLetterGenerationInputVO();

		if (ltrGenerationInputVO.getDueDateOffsetNum() == null
				|| ltrGenerationInputVO.getDueDateOffsetNum().intValue()==0) {
			lrDataBean.setDuedateoffsetnumRender(false);
		} else {
			lrDataBean.setDuedateoffsetnumRender(true);

		}*/

		FacesContext facesContext = FacesContext.getCurrentInstance();

		if (validate(lrDataBean.getLetterGenerationInputVO(), facesContext)) {
			LetterGenerationInputVO selectedLetterRequestVO = lrDataBean
					.getLetterGenerationInputVO();
			//List letterRequestList = lrDataBean.getLetterGenerationRequests();
			selectedLetterRequestVO.setAuditUserID(getUserIDForAudit());
			if (selectedLetterRequestVO.getDueDateOffsetNum() == null
					|| selectedLetterRequestVO.getDueDateOffsetNum().intValue()==0) {
				lrDataBean.setDuedateoffsetnumRender(false);
			} else {
				lrDataBean.setDuedateoffsetnumRender(true);

			}
			// Infinite : Code implementation for Letters and Responses Save
			// Code for Saving Record in database
			LetterGenerationRequest letterGenerationRequestDO = null;
			LetterGenerationDelegate ltrdelegate = new LetterGenerationDelegate();

			LetterGenerationInputDOConverter letterGenerationDOConverter = new LetterGenerationInputDOConverter();

			try {
				letterGenerationRequestDO = letterGenerationDOConverter
						.convertLetterRequestVOToDO(selectedLetterRequestVO);
				//EnterpriseUser tempUser = letterGenerationRequestDO
						//.getNotifyAlertUser();

				Boolean returnVal = ltrdelegate
						.updateLetterGenerationRealTime(letterGenerationRequestDO);
				logger.info("Letter Updated ??? --> " + returnVal);
				if (returnVal.booleanValue()) {
					lrDataBean.setShowSucessMessage(true);
					// Store LetterRequestSK, so that updated VersionNo will be
					// set to current LetterGenerationInputVO
					savedLetterReqSK = selectedLetterRequestVO.getLetterReqSK();
					logger.info("savedLetterReqSK = " + savedLetterReqSK);
					//getAssociatedLetterGenerationRequests();
					lrDataBean.setLetterGenerationInputVO(selectedLetterRequestVO);
				} else {
					logger.error("Failed to Update Letter");
					//					setErrorMessage(GlobalLetterConstants.LETTER_TECH_PROBLEM,
					//							null, GlobalLetterConstants.LTR_GEN_RESOURCEBUNDLE,
					//							facesContext);
				}
			} catch (Exception ex) {
				logger.info("Exception occured " + ex.getMessage());
				setErrorMessage(
						ContactManagementConstants.ERR_SW_SEVERE_FAILURE,
						new Object[] { "showErrorMessage" },
						MessageUtil.ENTMESSAGES_BUNDLE, facesContext,
						"showErrorMessage");
			}

			lrDataBean.setRenderEditSection(true);
		}
		logger.info("LettersAndResponsesControllerBean saveLetterRequest:END");	
	}

	/**
	 * This method validates the letter request
	 * 
	 * @param updatedVO
	 *            LetterGenerationInputVO
	 * @param facesContext
	 *            FacesContext
	 * @return boolean
	 */

	public boolean validate(LetterGenerationInputVO updatedVO,
			FacesContext facesContext) {
		logger.info("Inside validate method");

		boolean validationSuccess = true;
		/*
		 * Infinite Implementation for CR 1660 Business Rule LGS0021.0021.01
		 */
		if (updatedVO.getDueDateOffsetNum() != null) {
			if ((updatedVO.getDueDateOffsetNum().intValue() < GlobalLetterConstants.DUE_DT_OFFSET_MIN)
					|| (updatedVO.getDueDateOffsetNum().intValue() > GlobalLetterConstants.DUE_DT_OFFSET_MAX)) {
				setErrorMessage(
						GlobalLetterConstants.DUE_DT_OFFSET_VALID_ERROR,
						new Object[] { GlobalLetterConstants.COMPID_DAYS_DUE_BACK },
						GlobalLetterConstants.GLOBAL_LTR_ERR_MSG_BUNDLE,
						facesContext,
						GlobalLetterConstants.COMPID_DAYS_DUE_BACK);
				validationSuccess = false;
			}
		}
		/*
		 * Infinite Implementation for CR 1660 Business Rule LGS0022.0022.01
		 */

		// Following BR is removed as part of CR 409262
		/*if (updatedVO.getAlertBfrAftrCd() != null
				&& (updatedVO.getAlertBfrAftrCd().equals(
						GlobalLetterConstants.ALERT_AFTER_BEFORE_CD_B) || updatedVO
						.getAlertBfrAftrCd().equals(
								GlobalLetterConstants.ALERT_AFTER_BEFORE_CD_A))) {

			if (!(updatedVO.getSendAlertDaysCd()
					.equals(GlobalLetterConstants.EMPTY_STRING))
					&& ((Integer.parseInt(updatedVO.getSendAlertDaysCd()) < GlobalLetterConstants.DUE_DT_OFFSET_MIN) || Integer
							.parseInt(updatedVO.getSendAlertDaysCd()) > GlobalLetterConstants.DUE_DT_OFFSET_MAX)) {

				setErrorMessage(
						GlobalLetterConstants.SEND_ALERT_NUM_DAYS_INVALID_ERROR,
						new Object[] { GlobalLetterConstants.COMPID_SEND_ALERT_DAYS },
						GlobalLetterConstants.GLOBAL_LTR_ERR_MSG_BUNDLE,
						facesContext,
						GlobalLetterConstants.COMPID_SEND_ALERT_DAYS);
				validationSuccess = false;
			}
		}*/

		if (updatedVO.getNotifyAlrtUserId() != null
				&& (updatedVO.getAltrBsdOnColNm() == null || updatedVO
						.getAltrBsdOnColNm().trim().equals(
								ContactManagementConstants.EMPTY_STRING)))

		{
			setErrorMessage(GlobalLetterConstants.LTR_REQ_FIELD,
					new Object[] { GlobalLetterConstants.ALERT_BASED_ON },
					GlobalLetterConstants.GLOBAL_LTR_ERR_MSG_BUNDLE,
					facesContext, GlobalLetterConstants.COMPID_ALERT_BASED_ON);
			validationSuccess = false;
		}
		
		if (updatedVO.getNotifyAlrtUserId() != null
				&& (updatedVO.getAlertBfrAftrCd() == null || updatedVO.getAlertBfrAftrCd().trim().equals(
								ContactManagementConstants.EMPTY_STRING)))

		{
			logger.debug("Inside getAlertBfrAftrCd:::"+updatedVO.getAlertBfrAftrCd());
			setErrorMessage(GlobalLetterConstants.LTR_REQ_FIELD,
					new Object[] { GlobalLetterConstants.BEFORE_AFTER_RADIO},
					GlobalLetterConstants.GLOBAL_LTR_ERR_MSG_BUNDLE,
					facesContext, GlobalLetterConstants.COMPID_BEFORE_AFTER_RADIO);
			
			validationSuccess = false;
		}

		if (updatedVO.getNotifyAlrtUserId() != null
				&& (updatedVO.getSendAlertDaysCd() == null || updatedVO
						.getSendAlertDaysCd().trim().equals(
								ContactManagementConstants.EMPTY_STRING))) {
			setErrorMessage(GlobalLetterConstants.LTR_REQ_FIELD,
					new Object[] { GlobalLetterConstants.SEND_ALERT_NUM_DAYS },
					GlobalLetterConstants.GLOBAL_LTR_ERR_MSG_BUNDLE,
					facesContext,
					GlobalLetterConstants.COMPID_SEND_ALERT_DAYS);
			validationSuccess = false;
		}

		if (updatedVO.getStrRcvdDate() != null
				&& !updatedVO.getStrRcvdDate().trim().equals(
						ContactManagementConstants.EMPTY_STRING)
				&& !(EnterpriseCommonValidator.validateDate(updatedVO
						.getStrRcvdDate()))) {
			setErrorMessage(GlobalLetterConstants.RCVD_DT_INVALID,
					new Object[] { GlobalLetterConstants.SNT_DT_ID },
					GlobalLetterConstants.COMMON_LTR_ERR_MSG_BUNDLE,
					facesContext, GlobalLetterConstants.SNT_DT_ID);
			validationSuccess = false;
		}
		/*
		 * Infinite Implementation for CR 1660 Business Rule LGS0003.0003.01 and
		 * Error Message : 9-8050-0008
		 */
		if (updatedVO.getStrRcvdDate() != null
				&& updatedVO.getStrSentDate() != null) {

			// Date Received and Date Sent should not be BLANK
			if (!updatedVO.getStrRcvdDate().trim().equals(
					ContactManagementConstants.EMPTY_STRING)
					&& !updatedVO.getStrSentDate().trim().equals(
							ContactManagementConstants.EMPTY_STRING)) {

				boolean receivedDateGreaterFlag = EnterpriseCommonValidator
						.compareGreaterDate(
								new Date(updatedVO.getStrRcvdDate()), new Date(
										updatedVO.getStrSentDate()));
				logger
						.debug("receivedDate greater::"
								+ receivedDateGreaterFlag);
				if (!receivedDateGreaterFlag) {
					setErrorMessage(
							GlobalLetterConstants.RCVD_DT_CANNOT_PROIR_SENT_DATE,
							new Object[] { GlobalLetterConstants.SNT_DT_ID },
							GlobalLetterConstants.COMMON_LTR_ERR_MSG_BUNDLE,
							facesContext, GlobalLetterConstants.SNT_DT_ID);
					validationSuccess = false;
				}
			}
		}

		return validationSuccess;

	}

	/**
	 * This method finds the selected editing letter request from the list
	 * 
	 * @param letterRequestList
	 *            List
	 * @param letterId
	 *            String
	 * @return LetterGenerationInputVO editing letter request
	 */
	private LetterGenerationInputVO findEditedLetterRequest(
			List letterRequestList, String letterReqSK) {
		logger.info("findSelectedTemplate");

		LetterGenerationInputVO slctLetterRequest = null;
		Iterator iter;
		boolean matchFound = false;
		if (letterRequestList != null) {
			iter = letterRequestList.iterator();
			while (iter.hasNext()) {
				slctLetterRequest = (LetterGenerationInputVO) iter.next();
				/*
				 * if (slctLetterRequest.getLetterReqSK().equals(letterReqSK)) {
				 * logger.info("Selected letterId " + letterReqSK); matchFound =
				 * true; break; }
				 */
			}

		}
		// null, if not able find selected letter
		return (matchFound) ? slctLetterRequest : null;

	}

	/**
	 * @author WIPRO This class implements Comparator interface for Letter
	 *         sorting functionaltiy
	 */
	private class LetterRequestComparator implements Comparator {

		private transient String sortColumn = null;

		private transient String sortOrder = null;

		LetterRequestComparator(String sortColumn, String sortOrder) {
			logger.info("TemplateComparator");
			this.sortColumn = sortColumn;
			this.sortOrder = sortOrder;

		}

		public int compare(Object obj1, Object obj2) {

			LetterGenerationInputVO letterGenerationInputVO1 = (LetterGenerationInputVO) obj1;
			LetterGenerationInputVO letterGenerationInputVO2 = (LetterGenerationInputVO) obj2;

			boolean ascending = false;
			int returnValue = 0;

			ascending = (GlobalLetterConstants.ASCENDING
					.equalsIgnoreCase(sortOrder)) ? true : false;

			if (GlobalLetterConstants.CREATED_DT.equalsIgnoreCase(sortColumn)) {
				Date date1 = letterGenerationInputVO1.getAddedAuditTimeStamp();
				Date date2 = letterGenerationInputVO2.getAddedAuditTimeStamp();

				returnValue = ascending ? date1.compareTo(date2) : date2
						.compareTo(date1);

			} else if (GlobalLetterConstants.TEMPLATE
					.equalsIgnoreCase(sortColumn)) {

				/*
				 * String str1 = letterGenerationInputVO1.getTemplateName();
				 * String str2 = letterGenerationInputVO2.getTemplateName();
				 * 
				 * if (str1 == null) { str1 =
				 * GlobalLetterConstants.EMPTY_STRING; } else { str1 =
				 * str1.trim(); }
				 * 
				 * if (str2 == null) { str2 =
				 * GlobalLetterConstants.EMPTY_STRING; } else { str2 =
				 * str2.trim(); }
				 * 
				 * returnValue = ascending ? str1.compareTo(str2) : str2
				 * .compareTo(str1);
				 */

			} else if (GlobalLetterConstants.DATE_SENT
					.equalsIgnoreCase(sortColumn)) {
				Date date1 = letterGenerationInputVO1.getToBeSentDate();
				Date date2 = letterGenerationInputVO2.getToBeSentDate();

				returnValue = ascending ? date1.compareTo(date2) : date2
						.compareTo(date1);

			} else if (GlobalLetterConstants.DUE_DT
					.equalsIgnoreCase(sortColumn)) {
				/*
				 * Date date1 = letterGenerationInputVO1.getDueDate(); Date
				 * date2 = letterGenerationInputVO2.getDueDate();
				 * 
				 * returnValue = ascending ? date1.compareTo(date2) : date2
				 * .compareTo(date1);
				 */

			} else if (GlobalLetterConstants.DATE_RECEIVED
					.equalsIgnoreCase(sortColumn)) {
				/*
				 * Date date1 = letterGenerationInputVO1.getReplyReceivedDate();
				 * Date date2 = letterGenerationInputVO2.getReplyReceivedDate();
				 * 
				 * returnValue = ascending ? date1.compareTo(date2) : date2
				 * .compareTo(date1);
				 */

			}

			else if (GlobalLetterConstants.NOTIFY_VIA_ALERT
					.equalsIgnoreCase(sortColumn)) {
				/*
				 * String str1 = letterGenerationInputVO1.getNotifyAlrtUserId();
				 * String str2 = letterGenerationInputVO2.getNotifyAlrtUserId();
				 * if (str1 == null) { str1 =
				 * GlobalLetterConstants.EMPTY_STRING; } else { str1 =
				 * str1.trim(); }
				 * 
				 * if (str2 == null) { str2 =
				 * GlobalLetterConstants.EMPTY_STRING; } else { str2 =
				 * str2.trim(); }
				 * 
				 * returnValue = ascending ? str1.compareTo(str2) : str2
				 * .compareTo(str1);
				 */
			} else if (GlobalLetterConstants.ALERT_BASED_ON
					.equalsIgnoreCase(sortColumn)) {
				/*
				 * String str1 = letterGenerationInputVO1.getAltrBsdOnColNm();
				 * String str2 = letterGenerationInputVO2.getAltrBsdOnColNm();
				 * if (str1 == null) { str1 =
				 * GlobalLetterConstants.EMPTY_STRING; } else { str1 =
				 * str1.trim(); }
				 * 
				 * if (str2 == null) { str2 =
				 * GlobalLetterConstants.EMPTY_STRING; } else { str2 =
				 * str2.trim(); }
				 * 
				 * returnValue = ascending ? str1.compareTo(str2) : str2
				 * .compareTo(str1);
				 */
			} else if (GlobalLetterConstants.ALERT_NUMBER_OF_DAYS
					.equalsIgnoreCase(sortColumn)) {
				/*
				 * String str1 = letterGenerationInputVO1.getSendAlertDaysCd();
				 * String str2 = letterGenerationInputVO2.getSendAlertDaysCd();
				 * if (str1 == null) { str1 =
				 * GlobalLetterConstants.EMPTY_STRING; } if (str2 == null) {
				 * str2 = GlobalLetterConstants.EMPTY_STRING; }
				 * 
				 * returnValue = ascending ? str1.compareTo(str2) : str2
				 * .compareTo(str1);
				 */
			}

			else if (GlobalLetterConstants.EXPLANATION
					.equalsIgnoreCase(sortColumn)) {
				String str1 = letterGenerationInputVO1.getExplnText();
				String str2 = letterGenerationInputVO2.getExplnText();
				if (str1 == null) {
					str1 = GlobalLetterConstants.EMPTY_STRING;
				} else {
					str1 = str1.trim();
				}

				if (str2 == null) {
					str2 = GlobalLetterConstants.EMPTY_STRING;
				} else {
					str2 = str2.trim();
				}

				returnValue = ascending ? str1.compareTo(str2) : str2
						.compareTo(str1);
			} else if (GlobalLetterConstants.STATUS
					.equalsIgnoreCase(sortColumn)) {
				/*
				 * String str1 = letterGenerationInputVO1.getStatus(); String
				 * str2 = letterGenerationInputVO2.getStatus(); if (str1 ==
				 * null) { str1 = GlobalLetterConstants.EMPTY_STRING; } else {
				 * str1 = str1.trim(); }
				 * 
				 * if (str2 == null) { str2 =
				 * GlobalLetterConstants.EMPTY_STRING; } else { str2 =
				 * str2.trim(); }
				 * 
				 * returnValue = ascending ? str1.compareTo(str2) : str2
				 * .compareTo(str1);
				 */
			} else if (GlobalLetterConstants.SUPV_APPR_REQ
					.equalsIgnoreCase(sortColumn)) {
				boolean val1 = letterGenerationInputVO1.getSprvsrRevwReqdInd();
				boolean val2 = letterGenerationInputVO2.getSprvsrRevwReqdInd();

				returnValue = ascending ? (val1 ? -1 : +1) : (val2 ? -1 : +1);// false="N";true="Y"
			}

			return returnValue;
		}
	}

	/**
	 * This method gets selected letter Request object
	 * 
	 * @param lrDataBean
	 * @return LetterGenerationInputVO
	 */
	private LetterGenerationInputVO getSelectedLetterRequestVO(
			LettersAndResponsesDataBean lrDataBean) {
		logger.info("LettersAndResponsesControllerBean getSelectedLetterRequestVO:START");
		LetterGenerationInputVO selectedLetterRequestVO = null;
		//logger.info("index is: "
				//+ lrDataBean.getSelectedLetterRequestRowIndex());
		int rowIndex = lrDataBean.getSelectedLetterRequestRowIndex();
		logger.info("row index -->" + rowIndex);
		//logger.info("List Size: "
				//+ lrDataBean.getLetterGenerationRequests().size());
		//logger.debug("lrDataBean Date Received = "
				//+ ((LetterGenerationInputVO) lrDataBean
						//.getLetterGenerationRequests().get(
						//		lrDataBean.getSelectedLetterRequestRowIndex()))
						//.getStrRcvdDate());
		/*if (selectedLetterRequestVO != null) {
			logger.debug("selectedLetterRequestVO is NULL");
		}*/
		selectedLetterRequestVO = (LetterGenerationInputVO) lrDataBean
				.getLetterGenerationRequests().get(rowIndex);
		//logger.info("selectedLetterRequestVO.getCreatedDt () "
				//+ selectedLetterRequestVO.getCreatedDt());
		//logger.info("selectedLetterRequestVO.getExplnText () "
				//+ selectedLetterRequestVO.getExplnText());
		logger.info("LettersAndResponsesControllerBean getSelectedLetterRequestVO:END");
		return selectedLetterRequestVO;
	}

	/**
	 * This method invokes the Common Letter UI
	 */
	public void viewLetterRequest() {
		logger.info("LettersAndResponsesControllerBean viewLetterRequest:START");
		//CommonLetterInputVO inputVO = new CommonLetterInputVO();
		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		// Setting following flag to false so that everytime getInitialized ()
		// code is loaded
		lrDataBean.setSortLetterAndRespFlag(false);
		// Commented following VO/POJO object as part of heap dump activity - Remove POJO objects
		LetterGenerationInputVO selectedLtrReqVO = getSelectedLetterRequestVO(lrDataBean);

		/* Populating VO object to pass the data to Common Letter UI */
	    /*inputVO.setFunctionalAreaCode(lrDataBean.getFuncArea());
		inputVO.setFunctionalAreaSK(lrDataBean.getFuncSK().toString());
		inputVO.setLetterCategory(lrDataBean.getLetterCategory());
		logger.info("Func Area Code : " + inputVO.getFunctionalAreaCode());
		logger.info("Func Area SK : " + inputVO.getFunctionalAreaSK());
		logger.info("Letter Category : " + inputVO.getLetterCategory());*/

		String lettersk = selectedLtrReqVO.getLetterReqSK();
		 /*inputVO.setLetterGenerationRequestSK(Long.valueOf(selectedLtrReqVO
		  .getLetterReqSK()));*/
		 System.out.println("lettersk==========="+lettersk);

		/*FacesContext facesContext = FacesContext.getCurrentInstance();
		Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding("#{requestScope}").getValue(facesContext);
		 requestScope.put("letterRequestSK",lettersk);*/
		 
		 ActionRequest request = (ActionRequest) FacesContext
			.getCurrentInstance().getExternalContext().getRequest();
		 request.setAttribute("letterRequestSK",lettersk);
		lrDataBean.setRenderEditSection(false);
		logger.info("LettersAndResponsesControllerBean viewLetterRequest:END");
	}

	/**
	 * This method will get userid from Security.
	 * 
	 * @return String
	 */
	public String getUserIDForAudit() {
		logger.info("getUserIDForAudit");

		String userId = "UNIT_TEST";

		HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderResponse = null;

		EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
		if (eup != null && eup.getUserId() != null) {
			userId = eup.getUserId();
		}

		return userId;
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
	 */
	private void setErrorMessage(String errorName, Object[] arguments,
			String messageBundle, FacesContext facesContext) {
		logger.info("setErrorMessage");

		UIViewRoot root = facesContext.getViewRoot();
		FacesMessage fc = new FacesMessage();
		String clientId = null;
		Locale locale = root.getLocale();

		facesContext.getApplication().setMessageBundle(messageBundle);
		String errorMsg = MessageUtil.format(messageBundle, errorName,
				arguments, locale);

		fc.setDetail(errorMsg);

		facesContext.addMessage(clientId, fc);
	}

	/**
	 * This method get FilterDataBean object.
	 * 
	 * @return LettersAndResponsesDataBean.
	 */
	public final LettersAndResponsesDataBean getDataBeanFromContext() {

		logger.info("Inside getDataBeanFromContext");
		FacesContext fc = FacesContext.getCurrentInstance();
		LettersAndResponsesDataBean letterTemplateDataBean = (LettersAndResponsesDataBean) fc
				.getApplication().createValueBinding(
						"#{" + GlobalLetterConstants.LETTER_RESPONSES_DATA_BEAN
								+ "}").getValue(fc);
		return letterTemplateDataBean;
	}

	/**
	 * This method will populate all reference data required in Common Letter UI
	 * page.
	 * 
	 * @param dataBean
	 * @throws LetterResponseUIException
	 */
	public void populateReferenceData(LettersAndResponsesDataBean dataBean)
			throws LetterResponseUIException {
		logger.info("Inside populateReferenceData");
		List inputCriteriaList = new ArrayList();

		try {
			Map referenceDataInputValuesMap = this
					.getReferenceDataInputValues();
			Iterator iter = referenceDataInputValuesMap.keySet().iterator();

			ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();

			ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();

			while (iter.hasNext()) {
				String key = (String) iter.next();
				String elementName = (String) referenceDataInputValuesMap
						.get(key);

				String funcArea = key.split("#")[0];

				String listNumber = key.split("#")[2];
				logger.debug("Key = " + key + "#funcArea = " + funcArea);
				logger.debug("#elementName = " + elementName + "#listNumber = " + listNumber);
				inputCriteriaList.add(createInputCriteria(funcArea,
						elementName, listNumber));

			}

			referenceDataSearch.setInputList(inputCriteriaList);

			ReferenceDataListVO referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			Map referenceDataResponseMap = referenceDataListVO.getResponseMap();
			// Populate databean with reference values using the above response
			// map.
			this.populateDataBeanWithReferenceValues(referenceDataResponseMap,
					dataBean);
		} catch (ReferenceServiceRequestException ex) {
			logger
					.error("ReferenceServiceRequestException occured in populateReferenceData() method."
							+ ex);
			throw new LetterResponseUIException(ex.getErrorCode(), ex
					.getMessage());

		} catch (SystemListNotFoundException ex) {

			logger
					.error("SystemListNotFoundException occured in populateReferenceData() method."
							+ ex.getMessage());
			throw new LetterResponseUIException(ex.getErrorCode(), ex
					.getMessage());

		}
		logger.debug("dataBean.getAlertBasedOn() = " + dataBean.getAlertBasedOn());
		if (dataBean.getAlertBasedOn() == null) {
			dataBean.setAlertBasedOn(new ArrayList ());
		}
		logger.debug("dataBean.getAlertNumberOfDays() = " + dataBean.getAlertNumberOfDays());
		if (dataBean.getAlertNumberOfDays() == null) {
			dataBean.setAlertNumberOfDays(new ArrayList ());
		}

	}

	/**
	 * Populate data bean with reference values from the reponse map.
	 * 
	 * @param referenceDataResponseMap
	 * @param dataBean
	 */
	private void populateDataBeanWithReferenceValues(
			Map referenceDataResponseMap, LettersAndResponsesDataBean dataBean) {
		logger.info("Inside populateDataBeanWithReferenceValues");

		// Get reference data for Alert num days

		List referenceServiceVOList = (List) referenceDataResponseMap
				.get(FunctionalAreaConstants.CONTACT_MGMT + "#"
						+ GlobalLetterConstants.ALERT_NUM_OF_DAYS_SYSID_NEW);
		logger.debug("ALERT_NUM_OF_DAYS_SYSID_NEW VOList = " + referenceServiceVOList);

		List referenceDataAsSelectItems = this
				.getReferenceDataAsSelectItems(referenceServiceVOList);
		logger.debug("ALERT_NUM_OF_DAYS_SYSID_NEW Items = " + referenceDataAsSelectItems);
		if (referenceDataAsSelectItems == null) {
			referenceDataAsSelectItems = new ArrayList();
		}
		dataBean.setAlertNumberOfDays(referenceDataAsSelectItems);

		// Get reference data for Alert Based on
		referenceServiceVOList = (List) referenceDataResponseMap
				.get(FunctionalAreaConstants.CONTACT_MGMT + "#"
						+ GlobalLetterConstants.ALERT_BASED_ON_SYSID);
		logger.debug("ALERT_BASED_ON_SYSID VOList = " + referenceServiceVOList);

		referenceDataAsSelectItems = this.getReferenceDataAsSelectItems(
				referenceServiceVOList, true);
		logger.debug("ALERT_BASED_ON_SYSID Items = " + referenceDataAsSelectItems);
		if (referenceDataAsSelectItems == null) {
			referenceDataAsSelectItems = new ArrayList();
		}

		dataBean.setAlertBasedOn(referenceDataAsSelectItems);
	}

	/**
	 * @param referenceServiceVOList
	 * @return List
	 */
	private List getReferenceDataAsSelectItems(List referenceServiceVOList) {
		logger.info("Inside getReferenceDataAsSelectItems");

		List referenceDataAsSelectItems = new ArrayList();
		if (referenceServiceVOList != null) {
			sortSendAlertDays(referenceServiceVOList);
			int referenceServiceVOListSize = referenceServiceVOList.size();
			for (int i = 0; i < referenceServiceVOListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceServiceVOList
						.get(i);
				referenceDataAsSelectItems.add(new SelectItem(refVo
						.getValidValueCode(), refVo.getShortDescription()));

			}
		}
		return referenceDataAsSelectItems;
	}

	/**
	 * @param referenceServiceVOList
	 * @return List
	 */
	private List getReferenceDataAsSelectItems(List referenceServiceVOList,
			boolean showLongDesc) {
		logger.info("Inside getReferenceDataAsSelectItems");
		List referenceDataAsSelectItems = new ArrayList();
		String desc = null;//ESPRD00516800_UC-PGM-CRM-013.4_8sep2010
		if (referenceServiceVOList != null) {
			int referenceServiceVOListSize = referenceServiceVOList.size();
			for (int i = 0; i < referenceServiceVOListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceServiceVOList
						.get(i);
				logger.info("ValidValueCode = " + refVo.getValidValueCode());
				logger.info("ShortDescription = " + refVo.getShortDescription());
				logger.info("LongDescription = " + refVo.getLongDescription());
				if (showLongDesc) {
					//ESPRD00516800_UC-PGM-CRM-013.4_8sep2010
					desc = refVo.getValidValueCode() + "-" + refVo.getLongDescription();
					referenceDataAsSelectItems.add(new SelectItem(refVo
							.getValidValueCode(), desc));  //refVo.getLongDescription()));
					//EOF ESPRD00516800_UC-PGM-CRM-013.4_8sep2010
				} else {
					//ESPRD00516800_UC-PGM-CRM-013.4_8sep2010
					desc = refVo.getValidValueCode() + "-" + refVo.getShortDescription();
					referenceDataAsSelectItems.add(new SelectItem(refVo
							.getValidValueCode(), desc));  // refVo.getShortDescription()));
					//EOF ESPRD00516800_UC-PGM-CRM-013.4_8sep2010
				}

			}
		}
		return referenceDataAsSelectItems;
	}

	/**
	 * This method gets the Map for system list request
	 * 
	 * @return Map
	 */
	private Map getReferenceDataInputValues() {
		logger.info("Inside getReferenceDataInputValues");
		Map referenceDataInputValuesMap = new HashMap();

		referenceDataInputValuesMap.put(FunctionalAreaConstants.CONTACT_MGMT
				+ "#" + ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD
				+ "#" + GlobalLetterConstants.ALERT_NUM_OF_DAYS_SYSID_NEW,
				ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD);

		referenceDataInputValuesMap.put(FunctionalAreaConstants.CONTACT_MGMT
				+ "#" + GlobalLetterConstants.G_ALERT_BASED_ON_COL_NAM + "#"
				+ GlobalLetterConstants.ALERT_BASED_ON_SYSID,
				GlobalLetterConstants.G_ALERT_BASED_ON_COL_NAM);
		
		logger.debug("Key = " + FunctionalAreaConstants.CONTACT_MGMT
				+ "#" + ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD
				+ "#" + GlobalLetterConstants.ALERT_NUM_OF_DAYS_SYSID_NEW
				+ " $Value = " + ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD);
		logger.debug("Key = " + FunctionalAreaConstants.CONTACT_MGMT
				+ "#" + GlobalLetterConstants.G_ALERT_BASED_ON_COL_NAM + "#"
				+ GlobalLetterConstants.ALERT_BASED_ON_SYSID
				+ " $Value = " + GlobalLetterConstants.G_ALERT_BASED_ON_COL_NAM);

		return referenceDataInputValuesMap;
	}

	/**
	 * Creates input criteria based on the params
	 * 
	 * @param funcArea
	 * @param elementName
	 * @param listNumber
	 * @return InputCriteria
	 */
	private InputCriteria createInputCriteria(String funcArea,
			String elementName, String listNumber) {
		logger.info("Inside createInputCriteria");
		InputCriteria inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(funcArea);
		inputCriteria.setElementName(elementName);
		inputCriteria.setListNumber(Long.valueOf(listNumber));
		return inputCriteria;
	}

	/**
	 * This method gets request parameter from invoking module
	 * 
	 * @param requestParam
	 * @return String
	 */
	private String getReqParamFromCtx(String requestParam) {
		String param = null;
		logger.info("Inside getReqParamFromCtx");

		param = (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(requestParam);

		return param;
	}

	/**
	 * For storing LtrReqSk once VO saved
	 */
	private String savedLetterReqSK = null;

	/**
	 * This method is used to invoke converter and populate user departments
	 * 
	 * @param letterRequestList
	 * @param lrDataBean
	 * @return List
	 */
	public List convertLetterRequestDOToVO(List letterRequestList,
			LettersAndResponsesDataBean lrDataBean) {
		logger.info("Inside convertLetterRequestDOToVO List ");
		LetterGenerationInputDOConverter letterGenerationDOConverter = new LetterGenerationInputDOConverter();
		Iterator iter = null;
		//below line is fixed for find bug because it is not used in the class
     	//	List deptList = new ArrayList();
		List letterRequestsVOList = new ArrayList();
		LetterGenerationInputVO letterGenerationInputVO = null;
		LetterGenerationRequest letterRequest = null;
		if (letterRequestList != null) {
			iter = letterRequestList.iterator();
			while (iter.hasNext()) {
				letterRequest = (LetterGenerationRequest) iter.next();

				logger.debug("letterRequest.getDueDateOffsetNumber() = "
						+ letterRequest.getDueDateOffsetNumber());
				letterGenerationInputVO = letterGenerationDOConverter
						.convertLetterRequestDOToVO(letterRequest);
//	modified for defect			Test 516792
				StringBuffer sbName = new StringBuffer();
				String lastName = "";
				String firstName= "";
				if(letterRequest !=null){
					logger.info("NotifyAlertUser :"+ letterRequest.getNotifyAlertUser());
				}
				if(letterRequest.getNotifyAlertUser()!=null && letterRequest.getNotifyAlertUser().getLastName()!=null){
					 lastName = letterRequest.getNotifyAlertUser().getLastName();
				}
				if(letterRequest.getNotifyAlertUser()!=null && letterRequest.getNotifyAlertUser().getFirstName()!=null){
					 firstName = letterRequest.getNotifyAlertUser().getFirstName();
				}
				
				 if(lastName!=null && !MaintainContactManagementUIConstants.EMPTY_STRING.equals(lastName)){

                         	sbName.append(lastName).append(",").append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
				 }
				
                if(letterRequest.getNotifyAlertUser() !=null && letterRequest.getNotifyAlertUser().getUserID()!=null){
                	sbName.append(firstName).append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE).append(MaintainContactManagementUIConstants.HYPHEN);

                sbName.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE).append(letterRequest.getNotifyAlertUser().getUserID());
                }
				logger.info("sbName::is::"+sbName);
                letterGenerationInputVO.setDisplayNotifyAlrtUserId(sbName.toString());
                
                //Test 516792
				createVOAuditKeysList(letterRequest,letterGenerationInputVO);
				//				 Following code as Solution for StaleObjectException, updating
				// VersionNo
				logger.info("convertLetterRequestDOToVO#VersionNo = "
						+ letterGenerationInputVO.getVersionNo());
				logger.info("convertLetterRequestDOToVO#LetterReqSK = "
						+ letterGenerationInputVO.getLetterReqSK());
				if (savedLetterReqSK != null) {
					if (lrDataBean.getLetterGenerationInputVO() != null
							&& lrDataBean.getLetterGenerationInputVO()
									.getLetterReqSK() != null) {
						logger.info("savedLetterReqSK = " + savedLetterReqSK);
						logger.info("lrDataBean.getLetterReqSK = "
								+ lrDataBean.getLetterGenerationInputVO()
										.getLetterReqSK());
						logger.info("lrDataBean.getVersionNo = "
								+ lrDataBean.getLetterGenerationInputVO()
										.getVersionNo());
						if (savedLetterReqSK
								.equalsIgnoreCase(letterGenerationInputVO
										.getLetterReqSK())) {
							logger.info("BEFORE#getVersionNo"
									+ lrDataBean.getLetterGenerationInputVO()
											.getVersionNo());
							lrDataBean.getLetterGenerationInputVO()
									.setVersionNo(
											letterGenerationInputVO
													.getVersionNo());
							logger.info("AFTER#getVersionNo"
									+ lrDataBean.getLetterGenerationInputVO()
											.getVersionNo());
							// Set to null to avoid executing this block again
							// till next save
							savedLetterReqSK = null;
						}
					}
				}
				logger.info("letterGenerationInputVO.getReturnCode "
						+ letterGenerationInputVO.getReturnCode());
				logger.info("letterGenerationInputVO.getLtrReqDispCD "
						+ letterGenerationInputVO.getLtrReqDispCD());

				logger.info("LetterGenerationINputVO. getUSerID"
						+ letterGenerationInputVO.getUserID());
				logger.info("LetterGenerationINputVO. getUserWorkUnitSK"
						+ letterGenerationInputVO.getUserWorkUnitSK());
				// For displaying Supervisor Approval Reqd value in datatable of
				// Letters and Responses
				logger.info("Sup Appr Reqd = "
						+ letterGenerationInputVO.getSprvsrRevwReqdInd());
				if (letterGenerationInputVO.getSprvsrRevwReqdInd()) {
					letterGenerationInputVO.setSupApprovalReq(true);
				} else {
					letterGenerationInputVO.setSupApprovalReq(false);
				}
				letterRequestsVOList.add(letterGenerationInputVO);

				/*
				 * Getting user departments for a Letter and storing in
				 * HashTable
				 */

				/*if (letterRequest.getUser() != null
						&& letterRequest.getUser().getDepartmentUsers() != null) {
					Set deptUsers = letterRequest.getUser()
							.getDepartmentUsers();
					deptList = letterGenerationDOConverter
							.convertLetterRequestDeptUsersDOToVO(deptUsers);
					lrDataBean.getUserDepartments().put(
							GlobalLetterConstants.EMPTY_STRING
									+ letterRequest.getLetterRequestSK(),
							deptList);
				}*/
			}
		}

		return letterRequestsVOList;
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
	private void setErrorMessage(String errorName, Object[] arguments,
			String messageBundle, FacesContext facesContext, String componentId) {
		logger.info("setErrorMessage");

		//        FacesContext facesContext = FacesContext.getCurrentInstance();
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
		logger.info("findComponent");

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
		logger.info("findComponentInRoot");

		UIComponent component = null;
		FacesContext context = FacesContext.getCurrentInstance();

		if (context != null) {
			UIComponent root = context.getViewRoot();
			component = findComponent(root, id);
		}

		return component;
	}

	/**
	 * Below variable declared for AJAX Page Refresh implemetation in
	 * inc_lettersAndResponses.jsp
	 */
	private String addDate = "";

	/**
	 * @return Returns the addDate.
	 */
	public String getAddDate() {

		Map map = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();

		if (map != null && !(map.isEmpty()) && map.get("daysDueBack") != null) {
			try {
				LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
				LetterGenerationInputVO ltrGenerationInputVO = lrDataBean
						.getLetterGenerationInputVO();
				String dateSent = ltrGenerationInputVO.getStrSentDate();
				String dueDateOffsetNumber = (String) map.get("daysDueBack");
				Integer i = new Integer(dueDateOffsetNumber);
				int dueDateOffsetNum = i.intValue();

				if (dateSent != null) {
					String DueDate = addDaysToDate(dateSent, dueDateOffsetNum);

					ltrGenerationInputVO.setStrDueDate(DueDate);
				}
			} catch (Exception e) {
				logger.info("Exception is:::;" + e.getMessage());
			}
		}
		return addDate;
	}

	/**
	 * @param addDate
	 *            The addDate to set.
	 */
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	private String addDaysToDate(String date, int daysToAdd) throws Exception {
		Date todayDate = new Date();
		DateFormat sdf = new SimpleDateFormat(ContactManagementConstants.CONTCT_MGMT_DATE_FORMAT);
		String strDate = sdf.format(todayDate);
		Date parsedDate = sdf.parse(date);

		Calendar now = Calendar.getInstance();
		now.setTime(parsedDate);
		now.add(Calendar.DAY_OF_MONTH, daysToAdd);

		int day = now.get(Calendar.DATE);
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		String dueDate = "";
		if (day <= 9)
			dueDate = (month + "/" + ("0" + day) + "/" + year);
		if (month <= 9)
			dueDate = (("0" + month) + "/" + day + "/" + year);
		if (day >= 10 || month >= 10)
			dueDate = (month + "/" + day + "/" + year);

		return dueDate;
	}
	
	/**
	 * This field used to call all method available inside constructor 
	 */
	private String loadConstructorData = "success";

	/**
	 * @return Returns the loadConstructorData.
	 */
	public String getLoadConstructorData() {
		logger.info("Inside loadConstructorData()");
		//getUserPermission();
		LettersAndResponsesDataBean dataBean = getDataBeanFromContext();
		
		CorrespondenceDataBean crDataBean = new CorrespondenceControllerBean ().getCorrespondenceDataBean();
		CMLogCaseDataBean caseDataBean = (CMLogCaseDataBean) new CMLogCaseControllerBean ().getDataBean("logCaseDataBean");
		logger.info("SortLetterAndRespFlag = " + dataBean.isSortLetterAndRespFlag());
		logger.info("CR Audit Flag = " + crDataBean.isAuditLogFlag());
		logger.info("Case Audit Flag = " + caseDataBean.isEnableAuditLogForLogCase());
		
		// Below if condition added as part of defect ESPRD00796178, common code for both CRN & Case
		if (dataBean.getViewLetterReq() != null && !dataBean.getViewLetterReq().trim().equals(
				MaintainContactManagementUIConstants.EMPTY_STRING)) {
			dataBean.setRenderEditSection(true);
		}
		
		/* Following code change is done for defect 681457
		 * For newly saved CR/Case, getAssociatedLetterGenerationRequests() method was not calling
		 * hence funcSk, funcArea & LetterCategory values were not set. This resulted in failing to 
		 * load LGR valid values after clicking on 'Create Letter' button 
		 */
		logger.info("Is CR Saved ?? " + crDataBean.isLoadLetterData());
		logger.info("Is Case Saved ?? " + caseDataBean.isLoadLetterData());
		if (crDataBean.isLoadLetterData() || caseDataBean.isLoadLetterData())
		{
			logger.info("CR or Case record is saved hence setting flag so that LGR will display with VV");
			dataBean.setSortLetterAndRespFlag(false);
			crDataBean.setLoadLetterData(false);
			caseDataBean.setLoadLetterData(false);
			getPageAccessPermission();
		}
		logger.info("SortLetterAndRespFlag = " + dataBean.isSortLetterAndRespFlag());
		
		if ((!dataBean.isSortLetterAndRespFlag()) || caseDataBean.isEnableAuditLogForLogCase() || 
				crDataBean.isAuditLogFlag())
		{
			logger.info ("CRN SK -> " + crDataBean.getCorrespondenceRecordVO().getCorrespondenceRecordNumber());
			logger.info("Case SK -> " + caseDataBean.getCaseDetailsVO().getCaseSK());
			
			if (crDataBean.getCorrespondenceRecordVO().getCorrespondenceRecordNumber() != null) {
				System.out.println("getAssociatedLetterGenerationRequests() will execute for CRN");
				getAssociatedLetterGenerationRequests();
				getPageAccessPermission();
			} else if (caseDataBean.getCaseDetailsVO().getCaseSK() != null) {
				System.out.println("getAssociatedLetterGenerationRequestsForLogCase() will execute for Case");
				getAssociatedLetterGenerationRequestsForLogCase();
				//getPageAccessPermission();
			} else {
				logger.info("**** Neither Correspondence Not Case code got executed ****");
				System.out.println("**** Neither Correspondence Not Case code got executed ****");
			}
			
			// Setting back to 'true' so that above method will not be invoked always
			dataBean.setSortLetterAndRespFlag(true);
		}
		return loadConstructorData;
	}
	
	/**
	 * @param loadConstructorData The loadConstructorData to set.
	 */
	public void setLoadConstructorData(String loadConstructorData) {
		this.loadConstructorData = loadConstructorData;
	}
/**
 * below method for sorting SendAlerDays DEFECT ESPRD00256978   
 */
	private void sortSendAlertDays(List referenceServiceVOList){
		logger.info("enter into sortSendAlertDays method");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				logger.info("----------obj1---------------"+obj1);
				ReferenceServiceVO ltrgenVO1 = (ReferenceServiceVO) obj1;
				ReferenceServiceVO ltrgenVO2 = (ReferenceServiceVO) obj2;
				boolean ascending = true;
				String firstObj = null;
				String secondObj = null;
				firstObj = ltrgenVO1.getValidValueCode();
				secondObj = ltrgenVO2.getValidValueCode();
				if (firstObj == null) {
					firstObj = GlobalLetterConstants.EMPTY_STRING;
				}
				if (secondObj == null) {
					secondObj = GlobalLetterConstants.EMPTY_STRING;
				}	
				Integer int1 = new Integer(firstObj);
				Integer int2 = new Integer(secondObj);	
				return ascending ? (int1).compareTo(int2): (int2.compareTo(int1));
				}
      	};

    	Collections.sort(referenceServiceVOList,comparator);
	}
	
	private String pageAccessPermission = GlobalLetterConstants.EMPTY_STRING; 
	/**
	 * @return Returns the pageAccessPermission.
	 */
    
    // Security
	public String getPageAccessPermission() {
		System.out.println("LettersAndResponsesControllerBean getPageAccessPermission:START");
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		String accessForFunctionalArea = null;
		String accessForLGR = null;
		//RenderRequest request = null;
		//String currentPage = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map =
			(Map) facesContext
			.getApplication()
			.createValueBinding("#{requestScope}")
			.getValue(facesContext);
		try {
			//request = getRequest();
			//currentPage = request.getParameter("javax.servlet.include.path_info");
			String userID = userID();
			// Map map = (Map)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{requestScope}").getValue(FacesContext.getCurrentInstance());
			
			if(map.get("accessForFunctionalArea")!=null){
				accessForFunctionalArea = map.get("accessForFunctionalArea").toString();
				map.put("accessForFunctionalArea",map.get("accessForFunctionalArea"));
			}
			if(map.get("accessForLGR")!=null){
				accessForLGR = map.get("accessForLGR").toString();
				map.put("accessForLGR",map.get("accessForLGR"));
			}
			if(accessForFunctionalArea==null || (accessForFunctionalArea.equalsIgnoreCase(""))){
				System.out.println(" -- accessForFunctionalArea -- ");
				accessForFunctionalArea = fieldAccessControlImpl.getFiledAccessPermission(ContactManagementConstants.LOG_CORRESPONDENCE_PAGE, userID);
				System.out.println("user permission for CRSPD/CASE is : "+ accessForFunctionalArea);
				map.put("accessForFunctionalArea",accessForFunctionalArea);
			}
			if(accessForLGR==null || (accessForLGR.equalsIgnoreCase(""))){
				System.out.println(" -- accessForLGR -- ");
				accessForLGR = fieldAccessControlImpl.getFiledAccessPermission("/Enterprise/LetterGenerationPage", userID);
				System.out.println("user permission for LGR is : "+ accessForLGR);
				map.put("accessForLGR",accessForLGR);
			}
			
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage());
		}
		finally{
			System.out.println(" -- Inside finally block -- ");
			//feildAccessUpdate ="r";
			try{
			if(accessForFunctionalArea!=null && !(accessForFunctionalArea.equalsIgnoreCase(""))){
				if(accessForFunctionalArea.equalsIgnoreCase("r")){
					System.out.println(" -- Inside Read only Permission -- ");
					getDataBeanFromContext().setUpdateLtrAndRespFlag(true);
					map.put("displayMode", accessForFunctionalArea);
				}else if(accessForLGR == null){
					System.out.println(" -- Inside LGR Read only Permission -- ");
					getDataBeanFromContext().setAccessForLGRFlag(true);
				}
			}
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		System.out.println("LettersAndResponsesControllerBean getPageAccessPermission:END");
		return pageAccessPermission;
	}
	/**
	 * @param pageAccessPermission The pageAccessPermission to set.
	 */
	public void setPageAccessPermission(String pageAccessPermission) {
		this.pageAccessPermission = pageAccessPermission;
	}
	private String userID()
	{
		String userID = null;
		try
		{
			
			HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
			.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(
					renderrequest, renderresponse);
			
			if (enterpriseUserProfile != null)
			{
				userID = enterpriseUserProfile.getUserId();
			}
		}
		catch (Exception e1)
		{
			e1.getCause();
			logger.error("USER Id Exception " + e1.getMessage());
		}
		
		return userID;
		
	}
	
	private RenderRequest getRequest(){
		
		return (RenderRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	/**
     * method to clear dataBean values and set dataBean default values
     */
	public void clearLettersAndResponsesDataBeanState(){
		logger.info("enter into clearLettersAndResponsesDataBeanState()****** ");
		LettersAndResponsesDataBean ltrAndResDataBean = getDataBeanFromContext();
		if(ltrAndResDataBean.getLetterGenerationRequests()!= null){
			logger.info("ltrAndResDataBean.getLetterGenerationRequests() not null ");
			ltrAndResDataBean.getLetterGenerationRequests().clear();
		}
		ltrAndResDataBean.setLetterGenerationInputVO(new LetterGenerationInputVO());
		ltrAndResDataBean.setRenderEditSection(false);
		ltrAndResDataBean.setShowSucessMessage(false);
		ltrAndResDataBean.setSortLetterAndRespFlag(false);
		ltrAndResDataBean.setUpdateLtrAndRespFlag(false);
		ltrAndResDataBean.setDuedateoffsetnumRender(true);
		// Following code to resolve Add Correspondence related Session issue
		ltrAndResDataBean.setFuncArea(null);
		ltrAndResDataBean.setFuncSK(null);
		ltrAndResDataBean.setLetterCategory(null);
	}
//	added for defect ID ESPRD00334051
	
	private String initializedForLogCase=GlobalLetterConstants.SUCCESS;

	/**
	 * @return Returns the initializedForLogCase.
	 */
	public String getInitializedForLogCase() {
		initPageForLogCase();
		return GlobalLetterConstants.RENDER_SUCCESS;
	}
	/**
	 * @param initializedForLogCase The initializedForLogCase to set.
	 */
	public void setInitializedForLogCase(String initializedForLogCase) {
		this.initializedForLogCase = GlobalLetterConstants.SUCCESS;
	}
	private void initPageForLogCase() {
		logger.info("Inside initPageForLogCase....");
		getAssociatedLetterGenerationRequestsForLogCase();
	}
	/**
	 * This method is the action method to get the letter request
	 * 
	 * @return String
	 */
	public String getAssociatedLetterGenerationRequestsForLogCase() {

		logger.info("Inside getAssociatedLetterGenerationRequestsForLogCase");
		try {
			
			CMLogCaseControllerBean cmLogCaseControllerBean = new CMLogCaseControllerBean();
			/*CMLogCaseDataBean cmLogcaseDataBean = cmLogCaseControllerBean
					.getCMLogCaseDataBean();*/
			
			CMLogCaseDataBean cmLogcaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN);
			LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
			String recvAttrib = null;
			lrDataBean.setFuncArea(null);
			lrDataBean.setFuncSK(null);
					if (cmLogcaseDataBean.getCaseRegardingVO() != null
							&& cmLogcaseDataBean.getCaseRegardingVO()
									.getCaseRecordNumber() != null) {
						lrDataBean.setFuncSK(new Long(cmLogcaseDataBean
								.getCaseRegardingVO()
								.getCaseRecordNumber()));
						lrDataBean
								.setFuncArea(GlobalLetterConstants.FUNCTIONAL_AREA_CD_CASE);
					
					//}
						logger.debug(" VALUES : " + cmLogcaseDataBean
								.getCaseRegardingVO()
								.getCaseRecordNumber());
					logger.debug("Func Area:" + lrDataBean.getFuncArea());
					logger.debug("Func SK:" + lrDataBean.getFuncSK());

			//			if (lrDataBean.getLetterGenerationRequests() == null) {
			LetterGenerationDelegate delegate = new LetterGenerationDelegate();
			List resultList = null;
			List letterRequestVOList = new ArrayList();

			if (lrDataBean.getFuncArea() == null) {
				recvAttrib = getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_AREA);
				lrDataBean.setFuncArea(recvAttrib);

			}

			if (lrDataBean.getFuncSK() == null) {
				recvAttrib = getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_SK);
				// lrDataBean.setFuncSK(Long.valueOf(recvAttrib));
			}

			if (lrDataBean.getLetterCategory() == null) {
				// recvAttrib =
				// getReqParamFromCtx(GlobalLetterConstants.STR_LETTER_CATEGORY);
				// lrDataBean.setLetterCategory(recvAttrib);
			}
			if (lrDataBean.getFuncSK() != null) {
				
				resultList = delegate
				.getLetterRequestList(
						GlobalLetterConstants.FUNCTIONAL_AREA_CD_CASE,
						lrDataBean.getFuncSK());
				if(resultList!=null)
					logger.debug("LetterRequestList Size:" + resultList.size());
				lrDataBean.setLetterRequestList(resultList);
			}
			if (resultList != null && (!resultList.isEmpty())) {
				letterRequestVOList = this.convertLetterRequestDOToVO(
						resultList, lrDataBean);
			}
			List tempLetterRequestVOList = new ArrayList();
			List ltrGenReqList = new ArrayList();
			if (letterRequestVOList.size() > 0) {
				Iterator iter = letterRequestVOList.iterator();
				while (iter.hasNext()) {
					LetterGenerationInputVO letterGenerationInputVO = (LetterGenerationInputVO) iter
							.next();
					//CR_ESPRD00373565_LogCase_05AUG2010
					try{
						doAuditKeyListOperationForLetterVO(letterGenerationInputVO);
					}catch(Exception e){
						logger.error("Exception while doAuditKeyListOperationForLetterVO(): ",e);
					}
					//EOF CR_ESPRD00373565_LogCase_05AUG2010
					Set ltrReceiverSet = letterGenerationInputVO
							.getLetterReceiver();
					logger.debug("ltrReceiverSet size----" + ltrReceiverSet.size());
					String receName = "";
					String receiverNames = "";
					int count = 0;
					Iterator itrLtrReceiver = ltrReceiverSet.iterator();
					while (itrLtrReceiver.hasNext()) {
						LetterGenerationReceiverVO letterGenerationReceiverVO = (LetterGenerationReceiverVO) itrLtrReceiver
								.next();
						String typeCode = letterGenerationReceiverVO
								.getLetterRequestTypeCode();
						if (typeCode != null && typeCode.equalsIgnoreCase("to")) {
							logger.debug("getLetterRequestTypeCode():::"
									+ typeCode);
							String firstName = (String) letterGenerationReceiverVO
									.getFirstName();
							String lastName = (String) letterGenerationReceiverVO
									.getLastName();
							logger.info("full name====="
									+ letterGenerationReceiverVO.getFirstName()
									+ " "
									+ letterGenerationReceiverVO.getLastName());
							if (firstName == null) {
								firstName = "";
							}

							if (lastName == null) {
								lastName = "";
							}
							receName = letterGenerationReceiverVO
									.getFirstName()
									+ " "
									+ letterGenerationReceiverVO.getLastName();
							logger.debug("receName BEFORE :::::" + receName);
							if("".equals(receName.trim()))
							{
								receName = letterGenerationReceiverVO.getDbaName();
								logger.debug("receName:::::" + receName);
							}
							
							if (count == 0) {
								receiverNames = receiverNames + receName;
								count++;
							} else {
								receiverNames = receiverNames + " " + receName;
							}

						}

					}
					logger.debug("receiverNames:::" + receiverNames);
					letterGenerationInputVO.setReceiverName(receiverNames);
					tempLetterRequestVOList = eleminateDuplicateRecordsBasedOnLetterReq(
							letterGenerationInputVO.getLetterReqSK(),
							letterRequestVOList, tempLetterRequestVOList);
					ltrGenReqList.add(letterGenerationInputVO);
				}
			}
			//lrDataBean.setLetterGenerationRequests(tempLetterRequestVOList);
			ArrayList ltrgenVOList = new ArrayList();
			ltrgenVOList.addAll(tempLetterRequestVOList);
			Comparator comparator = new Comparator() {
				public int compare(Object obj1, Object obj2) {
					LetterGenerationInputVO ltrgenVO1 = (LetterGenerationInputVO) obj1;
					LetterGenerationInputVO ltrgenVO2 = (LetterGenerationInputVO) obj2;
					boolean ascending = false;
				      Integer int1 = new Integer(ltrgenVO1.getLetterReqSK());
				      Integer int2 = new Integer(ltrgenVO2.getLetterReqSK());
				      return ascending ? (int1).compareTo(int2) : (int2
				        .compareTo(int1));
				}
			};
			Collections.sort(ltrgenVOList, comparator);
			lrDataBean.setLetterGenerationRequests(ltrgenVOList);		
					}
					else{
						lrDataBean.setLetterGenerationRequests(new ArrayList());
					}
			//lrDataBean.setLetterGenerationRequests(letterRequestVOList);
			//			}
			logger.info("#### End Inside getAssociatedLetterGenerationRequestsForLogCase");
		} catch (Exception e) {
			logger.error("Exception : while loading getAssociatedLetterGenerationRequestsForLogCase",e);
			return GlobalLetterConstants.RENDER_SUCCESS;
		}
		return GlobalLetterConstants.RENDER_SUCCESS;
	}
//	EOF defect ESPRD00334051
	 /**
	 * @param enterpriseBaseDomain
	 * @param auditaleEnterpriseBaseVO
	 * author: Venkat J
	 */
	private void createVOAuditKeysList(EnterpriseBaseDomain enterpriseBaseDomain,AuditaleEnterpriseBaseVO  auditaleEnterpriseBaseVO)
	 {
		logger.info("Inside createVOAuditKeysList");
    	  List fullAuditList = new ArrayList();
    	  
    	  if(auditaleEnterpriseBaseVO.getAuditKeyList()!= null){
    	  	fullAuditList= auditaleEnterpriseBaseVO.getAuditKeyList();
    	  }
           LineItemAuditsDelegate auditDelegate;
    	try {
    		auditDelegate = new LineItemAuditsDelegate();
    		
    		List resultList = auditDelegate.getAuditKeyList(enterpriseBaseDomain);
           
    	       if(resultList!=null){
    	       	fullAuditList.addAll(resultList);
    	       	auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);
    	       }
    	} catch (LineItemAuditsBusinessException e) {
    	
    		e.printStackTrace();
    	}
    	catch (Exception e) {
    	
    		e.printStackTrace();
    	}
    	logger.info("Exit of createVOAuditKeysList");
    }
	
	//CR_ESPRD00373565_LogCase_05AUG2010
	//Below line is modified from static field to instance filed
	private List auditableLetterDetails;
	//private static List auditableLetterDetails;

	public List getAuditableLetterDetails() {
		auditableLetterDetails = new ArrayList();
		auditableLetterDetails.add(createAuditableField("Notify via Alert","notifyAlertUser"));
		auditableLetterDetails.add(createAuditableField("Alert Based On","alertBasedOnColName"));
		auditableLetterDetails.add(createAuditableField("Send Alert # of Days","sendAlertDaysCode"));
		auditableLetterDetails.add(createAuditableField("Explanation","explanationText"));

		return auditableLetterDetails;
	}

	private void doAuditKeyListOperationForLetterVO(
			LetterGenerationInputVO letterGenerationInputVO) {

		try {
			logger
					.debug(">>---------> Inside LetterGenerationInputVO letterGenerationInputVO doAuditKeyListOperationForLetterVO:");
			if (auditableLetterDetails == null
					|| auditableLetterDetails.isEmpty()) {
				getAuditableLetterDetails();
			}
			if (letterGenerationInputVO.getAuditKeyList() != null
					&& !letterGenerationInputVO.getAuditKeyList().isEmpty()) {
				AuditDataFilter.filterAuditKeys(auditableLetterDetails,
						letterGenerationInputVO);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
		}
	}

	private AuditableField createAuditableField(String fieldName,
			String domainAttributeName) {
		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(fieldName);
		auditableField.setDomainAttributeName(domainAttributeName);
		return auditableField;
	}
	
	/**
	 * Added as a part of Defect ESPRD00796178,to set the ID of 'View Letter
	 * Request' Link of Correspondence for Right Click Issue.
	 * 
	 * @return String
	 */

	public String invokeCRViewLetterReq() {

		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		lrDataBean.setViewLetterReq("logCrspdlettNrespSubview:logCrspdlettersNrespSubview:editViewLtrLinksec123");
		return GlobalLetterConstants.EMPTY_STRING;
	}

	/**
	 * Added as a part of Defect ESPRD00796178,to set the ID of 'View Letter
	 * Request' Link of Case for Right Click Issue.
	 * 
	 * @return String
	 */
	public String invokeCaseViewLetterReq() {

		LettersAndResponsesDataBean lrDataBean = getDataBeanFromContext();
		lrDataBean.setViewLetterReq("caseLettersAndResponses:logCrspdlettersNrespSubview:editViewLtrLinksec");
		return GlobalLetterConstants.EMPTY_STRING;
	}
}
