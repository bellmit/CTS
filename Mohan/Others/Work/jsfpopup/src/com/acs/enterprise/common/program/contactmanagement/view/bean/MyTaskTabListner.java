/*
 * Created on Dec 20, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.bean;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.myfaces.custom.tabbedpane.TabChangeEvent;
import org.apache.myfaces.custom.tabbedpane.TabChangeListener;

//import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author smohammed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MyTaskTabListner implements TabChangeListener {

	private MyTaskDataBean myTaskDataBean;
	private MyTaskControllerBean myTaskControllerBean;
	//commented for unused variables
	//private EnterpriseBaseControllerBean enterpriseBaseControllerBean;
	//commented for unused variables
	//private EnterpriseUserProfile enterpriseUserProfile;
	
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(MyTaskTabListner.class);
	
	/**
	 * 
	 */
	public MyTaskTabListner() {
		super();
		// TODO Auto-generated constructor stub
	}

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
    
       	/* (non-Javadoc)
	 * @see org.apache.myfaces.custom.tabbedpane.TabChangeListener#processTabChange(org.apache.myfaces.custom.tabbedpane.TabChangeEvent)
	 */
	public void processTabChange(TabChangeEvent event)
			throws AbortProcessingException {
		myTaskDataBean = (MyTaskDataBean) getDataBean(ContactManagementConstants.MYTASK_DATA_BEAN_NAME);
		myTaskControllerBean = (MyTaskControllerBean)getDataBean(ContactManagementConstants.MYTASK_CONTROLLER_BEAN_NAME);
		
		
		
		 EnterpriseUserProfile profile = myTaskControllerBean.getUserInfo();
		 
		if(event.getNewTabIndex() == 1){
			
			if(!myTaskDataBean.isMytaskCRsCounter()){
				myTaskControllerBean.createMyCrsList(profile.getUserId());
				myTaskDataBean.setMytaskCRsCounter(true);
			}
		
			myTaskDataBean.setMytaskAlertFlag(false);
			myTaskDataBean.setMytaskGrpCRsFlag(false);
			myTaskDataBean.setMytaskCRsWatchListFlag(false);
			myTaskDataBean.setMytaskCaseRecsFlag(false);
			myTaskDataBean.setMytaskGrpCaseRecsFlag(false);
			myTaskDataBean.setMytaskCaseRecWatchListFlag(false);
			myTaskDataBean.setMytaskCRsFlag(true);
		}else if(event.getNewTabIndex() == 2){
			
			if(!myTaskDataBean.isMytaskGrpCRsCounter()){
				myTaskControllerBean.createGrpCrsList(profile.getUserId());
				myTaskDataBean.setMytaskGrpCRsCounter(true);
			}
		
			myTaskDataBean.setMytaskAlertFlag(false);
			myTaskDataBean.setMytaskGrpCRsFlag(true);
			myTaskDataBean.setMytaskCRsWatchListFlag(false);
			myTaskDataBean.setMytaskCaseRecsFlag(false);
			myTaskDataBean.setMytaskGrpCaseRecsFlag(false);
			myTaskDataBean.setMytaskCaseRecWatchListFlag(false);
			myTaskDataBean.setMytaskCRsFlag(false);
		}else if(event.getNewTabIndex() == 3){
			
			if(!myTaskDataBean.isMytaskCRsWatchListCounter()){
				myTaskControllerBean.createCRsWatchlist(profile);
				myTaskDataBean.setMytaskCRsWatchListCounter(true);
			}
		
			myTaskDataBean.setMytaskAlertFlag(false);
			myTaskDataBean.setMytaskGrpCRsFlag(false);
			myTaskDataBean.setMytaskCRsWatchListFlag(true);
			myTaskDataBean.setMytaskCaseRecsFlag(false);
			myTaskDataBean.setMytaskGrpCaseRecsFlag(false);
			myTaskDataBean.setMytaskCaseRecWatchListFlag(false);
			myTaskDataBean.setMytaskCRsFlag(false);
		}else if(event.getNewTabIndex() == 4){
			
			if(!myTaskDataBean.isMytaskCaseRecsCounter()){
				//for defect ESPRD00846674
				myTaskControllerBean.caseCancel();
				myTaskControllerBean.createMyCaseRecList(profile.getUserId());
				myTaskDataBean.setMytaskCaseRecsCounter(true);
			}
		
			myTaskDataBean.setMytaskAlertFlag(false);
			myTaskDataBean.setMytaskGrpCRsFlag(false);
			myTaskDataBean.setMytaskCRsWatchListFlag(false);
			myTaskDataBean.setMytaskCaseRecsFlag(true);
			myTaskDataBean.setMytaskGrpCaseRecsFlag(false);
			myTaskDataBean.setMytaskCaseRecWatchListFlag(false);
			myTaskDataBean.setMytaskCRsFlag(false);
		}else if(event.getNewTabIndex() == 5){
			
			if(!myTaskDataBean.isMytaskGrpCaseRecsCounter()){
				//for defect ESPRD00846674
				myTaskControllerBean.caseCancel();
				myTaskControllerBean.createGroupCaseRecList(profile.getUserId());
				myTaskDataBean.setMytaskGrpCaseRecsCounter(true);
			}
		
			myTaskDataBean.setMytaskAlertFlag(false);
			myTaskDataBean.setMytaskGrpCRsFlag(false);
			myTaskDataBean.setMytaskCRsWatchListFlag(false);
			myTaskDataBean.setMytaskCaseRecsFlag(false);
			myTaskDataBean.setMytaskGrpCaseRecsFlag(true);
			myTaskDataBean.setMytaskCaseRecWatchListFlag(false);
			myTaskDataBean.setMytaskCRsFlag(false);
		}else if(event.getNewTabIndex() == 6){
			
			if(!myTaskDataBean.isMytaskCaseRecWatchListCounter()){
				//for defect ESPRD00846674
				myTaskControllerBean.caseCancel();
				myTaskControllerBean.createCaseRecWatchlist(profile.getUserId());
				myTaskDataBean.setMytaskCaseRecWatchListCounter(true);
			}
		
			myTaskDataBean.setMytaskAlertFlag(false);
			myTaskDataBean.setMytaskGrpCRsFlag(false);
			myTaskDataBean.setMytaskCRsWatchListFlag(false);
			myTaskDataBean.setMytaskCaseRecsFlag(false);
			myTaskDataBean.setMytaskGrpCaseRecsFlag(false);
			myTaskDataBean.setMytaskCaseRecWatchListFlag(true);
			myTaskDataBean.setMytaskCRsFlag(false);
		}else{
			
			myTaskDataBean.setMytaskAlertFlag(true);
			myTaskDataBean.setMytaskCRsFlag(false);			
			myTaskDataBean.setMytaskGrpCRsFlag(false);
			myTaskDataBean.setMytaskCRsWatchListFlag(false);
			myTaskDataBean.setMytaskCaseRecsFlag(false);
			myTaskDataBean.setMytaskGrpCaseRecsFlag(false);
			myTaskDataBean.setMytaskCaseRecWatchListFlag(false);
			
		}
		
	}

}
