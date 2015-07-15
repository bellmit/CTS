 /*
  * Created on Dec 12, 2007 TODO To change the template for this generated file
  * go to Window - Preferences - Java - Code Style - Code Templates
  */

 package com.acs.enterprise.common.program.contactmanagement.view.helper;

 import java.sql.Timestamp;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;

 import javax.faces.context.FacesContext;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
 import com.acs.enterprise.common.base.common.domain.Name;
 import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
 import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
 import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
 import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
 import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
 import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
 import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
 import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
 import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
 import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
 import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
 import com.acs.enterprise.common.program.benefitadministration.common.domain.LineOfBusiness;
 import com.acs.enterprise.common.program.commonentities.common.domain.CmnSpecEntyXref;
 import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;
 import com.acs.enterprise.common.program.commonentities.common.domain.SpecificEntity;
 import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
 import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
 import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
 import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
 import com.acs.enterprise.common.program.commonentities.view.vo.NameVO;
 import com.acs.enterprise.common.program.commonentities.view.vo.PhoneVO;
 import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
 import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
 import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
 import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
 import com.acs.enterprise.common.program.contactmanagement.common.vo.EntitySearchCriteriaVO;
 import com.acs.enterprise.common.program.contactmanagement.common.vo.EntitySearchResultsVO;
 import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
 import com.acs.enterprise.common.program.contactmanagement.view.bean.CMEntityMaintainControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CMEntitySearchDataBean;
 import com.acs.enterprise.common.program.contactmanagement.view.vo.CMEntityDetailVO;
 import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
 import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
 import com.acs.enterprise.common.util.logger.EnterpriseLogger;
 import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;


 /**
  * @author vijaymai TODO To change the template for this generated type comment
  *         go to Window - Preferences - Java - Code Style - Code Templates
  */
 public class CMEntityDOConvertor
         extends DataTransferObjectConverter
 {

     /** Enterprise Logger for Logging. */
     private transient EnterpriseLogger logger = EnterpriseLogFactory
             .getLogger(CMEntityDOConvertor.class);

     /**
      * Creates constructor of CMEntityDOConvertor class.
      */
     public CMEntityDOConvertor()
     {
         super();
         
     }

     /**
      * This Method converts CMEntityDetailVO to SpecificEntity.
      *
      * @param cmEntiydetailVO
      *            Takes CMEntityDetailVO as param.
      * @return SpecificEntity
      */
     public SpecificEntity convertCMEntityVOToDO(CMEntityDetailVO cmEntiydetailVO)
     {
         
         SpecificEntity specificEntity = new SpecificEntity();
         CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
         CommonEntity commonEntityDO = null;

         try
         {
             commonEntityDO = commonEntityValidator.getValidCommonEntityDO();
             
             if(cmEntiydetailVO.getCommonEntityVO() != null){
             commonEntityDO.setVersionNo(cmEntiydetailVO.getCommonEntityVO().getVersionNo());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("Checking for common entity version number::"+commonEntityDO.getVersionNo());
             }
             }
             
             if (!isNullOrEmptyField(cmEntiydetailVO.getCmEntityID()))
             {
                 commonEntityDO.setCommonEntitySK(Long.valueOf(cmEntiydetailVO
                         .getCmEntityID()));
             }
             /*if(cmEntiydetailVO.getEntityType()!=null && cmEntiydetailVO.getEntityType().trim().length() > 0){
                 commonEntityDO.setCommonEntityTypeCode(cmEntiydetailVO
                         .getEntityType());
                 }*/
             commonEntityDO.setCommonEntityTypeCode("SE");

         }
         catch (Exception commonEntityUIException)
         {
             
        	 if(logger.isErrorEnabled())
        	 {
        		 logger.error("Error Getting commonEntity DO" + commonEntityUIException);
        	 }
         }

         /** Audit Information */
		 //Modified for CRM_CASE-GAI-Recursive Call-FIX
         String userId=getCmEntitySearchDataBean().getUserId();
         if(userId==null || userId.trim().equals("")){
		 userId=getUserID();
		 }
         specificEntity.setAddedAuditTimeStamp(getTimeStamp());
         specificEntity.setAddedAuditUserID(userId);
         specificEntity.setAuditTimeStamp(getTimeStamp());
         specificEntity.setAuditUserID(userId);

         if (cmEntiydetailVO.getEntityType() != null
                 && cmEntiydetailVO.getEntityType().length() > 0)
         {
        	 if(logger.isDebugEnabled())
        	 {
        		 logger.debug("Inside ent type -----conv not null"  + cmEntiydetailVO.getEntityType());
        	 }
             specificEntity.setSpecificEntityTypeCode(cmEntiydetailVO
                     .getEntityType());
         }

         if (cmEntiydetailVO.getCmEntityID() != null
                 && cmEntiydetailVO.getCmEntityID().length() > 0)
         {
             specificEntity.setSpecificEntitySK(new Long(cmEntiydetailVO
                     .getCmEntityID()));
         }

         if (cmEntiydetailVO.getPrefix() != null
                 && cmEntiydetailVO.getPrefix().length() > 0)
         {
             specificEntity.setNamePrefixCode(cmEntiydetailVO.getPrefix());
         }

         if (cmEntiydetailVO.getNameVO() != null)
         {
             Name name = new Name();
             
            name.setFirstName(cmEntiydetailVO.getNameVO().getFirstName());
            if(cmEntiydetailVO.getNameVO().getMiddleName()!= null && ! cmEntiydetailVO.getNameVO().getMiddleName().equalsIgnoreCase(""))
            {
            	name.setMiddleName(cmEntiydetailVO.getNameVO().getMiddleName());
            }else
            {
            	name.setMiddleName(null);
            }
             name.setLastName(cmEntiydetailVO.getNameVO().getLastName());
             if(cmEntiydetailVO.getNameVO().getSuffixName()!= null && !cmEntiydetailVO.getNameVO().getSuffixName().equalsIgnoreCase(""))
             {
             	name.setSuffixName(cmEntiydetailVO.getNameVO().getSuffixName());
             }else
             {
             	name.setSuffixName(null);
             }
             if(cmEntiydetailVO.getNameVO().getTitleName()!= null && !cmEntiydetailVO.getNameVO().getTitleName().equalsIgnoreCase(""))
             {
             	name.setTitleName(cmEntiydetailVO.getNameVO().getTitleName());
             }else
             {
             	name.setTitleName(null);
             }
             /** Sets the version No*/
             name.setVersionNo(cmEntiydetailVO.getVersionNo());
             specificEntity.setName(name);

         }



         if (cmEntiydetailVO.getSsnNumber() != null
                 && cmEntiydetailVO.getSsnNumber().length() > 0)
         {
             specificEntity.setSsnNumber(cmEntiydetailVO.getSsnNumber().replaceAll("-", ""));
         }

         if (cmEntiydetailVO.getEmployeeIdentificationNumber() != null
                 && cmEntiydetailVO.getEmployeeIdentificationNumber().length() > 0)
         {
             // Do have to add ein

             specificEntity.setTaxID(cmEntiydetailVO
                     .getEmployeeIdentificationNumber());
         }
         /*
          * Change done for ES2 DO changes
          */
         if (cmEntiydetailVO.getLineOfBusiness() != null
                 && cmEntiydetailVO.getLineOfBusiness().length() > 0)
         {
         	specificEntity.setLob(new LineOfBusiness());
             if (specificEntity.getLob() != null)
             {
                 specificEntity.getLob().setLobCode(
                         cmEntiydetailVO.getLineOfBusiness());
             }
         }

         /** Add to enroll id */
         if (cmEntiydetailVO.getEntityID() != null
                 && cmEntiydetailVO.getEntityID().length() > 0)
         {
             
             /*
              * Change done for ES2 DO changes
              */
             //specificEntity.setEnrolID(cmEntiydetailVO.getEntityID());
         }

         if (cmEntiydetailVO.getOrganizationType() != null
                 && cmEntiydetailVO.getOrganizationType().length() > 0)
         {
             specificEntity.setOrginalTypeCode(cmEntiydetailVO
                     .getOrganizationType());
         }

         if (cmEntiydetailVO.getOrganizationName() != null
                 && cmEntiydetailVO.getOrganizationName().length() > 0)
         {
        	 if(logger.isDebugEnabled())
        	 {
        		 logger.debug("inside convertor --org name -in vo---"
        				 + cmEntiydetailVO.getOrganizationName());
        	 }
             specificEntity.setOrganizationName(cmEntiydetailVO
                     .getOrganizationName());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("inside convertor --org name -in DO---"
            			 + specificEntity.getOrganizationName());
             }
         }
         if (cmEntiydetailVO.getProviderType() != null
                 && cmEntiydetailVO.getProviderType().length() > 0)
         {
             specificEntity.setProviderTypeCode(cmEntiydetailVO
                     .getProviderType());
         }
         if (cmEntiydetailVO.getNationalProviderID() != null
                 && cmEntiydetailVO.getNationalProviderID().length() > 0)
         {
             specificEntity
                     .setNpiNumber(cmEntiydetailVO.getNationalProviderID());
         }
         if (cmEntiydetailVO.getNationalProviderID() != null
                 && cmEntiydetailVO.getNationalProviderID().length() > 0)
         {
             specificEntity
                     .setNpiNumber(cmEntiydetailVO.getNationalProviderID());
         }
         if (cmEntiydetailVO.getDistrictOfficeCode() != null
                 && cmEntiydetailVO.getDistrictOfficeCode().length() > 0)
         {
        	 if(logger.isDebugEnabled())
        	 {
        		 logger.debug("Inside create ---District Office Code"
        				 + cmEntiydetailVO.getDistrictOfficeCode());
        	 }
             specificEntity.setDistrictOfficeCode(cmEntiydetailVO
                     .getDistrictOfficeCode());
         }
         //ES2 Changes
         if (cmEntiydetailVO.getCountyCode() != null
                 && cmEntiydetailVO.getCountyCode().length() > 0)
         {
        	 if(logger.isDebugEnabled())
        	 {
        		 logger.debug("Inside create ---County Code"
        				 + cmEntiydetailVO.getCountyCode());
        	 }
             specificEntity.setCountyCode(cmEntiydetailVO
                     .getCountyCode());
         }
         //ES2 Changes
         if (cmEntiydetailVO.getCountyName() != null
                 && cmEntiydetailVO.getCountyName().length() > 0)
         {
        	 if(logger.isDebugEnabled())
        	 {
        		 logger.debug("inside convertor --County Name -in vo---"
                     + cmEntiydetailVO.getCountyName());
        	 }
             specificEntity.setOrganizationName(cmEntiydetailVO
                     .getCountyName());
             if(logger.isDebugEnabled())
             {
             logger.debug("inside convertor --County Name -in DO---"
                     + specificEntity.getOrganizationName());
             }
         }

         /**Sets the commonentity do  */
         if (commonEntityDO != null)
         {

             specificEntity.setCommonEntity(commonEntityDO);

         }

         /** Sets the version no */
         specificEntity.setVersionNo(cmEntiydetailVO.getVersionNo());
         return specificEntity;

     }

     /**
      * This Method converts SpecificEntity to CMEntityDetailVO .
      *
      * @param specificEntity
      *            Takes specificEntity as param.
      * @return CMEntityDetailVO
      */
     public CMEntityDetailVO convertCmEntityDOToVO(SpecificEntity specificEntity, boolean controlRequired)
     {
         
         ContactHelper contactHelper = new ContactHelper();
         CMEntityDetailVO cmEntityDetailVO = new CMEntityDetailVO();

         /**Sets the version no  */
         cmEntityDetailVO.setVersionNo(specificEntity.getVersionNo());         
         /** Sets Audit Info in to the Main VO */
         cmEntityDetailVO.setAddedAuditTimeStamp(specificEntity
                 .getAddedAuditTimeStamp());
         cmEntityDetailVO.setAddedAuditUserID(specificEntity
                 .getAddedAuditUserID());

         cmEntityDetailVO.setAuditUserID(specificEntity.getAuditUserID());

         cmEntityDetailVO.setAuditTimeStamp(specificEntity.getAuditTimeStamp());
         /** Set the main contents */
         if (specificEntity.getSpecificEntityTypeCode() != null
                 && specificEntity.getSpecificEntityTypeCode().length() > 0)
         {
             cmEntityDetailVO.setEntityType(specificEntity
                     .getSpecificEntityTypeCode());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("In convertor ---cmEntityDetailVO.entype-->"
            			 + cmEntityDetailVO.getEntityType());
             }
         }
         if (specificEntity.getSpecificEntitySK() != null)
         {
             cmEntityDetailVO.setCmEntityID(specificEntity.getSpecificEntitySK()
                     .toString());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("In convertor ---cmEntityDetailVO.setCmEntityID-->"
            			 + cmEntityDetailVO.getCmEntityID());
             }
         }
         if (specificEntity.getNamePrefixCode() != null
                 && specificEntity.getNamePrefixCode().length() > 0)
         {
             cmEntityDetailVO.setPrefix(specificEntity.getNamePrefixCode());
         }

         if (specificEntity.getName() != null)
         {
             NameVO nameVO = new NameVO();
             nameVO.setFirstName(specificEntity.getName().getFirstName());
             nameVO.setMiddleName(specificEntity.getName().getMiddleName());
             nameVO.setLastName(specificEntity.getName().getLastName());
             nameVO.setSuffixName(specificEntity.getName().getSuffixName());
             /** Sets the version no */
             nameVO.setVersionNo(specificEntity.getName().getVersionNo());
             cmEntityDetailVO.setNameVO(nameVO);
             if(logger.isDebugEnabled())
             {
            	 logger.debug("In convertor ---cmEntityDetailVOname-->"
                     + cmEntityDetailVO.getNameVO().getFirstName());
             }
            // createVOAuditKeysList(specificEntity.getName(),nameVO);
         }
         if (specificEntity.getSsnNumber() != null
                 && specificEntity.getSsnNumber().length() > 0)
         {
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

             cmEntityDetailVO.setSsnNumber(new String(SsnNumberUIArr));
            // cmEntityDetailVO.setSsnNumber(specificEntity.getSsnNumber());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("In convertor ---cmEntityDetailVO--ssn-->"
                     + cmEntityDetailVO.getSsnNumber());
             }

         }
         /*
          * Change done for ES2 DO changes
          */
         if (specificEntity.getLob() != null)
         {
         	if (specificEntity.getLob().getLobCode() != null
                 && specificEntity.getLob().getLobCode().length() > 0)
         		{
         		cmEntityDetailVO.setLineOfBusiness(specificEntity.getLob().getLobCode());
         		if(logger.isDebugEnabled())
         		{
         			logger.debug("In convertor ---cmEntityDetailVO--lob->"
         					+ cmEntityDetailVO.getLineOfBusiness());
         		}
         		}
         }
         if (specificEntity.getProviderTypeCode() != null
                 && specificEntity.getProviderTypeCode().length() > 0)
         {
             cmEntityDetailVO.setProviderType(specificEntity
                     .getProviderTypeCode());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("In convertor ---cmEntityDetailVO--provTY->"
                     + cmEntityDetailVO.getProviderType());
             }

         }
         if (specificEntity.getNpiNumber() != null
                 && specificEntity.getNpiNumber().length() > 0)
         {
             cmEntityDetailVO.setNationalProviderID(specificEntity
                     .getNpiNumber());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("In convertor ---cmEntityDetailVO--NIP->"
                     + cmEntityDetailVO.getNationalProviderID());
             }

         }
         if (specificEntity.getOrginalTypeCode() != null
                 && specificEntity.getOrginalTypeCode().length() > 0)
         {
             cmEntityDetailVO.setOrganizationType(specificEntity
                     .getOrginalTypeCode());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("In convertor ---cmEntityDetailVO--org ty->"
                     + cmEntityDetailVO.getOrganizationType());
             }

         }

         if (specificEntity.getOrganizationName() != null
                 && specificEntity.getOrganizationName().length() > 0)
         {
             cmEntityDetailVO.setOrganizationName(specificEntity
                     .getOrganizationName());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("In convertor ---cmEntityDetailVO--org nM->"
                     + cmEntityDetailVO.getOrganizationName());
             }

         }
         if (specificEntity.getDistrictOfficeCode() != null
                 && specificEntity.getDistrictOfficeCode().length() > 0)
         {
             cmEntityDetailVO.setDistrictOfficeCode(specificEntity
                     .getDistrictOfficeCode());
             if(logger.isDebugEnabled())
             {
            	 logger.debug("In convertor ---cmEntityDetailVO--do->"
            			 + cmEntityDetailVO.getDistrictOfficeCode());
             }

         }
         //ES2Changes
         if (specificEntity.getCountyCode() != null
                 && specificEntity.getCountyCode().length() > 0)
         {
             cmEntityDetailVO.setCountyCode(specificEntity.getCountyCode());
             cmEntityDetailVO
                     .setCountyName(specificEntity.getOrganizationName());
         }
         // Doubt EIN
         if (specificEntity.getTaxID() != null
                 && specificEntity.getTaxID().length() > 0)
         {
             cmEntityDetailVO.setEmployeeIdentificationNumber(specificEntity
                     .getTaxID());

         }        
         if (specificEntity.getCommonEntity() != null)
         {

             CommonEntityVO commonEntityVO = null;
             commonEntityVO = contactHelper
                     .convertCommonEntityDomainToVO(specificEntity
                             .getCommonEntity(), controlRequired);

             /**Sets the version no  */

             commonEntityVO.setVersionNo(specificEntity.getCommonEntity()
                     .getVersionNo());           
             CommonEntityDataBean commonEntityDataBean = ContactHelper
                     .getCommonEntityDataBean();

             /** Set the Common entity vo to common netity data bean */             
            /* commonEntityDataBean.getCommonEntityVO().setCommonEntitySK(
                     commonEntityVO.getCommonEntitySK());*/
             
             /* Changes made to fix defect ESPRD00438005*/
             
             commonEntityDataBean.setCommonEntityVO(commonEntityVO);

             if (commonEntityVO != null)
             {
                 setCommonEntityVO(commonEntityVO , commonEntityDataBean);

                 /* END OF COMMON ENITY INTGRATION */
             }

             commonEntityDataBean.setAddressRendered(false);
             commonEntityDataBean.setAddressHistoryRendered(false);
             commonEntityDataBean.setEaddressRendered(false);
             commonEntityDataBean.setPhoneRendered(false);
             commonEntityDataBean.setNewContactRender(false);

         }
         
         
         createVOAuditKeysList(specificEntity,cmEntityDetailVO);
         createVOAuditKeysList(specificEntity.getLob(),cmEntityDetailVO);
         

         
         
         
         return cmEntityDetailVO;
     }
     
     
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

     /**
      * This method is used to  set the Commonentity vo o be displayed in UI.
      * @param commonEntityVO
      * Takes commonEntityVO as param
      * @param commonEntityDataBean
      * Takes commonEntityDataBean as param
      */
     public void setCommonEntityVO(CommonEntityVO commonEntityVO , CommonEntityDataBean commonEntityDataBean)
     {     	  	
     	if (commonEntityVO.getEaddressVOList() != null
                 && commonEntityVO.getEaddressVOList().size() > 0)
         {
             commonEntityDataBean.getCommonEntityVO().setEaddressVOList(
                     commonEntityVO.getEaddressVOList());
         }
         if (commonEntityVO.getPhoneVOList() != null
                 && commonEntityVO.getPhoneVOList().size() > 0)
         {
         	PhoneVO phoneVO = commonEntityDataBean.getPhoneVO();
         	List phone_List = commonEntityVO.getPhoneVOList();
         	if(phone_List!=null && !(phone_List.isEmpty()))
         	{
         		boolean sameRecFlag = true;
         		Iterator it_phoneVO = phone_List.iterator();

         		  while(it_phoneVO.hasNext()&& phoneVO!=null)
         		  {
         		  	PhoneVO phoneVO_list = (PhoneVO)it_phoneVO.next();

         		  	   if(

         		  	   			phoneVO_list.getUsageTypeCode().equalsIgnoreCase(phoneVO.getUsageTypeCode())&&
 								phoneVO_list.getBeginDateStr().equalsIgnoreCase(phoneVO.getBeginDateStr())&&
 								phoneVO_list.getEndDateStr().equalsIgnoreCase(phoneVO.getEndDateStr())&&
 								phoneVO_list.getPhoneNumber().equalsIgnoreCase(phoneVO.getPhoneNumber()))
         		  	   {

         		  	   	if(phoneVO_list.getStatus()!=null && !(phoneVO_list.getStatus().equalsIgnoreCase(""))&&
         		  	   		phoneVO.getStatus()!=null && !(phoneVO.getStatus().equalsIgnoreCase("")))
         		  	   	{

         		  	   		if(!(phoneVO_list.getStatus().equalsIgnoreCase(phoneVO.getStatus())))
         		  	   		{
         		  	   		sameRecFlag = false;
         		  	   		}
         		  	   	}
         		  	  if(sameRecFlag && phoneVO_list.getSignificance()!=null && !(phoneVO_list.getSignificance().equalsIgnoreCase(""))&&
         		  	   		phoneVO.getSignificance()!=null && !(phoneVO.getSignificance().equalsIgnoreCase("")))
         		  	   	{



         		  	   		if(!(phoneVO_list.getSignificance().equalsIgnoreCase(phoneVO.getSignificance())))
         		  	   		{
         		  	   		sameRecFlag = false;
         		  	   		}
         		  	   	}
         		  	if(sameRecFlag && phoneVO_list.getExtension()!=null && !(phoneVO_list.getExtension().equalsIgnoreCase(""))&&
         		  	   		phoneVO.getExtension()!=null && !(phoneVO.getExtension().equalsIgnoreCase("")))
         		  	   	{

         		  	   		if(!(phoneVO_list.getExtension().equalsIgnoreCase(phoneVO.getExtension())))
         		  	   		{
         		  	   		sameRecFlag = false;
         		  	   		}
         		  	   	}
         		  	if(sameRecFlag )
         		  	{

         		  		if(phoneVO_list.getOutOfService()!=phoneVO.getOutOfService())
         		  		{
         		  			sameRecFlag = false;
         		  		}
         		  	}
         		  	 if(sameRecFlag && phoneVO_list.getCountryCode()!=null && !(phoneVO_list.getCountryCode().equalsIgnoreCase(""))&&
         		  	   		phoneVO.getCountryCode()!=null && !(phoneVO.getCountryCode().equalsIgnoreCase("")))
         		  	   	{

         		  	   		if(!(phoneVO_list.getCountryCode().equalsIgnoreCase(phoneVO.getCountryCode())))
         		  	   		{
         		  	   		sameRecFlag = false;
         		  	   		}
         		  	   	}
         		  	if(sameRecFlag && phoneVO_list.getInternationalPhoneNo()!=null && !(phoneVO_list.getInternationalPhoneNo().equalsIgnoreCase(""))&&
         		  	   		phoneVO.getInternationalPhoneNo()!=null && !(phoneVO.getInternationalPhoneNo().equalsIgnoreCase("")))
         		  	   	{

         		  	   		if(!(phoneVO_list.getInternationalPhoneNo().equalsIgnoreCase(phoneVO.getInternationalPhoneNo())))
         		  	   		{
         		  	   		sameRecFlag = false;
         		  	   		}
         		  	   	}
         		  	if(sameRecFlag)
              		  {
         		  		commonEntityDataBean.setUpdateEntityFlag(true);
           		  	 //	phoneVO_list.setStatus("D");

           		  	 	//commented for unused variables
         		  		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
           		  	 	//commented for unused variables
         		  		//String dateStr = sdf.format(new Date());
           		  	 	//phoneVO_list.setEndDateStr(dateStr);
           		  	  //  phoneVO_list.setEndDate(dateStr);


              		  }
         		  	   }


         		  	sameRecFlag = true;
         		  }


         	}


             commonEntityDataBean.getCommonEntityVO().setPhoneVOList(
                     commonEntityVO.getPhoneVOList());
         }

         if (commonEntityVO.getContactVOList() != null)
         {

             commonEntityDataBean.getCommonEntityVO().setContactVOList(
                     commonEntityVO.getContactVOList());
         }

         if (commonEntityVO.getAddressVOList() != null)
         {

             commonEntityDataBean.getCommonEntityVO().setAddressVOList(
                     commonEntityVO.getAddressVOList());
         }

         if (commonEntityVO.getAddressHistoryVOList() != null)
         {

             commonEntityDataBean.getCommonEntityVO().setAddressHistoryVOList(
                     commonEntityVO.getAddressHistoryVOList());
         }

     }

     /**
      * This method will get userid from Security.
      *
      * @return String
      */
     public String getUserID()
     {
         String userId = ContactManagementConstants.TEST_USERID;

         CMEntityMaintainControllerBean cmEntityMaintainControllerBean = new CMEntityMaintainControllerBean();

         /* commented for unused variables
          * HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                 .getCurrentInstance().getExternalContext().getRequest();*/
         //commented for unused variables
         //HttpServletResponse renderResponse = null;
         userId = cmEntityMaintainControllerBean.getUserID();

        /* EnterpriseUserProfile eup = cmEntityMaintainControllerBean.getUserData(
                 renderRequest, renderResponse);

         if (eup != null && !isNullOrEmptyField(eup.getUserId()))
         {
             userId = eup.getUserId();
         }*/
         
         getCmEntitySearchDataBean().setUserId(userId);
         return userId;
     }

     /**
      * This method is used to get the UserSK given the userId.
      *
      * @param userId :
      *            String User Id.
      * @return Long : UserSK.
      */
     private Long getUserSK(String userId)
     {
        

         ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

         Long userSK = null;

         try
         {
             userSK = contactMaintenanceDelegate.getWorkUnitID(userId);
         }
         catch (LOBHierarchyFetchBusinessException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
        		 logger.error(e.getMessage(), e);
        	 }
         }

         return userSK;
     }

     /**
      * This method is used to get the Current Timestamp.
      *
      * @return Timestamp : Current Timestamp.
      */
     private Timestamp getTimeStamp()
     {
         

         Calendar cal = Calendar.getInstance();

         return new Timestamp(cal.getTimeInMillis());
     }

     /**
      * This method is used to check if the input field is null or is equal to an
      * empty string.
      *
      * @param inputField :
      *            String inputField
      * @return boolean : true if input field is null or equal to an empty string
      *         else false
      */
     private boolean isNullOrEmptyField(String inputField)
     {
         

         return (inputField == null || ContactManagementConstants.EMPTY_STRING
                 .equalsIgnoreCase(inputField.trim()));
     }

     /**
      * This operation is used to get the reference data for all Entity Type.
      *
      * @return List
      */
     public List getEntityTypeReferenceData()
     {
    	 long entryTime = System.currentTimeMillis();
    	 // Modified for CRM_CASE-GAI-Recursive Call-FIX
         String userId=getCmEntitySearchDataBean().getUserId();
         if (userId == null
				|| userId.trim()
						.equals(ContactManagementConstants.EMPTY_STRING)) {
			userId = getUserID();
		 }
         Long userSK =getCmEntitySearchDataBean().getUserSk();
         if(userSK==null || userSK==0){
        	 userSK=getUserSK(userId);
        	 getCmEntitySearchDataBean().setUserSk(userSK);
         }
         String businessUnitDesc =getCmEntitySearchDataBean().getBusinessUnitDesc();
         if (businessUnitDesc == null
				|| businessUnitDesc.trim().equals(
						ContactManagementConstants.EMPTY_STRING)) {
			businessUnitDesc = getBusinessUnitforUser(userSK);
			getCmEntitySearchDataBean().setBusinessUnitDesc(businessUnitDesc);	
		}
         List referenceList = new ArrayList();
         Map referenceMap = null;

         int referenceListSize = 0;
         List entityList = new ArrayList();

         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO=null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();
         inputCriteriaEntityTyp
                 .setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
         inputCriteriaEntityTyp.setListNumber(
                 ContactManagementHelper.getSystemlistForCorrBU(
                         ContactManagementConstants.ENTITYTYPE,businessUnitDesc));

         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
         	
         if(businessUnitDesc != null && !businessUnitDesc.equalsIgnoreCase(""))
         {
         	referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);
            
             referenceMap = referenceDataListVO.getResponseMap();
//           FindBug issue Resolved
             if(referenceMap!=null){
             referenceList = (List) referenceMap
                     .get(FunctionalAreaConstants.CONTACT_MGMT
                             + ProgramConstants.HASH_SEPARATOR
                             + String
                             .valueOf(ContactManagementHelper
                                     .getSystemlistForCorrBU(ContactManagementConstants.ENTITYTYPE,
                                             businessUnitDesc)));
             referenceListSize = referenceList.size();
             
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                 
                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 entityList.add(new SelectItem(refVo.getValidValueCode(),
                         codesAndDesc));

             }
         }
         }
         }
         catch (ReferenceServiceRequestException e)
         {
         	
         	e.printStackTrace();
             
         }
         catch (SystemListNotFoundException e)
         {
         	
         	e.printStackTrace();
            
         }
         long exitTime = System.currentTimeMillis();
         if(logger.isInfoEnabled())
         {
        	 logger.info("CMEntityDataBean" + "#" + "getReferenceData" + "#"
        			 + (exitTime - entryTime));
         }
         return entityList;
     }

     /**
      * This operation is used to get the reference data for Specific Entity
      * Type.
      *
      * @return List
      */
     public List getSpecEntityTypeReferenceData()
     {
         long entryTime = System.currentTimeMillis();
         // Modified for CRM_CASE-GAI-Recursive Call-FIX
         String userId=getCmEntitySearchDataBean().getUserId();
         if (userId == null
				|| userId.trim()
						.equals(ContactManagementConstants.EMPTY_STRING)) {
			userId = getUserID();
		}
         Long userSK =getCmEntitySearchDataBean().getUserSk();
         if(userSK==null || userSK==0){
        	 userSK=getUserSK(userId);
        	 getCmEntitySearchDataBean().setUserSk(userSK);
         }
         String businessUnitDesc =getCmEntitySearchDataBean().getBusinessUnitDesc();
         if (businessUnitDesc == null
				|| businessUnitDesc.trim().equals(
						ContactManagementConstants.EMPTY_STRING)) {
			businessUnitDesc = getBusinessUnitforUser(userSK);
			getCmEntitySearchDataBean().setBusinessUnitDesc(businessUnitDesc);
	     }
         List referenceList = new ArrayList();
         Map referenceMap = null;

         int referenceListSize = 0;
         List entityList = new ArrayList();

         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO = null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();
         inputCriteriaEntityTyp
                 .setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
         inputCriteriaEntityTyp.setListNumber(
                 ContactManagementHelper.getSystemlistForCorrBU(
                         ContactManagementConstants.ADDENTITYTYPE,businessUnitDesc));
         /*inputCriteriaEntityTyp
                 .setElementName(ReferenceServiceDataConstants.G_CM_SPEC_ENTY_TY_CD);*/
         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
             referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);

             referenceMap = referenceDataListVO.getResponseMap();
             if(referenceMap!=null){

             /* referenceList = (List) referenceMap
                     .get(FunctionalAreaConstants.CONTACT_MGMT
                             + ProgramConstants.HASH_SEPARATOR
                             + ReferenceServiceDataConstants.G_CM_SPEC_ENTY_TY_CD);*/
             referenceList = (List) referenceMap
             .get(FunctionalAreaConstants.CONTACT_MGMT
                     + ProgramConstants.HASH_SEPARATOR
                     + String
                     .valueOf(ContactManagementHelper
                             .getSystemlistForCorrBU(ContactManagementConstants.ADDENTITYTYPE,
                                     businessUnitDesc)));
             referenceListSize = referenceList.size();
             
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                 
                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 entityList.add(new SelectItem(refVo.getValidValueCode(),
                         codesAndDesc));

             }
             }
         }
         catch (ReferenceServiceRequestException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }
         catch (SystemListNotFoundException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }
         long exitTime = System.currentTimeMillis();
         if(logger.isInfoEnabled())
         {
        	 logger.info("CMEntityDataBean" + "#" + "getReferenceData" + "#"
        			 + (exitTime - entryTime));
         }
         return entityList;
     }

     /**
      * This operation is used to get the reference data for Entity Type Member .
      *
      * @return List
      */
     public List getMemEntIDTypeReferenceData()
     {
         
         List referenceList = new ArrayList();
         Map referenceMap = null;

         int referenceListSize = 0;
         List memEntityIDList = new ArrayList();

         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO = null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();
         inputCriteriaEntityTyp
                 .setFunctionalArea(FunctionalAreaConstants.MEMBER);
         inputCriteriaEntityTyp
                 .setElementName(ReferenceServiceDataConstants.B_ALT_ID_TY_CD);

         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
             referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);

             referenceMap = referenceDataListVO.getResponseMap();
             if(referenceMap!=null){
             referenceList = (List) referenceMap
                     .get(FunctionalAreaConstants.MEMBER
                             + ProgramConstants.HASH_SEPARATOR
                             + ReferenceServiceDataConstants.B_ALT_ID_TY_CD);
             referenceListSize = referenceList.size();
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                

                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 memEntityIDList.add(new SelectItem(refVo.getValidValueCode(),
                         codesAndDesc));

             }
             }

         }
         catch (ReferenceServiceRequestException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
        		 logger.error(e.getMessage(), e);
        	 }
         }
         catch (SystemListNotFoundException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
        		 logger.error(e.getMessage(), e);
        	 }
         }

         return memEntityIDList;

     }

     /**
      * This operation is used to get the reference data for Entity Type Provider .
      *
      * @return List
      */
     public List getEntIDTypeReferenceData(String funcAreaConstant,Long listNumber)
     {
         
         List referenceList = new ArrayList();
         Map referenceMap = null;

         int referenceListSize = 0;
         List provEntityIDList = new ArrayList();

         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO =null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();
         inputCriteriaEntityTyp
                 .setFunctionalArea(funcAreaConstant);
         inputCriteriaEntityTyp
                 .setListNumber(listNumber);

         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
             referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);

             referenceMap = referenceDataListVO.getResponseMap();
             if(referenceMap!=null){
             referenceList = (List) referenceMap
                     .get(funcAreaConstant
                             + ProgramConstants.HASH_SEPARATOR
                             + listNumber);
             referenceListSize = referenceList.size();
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                 

                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 provEntityIDList.add(new SelectItem(refVo.getValidValueCode(),
                         codesAndDesc));

             }
             }
         }
         catch (ReferenceServiceRequestException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }
         catch (SystemListNotFoundException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }
         return provEntityIDList;
     }

     /**
      * This operation is used to get the reference data for Provider Type Drop
      * Down .
      *
      * @return List
      */
     public List getProvTypeReferenceData()
     {
         
         List referenceList = new ArrayList();
         Map referenceMap = null;

         int referenceListSize = 0;
         List provTypeList = new ArrayList();

         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO = null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();
         inputCriteriaEntityTyp
                 .setFunctionalArea(FunctionalAreaConstants.PROVIDER);
         inputCriteriaEntityTyp
                 .setElementName(ReferenceServiceDataConstants.PROV_TY_CD);

         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
             referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);

             referenceMap = referenceDataListVO.getResponseMap();
             if(referenceMap!=null){
             referenceList = (List) referenceMap
                     .get(FunctionalAreaConstants.PROVIDER
                             + ProgramConstants.HASH_SEPARATOR
                             + ReferenceServiceDataConstants.PROV_TY_CD);
             referenceListSize = referenceList.size();
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                 

                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 provTypeList.add(new SelectItem(refVo.getValidValueCode(),
                         codesAndDesc));

             }
             }

         }
         catch (ReferenceServiceRequestException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
        		logger.error(e.getMessage(), e);
        	 }
         }
         catch (SystemListNotFoundException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }

         return provTypeList;
     }

     /**
      * This operation is used to get the reference data for LOB Drop Down .
      *
      * @return List
      */
     public List getLobReferenceData()
     {
         

         List referenceList = new ArrayList();
         Map referenceMap = null;

         int referenceListSize = 0;

         List lobList = new ArrayList();
         
         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO = null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();
         inputCriteriaEntityTyp
                 .setFunctionalArea(FunctionalAreaConstants.GENERAL);
         inputCriteriaEntityTyp
                 .setElementName(ReferenceServiceDataConstants.R_LOB_CD);

         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
             referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);

             referenceMap = referenceDataListVO.getResponseMap();
             if(referenceMap!=null){
             referenceList = (List) referenceMap
                     .get(FunctionalAreaConstants.GENERAL
                             + ProgramConstants.HASH_SEPARATOR
                             + ReferenceServiceDataConstants.R_LOB_CD);
             referenceListSize = referenceList.size();
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                 

                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 lobList.add(new SelectItem(refVo.getValidValueCode(),
                         codesAndDesc));

             }
             }

         }
         catch (ReferenceServiceRequestException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }
         catch (SystemListNotFoundException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }

         return lobList;
     }

     /**
      * This operation is used to get the reference data for Prefix Drop Down .
      *
      * @return List
      */
     public List getPrefixReferenceData()
     {
         

         List referenceList = new ArrayList();
         
         Map referenceMap = null;

         int referenceListSize = 0;
         List prefixList = new ArrayList();
         
         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO = null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();
         inputCriteriaEntityTyp
                 .setFunctionalArea(FunctionalAreaConstants.COMMON_ENTITY);
         inputCriteriaEntityTyp
                 .setElementName(ReferenceServiceDataConstants.G_NAM_PREFX_CD);

         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
             referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);

             referenceMap = referenceDataListVO.getResponseMap();
             if(referenceMap!=null){
             referenceList = (List) referenceMap
                     .get(FunctionalAreaConstants.COMMON_ENTITY
                             + ProgramConstants.HASH_SEPARATOR
                             + ReferenceServiceDataConstants.G_NAM_PREFX_CD);
             referenceListSize = referenceList.size();
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                 

                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 prefixList.add(new SelectItem(refVo.getValidValueCode(),
                         codesAndDesc));
                

             }
             }

         }
         catch (ReferenceServiceRequestException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }
         catch (SystemListNotFoundException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }

         return prefixList;
     }

     /**
      * This operation is used to get the reference data for Organization type
      * Drop Down .
      *
      * @return List
      */
     public List getOrgTypeReferenceData()
     {
         
         List referenceList = new ArrayList();
         
         Map referenceMap = null;

         int referenceListSize = 0;
         List orgTypeList = new ArrayList();

         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO = null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();
         inputCriteriaEntityTyp
                 .setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
         inputCriteriaEntityTyp
                 .setElementName(ReferenceServiceDataConstants.G_ORG_TY_CD);

         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
             referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);

             referenceMap = referenceDataListVO.getResponseMap();
             if(referenceMap!=null){
             referenceList = (List) referenceMap
                     .get(FunctionalAreaConstants.CONTACT_MGMT
                             + ProgramConstants.HASH_SEPARATOR
                             + ReferenceServiceDataConstants.G_ORG_TY_CD);
             referenceListSize = referenceList.size();
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                

                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 orgTypeList.add(new SelectItem(refVo.getValidValueCode(),
                         codesAndDesc));
                 

             }
             }

         }
         catch (ReferenceServiceRequestException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
        		 logger.error(e.getMessage(), e);
        	 }
         }
         catch (SystemListNotFoundException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
        		 logger.error(e.getMessage(), e);
        	 }
         }

         return orgTypeList;
     }

     /**
      * This operation is used to get the reference data for District Office Drop
      * Down .
      *
      * @return List
      */
     public List getDistrictOfficeReferenceData()
     {
         
         List referenceList = new ArrayList();
         
         Map referenceMap = null;

         int referenceListSize = 0;
         List distOfficeCodeList = new ArrayList();

         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO=null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();
         inputCriteriaEntityTyp
                 .setFunctionalArea(FunctionalAreaConstants.MEMBER);
         inputCriteriaEntityTyp
                 .setElementName(ReferenceServiceDataConstants.B_CASE_DSTCT_OFC_CD);

         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
             referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);

             referenceMap = referenceDataListVO.getResponseMap();
             if(referenceMap!=null){
             referenceList = (List) referenceMap
                     .get(FunctionalAreaConstants.MEMBER
                             + ProgramConstants.HASH_SEPARATOR
                             + ReferenceServiceDataConstants.B_CASE_DSTCT_OFC_CD);
             referenceListSize = referenceList.size();
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                 

                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 distOfficeCodeList.add(new SelectItem(
                         refVo.getValidValueCode(), codesAndDesc));
                 

             }
             }

         }
         catch (ReferenceServiceRequestException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }
         catch (SystemListNotFoundException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }

         return distOfficeCodeList;
     }

     /**
      * This method gets code from he valid value.
      *
      * @param entityTypeVV
      *            TTakes the Entity Type as input.
      * @return String
      */
     /*
      * private String getCodeFromEntityTypeVV(String entityTypeVV) { String code =
      * ContactManagementConstants.EMPTY_STRING; return code; }
      */
     /**
      * This Method takes the Search Results and concatinates the name.
      *
      * @param entitySearchResultsList
      *            Takes entitySearchResultsList as param.
      * @param entitySearchCriteriaVO
      *            Takes EntitySearchCriteriaVO as param.
      * @return ArrayList
      */
     public List getEntities(List entitySearchResultsList,
             EntitySearchCriteriaVO entitySearchCriteriaVO)
     {
         List finalSearchResults = new ArrayList();

         Iterator itr = entitySearchResultsList.iterator();
         //   String lobType =
         // ContactManagementConstants.DFLT_LOB_CODE_FOR_SEARCH_ENTITY;

         while (itr.hasNext())
         {
             EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO) itr
                     .next();

             String entType = entitySearchCriteriaVO.getEntityType();
             
             
             /** Member */
             if (StringUtils.equalsIgnoreCase(
                     ContactManagementConstants.ENTITY_TYPE_MEM, entType))
             {
                
                 String enityName = setMemberName(entitySearchResultsVO);
                 /**
                  * Set the entity Id according to the Search Criteria - by name ,
                  * by ID or both
                  */
                 String entityID = setEntityID(entitySearchResultsVO,
                         entitySearchCriteriaVO);
                 entitySearchResultsVO.setEntityName(enityName);
                 entitySearchResultsVO.setEntityID(entityID);
             }
             /** Unenrolled Provider */
             else if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV, entType))
             {
                 if(entitySearchResultsVO.getSystemID() == null)
                 {
	                 String enityName = setUnenolledProvName(entitySearchResultsVO);
	                 String orgName = setUnenrolledProvOrgName(entitySearchResultsVO);
	                 if(orgName != null && !ContactManagementConstants.EMPTY_STRING.equalsIgnoreCase(orgName))
	                 {
	                 	entitySearchResultsVO.setEntityName(orgName);
	                 }else
	 				 {
	                 	entitySearchResultsVO.setEntityName(enityName);
	 				 }
	                 entitySearchResultsVO.setOrganisationName(orgName);
	                
	                 entitySearchCriteriaVO.setEntityIDType(entitySearchResultsVO.getEntityIDTyp());
	                 
	              //   entitySearchResultsVO.setLineOfBusiness(entitySearchResultsVO.getLineOfBusiness());
                 }
			 }

             /** District Office */
             else if (StringUtils.equalsIgnoreCase(
                     ContactManagementConstants.ENTITY_TYPE_DO, entType))
             {

                
                 String enityName = setFromEntityName(entitySearchResultsVO);
                 entitySearchResultsVO.setEntityName(enityName);
             }

             /** County */
             else if (StringUtils.equalsIgnoreCase(
                     ContactManagementConstants.ENTITY_TYPE_CT, entType))
             {
             	
                 String orgName = setUnenrolledProvOrgName(entitySearchResultsVO);
                 entitySearchResultsVO.setOrganisationName(orgName);
             }
          // Below else if condition is added for CR : ESPRD00808886
             else if (StringUtils.equalsIgnoreCase(
            		 ContactManagementConstants.ENTITY_TYPE_SC, entType))
             {
            	 String enityName = setFromEntityName(entitySearchResultsVO);
            	 entitySearchResultsVO.setEntityName(enityName);
             }

             /** TPL Carrier */
             else if (StringUtils.equalsIgnoreCase(
                     ContactManagementConstants.ENTITY_TYPE_TPL, entType))
             {

                
                 String enityName = setFromEntityName(entitySearchResultsVO);
                 entitySearchResultsVO.setEntityName(enityName);

                 /** Set the LOB */
                 /*
                  * if
                  * (isNullOrEmptyField(entitySearchResultsVO.getLineOfBusiness())) {
                  * entitySearchResultsVO
                  * .setLineOfBusiness(getValidValueDescFromCode(lobType,
                  * getLobReferenceData())); } else { lobType =
                  * entitySearchResultsVO.getLineOfBusiness();
                  * entitySearchResultsVO
                  * .setLineOfBusiness(getValidValueDescFromCode(lobType,
                  * getLobReferenceData())); }
                  */
                 
             }
             /** Provider */
             else if (StringUtils.equalsIgnoreCase(
                     ContactManagementConstants.ENTITY_TYPE_PROV, entType))
             {

                
                 String enityName = setFromEntityName(entitySearchResultsVO);

                 /**
                  * Set the entity Id according to the Search Criteria - by name ,
                  * by ID or both
                  */
                 String entityID = setEntityID(entitySearchResultsVO,
                         entitySearchCriteriaVO);
                 entitySearchResultsVO.setEntityName(enityName);
                 entitySearchResultsVO.setEntityID(entityID);
                 entitySearchCriteriaVO.setEntityIDType(entitySearchResultsVO.getEntityIDTyp());
                 
                
                 
                
                 /** Set the LOB */
                 /*
                  * if
                  * (isNullOrEmptyField(entitySearchResultsVO.getLineOfBusiness())) {
                  * entitySearchResultsVO
                  * .setLineOfBusiness(getValidValueDescFromCode(lobType,
                  * getLobReferenceData())); } else { lobType =
                  * entitySearchResultsVO.getLineOfBusiness();
                  * entitySearchResultsVO
                  * .setLineOfBusiness(getValidValueDescFromCode(lobType,
                  * getLobReferenceData())); }
                  */

             } 
             //added for CR ESPRD00486064 starts
             else if (StringUtils.equalsIgnoreCase(
             		ContactManagementConstants.ENTITY_TYPE_TP, entType))
             {
             	// No need any changes...
             }
             // added for CR ESPRD00486064 ends
             //ADDED FOR THE Correspondence ESPRD00436016
             else if(StringUtils.equalsIgnoreCase(
              		ContactManagementConstants.ENTITY_TYPE_TD, entType)){   
            	 
             	 String enityName = setFromEntityName(entitySearchResultsVO);
             	 
             	 String entityID = setEntityID(entitySearchResultsVO,
                          entitySearchCriteriaVO);
             	
             	 entitySearchResultsVO.setEntityName(enityName);  
             	 entitySearchCriteriaVO.setEntityIDType(entitySearchResultsVO.getEntityIDTyp());
             	// entitySearchResultsVO.setEntityID(entityID);  
              }
             else
             {
                 
                 String enityName = setSpecificEntName(entitySearchResultsVO);
                 entitySearchResultsVO.setEntityName(enityName);
             }

             String address=setAddress(entitySearchResultsVO);
             entitySearchResultsVO.setAddress(address);
             String city=setCity(entitySearchResultsVO);
             entitySearchResultsVO.setCity(city);
             
             

             /** Other */

             finalSearchResults.add(entitySearchResultsVO);
         }

         return finalSearchResults;

     }

     /**
      * This method is used to set the Name Column for Member Search results.
      *
      * @param entitySearchResultsVO
      *            Takes EntitySearchResultsVO as param.
      * @return EntitySearchResultsVO
      */
     private String setMemberName(EntitySearchResultsVO entitySearchResultsVO)
     {

         String entityName = ContactManagementConstants.EMPTY_STRING;
         if (!isNullOrEmptyField(entitySearchResultsVO.getFirstName()))
         {
             entityName = entitySearchResultsVO.getFirstName();
             entityName = entityName + ContactManagementConstants.EMPTY_STRING;


         }
         if (!isNullOrEmptyField(entitySearchResultsVO.getLastName()))
         {
             entityName = entityName + ContactManagementConstants.SPACE_STRING + entitySearchResultsVO.getLastName();
             if(logger.isDebugEnabled())
             {
             logger.debug("inside cmentity do convertor ---mem name " + entityName);
             }
         }
         return entityName;
     }

     /**
      * This method is used to set the Name Column for Unenrolled Provider Search
      * results.
      *
      * @param entitySearchResultsVO
      *            Takes EntitySearchResultsVO as param.
      * @return EntitySearchResultsVO
      */
     private String setUnenolledProvName(
             EntitySearchResultsVO entitySearchResultsVO)
     {

         String entityName = ContactManagementConstants.EMPTY_STRING;
         /** If org name not null return org name */

             if (!isNullOrEmptyField(entitySearchResultsVO.getFirstName()))
             {
                 entityName = entitySearchResultsVO.getFirstName();

             }

             if (!isNullOrEmptyField(entitySearchResultsVO.getMiddleName()))
             {
                 entityName = entityName + ContactManagementConstants.SPACE_STRING
 					+ entitySearchResultsVO.getMiddleName();
             }

             if (!isNullOrEmptyField(entitySearchResultsVO.getLastName()))
             {

                 entityName = entityName + ContactManagementConstants.SPACE_STRING + entitySearchResultsVO.getLastName();
                 if(logger.isDebugEnabled())
                 {
                 logger.debug("inside cmentity do convertor ---unprov name " + entityName);
                 }

             }



         return entityName;
     }

     /**
      * This method is used to set the OrgName Column for Unenrolled Provider Search
      * results.
      *
      * @param entitySearchResultsVO
      *            Takes EntitySearchResultsVO as param.
      *
      * @return String
      */
     private String setUnenrolledProvOrgName(EntitySearchResultsVO entitySearchResultsVO)
     {
     	String orgName = ContactManagementConstants.EMPTY_STRING;
     	if (!isNullOrEmptyField(entitySearchResultsVO.getOrganisationName()))
         {
     		orgName = entitySearchResultsVO.getOrganisationName();

         }
     	return orgName;

     }

     /**
      * This method is used to set the Name Column for Specific Entity Search
      * results.
      *
      * @param entitySearchResultsVO
      *            Takes EntitySearchResultsVO as param.
      * @return EntitySearchResultsVO
      */
     public String setSpecificEntName(
             EntitySearchResultsVO entitySearchResultsVO)
     {

         String entityName = ContactManagementConstants.EMPTY_STRING;
         if (!isNullOrEmptyField(entitySearchResultsVO.getFirstName()))
         {
             entityName = entitySearchResultsVO.getFirstName();

         }

         if (!isNullOrEmptyField(entitySearchResultsVO.getMiddleName()))
         {
             entityName = entityName + ContactManagementConstants.SPACE_STRING
                     + entitySearchResultsVO.getMiddleName();
         }

         if (!isNullOrEmptyField(entitySearchResultsVO.getLastName()))
         {
             entityName = entityName + ContactManagementConstants.SPACE_STRING
                     + entitySearchResultsVO.getLastName();
             if(logger.isDebugEnabled())
             {
             logger.debug("inside cmentity do convertor ---spec ent  name "
                     + entityName);
             }
         }

         return entityName;
     }

     /**
      * This method is used to set the Name Column for District office Search
      * results.
      *
      * @param entitySearchResultsVO
      *            Takes EntitySearchResultsVO as param.
      * @return String
      */
     private String setFromEntityName(EntitySearchResultsVO entitySearchResultsVO)
     {
         String entityName = ContactManagementConstants.EMPTY_STRING;

         if (!isNullOrEmptyField(entitySearchResultsVO.getEntityName()))
         {
             entityName = entitySearchResultsVO.getEntityName();
         }

         return entityName;
     }

    /* *//**
      * This method takes the ValidValue code for Entity Type and return the
      * respective Desc.
      *
      * @param vvCode
      *            Takes the valid value code for Entity Type
      * @param vvList
      *            List of valid values of Entity Type
      * @return String
      *//*
     private String getValidValueDescFromCode(String vvCode, List vvList)
     {
         logger.info("Inside getValidValueDescFromCode");
         List validValueList = new ArrayList(
                 ContactManagementConstants.DEFAULT_SIZE);
         int vvlistSize = 0;
         String vvDes = vvCode;
         validValueList = vvList;
         if (validValueList != null)
         {
             vvlistSize = validValueList.size();
         }
         for (int i = 0; i < vvlistSize; i++)
         {
             SelectItem item = (SelectItem) validValueList.get(i);

             String value = null;
             if (item != null)
             {
                 value = (String) item.getValue();
             }

             if (StringUtils.equalsIgnoreCase(value, vvCode))
             {
                 vvDes = item.getLabel();
             }
         }
         logger.debug("detttttt" + vvDes);
         return vvDes;
     }
 */
     /**
      * This method checks if the Search is done by name or by Entity Id and
      * according sets Current Alt Id or Entity ID in the Entity Id column of
      * search Results.
      *
      * @param entitySearchResultsVO
      *            Takes EntitySearchResultsVO as param
      * @param entitySearchCriteriaVO
      *            Takes EntitySearchCriteriaVO as param
      * @return String
      */
     private String setEntityID(EntitySearchResultsVO entitySearchResultsVO,
             EntitySearchCriteriaVO entitySearchCriteriaVO)
     {
         String entID = ContactManagementConstants.EMPTY_STRING;
         /**
          * If search is by name or by both entity id and name show current alt
          * id
          */
         if (!isNullOrEmptyField(entitySearchResultsVO.getCurrAltID()))
         {
             entID = entitySearchResultsVO.getCurrAltID();
         }
         /** If search is done by name and Entity Id is empty show Current Alt ID */
         if (!isNullOrEmptyField(entitySearchCriteriaVO.getEntityID()))
         {
             entID = entitySearchResultsVO.getEntityID();
         }

         return entID;
     }
     /**
      * This operation is used to get the reference data for County Code Drop
      * Down .
      *
      * @return List
      */
     public List getCountyCodeReferenceData()
     {
         
         List referenceList = new ArrayList();
         
         Map referenceMap = null;

         int referenceListSize = 0;
 
         List countyCodeList = new ArrayList();

         ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
         ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
         //---FindBug issue Resolved---
         /*ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();*/
         ReferenceDataListVO referenceDataListVO=null;
         InputCriteria inputCriteriaEntityTyp = new InputCriteria();

         inputCriteriaEntityTyp
                 .setFunctionalArea(FunctionalAreaConstants.GENERAL);
         inputCriteriaEntityTyp
                 .setElementName(ReferenceServiceDataConstants.G_COUNTY_CD);

         referenceList.add(inputCriteriaEntityTyp);

         referenceDataSearch.setInputList(referenceList);

         try
         {
             referenceDataListVO = referenceServiceDelegate
                     .getReferenceData(referenceDataSearch);

             referenceMap = referenceDataListVO.getResponseMap();
            
             if(referenceMap!=null){
             referenceList = (List) referenceMap
                     .get(FunctionalAreaConstants.GENERAL
                             + ProgramConstants.HASH_SEPARATOR
                             + ReferenceServiceDataConstants.G_COUNTY_CD);
             referenceListSize = referenceList.size();
             for (int i = 0; i < referenceListSize; i++)
             {
                 ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                         .get(i);
                 

                 String codesAndDesc = refVo.getValidValueCode() + "-"
                         + refVo.getShortDescription();
                 countyCodeList.add(new SelectItem(
                         refVo.getValidValueCode(), codesAndDesc));
                 

             }
             }

         }
         catch (ReferenceServiceRequestException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }
         catch (SystemListNotFoundException e)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(e.getMessage(), e);
        	 }
         }
        return countyCodeList;
     }
     /**
      * This method converts cmEntityDetailVO to CmnSpecEntyXref DO
      *
      * @param cmEntityDetailVO
      *            Takes cmEntityDetailVO as param
      * * @param provCmnEntySK
      *            Takes provCmnEntySK as param
      *
      * @return CmnSpecEntyXref
      */
     public CmnSpecEntyXref convertCmnSpecEntyXrefVOToDO(CMEntityDetailVO cmEntityDetailVO, Long provCmnEntySK)
     {
     	CmnSpecEntyXref cmnSpecEntyXref = new CmnSpecEntyXref();

     	if (provCmnEntySK != null)
     	{
     		cmnSpecEntyXref.setCommonEntitySK(provCmnEntySK);
     	}
     	if (cmEntityDetailVO.getCmEntityID() != null)
     	{
     		cmnSpecEntyXref.setSpecificEntitySK(new Long(cmEntityDetailVO.getCmEntityID()));
     	}
     	// Modified for CRM_CASE-GAI-Recursive Call-FIX
     	String userId=getCmEntitySearchDataBean().getUserId();
		if(userId==null || userId.trim().equals("")){
       	 userId=getUserID();
       	 }
     	cmnSpecEntyXref.setAddedAuditTimeStamp(getTimeStamp());
     	cmnSpecEntyXref.setAddedAuditUserID(userId);
     	cmnSpecEntyXref.setAuditTimeStamp(getTimeStamp());
     	cmnSpecEntyXref.setAuditUserID(userId);
     	cmnSpecEntyXref.setVersionNo(cmEntityDetailVO.getVersionNo());

         return cmnSpecEntyXref;
     }

     public String getBusinessUnitforUser(Long userSK)
     {
         

         ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
         String businessUnitDesc= null;
         DeptUserBasicInfo deptUserBasicInfo=new DeptUserBasicInfo();
         try
         {
        	 deptUserBasicInfo.setUserEnterpriseSK(userSK);
        	 deptUserBasicInfo.setDataReqested(ProgramConstants.DUBI_LOB_Hierarchy_SK);
          
        	 //  List deptsList = contactMaintenanceDelegate.getDepartmentUsers(userSK);
        	 List deptsList= contactMaintenanceDelegate.getDeptUserBasicInfo(deptUserBasicInfo).getLobSKlist();
             
             if (deptsList != null)
             {
                 for (Iterator iter = deptsList.iterator(); iter.hasNext();)
                 {
                	 // Code Commented  for CM-API
                   /*  DepartmentUser deptUser = (DepartmentUser) iter.next();
                     String lobHierarchySK = deptUser.getDepartment()
                             .getLineOfBusinessHierarchy()
                             .getLineOfBusinessHierarchySK().toString();*/
                                 	 
                	Long lobSk= (Long)iter.next();
                	String lobHierarchySK=lobSk.toString();
                	 
                     LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
                             .getDeptBusinessUnit(new Long(lobHierarchySK));
                     if (businessUnit != null)
                     {
                         
                     	if (businessUnitDesc == null)
                         {
                             businessUnitDesc = businessUnit.getLobHierarchyDescription();
                         }
                         else if (!businessUnitDesc.equalsIgnoreCase(businessUnit.getLobHierarchyDescription()))
                         {
                             businessUnitDesc=ContactManagementConstants.AllOthers;
                             
                             break;
                         }
                     }
                 }

             }
         }
         catch (LOBHierarchyFetchBusinessException lobExp)
         {
        	 if(logger.isErrorEnabled())
        	 {
             logger.error(lobExp.getMessage(), lobExp);
        	 }
         }


         return businessUnitDesc;
      }


     /**
      * This method is used to set the Address for  Search
      * results.
      *
      * @param entitySearchResultsVO
      *            Takes EntitySearchResultsVO as param.
      *
      * @return String
      */
     private String setAddress(EntitySearchResultsVO entitySearchResultsVO) {
         String adress = ContactManagementConstants.EMPTY_STRING;
         if (!isNullOrEmptyField(entitySearchResultsVO.getAddress())) {

             adress = entitySearchResultsVO.getAddress();

         }
         return adress;

     }

     /**
      * This method is used to set the City for  Search
      * results.
      *
      * @param entitySearchResultsVO
      *            Takes EntitySearchResultsVO as param.
      *
      * @return String
      */

     private String setCity(EntitySearchResultsVO entitySearchResultsVO) {
         String city = ContactManagementConstants.EMPTY_STRING;
         if (!isNullOrEmptyField(entitySearchResultsVO.getCity())) {

             city = entitySearchResultsVO.getCity();

         }
         return city;

     }
     
     // Added for CRM_CASE-GAI-Recursive Call-FIX
     /**
 	 * Gets reference of CMEntitySearchDataBean.
 	 * 
 	 * @author Infinite.
 	 * @return CMEntitySearchDataBean
 	 */
 	public CMEntitySearchDataBean getCmEntitySearchDataBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
 		CMEntitySearchDataBean cmEntitySearchDataBean = (CMEntitySearchDataBean) fc
 				.getApplication().createValueBinding(
 						"#{" + ContactManagementConstants.CMSEARCHENTITY_BEAN_NAME + "}")
 				.getValue(fc);

 		return cmEntitySearchDataBean;
 	}
 }


