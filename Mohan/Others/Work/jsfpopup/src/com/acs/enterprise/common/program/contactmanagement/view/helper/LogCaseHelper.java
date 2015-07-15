/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.cots.CommonLetter.view.bean.LettersAndResponsesControllerBean;
import com.acs.enterprise.common.cots.CommonLetter.view.bean.LettersAndResponsesDataBean;
import com.acs.enterprise.common.cots.util.GlobalLetterConstants;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesVO;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRecord;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeTemplate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CMLogCaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CaseSearchDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AlertVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AlternateIdentifiersVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCaseVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCorrespondenceVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseEventsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingMemberVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingProviderVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingTPL;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingTradingPartnerVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseStepsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeARSVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;

/**
 * This class having all the helper methods related to Log Case.
 * 
 * @author Wipro
 */
public class LogCaseHelper {

	private static final EnterpriseLogger log = EnterpriseLogFactory
			.getLogger(LogCaseHelper.class);
	
	// Moved to ContactManagementConstants.java
	//public static final String CMLOGCASE_DATA_BEAN = "logCaseDataBean";

	private CMLogCaseDataBean logCaseDataBean;

	//DEFECT 535752
	
	// Moved to ContactManagementConstants.java
	//public static final String COMMON_ENTITY_DATA_BEAN = "commonEntityDataBean";

	private CommonEntityDataBean commonEntityDataBean;

	//END DEFECT 535752

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
	 * 
	 * @param sortColumn
	 * @param sortOrder
	 * @param dataList
	 */
	public void sortExistingCaseComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		log
				.debug("sortCaseRegardingMemberComparator @ CMLogCaseControllerBean started");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				AssociatedCaseVO data1 = (AssociatedCaseVO) obj1;
				AssociatedCaseVO data2 = (AssociatedCaseVO) obj2;

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
				if (MaintainContactManagementUIConstants.EXISTINGCASE_ID
						.equals(sortColumn)) {
					if (null == data1.getCaseID()) {
						data1
								.setCaseID(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getCaseID()) {
						data2
								.setCaseID(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getCaseID().compareTo(
							data2.getCaseID()) : data2.getCaseID().compareTo(
							data1.getCaseID());
				}
				if (MaintainContactManagementUIConstants.EXISTINGCASE_CREATEDDATE
						.equals(sortColumn)) {
					if (null == data1.getCreatedDateStr()) {
						data1
								.setCreatedDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getCreatedDateStr()) {
						data2
								.setCreatedDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getCreatedDateStr().trim()
							.compareTo(data2.getCreatedDateStr().trim())
							: data2.getCreatedDateStr().trim().compareTo(
									data1.getCreatedDateStr().trim());
				}
				if (MaintainContactManagementUIConstants.EXISTINGCASE_STATUS
						.equals(sortColumn)) {
					if (null == data1.getStatus()) {
						data1
								.setStatus(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getStatus()) {
						data2
								.setStatus(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getStatus().trim().compareTo(
							data2.getStatus().trim()) : data2.getStatus()
							.trim().compareTo(data1.getStatus().trim());
				}
				if (MaintainContactManagementUIConstants.EXISTINGCASE_CASETYPE
						.equals(sortColumn)) {
					if (null == data1.getCaseType()) {
						data1
								.setCaseType(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getCaseType()) {
						data2
								.setCaseType(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getCaseType().trim().compareTo(
							data2.getCaseType().trim()) : data2.getCaseType()
							.trim().compareTo(data1.getCaseType().trim());
				}
				return 0;
			}
		};
		Collections.sort(dataList, comparator);
	}

	/**
	 * This method creates a new Comparator Class and overrides the int
	 * compare() to sort the Case Routing in the Databean.
	 * 
	 * @param sortColumn
	 *            String : name of the column to de sort.
	 * @param sortOrder
	 *            String : Ordering of the sort (asc / desc)
	 * @param dataList
	 *            List : List to be sort.
	 */
	public void sortCaseRoutingComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		log.debug("sortCaseRoutingComparator  started");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CMRoutingVO data1 = (CMRoutingVO) obj1;
				CMRoutingVO data2 = (CMRoutingVO) obj2;

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
				if (MaintainContactManagementUIConstants.CASEROUTING_DATE
						.equals(sortColumn)) {
					//for defect ESPRD00844065 starts
					try {
						Date begindate1 = ContactManagementHelper
								.getOpenEndedDate();
						Date begindate2 = ContactManagementHelper
								.getOpenEndedDate();
						DateFormat dateFormat = new SimpleDateFormat(
								ContactManagementConstants.DATE_ADDED_FORMAT);
						if (!isNullOrEmptyField(data1.getRoutingDateStr())) {
							begindate1 = dateFormat.parse(data1
									.getRoutingDateStr());
						}
						if (!isNullOrEmptyField(data2.getRoutingDateStr())) {
							begindate2 = dateFormat.parse(data2
									.getRoutingDateStr());
						}
						return ascending ? begindate1.compareTo(begindate2)
								: begindate2.compareTo(begindate1);
					} catch (Exception exc) {
						log.error(exc.getMessage());
					}
					//for defect ESPRD00844065 ends
				}
				if (MaintainContactManagementUIConstants.CASEROUTING_ROUTEDBY
						.equals(sortColumn)) {
					if (null == data1.getRoutedBy()) {
						data1
								.setRoutedBy(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getRoutedBy()) {
						data2
								.setRoutedBy(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getRoutedBy().trim().compareToIgnoreCase(
							data2.getRoutedBy().trim()) : data2.getRoutedBy()
							.trim().compareToIgnoreCase(data1.getRoutedBy().trim());
				}
				//changed for the defect 731105 
				if (MaintainContactManagementUIConstants.CASEROUTING_ROUTEDTO
						.equals(sortColumn)) {
					if (null == data1.getRoutedToName()) {
						data1
								.setRoutedToName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getRoutedToName()) {
						data2
								.setRoutedToName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getRoutedToName().trim().compareToIgnoreCase(
							data2.getRoutedToName().trim()) : data2.getRoutedToName()
							.trim().compareToIgnoreCase(data1.getRoutedToName().trim());
				}
				//changed end for the defect 731105 
				if (MaintainContactManagementUIConstants.CASEROUTING_WATCHLIST
						.equals(sortColumn)) {
					if (null == data1.getWatchList()) {
						data1
								.setWatchList(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getWatchList()) {
						data2
								.setWatchList(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getWatchList().trim().compareTo(
							data2.getWatchList().trim()) : data2.getWatchList()
							.trim().compareTo(data1.getWatchList().trim());
				}
				return 0;
			}
		};
		Collections.sort(dataList, comparator);
	}

	/**
	 * This method creates a new Comparator Class and overrides the int
	 * compare() to sort the Case Regarding Provider in the Databean.
	 * 
	 * @param sortColumn
	 *            String : name of the column to de sort.
	 * @param sortOrder
	 *            String : Ordering of the sort (asc / desc)
	 * @param dataList
	 *            List : List to be sort.
	 */
	public void sortCaseRegardingProviderComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		log.debug("sortCaseRegardingProviderComparator started");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				AlternateIdentifiersVO data1 = (AlternateIdentifiersVO) obj1;
				AlternateIdentifiersVO data2 = (AlternateIdentifiersVO) obj2;

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
				if (MaintainContactManagementUIConstants.CASEREGARDING_ALTIDEN_PROV_TYPE
						.equals(sortColumn)) {
					if (null == data1.getAlternateIDTypeCode()) {
						data1
								.setAlternateIDTypeCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getAlternateIDTypeCode()) {
						data2
								.setAlternateIDTypeCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getAlternateIDTypeCode()
							.compareTo(data2.getAlternateIDTypeCode()) : data2
							.getAlternateIDTypeCode().compareTo(
									data1.getAlternateIDTypeCode());
				}
				if (MaintainContactManagementUIConstants.CASEREGARDING_ALTIDEN_PROV_ALTID
						.equals(sortColumn)) {
					if (null == data1.getAlternateID()) {
						data1
								.setAlternateID(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getAlternateID()) {
						data2
								.setAlternateID(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getAlternateID().trim().compareTo(
							data2.getAlternateID().trim()) : data2
							.getAlternateID().trim().compareTo(
									data1.getAlternateID().trim());
				}
				if (MaintainContactManagementUIConstants.CASEREGARDING_ALTIDEN_PROV_LOB
						.equals(sortColumn)) {
					if (null == data1.getLineOfBusiness()) {
						data1
								.setLineOfBusiness(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getLineOfBusiness()) {
						data2
								.setLineOfBusiness(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					return ascending ? data1.getLineOfBusiness().trim()
							.compareTo(data2.getLineOfBusiness().trim())
							: data2.getLineOfBusiness().trim().compareTo(
									data1.getLineOfBusiness().trim());
				}
				return 0;
			}
		};
		Collections.sort(dataList, comparator);
	}

	/**
	 * This method creates a new Comparator Class and overrides the int
	 * compare() to sort the Case Regarding Member in the Databean.
	 * 
	 * @param sortColumn
	 *            String : name of the column to de sort.
	 * @param sortOrder
	 *            String : Ordering of the sort (asc / desc)
	 * @param dataList
	 *            List : List to be sort.
	 */
	public void sortCaseRegardingMemberComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		log.debug("sortCaseRegardingMemberComparator  started");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				AlternateIdentifiersVO data1 = (AlternateIdentifiersVO) obj1;
				AlternateIdentifiersVO data2 = (AlternateIdentifiersVO) obj2;

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
				if (MaintainContactManagementUIConstants.CASEREGARDING_ALTIDEN_MEM_TYPE
						.equals(sortColumn)) {
					if (null == data1.getAlternateIDTypeCode()) {
						data1
								.setAlternateIDTypeCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getAlternateIDTypeCode()) {
						data2
								.setAlternateIDTypeCode(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getAlternateIDTypeCode()
							.compareTo(data2.getAlternateIDTypeCode()) : data2
							.getAlternateIDTypeCode().compareTo(
									data1.getAlternateIDTypeCode());
				}
				if (MaintainContactManagementUIConstants.CASEREGARDING_ALTIDEN_MEM_ALTID
						.equals(sortColumn)) {
					if (null == data1.getAlternateID()) {
						data1
								.setAlternateID(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getAlternateID()) {
						data2
								.setAlternateID(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getAlternateID().trim().compareTo(
							data2.getAlternateID().trim()) : data2
							.getAlternateID().trim().compareTo(
									data1.getAlternateID().trim());
				}
				if (MaintainContactManagementUIConstants.CASEREGARDING_ALTIDEN_MEM_LOB
						.equals(sortColumn)) {
					if (null == data1.getLineOfBusiness()) {
						data1
								.setLineOfBusiness(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getLineOfBusiness()) {
						data2
								.setLineOfBusiness(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getLineOfBusiness().trim()
							.compareTo(data2.getLineOfBusiness().trim())
							: data2.getLineOfBusiness().trim().compareTo(
									data1.getLineOfBusiness().trim());
				}
				return 0;
			}
		};
		Collections.sort(dataList, comparator);
	}

	/**
	 * This method creates a new Comparator Class and overrides the int
	 * compare() to sort the Case Events in the Databean.
	 * 
	 * @param sortColumn
	 *            String : name of the column to de sort.
	 * @param sortOrder
	 *            String : Ordering of the sort (asc / desc)
	 * @param dataList
	 *            List : List to be sort.
	 */
	public void sortCaseEventsComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		log.debug("sortCaseEventsComparator @  started");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CaseEventsVO data1 = (CaseEventsVO) obj1;
				CaseEventsVO data2 = (CaseEventsVO) obj2;

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
				if (MaintainContactManagementUIConstants.CASEEVENT_DESC
						.equals(sortColumn)) {
					if (null == data1.getDesc()) {
						data1
								.setDesc(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDesc()) {
						data2
								.setDesc(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getDesc().compareToIgnoreCase(
							data2.getDesc()) : data2.getDesc().compareToIgnoreCase(
							data1.getDesc());
				}
				if (MaintainContactManagementUIConstants.CASEEVENT_DATE
						.equals(sortColumn)) {
					if (null == data1.getEventDateStr()) {
						data1
								.setEventDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getEventDateStr()) {
						data2
								.setEventDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getEventDateStr().trim()
							.compareTo(data2.getEventDateStr().trim()) : data2
							.getEventDateStr().trim().compareTo(
									data1.getEventDateStr().trim());
				}
				if (MaintainContactManagementUIConstants.CASEEVENT_TIME
						.equals(sortColumn)) {
					if (null == data1.getTime()) {
						data1
								.setTime(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getTime()) {
						data2
								.setTime(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getTime().trim().compareTo(
							data2.getTime().trim()) : data2.getTime().trim()
							.compareTo(data1.getTime().trim());
				}
				if (MaintainContactManagementUIConstants.CASEEVENT_STATUS
						.equals(sortColumn)) {
					if (null == data1.getStatusCd()) {
						data1
								.setStatusCd(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getStatusCd()) {
						data2
								.setStatusCd(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					return ascending ? data1.getStatusCd().trim().compareTo(
							data2.getStatusCd().trim()) : data2.getStatusCd()
							.trim().compareTo(data1.getStatusCd().trim());
				}
				if (MaintainContactManagementUIConstants.CASEEVENTS_DISDATE
						.equals(sortColumn)) {
					if (null == data1.getDispositionDateStr()) {
						data1
								.setDispositionDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getDispositionDateStr()) {
						data2
								.setDispositionDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					return ascending ? data1.getDispositionDateStr().trim()
							.compareTo(data2.getDispositionDateStr().trim())
							: data2.getDispositionDateStr().trim().compareTo(
									data1.getDispositionDateStr().trim());
				}
				//changed end for the defect 731105
				if (MaintainContactManagementUIConstants.CASEEVENTS_NOTIFYVIAALERT
						.equals(sortColumn)) {
					if (null == data1.getNotifyViaAlertName()) {
						data1
								.setNotifyViaAlertName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getNotifyViaAlertName()) {
						data2
								.setNotifyViaAlertName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					return ascending ? data1.getNotifyViaAlertName().trim()
							.compareToIgnoreCase(data2.getNotifyViaAlertName().trim())
							: data2.getNotifyViaAlertName().trim().compareToIgnoreCase(
									data1.getNotifyViaAlertName().trim());
				}
				//changed for the defect 731105 
				if (MaintainContactManagementUIConstants.CASEEVENTS_ALERTBASEDON
						.equals(sortColumn)) {
					if (null == data1.getAlertBasedOn()) {
						data1
								.setAlertBasedOn(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getAlertBasedOn()) {
						data2
								.setAlertBasedOn(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					return ascending ? data1.getAlertBasedOn().trim()
							.compareTo(data2.getAlertBasedOn().trim()) : data2
							.getAlertBasedOn().trim().compareTo(
									data1.getAlertBasedOn().trim());
				}
			 
				if (MaintainContactManagementUIConstants.CASEEVENTS_SENDALERTDAYS
						.equals(sortColumn)) {
					if (null == data1.getSendAlertDaysCd()) {
						data1
								.setSendAlertDaysCd(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getSendAlertDaysCd()) {
						data2
								.setSendAlertDaysCd(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					return ascending ? data1.getSendAlertDaysCd().trim()
							.compareTo(data2.getSendAlertDaysCd().trim())
							: data2.getSendAlertDaysCd().trim().compareTo(
									data1.getSendAlertDaysCd().trim());
				}

				return 0;
			}
		};
		Collections.sort(dataList, comparator);
	}

	/**
	 * This method creates a new Comparator Class and overrides the int
	 * compare() to sort the Case Steps in the Databean.
	 * 
	 * @param sortColumn
	 *            String : name of the column to de sort.
	 * @param sortOrder
	 *            String : Ordering of the sort (asc / desc)
	 * @param dataList
	 *            List : List to be sort.
	 */
	public void sortCaseStepsComparator(final String sortColumn,
			final String sortOrder, List dataList) {

		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CaseStepsVO data1 = (CaseStepsVO) obj1;
				CaseStepsVO data2 = (CaseStepsVO) obj2;

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
				if (MaintainContactManagementUIConstants.CASESTEPS_ORDER
						.equals(sortColumn)) {
					Integer order1Int = Integer.valueOf(0); /* FIND BUGS_FIX */
					Integer order2Int = Integer.valueOf(0); /* FIND BUGS_FIX */
					if (null == data1.getOrder()) {
						data1
								.setOrder(MaintainContactManagementUIConstants.EMPTY_STRING);
					} else {
						order1Int = new Integer(data1.getOrder());
					}
					if (null == data2.getOrder()) {
						data2
								.setOrder(MaintainContactManagementUIConstants.EMPTY_STRING);
					} else {
						order2Int = new Integer(data2.getOrder());
					}
					/*
					 * return ascending ? data1.getOrder().trim().compareTo(
					 * data2.getOrder().trim()) : data2.getOrder().trim()
					 * .compareTo(data1.getOrder().trim());
					 */

					/*
					 * Changed to Integer comparision because this is the actual
					 * datatype for order number
					 */
					return ascending ? order1Int.compareTo(order2Int)
							: order2Int.compareTo(order1Int);
				}
				if (MaintainContactManagementUIConstants.CASESTEPS_DESC
						.equals(sortColumn)) {
					if (null == data1.getDescription()) {
						data1
								.setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDescription()) {
						data2
								.setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getDescription().trim().compareToIgnoreCase(
							data2.getDescription().trim()) : data2
							.getDescription().trim().compareToIgnoreCase(
									data1.getDescription().trim());
				}
				if (MaintainContactManagementUIConstants.CASESTEPS_ROUTEDTO
						.equals(sortColumn)) {
					if (null == data1.getRouteTo()) {
						data1
								.setRouteTo(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getRouteTo()) {
						data2
								.setRouteTo(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getRouteTo().trim().compareToIgnoreCase(
							data2.getRouteTo().trim()) : data2.getRouteTo()
							.trim().compareToIgnoreCase(data1.getRouteTo().trim());
				}
				if (MaintainContactManagementUIConstants.CASESTEPS_EXPTOCOMP
						.equals(sortColumn)) {
					if (null == data1.getExpectedDaysToComplete()) {
						data1
								.setExpectedDaysToComplete(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getExpectedDaysToComplete()) {
						data2
								.setExpectedDaysToComplete(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getExpectedDaysToComplete().trim()
							.compareTo(data2.getExpectedDaysToComplete())
							: data2.getExpectedDaysToComplete().trim()
									.compareTo(
											data1.getExpectedDaysToComplete());
				}
				if (MaintainContactManagementUIConstants.CASESTEPS_COMBASEDON
						.equals(sortColumn)) {
					if (null == data1.getCompletedBasedOn()) {
						data1
								.setCompletedBasedOn(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getCompletedBasedOn()) {
						data2
								.setCompletedBasedOn(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getCompletedBasedOn().trim()
							.compareTo(data2.getCompletedBasedOn()) : data2
							.getCompletedBasedOn().trim().compareTo(
									data1.getCompletedBasedOn());
				}
				if (MaintainContactManagementUIConstants.CASESTEPS_STATUS
						.equals(sortColumn)) {
					if (null == data1.getStatus()) {
						data1
								.setStatus(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getStatus()) {
						data2
								.setStatus(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getStatus().trim().compareTo(
							data2.getStatus()) : data2.getStatus().trim()
							.compareTo(data1.getStatus());
				}
				if (MaintainContactManagementUIConstants.CASESTEPS_DATESTARTED
						.equals(sortColumn)) {
					if (null == data1.getDateStartedStr()) {
						data1
								.setDateStartedStr(MaintainContactManagementUIConstants.EMPTY_STRING);

					}
					if (null == data2.getDateStartedStr()) {
						data2
								.setDateStartedStr(MaintainContactManagementUIConstants.EMPTY_STRING);

					}
					return ascending ? data1.getDateStartedStr().compareTo(
							data2.getDateStartedStr()) : data2
							.getDateStartedStr().compareTo(
									data1.getDateStartedStr());
				}
				if (MaintainContactManagementUIConstants.CASESTEPS_EXPCOMPDATE
						.equals(sortColumn)) {
					if (null == data1.getExpectedCompletionDateStr()) {
						data1
								.setExpectedCompletionDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);

					}
					if (null == data2.getExpectedCompletionDateStr()) {
						data2
								.setExpectedCompletionDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);

					}
					return ascending ? data1.getExpectedCompletionDateStr()
							.compareTo(data2.getExpectedCompletionDateStr())
							: data2.getExpectedCompletionDateStr().compareTo(
									data1.getExpectedCompletionDateStr());
				}
				if (MaintainContactManagementUIConstants.CASESTEPS_COMPDATE
						.equals(sortColumn)) {
					if (null == data1.getCompletionDateStr()) {
						data1
								.setCompletionDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getCompletionDateStr()) {
						data2
								.setCompletionDateStr(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getCompletionDateStr().compareTo(
							data2.getCompletionDateStr()) : data2
							.getCompletionDateStr().compareTo(
									data1.getCompletionDateStr());
				}
				//changed for the defect 731105 
				if (MaintainContactManagementUIConstants.CASESTEPS_NOTIFYALERT
						.equals(sortColumn)) {
					if (null == data1.getNotifyAlertDescription()) {
						data1
								.setNotifyAlertDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getNotifyAlertDescription()) {
						data2
								.setNotifyAlertDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getNotifyAlertDescription().compareToIgnoreCase(
							data2.getNotifyAlertDescription()) : data2
							.getNotifyAlertDescription().compareToIgnoreCase(
									data1.getNotifyAlertDescription());

				}
				//changed end for the defect 731105 
				if (MaintainContactManagementUIConstants.CASESTEPS_TEMPLATE
						.equals(sortColumn)) {
					if (null == data1.getTemplate()) {
						data1
								.setTemplate(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getTemplate()) {
						data2
								.setTemplate(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getTemplate().compareToIgnoreCase(
							data2.getTemplate()) : data2.getTemplate()
							.compareToIgnoreCase(data1.getTemplate());
				}
				return 0;
			}
		};
		Collections.sort(dataList, comparator);
	}

	/**
	 * This method creates a new Comparator Class and overrides the int
	 * compare() to sort the Case Alerts in the Databean.
	 * 
	 * @param sortColumn
	 *            String : name of the column to de sort.
	 * @param sortOrder
	 *            String : Ordering of the sort (asc / desc)
	 * @param dataList
	 *            List : List to be sort.
	 */
	public void sortCaseAlertsComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		log.debug("sortCaseAlertsComparator  started");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				AlertVO data1 = (AlertVO) obj1;
				AlertVO data2 = (AlertVO) obj2;

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
				if (MaintainContactManagementUIConstants.CASEALERTS_DUEDATE
						.equals(sortColumn)) {
					if (null == data1.getDueDate()) {
						data1.setDueDate(ContactManagementHelper
								.getOpenEndedDate());
					}
					if (null == data2.getDueDate()) {
						data1.setDueDate(ContactManagementHelper
								.getOpenEndedDate());
					}
					return ascending ? data1.getDueDate().compareTo(
							data2.getDueDate()) : data2.getDueDate().compareTo(
							data1.getDueDate());
				}
				if (MaintainContactManagementUIConstants.CASEALERTS_ALERTSTYPE
						.equals(sortColumn)) {
					if (null == data1.getAlertType()) {
						data1
								.setAlertType(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getAlertType()) {
						data2
								.setAlertType(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getAlertType().trim().compareTo(
							data2.getAlertType().trim()) : data2.getAlertType()
							.trim().compareTo(data1.getAlertType().trim());
				}
				if (MaintainContactManagementUIConstants.CASEALERTS_DESCRIPTION
						.equals(sortColumn)) {
					if (null == data1.getDescription()) {
						data1
								.setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getDescription()) {
						data2
								.setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getDescription().trim().compareToIgnoreCase(
							data2.getDescription().trim()) : data2
							.getDescription().trim().compareToIgnoreCase(
									data1.getDescription().trim());
				}
				//changed  for the defect 731105 
				if (MaintainContactManagementUIConstants.CASEALERTS_NOTIFYVIAALERT
						.equals(sortColumn)) {
					if (null == data1.getNotifyUserName()) {
						data1
								.setNotifyUserName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getNotifyUserName()) {
						data2
								.setNotifyUserName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getNotifyUserName().trim()
							.compareToIgnoreCase(data2.getNotifyUserName().trim())
							: data2.getNotifyUserName().trim().compareToIgnoreCase(
									data1.getNotifyUserName().trim());
				}
				//changed end for the defect 731105 
				if (MaintainContactManagementUIConstants.CASEALERT_STATUS
						.equals(sortColumn)) {
					if (null == data1.getStatus()) {
						data1
								.setStatus(MaintainContactManagementUIConstants.EMPTY_STRING);
					}

					if (null == data2.getStatus()) {
						data2
								.setStatus(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getStatus().trim().compareTo(
							data2.getStatus().trim()) : data2.getStatus()
							.trim().compareTo(data1.getStatus().trim());
				}

				return 0;
			}
		};
		Collections.sort(dataList, comparator);
	}

	/**
	 * This method creates a new Comparator Class and overrides the int
	 * compare() to sort the Case Attachments in the Databean.
	 * 
	 * @param sortColumn
	 *            String : name of the column to de sort.
	 * @param sortOrder
	 *            String : Ordering of the sort (asc / desc)
	 * @param dataList
	 *            List : List to be sort.
	 */
	public void sortCaseAttachmentsComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		log.debug("sortCaseAttachmentsComparator  started");
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				AttachmentVO data1 = (AttachmentVO) obj1;
				AttachmentVO data2 = (AttachmentVO) obj2;

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
				if (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_DATEADDED
						.equals(sortColumn)) {
					Date dateadded1=ContactManagementHelper.getOpenEndedDate();
					Date dateadded2=ContactManagementHelper.getOpenEndedDate();
					DateFormat dateFormat = new SimpleDateFormat(
                    "MM/dd/yyyy hh:mm a");
					System.out.println("data1.getDateAdded() $$$$$$$$$"+data1.getDateAdded());
					System.out.println("data2.getDateAdded() $$$$$$$$$"+data2.getDateAdded());
					try {
						if (null == data1.getDateAdded()) {
							data1
									.setDateAdded(MaintainContactManagementUIConstants.EMPTY_STRING);
						} else {
							dateadded1 = dateFormat.parse(data1.getDateAdded());
						}
						if (null == data2.getDateAdded()) {
							data2
									.setDateAdded(MaintainContactManagementUIConstants.EMPTY_STRING);
						} else {
							dateadded2 = dateFormat.parse(data2.getDateAdded());
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("dateadded1 $$$$$$$$$"+dateadded1);
					System.out.println("dateadded2 $$$$$$$$"+dateadded2);
					return ascending ? dateadded1.compareTo(
							dateadded2) : dateadded2
							.compareTo(dateadded1);
				}
				if (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_ADDEDBY
						.equals(sortColumn)) {
					if (null == data1.getAddedBy()) {
						data1
								.setAddedBy(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getAddedBy()) {
						data2
								.setAddedBy(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getAddedBy().trim().compareToIgnoreCase(
							data2.getAddedBy().trim()) : data2.getAddedBy()
							.trim().compareToIgnoreCase(data1.getAddedBy().trim());
				}
				if (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_FILENAME
						.equals(sortColumn)) {
					if (null == data1.getFileName()) {
						data1
								.setFileName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getFileName()) {
						data2
								.setFileName(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getFileName().trim().compareToIgnoreCase(
							data2.getFileName().trim()) : data2.getFileName()
							.trim().compareToIgnoreCase(data1.getFileName().trim());
				}
				if (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_DESC
						.equals(sortColumn)) {
					if (null == data1.getDescription()) {
						data1
								.setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					if (null == data2.getDescription()) {
						data2
								.setDescription(MaintainContactManagementUIConstants.EMPTY_STRING);
					}
					return ascending ? data1.getDescription().trim().compareToIgnoreCase(
							data2.getDescription().trim()) : data2
							.getDescription().trim().compareToIgnoreCase(
									data1.getDescription().trim());
				}
				return 0;
			}
		};
		Collections.sort(dataList, comparator);
	}

	/**
	 * 
	 * @param tempSet
	 */
	public void setTemplates(Set tempSet) {
		System.err.println("inside;:setTemplates::::::::::mk::");
		Iterator iter = tempSet.iterator();
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//UC-PGM-CRM-018.4_ESPRD00351624_17DEC09
		if (logCaseDataBean.getTemplateList().size() == 0)
			logCaseDataBean.getTemplateList().add(
					new SelectItem(
							MaintainContactManagementUIConstants.EMPTY_STRING_SPACE,
							MaintainContactManagementUIConstants.EMPTY_STRING));
		// Modified for defect ESPRD00542559 starts
		CaseTypeTemplate caseTypeTemplate; //EOF
		// UC-PGM-CRM-018.4_ESPRD00351624_17DEC09
		List letterTemplateKeys = new ArrayList();

		// Begin - modified the code for the Defect ESPRD00699364
		while (iter.hasNext()) {

			caseTypeTemplate = (CaseTypeTemplate) iter.next();//UC-PGM-CRM-018.4_ESPRD00351624_17DEC09

			if (caseTypeTemplate.getLetterTemplateKeyData() != null)//UC-PGM-CRM-018.4_ESPRD00351624_17DEC09
			{
				letterTemplateKeys.add(caseTypeTemplate
						.getLetterTemplateKeyData());
				logCaseDataBean.getTemplateList().add(new SelectItem(caseTypeTemplate
						.getLetterTemplateKeyData(), caseTypeTemplate.getLetterTemplate().getName()));
			}
			/*
			 * if (caseTypeTemplate.getLetterTemplateKeyData() !=
			 * null)//UC-PGM-CRM-018.4_ESPRD00351624_17DEC09 {
			 * logCaseDataBean.getTemplateList().add( new
			 * SelectItem(caseTypeTemplate .getLetterTemplateKeyData(),
			 * caseTypeTemplate .getLetterTemplateKeyData())); }
			 */
		}
		//Collections.sort(letterTemplateKeys);
		Comparator sorCompare =new Comparator(){

			@Override
			public int compare(Object object1, Object object2) {
				// TODO Auto-generated method stub
				SelectItem selectItem1=(SelectItem)object1;
				SelectItem selectItem2=(SelectItem)object2;
				if(selectItem1.getLabel()==null){
					selectItem1.setLabel(ContactManagementConstants.EMPTY_STRING);
				}
				if(selectItem2.getLabel()==null){
					selectItem2.setLabel(ContactManagementConstants.EMPTY_STRING);
				}
				return selectItem1.getLabel().compareTo(selectItem2.getLabel());
			}};
		Collections.sort(logCaseDataBean.getTemplateList(),sorCompare);
		// End - modified the code for the Defect ESPRD00699364
		
		// Begin - comment for Defect ESPRD00699364
		/*for (Iterator iterator = letterTemplate.iterator(); iterator
				.hasNext();) {
			//String letterTemplateKey = (String) iterator.next();
			String letterTemplateValue = (String) iterator.next();
			logCaseDataBean.getTemplateList().add(new SelectItem("", letterTemplateValue));
		}*/
		// End - comment for Defect ESPRD00699364		

		// modified for defect ESPRD00542559 Ends

	}

	/**
	 * 
	 * @param record
	 * @param detailsVO
	 * @return
	 */
	public CaseRecord setStatusDate(CaseRecord record, CaseDetailsVO detailsVO) {
		if (!detailsVO.getStatus().equals(detailsVO.getPreviousStatus())) {
			record.setCaseStatusDate(new Date());
		} else {
			record.setCaseStatusDate(detailsVO.getStatusDate());
		}
		return record;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getSupWiserRewReqInd() {
		boolean ind = false;
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if (logCaseDataBean.isSuperviserRevReqInd()
				|| logCaseDataBean.isSuperviserRevReqIndForCaseType()) {
			ind = true;
		}
		return ind;
	}

	/**
	 * 
	 * @param bu
	 * @param shortDesc
	 */
	public void enableCaseTypeFields(String bu, String shortDesc) {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		if ("A".equals(bu)) {
			if (shortDesc.equals("ARS")) {
				if (!"CaseUpdate".equals(logCaseDataBean.getActionCode())) {
					logCaseDataBean.getCaseDetailsVO().setCaseTypeARSVO(
							new CaseTypeARSVO());
				}
				logCaseDataBean.setEnableRaterSetting(true);
				logCaseDataBean.setEnablePictureDate(false);
				logCaseDataBean.setEnablePayRatesFields(false);
				logCaseDataBean.setEnableHomeOfficeInd(false);
				logCaseDataBean.setEnableFieldAuditFields(false);
				logCaseDataBean.setEnableStateFiscal(false);
			} else if (shortDesc.equals("FCR")) {
				if (!"CaseUpdate".equals(logCaseDataBean.getActionCode())) {
					logCaseDataBean.getCaseDetailsVO().setCaseTypeARSVO(
							new CaseTypeARSVO());
				}
				logCaseDataBean.setEnableRaterSetting(false);
				logCaseDataBean.setEnablePictureDate(true);
				logCaseDataBean.setEnablePayRatesFields(false);
				logCaseDataBean.setEnableHomeOfficeInd(true);
				logCaseDataBean.setEnableFieldAuditFields(true);
				logCaseDataBean.setEnableStateFiscal(true);
			} else if (shortDesc.equals("HOCR")) {
				if (!"CaseUpdate".equals(logCaseDataBean.getActionCode())) {
					logCaseDataBean.getCaseDetailsVO().setCaseTypeARSVO(
							new CaseTypeARSVO());
				}
				logCaseDataBean.setEnableRaterSetting(false);
				logCaseDataBean.setEnablePictureDate(false);
				logCaseDataBean.setEnablePayRatesFields(false);
				logCaseDataBean.setEnableHomeOfficeInd(true);
				logCaseDataBean.setEnableFieldAuditFields(false);
				logCaseDataBean.setEnableStateFiscal(true);
			} else if (shortDesc.equals("FPPR")) {
				if (!"CaseUpdate".equals(logCaseDataBean.getActionCode())) {
					logCaseDataBean.getCaseDetailsVO().setCaseTypeARSVO(
							new CaseTypeARSVO());
				}
				logCaseDataBean.setEnableRaterSetting(false);
				logCaseDataBean.setEnablePictureDate(false);
				logCaseDataBean.setEnablePayRatesFields(true);
				logCaseDataBean.setEnableHomeOfficeInd(false);
				logCaseDataBean.setEnableFieldAuditFields(false);
				logCaseDataBean.setEnableStateFiscal(false);
			}
		} else if ("B".equals(bu)) {
			if (shortDesc.equals("BCCPBConfr")
					|| shortDesc.equals("BCCPBPrsum")) {
				logCaseDataBean.setEnableTumorAndNodes(false);
			} else {
				logCaseDataBean.setEnableTumorAndNodes(true);
			}
		}
	}

	/**
	 * Added by Madhurima
	 * 
	 * @param detailsVO
	 * @return
	 */
	/*
	 * public boolean execute505Rule(CaseDetailsVO detailsVO) {
	 * log.debug("Inside execute505Rule"); logCaseDataBean = (CMLogCaseDataBean)
	 * getDataBean(CMLOGCASE_DATA_BEAN); CaseDelegate caseDelegate = new
	 * CaseDelegate(); CaseRegardingMemberVO regardingMemberVO = logCaseDataBean
	 * .getCaseRegardingVO().getCaseRegardingMemberVO(); boolean execFlag =
	 * true; if
	 * ("D".equalsIgnoreCase(detailsVO.getCaseTypeBusinessUnitTypeCode()) &&
	 * "MID".equals(regardingMemberVO.getMemberIDType()) &&
	 * "M".equals(regardingMemberVO.getEntityType())) {
	 * 
	 * String shortDesc = ContactManagementHelper.setShortDescription(
	 * FunctionalAreaConstants.GENERAL,
	 * ReferenceServiceDataConstants.G_CASE_STAT_CD, detailsVO .getStatus()); if
	 * (!shortDesc.startsWith("C")) { List listOfCases = null; try {
	 * log.debug("In try BLock execute505Rule and CE SK $$ " +
	 * regardingMemberVO.getCommonEntitySK()); listOfCases =
	 * caseDelegate.getCases(Long
	 * .valueOf(regardingMemberVO.getCommonEntitySK())); if (listOfCases != null &&
	 * !listOfCases.isEmpty()) { log.debug("listOfCases size is :" +
	 * listOfCases.size()); execFlag = false; } } catch (Exception e) {
	 * log.debug("Inside CatchBlock"); e.printStackTrace(); } } } return
	 * execFlag; }
	 */
	/* Ended By Madhruima */

	/**
	 * @param caseSK
	 * @return
	 */
	/*
	 * public boolean execute441Rule(Long caseSK) { Boolean isAppealExists;
	 * boolean flag = false; AppealDelegate appealDelegate = new
	 * AppealDelegate(); try { isAppealExists =
	 * appealDelegate.isAppealExistsForCase(caseSK); flag =
	 * isAppealExists.booleanValue(); if (flag) { flag = true; } else { flag =
	 * false; } } catch (AppealFetchBusinessException e) { log.error( "Error
	 * occured while checking existing appeals for a Case", e); } return flag; }
	 */

	/**
	 * @param eventsList
	 * @return
	 */
	public boolean isDisposotionDateIsSetToEvents(List eventsList) {
		log.debug("verifying Disposition date is started");
		boolean disFlag = false;
		CaseEventsVO eventsVO = null;

		if (eventsList != null && !eventsList.isEmpty()) {
			int size = 0;

			size = eventsList.size();
			for (int i = 0; i < size; i++) {

				eventsVO = (CaseEventsVO) eventsList.get(i);
				if ("D".equals(eventsVO.getEventTypeCd())) {
					log.debug("CaseEvents statusCode is D");
					if (eventsVO.getDispositionDateStr() == null
							|| eventsVO.getDispositionDateStr().equals("")) {
						log.debug("we found no disposition date");
						disFlag = true;
					}
				}
			}
		}
		return disFlag;
	}

	/**
	 * 
	 * @param caseEventsVO
	 * @return
	 */
	public boolean isAlertBasedOnLessThanSysDate(CaseEventsVO caseEventsVO) {
		//ContactManagementHelper helper = new ContactManagementHelper();
		boolean vFlag = false;
		Date date = null;
		Date finalDate = null;
		boolean incommingDays = false;
		int days = 0;
		String sendDays;
		Calendar calendar = Calendar.getInstance();

		if (!isNullOrEmptyField(caseEventsVO.getAlertBasedOn())) {
			if (!isNullOrEmptyField(caseEventsVO.getSendAlertDaysCd())) {
				sendDays = caseEventsVO.getSendAlertDaysCd();
				log.debug("Send Alert days are $$ " + sendDays);
				days = Integer.valueOf(sendDays).intValue();
				incommingDays = true;
			}
			/** for defect ESPRD00847982
			 *  event date not null check validated
			 * */
			if (ContactManagementConstants.P.equals(caseEventsVO
					.getAlertBasedOn())
					&& incommingDays
					&& !isNullOrEmptyField(caseEventsVO.getEventDateStr())) {
				date = ContactManagementHelper.dateConverter(caseEventsVO.getEventDateStr());
				
				calendar.setTime(date);
				if (ContactManagementConstants.DFLT_BEFORE_CODE.equals(caseEventsVO.getAfterOrBeforeCd())) {
					calendar.add(Calendar.DATE, -days);
				}
				if (ContactManagementConstants.DFLT_AFTER_CODE.equals(caseEventsVO.getAfterOrBeforeCd())) {
					calendar.add(Calendar.DATE, days);
				}
				finalDate = calendar.getTime();
			} else if (ContactManagementConstants.CREATE_DATE_CODE.equals(caseEventsVO.getAlertBasedOn())
					&& incommingDays) {

				if (caseEventsVO.getAddedAuditTimeStamp() != null) {
					date = caseEventsVO.getAddedAuditTimeStamp();

					calendar.setTime(date);

				}

				if (ContactManagementConstants.DFLT_BEFORE_CODE.equals(caseEventsVO.getAfterOrBeforeCd())) {
					calendar.add(Calendar.DATE, -days);
				}
				if (ContactManagementConstants.DFLT_AFTER_CODE.equals(caseEventsVO.getAfterOrBeforeCd())) {
					calendar.add(Calendar.DATE, days);
				}
				finalDate = calendar.getTime();
			}

			if (finalDate != null
					&& !EnterpriseCommonValidator.compareLesserDate(new Date(),
							finalDate)) {
				vFlag = true;
			}
		}
		return vFlag;
	}

	public void restoreCaseState() {
		log.info("restoreCaseState() is started");
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		//DEFECT 535752

		CommonEntityDataBean commonEntityDataBean = (CommonEntityDataBean) getDataBean(ContactManagementConstants.COMMON_ENTITY_DATA_BEAN);
		//Added for the defect ESPRD00902780
		CaseSearchDataBean caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.CASE_SEARCH_DATA_BEAN);
		//test 535752

		//for defect ESPRD00677122 starts
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) context.getExternalContext()
				.getSession(true);
		httpSession.removeAttribute("DispositionDate");
		//for defect ESPRD00677122 ends

		//for fixing defect ESPRD00694681
		logCaseDataBean.setCursorFocusValue(MaintainContactManagementUIConstants.EMPTY_STRING); // Added for the defect ESPRD00697332_14DEC2011
		logCaseDataBean.setCursorFocusInquiry(MaintainContactManagementUIConstants.EMPTY_STRING); // Added for the defect ESPRD00697332_14DEC2011
		LettersAndResponsesDataBean lrDataBean = getLetterDataBean();
		lrDataBean.setLetterGenerationRequests(new ArrayList());

		logCaseDataBean.setCaseRegardingVO(new CaseRegardingVO());
		logCaseDataBean.setCaseDetailsVO(new CaseDetailsVO());
		logCaseDataBean.setRoutingVO(new CMRoutingVO());
		logCaseDataBean.setAttachmentVO(new AttachmentVO());
		logCaseDataBean.setCaseEventsVO(new CaseEventsVO());
		logCaseDataBean.setCaseStepsVO(new CaseStepsVO());
		logCaseDataBean.setAlertVO(new AlertVO());
		logCaseDataBean.setAlternateIdentifiersVO(new AlternateIdentifiersVO());
		logCaseDataBean
				.setAssociatedCorrespondenceVO(new AssociatedCorrespondenceVO());
		logCaseDataBean.setAssociatedCaseVO(new AssociatedCaseVO());
		logCaseDataBean.setCustomFieldValueVO(new CustomFieldValueVO());
		//logCaseDataBean.getFormElements().getChildren().clear(); // Commented for the panelGrid.
		logCaseDataBean.setDisableCaseType(false);
		logCaseDataBean.getCaseNotesList().clear();
		logCaseDataBean.setEnableSaveLink(false);
		logCaseDataBean.setEnableDetachInd(false);
		logCaseDataBean.setEnableCaseEventsLetterLink(false);
		logCaseDataBean.setEnableCaseStepsLetterLink(false);
		logCaseDataBean.setEntityID(null);
		logCaseDataBean.setEntityType(null);
		logCaseDataBean.setCreatedBy(null);
		logCaseDataBean.setCreatedBySK(null);
		logCaseDataBean.setAssignedTo(null);
		logCaseDataBean.setAssignedToWorkUnitSK(null);
		if (logCaseDataBean.getDeptMap() != null) {
			logCaseDataBean.getDeptMap().clear();
		}
		logCaseDataBean.getRoutingDeptMap().clear();
		logCaseDataBean.getLobDOMap().clear();
		logCaseDataBean.getLobSKAndDeptSKMap().clear();
		logCaseDataBean.setBusinessUnit(null);
		logCaseDataBean.setReportingUnit(null);
		logCaseDataBean.setLobSK(null);
		logCaseDataBean.setCaseSK(null);
		logCaseDataBean.setCaseTypeSK(null);
		logCaseDataBean.getRoutingDeptList().clear();
		logCaseDataBean.getRoutingUserList().clear();
		logCaseDataBean.getCaseTypeDOList().clear();
		logCaseDataBean.getListOfDepartments().clear();
		logCaseDataBean.setActionCode(null);
		logCaseDataBean.getCustomFieldDOList().clear();
		logCaseDataBean.setFromIPC(false);
		logCaseDataBean.setShowGeneralCaseTypeScreens(false);
		logCaseDataBean.getCaseTypeEventDOList().clear();
		logCaseDataBean.getCaseTypeStepDOList().clear();
		logCaseDataBean.setShowReviewReq(false);
		logCaseDataBean.setCancelLinkUpdate(false);
		logCaseDataBean.setShowCaseMessage(false);//for ESPRD00762703
		//Commented For the Defect ESPRD00743101
		//logCaseDataBean.setSuperviserRevReqInd(false);
		logCaseDataBean.setSuperviserRevReqIndForCaseType(false);
		logCaseDataBean.setSuperviser(false);
		logCaseDataBean.setShowInqAbtMember(false);
		logCaseDataBean.setShowInqAbtProvider(false);
		logCaseDataBean.setShowInqAbtFor(false);
		logCaseDataBean.setEnableFollowUpMon(false);
		logCaseDataBean.setEnableTumorAndNodes(false);
		logCaseDataBean.setEnableTreatementStDates(false);
		logCaseDataBean.setEnableFieldAuditFields(false);
		logCaseDataBean.setEnableHomeOfficeInd(false);
		logCaseDataBean.setEnablePayRatesFields(false);
		logCaseDataBean.setEnablePictureDate(false);
		logCaseDataBean.setEnableRaterSetting(false);
		logCaseDataBean.setEnableStateFiscal(false);
		logCaseDataBean.setEnableBiopsyFindings(false);
		logCaseDataBean.getCfValueSKMap().clear();
		logCaseDataBean.setShowAddRoutingAssignment(false);
		logCaseDataBean.setShowEditRoutingAssignment(false);
		logCaseDataBean.setRoutingAuditHistoryRender(false);
		logCaseDataBean.getRoutingAuditHistoryList().clear();
		logCaseDataBean.setShowAddAttachments(false);
		logCaseDataBean.setShowEditAttachments(false);
		logCaseDataBean.setAttachmentAuditHistoryRender(false);
		logCaseDataBean.getAttachmentAuditHistoryList().clear();
		logCaseDataBean.setShowAddCaseEvents(false);
		logCaseDataBean.setShowEditCaseEvents(false);
		logCaseDataBean.getCaseEventsVOList().clear();
		logCaseDataBean.setShowCaseEventsDataTable(false);
		logCaseDataBean.getCaseStepsVOList().clear();
		logCaseDataBean.setShowCaseStepsDataTable(false);
		logCaseDataBean.setShowEditAlers(false);
		logCaseDataBean.setShowAddAlers(false);
		logCaseDataBean.getAlertsVOList().clear();
		logCaseDataBean.setShowAlertsDataTable(false);
		logCaseDataBean.setShowEditCaseSteps(false);
		logCaseDataBean.setShowCaseStepsDelteMessage(false); // Added for the defect id ESPRD00735630_20DEC2011
		logCaseDataBean.setCaseEventDeleteMsgFlag(false); // Added for the defect id ESPRD00735630_20DEC2011
		logCaseDataBean.setShowEditCRAssocWithThisCase(false);
		logCaseDataBean.setShowEditAssocCaseWithThisCase(false);
		logCaseDataBean.setShowAddCaseSteps(false);
		logCaseDataBean.setShowBCCPTypeScreen(false);
		logCaseDataBean.setShowDDUTypeScreen(false);
		logCaseDataBean.setShowDDUAppointmentScreen(false);
		logCaseDataBean.setShowARSTypeScreen(false);
		logCaseDataBean.setShowAppealScreen(false);
		logCaseDataBean.getRoutingInfoList().clear();
		logCaseDataBean.setShowRoutingDataTable(false);
		logCaseDataBean.getAddiCaseEntitesInfoList().clear();
		logCaseDataBean.setShowAddiCaseEntitesDataTable(false);
		logCaseDataBean.setShowExistingCRDataTable(false);
		logCaseDataBean.getExistingCRInfoList().clear();
		logCaseDataBean.setShowCRAssociatedWithCaseDataTable(false);
		logCaseDataBean.getCrAssociatedWithCaseInfoList().clear();
		logCaseDataBean.getExistingCaseRecordsList().clear();
		logCaseDataBean.getAssoCRList().clear();
		logCaseDataBean.getAssoCaseList().clear();
		logCaseDataBean.setShowExistingCaseRecordsDataTable(false);
		logCaseDataBean.getCaseRecordsAssoWithCaseList().clear();
		logCaseDataBean.setShowCaseRecordsAssoWithCaseDataTable(false);
		logCaseDataBean.setShowAlternateIdentifiersProv(false);
		logCaseDataBean.getAlternateIdentiProvList().clear();
		logCaseDataBean.setShowAlternateIdentifiersMem(false);
		logCaseDataBean.getAlternateIdentiMemList().clear();
		logCaseDataBean.setShowCaseRegardingMember(false);
		logCaseDataBean.setShowCaseRegardingProvider(false);
		logCaseDataBean.setShowCaseRegardingUnEnrolProv(false);
		logCaseDataBean.setShowCaseRegardingUnEnrolMem(false);
		logCaseDataBean.setShowCaseRegardingOther(false);
		logCaseDataBean.setShowCorrespondenceSearchScreen(false);
		logCaseDataBean.getCorrespondenceSearchResultsList().clear();
		logCaseDataBean.setShowCorrespondenceSearchDataTable(false);
		logCaseDataBean.setShowCorrespondenceSearchResults(false);
		logCaseDataBean.setShowCaseSearchScreen(false);
		logCaseDataBean.getCaseSearchResultsList().clear();
		logCaseDataBean.setShowCaseSearchDataTable(false);
		logCaseDataBean.setShowCaseSearchResults(false);
		logCaseDataBean.setCaseStepsIndex(null);
		logCaseDataBean.setCaseAlertsIndex(null);
		logCaseDataBean.setCaseEventsIndex(null);
		logCaseDataBean.setRoutingIndex(null);
		logCaseDataBean.setAttachmentIndex(null);
		logCaseDataBean.setAlertAuditOpen(false);
		logCaseDataBean.setAttachmentAuditOpen(false);
		logCaseDataBean.setRoutingAuditOpen(false);
		logCaseDataBean.setLogCaseErrMsgFlag(Boolean.FALSE);
		//Added for the defect ESPRD00898731
		logCaseDataBean.setStatusClosedAndApproved(false);
		logCaseDataBean.getEventNotifyList().clear();
		// Added for Defect ESPRD00717220
		logCaseDataBean.getProviderhos().clear();
		// Begin - Added this block of code for the defect
		// ESPRD00682457_07SEP2011
		LogCaseDomainToVOConverter caseDomainToVOConverter = null;
		caseDomainToVOConverter = new LogCaseDomainToVOConverter();
		List list = new ArrayList();

		list.add(caseDomainToVOConverter.getInputCriteria(
				ReferenceServiceDataConstants.A_RSN_CD_APP,
				FunctionalAreaConstants.GENERAL));
		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		referenceDataSearch.setInputList(list);

		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();

		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);
		} catch (ReferenceServiceRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemListNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map map = referenceDataListVO.getResponseMap();

		List validValuesList = (List) map.get(FunctionalAreaConstants.GENERAL
				+ "#" + ReferenceServiceDataConstants.A_RSN_CD_APP);
		System.out.println("=====validValuesList :::: "
				+ validValuesList.size());
		int validValuesSize = validValuesList.size();
		List validList = new ArrayList();
		for (int i = 0; i < validValuesSize; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) validValuesList
					.get(i);

			validList.add(new SelectItem(refVo.getValidValueCode(), refVo
					.getValidValueCode()
					+ "-" + refVo.getShortDescription()));
		}
		logCaseDataBean.setUnusualCircumstancesList(validList);
		logCaseDataBean.getSelectedUnUsualList().clear();
		// End - Added this block of code for the defect ESPRD00682457_07SEP2011

		//Modified for defect ESPRD00512715
		//logCaseDataBean.getAttachmentVOList().clear();
		//added for the defect:esprd00679144
		if (logCaseDataBean.getAttachmentVOList() != null) {
			logCaseDataBean.getAttachmentVOList().clear();
		}

		logCaseDataBean
				.setCaseAttachmentsHidden(MaintainContactManagementUIConstants.PLUS);
		//end for the defect:esprd00679144
		logCaseDataBean.setShowAttachmentDataTable(false);
		logCaseDataBean.setShowDepartments(false);
		logCaseDataBean.setShowUserDepartments(false);
		logCaseDataBean.setShowUsers(false);
		logCaseDataBean.getInqAbtEntityListBeforeSave().clear();
		logCaseDataBean.setAlertAuditHistoryRender(false);
		logCaseDataBean.getAlertAuditHistoryList().clear();
		logCaseDataBean.setDisableScreenField(false);
		//Commented for defect ESPRD00880476
		//logCaseDataBean.setDisableLogCaseReset(false);
		logCaseDataBean.setCommonEntitySKForMyTask(null);
		logCaseDataBean.setShowAttachmentLink(false);
		//added for 544575
		logCaseDataBean.setTemplateList(new ArrayList());
		logCaseDataBean.setInvalidEventDate(false);

		// Defect ESPRD00675170 starts
		logCaseDataBean.setShowResultsDiv(false);
		logCaseDataBean
				.setCorrespondenceSearchCriteriaVO(new CorrespondenceSearchCriteriaVO());
		logCaseDataBean.getSearchResults().clear();
		logCaseDataBean.setShowCorrespondenceSearchScreen(false);
		logCaseDataBean.setShowCorrSearchScreen(false);
		logCaseDataBean.setSearchSelectAll(false);
		logCaseDataBean.setSearchDeSelectAll(false);
		
		//for ESPRD00747731
		logCaseDataBean.setShowAttachmentDelMsg(false);
		logCaseDataBean.setShowSucessMessage(false);

		// Defect ESPRD00675170 Ends
		//Added for the defect ESPRD00902780
		caseSearchDataBean.setCaseRecordSearchCriteriaVO(new CaseRecordSearchCriteriaVO());
		caseSearchDataBean.setShowData(false);
		//DEFECT 535752
		/* for defect ESPRD00834054
		 * reset the parent vo object while loading the page
		 * with fresh data from database.then child inner
		 * vo's will be automatically reseted.
		 */
		commonEntityDataBean.setCommonEntityVO(new CommonEntityVO());
		/*commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
				.setNoteText("");
		commonEntityDataBean.getCommonEntityVO().getCommonNotesVO()
				.setCurrentNote("");*/
		commonEntityDataBean.setNotesSaveMsg(false);
		commonEntityDataBean.getCommonNoteSearchVO().setUsageTypeCode("");
		commonEntityDataBean.setNoteList(new ArrayList());
		//test 535752

		//ESPRD00675640
		commonEntityDataBean.setTempNoteList(new ArrayList());
		//for defect ESPRD00843800 starts
		/** resetting below flags is nothing but like
		 * clicking the cancel link under notes section. As if 
		 * user click 'AddnewNote' and re visit the page without
		 * clicking of cancel link.
		 * */
		commonEntityDataBean.setNewNotesRender(Boolean.FALSE);
		commonEntityDataBean.setFilterNotesRender(Boolean.FALSE);
		commonEntityDataBean.setMainNotesRender(Boolean.FALSE);
		commonEntityDataBean.setSaveNotesChk(Boolean.FALSE);
		commonEntityDataBean.setFilterEnabled(Boolean.TRUE);
		//for defect ESPRD00843800 ends
		/*commonEntityDataBean.getCommonEntityVO().setCommonNotesVO(
				new CommonNotesVO());*/
		logCaseDataBean.getCaseNotesSetTempList().clear();
		getLettersAndResponsesControllerBean()
		.clearLettersAndResponsesDataBeanState();
		log.info("restoreCaseState() is ended");
		 // Added for the Defect Id : ESPRD00736433 
		 // Start
		/* Code commented to move it in CMlogcaseControllerBean -> getreceivemessage()
		 * logCaseDataBean.setRoutingHidden(MaintainContactManagementUIConstants.PLUS);
		 logCaseDataBean.setAssocCaseAndCRHidden(MaintainContactManagementUIConstants.PLUS);
		 logCaseDataBean.setCaseAttachmentsHidden(MaintainContactManagementUIConstants.PLUS);
		 logCaseDataBean.setLettersAndRespStyleVal("none");
		 logCaseDataBean.setCaseEventsHidden(MaintainContactManagementUIConstants.PLUS);
		 logCaseDataBean.setCaseAlertsHidden(MaintainContactManagementUIConstants.PLUS);
		 logCaseDataBean.setCaseStepsHidden(MaintainContactManagementUIConstants.PLUS);*/
		 //End 
		 //as below flags are in condition for sub section saves on major save
		 logCaseDataBean.setRenderedalertsFlag(Boolean.FALSE);
		 logCaseDataBean.setRenderedCaseEventsflag(Boolean.FALSE);
		 logCaseDataBean.setRenderedroutingFlag(Boolean.FALSE);
		 logCaseDataBean.setRenderedCaseStepsflag(Boolean.FALSE);
		 
		// modified for defect ESPRD00788749 starts
		logCaseDataBean.setShowCaseRoutingMessages(Boolean.FALSE);
		logCaseDataBean.setShowCaseEventsMessages(Boolean.FALSE);
		logCaseDataBean.setShowCaseAlertsMessages(Boolean.FALSE);
		logCaseDataBean.setShowCaseStepsMessages(Boolean.FALSE);
		// modified for defect ESPRD00788749 ends 
		commonEntityDataBean.setCommonNoteSaved(false); // modified for defect ESPRD00802065
		//for defect ESPRD00790505
		logCaseDataBean.setAttachmentAddOrUpdate(Boolean.FALSE);
		logCaseDataBean.setAttachmentListSize(ContactManagementConstants.ZERO);
		
		//Code added for CR_908771
		logCaseDataBean.setSaveFlag(false);
	}

	public void restoreAddUpdatePages() {
		logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
		logCaseDataBean.setShowAddRoutingAssignment(false);
		logCaseDataBean.setShowEditRoutingAssignment(false);
		logCaseDataBean.setShowAddAttachments(false);
		logCaseDataBean.setShowEditAttachments(false);
		logCaseDataBean.setShowAddCaseEvents(false);
		logCaseDataBean.setShowEditCaseEvents(false);
		logCaseDataBean.setShowEditAlers(false);
		logCaseDataBean.setShowAddAlers(false);
		logCaseDataBean.setShowEditCaseSteps(false);
		logCaseDataBean.setShowAddCaseSteps(false);
	}

	/**
	 * This operation is used to get all the AssocCaseRecords sorted on certain
	 * column and order.
	 * 
	 * @param sortColumn :
	 *            Column names to sort the columns
	 * @param sortOrder :
	 *            Oder Type to sort the Columns
	 * @param dataList :
	 *            List of the records to Sort
	 */
	public void sortAssocCaseRecords(final String sortColumn,
			final String sortOrder, List dataList) {
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				AssociatedCaseVO data1 = (AssociatedCaseVO) obj1;
				System.out
						.println("====data1 : case id : " + data1.getCaseID());
				AssociatedCaseVO data2 = (AssociatedCaseVO) obj2;
				System.out
						.println("====data2 : case id : " + data2.getCaseID());
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
				if ("caseAsso_ID".equals(sortColumn)) {
					System.out.println("====inside the case id");
					if (null == data1.getCaseID()) {
						data1
								.setCaseID(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getCaseID()) {
						data1
								.setCaseID(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getCaseID().compareTo(
							data2.getCaseID()) : data2.getCaseID().compareTo(
							data1.getCaseID());
				}
				if ("caseAsso_CreateDate".equals(sortColumn)) {
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
				if ("caseAsso_Status".equals(sortColumn)) {

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
				if ("caseAsso_CaseType".equals(sortColumn)) {

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

				return 0;
			}

		};

		Collections.sort(dataList, comparator);
	}

	/**
	 * This operation is used to get all the Correspondence AssocCaseRecords
	 * sorted on certain column and order.
	 * 
	 * @param sortColumn :
	 *            Column names to sort the columns
	 * @param sortOrder :
	 *            Oder Type to sort the Columns
	 * @param dataList :
	 *            List of the records to Sort
	 */
	public void sortCrAssocCaseRecords(final String sortColumn,
			final String sortOrder, List dataList) {
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				AssociatedCorrespondenceVO data1 = (AssociatedCorrespondenceVO) obj1;
				AssociatedCorrespondenceVO data2 = (AssociatedCorrespondenceVO) obj2;
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
				if ("CrAssocRecords_ID".equals(sortColumn)) {
					System.out.println("====inside the case id");
					if (null == data1.getCorrespondenceRecordNumber()) {
						data1
								.setCorrespondenceRecordNumber(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getCorrespondenceRecordNumber()) {
						data1
								.setCorrespondenceRecordNumber(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getCorrespondenceRecordNumber()
							.compareTo(data2.getCorrespondenceRecordNumber())
							: data2.getCorrespondenceRecordNumber().compareTo(
									data1.getCorrespondenceRecordNumber());
				}
				if ("CrAssocRecords_CreateDate".equals(sortColumn)) {
					if (null == data1.getOpenDate()) {
						data1.setOpenDate(ContactHelper
								.dateConverter(new Date()));
					}
					if (null == data2.getOpenDate()) {
						data2.setOpenDate(ContactHelper
								.dateConverter(new Date()));
					}
					return ascending ? data1.getOpenDate().compareTo(
							data2.getOpenDate()) : data2.getOpenDate()
							.compareTo(data1.getOpenDate());
				}
				if ("CrAssocRecords_Status".equals(sortColumn)) {

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
				if ("CrAssocRecords_Category".equals(sortColumn)) {

					if (null == data1.getCategory()) {
						data1
								.setCategory(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getCategory()) {
						data2
								.setCategory(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getCategory().trim().compareTo(
							data2.getCategory().trim()) : data2.getCategory()
							.trim().compareTo(data1.getCategory().trim());
				}
				if ("CrAssocRecords_Subject".equals(sortColumn)) {

					if (null == data1.getSubject()) {
						data1
								.setSubject(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getSubject()) {
						data2
								.setSubject(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getSubject().trim().compareTo(
							data2.getSubject().trim()) : data2.getSubject()
							.trim().compareTo(data1.getSubject().trim());
				}
				if ("CrAssocRecords_Contact".equals(sortColumn)) {

					if (null == data1.getContact()) {
						data1
								.setContact(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getContact()) {
						data2
								.setContact(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getContact().trim().compareTo(
							data2.getContact().trim()) : data2.getContact()
							.trim().compareTo(data1.getContact().trim());
				}

				return 0;
			}

		};

		Collections.sort(dataList, comparator);
	}

	/**
	 * This operation is used to get all the Correspondence Search Records
	 * sorted on certain column and order.
	 * 
	 * @param sortColumn :
	 *            Column names to sort the columns
	 * @param sortOrder :
	 *            Oder Type to sort the Columns
	 * @param dataList :
	 *            List of the records to Sort
	 */
	public void sortCrSearchRecords(final String sortColumn,
			final String sortOrder, List dataList) {
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CorrespondenceSearchResultsVO data1 = (CorrespondenceSearchResultsVO) obj1;
				CorrespondenceSearchResultsVO data2 = (CorrespondenceSearchResultsVO) obj2;
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
				if ("CRSearch_Created".equals(sortColumn)) {
					if (null == data1.getCreatedOn()) {
						data1.setCreatedOn(new Date());
					}
					if (null == data2.getCreatedOn()) {
						data2.setCreatedOn(new Date());
					}
					return ascending ? data1.getCreatedOn().compareTo(
							data2.getCreatedOn()) : data2.getCreatedOn()
							.compareTo(data1.getCreatedOn());
				}
				if ("CRSearch_EntityName".equals(sortColumn)) {

					if (null == data1.getEntityName()) {
						data1
								.setEntityName(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getEntityName()) {
						data2
								.setEntityName(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getEntityName().trim().compareTo(
							data2.getEntityName().trim()) : data2
							.getEntityName().trim().compareTo(
									data1.getEntityName().trim());
				}
				if ("CRSearch_EntityType".equals(sortColumn)) {

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
				if ("CrAssocRecords_Status".equals(sortColumn)) {

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
				if ("CRSearch_AssignedTo".equals(sortColumn)) {

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
				if ("CRSearch_CategoryDesc".equals(sortColumn)) {

					if (null == data1.getCategoryDescription()) {
						data1
								.setCategoryDescription(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getCategoryDescription()) {
						data2
								.setCategoryDescription(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getCategoryDescription().trim()
							.compareTo(data2.getCategoryDescription().trim())
							: data2.getCategoryDescription().trim().compareTo(
									data1.getCategoryDescription().trim());
				}
				if ("CRSearch_BusinessUnit".equals(sortColumn)) {

					if (null == data1.getBusinessUnit()) {
						data1
								.setBusinessUnit(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getBusinessUnit()) {
						data2
								.setBusinessUnit(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getBusinessUnit().trim()
							.compareTo(data2.getBusinessUnit().trim()) : data2
							.getBusinessUnit().trim().compareTo(
									data1.getBusinessUnit().trim());
				}
				if ("CRSearch_CorrNumber".equals(sortColumn)) {

					if (null == data1.getCorrespondenceNumber()) {
						data1.setCorrespondenceNumber(null);
					}
					if (null == data2.getCorrespondenceNumber()) {
						data2.setCorrespondenceNumber(null);
					}
					return ascending ? data1.getCorrespondenceNumber()
							.compareTo(data2.getCorrespondenceNumber()) : data2
							.getCorrespondenceNumber().compareTo(
									data1.getCorrespondenceNumber());
				}
				if ("CRSearch_LOB".equals(sortColumn)) {

					if (null == data1.getLobCode()) {
						data1
								.setLobCode(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getLobCode()) {
						data2
								.setLobCode(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getLobCode().trim().compareTo(
							data2.getLobCode().trim()) : data2.getLobCode()
							.trim().compareTo(data1.getLobCode().trim());
				}

				return 0;
			}

		};

		Collections.sort(dataList, comparator);
	}

	/**
	 * This operation is used to get all the case entity Records sorted on
	 * certain column and order.
	 * 
	 * @param sortColumn :
	 *            Column names to sort the columns
	 * @param sortOrder :
	 *            Oder Type to sort the Columns
	 * @param dataList :
	 *            List of the records to Sort
	 */
	public void sortAdditionalCaseEntites(final String sortColumn,
			final String sortOrder, List dataList) {
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CaseRegardingMemberVO CRMember1 = null;
				CaseRegardingMemberVO CRMember2 = null;
				CaseRegardingProviderVO CRProvider1 = null;
				CaseRegardingProviderVO CRProvider2 = null;
				CaseRegardingTPL CRTPL1 = null;
				CaseRegardingTPL CRTPL2 = null;
				CaseRegardingTradingPartnerVO CRtrnPtn1 = null;
				CaseRegardingTradingPartnerVO CRtrnPtn2 = null;
				Integer entityID1 = Integer.valueOf(0); 
				Integer  entityID2 = Integer.valueOf(0);
				boolean ascending = false;
				if (obj1 instanceof CaseRegardingMemberVO && obj2 instanceof CaseRegardingMemberVO) {
					CRMember1 = (CaseRegardingMemberVO) obj1;
					CRMember2 = (CaseRegardingMemberVO) obj2;

					
					if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
							.equals(sortOrder)) {
						ascending = true;
					} else {
						ascending = false;
					}

					if (sortColumn == null) {
						return 0;
					}
					if ("entityID".equals(sortColumn)) {
											if (null == CRMember1.getEntityId()||CRMember1.getEntityId().equals(MaintainContactManagementUIConstants.EMPTY_STRING)  ) {
							CRMember1
									.setEntityId(MaintainContactManagementUIConstants.NULL);
						}
						else {
							entityID1 = new Integer(CRMember1.getEntityId().trim());
						}
						if (null == CRMember2.getEntityId()||CRMember2.getEntityId().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
							
							CRMember2
									.setEntityId(MaintainContactManagementUIConstants.NULL);
						}
						else {
							entityID2 = new Integer(CRMember2.getEntityId().trim());
						}
						/*return ascending ? CRMember1.getEntityId().compareTo(
								CRMember2.getEntityId()) : CRMember2
								.getEntityId().compareTo(
										CRMember1.getEntityId());*/
						/*return ascending ? entityID1.compareTo(entityID2)
								: entityID2.compareTo(entityID1);*/
					}
					if ("Name".equals(sortColumn)) {

						if (null == CRMember1.getName()) {
							CRMember1
									.setName(MaintainContactManagementUIConstants.NULL);
						}
						if (null == CRMember2.getName()) {
							CRMember2
									.setName(MaintainContactManagementUIConstants.NULL);
						}
						return ascending ? CRMember1.getName().trim()
								.compareToIgnoreCase(CRMember2.getName().trim())
								: CRMember2.getName().trim().compareToIgnoreCase(
										CRMember1.getName().trim());
					}
					if ("EntityType".equals(sortColumn)) {

						if (null == CRMember1.getEntityType()) {
							CRMember1
									.setEntityType(MaintainContactManagementUIConstants.NULL);
						}
						if (null == CRMember2.getEntityType()) {
							CRMember2
									.setEntityType(MaintainContactManagementUIConstants.NULL);
						}
						return ascending ? CRMember1.getEntityType().trim()
								.compareTo(CRMember2.getEntityType().trim())
								: CRMember2.getEntityType().trim().compareTo(
										CRMember1.getEntityType().trim());
					}
				} else if (obj1 instanceof CaseRegardingProviderVO && obj2 instanceof CaseRegardingProviderVO) {
					CRProvider1 = (CaseRegardingProviderVO) obj1;
					CRProvider2 = (CaseRegardingProviderVO) obj2;

					ascending = false;
					if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
							.equals(sortOrder)) {
						ascending = true;
					} else {
						ascending = false;
					}

					if (sortColumn == null) {
						return 0;
					}
					if ("entityID".equals(sortColumn)) {
						if (null == CRProvider1.getEntityId() ||CRProvider1.getEntityId().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
							
							CRProvider1
									.setEntityId(MaintainContactManagementUIConstants.NULL);
						}
						else {
							entityID1 = new Integer(CRProvider1.getEntityId().trim());
						}
						if (null == CRProvider2.getEntityId()||CRProvider2.getEntityId().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
							
							CRProvider2
									.setEntityId(MaintainContactManagementUIConstants.NULL);
						}
						else {
							entityID2 = new Integer(CRProvider2.getEntityId().trim());
						}
						/*return ascending ? CRProvider1.getEntityId().compareTo(
								CRProvider2.getEntityId()) : CRProvider2
								.getEntityId().compareTo(
										CRProvider1.getEntityId());*/
						/*return ascending ? entityID1.compareTo(entityID2)
								: entityID2.compareTo(entityID1);*/
					}
					if ("Name".equals(sortColumn)) {

						if (null == CRProvider1.getName()) {
							CRProvider1
									.setName(MaintainContactManagementUIConstants.NULL);
						}
						if (null == CRProvider2.getName()) {
							CRProvider2
									.setName(MaintainContactManagementUIConstants.NULL);
						}
						return ascending ? CRProvider1.getName().trim()
								.compareToIgnoreCase(CRProvider2.getName().trim())
								: CRProvider2.getName().trim().compareToIgnoreCase(
										CRProvider1.getName().trim());
					}
					if ("EntityType".equals(sortColumn)) {

						if (null == CRProvider1.getEntityType()) {
							CRProvider1
									.setEntityType(MaintainContactManagementUIConstants.NULL);
						}
						if (null == CRProvider2.getEntityType()) {
							CRProvider2
									.setEntityType(MaintainContactManagementUIConstants.NULL);
						}
						return ascending ? CRProvider1.getEntityType().trim()
								.compareTo(CRProvider2.getEntityType().trim())
								: CRProvider2.getEntityType().trim().compareTo(
										CRProvider1.getEntityType().trim());
					}
				} else if (obj1 instanceof CaseRegardingTPL && obj2 instanceof CaseRegardingTPL) {
					CRTPL1 = (CaseRegardingTPL) obj1;
					CRTPL2 = (CaseRegardingTPL) obj2;

					ascending = false;
					if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
							.equals(sortOrder)) {
						ascending = true;
					} else {
						ascending = false;
					}

					if (sortColumn == null) {
						return 0;
					}
					if ("entityID".equals(sortColumn)) {
			     	if (null == CRTPL1.getEntityId() || CRTPL1.getEntityId().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
							
							CRTPL1	
							.setEntityId(MaintainContactManagementUIConstants.NULL);
						}
						else {
							entityID1 = new Integer(CRTPL1.getEntityId().trim());
						}
						if (null == CRTPL2.getEntityId()||CRTPL2.getEntityId().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
							
							CRTPL2
									.setEntityId(MaintainContactManagementUIConstants.NULL);
						}
						else {
							entityID2 = new Integer(CRTPL2.getEntityId());
						}
						/*return ascending ? CRTPL1.getEntityId().compareTo(
								CRTPL2.getEntityId()) : CRTPL2.getEntityId()
								.compareTo(CRTPL1.getEntityId());*/
						/*return ascending ? entityID1.compareTo(entityID2)
								: entityID2.compareTo(entityID1);*/
					}
					if ("Name".equals(sortColumn)) {

						if (null == CRTPL1.getName()) {
							CRTPL1
									.setName(MaintainContactManagementUIConstants.NULL);
						}
						if (null == CRTPL2.getName()) {
							CRTPL2
									.setName(MaintainContactManagementUIConstants.NULL);
						}
						return ascending ? CRTPL1.getName().trim().compareToIgnoreCase(
								CRTPL2.getName().trim()) : CRTPL2.getName()
								.trim().compareToIgnoreCase(CRTPL1.getName().trim());
					}
					if ("EntityType".equals(sortColumn)) {

						if (null == CRTPL1.getEntityType()) {
							CRTPL1
									.setEntityType(MaintainContactManagementUIConstants.NULL);
						}
						if (null == CRTPL2.getEntityType()) {
							CRTPL2
									.setEntityType(MaintainContactManagementUIConstants.NULL);
						}
						return ascending ? CRTPL1.getEntityType().trim()
								.compareTo(CRTPL2.getEntityType().trim())
								: CRTPL2.getEntityType().trim().compareTo(
										CRTPL1.getEntityType().trim());
					}
				} else if (obj1 instanceof CaseRegardingTradingPartnerVO) {
					CRtrnPtn1 = (CaseRegardingTradingPartnerVO) obj1;
					CRtrnPtn2 = (CaseRegardingTradingPartnerVO) obj2;

					ascending = false;
					if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
							.equals(sortOrder)) {
						ascending = true;
					} else {
						ascending = false;
					}

					if (sortColumn == null) {
						return 0;
					}
					if ("entityID".equals(sortColumn)) {
						if (null == CRtrnPtn1.getEntityId() || CRtrnPtn1.getEntityId().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
							CRtrnPtn1
									.setEntityId(MaintainContactManagementUIConstants.NULL);
						}
						else {
							entityID1 = new Integer(CRtrnPtn1.getEntityId().trim());
						}
						if (null == CRtrnPtn2.getEntityId()||CRtrnPtn2.getEntityId().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) {
							CRtrnPtn2
									.setEntityId(MaintainContactManagementUIConstants.NULL);
						}
						else {
							entityID2 = new Integer(CRtrnPtn2.getEntityId().trim());
						}
						/*return ascending ? CRtrnPtn1.getEntityId().compareTo(
								CRtrnPtn2.getEntityId()) : CRtrnPtn2
								.getEntityId().compareTo(
										CRtrnPtn1.getEntityId());*/
						/*return ascending ? entityID1.compareTo(entityID2)
								: entityID2.compareTo(entityID1);*/
					}
					if ("Name".equals(sortColumn)) {

						if (null == CRtrnPtn1.getName()) {
							CRtrnPtn1
									.setName(MaintainContactManagementUIConstants.NULL);
						}
						if (null == CRtrnPtn2.getName()) {
							CRtrnPtn2
									.setName(MaintainContactManagementUIConstants.NULL);
						}
						return ascending ? CRtrnPtn1.getName().trim()
								.compareToIgnoreCase(CRtrnPtn2.getName().trim())
								: CRtrnPtn2.getName().trim().compareToIgnoreCase(
										CRtrnPtn1.getName().trim());
					}
					if ("EntityType".equals(sortColumn)) {

						if (null == CRtrnPtn1.getEntityType()) {
							CRtrnPtn1
									.setEntityType(MaintainContactManagementUIConstants.NULL);
						}
						if (null == CRtrnPtn2.getEntityType()) {
							CRtrnPtn2
									.setEntityType(MaintainContactManagementUIConstants.NULL);
						}
						return ascending ? CRtrnPtn1.getEntityType().trim()
								.compareTo(CRtrnPtn2.getEntityType().trim())
								: CRtrnPtn2.getEntityType().trim().compareTo(
										CRtrnPtn1.getEntityType().trim());
					}
				}else{
					return 0;
				}
				return ascending ? entityID1.compareTo(entityID2)
						: entityID2.compareTo(entityID1);
				
			}
		};

		Collections.sort(dataList, comparator);
	}


	/**
	 * This method get LetterDataBean object.
	 * 
	 * @return LettersAndResponsesDataBean.
	 */
	public final LettersAndResponsesDataBean getLetterDataBean() {

		//logger.info("Inside getDataBeanFromContext");
		FacesContext fc = FacesContext.getCurrentInstance();
		LettersAndResponsesDataBean letterTemplateDataBean = (LettersAndResponsesDataBean) fc
				.getApplication().createValueBinding(
						"#{" + GlobalLetterConstants.LETTER_RESPONSES_DATA_BEAN
								+ "}").getValue(fc);
		return letterTemplateDataBean;
	}
	/**
	 * To get the LettersAndResponsesControllerBean.
	 * 
	 * @return LettersAndResponsesControllerBean.
	 */
	protected LettersAndResponsesControllerBean getLettersAndResponsesControllerBean() {
		log.info("getLettersAndResponsesControllerBean()");

		FacesContext fc = FacesContext.getCurrentInstance();
		log.info("Class is "
				+ fc.getApplication().createValueBinding(
						"#{" + "LettersAndResponsesControllerBean" + "}")
						.getValue(fc).getClass());

		LettersAndResponsesControllerBean lettAndResControllerBean = (LettersAndResponsesControllerBean) fc
				.getApplication().createValueBinding(
						"#{" + "LettersAndResponsesControllerBean" + "}")
				.getValue(fc);

		return lettAndResControllerBean;
	}
	
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
}