/*
 * Created on Mar 18, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author pradeepc
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MessageBean {
	
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    		.getLogger(MessageBean.class);
	
	/**
	 * 
	 *
	 */
	public MessageBean()
	{
		intitializeBundles();
        
	}
	
	/*	 */
	private Map prgMsgMap;
	
	/*	*/
	private Map myTaskMap;
	
	/*	*/
	private Map alertDtMap;
	

	/**
	 * @return Returns the prgMsgMap.
	 */
	public Map getPrgMsgMap() {
		return prgMsgMap;
	}
	/**
	 * @param prgMsgMap The prgMsgMap to set.
	 */
	public void setPrgMsgMap(Map prgMsgMap) {
		this.prgMsgMap = prgMsgMap;
	}
	/**
	 * @return Returns the myTaskMap.
	 */
	public Map getMyTaskMap() {
		return myTaskMap;
	}
	/**
	 * @param myTaskMap The myTaskMap to set.
	 */
	public void setMyTaskMap(Map myTaskMap) {
		this.myTaskMap = myTaskMap;
	}
	
	/**
	 * @return Returns the alertDtMap.
	 */
	public Map getAlertDtMap() {
		return alertDtMap;
	}
	/**
	 * @param alertDtMap The alertDtMap to set.
	 */
	public void setAlertDtMap(Map alertDtMap) {
		this.alertDtMap = alertDtMap;
	}
	
	/**
	 * 
	 *
	 */
	private void intitializeBundles()
	{
		FacesContext context = FacesContext.getCurrentInstance();
        
		ResourceBundle rb = ResourceBundle.getBundle(ProgramConstants.PROGRAM_MESSAGES_BUNDLE, 
        		context.getViewRoot().getLocale());
        setPrgMsgMap(fillMsgMap(rb));
        
        rb = ResourceBundle.getBundle(ProgramConstants.PGM_IN_MYTASK_MESSAGES_BUNDLE, 
        		context.getViewRoot().getLocale());
        setMyTaskMap(fillMsgMap(rb));
        
        rb = ResourceBundle.getBundle(ProgramConstants.PGM_IN_ALERTDATES_MESSAGES_BUNDLE, 
        		context.getViewRoot().getLocale());
        setAlertDtMap(fillMsgMap(rb));
        
	}
	
	/**
	 * 
	 * @param rb
	 * @return
	 */
	private Map fillMsgMap(ResourceBundle rb)
	{
		Map messageMap = new HashMap();
        Enumeration keys = rb.getKeys();
        while (keys.hasMoreElements()) {
          String key = (String) keys.nextElement();
          messageMap.put(key, rb.getString(key));
        }
        return messageMap;
	}
}
