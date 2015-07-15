/*
 * Created on Mar 15, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.commonentities.view.helper;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
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
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.common.domain.Name;
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
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.Address;
import com.acs.enterprise.common.program.commonentities.common.domain.AddressUsage;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntityRepCrossReference;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntityType;
import com.acs.enterprise.common.program.commonentities.common.domain.EAddress;
import com.acs.enterprise.common.program.commonentities.common.domain.EAddressUsage;
import com.acs.enterprise.common.program.commonentities.common.domain.Note;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteCex;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.common.domain.Phone;
import com.acs.enterprise.common.program.commonentities.common.domain.PhoneUsage;
import com.acs.enterprise.common.program.commonentities.common.domain.Representative;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonNotesControllerBean;
import com.acs.enterprise.common.program.commonentities.view.bean.EAddressControllerBean;
import com.acs.enterprise.common.program.commonentities.view.bean.PhoneControllerBean;
import com.acs.enterprise.common.program.commonentities.view.vo.AddressVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonContactTypeVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonContactVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesVO;
import com.acs.enterprise.common.program.commonentities.view.vo.EAddressVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NameVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.commonentities.view.vo.PhoneVO;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.ValidatorConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;

/**
 * Helper class used to convert DO to VO and VO to DO.
 */
public class ContactHelper
{

    /**
     * To hold EnterpriseLogger.
     */

    /*private transient static EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(ContactHelper.class);*/
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(ContactHelper.class);

    /**
     * To hold commonEntityDataBean.
     */
    private CommonEntityDataBean commonEntityDataBean = getCommonEntityDataBean();

    /**
     * This method converts the domain object to value object. It takes the
     * instances of Object to *be converted as input and returns the
     * corresponding value object with values from Object *being set to the vo.
     * 
     * @param commonEntityObj
     *            The commonEntity is to get commonentity details.
     * @return CommonEntityVO
     */
    public CommonEntityVO convertCommonEntityDomainToVO(
            CommonEntity commonEntityObj,boolean controlRequired)
    {
        CommonEntityVO commonEntityVO = new CommonEntityVO();

        logger.info("convertCommonEntityDomainToVO");
if(commonEntityObj != null){
        logger.debug("commonEntityObj  commonEntityObj.getCommonEntitySK "
                + commonEntityObj.getCommonEntitySK());
}
        /*Added Version number to fix defect ESPRD00438005 */
        // Added if Condition for Find_Bugs-FIX
        if(commonEntityObj != null){
        commonEntityVO.setVersionNo(commonEntityObj.getVersionNo()) ;
        logger.debug("commonEntityObj.getVersionNo()"+commonEntityObj.getVersionNo());
        }


        try
        {
        	//Added if Condition for Find_Bugs-FIX
        	if(commonEntityObj != null){
        	logger.debug("commonEntityObj.getCommonEntitySK="
                    + commonEntityObj.getCommonEntitySK());
        	}

            Set eAddressSet = null;
            ArrayList eAddressVOList = null;
            EAddressVO eAddressVO = null;
            EAddressUsage eAddressUsage = null;
            Set phoneSet = null;
            ArrayList phoneVOList = null;
            PhoneVO phoneVO = null;
            PhoneUsage phoneUsage = null;
            Iterator listItr = null;
            AddressVO addressVO = null;
            AddressUsage addressUsage = null;
            Set addressSet = null;
            ArrayList addressVOList = null;

            if (commonEntityObj != null)
            {
            	
                commonEntityVO.setCommonEntitySK(commonEntityObj
                        .getCommonEntitySK());
                //commonEntityVO.setPayerID(commonEntityObj.getPayerID());
                commonEntityVO.setVoidIndicator(commonEntityObj
                        .getVoidIndicator());
                commonEntityVO.setSecCommunicationCode(commonEntityObj
                        .getSecCommunicationCode());
                /*
                 * Change done for ES2 DO changes
                 */
                /*
                 * commonEntityVO
                 * .setNoteSetVO(convertNoteSetDomainToVO(commonEntityObj
                 * .getNoteSet()));
                 */
                commonEntityVO.setCommonEntityType(commonEntityObj
                        .getCommonEntityTypeCode());

                commonEntityVO
                        .setContactVOList(convertCERepCrossRefSetToContactVOList(commonEntityObj
                                .getCommonEntityRepCrossReference(),controlRequired));

                /**
                 * start -- convertion of EAddress to EAddressVO starts from
                 * here
                 */

                eAddressSet = commonEntityObj.geteAddressUsage();

                if (eAddressSet != null)
                {

                    listItr = eAddressSet.iterator();
                    eAddressVOList = new ArrayList();
                    while (listItr.hasNext())
                    {
                        eAddressUsage = (EAddressUsage) listItr.next();
                        eAddressVO = createEAddressVOWithEAddressUsage(eAddressUsage,controlRequired);
                        eAddressVOList.add(eAddressVO);
                    }
                    commonEntityVO.setEaddressVOList(eAddressVOList);
                    sortEaddress(commonEntityVO.getEaddressVOList());
                }
                //convertion of EAddress to EAddressVO ends here

                //convertion of Phone to PhoneVO starts here
                phoneSet = commonEntityObj.getPhoneUsage();
                if (phoneSet != null)
                {
                    listItr = phoneSet.iterator();
                    phoneVOList = new ArrayList();
                    while (listItr.hasNext())
                    {
                        phoneUsage = (PhoneUsage) listItr.next();
                        phoneVO = createPhoneVOWithPhoneUsage(phoneUsage,controlRequired);
                        phoneVOList.add(phoneVO);
                    }
                    commonEntityVO.setPhoneVOList(phoneVOList);
                    sortPhone(commonEntityVO.getPhoneVOList(), null, true);
                }
                //convertion of Phone to PhoneVO ends here

                //Convertion of Address to AddressVO starts here
                addressSet = commonEntityObj.getAddressUsage();
                logger.debug("in common entity helper >>>> address list >>>"
                        + addressSet);
                if (addressSet != null)
                {
                	 logger
                     .debug("in common entity helper >>>> address list size>>>"
                             + addressSet.size());
                    listItr = addressSet.iterator();
                    addressVOList = new ArrayList();
                    //ArrayList addressHistVOList = new ArrayList();
                    while (listItr.hasNext())
                    {
                        Object obj = listItr.next();
                        addressUsage = (AddressUsage) obj;
                        logger
                                .debug("in common entity helper >>>> addressUsage >>>"
                                        + obj.getClass());
                        addressVO = createAddressVOWithAddressUsage(addressUsage,controlRequired);

                        /**
                         * Below condition is to check the end date of the
                         * address if end date is < or equal to system date then
                         * it would be set to address history or else it would
                         * current address
                         */

                        /*if (dateConverter(addressVO.getEndDate()).compareTo(
                                new Date()) <= 0)
                        {
                            addressHistVOList.add(addressVO);
                        }
                        else
                        {
                            addressVOList.add(addressVO);
                        }*/
                        addressVOList.add(addressVO);
                    }
                    sortCommonAddress(addressVOList);
                    commonEntityVO.setAddressVOList(addressVOList);
                }
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);

        }
        return commonEntityVO;
    }

    /**
     * This method converts the domain object to value object. It takes the
     * instances of Object to *be converted as input and returns the
     * corresponding value object with values from Object *being set to the vo.
     * 
     * @param commonEntityObj
     *            The commonEntity is to get commonentity details.
     * @return CommonEntityVO
     */
    public CommonEntityVO convertRepCommonEntityDomainToVO(
            CommonEntity commonEntityObj,boolean controlRequired)
    {
        CommonEntityVO commonEntityVO = new CommonEntityVO();

        logger.info("convertRepCommonEntityDomainToVO");
        try
        {
        	//Added if Condition for Find_Bugs-FIX
        	if(commonEntityObj != null){
        	logger.debug("commonEntityObj.getCommonEntitySK="
                    + commonEntityObj.getCommonEntitySK()
                    + " Version number is " + commonEntityObj.getVersionNo()); 
        	}
            Set eAddressSet = null;
            ArrayList eAddressVOList = null;
            EAddressVO eAddressVO = null;
            EAddressUsage eAddressUsage = null;
            Set phoneSet = null;
            ArrayList phoneVOList = null;
            PhoneVO phoneVO = null;
            PhoneUsage phoneUsage = null;
            Iterator listItr = null;
            AddressVO addressVO = null;
            AddressUsage addressUsage = null;
            Set addressSet = null;
            ArrayList addressVOList = null;

            if (commonEntityObj != null)
            {
                commonEntityVO.setCommonEntitySK(commonEntityObj
                        .getCommonEntitySK());
                commonEntityVO.setVersionNo(commonEntityObj.getVersionNo());
                
                //commonEntityVO.setPayerID(commonEntityObj.getPayerID());
                commonEntityVO.setVoidIndicator(commonEntityObj
                        .getVoidIndicator());
                commonEntityVO.setSecCommunicationCode(commonEntityObj
                        .getSecCommunicationCode());
                /*
                 * Change done for ES2 DO changes
                 */
                /*
                 * commonEntityVO
                 * .setNoteSetVO(convertNoteSetDomainToVO(commonEntityObj
                 * .getNoteSet()));
                 */
                commonEntityVO.setCommonEntityType(commonEntityObj
                        .getCommonEntityTypeCode());

                commonEntityVO
                        .setContactVOList(convertCERepCrossRefSetToContactVOList(commonEntityObj
                                .getCommonEntityRepCrossReference(),controlRequired));

                /**
                 * start -- convertion of EAddress to EAddressVO starts from
                 * here
                 */

                eAddressSet = commonEntityObj.geteAddressUsage();

                if (eAddressSet != null)
                {

                    listItr = eAddressSet.iterator();
                    eAddressVOList = new ArrayList();
                    while (listItr.hasNext())
                    {
                        eAddressUsage = (EAddressUsage) listItr.next();
                        eAddressVO = createEAddressVOWithEAddressUsage(eAddressUsage,controlRequired);
                        eAddressVOList.add(eAddressVO);
                    }
                    commonEntityVO.setEaddressVOList(eAddressVOList);
                }
                //convertion of EAddress to EAddressVO ends here

                //convertion of Phone to PhoneVO starts here
                phoneSet = commonEntityObj.getPhoneUsage();
                if (phoneSet != null)
                {
                    listItr = phoneSet.iterator();
                    phoneVOList = new ArrayList();
                    while (listItr.hasNext())
                    {
                        phoneUsage = (PhoneUsage) listItr.next();
                        phoneVO = createPhoneVOWithPhoneUsage(phoneUsage,controlRequired);
                        phoneVOList.add(phoneVO);
                    }
                    commonEntityVO.setPhoneVOList(phoneVOList);
                }
                //convertion of Phone to PhoneVO ends here

                //Convertion of Address to AddressVO starts here
                addressSet = commonEntityObj.getAddressUsage();
                logger.debug("in common entity helper >>>> address list >>>"
                        + addressSet);
                if (addressSet != null)
                {
                	  logger
                      .debug("in common entity helper >>>> address list size>>>"
                              + addressSet.size());
                    listItr = addressSet.iterator();
                    addressVOList = new ArrayList();
                    ArrayList addressHistVOList = new ArrayList();
                    while (listItr.hasNext())
                    {
                        Object obj = listItr.next();
                        addressUsage = (AddressUsage) obj;
                        
                        addressVO = createAddressVOWithAddressUsage(addressUsage,controlRequired);

                        /**
                         * Below condition is to check the end date of the
                         * address if end date is < or equal to system date then
                         * it would be set to address history or else it would
                         * current address
                         */

                        if (dateConverter(addressVO.getEndDate()).compareTo(
                                new Date()) <= 0)
                        {
                            addressHistVOList.add(addressVO);
                        }
                        else
                        {
                            addressVOList.add(addressVO);
                        }
                    }
                    commonEntityVO.setAddressVOList(addressVOList);
                    commonEntityVO.setAddressHistoryVOList(addressHistVOList);
                }
                //              Convertion of Address to AddressVO ends here
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);

        }
        return commonEntityVO;
    }

    /**
     * This method converts the value object to domain object. It takes the
     * instances of Object to *be converted as input and returns the
     * corresponding doamin object with values from Object *being set to the
     * domain.
     * 
     * @param commonEntityVO
     *            The commonEntityVO is to get commonentity details.
     * @return CommonEntity
     */
    public CommonEntity convertCommonEntityVOToDomain(
            CommonEntityVO commonEntityVO)
    {

        CommonEntity commonEntityObj = new CommonEntity();
        Set commonEntityRepCrossRefSet = null;
        Set eAddressSet = null;
        ArrayList eAddressVOList = null;
        EAddressUsage eAddressUsage = null;
        Set phoneSet = null;
        ArrayList phoneVOList = null;
        PhoneUsage phoneUsage = null;
        Iterator itr = null;
        AddressUsage addressUsage = null;
        Set<AddressUsage> addressSet = new HashSet<AddressUsage>();
        ArrayList addressVOList = null;
        try{
        if (commonEntityVO != null)
        {

            commonEntityObj.setAddedAuditTimeStamp(new Timestamp(0));
            commonEntityObj.setAddedAuditUserID(getUserID());
            CommonEntityType commonEntityType = new CommonEntityType();
            /*
             * Change done for ES2 DO changes
             */
            if (commonEntityVO.getCommonEntityType() == null
                    || commonEntityVO.getCommonEntityType().trim().length() == 0)
            {
                commonEntityType
                        .setCommonEntityTypeCode(ProgramConstants.DEFAULT_ENT_TYP_CODE);
            }
            else
            {
                commonEntityType.setCommonEntityTypeCode(commonEntityVO
                        .getCommonEntityType());
            }

            commonEntityObj.setCommonEntityTypeCode(commonEntityType
                    .getCommonEntityTypeCode());
            //commonEntityObj.setPayerID(commonEntityVO.getPayerID());
            commonEntityObj.setAuditTimeStamp(new Date());
            commonEntityObj.setAuditUserID(getUserID());
            commonEntityObj.setVersionNo(commonEntityVO.getVersionNo());

            if (commonEntityVO.getVoidIndicator() == null)
            {
                commonEntityObj.setVoidIndicator(Boolean.FALSE);
            }
            else
            {
                commonEntityObj.setVoidIndicator(commonEntityVO
                        .getVoidIndicator());
            }

            commonEntityObj.setCommonEntitySK(commonEntityVO
                    .getCommonEntitySK());
            commonEntityObj.setPreferredCommunicationCode(commonEntityVO
                    .getPrimCommunicationCode());
            commonEntityObj.setSecCommunicationCode(commonEntityVO
                    .getSecCommunicationCode());
            /*
             * Change done for ES2 DO changes
             */
            // Setting NOTESET
            /*
             * commonEntityObj.setNoteSet(convertNoteSetVOToDomain(commonEntityVO
             * .getNoteSetVO()));
             */
            //end of stting NOTESET
            //  Setting Representative information
            ArrayList contactVOList = (ArrayList) commonEntityVO
                    .getContactVOList();
            if (contactVOList != null)
            {

                commonEntityRepCrossRefSet = convertContactVOListToCERepCrossRefSet(
                        contactVOList, commonEntityObj);
                commonEntityObj
                        .setCommonEntityRepCrossReference(commonEntityRepCrossRefSet);
            }
            // End of setting Representative information

            //start -- code for getting EAddressUsage from CommonEntityVO
            eAddressVOList = (ArrayList) commonEntityVO.getEaddressVOList();
            if (eAddressVOList != null)
            {
                itr = eAddressVOList.iterator();
                eAddressSet = new HashSet();
                while (itr.hasNext())
                {
                    eAddressUsage = getEAddressUsageFromEAddressVO((EAddressVO) itr
                            .next());
                    eAddressSet.add(eAddressUsage);
                }
                commonEntityObj.seteAddressUsage(eAddressSet);
            }
            //end -- code for getting EAddressUsage from CommonEntityVO

            //start -- code for getting PhoneUsage from CommonEntityVO
            phoneVOList = (ArrayList) commonEntityVO.getPhoneVOList();
            if (phoneVOList != null)
            {
                itr = phoneVOList.iterator();
                phoneSet = new HashSet();
                while (itr.hasNext())
                {
                    phoneUsage = getPhoneUsageFromPhoneVO((PhoneVO) itr.next());
                    phoneSet.add(phoneUsage);
                }
                commonEntityObj.setPhoneUsage(phoneSet);
            }
            //end -- code for getting PhoneUsage from CommonEntityVO
            commonEntityObj.setAddressUsage(null);

            //          Convertion of AddressVO to Address starts here
            addressVOList = (ArrayList) commonEntityVO.getAddressVOList();

            if (addressVOList != null && !(addressVOList.isEmpty()))
            {
                itr = addressVOList.iterator();
                addressSet = new HashSet<AddressUsage>();
                while (itr.hasNext())
                {
                    addressUsage = getAddressUsageFromAddressVO((AddressVO) itr
                            .next());
                    logger.debug("address usage...>>> " + addressUsage);
                    addressSet.add(addressUsage);
                }
            }

            ArrayList addressHistList = commonEntityVO
                    .getAddressHistoryVOList();
            if (addressHistList != null && !(addressHistList.isEmpty()))
            {
                itr = addressHistList.iterator();

                while (itr.hasNext())
                {
                    addressUsage = getAddressUsageFromAddressVO((AddressVO) itr
                            .next());
                    logger.debug("address usage...>>> " + addressUsage);
                    addressSet.add(addressUsage);
                }
            }
            if (addressSet != null && !(addressSet.isEmpty()))
            {
                commonEntityObj.setAddressUsage(addressSet);
            }
           }
        }catch(Exception e)
        {
     	   logger.debug("Inside Exception ");
     	   
        }
        return commonEntityObj;
    }

    /**
     * This method converts the value object to domain object. It takes the
     * instances of Object to *be converted as input and returns the
     * corresponding doamin object with values from Object *being set to the
     * domain.
     * 
     * @param commonEntityVO
     *            The commonEntityVO is to get commonentity details.
     * @return CommonEntity
     */
    public CommonEntity convertRepCommonEntityVOToDomain(
            CommonEntityVO commonEntityVO)
    {

        CommonEntity commonEntityObj = new CommonEntity();
        Set commonEntityRepCrossRefSet = null;
        Set eAddressSet = null;
        ArrayList eAddressVOList = null;
        EAddressUsage eAddressUsage = null;
        Set phoneSet = null;
        ArrayList phoneVOList = null;
        PhoneUsage phoneUsage = null;
        Iterator itr = null;

        AddressUsage addressUsage = null;
        Set addressSet = new HashSet();
        ArrayList addressVOList = null;

        if (commonEntityVO != null)
        {

            commonEntityObj.setAddedAuditTimeStamp(new Timestamp(0));
            commonEntityObj.setAddedAuditUserID(getUserID());
            CommonEntityType commonEntityType = new CommonEntityType();
            /*
             * Change done for ES2 DO changes
             */
            if (commonEntityVO.getCommonEntityType() == null
                    || commonEntityVO.getCommonEntityType().trim().length() == 0)
            {
                commonEntityType
                        .setCommonEntityTypeCode(ProgramConstants.DEFAULT_ENT_TYP_CODE);
            }
            else
            {
                commonEntityType.setCommonEntityTypeCode(commonEntityVO
                        .getCommonEntityType());
            }

            commonEntityObj.setCommonEntityTypeCode(commonEntityType
                    .getCommonEntityTypeCode());
            //commonEntityObj.setPayerID(commonEntityVO.getPayerID());
            commonEntityObj.setAuditTimeStamp(new Date());
            commonEntityObj.setAuditUserID(getUserID());
            commonEntityObj.setVersionNo(commonEntityVO.getVersionNo());

            if (commonEntityVO.getVoidIndicator() == null)
            {
                commonEntityObj.setVoidIndicator(Boolean.FALSE);
            }
            else
            {
                commonEntityObj.setVoidIndicator(commonEntityVO
                        .getVoidIndicator());
            }

            commonEntityObj.setCommonEntitySK(commonEntityVO
                    .getCommonEntitySK());
           
            commonEntityObj.setPreferredCommunicationCode(commonEntityVO
                    .getPrimCommunicationCode());
            commonEntityObj.setSecCommunicationCode(commonEntityVO
                    .getSecCommunicationCode());
            /*
             * Change done for ES2 DO changes
             */
            // Setting NOTESET
            /*
             * commonEntityObj.setNoteSet(convertNoteSetVOToDomain(commonEntityVO
             * .getNoteSetVO()));
             */
            //end of stting NOTESET
            //  Setting Representative information
            ArrayList contactVOList = (ArrayList) commonEntityVO
                    .getContactVOList();
            if (contactVOList != null)
            {

                commonEntityRepCrossRefSet = convertContactVOListToCERepCrossRefSet(
                        contactVOList, commonEntityObj);
                commonEntityObj
                        .setCommonEntityRepCrossReference(commonEntityRepCrossRefSet);
            }

            // End of setting Representative information

            //start -- code for getting EAddressUsage from CommonEntityVO
            eAddressVOList = (ArrayList) commonEntityVO.getEaddressVOList();

            if (eAddressVOList != null)
            {
                itr = eAddressVOList.iterator();
                eAddressSet = new HashSet();
                while (itr.hasNext())
                {
                    eAddressUsage = getEAddressUsageFromEAddressVO((EAddressVO) itr
                            .next());
                    eAddressSet.add(eAddressUsage);
                }
                commonEntityObj.seteAddressUsage(eAddressSet);
            }
            //end -- code for getting EAddressUsage from CommonEntityVO

            //start -- code for getting PhoneUsage from CommonEntityVO
            phoneVOList = (ArrayList) commonEntityVO.getPhoneVOList();
            if (phoneVOList != null)
            {
                itr = phoneVOList.iterator();
                phoneSet = new HashSet();
                while (itr.hasNext())
                {
                    phoneUsage = getPhoneUsageFromPhoneVO((PhoneVO) itr.next());
                    phoneSet.add(phoneUsage);
                }
                commonEntityObj.setPhoneUsage(phoneSet);
            }
            //end -- code for getting PhoneUsage from CommonEntityVO
            commonEntityObj.setAddressUsage(null);

            //          Convertion of AddressVO to Address starts here
            addressVOList = (ArrayList) commonEntityVO.getAddressVOList();

            if (addressVOList != null && !(addressVOList.isEmpty()))
            {
                itr = addressVOList.iterator();
                addressSet = new HashSet();
                while (itr.hasNext())
                {
                    addressUsage = getAddressUsageFromAddressVO((AddressVO) itr
                            .next());
                    logger.debug("address usage...>>> " + addressUsage);
                    addressSet.add(addressUsage);
                }
            }

            ArrayList addressHistList = commonEntityVO
                    .getAddressHistoryVOList();
            if (addressHistList != null && !(addressHistList.isEmpty()))
            {
                itr = addressHistList.iterator();

                while (itr.hasNext())
                {
                    addressUsage = getAddressUsageFromAddressVO((AddressVO) itr
                            .next());
                   
                    addressSet.add(addressUsage);
                }
            }
            if (addressSet != null && !(addressSet.isEmpty()))
            {
                commonEntityObj.setAddressUsage(addressSet);
            }
        }
        return commonEntityObj;
    }

    /**
     * This method would convert the CommonContactVO ArrayList to
     * CommonEntityRepCrossReference Set.
     * 
     * @param contactVOList
     *            The contactVOList is to get contactVO List.
     * @param commonEntity
     *            The commonEntity is to get commonEntity.
     * @return Set
     */

    private Set convertContactVOListToCERepCrossRefSet(ArrayList contactVOList,
            CommonEntity commonEntity)
    {

        Set commonEntityRepCrossRefSet = null;
        try
        {
            if (contactVOList != null && !(contactVOList.isEmpty()))
            {
                //"FindBugs" issue resolved
            	//CommonEntityRepCrossReference commonEntityRepCrossRef = new CommonEntityRepCrossReference();
            	CommonEntityRepCrossReference commonEntityRepCrossRef = null;
                commonEntityRepCrossRefSet = new HashSet();
                int listSize = contactVOList.size();

                for (int i = 0; i < listSize; i++)
                {

                    CommonContactVO contactVO = (CommonContactVO) contactVOList
                            .get(i);
                    Representative representative = new Representative();
                    if(contactVO
                            .getDateOfBirth()!=null && !(contactVO
                            .getDateOfBirth().equalsIgnoreCase("")))
                    {
                    representative.setDateOfBirth(dateConverter(contactVO
                            .getDateOfBirth()));
                    }
                    if(contactVO
                            .getDateOfDeath()!=null && !(contactVO
                                    .getDateOfDeath().equalsIgnoreCase("")))
                    {
                    representative.setDateOfDeath(dateConverter(contactVO
                            .getDateOfDeath()));
                    }
                    representative.setGenderCode(contactVO.getGender());
                    representative.setName(convertNameVOToDomain(contactVO
                            .getNameVO()));
                    representative
                            .setRepresentativeSK(contactVO.getContactSK());
                    String newSSN = ProgramConstants.EMPTY_STRING;
                    if (contactVO.getSSN() != null
                            && contactVO.getSSN().length() > 0)
                    {
                        newSSN = contactVO.getSSN().replaceAll(
                                ProgramConstants.HYPHEN_SEPARATOR,
                                ProgramConstants.EMPTY_STRING);
                        representative.setSsnNumber(newSSN);
                    }else
                    {
                    representative.setSsnNumber(null);
                    }
                    representative.setCommonEntityTypeCode(commonEntity.getCommonEntityTypeCode());
                    representative.setPhoneticFirstName(contactVO.getNameVO()
                            .getPhoneticFirstName());
                    representative.setPhoneticLastName(contactVO.getNameVO()
                            .getPhoneticLastName());
                    representative.setNamePrefixCode(contactVO.getPrefix());
                    /*
                     * this would hold chaild relation, need to change this as
                     * rep commonentity
                     */
                    representative
                            .setRepCommonEntity(convertRepCommonEntityVOToDomain(contactVO
                                    .getRepCommonEntityVO()));
                    representative.getRepCommonEntity().setCommonEntityTypeCode(commonEntity.getCommonEntityTypeCode());
                    representative.setAddedAuditTimeStamp(new Timestamp(0));
                    representative.setAddedAuditUserID(getUserID());
                    representative.setAuditTimeStamp(new Timestamp(0));
                    representative.setAuditUserID(getUserID());
                    representative.setVersionNo(contactVO.getVersionNo());

                    //change done for ES2
                    /*
                     * if (contactVO.getEntityType() != null &&
                     * contactVO.getEntityType().trim().length() > 0) { ceType =
                     * new CommonEntityType();
                     * ceType.setCommonEntityTypeCode(contactVO
                     * .getEntityType()); }
                     */
                    /*
                     * Change done for ES2 DO changes
                     */
                    //representative.setCommonEntityTypeCode(ceType.getCommonEntityTypeCode());
                    /*
                     * ArrayList commonContactTypeList = contactVO
                     * .getCommonContactTypeVOList(); int typeLstSize =
                     * commonContactTypeList.size();
                     */
                    /*
                     * for (int j = 0; j < typeLstSize; j++) {
                     */

                    /*
                     * CommonContactTypeVO contactTypeVO = (CommonContactTypeVO)
                     * commonContactTypeList .get(j);
                     */
                    commonEntityRepCrossRef = new CommonEntityRepCrossReference();
                    if (contactVO.getExistingContactType() != null
                            && contactVO.getExistingContactType().equals(
                                    contactVO.getContactType()))
                    {
                        representative.setRepresentativeSK(contactVO
                                .getContactSK());
                        representative.setVersionNo(contactVO.getVersionNo());
                        commonEntityRepCrossRef.setVersionNo(contactVO
                                .getCommonEntityRepCrossReferenceVersionNo());
                       /* representative.getRepCommonEntity().setCommonEntitySK(
                                null);
                        */
                    }
                    commonEntityRepCrossRef
                            .setAddedAuditTimeStamp(new Timestamp(0));
                    commonEntityRepCrossRef.setAddedAuditUserID(getUserID());
                    commonEntityRepCrossRef.setAuditTimeStamp(new Timestamp(0));
                    commonEntityRepCrossRef.setAuditUserID(getUserID());

                    commonEntityRepCrossRef
                            .setBeginDate(dateConverter(contactVO
                                    .getBeginDate()));
                    commonEntityRepCrossRef.setEndDate(dateConverter(contactVO
                            .getEndDate()));
                    commonEntityRepCrossRef.setRepresentativeSK(contactVO
                            .getContactSK());
                    commonEntityRepCrossRef.setRelationshipCode(null);
                    commonEntityRepCrossRef
                            .setRepresentativeXReferenceStatusCode(contactVO
                                    .getStatus());
                    commonEntityRepCrossRef
                            .setRepresentativeCrossReferenceCode(contactVO
                                    .getContactType());
                    commonEntityRepCrossRef.setRepresentative(representative);
                    commonEntityRepCrossRef.setRepresentativeSK(contactVO
                            .getContactSK());

                    commonEntityRepCrossRef
                            .setRepresentativeSignificantCode(contactVO
                                    .getSignificance());

                    commonEntityRepCrossRef.setCommonEntity(commonEntity);
                    /*
                     * Change done for ES2 DO changes
                     */
                    /*
                     * if (ProgramNumberConstants.ONE.equals(contactTypeVO
                     * .getVoidIndicator())) { commonEntityRepCrossRef
                     * .setVoidIndicator(new Boolean(false)); } else {
                     * commonEntityRepCrossRef .setVoidIndicator(new
                     * Boolean(true)); }
                     */
                    commonEntityRepCrossRefSet.add(commonEntityRepCrossRef);

                }
            }

        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return commonEntityRepCrossRefSet;
    }

    /**
     * This method would convert the CommonContactVO ArrayList to
     * CommonEntityRepCrossReference Set.
     * 
     * @param contactVO
     *            holds contact data.
     * @param contactTypeVO
     *            holds contact type data.
     * @param commonEntitySK
     *            holds commonEntitySK.
     * @return CommonEntityRepCrossReference returns
     *         CommonEntityRepCrossReference.
     */

    public CommonEntityRepCrossReference convertContactTypeVOToCrossRef(
            CommonContactVO contactVO, CommonContactTypeVO contactTypeVO,
            Long commonEntitySK)
    {

        CommonEntityRepCrossReference commonEntityRepCrossRef = null;

        try
        {

            commonEntityRepCrossRef = new CommonEntityRepCrossReference();
            commonEntityRepCrossRef.setAddedAuditTimeStamp(new Timestamp(0));
            commonEntityRepCrossRef.setAddedAuditUserID(getUserID());
            commonEntityRepCrossRef.setAuditTimeStamp(new Timestamp(0));
            commonEntityRepCrossRef.setAuditUserID(getUserID());
            commonEntityRepCrossRef.setVersionNo(contactTypeVO.getVersionNo());
            
            commonEntityRepCrossRef.setBeginDate(dateConverter(contactTypeVO
                    .getBeginDate()));
            commonEntityRepCrossRef.setEndDate(dateConverter(contactTypeVO
                    .getEndDate()));
            commonEntityRepCrossRef.setRelationshipCode(null);
            commonEntityRepCrossRef
                    .setRepresentativeCrossReferenceCode(contactTypeVO
                            .getContactType());
            /*
             * commonEntityRepCrossRef .setRepresentative(representative);
             */
            commonEntityRepCrossRef.setRepresentativeSK(contactVO
                    .getContactSK());

            commonEntityRepCrossRef
                    .setRepresentativeSignificantCode(contactTypeVO
                            .getSignificance());

            CommonEntity commonEntity = new CommonEntity();
            commonEntity.setCommonEntitySK(commonEntitySK);

            commonEntityRepCrossRef.setCommonEntity(commonEntity);
            /*
             * Change done for ES2 DO changes
             */
            /*
             * if (ProgramNumberConstants.ONE.equals(contactTypeVO
             * .getVoidIndicator())) {
             * commonEntityRepCrossRef.setVoidIndicator(new Boolean(false)); }
             * else { commonEntityRepCrossRef.setVoidIndicator(new
             * Boolean(true)); }
             */

        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return commonEntityRepCrossRef;
    }

    /**
     * This method is to convert NameVO to Name Domain.
     * 
     * @param nameVO
     *            The nameVO to get name information.
     * @return Name returns Name.
     */

    private Name convertNameVOToDomain(NameVO nameVO)
    {

        Name repName = null;
        boolean updateFlag =false;
        if (nameVO != null)
        {
            repName = new Name();
            if(StringUtils.isNotEmpty(nameVO.getFirstName())){
             repName.setFirstName(nameVO.getFirstName());
             updateFlag = true;
            }
            if(StringUtils.isNotEmpty(nameVO.getLastName())){
             repName.setLastName(nameVO.getLastName());
             updateFlag = true;
            }
            if(StringUtils.isNotEmpty(nameVO.getMiddleName())){
             repName.setMiddleName(nameVO.getMiddleName());
             updateFlag = true;
            }
            if(StringUtils.isNotEmpty(nameVO.getSortName())){
             repName.setSortName(nameVO.getSortName());
             updateFlag = true;
            }
            if(StringUtils.isNotEmpty(nameVO.getSuffixName())){
             repName.setSuffixName(nameVO.getSuffixName());
             updateFlag = true;
            }
            if(StringUtils.isNotEmpty(nameVO.getTitleName())){
             repName.setTitleName(nameVO.getTitleName());
             updateFlag = true;
            }
            
        }
        if(updateFlag){
         return repName;
        }
        else{
         return null; 
        }

    	/*
        Name repName = null;
        if (nameVO != null)
        {
            repName = new Name();
            repName.setFirstName(nameVO.getFirstName());
            repName.setLastName(nameVO.getLastName());
            repName.setMiddleName(nameVO.getMiddleName());
            repName.setSortName(nameVO.getSortName());
            repName.setSuffixName(nameVO.getSuffixName());
            repName.setTitleName(nameVO.getTitleName());
        }
        return repName;*/
    	
    	
    }

    /**
     * This method is to convert Name domain to NameVO.
     * 
     * @param name
     *            The name to get name information.
     * @return NameVO returns NameVO.
     */

    private NameVO convertNameDomianToVO(Name name)
    {
        NameVO nameVO = null;
        if (name != null)
        {
            nameVO = new NameVO();
            nameVO.setFirstName(name.getFirstName());
            nameVO.setLastName(name.getLastName());
            nameVO.setMiddleName(name.getMiddleName());
            nameVO.setSortName(name.getSortName());
            nameVO.setSuffixName(name.getSuffixName());
            nameVO.setSuffixNameDesc(getDescriptionFromVV(name.getSuffixName(), 
            		commonEntityDataBean.getNameSuffixList()));
            nameVO.setTitleName(name.getTitleName());
        }
        return nameVO;
    }

    /**
     * This method would convert the CommonContactVO to Representative domain
     * object.
     * 
     * @param contactVO
     *            The contactVO holds contact information.
     * @return Representative returns Representative.
     */

    public Representative convertCommonContactVOToDomain(
            CommonContactVO contactVO)
    {
        Representative representative = null;
        CommonEntityRepCrossReference commonEntityRepCrossRef = null;
        Set commonEntityRepCrossRefSet = null;
        if (contactVO != null)
        {
            representative = new Representative();
            commonEntityRepCrossRef = new CommonEntityRepCrossReference();
            /*
             * Change done for ES2 DO changes
             */
            representative.setCommonEntityTypeCode(null);
            representative.setDateOfBirth(dateConverter(contactVO
                    .getDateOfBirth()));
            representative.setDateOfDeath(dateConverter((contactVO
                    .getDateOfDeath())));
            representative.setGenderCode(contactVO.getGender());
            representative
                    .setName(convertNameVOToDomain(contactVO.getNameVO()));
            representative.setRepresentativeSK(contactVO.getContactSK());
            representative.setSsnNumber(contactVO.getSSN());
            representative.setPhoneticFirstName(contactVO.getNameVO()
                    .getPhoneticFirstName());
            representative.setPhoneticLastName(contactVO.getNameVO()
                    .getPhoneticLastName());
            //representative.setCommonEntity();
            ArrayList commonContactTypeList = contactVO
                    .getCommonContactTypeVOList();
            if (commonContactTypeList != null
                    && !(commonContactTypeList.isEmpty()))
            {
                commonEntityRepCrossRefSet = new HashSet();
                int typeLstSize = commonContactTypeList.size();
                for (int j = 0; j == typeLstSize; j++)
                {
                    CommonContactTypeVO contactTypeVO = (CommonContactTypeVO) commonContactTypeList
                            .get(j);

                    commonEntityRepCrossRef
                            .setAddedAuditTimeStamp(new Timestamp(0));
                    commonEntityRepCrossRef.setAddedAuditUserID(getUserID());
                    commonEntityRepCrossRef.setAuditTimeStamp(new Timestamp(0));
                    commonEntityRepCrossRef.setAuditUserID(getUserID());
                    commonEntityRepCrossRef.setVersionNo(contactTypeVO
                            .getVersionNo());
                    commonEntityRepCrossRef
                            .setBeginDate(dateConverter(contactTypeVO
                                    .getBeginDate()));
                    commonEntityRepCrossRef
                            .setEndDate(dateConverter(contactTypeVO
                                    .getEndDate()));
                    /*
                     * Change done for ES2 DO changes
                     */
                    //commonEntityRepCrossRef.setVoidIndicator(new
                    // Boolean(true));
                    commonEntityRepCrossRef.setMemberCaseRepresentatives(null);
                    commonEntityRepCrossRef.setMemberRepresentatives(null);
                    commonEntityRepCrossRef.setProviderRepresentatives(null);
                    commonEntityRepCrossRef.setRepresentative(representative);
                    commonEntityRepCrossRef.setTplCarrierRepresentatives(null);

                    commonEntityRepCrossRefSet.add(commonEntityRepCrossRef);
                }
            }
            /*
             * representative
             * .setCommonEntityRepCrossReferences(commonEntityRepCrossRefSet);
             */
        }
        return representative;
    }

    /**
     * To convert Rep object to VO.
     * 
     * @param rep
     *            This object hods Rep inormation.
     * @return commonConatctVO returns commonConatctVO.
     */

    private CommonContactVO convertRepDomainToCommonConatctVO(Representative rep,boolean controlRequired)
    {

        CommonContactVO commonConatctVO = new CommonContactVO();
        /*
         * Change done for ES2 DO changes
         */
        commonConatctVO.setEntityType(rep.getCommonEntityTypeCode());
        /*
         * commonConatctVO.setEntityTypeDesc(rep.getCommonEntityType()
         * .getDescription());
         */

        commonConatctVO.setContactSK(rep.getRepresentativeSK());
        commonConatctVO.setDateOfBirth(dateConverter(rep.getDateOfBirth()));
        commonConatctVO.setDateOfDeath(dateConverter(rep.getDateOfDeath()));
        commonConatctVO.setGender(rep.getGenderCode());
        commonConatctVO.setNameVO(convertNameDomianToVO(rep.getName()));
        commonConatctVO.getNameVO().setPhoneticLastName(
                rep.getPhoneticLastName());

        commonConatctVO.setAddedAuditTimeStamp(rep.getAddedAuditTimeStamp());
        commonConatctVO.setAddedAuditUserID(rep.getAddedAuditUserID());
        commonConatctVO.setAuditTimeStamp(rep.getAuditTimeStamp());
        commonConatctVO.setAuditUserID(rep.getAuditUserID());
        commonConatctVO.setVersionNo(rep.getVersionNo());

        String ssn = rep.getSsnNumber();
        if (ssn != null && ssn.trim() != null && ssn.trim().length() > 0)
        {
            StringBuffer newSSN = new StringBuffer();
            int size = ssn.length();
            for (int i = 0; i < size ; i++)
            {
                if (i == ProgramConstants.INT_THREE
                        || i == ProgramConstants.INT_FIVE)
                {
                    newSSN.append(ProgramConstants.HYPHEN_SEPARATOR);
                    newSSN.append(ssn.charAt(i));
                }
                else
                {
                    newSSN.append(ssn.charAt(i));
                }
            }
            ssn = newSSN.toString();
        }
        commonConatctVO.setSSN(ssn);
        
        commonConatctVO
                .setRepCommonEntityVO(convertRepCommonEntityDomainToVO(rep
                        .getRepCommonEntity(),controlRequired));
        
        return commonConatctVO;

    }

    /**
     * This is convert Domain object to VO.
     * 
     * @param commonEntityRepCrossReference
     *            This object holds commonEntityRepCrossReference information.
     * @return CommonContactTypeVO returns CommonContactTypeVO.
     */

    private CommonContactVO convertCERepCrossRefDomainTOCommonContactTypeVO(
            CommonEntityRepCrossReference commonEntityRepCrossReference)
    {

        logger.info("convertCERepCrossRefDomainTOCommonContactTypeVO");
        //CommonContactTypeVO commonContactTypeVO = new CommonContactTypeVO();
        CommonContactVO commonContactVO = new CommonContactVO();

        commonContactVO
                .setBeginDate(dateConverter(commonEntityRepCrossReference
                        .getBeginDate()));
        commonContactVO.setContactType(commonEntityRepCrossReference
                .getRepresentativeCrossReferenceCode());

        /* to get the contactType description form valivalues */
        commonContactVO.setContactTypeDesc(getDescriptionFromVV(commonContactVO
                .getContactType(), commonEntityDataBean.getContactTypeList()));

        commonContactVO.setEndDate(dateConverter(commonEntityRepCrossReference
                .getEndDate()));

        commonContactVO.setSignificance(commonEntityRepCrossReference
                .getRepresentativeSignificantCode());

        /* to get the Significance description form valivalues */
        /*
         * commonContactVO.setSignificanceDesc(getDescriptionFromVV(
         * commonContactVO.getSignificance(), commonEntityDataBean
         * .getContactSigList()));
         */

        /*
         * commonContactVO
         * .setStrBeginDate(getDateInMMDDYYYYFormat(commonEntityRepCrossReference
         * .getBeginDate())); commonContactTypeVO
         * .setStrEndDate(getDateInMMDDYYYYFormat(commonEntityRepCrossReference
         * .getEndDate()));
         */
        /*
         * Change done for ES2 DO changes
         */
        /*
         * if (commonEntityRepCrossReference.getVoidIndicator() != null &&
         * commonEntityRepCrossReference.getVoidIndicator() .booleanValue()) {
         * commonContactTypeVO.setVoidIndicator(ProgramNumberConstants.ZERO); }
         * else {
         * commonContactTypeVO.setVoidIndicator(ProgramNumberConstants.ONE); }
         */

        commonContactVO.setAddedAuditTimeStamp(commonEntityRepCrossReference
                .getAddedAuditTimeStamp());
        commonContactVO.setAddedAuditUserID(commonEntityRepCrossReference
                .getAddedAuditUserID());
        commonContactVO.setAuditUserID(commonEntityRepCrossReference
                .getAuditUserID());
        commonContactVO.setAuditTimeStamp(commonEntityRepCrossReference
                .getAuditTimeStamp());
        commonContactVO.setContactSK(commonEntityRepCrossReference
                .getRepresentativeSK());
        commonContactVO.setVersionNo(commonEntityRepCrossReference
                .getVersionNo());
        return commonContactVO;

    }

    /**
     * This method would convert the CommonContactVO ArrayList to
     * CommonEntityRepCrossReference Set.
     * 
     * @param commonEntityRepCrossRefSet
     *            This object holds contact list.
     * @return ArrayList returns ArrayList.
     */

    private ArrayList convertCERepCrossRefSetToContactVOList(
            Set commonEntityRepCrossRefSet,boolean controlRequired)
    {
        logger.info("convertCERepCrossRefSetToContactVOList");

        HashMap repMap = new HashMap();
        ArrayList contactVOList = null;

        if (commonEntityRepCrossRefSet != null
                && !(commonEntityRepCrossRefSet.isEmpty()))
        {
            /* loading validvalues for contact */
            commonEntityDataBean = getCommonEntityDataBean();
            if (commonEntityDataBean.getEntityTypeList() == null
                    || commonEntityDataBean.getEntityTypeList().size() == 0)
            {
                loadValidValuesForContact();
            }
            /* end of loading valid values */

            contactVOList = new ArrayList();
            
            CommonContactVO commonContactVO = new CommonContactVO();
            Iterator itr = commonEntityRepCrossRefSet.iterator();
   
            while (itr.hasNext())
            {

                CommonEntityRepCrossReference commonEntityRepCrossReference = (CommonEntityRepCrossReference) itr
                        .next();
                Representative rep = commonEntityRepCrossReference
                        .getRepresentative();

                if (repMap.containsKey(rep.getRepresentativeSK()))
                {
                    commonContactVO = (CommonContactVO) repMap.get(rep
                            .getRepresentativeSK());
                }
                else
                {
                    commonContactVO = convertRepDomainToCommonConatctVO(rep,controlRequired);

                }
                commonContactVO
                        .setBeginDate(dateConverter(commonEntityRepCrossReference
                                .getBeginDate()));
                commonContactVO
                        .setEndDate(dateConverter(commonEntityRepCrossReference
                                .getEndDate()));
                commonContactVO.setStatus(commonEntityRepCrossReference
                        .getRepresentativeXReferenceStatusCode());
                commonContactVO.setContactType(commonEntityRepCrossReference
                        .getRepresentativeCrossReferenceCode());
                commonContactVO.setContactTypeDesc(getDescriptionFromVV(commonContactVO
						  .getContactType(), commonEntityDataBean.getContactTypeList()));
                commonContactVO.setStatusDesc(getDescriptionFromVV(commonContactVO
						  .getStatus(), commonEntityDataBean.getStatusList()));
                commonContactVO.setSignificance(commonEntityRepCrossReference
                		.getRepresentativeSignificantCode());
                commonContactVO
                        .setExistingContactType(commonEntityRepCrossReference
                                .getRepresentativeCrossReferenceCode());
                commonContactVO
                        .setAddedAuditTimeStamp(commonEntityRepCrossReference
                                .getAddedAuditTimeStamp());
                commonContactVO
                        .setAddedAuditUserID(commonEntityRepCrossReference
                                .getAddedAuditUserID());
                commonContactVO.setAuditUserID(commonEntityRepCrossReference
                        .getAuditUserID());
                commonContactVO.setAuditTimeStamp(commonEntityRepCrossReference
                        .getAuditTimeStamp());
                commonContactVO.setContactSK(commonEntityRepCrossReference
                        .getRepresentativeSK());
                commonContactVO
                        .setCommonEntityRepCrossReferenceVersionNo(commonEntityRepCrossReference
                                .getVersionNo());
                //prefix value after major save
                commonContactVO.setPrefix(rep.getNamePrefixCode());
                if(controlRequired){
                createVOAuditKeysList(commonEntityRepCrossReference,commonContactVO);
                createVOAuditKeysList(commonEntityRepCrossReference.getCommonEntity(),commonContactVO);
                createVOAuditKeysList(commonEntityRepCrossReference.getRepresentative(),commonContactVO);
                }
              //  createVOAuditKeysList(commonEntityRepCrossReference.getRepresentative().getName(),commonContactVO);

                repMap.put(rep.getRepresentativeSK(), commonContactVO);
            }
        }
        if (repMap != null && !(repMap.isEmpty()))
        {
            Iterator itr = repMap.keySet().iterator();
            while (itr.hasNext())
            {
                contactVOList.add(repMap.get(itr.next()));
            }
        }

        return contactVOList;
    }

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

            List notesVOList = commonEntityDataBean.getNoteList();
            if (notesVOList != null && !(notesVOList.isEmpty()))
            {
            	logger.info("++notesVOList size=="+notesVOList.size());
                noteSetObj = new NoteSet();
                noteSetObj.setNoteSetSK(noteSetVOObj.getNoteSetSK());
                noteSetObj.setNoteSourceName(noteSetVOObj.getNoteSourceName());

                int listSize = notesVOList.size();
                for (int cnt = 0; cnt < listSize; cnt++)
                {
                    CommonNotesVO commonNotesVO = (CommonNotesVO) notesVOList
                            .get(cnt);

                    noteSetObj
                            .addNote(convertCommonNoteVOToDomain(commonNotesVO));
                }
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
                    noteSetObj.setAddedAuditUserID(getUserID());
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
                    noteSetObj.setAuditUserID(getUserID());
                }
                else
                {
                    noteSetObj.setAuditUserID(noteSetVOObj.getAuditUserID());
                }


                //noteSetObj.setAuditTimeStamp(new Timestamp(0));
                //noteSetObj.setAuditUserID(getUserID());
                noteSetObj.setVersionNo(noteSetVOObj.getVersionNo());
            }

        }
        return noteSetObj;
    }

    /**
     * This is convert VO to DO.
     * 
     * @param commonNotesVO
     *            This object holds notes information.
     * @return Note returns Note.
     */

    public Note convertCommonNoteVOToDomain(CommonNotesVO commonNotesVO)
    {
        Note notes = new Note();
        NoteCex noteCexObj = new NoteCex();
        if (commonNotesVO != null)
        {

            if (commonNotesVO.getAddedAuditTimeStamp() == null)
            {
                notes.setAddedAuditTimeStamp(new Date());
            }
            else
            {
                notes.setAddedAuditTimeStamp(commonNotesVO
                        .getAddedAuditTimeStamp());
            }

            if (commonNotesVO.getAddedAuditUserID() == null
                    || commonNotesVO.getAddedAuditUserID().trim().length() == 0)
            {
                notes.setAddedAuditUserID(getUserID());
            }
            else
            {
                notes.setAddedAuditUserID(commonNotesVO.getAddedAuditUserID());
            }
            
           if (commonNotesVO.getAuditTimeStamp() == null)
            {
                notes.setAuditTimeStamp(new Date());
            }
            else
            {
                notes.setAuditTimeStamp(commonNotesVO.getAuditTimeStamp());
            }
            if (commonNotesVO.getAuditUserID() == null
                    || commonNotesVO.getAuditUserID().trim().length() == 0)
            {
                notes.setAuditUserID(getUserID());
            }
            else
            {
                notes.setAuditUserID(commonNotesVO.getAuditUserID());
            }


           /* notes.setAuditTimeStamp(new Timestamp(0));
            notes.setAuditUserID(getUserID());*/
            notes.setNoteSequenceNumber(commonNotesVO.getNoteSequenceNumber());
            notes.setNoteTypeCode(commonNotesVO.getUsageTypeCode());
            notes.setVersionNo(commonNotesVO.getVersionNo());
            CommonEntityType ceType = null;

            logger.debug("CommonEntityTypeCode in convertCommonNoteVOToDomain--> "+commonNotesVO
                    .getCommonEntityTypeCode());
            if (commonNotesVO.getCommonEntityTypeCode() != null
                    && commonNotesVO.getCommonEntityTypeCode().trim().length() > 0)
            {
                ceType = new CommonEntityType();
                ceType.setCommonEntityTypeCode(commonNotesVO
                        .getCommonEntityTypeCode());
                logger.debug("!!CommonEntityTypeCode in convertCommonNoteVOToDomain--> "+commonNotesVO
                        .getCommonEntityTypeCode());
                notes.setCommonEntityTypeCode(ceType.getCommonEntityTypeCode());
            }
            /*
             * Change done for ES2 DO changes
             */
            
            if(commonNotesVO.getNoteSequenceNumber() != null  && commonNotesVO.getNotesCexAuditUserID() != null)
            {
            	noteCexObj.setNoteTextValue(commonNotesVO.getNoteTextValue());
            	noteCexObj.setVersionNo(commonNotesVO.getNoteCexVersionNum());
            	noteCexObj.setAuditTimeStamp(commonNotesVO.getNotesCexAuditTimeStamp());
            	noteCexObj.setAddedAuditTimeStamp(commonNotesVO.getNotesCexAddedAuditTimeStamp());
            	noteCexObj.setAuditUserID(commonNotesVO.getNotesCexAuditUserID());
            	noteCexObj.setAddedAuditUserID(commonNotesVO.getNotesCexAddedAuditUserID());
            	// Code added for defect - ESPRD00877928 to remove unwanted spaces.
            	noteCexObj.setDescriptionClob(commonNotesVO.getNoteText().trim());
        	 notes.setCex(noteCexObj);
            }

            
            if(notes.getCex() == null) {
                NoteCex noteCex = new NoteCex();
               noteCex.setAuditTimeStamp(new Timestamp(0));
               /*noteCex.setAuditUserID("TestUser");*/
               noteCex.setAuditUserID(getUserID());

            if (notes.getAddedAuditTimeStamp() == null)
            {
                noteCex.setAddedAuditTimeStamp(new Date());
            }
            else
            {
                noteCex.setAddedAuditTimeStamp(notes
                        .getAddedAuditTimeStamp());
            }
            
            if (notes.getAuditTimeStamp() == null)
            {
                noteCex.setAuditTimeStamp(new Date());
            }
            else
            {
                noteCex.setAuditTimeStamp(notes.getAuditTimeStamp());
            }


            if (notes.getAddedAuditUserID() == null
                    || notes.getAddedAuditUserID().trim().length() == 0)
            {
                noteCex.setAddedAuditUserID(getUserID());
            }
            else
            {
                noteCex.setAddedAuditUserID(notes.getAddedAuditUserID());
            }
               
            	notes.setCex(noteCex);
            }
            /*notes.getCex().setDescriptionClob(commonNotesVO.getNoteText());*/
            
            if(commonNotesVO.getNoteText() != null) 
			{
            notes.getCex().setDescriptionClob(commonNotesVO.getNoteText());
			}


        }

        return notes;
    }

    /**
     * This method converts the domain object to value object. It takes the
     * instances of Object to *be converted as input and returns the
     * corresponding value object with values.
     * 
     * @param noteSetObj
     *            The inputObj holds NoteSet.
     * @return NoteSetVO
     */
    public NoteSetVO convertNoteSetDomainToVO(NoteSet noteSetObj)
    {
      NoteSetVO noteSetVO = new NoteSetVO();
        if (noteSetObj != null)
        {

            commonEntityDataBean = getCommonEntityDataBean();
            if (commonEntityDataBean.getNoteTypeList() == null
                    || commonEntityDataBean.getNoteTypeList().size() == 0)
            {
                loadValidValuesForNotes();
            }
            noteSetVO.setNoteSetSK(noteSetObj.getNoteSetSK());
            noteSetVO.setAddedAuditTimeStamp(noteSetObj
                    .getAddedAuditTimeStamp());
            noteSetVO.setAddedAuditUserID(noteSetObj.getAddedAuditUserID());
            noteSetVO.setVersionNo(noteSetObj.getVersionNo());
            
            noteSetVO.setAuditTimeStamp(noteSetObj.getAuditTimeStamp());
            noteSetVO.setAuditUserID(noteSetObj.getAuditUserID());
            
            ArrayList notesVOList = null;
                        
            Set notesSet = noteSetObj.getNotes();
            if (notesSet != null && !(notesSet.isEmpty()))
            {
                notesVOList = new ArrayList();
                Iterator notesItr = notesSet.iterator();
                while (notesItr.hasNext())
                {
                    Note note = (Note) notesItr.next();
                    notesVOList.add(convertNoteDomainToVO(note, noteSetObj));
                }
                noteSetVO.setNotesList(notesVOList);
            }
        }
        return noteSetVO;
    }

    /**
     * This is convert DO to VO.
     * 
     * @param noteObj
     *            This object holds notes information.
     * @return CommonNotesVO returns CommonNotesVO.
     */
    public CommonNotesVO convertNoteDomainToVO(Note noteObj, NoteSet noteSetObj)
    {
        CommonNotesVO commonNotesVO = new CommonNotesVO();
        ContactManagementHelper helper = new  ContactManagementHelper();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                ProgramConstants.DATE_TIME_FORMAT, Locale.getDefault());

        if (noteObj != null)
        {

            commonNotesVO.setAddedAuditTimeStamp(noteObj
                    .getAddedAuditTimeStamp());
            commonNotesVO.setAddedAuditUserID(noteObj.getAddedAuditUserID());
            
            commonNotesVO.setAuditUserID(noteObj.getAuditUserID());
            
            commonNotesVO.setAuditTimeStamp(noteObj
                    .getAuditTimeStamp());

            //commonNotesVO.setCommonEntityTypeCode(noteObj.getCommonEntityType());
            commonNotesVO.setFilterbeginDate(noteObj.getAddedAuditTimeStamp());
            commonNotesVO.setStrBeginDate(dateFormat.format(noteObj
                    .getAddedAuditTimeStamp()));
            commonNotesVO
                    .setNoteSequenceNumber(noteObj.getNoteSequenceNumber());
            //Code added for the defect - ESPRD00877928 to remove unwanted spaces.
            if(noteObj.getCex() != null)
            {
            	commonNotesVO.setNoteText(noteObj.getCex().getDescriptionClob().trim());
            	commonNotesVO.setNoteTextValue(noteObj.getCex().getNoteTextValue());
                commonNotesVO.setNotesCexAuditTimeStamp(noteObj.getCex().getAuditTimeStamp());
                commonNotesVO.setNotesCexAddedAuditTimeStamp(noteObj.getCex().getAddedAuditTimeStamp());
                commonNotesVO.setNotesCexAuditUserID(noteObj.getCex().getAuditUserID());
                commonNotesVO.setNotesCexAddedAuditUserID(noteObj.getCex().getAddedAuditUserID());
                commonNotesVO.setNoteCexVersionNum(noteObj.getCex().getVersionNo());
            }
            String usageTypeCode = noteObj.getNoteTypeCode();
            commonNotesVO.setUsageTypeDesc(getDescriptionFromVV(usageTypeCode,
                    commonEntityDataBean.getNoteTypeList()));
            if (noteObj.getNoteSet() != null)
            {
                commonNotesVO.setNoteSetSK(noteObj.getNoteSet().getNoteSetSK());
            }

            String shortNotes = (noteObj.getCex() == null)? null : noteObj.getCex().getDescriptionClob();
            if (shortNotes != null
                    && shortNotes.trim().length() > ProgramConstants.INT_HUNDRED)
            {
                shortNotes = shortNotes.substring(0,
                		ProgramConstants.INT_HUNDRED);
            }
            commonNotesVO.setShortNotes(shortNotes);
            commonNotesVO.setUsageTypeCode(noteObj.getNoteTypeCode());
            commonNotesVO.setUserId(noteObj.getAddedAuditUserID());
            String userId=noteObj.getAddedAuditUserID();
            String userName=getUserNameByID(userId);
            String userIdName=null;
            if(userName!=null && userName.length()!=0){
             //Below code is modified for defect ESPRD00944125
            	userIdName=userName;
            }else{
            	userIdName=userId;
            }
            commonNotesVO.setUserIdName(userIdName);
            commonNotesVO.setCommonEntityTypeCode(noteObj.getCommonEntityTypeCode());
            commonNotesVO.setVersionNo(noteObj.getVersionNo() );
            /*if(noteSetObj.getNoteSourceName()!=null 
            		&& noteSetObj.getNoteSourceName().trim().length() > 0)
            {
            commonNotesVO.setTableName(noteSetObj.getNoteSourceName());
            }*/
          
        }
        return commonNotesVO;
    }

    //method used to append a space to string
    /*private String appendSpace(String values) {
		char[] valuesArray = values.toCharArray();
		StringBuffer sb = new StringBuffer();
		String finalString = null;
		int j = 120;
		if (values != null) {
			for (int i = 0; i < values.length(); i++) {
				if (i == j) {
					sb.append(" ");
					j = j + 120;
				}
				sb.append(valuesArray[i]);
			}
			finalString = sb.toString();
		}
		return finalString;
	}*/
    
    /**
     * To getEAddressFromEAddressVO.
     * 
     * @param eaddressVO
     *            This object holds eaddressVO.
     * @return EAddress returns EAddress.
     */

    public EAddress getEAddressFromEAddressVO(EAddressVO eaddressVO)
    {
        EAddress eaddress = null;
        eaddress = new EAddress();
        boolean boucedAddressStr = eaddressVO.isBouncedAddress();
        /*
         * if (ProgramConstants.YES.equals(boucedAddressStr)) { bouncedAddress =
         * true; }
         */

        eaddress.setBounceAddressIndicator(Boolean.valueOf(boucedAddressStr));
        eaddress.seteAddressText(eaddressVO.getEaddress());

        CommonEntityType cType = new CommonEntityType();
        cType.setCommonEntityTypeCode(ProgramConstants.DEFAULT_ENT_TYP_CODE);
        eaddress.setCommonEntityType(cType);
        eaddress.setCommonEntityTypeCode(cType.getCommonEntityTypeCode());
        if (isEAddressTypeCodeChanged(eaddressVO))
        {
            eaddress.seteAddressSK(eaddressVO.getEAddressSK());
            eaddress.setVersionNo(eaddressVO.getVersionNo());
        }

        eaddress.setAddedAuditTimeStamp(new Timestamp(0));
        eaddress.setAddedAuditUserID(getUserID());
        eaddress.setAuditTimeStamp(new Timestamp(0));
        eaddress.setAuditUserID(getUserID());
        eaddress.setVersionNo(eaddressVO.getVersionNo());
        return eaddress;
    }

    /**
     * To getEAddressUsageFromEAddressVO.
     * 
     * @param eaddressVO
     *            This object holds eaddressVO.
     * @return EAddressUsage returns EAddress.
     */

    private EAddressUsage getEAddressUsageFromEAddressVO(EAddressVO eaddressVO)
    {
        EAddressUsage eaddressUsage = null;
        /**
         * commented as part of ES2
         */
        // boolean voidIndicator = false;
        eaddressUsage = new EAddressUsage();
        /*
         * String voidIndicatorStr = eaddressVO.getVoidIndicator(); if
         * (ProgramConstants.YES.equals(voidIndicatorStr)) { voidIndicator =
         * true; }
         */
        eaddressUsage.setBeginDate(ContactHelper.dateConverter(eaddressVO
                .getBeginDate()));
        eaddressUsage.seteAddressSK(eaddressVO.getEAddressUsageSK());
        eaddressUsage.seteAddressUsageSigCode(eaddressVO.getSignificance());
        eaddressUsage.seteAddressUsageTypeCode(eaddressVO.getUsageTypeCode());
        eaddressUsage.setEndDate(ContactHelper.dateConverter(eaddressVO
                .getEndDate()));
        eaddressUsage.setEaddressUsageStatusCode(eaddressVO.getStatus());

        /*
         * Change done for ES2 DO changes
         */
        //eaddressUsage.setVoidIndicator(new Boolean(voidIndicator));
        eaddressUsage.seteAddress(getEAddressFromEAddressVO(eaddressVO));

        eaddressUsage.setAddedAuditTimeStamp(new Timestamp(0));
        eaddressUsage.setAddedAuditUserID(getUserID());
        eaddressUsage.setAuditTimeStamp(new Timestamp(0));
        eaddressUsage.setAuditUserID(getUserID());
        if (isEAddressTypeCodeChanged(eaddressVO))
        {
            eaddressUsage.setVersionNo(eaddressVO.getEAddressUsageVersionNo());
        }
        eaddressUsage.setEaddressUsageSequenceNumber(eaddressVO
                .getEAddressUsageSequenceNumber());
        return eaddressUsage;
    }

    /**
     * To updateEAddressVOWithEAddress.
     * 
     * @param eAddress
     *            This object holds eaddress.
     * @param eaddressVO
     *            This object holds eaddressVO.
     * @return EAddressVO returns
     */

    private EAddressVO updateEAddressVOWithEAddress(EAddress eAddress,
            EAddressVO eaddressVO)
    {

        boolean boolBouncedAddress = false;
        Boolean bouncedAddress = null;
        // String bouncedAddressStr = null;
        bouncedAddress = eAddress.getBounceAddressIndicator();
        if (bouncedAddress != null)
        {
            boolBouncedAddress = bouncedAddress.booleanValue();
        }
        /*
         * if (boolBouncedAddress) { bouncedAddressStr = ProgramConstants.YES; }
         * else { bouncedAddressStr = ProgramConstants.NO; }
         */
        
        if(boolBouncedAddress){
        	eaddressVO.setBouncedAddressStr(ProgramConstants.YES);
        }else{
        	eaddressVO.setBouncedAddressStr(ProgramConstants.NO);
        }

        eaddressVO.setBouncedAddress(boolBouncedAddress);
        eaddressVO.setEaddress(eAddress.geteAddressText());
        eaddressVO.setCommonEntityType(eAddress.getCommonEntityType());
        eaddressVO.setEAddressSK(eAddress.geteAddressSK());
        eaddressVO.setVersionNo(eAddress.getVersionNo());

        return eaddressVO;
    }

    /**
     * To createEAddressVOWithEAddressUsage.
     * 
     * @param eAddressUsage
     *            This object holds EaddressUsage.
     * @return EAddressVO
     */

    private EAddressVO createEAddressVOWithEAddressUsage(
            EAddressUsage eAddressUsage,boolean controlRequired)
    {
        EAddressVO eaddressVO = null;
        boolean boolVoidIndicator = false;
        Boolean voidIndicator = null;
        String voidIndicatorStr = null;
        eaddressVO = new EAddressVO();
        /*
         * Change done for ES2 DO changes
         */
        //voidIndicator = eAddressUsage.getVoidIndicator();
        if (voidIndicator != null)
        {
            boolVoidIndicator = voidIndicator.booleanValue();
        }
        if (boolVoidIndicator)
        {
            voidIndicatorStr = ProgramConstants.YES;
        }
        else
        {
            voidIndicatorStr = ProgramConstants.NO;
        }
        eaddressVO.setBeginDate(ContactHelper.dateConverter(eAddressUsage
                .getBeginDate()));
        eaddressVO.setEndDate(ContactHelper.dateConverter(eAddressUsage
                .getEndDate()));
        eaddressVO.setSignificance(eAddressUsage.geteAddressUsageSigCode());
        eaddressVO.setVoidIndicator(voidIndicatorStr);
        eaddressVO.setUsageTypeCode(eAddressUsage.geteAddressUsageTypeCode());
        eaddressVO.setExistingUsageTypeCode(eAddressUsage
                .geteAddressUsageTypeCode());
        eaddressVO = updateEAddressVOWithEAddress(eAddressUsage.geteAddress(),
                eaddressVO);
        eaddressVO.setBeginDateStr(getDateInMMDDYYYYFormat(eaddressVO
                .getBeginDate()));
        eaddressVO.setEndDateStr(getDateInMMDDYYYYFormat(eaddressVO
                .getEndDate()));

        eaddressVO.setAddedAuditTimeStamp(eAddressUsage.geteAddress()
                .getAddedAuditTimeStamp());
        eaddressVO.setAddedAuditUserID(eAddressUsage.geteAddress()
                .getAddedAuditUserID());
        eaddressVO.setAuditTimeStamp(eAddressUsage.geteAddress()
                .getAuditTimeStamp());
        eaddressVO.setAuditUserID(eAddressUsage.geteAddress().getAuditUserID());
        eaddressVO.setEAddressUsageSK(eAddressUsage.getEAddressSK());
        eaddressVO.setEAddressUsageSequenceNumber(eAddressUsage
                .getEaddressUsageSequenceNumber());
        eaddressVO.setEAddressUsageVersionNo(eAddressUsage.getVersionNo());
        eaddressVO.setStatus(eAddressUsage.getEaddressUsageStatusCode());       
        EAddressControllerBean eAddressControllerBean=new EAddressControllerBean();
        eAddressControllerBean.createValidValues(); 
        
        eaddressVO.setStatusStr(getDescriptionFromVV(eAddressUsage
                .getEaddressUsageStatusCode(), getCommonEntityDataBean()
                .getStatusEaddList()));       
        eaddressVO.setUsageTypeCodeStr(getDescriptionFromVV(eAddressUsage.geteAddressUsageTypeCode(),getCommonEntityDataBean().
                getUsageList() ));
        // EAddress AuditKey List
        if(controlRequired){
                 createVOAuditKeysList(eAddressUsage,eaddressVO);
                 createVOAuditKeysList(eAddressUsage.geteAddress(),eaddressVO);
        }

        return eaddressVO;
    }

    /**
     * To get Phone From PhoneVO.
     * 
     * @param phoneVO
     *            This obejcts holds phone info.
     * @return Phone
     */

    public Phone getPhoneFromPhoneVO(PhoneVO phoneVO)
    {
        phoneVO.getBeginDate();
        phoneVO.getEndDate();
        phoneVO.getEntityType();
        phoneVO.getEntityTypeCodeList();
        phoneVO.getExtension();
        phoneVO.getInternationalPhoneNo();
        phoneVO.getOutOfService();
        phoneVO.getPhoneNumber();
        phoneVO.getSignificance();
        phoneVO.getUsageTypeCode();
        /*
         * Change done for ES2 DO changes
         */
        //phoneVO.getVoidIndicator();
        Phone phone = null;
        boolean boolOOSIndicator = false;
        boolean oOSIndicatorStr = false;
        phone = new Phone();
        oOSIndicatorStr = phoneVO.getOutOfService();
        boolOOSIndicator = oOSIndicatorStr;
        /**
         * Commented as part of ES2
         */
        /*
         * if (ProgramConstants.YES.equals(oOSIndicatorStr)) { boolOOSIndicator =
         * true; }
         */
        phone.setCountryCode(phoneVO.getCountryCode());
        phone.setInternationalPhoneNumber(phoneVO.getInternationalPhoneNo());
        phone.setOutOfServiceIndicator(Boolean.valueOf(boolOOSIndicator));
        phone.setPhoneExtension(phoneVO.getExtension());
        /* Fixed for defect id ESPRD00438005 checking for null condition */
        if(phoneVO.getPhoneNumber() != null){
        phone.setPhoneNumber(phoneVO.getPhoneNumber().replaceAll("-", ""));
        }
        /* Fixed for defect id ESPRD00438005 end */
        phone.setCommonEntityTypeCode(phoneVO.getCommonEntityTypeCode());
        if (isPhoneTypeCodeChanged(phoneVO))
        {
            phone.setPhoneSK(phoneVO.getPhoneSK());
            phone.setVersionNo(phoneVO.getVersionNo());
        }
        phone.setAddedAuditTimeStamp(new Timestamp(0));
        phone.setAddedAuditUserID(getUserID());
        phone.setAuditTimeStamp(new Timestamp(0));
        phone.setAuditUserID(getUserID());

        return phone;
    }

    /**
     * To get PhoneUsage from PhoneVO.
     * 
     * @param phoneVO
     *            This object holds phone information.
     * @return PhoneUsage
     */

    private PhoneUsage getPhoneUsageFromPhoneVO(PhoneVO phoneVO)
    {
        PhoneUsage phoneUsage = null;
        phoneUsage = new PhoneUsage();
        /*
         * Change done for ES2 DO changes
         */
        /*
         * String voidIndicatorStr = phoneVO.getVoidIndicator(); if
         * (ProgramConstants.YES.equals(voidIndicatorStr)) { voidIndicator =
         * true; }
         */

        phoneUsage.setBeginDate(ContactHelper.dateConverter(phoneVO
                .getEndDate()));
        phoneUsage
                .setEndDate(ContactHelper.dateConverter(phoneVO.getEndDate()));
        phoneUsage.setPhoneUsageTypeCode(phoneVO.getUsageTypeCode());
        phoneUsage.setPhoneUsageTypeSigCode(phoneVO.getSignificance());
        /**
         * Below code commented as part of ES2 change
         */
        // phoneUsage.setVoidIndicator(new Boolean(voidIndicator));
        phoneUsage.setBeginDate(ContactHelper.dateConverter(phoneVO
                .getBeginDate()));
        //Added as part of ES2 change
        phoneUsage.setPhoneUsageStatCode(phoneVO.getStatus());
        phoneUsage.setPhone(getPhoneFromPhoneVO(phoneVO));

        phoneUsage.setAddedAuditTimeStamp(new Timestamp(
                ContactManagementConstants.TIMESTMP));
        phoneUsage.setAddedAuditUserID(getUserID());
        phoneUsage.setAuditTimeStamp(new Timestamp(
        		ContactManagementConstants.TIMESTMP));
        phoneUsage.setAuditUserID(getUserID());
        if (isPhoneTypeCodeChanged(phoneVO))
        {
            phoneUsage.setVersionNo(phoneVO.getPhoneUsageVersionNo());
        }

        phoneUsage.setPhoneSK(phoneVO.getPhoneUsageSK());
        phoneUsage.setPhoneUsageSequenceNumber(phoneVO
                .getPhoneUsageSequenceNumber());
        return phoneUsage;
    }

    /**
     * This is to update updatePhoneVOWithPhone.
     * 
     * @param phone
     *            This object holds phone information.
     * @param phoneVO
     *            This object holds phone information.
     * @return PhoneVO
     */

    private PhoneVO updatePhoneVOWithPhone(Phone phone, PhoneVO phoneVO)
    {
        boolean boolOOSIndicator = false;
        Boolean oOSIndicator = null;
        phone.getAddedAuditTimeStamp();
        phone.getAddedAuditUserID();
        phone.getAuditTimeStamp();
        phone.getAuditUserID();
        phone.getCommonEntityTypeCode();
        phone.getCountryCode();
        phone.getInternationalPhoneNumber();
        phone.getOutOfServiceIndicator();
        phone.getPhoneExtension();
        phone.getPhoneNumber();
        phone.getPhoneSK();
        phone.getVersionNo();
        oOSIndicator = phone.getOutOfServiceIndicator();
        if (oOSIndicator != null)
        {
            boolOOSIndicator = oOSIndicator.booleanValue();
            if ((boolOOSIndicator)) {
                phoneVO.setOutOfServicestr(ProgramConstants.YES);
            } else {
                phoneVO.setOutOfServicestr(ProgramConstants.NO);
            }
        }
        //Commented as part of ES2 change
        /*
         * if (boolOOSIndicator) { oOSIndicatorStr =
         * BenefitPlanConstants.BOOL_TRUE; } else { oOSIndicatorStr =
         * BenefitPlanConstants.BOOL_FALSE; }
         */
        phoneVO.setExtension(phone.getPhoneExtension());
        phoneVO.setInternationalPhoneNo(phone.getInternationalPhoneNumber());
        phoneVO.setOutOfService(boolOOSIndicator);
        if (phone.getPhoneNumber() != null
				&& !(phone.getPhoneNumber() == ProgramConstants.EMPTY_STRING)) {
			String phoneDB = phone.getPhoneNumber();
			char phoneDBArr[] = phoneDB.toCharArray();
			char phoneUIArr[] = new char[(phoneDBArr.length + 2)];

			for (int i = 0, j = 0; i < phoneDBArr.length; i++, j++) {
				if (i == 3 || i == 6) {
					phoneUIArr[j] = '-';
					j++;
					phoneUIArr[j] = phoneDBArr[i];
				} else
					phoneUIArr[j] = phoneDBArr[i];
			}

			phoneVO.setPhoneNumber(new String(phoneUIArr));
        }
        
        phoneVO.setPhoneSK(phone.getPhoneSK());
        /*
         * Change done for ES2 DO changes
         */
        phoneVO.setCommonEntityTypeCode(phone.getCommonEntityTypeCode());
        phoneVO.setCountryCode(phone.getCountryCode());
        phoneVO.setVersionNo(phone.getVersionNo());
        return phoneVO;
    }

    /**
     * This to convert VO to DO.
     * 
     * @param phoneUsage
     *            This object holds phone usage information.
     * @return PhoneVO
     */

    private PhoneVO createPhoneVOWithPhoneUsage(PhoneUsage phoneUsage,boolean controlRequired)
    {
    	logger.debug("inside createPhoneVOWithPhoneUsage");
    	
    	PhoneVO phoneVO = null;
        phoneVO = new PhoneVO();

        phoneUsage.getCommonEntity();
        phoneUsage.getVersionNo();
        phoneVO.setBeginDate(ContactHelper.dateConverter(phoneUsage
                .getBeginDate()));
        phoneVO
                .setEndDate(ContactHelper
                        .dateConverter(phoneUsage.getEndDate()));

        phoneVO.setSignificance(phoneUsage.getPhoneUsageTypeSigCode());
        phoneVO.setUsageTypeCode(phoneUsage.getPhoneUsageTypeCode());
        phoneVO.setExistingUsageTypeCode(phoneUsage.getPhoneUsageTypeCode());
        phoneVO.setStatus(phoneUsage.getPhoneUsageStatCode());
        PhoneControllerBean phoneControllerBean = new PhoneControllerBean();
       
        phoneControllerBean.createValidValues();
               
        phoneVO.setUsageTypeDesc((getDescriptionFromVV(phoneVO.getUsageTypeCode(),
        		getCommonEntityDataBean().getCommonEntityVO().getPhoneVO().getUsageList())));
        phoneVO.setStatusstr((getDescriptionFromVV(phoneVO.getStatus(),
        		getCommonEntityDataBean().getCommonEntityVO().getPhoneVO()
                        .getStatusList())));
        
        /*
         * Change done for ES2 DO changes
         */
        //phoneVO.setVoidIndicator(voidIndicatorStr);
        phoneVO.setPhoneUsageSK(phoneUsage.getPhoneSK());
        phoneVO = updatePhoneVOWithPhone(phoneUsage.getPhone(), phoneVO);
        phoneVO
                .setBeginDateStr(getDateInMMDDYYYYFormat(phoneVO.getBeginDate()));
        phoneVO.setEndDateStr(getDateInMMDDYYYYFormat(phoneVO.getEndDate()));

        phoneVO.setAddedAuditTimeStamp(phoneUsage.getPhone()
                .getAddedAuditTimeStamp());
        phoneVO
                .setAddedAuditUserID(phoneUsage.getPhone()
                        .getAddedAuditUserID());
        phoneVO.setAuditTimeStamp(phoneUsage.getPhone().getAuditTimeStamp());
        phoneVO.setAuditUserID(phoneUsage.getPhone().getAuditUserID());
        phoneVO.setPhoneUsageVersionNo(phoneUsage.getVersionNo());
        phoneVO.setPhoneUsageSequenceNumber(phoneUsage
                .getPhoneUsageSequenceNumber());
        if(controlRequired){
        createVOAuditKeysList(phoneUsage.getPhone(),phoneVO);
        createVOAuditKeysList(phoneUsage,phoneVO);
        createVOAuditKeysList(phoneUsage.getCommonEntity(),phoneVO);
        }

        return phoneVO;
    }

    /**
     * To get the dateformat.
     * 
     * @param date
     *            This object holds date.
     * @return String
     */
    public static String getDateInMMDDYYYYFormat(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat(
                ProgramConstants.DATE_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * To get the dateformat.
     * 
     * @param date
     *            This object holds date.
     * @return String
     */
    public static String getDateInMMDDYYYYFormat(String date)
    {
        DateFormat dateFormat = new SimpleDateFormat(
                ProgramConstants.DATE_FORMAT, Locale.getDefault());
        return dateFormat.format(dateConverter(date));
    }

    /**
     * To get the commonentityDatatBean.
     * 
     * @return CommonEntityDataBean.
     */

    public static CommonEntityDataBean getCommonEntityDataBean()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        CommonEntityDataBean commonEntityDataBean = (CommonEntityDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + ProgramConstants.COMMONENTITYDATABEAN + "}")
                .getValue(fc);
        return commonEntityDataBean;
    }

    /**
     * loading valid values for Notes.
     */

    private void loadValidValuesForNotes()
    {

        InputCriteria inputCriteria = null;
        List list = null;
        //"FindBugs" issue resolved
        //HashMap map = new HashMap();
        HashMap map = null;
        ReferenceDataSearchVO referenceDataSearch = null;
        ReferenceServiceDelegate referenceServiceDelegate = null;
        ReferenceDataListVO referenceDataListVO = null;

        //filling the drop down of claim type code
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_NOTE_TY_CD);
        list = new ArrayList();
        list.add(inputCriteria);
        referenceDataSearch = new ReferenceDataSearchVO();
        referenceDataSearch.setInputList(list);
        referenceServiceDelegate = new ReferenceServiceDelegate();

        //		                 Pass the list to the delegate
        logger.debug("bfor callling delegate");
        referenceDataListVO = new ReferenceDataListVO();
        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);
        }
        catch (ReferenceServiceRequestException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (SystemListNotFoundException e)
        {
            logger.error(e.getMessage(), e);
        }

        //for displaying retrieved values
        map = referenceDataListVO.getResponseMap();
        list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
                + ReferenceServiceDataConstants.G_NOTE_TY_CD);
        int noteSize = list.size();

        for (int i = 0; i < noteSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            commonEntityDataBean.getNoteTypeList().add(
                    new SelectItem(refVo.getValidValueCode(), refVo
                            .getValidValueCode()
                            + "-" + refVo.getShortDescription()));

        }

    }

    /**
     * Loading valid values for contact.
     */
    private void loadValidValuesForContact()
    {
        logger.info("loadValidValuesForContact");

        InputCriteria inputCriteria = null;
        List list = null;
        /*Storing valid values to separate lists and setting them to bean*/
		ArrayList contactSigList = new ArrayList();
		ArrayList genderList = new ArrayList();
		ArrayList statusList = new ArrayList();
		ArrayList suffixList = new ArrayList();
		ArrayList prefixList = new ArrayList();
		ArrayList entityList = new ArrayList();
		ArrayList contactTypeList = new ArrayList();
		
        //"FindBugs" issue resolved
		//HashMap map = new HashMap();
		HashMap map = null;
        String select = "Please Select One";
		String selectIndex = "";
        ReferenceDataSearchVO referenceDataSearch = null;
        ReferenceServiceDelegate referenceServiceDelegate = null;
        ReferenceDataListVO referenceDataListVO = null;

        //filling the drop down of claim type code
        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CMN_ENTY_TY_CD);
        list = new ArrayList();
        list.add(inputCriteria);

        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_REP_XREF_TY_CD);
        list.add(inputCriteria);

        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_CMN_ENTY_REP_SIG_CD);
        list.add(inputCriteria);

        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria.setElementName(ReferenceServiceDataConstants.G_GENDER_CD);
        list.add(inputCriteria);

        inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteria
                .setElementName(ReferenceServiceDataConstants.G_NAM_PREFX_CD);
        list.add(inputCriteria);
        
        inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_ADR_USG_STAT_CD);
		list.add(inputCriteria);
		

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.SUFFIX_NAME);
		list.add(inputCriteria);
			

        referenceDataSearch = new ReferenceDataSearchVO();
        referenceDataSearch.setInputList(list);
        referenceServiceDelegate = new ReferenceServiceDelegate();

        //		                 Pass the list to the delegate
       
        referenceDataListVO = new ReferenceDataListVO();
        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);
        }
        catch (ReferenceServiceRequestException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (SystemListNotFoundException e)
        {
            logger.error(e.getMessage(), e);
        }

        

        map = referenceDataListVO.getResponseMap();
        
       /*fetching ENTITY TYPE*/
        list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
                + ReferenceServiceDataConstants.G_CMN_ENTY_TY_CD);
        int cmnSize = list.size();

        for (int i = 0; i < cmnSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            /*commonEntityDataBean.getEntityTypeList().add(
                    new SelectItem(refVo.getValidValueCode(), refVo
                            .getValidValueCode()
                            + "-" + refVo.getShortDescription()));*/
            entityList.add(	new SelectItem(refVo.getValidValueCode(), refVo
					.getValidValueCode()
					+ "-" + refVo.getShortDescription()));

        }

        /*fetching CONTACT TYPE*/
        list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
                + ReferenceServiceDataConstants.G_REP_XREF_TY_CD);
        int repSize = list.size();
        /*commonEntityDataBean.getContactTypeList().add(
				new SelectItem(selectIndex, select));*/
        contactTypeList.add(new SelectItem(selectIndex, select));
        for (int i = 0; i < repSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            /*commonEntityDataBean.getContactTypeList().add(
                    new SelectItem(refVo.getValidValueCode(), refVo
                            .getValidValueCode()
                            + "-" + refVo.getShortDescription()));*/
            contactTypeList.add(new SelectItem(refVo.getValidValueCode(), refVo
					.getValidValueCode()
					+ "-" + refVo.getShortDescription()));

        }

        /*Fetching Contact Significance List*/
        list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
                + ReferenceServiceDataConstants.G_CMN_ENTY_REP_SIG_CD);
        int sigSize = list.size();
        /*commonEntityDataBean.getContactSigList().add(
				new SelectItem(selectIndex, select));*/
        contactSigList.add(new SelectItem(selectIndex, select));
        for (int i = 0; i < sigSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            /*commonEntityDataBean.getContactSigList().add(
                    new SelectItem(refVo.getValidValueCode(), refVo
                            .getValidValueCode()
                            + "-" + refVo.getShortDescription()));*/
            contactSigList.add(new SelectItem(refVo.getValidValueCode(), refVo
					.getValidValueCode()
					+ "-" + refVo.getShortDescription()));
        }
        /*Fetching Gender */
        list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
                + ReferenceServiceDataConstants.G_GENDER_CD);
        int genderSize = list.size();
        /*commonEntityDataBean.getGenderList().add(
				new SelectItem(selectIndex, select));*/
        genderList.add(new SelectItem(selectIndex, select));
        for (int i = 0; i < genderSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
           /* commonEntityDataBean.getGenderList().add(
                    new SelectItem(refVo.getValidValueCode(), refVo
                            .getValidValueCode()
                            + "-" + refVo.getShortDescription()));*/
            genderList.add(
					new SelectItem(refVo.getValidValueCode(), refVo
							.getValidValueCode()
							+ "-" + refVo.getShortDescription()));
        }

        /*Fetching Preffix Code*/
        list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
                + ReferenceServiceDataConstants.G_NAM_PREFX_CD);
        int namSize = list.size();
        /*commonEntityDataBean.getNamePrefixList().add(
				new SelectItem(selectIndex, select));*/
        prefixList.add(new SelectItem(selectIndex, select));
        for (int i = 0; i < namSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            String refCode = refVo.getValidValueCode();
            //prefix value after major save
            /*if (refCode != null && refCode.trim().length() > 2)
            {
                refCode = refCode.substring(0, 1);
            }*/
            /*commonEntityDataBean.getNamePrefixList().add(
                    new SelectItem(refCode, refVo.getShortDescription()));*/
            prefixList.add(new SelectItem(refCode, refVo.getShortDescription()));
        }
        
        /*Fetching Status Code*/
        list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
                + ReferenceServiceDataConstants.G_ADR_USG_STAT_CD);
        int statusSize = list.size();
        /*commonEntityDataBean.getStatusList().add(
				new SelectItem(selectIndex, select));*/
        statusList.add(new SelectItem(selectIndex, select));
        for (int i = 0; i < statusSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            String refCode = refVo.getValidValueCode();
            String statusValuesandDesc = "";
            if (refCode != null && refCode.trim().length() > 2)
            {
                refCode = refCode.substring(0, 1);
            }
            /*commonEntityDataBean.getStatusList().add(
                    new SelectItem(refCode, refVo.getShortDescription()));*/
            statusValuesandDesc = refVo.getValidValueCode()	+ "-" + refVo.getShortDescription();
			logger.debug("statusValuesandDesc Value is-----"+statusValuesandDesc);
            statusList.add(new SelectItem(refVo.getValidValueCode(), refVo
					.getValidValueCode()
					+ "-" + refVo.getShortDescription()));

        }
        /*Fetching Suffix Name*/
        list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
                + ReferenceServiceDataConstants.SUFFIX_NAME);
        int suffixName = list.size();
        /*commonEntityDataBean.getNameSuffixList().add(
				new SelectItem(selectIndex, select));*/
        suffixList.add(new SelectItem(selectIndex, select));
        for (int i = 0; i < suffixName; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            /*commonEntityDataBean.getNameSuffixList().add(
                    new SelectItem(refVo.getValidValueCode(),refVo.getShortDescription()));*/
            //suffixList.add(new SelectItem(refVo.getValidValueCode(),refVo.getShortDescription()));
            suffixList.add(new SelectItem(refVo.getValidValueCode(),
            		refVo.getValidValueCode()+"-"+refVo.getShortDescription()));

        }
    
        /**Setting the valid values lists to commonEntityDataBean*/
		commonEntityDataBean.setEntityTypeList(entityList);
		commonEntityDataBean.setContactTypeList(getEntityTypeReferenceDataForContacttype());
		commonEntityDataBean.setContactSigList(contactSigList);
		commonEntityDataBean.setGenderList(genderList);
		commonEntityDataBean.setNamePrefixList(prefixList);
		commonEntityDataBean.setStatusList(statusList);
		commonEntityDataBean.setNameSuffixList(suffixList);
    
    
    }
    /**
     * This operation is used to get the reference data for all Contact Type.
     *
     * @return List
     */
    public List getEntityTypeReferenceDataForContacttype()
    {
        logger.info("getReferenceData");
        long entryTime = System.currentTimeMillis();
       Long userSK = getUserSK(getUserID());
        String businessUnitDesc = getBusinessUnitforUser(userSK);
        logger.debug("businessUnitDesc::::::::::::::"+businessUnitDesc);
        String select = "Please Select One";
		String selectIndex = "";

        List referenceList = new ArrayList();
        Map referenceMap = null;

        int referenceListSize = 0;
        List contactTypeList = new ArrayList();
        contactTypeList.add(new SelectItem(selectIndex, select));
        
        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        //"FindBugs" issue resolved
       // ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
        ReferenceDataListVO referenceDataListVO = null;

        InputCriteria inputCriteriaEntityTyp = new InputCriteria();
        inputCriteriaEntityTyp
                .setFunctionalArea(FunctionalAreaConstants.GENERAL);
        inputCriteriaEntityTyp.setListNumber(
                ContactManagementHelper.getSystemlistForCorrBU(
                		ContactManagementConstants.CONTACTTYPE,businessUnitDesc));
        logger.debug(" systemlistnumber:::::::"+inputCriteriaEntityTyp.getListNumber());

        referenceList.add(inputCriteriaEntityTyp);

        referenceDataSearch.setInputList(referenceList);

        try
        {
        	logger.debug(" inside Try block");
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);

            referenceMap = referenceDataListVO.getResponseMap();

            referenceList = (List) referenceMap
                    .get(FunctionalAreaConstants.GENERAL
                            + ProgramConstants.HASH_SEPARATOR
                            + String
                            .valueOf(ContactManagementHelper
                                    .getSystemlistForCorrBU(ContactManagementConstants.CONTACTTYPE,
                                    		businessUnitDesc)));
            referenceListSize = referenceList.size();
            logger.debug("referenceListSize--->" + referenceListSize);
            for (int i = 0; i < referenceListSize; i++)
            {
                ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                        .get(i);
                logger.debug((i + 1) + " vv code 1 = "
                        + refVo.getValidValueCode());
                logger.debug((i + 1) + " vv description 1= "
                        + refVo.getShortDescription());
                logger.debug((i + 1) + " lob code 1= " + refVo.getLobCode());
                String codesAndDesc = refVo.getValidValueCode() + "-"
                        + refVo.getShortDescription();
                contactTypeList.add(new SelectItem(refVo.getValidValueCode(),
                        codesAndDesc));

            }
        }
        catch (ReferenceServiceRequestException e)
        {
        	logger.debug(" inside ReferenceServiceRequestException");
        	e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        catch (SystemListNotFoundException e)
        {
        	logger.debug(" inside SystemListNotFoundException");
        	e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        long exitTime = System.currentTimeMillis();
        logger.info("CMEntityDataBean" + "#" + "getReferenceData" + "#"
                + (exitTime - entryTime));
        return contactTypeList;
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
        logger.info("getUserSK");

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        Long userSK = null;

        try
        {
            userSK = contactMaintenanceDelegate.getWorkUnitID(userId);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
        }

        return userSK;
    }
    public String getBusinessUnitforUser(Long userSK)
    {
        logger.info("setDepartmentsList");

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        String businessUnitDesc= null;
        DeptUserBasicInfo deptUserBasicInfo=new DeptUserBasicInfo();
        try
        {
          //  List deptsList = contactMaintenanceDelegate.getDepartmentUsers(userSK);
        	// Code added for CM-API
        	deptUserBasicInfo.setUserEnterpriseSK(userSK);
       	    deptUserBasicInfo.setDataReqested(ProgramConstants.DUBI_LOB_Hierarchy_SK);
         	List deptsList= contactMaintenanceDelegate.getDeptUserBasicInfo(deptUserBasicInfo).getLobSKlist();
        	
            //Added if Condition for Find_Bugs-FIX
            if(deptsList != null){
            logger.debug("Dept list size" + deptsList.size());
            }
            if (deptsList != null)
            {
                for (Iterator iter = deptsList.iterator(); iter.hasNext();)
                {
                    
                	// Code Comented for CM-API
                	/*DepartmentUser deptUser = (DepartmentUser) iter.next();
                    String lobHierarchySK = deptUser.getDepartment()
                            .getLineOfBusinessHierarchy()
                            .getLineOfBusinessHierarchySK().toString();*/
                	// Line Added for CM-API
                	Long lobSk= (Long)iter.next();
                	 
                	 String lobHierarchySK=lobSk.toString();
                    LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
                            .getDeptBusinessUnit(new Long(lobHierarchySK));
                    if (businessUnit != null)
                    {
                        logger.info("businessUnitDesc===="+businessUnitDesc);
                    	if (businessUnitDesc == null)
                        {
                            businessUnitDesc = businessUnit.getLobHierarchyDescription();
                        }
                        else if (!businessUnitDesc.equalsIgnoreCase(businessUnit.getLobHierarchyDescription()))
                        {
                            businessUnitDesc=ContactManagementConstants.AllOthers;
                            logger.info("businessUnitDesc-----"+businessUnitDesc);
                            break;
                        }
                    }
                }

            }
        }
        catch (LOBHierarchyFetchBusinessException lobExp)
        {
            logger.error(lobExp.getMessage(), lobExp);
        }


        return businessUnitDesc;
     }
    /**
     * To get the description based on code.
     * 
     * @param code
     *            Holds the code valus.
     * @param vvList
     *            Holds the List of valid values.
     * @return String
     */
    private String getDescriptionFromVV(String code, List vvList)
    {
        String desc = ProgramConstants.EMPTY_STRING;
        String valueStr = ProgramConstants.EMPTY_STRING;
        int size=0;
        //Added if Condition for Find_Bugs-FIX
        if(vvList != null){
        size = vvList.size();
        }
        if (vvList != null && size > 0)
        {
            for (int i = 0; i < size; i++)
            {
                SelectItem selectItem = (SelectItem) vvList.get(i);
                valueStr = ProgramConstants.EMPTY_STRING;
                if (selectItem != null)
                {
                    valueStr = (String) selectItem.getValue();
                    if (valueStr != null && valueStr.equals(code))
                    {
                        desc = selectItem.getLabel();
                        break;
                    }
                }
            }
        }
        return desc;
    }

    /**
     * This method is used to convert String object to Date object.
     * 
     * @param begdate
     *            This contains the begin Date.
     * @return Date
     */
    public static Date dateConverter(String begdate)
    {

        Date beginDate = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat(
                ProgramConstants.DATE_FORMAT, Locale.getDefault());
        if (begdate != null)
        {
            if (begdate.indexOf('/') != -1)
            {
                try
                {
                    beginDate = new Date(sdf1.parse(begdate).getTime());

                }
                catch (ParseException e)
                {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return beginDate;
    }

    /**
     * This method is used to convert Date object to String object.
     * 
     * @param begdate
     *            This contains the date.
     * @return String
     */
    public static String dateConverter(Date begdate)
    {

        Format formatter;
        formatter = new SimpleDateFormat(ProgramConstants.DATE_FORMAT, Locale
                .getDefault());

        String beginDate = ContactManagementConstants.EMPTY_STRING;

        if (begdate != null)
        {
            beginDate = formatter.format(begdate);
        }

        return beginDate;
    }

    /**
     * This method is to constuct Address from AddressVO.
     * 
     * @param addressVO
     *            holds addressVO.
     * @return address returns address.
     */
    public Address getAddressFromAddressVO(AddressVO addressVO)
    {   
        Address address = null;
        address = new Address();
        address.setAddedAuditTimeStamp(new Timestamp(0));
        address.setAddedAuditUserID(getUserID());
        address.setAuditUserID(getUserID());
        address.setAuditTimeStamp(new Timestamp(0));
        address.setCountryCode(addressVO.getCountry());
        address.setCityName(addressVO.getCity());
        address.setStateCode(addressVO.getState());
       // address.setLatitudeNumber(Double.valueOf(addressVO.getLatitude()));
        // Code fixed for Defect ESPRD00741223
        if (addressVO.getLatitude() != null && !addressVO.getLatitude().trim().equals(ProgramConstants.EMPTY_STRING ))
		{
			address.setLatitudeNumber(Double.valueOf(addressVO.getLatitude()));
		}

        address.setCountyCode(addressVO.getCounty());
        
        //address.setLongitudeNumber(Double.valueOf(addressVO.getLongitude()));
     // Code fixed for Defect ESPRD00741223
        if(addressVO.getLongitude() != null && !addressVO.getLongitude().trim().equals(ProgramConstants.EMPTY_STRING ))
        {
          address.setLongitudeNumber(Double.valueOf(addressVO.getLongitude()));
        }
        address.setZipCode5(addressVO.getZipCode5());
        address.setTownCode(addressVO.getTown());
        address.setDistrictOfficeCode(addressVO.getDistrictOffice());
        address.setZipCode4(addressVO.getZipCode4());
        address.setAddressLine1(addressVO.getAddressline1());
        address.setAddressLine2((addressVO.getAddressline2()));
        address.setAddressLine3(addressVO.getAddressline3());
        address.setAddressLine4(addressVO.getAddressline4());
        address.setCommonEntityTypeCode("SE");
        
        if(addressVO.getUspsAddressline1() != null && !addressVO.getUspsAddressline1().trim().equals(ProgramConstants.EMPTY_STRING )){
        	logger.info("in ContactHelper getAddressFromAddressVO USPS addressline 1" + addressVO.getUspsAddressline1());
        	 address.setUspsAddressLine1(addressVO.getUspsAddressline1());        	 
        }
        
        if(addressVO.getUspsAddressline2() != null && !addressVO.getUspsAddressline2().trim().equals(ProgramConstants.EMPTY_STRING ) ){
        	logger.info("in ContactHelper getAddressFromAddressVO USPS addressline 2" + addressVO.getUspsAddressline1());
        	 address.setUspsAddressLine2(addressVO.getUspsAddressline2());        	 
        }
        
        
        if (isAddressTypeCodeChanged(addressVO))
        {
            address.setAddressSK(addressVO.getAddressSK());
            address.setVersionNo(addressVO.getVersionNo());
        }

        return address;
    }

    /**
     * This is for getting AddressUsage from AddressVO.
     * 
     * @param addressVO
     *            holds addressVO.
     * @return addressUsage holds addressUsage.
     */
    private AddressUsage getAddressUsageFromAddressVO(AddressVO addressVO)
    {
        AddressUsage addressUsage = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                ProgramConstants.DATE_FORMAT, Locale.getDefault());
        addressUsage = new AddressUsage();
        try
        {
           
            if (addressVO.getBeginDate() != null
                    && !addressVO.getBeginDate().trim().equals(
                            ProgramConstants.EMPTY_STRING))
            {
                
                addressUsage.setBeginDate(dateFormat.parse(addressVO
                        .getBeginDate()));
            }
            if (addressVO.getEndDate() != null
                    && !addressVO.getEndDate().trim().equals(
                            ProgramConstants.EMPTY_STRING))
            {
                
                addressUsage.setEndDate(dateFormat
                        .parse(addressVO.getEndDate()));

            }
            else
            {
                
                addressUsage.setEndDate(dateFormat
                        .parse(ProgramConstants.DEFAULT_DATE));

            }
        }
        catch (ParseException e)
        {
            logger.debug("date format >>> " + e);
        }
        addressUsage.setAddedAuditTimeStamp(new Timestamp(
        		ContactManagementConstants.TIMESTMP));
        addressUsage.setAuditUserID(getUserID());
        addressUsage.setAuditTimeStamp(new Timestamp(
        		ContactManagementConstants.TIMESTMP));
        addressUsage.setAddedAuditUserID(getUserID());

        addressUsage.setAddress(getAddressFromAddressVO(addressVO));

        if (isAddressTypeCodeChanged(addressVO))
        {
            addressUsage.setVersionNo(addressVO.getAddressUsageVersionNo());

        }
        addressUsage.setAddressUsageTypeCode(addressVO.getAddressType());
        addressUsage.setStatusCode(addressVO.getAddressStatus());
        addressUsage.setAddressSK(addressVO.getAddressUsageSK());
        addressUsage.setAddressUsageSequenceNumber(addressVO.getAddressUsageSequenceNumber());
        addressUsage.setAddressUsageSigCode(addressVO.getSignificance());
        addressUsage.setChangeReasonCode(addressVO.getChangeReason());
        logger.debug("address usage... " + addressUsage);
        return addressUsage;
    }

    /**
     * This will method will create AddressVOWith AddressUsage.
     * 
     * @param addressUsage
     *            addressUsage.
     * @return addressVO returns addressVO.
     */
    private AddressVO createAddressVOWithAddressUsage(AddressUsage addressUsage,boolean controlRequired)
    {
        AddressVO addressVO = new AddressVO();
        try
        {
            addressUsage.getCommonEntity();
            //"FindBugs" issue resolved
            /*CommonEntityDataBean commonEntityDataBean = ContactHelper
                    .getCommonEntityDataBean();*/
            

            addressVO.setAddressType(addressUsage.getAddressUsageTypeCode());
            addressVO.setExistingAddressType(addressUsage
                    .getAddressUsageTypeCode());

            addressVO.setBeginDate(dateConverter(addressUsage.getBeginDate()));
            addressVO.setEndDate(dateConverter(addressUsage.getEndDate()));
            addressVO.setAddressStatus(addressUsage.getStatusCode());
            addressVO.setAddressSK(addressUsage.getAddress().getAddressSK());
            addressVO.setAddressline1(addressUsage.getAddress()
                    .getAddressLine1());
            addressVO.setAddressline2(addressUsage.getAddress()
                    .getAddressLine2());
            
            addressVO.setUspsAddressline1(addressUsage.getAddress()
                    .getUspsAddressLine1());
            addressVO.setUspsAddressline2(addressUsage.getAddress()
                    .getUspsAddressLine2());
            
            addressVO.setAddressline3(addressUsage.getAddress()
                    .getAddressLine3());
            addressVO.setAddressline4(addressUsage.getAddress()
                    .getAddressLine4());
            addressVO.setAddressStatus(addressUsage.getStatusCode());
            addressVO.setAddressType(addressUsage.getAddressUsageTypeCode());
            addressVO.setCity(addressUsage.getAddress().getCityName());
            addressVO.setCountry(addressUsage.getAddress().getCountryCode());
            addressVO.setCounty(addressUsage.getAddress().getCountyCode());
            addressVO.setState(addressUsage.getAddress().getStateCode());
            addressVO.setZipCode5(addressUsage.getAddress().getZipCode5());
            addressVO.setZipCode4(addressUsage.getAddress().getZipCode4());
          //  addressVO.setLatitude(addressUsage.getAddress().getLatitudeNumber().toString());
         //   addressVO.setLongitude(addressUsage.getAddress().getLongitudeNumber().toString());
            addressVO.setBusinessType(ProgramConstants.BUSINESS_TYPE);
            addressVO.setAddedAuditTimeStamp(addressUsage
                    .getAddedAuditTimeStamp());
            addressVO.setAuditUserID(addressUsage.getAuditUserID());
            addressVO.setAuditTimeStamp(addressUsage.getAuditTimeStamp());
            addressVO.setAddedAuditUserID(addressUsage.getAddedAuditUserID());
            addressVO.setVersionNo(addressUsage.getAddress().getVersionNo());
            addressVO.setAddressUsageVersionNo(addressUsage.getVersionNo());
            addressVO.setAddressUsageSK(addressUsage.getAddressSK());
            addressVO.setAddressUsageSequenceNumber(addressUsage.getAddressUsageSequenceNumber());
            addressVO.setSignificance(addressUsage.getAddressUsageSigCode());
            addressVO.setChangeReason(addressUsage.getChangeReasonCode());
            addressVO.setLatitude(addressUsage.getAddress().getLatitudeNumber().toString());
            addressVO.setLongitude(addressUsage.getAddress().getLongitudeNumber().toString());
            //--------------getting Audit Key List----------------------------
            
            if(controlRequired){
                createVOAuditKeysList(addressUsage,addressVO);
                createVOAuditKeysList(addressUsage.getAddress(),addressVO);
                //for audit filtering
                filterAddressAudit(addressVO);

                }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }

        return addressVO;

    }

    /**
     * This method converts the Common Entity Representative domain object to
     * Common Contacts value object. It takes the instances of Object to be
     * converted as input and returns the corresponding value object with values
     * from Object being set to the vo.
     * 
     * @param commonEntityObj
     *            The commonEntity is to get commonentity details.
     * @return CommonEntityVO
     */
    public CommonEntityVO convertCERepDomainToCommonContactsVO(
            CommonEntity commonEntityObj)
    {
        CommonEntityVO commonEntityVO = new CommonEntityVO();
if(commonEntityObj != null){
        logger.debug("commonEntityObj.getCommonEntitySK="
                + commonEntityObj.getCommonEntitySK());
}

        if (commonEntityObj != null)
        {
            commonEntityVO
                    .setContactVOList(convertOnlyCERepCrossRefSetToContactVOList(commonEntityObj
                            .getCommonEntityRepCrossReference()));
        }

        return commonEntityVO;
    }

    /**
     * This method would convert the CommonContactVO ArrayList to
     * CommonEntityRepCrossReference Set.
     * 
     * @param commonEntityRepCrossRefSet
     *            This object holds contact list.
     * @return ArrayList returns ArrayList.
     */

    private ArrayList convertOnlyCERepCrossRefSetToContactVOList(
            Set commonEntityRepCrossRefSet)
    {
        logger.info("convertOnlyCERepCrossRefSetToContactVOList");

        HashMap repMap = new HashMap();
        ArrayList contactVOList = null;

        if (commonEntityRepCrossRefSet != null
                && !(commonEntityRepCrossRefSet.isEmpty()))
        {
            /* loading validvalues for contact */
            commonEntityDataBean = getCommonEntityDataBean();
            if (commonEntityDataBean.getEntityTypeList() == null
                    || commonEntityDataBean.getEntityTypeList().size() == 0)
            {
                loadValidValuesForContact();
            }
            /* end of loading valid values */

            contactVOList = new ArrayList();
            CommonContactVO commonContactVO = new CommonContactVO();
            Iterator itr = commonEntityRepCrossRefSet.iterator();

            while (itr.hasNext())
            {
                CommonEntityRepCrossReference commonEntityRepCrossReference = (CommonEntityRepCrossReference) itr
                        .next();

                Representative rep = commonEntityRepCrossReference
                        .getRepresentative();

                if (repMap.containsKey(rep.getRepresentativeSK()))
                {
                    commonContactVO = (CommonContactVO) repMap.get(rep
                            .getRepresentativeSK());
                    commonContactVO
                            .getCommonContactTypeVOList()
                            .add(
                                    convertCERepCrossRefDomainTOCommonContactTypeVO(commonEntityRepCrossReference));
                    repMap.put(rep.getRepresentativeSK(), commonContactVO);
                }
                else
                {
                    commonContactVO = convertOnlyRepDomainToCommonContactVO(rep);
                   
                    commonContactVO.setCommonContactTypeVOList(new ArrayList());
                    commonContactVO
                            .getCommonContactTypeVOList()
                            .add(
                                    convertCERepCrossRefDomainTOCommonContactTypeVO(commonEntityRepCrossReference));
                    repMap.put(rep.getRepresentativeSK(), commonContactVO);
                }
            }
        }

        if (repMap != null && !(repMap.isEmpty()))
        {
            Iterator itr = repMap.keySet().iterator();
            while (itr.hasNext())
            {
                contactVOList.add(repMap.get(itr.next()));

            }
        }

        return contactVOList;
    }

    /**
     * To convert Rep object to VO.
     * 
     * @param rep
     *            This object hods Rep inormation.
     * @return commonConatctVO returns commonConatctVO.
     */

    private CommonContactVO convertOnlyRepDomainToCommonContactVO(
            Representative rep)
    {
        logger.info("convertOnlyRepDomainToCommonContactVO");

        CommonContactVO commonContactVO = new CommonContactVO();
        
        /*
         * Change done for ES2 DO changes
         */
        if (rep.getCommonEntityTypeCode() != null)
        {
            commonContactVO.setEntityType(rep.getCommonEntityTypeCode());
            /*
             * commonContactVO.setEntityTypeDesc(rep.getCommonEntityTypeCode()
             * .getDescription());
             */
        }
        else
        {
            commonContactVO
                    .setEntityType(ContactManagementConstants.EMPTY_STRING);
            commonContactVO
                    .setEntityTypeDesc(ContactManagementConstants.EMPTY_STRING);
        }
        commonContactVO.setContactSK(rep.getRepresentativeSK());
        commonContactVO.setDateOfBirth(dateConverter(rep.getDateOfBirth()));
        commonContactVO.setDateOfDeath(dateConverter(rep.getDateOfDeath()));
        commonContactVO.setGender(rep.getGenderCode());
        commonContactVO.setNameVO(convertNameDomianToVO(rep.getName()));
        if(commonContactVO.getNameVO() != null)
        {
        	commonContactVO.getNameVO().setPhoneticLastName(rep.getPhoneticLastName());
        }
        commonContactVO.setAddedAuditTimeStamp(rep.getAddedAuditTimeStamp());
        commonContactVO.setAddedAuditUserID(rep.getAddedAuditUserID());
        commonContactVO.setAuditTimeStamp(rep.getAuditTimeStamp());
        commonContactVO.setAuditUserID(rep.getAuditUserID());

        String ssn = rep.getSsnNumber();
        int x = 0;
        if (ssn != null && ssn.trim().length() > x)
        {
            StringBuffer newSSN = new StringBuffer();
            int ssnSize = ssn.length();
            for (int i = 0; i < ssnSize; i++)
            {
                if (i == ProgramConstants.INT_THREE
                        || i == ProgramConstants.INT_FIVE)
                {
                    newSSN.append(ProgramConstants.HYPHEN_SEPARATOR);
                    newSSN.append(ssn.charAt(i));
                }
                else
                {
                    newSSN.append(ssn.charAt(i));
                }
            }
            ssn = newSSN.toString();
        }
        commonContactVO.setSSN(ssn);

        return commonContactVO;

    }

    /**
     * This method will get userid from Security.
     * 
     * @return String returns userid.
     */
    public String getUserID()
    {
        String userId = "testuserid";
        CommonNotesControllerBean commonNotesControllerBean = new CommonNotesControllerBean();

        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = commonNotesControllerBean.getUserData(
                renderRequest, renderResponse);

        /*Infinite GAP Implementation UC-PGM-CRM-018.10.GAP_15668*/
        if (eup != null
                && eup.getUserId() != null
                && !eup.getUserId().trim().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {
			userId = eup.getUserId();
        }
        return userId;
    }

    /**
     * @param commonEntityDataBean The commonEntityDataBean to set.
     */
    public void setCommonEntityDataBean(
            CommonEntityDataBean commonEntityDataBean)
    {
        this.commonEntityDataBean = commonEntityDataBean;
    }

    private boolean isEAddressTypeCodeChanged(EAddressVO eaddressVO)
    {
        boolean flag = false;
        if (eaddressVO.getExistingUsageTypeCode() != null
                && eaddressVO.getExistingUsageTypeCode().equals(
                        eaddressVO.getUsageTypeCode()))
        {
            flag = true;

        }
        return flag;
    }

    private boolean isPhoneTypeCodeChanged(PhoneVO phoneVO)
    {
        boolean flag = false;
        if (phoneVO.getExistingUsageTypeCode() != null
                && phoneVO.getExistingUsageTypeCode().equals(
                        phoneVO.getUsageTypeCode()))
        {
            flag = true;

        }
        return flag;
    }

    private boolean isAddressTypeCodeChanged(AddressVO addressVO)
    {
        boolean flag = false;
        if (addressVO.getExistingAddressType() != null
                && addressVO.getExistingAddressType().equals(
                        addressVO.getAddressType()))
        {
            flag = true;

        }
        return flag;
    }
    
    public static String convertDate(String date)
    {
    	boolean format1 = false;
        boolean format2 = false;
        
        Pattern p1 = Pattern.compile(ValidatorConstants.DATE_PATTERN1);
        Matcher m1 = p1.matcher(date);
        format1 = m1.matches();      

        if (!format1)
        {
            Pattern p2 = Pattern.compile(ValidatorConstants.DATE_PATTERN2);
            Matcher m2 = p2.matcher(date);
            format2 = m2.matches();
        }
        
        if (format1)
        {
            date = date.replace('-', '/');
        }
        else if (format2)
        {
            date = date.substring(0, 2) + "/" + date.substring(2, 4) + "/"
                    + date.substring(4);
        }
        return date;
    }

    /**
     * This method will sort phone based 
     * on column and ascending/descending
     * @param phoneVOList List of phones to sort
     * @param column Column to sort on; if null, then sort on phone Status and Begin Date descending
     * @param ascending true/false
     */
    public void sortPhone(List phoneVOList, final String sortColumn, final boolean ascending) {
        FacesContext context = FacesContext.getCurrentInstance();
        final ResourceBundle rb = ResourceBundle.getBundle(ProgramConstants.PHONE_PROPERTIES, context.getViewRoot().getLocale());

        Comparator comparator = new Comparator() {

            public int compare(Object obj1, Object obj2) {
                PhoneVO phoneVO1 = (PhoneVO) obj1;
                PhoneVO phoneVO2 = (PhoneVO) obj2;

                int comparison = 0;

                if ((sortColumn == null) || rb.getString("label.phone.status").equals(sortColumn)) {
                    String val1 = (phoneVO1.getStatusstr() == null) ? "" : phoneVO1.getStatusstr();
                    String val2 = (phoneVO2.getStatusstr() == null) ? "" : phoneVO2.getStatusstr();

                    if(val1.compareTo(val2) != 0)
                    {
                    	comparison = val1.compareTo(val2);
                    }else{
                    	String date1 = (phoneVO1.getBeginDate() == null) ? "" : phoneVO1.getBeginDate();
                        String date2 = (phoneVO2.getBeginDate() == null) ? "" : phoneVO2.getBeginDate();
                    	comparison = date2.compareTo(date1);
                    }
                }
                else if (rb.getString("label.phone.phoneType").equals(sortColumn)) {
                    String val1 = (phoneVO1.getUsageTypeDesc() == null) ? "" : phoneVO1.getUsageTypeDesc();
                    String val2 = (phoneVO2.getUsageTypeDesc() == null) ? "" : phoneVO2.getUsageTypeDesc();

                    comparison = val1.compareTo(val2);
                }
                else if (rb.getString("label.phone.beginDate").equals(sortColumn)) {
                    String val1 = (phoneVO1.getBeginDate() == null) ? "" : phoneVO1.getBeginDate();
                    String val2 = (phoneVO2.getBeginDate() == null) ? "" : phoneVO2.getBeginDate();

                    comparison = val1.compareTo(val2);
                }
                else if (rb.getString("label.phone.endDate").equals(sortColumn)) {
                    String val1 = (phoneVO1.getEndDate() == null) ? "" : phoneVO1.getEndDate();
                    String val2 = (phoneVO2.getEndDate() == null) ? "" : phoneVO2.getEndDate();

                    comparison = val1.compareTo(val2);
                }
                else if (rb.getString("label.phone.phone#").equals(sortColumn)) {
                    String val1 = (phoneVO1.getPhoneNumber() == null) ? "" : phoneVO1.getPhoneNumber();
                    String val2 = (phoneVO2.getPhoneNumber() == null) ? "" : phoneVO2.getPhoneNumber();

                    comparison = val1.compareTo(val2);
                }
                else if (rb.getString("label.phone.extension").equals(sortColumn)) {
                    String val1 = (phoneVO1.getExtension() == null) ? "" : phoneVO1.getExtension();
                    String val2 = (phoneVO2.getExtension() == null) ? "" : phoneVO2.getExtension();

                    comparison = val1.compareTo(val2);
                }
                else if (rb.getString("label.phone.outofservice").equals(sortColumn)) {
                    String val1 = (phoneVO1.getOutOfServicestr() == null) ? "" : phoneVO1.getOutOfServicestr();
                    String val2 = (phoneVO2.getOutOfServicestr() == null) ? "" : phoneVO2.getOutOfServicestr();

                    comparison = val1.compareTo(val2);
                }

                if (!ascending)
                    comparison = comparison * -1;

                return comparison;
            }
        };

        Collections.sort(phoneVOList, comparator);
    }

    /**
     * 
     * @param noteSetObj
     * @return
     */
    public NoteSet getNoteSetForCR(NoteSet noteSetObj)
    {
    	CommonNotesVO commonNotesVO = commonEntityDataBean.getCommonEntityVO().getCommonNotesVO();
    	noteSetObj = new NoteSet();
    	noteSetObj.setAddedAuditTimeStamp(new Date());
    	noteSetObj.setAddedAuditUserID(getUserID());
    	noteSetObj.setAuditTimeStamp(new Date());
    	noteSetObj.setAuditUserID(getUserID());
    	noteSetObj.addNote(convertCommonNoteVOToDomain(commonNotesVO));
    	noteSetObj.setNoteSourceName(ContactManagementConstants.G_CR_TB);
    	commonEntityDataBean.getNoteList().add(0,commonNotesVO);
    	return noteSetObj;
    }
    
    public NoteSetVO convertNoteSetDomainToVO(NoteSet noteSetObj, NoteSetVO noteSetVO)
    {
      //  NoteSetVO NoteSetVO = new NoteSetVO();
        if (noteSetObj != null)
        {

            commonEntityDataBean = getCommonEntityDataBean();
            if (commonEntityDataBean.getNoteTypeList() == null
                    || commonEntityDataBean.getNoteTypeList().size() == 0)
            {
                loadValidValuesForNotes();
            }
            noteSetVO.setNoteSetSK(noteSetObj.getNoteSetSK());
            noteSetVO.setAddedAuditTimeStamp(noteSetObj
                    .getAddedAuditTimeStamp());
            noteSetVO.setAddedAuditUserID(noteSetObj.getAddedAuditUserID());
            noteSetVO.setVersionNo(noteSetObj.getVersionNo());
            ArrayList notesVOList = null;
                        
            Set notesSet = noteSetObj.getNotes();
            if (notesSet != null && !(notesSet.isEmpty()))
            {
                notesVOList = new ArrayList();
                Iterator notesItr = notesSet.iterator();
                while (notesItr.hasNext())
                {
                    Note note = (Note) notesItr.next();
                    notesVOList.add(convertNoteDomainToVO(note, noteSetObj));
                }
                noteSetVO.setNotesList(notesVOList);
                
            }
        }
        return noteSetVO;
    }
    
    public String sortEaddress(List eaddressVOList) {
        List emptyStatusList = new ArrayList();
        List activeList= new ArrayList();
        List deactiveList= new ArrayList();
        List pendingList= new ArrayList();
        List voidList= new ArrayList();
        int index;
        if (eaddressVOList != null) {
            logger.debug("eaddressVOList size " + eaddressVOList.size());
        }
        try{
          Iterator i = eaddressVOList.iterator();
          while (i.hasNext()) {
              EAddressVO eaddressVO = (EAddressVO) i.next();
               if (eaddressVO.getStatus() == null)
                {
                    index = eaddressVOList.indexOf(eaddressVO);
                    emptyStatusList.add(eaddressVOList.get(index));
                  
                }
            }
          eaddressVOList.removeAll(emptyStatusList);
          Iterator j = eaddressVOList.iterator();
          while (j.hasNext())
          {
              EAddressVO eaddressVO = (EAddressVO) j.next();
              index = eaddressVOList.indexOf(eaddressVO);
            
               if(eaddressVO.getStatus().equalsIgnoreCase("A"))
               {
                   activeList.add(eaddressVOList.get(index));
               
               }
               else if(eaddressVO.getStatus().equalsIgnoreCase("D"))
               {
                   deactiveList.add(eaddressVOList.get(index));
               }
               else if(eaddressVO.getStatus().equalsIgnoreCase("P"))
               {
                   
                   pendingList.add(eaddressVOList.get(index));
                  
               }
               else if(eaddressVO.getStatus().equalsIgnoreCase("V"))
               {
                   
                   voidList.add(eaddressVOList.get(index));
                  
               }
          }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(eaddressVOList != null){
        eaddressVOList.clear();
        }
        Comparator comparator = new Comparator() {
            public int compare(Object obj1, Object obj2) {
                EAddressVO eaddressVO1 = (EAddressVO) obj1;
                EAddressVO eaddressVO2 = (EAddressVO) obj2;
                boolean descending = true;
                Date date1 = ContactHelper.dateConverter(eaddressVO1.getBeginDate());
                Date date2 = ContactHelper.dateConverter(eaddressVO2.getBeginDate());
                if (descending) {
                    return date2.compareTo(date1);
                }
                return 0;
            }
        };
        Collections.sort(activeList, comparator);
        Collections.sort(deactiveList, comparator);
        Collections.sort(pendingList, comparator);
        Collections.sort(voidList, comparator);
        Collections.sort(emptyStatusList, comparator);
        eaddressVOList.addAll(activeList);
        eaddressVOList.addAll(deactiveList);
        eaddressVOList.addAll(pendingList);
        eaddressVOList.addAll(voidList);
        eaddressVOList.addAll(emptyStatusList);
        return ContactManagementConstants.RENDER_SUCCESS;
        
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
    	       	
    	       	logger.debug("======LineItemAuditsDelegate====resultList====Size======="+resultList.size());
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
     * 
     * @param addressVOList
     * @return
     */
    public String sortCommonAddress(List addressVOList) 
    {
        if (addressVOList != null) 
        {
            logger.debug("addressVOList size " + addressVOList.size());
        
	        Comparator comparator = new Comparator() {
	            public int compare(Object obj1, Object obj2) {
	            	AddressVO addressVO1 = (AddressVO) obj1;
	            	AddressVO addressVO2 = (AddressVO) obj2;
	                
	                Date date1 = ContactHelper.dateConverter(addressVO1.getBeginDate());
	                Date date2 = ContactHelper.dateConverter(addressVO2.getBeginDate());
                    return date2.compareTo(date1);
	            }
	        };
	        Collections.sort(addressVOList, comparator);
        }
        return ContactManagementConstants.RENDER_SUCCESS;
    }
    
    /** filter the audit log values
     *  of Address section
     * */
    
   
    private void filterAddressAudit(AddressVO addressvo){
    	
    	List editableAddress = new ArrayList();
		editableAddress.add(createAuditableFeild("Address Type","addressUsageTypeCode"));
		
		// Uncommented the below Begin Date Line to fix the Audit defect:ESPRD00792761 in address section.
		editableAddress.add(createAuditableFeild("Begin Date","beginDate"));
		
		editableAddress.add(createAuditableFeild("End Date","endDate"));
		editableAddress.add(createAuditableFeild("Significance Type Code","addressUsageSigCode"));
		editableAddress.add(createAuditableFeild("Status Code","statusCode"));
		editableAddress.add(createAuditableFeild("Change Reason","changeReasonCode"));
		editableAddress.add(createAuditableFeild("Address Line 1","addressLine1"));
		editableAddress.add(createAuditableFeild("Address Line 2","addressLine2"));
		editableAddress.add(createAuditableFeild("City","cityName"));
		editableAddress.add(createAuditableFeild("State","stateCode"));
		editableAddress.add(createAuditableFeild("Zip Code","zipCode5"));
		editableAddress.add(createAuditableFeild("Ext","zipCode4"));
		
		AuditDataFilter.filterAuditKeys(editableAddress,addressvo);
		
    }
    
    private AuditableField createAuditableFeild(String feildName,String domainAttributeName){
		
		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);

        return auditableField;
   }
    
  // Below method added for defect ESPRD00944125 to get UserDetail like last name, first name and USERID.
    
    public String getUserNameByID(String userID)
    {

        String userName = null;
      
        Map userIDMap = new HashMap();
        List userData = null;
		String firstName = null;
		String lastName = null;
		String name = null;
        CMDelegate cMDelegate = new CMDelegate();
        try {
			userData =cMDelegate.getUserDetail(userID);
			if (!userData.isEmpty()) {
				int size = 0;

				size = userData.size();
				

				StringBuffer sbName = null;
				for (int i = 0; i < size; i++) {
					Object[] userDetails = (Object[]) userData.get(i);
					firstName = userDetails[1].toString();
					lastName = userDetails[0].toString();
						
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
					
						userIDMap.put(userDetails[2].toString(), name);
						}
			}

	} catch (Exception e) {
			logger.error("Exception occured at getUsersList()" + e);
		}
        if (!userIDMap.isEmpty())
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("UserID from controller bean is $$ " + userID);
        	}
            userName = (String) userIDMap.get(userID);
            if(logger.isDebugEnabled())
            {
            	logger.debug("User Name in helper is $$ " + userName);
            }
            userIDMap=null;
        }
        return userName;
    }
    
   }

