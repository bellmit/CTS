/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
//import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

//import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.portlet.ActionRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.audit.application.exception.GlobalAuditsBusinessException;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.cots.CommonLetter.view.bean.LettersAndResponsesDataBean;
import com.acs.enterprise.common.cots.edms.application.common.vo.EDMSQuickSearchResultsVO;
import com.acs.enterprise.common.cots.edms.application.common.vo.EnterpriseEDMSSearchCriteriaVO;
import com.acs.enterprise.common.cots.edms.application.common.vo.EnterpriseEDMSSearchResultsVO;
import com.acs.enterprise.common.cots.edms.application.exception.EDMSQuickSearchBusinessException;
import com.acs.enterprise.common.cots.edms.delegate.EDMSQuickSearchProcessDelegate;
import com.acs.enterprise.common.cots.edms.util.EDMSURLGeneratorImpl;
import com.acs.enterprise.common.cots.edms.util.exception.EDMSBaseException;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.cots.lettergeneration.vo.CommonLetterInputVO;
import com.acs.enterprise.common.cots.lettergeneration.vo.LetterGenerationInputVO;
import com.acs.enterprise.common.cots.util.GlobalLetterConstants;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.application.exception.SystemParameterNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.delegate.SystemListDelegate;
import com.acs.enterprise.common.program.administration.common.delegate.SystemParameterDelegate;
import com.acs.enterprise.common.program.administration.common.delegate.ValidValueDelegate;
import com.acs.enterprise.common.program.administration.common.domain.SystemList;
import com.acs.enterprise.common.program.administration.common.domain.SystemListDetail;
import com.acs.enterprise.common.program.administration.common.domain.SystemParameter;
import com.acs.enterprise.common.program.administration.common.domain.SystemParameterDetail;
import com.acs.enterprise.common.program.administration.common.domain.ValidValue;
import com.acs.enterprise.common.program.administration.common.domain.ValidValueDomain;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.common.domain.SpecificEntity;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonNotesControllerBean;
import com.acs.enterprise.common.program.commonentities.view.exception.CommonEntityUIException;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.contactmanagement.application.dataaccess.exception.CaseFetchDataException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.AppealFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseTypeFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CustomFieldFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.AppealDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMEntityDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMProcessDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.ActCMCaseEvent;
import com.acs.enterprise.common.program.contactmanagement.common.domain.ActCMCaseStep;
import com.acs.enterprise.common.program.contactmanagement.common.domain.AdministrativeHearing;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Alert;
import com.acs.enterprise.common.program.contactmanagement.common.domain.AppealTracking;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseAttachCrossReference;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseCommonEntityCrossRef;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseDDUUnusualCircumstances;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRecord;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeEvent;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeStep;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseWatchList;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CategorySubjectXref;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
//import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
//import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldDropDownValue;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldValue;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
//import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.common.domain.SubjectAuxillary;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CRAdvanceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRequestVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.ContactManagementProviderSearchVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CategoryDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CorrespondenceDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.DynamicCustomFieldHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.Executor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LogCaseDomainToVOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LogCaseHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LogCaseVOToDomainConverter;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LogCaseValidationHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MyTaskDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AlertVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCaseVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCorrespondenceVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseEventsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseLetterResponsesXrefVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingMemberVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingProviderVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingTPL;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingTradingPartnerVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseStepsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeBCCPVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeDDUVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceRecordVO;
//import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.MyAlertsVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.config.ConfigurationLoader;
import com.acs.enterprise.common.util.helper.DateUtility;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.helper.VirusScanUtility;
import com.acs.enterprise.common.util.helper.intf.URLGenerator;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.acs.enterprise.mmis.member.common.application.exception.MemberBusinessException;
import com.acs.enterprise.mmis.member.common.domain.Member;
import com.acs.enterprise.mmis.member.common.vo.MemberBasicInfo;
import com.acs.enterprise.mmis.member.common.vo.MemberInformationRequestVO;
import com.acs.enterprise.mmis.member.information.common.delegate.MemberInformationDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.application.exception.TPLCarrierBusinessException;
import com.acs.enterprise.mmis.operations.tpladministration.common.delegate.TPLCarrierDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.delegate.TPLPolicyDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLCarrier;
//import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLPolicyHolder;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLPolicyHolderDetailsVO;
import com.acs.enterprise.mmis.provider.common.delegate.ProviderInformationDelegate;
import com.acs.enterprise.mmis.provider.common.domain.TradingPartner;
import com.acs.enterprise.mmis.provider.common.vo.ProviderInformationRequestVO;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;
import com.ibm.faces.component.html.HtmlFileupload;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.faces.fileupload.util.ContentElement;
import com.acs.enterprise.common.program.administration.common.delegate.SystemListDelegate;
import com.acs.enterprise.common.program.contactmanagement.view.helper.AlertDOConvertor;	//ADDED FOR THE DEFECT ESPRD00743389

/**
 * This class implements and controls all action methods like add, update,
 * cancel for Case information.
 * 
 * @author Wipro
 */
public class CMLogCaseControllerBean extends EnterpriseBaseControllerBean {

	/** Holds requestScope of type Map. */
	private Map requestScope;

	/** Creates an instance of the logger. */
	static final EnterpriseLogger log = EnterpriseLogFactory
			.getLogger(CMLogCaseControllerBean.class.getName());

	/** This field is used to store receiveMessage. */
	private String receiveMessage;

	/** This field is used to store receiveMessage. */
	private String loadValidValue;

	private String showCaseTypeScreens;

	private String link2Show;

	/**
	 * Holds attachmentDataBean.
	 */
	private CMLogCaseDataBean logCaseDataBean=(CMLogCaseDataBean)getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

	private CaseSearchDataBean caseSearchDataBean;

	/** Added For the defect ID ESPRD00334100 */
	private boolean isDateErrOccurred = false;

	/** Ends */

	/** Holds common message to log at begin of every method. */
    // Moved to ContactManagementConstants.java
	//protected static final String BEGINMETHOD = "Begin of the CMLogCaseControllerBean";
	
	/** Holds common message to log at end of every method. */
	// Moved to ContactManagementConstants.java
	//protected static final String ENDMETHOD = "End of the CMLogCaseControllerBean";

	/**
	 * Holds the boolean value that indicate whether validation is passed or not
	 * before Big Save
	 */
	private boolean validateFlag;
	/**
	 * This String is used for to get the data from Entity Portlet to Log Case
	 * Page by using getter of this String getLoadDataFromEntity(),for Defect ESPRD00802462
	 * */
	private String loadDataFromEntity;

	private CommonEntityDataBean commonEntityDataBean;

	private SearchCorrespondenceDataBean searchCorrespondenceDataBean;

	private CommonNotesControllerBean commonNotesControllerBean;

	/**
	 * Holds the Bean name
	 */
	
	// Moved to ContactManagementConstants.java
	//public static final String CMLOGCASE_DATA_BEAN = "logCaseDataBean";

	//public static final String COMMON_ENTITY_DATA_BEAN = "commonEntityDataBean";

	//public static final String SEARCH_CORRESPONDENCE_DATA_BEAN = "cs_searchCorrespondenceDataBean";

	//public static final String CASE_SEARCH_DATA_BEAN = "caseSearchDataBean";

	//public static final String COMMON_NOTES_CONTROLLER_BEAN = "commonNotesControllerBean";

	/**
	 * Constructor
	 */
	/*
	 * public CMLogCaseControllerBean() { super(); FacesContext facesContext =
	 * FacesContext.getCurrentInstance(); requestScope = (Map)
	 * facesContext.getApplication().createValueBinding(
	 * "#{requestScope}").getValue(facesContext); //Infinite Computer Solutions
	 * FOR CR -1825 HttpServletRequest renderrequest = (HttpServletRequest)
	 * FacesContext .getCurrentInstance().getExternalContext().getRequest();
	 * HttpServletResponse renderresponse = (HttpServletResponse) FacesContext
	 * .getCurrentInstance().getExternalContext().getResponse();
	 * 
	 * 
	 * 
	 *  }
	 */

	//  Added by Infinite while Performance Issues...
	/**
	 * This method will return the reference of the data bean.
	 * 
	 * @return relatedDataBean
	 */

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
	 * It will shows the Correspondence Search screen
	 * 
	 * @return String
	 */
	public String showCorrespondenceSearchScreen() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " showCorrespondenceSearchScreen");
		//  logCaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowCorrespondenceSearchScreen(true);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * It will cancel the Correspondence Search screen
	 * 
	 * @return String
	 */
	public String cancelCorrespondenceSearchScreen() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " cancelCorrespondenceSearchScreen");
		//  logCaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowCorrespondenceSearchScreen(false);
		logCaseDataBean.setShowCorrSearchScreen(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * It will shows the Case Search screen in LogCase
	 * 
	 * @return String
	 */
	public String showCaseSearchScreen() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " showCaseSearchScreen");
		// logCaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowCaseSearchScreen(true);
		//UC-PGM-CRM-018_ESPRD00392654_25JAN10
		CaseSearchDataBean caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.CASE_SEARCH_DATA_BEAN);
		if (caseSearchDataBean != null) {
			caseSearchDataBean.setControlCameFromCMLogCase(true);
		}
		//end

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * It will cancel the Case Search screen in LogCase
	 * 
	 * @return String
	 */
	public String cancelCaseSearchScreen() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " cancelCaseSearchScreen");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowCaseSearchScreen(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * It will shows the Correspondence Search results
	 * 
	 * @return String
	 */
	public String showCorrespondenceSearchResults() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowCorrespondenceSearchResults(true);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * It will shows the Case Search results
	 * 
	 * @return String
	 */
	public String showCaseSearchResults() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowCaseSearchResults(true);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * It will reset the Correspondence search results
	 * 
	 * @return String
	 */
	public String showCorrespondenceSearchResultsReset() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowCorrespondenceSearchResults(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * It will reset the Case search results
	 * 
	 * @return String
	 */
	public String showCaseSearchResultsReset() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowCorrespondenceSearchResults(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method calculates he FollowUpmonths.
	 * 
	 * @return String
	 */
	public String showFollowUpMonths() {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " showFollowUpMonths");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String value = logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO()
				.getRecommendationCd();
		log.debug("showFollowUpMonths value is $$ " + value);
		if ("S".equals(value)) {
			logCaseDataBean.setEnableFollowUpMon(true);
		} else {
			logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO()
					.setStFollowUPNum("");
			logCaseDataBean.setEnableFollowUpMon(false);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " showFollowUpMonths");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This Method gets the StartedDate Fields.
	 * 
	 * @return String
	 */

	public String showTreamentStartedDateFields() {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " showTreamentStartedDateFields");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String value = logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO()
				.getTreatmentStartedCd();
		if (StringUtils.isNotEmpty(value)) {
			String shortDesc = ContactManagementHelper.setShortDescription(
					FunctionalAreaConstants.GENERAL2,
					ReferenceServiceDataConstants.G_CASE_BCCP_TRMT_STARTED_CD,
					value);
			if (shortDesc.startsWith("Y")) {
				logCaseDataBean.setEnableTreatementStDates(true);
			} else {
				logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO()
						.setTreatmentStartDateStr("");
				logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO()
						.setChemoProjectedEndDateStr("");
				logCaseDataBean.setEnableTreatementStDates(false);
			}
		}
		//to fix ESPRD00690067 starts
		else {
			logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO()
					.setTreatmentStartDateStr("");
			logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO()
					.setChemoProjectedEndDateStr("");
			logCaseDataBean.setEnableTreatementStDates(false);
		}
		//to fix ESPRD00690067 ends
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " showTreamentStartedDateFields");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This nethod is used to get the BiopsyFindings.
	 * 
	 * @return String
	 */
	public void showBiopsyFindings(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " showBiopsyFindings");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (StringUtils.isNotEmpty(event.getNewValue().toString())) {
			log.debug("showBiopsyFindings is not null");
			logCaseDataBean.setEnableBiopsyFindings(true);
		} else {
			logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO()
					.setBiopsyFindingsCd("");
			logCaseDataBean.setEnableBiopsyFindings(false);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " showBiopsyFindings");

	}

	/**
	 * This method gets the case StepStatus Details.
	 * 
	 * @param event
	 *            This method takes the event change as param
	 * @return String
	 */
	public String showCaseStepStatusDetails(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " showCaseStepStatusDetails");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String value = event.getNewValue().toString();
		Date date = new Date();
		String dateStr = ContactManagementHelper.dateConverter(date);
		//Modified for defect ESPRD00388582starts
		logCaseDataBean.getCaseStepsVO().setCompletionDateStr(
				MaintainContactManagementUIConstants.EMPTY_STRING);
		logCaseDataBean.getCaseStepsVO().setCompletionDate(null);
		//defect ESPRD00388582 ends
		if ("A".equals(value)) {
			logCaseDataBean.getCaseStepsVO().setDateStartedStr(dateStr);
			logCaseDataBean.getCaseStepsVO().setDateStarted(date);
			// defect ESPRD00540227 started
			String completionBasedOn = logCaseDataBean.getCaseStepsVO()
					.getCompletedBasedOn();
			if (logCaseDataBean.isShowEditCaseSteps()
					&& completionBasedOn != null
					&& !completionBasedOn
							.equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
				expecteCompletionDateForEditExtended(completionBasedOn);
			}
			//defect ESPRD00540227 ends
		} else if ("C".equals(value) || "V".equals(value)) {
			logCaseDataBean.getCaseStepsVO().setCompletionDateStr(dateStr);
			logCaseDataBean.getCaseStepsVO().setCompletionDate(date);
		}
		//for ESPRD00741245
		if (logCaseDataBean.isShowAddCaseSteps()) {
			logCaseDataBean.setPageFocusId("addCaseStepFocusPage");
			logCaseDataBean.setCursorFocusValue("LOGCASESTEPSSTATUS0");
		} else {
			logCaseDataBean.setPageFocusId("editCaseStepFocusPage");
			logCaseDataBean.setCursorFocusValue("LOGCASESTEPSSTATUS1");
		}
		logCaseDataBean.setSaveCaseStepFlag(false);//Add for the Defect ESPRD00773297
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " showCaseStepStatusDetails");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	public String resetCase() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " resetCase");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setPageFocusId("portletHeaderFocus");
		logCaseDataBean.setCursorFocusValue("LOGCASESTATUS");
		// Added for the Defect Id : ESPRD00736433
		// Start
		//LogCaseHelper logCaseHelper = new LogCaseHelper();
		//logCaseHelper.restoreCaseState();
		// End
		//ADDED FOR THE DEFECT ESPRD00734091
		//logCaseDataBean.setNavigatedToCorrespondence(true);
		//ADDED END FOR THE DEFECT ESPRD00734091
		//for defect ESPRD00880351
		collapseSections();
		if (isNullOrEmptyField(logCaseDataBean.getCaseRegardingVO().getCaseRecordNumber())) {
			try{/*
			log.debug("in addd mode $$$$$$$");
			CaseDetailsVO tempDetailsVO = new CaseDetailsVO();
			logCaseDataBean.setCaseDetailsVO(tempDetailsVO);

			Map userMap = null;
			String createdByName = logCaseDataBean.getLoggedInUserID(); // Modified for the Performance fix
			Long usrSK = getUserSK(createdByName);
			String userID = logCaseDataBean.getLoggedInUserID(); // Modified for the Performance fix
			String createdBySK = usrSK.toString();

			log.debug("createdBySK in populateInitialData() is $$ "
					+ createdBySK);
			logCaseDataBean.getCaseDetailsVO().setLineOfBusiness("MED");
			logCaseDataBean.getCaseDetailsVO().setPriority("M");

			userMap = logCaseDataBean.getUserNameMap();

			if (!userMap.isEmpty()) {
				String userName = (String) userMap.get(createdBySK);
				log.debug("While Loading created by name is $$ " + userName);
				logCaseDataBean.getCaseDetailsVO().setCreatedBy(userName);
				logCaseDataBean.getCaseDetailsVO().setAssignedTo(userName);
			}
				logCaseDataBean.getCaseDetailsVO().setAssignedToWorkUnitSK(
						createdBySK);
				logCaseDataBean.getCaseDetailsVO().setCreatedBySK(createdBySK);
				logCaseDataBean.getCaseDetailsVO().setCreatedDate(new Date());
				logCaseDataBean.getCaseDetailsVO().setDaysopened("0");
			
			logCaseDataBean.setShowBCCPTypeScreen(false);
			logCaseDataBean.setShowARSTypeScreen(false);
			logCaseDataBean.setShowDDUTypeScreen(false);
			logCaseDataBean.setShowGeneralCaseTypeScreens(false);
			logCaseDataBean.setShowAppealScreen(false);
			logCaseDataBean.setShowCaseDetailsMessages(false); // Added the code for the defect id ESPRD00712855_19OCT2011
			*/
				logCaseDataBean.setResetCaseInAddModeFlag(Boolean.TRUE);
			}catch(Exception excep){
				excep.printStackTrace();
			}

		} else {
			loadLogCasePageWithCaseSk(logCaseDataBean.getCaseSK());
			//logCaseDataBean.setCaseDetailsVO(ContactManagementHelper
					//.getCaseDetailMap(logCaseDataBean.getLoggedInUserID())); // Modified for the Performance fix
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " resetCase");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * It will shows the case type screens like BCCP, ARS, etc.. depends on user
	 * selected case type
	 * 
	 * @param event
	 *            it catches the user event
	 * @return String
	 */
	public void showCaseTypeScreens(ValueChangeEvent event) {

		log.debug("event $$$$$$$$$$$$$"+event.getNewValue());
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " showCaseTypeScreens");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		String caseTypeSKStr = (String) event.getNewValue();

		if (caseTypeSKStr != null) {
			log.debug("caseTypeSKStr$$$$$$$$$$$$$$$$$"+caseTypeSKStr);
			getCaseTypeDetails(caseTypeSKStr);
			showCaseType(caseTypeSKStr);
		}

		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " showCaseTypeScreens");
	}

	/**
	 * @param caseTypeSKStr
	 */
	private void getCaseTypeDetails(String caseTypeSKStr) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " getCaseTypeDetails");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		LogCaseDomainToVOConverter domainToVOConverter = new LogCaseDomainToVOConverter();

		CaseTypeEvent caseTypeEvent = null;
		CaseEventsVO caseEventsVO = null;
		List eventList = new ArrayList();
		Set caseTypeEventSet = null;

		CaseTypeStep caseTypeStep = null;
		CaseStepsVO caseStepsVO = null;
		List stepList = new ArrayList();
		Set caseTypeStepSet = null;
		logCaseDataBean.setCustomFieldVOList(null); // Added for the PanelGrid Fix

		CaseType caseType = null;
		// String businessUnit;

		Set templateSet = null;

		// List customFieldList = null;
		LogCaseHelper caseHelper = new LogCaseHelper();
		ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
		try {
			if (StringUtils.isNotEmpty(caseTypeSKStr)) {

				Long caseTypeSK = new Long(caseTypeSKStr);
				caseType = delegate.getCaseTypeDetails(caseTypeSK);

				if (caseType != null) {
					int eventsSize = 0;
					int stepsSize = 0;
					int templateSize = 0;

					logCaseDataBean.getCaseDetailsVO()
							.setCaseTypeBusinessUnitTypeCode(
									caseType.getBusinessUnitCaseTypeCode());
					logCaseDataBean.setSuperviserRevReqIndForCaseType(caseType
							.getSupervisorReviewReqIndicator().booleanValue());
					log
							.debug("While getting Case Type details Superviser revReqInd is $$ "
									+ logCaseDataBean
											.isSuperviserRevReqIndForCaseType());
					templateSet = caseType.getCaseTypeTemplates();
					templateSize = templateSet.size();
					log.debug("Case Type Templates Set size is $$ "
							+ templateSize);
					if (!templateSet.isEmpty() && templateSize > 0) {
						caseHelper.setTemplates(templateSet);
					} else { //UC-PGM-CRM-018.4_ESPRD00351624_17DEC09
						logCaseDataBean.getTemplateList().clear();
					}

					caseTypeEventSet = caseType.getCaseTypeEvents();
					log.debug("Case TypeEvents Set size is $$ "
							+ caseTypeEventSet.size());
					eventsSize = caseTypeEventSet.size();
					if (!caseTypeEventSet.isEmpty() && eventsSize > 0) {
						log
								.debug("Size of caseTypeEvents Set is "
										+ eventsSize);
						Iterator iter = caseTypeEventSet.iterator();
						while (iter.hasNext()) {
							caseTypeEvent = (CaseTypeEvent) iter.next();
							logCaseDataBean.getCaseTypeEventDOList().add(
									caseTypeEvent);

							LetterTemplate letterTemplate = caseTypeEvent
									.getLetterTemplate();

							caseEventsVO = domainToVOConverter
									.convertCaseTypeEventsDOToVO(caseTypeEvent);

							caseEventsVO.setCaseTypeSK(caseType.getCaseTypeSK()
									.toString());
							log.debug("CaseTypeSK in caseEvents VO is $$ "
									+ caseEventsVO.getCaseTypeSK());
							log
									.debug("convertion of caseTypeEventDO to CaseEventVO is over");
							eventList.add(caseEventsVO);
						}

						if (!eventList.isEmpty()) {
							logCaseDataBean.setCaseEventsVOList(eventList);
							logCaseDataBean.setShowCaseEventsDataTable(true);

						}
					}
					caseTypeStepSet = caseType.getCaseTypeSteps();
					log.debug("Case Type Steps Set size is $$ "
							+ caseTypeStepSet.size());
					stepsSize = caseTypeStepSet.size();
					if (!caseTypeStepSet.isEmpty() && stepsSize > 0) {
						Iterator iter = caseTypeStepSet.iterator();
						while (iter.hasNext()) {
							caseTypeStep = (CaseTypeStep) iter.next();
							logCaseDataBean.getCaseTypeStepDOList().add(
									caseTypeStep);
							caseStepsVO = domainToVOConverter
									.convertCaseTypeStepsDOToVO(caseTypeStep);
							caseStepsVO.setCaseTypeSK(caseType.getCaseTypeSK()
									.toString());
							log
									.debug("convertion of caseTypeStepDO to CaseStepVO is over");
							stepList.add(caseStepsVO);
						}
						log
								.debug("Case Type Steps list size form DataBean is $$  "
										+ logCaseDataBean
												.getCaseTypeStepDOList().size());
						if (!stepList.isEmpty()) {
							caseHelper
									.sortCaseStepsComparator(
											MaintainContactManagementUIConstants.CASESTEPS_ORDER,
											MaintainContactManagementUIConstants.SORT_TYPE_ASC,
											stepList);
							logCaseDataBean.setCaseStepsVOList(stepList);
							logCaseDataBean.setShowCaseStepsDataTable(true);
						}

						if (logCaseDataBean.getCaseStepsVOList() != null
								&& !logCaseDataBean.getCaseStepsVOList()
										.isEmpty()) {
							boolean isActiveFound = false;
							for (Iterator itr = logCaseDataBean
									.getCaseStepsVOList().iterator(); itr
									.hasNext();) {
								CaseStepsVO stepVO = (CaseStepsVO) itr.next();
								if (stepVO.getStatus() != null
										&& stepVO
												.getStatus()
												.equalsIgnoreCase(
														ContactManagementConstants.STEP_STATUS_ACTIVE)) {
									logCaseDataBean.getCaseDetailsVO().setStep(
											stepVO.getOrder());
									isActiveFound = true;
									break;
								}
							}
							if (!isActiveFound) {
								logCaseDataBean
										.getCaseDetailsVO()
										.setStep(
												MaintainContactManagementUIConstants.EMPTY_STRING);
							}
						} else {
							logCaseDataBean
									.getCaseDetailsVO()
									.setStep(
											MaintainContactManagementUIConstants.EMPTY_STRING);
						}

					}
					logCaseDataBean.getCustomFieldDOList().clear();

					//Commented for HeapDump Issue
					//logCaseDataBean.getFormElements().getChildren().clear();

					List cfList = delegate.getAllCustomFields(caseTypeSK);
					// Begin - Modified the code for the PanelGrid Fix
					DynamicCustomFieldHelper customFieldHelper = new DynamicCustomFieldHelper();
					if (cfList != null && !cfList.isEmpty()) {
						logCaseDataBean.setCustomFieldDOList(cfList);
						// showDynamic(cfList, caseTypeSK.toString(), false);
						logCaseDataBean.setCustomFieldVOList(customFieldHelper
								.showDynamicFields(cfList, false, null,
										logCaseDataBean.isShowCaseType()));
					}
					// End - Modified the code for the PanelGrid Fix
				}
			}

		} catch (CaseTypeFetchBusinessException e) {
			log.error("Error while showing case Type screens ", e);
		} catch (NumberFormatException e) {
			log.error("Error while showing case Type screens ", e);
		} catch (CustomFieldFetchBusinessException e) {
			log.error("Error while showing customField screens ", e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " getCaseTypeDetails");
	}

	//Commented for HeapDump Issue
	/**
	 * @param list
	 * @param caseTypeSK
	 * @param isCRClosed
	 */
	/*private void showDynamic(List list, String caseTypeSK, boolean isCRClosed) {
		log.info(BEGINMETHOD + " showDynamic");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN);
		CustomField field = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application app = facesContext.getApplication();

		String bindingValue = null;
		String required = null;
		List children = logCaseDataBean.getFormElements().getChildren();
		children.clear();

		DynamicCustomFieldHelper customFieldHelper = new DynamicCustomFieldHelper();
		if (list != null && !list.isEmpty()) {
			int cfListSize = 0;
			log.debug("After getting CF list size is $$ " + list.size());

			cfListSize = list.size();
			for (int i = 0; i < cfListSize; i++) {
				field = (CustomField) list.get(i);
				log.debug("CustomField Sk is $$ " + field.getCustomFieldSK());
				bindingValue = customFieldHelper.generateBindingValue(i,
						"logCaseDataBean");
				log.debug("Binding value for element " + i + " is "
						+ bindingValue);
				if (field.getRequiredValueIndicator().booleanValue()) {
					required = "*";
				} else {
					required = "";
				}
				String componentId = "";
				if (field.getDataTypeCode().equals("D")) {
					componentId = "calendar";

					customFieldHelper.getRequiredText(children, caseTypeSK,
							"r2" + i, required);
					customFieldHelper.getLabelComponent(children, caseTypeSK,
							"t2" + i, field.getDescription(), caseTypeSK
									+ "calendar" + i);
					customFieldHelper.getSpace(children, caseTypeSK, "o" + i);
					customFieldHelper.getDateComponent(children, caseTypeSK,
							"calendar" + i, field, app, bindingValue,
							isCRClosed, logCaseDataBean.isShowCaseType());
				} else if (field.getDataTypeCode().equals("N")
						|| field.getDataTypeCode().equals("T")
						|| field.getDataTypeCode().equals("DA")) {
					componentId = "input";
					customFieldHelper.getRequiredText(children, caseTypeSK,
							"r2" + i, required);
					customFieldHelper.getLabelComponent(children, caseTypeSK,
							"t3" + i, field.getDescription(), caseTypeSK
									+ "input" + i);
					customFieldHelper.getSpace(children, caseTypeSK, "o" + i);
					customFieldHelper.getInputComponent(children, caseTypeSK,
							"input" + i, field, app, bindingValue, isCRClosed,
							logCaseDataBean.isShowCaseType());
				} else if (field.getDataTypeCode().equals("DD")) {
					componentId = "select";
					customFieldHelper.getRequiredText(children, caseTypeSK,
							"r2" + i, required);
					customFieldHelper.getLabelComponent(children, caseTypeSK,
							"t3" + i, field.getDescription(), caseTypeSK
									+ "select" + i);
					customFieldHelper.getSpace(children, caseTypeSK, "o" + i);

					customFieldHelper.getDropDownComponent(children,
							caseTypeSK, "select" + i, field, app, bindingValue,
							isCRClosed, logCaseDataBean.isShowCaseType());
				} else if (field.getDataTypeCode().equals("CB")) {

					customFieldHelper.getRequiredText(children, caseTypeSK,
							"r2" + i, required);
					customFieldHelper.getLabelComponent(children, caseTypeSK,
							"t3" + i, field.getDescription(), caseTypeSK + "cb"
									+ i);
					customFieldHelper.getSpace(children, caseTypeSK, "o" + i);
					customFieldHelper.getCheckBoxComponent(children,
							caseTypeSK, "cb" + i, field, app, bindingValue,
							isCRClosed, logCaseDataBean.isShowCaseType());
				}
				//commented for critical defect ESPRD00597161
				
				 * customFieldHelper.getSpace(children, caseTypeSK, "o" + i);
				 * customFieldHelper.getSpace(children, caseTypeSK, "o" + i);
				 * customFieldHelper.getSpace(children, caseTypeSK, "o" + i);
				 * HtmlMessage htmlMessage =new HtmlMessage();
				 * htmlMessage.setFor(componentId + i +
				 * field.getCustomFieldSK());
				 * htmlMessage.setStyleClass("errorMessage");
				 * htmlMessage.setValueBinding("value",app.createValueBinding(bindingValue));
				 * children.add(htmlMessage);
				 
			}
		}
		log.info(ENDMETHOD + " showDynamic");
	}*/

	/**
	 * This method is used to show the DDU Appointment screen in JSP.
	 * 
	 * @param event
	 *            holds the user event
	 * @return String
	 */
	public String showDDUAppoint(ValueChangeEvent event) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (event.getNewValue() != null
				&& event.getNewValue().equals(
						MaintainContactManagementUIConstants.D)) {
			logCaseDataBean.setShowDDUAppointmentScreen(true);
		} else {
			logCaseDataBean.setShowDDUAppointmentScreen(false);
		}
		//Add for the Defect ESPRD00773297
		logCaseDataBean.setSaveCaseEventFlag(Boolean.FALSE);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to show the Field Audit Date in JSP
	 * 
	 * @return String
	 */
	public void showReviewRequredFields() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String status = logCaseDataBean.getCaseDetailsVO().getCaseTypeDDUVO()
				.getReviewRequiredInd();
		log.debug("while showing rev date " + status);
		if (status.equals("true")) {
			logCaseDataBean.setShowReviewReq(true);
		} else if (status.equals("false")) {
			logCaseDataBean.setShowReviewReq(false);
		}
	}

	public String showSelectedItemInSelectManyBox() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " showSelectedItemInSelectManyBox");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List selectedItems = logCaseDataBean.getCaseDetailsVO()
				.getCaseTypeDDUVO().getUnusualAvaiSelectedList();
		// List selectedList = new ArrayList();
		List availableList = logCaseDataBean.getUnusualCircumstancesList();
		if (selectedItems != null && !selectedItems.isEmpty()) {

			for (Iterator iter = selectedItems.iterator(); iter.hasNext();) {

				if (logCaseDataBean.getSelectedUnUsualList() != null) {
					logCaseDataBean.getSelectedUnUsualList().add(
							removeItem((String) iter.next(), availableList));
				}
			}

		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " showSelectedItemInSelectManyBox");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	public String removeSelectedItemsFromBox() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " removeSelectedItemsFromBox");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List removedList = logCaseDataBean.getCaseDetailsVO()
				.getCaseTypeDDUVO().getUnusualSelectedList();
		List existingList = logCaseDataBean.getUnusualCircumstancesList();
		List selectedList = logCaseDataBean.getSelectedUnUsualList();
		removeSelected(removedList, existingList, selectedList);
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " removeSelectedItemsFromBox");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * Method to remove an item from the list.
	 * 
	 * @param value
	 *            The value to set.
	 * @param filterList
	 *            The filterList to set.
	 * @return SelectItem.
	 */
	private SelectItem removeItem(String value, List availList) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "removeItem");
		SelectItem result = null;
		int size = availList.size();
		for (int i = 0; i < size; i++) {
			SelectItem item = (SelectItem) availList.get(i);
			if (value.equals(item.getValue())) {
				result = (SelectItem) availList.remove(i);
				break;
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "removeItem");
		return result;
	}

	/**
	 * Remove selected.
	 * 
	 * @param removedItems
	 *            The removedItems to set.
	 * @param availableList
	 *            The availableList to set.
	 * @param selectedList
	 *            The selectedList to set.
	 * @return String.
	 */
	private String removeSelected(List removedItems, List availableList,
			List selectedList) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "removeSelected");
		if (selectedList != null) {
			for (Iterator iter = removedItems.iterator(); iter.hasNext();) {
				availableList
						.add(removeItem((String) iter.next(), selectedList));
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "removeSelected");
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is used to show the user departments in JSP
	 * 
	 * @param changeEvent
	 *            holds the user event
	 * @return String
	 */
	public String showUserDepartments(ValueChangeEvent changeEvent) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "showUserDepartments");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//modified for defect id:ESPRD00327996
		if (changeEvent.getNewValue().equals(
				MaintainContactManagementUIConstants.EMPTY_STRING)) {
			logCaseDataBean.setShowDepartments(false);
			logCaseDataBean.setShowUserDepartments(false);
			logCaseDataBean.setShowUsers(false);
			logCaseDataBean.setShowBusinessUnitFields(false);//added for defect
															 // id:ESPRD00327996
		}
		//modified for defect id:ESPRD00327996
		else if (changeEvent
				.getNewValue()
				.equals(
						MaintainContactManagementUIConstants.LOGCASE_ROUTING_SHOW_FIELD_USERS_CODE)) {
			logCaseDataBean.setShowDepartments(false);
			logCaseDataBean.setShowUserDepartments(false);
			logCaseDataBean.setShowBusinessUnitFields(false);//added for defect
															 // id:ESPRD00327996
			logCaseDataBean.setShowUsers(false);//   added for defect
												// id:ESPRD00327996
		}
		//modified for defect id:ESPRD00327996
		else if (changeEvent
				.getNewValue()
				.equals(
						MaintainContactManagementUIConstants.LOGCASE_ROUTING_SHOW_FIELD_WORK_UNIT_CODE)) {
			logCaseDataBean.setShowDepartments(true);
			//Added for defect ESPRD00510702 starts
			setAllDepartmentsList();
			//Defect ESPRD00510702 ends
			logCaseDataBean.setShowUsers(false);
			logCaseDataBean.setShowUserDepartments(false);
			logCaseDataBean.setShowBusinessUnitFields(false);//added for defect
															 // id:ESPRD00327996
		}
		//    added for defect ESPRD00327996
		else if (changeEvent
				.getNewValue()
				.equals(
						MaintainContactManagementUIConstants.LOGCASE_ROUTING_SHOW_FIELD_BUSINESS_UNIT_CODE)) {
			logCaseDataBean.setShowBusinessUnitFields(true);
			logCaseDataBean.setShowDepartments(false);
			logCaseDataBean.setShowUsers(false);
			logCaseDataBean.setShowUserDepartments(false);
		}
		//EOF ESPRD00327996
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "showUserDepartments");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to show the user in JSP
	 * 
	 * @param changeEvent
	 *            holds the user event
	 * @return String
	 */
	public String showUser(ValueChangeEvent changeEvent) {
		String deptSK = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (changeEvent.getNewValue()!=null && !isNullOrEmptyField(changeEvent.getNewValue().toString()))//ESPRD00327996
		{
			deptSK = changeEvent.getNewValue().toString();
			Long deptSLLong = new Long(deptSK);
			getRoutingUsers(deptSLLong);
			logCaseDataBean.setShowUsers(true);
			logCaseDataBean.setShowUserDepartments(false);
		}//added for defeft ESPRD00327996
		else {
			if (logCaseDataBean.getRoutingUserList() != null) {
				logCaseDataBean.getRoutingUserList().clear();
				logCaseDataBean.setShowUsers(false);
			}
		}
		//EOF defect ID ESPRD00327996
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to show the departments in JSP
	 * 
	 * @param changeEvent
	 *            holds the user event
	 * @return String
	 */
	public String showDept(ValueChangeEvent changeEvent) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (changeEvent.getNewValue() != null
				&& !isNullOrEmptyField(changeEvent.getNewValue().toString())) {
			logCaseDataBean.getRoutingDeptList().clear();
			String userSK = changeEvent.getNewValue().toString();
			log.debug("++Selected UserSK is $$ " + userSK);
			List deptsList = setDepartmentsList(userSK, true);
			//***Modified for DepartmentUser heap dump issue.**
			//***unnecessary call to db once more avoided**
			/// Added for defect ESPRD00510702 starts
			//ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			try {
				/*List deptsList = contactMaintenanceDelegate
						.getDepartmentUsers(Long.valueOf(userSK));*/
			    //CRM Heapdump_FIX for DepartmentUsers
				/* FIND BUGS_FIX */
				if (deptsList != null
						&& deptsList.size() == MaintainContactManagementUIConstants.ONE) {
					logCaseDataBean.setDisableRoutingUserworkunitName(true);
					//DepartmentUser deptUser = (DepartmentUser) deptsList.get(0);
					Object[] departMentObj=(Object[]) deptsList.get(0);
					BigDecimal deptmntSK = null;
					deptmntSK = (BigDecimal)departMentObj[0];
					FacesContext context = FacesContext.getCurrentInstance();
					HttpSession httpSession = (HttpSession) context
							.getExternalContext().getSession(true);
					/*httpSession.setAttribute("RoutingUserworkunit", deptUser
							.getDepartmentSK().toString());
					logCaseDataBean.getRoutingVO().setUserDepartment(
							deptUser.getDepartmentSK().toString());*/
					httpSession.setAttribute("RoutingUserworkunit", deptmntSK.toString());
					logCaseDataBean.getRoutingVO().setUserDepartment(deptmntSK.toString());
					log.debug("++deptmntSK--************************************"+ deptmntSK);
				} else {
					logCaseDataBean.setDisableRoutingUserworkunitName(false);
					logCaseDataBean.getRoutingVO().setUserDepartment(
							"Please Select One");
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			///defect ESPRD00510702 ends
			logCaseDataBean.setShowUserDepartments(true);
			logCaseDataBean.setShowUsers(false);
		} else { //added fr ESPRD00327996
			logCaseDataBean.setRoutingDeptList(new ArrayList());
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	protected void openCaseInAddMode(String caseEntity) {

		int separatorIndex = caseEntity
				.indexOf(ContactManagementConstants.COLON_SEPARATOR);
		commonNotesControllerBean = (CommonNotesControllerBean) getDataBean(ContactManagementConstants.COMMON_NOTES_CONTROLLER_BEAN);
		String entityId = caseEntity.substring(0, separatorIndex);

		String entityType = caseEntity.substring(separatorIndex + 1, caseEntity
				.length());
		if (logCaseDataBean.getAddiCaseEntityRowIndex() != -1) {
			logCaseDataBean.setAddiCaseEntityRowIndex(0);
		}
		resetLettersEditSection();
		getCaseTypes();
		getEntitiesForCase(entityId, entityType, false, true);
		logCaseDataBean.getCustomFieldDOList().clear(); // Added for the PanelGrid Fix
		populateInitialDataForADD();

		commonNotesControllerBean.showNotes();

		/** Added for saving notes and common contacts refs */
		copyCommonEntitiesRefs();
	}

	/**
	 * @return Returns the receiveMessage.
	 * @throws MemberBusinessException
	 */
	public String getReceiveMessage() throws MemberBusinessException {
		String result = "";
		try {
			log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getReceiveMessage");
			logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
			commonNotesControllerBean = (CommonNotesControllerBean) getDataBean(ContactManagementConstants.COMMON_NOTES_CONTROLLER_BEAN);
			FacesContext fc = FacesContext.getCurrentInstance();
			String caseSk = null;
			String entityType = null;
			String entityID = null;
			Long caseNum = null;
			boolean statusflag = false; //Added for defect ESPRD00675983

			String caseAction = null;
			String caseEntityDet = null;
			String inqAbtCaseEntityDet = null;

			Map appMap = null;
			if (fc.getExternalContext().getApplicationMap() != null) {
				String val1 = (String) fc.getExternalContext()
						.getApplicationMap().get("inqEntitySearch");
				log.debug("val1 is:::::mk:" + val1);
				logCaseDataBean.setCursorFocusInquiry(val1);
				fc.getExternalContext().getApplicationMap().put(
						"inqEntitySearch", "refreshing");
			}
			LogCaseHelper helper = new LogCaseHelper();
			if (fc.getExternalContext().getRequestMap() != null) {

				Object object = fc.getExternalContext().getRequestMap().get(
						"CaseDetails");
				logCaseDataBean.setCursorFocusValue(MaintainContactManagementUIConstants.EMPTY_STRING); // Added for the defect ESPRD00697332_14DEC2011
				log.debug("Message1");
				String caseEntity = (String) fc.getExternalContext()
						.getRequestMap().get("MaintainSearchId");
				log.debug("MaintainSearchId:" + caseEntity);
				String caseInquiryEntity = (String) fc.getExternalContext()
						.getRequestMap().get("MaintainInquirySearchId");
				log.debug("MaintainInquirySearchId:" + caseInquiryEntity);
				if (caseEntity != null) {
					log.debug("validate the maintain entity"
							+ caseEntity);
					statusflag = true; //Added for defect ESPRD00677723
					helper.restoreCaseState();
					openCaseInAddMode(caseEntity);
					logCaseDataBean.setCaseEntityDetails(caseEntity);
					logCaseDataBean.setResetCaseInAddModeFlag(Boolean.FALSE);
					logCaseDataBean.setPageFocusId("portletHeaderFocus");
				} else if (caseInquiryEntity != null) {

					if (!ContactManagementConstants.STATUS_CLOSED
							.equalsIgnoreCase(logCaseDataBean
									.getCaseDetailsVO().getStatus())) {
						getInquiryAboutDetails(caseInquiryEntity);

					}

					/** Added for notes */
					restoreCommonEntitiesRefs();

				}

				if (object instanceof CommonCMCaseDetailsVO) {
					
					//for ESPRD00741245
					logCaseDataBean.setPageFocusId("portletHeaderFocus");
					logCaseDataBean.setCursorFocusValue("LOGCASESTATUS");
					
					log.debug("Message2");
					CommonCMCaseDetailsVO detailsVO = (CommonCMCaseDetailsVO) object;
					log
							.debug(" inside getCaseDetails() --  CommonCMCaseDetailsVO from Case Search is ## "
									+ detailsVO);
					log.debug("Message3");

					if (detailsVO != null) {
						log.debug("Message4");

						caseSk = detailsVO.getCaseSK();
						log.debug("++CaseSk from Case search @ LogCaseController bean is ## "
										+ detailsVO.getCaseSK());

						entityType = detailsVO.getEntityType();
						log.debug("++Entity Type from Case search @ LogCaseController bean is ## "
										+ entityType);
						entityID = detailsVO.getEntityID();
						log.debug("++Entity ID from Case search @ LogCaseController bean is ## "
										+ entityID);
						caseAction = detailsVO.getActionCode();

						log.debug("++Action code from Case search @"
								+ caseAction);

						if (caseSk != null) {
							statusflag = true; //Added for defect ESPRD00675983
							logCaseDataBean.setActionCode(caseAction);
							loadLogCasePageWithCaseSk(caseSk);
							//modified for defect ESPRD00788749
							collapseSections();
						}
					}
				}
				/** for Global case Search */
				Object globalObject = fc.getExternalContext().getRequestMap()
						.get("GlobalCaseSearchResult");
				log.debug("MessageGlobal - 1");
				if (globalObject instanceof CommonCMCaseDetailsVO) {
					
					//for ESPRD00741245
					logCaseDataBean.setPageFocusId("portletHeaderFocus");
					log.debug("MessageGlobal - 2");
					CommonCMCaseDetailsVO globalCaseDetailsVO = (CommonCMCaseDetailsVO) globalObject;
					log
							.debug(" inside getCaseDetails() --  CommonCMCaseDetailsVO from G Case Search is ## "
									+ globalCaseDetailsVO);
					log.debug("Message3");
					if (globalCaseDetailsVO != null) {
						log.debug("Message4");

						caseSk = globalCaseDetailsVO.getCaseSK();
						log
								.debug("CaseSk from G Case search @ LogCaseController bean is ## "
										+ globalCaseDetailsVO.getCaseSK());
						entityType = globalCaseDetailsVO.getEntityType();
						log
								.debug("Entity Type from G Case search @ LogCaseController bean is ## "
										+ entityType);
						entityID = globalCaseDetailsVO.getEntityID();
						log
								.debug("Entity ID from G Case search @ LogCaseController bean is ## "
										+ entityID);
						caseAction = globalCaseDetailsVO.getActionCode();
						log.debug("Action code from G Case search @"
								+ caseAction);
						//Modified for defect ESPRD00600420 starts
						if (caseSk != null) {

							helper.restoreCaseState();
							statusflag = true; //Added for defect ESPRD00675983
							logCaseDataBean.setCaseSK(caseSk);
							logCaseDataBean.setActionCode(caseAction);

							//logCaseDataBean.setEntityType(entityType);//moved
							// down after GetCaseRecord for defect ESPRD00560416
							caseNum = Long.valueOf(caseSk);
							logCaseDataBean.setFromIPC(true);
							log.debug("After long convertion caseSK is ##**## "
									+ caseNum);
							logCaseDataBean.setCancelLinkUpdate(true);

							getCaseTypes();
							//Modified for defect ESPRD00560416 starts
							//getEntities(entityID, entityType, false,
							// true);//moved down after GetCaseRecord for defect
							// ESPRD00560416
							populateInitialDataForUpdate();

							CaseRecord caseRecord = getCaseRecord(caseNum);
							Set ss = caseRecord.getCaseCommonEntityCrossRefs();
							String entId = "";
							String entType = "";
							for (Iterator itr = ss.iterator(); itr.hasNext();) {
								CaseCommonEntityCrossRef xref = (CaseCommonEntityCrossRef) itr
										.next();
								if (xref.getCaseCRContactReasonCode()
										.equalsIgnoreCase("P")) {
									entId = xref.getCommonEntitySK().toString();
									entType = xref.getCommonEntityTypeCode();
								}

							}
							log.debug(" cmlogcase controllerbean ::entId::"
											+ entId);
							log.debug(" cmlogcase controllerbean ::entType::"
											+ entType);
							logCaseDataBean.setEntityType(entType);
							getEntities(entId, entType, false, true);
							//defect ESPRD00560416 ends

							getCFElementsInUpdate();

							commonNotesControllerBean.showNotes();

							/** Added By Madhurima */
							copyCommonEntitiesRefs();
							
							//modified for defect ESPRD00788749
							collapseSections();

						}// defect ESPRD00600420 ends
					}
				}
				/** End of GCS */

				log.debug("MessageEntity01");
				if (fc.getExternalContext().getRequestMap().get(
						"CaseEntityDetails") != null) {
					//Modified for defect ESPRD00743381 starts
					logCaseDataBean.setNavigatedToCorrespondence(false);
					//defect ESPRD00743381 ends
					//Modified for defect ESPRD00552258 starts
					helper.restoreCaseState();
					//defect ESPRD00552258 ends
					caseEntityDet = (String) fc.getExternalContext()
							.getRequestMap().get("CaseEntityDetails");
					logCaseDataBean.setCaseEntityDetails(caseEntityDet);
					logCaseDataBean.setResetCaseInAddModeFlag(Boolean.FALSE);
					log.debug("MessageEntity02");
				}else if(logCaseDataBean.isResetCaseInAddModeFlag() && !isNullOrEmptyField(logCaseDataBean.getCaseEntityDetails())){
					caseEntityDet=logCaseDataBean.getCaseEntityDetails();
					logCaseDataBean.setResetCaseInAddModeFlag(Boolean.FALSE);
				}
				log.debug("MessageEntity2");
				if (caseEntityDet != null && !caseEntityDet.equals("")) {
					String menu = null;
					Map caseTypeSKMap = null;
					String caseTypeSK = null;
					String menuCode = null;
					log.debug("Case Entity details from Entity Search is ## "
							+ caseEntityDet);
					logCaseDataBean.setCommonEntitySK(null);//ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
					String[] entDetailsArr = caseEntityDet.split(":");
					if (entDetailsArr != null) {
						helper.restoreCaseState();
						statusflag = true; //Added for defect ESPRD00675983

						if (entDetailsArr[0] != null
								&& !MaintainContactManagementUIConstants.EMPTY_STRING
										.equals(entDetailsArr[0])) {

							entityID = entDetailsArr[0];
							log.debug("Entity ID from Entity search $$ "
									+ entityID);

							logCaseDataBean.setEntityID(entityID);
						}
						if (entDetailsArr[1] != null
								&& !MaintainContactManagementUIConstants.EMPTY_STRING
										.equals(entDetailsArr[1])) {

							entityType = entDetailsArr[1];
							log.debug("Entity Type is from Entity search $$ "
									+ entityType);
							logCaseDataBean.setEntityType(entityType);
						}
						/** Added For the Defect ID ESPRD00336197 */
						if (entDetailsArr.length > 2) {
							if (entDetailsArr[2] != null
									&& !MaintainContactManagementUIConstants.EMPTY_STRING
											.equals(entDetailsArr[2])) {
								menuCode = entDetailsArr[2];
								logCaseDataBean.setMenuCode(menuCode);
							}
						}
						/** Ends */
						//ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
						String commonEntitySK = null;
						if (entDetailsArr.length > 3) {
							if (!MaintainContactManagementUIConstants.EMPTY_STRING
									.equals(entDetailsArr[3])) {
								commonEntitySK = entDetailsArr[3];
								logCaseDataBean
										.setCommonEntitySK(commonEntitySK);
							}
						} //EOf ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010

						if (entityID != null && entityType != null) {
							logCaseDataBean.setCancelLinkUpdate(false);
							getCaseTypes();
							try{
							getEntities(entityID, entityType, false, false);
							}catch(Exception e){
								e.printStackTrace();
							}
							populateInitialDataForADD();
							logCaseDataBean.setPageFocusId("portletHeaderFocus");
							if (logCaseDataBean.getAttachmentVOList() != null) {
								logCaseDataBean.getAttachmentVOList().clear();
							}
							log.debug("logCaseDataBean.setCaseAttachHiddenID");
							logCaseDataBean
									.setCaseAttachmentsHidden(MaintainContactManagementUIConstants.PLUS);

							/** Added For the Defect ID ESPRD00336197 */

							if ("AddNewAppeals".equals(menuCode)) {
								/*System.err.println("AddNewAppeals");
								caseTypeSKMap = logCaseDataBean
										.getCaseTypeMemSKAndShortDes();
								System.err.println(" caseTypeSKMap"
										+ caseTypeSKMap);*/
								//hash map performance issue code change
								CaseType caseType= getCaseTypeFrmDosList(null,"A2");
								if (caseType!=null) {
									caseTypeSK = caseType.getCaseTypeSK().toString();
									logCaseDataBean.getCaseDetailsVO()
											.setCaseType(caseTypeSK);
									logCaseDataBean.setShowAppealScreen(false);
									logCaseDataBean
											.setShowBCCPTypeScreen(false);
									logCaseDataBean.setShowARSTypeScreen(false);
									logCaseDataBean.setShowDDUTypeScreen(false);
									logCaseDataBean
											.setShowMQIPTypeScreen(false);
									logCaseDataBean.setShowFCRTypeScreen(false);
									logCaseDataBean
											.setShowNewARSTypeScreen(false);
									logCaseDataBean
											.setShowHOCRTypeScreen(false);
									logCaseDataBean
											.setShowAcuityRateSettingTypeScreen(false);
									logCaseDataBean
											.setShowFPPRRTypeScreen(false);
									logCaseDataBean.setShowFATypeScreen(false);
									logCaseDataBean.setShowCBTypeScreen(false);
									logCaseDataBean.setShowFCSTypeScreen(false);
									logCaseDataBean
											.setShowNFQATypeScreen(false);
									logCaseDataBean
											.setShowNewNonARSTypeScreen(false);
									logCaseDataBean
											.setShowMemAppTypeScreen(true);
									/*logCaseDataBean
											.setShowProviderAppTypeScreen(false);*/
									logCaseDataBean
											.setShowCommonTypeScreen(true);
									//added for defect ESPRD00834261
									populateNotifyViaAlertList(caseTypeSK);
									showCaseType(caseTypeSK);
									getCaseTypeDetails(caseTypeSK);
									
									FacesContext context = FacesContext
											.getCurrentInstance();
									HttpSession httpSession = (HttpSession) context
											.getExternalContext().getSession(
													true);

									httpSession.setAttribute("DDUstatus", "PD");
								}
							} else if ("Add Provider Appeals".equals(menuCode)) {

								/*caseTypeSKMap = logCaseDataBean
										.getCaseTypeMemSKAndShortDes();
								System.err.println(" caseTypeSKMap11"
										+ caseTypeSKMap);*/
								//hash map performance issue code change
								CaseType caseType= getCaseTypeFrmDosList(null,"A1");
								if (caseType!=null) {
									caseTypeSK =  caseType.getCaseTypeSK().toString();
									logCaseDataBean.getCaseDetailsVO()
											.setCaseType(caseTypeSK);
									
									logCaseDataBean.setShowAppealScreen(false);
									logCaseDataBean
											.setShowBCCPTypeScreen(false);
									logCaseDataBean.setShowARSTypeScreen(false);
									logCaseDataBean.setShowDDUTypeScreen(false);
									logCaseDataBean
											.setShowMQIPTypeScreen(false);
									logCaseDataBean.setShowFCRTypeScreen(false);
									logCaseDataBean
											.setShowNewARSTypeScreen(false);
									logCaseDataBean
											.setShowHOCRTypeScreen(false);
									logCaseDataBean
											.setShowAcuityRateSettingTypeScreen(false);
									logCaseDataBean
											.setShowFPPRRTypeScreen(false);
									logCaseDataBean.setShowFATypeScreen(false);
									logCaseDataBean.setShowCBTypeScreen(false);
									logCaseDataBean.setShowFCSTypeScreen(false);
									logCaseDataBean
											.setShowNFQATypeScreen(false);
									logCaseDataBean
											.setShowNewNonARSTypeScreen(false);
									/***
									 * ShowMemAppTypeScreen is reused for Continue Button display
									 */
									logCaseDataBean
											.setShowMemAppTypeScreen(true);
									/*logCaseDataBean
											.setShowProviderAppTypeScreen(true);*/
									logCaseDataBean
											.setShowCommonTypeScreen(true);
									//added for defect ESPRD00834261
									populateNotifyViaAlertList(caseTypeSK);
									showCaseType(caseTypeSK);
									getCaseTypeDetails(caseTypeSK);
									
									logCaseDataBean.getCaseDetailsVO()
											.setStatus("PD");
									

								}
							}
							/** Ends Defect ID ESPRD00336197 */

							/*if ("AddNewAppeals".equals(menu)) {
								System.err.println("add menberappeal");
								caseTypeSKMap = logCaseDataBean
										.getCaseTypeSKAndShortDesc();
								System.err.println(" caseTypeSKMap111"
										+ caseTypeSKMap);
								if (!caseTypeSKMap.isEmpty()) {
									if (caseTypeSKMap
											.containsKey("Member Appeal")) {
										caseTypeSK = (String) caseTypeSKMap
												.get("Member Appeal");
										System.err.println("caseTypeSK 111"
												+ caseTypeSK);
										logCaseDataBean.getCaseDetailsVO()
												.setCaseType(caseTypeSK);
										getCaseTypeDetails(caseTypeSK);

										//ADDED FOR THE DEFECT ESPRD00629201

										FacesContext context = FacesContext
												.getCurrentInstance();
										System.err.println("for testing1");
										HttpSession httpSession = (HttpSession) context
												.getExternalContext()
												.getSession(true);

										httpSession.setAttribute("DDUstatus",
												"PD");

									}
								}
							} else if ("Add Provider Appeals".equals(menu)) {
								System.err.println("add providerappeal");
								caseTypeSKMap = logCaseDataBean
										.getCaseTypeSKAndShortDesc();
								System.err.println(" caseTypeSKMap1111"
										+ caseTypeSKMap);
								if (!caseTypeSKMap.isEmpty()) {
									if (caseTypeSKMap
											.containsKey("Provider Appeal")) {
										caseTypeSK = (String) caseTypeSKMap
												.get("Provider Appeal");
										System.err.println("caseTypeSK 1111"
												+ caseTypeSK);
										logCaseDataBean.getCaseDetailsVO()
												.setCaseType(caseTypeSK);
										getCaseTypeDetails(caseTypeSK);

										//ADDED FOR THE DEFECT ESPRD00629201
										logCaseDataBean.getCaseDetailsVO()
												.setStatus("PD");

									}
								}
							} else if ("none".equals(menu)) {

							}*/
							commonNotesControllerBean.showNotes();
							/** Added By Madhurima */
							copyCommonEntitiesRefs();
							
							//modified for defect ESPRD00788749
							collapseSections();
						}
					}
				}

				if (fc.getExternalContext().getRequestMap().get("MyTaskCaseSk") != null) {
					Object myTaskObj = fc.getExternalContext().getRequestMap()
							.get("MyTaskCaseSk");
					log.debug("MyTaskCaseSk02");
					if (myTaskObj instanceof CommonCMCaseDetailsVO) {
						
						//for ESPRD00741245
						logCaseDataBean.setPageFocusId("portletHeaderFocus");
						log.debug("Message2");
						CommonCMCaseDetailsVO myTaskDetailsVO = (CommonCMCaseDetailsVO) myTaskObj;
						log
								.debug(" inside getCaseDetails() --  CommonCMCaseDetailsVO from MyTask is ## "
										+ myTaskDetailsVO);
						log.debug("Message3");

						if (myTaskDetailsVO != null) {
							log.debug("Message4");
							caseSk = myTaskDetailsVO.getCaseSK();
							log.debug("CaseSk from MyTask @"
									+ myTaskDetailsVO.getCaseSK());
							entityType = myTaskDetailsVO.getEntityType();

							entityID = myTaskDetailsVO.getEntityID();

							caseAction = myTaskDetailsVO.getActionCode();
							log.debug("Action code from MyTask @" + caseAction);
							if (caseSk != null) {
								helper.restoreCaseState();
								statusflag = true; //Added for defect
												   // ESPRD00675983
								logCaseDataBean.setActionCode(caseAction);
								logCaseDataBean.setEntityID(entityID);
								logCaseDataBean.setEntityType(entityType);
								logCaseDataBean.setFromIPC(true);
								caseNum = Long.valueOf(caseSk);
								log
										.debug("After long convertion caseSK is ##**## "
												+ caseNum);
								logCaseDataBean.setCancelLinkUpdate(true);
								getCaseTypes();
								populateInitialDataForUpdate();
								//for entity type TD. ESPRD00740424
								logCaseDataBean.setCaseSK(caseSk);
								CaseRecord caseRecord=getCaseRecord(caseNum);
								log.debug("case sk $$$$$$$$$$$$"+caseNum);
								//for Entity type TD.
								Set ss = caseRecord.getCaseCommonEntityCrossRefs();
								String entId = "";
								String entType = "";
								for (Iterator itr = ss.iterator(); itr.hasNext();) {
									CaseCommonEntityCrossRef xref = (CaseCommonEntityCrossRef) itr
											.next();
									if (xref.getCaseCRContactReasonCode()
											.equalsIgnoreCase("P")) {
										entId = xref.getCommonEntitySK().toString();
										entType = xref.getCommonEntityTypeCode();
									}

								}
								log.debug("entType $$$$$$$$$$$$"+entType);
								log.debug("entId $$$$$$$$$"+entId);
								getEntities(entId, entType, true, false);
								populateInitialDataForUpdate();

								getCFElementsInUpdate();
								commonNotesControllerBean.showNotes();
								/** Added By Madhurima */
								copyCommonEntitiesRefs();
								
								//for defect ESPRD00880351
								collapseSections();
							}
						}
					}
				}
				if (fc.getExternalContext().getRequestMap().get(
						"MyTaskClaimCaseSK") != null) {
					String myTaskClaimCaseDet = (String) fc
							.getExternalContext().getRequestMap().get(
									"MyTaskClaimCaseSK");
					log.debug("MyTaskCaseSk02");
					if (myTaskClaimCaseDet != null) {
						//for ESPRD00741245
						logCaseDataBean.setPageFocusId("portletHeaderFocus");
						log.debug("Message2");
						String[] myTaskClaimArr = myTaskClaimCaseDet.split(":");
						if (myTaskClaimArr != null) {
							log.debug("Message4");
							if (myTaskClaimArr[0] != null
									&& !MaintainContactManagementUIConstants.EMPTY_STRING
											.equals(myTaskClaimArr[0])) {
								caseSk = myTaskClaimArr[0];
								log.debug("CaseSk from myTaskClaimArr $$ "
										+ caseSk);
							}
							if (myTaskClaimArr[1] != null
									&& !MaintainContactManagementUIConstants.EMPTY_STRING
											.equals(myTaskClaimArr[1])) {
								entityID = myTaskClaimArr[1];
								log.debug("entityID from myTaskClaimArr $$ "
										+ entityID);
							}
							if (myTaskClaimArr[2] != null
									&& !MaintainContactManagementUIConstants.EMPTY_STRING
											.equals(myTaskClaimArr[2])) {
								entityType = myTaskClaimArr[2];
								log.debug("entityType from myTaskClaimArr $$ "
										+ entityType);
							}
							if (caseSk != null) {
								helper.restoreCaseState();
								statusflag = true; //Added for defect
												   // ESPRD00675983
								logCaseDataBean.setActionCode("CaseUpdate");
								logCaseDataBean.setEntityID(entityID);
								logCaseDataBean.setEntityType(entityType);
								logCaseDataBean.setFromIPC(true);
								caseNum = Long.valueOf(caseSk);
								log
										.debug("After long convertion caseSK is ##**## "
												+ caseNum);
								logCaseDataBean.setCancelLinkUpdate(true);
								getCaseTypes();
								populateInitialDataForUpdate();
								CaseRecord caseRecord=getCaseRecord(caseNum);
								//for Entity type TD. ESPRD00740424
								Set ss = caseRecord.getCaseCommonEntityCrossRefs();
								String entId = "";
								String entType = "";
								for (Iterator itr = ss.iterator(); itr.hasNext();) {
									CaseCommonEntityCrossRef xref = (CaseCommonEntityCrossRef) itr
											.next();
									if (xref.getCaseCRContactReasonCode()
											.equalsIgnoreCase("P")) {
										entId = xref.getCommonEntitySK().toString();
										entType = xref.getCommonEntityTypeCode();
									}

								}
								getEntities(entId, entType, true, false);
								populateInitialDataForUpdate();

								getCFElementsInUpdate();
								commonNotesControllerBean.showNotes();
								/** Added By Madhurima */
								copyCommonEntitiesRefs();
								// commented the fix for 543375 exists in MyTaskControllerBean starts								
								//added for the defect 543375
								/*if (logCaseDataBean.getCaseDetailsVO()
										.getAssignedTo() != null
										&& logCaseDataBean.getCaseDetailsVO()
												.getAssignedTo() != "") {
									if (logCaseDataBean.getRoutingInfoList() != null
											&& logCaseDataBean
													.getRoutingInfoList()
													.size() > 0) {
										CMRoutingVO cMRoutingVO = (CMRoutingVO) logCaseDataBean
												.getRoutingInfoList()
												.get(
														logCaseDataBean
																.getRoutingInfoList()
																.size() - 1);
										cMRoutingVO
												.setRoutedToName(logCaseDataBean
														.getCaseDetailsVO()
														.getAssignedTo());
									}
								}*/
								//for defect ESPRD00880351
								collapseSections();
							}
						}
					}
				}
				if (fc.getExternalContext().getRequestMap().get(
						"inqAbtCaseEntityDetails") != null) {
					inqAbtCaseEntityDet = (String) fc.getExternalContext()
							.getRequestMap().get("inqAbtCaseEntityDetails");
					log.debug("inqAbtMessageEntity02");
				}
				log.debug("inqAbtMessageEntity2");
				if (inqAbtCaseEntityDet != null
						&& !inqAbtCaseEntityDet.equals("")) {
					log.debug("inqAbtMessageEntity3");
					getInquiryAboutDetails(inqAbtCaseEntityDet);
					executeBRCON307(); //ESPRD00517164_UC-PGM-CRM-18_06SEP2010
					/** Added for notes (Added By Madhurima) */
					restoreCommonEntitiesRefs();
				}

				Object edmsObj = fc.getExternalContext().getRequestMap().get(
						"logCaseEDMSSearchResultsList");
				if (edmsObj == null) {
					log.debug("Not an instance");
				} else {
					//start ESPRD00582020
					log.info("++if from EDMS");

					if (logCaseDataBean.getCaseDetailsVO() != null) {
						log.debug("Message4");
						if (logCaseDataBean.getCaseDetailsVO().getCaseSK() != null
								&& !logCaseDataBean.getCaseDetailsVO()
										.getCaseSK().equals("")) {
							caseSk = logCaseDataBean.getCaseDetailsVO()
									.getCaseSK().toString();
							log.debug("++CaseSk 1 " + caseSk);
						} else if (logCaseDataBean.getCaseRegardingVO() != null
								&& logCaseDataBean.getCaseRegardingVO()
										.getCaseRecordNumber().equals("")) {
							log.debug("++CaseSk 2 " + caseSk);
							caseSk = logCaseDataBean.getCaseRegardingVO()
									.getCaseRecordNumber();
						}

						log.debug("++Action code from Case search @"
								+ caseAction);

						if (caseSk != null) {

							helper.restoreCaseState();
							logCaseDataBean.setCaseSK(caseSk);
							logCaseDataBean.setActionCode("CaseUpdate");

							//logCaseDataBean.setEntityType(entityType);//moved
							// down after GetCaseRecord for defect ESPRD00560416
							caseNum = Long.valueOf(caseSk);
							logCaseDataBean.setFromIPC(true);
							log.debug("After long convertion caseSK is ##**## "
									+ caseNum);
							logCaseDataBean.setCancelLinkUpdate(true);

							getCaseTypes();
							//Modified for defect ESPRD00560416 starts
							//getEntities(entityID, entityType, false,
							// true);//moved down after GetCaseRecord for defect
							// ESPRD00560416
							populateInitialDataForUpdate();

							CaseRecord caseRecord = getCaseRecord(caseNum);
							Set ss = caseRecord.getCaseCommonEntityCrossRefs();
							String entId = "";
							String entType = "";
							for (Iterator itr = ss.iterator(); itr.hasNext();) {
								CaseCommonEntityCrossRef xref = (CaseCommonEntityCrossRef) itr
										.next();
								if (xref.getCaseCRContactReasonCode()
										.equalsIgnoreCase("P")) {
									entId = xref.getCommonEntitySK().toString();
									entType = xref.getCommonEntityTypeCode();
								}

							}
							log.debug(" cmlogcase controllerbean ::entId::"
											+ entId);
							log.debug(" cmlogcase controllerbean ::entType::"
											+ entType);
							logCaseDataBean.setEntityType(entType);
							getEntities(entId, entType, false, true);
							//defect ESPRD00560416 ends

							getCFElementsInUpdate();

							commonNotesControllerBean.showNotes();

							/** Added By Madhurima */
							copyCommonEntitiesRefs();
							//for defect ESPRD00880351
							collapseSections();
						}
					}

					//ESPRD00582020 ends
					logCaseDataBean
							.setCursorFocusValue("openDocumentRepositoryId");
					logCaseDataBean.setPageFocusId("caseAttachmentsHeader");
					//for ESPRD00747751
					logCaseDataBean.setCaseAttachmentsHidden("minus");
					EnterpriseEDMSSearchResultsVO searchResultsVO = new EnterpriseEDMSSearchResultsVO();
					try {

						List edmsResults = null;
						BeanUtils.copyProperties(searchResultsVO, edmsObj);
						if (searchResultsVO != null) {
							List attachmentList = null;

							edmsResults = searchResultsVO
									.getSearchResultsList();
							if(log.isDebugEnabled()){
							log.debug("EDMS search results size:"
									+ edmsResults.size());
							}
							attachmentList = getAttachmentList(edmsResults);
							commonNotesControllerBean.showNotes();
							restoreCommonEntitiesRefs();
						}
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}
				}

				// Start - Added the code for the defect ESPRD00688792
				/*Object obj1 = fc.getExternalContext().getRequestMap().get(
						"CorrespondenceResultsList");
				if (obj1 instanceof CorrespondenceRecordVO) {
					CorrespondenceRecordVO crRecordVO = (CorrespondenceRecordVO) obj1;

					AssociatedCorrespondenceVO associateCRVO = getAssociateCorrespondenceVO(crRecordVO);
					logCaseDataBean.getExistingCRInfoList().add(associateCRVO);
					logCaseDataBean.setShowExistingCRDataTable(true);
					logCaseDataBean.setShowExistingCaseRecordsDataTable(true);//ESPRD00610465
																			  // defect
				}*/
				log.debug("++logCaseDataBean.isNavigatedToCorrespondence() " 
												+ logCaseDataBean.isNavigatedToCorrespondence());
				if(logCaseDataBean.isNavigatedToCorrespondence()){
					if (logCaseDataBean.getCaseDetailsVO() != null) {
						log.debug("Message4@@@@@@@@@@");
						if (logCaseDataBean.getCaseDetailsVO().getCaseSK() != null
								&& !logCaseDataBean.getCaseDetailsVO()
										.getCaseSK().equals("")) {
							caseSk = logCaseDataBean.getCaseDetailsVO()
									.getCaseSK().toString();
							log.debug("++CaseSk 1111111 " + caseSk);
						} else if (logCaseDataBean.getCaseRegardingVO() != null
								&& logCaseDataBean.getCaseRegardingVO()
										.getCaseRecordNumber().equals("")) {
							log.debug("++CaseSk 222222 " + caseSk);
							caseSk = logCaseDataBean.getCaseRegardingVO()
									.getCaseRecordNumber();
						}

						log.debug("++Action code from Case search @++++++"
								+ caseAction);

						if (caseSk != null) {

							helper.restoreCaseState();
							logCaseDataBean.setCaseSK(caseSk);
							logCaseDataBean.setActionCode("CaseUpdate");

							//logCaseDataBean.setEntityType(entityType);//moved
							// down after GetCaseRecord for defect ESPRD00560416
							caseNum = Long.valueOf(caseSk);
							logCaseDataBean.setFromIPC(true);
							log.debug("After long convertion caseSK is ##**##+++++++ "
									+ caseNum);
							logCaseDataBean.setCancelLinkUpdate(true);

							getCaseTypes();
							//Modified for defect ESPRD00560416 starts
							//getEntities(entityID, entityType, false,
							// true);//moved down after GetCaseRecord for defect
							// ESPRD00560416
							populateInitialDataForUpdate();

							CaseRecord caseRecord = getCaseRecord(caseNum);
							Set ss = caseRecord.getCaseCommonEntityCrossRefs();
							String entId = "";
							String entType = "";
							for (Iterator itr = ss.iterator(); itr.hasNext();) {
								CaseCommonEntityCrossRef xref = (CaseCommonEntityCrossRef) itr
										.next();
								if (xref.getCaseCRContactReasonCode()
										.equalsIgnoreCase("P")) {
									entId = xref.getCommonEntitySK().toString();
									entType = xref.getCommonEntityTypeCode();
								}

							}
							log.debug(" cmlogcase controllerbean ::entId::++++++"
											+ entId);
							log.debug(" cmlogcase controllerbean ::entType::+++++++"
											+ entType);
							logCaseDataBean.setEntityType(entType);
							getEntities(entId, entType, false, true);
							//defect ESPRD00560416 ends

							getCFElementsInUpdate();

							commonNotesControllerBean.showNotes();

							/** Added By Madhurima */
							copyCommonEntitiesRefs();
							//for defect ESPRD00880351
							collapseSections();
						}
					}
					logCaseDataBean.setNavigatedToCorrespondence(false);
				}
				
				// End - Added the code for the defect ESPRD00688792

				/**
				 * Added By Madhurima
				 */
				//Modified for the defect ESPRD00891861
				if (fc.getExternalContext().getRequestMap().get(
						"LetterGenerationForCase") != null) {
					String letterGenDet = (String) fc.getExternalContext()
							.getRequestMap().get("LetterGenerationForCase");
					logCaseDataBean.setCursorFocusValue("createLetterBtn");
					logCaseDataBean.setPageFocusId("caseLettersHeader");

					int colonSeparatorIndex = letterGenDet
							.indexOf(ContactManagementConstants.COLON_SEPARATOR);
					String letterGenSK = letterGenDet.substring(0,
							colonSeparatorIndex);
					log.debug("letter generation sk ----->" + letterGenSK);
					String caseSK = letterGenDet.substring(
							colonSeparatorIndex + 1, letterGenDet.length());
					log.debug("case  sk ----->" + caseSK);
					loadLogCasePageWithCaseSk(caseSK);
					setLetterGenerationDetails(letterGenSK);
					/** Added for saving notes and common contacts refs */
					restoreCommonEntitiesRefs();
				} else {
					log.error("LetterGenerationSKAdded null");
				}
				/**
				 * Ended By Madhurima
				 */
			}
			if (!(logCaseDataBean.getCaseDetailsVO() != null && logCaseDataBean
					.getCaseDetailsVO().getCaseSK() != null)) {
				logCaseDataBean.setDisplayAddNoteLink(true);
			}

			//Added for defect ESPRD00299552

			if (logCaseDataBean.isShowAddCaseEvents()
					|| logCaseDataBean.isShowEditCaseEvents()) {
				if (logCaseDataBean.getCaseEventsVO() != null) {
					if (logCaseDataBean.isDisableCaseEventFields()) {
						logCaseDataBean
								.getCaseEventsVO()
								.setAlertBasedOn(
										MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
						logCaseDataBean
								.getCaseEventsVO()
								.setSendAlertDaysCd(
										MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
						logCaseDataBean.getCaseEventsVO().setAfterOrBeforeCd(
								MaintainContactManagementUIConstants.EMPTY_STRING);
					}
				}
			}
			if (logCaseDataBean.isRenderedCaseStepsflag()) {
				if (logCaseDataBean.getCaseStepsVO() != null) {
					if (logCaseDataBean.isDisableFields()) {
						logCaseDataBean
								.getCaseStepsVO()
								.setAlertBasedOn(
										MaintainContactManagementUIConstants.EMPTY_STRING);
						logCaseDataBean
								.getCaseStepsVO()
								.setSendAlertDays(
										MaintainContactManagementUIConstants.EMPTY_STRING);
					}
				}
			}
			//EOF defect ESPRD00299552
			//Added for defect ESPRD00675983
			if (statusflag) {
				getCaseStatusList();
			}
			// Moved Down Inside the getCaseStatusList method for defect
			// ESPRD00675983.
			/*
			 * // Added for defect ESPRD00368025 Starts try{ String userId =
			 * getUserID(); String businessUnitDesc = null;
			 * ContactMaintenanceDelegate delegate = new
			 * ContactMaintenanceDelegate();
			 * 
			 * List buinessUnitDescs=delegate.getBuisnessUnitsDescs(userId);
			 * 
			 * if(buinessUnitDescs!=null) {
			 * 
			 * if(buinessUnitDescs.size()==1) { businessUnitDesc =
			 * (String)buinessUnitDescs.get(0); setCaseStatus(businessUnitDesc); }
			 * else { businessUnitDesc = ContactManagementConstants.AllOthers;
			 * setCaseStatus(businessUnitDesc); } } else { businessUnitDesc =
			 * ContactManagementConstants.AllOthers;
			 * setCaseStatus(businessUnitDesc); } }
			 * catch(LOBHierarchyFetchBusinessException e){ log.debug(e);
			 * log.error(e.getMessage(), e); } catch(Exception e){ log.debug(e);
			 * log.error(e.getMessage(), e); } // Added for defect ESPRD00368025
			 * Ends
			 */
			//*********Moved to showCaseType() for PERFORMANCE-FIX 
			// Start 
			//Added for defect ESPRD00299900
			/*if (logCaseDataBean.getCaseDetailsVO().getCaseType() != null) {

				if (logCaseDataBean
						.getCaseDetailsVO()
						.getCaseType()
						.trim()
						.equals(
								MaintainContactManagementUIConstants.EMPTY_STRING)) {
					//Comment for defect ESPRD00327122 and ESPRD00336108

					 Added code to Fix defect ESPRD00351920 Starts 
					setCaseEventTypeList(MaintainContactManagementUIConstants.EMPTY_STRING);
					 Added code to Fix defect ESPRD00351920 End 
				} else {
					Iterator itr = logCaseDataBean.getCaseTypeList().iterator();
					boolean isFound = false;
					while (itr.hasNext()) {
						SelectItem element = (SelectItem) itr.next();
						if (logCaseDataBean.getCaseDetailsVO().getCaseType()
								.equals(element.getValue())) {

							 Added code to Fix defect ESPRD00351920 Starts 
							setCaseEventTypeList(element.getLabel());
							 Added code to Fix defect ESPRD00351920 End 
							isFound = true;
							break;
						}
					}
					if (!isFound) {

						 Added code to Fix defect ESPRD00351920 Starts 
						setCaseEventTypeList(MaintainContactManagementUIConstants.EMPTY_STRING);
						 Added code to Fix defect ESPRD00351920 End 
					}
				}

			} else {

				 Added code to Fix defect ESPRD00351920 Starts 
				setCaseEventTypeList(MaintainContactManagementUIConstants.EMPTY_STRING);
				 Added code to Fix defect ESPRD00351920 End 
			}*/
			//End 
			//EOF ESPRD00299900

			// Added for defect ID ESPRD00308997
			String caseRecordNumber = logCaseDataBean.getCaseRegardingVO()
					.getCaseRecordNumber();
			if (caseRecordNumber == null) {
				logCaseDataBean.setShowCaseType(false);
			}
			String caseType = logCaseDataBean.getCaseDetailsVO().getCaseType();
			if (caseType == null) {
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);
				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);
			}
			//added for defects ESPRD00340194,ESPRD00307362
			//setting create letter link flag
			if (logCaseDataBean.getCaseRegardingVO() != null) {
				logCaseDataBean
						.setCreateLetterLinkFlag((logCaseDataBean
								.getCaseRegardingVO().getCaseRecordNumber() != null && !(ContactManagementConstants.EMPTY_STRING
								.equals(logCaseDataBean.getCaseRegardingVO()
										.getCaseRecordNumber().trim()))));
			} else {
				logCaseDataBean.setCreateLetterLinkFlag(false);
			}
			//EOF ESPRD00340194,ESPRD00307362
			if (logCaseDataBean.isShowAddCaseEvents()
					|| logCaseDataBean.isShowEditCaseEvents()) {
				if (logCaseDataBean.getCaseEventsVO().getStatusCd() != null
						&& logCaseDataBean
								.getCaseEventsVO()
								.getStatusCd()
								.equalsIgnoreCase(
										MaintainContactManagementUIConstants.CASE_EVENT_STATUS_TYPE))
					logCaseDataBean.getCaseEventsVO().setDispositionDateStr(
							ContactManagementConstants.EMPTY_STRING);
			}

			//UC-PGM-CRM-018_ESPRD00367808_18JAN10
			if (!logCaseDataBean.isCaseStatusSetFlag()) {
				setCaseStatusInUpdateMode();
			} //EOF UC-PGM-CRM-018_ESPRD00367808_18JAN10

			//UC-PGM-CRM-018_ESPRD00392654_25JAN10
			CaseSearchDataBean caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.CASE_SEARCH_DATA_BEAN);
			//System.err.println(" @@@@@@@@@@@@ " +
			// caseSearchDataBean.getShowCaseTempList().size());
			//Added for displaying the Page level error messages related to Associated Case in Log Case Page
			if(caseSearchDataBean.isSearchCaseValidationFlag())
			{
				logCaseDataBean.setLogCaseErrMsgFlag(true);
				caseSearchDataBean.setSearchCaseValidationFlag(false);
			}
			if (caseSearchDataBean.getShowCaseTempList().size() > 0) {

				//System.err.println("
				// logCaseDataBean.getShowCaseTempList().size() " +
				// caseSearchDataBean.getShowCaseTempList().size());
				Iterator itr = caseSearchDataBean.getShowCaseTempList()
						.iterator();
				List tempList = new ArrayList();
				CaseRecordSearchResultsVO caseRecordSearchResultsVO = null;
				AssociatedCaseVO associatedCaseVO = null;
				while (itr.hasNext()) {
					//System.err.println("inside
					// while...................................");
					caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) itr
							.next();
					associatedCaseVO = convertCaseRecordSearchResultsVOToAssociatedCaseVO(caseRecordSearchResultsVO);
					if (associatedCaseVO != null) {
						logCaseDataBean.getCaseRecordsAssoWithCaseList().add(
								associatedCaseVO);
						logCaseDataBean
								.setShowCaseRecordsAssoWithCaseDataTable(true);
					}

				}
				caseSearchDataBean.getShowCaseTempList().clear();
			}
			//end
			//Modified for defect ESPRD00516726 starts
			if (logCaseDataBean.getCaseStepsVO() != null
					&& (logCaseDataBean.isShowAddCaseSteps() || logCaseDataBean
							.isShowEditCaseSteps())) {
				if (logCaseDataBean.getCaseStepsVO().getNotifyViaAlert() != null
						&& !logCaseDataBean.getCaseStepsVO()
								.getNotifyViaAlert().trim().equals("")) {
					logCaseDataBean.setDisableFields(false);
				} else {
					logCaseDataBean.setDisableFields(true);
					logCaseDataBean.getCaseStepsVO().setAlertBasedOn(
							MaintainContactManagementUIConstants.EMPTY_STRING);
					logCaseDataBean.getCaseStepsVO().setSendAlertDays(
							MaintainContactManagementUIConstants.EMPTY_STRING);
					logCaseDataBean.getCaseStepsVO().setBeforeOrAfterInd(
							MaintainContactManagementUIConstants.EMPTY_STRING);
					logCaseDataBean.getCaseStepsVO().setAlertBasedOnStr(
							MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
					logCaseDataBean.getCaseStepsVO().setSendAlertDaysStr(
							MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
					
				}
			}
			//defect ESPRD00516726 ends
			//Added for defect ESPRD00510702 starts
			if (logCaseDataBean.isShowUserDepartments()
					&& logCaseDataBean.isDisableRoutingUserworkunitName()) {
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession httpSession = (HttpSession) context
						.getExternalContext().getSession(true);
				String deptSk = (String) httpSession
						.getAttribute("RoutingUserworkunit");
				if (deptSk != null) {
					logCaseDataBean.getRoutingVO().setUserDepartment(deptSk);
				}
				httpSession.removeAttribute("RoutingUserworkunit");
			}
			//defect ESPRD00510702 ends
			//Added for defect ESPRD00533952 starts
			log.debug("++ logCaseDataBean.isDisableScreenField()::"
					+ logCaseDataBean.isDisableScreenField());
			if (logCaseDataBean.isDisableScreenField()
					&& logCaseDataBean.getCaseDetailsVO() != null
					&& logCaseDataBean.getCaseDetailsVO().getStatus()
							.startsWith("C")) {
				try {
					//Modified for defect ESPRD00935080 starts
					/*List status_list = getValidData(logCaseDataBean
							.getCaseStatusValidValues(),
							ReferenceServiceDataConstants.G_CASE_STAT_CD,
							FunctionalAreaConstants.GENERAL);*/
					
					List status_list =getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD, FunctionalAreaConstants.GENERAL);
					//defect ESPRD00935080 ends
					ContactManagementHelper helper2 = new ContactManagementHelper();
					log.debug("++ logCaseDataBean.getCaseDetailsVO().getStatus() ::"
									+ logCaseDataBean.getCaseDetailsVO()
											.getStatus());
					logCaseDataBean.getStatusList().add(
							new SelectItem(logCaseDataBean.getCaseDetailsVO()
									.getStatus(), helper2.getDescriptionFromVV(
									logCaseDataBean.getCaseDetailsVO()
											.getStatus(), status_list)));
				} catch (Exception e) {
					log.debug("++ caught in ::");
					e.printStackTrace();
				}
			}
			//defect ESPRD00533952 ends
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getReceiveMessage");
		return result;
	}

	public void getCFElementsInUpdate() {

		log.info("++" + ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getCFElementsInUpdate");
		List cfList = null;
		List valueList = null;
		ContactMaintenanceDelegate maintenanceDelegate = new ContactMaintenanceDelegate();
		LogCaseDomainToVOConverter converter = new LogCaseDomainToVOConverter();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		try {

			cfList = maintenanceDelegate
					.getAllCustomFieldsInDetail(logCaseDataBean
							.getCaseDetailsVO().getCaseSK());
			if (cfList != null && !cfList.isEmpty()) {
				logCaseDataBean.getCustomFieldDOList().clear();
				logCaseDataBean.setCustomFieldDOList(cfList);
				valueList = maintenanceDelegate.getCustomFieldValues(
						logCaseDataBean.getCaseDetailsVO().getCaseSK(),
						"G_CASE_TB");
				// Begin - Modified the code for the PanelGrid Fix
				Map map = new HashMap();
				DynamicCustomFieldHelper customFieldHelper = new DynamicCustomFieldHelper();
				for (int i = 0; i < valueList.size(); i++) {
					CustomFieldValueVO customFieldValueVO = logCaseDataBean
							.getCustomFieldValueVO();
					map.put(((CustomFieldValue) valueList.get(i))
							.getCustomFieldSK(), valueList.get(i));
					customFieldValueVO
							.setVersionNo(((CustomFieldValue) valueList.get(i))
									.getVersionNo());
				}
				logCaseDataBean.getCfValueSKMap().clear();
				converter.setCFValueToCustomField(valueList, cfList);
				//showDynamic(cfList, logCaseDataBean.getCaseDetailsVO()
				logCaseDataBean.setCustomFieldVOList(customFieldHelper
						.showDynamicFields(cfList, logCaseDataBean
								.getCustomFieldValueVO().isCrClosed(), map,
								logCaseDataBean.isShowCaseType()));
				// End - Modified the code for the PanelGrid Fix
				//Commented for critical defect ESPRD00597161 starts
				//Modified for defect ESPRD00477346 starts
				/*
				 * for (int j = 0; j < valueList.size(); j++) { CustomFieldValue
				 * fieldValue = (CustomFieldValue) valueList.get(j); for (int k =
				 * 0; k < cfList.size(); k++) { CustomField field =
				 * (CustomField) cfList.get(k); System.err.println("++==While
				 * retrieving custom CB"); if
				 * (field.getDataTypeCode().equals("CB")) { if
				 * (field.getCustomFieldSK().longValue() ==
				 * fieldValue.getCustomFieldSK() .longValue() &&
				 * logCaseDataBean.getFormElements().getChildren().get((k*4)+3)
				 * instanceof HtmlSelectBooleanCheckbox) { String value = null; //
				 * if (k ==0 ){ // HtmlSelectBooleanCheckbox htmlCheckBox =
				 * (HtmlSelectBooleanCheckbox)
				 * logCaseDataBean.getFormElements().getChildren().get(k); //
				 * value = fieldValue.getCustomFieldValue(); // // if
				 * (value.equals("true")){ // htmlCheckBox.setSelected(true); // } //
				 * logCaseDataBean.getFormElements().getChildren().set(k,htmlCheckBox); // // }
				 * else { HtmlSelectBooleanCheckbox htmlCheckBox =
				 * (HtmlSelectBooleanCheckbox)
				 * logCaseDataBean.getFormElements().getChildren().get((k*4)+3);
				 * value = fieldValue.getCustomFieldValue();
				 * 
				 * if (value.equals("true")){ htmlCheckBox.setSelected(true); }
				 * logCaseDataBean.getFormElements().getChildren().set((k*4)+3,htmlCheckBox); // }
				 *  } } } }
				 */
				//Defect ESPRD00477346 ends
				//ESPRD00597161 ends
			}
		} catch (CustomFieldFetchBusinessException e) {
			log.error("Exception while retriving CF values : " + e);
		} catch (NumberFormatException e) {
			log.error("Exception while retriving CF values : " + e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getCFElementsInUpdate");
	}

	/**
	 * Adde By Madhurima to COnvert crVo to AssocaiteCrVO
	 * 
	 * @param letterGenSK
	 * @return
	 */
	private AssociatedCorrespondenceVO getAssociateCorrespondenceVO(
			CorrespondenceRecordVO crRecordVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAssociateCorrespondenceVO");
		AssociatedCorrespondenceVO associateCRVO = new AssociatedCorrespondenceVO();
		if (StringUtils.isNotEmpty(crRecordVO.getCorrespondenceRecordNumber())) {
			associateCRVO.setCorrespondenceRecordNumber(crRecordVO
					.getCorrespondenceRecordNumber());

		}
		if (StringUtils.isNotEmpty(crRecordVO.getCorrespondenceDetailsVO()
				.getOpenDate())) {
			associateCRVO.setOpenDate(crRecordVO.getCorrespondenceDetailsVO()
					.getOpenDate());
		}
		if (StringUtils.isNotEmpty(crRecordVO.getCorrespondenceDetailsVO()
				.getStatusCode())) {
			associateCRVO.setStatus(crRecordVO.getCorrespondenceDetailsVO()
					.getStatusCode());
		}
		if (StringUtils.isNotEmpty(crRecordVO.getCorrespondenceDetailsVO()
				.getCategorySK())) {
			associateCRVO.setCategory(crRecordVO.getCorrespondenceDetailsVO()
					.getCategorySK());
		}
		if (StringUtils.isNotEmpty(crRecordVO.getCorrespondenceDetailsVO()
				.getSubjectCode())) {
			associateCRVO.setSubject(crRecordVO.getCorrespondenceDetailsVO()
					.getSubjectCode());
		}
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAssociateCorrespondenceVO");
		return associateCRVO;
	}

	/**
	 * Added By Madhurima
	 */

	private String setLetterGenerationDetails(String letterGenSK) {
		CaseLetterResponsesXrefVO letterGenerationVO = new CaseLetterResponsesXrefVO();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		log.debug("letterGenSK In setDetails: " + letterGenSK);
		letterGenerationVO.setLetterGeneratonRequestSK(letterGenSK);
		logCaseDataBean.getCaseDetailsVO().getListOfLettersAndResponses().add(
				letterGenerationVO);

		log.debug("Letters&Res size in setLetterDetails:"
				+ logCaseDataBean.getCaseDetailsVO()
						.getListOfLettersAndResponses().size());
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/** End BY Madhurima */

	/**
	 * Method to generate the URL based on pageID.
	 * 
	 * @return
	 */
	public String generateURL(String pageID) {
		String fileUrl = "";
		try {
			log.debug("In try of File URL");
			URLGenerator urlGenerator = new EDMSURLGeneratorImpl();
			fileUrl = urlGenerator.getURL(pageID);
			log.debug("File Url is ....." + fileUrl);
		} catch (EDMSBaseException edmsBaseException) {
			log.error(edmsBaseException.getMessage(), edmsBaseException);
		}
		return fileUrl;
	}

	private List getAttachmentList(List edmsResult) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAttachmentList");
		List attchList = new ArrayList();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//Modified for ESPRD00917530 starts
		EDMSQuickSearchProcessDelegate quickSearchProcessDelegate = null;
		Map fileinfoMap = null;
		String cascadeStr = null;
		String fileContent = null;
		Set keySet= null;
		String fileExtn = null;
		// ESPRD00917530  ends
		if (edmsResult != null && !edmsResult.isEmpty()) {
			log.debug("EDMS search results list size" + edmsResult.size());
			for (Iterator iter = edmsResult.iterator(); iter.hasNext();) {
				log.debug("For getAttachementList");
				EDMSQuickSearchResultsVO resultsVO = new EDMSQuickSearchResultsVO();
				Object innerObj = iter.next();
				try {
					quickSearchProcessDelegate = new EDMSQuickSearchProcessDelegate();
					BeanUtils.copyProperties(resultsVO, innerObj);

					if (resultsVO != null) {
						boolean flag = false;
						log.debug("page ID of resultsVO :"
								+ resultsVO.getPageId());
						if (logCaseDataBean.getAttachmentVOList() != null
								&& !logCaseDataBean.getAttachmentVOList()
										.isEmpty()
								&& resultsVO.getPageId() != null
								&& !resultsVO
										.getPageId()
										.equals(
												ContactManagementConstants.EMPTY_STRING)) {
							log.debug("Searching for duplicate");
							List attachList = logCaseDataBean
									.getAttachmentVOList();
							for (Iterator iterator = attachList.iterator(); iterator
									.hasNext();) {
								AttachmentVO element = (AttachmentVO) iterator
										.next();
								if (element.getAttachmentPageID() != null
										&& resultsVO.getPageId().equals(
												element.getAttachmentPageID())
										&& element.isDetachInd()) {

									log.debug("duplicate attachment found");
									flag = true;
								}
							}
						}
						if (flag) {

							ContactManagementHelper
									.setMessage("Duplicate File found");
						} else {
							AttachmentVO attachmentVO = new AttachmentVO();

							if (StringUtils.isEmpty(resultsVO.getDescription())) {
								attachmentVO.setDescription("Added from EDMS");
							} else {
								attachmentVO.setDescription(resultsVO
										.getDescription());
							}
							attachmentVO.setFileName(resultsVO.getFileName());
							//Modified for defect ESPRD00917530 
							/*if (resultsVO.getPageId() != null
									&& !resultsVO
											.getPageId()
											.equals(
													ContactManagementConstants.EMPTY_STRING)) {
								attachmentVO.setAttachmentPageID(resultsVO
										.getPageId());
							}*/
							fileinfoMap = quickSearchProcessDelegate.getSDRCasecadeStructure(resultsVO.getPageId());
							if(fileinfoMap!=null)
							{	
									cascadeStr = (String)fileinfoMap.get("CASCADE");
									log.debug("cascadeStr  "+  cascadeStr);
									cascadeStr = "ACS_NH/"+cascadeStr;
									keySet = fileinfoMap.keySet();
									Iterator it = keySet.iterator();
			    		    		while(it.hasNext())
			    		    		{
			    		    			fileExtn = (String)it.next();
			    		    			log.debug(" fileExtn :::   "+ fileExtn );
			    		    			if(!"CASCADE".equals(fileExtn))
			    		    			{	
			    		    				fileContent = (String)fileinfoMap.get(fileExtn);
			    		    				
			    		    			}	
			    		    		}
			    		    		log.debug(" Byte array :::   "+ fileContent.getBytes());
			    		    		try
			    		    		{
			    		    			String finalPathToWPS = writeToSan(fileContent.getBytes(), resultsVO.getFileName());
			    		    			log.debug("finalPathToWPS  :: "+ finalPathToWPS);
			    		    			log.debug("cascadeStr Final :: "+ cascadeStr);
			    		    			attachmentVO.setFinalPath(finalPathToWPS);
			    		    			attachmentVO.setCascadeKey(cascadeStr);
			    		    		}
			    		    		catch(Exception sanException)
			    		    		{
			    		    			log.debug(" Exception while writing to SAN Location" +
			    		    				" :::   "+ sanException.getMessage());
			    		    		    sanException.printStackTrace();
			    		    		}
							}
							// ends ESPRD00917530 

							if (attachmentVO.getAttachmentPageID() != null) {
								attachmentVO
										.setFileUrl(generateURL(attachmentVO
												.getAttachmentPageID()));
							}

							/** for use on server */
							attachmentVO.setAddedBy(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
							Date atdate = new Date();
							Format formatter = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm a", Locale.getDefault());
							String attachDate = formatter.format(atdate);
							log
									.debug("date before set to VO in getAttachments from EDMS :"
											+ attachDate);
							attachmentVO.setDateAdded(attachDate);
							//attachmentVO.setExistDoc(false);
							attachmentVO.setExistDoc(true);
							attachmentVO.setDbRecord(false);
							attachmentVO.setDetachInd(true);

							log.debug("EDMS search results getting is over:");
							logCaseDataBean.getAttachmentVOList().add(
									attachmentVO);
							logCaseDataBean.setShowAttachmentDataTable(true);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//for ESPRD00748455
		LogCaseHelper caseHelper = new LogCaseHelper();
		caseHelper
				.sortCaseAttachmentsComparator(
						MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_DATEADDED,
						MaintainContactManagementUIConstants.SORT_ORDER,
						logCaseDataBean.getAttachmentVOList());
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getAttachmentList");
		return attchList;
	}

	/**
	 * Added for notes
	 */
	private void copyCommonEntitiesRefs() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "copyCommonEntitiesRefs");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		commonEntityDataBean = (CommonEntityDataBean) getDataBean(ContactManagementConstants.COMMON_ENTITY_DATA_BEAN);
		logCaseDataBean.setCaseNotesList(commonEntityDataBean
				.getCommonEntityVO().getNoteSetVO().getNotesList());

		/** To show Current Note */
		if (!logCaseDataBean.getCaseNotesList().isEmpty()) {
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(
							((CommonNotesVO) logCaseDataBean.getCaseNotesList()
									.get(0)).getNoteText());
		}
	}

	private void restoreCommonEntitiesRefs() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "restoreCommonEntitiesRefs");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		commonEntityDataBean = (CommonEntityDataBean) getDataBean(ContactManagementConstants.COMMON_ENTITY_DATA_BEAN);
		if (logCaseDataBean.getCaseNotesList().isEmpty()) {
			log.debug("List is empty");
		} else {
			log.debug("List is not empty");
		}

		commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setNotesList(
				(ArrayList) (logCaseDataBean.getCaseNotesList()));

		/** To show Current Note */
		if (!logCaseDataBean.getCaseNotesList().isEmpty()) {
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(
							((CommonNotesVO) logCaseDataBean.getCaseNotesList()
									.get(0)).getNoteText());
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "restoreCommonEntitiesRefs");
	}

	/** End-Madhurima */

	/**
	 * @param receiveMessage
	 *            The receiveMessage to set.
	 */
	public void setReceiveMessage(String receiveMessage) {
		this.receiveMessage = receiveMessage;
		log.debug("receiveMessage is" + receiveMessage);
	}

	/**
	 * This method is used to get the Case Types on page load
	 * 
	 * @return String
	 */
	public String getCaseTypes() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getCaseTypes");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String userID = logCaseDataBean.getLoggedInUserID(); // Modified for the Performance fix
		//ADD for the Defect esprd00743101
		getUserSK(userID);
		List caseTypeList = new ArrayList();
		CaseType caseType = null;
		List caseList = null;
		//hash map performance issue code change
		//Map caseTypeSKAndShortDes = new HashMap();
		//Map caseTypeMemSKAndShortDes = new HashMap();
		//Map caseTypeAndBusinessUnit = new HashMap();
		ContactMaintenanceDelegate maintenanceDelegate = new ContactMaintenanceDelegate();
		try {

			//caseTypeList.add(new SelectItem("", "Please Select One"));
			caseList = maintenanceDelegate.getCaseTypes(userID);

			/* FIND BUGS_FIX */
			if (caseList != null && !caseList.isEmpty()) {
				log.debug("Total Case Type List size is $$ " + caseList.size());
				int caseTypeListSize = 0;
				logCaseDataBean.setCaseTypeDOList(caseList);
				caseTypeListSize = caseList.size();
				for (int i = 0; i < caseTypeListSize; i++) {
					caseType = (CaseType) caseList.get(i);
					if (caseType != null
							&& caseType.getCaseTypeSK() != null
							&& (caseType.getDescription() != null && !caseType
									.getDescription().equals(""))) {
						if(log.isDebugEnabled()){
						log.debug("Case Types are $$ "
								+ caseType.getCaseTypeSK() + " - "
								+ caseType.getDescription());
						}
						caseTypeList.add(new SelectItem(caseType
								.getCaseTypeSK().toString(), caseType
								.getDescription()));

						/* hash map performance issue code change
						 * caseTypeSKAndShortDes.put(caseType.getCaseTypeSK()
								.toString(), caseType.getShortDescription());
						caseTypeMemSKAndShortDes.put(caseType
								.getShortDescription(), caseType
								.getCaseTypeSK().toString());
						caseTypeAndBusinessUnit.put(caseType.getCaseTypeSK()
								.toString(), caseType
								.getBusinessUnitCaseTypeCode());*/
					}
				}
				sortCaseType(caseTypeList);
				logCaseDataBean.setCaseTypeList(caseTypeList);
				/* hash map performance issue code change
				 * logCaseDataBean
						.setCaseTypeSKAndShortDesc(caseTypeSKAndShortDes);
				logCaseDataBean
						.setCaseTypeMemSKAndShortDes(caseTypeMemSKAndShortDes);
				logCaseDataBean
						.setCaseTypeSKAndBusinessUnit(caseTypeAndBusinessUnit);*/

				logCaseDataBean.setEnableSaveLink(false);
			} else {
				logCaseDataBean.setCaseTypeList(caseTypeList);
				logCaseDataBean.setEnableSaveLink(true);
			}
		} catch (CaseTypeFetchBusinessException e) {
			log.error("Error occured at getCaseTypes()");
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getCaseTypes");
		return "";
	}

	/**
	 * it will populate the initial data for Log Case
	 */
	private void populateInitialDataForADD() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "populateInitialDataForADD");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//Map userMap = null;
		logCaseDataBean.setEnableAuditLogForLogCase(false);//CR_ESPRD00373565_LogCase_04AUG2010
		logCaseDataBean.setCaseStatusClosed(false);//UC-PGM-CRM-018.2_ESPRD00433227_12MAR2010
		logCaseDataBean.setCaseStatusSetFlag(true);//UC-PGM-CRM-018_ESPRD00367808_18JAN10
		logCaseDataBean.setDisableWorkUnit(false);//UC-PGM-CRM-18_ESPRD00367808_30DEC09
		logCaseDataBean.getDeletedEventsList().clear();//UC-PGM-CRM-018.4_ESPRD00379771_05JAN10
		logCaseDataBean.getDeletedStepsList().clear();//Modified for defect
													  // ESPRD00674473
		logCaseDataBean.getTempAssociatedCaseRecordsList().clear();//UC-PGM-CRM-018_ESPRD00388733_19JAN10
		logCaseDataBean.setCustomFieldVOList(null); // Added for the PanelGrid Fix
		logCaseDataBean.setDisableLogAddCaseAssocCrspondence(true);
		resetLettersEditSection();
		if (logCaseDataBean.getAddiCaseEntityRowIndex() != -1) {
			logCaseDataBean.setAddiCaseEntityRowIndex(0);
		}
		logCaseDataBean.setCursorFocusValue("");
		String userIdName = null;

		String createdBySK = null;
		//Long createdSK = getUserSK(createdByName); // Commented for the defect id ESPRD00702153_30NOV2011.
		Long createdSK = logCaseDataBean.getLoggedInUserSK(); // Modified for the defect id ESPRD00702153_30NOV2011
		if (createdSK == null) {
			createdBySK = "0";
		} else {
			createdBySK = createdSK.toString();
		}

		log.debug("createdBySK in populateInitialData() is $$ " + createdBySK);
		logCaseDataBean.getCaseDetailsVO().setLineOfBusiness("MED");
		logCaseDataBean.getCaseDetailsVO().setPriority("M");
		setDepartmentsList(createdBySK, false);

		//userMap = logCaseDataBean.getUserMap();
		//if (!userMap.isEmpty()) {
			//String userName = (String) userMap.get(createdBySK);
			//modified for removing map.
			String userName = getDescriptionFromVV(createdBySK, logCaseDataBean.getUserList());
			//Modified for the defect Id ESPRD00778564
			if (!isNullOrEmptyField(userName)) {
				logCaseDataBean.getCaseDetailsVO().setCreatedBy(userName);
				logCaseDataBean.getCaseDetailsVO().setAssignedTo(userName);
				logCaseDataBean.getRoutingVO().setCreatedBy(userName);
				logCaseDataBean.getRoutingVO().setAssignedTo(userName);
			}
			logCaseDataBean.getCaseDetailsVO().setCreatedBySK(createdBySK);
			logCaseDataBean.getCaseDetailsVO().setAssignedToWorkUnitSK(
					createdBySK);
			logCaseDataBean.getCaseDetailsVO().setCreatedDate(new Date());
			logCaseDataBean.getCaseDetailsVO().setCreatedDateStr(
					ContactManagementHelper.dateConverter(new Date()));
			logCaseDataBean.getCaseDetailsVO().setDaysopened("0");
			logCaseDataBean.getRoutingVO().setRoutedByWorkUntiSK(
					Long.valueOf(createdBySK));
		//}

		logCaseDataBean.getTemplateList().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "populateInitialDataForADD");
	}

	/**
	 * it will populate the initial data for Log Case
	 */
	private void populateInitialDataForUpdate() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "populateInitialDataForUpdate");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setEnableAuditLogForLogCase(false);//CR_ESPRD00373565_LogCase_04AUG2010
		String createdByName = logCaseDataBean.getLoggedInUserID(); // Modified for the Performance fix

		logCaseDataBean.setDisableLogAddCaseAssocCrspondence(false);
		logCaseDataBean.setCaseStatusClosed(false);//UC-PGM-CRM-018.2_ESPRD00433227_12MAR2010
		logCaseDataBean.getDeletedEventsList().clear();//UC-PGM-CRM-018.4_ESPRD00379771_05JAN10
		logCaseDataBean.getDeletedStepsList().clear();//Modified for defect
													  // ESPRD00674473
		logCaseDataBean.getTempAssociatedCaseRecordsList().clear();//UC-PGM-CRM-018_ESPRD00388733_19JAN10
		resetLettersEditSection();
		if (logCaseDataBean.getAddiCaseEntityRowIndex() != -1) {
			logCaseDataBean.setAddiCaseEntityRowIndex(0);
		}
		logCaseDataBean.setCursorFocusValue("");
		//Long createdBySk = getUserSK(createdByName); // Commented for the defect id ESPRD00702153_30NOV2011.
		Long createdBySk = logCaseDataBean.getLoggedInUserSK(); // Modified for the defect id ESPRD00702153_30NOV2011
		log.debug("createdBySk---" + createdBySk);
		if (createdBySk != null) {
			String createdBySK = createdBySk.toString();
			log.debug("++createdBySK in populateInitialData() is $$ "
					+ createdBySK);
			setDepartmentsList(createdBySK, false);

			logCaseDataBean.getRoutingVO().setRoutedByWorkUntiSK(
					Long.valueOf(createdBySK));
		}
		logCaseDataBean.getTemplateList().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

	}

	/**
	 * it will get the LogIn user ID
	 * 
	 * @return String
	 */
	 // Begin - Commented for the Performance fix
	/*public String getLoggedInUser() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getLoggedInUser");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String loggedInUser = getUserID();//"USERID2"
		logCaseDataBean.setUserID(loggedInUser);
		log.debug("User Id is " + loggedInUser);
		return loggedInUser;
	}*/
	// End - Commented for the Performance fix

	/**
	 * This method is used to get the User ID.
	 * 
	 * @return String : User ID
	 */
	public String getUserID() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getUserID");
		// String userId = "contactmgm002";
		//        String userId = "SPATZERALL";
		String userId = "TBOINAPALLY";
		// Added the below statement for the perfomance fix
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderresponse = null;
		EnterpriseUserProfile eup = getUserData(renderrequest, renderresponse);
		log.debug("Enterprise User profile is $$ " + eup);
		if (eup != null && !eup.getUserId().equals("")) {
			userId = eup.getUserId();
			log.debug("From Enterprise User ID is $$ " + userId);
		} else {
			log.debug("Enterprise User Profile is null");
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getUserID");
		// Added the below statement for the Performance fix
		logCaseDataBean.setLoggedInUserID(userId);
		return userId;
	}

	/**
	 * @return
	 */
	private boolean isCaseAssignedToLoggedInUser() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "isCaseAssignedToLoggedInUser");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		boolean crAssignedTo = false;
		try {
			String crAssignedToWUSK = logCaseDataBean.getCaseDetailsVO()
					.getAssignedToWorkUnitSK();

			String logInUser = logCaseDataBean.getLoggedInUserID(); // Modified for the Performance fix
			/*
			 * Modified for defectIDs ESPRD00299476 , ESPRD00299545,
			 * ESPRD00300150
			 */
			//Modified for GAI Recursive Call_Fix
			//Long userSKLongVal = getUserSK(logInUser);
			Long userSKLongVal = logCaseDataBean.getLoggedInUserSK();
			String logInUserSK = null;
			if (userSKLongVal != null)
				logInUserSK = userSKLongVal.toString();
			//EOF modification for defect IDs ESPRD00299476, ESPRD00299545,
			// ESPRD00300150
			if (crAssignedToWUSK.equalsIgnoreCase(logInUserSK)) {
				log.debug("LoggedIn user and Assigned user is defferent");
				crAssignedTo = true;
			}
		} catch (Exception e) {
			log.debug("Exception in isCaseAssignedToLoggedInUser()"
					+ e.getMessage());
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "isCaseAssignedToLoggedInUser");
		return crAssignedTo;
	}

	/**
	 * @return
	 */
	private boolean isLoggedInUserSupervisorOfDept() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "isLoggedInUserSupervisorOfDept");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		boolean crAssignedTo = false;
		try {
			String deptSuprvWorkUnitSK = logCaseDataBean.getCaseDetailsVO()
					.getDeptSuperWorkUnitSK();

			String logInUser = logCaseDataBean.getLoggedInUserID(); // Modified for the Performance fix

			/*
			 * Modified for defectIDs ESPRD00299476 , ESPRD00299545,
			 * ESPRD00300150
			 */
			//Modified for GAI Recursive Call_Fix
			//Long userSKLongVal = getUserSK(logInUser);
			Long userSKLongVal = logCaseDataBean.getLoggedInUserSK();
			String logInUserSK = null;
			if (userSKLongVal != null)
				logInUserSK = userSKLongVal.toString();

			if (deptSuprvWorkUnitSK.equalsIgnoreCase(logInUserSK)) {
				//EOF modification for defect IDs ESPRD00299476, ESPRD00299545,
				// ESPRD00300150
				log.debug("LoggedIn user and Assigned user is defferent");
				crAssignedTo = true;
			}
		} catch (Exception e) {
			log.debug("Exception in isLoggedInUserSupervisorOfDept()"
					+ e.getMessage());
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "isLoggedInUserSupervisorOfDept");
		return crAssignedTo;
	}

	/**
	 *  
	 */
	private void removeClosedValFromStatus() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List statusList = logCaseDataBean.getStatusList();

		if (statusList != null) {
			for (Iterator iter = statusList.iterator(); iter.hasNext();) {
				SelectItem item = (SelectItem) iter.next();
				String itemValue = (String) item.getValue();
				//Modified for defect ESPRD00491876
				//updated for the Defect : ESPRD00760011
				if (itemValue.equalsIgnoreCase("C")
						|| itemValue.equalsIgnoreCase("C2")
						|| itemValue.equalsIgnoreCase("C3")
						|| itemValue.equalsIgnoreCase("C4")
						|| itemValue.equalsIgnoreCase("C5")
						|| itemValue.equalsIgnoreCase("W")
						|| itemValue.equalsIgnoreCase("CA")
						|| itemValue.equalsIgnoreCase("CD")) {
					log.debug("++ itemValue ::" + itemValue);
					iter.remove();
					//					break;
				}
			}
		}
	}

	/**
	 * This method is used to get the UserSK given the userId.
	 * 
	 * @param userId :
	 *            String User Id.
	 * @return Long : UserSK.
	 */
	private Long getUserSK(String userId) {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		Long userSK = null;
		WorkUnit userWorkUnit = null;
		try {

			userWorkUnit = contactMaintenanceDelegate.getUserWorkUnit(userId);
			if (userWorkUnit != null) {

				userSK = userWorkUnit.getWorkUnitSK();
				checkSupervisorReviewReq(userWorkUnit);
				setCreatedByAssignedTo(userWorkUnit.getEnterpriseUser());//Added for the defect Id ESPRD00778564
			}
		} catch (LOBHierarchyFetchBusinessException e) {
			log.error(e.getMessage(), e);
		}

		return userSK;
	}
	//Added new method for  the defect Id ESPRD00778564 
	/** To set createdby and assigned to for casedetails vo
	 * */
	private void setCreatedByAssignedTo(EnterpriseUser enterpriseUser) {
		// TODO Auto-generated method stub
		String userName=userNameAPI(enterpriseUser);
		if(!isNullOrEmptyField(userName)){
			logCaseDataBean.getCaseDetailsVO().setCreatedBy(userName);
			logCaseDataBean.getCaseDetailsVO().setAssignedTo(userName);
		}
	}
	
	private void checkSupervisorReviewReq(WorkUnit workUnit) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "checkSupervisorReviewReq");
		EnterpriseUser user = workUnit.getEnterpriseUser();
		log.debug("----3------ user :" + user);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (user != null) {
			Boolean supervisorApprReq = user.getSupervisorReviewReqIndicator();
			Boolean supervisor = user.getSupervisorIndicator();
			log.debug("----4----- supervisor  :" + supervisor);
			//ADD for the Defect esprd00743101
			logCaseDataBean.setSuperviserRevReqInd(supervisorApprReq
					.booleanValue());
			if (supervisor != null) {
				/*logCaseDataBean.setSuperviserRevReqInd(supervisorApprReq
						.booleanValue());*///Commented for the Defect esprd00743101
				logCaseDataBean.setSuperviser(supervisor.booleanValue());
				log.debug("For logged in user, superviserReview Req is $$ "
						+ logCaseDataBean.isSuperviserRevReqInd());
				log.debug("For logged in user, Superviser indicator is $$ "
						+ logCaseDataBean.isSuperviser());
			}
		}
		if (!logCaseDataBean.isSuperviser()) {
			removeClosedValFromStatus();
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "checkSupervisorReviewReq");
	}

	/**
	 * This method is used to create the depts lists based on UserID.
	 * 
	 * @param userSK :
	 *            The userSK to set.
	 */
	public List setDepartmentsList(String userSK, boolean isRouting) {
		log.info("++ " + ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "setDepartmentsList");
		List deptList = new ArrayList();
		List deptList1 = new ArrayList();
		List deptsList = null;
		/***
		 * 
		 * Modified for Department User Heap dump issues starts
		 * 
		 */
		//Department department = null;
		BigDecimal lobHierarchySK = null;
		BigDecimal deptmntSK = null;
		String deptName = "";
	
		

		Map lobDOMap = new HashMap();
		Map deptMap = new HashMap();
		Map deptMap1 = new HashMap();
		Map lobSKAndDeptSKMap = new HashMap();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try {
deptsList = contactMaintenanceDelegate.getCRMDepartmentUsers(Long
					.valueOf(userSK));
			//  System.err.println("++Dept list size" + deptsList.size());
			if (deptsList != null) {
				deptList.add(new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));
				/*deptList1.add(new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));*/
				for (Iterator iter = deptsList.iterator(); iter.hasNext();) {
					Object[] departMentObj=(Object[]) iter.next();
					deptmntSK = (BigDecimal)departMentObj[0];
					deptName = (String)departMentObj[1];
					lobHierarchySK = (BigDecimal)departMentObj[2];
					
					
					if (!isRouting) {
						if(log.isDebugEnabled()){
						log.debug("=========1========="+deptmntSK+"-"+deptName+"-"+lobHierarchySK);
						}
						if (lobHierarchySK != null) {
							lobDOMap.put(deptmntSK.toString(),
									Long.valueOf(lobHierarchySK.toString()));

							lobSKAndDeptSKMap.put(lobHierarchySK.toString(),
									deptmntSK.toString());
						}
						deptList
								.add(new SelectItem(deptmntSK
										.toString(),deptName.toString()));
						deptMap.put(deptmntSK.toString(),
								deptName.toString());
					} else {
						if(log.isDebugEnabled()){
						log.debug("=========2========="+deptmntSK+"-"+deptName+"-"+lobHierarchySK);
						}
						deptList1
								.add(new SelectItem(deptmntSK
										.toString(), deptName.toString()));
						deptMap1.put(deptmntSK.toString(),
								deptName.toString());
					}
					// Commented for CRM Heapdump_FIX for DepartmentUsers
					/*DepartmentUser deptUser = (DepartmentUser) iter.next();
					department = (Department) deptUser.getDepartment();
					lobHierarchySK = department.getLineOfBusinessHierarchy()
							.getLineOfBusinessHierarchySK();
					if (!isRouting) {
						if (lobHierarchySK != null) {
							lobDOMap.put(deptUser.getDepartmentSK().toString(),
									lobHierarchySK);

							lobSKAndDeptSKMap.put(lobHierarchySK.toString(),
									deptUser.getDepartmentSK().toString());

						}
						deptList
								.add(new SelectItem(deptUser.getDepartmentSK()
										.toString(), deptUser.getDepartment()
										.getName()));
						deptMap.put(deptUser.getDepartmentSK().toString(),
								deptUser.getDepartment().getName());
					} else {
						deptList1
								.add(new SelectItem(deptUser.getDepartmentSK()
										.toString(), deptUser.getDepartment()
										.getName()));
						deptMap1.put(deptUser.getDepartmentSK().toString(),
								deptUser.getDepartment().getName());
					}*/
				}
				/***
				 * 
				 * Department User Heap dump issues Ends
				 * 
				 */
				if (!isRouting) {
					logCaseDataBean.setLobDOMap(lobDOMap);
					logCaseDataBean.setLobSKAndDeptSKMap(lobSKAndDeptSKMap);

					//by default in ascending order for workunit
					// (ESPRD00688018)
					Comparator deptNameCompare = new Comparator() {
						public int compare(Object o1, Object o2) {
							// TODO Auto-generated method stub
							SelectItem selectItem1 = null;
							SelectItem selectItem2 = null;
							if (o1 instanceof SelectItem
									&& o2 instanceof SelectItem) {
								selectItem1 = (SelectItem) o1;
								selectItem2 = (SelectItem) o2;
								return selectItem1.getLabel().compareTo(
										selectItem2.getLabel());
							}
							return 0;
						}
					};

					Collections.sort(deptList, deptNameCompare);
					//by default in ascending order for workunit
					// (ESPRD00688018)

					logCaseDataBean.setListOfDepartments(deptList);
					logCaseDataBean.setDeptMap(deptMap);
					if (MaintainContactManagementUIConstants.TWO == deptList
							.size()) {

						SelectItem item = (SelectItem) deptList.get(1);
						String deptSK = (String) item.getValue();
						autoPopulateDepartment(deptSK);
						//ESPRD00513826_UC-PGM-CRM-18_25AUG2010
						logCaseDataBean.setDisableWorkUnitInAddMode(true);
					} else {
						logCaseDataBean.setDisableWorkUnitInAddMode(false);
					}//EOF ESPRD00513826_UC-PGM-CRM-18_25AUG2010

				} else {
					List deptList2 = new ArrayList();
					if (MaintainContactManagementUIConstants.TWO == deptList1
							.size()) {
						SelectItem item = (SelectItem) deptList1.get(1);
		 				String deptSK = (String) item.getValue();
						deptList2.add(item);
						logCaseDataBean.setRoutingDeptList(deptList2);
						logCaseDataBean.getRoutingVO()
								.setUserDepartment(deptSK);
						logCaseDataBean.setDisableFields(true);
					} else {
						logCaseDataBean.setDisableFields(false);
						deptList2.clear();
						deptList2.addAll(deptList1);
						logCaseDataBean.setRoutingDeptList(deptList2);
						logCaseDataBean.getRoutingVO().setUserDepartment("");
						logCaseDataBean.setRoutingDeptMap(deptMap1);
					}

				}
			} else {//ESPRD00327996
				logCaseDataBean.setRoutingDeptList(new ArrayList());
			}
		} catch (LOBHierarchyFetchBusinessException lobExp) {
			lobExp.printStackTrace();
			log.error(lobExp.getMessage(), lobExp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "setDepartmentsList");
		return deptsList;
	}

	/**
	 * @param deptSK :
	 *            Department SK to be auto populated.
	 */
	private void autoPopulateDepartment(String deptSK) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "autoPopulateDepartment");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		log.debug("deptSK" + deptSK);
		logCaseDataBean.getCaseDetailsVO().setWorkUnit(deptSK);
		logCaseDataBean.setAutoPopulatedDept(true);
		setRepAndBusUnits(deptSK);
	}

	/**
	 * @param event
	 */
	public void getReportingAndBusinessUnit(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getReportingAndBusinessUnit");

		if (event.getNewValue() == null || isNullOrEmptyField(event.getNewValue().toString())) {
			return;
		} else {
			String departmentSK = (String) event.getNewValue();
			setRepAndBusUnits(departmentSK);
		}
	}

	/**
	 * @param departmentSK
	 */
	private void setRepAndBusUnits(String departmentSK) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "setRepAndBusUnits");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		log.debug("departmentSK" + departmentSK);
		Map mapOfDeptAndLobHier = logCaseDataBean.getLobDOMap();

		Long lobHierarchySK = (Long) mapOfDeptAndLobHier.get(departmentSK);
		logCaseDataBean.setLobSK(lobHierarchySK);
		if (logCaseDataBean.getLobSK() != null
				&& !logCaseDataBean.getLobSK().equals(Long.valueOf(0))) {
			log.debug("lobHierarchySK " + lobHierarchySK);
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			try {
                // Commented for Query Tunning
				//LineOfBusinessHierarchy reportingUnit = contactMaintenanceDelegate
				//		.getDeptReportingUnit(logCaseDataBean.getLobSK());
				//LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
				//		.getDeptBusinessUnit(logCaseDataBean.getLobSK());
                //Added for Query Tunning
				//Start
				Map rUnitAndBUnitMap = contactMaintenanceDelegate.getRUnitAndBUnit(lobHierarchySK);
    	        LineOfBusinessHierarchy reportingUnit =(LineOfBusinessHierarchy) rUnitAndBUnitMap.get("RUnit");
		        LineOfBusinessHierarchy businessUnit = (LineOfBusinessHierarchy) rUnitAndBUnitMap.get("BUnit");  
		        log.debug("reportingUnit" + reportingUnit);
    			log.debug("businessUnit" + businessUnit);
		        //End 
				if (reportingUnit != null) {
					logCaseDataBean.getCaseDetailsVO().setReportingUnit(
							reportingUnit.getLobHierarchyDescription());
				} else {
					logCaseDataBean.getCaseDetailsVO().setReportingUnit(
							ContactManagementConstants.EMPTY_STRING);
				}
				if (businessUnit != null) {
					logCaseDataBean.getCaseDetailsVO().setBusinessUnit(
							businessUnit.getLobHierarchyDescription());
				} else {
					logCaseDataBean.getCaseDetailsVO().setBusinessUnit(
							ContactManagementConstants.EMPTY_STRING);
				}
			} catch (LOBHierarchyFetchBusinessException e) {
				log.error(e.getMessage(), e);
			} catch (NumberFormatException e) {
				log.error(e.getMessage(), e);
			}

		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "setRepAndBusUnits");
	}

	/**
	 * @param inqAbtDetails
	 */
	private void getInquiryAboutDetails(String inqAbtDetails) {
		log.info("++getInquiryAboutDetails called");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//defect ESPRD00544128 starts
		/*
		 * int separatorIndex = inqAbtDetails
		 * .indexOf(ContactManagementConstants.COLON_SEPARATOR); String entityID =
		 * inqAbtDetails.substring(0, separatorIndex); String entityType =
		 * inqAbtDetails.substring(separatorIndex + 1, inqAbtDetails.length());
		 */
		String[] inqAbtDetailsArray = inqAbtDetails.split(":");
		String entityID = inqAbtDetailsArray[0];
		String entityType = inqAbtDetailsArray[1];
		String cmEntitySk = "";
		if (inqAbtDetailsArray.length > 2) {
			log.debug("++In getInquiryAboutDetails cmtSk is $$ "
					+ inqAbtDetailsArray[2]);
			cmEntitySk = inqAbtDetailsArray[2];
		}
		log.debug("++in getInquiryAboutDetails Entity ID is $$ "
				+ inqAbtDetailsArray[0]);
		log.debug("++In getInquiryAboutDetails EntityType is $$ "
				+ inqAbtDetailsArray[1]);
		/** flag is moved to global as in all condition we
		 *  are validating it and new condition is added like
		 *  if an entity on which case record is created is same
		 *  as of entity trying to add then an error msg should 
		 *  be displayed with out allowing.
		 * */
		boolean recordExists = false;
		CaseRegardingVO caseRegardingVO=logCaseDataBean.getCaseRegardingVO();
		//defect ESPRD00544128 ends
		//Added for the defect ESPRD00873143 to remove the success message/previous messages
		logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		CaseRegardingMemberVO caseRegardingMemberVO = null;
		CaseRegardingProviderVO regardingProviderVO = null;
		CaseRegardingTPL caseRegarding = null;
		CaseRegardingTradingPartnerVO caseRegardingTradingPartnerVO = null;
		if (StringUtils.isNotEmpty(entityID)
				&& StringUtils.isNotEmpty(entityType)) {
			if (ContactManagementConstants.ENTITY_TYPE_MEM.equals(entityType)) {
				caseRegardingMemberVO = getEntityDetailsForMember(entityID,
						false);
				if (caseRegardingMemberVO != null) //ADDED FOR THE
												   // CR_ESPRD00249998
				{
					//boolean recordExists = false;
					if(log.isDebugEnabled()){
					log.debug("caseRegardingMemberVO.getEntityId()"+caseRegardingMemberVO.getMemberId());
					log.debug("caseRegardingVO.getCaseRegardingMemberVO()"+
									caseRegardingVO.getCaseRegardingMemberVO()
											.getMemberId());
					}
					if (!isNullOrEmptyField(caseRegardingMemberVO.getMemberId())
							&& caseRegardingMemberVO.getMemberId().equals(
									caseRegardingVO.getCaseRegardingMemberVO()
											.getMemberId())) {
						recordExists=Boolean.TRUE;
					}else if (logCaseDataBean.getCaseDetailsVO()
							.getInqAbtEntityList() != null
							&& logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().size() > 0) {
						Iterator itr = logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().iterator();
						while (itr.hasNext()) {
							Object obj = itr.next();
							if (obj instanceof CaseRegardingMemberVO) {
								/*
								 * CaseRegardingMemberVO caseRegardingMemberVO2 =
								 * (CaseRegardingMemberVO) itr .next();
								 */
								CaseRegardingMemberVO caseRegardingMemberVO2 = (CaseRegardingMemberVO) obj;
								if (caseRegardingMemberVO.getEntityId()
										.equalsIgnoreCase(
												caseRegardingMemberVO2
														.getEntityId())) {
									recordExists = true;
									/*ContactManagementHelper
											.setErrorMessage(
													MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
													null, null, null);*/
									break;
								}
							}

						}
					}
					if (!recordExists) //END ADDED FOR THE CR_ESPRD00249998
					{
						logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().add(
										caseRegardingMemberVO);
						logCaseDataBean.getInqAbtEntityListBeforeSave().add(
								caseRegardingMemberVO);//ESPRD00358412
						logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
					}
				}
				// Added for the defect id ESPRD00429696
				/*
				 * else if
				 * (ContactManagementConstants.ENTITY_TYPE_PROV.equals(entityType) ||
				 * ContactManagementConstants.ENTITY_TYPE_UNPROV.equals(entityType))
				 */
				// Ends
			} else if (ContactManagementConstants.ENTITY_TYPE_PROV
					.equals(entityType)) {
				log.debug("providere==" + entityID);
				regardingProviderVO = getEntitiesForProvider(entityID, true);
				if (regardingProviderVO != null) {
//					boolean recordExists = false;
					if (log.isDebugEnabled()) {
						log.debug("regardingProviderVO.getEntityId()"
								+ regardingProviderVO.getProviderId());
						log.debug("caseRegardingVO.getCaseRegardingProviderVO()"
										+ caseRegardingVO
												.getCaseRegardingProviderVO()
												.getProviderId());
					}
					if (!isNullOrEmptyField(regardingProviderVO.getProviderId())
							&& regardingProviderVO.getProviderId().equals(
									caseRegardingVO.getCaseRegardingProviderVO()
											.getProviderId())) {
						recordExists=Boolean.TRUE;
					}else if (logCaseDataBean.getCaseDetailsVO()
							.getInqAbtEntityList() != null
							&& logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().size() > 0) {
						if(log.isDebugEnabled()){
						log.debug("logCaseDataBean.getCaseDetailsVO().getInqAbtEntityList().size()"
										+ logCaseDataBean.getCaseDetailsVO()
												.getInqAbtEntityList().size());
						}
						Iterator itr = logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().iterator();
						while (itr.hasNext()) {
							Object obj = itr.next();
							if (obj instanceof CaseRegardingProviderVO) {
								CaseRegardingProviderVO caseRegardingProviderVO2 = (CaseRegardingProviderVO) obj;
								if(log.isDebugEnabled()){
								log.debug("--caseRegardingProviderVO2.getEntityId()::"
												+ caseRegardingProviderVO2
														.getEntityId());
								log.debug("--caseRegardingProviderVO2.getEntityId()::"
												+ caseRegardingProviderVO2
														.getCommonEntitySK());
								}
								if (regardingProviderVO.getEntityId()
										.equalsIgnoreCase(
												caseRegardingProviderVO2
														.getEntityId())) {
									recordExists = true;
									log.info("=======inside duplicate provide entity");
									/*ContactManagementHelper
											.setErrorMessage(
													MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
													null, null, null);*/
									break;
								}

							}
						}
					}

					if (!recordExists) {
						logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().add(regardingProviderVO);
						logCaseDataBean.getInqAbtEntityListBeforeSave().add(
								regardingProviderVO);//ESPRD00358412
						logCaseDataBean.getPhysicianOverseeingList()
								.add(
										new SelectItem(regardingProviderVO
												.getEntityId(),
												regardingProviderVO
														.getEntityType()
														+ " - "
														+ regardingProviderVO
																.getName()));
						logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
						logCaseDataBean.getProviderhos().add(
								new SelectItem(regardingProviderVO
										.getEntityId(), regardingProviderVO
										.getName()));
					}

				}
			} else if (ContactManagementConstants.ENTITY_TYPE_TPL
					.equals(entityType)) {
				log.debug("============================---------------cmEntitySk:"
								+ cmEntitySk);
				log.debug("==centityID:" + entityID);
				if (cmEntitySk != null
						&& !cmEntitySk
								.equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
					caseRegarding = getEntityForTPL(cmEntitySk, true);
					log.info("1");
				} else {
					caseRegarding = getEntityForTPL(entityID, true);
					log.info("2");
				}
				log.debug("caseRegarding::" + caseRegarding);
				if (caseRegarding != null) {
//					boolean recordExists = false;
					if(log.isDebugEnabled()){
					log.debug("caseRegarding.getEntityId()"+caseRegarding.getEntityId());
					log.debug("caseRegardingVO.getCaseRegardingTPLVO()"+
							caseRegardingVO.getCaseRegardingTPLVO()
							.getEntityId());
					}
					if (!isNullOrEmptyField(caseRegarding.getEntityId())
							&& (caseRegarding.getEntityId().equals(
									caseRegardingVO.getCaseRegardingTPLVO()
											.getEntityId()) || caseRegarding.getEntityId().equals(
													caseRegardingVO.getCaseRegardingTPLVO()
													.getEntityId()))) {
						recordExists=Boolean.TRUE;
					}else if (logCaseDataBean.getCaseDetailsVO()
							.getInqAbtEntityList() != null
							&& logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().size() > 0) {
						if(log.isDebugEnabled()){
							log.debug("logCaseDataBean.getCaseDetailsVO().getInqAbtEntityList().size()"
										+ logCaseDataBean.getCaseDetailsVO()
												.getInqAbtEntityList().size());
						}
						Iterator itr = logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().iterator();
						while (itr.hasNext()) {
							Object obj = itr.next();
							if (obj instanceof CaseRegardingTPL) {

								CaseRegardingTPL caseRegardingTPL = (CaseRegardingTPL) obj;
								if(log.isDebugEnabled()){
									log.debug("--caseRegardingTPL.getEntityId()::"
												+ caseRegardingTPL
														.getEntityId());
								}

								if (caseRegarding.getEntityId()
										.equalsIgnoreCase(
												caseRegardingTPL.getEntityId())) {
									recordExists = true;
									/*ContactManagementHelper
											.setErrorMessage(
													MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
													null, null, null);*/
									break;
								}

							}
						}
					}
					if (!recordExists) {

						logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().add(caseRegarding);
						logCaseDataBean.getInqAbtEntityListBeforeSave().add(
								caseRegarding);//ESPRD00358412
						logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
					}
				}

			} else if (ContactManagementConstants.ENTITY_TYPE_TP
					.equals(entityType)) {
				caseRegarding = getEntityForTPLPolicyHolder(entityID, true);
				if (caseRegarding != null) {
//					boolean recordExists = false;
					if(log.isDebugEnabled()){
					log.debug("caseRegarding.getEntityId()"+caseRegarding.getEntityId());
					log.debug("caseRegardingVO.getCaseRegardingTPLVO()"+
							caseRegardingVO.getCaseRegardingTPLVO()
							.getEntityId());
					}
					if (!isNullOrEmptyField(caseRegarding.getEntityId())
							&& caseRegarding.getEntityId().equals(
									caseRegardingVO.getCaseRegardingTPLVO()
											.getEntityId())) {
						recordExists=Boolean.TRUE;
					}else if (logCaseDataBean.getCaseDetailsVO()
							.getInqAbtEntityList() != null
							&& logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().size() > 0) {
						Iterator itr = logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().iterator();
						while (itr.hasNext()) {
							Object obj = itr.next();
							if (obj instanceof CaseRegardingTPL) {
								CaseRegardingTPL caseRegardingTPL = (CaseRegardingTPL) obj;
								if (entityID.equalsIgnoreCase(caseRegardingTPL
										.getEntityId())) {
									recordExists = true;
									/*ContactManagementHelper
											.setErrorMessage(
													MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
													null, null, null);*/
									break;
								}

							}
						}
					}
					if (!recordExists) {
						logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().add(caseRegarding);
						logCaseDataBean.getInqAbtEntityListBeforeSave().add(
								caseRegarding);//ESPRD00358412
						logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
					}
				}
			} else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
					.equals(entityType)) {
				caseRegarding = getSpecificEntity(entityID, true);
				if (caseRegarding == null) {

					log.debug("providere==" + entityID);
					regardingProviderVO = getEntitiesForProvider(entityID, true);
					if (regardingProviderVO != null) {

						//for fixing ESPRD00707314
						regardingProviderVO
								.setEntityType(ContactManagementConstants.ENTITY_TYPE_UNPROV);
						regardingProviderVO
								.setEntityTypeDesc(ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
						Long.valueOf(80), ContactManagementConstants.ENTITY_TYPE_UNPROV));
						//for fixing ESPRD00707314
//						boolean recordExists = false;
						if(log.isDebugEnabled()){
						log.debug("regardingProviderVO.getEntityId()"+regardingProviderVO.getEntityId());
						log.debug("caseRegardingVO.getCaseRegardingTPLVO()"+
								caseRegardingVO.getCaseRegardingTPLVO()
								.getEntityId());
						}
						if (!isNullOrEmptyField(regardingProviderVO.getEntityId())
								&& regardingProviderVO.getEntityId().equals(
										caseRegardingVO.getCaseRegardingTPLVO()
												.getEntityId())) {
							recordExists=Boolean.TRUE;
						}else if (logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList() != null
								&& logCaseDataBean.getCaseDetailsVO()
										.getInqAbtEntityList().size() > 0) {
							if(log.isDebugEnabled()){
							log.debug("logCaseDataBean.getCaseDetailsVO().getInqAbtEntityList().size()"
											+ logCaseDataBean
													.getCaseDetailsVO()
													.getInqAbtEntityList()
													.size());
							}
							Iterator itr = logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().iterator();
							while (itr.hasNext()) {
								Object obj = itr.next();
								if (obj instanceof CaseRegardingProviderVO) {
									CaseRegardingProviderVO caseRegardingProviderVO2 = (CaseRegardingProviderVO) obj;
									if(log.isDebugEnabled()){
									System.err.println("--caseRegardingProviderVO2.getEntityId()::"
													+ caseRegardingProviderVO2
															.getEntityId());
									System.err.println("--caseRegardingProviderVO2.getEntityId()::"
													+ caseRegardingProviderVO2
															.getCommonEntitySK());
									}
									if (regardingProviderVO.getEntityId()
											.equalsIgnoreCase(
													caseRegardingProviderVO2
															.getEntityId())) {
										recordExists = true;
										System.err.println("=======inside duplicate provide entity");
										/*ContactManagementHelper
												.setErrorMessage(
														MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
														null, null, null);*/
										break;
									}

								}
							}
						}

						if (!recordExists) {
							logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().add(
											regardingProviderVO);
							logCaseDataBean.getInqAbtEntityListBeforeSave()
									.add(regardingProviderVO);//ESPRD00358412
							logCaseDataBean.getPhysicianOverseeingList().add(
									new SelectItem(regardingProviderVO
											.getEntityId(), regardingProviderVO
											.getEntityType()
											+ " - "
											+ regardingProviderVO.getName()));
							logCaseDataBean
									.setShowAddiCaseEntitesDataTable(true);
							logCaseDataBean.getProviderhos().add(
									new SelectItem(regardingProviderVO
											.getEntityId(), regardingProviderVO
											.getName()));
						}

					}

				}
				if(log.isDebugEnabled()){
				log.debug("*******caseRegarding********"
						+ caseRegarding);
				}
				if (caseRegarding != null) {
					/** for defect ESPRD00852811
					 *  If entity record selected is from G_SPEC_ENTY_TB
					 *  table of entity type 'UP'
					 * */
				if (!isNullOrEmptyField(caseRegarding.getEntityId())
						&& caseRegarding.getEntityId().equals(
								caseRegardingVO.getCaseRegardingTPLVO()
										.getEntityId())) {
					recordExists=Boolean.TRUE;
				}else if (logCaseDataBean.getCaseDetailsVO()
						.getInqAbtEntityList() != null
						&& logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().size() > 0) {
					if(log.isDebugEnabled()){
					log.debug("logCaseDataBean.getCaseDetailsVO().getInqAbtEntityList().size()"
									+ logCaseDataBean
											.getCaseDetailsVO()
											.getInqAbtEntityList()
											.size());
					}
					Iterator itr = logCaseDataBean.getCaseDetailsVO()
							.getInqAbtEntityList().iterator();
					while (itr.hasNext()) {
						Object obj = itr.next();
						if (obj instanceof CaseRegardingProviderVO) {
							CaseRegardingProviderVO caseRegardingProviderVO2 = (CaseRegardingProviderVO) obj;
							if (caseRegarding.getEntityId()
									.equalsIgnoreCase(
											caseRegardingProviderVO2
													.getEntityId())) {
								recordExists = true;
								break;
							}

						}
					}
				}
				if (!recordExists) {
					logCaseDataBean.getCaseDetailsVO()
							.getInqAbtEntityList().add(caseRegarding);
					logCaseDataBean.getInqAbtEntityListBeforeSave().add(
							caseRegarding);//ESPRD00358412
					logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
					logCaseDataBean.getProviderhos().add(
							new SelectItem(caseRegarding.getEntityId(),
									caseRegarding.getName()));
				}
			}

			} else if ("TD".equals(entityType)) {
				try {
					log.debug(" TradingPartner entityID " + entityID);
					log.debug(" TradingPartner logCaseDataBean.getCommonEntitySK() "
									+ logCaseDataBean.getCommonEntitySK());
					caseRegardingTradingPartnerVO = getEntityForTradingPartner(
							entityID, null, true);
					if (caseRegardingTradingPartnerVO != null) {
//						boolean recordExists = false;
						if(log.isDebugEnabled()){
						log.debug("caseRegardingTradingPartnerVO.getEntityId()"+caseRegardingTradingPartnerVO.getEntityId());
						log.debug("caseRegardingVO.getCaseRegardingTradingPartnerVO()"+
								caseRegardingVO.getCaseRegardingTradingPartnerVO()
								.getEntityId());
						}
						if (!isNullOrEmptyField(caseRegardingTradingPartnerVO.getEntityId())
								&& caseRegardingTradingPartnerVO.getEntityId().equals(
										caseRegardingVO.getCaseRegardingTradingPartnerVO()
												.getEntityId())) {
							recordExists=Boolean.TRUE;
						}else if (logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList() != null
								&& logCaseDataBean.getCaseDetailsVO()
										.getInqAbtEntityList().size() > 0) {
							Iterator itr = logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().iterator();
							while (itr.hasNext()) {
								Object obj = itr.next();
								if (obj instanceof CaseRegardingTradingPartnerVO) {
									CaseRegardingTradingPartnerVO caseRegardingTradPartner = (CaseRegardingTradingPartnerVO) obj;
									if (entityID
											.equalsIgnoreCase(caseRegardingTradPartner
													.getEntityId())) {
										recordExists = true;
										/*ContactManagementHelper
												.setErrorMessage(
														MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
														null, null, null);*/
										break;
									}

								}
							}
						}
						if (!recordExists) {
							logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().add(
											caseRegardingTradingPartnerVO);
							logCaseDataBean.getInqAbtEntityListBeforeSave()
									.add(caseRegardingTradingPartnerVO);//ESPRD00358412
							logCaseDataBean
									.setShowAddiCaseEntitesDataTable(true);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} /** commented as it is un-reachable code and it is 
			redundant. line no : 3682
			*/
			/* else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
					.equals(entityType)) {
				caseRegarding = getSpecificEntity(entityID, true);

				System.out
						.println("------caseRegarding-------" + caseRegarding);
				if (caseRegarding == null) {

					System.err.println("providere==" + entityID);
					regardingProviderVO = getEntitiesForProvider(entityID, true);

					log.debug("-----regardingProviderVO-----"
							+ regardingProviderVO);
					if (regardingProviderVO != null) {
//						boolean recordExists = false;

						if (logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList() != null
								&& logCaseDataBean.getCaseDetailsVO()
										.getInqAbtEntityList().size() > 0) {
							System.out
									.println("----logCaseDataBean.getCaseDetailsVO().getInqAbtEntityList().size()----"
											+ logCaseDataBean
													.getCaseDetailsVO()
													.getInqAbtEntityList()
													.size());
							Iterator itr = logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().iterator();
							while (itr.hasNext()) {
								Object obj = itr.next();
								if (obj instanceof CaseRegardingProviderVO) {
									CaseRegardingProviderVO caseRegardingProviderVO2 = (CaseRegardingProviderVO) obj;
									System.out
											.println("--caseRegardingProviderVO2.getEntityId()::----"
													+ caseRegardingProviderVO2
															.getEntityId());
									System.out
											.println("--caseRegardingProviderVO2.getCommonEntitySK()::----"
													+ caseRegardingProviderVO2
															.getCommonEntitySK());
									if (regardingProviderVO.getEntityId()
											.equalsIgnoreCase(
													caseRegardingProviderVO2
															.getEntityId())) {
										recordExists = true;
										System.out
												.println("=======inside duplicate provide entity");
										ContactManagementHelper
												.setErrorMessage(
														MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
														null, null, null);
										break;
									}

								}
							}
						}

						if (!recordExists) {
							logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().add(
											regardingProviderVO);
							logCaseDataBean.getInqAbtEntityListBeforeSave()
									.add(regardingProviderVO);//ESPRD00358412
							logCaseDataBean.getPhysicianOverseeingList().add(
									new SelectItem(regardingProviderVO
											.getEntityId(), regardingProviderVO
											.getEntityType()
											+ " - "
											+ regardingProviderVO.getName()));
							logCaseDataBean
									.setShowAddiCaseEntitesDataTable(true);
							logCaseDataBean.getProviderhos().add(
									new SelectItem(regardingProviderVO
											.getEntityId(), regardingProviderVO
											.getName()));
						}

					}

				}

				log.debug("*******caseRegarding********"
						+ caseRegarding);
				if (caseRegarding != null) {
//					boolean recordExists = false;
					if (logCaseDataBean.getCaseDetailsVO()
							.getInqAbtEntityList() != null
							&& logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().size() > 0) {
						Iterator itr = logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().iterator();

						while (itr.hasNext()) {
							Object obj = itr.next();
							if (obj instanceof CaseRegardingTPL) {
								CaseRegardingTPL caseRegardingTPL = (CaseRegardingTPL) obj;

								if (entityID.equalsIgnoreCase(caseRegardingTPL
										.getEntityId())) {
									recordExists = true;

									ContactManagementHelper
											.setErrorMessage(
													MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
													null, null, null);
									break;
								}

							}
						}
					}
					if (!recordExists) {
						logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().add(caseRegarding);
						logCaseDataBean.getInqAbtEntityListBeforeSave().add(
								caseRegarding);//ESPRD00358412
						logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
						logCaseDataBean.getProviderhos().add(
								new SelectItem(caseRegarding.getEntityId(),
										caseRegarding.getName()));
					}
				}

			}*/ else {
				caseRegarding = getSpecificEntity(entityID, true);
				if (caseRegarding != null) {
//					boolean recordExists = false;
					if(log.isDebugEnabled()){
					log.debug("caseRegarding.getEntityId()"+caseRegarding.getEntityId());
					log.debug("caseRegardingVO.getCaseRegardingTPLVO()"+
							caseRegardingVO.getCaseRegardingTPLVO()
							.getEntityId());
					}
					if (!isNullOrEmptyField(caseRegarding.getEntityId())
							&& caseRegarding.getEntityId().equals(
									caseRegardingVO.getCaseRegardingTPLVO()
											.getEntityId())) {
						recordExists=Boolean.TRUE;
					}else if (logCaseDataBean.getCaseDetailsVO()
							.getInqAbtEntityList() != null
							&& logCaseDataBean.getCaseDetailsVO()
									.getInqAbtEntityList().size() > 0) {
						Iterator itr = logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().iterator();
						while (itr.hasNext()) {
							Object obj = itr.next();
							if (obj instanceof CaseRegardingTPL) {
								CaseRegardingTPL caseRegardingTPL = (CaseRegardingTPL) obj;
								if (entityID.equalsIgnoreCase(caseRegardingTPL
										.getEntityId())) {
									recordExists = true;
									/*ContactManagementHelper
											.setErrorMessage(
													MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
													null, null, null);*/
									break;
								}

							}
						}
					}
					if (!recordExists) {
						logCaseDataBean.getCaseDetailsVO()
								.getInqAbtEntityList().add(caseRegarding);
						logCaseDataBean.getInqAbtEntityListBeforeSave().add(
								caseRegarding);//ESPRD00358412
						logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
						/**
						 * Commented for the defect ESPRD00868002,
						 * Provider/Hospital drop down populates only for
						 * Provider and Unenrolled Provider Entity Types only
						 * */
						/*logCaseDataBean.getProviderhos().add(
								new SelectItem(caseRegarding.getEntityId(),
										caseRegarding.getName()));*/
					}
				}
			}
			if(log.isDebugEnabled()){
				log.debug("recordExists value "+recordExists);
			}
			//Modified for the defect ESPRD00873143
			if (recordExists) {
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.ADDENTITY_AGAIN,
						"InquiryEntityExistsHidenMesg", "InquiryEntityExistsHidenMesg", null);
			}
			log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getInquiryAboutDetails");
		}
	}

	public String getInquiryAboutDetails() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getInquiryAboutDetails");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String strIndex = (String) map.get("rowindex");
		int index = 0;
		if(strIndex!=null){
			index = Integer.parseInt(strIndex);
		}
		///int index = logCaseDataBean.getAddiCaseEntityRowIndex();
		CaseRegardingTPL detailsVO = (CaseRegardingTPL) logCaseDataBean
				.getCaseDetailsVO().getInqAbtEntityList().get(index);

		logCaseDataBean.setSelectedAdditionaCaseEntityIndex(index);

		if (ContactManagementConstants.ENTITY_TYPE_PROV
				.equalsIgnoreCase(detailsVO.getEntityType())) {
			CaseRegardingProviderVO providerVO = (CaseRegardingProviderVO) detailsVO;
			// Commented for the Defect ID: ESPRD00795389
			/*logCaseDataBean.getCaseRegardingVO().setCaseRegardingProviderVO(
					providerVO);*/
			// Added for the Defect ID: ESPRD00795389
			logCaseDataBean.setCaseForProviderVO(providerVO);
			logCaseDataBean.setShowInqAbtProvider(true);
			logCaseDataBean.setShowInqAbtMember(false);
			logCaseDataBean.setShowInqAbtFor(false);
			} else if (ContactManagementConstants.ENTITY_TYPE_MEM
				.equalsIgnoreCase(detailsVO.getEntityType())) {
			CaseRegardingMemberVO memberVO = (CaseRegardingMemberVO) detailsVO;
			// Commented for the Defect ID: ESPRD00795389
			/*logCaseDataBean.getCaseRegardingVO().setCaseRegardingMemberVO(
					memberVO);*/
			// Added for the Defect ID: ESPRD00795389
			logCaseDataBean.setCaseForMemberVO(memberVO);
			logCaseDataBean.setShowInqAbtMember(true);
			logCaseDataBean.setShowInqAbtProvider(false);
			logCaseDataBean.setShowInqAbtFor(false);
			} else {
			CaseRegardingTPL regarding = (CaseRegardingTPL) detailsVO;
			// Commented for the Defect ID: ESPRD00795389
			/*logCaseDataBean.getCaseRegardingVO().setCaseRegardingTPLVO(
					regarding);*/
			// Added for the Defect ID: ESPRD00795389
			logCaseDataBean.setCaseForTPLVO(regarding);
			logCaseDataBean.setShowInqAbtFor(true);
			logCaseDataBean.setShowInqAbtMember(false);
			logCaseDataBean.setShowInqAbtProvider(false);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getInquiryAboutDetails");
		return "";
	}

	/**
	 * This Method is used to get entities.
	 * 
	 * @param entityID
	 *            Takes entityID as param
	 * @param entityType
	 *            Takes entityType as param
	 * @param isMyTask
	 *            isMyTask indicates if its in myTask
	 * @param isCase
	 *            isCase indicates if its a case
	 */
	private void getEntities(String entityID, String entityType,
			boolean isMyTask, boolean isCase) {
		log.debug("++ " + ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getEntities");
		log.debug(" ######## getEntities entityID " + entityID);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseRegardingMemberVO caseRegardingMemberVO = null;
		CaseRegardingProviderVO regardingProviderVO = null;
		CaseRegardingTPL caseRegarding = null;
		CaseRegardingTradingPartnerVO caseRegardingTradingPartnerVO = null;
		Long cmEntityID = null;

		if (isMyTask || isCase) {
			Long comEntitySK = null;
			if (isMyTask) {
				comEntitySK = logCaseDataBean.getCommonEntitySKForMyTask();
			} else if (isCase && !entityType.equals("TD")) {
				log.info("++ 2");
				if (!isNullOrEmptyField(entityID)) {
					comEntitySK = new Long(entityID);
				}
			}
			
			entityType = logCaseDataBean.getEntityType();
			log.debug(">>>>>>>>>>>>>> ENTITY TYPE------------"
					+ entityType);
			logCaseDataBean.setShowCaseRegardingTradPart(Boolean.FALSE);
			if (StringUtils.isNotEmpty(entityType)) {
				log.info("++ 3");
				if (ContactManagementConstants.ENTITY_TYPE_MEM
						.equals(entityType)) {

					MemberInformationDelegate memberInformationDelegate = new MemberInformationDelegate();
					log.debug("INSIDE getEntities() BEFORE Calling Deleg");

					try {
						log.debug("1------------------------comEntitySK="
										+ comEntitySK);
						log
								.debug("INSIDE getEntities() BEFORE Calling Delegate");
						cmEntityID = memberInformationDelegate
								.getMemberID(comEntitySK);
						log.debug("2------------------------comEntitySK="
										+ cmEntityID);
						log.debug("INSIDE getEntities()After Calling Delegate");
					} catch (MemberBusinessException memberBusinessException) {
						log.error(memberBusinessException.getMessage(), memberBusinessException);
					}
				} else if (ContactManagementConstants.ENTITY_TYPE_PROV
						.equals(entityType)) {
					ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
					try {
						if (comEntitySK != null)
							cmEntityID = providerInformationDelegate
									.getProviderSysID(comEntitySK);
					} catch (EnterpriseBaseBusinessException e) {
						e.printStackTrace();
					}
					// Modified for the defect ESPRD00688792 start
				}else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
						.equals(entityType)) {
					Long upEntityID = null;
					ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
					try {
						if (comEntitySK != null){
							upEntityID = providerInformationDelegate
									.getProviderSysID(comEntitySK);
							log.debug("11111111upEntityID -----"+upEntityID);
						if(upEntityID != null){
							logCaseDataBean.setSysIdForUP(upEntityID.toString());
						}
						}
					} catch (EnterpriseBaseBusinessException e) {
						e.printStackTrace();
					}	
					//Modified for the defect ESPRD00688792 end
				} else
				/*
				 * commented for CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010 if
				 * (ContactManagementConstants.ENTITY_TYPE_TPL
				 * .equals(entityType)) { // TPLCarrierDelegate carrierDelegate =
				 * new TPLCarrierDelegate(); TPLCarrier carrier = new
				 * TPLCarrier() ;
				 * 
				 * try {
				 * 
				 * if (carrier != null) { cmEntityID = new
				 * Long(carrier.getCarrierID()); } }
				 * 
				 * catch(Exception e){e.printStackTrace();} } else EOF
				 * CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
				 */
				{
					if (comEntitySK != null)
						entityID = comEntitySK.toString();
				}
				log.debug("3------------------------comEntitySK="
						+ cmEntityID);
				if (cmEntityID != null) {
					entityID = cmEntityID.toString();
					log.debug("4------------------------comEntitySK="
							+ entityID);
					logCaseDataBean.setEntityID(entityID.toString());
				}
			}
		}
		log.debug("5------------------------entityID=" + cmEntityID);
		log.debug(" ++entityID " + entityID);
		log.debug(" ++entityType " + entityType);
		if (StringUtils.isNotEmpty(entityID)
				&& StringUtils.isNotEmpty(entityType)) {

			Long commonEntitySK;
			if (ContactManagementConstants.ENTITY_TYPE_MEM.equals(entityType))

			{
				// 	infinite Added for ESPRD00332952
				if (!isNullOrEmptyField(entityID)) {
					caseRegardingMemberVO = getEntityDetailsForMember(entityID,
							true);
				}

				if (caseRegardingMemberVO != null) {
					if (caseRegardingMemberVO.getCommonEntitySK() != null) {
						commonEntitySK = new Long(caseRegardingMemberVO
								.getCommonEntitySK());
						getExistingCaseAndCRrecords(commonEntitySK, new Long(
								entityID), entityType);
					}
					logCaseDataBean.getCaseRegardingVO()
							.setCaseRegardingMemberVO(caseRegardingMemberVO);
					logCaseDataBean.setShowCaseRegardingMember(true);
					logCaseDataBean.setShowCaseRegardingProvider(false);
					logCaseDataBean.setShowCaseRegardingOther(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);
				}
			} else if (ContactManagementConstants.ENTITY_TYPE_PROV
					.equals(entityType)) {
				regardingProviderVO = getEntitiesForProvider(entityID, true);
				if (regardingProviderVO != null) {
					if (regardingProviderVO.getCommonEntitySK() != null) {
						commonEntitySK = new Long(regardingProviderVO
								.getCommonEntitySK());
						getExistingCaseAndCRrecords(commonEntitySK, Long
								.valueOf(entityID), entityType);
					}
					logCaseDataBean.getCaseRegardingVO()
							.setCaseRegardingProviderVO(regardingProviderVO);
					logCaseDataBean.setShowCaseRegardingProvider(true);
					logCaseDataBean.setShowCaseRegardingMember(false);
					logCaseDataBean.setShowCaseRegardingOther(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);
					logCaseDataBean.getProviderhos().add(
							new SelectItem(regardingProviderVO.getEntityId(),
									regardingProviderVO.getName()));
				}
			} else if (ContactManagementConstants.ENTITY_TYPE_TPL
					.equals(entityType)) {
				// caseRegarding = getEntityForTPL(entityID, true);
				// //ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
				//            	Modified for defect ESPRD00535762 starts
				String tplCmnEnty = "";
				if (logCaseDataBean.getCaseSK() != null) {
					tplCmnEnty = entityID;
					log.debug("++ update mode logCaseDataBean.getCommonEntitySK()==>"
									+ tplCmnEnty);
				} else {
					tplCmnEnty = logCaseDataBean.getCommonEntitySK();
					log.debug("++ add mode logCaseDataBean.getCommonEntitySK()==>"
									+ tplCmnEnty);
				}
				caseRegarding = getEntityForTPL(tplCmnEnty, true);//ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
				//caseRegarding =
				// getEntityForTPL(logCaseDataBean.getCommonEntitySK(),
				// true);//ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
				//defect ESPRD00535762 ends

				if (caseRegarding != null) {
					commonEntitySK = new Long(caseRegarding.getCommonEntitySK());
					getExistingCaseAndCRrecords(commonEntitySK, Long
							.valueOf(entityID), entityType);
					logCaseDataBean.getCaseRegardingVO().setCaseRegardingTPLVO(
							caseRegarding);
					logCaseDataBean.setShowCaseRegardingOther(true);
					//Added to fix the defect ESPRD00333005
					logCaseDataBean.setShowTPLlabel(true);
				}
			} else if (ContactManagementConstants.ENTITY_TYPE_TP
					.equals(entityType)) {
				log.info("++ 3");
				caseRegarding = getEntityForTPLPolicyHolder(entityID, true);
				log.debug("++ caseRegarding ::" + caseRegarding);
				if (caseRegarding != null) {
					commonEntitySK = new Long(caseRegarding.getCommonEntitySK());
					log.debug("++ commonEntitySK ::" + commonEntitySK);
					log.debug("++ entityID ::" + entityID);
					getExistingCaseAndCRrecords(commonEntitySK, Long
							.valueOf(entityID), entityType);
					logCaseDataBean.getCaseRegardingVO().setCaseRegardingTPLVO(
							caseRegarding);
					logCaseDataBean.setShowCaseRegardingOther(true);
					//Added to fix the defect ESPRD00333005
					logCaseDataBean.setShowTPLpolcyHldrlabel(true);
				}
			} else if ("TD".equals(entityType)) {
				try {
					log.debug("logCaseDataBean.getCommonEntitySK()--->"
									+ logCaseDataBean.getCommonEntitySK());
					log.debug("entityID--->" + entityID);
					if (logCaseDataBean.getCaseSK() != null) {
						if (logCaseDataBean.getCommonEntitySK() != null) {
							commonEntitySK = new Long(logCaseDataBean
									.getCommonEntitySK());
							caseRegardingTradingPartnerVO = getEntityForTradingPartner(
									entityID, commonEntitySK, true);
							getExistingCaseAndCRrecords(commonEntitySK, null,
									entityType);
						} else {
							if (entityID != null) {
								commonEntitySK = new Long(entityID);
								caseRegardingTradingPartnerVO = getEntityForTradingPartner(
										entityID, commonEntitySK, true);
								getExistingCaseAndCRrecords(commonEntitySK,
										null, entityType);
							}
						}

						//            			getExistingCaseAndCRrecords(new Long(entityID), new
						// Long(
						//                                entityID), entityType);
					} else if (logCaseDataBean.getCommonEntitySK() != null) {
						caseRegardingTradingPartnerVO = getEntityForTradingPartner(
								null, new Long(logCaseDataBean
										.getCommonEntitySK()), true);
						commonEntitySK = new Long(logCaseDataBean
								.getCommonEntitySK());
						getExistingCaseAndCRrecords(commonEntitySK, null,
								entityType);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (caseRegardingTradingPartnerVO != null) {

					logCaseDataBean.getCaseRegardingVO()
							.setCaseRegardingTradingPartnerVO(
									caseRegardingTradingPartnerVO);
					logCaseDataBean.setShowCaseRegardingTradPart(true);
					logCaseDataBean.setShowCaseRegardingProvider(false);
					logCaseDataBean.setShowCaseRegardingMember(false);
					logCaseDataBean.setShowCaseRegardingOther(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);

				}
			} else {
				caseRegarding = getSpecificEntity(entityID, true);
				if (caseRegarding != null) {
					commonEntitySK = new Long(caseRegarding.getCommonEntitySK());
					getExistingCaseAndCRrecords(commonEntitySK, Long
							.valueOf(entityID), entityType);
					if (ContactManagementConstants.ENTITY_TYPE_UNMEM
							.equals(entityType)) {
						log.debug("User selected entity type is $$ = UM =");
						logCaseDataBean.getCaseRegardingVO()
								.setCaseRegardingTPLVO(caseRegarding);
						logCaseDataBean.setShowCaseRegardingProvider(false);
						logCaseDataBean.setShowCaseRegardingMember(false);
						logCaseDataBean.setShowCaseRegardingUnEnrolMem(true);
						logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);
						logCaseDataBean.setShowCaseRegardingOther(false);
					} else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
							.equals(entityType)) {
						log.debug("User selected entity type is $$ = UM =");
						logCaseDataBean.getCaseRegardingVO()
								.setCaseRegardingTPLVO(caseRegarding);
						logCaseDataBean.setShowCaseRegardingProvider(false);
						logCaseDataBean.setShowCaseRegardingMember(false);
						logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
						logCaseDataBean.setShowCaseRegardingUnEnrolProv(true);
						logCaseDataBean.setShowCaseRegardingOther(false);
						logCaseDataBean.getProviderhos().add(
								new SelectItem(logCaseDataBean
										.getCaseRegardingVO()
										.getCaseRegardingTPLVO().getEntityId(),
										logCaseDataBean.getCaseRegardingVO()
												.getCaseRegardingTPLVO()
												.getName()));
					} else {
						log.debug("User selected entity type is $$ = UM =");
						logCaseDataBean.getCaseRegardingVO()
								.setCaseRegardingTPLVO(caseRegarding);
						logCaseDataBean.setShowCaseRegardingProvider(false);
						logCaseDataBean.setShowCaseRegardingMember(false);
						logCaseDataBean.setShowCaseRegardingOther(true);
						logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
						logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);
					}

				}

				//newly added for defect
				// ESPRD00669990**********************************
				else {
					if (ContactManagementConstants.ENTITY_TYPE_UNMEM
							.equals(entityType)) {
						/*
						 * logCaseDataBean.getCaseRegardingVO()
						 * .setCaseRegardingTPLVO(caseRegarding);
						 * logCaseDataBean.setShowCaseRegardingProvider( false);
						 * logCaseDataBean .setShowCaseRegardingMember(false);
						 * logCaseDataBean.setShowCaseRegardingUnEnrolMem(
						 * true);
						 * logCaseDataBean.setShowCaseRegardingUnEnrolProv(
						 * false);
						 * logCaseDataBean.setShowCaseRegardingOther(false);
						 */
					} else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
							.equals(entityType)) {
						log.info("++convertProviderToUnEnrolledProvider-->before calling");
						try {
							regardingProviderVO = getEntitiesForProvider(
									entityID, true);
							if (regardingProviderVO != null) {
								if (regardingProviderVO.getCommonEntitySK() != null) {
									commonEntitySK = new Long(
											regardingProviderVO
													.getCommonEntitySK());
									getExistingCaseAndCRrecords(commonEntitySK,
											Long.valueOf(entityID), entityType);
								}
								log.info("++convertProviderToUnEnrolledProvider-->Called");
								logCaseDataBean
										.getCaseRegardingVO()
										.setCaseRegardingTPLVO(
												convertProviderToUnEnrolledProvider(regardingProviderVO));
								logCaseDataBean
										.setShowCaseRegardingProvider(false);
								logCaseDataBean
										.setShowCaseRegardingMember(false);
								logCaseDataBean
										.setShowCaseRegardingOther(false);
								logCaseDataBean
										.setShowCaseRegardingUnEnrolMem(false);
								logCaseDataBean
										.setShowCaseRegardingUnEnrolProv(true);
								if (regardingProviderVO.getEntityId() != null
										&& !regardingProviderVO.getEntityId()
												.equals("")
										&& regardingProviderVO.getName() != null)
									logCaseDataBean.getProviderhos().add(
											new SelectItem(regardingProviderVO
													.getEntityId(),
													regardingProviderVO
															.getName()));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
				//new addition ends ESPRD00669990
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getEntities");
	}

	private CaseRegardingTPL convertProviderToUnEnrolledProvider(
			CaseRegardingProviderVO caseRegardingProviderVO) {
		CaseRegardingTPL regardingVO = new CaseRegardingTPL();

		regardingVO.setEntityTypeDesc("UP-UnenrldPrv");

		if (caseRegardingProviderVO.getCommonEntitySK() != null) {
			regardingVO
					.setEntityId(caseRegardingProviderVO.getCommonEntitySK());
		} else {
			regardingVO.setEntityId(ContactManagementConstants.EMPTYSTRING);
		}
		regardingVO.setEntityType("UP");
		if (caseRegardingProviderVO.getName() != null) {
			regardingVO.setName(caseRegardingProviderVO.getName());
		}
		if (caseRegardingProviderVO.getSsn() != null) {
			regardingVO.setSsn(caseRegardingProviderVO.getSsn());
		}
		if (caseRegardingProviderVO.getLobCode() != null) {
			regardingVO.setLobCode(caseRegardingProviderVO.getLobCode());
		}
		if (caseRegardingProviderVO.getDistricOffice() != null) {
			regardingVO.setDistricOffice(caseRegardingProviderVO
					.getDistricOffice());
		}
		if (caseRegardingProviderVO.getProviderTypeCode() != null) {
			regardingVO.setProvTypeCode(caseRegardingProviderVO
					.getProviderTypeCode());
			regardingVO.setProvTypeCodeDesc(ContactManagementHelper
					.setShortDescription(FunctionalAreaConstants.PROVIDER,
							ReferenceServiceDataConstants.P_TY_CD,
							caseRegardingProviderVO.getProviderTypeCode()));
		}
		if (caseRegardingProviderVO.getCommonEntitySK() != null) {
			regardingVO.setCommonEntitySK(caseRegardingProviderVO
					.getCommonEntitySK());
		}
		return regardingVO;
	}

	private void getEntitiesForCase(String entityID, String entityType,
			boolean isMyTask, boolean isCase) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getEntities");
		log.debug("EntityID getEntityDetails is" + entityID);
		log.debug("++EntityType getEntityDetails()" + entityType);
		log.debug("isMyTask--" + isMyTask);
		log.debug("isCase--" + isCase);

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseRegardingMemberVO caseRegardingMemberVO = null;
		CaseRegardingProviderVO regardingProviderVO = null;
		CaseRegardingTPL caseRegarding = null;
		Long cmEntityID = null;
		Long commonEntitySK;

		if (isMyTask || isCase) {
			Long comEntitySK = null;
			if (isMyTask) {
				comEntitySK = logCaseDataBean.getCommonEntitySKForMyTask();
			} else if (isCase) {

				if (!isNullOrEmptyField(entityID)) {
					comEntitySK = new Long(entityID);
				}
			}

			if (StringUtils.isNotEmpty(entityType)) {

				if (ContactManagementConstants.ENTITY_TYPE_MEM
						.equals(entityType)) {

					MemberInformationDelegate memberInformationDelegate = new MemberInformationDelegate();

					try {
						cmEntityID = memberInformationDelegate
								.getMemberID(comEntitySK);

					} catch (MemberBusinessException memberBusinessException) {
						log.error(memberBusinessException.getMessage(), memberBusinessException);
					}
				} else if (ContactManagementConstants.ENTITY_TYPE_PROV
						.equals(entityType)) {
					ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
					try {
						if (comEntitySK != null)
							cmEntityID = providerInformationDelegate
									.getProviderSysID(comEntitySK);
					} catch (EnterpriseBaseBusinessException e) {
						e.printStackTrace();
					}
				} else if (ContactManagementConstants.ENTITY_TYPE_TPL
						.equals(entityType)) {
					//TPLCarrierDelegate carrierDelegate = new
					// TPLCarrierDelegate();
					TPLCarrier carrier = new TPLCarrier();

					try {

						if (carrier != null) {
							cmEntityID = new Long(carrier.getCarrierID());
						}
					}

					catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					if (comEntitySK != null)
						entityID = comEntitySK.toString();
				}
				if (cmEntityID != null) {
					entityID = cmEntityID.toString();
					logCaseDataBean.setEntityID(entityID.toString());
				}
			}
		}
		if (StringUtils.isNotEmpty(entityID)
				&& StringUtils.isNotEmpty(entityType)) {
			if (ContactManagementConstants.ENTITY_TYPE_MEM.equals(entityType))

			{
				// 	infinite Added for ESPRD00332952
				if (!isNullOrEmptyField(entityID)) {
					caseRegardingMemberVO = getEntityDetailsForMember(entityID,
							true);
				}

				if (caseRegardingMemberVO != null) {
					if (caseRegardingMemberVO.getCommonEntitySK() != null) {
						commonEntitySK = new Long(caseRegardingMemberVO
								.getCommonEntitySK());
						getExistingCaseAndCRrecords(commonEntitySK, new Long(
								entityID), entityType);
					}
					logCaseDataBean.getCaseRegardingVO()
							.setCaseRegardingMemberVO(caseRegardingMemberVO);
					logCaseDataBean.setShowCaseRegardingMember(true);
					logCaseDataBean.setShowCaseRegardingProvider(false);
					logCaseDataBean.setShowCaseRegardingOther(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);
				}
			} else if (ContactManagementConstants.ENTITY_TYPE_PROV
					.equals(entityType)) {
				regardingProviderVO = getEntitiesForProvider(entityID, true);
				if (regardingProviderVO != null) {
					if (regardingProviderVO.getCommonEntitySK() != null) {
						commonEntitySK = new Long(regardingProviderVO
								.getCommonEntitySK());
						getExistingCaseAndCRrecords(commonEntitySK, Long
								.valueOf(entityID), entityType);
					}
					logCaseDataBean.getCaseRegardingVO()
							.setCaseRegardingProviderVO(regardingProviderVO);
					logCaseDataBean.setShowCaseRegardingProvider(true);
					logCaseDataBean.setShowCaseRegardingMember(false);
					logCaseDataBean.setShowCaseRegardingOther(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
					logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);
					logCaseDataBean.getProviderhos().add(
							new SelectItem(regardingProviderVO.getEntityId(),
									regardingProviderVO.getName()));
				}
			} else if (ContactManagementConstants.ENTITY_TYPE_TPL
					.equals(entityType)) {
				caseRegarding = getEntityForTPL(entityID, true);

				if (caseRegarding != null) {
					commonEntitySK = new Long(caseRegarding.getCommonEntitySK());
					getExistingCaseAndCRrecords(commonEntitySK, Long
							.valueOf(entityID), entityType);
					logCaseDataBean.getCaseRegardingVO().setCaseRegardingTPLVO(
							caseRegarding);
					logCaseDataBean.setShowCaseRegardingOther(true);
					//Added to fix the defect ESPRD00333005
					logCaseDataBean.setShowTPLlabel(true);
				}
			} else {
				caseRegarding = getSpecificEntity(entityID, true);
				if (caseRegarding != null) {
					commonEntitySK = new Long(caseRegarding.getCommonEntitySK());
					getExistingCaseAndCRrecords(commonEntitySK, Long
							.valueOf(entityID), entityType);
					if (ContactManagementConstants.ENTITY_TYPE_UNMEM
							.equals(entityType)) {

						logCaseDataBean.getCaseRegardingVO()
								.setCaseRegardingTPLVO(caseRegarding);
						logCaseDataBean.setShowCaseRegardingProvider(false);
						logCaseDataBean.setShowCaseRegardingMember(false);
						logCaseDataBean.setShowCaseRegardingUnEnrolMem(true);
						logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);
						logCaseDataBean.setShowCaseRegardingOther(false);
					} else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
							.equals(entityType)) {

						logCaseDataBean.getCaseRegardingVO()
								.setCaseRegardingTPLVO(caseRegarding);
						logCaseDataBean.setShowCaseRegardingProvider(false);
						logCaseDataBean.setShowCaseRegardingMember(false);
						logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
						logCaseDataBean.setShowCaseRegardingUnEnrolProv(true);
						logCaseDataBean.setShowCaseRegardingOther(false);
						logCaseDataBean.getProviderhos().add(
								new SelectItem(logCaseDataBean
										.getCaseRegardingVO()
										.getCaseRegardingTPLVO().getEntityId(),
										logCaseDataBean.getCaseRegardingVO()
												.getCaseRegardingTPLVO()
												.getName()));
					} else {

						logCaseDataBean.getCaseRegardingVO()
								.setCaseRegardingTPLVO(caseRegarding);
						logCaseDataBean.setShowCaseRegardingProvider(false);
						logCaseDataBean.setShowCaseRegardingMember(false);
						logCaseDataBean.setShowCaseRegardingOther(true);
						logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
						logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);
					}
				}
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getEntities");
	}

	/**
	 * This method fetches he existing Case and Cr records.
	 * 
	 * @param commonEntitySK
	 *            Takes commonEntitySK as param.
	 * @param entityID
	 *            Takes entityID as param.
	 * @param entityType
	 *            Takes entityType as param.
	 */
	private void getExistingCaseAndCRrecords(Long commonEntitySK,
			Long entityID, String entityType) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getExistingCaseAndCRrecords");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseDelegate caseDelegate = new CaseDelegate();
		CMDelegate delegate = new CMDelegate();
		LogCaseDomainToVOConverter converter = new LogCaseDomainToVOConverter();
		List existingCaseList = null;
		List existingCorrespondenceList = null;
		List existingCaseRecordsList = null;
		List existingCRList = null;
		try {

			if (commonEntitySK != null) {
				existingCaseRecordsList = caseDelegate
						.getExistingCaseRecords(commonEntitySK);
				if (existingCaseRecordsList != null
						&& !existingCaseRecordsList.isEmpty()) {
					existingCaseList = converter
							.convert90DaysCaseRecords(existingCaseRecordsList);
					logCaseDataBean
							.setExistingCaseRecordsList(existingCaseList);
					logCaseDataBean.setShowExistingCaseRecordsDataTable(true);
				}
			}
			if (entityID != null && entityType != null) {
				existingCRList = delegate.getAssociatedCorrespondence(
						commonEntitySK, entityType, Integer.valueOf("90"));
				if (existingCRList != null && !existingCRList.isEmpty()) {
					existingCorrespondenceList = converter
							.convert90DaysCR(existingCRList);
					logCaseDataBean
							.setExistingCRInfoList(existingCorrespondenceList);
					logCaseDataBean.setShowExistingCRDataTable(true);
					logCaseDataBean.setShowExistingCaseRecordsDataTable(true);//ESPRD00610465
																			  // defect
				}
			}
		} catch (CaseRecordFetchBusinessException e) {
			log.error("Exception while getting 90 days records : " + e);
		} catch (CorrespondenceRecordFetchBusinessException e) {
			log.error("Exception while getting 90 days records : " + e);
		} catch (NumberFormatException e) {
			log.error("Exception while getting 90 days records : " + e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getExistingCaseAndCRrecords");
	}

	/**
	 * This method is to get the short description for the given code from Valid
	 * Value Delegate.
	 * 
	 * @param functionalArea
	 *            Holds Functional area code
	 * @param elementName
	 *            Holds Domain name
	 * @param value
	 *            Holds valid value code
	 * @return String short Descriptino of valid value code
	 */
	private String setLongDescription(String functionalArea,
			String elementName, String value) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "setLongDescription");
		String validvalueDesc = null;
		ValidValueDomain validDomainName = null;
		try {

			ValidValueDelegate validDelegate = new ValidValueDelegate();
			validDomainName = validDelegate.getValidValueDetails(elementName);
			if (validDomainName != null) {
				Set assosiatedValidValues = validDomainName
						.getAssociatedValidValues();
				Iterator iterator = assosiatedValidValues.iterator();
				while (iterator.hasNext()) {
					ValidValue validValue = (ValidValue) iterator.next();
					if (validValue.getValidValueCode().equalsIgnoreCase(value)) {

						validvalueDesc = validValue.getLongDescription();
						break;
					}
				}
			}
		} catch (Throwable throwable) {
			log.error(throwable.getMessage(), throwable);
		}
		if (validvalueDesc == null || validvalueDesc.equalsIgnoreCase("")) {
			validvalueDesc = value;
			log.debug("validvalueDesc" + validvalueDesc);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "setLongDescription");
		return validvalueDesc;
	}

	/**
	 * This method is used to get the Entity details like Member, provider,
	 * etc... depend on the ID
	 * 
	 * @param entityID
	 *            holds the entity ID
	 * @param inqCaseEntity
	 *            holds the entityType
	 * @return CaseRegardingMemberVO
	 */
	public CaseRegardingMemberVO getEntityDetailsForMember(String entityID,
			boolean inqCaseEntity) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getEntityDetailsForMember");
		Long memberSysId;
		//Member member = null;
		CommonEntity cmResidentAddress = null;
		CommonEntity cmMailingAddress = null;
		MemberInformationDelegate memberInformationDelegate = null;
		CaseRegardingMemberVO caseRegardingMemberVO = null;
		LogCaseDomainToVOConverter caseDomainToVOConverter = null;
		caseDomainToVOConverter = new LogCaseDomainToVOConverter();

		memberSysId = Long.valueOf(entityID);
		log.debug("memberSysId getEntityDetails() is" + memberSysId);
		try {

			MemberInformationRequestVO memberInfRequestVO = new MemberInformationRequestVO();

			memberInfRequestVO.setMemberSysID(memberSysId);
			memberInfRequestVO.setAsOfDate(new Date());
			memberInfRequestVO.setPreviousNamesRequired(true);
			memberInfRequestVO.setEligibilitySpansRequired(true);
			memberInfRequestVO.setCommonEntityRequired(true);
			memberInfRequestVO.setAlternateMemberRequired(true);
			log.debug("before calling member details");
			memberInformationDelegate = new MemberInformationDelegate();
			/* Changes made to fix the defect ESPRD00333318 */
			//CMDelegate cmDelegate = new CMDelegate();
			MemberBasicInfo memberBasicInfo= null;
			//for defect ESPRD00884340
			//member = cmDelegate.getMemberDetailForCR(new Long(entityID));
			memberBasicInfo= memberInformationDelegate.getMemberDetailsForCR(memberSysId);
			/* Changes For defect ESPRD00333318 End */
			if (memberBasicInfo != null) {
				cmResidentAddress = memberInformationDelegate.getRecentAddress(
						memberSysId, "R");
				cmMailingAddress = memberInformationDelegate.getRecentAddress(
						memberSysId, "M");

				/*caseRegardingMemberVO = caseDomainToVOConverter
						.convertMemberDOToVo(member);*/
				
				caseRegardingMemberVO = caseDomainToVOConverter
				.convertMemberToVo(memberBasicInfo);
				// Added newly
				//Modified for defect ESPRD00513913 starts
				/*
				 * String entityTypeDesc = setLongDescription(
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
				 * ContactManagementConstants.ENTITY_TYPE_MEM);
				 */
				String entityTypeDesc = ReferenceServiceDelegate
						.getReferenceSearchShortDescription(
								FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
								Long.valueOf(80),
								ContactManagementConstants.ENTITY_TYPE_MEM); /*
																			  * FIND
																			  * BUGS_FIX
																			  */
				/*
				 * if
				 * (!entityTypeDesc.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM)) {
				 * entityTypeDesc = ContactManagementConstants.ENTITY_TYPE_MEM +
				 * ContactManagementConstants.HYPEN + entityTypeDesc; }
				 */
				//defect ESPRD00513913 ends
				caseRegardingMemberVO.setEntityTypeDesc(entityTypeDesc);

				// Ends

				caseRegardingMemberVO
						.setResidentialAddress(caseDomainToVOConverter
								.getAddress(cmResidentAddress));

				caseRegardingMemberVO.setMailingAddress(caseDomainToVOConverter
						.getAddress(cmMailingAddress));
				if (inqCaseEntity) {
					//for defect ESPRD00884340
					caseDomainToVOConverter.convertAltIdsForMember(memberBasicInfo);
				}
			}
		} catch (MemberBusinessException e) {
			log.error(e.getMessage(), e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getEntityDetailsForMember");
		return caseRegardingMemberVO;
	}

	/**
	 * This method fetches he entity for TPL.
	 * 
	 * @param entityID
	 *            holds the entity ID
	 * @param inqEntity
	 *            holds the inqEntity
	 * @return CaseRegardingTPL
	 */
	private CaseRegardingTPL getEntityForTPL(String entityID, boolean inqEntity) {
		CaseRegardingTPL regardingTPL = null;
		log.debug("Before calling TPL delegate entityID is $$ " + entityID);
		TPLCarrierDelegate carrierDelegate = new TPLCarrierDelegate();
		TPLCarrier carrier;

		try {

			carrier = carrierDelegate.getTPLCarrier(new Long(entityID));

			LogCaseDomainToVOConverter caseDomainToVOConverter = new LogCaseDomainToVOConverter();
			if (carrier != null) {
				regardingTPL = caseDomainToVOConverter
						.convertTPLCarrierDOToVO(carrier);

			}
		} catch (TPLCarrierBusinessException e) {
			log.info("================");
			e.printStackTrace();
			log.error(e.fillInStackTrace());
		} catch (Exception e) {
			log.info("---------------");
			e.printStackTrace();
			log.error(e.fillInStackTrace());
		}

		return regardingTPL;
	}

	/**
	 * This method gets entiy for Provider.
	 * 
	 * @param entityID
	 *            holds the entity ID
	 * @param inqEntity
	 *            holds the entity ID
	 * @return CaseRegardingProviderVO
	 */
	private CaseRegardingProviderVO getEntitiesForProvider(String entityID,
			boolean inqEntity) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getEntitiesForProvider");
		CaseRegardingProviderVO providerVO = null;
		ProviderInformationRequestVO providerInfRequestVO = new ProviderInformationRequestVO();
		if (!isNullOrEmptyField(entityID))
			providerInfRequestVO.setProviderSysID(Long.valueOf(entityID));
		providerInfRequestVO.setAlternateIdInfoRequired(true);
		providerInfRequestVO.setEnrollmentStatusRequired(true);
		providerInfRequestVO.setRepresentativeContactRequired(true);
		providerInfRequestVO.setBoardCertifiedSpecialityRequired(true);
		providerInfRequestVO.setProviderTypesRequired(true);
		LogCaseDomainToVOConverter caseDomainToVOConverter = null;
		caseDomainToVOConverter = new LogCaseDomainToVOConverter();
		/* FIND BUGS_FIX */
		// ProviderInformationDelegate providerInformationDelegate = new
		// ProviderInformationDelegate();
		/*
		 * Provider provider = providerInformationDelegate
		 * .getProviderDetails(providerInfRequestVO);
		 */
		CaseDelegate caseDelegate = new CaseDelegate();
		ContactManagementProviderSearchVO providerSearchResultsVO = null;
		try {
			long t3 = System.currentTimeMillis();
			providerSearchResultsVO = (ContactManagementProviderSearchVO) caseDelegate
					.getProviderDetailForCase(Long.valueOf(entityID));

			log.debug("-----providerSearchResultsVO----"
					+ providerSearchResultsVO);
			long t4 = System.currentTimeMillis();
			log.debug("T4-T3: " + (t4 - t3));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EnterpriseBaseBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * if (provider != null) { providerVO = caseDomainToVOConverter
		 * .convertProviderDOToVO(provider); if (inqEntity) {
		 * caseDomainToVOConverter.convertAltIdsForProvider(provider); } }
		 */

		if (providerSearchResultsVO != null) {
			providerVO = caseDomainToVOConverter
					.convertProviderResultVOToProviderVO(providerSearchResultsVO);
			if (inqEntity) {
				caseDomainToVOConverter
						.convertAltIdsForProvider(providerSearchResultsVO);
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getEntitiesForProvider");
		return providerVO;
	}

	/**
	 * This mehod sets reporting and Business Unit.
	 * 
	 * @param lobHierarchySK
	 *            Takes lobHierarchySK as param
	 * @param detailsVO
	 *            Takes detailsVO as param
	 */
	private void setReportingAndBusinessUnit(Long lobHierarchySK,
			CaseDetailsVO detailsVO) {
		log.info("setReportingAndBusinessUnit at DO 2 VO");
		log.debug("lobHierarchySK " + lobHierarchySK);
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			// Commented for Query Tunning
			//LineOfBusinessHierarchy reportingUnit = contactMaintenanceDelegate
			//		.getDeptReportingUnit(lobHierarchySK);
            //LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
			//		.getDeptBusinessUnit(lobHierarchySK);
			// Added for Query Tunning 
			//Start 
			Map rUnitAndBUnitMap = contactMaintenanceDelegate.getRUnitAndBUnit(lobHierarchySK);
			LineOfBusinessHierarchy reportingUnit = (LineOfBusinessHierarchy) rUnitAndBUnitMap.get("RUnit");
	        LineOfBusinessHierarchy businessUnit = (LineOfBusinessHierarchy) rUnitAndBUnitMap.get("BUnit");  
	        log.debug("reportingUnit" + reportingUnit);
			log.debug("businessUnit" + businessUnit);
	        //End
			if (reportingUnit != null) {

				detailsVO.setReportingUnit(reportingUnit
						.getLobHierarchyDescription());
			} else {
				detailsVO
						.setReportingUnit(ContactManagementConstants.EMPTY_STRING);
			}
			if (businessUnit != null) {

				detailsVO.setBusinessUnit(businessUnit
						.getLobHierarchyDescription());
			} else {
				detailsVO
						.setBusinessUnit(ContactManagementConstants.EMPTY_STRING);
			}

		} catch (LOBHierarchyFetchBusinessException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * This method gets entiy for SpecificEntity.
	 * 
	 * @param entityID
	 *            holds the entity ID
	 * @param inqEntity
	 *            holds the entity ID
	 * @return CaseRegardingTPL
	 */
	private CaseRegardingTPL getSpecificEntity(String entityID,
			boolean inqEntity) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getSpecificEntity");
		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
		SpecificEntity specificEntity = null;
		CaseRegardingTPL regarding = null;
		LogCaseDomainToVOConverter caseDomainToVOConverter = null;
		caseDomainToVOConverter = new LogCaseDomainToVOConverter();
		try {

			specificEntity = cmEntityDelegate.getSpecificEntityDetails(Long
					.valueOf(entityID));
			log
					.debug("After getting specific entity details the return DO is $$ "
							+ specificEntity);
			if (specificEntity != null) {
				regarding = caseDomainToVOConverter
						.convertSpecificEntityDOToVO(specificEntity);

				// Added newly
				//Modified for defect ESPRD00513913 starts
				/*
				 * String entityTypeDesc = setLongDescription(
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
				 * specificEntity .getSpecificEntityTypeCode());
				 * if(!entityTypeDesc.equalsIgnoreCase(specificEntity.getSpecificEntityTypeCode())) {
				 * entityTypeDesc = specificEntity.getSpecificEntityTypeCode() +
				 * ContactManagementConstants.HYPEN + entityTypeDesc; }
				 */
				String entityTypeDesc = ReferenceServiceDelegate
						.getReferenceSearchShortDescription(
								FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
								Long.valueOf(80), specificEntity /*
																  * FIND
																  * BUGS_FIX
																  */
								.getSpecificEntityTypeCode());
				regarding.setEntityTypeDesc(entityTypeDesc);

				// defect ESPRD00513913 Ends
			}
		} catch (CMEntityFetchBusinessException e) {
			log.error(e.getMessage(), e);
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getSpecificEntity");
		return regarding;
	}

	/**
	 * This Method checks if the logged in user is supervisor for dept.
	 * 
	 * @param deptSK
	 *            Takes deptSK as param
	 * @return String
	 */
	private String chkLoggedInUserIsSuperviForDept(String deptSK) {
		log.debug("chkLoggedInUserIsSuperviForDept is started");
		ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
		String deptSupviWorkUnitSK = null;
		try {
			Department department = null;
			department = delegate.getSupervisorToDept(new Long(deptSK));
			Long deptSuprviWorkUnitSK = department
					.getSupervisorUserWorkUnitSK();
			log.debug("dept Suprvisor WorkUnitSK in DO to VO is"
					+ deptSuprviWorkUnitSK);
			if (deptSuprviWorkUnitSK != null) {
				deptSupviWorkUnitSK = deptSuprviWorkUnitSK.toString();
			}
		} catch (LOBHierarchyFetchBusinessException e) {
			log.error(e.getMessage(), e);
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
		}
		return deptSupviWorkUnitSK;
	}

	/**
	 * This method is used to get the Case Record based on the CaseSK
	 * 
	 * @param caseSK
	 *            holds the CaseSK
	 * @return String
	 */
	//return type changed during defect ESPRD00560416
	public CaseRecord getCaseRecord(Long caseSK) {
		log.info("++=" + ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getCaseRecord");
		CaseRecord caseRecord = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setDisableCaseType(true);
		logCaseDataBean.setTemplateList(new ArrayList()); // Modified for the Defect ESPRD00734994
		CaseDelegate delegate = null;
		CaseDetailsVO detailsVO = null;
		List listOfCaseRoutingVOs = null;
		Set routingSet = null;
		Set alertSet = null;
		List listOfCaseAlertVOs = null;
		List caseStepList = null;
		Set caseStepSet = null;
		List listOfCaseEvents = null;
		Set caseEventsSet = null;
		CaseType caseTypeDO = null;

		Set caseTypeEventsSet = null;
		CaseTypeEvent caseTypeEventDO = null;

		Set caseTypeStepsSet = null;
		CaseTypeStep caseTypeStepDO = null;

		Set caseAttachments = null;
		List listOfCaseAttachVOs = null;

		Set currentCaseLint = null;
		List listOfCurrentCaseLinks = null;

		Set caseCRSet = null;
		List listOfCaseCRs = null;
		Set templateSet = null;

		NoteSet noteSet = null;
		LogCaseDomainToVOConverter caseDomainToVOConverter = null;
		caseDomainToVOConverter = new LogCaseDomainToVOConverter();
		LogCaseHelper caseHelper = new LogCaseHelper();
		Long lobSK = null; //EOF UC-PGM-CRM-018_ESPRD00367808_18JAN10
		try {

			delegate = new CaseDelegate();

			log.debug("* before delegate.getCaseDetails::");
			caseRecord = delegate.getCaseDetails(caseSK);
			log.debug("**  after delegate.getCaseDetails::" + caseSK);

			if (caseRecord != null) {
				int templateSize = 0;
				//logCaseDataBean.getProviderhos().clear();//ESPRD00544867_Fix_16Dec10
				logCaseDataBean.getCaseNotesSetTempList().clear();
				detailsVO = caseDomainToVOConverter
						.convertCaseDetailsDOToVO(caseRecord);

				populateNotifyViaAlertList(detailsVO.getCaseType());
				if (detailsVO.getStatus() != null) {
					logCaseDataBean
							.setCaseStatusClosed(detailsVO
									.getStatus()
									.startsWith(
											MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_C)
									|| detailsVO
											.getStatus()
											.equalsIgnoreCase(
													MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_W));
				} else {
					logCaseDataBean.setCaseStatusClosed(false);
				}//EOF UC-PGM-CRM-018.2_ESPRD00433227_12MAR2010
				//Added for the defect ESPRD00898731
				if ("D".equals(detailsVO.getCaseTypeBusinessUnitTypeCode())
						&& (detailsVO.getStatus().equalsIgnoreCase("CA")
								|| detailsVO.getStatus().equalsIgnoreCase("C2") || detailsVO.getStatus()
								.equalsIgnoreCase("C4"))) {
					logCaseDataBean.setStatusClosedAndApproved(true);
				} else {
					logCaseDataBean.setStatusClosedAndApproved(false);
				}
				// Added newly

				if (caseRecord.getLobHierarchy() != null
						&& caseRecord.getLobHierarchy()
								.getLineOfBusinessHierarchySK() != null
						&& caseRecord.getLobHierarchy()
								.getLobHierarchyLevelNumber().intValue() == MaintainContactManagementUIConstants.FOUR) {
					detailsVO.setWorkUnitSK(caseRecord.getLobHierarchy()
							.getLineOfBusinessHierarchySK());
					//Long
					lobSK = caseRecord.getLobHierarchy()
							.getLineOfBusinessHierarchySK();//modified
															// UC-PGM-CRM-018_ESPRD00367808_18JAN10

					Map mapOfDeptSKAndLobHier = logCaseDataBean
							.getLobSKAndDeptSKMap();

					String deptSK = (String) mapOfDeptSKAndLobHier.get(lobSK
							.toString());
					log.debug("++before deptSK==" + deptSK);
					log.debug("++caseRecord.getCaseCreatedByWorkUnit()===="
									+ caseRecord.getLobHierarchy()
											.getDepartments().size());
					if (deptSK == null && caseRecord.getLobHierarchy() != null) {
						Set department = caseRecord.getLobHierarchy()
								.getDepartments();
						Iterator deptIterator = department.iterator();
						log.debug("++deptIterator.hasNext()===="
								+ deptIterator.hasNext());
						if (deptIterator.hasNext()) {
							Department department2 = (Department) deptIterator
									.next();
							log.debug("***department2.getWorkUnitSK()**");
							deptSK = department2.getWorkUnitSK().toString();
							log.debug("***deptSK**"+deptSK);
							//Commented for Performance 
							//Iterator it = department2.getDepartmentUser()
							//		.iterator();
							/*if (it.hasNext()) {
								DepartmentUser departmentUser = (DepartmentUser) it
										.next();
								System.err.println("++--++deptSK=="
										+ departmentUser.getDepartmentSK());
								deptSK = String.valueOf(departmentUser
										.getDepartmentSK());
							}*/
						}
					}
					log.debug("++after deptSK==" + deptSK);
					detailsVO.setWorkUnit(deptSK);
					setReportingAndBusinessUnit(caseRecord.getLobHierarchy()
							.getLineOfBusinessHierarchySK(), detailsVO);
					String deptSuprWorkUnitSK = chkLoggedInUserIsSuperviForDept(deptSK);
					if (deptSuprWorkUnitSK != null) {
						detailsVO.setDeptSuperWorkUnitSK(deptSuprWorkUnitSK);

					}

				}

				if (caseRecord.getCaseDDU() != null) {

					if (caseRecord.getCaseDDU()
							.getCaseDDUUnusualCircumstances() != null
							&& !caseRecord.getCaseDDU()
									.getCaseDDUUnusualCircumstances().isEmpty()) {

						Set dduUnUsual = caseRecord.getCaseDDU()
								.getCaseDDUUnusualCircumstances();

						//Iterator iterator = dduUnUsual.iterator();

						SelectItem item = null;
						String shortDesc = null;
						List list = new ArrayList();
						List selectedUnUsualList = new ArrayList();

						ReferenceDataSearchVO referenceDataSearch = null;
						ReferenceServiceDelegate referenceServiceDelegate = null;
						ReferenceDataListVO referenceDataListVO = null;

						/** preparing criteria for Case Unusual Circumstances */
						LogCaseDomainToVOConverter caseDomainToVOConverter1 = null;
						caseDomainToVOConverter1 = new LogCaseDomainToVOConverter();

						// Begin - Modified the constant for the defect id
						// ESPRD00682457_05SEP2011
						list.add(caseDomainToVOConverter.getInputCriteria(
								ReferenceServiceDataConstants.A_RSN_CD_APP,
								FunctionalAreaConstants.GENERAL));
						// End - Modified the constant for the defect id
						// ESPRD00682457_05SEP2011
						referenceDataSearch = new ReferenceDataSearchVO();
						referenceDataSearch.setInputList(list);

						referenceServiceDelegate = new ReferenceServiceDelegate();

						referenceDataListVO = new ReferenceDataListVO();
						referenceDataListVO = referenceServiceDelegate
								.getReferenceData(referenceDataSearch);

						Map map = referenceDataListVO.getResponseMap();

						// Begin - Modified the code for the defect id
						// ESPRD00682457_05SEP2011
						List validValuesList = (List) map
								.get(FunctionalAreaConstants.GENERAL
										+ "#"
										+ ReferenceServiceDataConstants.A_RSN_CD_APP);
						int validValuesSize = validValuesList.size();
						List validList = new ArrayList();
						for (int i = 0; i < validValuesSize; i++) {
							ReferenceServiceVO refVo = (ReferenceServiceVO) validValuesList
									.get(i);

							validList.add(new SelectItem(refVo
									.getValidValueCode(), refVo
									.getValidValueCode()
									+ "-" + refVo.getShortDescription()));
						}
						// Begin - Added the code for the defect id
						// ESPRD00682457_07SEP2011
						logCaseDataBean.setUnusualCircumstancesList(validList);
						List availList = logCaseDataBean
								.getUnusualCircumstancesList();
						// End - Added the code for the defect id
						// ESPRD00682457_07SEP2011
						for (Iterator iter = dduUnUsual.iterator(); iter
								.hasNext();) {
							CaseDDUUnusualCircumstances element = (CaseDDUUnusualCircumstances) iter
									.next();

							/*
							 * shortDesc = ContactManagementHelper
							 * .setShortDescription(
							 * FunctionalAreaConstants.GENERAL2,
							 * ReferenceServiceDataConstants.G_CASE_DDU_UNUSL_CRCMST_CD,
							 * element.getCircumstanceTypeCode());
							 */

							item = new SelectItem();
							String description = caseDomainToVOConverter1
									.getLongDescription(element
											.getCircumstanceTypeCode(),
											validList);
							item.setLabel(element.getCircumstanceTypeCode()
									+ " - " + description);

							item.setValue(element.getCircumstanceTypeCode());

							selectedUnUsualList.add(item);
							removeItem(element.getCircumstanceTypeCode(),
									availList);
						}
						// End - Modified the code for the defect id
						// ESPRD00682457_05SEP2011
						logCaseDataBean
								.setSelectedUnUsualList(selectedUnUsualList);

					}
				}

				if (caseRecord.getCaseCommonEntityCrossRefs() != null) {

					Set caseCMNXRef = caseRecord.getCaseCommonEntityCrossRefs();
					log
					.debug("caseRecord.getCaseCommonEntityCrossRefs() size is $$ "
							+ caseCMNXRef.size());
					Iterator iterator = caseCMNXRef.iterator();
					CaseCommonEntityCrossRef ref = null;
					while (iterator.hasNext()) {
						ref = (CaseCommonEntityCrossRef) iterator.next();

						logCaseDataBean.setCaseCmnEntyXrefVersionNo(ref
								.getVersionNo());

						detailsVO = setInqAbtData(detailsVO, ref);
						// start
						if(log.isDebugEnabled()){
						log.debug("++ref.getOverseeingPhysicianIndicator() ::"
										+ ref.getOverseeingPhysicianIndicator());
						}
						if (ref.getCommonEntitySK() != null
								&& ref.getOverseeingPhysicianIndicator()
										.booleanValue() == true) {

							if (logCaseDataBean.getCaseDetailsVO() != null
									&& logCaseDataBean.getCaseDetailsVO()
											.getCaseTypeBCCPVO() != null
									&& ref.getCommonEntitySK() != null) {
								FacesContext context = FacesContext
										.getCurrentInstance();
								HttpSession httpSession = (HttpSession) context
										.getExternalContext().getSession(true);
								httpSession
										.setAttribute(
												"PhysicianOverseeingEntity",
												ref.getCommonEntitySK()
														.toString());
								if(log.isDebugEnabled()){
								log.debug("PhysicianOverseeingCare is successfully set with Cmnty sk::"
												+ ref.getCommonEntitySK());
								}
							}
						}
						// ends
					}
				}

				// Ends

				if (detailsVO != null) {
					logCaseDataBean.getCaseRegardingVO().setCaseRecordNumber(
							detailsVO.getCaseSK().toString());
					if (detailsVO.getCaseSK() != null) {
						logCaseDataBean.setShowCaseType(true);
					} else {
						logCaseDataBean.setShowCaseType(false);
					}
					CaseDetailsVO tempDetailsVO = new CaseDetailsVO();
					try {
						BeanUtils.copyProperties(tempDetailsVO, detailsVO);
						logCaseDataBean.setCaseDetailsVO(detailsVO);
						ContactManagementHelper.setCaseDetailMap(logCaseDataBean.getLoggedInUserID(),
								tempDetailsVO); // Modified for the Performance fix
					} catch (IllegalAccessException e) {
						log.error("Exception while copying"+e.getMessage());
					} catch (InvocationTargetException e) {
						log.error("Exception while copying"+e.getMessage());
					}
					if (!detailsVO.getInqAbtEntityList().isEmpty()) {
						logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
					}
					// added for the Defect ID: ESPRD00790932
					if (detailsVO
							.getStatus()
							.startsWith(
									MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_C)
							|| detailsVO
									.getStatus()
									.equalsIgnoreCase(
											MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_W)
											|| logCaseDataBean.isInInquiryModeFlag()) {
						//for inquiry mode UI fields should be disabled added for ESPRD00802214
						logCaseDataBean.setDisableScreenField(true);
						//added for defect ESPRD00774844
						//logCaseDataBean.setDisableLogCaseReset(true);
						logCaseDataBean.setDisableLogAddCaseAssocCrspondence(Boolean.TRUE);
						logCaseDataBean.setOpenDays(false);
					} else {
						logCaseDataBean.setDisableScreenField(false);
						//added for defect ESPRD00774844
						//logCaseDataBean.setDisableLogCaseReset(false);
						logCaseDataBean.setDisableLogAddCaseAssocCrspondence(Boolean.FALSE);
						logCaseDataBean.setOpenDays(true);
					}
					if (detailsVO
							.getStatus()
							.startsWith(
									MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_C)
							|| detailsVO
									.getStatus()
									.equalsIgnoreCase(
											MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_W)) {
						logCaseDataBean.setDisplayAddNoteLink(false);
					} else {
						logCaseDataBean.setDisplayAddNoteLink(true);
					}
					log.debug("isCaseAssignedToLoggedInUser $$ "
							+ isCaseAssignedToLoggedInUser());
					log.debug("isLoggedInUserSupervisorOfDept $$ "
							+ isLoggedInUserSupervisorOfDept());
					if (isCaseAssignedToLoggedInUser()
							|| isLoggedInUserSupervisorOfDept()) {
						logCaseDataBean.setEnableSaveLink(false);
					} else {
						logCaseDataBean.setEnableSaveLink(true);
					}
					log.debug("Case Type At getting is $$ "
							+ detailsVO.getCaseType());
					caseTypeDO = caseRecord.getCaseType();
					logCaseDataBean
							.setSuperviserRevReqIndForCaseType(caseTypeDO
									.getSupervisorReviewReqIndicator()
									.booleanValue());
					caseTypeEventsSet = caseTypeDO.getCaseTypeEvents();

					if (caseTypeEventsSet != null
							&& !caseTypeEventsSet.isEmpty()) {
						log
								.debug("INSIDE caseTypeEventsSet!null&&!caseTypeEventsSet.isEmpty");
						log.debug("caseTypeEventsSet size"
								+ caseTypeEventsSet.size());

						Iterator iter = caseTypeEventsSet.iterator();
						while (iter.hasNext()) {
							log.debug("-INSIDE ITERATION -");

							caseTypeEventDO = (CaseTypeEvent) iter.next();
							logCaseDataBean.getCaseTypeEventDOList().add(
									caseTypeEventDO);

							log.debug("** CaseTypeSK in caseEvents VO>>"
									+ caseTypeEventDO.getCaseTypeSK());
							log.debug(" getVersionNo()is $$>>>>>"
									+ caseTypeEventDO.getVersionNo());
							log.debug("** CaseTypeSK in caseEvents VO >>>"
									+ caseTypeEventDO.getCaseTypeSK());

						}
					}
					caseTypeStepsSet = caseTypeDO.getCaseTypeSteps();
					if (caseTypeStepsSet != null && !caseTypeStepsSet.isEmpty()) {
						Iterator iter = caseTypeStepsSet.iterator();
						while (iter.hasNext()) {
							caseTypeStepDO = (CaseTypeStep) iter.next();
							logCaseDataBean.getCaseTypeStepDOList().add(
									caseTypeStepDO);
						}
					}
					templateSet = caseTypeDO.getCaseTypeTemplates();
					templateSize = templateSet.size();
					log.debug("Case Type Templates Set size is $$ "
							+ templateSize);
					if (!templateSet.isEmpty() && templateSize > 0) {
						caseHelper.setTemplates(templateSet);
					}

				}
				routingSet = caseRecord.getCaseRoutings();
				if (routingSet != null && !routingSet.isEmpty()) {
					listOfCaseRoutingVOs = caseDomainToVOConverter
							.convertRoutingDOToVO(routingSet, caseRecord);
					/** for defect ESPRD00844065
					 *  by loading page with synchronize to db routing list 
					 *  should be in desc of routingDate.
					 * */
					if (listOfCaseRoutingVOs != null
							&& !listOfCaseRoutingVOs.isEmpty()) {
						caseHelper
								.sortCaseRoutingComparator(
										MaintainContactManagementUIConstants.CASEROUTING_DATE,
										ContactManagementConstants.EMPTY_STRING,
										listOfCaseRoutingVOs);
					}
					logCaseDataBean.setRoutingInfoList(listOfCaseRoutingVOs);
					logCaseDataBean.setShowRoutingDataTable(true);
				} else if (logCaseDataBean.getRoutingInfoList() != null) { // else
																		   // part
																		   // added
																		   // for
																		   // defect
																		   // UC-PGM-CRM-018.4_ESPRD00379771_09FEB2010
					logCaseDataBean.getRoutingInfoList().clear();
					logCaseDataBean.setShowRoutingDataTable(false);
				}
				alertSet = caseRecord.getAlerts();
				if (alertSet != null && !alertSet.isEmpty()) {
					listOfCaseAlertVOs = caseDomainToVOConverter
							.convertAlertDOToVO(alertSet);
				//  Modified for the Defect ESPRD00748466 starts
					// By default Alert date is set in descending order
					caseHelper.sortCaseAlertsComparator("caseAlerts_DueDate", "desc",
							listOfCaseAlertVOs);
				//  Modified for the Defect ESPRD00748466 ends
					logCaseDataBean.setAlertsVOList(listOfCaseAlertVOs);
					logCaseDataBean.setShowAlertsDataTable(true);
				} else if (logCaseDataBean.getAlertsVOList() != null) { // else
																		// part
																		// added
																		// for
																		// defect
																		// UC-PGM-CRM-018.4_ESPRD00379771_09FEB2010
					logCaseDataBean.getAlertsVOList().clear();
					logCaseDataBean.setShowAlertsDataTable(false);
				}
				caseStepSet = caseRecord.getActCMCaseSteps();
				if (caseStepSet != null && !caseStepSet.isEmpty()) {
					caseStepList = caseDomainToVOConverter
							.convertCMCaseStepsDOToVO(caseStepSet);
					if (caseStepList != null && caseStepList.size() > 0) {
						caseHelper
								.sortCaseStepsComparator(
										MaintainContactManagementUIConstants.CASESTEPS_ORDER,
										MaintainContactManagementUIConstants.SORT_TYPE_ASC,
										caseStepList);
						logCaseDataBean.setCaseStepsVOList(caseStepList);
						logCaseDataBean.setShowCaseStepsDataTable(true);
					}
				} else if (logCaseDataBean.getCaseStepsVOList() != null) { // else
																		   // part
																		   // added
																		   // for
																		   // defect
																		   // UC-PGM-CRM-018.4_ESPRD00379771_09FEB2010
					logCaseDataBean.getCaseStepsVOList().clear();
					logCaseDataBean.setShowCaseStepsDataTable(false);

				}
				caseEventsSet = caseRecord.getActCMCaseEvents();
				if (caseEventsSet != null && !caseEventsSet.isEmpty()) {
					listOfCaseEvents = caseDomainToVOConverter
							.convertCMCaseEventsDOToVO(caseEventsSet);
					//Modified for the defect ESPRD00896465
					if (listOfCaseEvents != null && listOfCaseEvents.size() > 0) {
						caseHelper
								.sortCaseEventsComparator(
										MaintainContactManagementUIConstants.CASEEVENT_DESC,
										MaintainContactManagementUIConstants.SORT_TYPE_ASC,
										listOfCaseEvents);
						logCaseDataBean.setCaseEventsVOList(listOfCaseEvents);
						logCaseDataBean.setShowCaseEventsDataTable(true);
					}
				} else if (logCaseDataBean.getCaseEventsVOList() != null) {// else
																		   // part
																		   // added
																		   // for
																		   // defect
																		   // UC-PGM-CRM-018.4_ESPRD00379771_09FEB2010
					logCaseDataBean.getCaseEventsVOList().clear();
					logCaseDataBean.setShowCaseEventsDataTable(false);
				}
				currentCaseLint = caseRecord.getCurrentCaseRecordLinks();
				if (currentCaseLint != null && !currentCaseLint.isEmpty()) {
					log.debug("Case Link size is $$ " + currentCaseLint.size());
					listOfCurrentCaseLinks = caseDomainToVOConverter
							.convertCaseLinksDOToVO(currentCaseLint);
					if (!listOfCurrentCaseLinks.isEmpty()) {
						logCaseDataBean.getCaseDetailsVO()
								.setAssociatedCaseList(listOfCurrentCaseLinks);
						logCaseDataBean
								.setCaseRecordsAssoWithCaseList(listOfCurrentCaseLinks);//UC-PGM-CRM-018.3_ESPRD00328556_07NOV09
						log.debug("CaseLink list size in ControllerBean is "
								+ listOfCurrentCaseLinks.size());
						clearExistingCaseList();//UC-PGM-CRM-018.3_ESPRD00328556_07NOV09
						logCaseDataBean
								.setShowCaseRecordsAssoWithCaseDataTable(true);
					} else {

						logCaseDataBean
								.setShowCaseRecordsAssoWithCaseDataTable(false);
					}
				}
				caseCRSet = caseRecord.getCaseCorrespondences();
				if (caseCRSet != null && !caseCRSet.isEmpty()) {
					log.debug("Case Correspondence size is $$ "
							+ caseCRSet.size());
					listOfCaseCRs = caseDomainToVOConverter
							.convertCaseCRsDOToVO(caseCRSet);
					if (!listOfCaseCRs.isEmpty()) {
						logCaseDataBean.getCaseDetailsVO().setAssociatedCrList(
								listOfCaseCRs);
						log
								.debug("Case Correspondence list size in ControllerBean is "
										+ listOfCaseCRs.size());
						clearExistingCRList();
						logCaseDataBean
								.setShowCRAssociatedWithCaseDataTable(true);
					} else {

						logCaseDataBean
								.setShowCRAssociatedWithCaseDataTable(false);
					}
				}
				caseAttachments = caseRecord.getCaseAttachCrossReferences();
				if (caseAttachments != null && !caseAttachments.isEmpty()) {
					listOfCaseAttachVOs = caseDomainToVOConverter
							.convertCaseAttachmentsDOToVO(caseAttachments);
					logCaseDataBean.setAttachmentVOList(listOfCaseAttachVOs);
					
					//for ESPRD00748455
					caseHelper
							.sortCaseAttachmentsComparator(
									MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_DATEADDED,
									MaintainContactManagementUIConstants.SORT_ORDER,
									logCaseDataBean.getAttachmentVOList());
					log.debug("Attachment list size in ControllerBean is "
							+ listOfCaseAttachVOs.size());
					logCaseDataBean.setShowAttachmentDataTable(true);
				} else {
					logCaseDataBean.setShowAttachmentDataTable(false);
				}
				noteSet = caseRecord.getNoteSet();
				if (noteSet != null) {
					populateNoteSet(caseRecord);
				}
				if (!logCaseDataBean.isFromIPC()) {
					getCFElementsInUpdate();
				}
				caseHelper.restoreAddUpdatePages();
			}

			if (logCaseDataBean.getCaseDetailsVO().getCaseType() != null
					&& logCaseDataBean.isShowCaseType()) {

				showCaseType(logCaseDataBean.getCaseDetailsVO().getCaseType());//commented
																			   // for
																			   // defect
																			   // ESPRD00299506

			}

			if (logCaseDataBean.getCaseStepsVOList() != null
					&& !logCaseDataBean.getCaseStepsVOList().isEmpty()) {
				boolean isActiveFound = false;
				for (Iterator itr = logCaseDataBean.getCaseStepsVOList()
						.iterator(); itr.hasNext();) {
					CaseStepsVO stepVO = (CaseStepsVO) itr.next();
					if (stepVO.getStatus() != null
							&& stepVO
									.getStatus()
									.equalsIgnoreCase(
											ContactManagementConstants.STEP_STATUS_ACTIVE)) {
						logCaseDataBean.getCaseDetailsVO().setStep(
								stepVO.getOrder());
						isActiveFound = true;
						break;
					}
				}
				if (!isActiveFound) {
					logCaseDataBean.getCaseDetailsVO().setStep(
							MaintainContactManagementUIConstants.EMPTY_STRING);
				}
			} else {
				logCaseDataBean.getCaseDetailsVO().setStep(
						MaintainContactManagementUIConstants.EMPTY_STRING);
			}

			//added for UC-PGM-CRM-18_ESPRD00367808_30DEC09
			Iterator itr = logCaseDataBean.getListOfDepartments().iterator();

			SelectItem sItem = null;

			boolean isWorkUnitFoundInTheLoggedUsersWorkUnitList = false;

			logCaseDataBean.setCaseStatusSetFlag(false);
			logCaseDataBean.setDisableWorkUnit(true);

			while (itr.hasNext()) {
				sItem = (SelectItem) itr.next();

				if (logCaseDataBean.getCaseDetailsVO().getWorkUnit() != null
						&& logCaseDataBean.getCaseDetailsVO().getWorkUnit()
								.equals(sItem.getValue().toString())) {
					isWorkUnitFoundInTheLoggedUsersWorkUnitList = true;
					break;
				} else if (logCaseDataBean.getCaseDetailsVO().getWorkUnit() == null) {
					break;
				}
			}
			if (!isWorkUnitFoundInTheLoggedUsersWorkUnitList) {

				try {//code for finding the work unit info

					LineOfBusinessHierarchy lob_hierarchy = null;
					Department department = null;
					ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

					List hierarchy_list = contactMaintenanceDelegate
							.getLobHierarchyDetails(null,
									ContactManagementConstants.FOUR);

					if (hierarchy_list != null) {

						boolean isWrokunitFound = false;

						for (Iterator list_itrator = hierarchy_list.iterator(); list_itrator
								.hasNext();) {

							lob_hierarchy = (LineOfBusinessHierarchy) list_itrator
									.next();
							if (lobSK != null
									&& lob_hierarchy != null
									&& lobSK.equals(lob_hierarchy
											.getLineOfBusinessHierarchySK())) {

								Set deptset = lob_hierarchy.getDepartments();

								for (Iterator deptList_itrator = deptset
										.iterator(); deptList_itrator.hasNext();) {
									department = (Department) deptList_itrator
											.next();
									if (department != null) {
										if (logCaseDataBean.getCaseDetailsVO()
												.getWorkUnit() != null
												&& logCaseDataBean
														.getCaseDetailsVO()
														.getWorkUnit()
														.equals(
																department
																		.getWorkUnitSK()
																		.toString())) {
											logCaseDataBean
													.getListOfDepartments()
													.add(
															new SelectItem(
																	department
																			.getWorkUnitSK()
																			.toString(),
																	department
																			.getName()));
											isWrokunitFound = true;
											break;
										} else if (logCaseDataBean
												.getCaseDetailsVO()
												.getWorkUnit() == null) {
											logCaseDataBean
													.getCaseDetailsVO()
													.setWorkUnit(
															department
																	.getWorkUnitSK()
																	.toString());
											logCaseDataBean
													.getListOfDepartments()
													.add(
															new SelectItem(
																	department
																			.getWorkUnitSK()
																			.toString(),
																	department
																			.getName()));
											isWrokunitFound = true;
											break;
										}

									}
								}//eof for

							}

							if (isWrokunitFound)
								break;
						}

					}

				} catch (LOBHierarchyFetchBusinessException lobExp) {
					log.error(lobExp.getMessage(), lobExp);
				}

				//EOF UC-PGM-CRM-018_ESPRD00367808_18JAN10
			}
			//EOF UC-PGM-CRM-18_ESPRD00367808_30DEC09
		} catch (Exception e) {
			log.info("++++exception while fetching");
			e.printStackTrace();
			log
					.error("Error occured at getCaseRecord()"
							+ e.fillInStackTrace());
		}
		//THIS CODE IS MOVE TO Recive message method FOR THE DEFECT ESPRD00732649
		/*executeBRCON307();//ESPRD00517164_UC-PGM-CRM-18_16SEP2010
		// start
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) context.getExternalContext()
				.getSession(true);
		String PhysicianOverseeingEntity = (String) httpSession
				.getAttribute("PhysicianOverseeingEntity");
		System.err.println("PhysicianOverseeingEntity"+PhysicianOverseeingEntity);
		if (PhysicianOverseeingEntity != null) {
			if (logCaseDataBean.getCaseDetailsVO() != null) {
				if (logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO() != null) {
					logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO().setPhysicianOverseeingCare(PhysicianOverseeingEntity);
					System.err.println("logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO().setPhysicianOverseeingCare$$$$$$$$"+logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO().getPhysicianOverseeingCare());
				}
			}
			httpSession.removeAttribute("PhysicianOverseeingEntity");
		}*/
		// END THE CODE
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getCaseRecord");
		//return MaintainContactManagementUIConstants.SUCCESS;//
		//return type changed during defect ESPRD00560416
		return caseRecord;
	}

	/**
	 * This method sets the inq abt data.
	 * 
	 * @param detailsVO
	 *            Takes CaseDetailsVO as param
	 * @param crossRef
	 *            Takes crossRef as param
	 * @return CaseDetailsVO
	 */
	private CaseDetailsVO setInqAbtData(CaseDetailsVO detailsVO,
			CaseCommonEntityCrossRef crossRef) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		log.debug("crossRef.getCaseCRContactReasonCode()"
				+ crossRef.getCaseCRContactReasonCode());
		if ("I".equals(crossRef.getCaseCRContactReasonCode())) {

			setInquiryAboutEntity(detailsVO, crossRef);
		}
		if ("P".equals(crossRef.getCaseCRContactReasonCode())) {
			logCaseDataBean.setCommonEntitySKForMyTask(crossRef
					.getCommonEntitySK());
			logCaseDataBean.setEntityType(crossRef.getCommonEntityTypeCode());
		}
		return detailsVO;
	}

	/**
	 * This method sets the inq abt Entity.
	 * 
	 * @param detailsVO
	 *            Takes CaseDetailsVO as param
	 * @param crossRef
	 *            Takes crossRef as param
	 * @return CaseDetailsVO
	 */
	private CaseDetailsVO setInquiryAboutEntity(CaseDetailsVO detailsVO,
			CaseCommonEntityCrossRef crossRef) {
		/* FIND BUGS_FIX */
		String entityType = null;
		Long cmEntitySK = null;
		if (crossRef != null) {
			log.debug("++crossRef-=" + crossRef);
			entityType = crossRef.getCommonEntityTypeCode();
			cmEntitySK = crossRef.getCommonEntity().getCommonEntitySK();
		}
		log.debug("setInquiryAboutEntity is started");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String physicianOverseeingCareEntityID = null;//ESPRD00517164_UC-PGM-CRM-18_16SEP2010
		if (ContactManagementConstants.ENTITY_TYPE_PROV.equals(entityType)) {
			ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

			Long entityId = null;
			try {
				entityId = providerInformationDelegate
						.getProviderSysID(cmEntitySK);
				if (entityId == null) {
					log.error("LogCase Provider Entity Not Found");
				} else {
					CaseRegardingProviderVO providerVO = getEntitiesForProvider(
							entityId.toString(), false);
					providerVO.setVersionNo(crossRef.getVersionNo());
					detailsVO.getInqAbtEntityList().add(providerVO);
					//                  ESPRD00517164_UC-PGM-CRM-18_16SEP2010
					if (crossRef.getOverseeingPhysicianIndicator() != null
							&& crossRef.getOverseeingPhysicianIndicator()
									.booleanValue()) {

						physicianOverseeingCareEntityID = providerVO
								.getEntityId();
					}//ESPRD00517164_UC-PGM-CRM-18_16SEP2010

					//                  ESPRD00544867_Fix_16Dec10.
					log.info("before:::call to:getProviderhos::inside:prov type::::");
					logCaseDataBean.getProviderhos().add(
							new SelectItem(providerVO.getEntityId(), providerVO
									.getName()));//EOf
												 // ESPRD00544867_Fix_16Dec10

					logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
				}
			} catch (EnterpriseBaseBusinessException e) {
				log.error(e.getMessage(), e);
			}
		} else if (ContactManagementConstants.ENTITY_TYPE_MEM
				.equals(entityType)) {
			MemberInformationDelegate memberInformationDelegate = new MemberInformationDelegate();
			Long entityId = null;

			try {
				entityId = memberInformationDelegate.getMemberID(cmEntitySK);
				if (entityId == null) {
					log.error("LogCase Member Entity Not Found");
				} else {
					CaseRegardingMemberVO memberVO = getEntityDetailsForMember(
							entityId.toString(), false);
					memberVO.setVersionNo(crossRef.getVersionNo());
					detailsVO.getInqAbtEntityList().add(memberVO);
					//                  ESPRD00517164_UC-PGM-CRM-18_16SEP2010
					if (crossRef.getOverseeingPhysicianIndicator() != null
							&& crossRef.getOverseeingPhysicianIndicator()
									.booleanValue()) {
						physicianOverseeingCareEntityID = memberVO
								.getEntityId();
					}
					//                  ESPRD00517164_UC-PGM-CRM-18_16SEP2010
					logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
				}
			} catch (MemberBusinessException e) {
				log.error(e.getMessage(), e);
			}
		} else if (ContactManagementConstants.ENTITY_TYPE_TPL
				.equals(entityType)) {
			log.debug("Your entity type is TPL and CMNO " + cmEntitySK);
			//TPLCarrierDelegate carrierDelegate = new TPLCarrierDelegate();
			log.info("inside;:::ENTITY_TYPE_TPL::::::::mk::");
			TPLCarrier carrier = new TPLCarrier();
			Long entityID = null;
			try {

				if (carrier != null) {
					CaseRegardingTPL regarding = null;
					log.debug("cmEntitySK:::is:::mk::" + cmEntitySK);
					/* FIND BUGS_FIX */
					if (cmEntitySK != null
							&& !(cmEntitySK.equals(Long.valueOf(0)))) {
						regarding = getEntityForTPL(cmEntitySK.toString(), true);
					}/*
					  * else{ caseRegarding =
					  * getEntityForTPL(entityID.toString(), true); }
					  */
					//  entityID = new Long(carrier.getCarrierID());
					log.debug("TPL carrier ID in setInquiryAboutEntity is "
							+ entityID);
					/*
					 * if (entityID == null) { System.err.println("TPL carrier
					 * Entity ID Not Found::::::::::mk::"); log.error("TPL
					 * carrier Entity ID Not Found"); } else {
					 */
					/*
					 * CaseRegardingTPL regarding = getEntityForTPL(entityID
					 * .toString(), false);
					 */
					//Added if Condition for Find_Bugs-FIX
					if(regarding != null){
					regarding.setVersionNo(crossRef.getVersionNo());
					}
					detailsVO.getInqAbtEntityList().add(regarding);
					//                      ESPRD00517164_UC-PGM-CRM-18_16SEP2010
					if (crossRef.getOverseeingPhysicianIndicator() != null
							&& crossRef.getOverseeingPhysicianIndicator()
									.booleanValue()) {
						physicianOverseeingCareEntityID = regarding
								.getEntityId();
					}
					//                      ESPRD00517164_UC-PGM-CRM-18_16SEP2010

					logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
					// }
				}
			}

			catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		//added for CR ESPRD00486064 starts
		else if (ContactManagementConstants.ENTITY_TYPE_TP.equals(entityType)) {
			log.debug("Your entity type is TPL Policy Holder and CMNO "
					+ cmEntitySK);
			try {
				if (cmEntitySK == null) {
					log.error("TPL Policy holder Entity ID Not Found");
				} else {
					CaseRegardingTPL regarding = getEntityForTPLPolicyHolder(
							cmEntitySK.toString(), false);
					regarding.setVersionNo(crossRef.getVersionNo());

					detailsVO.getInqAbtEntityList().add(regarding);
					//                      ESPRD00517164_UC-PGM-CRM-18_16SEP2010
					if (crossRef.getOverseeingPhysicianIndicator() != null
							&& crossRef.getOverseeingPhysicianIndicator()
									.booleanValue()) {
						physicianOverseeingCareEntityID = regarding
								.getEntityId();
					}
					//                      ESPRD00517164_UC-PGM-CRM-18_16SEP2010
					logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} //CR ESPRD00486064 ends
		else if ("TD".equals(entityType)) {

			log.debug("Your entity type is Trading Partner and CMNO "
					+ cmEntitySK);
			log.debug("crossRef " + crossRef);
			try {
				if (cmEntitySK == null) {
					log.error("Trading Partner Entity ID Not Found");
				} else {
					CaseRegardingTradingPartnerVO tradingPartnerVo = getEntityForTradingPartner(
							null, cmEntitySK, false);
					if (crossRef != null) {
						tradingPartnerVo.setVersionNo(crossRef.getVersionNo());

						detailsVO.getInqAbtEntityList().add(tradingPartnerVo);
						//                      ESPRD00517164_UC-PGM-CRM-18_16SEP2010
						if (crossRef.getOverseeingPhysicianIndicator() != null
								&& crossRef.getOverseeingPhysicianIndicator()
										.booleanValue()) {
							physicianOverseeingCareEntityID = tradingPartnerVo
									.getCommonEntitySK();
						}
					}
					//                      ESPRD00517164_UC-PGM-CRM-18_16SEP2010
					logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			log.debug("+++cmEntitySK " + cmEntitySK);
			String entityId = cmEntitySK.toString();
			CaseRegardingTPL regarding = getSpecificEntity(entityId, false);
			log.debug("+++regarding " + regarding);
			// Modify the Defect ESPRD00735117 starts
			if(regarding == null ){
				if(crossRef.getCommonEntityTypeCode().trim().equals(ContactManagementConstants.ENTITY_TYPE_UNPROV)){
				CaseRegardingProviderVO providerVO = getEntitiesForProvider(
						entityId, false);
				log.debug("+++providerVO " + providerVO);
				
				providerVO.setVersionNo(crossRef.getVersionNo());
				
				providerVO.setEntityType(ContactManagementConstants.ENTITY_TYPE_UNPROV);
				providerVO.setEntityTypeDesc("UP-UnenrldPrv");
				
				detailsVO.getInqAbtEntityList().add(providerVO);
				
				if (crossRef.getOverseeingPhysicianIndicator() != null
						&& crossRef.getOverseeingPhysicianIndicator()
								.booleanValue()) {
					physicianOverseeingCareEntityID = providerVO.getEntityId();
				}
				log.debug("before:::setting getProviderhos:::mk::inside;:else::");
				logCaseDataBean.getProviderhos()
						.add(
								new SelectItem(providerVO.getEntityId(), providerVO
										.getName()));
				logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
				
				}
			} else{
					// Modify the Defect ESPRD00735117 ends
				
				regarding.setVersionNo(crossRef.getVersionNo());
				detailsVO.getInqAbtEntityList().add(regarding);
				//          ESPRD00517164_UC-PGM-CRM-18_16SEP2010
				if (crossRef.getOverseeingPhysicianIndicator() != null
						&& crossRef.getOverseeingPhysicianIndicator()
								.booleanValue()) {
					physicianOverseeingCareEntityID = regarding.getEntityId();
				}
				//          ESPRD00517164_UC-PGM-CRM-18_16SEP2010
				//ESPRD00544867_Fix_16Dec10
				log.debug("before:::setting getProviderhos:::mk::inside;:else::************");
				logCaseDataBean.getProviderhos()
						.add(
								new SelectItem(regarding.getEntityId(), regarding
										.getName()));
				//EOF ESPRD00544867_Fix_16Dec10

				logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
			}
			
		}
		//      ESPRD00517164_UC-PGM-CRM-18_16SEP2010
		if (physicianOverseeingCareEntityID != null) {
			if (detailsVO.getCaseTypeBCCPVO() != null
					&& "B".equals(detailsVO.getCaseTypeBusinessUnitTypeCode())) {
				//System.err.println("setting physicianOverseeingCareEntityID::
				// "+physicianOverseeingCareEntityID);
				detailsVO.getCaseTypeBCCPVO().setPhysicianOverseeingCare(
						physicianOverseeingCareEntityID);
			}
		}//ESPRD00517164_UC-PGM-CRM-18_16SEP2010
		return detailsVO;
	}

	public void showCaseTypetempScreen(ValueChangeEvent event) {
		String casetype = event.getNewValue().toString();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) context.getExternalContext()
				.getSession(true);
		httpSession.setAttribute("DDUstatus", "OTH");
		httpSession.setAttribute("DDUAddMode", "true");
		populateNotifyViaAlertList(casetype);
		showCaseType(casetype);
		//added for UC-PGM-CRM-018.4_ESPRD00379771_09FEB2010
		logCaseDataBean.getCaseStepsVOList().clear();
		logCaseDataBean.getCaseEventsVOList().clear();
		logCaseDataBean.setShowCaseEventsDataTable(false);
		/* Fixed for Defect ESPRD00712154  */
		logCaseDataBean.setShowEditCaseEvents(false);
		logCaseDataBean.setShowAddCaseEvents(false);
		logCaseDataBean.setShowCaseStepsDataTable(false); //EOF
														  // UC-PGM-CRM-018.4_ESPRD00379771_09FEB2010
		//Added for defect ESPRD00792967
		logCaseDataBean.setRenderedCaseEventsflag(false);
		logCaseDataBean.setRenderedCaseStepsflag(false);
		getCaseTypeDetails(casetype);

	}

	private void showCaseType(String casetype) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "showCaseType");
		log.debug("casetype$$$$$$$$$$$$$$$"+casetype);
		//String caseTypeSK = casetype;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String businessUnitCD = null;

		String shortDesc = null;
		logCaseDataBean.setSaveFlag(false);
		logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		//Moved from getReceiveMessage() for  PERFORMANCE ISSUE 
		//Start 
		if (casetype != null) {

			if (casetype.trim().equals(
					MaintainContactManagementUIConstants.EMPTY_STRING)) {
				// Comment for defect ESPRD00327122 and ESPRD00336108
				/* Added code to Fix defect ESPRD00351920 Starts */
				setCaseEventTypeList(MaintainContactManagementUIConstants.EMPTY_STRING);
				/* Added code to Fix defect ESPRD00351920 End */
			} else {
				//moved here
				Iterator itr = logCaseDataBean.getCaseTypeList().iterator();
				boolean isFound = false;
				while (itr.hasNext()) {
					SelectItem element = (SelectItem) itr.next();
					if (casetype.equals(element.getValue())) {

						/* Added code to Fix defect ESPRD00351920 Starts */
						setCaseEventTypeList(element.getLabel());
						/* Added code to Fix defect ESPRD00351920 End */
						isFound = true;
						break;
					}
				}
				if (!isFound) {

					/* Added code to Fix defect ESPRD00351920 Starts */
					setCaseEventTypeList(MaintainContactManagementUIConstants.EMPTY_STRING);
					/* Added code to Fix defect ESPRD00351920 End */
				}
			}

		} else {

			/* Added code to Fix defect ESPRD00351920 Starts */
			setCaseEventTypeList(MaintainContactManagementUIConstants.EMPTY_STRING);
			/* Added code to Fix defect ESPRD00351920 End */
		}
		//End 
		if (casetype == null
				|| casetype.trim().equalsIgnoreCase(
						ContactManagementConstants.EMPTY_STRING)) {
			businessUnitCD = "Please Select One";
		} else {
			//hash map performance issue code change
			CaseType caseType=getCaseTypeFrmDosList(casetype,null);
			if(caseType!=null){
				businessUnitCD=caseType.getBusinessUnitCaseTypeCode();
				shortDesc=caseType.getShortDescription();
			}
			/*businessUnitCD = (String) logCaseDataBean
					.getCaseTypeSKAndBusinessUnit().get(casetype.toString());
			shortDesc = (String) logCaseDataBean.getCaseTypeSKAndShortDesc()
					.get(casetype.toString());*/
			if (businessUnitCD == null) {

				/*businessUnitCD = (String) logCaseDataBean
						.getCaseTypeSKAndShortDesc().get(casetype.toString());*/
				if(caseType!=null){
				businessUnitCD =caseType.getShortDescription();
				}
			}
		}
		log.debug("---------------------------------------------- businessUnitCD :> "
						+ businessUnitCD);
		log.debug(" Short Desc :> " + shortDesc);

		//UC_PGM_CRM_018_ESPRD00337772_24NOV09 modified
		if (shortDesc != null
				&& (shortDesc.equals("BC") || shortDesc.equals("BP") || shortDesc
						.equals("P2"))) {
			logCaseDataBean.setEnableTumorAndNodes(true);
		} else {
			logCaseDataBean.setEnableTumorAndNodes(false);
		}
		//	Code ended for the defect ID ESPRD00337772

		if (businessUnitCD != null) {
			//ESPRD00517164_UC-PGM-CRM-18_06SEP2010 // Modified for BRC
			// CON0307.0001.01 starts
			/*
			 * if(ContactManagementConstants.ENTITY_TYPE_PROV.equals(logCaseDataBean.getEntityType())
			 * ||ContactManagementConstants.ENTITY_TYPE_UNPROV.equals(logCaseDataBean.getEntityType())){
			 * logCaseDataBean.setPhysicianOverseeingFlag(true); }else{
			 * logCaseDataBean.setPhysicianOverseeingFlag(false); }
			 */
			executeBRCON307();//EOF ESPRD00517164_UC-PGM-CRM-18_06SEP2010
			//Modified for BRC CON0307.0001.01 ends

			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession httpSession = (HttpSession) context
					.getExternalContext().getSession(true);
			if ("A".equalsIgnoreCase(businessUnitCD)
					&& "F4".equalsIgnoreCase(shortDesc)) {

				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(true);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			}

			else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "AR".equalsIgnoreCase(shortDesc)) {

				logCaseDataBean.setShowAppealScreen(true);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			}

			else if (businessUnitCD
					.equalsIgnoreCase(ContactManagementConstants.BUSINESS_UNIT_BCCP)) {

				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(true);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);
				//Added for defect ESPRD00532717
				if (!"CaseUpdate".equals(logCaseDataBean.getActionCode())) {
					if (logCaseDataBean.getCaseDetailsVO() != null
							&& logCaseDataBean.getCaseDetailsVO()
									.getCaseTypeBCCPVO() != null) {
						logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO()
								.setForm778SignedInd("false");
						log.debug("Form778SignedInd--"
								+ logCaseDataBean.getCaseDetailsVO()
										.getCaseTypeBCCPVO()
										.getForm778SignedInd());
					}
				}
				//defect ESPRD00532717 ends
				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);
				getLoadValidValuesForBCCP(); // Added to call the loadValidValuesForBCCP
												// for the performance fix.
			}

			else if (businessUnitCD
					.equalsIgnoreCase(ContactManagementConstants.BUSINESS_UNIT_DDU)) {
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(true);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

				String dduAddMode = (String) httpSession
						.getAttribute("DDUAddMode");
				if (dduAddMode != null && dduAddMode.equals("true")) {
					httpSession.setAttribute("DDUstatus", "PE");
					httpSession.removeAttribute("DDUAddMode");
				}
				getLoadValidValuesForDDU(); // Added to call the loadValidValuesForDDU
												// for the performance fix.
				
				
				//Added for CR_908771
				if(!logCaseDataBean.isShowCaseRegardingMember() && logCaseDataBean.isDisableLogAddCaseAssocCrspondence()){
					
					logCaseDataBean.setSaveFlag(true);
					
					setErrorMessage(
							ContactManagementConstants.ERR_DDU_NON_MEMBER_ENTY,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							null);
					logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
					
				}
				
			} else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "A3".equalsIgnoreCase(shortDesc)) {
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(true);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			}

			else if (businessUnitCD
					.equalsIgnoreCase(ContactManagementConstants.BUSINESS_UNIT_ARS)
					&& casetype.equalsIgnoreCase("ARS"))

			{
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(true);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			} else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "F3".equalsIgnoreCase(shortDesc))

			{
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(true);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			} else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "CB".equalsIgnoreCase(shortDesc))

			{
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(true);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			}

			else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "F2".equalsIgnoreCase(shortDesc))

			{
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(true);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			} else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "H2".equalsIgnoreCase(shortDesc))

			{
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(true);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			} else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "N2".equalsIgnoreCase(shortDesc))

			{
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(true);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			} else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "Q2".equalsIgnoreCase(shortDesc)) {
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(true);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			} else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "A4".equalsIgnoreCase(shortDesc)) {
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(true);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			} else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "N3".equalsIgnoreCase(shortDesc))

			{
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(true);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			} else if ("A".equalsIgnoreCase(businessUnitCD)
					&& "FA".equalsIgnoreCase(shortDesc)) {
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(true);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

			}
			//Modified for CR ESPRD00865082
			else if (businessUnitCD
					.equalsIgnoreCase(ContactManagementConstants.BUSINESS_UNIT_APPEALS))

			{
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(true);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

				String dduAddMode = (String) httpSession
						.getAttribute("DDUAddMode");
				if (dduAddMode != null && dduAddMode.equals("true")) {
					httpSession.setAttribute("DDUstatus", "PD");
					httpSession.removeAttribute("DDUAddMode");
				}

			}
			/**
			 * Commented for CR ESPRD00865082 as Reusing ShowMemAppTypeScreen variable is used 
			 * to display continue button for all Appeal Case Types. 
			 * */
			/*else if (businessUnitCD
					.equalsIgnoreCase(ContactManagementConstants.PROVIDER_APPEALS)) {
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);

				logCaseDataBean.setShowMemAppTypeScreen(false);
				logCaseDataBean.setShowProviderAppTypeScreen(true);
				logCaseDataBean.setShowCommonTypeScreen(false);

				//Added by Infinite for defect ESPRD00362395
				String dduAddMode = (String) httpSession
						.getAttribute("DDUAddMode");
				if (dduAddMode != null && dduAddMode.equals("true")) {
					httpSession.setAttribute("DDUstatus", "PD");
					httpSession.removeAttribute("DDUAddMode");
				}

			}*/ else {
				logCaseDataBean.setShowAppealScreen(false);
				logCaseDataBean.setShowBCCPTypeScreen(false);
				logCaseDataBean.setShowARSTypeScreen(false);
				logCaseDataBean.setShowDDUTypeScreen(false);
				logCaseDataBean.setShowMQIPTypeScreen(false);
				logCaseDataBean.setShowFCRTypeScreen(false);
				logCaseDataBean.setShowNewARSTypeScreen(false);
				logCaseDataBean.setShowHOCRTypeScreen(false);
				logCaseDataBean.setShowAcuityRateSettingTypeScreen(false);
				logCaseDataBean.setShowFPPRRTypeScreen(false);
				logCaseDataBean.setShowFATypeScreen(false);
				logCaseDataBean.setShowCBTypeScreen(false);
				logCaseDataBean.setShowFCSTypeScreen(false);
				logCaseDataBean.setShowNFQATypeScreen(false);
				logCaseDataBean.setShowNewNonARSTypeScreen(false);
				logCaseDataBean.setShowMemAppTypeScreen(false);
				//logCaseDataBean.setShowProviderAppTypeScreen(false);
				logCaseDataBean.setShowCommonTypeScreen(false);

				log.info("getting default case type details");

			}

		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "showCaseType");
	}

	/** End Here * */

	/**
	 * This method shows the acse type based on BU.
	 * 
	 * @param caseType
	 *            Takes caseType as param
	 */
	/*
	 * private void showCaseTypeScreensBasedOnBU(CaseType caseType) {
	 * log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "showCaseTypeScreensBasedOnBU");
	 * ContactManagementHelper helper = new ContactManagementHelper(); boolean
	 * appeal = false; LogCaseHelper caseHelper = new LogCaseHelper();
	 * logCaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN);
	 * log.debug("in showCaseTypeScreensBasedOnBU case BU type Code :" +
	 * caseType.getBusinessUnitCaseTypeCode());
	 * 
	 * 
	 * if (StringUtils.isEmpty(caseType.getBusinessUnitCaseTypeCode())) {
	 * 
	 * 
	 * if (caseType.getShortDescription() != null) { List shortDescSet =
	 * logCaseDataBean .getCaseTypeShortDesc();
	 * 
	 * 
	 * 
	 * 
	 * if (!shortDescSet.isEmpty()) {
	 * 
	 * appeal = helper.chkCaseTypeShortDesc(caseType .getShortDescription(),
	 * shortDescSet); } if (appeal) { log.debug("Case Type is Appeal");
	 * logCaseDataBean.setShowAppealScreen(true);
	 * logCaseDataBean.setShowBCCPTypeScreen(false);
	 * logCaseDataBean.setShowARSTypeScreen(false);
	 * logCaseDataBean.setShowDDUTypeScreen(false);
	 * logCaseDataBean.setShowGeneralCaseTypeScreens(false); if
	 * (!"CaseUpdate".equals(logCaseDataBean .getActionCode())) {
	 * logCaseDataBean.getCaseDetailsVO().setStatus( "PD"); } } else {
	 * log.debug("Case Type is general");
	 * logCaseDataBean.setShowGeneralCaseTypeScreens(true);
	 * logCaseDataBean.setShowBCCPTypeScreen(false);
	 * logCaseDataBean.setShowARSTypeScreen(false);
	 * logCaseDataBean.setShowDDUTypeScreen(false);
	 * logCaseDataBean.setShowAppealScreen(false); } } } else if
	 * (caseType.getBusinessUnitCaseTypeCode().equals("B")) { caseHelper
	 * .enableCaseTypeFields("B", caseType.getShortDescription());
	 * logCaseDataBean.setShowBCCPTypeScreen(true);
	 * logCaseDataBean.setShowARSTypeScreen(false);
	 * logCaseDataBean.setShowDDUTypeScreen(false);
	 * logCaseDataBean.setShowGeneralCaseTypeScreens(false);
	 * logCaseDataBean.setShowAppealScreen(false); } else if
	 * (caseType.getBusinessUnitCaseTypeCode().equals("A")) { caseHelper
	 * .enableCaseTypeFields("A", caseType.getShortDescription());
	 * logCaseDataBean.setShowBCCPTypeScreen(false);
	 * logCaseDataBean.setShowARSTypeScreen(true);
	 * logCaseDataBean.setShowDDUTypeScreen(false);
	 * logCaseDataBean.setShowGeneralCaseTypeScreens(false);
	 * logCaseDataBean.setShowAppealScreen(false); } else if
	 * (caseType.getBusinessUnitCaseTypeCode().equals("D")) { caseHelper
	 * .enableCaseTypeFields("D", caseType.getShortDescription()); if
	 * (!"CaseUpdate".equals(logCaseDataBean.getActionCode())) {
	 * logCaseDataBean.getCaseDetailsVO().setStatus("PE"); }
	 * logCaseDataBean.setShowBCCPTypeScreen(false);
	 * logCaseDataBean.setShowARSTypeScreen(false);
	 * logCaseDataBean.setShowDDUTypeScreen(false);
	 * logCaseDataBean.setShowGeneralCaseTypeScreens(false);
	 * logCaseDataBean.setShowAppealScreen(false); } log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean +
	 * "showCaseTypeScreensBasedOnBU"); }
	 */

	/**
	 * This method is used to save the case record
	 * 
	 * @return String
	 */
	public String saveCaseRecord() {
		log.debug(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "saveCaseRecord");
		CaseRecord caseRecord = new CaseRecord();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//CaseDelegate delegate = null;
		String action = logCaseDataBean.getActionCode();
		Set caseXref = null;
		String msg = null;
		CaseRequestVO caseRecordVO = new CaseRequestVO();
		LogCaseHelper caseHelper = new LogCaseHelper();
		CaseDetailsVO caseDetailsVO = logCaseDataBean.getCaseDetailsVO();
		LogCaseVOToDomainConverter logCaseVOToDomainConverter = new LogCaseVOToDomainConverter();
		//for ESPRD00735630
		//resetMsgFlags();//for ESPRD00778912
		validateFlag = validateCase(caseDetailsVO);
		commonEntityDataBean = (CommonEntityDataBean) getDataBean(ContactManagementConstants.COMMON_ENTITY_DATA_BEAN);
		commonNotesControllerBean = (CommonNotesControllerBean) getDataBean(ContactManagementConstants.COMMON_NOTES_CONTROLLER_BEAN);
		if (caseDetailsVO != null) {
			//          ADD FOR THE DEFECT ESPRD00601882 18 MARCH 2011
			if (validateFlag) {
				/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
				if (!logCaseDataBean.isSaveAlertsFlag()
						&& logCaseDataBean.isRenderedalertsFlag())

				{
					saveAlerts();
					logCaseDataBean.setSaveAlertsFlag(false);
					if (!validateFlag) {
						return "";
					}
				}
				if (!logCaseDataBean.isSaveRoutingFlag()
						&& logCaseDataBean.isRenderedroutingFlag()) {
					saveRouting();
					logCaseDataBean.setSaveRoutingFlag(false);
					if (!validateFlag) {
						return "";
					}
				}
				if (!logCaseDataBean.isSaveCaseEventFlag()
						&& logCaseDataBean.isRenderedCaseEventsflag()) {
					saveCaseEvents();
					//COMMEMTED FOR THE DEFECT ESPRD00690966
					//logCaseDataBean.setSaveCaseEventFlag(false);
					log.debug("++validateFlag--" + validateFlag);
					//added for defect ESPRD00621016
					if (!validateFlag) {
						return "";
					}
				}
				if (!logCaseDataBean.isSaveCaseStepFlag()
						&& logCaseDataBean.isRenderedCaseStepsflag()) {
					saveCaseSteps();
					//COMMEMTED FOR THE DEFECT ESPRD00690966
					//logCaseDataBean.setSaveCaseStepFlag(false);
					log.debug("++validateFlag--" + validateFlag);
					//added for defect ESPRD00621016
					if (!validateFlag) {
						return "";
					}
				}
				//Modified for defect ESPRD00380802 starts
				if (commonEntityDataBean.isSaveNoteflag()
						&& commonEntityDataBean.isNewNotesRender()) {
					commonNotesControllerBean.saveNotes();
					//modified for defect ESPRD00806656
					//commonEntityDataBean.setSaveNoteflag(false);
					//if notes validation fails then resonse should return w/o
					//case record update/insert.
					if (!commonEntityDataBean.isNotesSaveMsg()) {
						logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
						return ContactManagementConstants.EMPTY_STRING;
					}
				}
				//defect ESPRD00380802 ends
				//Added for defect ESPRD00379116 starts
				//        		if((logCaseDataBean.isSuperviserRevReqIndForCaseType() ||
				// logCaseDataBean.isSuperviserRevReqInd())&&
				// caseDetailsVO.getStatus().equals("SR")){
				//    			System.err.println("++logCaseDataBean.isSuperviserRevReqIndForCaseType().."+logCaseDataBean.isSuperviserRevReqIndForCaseType()+"
				// logCaseDataBean.isSuperviserRevReqInd()==>"+
				// logCaseDataBean.isSuperviserRevReqInd());
				//    			sendAutomaticAlertIfReq();
				//    		}
				//COMMENTED FOR THE DEFECT ESPRD00703588
				/*if (logCaseDataBean.isCompletedApprFlag()) {
					caseDetailsVO.setStatus("A");
					AlertVO reviewAlertVO = new AlertVO();
					String assignedToUserid = null;
					if (logCaseDataBean.getUserIDAndWUMap() != null)
						assignedToUserid = (String) logCaseDataBean
								.getUserIDAndWUMap()
								.get(caseDetailsVO.getAssignedToWorkUnitSK());
					reviewAlertVO.setRcvgUserID(assignedToUserid);
					reviewAlertVO.setStatus("R");
					reviewAlertVO.setAddedAuditUserID(getUserID());
					reviewAlertVO.setAuditUserID(getUserID());
					if (reviewAlertVO.getRcvgUserID() != null)
						logCaseDataBean.getAlertsVOList().add(reviewAlertVO);

				}
				if (logCaseDataBean.isCompletedDeniFlag()) {
					caseDetailsVO.setStatus("O");
					AlertVO reviewAlertVO = new AlertVO();
					String assignedToUserid = null;
					if (logCaseDataBean.getUserIDAndWUMap() != null)
						assignedToUserid = (String) logCaseDataBean
								.getUserIDAndWUMap()
								.get(caseDetailsVO.getAssignedToWorkUnitSK());
					reviewAlertVO.setRcvgUserID(assignedToUserid);
					reviewAlertVO.setStatus("R");
					reviewAlertVO.setAddedAuditUserID(getUserID());
					reviewAlertVO.setAuditUserID(getUserID());
					if (reviewAlertVO.getRcvgUserID() != null)
						logCaseDataBean.getAlertsVOList().add(reviewAlertVO);
				}*/
				// END COMMENTED FOR THE DEFECT ESPRD00703588

				//defect ESPRD00379116 ends
				setDaysToClose(caseDetailsVO);

				setAssoiciatedCaseReordsToCaseDetailVO();//UC-PGM-CRM-018.3_ESPRD00328556_07NOV09
				//ESPRD00610465 defect starts
				setAssoiciatedCaseReordsToCRDetailVO();
				//ESPRD00610465 defect ends

				if ("CaseUpdate".equals(action)) {
					log.debug("While updating case EnableSaveLink value is $$ "
							+ logCaseDataBean.isEnableSaveLink());
					log
							.debug("While updating case DisableScreenField value is $$ "
									+ logCaseDataBean.isDisableScreenField());

					if (!logCaseDataBean.isEnableSaveLink()
							&& !logCaseDataBean.isDisableScreenField()) {
						log.debug("Case Record update is Started "
								+ caseDetailsVO.getCaseType());
						caseDetailsVO.setSupvrRewInd(caseHelper
								.getSupWiserRewReqInd());
						log.debug("CaseSK in Update mode is $$ "
								+ caseDetailsVO.getCaseSK());
						caseRecord = logCaseVOToDomainConverter
								.convertCaseRecordVOToDO(caseDetailsVO);
						caseXref = logCaseVOToDomainConverter
								.convertCaseCMNEnityXRef(caseRecord);
						if (!caseXref.isEmpty()) {

							caseRecord.setCaseCommonEntityCrossRefs(caseXref);
						}
						/* moved in case status onstatuschange() method
						 * caseRecord = caseHelper.setStatusDate(caseRecord,
								caseDetailsVO);*/

						msg = MaintainContactManagementUIConstants.CASE_SAVE_SUCCESS;
					} else {
						log.debug("Case was closed. Adding Notes is started");
						caseRecord = logCaseVOToDomainConverter
								.convertCaseRecordVOToDO(caseDetailsVO);
						caseXref = logCaseVOToDomainConverter
								.convertCaseCMNEnityXRef(caseRecord);
						if (!caseXref.isEmpty()) {

							caseRecord.setCaseCommonEntityCrossRefs(caseXref);
						}
						//added for defect ESPRD00299506
						createOrUpdateCustomfields(caseRecordVO, caseRecord,
								logCaseVOToDomainConverter);

						createOrUpdateRoutingInfo(caseRecord,
								logCaseVOToDomainConverter);

						createOrUpdateAlertsInfo(caseRecord,
								logCaseVOToDomainConverter);

						createOrUpdateCaseEventsInfo(caseRecord,
								logCaseVOToDomainConverter);

						createOrUpdateCaseStepsInfo(caseRecord,
								logCaseVOToDomainConverter);

						createOrUpdateAttachmentsInfo(caseRecord,
								logCaseVOToDomainConverter);
						//EOF defect ESPRD00299506
						if (caseRecord != null) {

							if (commonEntityDataBean.getNoteList() != null
									&& commonEntityDataBean.getNoteList()
											.size() > 0) {
								log.debug("Notes are available");
								addNoteSet(caseRecord);
							} else {
								log
										.debug("Notes are not available from Notes portlet");
								if (logCaseDataBean.getCaseNotesSetTempList()
										.size() > 0) {
									int size = logCaseDataBean
											.getCaseNotesSetTempList().size();
									log
											.debug("Notes is available from temp list and size is $$ "
													+ size);
									for (int i = 0; i < size; i++) {
										caseRecord
												.setNoteSet((NoteSet) logCaseDataBean
														.getCaseNotesSetTempList()
														.get(i));
									}
								}
							}
							caseRecordVO.setCaseRecord(caseRecord);
							applyBRsOnCaseRecord(caseRecord);//added for
															 // UC-PGM-CRM-018.7_ESPRD00382416_15JAN10

							createOrUpdateCaseRecord(
									caseRecordVO,
									MaintainContactManagementUIConstants.CASE_SAVE_SUCCESS);

						}
						/** defect ESPRD00834054
						 *  Population of notes from case record should be with the
						 *  update caseRecord object which is happening by invoking
						 *  this  method in createOrUpdateCaseRecord()> getCaseRecord()>populateNoteSet();
						 *  As below code is invoking with old caserecord object holding in session.hence
						 *  commented.
						 *  This will going to resolve duplicate notes issue.
						 * */
						//populateNoteSet(caseRecord);
						return MaintainContactManagementUIConstants.SUCCESS;
					}
				} else {
					log.debug(" -------- Case Record Add is Started ----"
									+ caseDetailsVO.getCaseType());
					caseDetailsVO.setCaseSensitiveDataInd("N");
					caseDetailsVO.setSupvrRewInd(caseHelper
							.getSupWiserRewReqInd());

					caseRecord = logCaseVOToDomainConverter
							.convertCaseRecordVOToDO(caseDetailsVO);
					caseRecord.setCaseStatusDate(new Date());
					caseXref = logCaseVOToDomainConverter
							.convertCaseCMNEnityXRef(caseRecord);
					if (!caseXref.isEmpty()) {

						caseRecord.setCaseCommonEntityCrossRefs(caseXref);
					}

					msg = MaintainContactManagementUIConstants.CASE_SAVE_SUCCESS;//modified
																				 // for
																				 // defect
																				 // :ESPRD00335106
				}
				createOrUpdateCustomfields(caseRecordVO, caseRecord,
						logCaseVOToDomainConverter);

				createOrUpdateRoutingInfo(caseRecord,
						logCaseVOToDomainConverter);

				createOrUpdateAlertsInfo(caseRecord, logCaseVOToDomainConverter);

				createOrUpdateCaseEventsInfo(caseRecord,
						logCaseVOToDomainConverter);

				createOrUpdateCaseStepsInfo(caseRecord,
						logCaseVOToDomainConverter);

				createOrUpdateAttachmentsInfo(caseRecord,
						logCaseVOToDomainConverter);

				if (caseRecord != null) {

					if (commonEntityDataBean.getNoteList() != null
							&& commonEntityDataBean.getNoteList().size() > 0) {
						log.debug("Notes are available");
						addNoteSet(caseRecord);
					} else {
						log.debug("Notes are not available from Notes portlet");
						if (logCaseDataBean.getCaseNotesSetTempList().size() > 0) {
							int size = logCaseDataBean
									.getCaseNotesSetTempList().size();
							log
									.debug("Notes is available from temp list and size is $$ "
											+ size);
							for (int i = 0; i < size; i++) {
								caseRecord.setNoteSet((NoteSet) logCaseDataBean
										.getCaseNotesSetTempList().get(i));
							}
						}
					}
					caseRecordVO.setCaseRecord(caseRecord);
					applyBRsOnCaseRecord(caseRecord);//added for
													 // UC-PGM-CRM-018.7_ESPRD00382416_15JAN10
					createOrUpdateCaseRecord(caseRecordVO, msg);
				}
				/** defect ESPRD00834054
				 *  Population of notes from case record should be with the
				 *  update caseRecord object which is happening by invoking
				 *  this  method in createOrUpdateCaseRecord()> getCaseRecord()>populateNoteSet();
				 *  As below code is invoking with old caserecord object holding in session.hence
				 *  commented.
				 *  This will going to resolve duplicate notes issue.
				 * */
				//populateNoteSet(caseRecord);

			}
		}
		logCaseDataBean.setFileBigSavedFlag(true);
		// Added following line for defect 681457
		logCaseDataBean.setLoadLetterData(true);
		/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end */
		
		commonEntityDataBean.setCommonNoteSaved(false); // modified for defect ESPRD00802065
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "saveCaseRecord");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to save the case record.
	 * 
	 * @param requestVO
	 *            holds the case record DO
	 * @param message
	 *            represents and holds the message to display while save.
	 * @return String
	 */
	public String createOrUpdateCaseRecord(CaseRequestVO requestVO,
			String message) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "createOrUpdateCaseRecord");
		CaseRecord returnedCaseRecord = null;
		CaseRequestVO returnedCaseRequestVO = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CMProcessDelegate processDelegate = null;
		try {

			Set s = requestVO.getCaseRecord().getCaseAttachCrossReferences();
			Iterator itr = s.iterator();
			int i = 1;
			while (itr.hasNext()) {

				CaseAttachCrossReference ref = (CaseAttachCrossReference) itr
						.next();
				if(log.isDebugEnabled()){
				log.debug("HistoryIndicator-->"
						+ ref.getHistoryIndicator() + "::DetachIndicator-->"
						+ ref.getDitachedOrAttachedIndicator());
				}
				//          	System.err.println("FileName"+i+":->"+ref.getAttachment().getAttachmentFileName()+"::attachVO.isExistDoc():->"+ref.getAttachment().isExistDoc());
				i++;
			}
			processDelegate = new CMProcessDelegate();
			//for defect ESPRD00790505
			boolean chkDocfinity = logCaseDataBean.getAttachmentAddOrUpdate();
			if (logCaseDataBean.getAttachmentVOList() != null) {
				chkDocfinity = logCaseDataBean.getAttachmentAddOrUpdate() ? true
						: logCaseDataBean.getAttachmentListSize() != logCaseDataBean
								.getAttachmentVOList().size();
			}
			requestVO.setCheckDocfinityCall(chkDocfinity);
			returnedCaseRequestVO = processDelegate.createCase(requestVO);

			log.debug("returnedCaseRequestVO--"
					+ returnedCaseRequestVO);
			//          UC-PGM-CRM-018.4_ESPRD00379771_05JAN10
			if (logCaseDataBean.getDeletedActCMCaseEventsList().size() > 0) {
				CaseDelegate caseDeleg = new CaseDelegate();
				try {
					caseDeleg.deleteActCMCaseEvents(logCaseDataBean
							.getDeletedActCMCaseEventsList(), logCaseDataBean.getLoggedInUserID());// Modified for the Performance fix //USERID2
					logCaseDataBean.getDeletedActCMCaseEventsList().clear();
				} catch (Exception e) {

					log
							.error("Exception while deleting CaseEvents afterMajorsave");
				}
			}//EOF UC-PGM-CRM-018.4_ESPRD00379771_05JAN10
			//Modified for defect ESPRD00674473 starts
			if (logCaseDataBean.getDeletedActCMCaseStepsList().size() > 0) {
				CaseDelegate caseDeleg = new CaseDelegate();
				try {
					caseDeleg.deleteActCMCaseSteps(logCaseDataBean
							.getDeletedActCMCaseStepsList(), logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix //USERID2
					logCaseDataBean.getDeletedActCMCaseStepsList().clear();
				} catch (Exception e) {

					log
							.error("Exception while deleting CaseEvents afterMajorsave");
				}
			}
			//Modified for defect ESPRD00674473 ends

			// Added for the defect id ESPRD00425780
			log.info("getEventToRemove size"
					+ logCaseDataBean.getEventToRemove().size());
			if (logCaseDataBean.getEventToRemove() != null
					&& logCaseDataBean.getEventToRemove().size() > 0) {
				CaseDelegate caseDeleg = new CaseDelegate();
				try {
					caseDeleg.deleteActCMCaseEvents(logCaseDataBean
							.getEventToRemove());
					logCaseDataBean.getEventToRemove().clear();
				} catch (Exception e) {
					log.info("Exception while deleting Events");
					e.printStackTrace();
				}
			}
			// Ends for the defect id ESPRD00425780

			//	Added for the defect id ESPRD00391040
			if (logCaseDataBean.getStepToRemove() != null
					&& logCaseDataBean.getStepToRemove().size() > 0) {
				CaseDelegate caseDeleg = new CaseDelegate();
				try {
					log.debug("Before delegate getStepToRemove size"
							+ logCaseDataBean.getStepToRemove().size());
					caseDeleg.deleteActCMCaseSteps(logCaseDataBean
							.getStepToRemove());
					logCaseDataBean.getStepToRemove().clear();
				} catch (Exception e) {
					log.info("Exception while deleting Steps");
					e.printStackTrace();
				}
			}
			// Ends for the defect id ESPRD00391040

			if (returnedCaseRequestVO != null
					&& returnedCaseRequestVO.getCaseRecord() != null) {
				returnedCaseRecord = returnedCaseRequestVO.getCaseRecord();
				log.debug("---------returnedCaseRecord-----"
						+ returnedCaseRecord);
				if (returnedCaseRecord.getCaseSK() != null) {
					log.debug("---------returnedCaseRecord.getCaseSK()-----"
									+ returnedCaseRecord.getCaseSK());
					//Added for defectESPRD00542354
					/*Set routingSet = null;
					routingSet = returnedCaseRecord.getCaseRoutings();
					//                	System.err.println("-------------routingSet----------"+routingSet);
					//                    if (routingSet != null && !routingSet.isEmpty())
					//                    {
					String entityType = null;
					String entityID = null;
					CaseDelegate caseDelegate = null;
					CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
					EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
					CaseDelegate caseDelegate2 = new CaseDelegate();
					//for ESPRD00762703
					//CommonCMCaseDetailsVO commonCMCaseDetailsVO = null;
					caseRecordSearchCriteriaVO.setCaseSK(returnedCaseRecord
							.getCaseSK().toString());*/
					try {
						/*enterpriseSearchResultsVO = caseDelegate2
								.getCaseRecords(caseRecordSearchCriteriaVO);
						if (enterpriseSearchResultsVO.getSearchResults() != null
								&& enterpriseSearchResultsVO.getSearchResults()
										.size() == 1) {
							CaseRecordSearchResultsVO searchResultsVO = (CaseRecordSearchResultsVO) enterpriseSearchResultsVO
									.getSearchResults().get(0);
							//commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
							System.err
									.println("+++++++caseSK "
											+ returnedCaseRecord.getCaseSK()
													.toString());
							//commonCMCaseDetailsVO.setCaseSK(returnedCaseRecord
									//.getCaseSK().toString());
							entityType = searchResultsVO.getEntityType();
							System.err.println("+++++++entityType "
									+ entityType);
							 FIND BUGS_FIX 
							if (searchResultsVO.getCommonEntitySK() != null
									&& !searchResultsVO.getCommonEntitySK()
											.equals(Long.valueOf(0))) {
								entityID = searchResultsVO.getCommonEntitySK()
										.toString();
								System.err.println("+++++++entityID "
										+ entityID);
							}*/
							//commonCMCaseDetailsVO.setEntityType(entityType);
							//commonCMCaseDetailsVO.setEntityID(entityID);
							//commonCMCaseDetailsVO.setActionCode("CaseUpdate");
							//Modified for defect ESPRD00549039 starts
							//for ESPRD00762703 defect.
							/*if (!logCaseDataBean.isNavToOtherPortletFlag()) {
								showCasePortlet(commonCMCaseDetailsVO);
							} else {*/
								logCaseDataBean.getCaseRegardingVO()
										.setCaseRecordNumber(
												returnedCaseRecord.getCaseSK()
														.toString());
								//getCaseRecord(returnedCaseRecord.getCaseSK());
								logCaseDataBean.getInqAbtEntityListBeforeSave()
										.clear();//ESPRD00358412
								logCaseDataBean.setActionCode("CaseUpdate");
								log.debug(" Appeal CASE RECORD CREATED SUCCESSFULL:::::"
												+ returnedCaseRecord);
								logCaseDataBean.setFromIPC(false);
								logCaseDataBean.setNavToOtherPortletFlag(false);
								loadLogCasePageWithCaseSk(String.valueOf(returnedCaseRecord.getCaseSK()));
							//}
							//defect ESPRD00549039 ends
							ContactManagementHelper.setErrorMessage(message,
									null, null, null);
							logCaseDataBean.setShowCaseMessage(true);
							logCaseDataBean.setDisableLogAddCaseAssocCrspondence(false);
							logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
							
							//to refresh my task tab data (MyCase, GroupCase, CaseWatchList)
							FacesContext fc = FacesContext.getCurrentInstance();

							PortletSession pSession = (PortletSession) fc.getExternalContext()
									.getSession(true);
							pSession.setAttribute(ContactManagementConstants.RefreshMyTaskData,
									ContactManagementConstants.REFRESH_MY_TASK_CASE_DATA,
									pSession.APPLICATION_SCOPE);
						//}
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*
					 * }else{//ESPRD00542354 ends
					 * logCaseDataBean.getCaseRegardingVO()
					 * .setCaseRecordNumber(
					 * returnedCaseRecord.getCaseSK().toString());
					 * getCaseRecord(returnedCaseRecord.getCaseSK());
					 * logCaseDataBean.getInqAbtEntityListBeforeSave().clear();//ESPRD00358412
					 * logCaseDataBean.setActionCode("CaseUpdate");
					 * 
					 * log.debug(" ------- CASE RECORD CREATED
					 * SUCCESSFULL:::::"+ returnedCaseRecord);
					 * ContactManagementHelper.setErrorMessage(message, null,
					 * null, null); logCaseDataBean.setShowCaseMessage(true);
					 * logCaseDataBean.setFromIPC(false); }
					 */
				} else {
					log.debug(" ---------- CASE RECORD NOT CREATED SUCCESSFULL:::::"
									+ returnedCaseRecord);
					ContactManagementHelper.setErrorMessage(
							MaintainContactManagementUIConstants.SAVE_FAILURE,
							null, null, null);
					logCaseDataBean.setShowCaseMessage(false);
					logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
				}
			} else {
				log.debug(" >>>>>>>>>>> CASE RECORD NOT CREATED SUCCESSFULL:::::"
								+ returnedCaseRecord);
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.SAVE_FAILURE,
						null, null, null);
				logCaseDataBean.setShowCaseMessage(false);
				logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
			}
		} catch (CaseCreateBusinessException e) {
			e.printStackTrace();
			log.debug("++ErrorCode--" + e.getErrorCode());
			e.printStackTrace();
			if (e.getErrorCode() != null
					&& (e.getErrorCode().equals("9-3210-0255") || e
							.getErrorCode().equals("9-3210-0260"))) {
				ContactManagementHelper.setMessage(e.getMessage());
			} else {
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.SAVE_FAILURE,
						null, null, null);
			}

			logCaseDataBean.setShowCaseMessage(true);
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);

			log.debug(" -------------- Exception while adding CaseRecord ----------");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info(" --------- END OF createOrUpdateCaseRecord ------------");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method Creates or Updates Cusom Fields.
	 * 
	 * @param caseRequestVO
	 *            Takes caseType
	 * @param caseRecord
	 *            Takes caseRecord
	 * @param logCaseVOToDomainConverter
	 *            Takes logCaseVOToDomainConverter
	 */
	private void createOrUpdateCustomfields(CaseRequestVO caseRequestVO,
			CaseRecord caseRecord,
			LogCaseVOToDomainConverter logCaseVOToDomainConverter) {
		List cfList = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CustomFieldValueVO customFieldValueVO = logCaseDataBean
				.getCustomFieldValueVO();
		if (customFieldValueVO != null) {
			cfList = logCaseVOToDomainConverter.convertCustomFieldVO(
					caseRecord, customFieldValueVO);
			if (cfList != null && !cfList.isEmpty()) {
				log
						.debug("After DO convertion of CustomField, List size is $$ "
								+ cfList.size());
				caseRequestVO.setListOfCustomFieldValues(cfList);
			} else {
				log
						.debug("After DO convertion of CustomField,List size is empty");
			}
		}
	}

	/**
	 * This method Creates or Updates RoutingInfo.
	 * 
	 * @param caseRecord
	 *            Takes caseRecord
	 * @param logCaseVOToDomainConverter
	 *            Takes logCaseVOToDomainConverter
	 */
	private void createOrUpdateRoutingInfo(CaseRecord caseRecord,
			LogCaseVOToDomainConverter logCaseVOToDomainConverter) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "createOrUpdateRoutingInfo");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseRouting caseRouting = null;
		CMRoutingVO routingVO = null;
		List routingList = null;
		Set routingDOSet = null;

		routingList = logCaseDataBean.getRoutingInfoList();
		if (!routingList.isEmpty()) {
			int routingListSize = 0;
			int routingSetSize = 0;
			routingDOSet = new HashSet();
			routingListSize = routingList.size();
			for (int i = 0; i < routingListSize; i++) {
				routingVO = (CMRoutingVO) logCaseDataBean.getRoutingInfoList()
						.get(i);
				if (StringUtils.isNotEmpty(routingVO.getShow())) {
					caseRouting = logCaseVOToDomainConverter
							.convertCMRoutingVOToDO(routingVO, caseRecord);
					log.debug(" routingVO.getAssignThisRecordTo() :> "
							+ routingVO.getAssignThisRecordTo());
					log.debug("routingVO.getShow() :> "
							+ routingVO.getShow());
					//                    if (routingVO.getAssignThisRecordTo() != null &&
					// !routingVO.getShow().equals("U")){
					//                    	String deptID = routingVO.getAssignThisRecordTo();
					//                    	ContactMaintenanceDelegate contactMaintenanceDelegate =
					// new ContactMaintenanceDelegate();
					//                    	System.err.println(" deptID :> " + deptID);
					//                    	LineOfBusinessHierarchy lob;
					//						try {
					//							lob =
					// contactMaintenanceDelegate.getLineOfBusinessForDept(new
					// Long(deptID));
					//							System.err.println("lob :> " +
					// lob.getLineOfBusinessHierarchySK());
					//							caseRecord.setLobHierarchy(lob);
					//						} catch (LOBHierarchyFetchBusinessException e) {
					//							e.printStackTrace();
					//						}
					//                    }
					if (caseRouting != null) {
						routingDOSet.add(caseRouting);
						log.debug("After Do convertion Routing Set size is $$ "
								+ routingDOSet.size());

						// Added by ICS
						if (routingVO.getAddThisRecordToMyWatchlist()) {
							Set watchListSet = new HashSet();
							caseRouting.setWatchListIndicator(Boolean.TRUE);
							CaseWatchList caseWatchList = new CaseWatchList();
							caseWatchList.setCaseRecord(caseRecord);
							//Modified for GAI Recursive Call_Fix
							caseWatchList
									.setWorkUnit(logCaseVOToDomainConverter
											.getWorkUnitForUser(logCaseDataBean.getLoggedInUserSK() // Modified for the Performance fix
													.toString()));
							caseWatchList.setAddedAuditTimeStamp(new Date());
							caseWatchList.setAddedAuditUserID(logCaseDataBean
									.getLoggedInUserID()); // Modified for the Performance fix
							caseWatchList.setAuditTimeStamp(new Date());
							caseWatchList.setAuditUserID(logCaseDataBean
									.getLoggedInUserID()); // Modified for the Performance fix
							watchListSet.add(caseWatchList);
							caseRecord.setCaseWatchLists(watchListSet);

						} else {
							caseRouting.setWatchListIndicator(Boolean.FALSE);

						}
						caseRouting.setVersionNo(logCaseDataBean.getRoutingVO()
								.getVersionNo());
						caseRouting.setAuditUserID(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
						caseRouting.setAuditTimeStamp(new Date());

						// Ends

					}
				}
			}
			routingSetSize = routingDOSet.size();
			if (routingSetSize > 0) {
				caseRecord.setCaseRoutings(routingDOSet);
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "createOrUpdateRoutingInfo");
	}

	/**
	 * This mehtod creates or updates Alerts Info.
	 * 
	 * @param caseRecord
	 *            Takes caseRecord as param
	 * @param logCaseVOToDomainConverter
	 *            Takes logCaseVOToDomainConverter as param
	 */
	private void createOrUpdateAlertsInfo(CaseRecord caseRecord,
			LogCaseVOToDomainConverter logCaseVOToDomainConverter) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "createOrUpdateAlertsInfo");
		Alert alertDO = null;
		AlertVO alertVO = null;
		List alertList = null;
		Set alertDOSet = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		alertList = logCaseDataBean.getAlertsVOList();
		if (!alertList.isEmpty()) {
			int alertSetSize = 0;
			int alertListSize = 0;
			alertDOSet = new HashSet();
			alertListSize = alertList.size();
			for (int i = 0; i < alertListSize; i++) {
				alertVO = (AlertVO) logCaseDataBean.getAlertsVOList().get(i);
				alertDO = logCaseVOToDomainConverter.convertAlertVOToDO(
						alertVO, caseRecord);
				if (alertDO != null) {
					alertDO.setAlertOriginCode("CS");//ESPRD00524051_UC-PGM-CRM-053_23SEP2010
					if ((caseRecord
							.getCaseStatusCode()
							.startsWith(
									MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_C) || caseRecord
							.getCaseStatusCode()
							.equalsIgnoreCase(
									MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_W))
							&& alertDO
									.getAlertStatusCode()
									.equalsIgnoreCase(
											ContactManagementConstants.ALERT_STATUS_OPEN)) {
						alertDO
								.setAlertStatusCode(ContactManagementConstants.ALERT_STATUS_CODE_CLOSE);
					}
					log.debug("alertVO.getStatus()" + alertVO.getStatus());
					//THIS CODE IS MOVE TO SaveAlert() for the defect ESPRD00703588
					/*if (alertVO
							.getStatus()
							.equals(
									MaintainContactManagementUIConstants.ALERT_STATUS_CA)) {
						 DEFECT ESPRD00362698 STARTS 
						caseRecord
								.setCaseStatusCode(MaintainContactManagementUIConstants.APPROVED_CASE_STATUS);
						logCaseDataBean
								.getCaseDetailsVO()
								.setStatus(
										MaintainContactManagementUIConstants.APPROVED_CASE_STATUS);//ESPRD00362595
						 DEFECT ESPRD00362698 END 
					} else if (alertVO
							.getStatus()
							.equals(
									MaintainContactManagementUIConstants.ALERT_STATUS_CD)) {
						 DEFECT ESPRD00362734 STARTS 
						caseRecord
								.setCaseStatusCode(MaintainContactManagementUIConstants.OPEN_CASE_STATUS);
						logCaseDataBean
								.getCaseDetailsVO()
								.setStatus(
										MaintainContactManagementUIConstants.OPEN_CASE_STATUS);//ESPRD00362595
						 DEFECT ESPRD00362734 END 
					}*/
					// END THIS CODE IS MOVE TO SaveAlert() for the defect ESPRD00703588
					log.debug("caseDetailsVO.setStatus"
							+ caseRecord.getCaseStatusCode());

					alertDOSet.add(alertDO);
					log.debug("After Do convertion Alert Set size is $$ "
							+ alertDOSet.size());
				}
			}
			alertSetSize = alertDOSet.size();
			if (alertSetSize > 0) {
				caseRecord.setAlerts(alertDOSet);
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "createOrUpdateAlertsInfo");
	}

	/**
	 * This mehtod creates or updates EventsInfo.
	 * 
	 * @param caseRecord
	 *            Takes caseRecord as param
	 * @param logCaseVOToDomainConverter
	 *            Takes LogCaseVOToDomainConverter as param
	 */
	private void createOrUpdateCaseEventsInfo(CaseRecord caseRecord,
			LogCaseVOToDomainConverter logCaseVOToDomainConverter) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "createOrUpdateCaseEventsInfo");
		ActCMCaseEvent actCMCaseEvent = null;
		CaseEventsVO caseEventsVO = null;
		List caseEventsList = null;
		//CaseTypeEvent typeEvent = null;
		Set actCMCaseEventsSet = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		caseEventsList = logCaseDataBean.getCaseEventsVOList();
		if (!caseEventsList.isEmpty()) {
			int eventsListSize = 0;
			int eventsSetSize = 0;
			//for defect ESPRD00864837
			Integer editEventSeq = 1;
			int eventRowIndex = 0;
			if(!isNullOrEmptyField(logCaseDataBean.getCaseEventsIndex())){
				log.debug("logCaseDataBean.getCaseEventsIndex()="+logCaseDataBean.getCaseEventsIndex());
				eventRowIndex=Integer.parseInt(logCaseDataBean.getCaseEventsIndex());
			}
			log.debug("eventRowIndex="+eventRowIndex);
			CaseDelegate caseDelegate=new CaseDelegate();
			Integer eventMaxSeq = null;
			if(logCaseDataBean.getCaseDetailsVO().getCaseSK()!=null){
				eventMaxSeq = caseDelegate.getActCMCaseEventsSequence(logCaseDataBean.getCaseDetailsVO().getCaseSK());
			}
			if(eventMaxSeq==null){
				eventMaxSeq = new Integer(1);
			}
			log.debug("eventMaxSeq="+eventMaxSeq);
			actCMCaseEventsSet = new HashSet();
			eventsListSize = caseEventsList.size();
			for (int index = 0; index < eventsListSize; index++) {
				caseEventsVO = (CaseEventsVO) caseEventsList.get(index);
				actCMCaseEvent = logCaseVOToDomainConverter
						.convertCaseEventsVOToDO(caseEventsVO, caseRecord);
				if (actCMCaseEvent != null) {
					//for defect ESPRD00864837
					/** If it is a new event the sequence no is assigning as max sequence + 1.
					 *  Sequence number of event edited is captured which is used while invoking
					 *  create letter from event section.
					 * */
					if(actCMCaseEvent.getCaseEventSeqNum() == null
							|| (actCMCaseEvent.getCaseEventSeqNum() != null && actCMCaseEvent
									.getCaseEventSeqNum().intValue() == 0)){
						actCMCaseEvent.setCaseEventSeqNum(eventMaxSeq++);
					}
					if(index==eventRowIndex){
						editEventSeq=actCMCaseEvent.getCaseEventSeqNum();
						log.debug("editEventSeq="+editEventSeq);
					}
					actCMCaseEventsSet.add(actCMCaseEvent);

				}
			}
			//for defect ESPRD00864837
			logCaseDataBean.setEditEventSeq(editEventSeq);
			/* pre decreement we are doing as the value in loop having 
			 * post increment value which assigned to the object last*/
			logCaseDataBean.setAddEventSeq(--eventMaxSeq);
			eventsSetSize = actCMCaseEventsSet.size();
			if (eventsSetSize > 0) {
				caseRecord.setActCMCaseEvents(actCMCaseEventsSet);
			}
		}
		//UC-PGM-CRM-018.4_ESPRD00379771_05JAN10
		logCaseDataBean.getDeletedActCMCaseEventsList().clear();

		if (!logCaseDataBean.getDeletedEventsList().isEmpty()) {
			//List deletedEventDOList = new ArrayList();
			ActCMCaseEvent deletedActCMCaseEvent = null;
			Set deletedActCMCaseEventsSet = new HashSet();
			Iterator list_itr = logCaseDataBean.getDeletedEventsList()
					.iterator();
			while (list_itr.hasNext()) {

				caseEventsVO = (CaseEventsVO) list_itr.next();

				deletedActCMCaseEvent = logCaseVOToDomainConverter
						.convertCaseEventsVOToDOForDel(caseEventsVO, caseRecord);
				if (deletedActCMCaseEvent != null) {
					deletedActCMCaseEventsSet.add(deletedActCMCaseEvent);
				}
			}
			if (deletedActCMCaseEventsSet.size() > 0) {
				list_itr = deletedActCMCaseEventsSet.iterator();
				while (list_itr.hasNext()) {
					logCaseDataBean.getDeletedActCMCaseEventsList().add(
							list_itr.next());
				}
			}
		}//EOF UC-PGM-CRM-018.4_ESPRD00379771_05JAN10

		// Added for the defect id ESPRD00425780
		if (logCaseDataBean.getEventToRemove() != null) {
			List tempEventVOList = new ArrayList(logCaseDataBean
					.getEventToRemove());
			log.info("tempEventVOList:: " + tempEventVOList.size());
			logCaseDataBean.getEventToRemove().clear();
			log.info("tempEventVOList after clear:: " + tempEventVOList.size());
			if (tempEventVOList != null && tempEventVOList.size() > 0) {
				CaseEventsVO eventsVO = null;
				ActCMCaseEvent caseEvent = null;
				for (Iterator itr = tempEventVOList.iterator(); itr.hasNext();) {
					eventsVO = (CaseEventsVO) itr.next();
					caseEvent = logCaseVOToDomainConverter
							.convertCaseEventsVOToDO(eventsVO, caseRecord);
					if (caseEvent != null)
						logCaseDataBean.getEventToRemove().add(caseEvent);
					log.info("EventToRemove List size "
							+ logCaseDataBean.getEventToRemove().size());
				}
			}
		}
		// Ends for the defect id ESPRD00425780
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "createOrUpdateCaseEventsInfo");
	}

	/**
	 * This mehtod creates or updates CaseStepsInfo.
	 * 
	 * @param caseRecord
	 *            Takes caseRecord as param
	 * @param logCaseVOToDomainConverter
	 *            Takes LogCaseVOToDomainConverter as param
	 */
	private void createOrUpdateCaseStepsInfo(CaseRecord caseRecord,
			LogCaseVOToDomainConverter logCaseVOToDomainConverter) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "createOrUpdateCaseStepsInfo");
		ActCMCaseStep actCMCaseStep = null;
		CaseStepsVO caseStepsVO = null;
		List caseStepList = null;
		// CaseTypeStep typeStep = null;
		Set actCMCaseStepSet = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		caseStepList = logCaseDataBean.getCaseStepsVOList();
		if (!caseStepList.isEmpty()) {
			int stepsListSize = 0;
			int stepsSetSize = 0;
			//for defect ESPRD00864837
			Integer editStepSeq = 1;
			int stepRowIndex = 0;
			if(!isNullOrEmptyField(logCaseDataBean.getCaseStepsIndex())){
				log.debug("logCaseDataBean.getCaseEventsIndex()="+logCaseDataBean.getCaseStepsIndex());
				stepRowIndex=Integer.parseInt(logCaseDataBean.getCaseStepsIndex());
			}
			log.debug("stepRowIndex="+stepRowIndex);
			CaseDelegate caseDelegate=new CaseDelegate();
			Integer stepMaxSeq = null;
			if(logCaseDataBean.getCaseDetailsVO().getCaseSK()!=null){
				stepMaxSeq = caseDelegate.getActCMCaseStepSequence(logCaseDataBean.getCaseDetailsVO().getCaseSK());
			}
			log.debug("stepMaxSeq="+stepMaxSeq);
			if(stepMaxSeq==null){
				stepMaxSeq = new Integer(1);
			}
			log.debug("stepMaxSeq="+stepMaxSeq);
			actCMCaseStepSet = new HashSet();
			stepsListSize = caseStepList.size();
			//          added for defect ESPRD00362595
			String businessUnitCD = null;
			boolean isAllEventsAreClosed = logCaseDataBean
					.isAllDDUCaseEventesAreClosed();

			boolean isCaseStatusClosed = (logCaseDataBean
					.getCaseDetailsVO()
					.getStatus()
					.startsWith(
							MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_C) || logCaseDataBean
					.getCaseDetailsVO()
					.getStatus()
					.equalsIgnoreCase(
							MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_W));

			//hash map performance issue code change
			CaseType caseType=getCaseTypeFrmDosList(logCaseDataBean
					.getCaseDetailsVO().getCaseType(),null);
			if(caseType!=null){
			businessUnitCD =caseType.getBusinessUnitCaseTypeCode();
			}
			/*businessUnitCD = String.valueOf(logCaseDataBean
					.getCaseTypeSKAndBusinessUnit().get(
							logCaseDataBean.getCaseDetailsVO().getCaseType()));*/
			boolean isCaseTypeDDU = ContactManagementConstants.BUSINESS_UNIT_DDU
					.equalsIgnoreCase(businessUnitCD);

			//EOF ESPRD00362595
			for (int i = 0; i < stepsListSize; i++) {
				caseStepsVO = (CaseStepsVO) caseStepList.get(i);
				actCMCaseStep = logCaseVOToDomainConverter
						.convertCaseStepsVOToDO(caseStepsVO, caseRecord);
				if (actCMCaseStep != null) {
					//for defect ESPRD00864837
					log.debug("before :actCMCaseEvent.getCaseEventSeqNum()="+actCMCaseStep.getCaseStepSeqNum());
					if(actCMCaseStep.getCaseStepSeqNum() == null
							|| (actCMCaseStep.getCaseStepSeqNum() != null && actCMCaseStep.getCaseStepSeqNum().intValue() == 0)){
						actCMCaseStep.setCaseStepSeqNum(stepMaxSeq++);
					}
					log.debug("after :actCMCaseEvent.getCaseEventSeqNum()="+actCMCaseStep.getCaseStepSeqNum());
					log.debug("i="+i);
					if(i==stepRowIndex){
						editStepSeq=actCMCaseStep.getCaseStepSeqNum();
						log.debug("editEventSeq="+editStepSeq);
					}
					if (isCaseTypeDDU && isAllEventsAreClosed
							&& isCaseStatusClosed) {

						if (actCMCaseStep.getStepStatusCode() != null
								&& !actCMCaseStep
										.getStepStatusCode()
										.equalsIgnoreCase(
												MaintainContactManagementUIConstants.CASE_STEP_STATUS_CLOSED)) {

							actCMCaseStep
									.setStepStatusCode(MaintainContactManagementUIConstants.CASE_STEP_STATUS_VOID_OR_SKIP);
						}
					}//EOF ESPRD00362595
					actCMCaseStepSet.add(actCMCaseStep);

				}
			}
			//for defect ESPRD00864837
			log.debug("after: editEventSeq="+editStepSeq);
			logCaseDataBean.setEditStepSeq(editStepSeq);
			logCaseDataBean.setAddStepSeq(--stepMaxSeq);
			stepsSetSize = actCMCaseStepSet.size();
			logCaseDataBean.setAllDDUCaseEventesAreClosed(false);//ESPRD00362595
			if (stepsSetSize > 0) {
				caseRecord.setActCMCaseSteps(actCMCaseStepSet);
			}
		}

		//Modified for defect ESPRD00674473 starts
		logCaseDataBean.getDeletedActCMCaseStepsList().clear();

		if (!logCaseDataBean.getDeletedStepsList().isEmpty()) {
			//List deletedEventDOList = new ArrayList();
			ActCMCaseStep deletedActCMCaseStep = null;
			Set deletedActCMCaseStepsSet = new HashSet();
			log.debug("++logCaseDataBean.getDeletedStepsList()::"
					+ logCaseDataBean.getDeletedStepsList().size());
			Iterator list_itr = logCaseDataBean.getDeletedStepsList()
					.iterator();
			while (list_itr.hasNext()) {

				caseStepsVO = (CaseStepsVO) list_itr.next();

				deletedActCMCaseStep = logCaseVOToDomainConverter
						.convertCaseStepsVOToDOForDel(caseStepsVO, caseRecord);
				log.debug("deletedActCMCaseStep audit time stamp $$$$$"+deletedActCMCaseStep.getAuditTimeStamp());
				if (deletedActCMCaseStep != null) {
					deletedActCMCaseStepsSet.add(deletedActCMCaseStep);
				}
			}
			if (deletedActCMCaseStepsSet.size() > 0) {
				list_itr = deletedActCMCaseStepsSet.iterator();
				while (list_itr.hasNext()) {
					logCaseDataBean.getDeletedActCMCaseStepsList().add(
							list_itr.next());
				}
			}
		}

		//Modified for defect ESPRD00674473 ends

		// Added for the defect id ESPRD00391040
		if (logCaseDataBean.getStepToRemove() != null) {
			List tempStepVOList = new ArrayList(logCaseDataBean
					.getStepToRemove());
			log.info("tempStepVOList:: " + tempStepVOList.size());
			logCaseDataBean.getStepToRemove().clear();
			log.info("tempStepVOList after clear:: " + tempStepVOList.size());
			if (tempStepVOList != null && tempStepVOList.size() > 0) {
				CaseStepsVO stepsVO = null;
				ActCMCaseStep caseStep = null;
				for (Iterator itr = tempStepVOList.iterator(); itr.hasNext();) {
					stepsVO = (CaseStepsVO) itr.next();
					caseStep = (ActCMCaseStep) logCaseVOToDomainConverter
							.convertCaseStepsVOToDO(stepsVO, caseRecord);
					if (caseStep != null)
						logCaseDataBean.getStepToRemove().add(caseStep);
					log.info("StepToRemove List size "
							+ logCaseDataBean.getStepToRemove().size());
				}
			}
		}
		// Ends for the defect id ESPRD00391040
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "createOrUpdateCaseStepsInfo");
	}

	/**
	 * This mehtod creates or updates AttachmentsInfo.
	 * 
	 * @param caseRecord
	 *            Takes caseRecord as param
	 * @param logCaseVOToDomainConverter
	 *            Takes LogCaseVOToDomainConverter as param
	 */
	private void createOrUpdateAttachmentsInfo(CaseRecord caseRecord,
			LogCaseVOToDomainConverter logCaseVOToDomainConverter) {
		log.debug(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "createOrUpdateAttachmentsInfo");
		List attachmentList = null;
		Set attachmentSet = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		attachmentList = logCaseDataBean.getAttachmentVOList();
		if (attachmentList != null && !attachmentList.isEmpty()) {
			attachmentSet = getAttachments(attachmentList, caseRecord);
			if (attachmentSet != null && !attachmentSet.isEmpty()) {
				caseRecord.setCaseAttachCrossReferences(attachmentSet);
			}
		}
		log.debug(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "createOrUpdateAttachmentsInfo");
	}

	/**
	 * This mehod clears exising CRList.
	 */
	private void clearExistingCRList() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "clearExistingCRList");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List existingCR = logCaseDataBean.getExistingCRInfoList();
		List assoCR = logCaseDataBean.getCrAssociatedWithCaseInfoList();

		AssociatedCorrespondenceVO existingCRVO = null;
		AssociatedCorrespondenceVO assocCRVO = null;
		if (!existingCR.isEmpty() && !assoCR.isEmpty()) {
			int exicrListSize = 0;
			int assoCRListSize = 0;
			exicrListSize = existingCR.size();
			assoCRListSize = assoCR.size();
			log.debug("Existing CR List size at clearExistingCRList $$ "
					+ exicrListSize);
			for (int i = 0; i < assoCRListSize; i++) {

				assocCRVO = (AssociatedCorrespondenceVO) assoCR.get(i);
				if (assocCRVO != null) {
					int size = 0;
					if (exicrListSize > 1) {
						size = exicrListSize - 1;
					} else {
						size = exicrListSize;
					}
					for (int j = 0; j < size; j++) {

						existingCRVO = (AssociatedCorrespondenceVO) existingCR
								.get(j);
						if (existingCRVO != null) {
							if (assocCRVO
									.getCorrespondenceRecordNumber()
									.equals(
											existingCRVO
													.getCorrespondenceRecordNumber())) {
								existingCR.remove(j);
								log.debug("Case CR deleted records is $$ $$ "
										+ i);
							}
						}
					}
				}
			}
			if (existingCR.isEmpty()) {
				logCaseDataBean.setShowExistingCRDataTable(false);
				logCaseDataBean.setShowExistingCaseRecordsDataTable(false);//ESPRD00610465
																		   // defect
				logCaseDataBean.setShowCorrespondenceSearchScreen(false);
			} else {
				logCaseDataBean.setExistingCRInfoList(existingCR);
				logCaseDataBean.setShowExistingCRDataTable(true);
				logCaseDataBean.setShowExistingCaseRecordsDataTable(true);//ESPRD00610465
																		  // defect
				logCaseDataBean.setShowCorrespondenceSearchScreen(false);
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "clearExistingCRList");
	}

	/**
	 * This mehod clearExistingCaseList.
	 */
	private void clearExistingCaseList() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "clearExistingCaseList");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List existingCase = logCaseDataBean.getExistingCaseRecordsList();
		List assoCaseList = logCaseDataBean.getCaseRecordsAssoWithCaseList();

		int existingCaseSize = 0;
		int assoCaseSize = 0;
		existingCaseSize = existingCase.size();
		assoCaseSize = assoCaseList.size();

		//modified for UC-PGM-CRM-018.3_ESPRD00328556_07NOV09
		Map existingCaseMap = new Hashtable(existingCaseSize);

		for (int indx = 0; indx < existingCaseSize; indx++) {

			existingCaseMap.put(((AssociatedCaseVO) existingCase.get(indx))
					.getCaseID().toString(), existingCase.get(indx));
		}

		String caseIdStr = null;
		for (int indx = 0; indx < assoCaseSize; indx++) {
			caseIdStr = ((AssociatedCaseVO) assoCaseList.get(indx)).getCaseID()
					.toString();

			if (existingCaseMap.containsKey(caseIdStr)) {

				existingCaseMap.remove(caseIdStr);
			}
		}

		Iterator itr = existingCaseMap.keySet().iterator();

		existingCase = new ArrayList(existingCaseMap.keySet().size());
		Object associatedCaseVOObj = null;
		while (itr.hasNext()) {
			caseIdStr = itr.next().toString();

			associatedCaseVOObj = existingCaseMap.get(caseIdStr);

			existingCase.add(associatedCaseVOObj);
		}
		log.debug("Now existing casesize is : " + existingCase.size());

		//UC-PGM-CRM-018.3_ESPRD00328556_10NOV09

		if (logCaseDataBean.getExistingCaseSearchRowIndex() != -1)
			logCaseDataBean.setExistingCaseSearchRowIndex(0);

		logCaseDataBean.setExistingCaseRecordsList(existingCase);
		if (logCaseDataBean.getExistingCaseRecordsList().isEmpty()) {
			logCaseDataBean.setShowExistingCaseRecordsDataTable(false);
			logCaseDataBean.setShowCaseSearchScreen(false);
		} else {
			logCaseDataBean.setShowExistingCaseRecordsDataTable(true);
			logCaseDataBean.setShowCaseSearchScreen(false);
		}
		//EOF UC-PGM-CRM-018.3_ESPRD00328556_10NOV09

		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "clearExistingCaseList");
	}

	/**
	 * This method validates Case.
	 * 
	 * @param detailsVO
	 *            Takes CaseDetailsVO as param
	 * @return boolean
	 */
	public boolean validateCase(CaseDetailsVO detailsVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "validateCase");
		boolean flag = true;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowCaseMessage(false);
		LogCaseHelper helper = new LogCaseHelper();
		if (detailsVO != null) {
			if (logCaseDataBean.getActionCode() != null
					&& "CaseUpdate".equals(logCaseDataBean.getActionCode())) {
				//Modified for defect ESPRD00561630 BR CON0096 starts
				//commemted for the defect ESPRD00724956
				/*if (detailsVO.getStatusDate() != null
						&& detailsVO.getCreatedDate() != null) {
					boolean flag1 = EnterpriseCommonValidator
							.compareGreaterDate(detailsVO.getStatusDate(),
									detailsVO.getCreatedDate());
					if (!flag1) {
						flag = false;

						setErrorMessage(
								ContactManagementConstants.ERR_SW_FIRST_LESS_THAN_OR_EQUAL_TO_SECOND,
								new Object[] {},
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								null);
					}
				}*/
				//END THE commemted for the defect ESPRD00724956
				//defect ESPRD00561630 ends BR CON0096 ends
			}
			log.debug("details vo is not null");
			if (isNullOrEmptyField(detailsVO.getCaseType())) {
				log.debug("detailsVO.getCaseType() is null");
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.CASETYPE_REQUIRED,
						"casetype", null, null);
				flag = false;
			}
			if (isNullOrEmptyField(detailsVO.getStatus())) {
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.CASESTATUS_REQUIRED,
								"LOGCASESTATUS", null, null); // Modified for the Defect ESPRD00762688
				flag = false;
			}
			if (isNullOrEmptyField(detailsVO.getPriority())) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.PRIORITY_REQUIRED,
						"priority", null, null);
			}

			// Added for the defect id ESPRD00299832
			//ADDED THE CODE !EnterpriseCommonValidator.validateAlphaSpecialCharsForAlert FOR THE DEFECT 724956
			
			if (StringUtils.isNotEmpty(detailsVO.getCaseTitle())
					&& !EnterpriseCommonValidator
					.validateAlphaSpecialCharsForAlert(detailsVO
							.getCaseTitle())) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.CASETITLE_INVALID,
						"casetitle", null, null);
			}

			if (isNullOrEmptyField(detailsVO.getWorkUnit())) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.WORKUNIT_REQUIRED,
						"LOGCASEWORKUNIT", null, null);
			}

			if (logCaseDataBean.isShowDDUTypeScreen()) {
				CaseTypeDDUVO caseTypeDDUVO = detailsVO.getCaseTypeDDUVO();
				//Added for CR ESPRD00761940
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");      
				Date currentDate=null; 
					try {
						currentDate= sdf.parse(sdf.format(new Date()));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if (caseTypeDDUVO != null) {
					if (caseTypeDDUVO.getApplicationDateStr() != null
							&& caseTypeDDUVO.getApplicationDateStr().length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeDDUVO
										.getApplicationDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											"Acal", null, null);//ESPRD00357486

						}//Added for CR ESPRD00761940
						else{
						Date ApplicationDate=ContactManagementHelper.dateConverter(caseTypeDDUVO.getApplicationDateStr());
						if(currentDate!=null && !ApplicationDate.before(currentDate))
						{
							flag=false;
							ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.BR_CON0426,
									"Acal", null, null);
							
						}
						}

					}
					//Added for the CR ESPRD00846696
					if ((caseTypeDDUVO.getSubstantiallyCompletedDateStr() == null || caseTypeDDUVO
							.getSubstantiallyCompletedDateStr().trim().length() == MaintainContactManagementUIConstants.ZERO)) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.CASE_SUBSTANTIALY_CMPLETED_DATE_REQUIRED,
										"subcomdat", null, null);
					}
					else if (caseTypeDDUVO.getSubstantiallyCompletedDateStr() != null
							&& caseTypeDDUVO.getSubstantiallyCompletedDateStr()
									.length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeDDUVO
										.getSubstantiallyCompletedDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											"subcomdat", null, null);//ESPRD00357486

						}//Added for CR ESPRD00761940
						else {
						Date SubstantiallyCompletedDate=ContactManagementHelper.dateConverter(caseTypeDDUVO.getSubstantiallyCompletedDateStr());
						if(currentDate!=null && !SubstantiallyCompletedDate.before(currentDate))
						{
							flag=false;
							ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.BR_CON0427,
									"subcomdat", null, null);
							
						}
						}
						
					}
					if (caseTypeDDUVO.getPacketReceivedDateStr() != null
							&& caseTypeDDUVO.getPacketReceivedDateStr()
									.length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeDDUVO
										.getPacketReceivedDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											"pcktcal", null, null);

						}//Added for CR ESPRD00761940
						else{
						Date PacketReceivedDate=ContactManagementHelper.dateConverter(caseTypeDDUVO.getPacketReceivedDateStr());
						if(currentDate!=null && PacketReceivedDate.after(currentDate))
						{
							flag=false;
							ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.BR_CON0428,
									"pcktcal", null, null);
							
						}
						}
					}
					if (caseTypeDDUVO.getReviewInitiatedDateStr() != null
							&& caseTypeDDUVO.getReviewInitiatedDateStr()
									.length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeDDUVO
										.getReviewInitiatedDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											"reviewInDate", null, null);//ESPRD00357486

						}

					}

					if (logCaseDataBean.isShowReviewReq()) {
						if ((caseTypeDDUVO.getSchDateOfReviewStr() == null || caseTypeDDUVO
								.getSchDateOfReviewStr().length() == MaintainContactManagementUIConstants.ZERO)) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.CASE_REVIEWSCH_DATE_REQUIRED,
											"scheduledDateOfReview", null, null);
						} else if (!EnterpriseCommonValidator
								.validateDate(caseTypeDDUVO
										.getSchDateOfReviewStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.CASE_REVIEWSCH_DATE_INVALID,
											"scheduledDateOfReview", null, null);
						} 
						// Added for the Defect ESPRD00784025 
						// Starts
                       else if ("CaseUpdate".equals(logCaseDataBean
									.getActionCode())) {
                    	   			if (!caseTypeDDUVO
										.getSchDateOfReviewStr()
										.equals(caseTypeDDUVO.getSchDateOfReviewStrForUpdateMode())) {
									if (EnterpriseCommonValidator.compareLesserDate(
										ContactManagementHelper
															.dateConverter(caseTypeDDUVO
																	.getSchDateOfReviewStr()),
													new Date())) {
										flag = false;
										ContactManagementHelper
												.setErrorMessage(
														MaintainContactManagementUIConstants.CASE_REVIEWSCH_DATE_GREATER,
														"scheduledDateOfReview",
														null, null);
									}
								}
							}
						// End
						else {
							  if (EnterpriseCommonValidator.compareLesserDate(
								ContactManagementHelper
										.dateConverter(caseTypeDDUVO
												.getSchDateOfReviewStr()),
								new Date())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.CASE_REVIEWSCH_DATE_GREATER,
											"scheduledDateOfReview", null, null);
						 }
					  }
					}

					log.debug("Outside if receiveddt==== @@"
							+ caseTypeDDUVO.getReceivedDateStr());
					//Modified for the CR ESPRD00791195 starts
					/*if ((caseTypeDDUVO.getReceivedDateStr() == null || caseTypeDDUVO
							.getReceivedDateStr().length() == MaintainContactManagementUIConstants.ZERO)) {
						log.debug("Inside if receiveddt====@@@@@"
								+ caseTypeDDUVO.getReceivedDateStr());
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.CASE_RECIEVED_DATE_REQUIRED,
										"receiveddt", null, null);
					} else*/ 
					if ((!isNullOrEmptyField(caseTypeDDUVO.getReceivedDateStr()) && caseTypeDDUVO
							.getReceivedDateStr().length() > MaintainContactManagementUIConstants.ZERO)) { 
						
						if (!EnterpriseCommonValidator
							.validateDate(caseTypeDDUVO.getReceivedDateStr())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
										"receiveddt", null, null);//ESPRD00357486

						}
					}//Modified for the CR ESPRD00791195 ends
					if (caseTypeDDUVO.getReceiptDateStr() != null
							&& caseTypeDDUVO.getReceiptDateStr().length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeDDUVO.getReceiptDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											"RcptDt", null, null); //ESPRD00357486

						}
					}
					if (caseTypeDDUVO.getDecisionDateStr() != null
							&& caseTypeDDUVO.getDecisionDateStr().length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeDDUVO
										.getDecisionDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											"descDt", null, null);//ESPRD00357486

						}
					}
					if (caseTypeDDUVO.getApprovedBeginDateStr() != null
							&& caseTypeDDUVO.getApprovedBeginDateStr().length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeDDUVO
										.getApprovedBeginDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											"appbegDt", null, null);

						}
					}

					// 					Added for defect ESPRD00343146 for BRC CON0911.0001.01
					// starts
					/*
					 * if (logCaseDataBean.isStatusClosedAndApproved() &&
					 * (caseTypeDDUVO.getCloseCodeCd() != null &&
					 * (caseTypeDDUVO.getCloseCodeCd().length() ==
					 * MaintainContactManagementUIConstants.ZERO ||
					 * caseTypeDDUVO.getCloseCodeCd().equals("CD")))) { flag =
					 * false; if (caseTypeDDUVO.getCloseCodeCd().length() ==
					 * MaintainContactManagementUIConstants.ZERO) {
					 * ContactManagementHelper .setErrorMessage(
					 * MaintainContactManagementUIConstants.CLOSE_CODE_REQ,
					 * "closeCode", null, null); } else {
					 * ContactManagementHelper .setErrorMessage(
					 * MaintainContactManagementUIConstants.CASE_CLOSE_CODE_INVALID,
					 * "closeCode", null, null); }
					 *  }
					 */

					if (logCaseDataBean.isStatusClosedAndApproved()
							&& (caseTypeDDUVO.getCloseCodeCd() == null || caseTypeDDUVO
									.getCloseCodeCd().equals("CD"))) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.CASE_CLOSE_CODE_INVALID,
										"closeCode", null, null);
					}
					// 					Added for defect ESPRD00343146 for BRC CON0911.0001.01
					// ends
					if (caseTypeDDUVO.getAppealDecisionDateStr() != null
							&& caseTypeDDUVO.getAppealDecisionDateStr()
									.length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeDDUVO
										.getAppealDecisionDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											"appldesDt", null, null);//ESPRD00357486

						}
					}
				}

			}

			if (logCaseDataBean.isShowBCCPTypeScreen()) {
				CaseTypeBCCPVO caseTypeBCCPVO = detailsVO.getCaseTypeBCCPVO();
				if (caseTypeBCCPVO != null) {
					if (caseTypeBCCPVO.getApplicationDateStr() != null
							&& caseTypeBCCPVO.getApplicationDateStr().length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeBCCPVO
										.getApplicationDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											//TO Fix ESPRD00689993
											//MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											MaintainContactManagementUIConstants.FROM_DATE_VAL,
											"appDt", null, null);//ESPRD00359865

						}

					}
					if (caseTypeBCCPVO.getEnrollmentDateStr() != null
							&& caseTypeBCCPVO.getEnrollmentDateStr().length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeBCCPVO
										.getEnrollmentDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											//TO Fix ESPRD00689993
											//MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											MaintainContactManagementUIConstants.FROM_DATE_VAL,
											"enrollDt", null, null);//ESPRD00359865

						}

					}
					if (caseTypeBCCPVO.getBiopsyDateStr() != null
							&& caseTypeBCCPVO.getBiopsyDateStr().length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeBCCPVO.getBiopsyDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											//TO Fix ESPRD00689993
											//MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											MaintainContactManagementUIConstants.FROM_DATE_VAL,
											"biopsyDt", null, null);//ESPRD00359865

						}

					}
					if (caseTypeBCCPVO.getConsultDateStr() != null
							&& caseTypeBCCPVO.getConsultDateStr().length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeBCCPVO
										.getConsultDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											//TO Fix ESPRD00689993
											//MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
											MaintainContactManagementUIConstants.FROM_DATE_VAL,
											"consultDt", null, null);//ESPRD00359865

						}

					}
					if (caseTypeBCCPVO.getTreatmentStartDateStr() != null
							&& caseTypeBCCPVO.getTreatmentStartDateStr()
									.length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeBCCPVO
										.getTreatmentStartDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											//ESPRD00690063
											// MaintainContactManagementUIConstants.CASE_TREATMENT_DATE_INVALID,
											MaintainContactManagementUIConstants.FROM_DATE_VAL,
											"trtmtstrtDt", null, null);
						}

					}
					if (caseTypeBCCPVO.getChemoProjectedEndDateStr() != null
							&& caseTypeBCCPVO.getChemoProjectedEndDateStr()
									.length() > MaintainContactManagementUIConstants.ZERO) {

						if (!EnterpriseCommonValidator
								.validateDate(caseTypeBCCPVO
										.getChemoProjectedEndDateStr())) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											//ESPRD00690063
											// MaintainContactManagementUIConstants.CASE_CHEMOPROJECT_DATE_INVALID,
											MaintainContactManagementUIConstants.FROM_DATE_VAL,
											"chmoendDt", null, null);
						}

					}
					if (caseTypeBCCPVO.getTumorSizeNum() != null
							&& caseTypeBCCPVO.getTumorSizeNum().length() > MaintainContactManagementUIConstants.ZERO) {
						String tumorSize = caseTypeBCCPVO.getTumorSizeNum();
						if (!(EnterpriseCommonValidator
								.validateNumeric(tumorSize) || EnterpriseCommonValidator
								.validateFloatValue(tumorSize))) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.ERROR_INVALID_NUMBER,
											"tumorSize", null, null);
						}

					}
					// Added for defect ESPRD00699947
					if (caseTypeBCCPVO.getNodesPositiveNum() != null
							&& caseTypeBCCPVO.getNodesPositiveNum().length() > MaintainContactManagementUIConstants.ZERO) {
						log.debug("INSIDE IF STMT :::: "
								+ caseTypeBCCPVO.getNodesPositiveNum());
						String nodePosNum = caseTypeBCCPVO
								.getNodesPositiveNum();
						if (!(EnterpriseCommonValidator
								.validateNumeric(nodePosNum) || EnterpriseCommonValidator
								.validateFloatValue(nodePosNum))) {
							flag = false;
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.ERROR_MSG_FOR_NODES_POSITIVE,
											"nodesPositive", null, null);
						}
					}
					// Ended here defect ESPRD00699947
				}
			}

			log.debug("Selected caseType BU is $$ "
					+ detailsVO.getCaseTypeBusinessUnitTypeCode());
			if ("D".equals(detailsVO.getCaseTypeBusinessUnitTypeCode())
					&& "C".equals(detailsVO.getStatus())) {
				log.debug("Case Type is DDU and status is Close");
				List eventsVoList = logCaseDataBean.getCaseEventsVOList();
				if (helper.isDisposotionDateIsSetToEvents(eventsVoList)) {
					flag = false;
					ContactManagementHelper
							.setMessage(MaintainContactManagementUIConstants.EVENTDISPOSITION_REQUIRED);
				}
			}

			//ESPRD00361734

			if (detailsVO.getCaseTypeAcuityRateSettingVO() != null) {
				if (!validateDateFormat(detailsVO
						.getCaseTypeAcuityRateSettingVO().getStateFiscYearEnd())) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.STATE_FISCAL_YEAR_DATE_INVALID,
									null, null, null);
				}
			}

			//Infinite Added for Defect ESPRD00329300

			if (detailsVO.getCaseTypeARSVO() != null) {
				if (detailsVO.getCaseTypeARSVO().getFieldAuditDateStr() != null) {
					if (!validateDateFormat(detailsVO.getCaseTypeARSVO()
							.getFieldAuditDateStr())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.FIELD_AUDIT_DATE_INVALID,
										null, null, null);

					}
				}

				if (detailsVO.getCaseTypeARSVO().getStateFiscalYearEndDate() != null) {
					if (!validateDateFormat(detailsVO.getCaseTypeARSVO()
							.getStateFiscalYearEndDate())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.STATE_FISCAL_YEAR_DATE_INVALID,
										null, null, null);

					}
				}

				if (detailsVO.getCaseTypeARSVO().getRateSettingDateStr() != null) {
					if (!validateDateFormat(detailsVO.getCaseTypeARSVO()
							.getRateSettingDateStr())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.RATE_SETTING_DATE_INVALID,
										null, null, null);
					}
				}

				//  Infinite Added for Defect ESPRD00333433
				if (detailsVO.getCaseTypeARSVO().getDeskReviewStartDate() != null) {
					if (!validateDateFormat(detailsVO.getCaseTypeARSVO()
							.getDeskReviewStartDate())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.DESK_REVIEW_START_DATE_INVALID,
										null, null, null);
					}

				}
			}

			if (detailsVO.getCaseTypeMemberAppealVO() != null) {
				if (detailsVO.getCaseTypeMemberAppealVO().getFileSupDate() != null) {
					if (!validateDateFormat(detailsVO
							.getCaseTypeMemberAppealVO().getFileSupDate())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.FILE_SUPERVISER_DATE_INVALID,
										null, null, null);
					}
				}
			}

			if (detailsVO.getCaseTypeNewARSFieldVO() != null) {
				if (detailsVO.getCaseTypeNewARSFieldVO()
						.getFacilityContactName() != null) {
					if (!EnterpriseCommonValidator.validateAlpha(detailsVO
							.getCaseTypeNewARSFieldVO()
							.getFacilityContactName())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.FILE_SUPERVISER_DATE_INVALID,
										"facCntNm1", null, null);
					}
				}

				if (detailsVO.getCaseTypeNewARSFieldVO()
						.getFacilityContactPhone() != null) {
					if (!EnterpriseCommonValidator.validateNumeric(detailsVO
							.getCaseTypeNewARSFieldVO()
							.getFacilityContactPhone())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.FILE_SUPERVISER_DATE_INVALID,
										"facCntPh1", null, null);
					}
				}
				if (detailsVO.getCaseTypeNewARSFieldVO().getStateFiscalYrEnd() != null) {
					if (!validateDateFormat(detailsVO
							.getCaseTypeNewARSFieldVO().getStateFiscalYrEnd())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.FILE_SUPERVISER_DATE_INVALID,
										"statefiscal1", null, null);
					}
				}

				if (detailsVO.getCaseTypeNewARSFieldVO()
						.getFacilityFiscalYrEnd() != null) {
					if (!validateDateFormat(detailsVO
							.getCaseTypeNewARSFieldVO()
							.getFacilityFiscalYrEnd())) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.FILE_SUPERVISER_DATE_INVALID,
										"facilityFiscal1", null, null);
					}
				}

			}

			// coed Ended for Defect ESPRD00329300

			/**
			 * Added By Madhurima
			 */
			LogCaseValidationHelper logCaseValidationHelper = new LogCaseValidationHelper();

			/* Added For the Defect ID ESPRD00359040 */

			if ("B".equals(detailsVO.getCaseTypeBusinessUnitTypeCode())) {
				boolean bccpFlag = false;
				if (detailsVO.getCaseTypeBCCPVO() != null) {
					if (detailsVO.getCaseTypeBCCPVO().getBccpID() != null) {
						if (!ContactManagementHelper.validateNumeric(detailsVO
								.getCaseTypeBCCPVO().getBccpID())) {
							bccpFlag = true;
						}
					}
				}
				String componentId = "bccpID";
				if (bccpFlag) {

					ContactManagementHelper
							.setErrorMessage1(
									"Please enter ID in the correct format. Example 123456789",
									componentId, null, null);
					flag = false;
				}
			}

			/* Ends For the Defect ID ESPRD00359040 */

			if ("D".equals(detailsVO.getCaseTypeBusinessUnitTypeCode())
					&& logCaseDataBean.isStatusClosedAppealUpheld()
					&& !(detailsVO.getCaseTypeDDUVO().getDenialReasonCd() != null && detailsVO
							.getCaseTypeDDUVO().getDenialReasonCd().trim()
							.length() > 0)) {
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_REQUIRED,
						new Object[] { ContactManagementConstants.DENIAL_REASON },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						"denialReason");
				flag = false;
			}

			if ("D".equals(detailsVO.getCaseTypeBusinessUnitTypeCode())
					&& logCaseDataBean.isStatusClosedAndApproved()
					&& !(detailsVO.getCaseTypeDDUVO().getApprovedBeginDateStr() != null && detailsVO
							.getCaseTypeDDUVO().getApprovedBeginDateStr()
							.trim().length() > 0)) {
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_REQUIRED,
						new Object[] { ContactManagementConstants.APP_BEG_DATE },
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						"appbegDt");
				flag = false;
			}
			//Added for the defect ESPRD00898731
			if ("D".equals(detailsVO.getCaseTypeBusinessUnitTypeCode())
					&& logCaseDataBean.isStatusClosedAndApproved()
					&& !(detailsVO.getCaseTypeDDUVO().getCloseCodeCd() != null && detailsVO
							.getCaseTypeDDUVO().getCloseCodeCd().trim().length() > 0)) {
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.CLOSE_CODE_REQ,
						"closeCode", "closeCode", null);
				flag = false;
			}

			/** Added today by Madhurima */
			if (!logCaseDataBean.isSuperviser()) {
				boolean caseExistFlag = execute505Rule(detailsVO);

			}

			/**
			 * End-Madhurima
			 */

			CustomFieldValueVO valueVO = logCaseDataBean
					.getCustomFieldValueVO();
			if (logCaseValidationHelper.validateCustonField(valueVO)) {
				flag = false;
			}
			CaseEventsVO caseEventsVO = null;
			//In Case Record Add mode, to do portlet save on click of major
			//save after editing the associated case events for case type.
			if(!"CaseUpdate".equals(logCaseDataBean.getActionCode())){
			/** for defect ESPRD00873159
			 * if the edited case event validation failed then event List
			 * should not be validated else validation will do as follows.
			 * */
			if(logCaseDataBean.isShowEditCaseEvents()){
				saveCaseEvents();
				if(logCaseDataBean.isShowEditCaseEvents()){
				flag = false;
				}
			}
			

			//to validate case events already in datatable
			int caseEventRowIndex = -1;
			if (!logCaseDataBean.isShowEditCaseEvents() && logCaseDataBean.getCaseEventsVOList() != null
					&& logCaseDataBean.getCaseEventsVOList().size() > 0) {
				for (Iterator eventTypeItr = logCaseDataBean
						.getCaseEventsVOList().iterator(); eventTypeItr
						.hasNext();) {
					caseEventsVO = (CaseEventsVO) eventTypeItr.next();
					caseEventRowIndex++;
					if (!validateCaseEvents(caseEventsVO)) {
						logCaseDataBean.setCaseEventsIndex(Integer
								.toString(caseEventRowIndex));
						viewCaseEventsPage();
						flag = false;
						break;
					}
				}
			}
			}

			//to validate case event in add/edit mode
			//COMMENTED FOR THE DEFECT ESPRD00696701
		/*	if (logCaseDataBean.isShowAddCaseEvents()
					|| logCaseDataBean.isShowEditCaseEvents()) {
				if (!validateCaseEvents(logCaseDataBean.getCaseEventsVO())) {
					flag = false;
				}
			}*/
			//COMMENTED  END FOR THE DEFECT ESPRD00696701

			boolean isDiposDateBlank = false;
			log.debug("logCaseDataBean.getCaseDetailsVO().getStatus()===>>"
							+ logCaseDataBean.getCaseDetailsVO().getStatus());
			boolean isCaseStatusClosed = false;

			if (logCaseDataBean.getCaseDetailsVO().getStatus() != null) {
				isCaseStatusClosed = (ContactManagementConstants.BUSINESS_UNIT_DDU
						.equals(detailsVO.getCaseTypeBusinessUnitTypeCode()) && (logCaseDataBean
						.getCaseDetailsVO()
						.getStatus()
						.startsWith(
								MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_C) || logCaseDataBean
						.getCaseDetailsVO()
						.getStatus()
						.equalsIgnoreCase(
								MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_W)));
				log.debug("<<===getCaseDetailsVO().getStatus()===>>"
						+ logCaseDataBean.getCaseDetailsVO().getStatus());
			}

			if (isCaseStatusClosed
					&& logCaseDataBean.getCaseEventsVOList() != null
					&& logCaseDataBean.getCaseEventsVOList().size() > 0) {

				for (Iterator eventTypeItr = logCaseDataBean
						.getCaseEventsVOList().iterator(); eventTypeItr
						.hasNext();) {
					caseEventsVO = (CaseEventsVO) eventTypeItr.next();
					if (!(caseEventsVO.getDispositionDateStr() != null && caseEventsVO
							.getDispositionDateStr().trim().length() > 0)) {
						flag = false;
						isDiposDateBlank = true;

						break;
					}
				}

			}
			if (isCaseStatusClosed) {

				if (logCaseDataBean.isShowAddCaseEvents()
						|| logCaseDataBean.isShowEditCaseEvents()) {

					if (!(logCaseDataBean.getCaseEventsVO()
							.getDispositionDateStr() != null && logCaseDataBean
							.getCaseEventsVO().getDispositionDateStr().trim()
							.length() > 0)) {
						flag = false;
						isDiposDateBlank = true;

					}
				}
			}

			if (isDiposDateBlank) {
				log
						.debug("CASE cannot be Closed. Disposition Date is required for Case Events  ");
				setErrorMessage(
						ContactManagementConstants.DDU_EVENT_CLOSE,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						"");
				logCaseDataBean.setShowCaseMessage(true);
				logCaseDataBean.setAllDDUCaseEventesAreClosed(false);//ESPRD00362595
			}//ESPRD00362595
			else {
				logCaseDataBean.setAllDDUCaseEventesAreClosed(true);
			}//EOF ESPRD00362595

			//implentation of BR4254 CON0169.0001.01 for defect :ESPRD00343145
			boolean isDDURecExisted = false;
			//			ESPRD00357939
			boolean isCaseRecordNumExist = (logCaseDataBean
					.getCaseRegardingVO() != null
					&& logCaseDataBean.getCaseRegardingVO()
							.getCaseRecordNumber() != null && !logCaseDataBean
					.getCaseRegardingVO().getCaseRecordNumber().trim().equals(
							MaintainContactManagementUIConstants.EMPTY_STRING));
			//EOF ESPRD00357939
			if (!logCaseDataBean.isSuperviser()
					&& isCaseRecordNumExist == false) {//ESPRD00357939
				if (ContactManagementConstants.ENTITY_TYPE_MEM
						.equalsIgnoreCase(logCaseDataBean.getEntityType())) {

					if (logCaseDataBean.getExistingCaseRecordsList() != null
							&& !logCaseDataBean.getExistingCaseRecordsList()
									.isEmpty()) {
						AssociatedCaseVO assoCase = null;
						for (Iterator itr = logCaseDataBean
								.getExistingCaseRecordsList().iterator(); itr
								.hasNext();) {
							assoCase = (AssociatedCaseVO) itr.next();

							if (ContactManagementConstants.BUSINESS_UNIT_DDU
									.equals(assoCase
											.getBusinessUnitCaseTypeCode())) {
								if (assoCase.getStatus() != null
										&& !(assoCase
												.getStatus()
												.startsWith(
														MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_C) || assoCase
												.getStatus()
												.equalsIgnoreCase(
														MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_W))) {
									flag = false;
									isDDURecExisted = true;
									break;
								}
							}

						}
					}
				}
			}
			if (isDDURecExisted) {

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.CASE_DDU_OPEN_ALREADY_EXISTED,
								null, null, null);
				logCaseDataBean.setDduExistedRecordErrorMsg(false); // Added for the defect id ESPRD00710222_12OCT2011
			}
			//EOF defect ESPRD00343145
			//	implemented for defect ESPRD00357971 // BR CON0088.0001.01
			// implementation
			// starts.//CASE_CANNOT_CLOSE_DUE_TO_OUT_STATNDING_LETTER
			try {
				boolean iscaseStatusClosed = (logCaseDataBean
						.getCaseDetailsVO()
						.getStatus()
						.startsWith(
								MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_C) || logCaseDataBean
						.getCaseDetailsVO()
						.getStatus()
						.equalsIgnoreCase(
								MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_W));

				if (iscaseStatusClosed) {
					log
							.debug("Case status is closed, Validation starts to find out-standing letters");
					List lettersList = getLettersAndResponsesDataBeanFromContext()
							.getLetterGenerationRequests();

					Iterator letrsItr = lettersList.iterator();

					LetterGenerationInputVO letterGenerationInputVO = null;

					while (letrsItr.hasNext()) {

						letterGenerationInputVO = (LetterGenerationInputVO) letrsItr
								.next();

						if (letterGenerationInputVO.getStatusDesc() != null) {

							boolean isApproved = (letterGenerationInputVO
									.getStatusDesc()
									.equals(
											MaintainContactManagementUIConstants.CASE_LETTERS_AND_RESPONSE_STATUS_APPROVED_VALID_CD) || letterGenerationInputVO
									.getStatusDesc()
									.equals(
											MaintainContactManagementUIConstants.CASE_LETTERS_AND_RESPONSE_STATUS_APPROVED_SHORT_DISC));

							boolean isInReview = (letterGenerationInputVO
									.getStatusDesc()
									.equals(
											MaintainContactManagementUIConstants.CASE_LETTERS_AND_RESPONSE_STATUS_IN_REVIEW_VALID_CD) || letterGenerationInputVO
									.getStatusDesc()
									.equals(
											MaintainContactManagementUIConstants.CASE_LETTERS_AND_RESPONSE_STATUS_IN_REVIEW_SHORT_DISC));

							boolean isNew = (letterGenerationInputVO
									.getStatusDesc()
									.equals(
											MaintainContactManagementUIConstants.CASE_LETTERS_AND_RESPONSE_STATUS_NEW_VALID_CD) || letterGenerationInputVO
									.getStatusDesc()
									.equals(
											MaintainContactManagementUIConstants.CASE_LETTERS_AND_RESPONSE_STATUS_NEW_SHORT_DISC));

							if (isApproved || isInReview || isNew) {
								flag = false;
								//modified for defect ESPRD00382973
								//ContactManagementHelper.setErrorMessage(MaintainContactManagementUIConstants.CASE_CANNOT_CLOSE_DUE_TO_NON_SUBMITTED_LETTER,null,null,null);
								//EOF ESPRD00382973
								// Added for the defects ESPRD00377773 &
								// ESPRD00430055
								ContactManagementHelper
										.setErrorMessage(
												MaintainContactManagementUIConstants.CASE_CANNOT_CLOSE_DUE_TO_OUT_STATNDING_LETTER,
												"status", null, null);
								// Ends
								break;
							}

						}

					}
				}

			} catch (Exception e) {
				log.error("Exception while LettersAndResopnses validation"
						+ e.getMessage());
			}
			//EOF BR CON0088.0001.01 for defect ESPRD00357971

		}
		/* Defect ESPRD00358904 Starts */
		AppealDelegate appealDelegate = new AppealDelegate();
		AppealTracking appealDO = null;

		//Defect ESPRD00362392 Code Starts by Infinite.....
		if (logCaseDataBean.getCaseDetailsVO().getCaseType() != null
				&& logCaseDataBean.isShowMemAppTypeScreen()) {
			if (logCaseDataBean.getCaseDetailsVO().getStatus() != null
					&& (logCaseDataBean
							.getCaseDetailsVO()
							.getStatus()
							.startsWith(
									MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_C) || logCaseDataBean
							.getCaseDetailsVO()
							.getStatus()
							.equalsIgnoreCase(
									MaintainContactManagementUIConstants.CASE_STATUS_CANCEL_CODE_W))) {
				try {

					String caseId = logCaseDataBean.getCaseRegardingVO()
							.getCaseRecordNumber();
					log.debug("caseId" + caseId);
					if (caseId != null && caseId.trim().length() > 0) {
						appealDO = appealDelegate.getAppealDetails(new Long(
								caseId));

						if (appealDO != null) {
							Set administrativeHearingSet = appealDO
									.getAdminHearings();
							boolean hearingCloseStatusFlag = true;
							if (administrativeHearingSet != null
									&& administrativeHearingSet.size() == 0) {
								hearingCloseStatusFlag = false;
								log.debug("administrativeHearingSet"
										+ administrativeHearingSet.size());
							}
							for (Iterator iter = administrativeHearingSet
									.iterator(); iter.hasNext();) {
								AdministrativeHearing adminHearing = (AdministrativeHearing) iter
										.next();
								log.debug("adminHearing.getHearStatusCode()"
										+ adminHearing.getHearStatusCode());
								if (adminHearing.getHearStatusCode() == null
										|| (adminHearing.getHearStatusCode() != null && adminHearing
												.getHearStatusCode()
												.equalsIgnoreCase("O"))) {
									hearingCloseStatusFlag = false;
								}

							}
							log.debug("hearingCloseStatusFlag"
									+ hearingCloseStatusFlag);
							log.debug("caseAppealStatusCode:"
									+ appealDO.getCaseAppealStatusCode());
							if (hearingCloseStatusFlag
									&& (appealDO.getCaseAppealStatusCode() != null && appealDO
											.getCaseAppealStatusCode()
											.equalsIgnoreCase("C"))) {
								flag = true;
							} else {

								ContactManagementHelper
										.setErrorMessage(MaintainContactManagementUIConstants.APPEALSTATCODE_HIRINGSTATCODE_CLOSE);
								flag = false;

							}
						} else {

							ContactManagementHelper
									.setErrorMessage(MaintainContactManagementUIConstants.APPEALSTATCODE_HIRINGSTATCODE_CLOSE);
							flag = false;

						}
					} else {

						ContactManagementHelper
								.setErrorMessage(MaintainContactManagementUIConstants.APPEALSTATCODE_HIRINGSTATCODE_CLOSE);
						flag = false;

					}
					/* Defect ESPRD00358904 End */

				} catch (AppealFetchBusinessException e) {
					log.error("Error while validating adming hearing" + e);
				} catch (NumberFormatException e) {
					log.error("Error while validating adming hearing" + e);
				}

			}
		}
		// Begin - Added this code for the defect id ESPRD00712855_19OCT2011
		if(flag == false)
		{
			logCaseDataBean.setShowCaseDetailsMessages(true);
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
		}else{
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		}
		// End - Added this code for the defect id ESPRD00712855_19OCT2011

		//      Defect ESPRD00362392 Code Ends by Infinite.....

		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "validateCase");
		/*
		 * if (logCaseDataBean.getCaseDetailsVO().getStatus() != null
		 *  && logCaseDataBean.getCaseDetailsVO().getStatus().equals("C")) {
		 * FacesContext fc = FacesContext.getCurrentInstance();
		 * LettersAndResponsesDataBean lettersAndResponsesDataBean =
		 * (LettersAndResponsesDataBean)fc.getApplication()
		 * .createValueBinding(ContactManagementConstants.BINDING_BEGIN_SEPARATOR +
		 * "LettersAndResponsesDataBean" +
		 * ContactManagementConstants.BINDING_END_SEPARATOR) .getValue(fc);
		 * 
		 * List letterGenerationRequestsList =
		 * lettersAndResponsesDataBean.getLetterGenerationRequests();
		 * LetterGenerationInputVO letterGenerationInputVO = null;
		 * if(letterGenerationRequestsList != null &&
		 * letterGenerationRequestsList.size() > 0) { Iterator itr =
		 * letterGenerationRequestsList.iterator(); while(itr.hasNext()) {
		 * letterGenerationInputVO = (LetterGenerationInputVO)itr.next();
		 * if(letterGenerationInputVO != null) { String ltrReqDispCD =
		 * letterGenerationInputVO.getLtrReqDispCD();
		 * if(GlobalLetterConstants.LTR_REQ_STATUS_CODE_NEW.equals(ltrReqDispCD) ||
		 * GlobalLetterConstants.LTR_REQ_STATUS_CODE_INREVIEW.equals(ltrReqDispCD) ||
		 * GlobalLetterConstants.LTR_REQ_STATUS_CODE_APPROVED.equals(ltrReqDispCD)) {
		 * 
		 * ContactManagementHelper .setErrorMessage(
		 * MaintainContactManagementUIConstants.CASE_CANNOT_CLOSE_DUE_TO_OUT_STATNDING_LETTER,
		 * "status", null, null); flag = false;
		 * log.debug("flag:::::::AG:::;"+ flag);
		 * setErrorMessage(EnterpriseMessageConstants.ERR_CANT_CLOSE_CR, new
		 * Object[] { ContactManagementConstants.STATUS },
		 * MessageUtil.ENTMESSAGES_BUNDLE, "status"); break;
		 * 
		 * 
		 *  }
		 *  } } } }
		 */
		return flag;
	}

	/**
	 * Added by Madhurima
	 * 
	 * @param detailsVO
	 * @return
	 */
	private boolean execute505Rule(CaseDetailsVO detailsVO) {
		log.debug("Inside execute505Rule");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseDelegate caseDelegate = new CaseDelegate();
		CaseRegardingMemberVO regardingMemberVO = logCaseDataBean
				.getCaseRegardingVO().getCaseRegardingMemberVO();
		boolean execFlag = true;
		if ("D".equalsIgnoreCase(detailsVO.getCaseTypeBusinessUnitTypeCode())
				&& "MID".equals(regardingMemberVO.getMemberIDType())
				&& "M".equals(regardingMemberVO.getEntityType())) {

			String shortDesc = ContactManagementHelper.setShortDescription(
					FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_CASE_STAT_CD, detailsVO
							.getStatus());
			if (!shortDesc.startsWith("C")) {
				List listOfCases = null;
				try {
					log.debug("In try BLock execute505Rule and CE SK $$ "
							+ regardingMemberVO.getCommonEntitySK());
					listOfCases = caseDelegate.getCases(Long
							.valueOf(regardingMemberVO.getCommonEntitySK()));
					if (listOfCases != null && !listOfCases.isEmpty()) {
						log.debug("listOfCases size is :" + listOfCases.size());
						execFlag = false;
					}
				} catch (Exception e) {
					log.debug("Inside CatchBlock");
					e.printStackTrace();
				}
			}
		}
		return execFlag;
	}

	/**
	 * This method gets the attachments.
	 * 
	 * @param attchList
	 *            Takes List of attachments.
	 * @param record
	 *            Takes CaseRecord
	 * @return Set
	 */
	private Set getAttachments(List attchList, CaseRecord record) {
		log.debug(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAttachments");

		Set attachmentDOSet = new HashSet();

		AttachmentVO attachmentVO = null;

		int attachSize = attchList.size();

		String uploadFilePath = null;

		String finalPathToWPS = null;

		CaseAttachCrossReference caseAttachCrossReference = null;

		LogCaseVOToDomainConverter logCaseVOToDomainConverter = new LogCaseVOToDomainConverter();

		//        ConfigurationLoader cl = ConfigurationLoader.getInstance();

		//        Properties props = cl.getAttachmentConfigProperties();

		//        log.debug("The Properties in fileupload is " + props);

		//        String osName = System.getProperty("os.name");

		//        if (osName.startsWith("Windows"))

		//        {

		//            uploadFilePath = props

		//                    .getProperty(MaintainContactManagementUIConstants.WINDOWS_PATH);

		//        }

		//        else

		//        {

		//            uploadFilePath = props

		//                    .getProperty(MaintainContactManagementUIConstants.UNIX_PATH);

		//        }

		//        for (int i = 0; i < attachSize; i++)

		//        {

		//            attachmentVO = (AttachmentVO) attchList.get(i);

		//            if (attachmentVO.getFile1() != null

		//                    && !attachmentVO.getFile1().equals(""))

		//            {

		//                      log.debug("Upload path is $$ " + uploadFilePath

		//                        + attachmentVO.getAddedBy());

		//               

		//                finalPathToWPS = uploadFilePath + attachmentVO.getFileName();

		//                log.debug("The final path to WPS is $$ " + finalPathToWPS);

		//                attachmentVO.setFinalPath(finalPathToWPS);

		//                File file1 = new File(finalPathToWPS);

		//                try

		//                {

		//                    byte[] byteArray = attachmentVO.getFile1();

		//                    FileOutputStream fs = new FileOutputStream(file1);

		//                    fs.write(byteArray);

		//                }

		//                catch (FileNotFoundException e)

		//                {

		//                    e.printStackTrace();

		//                }

		//                catch (IOException e)

		//                {

		//                    e.printStackTrace();

		//                }

		//            }

		//        }

		for (int i = 0; i < attachSize; i++)

		{

			attachmentVO = (AttachmentVO) attchList.get(i);

			caseAttachCrossReference = logCaseVOToDomainConverter

			.convertCaseAttachmentVOToDO(attachmentVO, record);

			attachmentDOSet.add(caseAttachCrossReference);

		}

		log.debug(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getAttachments");
		return attachmentDOSet;
	}

	/**
	 * This method is used to add Note Set to the LogCase Domain object.
	 * 
	 * @param caseRecord :
	 *            caseRecord object
	 */
	public void addNoteSet(CaseRecord caseRecord) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "addNoteSet");
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();

		NoteSet noteSet = null;
		try {
			noteSet = commonEntityValidator.getNoteSetDO();
			if (noteSet != null) {
				log.debug("After getting Noted DO @ addNoteSet()"
						+ noteSet.getNotes().size());
				log.debug("Final NoteSet is not null");
				noteSet.setNoteSourceName("G_CASE_TB");
				caseRecord.setNoteSet(noteSet);
			}
		} catch (CommonEntityUIException commonEntityUIException) {
			log.error(commonEntityUIException.getMessage(),
					commonEntityUIException);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "addNoteSet");
	}

	/**
	 * This method is used to populate NoteSet.
	 * 
	 * @param caseRecord :
	 *            caseRecord domain object
	 */
	public void populateNoteSet(CaseRecord caseRecord) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "populateNoteSet");
		CommonEntityDataBean commonEntityDataBean = ContactHelper
		.getCommonEntityDataBean();
		NoteSet noteSet = caseRecord.getNoteSet();
		commonEntityDataBean.setNoteSet(noteSet);
		NoteSetVO noteSetVO = new NoteSetVO();
		ContactHelper contactHelper = new ContactHelper();
		//for defect ESPRD00834054.
		try {
			if (noteSet != null) {
				noteSetVO = contactHelper.convertNoteSetDomainToVO(noteSet);
				if (commonEntityDataBean != null) {
					commonEntityDataBean.setNoteList(noteSetVO.getNotesList());
					// ADDED FOR THE DEFECT ESPRD00689752
					commonEntityDataBean.setTempNoteList(new ArrayList(
							noteSetVO.getNotesList()));
					// ADDED END FOR THE DEFECT ESPRD00689752
					sortListSequence(commonEntityDataBean.getNoteList());
					commonEntityDataBean.getCommonEntityVO().setNoteSetVO(
							noteSetVO);
				}
			}
			if (commonEntityDataBean.getCommonEntityVO().getNoteSetVO() == null) {
				commonEntityDataBean.getCommonEntityVO().setNoteSetVO(
						new NoteSetVO());
			}

			if (commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
					.getNotesList() != null) {
				int notesCount = commonEntityDataBean.getCommonEntityVO()
						.getNoteSetVO().getNotesList().size();
			}
			commonEntityDataBean.setNotesSaveMsg(false);

			// Added for the defect ESPRD00542028
			commonEntityDataBean.setStartRecord(1);
			commonEntityDataBean.setEndRecord(noteSetVO.getNotesList().size());
			commonEntityDataBean.setCount(noteSetVO.getNotesList().size());
		} catch (Exception exc) {
			log.error(exc.getMessage());
		}
		/*
		try {
			NoteSet noteSet = caseRecord.getNoteSet();
			NoteSetVO noteSetVO = null;
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();
			System.err.println("++++=noteSet" + noteSet);
			ContactHelper contactHelper = new ContactHelper();
			if (noteSet != null) {
				noteSetVO = contactHelper.convertNoteSetDomainToVO(noteSet);
				if (commonEntityDataBean != null)

				{
					commonEntityDataBean.getCommonEntityVO().setNoteSetVO(
							noteSetVO);
					commonEntityDataBean.setNoteList(noteSetVO.getNotesList());
					//ADDED FOR THE DEFECT ESPRD00689752
					commonEntityDataBean.setTempNoteList(new ArrayList(noteSetVO.getNotesList()));
					//ADDED END FOR THE DEFECT ESPRD00689752
					//                sortListSequence(commonEntityDataBean.getNoteList());
					commonEntityDataBean.setNotesSaveMsg(false);
				}
				if (commonEntityDataBean != null && commonEntityDataBean.getCommonEntityVO().getNoteSetVO() == null) {
					commonEntityDataBean.getCommonEntityVO().setNoteSetVO(
							new NoteSetVO());
				}
				if (commonEntityDataBean != null && commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
						.getNotesList() != null) {
					int notesCount = commonEntityDataBean.getCommonEntityVO()
							.getNoteSetVO().getNotesList().size();
					System.err.println("While getting Notes Size is "
							+ notesCount);
				}

			}
		} catch (Exception e) {
			System.err.println("error during populating notes");
			e.printStackTrace();
		}*/

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
	 * This method is used to save the Routing information
	 * 
	 * @return String
	 */
	public String saveRouting() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "saveRouting");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CMRoutingVO routingVO = logCaseDataBean.getRoutingVO();
		//Map userMap = null;
		Map deptMap = null;

		Map workUnitMap = null;
		if (validateRouting(routingVO)) {
			if (logCaseDataBean.isShowAddRoutingAssignment()) {
				if ((routingVO != null
						&& routingVO.getAssignThisRecordTo() != null && !routingVO
						.getAssignThisRecordTo().equals(""))
						|| (routingVO.getDeptName() != null && !routingVO
								.getDeptName().equals(""))) {
					log.debug("Routing Created by User is $$ "
							+ routingVO.getCreatedBy());

					log.debug("Show value at SaveRouting is $$ "
							+ routingVO.getShow());
					/*routingVO
							.setRoutedByName(routingVO.getAssignRoutedToName());*/
					//routingVO.setRoutedByName(getUserID()); // Added for the defect id ESPRD00702153_25NOV2011
					// Begin - Modified the code for the defect id ESPRD00702153_30NOV2011
					String userId = logCaseDataBean.getLoggedInUserID();
					try {
						ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
						WorkUnit userWorkUnit = contactMaintenanceDelegate.getUserWorkUnit(userId);
						String userNamebyId = getName(userWorkUnit, "U");
						routingVO.setRoutedByName(userNamebyId);
						log.debug("===userName ::: "+userNamebyId);
					}
					catch(LOBHierarchyFetchBusinessException e)
					{
						e.printStackTrace();
					}
					
					//routingVO.setRoutedByName(logCaseDataBean.getLoggedInUserID()); // Added for the performance fix
					routingVO.setRoutedByUserID(logCaseDataBean.getLoggedInUserID()); // Added for the performance fix
					//routingVO.setRoutedBySK(routingVO.getAssignedTo());
					//Modified for GAI Recursive Call_Fix
					routingVO.setRoutedBySK(logCaseDataBean.getLoggedInUserSK().toString()); // Modified for the defect id ESPRD00702153_30NOV2011 
					// End - Modified the code for the defect id ESPRD00702153_30NOV2011
					
					// Added for defect ID ESPRD00330104

					if (routingVO.getShow().equalsIgnoreCase("B")) {
						routingVO.setRoutedBy("Business Unit");
						String userOrWrkUnitName = ""; // Added the attribute for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
					
						// Begin - Commented unused code for the defect id ESPRD00702153_30NOV2011
						/*if (routingVO.getAssignThisRecordTo() != null
								&& !routingVO.getAssignThisRecordTo()
										.equals("")) {

							System.out
									.println("  routingVO.getAssignThisRecordTo() "
											+ routingVO.getAssignThisRecordTo());
							// Iterator wrkUntItr =
							// logCaseDataBean.getBusinessUnitList().iterator();
							System.out
									.println(" logCaseDataBean.getRoutingVO().getAssignedTo() : "
											+ logCaseDataBean.getRoutingVO()
													.getAssignedTo());*/
						// End - Commented unused code for the defect id ESPRD00702153_30NOV2011

							//routingVO.setRoutedToName(logCaseDataBean.getRoutingVO().getAssignedTo());

							// Begin - Commented for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
							/*userMap = logCaseDataBean.getUserMap();							
							if (!userMap.isEmpty()) {
								String userName = (String) userMap
										.get(routingVO.getAssignThisRecordTo());
								log.debug("  userMap userName: "
										+ userName);
								routingVO.setRoutedToName(userName);
								routingVO.setAssignedUserName(userName);
								logCaseDataBean.getCaseDetailsVO()
										.setAssignedTo(userName);

							}*/
							// End - Commented for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
							
							// Begin - Added for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
							if (routingVO.getAssignThisRecordTo() != null 
									&& !routingVO.getAssignThisRecordTo().trim().equals("")) {
								
								userOrWrkUnitName = getDescriptionFromVV(routingVO.getAssignThisRecordTo(),logCaseDataBean.getRoutingUserList());
								routingVO.setRoutedToName(userOrWrkUnitName);								
								routingVO.setAssignedUserName(userOrWrkUnitName);
								logCaseDataBean.getCaseDetailsVO()
										.setAssignedTo(userOrWrkUnitName);

							//} // Commented the code ESPRD00702153_30NOV2011
							// End - Added for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
							
							/*
							 * SelectItem sItem=null;
							 * while(wrkUntItr.hasNext()){
							 * sItem=(SelectItem)wrkUntItr.next();
							 * if(sItem.getValue().equals(routingVO.getAssignThisRecordTo())){
							 * 
							 * routingVO.setAssignedUserName(sItem.getLabel());
							 * break; } } }
							 */
						} else if (routingVO.getDeptName() != null
								&& !routingVO.getDeptName().trim().equals("")) {
							// Begin - Modified for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
							userOrWrkUnitName = getDescriptionFromVV(routingVO.getDeptName(),logCaseDataBean.getWorkUnitList());
							routingVO.setRoutedToName(userOrWrkUnitName);								
							routingVO.setAssignedUserName(userOrWrkUnitName);
							logCaseDataBean.getCaseDetailsVO()
									.setAssignedTo(userOrWrkUnitName);
							/*workUnitMap = logCaseDataBean.getWorkUnitSKMap();
							if (!workUnitMap.isEmpty()) {
								String deptName = (String) workUnitMap
										.get(new Long(routingVO.getDeptName()));
								routingVO.setRoutedToName(deptName);
								routingVO.setAssignedUserName(deptName);
								logCaseDataBean.getCaseDetailsVO()
										.setAssignedTo(deptName);
							}*/
							// End - Modified for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011							
						}
						logCaseDataBean.setShowUserDept(false);
						logCaseDataBean.setShowWorkUnit(false);
					}
					if (routingVO.getShow().equalsIgnoreCase("U")) {
						routingVO.setRoutedBy("User");
						if (routingVO.getAssignThisRecordTo() != null
								&& !routingVO.getAssignThisRecordTo().trim()
										.equals("")) {

							//userMap = logCaseDataBean.getUserMap();
							//if (!userMap.isEmpty()) {
								/*String userName = (String) userMap
										.get(routingVO.getAssignThisRecordTo());*/
								//modified for removing map.
								String userName =getDescriptionFromVV(routingVO.getAssignThisRecordTo(), logCaseDataBean.getUserList());
								routingVO.setRoutedToName(userName);
								routingVO.setAssignedUserName(userName);
								//DEFECT ESPRD00553461 13JAN2011
								logCaseDataBean.getCaseDetailsVO()
										.setAssignedTo(userName);
								//END DEFECT ESPRD00553461 13JAN2011

							//}
							deptMap = logCaseDataBean.getDeptMap();
							if (!deptMap.isEmpty()) {
								String userDeptName = (String) deptMap
										.get(routingVO.getUserDepartment());
								routingVO.setUserDeptName(userDeptName);
							}
						}
						logCaseDataBean.setShowUserDept(true);
						logCaseDataBean.setShowWorkUnit(false);
					}
					if (routingVO.getShow().equalsIgnoreCase("W")) {
						routingVO.setRoutedBy("Work Unit");

						workUnitMap = logCaseDataBean.getWorkUnitSKMap();
						log.debug(" workUnitMap :> " + workUnitMap);
						if (!workUnitMap.isEmpty()) {
							log.debug(" routingVO.getDeptName() :> "
									+ routingVO.getDeptName());
							String deptName = (String) workUnitMap
									.get(routingVO.getDeptName().trim());
							log.debug(" deptName 1 :> " + deptName);
							if (routingVO.getAssignThisRecordTo() == null
									|| (routingVO.getAssignThisRecordTo().trim())
											.equals("")) {

								routingVO.setRoutedToName(deptName);
								routingVO.setWorkUnit(deptName);
								routingVO.setAssignedUserName(deptName);
								logCaseDataBean.getCaseDetailsVO().setAssignedTo(
										deptName); // Added to set the AssignedTo value for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011

							} else {
								//userMap = logCaseDataBean.getUserMap();
								//if (!userMap.isEmpty()) {
									/* modified for removing map.
									 * String assignedUserName = (String) userMap
											.get(routingVO
													.getAssignThisRecordTo());*/
									String assignedUserName =getDescriptionFromVV(routingVO.getAssignThisRecordTo(), logCaseDataBean.getUserList());

									routingVO
											.setAssignedUserName(assignedUserName);
									routingVO.setRoutedToName(assignedUserName);
									routingVO.setWorkUnit(deptName);
									routingVO
											.setAssignedUserName(assignedUserName);
									logCaseDataBean.getCaseDetailsVO().setAssignedTo(
											assignedUserName); // Added to set the AssignedTo value for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
								//}
							}
							//DEFECT ESPRD00553461 13JAN2011
							/*logCaseDataBean.getCaseDetailsVO().setAssignedTo(
									deptName);*/ // Commented the AssignedTo value for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
							//END DEFECT ESPRD00553461 13JAN2011

						}
						logCaseDataBean.setShowUserDept(false);
						logCaseDataBean.setShowWorkUnit(true);
					}

					logCaseDataBean.getRoutingInfoList().add(routingVO);
					CMRoutingVO tempRoutingVO = new CMRoutingVO();
					tempRoutingVO.setCreatedBy(logCaseDataBean
							.getCaseDetailsVO().getCreatedBy());
					/*tempRoutingVO.setRoutedByWorkUntiSK(Long
							.valueOf(logCaseDataBean.getCaseDetailsVO()
									.getCreatedBySK()));*/ // Commented for the defect id ESPRD00702153_30NOV2011
					//Modified for GAI Recursive Call_Fix
					//tempRoutingVO.setRoutedByWorkUntiSK(getUserSK(logCaseDataBean.getLoggedInUserID())); // Added for the defect id ESPRD00702153_30NOV2011
					tempRoutingVO.setRoutedByWorkUntiSK(logCaseDataBean.getLoggedInUserSK());
					tempRoutingVO.setAssignedTo(logCaseDataBean
							.getCaseDetailsVO().getAssignedTo());
					
					tempRoutingVO.setReportingUnit(logCaseDataBean
							.getCaseDetailsVO().getReportingUnit());//Modified for the defect Id ESPRD00778564
					if (routingVO.getAddThisRecordToMyWatchlist()) {
						tempRoutingVO.setAddThisRecordToMyWatchlist(true);
					}
					logCaseDataBean.setRoutingVO(tempRoutingVO);

					/*ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
									null, null, null);*/
					logCaseDataBean.setShowCaseRoutingMessages(true);
					logCaseDataBean.setShowRoutingDataTable(true);
					logCaseDataBean.setRenderAddRouting(false);
				}

			} else if (logCaseDataBean.isShowEditRoutingAssignment()) {
				CMRoutingVO tempRoutVO = logCaseDataBean.getRoutingVO();
				logCaseDataBean.getCaseEventsVOList().add(
						Integer.parseInt(logCaseDataBean.getRoutingIndex()),
						tempRoutVO);
				logCaseDataBean.getRoutingInfoList()
						.remove(
								Integer.parseInt(logCaseDataBean
										.getRoutingIndex()) + 1);
				// Commented for the defect id ESPRD00735630_20DEC2011
				/*ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
						null, null, null);*/
				logCaseDataBean.setShowCaseRoutingMessages(true);
			}
			/** for defect ESPRD00844065
			 *  on minor save action routing list 
			 *  should be in desc of routingDate.
			 * */
			List listOfCaseRoutingVOs=logCaseDataBean.getRoutingInfoList();
			if (listOfCaseRoutingVOs != null
					&& !listOfCaseRoutingVOs.isEmpty()) {
				new LogCaseHelper()
						.sortCaseRoutingComparator(
								MaintainContactManagementUIConstants.CASEROUTING_DATE,
								ContactManagementConstants.EMPTY_STRING,
								listOfCaseRoutingVOs);
			}
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
			logCaseDataBean.setRenderedroutingFlag(false);
			logCaseDataBean.setSaveRoutingFlag(true);
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end */
			logCaseDataBean.setRenderEditRouting(true);
			logCaseDataBean.setRenderAddEditRouting(true);
			//ESPRD00786595
			logCaseDataBean.setShowAddRoutingAssignment(Boolean.FALSE);
			logCaseDataBean.setShowEditRoutingAssignment(Boolean.FALSE);
			logCaseDataBean.setPageFocusId("caseRoutingHeader");
		}
		//      added for defect ID ESPRD00327996
		if (logCaseDataBean.isShowCaseRoutingMessages()) {
			logCaseDataBean.setShowDepartments(false);
			logCaseDataBean.setShowUserDepartments(false);
			logCaseDataBean.setShowUsers(false);
			logCaseDataBean.setShowBusinessUnitFields(false);
		}
		logCaseDataBean.setShowCaseMessage(false); //DEFECT ESPRD00553461
												   // 14JAN2011
		logCaseDataBean.setDduExistedRecordErrorMsg(true); // Added for the defect id ESPRD00710222_12OCT2011
		logCaseDataBean.setShowCaseDetailsMessages(false); // Added for the defect id ESPRD00712855_19OCT2011
		//EOF defect ESPRD00327996
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "saveRouting");
		logCaseDataBean.setPageFocusId("caseRoutingHeader");
		logCaseDataBean.setCursorFocusValue("");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is used to validate the routing information in UI
	 * 
	 * @param routingVO
	 *            CMRoutingVO for validation
	 * @return boolean it returns false, if any of the validations are fail.
	 *         otherwise it will return true
	 */
	public boolean validateRouting(CMRoutingVO routingVO) {
		boolean flag = true;
		String action = null;
		//ESPRD00786595
		String pageFocus=null;
		String cursorFocus=null;
		ContactManagementHelper helper = new ContactManagementHelper();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (logCaseDataBean.isShowAddRoutingAssignment()) {
			action = MaintainContactManagementUIConstants.ADD;
			pageFocus="logCaseAddRouting";
			cursorFocus="show";
		} else {
			action = MaintainContactManagementUIConstants.UPDATE;
			pageFocus="logCaseEditRouting";
			cursorFocus="watchlist_routing";
		}
		log.debug("validateRouting is Started " + routingVO);
		logCaseDataBean.setShowCaseRoutingMessages(false);
		if (routingVO != null) {
			if (isNullOrEmptyField(routingVO.getShow())) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.SHOW_ROUTING_REQUIRED,
								MaintainContactManagementUIConstants.SHOW_ROUTING,
								null, action);
			} else {
				if (logCaseDataBean.isShowBusinessUnitFields()
						&& (isNullOrEmptyField(routingVO.getBusineesUnit()))) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.BUSINESS_UNIT_ROUTING_REQUIRED,
									MaintainContactManagementUIConstants.BUSINESS_UNIT_ROUTING,
									null, action);
				}
			}
			if (isNullOrEmptyField(routingVO.getRoutingDateStr())) {

				helper.getFormatedRoutingDate(routingVO, new Date());
			}
			if (logCaseDataBean.isShowDepartments()) {
				if (isNullOrEmptyField(routingVO.getDeptName())) {

					flag = false;
					ContactManagementHelper.setErrorMessage(
							MaintainContactManagementUIConstants.DEPT_REQUIRED,
							MaintainContactManagementUIConstants.ADD_DEPT,
							null, action);

				}
			}

			// Added for defect id ESPRD00299534
			if (!logCaseDataBean.isShowDepartments()) {
				log.debug("Inside assignThisRecordTo validation");
				if (isNullOrEmptyField(routingVO.getAssignThisRecordTo())) {

					flag = false;
					ContactManagementHelper.setErrorMessage(
							MaintainContactManagementUIConstants.ASSIGNEDTOREQ,
							MaintainContactManagementUIConstants.ASSIGNEDTO,
							null, action);
				}
			}
			//DEFECT ESPRD00521855 start
			if (logCaseDataBean.isShowUserDepartments()) {

				if (isNullOrEmptyField(routingVO.getUserDepartment())) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.USER_WORK_UNIT_NAME_REQUIRED,
									MaintainContactManagementUIConstants.WORKUNITNAME,
									null, action);
				}
			}
			//ESPRD00521855 ends

		}

		//      ADD FOR THE DEFECT ESPRD00601882 18 MARCH 2011
		if (flag == false) {
			validateFlag = false;
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
			//ESPRD00786595
			logCaseDataBean.setPageFocusId(pageFocus);
			logCaseDataBean.setCursorFocusValue(cursorFocus);
		}else{
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		}
		//END FOR THE DEFECT ESPRD00601882
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "validateRouting");
		return flag;
	}

	/**
	 * This method is used to render the Add page of Routing
	 * 
	 * @return String
	 */
	public String renderAddRouting() {
		
		boolean flag = true; // Added the flag for the defect id UC-PGM-CRM-018.1_ESPRD00709390_02NOV2011
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		log.debug("CMLOGCASECONTROLLER From renderAddRouting()...1");
		// Begin - Added the if conditions for the caseType and workUnit
		//			for the defect id UC-PGM-CRM-018.1_ESPRD00709390_02NOV2011
		if (isNullOrEmptyField(logCaseDataBean.getCaseDetailsVO().getWorkUnit())) {
			
			log.debug("CMLOGCASECONTROLLER From renderAddRouting()...2");
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.WORKUNIT_REQUIRED,
					"workunit", null, null);
			log.debug("CMLOGCASECONTROLLER From renderAddRouting()...3");
			flag = false;
		}
		if (isNullOrEmptyField(logCaseDataBean.getCaseDetailsVO().getCaseType())) {
				log.debug("detailsVO.getCaseType() is null");
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.CASETYPE_REQUIRED,
						"casetype", null, null);
				flag = false;
		}	
		// End - Added the condition to test the caseType and workUnit
		//			for the defect id UC-PGM-CRM-018.1_ESPRD00709390_02NOV2011
		
		if(flag) // Added the if condition to display the addRouting
				//			for the defect id UC-PGM-CRM-018.1_ESPRD00709390_02NOV2011
		{
			CMRoutingVO tempRoutingVO = new CMRoutingVO();
			log.debug("CMLOGCASECONTROLLER From renderAddRouting()...4");
			tempRoutingVO.setCreatedBy(logCaseDataBean.getCaseDetailsVO()
					.getCreatedBy());
			tempRoutingVO.setRoutedByWorkUntiSK(Long.valueOf(logCaseDataBean
					.getCaseDetailsVO().getCreatedBySK()));
			//ADDED FOR THE DEFECT ESPRD00634928
			tempRoutingVO.setAssignedTo(logCaseDataBean.getCaseDetailsVO()
					.getAssignedTo());
		
			 tempRoutingVO.setShow(MaintainContactManagementUIConstants.LOGCASE_ROUTING_SHOW_FIELD_USERS_CODE);
			//END FOR THE DEFECT ESPRD00634928
			tempRoutingVO.setReportingUnit(logCaseDataBean.getCaseDetailsVO()
					.getReportingUnit());
			logCaseDataBean.setRoutingVO(tempRoutingVO);
			logCaseDataBean.setShowAddRoutingAssignment(true);
			logCaseDataBean.setShowEditRoutingAssignment(false);
			logCaseDataBean.setShowCaseRoutingMessages(false);
			logCaseDataBean.setRenderedroutingFlag(true);
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
			logCaseDataBean.setSaveRoutingFlag(false);
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end */
			//added for defect ID ESPRD00327996
			try {
				log.debug("CMLOGCASECONTROLLER From renderAddRouting()...6");
				String caseTypesk = logCaseDataBean.getCaseDetailsVO().getCaseType();
				String assignedToWorkUnitSK = logCaseDataBean.getCaseDetailsVO().getAssignedToWorkUnitSK();
				if (!isNullOrEmptyField(caseTypesk) && !isNullOrEmptyField(assignedToWorkUnitSK))
	            {
					if(!logCaseDataBean.isShowCaseType() ){
						log.debug("CMLOGCASECONTROLLER From renderAddRouting()...7");
						//This condition differentiate Add mode
						getAllUsersSameBU(caseTypesk,assignedToWorkUnitSK,Boolean.FALSE);
					}else {
						//this flag is set in LogCaseDotoVo converter to distinguish work unit Type
						if(logCaseDataBean.isAssignedToUser()==false){
							log.debug("CMLOGCASECONTROLLER From renderAddRouting()...8");
							getAllUsersSameBU(caseTypesk,assignedToWorkUnitSK,Boolean.FALSE);
						}else{
							log.debug("CMLOGCASECONTROLLER From renderAddRouting()...9");
							getAllUsersSameBU(caseTypesk,assignedToWorkUnitSK,Boolean.TRUE);
						}
						
					}
	            }
	            else
	            {
	                log.error("Please select Case Type first");
	            }
				log.debug("CMLOGCASECONTROLLER From renderAddRouting()...10");
				setAllDepartmentsList();
			} catch (Exception e) {
				log.debug("Exception setting all departments list:"
						+ e.getMessage());
			}
			
			logCaseDataBean.setShowDepartments(false);
			logCaseDataBean.setShowUserDepartments(false);
			logCaseDataBean.setShowUsers(false);
			logCaseDataBean.setShowBusinessUnitFields(false);
			logCaseDataBean.setRenderAddRouting(true);
			logCaseDataBean.setRenderEditRouting(false);
			logCaseDataBean.setRenderAddEditRouting(false);
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
			
			//EOF defect ID ESPRD00327996
			return MaintainContactManagementUIConstants.SUCCESS;
		}
		else // Added the else condition not to display the addRouting
			//			for the defect id UC-PGM-CRM-018.1_ESPRD00709390_02NOV2011
		{
			return null;
		}
		
	}

	/**
	 * This Method is used to View the Routing details in EDIT (or) UPDATE JSP
	 * whenever the user clicks on the row in a Span in JSP.
	 * 
	 * @return String
	 */
	public String viewRoutingInfo() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "viewRoutingInfo");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		// Major Save Code Starts

		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String strIndex = (String) map.get("rowindex");
		int routingIndex = Integer.parseInt(strIndex);

		logCaseDataBean.setRoutingIndex(strIndex);
		CMRoutingVO caseRoutingVO = (CMRoutingVO) logCaseDataBean
				.getRoutingInfoList().get(routingIndex);

		logCaseDataBean.setShowCaseRoutingMessages(false);
		// Begin - Commented for the performance fix for the defect id ESPRD00702153_25NOV2011
		/*CMRoutingVO tempCroutingVO = new CMRoutingVO();
		try {
			BeanUtils.copyProperties(tempCroutingVO, caseRoutingVO);
		} catch (IllegalAccessException e) {
			log.error("Eception at viewRoutingInfo()");
		} catch (InvocationTargetException e) {
			log.error("Eception at viewRoutingInfo()");
		}*/
		// End - Commented for the performance fix for the defect id ESPRD00702153_25NOV2011

		Iterator wrkUntItr = logCaseDataBean.getWorkUnitList().iterator();
		SelectItem sItem = null;
		while (wrkUntItr.hasNext()) {
			sItem = (SelectItem) wrkUntItr.next();
		// Begin - Modified for the performance fix for the defect id ESPRD00702153_25NOV2011			
			if (sItem.getValue().equals(caseRoutingVO.getDeptName())) {
				caseRoutingVO.setWorkUnit(sItem.getLabel());
				break;
			}
		// End - Modified for the performance fix for the defect id ESPRD00702153_25NOV2011			
		}

		logCaseDataBean.setRoutingVO(caseRoutingVO); // Modified for the performance fix.
		logCaseDataBean.setShowAddRoutingAssignment(false);
		logCaseDataBean.setShowEditRoutingAssignment(true);
		logCaseDataBean.setRenderEditRouting(true);
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "viewRoutingInfo");
		//Added for the Defect ESPRD00778564
		logCaseDataBean.getRoutingVO().setCreatedBy(
				logCaseDataBean.getCaseDetailsVO().getCreatedBy());
		logCaseDataBean.getRoutingVO().setReportingUnit(
				logCaseDataBean.getCaseDetailsVO().getReportingUnit());
		//Added for the Defect ESPRD00778564
		UIComponent component = ContactManagementHelper
		.findComponentInRoot("routingAuditId");
		
		if (component != null) {

			AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
		
			auditHistoryTable.setValue(caseRoutingVO.getAuditKeyList());
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
					false);

		}
		
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to render the RESENT Case Routing page in JSP.
	 * 
	 * @return String.
	 */
	public String resetRoutingPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "resetRoutingPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setRoutingVO(new CMRoutingVO());
		//      added for defect ID ESPRD00327996
		logCaseDataBean.setShowDepartments(false);
		logCaseDataBean.setShowUserDepartments(false);
		logCaseDataBean.setShowUsers(false);
		logCaseDataBean.setShowBusinessUnitFields(false);//Modified for defect ID ESPRD00778518
		//EOF defect ID ESPRD00327996
		//Added for defect ID ESPRD00778518
		logCaseDataBean.getRoutingVO().setCreatedBy(logCaseDataBean.getCaseDetailsVO().getCreatedBy());
		logCaseDataBean.getRoutingVO().setAssignedTo(logCaseDataBean.getCaseDetailsVO().getAssignedTo());
		logCaseDataBean.getRoutingVO().setReportingUnit(logCaseDataBean.getCaseDetailsVO().getReportingUnit());
		logCaseDataBean.getRoutingVO().setShow(MaintainContactManagementUIConstants.LOGCASE_ROUTING_SHOW_FIELD_USERS_CODE);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is used to render the CANCEL the add or update page of
	 * Routing
	 * 
	 * @return String
	 */
	public String renderCancelRoutingInfo() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "renderCancelRoutingInfo");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setRenderedroutingFlag(false);
		if (logCaseDataBean.isShowAddRoutingAssignment()) {
			logCaseDataBean.setShowAddRoutingAssignment(false);
		}
		if (logCaseDataBean.isShowEditRoutingAssignment()) {
			logCaseDataBean.setShowEditRoutingAssignment(false);
		}
		logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/** ### coding for Attachments ### * */
	protected transient HtmlScriptCollector scriptCollector1;

	/** holds the HtmlForm object */
	protected transient HtmlForm form1;

	/** holds the HtmlFileUpload object */
	protected transient HtmlFileupload fileupload1;

	/**
	 * This method is used to get the HtmlScriptCollector object
	 * 
	 * @return HtmlScriptCollector it will return HtmlScriptCollector object
	 */
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) ContactManagementHelper
					.findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	/**
	 * This method is used to get the HtmlForm object
	 * 
	 * @return HtmlForm it returns the HtmlForm object
	 */
	protected HtmlForm getForm1() {
		log.debug("In Bean getForm1");
		if (form1 == null) {
			form1 = (HtmlForm) ContactManagementHelper
					.findComponentInRoot("CMlogCase");
		}
		return form1;
	}

	/**
	 * This method is used to save the Attachment information
	 * 
	 * @return String
	 */
	public String uploadFile() throws EDMSQuickSearchBusinessException {
		log.debug(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "uploadFile");

		//ESPRD00349838
		String msg = "";
		//ESPRD00349838
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		AttachmentVO attachment = logCaseDataBean.getAttachmentVO();
		//while updating attachment
		if (logCaseDataBean.isShowEditAttachments()
				&& requiredFldsAvblforEdit(attachment)) {
			AttachmentVO tempAttachVO = attachment;
			// for defect ESPRD00790505
			String attchIndexStr = logCaseDataBean.getAttachmentIndex();
			AttachmentVO attach = (AttachmentVO) logCaseDataBean
					.getAttachmentVOList().get(Integer.parseInt(attchIndexStr));
			if (!tempAttachVO.getDescription().equals(attach.getDescription())) {
				logCaseDataBean.setAttachmentAddOrUpdate(Boolean.TRUE);
			}
			logCaseDataBean.getAttachmentVOList().add(
					Integer.parseInt(logCaseDataBean.getAttachmentIndex()),
					tempAttachVO);
			logCaseDataBean.getAttachmentVOList().remove(
					Integer.parseInt(logCaseDataBean.getAttachmentIndex()) + 1);
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
					null, null, null);

			logCaseDataBean.setShowCaseAttachMessages(true);

			return ContactManagementConstants.EMPTY_STRING;
		}
		if (getFileupload1() != null && getFileupload1().getFilename() != null
				&& !getFileupload1().getFilename().equals("")
				&& getFileupload1().getFilename().length() != 0) {
			attachment.setFileName(getFileupload1().getFilename());
		}
		log.info(" upload file one..");
		if (requiredFldsAvbl(attachment)) {
			attachment.getAddedBy();
			log.debug("EdmsDocType-"+attachment.getEdmsDocType());
			log.debug("EdmsWrkUnitLevel-"+attachment.getEdmsWrkUnitLevel());
			log.debug("getAddedBy-"+getUserID());
			
			EDMSQuickSearchProcessDelegate edmsQuickSearchProcessDelegate = null;
			edmsQuickSearchProcessDelegate = new EDMSQuickSearchProcessDelegate();
			
			//Modified for the RPM_CR_ESPRD00718265 start
			String status = "";
			if (attachment.getEdmsDocType() != null
					&& attachment.getEdmsWrkUnitLevel() != null
					&& getUserID() != null) {

				status = edmsQuickSearchProcessDelegate.getEDMSPrivilegeQuery(
						getUserID(), attachment.getEdmsDocType(), attachment
								.getEdmsWrkUnitLevel());
			}
			 

			log.debug("status after delegate call = "+status);
			if (status != null) {
			if(status.equalsIgnoreCase("0")){
				log.debug("Not having Privilege");
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.SAVE_ERROR_MSG,
						null, null, null);
				logCaseDataBean.setShowCaseMessage(true);
				msg = MaintainContactManagementUIConstants.SUCCESS;
			}
			else if (status.equalsIgnoreCase("1")) {
				log.debug("User Privileged to UPLOAD Attachment");
			
				//Modified for the RPM_CR_ESPRD00718265 end
			try {
				String uploadFilePath = null;
				String description = null;

				HtmlFileupload file = null;

				if (logCaseDataBean.isShowAddAttachments()) {
					log.info("Add page Attachment");
					file = getFileupload1();

					if (file != null) {
						String fileName = file.getFilename();
						String mineType = file.getMimetype();
						ContentElement newFile = (ContentElement) file
								.getValue();
						log.info(" after file null check..");
						if (newFile != null) {
							FacesContext fct = FacesContext
									.getCurrentInstance();
							fct
									.getApplication()
									.setMessageBundle(
											"com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CommonAttachment");

							ResourceBundle bundle = ContactManagementHelper
									.resourceBundle(fct);
							//Below code has modified as part of the CR ESPRD00800460 
							/*String extension = bundle
									.getString("acceptable.attach.files");*/
							String[] totalExt = null;
							String fileExtension = null;
							if (fileName != null
									&& !(fileName.equalsIgnoreCase(""))) {
								int index = fileName.lastIndexOf(".");
								if (index != -1) {
									fileExtension = fileName.substring(
											index + 1, fileName.length());
								} else {
									fileExtension = "";
								}
							}
							System.out.println("fileExtension of user input ::"
									+ fileExtension);
							SystemListDelegate systemListDelegate = new SystemListDelegate();
							boolean validFile = true;
							 List systemList = systemListDelegate.getSystemListDtlStrtVal(
									 Long.valueOf("183") ,"G1");
							 System.out.println("syslit 183 in CMLogCaseControllerBean---->>  "+systemList);
							 
							 if(null!=systemList && !systemList.isEmpty() && systemList.contains(fileExtension))
							 {
								 validFile = false;
								 System.out.println("invaid file selecte in CMLogCaseControllerBean-->>>"+fileExtension);
							 }
							//Below code has modified as part of the CR ESPRD00800460 
							/*if ((extension != null)
									&& !(extension.equalsIgnoreCase(""))) {
								totalExt = extension.split(",");
							}

							boolean validFile = false;

							if ((totalExt != null) && (totalExt.length > 0)) {

								for (int i = 0; i < totalExt.length
										&& !(validFile); i++) {
									if (fileExtension != null
											&& !(fileExtension
													.equalsIgnoreCase(""))
											&& fileExtension
													.equalsIgnoreCase(totalExt[i])) {

										validFile = true;
									}
								}
							}*/
							log.info(" after file extension check...");
							if (validFile) {
								byte[] byteArray = newFile.getContentValue();
								/*
								 * if(byteArray == null || byteArray.length ==
								 * 0) { System.err.println("file byteArray null
								 * or Size 0"); throw new
								 * EnterpriseBaseBusinessException("err.fileattach.file.size.zero",
								 * "The attached file is null or size zero."); }
								 */
								if (byteArray != null && byteArray.length > 0) {
									int maxSizeInMB = 0;
									String maxSize = bundle
											.getString("acceptable.attach.file.size");

									if ((maxSize != null)
											&& !(maxSize.equalsIgnoreCase(""))) {
										maxSizeInMB = Integer.parseInt(maxSize);
									}

									if (byteArray.length <= (maxSizeInMB * (Integer
											.parseInt(MaintainContactManagementUIConstants.MAX_ONE_MB_SIZE)))) {
										log.debug("byteArray Size ::: "
														+ byteArray.length);
										log.debug("byteArray in VO ::: "
														+ attachment.getFile1());

										if (attachment != null) {
											String filepath = attachment
													.getFileName();
											log.debug("filepath from VO is:"
															+ filepath);
											//boolean scanResult = true;
											int scanResult;
											boolean totalSizeCheck = false;
											// newly added
											totalSizeCheck = checkTotalSizeOfAttachedFiles(
													attachment,
													maxSizeInMB,
													logCaseDataBean
															.getAttachmentVOList());
											if (!totalSizeCheck) {
												log.info(" after total attacment file size check...");
												//Scans the File for Virus
												//scanResult =
												// checkFileForVirus(filepath);
												String errorMsg;
												boolean skipScan = true;
												String strSkipScan = null;

												try {
													/** Holds the initial Context */
													InitialContext initialContext = new InitialContext();
													/**
													 * skipScan variable should
													 * configured in Server thru
													 * JNDI
													 */
													strSkipScan = (String) initialContext
															.lookup(MaintainContactManagementUIConstants.SKIP_SCAN);

												} catch (Exception e) {
													log
															.debug("########Lookup failed to get scan jndi parameter############"
																	+ e
																			.getMessage());
													e.printStackTrace();
												}

												/*
												 * ConfigurationLoader cl =
												 * ConfigurationLoader.getInstance();
												 * Properties attachProps =
												 * cl.getAttachmentConfigProperties();
												 * strSkipScan =
												 * attachProps.getProperty(MaintainContactManagementUIConstants.SKIP_SCAN);
												 */
												if ((strSkipScan != null)
														&& (strSkipScan
																.equalsIgnoreCase("false"))) {
													skipScan = false;
												}
												log.debug("skipScan ::: "
																+ skipScan);
												//scanResult =
												// VirusScanUtility.scanFile(byteArray,fileName,skipScan);
												scanResult = VirusScanUtility
														.scanFileBeforeUpload(
																byteArray,
																fileName,
																skipScan);
												log.debug("ScanResult-->"
																+ scanResult);

												//Its temporary. Need to remove
												// comment during checkin
												//scanResult =
												// VirusScanUtility.scanFile(byteArray,
												// fileName, skipScan);

												if (scanResult == 0) {
													log.info(" enters after scanResult..");
													description = attachment
															.getDescription();
													log.debug("Description is :"
																	+ description);

													Date atdate = new Date();
													//Format formatter = new
													// SimpleDateFormat(
													//"MM/dd/yyyy", Locale
													//.getDefault());
													Format formatter = new SimpleDateFormat(
															"MM/dd/yyyy  hh:mm a");
													String attachDate = formatter
															.format(atdate);
													log.debug("date before set to VO:"
																	+ attachDate);
													attachment
															.setDateAdded(attachDate);
													attachment
															.setFile1(byteArray);
													attachment
															.setAddedBy(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix // setAddedBy("Sample")
													attachment
															.setExistDoc(false);
													String fileSeparator = "\\"; //System.getProperty("file.separator");
																				 // --
																				 // Client
																				 // on
																				 // Windows
													fileName = fileName
															.substring(
																	fileName
																			.lastIndexOf(fileSeparator) + 1,
																	fileName
																			.length());

													String actualFileName = fileName
															.substring(
																	0,
																	fileName
																			.lastIndexOf("."));

													String finalFileName = actualFileName
															+ "."
															+ fileExtension;
													attachment
															.setFileName(finalFileName);
													attachment
															.setDetachInd(true);
													String finalPathToWPS = writeToSan(
															byteArray,
															finalFileName);

													attachment
															.setFinalPath(finalPathToWPS);

													/////////////////////
													//  Updating Cascade Key that
													// EDMS uses to save
													// attachments into Document
													// Management Systems.
													log.info("before updating cascade key");
													String cascadeOne = getSystemParameter(ContactManagementConstants.CASCADE_ONE_CODE);
													log.debug("Cascade One:"
															+ cascadeOne);
													String cascadeThree = getSystemParameter(ContactManagementConstants.CASCADE_THREE_CODE);
													log.debug("Cascade Three:"
															+ cascadeThree);

													if ((cascadeOne == null || cascadeOne
															.length() == 0)) {
														cascadeOne = "ACS_NH";
													}

													if (cascadeThree == null
															|| cascadeThree
																	.length() == 0) {

														cascadeThree = "UPLOAD";
													}
													// Set the Cascade Key for
													// the attachments
													StringBuffer cascadeBuffer = new StringBuffer();
													cascadeBuffer
															.append(cascadeOne)
															.append("/")
															.append(
																	attachment
																			.getEdmsWrkUnitLevel())
															.append("/")
															.append(
																	cascadeThree)
															.append("/")
															.append(
																	attachment
																			.getEdmsDocType());
													String cascadeKey = cascadeBuffer
															.toString();
													//Modified for defect ESPRD00917530 starts 
													/*attachment
															.setCascadeKey(cascadeKey);*/
													log.debug("cascadeKey = " + cascadeKey);
													log.debug("attachmentVo.getCascadeKey() " + attachment.getCascadeKey());
													if(attachment.getCascadeKey() == null || "".equals(attachment.getCascadeKey()))
													{
														attachment.setCascadeKey(cascadeKey);
														log.debug("Setting Cascade key ");
													}
													else
													{
														log.debug("Cascade key is not empty");
													}

													//defect ESPRD00917530 ends 
													log.debug("Cascade key:"
															+ cascadeKey);

													log
															.debug("Cascade key updated.");
													////////////////////
													logCaseDataBean
															.getAttachmentVOList()
															.add(attachment);
													if(log.isDebugEnabled()){
													log.debug(" logCaseDataBean.getAttachmentVOList() size check :: "
																	+ logCaseDataBean
																			.getAttachmentVOList()
																			.size());
													}

													//  getAttachmentDataBean().setShowAttachmentDataTable(true);
													logCaseDataBean
															.setShowAddAttachments(true);

													logCaseDataBean
															.setAttachmentVO(new AttachmentVO());
													log.debug("-->>Result from Symantec Scan Engine-->>"
																	+ scanResult
																	+ "::No Virus Found");
													errorMsg = bundle
															.getString(MaintainContactManagementUIConstants.CASE_SAVE_SUCCESS);
													
													//for defect ESPRD00790505
													logCaseDataBean.setAttachmentAddOrUpdate(Boolean.TRUE);
													//UC-PGM-CRM-054_ESPRD00357587_19FEB2010
													/*
													 * ContactManagementHelper.setErrorMessage(MaintainContactManagementUIConstants.CASE_SAVE_SUCCESS,
													 * null, null, null);
													 * logCaseDataBean.setShowCaseMessage(true);
													 * //EOf
													 * UC-PGM-CRM-054_ESPRD00357587_19FEB2010
													 */

												}
												/*
												 * else {
												 * System.err.println("File
												 * Virus scan returned error.");
												 * throw new
												 * EnterpriseBaseBusinessException("err.fileattach.file.virusscan.error",
												 * "File Virus scan returned
												 * error.");
												 *  }
												 */
												else if (scanResult == -1) {
													log.debug("-->>Result from Symantec Scan Engine-->>"
																	+ scanResult
																	+ "::Virus Found");
													errorMsg = bundle
															.getString(MaintainContactManagementUIConstants.ERR_FILE_VIRUS_FOUND);

												} else {
													log.debug("-->>Result from Symantec Scan Engine-->>"
																	+ scanResult
																	+ "::Scanning Problem");
													log
															.info("-->>Result from Symantec Scan Engine-->>"
																	+ scanResult
																	+ "::Scanning Problem");
													errorMsg = bundle
															.getString(MaintainContactManagementUIConstants.ERR_FILE_SCAN);
												}
												logCaseDataBean
														.setShowVirusMessage(true);
												FacesMessage fc = new FacesMessage(
														errorMsg);
												String clientId = null;
												UIComponent uiComponent = ContactManagementHelper
														.findComponentInRoot("fileupload1");
												if (uiComponent != null) {
													log.info("yes fileupload1 is thre");
													clientId = uiComponent
															.getClientId(fct);
												}
												//Modified for defect
												// ESPRD00456848 starts
												//fct.addMessage(clientId, fc);
												fct.addMessage(null, fc);
												//defect ESPRD00456848 ends
												log.debug("ShowVirusmessage--->2"
																+ logCaseDataBean
																		.isShowVirusMessage());

											} else {
												log.info("INVALID_FILE_SIZE for total file attachment");
												ContactManagementHelper
														.setErrorMessage(
																MaintainContactManagementUIConstants.INVALID_FILE_SIZE,
																MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
																null, null);
												logCaseDataBean
														.setShowCaseMessage(true);

											}

										}

									} else {
										log.info("INVALID_FILE_SIZE for single file");
										ContactManagementHelper
												.setErrorMessage(
														MaintainContactManagementUIConstants.INVALID_FILE_SIZE,
														MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
														null, null);
										logCaseDataBean
												.setShowCaseMessage(true);
									}
								} else {
									log.info("Size is Zero");
									ContactManagementHelper
											.setErrorMessage(
													MaintainContactManagementUIConstants.CASE_ATTACHED_FILE_SIZE_ZERO,
													MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
													null, null);
									logCaseDataBean.setShowCaseMessage(true);
								}

							} else {
								log.info("INVALID_FILE_TYPE");
								ContactManagementHelper
										.setErrorMessage(
												MaintainContactManagementUIConstants.INVALID_FILE_TYPE,
												MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
												null, null);
								logCaseDataBean.setShowCaseMessage(true);
							}
						} else {
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.ATTACH_MESSAGE,
											MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
											null, null);
							logCaseDataBean.setShowCaseMessage(true);
						}
					}
				} else if (logCaseDataBean.isShowEditAttachments()) {

					AttachmentVO tempAttachVO = logCaseDataBean
							.getAttachmentVO();
					logCaseDataBean.getAttachmentVOList().add(
							Integer.parseInt(logCaseDataBean
									.getAttachmentIndex()), tempAttachVO);
					logCaseDataBean.getAttachmentVOList().remove(
							Integer.parseInt(logCaseDataBean
									.getAttachmentIndex()) + 1);
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
									null, null, null);

					logCaseDataBean.setShowCaseAttachMessages(true);
				}

			} catch (Exception e) {

				log.error("Exception while uploading Attachments" + e);
			}
			//ESPRD00349838
			msg = MaintainContactManagementUIConstants.SUCCESS;
			//ESPRD00349838
			}
			
			
		} else {
			//Modified for the RPM_CR_ESPRD00718265 start
			log.debug("#### status IS NULL ###");
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.SAVE_ERROR_MSG,
					null, null, null);
			logCaseDataBean.setShowCaseMessage(true);
			msg = MaintainContactManagementUIConstants.SUCCESS;
		}
			//Modified for the RPM_CR_ESPRD00718265 end
		}

		//	UC-PGM-CRM-054_ESPRD00357587_19FEB2010
		if (logCaseDataBean.getAttachmentVOList() != null
				&& !logCaseDataBean.getAttachmentVOList().isEmpty()) {
			logCaseDataBean.setShowAttachmentDataTable(true);
		} else {
			logCaseDataBean.setShowAttachmentDataTable(false);
		}
		//EOF UC-PGM-CRM-054_ESPRD00357587_19FEB2010
		LogCaseHelper caseHelper = new LogCaseHelper();
		caseHelper
				.sortCaseAttachmentsComparator(
						MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_DATEADDED,
						MaintainContactManagementUIConstants.SORT_ORDER,
						logCaseDataBean.getAttachmentVOList());

		log.debug(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "uploadFile");

		return msg;
	}

	/**
	 * @param attachmentVO
	 * @param maxSizeInMB
	 * @param attachmentList
	 * @throws EnterpriseBaseBusinessException
	 */
	private boolean checkTotalSizeOfAttachedFiles(AttachmentVO attachmentVO,
			int maxSizeInMB, List attachmentList)
			throws EnterpriseBaseBusinessException {
		double totalSize = 0.0;
		boolean totalSizeChck = false;
		for (int i = 0; i < attachmentList.size(); i++) {
			AttachmentVO alreadyAttachedFile = (AttachmentVO) attachmentList
					.get(i);
			if (alreadyAttachedFile.getFile1() != null) {
				double size = alreadyAttachedFile.getFile1().length;
				totalSize = totalSize + size;
			}
		}

		if (attachmentVO.getFile1() != null) {
			/* && !attachmentVO.getFile1().equals("")) *//* FIND BUGS_FIX */

			totalSize = totalSize + attachmentVO.getFile1().length;
		}

		if (totalSize >= (maxSizeInMB * (Integer
				.parseInt(MaintainContactManagementUIConstants.MAX_ONE_MB_SIZE)))) {
			log.debug("The total size of files attached more than configured maximum. totalSize:"
							+ totalSize);
			//throw new
			// EnterpriseBaseBusinessException("err.fileattach.file.size.more",
			// "The total size of files attached more than configured maximum.
			// totalSize:" + totalSize);
			totalSizeChck = true;
		}
		return totalSizeChck;
	}

	/**
	 * 
	 * @param fileContent
	 * @param fileName
	 *            File name being attached (Not full path)
	 * @return
	 * @throws EnterpriseBaseBusinessException
	 */
	public String writeToSan(byte[] fileContent, String fileName)
			throws EnterpriseBaseBusinessException {
		String uploadFilePath = null;
		String finalPathToWPS = null;
		String uploadFileNameMode = "prod";
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);  // Added for the Performance fix
		ConfigurationLoader cl = ConfigurationLoader.getInstance();
		Properties props = cl.getAttachmentConfigProperties();
		log.debug("The Properties in fileupload is " + props);
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			log.info("***** - Windows");
			uploadFilePath = props
					.getProperty(MaintainContactManagementUIConstants.WINDOWS_PATH);
		} else {
			log.info("***** - Unix");
			uploadFilePath = props
					.getProperty(MaintainContactManagementUIConstants.UNIX_PATH);
		}

		if (fileContent != null) { /* && !fileContent.equals("")) *//*
																    * FIND
																    * BUGS_FIX
																    */

			log.info("***** - fileContent != null  && !fileContent.equals");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			PortletSession session = (PortletSession) context.getSession(true);
			String sanUserSessionDir = (String) session
					.getAttribute(ContactManagementConstants.LOG_CORR_ATTACHMENT_SAN_DIRECTORY);
			if (sanUserSessionDir != null) {
				sanUserSessionDir = sanUserSessionDir.trim();
			}

			if (sanUserSessionDir == null) {
				log.info("***** - sanUserSessionDir is NULL");
				String userId = logCaseDataBean.getLoggedInUserID();  // Modified for the Performance fix //"Sample";
											 // //getUserInfo().getUserId()
				Format formatter = new SimpleDateFormat("MMddyyyyhhmmssSSS");
				String dateAppended = formatter.format(new Date());

				String uploadSanLocation = "";
				String uploadSanLocationJndi = "cm_correspondence_upload_san_location";

				try {
					/** Holds the initial Context */
					InitialContext initialContext = new InitialContext();
					uploadSanLocation = (String) initialContext
							.lookup(uploadSanLocationJndi);

					if (uploadSanLocation == null) {
						uploadSanLocation = uploadFilePath; // If no JNDI
															// lookup, use from
															// property file.
					}
				} catch (NamingException e1) {
					log.debug("Initial Context JNDI lookup failed:"
							+ uploadSanLocationJndi);
					uploadSanLocation = uploadFilePath; // If no JNDI lookup,
														// use from property
														// file.
				}

				String cmUploadFileNameModeJndi = "cm_correspondence_file_name_mode";

				String dirToUse = uploadSanLocation
						+ System.getProperty("file.separator") + userId
						+ dateAppended;

				try {
					/** Holds the initial Context */
					InitialContext initialContext = new InitialContext();
					uploadFileNameMode = (String) initialContext
							.lookup(cmUploadFileNameModeJndi);

					if ((uploadFileNameMode != null)
							&& (uploadFileNameMode.equalsIgnoreCase("test"))) {
						//Modified for defect ESPRD00913494
						/*String nonGpfsFileUploadDir = props
								.getProperty(MaintainContactManagementUIConstants.NON_GPFS_FILE_UPLOAD_DIR);*/
						String nonGpfsFileUploadDir = props.getProperty(MaintainContactManagementUIConstants.NON_GPFS_FILE_UPLOAD_DIR);
						log.debug("nonGpfsFileUploadDir  "+ nonGpfsFileUploadDir);

						dirToUse = uploadSanLocation
								+ System.getProperty("file.separator");
							//	+ nonGpfsFileUploadDir;

					}
				} catch (NamingException e1) {
					log.debug("Initial Context JNDI lookup failed:"
							+ cmUploadFileNameModeJndi);
				}
				log.debug("dirToUse:" + dirToUse);

				File dirOnSan = new File(dirToUse);
				if (!dirOnSan.exists()) {
					log.info("***** - Directory does not exist");
					boolean dirCreated = dirOnSan.mkdir();
					if (!dirCreated) {
						log
								.error("SAN directory to write Attachments couldn't be created:"
										+ dirToUse);
						throw new EnterpriseBaseBusinessException(
								"err.fileattach.write.ioexception",
								"SAN directory to write Attachments couldn't be created:"
										+ dirToUse);
					} else {
						log.info("SAN Dir created.");
					}
				}
				sanUserSessionDir = dirToUse;
				log.debug("++dirToUse --" + dirToUse);
				session
						.setAttribute(
								ContactManagementConstants.LOG_CORR_ATTACHMENT_SAN_DIRECTORY,
								dirToUse);

			} else {
				log.debug("***** - sanUserSessionDir: "
						+ sanUserSessionDir);
			}

			if ((uploadFileNameMode != null)
					&& (uploadFileNameMode.equalsIgnoreCase("test"))) {
				log.info("Using test mode in CM Upload file Name.");
				if (sanUserSessionDir != null) {
					sanUserSessionDir = sanUserSessionDir.trim();
				}
				String testFileName = "";
				// Test file names are used when writing in test mode. Using
				// test file name 1 or 2 alternately
				if (logCaseDataBean.getAttachmentVOList().size() / 2 == 0) {
					testFileName = props
							.getProperty(MaintainContactManagementUIConstants.NON_GPFS_FILE_UPLOAD_TEST_FILE_1);
				} else {
					testFileName = props
							.getProperty(MaintainContactManagementUIConstants.NON_GPFS_FILE_UPLOAD_TEST_FILE_2);
				}
				finalPathToWPS = sanUserSessionDir
						+ System.getProperty("file.separator") + testFileName;

			} else {
				if (sanUserSessionDir != null) {
					sanUserSessionDir = sanUserSessionDir.trim();
				}
				finalPathToWPS = sanUserSessionDir
						+ System.getProperty("file.separator") + fileName;
			}

			log.debug("The final path to WPS is $$ " + finalPathToWPS);

			/*
			 * File fileExists = new File(uploadFilePath +
			 * attachmentVO.getAddedBy()); if (!fileExists.exists()) {
			 * fileExists.mkdir(); }
			 */
			/*
			 * finalPathToWPS = uploadFilePath + attachmentVO.getAddedBy() +
			 * File.separator + attachmentVO.getFileName();
			 */
			//attachmentVO.setFinalPath(finalPathToWPS);
			File file1 = new File(finalPathToWPS);
			try {
				FileOutputStream fs = new FileOutputStream(file1);
				fs.write(fileContent);
			} catch (IOException e) {
				log.error("IOException during file write to SAN:"
						+ finalPathToWPS);
				throw new EnterpriseBaseBusinessException(
						"err.fileattach.write.ioexception",
						"IOException during file write to SAN:"
								+ finalPathToWPS);
			}
		} else {
			log.error("Empty file content for " + fileName
					+ ". No file written.");
			throw new EnterpriseBaseBusinessException(
					"err.fileattach.file.size.zer",
					"Empty file content. File not uploaded.");
		}
		return finalPathToWPS;
	}

	/**
	 * It will validates the Attachments
	 * 
	 * @param attachmentVO
	 *            holds the Attachment object
	 * @return true/false
	 */
	/*
	 * private boolean validateAttachment(AttachmentVO attachmentVO) { boolean
	 * flag = true; LogCaseValidationHelper helper = new
	 * LogCaseValidationHelper(); if (helper.validateAttachments(attachmentVO)) {
	 * flag = false; } return flag; }
	 */

	/**
	 * This method resets attachments.
	 * 
	 * @return String
	 */
	public String resetAttachments() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "resetAttachments");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String index = logCaseDataBean.getAttachmentIndex();
		if (logCaseDataBean.isShowEditAttachments()) {
			AttachmentVO attachmentVO = null;
			AttachmentVO tempAttachmentVO = new AttachmentVO();

			attachmentVO = (AttachmentVO) logCaseDataBean.getAttachmentVOList()
					.get(Integer.parseInt(index));
			try {
				BeanUtils.copyProperties(tempAttachmentVO, attachmentVO);
			} catch (IllegalAccessException e) {
				log.error("IllegalAccessException");
			} catch (InvocationTargetException e) {
				log.error("InvocationTargetException");
			}
			logCaseDataBean.setAttachmentVO(tempAttachmentVO);
		} else if (logCaseDataBean.isShowAddAttachments()) {
			AttachmentVO attachmentVO = new AttachmentVO();
			logCaseDataBean.setAttachmentVO(attachmentVO);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "resetAttachments");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to get the HtmlFileUpload object
	 * 
	 * @return HtmlFileupload It returns the HtmlFileupload object
	 */
	protected HtmlFileupload getFileupload1() {
		if (fileupload1 == null) {
			fileupload1 = (HtmlFileupload) ContactManagementHelper
					.findComponentInRoot("fileupload1");
			log.debug("fileupload1 is ### " + fileupload1);
		}
		return fileupload1;
	}

	/**
	 * This method deletes Attachement.
	 * 
	 * @return String
	 */
	public String deleteAttachment() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "deleteAttachment");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String index = logCaseDataBean.getAttachmentIndex();
		logCaseDataBean.getAttachmentVOList().remove(Integer.parseInt(index));
		/*ContactManagementHelper.setErrorMessage(
				MaintainContactManagementUIConstants.DEL_SUCCESS_MSG, null,
				null, null);*/
		//for ESPRD00747731
		logCaseDataBean.setShowCaseAttachMessages(false);
		logCaseDataBean.setShowAttachmentDelMsg(true);
		logCaseDataBean.setShowEditAttachments(false);
		logCaseDataBean.setShowAddAttachments(false);
		if (logCaseDataBean.getAttachmentVOList().isEmpty()) {
			logCaseDataBean.setShowAttachmentDataTable(false);
		}
		logCaseDataBean.setCursorFocusValue("");
		logCaseDataBean.setPageFocusId("caseAttachmentsHeader");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method detach Attachement.
	 * 
	 * @return String
	 */
	public String detachAttachment() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "detachAttachment");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		AttachmentVO attach = logCaseDataBean.getAttachmentVO();
		attach.setDetachInd(false);
		//newly added
		attach.setDbRecord(true);
		logCaseDataBean.setShowAttachmentLink(false);
		//modified for the defect UC-PGM-CRM-018.2_ESPRD00433227_12MAR2010
		if (logCaseDataBean.isCaseStatusClosed()) {
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.CLOSED_CASE_CR_CANNOT_DETACH,
							null, null, null);
		} else {
			logCaseDataBean.getAttachmentVOList().add(
					Integer.parseInt(logCaseDataBean.getAttachmentIndex()),
					attach);
			logCaseDataBean.getAttachmentVOList().remove(
					Integer.parseInt(logCaseDataBean.getAttachmentIndex()) + 1);
			logCaseDataBean.setAttachmentAddOrUpdate(Boolean.TRUE);
			//for ESPRD00747751
			/*ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
					null, null, null);*/
		}//EOF modification UC-PGM-CRM-018.2_ESPRD00433227_12MAR2010
		logCaseDataBean.setShowCaseAttachMessages(false);
		//for ESPRD00747751
		logCaseDataBean.setDisableSaveAttachment(true);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to show the Add page of Attachments.
	 * 
	 * @return String
	 */
	public String renderAddAttachmentPage() {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "renderAddAttachmentPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowAddAttachments(true);
		logCaseDataBean.setShowEditAttachments(false);
		logCaseDataBean.setShowAttachmentDelMsg(false);
		//ADD for The Defect ESPRD00744910
		logCaseDataBean.setAttachmentVO(new AttachmentVO());
		logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This Method is used to View the Attachment details in EDIT (or) UPDATE
	 * JSP whenever the user clicks on the row in a Span in JSP.
	 * 
	 * @return String
	 */
	public String viewAttachments() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "viewAttachments");
		//for ESPRD00747731
		//int attchIndex = logCaseDataBean.getAttachmentsRowIndex();
		//System.err.println("attchIndex" + attchIndex);
		FacesContext fc = FacesContext.getCurrentInstance();

		String attchIndexStr = (String) fc.getExternalContext().getRequestParameterMap()
				.get("rowIndex");

		//String attchIndexStr = String.valueOf(attchIndex);
		//System.err.println(" attchIndexStr" + attchIndexStr);
		//log.debug("Index value is:" + attchIndex);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setAttachmentIndex(attchIndexStr);
		AttachmentVO attach = null;
		AttachmentVO tempAttachment = new AttachmentVO();
		log.debug(" tempAttachment" + tempAttachment);
		attach = (AttachmentVO) logCaseDataBean.getAttachmentVOList().get(
				Integer.parseInt(attchIndexStr));
		log.debug("str" + attchIndexStr);
		log.debug("attach" + attach);
		if (logCaseDataBean.getCaseDetailsVO() != null) {
			if (logCaseDataBean.getCaseDetailsVO().getStatus().equals("C")) {
				logCaseDataBean.setEnableDetachInd(true);
			} else {
				logCaseDataBean.setEnableDetachInd(false);
			}
		}
		try {
			BeanUtils.copyProperties(tempAttachment, attach);
			if (tempAttachment.isDetachInd()) {
				logCaseDataBean.setShowAttachmentLink(true);
				logCaseDataBean.setDisableSaveAttachment(false);
			} else {
				logCaseDataBean.setShowAttachmentLink(false);
				//for ESPRD00747751
				logCaseDataBean.setDisableSaveAttachment(true);
			}
		} catch (IllegalAccessException e) {
			log.debug("IllegalAccessException");
		} catch (InvocationTargetException e) {
			log.debug("InvocationTargetException");
		}
		logCaseDataBean.setAttachmentVO(tempAttachment);

		logCaseDataBean.setShowEditAttachments(true);
		logCaseDataBean.setShowAddAttachments(false);
		logCaseDataBean.setShowAttachmentDelMsg(false);
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "viewAttachments");
		
		//ESPRD00736691
		UIComponent component = ContactManagementHelper
		.findComponentInRoot("attachmentAuditId");
		
		if (component != null) {
			log.debug("in view attachment in if tempAttachment.getauditkeylist $$$$$$$$"+tempAttachment.getAuditKeyList().size());
			AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
			auditHistoryTable.setValue(tempAttachment.getAuditKeyList());
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
					false);

		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to render the CANCEL add or update page of
	 * Attachments
	 * 
	 * @return String
	 */
	public String cancelAttachments() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "cancelAttachments");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		if (logCaseDataBean.isShowEditAttachments()) {
			logCaseDataBean.setShowEditAttachments(false);
		} else {
			logCaseDataBean.setCursorFocusValue("addAttachment");
		}
		logCaseDataBean.setCursorFocusValue("");
		logCaseDataBean.setPageFocusId("caseAttachmentsHeader");
		log.debug("--End of CancelAttachments--");

		return MaintainContactManagementUIConstants.SUCCESS;

	}

	/**
	 * This method is used to save the Case Events information
	 * 
	 * @return String
	 */
	public String saveCaseEvents() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " saveCaseEvents");
		ContactManagementHelper helper = new ContactManagementHelper();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setInvalidEventDate(false);
		CaseEventsVO eventsVO = logCaseDataBean.getCaseEventsVO();
		/* FIND BUGS_FIX */
		// CaseDelegate caseDelegate=new CaseDelegate();
		//Commented for UC-PGM-CRM-018.4_ESPRD00382315_12FEB2010
		/*
		 * Integer tempMaxSeqNUm=null;
		 * 
		 * try { if(logCaseDataBean.getFirstCaseEventSeqNum()!=null) {
		 * if(logCaseDataBean.getFirstCaseEventSeqNum().intValue()==0) {
		 * tempMaxSeqNUm=caseDelegate.getActCMCaseEventsSequence();
		 * log.info("inside saveCaseEvents
		 * getFirstCaseEventSeqNum"+tempMaxSeqNUm);
		 * logCaseDataBean.setFirstCaseEventSeqNum(tempMaxSeqNUm); } } } catch
		 * (EnterpriseBaseBusinessException e) {
		 * ContactManagementHelper.setErrorMessage(
		 * MaintainContactManagementUIConstants.SAVE_FAILURE, null, null, null);
		 *  }
		 *///EOF UC-PGM-CRM-018.4_ESPRD00382315_12FEB2010
		if (validateCaseEvents(eventsVO)) {
			if (logCaseDataBean.isShowAddCaseEvents()) {
				if (eventsVO != null) {
					/*
					 * commented for UC-PGM-CRM-018.4_ESPRD00382315_12FEB2010
					 * if(logCaseDataBean.getFirstCaseEventSeqNum()!=null)
					 *  {
					 * if(logCaseDataBean.getFirstCaseEventSeqNum().intValue()==0) {
					 * 
					 * tempMaxSeqNUm=new
					 * Integer(logCaseDataBean.getFirstCaseEventSeqNum().intValue()+1);
					 * eventsVO.setCaseEventSeqNum(tempMaxSeqNUm);
					 * logCaseDataBean.setFirstCaseEventSeqNum(tempMaxSeqNUm); }
					 * else {
					 * 
					 * tempMaxSeqNUm=new
					 * Integer(logCaseDataBean.getFirstCaseEventSeqNum().intValue()+1);
					 * eventsVO.setCaseEventSeqNum(tempMaxSeqNUm);
					 * logCaseDataBean.setFirstCaseEventSeqNum(tempMaxSeqNUm); } }
					 *///EOf UC-PGM-CRM-018.4_ESPRD00382315_12FEB2010
					if (StringUtils.isNotEmpty(eventsVO.getNotifyViaAlert())) {
						/*eventsVO.setNotifyViaAlertName(helper
								.getUserNameByID(eventsVO.getNotifyViaAlert()));*/
						eventsVO.setNotifyViaAlertName(helper
								.getDescriptionFromVV(eventsVO
										.getNotifyViaAlert(), logCaseDataBean
										.getEventNotifyList()));
					}

					/* Added for the defect id ESPRD00334553 */
					if (StringUtils.isNotEmpty(eventsVO.getStatusCd())) {
						eventsVO.setStatusCdDescription(helper
								.getDescriptionFromVV(eventsVO.getStatusCd(),
										logCaseDataBean.getEventStatus()));
					}

					if (StringUtils.isNotEmpty(eventsVO.getAlertBasedOn())) {
						eventsVO.setAlertBasedOnDescription(helper
								.getDescriptionFromVV(eventsVO
										.getAlertBasedOn(), logCaseDataBean
										.getEventAlertBasedOn()));

					}
					if (StringUtils.isNotEmpty(eventsVO.getSendAlertDaysCd())) {
						eventsVO.setSendAlertDaysCdDesc(helper
								.getDescriptionFromVV(eventsVO
										.getSendAlertDaysCd(), logCaseDataBean
										.getEventAlertDaysList()));

					}

					logCaseDataBean.getCaseEventsVOList().add(eventsVO);
					/*ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
									null, null, null);*/
					logCaseDataBean.setShowCaseEventsMessages(true);
				}
				setDefaultCaseEvent();
				logCaseDataBean.setShowCaseEventsDataTable(true);
			} else if (logCaseDataBean.isShowEditCaseEvents()) {
				CaseEventsVO tempCEventsVO = logCaseDataBean.getCaseEventsVO();
				if (StringUtils.isNotEmpty(tempCEventsVO.getNotifyViaAlert())) {
					tempCEventsVO.setNotifyViaAlertName(helper
							.getDescriptionFromVV(eventsVO.getNotifyViaAlert(),
									logCaseDataBean.getEventNotifyList()));
				}
				if (StringUtils.isNotEmpty(tempCEventsVO.getEventTypeCd())) {
					// Added for defect id ESPRD00334553

					tempCEventsVO.setEventTypeCdDesc(helper
							.getDescriptionFromVV(tempCEventsVO
									.getEventTypeCd(), logCaseDataBean
									.getEventTypeList()));

				}
				if (StringUtils.isNotEmpty(tempCEventsVO.getAlertBasedOn())) {

					// Added for defect id ESPRD00334553
					/*
					 * tempCEventsVO.setAlertBasedOnDesc(helper
					 * .getDescriptionFromVV(tempCEventsVO .getAlertBasedOn(),
					 * logCaseDataBean .getEventAlertBasedOn()));
					 */
					tempCEventsVO.setAlertBasedOnDescription(helper
							.getDescriptionFromVV(tempCEventsVO
									.getAlertBasedOn(), logCaseDataBean
									.getEventAlertBasedOn()));

				}
				if (StringUtils.isNotEmpty(tempCEventsVO.getStatusCd())) {

					// Added for defect id ESPRD00334553
					/*
					 * tempCEventsVO.setStatusCdDesc(helper.getDescriptionFromVV(
					 * tempCEventsVO.getStatusCd(),
					 * logCaseDataBean.getEventStatus()));
					 */
					tempCEventsVO.setStatusCdDescription(helper
							.getDescriptionFromVV(tempCEventsVO.getStatusCd(),
									logCaseDataBean.getEventStatus()));

				}

				// Added for the defect id ESPRD00425780
				log.info("getCaseEventsVOList"
						+ logCaseDataBean.getCaseEventsVOList().size());
				if (tempCEventsVO.getCaseEventSeqNum() != null) {
					log.info("tempCEventsVO.getCaseEventSeqNum()= "
							+ tempCEventsVO.getCaseEventSeqNum());
					CaseEventsVO oldEventVo = (CaseEventsVO) logCaseDataBean
							.getCaseEventsVOList().get(
									Integer.parseInt(logCaseDataBean
											.getCaseEventsIndex()));
					if (oldEventVo != null
							&& !(oldEventVo.getEventTypeCd()
									.equalsIgnoreCase(tempCEventsVO
											.getEventTypeCd()))) {
						log.info("oldEventVo!=null");
						logCaseDataBean.getEventToRemove().add(oldEventVo);
					}
				}
				// Ends for the defect id ESPRD00425780

				logCaseDataBean.getCaseEventsVOList().add(
						Integer.parseInt(logCaseDataBean.getCaseEventsIndex()),
						tempCEventsVO);
				logCaseDataBean.getCaseEventsVOList()
						.remove(
								Integer.parseInt(logCaseDataBean
										.getCaseEventsIndex()) + 1);
				// Commented for the defect id ESPRD00735630_20DEC2011
				/*ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
						null, null, null);*/
				logCaseDataBean.setShowCaseEventsMessages(true);
			}
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
			logCaseDataBean.setSaveCaseEventFlag(true);
			//modified for defect ID ESPRD00299529

			logCaseDataBean.setRenderedCaseEventsflag(true);
			//EOF modification for defect ID ESPRD00299529
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end */
			if (logCaseDataBean.getCaseEventsVO() != null
					&& logCaseDataBean.getCaseEventsVO().getTemplate() != null
					&& !MaintainContactManagementUIConstants.EMPTY_STRING
							.equals(logCaseDataBean.getCaseEventsVO()
									.getTemplate().trim())) {
				logCaseDataBean.setCaseEventsCreateLetterStatus(true);
			} else {
				logCaseDataBean.setCaseEventsCreateLetterStatus(false);
			}
			
			//ESPRD00786595
			logCaseDataBean.setShowAddCaseEvents(Boolean.FALSE);
			logCaseDataBean.setShowEditCaseEvents(Boolean.FALSE);
			logCaseDataBean.setPageFocusId("caseEventsHeader");
		}
		logCaseDataBean.setCaseEventDeleteMsgFlag(false); // Added for the defect ESPRD00735630_20DEC2011
		logCaseDataBean.setShowCaseMessage(false);
		logCaseDataBean.setDduExistedRecordErrorMsg(true); // Added for the defect id ESPRD00710222_12OCT2011
		logCaseDataBean.setShowCaseDetailsMessages(false); // Added for the defect id ESPRD00712855_19OCT2011
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "saveCaseEvents");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to validate the Case Events page in JSP
	 * 
	 * @param eventsVO
	 *            CaseEventsVO for validation
	 * @return boolean it returns false, if any validation fail, otherwise it
	 *         return true.
	 */
	public boolean validateCaseEvents(CaseEventsVO eventsVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "validateCaseEvents");
		boolean flag = true;
		String eventsActionCode = null;
		Date eventDate =null;
		ContactManagementHelper helper = new ContactManagementHelper();
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//ESPRD00786595
		String pageFocus=null;
		String cursorFocus=null;
		//modified for defect ESPRD00333407
		if (!logCaseDataBean.isShowAddCaseEvents()) {
			eventsActionCode = MaintainContactManagementUIConstants.UPDATE;
			pageFocus="caseEventsEditHeader";
			cursorFocus="type2";
		} else {
			eventsActionCode = MaintainContactManagementUIConstants.ADD;
			pageFocus="addCaseEventPageFocus";
			cursorFocus="caseEventsType";
		}
		if (eventsVO != null) {
			if (isNullOrEmptyField(eventsVO.getCreateDateStr())) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.CREATEDDATE_REQUIRED,
								MaintainContactManagementUIConstants.ADD_CREATEDDATE,
								null, eventsActionCode);
			} else if (!isNullOrEmptyField(eventsVO.getCreateDateStr())) {
				Date createDate = helper.dateConverter(eventsVO
						.getCreateDateStr());
				if (createDate == null) {
					flag = false;
					eventsVO.setCreateDate(null);
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.INVALID_CREATEDDATE,
									MaintainContactManagementUIConstants.ADD_CREATEDDATE,
									null, eventsActionCode);
				} else {
					eventsVO.setCreateDate(createDate);
				}
			}
			if (isNullOrEmptyField(eventsVO.getEventTypeCd())) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.TYPE_REQUIRED,
						"caseEventsType",
						MaintainContactManagementUIConstants.EDIT_TYPE,
						eventsActionCode);
			} else if ((eventsVO.getEventTypeCd().equals("D-DDU Appt") || (eventsVO
					.getEventTypeCd().equals("D")))
					&& (isNullOrEmptyField(eventsVO.getProviderHospital()))) {//for ESPRD00773340
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.PROVIDER_HOPITAL_REQUIRED,
								"addprov", "editprov", eventsActionCode);
			}
			if (isNullOrEmptyField(eventsVO.getDesc())) {

				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.DESC_REQURIED,
						MaintainContactManagementUIConstants.ADD_EVENTDESC,
						MaintainContactManagementUIConstants.EDIT_EVENTDESC,
						eventsActionCode);
				flag = false;
			}
			if (!isNullOrEmptyField(eventsVO.getDesc())
					&& !ContactManagementHelper.validateAlphaNumeric(eventsVO
							.getDesc()))//ESPRD00516124_UC-PGM-CRM-18.4_27AUG2010
			// && !ContactManagementHelper.validateAlpha(eventsVO.getDesc()))
			{

				ContactManagementHelper.setErrorMessage(

				MaintainContactManagementUIConstants.ALPHA_EVENTDESC,
						MaintainContactManagementUIConstants.ADD_EVENTDESC,
						MaintainContactManagementUIConstants.EDIT_EVENTDESC,
						eventsActionCode);
				flag = false;
			}
			if (isNullOrEmptyField(eventsVO.getStatusCd())) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.STATUS_REQURIED,
						"LOGCASEEVENTSTATUS0",
						"LOGCASEEVENTSTATUS1", eventsActionCode);
			}
			if (isNullOrEmptyField(eventsVO.getEventDateStr())) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.EVENTDATE_REQUIRED,
								MaintainContactManagementUIConstants.ADD_EVENTDATE,
								MaintainContactManagementUIConstants.EDIT_EVENTDATE,
								eventsActionCode);
			} else if (!isNullOrEmptyField(eventsVO.getEventDateStr())) {
				eventDate = helper.dateConverter(eventsVO
						.getEventDateStr());
				if (eventDate == null) {
					flag = false;
					eventsVO.setEventDate(null);

					logCaseDataBean.setInvalidEventDate(true);

					//EOF ESPRD00357689
				}else{
					eventsVO.setEventDate(eventDate);
				}
			}
			if (!isNullOrEmptyField(eventsVO.getTime())) {
				if (!helper.isvalidTimeFormat(eventsVO.getTime())) {
					flag = false;
					ContactManagementHelper.setErrorMessage(
							MaintainContactManagementUIConstants.INVALID_TIME,
							MaintainContactManagementUIConstants.TIME_ADD_ID,
							MaintainContactManagementUIConstants.TIME_EDIT_ID,
							eventsActionCode);

				} else if (helper.isvalidTimeFormat(eventsVO.getTime())) {

					//event date is updating with 12hours increase on each event
					//updation hence we are checking the hours with 12 and eleminating
					//other than that we are adding time to event date.
					boolean isTwelve=false;
					String time = eventsVO.getTime();

					long hour = Long.parseLong(time.substring(0, time
							.indexOf(':')));

					long minute = Long.parseLong(time.substring(time
							.indexOf(':') + 1));

					if(hour==12){
						isTwelve=true;
					}
					if (eventsVO.getAmPM() != null
							&& eventsVO.getAmPM().equals("PM")) {
						if(!isTwelve){
						hour = 12 + hour;
						}else{
							isTwelve=false;
						}
					}
					long longTime = 0;
					if(!isTwelve)
					longTime = (hour) * 60 * 60 * 1000 ;
					
					longTime +=  (minute) * 60
					* 1000;
					

					if (eventDate != null) {
						Date newDate = new Date(eventDate.getTime() + longTime);
						eventDate = newDate;
						eventsVO.setEventDate(eventDate);
					}
				}
			}
			if (!isNullOrEmptyField(eventsVO.getDispositionDateStr())) {
				Date disDate = helper.dateConverter(eventsVO
						.getDispositionDateStr());
				if (disDate == null) {
					flag = false;
					eventsVO.setDispositionDate(null);

					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.INVALID_DATE_FORMATE,
									MaintainContactManagementUIConstants.ADD_DISPODATE,
									MaintainContactManagementUIConstants.EDIT_DISPODATE,
									eventsActionCode);
					//EOF defect ESPRD00333407
				}
				//Commented for defect ESPRD00561630 start
				// Start added for defect ID ESPRD00333027
				/*
				 * else if(eventsVO.getCreateDateStr()!=null &&
				 * (eventsVO.getDispositionDateStr().compareTo(eventsVO.getCreateDateStr()) <
				 * 0)){ flag = false; ContactManagementHelper.setErrorMessage(
				 * MaintainContactManagementUIConstants.COMPARE_CREATEDATE_DISPOSITIONDATE,
				 * MaintainContactManagementUIConstants.ADD_DISPODATE,
				 * MaintainContactManagementUIConstants.EDIT_DISPODATE,
				 * eventsActionCode); }
				 */
				//ENDed for defect ID ESPRD00333027
				//defect ESPRD00561630 ends
				else {
					eventsVO.setDispositionDate(disDate);
				}
			}
			if ((caseHelper.isAlertBasedOnLessThanSysDate(eventsVO))
					&& logCaseDataBean.isShowAddCaseEvents()) {
				flag = false;
				//for defect ESPRD00754693
				setErrorMessage(
						"9-3210-0040",
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						"EVENTSGNRICMSGHDN0");
			}
			//added for defect ID ESPRD00299535 && ESPRD00299796
			if (!isNullOrEmptyField(eventsVO.getTime())) {

				if (eventsVO.getAmPM() == null) {
					flag = false;
					//Modified for Defect ESPRD00786993
					ContactManagementHelper.setErrorMessage(
							MaintainContactManagementUIConstants.AM_PM_REQ,

							MaintainContactManagementUIConstants.AMPM_ADD_ID,
							MaintainContactManagementUIConstants.AMPM_EDIT_ID,
							eventsActionCode);
				}

			} else {
				eventsVO.setAmPM(null);
			}
			//EOF defect ID ESPRD00299535 && ESPRD00299796

			if (!isNullOrEmptyField(eventsVO.getSendAlertDaysCd())) {

				if (eventsVO.getAfterOrBeforeCd() == null) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.BEFORE_AFTER_REQ,

									MaintainContactManagementUIConstants.BEFORE_AFTER_ADD,
									MaintainContactManagementUIConstants.BEFORE_AFTER_EDIT,
									eventsActionCode);
				}

			} else {
				eventsVO.setAfterOrBeforeCd(null);
			}

		}

		//      ADD FOR THE DEFECT ESPRD00601882 18 MARCH 2011
		if (flag == false) {
			validateFlag = false;
			logCaseDataBean.setCaseEventsHidden("minus");//Added for the defect ESPRD00717094
			logCaseDataBean.setShowCaseEventsMessages(false);// Added for the defect ESPRD00708917
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
			//ESPRD00786595
			logCaseDataBean.setPageFocusId(pageFocus);
			logCaseDataBean.setCursorFocusValue(cursorFocus);
		}else{
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		}
		logCaseDataBean.setCaseEventDeleteMsgFlag(false);  // Added for the defect ESPRD00735630_20DEC2011
		//END FOR THE DEFECT ESPRD00601882
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "validateCaseEvents");
		return flag;
	}

	/**
	 * This method is used to show the Add page of Case Events.
	 * 
	 * @return String
	 */
	public String renderAddCaseEventsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "renderAddCaseEventsPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowAddCaseEvents(true);
		logCaseDataBean.setShowEditCaseEvents(Boolean.FALSE);
		
		/*//modified for defect ID ESPRD00300181

		CaseEventsVO cevo = new CaseEventsVO();
		cevo.setCreateDate(new Date());
		cevo.setCreateDateStr(ContactManagementHelper.dateConverter(cevo
				.getCreateDate()));
		logCaseDataBean.setCaseEventsVO(cevo);
		//EOF defect ID ESPRD00300181
		//For defect ESPRD00340574 BRC CON0384.0001.01 starts
		logCaseDataBean.getCaseEventsVO().setStatusCd(
				MaintainContactManagementUIConstants.CASE_EVENT_STATUS_TYPE);
		//For defect ESPRD00350457 starts
		CaseEventsVO caseEventsVO = logCaseDataBean.getCaseEventsVO();
		caseEventsVO.setDateDispositionFlag(true);
		//For defect ESPRD00350457 ends
		//For defect ESPRD00340574 BRC CON0384.0001.01 ends
		logCaseDataBean.setShowEditCaseEvents(false);
		logCaseDataBean.setShowCaseEventsMessages(false);

		logCaseDataBean.setRenderedCaseEventsflag(true);
		/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
		//logCaseDataBean.setSaveCaseEventFlag(false);
		/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end */
		//added for defect ESPRD00299552
		//logCaseDataBean.setDisableCaseEventFields(true);
		//EOF defect ESPRD00299552
		//logCaseDataBean.setCaseEventDeleteMsgFlag(false);  // Added for the defect id ESPRD00712855_19OCT2011
		//COMMENTED FOR THE DEFECT ESPRD00667570
		/*FacesContext context = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) context.getExternalContext()
				.getSession(true);
		httpSession.removeAttribute("DispositionDate");*/

		setDefaultCaseEvent();
		
		if (!isNullOrEmptyField(logCaseDataBean.getCaseEventsVO().getTemplate())) {
			logCaseDataBean.setCaseEventsCreateLetterStatus(true);
		} else {
			logCaseDataBean.setCaseEventsCreateLetterStatus(false);
		}
		logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		logCaseDataBean.setShowCaseEventsMessages(Boolean.FALSE);
		//EOF ESPRD00340194,ESPRD00307362
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "renderAddCaseEventsPage");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This Method is used to View the Case Events details in EDIT (or) UPDATE
	 * JSP whenever the user clicks on the row in a Span in JSP.
	 * 
	 * @return String
	 */
	public String viewCaseEventsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "viewCaseEventsPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String strIndex = (String) map.get("rowindex");
		if (strIndex == null) {
			strIndex = logCaseDataBean.getCaseEventsIndex();
		}
		int eventsIndex = Integer.parseInt(strIndex);

		// Major Save Code Ends
		logCaseDataBean.setCaseEventsIndex(strIndex);
		CaseEventsVO caseEventsVO = (CaseEventsVO) logCaseDataBean
				.getCaseEventsVOList().get(eventsIndex);
		/*
		 * caseEventsVO.setCreateDate(new Date());
		 * caseEventsVO.setCreateDateStr(ContactManagementHelper
		 * .dateConverter(new Date()));
		 */
		log.debug("--caseEventsVO.getTemplate()....."
				+ caseEventsVO.getTemplate());
		if (StringUtils.isNotEmpty(caseEventsVO.getTemplate())) {
			logCaseDataBean.setEnableCaseEventsLetterLink(false);
		} else {
			logCaseDataBean.setEnableCaseEventsLetterLink(true);
		}
		logCaseDataBean.setShowCaseEventsMessages(false);
		CaseEventsVO tempCEventsVO = new CaseEventsVO();
		try {
			BeanUtils.copyProperties(tempCEventsVO, caseEventsVO);
		} catch (IllegalAccessException e) {
			log.error("Exception at viewCaseEventsPage()" + e);
		} catch (InvocationTargetException e) {
			log.error("Exception at viewCaseEventsPage()" + e);
		}
		logCaseDataBean.setCaseEventsVO(tempCEventsVO);
		logCaseDataBean.setShowEditCaseEvents(true);
		logCaseDataBean.setShowAddCaseEvents(false);
		logCaseDataBean.setCaseEventDeleteMsgFlag(false);  // Added for the defect id ESPRD00712855_19OCT2011
		//added for ESPRD00340194,ESPRD00307362
		if (logCaseDataBean.getCaseEventsVO().getTemplate() != null
				&& !MaintainContactManagementUIConstants.EMPTY_STRING
						.equals(logCaseDataBean.getCaseEventsVO().getTemplate()
								.trim())) {
			logCaseDataBean.setCaseEventsCreateLetterStatus(true);
		} else {
			logCaseDataBean.setCaseEventsCreateLetterStatus(false);
		}
		if (logCaseDataBean.getCaseEventsVO().getNotifyViaAlertName() != null
				&& !MaintainContactManagementUIConstants.EMPTY_STRING
						.equals(logCaseDataBean.getCaseEventsVO()
								.getNotifyViaAlertName().trim())) {

			logCaseDataBean.setDisableCaseEventFields(false);
		} else {
			logCaseDataBean.setDisableCaseEventFields(true);//ESPRD00359439
		}
		//Modified for the defect ESPRD00380802 starts
		if (logCaseDataBean.getCaseEventsVO().getEventTypeCd() != null
				&& logCaseDataBean.getCaseEventsVO().getEventTypeCd().equals(
						MaintainContactManagementUIConstants.D)) {
			logCaseDataBean.setShowDDUAppointmentScreen(true);
		} else {
			logCaseDataBean.setShowDDUAppointmentScreen(false);
		}
		//Modified for the defect ESPRD00380802 ends
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "viewCaseEventsPage");
		//EOF ESPRD00340194,ESPRD00307362
		logCaseDataBean.setShowCaseEventsMessages(Boolean.FALSE);
		
		//ESPRD00736691
		UIComponent component = ContactManagementHelper
		.findComponentInRoot("caseEventsVOAuditId");
		
		if (component != null) {
			log.debug("in view attachment in if logCaseDataBean.getCaseEventsVO().getauditkeylist $$$$$$$$"+logCaseDataBean.getCaseEventsVO().getAuditKeyList().size());
			AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
			auditHistoryTable.setValue(logCaseDataBean.getCaseEventsVO().getAuditKeyList());
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
					false);

		}
		//Add for the Defect ESPRD00773297
		logCaseDataBean.setRenderedCaseEventsflag(Boolean.TRUE);
		logCaseDataBean.setSaveCaseEventFlag(Boolean.FALSE);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to render the CANCEL add or update page of Case
	 * Events.
	 * 
	 * @return String
	 */
	public String cancelCaseEvents() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "cancelCaseEvents");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setRenderedCaseEventsflag(false);
		logCaseDataBean.setInvalidEventDate(false);
		if (logCaseDataBean.isShowAddCaseEvents()) {
			logCaseDataBean.setShowAddCaseEvents(false);
		}
		if (logCaseDataBean.isShowEditCaseEvents()) {
			logCaseDataBean.setShowEditCaseEvents(false);
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to render the RESENT Case Alerts page in JSP.
	 * 
	 * @return String.
	 */
	public String resetCaseEventsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "resetCaseEventsPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setInvalidEventDate(false);
		setDefaultCaseEvent();
		if (logCaseDataBean.isShowEditCaseEvents()) {
			CaseEventsVO eventVO = null;
			CaseEventsVO tempEventVO = new CaseEventsVO();
			String index = logCaseDataBean.getCaseEventsIndex();
			eventVO = (CaseEventsVO) logCaseDataBean.getCaseEventsVOList().get(
					Integer.parseInt(index));
			try {
				BeanUtils.copyProperties(tempEventVO, eventVO);
			} catch (IllegalAccessException e) {
				log.error("IllegalAccessException");
			} catch (InvocationTargetException e) {
				log.error("InvocationTargetException");
			}
			logCaseDataBean.setCaseEventsVO(tempEventVO);
			if (!isNullOrEmptyField(logCaseDataBean.getCaseEventsVO().getTemplate())) {
				logCaseDataBean.setCaseEventsCreateLetterStatus(true);
			} else {
				logCaseDataBean.setCaseEventsCreateLetterStatus(false);
			}
			if (!isNullOrEmptyField(logCaseDataBean.getCaseEventsVO()
					.getNotifyViaAlertName())) {
				logCaseDataBean.setDisableCaseEventFields(false);
			} else {
				logCaseDataBean.setDisableCaseEventFields(true);
			}
			if (logCaseDataBean.getCaseEventsVO().getEventTypeCd() != null
					&& logCaseDataBean.getCaseEventsVO().getEventTypeCd().equals(
							MaintainContactManagementUIConstants.D)) {
				logCaseDataBean.setShowDDUAppointmentScreen(true);
			} else {
				logCaseDataBean.setShowDDUAppointmentScreen(false);
			}
		} 
		logCaseDataBean.setShowCaseEventsMessages(Boolean.FALSE);
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "resetCaseEventsPage");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to delete the Case Events record form list
	 * 
	 * @return String
	 */
	public String deleteCaseEvents() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "deleteCaseEvents");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String caseEventIndex = logCaseDataBean.getCaseEventsIndex();
		//UC-PGM-CRM-018.4_ESPRD00379771_05JAN10
		logCaseDataBean.getDeletedEventsList().add(
				logCaseDataBean.getCaseEventsVOList().get(
						Integer.parseInt(caseEventIndex)));
		//EOF UC-PGM-CRM-018.4_ESPRD00379771_05JAN10
		logCaseDataBean.getCaseEventsVOList().remove(
				Integer.parseInt(caseEventIndex));
		/*
		 * ContactManagementHelper.setErrorMessage(
		 * MaintainContactManagementUIConstants.DEL_SUCCESS_MSG, null, null,
		 * null);
		 */
		ContactManagementHelper.setErrorMessage(
				MaintainContactManagementUIConstants.DEL_SUCCESS_MSG,
				"deleteEvntErrMsgID", "deleteEvntErrMsgID", null);
		logCaseDataBean.setCaseEventDeleteMsgFlag(true);  // Added for the defect id ESPRD00712855_19OCT2011

		//logCaseDataBean.setShowCaseEventsMessages(true);
		logCaseDataBean.setShowEditCaseEvents(false);
		logCaseDataBean.setShowCaseMessage(false);  // Added for the defect ESPRD00735630_20DEC2011
		logCaseDataBean.setShowAddCaseEvents(false);
		//Added for Defect ESPRD00792967.
		logCaseDataBean.setRenderedCaseEventsflag(Boolean.FALSE);
		if (logCaseDataBean.getCaseEventsVOList().isEmpty()) {
			logCaseDataBean.setShowCaseEventsDataTable(false);
		}
		logCaseDataBean.setDduExistedRecordErrorMsg(true); // Added for the defect id ESPRD00710222_12OCT2011
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "deleteCaseEvents");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to save the Alerts information.
	 * 
	 * @return String
	 */
	public String saveAlerts() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "saveAlerts");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		AlertVO alertVO = logCaseDataBean.getAlertVO();
		ContactManagementHelper helper = new ContactManagementHelper();
		LogCaseHelper caseHelper  = new LogCaseHelper(); //Modified for the Defect ESPRD00748466 
		if (validateCaseAlerts(alertVO)) {
			if (logCaseDataBean.isShowAddAlers()) {
				if (alertVO != null) {
					alertVO
							.setAlertTypeDesc(ContactManagementHelper
									.setShortDescription(
											FunctionalAreaConstants.GENERAL,
											ReferenceServiceDataConstants.G_ALERT_TY_CD,
											alertVO.getAlertType()));
					alertVO
							.setStatusDesc(ContactManagementHelper
									.setShortDescription(
											FunctionalAreaConstants.GENERAL,
											ReferenceServiceDataConstants.G_ALERT_STAT_CD,
											alertVO.getStatus()));	
					//Modified for Defect ESPRD00778832
					/*alertVO.setNotifyUserName(helper.getUserName(alertVO
							.getNotifyViaAlert()));*/
					alertVO.setNotifyUserName(getDescriptionFromVV(alertVO
							.getNotifyViaAlert(), logCaseDataBean.getUserList()));
					logCaseDataBean.getAlertsVOList().add(alertVO);
					// Commented for the defect id ESPRD00735630_20DEC2011
					/*ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
									null, null, null);*/
					logCaseDataBean.setShowCaseAlertsMessages(true);
				}
				AlertVO tempAlertsVO = new AlertVO();
				tempAlertsVO.setStatus("O");
				logCaseDataBean.setAlertVO(tempAlertsVO);
				//Modified for Defect ESPRD00778832
				 Long userSKLongVal = logCaseDataBean.getLoggedInUserSK();
					logCaseDataBean.getAlertVO().setNotifyViaAlert(
							String.valueOf(userSKLongVal));
				logCaseDataBean.setShowAlertsDataTable(true);
				logCaseDataBean.setRenderedalertsFlag(true);
				logCaseDataBean.setShowAddAlers(true);
			} else if (logCaseDataBean.isShowEditAlers()) {
				AlertVO tempAleVO = logCaseDataBean.getAlertVO();
				tempAleVO.setStatusDesc(ContactManagementHelper
						.setShortDescription(FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_ALERT_STAT_CD,
								alertVO.getStatus()));
				AlertVO oldAlertVO = (AlertVO) logCaseDataBean
						.getAlertsVOList().get(
								Integer.parseInt(logCaseDataBean
										.getCaseAlertsIndex()));

				//un necessary code removed for ESPRD00743389 defect.
				if (oldAlertVO.getAlertType().equalsIgnoreCase("A")
						&& !(oldAlertVO.getStatus().equalsIgnoreCase(tempAleVO
								.getStatus()))) {
					if (tempAleVO.getStatus().equalsIgnoreCase("CA")){
						//ADDED FOR THE DEFECT ESPRD00743389
						AlertVO alertVOToSend = getAlertToSend();
			
						
						alertVOToSend
								.setAlertType(ContactManagementConstants.ALERT_TYPE_CODE_AU);
					
						alertVOToSend
								.setAlertTypeDesc(AlertDOConvertor
										.getDescriptionFromVV(
												ContactManagementConstants.ALERT_TYPE_CODE_AU,
												logCaseDataBean
														.getAlertType()));
						

						alertVOToSend
								.setStatus(ContactManagementConstants.ALERT_STATUS_REVIEW_APPROVED);
						
						alertVOToSend
								.setStatusDesc(AlertDOConvertor
										.getDescriptionFromVV(
												ContactManagementConstants.ALERT_STATUS_REVIEW_APPROVED,
												logCaseDataBean
														.getAlertStatusList()));
						

						alertVOToSend
								.setDescription(ContactManagementConstants.AUTOMATIC_ALERT_CASE_REVIEWED_DESC);
						
						alertVOToSend.setNotifyUserName(logCaseDataBean.getCaseDetailsVO().getCreatedBy());

						String createdBySk=logCaseDataBean.getCaseDetailsVO().getCreatedBySK();
						if(createdBySk==null){
							/*createdBySk=helper.getUserIDByWUSK(logCaseDataBean.getCaseDetailsVO().getCreatedBy());
							log.debug("--useridWUmap 00::"+createdBySk);*/
							createdBySk = helper.getUserIDByWUSK2(logCaseDataBean.getCaseDetailsVO().getCreatedBy());
							log.debug("++useridWUmap 00::"+createdBySk);
						}
						alertVOToSend.setNotifyViaAlert(createdBySk);
						alertVOToSend.setRcvgUserID(logCaseDataBean.getCaseDetailsVO().getCreatedBy());
						logCaseDataBean.getAlertsVOList().add(alertVOToSend);
						//ADDED END FOR THE DEFECT ESPRD00743389
					
						logCaseDataBean.setCompletedApprFlag(true);
						logCaseDataBean.getCaseDetailsVO().setStatus(MaintainContactManagementUIConstants.APPROVED_CASE_STATUS);//added for the defect esprd00703588
					}
						
					else{
						logCaseDataBean.setCompletedApprFlag(false);	
					}
						
					if (tempAleVO.getStatus().equalsIgnoreCase("CD")){
						//ADDED FOR THE DEFECT ESPRD00743389
						AlertVO alertVOToSend = getAlertToSend();
						
						alertVOToSend
								.setAlertType(ContactManagementConstants.ALERT_TYPE_CODE_AU);
						
						alertVOToSend.setAlertTypeDesc(AlertDOConvertor
								.getDescriptionFromVV(
										ContactManagementConstants.ALERT_TYPE_CODE_AU,
										logCaseDataBean
										.getAlertType()));
						
						alertVOToSend
								.setStatus(ContactManagementConstants.ALERT_STATUS_REVIEW_DENIED);
						
						alertVOToSend
								.setStatusDesc(AlertDOConvertor
										.getDescriptionFromVV(
												ContactManagementConstants.ALERT_STATUS_REVIEW_DENIED,
												logCaseDataBean
												.getAlertStatusList()));
					
						alertVOToSend
								.setDescription(ContactManagementConstants.AUTOMATIC_CASE_ALERT_DENIED_DESC);
						
						alertVOToSend.setNotifyUserName(logCaseDataBean.getCaseDetailsVO().getCreatedBy());
						
						String createdBySk=logCaseDataBean.getCaseDetailsVO().getCreatedBySK();
						if(createdBySk==null){
							/*createdBySk=helper.getUserIDByWUSK(logCaseDataBean.getCaseDetailsVO().getCreatedBy());
							log.debug("--useridWUmap 6::"+createdBySk);*/
							createdBySk = helper.getUserIDByWUSK2(logCaseDataBean.getCaseDetailsVO().getCreatedBy());
							log.debug("++useridWUmap 6::"+createdBySk);
						}
						alertVOToSend.setNotifyViaAlert(createdBySk);
						alertVOToSend.setRcvgUserID(logCaseDataBean.getCaseDetailsVO().getCreatedBy());
						logCaseDataBean.getAlertsVOList().add(alertVOToSend);
						//ADDED END FOR THE DEFECT ESPRD00743389
						
						logCaseDataBean.setCompletedDeniFlag(true);
						logCaseDataBean.getCaseDetailsVO().setStatus(MaintainContactManagementUIConstants.OPEN_CASE_STATUS);//added for the defect esprd00703588
					}
						
					else{
						logCaseDataBean.setCompletedDeniFlag(false);
					}
						
				}

				else {
					logCaseDataBean.setCompletedApprFlag(false);
					logCaseDataBean.setCompletedDeniFlag(false);
				}

				logCaseDataBean.getAlertsVOList().add(
						Integer.parseInt(logCaseDataBean.getCaseAlertsIndex()),
						tempAleVO);
				logCaseDataBean.getAlertsVOList()
						.remove(
								Integer.parseInt(logCaseDataBean
										.getCaseAlertsIndex()) + 1);
				 // Commented for the defect ESPRD00735630_20DEC2011
				/*ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
						null, null, null);*/
				logCaseDataBean.setShowCaseAlertsMessages(true);

			}
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
			logCaseDataBean.setSaveAlertsFlag(true);
			//Commented for defect ESPRD00446264
			// logCaseDataBean.setRenderedalertsFlag(false);
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end */
			//ESPRD00786595
			logCaseDataBean.setShowAddAlers(Boolean.FALSE);
			logCaseDataBean.setShowEditAlers(Boolean.FALSE);
			logCaseDataBean.setPageFocusId("CaseAlertsHeader");
		}
		logCaseDataBean.setShowCaseMessage(false); //DEFECT ESPRD00553461
												   // 14JAN2011
		logCaseDataBean.setDduExistedRecordErrorMsg(true); // Added for the defect id ESPRD00710222_12OCT2011
		logCaseDataBean.setShowCaseDetailsMessages(false);  // Added for the defect id ESPRD00712855_19OCT2011
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "saveAlerts");
		
		//  Modified for the Defect ESPRD00748466 starts
		// By default Alert date is set in descending order
		caseHelper.sortCaseAlertsComparator("caseAlerts_DueDate", "desc",
				logCaseDataBean.getAlertsVOList());
		
		//  Modified for the Defect ESPRD00748466 ends
		
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to validate UI fields in Alerts section
	 * 
	 * @param alertVO
	 *            holds the AlertVO for validation
	 * @return boolean it retuns false, if any validation fail. otherwise it
	 *         retuns true.
	 */
	public boolean validateCaseAlerts(AlertVO alertVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "validateCaseAlerts");
		boolean flag = true;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		ContactManagementHelper helper = new ContactManagementHelper();
		//ESPRD00786595
		String pageFocus=null;
		String cursorFocus=null;
		String alertActionCode = null;
		if (logCaseDataBean.isShowAddAlers()) {
			alertActionCode = MaintainContactManagementUIConstants.ADD;
			pageFocus="CaseAlertsAddHeader";
			cursorFocus="alertcreatedDate";
		} else {
			alertActionCode = MaintainContactManagementUIConstants.UPDATE;
			pageFocus="CaseAlertsEditHeader";
			cursorFocus="status_3";
		}
		if (alertVO != null) {
			if (isNullOrEmptyField(alertVO.getDueDateStr())) {
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.DUEDATE_REQUIRED,
						MaintainContactManagementUIConstants.ADD_DUEDATE, null,
						alertActionCode);
				flag = false;
			} else if (!isNullOrEmptyField(alertVO.getDueDateStr())) {
				Date dueDate = helper.dateConverter(alertVO.getDueDateStr());
				if (dueDate == null) {
					flag = false;
					alertVO.setDueDate(null);
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.INVALID_DUEDATE,
									MaintainContactManagementUIConstants.ADD_DUEDATE,
									null, alertActionCode);
				} else {
					alertVO.setDueDate(dueDate);
				}
			}
			//Modified for ESPRD00542797
			if (!logCaseDataBean.isShowEditAlers()
					&& alertVO.getDueDate() != null
					&& DateUtility
							.compareDate(alertVO.getDueDate(), new Date()) == 2) {
				flag = false;

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.DUEDATE_LESSTHAN_SYSTEM_DATE,
								MaintainContactManagementUIConstants.ADD_DUEDATE,
								null, alertActionCode);
				//EOF Modification for defect ID ESPRD00299556
			}
			if (isNullOrEmptyField(alertVO.getAlertType())) {
				flag = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.ALERTTYPE_REQUIRED,
								MaintainContactManagementUIConstants.ADD_ALERTTYPE,
								null, alertActionCode);
			}
			if (isNullOrEmptyField(alertVO.getDescription())) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.DESC_REQURIED,
						MaintainContactManagementUIConstants.ADD_DESCRIPTION,
						null, alertActionCode);
			}
			if (!isNullOrEmptyField(alertVO.getDescription())
					&& !EnterpriseCommonValidator
							.validateAlphaSpecialChars(alertVO.getDescription()
									.trim())) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.INVALID_DESC,
						MaintainContactManagementUIConstants.ADD_DESCRIPTION,
						null, alertActionCode);
			}
			if (!MaintainContactManagementUIConstants.UPDATE
					.equals(alertActionCode)
					&& (isNullOrEmptyField(alertVO.getNotifyViaAlert()))) {
				//Modified for defect ID : ESPRD00300212
				List user_list = logCaseDataBean.getUserList();
				if (user_list != null) {
					Iterator selItemList = user_list.iterator();
					while (selItemList.hasNext()) {
						SelectItem sItem = (SelectItem) selItemList.next();
						if (sItem.getLabel().equals(
								logCaseDataBean.getCaseDetailsVO()
										.getAssignedTo())) {
							logCaseDataBean.getAlertVO().setNotifyViaAlert(
									sItem.getValue().toString());
							break;
						}
					}
				}
				if (isNullOrEmptyField(alertVO.getNotifyViaAlert())) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.NOTIFYVIA_REQUIRED,
									MaintainContactManagementUIConstants.ADD_NOTIFYVIA,
									null, alertActionCode);
				}
				//EOF defect ID ESPRD00300212
			}
			if (isNullOrEmptyField(alertVO.getStatus())) {
				flag = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.STATUS_REQURIED,
						MaintainContactManagementUIConstants.ADD_ALERTSTATUS,
						MaintainContactManagementUIConstants.EDIT_ALERTSTATUS,
						alertActionCode);
			}
		}
		//      ADD FOR THE DEFECT ESPRD00601882 18 MARCH 2011
		if (flag == false) {
			validateFlag = false;
			logCaseDataBean.setShowCaseAlertsMessages(false);//Added for the defect ESPRD00708917
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
			//ESPRD00786595
			logCaseDataBean.setPageFocusId(pageFocus);
			logCaseDataBean.setCursorFocusValue(cursorFocus);
		}else{
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		}
		//     END FOR THE DEFECT ESPRD00601882
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "validateCaseAlerts");
		return flag;
	}

	/**
	 * This method is used to show the Add page of Alerts.
	 * 
	 * @return String
	 */
	public String renderAddAlertsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "renderAddAlertsPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowAddAlers(true);
		logCaseDataBean.setAlertVO(new AlertVO());
		logCaseDataBean.getAlertVO().setStatus(ContactManagementConstants.ALERT_STATUS_OPEN);
		logCaseDataBean.setShowEditAlers(false);
		logCaseDataBean.setShowCaseAlertsMessages(false);
		logCaseDataBean.setRenderedalertsFlag(true);
		/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
		logCaseDataBean.setSaveAlertsFlag(false);
		/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end */
		//For Latest CR 603800 modified
		//added for defect ID ESPRD00300212
		/*
		 * List user_list=logCaseDataBean.getUserList(); if(user_list!=null){
		 * Iterator selItemList = user_list.iterator();
		 * while(selItemList.hasNext()){ SelectItem sItem =
		 * (SelectItem)selItemList.next();
		 * if(sItem.getLabel().equals(logCaseDataBean.getCaseDetailsVO().getAssignedTo())){
		 * logCaseDataBean.getAlertVO().setNotifyViaAlert(sItem.getValue().toString());
		 * break; } } }
		 */
		String logInUser = logCaseDataBean.getLoggedInUserID();  // Modified for the Performance fix

		//Modified for GAI Recursive Call_Fix
		//Long userSKLongVal = getUserSK(logInUser);
		Long userSKLongVal = logCaseDataBean.getLoggedInUserSK();
		logCaseDataBean.getAlertVO().setNotifyViaAlert(
				String.valueOf(userSKLongVal));
		logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "renderAddAlertsPage");
		//EOF defect ID ESPRD00300212
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This Method is used to View the Alerts details in EDIT (or) UPDATE JSP
	 * whenever the user clicks on the row in a Span in JSP.
	 * 
	 * @return String
	 */
	public String viewAlertsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "viewAlertsPage");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String strIndex = (String) map.get("alertIndexCode");
		int alertIndex = new Integer(strIndex).intValue();
		logCaseDataBean.setCaseAlertsIndex(strIndex);
		AlertVO alertVO = (AlertVO) logCaseDataBean.getAlertsVOList().get(
				alertIndex);
		logCaseDataBean.setShowCaseAlertsMessages(false);
		AlertVO tempAlVO = new AlertVO();
		try {
			BeanUtils.copyProperties(tempAlVO, alertVO);
		} catch (IllegalAccessException e) {
			log.error("Exception at viewAlertsPage()" + e);
		} catch (InvocationTargetException e) {
			log.error("Exception at viewAlertsPage()" + e);
		}
		logCaseDataBean.setAlertVO(tempAlVO);
		logCaseDataBean.setShowEditAlers(true);
		logCaseDataBean.setShowAddAlers(false);
		log.debug(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "viewAlertsPage");
		
		//ESPRD00736691
		UIComponent component = ContactManagementHelper
		.findComponentInRoot("alertsAuditId");
		
		if (component != null) {
			log.debug("in view attachment in if tempAlVO.getauditkeylist $$$$$$$$"+tempAlVO.getAuditKeyList().size());
			AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
			auditHistoryTable.setValue(tempAlVO.getAuditKeyList());
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
					false);

		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to render the CANCEL add or update page of Alerts
	 * 
	 * @return String
	 */
	public String cancelAlertsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "cancelAlertsPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setRenderedalertsFlag(false);
		if (logCaseDataBean.isShowAddAlers()) {
			logCaseDataBean.setShowAddAlers(false);
		}
		if (logCaseDataBean.isShowEditAlers()) {
			logCaseDataBean.setShowEditAlers(false);
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to render the RESENT Case Alerts page in JSP.
	 * 
	 * @return String.
	 */
	public String resetAlertsPage() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setAlertVO(new AlertVO());
		//Added for the DEFECT-ESPRD00778832
		Long userSKLongVal = logCaseDataBean.getLoggedInUserSK();
		logCaseDataBean.getAlertVO().setNotifyViaAlert(
				String.valueOf(userSKLongVal));
		logCaseDataBean.getAlertVO().setStatus(ContactManagementConstants.ALERT_STATUS_OPEN);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to save the Case Steps information
	 * 
	 * @return String
	 */
	public String saveCaseSteps() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "saveCaseSteps");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseStepsVO stepsVO = logCaseDataBean.getCaseStepsVO();
		/* FIND BUGS_FIX */
		//  CaseDelegate caseDelegate=new CaseDelegate();
		ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
		/*
		 * commented for UC-PGM-CRM-018.4_ESPRD00382315_12FEB2010 Integer
		 * tempMaxSeqNUm=new Integer(0);
		 * 
		 * try { if(logCaseDataBean.getFirstCaseStepSeqNum()!=null) {
		 * if(logCaseDataBean.getFirstCaseStepSeqNum().intValue()==0) {
		 * tempMaxSeqNUm=caseDelegate.getActCMCaseStepSequence();
		 * 
		 * logCaseDataBean.setFirstCaseStepSeqNum(tempMaxSeqNUm); } } } catch
		 * (EnterpriseBaseBusinessException e) {
		 * ContactManagementHelper.setErrorMessage(
		 * MaintainContactManagementUIConstants.SAVE_FAILURE, null, null, null);
		 *  }
		 *///EOF UC-PGM-CRM-018.4_ESPRD00382315_12FEB2010
		if (validateCaseSteps(stepsVO)) {
			if (logCaseDataBean.isShowAddCaseSteps()) {
				if (stepsVO != null) {

					/*
					 * commented for UC-PGM-CRM-018.4_ESPRD00382315_12FEB2010
					 * if(logCaseDataBean.getFirstCaseStepSeqNum()!=null)
					 *  {
					 * if(logCaseDataBean.getFirstCaseStepSeqNum().intValue()==0) {
					 * 
					 * tempMaxSeqNUm=new
					 * Integer(logCaseDataBean.getFirstCaseStepSeqNum().intValue()+1);
					 * stepsVO.setCaseStepSeqNum(tempMaxSeqNUm);
					 * logCaseDataBean.setFirstCaseStepSeqNum(tempMaxSeqNUm); }
					 * else {
					 * 
					 * tempMaxSeqNUm=new
					 * Integer(logCaseDataBean.getFirstCaseStepSeqNum().intValue()+1);
					 * stepsVO.setCaseStepSeqNum(tempMaxSeqNUm);
					 * logCaseDataBean.setFirstCaseStepSeqNum(tempMaxSeqNUm); } }
					 *///EOF UC-PGM-CRM-018.4_ESPRD00382315_12FEB2010

					if (!isNullOrEmptyField(stepsVO.getStatus())) {
						stepsVO.setStatusDescription(contactManagementHelper
								.getDescriptionFromVV(stepsVO.getStatus(),
										logCaseDataBean.getStepStatusList()));
					}

					if (!isNullOrEmptyField(stepsVO.getNotifyViaAlert())) {
						stepsVO
								.setNotifyAlertDescription(contactManagementHelper
										.getDescriptionFromVV(stepsVO
												.getNotifyViaAlert(),
												logCaseDataBean.getUserList()));
					}

					if (!isNullOrEmptyField(stepsVO.getDescription())) {
						stepsVO.setCaseStepsDescription(contactManagementHelper
								.getDescriptionFromVV(stepsVO.getDescription(),
										logCaseDataBean.getCaseStepCodeList()));
					}

					if (!isNullOrEmptyField(stepsVO.getRouteTo())) {
						stepsVO.setRouteToDescription(contactManagementHelper
								.getDescriptionFromVV(stepsVO.getRouteTo(),
										logCaseDataBean.getUserList()));
						if (isNullOrEmptyField(stepsVO.getRouteToDescription())) {
							stepsVO
									.setRouteToDescription(contactManagementHelper
											.getDescriptionFromVV(stepsVO
													.getRouteTo(),
													logCaseDataBean
															.getWorkUnitList()));
						}
					}

					if (!isNullOrEmptyField(stepsVO.getCompletedBasedOn())) {
						stepsVO
								.setCompletionBasedOnDescription(contactManagementHelper
										.getDescriptionFromVV(stepsVO
												.getCompletedBasedOn(),
												logCaseDataBean
														.getStepCompBasedOn()));
					}

					logCaseDataBean.getCaseStepsVOList().add(stepsVO);
					renderAddCaseStepsPage();//Add for the Defect ESPRD00773297
					 // Commented for the defect ESPRD00735630_20DEC2011
					/*ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
									null, null, null);*/
					//logCaseDataBean.setShowCaseMessage(true); //DEFECT
					// ESPRD00553461 14JAN2011
					logCaseDataBean.setShowCaseStepsMessages(true);
				}

				logCaseDataBean.setShowCaseStepsDataTable(true);

			}
			if (logCaseDataBean.isShowEditCaseSteps()) {
				log.debug("CaseSteps update mode");
				CaseStepsVO tempCaseStepVO = logCaseDataBean.getCaseStepsVO();

				stepsVO.setStatusDescription(contactManagementHelper
						.getDescriptionFromVV(stepsVO.getStatus(),
								logCaseDataBean.getStepStatusList()));

				stepsVO.setNotifyAlertDescription(contactManagementHelper
						.getDescriptionFromVV(stepsVO.getNotifyViaAlert(),
								logCaseDataBean.getUserList()));

				stepsVO.setCaseStepsDescription(contactManagementHelper
						.getDescriptionFromVV(stepsVO.getDescription(),
								logCaseDataBean.getCaseStepCodeList()));

				stepsVO.setRouteToDescription(contactManagementHelper
						.getDescriptionFromVV(stepsVO.getRouteTo(),
								logCaseDataBean.getUserList()));
				if (isNullOrEmptyField(stepsVO.getRouteToDescription())) {
					stepsVO.setRouteToDescription(contactManagementHelper
							.getDescriptionFromVV(stepsVO.getRouteTo(),
									logCaseDataBean.getWorkUnitList()));
				}
				stepsVO.setCompletionBasedOnDescription(contactManagementHelper
						.getDescriptionFromVV(stepsVO.getCompletedBasedOn(),
								logCaseDataBean.getStepCompBasedOn()));

				// Added for the defect id ESPRD00391040
				if (stepsVO.getCaseStepSeqNum() != null) {
					log.info("stepsVO.getCaseStepSeqNum()= "
							+ stepsVO.getCaseStepSeqNum());
					CaseStepsVO oldStepVo = (CaseStepsVO) logCaseDataBean
							.getCaseStepsVOList().get(
									Integer.parseInt(logCaseDataBean
											.getCaseStepsIndex()));
					if (oldStepVo != null
							&& !(oldStepVo.getDescription()
									.equalsIgnoreCase(stepsVO.getDescription()))) {
						log.info("oldStepVo!=null");
						logCaseDataBean.getStepToRemove().add(oldStepVo);
						log.info("getStepToRemove size"
								+ logCaseDataBean.getStepToRemove().size());
					}
				}
				// Ends for the defect id ESPRD00391040

				logCaseDataBean.getCaseStepsVOList().add(
						Integer.parseInt(logCaseDataBean.getCaseStepsIndex()),
						tempCaseStepVO);
				logCaseDataBean.getCaseStepsVOList()
						.remove(
								Integer.parseInt(logCaseDataBean
										.getCaseStepsIndex()) + 1);
				// Commented for the defect id ESPRD00735630_20DEC2011
				/*ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
						null, null, null);*/
				// logCaseDataBean.setShowCaseMessage(true);//ESPRD00351438
				// //DEFECT ESPRD00553461 14JAN2011
				logCaseDataBean.setShowCaseStepsMessages(true);
				///Added for defect ESPRD00539435 starts/////
				if (stepsVO.getStatus().equals("C")
						|| stepsVO.getStatus().equals("P")) {
					FacesContext context = FacesContext.getCurrentInstance();
					HttpSession httpSession = (HttpSession) context
							.getExternalContext().getSession(true);
					String stepOrderToBeUpdated = (String) httpSession
							.getAttribute("CaseStepActiveOrder");
					log.debug("++stepOrderToBeUpdated==="
							+ stepOrderToBeUpdated);
					List caseStepsVOList = null;
					if (stepOrderToBeUpdated != null) {
						caseStepsVOList = logCaseDataBean.getCaseStepsVOList();
						if (!caseStepsVOList.isEmpty()
								&& caseStepsVOList.size() > 0) {
							for (int i = 0; i < caseStepsVOList.size(); i++) {
								CaseStepsVO stepsForCheck = (CaseStepsVO) caseStepsVOList
										.get(i);
								if (Integer.parseInt(stepsForCheck.getOrder()) == (Integer
										.parseInt(stepOrderToBeUpdated) + 1)) {
									stepsForCheck.setStatus("A");
									stepsForCheck
											.setStatusDescription(contactManagementHelper
													.getDescriptionFromVV(
															"A",
															logCaseDataBean
																	.getStepStatusList()));
									//defect ESPRD00540227 started
									Date date = new Date();
									String dateStr = ContactManagementHelper
											.dateConverter(date);
									stepsForCheck.setDateStartedStr(dateStr);
									stepsForCheck.setDateStarted(date);
									//defect ESPRD00540227 ends
								}
							}
						}
					}
					httpSession.removeAttribute("CaseStepActiveOrder");
				}
				////defect ESPRD00539435 ends//////
			}
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
			//--------Route To start-------------
			//Begin - Added new method for the defect Id
			// ESPRD00679441_18Aug2011
			routingFromSteps();
			//End - Added new method for the defect Id ESPRD00679441_18Aug2011

			//---------Route To Ends-------------------

			//changed for the defect ESPRD00335122
			logCaseDataBean.setRenderedCaseStepsflag(true);
			logCaseDataBean.setSaveCaseStepFlag(true);
			/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
			if (logCaseDataBean.getCaseStepsVO() != null
					&& logCaseDataBean.getCaseStepsVO().getTemplate() != null
					&& !MaintainContactManagementUIConstants.EMPTY_STRING
							.equals(logCaseDataBean.getCaseStepsVO()
									.getTemplate().trim())) {
				logCaseDataBean.setCaseStepsCreateLetterStatus(true);
			} else {
				logCaseDataBean.setCaseStepsCreateLetterStatus(false);
			}
			//Commented for the Defect ESPRD00773297
			//logCaseDataBean.setCaseStepsVO(new CaseStepsVO());//ADDED FOR THE DEFECT ESPRD00762756
			//ESPRD00786595
			logCaseDataBean.setShowAddCaseSteps(Boolean.FALSE);
			logCaseDataBean.setShowEditCaseSteps(Boolean.FALSE);
			logCaseDataBean.setPageFocusId("caseStepsHeader");

		}
		logCaseDataBean.setShowCaseMessage(false); //DEFECT ESPRD00553461 14JAN2011
		logCaseDataBean.setShowCaseStepsDelteMessage(false); // Added for the defect ESPRD00735630_20DEC2011
		logCaseDataBean.setDduExistedRecordErrorMsg(true); // Added for the defect id ESPRD00710222_12OCT2011
		logCaseDataBean.setShowCaseDetailsMessages(false); // Added for the defect id ESPRD00712855_19OCT2011
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "saveCaseSteps");
		// Commented for the defect  ESPRD00762756 
		// logCaseDataBean.setCaseStepsVO(new CaseStepsVO());//ADDED FOR THE DEFECT ESPRD00722266
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to validate UI fields in Case Steps section
	 * 
	 * @param valCaseStepVO
	 *            holds the CaseStepsVO for validation
	 * @return boolean it retuns false, if any validation fail. otherwise it
	 *         retuns true.
	 */
	public boolean validateCaseSteps(CaseStepsVO valCaseStepVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "validateCaseSteps");
		/* FIND BUGS_FIX */
		// boolean flag = true;
		ContactManagementHelper helper = new ContactManagementHelper();
		//ESPRD00786595
		String pageFocus=null;
		String cursorFocus=null;
		String actionCode = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (logCaseDataBean.isShowAddCaseSteps()) {
			actionCode = MaintainContactManagementUIConstants.ADD;
			cursorFocus="order";
			pageFocus="addCaseStepFocusPage";
		} else {
			actionCode = MaintainContactManagementUIConstants.UPDATE;
			pageFocus="editCaseStepFocusPage";
			cursorFocus="order2";
		}
		if (valCaseStepVO != null) {
			if (isNullOrEmptyField(valCaseStepVO.getOrder())) {
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.ORDER_REQURIED,
						MaintainContactManagementUIConstants.ADD_ORDER,
						MaintainContactManagementUIConstants.EDIT_ORDER2,
						actionCode);
				flag = false;
			}
			if (!isNullOrEmptyField(valCaseStepVO.getOrder())
					&& !EnterpriseCommonValidator.validateNumeric(valCaseStepVO
							.getOrder().trim())) {
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.ORDER_INVALID,
						MaintainContactManagementUIConstants.ADD_ORDER,
						MaintainContactManagementUIConstants.EDIT_ORDER2,
						actionCode);
				flag = false;
			}
			if (isNullOrEmptyField(valCaseStepVO.getDescription())) {

				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.DESC_REQURIED,
						MaintainContactManagementUIConstants.ADD_DESC,
						MaintainContactManagementUIConstants.EDIT_DESC2,
						actionCode);
				flag = false;
			}
			if (isNullOrEmptyField(valCaseStepVO.getStatus())) {

				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.STATUS_REQURIED,
						MaintainContactManagementUIConstants.ADD_STATUS,
						MaintainContactManagementUIConstants.EDIT_STATUS2,
						actionCode);
				flag = false;
			}
			if (!isNullOrEmptyField(valCaseStepVO.getExpectedDaysToComplete())
					&& !(EnterpriseCommonValidator
							.validateNumeric(valCaseStepVO
									.getExpectedDaysToComplete().trim()))) {
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_EXPECTEDDAYS,
								MaintainContactManagementUIConstants.ADD_EXPECTEDDAYS,
								MaintainContactManagementUIConstants.EDIT_EXPECTEDDAYS2,
								actionCode);
				flag = false;
			}
			if (valCaseStepVO.getExpectedDaysToComplete() != null
					&& valCaseStepVO.getCompletedBasedOn() != null
					&& valCaseStepVO.getExpectedDaysToComplete().length() > 0
					&& valCaseStepVO.getCompletedBasedOn().length() == 0) {
				//modified for UC-PGM-CRM-018.7_ESPRD00359439_27NOV09

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.COMPLETION_BASED_ON_REQ,
								MaintainContactManagementUIConstants.ADD_ID_COMPLETION_BASED_ON,
								MaintainContactManagementUIConstants.EDIT_ID_COMPLETION_BASED_ON,
								actionCode);
				//EOF modifications for defect
				// UC-PGM-CRM-018.7_ESPRD00359439_27NOV09
				flag = false;
			}
			if (valCaseStepVO.getCompletedBasedOn() != null
					&& valCaseStepVO.getCompletedBasedOn().trim().length() > 0
					&& valCaseStepVO.getExpectedDaysToComplete().length() == 0) {
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.EXPECTED_DAYS_TO_COMPLETE_REQ,
								MaintainContactManagementUIConstants.ADD_EXPECTEDDAYS,
								MaintainContactManagementUIConstants.EDIT_EXPECTEDDAYS2,
								actionCode);
				flag = false;
			}
			if (!isNullOrEmptyField(valCaseStepVO.getExpectedCompletionDateStr())) {
				Date compDate = helper.dateConverter(valCaseStepVO
						.getExpectedCompletionDateStr());
				if (compDate == null) {
					flag = false;
					valCaseStepVO.setExpectedCompletionDateStr(null);
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.INVALID_EXCEPTED_DATE,
									MaintainContactManagementUIConstants.ADD_EXPECTCOMP,
									MaintainContactManagementUIConstants.EDIT_EXPECTCOMP,
									actionCode);
				} else {
					valCaseStepVO.setExpectedCompletionDate(compDate);
				}
			}
			// Modified for the defect ESPRD00762756 starts
			if (!isNullOrEmptyField(valCaseStepVO.getSendAlertDaysStr())) {

				if (valCaseStepVO.getBeforeOrAfterInd() == null) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.BEFORE_AFTER_REQ,
									"addCaseStepsVOBeforeOrAfterInd",
									"editStepsBeforAftrIndic",
									actionCode);
				}

			} else {
				valCaseStepVO.setBeforeOrAfterInd(null);
			}
			
			// Modified for the defect ESPRD00762756 ends
		}
		if (logCaseDataBean.isShowAddCaseSteps()) {//Condition added for defect
												   // ESPRD00333429
			boolean isDuplicateFound = false;
			CaseStepsVO stepsVO = logCaseDataBean.getCaseStepsVO();
			if (logCaseDataBean.getCaseStepsVOList() != null) {
				Iterator itr = logCaseDataBean.getCaseStepsVOList().iterator();
				CaseStepsVO tempStepsVO = null;

				while (itr.hasNext()) {
					tempStepsVO = (CaseStepsVO) itr.next();
					if (tempStepsVO.getOrder() != null) {
						if (tempStepsVO.getOrder().trim().equals(
								stepsVO.getOrder().trim())) {
							if(log.isDebugEnabled()){
							log.debug("******tempStepsVO.getRouteTo()*******"
											+ tempStepsVO.getRouteTo());
							log.debug("******stepsVO.getRouteTo()*******"
											+ stepsVO.getRouteTo());
							}
							//Modified for the Defect ESPRD00780571 starts
							if (!isNullOrEmptyField(stepsVO.getRouteTo()) && !isNullOrEmptyField(tempStepsVO.getRouteTo())
									&& !stepsVO.getRouteTo().trim().equals(
											tempStepsVO.getRouteTo())) {//UC-PGM-CRM-018.7_ESPRD00357725_6JAN10
								isDuplicateFound = true;
								break;
							}//UC-PGM-CRM-018.7_ESPRD00357725_6JAN10}
							if (!isNullOrEmptyField(stepsVO.getRouteTo())
									&& !stepsVO.getRouteTo().equals(
											tempStepsVO.getRouteTo())) {
								isDuplicateFound = true;
								break;
							}
							if (!isNullOrEmptyField(tempStepsVO.getRouteTo())
									&& !tempStepsVO.getRouteTo().equals(
											stepsVO.getRouteTo())) {
								isDuplicateFound = true;
								break;
							}//Defect ESPRD00780571 ends
						}
					}
				}
			}
			if (isDuplicateFound) {
				flag = false;
				//Displaying error message at section level also
				ContactManagementHelper
						.setErrorMessage(MaintainContactManagementUIConstants.DUPLICATE_ORDER_CASE_STEP_SAVE, "ADDSTEPSERRMSGDIFFROUTE", "EDITSTEPSERRMSGDIFFROUTE", actionCode);
			}
		}// EOF defect ESPRD00333429

		// Defect ESPRD00696658 starts
		if (logCaseDataBean.isShowEditCaseSteps()) {
			boolean isDuplicateFound = false;
			int stepsIndex = Integer.parseInt(logCaseDataBean
					.getCaseStepsIndex());

			CaseStepsVO caseStepsVO1 = (CaseStepsVO) logCaseDataBean
					.getCaseStepsVOList().get(stepsIndex);

			CaseStepsVO caseStepsVO = logCaseDataBean.getCaseStepsVO();

			if (logCaseDataBean.getCaseStepsVOList() != null) {

				log.debug(" Before Checking size========"
						+ logCaseDataBean.getCaseStepsVOList().size());
				List tempCaseStepsVOList = new ArrayList(logCaseDataBean
						.getCaseStepsVOList());
				tempCaseStepsVOList.remove(stepsIndex);
				Iterator itr = tempCaseStepsVOList.iterator();
				CaseStepsVO tempStepsVO = null;

				while (itr.hasNext()) {
					tempStepsVO = (CaseStepsVO) itr.next();
					if (tempStepsVO.getOrder() != null) {
						if (tempStepsVO.getOrder().trim().equals(
								caseStepsVO.getOrder().trim())) {
							if(log.isDebugEnabled()){
							log.debug("******In edit mode tempStepsVO.getRouteTo()*******"
											+ tempStepsVO.getRouteTo());
							log.debug("******In edit mode caseStepsVO.getRouteTo()*******"
											+ caseStepsVO.getRouteTo());
							}
							//Modified for the Defect ESPRD00780571 starts
							if (!isNullOrEmptyField(caseStepsVO.getRouteTo()) && !isNullOrEmptyField(tempStepsVO.getRouteTo())
									&& !caseStepsVO.getRouteTo().equals(
											tempStepsVO.getRouteTo())) {
								isDuplicateFound = true;
								break;
							}
							if (!isNullOrEmptyField(caseStepsVO.getRouteTo())
									&& !caseStepsVO.getRouteTo().equals(
											tempStepsVO.getRouteTo())) {
								isDuplicateFound = true;
								break;
							}
							if (!isNullOrEmptyField(tempStepsVO.getRouteTo())
									&& !tempStepsVO.getRouteTo().equals(
											caseStepsVO.getRouteTo())) {
								isDuplicateFound = true;
								break;
							}//Defect ESPRD00780571 ends

						}
					}

				}

			}

			log.debug(" After Checking size========"
					+ logCaseDataBean.getCaseStepsVOList().size());
			if (isDuplicateFound) {
				flag = false;
				//Displaying error message at section level also
				ContactManagementHelper
				.setErrorMessage(MaintainContactManagementUIConstants.DUPLICATE_ORDER_CASE_STEP_SAVE, "ADDSTEPSERRMSGDIFFROUTE", "EDITSTEPSERRMSGDIFFROUTE", actionCode);
			}
		}
		// Defect ESPRD00696658 ends

		//      ADD FOR THE DEFECT ESPRD00601882 18 MARCH 2011
		if (flag == false) {
			validateFlag = false;
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
			//ESPRD00786595
			logCaseDataBean.setPageFocusId(pageFocus);
			logCaseDataBean.setCursorFocusValue(cursorFocus);
		}else{
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		}
		//END FOR THE DEFECT ESPRD00601882
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "validateCaseSteps");
		return flag;
	}

	/**
	 * This method is used to delete the selected row in Case Steps DataTable.
	 * This delete is not a database delete it is a memory delete, i.e delete
	 * from the existing list only.
	 * 
	 * @return String
	 */
	public String deleteCaseSteps() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "deleteCaseSteps");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String caseStepIndex = logCaseDataBean.getCaseStepsIndex();
		//Modification for defect ESPRD00659250 Starts

		CaseStepsVO caseStepsVO = (CaseStepsVO) logCaseDataBean
				.getCaseStepsVOList().get(Integer.parseInt(caseStepIndex));

		//Modified for defect ESPRD00674473 starts
		//logCaseDataBean.getStepToRemove().add(caseStepsVO);
		logCaseDataBean.getDeletedStepsList().add(caseStepsVO);
		//Modified for defect ESPRD00674473 ends
		// Modification for defect ESPRD00659250 Ends
		logCaseDataBean.getCaseStepsVOList().remove(
				Integer.parseInt(caseStepIndex));
		ContactManagementHelper.setErrorMessage(
				MaintainContactManagementUIConstants.DEL_SUCCESS_MSG, null,
				null, null);
		//logCaseDataBean.setShowCaseStepsMessages(true);
		//Added for Defect ESPRD00792967.
		logCaseDataBean.setRenderedCaseStepsflag(false);
		logCaseDataBean.setShowCaseMessage(false); // Added for the defect ESPRD00735630_20DEC2011
		logCaseDataBean.setShowCaseStepsDelteMessage(true);
		logCaseDataBean.setShowAddCaseSteps(false);
		logCaseDataBean.setShowEditCaseSteps(false);
		if (logCaseDataBean.getCaseStepsVOList().isEmpty()) {
			logCaseDataBean.setShowCaseStepsDataTable(false);
		}
		logCaseDataBean.setDduExistedRecordErrorMsg(true); // Added for the defect id ESPRD00710222_12OCT2011
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This Method is used to View the Case steps details in EDIT (or) UPDATE
	 * JSP whenever the user clicks on the row in a Span in JSP.
	 * 
	 * @return String
	 */
	public String viewCaseStepsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "viewCaseStepsPage");
		// Major Save Code Starts

		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String strIndex = (String) map.get("rowindex");
		int stepsIndex = Integer.parseInt(strIndex);

		// Major Save Code Ends

		logCaseDataBean.setCaseStepsIndex(strIndex);
		CaseStepsVO caseStepsVO = (CaseStepsVO) logCaseDataBean
				.getCaseStepsVOList().get(stepsIndex);
		///Added for defect ESPRD00539435 starts/////
		if (caseStepsVO.getStatus().equals("A")) {
			// defect ESPRD00540227 started
			String completionBasedOn = logCaseDataBean.getCaseStepsVO()
					.getCompletedBasedOn();
			if (completionBasedOn != null
					&& !completionBasedOn
							.equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
				expecteCompletionDateForEditExtended(completionBasedOn);
			}
			//defect ESPRD00540227 ends
			HttpSession httpSession = (HttpSession) context
					.getExternalContext().getSession(true);
			log.debug("++caseStepsVO.getOrder()==="
					+ caseStepsVO.getOrder());
			if (caseStepsVO.getOrder() != null) {
				httpSession.setAttribute("CaseStepActiveOrder", caseStepsVO
						.getOrder());
			}
		}
		/////defect ESPRD00539435 ends//////
		//added for defect ESPRD00359439 Starts
		String notifiedCaseSk = caseStepsVO.getNotifyViaAlert();
		Integer caseStepSeqNum = caseStepsVO.getCaseStepSeqNum();//Integer.valueOf("1002");

		if (notifiedCaseSk != null && caseStepSeqNum != null) {
			if (notifiedViaAlertforCaseSteps(notifiedCaseSk, caseStepSeqNum)) {

				logCaseDataBean.setNotifyViaAlertFlag(true);
			} else {
				logCaseDataBean.setNotifyViaAlertFlag(false);
			}
		}
		// defect ESPRD00359439 ends
		if (StringUtils.isNotEmpty(caseStepsVO.getTemplate())) {
			logCaseDataBean.setEnableCaseStepsLetterLink(false);
		} else {
			logCaseDataBean.setEnableCaseStepsLetterLink(true);
		}
		logCaseDataBean.setShowCaseStepsMessages(false);
		CaseStepsVO tempCSVO = new CaseStepsVO();
		try {
			BeanUtils.copyProperties(tempCSVO, caseStepsVO);
		} catch (IllegalAccessException e) {
			log.error("Exception at viewCaseStepsPage()" + e);
		} catch (InvocationTargetException e) {
			log.error("Exception at viewCaseStepsPage()" + e);
		}
		logCaseDataBean.setCaseStepsVO(tempCSVO);
		logCaseDataBean.setShowEditCaseSteps(true);
		logCaseDataBean.setShowCaseStepsDelteMessage(false);
		logCaseDataBean.setShowAddCaseSteps(false);
		//added for ESPRD00340194,ESPRD00307362
		if (logCaseDataBean.getCaseStepsVO().getTemplate() != null
				&& !MaintainContactManagementUIConstants.EMPTY_STRING
						.equals(logCaseDataBean.getCaseStepsVO().getTemplate()
								.trim())) {
			logCaseDataBean.setCaseStepsCreateLetterStatus(true);
		} else {
			logCaseDataBean.setCaseStepsCreateLetterStatus(false);
		}//ESPRD00359439
		if (logCaseDataBean.getCaseStepsVO().getNotifyViaAlert() != null
				&& !MaintainContactManagementUIConstants.EMPTY_STRING
						.equals(logCaseDataBean.getCaseStepsVO()
								.getNotifyViaAlert().trim())) {
			logCaseDataBean.setDisableFields(false);
		} else {
			logCaseDataBean.setDisableFields(true);
		}//EOF ESPRD00359439 // below code added for defect ESPRD00362595

		if (logCaseDataBean.getCaseStepsVO().getStatus() != null
				&& logCaseDataBean
						.getCaseStepsVO()
						.getStatus()
						.equalsIgnoreCase(
								MaintainContactManagementUIConstants.CASE_STEP_STATUS_VOID_OR_SKIP)) {
			logCaseDataBean.setCaseStepVoided(true);
		} else {
			logCaseDataBean.setCaseStepVoided(false);
		} //EOF ESPRD00362595

		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "viewCaseStepsPage"); //EOF
												   // ESPRD00340194,ESPRD00307362
		
		UIComponent component = ContactManagementHelper
		.findComponentInRoot("caseStepsVOAuditId");
		log.debug(" caseStepsVO $$$$$$$$$$"+caseStepsVO.getAuditKeyList().size());
		if (component != null) {

			AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
			log.debug("inside component $$$$$$$$$$$"+caseStepsVO.getAuditKeyList().size());
			auditHistoryTable.setValue(caseStepsVO.getAuditKeyList());
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
					false);

		}
		//Add for the Defect ESPRD00773297
		logCaseDataBean.setRenderedCaseStepsflag(true);
		logCaseDataBean.setSaveCaseStepFlag(false);

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to show the Add page of Case Steps.
	 * 
	 * @return String
	 */
	public String renderAddCaseStepsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "renderAddCaseStepsPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowAddCaseSteps(true);
		// modified for defect ESPRD00371382

		CaseStepsVO csv = new CaseStepsVO();
		csv.setDateStarted(new Date());
		csv.setDateStartedStr(ContactManagementHelper.dateConverter(csv
				.getDateStarted()));
		logCaseDataBean.setCaseStepsVO(csv);
		//      modified for defect ESPRD00371382 EOF
		logCaseDataBean.setShowEditCaseSteps(false);
		logCaseDataBean.setShowCaseStepsDelteMessage(false);
		logCaseDataBean.setShowCaseStepsMessages(false);
		logCaseDataBean.setRenderedCaseStepsflag(true);
		/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
		logCaseDataBean.setSaveCaseStepFlag(false);
		/* Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end */
		/*
		 * Infinite Implementation for UC-PGM-CRM-018.7, Defect ESPRD00300221
		 * start
		 */
		logCaseDataBean.setDisableFields(true);
		/*
		 * Infinite Implementation for UC-PGM-CRM-018.7, Defect ESPRD00300221
		 * ends
		 */
		//     added for ESPRD00340194,ESPRD00307362
		if (logCaseDataBean.getCaseStepsVO().getTemplate() != null
				&& !MaintainContactManagementUIConstants.EMPTY_STRING
						.equals(logCaseDataBean.getCaseStepsVO().getTemplate()
								.trim())) {
			logCaseDataBean.setCaseStepsCreateLetterStatus(true);
		} else {
			logCaseDataBean.setCaseStepsCreateLetterStatus(false);
		}
		logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "renderAddCaseStepsPage");
		//EOF ESPRD00340194,ESPRD00307362
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to render the CANCEL add or update page of Case Steps
	 * 
	 * @return String
	 */
	public String cancelCaseStepsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "cancelCaseStepsPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setRenderedCaseStepsflag(false);
		logCaseDataBean.setShowCaseStepsMessages(false);//added for the defect
														// 540071
		logCaseDataBean.setShowCaseStepsDelteMessage(false);
		if (logCaseDataBean.isShowAddCaseSteps()) {
			logCaseDataBean.setShowAddCaseSteps(false);
		}
		if (logCaseDataBean.isShowEditCaseSteps()) {
			logCaseDataBean.setShowEditCaseSteps(false);
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to render the RESENT Case Steps page in JSP.
	 * 
	 * @return String.
	 */
	public String resetCaseStepsPage() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "resetCaseStepsPage");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (logCaseDataBean.isShowEditCaseSteps()) {
			CaseStepsVO stepsVO = null;
			CaseStepsVO tempStepVO = new CaseStepsVO();
			String index = logCaseDataBean.getCaseStepsIndex();

			stepsVO = (CaseStepsVO) logCaseDataBean.getCaseStepsVOList().get(
					Integer.parseInt(index));
			try {
				BeanUtils.copyProperties(tempStepVO, stepsVO);
			} catch (IllegalAccessException e) {
				log.error("IllegalAccessException ");
			} catch (InvocationTargetException e) {
				log.error("InvocationTargetException");
			}
			logCaseDataBean.setCaseStepsVO(tempStepVO);
			if (!isNullOrEmptyField(logCaseDataBean.getCaseStepsVO().getTemplate())) {
				logCaseDataBean.setCaseStepsCreateLetterStatus(true);
			} else {
				logCaseDataBean.setCaseStepsCreateLetterStatus(false);
			}
		} else {
			CaseStepsVO caseStepsVO = new CaseStepsVO();
			logCaseDataBean.setCaseStepsVO(caseStepsVO);
			caseStepsVO.setDateStarted(new Date());
			caseStepsVO.setDateStartedStr(ContactManagementHelper.dateConverter(caseStepsVO.getDateStarted()));
			logCaseDataBean.setCaseStepsCreateLetterStatus(Boolean.FALSE);
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This memthos is used to view the CR Associated with this case details
	 * 
	 * @return String
	 */
	public String viewCRAssociatedWithThisCase() {
		FacesContext fc = FacesContext.getCurrentInstance();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		Map map = fc.getExternalContext().getRequestParameterMap();
		String dispRowIndex = (String) map.get("indexCode");
		int index = new Integer(dispRowIndex).intValue();
		//int index = getCraWithThisCaseRowIndex().getRowIndex();
		logCaseDataBean.setCrAssociatedCaseRowIndex(index);
		AssociatedCorrespondenceVO correspondenceVO = (AssociatedCorrespondenceVO) logCaseDataBean
				.getCaseDetailsVO().getAssociatedCrList().get(index);
		logCaseDataBean.setAssociatedCorrespondenceVO(correspondenceVO);
		logCaseDataBean.setShowEditCRAssocWithThisCase(true);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to delete the Associated Correspondence records with
	 * this case
	 * 
	 * @return String
	 */
	public String deleteCRAssociatedWithThisCase() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		int crIndex = logCaseDataBean.getCrAssociatedCaseRowIndex();
		if (logCaseDataBean.getCaseDetailsVO().getAssociatedCrList() != null
				&& logCaseDataBean.getCaseDetailsVO().getAssociatedCrList()
						.size() > 0) {
			logCaseDataBean.getCaseDetailsVO().getAssociatedCrList().remove(
					crIndex);
		}
		deleteFlagForCorrRecAssoWithThisCase = true; // Added for the deleteSuccess message for the CorrRecAssoWithThisCase
		ContactManagementHelper
				.setErrorMessage(
						MaintainContactManagementUIConstants.CASE_ASSOC_WITH_THIS_RECORD_SUCCESS,
						null, null, null);
		logCaseDataBean.setShowEditCRAssocWithThisCase(false);
		if (logCaseDataBean.getCrAssociatedWithCaseInfoList().isEmpty()) {
			logCaseDataBean.setShowCRAssociatedWithCaseDataTable(false);
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to cloase the CRAssocWithThisCase page
	 * 
	 * @return String
	 */
	public String renderCancelCRAssoWithThisCase() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowEditCRAssocWithThisCase(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This memthos is used to view the Case Associated with this case details
	 * 
	 * @return String
	 */
	public String viewCaseAssociatedWithThisCase() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		/*
		 * String strIndex = (String) map.get("rowindex");//commented for
		 * UC-PGM-CRM-018_ESPRD00388733_19JAN10 int index =
		 * Integer.parseInt(strIndex); AssociatedCaseVO caseVO =
		 * (AssociatedCaseVO) logCaseDataBean
		 * .getCaseDetailsVO().getAssociatedCaseList().get(index);
		 */
		int index = logCaseDataBean.getCaseRecordsWithThisCaeRowIndex();

		AssociatedCaseVO caseVO = (AssociatedCaseVO) logCaseDataBean
				.getCaseRecordsAssoWithCaseList().get(index);
		//for ESPRD00741245
		logCaseDataBean.setPageFocusId("viewCaseRecordAssctdHeader");
		logCaseDataBean.setCursorFocusValue("");
		//EOF UC-PGM-CRM-018_ESPRD00388733_19JAN10
		logCaseDataBean.setViewCaseAssociatedWithThisCaseIndex(index);//UC-PGM-CRM-018.3_ESPRD00328556_07NOV09
		logCaseDataBean.setAssociatedCaseVO(caseVO);
		logCaseDataBean.setShowEditAssocCaseWithThisCase(true);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to delete the Associated Case records with this case
	 * 
	 * @return String
	 */
	public String deleteCaseAssociatedWithThisCase() {

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (logCaseDataBean.getViewCaseAssociatedWithThisCaseIndex() >= 0
				&& logCaseDataBean.getViewCaseAssociatedWithThisCaseIndex() < logCaseDataBean
						.getCaseRecordsAssoWithCaseList().size()) {

			Object removedObj = null;
			removedObj = logCaseDataBean.getCaseRecordsAssoWithCaseList()
					.remove(
							logCaseDataBean
									.getViewCaseAssociatedWithThisCaseIndex());
			if (removedObj != null) {
				logCaseDataBean.getExistingCaseRecordsList().add(removedObj);
			}
			logCaseDataBean.setViewCaseAssociatedWithThisCaseIndex(-1);

			if (logCaseDataBean.getCaseRecordsWithThisCaeRowIndex() != -1)
				logCaseDataBean.setCaseRecordsWithThisCaeRowIndex(0);

			//    	UC-PGM-CRM-018.3_ESPRD00328556_10NOV09

			if (logCaseDataBean.getExistingCaseSearchRowIndex() != -1)
				logCaseDataBean.setExistingCaseSearchRowIndex(0);
			logCaseDataBean.setShowExistingCaseRecordsDataTable(true);
			deleteFlagForCaseRecAssoWithThisCase = true;
			logCaseDataBean.setShowEditAssocCaseWithThisCase(false);
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.CASE_ASSOC_WITH_THIS_RECORD_SUCCESS,
							"LCSEASCTCASEDIVHDN",
							null, "Add");
			//EOF UC-PGM-CRM-018.3_ESPRD00328556_10NOV09
		}
		//EOF UC-PGM-CRM-018.3_ESPRD00328556_07NOV09

		if (logCaseDataBean.getCaseRecordsAssoWithCaseList().isEmpty()) {
			logCaseDataBean.setShowCaseRecordsAssoWithCaseDataTable(false);
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to cloase the CaseAssocWithThisCase page
	 * 
	 * @return String
	 */
	public String renderCancelCaseAssoWithThisCase() {
		logCaseDataBean.setShowEditAssocCaseWithThisCase(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Routing span information based on sorted
	 * column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortRoutingInfo(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);

		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setRoutingRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortCaseRoutingComparator(sortColumn, sortOrder,
				logCaseDataBean.getRoutingInfoList());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Additional Case Entites span information
	 * based on sorted column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortAdditionalCaseEntites(ActionEvent event) {
		log.debug("sortAdditionalCaseEntites is started " + event);
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean.setAddiCaseEntityRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortAdditionalCaseEntites(sortColumn, sortOrder,
				logCaseDataBean.getCaseDetailsVO().getInqAbtEntityList());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Existing CR span information based on sorted
	 * column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortExistingCR(ActionEvent event) {
		log.debug("sortExistingCR is started " + event);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort CR Associated With This Case span information
	 * based on sorted column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortCRAssociatedWithThisCase(ActionEvent event) {
		log.debug("sortCRAssociatedWithThisCase is started " + event);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Alternate Identifiers for Provider span
	 * information based on sorted column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortAltIdenForProv(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		log.debug("The Sort column is @ sortAltIdenForProv--->" + sortColumn);
		log.debug("the sort order is @ sortAltIdenForProv--->" + sortOrder);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setAltIdentifiersProvRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortCaseRegardingProviderComparator(sortColumn, sortOrder,
				logCaseDataBean.getCaseEventsVOList());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Alternate Identifiers for Member span
	 * information based on sorted column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortAltIdenForMem(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);

		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setAltIdentifiersMemRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortCaseRegardingMemberComparator(sortColumn, sortOrder,
				logCaseDataBean.getAlternateIdentiMemList());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Correspondence Search results span
	 * information based on sorted column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortCRSearchResults(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean.setHtmlSearchResults(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortCrSearchRecords(sortColumn, sortOrder, logCaseDataBean
				.getSearchResults());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Existing Case span information based on
	 * sorted column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortExistingCase(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean.setCaseExistingCaseRecordsRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortExistingCaseComparator(sortColumn, sortOrder,
				logCaseDataBean.getExistingCaseRecordsList());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Case records associated with this case span
	 * information based on sorted column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortCaseRecordsAssoWithCase(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean.setCaseRecordsWithThisCaeRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortAssocCaseRecords(sortColumn, sortOrder, logCaseDataBean
				.getCaseRecordsAssoWithCaseList());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Case Events span information based on sorted
	 * column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortCaseEvents(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean.setCaseEventsRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortCaseEventsComparator(sortColumn, sortOrder,
				logCaseDataBean.getCaseEventsVOList());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Case Steps span information based on sorted
	 * column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortCaseSteps(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean.setCaseStepsRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortCaseStepsComparator(sortColumn, sortOrder,
				logCaseDataBean.getCaseStepsVOList());
		logCaseDataBean.setPageFocusId("addCaseStepFocusPage");
		logCaseDataBean.setCursorFocusValue("");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Case Alerts span information based on sorted
	 * column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortCaseAlerts(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean.setAlertsRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortCaseAlertsComparator(sortColumn, sortOrder,
				logCaseDataBean.getAlertsVOList());
		return MaintainContactManagementUIConstants.SUCCESS;

	}

	/**
	 * This method is used to sort Case Attachments span information based on
	 * sorted column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	public String sortCaseAttachmentsInfo(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean.setAttachmentsRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortCaseAttachmentsComparator(sortColumn, sortOrder,
				logCaseDataBean.getAttachmentVOList());
		logCaseDataBean.setCursorFocusValue("openDocumentRepositoryId");
		logCaseDataBean.setPageFocusId("caseAttachmentsHeader");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to get the existing CR records
	 * 
	 * @return String
	 */
	public String getExistingCRRecords() {

		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");

		int crSearchIndex = 0;
		if (indexCode != null) {
			crSearchIndex = Integer.parseInt(indexCode);
		}

		log.debug("CR Search results index is " + crSearchIndex);
		CorrespondenceSearchResultsVO resultsVO = null;
		AssociatedCorrespondenceVO associatedCorrespondenceVO = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		log.debug("Search Results Size"
				+ logCaseDataBean.getSearchResults().size());

		if (logCaseDataBean.getSearchResults().size() > 0) {
			resultsVO = (CorrespondenceSearchResultsVO) logCaseDataBean
					.getSearchResults().get(crSearchIndex);
			List existingCRList = logCaseDataBean.getExistingCRInfoList();
			boolean recordAlreadyAdded = false;

			int liSize = 0;
			if (resultsVO != null) {
				if (!existingCRList.isEmpty()) {
					AssociatedCorrespondenceVO existingCRVO = null;
					liSize = existingCRList.size();
					for (int i = 0; i < liSize; i++) {
						existingCRVO = (AssociatedCorrespondenceVO) existingCRList
								.get(i);
						if (existingCRVO.getCorrespondenceRecordNumber()
								.equals(
										resultsVO.getCorrespondenceNumber()
												.toString())) {
							recordAlreadyAdded = true;

							break;
						}
					}
				}
				if (!recordAlreadyAdded) {
					associatedCorrespondenceVO = new AssociatedCorrespondenceVO();
					associatedCorrespondenceVO
							.setCorrespondenceRecordNumber(resultsVO
									.getCorrespondenceNumber().toString());
					if (resultsVO.getCreatedOn() != null) {
						associatedCorrespondenceVO
								.setOpenDate(ContactManagementHelper
										.dateConverter(resultsVO.getCreatedOn()));
					}
					associatedCorrespondenceVO.setStatus(resultsVO.getStatus());
					associatedCorrespondenceVO.setCategory(resultsVO
							.getCategoryDescription());
					associatedCorrespondenceVO.setSubject(resultsVO
							.getSubjectCode());
					associatedCorrespondenceVO.setContact(resultsVO
							.getEntityName());

					logCaseDataBean.getExistingCRInfoList().add(
							associatedCorrespondenceVO);
					logCaseDataBean.setShowExistingCRDataTable(true);
					logCaseDataBean.setShowExistingCaseRecordsDataTable(true);//ESPRD00610465
																			  // defect
				}
			}
		}
		return "";
	}

	public String selectAllAssoCRs(ValueChangeEvent event) {

		return "";
	}

	/**
	 * This method is used to send the IPC details to the EDMS
	 * 
	 * @return String
	 */
	public String openDocumentRepository() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "openDocumentRepository");
		EnterpriseEDMSSearchCriteriaVO edmsSearchCriteiaVo = new EnterpriseEDMSSearchCriteriaVO();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setNavToOtherPortletFlag(true);
		saveCaseRecord();
		CaseDetailsVO caseDetailsVO = logCaseDataBean.getCaseDetailsVO();
		/* FIND BUGS_FIX */
		if (caseDetailsVO.getCaseSK() != null
				&& !caseDetailsVO.getCaseSK().equals(Long.valueOf(0))) {
			edmsSearchCriteiaVo.setModuleName("Contact Management");
			edmsSearchCriteiaVo.setFuncAreaCode("G1");
			edmsSearchCriteiaVo
					.setActionParameterName("logCaseEDMSSearchResultsList");
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().getRequestMap().put(
					"EDMSSearchCriteria", edmsSearchCriteiaVo);
			logCaseDataBean.setShowAddAttachments(false);
		}
		return "";
	}

	/**
	 * This method is used to send the IPC details to the EDMS
	 * 
	 * @return String
	 */
	public String openAddDocumentRepository() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "openAddDocumentRepository");
		EnterpriseEDMSSearchCriteriaVO edmsSearchCriteiaVo = new EnterpriseEDMSSearchCriteriaVO();
		edmsSearchCriteiaVo.setModuleName("Contact Management");
		edmsSearchCriteiaVo.setFuncAreaCode("G1");
		edmsSearchCriteiaVo
				.setActionParameterName("logCaseEDMSSearchResultsList");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getRequestMap().put(
				"EDMSSearchCriteria", edmsSearchCriteiaVo);
		return "";
	}

	/* Added For the DEfect ID ESPRD00357896 */

	private void getRoutingUsers(Long deptSK) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getRoutingUsers");
		log.debug("$$$$$ dept sk is::::::" + deptSK);
		//List userData = null; // Commented the line for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
		List deptList = new ArrayList();

		String strCaseType = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (logCaseDataBean.getCaseDetailsVO() != null) {
			strCaseType = logCaseDataBean.getCaseDetailsVO().getCaseType();
		}
		deptList.add(deptSK);

		EnterpriseUser enterpriseUser = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = 
			new ContactMaintenanceDelegate(); // Modified the delegate call for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
		// Begin - Modified the code for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
		//List users = new ArrayList();
		List listOfDeptUsers = logCaseDataBean.getRoutingUserList();
		try
        {
            if(listOfDeptUsers!=null)
            {
             listOfDeptUsers.clear();
            }
            Map userDescMap = new HashMap();
            List sortUserDesc = new ArrayList();
		    if(deptSK != null)
		    {
		       	log.info("departmentSK=========="+deptSK);
		        List deptUsersList = contactMaintenanceDelegate.getUsersForDepartment(deptSK);
		        log.info("deptUsersList======="+deptUsersList.size());
		        for (Iterator iterator = deptUsersList.iterator(); iterator
		                .hasNext();)
		        {
		            Object[] userData = (Object[]) iterator.next();
		            String userDesc = null;
		            if (userData[3]!= null && !isNullOrEmptyField(userData[3].toString()))
		            {
		            	userDesc = userData[3].toString();
		            }
		            if((userData[3] != null && !isNullOrEmptyField(userData[3].toString()))
		            		&&	(userData[2] != null && !isNullOrEmptyField(userData[2].toString())))
		            {
		            	userDesc = userDesc 
		                + ContactManagementConstants.COMMA_SEPARATOR
		                + ContactManagementConstants.SPACE_STRING;
		            }
		            if (userData[2] != null && !isNullOrEmptyField(userData[2].toString()))
		            {
		            	userDesc = userDesc + userData[2].toString();
		            }
		            log.debug("User Name " + userDesc);
		            StringBuffer userLabel = new StringBuffer().append(
		            		userDesc).append(
		                    ContactManagementConstants.HYPHEN_SEPARATOR).append(
		                    		userData[1].toString());
		            sortUserDesc.add(userLabel.toString());
		            userDescMap.put(userLabel.toString(),userData[0].toString());
		        }
		        Collections.sort(sortUserDesc,getStringComerator());
		        for (Iterator itr = sortUserDesc.iterator(); itr.hasNext();)
		        {
		        	Object obj = itr.next();
		        	listOfDeptUsers.add(new SelectItem(userDescMap.get(obj.toString()).toString(), obj.toString()));
		        }
		        userDescMap.clear();
		        sortUserDesc.clear();
		    }
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            log.error("e.getMessage(), e");
        }
		/*try {
			userData = delegate.getUserOfDepartment(deptSK);
			if (!userData.isEmpty()) {
				int size = 0;
				log.debug("user List Size for routing in controller"
						+ userData.size());
				size = userData.size();
				StringBuffer sbName = null; //ESPRD00513530_ESPRD00513532_19AUG2010
				for (int i = 0; i < size; i++) {
					enterpriseUser = (EnterpriseUser) userData.get(i);
					if (enterpriseUser.getFirstName() != null) {
						firstName = enterpriseUser.getFirstName();
						lastName = enterpriseUser.getLastName();
						//ESPRD00513530_ESPRD00513532_19AUG2010
						
						 * name = firstName +
						 * MaintainContactManagementUIConstants.EMPTY_STRING_SPACE +
						 * lastName;
						 
						sbName = new StringBuffer();
						if (lastName != null
								&& !MaintainContactManagementUIConstants.EMPTY_STRING
										.equals(lastName)) {
							sbName
									.append(lastName)
									.append(",")
									.append(
											MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
						}
						sbName
								.append(firstName)
								.append(
										MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
								.append(
										MaintainContactManagementUIConstants.HYPHEN);
						sbName
								.append(
										MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
								.append(enterpriseUser.getUserID());
						name = sbName.toString();

						//EOF ESPRD00513530_ESPRD00513532_19AUG2010
						users.add(new SelectItem(enterpriseUser
								.getUserWorkUnitSK().toString(), name));
					}
				}
			}
		} catch (LOBHierarchyFetchBusinessException e) {
			log.error("Error while getting routing user list");
			e.printStackTrace();
		}
		//      DEFECT ESPRD00560376 27 JAN 2011
		Comparator comparator = new Comparator() {

			public int compare(Object obj1, Object obj2) {

				SelectItem slctItem1 = (SelectItem) obj1;

				SelectItem slctItem2 = (SelectItem) obj2;

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

		Collections.sort(users, comparator);*/
		//END DEFECT ESPRD00560376 27 JAN 2011
		logCaseDataBean.setRoutingUserList(listOfDeptUsers);
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getRoutingUsers");
		// End - Modified the code for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011		
	}

	/**
	 * When the user clicks on Continue button as part of case Details table.
	 * 
	 * @return String
	 */
	public String submitAddAppealDetails() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "submitAddAppealDetails");
		Object obj = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		log.debug("obj" + obj.getClass().getName());
		CommonCMCaseDetailsVO commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setNavToOtherPortletFlag(true);

		String entityType = logCaseDataBean.getEntityType();
		saveCaseRecord();
		log.debug("++ back to submitAddAppealDetails--" + entityType);
		CaseType caseType = null;
		log.debug("--validateFlag::" + validateFlag);
		if (validateFlag) {

			CaseDetailsVO caseDetailsVO = logCaseDataBean.getCaseDetailsVO();

			try {

				log.info("Insdie try block");
				caseType = new ContactMaintenanceDelegate() // Added by ICS
						.getCaseTypeDetails(caseDetailsVO.getCaseSK());
			} catch (CaseTypeFetchBusinessException e) {
				log.info("1");
				e.printStackTrace();
			} catch (Exception e) {
				log.info("2");
				e.printStackTrace();
			}

			if (ContactManagementConstants.ENTITY_TYPE_MEM.equals(entityType)) {

				if (caseDetailsVO.getCaseSK() != null
						&& !caseDetailsVO.getCaseSK().equals(Long.valueOf(0))) {

					commonCMCaseDetailsVO.setCaseSK(caseDetailsVO.getCaseSK()
							.toString());

					if (caseType != null) {
						commonCMCaseDetailsVO.setCaseType(caseType
								.getShortDescription());

					}

					commonCMCaseDetailsVO.setEntityType(logCaseDataBean
							.getEntityType());

					//					commonCMCaseDetailsVO.setEntityID(logCaseDataBean.getEntityID());
					commonCMCaseDetailsVO.setEntityID(logCaseDataBean
							.getCaseRegardingVO().getCaseRegardingMemberVO()
							.getMemberId());
					commonCMCaseDetailsVO.setName(logCaseDataBean
							.getCaseRegardingVO().getCaseRegardingMemberVO()
							.getName());

					getRequestScope().put("AddAppealDetails",
							commonCMCaseDetailsVO);
				}
			} else if (ContactManagementConstants.ENTITY_TYPE_PROV
					.equals(entityType)) {
				log.info("++Inside tactManagementConstants.ENTITY_TYPE_PROV");
				/* FIND BUGS_FIX */
				if (caseDetailsVO.getCaseSK() != null
						&& !caseDetailsVO.getCaseSK().equals(Long.valueOf(0))) {
					log.debug("++Inside tactManagementConstants.ENTITY_TYPE_PROV::"
									+ caseDetailsVO.getCaseSK().toString());
					commonCMCaseDetailsVO.setCaseSK(caseDetailsVO.getCaseSK()
							.toString());

					commonCMCaseDetailsVO.setEntityType(logCaseDataBean
							.getEntityType());

					commonCMCaseDetailsVO.setEntityID(logCaseDataBean
							.getCaseRegardingVO().getCaseRegardingProviderVO()
							.getProviderId());

					commonCMCaseDetailsVO.setName(logCaseDataBean
							.getCaseRegardingVO().getCaseRegardingProviderVO()
							.getName());
					if (caseType != null) {
						commonCMCaseDetailsVO.setCaseType(caseType
								.getShortDescription());

					}

					log.info("++befor doing IPC");

					getRequestScope().put("AddAppealDetails",
							commonCMCaseDetailsVO);
				}

			} else {

				log.info("Inside other than P & M");
				/* FIND BUGS_FIX */
				if (caseDetailsVO.getCaseSK() != null
						&& !caseDetailsVO.getCaseSK().equals(Long.valueOf(0))) {

					commonCMCaseDetailsVO.setCaseSK(caseDetailsVO.getCaseSK()
							.toString());
					if (logCaseDataBean.getEntityType() != null) {
						commonCMCaseDetailsVO.setEntityType(logCaseDataBean
								.getEntityType());
					}

					if (logCaseDataBean.getEntityID() != null) {
						commonCMCaseDetailsVO.setEntityID(logCaseDataBean
								.getEntityID());
					}//DEFECT 542894 24 JAN 2011
					else if (!logCaseDataBean.isShowTPLlabel()
							&& !logCaseDataBean.isShowTPLpolcyHldrlabel()) {
						if (logCaseDataBean.getCaseRegardingVO() != null
								&& logCaseDataBean.getCaseRegardingVO()
										.getCaseRegardingTPLVO() != null
								&& logCaseDataBean.getCaseRegardingVO()
										.getCaseRegardingTPLVO().getEntityId() != null) {
							log.debug("-----logCaseDataBean.getCaseRegardingVO().getCaseRegardingTPLVO().getEntityId()------"
											+ logCaseDataBean
													.getCaseRegardingVO()
													.getCaseRegardingTPLVO()
													.getEntityId());
							commonCMCaseDetailsVO.setEntityID(logCaseDataBean
									.getCaseRegardingVO()
									.getCaseRegardingTPLVO().getEntityId());
						}
					}

					if (caseType != null) {
						commonCMCaseDetailsVO.setCaseType(caseType
								.getShortDescription());

					}
					if (logCaseDataBean.isShowCaseRegardingUnEnrolMem()
							|| logCaseDataBean.isShowCaseRegardingUnEnrolProv()
							|| logCaseDataBean.isShowCaseRegardingOther()) {
						commonCMCaseDetailsVO.setName(logCaseDataBean
								.getCaseRegardingVO().getCaseRegardingTPLVO()
								.getName());
					}
					log.debug("--other----commonCMCaseDetailsVO.getEntityType():"
									+ commonCMCaseDetailsVO.getEntityType());
					log.debug("--other----commonCMCaseDetailsVO.getEntityID():"
									+ commonCMCaseDetailsVO.getEntityID());

					getRequestScope().put("AddAppealDetails",
							commonCMCaseDetailsVO);
				}

			}
		}

		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "submitAddAppealDetails");
		return "";
	}

	/**
	 * Added By Madhurima Add response IPc
	 * 
	 * @return Srring
	 */
	public String submitCaseAddResponse() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "submitCaseAddResponse");
		saveCaseRecord();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseDetailsVO caseDetailsVO = logCaseDataBean.getCaseDetailsVO();
		/* FIND BUGS_FIX */
		if (caseDetailsVO.getCaseSK() != null
				&& !caseDetailsVO.getCaseSK().equals(Long.valueOf(0))) {
			String caseSK = caseDetailsVO.getCaseSK().toString();
			String entId = logCaseDataBean.getEntityID();
			String entType = logCaseDataBean.getEntityType();
			String caseDetails = caseSK + ":" + entId + "-" + entType;
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().getRequestMap().put(
					"LetterGenerationRequest", caseDetails);
		}
		return "";
	}

	public String deleteInquiringAbout() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "deleteInquiringAbout");
		int tableIndex;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		//modified for defect ESPRD00358412
		tableIndex = logCaseDataBean.getSelectedAdditionaCaseEntityIndex();
		Object inqAbtEntityObj = logCaseDataBean.getCaseDetailsVO()
				.getInqAbtEntityList().get(tableIndex);

		boolean isRecordAddedBeforeSave = false;
		/** inqB4Index variable will capture the 
		 * row index of session level inquiry data table before major save  
		 */
		 // Added code for the Defect ESPRD00800608 starts
		int inqB4Index=0;
		 // Added code for the Defect ESPRD00800608 Ends
		Iterator InqAbtEntityListBeforeSave_iter = logCaseDataBean
				.getInqAbtEntityListBeforeSave().iterator();
		Object itr_obj = null;

		CaseRegardingMemberVO caseRegardingMemberVO = null;
		CaseRegardingProviderVO regardingProviderVO = null;
		CaseRegardingTPL caseRegarding = null;

		while (InqAbtEntityListBeforeSave_iter.hasNext()) {

			itr_obj = InqAbtEntityListBeforeSave_iter.next();

			if (inqAbtEntityObj instanceof CaseRegardingMemberVO
					&& itr_obj instanceof CaseRegardingMemberVO) {

				caseRegardingMemberVO = (CaseRegardingMemberVO) itr_obj;

				if (((CaseRegardingMemberVO) inqAbtEntityObj).getEntityId()
						.trim().equals(
								caseRegardingMemberVO.getEntityId().trim())) {
					isRecordAddedBeforeSave = true;
					break;
				}
			} else if (inqAbtEntityObj instanceof CaseRegardingProviderVO
					&& itr_obj instanceof CaseRegardingProviderVO) {
				regardingProviderVO = (CaseRegardingProviderVO) itr_obj;

				if (((CaseRegardingProviderVO) inqAbtEntityObj).getEntityId()
						.trim()
						.equals(regardingProviderVO.getEntityId().trim())) {
					isRecordAddedBeforeSave = true;
					break;
				}
			} else if (inqAbtEntityObj instanceof CaseRegardingTPL
					&& itr_obj instanceof CaseRegardingTPL) {
				caseRegarding = (CaseRegardingTPL) itr_obj;

				if (((CaseRegardingTPL) inqAbtEntityObj).getEntityId().trim()
						.equals(caseRegarding.getEntityId().trim())) {
					isRecordAddedBeforeSave = true;
					break;
				}
			} else {
				caseRegarding = (CaseRegardingTPL) itr_obj;
				if (((CaseRegardingTPL) inqAbtEntityObj).getEntityId().trim()
						.equals(caseRegarding.getEntityId().trim())) {
					isRecordAddedBeforeSave = true;
					break;
				}
			}
			++inqB4Index;  // Added code for the Defect ESPRD00800608 
		}
		log.debug("isRecordAddedBeforeSave: " + isRecordAddedBeforeSave);
		if (isRecordAddedBeforeSave) {
			try{
			Iterator itr = logCaseDataBean.getProviderhos().iterator();
			if (inqAbtEntityObj instanceof CaseRegardingProviderVO) {

				String entityId = ((CaseRegardingProviderVO) inqAbtEntityObj)
						.getEntityId();
				int indx = 0;
				while (itr.hasNext()) {
					SelectItem sitem = (SelectItem) itr.next();

					if (entityId != null
							&& sitem.getValue() != null
							&& entityId.trim().equals(
									sitem.getValue().toString().trim())) {
						logCaseDataBean.getProviderhos().remove(indx);
						break;
					}
					indx++;
				}
			}
			//Added for the defect ESPRD00868002 Starts
			else if (inqAbtEntityObj instanceof CaseRegardingTPL) {
				String entityId = ((CaseRegardingTPL) inqAbtEntityObj)
						.getEntityId();
				int indx = 0;
				while (itr.hasNext()) {
					SelectItem sitem = (SelectItem) itr.next();

					if (entityId != null
							&& sitem.getValue() != null
							&& entityId.trim().equals(
									sitem.getValue().toString().trim())) {
						logCaseDataBean.getProviderhos().remove(indx);
						break;
					}
					indx++;
				}
			}
			else{
				log.info(" other than Provider & UnenrlordProvider loop ::");
			}
			//Defect ESPRD00868002 ends
			logCaseDataBean.getCaseDetailsVO().getInqAbtEntityList().remove(
					tableIndex);
			// Added for Defect ESPRD00660888
			logCaseDataBean.getInqAbtEntityListBeforeSave().remove(inqB4Index);   // modified code for the Defect ESPRD00800608 
			//Defect ESPRD00660888 Ends

			logCaseDataBean.setAddiCaseEntityRowIndex(0);
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.DELETE_SUCCESS, null,
					null, null);
			}
			catch(Exception e){
				if(log.isErrorEnabled()){
					log.error(" Exception due to :::"+ e);
				}
			}
		} else {
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.ADDITIONAL_CASE_ASSOCIATED_CANNOT_REMOVE,
							null, null, null);
		}

		//EOF ESPRD00358412
		//Added by ICS for 2105

		logCaseDataBean.setShowDeleteMessage(true);
		
		/**
		 * Due to this flag, display the error message at page level
		 */
		logCaseDataBean.setLogCaseErrMsgFlag(true); // Added code for the Defect ESPRD00800608 
		
		if (logCaseDataBean.getCaseDetailsVO().getInqAbtEntityList().isEmpty()) {
			logCaseDataBean.setShowAddiCaseEntitesDataTable(false);
		}
		executeBRCON307(); //ESPRD00517164_UC-PGM-CRM-18_06SEP2010

		cancelInquiringAbout();
		return MaintainContactManagementUIConstants.EMPTY_STRING;
	}

	public String cancelInquiringAbout() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (logCaseDataBean.isShowInqAbtMember()) {
			logCaseDataBean.setShowInqAbtMember(false);
		} else if (logCaseDataBean.isShowInqAbtProvider()) {
			logCaseDataBean.setShowInqAbtProvider(false);
		} else if (logCaseDataBean.isShowInqAbtFor()) {
			logCaseDataBean.setShowInqAbtFor(false);
		}
		return "";
	}

	/**
	 * @return Returns the requestScope.
	 */
	public Map getRequestScope() {
		setRequestScope(null);
		return requestScope;
	}

	/**
	 * @param requestScope
	 *            The requestScope to set.
	 */
	public void setRequestScope(Map requestScope) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		requestScope = (Map) facesContext.getApplication().createValueBinding(
				"#{requestScope}").getValue(facesContext);
		this.requestScope = requestScope;
	}

	/**
	 * This method is called from Maintain Appeals Portlet on click of Case
	 * Record Details.
	 * 
	 * @return String
	 */
	public String getCMLoCaseDetails() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getCMLoCaseDetails");
		String returnMsg = "";
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		commonNotesControllerBean = (CommonNotesControllerBean) getDataBean(ContactManagementConstants.COMMON_NOTES_CONTROLLER_BEAN);
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();

		if (map == null) {
			returnMsg = MaintainContactManagementUIConstants.FAILURE;
		} else {
			Long caseNum = null;
			String caseID = map.get("caseID").toString();
			String entityID = map.get("entityID").toString();
			String entityType = map.get("entityType").toString();

			logCaseDataBean.setActionCode("CaseUpdate");
			logCaseDataBean.setEntityID(entityID);
			logCaseDataBean.setEntityType(entityType);
			if (caseID != null) {
				caseNum = Long.valueOf(caseID);

				logCaseDataBean.setCancelLinkUpdate(true);
				getCaseTypes();
				getEntities(entityID, entityType, false, false);
				populateInitialDataForUpdate();

				getCaseRecord(caseNum);
				getCFElementsInUpdate();
				commonNotesControllerBean.showNotes();
				returnMsg = MaintainContactManagementUIConstants.SUCCESS;
			}
		}
		return returnMsg;
	}

	/**
	 * It will Submit CaseDetails.
	 *  
	 */

	public void submitCaseDetails() {
		log.debug("=inside submitCaseDetails==");
		/** Holds facesContext */
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		CommonCMCaseDetailsVO commonCMCaseDetailsVO = null;
		String caseSK = null;
		String entityType = null;
		String entityID = null;
		String preView=null;

		if (map != null) {
			caseSK = map.get("caseID").toString();
			entityType = map.get("entityType").toString();
			entityID = map.get("entityID").toString();
			//Added for Defect ESPRD00828394 Starts
			preView=(String) map.get("preView");
			if(preView!=null){
			UIViewRoot viewRoot = (UIViewRoot) facesContext.getViewRoot();	
			 viewRoot.setViewId(preView); 
			 facesContext.setViewRoot(viewRoot);
			}
			//Defect ESPRD00828394 Ends
			//ESPRD00529115 starts
			CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
			EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
			CaseDelegate caseDelegate = new CaseDelegate();
			if (caseSK != null) {
				caseRecordSearchCriteriaVO.setCaseSK(caseSK);
				try {
					enterpriseSearchResultsVO = caseDelegate
							.getCaseRecords(caseRecordSearchCriteriaVO);
					if (enterpriseSearchResultsVO.getSearchResults() != null
							&& enterpriseSearchResultsVO.getSearchResults()
									.size() == 1) {
						CaseRecordSearchResultsVO searchResultsVO = (CaseRecordSearchResultsVO) enterpriseSearchResultsVO
								.getSearchResults().get(0);
						commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
						log.debug("+++++++caseSK " + caseSK);
						commonCMCaseDetailsVO.setCaseSK(caseSK);
						entityType = searchResultsVO.getEntityType();
						log.debug("+++++++entityType " + entityType);
						/* FIND BUGS_FIX */
						if (searchResultsVO.getCommonEntitySK() != null
								&& !searchResultsVO.getCommonEntitySK().equals(
										Long.valueOf(0))) {
							entityID = searchResultsVO.getCommonEntitySK()
									.toString();
							log.debug("+++++++entityID " + entityID);
						}
						commonCMCaseDetailsVO.setEntityType(entityType);
						commonCMCaseDetailsVO.setEntityID(entityID);
						commonCMCaseDetailsVO.setActionCode("CaseUpdate");
						showCasePortlet(commonCMCaseDetailsVO);
					}
				} catch (CaseRecordFetchBusinessException e) {
					e.printStackTrace();
				}

				//--ESPRD00529115 ends
			}
		}

	}

	/**
	 * It will opens the Log Case screen.
	 * 
	 * @param commonCMCaseDetailsVO
	 *            holds the case details.
	 */
	public void showCasePortlet(CommonCMCaseDetailsVO commonCMCaseDetailsVO) {
		log.debug("== inside showCasePortlet=");
		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.getExternalContext().getRequestMap().put("CaseDetails",
				commonCMCaseDetailsVO);
	}

	/**
	 * Added By MAdhurima
	 */
	public String associateCorrespondence() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "associateCorrespondence");
		//  Added the code for the defect ESPRD00688792
//		saveCaseRecord();

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setNavToOtherPortletFlag(true);
		logCaseDataBean.setNavigatedToCorrespondence(true); //  Added the code for the defect ESPRD00688792
		CaseDetailsVO caseDetailsVO = logCaseDataBean.getCaseDetailsVO();
		/* FIND BUGS_FIX */
		if (caseDetailsVO.getCaseSK() != null
				&& !caseDetailsVO.getCaseSK().equals(Long.valueOf(0))) {
			String entID = null;
			String caseSk=caseDetailsVO.getCaseSK().toString(); //  Added the code for the defect ESPRD00688792
//			String entID = logCaseDataBean.getEntityID();
			String entType = logCaseDataBean.getEntityType();
			log.debug("@@@@@--entType--++++++++++"+entType);
			// Modified for the defect ESPRD00688792 start
			if(entType != null){
				if(entType.equals("UP")){
					entID = logCaseDataBean.getSysIdForUP();
					if(entID == null || entID.equals(ContactManagementConstants.EMPTYSTRING)){
						entID = logCaseDataBean.getCaseRegardingVO().getCaseRegardingTPLVO().getEntityId();
					}else{
						entType = "UA";
					}
						
				}
				else if(entType.equals("TD")){
					 entID = logCaseDataBean.getCaseRegardingVO().getCaseRegardingTradingPartnerVO().getEntityId();
				}else if(entType.equals("P")|| entType.equals("M")){
					entID = logCaseDataBean.getEntityID();
				}else{
					entID = logCaseDataBean.getCaseRegardingVO().getCaseRegardingTPLVO().getEntityId();
				}
			}
			
			//Modified for the defect ESPRD00688792 end
			String caseDetails = entID + ":" + entType+"$"+caseSk;
			log.debug("@@@@@--caseDetails--@@@@@@@@"+caseDetails);
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().getRequestMap().put("AssociateCaseData",
					caseDetails);

		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * showing audit child history for Alert details.
	 * 
	 * @return String
	 */
	public String showAlertAuditHistory() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "showAlertAuditHistory");
		GlobalAuditsDelegate audit;
		final List list = new ArrayList();
		ArrayList audlist = new ArrayList();
		Alert alertDO = new Alert();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		try {

			log.debug("in show child audit history");

			audit = new GlobalAuditsDelegate();
			LogCaseVOToDomainConverter converter = new LogCaseVOToDomainConverter();
			//Gets Child Domain Object
			CaseRecord caseRecord = converter
					.convertCaseRecordVOToDO(logCaseDataBean.getCaseDetailsVO());
			alertDO = converter.convertAlertVOToDO(
					logCaseDataBean.getAlertVO(), caseRecord);

			log.debug("alertSK ::::" + alertDO.getAlertSK());

			list.add(alertDO);

			HashMap hm = audit.getAuditLogInfo(list);

			audlist = (ArrayList) hm.get(alertDO);

			logCaseDataBean.setAlertAuditHistoryList(audlist);
			logCaseDataBean.setAlertAuditHistoryRender(true);
			logCaseDataBean.setAlertAuditOpen(true);
		} catch (GlobalAuditsBusinessException e) {
			log.error("Error in show child audit history  " + e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "showAlertAuditHistory");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	public String showAttachmentAuditHistory() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "showAttachmentAuditHistory");
		GlobalAuditsDelegate audit;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		final List list = new ArrayList();
		ArrayList auditlist = new ArrayList();
		CaseAttachCrossReference attachmentDO = new CaseAttachCrossReference();
		try {

			log.debug("in show child audit history");
			audit = new GlobalAuditsDelegate();
			LogCaseVOToDomainConverter converter = new LogCaseVOToDomainConverter();

			CaseRecord caseRecord = converter
					.convertCaseRecordVOToDO(logCaseDataBean.getCaseDetailsVO());
			attachmentDO = converter.convertCaseAttachmentVOToDO(
					logCaseDataBean.getAttachmentVO(), caseRecord);

			list.add(attachmentDO);

			HashMap hm = audit.getAuditLogInfo(list);
			auditlist = (ArrayList) hm.get(attachmentDO);

			logCaseDataBean.setAttachmentAuditHistoryList(auditlist);
			logCaseDataBean.setAttachmentAuditHistoryRender(true);
			logCaseDataBean.setAttachmentAuditOpen(true);
		} catch (GlobalAuditsBusinessException e) {
			log.error("Error in show child audit history  " + e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "showAttachmentAuditHistory");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	public String showRoutingAuditHistory() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "showRoutingAuditHistory");
		GlobalAuditsDelegate audit;
		final List list = new ArrayList();
		ArrayList auditlist = new ArrayList();
		CaseRouting routingDO = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		try {

			log.debug("in show child audit history");

			audit = new GlobalAuditsDelegate();
			LogCaseVOToDomainConverter converter = new LogCaseVOToDomainConverter();

			CaseRecord caseRecord = converter
					.convertCaseRecordVOToDO(logCaseDataBean.getCaseDetailsVO());
			routingDO = converter.convertCMRoutingVOToDO(logCaseDataBean
					.getRoutingVO(), caseRecord);

			list.add(routingDO);

			HashMap hm = audit.getAuditLogInfo(list);

			auditlist = (ArrayList) hm.get(routingDO);

			logCaseDataBean.setRoutingAuditHistoryList(auditlist);
			logCaseDataBean.setRoutingAuditHistoryRender(true);
			logCaseDataBean.setRoutingAuditOpen(true);

			// Added by ICS

			if (logCaseDataBean.getRoutingVO().getAddThisRecordToMyWatchlist())

			{
				Set watchListSet = new HashSet();
				//LogCaseVOToDomainConverter caseVOToDomainConverter = new
				// LogCaseVOToDomainConverter();
				routingDO.setWatchListIndicator(Boolean.TRUE);

				CaseWatchList caseWatchList = new CaseWatchList();
				caseWatchList.setCaseRecord(caseRecord);
				//Modified for GAI Recursive Call_Fix
				//caseWatchList.setWorkUnit(converter
				//		.getWorkUnitForUser(getUserSK(
				//				logCaseDataBean.getLoggedInUserID()).toString())); // Modified for the Performance fix
				caseWatchList.setWorkUnit(converter
						.getWorkUnitForUser(logCaseDataBean.getLoggedInUserSK().toString()));
				caseWatchList.setAddedAuditTimeStamp(new Date());
				caseWatchList.setAddedAuditUserID(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
				caseWatchList.setAuditTimeStamp(new Date());
				caseWatchList.setAuditUserID(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
				watchListSet.add(caseWatchList);
				caseRecord.setCaseWatchLists(watchListSet);

			} else {
				routingDO.setWatchListIndicator(Boolean.FALSE);

			}
			routingDO.setVersionNo(logCaseDataBean.getRoutingVO()
					.getVersionNo());
			routingDO.setAuditUserID(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
			routingDO.setAuditTimeStamp(new Date());

			// Ends

		} catch (GlobalAuditsBusinessException e) {
			log.error("Error in show child audit history  " + e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "showRoutingAuditHistory");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/*
	 * Infinite added for UC-PGM-CRM-018.10 Defect 3033 start Here
	 */

	public void fieldAuditChange(ValueChangeEvent e) {
		String value = e.getNewValue().toString();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if ("true".equalsIgnoreCase(value)) {
			logCaseDataBean.setRenderInputDateOnRadioYes(true);
		} else
			logCaseDataBean.setRenderInputDateOnRadioYes(false);
	}

	//End Here
	//  Infinite Computer Solutions FOR CR -1825
	public Map getPermissions() {
		Map linksMap = new HashMap();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);  // Added for the Performance fix
		String userid = logCaseDataBean.getLoggedInUserID(); // Modified for the Performance fix //userID();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		ArrayList linksList2Pass = new ArrayList();
		linksList2Pass.add(ContactManagementConstants.LOG_CASE_SAVE);
		linksList2Pass.add(ContactManagementConstants.LOG_CASE_RESET);
		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_ADD_ASSOC_CORRESPONDANCE);
		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_DELETE_CORRESPONDENCE_REC_ASSOC_WITH_CASE);
		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_DELETE_CASE_REC_ASSOC_WITH_CASE);

		linksList2Pass.add(ContactManagementConstants.LOG_CASE_ADD_ATTACHMENT);
		linksList2Pass.add(ContactManagementConstants.LOG_CASE_ATTACHMENT_SAVE);
		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_ATTACHMENT_RESET);
		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_ATTACHMENT_DELETE);
		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_ATTACHMENT_DETACH);

		linksList2Pass.add(ContactManagementConstants.LOG_CASE_ADD_ALERTS);
		linksList2Pass.add(ContactManagementConstants.LOG_CASE_ALERTS_SAVE);
		linksList2Pass.add(ContactManagementConstants.LOG_CASE_ADD_CASE_STEPS);
		linksList2Pass.add(ContactManagementConstants.LOG_CASE_CASE_STEPS_SAVE);
		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_CASE_STEPS_RESET);

		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_CASE_STEPS_DELETE);
		linksList2Pass.add(ContactManagementConstants.LOG_CASE_ADD_CASE_EVENTS);
		linksList2Pass.add(ContactManagementConstants.LOG_CASE_CASE_EVENT_SAVE);
		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_CASE_EVENT_RESET);
		linksList2Pass
				.add(ContactManagementConstants.LOG_CASE_CASE_EVENT_DELETE);
		try {
			linksMap = fieldAccessControlImpl.getActionLinkPermission(
					linksList2Pass, userid);

		} catch (SecurityFLSServiceException e) {

			e.printStackTrace();

		}

		return linksMap;
	}

	/**
	 * Used to render or disable links based on permission.
	 *  
	 */
	public String getLink2Show() {
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String userid = logCaseDataBean.getLoggedInUserID();  // Modified for the Performance fix
		try {
			/*
			 * String returnValue = fieldAccessControlImpl
			 * .getFiledAccessPermission(
			 * ContactManagementConstants.CTMGMT_LOGCASE, userid);
			 */
			String returnValue = fieldAccessControlImpl
					.getFiledAccessPermission("/Enterprise/CtMgmtCase", userid);

			/*
			 * String addAppealValue = fieldAccessControlImpl
			 * .getFiledAccessPermission(
			 * ContactManagementConstants.CTMGMT_ADD_APPEALS, userid);
			 */
			String addProviderAppealValue = fieldAccessControlImpl
					.getFiledAccessPermission("/Enterprise/AddProviderAppeals",
							userid);
			String addMemberAppealValue = fieldAccessControlImpl
					.getFiledAccessPermission(
							"/Enterprise/AddNewMemberAppeals", userid);
			log.debug("++userid case--" + userid);
			log.debug("++returnValue::" + returnValue);
			log.debug("++addProviderAppealValue::"
					+ addProviderAppealValue);
			log.debug("++addMemberAppealValue::"
					+ addMemberAppealValue);

			if (("R".equalsIgnoreCase(returnValue))
					|| ("R".equalsIgnoreCase(addProviderAppealValue))
					|| ("R".equalsIgnoreCase(addMemberAppealValue))) {
				logCaseDataBean
						.setDisableLogCaseSave(ContactManagementConstants.TRUE);
				commonEntityDataBean = (CommonEntityDataBean) getDataBean(ContactManagementConstants.COMMON_ENTITY_DATA_BEAN);
				commonEntityDataBean.setDisableaddNewNote(true);
				logCaseDataBean
						.setDisableLogCaseReset(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableLogAddCaseAssocCrspondence(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableDeleteCorrespondingRecAssocWithCase(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableDeleteCaseRecAssocWithCase(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAddAttachment(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAttachmentSave(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAttachmentReset(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAttachmentDelete(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAttachmentDetach(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAddAlert(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAlertSave(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAddCaseSteps(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableCaseStepSave(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableCaseStepReset(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableCaseStepDelete(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAddCaseEvents(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableCaseEventSave(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableCaseEventReset(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableCaseEventDelete(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAddRouting(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableLogcaseContiniue(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setControlRequired(ContactManagementConstants.TRUE);
		         logCaseDataBean
				         .setDisableSearchDocumentRepository(ContactManagementConstants.TRUE);//Added for the defect ESPRD00734517
		         //added for defect ESPRD00774844
		         logCaseDataBean.setInInquiryModeFlag(Boolean.TRUE);
		
			} else if (("U".equalsIgnoreCase(addProviderAppealValue))
					|| ("U".equalsIgnoreCase(addMemberAppealValue))) {
				//As per comment from SME, for Internal CMGT logged in user if
				// has update previlige then he should also have delete
				// privilige
				logCaseDataBean
						.setDisableDeleteCorrespondingRecAssocWithCase(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableDeleteCaseRecAssocWithCase(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableAttachmentDelete(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableCaseStepDelete(ContactManagementConstants.TRUE);
				logCaseDataBean
						.setDisableCaseEventDelete(ContactManagementConstants.TRUE);
				//added for defect ESPRD00774844
				logCaseDataBean.setInInquiryModeFlag(Boolean.FALSE);
			}
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return link2Show;

	}

	//ADDED FOR GAP-11022

	/**
	 * 
	 * @param entityId,
	 *            entityType
	 */
	public Long getCaseNum(Long entityId, String entityType) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getCaseNum");
		Long caseNum = null;

		CaseDelegate caseDelegate = new CaseDelegate();
		try {

			if (entityType != null) {

				caseNum = caseDelegate.getCaseNum(entityId, entityType);

			}
		} catch (CaseRecordFetchBusinessException e) {

			log.error("Exception while Case Number : " + e);
		} catch (NumberFormatException e) {

			log.error("Exception while getting Case Number : " + e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getCaseNum");
		return caseNum;
	}

	/**
	 * @return Returns the showCaseTypeScreens.
	 */
	public String getShowCaseTypeScreens() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getShowCaseTypeScreens");
		String result = "";
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		String caseTypeSKStr = logCaseDataBean.getCaseDetailsVO().getCaseType();

		getCaseTypeDetails(caseTypeSKStr);
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getShowCaseTypeScreens");
		return result;
	}

	/**
	 * @param showCaseTypeScreens
	 *            The showCaseTypeScreens to set.
	 */
	public void setShowCaseTypeScreens(String showCaseTypeScreens) {
		this.showCaseTypeScreens = showCaseTypeScreens;
	}

	private HtmlFileupload file;

	/**
	 * @return Returns the file.
	 */
	public HtmlFileupload getFile() {
		return file;
	}

	/**
	 * @param file
	 *            The file to set.
	 */
	public void setFile(HtmlFileupload file) {
		this.file = file;
	}

	/* Infinite Implementation for UC-PGM-CRM-018.7, Defect ESPRD00300221 start */
	/**
	 * @param disableFieldsnotifyViaAlert
	 *            The disable the fileds which are related to the notifyVia
	 *            Alert.
	 */
	public void disableFieldsnotifyViaAlert(ValueChangeEvent event) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (event.getNewValue() == null
				|| event.getNewValue().toString().trim().equals(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {
			logCaseDataBean.setDisableFields(true);
			logCaseDataBean.getCaseStepsVO().setSendAlertDays(
					MaintainContactManagementUIConstants.EMPTY_STRING);
			logCaseDataBean.getCaseStepsVO().setAlertBasedOn(
					MaintainContactManagementUIConstants.EMPTY_STRING);
			logCaseDataBean.getCaseStepsVO().setBeforeOrAfterInd(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
			logCaseDataBean.getCaseStepsVO().setSendAlertDaysStr(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
			logCaseDataBean.getCaseStepsVO().setAlertBasedOnStr(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
		} else {
			logCaseDataBean.setDisableFields(false);
		}
		//for ESPRD00741245
		if (logCaseDataBean.isShowAddCaseSteps()) {
			logCaseDataBean.setPageFocusId("addCaseStepFocusPage");
			logCaseDataBean.setCursorFocusValue("LOGCASESTEPSALERT0");
		} else {
			logCaseDataBean.setPageFocusId("editCaseStepFocusPage");
			logCaseDataBean.setCursorFocusValue("LOGCASESTEPSALERT1");
		}
	}

	/* Infinite Implementation for UC-PGM-CRM-018.7, Defect ESPRD00300221 Ends */
	public void expectedCompletionDateForEdit(ValueChangeEvent event)
			throws CaseRecordFetchBusinessException {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "expectedCompletionDateForEdit");
		String caseStepCode = event.getNewValue().toString();
		log.debug("------caseStepCode-----"+caseStepCode);
		if(isNullOrEmptyField(caseStepCode)){
			expecteCompletionDateForEditExtended(caseStepCode);			
		}
		//for ESPRD00741245
		logCaseDataBean.setPageFocusId("editCaseStepFocusPage");
		logCaseDataBean.setCursorFocusValue("LOGCASESTEPSCOMPLETE1");
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "expectedCompletionDateForEdit");
	}
	public void expecteCompletionDateForEditExtended(String caseStepCode){
		CaseDelegate delegate = new CaseDelegate();
		String actionCode = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		int i = 0;
		if (logCaseDataBean.isShowEditCaseSteps()) {
			actionCode = MaintainContactManagementUIConstants.ADD;
		} else {
			actionCode = MaintainContactManagementUIConstants.UPDATE;
		}
		Long caseSK = null;
		if (logCaseDataBean.getCaseRegardingVO().getCaseRecordNumber() != null) {
			caseSK = new Long(logCaseDataBean.getCaseRegardingVO()
					.getCaseRecordNumber());
		}
		Integer caseStepSeq = logCaseDataBean.getCaseStepsVO()
				.getCaseStepSeqNum();
		try {
			Date date = null;
			if (caseSK != null) {
				date = delegate.getExpectedCompletionDate(caseSK, caseStepSeq,
						caseStepCode);
			}
			String expectedCompletiondate = logCaseDataBean
					.getCaseStepsVO().getExpectedDaysToComplete();
			log.debug("++date--"+date);
			log.debug("------expectedCompletiondate-----"+expectedCompletiondate);
			if (expectedCompletiondate != null
					&& !(MaintainContactManagementUIConstants.EMPTY_STRING
							.equals(expectedCompletiondate.trim()))
					&& !(EnterpriseCommonValidator
							.validateNumeric(expectedCompletiondate.trim()))) {
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_EXPECTEDDAYS,
								MaintainContactManagementUIConstants.ADD_EXPECTEDDAYS,
								MaintainContactManagementUIConstants.EDIT_EXPECTEDDAYS2,
								actionCode);
			}else{
				if (expectedCompletiondate != null
						&& expectedCompletiondate.length() > 0) {
					i = Integer.parseInt(expectedCompletiondate.trim());
				}
				if(date != null){
					log.info("++expectedCompletiondate is empty");
				Date displayExpectedCompletiondate = DateUtility.addDayToDate(
						date, i);
				logCaseDataBean
						.getCaseStepsVO()
						.setExpectedCompletionDateStr(
								ContactManagementHelper
										.dateConverter(displayExpectedCompletiondate));
				}else if(caseStepCode.equals(ContactManagementConstants.STEP_START_DATE_CD) 
							|| caseStepCode.equals(ContactManagementConstants.OPEN_DATE_CD)){// Begin - modified the code for the Defect ESPRD00709325, modified the code for the Defect ESPRD00789236
					Date displayExpectedCompletiondate = DateUtility.addDayToDate(
					new Date(), i);
					logCaseDataBean
							.getCaseStepsVO()
							.setExpectedCompletionDateStr(
									ContactManagementHelper
											.dateConverter(displayExpectedCompletiondate));
			
					}else{
					log.debug("Condition is null ");
					logCaseDataBean
					     .getCaseStepsVO()
					         .setExpectedCompletionDateStr("");
				}
				// End - modified the code for the Defect ESPRD00709325
			}
		} catch (CaseRecordFetchBusinessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void expectedCompletionDateForAdd(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "expectedCompletionDateForAdd");
		String caseStepCode = event.getNewValue().toString();
		String actionCode = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		int i = 0;
		if (logCaseDataBean.isShowAddCaseSteps()) {
			actionCode = MaintainContactManagementUIConstants.ADD;
		} else {
			actionCode = MaintainContactManagementUIConstants.UPDATE;
		}
		String expectedCompletiondate = logCaseDataBean.getCaseStepsVO()
				.getExpectedDaysToComplete();
		if (expectedCompletiondate != null
				&& !(MaintainContactManagementUIConstants.EMPTY_STRING
						.equals(expectedCompletiondate.trim()))
				&& !(EnterpriseCommonValidator
						.validateNumeric(expectedCompletiondate.trim()))) {
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.INVALID_EXPECTEDDAYS,
					MaintainContactManagementUIConstants.ADD_EXPECTEDDAYS,
					MaintainContactManagementUIConstants.EDIT_EXPECTEDDAYS2,
					actionCode);
		} else {
			if (expectedCompletiondate != null
					&& expectedCompletiondate.length() > 0) {
				i = Integer.parseInt(expectedCompletiondate.trim());
			}
			Date displayExpectedCompletiondate = DateUtility.addDayToDate(
					new Date(), i);
			logCaseDataBean.getCaseStepsVO().setExpectedCompletionDateStr(
					ContactManagementHelper
							.dateConverter(displayExpectedCompletiondate));
		}
		//for ESPRD00741245
		logCaseDataBean.setPageFocusId("addCaseStepFocusPage");
		logCaseDataBean.setCursorFocusValue("LOGCASESTEPSCOMPLETE0");
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "expectedCompletionDateForAdd");
	}

	/**
	 * @param getDisableExpectedCompletionDate
	 *            The disable the fileds which are related to the Expected
	 *            Completion date.
	 */
	public String getDisableExpectedCompletionDate() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String expectedDaysToComplete = logCaseDataBean.getCaseStepsVO()
				.getExpectedDaysToComplete();
		String completedBasedOn = logCaseDataBean.getCaseStepsVO()
				.getCompletedBasedOn();
		if ((expectedDaysToComplete == null || expectedDaysToComplete.trim()
				.equals(MaintainContactManagementUIConstants.EMPTY_STRING))
				&& (completedBasedOn == null || completedBasedOn.trim().equals(
						MaintainContactManagementUIConstants.EMPTY_STRING))) {
			logCaseDataBean.setDisableExpectedCompletionDateFields(false);
			logCaseDataBean.setDisableCompletionBasedOn(true);
		} else if (expectedDaysToComplete == null
				|| expectedDaysToComplete.trim().equals(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {
			logCaseDataBean.setDisableCompletionBasedOn(true);

		} else {
			log.info("===========getDisableExpectedCompletionDate called=======");
			logCaseDataBean.setDisableExpectedCompletionDateFields(true);
			logCaseDataBean.setDisableCompletionBasedOn(false);
			//defect ESPRD00540227 started
			String completionBasedOn = logCaseDataBean.getCaseStepsVO()
					.getCompletedBasedOn();
			if (logCaseDataBean.isShowEditCaseSteps()
					&& completionBasedOn != null
					&& !completionBasedOn
							.equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
				expecteCompletionDateForEditExtended(completionBasedOn);
			}
        	//defect ESPRD00540227 ends
			
		}
		 // Begin - modified the code for the Defect ESPRD00709325
		if(!logCaseDataBean.isDisableCompletionBasedOn()){
			String completionBasedOn =logCaseDataBean.getCaseStepsVO().getCompletedBasedOn();
			if (logCaseDataBean.isShowEditCaseSteps() && completionBasedOn != null
					&& !completionBasedOn
							.equals(MaintainContactManagementUIConstants.EMPTY_STRING) && expectedDaysToComplete != null
							&& !completedBasedOn.trim().equals(
									MaintainContactManagementUIConstants.EMPTY_STRING)) {
				expecteCompletionDateForEditExtended(completionBasedOn);
			}
		} // End - modified the code for the Defect ESPRD00709325
		return "";
	}

	private String disableExpectedCompletionDate;

	/**
	 * @param disableExpectedCompletionDate
	 *            The disableExpectedCompletionDate to set.
	 */
	public void setDisableExpectedCompletionDate(
			String disableExpectedCompletionDate) {
		this.disableExpectedCompletionDate = disableExpectedCompletionDate;
	}

	/* Added for defect ID ESPRD00299552 */
	/**
	 * @param disableCaseEventFieldsnotifyViaAlert
	 *            The disable the fileds which are related to the notifyVia
	 *            Alert.
	 */
	public void disableCaseEventFieldsnotifyViaAlert(ValueChangeEvent event) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (event.getNewValue() == null
				|| event.getNewValue().toString().trim().equals(
						MaintainContactManagementUIConstants.EMPTY_STRING)) {
			logCaseDataBean.setDisableCaseEventFields(true);
			logCaseDataBean.getCaseEventsVO().setSendAlertDaysCd(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
			logCaseDataBean.getCaseEventsVO().setAfterOrBeforeCd(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
			logCaseDataBean.getCaseEventsVO().setAlertBasedOn(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
		} else {
			logCaseDataBean.setDisableCaseEventFields(false);
		}
	}

	/* EOF defect ID ESPRD00299552 */
	//	Added for defect ID ESPRD00327996
	/**
	 * This method is used to create All available depts lists.
	 */
	public void setAllDepartmentsList() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "setAllDepartmentsList");
		List deptList = new ArrayList();
		Department department = null;
		Map dept_map = new Hashtable();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try {
			//Modified for Department User Heap dump issues starts
			/*

			List deptsList = contactMaintenanceDelegate.getAllDepartmentUsers();

			if (deptsList != null) {
				deptList.add(new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING,
						MaintainContactManagementUIConstants.EMPTY_STRING));

				for (Iterator iter = deptsList.iterator(); iter.hasNext();) {
					DepartmentUser deptUser = (DepartmentUser) iter.next();

					department = (Department) deptUser.getDepartment();

					dept_map.put(deptUser.getDepartmentSK().toString(),
							deptUser.getDepartment().getName());
				}
				Object key = null;
				for (Iterator iter = dept_map.keySet().iterator(); iter
						.hasNext();) {
					key = iter.next();
					deptList.add(new SelectItem(key, dept_map.get(key)
							.toString()));
				}

				logCaseDataBean.setListOfAllDepartments(deptList);
				logCaseDataBean.setDeptMap(dept_map);
			}
		*/
			List deptsList = null;
			
			deptsList = contactMaintenanceDelegate.getAllDepartmentUserNames(ContactManagementConstants.EMPTY_STRING);
			
			if (deptsList != null) {
				deptList.add(new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

				for (Iterator iter = deptsList.iterator(); iter.hasNext();) {
					Object[] departMentObj = (Object[]) iter.next();

					if(departMentObj[0]==null){
						departMentObj[0]=ContactManagementConstants.EMPTY_STRING;
					}
					if(departMentObj[1]==null){
						departMentObj[1]=ContactManagementConstants.EMPTY_STRING;
					}
					dept_map.put(departMentObj[0].toString(),
							departMentObj[1].toString());

				}
				Object key = null;
				for (Iterator iter = dept_map.keySet().iterator(); iter
						.hasNext();) {
					key = iter.next();
					deptList.add(new SelectItem(key, dept_map.get(key)
							.toString()));
				}
				logCaseDataBean.setListOfAllDepartments(deptList);
				logCaseDataBean.setDeptMap(dept_map);
			}
		///Modified for Department User Heap dump issues ends
		} catch (LOBHierarchyFetchBusinessException lobExp) {
			log.error(lobExp.getMessage(), lobExp);
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		logCaseDataBean
				.setBusinessUnitList(getBusinessOrWorkUnitList(ContactManagementConstants.THREE));

		logCaseDataBean
				.setWorkUnitList(getBusinessOrWorkUnitList(ContactManagementConstants.FOUR));

	}

	//EOF defect ID ESPRD00327996

	//Infinite Added for Defect ESPRD00329300
	public boolean validateDateFormat(String Date) {
		boolean flag = true;
		if (Date != null && Date.toString().trim().length() != 0) {
			flag = EnterpriseCommonValidator.validateDate(Date);
		}

		return flag;
	}

	//added for defect ESPRD00330098
	/**
	 * This method does nothing. This is for making action on portlet to bind
	 * the values which are temperarly changed on UI screen.The values which
	 * will not lose after the page refresh.
	 */
	public void bindChangedValues() {

		log
				.debug("bindChangedValues method invoked by script in CMLogCaseControllerBean");//ESPRD00330098
	}

	//EOF ESPRD00330098

	/** Added For The Defect ID ESPRD00301018 */

	/**
	 * It will shows the Correspondence Search screen in LogCase
	 * 
	 * @return String
	 */
	public String showCorrSearchScreen() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "showCorrSearchScreen");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		getCorrReferenceData();
		logCaseDataBean.setShowCorrSearchScreen(true);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/** Added For the defect ID ESPRD00334100 */

	/**
	 * This method is used to convert String object to Date object
	 * 
	 * @param begdate
	 *            This contains the begin Date.
	 * @return Date
	 */
	public static Date dateConverter(String begdate) {

		Date beginDate = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat(
				ProgramConstants.DATE_FORMAT);
		if (begdate != null) {
			if (begdate.indexOf('/') != -1) {
				try {
					beginDate = new Date(sdf1.parse(begdate).getTime());

				} catch (ParseException e) {

					e.getMessage();
				}
			}
		}

		return beginDate;
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
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "setErrorMessage");

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
			if (uiComponent != null) {
				clientId = uiComponent.getClientId(facesContext);
			}

		}

		facesContext.addMessage(clientId, fc);
	}

	/**
	 * This operation is used to find the component in root by passing id.
	 * 
	 * @param id :
	 *            String object.
	 * @return UIComponent : UIComponent object.
	 */
	public UIComponent findComponentInRoot(String id) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "findComponentInRoot");

		UIComponent component = null;
		FacesContext context = FacesContext.getCurrentInstance();

		if (context != null) {
			UIComponent root = context.getViewRoot();
			component = findComponent(root, id);
		}

		return component;
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
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "findComponent");

		if (id.equals(base.getId())) {
			return base;
		}

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
	 * Declared for validation of the Search Criteria.
	 */
	private boolean flag = true;

	/**
	 * This method is used to check if the input field is null or is equal to an
	 * empty string.
	 * 
	 * @param inputField
	 *            String inputField.
	 * @return boolean : true if input field is null or equal to an empty string
	 *         else false.
	 */
	protected boolean isNullOrEmptyField(String inputField) {
		return (inputField == null || ContactManagementConstants.EMPTY_STRING
				.equalsIgnoreCase(inputField.trim()));
	}

	/**
	 * Resets the correspondenceSearchCriteriaVO.
	 */

	public String resetCorrespondence() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "resetCorrespondence");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);  // Added for the Performance fix
		CorrespondenceDOConvertor correspondenceDOConvertor = new CorrespondenceDOConvertor();
		String businessUnitDesc = ContactManagementConstants.CORR_ALLOTHERS;

		Long userSK = correspondenceDOConvertor.getUserSK(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
		businessUnitDesc = correspondenceDOConvertor
				.getBusinessUnitforUser(userSK);
		searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) getDataBean(ContactManagementConstants.SEARCH_CORRESPONDENCE_DATA_BEAN);
		//SearchCorrespondenceDataBean searchCorrespondenceDataBean =
		// getSearchCorrespondenceDataBean();
		//for reset functionality in associated correspondence search under log case page.
		logCaseDataBean.setShowResultsDiv(false);
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = new CorrespondenceSearchCriteriaVO();
		CRAdvanceSearchCriteriaVO advanceSearchCriteriaVO = new CRAdvanceSearchCriteriaVO();
		correspondenceSearchCriteriaVO
				.setAdvSearchCriteria(advanceSearchCriteriaVO);
		logCaseDataBean
				.setCorrespondenceSearchCriteriaVO(correspondenceSearchCriteriaVO);
		logCaseDataBean.getProvTypeVVList().clear();
		logCaseDataBean
				.setSubjectVVList(searchCorrespondenceDataBean
						.getSubjectReferenceData(businessUnitDesc));
		logCaseDataBean
				.setSourceVVList(searchCorrespondenceDataBean
						.getSourceReferenceData(businessUnitDesc));
		logCaseDataBean
				.setResolnVVList(searchCorrespondenceDataBean
						.getResolnReferenceData(businessUnitDesc));
		logCaseDataBean.setLobVVList(searchCorrespondenceDataBean
				.getLobReferenceData());
		searchCorrespondenceDataBean.getAllHierarchies();

		return "success";
	}

	/**
	 * This method is used to get the details for Correspondance.
	 * 
	 * @param correspondenceSk
	 *            Takes correspondenceSk as param
	 */
	public void showDetailPortletForCrspd(String correspondenceSk) {
		searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) getDataBean(ContactManagementConstants.SEARCH_CORRESPONDENCE_DATA_BEAN);
		FacesContext fc = FacesContext.getCurrentInstance();

		log.debug("Entity Id:" + correspondenceSk);

		fc.getExternalContext().getRequestMap().put("correspondenceSk",
				correspondenceSk);

		resetCorrespondence();
		searchCorrespondenceDataBean.getSearchResults().clear();
		searchCorrespondenceDataBean.setShowResultsDiv(false);

	}

	/**
	 * @param inquiryID
	 *            This is used to verify whether or not inquiryID is in valid
	 *            number format.
	 */
	private void verifyInqID(String inquiryID) {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "verifyInqID");

		if (!EnterpriseCommonValidator.validateNumeric(inquiryID)) {
			flag = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
		}

	}

	/**
	 * This method will fetch results based on Input search Criteria.
	 * 
	 * @return success
	 */
	public String getCorrespondence() {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getCorrespondence");
		CMLogCaseDataBean cmLogCaseDatabean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = new CorrespondenceSearchCriteriaVO();
		correspondenceSearchCriteriaVO = cmLogCaseDatabean
				.getCorrespondenceSearchCriteriaVO();
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		CMDelegate cmDelegate = new CMDelegate();
		//ContactMaintenanceDelegate contactMaintenanceDelegate = new
		// ContactMaintenanceDelegate();
		//List businessUnitsList = new ArrayList();

		validateSearch(correspondenceSearchCriteriaVO);
		//Added for displaying the Page level error messages related to Associated Correspondence in Log Case Page
		if(!flag)
		{
			logCaseDataBean.setLogCaseErrMsgFlag(true);
		}
		correspondenceSearchCriteriaVO.setUserID(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
		correspondenceSearchCriteriaVO.setAssocSrch(false);
		boolean assocSrch = cmLogCaseDatabean.isSearchAssocCR();
		//UC-PGM-CRM-019_ESPRD00357942_05DEC09
		cmLogCaseDatabean.getSearchResults().clear();
		correspondenceSearchCriteriaVO.setAssocSrch(true);//EOF
														  // UC-PGM-CRM-019_ESPRD00357942_05DEC09

		Object obj = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		ActionRequest request = null;
		RenderRequest renderRequest = null;
		String reassignCorrespondence = null;
		if (obj instanceof ActionRequest) {
			request = (ActionRequest) obj;
			reassignCorrespondence = request
					.getParameter("reassignCorrespondence");
		} else {
			renderRequest = (RenderRequest) obj;
			reassignCorrespondence = renderRequest
					.getParameter("reassignCorrespondence");
		}

		if (reassignCorrespondence != null
				&& (reassignCorrespondence
						.equalsIgnoreCase("reassignCorrespondence"))) {
			assocSrch = true;
		}

		if (flag) {

			try {
				if (correspondenceSearchCriteriaVO != null
						&& correspondenceSearchCriteriaVO.getCrn() != null
						&& !correspondenceSearchCriteriaVO.getCrn().equals(
								"null")
						&& correspondenceSearchCriteriaVO.getCrn().trim()
								.length() != 0) {
					Long crRecordNo = Long
							.valueOf(correspondenceSearchCriteriaVO.getCrn());
					correspondenceSearchCriteriaVO
							.setCorrespondenceRecordNumber(crRecordNo);
				} else {
					correspondenceSearchCriteriaVO
							.setCorrespondenceRecordNumber(null);
				}
				if (correspondenceSearchCriteriaVO != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria() != null) {
					if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
							.getReportingUnit() == null) {
						correspondenceSearchCriteriaVO.getAdvSearchCriteria()
								.setReportingUnit("");
					}

					if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
							.getDepartment() != null
							&& !correspondenceSearchCriteriaVO
									.getAdvSearchCriteria().getDepartment()
									.equals("null")
							&& correspondenceSearchCriteriaVO
									.getAdvSearchCriteria().getDepartment()
									.trim().length() > 0) {
						log
								.debug("Inside getCorrespondence Setting Dept"
										+ correspondenceSearchCriteriaVO
												.getAdvSearchCriteria()
												.getDepartment());
						List listSK = new ArrayList();
						listSK.add(correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getDepartment());
						correspondenceSearchCriteriaVO.getAdvSearchCriteria()
								.setMaintainGroups(listSK);
					}
				}
				/**
				 * Newly added for category --if no categoy selected send the
				 * enire list
				 */

				if (isNullOrEmptyField(correspondenceSearchCriteriaVO
						.getCategory())) {

					correspondenceSearchCriteriaVO
							.setCategoryList(cmLogCaseDatabean
									.getCatListToBeSentInSearchCritVO());
					log.debug("size of cat list in search crit vo "
							+ correspondenceSearchCriteriaVO.getCategoryList()
									.size());
				}

				/** If category is seleced at the category to he list */
				else {
					log.debug("Category selected");
					correspondenceSearchCriteriaVO.getCategoryList().add(
							correspondenceSearchCriteriaVO.getCategory());
				}

				if (assocSrch) {
					correspondenceSearchCriteriaVO.setAssocSrch(true);
				}

				if (obj instanceof ActionRequest) {
					request = (ActionRequest) obj;
					reassignCorrespondence = request
							.getParameter("reassignCorrespondence");
				} else {
					renderRequest = (RenderRequest) obj;
					reassignCorrespondence = renderRequest
							.getParameter("reassignCorrespondence");
				}
				log.info("-searchCrsIdenfication-" + reassignCorrespondence);
				if (reassignCorrespondence != null
						&& (reassignCorrespondence
								.equalsIgnoreCase("reassignCorrespondence"))) {
					correspondenceSearchCriteriaVO.setReassginCr(true);
				}

				correspondenceSearchCriteriaVO
						.setSortColumn(ContactManagementConstants.ORDERBY_CRN);
				correspondenceSearchCriteriaVO.setAscending(false);

				enterpriseSearchResultsVO = cmDelegate
						.getCorrespondence(correspondenceSearchCriteriaVO);

				List searchResults = null;
				if (enterpriseSearchResultsVO != null) {
					searchResults = enterpriseSearchResultsVO
							.getSearchResults();
				}
				if (searchResults != null) {
					log.debug("Size of results " + searchResults.size());
					cmLogCaseDatabean
							.setSearchCorrespondenceListSize(enterpriseSearchResultsVO
									.getSearchResults().size());
					cmLogCaseDatabean.setShowResultsDiv(true);
					cmLogCaseDatabean.setCaseCrspnAsctErrormsg(false);
				} else {
					setErrorMessage(
							EnterpriseMessageConstants.AUTHORITY_ERROR_CODE,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							"LCASECRRSEARCHERRMESGIDHID");
					logCaseDataBean.setLogCaseErrMsgFlag(true);
					cmLogCaseDatabean.setShowResultsDiv(false);
					cmLogCaseDatabean.setCaseCrspnAsctErrormsg(false);

				}
				if (searchResults != null && searchResults.size() == 0) {
					log.debug("No records found ---size is zero");

					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							"LCASECRRSEARCHERRMESGIDHID");
					logCaseDataBean.setLogCaseErrMsgFlag(true);
					cmLogCaseDatabean.setShowResultsDiv(false);
					cmLogCaseDatabean.setCaseCrspnAsctErrormsg(true);
				}

				log.debug("U R in Associated Search-->"
						+ cmLogCaseDatabean.isSearchAssocCR());

				if (searchResults != null && searchResults.size() > 0)//EOF
																	  // modifications
																	  // for
																	  // UC-PGM-CRM-019_ESPRD00357942_05DEC09
				{
					log
							.debug("Inside the enterpriseSearchResultsVO.getRecordCount() > 0");
					cmLogCaseDatabean.setShowResultsDiv(true);
					// commented as unused/never required in CMlogcaseControllerBean
					//as a part of code clean
					/*if (reassignCorrespondence != null
							&& (reassignCorrespondence
									.equalsIgnoreCase("reassignCorrespondence"))) {
						log.info("results size====" + searchResults.size());
						Iterator itr = searchResults.iterator();
						Map userListMap = new HashMap();
						while (itr.hasNext()) {
							List userList = null;
							List deptListTemp = new ArrayList();
							CorrespondenceSearchResultsVO crResultVo = (CorrespondenceSearchResultsVO) itr
									.next();
							 FIND BUGS_FIX 
							if (crResultVo != null
									&& crResultVo.getUserWorkUnitSK() != null) {
								log.info("crResultVo.getUserWorkUnitSK="
										+ crResultVo.getUserWorkUnitSK());
								if (!userListMap.containsKey(crResultVo
										.getUserWorkUnitSK().toString())) {
									userList = crResultVo.getDeptList();
									if (userList != null && userList.size() > 0) {
										log
												.debug("no.of users to whom we can assign the CR"
														+ userList.size());
										for (Iterator iter = userList
												.iterator(); iter.hasNext();) {
											Object[] userData = (Object[]) iter
													.next();
											StringBuffer userDesc = new StringBuffer();
											if (userData[1] != null) {
												userDesc.append(
														userData[1].toString())
														.append(", ");
											}
											if (userData[2] != null) {
												userDesc.append(
														userData[2].toString())
														.append(" - ");
											}
											if (!crResultVo.getUserWorkUnitSK()
													.equals(userData[0])) {
												if (userDesc != null
														&& userDesc.length() > 0
														&& userData[0] != null) {
													SelectItem selectItem = new SelectItem(
															userData[0]
																	.toString()
																	.trim(),
															userDesc.toString()
																	+ userData[0]
																			.toString());
													deptListTemp
															.add(selectItem);
												}
											}
										}
										crResultVo.getDeptList().clear();
									}
									userListMap.put(crResultVo
											.getUserWorkUnitSK().toString(),
											deptListTemp);
								} else {
									deptListTemp = (List) userListMap
											.get(crResultVo.getUserWorkUnitSK()
													.toString());
								}
							}
							crResultVo.setDeptList(deptListTemp);
						}
						userListMap.clear();
						getAllDeptUsersForReassign();
					}*/// UC-PGM-CRM-019_ESPRD00357942_05DEC09
					// START
					if (searchResults != null && searchResults.size() > 0) {
						//hash map performance issue code change
						//Map statusMap = logCaseDataBean.getStatusMap();
						// Added for the defect ESPRD00687821
						//Map entityTypeMap = logCaseDataBean.getEntityTypeMap();
						//performance code change
						List entitypeList= logCaseDataBean.getEntityTypeVVList();
						List statusList= logCaseDataBean.getStatusVVList();
						//Added for the Defect ESPRD00723617
						List lobList = logCaseDataBean.getLobCodeList();
						Iterator searchResultsItr = searchResults
									.iterator();
							while (searchResultsItr.hasNext()) {
								CorrespondenceSearchResultsVO searchResultVO = (CorrespondenceSearchResultsVO) searchResultsItr
										.next();
								if (searchResultVO != null) {
								// Added for the defect ESPRD00687821
									/*if (entityTypeMap.containsKey(searchResultVO
											.getStatus())) {
										searchResultVO.setEntityType(entityTypeMap.get(
												searchResultVO.getEntityType())
												.toString());
									}*/
									if(entitypeList!=null 
											&& entitypeList.size()>0){
										searchResultVO
										.setEntityType(getDescriptionFromVV
												(searchResultVO.getEntityType(),entitypeList));
									}
									/*if (statusMap.containsKey(searchResultVO
											.getStatus())) {
										searchResultVO.setStatus(statusMap.get(
												searchResultVO.getStatus())
												.toString());
									}*/
									if(statusList!=null 
											&& statusList.size()>0){
										searchResultVO
										.setStatus(getDescriptionFromVV
												(searchResultVO.getStatus(),statusList));
									}
									// Start Added for the Defect ESPRD00723617
									if(lobList!=null 
											&& lobList.size()>0){
										searchResultVO
										.setLobCode(getDescriptionFromVV
												(searchResultVO.getLobCode(),lobList));
									}
								   // End Added for the Defect ESPRD00723617
								}
							}
				   }
					//END
					cmLogCaseDatabean.setSearchResults(searchResults);
					cmLogCaseDatabean.setShowResultsDiv(true);
					if (searchResults.size() == 1)//EOF
												  // UC-PGM-CRM-019_ESPRD00357942_05DEC09
					{

						if (logCaseDataBean.getHtmlSearchResults() != -1) {
							logCaseDataBean.setHtmlSearchResults(0);
							associateCRToCase();
							cmLogCaseDatabean.setShowResultsDiv(false);
						} else {
							cmLogCaseDatabean.setShowResultsDiv(true);
						}
						//EOF UC-PGM-CRM-019_ESPRD00357942_05DEC09

					}

				}//added for UC-PGM-CRM-019_ESPRD00357942_05DEC09
				else {
					cmLogCaseDatabean.setShowResultsDiv(false);
				}//EOF UC-PGM-CRM-019_ESPRD00357942_05DEC09
			} catch (CorrespondenceRecordFetchBusinessException exe) {

				log.error(exe.getMessage(), exe);
				setErrorMessage(
						EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
						new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
				cmLogCaseDatabean.setShowResultsDiv(false);
				log.debug("Exception in getCorrespondece() ");

			}

		} else {
			cmLogCaseDatabean.setShowResultsDiv(false);

		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getCorrespondence");
		return "success";
	}

	/**
	 * This method will validate the search criteria.
	 * 
	 * @param correspondenceSearchCriteriaVO
	 *            the correspondenceSearchCriteriaVO to set
	 */
	protected void validateSearch(
			CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "validateSearch");
		if ((correspondenceSearchCriteriaVO.getEntityID() == null || correspondenceSearchCriteriaVO
				.getEntityID().trim().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getInqEntityID() == null || correspondenceSearchCriteriaVO
						.getInqEntityID().trim().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getInqEntityIDType() == null || correspondenceSearchCriteriaVO
						.getInqEntityIDType().trim().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getStatus() == null || correspondenceSearchCriteriaVO
						.getStatus().trim().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getEntityIDType() == null || correspondenceSearchCriteriaVO
						.getEntityIDType().trim().equalsIgnoreCase(""))
				&& (correspondenceSearchCriteriaVO.getCategory() == null
						|| correspondenceSearchCriteriaVO.getCategory().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getCategory().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getEntityType() == null
						|| correspondenceSearchCriteriaVO.getEntityType().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getEntityType().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getCrn() == null
						|| correspondenceSearchCriteriaVO.getCrn().trim().equals("") || correspondenceSearchCriteriaVO
						.getCrn().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getStatusDate() == null
						|| correspondenceSearchCriteriaVO.getStatusDate().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getStatusDate().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getPriority() == null
						|| correspondenceSearchCriteriaVO.getPriority().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getPriority().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getInqEntityType() == null
						|| correspondenceSearchCriteriaVO.getInqEntityType().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getInqEntityType().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getPayerID() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getPayerID().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getPayerID().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedBY() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedBY().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedBY().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getSubjectCode() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getSubjectCode().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getSubjectCode().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getReportingUnit() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getReportingUnit().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getReportingUnit().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getContact() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getContact().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getContact().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getSource() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getSource().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getSource().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getResolutionCode() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getResolutionCode().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getResolutionCode().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getBusinessUnit() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getBusinessUnit().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getBusinessUnit().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedFromDate() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedFromDate().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedFromDate().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getLineOfBusinessGroup() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria()
								.getLineOfBusinessGroup().trim().equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getLineOfBusinessGroup().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getDepartment() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getDepartment().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getDepartment().trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedToDate() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedToDate().trim()
						.length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getResponseExistIndicator() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria()
								.getResponseExistIndicator().trim().equalsIgnoreCase(
										"") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getResponseExistIndicator()
						.trim().length() == 0)
				&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getAssignedTo() == null
						|| correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getAssignedTo().trim()
								.equalsIgnoreCase("") || correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getAssignedTo().trim().length() == 0)) {

			log.debug("No criteria is entered");
			flag = false;
			setErrorMessage(
					ContactManagementConstants.ERR_EMPTY_SEARCH_INVALID,
					new Object[] {},
					ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
					"LCASECRRSEARCHERRMESGIDHID");

		} else {
			//commented for defect ESPRD00800576 starts
			/*Date crtdFromDate = null;
			Date statsDate = null;
			Date crtdToDate = null;
			String payerID = null;
			String contact = null;
			String entityID = (String) correspondenceSearchCriteriaVO
					.getEntityID();
			String inqID = (String) correspondenceSearchCriteriaVO
					.getInquiringAboutID();
			String entityType = (String) correspondenceSearchCriteriaVO
					.getEntityType();
			if (!isNullOrEmptyField(correspondenceSearchCriteriaVO.getInqEntityID())) {
				if (!ContactManagementHelper
						.validateNumeric(correspondenceSearchCriteriaVO
								.getInqEntityID())) {
					flag = false;
					setErrorMessage(
							ContactManagementConstants.ERR_INVALID_FORMAT,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							null);

				}
			}

			if (correspondenceSearchCriteriaVO.getStatusDate() != null) {
				String statusDate = (String) correspondenceSearchCriteriaVO
						.getStatusDate();
				statsDate = dateConverter(statusDate);
			}
			if (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null) {
				String createdFromDate = correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedFromDate();
				crtdFromDate = dateConverter(createdFromDate);

				String createdToDate = correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedToDate();
				crtdToDate = dateConverter(createdToDate);

				payerID = correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getPayerID();
				contact = correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getContact();
			}
			if (!isNullOrEmptyField(inqID)) {
				verifyInqID(inqID);
			}
			if (!isNullOrEmptyField(entityID)
					&& !isNullOrEmptyField(inqID)) {

				flag = false;
				setErrorMessage(
						ContactManagementConstants.ERR_ENTITY_TYPE_OR_INQID,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
						null);
			} else {

				if (!isNullOrEmptyField(entityID)) {

					verifyEntityID(entityID);

					if (isNullOrEmptyField(entityType)) {
						log.debug("entityType is NULL" + entityType);
						flag = false;
						setErrorMessage(
								ContactManagementConstants.ERR_ENTITY_TYPE_REQUIRED,
								new Object[] {},
								ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
								null);

					}

				}

				if (!isNullOrEmptyField(inqID)) {

					if (!isNullOrEmptyField(entityType)) {
						verifyInqID(inqID);
					} else {

						flag = false;
						setErrorMessage(
								ContactManagementConstants.ERR_ENTITY_TYPE_REQUIRED,
								new Object[] {},
								ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
								null);

					}

				}
			}*/
			//commented for defect ESPRD00800576 ends
			//Added for defect ESPRD00800576 starts
			Date crtdFromDate = null;
			Date statsDate = null;
			Date crtdToDate = null;
			String payerID = null;
			String contact = null;
			String entityID = (String) correspondenceSearchCriteriaVO
					.getEntityID();
			String inqID = (String) correspondenceSearchCriteriaVO
					.getInqEntityID();
			String entityType = (String) correspondenceSearchCriteriaVO
					.getEntityType();
			String entityIDType = (String) correspondenceSearchCriteriaVO
					.getEntityIDType();
			String inqentityType = (String) correspondenceSearchCriteriaVO
					.getInqEntityType();
			String inqentityIDType = (String) correspondenceSearchCriteriaVO
					.getInqEntityIDType();

			if (correspondenceSearchCriteriaVO.getStatusDate() != null) {
				String statusDate = (String) correspondenceSearchCriteriaVO
						.getStatusDate();
				statsDate = dateConverter(statusDate);
			}
			if (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null) {
				String createdFromDate = correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedFromDate();
				crtdFromDate = dateConverter(createdFromDate);

				String createdToDate = correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedToDate();
				crtdToDate = dateConverter(createdToDate);

				payerID = correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getPayerID();
				contact = correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getContact();
			}

			if (entityID != null
					&& !ContactManagementConstants.EMPTY_STRING
							.equalsIgnoreCase(entityID.trim())
					&& inqID != null
					&& !ContactManagementConstants.EMPTY_STRING
							.equalsIgnoreCase(inqID.trim())) {
				
				flag = false;
				setErrorMessage(
						ContactManagementConstants.ERR_ENTITY_TYPE_OR_INQID,
						new Object[] {},
						ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
						"LCASECRRSEARCHERRMESGIDHID");
			} else {

				if (entityID != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(entityID.trim())
						|| entityType != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(entityType.trim())
						|| entityIDType != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(entityIDType.trim())) {
                    
                     // Code added to validate EntityIDType "TP" as per Defect ESPRD00740424
					if(entityID!=null){
		        	if(entityIDType !=null && (entityIDType.equalsIgnoreCase("SY")||
		        			entityIDType.equalsIgnoreCase("TJ")||
		        			entityIDType.equalsIgnoreCase("XX")||
		        			entityIDType.equalsIgnoreCase("TP") ||
		        			entityIDType.equalsIgnoreCase("MCR")||
		        			entityIDType.equalsIgnoreCase("MID")||
		        			entityIDType.equalsIgnoreCase("RID")) )
		        	{
		        	if(!EnterpriseCommonValidator.validateAlphaNumeric(entityID)){
		        	setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
							new Object[] {ContactManagementConstants.ID },
							MessageUtil.ENTMESSAGES_BUNDLE,
							"CorrEntityID");
		        	flag = false;
		        	}
		         }else if(!EnterpriseCommonValidator.validateNumeric(entityID))
				 {
		        	 setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
		 					new Object[] { ContactManagementConstants.ID },
		 					MessageUtil.ENTMESSAGES_BUNDLE,
		 					"CorrEntityID");
		        	flag = false;
		        	}
		        
				//}	
						//verifyEntityID(entityID);
					}

					if (entityType == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(entityType.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Entity Type" },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								"CorrSearchEntityType");

					}
					if (entityType != null && !entityType.trim().equalsIgnoreCase(""))
		            {
					if (entityIDType == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(entityIDType.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Entity ID Type " },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								"CorrSearchEntityIDType");

					}

					if (entityID == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(entityID.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Entity ID" },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								"CorrEntityID");

					}
		           }
				}
				log.debug("%%%%%%%%% inquiry ID is %%%%%%%%%%%%"+inqID);
				if (inqID != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(inqID.trim())
						|| inqentityType != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(inqentityType.trim())
						|| inqentityIDType != null
						&& !ContactManagementConstants.EMPTY_STRING
								.equalsIgnoreCase(inqentityIDType.trim())) {
					 // Code added to validate EntityIDType "TP" as per Defect ESPRD00740424
					if (inqID != null) {
						if(inqentityIDType !=null && (inqentityIDType.equalsIgnoreCase("SY")||
								inqentityIDType.equalsIgnoreCase("TJ")||
								inqentityIDType.equalsIgnoreCase("XX")||
								inqentityIDType.equalsIgnoreCase("TP") ||
								inqentityIDType.equalsIgnoreCase("MCR")||
								inqentityIDType.equalsIgnoreCase("MID")||
								inqentityIDType.equalsIgnoreCase("RID")))
			        	{
			        	if(!EnterpriseCommonValidator.validateAlphaNumeric(inqID)){
			        		setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
			    					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, "corrInqEntityID");
			        	flag = false;
			        	}
			         }else if(!EnterpriseCommonValidator.validateNumeric(inqID))
					 {
			        	 setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
			 					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, "corrInqEntityID");
			        	flag = false;
			        	}
					}

					if (inqentityType == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(inqentityType.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Inquiry Entity Type " },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								"corrInqEntityType");

					}
					if(inqentityType!=null && !inqentityType.trim().equalsIgnoreCase(""))
					{
					if (inqentityIDType == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(inqentityIDType.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Inquiry Entity ID Type " },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								"corrInqEntityIDType");

					}

					if (inqID == null
							|| ContactManagementConstants.EMPTY_STRING
									.equalsIgnoreCase(inqID.trim())) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_REQUIRED,
								new Object[] { "Inquiry Entity ID" },
								ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
								"corrInqEntityID");

					}
				  }
				}
			}
			//Added for defect ESPRD00800576 ends
			if (correspondenceSearchCriteriaVO.getStatusDate() != null
					&& correspondenceSearchCriteriaVO.getStatusDate().trim()
							.length() > 0) {
				verifyStatusDate(correspondenceSearchCriteriaVO.getStatusDate());
			}

			if (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null) {
				if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedFromDate() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedFromDate()
								.trim().length() > 0 && !isDateErrOccurred) {
					verifyCreatedFromDate(correspondenceSearchCriteriaVO
							.getAdvSearchCriteria().getCreatedFromDate());
				}

				if (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate()
								.trim().length() > 0 && !isDateErrOccurred) {
					verifyCreatedToDate(correspondenceSearchCriteriaVO
							.getAdvSearchCriteria().getCreatedToDate());
				}

				if ((correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getCreatedFromDate() != null && correspondenceSearchCriteriaVO
						.getAdvSearchCriteria().getCreatedFromDate().trim()
						.length() > 0)
						&& (correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate() != null && correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getCreatedToDate()
								.trim().length() > 0)) {

					boolean flag1 = EnterpriseCommonValidator
							.compareLesserDate(crtdFromDate, crtdToDate);
					if (!flag1) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_FIRST_LESS_THAN_SECOND,

								new Object[] {
										ContactManagementConstants.CREATED_FROM_DATE,
										ContactManagementConstants.CREATED_TO_DATE },
								MessageUtil.ENTMESSAGES_BUNDLE,

								"createdFrm");

					}

				}
			}

			if ((correspondenceSearchCriteriaVO.getStatusDate() != null && correspondenceSearchCriteriaVO
					.getStatusDate().trim().length() > 0)
					&& (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null
							&& correspondenceSearchCriteriaVO
									.getAdvSearchCriteria()
									.getCreatedFromDate() != null && correspondenceSearchCriteriaVO
							.getAdvSearchCriteria().getCreatedFromDate().trim()
							.length() > 0)) {

				boolean flag1 = EnterpriseCommonValidator.compareGreaterDate(
						statsDate, crtdFromDate);
				if (!flag1) {
					flag = false;

					setErrorMessage(
							ContactManagementConstants.ERR_SW_FIRST_LESS_THAN_OR_EQUAL_TO_SECOND,
							new Object[] {},
							ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
							"StsDt");

				}
			}

			if (correspondenceSearchCriteriaVO.getAdvSearchCriteria() != null) {
				if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getPayerID() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getPayerID().trim()
								.length() > 0) {
					boolean flag2 = EnterpriseCommonValidator
							.validateAlphaNumeric(payerID);
					if (!flag2) {
						flag = false;
						/*
						 * setErrorMessage(
						 * ContactManagementConstants.ERR_PAYER_ID_INVALID, new
						 * Object[] {},
						 * ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
						 * null);
						 */
						setErrorMessage(
								EnterpriseMessageConstants.ERR_SW_INTEGER,
								new Object[] { "ID" },
								MessageUtil.ENTMESSAGES_BUNDLE, "PRGCMGTIT1");

					}
				}

				if (correspondenceSearchCriteriaVO.getCrn() != null
						&& correspondenceSearchCriteriaVO.getCrn().trim()
								.length() > 0) {
					String crRecNo = correspondenceSearchCriteriaVO.getCrn();
					boolean flag2 = EnterpriseCommonValidator
							.validateNumeric(crRecNo);
					if (!flag2) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_DATA_INCORRECT,
								new Object[] {},
								MessageUtil.ENTMESSAGES_BUNDLE, "CRN");

					}
				}
				if (correspondenceSearchCriteriaVO.getAdvSearchCriteria()
						.getContact() != null
						&& correspondenceSearchCriteriaVO
								.getAdvSearchCriteria().getContact().trim()
								.length() > 0) {
					boolean flag3 = EnterpriseCommonValidator
							.validateAlphaSpecialChars(contact);
					if (!flag3) {
						flag = false;
						setErrorMessage(
								EnterpriseMessageConstants.ERR_DATA_INCORRECT,
								new Object[] {},
								MessageUtil.ENTMESSAGES_BUNDLE, "PRGCMGTIT2");

					}
				}
			}

		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "validateSearch");
	}

	/**
	 * @param entityID
	 *            This is used to verify whether or not entityID is in valid
	 *            number format.
	 */
	private void verifyEntityID(String entityID) {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "verifyEntityID");

		if (!EnterpriseCommonValidator.validateNumeric(entityID)) {
			flag = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_INTEGER,
					new Object[] { ContactManagementConstants.ID },
					MessageUtil.ENTMESSAGES_BUNDLE,
					ContactManagementConstants.ID);
		}

	}

	/**
	 * @param createdFromDate
	 *            This is used to verify whether or not createdFromDate is in
	 *            expected date format.
	 */
	private void verifyCreatedFromDate(String createdFromDate)

	{

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "verifyCreatedFromDate");

		if (!EnterpriseCommonValidator.validateDate(createdFromDate))

		{
			flag = false;

			isDateErrOccurred = true;
//Modified for the Defect _ESPRD00777902
			//setDateErrMsg();
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE,
					new Object[] {"Date"}, MessageUtil.ENTMESSAGES_BUNDLE, "createdFrm");
			
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
			isDateErrOccurred = false;
			
		}

	}

	/**
	 * @param createdToDate
	 *            This is used to verify whether or not createdToDate is in
	 *            expected date format.
	 */
	private void verifyCreatedToDate(String createdToDate)

	{

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "verifyCreatedToDate");

		if (!EnterpriseCommonValidator.validateDate(createdToDate))

		{
			flag = false;

			isDateErrOccurred = true;
//Modified for the Defect _ESPRD00777902
			//setDateErrMsg();
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE,
					new Object[] {"Date"}, MessageUtil.ENTMESSAGES_BUNDLE, "createdTo");
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
			isDateErrOccurred = false;

		}

	}

	/**
	 * @param statusDate
	 *            This is used to verify whether or not statusDate is in
	 *            expected date format.
	 */

	private void verifyStatusDate(String statusDate)

	{

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "verifyStatusDate");

		if (!EnterpriseCommonValidator.validateDate(statusDate))

		{
			flag = false;

			isDateErrOccurred = true;
//Modified for the Defect _ESPRD00777902
			//setDateErrMsg();
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE,
					new Object[] {"Date"}, MessageUtil.ENTMESSAGES_BUNDLE, "StsDt");
			logCaseDataBean.setLogCaseErrMsgFlag(Boolean.TRUE);
			isDateErrOccurred = false;
			
			
		}

	}

	private void setDateErrMsg() {
		setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE_LINE1,
				new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

		setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE_LINE2,
				new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

		setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE_LINE3,
				new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

		setErrorMessage(EnterpriseMessageConstants.ERR_SW_DATE_LINE4,
				new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
	}

	// getAllDeptUsersForReassign() commented as unused/never required in CMlogcaseControllerBean
	//as a part of code clean
	/**
	 * This method is used to retreive all users and Departments for Reassign
	 * all
	 * 
	 *//*
	protected void getAllDeptUsersForReassign() {
		List deptUsers = null;
		List reassignDeptList = new ArrayList();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) getDataBean(ContactManagementConstants.SEARCH_CORRESPONDENCE_DATA_BEAN);
		Set depts = new HashSet();
		Set users = new HashSet();
		try {
			deptUsers = contactMaintenanceDelegate.getAllDepartmentUsers();
			log.info("after contact maintenance delegate call :");
		} catch (LOBHierarchyFetchBusinessException e) {
			log.error("error while retreving deptUsers==" + e);
		}
		Iterator it2 = null;
		if(deptUsers != null){
		it2 = deptUsers.iterator();
		}
		//Added if Condition for Find_Bugs-FIX
		if(it2 != null){
		while (it2.hasNext()) {
			DepartmentUser element = (DepartmentUser) it2.next();
			depts.add(element.getDepartment());
			users.add(element.getEnterpriseUser());
		 }
		}
		log.info("depts list Size:" + depts.size());
		for (Iterator iter = users.iterator(); iter.hasNext();) {
			EnterpriseUser user = (EnterpriseUser) iter.next();
			reassignDeptList.add(new SelectItem(user.getUserWorkUnitSK()
					.toString().trim(), user.getLastName() + ", "
					+ user.getFirstName() + " - "
					+ user.getUserWorkUnitSK().toString()));
		}
		log.info("reassignDeptList Size after user:" + reassignDeptList.size());
		for (Iterator iter = depts.iterator(); iter.hasNext();) {
			Department dept = (Department) iter.next();
			reassignDeptList.add(new SelectItem(dept.getWorkUnitSK().toString()
					.trim(), dept.getName()));
		}
		log.debug("in getAllDeptUsersForReassign()reassignDeptList:"
				+ reassignDeptList.size());
		searchCorrespondenceDataBean
				.setReassignAllDeptUsersList(reassignDeptList);

	}*/

	public String doCorrSearchEntityAction() {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "doCorrSearchEntityAction");

		CMLogCaseDataBean cmLogCaseDatabean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = cmLogCaseDatabean
				.getCorrespondenceSearchCriteriaVO();

		boolean isValid = validateCorresFor(correspondenceSearchCriteriaVO);

		if (isValid) {
			log
					.debug("doCorrSearchEntityAction validation for Corresp For successful");

			CorrespondenceSearchCriteriaVO newCorrespondenceSearchCriteriaVO = new CorrespondenceSearchCriteriaVO();

			newCorrespondenceSearchCriteriaVO
					.setEntityType(correspondenceSearchCriteriaVO
							.getEntityType());
			newCorrespondenceSearchCriteriaVO
					.setEntityIDType(correspondenceSearchCriteriaVO
							.getEntityIDType());
			newCorrespondenceSearchCriteriaVO
					.setEntityID(correspondenceSearchCriteriaVO.getEntityID());
			newCorrespondenceSearchCriteriaVO.setFromLogCase(true);
			logCaseDataBean.setLoadEntityDataFlag(true);
			FacesContext facesContext = FacesContext.getCurrentInstance();

			Map requestScope = (Map) facesContext.getApplication()
					.createValueBinding("#{requestScope}").getValue(
							facesContext);

			requestScope.put("CrsSearchCrtVO",
					newCorrespondenceSearchCriteriaVO);

		}

		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "doCorrSearchEntityAction");
		return "success";
	}

	/**
	 * 
	 * @param correspondenceSearchCriteriaVO
	 * @return
	 */
	public boolean validateCorresFor(
			CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO) {

		boolean isValid = true;
		//MOdified for the defect ESPRD00800576 starts
		if (!ContactManagementHelper
				.validateNumeric(correspondenceSearchCriteriaVO.getEntityID())) {
			isValid = false;
			String id = "ID";
			setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
					new Object[] { id }, MessageUtil.ENTMESSAGES_BUNDLE, "CorrEntityID");
			logCaseDataBean.setLogCaseErrMsgFlag(true);
		}
		if (!ContactManagementHelper
				.validateNumeric(correspondenceSearchCriteriaVO.getInqEntityID())) {
			isValid = false;
			String id = "ID";
			setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
					new Object[] { id }, MessageUtil.ENTMESSAGES_BUNDLE, "corrInqEntityID");
			logCaseDataBean.setLogCaseErrMsgFlag(true);
		}
		//MOdified for the defect ESPRD00800576 ends
		return isValid;
	}

	/**
	 * renders the detail part based on the filter.
	 * 
	 * @param event
	 *            event.
	 */

	public String onEntityTypeChange(ValueChangeEvent event) {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "onEntityTypeChange");
		CMLogCaseDataBean cmLogCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (!isNullOrEmptyField(event.getNewValue().toString())) {

			String selectedEntity = (String) event.getNewValue();

			getCorrEntIDTypeValues(selectedEntity, "Entity");

			if ("P".equals(selectedEntity)) {
				log.debug("Selected Entity:: P" + selectedEntity);
				cmLogCaseDataBean.setProvTypeVVList(getProvTypeReferenceData());

			}
		}
		//Undo the Entity IDType list
		else{
			cmLogCaseDataBean.getEntityIDTypeList().clear();
		}

		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "onEntityTypeChange");

		return "success";
	}

	/**
	 * Populating Entity ID Type values in Correspondence For section
	 * 
	 * @param entityType
	 */
	public void getCorrEntIDTypeValues(String entityType, String entityFrame) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getCorrEntIDTypeValues");

		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.CASE_SEARCH_DATA_BEAN);
		CMLogCaseDataBean cmlogCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();

		/** ENTITY TYPE -- MEMBER */

		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_MEM, entityType)) {

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_MEM);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type Member
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")) {
					cmlogCaseDataBean.setEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				} else {
					cmlogCaseDataBean
							.setInqEntityIDTypeList(cmEntityDOConvertor
									.getEntIDTypeReferenceData(
											FunctionalAreaConstants.GENERAL,
											sysListNumber));
				}
			}

		}

		/** ENTITY TYPE -- PROVIDER */
        // Modified the SystemList No for the Defect.ESPRD00744334
		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_PROV, entityType)) {
            Long sysListNumber = Long.valueOf("1017");/*ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_PROVIDER)*/
			/**
			 * Populates the Entity ID Type dropdown for Entity Type Provider
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.PROVIDER, sysListNumber) != null) {
				if (entityFrame.equals("Entity")) {
					cmlogCaseDataBean.setEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.PROVIDER,
									sysListNumber));
				} else {
					cmlogCaseDataBean
							.setInqEntityIDTypeList(cmEntityDOConvertor
									.getEntIDTypeReferenceData(
											FunctionalAreaConstants.PROVIDER,
											sysListNumber));
				}

			}

		}

		/** ENTITY TYPE -- UNENROLLED PROVIDER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_UNPROV, entityType)) {

			log.debug("Inside if loop for unenrolled  provider ");

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_UNPROV);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type Unenrolled
			 * Provider
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")) {
					cmlogCaseDataBean.setEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				} else {
					cmlogCaseDataBean
							.setInqEntityIDTypeList(cmEntityDOConvertor
									.getEntIDTypeReferenceData(
											FunctionalAreaConstants.GENERAL,
											sysListNumber));
				}
			}

		}

		/** ENTITY TYPE -- TPL CARRIER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_TPL, entityType)) {

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_TPL);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type TPL Carrier
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")) {
					cmlogCaseDataBean.setEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				} else {
					cmlogCaseDataBean
							.setInqEntityIDTypeList(cmEntityDOConvertor
									.getEntIDTypeReferenceData(
											FunctionalAreaConstants.GENERAL,
											sysListNumber));
				}
			}

		}

		/** ENTITY TYPE -- DISTRICT OFFICE */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_DO, entityType)) {

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_DO);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type District
			 * Office
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")) {
					cmlogCaseDataBean.setEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				} else {
					cmlogCaseDataBean
							.setInqEntityIDTypeList(cmEntityDOConvertor
									.getEntIDTypeReferenceData(
											FunctionalAreaConstants.GENERAL,
											sysListNumber));
				}
			}

		} else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_CT, entityType)) {

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CT);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type County
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")) {
					cmlogCaseDataBean.setEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				} else {
					cmlogCaseDataBean
							.setInqEntityIDTypeList(cmEntityDOConvertor
									.getEntIDTypeReferenceData(
											FunctionalAreaConstants.GENERAL,
											sysListNumber));
				}
			}

		}

		else {
			/** ENTITY TYPE -- UNENROLLED MEMBER and other Entities */

			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type Other
			 */
			if (cmEntityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				if (entityFrame.equals("Entity")) {
					cmlogCaseDataBean.setEntityIDTypeList(cmEntityDOConvertor
							.getEntIDTypeReferenceData(
									FunctionalAreaConstants.GENERAL,
									sysListNumber));
				} else {
					cmlogCaseDataBean
							.setInqEntityIDTypeList(cmEntityDOConvertor
									.getEntIDTypeReferenceData(
											FunctionalAreaConstants.GENERAL,
											sysListNumber));
				}
			}

		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getCorrEntIDTypeValues");
	}

	/**
	 * renders the detail part based on the filter.
	 * 
	 * @param event
	 *            event.
	 */

	public String onInqEntityTypeChange(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "onInqEntityTypeChange");
		if (!isNullOrEmptyField((String)event.getNewValue())) {
			String selectedEntity = (String) event.getNewValue();
			log.debug("****selectedEntity****" + selectedEntity);

			getCorrEntIDTypeValues(selectedEntity, "Inquiry");

		}
		//Undo the InqEntity IDType list
		else{
			logCaseDataBean.getInqEntityIDTypeList().clear();
		}

		return "success";

	}

	/**
	 * This method is used to retreive valid values in the Provider dropdown
	 * 
	 * @return List
	 */
	public final List getProvTypeReferenceData() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getProvTypeReferenceData");

		// Commented for HeapDump Issue
		/*
		 * List referenceList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 */
		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;

		// Commented for HeapDump Issue
		/*
		 * List provTypeList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 */
		List provTypeList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.PROVIDER);
		inputCriteriaEntityTyp
				.setElementName(ReferenceServiceDataConstants.PROV_TY_CD);

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.PROVIDER
							+ ProgramConstants.HASH_SEPARATOR
							+ ReferenceServiceDataConstants.PROV_TY_CD);
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				provTypeList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			log.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			log.error(e.getMessage(), e);
		}

		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getProvTypeReferenceData");
		return provTypeList;
	}

	/**
	 * renders the detail part based on the filter.
	 * 
	 * @param event
	 *            event.
	 */

	public String onCategoryChange(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "onCategoryChange");

		if (event != null
				&& event.getNewValue() != null
				&& !ProgramConstants.EMPTY_STRING.equals(event.getNewValue()
						.toString().trim())) {
			String selectedCategory = (String) event.getNewValue();
			log.debug("*selectedCategory*" + selectedCategory);
			getsubjects(selectedCategory);
		}

		return "success";
	}

	/**
	 * This method is used to get the subjects list based on the categorySK.
	 * 
	 * @param categorySK
	 *            The categorySK to set.
	 */
	public void getsubjects(String categorySK) {
		ArrayList subjectList = new ArrayList();

		searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) getDataBean(ContactManagementConstants.SEARCH_CORRESPONDENCE_DATA_BEAN);
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getsubjects");
		CorrespondenceCategory correspondenceCategory = null;

		if (searchCorrespondenceDataBean.getListOfCategoryDOs() != null) {

			Iterator iterator = searchCorrespondenceDataBean
					.getListOfCategoryDOs().iterator();
			while (iterator.hasNext()) {
				correspondenceCategory = (CorrespondenceCategory) iterator
						.next();

				if (correspondenceCategory.getCategorySK().toString().equals(
						categorySK)) {

					Set catSubjXrefs = correspondenceCategory.getCatSubjXrefs();

					Iterator iterateSet = catSubjXrefs.iterator();

					while (iterateSet.hasNext()) {
						CategorySubjectXref catSubjAux = (CategorySubjectXref) iterateSet
								.next();

						SubjectAuxillary subjectAux = catSubjAux
								.getSubjectAuxillary();

						subjectList
								.add(new SelectItem(
										subjectAux.getSubjectCode(), subjectAux
												.getSubjectCode()));

					}
				}
			}

		}
		log.info("subjectList==" + subjectList.size());
		if (subjectList.size() > 0) {
			searchCorrespondenceDataBean.setSubjectVVList(subjectList);
		}

	}

	/** Ends For the defect ID ESPRD00334100 */

	/**
	 * Reqired filed validation for add attachment
	 * 
	 * @param attachment
	 * @return
	 */
	private boolean requiredFldsAvbl(AttachmentVO attachment) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "requiredFldsAvbl");
		boolean fldsAvbl = true;

		//DEFECT-ESPRD00342433
		if (isNullOrEmptyField(attachment.getFileName())) {
			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.FILE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"fileupload1");
		}
		//END

		if (isNullOrEmptyField(attachment.getDescription())) {
			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.DESCRIPTION },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"attaddDesc");
		}
		if (isNullOrEmptyField(attachment.getEdmsWrkUnitLevel())) {
			fldsAvbl = false;
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.EDMS_WOKK_UNIT_LEVEL },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"edmsdepartlevel");
		}
		if (isNullOrEmptyField(attachment.getEdmsDocType())) {
			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.EDMS_DOC_TYPE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"edmsdoctype");
		}

		return fldsAvbl;
	}

	/** Added For the defect ID ESPRD00334100 */

	/**
	 * Fetches the reference data.
	 */
	public void getCorrReferenceData() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getCorrReferenceData");

		//FacesContext ctx = FacesContext.getCurrentInstance();
		//HttpServletRequest request =
		// (HttpServletRequest)ctx.getExternalContext().getRequest();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String userId = logCaseDataBean.getLoggedInUserID();  // Modified for the Performance fix //"USERID1";
		//ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
		String businessUnitDesc = logCaseDataBean.getBusinessUnitDesc();
		/*try {
			List buinessUnitDescs = delegate.getBuisnessUnitsDescs(userId);
			if (buinessUnitDescs.size() > 1) {
				businessUnitDesc = ContactManagementConstants.AllOthers;
			} else {
				businessUnitDesc = (String) buinessUnitDescs.get(0);
			}
			logCaseDataBean
					.setStatusVVList(getStatReferenceData(businessUnitDesc));
			logCaseDataBean
					.setEntityTypeVVList(getEntityTypeReferenceData(businessUnitDesc));
			logCaseDataBean.setPriorityVVList(getPriorityReferenceData());
			getAllCategories(userId);
		} catch (LOBHierarchyFetchBusinessException e) {
			log.debug(e);
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.debug(e);
			log.error(e.getMessage(), e);
		}*/
		if(businessUnitDesc!=null && !businessUnitDesc.equals("")){
			/*logCaseDataBean.setStatusVVList(getStatReferenceData(businessUnitDesc));
			logCaseDataBean
					.setEntityTypeVVList(getEntityTypeReferenceData(businessUnitDesc));
			logCaseDataBean.setPriorityVVList(getPriorityReferenceData());*/
			
			//hash map performance issue code change
			populateCorrSearch(businessUnitDesc);
			getAllCategories(userId);
		}
	}

	/**
	 * This method is used to retreive valid values in the status dropdown
	 * 
	 * @return List
	 */
	/* hash map performance issue code change
	 * public final List getStatReferenceData(String businessUnitDesc) {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getStatReferenceData");
		// Commented for HeapDump Issue
		
		 * List referenceList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 
		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		// Commented for HeapDump Issue
		// List statList = new
		// ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		List statList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = null;

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);

		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.CORR_STATUS,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String
									.valueOf(ContactManagementHelper
											.getSystemlistForCorrBU(
													ContactManagementConstants.CORR_STATUS,
													businessUnitDesc)));
			referenceListSize = referenceList.size();
			//Map statusMap = new HashMap();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				statList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

				//statusMap.put(refVo.getValidValueCode(), codesAndDesc);

			}
			//logCaseDataBean.setStatusMap(statusMap);
		} catch (ReferenceServiceRequestException e) {
			log.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			log.error(e.getMessage(), e);
		}

		return statList;
	}

	*//**
	 * This operation is used to get the reference data for all Entity Type.
	 * 
	 * @return List
	 *//*
	public final List getEntityTypeReferenceData(String businessUnitDesc) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getEntityTypeReferenceData");

		
		 * List referenceList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 
		List referenceList = new ArrayList();

		Map referenceMap = null;

		int referenceListSize = 0;
		// Commented for HeapDump Issue
		// List entityList = new
		// ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		List entityList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);

		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.ENTITYTYPE,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String
									.valueOf(ContactManagementHelper
											.getSystemlistForCorrBU(
													ContactManagementConstants.ENTITYTYPE,
													businessUnitDesc)));
			referenceListSize = referenceList.size();
			//Map entityTypeMap = logCaseDataBean.getEntityTypeMap();// Added for the defect ESPRD00687821

			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				entityList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));
						// Added for the defect ESPRD00687821
				entityTypeMap.put(refVo.getValidValueCode(),
						codesAndDesc);

			}
		} catch (ReferenceServiceRequestException e) {
			log.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			log.error(e.getMessage(), e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getEntityTypeReferenceData");
		return entityList;
	}

	*//**
	 * This method is used to retreive valid values in the Priority dropdown
	 * 
	 * @return List
	 *//*

	public final List getPriorityReferenceData() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getPriorityReferenceData");
		// Commented for HeapDump Issue
		
		 * List referenceList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 
		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		// Commented for HeapDump Issue
		
		 * List priorityList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 
		List priorityList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		inputCriteriaEntityTyp
				.setElementName(ReferenceServiceDataConstants.G_CR_PRTY_CD);

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ ReferenceServiceDataConstants.G_CR_PRTY_CD);
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				priorityList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			log.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			log.error(e.getMessage(), e);
		}

		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getPriorityReferenceData");
		return priorityList;
	}*/

	/**
	 * This operation is used to get all the Categories. *
	 * 
	 * @param userID :
	 *            The User ID to set.
	 */
	public void getAllCategories(String userID) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAllCategories");
		log.debug("user id ------>" + userID);
		List listOfCategoryDOs = null;
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		try {
			listOfCategoryDOs = contactMaintenanceDelegate
					.getAllCategoriesForList(userID);

		} catch (CategoryFetchBusinessException e) {
			log.error(e.getMessage(), e);
		}

		if (listOfCategoryDOs != null) {
			log.debug("Size of listOfCategoryDOs ********"
					+ listOfCategoryDOs.size());
			CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();
			Map categoryMap = new HashMap();
			List sortCategory = new ArrayList();
			Iterator iter = listOfCategoryDOs.iterator();
			List categoryList = logCaseDataBean.getCategoryList();
			while (iter.hasNext()) {
				CorrespondenceCategory categoryDO = (CorrespondenceCategory) iter
						.next();
				CategoryVO categoryVO = categoryDOConvertor
						.convertCategoryDOToVOForCatList(categoryDO);

				/** Add to the List that needs to be displayed in dropdown */
				sortCategory.add(categoryVO.getCategoryDesc());
				categoryMap.put(categoryVO.getCategoryDesc(), categoryVO
						.getCategorySK().toString());

				log.debug("%Size of categoryList->" + categoryList.size());
			}
			Collections.sort(sortCategory);
			iter = sortCategory.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				categoryList.add(new SelectItem(
						categoryMap.get(obj).toString(), obj.toString()));
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getAllCategories");
	}

	/**
	 * @return String
	 */
	public String associateCRToCase() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "associateCRToCase");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		// Added for the defect ESPRD00687821
		CorrespondenceDOConvertor correspondenceDOConvertor = new CorrespondenceDOConvertor();
		ContactManagementHelper contactManagementHelper=new ContactManagementHelper();
		Long userSK = correspondenceDOConvertor.getUserSK(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
					String businessUnitDesc = correspondenceDOConvertor
					.getBusinessUnitforUser(userSK);
					//end
		// Begin - Added the code for the defect id ESPRD00684566
		FacesContext fc = FacesContext.getCurrentInstance();
		String str = (String) fc.getExternalContext().getRequestParameterMap()
				.get("rowIndex");
		// End - Added the code for the defect id ESPRD00684566
		logCaseDataBean.setSearchSelectAll(false);
		logCaseDataBean.setSearchDeSelectAll(false);
		// Begin - Modified the code for the defect id ESPRD00684566
		CorrespondenceSearchResultsVO resultsVO = (CorrespondenceSearchResultsVO) logCaseDataBean
				.getSearchResults().get(Integer.parseInt(str));
		// End - Modified the code for the defect id ESPRD00684566
		// Added for the defect ESPRD00687821
		List subjectList=logCaseDataBean.getSubjectVVList();
		if(subjectList.size()==0)
			subjectList=getSubjectReferenceData(businessUnitDesc);
		
		if (recordNotAssociatedEarlier(resultsVO.getCorrespondenceNumber()
				.toString())) {
			AssociatedCorrespondenceVO associatedCorrespondenceVO = getAssociatedCRToCase(resultsVO);
			// Added for the defect ESPRD00687821
			associatedCorrespondenceVO.setSubject(contactManagementHelper.getDescriptionFromVV(associatedCorrespondenceVO.getSubject(), subjectList));
			logCaseDataBean.getCaseDetailsVO().getAssociatedCrList().add(
					associatedCorrespondenceVO);
		}

		return "success";
	}
	
	/**
	 * @param resultsVO
	 *            The resultsVO to set.
	 * @return boolean
	 */
	private boolean recordNotAssociatedEarlier(String correspondenceNumber) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "recordNotAssociatedEarlier");

		boolean notAssociated = true;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List listOfAssociatedCRs = logCaseDataBean.getCaseDetailsVO()
				.getAssociatedCrList();

		if (listOfAssociatedCRs != null) {
			for (Iterator iter = listOfAssociatedCRs.iterator(); iter.hasNext();) {
				AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) iter
						.next();
				if (asscCRVO.getCorrespondenceRecordNumber().equalsIgnoreCase(
						correspondenceNumber)) {
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
	private AssociatedCorrespondenceVO getAssociatedCRToCase(
			CorrespondenceSearchResultsVO resultsVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAssociatedCRToCase");
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
	 * @param event
	 * @return
	 */
	public String selectAllSubmit(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "selectAllSubmit");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List searchResults = logCaseDataBean.getSearchResults();
		logCaseDataBean.setSearchSelectAll(true);
		logCaseDataBean.setSearchDeSelectAll(false);
		associateSelectedCRs(searchResults);
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * 
	 * @param crList
	 */
	private void associateSelectedCRs(List crList) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "associateSelectedCRs");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (crList != null && crList.size() > 0) {
			log.debug("crList.size()---> " + crList.size());
			for (Iterator iterator = crList.iterator(); iterator.hasNext();) {
				CorrespondenceSearchResultsVO resultsVO = (CorrespondenceSearchResultsVO) iterator
						.next();
				if (!resultsVO.isCheckBox()) {
					resultsVO.setCheckBox(true);
					if (recordNotAssociatedEarlier(resultsVO
							.getCorrespondenceNumber().toString())) {
						AssociatedCorrespondenceVO associatedCorrespondenceVO = getAssociatedCRToCase(resultsVO);
						logCaseDataBean.getCaseDetailsVO()
								.getAssociatedCrList().add(
										associatedCorrespondenceVO);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String deSelectAllSubmit(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "deSelectAllSubmit");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List searchResults = logCaseDataBean.getSearchResults();
		logCaseDataBean.setSearchSelectAll(false);
		logCaseDataBean.setSearchDeSelectAll(true);
		dissociateSelectedCRs(searchResults);
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * 
	 * @param crList
	 */
	private void dissociateSelectedCRs(List crList) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "dissociateSelectedCRs");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CorrespondenceSearchResultsVO resultsVO = null;
		if (crList != null && crList.size() > 0) {

			for (Iterator iterator = crList.iterator(); iterator.hasNext();) {

				resultsVO = (CorrespondenceSearchResultsVO) iterator.next();
				resultsVO.setCheckBox(false);
				List listOfAssociatedCRs = logCaseDataBean.getCaseDetailsVO()
						.getAssociatedCrList();

				String crn = resultsVO.getCorrespondenceNumber().toString();

				for (Iterator iter = listOfAssociatedCRs.iterator(); iter
						.hasNext();) {
					AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) iter
							.next();
					if (asscCRVO.getCorrespondenceRecordNumber()
							.equalsIgnoreCase(crn)) {
						listOfAssociatedCRs.remove(asscCRVO);
						break;
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String selectAllExistingCRs(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "selectAllExistingCRs");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List existingCrslist = logCaseDataBean.getExistingCRInfoList();
		List newCrslist = new ArrayList();
		logCaseDataBean.setExistCrsSelectAll(true);
		logCaseDataBean.setExistCrsDeSelectAll(false);
		AssociatedCorrespondenceVO resultsVO = null;
		if (existingCrslist != null && existingCrslist.size() > 0) {
			log.debug("existingCrslist.size()---> " + existingCrslist.size());
			for (Iterator iterator = existingCrslist.iterator(); iterator
					.hasNext();) {

				resultsVO = (AssociatedCorrespondenceVO) iterator.next();

				resultsVO.setLinksToCR(true);
				newCrslist.add(resultsVO);
				if (recordNotAssociatedEarlier(resultsVO
						.getCorrespondenceRecordNumber().toString())) {
					logCaseDataBean.getCaseDetailsVO().getAssociatedCrList()
							.add(resultsVO);
				}

			}
			logCaseDataBean.getExistingCRInfoList().clear();//added for
															// ESPRD00652712
															// defect
			logCaseDataBean.setExistingCRInfoList(newCrslist);
		}
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String deSelectAllExistingCRs(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "deSelectAllExistingCRs");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List existingCrslist = logCaseDataBean.getExistingCRInfoList();
		logCaseDataBean.setExistCrsSelectAll(false);
		logCaseDataBean.setExistCrsDeSelectAll(true);
		AssociatedCorrespondenceVO resultsVO = null;
		List newCrslist = new ArrayList();
		if (existingCrslist != null && existingCrslist.size() > 0) {

			for (Iterator iterator = existingCrslist.iterator(); iterator
					.hasNext();) {

				resultsVO = (AssociatedCorrespondenceVO) iterator.next();
				//resultsVO.setLinkToCR(Boolean.FALSE);
				resultsVO.setLinksToCR(false);
				newCrslist.add(resultsVO);
				List listOfAssociatedCRs = logCaseDataBean.getCaseDetailsVO()
						.getAssociatedCrList();

				String crn = resultsVO.getCorrespondenceRecordNumber()
						.toString();

				for (Iterator iter = listOfAssociatedCRs.iterator(); iter
						.hasNext();) {
					AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) iter
							.next();
					if (asscCRVO.getCorrespondenceRecordNumber()
							.equalsIgnoreCase(crn)) {
						listOfAssociatedCRs.remove(asscCRVO);
						break;
					}
				}

			}

			logCaseDataBean.getExistingCRInfoList().clear();//added for
															// ESPRD00652712
															// defect
			logCaseDataBean.setExistingCRInfoList(newCrslist);// UC-PGM-CRM-018_ESPRD00376580_22DEC09
		}
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String associateExistingCrToCase() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "associateExistingCrToCase");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		int index = 0;

		logCaseDataBean.setExistCrsSelectAll(false);
		logCaseDataBean.setExistCrsDeSelectAll(false);

		AssociatedCorrespondenceVO resultsVO = (AssociatedCorrespondenceVO) logCaseDataBean
				.getExistingCRInfoList().get(index);

		if (resultsVO.getLinkToCR().equals(Boolean.TRUE)) {
			if (recordNotAssociatedEarlier(resultsVO
					.getCorrespondenceRecordNumber())) {

				logCaseDataBean.getCaseDetailsVO().getAssociatedCrList().add(
						resultsVO);
			}
		} else {

			List associateCRList = logCaseDataBean.getCaseDetailsVO()
					.getAssociatedCrList();
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

	//  added for ESPRD00340194,ESPRD00307362
	public String enableDisableCreateLetterLinkForCaseEvents(
			ValueChangeEvent event) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (logCaseDataBean.getCaseRegardingVO() != null) {
			//commented for defect ESPRD00552512
			/*
			 * if(logCaseDataBean.getCaseRegardingVO().getCaseRecordNumber()!=null &&
			 * !ContactManagementConstants.EMPTY_STRING.equals(logCaseDataBean.getCaseRegardingVO().getCaseRecordNumber().trim())){
			 */

			if (event.getComponent().getId() != null) {

				if (event.getComponent().getId().equals("LOGCASETEMPLATE0")
						|| event.getComponent().getId().equals("LOGCASETEMPLATE1")) {
					if (event.getNewValue() != null
							&& !event.getNewValue().toString().trim()
									.equals("")) {
						logCaseDataBean.setCaseEventsCreateLetterStatus(true);
					} else {
						logCaseDataBean.setCaseEventsCreateLetterStatus(false);
					}
				}

			}

			/* } */else {
				logCaseDataBean.setCaseEventsCreateLetterStatus(false);
			}
		} else {
			logCaseDataBean.setCaseEventsCreateLetterStatus(false);
		}
		//for ESPRD00741245
		if(logCaseDataBean.isShowAddCaseEvents()){
			logCaseDataBean.setPageFocusId("addCaseEventPageFocus");
			logCaseDataBean.setCursorFocusValue("LOGCASETEMPLATE0");
		}else{
			logCaseDataBean.setPageFocusId("addCaseEventPageFocus");
			logCaseDataBean.setCursorFocusValue("LOGCASETEMPLATE1");
		}

		return ContactManagementConstants.EMPTY_STRING;
	}

	/**
	 * This method enable/disables CreateLetterLink in add/edit spans of
	 * casesteps
	 * 
	 * @param event
	 * @return
	 */
	public String enableDisableCreateLetterLinkForCaseSteps(
			ValueChangeEvent event) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (logCaseDataBean.getCaseRegardingVO() != null) {
			/*
			 * if(logCaseDataBean.getCaseRegardingVO().getCaseRecordNumber()!=null &&
			 * !ContactManagementConstants.EMPTY_STRING.equals(logCaseDataBean.getCaseRegardingVO().getCaseRecordNumber().trim())){
			 */

			if (event.getComponent().getId() != null) {

				if (event.getComponent().getId().equals("templateID")
						|| event.getComponent().getId().equals("templateID2")) {
					if (event.getNewValue() != null
							&& !event.getNewValue().toString().trim()
									.equals("")) {
						logCaseDataBean.setCaseStepsCreateLetterStatus(true);
					} else {
						logCaseDataBean.setCaseStepsCreateLetterStatus(false);
					}
				}
			}

			/* } */else {
				logCaseDataBean.setCaseStepsCreateLetterStatus(false);
			}
		} else {
			logCaseDataBean.setCaseStepsCreateLetterStatus(false);
		}
		//for ESPRD00741245
		if (logCaseDataBean.isShowAddCaseSteps()) {
			logCaseDataBean.setPageFocusId("addCaseStepFocusPage");
			logCaseDataBean.setCursorFocusValue("templateID");
		} else {
			logCaseDataBean.setPageFocusId("editCaseStepFocusPage");
			logCaseDataBean.setCursorFocusValue("templateID2");
		}

		return ContactManagementConstants.EMPTY_STRING;
	}

	//    EOF ESPRD00340194,ESPRD00307362

	public String selectAllCaseRecords(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "selectAllCaseRecords");
		List caseRecordList = caseSearchDataBean.getSearchCaseList();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setSearchCaseSelectAll(true);
		logCaseDataBean.setSearchCaseDeSelectAll(false);
		associateSelectedCaseRecords(caseRecordList);
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * 
	 * @param caseList
	 */
	private void associateSelectedCaseRecords(List caseList) {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "associateSelectedCaseRecords");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (caseList != null && caseList.size() > 0) {

			for (Iterator iterator = caseList.iterator(); iterator.hasNext();) {
				CaseRecordSearchResultsVO resultsVO = (CaseRecordSearchResultsVO) iterator
						.next();
				if (!resultsVO.isCheckBox()) {
					resultsVO.setCheckBox(true);
					if (caseRecordNotAssociatedEarlier(resultsVO.getCaseSK()
							.toString())) {
						AssociatedCaseVO associatedCaseVO = getAssociatedCaseRecord(resultsVO);
						logCaseDataBean.getCaseDetailsVO()
								.getAssociatedCaseList().add(associatedCaseVO);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param caseSK
	 * @return
	 */
	private boolean caseRecordNotAssociatedEarlier(String caseSK) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "caseRecordNotAssociatedEarlier");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		boolean notAssociated = true;

		List associatedCaseList = logCaseDataBean.getCaseDetailsVO()
				.getAssociatedCaseList();

		if (associatedCaseList != null) {
			for (Iterator iter = associatedCaseList.iterator(); iter.hasNext();) {
				AssociatedCaseVO asscCaseVO = (AssociatedCaseVO) iter.next();
				if (asscCaseVO.getCaseID().equalsIgnoreCase(caseSK)) {
					notAssociated = false;
					break;
				}
			}
		}
		return notAssociated;
	}

	/**
	 * 
	 * @param resultsVO
	 * @return
	 */
	private AssociatedCaseVO getAssociatedCaseRecord(
			CaseRecordSearchResultsVO resultsVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAssociatedCaseRecord");
		AssociatedCaseVO associatedCaseVO = new AssociatedCaseVO();
		associatedCaseVO.setCaseID(resultsVO.getCaseSK().toString());

		associatedCaseVO.setCreatedDate(resultsVO.getCreatedDate());
		associatedCaseVO.setStatus(resultsVO.getStatus());
		associatedCaseVO.setCaseType(resultsVO.getCaseType());
		associatedCaseVO.setStatus(resultsVO.getStatus());

		return associatedCaseVO;
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String deSelectAllCaseRecords(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "deSelectAllCaseRecords");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List searchResults = caseSearchDataBean.getSearchCaseList();
		logCaseDataBean.setSearchCaseSelectAll(false);
		logCaseDataBean.setSearchCaseDeSelectAll(true);
		dissociateSelCaseRecords(searchResults);
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * 
	 * @param caseList
	 */
	private void dissociateSelCaseRecords(List caseList) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "dissociateSelCaseRecords");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseRecordSearchResultsVO resultsVO = null;
		if (caseList != null && caseList.size() > 0) {

			for (Iterator iterator = caseList.iterator(); iterator.hasNext();) {

				resultsVO = (CaseRecordSearchResultsVO) iterator.next();
				resultsVO.setCheckBox(false);
				List associatedCaseList = logCaseDataBean.getCaseDetailsVO()
						.getAssociatedCaseList();

				String caseID = resultsVO.getCaseSK().toString();

				for (Iterator iter = associatedCaseList.iterator(); iter
						.hasNext();) {
					AssociatedCaseVO associatedCaseVO = (AssociatedCaseVO) iter
							.next();
					if (associatedCaseVO.getCaseID().equalsIgnoreCase(caseID)) {
						associatedCaseList.remove(associatedCaseVO);
						break;
					}
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public String associateCaseRecord() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "associateCaseRecord");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		//for ESPRD00741245
		logCaseDataBean.setPageFocusId("caseRecAssWithCaseTableFocusPage");
		logCaseDataBean.setCursorFocusValue("");
		// Begin - Added the code for the defect id ESPRD00684566
		FacesContext fc = FacesContext.getCurrentInstance();
		String str = (String) fc.getExternalContext().getRequestParameterMap()
				.get("rowIndex");
		// End - Added the code for the defect id ESPRD00684566
		logCaseDataBean.setSearchCaseSelectAll(false);
		logCaseDataBean.setSearchCaseDeSelectAll(false);
		caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.CASE_SEARCH_DATA_BEAN);//UC-PGM-CRM-018_ESPRD00388733_19JAN10
		// Begin - Modified the code for the defect id ESPRD00684566
		CaseRecordSearchResultsVO resultsVO = (CaseRecordSearchResultsVO) caseSearchDataBean
				.getSearchCaseList().get(Integer.parseInt(str));
		// End - Modified the code for the defect id ESPRD00684566

		/*
		 * modified for defect UC-PGM-CRM-018_ESPRD00388733_19JAN10 if
		 * (caseRecordNotAssociatedEarlier(resultsVO.getCaseSK().toString())) {
		 * AssociatedCaseVO associatedCaseVO =
		 * getAssociatedCaseRecord(resultsVO);
		 * logCaseDataBean.getCaseDetailsVO()
		 * .getAssociatedCaseList().add(associatedCaseVO); }
		 */
		AssociatedCaseVO associatedCaseVO = convertCaseRecordSearchResultsVOToAssociatedCaseVO(resultsVO);
		if (associatedCaseVO != null) {
			boolean isCaseVoExist = false;
			Iterator assCaseVoList_itr = logCaseDataBean
					.getCaseRecordsAssoWithCaseList().iterator();
			AssociatedCaseVO tempAssociatedCaseVO = null;
			while (assCaseVoList_itr.hasNext()) {
				tempAssociatedCaseVO = (AssociatedCaseVO) assCaseVoList_itr
						.next();
				if (tempAssociatedCaseVO.getCaseID() != null
						&& tempAssociatedCaseVO.getCaseID().equals(
								associatedCaseVO.getCaseID())) {
					isCaseVoExist = true;
					break;
				}
			}
			if (!isCaseVoExist) {
				logCaseDataBean.getTempAssociatedCaseRecordsList().add(
						associatedCaseVO);
				logCaseDataBean.getCaseRecordsAssoWithCaseList().add(
						associatedCaseVO);
			}
		}

		//EOf UC-PGM-CRM-018_ESPRD00388733_19JAN10
		logCaseDataBean.setShowCaseRecordsAssoWithCaseDataTable(true);
		return "success";
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String selectAllExistCaseRecords(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "selectAllExistCaseRecords");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List existingCaselist = logCaseDataBean.getExistingCaseRecordsList();
		logCaseDataBean.setExistCaseSelectAll(true);
		logCaseDataBean.setExistCaseDeSelectAll(false);
		logCaseDataBean.setShowCaseRecordsAssoWithCaseDataTable(true);
		if (existingCaselist != null && existingCaselist.size() > 0) {

			for (Iterator iterator = existingCaselist.iterator(); iterator
					.hasNext();) {
				AssociatedCaseVO resultsVO = (AssociatedCaseVO) iterator.next();
				if (!resultsVO.isCaseLink()) {
					resultsVO.setCaseLink(true);
					if (caseRecordNotAssociatedEarlier(resultsVO.getCaseID()
							.toString())) {
						logCaseDataBean.getCaseDetailsVO()
								.getAssociatedCaseList().add(resultsVO);
					}
				}
			}
		}

		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String deSelectAllExistCaseRecords(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "deSelectAllExistCaseRecords");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List existingCaselist = logCaseDataBean.getExistingCaseRecordsList();
		logCaseDataBean.setExistCaseSelectAll(false);
		logCaseDataBean.setExistCaseDeSelectAll(true);
		AssociatedCaseVO resultsVO = null;
		if (existingCaselist != null && existingCaselist.size() > 0) {

			for (Iterator iterator = existingCaselist.iterator(); iterator
					.hasNext();) {

				resultsVO = (AssociatedCaseVO) iterator.next();
				resultsVO.setCaseLink(false);
				List associatedCaseList = logCaseDataBean.getCaseDetailsVO()
						.getAssociatedCaseList();
				String caseID = resultsVO.getCaseID().toString();
				for (Iterator iter = associatedCaseList.iterator(); iter
						.hasNext();) {
					AssociatedCaseVO associatedCaseVO = (AssociatedCaseVO) iter
							.next();
					if (associatedCaseVO.getCaseID().equalsIgnoreCase(caseID)) {
						associatedCaseList.remove(associatedCaseVO);
						break;
					}
				}
			}
		}
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String associateExistingCaseToCase() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "associateExistingCaseToCase");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		int index = logCaseDataBean.getExistingCaseSearchRowIndex();

		logCaseDataBean.setExistCaseSelectAll(false);
		logCaseDataBean.setExistCaseDeSelectAll(false);

		AssociatedCaseVO resultsVO = (AssociatedCaseVO) logCaseDataBean
				.getExistingCaseRecordsList().get(index);

		if (resultsVO.isCaseLink()) {
			if (caseRecordNotAssociatedEarlier(resultsVO.getCaseID())) {
				logCaseDataBean.getCaseDetailsVO().getAssociatedCaseList().add(
						resultsVO);
				logCaseDataBean.setShowCaseRecordsAssoWithCaseDataTable(true);
			}
		} else {
			List associateCaseList = logCaseDataBean.getCaseDetailsVO()
					.getAssociatedCaseList();
			String caseID = resultsVO.getCaseID().toString();
			if (associateCaseList != null) {
				for (Iterator iter = associateCaseList.iterator(); iter
						.hasNext();) {
					AssociatedCaseVO asscCaseVO = (AssociatedCaseVO) iter
							.next();
					if (asscCaseVO.getCaseID().equalsIgnoreCase(caseID)) {
						associateCaseList.remove(asscCaseVO);
						break;
					}
				}
			}
		}
		return "success";
	}

	/**
	 * 
	 * @param event
	 */
	public void onStatusChange(ValueChangeEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "onStatusChange");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (event != null && !isNullOrEmptyField((String)event.getNewValue()) ) {
			String status = (String) event.getNewValue();
			
			//updating status date.
			if (!status.equals(logCaseDataBean.getCaseDetailsVO()
					.getPreviousStatus())) {
				logCaseDataBean.getCaseDetailsVO().setStatusDate(new Date());
			} else {
				logCaseDataBean.getCaseDetailsVO().setStatusDate(
						logCaseDataBean.getCaseDetailsVO()
								.getPreviousStatusDate());
			}

			if ("D".equals(logCaseDataBean.getCaseDetailsVO()
					.getCaseTypeBusinessUnitTypeCode())
					&& status.equalsIgnoreCase("C5")) {
				logCaseDataBean.setStatusClosedAppealUpheld(true);
			} else {
				logCaseDataBean.setStatusClosedAppealUpheld(false);
			}

			if ("D".equals(logCaseDataBean.getCaseDetailsVO()
					.getCaseTypeBusinessUnitTypeCode())
					&& (status.equalsIgnoreCase("CA")
							|| status.equalsIgnoreCase("C2") || status
							.equalsIgnoreCase("C4"))) {
				logCaseDataBean.setStatusClosedAndApproved(true);
			} else {
				logCaseDataBean.setStatusClosedAndApproved(false);
			}

			if (status.equalsIgnoreCase("C")) {
				logCaseDataBean.getCaseDetailsVO().setStatus("C");
				setDaysToClose(logCaseDataBean.getCaseDetailsVO());

				logCaseDataBean.setOpenDays(false);
			} else {
				logCaseDataBean.setOpenDays(true);
			}

		}
	}

	// Method added for defect ESPRD00350457
	public void enableDisableDispositionDate(ValueChangeEvent event) {
		log.info("==enableDisableDispositionDate called");
		//Modified for defect ESPRD00544909 --BR CON0365.0001.01
		//COMMENTED FOR THE DEFECT ESPRD00667570
		/*FacesContext context = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) context.getExternalContext()
				.getSession(true);
		httpSession.removeAttribute("DispositionDate");*/
		//COMMENTED END FOR THE DEFECT ESPRD00667570
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseEventsVO caseEventsVO = logCaseDataBean.getCaseEventsVO();
		if (event != null
				&& event.getNewValue() != null
				&& (event
						.getNewValue()
						.toString().trim()
						.equalsIgnoreCase(
								MaintainContactManagementUIConstants.CASE_EVENT_STATUS_TYPE) || (event
						.getNewValue().toString().trim()
						.equalsIgnoreCase(MaintainContactManagementUIConstants.EMPTY_STRING)))) {
			caseEventsVO.setDateDispositionFlag(true);
			caseEventsVO
					.setDispositionDateStr(ContactManagementConstants.EMPTY_STRING);
		} else {
			caseEventsVO.setDateDispositionFlag(false);
			String dtStr = ContactManagementHelper.dateConverter(new Date());
			//COMMENTED FOR THE DEFECT ESPRD00667570 AND ADDED THE BELOW CODE
			//httpSession.setAttribute("DispositionDate", dtStr);
			caseEventsVO.setDispositionDate(new Date());
			caseEventsVO.setDispositionDateStr(dtStr);
		}
		//for ESPRD00741245
		if(logCaseDataBean.isShowAddCaseEvents()){
			logCaseDataBean.setPageFocusId("addCaseEventPageFocus");
			logCaseDataBean.setCursorFocusValue("LOGCASEEVENTSTATUS0");
		}else{
			logCaseDataBean.setPageFocusId("addCaseEventPageFocus");
			logCaseDataBean.setCursorFocusValue("LOGCASEEVENTSTATUS1");
		}
	}

	//added for ESPRD00327996
	/**
	 * This method returns BusinessUnit list/WorkUnit list for the input
	 * "LobHierarchyLevelNumber"
	 * 
	 * @param lobHierarchyLevelNumber
	 */
	public List getBusinessOrWorkUnitList(Integer lobHierarchyLevelNumber) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List result_list = new ArrayList();
		try {

			LineOfBusinessHierarchy lob_hierarchy = null;
			Department department = null;
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

			List hierarchy_list = contactMaintenanceDelegate
					.getLobHierarchyDetails(null, lobHierarchyLevelNumber);

			if (hierarchy_list != null) {
				Map busineesUnitMap = new HashMap();
				Map workUnitMap = new HashMap();
				Map workUnitSKMap = new HashMap();

				for (Iterator list_itrator = hierarchy_list.iterator(); list_itrator
						.hasNext();) {

					lob_hierarchy = (LineOfBusinessHierarchy) list_itrator
							.next();

					if (ContactManagementConstants.THREE
							.equals(lobHierarchyLevelNumber)) {
						busineesUnitMap.put(lob_hierarchy
								.getLineOfBusinessHierarchySK(), lob_hierarchy
								.getLobHierarchyDescription());

						result_list.add(new SelectItem(lob_hierarchy
								.getLineOfBusinessHierarchySK().toString(),
								lob_hierarchy.getLobHierarchyDescription()));

					} else if (ContactManagementConstants.FOUR
							.equals(lobHierarchyLevelNumber)) {

						workUnitMap.put(lob_hierarchy
								.getLineOfBusinessHierarchySK(), lob_hierarchy
								.getLobHierarchyDescription());

						Set deptset = lob_hierarchy.getDepartments();
						List deptList = new ArrayList();
						deptList.add(deptset);
						for (Iterator deptList_itrator = deptset.iterator(); deptList_itrator
								.hasNext();) {
							department = (Department) deptList_itrator.next();
							if (department != null) {
								String wrkUnitSK = department.getWorkUnitSK()
										.toString();
								workUnitSKMap.put(department.getWorkUnitSK()
										.toString(), department.getName());
								result_list.add(new SelectItem(department
										.getWorkUnitSK().toString(), department
										.getName()));
							}
						}

						logCaseDataBean.setBusineesUnitMap(workUnitMap);
					}
				}
				logCaseDataBean.setBusineesUnitMap(busineesUnitMap);
				logCaseDataBean.setWorkUnitMap(workUnitMap);
				logCaseDataBean.setWorkUnitSKMap(workUnitSKMap);

			}

		} catch (Exception e) {
			log.error(
					"Exception getting or preparing lists in getBusinessOrWorkUnitList(Integer)"
							+ e.getMessage(), e);

		}
		return result_list;
	}

	//EOF ESPRD00327996
	// added for UC-PGM-CRM-018.3_ESPRD00328556_07NOV09
	/**
	 * This method combines the currently selected records in Exsiting case
	 * records with the associated records and sets them to CaseDetailsVO's
	 * associatedCaseList property
	 */
	public void setAssoiciatedCaseReordsToCaseDetailVO() {
		log.info("Inside setAssoiciatedCaseReordsToCaseDetailVO()");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List existing_list = logCaseDataBean.getExistingCaseRecordsList();

		logCaseDataBean.getCaseDetailsVO().setAssociatedCaseList(
				new ArrayList());

		if (existing_list != null && !existing_list.isEmpty()) {

			Iterator existing_list_itr = existing_list.iterator();
			AssociatedCaseVO associatedCaseVO = null;

			while (existing_list_itr.hasNext()) {
				associatedCaseVO = ((AssociatedCaseVO) existing_list_itr.next());

				if (associatedCaseVO.isCaseLink()) {
					logCaseDataBean.getCaseDetailsVO().getAssociatedCaseList()
							.add(associatedCaseVO);
				}
			}
			//Commented for Defect ID ESPRD00601677
			//adding current associated list
			//logCaseDataBean.getCaseDetailsVO().getAssociatedCaseList().addAll(logCaseDataBean.getCaseRecordsAssoWithCaseList());
		}
		//    UC-PGM-CRM-018.3_ESPRD00328556_10NOV09
		logCaseDataBean.setExistCaseSelectAll(false);
		logCaseDataBean.setExistCaseDeSelectAll(false);
		//EOF UC-PGM-CRM-018.3_ESPRD00328556_10NOV09

	}//EOF UC-PGM-CRM-018.3_ESPRD00328556_07NOV09

	//ESPRD00610465 starts
	public void setAssoiciatedCaseReordsToCRDetailVO() {

		log.info("Inside setAssoiciatedCaseReordsToCRDetailVO()");

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		List existing_list = logCaseDataBean.getExistingCRInfoList();

		//Commented for the defect ESPRD00674000
		//logCaseDataBean.getCaseDetailsVO().setAssociatedCrList(new
		// ArrayList());

		if (existing_list != null && !existing_list.isEmpty()) {

			Iterator existing_list_itr = existing_list.iterator();

			AssociatedCorrespondenceVO associatedCRVO = null;

			while (existing_list_itr.hasNext()) {

				associatedCRVO = ((AssociatedCorrespondenceVO) existing_list_itr
						.next());

				if (associatedCRVO.isLinksToCR()) {

					logCaseDataBean.getCaseDetailsVO().getAssociatedCrList()
							.add(associatedCRVO);

				}

			}

		}

		logCaseDataBean.setExistCrsSelectAll(false);

		logCaseDataBean.setExistCrsDeSelectAll(false);

	}

	/*
	 * @param link2Show The link2Show to set.
	 */
	public void setLink2Show(String link2Show) {

	}

	//	added UC-PGM-CRM-018.3_ESPRD00328556_10NOV09
	boolean deleteFlagForCaseRecAssoWithThisCase = false;

	/**
	 * @return Returns the deleteFlagForCaseRecAssoWithThisCase.
	 */
	public boolean isDeleteFlagForCaseRecAssoWithThisCase() {
		return deleteFlagForCaseRecAssoWithThisCase;
	}

	/**
	 * @param deleteFlagForCaseRecAssoWithThisCase
	 *            The deleteFlagForCaseRecAssoWithThisCase to set.
	 */
	public void setDeleteFlagForCaseRecAssoWithThisCase(
			boolean deleteFlagForCaseRecAssoWithThisCase) {
		this.deleteFlagForCaseRecAssoWithThisCase = deleteFlagForCaseRecAssoWithThisCase;
	}

	/** This method selects all Existing case records to be associated */
	public String selectAllExistingCaseRecords() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "selectAllExistingCaseRecords");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List existingCaselist = logCaseDataBean.getExistingCaseRecordsList();
		logCaseDataBean.setExistCaseSelectAll(true);
		logCaseDataBean.setExistCaseDeSelectAll(false);
		logCaseDataBean.setShowCaseRecordsAssoWithCaseDataTable(true);

		List caseAssoWithCaseList = new ArrayList();
		if (existingCaselist != null && existingCaselist.size() > 0) {
			AssociatedCaseVO associatedCaseVO = null;
			for (Iterator iterator = existingCaselist.iterator(); iterator
					.hasNext();) {
				associatedCaseVO = (AssociatedCaseVO) iterator.next();
				associatedCaseVO.setCaseLink(true);
				caseAssoWithCaseList.add(associatedCaseVO);

			}
			// Added for Defect ESPRD00601677
			logCaseDataBean
					.setCaseRecordsAssoWithCaseList(caseAssoWithCaseList);
			if (logCaseDataBean.getCaseRecordsAssoWithCaseList() != null) {
				if(log.isDebugEnabled()){
				log.debug(" logCaseDataBean.setCaseRecordsAssoWithCaseList size:  "
								+ logCaseDataBean
										.getCaseRecordsAssoWithCaseList()
										.size());
				}
			}
			// Ends for Defect ESPRD00601677
		}
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	/** This method deselects all Existing case records do not to associated */
	public String deSelectAllExistingCaseRecords() {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "deSelectAllExistingCaseRecords");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List existingCaselist = logCaseDataBean.getExistingCaseRecordsList();
		logCaseDataBean.setExistCaseSelectAll(false);
		logCaseDataBean.setExistCaseDeSelectAll(true);
		AssociatedCaseVO resultsVO = null;
		if (existingCaselist != null && existingCaselist.size() > 0) {
			for (Iterator iterator = existingCaselist.iterator(); iterator
					.hasNext();) {
				resultsVO = (AssociatedCaseVO) iterator.next();
				resultsVO.setCaseLink(false);

				// Added for Defect ID ESPRD00601677
				if (logCaseDataBean.getCaseRecordsAssoWithCaseList() != null) {
					logCaseDataBean.getCaseRecordsAssoWithCaseList().clear();
				}
				logCaseDataBean.setShowCaseRecordsAssoWithCaseDataTable(false);
				// END for Defect ESPRD00601677
			}
		}
		return ContactManagementConstants.EDMS_RENDER_SUCCESS;
	}

	//EOF UC-PGM-CRM-018.3_ESPRD00328556_10NOV09
	/**
	 * This method implements the BRC CON0332.0001.01 for defect ESPRD00359439
	 * 
	 * @param notifiedCaseSk,caseStepSeqNum
	 * @return boolean
	 */
	public boolean notifiedViaAlertforCaseSteps(String notifiedCaseSk,
			Integer caseStepSeqNum) {
		log.info("insde getAlertVoList");
		String userId = "";
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//Commented for Heap dump fix defect ESPRD00935080
		//Map userMap = logCaseDataBean.getUserIDAndWUMap();
		if (notifiedCaseSk != null) {
			/*userId = (String) userMap.get(notifiedCaseSk);
			log.debug("--useridWUmap 7::"+userId);*/
			ContactManagementHelper helper = new ContactManagementHelper();
			userId = helper.getUserIDByWUSK2(notifiedCaseSk);
			log.debug("++useridWUmap 7::"+userId);
		}

		List alertList = null;
		List alertVolist = null;
		CMDelegate delegate = new CMDelegate();
		MyTaskDOConverter converter = new MyTaskDOConverter();
		if (userId != null) {
			try {
				alertList = delegate.getAllAlert(userId);
			}

			catch (Exception e) {
				e.printStackTrace();
			}

			//	if (alertList != null || alertList.isEmpty()==false) { // Find
			// bug issue
			if (alertList != null && !alertList.isEmpty()) {
				alertVolist = converter.getAlertsVOList(alertList);
				String caseRecordNum = logCaseDataBean.getCaseRegardingVO()
						.getCaseRecordNumber();
				for (int i = 0; i < alertVolist.size(); i++) {
					MyAlertsVO myAlertsVO = (MyAlertsVO) alertVolist.get(i);
					if (myAlertsVO.getCaseSk() != null
							&& myAlertsVO.getCaseStepSeqNum() != null) {

						if (myAlertsVO.getCaseSk().equals(caseRecordNum)
								&& myAlertsVO.getCaseStepSeqNum().equals(
										caseStepSeqNum)) {

							return true;
						}
					}
				}

			}
		}
		return false;
	}

	//	added for defect ESPRD00357971
	/**
	 * This method returns LettersAndResponsesDataBean object.
	 * 
	 * @return LettersAndResponsesDataBean.
	 */
	public final LettersAndResponsesDataBean getLettersAndResponsesDataBeanFromContext() {

		log.info("Inside getLettersAndResponsesDataBeanFromContext");
		FacesContext fc = FacesContext.getCurrentInstance();
		LettersAndResponsesDataBean letterTemplateDataBean = (LettersAndResponsesDataBean) fc
				.getApplication().createValueBinding(
						"#{" + GlobalLetterConstants.LETTER_RESPONSES_DATA_BEAN
								+ "}").getValue(fc);
		return letterTemplateDataBean;
	}

	//EOF ESPRD00357971

	/**
	 * This method is to calculate number of days to close when the status of
	 * case changed to close
	 */
	private void setDaysToClose(CaseDetailsVO caseDetailVO) {
		log.info("setDaysToClose");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (ContactManagementConstants.STATUS_CLOSED
				.equalsIgnoreCase(caseDetailVO.getStatus())) {
			DateFormat dateFormat = new SimpleDateFormat(
					ContactManagementConstants.DATE_FORMAT);

			try {
				Date createdDate = dateFormat.parse(caseDetailVO
						.getCreatedDateStr());
				Date closingDate = dateFormat.parse(ContactManagementHelper
						.dateConverter(new Date()));

				int daysToClose = (int) ((closingDate.getTime() - createdDate
						.getTime()) / (ContactManagementConstants.LONG_1000
						* ContactManagementConstants.LONG_60
						* ContactManagementConstants.LONG_60 * ContactManagementConstants.LONG_24));
				caseDetailVO
						.setClosedDays(ContactManagementConstants.EMPTY_STRING
								+ daysToClose);

				logCaseDataBean.setOpenDays(false);
			} catch (ParseException e) {
				log.error(e.getMessage(), e);
			}
		}

	}

	/*
	 * // Added for ESPRD00357689 private boolean invalidEventDate = false;
	 *  
	 *//**
	    * @return Returns the invalidEventDate.
	    */
	/*
	 * public boolean isInvalidEventDate() { return invalidEventDate; }
	 *//**
	    * @param invalidEventDate
	    *            The invalidEventDate to set.
	    */
	/*
	 * public void setInvalidEventDate(boolean invalidEventDate) {
	 * this.invalidEventDate = invalidEventDate; } //EOF for ESPRD00357689
	 */
	/**
	 * @param map -
	 *            Map object containing the area code and element name.
	 * @param referenceDataConstant -
	 *            Element name.
	 * @param functionalArea -
	 *            Functional Area code.
	 * @return List - with valid values populated.
	 */
	private List getValidData(Map map, String referenceDataConstant,
			String functionalArea) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " getValidData");
		List validList = new ArrayList();
		// Added for the Defect ID: ESPRD00744001 
		if (!referenceDataConstant
				.equals(ReferenceServiceDataConstants.A_RSN_CD_APP)
				&& !referenceDataConstant
				.equals(ReferenceServiceDataConstants.DM_EDMS_WORK_UNIT_LEVEL_CD)
				&& !referenceDataConstant
				.equals(ReferenceServiceDataConstants.G_EDMS_DOC_TYPE_CD)) {
			validList.add(new SelectItem(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
					MaintainContactManagementUIConstants.EMPTY_STRING));
		}
		String validValueCodeDesc = null;
		List validValuesList = (List) map.get(functionalArea + "#"
				+ referenceDataConstant);

		int validValuesSize = validValuesList.size();
		for (int i = 0; i < validValuesSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) validValuesList
					.get(i);
			validValueCodeDesc = refVo.getValidValueCode() + "-"
					+ refVo.getShortDescription();
			validList.add(new SelectItem(refVo.getValidValueCode(),
					validValueCodeDesc));
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " getValidData");
		return validList;
	}

	/**
	 * @return Returns the loadValidValue.
	 */
	public String getLoadValidValue() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " getReferenceData");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List list = new ArrayList();
		HashMap map = null;
		logCaseDataBean.setPhysicianOverseeingList(new ArrayList());
		logCaseDataBean.setLobCodeList(new ArrayList());
		logCaseDataBean.setPriorityList(new ArrayList());
		logCaseDataBean.setStatusList(new ArrayList());
		logCaseDataBean.setDiagnosisCode12(new ArrayList());
		logCaseDataBean.setExamTest12(new ArrayList());
		logCaseDataBean.setProviderhos(new ArrayList());
		logCaseDataBean.setEdmsWorkunitLevelList(new ArrayList());
		logCaseDataBean.setEdmsDocTypeList(new ArrayList());
		//logCaseDataBean.setDiagnosisCode12(new ArrayList()); // Commented repeated code for performance fix
		logCaseDataBean.setEventAlertBasedOn(new ArrayList());

		logCaseDataBean.getPhysicianOverseeingList().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));
		logCaseDataBean.getLobCodeList().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

		logCaseDataBean.getPriorityList().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));
		logCaseDataBean.getStatusList().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));
		logCaseDataBean.getDiagnosisCode12().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

		logCaseDataBean.getExamTest12().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

		//Added by Infics for providerhospital
		logCaseDataBean.getProviderhos().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

		/** Added by ics GAP -6493 */
		logCaseDataBean.getEdmsWorkunitLevelList().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

		logCaseDataBean.getEdmsDocTypeList().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));
		/** END BY ICS GAP-6493 */

		logCaseDataBean.getDiagnosisCode12().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

		logCaseDataBean.getExamTest12().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

		//    Added By ICS CR-1875(UC-18a)

		logCaseDataBean.getEventAlertBasedOn().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

		logCaseDataBean.getDiagnosisCode12().add(
				new SelectItem(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
						MaintainContactManagementUIConstants.EMPTY_STRING));

		String userId = getUserID(); //"USERID2" ;
		logCaseDataBean.setLoggedInUserSK(getUserSK(userId)); // Added for the defect id ESPRD00702153_30NOV2011.
		ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
		String businessUnitDesc = null;

		try {

			List buinessUnitDescs = delegate.getBuisnessUnitsDescs(userId);
			
			// Commented for Log Case Portlet Unavailable issue
			/*
			 * if(buinessUnitDescs.size()>1){ businessUnitDesc =
			 * ContactManagementConstants.AllOthers; }else{ businessUnitDesc =
			 * (String)buinessUnitDescs.get(0); }
			 */

			// Added for Log Case Portlet Unavailable issue
			if (buinessUnitDescs != null) {
				if (buinessUnitDescs.size() == 1) {
					businessUnitDesc = (String) buinessUnitDescs.get(0);

				} else {
					businessUnitDesc = ContactManagementConstants.AllOthers;
				}
				logCaseDataBean.setBusinessUnitDesc(businessUnitDesc);//Modified for Performance Activity. can avoid multiple calls to DB
				log.debug("++++++++getBusinessUnitDesc+++++++++++"+logCaseDataBean.getBusinessUnitDesc());
			} else {
				businessUnitDesc = ContactManagementConstants.AllOthers;

			}

			//Ends
		} catch (LOBHierarchyFetchBusinessException e) {
			e.printStackTrace();
			log.debug(e);
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
			log.error(e.getMessage(), e);
		}
		//   End of adding by ICS CR-1875(UC-18a)

		try {
			ReferenceDataSearchVO referenceDataSearch = null;
			ReferenceServiceDelegate referenceServiceDelegate = null;
			ReferenceDataListVO referenceDataListVO = null;
			/** preparing criteria for LOB */
			list.add(getInputCriteria(ReferenceServiceDataConstants.R_LOB_CD,
					FunctionalAreaConstants.REFERENCE));

			/** preparing criteria for Agency Site */
			// Begin - Commented the list and moved to new method getLoadValidValuesForBCCP() for the performance fix.
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_BCCP_SCRNG_SITE_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForBCCP() for the performance fix.
			
			/** preparing criteria for Case Priority */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_PRTY_CD,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case Status */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case Biopsy findings */
			// Begin - Commented the list and moved to new method getLoadValidValuesForBCCP() for the performance fix.
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_BCCP_BIOPSY_FNDNG_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			/** preparing criteria for Case Recommendation */
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_BCCP_CASE_RCMND_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			/** preparing criteria for Case Recommendation */
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_BCCP_FINAL_CAN_STG_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			/** preparing criteria for Case Recommendation */
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_BCCP_TRMT_STARTED_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForBCCP() for the performance fix.
			
			/** preparing criteria for Case Application Type */
			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.A_RSN_CD_DEN,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			
			/** preparing criteria for Case Application Type */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_DDU_AUTH_REP_TY_CD,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case Close Type Code */
			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_DDU_CLOSE_TY_CD,
					FunctionalAreaConstants.GENERAL));*/

			/** preparing criteria for Case Application Type */
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_DDU_DENY_RSN_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			/** preparing criteria for Case Evaluation Type */
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_DDU_EVAL_TY,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			
			/** preparing criteria for Case Unusual Circumstances */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.A_RSN_CD_APP,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case Medical Diagnosis */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_DDU_MED_DIAG_CD,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case Psychiatric Diagnosis */
			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.A_LI_UM_CURR_COND_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			
			/** preparing criteria for Case Event type */
			/*
			 * list.add(getInputCriteria(
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD,
			 * FunctionalAreaConstants.GENERAL2));
			 */
			// Added by ICS CR-1875(UC-18a)
			/* Commented code to fix defect ESPRD00351920 starts */

			/*
			 * if(businessUnitDesc.equals("DDU")) { list.add(getInputCriteria(
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU,
			 * FunctionalAreaConstants.GENERAL));
			 * 
			 * }else
			 * if((businessUnitDesc.equals("MLS"))||(businessUnitDesc.startsWith("MLS"))) {
			 * list.add(getInputCriteria(
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_MLS,
			 * FunctionalAreaConstants.GENERAL)); }else if
			 * ((businessUnitDesc.equals("ARS"))||
			 * (businessUnitDesc.startsWith("ARS"))) {
			 * list.add(getInputCriteria(
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_ARS,
			 * FunctionalAreaConstants.GENERAL)); }else {
			 * list.add(getInputCriteria(
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
			 * FunctionalAreaConstants.GENERAL)); }
			 */
			/* Commented code to fix defect ESPRD00351920 End */

			/* Code added to Fix defect ESPRD00351920 Starts */

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU,
					FunctionalAreaConstants.GENERAL));

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_MLS,
					FunctionalAreaConstants.GENERAL));

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_ARS,
					FunctionalAreaConstants.GENERAL));

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
					FunctionalAreaConstants.GENERAL));

			/* Code added to Fix defect ESPRD00351920 End */

			//  End of adding by ICS CR-1875(UC-18a)
			/** preparing criteria for Case Event Status */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_ACT_CASE_EVENT_STAT_CD,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case Event Alert days */

			//Major save code starts
			/*
			 * list.add(getInputCriteria(
			 * ReferenceServiceDataConstants.G_DFLT_SEND_ALERT_DAYS_CD,
			 * FunctionalAreaConstants.GENERAL));
			 */

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_SEND_EVENT_ALERT_DAYS_CD,
					FunctionalAreaConstants.GENERAL));

			//Major save code ends

			/** preparing criteria for Case Event Alert based on */
			/*
			 * list.add(getInputCriteria(
			 * ReferenceServiceDataConstants.G_DFLT_ALERT_BASED_ON_COL_NAM,
			 * FunctionalAreaConstants.GENERAL2));
			 */
			// Added by ICS CR-1875(UC-18a)
			if (businessUnitDesc.equals("DDU")) {
				list
						.add(getInputCriteria(
								ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU,
								FunctionalAreaConstants.GENERAL));

				list
						.add(getInputCriteria(
								ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU,
								FunctionalAreaConstants.GENERAL));
				log.debug(" Adding to map : businessUnitDesc "
						+ businessUnitDesc);
				list
						.add(getInputCriteria(
								ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU,
								FunctionalAreaConstants.GENERAL));
			} else {
				list
						.add(getInputCriteria(
								ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH,
								FunctionalAreaConstants.GENERAL));

				list
						.add(getInputCriteria(
								ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH,
								FunctionalAreaConstants.GENERAL));
			}
			// End of adding by ICS CR-1875(UC-18a)

			/** preparing criteria for Case Event Alert status */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_ALERT_STAT_CD,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case Event Alert type codes */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_ALERT_TY_CD,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case Step Alert days codes */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD,
					FunctionalAreaConstants.GENERAL));
			//raja
			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.A_LI_UM_CERT_TY_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			//EOF raja

			/** preparing criteria for Case Step Completion based on codes */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case Step status codes */
			/* COMMENTED BY ICS DEFECT - ESPRD00300257 */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_ACT_CASE_STEP_STAT_CD,
					FunctionalAreaConstants.GENERAL));

			//            list.add(getInputCriteria(
			//                    ReferenceServiceDataConstants.G_CASE_STEP_STAT_CD,
			//                    FunctionalAreaConstants.GENERAL));
			/* END */

			/** preparing criteria for Case Level Of Care codes */
			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_DDU_LOC_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			
			/** preparing criteria for Case G_TEST_1_CD codes */
			/*
			 * list.add(getInputCriteria(
			 * ReferenceServiceDataConstants.G_TEST_1_CD,
			 * FunctionalAreaConstants.GENERAL2));
			 */
			//  Added by ICS CR-1875(18a)
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_DIAGNOSTIC_EXAM_CD,
					FunctionalAreaConstants.GENERAL));
			// End of adding by ICS CR-1875(UC-18a)

			/** preparing criteria for Case G_DIAGNOSTIC_EXAM_1_CD codes */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_DIAGNOSTIC_EXAM_1_CD,
					FunctionalAreaConstants.GENERAL));

			/** preparing criteria for Case G_CM_CASE_STEP_CD codes */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CM_CASE_STEP_CD,
					FunctionalAreaConstants.GENERAL));

			/** ADDED BY ICS GAP -6493 */
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.DM_EDMS_WORK_UNIT_LEVEL_CD,
					FunctionalAreaConstants.GENERAL));

			log.debug("edmsworkunitsize " + list.size());

			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_EDMS_DOC_TYPE_CD,
					FunctionalAreaConstants.GENERAL));
			log.debug("-- edmsdoctypesize >> :" + list.size());

			/** END BY ICS GAP -6493 */
			//			Added for defect ID :ESPRD00299900
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
					FunctionalAreaConstants.GENERAL));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
					FunctionalAreaConstants.GENERAL));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
					FunctionalAreaConstants.GENERAL));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
					FunctionalAreaConstants.GENERAL));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
					FunctionalAreaConstants.GENERAL));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
					FunctionalAreaConstants.GENERAL));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_PRTY_CD,
					FunctionalAreaConstants.GENERAL));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD,
					FunctionalAreaConstants.GENERAL));

			//EOF ESPRD00299900

			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*list.add(getInputCriteria(
					ReferenceServiceDataConstants.A_RSN_CD_VOD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			
			//			added for defect ID ESPRD00327996
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.LOG_CASE_ROUTING_SHOW,
					FunctionalAreaConstants.GENERAL));
			//			EOF defect ID ESPRD00327996
			referenceDataSearch = new ReferenceDataSearchVO();
			referenceDataSearch.setInputList(list);
			referenceServiceDelegate = new ReferenceServiceDelegate();

			//Pass the list to the delegate
			referenceDataListVO = new ReferenceDataListVO();
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);
			map = referenceDataListVO.getResponseMap();
			
			// Begin - Added Threads for the perfomance fix.
			Class[] argtypes = new Class[] { Map.class, String.class,
					String.class };
			Executor[] executor = new Executor[19];
			// End - Added Threads for the perfomance fix.
			
			//Added for defect ID :ESPRD00299900
			//Modified for defect ESPRD00935080 starts
			//logCaseDataBean.setCaseStatusValidValues(map);
			/***
			 * Keeping entire map in session will cause Heap dump. So the map is commented and wherever the reference exists, DB call 
			 * done to fetch the list.
			 */
		     //defect ESPRD00935080 ends

			//EOF ESPRD00299900
			//added for defect ID ESPRD00327996
			// Begin - Added a Thread executor[0] to load the values in the RoutingShowList() for the perfomance fix.
			executor[0] = call(this, "getLogDescData", new Object[] {
					map, ReferenceServiceDataConstants.LOG_CASE_ROUTING_SHOW, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setRoutingShowList(getLogDescData(map,
					ReferenceServiceDataConstants.LOG_CASE_ROUTING_SHOW,
					FunctionalAreaConstants.GENERAL, false));*/
			// End - Added a Thread executor[0] to load the values in the RoutingShowList() for the perfomance fix.
			
			//EOf ESPRD00327996
			// Begin - Added a Thread executor[1] to load the values in the LobCodeList() for the perfomance fix.
			executor[1] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.R_LOB_CD, FunctionalAreaConstants.REFERENCE},
					argtypes);
			/*logCaseDataBean.setLobCodeList(getValidData(map,
					ReferenceServiceDataConstants.R_LOB_CD,
					FunctionalAreaConstants.REFERENCE));*/
			// End - Added a Thread executor[1] to load the values in the RoutingShowList() for the perfomance fix.

			// Begin - Commented the list and moved to new method getLoadValidValuesForBCCP() for the performance fix.
			/*logCaseDataBean.setAgencySiteList(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_BCCP_SCRNG_SITE_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForBCCP() for the performance fix.

			// Begin - Added a Thread executor[2] to load the values in the PriorityList() for the perfomance fix.
			executor[2] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.G_CASE_PRTY_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setPriorityList(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_PRTY_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[2] to load the values in the PriorityList() for the perfomance fix.
			/*
			 * commented for defect ID ESPRD00299900
			 * setStatusList(getValidData(map,
			 * ReferenceServiceDataConstants.G_CASE_STAT_CD,
			 * FunctionalAreaConstants.GENERAL)); EOF ESPRD00299900
			 */
			//          ESPRD00328556
			// Begin - Added a Thread executor[3] to load the values in the AllCaseStatusList() for the perfomance fix.
			executor[3] = call(this, "getLogDescData", new Object[] {
					map, ReferenceServiceDataConstants.G_CASE_STAT_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setAllCaseStatusList(getLogDescData(map,
					ReferenceServiceDataConstants.G_CASE_STAT_CD,
					FunctionalAreaConstants.GENERAL, false));*/
			// End - Added a Thread executor[3] to load the values in the AllCaseStatusList() for the perfomance fix.
			//EOF ESPRD00328556

			// Begin - Commented the list and moved to new method getLoadValidValuesForBCCP() for the performance fix.
			/*logCaseDataBean.setBiopsyFindingsList(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_BCCP_BIOPSY_FNDNG_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			/*logCaseDataBean.setRecommenList(getValidData(map,
					ReferenceServiceDataConstants.G_BCCP_CASE_RCMND_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			/*logCaseDataBean.setStageBCCPList(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_BCCP_FINAL_CAN_STG_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			/*logCaseDataBean.setTreatmentStartedList(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_BCCP_TRMT_STARTED_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForBCCP() for the performance fix.
			
			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*logCaseDataBean.setApplicationTypeList(getValidData(map,
					ReferenceServiceDataConstants.A_RSN_CD_DEN,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForBCCP() for the performance fix.
			
			/*
			 * modified setAuthorisedRepList(getValidData(map,
			 * ReferenceServiceDataConstants.G_CASE_DDU_AUTH_REP_TY_CD,
			 * FunctionalAreaConstants.GENERAL));
			 */
			
			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*logCaseDataBean.setAuthorisedRepList(getLogDescData(map,
					ReferenceServiceDataConstants.A_RSN_CD_VOD,
					FunctionalAreaConstants.GENERAL, false));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			
			//EOF modification

			// Added for defect id ESPRD00299791
			/*
			 * List referenceDataAsSelectItems =
			 * getReferenceDataAsSelectItems();
			 * logCaseDataBean.setPhysicianOverseeingList(referenceDataAsSelectItems);
			 *///ESPRD00517164_UC-PGM-CRM-18_16SEP2010
			// Ends
			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*logCaseDataBean.setCloseCodeList(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_DDU_CLOSE_TY_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			/*logCaseDataBean.setDenialReasonList(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_DDU_DENY_RSN_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			/*logCaseDataBean.setEvaluationTypeList(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_DDU_EVAL_TY,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.

			// Begin - Added a Thread executor[4] to load the values in the UnusualCircumstancesList() for the perfomance fix.
			executor[4] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.A_RSN_CD_APP, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setUnusualCircumstancesList(getValidData(map,
					ReferenceServiceDataConstants.A_RSN_CD_APP,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[4] to load the values in the UnusualCircumstancesList() for the perfomance fix.

			//Modified for defect ESPRD00538612 starts
			/*
			 * logCaseDataBean.setMedicalDiagnosisList(getValidData(map,
			 * ReferenceServiceDataConstants.G_CASE_DDU_MED_DIAG_CD,
			 * FunctionalAreaConstants.GENERAL));
			 */
			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*logCaseDataBean.setMedicalDiagnosisList(getValidData(map,
					ReferenceServiceDataConstants.A_LI_UM_CERT_TY_CD,
					FunctionalAreaConstants.GENERAL));*/
			
			//defect ESPRD00538612 ends
			/*logCaseDataBean.setPsychiatricDiagnosisList(getValidData(map,
					ReferenceServiceDataConstants.A_LI_UM_CURR_COND_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			
			/*
			 * setEventTypeList(getValidData(map,
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD,
			 * FunctionalAreaConstants.GENERAL2));
			 */
			//          Added by ICS CR-1875(UC-18a)
			/* Commented code to Fix defect ESPRD00351920 Starts */

			/*
			 * if(businessUnitDesc.equals("DDU")) {
			 * setEventTypeList(getValidData(map,
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU,
			 * FunctionalAreaConstants.GENERAL)); }else
			 * if((businessUnitDesc.equals("MLS"))||(businessUnitDesc.startsWith("MLS"))) {
			 * setEventTypeList(getValidData(map,
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_MLS,
			 * FunctionalAreaConstants.GENERAL)); }else
			 * if((businessUnitDesc.equals("ARS"))||(businessUnitDesc.startsWith("ARS"))) {
			 * setEventTypeList(getValidData(map,
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_ARS,
			 * FunctionalAreaConstants.GENERAL)); } else {
			 * setEventTypeList(getValidData(map,
			 * ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
			 * FunctionalAreaConstants.GENERAL)); }
			 */

			/* Commented code to Fix defect ESPRD00351920 End */
			// End of adding by ICS CR-1875(UC-18a)
			// Begin - Added a Thread executor[5] to load the values in the EventStatus() for the perfomance fix.
			executor[5] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.G_ACT_CASE_EVENT_STAT_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setEventStatus(getValidData(map,
					ReferenceServiceDataConstants.G_ACT_CASE_EVENT_STAT_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[5] to load the values in the EventStatus() for the perfomance fix.
			
			//Major save code starts

			/*
			 * setEventAlertDaysList(getValidData(map,
			 * ReferenceServiceDataConstants.G_DFLT_SEND_ALERT_DAYS_CD,
			 * FunctionalAreaConstants.GENERAL));
			 */

			// Begin - Added a Thread executor[6] to load the values in the EventAlertDaysList() for the perfomance fix.
			executor[6] = call(this, "getValidDataNew", new Object[] {
					map, ReferenceServiceDataConstants.G_SEND_EVENT_ALERT_DAYS_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setEventAlertDaysList(getValidDataNew(map,
					ReferenceServiceDataConstants.G_SEND_EVENT_ALERT_DAYS_CD,
					FunctionalAreaConstants.GENERAL));*/			
			// End - Added a Thread executor[6] to load the values in the EventAlertDaysList() for the perfomance fix.
			//Major save code ends

			/*
			 * setEventAlertBasedOn(getValidData(map,
			 * ReferenceServiceDataConstants.G_DFLT_ALERT_BASED_ON_COL_NAM,
			 * FunctionalAreaConstants.GENERAL2));
			 */
			//   Added by ICS CR-1875(UC-18a)
			if (businessUnitDesc.equals("DDU")) {
			// Begin - Added a Thread executor[7] to load the values in the EventAlertBasedOn() for the perfomance fix.
				executor[7] = call(this, "getValidData", new Object[] {
						map, ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU, FunctionalAreaConstants.GENERAL},
						argtypes);
				/*logCaseDataBean
						.setEventAlertBasedOn(getValidData(
								map,
								ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU,
								FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[7] to load the values in the EventAlertBasedOn() for the perfomance fix.

			// Begin - Added a Thread executor[8] to load the values in the StepAlertBasedOn() for the perfomance fix.
				executor[8] = call(this, "getValidData", new Object[] {
						map, ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU, FunctionalAreaConstants.GENERAL},
						argtypes);
				/*logCaseDataBean
						.setStepAlertBasedOn(getValidData(
								map,
								ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_DDU,
								FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[8] to load the values in the StepAlertBasedOn() for the perfomance fix.
				log.debug("inside if G_CMPLTN_BASED_ON_COL_NAM_DDU===== >>>>>");

			// Begin - Added a Thread executor[9] to load the values in the StepCompBasedOn() for the perfomance fix.
				executor[9] = call(this, "getValidData", new Object[] {
						map, ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU, FunctionalAreaConstants.GENERAL},
						argtypes);
				/*logCaseDataBean
						.setStepCompBasedOn(getValidData(
								map,
								ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM_DDU,
								FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[9] to load the values in the StepCompBasedOn() for the perfomance fix.
				log.debug("inside if===== >>>>> 222222");

			} else {
			// Begin - Added a Thread executor[7] to load the values in the EventAlertBasedOn() for the perfomance fix.
				executor[7] = call(this, "getValidData", new Object[] {
						map, ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH, FunctionalAreaConstants.GENERAL},
						argtypes);
				/*logCaseDataBean
						.setEventAlertBasedOn(getValidData(
								map,
								ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH,
								FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[7] to load the values in the EventAlertBasedOn() for the perfomance fix.
				
			// Begin - Added a Thread executor[8] to load the values in the StepAlertBasedOn() for the perfomance fix.
				executor[8] = call(this, "getValidData", new Object[] {
						map, ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH, FunctionalAreaConstants.GENERAL},
						argtypes);
				/*logCaseDataBean
						.setStepAlertBasedOn(getValidData(
								map,
								ReferenceServiceDataConstants.G_STEP_ALERT_BASED_ON_COL_OTH,
								FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[8] to load the values in the StepAlertBasedOn() for the perfomance fix.
				
			// Begin - Added a Thread executor[9] to load the values in the StepCompBasedOn() for the perfomance fix.
				executor[9] = call(this, "getValidData", new Object[] {
						map, ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM, FunctionalAreaConstants.GENERAL},
						argtypes);
				/*logCaseDataBean
						.setStepCompBasedOn(getValidData(
								map,
								ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM,
								FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[9] to load the values in the StepCompBasedOn() for the perfomance fix.	
			}
			// End of adding by ICS CR-1875(UC-18a)

			// Begin - Added a Thread executor[10] to load the values in the AlertStatusList() for the perfomance fix.
			executor[10] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.G_ALERT_STAT_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setAlertStatusList(getValidData(map,
					ReferenceServiceDataConstants.G_ALERT_STAT_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[10] to load the values in the AlertStatusList() for the perfomance fix.
			
			// Begin - Added a Thread executor[11] to load the values in the AlertType() for the perfomance fix.
			executor[11] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.G_ALERT_TY_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setAlertType(getValidData(map,
					ReferenceServiceDataConstants.G_ALERT_TY_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[11] to load the values in the AlertType() for the perfomance fix.
			
			// Begin - Added a Thread executor[12] to load the values in the StepAlertDays() for the perfomance fix.
			executor[12] = call(this, "getValidDataNew", new Object[] {
					map, ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setStepAlertDays(getValidDataNew(map,
					ReferenceServiceDataConstants.G_SEND_ALERT_DAYS_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[12] to load the values in the StepAlertDays() for the perfomance fix.
			

			/*
			 * Commented for 690454
			 * logCaseDataBean.setStepCompBasedOn(getValidData(map,
			 * ReferenceServiceDataConstants.G_CMPLTN_BASED_ON_COL_NAM,
			 * FunctionalAreaConstants.GENERAL));
			 */

			/* COMMENTED BY ICS DEFECT - ESPRD00300257 */
			// Begin - Added a Thread executor[13] to load the values in the StepStatusList() for the perfomance fix.
			executor[13] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.G_ACT_CASE_STEP_STAT_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setStepStatusList(getValidData(map,
					ReferenceServiceDataConstants.G_ACT_CASE_STEP_STAT_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[13] to load the values in the StepStatusList() for the perfomance fix.
			
			//            setStepStatusList(getValidData(map,
			//                    ReferenceServiceDataConstants.G_CASE_STEP_STAT_CD,
			//                    FunctionalAreaConstants.GENERAL));
			/* END */

			// Begin - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			/*logCaseDataBean.setLevelOfCareList(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_DDU_LOC_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Commented the list and moved to new method getLoadValidValuesForDDU() for the performance fix.
			
			/*
			 * setDiagnosisCode12(getValidData(map,
			 * ReferenceServiceDataConstants.G_TEST_1_CD,
			 * FunctionalAreaConstants.GENERAL2));
			 */
			//   Added by ICS CR-1875(UC-18a)
			// Begin - Added a Thread executor[14] to load the values in the DiagnosisCode12() for the perfomance fix.
			executor[14] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.G_CASE_DIAGNOSTIC_EXAM_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setDiagnosisCode12(getValidData(map,
					ReferenceServiceDataConstants.G_CASE_DIAGNOSTIC_EXAM_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[14] to load the values in the DiagnosisCode12() for the perfomance fix.
			// End of adding by ICS CR-1875(UC-18a)

			/*
			 * Code commented for temporary pirpose need to uncomment before
			 * check in
			 */
			// Begin - Added a Thread executor[15] to load the values in the ExamTest12() for the perfomance fix.
			executor[15] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.G_DIAGNOSTIC_EXAM_1_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setExamTest12(getValidData(map,
					ReferenceServiceDataConstants.G_DIAGNOSTIC_EXAM_1_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[15] to load the values in the ExamTest12() for the perfomance fix.
			
			// Begin - Added a Thread executor[16] to load the values in the CaseStepCodeList() for the perfomance fix.
			executor[16] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.G_CM_CASE_STEP_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setCaseStepCodeList(getValidData(map,
					ReferenceServiceDataConstants.G_CM_CASE_STEP_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[16] to load the values in the CaseStepCodeList() for the perfomance fix.
			
			/** ADDED BY ICS GAP -6493 */
			// Begin - Added a Thread executor[17] to load the values in the EdmsWorkunitLevelList() for the perfomance fix.
			executor[17] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.DM_EDMS_WORK_UNIT_LEVEL_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setEdmsWorkunitLevelList(getValidData(map,
					ReferenceServiceDataConstants.DM_EDMS_WORK_UNIT_LEVEL_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[17] to load the values in the EdmsWorkunitLevelList() for the perfomance fix.
			
			// Begin - Added a Thread executor[18] to load the values in the EdmsDocTypeList() for the perfomance fix.
			executor[18] = call(this, "getValidData", new Object[] {
					map, ReferenceServiceDataConstants.G_EDMS_DOC_TYPE_CD, FunctionalAreaConstants.GENERAL},
					argtypes);
			/*logCaseDataBean.setEdmsDocTypeList(getValidData(map,
					ReferenceServiceDataConstants.G_EDMS_DOC_TYPE_CD,
					FunctionalAreaConstants.GENERAL));*/
			// End - Added a Thread executor[18] to load the values in the EdmsDocTypeList() for the perfomance fix.
			
			// Begin - Setting the lists into the databean for the perfomance fix.			
			logCaseDataBean.setRoutingShowList((List) executor[0].get());
			logCaseDataBean.setLobCodeList((List) executor[1].get());
			logCaseDataBean.setPriorityList((List) executor[2].get());
			logCaseDataBean.setAllCaseStatusList((List) executor[3].get());
			logCaseDataBean.setUnusualCircumstancesList((List) executor[4].get());
			logCaseDataBean.setEventStatus((List) executor[5].get());
			logCaseDataBean.setEventAlertDaysList((List) executor[6].get());
			logCaseDataBean.setEventAlertBasedOn((List) executor[7].get());
			logCaseDataBean.setStepAlertBasedOn((List) executor[8].get());
			logCaseDataBean.setStepCompBasedOn((List) executor[9].get());
			logCaseDataBean.setAlertStatusList((List) executor[10].get());
			logCaseDataBean.setAlertType((List) executor[11].get());
			logCaseDataBean.setStepAlertDays((List) executor[12].get());
			logCaseDataBean.setStepStatusList((List) executor[13].get());
			logCaseDataBean.setDiagnosisCode12((List) executor[14].get());
			logCaseDataBean.setExamTest12((List) executor[15].get());
			logCaseDataBean.setCaseStepCodeList((List) executor[16].get());
			logCaseDataBean.setEdmsWorkunitLevelList((List) executor[17].get());
			logCaseDataBean.setEdmsDocTypeList((List) executor[18].get());
			Collections.sort(logCaseDataBean.getEventAlertDaysList());
			Collections.sort(logCaseDataBean.getStepAlertDays());
			// End - Setting the lists into the databean for the perfomance fix.
			
			/** END BY ICS GAP -6493 */
			getSystemList();
			getUsersList();
			logCaseDataBean.setValidValuesFlag(false);

		} catch (ReferenceServiceRequestException e) {
			e.printStackTrace();
			log.error("Exception while getting Valid Values ");
		} catch (SystemListNotFoundException e) {
			e.printStackTrace();
			log.error("Exception while getting Valid Values ");
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getReferenceData");
		return loadValidValue;
	}

	// Begin - Added new method to load the valid values for the BCCP for the performance fix.
	/**
	* This method is used to load the valid values for the BCCP.
	* @return 
	*/
	public String getLoadValidValuesForBCCP() {
		
		ReferenceDataListVO referenceDataListVO = null;
		ReferenceServiceDelegate referenceServiceDelegate = null;
		ReferenceDataSearchVO referenceDataSearch = null;
		referenceDataSearch = new ReferenceDataSearchVO();
		HashMap map = new HashMap();
		List list = new ArrayList();
		
		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_CASE_BCCP_SCRNG_SITE_CD,
				FunctionalAreaConstants.GENERAL));

		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_CASE_BCCP_BIOPSY_FNDNG_CD,
				FunctionalAreaConstants.GENERAL));

		/** preparing criteria for Case Close Type Code */
		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_BCCP_CASE_RCMND_CD,
				FunctionalAreaConstants.GENERAL));

		/** preparing criteria for Case Application Type */
		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_CASE_BCCP_FINAL_CAN_STG_CD,
				FunctionalAreaConstants.GENERAL));

		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_CASE_BCCP_TRMT_STARTED_CD,
				FunctionalAreaConstants.GENERAL));

		referenceDataSearch.setInputList(list);
		referenceServiceDelegate = new ReferenceServiceDelegate();
		try {
		referenceDataListVO = referenceServiceDelegate
				.getReferenceData(referenceDataSearch);
		map = referenceDataListVO.getResponseMap();
		
		logCaseDataBean.setAgencySiteList(getValidData(map,
				ReferenceServiceDataConstants.G_CASE_BCCP_SCRNG_SITE_CD,
				FunctionalAreaConstants.GENERAL));
		
		logCaseDataBean.setBiopsyFindingsList(getValidData(map,
				ReferenceServiceDataConstants.G_CASE_BCCP_BIOPSY_FNDNG_CD,
				FunctionalAreaConstants.GENERAL));
		
		logCaseDataBean.setRecommenList(getValidData(map,
				ReferenceServiceDataConstants.G_BCCP_CASE_RCMND_CD,
				FunctionalAreaConstants.GENERAL));
		
		logCaseDataBean.setStageBCCPList(getValidData(map,
				ReferenceServiceDataConstants.G_CASE_BCCP_FINAL_CAN_STG_CD,
				FunctionalAreaConstants.GENERAL));
		
		logCaseDataBean.setTreatmentStartedList(getValidData(map,
				ReferenceServiceDataConstants.G_CASE_BCCP_TRMT_STARTED_CD,
				FunctionalAreaConstants.GENERAL));
		
		} catch (ReferenceServiceRequestException e) {
			e.printStackTrace();
			log.error("Exception while getting Valid Values ");
		} catch (SystemListNotFoundException e) {
			e.printStackTrace();
			log.error("Exception while getting Valid Values ");
		}
		return "";
	}
	// End - Added new method to load the valid values for the BCCP for the performance fix.

	// Begin - Added new method to load the valid values for the DDU for the performance fix.
	/**
	* This method is used to load the valid values for the DDU.
	* @return 
	*/
	public String getLoadValidValuesForDDU() {
		
		ReferenceDataListVO referenceDataListVO = null;
		ReferenceServiceDelegate referenceServiceDelegate = null;
		ReferenceDataSearchVO referenceDataSearch = null;
		referenceDataSearch = new ReferenceDataSearchVO();
		HashMap map = new HashMap();
		List list = new ArrayList();
		
		list.add(getInputCriteria(
				ReferenceServiceDataConstants.A_RSN_CD_DEN,
				FunctionalAreaConstants.GENERAL));

		list.add(getInputCriteria(
				ReferenceServiceDataConstants.A_RSN_CD_VOD,
				FunctionalAreaConstants.GENERAL));

		/** preparing criteria for Case Close Type Code */
		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_CASE_DDU_EVAL_TY,
				FunctionalAreaConstants.GENERAL));

		/** preparing criteria for Case Application Type */
		list.add(getInputCriteria(
				ReferenceServiceDataConstants.A_LI_UM_CERT_TY_CD,
				FunctionalAreaConstants.GENERAL));

		/** preparing criteria for Case Medical Diagnosis */
		list.add(getInputCriteria(
				ReferenceServiceDataConstants.A_LI_UM_CURR_COND_CD,
				FunctionalAreaConstants.GENERAL));

		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_CASE_DDU_LOC_CD,
				FunctionalAreaConstants.GENERAL));

		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_CASE_DDU_DENY_RSN_CD,
				FunctionalAreaConstants.GENERAL));
		list.add(getInputCriteria(
				ReferenceServiceDataConstants.G_CASE_DDU_CLOSE_TY_CD,
				FunctionalAreaConstants.GENERAL));
			
		referenceDataSearch.setInputList(list);
		referenceServiceDelegate = new ReferenceServiceDelegate();
		try {
		referenceDataListVO = referenceServiceDelegate
				.getReferenceData(referenceDataSearch);
		map = referenceDataListVO.getResponseMap();
		
		logCaseDataBean.setApplicationTypeList(getValidData(map,
		ReferenceServiceDataConstants.A_RSN_CD_DEN,
		FunctionalAreaConstants.GENERAL));
		logCaseDataBean.setAuthorisedRepList(getLogDescData(map,
		ReferenceServiceDataConstants.A_RSN_CD_VOD,
		FunctionalAreaConstants.GENERAL));
		logCaseDataBean.setEvaluationTypeList(getValidData(map,
		ReferenceServiceDataConstants.G_CASE_DDU_EVAL_TY,
		FunctionalAreaConstants.GENERAL));
		logCaseDataBean.setMedicalDiagnosisList(getValidData(map,
		ReferenceServiceDataConstants.A_LI_UM_CERT_TY_CD,
		FunctionalAreaConstants.GENERAL));
		logCaseDataBean.setPsychiatricDiagnosisList(getValidData(map,
		ReferenceServiceDataConstants.A_LI_UM_CURR_COND_CD,
		FunctionalAreaConstants.GENERAL));
		logCaseDataBean.setLevelOfCareList(getValidData(map,
		ReferenceServiceDataConstants.G_CASE_DDU_LOC_CD,
		FunctionalAreaConstants.GENERAL));
		logCaseDataBean.setDenialReasonList(getValidData(map,
				ReferenceServiceDataConstants.G_CASE_DDU_DENY_RSN_CD,
				FunctionalAreaConstants.GENERAL));
		logCaseDataBean.setCloseCodeList(getValidData(map,
		ReferenceServiceDataConstants.G_CASE_DDU_CLOSE_TY_CD,
		FunctionalAreaConstants.GENERAL));
		} catch (ReferenceServiceRequestException e) {
			e.printStackTrace();
			log.error("Exception while getting Valid Values ");
		} catch (SystemListNotFoundException e) {
			e.printStackTrace();
			log.error("Exception while getting Valid Values ");
		}
		return "";
	}
	// End - Added new method to load the valid values for the DDU for the performance fix.
	
	/**
	 * @param loadValidValue
	 *            The loadValidValue to set.
	 */
	public void setLoadValidValue(String loadValidValue) {

	}

	/**
	 * This method fetches the InputCriteria object with the parmeters set for
	 * functional area and reference name.
	 * 
	 * @param referenceDataConstant -
	 *            Element name.
	 * @param functionalArea -
	 *            Functional Area code.
	 * @return - InputCriteria
	 */
	private InputCriteria getInputCriteria(String referenceDataConstant,
			String functionalArea) {
		log.debug("preparing InputCriteria for ## " + referenceDataConstant);
		InputCriteria inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(functionalArea);
		inputCriteria.setElementName(referenceDataConstant);
		return inputCriteria;
	}

	/**
	 * @param map -
	 *            Map object containing the area code and element name.
	 * @param referenceDataConstant -
	 *            Element name.
	 * @param functionalArea -
	 *            Functional Area code.
	 * @param isIncludeEmpty -
	 *            indicates that the returning list will contains default empty
	 *            option or not indicating by the boolean value
	 * @return List - with valid values populated.
	 */
	 // Begin - Modified the method by removing the parameter isIncludeEmpty for the performance fix.
	private List getLogDescData(Map map, String referenceDataConstant,
			String functionalArea) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getLogDescData");
		List validList = new ArrayList();

		/*if (isIncludeEmpty) {
			validList.add(new SelectItem(
					MaintainContactManagementUIConstants.EMPTY_STRING,
					MaintainContactManagementUIConstants.EMPTY_STRING));
		}*/

		List validValuesList = (List) map.get(functionalArea + "#"
				+ referenceDataConstant);

		int validValuesSize = validValuesList.size();
		for (int i = 0; i < validValuesSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) validValuesList
					.get(i);

			validList.add(new SelectItem(refVo.getValidValueCode(), refVo
					.getLongDescription()));
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getLogDescData");
		return validList;
	}
	// End - Modified the method by removing the parameter isIncludeEmpty for the performance fix.

	//  Added for defect id ESPRD00299791
	private List getReferenceDataAsSelectItems() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getReferenceDataAsSelectItems");
		List referenceServiceVOList = null;
		CaseDelegate caseDelegate = new CaseDelegate();
		try {
			referenceServiceVOList = caseDelegate
					.getPhysicianOverSeeingCareDetails();
			log.info("referenceServiceVOList" + referenceServiceVOList.size());
		} catch (CaseFetchDataException caseFetchDataException) {
			log.error(caseFetchDataException.getMessage(), caseFetchDataException);
		}
		List referenceDataAsSelectItems = new ArrayList();
		Iterator it = referenceServiceVOList.iterator();
		Object[] physicianOverSeeingArray = null;
		while (it.hasNext()) {
			physicianOverSeeingArray = (Object[]) it.next();

			String firstName = (String) physicianOverSeeingArray[1];
			String lastName = (String) physicianOverSeeingArray[2];
			String entityType = (String) physicianOverSeeingArray[3];

			if (lastName != null && lastName.length() > 0) {
				referenceDataAsSelectItems.add(new SelectItem(entityType + "-"
						+ lastName + ", " + firstName));
			} else {
				referenceDataAsSelectItems.add(new SelectItem(entityType + "-"
						+ firstName));
			}
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getReferenceDataAsSelectItems");
		return referenceDataAsSelectItems;
	}

	/**
	 * It will get the system lists.
	 *  
	 */
	private void getSystemList() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getSystemList");
		SystemListDelegate listDelegate = new SystemListDelegate();
		List pdList = new ArrayList();
		Map pdIDMap = new HashMap();
		Map pdValMap = new HashMap();
		// Defect Fix: ESPRD00675155
		//  pdList.add(new SelectItem("", "Please Select One"));
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		try {
			// getting Short Desc
			List shortDesclist = listDelegate.getSystemList("G_SHORT_DESC",
					"MED", "G1");
			log.debug("SystemList (Short desc) size is $$ "
					+ shortDesclist.size());
			if (!shortDesclist.isEmpty()) {
				logCaseDataBean.setCaseTypeShortDesc(shortDesclist);
				log
						.debug("final set size of SystemListDetails (Short desc) is $$ "
								+ logCaseDataBean.getCaseTypeShortDesc().size());
			}

			// getting Holidays list
			List holidaysList = listDelegate
					.getSystemList("DM_DT", "MED", "G1");
			log
					.debug("SystemList (Holidays) size is $$ "
							+ holidaysList.size());
			if (!holidaysList.isEmpty()) {
				logCaseDataBean.setHolidaysList(holidaysList);
				log
						.debug("final set size of SystemListDetails (Holidays) is $$ "
								+ logCaseDataBean.getHolidaysList().size());
			}

			// getting Picture Date list
			SystemList systemList = listDelegate.getSystemListDetail(Long
					.valueOf(5), "K1"); /* FIND BUGS_FIX */
			log.debug("SystemList (Picture Date) is $$ " + systemList);
			if (systemList != null) {
				Set sysDetSet = systemList.getSystemListDetails();
				int i = 0;
				//SystemListDetail detail = null;
				if (sysDetSet != null && !sysDetSet.isEmpty()) {
					//Iterator iterator = sysDetSet.iterator();
					for (Iterator iter = sysDetSet.iterator(); iter.hasNext();) {
						i++;
						SystemListDetail element = (SystemListDetail) iter
								.next();
						pdList.add(new SelectItem("p" + i, element
								.getEndingValue()));
						pdIDMap.put("p" + i, element.getEndingValue());
						pdValMap.put(element.getEndingValue(), "p" + i);
					}
				}
			}
			//Defect Fix: ESPRD00675155
			logCaseDataBean.setPictureDateList(new ContactManagementHelper()
					.sortDate(pdList));
			logCaseDataBean.setPicIDMap(pdIDMap);
			logCaseDataBean.setPicValMap(pdValMap);
			log
					.debug("final set size of SystemListDetails (Picture date) is $$ "
							+ logCaseDataBean.getPictureDateList().size());
		} catch (SystemListNotFoundException e) {
			e.printStackTrace();
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getSystemList");
	}

	/**
	 * @return Returns the usersList.
	 */
	public String getUsersList() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getUsersList");
		CMDelegate cMDelegate = new CMDelegate();
		List userData = null;
		ArrayList users = new ArrayList();
		EnterpriseUser enterpriseUser = null;
		String firstName = null;
		String lastName = null;
		String name = null;
		String userIdName = null;
		//Start Add By ICS
		//String userId = null;
		//End By ICS
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		 //Commented for Heap dump fix defect ESPRD00935080 starts
		//Map usersMap = new HashMap();
		//Map userIDMap = new HashMap();
		List userIDList = new ArrayList();
		//Map userWUAndID = new HashMap();
		List stepUsersList = new ArrayList();
		//Map userName = new HashMap();
		/* FIND BUGS_FIX */
		// List userListLOB = new ArrayList();
		try {
			userData =cMDelegate.getUserDetails();// caseDelegate.getAllUsers();
			if (!userData.isEmpty()) {
				int size = 0;

				size = userData.size();
				users.add(new SelectItem(
						ContactManagementConstants.SPACE_STRING,
						ContactManagementConstants.EMPTY_STRING));
				userIDList.add(new SelectItem(
						ContactManagementConstants.SPACE_STRING,
						ContactManagementConstants.EMPTY_STRING));
				stepUsersList.add(new SelectItem(
						ContactManagementConstants.SPACE_STRING,
						ContactManagementConstants.EMPTY_STRING));

				StringBuffer sbName = null;//ESPRD00513580_ESPRD00513615_19AUG2010
				for (int i = 0; i < size; i++) {
					Object[] userDetails = (Object[]) userData.get(i);
					assignEmptyIfNull(userDetails);
					firstName = userDetails[1].toString();
					lastName = userDetails[0].toString();
						/*
						 * name = firstName +
						 * MaintainContactManagementUIConstants.EMPTY_STRING_SPACE +
						 * lastName;
						 */
						//Start Add By ICS
						//ESPRD00513580_ESPRD00513615_19AUG2010
						//userId = userDetails[2].toString();
						//ESPRD00513580_ESPRD00513615_19AUG2010
						//name = userId + " - " + firstName +
						// MaintainContactManagementUIConstants.EMPTY_STRING_SPACE
						// + lastName;
						sbName = new StringBuffer();
						if (lastName != null
								&& !MaintainContactManagementUIConstants.EMPTY_STRING
										.equals(lastName)) {
							sbName
									.append(lastName)
									.append(",")
									.append(
											MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
						}
						sbName
								.append(firstName)
								.append(
										MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
								.append(
										MaintainContactManagementUIConstants.HYPHEN);
						sbName
								.append(
										MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
								.append(userDetails[2].toString());
						name = sbName.toString();
						//EOF ESPRD00513580_ESPRD00513615_19AUG2010
						userIdName = userDetails[2].toString()
								+ " - "
								+ firstName
								+ MaintainContactManagementUIConstants.EMPTY_STRING_SPACE
								+ lastName;
						//End By ICS
						//Modified for defect ESPRD00333455 start
						//if (enterpriseUser.getCaseFilter() != null) {
							users.add(new SelectItem(userDetails[3].toString(), name));
							//defct ESPRD00688301
							userIDList.add(new SelectItem(userDetails[2].toString(), name));
						//}
						//defect ESPRD00333455 ends
						// userIDList.add(new SelectItem(enterpriseUser
						//       .getUserID(), name));
						/*usersMap.put(userDetails[3]
								.toString(), name);*/
						//userIDMap.put(userDetails[2].toString(), name);
						/*userWUAndID.put(userDetails[3]
								.toString(), userDetails[2].toString());*/
						stepUsersList.add(new SelectItem(userDetails[3].toString(), name));
						/*userName.put(userDetails[3]
								.toString(), name);*///Modified for the Defect ESPRD00778564
						}
			}
//			logCaseDataBean.setUserList(users);
			//added for defect ESPRD00541933 starts
			getUsersAndActiveDeptForRoute(users);
			//defect ESPRD00541933 ends
			//logCaseDataBean.setUserIDsList(userIDList);
			//logCaseDataBean.setUserMap(usersMap);
			//logCaseDataBean.setUserIDsMap(userIDMap);
			//logCaseDataBean.setUserIDAndWUMap(userWUAndID);
			logCaseDataBean.setStepUserList(stepUsersList);
			//logCaseDataBean.setUserNameMap(userName);
			//Commented for Heap dump fix defect ESPRD00935080 Ends

		} catch (Exception e) {
			log.error("Exception occured at getUsersList()" + e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getUsersList");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	public void getAdvOptionsPlus() {

		CorrespondenceDOConvertor correspondenceDOConvertor = new CorrespondenceDOConvertor();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		try {
			Long userSK = correspondenceDOConvertor.getUserSK(logCaseDataBean
					.getLoggedInUserID()); // Modified for the Performance fix
			String businessUnitDesc = correspondenceDOConvertor
					.getBusinessUnitforUser(userSK);

			logCaseDataBean
					.setResolnVVList(getResolnReferenceData(businessUnitDesc));
			logCaseDataBean
					.setSubjectVVList(getSubjectReferenceData(businessUnitDesc));
			logCaseDataBean
					.setSourceVVList(getSourceReferenceData(businessUnitDesc));
			logCaseDataBean.setLobVVList(getLobReferenceData());

			// for ESPRD00748803
			logCaseDataBean.setProvTypeVVList(getProvTypeReferenceData());
			/** Get categories for he user */

			getAllUsers();
			getAllHierarchies();
			getAllDeptUsers();
			logCaseDataBean.setAdvOptionsFl(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getAdvOptionsMinus() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setAdvOptionsFl(false);

	}

	/**
	 * this method is to get the all active users
	 *  
	 */
	private void getAllDeptUsers() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAllDeptUsers");

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		// Commented for HeapDump Issue
		/*
		 * List userList = new
		 * ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		 */
		List userList = new ArrayList();
		Set testSet = new HashSet(ContactManagementConstants.DEFAULT_SIZE);

		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		try {
			//Modified for Department User Heap dump issues starts
			/*
			userList = contactMaintenanceDelegate.getAllDepartmentUsers();
			log.info("User List after Delegate" + userList);
			// Commented for HeapDump Issue
			
			 * List listOfUsers = new ArrayList(
			 * ContactManagementConstants.DEFAULT_SIZE);
			 
			List listOfUsers = new ArrayList();

			for (Iterator iter = userList.iterator(); iter.hasNext();) {
				DepartmentUser dUser = (DepartmentUser) iter.next();
				EnterpriseUser user = (EnterpriseUser) dUser
						.getEnterpriseUser();

				StringBuffer userDesc = new StringBuffer(
						ContactManagementConstants.EMPTY_STRING);

				if (user.getUserWorkUnitSK() != null) {
					userDesc.append(user.getUserWorkUnitSK()
							+ ContactManagementConstants.SPACE_STRING);

				}
				if (user.getPrefixName() != null) {
					userDesc.append(user.getPrefixName()
							+ ContactManagementConstants.SPACE_STRING);

				}
				if (user.getFirstName() != null) {
					userDesc.append(user.getFirstName()
							+ ContactManagementConstants.SPACE_STRING);

				}
				if (user.getMiddleName() != null) {
					userDesc.append(user.getMiddleName()
							+ ContactManagementConstants.SPACE_STRING);

				}
				if (user.getLastName() != null) {
					userDesc.append(user.getLastName()
							+ ContactManagementConstants.SPACE_STRING);

				}
				if (user.getSuffixName() != null) {
					userDesc.append(user.getSuffixName());

				}

				if (testSet.add(user.getUserWorkUnitSK().toString()
						+ userDesc.toString())) {
					listOfUsers.add(new SelectItem(user.getUserWorkUnitSK()
							.toString(), userDesc.toString()));
				}

			}
			log.debug("final listOfUsers " + listOfUsers);
			logCaseDataBean.setAssignedToList(listOfUsers);
		*/
			userList = contactMaintenanceDelegate.getAllDepartmentUserNames(ContactManagementConstants.DEPARTMENT_USER_NAME);
			List listOfUsers = new ArrayList();
			for (Iterator iter = userList.iterator(); iter.hasNext();) {
				Object[] departmentUserNames=(Object[])iter.next();
				
				StringBuffer userDesc = new StringBuffer(
						ContactManagementConstants.EMPTY_STRING);
				// Modified for the UI Standard starts
				if (departmentUserNames[3] != null) {
					userDesc.append(departmentUserNames[3]).append(
							ContactManagementConstants.COMMA_SEPARATOR).append(
							ContactManagementConstants.SPACE_STRING);

				}
				if (departmentUserNames[2] != null) {
					userDesc.append(departmentUserNames[2]).append(
							ContactManagementConstants.HYPHEN_SEPARATOR);

				}
				if (departmentUserNames[6] != null) {
					userDesc.append(departmentUserNames[6]);

				}

				// Modified for the UI Standard ends

				if (departmentUserNames[7]!=null &&
						testSet.add(departmentUserNames[7].toString()
						+ userDesc.toString())) {
					listOfUsers.add(new SelectItem(departmentUserNames[7]
							.toString(), userDesc.toString()));
				}
			}
			//Modified for Department User Heap dump issues ends
			log.debug("final listOfUsers " + listOfUsers);
			logCaseDataBean.setAssignedToList(listOfUsers);
		} catch (Exception exe) {

			log.debug("Exception in getAllUsers-databean");

		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getAllDeptUsers");
	}

	/**
	 * This method is used to retreive values in the createdBy dropdown
	 */
	private void getAllUsers() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAllUsers");

		CMDelegate cMDelegate = new CMDelegate();
		// Commented for HeapDump Issue
		// List userList = new
		// ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		List userList = new ArrayList();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);

		try {

			userList = cMDelegate.getUserDetails();
			// Commented for HeapDump Issue
			/*
			 * List listOfUsers = new ArrayList(
			 * ContactManagementConstants.DEFAULT_SIZE);
			 */
			List listOfUsers = new ArrayList();
			if (userList == null) {
				log.info("userList is null");
			} else {

				for (Iterator iter = userList.iterator(); iter.hasNext();) {
					Object[] userDetails = (Object[]) iter.next();

					StringBuffer userDesc = new StringBuffer(
							ContactManagementConstants.EMPTY_STRING);

					if (userDetails[0] != null) {
						userDesc.append(userDetails[0]).append(
								ContactManagementConstants.COMMA_SEPARATOR).append(
										ContactManagementConstants.SPACE_STRING);

					}

					if (userDetails[1] != null) {
						userDesc.append(userDetails[1]).append(
								ContactManagementConstants.HYPHEN_SEPARATOR);

					}

					if (userDetails[2] != null) {
						userDesc.append(userDetails[2]);

					}

					listOfUsers.add(new SelectItem(userDetails[3].toString(),
							userDesc.toString()));
				}

			}
			logCaseDataBean.setCreatedByList(listOfUsers);
		} catch (Exception exe) {

			log.debug("Exception in getAllUsers--databean");
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getAllUsers");
	}

	/**
	 * This method is used to retreive valid values in the Resolution dropdown
	 * 
	 * @return List
	 */
	public final List getResolnReferenceData(String businessUnitDesc) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getResolnReferenceData");
		// Commented for HeapDump Issue
		/*
		 * List referenceList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 */
		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		// Commented for HeapDump Issue
		// List resolnList = new
		// ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		List resolnList = new ArrayList();
		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);

		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(
						ContactManagementConstants.CORR_RESOLUTION,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String
									.valueOf(ContactManagementHelper
											.getSystemlistForCorrBU(
													ContactManagementConstants.CORR_RESOLUTION,
													businessUnitDesc)));
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				resolnList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			log.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			log.error(e.getMessage(), e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getResolnReferenceData");
		return resolnList;

	}

	/**
	 * This method is used to retreive valid values in the Subject dropdown
	 * 
	 * @return List
	 */
	public final List getSubjectReferenceData(String businessUnitDesc) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getSubjectReferenceData");
		//Commented for HeapDump Issue
		/*
		 * List referenceList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 */
		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		//Commented for HeapDump Issue
		/*
		 * List subjectList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 */
		List subjectList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);

		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU("SUBJECT", businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String.valueOf(ContactManagementHelper
									.getSystemlistForCorrBU("SUBJECT",
											businessUnitDesc)));
			referenceListSize = referenceList.size();

			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				subjectList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			log.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			log.error(e.getMessage(), e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getSubjectReferenceData");
		return subjectList;

	}

	/**
	 * This method is used to retreive valid values in the Source dropdown
	 * 
	 * @return List
	 */
	public final List getSourceReferenceData(String businessUnitDesc) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getSourceReferenceData");
		/*
		 * List referenceList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 */
		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		// Commented for HeapDump Issue
		// List srcList = new
		// ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		List srcList = new ArrayList();
		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);

		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.CORR_SOURCE,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.CONTACT_MGMT
							+ ProgramConstants.HASH_SEPARATOR
							+ String
									.valueOf(ContactManagementHelper
											.getSystemlistForCorrBU(
													ContactManagementConstants.CORR_SOURCE,
													businessUnitDesc)));

			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				srcList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			log.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			log.error(e.getMessage(), e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getSourceReferenceData");
		return srcList;
	}

	/**
	 * This method is used to retreive valid values in the Line Of Business
	 * dropdown
	 * 
	 * @return List
	 */
	public final List getLobReferenceData() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getLobReferenceData");
		// Commented for HeapDump Issue
		/*
		 * List referenceList = new ArrayList(
		 * ContactManagementConstants.DEFAULT_SIZE);
		 */
		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		// Commented for HeapDump Issue
		// List lobList = new
		// ArrayList(ContactManagementConstants.DEFAULT_SIZE);
		List lobList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteriaEntityTyp
				.setElementName(ReferenceServiceDataConstants.R_LOB_CD);

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.GENERAL
							+ ProgramConstants.HASH_SEPARATOR
							+ ReferenceServiceDataConstants.R_LOB_CD);
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);
				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();

				lobList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			e.printStackTrace();

		} catch (SystemListNotFoundException e) {
			e.printStackTrace();

		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getLobReferenceData");
		return lobList;

	}

	/**
	 * This method is used to retreive values in the ReportingUnit and
	 * BusinessUnit dropdown
	 */
	public void getAllHierarchies() {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getAllHierarchies");
		List repUnitsList = new ArrayList();
		List businessUnitsList = new ArrayList();
		List deptUnitsList = new ArrayList();
		List emptyList = new ArrayList();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			repUnitsList = contactMaintenanceDelegate.getLobHierarchyDetails(
					emptyList, ContactManagementConstants.TWO);
			logCaseDataBean.getListOfRepUnits().clear();
			Iterator itr1 = repUnitsList.iterator();
			while (itr1.hasNext()) {
				LineOfBusinessHierarchy repUnit = (LineOfBusinessHierarchy) itr1
						.next();
				logCaseDataBean.getListOfRepUnits().add(
						new SelectItem(repUnit.getLineOfBusinessHierarchySK()
								.toString(), repUnit
								.getLobHierarchyDescription()));

			}
			businessUnitsList = contactMaintenanceDelegate
					.getLobHierarchyDetails(emptyList,
							ContactManagementConstants.THREE);
			logCaseDataBean.getListOfRefBusUnits().clear();
			Iterator itr2 = businessUnitsList.iterator();
			while (itr2.hasNext()) {
				LineOfBusinessHierarchy businUnit = (LineOfBusinessHierarchy) itr2
						.next();
				logCaseDataBean.getListOfRefBusUnits().add(
						new SelectItem(businUnit.getLineOfBusinessHierarchySK()
								.toString(), businUnit
								.getLobHierarchyDescription()));

			}

			deptUnitsList = contactMaintenanceDelegate
					.getDepartmentDetails(new ArrayList());
			logCaseDataBean.getListOfRefDeptUnits().clear();
			Iterator itr3 = deptUnitsList.iterator();
			while (itr3.hasNext()) {
				Department deptUnit = (Department) itr3.next();

				if (deptUnit.getLineOfBusinessHierarchy() != null) {
					logCaseDataBean.getListOfRefDeptUnits().add(
							new SelectItem(deptUnit
									.getLineOfBusinessHierarchy()
									.getLineOfBusinessHierarchySK().toString(),
									deptUnit.getName()));

				}
			}
		} catch (Exception exe) {
			log.debug("Exception caught in getAllHierarchies");
		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getAllHierarchies");
	}

	//	added for defect ESPRD00299900
	//	updated for defect ESPRD00368025 Starts
	public void setCaseStatus(String businessUnitDesc) {

		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "setCaseStatus");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (businessUnitDesc != null) {
			//Modified for defect ESPRD00935080 starts
			if (businessUnitDesc.equals(ContactManagementConstants.bccp)
					|| businessUnitDesc
							.startsWith(ContactManagementConstants.bccp)) {//BCCP
				/*logCaseDataBean.setStatusList(getValidData(logCaseDataBean
						.getCaseStatusValidValues(),
						ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
						FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setStatusList(getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP, FunctionalAreaConstants.GENERAL));
			} else if (businessUnitDesc.equals(ContactManagementConstants.ddu)
					|| businessUnitDesc
							.startsWith(ContactManagementConstants.ddu)) {//DDU
				/*logCaseDataBean.setStatusList(getValidData(logCaseDataBean
						.getCaseStatusValidValues(),
						ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
						FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setStatusList(getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU, FunctionalAreaConstants.GENERAL));
			} else if (businessUnitDesc.equals(ContactManagementConstants.mcs)
					|| businessUnitDesc
							.startsWith(ContactManagementConstants.mcs)) {//MCS
				/*logCaseDataBean.setStatusList(getValidData(logCaseDataBean
						.getCaseStatusValidValues(),
						ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
						FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setStatusList(getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS, FunctionalAreaConstants.GENERAL));
			} else if (businessUnitDesc
					.equals(ContactManagementConstants.appeals)
					|| businessUnitDesc
							.startsWith(ContactManagementConstants.appeals)) {//Appeals
				/*logCaseDataBean.setStatusList(getValidData(logCaseDataBean
						.getCaseStatusValidValues(),
						ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
						FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setStatusList(getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL, FunctionalAreaConstants.GENERAL));
			} else if (businessUnitDesc
					.equalsIgnoreCase(ContactManagementConstants.provider_relations)
					|| businessUnitDesc
							.startsWith(ContactManagementConstants.providerRelations)) { //Provider
																						 // Relations
				/*logCaseDataBean.setStatusList(getValidData(logCaseDataBean
						.getCaseStatusValidValues(),
						ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
						FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setStatusList(getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL, FunctionalAreaConstants.GENERAL));
			} else if (businessUnitDesc.equals(ContactManagementConstants.ARS)
					|| businessUnitDesc
							.startsWith(ContactManagementConstants.ARS)) {//ARS
				/*logCaseDataBean.setStatusList(getValidData(logCaseDataBean
						.getCaseStatusValidValues(),
						ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
						FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setStatusList(getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS, FunctionalAreaConstants.GENERAL));
			} else {
				/*logCaseDataBean.setStatusList(getValidData(logCaseDataBean
						.getCaseStatusValidValues(),
						ReferenceServiceDataConstants.G_CASE_STAT_CD,
						FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setStatusList(getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD, FunctionalAreaConstants.GENERAL));
			}
		} else {
			/*logCaseDataBean.setStatusList(getValidData(logCaseDataBean
					.getCaseStatusValidValues(),
					ReferenceServiceDataConstants.G_CASE_STAT_CD,
					FunctionalAreaConstants.GENERAL));*/
			logCaseDataBean.setStatusList(getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD, FunctionalAreaConstants.GENERAL));
		}
	     //defect ESPRD00935080 ends
		//Modified for defect ESPRD00491876 starts
		if (logCaseDataBean.isSuperviserRevReqIndForCaseType()
				|| logCaseDataBean.isSuperviserRevReqInd()) {
			removeClosedValFromStatus();
		}
		//defect ESPRD00491876 ends
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "setCaseStatus");
	}

	//updated for defect ESPRD00368025 Ends

	//	EOF ESPRD00299900

	/* Defect ESPRD00351920,ESPRD00300172 starts */
	/**
	 * This method is used to get the value of event Type List Based on the .
	 * selected case type from the case type drop down or buisness Unit
	 * 
	 * @param selectedValue
	 */
	public void setCaseEventTypeList(String selectedValue) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "setCaseEventTypeList");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		String userId = logCaseDataBean.getLoggedInUserID();  // Modified for the Performance fix
		String businessUnit = getUserBusinessUnit(userId);
		if (selectedValue != null) {
			log.debug("Before Calling case Event type List ::"
					+ selectedValue);
			//Modified for defect ESPRD00935080 starts
			if (selectedValue.equals("DDU") || selectedValue.startsWith("DDU")
					|| businessUnit.startsWith("DDU")) {
				/*logCaseDataBean
						.setEventTypeList(getValidData(
								logCaseDataBean.getCaseStatusValidValues(),
								ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU,
								FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setEventTypeList(getReferenceData(ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU, FunctionalAreaConstants.GENERAL));
				log.debug("Inside DDUt ::" + selectedValue);
			} else if ((selectedValue.equals("MLS"))
					|| (selectedValue.startsWith("MLS"))
					|| businessUnit.startsWith("MLS")) {
				/*logCaseDataBean
						.setEventTypeList(getValidData(
								logCaseDataBean.getCaseStatusValidValues(),
								ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_MLS,
								FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setEventTypeList(getReferenceData(ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_MLS, FunctionalAreaConstants.GENERAL));
				log.debug("Inside MLS ::" + selectedValue + " : "
						+ logCaseDataBean.getEventTypeList());
			} else if ((selectedValue.equals("ARS"))
					|| (selectedValue.startsWith("ARS"))
					|| businessUnit.startsWith("ARS")) {
				/*logCaseDataBean
						.setEventTypeList(getValidData(
								logCaseDataBean.getCaseStatusValidValues(),
								ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_ARS,
								FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setEventTypeList(getReferenceData(ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_ARS, FunctionalAreaConstants.GENERAL));
				log.debug("Inside ARS ::" + selectedValue + " : "
						+ logCaseDataBean.getEventTypeList());
			} else {
				/*logCaseDataBean
						.setEventTypeList(getValidData(
								logCaseDataBean.getCaseStatusValidValues(),
								ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
								FunctionalAreaConstants.GENERAL));*/
				logCaseDataBean.setEventTypeList(getReferenceData(ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH, FunctionalAreaConstants.GENERAL));
				log.debug("OTH ::" + selectedValue + " : "
						+ logCaseDataBean.getEventTypeList());
			}
		} else {
			/*logCaseDataBean.setEventTypeList(getValidData(logCaseDataBean
					.getCaseStatusValidValues(),
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
					FunctionalAreaConstants.GENERAL));*/
			logCaseDataBean.setEventTypeList(getReferenceData(ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH, FunctionalAreaConstants.GENERAL));
			log.debug("OTH ::" + selectedValue + " : "
					+ logCaseDataBean.getEventTypeList());
		}
	     //defect ESPRD00935080 ends
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "setCaseStatus");
	}

	/* Defect ESPRD00351920,ESPRD00300172 End */

	/* Defect ESPRD00300172 starts */

	/**
	 * This method is used to get the Buisness Unit Description.
	 * 
	 * @param selectedValue
	 */

	public String getUserBusinessUnit(String userId) {

		ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
		String businessUnitDesc = null;

		try {

			List buinessUnitDescs = delegate.getBuisnessUnitsDescs(userId);
			if (buinessUnitDescs != null) {
				if (buinessUnitDescs.size() == 1) {
					businessUnitDesc = (String) buinessUnitDescs.get(0);

				} else {
					businessUnitDesc = ContactManagementConstants.AllOthers;
				}
			} else {
				businessUnitDesc = ContactManagementConstants.AllOthers;

			}

		} catch (LOBHierarchyFetchBusinessException e) {
			log.debug(e);
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.debug(e);
			log.error(e.getMessage(), e);
		}
		return businessUnitDesc;
	}

	/* Defect ESPRD00300172 Ends */
	//	Added for defect ESPRD00379116 starts
	protected void sendAutomaticAlertIfReq() {
		log.info("++sendAutomaticAlert");
		AlertVO alertVO = getAlertToSend();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);  // Added for the Performance fix
		if (alertVO != null) {
			if (logCaseDataBean.isSuperviserRevReqInd())
				alertVO.setDescription("Case Requires Approval");
			else if (logCaseDataBean.isSuperviserRevReqIndForCaseType())
				alertVO.setDescription("Case Requires Approval - Case Type");
			logCaseDataBean.getAlertsVOList().add(alertVO);
			log.debug("alertVO Description.. " + alertVO.getDescription());
		}
	}

	private AlertVO getAlertToSend() {
		log.info("++getAlertToSend");
		AlertVO alertVO = null;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN); // Added for the Performance fix
		EnterpriseUser supervisor = getSupervisorForDept();

		if (supervisor != null) {
			alertVO = new AlertVO();
			alertVO.setRcvgUserID(supervisor.getUserID());
			log.debug("++supervisor UserID()::"
					+ supervisor.getUserID());
			if (supervisor.getUserWorkUnitSK() != null) {
				alertVO.setUserWorkUnitSK(new Double(String.valueOf(supervisor
						.getUserWorkUnitSK())));
				log.debug("++alertVO UserWorkUnitSK.."
						+ alertVO.getUserWorkUnitSK());
			}
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
			alertVO.setDueDateStr(dateFormat.format(calendar.getTime()));
			alertVO.setDueDate(calendar.getTime());
			log.debug("++Due Date for automatic alert "
					+ alertVO.getDueDate());
			alertVO.setAddedAuditUserID(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
			alertVO.setAuditUserID(logCaseDataBean.getLoggedInUserID()); // Modified for the Performance fix
			alertVO.setDbRecord(false);
		}

		return alertVO;
	}

	/**
	 * @return EnterpriseUser
	 */
	protected EnterpriseUser getSupervisorForDept() {
		log.info("getSupervisorForDept");
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		EnterpriseUser supervisor = null;
		Long departmentSK = new Long(logCaseDataBean.getCaseDetailsVO()
				.getWorkUnit());
		log.debug("departmentSK ::>" + departmentSK);
		try {
			if (departmentSK != null) {
				Department department = contactMaintenanceDelegate
						.getSupervisorToDept(departmentSK);

				log.debug("department " + department);
				if (department != null)
					supervisor = department.getSupervisorUserWorkUnit();
				log.debug("supervisor " + supervisor);
			}
		} catch (LOBHierarchyFetchBusinessException e) {
			log.error(e.getMessage(), e);
		}

		return supervisor;
	}

	// defect ESPRD00379116 ends

	//added for defect ESPRD00382416
	/**
	 * This method is to apply Business rules on DO Caserecord
	 * 
	 * @param caseRecord
	 */
	private void applyBRsOnCaseRecord(CaseRecord caseRecord) {
		Set alerts = null;
		Set actCMCaseSteps = null;

		if (caseRecord != null) {
			actCMCaseSteps = caseRecord.getActCMCaseSteps();
			alerts = caseRecord.getAlerts();
			//BR CON0364.0001.01 If 'Status' value is set to 'Completed' or
			// 'Void',
			//.....update all Alerts associated to that Case Step to
			// 'Completed' or 'Completed - Void'.
			Iterator steps_iterator = actCMCaseSteps.iterator();
			Iterator alert_iterator = null;

			ActCMCaseStep actCMCaseStep = null;
			Alert alertDO = null;
			boolean isCaseStepClosed = false;
			boolean isCaseStepVoidedOrSkiped = false;
			while (steps_iterator.hasNext()) {
				actCMCaseStep = (ActCMCaseStep) steps_iterator.next();
				if (actCMCaseStep != null
						&& actCMCaseStep.getStepStatusCode() != null) {
					isCaseStepClosed = actCMCaseStep
							.getStepStatusCode()
							.equals(
									MaintainContactManagementUIConstants.CASE_STEP_STATUS_CLOSED);
					isCaseStepVoidedOrSkiped = actCMCaseStep
							.getStepStatusCode()
							.equals(
									MaintainContactManagementUIConstants.CASE_STEP_STATUS_VOID_OR_SKIP);
					if (isCaseStepClosed || isCaseStepVoidedOrSkiped) {
						if (actCMCaseStep.getCmCaseStepCode() != null
								&& actCMCaseStep.getCaseStepSeqNum() != null) {
							alert_iterator = alerts.iterator();

							while (alert_iterator.hasNext()) {

								alertDO = (Alert) alert_iterator.next();
								if (alertDO != null) {
									if (actCMCaseStep
											.getCmCaseStepCode()
											.equals(alertDO.getCmCaseStepCode())
											&& actCMCaseStep
													.getCaseStepSeqNum()
													.equals(
															alertDO
																	.getCaseStepSeqNum())) {

										if (isCaseStepClosed) {
											alertDO
													.setAlertStatusCode(MaintainContactManagementUIConstants.CASE_ALERT_STATUS_COMPLETED);
										} else if (isCaseStepVoidedOrSkiped) {
											alertDO
													.setAlertStatusCode(MaintainContactManagementUIConstants.CASE_ALERT_STATUS_COMPLETED_VOID);
										}
									}
								}//eof null verification
							}//eof while
						}
					}
				}
			}//EOF while
			//EOF CON0364.0001.01

			/*
			 * CON0366.0001.01 If 'Status' value is set to not Pending, all
			 * Alerts associated to that Case Event are set to 'Completed' or
			 * 'Completed - Void'.
			 */
			Set actCMCaseEventsSet = caseRecord.getActCMCaseEvents();
			alerts = caseRecord.getAlerts();

			Iterator events_iterator = actCMCaseEventsSet.iterator();

			ActCMCaseEvent actCMCaseEvent = null;
			boolean isCaseEventCompleted = false;
			boolean isCaseEventPending = false;

			while (events_iterator.hasNext()) {

				actCMCaseEvent = (ActCMCaseEvent) events_iterator.next();

				if (actCMCaseEvent != null
						&& actCMCaseEvent.getEventStatusCode() != null) {

					isCaseEventCompleted = actCMCaseEvent
							.getEventStatusCode()
							.equals(
									MaintainContactManagementUIConstants.CASE_EVENT_STATUS_COMPLETED);//"CM"
																									  // Completed
					isCaseEventPending = actCMCaseEvent
							.getEventStatusCode()
							.equals(
									MaintainContactManagementUIConstants.CASE_EVENT_STATUS_TYPE);//"P"
																								 // pending
					//					System.err.println("isCaseEventCompleted
					// :"+isCaseEventCompleted);
					//					System.err.println("isCaseEventPending :
					// "+isCaseEventPending);

					if (isCaseEventCompleted || !isCaseEventPending) {

						if (actCMCaseEvent.getCmCaseEventCode() != null
								&& actCMCaseEvent.getCaseEventSeqNum() != null) {
							alert_iterator = alerts.iterator();

							while (alert_iterator.hasNext()) {

								alertDO = (Alert) alert_iterator.next();
								if (alertDO != null) {
									//System.err.println("actCMCaseEvent.getCmCaseEventCode()
									// : "+actCMCaseEvent.getCmCaseEventCode());
									//System.err.println("alertDO.getCmCaseEventCode()
									// :"+alertDO.getCmCaseEventCode());
									//System.err.println("actCMCaseEvent.getCaseEventSeqNum()
									// : "+actCMCaseEvent.getCaseEventSeqNum());
									//System.err.println("alertDO.getCaseEventSeqNum():
									// "+alertDO.getCaseEventSeqNum());
									if (actCMCaseEvent
											.getCmCaseEventCode()
											.equals(
													alertDO
															.getCmCaseEventCode())
											&& actCMCaseEvent
													.getCaseEventSeqNum()
													.equals(
															alertDO
																	.getCaseEventSeqNum())) {
										if (isCaseEventCompleted) {
											alertDO
													.setAlertStatusCode(MaintainContactManagementUIConstants.CASE_ALERT_STATUS_COMPLETED);
										} else if (!isCaseEventPending) {
											alertDO
													.setAlertStatusCode(MaintainContactManagementUIConstants.CASE_ALERT_STATUS_COMPLETED_VOID);
										}
									}
								}//eof null verification
							}//eof while
						}
					}
				}
			}//EOF while //EOF CON0366.0001.01
		}

	}

	//EOF ESPRD00382416

	/**
	 * This method is used to get the UserSK given the userId.
	 * 
	 * @param userId :
	 *            String User Id.
	 * @return Long : UserSK.
	 */
	 // Begin - Commented the unused method for the defect id ESPRD00702153_30NOV2011.
	 /*
	private Long getUsersSK(String userId) {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		Long userSK = null;
		try {
			userSK = contactMaintenanceDelegate.getWorkUnitID(userId);
		} catch (LOBHierarchyFetchBusinessException e) {
			log.error(e.getMessage(), e);
		}

		return userSK;
	}*/
	 // End - Commented the unused method for the defect id ESPRD00702153_30NOV2011.

	//	added for UC-PGM-CRM-018_ESPRD00367808_18JAN10
	private void setCaseStatusInUpdateMode() {

		boolean isCaseStatusFound = false;
		SelectItem sItem = null;
		Iterator status_itr = logCaseDataBean.getStatusList().iterator();
		while (status_itr.hasNext()) {
			sItem = (SelectItem) status_itr.next();
			if (logCaseDataBean.getCaseDetailsVO() != null
					&& logCaseDataBean.getCaseDetailsVO().getStatus() != null
					&& logCaseDataBean.getCaseDetailsVO().getStatus().equals(
							sItem.getValue().toString())) {
				isCaseStatusFound = true;
				break;
			}
		}

		if (!isCaseStatusFound) {
			if (logCaseDataBean.getCaseDetailsVO() != null
					&& logCaseDataBean.getCaseDetailsVO().getStatus() != null) {
				/*
				 * Enable code for long description and then comment the code
				 * for short description if required List status_list =
				 * getLogDescData(logCaseDataBean.getCaseStatusValidValues(),ReferenceServiceDataConstants.G_CASE_STAT_CD,
				 * FunctionalAreaConstants.GENERAL,false);
				 * ContactManagementHelper helper =new
				 * ContactManagementHelper();
				 * logCaseDataBean.getStatusList().add(new
				 * SelectItem(logCaseDataBean.getCaseDetailsVO().getStatus(),logCaseDataBean.getCaseDetailsVO().getStatus() +
				 * "-" +
				 * helper.getDescriptionFromVV(logCaseDataBean.getCaseDetailsVO().getStatus(),status_list)));
				 */
				//code for short description
				//Modified for defect ESPRD00935080 starts
				/*List status_list = getValidData(logCaseDataBean
						.getCaseStatusValidValues(),
						ReferenceServiceDataConstants.G_CASE_STAT_CD,
						FunctionalAreaConstants.GENERAL);*/
				List status_list = getReferenceData(ReferenceServiceDataConstants.G_CASE_STAT_CD, FunctionalAreaConstants.GENERAL);
				ContactManagementHelper helper = new ContactManagementHelper();
				logCaseDataBean.getStatusList().add(
						new SelectItem(logCaseDataBean.getCaseDetailsVO()
								.getStatus(), helper.getDescriptionFromVV(
								logCaseDataBean.getCaseDetailsVO().getStatus(),
								status_list)));
			     //defect ESPRD00935080 ends
			}
		}
		logCaseDataBean.setCaseStatusSetFlag(true);
		//sort the status list if requred
	}

	//EOF UC-PGM-CRM-018_ESPRD00367808_18JAN10
	//added for UC-PGM-CRM-018_ESPRD00388733_19JAN10
	private AssociatedCaseVO convertCaseRecordSearchResultsVOToAssociatedCaseVO(
			CaseRecordSearchResultsVO caseRecordSearchResultsVO) {
		AssociatedCaseVO associatedCaseVO = null;
		if (caseRecordSearchResultsVO != null
				&& caseRecordSearchResultsVO.getCaseSK() != null) {
			associatedCaseVO = new AssociatedCaseVO();

			associatedCaseVO.setCaseID(caseRecordSearchResultsVO.getCaseSK()
					.toString());
			associatedCaseVO.setCreatedDate(caseRecordSearchResultsVO
					.getCreatedDate());
			associatedCaseVO.setCaseType(caseRecordSearchResultsVO
					.getCaseType());
			associatedCaseVO.setStatus(caseRecordSearchResultsVO.getStatus());
			if (caseRecordSearchResultsVO.getCreatedDate() != null) {
				associatedCaseVO.setCreatedDateStr(ContactManagementHelper
						.dateConverter(caseRecordSearchResultsVO
								.getCreatedDate()));
			}
		}

		return associatedCaseVO;
	}//EOf UC-PGM-CRM-018_ESPRD00388733_19JAN10

	/**
	 * @throws Exception
	 */
	private String getSystemParameter(String cascadeKey) //throws
														 // ContactManagementUIException
	{
		String cascadeVal = "";
		SystemParameterDelegate systemParameterDelegate = new SystemParameterDelegate();
		SystemParameterDetail systemParameterDetail = null;
		/* FIND BUGS_FIX */
		//Date today = new Date();
		SystemParameter systemParameter = null;
		Set systemParameterDetailSet = null;

		try {
			systemParameter = systemParameterDelegate.getSystemParameterDetail(
					new Long(cascadeKey),
					ContactManagementConstants.FUNCTIONAL_AREA_CODE);

		} catch (SystemParameterNotFoundException sytemParmNotFoundExec) {
			log.error("SystemParameterNotFoundException :"
					+ " saveCorrespondence()", sytemParmNotFoundExec);
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
				log.debug("cascadeKey:" + cascadeKey + " val:" + cascadeVal);
			}
		}

		return cascadeVal;
	}

	// Commented the unused code for the defect ESPRD00735630_20DEC2011
	/*private boolean caseEventDeleteMsgFlag = false;

	*//**
	 * @return Returns the caseEventDeleteMsgFlag.
	 *//*
	public boolean isCaseEventDeleteMsgFlag() {
		return caseEventDeleteMsgFlag;
	}

	*//**
	 * @param caseEventDeleteMsgFlag
	 *            The caseEventDeleteMsgFlag to set.
	 *//*
	public void setCaseEventDeleteMsgFlag(boolean caseEventDeleteMsgFlag) {
		this.caseEventDeleteMsgFlag = caseEventDeleteMsgFlag;
	}*/

	/**
	 * This method fetches he entity for TPL.
	 * 
	 * @param entityID
	 *            holds the entity ID
	 * @param inqEntity
	 *            holds the inqEntity
	 * @return CaseRegardingTPL
	 */
	private CaseRegardingTPL getEntityForTPLPolicyHolder(String entityID,
			boolean inqEntity) {
		CaseRegardingTPL regardingTPL = null;
		log.info("starting of getentityfor tpl policy holder$$$$$$$$$$$$$$");
		log.debug("Before calling TPL delegate entityID is $$ " + entityID);
		// Commented for TPL External-API for CRM.
		// Added for TPL External-API for CRM.
		
		///CaseDelegate caseDelegate = new CaseDelegate();
		///TPLPolicyHolder tplPolicyHolder;
		TPLPolicyDelegate tplPolicyDelegate = new TPLPolicyDelegate();
		TPLPolicyHolderDetailsVO tplPolicyHolderDetailsVO;
		try {
            ///tplPolicyHolder = caseDelegate
			///		.getTPLPloicyHolder(new Long(entityID));
			////LogCaseDomainToVOConverter caseDomainToVOConverter = new LogCaseDomainToVOConverter();
			////if (tplPolicyHolder != null) {
			///	regardingTPL = caseDomainToVOConverter
			///			.convertTPLPolicyHolderDOToVO(tplPolicyHolder);
			tplPolicyHolderDetailsVO = tplPolicyDelegate
					.getTPLPloicyHolderForCase(new Long(entityID));
			LogCaseDomainToVOConverter caseDomainToVOConverter = new LogCaseDomainToVOConverter();
			log.debug("tplPolicyHolderDetailsVO Inside if*** NOT NULL"
					+ tplPolicyHolderDetailsVO);
			if (tplPolicyHolderDetailsVO != null) {
				log.debug("tplPolicyHolderDetailsVO Inside if*** NOT NULL");
				regardingTPL = caseDomainToVOConverter
						.convertTPLPolicyHolderDOToVO(tplPolicyHolderDetailsVO);
			}
			//Added for TPL External-API for CRM.
		} catch (EnterpriseBaseBusinessException e) {
			e.printStackTrace();
		}

		return regardingTPL;
	}

	private CaseRegardingTradingPartnerVO getEntityForTradingPartner(
			String entityID, Long cmEntitySk, boolean inqEntity) {
		CaseRegardingTradingPartnerVO regardingTradPart = null;
		//CaseDelegate caseDelegate= new CaseDelegate();
		CMDelegate cmDelegate = new CMDelegate();
		TradingPartner tradePartner = null;

		try {
			log.debug("============cmEntitySk==========" + cmEntitySk);
			//tradePartner = caseDelegate.getTradingPartnerDtls(new
			// Long(cmEntitySk));
			if (cmEntitySk != null) {
				tradePartner = cmDelegate.getTradingPartner(null, cmEntitySk);
			} else if (entityID != null) {
				tradePartner = cmDelegate.getTradingPartner(entityID, null);
			}

			log.debug("+tradePartner---" + tradePartner);

			LogCaseDomainToVOConverter caseDomainToVOConverter = new LogCaseDomainToVOConverter();
			if (tradePartner != null) {
				regardingTradPart = caseDomainToVOConverter
						.convertTradingPartnerDOToVO(tradePartner);

			}
		} catch (Exception e) {
			log.info("++Exception caught in getEntityForTradingPartner()");
			e.printStackTrace();
			log.error(e.fillInStackTrace());
		}

		return regardingTradPart;
	}

	//CR_ESPRD00373565_LogCase_04AUG2010
	public void enableAuditLogForLogCase() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		
		String caseSk = logCaseDataBean.getCaseRegardingVO().getCaseRecordNumber();
		// Added for the Defect : ESPRD00790505
		logCaseDataBean.setEnableAuditLogs(true);
		//refreshCaseRecordOnPageSave(caseSk);
		loadLogCasePageWithCaseSk(caseSk);
		logCaseDataBean.setEnableAuditLogForLogCase(true);
		log.debug("starting letter filtering $$$$$$$$$$$$$$$$$$$");
		LettersAndResponsesDataBean lettersAndResponsesDataBean = (LettersAndResponsesDataBean) getLettersAndResponsesDataBeanFromContext();
		List lettersList= lettersAndResponsesDataBean.getLetterGenerationRequests();
		log.debug("lettersList size --->" + lettersList.size());
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
		
		UIComponent component = ContactManagementHelper
		.findComponentInRoot("logcaseDetailAuditId");
		
		if (component != null) {

			AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
			auditHistoryTable.setValue(logCaseDataBean.getCaseDetailsVO().getAuditKeyList());
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
					false);

		}
	} //EOF CR_ESPRD00373565_LogCase_04AUG2010

	private Object createAuditableFeild(String fieldName,String domainAttributeName) {
		AuditableField auditableField = new AuditableField();
	 	auditableField.setFieldName(fieldName);
	 	auditableField.setDomainAttributeName(domainAttributeName);
		return null;
	}

	//  Added for defect ESPRD00510702 starts
	public String getDeptsForBsnsUnit(ValueChangeEvent event) {
		String businessUnitSK = (String) event.getNewValue();
		log.debug("++businessUnitSK  >>>>>>>>>>>>>:" + businessUnitSK);
		if (!isNullOrEmptyField(businessUnitSK)) {
			getListOfBuDepts(businessUnitSK);
			logCaseDataBean.setShowDepartments(true);
		} else {
			logCaseDataBean.setShowDepartments(false);
		}
		logCaseDataBean.setShowUserDepartments(false);
		logCaseDataBean.setShowUsers(false);

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * @param bUnitSK
	 *            The bUnit to set
	 */
	protected void getListOfBuDepts(String bUnitSK) {
		log.info("++getLsitOfBuDepts");
		List listSK = new ArrayList();
		// List lobHierarchyList = new ArrayList();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		Long businessUnitSK = Long.valueOf(bUnitSK);
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			listSK.add(businessUnitSK);

			List refListOfDepts = contactMaintenanceDelegate
					.getDepartmentDetails(listSK);
			/* FIND BUGS_FIX */
			List listOfDepts = logCaseDataBean.getWorkUnitList();
			listOfDepts.clear();
			if (refListOfDepts != null) {
				if(log.isDebugEnabled()){
				log.debug("++ListofBuDepts  size :"
						+ refListOfDepts.size());
				}
				for (Iterator it2 = refListOfDepts.iterator(); it2.hasNext();) {
					Department dept = (Department) it2.next();
					log.debug("++work unit sk: "
							+ dept.getWorkUnitSK() + "dept name : "
							+ dept.getName());
					if (dept.getLineOfBusinessHierarchy() != null) {
						listOfDepts.add(new SelectItem(dept.getWorkUnitSK().toString(),
								dept.getName())); // Modified the code to get the correct listOfDepts for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}

	// Defect ESPRD00510702 ends
	//ESPRD00517164_UC-PGM-CRM-18_16SEP2010
	private void executeBRCON307() { //		BRC CON0307.0001.01

		boolean isEntityProviderOrCM = false;
		logCaseDataBean.getPhysicianOverseeingList().clear();

		if (ContactManagementConstants.ENTITY_TYPE_PROV.equals(logCaseDataBean
				.getEntityType())) {
			//System.err.println("inside::::::prov");
			if (logCaseDataBean.getCaseRegardingVO() != null
					&& logCaseDataBean.getCaseRegardingVO()
							.getCaseRegardingProviderVO() != null) {
				String entId = logCaseDataBean.getCaseRegardingVO()
						.getCaseRegardingProviderVO().getEntityId();
				String provName = logCaseDataBean.getCaseRegardingVO()
						.getCaseRegardingProviderVO().getName();
				//    			Modified for defect ESPRD00528135 starts
				//System.err.println("entId:"+entId);
				//System.err.println("provName:"+provName);
				if (entId != null) {
					logCaseDataBean.getPhysicianOverseeingList().add(
							new SelectItem(entId, entId + "-" + provName));
				}
				// defect ESPRD00528135 ends
			}

		} else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
				.equals(logCaseDataBean.getEntityType())) {
			//    		System.err.println("inside::::::UPprov");
			if (logCaseDataBean.getCaseRegardingVO() != null
					&& logCaseDataBean.getCaseRegardingVO()
							.getCaseRegardingProviderVO() != null) {
				String entId = logCaseDataBean.getCaseRegardingVO()
						.getCaseRegardingTPLVO().getEntityId();
				String provName = logCaseDataBean.getCaseRegardingVO()
						.getCaseRegardingTPLVO().getName();
				//System.err.println("entId:"+entId);
				//System.err.println("provName:"+provName);
				if (entId != null) {
					logCaseDataBean.getPhysicianOverseeingList().add(
							new SelectItem(entId, entId + "-" + provName));
				}// defect ESPRD00528135 ends
			}
		}

		if (logCaseDataBean.isShowAddiCaseEntitesDataTable()) {
			CaseRegardingTPL entityVO = null;
			CaseRegardingProviderVO caseRegardingProviderVO = null;
			Object inqAbtObj = null;

			try {
				Iterator inqAbtItr = logCaseDataBean.getCaseDetailsVO()
						.getInqAbtEntityList().iterator();

				while (inqAbtItr.hasNext()) {

					inqAbtObj = inqAbtItr.next();
					if (inqAbtObj instanceof CaseRegardingProviderVO) {

						caseRegardingProviderVO = (CaseRegardingProviderVO) inqAbtObj;
						logCaseDataBean.getPhysicianOverseeingList().add(
								new SelectItem(caseRegardingProviderVO
										.getEntityId(), caseRegardingProviderVO
										.getEntityId()
										+ "-"
										+ caseRegardingProviderVO.getName()));

						isEntityProviderOrCM = true;

					} else if (inqAbtObj instanceof CaseRegardingTPL) {
						entityVO = (CaseRegardingTPL) inqAbtObj;

						if (entityVO.getEntityType() != null
								&& (entityVO.getEntityType().equalsIgnoreCase(
										"UP") || entityVO.getEntityType()
										.equals("P"))) {

							logCaseDataBean.getPhysicianOverseeingList().add(
									new SelectItem(entityVO.getEntityId(),
											entityVO.getEntityId() + "-"
													+ entityVO.getName()));
							isEntityProviderOrCM = true;

						}
					}
				}//eof while

			} catch (Exception exception) {
				//log.error(e.getMessage(), e);
				log.error(exception.getMessage(), exception);
			}
			if (isEntityProviderOrCM) {
				logCaseDataBean.setPhysicianOverseeingFlag(true);
			} else {
				logCaseDataBean.setPhysicianOverseeingFlag(false);
			}

		}
		if (ContactManagementConstants.ENTITY_TYPE_PROV.equals(logCaseDataBean
				.getEntityType())
				|| ContactManagementConstants.ENTITY_TYPE_UNPROV
						.equals(logCaseDataBean.getEntityType())) {

			logCaseDataBean.setPhysicianOverseeingFlag(true);
		} else if (isEntityProviderOrCM) {
			logCaseDataBean.setPhysicianOverseeingFlag(true);
		} else {
			logCaseDataBean.setPhysicianOverseeingFlag(false);
		}

	}//EOf ESPRD00517164_UC-PGM-CRM-18_16SEP2010

	private void resetLettersEditSection() {
		try {
			LettersAndResponsesDataBean letterTemplateDataBean = (LettersAndResponsesDataBean) getLettersAndResponsesDataBeanFromContext();
			letterTemplateDataBean.setRenderEditSection(false);
		} catch (Exception exception) {
			log.error(exception.getMessage(), exception);
		}
	}

	protected void getUsersAndActiveDeptForRoute(List userList) {
		/* FIND BUGS_FIX */
		//ContactManagementHelper contactManagementHelper = new
		// ContactManagementHelper();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		List userAndActiveDept = new ArrayList();
		userAndActiveDept.addAll(userList);
		setAllDepartmentsList();
		userAndActiveDept.addAll(logCaseDataBean.getWorkUnitList());
		if(log.isDebugEnabled()){
		log.debug("logCaseDataBean.getListOfAllDepartments()--"
				+ logCaseDataBean.getListOfAllDepartments().size());
		log.debug("befor adding user list.size--" + userList.size());
		//        userAndActiveDept.addAll(contactManagementHelper.getAllActiveDepts());
		log.debug("adding user list.size--" + userList.size());
		}
		logCaseDataBean.setRouteAllDeptUsersList(userAndActiveDept);

		//sortListSequence(reassignDeptList);

		//sortListSequence(reassignDeptList);
	}

	public String saveRoutingFromSteps(String routedTo, String routedToName) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "saveRoutingFromSteps");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CMRoutingVO routingVO = logCaseDataBean.getRoutingVO();
		String userID = logCaseDataBean.getLoggedInUserID();  // Modified for the Performance fix
		//Modified for GAI Recursive Call_Fix
		//Long userSKLongVal = getUserSK(userID);
		Long userSKLongVal = logCaseDataBean.getLoggedInUserSK();
		// Begin - Modified the code for the defect id ESPRD00702153_30NOV2011.
		String userId = logCaseDataBean.getLoggedInUserID();
		try {
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			WorkUnit userWorkUnit = contactMaintenanceDelegate.getUserWorkUnit(userId);
			String userNamebyId = getName(userWorkUnit, "U");
			routingVO.setRoutedByName(userNamebyId);
		}
		catch(LOBHierarchyFetchBusinessException e)
		{
			e.printStackTrace();
		}
		routingVO.setRoutedByUserID(userID);
		//routingVO.setRoutedByName(userID);
		// End - Modified the code for the defect id ESPRD00702153_30NOV2011.		
		
		routingVO.setCreatedBy(logCaseDataBean.getCaseDetailsVO()
				.getCreatedBy());

		if (userSKLongVal != null) {
			routingVO.setRoutedBySK(userSKLongVal.toString());
		}
		log.debug(" getShow $$$$$$$$$$$$$$$$$$$$$$$$$$$"
				+ routingVO.getShow());
		if (routingVO.getShow().equalsIgnoreCase("U")) {
			routingVO.setRoutedBy("User");

			routingVO.setAssignThisRecordTo(routedTo);
			routingVO.setRoutedToName(routedToName);
			routingVO.setAssignedUserName(routedToName);
			logCaseDataBean.getCaseDetailsVO().setAssignedTo(routedToName);
			logCaseDataBean.getCaseDetailsVO().setAssignedToWorkUnitSK(routedTo);
		}
		if (routingVO.getShow().equalsIgnoreCase("W")) {

			routingVO.setRoutedBy("Work Unit");
			log.info("-====Work Unit ");
			routingVO.setDeptName(routedTo);
			routingVO.setWorkUnit(routedTo);
			routingVO.setRoutedToName(routedToName);
			routingVO.setAssignedUserName(routedToName);
			logCaseDataBean.getCaseDetailsVO().setAssignedTo(routedToName);
			logCaseDataBean.getCaseDetailsVO().setAssignedToWorkUnitSK(routedTo);
		}
		logCaseDataBean.getRoutingInfoList().add(routingVO);
		CMRoutingVO tempRoutingVO = new CMRoutingVO();
		tempRoutingVO.setCreatedBy(logCaseDataBean.getCaseDetailsVO()
				.getCreatedBy());
		log.debug("created by $$$$$$$$$$$$$$$$$$$$$$$$$$$"
				+ logCaseDataBean.getCaseDetailsVO().getCreatedBy());
		log.debug("created by  sk $$$$$$$$$$$$$$$$$$$$$$$$$$$"
				+ logCaseDataBean.getCaseDetailsVO().getCreatedBySK());
		tempRoutingVO.setRoutedByWorkUntiSK(Long.valueOf(logCaseDataBean
				.getCaseDetailsVO().getCreatedBySK()));
		tempRoutingVO.setAssignedTo(logCaseDataBean.getCaseDetailsVO()
				.getAssignedTo());

		logCaseDataBean.setRoutingVO(tempRoutingVO);

		logCaseDataBean.setRenderedroutingFlag(false);
		logCaseDataBean.setSaveRoutingFlag(false);
		logCaseDataBean.setShowCaseMessage(false);

		ContactManagementHelper.setErrorMessage(
				MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG, null,
				null, null);
		logCaseDataBean.setShowCaseDetailsMessages(false); // Added for the defect ESPRD00712855_19OCT2011
		logCaseDataBean.setShowCaseRoutingMessages(false);
		logCaseDataBean.setShowRoutingDataTable(true);
		logCaseDataBean.setDduExistedRecordErrorMsg(true); // Added for the defect id ESPRD00710222_12OCT2011
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "saveRouting");
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is the action method to create Letter For Event
	 */
	public String createLetterForEvent() {
		log.debug("Inside createLetterForEvent");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setNavToOtherPortletFlag(true);
		String caseEventMode = "";
		if (logCaseDataBean.isShowAddCaseEvents()) {
			caseEventMode = "addCaseEvent";
		} else if (logCaseDataBean.isShowEditCaseEvents()) {
			caseEventMode = "editCaseEvent";
		}
		log.debug("++before calling save caseEventMode::"
				+ caseEventMode);
		saveCaseRecord();

		//      ADD FOR THE DEFECT ESPRD00601882 18 MARCH 2011
		log.debug("++validateFlag:" + validateFlag);

		if (validateFlag) {
			CaseDetailsVO caseDetailsVO = logCaseDataBean.getCaseDetailsVO();
			log.debug("++caseDetailsVO.getCaseSK()--"
					+ caseDetailsVO.getCaseSK());
			/* FIND BUGS_FIX */
			if (caseDetailsVO.getCaseSK() != null
					&& !caseDetailsVO.getCaseSK().equals(Long.valueOf(0))) {
				String funcArea = caseDetailsVO.getCaseSK().toString();//getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_SK);
				String funcAreaCD = GlobalLetterConstants.FUNCTIONAL_AREA_CD_CASE;//getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_AREA);
				String letterCategory = "";//getReqParamFromCtx(GlobalLetterConstants.STR_LETTER_CATEGORY);
				//CR_ESPRD00463663_LogCase_Changes_22MAY2010
				log.debug("++after calling save caseEventMode::"
						+ caseEventMode);
				String logCaseEventSeqNum = getReqParamFromCtx("LogCaseEventSeqNum");
				log.debug("logCaseEventSeqNum : before"
						+ logCaseEventSeqNum);
				//    		if(logCaseEventSeqNum==null ||
				// logCaseEventSeqNum.equals(MaintainContactManagementUIConstants.EMPTY_STRING)){
				if(log.isDebugEnabled()){
				log.debug("++logCaseDataBean.getCaseEventsVOList().size()::::"
								+ logCaseDataBean.getCaseEventsVOList().size());
				}
				if (logCaseDataBean.getCaseEventsVOList().size() > 0
						&& !caseEventMode.equals("")) {
					if (caseEventMode.equals("addCaseEvent")) {
						//for defect ESPRD00864837
						/** Sequence no of latest added event.
						 * */
						Integer eventMaxSeq=logCaseDataBean.getAddEventSeq();
						if(eventMaxSeq==null || Integer.valueOf(0)==eventMaxSeq){
							eventMaxSeq = new Integer(1);
						}
						logCaseEventSeqNum = Integer.toString(eventMaxSeq);
						log.debug("===createLetterForEvent--Add mode--logCaseEventSeqNum"
								+ logCaseEventSeqNum);
						/*
						CaseEventsVO caseEventsVO = (CaseEventsVO) logCaseDataBean
								.getCaseEventsVOList().get(
										logCaseDataBean.getCaseEventsVOList()
												.size() - 1);
						log.debug(caseEventsVO.getCaseEventSeqNum());
						logCaseEventSeqNum = caseEventsVO
								.getCaseEventSeqNumStr();
						log.debug("===createLetterForEvent--Add mode--logCaseEventSeqNum"
										+ logCaseEventSeqNum);
					*/} else if (caseEventMode.equals("editCaseEvent")) {
						log.debug("--logCaseDataBean.getCaseEventsIndex()--"
										+ logCaseDataBean.getCaseEventsIndex());
						//if (!isNullOrEmptyField(logCaseDataBean.getCaseEventsIndex())) {
							//for defect ESPRD00864837
							/* assigning sequence num captured in vo to do convertor before save method
							 * becoz event index is nullifying in restore case state method of LogCaseHelper */
							logCaseEventSeqNum = Integer.toString(logCaseDataBean.getEditEventSeq());
							log.debug("===createLetterForEvent--edit mode--logCaseEventSeqNum"
									+ logCaseEventSeqNum);
							/*
							CaseEventsVO caseEventsVO = (CaseEventsVO) logCaseDataBean
									.getCaseEventsVOList().get(
											Integer.parseInt(logCaseDataBean
													.getCaseEventsIndex()));
							log.debug(caseEventsVO
									.getCaseEventSeqNum());
							logCaseEventSeqNum = caseEventsVO
									.getCaseEventSeqNumStr();
							log.debug("===createLetterForEvent--edit mode--logCaseEventSeqNum"
											+ logCaseEventSeqNum);
						}*/
					}
				}
				//    		}
				log.debug("logCaseEventSeqNum : after"
						+ logCaseEventSeqNum);
				log.debug("funcArea : " + funcArea);
				log.debug("funcAreaCD : " + funcAreaCD);
				log.debug("letterCategory : " + letterCategory);

				CommonLetterInputVO inputVO = new CommonLetterInputVO();
				inputVO.setFunctionalAreaCode(funcAreaCD);
				inputVO.setFunctionalAreaSK(funcArea);
				inputVO.setLetterCategory(letterCategory);
				//CR_ESPRD00463663_LogCase_Changes_22MAY2010
				if (logCaseEventSeqNum != null
						&& logCaseEventSeqNum.trim().length() > 0) {
					inputVO.setSentSeqNum(logCaseEventSeqNum);
				}//EOF CR_ESPRD00463663_LogCase_Changes_22MAY2010
				if(logCaseDataBean.getCaseEventsVO() !=null &&
						logCaseDataBean.getCaseEventsVO().getTemplate()!=null){
					log.debug("Inside Case Event Letter Name :" + logCaseDataBean.getCaseEventsVO().getTemplate());
					inputVO.setLetterID(logCaseDataBean.getCaseEventsVO().getTemplate());
				}
				FacesContext facesContext = FacesContext.getCurrentInstance();
				Map requestScope = (Map) facesContext.getApplication()
						.createValueBinding("#{requestScope}").getValue(
								facesContext);
				requestScope.put(GlobalLetterConstants.COMMON_LTR_INPUT,
						inputVO);

				getDataBeanFromContext().setRenderEditSection(false);
				log.info("End Inside createLetterForEvent");
			}
		}

		return GlobalLetterConstants.SUCCESS;
	}

	/**
	 * This method is the action method to create Letter For Event
	 */
	public String createLetterForStep() {
		log.info("Inside createLetterForStep");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setNavToOtherPortletFlag(true);
		String caseStepMode = "";
		if (logCaseDataBean.isShowAddCaseSteps()) {
			caseStepMode = "addCaseStep";
		} else if (logCaseDataBean.isShowEditCaseSteps()) {
			caseStepMode = "editCaseStep";
		}
		log.debug("++before calling save caseStepMode::"
				+ caseStepMode);
		saveCaseRecord();
		if (validateFlag) {
			CaseDetailsVO caseDetailsVO = logCaseDataBean.getCaseDetailsVO();
			log.debug("++caseDetailsVO.getCaseSK()--"
					+ caseDetailsVO.getCaseSK());
			/* FIND BUGS_FIX */
			if (caseDetailsVO.getCaseSK() != null
					&& !caseDetailsVO.getCaseSK().equals(Long.valueOf(0))) {
				String funcArea = caseDetailsVO.getCaseSK().toString();//getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_SK);
				String funcAreaCD = GlobalLetterConstants.FUNCTIONAL_AREA_CD_CASE;//getReqParamFromCtx(GlobalLetterConstants.STR_FUNCTIONAL_AREA);
				String letterCategory = "";//getReqParamFromCtx(GlobalLetterConstants.STR_LETTER_CATEGORY);
				//CR_ESPRD00463663_LogCase_Changes_22MAY2010
				log.debug("++after calling save caseStepMode::"
						+ caseStepMode);
				String logCaseStepSeqNum = getReqParamFromCtx("LogCaseStepSeqNum");
				log.debug("logCaseStepSeqNum : before "
						+ logCaseStepSeqNum);
				//    		if(logCaseStepSeqNum==null ||
				// logCaseStepSeqNum.equals(MaintainContactManagementUIConstants.EMPTY_STRING)){
				if(log.isDebugEnabled()){
				log.debug("++logCaseDataBean.getCaseStepsVOList().size()::::"
								+ logCaseDataBean.getCaseStepsVOList().size());
				}
				if (logCaseDataBean.getCaseStepsVOList().size() > 0
						&& !caseStepMode.equals("")) {
					log.debug("--logCaseDataBean.isShowAddCaseSteps()--"
									+ logCaseDataBean.isShowAddCaseSteps());
					if (caseStepMode.equals("addCaseStep")) {
						//for defect ESPRD00864837
						/** last added sequence number we are retrieving from 
						 *  the databean and passing to the letter page. 
						 * */
						Integer stepMaxSeq=logCaseDataBean.getAddStepSeq();
						log.debug("stepMaxSeq="+stepMaxSeq);
						if(stepMaxSeq==null || Integer.valueOf(0)==stepMaxSeq){
							stepMaxSeq = new Integer(1);
						}
						logCaseStepSeqNum = Integer.toString(stepMaxSeq);
						/*CaseStepsVO caseStepsVO = (CaseStepsVO) logCaseDataBean
								.getCaseStepsVOList().get(
										logCaseDataBean.getCaseStepsVOList()
												.size() - 1);
						log.debug(caseStepsVO.getCaseStepSeqNum());
						logCaseStepSeqNum = caseStepsVO.getCaseStepSeqNumStr();*/
						log.debug("===createLetterForStep--Add mode--logCaseStepSeqNum"
										+ logCaseStepSeqNum);
					} else if (caseStepMode.equals("editCaseStep")) {
						log.debug("--logCaseDataBean.getEditStepSeq()--"
										+ logCaseDataBean.getEditStepSeq());
						//for defect ESPRD00864837
						/** sequence number of step edited latest is passed.
						 * */
						logCaseStepSeqNum = Integer.toString(logCaseDataBean.getEditStepSeq());
						/*if (logCaseDataBean.getCaseStepsIndex() != null
								&& !logCaseDataBean.getCaseStepsIndex().equals(
										"")) {
							CaseStepsVO caseStepsVO = (CaseStepsVO) logCaseDataBean
									.getCaseStepsVOList().get(
											Integer.parseInt(logCaseDataBean
													.getCaseStepsIndex()));
							log.debug(caseStepsVO.getCaseStepSeqNum());
							logCaseStepSeqNum = caseStepsVO
									.getCaseStepSeqNumStr();
							log.debug("===createLetterForStep--edit mode--logCaseStepSeqNum"
											+ logCaseStepSeqNum);
						}*/
					}
				}
				//    		}
				log.debug("logCaseStepSeqNum : after"
						+ logCaseStepSeqNum);//EOF
											 // CR_ESPRD00463663_LogCase_Changes_22MAY2010
				log.debug("funcArea : " + funcArea);
				log.debug("funcAreaCD : " + funcAreaCD);
				log.debug("letterCategory : " + letterCategory);

				CommonLetterInputVO inputVO = new CommonLetterInputVO();
				inputVO.setFunctionalAreaCode(funcAreaCD);
				inputVO.setFunctionalAreaSK(funcArea);
				inputVO.setLetterCategory(letterCategory);
				//CR_ESPRD00463663_LogCase_Changes_22MAY2010
				if (logCaseStepSeqNum != null
						&& logCaseStepSeqNum.trim().length() > 0) {

					inputVO.setSentSeqNum(logCaseStepSeqNum);

				}//EOF CR_ESPRD00463663_LogCase_Changes_22MAY2010
				if(logCaseDataBean.getCaseStepsVO() !=null &&
						logCaseDataBean.getCaseStepsVO().getTemplate()!=null){
					log.debug("Inside Case Stpes Letter Name :" + logCaseDataBean.getCaseStepsVO().getTemplate());
					inputVO.setLetterID(logCaseDataBean.getCaseStepsVO().getTemplate());
				}
				FacesContext facesContext = FacesContext.getCurrentInstance();
				Map requestScope = (Map) facesContext.getApplication()
						.createValueBinding("#{requestScope}").getValue(
								facesContext);
				requestScope.put(GlobalLetterConstants.COMMON_LTR_INPUT,
						inputVO);

				getDataBeanFromContext().setRenderEditSection(false);
				log.info("End Inside createLetterForStep");
			}
		}

		return GlobalLetterConstants.SUCCESS;
	}

	/**
	 * This method gets request parameter from invoking module
	 * 
	 * @param requestParam
	 * @return String
	 */
	private String getReqParamFromCtx(String requestParam) {
		String param = null;
		log.info("Inside getReqParamFromCtx");

		param = (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(requestParam);

		return param;
	}

	/**
	 * This method get FilterDataBean object.
	 * 
	 * @return LettersAndResponsesDataBean.
	 */
	public final LettersAndResponsesDataBean getDataBeanFromContext() {

		log.info("Inside getDataBeanFromContext");
		FacesContext fc = FacesContext.getCurrentInstance();
		LettersAndResponsesDataBean letterTemplateDataBean = (LettersAndResponsesDataBean) fc
				.getApplication().createValueBinding(
						"#{" + GlobalLetterConstants.LETTER_RESPONSES_DATA_BEAN
								+ "}").getValue(fc);
		return letterTemplateDataBean;
	}

	//ADDED FOR THE DEFECT ESPRD00603234

	public void disableCaseEventDate(ValueChangeEvent event) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (StringUtils.isNotEmpty(event.getNewValue().toString())) {
			logCaseDataBean.setDisableEventDate(true);

		} else {
			logCaseDataBean.setDisableEventDate(false);
		}
	}

	//END FOR THE DEFECT ESPRD00603234

	private void sortCaseType(List caseList) {

		Comparator selectItemComparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				String first = null;
				String second = null;

				SelectItem s1 = (SelectItem) obj1;
				SelectItem s2 = (SelectItem) obj2;

				boolean ascending = true;

				if (s1.getLabel() != null && s2.getLabel() != null) {
					first = s1.getLabel().toLowerCase();
					second = s2.getLabel().toLowerCase();

					return first.compareTo(second);
				}
				return 0;

			}

		};

		Collections.sort(caseList, selectItemComparator);
	}

private List getValidDataNew(Map map, String referenceDataConstant,
             String functionalArea)
     {
     	log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + " getValidDataNew");
     	//Method inner class to sort SelectItem values
     	
     	
    	class ISelectItem extends javax.faces.model.SelectItem implements Comparable<ISelectItem>,Serializable 
		{

			private Object  _value;
		    private String  _label;
		    private String  _description;
		   private boolean _disabled;
		   
			public ISelectItem() {

			}

			public ISelectItem(Object value, String label) {
				if (value == null)
					throw new NullPointerException("value");

				if (label == null)
					throw new NullPointerException("label");
				_value = value;
				_label = label;
				_description = null;
				_disabled = false;
			}

			public Object getValue() {
				return _value;
			}

			public void setValue(Object value) {
				if (value == null)
					throw new NullPointerException("value");
				_value = value;
			}

			public String getLabel() {
				return _label;
			}

			public void setLabel(String label) {
				if (label == null)
					throw new NullPointerException("label");
				_label = label;
			}

			public int compareTo(ISelectItem s) {

				return Long.valueOf(this.getValue().toString()).compareTo(Long.valueOf(s
						.getValue().toString()));

			}
		}
     	
     	
     	
     	//Method inner class to sort SelectItem values
     	
         List validList = new ArrayList();
        /*
		 * if (!referenceDataConstant
		 * .equals(ReferenceServiceDataConstants.A_RSN_CD_APP)) {
		 * validList.add(new ISelectItem(
		 * MaintainContactManagementUIConstants.EMPTY_STRING,
		 * MaintainContactManagementUIConstants.EMPTY_STRING)); }
		 */
         String validValueCodeDesc = null;        
         List validValuesList = (List) map.get(functionalArea + "#"
                 + referenceDataConstant);

         int validValuesSize = validValuesList.size();
         for (int i = 0; i < validValuesSize; i++)
         {
             ReferenceServiceVO refVo = (ReferenceServiceVO) validValuesList
                     .get(i);
             validValueCodeDesc = refVo.getValidValueCode() + "-"
                     + refVo.getShortDescription();
             validList.add(new ISelectItem(refVo.getValidValueCode(),
                     validValueCodeDesc));
         }
     	log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + " getValidData");
         return validList;
     }	/**
		   * This method is used to sort Correspondence records associated with
		   * this case span information based on sorted column and order.
		   * 
		   * @param event
		   *            ActionEvent : It catches the user action event
		   * @return String : It will return success.
		   */
	public String sortCrRecordsAssoWithCase(ActionEvent event) {
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		LogCaseHelper caseHelper = new LogCaseHelper();
		logCaseDataBean.setCrRecordsAssocWithCaseRowIndex(0);
		logCaseDataBean.setImageRender(event.getComponent().getId());
		caseHelper.sortCrAssocCaseRecords(sortColumn, sortOrder,
				logCaseDataBean.getCaseDetailsVO().getAssociatedCrList());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	//Added for defect ESPRD00675983
	public void getCaseStatusList() {

		//	    	Added for defect ESPRD00368025 Starts
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);  // Added for the Performance fix
		try {
			String userId = logCaseDataBean.getLoggedInUserID(); // Modified for the Performance fix
			String businessUnitDesc = null;
			ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();

			List buinessUnitDescs = delegate.getBuisnessUnitsDescs(userId);

			if (buinessUnitDescs != null) {

				if (buinessUnitDescs.size() == 1) {
					businessUnitDesc = (String) buinessUnitDescs.get(0);
					setCaseStatus(businessUnitDesc);
				} else {
					businessUnitDesc = ContactManagementConstants.AllOthers;
					setCaseStatus(businessUnitDesc);
				}
			} else {
				businessUnitDesc = ContactManagementConstants.AllOthers;
				setCaseStatus(businessUnitDesc);
			}
		} catch (LOBHierarchyFetchBusinessException e) {
			log.debug(e);
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.debug(e);
			log.error(e.getMessage(), e);
		}
		//	    	Added for defect ESPRD00368025 Ends
	}

	// Defect ESPRD00675983 ends

	//Begin - Added new method for the defect Id ESPRD00679441_18Aug2011
	/**
	 * This method is adding the records to routing from steps
	 */
	public void routingFromSteps() {
		//Begin - Modified the code for the defect Id ESPRD00679441_09Aug2011
		ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
		List caseStepList = logCaseDataBean.getCaseStepsVOList();
		Iterator itr1 = caseStepList.iterator();
		int count = 0;
		boolean statusFlag = true;
		String routeValue = "";
		String routedToName = "";
		while (itr1.hasNext()) {
			CaseStepsVO caseStepsVO = (CaseStepsVO) itr1.next();
			if (caseStepsVO.getStatus().equals("A")) {
				statusFlag = true;
				count++;
				if (count > 1) {
					statusFlag = false;
					break;
				}
				routeValue = caseStepsVO.getRouteTo();
				log.debug("------routeValue--------" + routeValue);
				if (!isNullOrEmptyField(routeValue)) {
					String routTo = routeValue;
					boolean workunitflag = false;
					Iterator itr = logCaseDataBean.getWorkUnitList().iterator();
					SelectItem sItem = null;
					while (itr.hasNext()) {
						sItem = (SelectItem) itr.next();
						if (routTo.equals(sItem.getValue().toString())) {
							log.debug("sItem.getValue().toString()---"
									+ sItem.getValue().toString());
							workunitflag = true;
							break;
						}
					}
					if (workunitflag) {
						log.debug("logCaseDataBean.getRoutingVO().getRouteTo:::"
										+ routTo);
						logCaseDataBean.getRoutingVO().setShow("W");
						logCaseDataBean.setShowAddRoutingAssignment(false);
						logCaseDataBean.setShowDepartments(false);
						logCaseDataBean.setShowWorkUnit(true);
						setAllDepartmentsList();
						logCaseDataBean.setShowUsers(false);
						logCaseDataBean.setShowUserDepartments(false);
						logCaseDataBean.setShowBusinessUnitFields(false);
						logCaseDataBean.setShowRoutingDataTable(true);
						String deptName = contactManagementHelper
								.getDescriptionFromVV(routTo, logCaseDataBean
										.getWorkUnitList());
					} else {
						log.info("  User :");
						log.debug("  User routTo :" + routTo);
						logCaseDataBean.getRoutingVO().setShow("U");
						logCaseDataBean.setShowAddRoutingAssignment(false);
						logCaseDataBean.setShowDepartments(false);
						setAllDepartmentsList();
						logCaseDataBean.setShowUsers(true);
						logCaseDataBean.setShowUserDept(true);
						logCaseDataBean.setShowWorkUnit(false);
						logCaseDataBean.setShowBusinessUnitFields(false);
						logCaseDataBean.setShowUserDepartments(true);
						logCaseDataBean.setShowBusinessUnitFields(false);
						logCaseDataBean.setShowRoutingDataTable(true);
					}
					routedToName = contactManagementHelper
							.getDescriptionFromVV(routeValue, logCaseDataBean
									.getRouteAllDeptUsersList());
				} else {
					statusFlag = false;
				}
			}
			//if an active steps is routed and succeding with inactive steps then statusflag should be true becoz
			//routeTo value has a valid value.
			else if(count!=1) {
				statusFlag = false;
			}
		}
		// for validate the routing is already is done.
		String assignedToWorkUnitSk = logCaseDataBean.getCaseDetailsVO()
				.getAssignedToWorkUnitSK();
		if (log.isDebugEnabled()) {
			log.debug("assignedToWorkUnitSk $$$$$$$$" + assignedToWorkUnitSk);
			log.debug("routedWorkUnitSk$$$$$$$$$$" + routeValue);
			log.debug("statusFlag$$$$$$$$$$" + statusFlag);
		}
		boolean alreadyRouted = (!isNullOrEmptyField(assignedToWorkUnitSk)
				&& !isNullOrEmptyField(routeValue) && assignedToWorkUnitSk
				.trim().equals(routeValue.trim()));
		if (statusFlag == true && !alreadyRouted) {
			if (log.isDebugEnabled()) {
				log.debug("routing from case steps started $$$$$$$");
			}
			saveRoutingFromSteps(routeValue, routedToName);
		}
		//End - Modified the code for the defect Id ESPRD00679441_09Aug2011
	}

	//End - Added new method for the defect Id ESPRD00679441_18Aug2011

	/**
	 * This method is used to display defaulted values in JSP for case events.
	 */
	private void setDefaultCaseEvent() {
		CaseEventsVO caseEventsVO = new CaseEventsVO();

		caseEventsVO.setCreateDate(new Date());
		caseEventsVO.setCreateDateStr(ContactManagementHelper
				.dateConverter(caseEventsVO.getCreateDate()));
		caseEventsVO.setDateDispositionFlag(Boolean.TRUE);
		caseEventsVO
				.setStatusCd(MaintainContactManagementUIConstants.CASE_EVENT_STATUS_TYPE);

		logCaseDataBean.setCaseEventsVO(caseEventsVO);
		logCaseDataBean.setDisableCaseEventFields(Boolean.TRUE);
		logCaseDataBean.setShowDDUAppointmentScreen(Boolean.FALSE);
		logCaseDataBean.setRenderedCaseEventsflag(Boolean.TRUE);
		logCaseDataBean.setSaveCaseEventFlag(Boolean.FALSE);
		logCaseDataBean.setDisableCaseEventFields(Boolean.TRUE);
		logCaseDataBean.setCaseEventDeleteMsgFlag(Boolean.FALSE);
		logCaseDataBean.setCaseEventsCreateLetterStatus(Boolean.FALSE);
	}
	
	// Begin - Added the method for the defect id UC-PGM-CRM-018.1_ESPRD00709390_02NOV2011
	/**
	 * BR CON0356.0001.01
     * users who belong to the same Business Unit of the currently assigned to user..
	 * @param caseTypeSk
	 * @param assignedUsrSk
     */
	 // Added for the Defect: ESPRD00709390
    private void getAllUsersSameBU(String caseTypSk,String assignedUsrSk, Boolean isAssingedToUser )
    {
        log.info("getListOfUsers");
      
        Long caseTypeSK = Long.valueOf(caseTypSk);
        Long assignedUserSk = Long.valueOf(assignedUsrSk);
        CaseDelegate caseDelegate = new CaseDelegate();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        try
        {
            List tempListOfUsers = caseDelegate.getAllUsersSameBU(caseTypeSK,assignedUserSk,isAssingedToUser);
            
            if (tempListOfUsers != null)
            {
            log.debug("getListOfUsers size:" + tempListOfUsers.size());
            List userOfSameBU = logCaseDataBean.getUserOfSameBU();
            userOfSameBU.clear();

                for (Iterator iter = tempListOfUsers.iterator(); iter.hasNext();)
                {
                	Object[] userData = (Object[]) iter.next();
                    String name = ContactManagementConstants.EMPTY_STRING;
                    log.debug("User id " + userData[1].toString());
                   
                    if (userData[3] != null && !isNullOrEmptyField(userData[3].toString()))
                    {
                    	name = userData[3].toString();
                    }
                    if((userData[3] != null && !isNullOrEmptyField(userData[3].toString())) &&
                    		(userData[2] != null && !isNullOrEmptyField(userData[2].toString())))
                    {
                    	name = name 
                        + ContactManagementConstants.COMMA_SEPARATOR
                        + ContactManagementConstants.SPACE_STRING;
                    }
                    if (userData[2] != null && !isNullOrEmptyField(userData[2].toString()))
                    {
                    	name = name + userData[2].toString();
                    }
                   log.debug("User Name " + name);
                    String userWrkUtSk = userData[0].toString();
                    
                    StringBuffer userLabel = new StringBuffer().append(
                    		name).append(
                            ContactManagementConstants.HYPHEN_SEPARATOR).append(
                            		userData[1].toString());

                    userOfSameBU.add(new SelectItem(userData[0].toString(), userLabel.toString()));
                    
                }
                logCaseDataBean.setUserOfSameBU(userOfSameBU);
            }
        }
        catch (CaseRecordFetchBusinessException e)
        {
        	e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        
    }
	// End - Added the method for the defect id UC-PGM-CRM-018.1_ESPRD00709390_02NOV2011

	// Begin - Added new method for sorting the values from the list
	// for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011	
	/**
	 * This method is used to compare the objects
	 * @return comparator
	 */
	private Comparator getStringComerator()
	{
		Comparator comparator = new Comparator()
        {
            /*
             * (non-Javadoc)
             * 
             * @see java.util.Comparator#compare(java.lang.Object,
             *      java.lang.Object)
             */
            public int compare(Object obj1, Object obj2)
            {
                log.info("compare");
                int returnValue = 0;
                String string1 = (String) obj1;
                String string2 = (String) obj2;
                returnValue = string1.compareToIgnoreCase(string2);
                return returnValue;
            }
        };
        return comparator;
	}
	// End - Added new method for sorting the values from the list
	// for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
	
	// Begin - Added new methods to get the valid values from the list
	// for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
	 /**
	 * To get the description based on code.
	 * 
	 * @param code
	 *            Holds the code valus.
	 * @param vvList
	 *            Holds the List of valid values.
	 * @return String
	 */
	public String getDescriptionFromVV(String code, List vvList) {
		String desc = "";
		String valueStr = "";
		int size = vvList.size();
		for (int i = 0; i < size; i++) {
			SelectItem selectItem = (SelectItem) vvList.get(i);
			valueStr = "";
			if (selectItem != null) {
				valueStr = (String) selectItem.getValue();
				if (valueStr != null && valueStr.equals(code)) {
					desc = selectItem.getLabel();
					break;
				}
			}
		}
		return desc;
	}
	// End - Added new methods to get the valid values from the list
	// for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
	
	// Begin - Added the code for the defect id ESPRD00725239_16NOV2011
	/**
	* Holds the value of EntityTypeIdForNotesPrint.
	*/
	private String EntityTypeIdForNotesPrint;
	
	/**
	 * @param entityTypeIdForNotesPrint the entityTypeIdForNotesPrint to set
	 */
	public void setEntityTypeIdForNotesPrint(String entityTypeIdForNotesPrint) {
		EntityTypeIdForNotesPrint = entityTypeIdForNotesPrint;
	}

	/**
	 * This method is used to get the details of entityId and entityName for Notes.
	 */
	public String getEntityTypeIdForNotesPrint(){
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		
		if(logCaseDataBean.isShowCaseRegardingProvider()){
			logCaseDataBean.setEntityIdForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingProviderVO().getProviderId());
			logCaseDataBean.setEntityNameForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingProviderVO().getName());
		}
		else if(logCaseDataBean.isShowCaseRegardingMember()){
			logCaseDataBean.setEntityIdForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingMemberVO().getMemberId());
			logCaseDataBean.setEntityNameForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingMemberVO().getName());
		}
		else if(logCaseDataBean.isShowCaseRegardingUnEnrolMem()){
			logCaseDataBean.setEntityIdForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingTPLVO().getEntityId());
			logCaseDataBean.setEntityNameForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingTPLVO().getName());
		}
		else if(logCaseDataBean.isShowCaseRegardingUnEnrolProv()){
			logCaseDataBean.setEntityIdForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingTPLVO().getEntityId());
			logCaseDataBean.setEntityNameForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingTPLVO().getName());
		}
		else if(logCaseDataBean.isShowCaseRegardingOther()){
			logCaseDataBean.setEntityIdForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingTPLVO().getEntityId());
			logCaseDataBean.setEntityNameForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingTPLVO().getName());
		}
		else if(logCaseDataBean.isShowCaseRegardingTradPart()){
			logCaseDataBean.setEntityIdForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingTradingPartnerVO().getTradingPartnerId());
			logCaseDataBean.setEntityNameForNote(logCaseDataBean.getCaseRegardingVO()
					.getCaseRegardingTradingPartnerVO().getName());
		}
		return "Success";
	}
	// End - Added the code for the defect id ESPRD00725239_16NOV2011
	
	// Begin - Added new method to call the thread for the performance fix.
	/**
	* This method is used to start the thread.
	* @return e
	*/
	public Executor call(Object object, String methodName, Object[] args,
			Class[] argtypes) {
		Executor e = new Executor(object, methodName, args, argtypes);
		e.start();
		return e;
	}
	// End - Added new method to call the thread for the performance fix.

	//hash map performance issue code change starts
	private Map getCorrSrchReferenceData(String businessUnitDesc){
		Map referenceMap = null;
		
		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = null;
		
		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		List referenceList = new ArrayList();
		
		//entity type
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.ENTITYTYPE,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);

		//status
		inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		inputCriteriaEntityTyp.setListNumber(ContactManagementHelper
				.getSystemlistForCorrBU(ContactManagementConstants.CORR_STATUS,
						businessUnitDesc));

		referenceList.add(inputCriteriaEntityTyp);
		
		//priority
		inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		inputCriteriaEntityTyp
				.setElementName(ReferenceServiceDataConstants.G_CR_PRTY_CD);

		referenceList.add(inputCriteriaEntityTyp);
		
		referenceDataSearch.setInputList(referenceList);
		
		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);
			referenceMap = referenceDataListVO.getResponseMap();
		} catch (ReferenceServiceRequestException e) {
			log.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			log.error(e.getMessage(), e);
		}
		
		return referenceMap;
	}
	
	private List populateListFromMap(Map referenceMap,String key){
		
		List entityList = new ArrayList();
		
		List referenceList = (List) referenceMap.get(key);
		int referenceListSize = referenceList.size();

		for (int i = 0; i < referenceListSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
					.get(i);
		
			String codesAndDesc = refVo.getValidValueCode() + "-"
					+ refVo.getShortDescription();
			entityList.add(new SelectItem(refVo.getValidValueCode(),
					codesAndDesc));
		} 
	   return entityList;
	}
	
	private void populateCorrSearch(String businessUnitDesc){
		
		Map referenceMap=getCorrSrchReferenceData(businessUnitDesc);
		//status key
		String key = FunctionalAreaConstants.CONTACT_MGMT
				+ ProgramConstants.HASH_SEPARATOR
				+ String.valueOf(ContactManagementHelper
						.getSystemlistForCorrBU(
								ContactManagementConstants.CORR_STATUS,
								businessUnitDesc));
		logCaseDataBean.setStatusVVList(populateListFromMap(referenceMap,key));
		//entity type key
		key = FunctionalAreaConstants.CONTACT_MGMT
				+ ProgramConstants.HASH_SEPARATOR
				+ String.valueOf(ContactManagementHelper
						.getSystemlistForCorrBU(
								ContactManagementConstants.ENTITYTYPE,
								businessUnitDesc));
		logCaseDataBean.setEntityTypeVVList(populateListFromMap(referenceMap,key));
		//priority
		key = FunctionalAreaConstants.CONTACT_MGMT
				+ ProgramConstants.HASH_SEPARATOR
				+ ReferenceServiceDataConstants.G_CR_PRTY_CD;
		logCaseDataBean.setPriorityVVList(populateListFromMap(referenceMap,key));
	}
	//hash map performance issue code change ends
	
//	hash map performance issue code change
	private CaseType getCaseTypeFrmDosList(String caseSk,String shortDesc){
		CaseType caseType=null; 
		List caseTypeDosList=logCaseDataBean.getCaseTypeDOList();
		if (caseTypeDosList != null && caseTypeDosList.size() > 0) {
			for (int i = 0; i < caseTypeDosList.size(); i++) {
				caseType = (CaseType) caseTypeDosList.get(i);
				if (caseSk!=null && caseType
						.getCaseTypeSK().toString().equals(caseSk)) {
					caseType = caseType;
					break;
				}else if(shortDesc!=null && caseType
						.getShortDescription().equals(shortDesc)){
					caseType = caseType;
					break;
				}
			}
		}
		return caseType;
	}
	
	// Begin - Added new method for the defect id ESPRD00702153_30NOV2011.
	/**
	* This method is used to get the userName by passing the workUnit and typeCode.
	*/
	public String getName(WorkUnit workUnit, String workUnitTypeCode)
    {

        String name = ContactManagementConstants.EMPTY_STRING;

        if (workUnit != null && "U".equalsIgnoreCase(workUnitTypeCode))
        {
            EnterpriseUser user = workUnit.getEnterpriseUser();
            if (!isNullOrEmptyField(user.getLastName()))
            {
            	name = user.getLastName();
            }
            if(!isNullOrEmptyField(user.getLastName()) &&
            		!isNullOrEmptyField(user.getFirstName()))
            {
            	name = name 
                + ContactManagementConstants.COMMA_SEPARATOR
                + ContactManagementConstants.SPACE_STRING;
            }
            if (!isNullOrEmptyField(user.getFirstName()))
            {
            	name = name + user.getFirstName();
            }
        }
        if (workUnit != null && "W".equalsIgnoreCase(workUnitTypeCode))
        {
            Department department = workUnit.getDepartment();
            name = department.getName();
        }

        if (workUnit != null && "B".equalsIgnoreCase(workUnitTypeCode))
        {
            Department department = workUnit.getDepartment();
            name = department.getName();
        }
        return name;
    }
	// End - Added new method for the defect id ESPRD00702153_30NOV2011.
	
	// Begin - Added this code for the CorrRecAssoWithThisCase
	/**
	 * Holds the value of deleteFlagForCorrRecAssoWithThisCase
	 */
	private boolean deleteFlagForCorrRecAssoWithThisCase = false;

	/**
	 * @return the deleteFlagForCorrRecAssoWithThisCase
	 */
	public boolean isDeleteFlagForCorrRecAssoWithThisCase() {
		return deleteFlagForCorrRecAssoWithThisCase;
	}

	/**
	 * @param deleteFlagForCorrRecAssoWithThisCase the deleteFlagForCorrRecAssoWithThisCase to set
	 */
	public void setDeleteFlagForCorrRecAssoWithThisCase(
			boolean deleteFlagForCorrRecAssoWithThisCase) {
		this.deleteFlagForCorrRecAssoWithThisCase = deleteFlagForCorrRecAssoWithThisCase;
	}
	// End - Added this code for the CorrRecAssoWithThisCase
	//ADDED FOR THE DEFECT ESPRD00736967
	public void getAdvOptionsCaseMinus() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setAdvOptionsF2(false);
		log.debug("logCaseDataBean.setAdvOptionsF2"+logCaseDataBean.isAdvOptionsF2());

	}
	public void getAdvOptionsCasePlus() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setAdvOptionsF2(true);
		log.debug("logCaseDataBean.setAdvOptionsF2"+logCaseDataBean.isAdvOptionsF2());

	}
	//ADDED END FOR THE DEFECT ESPRD00736967
	
	private void refreshCaseRecordOnPageSave(String caseSk){
		
		String entityType = null;
		String entityID = null;
		CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = new CaseRecordSearchCriteriaVO();
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
		CaseDelegate caseDelegate2 = new CaseDelegate();
		CommonCMCaseDetailsVO commonCMCaseDetailsVO = null;
		
		caseRecordSearchCriteriaVO.setCaseSK(caseSk);
		
		try {
			enterpriseSearchResultsVO = caseDelegate2
					.getCaseRecords(caseRecordSearchCriteriaVO);
			
			if (enterpriseSearchResultsVO.getSearchResults() != null
					&& enterpriseSearchResultsVO.getSearchResults()
							.size() == 1) {
				
				CaseRecordSearchResultsVO searchResultsVO = (CaseRecordSearchResultsVO) enterpriseSearchResultsVO
						.getSearchResults().get(0);
				
				commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
				
				commonCMCaseDetailsVO.setCaseSK(caseSk);
				
				
				entityType = searchResultsVO.getEntityType();
				
				/* FIND BUGS_FIX */
				
				if (searchResultsVO.getCommonEntitySK() != null
						&& !searchResultsVO.getCommonEntitySK()
								.equals(Long.valueOf(0))) {
					
					
					entityID = searchResultsVO.getCommonEntitySK()
							.toString();
					
				}
				commonCMCaseDetailsVO.setEntityType(entityType);
				
				
				commonCMCaseDetailsVO.setEntityID(entityID);
				
				
				commonCMCaseDetailsVO.setActionCode("CaseUpdate");
				
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession httpSession = (HttpSession) context
						.getExternalContext().getSession(true);
				//Modified for defect ESPRD00549039 starts
				showCasePortlet(commonCMCaseDetailsVO);
				httpSession.setAttribute("CaseAudit","CaseAuditDetails");
				
				//defect ESPRD00549039 ends
			}
		} catch (CaseRecordFetchBusinessException e) {
			e.printStackTrace();
		}
	}
	
	/** Reset Page level message flags.
	 * */
	private void resetMsgFlags(){
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		
		logCaseDataBean.setShowCaseDetailsMessages(Boolean.FALSE);
		logCaseDataBean.setShowCaseStepsMessages(Boolean.TRUE);
		logCaseDataBean.setShowCaseStepsDelteMessage(Boolean.FALSE);
		logCaseDataBean.setShowCaseAlertsMessages(Boolean.TRUE);
		logCaseDataBean.setShowCaseEventsMessages(Boolean.TRUE);
		logCaseDataBean.setCaseEventDeleteMsgFlag(Boolean.FALSE);
		logCaseDataBean.setShowCaseMessage(Boolean.TRUE);
		logCaseDataBean.setShowCaseRoutingMessages(Boolean.TRUE);
		logCaseDataBean.setShowDeleteMessage(Boolean.FALSE);
		logCaseDataBean.setCaseCrspnAsctErrormsg(Boolean.TRUE);
		logCaseDataBean.setDduExistedRecordErrorMsg(Boolean.TRUE);
		
	}
	
	/** The recieved array of object holds any
	 *  null value then it will be replaced with
	 *  empty string('').
	 * */
	private void assignEmptyIfNull(Object[] object){
		if(object!=null && object.length!=0){
			for(int i=0;i<object.length;i++){
				if(object[i]==null){
					log.debug("null value referencing $$$$$$$$$$$$$$"+i);
					object[i]=ContactManagementConstants.EMPTY_STRING;
				}
			}
		}
	}
	
	/**
	 * This method is to populate notify via alert list in Events, Steps and
	 * Alert section
	 * */
	private void populateNotifyViaAlertList(String caseType) {

		List notifyViaAlertList = new ArrayList();
		List eventsNotifyViaAlertList = new ArrayList();
		if (!isNullOrEmptyField(caseType)) {
			// notify via alert drop down populate.
			notifyViaAlertList.add(new SelectItem(
					ContactManagementConstants.SPACE_STRING,
					ContactManagementConstants.EMPTY_STRING));
			try {
				List notifyList = new CaseDelegate().getNotifyViaUsers(Long
						.valueOf(caseType));
				if (notifyList != null && !notifyList.isEmpty()) {
					log.debug("notifyList $$$$$$$$$$$" + notifyList.size());
					StringBuffer sbName = null;
					String name = ContactManagementConstants.EMPTY_STRING;
					for (int i = 0; i < notifyList.size(); i++) {
						Object[] userObject = (Object[]) notifyList.get(i);
						assignEmptyIfNull(userObject);

						sbName = new StringBuffer();
						if (!userObject[2]
								.equals(ContactManagementConstants.EMPTY_STRING))
							sbName
									.append(userObject[2])
									.append(",")
									.append(
											MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
						if (!userObject[1]
								.equals(ContactManagementConstants.EMPTY_STRING))
							sbName
									.append(userObject[1])
									.append(
											MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
									.append(
											MaintainContactManagementUIConstants.HYPHEN);
						if (!sbName
								.equals(ContactManagementConstants.EMPTY_STRING)) {
							sbName
									.append(
											MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
									.append(userObject[3].toString());
							name = sbName.toString();
						} else {
							name = userObject[3].toString();
						}

						notifyViaAlertList.add(new SelectItem(userObject[0]
								.toString(), name));
						eventsNotifyViaAlertList.add(new SelectItem(
								userObject[3].toString(), name));

					}
				}
			} catch (CaseRecordFetchBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logCaseDataBean.setUserList(notifyViaAlertList);
		logCaseDataBean.setEventNotifyList(eventsNotifyViaAlertList);
		log.debug("notifyViaAlertList $$$$$$$$$$$$$$$"
				+ notifyViaAlertList.size());
		logCaseDataBean.setUserIDsList(notifyViaAlertList);
	}
	
	/**
	 * This methoed is used to refresh case detials data.
	 * */
	private void loadLogCasePageWithCaseSk(String caseSk) {
		log.debug("in loadLogCasePageWithCaseSk $$$$$$$$"+caseSk);

		try {
			if (!isNullOrEmptyField(caseSk)) {
				LogCaseHelper helper = new LogCaseHelper();

				helper.restoreCaseState();
				// statusflag = true; // Added for defect ESPRD00675983
				logCaseDataBean.setCaseSK(caseSk);
				logCaseDataBean.setActionCode("CaseUpdate");
				logCaseDataBean.setSysIdForUP(""); // Added for defect
													// ESPRD00688792

				// logCaseDataBean.setEntityType(entityType);//moved
				// down after GetCaseRecord for defect ESPRD00560416
				Long caseNum = Long.valueOf(caseSk);
				logCaseDataBean.setFromIPC(true);
				log.debug("After long convertion caseSK is ##**## " + caseNum);
				logCaseDataBean.setCancelLinkUpdate(true);

				getCaseTypes();
				// Modified for defect ESPRD00560416 starts
				// getEntities(entityID, entityType, false,
				// true);//moved down after GetCaseRecord for defect
				// ESPRD00560416
				populateInitialDataForUpdate();

				CaseRecord caseRecord = getCaseRecord(caseNum);
				//for 790505
				logCaseDataBean.setAttachmentListSize(logCaseDataBean.getAttachmentVOList().size());
				Set ss = caseRecord.getCaseCommonEntityCrossRefs();
				String entId = "";
				String entType = "";
				for (Iterator itr = ss.iterator(); itr.hasNext();) {
					CaseCommonEntityCrossRef xref = (CaseCommonEntityCrossRef) itr
							.next();
					if (xref.getCaseCRContactReasonCode().equalsIgnoreCase("P")) {
						entId = xref.getCommonEntitySK().toString();
						entType = xref.getCommonEntityTypeCode();
					}

				}
				log.debug(" cmlogcase controllerbean ::entId::"
						+ entId);
				log.debug(" cmlogcase controllerbean ::entType::"
						+ entType);
				logCaseDataBean.setEntityType(entType);
				getEntities(entId, entType, false, true);
				// defect ESPRD00560416 ends
				// ADDED FOR THE DEFECT ESPRD00732649
				executeBRCON307();// ESPRD00517164_UC-PGM-CRM-18_16SEP2010

				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession httpSession = (HttpSession) context
						.getExternalContext().getSession(true);
				String PhysicianOverseeingEntity = (String) httpSession
						.getAttribute("PhysicianOverseeingEntity");
				log.debug("PhysicianOverseeingEntity"
						+ PhysicianOverseeingEntity);
				if (PhysicianOverseeingEntity != null) {
					if (logCaseDataBean.getCaseDetailsVO() != null) {
						if (logCaseDataBean.getCaseDetailsVO()
								.getCaseTypeBCCPVO() != null) {
							logCaseDataBean.getCaseDetailsVO()
									.getCaseTypeBCCPVO()
									.setPhysicianOverseeingCare(
											PhysicianOverseeingEntity);
							log
									.debug("logCaseDataBean.getCaseDetailsVO().getCaseTypeBCCPVO().setPhysicianOverseeingCare$$$$$$$$"
											+ logCaseDataBean
													.getCaseDetailsVO()
													.getCaseTypeBCCPVO()
													.getPhysicianOverseeingCare());
						}
					}
					httpSession.removeAttribute("PhysicianOverseeingEntity");
				}

				// ADDED END FOR THE DEFECT ESPRD00732649
				getCFElementsInUpdate();

				commonNotesControllerBean.showNotes();

				/** Added By Madhurima */
				copyCommonEntitiesRefs();

				// testing
				String caseAudit = (String) httpSession
						.getAttribute("CaseAudit");
				log.debug("caseAudit $$$$$$$$$$$$$$$$$$$$" + caseAudit);
				if (caseAudit != null) {
					log.debug("inside if condition $$$$$$$$$$$$$$$$$$$");
					logCaseDataBean.setEnableAuditLogForLogCase(true);
					httpSession.removeAttribute("CaseAudit");
				}
			}
		// Added for the Defect : ESPRD00790505 
		logCaseDataBean.setEnableAuditLogs(false);
		} 
		catch (Exception excep) {
			excep.printStackTrace();
		}

	}
	//Added new method  for the defect Id ESPRD00778564 Starts
	/**
	 * This method will get the username in lastname, firstname - userid format for
	 * recieved enterpriseuser object.
	 * */
	private String userNameAPI(EnterpriseUser enterpriseUser) {
		StringBuffer sbName = new StringBuffer();
		if (!isNullOrEmptyField(enterpriseUser.getLastName())) {
			sbName.append(enterpriseUser.getLastName()).append(
					ContactManagementConstants.COMMA_SEPARATOR).append(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
		}
		if (!isNullOrEmptyField(enterpriseUser.getFirstName())) {
			sbName.append(enterpriseUser.getFirstName()).append(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
			sbName.append(MaintainContactManagementUIConstants.HYPHEN);
		}
		if (!isNullOrEmptyField(enterpriseUser.getUserID())) {
			sbName.append(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
					.append(enterpriseUser.getUserID());
		}
		return sbName.toString();
	}
	
	/**
	 * To collapse sub sections in log case page
	 * */
	private void collapseSections() {

		logCaseDataBean
				.setRoutingHidden(MaintainContactManagementUIConstants.PLUS);
		//Modified for the defect ESPRD00903176 starts
		/*logCaseDataBean
				.setAssocCaseAndCRHidden(MaintainContactManagementUIConstants.PLUS);*/
		logCaseDataBean.setSearchForCaseAndCR(false);
		logCaseDataBean.setAssociatePlusMinusFlag(true);
		//defect ESPRD00903176 ends
		logCaseDataBean
				.setCaseAttachmentsHidden(MaintainContactManagementUIConstants.PLUS);
		logCaseDataBean.setLettersAndRespStyleVal("none");
		logCaseDataBean
				.setCaseEventsHidden(MaintainContactManagementUIConstants.PLUS);
		logCaseDataBean
				.setCaseAlertsHidden(MaintainContactManagementUIConstants.PLUS);
		logCaseDataBean
				.setCaseStepsHidden(MaintainContactManagementUIConstants.PLUS);
		//for defect ESPRD00880351
		//reset the provider and member alternate identifier section close
		logCaseDataBean
				.setProvAltIdentifierHidden(MaintainContactManagementUIConstants.PLUS);
		logCaseDataBean
				.setMemAltIdentifierHidden(MaintainContactManagementUIConstants.PLUS);
	}
	

	/**
	 * @return the loadDataFromEntity
	 */
	public String getLoadDataFromEntity() {
		PortletSession portletSession = (PortletSession) FacesContext
		.getCurrentInstance().getExternalContext().getSession(false);

		if (portletSession != null
		&& portletSession.getAttribute("DataMap") != null) {
			Map dataMap = (Map) portletSession.getAttribute("DataMap");

	portletSession.removeAttribute("DataMap");
	
	String identification = dataMap.get("Identification").toString();

	if (identification != null && identification.equals("C")) {
		String entityType = dataMap.get("EntityType").toString();
		if (entityType != null && !entityType.equals("")) {
			logCaseDataBean
					.getCorrespondenceSearchCriteriaVO().setEntityType(
							entityType);
			getCorrEntIDTypeValues(entityType,"Entity");
		}

		String entityIDType = dataMap.get("EntityIDType").toString();
		if (entityIDType != null && !entityIDType.equals("")) {
			logCaseDataBean
					.getCorrespondenceSearchCriteriaVO()
					.setEntityIDType(entityIDType);
		}

		String entityID = dataMap.get("EntityID").toString();
		if (entityID != null && !entityID.equals("")) {
			logCaseDataBean
					.getCorrespondenceSearchCriteriaVO().setEntityID(
							entityID);
		}

		}

	else if (identification != null && identification.equals("I")) {
		String inqentityType = dataMap.get("EntityType").toString();
		if (inqentityType != null && !inqentityType.equals("")) {
			logCaseDataBean
					.getCorrespondenceSearchCriteriaVO()
					.setInqEntityType(inqentityType);
			getCorrEntIDTypeValues(inqentityType,"Inquiry");
		}

		String inqentityIDType = dataMap.get("EntityIDType").toString();
		if (inqentityIDType != null && !inqentityIDType.equals("")) {
			logCaseDataBean
					.getCorrespondenceSearchCriteriaVO()
					.setInqEntityIDType(inqentityIDType);
		}

		String inqentityID = dataMap.get("EntityID").toString();
		if (inqentityID != null && !inqentityID.equals("")) {
			logCaseDataBean
					.getCorrespondenceSearchCriteriaVO()
					.setInqEntityID(inqentityID);
		}

		}
	else{
		if(log.isDebugEnabled()){
			log.debug("- inside the else block og getLoadDataFromEntity method-");
		}
	}
	}
		logCaseDataBean.setLoadEntityDataFlag(false);
		return loadDataFromEntity;
	}

	/**
	 * @param loadDataFromEntity the loadDataFromEntity to set
	 */
	public void setLoadDataFromEntity(String loadDataFromEntity) {
		this.loadDataFromEntity = loadDataFromEntity;
	}
	/**
	 * This Method will call when click on Search Entity button of Inquiring
	 * About Id section in Assoicated Correspondence, for Defect ESPRD00802462
	 * */
	public String doCorrSearchInqEntityAction() {

		CMLogCaseDataBean cmLogCaseDatabean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = cmLogCaseDatabean
				.getCorrespondenceSearchCriteriaVO();

		boolean isValid = validateCorresFor(correspondenceSearchCriteriaVO);

		if (isValid) {
			CorrespondenceSearchCriteriaVO newCorrespondenceSearchCriteriaVO = new CorrespondenceSearchCriteriaVO();
			newCorrespondenceSearchCriteriaVO.setFromLogCase(true);
			logCaseDataBean.setLoadEntityDataFlag(true);
			newCorrespondenceSearchCriteriaVO
			.setInqEntityType(correspondenceSearchCriteriaVO
					.getInqEntityType());
			newCorrespondenceSearchCriteriaVO
			.setInqEntityIDType(correspondenceSearchCriteriaVO
					.getInqEntityIDType());
			newCorrespondenceSearchCriteriaVO
			.setInqEntityID(correspondenceSearchCriteriaVO
					.getInqEntityID());
			FacesContext facesContext = FacesContext.getCurrentInstance();

			Map requestScope = (Map) facesContext.getApplication()
					.createValueBinding("#{requestScope}").getValue(
							facesContext);

			requestScope.put("CrsSearchCrtVO",
					newCorrespondenceSearchCriteriaVO);

		}

		return MaintainContactManagementUIConstants.SUCCESS;
	}
	
	/**
	 * ESPRD00823632 listener method is not able to assign the selected value by
	 * the user hence hidden command button is implemented.
	 * */
	public String dispositionDateForEvents() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		CaseEventsVO caseEventsVO = logCaseDataBean.getCaseEventsVO();
		if (MaintainContactManagementUIConstants.CASE_EVENT_STATUS_TYPE
				.equalsIgnoreCase(caseEventsVO.getStatusCd())
				|| (MaintainContactManagementUIConstants.EMPTY_STRING
						.equalsIgnoreCase(caseEventsVO.getStatusCd()))) {
			caseEventsVO.setDateDispositionFlag(true);
			caseEventsVO
					.setDispositionDateStr(ContactManagementConstants.EMPTY_STRING);
		} else {
			caseEventsVO.setDateDispositionFlag(false);
			String dtStr = ContactManagementHelper.dateConverter(new Date());
			caseEventsVO.setDispositionDate(new Date());
			logCaseDataBean.getCaseEventsVO().setDispositionDateStr(dtStr);
		}
		logCaseDataBean.setCaseEventsVO(caseEventsVO);
		if (logCaseDataBean.isShowAddCaseEvents()) {
			logCaseDataBean.setPageFocusId("addCaseEventPageFocus");
			logCaseDataBean.setCursorFocusValue("LOGCASEEVENTSTATUS0");
		} else {
			logCaseDataBean.setPageFocusId("addCaseEventPageFocus");
			logCaseDataBean.setCursorFocusValue("LOGCASEEVENTSTATUS1");
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}
	
	
	/**
	 * for ESPRD00790505
	 * Required field validation for Edit attachment
	 * 
	 * @param attachment
	 * @return
	 */
	private boolean requiredFldsAvblforEdit(AttachmentVO attachment) {
		boolean fldsAvbl = true;
		if (isNullOrEmptyField(attachment.getDescription())) {
			fldsAvbl = false;
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.DESCRIPTION },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"attaddDesc");
		}
		return fldsAvbl;
	}
	
	/**
	 * Added for defect ESPRD00935080
	 * This method is used to retreive valid values in the Line Of Business
	 * dropdown
	 * 
	 * @return List
	 */
	public List getReferenceData(String elementName, String functionalArea) {
		log.info(ContactManagementConstants.BEGINMETHOD_CMLogCaseControllerBean + "getLobReferenceData");
		
		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		
		List vvList = new ArrayList();
		vvList.add(new SelectItem(
				MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
				MaintainContactManagementUIConstants.EMPTY_STRING));
		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();
		inputCriteriaEntityTyp
				.setFunctionalArea(functionalArea);
		inputCriteriaEntityTyp
				.setElementName(elementName);

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(functionalArea
							+ ProgramConstants.HASH_SEPARATOR
							+ elementName);
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);
				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();

				vvList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));

			}

		} catch (ReferenceServiceRequestException e) {
			e.printStackTrace();

		} catch (SystemListNotFoundException e) {
			e.printStackTrace();

		}
		log.info(ContactManagementConstants.ENDMETHOD_CMLogCaseControllerBean + "getLobReferenceData");
		return vvList;

	}
	/**
	 * getAssociateOptionsPlus added for defect ESPRD00903176 to expand the Associated case and Correspondence field set
	 * @return
	 */
	public String getAssociateOptionsPlus() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setSearchForCaseAndCR(true);
		logCaseDataBean.setAssociatePlusMinusFlag(false);
		return "success";

	}
	/**
	 * getAssociateOptionsMinus added for defect ESPRD00903176 to collapse the Assocated case and Correspondence field set
	 * @return
	 */
	public String getAssociateOptionsMinus() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setSearchForCaseAndCR(false);
		logCaseDataBean.setAssociatePlusMinusFlag(true);
		return "success";
	}
}