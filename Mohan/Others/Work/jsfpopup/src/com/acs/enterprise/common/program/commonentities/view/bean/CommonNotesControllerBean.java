/*
 * Created on Mar 15, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.commonentities.view.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.benefitadministration.util.helper.BenefitPlanConstants;
import com.acs.enterprise.common.program.commonentities.application.exception.CommonNoteNotFoundException;
import com.acs.enterprise.common.program.commonentities.common.delegate.CommonEntityDelegate;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesSearchCriteriaVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CMEntitySearchDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CorrespondenceDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.config.ConfigurationLoader;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This controllerbean is used to manage CommonNotes Information.
 */
public class CommonNotesControllerBean extends EnterpriseBaseControllerBean {
	public static final String BEAN_NAME = ContactManagementConstants.CORRESPONDENCE_BEAN_NAME;

	/**
	 * This is hold the firstRowIndexvalue
	 */
	//	private HtmlInputHidden firstRowIndexHidden;

	//for heap dump issue
	private int startIndexForCmnNotes = 0;

	/**
	 * This is to hold commonEntityDataBean
	 */

	private CommonEntityDataBean commonEntityDataBean = ContactHelper
			.getCommonEntityDataBean();

	/**
	 * This is to hold controlRequired
	 */
	private boolean controlRequired = true;

	/**
	 * This field is used to store securityfield.
	 */
	private String securityfield = "";

	/**
	 * @return Returns the startIndexForCmnNotes.
	 */
	public int getStartIndexForCmnNotes() {
		return startIndexForCmnNotes;
	}

	/**
	 * @param startIndexForCmnNotes
	 *            The startIndexForCmnNotes to set.
	 */
	public void setStartIndexForCmnNotes(int startIndexForCmnNotes) {
		this.startIndexForCmnNotes = startIndexForCmnNotes;
	}

	/**
	 * This is to hold the EnterpriseLogger value
	 */

	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CommonNotesControllerBean.class.getName());

	/**
	 * To get the List of Notes based on the Entity Id. This method would list
	 * all the case and correspondence notes associated with a particular Entity
	 * Id.
	 */
	public void getGlobalNotes() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = "0";
		int x = 0;
		if (map != null && map.size() > x) {
			indexCode = (String) map.get(BenefitPlanConstants.INDEX_CODE);
			logger.info("indexCode--------" + indexCode);
			if (indexCode != null && StringUtils.isNotEmpty(indexCode)) {
				getCmEntityDataBean().setItemSelectedRowIndex(
						Integer.parseInt(indexCode));
			}
		}
		logger.debug("entered into Get global notes");
		ContactHelper contactHelper = new ContactHelper();

		String entyId = ContactManagementConstants.EMPTY_STRING;
		String commonEntSKVal = "0";
		String entityName = ContactManagementConstants.EMPTY_STRING;
		String entityType = ContactManagementConstants.EMPTY_STRING;
		String noteSetSKVal = null;
		FacesContext context = FacesContext.getCurrentInstance();
		if (commonEntityDataBean.isFlag()) {

			HttpServletRequest req = (HttpServletRequest) context
					.getExternalContext().getRequest();
			logger.debug("\n\n*****Gettting entity type from request\t\t"
					+ req.getAttribute("entityType"));
			logger.debug("\n\n*****Gettting entity id from request\t\t"
					+ req.getAttribute("entityID"));

			entyId = (String) req.getAttribute("entityID");
			entityType = (String) req.getAttribute("entityType");
			entityName = (String) req.getAttribute("entityName");

			commonEntityDataBean.setFlag(false);
		} else {
			entyId = (String) map.get(ContactManagementConstants.IPC_ENTITYID);
			entityType = (String) map
					.get(ContactManagementConstants.GLOBAL_ENTITY_TYPE);
			entityName = (String) map
					.get(ContactManagementConstants.GLOBAL_ENTITY_NAME);
			if (entityType != null && !entityType.equals("")) {
				System.out.println(" inside If condition " + entityType);
			} else {
				entityType = commonEntityDataBean.getEntityTypeForNote();
				System.out.println(" inside If condition " + entityType);
			}

			if (entityType != null && !entityType.equals("")) {
				if (entityType.equalsIgnoreCase("TP")) {
					entyId = (String) map
							.get(ContactManagementConstants.GLOBALCOMMON_NOTES_COMMONENTSK);
				}
			}
		}
		logger.debug(" entityName==== " + entityName);
		logger.debug(" noteSetSKVal  " + noteSetSKVal);
		logger.debug(" entityType value " + entityType);

		if (entityType != null && !entityType.equals("")) {
			if (entityType.equalsIgnoreCase("UP")) {
				commonEntSKVal = (String) map
						.get(ContactManagementConstants.GLOBALCOMMON_NOTES_COMMONENTSK);
				noteSetSKVal = "0";
			}
		}
		if (commonEntSKVal == null) {
			commonEntSKVal = "0";
		}
		logger.debug("entityid valuecommonEntSKVal********" + commonEntSKVal);
		// Long commonSKValue = new Long(commonEntSKVal.trim()); //Find bug
		// issue
		List globalNotesList = new ArrayList();
		ArrayList globalLst = new ArrayList();
		List noteSetSKLst = new ArrayList();
		String businessDesc = "";
		try {
			logger.debug(" before commonEntityDelegate");
			CommonEntityDelegate commonEntityDelegate = new CommonEntityDelegate();
			logger.debug(" entity ID :: entityType  " + entyId + " :: "
					+ entityType);
			globalNotesList = commonEntityDelegate.getGlobalNotesList(entyId,
					entityType);
			//Added if Condition for Find_Bugs-FIX
			Iterator listItr = null;
			if(globalNotesList != null){
			logger.debug("globalNotesList size : " + globalNotesList.size());
			listItr = globalNotesList.iterator();
			}
			logger.debug(" after iterator globalNotesList " + globalNotesList);
			logger.debug(" after iterator listItr " + listItr);
			while (listItr.hasNext()) {
				logger.debug(" globalNotesList. NoteSet ");
				Object setObject = listItr.next();
				logger.debug(" setObject values " + setObject);
				if (setObject != null) {
					NoteSet ntSet = (NoteSet) setObject;

					/*
					 * if (ntSet.getNoteSetSK() != null &&
					 * !(ntSet.getNoteSetSK().equals(""))) {
					 * noteSetSKLst.add(ntSet.getNoteSetSK()); }
					 */

					if (ntSet.getNoteSetSK() != null) {
						noteSetSKLst.add(ntSet.getNoteSetSK());
					}
				}
				logger.debug(" end globalNotesList. NoteSet ");
			}
			logger.debug("end while loooooooop");
			Map businessDescMap = null;
			if (noteSetSKLst != null && noteSetSKLst.size() > 0) {
				businessDescMap = commonEntityDelegate
						.getBusinessDescMap(noteSetSKLst);
			}
			if (globalNotesList != null && globalNotesList.size() > 0) {

				Iterator iterator = globalNotesList.iterator();
				CommonNotesVO commonNotesVO = new CommonNotesVO();
				NoteSetVO noteSetVO = null;
				while (iterator.hasNext()) {
					NoteSet noteSet = (NoteSet) iterator.next();
					if (noteSet != null) {
						noteSetVO = contactHelper
								.convertNoteSetDomainToVO(noteSet);
						Long noteSetKey = null;
						//Added if Condition for Find_Bugs-FIX
						if(noteSetVO != null){
						noteSetKey = noteSetVO.getNoteSetSK();
						}
						commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
								.setNoteSetSK(noteSetKey);

						if (businessDescMap.get(noteSetKey) != null
								&& !(businessDescMap.get(noteSetKey).equals(""))) {
							businessDesc = (String) businessDescMap
									.get(noteSetKey);
							noteSetVO.setNoteSourceName(businessDesc);
						}
						if (noteSetVO != null) {
							logger.info("noteSetVO is not null");
							Iterator listiter = noteSetVO.getNotesList()
									.iterator();
							while (listiter.hasNext()) {
								commonNotesVO = (CommonNotesVO) listiter.next();
								commonNotesVO.setTableName(noteSetVO
										.getNoteSourceName());

								String entityTypeModStr = getDescriptionFromVV(
										entityType, getCmEntityDataBean()
												.getEntityDropDownList());
								commonNotesVO
										.setCommonEntityTypeCode(entityTypeModStr);

								commonNotesVO.setCommonEntityName(entityName);
								commonNotesVO.setEntityId(entyId);
								globalLst.add(commonNotesVO);
							}
						}
					}
				}
			}
		}

		catch (CommonNoteNotFoundException e) {
			logger
					.error("Error in the Get Global Notes Of Notes ControllerBean :"
							+ e.getMessage());
		}
		if (globalLst != null && globalLst.size() > 0) {
			sortListByDate(globalLst);
		}
		commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setNotesList(
				globalLst);
		commonEntityDataBean.setFilterNotesList(new ArrayList(globalLst));
		showNotes();
		commonEntityDataBean.setGlobalNotesRender(true);
		commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
				.setAddNewLinkRender(false);
		commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
				.setPrintLinkRender(true);
		commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setCheckAll(
				false);

		if (globalLst.size() > 0) {
			commonEntityDataBean.setCurrentNoteRender(true);
			//Modified for DEFECT ESPRD00794002 for properly display of row Count in Global Notes Page
			commonEntityDataBean.setRenderedreccordsnum(true);
			
		} else {
			commonEntityDataBean.setCurrentNoteRender(false);
			//Modified for DEFECT ESPRD00794002 for properly display of row Count in Global Notes Page
			commonEntityDataBean.setRenderedreccordsnum(false);
			
		}

		// Added for the defect ESPRD00542028

		commonEntityDataBean.setStartRecord(1);
		commonEntityDataBean.setCount(globalLst.size());
	    commonEntityDataBean.setEndRecord(globalLst.size());
	}

	/**
	 * To get the Notes based on the filter infrmation entered by the user.
	 */
	public void getGlobalFilteredNotes() {
		ArrayList notesList = new ArrayList();
		ArrayList filterNotesLst = new ArrayList();
		notesList = commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
				.getNotesList();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				ProgramConstants.DATE_FORMAT, Locale.getDefault());
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
		boolean errFlg = false;
		String beginDate = commonEntityDataBean.getCommonNoteSearchVO()
				.getStrBeginDate();
		String endDate = commonEntityDataBean.getCommonNoteSearchVO()
				.getStrEndDate();

		String usageTypeCode = commonEntityDataBean.getCommonNoteSearchVO()
				.getUsageTypeCode();
		String userId = commonEntityDataBean.getCommonNoteSearchVO()
				.getUserId();
		int x = 0;
		if (beginDate != null && beginDate.trim().length() > x) {
			try {
				beginDate = commonEntityValidator.getValidateDate(beginDate);
				commonEntityDataBean.getCommonNoteSearchVO().setStrBeginDate(
						beginDate);
				dateFormat.setLenient(false);
				dateFormat.parse(beginDate);
			} catch (ParseException e1) {
				logger.debug("globalnotes begindate error, parsing error ");
				commonEntityValidator.setErrorMessage(
						ProgramConstants.NOTES_BEGINDATE_INVALID,
						new Object[] {},
						ProgramConstants.COMMON_NOTES_PROPERTIES, null);
			}
		}
		if (endDate != null && endDate.trim().length() > x) {
			try {
				endDate = commonEntityValidator.getValidateDate(endDate);
				commonEntityDataBean.getCommonNoteSearchVO().setStrEndDate(
						endDate);
				dateFormat.setLenient(false);
				dateFormat.parse(endDate);
			} catch (ParseException e1) {
				logger.debug("end date exception, parsing error ");
				commonEntityValidator.setErrorMessage(
						ProgramConstants.NOTES_ENDDATE_INVALID,
						new Object[] {},
						ProgramConstants.COMMON_NOTES_PROPERTIES, null);
			}
		}
		/*
		 * if (beginDate == null || beginDate.trim().length() == x) {
		 * setErrorMessage(ProgramConstants.FILTER_BEGIN_DATE_NOT_NULL); errFlg =
		 * true; }
		 * 
		 * if (endDate == null || endDate.trim().length() == x) {
		 * setErrorMessage(ProgramConstants.FILTER_END_DATE_NOT_NULL); errFlg =
		 * true; }
		 */

		if (((beginDate != null && beginDate.trim().length() > x) && (endDate != null && endDate
				.trim().length() > x))
				&& (ContactHelper.dateConverter(endDate).compareTo(
						ContactHelper.dateConverter(beginDate)) < x)) {
			setErrorMessage(ProgramConstants.NOTES_END_DATE_LESSTHAN_BEGINDATE);
			errFlg = true;
		}
		if (!errFlg) {
			Iterator glbListiter = notesList.iterator();
			while (glbListiter.hasNext()) {
				CommonNotesVO cmnNoteVO = (CommonNotesVO) glbListiter.next();
				String usgTyp = cmnNoteVO.getUsageTypeCode();
				String uId = cmnNoteVO.getUserId();
				String stDate = cmnNoteVO.getStrBeginDate();
				if (((beginDate != null && beginDate.trim().length() > x) && (endDate != null && endDate
						.trim().length() > x))
						&& (ContactHelper.dateConverter(stDate).compareTo(
								ContactHelper.dateConverter(beginDate)) > x)
						&& (ContactHelper.dateConverter(stDate).compareTo(
								ContactHelper.dateConverter(endDate)) < x)
						|| (ContactHelper.dateConverter(stDate).compareTo(
								ContactHelper.dateConverter(beginDate)) == x)
						|| (ContactHelper.dateConverter(stDate).compareTo(
								ContactHelper.dateConverter(endDate)) == x)) {
					if (usgTyp.equalsIgnoreCase(usageTypeCode)
							|| uId.equalsIgnoreCase(userId)) {
						filterNotesLst.add(cmnNoteVO);
					}
				}
			}
		}
		commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setNotesList(
				filterNotesLst);
		commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
				.setGlobalNotesList(notesList);
		commonEntityDataBean.setGlobalDataRender(true);
		showNotes();
	}

	/**
	 * To get the Notes based on the filter infrmation entered by the user.
	 */

	public void getCRFilterNotes() {

		logger.debug("getfiltered notes......1");
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				ProgramConstants.DATE_FORMAT, Locale.getDefault());
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
		commonEntityDataBean
				.setNoteList(commonEntityDataBean.getTempNoteList());

		commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
				.setFilterbeginDate(new Date());

		boolean errFlg = false;
		String beginDate = commonEntityDataBean.getCommonNoteSearchVO()
				.getStrBeginDate();
		logger.info("beginDate=======" + beginDate);
		String endDate = commonEntityDataBean.getCommonNoteSearchVO()
				.getStrEndDate();
		logger.info("endDate=======" + endDate);
		String usageTypeCode = commonEntityDataBean.getCommonNoteSearchVO()
				.getUsageTypeCode();
		logger.info("usageTypeCode=======" + usageTypeCode);
		String userId = commonEntityDataBean.getCommonNoteSearchVO()
				.getUserId();
		logger.info("userId=======" + userId);
		logger.debug("getfiltered notes......2");
		int x = 0;
		if (beginDate != null && beginDate.trim().length() > x) {
			try {
				beginDate = commonEntityValidator.getValidateDate(beginDate);
				commonEntityDataBean.getCommonNoteSearchVO().setStrBeginDate(
						beginDate);
				dateFormat.setLenient(false);
				dateFormat.parse(beginDate);
			} catch (ParseException e1) {
				logger.debug("get filtered notes exception, parsing error ");
				commonEntityValidator.setErrorMessage(
						ProgramConstants.NOTES_BEGINDATE_INVALID,
						new Object[] {},
						ProgramConstants.COMMON_NOTES_PROPERTIES,
						"filterBeginDt");
				errFlg = true;
			}
		}
		logger.debug("getfiltered notes......3");

		if (endDate != null && endDate.trim().length() > x) {
			try {
				endDate = commonEntityValidator.getValidateDate(endDate);
				commonEntityDataBean.getCommonNoteSearchVO().setStrEndDate(
						endDate);
				dateFormat.setLenient(false);
				dateFormat.parse(endDate);
			} catch (ParseException e1) {
				logger.debug("in validating dates, parsing error ");
				commonEntityValidator
						.setErrorMessage(
								ProgramConstants.NOTES_ENDDATE_INVALID,
								new Object[] {},
								ProgramConstants.COMMON_NOTES_PROPERTIES,
								"filterEndDt");
				errFlg = true;
			}
		}

		if (((beginDate != null && beginDate.trim().length() > x) && (endDate != null && endDate
				.trim().length() > x))
				&& (ContactHelper.dateConverter(endDate).compareTo(
						ContactHelper.dateConverter(beginDate)) < x) && !errFlg) {
			//	setErrorMessage(ProgramConstants.NOTES_END_DATE_LESSTHAN_BEGINDATE);
			commonEntityValidator.setErrorMessage(
					ProgramConstants.END_DATE_LESSTHAN_BEGINDATE,
					new Object[] {},
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
					"filterEndDt");
			errFlg = true;
		}

		if (!errFlg) {
			NoteSet noteSet = null;
			List notesList = commonEntityDataBean.getNoteList();
			ArrayList filteredNotesList = new ArrayList();
			if (notesList != null && notesList.size() > 0) {
				Iterator itr = notesList.iterator();
				while (itr.hasNext()) {
					CommonNotesVO commonNotesVO = (CommonNotesVO) itr.next();
					boolean filtered = true;
					if (beginDate != null && beginDate.trim() != null
							&& !beginDate.trim().equalsIgnoreCase("")
							&& endDate != null && endDate.trim() != null
							&& !endDate.trim().equalsIgnoreCase("")) {
						logger.info("both the dates are not null");
						Date fromDate = ContactHelper.dateConverter(beginDate);
						Date toDate = ContactHelper.dateConverter(endDate);
						Date strBeginDate = ContactHelper
								.dateConverter(commonNotesVO.getStrBeginDate());
						logger.info("strBeginDate=======" + strBeginDate);
						if (strBeginDate.before(fromDate)
								|| strBeginDate.after(toDate)) {
							filtered = false;
							logger.info("1=========" + filtered);
						}
					} else if (beginDate != null && beginDate.trim() != null
							&& !beginDate.trim().equalsIgnoreCase("")) {
						logger.info("begin date is not null");
						Date fromDate = ContactHelper.dateConverter(beginDate);
						Date strBeginDate = ContactHelper
								.dateConverter(commonNotesVO.getStrBeginDate());
						if (strBeginDate.before(fromDate)) {
							filtered = false;
							logger.info("2=========" + filtered);
						}
					} else if (endDate != null && endDate.trim() != null
							&& !endDate.trim().equalsIgnoreCase("")) {
						logger.info("end date is not null");
						Date toDate = ContactHelper.dateConverter(endDate);
						Date strBeginDate = ContactHelper
								.dateConverter(commonNotesVO.getStrBeginDate());
						if (strBeginDate.after(toDate)) {
							filtered = false;
							logger.info("3=========" + filtered);
						}
					}
					if (usageTypeCode != null && usageTypeCode.trim() != null
							&& !usageTypeCode.trim().equalsIgnoreCase("")) {
						if (!usageTypeCode.equalsIgnoreCase(commonNotesVO
								.getUsageTypeCode())) {
							filtered = false;
							logger.info("4=========" + filtered);
						}
					}
					if (userId != null && userId.trim() != null
							&& !userId.trim().equalsIgnoreCase("")) {
						if (!userId.equalsIgnoreCase(commonNotesVO.getUserId())) {
							filtered = false;
							logger.info("5=========" + filtered);
						}
					}
					if (filtered) {
						filteredNotesList.add(commonNotesVO);
					}
				}
				commonEntityDataBean.setNoteList(filteredNotesList);
				if (filteredNotesList != null && filteredNotesList.size() > 0) {
					CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
							.getNoteList().get(0);
					commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
							.setCurrentNote(commonNoteVO.getNoteText());
					commonEntityDataBean.setCurrentNoteRender(true);
					
					//Added for the defect - ESPRD00846619 to display the proper search result grid with number of records. 
					commonEntityDataBean.setStartRecord(1);
					commonEntityDataBean.setCount(filteredNotesList.size());
				    commonEntityDataBean.setEndRecord(filteredNotesList.size());
					commonEntityDataBean.setRenderedreccordsnum(true);
					
				} else if (filteredNotesList.size() <= 0) {
					commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
            		.setCurrentNote(ContactManagementConstants.EMPTY_STRING);
					commonEntityDataBean.setCurrentNoteRender(false);
					commonEntityDataBean.setRenderedreccordsnum(false);
				}
			}
		}

	}

	/**
	 * To get the Notes based on the filter infrmation entered by the user. This
	 * method would invoke the getFilteredNotes() method of
	 * commonEntityDelegate.
	 */

	public void getFilteredNotes() {
		logger.debug("getfiltered notes......1");
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				ProgramConstants.DATE_FORMAT, Locale.getDefault());
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();

		commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
				.setFilterbeginDate(new Date());

		boolean errFlg = false;
		String beginDate = commonEntityDataBean.getCommonNoteSearchVO()
				.getStrBeginDate();
		logger.info("beginDate=======" + beginDate);
		String endDate = commonEntityDataBean.getCommonNoteSearchVO()
				.getStrEndDate();
		logger.info("endDate=======" + endDate);
		String usageTypeCode = commonEntityDataBean.getCommonNoteSearchVO()
				.getUsageTypeCode();
		logger.info("usageTypeCode=======" + usageTypeCode);
		String userId = commonEntityDataBean.getCommonNoteSearchVO()
				.getUserId();
		logger.info("userId=======" + userId);
		logger.debug("getfiltered notes......2");
		int x = 0;
		if (beginDate != null && beginDate.trim().length() > x) {
			try {
				beginDate = commonEntityValidator.getValidateDate(beginDate);
				commonEntityDataBean.getCommonNoteSearchVO().setStrBeginDate(
						beginDate);
				dateFormat.setLenient(false);
				dateFormat.parse(beginDate);
			} catch (ParseException e1) {
				logger.debug("get filtered notes exception, parsing error ");
				commonEntityValidator.setErrorMessage(
						ProgramConstants.NOTES_BEGINDATE_INVALID,
						new Object[] {},
						ProgramConstants.COMMON_NOTES_PROPERTIES,
						"filterBeginDt");
			}
		}
		logger.debug("getfiltered notes......3");

		if (endDate != null && endDate.trim().length() > x) {
			try {
				endDate = commonEntityValidator.getValidateDate(endDate);
				commonEntityDataBean.getCommonNoteSearchVO().setStrEndDate(
						endDate);
				dateFormat.setLenient(false);
				dateFormat.parse(endDate);
			} catch (ParseException e1) {
				logger.debug("in validating dates, parsing error ");
				commonEntityValidator
						.setErrorMessage(
								ProgramConstants.NOTES_ENDDATE_INVALID,
								new Object[] {},
								ProgramConstants.COMMON_NOTES_PROPERTIES,
								"filterEndDt");
			}
		}

		if (((beginDate != null && beginDate.trim().length() > x) && (endDate != null && endDate
				.trim().length() > x))
				&& (ContactHelper.dateConverter(endDate).compareTo(
						ContactHelper.dateConverter(beginDate)) < x)) {
			setErrorMessage(ProgramConstants.NOTES_END_DATE_LESSTHAN_BEGINDATE);
			errFlg = true;
		}

		if (!errFlg) {
			NoteSet noteSet = null;
			//Defect fixed for Filter Issue.
			//List notesList = commonEntityDataBean.getNoteList();//ESPRD00690090
																// FilterNotesList();
			List notesList = commonEntityDataBean.getFilterNotesList();
			ArrayList filteredNotesList = new ArrayList();
			if (notesList != null && notesList.size() > 0) {
				Iterator itr = notesList.iterator();
				while (itr.hasNext()) {
					CommonNotesVO commonNotesVO = (CommonNotesVO) itr.next();
					boolean filtered = true;
					if (beginDate != null && beginDate.trim() != null
							&& !beginDate.trim().equalsIgnoreCase("")
							&& endDate != null && endDate.trim() != null
							&& !endDate.trim().equalsIgnoreCase("")) {
						logger.info("both the dates are not null");
						Date fromDate = ContactHelper.dateConverter(beginDate);
						Date toDate = ContactHelper.dateConverter(endDate);
						Date strBeginDate = ContactHelper
								.dateConverter(commonNotesVO.getStrBeginDate());
						logger.info("strBeginDate=======" + strBeginDate);
						if (strBeginDate.before(fromDate)
								|| strBeginDate.after(toDate)) {
							filtered = false;
							logger.info("1=========" + filtered);
						}
					} else if (beginDate != null && beginDate.trim() != null
							&& !beginDate.trim().equalsIgnoreCase("")) {
						logger.info("begin date is not null");
						Date fromDate = ContactHelper.dateConverter(beginDate);
						Date strBeginDate = ContactHelper
								.dateConverter(commonNotesVO.getStrBeginDate());
						if (strBeginDate.before(fromDate)) {
							filtered = false;
							logger.info("2=========" + filtered);
						}
					} else if (endDate != null && endDate.trim() != null
							&& !endDate.trim().equalsIgnoreCase("")) {
						logger.info("end date is not null");
						Date toDate = ContactHelper.dateConverter(endDate);
						Date strBeginDate = ContactHelper
								.dateConverter(commonNotesVO.getStrBeginDate());
						if (strBeginDate.after(toDate)) {
							filtered = false;
							logger.info("3=========" + filtered);
						}
					}
					if (usageTypeCode != null && usageTypeCode.trim() != null
							&& !usageTypeCode.trim().equalsIgnoreCase("")) {
						if (!usageTypeCode.equalsIgnoreCase(commonNotesVO
								.getUsageTypeCode())) {
							filtered = false;
							logger.info("4=========" + filtered);
						}
					}
					if (userId != null && userId.trim() != null
							&& !userId.trim().equalsIgnoreCase("")) {
						if (!userId.equalsIgnoreCase(commonNotesVO.getUserId())) {
							filtered = false;
							logger.info("5=========" + filtered);
						}
					}
					if (filtered) {
						filteredNotesList.add(commonNotesVO);
					}
				}
				//commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setNotesList(filteredNotesList);
				//Defect Fixed for filter issue.
				commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setNotesList(filteredNotesList);
				//commonEntityDataBean.setNoteList(filteredNotesList);
				if (filteredNotesList != null && filteredNotesList.size() > 0) {
					//Defect Fixed for filter issue.
					CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean.getCommonEntityVO().getNoteSetVO().getNotesList().get(0);
							//.getNoteList().get(0);
					commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
							.setCurrentNote(commonNoteVO.getNoteText());
					commonEntityDataBean.setCurrentNoteRender(true);
					
					//Added for the defect - ESPRD00846619 to display the proper search result grid with number of records. 
					commonEntityDataBean.setStartRecord(1);
					commonEntityDataBean.setCount(filteredNotesList.size());
				    commonEntityDataBean.setEndRecord(filteredNotesList.size());
					commonEntityDataBean.setRenderedreccordsnum(true);
				    
				} else if (filteredNotesList.size() <= 0) {
					commonEntityDataBean.setCurrentNoteRender(false);
					commonEntityDataBean.setRenderedreccordsnum(false);
				}
			}

			/*
			 * try { CommonEntityDelegate commonEntityDelegate = new
			 * CommonEntityDelegate(); Long noteSetSK =
			 * commonEntityDataBean.getCommonEntityVO()
			 * .getNoteSetVO().getNoteSetSK();
			 * 
			 * logger.info("beginDate---" + beginDate + "-endDate---" +
			 * endDate);
			 * 
			 * CommonNotesSearchCriteriaVO commonNoteSearchVO = new
			 * CommonNotesSearchCriteriaVO();
			 * commonNoteSearchVO.setNotesID(noteSetSK);
			 * commonNoteSearchVO.setBeginDate(ContactHelper
			 * .dateConverter(beginDate));
			 * commonNoteSearchVO.setEndDate(ContactHelper
			 * .dateConverter(endDate));
			 * 
			 * 
			 * if(beginDate != null && !beginDate.equalsIgnoreCase("")) {
			 * 
			 * commonNoteSearchVO.setBeginDate(ContactHelper
			 * .dateConverter(beginDate)); logger.debug("beginDate Inside if
			 * ::::::::::: "+ commonNoteSearchVO.getBeginDate()); } if(endDate !=
			 * null && !endDate.equalsIgnoreCase("")) {
			 * commonNoteSearchVO.setEndDate(ContactHelper
			 * .dateConverter(endDate)); logger.debug("endDate Inside if
			 * ::::::::::: "+ commonNoteSearchVO.getEndDate()); } if
			 * (usageTypeCode != null && usageTypeCode.trim().length() == x) {
			 * usageTypeCode = null; }
			 * 
			 * if (userId != null && userId.trim().length() == x) { userId =
			 * null; }
			 * 
			 * commonNoteSearchVO.setUsageTypeCode(usageTypeCode);
			 * commonNoteSearchVO.setUserId(userId); noteSet =
			 * commonEntityDelegate .getFilteredNotes(commonNoteSearchVO);
			 * 
			 * if (noteSet != null) {
			 * 
			 * ContactHelper contactHelper = new ContactHelper(); NoteSetVO
			 * noteSetVO = contactHelper .convertNoteSetDomainToVO(noteSet);
			 * 
			 * commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
			 * .setNotesList(noteSetVO.getNotesList()); //
			 * setNoteSetVO(noteSetVO);
			 * 
			 * if (commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
			 * .getNotesList() != null &&
			 * commonEntityDataBean.getCommonEntityVO()
			 * .getNoteSetVO().getNotesList().size() > x) {
			 * commonEntityDataBean.getCommonEntityVO() .getCommonNotesVO()
			 * .setRenderNoHistoryMsg(false); } else {
			 * commonEntityDataBean.getCommonEntityVO()
			 * .getCommonNotesVO().setRenderNoHistoryMsg(true); }
			 * 
			 * CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
			 * .getCommonEntityVO().getNoteSetVO().getNotesList() .get(0);
			 * commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
			 * .setCurrentNote(commonNoteVO.getNoteText()); } else {
			 * commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
			 * .setNotesList(new ArrayList()); } } catch (Exception e) {
			 * logger.error(e.getMessage(), e);
			 * commonEntityValidator.setErrorMessage(ContactManagementConstants.SAVE_FAILURE,
			 * new Object[] {},
			 * ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
			 * null); logger.error(e.getMessage(), e); }
			 */
		}

	}

	/**
	 * To display the selected Notes.
	 */
	public void displaySelectedNote() {
		logger.info("displaySelectedNote=====");
		commonEntityDataBean.setNoteFlag(true);
		if (commonEntityDataBean.getNoteTypeList() == null
				|| commonEntityDataBean.getNoteTypeList().size() == 0) {
			getReferenceData();
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = "0";
		int x = 0;
		if (map != null && map.size() > x) {
			indexCode = (String) map.get(ContactManagementConstants.INDEX_CODE);
			if (indexCode == null || indexCode.trim().length() == x) {
				indexCode = "0";
			} else {
				commonEntityDataBean.setCurrentNoteRender(true);
			}

		}

		int index = Integer.parseInt(indexCode);
		//commonEntityDataBean.setStartIndexForCmnNotes((index / 10) * 10);
		logger.info("index====" + index);
		String indexCodeCHK = (String) map
				.get(BenefitPlanConstants.INDEX_CODE_CHK);
		logger.info("indexCodeCHK--------" + indexCodeCHK);
		if (indexCodeCHK != null && indexCodeCHK.trim().length() > x) {
			rowCheckBoxChanged();
		} else {

			CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
					.getCommonEntityVO().getNoteSetVO().getNotesList().get(
							index);
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(commonNoteVO.getNoteText());

		}
		  
	}

	/**
	 * To display the selected NotesForCR.
	 */
	public void displaySelectedNoteForCR() {

		logger.info("displaySelectedNoteForCR=====");
		commonEntityDataBean.setNoteFlag(true);
		if (commonEntityDataBean.getNoteTypeList() == null
				|| commonEntityDataBean.getNoteTypeList().size() == 0) {
			getReferenceData();
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = "0";
		int x = 0;
		if (map != null && map.size() > x) {
			indexCode = (String) map.get(ContactManagementConstants.INDEX_CODE);
			if (indexCode == null || indexCode.trim().length() == x) {
				indexCode = "0";
			} else {
				commonEntityDataBean.setCurrentNoteRender(true);
			}
			//COde added for defect - ESPRD00920192 for row highlight to display the highlighted row in notes section
			getCommonEntityDataBean().setItemSelectedRowIndeNotes(Integer.parseInt(indexCode));
		}

		int index = Integer.parseInt(indexCode);
		/*below line of code is commented for not to set StartIndex on click of row
		for resolving defect ESPRD00912138*/
	    //commonEntityDataBean.setStartIndexForCmnNotes((index / 10) * 10);
		logger.info("index====" + index);
		String indexCodeCHK = (String) map
				.get(BenefitPlanConstants.INDEX_CODE_CHK);
		logger.info("indexCodeCHK--------" + indexCodeCHK);
		if (indexCodeCHK != null && indexCodeCHK.trim().length() > x) {
			rowCheckBoxChanged();
		} else {
			CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
					.getNoteList().get(index);
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(commonNoteVO.getNoteText());

		}
		  

	}

	/**
	 * To Filter the Notes.
	 */
	public void filterNotes() {

		getReferenceData();
		if (commonEntityDataBean.isGlobalDataRender()) {
			if (commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
					.getGlobalNotesList() != null
					&& commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
							.getGlobalNotesList().size() > 0) {
				commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
						.setNotesList(
								commonEntityDataBean.getCommonEntityVO()
										.getNoteSetVO().getGlobalNotesList());
			}
		}
		commonEntityDataBean.setNewNotesRender(false);
		commonEntityDataBean.setFilterNotesRender(true);
		if (commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
				.getNotesList().size() > 0) {
			CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
					.getCommonEntityVO().getNoteSetVO().getNotesList().get(0);
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(commonNoteVO.getNoteText());
		}

	}

	/**
	 * This operation is to save the commonNotesVO to request scope, this method
	 * invkoes the validateNotes() method of commonEntityValidator. If the
	 * CommonNotesVO is valid then validateNote() sets the CommonNotesVO to
	 * commonEntityDatatbean.NoteSet. And commonEntityDataBean will be save to
	 * request scope using tstate tag.
	 */
	public void saveNotes() {

		logger.info("saveNotes-------");
		boolean valid = false;
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
		ContactManagementHelper helper = new ContactManagementHelper();
		/*
		 * valid = commonEntityValidator.validateNotes(commonEntityDataBean
		 * .getCommonEntityVO().getCommonNotesVO());
		 */
		boolean flag = commonEntityDataBean.isSaveNoteflag();
		/** Disable Filter Link */
		commonEntityDataBean.setFilterEnabled(true);
		/** end of Disable Filter Link */
		getCorrespondenceDataBean().setCrSaved(false);
						
		if (flag) {
			
			valid = commonEntityValidator.validateNotes(commonEntityDataBean
					.getCommonEntityVO().getCommonNotesVO());
			
			if (valid) {
				//commonEntityDataBean.getCommonEntityVO().getCommonNotesVO().setChecked(false);
				//commonNotesVO.setCommonEntityTypeCode("M");

				int seqNum = 0;
				if (commonEntityDataBean.getNoteList() != null
						&& commonEntityDataBean.getNoteList().size() > 0) {
					seqNum = commonEntityDataBean.getNoteList().size();
				}

				String shortNotes = "";

				shortNotes = commonEntityDataBean.getCommonEntityVO()
						.getCommonNotesVO().getNoteText();

				if (shortNotes != null
						&& shortNotes.trim().length() > ProgramNumberConstants.INT_HUNDRED) {
					shortNotes = shortNotes.substring(0,
							ProgramNumberConstants.INT_HUNDRED);
				}

				commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
						.setShortNotes(shortNotes);

				commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
						.setNoteSequenceNumber(Long.valueOf(seqNum + 1));

				String usageTypeCode = commonEntityDataBean.getCommonEntityVO()
						.getCommonNotesVO().getUsageTypeCode();

				if (commonEntityDataBean.getNoteTypeList() != null
						&& commonEntityDataBean.getNoteTypeList().size() > 0) {
					commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
							.setUsageTypeDesc(
									getDescriptionFromVV(usageTypeCode,
											commonEntityDataBean
													.getNoteTypeList()));
				}
				ContactHelper contactHelper = new ContactHelper();
				commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
						.setUserId(contactHelper.getUserID());
				String userId = contactHelper.getUserID();
				String userName = contactHelper.getUserNameByID(userId);
				String userIdName = null;
				if (userName != null && userName.length() != 0) {
					//Below code is modified for defect ESPRD00944125
					userIdName =userName;
				} else {
					userIdName = userId;
				}
				commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
						.setUserIdName(userIdName);
				if (commonEntityDataBean.getNoteList() == null) {
					commonEntityDataBean.setNoteList(new ArrayList());
				}
				commonEntityDataBean.getNoteList().add(
						0,
						commonEntityDataBean.getCommonEntityVO()
								.getCommonNotesVO());
				Date date = new Date();
				/*
				 * commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
				 * .setAddedAuditTimeStamp(date);
				 */
				commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
						.setFilterbeginDate(date);
				/*
				 * SimpleDateFormat dateFormat = new SimpleDateFormat(
				 * "M/dd/yyyy hh:mm:ss", Locale.getDefault());
				 *///Find bug issue
				//UC-PGM-CRM-045_ESPRD00378958_28JAN10
				/*
				 * Commenting the code for CR- ESPRD00357856, which is
				 * applicable for all the protlets which are using the notes, so
				 * don't uncomment this code. Please use the CR as reference for
				 * any defects.
				 */
				/*
				 * commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
				 * .setStrBeginDate(dateFormat.format(date));
				 */

				commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
						.setCurrentNote(
								commonEntityDataBean.getCommonEntityVO()
										.getCommonNotesVO().getNoteText());
				commonEntityDataBean.setNewNotesRender(false);
				//commented on 25th July to implement the block level save
				//setErrorMessage(ProgramConstants.NOTES_SAVE_SUCC_MESSAGE);
				commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
						.setRenderNoHistoryMsg(false);
			
				//making checkall as false
				commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
						.setCheckAll(false);
				
				commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
						.setChecked(false);
				//sortListByDate(commonEntityDataBean.getNoteList());
				//heap dump issue
				//dataTable.setFirst(0);
				commonEntityDataBean.setStartIndexForCmnNotes(0);
				commonEntityDataBean.setNotesSaveMsg(true);
				commonEntityDataBean.setCommonNoteSaved(true);
				commonEntityDataBean.setSaveNoteflag(false);
				commonEntityDataBean.setSmallSaveFlag("true");
			} else {
				if (commonEntityDataBean.getNoteList() != null

				&& commonEntityDataBean.getNoteList().size() > 0) {
					((CommonNotesVO) commonEntityDataBean.getNoteList().get(0))
							.setChecked(commonEntityDataBean
									.getCommonEntityVO().getCommonNotesVO()
									.getChecked());
					commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
							.setCurrentNote(
									((CommonNotesVO) commonEntityDataBean
											.getNoteList().get(0))
											.getNoteText());
				}
			}

			}

		
		//ADDED FOR THE DEFECT ESPRD00689752
		if(commonEntityDataBean.getNoteList() != null)
		{
			commonEntityDataBean.setTempNoteList(new ArrayList(
					commonEntityDataBean.getNoteList()));

			// ADDED END FOR THE DEFECT ESPRD00689752

			// Added for the defect ESPRD00542028

			/*
			 * Below 3 lines of code are moved in to this if condition to display
			 * the validation error message after clicking small save for the
			 * Defect - ESPRD00844471.
			 */
			commonEntityDataBean.setStartRecord(1);
			commonEntityDataBean.setEndRecord(commonEntityDataBean
					.getNoteList().size());
			commonEntityDataBean.setCount(commonEntityDataBean.getNoteList()
					.size());
			/* commonEntityDataBean.setSaveNotesChk(false); */
			// Below Code added for the defect ESPRD00795893 to display record
			// count.
			commonEntityDataBean.setRenderedreccordsnum(true);
		}
		commonEntityDataBean.setSaveNotesChk(false);
		
		//COde added for defect - ESPRD00920192 for row highlight to display the highlighted row in notes section
		commonEntityDataBean.setItemSelectedRowIndeNotes(-1);

	}

	/**
	 * To operation contains the logic to display UI components to enter new
	 * Notes information.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public void addNewNotes() {
		logger.info("addNewNotes=========");
		CommonNotesVO commonNotesVO = null;
		commonNotesVO = new CommonNotesVO();
		commonEntityDataBean.getCommonEntityVO()
				.setCommonNotesVO(commonNotesVO);

		/** Disable Filter Link */
		commonEntityDataBean.setFilterEnabled(false);
		commonEntityDataBean.setSaveNoteflag(true);
		/** end of Disable Filter Link */
		getReferenceData();
		commonEntityDataBean.setNewNotesRender(true);
		commonEntityDataBean.setFilterNotesRender(false);
		commonEntityDataBean.setMainNotesRender(true);
		commonEntityDataBean.setSaveNotesChk(true);
		commonEntityDataBean.setNotesSaveMsg(false);
		if (commonEntityDataBean.getNoteList() != null
				&& commonEntityDataBean.getNoteList().size() > 0) {
			logger
					.info("size====="
							+ commonEntityDataBean.getNoteList().size());
			CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
					.getNoteList().get(0);
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(commonNoteVO.getNoteText());
			((CommonNotesVO) commonEntityDataBean.getNoteList().get(0))
					.setChecked(commonEntityDataBean.getCommonEntityVO()
							.getCommonNotesVO().getChecked());
		}
		commonEntityDataBean.getCommonEntityVO().getCommonNotesVO().setChecked(
				commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
						.getChecked());

	}

	/**
	 * To hide the UI fields related to Note.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public void showNotes() {
		logger.info("showNotes");
		logger.debug("entered into show notes method...1");
		commonEntityDataBean.setNewNotesRender(false);
		commonEntityDataBean.setFilterNotesRender(false);
		commonEntityDataBean.setMainNotesRender(true);
		logger.debug("entered into show notes method...2");

		if (commonEntityDataBean.getCommonEntityVO().getNoteSetVO() == null) {
			logger.debug("show notes...entered into nul part of notesetvo");
			commonEntityDataBean.getCommonEntityVO().setNoteSetVO(
					new NoteSetVO());
		}
		logger.debug("entered into show notes method...3");
		if (commonEntityDataBean.getCommonEntityVO().getCommonNotesVO() == null) {
			logger
					.debug("show notes...entered into nul part of common notes vo");
			commonEntityDataBean.getCommonEntityVO().setCommonNotesVO(
					new CommonNotesVO());
		}
		logger.debug("entered into show notes method...4");
		//need to comment while integrating with modules
		/*
		 * commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setNoteSetSK(
		 * new Long("139"));
		 */
		logger.debug("entered into show notes method...5");
		//modified to performance fix as list retrieving from vo every time
		//causes delay hence referred to a property and used in further. 
		List noteList=commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
		.getNotesList();
		if (noteList != null
				&& !noteList.isEmpty()) {
			sortListByDate(noteList);
			if (logger.isDebugEnabled()) {
				logger
						.debug("commonEntityDataBean.getCommonEntityVO().getNoteSetVO() . getNotesList() != null");
				logger.debug("sorting starting ..." + noteList.size());
			}
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setRenderNoHistoryMsg(false);
			CommonNotesVO commonNoteVO = (CommonNotesVO) noteList.get(0);
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(commonNoteVO.getNoteText());
		} else {
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setRenderNoHistoryMsg(true);
		}
		logger.debug("entered into show notes method...6");

		/*
		 * commonEntityDataBean.setNoteSetSK(new Long(139)); getNotes();
		 */

		//commonEntityDataBean.getCommonEntityVO().getCommonNotesVO().setAddNewLinkRender(false);
		//commonEntityDataBean.getCommonEntityVO().getCommonNotesVO().setFilterLinkRender(false);
		//commonEntityDataBean.getCommonEntityVO().getCommonNotesVO().setPrintLinkRender(false);
		/* setValidValues() ; */

	}

	/**
	 * To hide the UI fields related to Note.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public void getNotes() {

		if (commonEntityDataBean.getNoteSetSK() != null) {
			CommonEntityDelegate commonEntityDelegate;

			CommonNotesSearchCriteriaVO commonNoteSearchVO = new CommonNotesSearchCriteriaVO();
			commonNoteSearchVO.setNotesID(commonEntityDataBean.getNoteSetSK());
			commonNoteSearchVO.setUsageTypeCode(null);
			commonNoteSearchVO.setUserId(null);
			commonNoteSearchVO
					.setBeginDate(ContactHelper
							.dateConverter(ContactManagementConstants.DEFAULT_DATE_NOTES));
			commonNoteSearchVO.setEndDate(ContactHelper
					.dateConverter(ContactManagementConstants.DEFAULT_DATE));
			try {
				commonEntityDelegate = new CommonEntityDelegate();
				NoteSet noteSet = commonEntityDelegate
						.getFilteredNotes(commonNoteSearchVO);
				if (noteSet != null) {
					ContactHelper contactHelper = new ContactHelper();
					NoteSetVO noteSetVO = contactHelper
							.convertNoteSetDomainToVO(noteSet);
					commonEntityDataBean.getCommonEntityVO().setNoteSetVO(
							noteSetVO);

				}

			} catch (CommonNoteNotFoundException e) {
				logger.error("Error in the Get Notes Of Notes ControllerBean"
						+ e);
			}

		}
		showNotes();

	}

	/**
	 * To hide the UI fields related to Note.
	 * 
	 * @return cancelNotes
	 */
	public String cancelNotes() {

		commonEntityDataBean.setNewNotesRender(false);
		commonEntityDataBean.setFilterNotesRender(false);
		commonEntityDataBean.setMainNotesRender(false);
		commonEntityDataBean.setSaveNotesChk(false);
		commonEntityDataBean.setFilterEnabled(true);
		return "cancelNotes";
	}

	/**
	 * To reset the values.
	 */

	public void reset() {

		commonEntityDataBean.getCommonNoteSearchVO().setBeginDate(null);
		commonEntityDataBean.getCommonNoteSearchVO().setEndDate(null);
		commonEntityDataBean.getCommonNoteSearchVO().setStrBeginDate(null);
		commonEntityDataBean.getCommonNoteSearchVO().setStrEndDate(null);
		commonEntityDataBean.getCommonNoteSearchVO().setUserId("");
		commonEntityDataBean.getCommonNoteSearchVO().setUsageTypeCode("");
	}

	/**
	 * To set the error message.
	 * 
	 * @param message
	 *            The message to get the message.
	 */
	private void setErrorMessage(String message) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().setMessageBundle(
				ProgramConstants.COMMON_NOTES_PROPERTIES);
		ResourceBundle bundle = resourceBundle(fc);
		String errorMsg = bundle.getString(message);
		FacesMessage fm = new FacesMessage(errorMsg);
		fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		fc.addMessage(null, fm);

	}

	/**
	 * This method is used for populating Resource Bundle.
	 * 
	 * @param facesContext
	 *            The facesContext to get context values.
	 * @return ResourceBundle
	 */
	private ResourceBundle resourceBundle(FacesContext facesContext) {
		facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		String messageBundle = facesContext.getApplication().getMessageBundle();
		Locale locale = root.getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);
		return bundle;
	}

	/**
	 * To handle the checkBox chnange event.
	 */
	public void checkBoxChanged() {

		boolean checkedAll = commonEntityDataBean.getCommonEntityVO()
				.getNoteSetVO().getCheckAll();

		ArrayList notesList = commonEntityDataBean.getCommonEntityVO()
				.getNoteSetVO().getNotesList();

		int size = notesList.size();

		for (int i = 0; i < size; i++) {
			((CommonNotesVO) notesList.get(i)).setChecked(checkedAll);
		}

		commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setCheckAll(
				checkedAll);
		commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setNotesList(
				notesList);

		//this is to set the first index/0 value of grid
		commonEntityDataBean.getCommonEntityVO().getCommonNotesVO().setChecked(
				checkedAll);
	}

	/**
	 * To handle if single check box is selected.
	 */

	public void rowCheckBoxChanged() {
		logger.info("rowCheckBoxChanged--------");
		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCodeCHK = "0";
		String firstRowIndex = "0";
		String indexCode = "0";
		int x = 0;
		if (map != null && map.size() > 0) {
			
			indexCode = (String) map.get(ContactManagementConstants.INDEX_CODE);
			if (indexCode == null || indexCode.trim().length() == x) {
				indexCode = "0";
			}
		}
    	int index = Integer.parseInt(indexCode);
		boolean modifiedFlg = false;
		/*
		 * int size = commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
		 * .getNotesList().size();
		 */
		if (!modifiedFlg) {
			CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
					.getNoteList().get(index);
			modifiedFlg = true;
			
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(commonNoteVO.getNoteText());
			if (!commonNoteVO.getChecked()) {
				commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
						.setCheckAll(false);
			}
		}
	}

	/**
	 * To sort Notes.
	 * 
	 * @param event
	 *            The event to handle the ActionEvent.
	 */
	public void sort(ActionEvent event) {

		ArrayList notesVOList = commonEntityDataBean.getCommonEntityVO()
				.getNoteSetVO().getNotesList();
		if (notesVOList != null) {
			logger.debug("notesVOList size " + notesVOList.size());
		}
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.COLUMN_NAME);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(ContactManagementConstants.SORT_ORDER);
	    // Added for the Defect ID :ESPRD00802081
	    commonEntityDataBean.setImageRender(event.getComponent().getId());
		clearAllChecks();
		Comparator comparator = new Comparator() {

			public int compare(Object obj1, Object obj2) {
				CommonNotesVO commonNotesVO1 = (CommonNotesVO) obj1;
				CommonNotesVO commonNotesVO2 = (CommonNotesVO) obj2;

				commonNotesVO1.setChecked(false);
				commonNotesVO2.setChecked(false);

				boolean ascending = false;
				if ("asc".equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}

				if ("User ID".equals(sortColumn)) {

					return ascending ? (commonNotesVO1.getUserId()
							.compareToIgnoreCase(commonNotesVO2.getUserId()))
							: (commonNotesVO2.getUserId()
									.compareToIgnoreCase(commonNotesVO1.getUserId()));
				}

				if ("Note".equals(sortColumn)) {

					return ascending ? (commonNotesVO1.getNoteText()
							.compareToIgnoreCase(commonNotesVO2.getNoteText()))
							: (commonNotesVO2.getNoteText()
									.compareToIgnoreCase(commonNotesVO1.getNoteText()));
				}

				if ("Usage Type Code".equals(sortColumn)) {

					return ascending ? (commonNotesVO1.getUsageTypeCode()
							.compareToIgnoreCase(commonNotesVO2.getUsageTypeCode()))
							: (commonNotesVO2.getUsageTypeCode()
									.compareToIgnoreCase(commonNotesVO1
											.getUsageTypeCode()));
				}

				if ("Date / Time".equals(sortColumn)) {
                    // Commented for the Defect ID: ESPRD00802081
					/*
					 * if (commonNotesVO1.getFilterbeginDate() != null &&
					 * commonNotesVO2.getFilterbeginDate() != null) { return
					 * ascending ? (commonNotesVO1.getFilterbeginDate()
					 * .compareTo(commonNotesVO2.getFilterbeginDate())) :
					 * (commonNotesVO2.getFilterbeginDate()
					 * .compareTo(commonNotesVO1 .getFilterbeginDate())); }
					 */
					// Added for the Defect ID: ESPRD00802081
					Date opendate1 = ContactManagementHelper.getOpenEndedDate();
					Date opendate2 = ContactManagementHelper.getOpenEndedDate();
					DateFormat dateFormat = new SimpleDateFormat(ContactManagementConstants.DATE_FORMAT_TIME_SECONDS);

					try {
						if (null == commonNotesVO1.getStrBeginDate())
						{
							commonNotesVO1.setStrBeginDate(MaintainContactManagementUIConstants.NULL);
						}
						else {
							opendate1 = dateFormat.parse(commonNotesVO1
									.getStrBeginDate());
						}
						if (null == commonNotesVO2.getStrBeginDate())
						{
							commonNotesVO2
									.setStrBeginDate(MaintainContactManagementUIConstants.NULL);
						}
						else {
							opendate2 = dateFormat.parse(commonNotesVO2
									.getStrBeginDate());
						}
					}catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ascending ? opendate1.compareTo(
					opendate2) : opendate2.compareTo(opendate1);

				}

				return 0;
			}

		};
        Collections.sort(notesVOList, comparator);
        // Added for the Defect ID :ESPRD00802081
        commonEntityDataBean.setNoteList(notesVOList);
		//heap dump issue
		//dataTable.setFirst(0);
		commonEntityDataBean.setStartIndexForCmnNotes(0);
		// Added for the Defect ID :ESPRD00802081
		setCrntNteFrmNteLst();
	}

	/**
	 * To handle the DataScroll action.
	 * 
	 * @param e
	 *            The e to handle the ActionEvent.
	 */
	public void dataScrollerAction(ActionEvent e) {
		clearAllChecks();
	}

	/**
	 * To clear all check boxes.
	 */
	private void clearAllChecks() {

		ArrayList notesList = commonEntityDataBean.getCommonEntityVO()
				.getNoteSetVO().getNotesList();
		boolean checkedAll = commonEntityDataBean.getCommonEntityVO()
				.getNoteSetVO().getCheckAll();
		if (notesList != null && notesList.size() > 0) {
			int size = notesList.size();
			for (int i = 0; i < size; i++) {
				if (checkedAll == false) {
					((CommonNotesVO) notesList.get(i)).setChecked(false);
				} else {
					((CommonNotesVO) notesList.get(i)).setChecked(true);
				}
			}
		}

		commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setCheckAll(
				checkedAll);
		commonEntityDataBean.getCommonEntityVO().getCommonNotesVO().setChecked(
				checkedAll);
	}

	/**
	 * To sort the List based on date.
	 * 
	 * @param notesList
	 *            The notesList to get the CommonNotesVOs
	 */
	private void sortListByDate(List notesList) {

		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CommonNotesVO commonNotesVO1 = (CommonNotesVO) obj1;
				CommonNotesVO commonNotesVO2 = (CommonNotesVO) obj2;
				commonNotesVO1.setChecked(false);
				commonNotesVO2.setChecked(false);
				try {
					//modified for defect ESPRD00806656
					//sorting should be done by date/time
					//SimpleDateFormat dateFormate = new SimpleDateFormat(
						//	"MM/dd/yyyy hh:mm:ss");
					Date begindate1 = ContactManagementHelper.getOpenEndedDate();
					Date begindate2 = ContactManagementHelper.getOpenEndedDate();
					DateFormat dateFormat = new SimpleDateFormat(ContactManagementConstants.DATE_FORMAT_TIME_SECONDS);
					if (null == commonNotesVO1.getStrBeginDate())
					{
						commonNotesVO1.setStrBeginDate(MaintainContactManagementUIConstants.NULL);
					}
					else {
						begindate1 = dateFormat.parse(commonNotesVO1
								.getStrBeginDate());
					}
					if (null == commonNotesVO2.getStrBeginDate())
					{
						commonNotesVO2
								.setStrBeginDate(MaintainContactManagementUIConstants.NULL);
					}
					else {
						begindate2 = dateFormat.parse(commonNotesVO2
								.getStrBeginDate());
					}
					boolean ascending = false;
					if (begindate1 != null && begindate2 != null) {
						if (begindate1.compareTo(begindate2) != 0) {
							return ascending ? begindate1.compareTo(
									begindate2) : begindate2.compareTo(begindate1);
						} else {
							return ascending ? (commonNotesVO1
									.getNoteSequenceNumber()
									.compareTo(commonNotesVO2
											.getNoteSequenceNumber()))
									: (commonNotesVO2.getNoteSequenceNumber()
											.compareTo(commonNotesVO1
													.getNoteSequenceNumber()));
						}
					}

				} catch (Exception e) {
					logger.debug(" inside Common Notes search Exception");
					e.printStackTrace();

				}
				return 0;
			}

		};
		Collections.sort(notesList, comparator);

	}
/**
	 * To get the avlidvalues.
	 */
	private void getReferenceData() {
		logger.info("getReferenceData of common notes===");
		InputCriteria inputCriteria = null;
		List list = null;
		HashMap map = new HashMap();
		ReferenceDataSearchVO referenceDataSearch = null;
		ReferenceServiceDelegate referenceServiceDelegate = null;
		ReferenceDataListVO referenceDataListVO = null;
		Long propVal = null;
		try {
			ContactMaintenanceDelegate cmMaintenanceDelegate = new ContactMaintenanceDelegate();
			List buinessUnitDescs = cmMaintenanceDelegate
					.getBuisnessUnitsDescs(getUserIDForSecurity());
			Properties sysListProp = ConfigurationLoader.getInstance()
					.getSystemListNumberProperties();
			String elementName = ReferenceServiceDataConstants.G_NOTE_TY_CD;
			if (buinessUnitDescs != null && buinessUnitDescs.size() == 1) {
				if (sysListProp.getProperty(elementName + "_"
						+ (String) buinessUnitDescs.get(0)) != null) {
					elementName = elementName + "_"
							+ (String) buinessUnitDescs.get(0);
					propVal = Long
							.valueOf(sysListProp.getProperty(elementName));
				} else {
					propVal = Long
							.valueOf(sysListProp.getProperty(elementName));
				}
			} else {
				propVal = Long.valueOf(sysListProp.getProperty(elementName));
			}
		} catch (LOBHierarchyFetchBusinessException e) {
			logger.debug(e);
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.debug(e);
			logger.error(e.getMessage(), e);
		}

		//filling the drop down of claim type code
		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria.setListNumber(propVal);
		list = new ArrayList();
		list.add(inputCriteria);
		referenceDataSearch = new ReferenceDataSearchVO();
		referenceDataSearch.setInputList(list);
		referenceServiceDelegate = new ReferenceServiceDelegate();

		// Pass the list to the delegate
		logger.debug("bfor callling delegate");
		referenceDataListVO = new ReferenceDataListVO();
		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);
			map = referenceDataListVO.getResponseMap();
			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ propVal);
			if (list != null) {
				int noteSize = list.size();
				commonEntityDataBean.getNoteTypeList().clear();
				for (int i = 0; i < noteSize; i++) {
					ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
					commonEntityDataBean.getNoteTypeList().add(
							new SelectItem(refVo.getValidValueCode(), refVo
									.getValidValueCode()
									+ "-" + refVo.getShortDescription()));

				}
			}
		} catch (ReferenceServiceRequestException e) {
			logger.error(e.getMessage(), e);
		} catch (SystemListNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * To get the description based on code
	 * 
	 * @param code
	 *            Holds the code valus
	 * @param vvList
	 *            Holds the List of valid values
	 * @return String
	 */
	private String getDescriptionFromVV(String code, List vvList) {
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

	/**
	 * @return Returns the commonEntityDataBean.
	 */
	public CommonEntityDataBean getCommonEntityDataBean() {
		return commonEntityDataBean;
	}

	/**
	 * @param commonEntityDataBean
	 *            The commonEntityDataBean to set.
	 */
	public void setCommonEntityDataBean(
			CommonEntityDataBean commonEntityDataBean) {
		this.commonEntityDataBean = commonEntityDataBean;
	}

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

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode", userPermission);
		logger.debug("userPermission in Common notes -->" + userPermission);
		// check the permission level for the user and set it in a boolean
		// variable. This will be used for Buttons/Links
		if (userPermission.equals("r")) {
			controlRequired = false;
		}
	}

	private String getUserIDForSecurity() {
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
			e.getMessage();
			logger.debug("Exception has come:");
		}

		return userID;
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

	/**
	 * @return Returns the securityfield.
	 */
	public String getSecurityfield() {
		getUserPermission();
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
	 * 
	 * @return
	 */
	public static CMEntitySearchDataBean getCmEntityDataBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		CMEntitySearchDataBean cMEntitySearchDataBean = (CMEntitySearchDataBean) fc
				.getApplication().createValueBinding(
						"#{" +ContactManagementConstants.CMSEARCHENTITY_BEAN_NAME+ "}")
				.getValue(fc);
		return cMEntitySearchDataBean;
	}

	public static CorrespondenceDataBean getCorrespondenceDataBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		CorrespondenceDataBean correspondenceDataBean = (CorrespondenceDataBean) fc
				.getApplication().createValueBinding("#{" + BEAN_NAME + "}")
				.getValue(fc);
		return correspondenceDataBean;
	}

	public void checkBoxForCRChanged() {
		logger.debug("inside checkBoxForCRChanged ");

		boolean checkedAll = commonEntityDataBean.getCommonEntityVO()
				.getNoteSetVO().getCheckAll();
		logger.debug("inside checkBoxChanged :::::::::::" + checkedAll);

		/*
		 * ArrayList notesList = commonEntityDataBean.getCommonEntityVO()
		 * .getNoteSetVO().getNotesList();
		 */
		List notesList = commonEntityDataBean.getNoteList();

		logger.debug("notesList length:::::::: " + notesList.size());
		int size = notesList.size();

		for (int i = 0; i < size; i++) {
			((CommonNotesVO) notesList.get(i)).setChecked(checkedAll);
		}

		commonEntityDataBean.setNoteList(notesList);

		commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setCheckAll(
				checkedAll);

		//this is to set the first index/0 value of grid
		commonEntityDataBean.getCommonEntityVO().getCommonNotesVO().setChecked(
				checkedAll);
	}
	// Added for the Defect ID :ESPRD00802081
	/**
	 * This method will set the current note from the note list
	 */
	private void setCrntNteFrmNteLst() {
		commonEntityDataBean = ContactHelper.getCommonEntityDataBean();
		if (commonEntityDataBean.getNoteList() != null
				&& commonEntityDataBean.getNoteList().size() > 0) {
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setRenderNoHistoryMsg(false);
			CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
					.getNoteList().get(0);
			logger.debug("in show notes commonNoteVO.getNoteText() $$$$$"
					+ commonNoteVO.getNoteText());
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setCurrentNote(commonNoteVO.getNoteText());
			
			//COde added for defect - ESPRD00920192 for row highlight to display the highlighted row in notes section
			getCommonEntityDataBean().setItemSelectedRowIndeNotes(ContactManagementConstants.ZERO);
		} else {
			commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
					.setRenderNoHistoryMsg(true);
		}
	}
	
	/**
	 * To handle the checkBox change event for Log Case,for defect ESPRD00860914.
	 */
	public void checkBoxCaseChanged() {

		boolean checkedAll = commonEntityDataBean.getCommonEntityVO()
				.getNoteSetVO().getCheckAll();

		List notesList = commonEntityDataBean.getNoteList();
		int size = notesList.size();

		for (int i = 0; i < size; i++) {
			((CommonNotesVO) notesList.get(i)).setChecked(checkedAll);
		}
		commonEntityDataBean.getCommonEntityVO().getNoteSetVO().setCheckAll(
				checkedAll);
		commonEntityDataBean.setNoteList(notesList);
		commonEntityDataBean.getCommonEntityVO().getCommonNotesVO().setChecked(
				checkedAll);
	}
}