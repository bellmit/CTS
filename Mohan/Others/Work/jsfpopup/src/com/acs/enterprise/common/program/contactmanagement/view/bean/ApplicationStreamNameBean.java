/*
 * Created on Mar 23, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.bean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author pradeepc
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApplicationStreamNameBean {
	
	/** Enterprise Logger for Logging */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(ApplicationStreamNameBean.class);
	
	public ApplicationStreamNameBean()
	{
		String appName = null;
		logger.info("++Inside getApplicationName ()");
		try {
	    	FacesContext context = FacesContext.getCurrentInstance();
	    	RenderRequest renderRequest = null;
	    	logger.info("++context = " + context);
	    	if (context != null) {
	    		ExternalContext extContext = context.getExternalContext();
	    		logger.info("++extContext = " + extContext);
	    		if (extContext != null) {
    				renderRequest = (RenderRequest)extContext.getRequest();
    				logger.info("++renderRequest = " + renderRequest);
    		    	if (renderRequest != null) {
			    		PortletPreferences preferences = renderRequest.getPreferences();
			    		logger.info("++preferences = " + preferences);
			    		if (preferences != null) {
		    				appName = preferences.getValue("pe_mmis", "Invalid");
			    		}
    		    	}
	    		}
	    	}
	    	if (appName != null && appName.equals("mmis")) {

	    		setApplicationNameFlag(true);
		} else {
			setApplicationNameFlag(false);
		}
		logger.info("++ Application Name:::::::::::" + appName);
    	} catch (Exception exc) {
    		exc.printStackTrace();
    	}
    	
    	logger.info("++appName = " + appName);
    	if (appName == null) {
    		appName = "";
    	}
	
	}
	
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
}
