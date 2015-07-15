package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.application.exception.CommonNoteNotFoundException;
import com.acs.enterprise.common.program.commonentities.common.domain.Note;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.common.domain.SpecificEntity;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMEntityDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
//import com.acs.enterprise.common.program.contactmanagement.common.domain.Alert;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CRCommonEntityCrossRef;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseCommonEntityCrossRef;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRecord;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseWatchList;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
//import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceWatchList;
import com.acs.enterprise.common.program.contactmanagement.common.vo.MyAlertResultsVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.MyCRResultsVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.MyTaskControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.MyTaskDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CRDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CRsWatchListVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRecWatchListVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseViewVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.GroupCRsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.GroupCaseRecordsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.MyAlertsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.MyCRsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.MyCaseRecordsVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.mmis.member.common.application.exception.MemberBusinessException;
import com.acs.enterprise.mmis.member.common.domain.Member;
import com.acs.enterprise.mmis.member.common.vo.MemberInformationRequestVO;
import com.acs.enterprise.mmis.member.information.common.delegate.MemberInformationDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLCarrier;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLPolicyHolder;
import com.acs.enterprise.mmis.provider.common.delegate.ProviderInformationDelegate;
import com.acs.enterprise.mmis.provider.common.domain.Provider;
import com.acs.enterprise.mmis.provider.common.vo.ProviderInformationRequestVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.MyCaseResultsVO;

/** Class for converting Do to Vo */
public class MyTaskDOConverter {

	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(MyTaskDOConverter.class);

	/**
	 * Stores Reference for createdOrOpened.
	 */
	private boolean createdOrOpened = false;

	private MyTaskDataBean myTaskDataBean = (MyTaskDataBean) getDataBean("myTaskDataBean");

	public List getAlertsVOList(List correspondenceList) {
		List alertVOList = new ArrayList();
		if(logger.isInfoEnabled())
		{
		logger.info("In the getAlertsVOList  correspondenceList.size "
				+ correspondenceList.size());
		}
		Format formatter = new SimpleDateFormat("MM/dd/yyyy", Locale
				.getDefault());
		for (Iterator iter = correspondenceList.iterator(); iter.hasNext();) {
			
			MyAlertResultsVO alertResultVO = (MyAlertResultsVO) iter.next();
			Long correspondenceSK = null;
			Long caseSK = null;
			String tplCaseUserID = null;//MyTask_Alerts_ESPRD00423256_9MAR2010
			String serviceAuthorizationID = null;//MyTask_Alerts_ESPRD00432772_10MAR2010
			String msqID = null;//UC-PGM-CRM-053_ESPRD00431860_26MAR2010
			String memberID = null;//UC-PGM-CRM-053_ESPRD00431860_26MAR2010

			if (alertResultVO != null) {

				MyAlertsVO alertVO = new MyAlertsVO();

				correspondenceSK = alertResultVO.getCorrespondenceSK();

				caseSK = alertResultVO.getCaseSk();

				tplCaseUserID = alertResultVO.getTplCaseUserID();//MyTask_Alerts_ESPRD00423256_9MAR2010
				serviceAuthorizationID = alertResultVO
						.getServiceAuthorizationID();//MyTask_Alerts_ESPRD00432772_10MAR2010

				if (alertResultVO.getMsgSentSeqNum() != null) {// UC-PGM-CRM-053_ESPRD00431860_26MAR2010
					msqID = String.valueOf(alertResultVO.getMsgSentSeqNum());

				}
				if (alertResultVO.getMemberID() != null) {
					memberID = String.valueOf(alertResultVO.getMemberID());

				} //EOF UC-PGM-CRM-053_ESPRD00431860_26MAR2010

				if (alertResultVO.getDueDate() != null) {
					alertVO.setDueDate(formatter.format(alertResultVO
							.getDueDate()));
				}

				if (alertResultVO.getAlertTypeCode() != null) {
					alertVO.setAlertType(alertResultVO.getAlertTypeCode());
					alertVO
							.setAlertTypeDesc(ContactManagementHelper
									.setShortDescription(
											FunctionalAreaConstants.GENERAL,
											ReferenceServiceDataConstants.G_ALERT_TY_CD,
											alertVO.getAlertType()));
				}

				if (alertResultVO.getLetterReqSk() != null) {
					alertVO.setLetterReqSk(alertResultVO.getLetterReqSk()
							.toString());
					alertVO.setScope("LG");
                    String ScopeLetterDesc= ReferenceServiceDelegate
						.getReferenceSearchShortDescription(
								FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_ALERT_SCOPE_CD,
								new Long(1110),"LG");
   	               alertVO.setScopeDesc(ScopeLetterDesc);
   	              if(alertResultVO.getEntityName()!=null){
            		alertVO.setEntityName(alertResultVO.getEntityName());
   	              }
				}

				alertVO.setDescription(alertResultVO.getDescription());

				if (caseSK != null) {

					alertVO.setScope(ContactManagementConstants.CASE_RECORD);
					String ScopeCSDesc = ReferenceServiceDelegate
							.getReferenceSearchShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.G_ALERT_SCOPE_CD,
									new Long(1110), "CS");
					alertVO.setScopeDesc(ScopeCSDesc);

					alertVO.setCaseSk(alertResultVO.getCaseSk().toString());
					//added for defect ESPRD00359439 Starts
					alertVO
							.setCaseStepSeqNum(alertResultVO
									.getCaseStepSeqNum());
					//defect ESPRD00359439 ends

					if (alertResultVO.getEntityName() != null) {//UC-PGM-CRM-019_ESPRD00440700_02APR2010
						alertVO.setEntityName(alertResultVO.getEntityName());
					} //EOF UC-PGM-CRM-019_ESPRD00440700_02APR2010

					//                    alertVOList.add(alertVO);

				}

				//added for UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010
				if (alertResultVO.getAlertSK() != null) {
					alertVO
							.setAlertSKStr(alertResultVO.getAlertSK()
									.toString());
				}
				if (alertResultVO.getAlertStatusCode() != null) {
					if (alertResultVO.getAlertStatusCode().trim()
							.equalsIgnoreCase("O")) {
						alertVO.setStatus("Open");
					} else {
						alertVO.setStatus(alertResultVO.getAlertStatusCode());
					}
				}
				try {
					if (alertResultVO.getAlertAddedAuditTimeStamp() != null) {
						alertVO.setOpenDateStr(formatter.format(alertResultVO
								.getAlertAddedAuditTimeStamp()));
					}
				} catch (Exception e) {
					alertVO.setOpenDateStr("");
				}
				if (alertResultVO.getAlertAddedAuditUserID() != null) {
					alertVO.setCreatedBy(alertResultVO
							.getAlertAddedAuditUserID());

				} //EOF UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010

				//MyTask_Alerts_ESPRD00423256_9MAR2010
				if (tplCaseUserID != null && tplCaseUserID.trim().length() > 0) {

					alertVO.setScope(ContactManagementConstants.TPL_RECORD);

					String ScopeTPLDesc = ReferenceServiceDelegate
							.getReferenceSearchShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.G_ALERT_SCOPE_CD,
									new Long(1110), "TR");
					alertVO.setScopeDesc(ScopeTPLDesc);

					alertVO.setTplCaseUserID(tplCaseUserID);

					if (alertResultVO.getEntityName() != null) {
						alertVO.setEntityName(alertResultVO.getEntityName());
					}

					//                	 alertVOList.add(alertVO);

				}//EOF MyTask_Alerts_ESPRD00423256_9MAR2010

				//              MyTask_Alerts_ESPRD00432772_10MAR2010
				if (serviceAuthorizationID != null
						&& serviceAuthorizationID.trim().length() > 0) {
					alertVO.setScope(ContactManagementConstants.SA_RECORD);
					String ScopeSADesc = ReferenceServiceDelegate
							.getReferenceSearchShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.G_ALERT_SCOPE_CD,
									new Long(1110), "SA");
					alertVO.setScopeDesc(ScopeSADesc);

					alertVO.setServiceAuthorizationID(serviceAuthorizationID);

					if (alertResultVO.getEntityName() != null) {
						alertVO.setEntityName(alertResultVO.getEntityName());
					}

					//                	 alertVOList.add(alertVO);

				}//EOF MyTask_Alerts_ESPRD00432772_10MAR2010
				//UC-PGM-CRM-053_ESPRD00431860_26MAR2010
				if (memberID != null && memberID.trim().length() > 0) {
					alertVO.setScope("MSQ");

					String ScopeMSDesc = ReferenceServiceDelegate
							.getReferenceSearchShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.G_ALERT_SCOPE_CD,
									new Long(1110), "MS");
					alertVO.setScopeDesc(ScopeMSDesc);
					alertVO.setMsqID(msqID);
					alertVO.setMemberID(memberID);

					if (alertResultVO.getEntityName() != null) {
						alertVO.setEntityName(alertResultVO.getEntityName());
					}

					//                	 alertVOList.add(alertVO);

				} //EOF UC-PGM-CRM-053_ESPRD00431860_26MAR2010

				if (correspondenceSK != null) {

					alertVO.setCorrespondenceSk(alertResultVO
							.getCorrespondenceSK().toString());
					alertVO.setScope(ContactManagementConstants.EDMS_CRSPD);
					String ScopeCRDesc = ReferenceServiceDelegate
							.getReferenceSearchShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.G_ALERT_SCOPE_CD,
									new Long(1110),
									ContactManagementConstants.EDMS_CRSPD);
					alertVO.setScopeDesc(ScopeCRDesc);

					if (alertResultVO.getEntityName() != null) {
						alertVO.setEntityName(alertResultVO.getEntityName());
					}

					setCreatedOrOpened(true);
					setCreatedOrOpened(false);
					//                    alertVOList.add(alertVO);
				}
				if (alertVO.getAlertSKStr().equalsIgnoreCase(
						myTaskDataBean.getMyTaskAlertSk())) {
					
					myTaskDataBean.setCrExists(true);
				}
				alertVOList.add(alertVO);
			}
		}
		return alertVOList;
	}

	/**
	 * getting alerts vo detailsfrom the correspondence object
	 * 
	 * @param alertVO
	 *            MyAlertsVO
	 * @param entityDetails
	 *            the entityDetails to set
	 * @return
	 */
	/*
	 * private void getEntityDetails(MyAlertsVO alertVO, String entityDetails) {
	 * 
	 * if (entityDetails != null && entityDetails.length() > 1) { int
	 * separatorIndex = entityDetails
	 * .indexOf(ContactManagementConstants.COLON_SEPARATOR);
	 * 
	 * String entityName = entityDetails.substring(0, separatorIndex);
	 * 
	 * if (entityName != null) { alertVO.setEntityName(entityName); } }
	 *  }
	 */

	/**
	 * This method convert all the Correspondence do's to MyCr vo's.
	 * 
	 * @param correspondenceList
	 *            The correspondenceList to set.
	 * @return List.
	 */
	public List getMyCrVOList(List correspondenceList) {
		List myCrVOList = new ArrayList();
		for (Iterator iter = correspondenceList.iterator(); iter.hasNext();) {

			MyCRResultsVO myCRResultsVO = (MyCRResultsVO) iter.next();
			if (myCRResultsVO != null) {
				MyCRsVO myCRsVO = new MyCRsVO();
				myCRsVO.setCorrespondenceSK(myCRResultsVO.getCorrespondenceSK()
						.toString());
				myCRsVO.setCrn(myCRResultsVO.getCorrespondenceSK().toString()
						.toString());
				myCRsVO.setEntity(myCRResultsVO.getEntity());
				if (myCRResultsVO.getEntityType() != null) {
					String entityTypeDesc = ContactManagementHelper
							.setShortDescription(
									FunctionalAreaConstants.CONTACT_MGMT,
									ReferenceServiceDataConstants.G_CE_TY_OR_SE_TY_CD,
									myCRResultsVO.getEntityType());
					
					myCRsVO.setEntityType(entityTypeDesc);
				}

				
				if (myCRResultsVO.getOpenDate() != null) {
					myCRsVO.setOpenDate(dateConverter(myCRResultsVO
							.getOpenDate()));
				}
				//myCRsVO.setPriorityCode(correspondence.getPriorityCode());

				if (myCRResultsVO.getPriorityCode() != null) {

					String priorityDesc = ContactManagementHelper
							.setShortDescription(
									FunctionalAreaConstants.CONTACT_MGMT,
									ReferenceServiceDataConstants.G_CR_PRTY_CD,
									myCRResultsVO.getPriorityCode());
					
					myCRsVO.setPriorityCode(priorityDesc);

				}

				if (myCRResultsVO.getStatusCode() != null) {
					String statusDesc = ContactManagementHelper
							.setShortDescription(
									FunctionalAreaConstants.CONTACT_MGMT,
									ReferenceServiceDataConstants.G_CR_STAT_CD,
									myCRResultsVO.getStatusCode());
					
					myCRsVO.setStatusCode(statusDesc);
				}

				if (myCRsVO.getCorrespondenceSK().equalsIgnoreCase(
						myTaskDataBean.getMyTaskCrSk())) {
					
					myTaskDataBean.setCrExists(true);
				}
				myCrVOList.add(myCRsVO);
			}
		}
		return myCrVOList;
	}

	/**
	 * This method convert all the Correspondence do's to My group Cr vo's.
	 * 
	 * @param correspondenceList
	 *            The correspondenceList to set.
	 * @return List.
	 */
	public List getMyGrpCrVOList(List correspondenceList, Map deptMap) {
		List grpCrVOList = new ArrayList();
		String deptName = null;
		for (Iterator iter = correspondenceList.iterator(); iter.hasNext();) {
			MyCRResultsVO myCRResultsVO = (MyCRResultsVO) iter.next();

			if (myCRResultsVO != null) {
				GroupCRsVO groupCRsVO = new GroupCRsVO();

				if (myCRResultsVO.getCorrespondenceSK() != null) {
					groupCRsVO.setCorrespondenceSK(myCRResultsVO
							.getCorrespondenceSK().toString());
					groupCRsVO.setCrn(myCRResultsVO.getCorrespondenceSK()
							.toString());
				}

				
				groupCRsVO.setEntity(myCRResultsVO.getEntity());
				
				if (myCRResultsVO.getEntityType() != null) {
					String entityTypeDesc = ContactManagementHelper
							.setShortDescription(
									FunctionalAreaConstants.CONTACT_MGMT,
									ReferenceServiceDataConstants.G_CE_TY_OR_SE_TY_CD,
									myCRResultsVO.getEntityType());
					
					groupCRsVO.setEntityType(entityTypeDesc);
				}

				if (myCRResultsVO.getOpenDate() != null) {
					groupCRsVO.setOpenDate(dateConverter(myCRResultsVO
							.getOpenDate()));
				}

				if (myCRResultsVO.getPriorityCode() != null) {
					String priorityDesc = ContactManagementHelper
							.setShortDescription(
									FunctionalAreaConstants.CONTACT_MGMT,
									ReferenceServiceDataConstants.G_CR_PRTY_CD,
									myCRResultsVO.getPriorityCode());
					
					groupCRsVO.setPriorityCode(priorityDesc);
				}

				if (myCRResultsVO.getStatusCode() != null) {
					String statusDesc = ContactManagementHelper
							.setShortDescription(
									FunctionalAreaConstants.CONTACT_MGMT,
									ReferenceServiceDataConstants.G_CR_STAT_CD,
									myCRResultsVO.getStatusCode());
					
					groupCRsVO.setStatusCode(statusDesc);
				}

				if (myCRResultsVO.getWorkUnitTypeCode() != null
						&& myCRResultsVO.getWorkUnitTypeCode()
								.equalsIgnoreCase("W")) {
					Long workUnitSK = myCRResultsVO.getWorkUnitSK();
					
					if (deptMap != null) {
						deptName = (String) deptMap.get(workUnitSK.toString());
						
						groupCRsVO.setDeptName(deptName);
						groupCRsVO.setWorkUnit(deptName);
					}
				}
				
				if (groupCRsVO.getCorrespondenceSK().equalsIgnoreCase(
						myTaskDataBean.getMyTaskCrSk())) {
					
					myTaskDataBean.setCrExists(true);
				}
				grpCrVOList.add(groupCRsVO);
			}
		}
		return grpCrVOList;
	}

	/**
	 * This method convert all the Correspondence do's to CrWatchList vo's.
	 * 
	 * @param correspondenceList
	 *            The correspondenceList to set.
	 * @return List.
	 */
	public List getCrWatchListVOList(List correspondenceList, String userName) {
		List crWatchVOList = new ArrayList();
		
		if (correspondenceList != null) {
			if(logger.isInfoEnabled())
			{
			logger.info("watch list size====" + correspondenceList.size());
			}
			Iterator iterator = correspondenceList.iterator();
			while (iterator.hasNext()) {
				MyCRResultsVO myCRResultsVO = (MyCRResultsVO) iterator.next();
				CRsWatchListVO watchListVO = new CRsWatchListVO();
				if (myCRResultsVO != null) {
					watchListVO.setEntity(myCRResultsVO.getEntity());
					
					
					if (myCRResultsVO.getEntityType() != null) {
						String entityTypeDesc = ContactManagementHelper
								.setShortDescription(
										FunctionalAreaConstants.CONTACT_MGMT,
										ReferenceServiceDataConstants.G_CE_TY_OR_SE_TY_CD,
										myCRResultsVO.getEntityType());
						
						watchListVO.setEntityType(entityTypeDesc);
					}
					//watchListVO.setEntityType(myCRResultsVO.getEntityType());
					if (myCRResultsVO.getCorrespondenceSK() != null) {
						watchListVO.setCorrespondenceSK(myCRResultsVO
								.getCorrespondenceSK().toString());
						watchListVO.setCrn(myCRResultsVO.getCorrespondenceSK()
								.toString());
					}
					
					watchListVO.setWorkUnitSk(myCRResultsVO.getWorkUnitSK());
					if (myCRResultsVO.getOpenDate() != null) {
						watchListVO.setOpenDate(dateConverter(myCRResultsVO
								.getOpenDate()));
					}
					
					if (myCRResultsVO.getPriorityCode() != null) {
						String priorityDesc = ContactManagementHelper
								.setShortDescription(
										FunctionalAreaConstants.CONTACT_MGMT,
										ReferenceServiceDataConstants.G_CR_PRTY_CD,
										myCRResultsVO.getPriorityCode());
						
						watchListVO.setPriorityCode(priorityDesc);

					}
					if (myCRResultsVO.getStatusCode() != null) {
						String statusDesc = ContactManagementHelper
								.setShortDescription(
										FunctionalAreaConstants.CONTACT_MGMT,
										ReferenceServiceDataConstants.G_CR_STAT_CD,
										myCRResultsVO.getStatusCode());

						watchListVO.setStatusCode(statusDesc);
					}
					if (myCRResultsVO.getWorkUnitTypeCode() != null) {
						if (myCRResultsVO.getWorkUnitTypeCode().equals("U")) {
							
							watchListVO.setUserName(myCRResultsVO.getLastName()
									+ " " + myCRResultsVO.getFirstName());
						} else if (myCRResultsVO.getWorkUnitTypeCode().equals(
								"W")) {
							
							watchListVO.setUserName(myCRResultsVO
									.getWorkUnitName());
						}
					}

					if (watchListVO.getCorrespondenceSK().equalsIgnoreCase(
							myTaskDataBean.getMyTaskCrSk())) {
						
						myTaskDataBean.setCrExists(true);
					}
					crWatchVOList.add(watchListVO);
				}
			}
		}
		if(logger.isInfoEnabled())
		{
		logger.info("crWatchVOList size" + crWatchVOList.size());
		}
		return crWatchVOList;
	}

	/**
	 * Method to add the details of the Cr.
	 * 
	 * @param correspondence
	 *            The correspondence to set.
	 * @return CRDetailsVO.
	 */
	public CRDetailsVO addCrDetails(Correspondence correspondence) {
		CRDetailsVO detailsVO = new CRDetailsVO();
		MyTaskControllerBean controllerBean = new MyTaskControllerBean();
		
		if (correspondence.getCorrespondenceCategory() != null) {
			detailsVO.setCatDescription(correspondence
					.getCorrespondenceCategory().getDescription());
		}

		if (correspondence.getRepresentative() != null
				&& correspondence.getRepresentative().getName() != null) {
			detailsVO.setContactName(correspondence.getRepresentative()
					.getName().getFirstName()
					+ ", "
					+ correspondence.getRepresentative().getName()
							.getLastName());
		}
		

		if (correspondence.getSubjectCode() != null) {

			String subjDesc = ContactManagementHelper.setShortDescription(
					FunctionalAreaConstants.CONTACT_MGMT,
					ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK,
					correspondence.getSubjectCode());

			detailsVO.setSubjectCode(subjDesc);

		}
		if(logger.isDebugEnabled())
		{
		logger.debug("---- SetSource value in Cr datails is --"
				+ correspondence.getSourceCode());
		}
		detailsVO.setSource(correspondence.getSourceCode());
		if (correspondence.getSourceCode() != null) {

			String sourceDesc = ContactManagementHelper.setShortDescription(
					FunctionalAreaConstants.CONTACT_MGMT,
					ReferenceServiceDataConstants.G_CRSPD_SRC_CD,
					correspondence.getSourceCode());

			detailsVO.setSource(sourceDesc);

		}

		if (isCreatedOrOpened()) {
			
		} else {
			if (correspondence.getCreatedByWorkUnit() != null
					&& correspondence.getCreatedByWorkUnit()
							.getEnterpriseUser() != null) {
				detailsVO.setCreatedBy(correspondence.getCreatedByWorkUnit()
						.getEnterpriseUser().getFirstName()
						+ " "
						+ correspondence.getCreatedByWorkUnit()
								.getEnterpriseUser().getLastName());
			}
		}
		if (correspondence.getSupervisorReviewReqIndicator() != null) {
			detailsVO.setSupervisorAppReq(correspondence
					.getSupervisorReviewReqIndicator().booleanValue());
		}
		String noteText = null;
		if(logger.isDebugEnabled())
		{
		logger.debug("the corresponSK before noteset "
				+ correspondence.getCorrespondenceSK());
		logger.debug("before noteset correspondence.getNoteSet() :"
				+ correspondence.getNoteSet());
		}
		if (correspondence.getNoteSet() != null) {
			if(logger.isDebugEnabled())
			{
			logger.debug("iam in Noteset 1 noteset SK"
					+ correspondence.getNoteSet().getNoteSetSK());
			}
			NoteSet noteSet = null;
			try {
				noteSet = controllerBean.getNoteSet(correspondence.getNoteSet()
						.getNoteSetSK());

			} catch (CommonNoteNotFoundException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}
			if (noteSet != null) {

				Set notes = noteSet.getNotes();
				if(logger.isDebugEnabled())
				{
				logger.debug("noteset size:" + notes.size());
				}
				for (Iterator iter1 = notes.iterator(); iter1.hasNext();) {
					Note noteCr = (Note) iter1.next();

					noteText = (noteCr.getCex() == null) ? null : noteCr
							.getCex().getDescriptionClob();
				}
			}

			detailsVO.setNoteSet(noteText);
			if(logger.isDebugEnabled())
			{
			logger.debug("Notetext from detailsVo:" + detailsVO.getNoteSet());
			}
		}

		return detailsVO;
	}

	/**
	 * Method to get the entity n entity Type.
	 * 
	 * @return String.
	 */
	/*
	 * private String getNotes() { String noteText = null; return noteText; }
	 */

	/**
	 * Method to get the noteText.
	 * 
	 * @param correspondence :
	 *            The correspondence to set.
	 * @return String.
	 */
	public String getEntityValues(Correspondence correspondence) {
		String entityTypeCd = null;
		Long entitySk = null;
		String entityDetails = null;
		String entityType = null;
		Set entitySet = correspondence.getCrCmnEntyXRefs();
		MyTaskControllerBean controllerBean = new MyTaskControllerBean();
		
		if (entitySet != null) {
			
			for (Iterator iter1 = entitySet.iterator(); iter1.hasNext();) {
				
				CRCommonEntityCrossRef commonEntityCrossRef = (CRCommonEntityCrossRef) iter1
						.next();

				entityTypeCd = commonEntityCrossRef.getCommonEntityTypeCode();
				entitySk = commonEntityCrossRef.getCommonEntitySK();
				

				if (commonEntityCrossRef.getCommonEntityTypeCode() != null) {
					entityType = commonEntityCrossRef.getCommonEntityTypeCode();

				}
				String entityName = controllerBean.getEntityDetails(entitySk,
						entityTypeCd);
				
				if (entityName == null) {
					entityName = ContactManagementConstants.EMPTY_STRING;
				}
				if (entityType == null) {
					entityType = ContactManagementConstants.EMPTY_STRING;
				}
				entityDetails = entityName
						+ ContactManagementConstants.COLON_SEPARATOR
						+ entityType;

			}
		}
		return entityDetails;
	}

	/**
	 * Method to convert the Date into String.
	 * 
	 * @param date
	 *            the date to set.
	 * @return String.
	 */
	public static String dateConverter(java.util.Date date) {
		Format formatter;
		formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
		String strDate = formatter.format(date);

		return strDate;
	}

	/**
	 * This method convert all the Correspondence do's to MyCr vo's.
	 * 
	 * @param caseList
	 *            The caseList to set.
	 * @return List.
	 */

	public List getMyCaseVOList(List caseList) {
		
		List myCaseVOList = new ArrayList();

		//	CaseRecord caseRecord = new CaseRecord(); // Find bug issue

		for (Iterator iter = caseList.iterator(); iter.hasNext();) {
			MyCaseResultsVO myCaseResultsVO = (MyCaseResultsVO) iter.next();

			/* if (caseRecord != null) { */

			MyCaseRecordsVO myCaseRecordsVO = new MyCaseRecordsVO();
			/*
			 * if (caseRecord != null && caseRecord.getCaseSK() != null) {
			 * myCaseRecordsVO.setCaseRecNo(caseRecord.getCaseSK() .toString());
			 *  }
			 */
			if (myCaseResultsVO.getCaseSK() != null) {
				myCaseRecordsVO.setCaseRecNo(myCaseResultsVO.getCaseSK()
						.toString());

			}
			/*
			 * if (caseRecord.getCaseType() != null) { CaseType caseType =
			 * caseRecord.getCaseType();
			 * myCaseRecordsVO.setCaseType(caseType.getDescription());
			 * logger.debug("Casetype from Details:" +
			 * myCaseRecordsVO.getCaseType());
			 *  }
			 */
			myCaseRecordsVO.setCaseType(myCaseResultsVO.getCaseTypeDesc());
			if (myCaseResultsVO.getOpenDate() != null) {
				myCaseRecordsVO.setOpenDate(dateConverter(myCaseResultsVO
						.getOpenDate()));
			}
			if (myCaseResultsVO.getPriorityCode() != null) {
				myCaseRecordsVO.setPriorityCode(myCaseResultsVO
						.getPriorityCode());
				String priorityDesc = ContactManagementHelper
						.setShortDescription(FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_CASE_PRTY_CD,
								myCaseRecordsVO.getPriorityCode());
				
				myCaseRecordsVO.setPriorityCodeDesc(priorityDesc);

			}
			if (myCaseResultsVO.getStatusCode() != null) {
				myCaseRecordsVO.setStatusCode(myCaseResultsVO.getStatusCode());
				String statusDesc = ContactManagementHelper
						.setShortDescription(FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_CASE_STAT_CD,
								myCaseRecordsVO.getStatusCode());
				
				myCaseRecordsVO.setStatusCodeDesc(statusDesc);

			}
			/*
			 * To get Entity type
			 */
			//	Set entitySet = caseRecord.getCaseCommonEntityCrossRefs();
			//commented for unused variables
			//String entityTypeCd = null;
			//Long entitySk = null;
			//String entityName = null;
			if (myCaseResultsVO.getEntitySK() != null) {
				myCaseRecordsVO.setEntitySK(myCaseResultsVO.getEntitySK()
						.toString());
			}
			myCaseRecordsVO.setEntityType(myCaseResultsVO.getEntityType());
			String entityTypeDesc = ContactManagementHelper
					.setShortDescription(FunctionalAreaConstants.GENERAL,//chaged
																		 // for
																		 // defect
																		 // ESPRD00595132
							ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
							myCaseRecordsVO.getEntityType());
			myCaseRecordsVO.setEntityTypeDesc(entityTypeDesc);
			myCaseRecordsVO.setEntityName(myCaseResultsVO.getEntityName());

			/*
			 * if (entitySet != null) { for (Iterator iter1 =
			 * entitySet.iterator(); iter1.hasNext();) {
			 * CaseCommonEntityCrossRef caseCommonEntityCrossRef =
			 * (CaseCommonEntityCrossRef) iter1 .next();
			 * 
			 * entityTypeCd = caseCommonEntityCrossRef
			 * .getCommonEntityTypeCode();
			 * 
			 * entitySk = caseCommonEntityCrossRef.getCommonEntitySK();
			 * logger.debug("EntitySk from Crossref:" + entitySk);
			 * myCaseRecordsVO.setEntitySK(entitySk.toString());
			 * logger.debug("entity Sk from mycase " +
			 * myCaseRecordsVO.getEntitySK());
			 * 
			 * myCaseRecordsVO.setEntityType(entityTypeCd);
			 * logger.debug("EntityName From MyCaseVo:" +
			 * myCaseRecordsVO.getEntityType());
			 * 
			 * String entityTypeDesc = ContactManagementHelper
			 * .setShortDescription( FunctionalAreaConstants.CONTACT_MGMT,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
			 * myCaseRecordsVO.getEntityType());
			 * myCaseRecordsVO.setEntityTypeDesc(entityTypeDesc);
			 * myCaseRecordsVO.setEntityName(getEntityName( entityTypeCd,
			 * entitySk)); } }
			 */
			/*CaseDetailsVO casedetailsVO = addCaseDetailsNew(myCaseResultsVO);

			myCaseRecordsVO.setCaseDetailsVO(casedetailsVO);*/
			myCaseVOList.add(myCaseRecordsVO);
		}
		//	}
		return myCaseVOList;
	}

	/**
	 * Method to add the details of the Cr.
	 * 
	 * @param caseRecord
	 *            The caseRecord to set.
	 * @return CaseDetailsVO.
	 */
	public CaseViewVO addCaseDetails(CaseRecord caseRecord) {

		CaseViewVO detailsVO = new CaseViewVO();
		MyTaskControllerBean controllerBean = new MyTaskControllerBean();
		
		if (caseRecord.getCaseSK() != null) {
			detailsVO.setCaseSK(caseRecord.getCaseSK());
		}
		
		//for defect ESPRD00678075
		if(caseRecord.getCaseType()!=null){
			detailsVO.setCaseType(caseRecord.getCaseType().getDescription());
		}
		
		if (caseRecord.getTitle() != null) {
			detailsVO.setCaseTitle(caseRecord.getTitle());
		}

		if (caseRecord.getCaseCreatedByWorkUnit() != null) {
			//for defect ESPRD00809473
			/** If the case record is created by work unit via ARS batch job then
			 *  the created by field value as department name should be retrieved 
			 *  from department table(G_DEPT_TB). If it is created by user then retrieved as 
			 *  'firstname, lastname' from G_USER_TB.
			 * */
			 //Modified for defect ESPRD00809473
			if (caseRecord.getCaseCreatedByWorkUnit().getEnterpriseUser() != null) {
				if(caseRecord.getCaseCreatedByWorkUnit()
						.getEnterpriseUser().getFirstName()==null){
					detailsVO.setCreatedBy(caseRecord.getCaseCreatedByWorkUnit()
									.getEnterpriseUser().getLastName());
				}else if(caseRecord.getCaseCreatedByWorkUnit()
						.getEnterpriseUser().getLastName()==null){
					detailsVO.setCreatedBy(caseRecord.getCaseCreatedByWorkUnit()
									.getEnterpriseUser().getFirstName());
				}else{
				detailsVO.setCreatedBy(caseRecord.getCaseCreatedByWorkUnit()
						.getEnterpriseUser().getFirstName()
						+ ", "
						+ caseRecord.getCaseCreatedByWorkUnit()
								.getEnterpriseUser().getLastName());
				}
				if(logger.isDebugEnabled())
				{
				logger.debug("CreatedBy From DetailsVo:"
						+ detailsVO.getCreatedBy());
				}
			} else if (caseRecord.getCaseCreatedByWorkUnit().getDepartment() != null) {
				if (!ContactManagementConstants.EMPTY_STRING.equals(caseRecord
						.getCaseCreatedByWorkUnit().getDepartment().getName())) {
					detailsVO.setCreatedBy(caseRecord
							.getCaseCreatedByWorkUnit().getDepartment()
							.getName());
				}
			}
		}

		String noteText = null;

		if (caseRecord.getOpenDate() != null) {
			Date openDate = null;
			openDate = caseRecord.getOpenDate();
			detailsVO.setCreatedDate(openDate);
			if(logger.isDebugEnabled())
			{
			logger.debug("Created Date:" + detailsVO.getCreatedDate());
			}

		}

		if (caseRecord.getNoteSet() != null) {
			if(logger.isDebugEnabled())
			{
			logger.debug("caserecord notesetSk:"
					+ caseRecord.getNoteSet().getNoteSetSK());
			}
			NoteSet noteSet = null;
			NoteSetVO noteSetVO = new NoteSetVO();
			ContactHelper contactHelper = new ContactHelper();
			
			try {
				noteSet = controllerBean.getNoteSet(caseRecord.getNoteSet()
						.getNoteSetSK());
				
				// Below code added for defect ESPRD00937627 to display the correct note in Case Note Details box.
				
				noteSetVO = contactHelper.convertNoteSetDomainToVO(noteSet);
				if(null != noteSetVO)
				{
				List noteList=noteSetVO.getNotesList();
				if (noteList != null && !noteList.isEmpty())
				{
					sortListByDate(noteList);
					CommonNotesVO commonNoteVO = (CommonNotesVO) noteList.get(0);
					detailsVO.setNoteSet(commonNoteVO.getNoteText());
				}
				}
				

			} catch (CommonNoteNotFoundException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}
			/*if (noteSet != null) {
				
				Set notes = noteSet.getNotes();
				if(logger.isDebugEnabled())
				{
				logger.debug("notes size from noteset:" + notes.size());
				}

				for (Iterator iter1 = notes.iterator(); iter1.hasNext();) {
					Note noteCase = (Note) iter1.next();

					noteText = (noteCase.getCex() == null) ? null : noteCase
							.getCex().getDescriptionClob();
					
					break;
				}
			}
			detailsVO.setNoteSet(noteText);*/

		}
		return detailsVO;
	}

	/**
	 * Method to add the details of the Cr.
	 * 
	 * @param myCaseResultsVo
	 *            The caseRecord to set.
	 * @return MyCaseResultsVO.
	 */
	public CaseViewVO addCaseDetailsNew(MyCaseResultsVO myCaseResultsVo) {

		CaseViewVO detailsVO = new CaseViewVO();
		//    MyTaskControllerBean controllerBean = new MyTaskControllerBean(); //
		// find bug issue
		
		if (myCaseResultsVo.getCaseSK() != null) {
			detailsVO.setCaseSK(myCaseResultsVo.getCaseSK());
		}

		detailsVO.setCreatedBy(myCaseResultsVo.getFirstName() + ", "
				+ myCaseResultsVo.getLastName());
		String noteText = null;

		if (myCaseResultsVo.getOpenDate() != null) {
			Date openDate = null;
			openDate = myCaseResultsVo.getOpenDate();
			detailsVO.setCreatedDate(openDate);
			if(logger.isDebugEnabled())
			{
			logger.debug("Created Date:" + detailsVO.getCreatedDate());
			}

		}

		return detailsVO;
	}

	/**
	 * This method convert all the Correspondence do's to CrWatchList vo's.
	 * 
	 * @param caseList
	 *            The caseList to set.
	 * @return List.
	 */
	public List getCaseWatchListVOList(List caseList, Map userMap) {
		List caseWatchVOList = new ArrayList();

		

		//	CaseWatchList caseWatchList = new CaseWatchList(); // find bug issue
		Iterator iterator = caseList.iterator();

		while (iterator.hasNext()) {
			MyCaseResultsVO myCaseResultsVO = (MyCaseResultsVO) iterator.next();

			CaseRecWatchListVO watchListVO = new CaseRecWatchListVO();
			
			if (myCaseResultsVO.getCaseSK() != null) {
				watchListVO
						.setCaseRecNo(myCaseResultsVO.getCaseSK().toString());
				
			}

			if (myCaseResultsVO.getOpenDate() != null) {
				watchListVO.setOpenDate(dateConverter(myCaseResultsVO
						.getOpenDate()));
			}
			if (myCaseResultsVO.getPriorityCode() != null) {

				watchListVO.setPriorityCode(myCaseResultsVO.getPriorityCode());
				
				String priorityDesc = ContactManagementHelper
						.setShortDescription(FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_CASE_PRTY_CD,
								watchListVO.getPriorityCode());

				watchListVO.setPriorityCodeDesc(priorityDesc);
			}
			if (myCaseResultsVO.getStatusCode() != null) {
				watchListVO.setStatusCode(myCaseResultsVO.getStatusCode());
				String statusDesc = ContactManagementHelper
						.setShortDescription(FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_CASE_STAT_CD,
								watchListVO.getStatusCode());

				watchListVO.setStatusCodeDesc(statusDesc);
			}

			/*
			 * if (myCaseResultsVO.getWorkUnitSK() != null) { if
			 * (myCaseResultsVO.getWorkUnitTypeCode() != null &&
			 * myCaseResultsVO.getWorkUnitTypeCode().equals("U")) {
			 * 
			 * String userSk = myCaseResultsVO.getWorkUnitSK().toString(); if
			 * (userMap != null && userMap.get(userSk) != null) { String user =
			 * (String) userMap.get(userSk);
			 * watchListVO.setUserOrWorkUnit(user);
			 * watchListVO.setUserName(user); }
			 *  } }
			 */

			//Added for the DEFECT:ESPRD00677756
			if (myCaseResultsVO.getWorkUnitTypeCode() != null) {
				if (myCaseResultsVO.getWorkUnitTypeCode().equals("U")) {

					if (myCaseResultsVO.getLastName() != null
							&& myCaseResultsVO.getFirstName() != null) {

						watchListVO.setUserOrWorkUnit(myCaseResultsVO
								.getLastName()
								+ ","
								+ myCaseResultsVO.getFirstName()
								+ "-"
								+ myCaseResultsVO.getUserID());

					} else if (myCaseResultsVO.getLastName() == null) {

						watchListVO.setUserOrWorkUnit(myCaseResultsVO
								.getFirstName()
								+ "-" + myCaseResultsVO.getUserID());

					} else if (myCaseResultsVO.getFirstName() == null) {

						watchListVO.setUserOrWorkUnit(myCaseResultsVO
								.getLastName()
								+ "-" + myCaseResultsVO.getUserID());

					}
				} else if (myCaseResultsVO.getWorkUnitTypeCode().equals("W")) {
					
					watchListVO.setUserOrWorkUnit(myCaseResultsVO
							.getWorkUnitName());
				}
			}
			//End for the DEFECT:ESPRD00677756
			watchListVO.setCaseType(myCaseResultsVO.getCaseTypeDesc());
			watchListVO.setEntityType(myCaseResultsVO.getEntityType());

			if (myCaseResultsVO.getEntitySK() != null) {
				watchListVO.setEntitySk(myCaseResultsVO.getEntitySK()
						.toString());
			}
			//added for the defect ESPRD00678094
			//"FindBugs" issue fixed
			/*String entityTypeDesc = ReferenceServiceDelegate
					.getReferenceSearchShortDescription(
							FunctionalAreaConstants.GENERAL,
							ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
							new Long(80), watchListVO.getEntityType());*/
			String entityTypeDesc = ReferenceServiceDelegate
			.getReferenceSearchShortDescription(
					FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
					Long.valueOf(80), watchListVO.getEntityType());
			/*
			 * ContactManagementHelper .setShortDescription(
			 * FunctionalAreaConstants.CONTACT_MGMT,
			 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
			 * watchListVO.getEntityType());
			 */
			//end for the defect ESPRD00678094
			watchListVO.setEntityTypeDesc(entityTypeDesc);
			watchListVO.setEntityName(myCaseResultsVO.getEntityName());

			CaseViewVO casedetailsVO = addCaseDetailsNew(myCaseResultsVO);
			
			watchListVO.setCaseDetailsVO(casedetailsVO);
			caseWatchVOList.add(watchListVO);

		}

		//}

		return caseWatchVOList;
	}

	/**
	 * This method convert all the Correspondence do's to My group Cr vo's.
	 * 
	 * @param caseList
	 *            The caseList to set.
	 * @return List.
	 */
	public List getMyGrpCaseVOList(List caseList, Map deptMap) {
		
		List grpCaseVOList = new ArrayList();
		String deptName = null;
		if(logger.isInfoEnabled())
		{
		logger.info("caseList size:::::mk::" + caseList.size());
		}
		for (Iterator iter = caseList.iterator(); iter.hasNext();) {
			MyCaseResultsVO myCaseResultsVO = (MyCaseResultsVO) iter.next();

			if (myCaseResultsVO != null) {
				GroupCaseRecordsVO groupCaseVO = new GroupCaseRecordsVO();

				if (myCaseResultsVO.getCaseSK() != null) {

					groupCaseVO.setCaseRecNo(myCaseResultsVO.getCaseSK()
							.toString());
				}
				if (myCaseResultsVO.getOpenDate() != null) {
					groupCaseVO.setOpenDate(dateConverter(myCaseResultsVO
							.getOpenDate()));
				}
				if (myCaseResultsVO.getPriorityCode() != null) {
					groupCaseVO.setPriorityCode(myCaseResultsVO
							.getPriorityCode());
					String priorityDesc = ContactManagementHelper
							.setShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.G_CASE_PRTY_CD,
									groupCaseVO.getPriorityCode());
					
					groupCaseVO.setPriorityCodeDesc(priorityDesc);
				}
				if (myCaseResultsVO.getStatusCode() != null) {
					groupCaseVO.setStatusCode(myCaseResultsVO.getStatusCode());
					String statusDesc = ContactManagementHelper
							.setShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.G_CASE_STAT_CD,
									groupCaseVO.getStatusCode());
					groupCaseVO.setStatusCodeDesc(statusDesc);
				}
				groupCaseVO.setCaseType(myCaseResultsVO.getCaseTypeDesc());

				groupCaseVO.setEntityType(myCaseResultsVO.getEntityType());
				if (myCaseResultsVO.getEntitySK() != null) {
					groupCaseVO.setEntitySk(myCaseResultsVO.getEntitySK()
							.toString());
				}
				
				//ADDED FOR THE DEFECT ESPRD00678089
				//"FindBugs" issue fixed
				/*String entityTypeDesc = ReferenceServiceDelegate
						.getReferenceSearchShortDescription(
								FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
								new Long(80), groupCaseVO.getEntityType());*/
				String entityTypeDesc = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
						Long.valueOf(80), groupCaseVO.getEntityType());
				/*
				 * ContactManagementHelper .setShortDescription(
				 * FunctionalAreaConstants.CONTACT_MGMT,
				 * ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
				 * groupCaseVO.getEntityType());
				 */
				//END FOR THE DEFECT ESPRD00678089
				groupCaseVO.setEntityTypeDesc(entityTypeDesc);
				groupCaseVO.setEntityName(myCaseResultsVO.getEntityName());
				if (myCaseResultsVO.getWorkUnitSK() != null) {
					groupCaseVO.setWorkUnit(myCaseResultsVO.getWorkUnitSK()
							.toString());
					if (deptMap != null) {
						deptName = (String) deptMap.get(myCaseResultsVO
								.getWorkUnitSK().toString());
						groupCaseVO.setDeptName(deptName);
						//ADDED FOR THE DEFECT:ESPRD00677739
						groupCaseVO.setWorkUnit(deptName);

					}
				}

				//CaseDetailsVO casedetailsVO = new CaseDetailsVO();
				//casedetailsVO = addCaseDetailsNew(myCaseResultsVO);
				
				

				//groupCaseVO.setCaseDetailsVO(casedetailsVO);
				
				/*
				 * 1. Create CaseSk, CreatedBy, CreatedDate/Open Date in groupCaseVO
				 * 
				 */

				grpCaseVOList.add(groupCaseVO);
			}
		}

		return grpCaseVOList;
	}

	/**
	 * @return Returns the createdOrOpened.
	 */
	public boolean isCreatedOrOpened() {
		return createdOrOpened;
	}

	/**
	 * @param createdOrOpened
	 *            The createdOrOpened to set.
	 */
	public void setCreatedOrOpened(boolean createdOrOpened) {
		this.createdOrOpened = createdOrOpened;
	}

	/**
	 * @param myCaseRecordsVO
	 * @param entityTypeCd
	 * @return
	 */
	private String getEntityName(String entityTypeCd, Long entitySk) {
		String entityName = null;
		if (entityTypeCd != null) {
			StringBuffer stringBuffer = new StringBuffer();
			if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM)) {

				
				MemberInformationDelegate memberDelegate = new MemberInformationDelegate();
				MemberInformationRequestVO memberVO = new MemberInformationRequestVO();
				try {
					Long systemID = memberDelegate.getMemberID(entitySk);
					if (systemID != null) {
						memberVO.setMemberSysID(systemID);
						Member member = memberDelegate
								.getMemberDetail(memberVO);
						if (member != null) {
							if (member.getDemographicInformation().getName()
									.getFirstName() != null) {
								stringBuffer.append(member
										.getDemographicInformation().getName()
										.getFirstName());
								stringBuffer.append(" ");
							}
							if (member.getDemographicInformation().getName()
									.getMiddleName() != null) {
								stringBuffer.append(member
										.getDemographicInformation().getName()
										.getMiddleName());
								stringBuffer.append(" ");
							}
							if (member.getDemographicInformation().getName()
									.getLastName() != null) {
								stringBuffer.append(member
										.getDemographicInformation().getName()
										.getLastName());
							}
						}
						entityName = stringBuffer.toString();
					}
				} catch (MemberBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
				}
			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_PROV)) {

				
				ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
				ProviderInformationRequestVO providerVO = new ProviderInformationRequestVO();
				try {
					Long pSysId = providerInformationDelegate
							.getProviderSysID(entitySk);
					if (pSysId != null) {
						providerVO.setProviderSysID(pSysId);
						Provider provider = providerInformationDelegate
								.getProviderDetails(providerVO);
						if (provider != null
								&& provider.getName().getSortName() != null) {
							entityName = provider.getName().getSortName();
						}
					}
				} catch (EnterpriseBaseBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
				}

			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNMEM)
					|| entityTypeCd
							.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV)) {

				
				CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
				try {
					SpecificEntity specficEntity = cmEntityDelegate
							.getSpecificEntityDetails(entitySk);
					if (specficEntity != null) {
						StringBuffer sb = new StringBuffer();
						if (specficEntity.getName() != null) {
							if (specficEntity.getName().getFirstName() != null) {
								sb.append(specficEntity.getName()
										.getFirstName());
								sb.append(" ");
							}
							if (specficEntity.getName().getMiddleName() != null) {
								sb.append(specficEntity.getName()
										.getMiddleName());
								sb.append(" ");
							}
							if (specficEntity.getName().getLastName() != null) {
								sb
										.append(specficEntity.getName()
												.getLastName());
								sb.append(" ");
							}
						}
						if (specficEntity.getOrganizationName() != null) {
							sb.append(specficEntity.getOrganizationName());
						}

						entityName = sb.toString();
					}
				} catch (CMEntityFetchBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
				}

			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TPL)) {

				
				CMDelegate cmDelegate = new CMDelegate();
				try {
					TPLCarrier tplCarrier = cmDelegate.getTPLCarrier(entitySk);
					if (tplCarrier != null
							&& tplCarrier.getCarrierName() != null) {
						entityName = tplCarrier.getCarrierName();
					}
				} catch (CorrespondenceRecordFetchBusinessException e) {
					logger.error(e.getMessage(), e);

				}
			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TP)) {

				
				CaseDelegate cmDelegate = new CaseDelegate();
				try {
					TPLPolicyHolder tplPolicyHolder = cmDelegate
							.getTPLPloicyHolder(entitySk);
					if (tplPolicyHolder != null
							&& tplPolicyHolder.getName() != null) {
						if (tplPolicyHolder.getName().getFirstName() != null) {
							entityName = tplPolicyHolder.getName()
									.getFirstName();
						}
						if (tplPolicyHolder.getName().getLastName() != null) {
							entityName = entityName
									+ tplPolicyHolder.getName().getLastName();
						}
					}
				} catch (EnterpriseBaseBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}

				}
			}
		}
		return entityName;
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

	// Below method is added for defect ESPRD00937627
	private void sortListByDate(List notesList) {

		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CommonNotesVO commonNotesVO1 = (CommonNotesVO) obj1;
				CommonNotesVO commonNotesVO2 = (CommonNotesVO) obj2;
				commonNotesVO1.setChecked(false);
				commonNotesVO2.setChecked(false);
				try {
					
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

}