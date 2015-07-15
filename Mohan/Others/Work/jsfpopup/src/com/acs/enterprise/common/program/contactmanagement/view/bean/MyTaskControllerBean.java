/*
 * Created on Nov 7, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPane;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.commonentities.application.exception.CommonNoteNotFoundException;
import com.acs.enterprise.common.program.commonentities.common.delegate.CommonEntityDelegate;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordUpdateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMEntityDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRecord;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceWatchList;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceRequestVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.Executor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MyTaskDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CRDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CRsWatchListVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRecWatchListVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseViewVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.GroupCRsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.GroupCaseRecordsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.MyAlertsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.MyCRsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.MyCaseRecordsVO;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.acs.enterprise.common.view.vo.CommonMemberDetailsVO;
import com.acs.enterprise.mmis.member.common.application.exception.MemberBusinessException;
import com.acs.enterprise.mmis.member.common.domain.Member;
import com.acs.enterprise.mmis.member.information.common.delegate.MemberInformationDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLRecoverySearchResultVO;

/**
 * This is a controller class used for Contact Management My tasks related
 * functionality in the presentation layer.
 */
public class MyTaskControllerBean
        extends EnterpriseBaseControllerBean
{

	/** Holds the application name **/
	private String appName = null;
    /** Enterprise Logger for Logging */
   
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(MyTaskControllerBean.class);

    private String allData;


    /**
     * A variable of type EDMSDefaultsDataBean to hold the instance of data
     * bean.
     */
    private MyTaskDataBean myTaskDataBean = null;
    
    private MyTaskControllerBean myTaskControllerBean;
    
    private String usersList;
    
    private CMDelegate cmdelegate = null;
    
    private CaseDelegate caseDelegate = null;
 
    private boolean alertDetailFlag=true;
    
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
     * Method to get the Cr Details from MyAlerts Vo.
     *
     * @return String.
     */
    
    public String getAlertCrDets()
    {
    	
    	  
    	myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);  
    	myTaskControllerBean = (MyTaskControllerBean)getDataBean(ContactManagementConstants.MYTASK_CONTROLLER_BEAN_NAME);
        if(alertDetailFlag && !myTaskControllerBean.isExecuted())
        {
        myTaskDataBean.setCrDetails(false);
        myTaskDataBean.setCaseDetails(false);
        myTaskDataBean.setOpenCaseDets(false);
        myTaskDataBean.setOpenCrDets(false);
        
        myTaskDataBean.setDisableApplication(true);
        
        MyTaskDOConverter myTaskDOConverter = new MyTaskDOConverter();
        cmdelegate = new CMDelegate();
      
        myTaskDataBean.setOpenLetterReq(false);
        FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCode");
	
		if(map.get("tab")!=null){
			int tabIndex = Integer.parseInt(map.get("tab").toString());
			  /*if(tabIndex>=0){
			  	HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
			  	pane.setSelectedIndex(tabIndex);
			  }*/
			// Added for  Heap Dump start
			if(tabIndex>=0){
				  getMyTaskDataBean().setTabIndex(tabIndex);
			  }
			//Added for  Heap Dump end
			  getMyTaskDataBean().setItemSelectedRowIndexAlert(Integer.parseInt(indexCode));
		}
		
		if (indexCode == null) {
			indexCode = "0";
		}
		int rowIndex = Integer.parseInt(indexCode);
		if(logger.isDebugEnabled())
		{
		 logger.debug("-----the alert row index is ----> " + rowIndex);
	     logger.debug("-----the alertVoLIst size ---->"
	                + myTaskDataBean.getAlertVoList().size());
		}
	    //for fixing ESPRD00680070
	    myTaskDataBean.setStartIndexForAlert((rowIndex/10)*10);
	    
        MyAlertsVO myAlertsVO = (MyAlertsVO) myTaskDataBean.getAlertVoList()
                .get(rowIndex);

        myTaskDataBean.setMyTaskAlertSk(myAlertsVO.getAlertSKStr());
		if (myAlertsVO.getLetterReqSk() != null)
        {
			if(logger.isDebugEnabled())
			{
			logger.debug("myAlertsVO.getLetterReqSk() = " + myAlertsVO.getLetterReqSk());
			}
			myTaskDataBean.setShowTplRecoeryCaseAlertDetail(true);
			ActionRequest request = (ActionRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.getPortletSession(false).setAttribute("ltrReqSK", myAlertsVO.getLetterReqSk(),PortletSession.PORTLET_SCOPE);
            myTaskDataBean.setOpenLetterReq(true);
            myTaskDataBean.setAlertDetails(true);
           	request.getPortletSession(false).setAttribute("sourcePortlet", "AlertDetails", PortletSession.PORTLET_SCOPE);

        }
        if (myAlertsVO.getScope().equals(ContactManagementConstants.EDMS_CRSPD))
        {
        	myTaskDataBean.setShowTplRecoeryCaseAlertDetail(false);//MyTask_Alerts_ESPRD00432772_11MAR2010
            myTaskDataBean.setAlertDetails(true);
       
            myTaskDataBean.setCrsWatchListDetails(false);
            myTaskDataBean.setAlertCRDetails(true);
            myTaskDataBean.setMyCrsDetails(false);
            myTaskDataBean.setGroupCRDetails(false);
            myTaskDataBean.setMyCaseRecordsDetails(false);
            myTaskDataBean.setGroupCaseRecordsDetails(false);
            myTaskDataBean.setCaseRecordWatchListDetails(false);            
            
            myTaskDataBean.setCaseDetails(false);
            myTaskDataBean.setOpenCaseDets(false);
            myTaskDataBean.setOpenCrDets(true);
            myTaskDataBean.setMyTaskCrSk(myAlertsVO.getCorrespondenceSk());
            if(logger.isDebugEnabled())
            {
            logger.debug("myTaskDataBean.setMyTaskCrSk::"+myTaskDataBean.getMyTaskCrSk());    
            }

            CRDetailsVO crDetailsVO = new CRDetailsVO();
            Correspondence correspondence = new Correspondence();
            try
			{
            	if(logger.isInfoEnabled())
            	{
            		logger.info(" Calling getCorrespondenceDetails getCorrespondenceSk()::"+myAlertsVO.getCorrespondenceSk());
            	}
            correspondence = cmdelegate.getMyCrDeatils(Long.valueOf(myAlertsVO.getCorrespondenceSk()));
            if(logger.isInfoEnabled())
            {
            	logger.info( "After cmdeletegate call correspondance "+correspondence.getCorrespondenceSK());
            }
			}catch( CorrespondenceRecordFetchBusinessException e)
			{
				if(logger.isErrorEnabled())
				{
				logger.error("Exception in getAlertCrDets ::"+ e);	
				}
			}
			crDetailsVO = myTaskDOConverter.addCrDetails(correspondence);
			myAlertsVO.setCrDetailsVO(crDetailsVO);
            myTaskDataBean.setCrDetailsVO(myAlertsVO.getCrDetailsVO());
        }
        else if(myAlertsVO.getCaseSk() != null)
        {
        	myTaskDataBean.setShowTplRecoeryCaseAlertDetail(false);//MyTask_Alerts_ESPRD00432772_11MAR2010
        	myTaskDataBean.setAlertDetails(true);
            myTaskDataBean.setCaseDetails(true);
            myTaskDataBean.setOpenCrDets(false);
            myTaskDataBean.setOpenCaseDets(true);
            myTaskDataBean.setAlertCRDetails(false);
            CaseRecord caseRecord = new CaseRecord();
            CaseViewVO caseDetailsVO = new CaseViewVO();
            if (myAlertsVO.getCaseSk() != null)
            {
            	//Modified for defect ESPRD00512516
            	//caseRecord =getCaseDetails();
            	caseRecord =getCaseDetails(myAlertsVO.getCaseSk());
            }
            caseDetailsVO  = myTaskDOConverter.addCaseDetails(caseRecord);
            myAlertsVO.setCaseDetailsVO( caseDetailsVO);
            
            myTaskDataBean
                    .setMyTaskCaseDetailsVO(myAlertsVO.getCaseDetailsVO());
            myTaskDataBean.setMyTaskCaseSK(myAlertsVO.getCaseSk());
            myTaskDataBean.setOpenCaseDets(true);

        }
//      MyTask_Alerts_ESPRD00432772_11MAR2010
        if(myAlertsVO.getTplCaseUserID()!=null){
        	myTaskDataBean.setShowTplRecoeryCaseAlertDetail(true);
            myTaskDataBean.setCaseDetails(false);
            myTaskDataBean.setOpenCrDets(false);
            myTaskDataBean.setOpenCaseDets(false);
            myTaskDataBean.setAlertCRDetails(false);
        	myTaskDataBean.setAlertDetails(true);//UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010
        	myTaskDataBean.setOpenTPLRecoveryCaseRecord(true);//UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010
        	myTaskDataBean.setOpenSADetails(false);//UC-PGM-CRM-052.1_ESPRD00436245_18MAR2010
        	myTaskDataBean.setOpenMSQDetails(false);//UC-PGM-CRM-052.1_ESPRD00436245_18MAR2010
            
        	
        }        //EOF MyTask_Alerts_ESPRD00432772_11MAR2010
        else if(myAlertsVO.getServiceAuthorizationID() !=null ){         //added forUC-PGM-CRM-052.1_ESPRD00436245_18MAR2010
        	myTaskDataBean.setShowTplRecoeryCaseAlertDetail(true);
        	myTaskDataBean.setAlertDetails(true);
        	myTaskDataBean.setOpenSADetails(true);
        	myTaskDataBean.setOpenMSQDetails(false);
        	myTaskDataBean.setOpenTPLRecoveryCaseRecord(false);
        	
        	 myTaskDataBean.setCaseDetails(false);
             myTaskDataBean.setOpenCrDets(false);
             myTaskDataBean.setOpenCaseDets(false);
             myTaskDataBean.setAlertCRDetails(false);
             
        }
        else if(myAlertsVO.getMemberID()!=null){//UC-PGM-CRM-053_ESPRD00431860_26MAR2010
        	myTaskDataBean.setShowTplRecoeryCaseAlertDetail(true);
        	myTaskDataBean.setAlertDetails(true);
        	myTaskDataBean.setOpenSADetails(false);
        	myTaskDataBean.setOpenMSQDetails(true);
        	myTaskDataBean.setOpenTPLRecoveryCaseRecord(false);
        	
        	 myTaskDataBean.setCaseDetails(false);
             myTaskDataBean.setOpenCrDets(false);
             myTaskDataBean.setOpenCaseDets(false);
             myTaskDataBean.setAlertCRDetails(false);
        }
        //EOF UC-PGM-CRM-052.1_ESPRD00436245_18MAR2010
        myTaskDataBean.setAlertDetailsVO(myAlertsVO);//UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010
        
        alertDetailFlag=false;
        myTaskControllerBean.setExecuted(true);
        }
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * Method to get the Cr Details from MyCrs Vo.
     *
     * @return String.
     */
    public String getMyCrDets()
    {
        
  
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
     
        FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCodeMyCRs");
	
		if(map.get("tab")!=null){
			int tabIndex = Integer.parseInt(map.get("tab").toString());
			  /*if(tabIndex>=0){
			  	HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
			  	pane.setSelectedIndex(tabIndex);
			  }*/
			// Added for  Heap Dump start
			if(tabIndex>=0){
				  getMyTaskDataBean().setTabIndex(tabIndex);
			  }
			//Added for  Heap Dump end
			  getMyTaskDataBean().setItemSelectedRowIndex(Integer.parseInt(indexCode));
		}
		if (indexCode == null) {
			indexCode = "0";
		}
		int rowIndex = Integer.parseInt(indexCode);
   
        myTaskDataBean.setAlertDetails(false);
        myTaskDataBean.setOpenLetterReq(true);
  
        myTaskDataBean.setCrsWatchListDetails(false);
        myTaskDataBean.setAlertCRDetails(false);
        myTaskDataBean.setMyCrsDetails(true);
        myTaskDataBean.setGroupCRDetails(false);
        myTaskDataBean.setMyCaseRecordsDetails(false);
        myTaskDataBean.setGroupCaseRecordsDetails(false);
        myTaskDataBean.setCaseRecordWatchListDetails(false);
        myTaskDataBean.setDisableApplication(true);
        if(logger.isDebugEnabled())
        {
        logger.debug("-----the my Crs size ---->"
                + myTaskDataBean.getMyCrsList().size());
        }
        
        //for fixing ESPRD00680070
        myTaskDataBean.setStartIndexForMyCr((rowIndex/10)*10);
        
        MyCRsVO myCRsVO = (MyCRsVO) myTaskDataBean.getMyCrsList().get(rowIndex);
        if(logger.isDebugEnabled())
        {
        logger.debug("-----the my Crs CorrespondenceSK ---->"+myCRsVO.getCorrespondenceSK());
        }
        if(myCRsVO.getCorrespondenceSK() != null)
        {
        	Correspondence correspondence = null;
        	MyTaskDOConverter converter = new MyTaskDOConverter();
        	try{
        		cmdelegate = new CMDelegate();
        		
        		correspondence = cmdelegate.getMyCrDeatils(Long.valueOf(myCRsVO.getCorrespondenceSK()));
        	}
        	catch (CorrespondenceRecordFetchBusinessException e)
	        {
        		if(logger.isErrorEnabled())
        		{
        		logger.error(e);
        		}
        	
	        }
        	if(correspondence != null){
        		if(logger.isInfoEnabled())
        		{
        		logger.info("correspondence==="+correspondence.getCorrespondenceSK());
        		}
        		//"FindBugs" issue fixed
        		//CRDetailsVO crdetailsVO = new CRDetailsVO();
        		CRDetailsVO crdetailsVO = null;
        		crdetailsVO = converter.addCrDetails(correspondence);
                myCRsVO.setCrDetailsVO(crdetailsVO);
        	}
        }
        
        myTaskDataBean.setMyTaskCrSk(myCRsVO.getCorrespondenceSK());
        myTaskDataBean.setOpenCrDets(true);
        myTaskDataBean.setCrDetailsVO(myCRsVO.getCrDetailsVO());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * Method to get the Cr Details from GroupCrs Vo.
     *
     * @return String.
     */
    public String getGrpCrDets()
    {
       
    
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
       
        FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCodeGrpCRDets");
		
		if(map.get("tab")!=null){
			int tabIndex = Integer.parseInt(map.get("tab").toString());
			  /*if(tabIndex>=0){
			  	HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
			  	pane.setSelectedIndex(tabIndex);
			  }*/
			// Added for  Heap Dump start
			if(tabIndex>=0){
				  getMyTaskDataBean().setTabIndex(tabIndex);
			  }
			//Added for  Heap Dump end
			  getMyTaskDataBean().setItemSelectedRowIndexGrp(Integer.parseInt(indexCode));
		}
		if (indexCode == null) {
			indexCode = "0";
		}
		int rowIndex = Integer.parseInt(indexCode);
       
        myTaskDataBean.setAlertDetails(false);
     
        
        myTaskDataBean.setCrsWatchListDetails(false);
        myTaskDataBean.setAlertCRDetails(false);
        myTaskDataBean.setMyCrsDetails(false);
        myTaskDataBean.setGroupCRDetails(true);
        myTaskDataBean.setMyCaseRecordsDetails(false);
        myTaskDataBean.setGroupCaseRecordsDetails(false);
        myTaskDataBean.setCaseRecordWatchListDetails(false);
        if(logger.isDebugEnabled())
        {
        logger.debug("-----the GroupLIst size ---->"
                + myTaskDataBean.getMyGrpCRsList().size());
        }
        //for fixing ESPRD00680070
        myTaskDataBean.setStartIndexForGrpCr((rowIndex/10)*10);
        
        GroupCRsVO groupCRsVO = (GroupCRsVO) myTaskDataBean.getMyGrpCRsList()
                .get(rowIndex);
        if(logger.isDebugEnabled())
        {
        logger.debug("--from the Group CorrespondenceSK()->"+groupCRsVO.getCorrespondenceSK());
        }
        if(groupCRsVO.getCorrespondenceSK() != null)
        {
        	Correspondence correspondence = null;
        	MyTaskDOConverter converter = new MyTaskDOConverter();
        	try{
        		cmdelegate = new CMDelegate();
        		correspondence = cmdelegate.getMyCrDeatils(Long.valueOf(groupCRsVO.getCorrespondenceSK()));
        	}
        	catch (CorrespondenceRecordFetchBusinessException e)
	        {
        		if(logger.isErrorEnabled())
        		{
        		logger.error(e);
        		}
        		
	        }
        	if(correspondence != null){
        		if(logger.isInfoEnabled())
        		{
        		logger.info("correspondence==="+correspondence.getCorrespondenceSK());
        		}
        		CRDetailsVO crdetailsVO = new CRDetailsVO();
        		crdetailsVO = converter.addCrDetails(correspondence);
        		groupCRsVO.setCrDetailsVO(crdetailsVO);
        	}
        }
        
        
        myTaskDataBean.setOpenCrDets(false);
        myTaskDataBean.setMyTaskCrSk(groupCRsVO.getCorrespondenceSK());
        myTaskDataBean.setCrDetailsVO(groupCRsVO.getCrDetailsVO());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * Method to get the Cr Details from CrWatchList Vo.
     *
     * @return String.
     */
    public String getCrWatchListCrDets()
    {
       
      
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        
        FacesContext fc = FacesContext.getCurrentInstance();
		Map map = fc.getExternalContext().getRequestParameterMap();
		String indexCode = (String) map.get("indexCodeCRsWatchList");
		
		if(map.get("tab")!=null){
			int tabIndex = Integer.parseInt(map.get("tab").toString());
			  /*if(tabIndex>=0){
			  	HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
			  	pane.setSelectedIndex(tabIndex);
			  }*/
			// Added for  Heap Dump start
			if(tabIndex>=0){
				  getMyTaskDataBean().setTabIndex(tabIndex);
			  }
			//Added for  Heap Dump end
			  getMyTaskDataBean().setItemSelectedRowIndexCrWL(Integer.parseInt(indexCode));
		}
		if (indexCode == null) {
			indexCode = "0";
		}
		int rowIndex = Integer.parseInt(indexCode);

        
        myTaskDataBean.setAlertDetails(false);
  
        
        myTaskDataBean.setCrsWatchListDetails(true);
        myTaskDataBean.setAlertCRDetails(false);
        myTaskDataBean.setMyCrsDetails(false);
        myTaskDataBean.setGroupCRDetails(false);
        myTaskDataBean.setMyCaseRecordsDetails(false);
        myTaskDataBean.setGroupCaseRecordsDetails(false);
        myTaskDataBean.setCaseRecordWatchListDetails(false);
        if(logger.isDebugEnabled())
        {
        logger.debug("-----the watchlist size ---->"
                + myTaskDataBean.getCrWatchList().size());
        }
        //for fixing ESPRD00680070
        myTaskDataBean.setStartIndexForWtchList((rowIndex/10)*10);
        
        CRsWatchListVO watchListVO = (CRsWatchListVO) myTaskDataBean
                .getCrWatchList().get(rowIndex);
        myTaskDataBean.setOpenCrDets(true);
        if(watchListVO.getCorrespondenceSK() != null)
        {
        	CorrespondenceWatchList crWatchList = null;
        	MyTaskDOConverter converter = new MyTaskDOConverter();
        	try{
        		cmdelegate = new CMDelegate();
        		
        		crWatchList = cmdelegate.getCrWatchListDetails(Long.valueOf(watchListVO.getCorrespondenceSK()),
        				watchListVO.getWorkUnitSk());
        	}
        	catch (CorrespondenceRecordFetchBusinessException e)
	        {
        		if(logger.isErrorEnabled())
        		{
        		logger.error(e);
        		}
        		
	        }
        	if(crWatchList != null && crWatchList.getCorrespondence() != null){
        		//"FindBugs" issue fixed
        		//CRDetailsVO crdetailsVO = new CRDetailsVO();
        		CRDetailsVO crdetailsVO = null;
        		crdetailsVO = converter.addCrDetails(crWatchList.getCorrespondence());
        		watchListVO.setCrDetailsVO(crdetailsVO);
        	}
        }
        if(logger.isDebugEnabled())
        {
        logger.debug("from the watchListVO CorrespondenceSK()->"+watchListVO.getCorrespondenceSK());
        }
        
        myTaskDataBean.setMyTaskCrSk(watchListVO.getCorrespondenceSK());
        myTaskDataBean.setCrDetailsVO(watchListVO.getCrDetailsVO());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This Method to sort the records.
     *
     * @param event
     *            The AlertVoList to sort.
     * @return String.
     */

    public String sort(ActionEvent event)
    {
    	  
      
        AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
    	alBean.setMyCrSort(true);
      
    	myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        String sortColumn = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.COLUMN_NAME);

        String sortOrder = (String) event.getComponent().getAttributes().get(
                ContactManagementConstants.SORT_ORDER);

        myTaskDataBean.setImageRender(event.getComponent().getId());
        //By default focus should come to first page after sort.
        myTaskDataBean.setStartIndexForAlert(0);
        alertsComparator(sortColumn, sortOrder, myTaskDataBean
                .getAlertVoList());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This Method to sort the records.
     *
     * @param event
     *            The MyCrsList to sort.
     * @return String.
     */
    
    public String myCrSort(ActionEvent event) {

        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
    	//AlertDataBean alBean = getAlertDataBean();
        AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
    	alBean.setMyCrSort(true);
       
        
       
		ArrayList myCrsList = (ArrayList) myTaskDataBean
        .getMyCrsList();
		myTaskDataBean.setImageRender(event.getComponent().getId());

		final String sortColumn = (String) event.getComponent().getAttributes().get(
                "columnName");
        final String sortOrder = (String) event.getComponent().getAttributes().get(
                "sortOrder");
        
		Comparator comparator = new Comparator() {

			public int compare(Object obj1, Object obj2) {
				MyCRsVO data1 = (MyCRsVO) obj1;
                MyCRsVO data2 = (MyCRsVO) obj2;
				boolean ascending = false;
				if ("ascending".equals(sortOrder)) {
					ascending = true;
				} else {
					ascending = false;
				}

				if (sortColumn == null) {
					return 0;
				}

				if ("crn".equals(sortColumn)) {
					if (null == data1.getCrn())
                    {
                        data1.setCrn(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getCrn())
                    {
                        data2.setCrn(ContactManagementConstants.EMPTY_STRING);
                    }
					/*return ascending ? (data1.getCrn()
							.compareTo(data2.getCrn())) : (data2
							.getCrn().compareTo(data1
							.getCrn()));*/
                    Integer firstCrn = Integer.valueOf(data1.getCrn());
                    Integer secondCrn = Integer.valueOf(data2.getCrn());
                    return ascending ? firstCrn.compareTo(secondCrn)
                            : secondCrn.compareTo(firstCrn);
				}
				if ("openDate".equals(sortColumn)) {
					 if (null == data1.getOpenDate())
	                    {
	                        data1
	                                .setOpenDate(ContactManagementConstants.EMPTY_STRING);
	                    }
	                    if (null == data2.getOpenDate())
	                    {
	                        data2
	                                .setOpenDate(ContactManagementConstants.EMPTY_STRING);
	                    }
	                    return ascending ? (dateConverter(data1.getOpenDate())
								.compareTo(dateConverter(data2.getOpenDate()))) : (dateConverter(data2
								.getOpenDate()).compareTo(dateConverter(data1.getOpenDate())));
				}
				if ("entity".equals(sortColumn)) {
					 if (null == data1.getEntity())
	                    {
	                        data1
	                                .setEntity(ContactManagementConstants.EMPTY_STRING);
	                    }
	                    if (null == data2.getEntity())
	                    {
	                        data2
	                                .setEntity(ContactManagementConstants.EMPTY_STRING);
	                    }
					return ascending ? (data1.getEntity()
							.compareToIgnoreCase(data2.getEntity())) : (data2
							.getEntity().compareToIgnoreCase(data1.getEntity()));
				}

				if ("entityType".equals(sortColumn)) {
					if (null == data1.getEntityType())
                    {
                        data1
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getEntityType())
                    {
                        data2
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
					return ascending ? (data1.getEntityType()
							.compareTo(data2.getEntityType())) : (data2
							.getEntityType().compareTo(data1.getEntityType()));
				}
				if ("priorityCode".equals(sortColumn)) {
					if (null == data1.getPriorityCode())
                    {
                        data1
                                .setPriorityCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getPriorityCode())
                    {
                        data2
                                .setPriorityCode(ContactManagementConstants.EMPTY_STRING);
                    }
					return ascending ? (data1.getPriorityCode()
							.compareTo(data2.getPriorityCode())) : (data2
							.getPriorityCode().compareTo(data1
							.getPriorityCode()));
				}
				if ("statusCode".equals(sortColumn)) {
					if (null == data1.getStatusCode())
                    {
                        data1
                                .setStatusCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getStatusCode())
                    {
                        data2
                                .setStatusCode(ContactManagementConstants.EMPTY_STRING);
                    }
					return ascending ? (data1.getStatusCode()
							.compareTo(data2.getStatusCode()))
							: (data2.getStatusCode().compareTo(data1
									.getStatusCode()));
				}

				return 0;
			}

		};
		//"FindBugs" issue fixed
		//Collections.sort(myCrsList, comparator);
		if(myCrsList!=null){
		Collections.sort(myCrsList, comparator);
		}

		return ContactManagementConstants.RENDER_SUCCESS;
	}
  

    /**
     * This Method to sort the records.
     *
     * @param event
     *            The MyGrpCRsList to sort.
     * @return String.
     */
    public String grpCrSort(ActionEvent event)
    {
        
        
        
      
        AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
    	alBean.setMyCrSort(true);
     
    	myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        String sortColumn = (String) event.getComponent().getAttributes().get(
                "columnName");
        String sortOrder = (String) event.getComponent().getAttributes().get(
                "sortOrder");
        myTaskDataBean.setImageRender(event.getComponent().getId());
        
        grpCrComparator(sortColumn, sortOrder, myTaskDataBean
                .getMyGrpCRsList());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This Method to sort the records.
     *
     * @param event
     *            The CrWatchList to sort.
     * @return String.
     */
    public String crWatchlistSort(ActionEvent event)
    {
       
        
       
      
        AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
    	alBean.setMyCrSort(true);
    	myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        
        String sortColumn = (String) event.getComponent().getAttributes().get(
                "columnName");
        String sortOrder = (String) event.getComponent().getAttributes().get(
                "sortOrder");
        myTaskDataBean.setImageRender(event.getComponent().getId());
       
        crsWatchListComparator(sortColumn, sortOrder, myTaskDataBean
                .getCrWatchList());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This Method Sorts The data table of Codes & Indicator Section.
     *
     * @param sortColumn
     *            Takes sortColumn as Parameter
     * @param sortOrder
     *            Takes sortOrder as Parameter
     * @param dataList
     *            Takes dataList as Parameter
     */
    private void grpCrComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
       
       
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                String first = null;
                String second = null;
                GroupCRsVO data1 = (GroupCRsVO) obj1;
                GroupCRsVO data2 = (GroupCRsVO) obj2;
                boolean ascending = false;
                if (ContactManagementConstants.ASCENDING.equals(sortOrder))
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

                if (ContactManagementConstants.MYTASK_CRN.equals(sortColumn))
                {
                    if (null == data1.getCrn())
                    {
                        data1.setCrn(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getCrn())
                    {
                        data2.setCrn(ContactManagementConstants.EMPTY_STRING);
                    }
                    Integer firstCrn = Integer.valueOf(data1.getCrn());
                    Integer secondCrn = Integer.valueOf(data2.getCrn());
                    return ascending ? firstCrn.compareTo(secondCrn)
                            : secondCrn.compareTo(firstCrn);
                }
               
                if (ContactManagementConstants.SORT_COL_ACTIVE
                        .equals(sortColumn))
                {
                   
                    if (null == data1.getStatusCode())
                    {
                        data1
                                .setStatusCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getStatusCode())
                    {
                        data2
                                .setStatusCode(ContactManagementConstants.EMPTY_STRING);
                    }

                    first = data1.getStatusCode().toLowerCase();
                    second = data2.getStatusCode().toLowerCase();
                   

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);
                }
				  // Modified for Defect ESPRD0076144
                if ("openDate"
                        .equals(sortColumn))
                {

                    if (null == data1.getOpenDate())
                    {
                        data1
                                .setOpenDate(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getOpenDate())
                    {
                        data2
                                .setOpenDate(ContactManagementConstants.EMPTY_STRING);
                    }

                   
				  // Commented for defect ESPRD00761446  
				  /* first = data1.getOpenDate().toLowerCase();
                    second = data2.getOpenDate().toLowerCase();
                    logger.debug("First in desc&&&&&&&&&" + first);
                    logger.debug("second in desc%%%%%%%%%%%%" + second);*/

                    /*return ascending ? first.compareTo(second) : second
                            .compareTo(first);*/
                  // Added for Defect ESPRD00761446 - Date Sorting - START
                    return ascending ? (dateConverter(data1.getOpenDate())
							.compareTo(dateConverter(data2.getOpenDate()))) : (dateConverter(data2
							.getOpenDate()).compareTo(dateConverter(data1.getOpenDate())));
               
				// Added for Defect ESPRD00761446 - Date Sorting - END
				}
				if (ContactManagementConstants.MYTASK_PRIORITY_CODE
                        .equals(sortColumn))
                {
                    if (null == data1.getPriorityCode())
                    {
                        data1
                                .setPriorityCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getPriorityCode())
                    {
                        data2
                                .setPriorityCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getPriorityCode().compareTo(
                            data2.getPriorityCode()) : data2.getPriorityCode()
                            .compareTo(data1.getPriorityCode());
                }
                if (ContactManagementConstants.MYTASK_ENTITY.equals(sortColumn))
                {
                    if (null == data1.getEntity())
                    {
                        data1
                                .setEntity(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getEntity())
                    {
                        data2
                                .setEntity(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getEntity().compareToIgnoreCase(
                            data2.getEntity()) : data2.getEntity().compareToIgnoreCase(
                            data1.getEntity());
                }
                if (ContactManagementConstants.MYTASK_ENTITY_TYPE
                        .equals(sortColumn))
                {
                    if (null == data1.getEntityType())
                    {
                        data1
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getEntityType())
                    {
                        data2
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getEntityType().compareTo(
                            data2.getEntityType()) : data2.getEntityType()
                            .compareTo(data1.getEntityType());
                }
                if (ContactManagementConstants.MYTASK_WORK_UNIT
                        .equals(sortColumn))
                {
                	
                    if (null == data1.getDeptName())
                    {
                        data1
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getDeptName())
                    {
                        data2
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getDeptName().compareTo(
                            data2.getDeptName()) : data2.getDeptName()
                            .compareTo(data1.getDeptName());
                }
                return 0;
            }
        };
        Collections.sort(dataList, comparator);
        
    }

    /**
     * This Method Sorts The data table of Codes & Indicator Section.
     *
     * @param sortColumn
     *            Takes sortColumn as Parameter
     * @param sortOrder
     *            Takes sortOrder as Parameter
     * @param dataList
     *            Takes dataList as Parameter
     */
    private void crsWatchListComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
       
        
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                String first = null;
                String second = null;
                CRsWatchListVO data1 = (CRsWatchListVO) obj1;
                CRsWatchListVO data2 = (CRsWatchListVO) obj2;
                boolean ascending = false;
                if (ContactManagementConstants.ASCENDING.equals(sortOrder))
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

                if (ContactManagementConstants.MYTASK_CRN.equals(sortColumn))
                {
                    if (null == data1.getCrn())
                    {
                        data1.setCrn(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getCrn())
                    {
                        data2.setCrn(ContactManagementConstants.EMPTY_STRING);
                    }
                    Integer firstCrn = Integer.valueOf(data1.getCrn());
                    Integer secondCrn = Integer.valueOf(data2.getCrn());
                    return ascending ? firstCrn.compareTo(secondCrn)
                            : secondCrn.compareTo(firstCrn);
                }
                
                if (ContactManagementConstants.SORT_COL_ACTIVE
                        .equals(sortColumn))
                {
                    
                    if (null == data1.getStatusCode())
                    {
                        data1
                                .setStatusCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getStatusCode())
                    {
                        data2
                                .setStatusCode(ContactManagementConstants.EMPTY_STRING);
                    }

                    first = data1.getStatusCode().toLowerCase();
                    second = data2.getStatusCode().toLowerCase();
                   

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);
                }
				// Modified for Defect ESPRD0076144
                if ("openDate"
                        .equals(sortColumn))
                {

                    if (null == data1.getOpenDate())
                    {
                        data1
                                .setOpenDate(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getOpenDate())
                    {
                        data2
                                .setOpenDate(ContactManagementConstants.EMPTY_STRING);
                    }

                   // Commented for defect ESPRD00761446
					/*first = data1.getOpenDate().toLowerCase();
                    second = data2.getOpenDate().toLowerCase();
                    logger.debug("First in desc" + first);
                    logger.debug("second in desc" + second);

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first); */
					
				// Added for Defect ESPRD00761446 - Date Sorting - START
                    return ascending ? (dateConverter(data1.getOpenDate())
							.compareTo(dateConverter(data2.getOpenDate()))) : (dateConverter(data2
							.getOpenDate()).compareTo(dateConverter(data1.getOpenDate())));
               
				// Added for Defect ESPRD00761446 - Date Sorting - END
                }
                if (ContactManagementConstants.MYTASK_PRIORITY_CODE
                        .equals(sortColumn))
                {
                    if (null == data1.getPriorityCode())
                    {
                        data1
                                .setPriorityCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getPriorityCode())
                    {
                        data2
                                .setPriorityCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getPriorityCode().compareTo(
                            data2.getPriorityCode()) : data2.getPriorityCode()
                            .compareTo(data1.getPriorityCode());
                }
                if (ContactManagementConstants.MYTASK_ENTITY.equals(sortColumn))
                {
                    if (null == data1.getEntity())
                    {
                        data1
                                .setEntity(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getEntity())
                    {
                        data2
                                .setEntity(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getEntity().compareToIgnoreCase(
                            data2.getEntity()) : data2.getEntity().compareToIgnoreCase(
                            data1.getEntity());
                }
                if (ContactManagementConstants.MYTASK_ENTITY_TYPE
                        .equals(sortColumn))
                {
                    if (null == data1.getEntityType())
                    {
                        data1
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getEntityType())
                    {
                        data2
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getEntityType().compareTo(
                            data2.getEntityType()) : data2.getEntityType()
                            .compareTo(data1.getEntityType());
                }
                if (ContactManagementConstants.MYTASK_USER_WORK_UNIT
                        .equals(sortColumn))
                {
                    if (null == data1.getUserName())
                    {
                        data1
                                .setUserName(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getUserName())
                    {
                        data2
                                .setUserName(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getUserName().compareTo(
                            data2.getUserName()) : data2
                            .getUserName().compareTo(
                                    data1.getUserName());
                }
                return 0;
            }
        };
        Collections.sort(dataList, comparator);
        
    }

    /**
     * This Method Sorts The data table of Codes & Indicator Section.
     *
     * @param sortColumn
     *            Takes sortColumn as Parameter
     * @param sortOrder
     *            Takes sortOrder as Parameter
     * @param dataList
     *            Takes dataList as Parameter
     */
    /*private void myCrAlertsComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
        logger.debug("Inside alertsComparator --cr");
       
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                String first = null;
                String second = null;
                MyCRsVO data1 = (MyCRsVO) obj1;
                MyCRsVO data2 = (MyCRsVO) obj2;
                boolean ascending = false;
                if ("ascending".equals(sortOrder))
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

                if ("crn".equals(sortColumn))
                {
                    if (null == data1.getCrn())
                    {
                        data1.setCrn(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getCrn())
                    {
                        data2.setCrn(ContactManagementConstants.EMPTY_STRING);
                    }
                    Integer firstCrn = Integer.valueOf(data1.getCrn());
                    Integer secondCrn = Integer.valueOf(data2.getCrn());
                    return ascending ? firstCrn.compareTo(secondCrn)
                            : secondCrn.compareTo(firstCrn);
                }
                logger.debug("outside description");
                if ("statusCode".equals(sortColumn))
                {
                    logger.debug("in description");
                    if (null == data1.getStatusCode())
                    {
                        data1
                                .setStatusCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getStatusCode())
                    {
                        data2
                                .setStatusCode(ContactManagementConstants.EMPTY_STRING);
                    }

                    first = data1.getStatusCode().toLowerCase();
                    second = data2.getStatusCode().toLowerCase();
                    logger.debug("First in desc" + first);
                    logger.debug("second in desc" + second);

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);
                }
                if ("openDate".equals(sortColumn))
                {

                    if (null == data1.getOpenDate())
                    {
                        data1
                                .setOpenDate(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getOpenDate())
                    {
                        data2
                                .setOpenDate(ContactManagementConstants.EMPTY_STRING);
                    }

                    first = data1.getOpenDate().toLowerCase();
                    second = data2.getOpenDate().toLowerCase();
                    logger.debug("First in desc" + first);
                    logger.debug("second in desc" + second);

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);
                }
                if ("priorityCode".equals(sortColumn))
                {
                    if (null == data1.getPriorityCode())
                    {
                        data1
                                .setPriorityCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getPriorityCode())
                    {
                        data2
                                .setPriorityCode(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getPriorityCode().compareTo(
                            data2.getPriorityCode()) : data2.getPriorityCode()
                            .compareTo(data1.getPriorityCode());
                }
                if ("entity".equals(sortColumn))
                {
                    if (null == data1.getEntity())
                    {
                        data1
                                .setEntity(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getEntity())
                    {
                        data2
                                .setEntity(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getEntity().compareTo(
                            data2.getEntity()) : data2.getEntity().compareTo(
                            data1.getEntity());
                }
                if ("entityType".equals(sortColumn))
                {
                    if (null == data1.getEntityType())
                    {
                        data1
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getEntityType())
                    {
                        data2
                                .setEntityType(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getEntityType().compareTo(
                            data2.getEntityType()) : data2.getEntityType()
                            .compareTo(data1.getEntityType());
                }

                return 0;
            }
        };
        Collections.sort(dataList, comparator);
        
    }*/

    /**
     * This Method Sorts The data table of Codes & Indicator Section.
     *
     * @param sortColumn
     *            Takes sortColumn as Parameter
     * @param sortOrder
     *            Takes sortOrder as Parameter
     * @param dataList
     *            Takes dataList as Parameter
     */
    private void alertsComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
       
        AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
    	alBean.setMyCrSort(true);
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                String first = null;
                String second = null;
                MyAlertsVO data1 = (MyAlertsVO) obj1;
                MyAlertsVO data2 = (MyAlertsVO) obj2;
                boolean ascending = false;
                if (ContactManagementConstants.ASCENDING.equals(sortOrder))
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

                if (ContactManagementConstants.ALERT_TYPE_ID.equals(sortColumn))
                {
                    if (null == data1.getAlertType())
                    {
                        data1
                                .setAlertType(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getAlertType())
                    {
                        data2
                                .setAlertType(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getAlertType().compareTo(
                            data2.getAlertType()) : data2.getAlertType()
                            .compareTo(data1.getAlertType());
                }
               
                if (ContactManagementConstants.MYTASK_DESCRIPTION
                        .equals(sortColumn))
                {
                    
                    if (null == data1.getDescription())
                    {
                        data1
                                .setDescription(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getDescription())
                    {
                        data2
                                .setDescription(ContactManagementConstants.EMPTY_STRING);
                    }

                    first = data1.getDescription().toLowerCase();
                    second = data2.getDescription().toLowerCase();
                   

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);
                }
                if (ContactManagementConstants.ALERT_DUE_DATE_ID
                        .equals(sortColumn))
                {

                    if (null == data1.getDueDate())
                    {
                        data1
                                .setDueDate(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getDueDate())
                    {
                        data2
                                .setDueDate(ContactManagementConstants.EMPTY_STRING);
                    }

                    first = data1.getDueDate().toLowerCase();
                    second = data2.getDueDate().toLowerCase();
                    

                    return ascending ? first.compareTo(second) : second
                            .compareTo(first);
                }
                if (ContactManagementConstants.MYTASK_SCOPE.equals(sortColumn))
                {
                    if (null == data1.getScope())
                    {
                        data1.setScope(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getDueDate())
                    {
                        data2.setScope(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getScope().compareTo(
                            data2.getScope()) : data2.getScope().compareTo(
                            data1.getScope());
                }
                // change in es2
                if (ContactManagementConstants.MYTASK_ENTITY_NAME
                        .equals(sortColumn))
                {
                    if (null == data1.getEntityName())
                    {
                        data1
                                .setEntityName(ContactManagementConstants.EMPTY_STRING);
                    }
                    if (null == data2.getEntityName())
                    {
                        data2
                                .setEntityName(ContactManagementConstants.EMPTY_STRING);
                    }
                    return ascending ? data1.getEntityName().compareToIgnoreCase(
                            data2.getEntityName()) : data2.getEntityName()
                            .compareToIgnoreCase(data1.getEntityName());
                }
                return 0;
            }
        };
        Collections.sort(dataList, comparator);
        
    }

    /**
     * Method to get the index of the Row selected.
     *
     * @return int.
     */
   

    /**
     * This method will be used to view the CR details in My CRs, Group CRs and
     * CRs Watchlist tabs in CR Details section of My Tasks page.
     *
     * @return String.
     */
    public String getCRDetails()
    {
        return null;
    }

    /**
     * This method will be used to cancel and close the detail section.
     *
     * @return String.
     */
    public String cancel()
    {
       
       
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
       
        
         // Commented for heapdump issue
        /*HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
        if(pane!=null && pane.getSelectedIndex()>-1){
        	int index = pane.getSelectedIndex();
        	
        	if(index == 0)
        	pane.setSelectedIndex(0);
        	else if(index == 1){
            	pane.setSelectedIndex(1);
            	
        	}
        	else if(index == 2){
            	pane.setSelectedIndex(2);
            	
        	}
        	else if(index == 3){
            	pane.setSelectedIndex(3);
            	
        	}
        	else if(index == 4){
            	pane.setSelectedIndex(4);
            	
        	}
        	else if(index == 5){
            	pane.setSelectedIndex(5);
            	
        	}
        	else if(index == 6){
        		pane.setSelectedIndex(6);
        		
        	}
            	
        	
        }*/
        myTaskDataBean.setCrDetails(false);
        myTaskDataBean.setAlertCRDetails(false);
        myTaskDataBean.setMyCrsDetails(false);
        myTaskDataBean.setGroupCRDetails(false);
        myTaskDataBean.setCrsWatchListDetails(false);
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method will be used to open the letter request.
     *
     * @return String.
     */
    public String openLetterReq()
    {
		// Modified for the defect ESPRD00809884
		logger.debug("openLetterReq Started = " + myTaskDataBean);
		FacesContext fc = FacesContext.getCurrentInstance();
		String ltrsk = myTaskDataBean.getAlertDetailsVO().getLetterReqSk();
		logger.debug("openLetterReq ltrsk = " + ltrsk);
		fc.getExternalContext().getRequestMap().put(
				ContactManagementConstants.LTR_REQ_SK, ltrsk);
		return ContactManagementConstants.RENDER_SUCCESS;
	}

    /**
     * Method for Displaying the Case Record Details.
     *
     * @return String.
     */
    public String getCaseRecordDetails()
    {
        return null;
    }

    /**
     * This method is to create an instance of Data Bean.
     *
     * @return Returns EDMSDefaultsDataBean
     */
  

    /**
     * Method to get the details of the user logged-In.
     *
     * @return EnterpriseUserProfile.
     */
    public EnterpriseUserProfile getUserInfo()
    {
       
        HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderresponse = null;
        EnterpriseUserProfile enterpriseUserProfile = getUserData(
                renderrequest, renderresponse);
        
        return enterpriseUserProfile;
    }

    /**
     * This method is used to get the details for Correspondance.
     *
     * @param correspondenceSk
     *            Takes correspondenceSk as param
     * @return null.
     */
    public String showDetailPortletForCrspd(String correspondenceSk)
    {
    	if(logger.isDebugEnabled())
    	{
        logger.debug("showDetailPortletForCrspd:correspondenceSk : "
                + correspondenceSk);
    	}
       
        
        FacesContext fc = FacesContext.getCurrentInstance();

        PortletSession pSession = (PortletSession) fc.getExternalContext().getSession(true);
        pSession.setAttribute(ContactManagementConstants.ClearInqEntyData,
                                        ContactManagementConstants.ClearInqEntyData, pSession.APPLICATION_SCOPE);

        fc.getExternalContext().getRequestMap().put("mytaskcorrespondenceSk",
                correspondenceSk);
       
        
        return ContactManagementConstants.EMPTY_STRING;

    }

    /**
     * This method isused for IPC.
     *
     * @return null.
     */
    public String openCR()
    {
        
        
       
  
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        String myTaskCrSK = myTaskDataBean.getMyTaskCrSk();
       
  

        
        showDetailPortletForCrspd(myTaskCrSK);

        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method isused for IPC.
     *
     * @return null.
     */
    public String claimCR()
    {
       
        boolean flag = false;
        FacesContext fc = FacesContext.getCurrentInstance();
        
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
  
        String myTaskCrSK = myTaskDataBean.getMyTaskCrSk();
        if(logger.isDebugEnabled())
        {
        logger.debug("myTaskCrSK : " + myTaskCrSK);
        }
       
        
        if (myTaskCrSK != null)
        {
            flag = updateCr(myTaskCrSK);
        }
       
        if (flag)
        {
        	AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
            PortletSession pSession = (PortletSession) fc.getExternalContext().getSession(true);
     		pSession.setAttribute(ContactManagementConstants.RefreshMyTaskData,
     				ContactManagementConstants.RefreshMyTaskData, pSession.APPLICATION_SCOPE);
        	alBean.setMyCrSort(false);
        	fc.getExternalContext().getRequestMap().put(
                    "myTaskClaimCorrespondenceSk", myTaskCrSK);
            
        }
        myTaskDataBean.setMytaskGrpCRsCounter(false);
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method for updateCr.
     *
     * @param myTaskCrSK
     *            The Correspondence to set.
     * @return boolean.
     */
    private boolean updateCr(String myTaskCrSK)
    {
        
        
        Correspondence correspondence = null;
        Long crSK= null;
        boolean flag = false;
        cmdelegate = new CMDelegate();
        CorrespondenceRequestVO correspondenceRequestVO = null;
        ContactMaintenanceDelegate maintenanceDelegate = new ContactMaintenanceDelegate();
        try
        {
            
            correspondence = cmdelegate.getCorrespondenceDetails(Long
                    .valueOf(myTaskCrSK));
            
        }
        catch (CorrespondenceRecordFetchBusinessException e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }
        if (correspondence != null)
        {
        	if(logger.isDebugEnabled())
        	{
        	 logger.debug("in update correspondence sk :"
                    + correspondence.getCorrespondenceSK());
        	}
            //EnterpriseUserProfile profile = getUserInfo();
        	 FacesContext facesContext = FacesContext.getCurrentInstance();
     		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
     		String userId = getLoggedInUserID(portletRequest);
            Long userWrkSk = null;
            if (userId != null)
            {
            	if(logger.isDebugEnabled())
            	{
            	  logger.debug("UserId from profile :" + userId);
            	}
                try
                {
                    userWrkSk = maintenanceDelegate.getWorkUnitID(userId);
                    if(logger.isDebugEnabled())
                    {
                    	logger.debug("After try of .getWorkUnitID() UserWorkUnitSk :"  + userWrkSk);
                    }
                }
                catch (LOBHierarchyFetchBusinessException e1)
                {
                	if(logger.isErrorEnabled())
                	{
                    logger.error(e1.getMessage(), e1);
                	}
                }
            }
            if (userWrkSk != null)
            {
            	crSK=correspondence.getCorrespondenceSK();
                WorkUnit assignToWorkUnit = correspondence
                        .getCorrespondenceAssignToWorkUnit();
                assignToWorkUnit.setWorkUnitSK(userWrkSk);
                correspondence
                        .setCorrespondenceAssignToWorkUnit(assignToWorkUnit);
                correspondence.setPriorAssociatedCRs(null);
              //  CorrespondenceRequestVO requestVO = new CorrespondenceRequestVO();
              //  requestVO.setCorrespondenceRecord(correspondence);

                try
                {
                	 
                    /*correspondenceRequestVO = cmdelegate
                            .updateCorrespondence(requestVO);*/
                	
                	/*Above line of code is commented and below line of code is 
                	added for defect ESPRD00873439 to update only AssignToWorkUnit column*/
                 
                	if (null != crSK) {
						flag = cmdelegate.updateAssignToWorkUnit(userWrkSk,crSK, userId);
					} else {
						logger.debug("crSK is null");
					}
                	
                }
                catch (CorrespondenceRecordUpdateBusinessException e1)
                {
                	if(logger.isErrorEnabled())
                	{
                    logger.error(e1.getMessage(), e1);
                	}
                }
              /*  if (correspondenceRequestVO != null
                        && correspondenceRequestVO.getCorrespondenceRecord() != null
                        && correspondenceRequestVO.getCorrespondenceRecord()
                                .getCorrespondenceSK() != null)
                {
                    flag = true;
                }*/

            }

        }
        
        return flag;
    }

    /**
     * @param commonEntitySk :
     *            The commonEntitySk to set.
     * @param commonEntityTypeCode :
     *            The commonEntityTypeCode to set.
     * @return String.
     */
    public String getEntityDetails(Long commonEntitySk,
            String commonEntityTypeCode)

    {
        
        
    
        CMEntityDelegate entityDelegate = new CMEntityDelegate();

        String entityName = null;
       

        if (commonEntitySk != null
                && (commonEntityTypeCode != null && !commonEntityTypeCode
                        .equals(ContactManagementConstants.EMPTY_STRING)))
        {
            try
            {
                entityName = entityDelegate.getEntityName(commonEntitySk,
                        commonEntityTypeCode);
                
            }
            catch (CMEntityFetchBusinessException e)
            {
            	if(logger.isErrorEnabled())
            	{
                logger.error(e.getMessage(), e);
            	}
            }
        }
        
        
        return entityName;
    }

    /**
     * @param noteSetKey :
     *            The noteSetKey to set.
     * @return NoteSet.
     * @throws CommonNoteNotFoundException :
     *             Exception thrown if unable to fetch the information.
     */

    public NoteSet getNoteSet(Long noteSetKey)
            throws CommonNoteNotFoundException

    {
       
        
        CommonEntityDelegate commonEntityDelegate = new CommonEntityDelegate();
        
        NoteSet noteSet = null;
        try
        {
            noteSet = commonEntityDelegate.getNoteSet(noteSetKey);
        }
        catch (CommonNoteNotFoundException notesFetchException)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(notesFetchException.getMessage(), notesFetchException);
        	}
            throw new CommonNoteNotFoundException(notesFetchException);
        }
        
        return noteSet;
    }

    /**
     * For Case
     *
     * @author Madhurima
     * @return String.
     */
    public String caseCancel()
    {
       
   
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
       
        myTaskDataBean.setCaseDetails(false);
        //Commented for heapdump issue
       /* HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
        if(pane!=null && pane.getSelectedIndex()>-1){
        	int index = pane.getSelectedIndex();
        	
        	if(index == 0)
        	pane.setSelectedIndex(0);
        	else if(index == 1){
            	pane.setSelectedIndex(1);
            	
        	}
        	else if(index == 2){
            	pane.setSelectedIndex(2);
            	
        	}
        	else if(index == 3){
            	pane.setSelectedIndex(3);
            	
        	}
        	else if(index == 4){
            	pane.setSelectedIndex(4);
            	
        	}
        	else if(index == 5){
            	pane.setSelectedIndex(5);
            	
        	}
        	else if(index == 6){
        		pane.setSelectedIndex(6);
        		
        	}
            	
        	
        }*/
        myTaskDataBean.setMyCaseRecordsDetails(false);
        myTaskDataBean.setGroupCaseRecordsDetails(false);
        myTaskDataBean.setCaseRecordWatchListDetails(false);

        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * Method to get the Cr Details from MyCrs Vo.
     *
     * @return String.
     */
    public String getMyCaseDetails()
    {

        
      
   
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        myTaskControllerBean = (MyTaskControllerBean)getDataBean(ContactManagementConstants.MYTASK_CONTROLLER_BEAN_NAME);
        if(!myTaskControllerBean.isExecuted())
        {
        	FacesContext fc = FacesContext.getCurrentInstance();
        	Map map = fc.getExternalContext().getRequestParameterMap();
        	String indexCode = (String) map.get("indexCodeMyCaseRecords");
        	
        	if(map.get("tab")!=null){
        		int tabIndex = Integer.parseInt(map.get("tab").toString());
        		/*if(tabIndex>=0){
        			HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
        			pane.setSelectedIndex(tabIndex);
        		}*/
        		// Added for  Heap Dump start
    			if(tabIndex>=0){
    				  getMyTaskDataBean().setTabIndex(tabIndex);
    			  }
    			//Added for  Heap Dump end
        	}
        	if (indexCode == null) {
        		indexCode = "0";
        	}
        	int rowIndex = Integer.parseInt(indexCode);
        	//for ESPRD00680070
        	myTaskDataBean.setItemSelectedRowIndexMyCase(rowIndex);
        	
        	myTaskDataBean.setAlertDetails(false);
        	
        	
        	myTaskDataBean.setCrsWatchListDetails(false);
        	myTaskDataBean.setAlertCRDetails(false);
        	myTaskDataBean.setMyCrsDetails(false);
        	myTaskDataBean.setGroupCRDetails(false);
        	myTaskDataBean.setMyCaseRecordsDetails(true);
        	myTaskDataBean.setGroupCaseRecordsDetails(false);
        	myTaskDataBean.setCaseRecordWatchListDetails(false);
        	logger.debug("MyCaseRecord List Size is:"
        			+ myTaskDataBean.getMyCaseRecordList().size());
        	//for fixing ESPRD00680070
        	myTaskDataBean.setStartIndexForMyCase((rowIndex/10)*10);
        	
        	MyCaseRecordsVO myCaseRecordsVO = (MyCaseRecordsVO) myTaskDataBean
			.getMyCaseRecordList().get(rowIndex);
        	if(logger.isDebugEnabled())
        	{
        	logger.debug("Caserecno in getCasedetails:"
        			+ myCaseRecordsVO.getCaseRecNo());
        	}
        	
        	if (myCaseRecordsVO.getCaseRecNo() != null) 
        	{
        		CaseRecord caseRecord = null;
            	MyTaskDOConverter converter = new MyTaskDOConverter();
            	try{
            		caseDelegate = new CaseDelegate();
            		caseRecord = caseDelegate.getCaseDetails(Long.valueOf(myCaseRecordsVO.getCaseRecNo()));
            	}
            	catch (CaseRecordFetchBusinessException e)
                {
            		e.printStackTrace();
            		if(logger.isErrorEnabled())
            		{
            		logger.error(e);
            		}
            		
                }
            	if(caseRecord != null){
            		if(logger.isDebugEnabled())
            		{
            		logger.debug("caserecord case sk:==="+caseRecord.getCaseSK());
            		}
            		CaseViewVO detailsVO = new CaseViewVO();
            		
            		detailsVO = converter.addCaseDetails(caseRecord);
            		myCaseRecordsVO.setCaseDetailsVO(detailsVO);
            	}
				
			}
        	
        	myTaskDataBean.setMyTaskCaseSK(myCaseRecordsVO.getCaseRecNo());
        	if (myCaseRecordsVO.getEntityType() != null)
        	{
        		myTaskDataBean.setCaseEntityType(myCaseRecordsVO.getEntityType());
        	}
        	if (myCaseRecordsVO.getEntitySK() != null)
        	{
        		myTaskDataBean.setEntitySk(myCaseRecordsVO.getEntitySK());
        	}
        	myTaskDataBean.setOpenCaseDets(true);
        	myTaskDataBean.setClaimCaseDets(false);
        	myTaskDataBean.setMyTaskCaseDetailsVO(myCaseRecordsVO
        			.getCaseDetailsVO());
        	if(logger.isDebugEnabled())
        	{
        	logger.debug("Notetext from detailsVO in getMycasedetails:"
        			+ myTaskDataBean.getMyTaskCaseDetailsVO().getNoteSet());
        	}
        	((ActionRequest)fc.getExternalContext().getRequest()).getPortletSession()
			.setAttribute("openCrSK",myCaseRecordsVO.getCaseRecNo(),PortletSession.PORTLET_SCOPE);
        	
        	myTaskControllerBean.setExecuted(true);
        }
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * Method to get the Cr Details from GroupCrs Vo.
     *
     * @return String.
     */
    public String getGrpCaseDets()
    {
        
       

    
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        myTaskControllerBean = (MyTaskControllerBean)getDataBean(ContactManagementConstants.MYTASK_CONTROLLER_BEAN_NAME);
        if(!myTaskControllerBean.isExecuted())
        {
        	FacesContext fc = FacesContext.getCurrentInstance();
        	Map map = fc.getExternalContext().getRequestParameterMap();
        	String indexCode = (String) map.get("indexCodegroupCaseRecords");
        	
        	if(map.get("tab")!=null){
        		int tabIndex = Integer.parseInt(map.get("tab").toString());
        		/*if(tabIndex>=0){
        			HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
        			pane.setSelectedIndex(tabIndex);
        		}*/
        		// Added for  Heap Dump start
    			if(tabIndex>=0){
    				  getMyTaskDataBean().setTabIndex(tabIndex);
    			  }
    			//Added for  Heap Dump end
        	}
        	if (indexCode == null) {
        		indexCode = "0";
        	}
        	int rowIndex = Integer.parseInt(indexCode);
        	//for ESPRD00680070
        	myTaskDataBean.setItemSelectedRowIndexGrpCase(rowIndex);
        	
        	myTaskDataBean.setAlertDetails(false);
        	
        	myTaskDataBean.setCrsWatchListDetails(false);
        	myTaskDataBean.setAlertCRDetails(false);
        	myTaskDataBean.setMyCrsDetails(false);
        	myTaskDataBean.setGroupCRDetails(false);
        	myTaskDataBean.setMyCaseRecordsDetails(false);
        	myTaskDataBean.setGroupCaseRecordsDetails(true);
        	myTaskDataBean.setCaseRecordWatchListDetails(false);
        	if(logger.isDebugEnabled())
        	{
        	logger.debug("-----the GroupLIst size ---->"
        			+ myTaskDataBean.getGroupCaseRecList().size());
        	}
        	//for fixing ESPRD00680070
        	myTaskDataBean.setStartIndexForGrpCase((rowIndex/10)*10);
        	
        	GroupCaseRecordsVO groupCaseRecordsVO = (GroupCaseRecordsVO) myTaskDataBean
			.getGroupCaseRecList().get(rowIndex);
        	
        	if(groupCaseRecordsVO.getCaseRecNo() != null)
            {
            	CaseRecord caseRecord = null;
            	MyTaskDOConverter converter = new MyTaskDOConverter();
            	try{
            		caseDelegate = new CaseDelegate();
            		caseRecord = caseDelegate.getCaseDetails(Long.valueOf(groupCaseRecordsVO.getCaseRecNo()));
            	}
            	catch (CaseRecordFetchBusinessException e)
                {
            		e.printStackTrace();
            		if(logger.isErrorEnabled())
            		{
            		logger.error(e);
            		}
            		
                }
            	if(caseRecord != null){
            		if(logger.isDebugEnabled())
            		{
            		logger.debug("caserecord case sk:==="+caseRecord.getCaseSK());
            		}
            		CaseViewVO detailsVO = new CaseViewVO();
            		
            		detailsVO = converter.addCaseDetails(caseRecord);
            		groupCaseRecordsVO.setCaseDetailsVO(detailsVO);
            	}
            }
        	
        	myTaskDataBean.setOpenCaseDets(false);
        	myTaskDataBean.setClaimCaseDets(true);
        	myTaskDataBean.setMyTaskCaseSK(groupCaseRecordsVO.getCaseRecNo());
        	String caseSk = groupCaseRecordsVO.getCaseRecNo();
        	if(caseSk != null){
        		myTaskDataBean.setDisableNotes(true);
        	}
        	if (groupCaseRecordsVO.getEntityType() != null)
        	{
        		myTaskDataBean
				.setCaseEntityType(groupCaseRecordsVO.getEntityType());
        	}
        	if (groupCaseRecordsVO.getEntitySk() != null)
        	{
        		myTaskDataBean.setEntitySk(groupCaseRecordsVO.getEntitySk());
        		if(logger.isDebugEnabled())
        		{
        		logger.debug("Entitysk fromgroupCaseVo in getdetails:"
        				+ groupCaseRecordsVO.getEntitySk());
        		logger.debug("EntitySk from databean in getdetails:"
        				+ myTaskDataBean.getEntitySk());
        		}
        	}
        	if(logger.isDebugEnabled())
        	{
        	logger.debug("CaseSk from MyTaskDatabean:"
        			+ myTaskDataBean.getMyTaskCaseSK());
        	}
        	myTaskDataBean.setMyTaskCaseDetailsVO(groupCaseRecordsVO
        			.getCaseDetailsVO());
        	
        	((ActionRequest)fc.getExternalContext().getRequest()).getPortletSession()
			.setAttribute("openCrSK",groupCaseRecordsVO.getCaseRecNo(),PortletSession.PORTLET_SCOPE);
        	myTaskControllerBean.setExecuted(true);
        }
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * Method to get the Cr Details from CrWatchList Vo.
     *
     * @return String.
     */
    public String getCaseWatchListDets()
    {
       
      
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        myTaskControllerBean = (MyTaskControllerBean)getDataBean(ContactManagementConstants.MYTASK_CONTROLLER_BEAN_NAME);
        if(!myTaskControllerBean.isExecuted())
        {
        	FacesContext fc = FacesContext.getCurrentInstance();
        	Map map = fc.getExternalContext().getRequestParameterMap();
        	String indexCode = (String) map.get("indexCodeCaseWatchList");
        	
        	if(map.get("tab")!=null){
        		int tabIndex = Integer.parseInt(map.get("tab").toString());
        		/*if(tabIndex>=0){
        			HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
        			pane.setSelectedIndex(tabIndex);
        		}*/
        		// Added for  Heap Dump start
    			if(tabIndex>=0){
    				  getMyTaskDataBean().setTabIndex(tabIndex);
    			  }
    			//Added for  Heap Dump end
        	}
        	if (indexCode == null) {
        		indexCode = "0";
        	}
        	int rowIndex = Integer.parseInt(indexCode);
        	//for ESPRD00680070
        	myTaskDataBean.setItemSelectedRowIndexCaseWL(rowIndex);
        	myTaskDataBean.setAlertDetails(false);
        	
        	myTaskDataBean.setCrsWatchListDetails(false);
        	myTaskDataBean.setAlertCRDetails(false);
        	myTaskDataBean.setMyCrsDetails(false);
        	myTaskDataBean.setGroupCRDetails(false);
        	myTaskDataBean.setMyCaseRecordsDetails(false);
        	myTaskDataBean.setGroupCaseRecordsDetails(false);
        	myTaskDataBean.setCaseRecordWatchListDetails(true);
        	//for fixing ESPRD00680070
        	myTaskDataBean.setStartIndexForGrpCaseWtchList((rowIndex/10)*10);
        	
        	CaseRecWatchListVO caseWatchListVO = (CaseRecWatchListVO) myTaskDataBean
			.getCaseRecWatchList().get(rowIndex);
        	
        	if (caseWatchListVO.getCaseRecNo() != null) {
        		CaseRecord caseRecord = null;
            	MyTaskDOConverter converter = new MyTaskDOConverter();
            	try{
            		caseDelegate = new CaseDelegate();
				caseRecord = caseDelegate.getCaseDetails(Long.valueOf(caseWatchListVO.getCaseRecNo()));
				
				} catch (CaseRecordFetchBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(caseRecord != null){
					if(logger.isDebugEnabled())
					{
            		logger.debug("caserecord case sk:==="+caseRecord.getCaseSK());
					}
            		CaseViewVO detailsVO = new CaseViewVO();
            		
            		detailsVO = converter.addCaseDetails(caseRecord);
            		caseWatchListVO.setCaseDetailsVO(detailsVO);
            	}
			}
        	
        	myTaskDataBean.setOpenCaseDets(true);
        	myTaskDataBean.setClaimCaseDets(false);
        	myTaskDataBean.setMyTaskCaseSK(caseWatchListVO.getCaseRecNo());
        	if (caseWatchListVO.getEntityType() != null)
        	{
        		myTaskDataBean.setCaseEntityType(caseWatchListVO.getEntityType());
        	}
        	if (caseWatchListVO.getEntitySk() != null)
        	{
        		myTaskDataBean.setEntitySk(caseWatchListVO.getEntitySk());
        	}
        	myTaskDataBean.setMyTaskCaseDetailsVO(caseWatchListVO
        			.getCaseDetailsVO());
        	
        	((ActionRequest)fc.getExternalContext().getRequest()).getPortletSession()
			.setAttribute("openCrSK",caseWatchListVO.getCaseRecNo(),PortletSession.PORTLET_SCOPE);
        	myTaskControllerBean.setExecuted(true);
        	
        }
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method is used to get the User ID to process Claim case
     *
     * @return String : User ID
     */
    public String getUserID()
    {
        
        String userId = null ;
        /*String userId = "UID1";

        HttpServletRequest renderrequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderresponse = null;
        EnterpriseUserProfile eup = getUserData(renderrequest, renderresponse);
        if (eup != null && !eup.getUserId().equals(""))
        {
            userId = eup.getUserId();
        }*/

        FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		userId = getLoggedInUserID(portletRequest);
		
        return userId;
    }

    /**
     * This method is used to get the UserSK given the userId. ClaimCase
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
            if(logger.isDebugEnabled())
            {
            logger.debug("UserSk from getUSerSk:" + userSK);
            }
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
     * THis method used to get the casedetails
     * @return String.
     */
    private CaseRecord getCaseDetails()
    {
       
        //MyTaskDataBean myTaskDataBean = getMyTaskDataBean();
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        String caseSk = myTaskDataBean.getMyTaskCaseSK();
        
        CaseRecord caserecord = new CaseRecord();
        caseDelegate = new CaseDelegate();
        try
        {
            caserecord = caseDelegate.getCaseDetails(new Long(caseSk));
        }
        catch (Exception e)
        {
        	if(logger.isDebugEnabled())
        	{
            logger.debug(e);
        	}
        }
        
        return caserecord;
    }
    
    /**
     * THis method used to get the casedetails
     * @return String.
     */
    private CaseRecord getCaseDetails(String caseSk)
    {
        CaseRecord caserecord = new CaseRecord();
        caseDelegate = new CaseDelegate();
        try
        {
            caserecord = caseDelegate.getCaseDetails(new Long(caseSk));
        }
        catch (Exception e)
        {
        	if(logger.isDebugEnabled())
        	{
            logger.debug(e);
        	}
        }
        
        return caserecord;
    }

    /**
     * @return String.
     */
    public String submitClaimCase()
    {

        
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
     
        boolean flag = false;
        /** Holds facesContext */
        if (myTaskDataBean.getMyTaskCaseSK() != null)
        {
            flag = UpdateclaimCase();
        }
        if (flag)
        {
        	//for defect ESPRD00846674
        	//if the case record is claimed then record should be removed from the list hence
        	//data has to refreshed.
            FacesContext facesContext = FacesContext.getCurrentInstance();
            PortletSession pSession = (PortletSession) facesContext.getExternalContext()
					.getSession(true);
			pSession.setAttribute(ContactManagementConstants.RefreshMyTaskData,
					ContactManagementConstants.REFRESH_MY_TASK_CASE_DATA,
					pSession.APPLICATION_SCOPE);
            String caseSk = null;
            String entType = null;
            String entID = null;

            if (myTaskDataBean.getMyTaskCaseSK() != null)
            {
                caseSk = myTaskDataBean.getMyTaskCaseSK();
            }
            if (myTaskDataBean.getCaseEntityType() != null)
            {
                entType = myTaskDataBean.getCaseEntityType();
            }

            if (myTaskDataBean.getEntitySk() != null)
            {
                entID = myTaskDataBean.getEntitySk();
            }
            String caseClaimDet = caseSk + ":" + entType + ":" + entID;
            facesContext.getExternalContext().getRequestMap().put(
                    "MyTaskClaimCaseSK", caseClaimDet);
        }
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * Opencase ipc
     */
    public String openCase()
    {
    	boolean caseFilterStatus=false;
		CaseDelegate caseDelegate = new CaseDelegate();
		String userId=getUserID();
		
		try{
			caseFilterStatus = caseDelegate.caseFilterStatus(userId);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(!caseFilterStatus){
			 setErrorMessage(
                    EnterpriseMessageConstants.AUTHORITY_CR_ERROR_CODE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);  
		}else{

       
       
        /** Holds facesContext */
        FacesContext facesContext = FacesContext.getCurrentInstance();
    
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        CommonCMCaseDetailsVO commonCMCaseDetailsVO = new CommonCMCaseDetailsVO();
        if (myTaskDataBean.getMyTaskCaseSK() != null)
        {
            commonCMCaseDetailsVO.setCaseSK(myTaskDataBean.getMyTaskCaseSK());
            if(logger.isDebugEnabled())
            {
            	logger.debug("Casesk from CommonCMdetailsVo:"
                    + commonCMCaseDetailsVO.getCaseSK());
            }
        }
        if (myTaskDataBean.getCaseEntityType() != null)
        {
            commonCMCaseDetailsVO.setEntityType(myTaskDataBean
                    .getCaseEntityType());
            if(logger.isDebugEnabled())
            {
            logger.debug("CaseEntitytype from commondetailsvo:"
                    + commonCMCaseDetailsVO.getEntityType());
            }
        }
        if (myTaskDataBean.getEntitySk() != null)
        {
            commonCMCaseDetailsVO.setEntityID(myTaskDataBean.getEntitySk());
            if(logger.isDebugEnabled())
            {
            logger.debug("EntityID from CaseCMDetailsVo:"
                    + commonCMCaseDetailsVO.getEntityID());
            }
        }
        commonCMCaseDetailsVO.setActionCode("CaseUpdate");
        PortletSession pSession = (PortletSession) facesContext.getExternalContext().getSession(true);
        pSession.setAttribute(ContactManagementConstants.ClearInqEntyData,
                                        ContactManagementConstants.ClearInqEntyData, pSession.APPLICATION_SCOPE);
        facesContext.getExternalContext().getRequestMap().put("MyTaskCaseSk",
                commonCMCaseDetailsVO);
		}
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * this method calls when user clicks on claim case
     * @return boolean
     */
    private boolean UpdateclaimCase()
    {

        
       
        String userID = getUserID();
       
        Long userWorkUnitSk = getUserSK(userID);
        
        CaseRecord caseRecordUpdate = getCaseDetails();
        
        
        if (userWorkUnitSk != null)
        {
            

            WorkUnit assignToWorkUnit = caseRecordUpdate
                    .getCaseAssignedToWorkUnit();
            assignToWorkUnit.setWorkUnitSK(userWorkUnitSk);
            caseRecordUpdate.setCaseAssignedToWorkUnit(assignToWorkUnit);
           
            //
            CaseRouting caseRouting = new CaseRouting();
			try {
				Format formatter;
				formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
				String strDate = formatter.format(new Date());

				Date routeDate = (Date) formatter.parseObject(strDate);
				caseRouting.setCaseRoutingDate(routeDate);
			} catch (ParseException e) {
				if(logger.isErrorEnabled())
				{
				logger.error("Error occured while parsing Date object", e);
				}
			}
			caseRouting.setRoutedToWorkUnit(assignToWorkUnit);
			caseRouting.setWatchListIndicator(Boolean.valueOf(false));   /*FIND BUGS_FIX*/
			caseRouting.setAddedAuditTimeStamp(new Date());
			caseRouting.setAddedAuditUserID(userID);
			caseRouting.setAuditUserID(userID);
			caseRouting.setVersionNo(0);
			caseRouting.setRoutedByWorkUnit(assignToWorkUnit);
			EnterpriseUser enterpriseUser = new EnterpriseUser();
			enterpriseUser.setUserID(getUserID());
			WorkUnit unit = new WorkUnit();
			unit = caseRouting.getRoutedToWorkUnit();
			unit.setEnterpriseUser(enterpriseUser);
			caseRecordUpdate.setCaseAssignedToWorkUnit(unit);
			caseRouting.setCaseRecord(caseRecordUpdate);
			
			Set caseRoutingSet = caseRecordUpdate.getCaseRoutings();
			caseRoutingSet.add(caseRouting);
			
			caseRecordUpdate.setCaseRoutings(caseRoutingSet);
            //
        }

        caseDelegate = new CaseDelegate();
        boolean flag = false;

        try
        {
            caseDelegate.updateCaseForGroup(caseRecordUpdate);
            
            flag = true;
        }
        catch (CaseCreateBusinessException e)
        {
            flag = false;
            
            e.printStackTrace();
        }catch(Exception e){
        	e.printStackTrace();
        }
        

        return flag;

    }

    /**
     * This method is used to get the details for Correspondance.
     * @param caseSk
     * 			the caseSk
     * @return String
     */
    public String showDetailPortletForCase(String caseSk)
    {
        
       
        FacesContext fc = FacesContext.getCurrentInstance();
        

        PortletSession pSession = (PortletSession) fc.getExternalContext().getSession(true);
        pSession.setAttribute(ContactManagementConstants.ClearInqEntyData,
                                        ContactManagementConstants.ClearInqEntyData, pSession.APPLICATION_SCOPE);

        fc.getExternalContext().getRequestMap().put("MyTaskCaseSk", caseSk);
        if(logger.isDebugEnabled())
        {
        logger.debug("CaseSk fromContext"
                + fc.getExternalContext().getRequestMap().get("MyTaskCaseSk")
                        .toString());
        }

        return ContactManagementConstants.EMPTY_STRING;

    }

    /**
     *
     * @param event
     * 			the event of the action
     * @return String
     */

    public String sortCaseRecordNum(ActionEvent event)
    {
        
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        String sortColumn = (String) event.getComponent().getAttributes().get(
                MaintainContactManagementUIConstants.CLOUMN_NAME);
        String sortOrder = (String) event.getComponent().getAttributes().get(
                MaintainContactManagementUIConstants.SORT_ORDER);
       
        myTaskDataBean.setImageRender(event.getComponent().getId());
        //By default focus should come to first page after sort.
        myTaskDataBean.setStartIndexForMyCase(0);
        sortCaseRecordComparator(sortColumn, sortOrder, myTaskDataBean
                .getMyCaseRecordList());
        if(logger.isDebugEnabled())
        {
        logger.debug("List size in sortCaseRecordNum"
                + myTaskDataBean.getMyCaseRecordList().size());
        }
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     *
     * @param sortColumn
     * 		  the sortColumn to set
     * @param sortOrder
     * 			the sortOrder to set
     * @param dataList
     * 			the dataList to set
     */

    private void sortCaseRecordComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
        
      
        AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
    	alBean.setMyCrSort(true);
        Comparator comparator1 = new Comparator()
        {
            public int compare(Object temp, Object tempOne)
            {
               
                MyCaseRecordsVO caseRecordVO1 = (MyCaseRecordsVO) temp;
                MyCaseRecordsVO caseRecordVO2 = (MyCaseRecordsVO) tempOne;
                boolean ascending = false;
                if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
                        .equals(sortOrder))
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
                if (MaintainContactManagementUIConstants.MYCASE_REC_NO
                        .equals(sortColumn))
                {
                    
                    if (null == caseRecordVO1.getCaseRecNo())
                    {
                        caseRecordVO1
                                .setCaseRecNo(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecordVO2.getCaseRecNo())
                    {
                        caseRecordVO2
                                .setCaseRecNo(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? Integer.valueOf(
                            caseRecordVO1.getCaseRecNo()).compareTo(
                            Integer.valueOf(caseRecordVO2.getCaseRecNo()))
                            : Integer.valueOf(caseRecordVO2.getCaseRecNo())
                                    .compareTo(
                                            Integer.valueOf(caseRecordVO1
                                                    .getCaseRecNo()));
                }
                if (MaintainContactManagementUIConstants.MYCASE_OPEN_DATE
                        .equals(sortColumn))
                {
                	Date opendate1=ContactManagementHelper.getOpenEndedDate();
					Date opendate2=ContactManagementHelper.getOpenEndedDate();
					DateFormat dateFormat = new SimpleDateFormat(
                    "MM/dd/yyyy");
					try{
					if (null == caseRecordVO1.getOpenDate())
                    {
                        caseRecordVO1
                                .setOpenDate(MaintainContactManagementUIConstants.NULL);
                    }
                    else{
                    	opendate1=dateFormat.parse(caseRecordVO1.getOpenDate());
                    }
                    if (null == caseRecordVO2.getOpenDate())
                    {
                        caseRecordVO2
                                .setOpenDate(MaintainContactManagementUIConstants.NULL);
                    }
                    else{
                    	opendate2=dateFormat.parse(caseRecordVO2.getOpenDate());
                    }
                    }
                    catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ascending ? opendate1.compareTo(
                    		opendate2) : opendate2.compareTo(opendate1);
                }

                if (MaintainContactManagementUIConstants.MYCASE_PRV_CODE
                        .equals(sortColumn))
                {
                    if (null == caseRecordVO1.getPriorityCode())
                    {
                        caseRecordVO1
                                .setPriorityCode(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecordVO2.getPriorityCode())
                    {
                        caseRecordVO2
                                .setPriorityCode(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecordVO1.getPriorityCode()
                            .compareToIgnoreCase(caseRecordVO2.getPriorityCode())
                            : caseRecordVO2.getPriorityCode().compareToIgnoreCase(
                                    caseRecordVO1.getPriorityCode());
                }
                if (MaintainContactManagementUIConstants.MYCASE_STATUS_CODE
                        .equals(sortColumn))
                {
                    if (null == caseRecordVO1.getStatusCode())
                    {
                        caseRecordVO1
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecordVO2.getStatusCode())
                    {
                        caseRecordVO2
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecordVO1.getStatusCode().compareToIgnoreCase(
                            caseRecordVO2.getStatusCode()) : caseRecordVO2
                            .getStatusCode().compareToIgnoreCase(
                                    caseRecordVO1.getStatusCode());
                }
                if (MaintainContactManagementUIConstants.MYCASE_ENTY_TYPE
                        .equals(sortColumn))
                {
                    if (null == caseRecordVO1.getEntityType())
                    {
                        caseRecordVO1
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecordVO2.getEntityType())
                    {
                        caseRecordVO2
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecordVO1.getEntityType().compareToIgnoreCase(
                            caseRecordVO2.getEntityType()) : caseRecordVO2
                            .getEntityType().compareToIgnoreCase(
                                    caseRecordVO1.getEntityType());
                }
              //Added fro the defect ESPRD00709133 start
                if (MaintainContactManagementUIConstants.MYCASE_ENTY_NAME
                        .equals(sortColumn))
                {
                    if (null == caseRecordVO1.getEntityName())
                    {
                        caseRecordVO1
                                .setEntityName(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecordVO2.getEntityName())
                    {
                        caseRecordVO2
                                .setEntityName(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecordVO1.getEntityName().compareToIgnoreCase(
                            caseRecordVO2.getEntityName()) : caseRecordVO2
                            .getEntityName().compareToIgnoreCase(
                                    caseRecordVO1.getEntityName());
                }
				//ESPRD00709133 ended the defect
                if (MaintainContactManagementUIConstants.MYCASE_CASE_TYPE
                        .equals(sortColumn))
                {
                    if (null == caseRecordVO1.getCaseType())
                    {
                        caseRecordVO1
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecordVO2.getCaseType())
                    {
                        caseRecordVO2
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecordVO1.getCaseType().compareToIgnoreCase(
                            caseRecordVO2.getCaseType()) : caseRecordVO2
                            .getCaseType().compareToIgnoreCase(
                                    caseRecordVO1.getCaseType());
                }
                return MaintainContactManagementUIConstants.ZERO;

            }

        };
        Collections.sort(dataList, comparator1);
        
    }

    /**
     *
     * @param event
     * 			the event of the action
     * @return	String
     */

    public String sortGrpCaseRecordNum(ActionEvent event)
    {
        
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        String sortColumn = (String) event.getComponent().getAttributes().get(
                MaintainContactManagementUIConstants.CLOUMN_NAME);
        String sortOrder = (String) event.getComponent().getAttributes().get(
                MaintainContactManagementUIConstants.SORT_ORDER);
        
        myTaskDataBean.setImageRender(event.getComponent().getId());
        //By default focus should come to first page after sort.
        myTaskDataBean.setStartIndexForGrpCase(0);
        sortGrpCaseRecordComparator(sortColumn, sortOrder, myTaskDataBean
                .getGroupCaseRecList());
        
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

   /**
    *
    * @param sortColumn
    * 			sortColumn to set
    * @param sortOrder
    * 		sortOrder  to set
    * @param dataList
    * 		dataList  to set
    */

    private void sortGrpCaseRecordComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
    	 AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
     	alBean.setMyCrSort(true);
        Comparator comparator = new Comparator()
        {
            public int compare(Object temp, Object tempOne)
            {
                GroupCaseRecordsVO grpCaseRecordVO1 = (GroupCaseRecordsVO) temp;
                GroupCaseRecordsVO grpCaseRecordVO2 = (GroupCaseRecordsVO) tempOne;
                boolean ascending = false;
                if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
                        .equals(sortOrder))
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
                if (MaintainContactManagementUIConstants.GRPCASE_REC_NO
                        .equals(sortColumn))
                {
                    if (null == grpCaseRecordVO1.getCaseRecNo())
                    {
                        grpCaseRecordVO1
                                .setCaseRecNo(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == grpCaseRecordVO2.getCaseRecNo())
                    {
                        grpCaseRecordVO2
                                .setCaseRecNo(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? Integer.valueOf(
                            grpCaseRecordVO1.getCaseRecNo()).compareTo(
                            Integer.valueOf(grpCaseRecordVO2.getCaseRecNo()))
                            : Integer.valueOf(grpCaseRecordVO2.getCaseRecNo())
                                    .compareTo(
                                            Integer.valueOf(grpCaseRecordVO1
                                                    .getCaseRecNo()));
                }
                if (MaintainContactManagementUIConstants.GRPCASE_OPEN_DATE
                        .equals(sortColumn))
                {
                	Date opendate1=ContactManagementHelper.getOpenEndedDate();
					Date opendate2=ContactManagementHelper.getOpenEndedDate();
					DateFormat dateFormat = new SimpleDateFormat(
                    "MM/dd/yyyy");
					try{
                    if (null == grpCaseRecordVO1.getOpenDate())
                    {
                        grpCaseRecordVO1
                                .setOpenDate(MaintainContactManagementUIConstants.NULL);
                    }
                    else{
                    	opendate1=dateFormat.parse(grpCaseRecordVO1.getOpenDate());
                    }
               
                    if (null == grpCaseRecordVO2.getOpenDate())
                    {
                        grpCaseRecordVO2
                                .setOpenDate(MaintainContactManagementUIConstants.NULL);
                    }
                    else{
                    	opendate2=dateFormat.parse(grpCaseRecordVO2.getOpenDate());
                    }
					}
					catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Modified for Defect ESPRD00709277 as compareToIgnoreCase
                    return ascending ? opendate1
                            .compareTo(opendate2)
                            : opendate2.compareTo(
                            		opendate1);
                }

                if (MaintainContactManagementUIConstants.GRPCASE_PRV_CODE
                        .equals(sortColumn))
                {
                    if (null == grpCaseRecordVO1.getPriorityCode())
                    {
                        grpCaseRecordVO1
                                .setPriorityCode(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == grpCaseRecordVO2.getPriorityCode())
                    {
                        grpCaseRecordVO2
                                .setPriorityCode(MaintainContactManagementUIConstants.NULL);
                    }
                    //Modified for Defect ESPRD00709277 as compareToIgnoreCase
                    return ascending ? grpCaseRecordVO1.getPriorityCode()
                            .compareToIgnoreCase(grpCaseRecordVO2.getPriorityCode())
                            : grpCaseRecordVO2.getPriorityCode().compareToIgnoreCase(
                                    grpCaseRecordVO1.getPriorityCode());
                }
                if (MaintainContactManagementUIConstants.GRPCASE_ENTY_TYPE
                        .equals(sortColumn))
                {
                    if (null == grpCaseRecordVO1.getEntityType())
                    {
                        grpCaseRecordVO1
                                .setEntityType(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == grpCaseRecordVO2.getEntityType())
                    {
                        grpCaseRecordVO2
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    //Modified for Defect ESPRD00709277 as compareToIgnoreCase
                    return ascending ? grpCaseRecordVO1.getEntityType()
                            .compareToIgnoreCase(grpCaseRecordVO2.getEntityType())
                            : grpCaseRecordVO2.getEntityType().compareToIgnoreCase(
                                    grpCaseRecordVO1.getEntityType());
                }
                //ADDED FOR THE DEFECT ESPRDOO709277
                if (MaintainContactManagementUIConstants.GRPCASE_ENTY_NAME
                        .equals(sortColumn))
                {
                    if (null == grpCaseRecordVO1.getEntityName())
                    {
                        grpCaseRecordVO1
                                .setEntityName(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == grpCaseRecordVO2.getEntityName())
                    {
                        grpCaseRecordVO2
                                .setEntityName(MaintainContactManagementUIConstants.NULL);
                    }
                    //Modified for Defect ESPRD00709277 as compareToIgnoreCase
                    return ascending ? grpCaseRecordVO1.getEntityName()
                            .compareToIgnoreCase(grpCaseRecordVO2.getEntityName())
                            : grpCaseRecordVO2.getEntityName().compareToIgnoreCase(
                                    grpCaseRecordVO1.getEntityName());
                }
                //END FOR THE DEFECT ESPRDOO709277

                if (MaintainContactManagementUIConstants.GRPCASE_CASE_TYPE
                        .equals(sortColumn))
                {
                    if (null == grpCaseRecordVO1.getCaseType())
                    {
                        grpCaseRecordVO1
                                .setCaseType(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == grpCaseRecordVO2.getCaseType())
                    {
                        grpCaseRecordVO2
                                .setCaseType(MaintainContactManagementUIConstants.NULL);
                    }
                    //Modified for Defect ESPRD00709277 as compareToIgnoreCase
                    return ascending ? grpCaseRecordVO1.getCaseType()
                            .compareToIgnoreCase(grpCaseRecordVO2.getCaseType())
                            : grpCaseRecordVO2.getCaseType().compareToIgnoreCase(
                                    grpCaseRecordVO1.getCaseType());
                }
                if (MaintainContactManagementUIConstants.GRPCASE_STATUS_CODE
                        .equals(sortColumn))
                {
                    if (null == grpCaseRecordVO1.getStatusCode())
                    {
                        grpCaseRecordVO1
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == grpCaseRecordVO2.getStatusCode())
                    {
                        grpCaseRecordVO2
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    //Modified for Defect ESPRD00709277 as compareToIgnoreCase
                    return ascending ? grpCaseRecordVO1.getStatusCode()
                            .compareToIgnoreCase(grpCaseRecordVO2.getStatusCode())
                            : grpCaseRecordVO2.getStatusCode().compareToIgnoreCase(
                                    grpCaseRecordVO1.getStatusCode());
                }
                //Added for Work unit
                if (MaintainContactManagementUIConstants.GRPCASE_WORKUNIT_
                        .equals(sortColumn))
                {
                    if (null == grpCaseRecordVO1.getWorkUnit())
                    {
                        grpCaseRecordVO1
                                .setWorkUnit(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == grpCaseRecordVO2.getWorkUnit())
                    {
                        grpCaseRecordVO2
                                .setWorkUnit(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? grpCaseRecordVO1.getWorkUnit()
                            .compareToIgnoreCase(grpCaseRecordVO2.getWorkUnit())
                            : grpCaseRecordVO2.getWorkUnit().compareToIgnoreCase(
                                    grpCaseRecordVO1.getWorkUnit());
                }

                return MaintainContactManagementUIConstants.ZERO;

            }

        };

        Collections.sort(dataList, comparator);
        

    }

    /**
     *
     * @param event
     * 			the event to set
     * @return	String
     */

    public String sortCaseWatchList(ActionEvent event)
    {
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        String sortColumn = (String) event.getComponent().getAttributes().get(
                MaintainContactManagementUIConstants.CLOUMN_NAME);
        String sortOrder = (String) event.getComponent().getAttributes().get(
                MaintainContactManagementUIConstants.SORT_ORDER);
        
        myTaskDataBean.setImageRender(event.getComponent().getId());
        //By default focus should come to first page after sort.
        myTaskDataBean.setStartIndexForGrpCaseWtchList(0);
        sortCaseWatchListComparator(sortColumn, sortOrder, myTaskDataBean
                .getCaseRecWatchList());
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     *
     * @param sortColumn
     * 			the sortColumn to set
     * @param sortOrder
     * 			the sortOrder to set
     * @param dataList
     * 			the dataList to set
     */

    private void sortCaseWatchListComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
    	 AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
     	alBean.setMyCrSort(true);
        Comparator comparator = new Comparator()
        {
            public int compare(Object temp, Object tempOne)
            {
                CaseRecWatchListVO caseRecWatchVO1 = (CaseRecWatchListVO) temp;
                CaseRecWatchListVO caseRecWatchVO2 = (CaseRecWatchListVO) tempOne;
                boolean ascending = false;
                if (MaintainContactManagementUIConstants.SORT_TYPE_ASC
                        .equals(sortOrder))
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
                if (MaintainContactManagementUIConstants.CASEWL_REC_NO
                        .equals(sortColumn))
                {
                    if (null == caseRecWatchVO1.getCaseRecNo())
                    {
                        caseRecWatchVO1
                                .setCaseRecNo(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecWatchVO2.getCaseRecNo())
                    {
                        caseRecWatchVO2
                                .setCaseRecNo(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? Integer.valueOf(
                            caseRecWatchVO1.getCaseRecNo()).compareTo(
                            Integer.valueOf(caseRecWatchVO2.getCaseRecNo()))
                            : Integer.valueOf(caseRecWatchVO2.getCaseRecNo())
                                    .compareTo(
                                            Integer.valueOf(caseRecWatchVO1
                                                    .getCaseRecNo()));
                }
                if (MaintainContactManagementUIConstants.CASEWL_OPEN_DATE
                        .equals(sortColumn))
                {
                	Date opendate1=ContactManagementHelper.getOpenEndedDate();
					Date opendate2=ContactManagementHelper.getOpenEndedDate();
					DateFormat dateFormat = new SimpleDateFormat(
                    "MM/dd/yyyy");
					try{
					if (null == caseRecWatchVO1.getOpenDate())
                    {
                        caseRecWatchVO1
                                .setOpenDate(MaintainContactManagementUIConstants.NULL);
                    }
                    else{
                    	opendate1=dateFormat.parse(caseRecWatchVO1.getOpenDate());
                    }
                    if (null == caseRecWatchVO2.getOpenDate())
                    {
                        caseRecWatchVO2
                                .setOpenDate(MaintainContactManagementUIConstants.NULL);
                    }
                    else{
                    	opendate2=dateFormat.parse(caseRecWatchVO2.getOpenDate());
                    }
                    }
                    catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ascending ? opendate1.compareTo(
                    		opendate2) : opendate2.compareTo(opendate1);
                }

                if (MaintainContactManagementUIConstants.CASEWL_PRV_CODE
                        .equals(sortColumn))
                {
                    if (null == caseRecWatchVO1.getPriorityCode())
                    {
                        caseRecWatchVO1
                                .setPriorityCode(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecWatchVO2.getPriorityCode())
                    {
                        caseRecWatchVO2
                                .setPriorityCode(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecWatchVO1.getPriorityCode()
                            .compareToIgnoreCase(caseRecWatchVO2.getPriorityCode())
                            : caseRecWatchVO2.getPriorityCode().compareToIgnoreCase(
                                    caseRecWatchVO1.getPriorityCode());
                }
                if (MaintainContactManagementUIConstants.CASEWL_ENTY_TYPE
                        .equals(sortColumn))
                {
                    if (null == caseRecWatchVO1.getEntityType())
                    {
                        caseRecWatchVO1
                                .setEntityType(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecWatchVO2.getEntityType())
                    {
                        caseRecWatchVO2
                                .setEntityType(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecWatchVO1.getEntityType()
                            .compareToIgnoreCase(caseRecWatchVO2.getEntityType())
                            : caseRecWatchVO2.getEntityType().compareToIgnoreCase(
                                    caseRecWatchVO1.getEntityType());
                }
                if (MaintainContactManagementUIConstants.CASEWL_ENTY_NAME
                        .equals(sortColumn))
                {
                    if (null == caseRecWatchVO1.getEntityName())
                    {
                        caseRecWatchVO1
                                .setEntityName(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecWatchVO2.getEntityType())
                    {
                        caseRecWatchVO2
                                .setEntityName(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecWatchVO1.getEntityName()
                            .compareToIgnoreCase(caseRecWatchVO2.getEntityName())
                            : caseRecWatchVO2.getEntityName().compareToIgnoreCase(
                                    caseRecWatchVO1.getEntityName());
                }
                
                if (MaintainContactManagementUIConstants.CASEWL_CASE_TYPE
                        .equals(sortColumn))
                {
                    if (null == caseRecWatchVO1.getCaseType())
                    {
                        caseRecWatchVO1
                                .setCaseType(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecWatchVO2.getCaseType())
                    {
                        caseRecWatchVO2
                                .setCaseType(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecWatchVO1.getCaseType().compareToIgnoreCase(
                            caseRecWatchVO2.getCaseType()) : caseRecWatchVO2
                            .getCaseType().compareToIgnoreCase(
                                    caseRecWatchVO1.getCaseType());
                }

                if (MaintainContactManagementUIConstants.CASEWL_STATUS_CODE
                        .equals(sortColumn))
                {
                    if (null == caseRecWatchVO1.getStatusCode())
                    {
                        caseRecWatchVO1
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecWatchVO2.getStatusCode())
                    {
                        caseRecWatchVO2
                                .setStatusCode(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecWatchVO1.getStatusCode()
                            .compareToIgnoreCase(caseRecWatchVO2.getStatusCode())
                            : caseRecWatchVO2.getStatusCode().compareToIgnoreCase(
                                    caseRecWatchVO1.getStatusCode());
                }
                if (MaintainContactManagementUIConstants.CASEWL_USER_WORKUNIT
                        .equals(sortColumn))
                {
                    if (null == caseRecWatchVO1.getUserOrWorkUnit())
                    {
                        caseRecWatchVO1
                                .setUserOrWorkUnit(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == caseRecWatchVO2.getUserOrWorkUnit())
                    {
                        caseRecWatchVO2
                                .setUserOrWorkUnit(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? caseRecWatchVO1.getUserOrWorkUnit()
                            .compareToIgnoreCase(caseRecWatchVO2.getUserOrWorkUnit())
                            : caseRecWatchVO2.getUserOrWorkUnit().compareToIgnoreCase(
                                    caseRecWatchVO1.getUserOrWorkUnit());
                }
                return MaintainContactManagementUIConstants.ZERO;

            }

        };

        Collections.sort(dataList, comparator);
        
    }

    /**
     * @return Returns the allData.
     */
    public String getAllData()
    {
        EnterpriseUserProfile profile = getUserInfo();
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        if (profile == null)
        {
            
            setErrorMessage(
                    EnterpriseMessageConstants.ERR_SW_LOG_USER_UNAVAILABLE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
            myTaskDataBean.setWatchListNoData(true);
            myTaskDataBean.setAlertNoData(true);
            myTaskDataBean.setMyCrNoData(true);
            myTaskDataBean.setGrpCrNoData(true);

        }
        else
        {
        	myTaskDataBean.setAllData(false);
            AlertDataBean alBean = (AlertDataBean) getDataBean(ContactManagementConstants.ALERT_DATA_BEAN_NAME);
            FacesContext fc = FacesContext.getCurrentInstance();
            PortletSession pSession = (PortletSession) fc.getExternalContext().getSession(true);
            if(pSession.getAttribute(ContactManagementConstants.RefreshMyTaskData,
            		pSession.APPLICATION_SCOPE) != null)
    		{
    			String refreshMyTaskData = (String) pSession.getAttribute(ContactManagementConstants.RefreshMyTaskData,
    					pSession.APPLICATION_SCOPE);
    			if(refreshMyTaskData.equalsIgnoreCase(ContactManagementConstants.RefreshMyTaskData))
    			{
    				alBean.setMyCrSort(false);
    				myTaskDataBean.setMytaskCRsCounter(false);
    				myTaskDataBean.setMytaskGrpCRsCounter(false);
    				myTaskDataBean.setMytaskCRsWatchListCounter(false);
    				if(1 == myTaskDataBean.getTabIndex())
    				{
    					createMyCrsList(profile.getUserId());
    				}else if(2 == myTaskDataBean.getTabIndex())
    				{
    					createGrpCrsList(profile.getUserId());
    				}else if(3 == myTaskDataBean.getTabIndex())
    				{
    					createCRsWatchlist(profile);
    				}
    			//to refresh my task tab data (MyCase, GroupCase, CaseWatchList) on Save of case record.
				} else if (refreshMyTaskData
						.equalsIgnoreCase(ContactManagementConstants.REFRESH_MY_TASK_CASE_DATA)) {
					//for defect ESPRD00846674
					caseCancel();
					myTaskDataBean.setMytaskCaseRecsCounter(Boolean.FALSE);
					myTaskDataBean.setMytaskGrpCaseRecsCounter(Boolean.FALSE);
					myTaskDataBean
							.setMytaskCaseRecWatchListCounter(Boolean.FALSE);
					if (4 == myTaskDataBean.getTabIndex()) {
						createMyCaseRecList(profile.getUserId());
					} else if (5 == myTaskDataBean.getTabIndex()) {
						createGroupCaseRecList(profile.getUserId());
					} else if (6 == myTaskDataBean.getTabIndex()) {
						createCaseRecWatchlist(profile.getUserId());
					}
				}
    			pSession.removeAttribute(ContactManagementConstants.RefreshMyTaskData,pSession.APPLICATION_SCOPE);
    		}
 			 if(!alBean.isMyCrSort())
    		{   
 		        createAlertsList(profile.getUserId());//MyTask_Alerts_ESPRD00432772_11MAR2010
            	alBean.setMyCrSort(false);
    		}
        }
        myTaskDataBean.setExecuted(false);
        return allData;

    }

    
    /**
	 * This method used to create MyCaseRec List .
	 * 
	 * @param userID
	 *            The caseRecordList to set.
	 */

	public void createMyCaseRecList(String userID) {
		
		List caseRecordList = new ArrayList();
		List myCaseRecordList = new ArrayList();
		CaseDelegate caseDelegate = new CaseDelegate();
		MyTaskDOConverter myTaskDOConverter = new MyTaskDOConverter();
		try {
			caseRecordList = caseDelegate.getMyCaseRecords(userID);
			if(logger.isDebugEnabled())
			{
				logger.debug("myCaseVOList size is :" + caseRecordList.size());
			}

			myCaseRecordList = myTaskDOConverter
					.getMyCaseVOList(caseRecordList);
			if(logger.isDebugEnabled())
			{
				logger.debug("myCaseRecordList size:" + myCaseRecordList.size());
			}
			if (myTaskDataBean == null) 
			{
				
				myTaskDataBean = (MyTaskDataBean)getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
			}
			// this.myCaseRecordList = myCaseRecordList;
			myTaskDataBean.setMyCaseRecordList(myCaseRecordList);

		} catch (CaseRecordFetchBusinessException e) {
			e.printStackTrace();
			
		}

	}
  
    

    /**
	 * Method to get the list of crWatchList.
	 * 
	 * @param userID :
	 *            The userID to set.
	 */
	
	  public void createCaseRecWatchlist(String userID) {

		CaseDelegate caseDelegate = new CaseDelegate();
		MyTaskDOConverter myTaskDOConverter = new MyTaskDOConverter();
		List caseRecWatchList = new ArrayList();
		
		try {
			List watchList = caseDelegate.getCaseWatchList(userID);
			if(logger.isDebugEnabled())
			{
			logger.debug("Watchlist size:from delegate:" + watchList.size());
			}
			if (myTaskDataBean == null) 
			{
				
				myTaskDataBean = (MyTaskDataBean)getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
			}
			if (!watchList.isEmpty()) {
				getUsersList();
				caseRecWatchList = myTaskDOConverter.getCaseWatchListVOList(
						watchList, myTaskDataBean.getUserMap());

				myTaskDataBean.setWatchListNoData(false);
			}
			myTaskDataBean.setCaseRecWatchList(caseRecWatchList);

		} catch (CaseRecordFetchBusinessException e) {
			if(logger.isDebugEnabled())
			{
			logger.debug(e);
			}
		}

	}
	
	
    /**
     * This method used to create GroupCaseRecList .
     *
     * @param userID
     *            The userID to set.
     */
    
  
    public void createGroupCaseRecList(String userID) {
		

		List grpList = new ArrayList();
		List grpCaseRecordList = new ArrayList();
		CaseDelegate caseDelegate = new CaseDelegate();
		MyTaskDOConverter myTaskDOConverter = new MyTaskDOConverter();
		List deptNamesList = new ArrayList();

		
		if (myTaskDataBean == null) 
		{
			
			myTaskDataBean = (MyTaskDataBean)getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
		}
		Map deptMap = null;
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        try
        {
            List deptsList = contactMaintenanceDelegate.getDepartmentNmaesForUser(userID);
            deptMap = new HashMap();
            if (!deptsList.isEmpty())
            {
            	 int size = 0;
                size = deptsList.size();
                for (int i = 0; i < size; i++)
                {
                	Object[] dept = (Object[]) deptsList.get(i);
                	
                	if(dept[1] != null)
                	{
                		deptMap.put(dept[0].toString(),dept[1].toString());
                		deptNamesList.add(dept[1].toString());
                	}
                }
            }
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
        	if(logger.isDebugEnabled())
        	{
            logger.debug(e);
        	}
        }

		if (deptNamesList != null && !deptNamesList.isEmpty()) {
			try {
				grpList = caseDelegate.getGroupCaseRecords(deptNamesList);
				if(logger.isDebugEnabled())
				{
				logger.debug("grpList size is :" + grpList.size());
				}

				if (grpList.isEmpty()) {
					myTaskDataBean.setGrpCaseNoData(true);
				} else {
					grpCaseRecordList = myTaskDOConverter.getMyGrpCaseVOList(
							grpList, deptMap);
					if(logger.isDebugEnabled())
					{
					logger.debug("grpCaseRecordList size:"
							+ grpCaseRecordList.size());
					}
				}
				myTaskDataBean.setGroupCaseRecList(grpCaseRecordList);

			} catch (CaseRecordFetchBusinessException e) {
				if(logger.isDebugEnabled())
				{
				logger.debug(e);
				}
			}
		}

	}
    
    

    /**
	 * This method is used to create the alerts Vo list.
	 * 
	 * @param userid :
	 *            The userid to set.
	 */
    public void createAlertsList(String userid)
    {

       
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        List correspondenceList = null;
        List alertVolist = new ArrayList();
        //	CMProcessDelegate delegate = new CMProcessDelegate();
        cmdelegate  = new CMDelegate();
        MyTaskDOConverter converter = new MyTaskDOConverter();
        
        if (userid != null)
        {
            try
            {
                
                correspondenceList = cmdelegate.getAllAlert(userid);
              

            }

            catch (CorrespondenceRecordFetchBusinessException e)
            {
            	

            }

            if (correspondenceList == null || correspondenceList.isEmpty())
            {
            	
               myTaskDataBean.setAlertNoData(true);
               myTaskDataBean.setAlertCRDetails(false);
            }
            else
            {
            	if(logger.isDebugEnabled())
            		
            	{
            	 logger.debug("&&&&&&& in alert correspondenceList size :"
                        + correspondenceList.size());
            	}
                alertVolist = converter.getAlertsVOList(correspondenceList);
                if(myTaskDataBean.isAlertCRDetails() && !myTaskDataBean.isCrExists())
                {
                	myTaskDataBean.setAlertCRDetails(false);
                }
                else if(myTaskDataBean.isCaseDetails() && !myTaskDataBean.isCrExists())
                {
                	myTaskDataBean.setCaseDetails(false);
                }
                myTaskDataBean.setCrExists(false);
                alertsComparator(ContactManagementConstants.ALERT_TYPE_ID, ContactManagementConstants.ASCENDING, alertVolist);
            }
        }
       myTaskDataBean.setAlertVoList(alertVolist);

        
    }
    public String getAlertDates(){
    	//EnterpriseUserProfile profile = getUserInfo();
    	//String userId = profile.getUserId();
    	String userId = null;
    	FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		userId = getLoggedInUserID(portletRequest);
    	getAlertDates(userId);
    	return null;
    }
    /**
     * This method convert all the Correspondence do's to My group Cr vo's.
     *
     * @param userID
     *            The countMap to set.
     */
    public void getAlertDates(String userID)
    {

        Map countMap = null;
        MyTaskDataBean dataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        cmdelegate = new CMDelegate();
        try
        {
            countMap = cmdelegate.getAlertDates(userID);
           
        }
        catch (CorrespondenceRecordFetchBusinessException correspondenceRecordFetchBusinessException)
        {
        	if(logger.isErrorEnabled())
        	{
        	logger.error(correspondenceRecordFetchBusinessException.getMessage(), correspondenceRecordFetchBusinessException);
        	}
        }
       
        if (countMap != null)
        {
            Object firstCount = null;
            Object secondCount = null;
            Object thirdCount = null;
            Object fourCount = null;
          
            firstCount = countMap.get((Integer.valueOf(0)));  /*FIND BUGS_FIX*/
           
            if (firstCount != null)
            {
                dataBean.setFirstCount(firstCount.toString());
            }
            secondCount = countMap.get((Integer.valueOf(1)));  /*FIND BUGS_FIX*/
            
            if (secondCount != null)
            {
            	dataBean.setSecondCount(secondCount.toString());
            }
            thirdCount = countMap.get((Integer.valueOf(2)));   /*FIND BUGS_FIX*/
           
            if (thirdCount != null)
            {
            	dataBean.setThirdCount(thirdCount.toString());
            }
            fourCount = countMap.get(ContactManagementConstants.THREE);
           
            if (fourCount != null)
            {
            	dataBean.setFourCount(fourCount.toString());
            }
        }
        
    }

    /**
     * This method is used to create the Grp Vo list.
     *
     * @param userID :
     *            The userID to set.
     */
    public void createMyCrsList(String userID)
    {
        
    	

        List myCrsList = null;
        List myCrVolist = new ArrayList();
    
        cmdelegate = new CMDelegate();
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        MyTaskDOConverter converter = new MyTaskDOConverter();

        if (userID != null)
        {
            try
            {
                
            	
            	myCrsList = cmdelegate.getAllCorrespondencRecords(userID);
            	
            }
            catch (CorrespondenceRecordFetchBusinessException e)
            {
            	 e.printStackTrace();
            }

            if (myCrsList == null || myCrsList.isEmpty())
            {
            	
               myTaskDataBean.setMyCrNoData(true);
               myTaskDataBean.setMyCrsDetails(false);

            }
            else
            {
            	if(logger.isDebugEnabled())
            	{
            		logger
            			.debug("in create My Crs List correspondenceList size :"
            					+ myCrsList.size());
            	}
                myCrVolist = converter.getMyCrVOList(myCrsList);
                if(myTaskDataBean.isMyCrsDetails() && !myTaskDataBean.isCrExists())
                {
                	myTaskDataBean.setMyCrsDetails(false);
                }
                myTaskDataBean.setCrExists(false);
                myTaskDataBean.setMyCrNoData(false);
                if(logger.isInfoEnabled())
                {
                logger.info("myCrVolist size====="+myCrVolist.size());
                }
           
            }
        }
       myTaskDataBean.setMyCrsList(myCrVolist);

        
    }

    /**
     * This method is used to create the Grp Vo list.
     *
     * @param userID :
     *            The userSK to set.
     */
    public void createGrpCrsList(String userID)
    {
       
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        List grpCrs = null;
        List grpCrVolist = new ArrayList();
        cmdelegate = new CMDelegate();
        MyTaskDOConverter converter = new MyTaskDOConverter();
        List deptNamesList = new ArrayList();
        Map deptMap = null;
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        try
        {
            List deptsList = contactMaintenanceDelegate.getDepartmentNmaesForUser(userID);
            deptMap = new HashMap();
            if (!deptsList.isEmpty())
            {
            	 int size = 0;
                size = deptsList.size();
                for (int i = 0; i < size; i++)
                {
                	Object[] dept = (Object[]) deptsList.get(i);
                	
                	if(dept[1] != null)
                	{
                		deptMap.put(dept[0].toString(),dept[1].toString());
                		deptNamesList.add(dept[1].toString());
                	}
                }
            }
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            e.printStackTrace();
        	
        }
      
        if (deptNamesList != null && !deptNamesList.isEmpty())
        {
            try
            {
                grpCrs = cmdelegate.getGroupCorresponce(deptNamesList);
               

            }
            catch (CorrespondenceRecordFetchBusinessException e)
            {
               e.printStackTrace();
            }
        }
        if (grpCrs == null || grpCrs.isEmpty())
        {
            if (grpCrs != null && grpCrs.isEmpty())
            {
            	if(logger.isDebugEnabled())
            	{
                logger.debug("In createGrpCrsList() is  EMPTYYYYYYY ");
            	}
            }
            else
            {
            	if(logger.isDebugEnabled())
            	{
                logger.debug("In createGrpCrsList() is  NULL ");
            	}
            }
            myTaskDataBean.setGrpCrNoData(true);

        }
        else
        {
        	if(logger.isDebugEnabled())
        	{
        	logger
            .debug("in create Grp Crs List correspondenceList size :"
                    + grpCrs.size());
        	}
           
            grpCrVolist = converter.getMyGrpCrVOList(grpCrs,deptMap);
            if(myTaskDataBean.isGroupCRDetails() && !myTaskDataBean.isCrExists())
            {
            	myTaskDataBean.setGroupCRDetails(false);
            }
            myTaskDataBean.setCrExists(false);
            if(logger.isInfoEnabled())
            {
            logger.info("grpCrVolist size=="+grpCrVolist.size());
            }

        }
     
        grpCrComparator(ContactManagementConstants.MYTASK_CRN,"ascending",grpCrVolist);
        
        myTaskDataBean.setMyGrpCRsList(grpCrVolist);
        
    }

    /**
     * Method to get the list of crWatchList.
     *
     * @param userID :
     *            The userID to set.
     */
    public void createCRsWatchlist(EnterpriseUserProfile userProfile)
    {

       
    	cmdelegate = new CMDelegate();
        MyTaskDOConverter converter = new MyTaskDOConverter();
        List crWatchListVolist = new ArrayList();
        List correspondenceList = null;
        
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        if (userProfile != null)
        {
            try
            {
                
                correspondenceList = cmdelegate.getCRsWatchlist(userProfile.getUserId());
                
            
            }
            catch (CorrespondenceRecordFetchBusinessException e)
            {
                if(logger.isErrorEnabled())
                {
                logger.error(e);
                }
            }

           
            if (correspondenceList == null || correspondenceList.isEmpty())
            {
            	  
            	myTaskDataBean.setCrWatchListNoData(true);
            }
            else
            {
            	if(logger.isDebugEnabled())
            	{
            	logger.debug("---->>>>>> getCRsWatchlist size is"
                        + correspondenceList.size());
            	}
            	

            	String userName = userProfile.getFirstName()
     	                + MaintainContactManagementUIConstants.EMPTY_STRING_SPACE
     	                + userProfile.getLastName();
            	if(logger.isInfoEnabled())
            	{
     	        logger.info("Enterprise user name===="+userName);
            	}
                crWatchListVolist = converter
                        .getCrWatchListVOList(correspondenceList,userName);
                if(myTaskDataBean.isCrsWatchListDetails() && !myTaskDataBean.isCrExists())
                {
                	myTaskDataBean.setCrsWatchListDetails(false);
                }
                myTaskDataBean.setCrExists(false);
                getMyTaskDataBean().setCrWatchListNoData(false);
                
            }
        }

       myTaskDataBean.setCrWatchList(crWatchListVolist);
        
    }

    /**
     * @param allData The allData to set.
     */
    public void setAllData(String allData)
    {
        this.allData = allData;
    }

    /**
     * This method used for setting error display messages.
     *
     * @param errorName :
     *            String errorName.
     * @param arguments :
     *            Array of Object. Parameters to be passed to the message.
     * @param messageBundle :
     *            String messageBundle.
     * @param componentId :
     *            String componentId.
     */
    private void setErrorMessage(String errorName, Object[] arguments,
            String messageBundle, String componentId)
    {
        
       

        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        Locale locale = root.getLocale();
        String clientId = null;

        facesContext.getApplication().setMessageBundle(messageBundle);
        String errorMsg = MessageUtil.format(messageBundle, errorName,
                arguments, locale);
        if(logger.isDebugEnabled())
        {
        logger.debug("Error mesg in setErrorMessage :" + errorMsg);
        }
        FacesMessage fc = new FacesMessage();
        fc.setDetail(errorMsg);

        if (componentId != null)
        {
            if(logger.isDebugEnabled())
            {
        	 logger.debug("Component ID " + componentId);
            }
            UIComponent uiComponent = findComponentInRoot(componentId);
            clientId = uiComponent.getClientId(facesContext);
            if(logger.isDebugEnabled())
            {
            logger.debug("Client Id " + clientId);
            }
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
    private UIComponent findComponentInRoot(String id)
    {
        

       
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
    private UIComponent findComponent(UIComponent base, String id)
    {
        

      

      
        UIComponent dataReturn = null;
      
        if (id.equals(base.getId()))
        {
            dataReturn = base;
        }
        else
        {
          
            UIComponent component = null;
            Iterator cmpIterator = base.getFacetsAndChildren();

            while (cmpIterator.hasNext() && (dataReturn == null))
            {
                component = (UIComponent) cmpIterator.next();
                if (id.equals(component.getId()))
                {
                    dataReturn = component;
                    break;
                }
                dataReturn = findComponent(component, id);
                if (dataReturn != null)
                {
                    break;
                }
            }
        }
        
        return dataReturn;
    }

    /**
     *
     * @param userSK
     * 			userSK to set
     * @return  String
    
    public String getUserName(String userSK)
    {
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        String userName = null;
        Map userMap = myTaskDataBean.getUserMap();
        if (!userMap.isEmpty())
        {
            logger.debug("UserSK from getWatchList is $$ " + userSK);
            userName = (String) userMap.get(userSK);
            logger.debug("User Name in helper is $$ " + userName);
        }
        
        return userName;
    } */


	/**
	 * @return Returns the usersList.
	 */
    
    //Code commented below for new API on 07/03/2012.
	
    /*public String getUsersList() {
		
	        caseDelegate = new CaseDelegate();
	        List userData = null;
	        int size = 0;
	        EnterpriseUser enterpriseUser = null;
	        String firstName = null;
	        String lastName = null;
	        String name = null;
	        String userId = null;
	        Map usersMap = new HashMap();
	        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
	        try
	        {
	            userData = caseDelegate.getAllUsers();
	            if (!userData.isEmpty())
	            {
	                size = userData.size();
	                for (int i = 0; i < size; i++)
	                {
	                    enterpriseUser = (EnterpriseUser) userData.get(i);
	                    if (enterpriseUser.getFirstName() != null)
	                    {
	                        firstName = enterpriseUser.getFirstName();
	                        lastName = enterpriseUser.getLastName();
	                        userId = enterpriseUser.getUserWorkUnitSK().toString();
	                        name = firstName + MaintainContactManagementUIConstants.EMPTY_STRING_SPACE + lastName;
	                        usersMap.put(enterpriseUser.getUserWorkUnitSK()
	                                .toString(), name);
	                    }
	                }
	            }
	            myTaskDataBean.setUserMap(usersMap);
	        }
	        catch (CaseRecordFetchBusinessException e)
	        {
	        	logger.error("Exception occured at getUsersList()" + e);
	        }
	      
	        myTaskDataBean.setUserListFlag(false);
		return MaintainContactManagementUIConstants.SUCCESS;
	}*/
    
    //Code Added below for new API on 07/03/2012.
    
    public String getUsersList(){
    	ContactMaintenanceDelegate contactmaintenanceDelegate = new ContactMaintenanceDelegate();
    	List userData = null;
    	int size = 0;
    	DeptUserBasicInfo deptUserBasicInfo = null;
    	String firstName = null;
        String lastName = null;
        String name = null;
        String userId = null;
        String userWorkUnitSK = null;
        Map usersMap = new HashMap();
        myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
        try{
        	deptUserBasicInfo = new DeptUserBasicInfo();
        	deptUserBasicInfo.setDataReqested("userIdFirstLastNameWorkUnitId");
        	userData = contactmaintenanceDelegate.getEnterpriseUserDetails(deptUserBasicInfo);
        	if(!userData.isEmpty()){
        		size = userData.size();
        		for(int i = 0;i < size; i++){
        			deptUserBasicInfo =(DeptUserBasicInfo) userData.get(i);
        			if(deptUserBasicInfo.getFirstName() != null){
        				firstName = deptUserBasicInfo.getFirstName();
        				lastName = deptUserBasicInfo.getLastName();
        				userId = deptUserBasicInfo.getUserID();
        				userWorkUnitSK = deptUserBasicInfo.getUserWorkUnitSK().toString();
        				name = firstName + MaintainContactManagementUIConstants.EMPTY_STRING_SPACE + lastName;
        				usersMap.put(userWorkUnitSK, name);
        			}
        		}
        	}
        	myTaskDataBean.setUserMap(usersMap);
        }
        	catch (Exception e)
	        {
        		if(logger.isErrorEnabled())
        		{
	        	logger.error("Exception occured at getUsersList()" + e);
        		}
	        }
	      
	        myTaskDataBean.setUserListFlag(false);
		return MaintainContactManagementUIConstants.SUCCESS;
        }
    
    
    /**
	 * @param myCaseRecordsVO
	 * @param entityTypeCd
	 * @return
	 */
/*	private String getEntityName(String entityTypeCd, Long entitySk) {

		String entityName = null;
		if (entityTypeCd != null) {
			StringBuffer stringBuffer = new StringBuffer();
			if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM)) {

				MemberInformationDelegate memberDelegate = new MemberInformationDelegate();
				MemberInformationRequestVO memberVO = new MemberInformationRequestVO();
				try {
					Long systemID = memberDelegate.getMemberID(entitySk);
					if (systemID != null) {
						memberVO.setMemberSysID(systemID);
						Member member = memberDelegate
								.getMemberDetail(memberVO);
						if (member != null) {
							if (member.getDemographicInformation().getName()
									.getFirstName() != null) {
								stringBuffer.append(member
										.getDemographicInformation().getName()
										.getFirstName());
								stringBuffer.append(" ");
							}
							if (member.getDemographicInformation().getName()
									.getMiddleName() != null) {
								stringBuffer.append(member
										.getDemographicInformation().getName()
										.getMiddleName());
								stringBuffer.append(" ");
							}
							if (member.getDemographicInformation().getName()
									.getLastName() != null) {
								stringBuffer.append(member
										.getDemographicInformation().getName()
										.getLastName());
							}
						}
						entityName = stringBuffer.toString();
					}
				} catch (MemberBusinessException e) {
					logger.error(e.getMessage(), e);
				}
			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_PROV)) {

				ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
				ProviderInformationRequestVO providerVO = new ProviderInformationRequestVO();
				try {
					Long pSysId = providerInformationDelegate
							.getProviderSysID(entitySk);
					if (pSysId != null) {
						providerVO.setProviderSysID(pSysId);
						Provider provider = providerInformationDelegate
								.getProviderDetails(providerVO);
						if (provider != null
								&& provider.getName().getSortName() != null) {
							entityName = provider.getName().getSortName();
						}
					}
				} catch (EnterpriseBaseBusinessException e) {
					logger.error(e.getMessage(), e);
				}

			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNMEM)
					|| entityTypeCd
							.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV)) {

				CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
				try {
					SpecificEntity specficEntity = cmEntityDelegate
							.getSpecificEntityDetails(entitySk);
					if (specficEntity != null) {
						StringBuffer sb = new StringBuffer();
						if (specficEntity.getName().getFirstName() != null) {
							sb.append(specficEntity.getName().getFirstName());
							sb.append(" ");
						}
						if (specficEntity.getName().getMiddleName() != null) {
							sb.append(specficEntity.getName().getMiddleName());
							sb.append(" ");
						}
						if (specficEntity.getName().getLastName() != null) {
							sb.append(specficEntity.getName().getLastName());
							sb.append(" ");
						}
						if (specficEntity.getOrganizationName() != null) {
							sb.append(specficEntity.getOrganizationName());
						}
						entityName = sb.toString();
					}
				} catch (CMEntityFetchBusinessException e) {
					logger.error(e.getMessage(), e);
				}

			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TPL)) {

				CMDelegate cmDelegate = new CMDelegate();
				try {
					TPLCarrier tplCarrier = cmDelegate.getTPLCarrier(entitySk);
					if (tplCarrier != null
							&& tplCarrier.getCarrierName() != null) {
						entityName = tplCarrier.getCarrierName();
					}
				} catch (CorrespondenceRecordFetchBusinessException e) {
					logger.error(e.getMessage(), e);

				}
			}
		}
		return entityName;
	}
	*/
//	MyTask_Alerts_ESPRD00432772_11MAR2010
	public String tplMsqSaAlertDetailsCancel(){
		
		   myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
		   
		   // commented for heap dump issue
	        /*HtmlPanelTabbedPane pane = myTaskDataBean.getHtmlPanelTabbedPane();
	        if(pane!=null && pane.getSelectedIndex()>-1){
	        	int index = pane.getSelectedIndex();
	        	
	        	if(index == 0)
	        	pane.setSelectedIndex(0);
	        	else if(index == 1){
	            	pane.setSelectedIndex(1);
	            	
	        	}
	        	else if(index == 2){
	            	pane.setSelectedIndex(2);
	            	
	        	}
	        	else if(index == 3){
	            	pane.setSelectedIndex(3);
	            	
	        	}
	        	else if(index == 4){
	            	pane.setSelectedIndex(4);
	            	
	        	}
	        	else if(index == 5){
	            	pane.setSelectedIndex(5);
	            	
	        	}
	        	else if(index == 6){
	        		pane.setSelectedIndex(6);
	        		
	        	}
	        	
	        }*/
	        myTaskDataBean.setShowTplRecoeryCaseAlertDetail(false);	        
	        
		return ContactManagementConstants.RENDER_SUCCESS;
	}	//EOF MyTask_Alerts_ESPRD00432772_11MAR2010
	
	public String openTPLRecoveryCaseRecord(){//method added for UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010
		
//		UC-PGM-CRM-052.1_ESPRD00437600_25MAR2010

		TPLRecoverySearchResultVO tplRecoverySearchResultVO = new TPLRecoverySearchResultVO();
		tplRecoverySearchResultVO.setRecoveryCaseID(myTaskDataBean.getAlertDetailsVO().getTplCaseUserID());
		ActionRequest request = (ActionRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("sendVO1", tplRecoverySearchResultVO);

		//EOF UC-PGM-CRM-052.1_ESPRD00437600_25MAR2010
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}
	
	//added for UC-PGM-CRM-052.1_ESPRD00436245_18MAR2010
	public String openSADetails(){//modified for UC-PGM-CRM-052.1_ESPRD00437600_25MAR2010
		
		ActionRequest request = (ActionRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("sourceSID",myTaskDataBean.getAlertDetailsVO().getServiceAuthorizationID());
		
		return ContactManagementConstants.RENDER_SUCCESS;
	}
	//Modified for defect ESPRD00830192 to fix wiring issue from my task portlet to MSQ portlet
	public String openMSQDetails(){
		logger.debug("Inside openMSQDetails() @ Start");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map requestScope = (Map) facesContext.getApplication().createValueBinding("#{requestScope}").getValue(facesContext);
		if(requestScope!=null){
			String memberName = null;
			String lastName = null;
			String firstName = null;
			String middleName = null;
			String suffixName  = null;
			CommonMemberDetailsVO memDetailVo =new CommonMemberDetailsVO();
			memDetailVo.setMemberSysID(myTaskDataBean.getAlertDetailsVO().getMemberID());
			memDetailVo.setMenuActionCode(MaintainContactManagementUIConstants.MEM_TPL_MSQ);
			try{
				if(memDetailVo.getMemberSysID()!=null){
					MemberInformationDelegate memberInformationDelegate = new MemberInformationDelegate();
					List memIDs = new ArrayList();
					logger.debug("memDetailVo.getMemberSysID() --->"+memDetailVo.getMemberSysID());
					memIDs.add(new Long(memDetailVo.getMemberSysID()));
					List memmbers = memberInformationDelegate.getMemberDetails(memIDs);
					
					if(memmbers!=null && memmbers.size()!=0){
						Member member = (Member)memmbers.get(0);
						if(member!=null){ 							
							if(member.getDemographicInformation().getName().getLastName()!=null &&
									!member.getDemographicInformation().getName().getLastName().trim().equalsIgnoreCase("")){
							lastName = member.getDemographicInformation().getName().getLastName();
							}
							if(member.getDemographicInformation().getName().getFirstName()!=null &&
									!member.getDemographicInformation().getName().getFirstName().trim().equalsIgnoreCase("")){
				        	firstName = member.getDemographicInformation().getName().getFirstName();
							}
							if(member.getDemographicInformation().getName().getMiddleName()!=null &&
									!member.getDemographicInformation().getName().getMiddleName().trim().equalsIgnoreCase("")){
				        	middleName = member.getDemographicInformation().getName().getMiddleName();
							}
							if(member.getDemographicInformation().getName().getSuffixName()!=null &&
									!member.getDemographicInformation().getName().getSuffixName().trim().equalsIgnoreCase("")){
				        	suffixName = member.getDemographicInformation().getName().getSuffixName();
							}
							memDetailVo.setAltID(member.getCurrAltID());
							memDetailVo.setLobCode("MED");
							memDetailVo.setMemberIdType(member.getCurrAltIDTypeCode());		
							
							if(lastName != null && firstName != null)
					        {
					        	memberName = lastName+", "+firstName;
					        	memDetailVo.setLastName(lastName);
					        	memDetailVo.setFirstName(firstName);
					        	if((suffixName != null && !suffixName.trim().equalsIgnoreCase("")) &&
					        			!suffixName.trim().equalsIgnoreCase("null") && 
					        			middleName != null && !middleName.trim().equalsIgnoreCase("") &&
					        			!middleName.trim().equalsIgnoreCase("null"))
					        	{
					        		memberName = lastName+", "+suffixName+" "+firstName+" "+middleName;
					        		memDetailVo.setSuffixName(suffixName);
					        		memDetailVo.setMiddleInitial(middleName);
					        	}
					        	else if(suffixName != null && !suffixName.trim().equalsIgnoreCase("") && 
					        			!suffixName.trim().equalsIgnoreCase("null"))
					        	{		        
					        		memberName = lastName+", "+suffixName+" "+firstName;
					        		memDetailVo.setSuffixName(suffixName);
					        	}
					        	else if(middleName != null && !middleName.trim().equalsIgnoreCase("") &&
					        			!middleName.trim().equalsIgnoreCase("null"))
					        	{		        	
					        		memberName = lastName+", "+firstName+" "+middleName;
					        		memDetailVo.setMiddleInitial(middleName);
					        	}
					        	memDetailVo.setMemberName(memberName);
					        	logger.debug("Member Name --->"+memDetailVo.getMemberName());
					        } 
						}						
					}
				}
				
			}
			
			catch(MemberBusinessException e){
				if(logger.isErrorEnabled())
				{
				logger.error("Unable to set member details to CommonMemberDetailsVO :: "+e);
				}
			}
			requestScope.remove("MemberDetail");
			requestScope.put("MemberDetail",memDetailVo);
			logger.debug("Inside openMSQDetails() @ End");
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}


    /**
     * This method is to create an instance of Data Bean.
     *
     * @return Returns EDMSDefaultsDataBean
     */
    private MyTaskDataBean getMyTaskDataBean()
    {
    	if(myTaskDataBean==null){
       
        FacesContext fc = FacesContext.getCurrentInstance();
	        myTaskDataBean = (MyTaskDataBean) fc.getApplication()
                .createValueBinding("#{" + ContactManagementConstants.MYTASK_DATA_BEAN_NAME + "}")
                .getValue(fc);
        

    	}
        return myTaskDataBean;
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
	 * Gets application stream code - either PE or MMIS
	 */
	private void getApplicationStreamName ()
	{
		
		try {
	    	FacesContext context = FacesContext.getCurrentInstance();
	    	RenderRequest renderRequest = null;
	    	myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
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

	    		myTaskDataBean.setApplicationNameFlag(true);
		} else {
			myTaskDataBean.setApplicationNameFlag(false);
		}
	    	if(logger.isInfoEnabled())
	    	{
	    		logger.info("++ Application Name:::::::::::" + appName);
	    		logger.info("++ getAttachmentDataBean().isApplicationNameFlag():::::::::::" + myTaskDataBean.isApplicationNameFlag());
	    	}
    	} catch (Exception exc) {
    		
    		exc.printStackTrace();
    	}
    	
    	
    	if (appName == null) {
    		appName = "";
    	}
	}
	private boolean executed;
	/**
	 * @return Returns the executed.
	 */
	public boolean isExecuted() {
		return executed;
	}
	/**
	 * @param executed The executed to set.
	 */
	public void setExecuted(boolean executed) {
		this.executed = executed;
	}
	 /**
     * This method is used to convert String object to Date object.
     * 
     * @param begdate
     *            This contains the begin Date.
     * @return Date
     */
    private static Date dateConverter(String begdate)
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
                	if(logger.isErrorEnabled())
                	{
                    logger.error(e.getMessage(), e);
                	}
                }
            }
        }
        return beginDate;
    }
}
