/*
 * Created on Jun 9, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.GlobalCommonNoteFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.GlobalCommonNotesDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.EntityNotesSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CorrespondenceDOConvertor;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;


/**
 * @author vijaymai TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class GlobalCommonNotesControllerBean
        extends EnterpriseBaseControllerBean
{
    /**
     * Generating object of EnterpriseLogger.
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(GlobalCommonNotesControllerBean.class);

    /** Creates reference of data Bean */
    private GlobalCommonNotesDataBean globalCommonNotesDataBean = getGlobalCommonNotesDataBean();

    /** Creates Reference of CMEntityDOConvertor */
    private CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();

    /**
     * This Method Renders the Different Search Entity Block based on the Entity
     * Type .
     * 
     * @return String
     */
    public String renderRespEntityBlock()
    {

        logger.debug("renderRespEntityBlock");

        String newValue = (String) globalCommonNotesDataBean
                .getEntityNotesSearchCriteriaVO().getEntityType();
        logger.debug("Inside renderRespEntityBlock -- new value " + newValue);
        globalCommonNotesDataBean.getEntityNotesSearchCriteriaVO()
                .setEntityType(newValue);

        // resetSearch();

        /** ENTITY TYPE -- MEMBER */

        if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_MEM, newValue))
        {
            logger.info("Inside if loop for member ");

            /** Populates the Entity ID Type dropdown for Entity Type Member */
            if (cmEntityDOConvertor.getMemEntIDTypeReferenceData() != null)
            {
                globalCommonNotesDataBean
                        .setEntityIDTypeList(cmEntityDOConvertor
                                .getMemEntIDTypeReferenceData());
            }

        }

        /** ENTITY TYPE -- PROVIDER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_PROV, newValue))
        {

            logger.info("Inside if loop for provider ");

            Long sysListNumber = ContactManagementHelper
                    .getSystemlistForEntyIdType(
                            ContactManagementConstants.ENTITYIDTYPE,
                            ContactManagementConstants.ENTITY_TYPE_PROV);
            /** Populates the Entity ID Type dropdown for Entity Type Provider */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.PROVIDER, sysListNumber) != null)
            {
                globalCommonNotesDataBean
                        .setEntityIDTypeList(cmEntityDOConvertor
                                .getEntIDTypeReferenceData(
                                        FunctionalAreaConstants.PROVIDER,
                                        sysListNumber));
            }

        }

        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This Method Performs the Reset Operation in Search page.
     * 
     * @return Success
     */

    public String resetSearch()
    {
        logger.debug("Inside resetSearch");

        globalCommonNotesDataBean
                .setEntityNotesSearchCriteriaVO(new EntityNotesSearchCriteriaVO());

        globalCommonNotesDataBean.setRenderSearchResult(Boolean.FALSE);

        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method gets the search resluts on performing search operation
     * 
     * @return String
     */
    public String getEntities()
    {
        logger.info("Inside getEntities method  ");
        /** Get the search Criteria from DataBean */
        EntityNotesSearchCriteriaVO entityNotesSearchCriteriaVO = globalCommonNotesDataBean
                .getEntityNotesSearchCriteriaVO();

        boolean flag = validateSearchCriteria(entityNotesSearchCriteriaVO);
        logger.debug("Value of  the boolean flag " + flag);

        if (flag)
        {
            entityNotesSearchCriteriaVO
                    .setStartIndex(ProgramNumberConstants.INT_ZERO);
            entityNotesSearchCriteriaVO
                    .setRowsPerPage(ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
            EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
            GlobalCommonNotesDelegate globalCommonNotesDelegate = new GlobalCommonNotesDelegate();

            /** Set the date fields from String Var in VO to Date Var */
            Date beginDate = dateConverter(entityNotesSearchCriteriaVO
                    .getBeginDateInString());
            Date enddate = dateConverter(entityNotesSearchCriteriaVO
                    .getEndDateInString());

            entityNotesSearchCriteriaVO.setBeginDate(beginDate);
            entityNotesSearchCriteriaVO.setEndDate(enddate);

            try
            {

                enterpriseSearchResultsVO = globalCommonNotesDelegate
                        .getEntityNotes(entityNotesSearchCriteriaVO);

                List resultsList = new ArrayList();

                resultsList = enterpriseSearchResultsVO.getSearchResults();

                if (resultsList.isEmpty())
                {
                    logger.debug("No records found ---size is zero");
                    setErrorMessage(
                            EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
                            new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
                            null);

                }

                else
                {
                    logger.debug("Inside else loop !!!!! ");
                    logger.debug("size in cntrl loop ------>"
                            + enterpriseSearchResultsVO.getSearchResults()
                                    .size());

                    globalCommonNotesDataBean.setSearchResultsList(resultsList);
                    globalCommonNotesDataBean
                            .setRenderSearchResult(Boolean.TRUE);

                    // setRecordRange(enterpriseSearchResultsVO);

                }

            }
            catch (GlobalCommonNoteFetchBusinessException e)
            {
                logger.debug("inside controller catch ----cm fectch exp@@@@");
                logger.error(e.getMessage(), e);
                setErrorMessage( EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
                new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
            /*    setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);*/

            }
        }

        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This operation is used to get Contact Management Entity Record details
     * based on the primary key value 'CMEntityID'
     */
    public void getEntityNotesDetails()
    {
    	 logger.debug("Inside getEntityNotesDetails");

        
         
         String commonEntSK = ContactManagementConstants.EMPTY_STRING;
         FacesContext context = FacesContext.getCurrentInstance();
         Map map = context.getExternalContext().getRequestParameterMap();

         commonEntSK = (String) map.get("commonEntSKVal");
         logger.info("commonEntSK========"+commonEntSK);
         if (commonEntSK != null)
         {
         	NoteSet noteSet = null;
         	 GlobalCommonNotesDelegate globalCommonNotesDelegate = new GlobalCommonNotesDelegate();
             try
             {
             	noteSet = globalCommonNotesDelegate.getEntityNotesDetails(commonEntSK);
             }
             catch (GlobalCommonNoteFetchBusinessException e)
             {
                 logger.error(e.getMessage(), e);
             }
           //  CorrespondenceDOConvertor crDOConvertor = new CorrespondenceDOConvertor(); Find bug issue
             
             logger.debug("after getting NoteSet ");

             NoteSetVO noteSetVO = new NoteSetVO();
             CommonEntityDataBean commonEntityDataBean = ContactHelper
                     .getCommonEntityDataBean();

             logger.info("Here1 ");
          //   CommonEntityVO commonEntityVO = new CommonEntityVO(); // Find bug issue
             ContactHelper contactHelper = new ContactHelper();

             if (noteSet != null)
             {
                 logger.info("Notes set not null ");
                 noteSetVO = contactHelper.convertNoteSetDomainToVO(noteSet);
                 if (commonEntityDataBean != null)

                 {
                     commonEntityDataBean.getCommonEntityVO()
                             .setNoteSetVO(noteSetVO);
                 }
             }
             if (commonEntityDataBean != null && commonEntityDataBean.getCommonEntityVO().getNoteSetVO() == null)
             {
                 commonEntityDataBean.getCommonEntityVO().setNoteSetVO(
                         new NoteSetVO());
             }
             if (commonEntityDataBean != null && commonEntityDataBean.getCommonEntityVO().getNoteSetVO()
                     .getNotesList() != null)
             {
                 int notesCount = commonEntityDataBean.getCommonEntityVO()
                         .getNoteSetVO().getNotesList().size();
             }
             getCommonEntityDataBean().setMainNotesRender(true);
         }
       
    }
    
    /**
     * This method is to create an instance of Data Bean.
     * 
     * @return Returns CommonEntityDataBean
     */
    public CommonEntityDataBean getCommonEntityDataBean()
    {
       
        FacesContext fc = FacesContext.getCurrentInstance();
        CommonEntityDataBean commonEntityDataBean = (CommonEntityDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + CommonEntityDataBean.BEAN_NAME + "}")
                .getValue(fc);
      
        return commonEntityDataBean;
    }

    /**
     * This operation will be used to sort the set of records.
     * 
     * @param event
     *            Takes event as param
     * @return String
     */
    public String sortSearchResults(ActionEvent event)
    {

        logger.debug("INSIDE  SORTING METHOD ");
        final String sortColumn = (String) event.getComponent().getAttributes()
                .get(ContactManagementConstants.COLUMN_NAME);
        logger.debug("Sort column--->" + sortColumn);

        final String sortOrder = (String) event.getComponent().getAttributes()
                .get(ContactManagementConstants.SORT_ORDER);
        logger.debug("Sort Order---->" + sortOrder);

      //  List searchList = new ArrayList(); //FInd bug issue

        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method Validates the Search Criteria .
     * 
     * @param entityNotesSearchCriteriaVO
     *            Takes entityNotesSearchCriteriaVO as param
     * @return boolean
     */
    private boolean validateSearchCriteria(
            EntityNotesSearchCriteriaVO entityNotesSearchCriteriaVO)
    {
        logger.info("validateSearchCriteria");
        boolean dataFlag = true;
        String entType = entityNotesSearchCriteriaVO.getEntityType();

        /** Checks if Entity type drop Down is empty */
        if (isNullOrEmptyField(entType))
        {

            logger.debug("Inside loop for entity Type null");
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.ENTITY_TYPE},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, null);
            dataFlag = false;

        }

        else if ((entType
                .equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_PROV) || entType
                .equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM))
                && isNullOrEmptyField(entityNotesSearchCriteriaVO
                        .getEntityIDType()))
        {
            setErrorMessage(
                    EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.ENTITY_ID_TYPE_LABEL},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, null);

            dataFlag = false;

        }
        if (isNullOrEmptyField(entityNotesSearchCriteriaVO.getEntityID()))
        {
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.ENTITY_ID},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, null);
            dataFlag = false;
        }

        /** Begin date Null check */
        /*if (isNullOrEmptyField(entityNotesSearchCriteriaVO
                .getBeginDateInString()))
        {
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.BEGIN_DATE},
                    MessageUtil.ENTMESSAGES_BUNDLE, null);
            dataFlag = false;
        }*/

        /** Begin date Validate check */
        //else
        if (!isNullOrEmptyField(entityNotesSearchCriteriaVO
                .getBeginDateInString()))
        {
            Date sentDt = dateConverter(entityNotesSearchCriteriaVO
                    .getBeginDateInString());
            if (sentDt == null)
            {
                logger.debug("Invalid Begin date  Date");

                setErrorMessage(EnterpriseMessageConstants.ERR_SW_INVALID,
                        new Object[] {ContactManagementConstants.BEGIN_DATE},
                        MessageUtil.ENTMESSAGES_BUNDLE, null);
                dataFlag = false;

            }

        }

        /** End date Null check */
       /* if (isNullOrEmptyField(entityNotesSearchCriteriaVO.getEndDateInString()))
        {
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.END_DATE},
                    MessageUtil.ENTMESSAGES_BUNDLE, null);
            dataFlag = false;
        }*/

        /** End date Validate check */
        //else
        if (!isNullOrEmptyField(entityNotesSearchCriteriaVO.getEndDateInString()))
        {
            Date sentDt = dateConverter(entityNotesSearchCriteriaVO
                    .getEndDateInString());
            if (sentDt == null)
            {
                logger.debug("Invalid end date  Date");

                setErrorMessage(EnterpriseMessageConstants.ERR_SW_INVALID,
                        new Object[] {ContactManagementConstants.END_DATE},
                        MessageUtil.ENTMESSAGES_BUNDLE, null);
                dataFlag = false;

            }

        }

        return dataFlag;
    }

    /**
     * Gets reference of GlobalCommonNotesDataBean.
     * 
     * @author Wipro.
     * @return GlobalCommonNotesDataBean
     */
    public GlobalCommonNotesDataBean getGlobalCommonNotesDataBean()
    {

        logger.info("Inside getGlobalCommonNotesDataBean method ");
        FacesContext fc = FacesContext.getCurrentInstance();
        GlobalCommonNotesDataBean globalCommonNotesDataBean = (GlobalCommonNotesDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + GlobalCommonNotesDataBean.BEAN_NAME + "}")
                .getValue(fc);

        return globalCommonNotesDataBean;
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
            String messageBundle, String componentId)
    {
        logger.info("setErrorMessage");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        Locale locale = root.getLocale();
        String clientId = null;

        facesContext.getApplication().setMessageBundle(messageBundle);
        String errorMsg = MessageUtil.format(messageBundle, errorName,
                arguments, locale);
        FacesMessage fc = new FacesMessage();
        fc.setDetail(errorMsg);

        if (componentId != null)
        {
            logger.debug("Component ID " + componentId);

            UIComponent uiComponent = findComponentInRoot(componentId);
            clientId = uiComponent.getClientId(facesContext);

            logger.debug("Client Id " + clientId);
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
    public UIComponent findComponentInRoot(String id)
    {
        logger.info("findComponentInRoot");

        UIComponent component = null;
        FacesContext context = FacesContext.getCurrentInstance();

        if (context != null)
        {
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
    public UIComponent findComponent(UIComponent base, String id)
    {
        logger.info("findComponent");

        UIComponent result = null;
        // Is the "base" component itself the match we are looking for?
        if (id.equals(base.getId()))
        {
            result = base;
        }

        else
        {
            // Search through our facets and children
            UIComponent component = null;

            Iterator cmpIterator = base.getFacetsAndChildren();

            while (cmpIterator.hasNext() && (result == null))
            {
                component = (UIComponent) cmpIterator.next();
                if (id.equals(component.getId()))
                {
                    result = component;
                    break;
                }
                result = findComponent(component, id);
                if (result != null)
                {
                    break;
                }
            }
        }
        return result;
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
        logger.info("isNullOrEmptyField");

        return (inputField == null || ContactManagementConstants.EMPTY_STRING
                .equalsIgnoreCase(inputField.trim()));
    }

    /**
     * This method is used to convert String object to Date object
     * 
     * @param strdate
     *            String String This contains the Date.
     * @return Date
     */
    public Date dateConverter(String strdate)
    {
        Date date = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        if (strdate != null)
        {
            if (EnterpriseCommonValidator.validateDate(strdate))
            {
                logger.debug(" valid date");
                String valDt = getValidDateFormat(strdate);
                try
                {
                    date = new Date(sdf1.parse(valDt).getTime());
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                logger.debug("Date is invalid");
                date = null;
            }
        }
        return date;
    }

    /**
     * This method returns the valid date format MM/DD/YY
     * 
     * @param inputDate
     *            String
     * @return String
     */
    public static String getValidDateFormat(String inputDate)
    {
        String outputDate = inputDate;

        if (!(outputDate.indexOf('/') > 0))
        {
            if (outputDate.indexOf('-') > 0)
            {
                inputDate = inputDate.replace('-', '/');
            }
            else
            {
                inputDate = inputDate.substring(0, 2) + "/"
                        + inputDate.substring(2, 4) + "/"
                        + inputDate.substring(4, 8);
            }
        }
        return inputDate;
    }
}
