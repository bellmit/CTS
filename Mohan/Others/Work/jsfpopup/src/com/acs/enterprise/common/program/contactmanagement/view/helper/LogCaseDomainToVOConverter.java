/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.base.common.domain.Attachment;
import com.acs.enterprise.common.base.common.domain.Name;
import com.acs.enterprise.common.cots.edms.util.EDMSURLGeneratorImpl;
import com.acs.enterprise.common.cots.edms.util.exception.EDMSBaseException;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;

import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.Address;
import com.acs.enterprise.common.program.commonentities.common.domain.AddressUsage;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;
import com.acs.enterprise.common.program.commonentities.common.domain.EAddressUsage;
import com.acs.enterprise.common.program.commonentities.common.domain.Phone;
import com.acs.enterprise.common.program.commonentities.common.domain.SpecificEntity;
import com.acs.enterprise.common.program.commonentities.view.vo.PhoneVO;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.ActCMCaseEvent;
import com.acs.enterprise.common.program.contactmanagement.common.domain.ActCMCaseStep;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Alert;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseARS;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseAttachCrossReference;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseBCCP;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseCorrespondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseDDU;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseEventAppointment;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseLink;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRecord;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeEvent;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseTypeStep;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldValue;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CMLogCaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CaseSearchControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CaseSearchDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.SearchCorrespondenceDataBean;
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
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseStepsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeARSVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeAcuityRateSettingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeAppealRequestVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeBCCPVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeCreditBalanceVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeDDUVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeFCRVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeFPPRRVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeFacilityCensusSubmissionVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeFieldAuditVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeHomeOfficeCostReportingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeNFQAVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeNewARSFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeNonARSFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.caseTypeQuarterlyMQIPReturnsVO;
import com.acs.enterprise.common.util.helper.intf.URLGenerator;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
//import com.acs.enterprise.mmis.member.common.application.exception.MemberBusinessException;
import com.acs.enterprise.mmis.member.common.domain.AlternateMemberID;
import com.acs.enterprise.mmis.member.common.domain.CaseOwner;
import com.acs.enterprise.mmis.member.common.domain.DemographicInformation;
import com.acs.enterprise.mmis.member.common.domain.EligibilitySpan;
import com.acs.enterprise.mmis.member.common.domain.Member;
import com.acs.enterprise.mmis.member.common.domain.PreviousName;
import com.acs.enterprise.mmis.member.common.vo.MemberBasicInfo;
//import com.acs.enterprise.mmis.member.common.vo.MemberInformationRequestVO;
//import com.acs.enterprise.mmis.member.information.common.delegate.MemberInformationDelegate;
//import com.acs.enterprise.mmis.operations.tpladministration.application.exception.TPLCarrierBusinessException;
//import com.acs.enterprise.mmis.operations.tpladministration.common.delegate.TPLCarrierDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLCarrier;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLPolicyHolder;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLPolicyHolderDetailsVO;
//import com.acs.enterprise.mmis.provider.common.delegate.ProviderInformationDelegate;
import com.acs.enterprise.mmis.provider.common.domain.AlternateIdInfo;
import com.acs.enterprise.mmis.provider.common.domain.BoardCertifiedSpeciality;
import com.acs.enterprise.mmis.provider.common.domain.EnrollmentStatus;
import com.acs.enterprise.mmis.provider.common.domain.Provider;
import com.acs.enterprise.mmis.provider.common.domain.ProviderType;
import com.acs.enterprise.mmis.provider.common.domain.TradingPartner;
import com.acs.enterprise.common.program.contactmanagement.common.vo.ContactManagementProviderSearchVO;
//import com.acs.enterprise.mmis.provider.common.vo.ProviderInformationRequestVO;

/**
 * This class is used to convert all Domain Objects into Value objects of Log
 * Case.
 * 
 * @author Wipro
 */
public class LogCaseDomainToVOConverter
{
   
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(LogCaseDomainToVOConverter.class);
  

   
	
	// Moved to ContactManagementConstants.java
    // public static final String CMLOGCASE_DATA_BEAN = "logCaseDataBean";
    
    private CMLogCaseDataBean logCaseDataBean=(CMLogCaseDataBean)getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
	

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
     * This method is used to convert the MemberDO to vo
     * 
     * @param memberDO
     *            holds the Member Domain Object
     * @return CaseRegardingMemberVO it returns the CaseRegardingMemberVO object
     */
    public CaseRegardingMemberVO convertMemberDOToVo(Member memberDO)
    {
        CaseRegardingMemberVO regardingMemberVO = new CaseRegardingMemberVO();

        regardingMemberVO
                .setEntityType(ContactManagementConstants.ENTITY_TYPE_MEM);
    /*    String entityTypeDesc = ContactManagementHelper.setLongDescription(
                FunctionalAreaConstants.CONTACT_MGMT,
                ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
                ContactManagementConstants.ENTITY_TYPE_MEM);
        if (!entityTypeDesc.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM))
        {
            entityTypeDesc = ContactManagementConstants.ENTITY_TYPE_MEM
                    + ContactManagementConstants.HYPEN + entityTypeDesc;
        }   
        regardingMemberVO.setEntityTypeDesc(entityTypeDesc);*/
//      CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
        // regardingMemberVO.setEntityId(memberDO.getCurrAltID());
        if(memberDO!=null && memberDO.getCommonEntity() !=null 
        		&& memberDO.getCommonEntity().getCommonEntitySK()!=null){
        	regardingMemberVO.setEntityId(String.valueOf(memberDO.getCommonEntity().getCommonEntitySK()));	
        	//defect ESPRD00529115 starts
        	if(memberDO.getCurrAltID()!=null){
        		regardingMemberVO.setMemberId(memberDO.getCurrAltID());        		
        	}
        	//defect ESPRD00529115 ends
        }else{
        	regardingMemberVO.setEntityId(ContactManagementConstants.EMPTY_STRING);
        }
        
        //EOf CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
        //Added if Condition for Find_Bugs-FIX
        if(memberDO != null){
        regardingMemberVO.setMemberIDType(memberDO.getCurrAltIDTypeCode());
        }
        regardingMemberVO.setMemberIDTypeDesc(ContactManagementHelper
                .setShortDescription(FunctionalAreaConstants.MEMBER,
                        ReferenceServiceDataConstants.B_ALT_ID_TY_CD,
                        regardingMemberVO.getMemberIDType()));
        Set setOfPreviousNames = null;
        //Added if Condition for Find_Bugs-FIX
        if(memberDO != null){
        setOfPreviousNames = memberDO.getPreviousName();
        }
        if (setOfPreviousNames != null)
        {
            for (Iterator iter = setOfPreviousNames.iterator(); iter.hasNext();)
            {
                PreviousName prevName = (PreviousName) iter.next();
                Name name = prevName.getName();
                regardingMemberVO
                        .setPreviousName(name.getFirstName()
                                + MaintainContactManagementUIConstants.EMPTY_STRING_SPACE
                                + name.getLastName());
                break; 
            }
        }
        Set setOfEligibilitySpans = null; 
        //Added if Condition for Find_Bugs-FIX
        if(memberDO != null){
        setOfEligibilitySpans = memberDO.getEligibilitySpans();
        }
        Set setOfCaseOwners = null;
        if (setOfEligibilitySpans != null)
        {
            for (Iterator iter = setOfEligibilitySpans.iterator(); iter
                    .hasNext();)
            {
                EligibilitySpan eligibitySpan = (EligibilitySpan) iter.next();
                regardingMemberVO.setCategoryOfEligibility(eligibitySpan
                        .getCoeCode());
                regardingMemberVO.setCoeCodeDesc(ContactManagementHelper
                        .setShortDescription(FunctionalAreaConstants.MEMBER,
                                ReferenceServiceDataConstants.B_COE_CD,
                                regardingMemberVO.getCategoryOfEligibility()));
                if (eligibitySpan.getCaseInfo() != null) {
					setOfCaseOwners = eligibitySpan.getCaseInfo()
							.getCaseOwner();
				}
                if (setOfCaseOwners != null)
                {
                    for (Iterator iterator = setOfCaseOwners.iterator(); iterator
                            .hasNext();)
                    {
                        CaseOwner caseOwner = (CaseOwner) iterator.next();
                        regardingMemberVO.setDistrictOffice(caseOwner
                                .getCaseDistrictOfficeCode());
                        regardingMemberVO
                                .setDistrictOfficeDesc(ContactManagementHelper
                                        .setShortDescription(
                                                FunctionalAreaConstants.MEMBER,
                                                ReferenceServiceDataConstants.B_CASE_DSTCT_OFC_CD,
                                                regardingMemberVO
                                                        .getDistrictOffice()));
                        break; 
                    }
                }
                break; 
            }
        }
        DemographicInformation demographicInformation = null;
      //Added if Condition for Find_Bugs-FIX
        if(memberDO != null){
        demographicInformation = memberDO.getDemographicInformation();
        }
        if (demographicInformation != null)
        {
        	if(demographicInformation.getDateOfBirth() != null) {
            regardingMemberVO.setDateofBirth(ContactManagementHelper
                    .dateConverter(demographicInformation.getDateOfBirth()));
        	}
            if (demographicInformation.getName() != null)
            {
                regardingMemberVO
                        .setName(demographicInformation.getName()
                                .getFirstName()
                                + MaintainContactManagementUIConstants.EMPTY_STRING_SPACE
                                + demographicInformation.getName()
                                        .getLastName());
            }
            Set setOfAlternateIds = demographicInformation
                    .getAlternateMemberID();
            if (setOfAlternateIds != null)
            {
                for (Iterator iter = setOfAlternateIds.iterator(); iter
                        .hasNext();)
                {
                    AlternateMemberID altId = (AlternateMemberID) iter.next();
                    if (MaintainContactManagementUIConstants.SSN
                            .equalsIgnoreCase(altId.getAltIDTypeCode()))
                    {
                        regardingMemberVO.setMemberSSN(altId.getAltID());
                    }
                }
            }
        }
        CommonEntity commonEntity = null;
       //Added if Condition for Find_Bugs-FIX
        if(memberDO != null){
        commonEntity = memberDO.getCommonEntity();
        }
        if (commonEntity != null)
        {
            Set eAddressUsage = commonEntity.geteAddressUsage();
            if (eAddressUsage != null)
            {
                for (Iterator iter = eAddressUsage.iterator(); iter.hasNext();)
                {
                    EAddressUsage eAddrUsage = (EAddressUsage) iter.next();
                    regardingMemberVO.setEmail(eAddrUsage.geteAddress()
                            .geteAddressText());
                    break; 
                }
            }
            if(commonEntity.getCommonEntitySK() != null){
            regardingMemberVO.setCommonEntitySK(commonEntity
                    .getCommonEntitySK().toString());
            }
        }
        return regardingMemberVO;
    }

    
    
    /**
     * This method is used to convert the ProviderDO to VO
     * 
     * @param providerDO
     *            holds the Provider domain object
     * @return CaseRegardingProviderVO it returns the CaseRegardingProviderVO
     *         object
     */
    public CaseRegardingProviderVO convertProviderDOToVO(Provider providerDO)
    {
    	CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();
        CaseRegardingProviderVO regardingProviderVO = new CaseRegardingProviderVO();
        regardingProviderVO
                .setEntityType(ContactManagementConstants.ENTITY_TYPE_PROV);
        regardingProviderVO.setEntityTypeDesc(ContactManagementHelper
                .setShortDescription(FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
                        ContactManagementConstants.ENTITY_TYPE_PROV));
        //Modified for defectESPRD00386977 starts
//      CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
        //if(providerDO.getReportingAlternateID()!=null){
        	//regardingProviderVO.setEntityId(providerDO.getReportingAlternateID());
        	
        //}
        
        if(providerDO!=null && providerDO.getCommonEntity()!=null 
        		&& providerDO.getCommonEntity().getCommonEntitySK()!=null){
        	regardingProviderVO.setEntityId(String.valueOf(providerDO.getCommonEntity().getCommonEntitySK()));
        }else{
        	regardingProviderVO.setEntityId(ContactManagementConstants.EMPTYSTRING);
        }
//      EOf CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
        //defectESPRD00386977 ends
        //Added for defect ESPRD00332994 start
        ContactManagementHelper helper= new ContactManagementHelper();
        
        Long sysListNumber =  new Long("1017");//commemted for the crESPRD00703521
        /*ContactManagementHelper.getSystemlistForEntyIdType(
                ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_PROVIDER);*/
       
        List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                FunctionalAreaConstants.PROVIDER,sysListNumber);
        //Added if Condition for Find_Bugs-FIX
        if(providerDO != null){
        regardingProviderVO.setIdType(providerDO.getCurrentAltIDTypeCode());
        }
       String desc= helper.getDescriptionFromVV(
       		regardingProviderVO.getIdType(), 
			entIDTypeReferenceData);
       regardingProviderVO.setIdType(desc);
       //Code for defect ESPRD00332994 ends
       //Added if Condition for Find_Bugs-FIX
         if (providerDO != null && "I".equals(providerDO.getIndividualGroupCode()))
        {
            regardingProviderVO.setName(providerDO.getName().getLastName()
                    + MaintainContactManagementUIConstants.EMPTY_STRING_SPACE
                    + providerDO.getName().getFirstName());
        }
        else
        {
            regardingProviderVO.setName(providerDO.getOrganizationName());
        }
        if (providerDO.getCommonEntity() != null)
        {
        	
        	
            regardingProviderVO.setCommonEntitySK(providerDO.getCommonEntity()
                    .getCommonEntitySK().toString());
        }
        Set setOfProviderTypes = providerDO.getProviderTypes();
        if (setOfProviderTypes != null)
        {
            for (Iterator iter = setOfProviderTypes.iterator(); iter.hasNext();)
            {
                ProviderType provType = (ProviderType) iter.next();
                regardingProviderVO.setProviderTypeCode(provType
                        .getProviderTypeCode());
                regardingProviderVO
                        .setProviderTypeCodeDesc(ContactManagementHelper
                                .setShortDescription(
                                        FunctionalAreaConstants.PROVIDER,
                                        ReferenceServiceDataConstants.P_TY_CD,
                                        regardingProviderVO
                                                .getProviderTypeCode()));
            }
        }

        Set setOfProviderSpecialties = providerDO.getBoardCertifiedSpeciality();
        if (setOfProviderSpecialties != null)
        {
            for (Iterator iter = setOfProviderSpecialties.iterator(); iter
                    .hasNext();)
            {
                BoardCertifiedSpeciality provSpcl = (BoardCertifiedSpeciality) iter
                        .next();
                regardingProviderVO.setSpecialty(provSpcl.getSpecialityCode());
                regardingProviderVO.setSpecialtyDesc(ContactManagementHelper
                        .setShortDescription(FunctionalAreaConstants.PROVIDER,
                                ReferenceServiceDataConstants.P_SPECL_CD,
                                regardingProviderVO.getSpecialty()));
                break; // Get the first speciality
            }
        }

        Set setOfEnrollmentStatus = providerDO.getEnrollmentStatus();
        if (setOfEnrollmentStatus != null)
        {
            for (Iterator iter = setOfEnrollmentStatus.iterator(); iter
                    .hasNext();)
            {
                EnrollmentStatus enrollStatus = (EnrollmentStatus) iter.next();
                regardingProviderVO.setStatus(enrollStatus
                        .getEnrollmentStatusCode());
               
            }
        }
        return regardingProviderVO;
    }

    /**
     * This method Converts TPLCarrierDOToVO.
     * 
     * @param carrier
     *            Takes TPLCarrier as param.
     * @return CaseRegardingTPL
     */
    public CaseRegardingTPL convertTPLCarrierDOToVO(TPLCarrier carrier)
    {
        CaseRegardingTPL regardingTPL = new CaseRegardingTPL();
        //CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
        //regardingTPL.setEntityId(carrier.getCarrierID());
        if(carrier!=null && carrier.getCommonEntity()!=null
        		&& carrier.getCommonEntity().getCommonEntitySK()!=null){
        	regardingTPL.setEntityId(String.valueOf(carrier.getCommonEntity().getCommonEntitySK()));
        }else{
        	regardingTPL.setEntityId(ContactManagementConstants.EMPTYSTRING);
        }
        
        //EOF CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010

        regardingTPL.setEntityType(ContactManagementConstants.ENTITY_TYPE_TPL);
        
        
        
        //add code on ESPRD00357163
      
        regardingTPL
                .setEntityTypeDesc(ContactManagementConstants.ENTITY_TYPE_TPL
                        + ContactManagementConstants.HYPEN
                        + ContactManagementConstants.ENTITY_TYPE_TPLCarrier);
        //end
        //Added if Condition for Find_Bugs-FIX
        if(carrier != null){
        regardingTPL.setName(carrier.getCarrierName());
        }
        if (carrier != null && carrier.getCommonEntity() != null)
        {
            regardingTPL.setCommonEntitySK(carrier.getCommonEntity()
                    .getCommonEntitySK().toString());
        }
        return regardingTPL;
    }

    /**
     * This Method convertAltIdsForMember.
     * 
     * @param member
     *            Takes member object
     */
    public void convertAltIdsForMember(Member member)
    {
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        Set memberAltIDs = null;
        List altIDList = new ArrayList();
        memberAltIDs = member.getDemographicInformation()
                .getAlternateMemberID();
        if (memberAltIDs != null && !memberAltIDs.isEmpty())
        {
            for (Iterator iter = memberAltIDs.iterator(); iter.hasNext();)
            {
                AlternateMemberID altId = (AlternateMemberID) iter.next();
                if (altId != null)
                {
                    AlternateIdentifiersVO alternateIdentifiersVO = new AlternateIdentifiersVO();
                    alternateIdentifiersVO.setAlternateIDTypeCode(altId
                            .getAltIDTypeCode());
                    alternateIdentifiersVO
                            .setAlternateIDTypeCodeDesc(ContactManagementHelper
                                    .setShortDescription(
                                            FunctionalAreaConstants.MEMBER,
                                            ReferenceServiceDataConstants.B_ALT_ID_TY_CD,
                                            altId.getAltIDTypeCode()));
                    alternateIdentifiersVO.setAlternateID(altId.getAltID());
                    
                    
                    
                    /** ES2 --AlternateMemberID DO changed   */
                    alternateIdentifiersVO.setLineOfBusiness(altId.getLobCode());
                    
                    altIDList.add(alternateIdentifiersVO);
                }
            }
            logCaseDataBean.setAlternateIdentiMemList(altIDList);
            logCaseDataBean.setShowAlternateIdentifiersMem(true);
        }
    }

    /**
     * This Method convertAltIdsForProvider.
     * 
     * @param provider
     *            Takes provider object
     */
    public void convertAltIdsForProvider(Provider provider)
    {
        List altIDList = new ArrayList();
        Set setProviderAltIdsList = provider.getAlternateIdInfo();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        if (setProviderAltIdsList != null)
        {

            for (Iterator iter = setProviderAltIdsList.iterator(); iter
                    .hasNext();)
            {
                AlternateIdInfo altId = (AlternateIdInfo) iter.next();
                AlternateIdentifiersVO altIdVO = new AlternateIdentifiersVO();

                altIdVO.setAlternateID(altId.getAlternateID());
                altIdVO.setAlternateIDTypeCode(altId.getTypeCode());
                altIdVO.setAlternateIDTypeCodeDesc(ContactManagementHelper
                        .setShortDescription(FunctionalAreaConstants.PROVIDER,
                                ReferenceServiceDataConstants.P_ALT_ID_TY_CD,
                                altId.getTypeCode()));
                //altIdVO.setLineOfBusiness(altId.get)
                altIDList.add(altIdVO);
            }
            logCaseDataBean.setAlternateIdentiProvList(altIDList);
            logCaseDataBean.setShowAlternateIdentifiersProv(true);
        }
    }

    /**
     * This method is used to convert the SpecificEntity DO to VO
     * 
     * @param specificEntity
     *            holds the SpecificEntity DO
     * @return CaseRegardingTPL object
     */
    public CaseRegardingTPL convertSpecificEntityDOToVO(
            SpecificEntity specificEntity)
    {
    	if(logger.isDebugEnabled()){
        logger.debug("convertSpecificEntityDOToVO() is started");
    	}
        CaseRegardingTPL regardingVO = new CaseRegardingTPL();
        if(specificEntity!=null){
        regardingVO.setEntityType(specificEntity.getSpecificEntityTypeCode());
        }
      /*  String entityTypeDesc = ContactManagementHelper.setLongDescription(
                FunctionalAreaConstants.CONTACT_MGMT,
                ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD, specificEntity
                        .getSpecificEntityTypeCode());
        if(!entityTypeDesc.equalsIgnoreCase(specificEntity.getSpecificEntityTypeCode()))
        {
            entityTypeDesc = specificEntity.getSpecificEntityTypeCode()
                    + ContactManagementConstants.HYPEN + entityTypeDesc;
        }
        regardingVO.setEntityTypeDesc(entityTypeDesc);*/
        //End
        //CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
        /*if (specificEntity.getSpecificEntitySK() != null
                && !specificEntity.getSpecificEntitySK().equals(""))
        {
        	logger.debug("Specific entitySK is $$ "
                    + specificEntity.getSpecificEntitySK());
            regardingVO.setEntityId(specificEntity.getSpecificEntitySK()
                    .toString());
        }*///commented for CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
        if(specificEntity != null && specificEntity.getCommonEntity() != null 
        		&& specificEntity.getCommonEntity().getCommonEntitySK() != null){
        	 regardingVO.setEntityId(String.valueOf(specificEntity.getCommonEntity().getCommonEntitySK()));
        }else{
        	regardingVO.setEntityId(ContactManagementConstants.EMPTYSTRING);
        }
        //EOF CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
        //Added if Condition for Find_Bugs-FIX
        //for ESPRD00754717
        if(specificEntity.getOrganizationName()!=null){
        	regardingVO.setName(specificEntity.getOrganizationName());
        }
        
        if ((specificEntity.getOrganizationName() == null)
				&& specificEntity != null && specificEntity.getName() != null)
        {
            regardingVO.setName(specificEntity.getName().getFirstName()
                    + MaintainContactManagementUIConstants.EMPTY_STRING_SPACE
                    + specificEntity.getName().getLastName());
        }
        //for ESPRD00754717
        
        //Added if Condition for Find_Bugs-FIX
        if (specificEntity != null && specificEntity.getSsnNumber() != null) {

        	//added for the defect:esprd00680122
        	String SsnNumber = specificEntity.getSsnNumber();
        	
  			char SsnNumberArr[] = SsnNumber.toCharArray();
  			char SsnNumberUIArr[] = new char[(SsnNumberArr.length + 2)];

  			for (int i = 0, j = 0; i < SsnNumberArr.length; i++, j++) {
  				if (i == 3 || i == 5) {
  				    SsnNumberUIArr[j] = '-';
  					j++;
  					SsnNumberUIArr[j] = SsnNumberArr[i];
  					
  				} else
  				    SsnNumberUIArr[j] = SsnNumberArr[i];
  				
  			}
  			regardingVO.setSsn(new String(SsnNumberUIArr));
            //regardingVO.setSsn(specificEntity.getSsnNumber());
        }
        
    CommonEntity commonEntity = null; 
    //Added if Condition for Find_Bugs-FIX
    if(specificEntity != null){
    commonEntity = specificEntity.getCommonEntity();
    }
    Set setOfeAddressUsage = null;
    //Added if Condition for Find_Bugs-FIX
    if(commonEntity != null){
    	setOfeAddressUsage = commonEntity.geteAddressUsage();
        }
   		if (setOfeAddressUsage != null) {
   			
   			for (Iterator iter = setOfeAddressUsage.iterator(); iter.hasNext();) {
   				EAddressUsage eAddrUsage = (EAddressUsage) iter.next();
   				
   				
   				regardingVO.setEmail(eAddrUsage.geteAddress().getEAddressText());
   				
   				break; // get the first email.
   			}
   		}

   	//end for the defect:esprd00680122


       
        if (specificEntity != null && specificEntity.getLob() != null) {
            regardingVO.setLobCode(specificEntity.getLob().getLobCode());
        }
        if (specificEntity != null && specificEntity.getDistrictOfficeCode() != null) {
            regardingVO.setDistricOffice(specificEntity.getDistrictOfficeCode());
        }
        //End By ICS       
        if (specificEntity != null && specificEntity.getProviderTypeCode() != null)
        {
            regardingVO.setProvTypeCode(specificEntity.getProviderTypeCode());
            regardingVO.setProvTypeCodeDesc(ContactManagementHelper
                    .setShortDescription(FunctionalAreaConstants.PROVIDER,
                            ReferenceServiceDataConstants.P_TY_CD,
                            specificEntity.getProviderTypeCode()));
        }
        if (specificEntity != null && specificEntity.getCommonEntity() != null) {
			//CommonEntity commonEntity = specificEntity.getCommonEntity();
			if (commonEntity != null)
			{	
				regardingVO.setCommonEntitySK(commonEntity.getCommonEntitySK()
						.toString());
			}
		}
        return regardingVO;
    }

    /**
     * This method is used to get the Addtess object from the CommonEnity
     * 
     * @param cmAddress
     *            holds the CommonEnity DO object
     * @return String
     */
    public String getAddress(CommonEntity cmAddress)
    {
    	if(logger.isInfoEnabled()){
        logger.info("getAddress @ caseDomainToVOConverter");
    	}
        StringBuffer addr = new StringBuffer(
                ContactManagementConstants.DEFAULT_SIZE);
        if (cmAddress != null)
        {
            Set setOfAddressUsage = cmAddress.getAddressUsage();
            if (setOfAddressUsage != null)
            {
                for (Iterator iter = setOfAddressUsage.iterator(); iter
                        .hasNext();)
                {
                    AddressUsage addrUsage = (AddressUsage) iter.next();
                    Address address = addrUsage.getAddress();

                    addr.append(address.getAddressLine1());

                    if (address.getAddressLine2() != null && !address.getAddressLine2().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING))
                    {
                        addr
                                .append(MaintainContactManagementUIConstants.BR_TAG);
                        addr.append(address.getAddressLine2());
                    }
                    if (address.getAddressLine3() != null && !address.getAddressLine3().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING))
                    {
                        addr
                                .append(MaintainContactManagementUIConstants.BR_TAG);
                        addr.append(address.getAddressLine3());
                    }
                    if (address.getAddressLine4() != null && !address.getAddressLine4().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING))
                    {
                        addr
                                .append(MaintainContactManagementUIConstants.BR_TAG);
                        addr.append(address.getAddressLine4());
                    }
                    addr.append(MaintainContactManagementUIConstants.BR_TAG);
                    //Modified for the Defect ESPRD00879551
                    /*if (address.getTownCode() != null)
                    {
                        addr.append(address.getTownCode());
                    }
                    if (address.getCountyCode() != null)
                    {
                        addr.append(address.getCountyCode());
                    }*/
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
        }
        return addr.toString();
    }

    /**
     * This method is used to get the CaseTypeEvents DO to Vo
     * 
     * @param caseEvent
     *            holds the CaseTypeEvent object
     * @return CaseEventsVO returns the CaseEventsVO object
     */
    public CaseEventsVO convertCaseTypeEventsDOToVO(CaseTypeEvent caseEvent)
    {
    	
        ContactManagementHelper helper = new ContactManagementHelper();
        CaseEventsVO caseEventsVO = new CaseEventsVO();
        // Modify for the Defect ESPRD00789752 Starts
        /*caseEventsVO.setCreateDate(caseEvent.getAddedAuditTimeStamp());
        caseEventsVO.setCreateDateStr(ContactManagementHelper
                .dateConverter(caseEvent.getAddedAuditTimeStamp()));*/
        
        caseEventsVO.setCreateDate(new Date());
        caseEventsVO.setCreateDateStr(ContactManagementHelper
                .dateConverter(new Date()));
     // Modify for the Defect ESPRD00789752 Ends
        if (caseEvent.getCmCaseEventCode() != null)
        {
            caseEventsVO.setDesc(caseEvent.getCmCaseEventCode());
            //Added for the defect ESPRD00739441
			String desc = ContactManagementHelper.setShortDescription(
					FunctionalAreaConstants.GENERAL,
					ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_DDU,
					caseEvent.getCmCaseEventCode());

			if (desc == null) {
				desc = ContactManagementHelper
						.setShortDescription(
								FunctionalAreaConstants.GENERAL,
								ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD_OTH,
								caseEvent.getCmCaseEventCode());
			}
			if(desc!=null){
				if (desc.indexOf("-") != -1) {
					String[] description = desc.split("-");

					String descriptionVal = description[1];
					caseEventsVO.setDesc(descriptionVal);
				} else {
					caseEventsVO.setDesc(desc);
				}
			}else{
				caseEventsVO.setDesc(caseEvent.getCmCaseEventCode());
			}
			//Added  END for the defect ESPRD00739441
			
        }
        if (caseEvent.getCmCaseEventCode() != null)
        {
            caseEventsVO.setEventTypeCd(caseEvent.getCmCaseEventCode());
            caseEventsVO
                    .setEventTypeCdDesc(ContactManagementHelper
                            .setShortDescription(
                                    FunctionalAreaConstants.GENERAL2,
                                    ReferenceServiceDataConstants.G_CASE_TY_EVENT_TY_CD,
                                    caseEvent.getCmCaseEventCode()));
        }
        if (caseEvent.getEnterpriseUser() != null)
        {
            caseEventsVO.setNotifyViaAlert(caseEvent.getEnterpriseUser()
                    .getUserID());
            caseEventsVO.setNotifyViaAlertName(helper
                    .getDescriptionFromVV(caseEvent.getEnterpriseUser()
                            .getUserID(),logCaseDataBean.getEventNotifyList()));
        }
        if (caseEvent.getDefaultAlertBasedOnColName() != null)
        {
            caseEventsVO.setAlertBasedOn(caseEvent
                    .getDefaultAlertBasedOnColName());
           /* caseEventsVO
                    .setAlertBasedOnDesc(ContactManagementHelper
                            .setShortDescription(
                                    FunctionalAreaConstants.GENERAL2,
                                    ReferenceServiceDataConstants.G_DFLT_ALERT_BASED_ON_COL_NAM,
                                    caseEvent.getDefaultAlertBasedOnColName()));*/
            //Modified for defect ESPRD00902459 starts
            String alertBasedOnDesc=null;
            if(caseEvent.getDefaultAlertBasedOnColName()!=null){
            	alertBasedOnDesc = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_OTH,
						Long.valueOf(11), caseEvent.getDefaultAlertBasedOnColName());
            	
            	if(alertBasedOnDesc!=null && alertBasedOnDesc.length()<=2){
                	alertBasedOnDesc = ReferenceServiceDelegate
    				.getReferenceSearchShortDescription(
    						FunctionalAreaConstants.GENERAL,
    						ReferenceServiceDataConstants.G_ALERT_BASED_ON_COL_NAM_DDU,
    						Long.valueOf(10), caseEvent.getDefaultAlertBasedOnColName());
                	caseEventsVO.setAlertBasedOnDescription(alertBasedOnDesc);
                	if(logger.isInfoEnabled())
                    	logger.info("DDU User Logged in");
                }else{
                	if(logger.isInfoEnabled())
                    	logger.info("Other than DDU User Logged in");
                }
            	caseEventsVO.setAlertBasedOnDescription(alertBasedOnDesc);
            	caseEventsVO.setAlertBasedOnDesc(alertBasedOnDesc);
            }
            //ESPRD00902459 ends
        }
        if (caseEvent.getDefaultSendAlertDaysCode() != null)
        {
            caseEventsVO.setSendAlertDaysCd(caseEvent
                    .getDefaultSendAlertDaysCode());
        }
        if (caseEvent.getDefaultBeforeAfterCode() != null)
        {
            //caseEventsVO.setAfterOrBeforeCd(caseEvent.getDefaultBeforeAfterCode());
            //Modified for defect ESPRD00902459 starts
            if(caseEvent.getDefaultBeforeAfterCode().equals(MaintainContactManagementUIConstants.ALERT_BASED_VALID_CODE_B_FOR_BEFORE)){
				caseEventsVO.setAfterOrBeforeCd(MaintainContactManagementUIConstants.ALERT_BASED_DESC_FOR_VALID_CODE_B_FOR_BEFORE);
		}else if(caseEvent.getDefaultBeforeAfterCode().equals(MaintainContactManagementUIConstants.ALERT_BASED_VALID_CODE_A_FOR_AFTER)){

			caseEventsVO.setAfterOrBeforeCd(MaintainContactManagementUIConstants.ALERT_BASED_DESC_FOR_VALID_CODE_A_FOR_AFTER);
		} 
            //ESPRD00902459 ends
        }
      
        if (caseEvent.getLetterTemplate() != null)
        {
//        	added for UC-PGM-CRM-018.4_ESPRD00351624_17DEC09
        	if(caseEvent.getLetterTemplate().getLetterTemplateKeyData() != null){
        		caseEventsVO.setTemplate(caseEvent.getLetterTemplate().getLetterTemplateKeyData());
        	}//EOF UC-PGM-CRM-018.4_ESPRD00351624_17DEC09
        }
        caseEventsVO.setAddedAuditTimeStamp(caseEvent.getAddedAuditTimeStamp());
        caseEventsVO.setAddedAuditUserID(caseEvent.getAddedAuditUserID());
        caseEventsVO.setAuditTimeStamp(caseEvent.getAuditTimeStamp());
        caseEventsVO.setAuditUserID(caseEvent.getAuditUserID());
        return caseEventsVO;
    }

    /**
     * This method is used to get the CaseTypeSteps DO to Vo
     * 
     * @param caseTypeStep
     *            holds the CaseTypeStep object
     * @return CaseStepsVO it returns the CaseStepsVO object
     */
    public CaseStepsVO convertCaseTypeStepsDOToVO(CaseTypeStep caseTypeStep)
    {
        CaseStepsVO caseStepsVO = new CaseStepsVO();
        ContactManagementHelper helper = new ContactManagementHelper();
        caseStepsVO.setOrder(String.valueOf(caseTypeStep.getStepOrderNumber()
                .intValue()));
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
			//ADDED FOR THE DEFECT ESPRD00741609
        Date date = new Date();
        
		String dateStr = ContactManagementHelper.dateConverter(date);
			//ADDED  END FOR THE DEFECT ESPRD00741609
		
        if ("1".equals(caseStepsVO.getOrder()))
        {
            caseStepsVO.setStatus("A");
				//ADDED FOR THE DEFECT ESPRD00741609
            caseStepsVO.setDateStartedStr(dateStr);
           
            caseStepsVO.setDateStarted(date);
				//ADDED  END FOR THE DEFECT ESPRD00741609
            
        }
        else
        {
            caseStepsVO.setStatus("I");
        }
        caseStepsVO.setStatusDescription(helper.getDescriptionFromVV(caseStepsVO.getStatus(),logCaseDataBean.getStepStatusList()));
        //added for defect ESPRD00359439 Starts
       
        // defect ESPRD00359439 ends
        caseStepsVO.setDescription(caseTypeStep.getCmCaseStepCode());
        caseStepsVO.setCaseStepsDescription(helper.getDescriptionFromVV(caseTypeStep.getCmCaseStepCode(),logCaseDataBean.getCaseStepCodeList()));
        caseStepsVO.setCaseStepCode(caseTypeStep.getCmCaseStepCode());
        if (caseTypeStep.getDefaultRouteToWorkUnit() != null)
        {
			caseStepsVO.setRouteTo(caseTypeStep.getDefaultRouteToWorkUnit()
					.getWorkUnitSK().toString());
			//Modified for the defect ESPRD00937537
			caseStepsVO.setRouteToDescription(helper.getDescriptionFromVV(
					caseTypeStep.getDefaultRouteToWorkUnit().getWorkUnitSK()
							.toString(), logCaseDataBean
							.getRouteAllDeptUsersList()));
		}
        if (caseTypeStep.getDefaultDaysToCompleteCount() != null)
        {
            caseStepsVO.setExpectedDaysToComplete(String.valueOf(caseTypeStep
                    .getDefaultDaysToCompleteCount().intValue()));
        }
        if (caseTypeStep.getDefaultCompltBasedOnColName() != null)
        {
            caseStepsVO.setCompletedBasedOn(caseTypeStep
                    .getDefaultCompltBasedOnColName());
            caseStepsVO.setCompletionBasedOnDescription(helper.getDescriptionFromVV(caseTypeStep.getDefaultCompltBasedOnColName(),logCaseDataBean.getStepCompBasedOn()));
        }
       
        if (caseTypeStep.getEnterpriseUser() != null)
        {
			caseStepsVO.setNotifyViaAlert(caseTypeStep.getEnterpriseUser()
					.getUserWorkUnitSK().toString());
			//Modified for the defect ESPRD00937537
			/*caseStepsVO.setNotifyAlertDescription(helper.getDescriptionFromVV(
					caseTypeStep.getEnterpriseUser().getUserID(), logCaseDataBean.getUserIDsList()));*/
			caseStepsVO.setNotifyAlertDescription(getNameforUserId(caseTypeStep.getEnterpriseUser()));

		}
//      Modified for defect ESPRD00538641 starts
        if (caseTypeStep.getDefaultAlertBasedOnColName() != null)
        {
        	 caseStepsVO.setAlertBasedOnValue(caseTypeStep
                    .getDefaultAlertBasedOnColName());
            caseStepsVO.setAlertBasedOn(caseTypeStep
                    .getDefaultAlertBasedOnColName());
            caseStepsVO.setAlertBasedOnStr(caseTypeStep
                    .getDefaultAlertBasedOnColName());
            
        }
        
        if(caseTypeStep.getDefaultSendAlertDaysCode()!=null){
        	caseStepsVO
            .setSendAlertDays(caseTypeStep.getDefaultSendAlertDaysCode());
        	caseStepsVO
            .setSendAlertDaysStr(caseTypeStep.getDefaultSendAlertDaysCode());
        }
        if(caseTypeStep.getDefaultBeforeAfterCode()!=null){
        	caseStepsVO.setBeforeOrAfterInd(caseTypeStep
                    .getDefaultBeforeAfterCode());
        }
        //defect ESPRD00538641 ends
        if (caseTypeStep.getLetterTemplate() != null)
        {
            caseStepsVO.setTemplate(caseTypeStep.getLetterTemplate()
                    .getLetterTemplateKeyData());
        }
        caseTypeStep.setAddedAuditTimeStamp(caseTypeStep
                .getAddedAuditTimeStamp());
        caseTypeStep.setAddedAuditUserID(caseTypeStep.getAddedAuditUserID());
        caseTypeStep.setAuditTimeStamp(caseTypeStep.getAuditTimeStamp());
        caseTypeStep.setAuditUserID(caseTypeStep.getAuditUserID());
        return caseStepsVO;
    }

    /**
     * This method is used to convert the CaseRecord DO to CaseDetailsVO.
     * 
     * @param caseRecord
     *            holds the CaseRecord domain object.
     * @return CaseDetailsVO it returns the CaseDetailsVO object.
     * @throws SystemListNotFoundException
     * @throws ReferenceServiceRequestException
     */
    public CaseDetailsVO convertCaseDetailsDOToVO(CaseRecord caseRecord) throws ReferenceServiceRequestException, SystemListNotFoundException
    {
    	if(logger.isInfoEnabled()){
    	logger.info("- INSIDE convertCaseDetailsDOToVO()");
    	}
        ContactManagementHelper managementHelper = new ContactManagementHelper();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        CaseDetailsVO caseDetailsVO = new CaseDetailsVO();
        caseDetailsVO.setCaseSK(caseRecord.getCaseSK());
        if (caseRecord.getLobHierarchy() != null
                && caseRecord.getLobHierarchy().getLineOfBusinessHierarchySK() != null){
        	logCaseDataBean.setLobHierarchySK(caseRecord.getLobHierarchy().getLineOfBusinessHierarchySK());
        }
        
        if (caseRecord.getLobHierarchy() != null
                && caseRecord.getLobHierarchy().getLineOfBusinessHierarchySK() != null
                && caseRecord.getLobHierarchy().getLobHierarchyLevelNumber()
                        .intValue() == MaintainContactManagementUIConstants.TWO)
        {
            
            caseDetailsVO.setReportingUnitSK(caseRecord.getLobHierarchy()
                    .getLineOfBusinessHierarchySK());
            caseDetailsVO.setReportingUnit(caseRecord.getLobHierarchy()
                    .getLobHierarchyDescription());
        }
        if(caseRecord.getLineOfBusiness()!=null){
        	caseDetailsVO.setLineOfBusiness(caseRecord.getLineOfBusiness().getLobCode());
        }
        if (caseRecord.getLobHierarchy() != null
                && caseRecord.getLobHierarchy().getLineOfBusinessHierarchySK() != null
                && caseRecord.getLobHierarchy().getLobHierarchyLevelNumber()
                        .intValue() == MaintainContactManagementUIConstants.THREE)
        {
            caseDetailsVO.setBusinessUnitSK(caseRecord.getLobHierarchy()
                    .getLineOfBusinessHierarchySK());
            caseDetailsVO.setBusinessUnit(caseRecord.getLobHierarchy()
                    .getLobHierarchyDescription());
        }
 /*       if (caseRecord.getLobHierarchy() != null
                && caseRecord.getLobHierarchy().getLineOfBusinessHierarchySK() != null
                && caseRecord.getLobHierarchy().getLobHierarchyLevelNumber()
                        .intValue() == MaintainContactManagementUIConstants.FOUR)
        {
            caseDetailsVO.setWorkUnitSK(caseRecord.getLobHierarchy()
                    .getLineOfBusinessHierarchySK());
            logger.debug("LobHierarchy Work unitSK is "
                    + caseRecord.getLobHierarchy()
                            .getLineOfBusinessHierarchySK());
            Long lobSK = caseRecord.getLobHierarchy()
                    .getLineOfBusinessHierarchySK();
            Map mapOfDeptSKAndLobHier = logCaseDataBean
                    .getLobSKAndDeptSKMap();            

            String deptSK = (String) mapOfDeptSKAndLobHier
                    .get(lobSK.toString());
            logger.debug("deptSK while getting caseRecord " + deptSK);
            caseDetailsVO.setWorkUnit(deptSK);
            setReportingAndBusinessUnit(caseRecord.getLobHierarchy()
                    .getLineOfBusinessHierarchySK(), caseDetailsVO);
            String deptSuprWorkUnitSK = chkLoggedInUserIsSuperviForDept(deptSK);
            logger.debug("After getting supervisor workunit in DO to VO is $$ "
                    + deptSuprWorkUnitSK);
            if (deptSuprWorkUnitSK != null)
            {
                caseDetailsVO.setDeptSuperWorkUnitSK(deptSuprWorkUnitSK);
            }
            
        }*/
        if (caseRecord.getCaseCreatedByWorkUnit() != null)
        {
        	if(logger.isDebugEnabled()){
        	logger.debug("++ caseRecord.getCaseCreatedByWorkUnit()");
        	}
			/** for defect ESPRD00809473
			 * work unit type code = 'S' refers to converted case records
			 *  and It is System User.
			 * **/
            if ("U".equals(caseRecord.getCaseCreatedByWorkUnit()
					.getWorkUnitTypeCode())
					|| "S".equals(caseRecord.getCaseCreatedByWorkUnit()
							.getWorkUnitTypeCode())) {
               
                caseDetailsVO.setCreatedBySK(caseRecord
                        .getCaseCreatedByWorkUnit().getEnterpriseUser()
                        .getUserWorkUnitSK().toString());
                if (caseDetailsVO.getCreatedBySK() != null)
                {
                	//Commented for Heap dump fix defect ESPRD00935080
                    //Map userIdNameMap = logCaseDataBean.getUserNameMap();
                    //if(!userIdNameMap.isEmpty()){
                        //caseDetailsVO.setCreatedBy((String)userIdNameMap.get(caseDetailsVO.getCreatedBySK()));
                        //logger.debug("--UserNameMap 1::"+caseDetailsVO.getCreatedBy());
                        caseDetailsVO.setCreatedBy(getNameforUserId(caseRecord
                                .getCaseCreatedByWorkUnit().getEnterpriseUser()));
                        logger.debug("++UserNameMap 1::"+caseDetailsVO.getCreatedBy());
                        //Heap dump fix defect ESPRD00935080 ends
                    //Added for defect ESPRD00809473 Starts
                    if (caseDetailsVO.getCreatedBy()==null){
                    		String lastName = caseRecord
									.getCaseCreatedByWorkUnit()
									.getEnterpriseUser().getLastName();
							String firstName = caseRecord
									.getCaseCreatedByWorkUnit()
									.getEnterpriseUser().getFirstName();
							String userId = caseRecord
									.getCaseCreatedByWorkUnit()
									.getEnterpriseUser().getUserID();
							StringBuffer userName = new StringBuffer();
							
							if(lastName==null){
								userName.append(firstName).append("-").append(userId);
								caseDetailsVO.setCreatedBy(userName.toString());
							}else if(firstName==null){
								userName.append(lastName).append("-").append(userId);
								caseDetailsVO.setCreatedBy(userName.toString());
							}else if(userId==null){
								userName.append(lastName).append(", ").append(
										firstName);
								caseDetailsVO.setCreatedBy(userName.toString());
							}else{
								userName.append(lastName).append(", ").append(
										firstName).append("-").append(userId);
							caseDetailsVO.setCreatedBy(userName.toString());
							}
                    }
                  //Added for defect ESPRD00809473 Ends
                   // }
                }
            }
	        if (caseRecord.getCaseCreatedByWorkUnit().getWorkUnitTypeCode().equals("W")) {
	             caseDetailsVO.setCreatedBySK(caseRecord.getCaseCreatedByWorkUnit().getWorkUnitSK().toString());
	             if (caseDetailsVO.getCreatedBySK() != null) {
//	                 Map deptMap = logCaseDataBean.getDeptMap();
//	                 System.err.println("deptMap--=="+deptMap);
//	                 String assignedToName = (String) deptMap.get(caseDetailsVO
//	                         .getCreatedBySK());
	             	
	             	CaseDelegate caseDelegate =new CaseDelegate();
                    //Map deptMap = logCaseDataBean.getDeptMap();
                    Department department;
					try {
						department = caseDelegate.getDepartment(Long.valueOf(caseDetailsVO.getCreatedBySK()));
						if(department!=null){
			                 caseDetailsVO.setCreatedBy(department.getName());						}
					} catch (EnterpriseBaseBusinessException e) {
						// TODO Auto-generated catch block
						if(logger.isErrorEnabled()){
							logger.error(" Exception due to EnterpriseBaseBusinessException");
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						if(logger.isErrorEnabled()){
							logger.error(" Exception due to RemoteException");
						}
					} catch (CreateException e) {
						// TODO Auto-generated catch block
						if(logger.isErrorEnabled()){
							logger.error(" Exception due to CreateException");
						}
					}

	             }
	         }
        }
        if (caseRecord.getCaseAssignedToWorkUnit() != null)
        {
           
            if (caseRecord.getCaseAssignedToWorkUnit().getEnterpriseUser() != null
                    && caseRecord.getCaseAssignedToWorkUnit()
                            .getWorkUnitTypeCode().equals("U"))
            {
                //Added for identifying assigned to workunit type code
				// Added for the Defect: ESPRD00709390
                logCaseDataBean.setAssignedToUser(false);
                caseDetailsVO.setAssignedToWorkUnitSK(caseRecord
                        .getCaseAssignedToWorkUnit().getEnterpriseUser()
                        .getUserWorkUnitSK().toString());
                // Begin - Added the attributes for displaying the userName for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
                String lastName = caseRecord
                .getCaseAssignedToWorkUnit().getEnterpriseUser().getLastName();
                String firstName = caseRecord
                .getCaseAssignedToWorkUnit().getEnterpriseUser().getFirstName();
                String userId = caseRecord
                .getCaseAssignedToWorkUnit().getEnterpriseUser().getUserID();
                StringBuffer userName = new StringBuffer(); 
                userName.append(lastName).append(", ").append(firstName).append("-").append(userId); 
                // End - Added the attributes for displaying the userName for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
                if (caseDetailsVO.getAssignedToWorkUnitSK() != null)
                {
                   /* Map userIdNameMap = logCaseDataBean.getUserNameMap();
                    if(!userIdNameMap.isEmpty()) {*/
                    	/* Commented the code to fix defect id ESPRD00382224 */
                       // caseDetailsVO.setAssignedTo((String)userIdNameMap.get(caseDetailsVO.getCreatedBySK()));
                    	/*Added the following line to fix the defect ESPRD00382224 */
                    	//caseDetailsVO.setAssignedTo(managementHelper.getUserName(caseDetailsVO.getAssignedToWorkUnitSK())); 
                   // }  
                	// Begin - Added the code for setting the userName for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
                	try
                	{
                	CMRoutingVO routingVO = logCaseDataBean.getRoutingVO();
                	/*ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
                	WorkUnit workUnit = (WorkUnit) contactMaintenanceDelegate.getUserWorkUnit(caseDetailsVO.getAssignedToWorkUnitSK());
                	EnterpriseUser enterpriseUser = workUnit.getEnterpriseUser();*/
                    //caseDetailsVO.setAssignedTo(managementHelper.getUserName(caseDetailsVO.getAssignedToWorkUnitSK()));
                	caseDetailsVO.setAssignedTo(userName.toString());
                	}
                	catch (Exception  e) {
						// TODO: handle exception
                		if(logger.isErrorEnabled()){
							logger.error(" Exception due to convertCaseDetailsDOToVO");
						}
					}
                	// End - Added the code for setting the userName for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011                	
                }
            }
//            if (caseRecord.getCaseAssignedToWorkUnit().getWorkUnitTypeCode()
//                    .equals("W"))
//            {
               
//                caseDetailsVO
//                        .setAssignedToWorkUnitSK(caseRecord
//                                .getCaseAssignedToWorkUnit().getWorkUnitSK()
//                                .toString());
////                if (caseDetailsVO.getAssignedToWorkUnitSK() != null)
////				{
////	                if (caseDetailsVO.getAssignedToWorkUnitSK() != null) {
////	                    caseDetailsVO.setAssignedTo(managementHelper.getUserName(caseDetailsVO.getAssignedToWorkUnitSK()));
////	                }
////	            }
	            if (caseRecord.getCaseAssignedToWorkUnit().getWorkUnitTypeCode().equals("W")) {
	            	//Added for identifying assigned to workunit type code
					// Added for the Defect: ESPRD00709390
                    logCaseDataBean.setAssignedToUser(true);
	                caseDetailsVO.setAssignedToWorkUnitSK(caseRecord.getCaseAssignedToWorkUnit().getWorkUnitSK().toString());
//	                if (caseDetailsVO.getAssignedToWorkUnitSK() != null) {
//	                    caseDetailsVO.setAssignedTo(managementHelper.getUserName(caseDetailsVO.getAssignedToWorkUnitSK()));
//	                }
	                if (caseDetailsVO.getAssignedToWorkUnitSK() != null) {
	                	CaseDelegate caseDelegate =new CaseDelegate();
	                    //Map deptMap = logCaseDataBean.getDeptMap();
	                    Department department;
						try {
							department = caseDelegate.getDepartment(Long.valueOf(caseDetailsVO.getAssignedToWorkUnitSK()));
							if(department!=null){
			                    caseDetailsVO.setAssignedTo(department.getName());
							}
						} catch (EnterpriseBaseBusinessException e) {
							// TODO Auto-generated catch block
							if(logger.isErrorEnabled()){
								logger.error(" Exception due to EnterpriseBaseBusinessException");
							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							if(logger.isErrorEnabled()){
								logger.error(" Exception due to RemoteException");
							}
						} catch (CreateException e) {
							// TODO Auto-generated catch block
							if(logger.isErrorEnabled()){
								logger.error(" Exception due to CreateException");
							}
						}
						//(String) deptMap.get(caseDetailsVO                       .getAssignedToWorkUnitSK());
	                }
	            }
	    //    }
		// Begin - Added this block of code for displaying the businessUnit
		// 			for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011
	            if (caseRecord.getCaseAssignedToWorkUnit().getWorkUnitTypeCode().equals("B")) {
	                caseDetailsVO.setAssignedToWorkUnitSK(caseRecord.getCaseAssignedToWorkUnit().getWorkUnitSK().toString());
	                if (caseDetailsVO.getAssignedToWorkUnitSK() != null) {
							Map workUnitMap = logCaseDataBean.getWorkUnitMap();
							String deptName = (String) workUnitMap.get(new Long(caseDetailsVO.getAssignedToWorkUnitSK()));
							if(deptName!=null){
			                    caseDetailsVO.setAssignedTo(deptName);
							}
	                }
	            }
		// End - Added this block of code for displaying the businessUnit
		// 			for the defect id UC-PGM-CRM-018.1_ESPRD00702153_31OCT2011			
        }    
        if (caseRecord.getCaseType() != null)
        {
            caseDetailsVO.setCaseType(String.valueOf(caseRecord.getCaseType()
                    .getCaseTypeSK()));
            caseDetailsVO.setCaseTypeBusinessUnitTypeCode(caseRecord
                    .getCaseType().getBusinessUnitCaseTypeCode());
        }
        if (caseRecord.getCaseStatusCode() != null)
        {
            caseDetailsVO.setStatus(caseRecord.getCaseStatusCode());
            caseDetailsVO.setPreviousStatus(caseRecord.getCaseStatusCode());
        }
        if (caseRecord.getCasePriorityCode() != null)
        {
            caseDetailsVO.setPriority(caseRecord.getCasePriorityCode());
        }

        if (caseRecord.getTitle() != null)
        {
            caseDetailsVO.setCaseTitle(caseRecord.getTitle());
        }
       
        if (caseRecord.getLineOfBusiness() != null && caseRecord.getLineOfBusiness().getLobCode()!= null )
        {
            caseDetailsVO.setLineOfBusiness(caseRecord.getLineOfBusiness().getLobCode());
        }
        
        if (caseRecord.getSupervisorReviewReqIndicator() != null)
        {
            caseDetailsVO.setSupvrRewInd(caseRecord
                    .getSupervisorReviewReqIndicator().booleanValue());
        }
        if (caseRecord.getSensitiveDataIndicator() != null)
        {
            caseDetailsVO.setCaseSensitiveDataInd(String.valueOf(caseRecord
                    .getSensitiveDataIndicator()));
        }
        if (caseRecord.getOpenDate() != null)
        {
            caseDetailsVO.setCreatedDateStr(ContactManagementHelper
                    .dateConverter(caseRecord.getOpenDate()));
            caseDetailsVO.setCreatedDate(caseRecord.getOpenDate());
            if (!"C".equals(caseRecord.getCaseStatusCode()))
            {
                String days = managementHelper.getDaysDefference(caseRecord
                        .getOpenDate());
                
                if (days != null)
                {
                    List holidays = logCaseDataBean.getHolidaysList();
                    int intDays = new Integer(days).intValue();
                    int daysAfterExlHolidays = managementHelper
                            .getClosingDaysAfterExclHolidays(caseRecord
                                    .getOpenDate(), new Date(), intDays,
                                    holidays);
                    caseDetailsVO.setDaysopened(Integer.valueOf(daysAfterExlHolidays).toString());   /*FIND BUGS_FIX*/
                }
            }
            else
            {
                caseDetailsVO.setClosedDays(caseRecord.getDaysToCloseNumber()
                        .toString());
            }
        }
        if (caseRecord.getCaseStatusDate() != null)
        {
            caseDetailsVO.setStatusDate(caseRecord.getCaseStatusDate());
            caseDetailsVO.setPreviousStatusDate(caseRecord.getCaseStatusDate());
        }
        if (caseRecord.getNoteSet() != null)
        {
           
            logCaseDataBean.getCaseNotesSetTempList().add(
                    caseRecord.getNoteSet());
           
        }
        caseDetailsVO.setVersionNo(caseRecord.getVersionNo());
        if (caseRecord.getAuditUserID() != null)
        {
            caseDetailsVO.setAuditUserID(caseRecord.getAuditUserID());
        }
        if (caseRecord.getAuditTimeStamp() != null)
        {
            caseDetailsVO.setAuditTimeStamp(caseRecord.getAuditTimeStamp());
        }
        if (caseRecord.getAddedAuditUserID() != null)
        {
            caseDetailsVO.setAddedAuditUserID(caseRecord.getAddedAuditUserID());
        }
        if (caseRecord.getAddedAuditTimeStamp() != null)
        {
            caseDetailsVO.setAddedAuditTimeStamp(caseRecord
                    .getAddedAuditTimeStamp());
        }
        if (caseRecord.getCaseARS() != null)
        {
        	
             if (caseRecord.getCaseType().getShortDescription()
					.equalsIgnoreCase("A3")) {
				caseDetailsVO
						.setCaseTypeAcuityRateSettingVO(convertCaseTypeAcuityRateSettingDOToVO(caseRecord
								.getCaseARS()));
			}
             
             /*
			  * if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("A3")){
			  * logger.debug(" in CASE ARS in convertor"); logger.info("I am in
			  * convertCaseTypeAcuityRateSettingDOToVO");
			  * 
			  * 
			  * caseDetailsVO.setCaseTypeARSVO(convertCaseTypeARSDOToVO(caseRecord
			  * .getCaseARS())); }
			  */
             
             
             
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("F4")){
            caseDetailsVO.setCaseTypeFPPRRVO(convertCaseTypeFPPRRDOToVO(caseRecord
                    .getCaseARS()));
        	}
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("CB")){
            caseDetailsVO.setCaseTypeCreditBalanceVO(convertCaseTypeCreditBalanceDOToVO(caseRecord
                    .getCaseARS()));
            }
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("F3")){
            caseDetailsVO.setCaseTypeFacilityCensusSubmissionVO(convertCaseTypeFacilityCensusSubmissionDOToVO(caseRecord
                    .getCaseARS()));
            }
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("F2")){
            caseDetailsVO.setCaseTypeFCRVO(convertCaseTypeFCRDOToVO(caseRecord
                    .getCaseARS()));
            }
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("H2")){
            caseDetailsVO.setCaseTypeHomeOfficeCostReportingVO(convertCaseTypeHomeOfficeCostReportingDOToVO(caseRecord
                    .getCaseARS()));
            }
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("N2")){
            caseDetailsVO.setCaseTypeNFQAVO(convertCaseTypeNFQADOToVO(caseRecord
                    .getCaseARS()));
            }
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("Q2")){
            caseDetailsVO.setCaseTypeQuarterlyMQIPReturnsVO(convertCaseTypeQuarterlyMQIPReturnsDOToVO(caseRecord
                    .getCaseARS()));
            }
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("A4")){
            caseDetailsVO.setCaseTypeNewARSFieldVO(convertCaseTypeNewARSFieldDOToVO(caseRecord
                    .getCaseARS()));
            }
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("N3")){
            caseDetailsVO.setCaseTypeNonARSFieldVO(convertCaseTypeNonARSFieldDOToVO(caseRecord
                    .getCaseARS()));
            }
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("AR")){
            caseDetailsVO.setCaseTypeAppealRequestVO(convertCaseTypeAppealRequestDOToVO(caseRecord
                    .getCaseARS()));
            }
            if(caseRecord.getCaseType().getShortDescription().equalsIgnoreCase("FA")){
            caseDetailsVO.setCaseTypeFieldAuditVO(convertCaseTypeFieldAuditDOToVO(caseRecord
                    .getCaseARS()));
            }
        }
        if (caseRecord.getCaseBCCP() != null
                && caseRecord.getCaseBCCP().getAuditTimeStamp() != null)
        {
            caseDetailsVO
                    .setCaseTypeBCCPVO(convertCaseTypeBCCPDOToVO(caseRecord
                            .getCaseBCCP()));
        }
        if (caseRecord.getCaseDDU() != null
                && caseRecord.getCaseDDU().getAuditTimeStamp() != null)
        {
            caseDetailsVO.setCaseTypeDDUVO(convertCaseTypeDDUDOToVO(caseRecord
                    .getCaseDDU()));
        }
    /*    if (caseRecord.getCaseCommonEntityCrossRefs() != null)
        {
           
            Set caseCMNXRef = caseRecord.getCaseCommonEntityCrossRefs();
            Iterator iterator = caseCMNXRef.iterator();
            CaseCommonEntityCrossRef ref = null;
            while (iterator.hasNext())
            {
            	 logger
                 .debug("caseRecord.getCaseCommonEntityCrossRefs() size is $$ "
                         + caseCMNXRef.size());
                ref = (CaseCommonEntityCrossRef) iterator.next();
                
                logCaseDataBean.setCaseCmnEntyXrefVersionNo(ref.getVersionNo());
                
                caseDetailsVO = setInqAbtData(caseDetailsVO, ref);
                
            }
        }*/
    	//CR_ESPRD00373565_LogCase_05AUG2010
        // Condition Added for the Defect : ESPRD00790505 
         if(logCaseDataBean.isEnableAuditLogs()){
	        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
	        contactManagementHelper.createVOAuditKeysList(caseRecord,caseDetailsVO);
	        if(caseRecord.getLineOfBusiness() != null){
	        	contactManagementHelper.createVOAuditKeysList(caseRecord.getLineOfBusiness(),caseDetailsVO);
	        }
	        if(caseRecord.getCaseDDU() != null){
	        	contactManagementHelper.createVOAuditKeysList(caseRecord.getCaseDDU(),caseDetailsVO);
	        }
	        if(caseRecord.getCaseARS() != null){
	        	contactManagementHelper.createVOAuditKeysList(caseRecord.getCaseARS(),caseDetailsVO);
	        }
	        if(caseRecord.getCaseBCCP() != null){
	        	contactManagementHelper.createVOAuditKeysList(caseRecord.getCaseBCCP(),caseDetailsVO);
	        }
	        if(caseRecord.getCaseBCCP() !=null && caseRecord.getCaseBCCP().getScreeningAgencyPhone() != null){
	        	contactManagementHelper.createVOAuditKeysList(caseRecord.getCaseBCCP().getScreeningAgencyPhone(),caseDetailsVO);
	        }
	        if(caseRecord.getCaseBCCP() !=null && caseRecord.getCaseBCCP().getCaregiverPhone() != null){
	        	contactManagementHelper.createVOAuditKeysList(caseRecord.getCaseBCCP().getCaregiverPhone(),caseDetailsVO);
	        }
        doAuditKeyListOperationForCaseDetails(caseDetailsVO);
        }
        // Condition Added for the Defect : ESPRD00790505  End
        //EOF CR_ESPRD00373565_LogCase_05AUG2010
        if(logger.isInfoEnabled()){
        logger.info("- END of convertCaseDetailsDOToVO()--");
        }

        return caseDetailsVO;
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
  /*  private CaseDetailsVO setInqAbtData(CaseDetailsVO detailsVO,
            CaseCommonEntityCrossRef crossRef)
    {
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN);
        logger.debug("crossRef.getCaseCRContactReasonCode()"+ crossRef.getCaseCRContactReasonCode());
        if ("I".equals(crossRef.getCaseCRContactReasonCode()))
        {
            
            setInquiryAboutEntity(detailsVO, crossRef);
        }
        if ("P".equals(crossRef.getCaseCRContactReasonCode()))
        {
            logCaseDataBean.setCommonEntitySKForMyTask(
                    crossRef.getCommonEntitySK());
            logCaseDataBean.setEntityType(
                    crossRef.getCommonEntityTypeCode());
        }
        return detailsVO;
    }*/

    /**
     * This method sets the inq abt Entity.
     * 
     * @param detailsVO
     *            Takes CaseDetailsVO as param
     * @param crossRef
     *            Takes crossRef as param
     * @return CaseDetailsVO
     */
 /*   private CaseDetailsVO setInquiryAboutEntity(CaseDetailsVO detailsVO,
            CaseCommonEntityCrossRef crossRef)
    {
        String entityType = crossRef.getCommonEntityTypeCode();
        Long cmEntitySK = crossRef.getCommonEntity().getCommonEntitySK();
        logger.debug("setInquiryAboutEntity is started");
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN);
        if (ContactManagementConstants.ENTITY_TYPE_PROV.equals(entityType))
        {
            ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

            Long entityId = null;
            try
            {
                entityId = providerInformationDelegate
                        .getProviderSysID(cmEntitySK);
                if (entityId == null)
                {
                    logger.error("LogCase Provider Entity Not Found");
                }
                else
                {
                    CaseRegardingProviderVO providerVO = getEntitiesForProvider(
                            entityId.toString(), false);
                    providerVO.setVersionNo(crossRef.getVersionNo());
                    detailsVO.getInqAbtEntityList().add(providerVO);
                    logCaseDataBean
                            .setShowAddiCaseEntitesDataTable(true);
                }
            }
            catch (EnterpriseBaseBusinessException e)
            {
                logger.error(e.getMessage(), e);
            }
        }
        else if (ContactManagementConstants.ENTITY_TYPE_MEM.equals(entityType))
        {
            MemberInformationDelegate memberInformationDelegate = new MemberInformationDelegate();
            Long entityId = null;

            try
            {
                entityId = memberInformationDelegate.getMemberID(cmEntitySK);
                if (entityId == null)
                {
                    logger.error("LogCase Member Entity Not Found");
                }
                else
                {
                    CaseRegardingMemberVO memberVO = getEntityDetailsForMember(
                            entityId.toString(), false);
                    memberVO.setVersionNo(crossRef.getVersionNo());
                    detailsVO.getInqAbtEntityList().add(memberVO);
                    logCaseDataBean
                            .setShowAddiCaseEntitesDataTable(true);
                }
            }
            catch (MemberBusinessException e)
            {
                logger.error(e.getMessage(), e);
            }
        }
        else if (ContactManagementConstants.ENTITY_TYPE_TPL.equals(entityType))
        {
        	logger.debug("Your entity type is TPL and CMNO " + cmEntitySK);
            TPLCarrierDelegate carrierDelegate = new TPLCarrierDelegate();
           
            TPLCarrier carrier =new TPLCarrier();
            Long entityID = null;
            try
            {
                
                if (carrier != null)
                {
                    entityID = new Long(carrier.getCarrierID());
                    logger.debug("TPL carrier ID in setInquiryAboutEntity is "
                            + entityID);
                    if (entityID == null)
                    {
                        logger.error("TPL Entity ID Not Found");
                    }
                    else
                    {
                        CaseRegardingTPL regarding = getEntityForTPL(entityID
                                .toString(), false);
                        regarding.setVersionNo(crossRef.getVersionNo());
                        detailsVO.getInqAbtEntityList().add(regarding);
                        logCaseDataBean.setShowAddiCaseEntitesDataTable(
                                true);
                    }
                }
            }
           
            catch (Exception e)
            {
                logger.error(e.getMessage(), e);
            }
        }
        else
        {
            String entityId = cmEntitySK.toString();
            CaseRegardingTPL regarding = getSpecificEntity(entityId, false);
            regarding.setVersionNo(crossRef.getVersionNo());
            detailsVO.getInqAbtEntityList().add(regarding);
            logCaseDataBean.setShowAddiCaseEntitesDataTable(true);
        }
        return detailsVO;
    }*/

    /**
     * This method is used to get the Entity details like Member, provider,
     * etc... depend on the ID
     * 
     * @param entityID
     *            holds the entity ID
     * @param inqCaseEntity
     *            holds the entityType
     */
   /* public CaseRegardingMemberVO getEntityDetailsForMember(String entityID,
            boolean inqCaseEntity)
    {
        logger
                .debug("Getting Entity details are started $ LogCaseControllerBean");
        Long memberSysId;
        Member member = null;
        CommonEntity cmResidentAddress = null;
        CommonEntity cmMailingAddress = null;
        MemberInformationDelegate memberInformationDelegate = null;
        CaseRegardingMemberVO caseRegardingMemberVO = null;
        LogCaseDomainToVOConverter caseDomainToVOConverter = null;
        caseDomainToVOConverter = new LogCaseDomainToVOConverter();

        memberSysId = Long.valueOf(entityID);
        logger
        .debug("memberSysId @ getEntityDetails() is"
                + memberSysId);
        try
        {
            MemberInformationRequestVO memberInfRequestVO = new MemberInformationRequestVO();

            memberInfRequestVO.setMemberSysID(memberSysId);
            memberInfRequestVO.setAsOfDate(new Date());
            memberInfRequestVO.setPreviousNamesRequired(true);
            memberInfRequestVO.setEligibilitySpansRequired(true);
            memberInfRequestVO.setCommonEntityRequired(true);
            memberInfRequestVO.setAlternateMemberRequired(true);
            logger.debug("before calling member details");
            memberInformationDelegate = new MemberInformationDelegate();
            member = memberInformationDelegate
                    .getMemberDetail(memberInfRequestVO);

            if (member != null)
            {
                cmResidentAddress = memberInformationDelegate.getRecentAddress(
                        memberSysId, "R");
                cmMailingAddress = memberInformationDelegate.getRecentAddress(
                        memberSysId, "M");

                caseRegardingMemberVO = caseDomainToVOConverter
                        .convertMemberDOToVo(member);

                caseRegardingMemberVO
                        .setResidentialAddress(caseDomainToVOConverter
                                .getAddress(cmResidentAddress));

                caseRegardingMemberVO.setMailingAddress(caseDomainToVOConverter
                        .getAddress(cmMailingAddress));
                if (inqCaseEntity)
                {
                    caseDomainToVOConverter.convertAltIdsForMember(member);
                }
            }
        }
        catch (MemberBusinessException e)
        {
            logger.error(e.getMessage(), e);
        }
        return caseRegardingMemberVO;
    }*/

    /**
     * This method getEntitiesForProvider.
     * 
     * @param entityID
     *            Takes entityID as param
     * @param inqEntity
     *            Takes inqEntity as param
     * @return CaseRegardingProviderVO
     */
   /* private CaseRegardingProviderVO getEntitiesForProvider(String entityID,
            boolean inqEntity)
    {
        logger.debug("Getting Provider details are started");
        CaseRegardingProviderVO providerVO = null;
        ProviderInformationRequestVO providerInfRequestVO = new ProviderInformationRequestVO();
        providerInfRequestVO.setProviderSysID(Long.valueOf(entityID));
        providerInfRequestVO.setAlternateIdInfoRequired(true);
        providerInfRequestVO.setEnrollmentStatusRequired(true);
        providerInfRequestVO.setRepresentativeContactRequired(true);
        providerInfRequestVO.setBoardCertifiedSpecialityRequired(true);
        providerInfRequestVO.setProviderTypesRequired(true);
        LogCaseDomainToVOConverter caseDomainToVOConverter = null;
        caseDomainToVOConverter = new LogCaseDomainToVOConverter();
        ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

        Provider provider = providerInformationDelegate
                .getProviderDetails(providerInfRequestVO);

        if (provider != null)
        {
            providerVO = caseDomainToVOConverter
                    .convertProviderDOToVO(provider);
            if (inqEntity)
            {
                caseDomainToVOConverter.convertAltIdsForProvider(provider);
            }
        }
        return providerVO;
    }*/

    /**
     * This method getEntityForTPL.
     * 
     * @param entityID
     *            Takes entityID as param
     * @param inqEntity
     *            Takes inqEntity as param
     * @return CaseRegardingTPL
     */
  /*  private CaseRegardingTPL getEntityForTPL(String entityID, boolean inqEntity)
    {
        CaseRegardingTPL regardingTPL = null;
        logger.debug("Before calling TPL delegate entityID is $$ " + entityID);
        TPLCarrierDelegate carrierDelegate = new TPLCarrierDelegate();
        TPLCarrier carrier;
        try
        {
            carrier = carrierDelegate.checkCarrierIdExists(entityID);
            LogCaseDomainToVOConverter caseDomainToVOConverter = new LogCaseDomainToVOConverter();
            if (carrier != null)
            {
                regardingTPL = caseDomainToVOConverter
                        .convertTPLCarrierDOToVO(carrier);
            }
        }
        catch (TPLCarrierBusinessException e)
        {
            logger.error(e.fillInStackTrace());
        }
        return regardingTPL;
    }*/

    /**
     * This method getSpecificEntity.
     * 
     * @param entityID
     *            Takes entityID as param
     * @param inqEntity
     *            Takes inqEntity as param
     * @return CaseRegardingTPL
     */
 /*   private CaseRegardingTPL getSpecificEntity(String entityID,
            boolean inqEntity)
    {
        logger.debug("Getting Specific Entity details are started");
        CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
        SpecificEntity specificEntity = null;
        CaseRegardingTPL regarding = null;
        LogCaseDomainToVOConverter caseDomainToVOConverter = null;
        caseDomainToVOConverter = new LogCaseDomainToVOConverter();
        try
        {
            specificEntity = cmEntityDelegate.getSpecificEntityDetails(Long
                    .valueOf(entityID));
            logger
            .debug("After getting specific entity details the return DO is $$ "
                    + specificEntity);
            if (specificEntity != null)
            {
                regarding = caseDomainToVOConverter
                        .convertSpecificEntityDOToVO(specificEntity);
            }
        }
        catch (CMEntityFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (NumberFormatException e)
        {
            logger.error(e.getMessage(), e);
        }
        return regarding;
    }*/

    /**
     * This Method checks if the logged in user is supervisor for dept.
     * 
     * @param deptSK
     *            Takes deptSK as param
     * @return String
     */
  /*  private String chkLoggedInUserIsSuperviForDept(String deptSK)
    {
        logger.debug("chkLoggedInUserIsSuperviForDept is started");
        ContactMaintenanceDelegate delegate = new ContactMaintenanceDelegate();
        String deptSupviWorkUnitSK = null;
        try
        {
            Department department = null;
            department = delegate.getSupervisorToDept(new Long(deptSK));
            Long deptSuprviWorkUnitSK = department
                    .getSupervisorUserWorkUnitSK();
            logger.debug("dept Suprvisor WorkUnitSK in DO to VO is"
                    + deptSuprviWorkUnitSK);
            if (deptSuprviWorkUnitSK != null)
            {
                deptSupviWorkUnitSK = deptSuprviWorkUnitSK.toString();
            }
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (NumberFormatException e)
        {
            logger.error(e.getMessage(), e);
        }
        return deptSupviWorkUnitSK;
    }*/

    /**
     * This mehod sets reporting and Business Unit.
     * 
     * @param lobHierarchySK
     *            Takes lobHierarchySK as param
     * @param detailsVO
     *            Takes detailsVO as param
     */
    /*private void setReportingAndBusinessUnit(Long lobHierarchySK,
            CaseDetailsVO detailsVO)
    {
        logger.info("setReportingAndBusinessUnit at DO 2 VO");      
        logger.debug("lobHierarchySK " + lobHierarchySK);
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        try
        {
            LineOfBusinessHierarchy reportingUnit = contactMaintenanceDelegate
                    .getDeptReportingUnit(lobHierarchySK);

            LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
                    .getDeptBusinessUnit(lobHierarchySK);            

            if (reportingUnit != null)
            {
                
                detailsVO.setReportingUnit(reportingUnit
                        .getLobHierarchyDescription());
            }
            else
            {
                detailsVO
                        .setReportingUnit(ContactManagementConstants.EMPTY_STRING);
            }
            if (businessUnit != null)
            {
                
                detailsVO.setBusinessUnit(businessUnit
                        .getLobHierarchyDescription());
            }
            else
            {
                detailsVO
                        .setBusinessUnit(ContactManagementConstants.EMPTY_STRING);
            }

        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
        }
    }*/

    /**
     * This method is used to convert the CMRouting DO to VO
     * 
     * @param setOfCaseRoutingsDOs
     *            holds the set of CMRouting DOs
     * @return it retirns the list of CMRouting VOs
     */
    public List convertRoutingDOToVO(Set setOfCaseRoutingsDOs,CaseRecord caseRecord)
    {
    	if(logger.isInfoEnabled()){
        logger.info("Case Routings  :" + setOfCaseRoutingsDOs.size());
    	}
        List listOfCaseRoutingVOs = new ArrayList();

        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();//CR_ESPRD00373565_LOGCASE_23JUL2010


        for (Iterator iter = setOfCaseRoutingsDOs.iterator(); iter.hasNext();)
        {
            CaseRouting caseRoutingDO = (CaseRouting) iter.next();
            CMRoutingVO cMRoutingVO = convertCMRoutingDOToVO(caseRoutingDO,caseRecord);
            listOfCaseRoutingVOs.add(cMRoutingVO);
            // Condition Added for the Defect : ESPRD00790505 Start
            if(logCaseDataBean.isEnableAuditLogs()){
            	//CR_ESPRD00373565_LOGCASE_23JUL2010
	            contactManagementHelper.createVOAuditKeysList(caseRoutingDO,cMRoutingVO);
	            doAuditKeyListOperationForRouting(cMRoutingVO);
	            //EOF CR_ESPRD00373565_LOGCASE_23JUL2010
            } 
           //  Condition Added for the Defect : ESPRD00790505 End
        }
        return listOfCaseRoutingVOs;
    }

    /**
     * This private method is used to convert the CMRouting DO to VO
     * 
     * @param caseRouting
     *            holds the CaseRouting DO
     * @return it returns the CMRoutingVO object
     */
    private CMRoutingVO convertCMRoutingDOToVO(CaseRouting caseRouting,CaseRecord caseRecord)
    {
        CMRoutingVO cMRoutingVO = new CMRoutingVO();
        String routedToName = null;
        String routedByName = null;
        Map deptMap = null;
        String userDept = null;
        Map busineesUnitMap = null;
        Map workUnitMap = null;
        ContactManagementHelper helper = new ContactManagementHelper();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        if (caseRouting.getCaseRoutingDate() != null)
        {
            helper.getFormatedRoutingDate(cMRoutingVO, caseRouting
                    .getCaseRoutingDate());
        }
        if (caseRouting.getRoutedToWorkUnit() != null
                && caseRouting.getRoutedToWorkUnit().getEnterpriseUser() != null
                && caseRouting.getRoutedToWorkUnit().getWorkUnitTypeCode()
                        .equals("U"))
        {
        	
            cMRoutingVO.setRoutedToWorkUnitSK(caseRouting.getRoutedToWorkUnit()
                    .getEnterpriseUser().getUserWorkUnitSK());
            cMRoutingVO.setAssignThisRecordTo(String.valueOf(caseRouting
                    .getRoutedToWorkUnit().getEnterpriseUser()
                    .getUserWorkUnitSK()));
           
            /*routedToName = helper.getUserName(caseRouting.getRoutedToWorkUnit()
                    .getEnterpriseUser().getUserWorkUnitSK().toString());
            logger.debug("--usermap 1::"+routedToName);*/
            routedToName = getUserName(caseRouting.getRoutedToWorkUnit());
            logger.debug("++usermap 1::"+routedToName);
            //
            /* routedToName = helper.getUserName(caseRouting.getRoutedToWorkUnit()
            .getEnterpriseUser().getUserWorkUnitSK().toString());*/
            
			//    cMRoutingVO.setAssignRoutedToName(getName(caseRouting
			//            .getRoutedToWorkUnit(), "U"));
			//    
			//   
			//    logger.debug("::cMRoutingVO.getAssignRoutedToName():::::: "+ cMRoutingVO.getAssignRoutedToName());
			//    cMRoutingVO.setRoutedTo(cMRoutingVO.getAssignRoutedToName());
			//    cMRoutingVO.setAssignedTo(cMRoutingVO.getAssignRoutedToName());
			//    cMRoutingVO.setAssignRoutedToName(cMRoutingVO.getAssignRoutedToName());
			//    
			//    /** Added For Defect ID ESPRD00330104 */   
			//    System.err.println("cMRoutingVO.getAssignRoutedToName()::::" + cMRoutingVO.getAssignRoutedToName());
			//    cMRoutingVO.setRoutedToName(cMRoutingVO.getAssignRoutedToName());
			//    cMRoutingVO.setAssignedUserName(cMRoutingVO.getAssignRoutedToName());
    

            //
            cMRoutingVO.setRoutedTo(routedToName);
            cMRoutingVO.setAssignedTo(routedToName);
            cMRoutingVO.setAssignRoutedToName(routedToName);
            
            /** Added For Defect ID ESPRD00330104 */   
            
            cMRoutingVO.setRoutedToName(routedToName);
            cMRoutingVO.setAssignedUserName(routedToName);
            
            Long workUnitSK = caseRouting.getRoutedToWorkUnit().getWorkUnitSK();
           
            busineesUnitMap = logCaseDataBean.getBusineesUnitMap();
            if(busineesUnitMap != null && !(busineesUnitMap.isEmpty())){
            userDept = (String) busineesUnitMap.get(workUnitSK.toString());
            }
            workUnitMap = logCaseDataBean.getWorkUnitMap();
            Long lobHighSk = caseRecord.getLobHierarchy().getLineOfBusinessHierarchySK();
            if(workUnitMap != null && !(workUnitMap.isEmpty())){
            userDept = (String) workUnitMap.get(lobHighSk);
            }
            cMRoutingVO.setWorkUnit(userDept);
            String userDeptName = (String)logCaseDataBean.getDeptMap().get(logCaseDataBean.getUserDeptName());
			cMRoutingVO.setUserDeptName(userDeptName); 
        }
       
           
       else if (caseRouting.getRoutedToWorkUnit() != null
				&& caseRouting.getRoutedToWorkUnit().getWorkUnitTypeCode()
						.equals("W")) {
			
			Long workUnitSK = caseRouting.getRoutedToWorkUnit().getWorkUnitSK();
			
			if (workUnitSK != null) {
				deptMap = logCaseDataBean.getWorkUnitSKMap();
				routedToName = (String) deptMap.get(workUnitSK.toString());
				String userId = "";
				 //Commented for Heap dump fix defect ESPRD00935080
				//userId = helper.getUserIDByWUSK(workUnitSK.toString());
				//logger.debug("--useridWUmap 1::"+userId);
				userId = helper.getUserIDByWUSK2(workUnitSK.toString());
				logger.debug("--useridWUmap 1::"+userId);
				/*routedToName = helper.getUserName(caseRouting
						.getRoutedToWorkUnit().getEnterpriseUser()
						.getUserWorkUnitSK().toString());*/
				/*if (userId != null){
					routedToName = helper.getUserName(workUnitSK.toString());
					logger.debug("--usermap 2::"+routedToName);
				}*/
				EnterpriseUser routeToUser = caseRouting.getRoutedToWorkUnit().getEnterpriseUser();
				
				if (routeToUser != null){
					//Commented for getting Username from Map
					//routedToName = helper.getUserName(workUnitSK.toString());
					routedToName = getUserName(caseRouting.getRoutedToWorkUnit());
					logger.debug("++usermap 2::"+routedToName);
				}
				cMRoutingVO.setRoutedToWorkUnitSK(workUnitSK);
				cMRoutingVO.setRoutedTo(routedToName);
				/*cMRoutingVO.setAssignedTo(caseRecord
						.getCaseAssignedToWorkUnit().getEnterpriseUser()
						.getUserID());*/
				cMRoutingVO.setAssignedTo(userId);
				cMRoutingVO.setUserDepartment(routedToName);
				/*String Name = caseRecord.getCaseAssignedToWorkUnit()
						.getEnterpriseUser().getFirstName()
						+ " "
						+ caseRecord.getCaseAssignedToWorkUnit()
								.getEnterpriseUser().getLastName();*/
				
				cMRoutingVO.setAssignRoutedToName(routedToName);

				/** Added For Defect ID ESPRD00330104 */
				cMRoutingVO.setRoutedToName(routedToName);
				cMRoutingVO.setAssignedUserName(routedToName);
				cMRoutingVO.setWorkUnit(logCaseDataBean.getRoutingVO()
						.getDeptName());
				/** Ends */

				busineesUnitMap = logCaseDataBean.getBusineesUnitMap();
				
				if (busineesUnitMap != null && !(busineesUnitMap.isEmpty())) {
					userDept = (String) busineesUnitMap.get(workUnitSK
							.toString());
				}
				workUnitMap = logCaseDataBean.getWorkUnitMap();
				
				Long lobHighSk = caseRecord.getLobHierarchy()
						.getLineOfBusinessHierarchySK();
				if (workUnitMap != null && !(workUnitMap.isEmpty())) {
					userDept = (String) workUnitMap.get(lobHighSk);
					
				}
				cMRoutingVO.setWorkUnit(userDept);
				if (logCaseDataBean.getDeptMap() != null
						&& logCaseDataBean.getUserDeptName() != null) {
					String userDeptName = (String) logCaseDataBean.getDeptMap()
							.get(logCaseDataBean.getUserDeptName());
					cMRoutingVO.setUserDeptName(userDeptName);
				}
				
			}
		}
        
        else if (caseRouting.getRoutedToWorkUnit() != null
				&& caseRouting.getRoutedToWorkUnit().getWorkUnitTypeCode()
						.equals("B")) {
			Long workUnitSK = caseRouting.getRoutedToWorkUnit().getWorkUnitSK();
			if (workUnitSK != null) {
				deptMap =  logCaseDataBean.getWorkUnitMap();
				routedToName = (String) deptMap.get(workUnitSK);
				/*String userId = helper.getUserIDByWUSK(workUnitSK.toString());
				logger.debug("--useridWUmap 2::"+userId);*/
				String userId = helper.getUserIDByWUSK2(workUnitSK.toString());
				logger.debug("++useridWUmap 2::"+userId);
				/*routedToName = helper.getUserName(caseRouting
						.getRoutedToWorkUnit().getEnterpriseUser()
						.getUserWorkUnitSK().toString());*/
				/*if (userId != null){
					routedToName = helper.getUserName(workUnitSK.toString());
					logger.debug("--usermap 3::"+routedToName);
				}*/
				EnterpriseUser routeToUser = caseRouting.getRoutedToWorkUnit().getEnterpriseUser();
				if (routeToUser != null){
					//Commented for getting Username from Map
					//routedToName = helper.getUserName(workUnitSK.toString());
					routedToName = getUserName(caseRouting.getRoutedToWorkUnit());
					logger.debug("++usermap 3::"+routedToName);
				}
				cMRoutingVO.setRoutedToWorkUnitSK(workUnitSK);
				cMRoutingVO.setRoutedTo(routedToName);
				cMRoutingVO.setAssignedTo(routedToName);
				cMRoutingVO.setUserDepartment(routedToName);
				cMRoutingVO.setAssignRoutedToName("");

				/** Added For Defect ID ESPRD00330104 */ 
				
				cMRoutingVO.setRoutedToName(routedToName);
				cMRoutingVO.setAssignedUserName(routedToName);
				/** Ends */
	            deptMap = logCaseDataBean.getDeptMap();
	            userDept = (String) deptMap.get(workUnitSK.toString());
	            cMRoutingVO.setUserDeptName(userDept);
				
                busineesUnitMap = logCaseDataBean.getBusineesUnitMap();
                
                if(busineesUnitMap != null && !(busineesUnitMap.isEmpty())){
                userDept = (String) busineesUnitMap.get(workUnitSK.toString());
                }
                workUnitMap = logCaseDataBean.getWorkUnitMap();
                Long lobHighSk = caseRecord.getLobHierarchy().getLineOfBusinessHierarchySK();
                if(workUnitMap != null && !(workUnitMap.isEmpty())){
                userDept = (String) workUnitMap.get(lobHighSk);
                }
                cMRoutingVO.setWorkUnit(userDept);
                String userDeptName = (String)logCaseDataBean.getDeptMap().get(logCaseDataBean.getUserDeptName());
    			cMRoutingVO.setUserDeptName(userDeptName); 
			}

		}

        if (caseRouting.getRoutedByWorkUnit() != null
                && caseRouting.getRoutedByWorkUnit().getEnterpriseUser() != null)
        {
            cMRoutingVO.setRoutedByWorkUntiSK(caseRouting.getRoutedByWorkUnit()
                    .getEnterpriseUser().getUserWorkUnitSK());
			//routedByName = caseRouting.getRoutedByWorkUnit().getEnterpriseUser().getUserID(); // Commented for the defect id ESPRD00702153_30NOV2011
            routedByName = getName(caseRouting.getRoutedByWorkUnit(), "U");
            /*routedByName = helper.getUserName(caseRouting.getRoutedByWorkUnit()
                    .getEnterpriseUser().getUserWorkUnitSK().toString());*/ // Commented for the defect id ESPRD00702153_29NOV2011
           // Begin - Added the code for the defect id ESPRD00702153_30NOV2011
           String userId = caseRouting.getRoutedByWorkUnit().getEnterpriseUser().getUserID(); // Added for the defect id ESPRD00702153_29NOV2011
           cMRoutingVO.setRoutedBySK(caseRouting.getRoutedByWorkUnit().getWorkUnitSK().toString()); // Added for the defect id ESPRD00702153_30NOV2011
           cMRoutingVO.setRoutedBy(routedByName);
           cMRoutingVO.setRoutedByName(routedByName);
            cMRoutingVO.setRoutedByUserID(userId);
           // End - Added the code for the defect id ESPRD00702153_30NOV2011
        }
        cMRoutingVO.setAddThisRecordToMyWatchlist(caseRouting
                .getWatchListIndicator().booleanValue());
        cMRoutingVO.setVersionNo(caseRouting.getVersionNo());
        if (caseRouting.getAuditUserID() != null)
        {
            cMRoutingVO.setAuditUserID(caseRouting.getAuditUserID());
        }
        if (caseRouting.getAuditTimeStamp() != null)
        {
            cMRoutingVO.setAuditTimeStamp(caseRouting.getAuditTimeStamp());
        }
        if (caseRouting.getAddedAuditUserID() != null)
        {
            cMRoutingVO.setAddedAuditUserID(caseRouting.getAddedAuditUserID());
        }
        if (caseRouting.getAddedAuditTimeStamp() != null)
        {
            cMRoutingVO.setAddedAuditTimeStamp(caseRouting
                    .getAddedAuditTimeStamp());
        }
        return cMRoutingVO;
    }

    /**
     * This method is used to convert the Case Alets Do to VO
     * 
     * @param setOfCaseAlertDOs
     *            holds the set of Alert DOs
     * @return it returns the list of Alert VOs
     */
    public List convertAlertDOToVO(Set setOfCaseAlertDOs)
    {
    	if(logger.isInfoEnabled()){
    	logger.info(" Case Alerts  :" + setOfCaseAlertDOs.size());
    	}
        List listOfCaseAlertVOs = new ArrayList();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();//CR_ESPRD00373565_LOGCASE_23JUL2010

        for (Iterator iter = setOfCaseAlertDOs.iterator(); iter.hasNext();)
        {
            Alert caseAlertDO = (Alert) iter.next();
            AlertVO alertVO = convertAlertDOToVO(caseAlertDO);
            listOfCaseAlertVOs.add(alertVO);
            //  Condition Added for the Defect : ESPRD00790505 Start
            if(logCaseDataBean.isEnableAuditLogs()){
            	//CR_ESPRD00373565_LOGCASE_23JUL2010
            contactManagementHelper.createVOAuditKeysList(caseAlertDO,alertVO);
            doAuditKeyListOperationForAlerts(alertVO);
            //EOF CR_ESPRD00373565_LOGCASE_23JUL2010
            }
           //  Condition Added for the Defect : ESPRD00790505 End
        }
        return listOfCaseAlertVOs;
    }

    /**
     * This method is used to convert the Case Alets Do to VO
     * 
     * @param alert
     *            holds the Alet DO object
     * @return it returns the AlertVO object
     */
    public AlertVO convertAlertDOToVO(Alert alert)
    {
    	if(logger.isInfoEnabled()){
    		logger.info(" convertAlertDOToVO Method start ");
    	}
        ContactManagementHelper helper = new ContactManagementHelper();
        AlertVO alertVO = new AlertVO();
        if (alert.getAlertSK() != null)
        {
            alertVO.setAlertSK(alert.getAlertSK());
        }
        if (alert.getDueDate() != null)
        {
            alertVO.setDueDateStr(ContactManagementHelper.dateConverter(alert
                    .getDueDate()));
            alertVO.setDueDate(alert.getDueDate());
        }
        if (alert.getAlertTypeCode() != null)
        {
            alertVO.setAlertType(alert.getAlertTypeCode());
            alertVO.setAlertTypeDesc(ContactManagementHelper
                    .setShortDescription(FunctionalAreaConstants.GENERAL,
                            ReferenceServiceDataConstants.G_ALERT_TY_CD,
                            alertVO.getAlertType()));
        }
        if (alert.getDescription() != null)
        {
            alertVO.setDescription(alert.getDescription());
        }
        /* Added code to fix defect ESPRD00357233 starts */
        if (alert.getEnterpriseUser() != null)
        {       
            alertVO.setNotifyViaAlert(String.valueOf(alert
                    .getEnterpriseUser().getUserID()));          
        
            /*alertVO.setNotifyUserName(helper.getUserNameByID(alert.getEnterpriseUser().getUserID()));            
            logger.debug("--useridmap NotifyUserName--"+alertVO.getNotifyUserName());*/
            if(alert.getEnterpriseUser()!=null){
            	alertVO.setNotifyUserName(getNameforUserId(alert.getEnterpriseUser()));
            	
            	logger.debug("++useridmap NotifyUserName--"+alertVO.getNotifyUserName());
            }
            
        }
        /* Added code to fix defect ESPRD00357233 End */
        
       
        if (alert.getAlertStatusCode() != null)
        {
            alertVO.setStatus(alert.getAlertStatusCode());
            alertVO.setStatusDesc(ContactManagementHelper.setShortDescription(
                    FunctionalAreaConstants.GENERAL,
                    ReferenceServiceDataConstants.G_ALERT_STAT_CD, alertVO
                            .getStatus()));
           
        }
        if (alert.getEnterpriseUser() != null)
        {
            alertVO.setRcvgUserID(alert.getEnterpriseUser().getUserID());
        }
        if (alert.getStepOrderNumber() != null)
        {
            
            alertVO.setStepOrderNo(alert.getStepOrderNumber().toString());
        }
        if (alert.getCmCaseStepCode() != null)
        {
            
            alertVO.setActCMCaseStepCode(alert.getCmCaseStepCode());
        }
       
        if(alert.getCaseStepSeqNum()!=null){ // added for UC-PGM-CRM-018.7_ESPRD00382416_30JAN10
        	
        	alertVO.setCaseStepSeqNum(alert.getCaseStepSeqNum());        	
        }//EOF UC-PGM-CRM-018.7_ESPRD00382416_30JAN10
        
        if (alert.getCmCaseEventCode() != null)
        {
            
            alertVO.setActCMCaseEventCode(alert.getCmCaseEventCode());
        }

        if(alert.getCaseEventSeqNum()!=null){//added for UC-PGM-CRM-018.4_ESPRD00382315_16FEB2010 
        	
        	alertVO.setCaseEventSeqNum(alert.getCaseEventSeqNum());        	
        }//EOf UC-PGM-CRM-018.4_ESPRD00382315_16FEB2010
        
        if (alert.getCaseTypeSK() != null)
        {
            
            alertVO.setCaseTypeSK(alert.getCaseTypeSK());
        }
        alertVO.setVersionNo(alert.getVersionNo());
        if (alert.getAuditUserID() != null)
        {
            alertVO.setAuditUserID(alert.getAuditUserID());
        }
        if (alert.getAuditTimeStamp() != null)
        {
            alertVO.setAuditTimeStamp(alert.getAuditTimeStamp());
        }
        if (alert.getAddedAuditUserID() != null)
        {
            alertVO.setAddedAuditUserID(alert.getAddedAuditUserID());
        }
        if (alert.getAddedAuditTimeStamp() != null)
        {
            alertVO.setAddedAuditTimeStamp(alert.getAddedAuditTimeStamp());
        }
        return alertVO;

    }
    
    /**
     * This method is used to convert the Actual CMCaseSteps DO to VO
     * 
     * @param setOfCaseStepsDOs
     *            holds the set of ActCMCaseStep DO object
     * @return it returns list of CaseStepsVOs
     */
    public List convertCMCaseStepsDOToVO(Set setOfCaseStepsDOs)
    {
    	if(logger.isInfoEnabled()){
    	logger.info(" Case steps size :" + setOfCaseStepsDOs.size());
    	}
        List listOfCaseStepVOs = new ArrayList();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        for (Iterator iter = setOfCaseStepsDOs.iterator(); iter.hasNext();)
        {
            ActCMCaseStep caseStepDO = (ActCMCaseStep) iter.next();
            CaseStepsVO caseStepVO = convertCaseStepsDOToVO(caseStepDO);
            //  Condition Added for the Defect : ESPRD00790505 Start
            if(logCaseDataBean.isEnableAuditLogs()){
            	try{//CR_ESPRD00373565_LogCase_05AUG2010
                contactManagementHelper.createVOAuditKeysList(caseStepDO,caseStepVO);
                if(caseStepDO.getAssignWorkUnit() != null){
                	contactManagementHelper.createVOAuditKeysList(caseStepDO.getAssignWorkUnit(),caseStepVO);	
                }
                if(caseStepDO.getAlertWorkUnit() != null){
                	contactManagementHelper.createVOAuditKeysList(caseStepDO.getAlertWorkUnit(),caseStepVO);	
                }         
                doAuditKeyListOperationForCaseSteps(caseStepVO);
                
            }catch(Exception exception){
            	if(logger.isErrorEnabled()){
            	logger.error(exception.getMessage(), exception);
            	} //  Condition Added for the Defect : ESPRD00790505 End
            }//EOF CR_ESPRD00373565_LogCase_05AUG2010
            }
            listOfCaseStepVOs.add(caseStepVO);
        }
        return listOfCaseStepVOs;
    }

    /**
     * This method is used to convert the Actual CMCaseSteps DO to VO
     * 
     * @param caseStep
     *            it holds the ActCMCaseStep object
     * @return it returns CaseStepsVO object
     */
    public CaseStepsVO convertCaseStepsDOToVO(ActCMCaseStep caseStep)
    {
        CaseStepsVO caseStepsVO = new CaseStepsVO();
        ContactManagementHelper helper = new ContactManagementHelper();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        
        
        if(caseStep.getCaseSK()!= null)
        {
          caseStepsVO.setCaseSK(String.valueOf(caseStep.getCaseSK()));
         
         }
        
        if(caseStep.getCaseTypeSK()!= null)
        {
          caseStepsVO.setCaseTypeSK(String.valueOf(caseStep.getCaseTypeSK())); 
         
        }
        
        if (caseStep.getCaseStepSeqNum() != null)
        {
            caseStepsVO.setCaseStepSeqNum(caseStep.getCaseStepSeqNum());
            //CR_ESPRD00463663_LogCase_Changes_22MAY2010
            caseStepsVO.setCaseStepSeqNumStr(String.valueOf(caseStep.getCaseStepSeqNum().intValue()));            
            //EOF CR_ESPRD00463663_LogCase_Changes_22MAY2010

            
        }
        
       
        
        if (caseStep.getStepOrderNumber() != null)
        {
            caseStepsVO.setOrder(caseStep.getStepOrderNumber().toString());
           
        }
        if (caseStep.getCmCaseStepCode() != null)
        {
            caseStepsVO.setCaseStepCode(caseStep.getCmCaseStepCode());
            caseStepsVO.setDescription(caseStep.getCmCaseStepCode());
            caseStepsVO.setCaseStepsDescription(helper.getDescriptionFromVV(caseStep.getCmCaseStepCode(),logCaseDataBean.getCaseStepCodeList()));
        }
       /* System.err.println(" caseStep.getAssignWorkUnit().getWorkUnitSK().toString() : "+caseStep.getAssignWorkUnit().getWorkUnitSK().toString());
        System.err.println(" logCaseDataBean.getUserList() : "+logCaseDataBean.getRouteAllDeptUsersList());
        */
        if (caseStep.getAssignWorkUnit() != null)
        {
            caseStepsVO.setRouteTo(caseStep.getAssignWorkUnit().getWorkUnitSK()
                    .toString());
            caseStepsVO.setRouteToDescription(helper.getDescriptionFromVV(caseStep.getAssignWorkUnit().getWorkUnitSK().toString(),logCaseDataBean.getRouteAllDeptUsersList()));
        }
        if (caseStep.getStepStatusCode() != null)
        {
            caseStepsVO.setStatus(caseStep.getStepStatusCode());
            caseStepsVO.setStatusDescription(helper.getDescriptionFromVV(caseStep.getStepStatusCode(),logCaseDataBean.getStepStatusList()));
        }
        if (caseStep.getStartDate() != null)
        {
            caseStepsVO.setDateStartedStr(ContactManagementHelper
                    .dateConverter(caseStep.getStartDate()));
            caseStepsVO.setDateStarted(caseStep.getStartDate());
        }
		//ADDED FOR THE DEFECT ESPRD00741609
        if(caseStep.getStepOrderNumber().toString().equals("1"))
        {
        	
        	caseStepsVO.setDateStartedStr(ContactManagementHelper
                    .dateConverter(caseStep.getAddedAuditTimeStamp()));
        	
        	
        }
			//ADDED END FOR THE DEFECT ESPRD00741609
        if (caseStep.getDaysToCompleteNumber() != null)
        {
            caseStepsVO.setExpectedDaysToComplete(String.valueOf(caseStep
                    .getDaysToCompleteNumber().intValue()));
        }
        if (caseStep.getCompletBasedOnColName() != null)
        {
            caseStepsVO
                    .setCompletedBasedOn(caseStep.getCompletBasedOnColName());
            caseStepsVO.setCompletionBasedOnDescription(helper.getDescriptionFromVV(caseStep.getCompletBasedOnColName(),logCaseDataBean.getStepCompBasedOn()));
        }
        if (caseStep.getExpectedCompletionDate() != null)
        {
            caseStepsVO.setExpectedCompletionDateStr(ContactManagementHelper
                    .dateConverter(caseStep.getExpectedCompletionDate()));
            caseStepsVO.setExpectedCompletionDate(caseStep
                    .getExpectedCompletionDate());
        }
     // Begin - modified the code for the Defect ESPRD00709364
        if (caseStep.getCompletionDate() != null)
        {
        	caseStepsVO.setCompletionDateStr(ContactManagementHelper
                .dateConverter(caseStep.getCompletionDate()));
        	caseStepsVO.setCompletionDate(caseStep.getCompletionDate());
        }
     // End - modified the code for the Defect ESPRD00709364
        if (caseStep.getAlertWorkUnit() != null) {
			caseStepsVO.setNotifyViaAlert(String.valueOf(caseStep.getAlertWorkUnit().getWorkUnitSK()));
			//Modified for the defect ESPRD00937537
			/*caseStepsVO.setNotifyAlertDescription(helper.getDescriptionFromVV(
					caseStep.getAlertWorkUnit().getWorkUnitSK().toString(), logCaseDataBean.getStepUserList()));*/
			caseStepsVO.setNotifyAlertDescription(getNameforUserId(caseStep.getEnterpriseUser()));
		}
        if (caseStep.getAlertBasedOnColName() != null)
        {
            caseStepsVO.setAlertBasedOn(caseStep.getAlertBasedOnColName());
            caseStepsVO.setAlertBasedOnStr(caseStep.getAlertBasedOnColName());
        }
        if (caseStep.getSendAlertDaysCode() != null)
        {
            caseStepsVO.setSendAlertDays(caseStep.getSendAlertDaysCode());
            //Added for defect ESPRD00538641 starts
            caseStepsVO.setSendAlertDaysStr(caseStep.getSendAlertDaysCode());
            //defect ESPRD00538641 ends
        }
        if (caseStep.getBeforeAfterCode() != null)
        {
            caseStepsVO.setBeforeOrAfterInd(caseStep.getBeforeAfterCode());
        }
        if (caseStep.getLetterTemplate() != null)
        {
            caseStepsVO.setTemplate(caseStep.getLetterTemplate()
                    .getLetterTemplateKeyData());
        }
        caseStepsVO.setVersionNo(caseStep.getVersionNo());
        if (caseStep.getAuditUserID() != null)
        {
            caseStepsVO.setAuditUserID(caseStep.getAuditUserID());
        }
        if (caseStep.getAuditTimeStamp() != null)
        {
            caseStepsVO.setAuditTimeStamp(caseStep.getAuditTimeStamp());
        }
        if (caseStep.getAddedAuditUserID() != null)
        {
            caseStepsVO.setAddedAuditUserID(caseStep.getAddedAuditUserID());
        }
        if (caseStep.getAddedAuditTimeStamp() != null)
        {
            caseStepsVO.setAddedAuditTimeStamp(caseStep
                    .getAddedAuditTimeStamp());
        }
        return caseStepsVO;
    }

    /**
     * This method is used to convert the Actual CMCaseEvent DO to VO
     * 
     * @param setOfCaseEventsDOs
     *            it holds the ActCMCaseStep object
     * @return it returns CaseEventsVO object
     */
    public List convertCMCaseEventsDOToVO(Set setOfCaseEventsDOs)
    {
    	if(logger.isInfoEnabled()){
    	logger.info(" Case Events  :" + setOfCaseEventsDOs.size());
    	}
        List listOfCaseEventVOs = new ArrayList();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        for (Iterator iter = setOfCaseEventsDOs.iterator(); iter.hasNext();)
        {
        	ActCMCaseEvent caseEventDO = (ActCMCaseEvent) iter.next();
            CaseEventsVO caseEventVO = convertCaseEventsDOToVO(caseEventDO);
            //  Condition Added for the Defect : ESPRD00790505 Start
            if(logCaseDataBean.isEnableAuditLogs()){
            	try{//CR_ESPRD00373565_LogCase_05AUG2010
                contactManagementHelper.createVOAuditKeysList(caseEventDO,caseEventVO);
                if(caseEventDO.getEnterpriseUser() != null){
                	contactManagementHelper.createVOAuditKeysList(caseEventDO.getEnterpriseUser(),caseEventVO);	
                }
                
                if (caseEventDO.getCaseEventAppointments() != null
    					&& !caseEventDO.getCaseEventAppointments().isEmpty()) {
    				Iterator caseEventAppointmentsItr = caseEventDO
    						.getCaseEventAppointments().iterator();
    				try {
    					while (caseEventAppointmentsItr.hasNext()) {
    						CaseEventAppointment caseEventAppointment = (CaseEventAppointment) caseEventAppointmentsItr.next();
    						if (caseEventAppointment != null) {
    							contactManagementHelper.createVOAuditKeysList(caseEventAppointment, caseEventVO);
    						}
    					}
    				} catch (Exception exception) {
    					if(logger.isErrorEnabled()){
    					logger.error(exception.getMessage(), exception);
    					}
    				}

    			}
            
                doAuditKeyListOperationForCaseEvents(caseEventVO);

            }catch(Exception exception){
            	if(logger.isErrorEnabled()){
            	logger.error(exception.getMessage(), exception);
            	}
            }//EOF CR_ESPRD00373565_LogCase_05AUG2010
          }
			//  Condition Added for the Defect : ESPRD00790505 End
           listOfCaseEventVOs.add(caseEventVO);
        }
        return listOfCaseEventVOs;
    }

    /**
     * This method is used to convert the CaseAttachCrossReference DO to VO
     * 
     * @param setOfCaseAttachDOs
     *            it holds the CaseAttachCrossReference object
     * @return it returns AttachmentVO object
     */
    public List convertCaseAttachmentsDOToVO(Set setOfCaseAttachDOs)
    {
    	if(logger.isInfoEnabled()){
    	logger.info(" Case Attachments  :" + setOfCaseAttachDOs.size());
    	}
        List listOfCaseAttachVOs = new ArrayList();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();//CR_ESPRD00373565_LOGCASE_23JUL2010
        Attachment attachmentDO=null;
        
        for (Iterator iter = setOfCaseAttachDOs.iterator(); iter.hasNext();)
        {
            CaseAttachCrossReference attachmentCrossDO = (CaseAttachCrossReference) iter
                    .next();
            AttachmentVO attachmentVO = convertCaseAttachmentDOToVO(attachmentCrossDO);
            listOfCaseAttachVOs.add(attachmentVO);
          // Condition Added for the Defect : ESPRD00790505 Start
            if(logCaseDataBean.isEnableAuditLogs()){
            	//CR_ESPRD00373565_LOGCASE_23JUL2010
            try{
            if(attachmentCrossDO!=null && attachmentCrossDO.getAttachment()!=null){
            	
				attachmentDO = attachmentCrossDO.getAttachment();
				if(attachmentDO != null){
					contactManagementHelper.createVOAuditKeysList(attachmentDO,attachmentVO);
			        doAuditKeyListOperationForAttachment(attachmentVO);				
				}
            }
            }catch(Exception e){
            	if(logger.isErrorEnabled()){
            	logger.error("convertCaseAttachmentsDOToVO(Set):",e);
            	} //  Condition Added for the Defect : ESPRD00790505 End
            }
            }
            //EOF CR_ESPRD00373565_LOGCASE_23JUL2010
        }
        return listOfCaseAttachVOs;
    }

    /**
     * This method convertCaseLinksDOToVO.
     * 
     * @param setOfCaseLinks
     *            Takes set of case links.
     * @return List
     */
    public List convertCaseLinksDOToVO(Set setOfCaseLinks)
    {
    	if(logger.isInfoEnabled()){
        logger.info("convertCaseLinksDOToVO=====");
    	}
    	List listOfCaseLinks = new ArrayList();
        CaseRecord caseRecord = null;
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        for (Iterator iter = setOfCaseLinks.iterator(); iter.hasNext();)
        {
            CaseLink caseLinkDO = (CaseLink) iter.next();
            caseRecord = caseLinkDO.getPriorCaseRecord();
            AssociatedCaseVO caseVO = convertCaseLinkDOToVO(caseRecord);
            caseVO.setAddedAuditTimeStamp(caseLinkDO.getAddedAuditTimeStamp());
            caseVO.setAddedAuditUserID(caseLinkDO.getAddedAuditUserID());
            caseVO.setAuditTimeStamp(caseLinkDO.getAuditTimeStamp());
            caseVO.setAuditUserID(caseLinkDO.getAuditUserID());
            caseVO.setVersionNo(caseLinkDO.getVersionNo());
          //  Condition Added for the Defect : ESPRD00790505
            if(logCaseDataBean.isEnableAuditLogs()){
            	try{//CR_ESPRD00373565_LogCase_05AUG2010
                if(caseRecord != null){
                	contactManagementHelper.createVOAuditKeysList(caseRecord,caseVO);
                	if(caseRecord.getCaseType() != null){
                		contactManagementHelper.createVOAuditKeysList(caseRecord.getCaseType(),caseVO);	
                	}            	            	
                    doAuditKeyListOperationForAssociatedCase(caseVO);
                }

            }catch(Exception e){
            	if(logger.isErrorEnabled()){
            	logger.error("convertCaseLinksDOToVO(Set):",e);
            	} //  Condition Added for the Defect : ESPRD00790505 End
            }
            }//EOF CR_ESPRD00373565_LogCase_05AUG2010
            
            listOfCaseLinks.add(caseVO);
        }
        return listOfCaseLinks;
    }

    private SearchCorrespondenceDataBean searchCorrespondenceDataBean;
        
    private CaseSearchDataBean caseSearchDataBean;
 
	// Moved to ContactManagementConstants.java
    //public static final String CASE_SEARCH_DATA_BEAN = "caseSearchDataBean";
    
    //public static final String SEARCH_CORRESPONDENCE_DATA_BEAN = "cs_searchCorrespondenceDataBean";
    /**
     * This method convertCaseLinkDOToVO.
     * 
     * @param caseRecord
     *            Takes caseRecord as param.
     * @return AssociatedCaseVO
     */
    public AssociatedCaseVO convertCaseLinkDOToVO(CaseRecord caseRecord)
    {
        AssociatedCaseVO associatedCaseVO = new AssociatedCaseVO();
        ContactManagementHelper helper = new ContactManagementHelper();
//        CaseSearchDataBean caseSearchDataBean = new CaseSearch?DataBean();
        CaseSearchDataBean caseSearchDataBean = (CaseSearchDataBean) getDataBean(ContactManagementConstants.CASE_SEARCH_DATA_BEAN);
        	
        
        if (caseRecord != null)
        {
            associatedCaseVO.setCaseID(caseRecord.getCaseSK().toString());
            associatedCaseVO.setCreatedDate(caseRecord.getOpenDate());
            associatedCaseVO.setCreatedDateStr(ContactManagementHelper
                    .dateConverter(caseRecord.getOpenDate()));
            associatedCaseVO.setStatus(helper.getDescriptionFromVV(caseRecord.getCaseStatusCode(),caseSearchDataBean.getStatus()));
           associatedCaseVO.setStatusstr(helper.getDescriptionFromVV(caseRecord.getCaseStatusCode(),caseSearchDataBean.getStatus()));
            
            if (caseRecord.getCaseType() != null)
            {
                associatedCaseVO.setCaseType(caseRecord.getCaseType()
                        .getDescription());
                associatedCaseVO.setBusinessUnitCaseTypeCode(caseRecord.getCaseType().getBusinessUnitCaseTypeCode()); //UC-PGM-CRM-018_ESPRD00388742_29JAN10
            }
        }
        return associatedCaseVO;
    }

    /**
     * This method convertCaseCRsDOToVO.
     * 
     * @param setOfCaseCRs
     *            Takes set of CaseCRs.
     * @return List
     */
    public List convertCaseCRsDOToVO(Set setOfCaseCRs)
    {
        List listOfCaseCRs = new ArrayList();
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        for (Iterator iter = setOfCaseCRs.iterator(); iter.hasNext();)
        {
            CaseCorrespondence caseCorrespondenceDO = (CaseCorrespondence) iter
                    .next();
            Correspondence corr = caseCorrespondenceDO.getCorrespondence();
            AssociatedCorrespondenceVO caseCRVO = convertCaseCRDOToVO(corr);
            caseCRVO.setCaseCRVersionNumber(Integer.valueOf(caseCorrespondenceDO.getVersionNo()));  /*FIND BUGS_FIX*/
          //  Condition Added for the Defect : ESPRD00790505 Start
            if(logCaseDataBean.isEnableAuditLogs()){
            try{//CR_ESPRD00373565_LogCase_05AUG2010
                if(corr != null){
                	contactManagementHelper.createVOAuditKeysList(corr,caseCRVO);
                    doAuditKeyListOperationForAssociatedCRs(caseCRVO);
                }
     
            }catch(Exception e){
            	if(logger.isErrorEnabled()){
            	logger.error("convertCaseCRsDOToVO(Set):",e);
            	} //  Condition Added for the Defect : ESPRD00790505 End
            }
            }//EOF CR_ESPRD00373565_LogCase_05AUG2010
            listOfCaseCRs.add(caseCRVO);
            
        }
        return listOfCaseCRs;
    }

    /**
     * This method is used to convert CaseCrDOToVo.
     * 
     * @param correspondence
     *            Takes Correspondence
     * @return AssociatedCorrespondenceVO
     */
    public AssociatedCorrespondenceVO convertCaseCRDOToVO(
            Correspondence correspondence)
    {
    	 String subjectStr=null;
        AssociatedCorrespondenceVO correspondenceVO = new AssociatedCorrespondenceVO();
       //commented for unused variables
        //ContactManagementHelper helper = new ContactManagementHelper();
        searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) getDataBean(ContactManagementConstants.SEARCH_CORRESPONDENCE_DATA_BEAN);
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        if (correspondence != null)
        {
            correspondenceVO.setCorrespondenceRecordNumber(correspondence
                    .getCorrespondenceSK().toString());
            if (correspondence.getOpenDate() != null)
            {
                correspondenceVO.setOpenDate(ContactManagementHelper
                        .dateConverter(correspondence.getOpenDate()));
            }
            correspondenceVO.setStatus(ReferenceServiceDelegate
        			.getReferenceSearchShortDescription(
        					FunctionalAreaConstants.CONTACT_MGMT,
        					ContactManagementConstants.CORR_STATUS,
        					Long.valueOf(78),
        					correspondence.getStatusCode()));
            
            correspondenceVO.setStatusstr(ReferenceServiceDelegate
        			.getReferenceSearchShortDescription(
        					FunctionalAreaConstants.CONTACT_MGMT,
        					ContactManagementConstants.CORR_STATUS,
        					Long.valueOf(78),
        					correspondence.getStatusCode()));      
            if (correspondence.getCorrespondenceCategory() != null)
            {
                correspondenceVO.setCategory(correspondence
                        .getCorrespondenceCategory().getDescription());
            }
            correspondenceVO.setSubject(ReferenceServiceDelegate
        			.getReferenceSearchShortDescription(
        					FunctionalAreaConstants.CONTACT_MGMT,
        					ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK,
        					new Long(1078),
        					correspondence.getSubjectCode()));  
           
            subjectStr= ReferenceServiceDelegate
			.getReferenceSearchShortDescription(
					FunctionalAreaConstants.CONTACT_MGMT,
					ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK,
					new Long(1078),
					correspondence.getSubjectCode());
            
          //  correspondenceVO.setSubjectstr(helper.getDescriptionFromVV(correspondence.getSubjectCode(),logCaseDataBean.getSubjectVVList()));
            correspondenceVO.setSubjectstr(subjectStr);
            
            correspondenceVO.setVersionNo(correspondence.getVersionNo());            
            correspondenceVO.setAddedAuditTimeStamp(correspondence
                    .getAddedAuditTimeStamp());
            correspondenceVO.setAddedAuditUserID(correspondence
                    .getAddedAuditUserID());
            correspondenceVO.setAuditTimeStamp(correspondence
                    .getAuditTimeStamp());
            correspondenceVO.setAuditUserID(correspondence.getAuditUserID());
          
        }
        return correspondenceVO;
    }   
   

    /**
     * This method convert90DaysCaseRecords.
     * 
     * @param existingCaseRecordList
     *            Takes existingCaseRecordList
     * @return List
     */
    public List convert90DaysCaseRecords(List existingCaseRecordList)
    {
        List existingCaseList = new ArrayList();

        if (existingCaseRecordList != null && !existingCaseRecordList.isEmpty())
        {
            for (Iterator iter = existingCaseRecordList.iterator(); iter
                    .hasNext();)
            {
                CaseRecord caseRecordDO = (CaseRecord) iter.next();
                AssociatedCaseVO caseVO = convertCaseLinkDOToVO(caseRecordDO);
                existingCaseList.add(caseVO);
            }
        }
        return existingCaseList;
    }

    /**
     * This method convert90DaysCR.
     * 
     * @param existingCRsList
     *            Takes existingCRsList
     * @return List
     */
    public List convert90DaysCR(List existingCRsList)
    {
        List existingCRList = new ArrayList();
        if (existingCRsList != null && !existingCRsList.isEmpty())
        {
            for (Iterator iter = existingCRsList.iterator(); iter.hasNext();)
            {
                Correspondence correspondence = (Correspondence) iter.next();
                AssociatedCorrespondenceVO correspondenceVO = convertCaseCRDOToVO(correspondence);
                existingCRList.add(correspondenceVO);
            }
        }
        return existingCRList;
    }

    /**
     * This method is used to convert the Actual CMCaseEvent DO to VO
     * 
     * @param actCMCaseEvent
     *            it holds the ActCMCaseEvent object
     * @return CaseEventsVO : it returns CaseEventsVO object
     */
    public CaseEventsVO convertCaseEventsDOToVO(ActCMCaseEvent actCMCaseEvent)
    {
        CaseEventsVO caseEventsVO = new CaseEventsVO();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        ContactManagementHelper helper = new ContactManagementHelper();
        caseEventsVO.setCreateDate(actCMCaseEvent.getAddedAuditTimeStamp());
        caseEventsVO.setCreateDateStr(ContactManagementHelper
                .dateConverter(actCMCaseEvent.getAddedAuditTimeStamp()));

      
        
        if(actCMCaseEvent.getCaseSK()!= null)
        {
        	caseEventsVO.setCaseSK(String.valueOf(actCMCaseEvent.getCaseSK()));
        }
        if(actCMCaseEvent.getCaseEventSeqNum()!=null)
        {
        	caseEventsVO.setCaseEventSeqNum(actCMCaseEvent.getCaseEventSeqNum());
        	//CR_ESPRD00463663_LogCase_Changes_22MAY2010
        	caseEventsVO.setCaseEventSeqNumStr(String.valueOf(actCMCaseEvent.getCaseEventSeqNum().intValue()));
        	//EOF CR_ESPRD00463663_LogCase_Changes_22MAY2010
        	
        }
        if(actCMCaseEvent.getCaseTypeSK()!= null)
        {
        	 caseEventsVO.setCaseTypeSK(String.valueOf(actCMCaseEvent.getCaseTypeSK()));
        	
        }
        
      
        if (actCMCaseEvent.getEventDate() != null)
        {
        	  	
        	caseEventsVO.setTime(ContactManagementHelper.timeConverter(actCMCaseEvent.getEventDate()).trim()); 
        	Format formatter;
            formatter = new SimpleDateFormat("hh:mm a");
            String strDate = formatter.format(actCMCaseEvent.getEventDate());            
            caseEventsVO.setAmPM(strDate.substring(strDate.lastIndexOf(" ")).trim());//fixed by rajasekhar 
            caseEventsVO.setEventDate(actCMCaseEvent.getEventDate());
            caseEventsVO.setEventDateStr(ContactManagementHelper
                    .dateConverter(actCMCaseEvent.getEventDate()));
        }
        if (actCMCaseEvent.getCmCaseEventCode() != null)
        {
        	
        	
        	 caseEventsVO.setEventTypeCd(actCMCaseEvent.getCmCaseEventCode());
             caseEventsVO.setEventTypeCdDesc(actCMCaseEvent.getCmCaseEventCode());
             caseEventsVO.setCaseEventsDescription(helper.getDescriptionFromVV(actCMCaseEvent
				.getCmCaseEventCode(),logCaseDataBean.getEventTypeList()));
        	
        }
        if (actCMCaseEvent.getDescription() != null)
        {
            caseEventsVO.setDesc(actCMCaseEvent.getDescription());
        }
    
        if (actCMCaseEvent.getEnterpriseUser() != null)
        {
            caseEventsVO.setNotifyViaAlert(String.valueOf(actCMCaseEvent
                    .getEnterpriseUser().getUserID()));
            caseEventsVO.setNotifyViaAlertName(helper
                    .getDescriptionFromVV(actCMCaseEvent.getEnterpriseUser()
                            .getUserID(),logCaseDataBean.getEventNotifyList()));
        }
        if (actCMCaseEvent.getAlertBasedOnColName() != null)
        {
        	
			caseEventsVO.setAlertBasedOn(actCMCaseEvent
					.getAlertBasedOnColName());
			caseEventsVO.setAlertBasedOnDesc(actCMCaseEvent
					.getAlertBasedOnColName());
			caseEventsVO.setAlertBasedOnDescription(helper
					.getDescriptionFromVV(actCMCaseEvent
							.getAlertBasedOnColName(), logCaseDataBean.getEventAlertBasedOn()));
			
        }
        if (actCMCaseEvent.getSendAlertDaysCode() != null)
        {
        	
			caseEventsVO.setSendAlertDaysCd(actCMCaseEvent
					.getSendAlertDaysCode()); 
			caseEventsVO.setSendAlertDaysCdDesc(actCMCaseEvent
					.getSendAlertDaysCode());
			caseEventsVO.setSendAlertDaysCdDescription(helper
					.getDescriptionFromVV(
							actCMCaseEvent.getSendAlertDaysCode(), logCaseDataBean
									.getEventAlertDaysList()));
         	
        }
        if (actCMCaseEvent.getBeforeAfterCode() != null)
        {//modified to fix upcoming defect by rajasekhar
        	
        		if(actCMCaseEvent.getBeforeAfterCode().equals(MaintainContactManagementUIConstants.ALERT_BASED_VALID_CODE_B_FOR_BEFORE)){
        				caseEventsVO.setAfterOrBeforeCd(MaintainContactManagementUIConstants.ALERT_BASED_DESC_FOR_VALID_CODE_B_FOR_BEFORE);
        		}else if(actCMCaseEvent.getBeforeAfterCode().equals(MaintainContactManagementUIConstants.ALERT_BASED_VALID_CODE_A_FOR_AFTER)){
		
        			caseEventsVO.setAfterOrBeforeCd(MaintainContactManagementUIConstants.ALERT_BASED_DESC_FOR_VALID_CODE_A_FOR_AFTER);
        		} 

        		
        }
        if (actCMCaseEvent.getEventStatusCode() != null)
        {
        	// Added for the defect id ESPRD00334553
			
			caseEventsVO.setStatusCd(actCMCaseEvent.getEventStatusCode());
			caseEventsVO.setStatusCdDesc(actCMCaseEvent.getEventStatusCode());
			caseEventsVO.setStatusCdDescription(helper.getDescriptionFromVV(
					actCMCaseEvent.getEventStatusCode(), logCaseDataBean.getEventStatus()));
        	
        }
        if (actCMCaseEvent.getDispositionDate() != null)
        {
            caseEventsVO.setDispositionDateStr(ContactManagementHelper
                    .dateConverter(actCMCaseEvent.getDispositionDate()));
            caseEventsVO
                    .setDispositionDate(actCMCaseEvent.getDispositionDate());
        }
        if (actCMCaseEvent.getLetterTemplate() != null)
        {
            caseEventsVO.setTemplate(actCMCaseEvent.getLetterTemplate()
                    .getLetterTemplateKeyData());
        }
        caseEventsVO.setVersionNo(actCMCaseEvent.getVersionNo());
//      Fix for case event appoinment issue
        
        if(actCMCaseEvent.getCaseEventAppointments()!= null)
        {
        	Iterator caseEventAppointmentsItr = actCMCaseEvent.getCaseEventAppointments().iterator();
        	try{
        	while(caseEventAppointmentsItr.hasNext()){
        		CaseEventAppointment caseEventAppointment = (CaseEventAppointment)caseEventAppointmentsItr.next();
        		
        		
        		if(caseEventAppointment!=null){
        			if(caseEventAppointment.getCaseSK() != null)
        			{
        				caseEventsVO.setCaseSK(caseEventAppointment.getCaseSK().toString());
        			}
        			if(caseEventAppointment.getCaseTypeSK() != null){
        				caseEventsVO.setCaseTypeSK(caseEventAppointment.getCaseTypeSK().toString());
        			}
        			if(caseEventAppointment.getCmCaseEventCode()!=null){
        				caseEventsVO.setEventTypeCd(caseEventAppointment.getCmCaseEventCode());	
        			}
        			if(caseEventAppointment.getCaseEventSeqNum()!=null){
        				caseEventsVO.setCaseEventSeqNum(caseEventAppointment.getCaseEventSeqNum());	
        			}
        			if(caseEventAppointment.getProviderCommonEntitySK()!= null){
        				caseEventsVO.setProviderHospital(caseEventAppointment.getProviderCommonEntitySK().toString());
        			}
        			if(caseEventAppointment.getDiagnosticExam1Text()!=null){
        				caseEventsVO.setDiagnosisCode1(caseEventAppointment.getDiagnosticExam1Text());	
        			}
        			if(caseEventAppointment.getDiagnosticExam2Text()!=null){
        				caseEventsVO.setDiagnosisCode2(caseEventAppointment.getDiagnosticExam2Text());	
        			}
        			if(caseEventAppointment.getTest1Code()!=null){
        				caseEventsVO.setExamCode1(caseEventAppointment.getTest1Code());	
        			}
        			if(caseEventAppointment.getTest2Code()!=null){
        				caseEventsVO.setExamCode2(caseEventAppointment.getTest2Code());	
        			}
        			
       				caseEventsVO.setApptVersionNo(caseEventAppointment.getVersionNo());
     
        			if(caseEventAppointment.getAddedAuditUserID()!=null){
        				caseEventsVO.setCaseEventAppintmentAuditAddedUserId(caseEventAppointment.getAddedAuditUserID());
        			}
        			if(caseEventAppointment.getAddedAuditTimeStamp()!= null){
        				caseEventsVO.setCaseEventAppintmentAuditAddedTimestamp(caseEventAppointment.getAddedAuditTimeStamp());
        			}
        			if(caseEventAppointment.getAuditUserID()!=null){
        				caseEventsVO.setAuditUserID(caseEventAppointment.getAuditUserID());
        			}
        			
        			if(caseEventAppointment.getAuditTimeStamp()!= null){
        				caseEventsVO.setAuditTimeStamp(caseEventAppointment.getAuditTimeStamp());
        			}
        		}
        	}
        	}catch(Exception e){
        		if(logger.isDebugEnabled()){
        		logger.debug("Exception while getting caseEventAppointment At convertCaseEventsDOToVO(ActCMCaseEvent actCMCaseEvent)"+e.getMessage());
        		}
        	}
        }
        // end Fix for case event appoinment issue
        if (actCMCaseEvent.getAuditUserID() != null)
        {
            caseEventsVO.setAuditUserID(actCMCaseEvent.getAuditUserID());
        }
        if (actCMCaseEvent.getAuditTimeStamp() != null)
        {
            caseEventsVO.setAuditTimeStamp(actCMCaseEvent.getAuditTimeStamp());
        }
        if (actCMCaseEvent.getAddedAuditUserID() != null)
        {
            caseEventsVO.setAddedAuditUserID(actCMCaseEvent
                    .getAddedAuditUserID());
        }
        if (actCMCaseEvent.getAddedAuditTimeStamp() != null)
        {
            caseEventsVO.setAddedAuditTimeStamp(actCMCaseEvent
                    .getAddedAuditTimeStamp());
        }
        return caseEventsVO;
    }

    /**
     * This method is used to concvert the CaseTypeAcuityRateSetting DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeAcuityRateSettingVO object
     */
    private CaseTypeAcuityRateSettingVO convertCaseTypeAcuityRateSettingDOToVO(CaseARS caseARS)
    {  	
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeAcuityRateSetting DO To VO");
    	}

		CaseTypeAcuityRateSettingVO caseTypeAcuityRateSettingVO = null;
		caseTypeAcuityRateSettingVO = new CaseTypeAcuityRateSettingVO();

		if (caseARS != null) {
			if (caseARS.getRateSettingDate() != null) {
				caseTypeAcuityRateSettingVO
						.setRateEffectiveDate(ContactManagementHelper
								.dateConverter(caseARS.getRateSettingDate()));
			}
			if (caseARS.getFiscalYearEndDate() != null) {
				caseTypeAcuityRateSettingVO
						.setStateFiscYearEnd(ContactManagementHelper
								.dateConverter(caseARS.getFiscalYearEndDate()));
			}
			if (caseARS.getCaseARSCRPICDate() != null) {
				Map pdValMap = logCaseDataBean.getPicValMap();
				String pictID = (String) pdValMap.get(caseARS
						.getCaseARSCRPICDate());
				caseTypeAcuityRateSettingVO.setPictureDate(pictID);
			}
			if (caseARS.getAppealReceivedIndicator() != null) {
				caseTypeAcuityRateSettingVO.setAppealReceived(String
						.valueOf(caseARS.getAppealReceivedIndicator()));
			}
			caseTypeAcuityRateSettingVO.setVersionNo(caseARS.getVersionNo());
			caseTypeAcuityRateSettingVO
					.setAuditUserID(caseARS.getAuditUserID());
			caseTypeAcuityRateSettingVO.setAddedAuditUserID(caseARS
					.getAddedAuditUserID());
			caseTypeAcuityRateSettingVO.setAuditTimeStamp(caseARS
					.getAuditTimeStamp());
			caseTypeAcuityRateSettingVO.setAddedAuditTimeStamp(caseARS
					.getAddedAuditTimeStamp());

		}
		if(logger.isInfoEnabled()){
		logger.info("after convertCaseTypeAcuityRateSetting DO To VO");
		}

		return caseTypeAcuityRateSettingVO;
    }
    
    
    
    /**
     * This method is used to concvert the CaseTypeAcuityRateSetting DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeAcuityRateSettingVO object
     */
    private CaseTypeARSVO convertCaseTypeARSDOToVO(CaseARS caseARS)
    {  	
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeARS DO To VO");
    	}

    	CaseTypeARSVO caseTypeARSVO=null;
    	caseTypeARSVO= new CaseTypeARSVO();

    	if (caseARS != null) {
			if (caseARS.getAppealReceivedIndicator() != null) {
				caseTypeARSVO.setAppealReceivedInd(String.valueOf(caseARS
						.getAppealReceivedIndicator()));
			}
			if (caseARS.getFieldAuditRequiredInd() != null) {
				caseTypeARSVO.setFieldAuditRequiredInd(String.valueOf(caseARS
						.getFieldAuditRequiredInd()));
			}
			if (caseARS.getFieldAuditDate() != null) {
				caseTypeARSVO.setFieldAuditDateStr(ContactManagementHelper
						.dateConverter(caseARS.getFieldAuditDate()));
			}
			if (caseARS.getHomeOfficeInd() != null) {
				caseTypeARSVO.setHomeOfficeIndicator(String.valueOf(caseARS
						.getHomeOfficeInd()));
			}
			if (caseARS.getFacilityFiscalYearEndDate() != null ) {
					//&& !caseARS.getFacilityFiscalYearEndDate().equals("")) { /* FIND BUGS_FIX*/

				caseTypeARSVO
						.setFacilityFiscalYearEndDate(ContactManagementHelper
								.dateConverter(caseARS
										.getFacilityFiscalYearEndDate()));
			}
			if (caseARS.getCaseARSCRPICDate() != null) {

				Map pdValMap = logCaseDataBean.getPicValMap();
				String pictID = (String) pdValMap.get(caseARS
						.getCaseARSCRPICDate());

				caseTypeARSVO.setPictureDateStr(pictID);

			}
			if (caseARS.getRateSettingDate() != null) {
				caseTypeARSVO.setRateSettingDateStr(ContactManagementHelper
						.dateConverter(caseARS.getRateSettingDate()));
			}
			if (caseARS.getPrivatePayRatesLoadedInd() != null) {
				caseTypeARSVO.setPrivatePayRatesLoadedInd(String
						.valueOf(caseARS.getPrivatePayRatesLoadedInd()));
			}

			if (caseARS.getHomeOfficeDeskReviewResultsDate() != null
					&& !caseTypeARSVO.getHomeOfficeIndicator().equals(""))

			{
				caseTypeARSVO.setDeskReviewStartDate(ContactManagementHelper
						.dateConverter(caseARS
								.getHomeOfficeDeskReviewResultsDate()));
			}

			if (caseARS.getFiscalYearEndDate() != null ) {
					//&& !caseARS.getFiscalYearEndDate().equals("")) { /* FIND BUGS_FIX*/

				caseTypeARSVO.setStateFiscalYearEndDate(ContactManagementHelper
						.dateConverter(caseARS.getFiscalYearEndDate()));
			}
		}    
    	if(logger.isInfoEnabled()){
    	logger.info("after convertCaseTypeARS DO To VO");
    	}

    	return caseTypeARSVO;
    }
    
    
    /**
	 * This method is used to concvert the convertCaseTypeFPPRR DO to VO
	 * 
	 * @param caseARS
	 *            holds the CaseARS DO
	 * @return it returns the CaseTypeFPPRRVO object
	 */
    private CaseTypeFPPRRVO convertCaseTypeFPPRRDOToVO(CaseARS caseARS)
    {   	
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeFPPRRVOToDO");
    	}

    	CaseTypeFPPRRVO caseTypeFPPRRVO=null;
    	caseTypeFPPRRVO= new CaseTypeFPPRRVO();
    	
    	if(caseARS !=null){
    	
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeFPPRRVO.setStateFiscalYearEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	if(caseARS.getPrivatePayRatesLoadedInd()!=null){
    		caseTypeFPPRRVO.setPrivatePayRatesLoadedInd(String
    			.valueOf(caseARS.getAppealReceivedIndicator()));
    	}
    	    	
    	caseTypeFPPRRVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeFPPRRVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeFPPRRVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeFPPRRVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeFPPRRVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}
    	if(logger.isInfoEnabled()){
    	 logger.info("after convertCaseTypeFPPRRVOToDO");
    	}

    	return caseTypeFPPRRVO;
    }

    /**
     * This method is used to concvert the convertCaseTypeCreditBalance DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeCreditBalanceVO object
     */
    private CaseTypeCreditBalanceVO convertCaseTypeCreditBalanceDOToVO(CaseARS caseARS)
    {   	
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeCreditBalanceDOToVO");
    	}

    	CaseTypeCreditBalanceVO caseTypeCreditBalanceVO=null;
    	caseTypeCreditBalanceVO= new CaseTypeCreditBalanceVO();
    	
    	if(caseARS !=null){   		
    	
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeCreditBalanceVO.setStateFiscYrEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	    	
    	caseTypeCreditBalanceVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeCreditBalanceVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeCreditBalanceVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeCreditBalanceVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeCreditBalanceVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}
    	if(logger.isInfoEnabled()){
    	  logger.info("after convertCaseTypeCreditBalanceDOToVO");
    	}

    	return caseTypeCreditBalanceVO;
    }
    
    /**
     * This method is used to concvert the convertCaseTypeFacilityCensusSubmission DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeFacilityCensusSubmissionVO object
     */
    private CaseTypeFacilityCensusSubmissionVO convertCaseTypeFacilityCensusSubmissionDOToVO(CaseARS caseARS)
    {
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeFacilityCensusSubmissionDOToVO");
    	}
    	CaseTypeFacilityCensusSubmissionVO caseTypeFacilityCensusSubmissionVO=null;
    	caseTypeFacilityCensusSubmissionVO= new CaseTypeFacilityCensusSubmissionVO();
    	
    	if(caseARS !=null){
    	
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeFacilityCensusSubmissionVO.setStateFiscYrEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	if(caseARS.getCaseARSCRPICDate()!=null){
    		caseTypeFacilityCensusSubmissionVO.setPictureDate(String
    				.valueOf(caseARS.getCaseARSCRPICDate()));   
    	}

    	caseTypeFacilityCensusSubmissionVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeFacilityCensusSubmissionVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeFacilityCensusSubmissionVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeFacilityCensusSubmissionVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeFacilityCensusSubmissionVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}
    	if(logger.isInfoEnabled()){
    	  logger.info("after convertCaseTypeFacilityCensusSubmissionDOToVO");
    	}
    	return caseTypeFacilityCensusSubmissionVO;
    }
    
    /**
     * This method is used to concvert the convertCaseTypeFCR DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeFCRVO object
     */
    private CaseTypeFCRVO convertCaseTypeFCRDOToVO(CaseARS caseARS)
    {
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeFCRDOToVO");
    	}
    	CaseTypeFCRVO caseTypeFCRVO=null;
    	caseTypeFCRVO= new CaseTypeFCRVO();
    	
    	if(caseARS !=null){
    	
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeFCRVO.setStateFiscalYearEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	if(caseARS.getFacilityFiscalYearEndDate()!=null){
    		caseTypeFCRVO.setFacilityFiscalYearEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFacilityFiscalYearEndDate()));   
    	}
    	if(caseARS.getHomeOfficeInd()!=null){
    		caseTypeFCRVO.setHomeOfficeIndicator(String
    				.valueOf(caseARS.getHomeOfficeInd()));   
    	}
    	if(caseARS.getFieldAuditRequiredInd()!=null){
    		caseTypeFCRVO.setFieldAuditRequired(String
    				.valueOf(caseARS.getFieldAuditRequiredInd()));   
    	}
    	if(caseARS.getFieldAuditDate()!=null){
    		caseTypeFCRVO.setFieldAuditDate(ContactManagementHelper
    				.dateConverter(caseARS.getFieldAuditDate()));   
    	}
    	
		if (caseARS.getDeskReviewDate() != null){
				//&& !caseARS.getDeskReviewDate().equals("")) /* FIND BUGS_FIX*/

		
			caseTypeFCRVO.setDeskReviewStartDate(ContactManagementHelper
					.dateConverter(caseARS.getDeskReviewDate()));
		}
    	
    	caseTypeFCRVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeFCRVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeFCRVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeFCRVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeFCRVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}
    	if(logger.isInfoEnabled()){
    	  logger.info("after convertCaseTypeFCRDOToVO");
    	}

    	return caseTypeFCRVO;
    }
    
    /**
     * This method is used to concvert the convertCaseTypeHomeOfficeCostReporting DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeHomeOfficeCostReportingVO object
     */
    private CaseTypeHomeOfficeCostReportingVO convertCaseTypeHomeOfficeCostReportingDOToVO(CaseARS caseARS)
    {   	 
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeHomeOfficeCostReportingDOToVO");
    	}
    	CaseTypeHomeOfficeCostReportingVO caseTypeHomeOfficeCostReportingVO=null;
    	caseTypeHomeOfficeCostReportingVO= new CaseTypeHomeOfficeCostReportingVO();
    	
    	if(caseARS !=null){
    	
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeHomeOfficeCostReportingVO.setStateFiscalYearEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	if(caseARS.getFacilityFiscalYearEndDate()!=null){
    		caseTypeHomeOfficeCostReportingVO.setFacilityFiscalYearend(ContactManagementHelper
    				.dateConverter(caseARS.getFacilityFiscalYearEndDate()));   
    	}
    	if(caseARS.getFieldAuditRequiredInd()!=null){
    		caseTypeHomeOfficeCostReportingVO.setFieldAuditRequired(String
    				.valueOf(caseARS.getFieldAuditRequiredInd()));   
    	}
    	if(caseARS.getFieldAuditDate()!=null){
    		caseTypeHomeOfficeCostReportingVO.setFieldAuditDate(ContactManagementHelper
    				.dateConverter(caseARS.getFieldAuditDate()));   
    	}
    	
    	caseTypeHomeOfficeCostReportingVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeHomeOfficeCostReportingVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeHomeOfficeCostReportingVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeHomeOfficeCostReportingVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeHomeOfficeCostReportingVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}
    	if(logger.isInfoEnabled()){
        logger.info("after convertCaseTypeHomeOfficeCostReportingDOToVO");
    	}

    	return caseTypeHomeOfficeCostReportingVO;
    }
    
    /**
     * This method is used to concvert the convertCaseTypeNFQA DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeNFQAVO object
     */
    private CaseTypeNFQAVO convertCaseTypeNFQADOToVO(CaseARS caseARS)
    {
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeNFQADOToVO");
    	}
    	CaseTypeNFQAVO caseTypeNFQAVO=null;
    	caseTypeNFQAVO= new CaseTypeNFQAVO();
    	
    	if(caseARS !=null){
    		
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeNFQAVO.setStateFiscalYearEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	    	
    	caseTypeNFQAVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeNFQAVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeNFQAVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeNFQAVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeNFQAVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}
    	if(logger.isInfoEnabled()){
    	   logger.info("after convertCaseTypeNFQADOToVO");
    	}

    	return caseTypeNFQAVO;
    }
    
    /**
     * This method is used to concvert the convertCaseTypeQuarterlyMQIPReturns DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the caseTypeQuarterlyMQIPReturnsVO object
     */
    private caseTypeQuarterlyMQIPReturnsVO convertCaseTypeQuarterlyMQIPReturnsDOToVO(CaseARS caseARS)
    {
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeQuarterlyMQIPReturnsDOToVO");
    	}
    	caseTypeQuarterlyMQIPReturnsVO caseTypeQuarterlyMQIPReturnsVO=null;
    	caseTypeQuarterlyMQIPReturnsVO= new caseTypeQuarterlyMQIPReturnsVO();
    	
    	if(caseARS !=null){
    		
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeQuarterlyMQIPReturnsVO.setStateFiscalYearEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	    	
    	caseTypeQuarterlyMQIPReturnsVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeQuarterlyMQIPReturnsVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeQuarterlyMQIPReturnsVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeQuarterlyMQIPReturnsVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeQuarterlyMQIPReturnsVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}
    	if(logger.isInfoEnabled()){
    	 logger.info("after convertCaseTypeQuarterlyMQIPReturnsDOToVO");
    	}
    	return caseTypeQuarterlyMQIPReturnsVO;
    }
    
    /**
     * This method is used to concvert the convertCaseTypeNewARSField DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeNewARSFieldVO object
     */
    private CaseTypeNewARSFieldVO convertCaseTypeNewARSFieldDOToVO(CaseARS caseARS)
    {  	
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeNewARSFieldDOToVO");
    	}

    	CaseTypeNewARSFieldVO caseTypeNewARSFieldVO=null;
    	caseTypeNewARSFieldVO= new CaseTypeNewARSFieldVO();
    	
    	if(caseARS !=null){
    		
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeNewARSFieldVO.setStateFiscalYrEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	if(caseARS.getFacilityFiscalYearEndDate()!=null){
    		caseTypeNewARSFieldVO.setFacilityFiscalYrEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	if(caseARS.getAppealReceivedIndicator()!=null){
    		caseTypeNewARSFieldVO.setAppealRequest(String
    				.valueOf(caseARS.getAppealReceivedIndicator()));   
    	}
    	    	
    	caseTypeNewARSFieldVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeNewARSFieldVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeNewARSFieldVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeNewARSFieldVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeNewARSFieldVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}
    	if(logger.isInfoEnabled()){
        logger.info("after convertCaseTypeNewARSFieldDOToVO");
    	}
    	return caseTypeNewARSFieldVO;
    }
    
    /**
     * This method is used to concvert the convertCaseTypeNonARSField DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeNonARSFieldVO object
     */
    private CaseTypeNonARSFieldVO convertCaseTypeNonARSFieldDOToVO(CaseARS caseARS)
    {  	  
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeNonARSFieldDOToVO");
    	}

    	CaseTypeNonARSFieldVO caseTypeNonARSFieldVO=null;
    	caseTypeNonARSFieldVO= new CaseTypeNonARSFieldVO();
    	
    	if(caseARS !=null){ 		
    	
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeNonARSFieldVO.setStateFiscalYrEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	if(caseARS.getFacilityFiscalYearEndDate()!=null){
    		caseTypeNonARSFieldVO.setFacilityFiscalYrEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFacilityFiscalYearEndDate()));   
    	}
    	if(caseARS.getAppealReceivedIndicator()!=null){
    		caseTypeNonARSFieldVO.setAppealRequest(String
    				.valueOf(caseARS.getAppealReceivedIndicator()));   
    	}
    	    	
    	caseTypeNonARSFieldVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeNonARSFieldVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeNonARSFieldVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeNonARSFieldVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeNonARSFieldVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}     
    	if(logger.isInfoEnabled()){
    	 logger.info("after convertCaseTypeNonARSFieldDOToVO");
    	}
    	return caseTypeNonARSFieldVO;
    }
    
    /**
     * This method is used to concvert the convertCaseTypeAppealRequest DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeAppealRequestVO object
     */
    private CaseTypeAppealRequestVO convertCaseTypeAppealRequestDOToVO(CaseARS caseARS)
    {  	
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeAppealRequestDOToVO");
    	}

    	CaseTypeAppealRequestVO caseTypeAppealRequestVO=null;
    	caseTypeAppealRequestVO= new CaseTypeAppealRequestVO();
    	
    	if(caseARS !=null){   		
    	
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeAppealRequestVO.setStateFiscalYrEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	if(caseARS.getRateSettingDate()!=null){
    		caseTypeAppealRequestVO.setRateSettingDate(ContactManagementHelper
    				.dateConverter(caseARS.getRateSettingDate()));   
    	}
    	    	    	
    	caseTypeAppealRequestVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeAppealRequestVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeAppealRequestVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeAppealRequestVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeAppealRequestVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}  
    	if(logger.isInfoEnabled()){
    	logger.info("after convertCaseTypeAppealRequestDOToVO");
    	}

    	return caseTypeAppealRequestVO;
    }
    
    /**
     * This method is used to concvert the convertCaseTypeFieldAudit DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeFieldAuditVO object
     */
    private CaseTypeFieldAuditVO convertCaseTypeFieldAuditDOToVO(CaseARS caseARS)
    { 	  
    	if(logger.isInfoEnabled()){
    	logger.info("inside convertCaseTypeFieldAuditDOToVO");
    	}

    	CaseTypeFieldAuditVO caseTypeFieldAuditVO=null;
    	caseTypeFieldAuditVO= new CaseTypeFieldAuditVO();
    	
    	if(caseARS !=null){
    		
    	if(caseARS.getFiscalYearEndDate()!=null){
    		caseTypeFieldAuditVO.setStateFiscalYrEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFiscalYearEndDate()));   
    	}
    	if(caseARS.getFacilityFiscalYearEndDate()!=null){
    		caseTypeFieldAuditVO.setFacilityFiscalYearEnd(ContactManagementHelper
    				.dateConverter(caseARS.getFacilityFiscalYearEndDate()));   
    	}
    	if(caseARS.getFieldAuditDate()!=null){
    		caseTypeFieldAuditVO.setFieldAuditDate(ContactManagementHelper
    				.dateConverter(caseARS.getFieldAuditDate()));   
    	}
    	if (caseARS.getHomeOfficeInd() != null) {
    		caseTypeFieldAuditVO.setHomeOfficeInd(String.valueOf(caseARS.getHomeOfficeInd()));
		}    	    	
    	caseTypeFieldAuditVO.setVersionNo(caseARS.getVersionNo());
    	caseTypeFieldAuditVO.setAuditUserID(caseARS.getAuditUserID());
    	caseTypeFieldAuditVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
    	caseTypeFieldAuditVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
    	caseTypeFieldAuditVO.setAddedAuditTimeStamp(caseARS.getAddedAuditTimeStamp());
    	
    	}  
    	if(logger.isInfoEnabled()){
    	 logger.info("after convertCaseTypeFieldAuditDOToVO");
    	}

    	return caseTypeFieldAuditVO;
    }
    
    /**
     * This method is used to concvert the Case type ARS DO to VO
     * 
     * @param caseARS
     *            holds the CaseARS DO
     * @return it returns the CaseTypeARSVO object
     */
  /*  private CaseTypeARSVO convertCaseTypeARSDOToVO(CaseARS caseARS)
    {
        logger.debug("In Case ARS Convertor");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(CMLOGCASE_DATA_BEAN);
        CaseTypeARSVO caseTypeARSVO = new CaseTypeARSVO();

        if (caseARS.getAppealReceivedIndicator() != null)
        {
            caseTypeARSVO.setAppealReceivedInd(String.valueOf(caseARS
                    .getAppealReceivedIndicator()));
        }
        
        if (caseARS.getFieldAuditRequiredInd() != null)
        {
            caseTypeARSVO.setFieldAuditRequiredInd(String.valueOf(caseARS
                    .getFieldAuditRequiredInd()));
        }
        if (caseARS.getFieldAuditDate() != null)
        {
            caseTypeARSVO.setFieldAuditDateStr(ContactManagementHelper
                    .dateConverter(caseARS.getFieldAuditDate()));
            caseTypeARSVO.setFieldAuditDate(caseARS.getFieldAuditDate());
        }
        if (caseARS.getHomeOfficeInd() != null)
        {
            caseTypeARSVO.setHomeOfficeIndicator(String.valueOf(caseARS
                    .getHomeOfficeInd()));
        }
        if (caseARS.getCaseARSCRPICDate() != null)
        {
            String picDateStr = caseARS.getCaseARSCRPICDate();
            String picCode = logCaseDataBean.getPicValMap().get(picDateStr).toString();
            caseTypeARSVO.setPictureDateStr(picCode);
            try {
            	SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy");
            	caseTypeARSVO.setPictureDate(ss.parse(caseARS.getCaseARSCRPICDate()));
            }catch (ParseException e){
            	
            }
        }
        if (caseARS.getRateSettingDate() != null)
        {
            caseTypeARSVO.setRateSettingDateStr(ContactManagementHelper
                    .dateConverter(caseARS.getRateSettingDate()));
            caseTypeARSVO.setRateSettingDate(caseARS.getRateSettingDate());
        }
        if (caseARS.getPrivatePayRatesLoadedInd() != null)
        {
            caseTypeARSVO.setPrivatePayRatesLoadedInd(String.valueOf(caseARS
                    .getPrivatePayRatesLoadedInd()));
        }
        if (caseARS.getPrivatePayRatesReceivedInd() != null)
        {
            caseTypeARSVO.setPrivatePayRatesReceivedInd(String.valueOf(caseARS
                    .getPrivatePayRatesReceivedInd()));
        }
        if (caseARS.getFiscalYearEndDate() != null)
        {
           
        }
        if (caseARS.getFiscalYearEndDate() != null)
        {
       
        if (caseARS.getFiscalYearEndDate() != null)
        {
            caseTypeARSVO.setStateFiscalYearEndDate(caseARS.getFiscalYearEndDate()
                    .toString());
        }
        
       
        
        caseTypeARSVO.setVersionNo(caseARS.getVersionNo());
        if (caseARS.getAuditUserID() != null)
        {
            caseTypeARSVO.setAuditUserID(caseARS.getAuditUserID());
        }
        if (caseARS.getAuditTimeStamp() != null)
        {
            caseTypeARSVO.setAuditTimeStamp(caseARS.getAuditTimeStamp());
        }
        if (caseARS.getAddedAuditUserID() != null)
        {
            caseTypeARSVO.setAddedAuditUserID(caseARS.getAddedAuditUserID());
        }
        if (caseARS.getAddedAuditTimeStamp() != null)
        {
            caseTypeARSVO.setAddedAuditTimeStamp(caseARS
                    .getAddedAuditTimeStamp());
        }
		}
        return caseTypeARSVO;
    }*/

    /**
     * This method is used to concvert the Case type DDU DO to VO
     * 
     * @param caseDDU
     *            holds the CaseDDU DO
     * @return it returns the CaseTypeDDUVO object
     * @throws SystemListNotFoundException
     * @throws ReferenceServiceRequestException
     */
    private CaseTypeDDUVO convertCaseTypeDDUDOToVO(CaseDDU caseDDU) throws ReferenceServiceRequestException, SystemListNotFoundException
    {
        CaseTypeDDUVO caseTypeDDUVO = new CaseTypeDDUVO();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        if(logger.isDebugEnabled()){
        logger.debug("In Case DDU Convertor");
        }
        if (caseDDU.getReviewInitiatedDate() != null)
        {
            caseTypeDDUVO.setReviewInitiatedDateStr(ContactManagementHelper
                    .dateConverter(caseDDU.getReviewInitiatedDate()));
        }
        if (caseDDU.getApplicationBeginDate() != null)
        {
            caseTypeDDUVO.setApprovedBeginDateStr(ContactManagementHelper.dateConverter(caseDDU.getApplicationBeginDate()));
        }
        if (caseDDU.getScheduleReviewDate() != null)
        {
            caseTypeDDUVO.setSchDateOfReviewStr(ContactManagementHelper
                    .dateConverter(caseDDU.getScheduleReviewDate()));
         // Added for the Defect ESPRD00784025 
            caseTypeDDUVO.setSchDateOfReviewStrForUpdateMode(ContactManagementHelper
                    .dateConverter(caseDDU.getScheduleReviewDate()));
            logCaseDataBean.setShowReviewReq(true);
        }
        if (caseDDU.getAppealDecisionDate() != null)
        {
            caseTypeDDUVO.setAppealDecisionDateStr(ContactManagementHelper
                    .dateConverter(caseDDU.getAppealDecisionDate()));
            caseTypeDDUVO
                    .setAppealDecisionDate(caseDDU.getAppealDecisionDate());
        }
        if (!isNullOrEmptyField(caseDDU.getCaseDDUApplicationTypeCode()))
        {
            caseTypeDDUVO.setApplicationTypeCd(caseDDU
                    .getCaseDDUApplicationTypeCode());
        }
        if (caseDDU.getApplicationDate() != null)
        {
            caseTypeDDUVO.setApplicationDateStr(ContactManagementHelper
                    .dateConverter(caseDDU.getApplicationDate()));
            caseTypeDDUVO.setApplicationDate(caseDDU.getApplicationDate());
        }
        if (caseDDU.getCompletedDate() != null)
        {
            caseTypeDDUVO
                    .setSubstantiallyCompletedDateStr(ContactManagementHelper
                            .dateConverter(caseDDU.getCompletedDate()));
            caseTypeDDUVO.setSubstantiallyCompletedDate(caseDDU
                    .getCompletedDate());
        }
        if (!isNullOrEmptyField(caseDDU.getCaseDDUAuthorizedRepresentTyCd()))
        {
            caseTypeDDUVO.setAuthorizedRepCd(caseDDU
                    .getCaseDDUAuthorizedRepresentTyCd());
        }
        if (caseDDU.getPacketReceivedDate() != null)
        {
            caseTypeDDUVO.setPacketReceivedDateStr(ContactManagementHelper
                    .dateConverter(caseDDU.getPacketReceivedDate()));
            caseTypeDDUVO
                    .setPacketReceivedDate(caseDDU.getPacketReceivedDate());
        }
       
        if (caseDDU.getReviewRequiredIndicator() != null)
        {
            caseTypeDDUVO.setReviewRequiredInd(String.valueOf(caseDDU
                    .getReviewRequiredIndicator()));
        }
        if (caseDDU.getReceivedDate() != null)
        {
            caseTypeDDUVO.setReceivedDateStr(ContactManagementHelper
                    .dateConverter(caseDDU.getReceivedDate()));
            caseTypeDDUVO.setReceivedDate(caseDDU.getReceivedDate());
        }
        if (caseDDU.getReceiptDate() != null)
        {
            caseTypeDDUVO.setReceiptDateStr(ContactManagementHelper
                    .dateConverter(caseDDU.getReceiptDate()));
            caseTypeDDUVO.setReceiptDate(caseDDU.getReceiptDate());
        }
        if (!isNullOrEmptyField(caseDDU.getCaseDDUEvaluateType()))
        {
            caseTypeDDUVO.setEvaluationTypeCd(caseDDU.getCaseDDUEvaluateType());
        }
        if (!isNullOrEmptyField(caseDDU.getCaseDDUMedicalDiagnosisCode()))
        {
            caseTypeDDUVO.setMedicalDiagnosisCd(caseDDU
                    .getCaseDDUMedicalDiagnosisCode());
        }
        if (!isNullOrEmptyField(caseDDU.getCaseDDUpsychiatricDiagnosisCode()))
        {
            caseTypeDDUVO.setPsychiatricDiagnosisCd(caseDDU
                    .getCaseDDUpsychiatricDiagnosisCode());
        }
        if (caseDDU.getResponseIndicator() != null)
        {
            caseTypeDDUVO.setResponseIndicator(String.valueOf(caseDDU
                    .getResponseIndicator()));
        }
        if (caseDDU.getDecisionDate() != null)
        {
            caseTypeDDUVO.setDecisionDateStr(ContactManagementHelper
                    .dateConverter(caseDDU.getDecisionDate()));
            caseTypeDDUVO.setDecisionDate(caseDDU.getDecisionDate());
        }
        if (!isNullOrEmptyField(caseDDU.getCaseDDULOCCode()))
        {
            caseTypeDDUVO.setLevelOfCareCd(caseDDU.getCaseDDULOCCode());
        }
        if (!isNullOrEmptyField(caseDDU.getCaseDDUdenialReasonCode()))
        {
            caseTypeDDUVO.setDenialReasonCd(caseDDU
                    .getCaseDDUdenialReasonCode());
        }
        if (!isNullOrEmptyField(caseDDU.getCaseDDUCloseTypeCode()))
        {
            caseTypeDDUVO.setCloseCodeCd(caseDDU.getCaseDDUCloseTypeCode());
        }
        caseTypeDDUVO.setVersionNo(caseDDU.getVersionNo());
        if (caseDDU.getAuditUserID() != null)
        {
            caseTypeDDUVO.setAuditUserID(caseDDU.getAuditUserID());
        }
        if (caseDDU.getAuditTimeStamp() != null)
        {
            caseTypeDDUVO.setAuditTimeStamp(caseDDU.getAuditTimeStamp());
        }
        if (caseDDU.getAddedAuditUserID() != null)
        {
            caseTypeDDUVO.setAddedAuditUserID(caseDDU.getAddedAuditUserID());
        }
        if (caseDDU.getAddedAuditTimeStamp() != null)
        {
            caseTypeDDUVO.setAddedAuditTimeStamp(caseDDU
                    .getAddedAuditTimeStamp());
        }
       /* if (caseDDU.getCaseDDUUnusualCircumstances() != null
                && !caseDDU.getCaseDDUUnusualCircumstances().isEmpty())
        {
            logger
                    .debug("CaseDDUUnusualCircumstances list is not empty in getting");
            Set dduUnUsual = caseDDU.getCaseDDUUnusualCircumstances();
            Iterator iterator = dduUnUsual.iterator();
            SelectItem item = null;
            String shortDesc = null;
            List list = new ArrayList();
            List selectedUnUsualList = new ArrayList();
            List availList = logCaseDataBean
                    .getUnusualCircumstancesList();
            ReferenceDataSearchVO referenceDataSearch = null;
            ReferenceServiceDelegate referenceServiceDelegate = null;
            ReferenceDataListVO referenceDataListVO = null;
            
            *//** preparing criteria for Case Unusual Circumstances *//*
            list.add(getInputCriteria(
                    ReferenceServiceDataConstants.G_CASE_DDU_UNUSL_CRCMST_CD,
                    FunctionalAreaConstants.GENERAL));
            
            referenceDataSearch = new ReferenceDataSearchVO();
            referenceDataSearch.setInputList(list);
            referenceServiceDelegate = new ReferenceServiceDelegate();

          
            referenceDataListVO = new ReferenceDataListVO();            
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);            

           Map map = referenceDataListVO.getResponseMap();
            for (Iterator iter = dduUnUsual.iterator(); iter.hasNext();)
            {
                CaseDDUUnusualCircumstances element = (CaseDDUUnusualCircumstances) iter
                        .next();
                
                shortDesc = ContactManagementHelper
                        .setShortDescription(
                                FunctionalAreaConstants.GENERAL2,
                                ReferenceServiceDataConstants.G_CASE_DDU_UNUSL_CRCMST_CD,
                                element.getCircumstanceTypeCode());
               
                item = new SelectItem();
                String description = getLongDescription(element.getCircumstanceTypeCode(), availList);
                item.setLabel(element.getCircumstanceTypeCode()+" - "+description);
                item.setValue(element.getCircumstanceTypeCode());
                selectedUnUsualList.add(item);
                removeItem(element.getCircumstanceTypeCode(), availList);
            }
            logCaseDataBean.setSelectedUnUsualList(selectedUnUsualList);
           
        }*/
        return caseTypeDDUVO;
    }

    /**
     * Method to remove an item from the list.
     * 
     * @param value
     *            The value to set.
     * @param availList
     *            The availList to set.
     * @return SelectItem.
     */
    /*private SelectItem removeItem(String value, List availList)
    {
        logger.info("removeItem");
        SelectItem result = null;
        int size = availList.size();
        for (int i = 0; i < size; i++)
        {
            SelectItem item = (SelectItem) availList.get(i);
            if (value.equals(item.getValue()))
            {
                result = (SelectItem) availList.remove(i);
                break;
            }
        }
        return result;
    }*/

    /**
     * Method to get the description of Unusual Circumstances 
     * 
     * @param value
     *            The value to set.
     * @param availList
     *            The availList to set.
     * @return String.
     */
    public String getLongDescription(String value, List availList)
    {
    	if(logger.isInfoEnabled()){
        logger.info("getLongDescription");
    	}
        int size = availList.size();
        String description =null;
        for (int i = 0; i < size; i++)
        {
            SelectItem item = (SelectItem) availList.get(i);
            String[] descriptionDetail= item.getLabel().split("-");
            // Begin - Added the if block for the defect id ESPRD00682457_05SEP2011
            if (descriptionDetail[0].equals(value)) {	            
            description = descriptionDetail[1];
            if (value.equals(item.getValue()))
                break;
            }
            // End - Added the if block for the defect id ESPRD00682457_05SEP2011 
        }
        return description;
    }
    /**
     * This method is used to concvert the Case type BCCP DO to VO
     * 
     * @param caseBCCP
     *            holds the CaseBCCP DO
     * @return it returns the CaseTypeBCCPVO object
     */
    private CaseTypeBCCPVO convertCaseTypeBCCPDOToVO(CaseBCCP caseBCCP)
    {
    	if(logger.isDebugEnabled()){
        logger.debug("In Case BCCP Convertor");
    	}
        CaseTypeBCCPVO caseTypeBCCPVO = new CaseTypeBCCPVO();
        if (caseBCCP.getApplicationDate() != null)
        {
            caseTypeBCCPVO.setApplicationDateStr(ContactManagementHelper
                    .dateConverter(caseBCCP.getApplicationDate()));
            caseTypeBCCPVO.setApplicationDate(caseBCCP.getApplicationDate());
        }
        if (!isNullOrEmptyField(caseBCCP.getBccpID()))
        {
            caseTypeBCCPVO.setBccpID(caseBCCP.getBccpID());
        }
        if (!isNullOrEmptyField(caseBCCP.getContactName()))
        {
            caseTypeBCCPVO.setContactPerson(caseBCCP.getContactName());
        }
      
        if (caseBCCP.getScreeningEnrollDate() != null)
        {
            caseTypeBCCPVO.setEnrollmentDateStr(ContactManagementHelper
                    .dateConverter(caseBCCP.getScreeningEnrollDate()));
            caseTypeBCCPVO.setEnrollmentDate(caseBCCP.getScreeningEnrollDate());
        }
        if (!isNullOrEmptyField(caseBCCP.getCaseBCCPScreeningSiteCode()))
        {
            caseTypeBCCPVO.setAgencySite(caseBCCP
                    .getCaseBCCPScreeningSiteCode());
        }
        if (!isNullOrEmptyField(caseBCCP.getScreeningAgencyCMText()))
        {
            caseTypeBCCPVO.setAgencyCaseManager(caseBCCP
                    .getScreeningAgencyCMText());
        }

        if (caseBCCP.getBiopsyDate() != null)
        {
            caseTypeBCCPVO.setBiopsyDateStr(ContactManagementHelper
                    .dateConverter(caseBCCP.getBiopsyDate()));
            caseTypeBCCPVO.setBiopsyDate(caseBCCP.getBiopsyDate());
        }
        if (!isNullOrEmptyField(caseBCCP.getCaseBCCPBiopsyFundingCode()))
        {
            caseTypeBCCPVO.setBiopsyFindingsCd(caseBCCP
                    .getCaseBCCPBiopsyFundingCode());
        }
        if (!isNullOrEmptyField(caseBCCP.getBccpCaseRecommendCode()))
        {
            caseTypeBCCPVO.setRecommendationCd(caseBCCP
                    .getBccpCaseRecommendCode());
        }
        if (caseBCCP.getRecommendFOllowupMONumber() != null)
        {
            caseTypeBCCPVO.setStFollowUPNum(caseBCCP
                    .getRecommendFOllowupMONumber().toString());
        }
        if (caseBCCP.getConsultantDate() != null)
        {
            caseTypeBCCPVO.setConsultDateStr(ContactManagementHelper
                    .dateConverter(caseBCCP.getConsultantDate()));
            caseTypeBCCPVO.setConsultDate(caseBCCP.getConsultantDate());
        }
        if (!isNullOrEmptyField(caseBCCP.getCaseBCCPFinalCancerStageCode()))
        {
            caseTypeBCCPVO.setFinalStageCd(caseBCCP
                    .getCaseBCCPFinalCancerStageCode());
        }
        if (!isNullOrEmptyField(caseBCCP.getBccpFinalMTSTSAreaCode()))
        {
            caseTypeBCCPVO.setMetastasisAreaCd(caseBCCP
                    .getBccpFinalMTSTSAreaCode());
        }
        if (!isNullOrEmptyField(caseBCCP.getCaseBCCPUNSTGReasonCode()))
        {
            caseTypeBCCPVO.setUnstagedReasonCd(caseBCCP
                    .getCaseBCCPUNSTGReasonCode());
        }
        if (caseBCCP.getFinalTumorCMSizeNumber() != null)
        {
            caseTypeBCCPVO.setTumorSizeNum(String.valueOf(caseBCCP
                    .getFinalTumorCMSizeNumber()));
        }
        if (caseBCCP.getFinalCancerNodesPositiveNumber() != null)
        {
            caseTypeBCCPVO.setNodesPositiveNum(caseBCCP
                    .getFinalCancerNodesPositiveNumber().toString());
        }
        if (!isNullOrEmptyField(caseBCCP.getCaseBCCPTreatmentStartedCode()))
        {
            caseTypeBCCPVO.setTreatmentStartedCd(caseBCCP
                    .getCaseBCCPTreatmentStartedCode());
        }
        if (caseBCCP.getTreatmentStartedDate() != null)
        {
            caseTypeBCCPVO.setTreatmentStartDateStr(ContactManagementHelper
                    .dateConverter(caseBCCP.getTreatmentStartedDate()));
            caseTypeBCCPVO.setTreatmentStartDate(caseBCCP
                    .getTreatmentStartedDate());
        }
        if (caseBCCP.getChemoProjectedEndDate() != null)
        {
            caseTypeBCCPVO.setChemoProjectedEndDateStr(ContactManagementHelper
                    .dateConverter(caseBCCP.getChemoProjectedEndDate()));
            caseTypeBCCPVO.setChemoProjectedEndDate(caseBCCP
                    .getChemoProjectedEndDate());
        }
        if (!isNullOrEmptyField(caseBCCP.getCaregiverName()))
        {
            caseTypeBCCPVO.setCaregiverName(caseBCCP.getCaregiverName());
        }

        if (caseBCCP.getForm778SignIndicator() != null)
        {
            caseTypeBCCPVO.setForm778SignedInd(String.valueOf(caseBCCP
                    .getForm778SignIndicator()));
        }
        caseTypeBCCPVO.setVersionNo(caseBCCP.getVersionNo());
        if (caseBCCP.getAuditUserID() != null)
        {
            caseTypeBCCPVO.setAuditUserID(caseBCCP.getAuditUserID());
        }
        if (caseBCCP.getAuditTimeStamp() != null)
        {
            caseTypeBCCPVO.setAuditTimeStamp(caseBCCP.getAuditTimeStamp());
        }
        if (caseBCCP.getAddedAuditUserID() != null)
        {
            caseTypeBCCPVO.setAddedAuditUserID(caseBCCP.getAddedAuditUserID());
        }
        if (caseBCCP.getAddedAuditTimeStamp() != null)
        {
            caseTypeBCCPVO.setAddedAuditTimeStamp(caseBCCP
                    .getAddedAuditTimeStamp());
        }
        //////ESPRD00532683////
        if (caseBCCP.getCaregiverPhone() != null)
        {
            caseTypeBCCPVO.setCaregiverPhone(caseBCCP.getCaregiverPhone()
                    .getPhoneNumber());
            if(caseBCCP.getCaregiverPhone()
                    .getPhoneNumber()!=null && !caseBCCP.getCaregiverPhone()
                    .getPhoneNumber().equals("")){
            	caseTypeBCCPVO.setCareGiverPhoneVo(convertPhoneDOtoVO(caseBCCP.getCaregiverPhone()));
            }
        }
        if (caseBCCP.getScreeningAgencyPhone() != null)
        {
            caseTypeBCCPVO.setAgencyPhoneNumber(caseBCCP
                    .getScreeningAgencyPhone().getPhoneNumber());
            //Modified for defect ESPRD00690158 starts
            if(caseBCCP.getScreeningAgencyPhone()
                    .getPhoneNumber()!=null && !caseBCCP.getScreeningAgencyPhone()
                    .getPhoneNumber().equals("")){
            	caseTypeBCCPVO.setAgencyPhoneVo(convertPhoneDOtoVO(caseBCCP.getScreeningAgencyPhone()));
            }
           // defect ESPRD00690158 ends
        }
        ////////ESPRD00532683//////
        return caseTypeBCCPVO;
    }

    private PhoneVO convertPhoneDOtoVO(Phone phone){
	 	PhoneVO phoneVO = new PhoneVO();
	 	if(phone.getPhoneNumber()!=null){
	 		phoneVO.setPhoneNumber(phone.getPhoneNumber());	
	 	}
	 	if(phone.getAddedAuditTimeStamp()!=null){
	 		phoneVO.setAddedAuditTimeStamp(phone.getAddedAuditTimeStamp());
	 	}
	 
	 	if(phone.getAddedAuditUserID()!=null){
	 		phoneVO.setAddedAuditUserID(phone.getAddedAuditUserID());
	 	}
	 	
	 	if(phone.getAuditUserID()!=null){
	 		phoneVO.setAuditUserID(phone.getAuditUserID());
	 	}
	 	
	 	if(phone.getOutOfServiceIndicator()!=null){
	 		phoneVO.setOutOfService(phone.getOutOfServiceIndicator().booleanValue());
	 	}
	 	if(phone.getDummyInd()!=null && phone.getDummyInd().equals("N")){
	 		phoneVO.setDbRecord(false);	
	 	}else if(phone.getDummyInd().equals("Y")){
	 		phoneVO.setDbRecord(true);
	 	}
	 	//Modified for defect ESPRD00690158 starts
	 	phoneVO.setPhoneSK(phone.getPhoneSK());
	 	// defect ESPRD00690158 ends
	 	phoneVO.setVersionNo(phone.getVersionNo());
	 	
	 	phoneVO.setAuditTimeStamp(phone.getAuditTimeStamp());
	 	return phoneVO;
	 }

    /**
     * This method is used to convert the AttachmentCrossRef to AttachmentVO
     * 
     * @param caseAttachCrossReference
     *            holds the CaseAttachCrossReference object.
     * @return it returns the AttachmentVO object
     */
    public AttachmentVO convertCaseAttachmentDOToVO(
            CaseAttachCrossReference caseAttachCrossReference)
    {
    	if(logger.isDebugEnabled()){
        logger.debug("convertCaseAttachmentDOToVO is Started");
    	}
        AttachmentVO attachmentVO = new AttachmentVO();
        Attachment attachmentDO = null;
       
        String userName = null;
        ContactManagementHelper contactHelper = new ContactManagementHelper();
       
        if (caseAttachCrossReference.getAttachment() != null)
        {
            attachmentDO = caseAttachCrossReference.getAttachment();
            
            attachmentVO.setVersionNo(attachmentDO.getVersionNo());
            attachmentVO.setCaseCrossRefVersionNo(caseAttachCrossReference.getVersionNo());
            if (attachmentDO.getAttachmentFileName() != null)
            {
                attachmentVO.setFileName(attachmentDO.getAttachmentFileName());
            }
            if (attachmentDO.getAttachmentDescription() != null)
            {
                attachmentVO.setDescription(attachmentDO
                        .getAttachmentDescription());
            }
            if (attachmentDO.getAttachmentCreatedDate() != null)
			{
              
                contactHelper.getFormatedAttachmentDate(attachmentVO, attachmentDO.getAttachmentCreatedDate());                
               
            }
            if (attachmentDO.getAttachmentAddedByName() != null)
			{
            	                      
                /*userName = contactHelper.getUserName(caseAttachCrossReference.getCaseRecord().getCaseOpenWorkUnit().getEnterpriseUser().getUserWorkUnitSK().toString());
                attachmentVO.setAddedBy(userName);*///commented for UC-PGM-CRM-063_ESPRD00407851_17FEB2010
              
                attachmentVO.setAddedBy(attachmentDO.getAttachmentAddedByName());
            }
            if (attachmentDO.getAttachmentSK() != null)
            {
                attachmentVO.setAttachmentSK(attachmentDO.getAttachmentSK());
            }
            if (attachmentDO.getAttachmentEDMSPageID() != null)
            {
                attachmentVO.setAttachmentPageID(attachmentDO
                        .getAttachmentEDMSPageID());
                attachmentVO.setFileUrl(generateURL(attachmentVO
                        .getAttachmentPageID()));
            }
            
            attachmentVO.setExistDoc(false);
            
            if (caseAttachCrossReference.getDitachedOrAttachedIndicator() != null)
            {
                attachmentVO.setDetachInd(caseAttachCrossReference
                        .getDitachedOrAttachedIndicator().booleanValue());
            }
            
            
            if (attachmentVO.isDetachInd()){
            	attachmentVO.setExistDoc(true);
            }
          
            //for displaying detach link instead of delete.
			//for defect ESPRD00747731.
            attachmentVO.setShowDetachAttachment(true);

            //for defect 790505
            if(attachmentDO.getEdmsWrkUnitLevel()!=null){
            	attachmentVO.setEdmsWrkUnitLevel(attachmentDO.getEdmsWrkUnitLevel());
            }
            if(attachmentDO.getEdmsDocType()!=null){
            	attachmentVO.setEdmsDocType(attachmentDO.getEdmsDocType());
            }
        }
     
        return attachmentVO;
    }
    
   /* private String dateConverterForAttach(Date date)
    {
        Format formatter = new SimpleDateFormat(
                "MM/dd/yyyy hh:mm a", Locale.getDefault());
        String attachDate = formatter.format(date);
       
        return attachDate;
    }*/
    

    /**
     * Method to generate the URL based on pageID.
     * 
     * @param pageID
     *            Takes pageID as param
     * @return String
     */
    public String generateURL(String pageID)
    {
        String fileUrl = "";
        try
        {
            URLGenerator urlGenerator = new EDMSURLGeneratorImpl();
            fileUrl = urlGenerator.getURL(pageID);
        }
        catch (EDMSBaseException edmsBaseException)
        {
        	if(logger.isErrorEnabled()){
            logger.error(edmsBaseException.getMessage(), edmsBaseException);
        	}
        }
        return fileUrl;
    }

    /**
     * This method setCFValueToCustomField.
     * 
     * @param cfValueList
     *            Takes cfValueList
     * @param cfList
     *            Takes cfList
     */
    public void setCFValueToCustomField(List cfValueList, List cfList)
    {
        CustomField field = null;
        CustomFieldValue fieldValue = null;
        CustomFieldValueVO valueVO = new CustomFieldValueVO();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        Long valueCFSK = null;
        if (cfList != null && !cfList.isEmpty())
        {
            int cfListSize = cfList.size();
            for (int i = 0; i < cfListSize; i++)
            {
                field = (CustomField) cfList.get(i);
                for (int j = 0; j < cfValueList.size(); j++)
                {
                    fieldValue = (CustomFieldValue) cfValueList.get(j);
                    if (fieldValue != null)
                    {
                        valueCFSK = fieldValue.getCustomField()
                                .getCustomFieldSK();
                        
                        if (field.getCustomFieldSK().longValue() == valueCFSK
                                .longValue())
                        {
                            valueVO = setCustomFieldValue(i, valueVO,
                                    fieldValue.getCustomFieldValue());
                            valueVO = convertCustomFieldValueDOToVO(valueVO,
                                    fieldValue);
                           
                            logCaseDataBean.getCfValueSKMap().put(
                                    field.getCustomFieldSK().toString(),
                                    String.valueOf(valueVO.getVersionNo()));
                        }
                    }
                }
            }
            if ("C".equals(logCaseDataBean.getCaseDetailsVO()
                    .getStatus()))
            {
                valueVO.setCrClosed(true);
            }
            else
            {
                valueVO.setCrClosed(false);
            }
            logCaseDataBean.setCustomFieldValueVO(valueVO);
        }
    }

    /**
     * This method is used to convertCustomFieldValueDOToVO.
     * 
     * @param customFieldValueVO
     *            Takes customFieldValueVO
     * @param valueDO
     *            Takes customFieldValueVO
     * @return CustomFieldValueVO
     */
    private CustomFieldValueVO convertCustomFieldValueDOToVO(
            CustomFieldValueVO customFieldValueVO, CustomFieldValue valueDO)
    {
        customFieldValueVO.setAddedAuditTimeStamp(valueDO
                .getAddedAuditTimeStamp());
        customFieldValueVO.setAddedAuditUserID(valueDO.getAddedAuditUserID());
        customFieldValueVO.setAuditTimeStamp(valueDO.getAuditTimeStamp());
        customFieldValueVO.setAuditUserID(valueDO.getAuditUserID());
        customFieldValueVO.setVersionNo(valueDO.getVersionNo());
        return customFieldValueVO;
    }

    /**
     * This mehod is used to setCustomFieldValue.
     * 
     * @param index
     *            Takes index
     * @param customFieldValueVO
     *            Takes customFieldValueVO
     * @param value
     *            Takes value
     * @return CustomFieldValueVO
     */
    private CustomFieldValueVO setCustomFieldValue(int index,
            CustomFieldValueVO customFieldValueVO, String value)
    {
        
        if (index == 0)
        {
            customFieldValueVO.setElementZero(value);
        }
        else if (index == 1)
        {
            customFieldValueVO.setElementOne(value);
        }
        else if (index == 2)
        {
            customFieldValueVO.setElementTwo(value);
        }
        else if (index == 3)
        {
            customFieldValueVO.setElementThree(value);
        }
        else if (index == 4)
        {
            customFieldValueVO.setElementFour(value);
        }
        else if (index == 5)
        {
            customFieldValueVO.setElementFive(value);
        }
        else if (index == 6)
        {
            customFieldValueVO.setElementSix(value);
        }
        else if (index == 7)
        {
            customFieldValueVO.setElementSeven(value);
        }
        else if (index == 8)
        {
            customFieldValueVO.setElementEight(value);
        }
        else if (index == 9)
        {
            customFieldValueVO.setElementNine(value);
        }
        else if (index == 10)
        {
            customFieldValueVO.setElementTen(value);
        }
        else if (index == 11)
        {
            customFieldValueVO.setElementEleven(value);
        }
        else if (index == 12)
        {
            customFieldValueVO.setElementTwelve(value);
        }
        else if (index == 13)
        {
            customFieldValueVO.setElementThirteen(value);
        }
        else if (index == 14)
        {
            customFieldValueVO.setElementFourteen(value);
        }
        else if (index == 15)
        {
            customFieldValueVO.setElementFifteen(value);
        }
        else if (index == 16)
        {
            customFieldValueVO.setElementSixteen(value);
        }
        else if (index == 17)
        {
            customFieldValueVO.setElementSeventeen(value);
        }
        else if (index == 18)
        {
            customFieldValueVO.setElementEighteen(value);
        }
        else if (index == 19)
        {
            customFieldValueVO.setElementNinteen(value);
        }
        else if (index == 20)
        {
            customFieldValueVO.setElementTwenty(value);
        }
        
        return customFieldValueVO;
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
    public InputCriteria getInputCriteria(String referenceDataConstant,
            String functionalArea)
    {
       
        InputCriteria inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(functionalArea);
        inputCriteria.setElementName(referenceDataConstant);
        return inputCriteria;
    }
    
    public CaseRegardingProviderVO convertProviderResultVOToProviderVO(ContactManagementProviderSearchVO providerResultVo)
    {
    	CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();
        CaseRegardingProviderVO regardingProviderVO = new CaseRegardingProviderVO();
        regardingProviderVO
                .setEntityType(ContactManagementConstants.ENTITY_TYPE_PROV);
        regardingProviderVO.setEntityTypeDesc(ContactManagementHelper
                .setShortDescription(FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
                        ContactManagementConstants.ENTITY_TYPE_PROV));
        //Modified for defectESPRD00386977 starts
        if(providerResultVo.getCmnEntySk()!=null){
        	regardingProviderVO.setEntityId(providerResultVo.getCmnEntySk().toString());
        }
        //defectESPRD00386977 ends
        //ESPRD00529618
        if(providerResultVo.getCurrentAltId()!=null){
        	regardingProviderVO.setProviderId(providerResultVo.getCurrentAltId());        	
        }
        //--ESPRD00529618
        //Added for defect ESPRD00332994 start
        ContactManagementHelper helper= new ContactManagementHelper();
        
        Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_PROVIDER);
       
        List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                FunctionalAreaConstants.PROVIDER,sysListNumber);

        regardingProviderVO.setIdType(providerResultVo.getCurrentAltIdTyCd());
        
       String desc= helper.getDescriptionFromVV(
       		regardingProviderVO.getIdType(), 
			entIDTypeReferenceData);
       regardingProviderVO.setIdType(desc);
       //Code for defect ESPRD00332994 ends

        
        
        
        if ("I".equals(providerResultVo.getIndividualGrpCd()))
        {
            regardingProviderVO.setName(providerResultVo.getProvName().getLastName()
                    + MaintainContactManagementUIConstants.EMPTY_STRING_SPACE
                    + providerResultVo.getProvName().getFirstName());
        }
        else
        {
            regardingProviderVO.setName(providerResultVo.getOrganizationName());
        }
        if (providerResultVo.getCmnEntySk() != null)
        {
        	
        	
            regardingProviderVO.setCommonEntitySK(providerResultVo.getCmnEntySk().toString());
        }
        Set provTyCdSet = providerResultVo.getProvTyCd();
        if (provTyCdSet != null)
        {
            for (Iterator iter = provTyCdSet.iterator(); iter.hasNext();)
            {
                String provTypeCd = (String) iter.next();
                regardingProviderVO.setProviderTypeCode(provTypeCd);
            }
           //Modified for the defect ESPRD00809457
            regardingProviderVO
            .setProviderTypeCodeDesc(ContactManagementHelper
                    .setShortDescription(
                            FunctionalAreaConstants.PROVIDER,
                            ReferenceServiceDataConstants.PROV_TY_CD,
                            regardingProviderVO
                                    .getProviderTypeCode()));
        }

        Set providerSpeclCdSet = providerResultVo.getSpecialityCd();
        if (providerSpeclCdSet != null)
        {
            for (Iterator iter = providerSpeclCdSet.iterator(); iter
                    .hasNext();)
            {
                String provSpclCd = (String) iter
                        .next();
                regardingProviderVO.setSpecialty(provSpclCd);
                break; // Get the first speciality
            }
            //Modified for the defect ESPRD00809457
            regardingProviderVO.setSpecialtyDesc(ContactManagementHelper
                    .setShortDescription(FunctionalAreaConstants.PROVIDER,
                            ReferenceServiceDataConstants.P_SPECL_CD,
                            regardingProviderVO.getSpecialty()));
        }

        Set enrollStatCdSet = providerResultVo.getEnrollStatCd();
        /** for defect ESPRD00837639 
         *  enrollment status returned from the db will be holding
         *  the status list for particualar sysid if any record exists
         *  with Enrollment end date > current date.
         *  If atleast one Active is present in the list then its
		 *  an active record
         *  If list is null or empty then this sysid is treated as
         *  In-Active. 
         * */
        if (enrollStatCdSet != null && !enrollStatCdSet.isEmpty())
        {
            for (Iterator iter = enrollStatCdSet.iterator(); iter
                    .hasNext();)
            {
                String enrollStatusCd = (String) iter.next();
                regardingProviderVO.setStatus(enrollStatusCd);
				if (enrollStatusCd
						.equalsIgnoreCase(ContactManagementConstants.STEP_STATUS_ACTIVE)) {
					break;
				}
               
            }
        }else {
        	regardingProviderVO.setStatus(ContactManagementConstants.CASE_CR_CONTCT_RSN_I);
        }
        if (!isNullOrEmptyField(regardingProviderVO.getStatus())) {
			// Added for the defect ESPRD00809457 Starts
			String statusDesc = ReferenceServiceDelegate
					.getReferenceSearchShortDescription(
							FunctionalAreaConstants.PROVIDER,
							ReferenceServiceDataConstants.PROVIDER_STATUS,
							new Long(1018), regardingProviderVO.getStatus());
			regardingProviderVO.setStatusDesc(statusDesc);
			// Added for the defect ESPRD00809457 Ends
		}
        return regardingProviderVO;
    }
    
    
    public void convertAltIdsForProvider(ContactManagementProviderSearchVO resultsVO)
    {
        List altIDList = new ArrayList();
        Set setProviderAltIdsList = resultsVO.getAltIdInfo();
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        if (setProviderAltIdsList != null)
        {

            for (Iterator iter = setProviderAltIdsList.iterator(); iter
                    .hasNext();)
            {
                AlternateIdInfo altId = (AlternateIdInfo) iter.next();
                AlternateIdentifiersVO altIdVO = new AlternateIdentifiersVO();

                altIdVO.setAlternateID(altId.getAlternateID());
                altIdVO.setAlternateIDTypeCode(altId.getTypeCode());
                altIdVO.setAlternateIDTypeCodeDesc(ContactManagementHelper
                        .setShortDescription(FunctionalAreaConstants.PROVIDER,
                                ReferenceServiceDataConstants.P_ALT_ID_TY_CD,
                                altId.getTypeCode()));
                //Added for defect ESPRD00882669
                altIdVO.setLineOfBusiness("MED-Medicaid");
                altIDList.add(altIdVO);
            }
            logCaseDataBean.setAlternateIdentiProvList(altIDList);
            logCaseDataBean.setShowAlternateIdentifiersProv(true);
        }
    }
    // Added for CR ESPRD00486064 starts
    /**
     * This method Converts convertTPLPolicyHolderDOToVO.
     * 
     * @param tplPolicyHolder
     *            Takes policyHolder as param.
     * @return CaseRegardingTPL
     */
    public CaseRegardingTPL convertTPLPolicyHolderDOToVO(TPLPolicyHolder policyHolder)
    {
        CaseRegardingTPL regardingTPL = new CaseRegardingTPL();
        //CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
        //regardingTPL.setEntityId(carrier.getCarrierID());
        if(policyHolder!=null && policyHolder.getCommonEntity()!=null
        		&& policyHolder.getCommonEntity().getCommonEntitySK()!=null){
        	regardingTPL.setEntityId(String.valueOf(policyHolder.getCommonEntity().getCommonEntitySK()));
        }else{
        	regardingTPL.setEntityId(ContactManagementConstants.EMPTYSTRING);
        }
        
        //EOF CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010

        regardingTPL.setEntityType(ContactManagementConstants.ENTITY_TYPE_TP);
        
        
        
        //add code on ESPRD00357163
      
        regardingTPL
                .setEntityTypeDesc(ContactManagementConstants.ENTITY_TYPE_TP
                        + ContactManagementConstants.HYPEN
                        + ContactManagementConstants.ENTITY_TYPE_TPLPOLICYHOLDER);
        //end
        if(policyHolder!=null){
        	if(policyHolder.getName()!=null){
        		String policyHolderName =ContactManagementConstants.EMPTY_STRING;
        		if(policyHolder.getName().getFirstName()!=null){
        			policyHolderName = policyHolder.getName().getFirstName();
        		}
        		if(policyHolder.getName().getLastName()!=null){
        			policyHolderName = policyHolderName+" "+policyHolder.getName().getLastName();
        		}
        		regardingTPL.setName(policyHolderName);	
        	}
            if (policyHolder.getCommonEntity() != null)
            {
                regardingTPL.setCommonEntitySK(policyHolder.getCommonEntity().getCommonEntitySK().toString());
            }
            if (policyHolder.getPolicyHolderID() != null)
            {
                regardingTPL.setPolicyHolderID(policyHolder.getPolicyHolderID());
            }
            if(policyHolder.getTplGroupPolicy()!=null){
            	regardingTPL.setTplGroupId(String.valueOf(policyHolder.getTplGroupPolicy().getPolicyReferenceSK()));	
            }
            
        }
        return regardingTPL;
    }
   
    // Added for TPL External-API for CRM.
    /**
     * This method Converts convertTPLPolicyHolderDOToVO.
     * 
     * @param tplPolicyHolder
     *            Takes policyHolder as param.
     * @return CaseRegardingTPL
     */
    public CaseRegardingTPL convertTPLPolicyHolderDOToVO(
			TPLPolicyHolderDetailsVO tplPolicyHolderDetailsVO) {
		CaseRegardingTPL regardingTPL = new CaseRegardingTPL();
		if(logger.isInfoEnabled()){
		logger.info("starting of convertTPLPolicyHolderDOToVO $$$$$$$$$$$");
		}
		// CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
		// regardingTPL.setEntityId(carrier.getCarrierID());
		if (tplPolicyHolderDetailsVO == null) {
			return regardingTPL;
		}
		if (tplPolicyHolderDetailsVO != null
				&& tplPolicyHolderDetailsVO.getCommonEntitySK() != null) {
			regardingTPL.setEntityId(String.valueOf(tplPolicyHolderDetailsVO
					.getCommonEntitySK()));
		} else {
			regardingTPL.setEntityId(ContactManagementConstants.EMPTYSTRING);
		}
		regardingTPL.setEntityType(ContactManagementConstants.ENTITY_TYPE_TP);

		regardingTPL
				.setEntityTypeDesc(ContactManagementConstants.ENTITY_TYPE_TP
						+ ContactManagementConstants.HYPEN
						+ ContactManagementConstants.ENTITY_TYPE_TPLPOLICYHOLDER);
		if (tplPolicyHolderDetailsVO.getFirstName() != null) {
			String policyHolderName = ContactManagementConstants.EMPTY_STRING;
			if (tplPolicyHolderDetailsVO.getFirstName() != null) {
				policyHolderName = tplPolicyHolderDetailsVO.getFirstName();
			}
			if (tplPolicyHolderDetailsVO.getLastName() != null) {
				policyHolderName = policyHolderName + " "
						+ tplPolicyHolderDetailsVO.getLastName();
			}
			regardingTPL.setName(policyHolderName);
		}
		if (tplPolicyHolderDetailsVO.getCommonEntitySK() != null) {
			regardingTPL.setCommonEntitySK(tplPolicyHolderDetailsVO
					.getCommonEntitySK().toString());
		}
		if (tplPolicyHolderDetailsVO.getPolicyHolderID() != null) {
			regardingTPL.setPolicyHolderID(tplPolicyHolderDetailsVO
					.getPolicyHolderID());
		}
		if (tplPolicyHolderDetailsVO.getPolicyReferenceSK() != null) {
			regardingTPL.setTplGroupId(String.valueOf(tplPolicyHolderDetailsVO
					.getPolicyReferenceSK()));
		}

		return regardingTPL;
	}
    //*************
    // Added for CR ESPRD00486064 ends
//  CR_ESPRD00373565_LOGCASE_23JUL2010
    
    static List auditableAlertDetails;
    static List auditableRoutingDetails;
    static List auditableAttachmentDetails;
    //CR_ESPRD00373565_LogCase_05AUG2010
    static List auditableCaseStepDetails;
    static List auditableCaseEventDetails;
    static List auditableAssociatedCaseDetails;
    static List auditableAssociatedCRDetails;
    static List auditableCaseDetails;
    
	  private void doAuditKeyListOperationForCaseSteps(CaseStepsVO caseStepsVO){
		  if(logger.isDebugEnabled()){
	  	logger.debug(">>---------> Inside CaseStepsVO caseStepsVO doAuditKeyListOperationForCaseSteps:");    
		  }
	    	if(auditableCaseStepDetails==null || auditableCaseStepDetails.isEmpty())
	    	{
	    		getAuditableCaseStepsDetails();
	    	}
			if(caseStepsVO.getAuditKeyList()!=null && !caseStepsVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableCaseStepDetails,caseStepsVO);
			}  
	  }
	  private void doAuditKeyListOperationForCaseEvents(CaseEventsVO caseEventVO){
		  if(logger.isDebugEnabled()){
	  	logger.debug(">>---------> Inside CaseEventsVO caseEventVO doAuditKeyListOperationForCaseEvents:");
		  }
	    	if(auditableCaseEventDetails==null || auditableCaseEventDetails.isEmpty())
	    	{
	    		getAuditableCaseEventsDetails();
	    	}
			if(caseEventVO.getAuditKeyList()!=null && !caseEventVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableCaseEventDetails,caseEventVO);
			}  
	  }
	  private void doAuditKeyListOperationForAssociatedCase(AssociatedCaseVO associatedCaseVO){
		  if(logger.isDebugEnabled()){
	  	logger.debug(">>---------> Inside AssociatedCaseVO associatedCaseVO doAuditKeyListOperationForAssociatedCase:"); 
		  }
	    	if(auditableAssociatedCaseDetails==null || auditableAssociatedCaseDetails.isEmpty())
	    	{
	    		getAuditableAssociatedCaseDetails();
	    	}
			if(associatedCaseVO.getAuditKeyList()!=null && !associatedCaseVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableAssociatedCaseDetails,associatedCaseVO);
			}  
	  }
	  
	  private void doAuditKeyListOperationForAssociatedCRs(AssociatedCorrespondenceVO caseCRVO){
		  if(logger.isDebugEnabled()){
	  	logger.debug(">>---------> Inside AssociatedCorrespondenceVO caseCRVO doAuditKeyListOperationForAssociatedCRs:");
		  }
	    	if(auditableAssociatedCRDetails==null || auditableAssociatedCRDetails.isEmpty())
	    	{
	    		getAuditableAssociatedCRDetails();
	    	}
			if(caseCRVO.getAuditKeyList()!=null && !caseCRVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableAssociatedCRDetails,caseCRVO);
			}  
	  }
	  private void doAuditKeyListOperationForCaseDetails(CaseDetailsVO caseDetailsVO){
		  if(logger.isDebugEnabled()){
	  	logger.debug(">>---------> Inside CaseDetailsVO caseDetailsVO doAuditKeyListOperationForCaseDetails:");   
		  }
	    	if(auditableCaseDetails==null || auditableCaseDetails.isEmpty())
	    	{
	    		getAuditableCaseDetails();
	    	}
			if(caseDetailsVO.getAuditKeyList()!=null && !caseDetailsVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableCaseDetails,caseDetailsVO);
			}  
	  }
	  public List getAuditableCaseDetails() {
	  	auditableCaseDetails = new ArrayList();
	  	auditableCaseDetails.add(createAuditableField("Status","caseStatusCode"));
	  	auditableCaseDetails.add(createAuditableField("Priority","casePriorityCode"));
	  	auditableCaseDetails.add(createAuditableField("Case Title","title"));
	  	auditableCaseDetails.add(createAuditableField("Line of Business","lobCode"));
	  	auditableCaseDetails.add(createAuditableField("Application Type","caseDDUApplicationTypeCode"));
	  	auditableCaseDetails.add(createAuditableField("Application Date","applicationDate"));
	  	auditableCaseDetails.add(createAuditableField("Substantially Completed Date","completedDate"));
	  	auditableCaseDetails.add(createAuditableField("Authorized Rep","caseDDUAuthorizedRepresentTyCd"));
	  	auditableCaseDetails.add(createAuditableField("Packet Received Date","packetReceivedDate"));
	  	
	  	auditableCaseDetails.add(createAuditableField("Review Required","reviewRequiredIndicator"));
	  	auditableCaseDetails.add(createAuditableField("Review Initiated Date","reviewInitiatedDate"));
	  	auditableCaseDetails.add(createAuditableField("Scheduled Date of Review","scheduleReviewDate"));
	  	auditableCaseDetails.add(createAuditableField("Received Date","receivedDate"));
	  	auditableCaseDetails.add(createAuditableField("Receipt Date","receiptDate"));
	  	auditableCaseDetails.add(createAuditableField("Evaluation Type","caseDDUEvaluateType"));
	  	auditableCaseDetails.add(createAuditableField("Medical Diagnosis","caseDDUMedicalDiagnosisCode"));
	  	auditableCaseDetails.add(createAuditableField("Psychiatric Diagnosis","caseDDUpsychiatricDiagnosisCode"));
	  	auditableCaseDetails.add(createAuditableField("Response Indicator","responseIndicator"));
	  	
	  	auditableCaseDetails.add(createAuditableField("Decision Date","decisionDate"));
	  	auditableCaseDetails.add(createAuditableField("Approval Begin Date","applicationBeginDate"));
	  	auditableCaseDetails.add(createAuditableField("Appeal Decision Date","appealDecisionDate"));
	  	auditableCaseDetails.add(createAuditableField("Level Of Care","caseDDULOCCode"));
	  	auditableCaseDetails.add(createAuditableField("Denial reason","caseDDUdenialReasonCode"));
	  	auditableCaseDetails.add(createAuditableField("Close Code","caseDDUCloseTypeCode"));
	  	auditableCaseDetails.add(createAuditableField("State Fiscal Year End","fiscalYearEndDate"));
	  	auditableCaseDetails.add(createAuditableField("Facility Fiscal Year End","facilityFiscalYearEndDate"));
	  	auditableCaseDetails.add(createAuditableField("Home Office Indicator","homeOfficeInd"));
	  	
	  	auditableCaseDetails.add(createAuditableField("Field Audit Required","fieldAuditRequiredInd"));
	  	auditableCaseDetails.add(createAuditableField("Field Audit Date","fieldAuditDate"));
	  	auditableCaseDetails.add(createAuditableField("Desk Review Start Date","deskReviewDate"));
	  	auditableCaseDetails.add(createAuditableField("Appeal Received","appealReceivedIndicator"));
	  	auditableCaseDetails.add(createAuditableField("Field Audit Required","fieldAuditRequiredInd"));
	  	auditableCaseDetails.add(createAuditableField("Picture Date","caseARSCRPICDate"));
	  	auditableCaseDetails.add(createAuditableField("Private Pay Rates Loaded","privatePayRatesLoadedInd"));
	  	auditableCaseDetails.add(createAuditableField("Rate Setting Date","rateSettingDate"));
	  	
	  	auditableCaseDetails.add(createAuditableField("BCCP ID","bccpID"));	  	
	  	auditableCaseDetails.add(createAuditableField("Contact Person","contactName"));
	  	auditableCaseDetails.add(createAuditableField("Enrollment Date","screeningEnrollDate"));
	  	auditableCaseDetails.add(createAuditableField("Agency Site","caseBCCPScreeningSiteCode"));
	  	auditableCaseDetails.add(createAuditableField("Agency Case Manager","screeningAgencyCMText"));
	  	auditableCaseDetails.add(createAuditableField("Agency Phone Number","phoneNumber"));
	  	auditableCaseDetails.add(createAuditableField("Biopsy Date","biopsyDate"));
	  	auditableCaseDetails.add(createAuditableField("Biopsy Findings","caseBCCPBiopsyFundingCode"));
	  	auditableCaseDetails.add(createAuditableField("Recommendation","bccpCaseRecommendCode"));
	  	auditableCaseDetails.add(createAuditableField("ST Follow-Up (months)","recommendFOllowupMONumber"));
	  	auditableCaseDetails.add(createAuditableField("Consult Date","consultantDate"));
	  	auditableCaseDetails.add(createAuditableField("Stage","caseBCCPFinalCancerStageCode"));
	  	auditableCaseDetails.add(createAuditableField("Metastasis Area","bccpFinalMTSTSAreaCode"));
	  	auditableCaseDetails.add(createAuditableField("Unstaged Reason","caseBCCPUNSTGReasonCode"));
	  	auditableCaseDetails.add(createAuditableField("Tumor Size","finalTumorCMSizeNumber"));
	  	auditableCaseDetails.add(createAuditableField("Nodes Positive","finalCancerNodesPositiveNumber"));
	  	auditableCaseDetails.add(createAuditableField("Treatment Started","caseBCCPTreatmentStartedCode"));
	  	auditableCaseDetails.add(createAuditableField("Treatment Started Date","treatmentStartedDate"));
	  	auditableCaseDetails.add(createAuditableField("Chemo Projected End Date","chemoProjectedEndDate"));
	  	auditableCaseDetails.add(createAuditableField("Care Giver Name","caregiverName"));
	  	auditableCaseDetails.add(createAuditableField("Care Giver Phone Number","phoneNumber"));
	  	auditableCaseDetails.add(createAuditableField("Form 778 signed","form778SignIndicator"));
	  	

		return auditableCaseDetails;
	}
	  public List getAuditableAssociatedCRDetails() {
	  	auditableAssociatedCRDetails = new ArrayList();
	  	auditableAssociatedCRDetails.add(createAuditableField("ID","correspondenceSK"));
	  	auditableAssociatedCRDetails.add(createAuditableField("Date","openDate"));
	  	auditableAssociatedCRDetails.add(createAuditableField("Status","statusCode"));
	  	auditableAssociatedCRDetails.add(createAuditableField("Subject","subjectCode"));
	
		return auditableAssociatedCRDetails;
	}

	  
	  public List getAuditableAssociatedCaseDetails() {
	  	auditableAssociatedCaseDetails = new ArrayList();
	  	auditableAssociatedCaseDetails.add(createAuditableField("ID","caseSK"));
	  	auditableAssociatedCaseDetails.add(createAuditableField("Created Date","openDate"));
	  	auditableAssociatedCaseDetails.add(createAuditableField("Status","caseStatusCode"));
	  	auditableAssociatedCaseDetails.add(createAuditableField("Case Type","description"));
	
		return auditableAssociatedCaseDetails;
	}

	  public List getAuditableCaseEventsDetails() {
		auditableCaseEventDetails = new ArrayList();
		auditableCaseEventDetails.add(createAuditableField("Created Date","addedAuditTimeStamp"));
		auditableCaseEventDetails.add(createAuditableField("Type","cmCaseEventCode"));
		auditableCaseEventDetails.add(createAuditableField("Description","description"));
		auditableCaseEventDetails.add(createAuditableField("Event Date","eventDate"));
		auditableCaseEventDetails.add(createAuditableField("Time","eventDate"));
		auditableCaseEventDetails.add(createAuditableField("Notify Via Alert","userID"));
		auditableCaseEventDetails.add(createAuditableField("Alert Based On","alertBasedOnColName"));
		auditableCaseEventDetails.add(createAuditableField("Send Alert Days","sendAlertDaysCode"));
		auditableCaseEventDetails.add(createAuditableField("Before After","beforeAfterCode"));
		auditableCaseEventDetails.add(createAuditableField("Status","eventStatusCode"));
		auditableCaseEventDetails.add(createAuditableField("Disposition Date","dispositionDate"));
		auditableCaseEventDetails.add(createAuditableField("Template","letterTemplate"));
		auditableCaseEventDetails.add(createAuditableField("Diagnosis 1","diagnosticExam1Text"));
		auditableCaseEventDetails.add(createAuditableField("Diagnosis 2","diagnosticExam2Text"));
		auditableCaseEventDetails.add(createAuditableField("Provider/Hospital","providerCommonEntitySK"));
		
		return auditableCaseEventDetails;
	}

	  public List getAuditableCaseStepsDetails() {
		auditableCaseStepDetails = new ArrayList();
		auditableCaseStepDetails.add(createAuditableField("Order","stepOrderNumber"));
		auditableCaseStepDetails.add(createAuditableField("Description","cmCaseStepCode"));	
		auditableCaseStepDetails.add(createAuditableField("Route To","workUnitSK"));	
		auditableCaseStepDetails.add(createAuditableField("Status","stepStatusCode"));	
		auditableCaseStepDetails.add(createAuditableField("Date Started","startDate"));
		auditableCaseStepDetails.add(createAuditableField("Expected Days to Complete","daysToCompleteNumber"));	
		auditableCaseStepDetails.add(createAuditableField("Completion Based On","completBasedOnColName"));	
		auditableCaseStepDetails.add(createAuditableField("Expected Completion Date","expectedCompletionDate"));
		auditableCaseStepDetails.add(createAuditableField("Notify via Alert","workUnitSK"));
		auditableCaseStepDetails.add(createAuditableField("Alert Based On","alertBasedOnColName"));	
		auditableCaseStepDetails.add(createAuditableField("Send Alert Days","sendAlertDaysCode"));	
		auditableCaseStepDetails.add(createAuditableField("Send Alert Days","beforeAfterCode"));
		auditableCaseStepDetails.add(createAuditableField("Template","letterTemplate"));
		
		return auditableCaseStepDetails;
	}

    //EOF CR_ESPRD00373565_LogCase_05AUG2010

	  private void doAuditKeyListOperationForAlerts(AlertVO alertVO){
		  if(logger.isDebugEnabled()){
	  	logger.debug(">>---------> Inside caseTypeVO caseTypeVO doAuditKeyListOperation:");  
		  }
	    	if(auditableAlertDetails==null || auditableAlertDetails.isEmpty())
	    	{
	    		getAuditableAlertDetails();
	    	}
			if(alertVO.getAuditKeyList()!=null && !alertVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableAlertDetails,alertVO);
			}  
	  }
	  private void doAuditKeyListOperationForRouting(CMRoutingVO cMRoutingVO){
		  if(logger.isDebugEnabled()){
	  	logger.debug(">>---------> Inside CMRoutingVO cMRoutingVO doAuditKeyListOperationForRouting:");    
		  }
	    	if(auditableRoutingDetails==null || auditableRoutingDetails.isEmpty())
	    	{
	    		getAuditableRoutingDetails();
	    	}
			if(cMRoutingVO.getAuditKeyList()!=null && !cMRoutingVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableRoutingDetails,cMRoutingVO);
			}  
	  }
	  private void doAuditKeyListOperationForAttachment(AttachmentVO attachmentVO){
		  if(logger.isDebugEnabled()){
	  	logger.debug(">>---------> Inside AttachmentVO attachmentVO doAuditKeyListOperationForAttachment:"); 
		  }
	    	if(auditableAttachmentDetails==null || auditableAttachmentDetails.isEmpty())
	    	{
	    		getAuditableAttachmentDetails();
	    	}
			if(attachmentVO.getAuditKeyList()!=null && !attachmentVO.getAuditKeyList().isEmpty())
			{
				AuditDataFilter.filterAuditKeys(auditableAttachmentDetails,attachmentVO);
			}  
	  }


/**
	 * @return Returns the auditableAlertDetails.
	 */
	public List getAuditableAlertDetails() {
		auditableAlertDetails = new ArrayList();
		auditableAlertDetails.add(createAuditableField("Status","alertStatusCode"));		
/*		auditableAlertDetails.add(createAuditableField("Due Date","dueDate"));
		auditableAlertDetails.add(createAuditableField("Alert Type","alertTypeCode"));
		auditableAlertDetails.add(createAuditableField("Description","description"));
*/		
		return auditableAlertDetails;
	}
	/**
	 * @return Returns the auditableRoutingDetails.
	 */
	public List getAuditableAttachmentDetails() {
		auditableAttachmentDetails = new ArrayList();
		//ESPRD00736691
		//auditableAttachmentDetails.add(createAuditableField("Add this Record to my Watchlist","watchListIndicator"));		
		
		auditableAttachmentDetails.add(createAuditableField("Date Added",
				"attachmentCreatedDate"));
		auditableAttachmentDetails.add(createAuditableField("Added By",
				"attachmentAddedByName"));
		auditableAttachmentDetails.add(createAuditableField("File Name",
				"attachmentFileName"));
		auditableAttachmentDetails.add(createAuditableField("Description",
				"attachmentDescription"));	
		
		return auditableAttachmentDetails;
	}
	/**
	 * @return Returns the auditableRoutingDetails.
	 */
	public List getAuditableRoutingDetails() {
		auditableRoutingDetails = new ArrayList();
		// Modified for the Defect ESPRD00736691 Start
		auditableRoutingDetails.add(createAuditableField("Created by","routedByWorkUnit"));		
		auditableRoutingDetails.add(createAuditableField("Assigned to","routedToWorkUnit"));
		auditableRoutingDetails.add(createAuditableField("Reporting Unit","lobHierarchyDescription"));
		auditableRoutingDetails.add(createAuditableField("Add this Record to my Watchlist","watchlistIndicator"));
		// Modified for the Defect ESPRD00736691 Ends
		return auditableRoutingDetails;
	}

	 private AuditableField createAuditableField(String fieldName,String domainAttributeName){
	 	AuditableField auditableField = new AuditableField();
	 	auditableField.setFieldName(fieldName);
	 	auditableField.setDomainAttributeName(domainAttributeName);
	 	return auditableField;
	 }
//	EOF CR_ESPRD00373565_LOGCASE_23JUL2010
	 
	 private String getName(WorkUnit workUnit, String workUnitTypeCode)
	    {
		 if(logger.isInfoEnabled()){
	        logger.info("getName");
		 }

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
	 
	 private boolean isNullOrEmptyField(String inputField)
	    {
		 if(logger.isInfoEnabled()){
	        logger.info("isNullOrEmptyField");
		 }

	        return (inputField == null || ContactManagementConstants.EMPTY_STRING
	                .equalsIgnoreCase(inputField.trim()));
	    }
	 
	 public CaseRegardingTradingPartnerVO convertTradingPartnerDOToVO(TradingPartner tradePartner)
	    {
	        CaseRegardingTradingPartnerVO regardingTradePartner = new CaseRegardingTradingPartnerVO();
	        //CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
	        //regardingTPL.setEntityId(carrier.getCarrierID());
	        /*if(tradePartner!=null && tradePartner.getCommonEntity()!=null
	        		&& tradePartner.getCommonEntity().getCommonEntitySK()!=null){
	        	regardingTradePartner.setEntityId(tradePartner.getCommonEntity());
	        }else{
	        	regardingTradePartner.setEntityId(ContactManagementConstants.EMPTYSTRING);
	        }*/
	        
	        //EOF CR_ESPRD00439068_UC-PGM-CRM-033_6APR2010
	        
	        regardingTradePartner.setEntityId(tradePartner.getTradPartId());
	        
	        regardingTradePartner.setEntityType("TD");
	        
	        
	        
	        //add code on ESPRD00357163
	      
	        regardingTradePartner
	                .setEntityTypeDesc("TD"
	                        + ContactManagementConstants.HYPEN
	                        + "TrdngPartn");
	        //end
	        if(tradePartner.getName()!=null){
	        	regardingTradePartner.setName(tradePartner.getName().getSortName());	        	
	        }
	        if(tradePartner.getClassification()!=null){
	        	//regardingTradePartner.setClassification(tradePartner.getClassification());
	        	//DEFECT_ESPRD00740424_CRM_UC_PGM_CRM_20_18-01-12
	        	String classificationstr = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						 FunctionalAreaConstants.EDI_TRADING,
						"1003",
						new Long(1003),
						tradePartner.getClassification());
	        	regardingTradePartner.setClassification(classificationstr);
	        }
	        if(tradePartner.getTradPartId()!=null){
	        	regardingTradePartner.setTradingPartnerId(tradePartner.getTradPartId());
	        }
	        if(tradePartner.getApprovalProcessStatusCode()!=null){
	        	//DEFECT_ESPRD00740424_CRM_UC_PGM_CRM_20_18-01-12
	        	//regardingTradePartner.setStatus(tradePartner.getApprovalProcessStatusCode());
	        	String statusstr = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						 FunctionalAreaConstants.EDI_TRADING,
						"003",
						new Long(003),
						tradePartner.getApprovalProcessStatusCode());
	        	regardingTradePartner.setStatus(statusstr);
	        }
	        if(tradePartner.getCommonEntity() != null && tradePartner.getCommonEntity().getCommonEntitySK() != null){
	        	regardingTradePartner.setCommonEntitySK(tradePartner.getCommonEntity().getCommonEntitySK().toString());
	        }
	        regardingTradePartner.setVersionNo(tradePartner.getVersionNo());
	        	
	     
	        return regardingTradePartner;
	    }
	 
	 
	 /**
	 * This method is used to convert the MemberDO to vo
	 * for defect ESPRD00884340
	 * @param memberBasicInfo
	 *            holds the MemberBasicInfo Object
	 * @return CaseRegardingMemberVO it returns the CaseRegardingMemberVO object
	 */
	public CaseRegardingMemberVO convertMemberToVo(
			MemberBasicInfo memberBasicInfo) {
		CaseRegardingMemberVO regardingMemberVO = new CaseRegardingMemberVO();
		if (memberBasicInfo != null) {
			DateFormat dateFormat = new SimpleDateFormat(
					ContactManagementConstants.DATE_FORMAT);
			regardingMemberVO
					.setEntityType(ContactManagementConstants.ENTITY_TYPE_MEM);
			if (memberBasicInfo.getCmnEntySK() != null) {
				regardingMemberVO.setEntityId(String.valueOf(memberBasicInfo
						.getCmnEntySK()));
				if (memberBasicInfo.getCurrAltID() != null) {
					regardingMemberVO.setMemberId(memberBasicInfo
							.getCurrAltID());
				}
			} else {
				regardingMemberVO
						.setEntityId(ContactManagementConstants.EMPTY_STRING);
			}
			regardingMemberVO.setMemberIDType(memberBasicInfo
					.getCurrAltIDTypeCD());
			regardingMemberVO.setMemberIDTypeDesc(ContactManagementHelper
					.setShortDescription(FunctionalAreaConstants.MEMBER,
							ReferenceServiceDataConstants.B_ALT_ID_TY_CD,
							regardingMemberVO.getMemberIDType()));
			Set setOfPreviousNames = null;
			Name name = memberBasicInfo.getPreviousName();
			if (name != null) {
				if (name.getMiddleName() != null) {
					regardingMemberVO.setPreviousName(name.getFirstName()
							+ ContactManagementConstants.SPACE_STRING
							+ name.getMiddleName()
							+ ContactManagementConstants.SPACE_STRING
							+ name.getLastName());
				} else {

					regardingMemberVO.setPreviousName(name.getFirstName()
							+ ContactManagementConstants.SPACE_STRING
							+ name.getLastName());
				}
			}
			regardingMemberVO.setCategoryOfEligibility(memberBasicInfo
					.getCoeCD());
			regardingMemberVO.setCoeCodeDesc(memberBasicInfo.getCoeCD());
			regardingMemberVO.setDistrictOffice(memberBasicInfo
					.getDistrictOfficeCD());
			regardingMemberVO.setDistrictOfficeDesc(ContactManagementHelper
					.setShortDescription(FunctionalAreaConstants.MEMBER,
							ReferenceServiceDataConstants.B_CASE_DSTCT_OFC_CD,
							regardingMemberVO.getDistrictOffice()));
			regardingMemberVO.setDateofBirth(dateFormat.format(memberBasicInfo
					.getDob()));
			regardingMemberVO.setName(memberBasicInfo.getFirstName()
					+ ContactManagementConstants.SPACE_STRING
					+ memberBasicInfo.getLastName());
			List memberBasicInfolist = memberBasicInfo.getAlternativeIDs();
			for (Iterator iter = memberBasicInfolist.iterator(); iter.hasNext();)

			{
				AlternateMemberID altdetails = (AlternateMemberID) iter.next();

				if (altdetails.getAltIDTypeCode() != null
						&& "SSN"
								.equalsIgnoreCase(altdetails.getAltIDTypeCode())) {
					regardingMemberVO.setMemberSSN(altdetails.getAltID());
					break;
				}
			}
			regardingMemberVO.setEmail(memberBasicInfo.getEmail());
			regardingMemberVO.setCommonEntitySK(memberBasicInfo.getCmnEntySK()
					.toString());
		}
		return regardingMemberVO;
	}
	
	/**
     * This Method convertAltIdsForMember.
     * 
     * @param memberBasicInfo
     *            Takes MemberBasicInfo object
     */
    public void convertAltIdsForMember(MemberBasicInfo memberBasicInfo)
    {
        logCaseDataBean = (CMLogCaseDataBean) getDataBean(ContactManagementConstants.CMLOGCASE_DATA_BEAN);
        List memberAltIDs = null;
        List altIDList = new ArrayList();
        memberAltIDs = memberBasicInfo.getAlternativeIDs();
        if (memberAltIDs != null && !memberAltIDs.isEmpty())
        {
            for (Iterator iter = memberAltIDs.iterator(); iter.hasNext();)
            {
                AlternateMemberID altId = (AlternateMemberID) iter.next();
                if (altId != null)
                {
                    AlternateIdentifiersVO alternateIdentifiersVO = new AlternateIdentifiersVO();
                    alternateIdentifiersVO.setAlternateIDTypeCode(altId
                            .getAltIDTypeCode());
                    alternateIdentifiersVO
                            .setAlternateIDTypeCodeDesc(ContactManagementHelper
                                    .setShortDescription(
                                            FunctionalAreaConstants.MEMBER,
                                            ReferenceServiceDataConstants.B_ALT_ID_TY_CD,
                                            altId.getAltIDTypeCode()));
                    alternateIdentifiersVO.setAlternateID(altId.getAltID());
                    
                    
                    
                    /** ES2 --AlternateMemberID DO changed   */
                    alternateIdentifiersVO.setLineOfBusiness(altId.getLobCode());
                    //Added for defect ESPRD00882669
                    alternateIdentifiersVO.setLineOfBusinessDesc(ContactManagementHelper
                                    .setShortDescription(
                                            FunctionalAreaConstants.REFERENCE,
                                            ReferenceServiceDataConstants.R_LOB_CODE,
                                            altId.getLobCode()));
                    
                    altIDList.add(alternateIdentifiersVO);
                }
            }
            logCaseDataBean.setAlternateIdentiMemList(altIDList);
            logCaseDataBean.setShowAlternateIdentifiersMem(true);
        }
    }
    /***
     * 
     * @param EnterpriseUser
     * @return users details in concatenated String for display purpose. lastName,Firstnam-UserId
     */
    private String getNameforUserId(EnterpriseUser user)
    {
    	if(logger.isInfoEnabled()){
            logger.info("getUserName");
    	 }

            String name = ContactManagementConstants.EMPTY_STRING;
            StringBuffer sbName = null;
            	sbName = new StringBuffer();
                if (!isNullOrEmptyField(user.getLastName()))
                {
                	sbName
    				.append(user.getLastName())
    				.append(",")
    				.append(
    						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
                }
                if(!isNullOrEmptyField(user.getLastName()) &&
                		!isNullOrEmptyField(user.getFirstName()))
                {
                	sbName
    				.append(user.getFirstName())
    				.append(
    						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
    				.append(
    						MaintainContactManagementUIConstants.HYPHEN);
                }
                sbName
    			.append(
    					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
    			.append(user.getUserID());
                name = sbName.toString();
        return name;
    }
    
    /***
     * 
     * @param WorkUnit
     * @return users details in concatenated String for display purpose. lastName,Firstnam-UserId
     */
    private String getUserName(WorkUnit workUnit)
    {
	 if(logger.isInfoEnabled()){
        logger.info("getUserName");
	 }

        String name = ContactManagementConstants.EMPTY_STRING;
        StringBuffer sbName = null;
        if (workUnit != null)
        {
        	sbName = new StringBuffer();
            EnterpriseUser user = workUnit.getEnterpriseUser();
            if (!isNullOrEmptyField(user.getLastName()))
            {
            	sbName
				.append(user.getLastName())
				.append(",")
				.append(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
            }
            if(!isNullOrEmptyField(user.getLastName()) &&
            		!isNullOrEmptyField(user.getFirstName()))
            {
            	sbName
				.append(user.getFirstName())
				.append(
						MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
				.append(
						MaintainContactManagementUIConstants.HYPHEN);
            }
            sbName
			.append(
					MaintainContactManagementUIConstants.EMPTY_STRING_SPACE)
			.append(user.getUserID());
            name = sbName.toString();
        }
        
        return name;
    }
}
