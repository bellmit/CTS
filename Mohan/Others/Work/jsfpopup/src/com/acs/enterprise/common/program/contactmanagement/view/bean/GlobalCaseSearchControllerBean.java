/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionResponse;
import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.GlobalCaseSearchConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.GlobalCaseSearchResultVO;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLGlobalSearchResultsVO;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLHIPPSearchResultVO;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLRecoverySearchResultVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;
import com.acs.enterprise.common.base.application.dataaccess.exception.EnterpriseBaseDataException;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
/**
 * This class implements the searching for existing CaseRecord information for ContactManagement,
 * TPL Recovery and TPL HIPPA functionalities.
 * 
 * @author Wipro
 */
public class GlobalCaseSearchControllerBean extends
		EnterpriseBaseControllerBean {

	/** Creates an instance of the logger. * */
	static final EnterpriseLogger log = EnterpriseLogFactory
			.getLogger(GlobalCaseSearchControllerBean.class.getName());
	// Moved to ContactManagementConstants.java
	//protected static final String BEGINMETHOD = "Begin of the GlobalCaseSearchControllerBean";
    //protected static final String ENDMETHOD = "End of the GlobalCaseSearchControllerBean";

	/**
	 * Constructor calls getReferenceData method.
	 */
	public GlobalCaseSearchControllerBean() {
		super();
		log.debug("GlbCaseSrchCntrBean constructor");

	}

	/**
	 * GlobalCaseSearchDataBean Object
	 */
	GlobalCaseSearchDataBean globalGlobalCaseSearchDataBean = getGlobalCaseSearchDataBean();

	/**
	 * CaseDelegate Object
	 */
	private CaseDelegate caseDelegate = new CaseDelegate();

	/**
	 * ReferenceServiceDelegate Objectarc
	 */
	private ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();

	/**
	 * This method is used to get the Case Search Data Bean.
	 * 
	 * @return CaseSearchDataBean : CaseSearchDataBean object.
	 */
	public GlobalCaseSearchDataBean getGlobalCaseSearchDataBean() {
		FacesContext fc = FacesContext.getCurrentInstance();

		GlobalCaseSearchDataBean globalCaseSearchDataBean = (GlobalCaseSearchDataBean) fc
				.getApplication().getVariableResolver().resolveVariable(fc,
						"#{" + ContactManagementConstants.BEAN_NAME_GlobalCaseSearchDataBean + "}");

		if (globalCaseSearchDataBean == null) {

			globalCaseSearchDataBean = (GlobalCaseSearchDataBean) fc
					.getApplication()
					.createValueBinding(
							ContactManagementConstants.BINDING_BEGIN_SEPARATOR
									+ ContactManagementConstants.BEAN_NAME_GlobalCaseSearchDataBean
									+ ContactManagementConstants.BINDING_END_SEPARATOR)
					.getValue(fc);
		}

		return globalCaseSearchDataBean;
	}

	/**
	 * This method is used to get the CaseRecordSearchcriteriaVo.
	 */
	public String resetGlobalSerachCase() {

		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + " resetGlobalSerachCase");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		HtmlInputText inputText = null;
		inputText = (HtmlInputText) uiViewRoot
				.findComponent("GlobalCaseSearch:firstName");
		inputText.setValue("");

		inputText = (HtmlInputText) uiViewRoot
				.findComponent("GlobalCaseSearch:middleName");
		inputText.setValue("");

		inputText = (HtmlInputText) uiViewRoot
				.findComponent("GlobalCaseSearch:LastName");
		inputText.setValue("");

		inputText = (HtmlInputText) uiViewRoot
				.findComponent("GlobalCaseSearch:providerSortName");
		inputText.setValue("");

		inputText = (HtmlInputText) uiViewRoot
				.findComponent("GlobalCaseSearch:carrierName");
		inputText.setValue("");

		globalGlobalCaseSearchDataBean
				.setCaseRecordSearchCriteriaVO(new CaseRecordSearchCriteriaVO());

		globalGlobalCaseSearchDataBean.setContctManagementnoData(false);

		globalGlobalCaseSearchDataBean.setTplRecoverynoData(false);

		globalGlobalCaseSearchDataBean.setTplHippnoData(false);

		globalGlobalCaseSearchDataBean.setContctManagementnoDataFlag(false);

		globalGlobalCaseSearchDataBean.setTplRecoverynoDataFlag(false);

		globalGlobalCaseSearchDataBean.setTplHippnoDataFlag(false);

		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + " resetGlobalSerachCase");
		return "";
	}
   /* Commented for GAI-CMGT_CASE-EnterpriseUser_Fix
    * Moved to GetUserList()
    * */
	/*public void populateUserIdNameMap()
	{

		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "getUsersList");
		EnterpriseUser enterpriseUser = null;
		List userData = null;
		String desc = null;
		Map temp = new HashMap();
		Map temp2 = new HashMap();
		try {
			userData = caseDelegate.getAllUsers();
			if (!userData.isEmpty()) {
				
				
				int size = userData.size();
				for (int i = 0; i < size; i++) {
					enterpriseUser = (EnterpriseUser) userData.get(i);
					if (enterpriseUser.getFirstName() != null) {
						desc = enterpriseUser.getFirstName();
						if(enterpriseUser.getLastName() != null){
//						desc = desc + " " + enterpriseUser.getLastName();
							desc = enterpriseUser.getLastName()+ " , " + desc;
							
						}
					}
					desc = desc + " - " + enterpriseUser.getUserID();
					temp.put(enterpriseUser.getUserID(), desc);
				temp2.put(enterpriseUser.getUserWorkUnitSK(), desc);
				}
			}
			globalGlobalCaseSearchDataBean.setUserIdNameMap(temp);
			globalGlobalCaseSearchDataBean.setUserSkNameMap(temp2);
			//ESPRD00560692 start. This section added to get list of department name
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			Set depts = new HashSet();
			 List deptUsers = null;
			ArrayList departmentList= new ArrayList();
			try {
				deptUsers = contactMaintenanceDelegate.getAllDepartmentUsers();
			} catch (LOBHierarchyFetchBusinessException e1) {
				e1.printStackTrace();
			}
			Iterator it2 = null;
			//Added if Condition for Find_Bugs-FIX
			if(deptUsers != null){
				it2 = deptUsers.iterator();
			  }
			//Added if Condition for Find_Bugs-FIX
			if(it2 != null){    
			   while (it2.hasNext())
			        {
			            DepartmentUser element = (DepartmentUser) it2.next();

			            depts.add(element.getDepartment());

			        }
			}
			for (Iterator iter = depts.iterator(); iter.hasNext();)
			        {
				 		Department dept = (Department) iter.next();
				 		log.debug("=Name="+ dept.getName());
			                 departmentList.add(dept.getName());

			        }
			globalGlobalCaseSearchDataBean.setDepartmentList(departmentList);
			//ESPRD00560692 ends
		}
		catch (CaseRecordFetchBusinessException e) {
			log.error(e.getMessage(), e);
		}
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "getUsersList");
	
	}*/
	public void searchGlobalCase() {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + " searchGlobalCase");
		log.debug("CaseSK From Databean:  "
				+ globalGlobalCaseSearchDataBean
						.getCaseRecordSearchCriteriaVO().getCaseSK());
		CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = null;
		GlobalCaseSearchConvertor globalCaseSearchConvertor = null;
		caseRecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) globalGlobalCaseSearchDataBean
				.getCaseRecordSearchCriteriaVO();
		globalGlobalCaseSearchDataBean.setContctManagementnoData(false);
		globalGlobalCaseSearchDataBean.setTplRecoverynoData(false);
		globalGlobalCaseSearchDataBean.setTplHippnoData(false);
		globalGlobalCaseSearchDataBean.setContctManagementnoDataFlag(false);
		globalGlobalCaseSearchDataBean.setTplRecoverynoDataFlag(false);
		globalGlobalCaseSearchDataBean.setTplHippnoDataFlag(false);
		getGlobalCaseSearchDataBean().setAuthUser(false);
		try {
			if (validate(caseRecordSearchCriteriaVO)) {
				/**
				 * For defect ESPRD00803287. Logged in user should be considered
				 * for filter while searching the case records.Hence value is
				 * retrieved form data bean if not exists then invoking method
				 * to get it from EnterpriseUserProfile object.
				 * */
				String userId = globalGlobalCaseSearchDataBean.getUserId();
				if (isNullOrEmptyField(userId)) {
					userId = getUserID();
				}
				caseRecordSearchCriteriaVO.setUserId(userId);
				EnterpriseSearchResultsVO searchResultsVO = null;
				EnterpriseSearchResultsVO tplSearchResultsVO = null;
				EnterpriseSearchResultsVO tplHIPPSearchResultsVO = null;
				GlobalCaseSearchResultVO caseResultVO = null;
				GlobalCaseSearchResultVO tplRecResultVO = null;
				GlobalCaseSearchResultVO tplHippResultVO = null;
				CommonCMCaseDetailsVO commonCMCaseDetailsVO = null;
				List totalResultList = new ArrayList();
				globalCaseSearchConvertor = new GlobalCaseSearchConvertor();
				caseRecordSearchCriteriaVO.setCaseSK(caseRecordSearchCriteriaVO
						.getCaseRecordNumber());
				//Modified for defect ESPRD00560768
				caseRecordSearchCriteriaVO.setSearchedFromPage("GLOBAL_CASE_SEARCH");
				log.debug("CASE SK   "
								+ caseRecordSearchCriteriaVO.getCaseSK());
				caseRecordSearchCriteriaVO.setReassignFlag(false); // Added for the HeapDump Fix
				Hashtable result = caseDelegate
						.getGlobalCaseRecords(caseRecordSearchCriteriaVO);
				// Commented for GAI EnterpriseUser_Fix
				//populateUserIdNameMap();
				searchResultsVO = (EnterpriseSearchResultsVO) result
						.get("CASE");
				tplSearchResultsVO = (EnterpriseSearchResultsVO) result
						.get("TPL_RECOVERY");
				tplHIPPSearchResultsVO = (EnterpriseSearchResultsVO) result
				         .get("TPL_HIPP");
				if (searchResultsVO != null
						&& !searchResultsVO.getSearchResults().isEmpty()) {
					log.debug("ContactManagement result size  "
							+ searchResultsVO.getSearchResults().size());

					List contSearchResult = searchResultsVO.getSearchResults();
					if (contSearchResult.size() == 1) {
						log.debug("If CaseSearch result size is one and Tpl result is Zero");
						List list = searchResultsVO.getSearchResults();
						CaseRecordSearchResultsVO caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) list
								.get(0);
						if (caseRecordSearchResultsVO != null) {
							caseResultVO = globalCaseSearchConvertor
									.convertCaseVOtoCommonVO(caseRecordSearchResultsVO, globalGlobalCaseSearchDataBean.getUserSkNameMap(),globalGlobalCaseSearchDataBean.getDepartmentList());
							if (caseRecordSearchResultsVO.getEntityType() != null
									&& !caseRecordSearchResultsVO
											.getEntityType().equalsIgnoreCase(
													"")) {
								caseResultVO
										.setEntityType(caseRecordSearchResultsVO
												.getEntityType());
								String entityTypeDesc = setShortDescription(
										FunctionalAreaConstants.CONTACT_MGMT,
										ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
										caseRecordSearchResultsVO
												.getEntityType());
								caseResultVO.setEntityTypeDesc(entityTypeDesc);

							} else {
								caseResultVO.setEntityType("");
							}
							if (caseRecordSearchResultsVO.getStatus() != null
									&& !caseRecordSearchResultsVO.getStatus()
											.equalsIgnoreCase("")) {
								String status = setShortDescription(
										FunctionalAreaConstants.GENERAL,
										ReferenceServiceDataConstants.G_CASE_STAT_CD,
										caseRecordSearchResultsVO.getStatus());
								caseResultVO.setStatus(status);
							}

							if (caseRecordSearchResultsVO.getLob() != null
									&& !caseRecordSearchResultsVO.getLob()
											.equalsIgnoreCase("")) {
								String lob = setShortDescription(
										FunctionalAreaConstants.REFERENCE,
										ReferenceServiceDataConstants.R_LOB_CD,
										caseRecordSearchResultsVO.getLob());
								caseResultVO.setLob(lob);
							}
						}
						totalResultList.add(caseResultVO);
						globalGlobalCaseSearchDataBean
								.setContctManagementnoDataFlag(true);
						commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
						commonCMCaseDetailsVO.setCaseSK(caseResultVO
								.getCaseSK());
						commonCMCaseDetailsVO.setEntityType(caseResultVO
								.getEntityType());
						commonCMCaseDetailsVO.setEntityID(caseResultVO
								.getEntityID());
						commonCMCaseDetailsVO.setActionCode("CaseUpdate");
						log.debug("Before calling CaseIpc method when list size is one");
						
						if(getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO() != null &&
								getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO().getEntityType()!=null){
							if(getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO().getEntityType().equals("M")){
							caseResultVO.setEntityTypeDesc("M-Member");
						}
						}
						caseResultVO.setModuleType("ContactManagement");
						//showCasePortlet(commonCMCaseDetailsVO);
						//added for the defect ESPRD00560681 02 feb2011
						/*HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
						HttpServletResponse renderResponse = null;
						EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
						FacesContext facesContext = FacesContext.getCurrentInstance();
						if (eup != null && eup.getCaseFilter() == null
								&& isNullOrEmptyField(eup.getCaseFilter())){
						System.err.println("ENTERED INTO CASEFILTER NULL CONDITION");
						getGlobalCaseSearchDataBean().setAuthUser(true);
						}else{
							System.err.println("ENTERED INTO CASEFILTER NOT NULL CONDITION");
							showCasePortlet(commonCMCaseDetailsVO);
						}*/
//	end	added for the defect ESPRD00560681 02 feb2011
					 } else {
							for (int i = 0; i < contSearchResult.size(); i++) {
							CaseRecordSearchResultsVO caseRecordSearchResultsVO = (CaseRecordSearchResultsVO) contSearchResult
									.get(i);
							if (caseRecordSearchResultsVO != null) {
								caseResultVO = globalCaseSearchConvertor
										.convertCaseVOtoCommonVO(caseRecordSearchResultsVO, globalGlobalCaseSearchDataBean.getUserSkNameMap(),globalGlobalCaseSearchDataBean.getDepartmentList());
								log.debug("---caseRecordSearchResultsVO.getAssignedTo()--"+i+"-"+caseRecordSearchResultsVO.getAssignedTo());
								log.debug("---caseRecordSearchResultsVO.getAssignedToUser()--"+caseRecordSearchResultsVO.getAssignedToUser());
								if (caseRecordSearchResultsVO.getEntityType() != null
										&& !caseRecordSearchResultsVO
												.getEntityType()
												.equalsIgnoreCase("")) {
									caseResultVO
											.setEntityType(caseRecordSearchResultsVO
													.getEntityType());
									String entityTypeDesc = setShortDescription(
											FunctionalAreaConstants.GENERAL,
											ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
											caseRecordSearchResultsVO
													.getEntityType());
									caseResultVO
											.setEntityTypeDesc(entityTypeDesc);
								} else {
									caseResultVO.setEntityType("");
								}
								if (caseRecordSearchResultsVO.getStatus() != null
										&& !caseRecordSearchResultsVO
												.getStatus().equalsIgnoreCase(
														"")) {
									String status = setShortDescription(
											FunctionalAreaConstants.GENERAL,
											ReferenceServiceDataConstants.G_CASE_STAT_CD,
											caseRecordSearchResultsVO
													.getStatus());
									caseResultVO.setStatus(status);
								}

								if (caseRecordSearchResultsVO.getLob() != null
										&& !caseRecordSearchResultsVO.getLob()
												.equalsIgnoreCase("")) {
									String lob = setShortDescription(
											FunctionalAreaConstants.REFERENCE,
											ReferenceServiceDataConstants.R_LOB_CD,
											caseRecordSearchResultsVO.getLob());
									caseResultVO.setLob(lob);
								}
								if(getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO() != null &&
										getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO().getEntityType()!=null){
									if(getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO().getEntityType().equals("M")){
									caseResultVO.setEntityTypeDesc("M-Member");
								}
								}
								caseResultVO.setModuleType("ContactManagement");
							}
							totalResultList.add(caseResultVO);
						}
							log.debug("totalResultList afterCaseRecordSearch :"
								+ totalResultList.size());
					}
				}
				if (tplSearchResultsVO != null
						&& !tplSearchResultsVO.getSearchResults().isEmpty()) {
					log.debug("TPLRecovery result size  "
							+ tplSearchResultsVO.getSearchResults().size());
					getGlobalCaseSearchDataBean().setAuthUser(false);
					log.debug("ENTERED INTO TPLSEARCHRESULTS NOT NULL AND NOT EMPTY CONDITION");
					List tplRecResult = tplSearchResultsVO.getSearchResults();
					for (int i = 0; i < tplRecResult.size(); i++) {
						TPLGlobalSearchResultsVO tplRecoverycaseResultVO = (TPLGlobalSearchResultsVO) tplRecResult
								.get(i);
						if (tplRecoverycaseResultVO != null) {
							tplRecResultVO = globalCaseSearchConvertor
									.convertTplRecVOtoCommonVO(tplRecoverycaseResultVO, globalGlobalCaseSearchDataBean.getUserIdNameMap());
							if (tplRecoverycaseResultVO.getStatus() != null
									&& !tplRecoverycaseResultVO.getStatus()
											.equalsIgnoreCase("")) {
							
								String status = setShortDescription(
										FunctionalAreaConstants.TPL,
										ReferenceServiceDataConstants.T_CASE_STAT_CD,
										tplRecoverycaseResultVO.getStatus());
								log.debug("===status::"+status);
								tplRecResultVO.setStatus(status);
							}
							// Defect ESPRD00560692 start
							if(tplRecoverycaseResultVO.getCaseType() != null && 
									!tplRecoverycaseResultVO.getCaseType().equalsIgnoreCase(""))
							{
															
						String caseType = setShortDescription(
								FunctionalAreaConstants.TPL,
								ReferenceServiceDataConstants.T_CASE_TY_CD,
								tplRecoverycaseResultVO.getCaseType());
						log.debug("caseType:::::" + caseType);
						tplRecResultVO.setCaseType(caseType);
								}
							else {
								tplRecResultVO.setCaseType("");
							}
//							 Defect ESPRD00560692 ends
							if (tplRecoverycaseResultVO.getLob() != null
									&& !tplRecoverycaseResultVO.getLob()
											.equalsIgnoreCase("")) {
								String lob = setShortDescription(
										FunctionalAreaConstants.REFERENCE,
										ReferenceServiceDataConstants.R_LOB_CD,
										tplRecoverycaseResultVO.getLob());
								tplRecResultVO.setLob(lob);
							}
							if(getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO() != null &&
									getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO().getEntityType()!=null){
								if(getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO().getEntityType().equals("M")){
									tplRecResultVO.setEntityTypeDesc("M-Member");
							}
							}
							tplRecResultVO.setModuleType("TPL Recovery");
						}
						totalResultList.add(tplRecResultVO);
					}
					log.debug("totalResultList afterTPLRecSearch :"
							+ totalResultList.size());

				}
				if (tplHIPPSearchResultsVO != null
						&& !tplHIPPSearchResultsVO.getSearchResults().isEmpty()) {
					log.debug("TPLRecovery result size  "
							+ tplHIPPSearchResultsVO.getSearchResults().size());
					getGlobalCaseSearchDataBean().setAuthUser(false);
					log.debug("ENTERED INTO TPLHIPP SEARCHRESULTS NOT NULL AND NOT EMPTY CONDITION");
					List tplHippResult = tplHIPPSearchResultsVO
							.getSearchResults();
					for (int i = 0; i < tplHippResult.size(); i++) {
						TPLGlobalSearchResultsVO tplHippSearchResultVO = (TPLGlobalSearchResultsVO) tplHippResult
								.get(i);
						if (tplHippSearchResultVO != null) {
							tplHippResultVO = globalCaseSearchConvertor
									.convertTplHippVOtoCommonVO(tplHippSearchResultVO, globalGlobalCaseSearchDataBean.getUserIdNameMap());
							if (tplHippSearchResultVO.getStatus() != null
									&& !tplHippSearchResultVO.getStatus()
											.equalsIgnoreCase("")) {
								String status = setShortDescription(
										FunctionalAreaConstants.TPL,
										ReferenceServiceDataConstants.T_HIPP_STAT_CD,
										tplHippSearchResultVO.getStatus());
								tplHippResultVO.setStatus(status);
							}

							if (tplHippSearchResultVO.getLob() != null
									&& !tplHippSearchResultVO.getLob()
											.equalsIgnoreCase("")) {
								String lob = setShortDescription(
										FunctionalAreaConstants.REFERENCE,
										ReferenceServiceDataConstants.R_LOB_CD,
										tplHippSearchResultVO.getLob());
								
										tplHippResultVO.setLob(lob);
							}
							
							if(getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO() != null &&
									getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO().getEntityType()!=null){
								if(getGlobalCaseSearchDataBean().getCaseRecordSearchCriteriaVO().getEntityType().equals("M")){
									tplHippResultVO.setEntityTypeDesc("M-Member");
							}
							}
							tplHippResultVO.setModuleType("TPL HIPP");
						}
						totalResultList.add(tplHippResultVO);
					}
					log.debug("totalResultList afterTPl HippSearch:"
							+ totalResultList.size());

				}
				getGlobalCaseSearchDataBean().setContactManagemtCaseList(
						totalResultList);
				log.debug("ContactManagemtCaseList size :"
						+ getGlobalCaseSearchDataBean()
								.getContactManagemtCaseList().size());
				if (totalResultList.isEmpty()) {
					log.debug("totalResultList size is Zero");
					setErrorMessage(
							EnterpriseMessageConstants.ERR_SW_NO_REC_FOUND,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);
					getGlobalCaseSearchDataBean().setContctManagementnoData(
							false);
					getGlobalCaseSearchDataBean()
							.setContctManagementnoDataFlag(false);
				} else if (totalResultList.size() == 1) {
					log.debug("totalResultList size is one");
					getGlobalCaseSearchDataBean().setContctManagementnoData(
							true);
					getGlobalCaseSearchDataBean()
							.setContctManagementnoDataFlag(false);
//	added for the defect ESPRD00560681 02 feb2011
					HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
					HttpServletResponse renderResponse = null;
					EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
					FacesContext facesContext = FacesContext.getCurrentInstance();
					if (eup != null && eup.getCaseFilter() == null
							&& isNullOrEmptyField(eup.getCaseFilter())){
						log.debug("ENTERED INTO CASEFILTER NULL CONDITION");
					getGlobalCaseSearchDataBean().setAuthUser(true);
					}else{
						if(searchResultsVO.getSearchResults().size()==1){
							showCasePortlet(commonCMCaseDetailsVO);
						}else if(tplSearchResultsVO.getSearchResults().size()==1 && tplRecResultVO.getCaseSK()!=null && !tplRecResultVO.getCaseSK().equals("")){
							TPLRecoverySearchResultVO tplRecoverySearchResultVO = new TPLRecoverySearchResultVO();
							log.debug("++tplRecResultVO.getCaseSK()::"+tplRecResultVO.getCaseSK());
							tplRecoverySearchResultVO.setRecoveryCaseID(tplRecResultVO.getCaseSK());
							ActionRequest request = (ActionRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
							log.debug("++ IPC to TPL Recovery starts..");
							request.setAttribute("sendVO2", tplRecoverySearchResultVO);
							
						}else if(tplHIPPSearchResultsVO.getSearchResults().size()==1 && tplHippResultVO.getCaseSK()!=null && !tplHippResultVO.getCaseSK().equals("")){
							//TPLHIPPSearchResultVO tplHIPPSearchResultVO = new TPLHIPPSearchResultVO();
							//log.debug("++tplHippResultVO.getCaseSK()::"+tplHippResultVO.getCaseSK());
							//tplHIPPSearchResultVO.setHippCaseId();
							String caseSK = tplHippResultVO.getCaseSK();
							ActionRequest request = (ActionRequest) FacesContext
							.getCurrentInstance().getExternalContext().getRequest();
							log.debug("++ IPC to TPL Hipp starts..");
							request.setAttribute("resultVO", caseSK);
							
						}
						log.debug("ENTERED INTO CASEFILTER NOT NULL CONDITION");
				}
//	end added for the defect ESPRD00560681 02 feb2011
				} else {
					getGlobalCaseSearchDataBean().setContctManagementnoData(
							true);
					getGlobalCaseSearchDataBean()
							.setContctManagementnoDataFlag(false);
				}
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
		}
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "searchGlobalCase");
	}

	private boolean validate(CaseRecordSearchCriteriaVO searchCriteriaVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "validate");
		boolean flag = true;
		globalGlobalCaseSearchDataBean.setShowGlobalCaseSearchMsgFlag(false);
		try{
			if ((searchCriteriaVO.getEntityId().trim() == null || searchCriteriaVO
				.getEntityId().trim().equalsIgnoreCase(""))
				&& (searchCriteriaVO.getAssignedTo() == null || searchCriteriaVO
						.getAssignedTo().equalsIgnoreCase(""))
				&& (searchCriteriaVO.getStatus() == null || searchCriteriaVO
						.getStatus().equalsIgnoreCase(""))
				&& (searchCriteriaVO.getCreatedBy() == null || searchCriteriaVO
						.getCreatedBy().equalsIgnoreCase(""))
				&& (searchCriteriaVO.getEntityType() == null || searchCriteriaVO
						.getEntityType().equalsIgnoreCase(""))
				&& (searchCriteriaVO.getCaseRecordNumber() == null || searchCriteriaVO
						.getCaseRecordNumber().equals(""))) {
			flag = false;
			ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.SELECT_COLUMN);
			globalGlobalCaseSearchDataBean.setShowGlobalCaseSearchMsgFlag(true);
		} else {
			String entityID = searchCriteriaVO.getEntityId().trim();
			String entityType = null;
			if(searchCriteriaVO.getEntityType() != null){
				entityType = searchCriteriaVO.getEntityType().trim();
			}
			String crNumber = searchCriteriaVO.getCaseRecordNumber();
			String actionCode = null;
			if (entityID != null && !entityID.equalsIgnoreCase("")) {
				if (!EnterpriseCommonValidator.validateNumeric(entityID)) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.ENTITY_ID_VAL,
									MaintainContactManagementUIConstants.ADD_ENTITY_ID,
									MaintainContactManagementUIConstants.EDIT_ENTITY_ID,
									actionCode);
				} else {
					if (entityType == null || entityType.equalsIgnoreCase("")) {
						flag = false;
						ContactManagementHelper
								.setErrorMessage(
										MaintainContactManagementUIConstants.ENTITY_TYPE_REQ,
										MaintainContactManagementUIConstants.ENTITY_TYPE,
										MaintainContactManagementUIConstants.ENTITY_TYPE,
										actionCode);
					}

				}
			}
			if (crNumber != null && !crNumber.equals("")) {
				if (!EnterpriseCommonValidator.validateNumeric(crNumber)) {
					flag = false;
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.CASE_NUMBER_REQ,
									MaintainContactManagementUIConstants.ADD_CASE_NUMBER,
									MaintainContactManagementUIConstants.EDIT_CASE_NUMBER,
									actionCode);
				}
			}
			//Added for Validation of invalid Entity Names, for defect ESPRD00807682
			if(flag){
				flag = validateEntityName(searchCriteriaVO, flag);
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		log
				.debug("globalCaseSearchDataBean.setShowGlobalCaseSearchMsgFlag(true) :  "
						+ globalGlobalCaseSearchDataBean
								.isShowGlobalCaseSearchMsgFlag());
		log.debug("Flag value is  " + flag);
		
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "validate");
		return flag;
	}
	/**
	 * This Method Added for Validating invalid EntityNames,for defect ESPRD00807682
	 * */
	private boolean validateEntityName(
			CaseRecordSearchCriteriaVO searchCriteriaVO, boolean flag)
    {
       
        boolean flagInd = flag;

        String firstName = searchCriteriaVO.getFirstName();
        String lastName = searchCriteriaVO.getLastName();
        String middleName = searchCriteriaVO.getMiddleName();
        String sortName = searchCriteriaVO.getProviderSortName();
        String Name=searchCriteriaVO.getName();
        String orgName = searchCriteriaVO.getOrganizationName();
        String carrierName = searchCriteriaVO.getCarrierName();
        String EntityType=searchCriteriaVO.getEntityType();
        if(EntityType.equalsIgnoreCase("M") || EntityType.equalsIgnoreCase("P") || EntityType.equalsIgnoreCase("UM") || EntityType.equalsIgnoreCase("UP") || EntityType.equalsIgnoreCase("TP") )
        {
        /** Validate First Name */
        if (!isNullOrEmptyField(firstName) && !validateAlphaNumeric(firstName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.FIRST_NAME},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "firstName");
            flagInd = false;
        }
        
        /** Validate Last Name */
        if (!isNullOrEmptyField(lastName) && !validateAlphaNumeric(lastName))
        {
        	setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "LastName");
            flagInd = false;
        }

        /** Validate Middle Name */
        if(!EntityType.equalsIgnoreCase("TP")){
        if (!isNullOrEmptyField(middleName)
                && !validateMiddlename(middleName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.MIDDLE_INITIAL},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "middleName");
            flagInd = false;
        }
        }
        }
        /** Validate  Name */
        if(EntityType.equalsIgnoreCase("TD")){
        if (!isNullOrEmptyField(Name)
                && !validateAlphaNumeric(Name))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "Name");
            flagInd = false;
        }
        }
        /** Validate Sort Name */
        if (!isNullOrEmptyField(sortName) && !validateAlphaNumeric(sortName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.MIDDLE_INITIAL},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "providerSortName");
            flagInd = false;
        }
        
        /** Validate Organization Name */
        if (!isNullOrEmptyField(orgName) && !validateAlphaNumeric(orgName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.ORG_NAME_COMPID},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "OrganizationName");
            flagInd = false;
        }
        
        /** Validate Carrier Name */
        if (!isNullOrEmptyField(carrierName) && !validateAlphaNumeric(carrierName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.CARRIER_NAME_SEARCH},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "carrierName");
            flagInd = false;
        }

        return flagInd;
    }
	
	public static boolean validateAlphaNumeric(String expression)
    {

        Pattern p = Pattern.compile(ProgramConstants.ALPHANUMERIC_PATTERN_NAME);
        Matcher m = p.matcher(expression);

        return m.matches();
    }
	
	public static boolean validateMiddlename(String expression)
    {

        Pattern p = Pattern.compile(ProgramConstants.ALPHA_PATTERN_MIDDLE_NAME);
        Matcher m = p.matcher(expression);

        return m.matches();
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
			String messageBundle, String componentId) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "setErrorMessage");
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
			log.debug("Component ID " + componentId);

			UIComponent uiComponent = ContactManagementHelper
					.findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);

			log.debug("Client Id " + clientId);
		}
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "setErrorMessage");
		facesContext.addMessage(clientId, fc);
	}

	/**
	 * This operation is used to get all the Categories sorted on certain column
	 * and order.
	 * 
	 * @param event :
	 *            ActionEvent object.
	 * @return String
	 */
	public String getAllSortedGlobalCaseRecords(ActionEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "getAllSortedCaseRecords");
		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);

		globalGlobalCaseSearchDataBean.setContctManagementImageRender(event
				.getComponent().getId());
		//By default focus should come to first page after sort.
		globalGlobalCaseSearchDataBean.setHashContactManagmentCaseList(0);
		sortCaseSerachRecords(sortColumn, sortOrder,
				globalGlobalCaseSearchDataBean.getContactManagemtCaseList());
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "getAllSortedCaseRecords");
		return "success";

	}

	/**
	 * This operation is used to get all the Categories sorted on certain column
	 * and order.
	 * 
	 * @param event :
	 *            ActionEvent object.
	 * @return String
	 */
	public String getAllSortedTPLRecoveryCaseRecords(ActionEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "getAllSortedTPLRecoveryCaseRecords");

		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);

		globalGlobalCaseSearchDataBean.setTplRecoveryImageRender(event
				.getComponent().getId());
		//By default focus should come to first page after sort.
		globalGlobalCaseSearchDataBean.setHashTPLRecoveryCaseList(0);
		sortTPLCaseSerachRecords(sortColumn, sortOrder,
				globalGlobalCaseSearchDataBean.getTplRecoveryCaseList());
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "getAllSortedTPLRecoveryCaseRecords");
		return "success";

	}

	/**
	 * This operation is used to get all the Categories sorted on certain column
	 * and order.
	 * 
	 * @param event :
	 *            ActionEvent object.
	 * @return String
	 */
	public String getAllSortedTPLHippCaseRecords(ActionEvent event) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "getAllSortedTPLHippCaseRecords");
		String sortColumn = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.COLUMN_NAME);
		String sortOrder = (String) event.getComponent().getAttributes().get(
				ContactManagementConstants.SORT_ORDER);

		globalGlobalCaseSearchDataBean.setTplHippImageRender(event
				.getComponent().getId());
		//By default focus should come to first page after sort.
		globalGlobalCaseSearchDataBean.setHashTPLHippCaseList(0);
		sortTPLCaseSerachRecords(sortColumn, sortOrder,
				globalGlobalCaseSearchDataBean.getTplHIPPCaseList());
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "getAllSortedTPLHippCaseRecords");
		return "success";

	}

	/**
	 * This operation is used to get all the CaseSearch sorted on certain column
	 * and order.
	 * 
	 * @param sortColumn :
	 *            Column names to sort the columns
	 * @param sortOrder :
	 *            Oder Type to sort the Columns
	 * @param dataList :
	 *            List of the records to Sort
	 */
	private void sortCaseSerachRecords(final String sortColumn,
			final String sortOrder, List dataList) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "sortCaseSerachRecords");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				GlobalCaseSearchResultVO data1 = (GlobalCaseSearchResultVO) obj1;
				GlobalCaseSearchResultVO data2 = (GlobalCaseSearchResultVO) obj2;
				boolean ascending = false;
				if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
						.equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}
				if ("globalCaseSearchType".equals(sortColumn)) {
					if (null == data1.getType()) {
						data1
								.setType(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getType()) {
						data2
								.setType(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getType().trim().compareTo(
							data2.getType().trim()) : data2.getType().trim()
							.compareTo(data1.getType().trim());
				}
				if ("createdDate".equals(sortColumn)) {

					if (null == data1.getCreatedDate()) {
						data1
								.setCreatedDate(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getCreatedDate()) {
						data2
								.setCreatedDate(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getCreatedDate().compareTo(
							data2.getCreatedDate()) : data2.getCreatedDate()
							.compareTo(data1.getCreatedDate());
				}
				if ("entityName".equals(sortColumn)) {

					if (null == data1.getEntityName()) {
						data1
								.setEntityName(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getEntityName()) {
						data2
								.setEntityName(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getEntityName().trim().compareToIgnoreCase(
							data2.getEntityName().trim()) : data2
							.getEntityName().trim().compareToIgnoreCase(
									data1.getEntityName().trim());
				}
				if ("entityType".equals(sortColumn)) {
					log.debug("Entity Type   " + data1.getEntityType());
					log.debug("EntityType  " + data2.getEntityType());
					if (null == data1.getEntityType()) {
						data1
								.setEntityType(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getEntityType()) {
						data2
								.setEntityType(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getEntityType().trim().compareTo(
							data2.getEntityType().trim()) : data2
							.getEntityType().trim().compareTo(
									data1.getEntityType().trim());
				}
				if ("status".equals(sortColumn)) {

					if (null == data1.getStatus()) {
						data1
								.setStatus(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getStatus()) {
						data2
								.setStatus(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getStatus().trim().compareTo(
							data2.getStatus().trim()) : data2.getStatus()
							.trim().compareTo(data1.getStatus().trim());
				}
				if ("assignedTO".equals(sortColumn)) {

					if (null == data1.getAssignedTo()) {
						data1
								.setAssignedTo(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getAssignedTo()) {
						data2
								.setAssignedTo(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getAssignedTo().trim().compareTo(
							data2.getAssignedTo().trim()) : data2
							.getAssignedTo().trim().compareTo(
									data1.getAssignedTo().trim());
				}
				if ("caseType".equals(sortColumn)) {

					if (null == data1.getCaseType()) {
						data1
								.setCaseType(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getCaseType()) {
						data2
								.setCaseType(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getCaseType().trim().compareTo(
							data2.getCaseType().trim()) : data2.getCaseType()
							.trim().compareTo(data1.getCaseType().trim());
				}
				if ("lineofBusiness".equals(sortColumn)) {

					if (null == data1.getLob()) {
						data1.setLob(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getLob()) {
						data2.setLob(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getLob().trim().compareTo(
							data2.getLob().trim()) : data2.getLob().trim()
							.compareTo(data1.getLob().trim());
				}
				return 0;
			}

		};
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "sortCaseSerachRecords");
		Collections.sort(dataList, comparator);
	}

	/**
	 * This operation is used to get all the TPLCaseSearch sorted on certain
	 * column and order.
	 * 
	 * @param sortColumn :
	 *            Column names to sort the columns
	 * @param sortOrder :
	 *            Oder Type to sort the Columns
	 * @param dataList :
	 *            List of the records to Sort
	 */
	private void sortTPLCaseSerachRecords(final String sortColumn,
			final String sortOrder, List dataList) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "sortTPLCaseSerachRecords");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				TPLGlobalSearchResultsVO data1 = (TPLGlobalSearchResultsVO) obj1;
				TPLGlobalSearchResultsVO data2 = (TPLGlobalSearchResultsVO) obj2;
				boolean ascending = false;
				if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
						.equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}
				if ("globalCaseSearchType".equals(sortColumn)) {
					if (null == data1.getType()) {
						data1
								.setType(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getType()) {
						data2
								.setType(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getType().trim().compareTo(
							data2.getType().trim()) : data2.getType().trim()
							.compareTo(data1.getType().trim());
				}
				if ("createdDate".equals(sortColumn)) {

					if (null == data1.getCreatedDate()) {
						data1.setCreatedDate(new Date());
					}
					if (null == data2.getCreatedDate()) {
						data2.setCreatedDate(new Date());
					}
					return ascending ? data1.getCreatedDate().compareTo(
							data2.getCreatedDate()) : data2.getCreatedDate()
							.compareTo(data1.getCreatedDate());
				}
				if ("entityName".equals(sortColumn)) {

					if (null == data1.getEntityName()) {
						data1
								.setEntityName(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getEntityName()) {
						data2
								.setEntityName(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getEntityName().trim().compareToIgnoreCase(
							data2.getEntityName().trim()) : data2
							.getEntityName().trim().compareToIgnoreCase(
									data1.getEntityName().trim());
				}
				/*
				 * if ("entityType".equals(sortColumn)) { if (null ==
				 * data1.getEntityType()) { data1
				 * .setEntityType(MaintainContactManagementUIConstants.NULL); }
				 * if (null == data2.getEntityType()) { data2
				 * .setEntityType(MaintainContactManagementUIConstants.NULL); }
				 * return ascending ? data1.getEntityType().compareTo(
				 * data2.getEntityID()) : data2.getEntityType()
				 * .compareTo(data1.getEntityID()); }
				 */
				if ("status".equals(sortColumn)) {

					if (null == data1.getStatus()) {
						data1
								.setStatus(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getStatus()) {
						data2
								.setStatus(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getStatus().trim().compareTo(
							data2.getStatus().trim()) : data2.getStatus()
							.trim().compareTo(data1.getStatus().trim());
				}
				if ("assignedTO".equals(sortColumn)) {

					if (null == data1.getAssignedTo()) {
						data1
								.setAssignedTo(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getAssignedTo()) {
						data2
								.setAssignedTo(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getAssignedTo().trim().compareTo(
							data2.getAssignedTo().trim()) : data2
							.getAssignedTo().trim().compareTo(
									data1.getAssignedTo().trim());
				}
				if ("caseType".equals(sortColumn)) {

					if (null == data1.getCaseType()) {
						data1
								.setCaseType(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getCaseType()) {
						data2
								.setCaseType(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getCaseType().trim().compareTo(
							data2.getCaseType().trim()) : data2.getCaseType()
							.trim().compareTo(data1.getCaseType().trim());
				}
				if ("lineofBusiness".equals(sortColumn)) {

					if (null == data1.getLob()) {
						data1.setLob(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getLob()) {
						data2.setLob(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getLob().trim().compareTo(
							data2.getLob().trim()) : data2.getLob().trim()
							.compareTo(data1.getLob().trim());
				}
				return 0;
			}

		};
		Collections.sort(dataList, comparator);
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "sortTPLCaseSerachRecords");
	}

	/**
	 * @return
	 */
	public void submitCaseDetails() {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "submitCaseDetails");
		System.err.println("Entered into submitCaseDetails");
		HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
		.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderResponse = null;
		EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		CommonCMCaseDetailsVO commonCMCaseDetailsVO = null;
		if (eup != null && eup.getCaseFilter() == null
				&& isNullOrEmptyField(eup.getCaseFilter()) && map != null && (!map.get("entityType").toString().equalsIgnoreCase("TPL")) && (!map.get("entityType").toString().equalsIgnoreCase("TPL HIPP"))){
			//if(getGlobalCaseSearchDataBean().isAuthUser()){
				getGlobalCaseSearchDataBean().setAuthUser(true);
				//ContactManagementHelper
					//.setErrorMessage(MaintainContactManagementUIConstants.NO_AUTHORITY_ADD_CASE);
				System.err.println("Entered into EnterpriseUserProfile CASEFILTER NULL CONDITION ");
			//caseSearchDataBean.setShowCaseSearchMsgFlag(true);
			//}
		} else {
		System.err.println("Entered into EnterpriseUserProfile CASEFILTER ELSE CONDITION ");
		if (map != null) {
			
			Object moduleType =map.get("moduleType");
			if(moduleType!=null){
				String moduleTypeStr=moduleType.toString();
				System.err.println("==moduleTypeStr::"+moduleTypeStr);
				if(moduleTypeStr.equalsIgnoreCase("ContactManagement")){
					String caseSK = map.get("caseSK").toString();
					System.err.println("++Case SK in SearchCase -- " + caseSK);
					String entityType = map.get("entityType").toString();
					log.debug("EntityType in SearchCase -- " + entityType);
					String entityID = map.get("entityID").toString();
					log.debug("Entity ID in SearchCase -- " + entityID);
					commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
					commonCMCaseDetailsVO.setCaseSK(caseSK);
					commonCMCaseDetailsVO.setEntityType(entityType);
					commonCMCaseDetailsVO.setEntityID(entityID);
					commonCMCaseDetailsVO.setActionCode("CaseUpdate");
					 String action = (String) facesContext.getExternalContext().getRequestParameterMap().get("send.Global.Case.Search.Results");
					 System.err.println("++action--Logcase--"+action);
					System.err.println("++ IPC to Logcase starts..");
					showCasePortlet(commonCMCaseDetailsVO);
				}else if(moduleTypeStr.equalsIgnoreCase("TPL Recovery")){
					TPLRecoverySearchResultVO tplRecoverySearchResultVO = new TPLRecoverySearchResultVO();
					String caseSK = map.get("caseSK").toString();
					System.err.println("++Recovery Case SK  -- " + caseSK);
					 String action = (String) facesContext.getExternalContext().getRequestParameterMap().get("ACTION_NAME");
					 System.err.println("++action--Recovery--"+action);
					if(caseSK!=null && !caseSK.equals("") && action!=null && "sourceAction1".equalsIgnoreCase(action)){
						tplRecoverySearchResultVO.setRecoveryCaseID(caseSK);
						ActionRequest request = (ActionRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
						System.err.println("++ IPC to TPL Recovery starts..");
						request.setAttribute("sendVO2", tplRecoverySearchResultVO);
					}
				}else if(moduleTypeStr.equalsIgnoreCase("TPL HIPP")){
					//TPLHIPPSearchResultVO tplHIPPSearchResultVO = new TPLHIPPSearchResultVO();
					String caseSK = map.get("caseSK").toString();
					System.err.println("++HIPP Case SK  -- " + caseSK);
					 String action = (String) facesContext.getExternalContext().getRequestParameterMap().get("ACTION_NAME");
					 System.err.println("++action--HIPP--"+action);
					if(caseSK!=null && !caseSK.equals("") && action!=null && "SourceAction".equalsIgnoreCase(action)){
						//tplHIPPSearchResultVO.setHippCaseId(caseSK);
						ActionRequest request = (ActionRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
						System.err.println("++ IPC to TPL Hipp starts..");
						request.setAttribute("resultVO", caseSK);						
					}
				}
					
				
			}
		}
	}
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "submitCaseDetails");
	}

	/**
	 * It will opens the Log Case screen.
	 * 
	 * @param commonCMCaseDetailsVO
	 *            holds the case details.
	 */
	public void showCasePortlet(CommonCMCaseDetailsVO commonCMCaseDetailsVO) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "showCasePortlet");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletSession pSession = (PortletSession) facesContext.getExternalContext().getSession(true);
        pSession.setAttribute(ContactManagementConstants.ClearInqEntyData,
                                        ContactManagementConstants.ClearInqEntyData, pSession.APPLICATION_SCOPE);
		facesContext.getExternalContext().getRequestMap().put(
				"GlobalCaseSearchResult", commonCMCaseDetailsVO);
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "showCasePortlet");
	}

	public void entityTypeValueChange(ValueChangeEvent vce) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "entityTypeValueChange");
		CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO = globalGlobalCaseSearchDataBean
				.getCaseRecordSearchCriteriaVO();
		String entityTypeCode = vce.getNewValue().toString();
		getGlobalCaseSearchDataBean().setAuthUser(false);
		if (entityTypeCode.equalsIgnoreCase("P"))
		{
			caseRecordSearchCriteriaVO.setMemberInd(true);
			caseRecordSearchCriteriaVO.setProviderSortInd(true);
			caseRecordSearchCriteriaVO.setCarrierInd(false);
			caseRecordSearchCriteriaVO.setOrganizationNameInd(false);
			caseRecordSearchCriteriaVO.setTPLPolicyInd(false);
			caseRecordSearchCriteriaVO.setTradingPartnerInd(false);
		} else if (entityTypeCode.equalsIgnoreCase("M")
				|| entityTypeCode.equalsIgnoreCase("UM")) {
			caseRecordSearchCriteriaVO.setMemberInd(true);
			caseRecordSearchCriteriaVO.setProviderSortInd(false);
			caseRecordSearchCriteriaVO.setCarrierInd(false);
			caseRecordSearchCriteriaVO.setOrganizationNameInd(false);
			caseRecordSearchCriteriaVO.setTPLPolicyInd(false);
			caseRecordSearchCriteriaVO.setTradingPartnerInd(false);
		} else if (entityTypeCode.equalsIgnoreCase("TC")) {
			caseRecordSearchCriteriaVO.setMemberInd(false);
			caseRecordSearchCriteriaVO.setProviderSortInd(false);
			caseRecordSearchCriteriaVO.setCarrierInd(true);
			caseRecordSearchCriteriaVO.setOrganizationNameInd(false);
			caseRecordSearchCriteriaVO.setTradingPartnerInd(false);
		}else if (entityTypeCode.equalsIgnoreCase("TD")) {
			caseRecordSearchCriteriaVO.setTradingPartnerInd(true);
			caseRecordSearchCriteriaVO.setMemberInd(false);
			caseRecordSearchCriteriaVO.setProviderSortInd(false);
			caseRecordSearchCriteriaVO.setCarrierInd(false);
			caseRecordSearchCriteriaVO.setOrganizationNameInd(false);
		}
		else if (entityTypeCode.equalsIgnoreCase("TP")) {
			caseRecordSearchCriteriaVO.setTPLPolicyInd(true);
			caseRecordSearchCriteriaVO.setTradingPartnerInd(false);
			caseRecordSearchCriteriaVO.setMemberInd(true);
			caseRecordSearchCriteriaVO.setProviderSortInd(false);
			caseRecordSearchCriteriaVO.setCarrierInd(false);
			caseRecordSearchCriteriaVO.setOrganizationNameInd(false);
		}
		else if (entityTypeCode.equalsIgnoreCase("UP")) {
			caseRecordSearchCriteriaVO.setMemberInd(true);
			caseRecordSearchCriteriaVO.setProviderSortInd(false);
			caseRecordSearchCriteriaVO.setCarrierInd(false);
			caseRecordSearchCriteriaVO.setOrganizationNameInd(true);
			caseRecordSearchCriteriaVO.setTPLPolicyInd(false);
			caseRecordSearchCriteriaVO.setTradingPartnerInd(false);
		} else {
			caseRecordSearchCriteriaVO.setMemberInd(false);
			caseRecordSearchCriteriaVO.setProviderSortInd(false);
			caseRecordSearchCriteriaVO.setCarrierInd(false);
			caseRecordSearchCriteriaVO.setOrganizationNameInd(false);
			caseRecordSearchCriteriaVO.setTradingPartnerInd(false);
			caseRecordSearchCriteriaVO.setTPLPolicyInd(false);
		}
		loadAllValiedValues(entityTypeCode);
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "entityTypeValueChange");
	}

	public void loadAllValiedValues(String entityType) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "loadAllValiedValues");
		ReferenceDataSearchVO referenceDataSearch = null;
		ReferenceDataListVO referenceDataListVO = null;
		List list = new ArrayList();
		ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
		List buinessUnitDescs = null;
		String businessUnitDesc = null;         
        try{ 
        	/**
			 * UserId is retrieved form data bean if not exists then invoking
			 * method to get it from EnterpriseUserProfile object.
			 * */
			String userId = globalGlobalCaseSearchDataBean.getUserId();
			if (isNullOrEmptyField(userId)) {
				userId = getUserID();
			}
//			String userId ="USERID2";
			buinessUnitDescs = delegate.getBuisnessUnitsDescs(userId);	 
        	if(buinessUnitDescs!=null)
        	{   
	        	if(buinessUnitDescs.size()>1)
	        	{
	        		businessUnitDesc = ContactManagementConstants.AllOthers;	        		
	        	}
	        	else
	        	{
	        		businessUnitDesc = (String)buinessUnitDescs.get(0);
	        	}
	       }
        	else
        	{
         		businessUnitDesc = ContactManagementConstants.AllOthers;
        		
        	}	
        }
        catch(LOBHierarchyFetchBusinessException e){
        	log.debug(e);
        	log.error(e.getMessage(), e); 
        } 
        catch(Exception e){
       	   	log.debug(e);
        	log.error(e.getMessage(), e); 
        }
		/*if (entityType.equalsIgnoreCase("UP")) {
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
					FunctionalAreaConstants.CONTACT_MGMT));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
					FunctionalAreaConstants.GENERAL));
		} else*/ 
        /*if (entityType.equalsIgnoreCase("TC")) {
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
					FunctionalAreaConstants.CONTACT_MGMT));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
					FunctionalAreaConstants.TPL));*/
		/*} else if (entityType.equalsIgnoreCase("UM")) {
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
					FunctionalAreaConstants.CONTACT_MGMT));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD,
					FunctionalAreaConstants.GENERAL));*/			 
	    
		/*}else*/ /*if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))||(entityType.equalsIgnoreCase("UP"))
				||(entityType.equalsIgnoreCase("UM"))) {*/
			if(buinessUnitDescs!=null)
        	{
				for(int i=0;i<buinessUnitDescs.size();i++){
			     
       	        if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("ARS")){    	 	            
       	        	log.debug("--1----ARS-------------");
    	 	          if (entityType.equalsIgnoreCase("UM")) {
    	 	          	list.add(getInputCriteria(
    	 						ReferenceServiceDataConstants.G_ALERT_TY_CD_ARS,
    	 						FunctionalAreaConstants.GENERAL));
    	 	          	list.add(getInputCriteria(
    					        ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
    					        FunctionalAreaConstants.GENERAL)); 
    	 	          }else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
    				    list.add(getInputCriteria(
    							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
    							FunctionalAreaConstants.CONTACT_MGMT));
    				    list.add(getInputCriteria(
    					        ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
    					        FunctionalAreaConstants.GENERAL)); 
    					}else if(entityType.equalsIgnoreCase("UP")){
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
    								FunctionalAreaConstants.CONTACT_MGMT));
    						list.add(getInputCriteria(
    						        ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
    						        FunctionalAreaConstants.GENERAL)); 
    					}else if (entityType.equalsIgnoreCase("TC")) {
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
    								FunctionalAreaConstants.CONTACT_MGMT));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
    								FunctionalAreaConstants.TPL));
    					}else if (entityType.equalsIgnoreCase("TD")) {
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.W_TP_TY_CD,
    								FunctionalAreaConstants.EDI_TRADING));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
    								FunctionalAreaConstants.EDI_TRADING));
    					}
    					else{
    						list.add(getInputCriteria(
        					        ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
        					        FunctionalAreaConstants.GENERAL));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
    								FunctionalAreaConstants.CONTACT_MGMT));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
    								FunctionalAreaConstants.GENERAL));
    					}
    	         }
       	        if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("ProviderRelations")){
       	        	log.debug("--1----ProvRel-------------");
    	         	  if (entityType.equalsIgnoreCase("UM")) {
    	         	  	list.add(getInputCriteria(
    							ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
    							FunctionalAreaConstants.GENERAL));
    	 	          	list.add(getInputCriteria(
        						ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
        						FunctionalAreaConstants.GENERAL)); 
    	 	          }else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
    				    list.add(getInputCriteria(
    							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
    							FunctionalAreaConstants.CONTACT_MGMT));
    				    list.add(getInputCriteria(
        						ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
        						FunctionalAreaConstants.GENERAL)); 
    					}else if(entityType.equalsIgnoreCase("UP")){
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
    								FunctionalAreaConstants.CONTACT_MGMT));
    						list.add(getInputCriteria(
    	    						ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
    	    						FunctionalAreaConstants.GENERAL)); 
    					}else if (entityType.equalsIgnoreCase("TC")) {
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
    								FunctionalAreaConstants.CONTACT_MGMT));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
    								FunctionalAreaConstants.TPL));
    					}else if (entityType.equalsIgnoreCase("TD")) {
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.W_TP_TY_CD,
    								FunctionalAreaConstants.EDI_TRADING));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
    								FunctionalAreaConstants.EDI_TRADING));
    					}
    					else{
    						list.add(getInputCriteria(
            						ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
            						FunctionalAreaConstants.GENERAL));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
    								FunctionalAreaConstants.CONTACT_MGMT));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
    								FunctionalAreaConstants.GENERAL));
    					}
    	    	 }
       	        if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("Appeals")){       	         		 
       	        	log.debug("--1----Appeals-------------");
       	         	   if (entityType.equalsIgnoreCase("UM")) {
       	         	list.add(getInputCriteria(
							ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
							FunctionalAreaConstants.GENERAL));
    	 	          	list.add(getInputCriteria(
        						ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
        						FunctionalAreaConstants.GENERAL));
    	 	            }else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
    	 				    list.add(getInputCriteria(
    	 							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
    	 							FunctionalAreaConstants.CONTACT_MGMT));
    	 				   list.add(getInputCriteria(
        						ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
        						FunctionalAreaConstants.GENERAL));
    	 					}else if(entityType.equalsIgnoreCase("UP")){
    	 						list.add(getInputCriteria(
    	 								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
    	 								FunctionalAreaConstants.CONTACT_MGMT));
    	 						list.add(getInputCriteria(
    	 	    						ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
    	 	    						FunctionalAreaConstants.GENERAL));
    	 					}else if (entityType.equalsIgnoreCase("TC")) {
    	 						list.add(getInputCriteria(
    	 								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
    	 								FunctionalAreaConstants.CONTACT_MGMT));
    	 						list.add(getInputCriteria(
    	 								ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
    	 								FunctionalAreaConstants.TPL));
    	 					}else if (entityType.equalsIgnoreCase("TD")) {
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.W_TP_TY_CD,
        								FunctionalAreaConstants.EDI_TRADING));
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
        								FunctionalAreaConstants.EDI_TRADING));
        					}
    	 					else{
    	 						list.add(getInputCriteria(
    	 	    						ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
    	 	    						FunctionalAreaConstants.GENERAL));
    	 						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
        								FunctionalAreaConstants.CONTACT_MGMT));
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
        								FunctionalAreaConstants.GENERAL));
        					}
    	    	 }
       	        if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("MCS")){
       	        	log.debug("--1----MCS-------------");
    	    	 	  if (entityType.equalsIgnoreCase("UM")) {
    	    	 	  	list.add(getInputCriteria(
    							ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
    							FunctionalAreaConstants.GENERAL));
    	 	          	list.add(getInputCriteria(
        						ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
        						FunctionalAreaConstants.GENERAL)); 
    	 	          }else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
    				    list.add(getInputCriteria(
    							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
    							FunctionalAreaConstants.CONTACT_MGMT));
    				    list.add(getInputCriteria(
        						ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
        						FunctionalAreaConstants.GENERAL)); 
    					}else if(entityType.equalsIgnoreCase("UP")){
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
    								FunctionalAreaConstants.CONTACT_MGMT));
    						list.add(getInputCriteria(
    	    						ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
    	    						FunctionalAreaConstants.GENERAL)); 
    					}else if (entityType.equalsIgnoreCase("TD")) {
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.W_TP_TY_CD,
    								FunctionalAreaConstants.EDI_TRADING));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
    								FunctionalAreaConstants.EDI_TRADING));
    					}
    					else if (entityType.equalsIgnoreCase("TC")) {
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
    								FunctionalAreaConstants.CONTACT_MGMT));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
    								FunctionalAreaConstants.TPL));
    					}else{
    						list.add(getInputCriteria(
            						ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
            						FunctionalAreaConstants.GENERAL));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
    								FunctionalAreaConstants.CONTACT_MGMT));
    						list.add(getInputCriteria(
    								ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
    								FunctionalAreaConstants.GENERAL));
    					}
    	    	 	    
    	    	 }
       	        if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("DDU")){
       	        	log.debug("--1----DDU-------------");
    	    	 		if (entityType.equalsIgnoreCase("UM")) {
    	    	 			list.add(getInputCriteria(
        							ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
        							FunctionalAreaConstants.GENERAL));
        	 	          	list.add(getInputCriteria(
            						ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
            						FunctionalAreaConstants.GENERAL));
        	 	          }else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
        				    list.add(getInputCriteria(
        							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
        							FunctionalAreaConstants.CONTACT_MGMT));
        				    list.add(getInputCriteria(
            						ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
            						FunctionalAreaConstants.GENERAL));
        					}else if(entityType.equalsIgnoreCase("UP")){
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
        								FunctionalAreaConstants.CONTACT_MGMT));
        						list.add(getInputCriteria(
        	    						ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
        	    						FunctionalAreaConstants.GENERAL));
        					}else if (entityType.equalsIgnoreCase("TC")) {
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
        								FunctionalAreaConstants.CONTACT_MGMT));
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
        								FunctionalAreaConstants.TPL));
        					}else if (entityType.equalsIgnoreCase("TD")) {
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.W_TP_TY_CD,
        								FunctionalAreaConstants.EDI_TRADING));
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
        								FunctionalAreaConstants.EDI_TRADING));
        					}
        					else{
        						list.add(getInputCriteria(
                						ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
                						FunctionalAreaConstants.GENERAL)); 
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
        								FunctionalAreaConstants.CONTACT_MGMT));
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
        								FunctionalAreaConstants.GENERAL));
        					}
    	    	 }
       	        if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("BCCP")){
       	        	log.debug("--1----BCCP-------------");
    	    	 		if (entityType.equalsIgnoreCase("UM")) {
        	 	          	list.add(getInputCriteria(
        	 						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_BCCP,
        	 						FunctionalAreaConstants.GENERAL));
        	 	          	list.add(getInputCriteria(
            						ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
            						FunctionalAreaConstants.GENERAL)); 
        	 	          }else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
        				    list.add(getInputCriteria(
        							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
        							FunctionalAreaConstants.CONTACT_MGMT));
        				    list.add(getInputCriteria(
            						ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
            						FunctionalAreaConstants.GENERAL)); 
        					}else if(entityType.equalsIgnoreCase("UP")){
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
        								FunctionalAreaConstants.CONTACT_MGMT));
        						list.add(getInputCriteria(
        	    						ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
        	    						FunctionalAreaConstants.GENERAL)); 
        					}else if (entityType.equalsIgnoreCase("TC")) {
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
        								FunctionalAreaConstants.CONTACT_MGMT));
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
        								FunctionalAreaConstants.TPL));
        					}else if (entityType.equalsIgnoreCase("TD")) {
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.W_TP_TY_CD,
        								FunctionalAreaConstants.EDI_TRADING));
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
        								FunctionalAreaConstants.EDI_TRADING));
        					}
        					else{
        						list.add(getInputCriteria(
                						ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
                						FunctionalAreaConstants.GENERAL)); 
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
        								FunctionalAreaConstants.CONTACT_MGMT));
        						list.add(getInputCriteria(
        								ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
        								FunctionalAreaConstants.GENERAL));
        					}
    	    	 }
       	   //ADD FOR THE DEFECT ESPRD00628476

				 else if((!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("ARS"))&&
	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("ProviderRelations"))&&
	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("Appeals"))&&
	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("MCS"))&&
	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("DDU"))&&
	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("BCCP")))    
	    
			{
					 log.debug("global case search1");
	        	list.add(getInputCriteria(
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
						FunctionalAreaConstants.GENERAL));
				list.add(getInputCriteria(
						ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
						FunctionalAreaConstants.CONTACT_MGMT));
				list.add(getInputCriteria(
						ReferenceServiceDataConstants.G_CASE_STAT_CD,
						FunctionalAreaConstants.GENERAL));
				list.add(getInputCriteria(
						ReferenceServiceDataConstants.W_TP_TY_CD,
						FunctionalAreaConstants.EDI_TRADING));
				list.add(getInputCriteria(
						ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
						FunctionalAreaConstants.EDI_TRADING));
			}
			//END FOR THE DEFECT ESPRD00628476
       	       } 
        	 //}	
						
	    } else {
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
					FunctionalAreaConstants.CONTACT_MGMT));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CASE_STAT_CD,
					FunctionalAreaConstants.GENERAL));
			list.add(getInputCriteria(
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
					FunctionalAreaConstants.GENERAL));
		}
		referenceDataListVO = new ReferenceDataListVO();
		try {
			referenceDataSearch = new ReferenceDataSearchVO();
			referenceDataSearch.setInputList(list);
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);
		} catch (ReferenceServiceRequestException e) {
			log.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			log.error(e.getMessage(), e);
		}

		HashMap map = referenceDataListVO.getResponseMap();
		/*if (entityType.equalsIgnoreCase("UP")) {
			getGlobalCaseSearchDataBean().setEntityIDType(
					getValidData(map,
							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
							FunctionalAreaConstants.CONTACT_MGMT));
			getGlobalCaseSearchDataBean().setStatus(
					getValidData(map,
							ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
							FunctionalAreaConstants.GENERAL));

		} else*/ 
		/*if (entityType.equalsIgnoreCase("TC")) {
			getGlobalCaseSearchDataBean().setEntityIDType(
					getValidData(map,
							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
							FunctionalAreaConstants.CONTACT_MGMT));
					getGlobalCaseSearchDataBean().setStatus(
					getValidData(map,
							ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
							FunctionalAreaConstants.TPL));*/
		/*} else if (entityType.equalsIgnoreCase("UM")) {
			getGlobalCaseSearchDataBean().setEntityIDType(
					getValidData(map,
							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
							FunctionalAreaConstants.CONTACT_MGMT));
			getGlobalCaseSearchDataBean().setStatus(
					getValidData(map,
							ReferenceServiceDataConstants.G_CASE_STAT_CD,
							FunctionalAreaConstants.GENERAL));*/
		/*}else *//*if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))||(entityType.equalsIgnoreCase("UP"))
				||(entityType.equalsIgnoreCase("UM"))) {*/
			if(buinessUnitDescs!=null)
      	    {
				 for(int i=0;i<buinessUnitDescs.size();i++){		     
			   
			     if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("ARS")){
			    	 log.debug("--2----ARS-------------");
			    	if (entityType.equalsIgnoreCase("UM")) {
			    		getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.G_ALERT_TY_CD_ARS,
										FunctionalAreaConstants.GENERAL));
			    		getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
												FunctionalAreaConstants.GENERAL));
			    	}else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
						  getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
										FunctionalAreaConstants.CONTACT_MGMT));
						  getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
												FunctionalAreaConstants.GENERAL));
							  }else if(entityType.equalsIgnoreCase("UP")){
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
												FunctionalAreaConstants.CONTACT_MGMT));	
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
														FunctionalAreaConstants.GENERAL));
							  }else if (entityType.equalsIgnoreCase("TC")) {
								getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
												FunctionalAreaConstants.CONTACT_MGMT));
										getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,
												ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
												FunctionalAreaConstants.TPL));
							  }else if (entityType.equalsIgnoreCase("TD")) {
									getGlobalCaseSearchDataBean().setTPStatusList(
											getValidData(map,
													ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
													FunctionalAreaConstants.EDI_TRADING));
											getGlobalCaseSearchDataBean().setClassficationList(
											getValidData(map,
													ReferenceServiceDataConstants.W_TP_TY_CD,
													FunctionalAreaConstants.EDI_TRADING));
								  }
							  else{
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_ARS,
														FunctionalAreaConstants.GENERAL));
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
												FunctionalAreaConstants.CONTACT_MGMT));
							  	getGlobalCaseSearchDataBean().setEntityType(
										getValidData(map,
												ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
												FunctionalAreaConstants.GENERAL));
							  }
			    }
			     if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("ProviderRelations")){
			    	 log.debug("--2----ProvRel-------------");
			    	if (entityType.equalsIgnoreCase("UM")) {
			    		getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
										FunctionalAreaConstants.GENERAL));
			    		getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
												FunctionalAreaConstants.GENERAL));
			    	}else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
						  getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
										FunctionalAreaConstants.CONTACT_MGMT));
						  getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
												FunctionalAreaConstants.GENERAL));
							  }else if(entityType.equalsIgnoreCase("UP")){
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
												FunctionalAreaConstants.CONTACT_MGMT));
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
														FunctionalAreaConstants.GENERAL));
							  }else if (entityType.equalsIgnoreCase("TC")) {
								getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
												FunctionalAreaConstants.CONTACT_MGMT));
										getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,
												ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
												FunctionalAreaConstants.TPL));
							  }else if (entityType.equalsIgnoreCase("TD")) {
									getGlobalCaseSearchDataBean().setTPStatusList(
											getValidData(map,
													ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
													FunctionalAreaConstants.EDI_TRADING));
											getGlobalCaseSearchDataBean().setClassficationList(
											getValidData(map,
													ReferenceServiceDataConstants.W_TP_TY_CD,
													FunctionalAreaConstants.EDI_TRADING));
								  }
							  else{
							  	 getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_PROV_REL,
														FunctionalAreaConstants.GENERAL));
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
												FunctionalAreaConstants.CONTACT_MGMT));
							  	getGlobalCaseSearchDataBean().setEntityType(
										getValidData(map,
												ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
												FunctionalAreaConstants.GENERAL));
							  }
			    }
			     if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("Appeals")){			     	
			    	 log.debug("--2----Appeals-------------");
			     	if (entityType.equalsIgnoreCase("UM")) {
			     		getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
										FunctionalAreaConstants.GENERAL));
			    		getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
												FunctionalAreaConstants.GENERAL));
			    	}else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
						  getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
										FunctionalAreaConstants.CONTACT_MGMT));
						  getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
												FunctionalAreaConstants.GENERAL));
							  }else if(entityType.equalsIgnoreCase("UP")){
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
												FunctionalAreaConstants.CONTACT_MGMT));	
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
														FunctionalAreaConstants.GENERAL));
							  }else if (entityType.equalsIgnoreCase("TC")) {
								getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
												FunctionalAreaConstants.CONTACT_MGMT));
										getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,
												ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
												FunctionalAreaConstants.TPL));
							  }else if (entityType.equalsIgnoreCase("TD")) {
									getGlobalCaseSearchDataBean().setTPStatusList(
											getValidData(map,
													ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
													FunctionalAreaConstants.EDI_TRADING));
											getGlobalCaseSearchDataBean().setClassficationList(
											getValidData(map,
													ReferenceServiceDataConstants.W_TP_TY_CD,
													FunctionalAreaConstants.EDI_TRADING));
								  }
							  else{
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_APEL,
														FunctionalAreaConstants.GENERAL));
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
												FunctionalAreaConstants.CONTACT_MGMT));
							  	getGlobalCaseSearchDataBean().setEntityType(
										getValidData(map,
												ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
												FunctionalAreaConstants.GENERAL));
							  }
			     	
			    }
			     if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("MCS")){
			    	 log.debug("--2----MCS-------------");
			    	if (entityType.equalsIgnoreCase("UM")) {
			    		getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
										FunctionalAreaConstants.GENERAL));
			    		getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
												FunctionalAreaConstants.GENERAL));
			    	}else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
						  getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
										FunctionalAreaConstants.CONTACT_MGMT));
						  getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
												FunctionalAreaConstants.GENERAL));
							  }else if(entityType.equalsIgnoreCase("UP")){
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
												FunctionalAreaConstants.CONTACT_MGMT));
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
														FunctionalAreaConstants.GENERAL));
							  }else if (entityType.equalsIgnoreCase("TC")) {
								getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
												FunctionalAreaConstants.CONTACT_MGMT));
										getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,
												ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
												FunctionalAreaConstants.TPL));
							  }else if (entityType.equalsIgnoreCase("TD")) {
									getGlobalCaseSearchDataBean().setTPStatusList(
											getValidData(map,
													ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
													FunctionalAreaConstants.EDI_TRADING));
											getGlobalCaseSearchDataBean().setClassficationList(
											getValidData(map,
													ReferenceServiceDataConstants.W_TP_TY_CD,
													FunctionalAreaConstants.EDI_TRADING));
								  }
							  else{
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_MCS,
														FunctionalAreaConstants.GENERAL));
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
												FunctionalAreaConstants.CONTACT_MGMT));
							  	getGlobalCaseSearchDataBean().setEntityType(
										getValidData(map,
												ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
												FunctionalAreaConstants.GENERAL));
							  }
			    }
			     if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("DDU")){
			    	 log.debug("--2----DDU-------------");
			    	if (entityType.equalsIgnoreCase("UM")) {
			    		getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_DDU,
										FunctionalAreaConstants.GENERAL));
			    		getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
												FunctionalAreaConstants.GENERAL));
			    	}else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
						  getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
										FunctionalAreaConstants.CONTACT_MGMT));
						  getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
												FunctionalAreaConstants.GENERAL));
							  }else if(entityType.equalsIgnoreCase("UP")){
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
												FunctionalAreaConstants.CONTACT_MGMT));	
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
														FunctionalAreaConstants.GENERAL));
							  }else if (entityType.equalsIgnoreCase("TC")) {
								getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
												FunctionalAreaConstants.CONTACT_MGMT));
										getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,
												ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
												FunctionalAreaConstants.TPL));
							  }else if (entityType.equalsIgnoreCase("TD")) {
									getGlobalCaseSearchDataBean().setTPStatusList(
											getValidData(map,
													ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
													FunctionalAreaConstants.EDI_TRADING));
											getGlobalCaseSearchDataBean().setClassficationList(
											getValidData(map,
													ReferenceServiceDataConstants.W_TP_TY_CD,
													FunctionalAreaConstants.EDI_TRADING));
								  }
							  else{
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_DDU,
														FunctionalAreaConstants.GENERAL));
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
												FunctionalAreaConstants.CONTACT_MGMT));
							  	getGlobalCaseSearchDataBean().setEntityType(
										getValidData(map,
												ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
												FunctionalAreaConstants.GENERAL));
							  }
			    	  
			    }
			     if(((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("BCCP")){
			    	 log.debug("--2----BCCP-------------");
			    	if (entityType.equalsIgnoreCase("UM")) {
			    		getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_BCCP,
										FunctionalAreaConstants.GENERAL));
			    		getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
												FunctionalAreaConstants.GENERAL));
			    	}else if (entityType.equalsIgnoreCase("P")||(entityType.equalsIgnoreCase("M"))){
						  getGlobalCaseSearchDataBean().setEntityIDType(
								getValidData(map,
										ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
										FunctionalAreaConstants.CONTACT_MGMT));
						  getGlobalCaseSearchDataBean().setStatus(
								getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
												FunctionalAreaConstants.GENERAL));
							  }else if(entityType.equalsIgnoreCase("UP")){
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0086,
												FunctionalAreaConstants.CONTACT_MGMT));	
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
														FunctionalAreaConstants.GENERAL));
							  }else if (entityType.equalsIgnoreCase("TC")) {
								getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0085,
												FunctionalAreaConstants.CONTACT_MGMT));
										getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,
												ReferenceServiceDataConstants.C8_OTHER_SBS_INSR_TY_CD,
												FunctionalAreaConstants.TPL));
							  }else if (entityType.equalsIgnoreCase("TD")) {
									getGlobalCaseSearchDataBean().setTPStatusList(
											getValidData(map,
													ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
													FunctionalAreaConstants.EDI_TRADING));
											getGlobalCaseSearchDataBean().setClassficationList(
											getValidData(map,
													ReferenceServiceDataConstants.W_TP_TY_CD,
													FunctionalAreaConstants.EDI_TRADING));
								  }
							  else{
							  	getGlobalCaseSearchDataBean().setStatus(
										getValidData(map,ReferenceServiceDataConstants.G_CASE_STAT_CD_BCCP,
														FunctionalAreaConstants.GENERAL));
							  	getGlobalCaseSearchDataBean().setEntityIDType(
										getValidData(map,
												ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
												FunctionalAreaConstants.CONTACT_MGMT));
							  	getGlobalCaseSearchDataBean().setEntityType(
										getValidData(map,
												ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
												FunctionalAreaConstants.GENERAL));
							  }
			    }
			     //ADD FOR THE DEFECT ESPRD00628476
			     else if((!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("ARS"))&&
			    	       	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("ProviderRelations"))&&
			    	       	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("Appeals"))&&
			    	       	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("MCS"))&&
			    	       	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("DDU"))&&
			    	       	        		(!((String)(buinessUnitDescs.get(i))).equalsIgnoreCase("BCCP")))    
			    	       	    
			    					{
			    	                 log.debug("global case search2");
			    			    	 getGlobalCaseSearchDataBean().setEntityIDType(
			    								getValidData(map,
			    										ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
			    										FunctionalAreaConstants.CONTACT_MGMT));
			    						getGlobalCaseSearchDataBean().setEntityType(
			    								getValidData(map,
			    										ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
			    										FunctionalAreaConstants.GENERAL));
			    						getGlobalCaseSearchDataBean().setStatus(
			    								getValidData(map,
			    										ReferenceServiceDataConstants.G_CASE_STAT_CD,
			    										FunctionalAreaConstants.GENERAL));
			    						getGlobalCaseSearchDataBean().setTPStatusList(
												getValidData(map,
														ReferenceServiceDataConstants.W_APPRVL_PRCS_STAT_CD_0003,
														FunctionalAreaConstants.EDI_TRADING));
												getGlobalCaseSearchDataBean().setClassficationList(
												getValidData(map,
														ReferenceServiceDataConstants.W_TP_TY_CD,
														FunctionalAreaConstants.EDI_TRADING));
			    					}
			    					//END FOR THE DEFECT ESPRD00628476
			  }
      	 //}	
			  		
		} else {
			getGlobalCaseSearchDataBean().setEntityIDType(
					getValidData(map,
							ReferenceServiceDataConstants.B_ALT_ID_TY_CD_0083,
							FunctionalAreaConstants.CONTACT_MGMT));
			getGlobalCaseSearchDataBean().setStatus(
					getValidData(map,
							ReferenceServiceDataConstants.G_CASE_STAT_CD,
							FunctionalAreaConstants.GENERAL));
			getGlobalCaseSearchDataBean().setEntityType(
					getValidData(map,
							ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD_ALL,
							FunctionalAreaConstants.GENERAL));
		}
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "loadAllValiedValues");
	}

	private InputCriteria getInputCriteria(String referenceDataConstant,
			String functionalArea) {
		InputCriteria inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(functionalArea);
		inputCriteria.setElementName(referenceDataConstant);
		return inputCriteria;
	}

	private List getValidData(Map map, String referenceDataConstant,
			String functionalArea) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "getValidData");
		int validValuesSize = 0;
		List validValues = null;
		List validList = null;
		if (map != null) {
			validValues = (List) map.get(functionalArea + "#"
					+ referenceDataConstant);
			validList = new ArrayList();
			validList.add(new SelectItem("", ""));
		}
		if (validValues != null)
			validValuesSize = validValues.size();
		for (int i = 0; i < validValuesSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) validValues.get(i);
			String validValueCodeDesc = refVo.getValidValueCode() + "-"
					+ refVo.getShortDescription();

			validList.add(new SelectItem(refVo.getValidValueCode(),
					validValueCodeDesc));
		}
		log.info(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "getValidData");
		return validList;
	}

	public String setShortDescription(String functionalArea,
			String elementName, String value) {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "setShortDescription");
		String validvalueDesc = null;
		try {
			validvalueDesc = ReferenceServiceDelegate
					.getReferenceSearchShortDescription(functionalArea,
							elementName, null, value);
		} catch (Throwable throwable) {
			log.error(throwable.getMessage(), throwable);
			}
		if (validvalueDesc == null || validvalueDesc.equalsIgnoreCase("")) {
			validvalueDesc = value;
		}
		return validvalueDesc;
	}

	public String getLoadValidValues() {

		loadAllValiedValues(MaintainContactManagementUIConstants.EMPTY_STRING);
		getUsersList();
		getGlobalCaseSearchDataBean().setValidValuesFlag(false);
		return null;
	}

	public void getUsersList() {
		log.info(ContactManagementConstants.BEGINMETHOD_GlobalCaseSearchControllerBean + "getUsersList");
		//Commented for GAI-CMGT_CASE-EnterpriseUser_Fix
		//EnterpriseUser enterpriseUser = null;
		List userData = null;
		String desc = null;
		//Commented for GAI-CMGT_CASE-EnterpriseUser_Fix
		/*try {
			userData = caseDelegate.getAllUsers();
			if (!userData.isEmpty()) {
				getGlobalCaseSearchDataBean()
						.setCreatedBy(new ArrayList());
				getGlobalCaseSearchDataBean()
						.setAssignedTo(new ArrayList());
				getGlobalCaseSearchDataBean().getAssignedTo().add(
						new SelectItem("", ""));
				getGlobalCaseSearchDataBean().getCreatedBy().add(
						new SelectItem("", ""));
				int size = userData.size();
				for (int i = 0; i < size; i++) {
					enterpriseUser = (EnterpriseUser) userData.get(i);
					if (enterpriseUser.getFirstName() != null) {
						
						desc = enterpriseUser.getFirstName() + " "
								+ enterpriseUser.getLastName();
						// Defect ESPRD00671340 Start
						desc =  enterpriseUser.getLastName()+ " " +enterpriseUser.getFirstName() + " - "
						        + enterpriseUser.getUserID();
						// Defect ESPRD00671340 Ends
						getGlobalCaseSearchDataBean().getAssignedTo().add(
								new SelectItem(enterpriseUser
										.getUserWorkUnitSK().toString(), desc));
						getGlobalCaseSearchDataBean().getCreatedBy().add(
								new SelectItem(enterpriseUser
										.getUserWorkUnitSK().toString(), desc));
					}
				}
			}
		}

		catch (CaseRecordFetchBusinessException e) {
			log.error(e.getMessage(), e);
		}*/
		// Added for GAI-CMGT_CASE-EnterpriseUser_Fix
		Map temp1 = new HashMap();
		Map temp2 = new HashMap();
		List departmentList = new ArrayList();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try {
			DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
			deptUserBasicInfo.setDataReqested(ProgramConstants.UserId_FirstName_LastName_WorkUnitId);
			userData = contactMaintenanceDelegate.getEnterpriseUserDetails(deptUserBasicInfo);
			if (!userData.isEmpty()) {
				getGlobalCaseSearchDataBean()
						.setCreatedBy(new ArrayList());
				getGlobalCaseSearchDataBean()
						.setAssignedTo(new ArrayList());
				getGlobalCaseSearchDataBean().getAssignedTo().add(
						new SelectItem("", ""));
				getGlobalCaseSearchDataBean().getCreatedBy().add(
						new SelectItem("", ""));
				int size = userData.size();
				for (int i = 0; i < size; i++) {
					deptUserBasicInfo = (DeptUserBasicInfo) userData.get(i);
					if (deptUserBasicInfo.getFirstName() != null) {
						/*desc = enterpriseUser.getFirstName() + " "
								+ enterpriseUser.getLastName();*/
						// Defect ESPRD00671340 Start
						desc =  deptUserBasicInfo.getLastName()+ ContactManagementConstants.COMMA_SEPARATOR
				        +deptUserBasicInfo.getFirstName() + ContactManagementConstants.HYPHEN_SEPARATOR
				        + deptUserBasicInfo.getUserID();
						// Defect ESPRD00671340 Ends
						getGlobalCaseSearchDataBean().getAssignedTo().add(
								new SelectItem(deptUserBasicInfo
										.getUserWorkUnitSK().toString(), desc));
						getGlobalCaseSearchDataBean().getCreatedBy().add(
								new SelectItem(deptUserBasicInfo
										.getUserWorkUnitSK().toString(), desc));
						temp1.put(deptUserBasicInfo.getUserID(), desc);
						temp2.put(deptUserBasicInfo.getUserWorkUnitSK(), desc);
					}
				}
				globalGlobalCaseSearchDataBean.setUserIdNameMap(temp1);
				globalGlobalCaseSearchDataBean.setUserSkNameMap(temp2);
			}
		}
    	catch (EnterpriseBaseDataException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
    	try {
			List dbDeptList = contactMaintenanceDelegate
					.getAllDepartmentUserNames(ContactManagementConstants.EMPTY_STRING);
			if (dbDeptList != null && !dbDeptList.isEmpty()) {
				log.debug("dbDeptList.size()"+ dbDeptList.size());
				for (int i = 0; i < dbDeptList.size(); i++) {
					Object[] departMentObj = (Object[]) dbDeptList.get(i);
					if (departMentObj[1] == null) {
						departMentObj[1] = ContactManagementConstants.EMPTY_STRING;
					}
					departmentList.add(departMentObj[1]);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		log.debug("departmentList.size()" + departmentList.size());
		globalGlobalCaseSearchDataBean.setDepartmentList(departmentList);
		log.debug(ContactManagementConstants.ENDMETHOD_GlobalCaseSearchControllerBean + "getUsersList");
	}
	protected boolean isNullOrEmptyField(String inputField)
    {
        return (inputField == null || ContactManagementConstants.EMPTY_STRING
                .equalsIgnoreCase(inputField.trim()));
    }
	
//	ESPRD00429942_UC-PGM-CRM-060_26MAY2010
	private String helpFlagSettings = null;
	/**
	 * @return Returns the helpFlagSettings.
	 */
	public String getHelpFlagSettings() {
		try {
			PortletRequest request = (PortletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			request.getPortletSession().setAttribute(
					"MAINTAINANCE_CURRENT_PAGE",
					"/jsp/globalCaseSearch/globalCaseSearch.jsp",
					PortletSession.APPLICATION_SCOPE);
		} catch (Exception exception) {
			log.error(exception.getMessage(), exception);
		}
		try {
			ActionResponse response = (ActionResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.setRenderParameter("javax.servlet.include.path_info","/jsp/globalCaseSearch/globalCaseSearch.jsp");
			
		} catch (Exception exception) {
			log.error(exception.getMessage(), exception);
		}
		return helpFlagSettings;
	}
	/**
	 * @param helpFlagSettings The helpFlagSettings to set.
	 */
	public void setHelpFlagSettings(String helpFlagSettings) {
		this.helpFlagSettings = helpFlagSettings;
	}
	//EOF ESPRD00429942_UC-PGM-CRM-060_26MAY2010
	
	
	/**
	 * This method is used to get the logged in User ID.
	 * 
	 * @return String : User ID
	 */
	public String getUserID() {
		if (log.isInfoEnabled())
			log.info("getUserID");
		String userId = "snofty";
		HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderResponse = null;
		EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
		if (eup != null && !isNullOrEmptyField(eup.getUserId())) {
			userId = eup.getUserId();
			globalGlobalCaseSearchDataBean.setUserId(userId);
		}
		if (log.isInfoEnabled())
			log.info("the useriD in controller is  :" + userId);
		return userId;
	}
}