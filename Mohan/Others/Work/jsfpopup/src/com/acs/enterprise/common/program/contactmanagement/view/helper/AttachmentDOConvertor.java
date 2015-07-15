/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acs.enterprise.common.base.common.domain.Attachment;
import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceAttachmentXref;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.AttachmentControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceAttachmentXrefVO;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.config.ConfigurationLoader;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro This class will be used for converting CategoryVO to
 *         CorrespondenceCategory domain object and vice versa.
 */
public class AttachmentDOConvertor
        extends DataTransferObjectConverter
{
    /** Enterprise Logger for Logging. */
    /*private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(AttachmentDOConvertor.class);*/
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(AttachmentDOConvertor.class);

    /**
     * Creates constructor of AttachmentDOConvertor class.
     */
    public AttachmentDOConvertor()
    {
        super();
        
    }

    /**
     * Method to convert attachment to DO.
     * 
     * @param attachment :
     *            The attachment to set.
     * @return AttachmentVO.
     */
    public AttachmentVO convertAttachmentDO(Attachment attachment)
    {
        AttachmentVO attachmentVO = new AttachmentVO();

        attachmentVO.setAttachmentSK(attachment.getAttachmentSK());
        if(logger.isInfoEnabled())
        {
        	logger.info("AttachmentSK is :" + attachmentVO.getAttachmentSK());
        }
        if (attachment.getAttachmentCreatedDate() != null
                && !attachment.getAttachmentCreatedDate().toString().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            attachmentVO.setDateAdded(dateFormat.format(attachment
                    .getAttachmentCreatedDate()));
        }
        attachmentVO.setDescription(attachment.getAttachmentDescription());
        attachmentVO.setAddedBy(attachment.getAttachmentAddedByName());
        if(logger.isInfoEnabled())
        {
        logger.info("setAddedBy is :" + attachmentVO.getAddedBy());
        }
        attachmentVO.setFileName(attachment.getAttachmentFileName());
        if(logger.isInfoEnabled())
        {
        logger.info("setFileName is :" + attachmentVO.getFileName());
        }
        if (attachment.getAttachmentEDMSPageID() != null)
        {
            attachmentVO.setAttachmentPageID(attachment
                    .getAttachmentEDMSPageID());
        }

        if (attachmentVO.getAttachmentPageID() != null)
        {
        	
            AttachmentControllerBean controllerBean = new AttachmentControllerBean();
            attachmentVO.setFileUrl(controllerBean.generateURL(attachmentVO
                    .getAttachmentPageID()));
            if(logger.isDebugEnabled())
            {
            	logger.debug("the Url is :" + attachmentVO.getFileUrl());
            }
        }

        // attachment.
        attachmentVO.setDbRecord(true);

        attachmentVO
                .setAddedAuditTimeStamp(attachment.getAddedAuditTimeStamp());
        attachmentVO.setAddedAuditUserID(attachment.getAddedAuditUserID());
        attachmentVO.setAuditTimeStamp(attachment.getAuditTimeStamp());
        attachmentVO.setAuditUserID(attachment.getAuditUserID());
        attachmentVO.setVersionNo(attachment.getVersionNo());
        
        createVOAuditKeysList(attachment,attachmentVO);	
        
        return attachmentVO;
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
    	       	if(logger.isDebugEnabled())
    	       	{
    	       		logger.debug("======LineItemAuditsDelegate====resultList====Size======="+resultList.size());
    	       	}
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
     * Method to convert Do to Vo.
     * 
     * @param attachments :
     *            The attachments to set.
     * @param crXrefList :
     *            The crXrefList to set.
     * @return List.
     */
    public List convertAttachmentDOToVO(List attachments, List crXrefList)
    {
        List attachmentVos = new ArrayList();
        for (Iterator iter = attachments.iterator(); iter.hasNext();)
        {
            Attachment attachment = (Attachment) iter.next();
           
            AttachmentVO attachmentVO = new AttachmentVO();
            attachmentVO.setAttachmentSK(attachment.getAttachmentSK());
            
            if (attachment.getAttachmentCreatedDate() != null
                    && !attachment.getAttachmentCreatedDate().toString()
                            .equals(ContactManagementConstants.EMPTY_STRING))
            {
                DateFormat dateFormat = new SimpleDateFormat(
                        "MM/dd/yyyy hh:mm a");
                attachmentVO.setDateAdded(dateFormat.format(attachment
                        .getAttachmentCreatedDate()));

                /*
                 * DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy
                 * hh:mm a");
                 * attachmentVO.setDateAdded(Date.attachment.getAttachmentCreatedDate());
                 */
            }
            attachmentVO.setDescription(attachment.getAttachmentDescription());
            attachmentVO.setAddedBy(attachment.getAttachmentAddedByName());
            
            attachmentVO.setFileName(attachment.getAttachmentFileName());
            
            if (attachment.getAttachmentEDMSPageID() != null)
            {
                attachmentVO.setAttachmentPageID(attachment
                        .getAttachmentEDMSPageID());
            }

            // attachment.
            attachmentVO.setDbRecord(true);
            
            for (Iterator iterator = crXrefList.iterator(); iterator.hasNext();)
            {
            	
                CorrespondenceAttachmentXrefVO element = (CorrespondenceAttachmentXrefVO) iterator
                        .next();
                
                if (element.getAttachmentSk().equals(
                        attachmentVO.getAttachmentSK()))
                {
                    
                    if (element.getAttachIndicator() != null
                            && (!element.getAttachIndicator().equals(
                                    ContactManagementConstants.EMPTY_STRING)))
                    {
                    	
                        if (element.getAttachIndicator().booleanValue())
                        {
                            attachmentVO
                                    .setAttachmentIndicator(ContactManagementConstants.NO);
                            attachmentVO.setHistoryIndicator(true);
                        }
                        else
                        {
                            attachmentVO
                                    .setAttachmentIndicator(ContactManagementConstants.YES);
                        }
                    }
                }
            }
            attachmentVO.setAddedAuditTimeStamp(attachment
                    .getAddedAuditTimeStamp());
            attachmentVO.setAddedAuditUserID(attachment.getAddedAuditUserID());
            attachmentVO.setAuditTimeStamp(attachment.getAuditTimeStamp());
            attachmentVO.setAuditUserID(attachment.getAuditUserID());
            attachmentVO.setVersionNo(attachment.getVersionNo());
            attachmentVos.add(attachmentVO);
        }
        return attachmentVos;
    }

    /**
     * This method is used to convert the Case Attachments VO to Do
     * 
     * @param attachmentVO
     *            holds the AttachmentVO object
     * @param correspondence
     *            holds the correspondence object
     * @return it returns the Attachment DO
     */
    public CorrespondenceAttachmentXref convertCrAttachmentVOToDO(
            AttachmentVO attachmentVO, Correspondence correspondence,Set attachXrefVO)
    {
        
        Attachment attachment = new Attachment();
        CorrespondenceAttachmentXref attachCrossReference = new CorrespondenceAttachmentXref();
        if (attachmentVO.getFinalPath() != null
                && !attachmentVO.getFinalPath().equals(""))
        {
            attachment.setAttachmentFileName(attachmentVO.getFileName()); // Fixing value long for DB Col 
            attachment.setFinalPath(attachmentVO.getFinalPath());
        }
        if (attachmentVO.getFinalPath() == null
                && attachmentVO.getFileName() != null)
        {
            
            attachment.setAttachmentFileName(attachmentVO.getFileName());
        }
        
        if (attachmentVO.getDescription() != null)
        {
            if(attachmentVO.getDescription().length()>50)
            {
            	attachment.setAttachmentDescription(attachmentVO.getDescription().substring(0,50));
            }else {
        	    attachment.setAttachmentDescription(attachmentVO.getDescription());
            }
        }
        if(attachmentVO.getEdmsDocType() != null)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("EdmsDocType============"+attachmentVO.getEdmsDocType());
        	}
            attachment.setEdmsDocType(attachmentVO.getEdmsDocType());
            
        }
        if(attachmentVO.getEdmsWrkUnitLevel() != null)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("EdmsWrkUnitLevel========="+attachmentVO.getEdmsWrkUnitLevel());
        	}
            attachment.setEdmsWrkUnitLevel(attachmentVO.getEdmsWrkUnitLevel());
        }
        if (attachmentVO.getAttachmentPageID() != null)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("The edmsPageID is  "
                    + attachmentVO.getAttachmentPageID());
        	}
            attachment.setAttachmentEDMSPageID(attachmentVO
                    .getAttachmentPageID());
        }
        if (attachmentVO.getAddedBy() != null)
        {
        	if(logger.isDebugEnabled())
        	{
        	  logger.debug("The addedby user is " + attachmentVO.getAddedBy());
        	}
            attachment.setAttachmentAddedByName(attachmentVO.getAddedBy());
        }

        if (attachmentVO.getDateAdded() != null) {
            // attachment.setAttachmentCreatedDate(getTimeStamp());

            try {

                DateFormat dateFormat = new SimpleDateFormat(
                        "MM/dd/yyyy hh:mm a");
                attachment.setAttachmentCreatedDate(dateFormat
                        .parse(attachmentVO.getDateAdded()));

            } catch (Exception exception) {
            	if(logger.isErrorEnabled())
            	{
            		logger.error(exception.getMessage(), exception);
            	}
              //  e.printStackTrace();
            }
            
        }
        
        attachment.setCascadeKey(attachmentVO.getCascadeKey());
        if(logger.isDebugEnabled())
        {
        	logger.debug("AttachmentSK Before checking in convertCrAttachmentVOToDO() "+ attachmentVO.getAttachmentSK());
        }
        CorrespondenceAttachmentXrefVO correspondenceAttachmentXrefVO_new = null;
        if (attachmentVO.getAttachmentSK() != null)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("The AttachmentSK in convertCrAttachmentVOToDO :"+ attachmentVO.getAttachmentSK());
        	}
            attachCrossReference
                    .setAttachmentSK(attachmentVO.getAttachmentSK());
            attachment.setAttachmentSK(attachmentVO.getAttachmentSK());
            
            if(attachXrefVO!=null && !(attachXrefVO.isEmpty())){
            	
            Iterator attachXrefVO_It = attachXrefVO.iterator();
                while(attachXrefVO_It!= null && attachXrefVO_It.hasNext()){
                	
                	CorrespondenceAttachmentXrefVO correspondenceAttachmentXrefVO = (CorrespondenceAttachmentXrefVO)attachXrefVO_It.next();
                	if(correspondenceAttachmentXrefVO.getAttachmentSk()!=null && correspondenceAttachmentXrefVO.getAttachmentSk().longValue() == attachmentVO.getAttachmentSK().longValue()){
                			correspondenceAttachmentXrefVO_new = correspondenceAttachmentXrefVO;
                		break;
                	}
                }
            }
            
            
        }
        if (attachmentVO.getAuditUserID() != null)
        {
            attachment.setAuditUserID(attachmentVO.getAuditUserID());
        }
        else
        {
            attachment.setAuditUserID(getLoggedInUserID());
        }

        if (attachmentVO.getAuditTimeStamp() != null)
        {
            attachment.setAuditTimeStamp(attachmentVO.getAuditTimeStamp());
        }
        else
        {
            attachment.setAuditTimeStamp(getTimeStamp());
        }

        if (attachmentVO.getAddedAuditUserID() != null)
        {
            attachment.setAddedAuditUserID(attachmentVO.getAddedAuditUserID());
        }
        else
        {
            attachment.setAddedAuditUserID(getLoggedInUserID());
        }

        if (attachmentVO.getAddedAuditTimeStamp() != null)
        {
            attachment.setAddedAuditTimeStamp(attachmentVO
                    .getAddedAuditTimeStamp());
        }
        else
        {
            attachment.setAddedAuditTimeStamp(getTimeStamp());
        }

        attachment.setVersionNo(attachmentVO.getVersionNo());

        attachCrossReference.setAttachmentRef(attachment);
        /*
         * if (attachmentVO.getAttachmentSK() != null ) { logger.debug("The
         * AttachmentSK in convertCrAttachmentVOToDO :" +
         * attachmentVO.getAttachmentSK());
         * attachment.setAttachmentSK(attachmentVO.getAttachmentSK());
         * attachCrossReference.setAttachmentSK(); }
         */

        //   attachCrossReference.setCorrespondenceRef(correspondence);
        
        if (attachmentVO.isDbRecord())
        {
        	
            if (attachmentVO.getAttachmentIndicator() != null
                    && attachmentVO.getAttachmentIndicator().equals(
                            ContactManagementConstants.YES))
            {
            	if(logger.isDebugEnabled())
            	{
            		logger.debug("attachmentVO.getAttachmentIndicator is true-->"+attachmentVO.getAttachmentIndicator());
            	}
                attachCrossReference
                        .setDetachedAttchmntIndicator(Boolean.TRUE);
                if(logger.isDebugEnabled())
                {
                	logger.debug("after setting DetachedAttchmntIndicator to true -->"+attachCrossReference.getDetachedAttchmntIndicator());
                }
                attachCrossReference.setHistoryIndicator(Boolean.FALSE);
            }

            if (attachmentVO.getAttachmentIndicator() != null
                    && attachmentVO.getAttachmentIndicator().equals(
                            ContactManagementConstants.NO))
            {
            	
                attachCrossReference.setDetachedAttchmntIndicator(Boolean.FALSE);
                if(logger.isDebugEnabled())
                {
                	logger.debug("after setting DetachedAttchmntIndicator to false -->"+attachCrossReference.getDetachedAttchmntIndicator());
                }
                //attachment.setAttachmentFileName("");
                attachCrossReference.setHistoryIndicator(Boolean.valueOf(attachmentVO.isHistoryIndicator()));
            }
            //  attachCrossReference.setDetachedAttchmntIndicator(Boolean.FALSE);
        }
        else
        {
        	
            attachCrossReference.setDetachedAttchmntIndicator(Boolean.TRUE);
        }

        if (correspondenceAttachmentXrefVO_new != null &&
        		correspondenceAttachmentXrefVO_new.getAuditUserID() != null)
        {
            attachCrossReference.setAuditUserID(correspondenceAttachmentXrefVO_new.getAuditUserID());
        }
        else
        {
            attachCrossReference.setAuditUserID(getLoggedInUserID());
        }
        if (correspondenceAttachmentXrefVO_new != null)
        {
        	if (correspondenceAttachmentXrefVO_new.getAuditTimeStamp() != null) {
        		attachCrossReference.setAuditTimeStamp(correspondenceAttachmentXrefVO_new.getAuditTimeStamp());
        	} else {
        		attachCrossReference.setAuditTimeStamp(getTimeStamp());
        	}
             attachCrossReference.setVersionNo(correspondenceAttachmentXrefVO_new.getVersionNo());
        }
        else
        {
            attachCrossReference.setAuditTimeStamp(getTimeStamp());
        }

        if (correspondenceAttachmentXrefVO_new != null && 
        		correspondenceAttachmentXrefVO_new.getAddedAuditUserID() != null)
        {
            attachCrossReference.setAddedAuditUserID(correspondenceAttachmentXrefVO_new
                    .getAddedAuditUserID());
        }
        else
        {
            attachCrossReference.setAddedAuditUserID(getLoggedInUserID());
        }
        if (correspondenceAttachmentXrefVO_new != null && 
        		correspondenceAttachmentXrefVO_new.getAddedAuditTimeStamp() != null)
        {
            attachCrossReference.setAddedAuditTimeStamp(correspondenceAttachmentXrefVO_new
                    .getAddedAuditTimeStamp());
        }
        else
        {
            attachCrossReference.setAddedAuditTimeStamp(getTimeStamp());
        }
        //attachCrossReference.setVersionNo(attachmentVO.getVersionNo());
        return attachCrossReference;
    }

    /**
     * Method to return to correspondence controller. The method expects the files to be written to SAN/Shared Location already.
     * 
     * @param attchList
     * @param attchList
     *            holds the attchList object
     * @param correspondence
     *            holds the correspondence object
     * @return Set
     */
    public Set getAttachments(List attchList, Correspondence correspondence,Set attachXrefVO)
    {
        Set attachmentDOSet = new HashSet();
        AttachmentVO attachmentVO = null;
        int attachSize = attchList.size();
       // Removed Unused variable Start
      //  String uploadFilePath = null;
      //  String finalPathToWPS = null;
	// Removed Unused variable End
        CorrespondenceAttachmentXref attachmentXref = null;
        AttachmentDOConvertor convertor = new AttachmentDOConvertor();
        /*// Start Coomenting here for new upload code
        ConfigurationLoader cl = ConfigurationLoader.getInstance();
        Properties props = cl.getAttachmentConfigProperties();
        logger.debug("The Properties in fileupload is " + props);
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows"))
        {
            uploadFilePath = props
                    .getProperty(MaintainContactManagementUIConstants.WINDOWS_PATH);
        }
        else
        {
            uploadFilePath = props
                    .getProperty(MaintainContactManagementUIConstants.UNIX_PATH);
        }
        
        
        for (int i = 0; i < attachSize; i++)
        {
            attachmentVO = (AttachmentVO) attchList.get(i);
            if (attachmentVO.getFile1() != null
                    && !attachmentVO.getFile1().equals(""))
            {
                 */// End Coomenting here for new upload code
                /*
                 * File fileExists = new File(uploadFilePath +
                 * attachmentVO.getAddedBy()); if (!fileExists.exists()) {
                 * fileExists.mkdir(); }
                 */
                /*
                 * finalPathToWPS = uploadFilePath + attachmentVO.getAddedBy() +
                 * File.separator + attachmentVO.getFileName();
                 */
        		/*//  Start Coomenting here for new upload code
                finalPathToWPS = uploadFilePath + attachmentVO.getFileName();
               
                attachmentVO.setFinalPath(finalPathToWPS);
                File file1 = new File(finalPathToWPS);
                try
                {
                    byte[] byteArray = attachmentVO.getFile1();
                    FileOutputStream fs = new FileOutputStream(file1);
                    fs.write(byteArray);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        } */// End Coomenting here for new upload code
        for (int i = 0; i < attachSize; i++)
        {
            attachmentVO = (AttachmentVO) attchList.get(i);
            attachmentXref = convertor.convertCrAttachmentVOToDO(attachmentVO,
                    correspondence,attachXrefVO);
            attachmentDOSet.add(attachmentXref);
        }
        if (attachmentDOSet != null)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("The attachmentDOSet size :" + attachmentDOSet.size());
        	}
        }
        return attachmentDOSet;
    }

    /**
     * This method will get userid from Security.
     * 
     * @return String.
     */
    /*public String getUserID()
    {
        //String userId = ContactManagementConstants.TEST_USERID;
        String userId = "USERID1";
        //        FacesContext fc = FacesContext.getCurrentInstance();
        AttachmentControllerBean controllerBean = new AttachmentControllerBean();

        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = controllerBean.getUserData(renderRequest,
                renderResponse);

        if (eup != null && !isNullOrEmptyField(eup.getUserId()))
        {
            userId = eup.getUserId();
        }

        return userId;
    }*/
    public String getLoggedInUserID() {
		String userId = null;
		Principal principal = null ;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		if(portletRequest != null){
			
		 principal= portletRequest.getUserPrincipal();
		}
		if (principal == null) {
			userId = "guestUser";
		}
		else{
			userId = principal.getName();
		}
		
		return userId;
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
     *            String inputField.
     * @return boolean : true if input field is null or equal to an empty string
     *         else false.
     */
    private boolean isNullOrEmptyField(String inputField)
    {
        

        return (inputField == null || ContactManagementConstants.EMPTY_STRING
                .equalsIgnoreCase(inputField.trim()));
    }

    /**
     * This method converts AttachmentVO to AttachmentDO.
     * @param attachmentVO
     * Takes attachmentVO as param
     * @return Attachment
     */
    public Attachment convertCrAttachmentVOToDO(AttachmentVO attachmentVO)
    {
       
        Attachment attachment = new Attachment();

        if (attachmentVO.getFileName() != null)
        {
        	if(logger.isDebugEnabled())
        	{
        	logger.debug("The FileName is $$ " + attachmentVO.getFileName());
        	}
            attachment.setAttachmentFileName(attachmentVO.getFileName());
        }

        if (attachmentVO.getDescription() != null)
        {
            attachment.setAttachmentDescription(attachmentVO.getDescription());
            if(logger.isDebugEnabled())
            {
            logger.debug("the desc in convertCrAttachmentVOToDO : " + attachmentVO.getDescription());
            }
        }
        if(attachmentVO.getEdmsDocType() != null)
        {
            attachment.setEdmsDocType(attachmentVO.getEdmsDocType());
            if(logger.isDebugEnabled())
            {
            logger.debug("the EdmsDocType in convertCrAttachmentVOToDO : " + attachmentVO.getEdmsDocType());
            }
        }
        if(attachmentVO.getEdmsWrkUnitLevel() != null)
        {
            attachment.setEdmsWrkUnitLevel(attachmentVO.getEdmsWrkUnitLevel());
            if(logger.isDebugEnabled())
            {
            logger.debug("the EdmsDocType in convertCrAttachmentVOToDO : " + attachmentVO.getEdmsWrkUnitLevel());
            }
        }
        if (attachmentVO.getAttachmentPageID() != null)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("The edmsPageID is  " + attachmentVO.getAttachmentPageID());
        	}
            attachment.setAttachmentEDMSPageID(attachmentVO
                    .getAttachmentPageID());
        }
        if (attachmentVO.getAddedBy() != null)
        {
        	if(logger.isDebugEnabled())
        	{
        	 logger.debug("The addedby user is " + attachmentVO.getAddedBy());
        	}
            attachment.setAttachmentAddedByName(attachmentVO.getAddedBy());
        }

        if (attachmentVO.getDateAdded() != null)
        {
            attachment.setAttachmentCreatedDate(getTimeStamp());
        }
        if(logger.isDebugEnabled())
        {
        	logger.debug("AttachmentSK Before checking in convertCrAttachmentVOToDO() " + attachmentVO.getAttachmentSK());
        }
        if (attachmentVO.getAttachmentSK() != null)
        {

            attachment.setAttachmentSK(attachmentVO.getAttachmentSK());
        }
        if (attachmentVO.getAuditUserID() == null)
        {
            attachment.setAuditUserID(getLoggedInUserID());

        }
        else
        {
            attachment.setAuditUserID(attachmentVO.getAuditUserID());
        }
        attachment.setAuditTimeStamp(getTimeStamp());

        if (attachmentVO.getAddedAuditUserID() == null)
        {
            attachment.setAddedAuditUserID(getLoggedInUserID());
        }
        else
        {
            attachment.setAddedAuditUserID(attachmentVO.getAddedAuditUserID());
        }
        attachment.setAddedAuditTimeStamp(getTimeStamp());

        attachment.setVersionNo(attachmentVO.getVersionNo());

        return attachment;
    }
}
