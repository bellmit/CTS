/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceAttachmentXref;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.AttachmentDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceAttachmentXrefVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
/*import com.ibm.websphere.cache.DistributedMap;
import com.ibm.ws.cache.spi.DistributedMapFactory;*/

/**
 * @author Wipro This class is a data bean which holds the value objects and
 *         other data related to add/update Maintenance Category functionality.
 */
public class AttachmentDataBean
        extends EnterpriseBaseDataBean
{

    /** Enterprise Logger for Logging */
    private static final EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(AttachmentDataBean.class);

    /**
     * Holds attachmentVO.
     */
    private AttachmentVO attachmentVO = new AttachmentVO();

    /**
     * Holds attachmentList.
     */
    private List attachmentList = new ArrayList();

    /**
     * Variable used to render Image for Sorting purpose.
     */
    private String imageRender;

    /**
     * Holds noData.
     */
    private boolean noData = false;

    /** holds the boolean flag for showing the Add screen for Attachments */
    private boolean showAddAttachments = false;

    /** holds the boolean flag for showing the Edit screen for Attachments */
    private boolean showEditAttachments = false;

    /** holds the boolean flag for showing that the record is saved */
    private boolean showSucessMessage = false;
    
    /**
     * Added for CR 781265
     */
    
    private boolean showUnSucessMessage = false;

    public boolean isShowUnSucessMessage() {
		return showUnSucessMessage;
	}
	public void setShowUnSucessMessage(boolean showUnSucessMessage) {
		this.showUnSucessMessage = showUnSucessMessage;
	}
	
	/** holds the boolean flag for showing that the record is saved */
    private boolean showDelSucessMessage = false;

    /** holds the boolean flag for showing that the record is saved */
    private boolean showDescReadonly = false;

    /** holds the boolean flag for showing that the record is saved */
    private boolean disableDetach = false;

    /** Holds the row Index of the DataTable for Attachments */
    private int attachmentsRowIndex = 0;

    /** holds the index of Case Attachment DataTable */
    private int attachmentIndex;

    /**
     * This is used to render alertAuditOpen.
     */
    private boolean attachAuditOpen = false;

    /**
     * This is used to render Audit for Dormer cdodes for details page.
     */

    private boolean attachAuditRender = false;
    
    /**
     * to hold the  edmsWrkUnitLevelList 
     */
    private List edmsWrkUnitLevelList;
    
    /**
     * to hold the  edmsDocTypeList 
     */
    private List edmsDocTypeList;

    /** for fixing Defect ESPRD00611930
     *  save button disabled
     * */
    private boolean disableSave=false;
    
    
	/**
	 * @return Returns the disableSave.
	 */
	public boolean isDisableSave() {
		return disableSave;
	}
	/**
	 * @param disableSave The disableSave to set.
	 */
	public void setDisableSave(boolean disableSave) {
		this.disableSave = disableSave;
	}
    /**
     * audit log column value render
     */
    private boolean columnValueRender = false;
    
    //private boolean downLoadFile = false;
    
    /*private static final DistributedMap cache = DistributedMapFactory
    				.getMap("fileDownLoad");*/
    
	/**
	 * @return Returns the downLoadFile.
	 */
	/*public boolean isDownLoadFile() {
		if(cache != null)
		{
			logger.info("cache========"+cache);
			String Status = (String) cache.get("ATTACHDWNLD");
			cache.remove("ATTACHDWNLD");
			logger.info("Status====="+Status);
			if("ATTACHDWNLD".equalsIgnoreCase(Status)){
				downLoadFile = false;
			}
		}
		return downLoadFile;
	}*/
	/**
	 * @param downLoadFile The downLoadFile to set.
	 */
	/*public void setDownLoadFile(boolean downLoadFile) {
		this.downLoadFile = downLoadFile;
	}*/
    /**
     * 
     * @param columnValueRender
     */
    public void setColumnValueRender(boolean columnValueRender){
    	this.columnValueRender = columnValueRender;
    }
    
    /**
     * 
     * @return
     */
    public boolean getColumnValueRender(){
    	return columnValueRender;
    }
    
    /**
     * the selected Audit log
     */
    private AuditLog selectedAuditLog;
    
    /**
     * items per page
     */
    private int itemsPerPage = 10;
    
    /**
     * 
     * @return
     */
    public int getItemsPerPage(){
    	return itemsPerPage;
    }
    
    /**
     * 
     * @param auditLog
     */
    public void setSelectedAuditLog(AuditLog auditLog){
    	this.selectedAuditLog = auditLog;
    }
    
    /**
     * 
     * @return
     */
    public AuditLog getSelectedAuditLog(){
    	return selectedAuditLog;
    }
    
    
    /**
     * This is used to hold Former codes audit field history list for details
     * page.
     */
    
    private List attachAuditHistoryList = new ArrayList();

    /**
     * Constructor for CategoryDataBean
     */
    public AttachmentDataBean()
    {
        super();
        createAttachmentsList();

       }

    /**
     * This method is used to create attachment.
     */
    private void createAttachmentsList()
    {

        List attachmentsList = new ArrayList();
        List attachmentSks = new ArrayList();
        Correspondence correspondence = null;
        CMDelegate delegate = new CMDelegate();
        //Boolean set = Boolean.FALSE;
        List crXrefList = new ArrayList();
        String crn = null;
        CorrespondenceDataBean controllerBean = getCorrespondenceDataBean();
        if (controllerBean.getCorrespondenceRecordVO() != null
                && controllerBean.getCorrespondenceRecordVO()
                        .getCorrespondenceRecordNumber() != null)
        {
            crn = controllerBean.getCorrespondenceRecordVO()
                    .getCorrespondenceRecordNumber();
        }

        

        /** for hardcoding the Attachment object */

        /*
         * AttachmentVO attachmentVO = new AttachmentVO();
         * attachmentVO.setDateAdded("3rd july");
         * attachmentVO.setAddedBy("Anil");
         * attachmentVO.setFileName("AttachmentTest");
         * attachmentVO.setDescription("For testing Attachment edit");
         * attachmentsList.add(attachmentVO); this.attachmentList =
         * attachmentsList;
         */
        //Added if Condition for Find_Bugs-FIX
        if(attachmentList != null){
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("the attachmentList size :" + attachmentList.size());
        	}
        }
        if (crn != null && !crn.equals(ContactManagementConstants.EMPTY_STRING))
        {
            try
            {
                //	correspondence = delegate.getCorrespondenceDetails(new
                // Long("1"));
                correspondence = delegate.getCorrespondenceDetails(Long
                        .valueOf(crn));
            }
            catch (CorrespondenceRecordFetchBusinessException e)
            {
            	if(logger.isErrorEnabled())
            	{
            		logger.error(e.getMessage(), e);
            	}
                // e.printStackTrace();
            }
        }

        if (correspondence != null)
        {
            Set attachmentXref = correspondence.getCrAttachmentXrefs();

            if (attachmentXref != null)
            {
                for (Iterator iter = attachmentXref.iterator(); iter.hasNext();)
                {
                    CorrespondenceAttachmentXref element = (CorrespondenceAttachmentXref) iter
                            .next();
                    attachmentSks.add(element.getAttachmentSK());
                    CorrespondenceAttachmentXrefVO attachmentXrefVO = new CorrespondenceAttachmentXrefVO();
                    attachmentXrefVO.setAttachmentSk(element.getAttachmentSK());
                    attachmentXrefVO.setAttachIndicator(element
                            .getDetachedAttchmntIndicator());
                    crXrefList.add(attachmentXrefVO);
                }
            }
        }

        if (attachmentSks != null && !attachmentSks.isEmpty())
        {
            try
            {
                attachmentsList = delegate.getAttachmentDetails(attachmentSks);
            }
            catch (CorrespondenceRecordFetchBusinessException e)
            {
            	if(logger.isErrorEnabled())
            	{
            		logger.error(e.getMessage(), e);
            	}
                //e.printStackTrace();
            }
        }

        if (attachmentsList != null && !attachmentsList.isEmpty())
        {
            AttachmentDOConvertor convertor = new AttachmentDOConvertor();
            List tempList=convertor.convertAttachmentDOToVO(
                    attachmentsList, crXrefList);
            Iterator it=tempList.iterator();
            AttachmentVO attachmentVO = null;
            while(it.hasNext()){
            	attachmentVO=(AttachmentVO)it.next();
            	
            	attachmentVO.setNewAttachment(false);
            	attachmentList.add(attachmentVO);
            }
            /*this.attachmentList = convertor.convertAttachmentDOToVO(
                    attachmentsList, crXrefList);*/
        }

        if (this.attachmentList == null || this.attachmentList.isEmpty())
        {
            this.noData = true;
        }
    }

    /**
     * This method is to create an instance of Data Bean.
     * 
     * @return Returns CorrespondenceDataBean
     */
    private CorrespondenceDataBean getCorrespondenceDataBean()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        CorrespondenceDataBean correspondenceDataBean = (CorrespondenceDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + ContactManagementConstants.CORRESPONDENCE_BEAN_NAME + "}")
                .getValue(fc);
        return correspondenceDataBean;
    }

    /**
     * @return Returns the showAddAttachments.
     */
    public boolean isShowAddAttachments()
    {
        // logger.debug("in isShowAddAttachments() :" + showAddAttachments);
        return showAddAttachments;
    }

    /**
     * @param showAddAttachments
     *            The showAddAttachments to set.
     */
    public void setShowAddAttachments(boolean showAddAttachments)
    {
        //  logger.debug("in setShowAddAttachments :" + showAddAttachments);
        this.showAddAttachments = showAddAttachments;
    }

    /**
     * @return Returns the showEditAttachments.
     */
    public boolean isShowEditAttachments()
    {
        return showEditAttachments;
    }

    /**
     * @param showEditAttachments
     *            The showEditAttachments to set.
     */
    public void setShowEditAttachments(boolean showEditAttachments)
    {
        this.showEditAttachments = showEditAttachments;
    }

    /**
     * @return Returns the attachmentVO.
     */
    public AttachmentVO getAttachmentVO()
    {
        return attachmentVO;
    }

    /**
     * @param attachmentVO
     *            The attachmentVO to set.
     */
    public void setAttachmentVO(AttachmentVO attachmentVO)
    {
        this.attachmentVO = attachmentVO;
    }

    /**
     * @return Returns the attachmentList.
     */
    public List getAttachmentList()
    {

        return attachmentList;
    }

    /**
     * @param attachmentList
     *            The attachmentList to set.
     */
    public void setAttachmentList(List attachmentList)
    {

        this.attachmentList = attachmentList;

    }

    /**
     * @return Returns the imageRender.
     */
    public String getImageRender()
    {
        return imageRender;
    }

    /**
     * @param imageRender
     *            The imageRender to set.
     */
    public void setImageRender(String imageRender)
    {
        this.imageRender = imageRender;
    }

    /**
     * @return Returns the noData.
     */
    public boolean isNoData()
    {
        return noData;
    }

    /**
     * @param noData
     *            The noData to set.
     */
    public void setNoData(boolean noData)
    {
        this.noData = noData;
    }

    /**
     * @return Returns the attachmentsRowIndex.
     */
    public int getAttachmentsRowIndex()
    {
        return attachmentsRowIndex;
    }

    /**
     * @param attachmentsRowIndex
     *            The attachmentsRowIndex to set.
     */
    public void setAttachmentsRowIndex(int attachmentsRowIndex)
    {
        this.attachmentsRowIndex = attachmentsRowIndex;
    }

    /**
     * @return Returns the attachmentIndex.
     */
    public int getAttachmentIndex()
    {
        return attachmentIndex;
    }

    /**
     * @param attachmentIndex
     *            The attachmentIndex to set.
     */
    public void setAttachmentIndex(int attachmentIndex)
    {
        this.attachmentIndex = attachmentIndex;
    }

    /**
     * @return Returns the showSucessMessage.
     */
    public boolean isShowSucessMessage()
    {
        return showSucessMessage;
    }

    /**
     * @param showSucessMessage
     *            The showSucessMessage to set.
     */
    public void setShowSucessMessage(boolean showSucessMessage)
    {
        this.showSucessMessage = showSucessMessage;
    }

    /**
     * @return Returns the showDelSucessMessage.
     */
    public boolean isShowDelSucessMessage()
    {
        return showDelSucessMessage;
    }

    /**
     * @param showDelSucessMessage
     *            The showDelSucessMessage to set.
     */
    public void setShowDelSucessMessage(boolean showDelSucessMessage)
    {
        this.showDelSucessMessage = showDelSucessMessage;
    }

    /**
     * @return Returns the showDescReadonly.
     */
    public boolean isShowDescReadonly()
    {
        return showDescReadonly;
    }

    /**
     * @param showDescReadonly
     *            The showDescReadonly to set.
     */
    public void setShowDescReadonly(boolean showDescReadonly)
    {
        this.showDescReadonly = showDescReadonly;
    }

    /**
     * @return Returns the disableDetach.
     */
    public boolean isDisableDetach()
    {
        return disableDetach;
    }

    /**
     * @param disableDetach
     *            The disableDetach to set.
     */
    public void setDisableDetach(boolean disableDetach)
    {
        this.disableDetach = disableDetach;
    }

    /**
     * @return Returns the attachAuditHistoryList.
     */
    public List getAttachAuditHistoryList()
    {
        return attachAuditHistoryList;
    }

    /**
     * @param attachAuditHistoryList
     *            The attachAuditHistoryList to set.
     */
    public void setAttachAuditHistoryList(List attachAuditHistoryList)
    {
        this.attachAuditHistoryList = attachAuditHistoryList;
    }

    /**
     * @return Returns the attachAuditOpen.
     */
    public boolean isAttachAuditOpen()
    {
        return attachAuditOpen;
    }

    /**
     * @param attachAuditOpen
     *            The attachAuditOpen to set.
     */
    public void setAttachAuditOpen(boolean attachAuditOpen)
    {
        this.attachAuditOpen = attachAuditOpen;
    }

    /**
     * @return Returns the attachAuditRender.
     */
    public boolean isAttachAuditRender()
    {
        return attachAuditRender;
    }

    /**
     * @param attachAuditRender
     *            The attachAuditRender to set.
     */
    public void setAttachAuditRender(boolean attachAuditRender)
    {
        this.attachAuditRender = attachAuditRender;
    }
    /**
     * @return Returns the edmsDocTypeList.
     */
    public List getEdmsDocTypeList()
    {
        return edmsDocTypeList;
    }
    /**
     * @param edmsDocTypeList The edmsDocTypeList to set.
     */
    public void setEdmsDocTypeList(List edmsDocTypeList)
    {
        this.edmsDocTypeList = edmsDocTypeList;
    }
    /**
     * @return Returns the edmsWrkUnitLevelList.
     */
    public List getEdmsWrkUnitLevelList()
    {
        return edmsWrkUnitLevelList;
    }
    /**
     * @param edmsWrkUnitLevelList The edmsWrkUnitLevelList to set.
     */
    public void setEdmsWrkUnitLevelList(List edmsWrkUnitLevelList)
    {
        this.edmsWrkUnitLevelList = edmsWrkUnitLevelList;
    }
    
    private Set attachmentXrefVO;
	/**
	 * @return Returns the attachmentXrefVO.
	 */
	public Set getAttachmentXrefVO() {
		return attachmentXrefVO;
	}
	/**
	 * @param attachmentXrefVO The attachmentXrefVO to set.
	 */
	public void setAttachmentXrefVO(Set attachmentXrefVO) {
		this.attachmentXrefVO = attachmentXrefVO;
	}
	
	private boolean attachmentTrueVar = true;
	/**
	 * @return Returns the attachmentTrueVar.
	 */
	public boolean isAttachmentTrueVar() {
		return attachmentTrueVar;
	}
	/**
	 * @param attachmentTrueVar The attachmentTrueVar to set.
	 */
	public void setAttachmentTrueVar(boolean attachmentTrueVar) {
		this.attachmentTrueVar = attachmentTrueVar;
	}
	
	private boolean dupAttachmentChk = true;
	/**
	 * @return Returns the dupAttachmentChk.
	 */
	public boolean isDupAttachmentChk() {
		return dupAttachmentChk;
	}
	/**
	 * @param dupAttachmentChk The dupAttachmentChk to set.
	 */
	public void setDupAttachmentChk(boolean dupAttachmentChk) {
		this.dupAttachmentChk = dupAttachmentChk;
	}
	
	 /**
     * to hold the  render status for an Application 
     */
	private boolean applicationNameFlag = false;
	
	
	/**
	 * @return Returns the applicationNameFlag.
	 */
	public boolean isApplicationNameFlag() {
		return applicationNameFlag;
	}
	/**
	 * @param applicationNameFlag The applicationNameFlag to set.
	 */
	public void setApplicationNameFlag(boolean applicationNameFlag) {
		this.applicationNameFlag = applicationNameFlag;
	}
	
	
	/** ESPRD00825861 This flag is to hold the value for
	 *  validating whether attachment is added or modified. hence
	 *  the docfinity server call is managed accordingly.
	 **/
	private Boolean attachmentAddOrUpdateforCr = false;
	private int attachmentListSizeforCr;

	public Boolean getAttachmentAddOrUpdateforCr() {
		return attachmentAddOrUpdateforCr;
	}
	public void setAttachmentAddOrUpdateforCr(Boolean attachmentAddOrUpdateforCr) {
		this.attachmentAddOrUpdateforCr = attachmentAddOrUpdateforCr;
	}
	public int getAttachmentListSizeforCr() {
		return attachmentListSizeforCr;
	}
	public void setAttachmentListSizeforCr(int attachmentListSizeforCr) {
		this.attachmentListSizeforCr = attachmentListSizeforCr;
	}

}
