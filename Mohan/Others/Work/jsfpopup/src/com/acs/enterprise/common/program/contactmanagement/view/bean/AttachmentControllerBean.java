/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

/**
 * @author Wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.PortletPreferences;

import org.apache.commons.beanutils.BeanUtils;

import com.acs.enterprise.common.audit.common.delegate.GlobalAuditsDelegate;
import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.base.common.domain.Attachment;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.cots.edms.application.common.vo.EDMSQuickSearchResultsVO;
import com.acs.enterprise.common.cots.edms.application.common.vo.EnterpriseEDMSSearchCriteriaVO;
import com.acs.enterprise.common.cots.edms.application.common.vo.EnterpriseEDMSSearchResultsVO;
import com.acs.enterprise.common.cots.edms.delegate.EDMSQuickSearchProcessDelegate;
import com.acs.enterprise.common.cots.edms.util.EDMSURLGeneratorImpl;
import com.acs.enterprise.common.cots.edms.util.exception.EDMSBaseException;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
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
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceAttachmentXref;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.AttachmentDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceAttachmentXrefVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.config.ConfigurationLoader;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.helper.VirusScanUtility;
import com.acs.enterprise.common.util.helper.intf.URLGenerator;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;
import com.acs.enterprise.common.program.administration.common.delegate.SystemListDelegate;

import com.ibm.faces.component.html.HtmlFileupload;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.faces.fileupload.util.ContentElement;
import com.acs.enterprise.common.cots.edms.application.exception.EDMSQuickSearchBusinessException; 
//import com.ibm.websphere.cache.DistributedMap;
//import com.ibm.ws.cache.spi.DistributedMapFactory;

/**
 * This is a controller class used for Contact Management Call script related
 * functionality in the presentation layer.
 */
public class AttachmentControllerBean extends EnterpriseBaseControllerBean {

	/**
	 * Generating object of EnterpriseLogger.
	 */
	/*private EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(AttachmentControllerBean.class);*/
	private static final EnterpriseLogger logger = EnterpriseLogFactory
	.getLogger(AttachmentControllerBean.class);
	
	

	/**
	 * Holds attachmentDataBean.
	 */
	//private AttachmentDataBean attachmentDataBean = getAttachmentDataBean();
	
	private AttachmentDataBean attachmentDataBean;

	/** ### coding for Attachments ### * */
	protected transient HtmlScriptCollector scriptCollector1;

	/** holds the HtmlForm object */
	protected transient HtmlForm form1;

	/** holds the HtmlFileUpload object */
	protected transient HtmlFileupload fileupload1;

	/* *//** holds the EDMS Search results */
	/*
	 * private String edmsSearchResultsList;
	 */

        /**
	 * This field is used to store whether user has permission.
	 */

        private boolean controlRequired = true;
        
        /** Holds the application name **/
    	private String appName = null;
        
        private String intialData;
        
        /** Instance of the cache */
        //private static final DistributedMap cache = DistributedMapFactory
         //       .getMap("statistics");
        
  
        
//      Added by Infinite while Performance Issues...
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
	 * This is the AttachmentControllerBean Constructor
	 */

	public AttachmentControllerBean() {

		super();
         /*                  getUserPermission();
		logger.debug("Inside AttachmentControllerBean constuctor");*/
	}

	
       
    
    /**
	 * @return Returns the controlRequired.
	 */
	public boolean isControlRequired() {
		return controlRequired;
	}
	/**
	 * @param controlRequired The controlRequired to set.
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}

    /**
     * This method gets the permission level for the logged in user
     *
     */	
    public void getUserPermission() {
    	String userPermission = "";
		String userid = getUserID();		
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try {
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(ContactManagementConstants.LOG_CORRESPONDENCE_PAGE, userid);
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPermission = userPermission != null ? userPermission.trim() : "";
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode",userPermission);
		
		// check the permission level for the user and set it in a boolean variable.  This will be used for Buttons/Links
		if(userPermission.equals("r")) {
			controlRequired = false;		
		}
	}
    
    /**
     * This method get the User ID
     * 
     * @return String
     */
    private String getUserID() {
		String userID = null;
		try {

			/*HttpServletRequest renderrequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(renderrequest, renderresponse);
			if (enterpriseUserProfile != null) {
				userID = enterpriseUserProfile.getUserId();
			}*/
			FacesContext facesContext = FacesContext.getCurrentInstance();
			PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
			userID = getLoggedInUserID(portletRequest);
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("LOGGED_IN_USERID", userID);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			}
		
		return userID;
	}
    
    private Timestamp getTimeStamp()
    {
        

        Calendar cal = Calendar.getInstance();

        return new Timestamp(cal.getTimeInMillis());
    }
         /**
         * This method is used to get the HtmlScriptCollector object
	 * 
	 * @return HtmlScriptCollector it will return HtmlScriptCollector object
	 */
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) ContactManagementHelper
					.findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	/**
	 * This method is used to get the HtmlForm object
	 * 
	 * @return HtmlForm it returns the HtmlForm object
	 */
	protected HtmlForm getForm1() {
		
		if (form1 == null) {
			form1 = (HtmlForm) ContactManagementHelper
					.findComponentInRoot("logCorrespondence");
		}
		return form1;
	}
	
	
	
	/**
	 * Write the contents into SAN/GPFS location. Uses timestamp in SAN directory to be created for staging the file.
	 * If GPFS not configured, test mode can be used by configuring JNDI parameters.
	 * In test mode, no timestamp is used in SAN directory name and file names are altered to one of configured names.
	 * @param fileContent
	 * @param fileName File name being attached (Not full path)
	 * @return
	 * @throws EnterpriseBaseBusinessException
	 */
	public String writeToSan(byte[] fileContent, String fileName) throws EnterpriseBaseBusinessException
	{
		logger.debug("writeToSan STARTED ");
	   String uploadFilePath = null;
       String finalPathToWPS = null;
      

		ConfigurationLoader cl = ConfigurationLoader.getInstance();
       Properties props = cl.getAttachmentConfigProperties();
       if(logger.isDebugEnabled())
       {
    	   logger.debug("The Properties in fileupload is " + props);
       }
       String osName = System.getProperty("os.name");
       if (osName.startsWith("Windows"))
       {
			
           uploadFilePath = props
                   .getProperty(MaintainContactManagementUIConstants.WINDOWS_PATH);
           logger.debug("uploadFilePath::  " + uploadFilePath);
       }
       else
       {
			
           uploadFilePath = props
                   .getProperty(MaintainContactManagementUIConstants.UNIX_PATH);
           logger.debug("uploadFilePath::  " + uploadFilePath);
       }
       
       //     Trimming to avoid any spaces in property value
       if(uploadFilePath != null)
       {
       		uploadFilePath = uploadFilePath.trim();
       }
       
       String uploadFileNameMode = "prod";
       String cmUploadFileNameModeJndi = "attachment_simulation_mode";
       String nonGpfsFileUploadDir = "";
       try {
			/** Holds the initial Context */
			InitialContext initialContext = new InitialContext();
			uploadFileNameMode =(String) initialContext.lookup(cmUploadFileNameModeJndi);
			logger.debug("uploadFileNameMode  :::"+ uploadFileNameMode);
			if(logger.isInfoEnabled())
			{
				logger.info("uploadFileNameMode::"+uploadFileNameMode);
			}

			
			
			if ((uploadFileNameMode != null) && (uploadFileNameMode.equalsIgnoreCase("test")))
			{
				nonGpfsFileUploadDir = props.getProperty(MaintainContactManagementUIConstants.NON_GPFS_FILE_UPLOAD_DIR);
				logger.debug("nonGpfsFileUploadDir  "+ nonGpfsFileUploadDir);
			}
        } catch (NamingException e1) {
        	if(logger.isInfoEnabled())
        	{
        		logger.info("Initial Context JNDI lookup failed:" + cmUploadFileNameModeJndi);	
        	}
			
        }

      //"FindBugs" issue fixed starts
        byte emptyArray3[]={};
       //if (fileContent != null  && !fileContent.equals(""))
        if (fileContent != null  && !Arrays.equals(fileContent, emptyArray3))
          //"FindBugs" issue fixed ends
       {
			
        	ExternalContext context  = FacesContext.getCurrentInstance().getExternalContext();
			PortletSession session = (PortletSession) context.getSession(true);
			String sanUserSessionDir = (String) session.getAttribute(ContactManagementConstants.LOG_CORR_ATTACHMENT_SAN_DIRECTORY);
			logger.debug("sanUserSessionDir  ::: "+ sanUserSessionDir);
			if (sanUserSessionDir == null)
			{
				
				//String userId = getUserInfo().getUserId(); //"Sample"; //getUserInfo().getUserId()
				FacesContext facesContext = FacesContext.getCurrentInstance();
				PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
				String userId = getLoggedInUserID(portletRequest);
	        	Format formatter = new SimpleDateFormat("MMddyyyyhhmmssSSS");
	        	String dateAppended = formatter.format(new Date());
	        	
	        	
	        	String uploadSanLocation = "";
	        	String uploadSanLocationJndi = "attachment_san_location";
	        	
	        	
	        	try {
	    			/** Holds the initial Context */
	    			InitialContext initialContext = new InitialContext();
	    			uploadSanLocation =(String) initialContext.lookup(uploadSanLocationJndi);
	    			
	    			if (uploadSanLocation == null)
	    			{
	    				uploadSanLocation = uploadFilePath; // If no JNDI lookup, use from property file.
	    			}
	    		} catch (NamingException e1) {
	    			if(logger.isInfoEnabled())
	    			{
	    				logger.info("Initial Context JNDI lookup failed:" + uploadSanLocationJndi);
	    			}
	    			uploadSanLocation = uploadFilePath; // If no JNDI lookup, use from property file.
	    		}  
	        	
	    		String dirToUse = uploadSanLocation + System.getProperty("file.separator") + userId +  dateAppended;
	    		logger.debug("dirToUse  ::: "+ dirToUse);
	    		
	    		if ((uploadFileNameMode != null) && (uploadFileNameMode.equalsIgnoreCase("test")))
	    		{
	    			dirToUse = uploadSanLocation + System.getProperty("file.separator") + nonGpfsFileUploadDir;
	    			logger.debug(" dirToUse ### "+ dirToUse);
	    		}
	    		if(logger.isDebugEnabled())
	    		{
	    			logger.debug("dirToUse:" + dirToUse);
	    		}
	    		
	        	File dirOnSan = new File(dirToUse);
	        	logger.debug(" dirOnSan "+dirOnSan);
	        	if (!dirOnSan.exists())
	        	{
					
	        		boolean dirCreated = dirOnSan.mkdir();
	        		if (!dirCreated)
	        		{
	        			if(logger.isErrorEnabled())
	        			{
	        				logger.error("SAN directory to write Attachments couldn't be created:" + dirToUse);
	        			}
	                	throw new EnterpriseBaseBusinessException("err.fileattach.write.ioexception", "SAN directory to write Attachments couldn't be created:" + dirToUse);
	        		} else
	        		{
											
	        		}
	        	}
	        	sanUserSessionDir = dirToUse;
	        	session.setAttribute(ContactManagementConstants.LOG_CORR_ATTACHMENT_SAN_DIRECTORY, dirToUse);
        		
			}
			else
			{
				if(logger.isDebugEnabled())
				{
					logger.debug("***** - sanUserSessionDir: " + sanUserSessionDir);
				}
				logger.debug("sanUserSessionDir ### "+ sanUserSessionDir);
			}

			if ((uploadFileNameMode != null) && (uploadFileNameMode.equalsIgnoreCase("test")))
			{
				
				String testFileName = "";
				// Test file names are used when writing in test mode. Using test file name 1 or 2 alternately
				if (getAttachmentDataBean().getAttachmentList().size()/2 == 0)
				{
					testFileName = props.getProperty(MaintainContactManagementUIConstants.NON_GPFS_FILE_UPLOAD_TEST_FILE_1);
					logger.debug("testFileName ### "+ testFileName);
				} else
				{
					testFileName = props.getProperty(MaintainContactManagementUIConstants.NON_GPFS_FILE_UPLOAD_TEST_FILE_2);
					logger.debug("testFileName $$$ "+ testFileName);
				}
				finalPathToWPS = sanUserSessionDir +  System.getProperty("file.separator") + testFileName;
				logger.debug("finalPathToWPS ### "+ finalPathToWPS);
				
			} else
			{
				finalPathToWPS = sanUserSessionDir +  System.getProperty("file.separator") + fileName;
				logger.debug("finalPathToWPS **** "+finalPathToWPS);
			}			
			if(logger.isDebugEnabled())
			{
				logger.debug("The final path to WPS is $$ " + finalPathToWPS );
			}

           
           File file1 = new File(finalPathToWPS);
          //"FindBugs" issue fixed starts
           FileOutputStream fs = null;
           try
           {
        	  // FileOutputStream fs = new FileOutputStream(file1);
        	   fs = new FileOutputStream(file1);
               fs.write(fileContent);
               fs.close();
             //"FindBugs" issue fixed ends
           }
           catch (IOException e)
           {
        	   if(logger.isErrorEnabled())
        	   {
        		   logger.error("IOException during file write to SAN:" + finalPathToWPS);
        	   }
           		throw new EnterpriseBaseBusinessException("err.fileattach.write.ioexception", "IOException during file write to SAN:" + finalPathToWPS);
           }
       } else {
    	   	if(logger.isErrorEnabled())
    	   	{
    	   		logger.error("Empty file content for " +  fileName + ". No file written.");
    	   	}
       		throw new EnterpriseBaseBusinessException("err.fileattach.file.size.zer", "Empty file content. File not uploaded.");
       }
        logger.debug("writeToSan END "+finalPathToWPS);
       return finalPathToWPS;
	}

	/**
	 * @param attachmentVO
	 * @param maxSizeInMB
	 * @param attachmentList
	 * @throws EnterpriseBaseBusinessException
	 */
	private void checkTotalSizeOfAttachedFiles(AttachmentVO attachmentVO, int maxSizeInMB, List attachmentList) throws EnterpriseBaseBusinessException {
		double totalSize = 0.0;
		for(int i=0; i < attachmentList.size(); i++)
		{
			AttachmentVO alreadyAttachedFile = (AttachmentVO) attachmentList.get(i);
			if(alreadyAttachedFile.getFile1() != null) // For attachments that were there in the queried CR, this will be null. Discussed with BA.
			{
				double size = alreadyAttachedFile.getFile1().length;
				totalSize = totalSize + size;
			}			
		}
		//"FindBugs" issue fixed starts
		byte emptyByte[]={};
		if (attachmentVO.getFile1() != null
               //&& !attachmentVO.getFile1().equals(""))
				&& !Arrays.equals(attachmentVO.getFile1(), emptyByte))
			//"FindBugs" issue fixed ends
       {
		 	totalSize = totalSize + attachmentVO.getFile1().length;
       }
		

		if (totalSize >= (maxSizeInMB * (Integer
				.parseInt(MaintainContactManagementUIConstants.MAX_ONE_MB_SIZE)))) {
			if(logger.isDebugEnabled())
			{
				logger.debug("The total size of files attached more than configured maximum. totalSize:" + totalSize);
			}
			throw new EnterpriseBaseBusinessException("err.fileattach.file.size.more", "The total size of files attached more than configured maximum. totalSize:" + totalSize);
		}
	}




	/**
	 * This method is used to save the Attachment information
	 *
	 * @return String
	 */

	public String uploadFile() throws EDMSQuickSearchBusinessException{
		String qryStatus = null;
		AttachmentVO attachment = getAttachmentDataBean().getAttachmentVO();

		
		if(getFileupload1()!=null
				&& getFileupload1().getFilename() !=null
				&& !getFileupload1().getFilename().equals("")
				&& getFileupload1().getFilename().length()!=0)
		{
			attachment.
				setFileName(getFileupload1().getFilename());
		}

		if(requiredFldsAvbl(attachment))
		{
			
			attachment.getAddedBy();
						
			EDMSQuickSearchProcessDelegate edmsQuickSearchProcessDelegate = null;
			edmsQuickSearchProcessDelegate = new EDMSQuickSearchProcessDelegate();
			
			String status = null;
			if (attachment.getEdmsDocType() != null
					&& attachment.getEdmsWrkUnitLevel() != null
					&& getUserID() != null) {

				status = edmsQuickSearchProcessDelegate.getEDMSPrivilegeQuery(
						getUserID(), attachment.getEdmsDocType(), attachment
								.getEdmsWrkUnitLevel());
			}
			
			
			if (status != null) {
				if (status.equals("1")) {
									
					try {
						String uploadFilePath = null;
						String description = null;
						

				HtmlFileupload file = null;

				if (getAttachmentDataBean().isShowAddAttachments()) {
					
					file = getFileupload1();
					

					if (file != null) {
						String fileName = file.getFilename();
						String mineType = file.getMimetype();
						if(logger.isDebugEnabled())
						{
							logger.debug("fileName ::: " + fileName);
							logger.debug("mineType ::: " + mineType);
						}

						ContentElement newFile = (ContentElement) file.getValue();

						if (newFile != null) {
							FacesContext fct = FacesContext.getCurrentInstance();
							fct
									.getApplication()
									.setMessageBundle(
											"com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CommonAttachment");

							ResourceBundle bundle = ContactManagementHelper
									.resourceBundle(fct);
							/* Code has Modified as part of CR ESPRD00800460 to restrict the files upload 
							 * from Desktop if file type is any one of these .jar,.exe,.dll,.war,.ear							
							 */
							/*String extension = bundle
							.getString("restricted.file.types");*/
							String[] totalExt = null;
							String fileExtension = null;
							if (fileName != null
									&& !(fileName.equalsIgnoreCase(""))) {
								int index = fileName.lastIndexOf(".");
								if (index != -1)
								{
									fileExtension = fileName.substring(index + 1, fileName.length());
								} else
								{
									fileExtension = "";
								}
								//fileExtension = fileName.substring(fileName
										//.lastIndexOf(".") + 1, fileName.length());
							}
							if(logger.isDebugEnabled())
							{
								logger.debug("fileExtension of user input ::"+ fileExtension);
							}
							SystemListDelegate systemListDelegate = new SystemListDelegate();
							boolean validFile = true;
							 List systemList = systemListDelegate.getSystemListDtlStrtVal(
									 Long.valueOf("183") ,"G1");
							 System.out.println("syslit 183 "+systemList);
							 
							 if(null!=systemList && !systemList.isEmpty() && systemList.contains(fileExtension))
							 {
								 validFile = false;
								 System.out.println("invaid file selecte"+fileExtension);
							 }
							 
							/*if ((extension != null)
									&& !(extension.equalsIgnoreCase(""))) {
								totalExt = extension.split(",");
							}*/

							//boolean validFile = true;

							/*if ((totalExt != null) && (totalExt.length > 0)) {

								for (int i = 0; i < totalExt.length && validFile; i++) {
									if (fileExtension != null
											&& !(fileExtension.equalsIgnoreCase(""))
											&& fileExtension
													.equalsIgnoreCase(totalExt[i])) {
										System.err.println("Invalid File Type --> " + fileExtension);
										validFile = false;
										break;
									}
								}
							}*/
							 System.out.println("AttachmentControllerBean ---->uploadFile()--->validFile ------>>"+validFile);
							if(logger.isDebugEnabled())
							{
								logger.debug("checking valid file ::: " + validFile);
							}

							if (validFile) {
								byte[] byteArray = newFile.getContentValue();
								if(byteArray == null || byteArray.length == 0) {
									
									throw new EnterpriseBaseBusinessException("err.fileattach.file.size.zero", "The attached file is null or size zero.");
								}

								int maxSizeInMB = 0;
								String maxSize = bundle
										.getString("acceptable.attach.file.size");

								if ((maxSize != null)
										&& !(maxSize.equalsIgnoreCase(""))) {
									maxSizeInMB = Integer.parseInt(maxSize);
								}

								if (byteArray.length <= (maxSizeInMB * (Integer
										.parseInt(MaintainContactManagementUIConstants.MAX_ONE_MB_SIZE)))) {
									if(logger.isDebugEnabled())
									{
									logger.debug("byteArray Size ::: "	+ byteArray.length);
									}

									//"FindBugs" issue fixed 
									/*logger.debug("byteArray in VO ::: "
											+ attachment.getFile1());*/

									if (attachment != null) {
										String filepath = attachment.getFileName();
										if(logger.isDebugEnabled())
										{
											logger.debug("filepath from VO is:"+ filepath);
										}

										boolean scanResult = true;

										checkTotalSizeOfAttachedFiles(attachment, maxSizeInMB, getAttachmentDataBean().getAttachmentList());


										//Scans the File for Virus
										//scanResult = checkFileForVirus(filepath);
										
										
										boolean skipScan = true;
										/*ConfigurationLoader cl = ConfigurationLoader.getInstance();
									    Properties attachProps = cl.getAttachmentConfigProperties();
									    String strSkipScan = attachProps.getProperty(MaintainContactManagementUIConstants.SKIP_SCAN);
										*/
										 String strSkipScan=null;

										 try{
										/** Holds the initial Context */
										InitialContext initialContext = new InitialContext();
										strSkipScan =(String) initialContext.lookup(MaintainContactManagementUIConstants.SKIP_SCAN);
									    }catch(Exception e){
									    	
									    	e.printStackTrace();
									    }
									    
									    if ((strSkipScan != null) && (strSkipScan.equalsIgnoreCase("true")))
									    {
									    	skipScan = true;
									    }
									    

										scanResult = VirusScanUtility.scanFile(byteArray, fileName, skipScan);

										if (scanResult) {

											description = attachment
													.getDescription();
											if(logger.isDebugEnabled())
											{
												logger.debug("Description is :"+ description);
											}

											Date atdate = new Date();
											Format formatter = new SimpleDateFormat(
													ContactManagementConstants.DATE_ADDED_FORMAT, Locale.getDefault());
											String attachDate = formatter
													.format(atdate);
											if(logger.isDebugEnabled())
											{
												logger.debug("date before set to VO:"+ attachDate);
											}
											attachment.setDateAdded(attachDate);
											attachment.setFile1(byteArray);
											
											FacesContext facesContext = FacesContext.getCurrentInstance();
											PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
											
											attachment.setAddedBy(getLoggedInUserID(portletRequest)); // setAddedBy("Sample")

											String fileSeparator = "\\"; //System.getProperty("file.separator"); -- Client on Windows
											fileName = fileName.substring(fileName
													.lastIndexOf(fileSeparator) + 1,
													fileName.length());

											String actualFileName = fileName
													.substring(0, fileName
															.lastIndexOf("."));

											String finalFileName = actualFileName
													+ "." + fileExtension;
											attachment.setFileName(finalFileName);

											String finalPathToWPS = writeToSan(byteArray, finalFileName);
											attachment.setFinalPath(finalPathToWPS);

											getAttachmentDataBean()
													.getAttachmentList().add(
															attachment);

											//  getAttachmentDataBean().setShowAttachmentDataTable(true);
											getAttachmentDataBean()
													.setShowAddAttachments(true);
											if(logger.isDebugEnabled())
											{
											logger.debug("List Size is:"
													+ getAttachmentDataBean()
															.getAttachmentList()
															.size());
											}

											getAttachmentDataBean()
													.setAttachmentVO(
															new AttachmentVO());
											attachmentDataBean.setShowSucessMessage(true);
													getAttachmentDataBean().setShowUnSucessMessage(false);
													
													
										} else
										{
											
											throw new EnterpriseBaseBusinessException("err.fileattach.file.virusscan.error", "File Virus scan returned error.");

										}

									}

								} else {
									ContactManagementHelper
											.setErrorMessage(
													MaintainContactManagementUIConstants.INVALID_FILE_SIZE,
													MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
													null, null);
								}
							} else {
								ContactManagementHelper
										.setErrorMessage(
												MaintainContactManagementUIConstants.INVALID_FILE_TYPE,
												MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
												null, null);
							}
						} else {
							ContactManagementHelper
									.setErrorMessage(
											MaintainContactManagementUIConstants.ATTACH_MESSAGE,
											MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
											null, null);
						}
					}
				} else if (getAttachmentDataBean().isShowEditAttachments()) {
					

					AttachmentVO tempAttachVO = getAttachmentDataBean()
							.getAttachmentVO();
					getAttachmentDataBean().getAttachmentList().add(
							(getAttachmentDataBean().getAttachmentIndex()),
							tempAttachVO);
					getAttachmentDataBean().getAttachmentList().remove(
							(getAttachmentDataBean().getAttachmentIndex()) + 1);
					ContactManagementHelper.setErrorMessage(
							MaintainContactManagementUIConstants.SAVE_SUCCESS_MSG,
							null, null, null);

					//getCMLogCaseDataBean().setShoCaseEventsMessages(true);
				}

						//resetAttachment();
					} catch (EnterpriseBaseBusinessException e) {
						e.printStackTrace();
						ContactManagementHelper
						.setErrorMessage(
								e.getErrorCode(),
								MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
								null, null);
						
					}
					catch (Exception e) {
						e.printStackTrace();
						ContactManagementHelper
						.setErrorMessage(
								"err.fileattach.write.ioexception",
								MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
								null, null);
						
					}
					
					
					
					qryStatus = ContactManagementConstants.RENDER_SUCCESS;
				} else if (status.equals("0")) {
					getAttachmentDataBean().setShowUnSucessMessage(true);
					getAttachmentDataBean().setShowSucessMessage(false);
					
					qryStatus = "";
				} else if (status.equals("CI")) {
					
					qryStatus = "";
				}
			} else {
				getAttachmentDataBean().setShowUnSucessMessage(true);
				getAttachmentDataBean().setShowSucessMessage(false);
				
				qryStatus = "";
				
			}
			
		}

		/* Fixes for defect id ESPRD00438904 Start*/		
		if (attachmentDataBean.getAttachmentList() != null
				&& !attachmentDataBean.getAttachmentList().isEmpty()) {
			attachmentDataBean.setNoData(false);
			// Added for the Defect ESPRD00748270 - start //
			ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
			contactManagementHelper.sortCorrespondenceAttachmentsComparator("Attach_DateAdded", "desc",
					attachmentDataBean.getAttachmentList());
		}// Added for the Defect ESPRD00748270 - END //
		/* Fixes for defect id ESPRD00438904 End*/
		/** Hard coding for running in local system *//*


		 * AttachmentVO attachment = attachmentDataBean .getAttachmentVO();



		 * AttachmentVO attachmentVO = new AttachmentVO(); /
		 * attachmentDataBean.setAttachmentVO(attachmentVO); Format formatter =
		 * new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()); Date atdate =
		 * new Date(); String attachDate = formatter.format(atdate);
		 * logger.debug("date before set to VO:" + attachDate); /
		 * attachment.setDescription("Testing attachment");
		 * attachmentVO.setFileName("attachment.doc");
		 * attachmentVO.setDateAdded(attachDate);
		 * attachmentVO.setAddedBy("vijaya");
		 * attachmentVO.setAttachmentIndicator(ContactManagementConstants.YES);
		 * attachmentVO.setDescription(attachmentDataBean.getAttachmentVO().getDescription());
		 * logger.debug("list size before " +
		 * attachmentDataBean.getAttachmentList().size());
		 * attachmentDataBean.getAttachmentList().add( attachmentVO);
		 * logger.debug("list size before " +
		 * attachmentDataBean.getAttachmentList().size());
		 * attachmentDataBean.setShowSucessMessage(true);
		 * attachmentDataBean.setAttachmentVO(new AttachmentVO());
		 * logger.debug("uploadFile.CMLogCaseControllerBean is Ended");
		 */

		//return ContactManagementConstants.RENDER_SUCCESS;
		return qryStatus;
	}
	
	/**
	 * This method uses the virus scan service to scan the file before upload.
	 * @param filepath
	 * @return returnValue
	 */
	/*   
	 private boolean checkFileForVirus(String filepath){
	 
	 boolean returnValue = true;
	 
	 CMProcessDelegate objCMProcessDeleagate = new CMProcessDelegate();
	 
	 int scanResponse = objCMProcessDeleagate.scanFile(new File(filepath));
	 
	 if (scanResponse == 0) 
	 {
	 logger.debug("File is Clean");
	 ContactManagementHelper.setErrorMessage(MaintainContactManagementUIConstants.SCAN_FILE_CLEAN,
	 MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
	 null, null);
	 
	 }
	 else if (scanResponse == -1) 
	 {
	 logger.debug("File is infected.");
	 ContactManagementHelper.setErrorMessage(MaintainContactManagementUIConstants.SCAN_FILE_INFECTED,
	 MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
	 null, null);
	 returnValue = false;
	 }
	 else 
	 {
	 logger.debug("Scan error.");
	 ContactManagementHelper.setErrorMessage(MaintainContactManagementUIConstants.SCAN_FILE_ERROR,
	 MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
	 null, null);
	 returnValue = false;
	 
	 } 
	 
	 return returnValue;
	 
	 }*/

	/**
	 * @param attachmentVO
	 * @return
	 */
	/*
	 * private boolean validateAttachment(AttachmentVO attachmentVO) { boolean
	 * flag = true; if (attachmentVO != null) { if
	 * (StringUtils.isNotEmpty(attachmentVO.getFileName())) { flag = false;
	 * ContactManagementHelper .setErrorMessage(
	 * MaintainContactManagementUIConstants.ATTACH_MESSAGE,
	 * MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID, null, null); } }
	 * return flag; }
	 */

	/**
	 * @param attchList
	 * @param record
	 * @return
	 */
	private Set getAttachments(List attchList, Correspondence correspondence) {
		Set attachmentDOSet = new HashSet();
		AttachmentVO attachmentVO = null;
		int attachSize = attchList.size();
		String uploadFilePath = null;
		String finalPathToWPS = null;
		CorrespondenceAttachmentXref attachmentXref = null;
		AttachmentDOConvertor convertor = new AttachmentDOConvertor();
		ConfigurationLoader cl = ConfigurationLoader.getInstance();
		Properties props = cl.getAttachmentConfigProperties();
		if(logger.isDebugEnabled())
		{
			logger.debug("The Properties in fileupload is " + props);
		}
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			uploadFilePath = props
					.getProperty(MaintainContactManagementUIConstants.WINDOWS_PATH);
		} else {
			uploadFilePath = props
					.getProperty(MaintainContactManagementUIConstants.UNIX_PATH);
		}
		for (int i = 0; i < attachSize; i++) {
			attachmentVO = (AttachmentVO) attchList.get(i);
			//"FindBugs" issue fixed starts
			byte emptyArray2[]={};
			if (attachmentVO.getFile1() != null
					//&& !(attachmentVO.getFile1().equals(" "))) {
					&& !Arrays.equals(attachmentVO.getFile1(), emptyArray2)) {
				//"FindBugs" issue fixed ends
				

				/*
				 * File fileExists = new File(uploadFilePath +
				 * attachmentVO.getAddedBy()); if (!fileExists.exists()) {
				 * fileExists.mkdir(); }
				 */
				/*
				 * finalPathToWPS = uploadFilePath + attachmentVO.getAddedBy() +
				 * File.separator + attachmentVO.getFileName();
				 */
				finalPathToWPS = uploadFilePath + attachmentVO.getFileName();
				

				attachmentVO.setFinalPath(finalPathToWPS);
				File file1 = new File(finalPathToWPS);
				//"FindBugs" issue fixed  starts
				FileOutputStream fs =null;
				try {
					byte[] byteArray = attachmentVO.getFile1();
					fs = new FileOutputStream(file1);
					fs.write(byteArray);
					fs.close();
					//"FindBugs" issue fixed  ends
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for (int i = 0; i < attachSize; i++) {
		    attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME);
			attachmentVO = (AttachmentVO) attchList.get(i);
			attachmentXref = convertor.convertCrAttachmentVOToDO(attachmentVO,
					correspondence,attachmentDataBean.getAttachmentXrefVO());
			attachmentDOSet.add(attachmentXref);
		}
		return attachmentDOSet;
	}

	/**
	 * This method is used to get the HtmlFileupload object
	 * 
	 * @return HtmlFileupload It returns the HtmlFileupload object
	 */
	protected HtmlFileupload getFileupload1() {
		if (fileupload1 == null) {
			fileupload1 = (HtmlFileupload) ContactManagementHelper
					.findComponentInRoot("fileupload1");
			if(logger.isDebugEnabled())
			{
				logger.debug("fileupload1 is ### " + fileupload1);
			}
		}
		return fileupload1;
	}

	/**
	 * This Method is used to View the Attachment details in EDIT (or) UPDATE
	 * JSP whenever the user clicks on the row in a Span in JSP.
	 * 
	 * @return String
	 */
	public String viewAttachments() {
		createValidValues();
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME);
		attachmentDataBean.setShowDelSucessMessage(false);
		attachmentDataBean.setShowSucessMessage(false);
		FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
		if (indexCode == null) {
			indexCode = "0";
		}
		int attchIndex = Integer.parseInt(indexCode);
		//"FindBugs" issue fixed
		//String attchIndexStr = String.valueOf(attchIndex);
		
		attachmentDataBean.setAttachmentIndex(attchIndex);
		

		// AttachmentVO attach = null;
		// AttachmentVO tempAttachment = new AttachmentVO();
		AttachmentVO attach = (AttachmentVO) attachmentDataBean
				.getAttachmentList().get(attchIndex);
				
		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setAddedAuditTimeStamp(attach.getAddedAuditTimeStamp());
		attachmentVO.setAddedAuditUserID(attach.getAddedAuditUserID());
		attachmentVO.setAddedBy(attach.getAddedBy());
		attachmentVO.setAnEDMSPageId(attach.getAnEDMSPageId());
		attachmentVO.setAttachmentIndicator(attach.getAttachmentIndicator());
		attachmentVO.setAttachmentPageID(attach.getAttachmentPageID());
		attachmentVO.setAttachmentSK(attach.getAttachmentSK());
		attachmentVO.setAuditTimeStamp(attach.getAuditTimeStamp());
		attachmentVO.setAuditUserID(attach.getAuditUserID());
		attachmentVO.setDateAdded(attach.getDateAdded());
		attachmentVO.setDbRecord(attach.isDbRecord());
		attachmentVO.setShowDeleteForSearched(attach.isShowDeleteForSearched());
		attachmentVO.setDescription(attach.getDescription());
		//attachmentVO.setDetachInd(attach.getD)
		attachmentVO.setEdmsDocType(attach.getEdmsDocType());
		attachmentVO.setEdmsStoredDate(attach.getEdmsStoredDate());
		attachmentVO.setEdmsWrkUnitLevel(attach.getEdmsWrkUnitLevel());
		attachmentVO.setFile1(attach.getFile1());
		attachmentVO.setFileName(attach.getFileName());
		attachmentVO.setFileUrl(attach.getFileUrl());
		attachmentVO.setFinalPath(attach.getFinalPath());
		attachmentVO.setHistoryIndicator(attach.isHistoryIndicator());
		attachmentVO.setVersionNo(attach.getVersionNo());
		
		//for fixing Defect ESPRD00611913
		attachmentVO.setNewAttachment(attach.isNewAttachment());
		
		UIComponent component = ContactManagementHelper.findComponentInRoot("attchmtAuditId");
		if(component!=null)
		{
			AuditHistoryTable auditHistoryTable = (AuditHistoryTable)component ;
			auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(false);
			
		}
		attachmentVO.setAuditKeyList(attach.getAuditKeyList());
		
		StringBuffer logMsg = new StringBuffer();
		logMsg.append("dbRecord:").append(attachmentVO.isDbRecord())
				.append(" ShowDeleteForSearched:").append(attachmentVO.isShowDeleteForSearched())
				.append(" AttachmentIndicator:").append(attachmentVO.getAttachmentIndicator())
				.append(" HistoryIndicator:").append(attachmentVO.isHistoryIndicator())
				.append(" fileUrl:").append(attachmentVO.getFileUrl());
		
		
		attachmentDataBean.setAttachmentVO(attachmentVO);

		/*
		 * try { BeanUtils.copyProperties(tempAttachment, attach); } catch
		 * (IllegalAccessException e) { logger.debug("IllegalAccessException"); }
		 * catch (InvocationTargetException e) {
		 * logger.debug("InvocationTargetException"); }
		 */
		//   attachmentDataBean.setAttachmentVO(tempAttachment);
		CorrespondenceControllerBean controllerBean = new CorrespondenceControllerBean();
		CorrespondenceDataBean dataBean = controllerBean
				.getCorrespondenceDataBean();
		dataBean.setCrSaved(false);

		
		if ((dataBean.getCorrespondenceRecordVO() != null
				&& dataBean.getCorrespondenceRecordVO().getCorrespondenceDetailsVO() != null
				&& dataBean.getCorrespondenceRecordVO().getCorrespondenceDetailsVO().getStatusCode() != null 
				&& dataBean.getCorrespondenceRecordVO().getCorrespondenceDetailsVO().getStatusCode().equalsIgnoreCase("C"))
				&& (attach.getAttachmentIndicator() != null && attach.getAttachmentIndicator().equals(ContactManagementConstants.NO))) {
			
			attachmentDataBean.setDisableDetach(true);
			//for fixing Defect ESPRD00611930
			attachmentDataBean.setDisableSave(true);
			//Fix for the Defect ID : ESPRD00725220......start
			
			attachmentDataBean.setShowDescReadonly(true);
			attachmentVO.setAttachmentIndicator(ContactManagementConstants.NO);
			
			//Fix for the Defect ID : ESPRD00725220......end
		} 
		//Code added below for fixing the defect ESPRD00772128 on 17/03/2012
		
		else if((dataBean.getCorrespondenceRecordVO() != null
				&& dataBean.getCorrespondenceRecordVO().getCorrespondenceDetailsVO() != null
				&& dataBean.getCorrespondenceRecordVO().getCorrespondenceDetailsVO().getStatusCode() != null 
				&& !(dataBean.getCorrespondenceRecordVO().getCorrespondenceDetailsVO().getStatusCode().equalsIgnoreCase("C")))
				&& (attach.getAttachmentIndicator() != null && attach.getAttachmentIndicator().equals(ContactManagementConstants.NO)))
		{
    		
			attachmentDataBean.setDisableDetach(true);
			attachmentVO.setAttachmentIndicator(ContactManagementConstants.NO);
			attachmentDataBean.setDisableSave(true);
			attachmentDataBean.setShowDescReadonly(true);
		}
    else {
    	
    		//for fixing Defect ESPRD00611930
			attachmentDataBean.setDisableSave(false);
			attachmentDataBean.setShowDescReadonly(false);
			attachmentDataBean.setDisableDetach(false);
			attachmentVO.setAttachmentIndicator(ContactManagementConstants.YES);
		}

		attachmentDataBean.setShowEditAttachments(true);
		attachmentDataBean.setShowAddAttachments(false);
		//showAuditLog();

		

		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Method used to save the edited attachment
	 * 
	 * @return String.
	 * @throws CorrespondenceRecordFetchBusinessException
	 *             thrown, if unable to fetch the Attachment object.
	 * @throws CorrespondenceRecordUpdateBusinessException
	 *             Throws if exception occurs
	 */
	public String saveEditedAttachment()
			throws CorrespondenceRecordUpdateBusinessException,
			CorrespondenceRecordFetchBusinessException {
	    attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME);
	
	  //"FindBugs" issue fixed
		//Boolean flag = Boolean.FALSE;
		AttachmentVO attachmentVO = attachmentDataBean.getAttachmentVO();
		attachmentVO.setAuditTimeStamp(null);
		int attchIndex = attachmentDataBean.getAttachmentIndex();
		
		if(requiredFldsAvblforEdit(attachmentVO))
		{
			attachmentVO.setAuditUserID(getUserID());
			attachmentVO.setAuditTimeStamp(new Date());
			
			//Code added for defect - ESPRD00825861 to chk for description
			AttachmentVO tempAttachVO = attachmentVO;
			AttachmentVO attach = (AttachmentVO) attachmentDataBean.getAttachmentList().get(attchIndex);
			
			if (!tempAttachVO.getDescription().equals(attach.getDescription())) 
			{
				attachmentDataBean.setAttachmentAddOrUpdateforCr(Boolean.TRUE);
			}
			//COde ended for defect - ESPRD00825861
			
			attachmentDataBean.getAttachmentList().set(attchIndex, attachmentVO);
			attachmentDataBean.setShowSucessMessage(true);
			
			/*
			 * attachmentDataBean.setAttachmentVO( new AttachmentVO());
			 */
	
			// AttachmentDOConvertor convertor = new AttachmentDOConvertor();
			//  CMDelegate delegate = new CMDelegate();
			//  Attachment attachment = null;
			if(logger.isDebugEnabled())
			{
				logger.debug("Attachment Sk is :" + attachmentVO.getAttachmentSK());
			}
			/*
			 * try { attachment =
			 * delegate.getAttachmentDetails(attachmentVO.getAttachmentSK());
			 * logger.debug("After method call in dontroller"); } catch
			 * (CorrespondenceRecordFetchBusinessException e) { throw new
			 * CorrespondenceRecordFetchBusinessException(e); } // Attachment
			 * attachmentRecv =
			 * convertor.convertAttachmentVOToDO(attachmentVO,attachment);
			 * logger.debug("the Desc in VO is :" + attachmentVO.getDescription());
			 * if (attachment != null) {
			 * attachment.setAttachmentDescription(attachmentVO.getDescription());
			 * try { flag = delegate.updateAttachment(attachment);
			 * logger.debug("After the Update Attachment() flag :" +
			 * flag.booleanValue()); } catch
			 * (CorrespondenceRecordUpdateBusinessException e) { throw new
			 * CorrespondenceRecordUpdateBusinessException(e); } } if
			 * (flag.booleanValue()) { logger.debug("*********** SUCCESS
			 * ************"); attachmentDataBean.setShowSucessMessage(true); }
			 */
		}
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is used to delete the attachment.
	 * 
	 * @return String
	 */
	public String deleteEditedAttachment() {
		

		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME);
		int index = attachmentDataBean.getAttachmentIndex();
		if(logger.isDebugEnabled())
		{
			logger.debug("Attachment index is : " + index);
			logger.debug("list size before deleting : "	+ attachmentDataBean.getAttachmentList().size());
		}
		attachmentDataBean.getAttachmentList().remove(index);
		if(logger.isDebugEnabled())
		{
			logger.debug("list size after deleting : "	+ attachmentDataBean.getAttachmentList().size());
		}

		attachmentDataBean.setShowDelSucessMessage(true);

		attachmentDataBean.setShowEditAttachments(false);
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is used to detach the attachment.
	 * 
	 * @return String
	 */
	public String detachEditedAttachment() {
		
		
//		CorrespondenceControllerBean controllerBean = new CorrespondenceControllerBean();
//		CorrespondenceDataBean dataBean = controllerBean
//		.getCorrespondenceDataBean();
//		dataBean.setCrSaved(false);
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		attachmentDataBean.setShowSucessMessage(false);
		AttachmentVO attachmentVO = attachmentDataBean.getAttachmentVO();
		attachmentVO.setAttachmentIndicator(ContactManagementConstants.NO);
		//Code added for defect - ESPRD00825861 to set the value to true to check the docfinity call
		attachmentDataBean.setAttachmentAddOrUpdateforCr(Boolean.TRUE);
		//Code Ended for defect - ESPRD00825861
		attachmentDataBean.setDisableDetach(true);
		//for fixing Defect ESPRD00611930
		attachmentDataBean.setDisableSave(true);
		//attachmentDataBean.setShowDelSucessMessage(true);
		//attachmentDataBean.setShowEditAttachments(false);
		Set attchmentXrefSet = getAttachmentDataBean().getAttachmentXrefVO();
		if(attchmentXrefSet != null)
		{
			Iterator itr = attchmentXrefSet.iterator();
			while(itr.hasNext())
			{
				CorrespondenceAttachmentXrefVO attachmentXrefVO = (CorrespondenceAttachmentXrefVO) itr.next();
				if(attachmentXrefVO.getAttachmentSk() != null && 
						attachmentXrefVO.getAttachmentSk().longValue() == attachmentVO.getAttachmentSK().longValue())
				{
					attachmentXrefVO.setAuditUserID(getUserID());
					attachmentXrefVO.setAuditTimeStamp(getTimeStamp());
					break;
				}
			}
		}
		
		int attchIndex = attachmentDataBean.getAttachmentIndex();
		if(logger.isInfoEnabled())
		{
			logger.info("index is :" + attchIndex);
		}
		attachmentVO.setAuditUserID(getUserID());		//ESPRD00449852_UC-PGM-CRM-054_14MAY2010
		
		attachmentDataBean.getAttachmentList().set(attchIndex, attachmentVO);		

		//for fixing Defect ESPRD00611930
		attachmentDataBean.setShowDescReadonly(true);

		/*
		 * if (attachmentDataBean.isShowAddAttachments()) {
		 * attachmentDataBean.setShowAddAttachments(false); } if
		 * (attachmentDataBean.isShowEditAttachments()) {
		 * attachmentDataBean.setShowEditAttachments(false); }
		 */
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is used to render the CANCEL add or update page of
	 * Attachments
	 * 
	 * @return String
	 */
	public String cancelAttachments() {
		
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
	
		/*
		 * if (attachmentDataBean.isShowAddAttachments()) {
		 * attachmentDataBean.setShowAddAttachments(false); }
		 */
		if (attachmentDataBean.isShowEditAttachments()) {
			attachmentDataBean.setShowEditAttachments(false);
		}
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Method to render attachment details.
	 * 
	 * @return Returns String
	 */
	public String addAttachment() {
		createValidValues();
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		//Modified for the Defect ID ....ESPRD00744885...start..Added to keep Description as blank on opening Add Attachment page 
		attachmentDataBean.getAttachmentVO().setDescription("");
		//Modified for the Defect ID ....ESPRD00744885...end
		attachmentDataBean.setShowDelSucessMessage(false);
		attachmentDataBean.setShowSucessMessage(false);
		attachmentDataBean.setShowAddAttachments(true);
		attachmentDataBean.setShowEditAttachments(false);
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is to reset the values in edit attachment mode.
	 * 
	 * @return Returns String
	 */
	public String resetEditAttachment() {
		
				
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		int index = attachmentDataBean.getAttachmentIndex();
		AttachmentVO attachVO = (AttachmentVO) attachmentDataBean
				.getAttachmentList().get(index);
		
		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setAddedAuditTimeStamp(attachVO.getAddedAuditTimeStamp());
		attachmentVO.setAddedAuditUserID(attachVO.getAddedAuditUserID());
		attachmentVO.setAddedBy(attachVO.getAddedBy());
		attachmentVO.setAnEDMSPageId(attachVO.getAnEDMSPageId());
		attachmentVO.setAttachmentIndicator(attachVO.getAttachmentIndicator());
		attachmentVO.setAttachmentPageID(attachVO.getAttachmentPageID());
		attachmentVO.setAttachmentSK(attachVO.getAttachmentSK());
		attachmentVO.setAuditTimeStamp(attachVO.getAuditTimeStamp());
		attachmentVO.setAuditUserID(attachVO.getAuditUserID());
		attachmentVO.setDateAdded(attachVO.getDateAdded());
		attachmentVO.setDbRecord(attachVO.isDbRecord());
		attachmentVO.setShowDeleteForSearched(attachVO.isShowDeleteForSearched());
		
		attachmentVO.setDescription(attachVO.getDescription());
		//attachmentVO.setDetachInd(attachVO.isD);
		attachmentVO.setEdmsDocType(attachVO.getEdmsDocType());
		attachmentVO.setEdmsStoredDate(attachVO.getEdmsStoredDate());
		attachmentVO.setEdmsWrkUnitLevel(attachVO.getEdmsWrkUnitLevel());
		attachmentVO.setFile1(attachVO.getFile1());
		attachmentVO.setFileName(attachVO.getFileName());
		attachmentVO.setFileUrl(attachVO.getFileUrl());
		attachmentVO.setFinalPath(attachVO.getFinalPath());
		attachmentVO.setHistoryIndicator(attachVO.isHistoryIndicator());
		attachmentVO.setVersionNo(attachVO.getVersionNo());
		
		/*Below line is added to show detach link in disable mode 
		 *when clicking reset after editing the  attachment for closed CR 
		 *for Defect - ESPRD00835439 */
		
		attachmentVO.setNewAttachment(attachVO.isNewAttachment());
		attachmentDataBean.setAttachmentVO(attachmentVO);
		
		attachmentDataBean.setShowDelSucessMessage(false);
		attachmentDataBean.setShowSucessMessage(false);
		
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This will create the valid values
	 */
	private void createValidValues() {
	    attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		InputCriteria inputCriteria = null;
		List list = null;
		//"FindBugs" issue fixed
		//HashMap map = new HashMap();
		HashMap map = null;
		ReferenceDataSearchVO referenceDataSearch = null;

		ReferenceServiceDelegate referenceServiceDelegate = null;

		ReferenceDataListVO referenceDataListVO = null;

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_DEPT_WORK_UNIT_SK);
		list = new ArrayList();
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_EDMS_DOC_TYPE_CD);
		list.add(inputCriteria);
		referenceDataSearch = new ReferenceDataSearchVO();
		referenceDataSearch.setInputList(list);
		referenceServiceDelegate = new ReferenceServiceDelegate();

		//        Pass the list to the delegate
		
		//"FindBugs" issue fixed
		//referenceDataListVO = new ReferenceDataListVO();
		try

		{

			referenceDataListVO = referenceServiceDelegate

			.getReferenceData(referenceDataSearch);

			//for displaying retrieved values

			map = referenceDataListVO.getResponseMap();

			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"

			+ ReferenceServiceDataConstants.G_DEPT_WORK_UNIT_SK);

			List wrkUnitLevelList = new ArrayList();
			List docTypeList = new ArrayList();

			for (Iterator i = list.iterator(); i.hasNext();)

			{
				ReferenceServiceVO refVo = (ReferenceServiceVO) i.next();
				wrkUnitLevelList.add(new SelectItem(refVo.getValidValueCode(),
						refVo.getValidValueCode() + "-"
								+ refVo.getShortDescription()));
			}
			attachmentDataBean.setEdmsWrkUnitLevelList(wrkUnitLevelList);

			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"

			+ ReferenceServiceDataConstants.G_EDMS_DOC_TYPE_CD);
			for (Iterator i = list.iterator(); i.hasNext();)

			{

				ReferenceServiceVO refVo = (ReferenceServiceVO) i.next();
				docTypeList.add(new SelectItem(refVo.getValidValueCode(), refVo
						.getValidValueCode()
						+ "-" + refVo.getShortDescription()));

			}
			attachmentDataBean.setEdmsDocTypeList(docTypeList);
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

	}

	/**
	 * This method is Used for resetting values in Add Attachment.
	 * 
	 * @return String.
	 */
	public String resetAddAttachment() {
		

		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );

		attachmentDataBean.setAttachmentVO(new AttachmentVO());

		//Below line of code added for the defect - ESPRD00834141 to clear the success message.
		attachmentDataBean.setShowSucessMessage(false);
		//Below line of code added for the defect - ESPRD00870392 to clear the error message.
		attachmentDataBean.setShowUnSucessMessage(false);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method will instantiate the data bean.
	 *
	 * @return Returns AttachmentDataBean
	 */
	private AttachmentDataBean getAttachmentDataBean() {


		FacesContext fc = FacesContext.getCurrentInstance();
		AttachmentDataBean attachmentDataBean = (AttachmentDataBean) fc
				.getApplication().createValueBinding(
						"#{" + ContactManagementConstants.ATTACHMENT_BEAN_NAME + "}").getValue(fc);

		//"FindBugs" issue fixed
		//long exitTime = System.currentTimeMillis();


		return attachmentDataBean;
	}

	/**
	 * This method is used to sort Case Attachments span information based on
	 * sorted column and order.
	 * 
	 * @param event
	 *            ActionEvent : It catches the user action event
	 * @return String : It will return success.
	 */
	/*
	 * public String sortCaseAttachmentsInfo(ActionEvent event) { String
	 * sortColumn = (String) event.getComponent().getAttributes().get(
	 * MaintainContactManagementUIConstants.CLOUMN_NAME); String sortOrder =
	 * (String) event.getComponent().getAttributes().get(
	 * MaintainContactManagementUIConstants.SORT_ORDER); logger.debug("The Sort
	 * column is @ sortCaseAttachmentsInfo--->" + sortColumn); logger
	 * .debug("the sort order is @ sortCaseAttachmentsInfo--->" + sortOrder);
	 * attachmentDataBean.setImageRender(event.getComponent().getId());
	 * sortCaseAttachmentsComparator(sortColumn, sortOrder,
	 * attachmentDataBean.getAttachmentList()); return
	 * MaintainContactManagementUIConstants.SUCCESS; }
	 *//**
	 * This method creates a new Comparator Class and overrides the int
	 * compare() to sort the Case Attachments in the Databean.
	 * 
	 * @param sortColumn
	 *            String : name of the column to de sort.
	 * @param sortOrder
	 *            String : Ordering of the sort (asc / desc)
	 * @param dataList
	 *            List : List to be sort.
	 */
	/*
	 * private void sortCaseAttachmentsComparator(final String sortColumn, final
	 * String sortOrder, List dataList) { logger
	 * .debug("sortCaseAttachmentsComparator @ CMLogCaseControllerBean
	 * started"); Comparator comparator = new Comparator() { public int
	 * compare(Object obj1, Object obj2) { AttachmentVO data1 = (AttachmentVO)
	 * obj1; AttachmentVO data2 = (AttachmentVO) obj2; boolean ascending =
	 * false; if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
	 * .equals(sortOrder)) { ascending = true; } else { ascending = false; } if
	 * (sortColumn == null) { return 0; } if
	 * (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_DATEADDED
	 * .equals(sortColumn)) { if (null == data1.getDateAdded()) { data1
	 * .setDateAdded(MaintainContactManagementUIConstants.EMPTY_STRING); } if
	 * (null == data2.getDateAdded()) { data2
	 * .setDateAdded(MaintainContactManagementUIConstants.EMPTY_STRING); }
	 * return ascending ? data1.getDateAdded().compareTo( data2.getDateAdded()) :
	 * data2.getDateAdded() .compareTo(data1.getDateAdded()); } if
	 * (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_ADDEDBY
	 * .equals(sortColumn)) { if (null == data1.getAddedBy()) { data1
	 * .setAddedBy(MaintainContactManagementUIConstants.EMPTY_STRING); } if
	 * (null == data2.getAddedBy()) { data2
	 * .setAddedBy(MaintainContactManagementUIConstants.EMPTY_STRING); } return
	 * ascending ? data1.getAddedBy().trim().compareTo(
	 * data2.getAddedBy().trim()) : data2.getAddedBy()
	 * .trim().compareTo(data1.getAddedBy().trim()); } if
	 * (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_FILENAME
	 * .equals(sortColumn)) { if (null == data1.getFileName()) { data1
	 * .setFileName(MaintainContactManagementUIConstants.EMPTY_STRING); } if
	 * (null == data2.getFileName()) { data2
	 * .setFileName(MaintainContactManagementUIConstants.EMPTY_STRING); } return
	 * ascending ? data1.getFileName().trim().compareTo(
	 * data2.getFileName().trim()) : data2.getFileName()
	 * .trim().compareTo(data1.getFileName().trim()); } if
	 * (MaintainContactManagementUIConstants.CASEATTACHMENTS_ATTACH_DESC
	 * .equals(sortColumn)) { if (null == data1.getDescription()) { data1
	 * .setDescription(MaintainContactManagementUIConstants.EMPTY_STRING); } if
	 * (null == data2.getDescription()) { data2
	 * .setDescription(MaintainContactManagementUIConstants.EMPTY_STRING); }
	 * return ascending ? data1.getDescription().trim().compareTo(
	 * data2.getDescription().trim()) : data2
	 * .getDescription().trim().compareTo( data1.getDescription().trim()); }
	 * return 0; } }; Collections.sort(dataList, comparator); }
	 */

	/**
	 * This method is used to send the IPC details to the EDMS
	 * 
	 * @return String
	 */
	public String openDocumentRepository() {
		
		CorrespondenceControllerBean controllerBean = new CorrespondenceControllerBean();
		CorrespondenceDataBean bean = controllerBean
		.getCorrespondenceDataBean();
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME);
		if(!bean.isSrchDocRepSecurity())
		{
			try {
				EnterpriseEDMSSearchCriteriaVO edmsSearchCriteiaVo = new EnterpriseEDMSSearchCriteriaVO();
				/*String correspondenceSK = "";
				if (bean.getCorrespondenceRecordVO() != null
						&& bean.getCorrespondenceRecordVO()
								.getCorrespondenceRecordNumber() != null) {
					correspondenceSK = bean.getCorrespondenceRecordVO()
							.getCorrespondenceRecordNumber();
				}
				logger.debug("correspondenceSK in openDocumentRepository() :"
						+ correspondenceSK);
				if(correspondenceSK == null || "".equalsIgnoreCase(correspondenceSK))
				{
					controllerBean.saveCorrespondence();
				}*/
				if(FacesContext.getCurrentInstance().getMaximumSeverity() != null)
				{
					return "";
				}
				
				attachmentDataBean.setDupAttachmentChk(true);
				bean.setCrSaved(false);
				
				/*if (!correspondenceSK
						.equals(ContactManagementConstants.EMPTY_STRING)) {*/
					edmsSearchCriteiaVo.setModuleName("Contact Management");
					edmsSearchCriteiaVo.setFuncAreaCode("G1");
					edmsSearchCriteiaVo
							.setActionParameterName("logCREDMSSearchResultsList");
	
					FacesContext facesContext = FacesContext.getCurrentInstance();
					Map requestScope = (Map) facesContext.getApplication()
							.createValueBinding("#{requestScope}").getValue(
									facesContext);
					requestScope.put("EDMSSearchCriteria", edmsSearchCriteiaVo);
					attachmentDataBean.setShowAddAttachments(false);
					attachmentDataBean.setShowEditAttachments(false);
					// Added for Defect ESPRD00744866 - start
					attachmentDataBean.setShowDelSucessMessage(false);
					// Added for Defect ESPRD00744866 - end
				
				}
			//}
			/* 
			 Author: Infinite 
			 *	CR 2196 
			 *  Date : 015/01/2009 */
			catch (Exception ex) {
				//ex.printStackTrace();
				
				setErrorMessage(ContactManagementConstants.ERR_SW_SEVERE_FAILURE,
						new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
			}
		}
		else
		{
			setErrorMessage(ContactManagementConstants.ATMT_SEC_MSG,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
		}
		
		return "";
	}

	
	/**
	 * This method is used to send the IPC details to the EDMS
	 * 
	 * @return String
	 */
	public String openAddDocumentRepository() {
		
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		EnterpriseEDMSSearchCriteriaVO edmsSearchCriteiaVo = new EnterpriseEDMSSearchCriteriaVO();
		edmsSearchCriteiaVo.setModuleName("Contact Management");
		edmsSearchCriteiaVo.setFuncAreaCode("G1");
		edmsSearchCriteiaVo
				.setActionParameterName("logCREDMSSearchResultsList");

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map requestScope = (Map) facesContext.getApplication()
				.createValueBinding("#{requestScope}").getValue(
						facesContext);
		requestScope.put("EDMSSearchCriteria", edmsSearchCriteiaVo);
		attachmentDataBean.setShowAddAttachments(false);
		attachmentDataBean.setShowEditAttachments(false);
	
		return "";
	}
	/**
	 * This method is used to get the EDMS search results. this is used in IPC
	 * with EDMS
	 * 
	 * @return Returns the commonEDMSSearchResultsList.
	 */
	public void getSearchRepositoryResultsList() {
		

		
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		List edmsResults = null;
		EDMSQuickSearchProcessDelegate quickSearchProcessDelegate = null;
		Map fileinfoMap = null;
		String cascadeStr = null;
		String fileContent = null;
		Set keySet= null;
		String fileExtn = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)fc.getExternalContext().getRequest();
		//EnterpriseUserProfile profile = getUserInfo();
		
		
		if (fc.getExternalContext().getRequestMap() != null && attachmentDataBean.isDupAttachmentChk()) {
			
			//need to change the param name
         List tempAttachList = new ArrayList(attachmentDataBean.getAttachmentList());
			Object obj = fc.getExternalContext().getRequestMap().get(
					"logCREDMSSearchResultsList");
			
			if (obj != null) {
				EnterpriseEDMSSearchResultsVO searchResultsVO = new EnterpriseEDMSSearchResultsVO();
				//need to change the param name
				try {
					quickSearchProcessDelegate = new EDMSQuickSearchProcessDelegate();
					BeanUtils.copyProperties(searchResultsVO, obj);
					/*fc.getExternalContext().getRequestMap().remove(
							"logCREDMSSearchResultsList");*/

				} catch (Exception e) {
					if(logger.isErrorEnabled())
					{
						logger.error(e.getMessage(), e);
					}
				}

				/*
				 * EnterpriseEDMSSearchResultsVO searchResultsVO =
				 * (EnterpriseEDMSSearchResultsVO) fc
				 * .getExternalContext().getRequestMap().get(
				 * "logCREDMSSearchResultsList");
				 */
				

				if (searchResultsVO != null) {
					
					edmsResults = searchResultsVO.getSearchResultsList();
					if (edmsResults != null) {
						if(logger.isDebugEnabled())
						{
							logger.debug("EDMS search results list size is ##--## "	+ edmsResults.size());
						}
						Iterator iter = edmsResults.iterator();
						while( iter.hasNext()) {
							
							EDMSQuickSearchResultsVO resultsVO = new EDMSQuickSearchResultsVO();
							Object innerObj = (Object) iter.next();
							try {
								BeanUtils.copyProperties(resultsVO, innerObj);
							} catch (Exception e) {
								
							}
							
							if (resultsVO != null) {
								boolean flag = false;
								
								if (tempAttachList != null
										&& !tempAttachList.isEmpty()
										&& resultsVO.getPageId() != null
										&& !resultsVO
												.getPageId()
												.equals(
														ContactManagementConstants.EMPTY_STRING)) {
									
									Iterator iterator = tempAttachList.iterator(); 
									while(iterator.hasNext()) {
										AttachmentVO element = (AttachmentVO) iterator
												.next();
                  
                  //Code Modified for fixing the defect ESPRD00772128 on 16/03/2012
									/*	if (element.getAttachmentPageID() != null
												&& resultsVO
														.getPageId()
														.equals(
																element
																		.getAttachmentPageID()))  */
                                    
                      if (element.getAttachmentPageID() != null && resultsVO.getPageId()
												.equals(element.getAttachmentPageID()) && element.getAttachmentIndicator() != null
												&& element.getAttachmentIndicator().equals(ContactManagementConstants.YES)){
											
											flag = true;
										}
									}
								}
								if (flag) {
									/*
									 * setErrorMessage("err-duplicate-attachment",
									 * new Object[] {"Attachment" },
									 * "com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CommonAttachment" ,
									 * null);
									 */
									setMessage("err-duplicate-attachment");
								} else {
									AttachmentVO attachmentVO = new AttachmentVO();
									

									attachmentVO.setDescription(resultsVO
											.getDescription());
									attachmentVO.setFileName(resultsVO
											.getFileName());
									/*if (resultsVO.getPageId() != null
										&& !resultsVO.getPageId().equals(
										ContactManagementConstants.EMPTY_STRING)) 
									{
										attachmentVO.setAttachmentPageID(resultsVO.getPageId());
									}*/
									
									fileinfoMap = quickSearchProcessDelegate.getSDRCasecadeStructure(resultsVO.getPageId());
									if(fileinfoMap!=null)
									{	
											cascadeStr = (String)fileinfoMap.get("CASCADE");
											logger.debug("cascadeStr  "+  cascadeStr);
											cascadeStr = "ACS_NH/"+cascadeStr;
											keySet = fileinfoMap.keySet();
											Iterator it = keySet.iterator();
					    		    		while(it.hasNext())
					    		    		{
					    		    			fileExtn = (String)it.next();
					    		    			logger.debug(" fileExtn :::   "+ fileExtn );
					    		    			if(!"CASCADE".equals(fileExtn))
					    		    			{	
					    		    				fileContent = (String)fileinfoMap.get(fileExtn);
					    		    				
					    		    			}	
					    		    		}
					    		    		logger.debug(" Byte array :::   "+ fileContent.getBytes());
					    		    		try
					    		    		{
					    		    			String finalPathToWPS = writeToSan(fileContent.getBytes(), resultsVO.getFileName());
					    		    			logger.debug("finalPathToWPS  :: "+ finalPathToWPS);
					    		    			logger.debug("cascadeStr Final :: "+ cascadeStr);
					    		    			attachmentVO.setFinalPath(finalPathToWPS);
					    		    			attachmentVO.setCascadeKey(cascadeStr);
					    		    		}
					    		    		catch(Exception sanException)
					    		    		{
					    		    			logger.debug(" Exception while writing to SAN Location" +
					    		    				" :::   "+ sanException.getMessage());
					    		    		    sanException.printStackTrace();
					    		    		}
									}

									//Set FileUrl
									if (attachmentVO.getAttachmentPageID() != null) {
										attachmentVO
												.setFileUrl(generateURL(attachmentVO
														.getAttachmentPageID()));
									}

									/** for use on server */
									if (getLoggedInUserID(portletRequest) != null) {
										attachmentVO.setAddedBy(getLoggedInUserID(portletRequest));
									}
									DateFormat dateFormat = new SimpleDateFormat(
											"MM/dd/yyyy hh:mm a");
									attachmentVO.setDateAdded(dateFormat
											.format(new Date()));
									//     attachmentVO.setDateAdded(new Date());
									attachmentVO.setDbRecord(true);
									attachmentVO.setShowDeleteForSearched(true);
									
									attachmentVO
											.setAttachmentIndicator(ContactManagementConstants.YES);
									attachmentDataBean.getAttachmentList()
											.add(attachmentVO);
								}
							}
						}

					}

				}

			}
			attachmentDataBean.setDupAttachmentChk(false);
		}
		// Added for the Defect ESPRD00748270 - start //
		if (attachmentDataBean.getAttachmentList() != null
				&& !attachmentDataBean.getAttachmentList().isEmpty()) {
			attachmentDataBean.setNoData(false);
			ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
			contactManagementHelper.sortCorrespondenceAttachmentsComparator("Attach_DateAdded", "desc",
					attachmentDataBean.getAttachmentList());
		} // Added for the Defect ESPRD00748270 - END //
		//  return ContactManagementConstants.RENDER_SUCCESS;
		
	}

	/**
	 * Method to generate the URL based on pageID.
	 * 
	 * @return
	 */
	public String generateURL(String pageID) {
		

		String fileUrl = "";
		try {
			
			URLGenerator urlGenerator = new EDMSURLGeneratorImpl();
			fileUrl = urlGenerator.getURL(pageID);
			if(logger.isDebugEnabled())
			{
				logger.debug("File Url is ....." + fileUrl);
			}

		} catch (EDMSBaseException edmsBaseException) {
			if(logger.isErrorEnabled())
			{
				logger.error(edmsBaseException.getMessage(), edmsBaseException);
			}
		}
		
		return fileUrl;
	}

	/**
	 * Method to get the details of the user logged-In.
	 * 
	 * @return EnterpriseUserProfile.
	 */
	/*public EnterpriseUserProfile getUserInfo() {
		

		HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderresponse = null;
		EnterpriseUserProfile enterpriseUserProfile = getUserData(
				renderrequest, renderresponse);
		
		return enterpriseUserProfile;
	}*/

	/**
	 * @param logCaseEDMSSearchResultsList
	 *            The commonEDMSSearchResultsList to set.
	 */
	/*
	 * public void setEdmsSearchResultsList( String edmsSearchResultsList) {
	 * this.edmsSearchResultsList = edmsSearchResultsList; }
	 */

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
			String messageBundle, String componentId) {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		Locale locale = root.getLocale();
		String clientId = null;

		facesContext.getApplication().setMessageBundle(messageBundle);
		String errorMsg = MessageUtil.format(messageBundle, errorName,
				arguments, locale);
		FacesMessage fc = new FacesMessage();
		fc.setDetail(errorMsg);

		if (componentId != null) {
			UIComponent uiComponent = findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);
		}

		facesContext.addMessage(clientId, fc);
		
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
	public UIComponent findComponent(UIComponent base, String id) {
		
		// Is the "base" component itself the match we are looking for?
		if (id.equals(base.getId())) {
			return base;
		}

		// Search through our facets and children
		UIComponent component = null;
		UIComponent result = null;
		Iterator cmpIterator = base.getFacetsAndChildren();

		while (cmpIterator.hasNext() && (result == null)) {
			component = (UIComponent) cmpIterator.next();
			if (id.equals(component.getId())) {
				result = component;
				break;
			}
			result = findComponent(component, id);
			if (result != null) {
				break;
			}
		}
		
		return result;
	}

	/**
	 * This operation is used to find the component in root by passing id.
	 * 
	 * @param id :
	 *            String object.
	 * @return UIComponent : UIComponent object.
	 */
	public UIComponent findComponentInRoot(String id) {
		
		UIComponent component = null;
		FacesContext context = FacesContext.getCurrentInstance();

		if (context != null) {
			UIComponent root = context.getViewRoot();
			component = findComponent(root, id);
		}
		
		return component;
	}

	/**
	 * This method used to print Error Messages.
	 * 
	 * @param message
	 *            Takes error message to be diaplyed
	 */

	private void setMessage(String message) {
		

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext
				.getApplication()
				.setMessageBundle(
						"com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CommonAttachment");
		ResourceBundle bundle = resourceBundle(facesContext);
		String errorMsg = bundle.getString(message);
		FacesMessage fm = new FacesMessage(errorMsg);
		facesContext.addMessage(null, fm);
		

	}

	/**
	 * This Method is used to Map to Resource Bundle.
	 * 
	 * @param facesContext
	 *            Takes faces Context Parameter
	 * @return ResourceBundle
	 */
	public static ResourceBundle resourceBundle(FacesContext facesContext) {
		

		facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		String messageBundle = facesContext.getApplication().getMessageBundle();
		Locale locale = root.getLocale();
		
		return ResourceBundle.getBundle(messageBundle, locale);
	}

	public void clearAttachmentDataBeanState() {
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		attachmentDataBean.getAttachmentList().clear();
		attachmentDataBean.setAttachmentVO(new AttachmentVO());
		attachmentDataBean.setNoData(false);
		attachmentDataBean.setShowAddAttachments(false);
		attachmentDataBean.setShowDelSucessMessage(false);
		attachmentDataBean.setShowEditAttachments(false);
		attachmentDataBean.setShowSucessMessage(false);
		
		//Code added for the defect ESPRD00901695 to fix the pagination issue in attachment section.
		attachmentDataBean.setAttachmentsRowIndex(ProgramConstants.INT_ZERO);

		
		// Code added for ESPRD00825861
		attachmentDataBean.setAttachmentAddOrUpdateforCr(false);
		// Code Ended for ESPRD00825861
	}
	
   /**
     * This operation will show audit log.
     *
     * @return String
     */
    public String showAuditLog()
    {
       
        attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
        attachmentDataBean.setAttachAuditRender(true);
        attachmentDataBean.setAttachAuditOpen(true);
        attachmentDataBean.setColumnValueRender(false);

        EnterpriseSearchResultsVO resultVO = retrieveData(0, 0);
        setRecordRange(resultVO);
        attachmentDataBean.setAttachAuditHistoryList(resultVO.getSearchResults());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }
	
	   /**
     * show the next page
     * @return
     */
    public String next(){
	//	long entryTime = System.currentTimeMillis();
        attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ attachmentDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ attachmentDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ attachmentDataBean.getStartRecord());
			logger.debug("The end record......."
					+ attachmentDataBean.getEndRecord());
		}
		EnterpriseBaseDataBean entDataBean = nextPage(attachmentDataBean,
				attachmentDataBean.getItemsPerPage());

		int start = (entDataBean.getCurrentPage()-1) * ProgramNumberConstants.INT_TEN;
		EnterpriseSearchResultsVO resultVO = retrieveData(start, ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

		attachmentDataBean.setAttachAuditHistoryList(resultVO
				.getSearchResults());
		
		/*long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("SearchSystemParameterControllerBean" + "#" + "next"
					+ "#" + (exitTime - entryTime));
		}*/

		return ProgramConstants.NEXT;
    }
    
    /**
     * show the previous page
     * @return
     */
    public String previous() {
	//	long entryTime = System.currentTimeMillis();
		
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		EnterpriseBaseDataBean entDataBean = previousPage(
				attachmentDataBean, attachmentDataBean
						.getItemsPerPage());
		
		int start = (entDataBean.getCurrentPage()-1) * ProgramNumberConstants.INT_TEN;
		EnterpriseSearchResultsVO resultVO = retrieveData(start, ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

		attachmentDataBean.setAttachAuditHistoryList(resultVO
				.getSearchResults());

		/*long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("DiagnosisCodeControllerBean" + "#" + "previous" + "#"
					+ "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));
		}*/

		return ProgramConstants.PREVIOUS;
	}
	
    /**
     * show the column value details of the selected audit log
     * @return
     */
    public String showColValHistory()
    {
        GlobalAuditsDelegate audit;
        attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
        try
        {
            
            audit = new GlobalAuditsDelegate();
            AuditLog auditLog = audit.getAuditLog(Long
					.valueOf((String) getParamValue("auditLogSK")));
            attachmentDataBean.setSelectedAuditLog(auditLog);
            attachmentDataBean.setColumnValueRender(true);
        }
        catch (Exception e)
        {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("Error in show Column Value  " + e);
        	}
        }
        return ContactManagementConstants.RENDER_SUCCESS;
    }
    
    /**
     * 
     * @return
     */
    public String closeColValHistory()
    {
        attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
        attachmentDataBean.setColumnValueRender(false);
        return ContactManagementConstants.RENDER_SUCCESS;
    }
    
    
    /**
     * converts CorrespondenceRecordVO to Correspondence
     * @param correspondenceRecordVO
     * @return
     */
	private Attachment convert(AttachmentVO correspondenceRecordVO){
	    attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		AttachmentDOConvertor attachmentDOConvertor = new AttachmentDOConvertor();
		Attachment attachment = attachmentDOConvertor
		.convertCrAttachmentVOToDO(attachmentDataBean
				.getAttachmentVO());
		
		return attachment;
	}
	
	/**
	 * retrieve the param value
	 * @param name
	 * @return
	 */
	private Object getParamValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map m = fc.getExternalContext().getRequestParameterMap();
		return m.get(name);
	}
	
	/**
	 * retrieve audit logs from the database
	 * @param start
	 * @param noOfRecord
	 * @return
	 */
    private EnterpriseSearchResultsVO retrieveData(int start, int noOfRecord){
	//	long entryTime = System.currentTimeMillis();
		GlobalAuditsDelegate audit;
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		try {
			audit = new GlobalAuditsDelegate();
			Attachment attachment = convert(
					attachmentDataBean.getAttachmentVO());

			final EnterpriseSearchResultsVO enterpriseResultVO = audit
					.getAuditLogList(attachment, start,
							noOfRecord);
			return enterpriseResultVO;
		} catch (Exception e) {
		//	e.printStackTrace();
			if(logger.isDebugEnabled())
			{
				logger.debug("Error in show Parent audit history  " + e);
			}
		}

		/*long exitTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("UpdateSystemParameterControllerBean" + "#" + "#"
					+ (exitTime - entryTime));
		}*/
		
		return null;
    }
    
    /**
     * set the record range based on the retrieve data
     * from the database that is stored in the EnterpriseSearchResultsVO
     * @param enterpriseSearchResultsVO
     */
    public void setRecordRange(EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
	//	long entryTime = System.currentTimeMillis();
		int listSize;
		attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
		
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		if(logger.isDebugEnabled())
		{
			logger.debug("Total no of records-->" + totalRecordCount);
		}
		attachmentDataBean.setCount((int) totalRecordCount);
		
		int noOfPages = attachmentDataBean.getCount()
				/ attachmentDataBean.getItemsPerPage();

		int modNofPages = attachmentDataBean.getCount()
				% attachmentDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}
		if(logger.isDebugEnabled())
		{
			logger.debug("Number Of pages: " + noOfPages);
		}

		attachmentDataBean
				.setCurrentPage(ProgramConstants.INITIAL_CURRENT_PAGE);
		attachmentDataBean.setNumberOfPages(noOfPages);
		attachmentDataBean.setStartRecord(ProgramConstants.START_RECORD);
		attachmentDataBean
				.setEndRecord(ProgramConstants.INITIAL_END_RECORD);
		listSize = attachmentDataBean.getCount();
		if(logger.isDebugEnabled())
		{
			logger.debug("Total records count is : " + listSize);
		}
		if (listSize <= attachmentDataBean.getItemsPerPage()) {
			attachmentDataBean.setEndRecord(listSize);
		}
		//"FindBugs" issue fixed
		/*if (listSize > attachmentDataBean.getItemsPerPage()) {
			listSize = attachmentDataBean.getItemsPerPage();
		}*/

		if (attachmentDataBean.getCount() <= attachmentDataBean
				.getItemsPerPage()) {
			attachmentDataBean.setShowNext(false);
		} else {
			attachmentDataBean.setShowNext(true);
		}
		attachmentDataBean.setShowPrevious(false);
		if (logger.isDebugEnabled()) {
			logger.debug("The no. of pages"
					+ attachmentDataBean.getNumberOfPages());
			logger.debug("The current no. of page"
					+ attachmentDataBean.getCurrentPage());
			logger.debug("The start record......."
					+ attachmentDataBean.getStartRecord());
			logger.debug("The end record......."
					+ attachmentDataBean.getEndRecord());
			logger.debug("The total count......."
					+ attachmentDataBean.getCount());
		}
		/*long exitTime = System.currentTimeMillis();
		logger.info("DiagnosisCodeControllerBean" + "#" + "setRecordRange"
				+ "#" + "DIAGNOSIS CODE" + "#" + (exitTime - entryTime));*/
	}
   
	
	/**
	 * Reqired filed validation for add attachment
	 * @param attachment
	 * @return
	 */
    
	private boolean requiredFldsAvbl(AttachmentVO attachment)
	{
		/**
		 * Code is added below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
		CorrespondenceControllerBean controllerBean = new CorrespondenceControllerBean();
        CorrespondenceDataBean dataBean = controllerBean.getCorrespondenceDataBean();
        boolean fldsAvbl = true;
        attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
        
        if (isNullOrEmptyField(attachment.getDescription()))
        {
            fldsAvbl = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] { ContactManagementConstants.DESCRIPTION },
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "attaddDesc");
            
            //added..for the defect ID :ESPRD00782375.....Modified to remove success message if null field
            attachmentDataBean.setShowSucessMessage(false);
        }
        
        if (isNullOrEmptyField(attachment.getEdmsWrkUnitLevel()))
        {
            fldsAvbl = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] { ContactManagementConstants.EDMS_WOKK_UNIT_LEVEL },
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "edmsdepartlevel");
            
          //added..for the defect ID :ESPRD00782375.....Modified to remove success message if null field
            attachmentDataBean.setShowSucessMessage(false);
        }
        
        if (isNullOrEmptyField(attachment.getEdmsDocType()))
        {
            fldsAvbl = false;
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] { ContactManagementConstants.EDMS_DOC_TYPE },
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "edmsdoctype");
            
          //added..for the defect ID :ESPRD00782375.....Modified to remove success message if null field
            attachmentDataBean.setShowSucessMessage(false);
        }
       
      //added..for the defect ID :ESPRD00782375.....Modified to remove success message if null field
        if (isNullOrEmptyField(attachment.getFileName()))
        {
        
        fldsAvbl = false;
        ContactManagementHelper
		.setErrorMessage(
				MaintainContactManagementUIConstants.ATTACH_MESSAGE,
				MaintainContactManagementUIConstants.ATTACH_UPLOAD_ID,
				null, null);
        
        attachmentDataBean.setShowSucessMessage(false);
        
        }
         //end
        /**
		 * Code is added below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
        if(fldsAvbl == false)
        {
        	dataBean.setLogCRErrMsgFlag(true);
        }
        else
        {
        	dataBean.setLogCRErrMsgFlag(false);
        }
        
		return fldsAvbl;
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
    public String sortCorrespondenceAttachmentsInfo(ActionEvent event)
    {
        attachmentDataBean = (AttachmentDataBean) getDataBean(ContactManagementConstants.ATTACHMENT_BEAN_NAME );
        String sortColumn = (String) event.getComponent().getAttributes().get(
                MaintainContactManagementUIConstants.CLOUMN_NAME);
        String sortOrder = (String) event.getComponent().getAttributes().get(
                MaintainContactManagementUIConstants.SORT_ORDER);
        
        ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
        attachmentDataBean.setImageRender(event.getComponent().getId());
        contactManagementHelper.sortCorrespondenceAttachmentsComparator(sortColumn, sortOrder,
        		attachmentDataBean.getAttachmentList());
        return MaintainContactManagementUIConstants.SUCCESS;
    }
	
		/**
		 * @return Returns the intialData.
		 */
		public String getIntialData() {
			
			getApplicationName ();
			
			getUserPermission();
			return intialData;
		}
		/**
		 * @param intialData The intialData to set.
		 */
		public void setIntialData(String intialData) {
			this.intialData = intialData;
		}
		
		/*public void downLoadFile()
		{
			logger.info("downLoadFile======");
			attachmentDataBean = (AttachmentDataBean) getDataBean(ATTACHMENT_DATA_BEAN);
			FacesContext facesContext = FacesContext.getCurrentInstance();
       		ExternalContext context = facesContext.getExternalContext();
			PortletSession session = (PortletSession) context.getSession(false);
       		cache.put(ContactManagementHelper.getKey("ATTACHMENT", session),
       				attachmentDataBean);
			attachmentDataBean.setDownLoadFile(true);
			
		}*/
	
		/**
		 * Reqired filed validation for Edit  attachment
		 * @param attachment
		 * @return
		 */
		
	   	private boolean requiredFldsAvblforEdit(AttachmentVO attachment)
		{
			
	        boolean fldsAvbl = true;
	        /**
			 * Code is added below for not retaining the error messages 
			 * on Log Correspondence page for the Defect ESPRD00852896
			 */
	        CorrespondenceControllerBean controllerBean = new CorrespondenceControllerBean();
	        CorrespondenceDataBean dataBean = controllerBean.getCorrespondenceDataBean();
	        if (isNullOrEmptyField(attachment.getDescription()))
	        {
	            fldsAvbl = false;
	            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
	                    new Object[] { ContactManagementConstants.DESCRIPTION },
	                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE, "attaddDesc");
	        }
	      	 if(logger.isInfoEnabled())
	      	 {
	      		 logger.info("requiredFldsAvblforEdit======"+fldsAvbl);
	      	 }
	      	/**
	 		 * Code is added below for not retaining the error messages 
	 		 * on Log Correspondence page for the Defect ESPRD00852896
	 		 */
	      	 if(fldsAvbl == false)
	      	 {
	      		dataBean.setLogCRErrMsgFlag(true);
	      	 }
	      	 else
	      	 {
	      		dataBean.setLogCRErrMsgFlag(false);
	      	 }
			return fldsAvbl;
		}
		
	   	
	   	/**
		 * Get application stream, whether it's PE or MMIS
		 *
		 */ 	
	   	
	   	private void getApplicationName ()
		{
			
			try {
		    	FacesContext context = FacesContext.getCurrentInstance();
		    	RenderRequest renderRequest = null;
		    	if(logger.isInfoEnabled())
		    	{
		    		logger.info("++context = " + context);
		    	}
		    	if (context != null) {
		    		ExternalContext extContext = context.getExternalContext();
		    		if(logger.isInfoEnabled())
		    		{
		    			logger.info("++extContext = " + extContext);
		    		}
		    		if (extContext != null) {
	    				renderRequest = (RenderRequest)extContext.getRequest();
	    				if(logger.isInfoEnabled())
	    				{
	    					logger.info("++renderRequest = " + renderRequest);
	    				}
	    		    	if (renderRequest != null) {
				    		PortletPreferences preferences = renderRequest.getPreferences();
				    		if(logger.isInfoEnabled())
				    		{
				    			logger.info("++preferences = " + preferences);
				    		}
				    		if (preferences != null) {
			    				appName = preferences.getValue("pe_mmis", "Invalid");
				    		}
	    		    	}
		    		}
		    	}
		    	if (appName != null && appName.equals("mmis")) {

				getAttachmentDataBean().setApplicationNameFlag(true);
			} else {
				getAttachmentDataBean().setApplicationNameFlag(false);
			}
		    	if(logger.isInfoEnabled())
		    	{
		    		logger.info("++ Application Name:::::::::::" + appName);
		    		logger.info("++ getAttachmentDataBean().isApplicationNameFlag():::::::::::" + getAttachmentDataBean().isApplicationNameFlag());
		    	}
	    	} catch (Exception exc) {
	    		if(logger.isErrorEnabled())
	    		{
	    			logger.error("Exception while setting Application Name -: " + exc.getMessage());
	    		}
	    		exc.printStackTrace();
	    	}
	    	if(logger.isInfoEnabled())
	    	{
	    		logger.info("++appName = " + appName);
	    	}
	    	if (appName == null) {
	    		appName = "";
	    	}
		}

		
		
		/**
		 * @return Returns the appName.
		 */
		public String getAppName() {
			return appName;
		}
		/**
		 * @param appName The appName to set.
		 */
		public void setAppName(String appName) {
			this.appName = appName;
		}

		/**
		 * Used to upload file to SAN location, when attached from SDR
		 */
		public void uploadSDRtoSAN()
		{	
			logger.debug(" uploadSDRtoSAN() START");
			List edmsResults = null;
			Map fileinfoMap = null;
			String fileContent = null;
			Set keySet= null;
			String fileExtn = null;
			EDMSQuickSearchProcessDelegate quickSearchProcessDelegate = null;
			FacesContext fc = FacesContext.getCurrentInstance();
			Object obj = fc.getExternalContext().getRequestMap().get("logCREDMSSearchResultsList");
			logger.debug("  logCREDMSSearchResultsList::  "+ obj);
			if (obj != null)
			{
				EnterpriseEDMSSearchResultsVO searchResultsVO = new EnterpriseEDMSSearchResultsVO();
				try
				{
					
					BeanUtils.copyProperties(searchResultsVO, obj);
					logger.debug("searchResultsVO "+ searchResultsVO);
					fc.getExternalContext().getRequestMap().remove("logCREDMSSearchResultsList");
					if(searchResultsVO!=null)
						{	
							EDMSQuickSearchResultsVO resultsVO = null;
							edmsResults = searchResultsVO.getSearchResultsList();
							if(edmsResults!=null && edmsResults.size()>0)
							{	
								logger.debug("edmsResults "+ edmsResults);
								quickSearchProcessDelegate = new EDMSQuickSearchProcessDelegate();
								Iterator iter = edmsResults.iterator();
								while(iter.hasNext())
								{
									resultsVO = (EDMSQuickSearchResultsVO)iter.next();
									if(resultsVO.getPageId()!=null)
									{
										logger.debug(" resultsVO.getPageId()   :::   "+ resultsVO.getPageId() );
										logger.debug(" resultsVO.getFileName() :::   "+ resultsVO.getFileName() );
										fileinfoMap = quickSearchProcessDelegate.getFileInfo(resultsVO.getPageId());
										keySet = fileinfoMap.keySet();
										Iterator it = keySet.iterator();
				    		    		while(it.hasNext())
				    		    		{
				    		    			fileExtn = (String)it.next();
				    		    			logger.debug(" fileExtn :::   "+ fileExtn );
				    		    			fileContent = (String)fileinfoMap.get(fileExtn);
				    		    		}
				    		    		writeToSan(fileContent.getBytes(), resultsVO.getFileName());
									}
								}
							}
						}
				
				} 
				catch (Exception e) 
				{   logger.debug("Exception "+ e.getMessage());
					e.printStackTrace();
				}
			}
			logger.debug(" uploadSDRtoSAN() END");
		}
}
