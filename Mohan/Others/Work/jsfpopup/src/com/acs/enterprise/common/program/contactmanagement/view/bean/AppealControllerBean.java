/*
 * Created on Jan 17, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.audit.application.exception.GlobalAuditsBusinessException;
import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntityType;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.exception.CommonEntityUIException;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.contactmanagement.application.dataaccess.exception.AppealUpdateDataException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.AppealCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.AppealFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.AppealUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.ClaimCorrectionSearchGetException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.AppealDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.AdministrativeHearing;
import com.acs.enterprise.common.program.contactmanagement.common.domain.AppealTracking;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRecord;
import com.acs.enterprise.common.program.contactmanagement.common.vo.ClaimCorrectionResponseVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.ClaimCorrectionSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.SALineItemsVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.AppealDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.Executor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AdminHearingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AppealVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.acs.enterprise.common.view.vo.CommonMemberDetailsVO;
import com.acs.enterprise.common.view.vo.CommonProviderDetailsVO;
import com.acs.enterprise.mmis.operations.claimadministration.common.delegate.ClaimCorrectionDelegate;
import com.acs.enterprise.mmis.operations.common.vo.ClaimInquirySearchResultsVO;
import com.acs.enterprise.mmis.operations.serviceauthorization.common.delegate.ServiceAuthDelegate;
import com.acs.enterprise.mmis.operations.serviceauthorization.common.domain.SALineItemInfo;
import com.acs.enterprise.mmis.operations.serviceauthorization.common.domain.ServiceAuthorization;


/**
 * @author sivngan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class AppealControllerBean extends EnterpriseBaseControllerBean {

	
	/** Creates an instance of the logger. * */
	
	private static final EnterpriseLogger log = EnterpriseLogFactory
	.getLogger(AppealControllerBean.class);

	/** Holds loadValidValues */
	private String loadValidValues;

	/** Holds memberAppealsSearch */
	private String memberAppealsSearch;

	/** Holds providerAppealsSearch */
	private String providerAppealsSearch;

	/** Holds addAppealsCritria */
	private String addAppealsCritria;

	/** Holds the Grey mode */
	private String link2Show;

	/** Holds saLineAppStatus */
	private List saLineAppStatus;

	/** Holds saLineAppResult */
	private List saLineAppResult;
	
	/** Holds validation of Appeal */
	private boolean validateAppealFlg;
	
	// Moved to ContactManagementConstants.java
	//public static final String APPEAL_DATA_BEAN = "appealDataBean";
	
	private AppealDataBean appealDataBean;
	
	private CommonEntityDataBean commonEntityDataBean;
	
	/**
	 * @return Returns the saLineAppResult.
	 */
	public List getSaLineAppResult() {
		return saLineAppResult;
	}

	/**
	 * @param saLineAppResult The saLineAppResult to set.
	 */
	public void setSaLineAppResult(List saLineAppResult) {
		this.saLineAppResult = saLineAppResult;
	}

	/**
	 * @return Returns the saLineAppStatus.
	 */
	public List getSaLineAppStatus() {
		return saLineAppStatus;
	}

	/**
	 * @param saLineAppStatus The saLineAppStatus to set.
	 */
	public void setSaLineAppStatus(List saLineAppStatus) {
		this.saLineAppStatus = saLineAppStatus;
	}

	private static final String PRV_APPEAL_TYPE = "providerAppealType";

	
	
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
     * This method will return the reference of the AppealTracking.
     * 
     * @return appealTracking
     */
    public AppealTracking getAppealTracking(AppealVO appealVO,
            List adminHearingList)
    {
	 	AppealTracking appealTracking = new AppealTracking();
		AppealDOConverter appealDOConverter = new AppealDOConverter();
		appealTracking = appealDOConverter.convertAppealVOToDO(
				appealDataBean.getAppealVO(), appealDataBean
						.getAdminHearingList());
		
		if (!adminHearingList.isEmpty())
        {
            
            //To Do get max of Seq number from Data Base
            try
            {
                AppealDelegate appealDelegate = new AppealDelegate();
                Integer maxSeqNum = appealDelegate.getMaxCaseHearSequenceNum();
                appealTracking.setAdminHearings(appealDOConverter.convertVoToDo(adminHearingList,
                        appealVO.getCaseSK(), appealTracking, maxSeqNum));
            }
            catch (AppealFetchBusinessException afe)
            {
                ContactManagementHelper
                        .setErrorMessage(MaintainContactManagementUIConstants.SAVE_FAILURE);
                if(log.isErrorEnabled()){
                log.error("eror in getMaxCaseHearSequenceNum");
                }
            }

        }
		return appealTracking;
    }

	/**
	 * this method is usd to add Admin Hearing
	 * 
	 * @return String
	 */
	public String addAdminHearing() {
		if(log.isDebugEnabled()){
			log.debug("Inside add adminhearing");
		}
		
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.getAppealVO().setAdminHearingVO(
				new AdminHearingVO());
		appealDataBean.setAddAdminHearingFlag(true);
		
		appealDataBean.setEditAdminHearingFlag(false);
		appealDataBean.setShowSuccessMsgFlag(false);
		appealDataBean.setShowDeletedMsgFlag(false);
		//notes section not rendering.
		renderNotNotes();
		CommonEntityDataBean commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();
		
		commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
				.getNotesList().clear();
		commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
				.setCurrentNote(ContactManagementConstants.EMPTY_STRING);
		commonEntityDataBean.getNoteList().clear();
		commonEntityDataBean.getTempNoteList().clear();
		commonEntityDataBean.setNoteText(ContactManagementConstants.EMPTY_STRING);

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to edit Admin Hearing
	 * 
	 * @return String
	 */
	public String editAdminHearing() {
		if(log.isDebugEnabled()){
			log.debug("Inside editAdminHearing");
		}
		AdminHearingVO tempAdminHearingVO = new AdminHearingVO();
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setEditAdminHearingFlag(true);
		appealDataBean.setAddAdminHearingFlag(false);
		appealDataBean.setShowSuccessMsgFlag(false);
		appealDataBean.setShowDeletedMsgFlag(false);
		
		//notes section not rendering.
		renderNotNotes();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		String indx = fc.getExternalContext().getRequestParameterMap().get(
				"rowIndex").toString();
		int intAdminHearingRowIndex = new Integer(indx).intValue();
		
		String index = Integer.toString(intAdminHearingRowIndex);
		if (index != null) {
			appealDataBean.setAdminHearingIndex(index);
			AdminHearingVO adminHearingVO = (AdminHearingVO) appealDataBean
					.getAdminHearingList().get(intAdminHearingRowIndex);
			//for ESPRD00782216 defect.
			//notes
			NoteSetVO noteSetVO = adminHearingVO.getAdminNotesetVO();
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();
			
			commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
					.getNotesList().clear();
			commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
					.setCurrentNote(ContactManagementConstants.EMPTY_STRING);
			
			if (adminHearingVO != null && adminHearingVO.getNoteList()!=null && !adminHearingVO.getNoteList().isEmpty()) {
				ArrayList noteList=(ArrayList)adminHearingVO.getNoteList();
				commonEntityDataBean.setNoteList((ArrayList)noteList.clone());
				commonEntityDataBean.setTempNoteList((ArrayList)noteList.clone());
				commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO().setNotesList((ArrayList)noteList.clone());
				appealDataBean.setNotesListIndicator(Boolean.TRUE);
			}else{
				commonEntityDataBean.getNoteList().clear();
				commonEntityDataBean.getTempNoteList().clear();
				appealDataBean.setNotesListIndicator(Boolean.FALSE);
			}

			/*if (commonEntityDataBean != null
					&& commonEntityDataBean.getCommonEntityVO() != null
					&& commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO() != null) {
				if (noteSetVO != null) {
					commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
							.setNotesList(noteSetVO.getNotesList());
					commonEntityDataBean.setNoteList(noteSetVO.getNotesList());
					commonEntityDataBean.setTempNoteList(noteSetVO.getNotesList());
					System.out.println("noteSetVO.getNotesList() $$$$$$$$$"+noteSetVO.getNotesList());
				}
			}*/
			
			try {
				
				BeanUtils.copyProperties(tempAdminHearingVO, adminHearingVO);
			} catch (IllegalAccessException e) {
				if(log.isErrorEnabled()){
					log.error("error in editAdminHearing IllegalAccessException");
				}
			} catch (InvocationTargetException e) {
				if(log.isErrorEnabled()){
					log.error("error in editAdminHearing InvocationTargetException");
				}
			}
		}
		appealDataBean.getAppealVO().setAdminHearingVO(tempAdminHearingVO);

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to save Admin Hearing
	 * 
	 * @return String
	 */
	public String saveAdminHearing() {
		if(log.isDebugEnabled()){
			log.debug("In saveAdminHearing------->");
		}
		
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		commonEntityDataBean = (CommonEntityDataBean) getDataBean(ContactManagementConstants.COMMON_ENTITY_DATA_BEAN);
		AdminHearingVO adminHearingVO = appealDataBean.getAppealVO()
				.getAdminHearingVO();

		appealDataBean.setShowFinalSuccessMsgFlag(false);
		appealDataBean.setShowSuccessMsgFlag(false);
		commonEntityDataBean.setDetailNotesSaveMsg(false);
		//To fix Defect ESPRD00696684
		if(adminHearingVO != null && (adminHearingVO.getStrAdminHearingDate()== null 
				|| adminHearingVO.getStrAdminHearingDate().trim().equals(""))
				&& adminHearingVO.getHearingResults()== null
				&& adminHearingVO.getHearingStatus()== null
				&& adminHearingVO.getDocketNumber().equals("")
                && adminHearingVO.getHearingOfficerName().equals("")
                && adminHearingVO.getHearingCitation().equals(""))
		{
			
			cancelAdminHearing();
		}else  if (adminHearingVO != null && validateAdminHearing(adminHearingVO)) { // Added for Find_Bugs-FIX
			//ESPRD00514325_UC-PGM-CRM-067_26AUG2010
			adminHearingVO.setHearingResultsDesc(ContactManagementHelper.setShortDescription(FunctionalAreaConstants.GENERAL,
                    ReferenceServiceDataConstants.G_CASE_HEAR_RSLTS_CD,
                    adminHearingVO.getHearingResults()));
			
			adminHearingVO.setHearingStatusDesc(ContactManagementHelper.setShortDescription(FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_CASE_HEAR_STAT_CD,
                    adminHearingVO.getHearingStatus()));
			
			//EOF ESPRD00514325_UC-PGM-CRM-067_26AUG2010
			if (appealDataBean.isAddAdminHearingFlag()) {
				if (adminHearingVO != null) {
					
					CommonEntityDataBean entityDataBean = ContactHelper
							.getCommonEntityDataBean();
					ArrayList adminNotesList = null;
					if (entityDataBean != null
							&& entityDataBean.getNoteList() != null) {
						adminNotesList = new ArrayList(entityDataBean.getNoteList());
					}
					//for ESPRD00782216 defect.
					if (adminNotesList != null && !adminNotesList.isEmpty()) {
						NoteSetVO adminNotesetVO=adminHearingVO.getAdminNotesetVO();
						if(adminNotesetVO==null){
							adminNotesetVO = new NoteSetVO();
						}
						adminNotesetVO.setNotesList((ArrayList) adminNotesList.clone());
						adminHearingVO.setAdminNotesetVO(adminNotesetVO);
						adminHearingVO.setNoteList((List) adminNotesList.clone());
						appealDataBean.setNotesListIndicator(Boolean.TRUE);
						log.debug("admin note list size $$$$$$$$"+adminNotesList.size());
					}else{
						appealDataBean.setNotesListIndicator(Boolean.FALSE);
					}
					

					appealDataBean.getAdminHearingList().add(
							adminHearingVO);
				}
				//AdminHearingVO tempAdminHearingVO = new AdminHearingVO();
				// Defect ESPRD00729574 modified
				/*appealDataBean.getAppealVO().setAdminHearingVO(
						tempAdminHearingVO);*/
				/*ContactManagementHelper
						.setErrorMessage(MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG);*/
				appealDataBean.setShowSuccessMsgFlag(true);
				appealDataBean.setShowDeletedMsgFlag(false);
				appealDataBean.setAdminHearingDataFlag(true);
				//added for the defect ESPRD00336213
				appealDataBean.setAddAdminHearingFlag(true);//To fix Defect ESPRD00696684
				
				int count = appealDataBean.getSmallSaveCount();
				appealDataBean.setSmallSaveCount(count + 1);
				
				editAdminHearingCalledFromAddModeSave(); // Defect ESPRD00729574 modified
			} else if (appealDataBean.isEditAdminHearingFlag()) {
				AdminHearingVO tempAdminHearingVO=new AdminHearingVO();
				//tempAdminHearingVO = appealDataBean.getAppealVO()
						//.getAdminHearingVO();
				// for notes
				if (adminHearingVO != null) {
					CommonEntityDataBean entityDataBean = ContactHelper
							.getCommonEntityDataBean();
					ArrayList adminNotesList = null;
					if (entityDataBean != null
							&& entityDataBean.getNoteList() != null) {
						adminNotesList = new ArrayList(entityDataBean.getNoteList());
					}
					//for ESPRD00782216 defect.
					if (adminNotesList != null && !adminNotesList.isEmpty()) {
						NoteSetVO adminNotesetVO=adminHearingVO.getAdminNotesetVO();
						if(adminNotesetVO==null){
							adminNotesetVO = new NoteSetVO();
						}
						adminNotesetVO.setNotesList((ArrayList) adminNotesList.clone());
						adminHearingVO.setAdminNotesetVO(adminNotesetVO);
						adminHearingVO.setNoteList((List) adminNotesList.clone());
					}
					
				}
				try {
					BeanUtils
							.copyProperties(tempAdminHearingVO, adminHearingVO);
				} catch (IllegalAccessException e) {
					if (log.isErrorEnabled()) {
						log.error("error in editAdminHearing IllegalAccessException");
					}
				} catch (InvocationTargetException e) {
					if (log.isErrorEnabled()) {
						log.error("error in editAdminHearing InvocationTargetException");
					}
				}
				appealDataBean.getAdminHearingList().add(
						Integer.parseInt(appealDataBean
								.getAdminHearingIndex()), tempAdminHearingVO);
				appealDataBean.getAdminHearingList().remove(
						Integer.parseInt(appealDataBean
								.getAdminHearingIndex()) + 1);
				// Defect ESPRD00729574 modified
				/*ContactManagementHelper
						.setErrorMessage(MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG);*/
				appealDataBean.setShowSuccessMsgFlag(true);
				appealDataBean.setShowDeletedMsgFlag(false);
				appealDataBean.setAdminHearingDataFlag(true);
				//	added for the defect ESPRD00336213
				appealDataBean.setEditAdminHearingFlag(true);//To fix Defect ESPRD00696684
				
			}
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to validate Admin Hearing
	 * 
	 * @return boolean
	 * @param adminHearingVO
	 *            AdminHearingVO
	 */
	private boolean validateAdminHearing(AdminHearingVO adminHearingVO) {
		ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
		boolean flag = true;
		String actionCode = null;
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		if (appealDataBean.isAddAdminHearingFlag()) {
			actionCode = MaintainContactManagementUIConstants.ADD;
		} else {
			actionCode = MaintainContactManagementUIConstants.UPDATE;
		}
		if (adminHearingVO.getStrAdminHearingDate() != null
				&& !MaintainContactManagementUIConstants.NULL
						.equals(adminHearingVO.getStrAdminHearingDate().trim())) {
			Date hrngDate = contactManagementHelper
					.dateConverter(adminHearingVO.getStrAdminHearingDate());
			if (hrngDate == null) {
				adminHearingVO.setAdminHearingDate(null);

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADMN_HRNG_DT_ID,
								MaintainContactManagementUIConstants.EDIT_ADMN_HRNG_DT_ID,
								actionCode);
				flag = false;
			} else {
				adminHearingVO.setAdminHearingDate(hrngDate);
			}
		}

		return flag;
	}

	/**
	 * this method is usd to reset Add Admin Hearing
	 * 
	 * @return String
	 */
	public String resetAddAdminHearing() {
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.getAppealVO().setAdminHearingVO(new AdminHearingVO());
		renderNotNotes();
		commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();

		commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
				.getNotesList().clear();
		commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
				.setCurrentNote(ContactManagementConstants.EMPTY_STRING);
		commonEntityDataBean.getNoteList().clear();
		commonEntityDataBean.getTempNoteList().clear();
		commonEntityDataBean
				.setNoteText(ContactManagementConstants.EMPTY_STRING);
		appealDataBean.setNotesListIndicator(Boolean.FALSE);
		appealDataBean.setShowSuccessMsgFlag(Boolean.FALSE);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to reset Edit Admin Hearing
	 * 
	 * @return String
	 */
	public String resetEditAdminHearing() {
		AdminHearingVO adminHearingVO = null;
		AdminHearingVO tempAdminHearingVO = new AdminHearingVO();
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		commonEntityDataBean = ContactHelper
		.getCommonEntityDataBean();
		String adminHearingIndex = appealDataBean.getAdminHearingIndex();
		adminHearingVO = (AdminHearingVO) appealDataBean
				.getAdminHearingList().get(Integer.parseInt(adminHearingIndex));
		try {
			BeanUtils.copyProperties(tempAdminHearingVO, adminHearingVO);
		} catch (IllegalAccessException e) {
			if(log.isErrorEnabled()){
			log.error("error in resetEditAdminHearing IllegalAccessException");
			}
		} catch (InvocationTargetException e) {
			if(log.isErrorEnabled()){
				log.error("error in resetEditAdminHearing InvocationTargetException");
				}
		}
		if (tempAdminHearingVO != null
				&& tempAdminHearingVO.getNoteList() != null
				&& !tempAdminHearingVO.getNoteList().isEmpty()) {
			ArrayList noteList = (ArrayList) tempAdminHearingVO.getNoteList();
			commonEntityDataBean.setNoteList((ArrayList) noteList.clone());
			commonEntityDataBean.setTempNoteList((ArrayList) noteList.clone());
			commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
					.setNotesList((ArrayList) noteList.clone());
			appealDataBean.setNotesListIndicator(Boolean.TRUE);
		} else {
			commonEntityDataBean.getNoteList().clear();
			commonEntityDataBean.getTempNoteList().clear();
			appealDataBean.setNotesListIndicator(Boolean.FALSE);
		}
		renderNotNotes();
		appealDataBean.getAppealVO().setAdminHearingVO(tempAdminHearingVO);
		appealDataBean.setShowSuccessMsgFlag(Boolean.FALSE);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to cancel Admin Hearing
	 * 
	 * @return String
	 */
	public String cancelAdminHearing() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setAddAdminHearingFlag(false);
		appealDataBean.setEditAdminHearingFlag(false);
		renderNotNotes();
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to cancel Member Appeals
	 * 
	 * @return String
	 */
	public String cancelMemberAppeals() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setShowMemberAppealsFlag(false);
		// Added for the Defect Id : ESPRD00728875
		// START
		appealDataBean.setCancelFlag(false);
		// END
		appealDataBean.setAddAppealFlag(false);
		appealDataBean.setEditAppealFlag(false);
		appealDataBean.setMemberAppealDataFlag(false);
		appealDataBean.setShowMemberInfoLabelFlag(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to cancel Provider Appeals
	 * 
	 * @return String
	 */
	public String cancelProviderAppeals() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setShowProviderAppealsFlag(false);
		//Added for the Defect Id : ESPRD00731889  
		// START
		appealDataBean.setCancelFlag(false);
		// END
		appealDataBean.setAddAppealFlag(false);
		appealDataBean.setEditAppealFlag(false);
		appealDataBean.setProviderAppealDataFlag(false);
		appealDataBean.setShowProviderInfoLabelFlag(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to cancel Appeals
	 * 
	 * @return String
	 */
	public String cancelAppeals() {
		if(log.isDebugEnabled()){
		log.debug("Inside Cancel Appeals");
		}
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setAddAppealFlag(false);
		appealDataBean.setEditAppealFlag(false);
		appealDataBean.setAdminHearingDataFlag(false);
		appealDataBean.setShowMemberAppealsFlag(false);
		appealDataBean.setShowProviderAppealsFlag(false);
		appealDataBean.setProviderAppealDataFlag(false);
		appealDataBean.setMemberAppealDataFlag(false);
		appealDataBean.setShowProviderInfoLabelFlag(false);
		appealDataBean.setShowMemberInfoLabelFlag(false);
		if(log.isDebugEnabled()){
		log.debug("End Cancel Appeals");
		}
		return MaintainContactManagementUIConstants.SUCCESS;

	}

	/**
	 * this method is usd to delete Admin Hearing
	 * 
	 * @return String
	 */
	public String deleteAdminHearing() {
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		AdminHearingVO adminHearingVO = (AdminHearingVO) appealDataBean
				.getAdminHearingList().get(
						Integer.parseInt(appealDataBean
								.getAdminHearingIndex()));

		if (adminHearingVO != null) {
			appealDataBean.getDeletedAdminHearingList()
					.add(adminHearingVO);
		}
		appealDataBean.getAdminHearingList().remove(
				Integer.parseInt(appealDataBean.getAdminHearingIndex()));

		ContactManagementHelper
				.setErrorMessage(MaintainContactManagementUIConstants.DEL_SUCCESS_MSG);
		if(log.isDebugEnabled()){
		log.debug("Index-->" + appealDataBean.getAdminHearingIndex());
		}
		if (appealDataBean.getAdminHearingIndex().equals(
				MaintainContactManagementUIConstants.STR_ZERO)) {
			appealDataBean.setAdminHearingDataFlag(false);
		}
		appealDataBean.setEditAdminHearingFlag(false);
		appealDataBean.setAddAdminHearingFlag(false);
		appealDataBean.setShowDeletedMsgFlag(true);
		appealDataBean.setShowSuccessMsgFlag(false);
		int count = appealDataBean.getSmallSaveCount();
		appealDataBean.setSmallSaveCount(count - 1);
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to reset Member Appeals
	 * 
	 * @return String
	 */
	public String resetMemberAppeals() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isDebugEnabled()){
		log.debug(" in resetMemberAppeals: "
				+ appealDataBean.getMemAppealIndex());
	    }
		if (appealDataBean.isMemberAppealDataFlag()
				&& !appealDataBean.getMemberAppealList().isEmpty()) {
			if (StringUtils.isNotBlank(appealDataBean.getMemAppealIndex())) {
				AppealVO tempAppealVO = new AppealVO();
				int intMemAppealRowIndex = Integer.parseInt(appealDataBean
						.getMemAppealIndex());
				AppealVO appealVO = (AppealVO) appealDataBean
						.getMemberAppealList().get(intMemAppealRowIndex);
				try {
					BeanUtils.copyProperties(tempAppealVO, appealVO);
					appealDataBean.setAppealVO(tempAppealVO);
					if (appealDataBean.getAppealVO().getAplTyCd()
							.equalsIgnoreCase("C")) {
						appealDataBean.setShowContReasonFlag(true);
					} else {
						appealDataBean.setShowContReasonFlag(false);
					}
				} catch (IllegalAccessException e) {
					if(log.isErrorEnabled()){
					log.error("Exception while copying IllegalAccessException");
					}
				} catch (InvocationTargetException e) {
					if(log.isErrorEnabled()){
						log.error("Exception while copying InvocationTargetException");
						}
				}

			}
		} else {
			appealDataBean.setAppealVO(new AppealVO());
			appealDataBean.setShowContReasonFlag(false);
		}
		if(log.isDebugEnabled()){
		log.debug("ResetMemberAppeals End");
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to reset Provider Appeals
	 * 
	 * @return String
	 */
	public String resetProviderAppeals() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isDebugEnabled()){
		log.debug(" in resetProviderAppeals: "
				+ appealDataBean.getPrvAppealIndex());
	    }
		if (appealDataBean.isProviderAppealDataFlag()
				&& !appealDataBean.getProviderAppealList().isEmpty()) {
			if (StringUtils.isNotBlank(appealDataBean.getPrvAppealIndex())) {
				AppealVO tempAppealVO = new AppealVO();
				int intPrvAppealRowIndex = Integer.parseInt(appealDataBean
						.getPrvAppealIndex());
				
				AppealVO appealVO = (AppealVO) appealDataBean
						.getProviderAppealList().get(intPrvAppealRowIndex);
				try {
					BeanUtils.copyProperties(tempAppealVO, appealVO);
					appealDataBean.setAppealVO(tempAppealVO);
					if (appealDataBean.getAppealVO().getAplTyCd()
							.equalsIgnoreCase("C")) {
						appealDataBean.setShowContReasonFlag(true);
					} else {
						appealDataBean.setShowContReasonFlag(false);
					}
				} catch (IllegalAccessException e) {
					if(log.isErrorEnabled()){
					log.error("Exception while copying IllegalAccessException");
					}
				} catch (InvocationTargetException e) {
					if(log.isErrorEnabled()){
						log.error("Exception while copying InvocationTargetException");
						}
				}

			}
		} else {
			appealDataBean.setAppealVO(new AppealVO());
			appealDataBean.setShowContReasonFlag(false);
		}
		if(log.isDebugEnabled()){
		log.debug("ResetMemberAppeals End");
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * this method is usd to reset Appeals
	 * 
	 * @return String
	 */
	public String resetAppeals() {
		if(log.isDebugEnabled()){
		log.debug("Inside ResetAppeals");
		}
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setShowFinalSuccessMsgFlag(false);
		appealDataBean.setShowSuccessMsgFlag(false);
		//check if appeal exists for caseSK
		boolean appealExists = isAppealExistsForCase();

		if (appealDataBean.isAddAppealFlag()) {
			appealDataBean.setAppealVO(new AppealVO());
			appealDataBean.setShowContReasonFlag(false);

		} else if (appealDataBean.isEditAppealFlag()) {
			AppealVO oldAppealVO = appealDataBean.getTempAppealVO();
			AppealVO appealVO = new AppealVO();
			try {
				BeanUtils.copyProperties(appealVO, oldAppealVO);
			} catch (IllegalAccessException e) {
				if(log.isErrorEnabled()){
				log.error("error in resetEditAppeal IllegalAccessException");
				}
			} catch (InvocationTargetException e) {
				if(log.isErrorEnabled()){
					log.error("error in resetEditAppeal InvocationTargetException");
					}
			}

			/*if (oldAppealVO.getAplDt() != null) {
				appealVO.setAplDt(oldAppealVO.getAplDt());
			}
			if (oldAppealVO.getCaseAplStatCdDt() != null) {
				appealVO.setCaseAplStatCdDt(oldAppealVO.getCaseAplStatCdDt());
			}
			if (oldAppealVO.getAddlInfoReqDt() != null) {
				appealVO.setAddlInfoReqDt(oldAppealVO.getAddlInfoReqDt());
			}
			if (oldAppealVO.getAddlInfoDueDt() != null) {
				appealVO.setAddlInfoDueDt(oldAppealVO.getAddlInfoDueDt());
			}
			if (oldAppealVO.getAddlInfoNotfyLtrSentDt() != null) {
				appealVO.setAddlInfoNotfyLtrSentDt(oldAppealVO
						.getAddlInfoNotfyLtrSentDt());
			}
			if (oldAppealVO.getAddlInfo2NdRvsdDueDt() != null) {
				appealVO.setAddlInfo2NdRvsdDueDt(oldAppealVO
						.getAddlInfo2NdRvsdDueDt());
			}
			if (oldAppealVO.getAddlInfoRecdDt() != null) {
				appealVO.setAddlInfoRecdDt(oldAppealVO.getAddlInfoRecdDt());
			}
			if (oldAppealVO.getAddlInfoRvsdRevwDueDt() != null) {
				appealVO.setAddlInfoRvsdRevwDueDt(oldAppealVO
						.getAddlInfoRvsdRevwDueDt());
			}
			if (oldAppealVO.getAddlInfo2NdRvsdDueDt() != null) {
				appealVO.setAddlInfo2NdRvsdDueDt(oldAppealVO
						.getAddlInfo2NdRvsdDueDt());
			}
			if (oldAppealVO.getAddlInfoRespReqDueDt() != null) {
				appealVO.setAddlInfoRespReqDueDt(oldAppealVO
						.getAddlInfoRespReqDueDt());
			}
			if (oldAppealVO.getAddlInfoFiledDt() != null) {
				appealVO.setAddlInfoFiledDt(oldAppealVO.getAddlInfoFiledDt());
			}
			if (oldAppealVO.getAsgnDt() != null) {
				appealVO.setAsgnDt(oldAppealVO.getAsgnDt());
			}
			if (oldAppealVO.getDhhsDecisDueDt() != null) {
				appealVO.setDhhsDecisDueDt(oldAppealVO.getDhhsDecisDueDt());
			}
			if (oldAppealVO.getDhhsDecisDueDt() != null) {
				appealVO.setDhhsDecisDueDt(oldAppealVO.getDhhsDecisDueDt());
			}
			if (oldAppealVO.getDhhsSentDt() != null) {
				appealVO.setDhhsSentDt(oldAppealVO.getDhhsSentDt());
			}
			if (oldAppealVO.getInfrmlRevwAckSentDt() != null) {
				appealVO.setInfrmlRevwAckSentDt(oldAppealVO
						.getInfrmlRevwAckSentDt());
			}
			if (oldAppealVO.getInfrmlRevwDueDt() != null) {
				appealVO.setInfrmlRevwDueDt(oldAppealVO.getInfrmlRevwDueDt());
			}
			if (oldAppealVO.getInfrmlRevwReqDt() != null) {
				appealVO.setInfrmlRevwReqDt(oldAppealVO.getInfrmlRevwReqDt());
			}
			if (oldAppealVO.getInfrmlRevwSentDt() != null) {
				appealVO.setInfrmlRevwSentDt(oldAppealVO.getInfrmlRevwSentDt());
			}
			if (oldAppealVO.getRcnsdrtnDecisDt() != null) {
				appealVO.setRcnsdrtnDecisDt(oldAppealVO.getRcnsdrtnDecisDt());
			}
			if (oldAppealVO.getRcnsdrtnNotfyLtrSentDt() != null) {
				appealVO.setRcnsdrtnNotfyLtrSentDt(oldAppealVO
						.getRcnsdrtnNotfyLtrSentDt());
			}
			if (oldAppealVO.getRcnsdrtnRtrnDt() != null) {
				appealVO.setRcnsdrtnRtrnDt(oldAppealVO.getRcnsdrtnRtrnDt());
			}
			appealVO.setCaseAplTyCd(oldAppealVO.getCaseAplTyCd());
			log.debug("Appealtype code from newAppealVO:"
					+ appealVO.getAplTyCd());
			if (oldAppealVO.getTcnNum() != null) {
				appealVO.setTcnNum(oldAppealVO.getTcnNum());
			}
			log.debug("u r here2");
			appealVO.setAplTyCd(oldAppealVO.getAplTyCd());

			if (oldAppealVO.getCaseAplContinuanceRsnCd() != null) {
				appealVO.setCaseAplContinuanceRsnCd(oldAppealVO
						.getCaseAplContinuanceRsnCd());
				log.debug(" appealVO.getCaseAplContinuanceRsnCd"
						+ appealVO.getCaseAplContinuanceRsnCd());

			}
			appealVO.setCaseAplContinuanceRsnCd(oldAppealVO
					.getCaseAplContinuanceRsnCd());
			appealVO.setCaseAplRsltsCd(oldAppealVO.getCaseAplRsltsCd());
			appealVO.setCaseAplStatCd(oldAppealVO.getCaseAplStatCd());
			appealVO.setTcnNum(oldAppealVO.getTcnNum());
			appealVO.setCaseAplDstctOfcCd(oldAppealVO.getCaseAplDstctOfcCd());
			appealVO.setPrevAplNum(oldAppealVO.getPrevAplNum().toString());
			appealVO.setAddlInfoAAUOfficerNam(oldAppealVO
					.getAddlInfoAAUOfficerNam());
			appealVO.setAddlInfoMotionTyCd(oldAppealVO.getAddlInfoMotionTyCd());
			appealVO.setAddlInfoFileLocnCd(oldAppealVO.getAddlInfoFileLocnCd());
			appealVO.setAddlInfoClientRepNam(oldAppealVO
					.getAddlInfoClientRepNam());
			appealVO.setDhhsDecisCd(oldAppealVO.getDhhsDecisCd());
			appealVO.setRcnsdrtnDecisCd(oldAppealVO.getRcnsdrtnDecisCd());
			appealVO.setRcnsdrtnRevwrNam(oldAppealVO.getRcnsdrtnRevwrNam());
			appealVO.setVersionNo(oldAppealVO.getVersionNo());
			appealVO.setRevwrNam(oldAppealVO.getRevwrNam());*/
			appealDataBean.setAppealVO(appealVO);

		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to Show the particular component selected
	 * 
	 * @param event
	 *            ValueChangeEvent
	 * @return String
	 */
	public String showComponent(ValueChangeEvent event) {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isDebugEnabled()){
		log.debug("inside showComponent------------>" + event.getNewValue());
	    }
		appealDataBean.setShowClaimCompFlag(false);
		appealDataBean.setShowSACompFlag(false);

		if (event.getNewValue().equals(
				MaintainContactManagementUIConstants.COMP_C)) {
			appealDataBean.setShowClaimCompFlag(true);
		}
		//      ADDED BY ICS GAP-11022
		else if (event.getNewValue().equals(
				MaintainContactManagementUIConstants.COMP_SE))
		//        	END BY ICS GAP-11022
		{
			appealDataBean.setShowSACompFlag(true);
			appealDataBean.setSaLineItemDataFlag(true);
		}
		//      ADDED BY ICS CR-1622
		else if (event.getNewValue().equals(
				MaintainContactManagementUIConstants.COMP_SA)) {
			appealDataBean.setShowSACompFlag(true);
			appealDataBean.setSaLineItemDataFlag(true);
		}
		//    	END BY ICS CR-1622
		if (appealDataBean.getAppealVO().getListSALineItemsVO() != null
				&& !appealDataBean.getAppealVO().getListSALineItemsVO()
						.isEmpty()) {
			appealDataBean.setSaLineItemDataFlag(Boolean.TRUE);
		} else {
			appealDataBean.setSaLineItemDataFlag(Boolean.FALSE);
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to Show the particular component selected
	 * 
	 * @param event
	 *            ValueChangeEvent
	 * @return String
	 */
	public String showAppealContReason(ValueChangeEvent event) {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isDebugEnabled()){
		log.debug("inside showAppealContReason------------>"
				+ event.getNewValue());
	    }
		//      ADDED BY ICS GAP-11022
		if (event.getNewValue().equals("CO"))
		//END BY ICS GAP-11022
		{
			appealDataBean.setShowContReasonFlag(true);
		} else {
			appealDataBean.setShowContReasonFlag(false);
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort Admin Hearing information based on sorted
	 * column and order.
	 * 
	 * @param event
	 *            ActionEvent
	 * @return String
	 */

	public String sortAdminHearing(ActionEvent event) {
		if(log.isDebugEnabled()){
		log.debug("inside sortAdminHearing");
		}
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);

		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);

		appealDataBean.setImageRender(event.getComponent().getId());
		//By default focus should come to first page after sort.
		appealDataBean.setAddAppealsRowIndex(0);
		sortAHComparator(sortColumn, sortOrder, appealDataBean
				.getAdminHearingList());

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * Comparator to the AdminHearing list to sort the list according to the
	 * sort order selected by the user.
	 * 
	 * @param sortColumn -
	 *            Column to be sorted.
	 * @param sortOrder -
	 *            Order by which the column should be started.
	 * @param dataList -
	 *            List to be sorted.
	 */
	private void sortAHComparator(final String sortColumn,
			final String sortOrder, List dataList) {
		if(log.isDebugEnabled()){
		log.debug("inside sortAHComparator");
		}
		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				AdminHearingVO data1 = (AdminHearingVO) obj1;
				AdminHearingVO data2 = (AdminHearingVO) obj2;
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
				//changed for sorting
				if (MaintainContactManagementUIConstants.ADMN_HRNG_DT
						.equals(sortColumn)) {
					Date opendate1=ContactManagementHelper.getOpenBeginDate();
					Date opendate2=ContactManagementHelper.getOpenBeginDate();
					DateFormat dateFormat = new SimpleDateFormat(
                    ContactManagementConstants.CONTCT_MGMT_DATE_FORMAT);
					try{
					if (null == data1.getStrAdminHearingDate()
								|| ContactManagementConstants.EMPTY_STRING
										.equals(data1.getStrAdminHearingDate()
												.trim())) {
						data1
								.setStrAdminHearingDate(MaintainContactManagementUIConstants.NULL);
						/*try {
							data1.setStrAdminHearingDate(data1
									.getStrAdminHearingDate());
						} catch (Exception e) {
							if(log.isDebugEnabled()){
							log.debug("exception is" + e);
							}
						}*/
					}
					else{
						
                    	opendate1=dateFormat.parse(data1.getStrAdminHearingDate());
                    }
					if (null == data2.getStrAdminHearingDate()
								|| ContactManagementConstants.EMPTY_STRING
										.equals(data2.getStrAdminHearingDate()
												.trim())) {
						data2
								.setStrAdminHearingDate(MaintainContactManagementUIConstants.NULL);
						/*try {
							data2.setStrAdminHearingDate(data2
									.getStrAdminHearingDate());
						} catch (Exception e) {
							if(log.isDebugEnabled()){
								log.debug("exception is" + e);
								}
						}*/
					}
					else{						
                    	opendate2=dateFormat.parse(data2.getStrAdminHearingDate());
                    }
					}
					catch (ParseException e) {
						e.printStackTrace();
					}
					return ascending ? opendate1.compareTo(
							opendate2) : opendate2.compareTo(
									opendate1);

				}
				//changed for sorting
				if (MaintainContactManagementUIConstants.HRNG_RSLTS
						.equals(sortColumn)) {
					if (null == data1.getHearingResults()) {
						data1
								.setHearingResults(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getHearingResults()) {
						data2
								.setHearingResults(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getHearingResults().trim()
							.compareTo(data2.getHearingResults().trim())
							: data2.getHearingResults().trim().compareTo(
									data1.getHearingResults().trim());
				}
				if (MaintainContactManagementUIConstants.HRNG_STS
						.equals(sortColumn)) {
					if (null == data1.getHearingStatus()) {
						data1
								.setHearingStatus(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getHearingStatus()) {
						data2
								.setHearingStatus(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getHearingStatus().trim()
							.compareTo(data2.getHearingStatus().trim()) : data2
							.getHearingStatus().trim().compareTo(
									data1.getHearingStatus().trim());
				}
				if (MaintainContactManagementUIConstants.HRNG_OFF_NM
						.equals(sortColumn)) {
					if (null == data1.getHearingOfficerName()) {
						data1
								.setHearingOfficerName(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getHearingOfficerName()) {
						data2
								.setHearingOfficerName(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getHearingOfficerName().trim()
							.compareToIgnoreCase(data2.getHearingOfficerName().trim())
							: data2.getHearingOfficerName().trim().compareToIgnoreCase(
									data1.getHearingOfficerName().trim());
				}
				if (MaintainContactManagementUIConstants.DOCKT_NM
						.equals(sortColumn)) {
					Integer dockNO1 = Integer.valueOf(0); //added for sorting
					Integer dockNO2 = Integer.valueOf(0);
					if (null == data1.getDocketNumber()) {
						data1
								.setDocketNumber(MaintainContactManagementUIConstants.NULL);
					}
					//added else block for sorting
					else {
						 dockNO1 = new Integer(data1.getDocketNumber());
					}
					if (null == data2.getDocketNumber()) {
						data2
								.setDocketNumber(MaintainContactManagementUIConstants.NULL);
					}
					//added else block for sorting
					else {
						 dockNO2 = new Integer(data2.getDocketNumber());
					}
					//commemnted for sorting
					/*return ascending ? data1.getDocketNumber().trim()
							.compareTo(data2.getDocketNumber().trim()) : data2
							.getDocketNumber().trim().compareTo(
									data1.getDocketNumber().trim());*/
					return ascending ? dockNO1.compareTo(dockNO2)
							: dockNO2.compareTo(dockNO1);
				}
				return 0;
			}
		};
		Collections.sort(dataList, comparator);
	}

	/**
	 * Method to save appeals which includes edit and add functionalities
	 * 
	 * @return String
	 */
	public String saveAppeal() {
	    
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    commonEntityDataBean = (CommonEntityDataBean) getDataBean(ContactManagementConstants.COMMON_ENTITY_DATA_BEAN);
		AppealVO appealVO = appealDataBean.getAppealVO();

		AppealDelegate appealDelegate = new AppealDelegate();
		AppealTracking appealTrackingDO = new AppealTracking();
		AppealTracking returnAppealTracking = null;
		CaseDelegate caseDelegate = new CaseDelegate();
		CaseRecord caseRecord = null;

		appealDataBean.setShowFinalSuccessMsgFlag(false);
		appealDataBean.setShowSuccessMsgFlag(false);
		appealDataBean.setEditAdminHearingFlag(false);
		appealDataBean.setAddAdminHearingFlag(false);
		commonEntityDataBean.setDetailMainNotesRender(false);
		appealDataBean.setCursorFocusValue("");

		
		String caseRecNo = appealDataBean.getCaseSK();

		
		boolean appealExists = isAppealExistsForCase();
		
		if (appealDataBean.isAddAdminHearingFlag()
				|| appealDataBean.isEditAdminHearingFlag()) {
			saveAdminHearing();
		}
		
		validateAppealFlg = validateAppeal(appealVO);

		try {
			if (validateAppealFlg) {
				appealVO.setCaseSK(caseRecNo);
				if (appealDataBean.isAddAppealFlag()) {
					try {
						/*appealTrackingDO = appealDOConverter
								.convertAppealVOToDO(appealVO,
										appealDataBean
												.getAdminHearingList());*/
						appealTrackingDO = getAppealTracking(appealVO,
								appealDataBean
										.getAdminHearingList());
						
						try {
							caseRecord = caseDelegate.getCaseDetails(new Long(
									caseRecNo));
							if (caseRecord != null) {
								
								appealTrackingDO.setCaseRef(caseRecord);
							}
						} catch (Exception ex) {
							ContactManagementHelper
									.setErrorMessage(MaintainContactManagementUIConstants.SAVE_FAILURE);
							appealDataBean.setShowFinalSuccessMsgFlag(
									false);
							if(log.isDebugEnabled()){
								log.debug("exception in getCaseDetails");
								}
						
						}
						appealTrackingDO.setCaseSK(new Long(caseRecNo));
						appealTrackingDO
								.setCommonEntityTypeCode(appealDataBean
										.getEntityType());
						
						returnAppealTracking = appealDelegate
								.createAppeal(appealTrackingDO);
						//Added for the defect id ESPRD00336213
						List listSAList = appealVO.getListSALineItemsVO();
						try {

							List list = appealVO.getListSALineItemsVO();

							if (list != null) {
								Iterator it = list.iterator();

								while (it.hasNext()) {
									SALineItemsVO objSALineItemsVO = (SALineItemsVO) it
											.next();

									if (objSALineItemsVO.isInclude()) {
										objSALineItemsVO
												.setServiceAuthID(appealVO
														.getAuthID());
									}
								}
							}
							appealDelegate.updateServiceAuthForAppeal(
									listSAList, appealTrackingDO);

						} catch (AppealUpdateDataException e) {

							if(log.isErrorEnabled()){
								log.error("AppealUpdateDataException has come");
							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							if(log.isErrorEnabled()){
								log.error("RemoteException has come");
							}
						}
						
						ContactManagementHelper
								.setErrorMessage(MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG);
						appealDataBean.setShowFinalSuccessMsgFlag(true);
						appealDataBean.setSmallSaveCount(0);
						appealDataBean.setShowSuccessMsgFlag(false);
						appealDataBean.setAddAppealFlag(false);
						if(log.isDebugEnabled()){
						log.debug(" appealVO.getCaseSK() :> "+ appealVO.getCaseSK());
						}

						if (appealVO.getCaseSK() != null) {
							getAppealDetails(appealVO.getCaseSK());
						}
						/** Page level success message render flag is 
						 *  making false in getAppealDetails hence modified
						 *  to true here.
						 * */
						appealDataBean.setShowFinalSuccessMsgFlag(Boolean.TRUE);
					} catch (AppealCreateBusinessException e) {
						

						ContactManagementHelper
								.setErrorMessage(MaintainContactManagementUIConstants.SAVE_FAILURE);

						appealDataBean.setShowFinalSuccessMsgFlag(false);
					} finally {

					}

				} else if (appealDataBean.isEditAppealFlag()) {
					
					if (!appealDataBean.getDeletedAdminHearingList()
							.isEmpty()) {
						try {
							/*appealTrackingDO = appealDOConverter
									.convertAppealVOToDO(
											appealVO,
											appealDataBean
													.getDeletedAdminHearingList());*/
							appealTrackingDO = getAppealTracking(
									appealVO,
									appealDataBean
											.getDeletedAdminHearingList());
							appealDelegate.deleteAdminHearings(new ArrayList(
									appealTrackingDO.getAdminHearings()),
									appealTrackingDO.getAuditUserID());
						} catch (Exception e) {
							if(log.isErrorEnabled()){
							log.error("Error while deleting Admin Hearing", e.fillInStackTrace());
							}
						}
					}
					/*appealTrackingDO = appealDOConverter
							.convertAppealVOToDO(appealVO, appealDataBean
									.getAdminHearingList());*/
					appealTrackingDO = getAppealTracking(appealVO, appealDataBean
							.getAdminHearingList());

					boolean updated = false;
					try {
						appealTrackingDO.setCaseSK(new Long(caseRecNo));

						if(appealDataBean.getAppealVO().getAplDt()!=null || !appealDataBean.getAppealVO().getAplDt().equals("")){
							appealTrackingDO.setAppealDate(ContactManagementHelper
									.dateConverter(appealDataBean.getAppealVO().getAplDt()));
						}
						//ADDED BY ICS GAP-11022 & CR-1622
						appealTrackingDO
								.setCommonEntityTypeCode(appealDataBean
										.getEntityType());
						//END BY ICS GAP-11022 & CR-1622

						if(log.isErrorEnabled()){
						log.debug("calling Edit Appeal -------->"
								+ appealTrackingDO.getCaseSK());
						}
						updated = (appealDelegate
								.updateAppeal(appealTrackingDO)).booleanValue();

						if (updated) {
							if(log.isErrorEnabled()){
							log.debug("Update Successful In controller");
							}

							//ADDED BY ICS GAP-11022 & CR-1622
							List listSAList = appealVO.getListSALineItemsVO();
							try {

							
								List list = appealVO.getListSALineItemsVO();

								if (list != null) {
									Iterator it = list.iterator();

									while (it.hasNext()) {
										SALineItemsVO objSALineItemsVO = (SALineItemsVO) it
												.next();

										
											objSALineItemsVO
													.setServiceAuthID(appealVO
															.getAuthID());
										
									}
								}
								

								appealDelegate.updateServiceAuthForAppeal(
										listSAList, appealTrackingDO);
							} catch (AppealUpdateDataException e) {
								// TODO Auto-generated catch block
								if(log.isErrorEnabled()){
									log.error("Error while AppealUpdateDataException");
									}
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								if(log.isErrorEnabled()){
									log.error("Error while RemoteException");
									}
							}
							//END BY ICS GAP-11022 & CR-1622

							if (appealVO.getCaseSK() != null) {
								
								String caseSK = appealDataBean.getCaseSK();
								getAppealDetails(caseSK);
							}
							ContactManagementHelper
									.setErrorMessage(MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG);
							appealDataBean
									.setShowFinalSuccessMsgFlag(true);
							appealDataBean.setShowSuccessMsgFlag(false);

							
						} else {
							if(log.isDebugEnabled()){
							log.debug("Update Failed In Controller");
							}
							ContactManagementHelper
									.setErrorMessage(MaintainContactManagementUIConstants.SAVE_FAILURE);
							appealDataBean.setShowFinalSuccessMsgFlag(
									false);
							appealDataBean.setShowSuccessMsgFlag(false);
						}
					} catch (AppealUpdateBusinessException au) {
						if(log.isErrorEnabled()){
						log.error("error in saveAppeal");
						}
						ContactManagementHelper
								.setErrorMessage(MaintainContactManagementUIConstants.SAVE_FAILURE);
						appealDataBean.setShowFinalSuccessMsgFlag(false);

					}
					AppealVO oldAppealVO = new AppealVO();
					//AppealVO appealVO = new AppealVO();

					try {
						BeanUtils.copyProperties(oldAppealVO, appealVO);
					} catch (IllegalAccessException e) {
						if(log.isErrorEnabled()){
						log.error("error in resetEditAdminHearing IllegalAccessException");
						}
					} catch (InvocationTargetException e) {
						if(log.isErrorEnabled()){
							log.error("error in resetEditAdminHearing InvocationTargetException");
							}
					}
					appealDataBean.setTempAppealVO(oldAppealVO);

				}
			}else{
				appealDataBean.setShowFinalSuccessMsgFlag(true);
			}
			appealDataBean.setWarnBeforeExitStatus("saveOrUpdate");
			return MaintainContactManagementUIConstants.SUCCESS;
		} finally {

		}

	}

	/**
	 * This method is to create existing CommonEntityType object
	 * 
	 * @return CommonEntityType
	 */
	/*private CommonEntityType createCommonEntityTypeObject() {
	    appealDataBean = (AppealDataBean) getDataBean(APPEAL_DATA_BEAN);
		CommonEntityType commonEntityType = new CommonEntityType();
		commonEntityType.setCommonEntityTypeCode(appealDataBean
				.getEntityType());

		log.debug("getCommonEntityTypeCode in controllerr--->"
				+ commonEntityType.getCommonEntityTypeCode());
		return commonEntityType;
	}*/

	/**
	 * This method is to validate appeals
	 * 
	 * @param appealVO
	 *            AppealVO
	 * @return boolean
	 */

	private boolean validateAppeal(AppealVO appealVO) {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
		boolean flag = true;
		String actionCode = null;

		if (appealDataBean.isAddAppealFlag()) {
			actionCode = MaintainContactManagementUIConstants.ADD;
		} else {
			actionCode = MaintainContactManagementUIConstants.UPDATE;
		}
		/** Added By Madhurima */
		if (StringUtils.isNotBlank(appealVO.getRevwrNam())
				&& !ContactManagementHelper.validateAlpha(appealVO
						.getRevwrNam())) {
			
			flag = false;
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.INVALID_APL_REVIEW_NAME,
							MaintainContactManagementUIConstants.APL_REVIEW_NAME,
							MaintainContactManagementUIConstants.APL_REVIEW_NAME,
							actionCode);
		}
		if (StringUtils.isNotBlank(appealVO.getAddlInfoClientRepNam())
				&& !ContactManagementHelper.validateAlpha(appealVO
						.getAddlInfoClientRepNam())) {
			flag = false;
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.INVALID_ADDNL_CLIENT_REP,
							MaintainContactManagementUIConstants.ADDNL_CLIENT_REP,
							MaintainContactManagementUIConstants.ADDNL_CLIENT_REP,
							actionCode);
		}
		
		
		if (appealVO.getCaseAplTyCd() == null
				|| appealVO.getCaseAplTyCd().length() == 0) {
			flag = false;
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.CASE_APL_TYP_CD_ID_RQD,
							MaintainContactManagementUIConstants.APL_TYPE_CD_ID,
							MaintainContactManagementUIConstants.APL_TYPE_CD_ID,
							actionCode);
		}
		
		if (StringUtils.isBlank(appealVO.getAplTyCd())) {
			flag = false;
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.CASE_APPEAL_TYP_CD_ID_RQD,
							MaintainContactManagementUIConstants.APPEAL_TYPE_CD_ID,
							MaintainContactManagementUIConstants.APPEAL_TYPE_CD_ID,
							actionCode);
		}
		
		
		
		if (appealVO.getAsgnDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAsgnDt().trim())) {
			Date dateAsgnDt = contactManagementHelper.dateConverter(appealVO
					.getAsgnDt());
			if (dateAsgnDt == null) {
				

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ASGN_DT_ID,
								MaintainContactManagementUIConstants.EDIT_ASGN_DT_ID,
								actionCode);
				flag = false;

			} 
		}
		
		if (appealVO.getAplDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAplDt().trim())) {
			Date dateAplDt = contactManagementHelper.dateConverter(appealVO
					.getAplDt());
			if (dateAplDt == null) {
				
		

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_APL_DT_ID,
								MaintainContactManagementUIConstants.EDIT_APL_DT_ID,
								actionCode);
				flag = false;

			} 
		}
		
		if (appealVO.getCaseAplStatCdDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getCaseAplStatCdDt().trim())) {
			Date dateCaseAplStatCdDt = contactManagementHelper
					.dateConverter(appealVO.getCaseAplStatCdDt());
			if (dateCaseAplStatCdDt == null) {
			
              
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_APL_ST_UPDT_DT,
								MaintainContactManagementUIConstants.EDIT_APL_ST_UPDT_DT,
								actionCode);
				flag = false;

			} 
		}
	
		if (appealVO.getInfrmlRevwReqDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getInfrmlRevwReqDt().trim())) {
			Date dateInfrmlRevwReqDt = contactManagementHelper
					.dateConverter(appealVO.getInfrmlRevwReqDt());
			if (dateInfrmlRevwReqDt == null) {
				
			
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_INF_REV_REQ_DT,
								MaintainContactManagementUIConstants.EDIT_INF_REV_REQ_DT,
								actionCode);
				flag = false;

			} 
		}

		if (appealVO.getInfrmlRevwAckSentDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getInfrmlRevwAckSentDt().trim())) {
			Date dateInfrmlRevwAckSentDt = contactManagementHelper
					.dateConverter(appealVO.getInfrmlRevwAckSentDt());
			if (dateInfrmlRevwAckSentDt == null) {
	

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_INF_REV_ACK_SENT_DT,
								MaintainContactManagementUIConstants.EDIT_INF_REV_ACK_SENT_DT,
								actionCode);
				flag = false;

			} 
		}

		if (appealVO.getInfrmlRevwSentDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getInfrmlRevwSentDt().trim())) {
			Date dateInfrmlRevwSentDt = contactManagementHelper
					.dateConverter(appealVO.getInfrmlRevwSentDt());
			if (dateInfrmlRevwSentDt == null) {


				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_INF_REV_SENT_REV_DT,
								MaintainContactManagementUIConstants.EDIT_INF_REV_SENT_REV_DT,
								actionCode);
				flag = false;

			}
		}

		if (appealVO.getInfrmlRevwDueDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getInfrmlRevwDueDt().trim())) {
			Date dateInfrmlRevwDueDt = contactManagementHelper
					.dateConverter(appealVO.getInfrmlRevwDueDt());
			if (dateInfrmlRevwDueDt == null) {
	

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_INF_REV_DUE_DT,
								MaintainContactManagementUIConstants.ADD_INF_REV_DUE_DT,
								actionCode);
				flag = false;

			} 
		}
	
		if (appealVO.getAddlInfoReqDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAddlInfoReqDt().trim())) {
			Date dateAddlInfoReqDt = contactManagementHelper
					.dateConverter(appealVO.getAddlInfoReqDt());
			if (dateAddlInfoReqDt == null) {
			

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_REQ_DT,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_REQ_DT,
								actionCode);
				flag = false;

			} 
		}

		if (appealVO.getAddlInfoDueDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAddlInfoDueDt().trim())) {
			Date dateAddlInfoDueDt = contactManagementHelper
					.dateConverter(appealVO.getAddlInfoDueDt());
			if (dateAddlInfoDueDt == null) {
	
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_DUE_DT,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_DUE_DT,
								actionCode);
				flag = false;

			} 
		}

		if (appealVO.getAddlInfoRecdDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAddlInfoRecdDt().trim())) {
			Date dateAddlInfoRecdDt = contactManagementHelper
					.dateConverter(appealVO.getAddlInfoRecdDt());
			if (dateAddlInfoRecdDt == null) {
	

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_RECD_DT,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_RECD_DT,
								actionCode);
				flag = false;

			} 
		}

		if (appealVO.getAddlInfoRvsdRevwDueDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAddlInfoRvsdRevwDueDt().trim())) {
			Date dateAddlInfoRvsdRevwDueDt = contactManagementHelper
					.dateConverter(appealVO.getAddlInfoRvsdRevwDueDt());
			if (dateAddlInfoRvsdRevwDueDt == null) {

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_REV_DUE_DT,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_REV_DUE_DT,
								actionCode);
				flag = false;

			} 
		}
	
		if (appealVO.getAddlInfoNotfyLtrSentDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAddlInfoNotfyLtrSentDt().trim())) {
			Date dateAddlInfoNotfyLtrSentDt = contactManagementHelper
					.dateConverter(appealVO.getAddlInfoNotfyLtrSentDt());
			if (dateAddlInfoNotfyLtrSentDt == null) {
	

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_NOTIFY_LTR_SNT_DT,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_NOTIFY_LTR_SNT_DT,
								actionCode);
				flag = false;

			} 
		}

		if (appealVO.getAddlInfo2NdRvsdDueDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAddlInfo2NdRvsdDueDt().trim())) {
			Date dateAddlInfo2NdRvsdDueDt = contactManagementHelper
					.dateConverter(appealVO.getAddlInfo2NdRvsdDueDt());
			if (dateAddlInfo2NdRvsdDueDt == null) {


				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_SCND_REV_DUE_DT,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_SCND_REV_DUE_DT,
								actionCode);
				flag = false;

			} 
		}
	
		if (appealVO.getAddlInfoRecd2NdReqDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAddlInfoRecd2NdReqDt().trim())) {
			Date dateAddlInfoRecd2NdReqDt = contactManagementHelper
					.dateConverter(appealVO.getAddlInfoRecd2NdReqDt());
			if (dateAddlInfoRecd2NdReqDt == null) {


				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_RCD_SCND_REQ_DT,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_RCD_SCND_REQ_DT,
								actionCode);
				flag = false;

			} 
		}
	
		if (appealVO.getAddlInfoRespReqDueDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAddlInfoRespReqDueDt().trim())) {
			Date dateAddlInfoRespReqDueDt = contactManagementHelper
					.dateConverter(appealVO.getAddlInfoRespReqDueDt());
			if (dateAddlInfoRespReqDueDt == null) {


				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_RSP_REQ_DT,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_RSP_REQ_DT,
								actionCode);
				flag = false;

			} 
		}

		if (appealVO.getAddlInfoFiledDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getAddlInfoFiledDt().trim())) {
			Date dateAddlInfoFiledDt = contactManagementHelper
					.dateConverter(appealVO.getAddlInfoFiledDt());
			if (dateAddlInfoFiledDt == null) {
	

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_DT_MOT_DT,
								MaintainContactManagementUIConstants.ADD_ADL_INFO_DT_MOT_DT,
								actionCode);
				flag = false;

			} 
		}

	

		/** Added By shravan */
		if (StringUtils.isNotBlank(appealVO.getAddlInfoAAUOfficerNam())
				&& !ContactManagementHelper.validateAlpha(appealVO
						.getAddlInfoAAUOfficerNam())) {
	
			flag = false;
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.INVALID_ADDITIONAL_INFO_AAU_OFFICER_NAME,
							MaintainContactManagementUIConstants.ADDINFO_AAU_OFFICER_ID,
							MaintainContactManagementUIConstants.ADDINFO_AAU_OFFICER_ID,
							actionCode);
		}

	
		if (appealVO.getDhhsSentDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getDhhsSentDt().trim())) {
			Date dateDhhsSentDt = contactManagementHelper
					.dateConverter(appealVO.getDhhsSentDt());
			if (dateDhhsSentDt == null) {
		

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_DHHS_SNT_TO_DHHS_DT,
								MaintainContactManagementUIConstants.ADD_DHHS_SNT_TO_DHHS_DT,
								actionCode);
				flag = false;

			} 
		}

		if (appealVO.getDhhsDecisDueDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getDhhsDecisDueDt().trim())) {
			Date dateDhhsDecisDueDt = contactManagementHelper
					.dateConverter(appealVO.getDhhsDecisDueDt());
			if (dateDhhsDecisDueDt == null) {
	

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_DHHS_DEC_DUE,
								MaintainContactManagementUIConstants.ADD_DHHS_DEC_DUE,
								actionCode);
				flag = false;

			} 
		}

		if (appealVO.getDhhsNotfyLtrSentDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getDhhsNotfyLtrSentDt().trim())) {
			Date dateDhhsNotfyLtrSentDt = contactManagementHelper
					.dateConverter(appealVO.getDhhsNotfyLtrSentDt());
			if (dateDhhsNotfyLtrSentDt == null) {
	

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_DHHS_NOTY_SNT_DT,
								MaintainContactManagementUIConstants.ADD_DHHS_NOTY_SNT_DT,
								actionCode);
				flag = false;

			} 
		}
	
		if (appealVO.getRcnsdrtnSentDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getRcnsdrtnSentDt().trim())) {
			Date dateRcnsdrtnSentDt = contactManagementHelper
					.dateConverter(appealVO.getRcnsdrtnSentDt());
			if (dateRcnsdrtnSentDt == null) {
		
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_RECONS_SNT_DUE,
								MaintainContactManagementUIConstants.ADD_RECONS_SNT_DUE,
								actionCode);
				flag = false;

			} 
		}
	
		if (appealVO.getRcnsdrtnRtrnDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getRcnsdrtnRtrnDt().trim())) {
			Date dateRcnsdrtnRtrnDt = contactManagementHelper
					.dateConverter(appealVO.getRcnsdrtnRtrnDt());
			if (dateRcnsdrtnRtrnDt == null) {


				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_RECONS_RTN_DT,
								MaintainContactManagementUIConstants.ADD_RECONS_RTN_DT,
								actionCode);
				flag = false;

			}
		}
	
		if (appealVO.getRcnsdrtnDecisDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getRcnsdrtnDecisDt().trim())) {
			Date dateRcnsdrtnDecisDt = contactManagementHelper
					.dateConverter(appealVO.getRcnsdrtnDecisDt());
			if (dateRcnsdrtnDecisDt == null) {


				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_RECONS_DEC_DT,
								MaintainContactManagementUIConstants.ADD_RECONS_DEC_DT,
								actionCode);
				flag = false;

			}
		}

		if (appealVO.getRcnsdrtnNotfyLtrSentDt() != null
				&& !MaintainContactManagementUIConstants.NULL.equals(appealVO
						.getRcnsdrtnNotfyLtrSentDt().trim())) {
			Date dateRcnsdrtnNotfyLtrSentDt = contactManagementHelper
					.dateConverter(appealVO.getRcnsdrtnNotfyLtrSentDt());
			if (dateRcnsdrtnNotfyLtrSentDt == null) {

				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_DATE_FORMATE_FOR_APPEAL,
								MaintainContactManagementUIConstants.ADD_RCONS_NOTY_SNT_DT,
								MaintainContactManagementUIConstants.ADD_RCONS_NOTY_SNT_DT,
								actionCode);
				flag = false;

			} 
		}

		if (StringUtils.isNotBlank(appealVO.getRcnsdrtnRevwrNam())
				&& !ContactManagementHelper.validateAlpha(appealVO
						.getRcnsdrtnRevwrNam())) {
	
			flag = false;
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.INVALID_APL_RECONSIDERATION_REVIEW_NAME,
							MaintainContactManagementUIConstants.APL_RECONSIDER_REVIEW_NAME,
							MaintainContactManagementUIConstants.APL_RECONSIDER_REVIEW_NAME,
							actionCode);
		}
		

		// Added for the defect id ESPRD00336213
		if(appealVO.getCaseAplTyCd() != null){
		if (appealVO.getCaseAplTyCd().equalsIgnoreCase("SA")
				|| appealVO.getCaseAplTyCd().equalsIgnoreCase("SE")) {
			List list = appealVO.getListSALineItemsVO();
			boolean check = true;
			boolean checkResults = false;
			boolean checkStatus = false;
			if (list != null) {
				Iterator it = list.iterator();

				while (it.hasNext()) {
					SALineItemsVO objSALineItemsVO = (SALineItemsVO) it.next();

					if (objSALineItemsVO.isInclude()) {
						check = false;
					}

					if (objSALineItemsVO.getSaLineAppStatusValue() != null
							&& objSALineItemsVO.getSaLineAppResultValue() != null
							&& objSALineItemsVO.getSaLineAppStatusValue()
									.equalsIgnoreCase("A")
							&& (!objSALineItemsVO.getSaLineAppResultValue()
									.equalsIgnoreCase("01") && !objSALineItemsVO
									.getSaLineAppResultValue()
									.equalsIgnoreCase("02"))) {
						checkResults = true;
					}

					if (objSALineItemsVO.getSaLineAppStatusValue() != null
							&& objSALineItemsVO.getSaLineAppStatusValue()
									.equalsIgnoreCase("A")) {
						checkStatus = true;
					}
				}
			}
			/**
			 * Modified for defect ESPRD00872396 SA field validation for Page level save
			 * **/
			String authID = appealDataBean.getAppealVO().getAuthID();
			
			if (StringUtils.isBlank(authID)) {
				check = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_SA_NUMBER_BLANK,
								MaintainContactManagementUIConstants.APL_SA_NUMBERE,
								MaintainContactManagementUIConstants.APL_SA_NUMBERE,
								actionCode);
				flag = false;
			}

			if (check) {
		
				check = false;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.SA_LINE_ITEMS_REQ,
						MaintainContactManagementUIConstants.SA_LINE_ITEMS,
						MaintainContactManagementUIConstants.SA_LINE_ITEMS,
						actionCode);
				flag = false;
			}

			if (checkResults) {
				check = false;
				checkResults = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.APPEAL_RESULTS_CHECK,
								MaintainContactManagementUIConstants.APPEAL_RESULT,
								MaintainContactManagementUIConstants.APPEAL_RESULT,
								actionCode);
				flag = false;
			}

			if (checkStatus && appealVO.getCaseAplStatCd() != null
					&& StringUtils.isNotBlank(appealVO.getCaseAplStatCd())
					&& !appealVO.getCaseAplStatCd().equalsIgnoreCase("A")) {
				check = false;
				checkStatus = false;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.APPEAL_STATUS_CHECK,
								MaintainContactManagementUIConstants.APPEAL_STATUS,
								MaintainContactManagementUIConstants.APPEAL_STATUS,
								actionCode);
				flag = false;
			}

		}
	}
		// Ends
		
		
		return flag;
	}

	/**
	 * This operation is used to get Appeal Record details based on the primary
	 * key value appealCaseRecordNum.
	 * 
	 * @param caseRecNum
	 *            String.
	 * @return String
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String getAppealDetails(String caseRecNum) {
		if(log.isInfoEnabled()){
			log.info("++Inside GetAppealDetails");
		}
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		AppealDelegate appealDelegate = new AppealDelegate();
		
		AppealTracking appealDO = null;
		AppealDOConverter appealDOConverter = new AppealDOConverter();
		AppealVO appealVO = null;


		try {
			//for ESPRD00782216 defect.
			appealDO = appealDelegate.getAppealDetails(new Long(caseRecNum));
			//NoteSet noteSet = null;
			clearDataFields();
			//ContactHelper contactHelper = new ContactHelper();
			CommonEntityDataBean commonEntityDataBean = ContactHelper
					.getCommonEntityDataBean();
			
			commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
					.getNotesList().clear();
			commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
					.setNoteText(ContactManagementConstants.EMPTY_STRING);
			commonEntityDataBean.getNoteList().clear();
			//clearing data
			//commonEntityDataBean.setCommonEntityVO(new CommonEntityVO());
			if (appealDO != null) {
				//for clearing previous data.
				
				
				/* Present notes count is not using.
				 * for ESPRD00782216 defect.
				 * Set administrativeHearingSet = appealDO.getAdminHearings();
				AdministrativeHearing administrativeHearing = null;

				Iterator itr = administrativeHearingSet.iterator();
				while (itr.hasNext()) {
					administrativeHearing = (AdministrativeHearing) itr.next();
					noteSet = administrativeHearing.getNoteSet();

					if (noteSet != null) {
						if (commonEntityDataBean != null) {
							NoteSetVO noteSetVO = contactHelper
									.convertNoteSetDomainToVO(noteSet);
							if (noteSetVO != null) {
								
								commonEntityDataBean.getCommonEntityVO()
										.setNoteSetVO(noteSetVO);
								if (noteSetVO.getNotesList() != null) {
									appealDataBean.setNotesCount(
											noteSetVO.getNotesList().size());

								}
							}
						}
					}
				}*/
				
				appealVO = appealDOConverter.convertAppealDOToVO(appealDO);
				//Added for CR ESPRD00373565 AuditLog starts
//				appealDOConverter.createVOAuditKeysList(administrativeHearing,appealVO);
				doAuditKeyListOperation(appealVO);
				//Added for CR ESPRD00373565 AuditLog ends
				appealDataBean.setAppealVO(appealVO);
				if(log.isDebugEnabled()){
                log.debug("appealVO.getCaseSK()"
						+ appealVO.getCaseSK());
				}
				if (appealVO.getAuthID() != null) {
					isInValidSARecord(appealVO.getAuthID(), appealVO
							.getCaseSK());
				}

				AppealVO tempAppealVO = new AppealVO();
				try {
					BeanUtils.copyProperties(tempAppealVO, appealVO);
					appealDataBean.setTempAppealVO(tempAppealVO);
					appealDataBean.setAdminHearingList(
							tempAppealVO.getAdminHearingList());
					
					if (appealVO.getCaseAplTyCd() != null
							&& appealVO.getCaseAplTyCd().equalsIgnoreCase("C")) {
						appealDataBean.setShowClaimCompFlag(true);
					} else {
						appealDataBean.setShowClaimCompFlag(false);
					}

					if (appealVO.getCaseAplTyCd() != null
							&& (appealVO.getCaseAplTyCd()
									.equalsIgnoreCase("SA") || appealVO
									.getCaseAplTyCd().equalsIgnoreCase("SE"))) {
						appealDataBean.setShowSACompFlag(true);
//						appealDataBean.setSaLineItemDataFlag(true);
						if (appealDataBean.getAppealVO().getListSALineItemsVO() != null
								&& !appealDataBean.getAppealVO().getListSALineItemsVO()
										.isEmpty()) {
							appealDataBean.setSaLineItemDataFlag(Boolean.TRUE);
						} else {
							appealDataBean.setSaLineItemDataFlag(Boolean.FALSE);
						}
					} else {
						appealDataBean.setShowSACompFlag(false);
						appealDataBean.setSaLineItemDataFlag(false);
					}

					if (appealVO.getAplTyCd() != null
							&& appealVO.getAplTyCd().equalsIgnoreCase("CO")) {
						appealDataBean.setShowContReasonFlag(true);
					} else {
						appealDataBean.setShowContReasonFlag(false);
					}
					//---Modified by Infinite start
					if (appealDO.getCommonEntityType() != null) {
						
					}
					//--end

					if(appealDataBean != null && appealDataBean.getEntityType() != null){
						if(log.isDebugEnabled()){
						log.debug("Entity Type is..."+appealDataBean.getEntityType());
						}
					}
					else{ 
						     appealDataBean.setEntityType(appealVO.getCommonEntityTypeCode());
										    
					      }
					if (appealDataBean.getEntityType().equals(
							MaintainContactManagementUIConstants.ENT_TYPE_M)) {
						// flag to be set on conditional basis for P/M
						appealDataBean.setShowMemberAppealsFlag(true);
						appealDataBean.setShowProviderAppealsFlag(false);
					} else if (appealDataBean.getEntityType().equals(
							MaintainContactManagementUIConstants.ENT_TYPE_P)) {
						appealDataBean.setShowProviderAppealsFlag(true);
						appealDataBean.setShowMemberAppealsFlag(false);

					}
					if (appealDataBean.getAdminHearingList().size() > 0) {
						appealDataBean.setAdminHearingDataFlag(true);
					}else{
						appealDataBean.setAdminHearingDataFlag(Boolean.FALSE);
					}
				} catch (IllegalAccessException e) {
					if(log.isErrorEnabled()){
					log.error("error in getAppealDetails IllegalAccessException");
					}
				} catch (Exception e) {
					if(log.isErrorEnabled()){
					log.error("error in getAppealDetails Exception");
					}
				}

			}
		} catch (AppealFetchBusinessException af) {
			if(log.isErrorEnabled()){
			log.error("error in getAppealDetails");
			}
		}

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method shows the appeal details for the record selected
	 * 
	 * @return String
	 */
	public String getMemAppealsForCaseRecord() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		FacesContext fc = FacesContext.getCurrentInstance();
		String indx = fc.getExternalContext().getRequestParameterMap().get(
				"rowIndex").toString();
		int intMemAppealRowIndex = new Integer(indx).intValue();
	
		String index = Integer.toString(intMemAppealRowIndex);
		appealDataBean.setMemAppealIndex(index);
		if (index != null) {
			AppealVO appealVO = (AppealVO) appealDataBean
					.getMemberAppealList().get(intMemAppealRowIndex);
			
			if (appealVO != null) {
				appealDataBean.setCaseSK(appealVO.getCaseSK());
				getAppealDetails(appealVO.getCaseSK());
				

			}
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method shows the appeal details for the record selected
	 * 
	 * @return String
	 */
	public String getProvAppealsForCaseRecord() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	
		FacesContext fc = FacesContext.getCurrentInstance();

		String indx = fc.getExternalContext().getRequestParameterMap().get(
				"rowIndex").toString();

		int intProvAppealRowIndex = new Integer(indx).intValue();

	
		String index = Integer.toString(intProvAppealRowIndex);
		appealDataBean.setPrvAppealIndex(index);
		if (index != null) {

			if (appealDataBean.getProviderAppealList() != null) {
				AppealVO appealVO = (AppealVO) appealDataBean
						.getProviderAppealList().get(intProvAppealRowIndex);
				

				if (appealVO != null) {
					
					appealDataBean.setCaseSK(appealVO.getCaseSK());
					getAppealDetails(appealVO.getCaseSK());
					//Added By ICS FOR CR-1622
					if (appealDataBean
							.getAppealVO()
							.getCaseAplTyCd()
							.equals(
									MaintainContactManagementUIConstants.COMP_SA)) {

						appealDataBean.setShowSACompFlag(true);
					}
					//Ended By ICS
				}
			}
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * Method gets appeals any entity provided entityID and entityType
	 * 
	 * @param altID
	 *            String
	 * @param typeCode
	 *            String
	 * @return String
	 */
	public String getAllAppeals(String altID, String typeCode) {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		AppealDelegate appealDelegate = new AppealDelegate();
		if(log.isDebugEnabled()){
		log.debug("Inside getAllAppeals");
		}
		List memberAppealList = null;
		List memberAppealConvertList = null;
		List providerAppealList = null;
		List providerAppealConvertList = null;
		Set appealSet = new HashSet();
		//String entityTypeCode = null;

		if (typeCode != null && altID != null) {
			/**
			 * for defect ESPRD00872748 
			 * if no appeal records found for the
			 * provider or member selected then old data should not render hence
			 * by defaults making render flags false as if data is present then
			 * accordingly render will happen moving forward.
			 */
			//CommonEntityType commonEntityType = new CommonEntityType();
			//commonEntityType.setCommonEntityTypeCode(typeCode);
			//entityTypeCode = commonEntityType.getCommonEntityTypeCode();

			try {
				if (typeCode
						.equalsIgnoreCase(MaintainContactManagementUIConstants.ENT_TYPE_M)) {

					memberAppealList = appealDelegate.getAllAppeals(altID,
							typeCode);
					
					if (!memberAppealList.isEmpty()) {
						AppealDOConverter appealDOConverter = new AppealDOConverter();
						appealSet.addAll(memberAppealList);
						memberAppealConvertList = appealDOConverter
								.convertAppealDOToVO(appealSet);
						appealDataBean.setMemberAppealList(
								memberAppealConvertList);
						appealDataBean.setEntityType(typeCode);
						appealDataBean.setMemberAppealDataFlag(true);
						appealDataBean.setProviderAppealDataFlag(false);

					}else{
						appealDataBean.setMemberAppealList(new ArrayList());
						appealDataBean.setMemberAppealDataFlag(Boolean.FALSE);
					}
				} else if (typeCode
						.equalsIgnoreCase(MaintainContactManagementUIConstants.ENT_TYPE_P)) {

					providerAppealList = appealDelegate.getAllAppeals(altID,
							typeCode);
					
					if (!providerAppealList.isEmpty()) {
						AppealDOConverter appealDOConverter = new AppealDOConverter();
						appealSet.addAll(providerAppealList);
						providerAppealConvertList = appealDOConverter
								.convertAppealDOToVO(appealSet);

						appealDataBean.setProviderAppealList(
								providerAppealConvertList);
						appealDataBean.setEntityType(typeCode);
						appealDataBean.setProviderAppealDataFlag(true);
						appealDataBean.setMemberAppealDataFlag(false);
					}else{
						appealDataBean.setProviderAppealList(new ArrayList());
						appealDataBean.setProviderAppealDataFlag(Boolean.FALSE);
					}
				}
			} catch (AppealFetchBusinessException af) {
				if(log.isErrorEnabled()){
				log.error("error in getAllAppeals");
				}

			}

		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * @return
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 * @return boolean
	 */
	public boolean isAppealExistsForCase() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		AppealDelegate appealDelegate = new AppealDelegate();
		boolean appealExists = false;
		try {
			
			String caseRecNum = appealDataBean.getCaseSK();
			if (StringUtils.isNotBlank(caseRecNum)) {
				appealExists = (appealDelegate.isAppealExistsForCase(new Long(
						caseRecNum))).booleanValue();
				if (appealExists) {
					appealDataBean.setEditAppealFlag(true);
				} else {
					appealDataBean.setAddAppealFlag(true);
				}
			}
		} catch (AppealFetchBusinessException a) {
			if(log.isErrorEnabled()){
			log.error("error in isAppealExistsForCase");
			}
		}
		return appealExists;
	}

	

	/**
	 * showing audit child history for Appeals.
	 * 
	 * @return String
	 */
	public String showAppealsAuditHistory() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		GlobalAuditsDelegate audit;
		List list = new ArrayList();
		ArrayList audlist = new ArrayList();
		AppealTracking appealTracking = null;
		try {
			if(log.isDebugEnabled()){
			log.debug("in show child audit history");
			}
			audit = new GlobalAuditsDelegate();

			/*appealTracking = appealDOConverter.convertAppealVOToDO(
					appealDataBean.getAppealVO(), appealDataBean
							.getAdminHearingList());*/
			appealTracking = getAppealTracking(
					appealDataBean.getAppealVO(), appealDataBean
							.getAdminHearingList());
			if(log.isDebugEnabled()){
			log.debug("APPEAL SK ID::::" + appealTracking.getCaseSK());
			}

			list.add(appealTracking);

			HashMap hm = audit.getAuditLogInfo(list);

			

			audlist = (ArrayList) hm.get(appealTracking);

			appealDataBean.setAppealAuditHistoryList(audlist);
			appealDataBean.setAppealAuditHistoryRender(true);

			
			appealDataBean.setAppealAuditOpen(true);
		} catch (GlobalAuditsBusinessException e) {
			if(log.isErrorEnabled()){
			log.error("error in showAppealsAuditHistory");
			}
		}

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * showing audit child history for Appeals Admin Hearing.
	 * 
	 * @return String
	 */
	public String showAdminHearingAuditHistory() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		GlobalAuditsDelegate audit;
		List list = new ArrayList();
		ArrayList audlist = new ArrayList();
		AppealTracking appealTracking = new AppealTracking();
		try {
			if(log.isDebugEnabled()){
			log.debug("in show child audit history");
			}
			audit = new GlobalAuditsDelegate();
			/* Gets Child Domain Object */

			/*appealTracking = appealDOConverter.convertAppealVOToDO(
					appealDataBean.getAppealVO(), appealDataBean
							.getAdminHearingList());*/
			appealTracking =getAppealTracking(
					appealDataBean.getAppealVO(), appealDataBean
							.getAdminHearingList());
			if(log.isDebugEnabled()){
			log.debug("APPEAL SK ID::::" + appealTracking.getCaseSK());
			}

			list.add(appealTracking);

			HashMap hm = audit.getAuditLogInfo(list);

			

			audlist = (ArrayList) hm.get(appealTracking);

			appealDataBean.setAdminHearAuditHistoryList(audlist);
			appealDataBean.setAdminHearAuditHistoryRender(true);

			/* Added new for Expand */
			appealDataBean.setAdminHearAuditOpen(true);
		} catch (GlobalAuditsBusinessException e) {
			if(log.isDebugEnabled()){
			log.debug("Error in show child audit history  " + e);
			}
		}

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method gets the member details from CommonMemberDetailsVO/Member
	 * search portlet
	 * 
	 * @return String
	 */
	public String getMemberAppealsSearch() {
		if(log.isDebugEnabled()){
		log.debug("Inside getMemberAppealsSearch()");
		}
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		String resultString = "";
		String lobCode = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		CommonEntityType commonEntityType = new CommonEntityType();
		
		if (fc.getExternalContext().getRequestMap() != null) {
			Object object = fc.getExternalContext().getRequestMap().get(
					MaintainContactManagementUIConstants.MEM_APPLS_SRCH);
			
			Object object1 = fc.getExternalContext().getRequestMap().get(
					"MSQResetSearch");
			
			if (object != null) {
				// Added for the Defect Id : ESPRD00728875
				appealDataBean.setCancelFlag(true);
				/* for defect ESPRD00872748, 
				 * fresh data should come up with closing the details of appeal record*/
				appealDataBean.setShowMemberAppealsFlag(false);
				CommonMemberDetailsVO memberDetailsVO = new CommonMemberDetailsVO();
				try {
					getLinksToDisable();
					BeanUtils.copyProperties(memberDetailsVO, object);
					fc
							.getExternalContext()
							.getRequestMap()
							.remove(
									MaintainContactManagementUIConstants.MEM_APPLS_SRCH);

				} catch (Exception e) {
					if(log.isErrorEnabled()){
					log.error(e.getMessage(), e);
					}
				}

				

				if (memberDetailsVO != null) {
					appealDataBean.setEnableAppealDetailAudit(false);//ESPRD00509203_ProviderAppeals_03AUG2010
					String altID = memberDetailsVO.getAltID();
					lobCode = memberDetailsVO.getLobCode();
					appealDataBean.setCommonMemberDetailsVO(
							memberDetailsVO);
					// code changes for the defect id ESPRD00343176
					
					appealDataBean.setMemberID(
							memberDetailsVO.getMemberSysID());
					// Ends
					appealDataBean.setMemberName(
							memberDetailsVO.getMemberName());
					appealDataBean.setEntityType(
							memberDetailsVO.getEntityType());
					appealDataBean.setEntityName(
							memberDetailsVO.getMemberName());
					
					appealDataBean.setEntityID(memberDetailsVO.getAltID());
					appealDataBean.setCancelFlag(true);

					if (memberDetailsVO.getEntityType() != null) {
						commonEntityType
								.setCommonEntityTypeCode(memberDetailsVO
										.getEntityType());
					}
					resultString = getAllAppeals(altID, memberDetailsVO
							.getEntityType());
					
				}
				
			} else if(object1 != null)
			{
				appealDataBean.setCancelFlag(false);
			}
			
			/* Added for defect ESPRD00412572 implementation  */
			else if(fc.getExternalContext().getRequestMap().get("receServauthAppid") != null) {
		      try {
		    	  //for defect ESPRD00805886
		    	  //IPC issue fix from Service Authorization to Member.
		    	  String aid =(String)fc.getExternalContext().getRequestMap().get("receServauthAppid");
		    	  if (log.isDebugEnabled()) {
						log.debug("aid--->"+aid);
					}
                 // String Aid =(String.valueOf(AppealNo));
                  getAppealDetails(aid);
                  
            } catch (RuntimeException e) {
                // TODO Auto-generated catch block
            	if(log.isErrorEnabled()){
            	log.error("Error due to Runtime Exception");
            	}
            }

			} else {
				if(log.isDebugEnabled()){
				log.debug("Not an instance");
				}
			}
		}

		return resultString;
	}

	/**
	 * @param memberAppealsSearch
	 *            The memberAppealsSearch to set.
	 */
	public void setMemberAppealsSearch(String memberAppealsSearch) {
		this.memberAppealsSearch = memberAppealsSearch;
	}

	/**
	 * This method submits the memberSysID to memberdetails portlet
	 * 
	 * @return String
	 */

	public String submitMemberSummary() {
		if(log.isDebugEnabled()){
		log.debug("calling submitMemberSummary--->");
		}
		String memberSysID = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding(
						MaintainContactManagementUIConstants.REQ_SCOPE)
				.getValue(facesContext);
		if (map != null) {
			
			memberSysID = map.get(MaintainContactManagementUIConstants.SYS_ID)
					.toString();
			if(log.isDebugEnabled()){
			log.debug("memberSysID--------->" + memberSysID);
			}
		}
		requestScope
				.remove(MaintainContactManagementUIConstants.CM_MEM_SUMMARY);
		requestScope.put(MaintainContactManagementUIConstants.CM_MEM_SUMMARY,
				memberSysID);

		return "";
	}

	/**
	 * This method submits the case details to logcase portlet
	 * 
	 * @return String
	 */

	public String submitCaseDetails() {
		if(log.isDebugEnabled()){
		log.debug("calling submitCaseDetails--->");
		}
		String memberSysID = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding(
						MaintainContactManagementUIConstants.REQ_SCOPE)
				.getValue(facesContext);
		if (map != null) {
		
			memberSysID = map.get(MaintainContactManagementUIConstants.SYS_ID)
					.toString();
			if(log.isDebugEnabled()){
			log.debug("memberSysID--------->" + memberSysID);
			}
		}
		requestScope
				.remove(MaintainContactManagementUIConstants.CM_MEM_SUMMARY);
		requestScope.put(MaintainContactManagementUIConstants.CM_MEM_SUMMARY,
				memberSysID);

		return "";
	}

	

	/**
	 * @return Returns the addAppealsCritria.
	 */
	public String getAddAppealsCritria() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    commonEntityDataBean = (CommonEntityDataBean) getDataBean(ContactManagementConstants.COMMON_ENTITY_DATA_BEAN); // Defect ESPRD00729574 modified
	    if(log.isDebugEnabled()){
		log.debug("Inside getAddAppealsCritria");
	    }
		String resultString = " ";
		FacesContext fc = FacesContext.getCurrentInstance();
		CommonEntityType commonEntityType = new CommonEntityType();

		
		if (fc.getExternalContext().getRequestMap() != null) {
			Object object = fc.getExternalContext().getRequestMap().get(
					MaintainContactManagementUIConstants.ADD_APL_DET);
		
			if (object instanceof CommonCMCaseDetailsVO) {
				
				CommonCMCaseDetailsVO commonCMCaseDetailsVO = (CommonCMCaseDetailsVO) object;
				appealDataBean.setShowMemberInfoLabelFlag(false);
				appealDataBean.setShowProviderInfoLabelFlag(false);
				// Defect ESPRD00729574 modified Starts
				appealDataBean.setEditAdminHearingFlag(false);
				appealDataBean.setAddAdminHearingFlag(false);
				commonEntityDataBean.setDetailMainNotesRender(false);
				// Defect ESPRD00729574 modified ends
				appealDataBean.setAppealVO(new AppealVO());
				if(log.isDebugEnabled()){
				log
				.debug("In getAddAppealsCritria()--commonCMCaseDetailsVO is an instance.");
				}
				if (commonCMCaseDetailsVO != null) {
					getLinksToDisable();
					appealDataBean.setEnableAppealDetailAudit(false);//ESPRD00509203_ProviderAppeals_03AUG2010
					appealDataBean.setCommonCMCaseDetailsVO(
							commonCMCaseDetailsVO);
					appealDataBean.setEntityType(
							commonCMCaseDetailsVO.getEntityType());
					appealDataBean.setEntityID(
							commonCMCaseDetailsVO.getEntityID());
				
					appealDataBean.setCaseSK(
							commonCMCaseDetailsVO.getCaseSK());
					appealDataBean.getAppealVO().setCaseSK(commonCMCaseDetailsVO.getCaseSK());
//					NFR_ESPRD00432533_UC-PGM-CRM-067_07MAY2010
					PortletRequest portletRequest = null;
					if(FacesContext.getCurrentInstance()!=null && FacesContext.getCurrentInstance().getExternalContext()!=null
							&& FacesContext.getCurrentInstance().getExternalContext().getRequest()!=null){
						try{
							portletRequest = (PortletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
						}catch(Exception e){
							if(log.isDebugEnabled()){
							log.debug("Exception while request type cast to PortletRequest: "+e.getMessage());
							}
						}
						
					}
					
					//EOF NFR_ESPRD00432533_UC-PGM-CRM-067_07MAY2010

					if (appealDataBean
							.getCommonCMCaseDetailsVO()
							.getEntityType()
							.equals(
									MaintainContactManagementUIConstants.ENT_TYPE_M)) {
						
						if(portletRequest!=null){//NFR_ESPRD00432533_UC-PGM-CRM-067_07MAY2010
							if(log.isDebugEnabled()){
							log.debug("setting Portlet session attrib : Appeal_EntityType: for entityType: "+MaintainContactManagementUIConstants.ENT_TYPE_M);
							}
							portletRequest.getPortletSession().setAttribute("Appeal_EntityType",MaintainContactManagementUIConstants.ENT_TYPE_M,PortletSession.APPLICATION_SCOPE);
						}//EOF NFR_ESPRD00432533_UC-PGM-CRM-067_07MAY2010

		
						appealDataBean.setShowMemberInfoLabelFlag(true);
						appealDataBean.setShowProviderInfoLabelFlag(false);
					} else if (appealDataBean
							.getCommonCMCaseDetailsVO()
							.getEntityType()
							.equals(
									MaintainContactManagementUIConstants.ENT_TYPE_P)) {

						if(portletRequest!=null){//NFR_ESPRD00432533_UC-PGM-CRM-067_07MAY2010
							if(log.isDebugEnabled()){
							log.debug("setting Portlet session attrib : Appeal_EntityType: for entityType: "+MaintainContactManagementUIConstants.ENT_TYPE_P);
							}
							portletRequest.getPortletSession().setAttribute("Appeal_EntityType",MaintainContactManagementUIConstants.ENT_TYPE_P,PortletSession.APPLICATION_SCOPE);
						}//EOF NFR_ESPRD00432533_UC-PGM-CRM-067_07MAY2010

						appealDataBean.setShowProviderInfoLabelFlag(true);
						appealDataBean.setShowMemberInfoLabelFlag(false);
					}else{
						/****
						 * Added for defect ESPRD00860936
						 *This else block added to get the short description of entity type other than 'Member','provider'. This is required
						 *to show the entity type while printing Notes in Admin Hearing section. 
						 */
						String entityTypeDesc = ReferenceServiceDelegate
								.getReferenceSearchShortDescription(
										FunctionalAreaConstants.GENERAL,
										ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
										Long.valueOf(80), commonCMCaseDetailsVO
												.getEntityType());
						if (log.isDebugEnabled()) {
							log.debug("entityTypeDesc : " + entityTypeDesc);
						}
						appealDataBean.getCommonCMCaseDetailsVO()
								.setEntityType(entityTypeDesc);
					}

					if (commonCMCaseDetailsVO.getEntityType() != null) {
						commonEntityType
								.setCommonEntityTypeCode(commonCMCaseDetailsVO
										.getEntityType());
					}

				
					//Added for defect ESPRD00335100
					getDistrictOffice();

					resultString = getAppealDetails(commonCMCaseDetailsVO
							.getCaseSK());
				
				}

			

				/** hardcoded for testing */

			} else {
				
				
			}

		}
	
		return resultString;
	}

	/**
	 * @param addAppealsCritria
	 *            The addAppealsCritria to set.
	 */
	public void setAddAppealsCritria(String addAppealsCritria) {
		this.addAppealsCritria = addAppealsCritria;
	}

	/**
	 * @return Returns the providerAppealsSearch.
	 */
	public String getProviderAppealsSearch() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		String resultString = "";
		
		FacesContext fc = FacesContext.getCurrentInstance();
		CommonEntityType commonEntityType = new CommonEntityType();

		if (fc.getExternalContext().getRequestMap() != null) {
			Object object = fc.getExternalContext().getRequestMap().get(
					MaintainContactManagementUIConstants.PROV_APLS);
			if (object != null) {
				/* for defect ESPRD00872748, 
				 * fresh data should come up with closing the details of appeal record*/
				appealDataBean.setShowProviderAppealsFlag(Boolean.FALSE);
				 //Added for the Defect Id : ESPRD00731889
				 appealDataBean.setCancelFlag(true);
				CommonProviderDetailsVO providerDetailsVO = new CommonProviderDetailsVO();
				try {
					getLinksToDisable();
					BeanUtils.copyProperties(providerDetailsVO, object);
					fc.getExternalContext().getRequestMap().remove(
							MaintainContactManagementUIConstants.PROV_APLS);

				} catch (Exception e) {
					if(log.isErrorEnabled()){
					log.error(e.getMessage(), e);

					}
				}

				

				if(log.isDebugEnabled()){
				log
				.debug("Inside getProviderAppealsSearch()--providerDetailsVO is an instance...");
				}
				if (providerDetailsVO != null) {
					appealDataBean.setEnableAppealDetailAudit(false);//ESPRD00509203_ProviderAppeals_03AUG2010
					appealDataBean.setCommonProviderDetailsVO(
							providerDetailsVO);
					//	code changes for the defect id ESPRD00343176
					appealDataBean.setProviderID(
							providerDetailsVO.getProviderSysID());
					
					// Ends
					appealDataBean.setEntityType(
							providerDetailsVO.getEntityType());
					appealDataBean.setEntityID(
							providerDetailsVO.getProviderID());
					appealDataBean.setEntityName(
							providerDetailsVO.getProviderName());

					if (providerDetailsVO.getEntityType() != null) {
						commonEntityType
								.setCommonEntityTypeCode(providerDetailsVO
										.getEntityType());
					}
					resultString = getAllAppeals(providerDetailsVO
							.getProviderID(), providerDetailsVO.getEntityType());
					
				}
				
			} else {
				if(log.isDebugEnabled()){
				log.debug("Not an instance");
				}

			}
		}
		return resultString;
	}

	/**
	 * @param providerAppealsSearch
	 *            The providerAppealsSearch to set.
	 */
	public void setProviderAppealsSearch(String providerAppealsSearch) {
		this.providerAppealsSearch = providerAppealsSearch;
	}

	/**
	 * This method is used to add Note Set to the LogCase Domain object.
	 * 
	 * @param appealDO :
	 *            AppealTracking object
	 */
	public void addNoteSet(AppealTracking appealDO) {
		if(log.isInfoEnabled()){
		log.info("addNoteSet");
		}
		CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
	
		NoteSet noteSet = null;
		try {
			noteSet = commonEntityValidator.getNoteSetDO();
			/*FIND BUGS_FIX */		
			if (noteSet != null) {
				if (appealDO != null) {
					
					Set administrativeHearingSet = appealDO.getAdminHearings();
					AdministrativeHearing administrativeHearing = null;

					Iterator itr = administrativeHearingSet.iterator();
					while (itr.hasNext()) {
						administrativeHearing = (AdministrativeHearing) itr
								.next();
						administrativeHearing.setNoteSet(noteSet);
					}
				}
			}
		} catch (CommonEntityUIException commonEntityUIException) {
			if(log.isErrorEnabled()){
			log.error(commonEntityUIException.getMessage(),
					commonEntityUIException);
			}
		}
	}

	/**
	 * This method is used to populate NoteSet.
	 * 
	 * @param appealDO :
	 *            AppealTracking domain object
	 */
	public void populateNoteSet(AppealTracking appealDO) {
		if(log.isInfoEnabled()){
		log.info("populateNoteSet");
		}
		NoteSet noteSet = null;
		if (appealDO != null) {
			Set administrativeHearingSet = appealDO.getAdminHearings();
			AdministrativeHearing administrativeHearing = null;

			Iterator itr = administrativeHearingSet.iterator();
			while (itr.hasNext()) {
				administrativeHearing = (AdministrativeHearing) itr.next();
				noteSet = administrativeHearing.getNoteSet();
			}
		}

		NoteSetVO noteSetVO = null;
		CommonEntityDataBean commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();
	
		ContactHelper contactHelper = new ContactHelper();
		if (noteSet != null) {
			noteSetVO = contactHelper.convertNoteSetDomainToVO(noteSet);
			if (commonEntityDataBean != null)

			{
				commonEntityDataBean.getCommonEntityVO()
						.setNoteSetVO(noteSetVO);
			}
			// Added IF-Condition for Find_Bugs-FIX
			if(commonEntityDataBean != null){
			int notesCount = commonEntityDataBean.getCommonEntityVO()
					.getNoteSetVO().getNotesList().size();
			}
		}
	}

	public String submitSADetailsForIPC() {
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		if(log.isInfoEnabled()){
		log.info("Inside the method - submitSADetails");
		}
		FacesContext facesContext = FacesContext.getCurrentInstance();
		/*final Map map = facesContext.getExternalContext()
				.getRequestParameterMap();*/
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		//String authID = (String) map.get("claimServiceAuthID");
		String actionCode = null;
		//Added for Defect ESPRD00828394 Starts
		String preView=null;
		preView=(String) map.get("preView");
		if(preView!=null){
		UIViewRoot viewRoot = (UIViewRoot) facesContext.getViewRoot();	
		 viewRoot.setViewId(preView); 
		 facesContext.setViewRoot(viewRoot);
		}
		 //Defect ESPRD00828394 Ends
		/*final Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding("#{requestScope}").getValue(facesContext);*/
		String authID = appealDataBean.getAppealVO().getAuthID();
		String caseSK = appealDataBean.getAppealVO().getCaseSK();
		if (StringUtils.isNotBlank(authID)
				&& !isInValidSARecord(authID, caseSK)) {
			if (map != null && !map.isEmpty()) {
//				requestScope.remove("InternalServiceAuthID");
				if (StringUtils.isNotBlank(authID)) {
					
					//requestScope.put("InternalServiceAuthID", authID);
					facesContext.getExternalContext().getRequestMap().put("recieveVO",
							authID);
				}
			}
			/*log.debug("InternalServiceAuthID----->"
					+ requestScope.get("InternalServiceAuthID"));*/
		}

		else {
			if(log.isInfoEnabled()){
			log.info("++Invalid SA Record");
			}
		}
		if (StringUtils.isBlank(authID)) {
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.INVALID_SA_NUMBER_BLANK,
							MaintainContactManagementUIConstants.APL_SA_NUMBERE,
							MaintainContactManagementUIConstants.APL_SA_NUMBERE,
							actionCode);
		}
		return "";
	}

	public String submitSADetails() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isInfoEnabled()){
	    log.info("++Inside the method - submitSADetails");
	    }
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final Map map = facesContext.getExternalContext()
				.getRequestParameterMap();
		String actionCode = null;
		final Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding("#{requestScope}").getValue(facesContext);

		String authID = appealDataBean.getAppealVO().getAuthID();
		String caseSK = appealDataBean.getAppealVO().getCaseSK();
		if(log.isInfoEnabled()){
		log.info("event.getNewValue()" + authID);
		}
		if (authID != null) {
			if (StringUtils.isNotBlank(authID)
					&& !isInValidSARecord(authID, caseSK)) {
				if (map != null && !map.isEmpty()) {
					requestScope.remove("InternalServiceAuthID");
					if (StringUtils.isNotBlank(authID)) {
						//IPC is not required.
						//requestScope.put("InternalServiceAuthID", authID);
						if (appealDataBean.getAppealVO().getListSALineItemsVO() != null
								&& !appealDataBean.getAppealVO().getListSALineItemsVO()
										.isEmpty()) {
							appealDataBean.setSaLineItemDataFlag(Boolean.TRUE);
						} else {
							appealDataBean.setSaLineItemDataFlag(Boolean.FALSE);
						}
					}
				}
			} else {
				if(log.isInfoEnabled()){
				log.info("Invalid SA Record");
				}
			}
			if (StringUtils.isBlank(authID)) {
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_SA_NUMBER_BLANK,
								MaintainContactManagementUIConstants.APL_SA_NUMBERE,
								MaintainContactManagementUIConstants.APL_SA_NUMBERE,
								actionCode);
			}
		}
		return "";
	}

	/**
	 * This method will check whether the record which is going to be fetched
	 * from database has proper AuthID format, if not it will show, the SA
	 * record in database is not correct.
	 * 
	 * @param authID
	 *            String, caseSK String,
	 * @return boolean
	 */
	public boolean isInValidSARecord(String authID, String caseSK) {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isInfoEnabled()){
	    log.info("********** isInValidSARecord ************");
	    }
		boolean isInvalid = false;
		String actionCode = null;
		ServiceAuthDelegate authDelegate = new ServiceAuthDelegate();
		ServiceAuthorization serviceAuth = null;
		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		
		try {
			serviceAuth = authDelegate.getServiceAuthDetails(authID);

			if (serviceAuth == null) {
				isInvalid = true;
				ContactManagementHelper.setErrorMessage(
						MaintainContactManagementUIConstants.INVALID_SA_NUMBER,
						MaintainContactManagementUIConstants.APL_SA_NUMBERE,
						MaintainContactManagementUIConstants.APL_SA_NUMBERE,
						actionCode);
			} else {

				Set saLineItems = serviceAuth.getSaLineItems();
				Iterator it = saLineItems.iterator();
				List listSALineItemsVO = new ArrayList();
				List list = new ArrayList();

				/** preparing criteria for G_CASE_APL_STAT_CD */
				list.add(getInputCriteria(
						ReferenceServiceDataConstants.G_CASE_APL_STAT_CD,
						FunctionalAreaConstants.GENERAL));

				/** preparing criteria for G_CASE_APL_STAT_CD */
				list.add(getInputCriteria(
						ReferenceServiceDataConstants.G_CASE_APL_RSLTS_CD,
						FunctionalAreaConstants.GENERAL));

				/** preparing criteria for G_CASE_APL_STAT_CD */
				list.add(getInputCriteria(
						ReferenceServiceDataConstants.T_LI_SVC_TY_CD,
						FunctionalAreaConstants.SERVICE_AUTH));

				/** preparing criteria for T_LI_SVC_TY_CD */
				list.add(getInputCriteria(
						ReferenceServiceDataConstants.A_LI_STAT_CD,
						FunctionalAreaConstants.SERVICE_AUTH));

				referenceDataSearch = new ReferenceDataSearchVO();
				referenceDataSearch.setInputList(list);
				ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();

				referenceDataListVO = referenceServiceDelegate
						.getReferenceData(referenceDataSearch);

				Map map = referenceDataListVO.getResponseMap();

				setSaLineAppStatus(getValidData(map,
						ReferenceServiceDataConstants.G_CASE_APL_STAT_CD,
						FunctionalAreaConstants.GENERAL));
				setSaLineAppResult(getValidData(map,
						ReferenceServiceDataConstants.G_CASE_APL_RSLTS_CD,
						FunctionalAreaConstants.GENERAL));

				while (it.hasNext()) {
					SALineItemInfo objSALineItemInfo = (SALineItemInfo) it
							.next();
					/*FIND BUGS_FIX*/
					if ((objSALineItemInfo.getAppealNumber() == null || objSALineItemInfo
							.getAppealNumber().equals(Long.valueOf(0)))
							|| (caseSK!=null && objSALineItemInfo.getAppealNumber().toString()
									.trim().equals(caseSK.trim()))) {

						SALineItemsVO objSALineItemsVO = new SALineItemsVO();
						if (objSALineItemInfo.getAppealNumber() == null
								|| objSALineItemInfo.getAppealNumber().equals(
										Long.valueOf(0)/*new Long(0)*/))/*FIND BUGs_FIX*/ {
							objSALineItemsVO.setInclude(false);
						} else {
							objSALineItemsVO.setInclude(true);
						}
						objSALineItemsVO.setLineNumber(objSALineItemInfo
								.getLineNumber().intValue());

						objSALineItemsVO.setSvcTypeCode(getShortDesp(map,
								ReferenceServiceDataConstants.A_LI_STAT_CD,
								FunctionalAreaConstants.SERVICE_AUTH,
								objSALineItemInfo.getLineItemStatusCode()));

						objSALineItemsVO
								.setSaLineAppStatus(getSaLineAppStatus());
						objSALineItemsVO
								.setSaLineAppResult(getSaLineAppResult());
						objSALineItemsVO
								.setSaLineAppStatusValue(objSALineItemInfo
										.getAppealStatusCode());
						objSALineItemsVO
								.setSaLineAppResultValue(objSALineItemInfo
										.getAppealResultCode());

						objSALineItemsVO.setSvcTypeCode(getShortDesp(map,
								ReferenceServiceDataConstants.T_LI_SVC_TY_CD,
								FunctionalAreaConstants.SERVICE_AUTH,
								objSALineItemInfo.getSaLineServiceInfo().getServiceTYCode()));

						objSALineItemsVO.setSvcFromCode(objSALineItemInfo
								.getSaLineServiceInfo().getServiceCode());
						objSALineItemsVO.setSvcToCode(objSALineItemInfo
								.getSaLineServiceInfo().getServiceThroughCode());
						objSALineItemsVO.setReqAmount(objSALineItemInfo
								.getRequestedAmount());
						objSALineItemsVO.setReqUnits(objSALineItemInfo
								.getRequestedUnitAmount());
						objSALineItemsVO.setReqRate(objSALineItemInfo
								.getRequestedRateAmount());
						objSALineItemsVO.setReqBeginDate(objSALineItemInfo
								.getRequestBeginDate());
						objSALineItemsVO.setReqEndDate(objSALineItemInfo
								.getRequestEndDate());

						listSALineItemsVO.add(objSALineItemsVO);
					}

				}

				appealDataBean.getAppealVO().setListSALineItemsVO(
						listSALineItemsVO);
				//Modified for defect ESPRD00352622
				sortSAComparator(
						MaintainContactManagementUIConstants.PROV_APLS_SA_LINE_NUM,
						MaintainContactManagementUIConstants.SORT_TYPE_ASC,
						appealDataBean.getAppealVO()
								.getListSALineItemsVO());
				//ESPRD00352622 ends
			}
		}

		catch (Exception e) {
			isInvalid = true;
			ContactManagementHelper.setErrorMessage(
					MaintainContactManagementUIConstants.INVALID_SA_NUMBER,
					MaintainContactManagementUIConstants.APL_SA_NUMBERE,
					MaintainContactManagementUIConstants.APL_SA_NUMBERE,
					actionCode);
		}
		return isInvalid;
	}

	/**
	 * Method for searching Using tcn or BatchInfo
	 */
	public void searchById() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isDebugEnabled()){
		log.debug("********** searchById ***********");
	    }
	
		String actionCode = null;
		
		AppealDelegate appealDelegate = new AppealDelegate();
		EnterpriseSearchResultsVO objEnterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
		ClaimCorrectionSearchCriteriaVO claimCorrSearchCriteriaVO = new ClaimCorrectionSearchCriteriaVO();
		String tcnNumber = appealDataBean.getAppealVO().getTcnNum();
		
		if (!displayErrorMessage(appealDataBean.getAppealVO())) {
			try {
				claimCorrSearchCriteriaVO.setTcn(tcnNumber);
				objEnterpriseSearchResultsVO = appealDelegate
						.searchById(claimCorrSearchCriteriaVO);
				
				if (objEnterpriseSearchResultsVO != null
						&& objEnterpriseSearchResultsVO.getSearchResults() != null) {
					if (objEnterpriseSearchResultsVO.getSearchResults().size() == 1) {
						showCorrectionPortlet(objEnterpriseSearchResultsVO
								.getSearchResults());
					} else {
						if (objEnterpriseSearchResultsVO.getSearchResults()
								.size() == 0) {
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.VALID_CLAIM_NUMBER,
											MaintainContactManagementUIConstants.APL_CLAIM_NUMBERE,
											MaintainContactManagementUIConstants.APL_CLAIM_NUMBERE,
											actionCode);
						}
					}
				} else {
					
					ContactManagementHelper
							.setErrorMessage(
									MaintainContactManagementUIConstants.VALID_CLAIM_NUMBER,
									MaintainContactManagementUIConstants.APL_CLAIM_NUMBERE,
									MaintainContactManagementUIConstants.APL_CLAIM_NUMBERE,
									actionCode);

				}
			} catch (ClaimCorrectionSearchGetException e) {
				if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
				}
			}
		} else {
			if(log.isDebugEnabled()){
			log.debug("TCN Number is INVALIED");
			}
		}
	}

	/**
	 * This method validates if the required data is entered and displays
	 * message accordingly.
	 * 
	 * @param AppealVO
	 *            appealVO.
	 * @return isValid :boolean varaible.
	 */
	public boolean displayErrorMessage(AppealVO appealVo) {
		String BLANK = "";
		String actionCode = null;
		boolean isValid = false;
		if ((appealVo.getTcnNum() != null && !appealVo.getTcnNum().trim()
				.equals(BLANK))) {
			if (!EnterpriseCommonValidator
					.validateNumeric(appealVo.getTcnNum())) {
				isValid = true;
				ContactManagementHelper
						.setErrorMessage(
								MaintainContactManagementUIConstants.INVALID_CLAIM_NUMBER,
								MaintainContactManagementUIConstants.APL_CLAIM_NUMBERE,
								MaintainContactManagementUIConstants.APL_CLAIM_NUMBERE,
								actionCode);
			}
		} else {
			isValid = true;
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.INVALID_CLAIM_NUMBER_BLANK,
							MaintainContactManagementUIConstants.APL_CLAIM_NUMBERE,
							MaintainContactManagementUIConstants.APL_CLAIM_NUMBERE,
							actionCode);
		}
		return isValid;
	}

	/**
	 * This method displays the corresponding correction portlet when a single
	 * record is entered.
	 * 
	 * @param singleSeachResult
	 *            List.
	 */
	public void showCorrectionPortlet(List singleSeachResult) {
		if(log.isDebugEnabled()){
		log.debug("********** showCorrectionPortlet **************");
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		String receivedTCN = null;
		String memberId = null;
		Iterator searchResultIterator = singleSeachResult.iterator();
		ClaimCorrectionResponseVO claimCorrectionResponseVO = (ClaimCorrectionResponseVO) searchResultIterator
				.next();
		if(log.isDebugEnabled()){
		log.debug("BatchType Code$$"
				+ claimCorrectionResponseVO.getBatchTypeCode());
		log.debug("TCN Number$$" + claimCorrectionResponseVO.getTcn());
		log.debug("MemberSustemID$$"
				+ claimCorrectionResponseVO.getMemberSystemID());
		}

		if (claimCorrectionResponseVO.getTcn() != null) {
			receivedTCN = claimCorrectionResponseVO.getTcn();
		}
		if (claimCorrectionResponseVO.getMemberSystemID() != null) {
			memberId = claimCorrectionResponseVO.getMemberSystemID() + "";
		}

		

		//Added for the defect id ESPRD00335134
		String tcnInfo = receivedTCN + " " + memberId;
		List tcnList = new ArrayList();
		tcnList.add(tcnInfo);

		if (claimCorrectionResponseVO.getBatchTypeCode() != null) {
			if (claimCorrectionResponseVO.getBatchTypeCode().equals("D")) {
				fc.getExternalContext().getRequestMap().put("dentalTcnInfo",
						tcnList);


			} else if (claimCorrectionResponseVO.getBatchTypeCode().equals("U")
					|| claimCorrectionResponseVO.getBatchTypeCode().equals("A")) {
				fc.getExternalContext().getRequestMap().put("instiTcnInfo",
						tcnList);


			} else if (claimCorrectionResponseVO.getBatchTypeCode().equals("H")
					|| claimCorrectionResponseVO.getBatchTypeCode().equals("B")) {

				fc.getExternalContext().getRequestMap().put("profTcnInfo",
						tcnList);


			} else if (claimCorrectionResponseVO.getBatchTypeCode().equals("Z")
					|| claimCorrectionResponseVO.getBatchTypeCode().equals("Y")) {

				fc.getExternalContext().getRequestMap().put(
						"voidReplaceTcnInfo", tcnList);

			}
			// Ends
			else {
				if(log.isDebugEnabled()){
				log.debug("INVALID BATCH TYPE:navigation is not done:");
				}
			}

		}
	}

	/**
	 * When the user clicks from Quick links in left pod.
	 * 
	 * @return String
	 */
	public String sendEntityIDForIPC() {
		if(log.isDebugEnabled()){
		log.debug("=========inside sendEntityIDForIPC===========");
		}
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding("#{requestScope}").getValue(facesContext);
		String memberID = null;

		if (map != null) {
			memberID = map.get("entityId").toString();


			requestScope.remove("MemberIDServiceAuth");
			requestScope.put("MemberIDServiceAuth", memberID);

		}
		return "";
	}

	public String sendCaseTrackingProvider() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isDebugEnabled()){
		log.debug("Inside sendCaseTrackingProvider");
	    }
		String caseSK = appealDataBean.getCaseSK();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getRequestMap().put(
				"CaseTrackingProviderSk", caseSK);
		if(log.isDebugEnabled()){
		log.debug("End sendCaseTrackingProvider");
		}
		return ContactManagementConstants.EMPTY_STRING;
	}

	

	public String sendCaseTrackingAppeal() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isDebugEnabled()){
		log.debug("Inside sendCaseTrackingAppeal");
	    }
		String caseSK = appealDataBean.getCaseSK();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getRequestMap().put(
				"CaseTrackingAppealSK", caseSK);
		if(log.isDebugEnabled()){
		log.debug("End sendCaseTrackingAppeal");
		}
		return ContactManagementConstants.EMPTY_STRING;
	}

	public String sendCrspdGenAppeal() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
	    if(log.isDebugEnabled()){
		log.debug("Inside sendCrspdGenAppeal");
	    }
		String entityID = appealDataBean.getEntityID();
		String entityType = appealDataBean.getEntityType();
		String entity = entityID + ":" + entityType;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getRequestMap().put(
				"CrspdGenAppealSK", entity);
		if(log.isDebugEnabled()){
		log.debug("End sendCaseTrackingAppeal");
		}
		return ContactManagementConstants.EMPTY_STRING;

	}

	//ADDED FOR GAP-11022

	/** Holds requestScope of type Map. */
	private Map requestScope;

	/**
	 * @return Returns the requestScope.
	 */
	public Map getRequestScope() {
		return requestScope;
	}

	/**
	 * @param requestScope
	 *            The requestScope to set.
	 */
	public void setRequestScope(Map requestScope) {
		this.requestScope = requestScope;
	}

	//END FOR GAP-11022
	// START BY ICS FOR SA ITEM SORTING (CR-1622)

	/**
	 * This method is used to Select all the SA Line Items Check Boxes
	 * 
	 * @param event
	 *            ValueChangeEvent
	 * @return String
	 */
	public String selectAllSALineItems(ValueChangeEvent event) {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setSelectALLSALineItemFlag(false);
		if (event.getNewValue().equals("true")) {
			appealDataBean.setSelectALLSALineItemFlag(true);
		}
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort SA Item information based on sorted column
	 * and order for Member.
	 * 
	 * @param event
	 *            ActionEvent
	 * @return String
	 */

	public String sortSADataItemForMember(ActionEvent event) {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);

		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);

		appealDataBean.setImageRender(event.getComponent().getId());

		AppealVO appealVO = appealDataBean.getAppealVO();
		sortSAComparator(sortColumn, sortOrder, appealVO.getListSALineItemsVO());
		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * This method is used to sort SA Item information based on sorted column
	 * and order.
	 * 
	 * @param event
	 *            ActionEvent
	 * @return String
	 */

	public String sortSADataItem(ActionEvent event) {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);

		String sortColumn = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.CLOUMN_NAME);

		String sortOrder = (String) event.getComponent().getAttributes().get(
				MaintainContactManagementUIConstants.SORT_ORDER);
		
		appealDataBean.setImageRender(event.getComponent().getId());
		sortSAComparator(sortColumn, sortOrder, appealDataBean
				.getProviderSADataItemList());

		return MaintainContactManagementUIConstants.SUCCESS;
	}

	/**
	 * Comparator to the SA Item list to sort the list according to the sort
	 * order selected by the user.
	 * 
	 * @param sortColumn -
	 *            Column to be sorted.
	 * @param sortOrder -
	 *            Order by which the column should be started.
	 * @param dataList -
	 *            List to be sorted.
	 */

	private void sortSAComparator(final String sortColumn,
			final String sortOrder, List listSALineItemsVO) {

		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				SALineItemsVO data1 = (SALineItemsVO) obj1;
				SALineItemsVO data2 = (SALineItemsVO) obj2;
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
				// Sorting for Begin Date
				if (MaintainContactManagementUIConstants.PROV_APLS_SA_REQ_BEG_DT
						.equals(sortColumn)) {

					if (null != data1.getReqBeginDate()) {

						try {
							data1.setReqBeginDate(data1.getReqBeginDate());
						} catch (Exception e) {
							if(log.isDebugEnabled()){
							log.debug("exception is" + e);
							}
						}
					}
					if (null != data2.getReqBeginDate()) {

						try {
							data2.setReqBeginDate(data2.getReqBeginDate());
						} catch (Exception e) {
							if(log.isDebugEnabled()){
								log.debug("exception is" + e);
								}
						}
					}
					return ascending ? data1.getReqBeginDate().compareTo(
							data2.getReqBeginDate()) : data2.getReqBeginDate()
							.compareTo(data1.getReqBeginDate());

				}
				//Sorting for End Date
				if (MaintainContactManagementUIConstants.PROV_APLS_SA_REQ_END_DT
						.equals(sortColumn)) {

					if (null != data1.getReqEndDate()) {

						try {
							data1.setReqEndDate(data1.getReqEndDate());
						} catch (Exception e) {
							if(log.isDebugEnabled()){
								log.debug("exception is" + e);
								}
						}
					}
					if (null != data2.getReqEndDate()) {

						try {
							data2.setReqEndDate(data2.getReqEndDate());
						} catch (Exception e) {
							if(log.isDebugEnabled()){
								log.debug("exception is" + e);
								}
						}
					}
					return ascending ? data1.getReqEndDate().compareTo(
							data2.getReqEndDate()) : data2.getReqEndDate()
							.compareTo(data1.getReqEndDate());

				}

				// Sorting For Line Number
				if (MaintainContactManagementUIConstants.PROV_APLS_SA_LINE_NUM
						.equals(sortColumn)) {

					int intLineNumber =Integer.valueOf(data1.getLineNumber()); //new Integer(data1.getLineNumber());
							//.intValue();  /*FIND BUGS_FIX*/

					if (intLineNumber == 0) {
						data1
								.setLineNumber(Integer
										.parseInt(MaintainContactManagementUIConstants.STR_ZERO));
					}

					if (intLineNumber == 0) {
						data2
								.setLineNumber(Integer
										.parseInt(MaintainContactManagementUIConstants.STR_ZERO));
					}

					/*return ascending ? new Integer(data1.getLineNumber())
							.compareTo((new Integer(data2.getLineNumber())))
							: new Integer(data2.getLineNumber())
									.compareTo(Integer.valueOf((data1
											.getLineNumber())));*/  /*FIND BUGS_FIX*/
					return ascending ? Integer.valueOf(data1.getLineNumber())
					.compareTo((Integer.valueOf(data2.getLineNumber())))
					: Integer.valueOf(data2.getLineNumber())
							.compareTo(Integer.valueOf((data1
									.getLineNumber())));
					
				}
				// Sorting For Include.
				if ("include".equals(sortColumn)) {

					if (null == String.valueOf(data1.isInclude())) {
						data1
								.setLineNumber(Integer
										.parseInt(MaintainContactManagementUIConstants.STR_ZERO));
					}
					if (null == String.valueOf(data2.isInclude())) {
						data2
								.setLineNumber(Integer
										.parseInt(MaintainContactManagementUIConstants.STR_ZERO));
					}
					return ascending ? String.valueOf(data1.isInclude())
							.compareTo((String.valueOf(data2.isInclude())))
							: String.valueOf(data2.isInclude()).compareTo(
									String.valueOf(data1.isInclude()));
				}

				// Sorting For Service Type
				if (MaintainContactManagementUIConstants.PROV_APLS_SA_TY_CD
						.equals(sortColumn)) {

					if (null == data1.getSvcTypeCode()) {
						data1
								.setSvcTypeCode(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getSvcTypeCode()) {
						data2
								.setSvcTypeCode(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getSvcTypeCode().trim().compareTo(
							data2.getSvcTypeCode().trim()) : data2
							.getSvcTypeCode().trim().compareTo(
									data1.getSvcTypeCode().trim());
				}
				// Sorting For Service From Code
				if (MaintainContactManagementUIConstants.PROV_APLS_SA_FRM_CD
						.equals(sortColumn)) {

					if (null == data1.getSvcFromCode()) {
						data1
								.setSvcFromCode(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getSvcFromCode()) {
						data2
								.setSvcFromCode(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getSvcFromCode().trim().compareTo(
							data2.getSvcFromCode().trim()) : data2
							.getSvcFromCode().trim().compareTo(
									data1.getSvcFromCode().trim());
				}
				// Sorting For Service To Code
				if (MaintainContactManagementUIConstants.PROV_APLS_SA_TO_CD
						.equals(sortColumn)) {

					if (null == data1.getSvcToCode()) {
						data1
								.setSvcToCode(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getSvcToCode()) {
						data2
								.setSvcToCode(MaintainContactManagementUIConstants.NULL);
					}
					return ascending ? data1.getSvcToCode().trim().compareTo(
							data2.getSvcToCode().trim()) : data2.getSvcToCode()
							.trim().compareTo(data1.getSvcToCode().trim());
				}
				//Sorting For Service Requested Amount
				Double reqAmtConvt = new Double(
						Double
								.parseDouble(MaintainContactManagementUIConstants.STR_ZERO));

				if (MaintainContactManagementUIConstants.PROV_APLS_SA_REQ_AMT
						.equals(sortColumn)) {

					if (null == data1.getReqAmount()) {

						data1.setReqAmount(reqAmtConvt);
					}
					if (null == data2.getReqAmount()) {

						data2.setReqAmount(reqAmtConvt);
					}
					return ascending ? data1.getReqAmount().compareTo(
							data2.getReqAmount()) : data2.getReqAmount()
							.compareTo(data1.getReqAmount());
				}
				//Sorting For Service Requested Unit
				if (MaintainContactManagementUIConstants.PROV_APLS_SA_REQ_UNIT
						.equals(sortColumn)) {

					if (null == data1.getReqUnits()) {
						data1.setReqUnits(reqAmtConvt);
					}
					if (null == data2.getReqUnits()) {
						data2.setReqUnits(reqAmtConvt);
					}
					return ascending ? data1.getReqUnits().compareTo(
							data2.getReqUnits()) : data2.getReqUnits()
							.compareTo(data1.getReqUnits());
				}
				// Sorting For Service Requested Rate
				if (MaintainContactManagementUIConstants.PROV_APLS_SA_REQ_RATE
						.equals(sortColumn)) {

					if (null != data1.getReqRate()) {
						data1.setReqRate(data1.getReqRate());
					}
					if (null != data2.getReqRate()) {
						data2.setReqRate(data1.getReqRate());
					}
					return ascending ? data1.getReqRate().compareTo(
							data2.getReqRate()) : data2.getReqRate().compareTo(
							data1.getReqRate());
				}
				// Sorting For Service Status
				if (MaintainContactManagementUIConstants.PROV_APLS_SA_STATUS_CD
						.equals(sortColumn)) {

					if (null == data1.getSvcTypeCode()) {

						data1
								.setSvcTypeCode(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getSvcTypeCode()) {

						data2
								.setSvcTypeCode(MaintainContactManagementUIConstants.NULL);
					}

					return ascending ? data1.getSvcTypeCode().trim().compareTo(
							data2.getSvcTypeCode().trim()) : data2
							.getSvcTypeCode().trim().compareTo(
									data1.getSvcTypeCode().trim());

				}
				// Sorting For SaLineAppResult
				if ("saLineAppResult".equals(sortColumn)) {

					if (null == data1.getSaLineAppResultValue()) {

						data1
								.setSaLineAppResultValue(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getSaLineAppResultValue()) {

						data2
								.setSaLineAppResultValue(MaintainContactManagementUIConstants.NULL);
					}

					return ascending ? data1.getSaLineAppResultValue().trim()
							.compareTo(data2.getSaLineAppResultValue().trim())
							: data2.getSaLineAppResultValue().trim().compareTo(
									data1.getSaLineAppResultValue().trim());

				}
				// Sorting For saLineAppStatus
				if ("saLineAppStatus".equals(sortColumn)) {

					if (null == data1.getSaLineAppStatusValue()) {

						data1
								.setSaLineAppStatusValue(MaintainContactManagementUIConstants.NULL);
					}
					if (null == data2.getSaLineAppStatusValue()) {

						data2
								.setSaLineAppStatusValue(MaintainContactManagementUIConstants.NULL);
					}

					return ascending ? data1.getSaLineAppStatusValue().trim()
							.compareTo(data2.getSaLineAppStatusValue().trim())
							: data2.getSaLineAppStatusValue().trim().compareTo(
									data1.getSaLineAppStatusValue().trim());

				}
				return 0;
			}
		};
		Collections.sort(listSALineItemsVO, comparator);
	}

	// END ICS CODING FOR SA ITEM SORTING

	

	public void setLink2Show(String link2Show) {
	}

	public String getLink2Show() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		String userid = getUserID();
		try {
			/*String returnValue = fieldAccessControlImpl
					.getFiledAccessPermission(
							ContactManagementConstants.CTMGMT_APPEAL_INQRY, userid);*/
			String memberAppealsInquiry = fieldAccessControlImpl
					.getFiledAccessPermission(
							"/Enterprise/MemberAppealsInquiry", userid);
			String providerAppealsInquiry = fieldAccessControlImpl
					.getFiledAccessPermission(
							"/Enterprise/ProviderAppealsInquiry", userid);
			if(log.isDebugEnabled()){
			log.debug("++memberAppealsInquiry::"+memberAppealsInquiry);
			log.debug("++providerAppealsInquiry::"+providerAppealsInquiry);
			}
			if (("R".equalsIgnoreCase(memberAppealsInquiry))||("R".equalsIgnoreCase(providerAppealsInquiry))) {
				appealDataBean.setDisableSaveAppeal(true);
				appealDataBean.setDisableResetAppeal(true);
				appealDataBean.setDisableAppAddAdminHear(true);
				appealDataBean.setDisableAppSaveAdminHear(true);
				appealDataBean.setDisableAppResetAdminHear(true);
				appealDataBean.setDisableAppDelAdminHear(true);
				appealDataBean.setDisableResetProvApp(true);
				appealDataBean.setDisableSaveProvApp(true);
				appealDataBean.setDisableResetMemApp(true);
				appealDataBean.setDisableSaveMemApp(true);
				appealDataBean.setControlRequired(ContactManagementConstants.TRUE);
			}else if (("U".equalsIgnoreCase(memberAppealsInquiry))||("U".equalsIgnoreCase(providerAppealsInquiry))) {
				appealDataBean.setDisableAppDelAdminHear(true);
			}
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			if(log.isErrorEnabled()){
				log.error("SecurityFLSServiceException");
			}
		}
		
		
		return link2Show;
	}

	/**
	 * @param link2Show The link2Show to set.
	 */
	public Map getPermissions() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		Map linksMap = new HashMap();
		String userid = getUserID();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();

		ArrayList linksList2Pass = new ArrayList();
		linksList2Pass.add(ContactManagementConstants.MEM_APP_SAVE_MEM_APP);
		linksList2Pass.add(ContactManagementConstants.MEM_APP_RESET_MEM_APP);

		linksList2Pass.add(ContactManagementConstants.PROV_APP_SAVE_PROV_APP);
		linksList2Pass.add(ContactManagementConstants.PROV_APP_RESET_PROV_APP);

		linksList2Pass.add(ContactManagementConstants.APP_ADD_ADMIN_HEAR);
		linksList2Pass.add(ContactManagementConstants.APP_SAVE_ADMIN_HEAR);
		linksList2Pass.add(ContactManagementConstants.APP_RESET_ADMIN_HEAR);
		linksList2Pass.add(ContactManagementConstants.APP_DEL_ADMIN_HEAR);

		try {
			linksMap = fieldAccessControlImpl.getActionLinkPermission(
					linksList2Pass, userid);

		} catch (SecurityFLSServiceException e) {
			if(log.isErrorEnabled()){
				log.error("SecurityFLSServiceException");
			}
		}
		return linksMap;
	}

	/**
	 * Newly Added
	 * This method get the User ID
	 * 
	 * @return String
	 */
	private String getUserID() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
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
			if(log.isErrorEnabled()){
				log.error("Exception has come");
			}
		}
        appealDataBean.setLoggedInUserId(userID);
		return userID;
	}

	
	private String provAppealsForCaseRecord;

	/**
	 * @param provAppealsForCaseRecord The provAppealsForCaseRecord to set.
	 */
	public void setProvAppealsForCaseRecord(String provAppealsForCaseRecord) {
		this.provAppealsForCaseRecord = provAppealsForCaseRecord;
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
	 * @return List - with valid values populated.
	 */
	private List getValidData(Map map, String referenceDataConstant,
			String functionalArea) {
		List validList = new ArrayList();
		validList.add(new SelectItem(
					MaintainContactManagementUIConstants.EMPTY_STRING,
					MaintainContactManagementUIConstants.EMPTY_STRING)); // Added empty string in the drop down list
																			// for the performance fix.
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
		return validList;
	}

	private String getShortDesp(Map map, String ref, String func,
			String lineStatusCode) {
		String valLineStatusCode = null;
		List listTypeCode = (List) map.get(func + "#" + ref);
		for (int j = 0; j < listTypeCode.size(); j++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) listTypeCode.get(j);
			if (refVo.getValidValueCode().equalsIgnoreCase(lineStatusCode)) {
				valLineStatusCode = refVo.getShortDescription();
			}
		}
		return valLineStatusCode;
	}
	
	//Added for defect ESPRD00335100
	
	
	public void getDistrictOffice() {
	    appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		String userId = getUserID();
		List list = new ArrayList();
		List newDistrictList = new ArrayList();
		newDistrictList.add(new SelectItem("", ""));
		String validValueCodeAndDesc = null;
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
			if(log.isErrorEnabled()){
			log.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
				}

		}

		try {
			
			//Modified for defect ESPRD00690111 
			/*if (businessUnitDesc != null
					&& (businessUnitDesc.equals("ProviderRelations")
							|| businessUnitDesc.startsWith("ProvRel")
							|| businessUnitDesc.equals("ARS") || businessUnitDesc
							.startsWith("ARS"))) {

				list = (List) appealDataBean
						.getValidValues()
						.get(
								FunctionalAreaConstants.GENERAL
										+ MaintainContactManagementUIConstants.HASH
										+ ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_B);
			} else */
			// Fix for Defect ESPRD00755043 starts 
			if (businessUnitDesc != null
					&& (businessUnitDesc.equals(ContactManagementConstants.businessUnit_Appeals) || businessUnitDesc
							.startsWith(ContactManagementConstants.businessUnit_Appeals))) {
				list = (List) appealDataBean
						.getValidValues()
						.get(
								FunctionalAreaConstants.GENERAL
										+ MaintainContactManagementUIConstants.HASH
										+ ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_C);
			} else if(businessUnitDesc != null && (businessUnitDesc.equals(ContactManagementConstants.businessUnit_BCCP) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_DDU) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_MCS) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_ARS) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_Provider_Relations)))
			{
				list = (List) appealDataBean
				.getValidValues()
				.get(
						FunctionalAreaConstants.GENERAL
								+ MaintainContactManagementUIConstants.HASH
								+ ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_B);
			}			
			else {
				
				list = (List) appealDataBean
						.getValidValues()
						.get(
								FunctionalAreaConstants.GENERAL
										+ MaintainContactManagementUIConstants.HASH
										+ ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD);

			}
			//Fix for Defect ESPRD00755043 ends
			int distOfficListSize = list.size();

			for (int i = 0; i < distOfficListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				validValueCodeAndDesc = refVo.getValidValueCode()
						+ MaintainContactManagementUIConstants.HYPHEN
						+ refVo.getShortDescription();

				newDistrictList.add(new SelectItem(refVo.getValidValueCode(),
						validValueCodeAndDesc));
			}

			appealDataBean.setDistOfficeList(newDistrictList);

		} catch (Exception e) {
			e.getMessage();
		}

	}
	
	
	/**
	 * Added New methods to call the save method of appeal detail before
	 * navigating to case detail page
	 *  
	 */
    
    public void submitLogCaseDetails() 
    {
    	if(log.isDebugEnabled()){
        log.debug("========= inside submitCaseDetails ===========");
    	}
        /** Holds facesContext */
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map map = facesContext.getExternalContext().getRequestParameterMap();
        saveAppeal();
        if(validateAppealFlg){
        CommonCMCaseDetailsVO commonCMCaseDetailsVO = null;
        String caseSK = null;
        String entityType = null;
        String entityID = null;

        if (map != null)
        {          
            appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
            caseSK = appealDataBean.getCaseSK();
            entityType = appealDataBean.getEntityType();
            entityID = appealDataBean.getEntityID();
            commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
            commonCMCaseDetailsVO.setCaseSK(caseSK);
            commonCMCaseDetailsVO.setEntityType(entityType);
            commonCMCaseDetailsVO.setEntityID(entityID);
            commonCMCaseDetailsVO.setActionCode("CaseUpdate");
            if(log.isDebugEnabled()){
            log.debug("commonCMCaseDetailsVO.getcaseSk()"+commonCMCaseDetailsVO.getCaseSK());
            log.debug("commonCMCaseDetailsVO.getEntityType()"+commonCMCaseDetailsVO.getEntityType());
            log.debug("commonCMCaseDetailsVO.getEntityID()"+commonCMCaseDetailsVO.getEntityID());  
            }
            showCasePortlet(commonCMCaseDetailsVO);
        }
        }
       
    }
    
    /**
     * It will opens the Log Case screen.
     * 
     * @param commonCMCaseDetailsVO
     *            holds the case details.
     */
    public void showCasePortlet(CommonCMCaseDetailsVO commonCMCaseDetailsVO)
    {
    	if(log.isDebugEnabled()){
        log.debug("========= inside showCasePortlet ===========");
    	}
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        facesContext.getExternalContext().getRequestMap().put("CaseDetails",
                commonCMCaseDetailsVO);
    }


	
	

	/**
	 * @return Returns the loadValidValues.
	 */
	public String getLoadValidValues() {
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
        InputCriteria inputCriteria = null;
        List list = new ArrayList();
        String validValueCodeAndDesc = null;
        appealDataBean.setTypeList( new ArrayList());
        appealDataBean.setAppealTypeList( new ArrayList());	
        appealDataBean.setAppealResultsList( new ArrayList());
        appealDataBean.setAppealStatusList( new ArrayList());
        appealDataBean.setDistOfficeList( new ArrayList());
        appealDataBean.setMotionTypeList( new ArrayList());
        appealDataBean.setAppealDHHSDecisionList( new ArrayList());
        appealDataBean.setDecisionList( new ArrayList());
        appealDataBean.setHearingResultsList( new ArrayList());
        appealDataBean.setAppealContReasonList( new ArrayList());
        appealDataBean.setAddlInfoFileList( new ArrayList());
        appealDataBean.setHearingStatusList( new ArrayList());
        appealDataBean.getTypeList().add(new SelectItem("", ""));
        appealDataBean.getAppealTypeList().add(new SelectItem("", ""));
        appealDataBean.getAppealResultsList().add(new SelectItem("", ""));
        appealDataBean.getAppealStatusList().add(new SelectItem("", ""));
        appealDataBean.getDistOfficeList().add(new SelectItem("", ""));
        appealDataBean.getMotionTypeList().add(new SelectItem("", ""));
        appealDataBean.getAppealDHHSDecisionList().add(new SelectItem("", ""));
        appealDataBean.getDecisionList().add(new SelectItem("", ""));
        appealDataBean.getHearingResultsList().add(new SelectItem("", ""));
        appealDataBean.getAppealContReasonList().add(new SelectItem("", ""));
        appealDataBean.getAddlInfoFileList().add(new SelectItem("", ""));
        
        //Added By ICS - GAP-11022
        appealDataBean.getHearingStatusList().add(new SelectItem("", ""));
        //END BY ICS - GAP-11022
        
        //   Added by ICS for GAP # 11021
       String userId=getUserID();
        
       if(log.isDebugEnabled()){
        log.debug("sc:"+userId);   	
       }
    	ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
        String businessUnitDesc = null; 
        
    try{
        	List buinessUnitDescs=delegate.getBuisnessUnitsDescs(userId); 
        	if(log.isDebugEnabled()){
        	log.debug("buinessUnitDescs:::::::::::::"+buinessUnitDescs);
        	}
       	if(buinessUnitDescs!=null)
       	{
	        	if(buinessUnitDescs.size()==1)
	        	{
	        		businessUnitDesc = (String)buinessUnitDescs.get(0);
	
	        	}
	        	else
	        	{
	        		businessUnitDesc = ContactManagementConstants.AllOthers;
	        	}
	       }
       	else
       	{
        		businessUnitDesc = ContactManagementConstants.AllOthers;
       	}      	
       	

        	
        }
        catch(LOBHierarchyFetchBusinessException e){
        	if(log.isErrorEnabled()){
        	log.error(e.getMessage(), e);
        	}
        }catch(Exception e){
        	if(log.isErrorEnabled()){
        	log.error(e.getMessage(), e);
        	}
        	
        }
        // End of adding by ICS for GAP # 11021

        HashMap map = new HashMap();
        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

        /***********************************************************/
        /**        				ES2 Changes                        */
        /** All the IF conditions are added as part of ES2 changes */
        /***********************************************************/
        
  
     
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CASE_APL_CONTINUANCE_RSN_CD);
        list.add(inputCriteria);
       
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_ADDL_INFO_FILE_LOCN_CD);
        list.add(inputCriteria);

        
        // Added by ICS for GAP # 11021 & Major save
        
        if(businessUnitDesc != null && (businessUnitDesc.equals(ContactManagementConstants.businessUnit_DDU) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_Appeals)))
        {
        	 inputCriteria = new InputCriteria();
             inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
             inputCriteria
                     .setElementName(ReferenceServiceDataConstants.G_CASE_APL_TY_CD_Program);
             list.add(inputCriteria);
        }else{
        	 inputCriteria = new InputCriteria();
             inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
             inputCriteria.setElementName(ReferenceServiceDataConstants.G_CASE_APL_TY_CD_Program);
           
             list.add(inputCriteria);
        }
        // End of adding by ICS for GAP # 11021
        referenceDataSearch.setInputList(list);
        
        
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria.setElementName(ReferenceServiceDataConstants.G_APL_TY_CD);
        list.add(inputCriteria);

        referenceDataSearch.setInputList(list);

        
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CASE_APL_RSLTS_CD);
        list.add(inputCriteria);

        referenceDataSearch.setInputList(list);

        
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CASE_APL_STAT_CD);
        list.add(inputCriteria);

        referenceDataSearch.setInputList(list);

        
        
        //Added for defect ESPRD00335100
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_B);
        list.add(inputCriteria);
        referenceDataSearch.setInputList(list);
        
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_C);
        list.add(inputCriteria);
        referenceDataSearch.setInputList(list);
        
        
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD);
        list.add(inputCriteria);
        referenceDataSearch.setInputList(list);

        
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_ADDL_INFO_MOTION_TY_CD);
        list.add(inputCriteria);

        referenceDataSearch.setInputList(list);

        
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_DHHS_DECIS_CD);
        list.add(inputCriteria);

        referenceDataSearch.setInputList(list);

        
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_RCNSDRTN_DECIS_CD);
        list.add(inputCriteria);

        referenceDataSearch.setInputList(list);

      
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CASE_HEAR_RSLTS_CD);
        list.add(inputCriteria);
        
      
        
        //ADDED BY ICS
        if(businessUnitDesc != null && (businessUnitDesc.equals(ContactManagementConstants.businessUnit_DDU) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_Appeals)))
        {
        	inputCriteria = new InputCriteria();
         inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
         inputCriteria
         		.setElementName(ReferenceServiceDataConstants.G_CASE_HEAR_RSLTS_CD);
         list.add(inputCriteria);
        }else{ 
        	
        	inputCriteria = new InputCriteria();
            inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
            inputCriteria
            		.setElementName(ReferenceServiceDataConstants.G_CASE_HEAR_RSLTS_CD);
            list.add(inputCriteria);
        }
        
       
        
       inputCriteria = new InputCriteria();
       inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
       inputCriteria
               .setElementName(ReferenceServiceDataConstants.G_CASE_HEAR_STAT_CD);
       list.add(inputCriteria);
       
     
     
              
       
       if(businessUnitDesc != null && (businessUnitDesc.equals(ContactManagementConstants.businessUnit_DDU) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_Appeals)))
       {
       	inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CASE_HEAR_STAT_CD);
        list.add(inputCriteria);
       } else{ 
       	
    	inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CASE_HEAR_STAT_CD);
        list.add(inputCriteria);
       }
      //END BY ICS
       
        referenceDataSearch.setInputList(list);
        
        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);
        }
        catch (ReferenceServiceRequestException e)
        {
        	if(log.isErrorEnabled()){
            log.error("error in getReferenceData");
        	}
        }
        catch (SystemListNotFoundException e)
        {
        	if(log.isErrorEnabled()){
            log.error("SystemListNotFoundException in getReferenceData");
        	}
        }

        map = referenceDataListVO.getResponseMap();
        
		// Begin - Added Threads for the perfomance fix.
		Class[] argtypes = new Class[] { Map.class, String.class,
				String.class };
		Executor[] executor = new Executor[12];
		// End - Added Threads for the perfomance fix.
        
        //Added for defect ESPRD00335100
        appealDataBean.setValidValues(map);

		// Begin - Commented the code and moved to thread for the performance fix.
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_CASE_APL_CONTINUANCE_RSN_CD);
        int contListSize = list.size();

        for (int i = 0; i < contListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getAppealContReasonList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }
        appealDataBean.setAppealContReasonList(appealDataBean.getAppealContReasonList());*/
		// End - Commented the code and moved to thread for the performance fix.

		// Begin - Added a Thread executor[0] to load the values in the AppealContReasonList() for the perfomance fix.        
        executor[0] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_CASE_APL_CONTINUANCE_RSN_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[0] to load the values in the AppealContReasonList() for the perfomance fix.        

     
		// Begin - Commented the code and moved to thread for the performance fix.  
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_ADDL_INFO_FILE_LOCN_CD);
        int fileInfoListSize = list.size();
        log.debug("FileiNfo list size in GetReferenceData:" + fileInfoListSize);

        for (int i = 0; i < fileInfoListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getAddlInfoFileList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }
        appealDataBean.setAddlInfoFileList(appealDataBean.getAddlInfoFileList());*/
		// End - Commented the code and moved to thread for the performance fix.
		
		// Begin - Added a Thread executor[1] to load the values in the AddlInfoFileList() for the perfomance fix.        
		executor[1] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_ADDL_INFO_FILE_LOCN_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[1] to load the values in the AddlInfoFileList() for the perfomance fix.        
        
       // ICS added code for the defect ESPRD00336062 - start
        
        if(businessUnitDesc != null && (businessUnitDesc.equals(ContactManagementConstants.businessUnit_DDU) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_Appeals)))
        {  
		// Begin - Commented the code and moved to thread for the performance fix.  
        	/*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_CASE_APL_TY_CD_Program);
	        int typeListSize = list.size();
	        
	
	        for (int i = 0; i < typeListSize; i++)
	        {
	            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
	            validValueCodeAndDesc = refVo.getValidValueCode()
	                    + MaintainContactManagementUIConstants.HYPHEN
	                    + refVo.getShortDescription();
	            appealDataBean.getTypeList().add(new SelectItem(refVo.getValidValueCode(),
	                    validValueCodeAndDesc));
	        }
	        appealDataBean.setTypeList(appealDataBean.getTypeList());*/
		// End - Commented the code and moved to thread for the performance fix.  
		
		// Begin - Added a Thread executor[2] to load the values in the TypeList() for the perfomance fix.        		        	
        	executor[2] = call(this, "getValidData", new Object[] {
    				map, ReferenceServiceDataConstants.G_CASE_APL_TY_CD_Program, FunctionalAreaConstants.GENERAL},
    				argtypes);
		// End - Added a Thread executor[2] to load the values in the TypeList() for the perfomance fix.        		        	
        }
        else
        {
		// Begin - Commented the code and moved to thread for the performance fix.       
	        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
	                + MaintainContactManagementUIConstants.HASH
	                + ReferenceServiceDataConstants.G_CASE_APL_TY_CD_Program);
	        int typeListSize = list.size();
	        
	        for (int i = 0; i < typeListSize; i++)
	        {
	            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
	            validValueCodeAndDesc = refVo.getValidValueCode()
	                    + MaintainContactManagementUIConstants.HYPHEN
	                    + refVo.getShortDescription();
	            appealDataBean.getTypeList().add(new SelectItem(refVo.getValidValueCode(),
	                    validValueCodeAndDesc));
	        }
	        appealDataBean.setTypeList(appealDataBean.getTypeList());*/
		// End - Commented the code and moved to thread for the performance fix.  

		// Begin - Added a Thread executor[2] to load the values in the TypeList() for the perfomance fix.        		        	        
        	executor[2] = call(this, "getValidData", new Object[] {
    				map, ReferenceServiceDataConstants.G_CASE_APL_TY_CD_Program, FunctionalAreaConstants.GENERAL},
    				argtypes);
		// End - Added a Thread executor[2] to load the values in the TypeList() for the perfomance fix.        		        	
        }
        
        // ICS added code for the defect ESPRD00336062 - end

		// Begin - Commented the code and moved to thread for the performance fix.  
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_APL_TY_CD);
        int appealTypeListSize = list.size();

        for (int i = 0; i < appealTypeListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getAppealTypeList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }
        appealDataBean.setAppealTypeList(appealDataBean.getAppealTypeList());*/
		// End - Commented the code and moved to thread for the performance fix.  
        
		// Begin - Added a Thread executor[3] to load the values in the AppealTypeList() for the perfomance fix.        		        	        		
        executor[3] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_APL_TY_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[3] to load the values in the AppealTypeList() for the perfomance fix.
		// Begin - Commented the code and moved to thread for the performance fix.  
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_CASE_APL_RSLTS_CD);
        int appealResultsListSize = list.size();

        for (int i = 0; i < appealResultsListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getAppealResultsList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }
        appealDataBean.setAppealResultsList(appealDataBean.getAppealResultsList());*/
		// End - Commented the code and moved to thread for the performance fix.  

		// Begin - Added a Thread executor[4] to load the values in the AppealResultsList() for the perfomance fix.        		        	        		        
        executor[4] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_CASE_APL_RSLTS_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[4] to load the values in the AppealResultsList() for the perfomance fix.
		// Begin - Commented the code and moved to thread for the performance fix.
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_CASE_APL_STAT_CD);
        int appealStatusListSize = list.size();

        for (int i = 0; i < appealStatusListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getAppealStatusList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }
        appealDataBean.setAppealStatusList(appealDataBean.getAppealStatusList());*/
		// End - Commented the code and moved to thread for the performance fix.

		// Begin - Added a Thread executor[5] to load the values in the AppealStatusList() for the perfomance fix.        		        	        		        
        executor[5] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_CASE_APL_STAT_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[5] to load the values in the AppealStatusList() for the perfomance fix.

        
        //Added for defect ESPRD00335100
      //Modified for defect ESPRD00690111 
        /*if(businessUnitDesc != null && (businessUnitDesc.equals("ProviderRelations") ||
        		businessUnitDesc.startsWith("ProvRel")||
				businessUnitDesc.equals("ARS")|| businessUnitDesc.startsWith("ARS")))
        {
        	list = (List) map.get(FunctionalAreaConstants.GENERAL
                    + MaintainContactManagementUIConstants.HASH
                    + ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_B);
        }else*/ 
        // Fix for Defect ESPRD00755043 Starts 
        if((businessUnitDesc.equals(ContactManagementConstants.businessUnit_Appeals) ||
        		businessUnitDesc.startsWith(ContactManagementConstants.businessUnit_Appeals)))
        {
        	
			// Begin - Commented the code and moved to thread for the performance fix.
        	/*list = (List) map.get(FunctionalAreaConstants.GENERAL
                    + MaintainContactManagementUIConstants.HASH
                    + ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_C);*/
			// End - Commented the code and moved to thread for the performance fix.
			
		// Begin - Added a Thread executor[6] to load the values in the DistOfficeList() for the perfomance fix.        		        	        		        								
        	executor[6] = call(this, "getValidData", new Object[] {
    				map, ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_C, FunctionalAreaConstants.GENERAL},
    				argtypes);
		// End - Added a Thread executor[6] to load the values in the DistOfficeList() for the perfomance fix.        		        	        		        								
        }else if((businessUnitDesc.equals(ContactManagementConstants.businessUnit_BCCP) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_DDU) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_MCS) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_ARS) || businessUnitDesc.equals(ContactManagementConstants.businessUnit_Provider_Relations)))
        {

			executor[6] = call(this, "getValidData", new Object[] { map,
					ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_B,
					FunctionalAreaConstants.GENERAL }, argtypes);
		      		        	        		        								
        }     
        
        else{
        	
			// Begin - Commented the code and moved to thread for the performance fix.
        	/*list = (List) map.get(FunctionalAreaConstants.GENERAL
                    + MaintainContactManagementUIConstants.HASH
                    + ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD_B);*/
			// End - Commented the code and moved to thread for the performance fix.

		// Begin - Added a Thread executor[6] to load the values in the DistOfficeList() for the perfomance fix.
        	executor[6] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_CASE_APL_DSTCT_OFC_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[6] to load the values in the DistOfficeList() for the perfomance fix.
        }
        //Fix for Defect ESPRD00755043 ends
		// Begin - Commented the code and moved to thread for the performance fix.
        /*int distOfficListSize = list.size();

        for (int i = 0; i < distOfficListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getDistOfficeList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }*/
        
        //appealDataBean.setDistOfficeList(appealDataBean.getDistOfficeList());
		// End - Commented the code and moved to thread for the performance fix.
       
		// Begin - Commented the code and moved to thread for the performance fix.
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_ADDL_INFO_MOTION_TY_CD);
        int motionTypeListSize = list.size();

        for (int i = 0; i < motionTypeListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getMotionTypeList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }
        appealDataBean.setMotionTypeList(appealDataBean.getMotionTypeList());*/
		// End - Commented the code and moved to thread for the performance fix.        
		
		// Begin - Added a Thread executor[7] to load the values in the MotionTypeList() for the perfomance fix.		
        executor[7] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_ADDL_INFO_MOTION_TY_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[7] to load the values in the MotionTypeList() for the perfomance fix.
       
		// Begin - Commented the code and moved to thread for the performance fix.
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_DHHS_DECIS_CD);
        int dHHSDecisionListSize = list.size();

        for (int i = 0; i < dHHSDecisionListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getAppealDHHSDecisionList().add(new SelectItem(
                    refVo.getValidValueCode(), validValueCodeAndDesc));
        }
        appealDataBean.setAppealDHHSDecisionList(appealDataBean.getAppealDHHSDecisionList());*/
		// End - Commented the code and moved to thread for the performance fix.       
		
		// Begin - Added a Thread executor[8] to load the values in the AppealDHHSDecisionList() for the perfomance fix.		 
        executor[8] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_DHHS_DECIS_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[8] to load the values in the AppealDHHSDecisionList() for the perfomance fix.
      
		// Begin - Commented the code and moved to thread for the performance fix.
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_RCNSDRTN_DECIS_CD);
        int decisionListSize = list.size();

        for (int i = 0; i < decisionListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getDecisionList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }
        appealDataBean.setDecisionList(appealDataBean.getDecisionList());*/
		// End - Commented the code and moved to thread for the performance fix.
        
		// Begin - Added a Thread executor[9] to load the values in the DecisionList() for the perfomance fix.		
        executor[9] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_RCNSDRTN_DECIS_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[9] to load the values in the DecisionList() for the perfomance fix.		
       
		// Begin - Commented the code and moved to thread for the performance fix.
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                + MaintainContactManagementUIConstants.HASH
                + ReferenceServiceDataConstants.G_CASE_HEAR_RSLTS_CD);
        int hearingResultsListSize = list.size();

        for (int i = 0; i < hearingResultsListSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                    + refVo.getShortDescription();
            appealDataBean.getHearingResultsList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }
        appealDataBean.setHearingResultsList(appealDataBean.getHearingResultsList());*/
		// Begin - Commented the code and moved to thread for the performance fix.
		
		// Begin - Added a Thread executor[10] to load the values in the HearingResultsList() for the perfomance fix.				        
        executor[10] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_CASE_HEAR_RSLTS_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[10] to load the values in the HearingResultsList() for the perfomance fix.				        
      
        //Added By ics GAP-11022
		// Begin - Commented the code and moved to thread for the performance fix.
        /*list = (List) map.get(FunctionalAreaConstants.GENERAL
                 + MaintainContactManagementUIConstants.HASH
                 + ReferenceServiceDataConstants.G_CASE_HEAR_STAT_CD);
         int hearingStatusListSize = list.size();
        
         for (int i = 0; i < hearingStatusListSize; i++)
        {
             ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
             validValueCodeAndDesc = refVo.getValidValueCode()
                    + MaintainContactManagementUIConstants.HYPHEN
                     + refVo.getShortDescription();
             appealDataBean.getHearingStatusList().add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
         }
         appealDataBean.setHearingStatusList(appealDataBean.getHearingStatusList());*/
		// End - Commented the code and moved to thread for the performance fix.

		// Begin - Added a Thread executor[11] to load the values in the HearingStatusList() for the perfomance fix.				                 
        executor[11] = call(this, "getValidData", new Object[] {
				map, ReferenceServiceDataConstants.G_CASE_HEAR_STAT_CD, FunctionalAreaConstants.GENERAL},
				argtypes);
		// End - Added a Thread executor[11] to load the values in the HearingStatusList() for the perfomance fix.				                 
         
		// Begin - Setting the lists into the databean for the performance fix.
        try {
	        appealDataBean.setAppealContReasonList((List) executor[0].get());
	        appealDataBean.setAddlInfoFileList((List) executor[1].get());
	        appealDataBean.setTypeList((List) executor[2].get());
	        appealDataBean.setAppealTypeList((List) executor[3].get());
	        appealDataBean.setAppealResultsList((List) executor[4].get());
	        appealDataBean.setAppealStatusList((List) executor[5].get());
	        appealDataBean.setDistOfficeList((List) executor[6].get());
	        appealDataBean.setMotionTypeList((List) executor[7].get());
	        appealDataBean.setAppealDHHSDecisionList((List) executor[8].get());
	        appealDataBean.setDecisionList((List) executor[9].get());
	        appealDataBean.setHearingResultsList((List) executor[10].get());
	        appealDataBean.setHearingStatusList((List) executor[11].get());
        }
        catch(Exception e)
        {
        	if(log.isErrorEnabled()){
        	log.error("Exception is generating");
        }
        }
		// End - Setting the lists into the databean for the performance fix.
        
         appealDataBean.setValidValueFlag(false);
        //End by ICS GAP-11022

    
		return loadValidValues;
	}
	/**
	 * @param loadValidValues The loadValidValues to set.
	 */
	public void setLoadValidValues(String loadValidValues) {
		
	}
	//Added for CR ESPRD00373565 AuditLog starts
	 private void doAuditKeyListOperation(AppealVO appealVO) {
		 if(log.isDebugEnabled()){
		 log.debug(">>---------> Inside caseTypeVO caseTypeVO doAuditKeyListOperation:");
		 }
		if (auditableAppealDetails == null
				|| auditableAppealDetails.isEmpty()) {
			getAuditableAppealDetailsOnce();
		}
		if (appealVO.getAuditKeyList() != null
				&& !appealVO.getAuditKeyList().isEmpty()) {
			AuditDataFilter.filterAuditKeys(auditableAppealDetails, appealVO);
		} else {
			if(log.isDebugEnabled()){
			log.debug("======appealVO====Before Filter Empty===");
			}
		}
		if(log.isDebugEnabled()){
		log.debug(">>------appealVO---->" + appealVO.getAuditKeyList());
		}
	}

	static List auditableAppealDetails;

	/**
	 * @return Returns the auditableSwipeDetails.
	 */
	public List getAuditableAppealDetailsOnce() {
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		auditableAppealDetails = new ArrayList();
		
		// 1. Appeal Details Field Set
		auditableAppealDetails.add(createAuditableField("Program Type","caseAppealTypeCode"));
		auditableAppealDetails.add(createAuditableField("Appeal Type","appealTypeCode"));
		auditableAppealDetails.add(createAuditableField("Reviewer Name","reviewerName"));
		auditableAppealDetails.add(createAuditableField("Assigned Date","assignedDate"));
		auditableAppealDetails.add(createAuditableField("Previous Appeal Number","previousAppealNumber"));
		if(appealDataBean.isShowContReasonFlag()){
			auditableAppealDetails.add(createAuditableField("Continuance Reason","caseAppealReasonCode"));			
		}
		auditableAppealDetails.add(createAuditableField("Appeal Date","appealDate"));
		auditableAppealDetails.add(createAuditableField("Appeal Results","caseAppealResultsCode"));
		auditableAppealDetails.add(createAuditableField("Appeal Status","caseAppealStatusCode"));
		auditableAppealDetails.add(createAuditableField("Appeal Status Update Date","caseAppealStatusCodeDate"));
		auditableAppealDetails.add(createAuditableField("District Office","caseAppealDistrictOfficeCode"));
		if(appealDataBean.isShowClaimCompFlag()){
			auditableAppealDetails.add(createAuditableField("Claim TCN","transactionControlNumber"));
		}
		if(appealDataBean.isShowSACompFlag()){
			auditableAppealDetails.add(createAuditableField("SA Number","serviceAuthorizationID"));
		}
		// 2. Additional Information Field Set
		auditableAppealDetails.add(createAuditableField("Requested Date","additionalInfoReqdDate"));
		auditableAppealDetails.add(createAuditableField("Notification Letter Sent Date","additionalInfoNotifyLtrSentDate"));
		auditableAppealDetails.add(createAuditableField("Due Date","additionalInfoDueDate"));
		auditableAppealDetails.add(createAuditableField("Received 2nd Request Date","additionalInfoRecvd2ndReqdDate"));
		auditableAppealDetails.add(createAuditableField("Received Date","additionalInfoRecvdDate"));
		auditableAppealDetails.add(createAuditableField("2nd Revised Due Date","additionalInfo2ndRevisedDueDate"));
		auditableAppealDetails.add(createAuditableField("Revised Review Due","additionalInfoRevisedReviewDueDate"));
		auditableAppealDetails.add(createAuditableField("AAU Officer","additionalInfoAauOfficerName"));
		auditableAppealDetails.add(createAuditableField("Response Requested By","additionalInfoRespReqdDueDate"));
		auditableAppealDetails.add(createAuditableField("Motion Type","additionalInfoMotionTypeCode"));
		auditableAppealDetails.add(createAuditableField("Date Motion Filed","additionalInfoMotionFiledDate"));
		auditableAppealDetails.add(createAuditableField("Case File Location","additionalInfoFileLocationCode"));
		auditableAppealDetails.add(createAuditableField("Client Representative","additionalInfoClientRepresentName"));
		 
		// 3. Reconsideration Field Set
		auditableAppealDetails.add(createAuditableField("Order Date","reconsiderationOrderDate"));
		auditableAppealDetails.add(createAuditableField("Returned Date","reconsiderationReturnDate"));
		auditableAppealDetails.add(createAuditableField("Reviewer Name","reconsiderationReviewerName"));
		auditableAppealDetails.add(createAuditableField("Decision","reconsiderationDescCode"));
		auditableAppealDetails.add(createAuditableField("Decision Date","reconsiderationDescDate"));
		auditableAppealDetails.add(createAuditableField("Notification Letter Sent Date","reconsiderationNotifyLtrSentDate"));
		
		// 4. Informal Review Field Set
		auditableAppealDetails.add(createAuditableField("Requested Date","informalReviewRequiredDate"));
		auditableAppealDetails.add(createAuditableField("Acknowledgement Sent Date","informalReviewAckSentDate"));
		auditableAppealDetails.add(createAuditableField("Sent for Review Date","informalReviewSentDate"));
		auditableAppealDetails.add(createAuditableField("Due Date","informalReviewDueDate"));
		
		// 5. DHHS Formal Review Field Set
		auditableAppealDetails.add(createAuditableField("Sent to DHHS Date","dhhsSentDate"));
		auditableAppealDetails.add(createAuditableField("DHHS Decision Due","dhhsDescDueDate"));
		auditableAppealDetails.add(createAuditableField("DHHS Decision","dhhsDescCode"));
		auditableAppealDetails.add(createAuditableField("Notification Letter Sent Date","dhhsNotifyLtrSentDate"));
		
		return auditableAppealDetails;
	}

	private AuditableField createAuditableField(String fieldName,
			String domainAttributeName) {
		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(fieldName);
		auditableField.setDomainAttributeName(domainAttributeName);
		return auditableField;
 }
	// CR ESPRD00373565 AuditLog ends
//	ESPRD00509203_ProviderAppeals_03AUG2010
	public void enableAppealDetailAudit(){
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setEnableAppealDetailAudit(true);
		if(appealDataBean.getCommonCMCaseDetailsVO()!=null 
				&& appealDataBean.getCommonCMCaseDetailsVO().getCaseSK()!=null 
				&& !appealDataBean.getCommonCMCaseDetailsVO().getCaseSK().isEmpty());
		getAppealDetails(appealDataBean.getCommonCMCaseDetailsVO().getCaseSK());
	}
	//EOF ESPRD00509203_ProviderAppeals_03AUG2010
	/*
	 * searchClaimInquiryByTCN navigates ot Claims Inquiry portlet
	 * for defect ESPRD00529011 implemented
	 */
	public void searchClaimInquiryByTCN()
    {
		if(log.isInfoEnabled()){
		log.info("++Begin of searchClaimInquiryByTCN()");
		}
		boolean validTcn = true;
		String preView=null;
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		 EnterpriseSearchResultsVO objEnterpriseSearchResultsVO = null;
	     ClaimCorrectionDelegate objClaimCorrectionDelegate = null;
	     FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext extContext = fc.getExternalContext();
        String tcnNumber = appealDataBean.getAppealVO().getTcnNum();
        //Added for Defect ESPRD00828394 Starts
        Map map = fc.getExternalContext().getRequestParameterMap();
        preView=(String) map.get("preView");
		if(preView!=null){
		UIViewRoot viewRoot = (UIViewRoot) fc.getViewRoot();	
		 viewRoot.setViewId(preView); 
		 fc.setViewRoot(viewRoot);
		}
		 //Defect ESPRD00828394 Ends
        if(tcnNumber != null){
        	validTcn = validationByTCN(tcnNumber);
        	 if(validTcn){
        	 	 objClaimCorrectionDelegate = new ClaimCorrectionDelegate();
                 objEnterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
                         	try {
						objEnterpriseSearchResultsVO = objClaimCorrectionDelegate
						         .searchClaimInquiryByTCN(tcnNumber);
					} catch (com.acs.enterprise.mmis.operations.claimadministration.application.exception.ClaimCorrectionSearchGetException e) {
						if(log.isDebugEnabled()){
						log.debug("++ClaimCorrectionSearchGetException");
						}
					}
                 	 navigateToDetails(objEnterpriseSearchResultsVO, extContext);
                 }
            }
        }

/**
     * @param fc
     * @param tcnNo
     * @return boolean
     */
    public boolean validationByTCN(String tcnNo)
    {
    	String actionCode = null;
    	if (appealDataBean.isAddAppealFlag()) {
			actionCode = MaintainContactManagementUIConstants.ADD;
		} else {
			actionCode = MaintainContactManagementUIConstants.UPDATE;
		}
        boolean isValid = true;
        if (StringUtils.isBlank(tcnNo)) {
        	isValid = false;
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.APPEAL_DTL_TCN_NUMBER_RQD,
							MaintainContactManagementUIConstants.APPEAL_DTL_TCN_NUMBER,
							MaintainContactManagementUIConstants.APPEAL_DTL_TCN_NUMBER,
							actionCode);
		}
        //validation is done considering tcn no may contains alphabets and numeric characters.
        else if ((!StringUtils.isAlphanumeric(tcnNo)) || (17 > tcnNo.length()))
        {
        	isValid = false;
			ContactManagementHelper
					.setErrorMessage(
							MaintainContactManagementUIConstants.APPEAL_DTL_TCN_NUMBER_INCRRCT,
							MaintainContactManagementUIConstants.APPEAL_DTL_TCN_NUMBER,
							MaintainContactManagementUIConstants.APPEAL_DTL_TCN_NUMBER,
							actionCode);
        }
        return isValid;
    }
    
    /**
     * @param objEnterpriseSearchResultsVO
     */
    private void navigateToDetails(
            EnterpriseSearchResultsVO objEnterpriseSearchResultsVO,
            ExternalContext extContext)
    {
        String claimType = MaintainContactManagementUIConstants.EMPTY_STRING;
        String tcnNo = MaintainContactManagementUIConstants.EMPTY_STRING;
        String memberSysId = MaintainContactManagementUIConstants.EMPTY_STRING;
        
        if ((objEnterpriseSearchResultsVO == null)
                || (objEnterpriseSearchResultsVO.getRecordCount() == 0))
        {
        	String actionCode = null;
        	if (appealDataBean.isAddAppealFlag()) {
    			actionCode = MaintainContactManagementUIConstants.ADD;
    		} else {
    			actionCode = MaintainContactManagementUIConstants.UPDATE;
    		}
            	ContactManagementHelper
				.setErrorMessage(
						MaintainContactManagementUIConstants.VALID_CLAIM_NUMBER,
						MaintainContactManagementUIConstants.APPEAL_DTL_TCN_NUMBER,
						MaintainContactManagementUIConstants.APPEAL_DTL_TCN_NUMBER,
						actionCode);
//            String valueToSendThruIPC = MaintainContactManagementUIConstants.EMPTY_STRING
//            + MaintainContactManagementUIConstants.EMPTY_STRING
//            + MaintainContactManagementUIConstants.EMPTY_STRING;
//            extContext.getRequestMap().put("invokeDetails",valueToSendThruIPC);

        }
        else if (objEnterpriseSearchResultsVO.getRecordCount() == 1)
        {

            ClaimInquirySearchResultsVO claimInquirySearchResultsVO = (ClaimInquirySearchResultsVO) objEnterpriseSearchResultsVO
                    .getSearchResults().get(0);
            if (null != claimInquirySearchResultsVO.getClaimType())
            {
                claimType = claimInquirySearchResultsVO.getClaimType();
            }
            if (null != claimInquirySearchResultsVO.getTCN())
            {
                tcnNo = claimInquirySearchResultsVO.getTCN();
            }
            if (null != claimInquirySearchResultsVO.getMemberSystemID())
            {
                memberSysId = claimInquirySearchResultsVO.getMemberSystemID()
                        + MaintainContactManagementUIConstants.EMPTY_STRING;
            }

			  
              //<!-- Commented for the Defect ESPRD00731985 -->
            /*String valueToSendThruIPC = MaintainContactManagementUIConstants.EMPTY_STRING;
            if ((tcnNo != null) && (tcnNo.length() > 0))
            {
                valueToSendThruIPC = claimType+ "," + tcnNo+ "," + memberSysId;
                System.err.println("valueToSendThruIPC : " + valueToSendThruIPC);
            }*/
			//<!-- Added for the Defect ESPRD00731985 -->
            extContext.getRequestMap().put("objInvokeDetails",claimInquirySearchResultsVO);
        }
    }
    
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
    
    
 
    /**
	 * editAdminHearingCalledFromAddModeSave is used to retrieve the New record saved data and display in edit mode 
	 * As per KYM/NH implemented
	 * 
	 * @return String
	 */
    
 // Defect ESPRD00729574 modified Starts
	public String editAdminHearingCalledFromAddModeSave() {
		if(log.isDebugEnabled()){
		log.debug("Inside editAdminHearing------>");
		}
		AdminHearingVO tempAdminHearingVO = new AdminHearingVO();
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setEditAdminHearingFlag(true);
		appealDataBean.setAddAdminHearingFlag(false);
		appealDataBean.setShowSuccessMsgFlag(true);
		appealDataBean.setShowDeletedMsgFlag(false);
		if(appealDataBean.getAdminHearingList()!=null && appealDataBean.getAdminHearingList().size()>0){
		int intAdminHearingRowIndex = appealDataBean.getAdminHearingList().size()-1;
		
		String index = Integer.toString(intAdminHearingRowIndex);
		if (index != null) {
			appealDataBean.setAdminHearingIndex(index);
			AdminHearingVO adminHearingVO = (AdminHearingVO) appealDataBean
					.getAdminHearingList().get(intAdminHearingRowIndex);
			//for ESPRD00782216 defect.
			//notes
			NoteSetVO noteSetVO = adminHearingVO.getAdminNotesetVO();
			commonEntityDataBean = ContactHelper.getCommonEntityDataBean();

				if (adminHearingVO != null
						&& adminHearingVO.getNoteList() != null
						&& !adminHearingVO.getNoteList().isEmpty()) {
					ArrayList noteList = (ArrayList) adminHearingVO
							.getNoteList();
					commonEntityDataBean.setNoteList((ArrayList) noteList
							.clone());
					commonEntityDataBean.setTempNoteList((ArrayList) noteList
							.clone());
					commonEntityDataBean.getCommonEntityVO()
							.getDetailNoteSetVO().setNotesList(
									(ArrayList) noteList.clone());
					appealDataBean.setNotesListIndicator(Boolean.TRUE);
				} else {
					commonEntityDataBean.getNoteList().clear();
					commonEntityDataBean.getTempNoteList().clear();
					appealDataBean.setNotesListIndicator(Boolean.FALSE);
				}
			
			try {
				
				BeanUtils.copyProperties(tempAdminHearingVO, adminHearingVO);
			} catch (IllegalAccessException e) {
				if(log.isErrorEnabled()){
				log.error("error in editAdminHearing IllegalAccessException");
				}
			} catch (InvocationTargetException e) {
				
				if(log.isErrorEnabled()){
					log.error("error in editAdminHearing InvocationTargetException");
					}
			}
		}
		}
		appealDataBean.getAppealVO().setAdminHearingVO(tempAdminHearingVO);

		return MaintainContactManagementUIConstants.SUCCESS;
	}
	
	// Defect ESPRD00729574 modified Ends
	
	/** This method is to clear data
	 *  before populating with fresh data
	 * */
	private void clearDataFields(){
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		appealDataBean.setAppealVO(new AppealVO());
		appealDataBean.setTempAppealVO(new AppealVO());
		appealDataBean.getAdminHearingList().clear();
		appealDataBean.setShowSACompFlag(Boolean.FALSE);
		appealDataBean.setShowFinalSuccessMsgFlag(Boolean.FALSE);
	}
	
	/** close notes section
	 * */
	private void renderNotNotes(){
		ContactHelper contactHelper = new ContactHelper();
		CommonEntityDataBean commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();
		commonEntityDataBean.setDetailNewNotesRender(false);
        commonEntityDataBean.setDetailFilterNotesRender(false);
        commonEntityDataBean.setDetailMainNotesRender(false);
        commonEntityDataBean.setDetailSaveNotesChk(false);
        commonEntityDataBean.setFilterEnabled(true);
	}
	
	/***
	 * For invoking the right click new window opening.This method will be
	 * invoke on right click on command link for retrieving the details of
	 * Case/Claims/SA according to input provided by the user.
	 * */
	public void submitDetailsForRightClick() {
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		FacesContext fc = FacesContext.getCurrentInstance();
		String cmdLinkID = fc.getExternalContext().getRequestParameterMap()
				.get("linkID").toString();
		appealDataBean.setRightClickIndicator(cmdLinkID);
		//Added for the Defect ESPRD00828394
		handleNavigationPath(fc);
	}
	
	/**
	 * This method is invoked onblur while submitting the value provided by the
	 * user to the databean in Claims/SA.
	 * */
	public String sumbitTCNDetails() {

		return ContactManagementConstants.EMPTY_STRING;
	}
	/**
	 * This Method Added for displaying the blank jsp instead of loading Source
	 * Page again while navigating to Target Page, for Defect ESPRD00828394
	 * */
	public void handleNavigationPath(FacesContext facesContext) {		
		   String outCome = "/jsp/appeals/rightClickProgress.jsp";
		   UIViewRoot viewRoot = (UIViewRoot) facesContext.getViewRoot();		   
		   appealDataBean.setPreView(viewRoot.getViewId());
		   viewRoot.setViewId(outCome); 
           facesContext.setViewRoot(viewRoot);  
           
	}
	
	/**
	 * This method is used for displaying quick links depending upon the Roles
	 * provided to user while creating,for defect ESPRD00873448
	 * */
	public void getLinksToDisable(){
		log.info("inside getLinksToDisable-->");
		appealDataBean = (AppealDataBean) getDataBean(ContactManagementConstants.APPEAL_DATA_BEAN);
		RenderRequest request = null;
		ActionRequest actionRequest = null;
		PortletSession session = null;
		ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
		
		Object objRequest = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if(objRequest instanceof RenderRequest)
		{
			 request = (RenderRequest)objRequest;
			 session = request.getPortletSession(false);
		}
		else if(objRequest instanceof ActionRequest)
		{
			actionRequest = (ActionRequest)objRequest;
			session = actionRequest.getPortletSession(false);
		}
		
		Map accessPermissions = null;
		if(session!=null){
			
			if(session.getAttribute("AppealsAccessPermission",PortletSession.APPLICATION_SCOPE)==null){
				session.setAttribute("AppealsAccessPermission",contactManagementHelper.getPagePermissions(getUserID()), PortletSession.APPLICATION_SCOPE);
			}
			accessPermissions =  (Map) session.getAttribute("AppealsAccessPermission",PortletSession.APPLICATION_SCOPE);
			log.debug(">>-----getting accessPermissions:-->"+accessPermissions);
	    	
	    	if(accessPermissions!=null){
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_EDMS) == null)
	    		{
	    			appealDataBean.setRenderEDMSQuickLinks(false);
	    		}
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_CORRES_GEN) == null)
	    		{
	    			appealDataBean.setRenderCorresGenQuickLinks(false);
	    		}
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_SA) == null)
	    		{
	    			appealDataBean.setRenderSAQuickLinks(false);
	    		}
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_PRV_APPEALS) == null)
	    		{
	    			appealDataBean.setRenderProviderAppealsInquiryQuickLinks(false);
	    		}
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_MEM_APPEALS) == null)
	    		{
	    			appealDataBean.setRenderMemberAppealsInquiryQuickLinks(false);
	    		}
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_CASE_TRACKING) == null)
	    		{
	    			appealDataBean.setRenderCaseTrackingQuickLinks(false);
	    		}
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_CLAIMS_INQ) == null)
	    		{
	    			appealDataBean.setRenderClaimsInquiryQuickLinks(false);
	    		}
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_BENEFIT_PLAN) == null)
	    		{
	    			appealDataBean.setRenderBenefitPlanQuickLinks(false);
	    		}
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_EVENT_TRACK) == null)
	    		{
	    			appealDataBean.setRenderEventTrackingQuickLinks(false);
	    		}
	    		if(accessPermissions.get(MaintainContactManagementUIConstants.CT_MGMT_QUICK_LINK_FIN_ENTITY) == null)
	    		{
	    			appealDataBean.setRenderFinancialEntityQuickLinks(false);
	    		}
	    		
	    	}
		}
	}
	
}