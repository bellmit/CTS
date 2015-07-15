/*
 * Created on Jan 18, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
//import com.acs.enterprise.common.program.commonentities.view.bean.CommonNotesControllerBean;
//import com.acs.enterprise.common.program.commonentities.view.bean.DetailedNotesControllerBean;
import com.acs.enterprise.common.program.commonentities.view.exception.CommonEntityUIException;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
//import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.contactmanagement.common.domain.AdministrativeHearing;
import com.acs.enterprise.common.program.contactmanagement.common.domain.AppealTracking;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.AppealDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AdminHearingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AppealVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.mmis.operations.serviceauthorization.common.domain.ServiceAuthorization;


/**
 * @author wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class AppealDOConverter
{

    
	private static final EnterpriseLogger log = EnterpriseLogFactory
    .getLogger(AppealDOConverter.class);
    
   
    
    public static final String APPEAL_DATA_BEAN = "appealDataBean";
    
    private CommonEntityDataBean commonEntityDataBean;
	public static final String COMMON_ENTITY_DATA_BEAN = "commonEntityDataBean";
    

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

    private AppealDataBean appealDataBean = (AppealDataBean) getDataBean(APPEAL_DATA_BEAN);
    
    /** holds the user ID */
	private String userID = appealDataBean.getLoggedInUserId();
	
    private String commonEntityTypeCode=appealDataBean.getEntityType();
    /**
     * Converts the Appeal VO to DO.
     * 
     * @param appealVO
     *            AppealVO.
     * @param adminHearingList
     *            List
     * @return AppealTracking.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public AppealTracking convertAppealVOToDO(AppealVO appealVO,
            List adminHearingList)
    {
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        AppealTracking appealTracking = new AppealTracking();

        if(!isNullOrEmptyField(appealVO.getAplDt()))
        appealTracking.setAppealDate(contactManagementHelper
                .dateConverter(appealVO.getAplDt()));
       //for BR CON0387 
       if (!isNullOrEmptyField(appealVO.getCaseAplStatCd())
				&& !appealVO.getCaseAplStatCd().equals(
						appealVO.getPreviousStatus())) {
			appealTracking.setCaseAppealStatusCodeDate(new Date());
		} else {
			if (!isNullOrEmptyField(appealVO.getCaseAplStatCdDt())) {
				appealTracking
						.setCaseAppealStatusCodeDate(contactManagementHelper
								.dateConverter(appealVO.getCaseAplStatCdDt()));
			}
		}
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoReqDt()))
        appealTracking.setAdditionalInfoReqdDate(contactManagementHelper
                .dateConverter(appealVO.getAddlInfoReqDt()));
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoDueDt()))
        appealTracking.setAdditionalInfoDueDate(contactManagementHelper
                .dateConverter(appealVO.getAddlInfoDueDt()));
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoNotfyLtrSentDt()))
        appealTracking
                .setAdditionalInfoNotifyLtrSentDate(contactManagementHelper
                        .dateConverter(appealVO.getAddlInfoNotfyLtrSentDt()));
      
        if(!isNullOrEmptyField(appealVO.getAddlInfoRecd2NdReqDt()))
        appealTracking
        .setAdditionalInfoRecvd2ndReqdDate(contactManagementHelper
                .dateConverter(appealVO.getAddlInfoRecd2NdReqDt()));
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoRecdDt()))
        appealTracking.setAdditionalInfoRecvdDate(contactManagementHelper
                .dateConverter(appealVO.getAddlInfoRecdDt()));
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoRvsdRevwDueDt()))
        appealTracking
                .setAdditionalInfoRevisedReviewDueDate(contactManagementHelper
                        .dateConverter(appealVO.getAddlInfoRvsdRevwDueDt()));
        
        if(!isNullOrEmptyField(appealVO.getAddlInfo2NdRvsdDueDt()))
        appealTracking
                .setAdditionalInfo2ndRevisedDueDate(contactManagementHelper
                        .dateConverter(appealVO.getAddlInfo2NdRvsdDueDt()));
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoRespReqDueDt()))
        appealTracking.setAdditionalInfoRespReqdDueDate(contactManagementHelper
                .dateConverter(appealVO.getAddlInfoRespReqDueDt()));
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoFiledDt()))
        appealTracking.setAdditionalInfoMotionFiledDate(contactManagementHelper
                .dateConverter(appealVO.getAddlInfoFiledDt()));
        
        if(!isNullOrEmptyField(appealVO.getAsgnDt()))
        appealTracking.setAssignedDate(contactManagementHelper
                .dateConverter(appealVO.getAsgnDt()));
       
     
        if(!isNullOrEmptyField(appealVO.getDhhsDecisDueDt()))
        appealTracking.setDhhsNotifyLtrSentDate(contactManagementHelper
                .dateConverter(appealVO.getDhhsDecisDueDt()));
        
        if(!isNullOrEmptyField(appealVO.getDhhsSentDt()))
        appealTracking.setDhhsSentDate(contactManagementHelper
                .dateConverter(appealVO.getDhhsSentDt()));
        
        if(!isNullOrEmptyField(appealVO.getInfrmlRevwAckSentDt()))
        appealTracking.setInformalReviewAckSentDate(contactManagementHelper
                .dateConverter(appealVO.getInfrmlRevwAckSentDt()));
        
        if(!isNullOrEmptyField(appealVO.getInfrmlRevwDueDt()))
        appealTracking.setInformalReviewDueDate(contactManagementHelper
                .dateConverter(appealVO.getInfrmlRevwDueDt()));
        
        if(!isNullOrEmptyField(appealVO.getInfrmlRevwReqDt()))
        appealTracking.setInformalReviewRequiredDate(contactManagementHelper
                .dateConverter(appealVO.getInfrmlRevwReqDt()));
        
        if(!isNullOrEmptyField(appealVO.getInfrmlRevwSentDt()))
        appealTracking.setInformalReviewSentDate(contactManagementHelper
                .dateConverter(appealVO.getInfrmlRevwSentDt()));
        
        if(!isNullOrEmptyField(appealVO.getRcnsdrtnDecisDt()))
        appealTracking.setReconsiderationDescDate(contactManagementHelper
                .dateConverter(appealVO.getRcnsdrtnDecisDt()));
        
        if(!isNullOrEmptyField(appealVO.getRcnsdrtnNotfyLtrSentDt()))
        appealTracking
                .setReconsiderationNotifyLtrSentDate(contactManagementHelper
                        .dateConverter(appealVO.getRcnsdrtnNotfyLtrSentDt()));
        
        if(!isNullOrEmptyField(appealVO.getRcnsdrtnRtrnDt()))
        appealTracking.setReconsiderationReturnDate(contactManagementHelper
                .dateConverter(appealVO.getRcnsdrtnRtrnDt()));

        if(!isNullOrEmptyField(appealVO.getAplTyCd()))
        appealTracking.setAppealTypeCode(appealVO.getAplTyCd());
        
        if(!isNullOrEmptyField(appealVO.getCaseAplDispCd()))
        appealTracking.setCaseAppealDisputeCode(appealVO.getCaseAplDispCd());
        if(StringUtils.isNotBlank(appealVO.getCaseAplContinuanceRsnCd())
        		 && appealVO.getAplTyCd().equalsIgnoreCase("CO"))
        {
            appealTracking.setCaseAppealReasonCode(appealVO.getCaseAplContinuanceRsnCd());
            log.debug("appealTracking.getCaseAppealReasonCode" 
                    + appealTracking.getCaseAppealReasonCode());

        }
        
        if(!isNullOrEmptyField(appealVO.getCaseAplRsltsCd()))
        appealTracking.setCaseAppealResultsCode(appealVO.getCaseAplRsltsCd());
        
        if(!isNullOrEmptyField(appealVO.getCaseAplStatCd()))
        appealTracking.setCaseAppealStatusCode(appealVO.getCaseAplStatCd());
        
        if(!isNullOrEmptyField(appealVO.getCaseAplTyCd()))
        appealTracking.setCaseAppealTypeCode(appealVO.getCaseAplTyCd());
        if (appealVO.getCaseAplTyCd().equalsIgnoreCase(
                MaintainContactManagementUIConstants.COMP_C))
        {
            appealTracking.setTransactionControlNumber(appealVO.getTcnNum());
        }
        if (appealVO.getCaseAplTyCd().equalsIgnoreCase("SA") || appealVO.getCaseAplTyCd().equalsIgnoreCase("SE")) { //ESPRD00510500_UC-PGM-CRM-065_09AUG2010
			ServiceAuthorization serviceAuth = new ServiceAuthorization();
			serviceAuth.setServiceAuthorizationID(appealVO.getAuthID());
			appealTracking.setServiceAuthorization(serviceAuth);
			
		}
      
        if(!isNullOrEmptyField(appealVO.getCaseAplDstctOfcCd()))
        appealTracking.setCaseAppealDistrictOfficeCode(appealVO
                .getCaseAplDstctOfcCd());
        if (appealVO.getPrevAplNum() != null && !appealVO.getPrevAplNum().equals(""))
        {
	        appealTracking.setPreviousAppealNumber(new Long(appealVO
	                .getPrevAplNum()));
        }
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoAAUOfficerNam()))
        appealTracking.setAdditionalInfoAauOfficerName(appealVO
                .getAddlInfoAAUOfficerNam());
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoMotionTyCd()))
        appealTracking.setAdditionalInfoMotionTypeCode(appealVO
                .getAddlInfoMotionTyCd());
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoFileLocnCd()))
        appealTracking.setAdditionalInfoFileLocationCode(appealVO
                .getAddlInfoFileLocnCd());
        
        if(!isNullOrEmptyField(appealVO.getAddlInfoClientRepNam()))
        appealTracking.setAdditionalInfoClientRepresentName(appealVO
                .getAddlInfoClientRepNam());
        
        if(!isNullOrEmptyField(appealVO.getDhhsDecisCd()))
        appealTracking.setDhhsDescCode(appealVO.getDhhsDecisCd());
        
        if(!isNullOrEmptyField(appealVO.getRcnsdrtnDecisCd()))
        appealTracking
                .setReconsiderationDescCode(appealVO.getRcnsdrtnDecisCd());
        
        if(!isNullOrEmptyField(appealVO.getRcnsdrtnRevwrNam()))
        appealTracking.setReconsiderationReviewerName(appealVO
                .getRcnsdrtnRevwrNam());
        
        if(!isNullOrEmptyField(appealVO.getRevwrNam()))
        appealTracking.setReviewerName(appealVO.getRevwrNam());
        appealTracking.setVersionNo(appealVO.getVersionNo());
        log.debug("Appal version no-------->" + appealTracking.getVersionNo());
        
        appealTracking
				.setAuditUserID(userID);
		appealTracking.setAuditTimeStamp(new Date());

		if (appealVO.getAddedAuditUserID() == null) {
			appealTracking.setAddedAuditUserID(userID);
		} else {
			appealTracking.setAddedAuditUserID(appealVO.getAddedAuditUserID());
		}

		if (appealVO.getAddedAuditTimeStamp() == null) {
			appealTracking.setAddedAuditTimeStamp(new Date());
		} else {
			appealTracking.setAddedAuditTimeStamp(appealVO
					.getAddedAuditTimeStamp());
		}	
		
		
		if(!isNullOrEmptyField(appealVO.getDhhsNotfyLtrSentDt()))
		appealTracking.setDhhsNotifyLtrSentDate(contactManagementHelper
				.dateConverter(appealVO.getDhhsNotfyLtrSentDt()));
		 
		if(!isNullOrEmptyField(appealVO.getRcnsdrtnSentDt()))
		appealTracking.setReconsiderationOrderDate(contactManagementHelper
				.dateConverter(appealVO.getRcnsdrtnSentDt()));
		
		
//		getCommonEntityTypeCode
        /* --------Added for defect ESPRD412572------------*/
        if (appealVO.getCommonEntityTypeCode() == null)
        {
            
            appealTracking.setCommonEntityTypeCode(commonEntityTypeCode);
                             
        }
        else
        {
            appealTracking.setCommonEntityTypeCode(appealVO.getCommonEntityTypeCode());
        
       
    }

       
        /*if (!adminHearingList.isEmpty())
        {
            log.debug("caseSK--->" + appealVO.getCaseSK());
            
          
            try
            {
                AppealDelegate appealDelegate = new AppealDelegate();
                Integer maxSeqNum = appealDelegate.getMaxCaseHearSequenceNum();
                log.debug("Max seq Num--->" + maxSeqNum);
                appealTracking.setAdminHearings(convertVoToDo(adminHearingList,
                        appealVO.getCaseSK(), appealTracking, maxSeqNum));
                log.debug("AdminHearingList from AppealTracking in VOTODO:"
                        + appealTracking.getAdminHearings().size());
            }
            catch (AppealFetchBusinessException afe)
            {
                ContactManagementHelper
                        .setErrorMessage(MaintainContactManagementUIConstants.SAVE_FAILURE);
                log.error("eror in getMaxCaseHearSequenceNum");
            }

        }*/

        return appealTracking;
    }

   

    /**
     * This method converts Appeal DO to VO
     * 
     * @param appealSet
     *            Set
     * @return List
     */

    public List convertAppealDOToVO(Set appealSet)
    {
        log.debug("starting convertAppealDOToVO-- set-->");
        List appealVOList = new ArrayList();
        AppealTracking appealTrackingDO;
        AppealVO appealVO = null;
        Iterator iterator = appealSet.iterator();
        while (iterator.hasNext())
        {
            appealTrackingDO = (AppealTracking) iterator.next();
            appealVO = convertAppealDOToVO(appealTrackingDO);
            appealVOList.add(appealVO);

        }

        return appealVOList;
    }

    /**
     * Converts the Appeal DO to VO.
     * 
     * @param appealTracking
     *            AppealTracking.
     * @return AppealVO.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public AppealVO convertAppealDOToVO(AppealTracking appealTracking)
    {
        log.debug("calling convertAppealDOToVO --AppealTrackingDO--->");
        AppealVO appealVO = new AppealVO();

        appealVO.setCaseSK(appealTracking.getCaseSK().toString());
        if (appealTracking.getAppealDate() != null)
        {
            appealVO.setAplDt(ContactManagementHelper
                    .dateConverter(appealTracking.getAppealDate()));
        }
        if (appealTracking.getCaseAppealStatusCodeDate() != null)
        {
            appealVO
                    .setCaseAplStatCdDt(ContactManagementHelper
                            .dateConverter(appealTracking
                                    .getCaseAppealStatusCodeDate()));
        }
        else if(appealTracking.getCaseAppealStatusCodeDate()==null) {   
        	System.out.println("appealTracking.getCaseAppealStatusCodeDate( is null in convertAppealDOToVO$$$$$$$$$$$");
        	//appealVO.setCaseAplStatCdDt(ContactManagementHelper.dateConverter(new Date()));        	
        }         
        if (appealTracking.getReconsiderationOrderDate() != null) {
        	
        	appealVO.setRcnsdrtnSentDt(ContactManagementHelper
                    .dateConverter(appealTracking.getReconsiderationOrderDate()));
        }
        if (appealTracking.getAdditionalInfoReqdDate() != null)
        {
            appealVO.setAddlInfoReqDt(ContactManagementHelper
                    .dateConverter(appealTracking.getAdditionalInfoReqdDate()));
        }
        if (appealTracking.getAdditionalInfoDueDate() != null)
        {
            appealVO.setAddlInfoDueDt(ContactManagementHelper
                    .dateConverter(appealTracking.getAdditionalInfoDueDate()));
        }
        if (appealTracking.getAdditionalInfoNotifyLtrSentDate() != null)
        {
            appealVO.setAddlInfoNotfyLtrSentDt(ContactManagementHelper
                    .dateConverter(appealTracking
                            .getAdditionalInfoNotifyLtrSentDate()));
        }
        if (appealTracking.getAdditionalInfoRecvd2ndReqdDate() != null)
        {
         
			appealVO.setAddlInfoRecd2NdReqDt(ContactManagementHelper
					.dateConverter(appealTracking
							.getAdditionalInfoRecvd2ndReqdDate()));
		}
        if (appealTracking.getAdditionalInfoRecvdDate() != null)
        {
            appealVO
                    .setAddlInfoRecdDt(ContactManagementHelper
                            .dateConverter(appealTracking
                                    .getAdditionalInfoRecvdDate()));
        }
        if (appealTracking.getAdditionalInfoRevisedReviewDueDate() != null)
        {
            appealVO.setAddlInfoRvsdRevwDueDt(ContactManagementHelper
                    .dateConverter(appealTracking
                            .getAdditionalInfoRevisedReviewDueDate()));
        }
        if (appealTracking.getAdditionalInfo2ndRevisedDueDate() != null)
        {
            appealVO.setAddlInfo2NdRvsdDueDt(ContactManagementHelper
                    .dateConverter(appealTracking
                            .getAdditionalInfo2ndRevisedDueDate()));
        }
        if (appealTracking.getAdditionalInfoRespReqdDueDate() != null)
        {
            appealVO.setAddlInfoRespReqDueDt(ContactManagementHelper
                    .dateConverter(appealTracking
                            .getAdditionalInfoRespReqdDueDate()));
        }
        if (appealTracking.getAdditionalInfoMotionFiledDate() != null)
        {
            appealVO.setAddlInfoFiledDt(ContactManagementHelper
                    .dateConverter(appealTracking
                            .getAdditionalInfoMotionFiledDate()));
        }
        if (appealTracking.getAssignedDate() != null)
        {
            appealVO.setAsgnDt(ContactManagementHelper
                    .dateConverter(appealTracking.getAssignedDate()));
        }
        if (appealTracking.getDhhsDescDueDate() != null)
        {
            appealVO.setDhhsDecisDueDt(ContactManagementHelper
                    .dateConverter(appealTracking.getDhhsDescDueDate()));
        }
        if (appealTracking.getDhhsNotifyLtrSentDate() != null)
        {
          
            appealVO.setDhhsNotfyLtrSentDt(ContactManagementHelper
                    .dateConverter(appealTracking.getDhhsNotifyLtrSentDate()));
        }
        if (appealTracking.getDhhsSentDate() != null)
        {
            appealVO.setDhhsSentDt(ContactManagementHelper
                    .dateConverter(appealTracking.getDhhsSentDate()));
        }
        if (appealTracking.getInformalReviewAckSentDate() != null)
        {
            appealVO.setInfrmlRevwAckSentDt(ContactManagementHelper
                    .dateConverter(appealTracking
                            .getInformalReviewAckSentDate()));
        }
        if (appealTracking.getInformalReviewDueDate() != null)
        {
            appealVO.setInfrmlRevwDueDt(ContactManagementHelper
                    .dateConverter(appealTracking.getInformalReviewDueDate()));
        }
        if(appealTracking.getInformalReviewRequiredDate() != null)
        {
            appealVO.setInfrmlRevwReqDt(ContactManagementHelper
                    .dateConverter(appealTracking
                            .getInformalReviewRequiredDate()));
        }
        if (appealTracking.getInformalReviewSentDate() != null)
        {
            appealVO.setInfrmlRevwSentDt(ContactManagementHelper
                    .dateConverter(appealTracking.getInformalReviewSentDate()));
        }
        if (appealTracking.getReconsiderationDescDate() != null)
        {
            appealVO
                    .setRcnsdrtnDecisDt(ContactManagementHelper
                            .dateConverter(appealTracking
                                    .getReconsiderationDescDate()));
        }
        if (appealTracking.getReconsiderationNotifyLtrSentDate() != null)
        {
            appealVO.setRcnsdrtnNotfyLtrSentDt(ContactManagementHelper
                    .dateConverter(appealTracking
                        .getReconsiderationNotifyLtrSentDate()));
        }
        if(appealTracking.getReconsiderationReturnDate() != null)
        {
            appealVO.setRcnsdrtnRtrnDt(ContactManagementHelper
                   .dateConverter(appealTracking.getReconsiderationReturnDate()));
        }
        
        appealVO.setCaseAplTyCd(appealTracking.getCaseAppealTypeCode());
        if (appealTracking.getTransactionControlNumber() != null)
        {
            appealVO.setTcnNum(appealTracking.getTransactionControlNumber());
        }
       
        appealVO.setAplTyCd(appealTracking.getAppealTypeCode());
        if (appealTracking.getCaseAppealReasonCode() != null)
        {
            appealVO.setCaseAplContinuanceRsnCd(appealTracking
                    .getCaseAppealReasonCode());
            
        }
        if(!isNullOrEmptyField(appealTracking.getCaseAppealDisputeCode()))
        appealVO.setCaseAplDispCd(appealTracking.getCaseAppealDisputeCode());
        
        if(!isNullOrEmptyField(appealTracking.getCaseAppealResultsCode()))
        appealVO.setCaseAplRsltsCd(appealTracking.getCaseAppealResultsCode());
        
        if (!isNullOrEmptyField(appealTracking.getCaseAppealStatusCode())) {
			appealVO
					.setPreviousStatus(appealTracking.getCaseAppealStatusCode());
			appealVO.setCaseAplStatCd(appealTracking.getCaseAppealStatusCode());
		}
        
        if(!isNullOrEmptyField(appealTracking.getTransactionControlNumber()))
        appealVO.setTcnNum(appealTracking.getTransactionControlNumber());
        
        if(!isNullOrEmptyField(appealTracking.getCaseAppealDistrictOfficeCode()))
        appealVO.setCaseAplDstctOfcCd(appealTracking
                .getCaseAppealDistrictOfficeCode());
        if (appealTracking.getPreviousAppealNumber() != null)
        {
	        appealVO.setPrevAplNum(appealTracking.getPreviousAppealNumber()
	                .toString());
        }
        if(!isNullOrEmptyField(appealTracking.getAdditionalInfoAauOfficerName()))
        appealVO.setAddlInfoAAUOfficerNam(appealTracking
                .getAdditionalInfoAauOfficerName());
        
        if(!isNullOrEmptyField(appealTracking.getAdditionalInfoMotionTypeCode()))
        appealVO.setAddlInfoMotionTyCd(appealTracking
                .getAdditionalInfoMotionTypeCode());
        if(!isNullOrEmptyField(appealTracking.getAdditionalInfoFileLocationCode()))
        appealVO.setAddlInfoFileLocnCd(appealTracking
                .getAdditionalInfoFileLocationCode());
        if(!isNullOrEmptyField(appealTracking.getAdditionalInfoClientRepresentName()))
        appealVO.setAddlInfoClientRepNam(appealTracking
                .getAdditionalInfoClientRepresentName());
        if(!isNullOrEmptyField(appealTracking.getDhhsDescCode()))
        appealVO.setDhhsDecisCd(appealTracking.getDhhsDescCode());
        
        if(!isNullOrEmptyField(appealTracking.getReconsiderationDescCode()))
        appealVO
                .setRcnsdrtnDecisCd(appealTracking.getReconsiderationDescCode());
        
        if(!isNullOrEmptyField(appealTracking.getReconsiderationReviewerName()))
        appealVO.setRcnsdrtnRevwrNam(appealTracking
                .getReconsiderationReviewerName());
        if(!isNullOrEmptyField(appealTracking.getReviewerName()))
        appealVO.setRevwrNam(appealTracking.getReviewerName());
        appealVO.setVersionNo(appealTracking.getVersionNo());
        log.debug("setting version in ApealDO to VO------>"
                + appealVO.getVersionNo());
        log.debug("u r here3");
     
        if(appealTracking.getAddedAuditTimeStamp() != null){
        appealVO.setAddedAuditTimeStamp(appealTracking.getAddedAuditTimeStamp());
        }
        if(appealTracking.getAddedAuditUserID() != null){
        appealVO.setAddedAuditUserID(appealTracking.getAddedAuditUserID());
        }
        if(appealTracking.getAuditTimeStamp() != null){
        appealVO.setAuditTimeStamp(appealTracking.getAuditTimeStamp());
        }
        if(appealTracking.getAuditUserID() != null){
        appealVO.setAuditUserID(appealTracking.getAuditUserID());
        }        
       
        
        // Added for the defect id ESPRD00336213
        if (appealTracking.getServiceAuthorization() != null) {
        	appealVO.setAuthID(appealTracking.getServiceAuthorization().getServiceAuthorizationID());
        }
        // Ends
        if (appealTracking.getAdminHearings() != null)
        {
            
            appealVO.setAdminHearingList(convertDoToVo(appealTracking
                    .getAdminHearings()));         
        }
        //getCommonEntityTypeCode
        /* --------Added for defect ESPRD412572------------*/
        
            appealVO.setCommonEntityTypeCode(appealTracking
                    .getCommonEntityTypeCode());
            // Added for CR ESPRD00373565 AuditLog starts
            createVOAuditKeysList(appealTracking,appealVO);
            if(appealTracking.getServiceAuthorization()!=null){
            	createVOAuditKeysList(appealTracking.getServiceAuthorization(),appealVO);	
            }
            
            //CR ESPRD00373565 AuditLog ends
           // doAuditKeyListOperationForProviderAppeal(appealVO);//code available in controller bean CR_ESPRD00373565_ProviderAppeals_30JUL2010
            
        return appealVO;
    }
    
    //CR_ESPRD00373565_ProviderAppeals_30JUL2010
    static List auditableAppealDetails;
    static List auditableAdministrativeHearingDetails;
    
    private void doAuditKeyListOperationForProviderAppeal(AppealVO appealVO){
    	log.debug(">>---------> Inside AppealVO appealVO doAuditKeyListOperation:");    			
	    	if(auditableAppealDetails==null || auditableAppealDetails.isEmpty())
	    	{
	    		getAuditableAppealDetails();
	    	}
			if(appealVO.getAuditKeyList()!=null && !appealVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableAppealDetails,appealVO);
			}  
	  }
    private void doAuditKeyListOperationForAdministrativeHearing(AdminHearingVO adminHearingVO){
    	log.debug(">>---------> Inside AdminHearingVO adminHearingVO doAuditKeyListOperation:");    			
	    	if(auditableAdministrativeHearingDetails==null || auditableAdministrativeHearingDetails.isEmpty())
	    	{
	    		getAuditableAdministrativeHearingDetails();
	    	}
			if(adminHearingVO.getAuditKeyList()!=null && !adminHearingVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableAdministrativeHearingDetails,adminHearingVO);
			}  
	  }
    
    public List getAuditableAdministrativeHearingDetails() {
    	auditableAdministrativeHearingDetails = new ArrayList();
    	auditableAdministrativeHearingDetails.add(createAuditableField("Administrative Hearing Date","hearDate"));	    	
    	auditableAdministrativeHearingDetails.add(createAuditableField("Hearing Results","hearResultsCode"));    	
    	auditableAdministrativeHearingDetails.add(createAuditableField("Hearing Status","hearStatusCode"));    	
    	auditableAdministrativeHearingDetails.add(createAuditableField("Docket Number","docketNumber"));    	
    	auditableAdministrativeHearingDetails.add(createAuditableField("Hearing Officer Name","hearOfficerName"));    	
    	auditableAdministrativeHearingDetails.add(createAuditableField("Hearing Citation","hearCitationText"));    	
    	
		return auditableAdministrativeHearingDetails;
	}
    public List getAuditableAppealDetails() {
    	auditableAppealDetails = new ArrayList();
    	auditableAppealDetails.add(createAuditableField("Program Type","caseAppealTypeCode"));	    	
    	auditableAppealDetails.add(createAuditableField("Appeal Type","appealTypeCode"));    	
    	auditableAppealDetails.add(createAuditableField("Reviewer Name","reviewerName"));    	
    	auditableAppealDetails.add(createAuditableField("Assigned Date","assignedDate"));    	
    	auditableAppealDetails.add(createAuditableField("Previous Appeal Number","previousAppealNumber"));    	
    	auditableAppealDetails.add(createAuditableField("Appeal Date","appealDate"));    	
    	auditableAppealDetails.add(createAuditableField("Appeal Results","caseAppealResultsCode"));    	
    	auditableAppealDetails.add(createAuditableField("Appeal Status","caseAppealStatusCode"));    	
    	auditableAppealDetails.add(createAuditableField("Appeal Status Update Date","caseAppealStatusCodeDate"));    	
    	auditableAppealDetails.add(createAuditableField("District Office","caseAppealDistrictOfficeCode"));    	
    	auditableAppealDetails.add(createAuditableField("Claim TCN","transactionControlNumber"));    	
    	auditableAppealDetails.add(createAuditableField("Requested Date","additionalInfoReqdDate"));    	
    	auditableAppealDetails.add(createAuditableField("Due Date","additionalInfoDueDate"));    	
    	auditableAppealDetails.add(createAuditableField("Received Date","additionalInfoRecvdDate"));    	
    	auditableAppealDetails.add(createAuditableField("Revised Review Due","additionalInfoRevisedReviewDueDate"));    	
    	auditableAppealDetails.add(createAuditableField("Notification Letter Sent Date","additionalInfoNotifyLtrSentDate"));    	
    	auditableAppealDetails.add(createAuditableField("Received 2nd Request Date","additionalInfoRecvd2ndReqdDate"));    	
    	auditableAppealDetails.add(createAuditableField("2nd Revised Due Date","additionalInfo2ndRevisedDueDate"));    	
    	auditableAppealDetails.add(createAuditableField("AAU Officer","additionalInfoAauOfficerName"));
    	auditableAppealDetails.add(createAuditableField("Response Requested By","additionalInfoRespReqdDueDate"));    	
    	auditableAppealDetails.add(createAuditableField("Motion Type","additionalInfoMotionTypeCode"));    	
    	auditableAppealDetails.add(createAuditableField("Date Motion Filed","additionalInfoMotionFiledDate"));    	
    	auditableAppealDetails.add(createAuditableField("Case File Location","additionalInfoFileLocationCode"));    	
    	auditableAppealDetails.add(createAuditableField("Client Representative","additionalInfoClientRepresentName"));    	
    	auditableAppealDetails.add(createAuditableField("Order Date","reconsiderationOrderDate"));    	
    	auditableAppealDetails.add(createAuditableField("Returned Date","reconsiderationReturnDate"));
    	auditableAppealDetails.add(createAuditableField("Reviewer Name","reconsiderationReviewerName"));
    	auditableAppealDetails.add(createAuditableField("Decision","reconsiderationDescCode"));    	
    	auditableAppealDetails.add(createAuditableField("Decision Date","reconsiderationDescDate"));
    	auditableAppealDetails.add(createAuditableField("Notification Letter Sent Date","reconsiderationNotifyLtrSentDate"));    	
    	auditableAppealDetails.add(createAuditableField("Requested Date","informalReviewRequiredDate"));
    	auditableAppealDetails.add(createAuditableField("Acknowledgement Sent Date","informalReviewAckSentDate"));
    	auditableAppealDetails.add(createAuditableField("Sent for Review Date","informalReviewSentDate"));
    	auditableAppealDetails.add(createAuditableField("Due Date","informalReviewDueDate"));    	
    	auditableAppealDetails.add(createAuditableField("Sent to DHHS Date","dhhsSentDate"));
    	auditableAppealDetails.add(createAuditableField("DHHS Decision Due","dhhsDescDueDate"));
    	auditableAppealDetails.add(createAuditableField("DHHS Decision","dhhsDescCode"));
    	auditableAppealDetails.add(createAuditableField("Notification Letter Sent Date","dhhsNotifyLtrSentDate"));
    	
    	
		
		return auditableAppealDetails;
	}
    
	 private AuditableField createAuditableField(String fieldName,String domainAttributeName){
	 	AuditableField auditableField = new AuditableField();
	 	auditableField.setFieldName(fieldName);
	 	auditableField.setDomainAttributeName(domainAttributeName);
	 	return auditableField;
	 }
    //EOF CR_ESPRD00373565_ProviderAppeals_30JUL2010
    
    // Added for CR ESPRD00373565 AuditLog starts
    private void createVOAuditKeysList(EnterpriseBaseDomain enterpriseBaseDomain,AuditaleEnterpriseBaseVO  auditaleEnterpriseBaseVO){
    	
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
    	       	
    	       	System.err.println("======LineItemAuditsDelegate====resultList====Size======="+resultList.size());
    	       	auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);
    	       }
    	} catch (LineItemAuditsBusinessException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
          
     
    }
    // CR ESPRD00373565 AuditLog ends
    /**
     * This method converts DO to VO object
     * 
     * @param setObject
     *            Set
     * @return List
     */
    private List convertDoToVo(Set setObject)
    {
        List listObject = null;
        EnterpriseBaseDomain enterpriseBaseDomain;
        try
        {
            listObject = new ArrayList();
            Iterator itr = setObject.iterator();
            AdminHearingVO adminHearingVO=null;
            while (itr.hasNext())
            {
                enterpriseBaseDomain = new EnterpriseBaseDomain();
                enterpriseBaseDomain = (EnterpriseBaseDomain) itr.next();
                log.debug("calling convertAdminHearing-------->");
                //modified 16_7_09
                adminHearingVO = convertAdminHearing((AdministrativeHearing) enterpriseBaseDomain);
                if(adminHearingVO!=null){
                	try{
                	createVOAuditKeysList(enterpriseBaseDomain,adminHearingVO);
                	doAuditKeyListOperationForAdministrativeHearing(adminHearingVO);
                	}catch(Exception e){
                		log.error("Exception while preapare auditkeylist operations for AdministrativeHearing",e);
                	}
                listObject.add(adminHearingVO);
                }
                //EOF modification

            }
        }
        catch (Exception e)
        {
            log.error("error in convertDoToVo");
            e.printStackTrace();
        }
        return listObject;
    }

    /**
     * This method converts VO to DO object
     * 
     * @param listObject
     *            List
     * @param caseSK
     *            String
     * @param appealTracking
     *            AppealTracking
     * @return Set
     */
   /* private Set convertVoToDo(List listObject, String caseSK,
            AppealTracking appealTracking, Integer maxSeqNum)*/
    public Set convertVoToDo(List listObject, String caseSK,
            AppealTracking appealTracking, Integer maxSeqNum)
    {
        Set setObject = new HashSet();
        try
        {
            Iterator itr = listObject.iterator();
            int seqNum = maxSeqNum.intValue();
            while (itr.hasNext())
            {
                EnterpriseBaseVO enterpriseBaseVO = new EnterpriseBaseVO();
                enterpriseBaseVO = (EnterpriseBaseVO) itr.next();
                AdminHearingVO adminHearingVO = (AdminHearingVO) enterpriseBaseVO;
                if (adminHearingVO.getHearingSeqNum() == null)
                {
                    seqNum = seqNum + 1;
                    log.debug("New Record--->" + seqNum);
                }

                setObject.add(convertAdminHearing(
                        (AdminHearingVO) enterpriseBaseVO, caseSK,
                        appealTracking, seqNum));
                log
                .debug("setObject Size in ConvertVOTODO:"
                        + setObject.size());
            }

        }
        catch (Exception e)
        {
            log.error("error in convertVoToDo");
        }
        return setObject;
    }

    /**
     * This method converts Admin Hearing VO to DO
     * 
     * @param adminHearingVO
     *            AdminHearingVO
     * @param caseSK
     *            String
     * @param appealTracking
     *            AppealTracking
     * @return AdministrativeHearing
     */
    private AdministrativeHearing convertAdminHearing(
            AdminHearingVO adminHearingVO, String caseSK,
            AppealTracking appealTracking, int seqNum)
    {
        AdministrativeHearing administrativeHearing = new AdministrativeHearing();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();

        if (adminHearingVO.getHearingSeqNum() == null)
        {
            administrativeHearing.setHearSeqNumber(Integer.valueOf(seqNum));    /*FIND BUGS_FIX*/
            
        }
        else
        {
            administrativeHearing.setHearSeqNumber(adminHearingVO
                    .getHearingSeqNum());
        }
        
        administrativeHearing.setCaseSK(new Long(caseSK));

      
        log.debug("ADMIN SEQ NUM IN VO TO DO -->"
                + administrativeHearing.getHearSeqNumber());
        //Added for AuditUpdate-FIX
        if(!isNullOrEmptyField(adminHearingVO.getDocketNumber())){
        administrativeHearing.setDocketNumber(adminHearingVO.getDocketNumber());
        }
        //Added for AuditUpdate-FIX
        if(!isNullOrEmptyField(adminHearingVO.getHearingCitation())){
        administrativeHearing.setHearCitationText(adminHearingVO
                .getHearingCitation());
        }
        if (adminHearingVO.getStrAdminHearingDate() != null)
        {
            administrativeHearing.setHearDate(contactManagementHelper
                    .dateConverter(adminHearingVO.getStrAdminHearingDate()));
        }
        //Added for AuditUpdate-FIX
        if(!isNullOrEmptyField(adminHearingVO.getHearingOfficerName())){
        administrativeHearing.setHearOfficerName(adminHearingVO
                .getHearingOfficerName());
        }
        //Added for AuditUpdate-FIX
        if(!isNullOrEmptyField(adminHearingVO.getHearingResults())){
        administrativeHearing.setHearResultsCode(adminHearingVO
                .getHearingResults());
        }
        //Added for AuditUpdate-FIX
        if(!isNullOrEmptyField(adminHearingVO.getHearingStatus())){
        administrativeHearing.setHearStatusCode(adminHearingVO
                .getHearingStatus());
        }
        administrativeHearing.setVersionNo(adminHearingVO.getVersionNo());
        log.debug("Version From AdminHearing in VOTODO:"
                + administrativeHearing.getVersionNo());

        appealTracking.setCaseSK(new Long(caseSK));
        administrativeHearing.setAppealRef(appealTracking);

        
        administrativeHearing
                .setAuditUserID(userID);
        administrativeHearing.setAuditTimeStamp(new Date());
        
        if (adminHearingVO.getAddedAuditUserID() == null)
        {
        	administrativeHearing.setAddedAuditUserID(userID);
        }
        else
        {
        	administrativeHearing.setAddedAuditUserID(adminHearingVO.getAddedAuditUserID());
        }
        
        if (adminHearingVO.getAddedAuditTimeStamp() == null)
        {
        	administrativeHearing.setAddedAuditTimeStamp(new Date());
        }
        else
        {
        	administrativeHearing.setAddedAuditTimeStamp(adminHearingVO.getAddedAuditTimeStamp());
        }        
       

      //Modified for defect ESPRD00532573 starts
        /*if (adminHearingVO != null
                && adminHearingVO.getAdminNotesetVO() != null)
        {
            NoteSetVO adminHearingNoteSetVO = adminHearingVO
                    .getAdminNotesetVO();
            ContactHelper contactHelper = new ContactHelper();
            NoteSet adminHearingNoteSet = contactHelper
                    .convertNoteSetVOToDomain(adminHearingNoteSetVO);
            administrativeHearing.setNoteSet(adminHearingNoteSet);

            if (administrativeHearing.getNoteSet() != null
                    && administrativeHearing.getNoteSet().getNotes() != null)
            {
                log.debug("administrativeHearing NoteSet List Size ::"
                        + administrativeHearing.getNoteSet().getNotes().size());
            }
        }*/
       /* System.err.println("============adminHearingVO.getAdminNotesetVO()"+adminHearingVO.getAdminNotesetVO());
        commonEntityDataBean = (CommonEntityDataBean) getDataBean(COMMON_ENTITY_DATA_BEAN);
        List notesVOList = commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO().getNotesList();
   	 	FIND BUGS_FIX
        if (notesVOList != null
	            && notesVOList.size() > 0)
	    {
        	System.err.println("===========notesVOList"+notesVOList.size());
        	System.err.println("++Notes are available");
	        addNoteSet(administrativeHearing);
	    }*/
        //defect ESPRD00532573 ends
        
        //for ESPRD00782216 defect.
        //note section 
        List noteList=adminHearingVO.getNoteList();
        if(noteList!=null && !noteList.isEmpty()){
        	addNoteSet(adminHearingVO,administrativeHearing);
        }
        return administrativeHearing;
    }
    
    /**
     * This method is used to add Note Set to the AdministrativeHearing Domain object.
     *
     * @param correspondence :
     *            Correspondence object
     */
    protected void addNoteSet(AdministrativeHearing administrativeHearing)
    {
    	System.err.println("++addNoteSet");
        
        CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
        try
        {
            NoteSet noteSet = commonEntityValidator.getNoteSetDO();
            if (noteSet != null)
            {
                System.err.println("++getNoteSetDO not Null-->" + noteSet);
                noteSet.setNoteSourceName("G_CASE_HEAR_TB");
                administrativeHearing.setNoteSet(noteSet);
                System.err.println("++setting noteset source name in add noteset---> "
                        + administrativeHearing.getNoteSet().getNoteSourceName());
            }
        }
        catch (CommonEntityUIException commonEntityUIException)
        {
            log.error(commonEntityUIException.getMessage(),
                    commonEntityUIException);
            commonEntityUIException.printStackTrace();
        }
       
    }

    /**
     * This method converts AdminHearing DO to VO
     * 
     * @param adminHearingDO
     *            AdministrativeHearing
     * @return AdminHearingVO
     */
    private AdminHearingVO convertAdminHearing(
            AdministrativeHearing adminHearingDO)
    {
        AdminHearingVO adminHearingVO = new AdminHearingVO();
        log.debug("converting admin hearing Do to VO-------->");

        adminHearingVO.setDocketNumber(adminHearingDO.getDocketNumber());
        adminHearingVO.setHearingCitation(adminHearingDO.getHearCitationText());
        if (adminHearingDO.getHearDate() != null)
        {
            adminHearingVO.setStrAdminHearingDate(ContactManagementHelper
                    .dateConverter(adminHearingDO.getHearDate()));
        }
        
        adminHearingVO.setHearingOfficerName(adminHearingDO
                .getHearOfficerName());
        
        adminHearingVO.setHearingResults(adminHearingDO.getHearResultsCode());
        
        adminHearingVO.setHearingStatus(adminHearingDO.getHearStatusCode());
		//ESPRD00521844_UC-PGM-CRM-065_09sep2010

        adminHearingVO.setHearingResultsDesc(ContactManagementHelper.setShortDescription(FunctionalAreaConstants.GENERAL,
                ReferenceServiceDataConstants.G_CASE_HEAR_RSLTS_CD,
                adminHearingVO.getHearingResults()));
		
		adminHearingVO.setHearingStatusDesc(ContactManagementHelper.setShortDescription(FunctionalAreaConstants.GENERAL,
				ReferenceServiceDataConstants.G_CASE_HEAR_STAT_CD,
                adminHearingVO.getHearingStatus()));

		//EOF ESPRD00521844_UC-PGM-CRM-065_09sep2010
        adminHearingVO.setHearingSeqNum(adminHearingDO.getHearSeqNumber());
        log.debug("Hearing Seq Num in admin DO to VO--->"
                + adminHearingVO.getHearingSeqNum());
        adminHearingVO.setVersionNo(adminHearingDO.getVersionNo());
        
      
        
        if(adminHearingDO.getAddedAuditTimeStamp() != null){
        adminHearingVO.setAddedAuditTimeStamp(adminHearingDO
                .getAddedAuditTimeStamp());
        }
        if(adminHearingDO.getAddedAuditUserID() != null){
        adminHearingVO
                .setAddedAuditUserID(adminHearingDO.getAddedAuditUserID());
        }
        log.debug("Audit useriD from adminHearingVo:"
                + adminHearingVO.getAddedAuditUserID());
        if(adminHearingDO.getAuditTimeStamp() != null) {
        adminHearingVO.setAuditTimeStamp(adminHearingDO.getAuditTimeStamp());
        }
        
        if(adminHearingDO.getAuditUserID() != null ){        	
        adminHearingVO.setAuditUserID(adminHearingDO.getAuditUserID());
        }
             
        log.debug("converter Do to VO--###---DocketNumber-->"
                + adminHearingVO.getDocketNumber());
        log.debug("converter Do to VO--###---HearingCitation-->"
                + adminHearingVO.getHearingCitation());
        log.debug("converter Do to VO--###---HearStatusCode-->"
                + adminHearingVO.getHearingStatus());

        if (adminHearingDO.getNoteSet() != null)
        {
            if (adminHearingDO.getNoteSet().getNotes() != null)
            {
                ContactHelper contactHelper = new ContactHelper();
                NoteSetVO noteSetVO = contactHelper
                        .convertNoteSetDomainToVO(adminHearingDO.getNoteSet());
                adminHearingVO.setAdminNotesetVO(noteSetVO);
                if (noteSetVO != null) {
					adminHearingVO.setNoteList(noteSetVO.getNotesList());
					System.out.println("setting note list to adminhearing vo $$$$$$$"+noteSetVO.getNotesList().size());
				}
                
            }
        }
        
        String emptyString="";
        //added and modified 
        if((adminHearingVO.getStrAdminHearingDate()==null ||adminHearingVO.getStrAdminHearingDate().equals(emptyString))
        	&& (adminHearingVO.getHearingResults()==null || adminHearingVO.getHearingResults().equals(emptyString))
			&& (adminHearingVO.getHearingStatus()==null || adminHearingVO.getHearingStatus().equals(emptyString))
			&& (adminHearingVO.getHearingOfficerName()==null || adminHearingVO.getHearingOfficerName().equals(emptyString))
			&&(adminHearingVO.getDocketNumber()==null || adminHearingVO.getDocketNumber().equals(emptyString))){
        	return null;
        }else
        	return adminHearingVO;
       
    }
    

    
    public AppealDataBean getAppealDataBean() {
		log.debug("getAppealDataBean############");

		FacesContext fc = FacesContext.getCurrentInstance();

		AppealDataBean appealDataBean = (AppealDataBean) fc
				.getApplication()
				.createValueBinding(
						ContactManagementConstants.BINDING_BEGIN_SEPARATOR
								+ ContactManagementConstants.APPEAL_DATA_BEAN
								+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc);

		return appealDataBean;
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
	
	 /**
     * This method is used to add Note Set to the AdministrativeHearing Domain object.
     *
     * @param adminHearingVO :
     *            AdminHearingVO object
     * @param administrativeHearing :
     *            AdministrativeHearing object
     */
    protected void addNoteSet(AdminHearingVO adminHearingVO,
			AdministrativeHearing administrativeHearing) {
		log.debug("++addNoteSet");
		if (adminHearingVO != null) {
			CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
			try {
				if(adminHearingVO.getAdminNotesetVO()==null){
					log.debug("adminHearingVO.getAdminNotesetVO()==null");
					adminHearingVO.setAdminNotesetVO(new NoteSetVO());
				}
				adminHearingVO.getAdminNotesetVO().setNotesList(
						(ArrayList) adminHearingVO.getNoteList());
				/*NoteSet noteSet = getNoteSetDO(adminHearingVO
						.getAdminNotesetVO());*/
				NoteSet noteSet = convertNoteSetVOToDomain(adminHearingVO
						.getAdminNotesetVO());
				if (noteSet != null) {
					log.debug("++getNoteSetDO not Null-->" + noteSet);
					noteSet.setNoteSourceName("G_CASE_HEAR_TB");
					administrativeHearing.setNoteSet(noteSet);
					log.debug("++setting noteset source name in add noteset---> "
									+ administrativeHearing.getNoteSet()
											.getNoteSourceName());
				}
			} catch (Exception exception) {
				log.error(exception.getMessage(),
						exception);
				exception.printStackTrace();
			}
		}
	}
    
   /* *//**
	 * To get NoteSet DO.
     * @param noteSetVO 
	 * 
	 * @return NoteSet
	 * @throws CommonEntityUIException
	 *             CommonEntityUIException is thrown.
	 *//*
	public NoteSet getNoteSetDO(NoteSetVO noteSetVO) throws CommonEntityUIException {
		NoteSet noteSet = null;
		boolean validNotes = true;
		commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();
		if (commonEntityDataBean != null) {
			CommonEntityVO commonEnitityVO = commonEntityDataBean
					.getCommonEntityVO();
			System.out.println("commonEntityDataBean.isSaveNotesChk()"
					+ commonEntityDataBean.isSaveNotesChk());

			if (commonEntityDataBean.isDetailSaveNotesChk()) {
				DetailedNotesControllerBean detailNotesControllerBean=new DetailedNotesControllerBean();
				validNotes = detailNotesControllerBean.validateNotes(commonEntityDataBean
		                .getCommonEntityVO().getDetailCommonNotesVO());
				if (!validNotes) {
					throw new CommonEntityUIException("error",
							"Requried values for CommonEntites Missing");
				} else {
					detailNotesControllerBean.saveNotes();
				}
			}
			
			noteSet = convertNoteSetVOToDomain(noteSetVO);
		}
		return noteSet;
	}*/

	 /**
     * This method converts the value object to domain object. It takes the
     * instances of Object to *be converted as input and returns the
     * corresponding doamin object with values from Object *being set to the
     * domain.
     * 
     * @param noteSetVOObj
     *            The noteSetVOObj holds NoteSet DO.
     * @return NoteSet returns NoteSet.
     */
    public NoteSet convertNoteSetVOToDomain(NoteSetVO noteSetVOObj)
    {
        NoteSet noteSetObj = null;

        if (noteSetVOObj != null)
        {

        	ContactHelper contactHelper = new ContactHelper();
        	if(isNullOrEmptyField(userID)){
        		userID=contactHelper.getUserID();
        	}
        	//String userId=contactHelper.getUserID();
            List notesVOList = noteSetVOObj.getNotesList();
            if (notesVOList != null && !(notesVOList.isEmpty()))
            {
            	log.debug("++notesVOList size=="+notesVOList.size());
                noteSetObj = new NoteSet();
                noteSetObj.setNoteSetSK(noteSetVOObj.getNoteSetSK());
                noteSetObj.setNoteSourceName(noteSetVOObj.getNoteSourceName());

                int listSize = notesVOList.size();
                for (int cnt = 0; cnt < listSize; cnt++)
                {
                    CommonNotesVO commonNotesVO = (CommonNotesVO) notesVOList
                            .get(cnt);

                    noteSetObj
                            .addNote(contactHelper.convertCommonNoteVOToDomain(commonNotesVO));
                }
                log.debug("noteSetVOObj.getAddedAuditTimeStamp() $$$$$$$$"+noteSetVOObj.getAddedAuditTimeStamp());
                if (noteSetVOObj.getAddedAuditTimeStamp() == null)
                {
                    noteSetObj.setAddedAuditTimeStamp(new Date());
                }
                else
                {
                    noteSetObj.setAddedAuditTimeStamp(noteSetVOObj
                            .getAddedAuditTimeStamp());
                }

                if (noteSetVOObj.getAddedAuditUserID() == null
                        || noteSetVOObj.getAddedAuditUserID().trim().length() == 0)
                {
                    noteSetObj.setAddedAuditUserID(userID);
                }
                else
                {
                    noteSetObj.setAddedAuditUserID(noteSetVOObj
                            .getAddedAuditUserID());
                }
                if (noteSetVOObj.getAuditTimeStamp() == null)
                {
                    noteSetObj.setAuditTimeStamp(new Timestamp(0));
                }
                else
                {
                    noteSetObj.setAuditTimeStamp(noteSetVOObj
                            .getAuditTimeStamp());
                }
                if (noteSetVOObj.getAuditUserID() == null)
                {
                    noteSetObj.setAuditUserID(userID);
                }
                else
                {
                    noteSetObj.setAuditUserID(noteSetVOObj.getAuditUserID());
                }
                noteSetObj.setVersionNo(noteSetVOObj.getVersionNo());
            }

        }
        return noteSetObj;
    }
}
