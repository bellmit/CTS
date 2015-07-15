/*
 * Created on Mar 15, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.commonentities.view.bean;

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
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.myfaces.custom.datascroller.HtmlDataScroller;

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
import com.acs.enterprise.common.program.commonentities.common.delegate.CommonEntityDelegate;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.validator.CommonEntityValidator;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesSearchCriteriaVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This controllerbean is used to manage CommonNotes Information.
 */
public class DetailedNotesControllerBean
extends EnterpriseBaseControllerBean
{
    /**
     * This is to hold dataTable
     */
    private int dataTable;

    /**
     * This is to hold dataScroller
     */
     // Serialization Issue (JSF 2.0 Migration) fix Starts
	 //private HtmlDataScroller dataScroller;
	 private transient HtmlDataScroller dataScroller;

     // Serialization Issue (JSF 2.0 Migration) fix Ends

    /**
     * This is to hold commonEntityDataBean
     */

    private CommonEntityDataBean commonEntityDataBean;

    /**
     * This is to hold dateFormat
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            ProgramConstants.DATE_FORMAT);

    /**
     * To get DataTable.
     *
     * @return dataTable
     */
    public int getDataTable()
    {
        return dataTable;
    }

    /**
     * To set DataTable.
     *
     * @param int
     *            The dataTable to set dataTable.
     */
    public void setDataTable(int dataTable)
    {
        this.dataTable = dataTable;
    }

    /**
     * This is to hold the modifiedFlg value
     */
    private boolean modifiedFlg = false;

    /**
     * This is to hold the EnterpriseLogger value
     */

// Serialization Issue (JSF 2.0 Migration) fix Starts
private static final EnterpriseLogger logger = EnterpriseLogFactory.getLogger(DetailedNotesControllerBean.class);
//    private EnterpriseLogger logger = EnterpriseLogFactory
//            .getLogger(ProgramConstants.COMMON_NOTE_CTRLR_BEAN);
// Serialization Issue (JSF 2.0 Migration) fix Ends

    /**
     * To get the Notes based on the filter infrmation entered by the user. This
     * method would invoke the getFilteredNotes() method of
     * commonEntityDelegate.
     */

    public void getFilteredNotes()
    {
        CommonEntityValidator commonEntityValidator = new CommonEntityValidator();

        commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                .setFilterbeginDate(new Date());

        boolean errFlg = false;
        String beginDate = commonEntityDataBean.getCommonNoteSearchVO()
                .getStrBeginDate();
        String endDate = commonEntityDataBean.getCommonNoteSearchVO()
                .getStrEndDate();

        String usageTypeCode = commonEntityDataBean.getCommonNoteSearchVO()
                .getUsageTypeCode();
        String userId = commonEntityDataBean.getCommonNoteSearchVO()
                .getUserId();

        if (beginDate != null && beginDate.trim().length() > 0)
        {
            try
            {
                beginDate = commonEntityValidator.getValidateDate(beginDate);
                commonEntityDataBean.getCommonNoteSearchVO().setStrBeginDate(
                        beginDate);
                dateFormat.setLenient(false);
                dateFormat.parse(beginDate);
            }
            catch (ParseException e1)
            {
                logger.debug("in validating dates, parsing error ");
                commonEntityValidator.setErrorMessage(
                        ProgramConstants.NOTES_BEGINDATE_INVALID,
                        new Object[] {},
                        ProgramConstants.COMMON_NOTES_PROPERTIES, null);
            }
        }

        if (endDate != null && endDate.trim().length() > 0)
        {
            try
            {
                endDate = commonEntityValidator.getValidateDate(endDate);
                commonEntityDataBean.getCommonNoteSearchVO().setStrEndDate(
                        endDate);
                dateFormat.setLenient(false);
                dateFormat.parse(endDate);
            }
            catch (ParseException e1)
            {
                logger.debug("in validating dates, parsing error ");
                commonEntityValidator.setErrorMessage(
                        ProgramConstants.NOTES_ENDDATE_INVALID,
                        new Object[] {},
                        ProgramConstants.COMMON_NOTES_PROPERTIES, null);
            }
        }

        if (beginDate == null || beginDate.trim().length() == 0)
        {
            setErrorMessage(ProgramConstants.FILTER_BEGIN_DATE_NOT_NULL);
            errFlg = true;
        }
        if (endDate == null || endDate.trim().length() == 0)
        {
            setErrorMessage(ProgramConstants.FILTER_END_DATE_NOT_NULL);
            errFlg = true;
        }
        if (((beginDate != null && beginDate.trim().length() > 0) && (endDate != null && endDate
                .trim().length() > 0))
                && (ContactHelper.dateConverter(endDate).compareTo(
                        ContactHelper.dateConverter(beginDate)) < 0))
        {
            setErrorMessage(ProgramConstants.NOTES_END_DATE_LESSTHAN_BEGINDATE);
            errFlg = true;
        }
        if (!errFlg)
        {
            NoteSet noteSet = null;
            try
            {
                CommonEntityDelegate commonEntityDelegate = new CommonEntityDelegate();
                Long noteSetSK = commonEntityDataBean.getCommonEntityVO()
                        .getDetailNoteSetVO().getNoteSetSK();

                logger.info("beginDate---" + beginDate + "-endDate---"
                        + endDate);

                CommonNotesSearchCriteriaVO commonNoteSearchVO = new CommonNotesSearchCriteriaVO();
                commonNoteSearchVO.setNotesID(noteSetSK);
                commonNoteSearchVO.setBeginDate(ContactHelper
                        .dateConverter(beginDate));
                commonNoteSearchVO.setEndDate(ContactHelper
                        .dateConverter(endDate));

                if (usageTypeCode != null && usageTypeCode.trim().length() == 0)
                {
                    usageTypeCode = null;
                }

                if (userId != null && userId.trim().length() == 0)
                {
                    userId = null;
                }

                commonNoteSearchVO.setUsageTypeCode(usageTypeCode);
                commonNoteSearchVO.setUserId(userId);
                noteSet = commonEntityDelegate
                        .getFilteredNotes(commonNoteSearchVO);
                logger.info("noteSet" + noteSet);
                if (noteSet != null)
                {

                    ContactHelper contactHelper = new ContactHelper();
                    NoteSetVO noteSetVO = contactHelper
                            .convertNoteSetDomainToVO(noteSet);

                    commonEntityDataBean.getCommonEntityVO()
                            .getDetailNoteSetVO().setNotesList(
                                    noteSetVO.getNotesList()); // setNoteSetVO(noteSetVO);

                    if (commonEntityDataBean.getCommonEntityVO()
                            .getDetailNoteSetVO().getNotesList() != null
                            && commonEntityDataBean.getCommonEntityVO()
                                    .getDetailNoteSetVO().getNotesList().size() > 0)
                    {
                        commonEntityDataBean.getCommonEntityVO()
                                .getDetailCommonNotesVO()
                                .setRenderNoHistoryMsg(false);
                    }
                    else
                    {
                        commonEntityDataBean.getCommonEntityVO()
                                .getDetailCommonNotesVO()
                                .setRenderNoHistoryMsg(true);
                    }

                    CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
                            .getCommonEntityVO().getDetailNoteSetVO()
                            .getNotesList().get(0);
                    commonEntityDataBean.getCommonEntityVO()
                            .getDetailCommonNotesVO().setCurrentNote(
                                    commonNoteVO.getNoteText());
                }
                else
                {
                    commonEntityDataBean.getCommonEntityVO()
                            .getDetailNoteSetVO().setNotesList(new ArrayList());
                }
            }
            catch (Exception commonNoteNotFoundException)
            {
                commonNoteNotFoundException.printStackTrace();
            }
        }

    }

    /**
     * CommonNotesControllerBean Constructor
     */

    public DetailedNotesControllerBean()
    {

        commonEntityDataBean = ContactHelper.getCommonEntityDataBean();

        if (commonEntityDataBean.getCommonEntityVO() != null)
        {
            if (commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO() != null)
            {
                if (commonEntityDataBean.getCommonEntityVO()
                        .getDetailNoteSetVO().getNotesList() != null
                        && commonEntityDataBean.getCommonEntityVO()
                                .getDetailNoteSetVO().getNotesList().size() > 0)
                {
                    commonEntityDataBean.getCommonEntityVO()
                            .getDetailNoteSetVO().setFilterLinkRender(true);
                    commonEntityDataBean.getCommonEntityVO()
                            .getDetailNoteSetVO().setPrintLinkRender(true);
                }
            }
        }
    }

    // Begin - modified the method for the HeapDump Fix
    /**
     * To display the selected Notes.
     */
    public void displaySelectedNote()
    {

        if (commonEntityDataBean.getNoteTypeList() == null
                || commonEntityDataBean.getNoteTypeList().size() == 0)
        {
            getReferenceData();
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        Map map = fc.getExternalContext().getRequestParameterMap();
        String indexCode = "0";
        if (map != null && map.size() > 0)
        {
            indexCode = (String) map.get("indexCode");
            if (indexCode == null || indexCode.trim().length() == 0)
            {
                indexCode = "0";
            }
        }

        int index = Integer.parseInt(indexCode);
        String indexCodeCHK = (String) map.get("indexCodeCHK");
        if (indexCodeCHK != null && indexCodeCHK.trim().length() > 0)
        {
            rowCheckBoxChanged();
        }
        else
        {
            CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
                    .getNoteList()
                    .get(index);
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setCurrentNote(commonNoteVO.getNoteText());

        }

    }
    // End - modified the method for the HeapDump Fix

    /**
     * To Filter the Notes.
     */
    public void filterNotes()
    {
        if (commonEntityDataBean.getNoteTypeList() == null
                || commonEntityDataBean.getNoteTypeList().size() == 0)
        {
            getReferenceData();
        }

        commonEntityDataBean.setDetailNewNotesRender(false);
        commonEntityDataBean.setDetailFilterNotesRender(true);
        /*if (commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
                .getNotesList().size() > 0)
        {
            CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
                    .getCommonEntityVO().getDetailNoteSetVO().getNotesList()
                    .get(0);
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setCurrentNote(commonNoteVO.getNoteText());
        }*/
        commonEntityDataBean
				.setCommonNoteSearchVO(new CommonNotesSearchCriteriaVO());
        setCrntNteFrmNteLst();
        commonEntityDataBean.setDetailNotesSaveMsg(Boolean.FALSE);
    }

    /**
     * This operation is to save the commonNotesVO to request scope, this method
     * invkoes the validateNotes() method of commonEntityValidator. If the
     * CommonNotesVO is valid then validateNote() sets the CommonNotesVO to
     * commonEntityDatatbean.NoteSet. And commonEntityDataBean will be save to
     * request scope using tstate tag.
     */
    public void saveNotes()
    {

        boolean valid = false;
        CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
        ContactManagementHelper helper = new ContactManagementHelper();
        valid = validateNotes(commonEntityDataBean
                .getCommonEntityVO().getDetailCommonNotesVO());
        commonEntityDataBean.setFilterEnabled(true);
        if (valid)
        {
        	//for ESPRD00782216 defect.
        	String userId=commonEntityDataBean.getUserId();
        	if(userId==null){
        		ContactHelper contactHelper = new ContactHelper();
        		userId=contactHelper.getUserID();
        		commonEntityDataBean.setUserId(userId);
        	}
        	logger.debug("in side valid condition $$$$$$$$$$$");
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setChecked(false);
            //commonNotesVO.setCommonEntityTypeCode("M");

            if (commonEntityDataBean.getNoteList() == null)
            {
                commonEntityDataBean.setNoteList(new ArrayList());
            }

            int seqNum = commonEntityDataBean.getNoteList().size();

            String shortNotes = "";

           // shortNotes = commonEntityDataBean.getCommonEntityVO()
                 //   .getDetailCommonNotesVO().getNoteText();

            shortNotes=commonEntityDataBean.getNoteText();

            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
					.setNoteText(shortNotes);
            logger.debug("shortNotes $$$$$$$"+shortNotes);
            if (shortNotes != null
                    && shortNotes.trim().length() > ProgramNumberConstants.INT_HUNDRED)
            {
                shortNotes = shortNotes.substring(0,
                        ProgramNumberConstants.INT_HUNDRED);
            }

            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setShortNotes(shortNotes);

            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setNoteSequenceNumber(new Long(seqNum + 1));

            String usageTypeCode = commonEntityDataBean.getCommonEntityVO()
                    .getDetailCommonNotesVO().getUsageTypeCode();

            if (commonEntityDataBean.getNoteTypeList() != null
                    && commonEntityDataBean.getNoteTypeList().size() > 0)
            {
                commonEntityDataBean
                        .getCommonEntityVO()
                        .getDetailCommonNotesVO()
                        .setUsageTypeDesc(
                                getDescriptionFromVV(usageTypeCode,
                                        commonEntityDataBean.getNoteTypeList()));
            }

            try{

            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
					.setUserId(userId);
			//String userId = contactHelper.getUserID();
			String userName = helper.getUserNameByID(userId);
			String userIdName = null;
			logger.debug("userId $$$$$$$"+userId);
			if (userName != null && userName.length() != 0) {
				userIdName = userId + "-" + userName;
			} else {
				userIdName = userId;
			}
			logger.debug("userIdName $$$$$$$$"+userIdName);
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setUserIdName(userIdName);
            }catch(Exception excep){
            	excep.printStackTrace();
            }

            if(commonEntityDataBean.getNoteList()==null){
            	logger.debug("notelist is null $$$$$$$$$$$");
            	commonEntityDataBean.setNoteList(new ArrayList());
            }
            commonEntityDataBean.getNoteList().add(
                            0,
                            commonEntityDataBean.getCommonEntityVO()
                                    .getDetailCommonNotesVO());
            Date date = new Date();
            SimpleDateFormat dateFormate = new SimpleDateFormat(
                    ProgramConstants.DATE_TIME_FORMAT, Locale.getDefault());
            /*commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setAddedAuditTimeStamp(date);*/
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setFilterbeginDate(date);
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
					.setStrBeginDate(dateFormate.format(date));
            /*SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "M/dd/yyyy hh:mm:ss");
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setStrBeginDate(dateFormat.format(date));*/

            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setCurrentNote(commonEntityDataBean.getNoteText());
            commonEntityDataBean.setDetailNewNotesRender(false);
            //commented on 25th July to implement the block level save
            //setErrorMessage(ProgramConstants.NOTES_SAVE_SUCC_MESSAGE);
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setRenderNoHistoryMsg(false);

            //commented for Defect ESPRD00729574 starts
            // Begin - Modified the code for the HeapDump Fix
            /*FacesContext fc = FacesContext.getCurrentInstance();
            String temp = (String) fc.getExternalContext().getRequestParameterMap().get("indexCode");
            int indexTemp = Integer.parseInt(temp);;*/
            // End - Modified the code for the HeapDump Fix
            //Defect ESPRD00729574 ends
            //making checkall as false
            commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
                    .setCheckAll(false);
            //commented for Defect ESPRD00729574 starts
            /*if (commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
                    .getNotesList() != null
                    && commonEntityDataBean.getCommonEntityVO()
                            .getDetailNoteSetVO().getNotesList().size() > 1)
            {
                logger.debug("IN SAVE"
                        + commonEntityDataBean.getCommonEntityVO()
                                .getDetailCommonNotesVO().getChecked());
                ((CommonNotesVO) commonEntityDataBean.getCommonEntityVO()
                        .getDetailNoteSetVO().getNotesList().get(indexTemp))
                        .setChecked(commonEntityDataBean.getCommonEntityVO()
                                .getDetailCommonNotesVO().getChecked());
            }*/
            //Defect ESPRD00729574 ends
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setChecked(false);
            sortListByDate(commonEntityDataBean.getNoteList());
            setDataTable(0);
            //by default should show first page.
			commonEntityDataBean
					.setStartIndexForCmnNotes(MaintainContactManagementUIConstants.ZERO);
			commonEntityDataBean
					.setImageRender(ContactManagementConstants.EMPTY_STRING);
            commonEntityDataBean.setDetailNotesSaveMsg(true);
        }
        else
        {
            if (commonEntityDataBean.getNoteList() != null
                    && commonEntityDataBean.getNoteList().size() > 0)
            {
                ((CommonNotesVO) commonEntityDataBean.getNoteList().get(0))
                        .setChecked(commonEntityDataBean.getCommonEntityVO()
                                .getDetailCommonNotesVO().getChecked());
                commonEntityDataBean.getCommonEntityVO()
                        .getDetailCommonNotesVO().setCurrentNote(
                                ((CommonNotesVO) commonEntityDataBean.getNoteList()
                                        .get(0)).getNoteText());
            }

        }
        List notesList =commonEntityDataBean.getNoteList();

       if(notesList!=null && !notesList.isEmpty()){
    	   logger.debug("======================notesList"+notesList.size());
       	commonEntityDataBean.setTempNoteList(new ArrayList(notesList));
       	//added for defect ESPRD00844022
       	/** This is to display the notes record count
       	 *  under the datatable of notes on minor save
       	 * */
       	int noteListSize=notesList.size();
       	commonEntityDataBean.setStartRecord(1);
		commonEntityDataBean.setEndRecord(noteListSize);
		commonEntityDataBean.setCount(noteListSize);
       }
        commonEntityDataBean.setDetailSaveNotesChk(false);

    }

    /**
     * To operation contains the logic to display UI components to enter new
     * Notes information.
     *
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void addNewNotes()
    {
        if (commonEntityDataBean.getNoteTypeList() == null
                || commonEntityDataBean.getNoteTypeList().size() == 0)
        {
            getReferenceData();
        }
        CommonNotesVO noteSetVO = null;
        noteSetVO = new CommonNotesVO();
        commonEntityDataBean.getCommonEntityVO()
		.setDetailCommonNotesVO(noteSetVO);
        commonEntityDataBean.setNoteText(ContactManagementConstants.EMPTY_STRING);
        commonEntityDataBean.setDetailNewNotesRender(true);
        commonEntityDataBean.setDetailFilterNotesRender(false);
        commonEntityDataBean.setDetailMainNotesRender(true);
        commonEntityDataBean.setDetailSaveNotesChk(true);
        commonEntityDataBean.setFilterEnabled(false);
        commonEntityDataBean.setDetailNotesSaveMsg(Boolean.FALSE);

        if (commonEntityDataBean.getNoteList() != null
                && commonEntityDataBean.getNoteList().size() > 0)
        {
            CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean.getNoteList()
                    .get(0);
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setCurrentNote(commonNoteVO.getNoteText());
            commonNoteVO
                    .setChecked(commonEntityDataBean.getCommonEntityVO()
                            .getDetailCommonNotesVO().getChecked());
        }
        commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                .setChecked(
                        commonEntityDataBean.getCommonEntityVO()
                                .getDetailCommonNotesVO().getChecked());
        //ADDED FOR THE DEFECT ESPRD00748543
        //commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO().setUsageTypeCode(ContactManagementConstants.EMPTY_STRING);
    	//commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO().setNoteText(ContactManagementConstants.EMPTY_STRING);
    	  //ADDED END FOR THE DEFECT ESPRD00748543
    }

    /**
     * To hide the UI fields related to Note.
     *
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void showNotes()
    {
    	commonEntityDataBean = ContactHelper.getCommonEntityDataBean();
        commonEntityDataBean.setDetailNewNotesRender(false);
        commonEntityDataBean.setDetailFilterNotesRender(false);
        commonEntityDataBean.setDetailMainNotesRender(true);
        commonEntityDataBean.setDetailNotesSaveMsg(false);  // Defect ESPRD00729574 Modified

        //need to comment while integrating with modules
        /*
         * commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO().setNoteSetSK(
         * new Long("139"));
         */

       /* if (commonEntityDataBean.getNoteList() != null
                && commonEntityDataBean.getNoteList().size() > 0)
        {
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setRenderNoHistoryMsg(false);
            CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean.getNoteList()
                    .get(0);
            logger.debug("in show notes commonNoteVO.getNoteText() $$$$$"+commonNoteVO.getNoteText());
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setCurrentNote(commonNoteVO.getNoteText());
        }
        else
        {
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setRenderNoHistoryMsg(true);
        }*/
        setCrntNteFrmNteLst();

        //commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO().setAddNewLinkRender(false);
        //commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO().setFilterLinkRender(false);
        //commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO().setPrintLinkRender(false);

        /* setValidValues() ; */
      // by default should show first page.
		commonEntityDataBean
				.setStartIndexForCmnNotes(MaintainContactManagementUIConstants.ZERO);
		commonEntityDataBean.setImageRender(ContactManagementConstants.EMPTY_STRING);
		//by default sort by begin date.
		sortListByDate(commonEntityDataBean.getNoteList());

    }

    /**
     * To hide the UI fields related to Note.
     *
     * @return cancelNotes
     */
    public String cancelNotes()
    {

        commonEntityDataBean.setDetailNewNotesRender(false);
        commonEntityDataBean.setDetailFilterNotesRender(false);
        commonEntityDataBean.setDetailMainNotesRender(false);
        commonEntityDataBean.setDetailSaveNotesChk(false);
        commonEntityDataBean.setFilterEnabled(true);
        return "cancelNotes";
    }

    /**
     * To reset the values.
     */

    public void reset()
    {

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
    private void setErrorMessage(String message)
    {
        long entryTime = System.currentTimeMillis();
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
    private ResourceBundle resourceBundle(FacesContext facesContext)
    {
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
    public void checkBoxChanged()
    {

        boolean checkedAll = commonEntityDataBean.getCommonEntityVO()
                .getDetailNoteSetVO().getCheckAll();
        //Modified for defect ESPRD00860914
        /*ArrayList notesList = commonEntityDataBean.getCommonEntityVO()
                .getDetailNoteSetVO().getNotesList();*/
        List notesList = commonEntityDataBean.getNoteList();

        int size = notesList.size();

        for (int i = 0; i < size; i++)
        {
            ((CommonNotesVO) notesList.get(i)).setChecked(checkedAll);
        }

        commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
                .setCheckAll(checkedAll);
       //Modified for defect ESPRD00860914
       /* commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
                .setNotesList(notesList);*/
        commonEntityDataBean.setNoteList(notesList);
        //this is to set the first index/0 value of grid
        commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                .setChecked(checkedAll);
    }

    /**
     * To handle if single check box is selected.
     */

    public void rowCheckBoxChanged()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map map = fc.getExternalContext().getRequestParameterMap();
        String indexCode = "0";

        if (map != null && map.size() > 0)
        {
            indexCode = (String) map.get("indexCode");
            if (indexCode == null || indexCode.trim().length() == 0)
            {
                indexCode = "0";
            }
        }
        int index = Integer.parseInt(indexCode);
        if (!modifiedFlg)
        {
            CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
                    .getCommonEntityVO().getDetailNoteSetVO().getNotesList()
                    .get(index);
            commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                    .setCurrentNote(commonNoteVO.getNoteText());
            if (!commonNoteVO.getChecked())
            {
                commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
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
    public void sort(ActionEvent event)
    {

        List notesVOList = commonEntityDataBean.getNoteList();
        if (notesVOList != null)
        {
            logger.debug("notesVOList size " + notesVOList.size());
        }
        final String sortColumn = (String) event.getComponent().getAttributes()
                .get(ContactManagementConstants.COLUMN_NAME);
        final String sortOrder = (String) event.getComponent().getAttributes()
                .get(ContactManagementConstants.SORT_ORDER);
        commonEntityDataBean.setImageRender(event.getComponent().getId());
        logger.debug("sortColumn $$$$$$"+sortColumn);
        logger.debug("sortOrder $$$$$$$$$"+sortOrder);
        clearAllChecks();
        Comparator comparator = new Comparator()
        {

            public int compare(Object obj1, Object obj2)
            {
                CommonNotesVO commonNotesVO1 = (CommonNotesVO) obj1;
                CommonNotesVO commonNotesVO2 = (CommonNotesVO) obj2;

                commonNotesVO1.setChecked(false);
                commonNotesVO2.setChecked(false);

                boolean ascending = false;
                if ("asc".equals(sortOrder))
                {
                    ascending = true;
                }
                else
                {
                    ascending = false;
                }

                if (sortColumn == null)
                {
                    return 0;
                }

                if ("UserID".equals(sortColumn))
                {

                    return ascending ? (commonNotesVO1.getUserId()
                            .compareTo(commonNotesVO2.getUserId()))
                            : (commonNotesVO2.getUserId()
                                    .compareTo(commonNotesVO1.getUserId()));
                }

                if ("Note".equals(sortColumn))
                {

                    return ascending ? (commonNotesVO1.getNoteText()
                            .compareTo(commonNotesVO2.getNoteText()))
                            : (commonNotesVO2.getNoteText()
                                    .compareTo(commonNotesVO1.getNoteText()));
                }

                if ("UsageTypeCode".equals(sortColumn))
                {

                    return ascending ? (commonNotesVO1.getUsageTypeCode()
                            .compareTo(commonNotesVO2.getUsageTypeCode()))
                            : (commonNotesVO2.getUsageTypeCode()
                                    .compareTo(commonNotesVO1
                                            .getUsageTypeCode()));
                }

                if ("DateTime".equals(sortColumn))
                {

                    if (commonNotesVO1.getFilterbeginDate() != null
                            && commonNotesVO2.getFilterbeginDate() != null)
                    {
                        return ascending ? (commonNotesVO1.getFilterbeginDate()
                                .compareTo(commonNotesVO2.getFilterbeginDate()))
                                : (commonNotesVO2.getFilterbeginDate()
                                        .compareTo(commonNotesVO1
                                                .getFilterbeginDate()));
                    }
                }

                return 0;
            }

        };

        Collections.sort(notesVOList, comparator);
        setDataTable(MaintainContactManagementUIConstants.ZERO);
        //by default should show first page.
        commonEntityDataBean.setStartIndexForCmnNotes(MaintainContactManagementUIConstants.ZERO);
        setCrntNteFrmNteLst();
    }

    /**
     * To handle the DataScroll action.
     *
     * @param e
     *            The e to handle the ActionEvent.
     */
    public void dataScrollerAction(ActionEvent e)
    {
        clearAllChecks();
    }

    /**
     * To clear all check boxes.
     */
    private void clearAllChecks()
    {

        List notesList = commonEntityDataBean.getNoteList();

        if (notesList != null && notesList.size() > 0)
        {
            int size = notesList.size();
            for (int i = 0; i < size; i++)
            {
                ((CommonNotesVO) notesList.get(i)).setChecked(false);
            }
        }

        commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO()
                .setCheckAll(false);
        commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
                .setChecked(false);
    }

    /**
     * To sort the List based on date.
     *
     * @param notesList
     *            The notesList to get the CommonNotesVOs
     */
    private void sortListByDate(List notesList)
    {

		Comparator comparator = new Comparator() {
			public int compare(Object obj1, Object obj2) {
				CommonNotesVO commonNotesVO1 = (CommonNotesVO) obj1;
				CommonNotesVO commonNotesVO2 = (CommonNotesVO) obj2;
				commonNotesVO1.setChecked(false);
				commonNotesVO2.setChecked(false);
				try {
					SimpleDateFormat dateFormate = new SimpleDateFormat(
			                ProgramConstants.DATE_TIME_FORMAT, Locale.getDefault());
					if(commonNotesVO1.getStrBeginDate()==null){

					}
					Date begindate1 = dateFormate.parse(commonNotesVO1
							.getStrBeginDate());
					Date beginedate2 = dateFormate.parse(commonNotesVO2
							.getStrBeginDate());

					boolean ascending = false;

					if (begindate1 != null && beginedate2 != null) {
						if (begindate1.compareTo(beginedate2) != 0) {
							return ascending ? (begindate1
									.compareTo(beginedate2)) : (beginedate2
									.compareTo(begindate1));
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
		setCrntNteFrmNteLst();

	}


    /**
     * @return Returns the dataScroller.
     */
    public HtmlDataScroller getDataScroller()
    {
        return dataScroller;
    }

    /**
     * @param dataScroller
     *            The dataScroller to set.
     */
    public void setDataScroller(HtmlDataScroller dataScroller)
    {
        this.dataScroller = dataScroller;
    }

    /**
     * To get the avlidvalues.
     */
    private void getReferenceData()
    {
        InputCriteria inputCriteria = null;
        List list = null;
        HashMap map = new HashMap();
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

        for (int i = 0; i < list.size(); i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            commonEntityDataBean.getNoteTypeList().add(
                    new SelectItem(refVo.getValidValueCode(), refVo
                            .getValidValueCode()
                            + "-" + refVo.getShortDescription()));

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
    private String getDescriptionFromVV(String code, List vvList)
    {
        String desc = "";
        String valueStr = "";
        for (int i = 0; i < vvList.size(); i++)
        {
            SelectItem selectItem = (SelectItem) vvList.get(i);
            valueStr = "";
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
        return desc;
    }

    /** To get the Notes based on the filter administrative hearing
     *  information entered by the user
     * */
    public void getAppealFilterNotes(){


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
		logger.debug("beginDate=======" + beginDate);
		String endDate = commonEntityDataBean.getCommonNoteSearchVO()
				.getStrEndDate();
		logger.debug("endDate=======" + endDate);
		String usageTypeCode = commonEntityDataBean.getCommonNoteSearchVO()
				.getUsageTypeCode();
		logger.debug("usageTypeCode=======" + usageTypeCode);
		String userId = commonEntityDataBean.getCommonNoteSearchVO()
				.getUserId();
		logger.debug("userId=======" + userId);
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
						logger.debug("both the dates are not null");
						Date fromDate = ContactHelper.dateConverter(beginDate);
						Date toDate = ContactHelper.dateConverter(endDate);
						Date strBeginDate = ContactHelper
								.dateConverter(commonNotesVO.getStrBeginDate());
						logger.debug("strBeginDate=======" + strBeginDate);
						if (strBeginDate.before(fromDate)
								|| strBeginDate.after(toDate)) {
							filtered = false;
							logger.debug("1=========" + filtered);
						}
					} else if (beginDate != null && beginDate.trim() != null
							&& !beginDate.trim().equalsIgnoreCase("")) {
						logger.debug("begin date is not null");
						Date fromDate = ContactHelper.dateConverter(beginDate);
						Date strBeginDate = ContactHelper
								.dateConverter(commonNotesVO.getStrBeginDate());
						if (strBeginDate.before(fromDate)) {
							filtered = false;
							logger.debug("2=========" + filtered);
						}
					} else if (endDate != null && endDate.trim() != null
							&& !endDate.trim().equalsIgnoreCase("")) {
						logger.debug("end date is not null");
						Date toDate = ContactHelper.dateConverter(endDate);
						Date strBeginDate = ContactHelper
								.dateConverter(commonNotesVO.getStrBeginDate());
						if (strBeginDate.after(toDate)) {
							filtered = false;
							logger.debug("3=========" + filtered);
						}
					}
					if (usageTypeCode != null && usageTypeCode.trim() != null
							&& !usageTypeCode.trim().equalsIgnoreCase("")) {
						if (!usageTypeCode.equalsIgnoreCase(commonNotesVO
								.getUsageTypeCode())) {
							filtered = false;
							logger.debug("4=========" + filtered);
						}
					}
					if (userId != null && userId.trim() != null
							&& !userId.trim().equalsIgnoreCase("")) {
						if (!userId.equalsIgnoreCase(commonNotesVO.getUserId())) {
							filtered = false;
							logger.debug("5=========" + filtered);
						}
					}
					if (filtered) {
						filteredNotesList.add(commonNotesVO);
					}
				}
				commonEntityDataBean.setNoteList(filteredNotesList);
				commonEntityDataBean.getCommonEntityVO().getDetailNoteSetVO().setNotesList(filteredNotesList);
				if (filteredNotesList != null && filteredNotesList.size() > 0) {
					CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
							.getNoteList().get(0);
					commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
							.setCurrentNote(commonNoteVO.getNoteText());
					commonEntityDataBean.setCurrentNoteRender(true);
				} else if (filteredNotesList.size() <= 0) {
					commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
            		.setCurrentNote(ContactManagementConstants.EMPTY_STRING);
					commonEntityDataBean.setCurrentNoteRender(false);
				}
			}
		}


    }

    /**
	 * This method will make a call to Validate Note method in the common class
	 * and if it is valid it will update in the CommonEntityVO.
	 *
	 * @param commonNotenote
	 *            holds common notcevo.
	 * @return boolean
	 */
	public boolean validateNotes(CommonNotesVO commonNotenote) {
		logger.debug("in validateNotes$$$$$$"+commonNotenote);
	    boolean valid = true;
		int x = 0;
		if (commonNotenote != null) {
			CommonEntityValidator commonEntityValidator = new CommonEntityValidator();
			String noteText = commonEntityDataBean.getNoteText();
			String usageTypeCode = commonNotenote.getUsageTypeCode();
			logger.debug("noteText $$$$$$$$$$"+noteText);
			logger.debug("usageTypeCode $$$$$$$$$$$$$$"+usageTypeCode);
			if (noteText == null || noteText.trim().length() == x) {
				commonEntityValidator.setErrorMessage(ProgramConstants.NOTES_TEXT_NOT_NULL,
						new Object[] {},
						ProgramConstants.COMMON_NOTES_PROPERTIES, "lblNotes");
				logger.debug("noteText == null || noteText.trim().length() == x");
				valid = false;
			}
			if (usageTypeCode == null || usageTypeCode.trim().length() == x) {
				commonEntityValidator.setErrorMessage(ProgramConstants.USAGETYPE_CODE_NOT_NULL,
						new Object[] {},
						ProgramConstants.COMMON_NOTES_PROPERTIES,
						"lblUsageTypeCode");
				valid = false;
				logger.debug("usageTypeCode == null || usageTypeCode.trim().length() == x");
			}
		}
		return valid;
	}

	/**
	 * This method will set the current note from the note list
	 */
	private void setCrntNteFrmNteLst() {
		commonEntityDataBean = ContactHelper.getCommonEntityDataBean();
		if (commonEntityDataBean.getNoteList() != null
				&& commonEntityDataBean.getNoteList().size() > 0) {
			commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
					.setRenderNoHistoryMsg(false);
			CommonNotesVO commonNoteVO = (CommonNotesVO) commonEntityDataBean
					.getNoteList().get(0);
			logger.debug("in show notes commonNoteVO.getNoteText() $$$$$"
					+ commonNoteVO.getNoteText());
			commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
					.setCurrentNote(commonNoteVO.getNoteText());
		} else {
			commonEntityDataBean.getCommonEntityVO().getDetailCommonNotesVO()
					.setRenderNoHistoryMsg(true);
		}
	}
}
