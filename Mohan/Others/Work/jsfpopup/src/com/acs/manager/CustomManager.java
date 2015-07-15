/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002, 2006 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

package com.acs.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.StateManager;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class CustomManager extends StateManager{
	
	private StateManager parentManager = null;
	private static final Log log = LogFactory.getLog(CustomManager.class);
	
	private static Boolean savingEnhancedServerState = null;
    public static final String ENHANCED_SERVER_STATE_SAVING_SESSION_STORED_VIEWS = "ENHANCED_SERVER_STATE_SAVING_SESSION_STORED_VIEWS";
    public static final String ENHANCED_SERVER_STATE_SAVING = "ENHANCED_SERVER_STATE_SAVING";
    private static final String ENHANCED_SERVER_STATE_SAVING_VIEW_LIST = "ENHANCED_SERVER_STATE_SAVING_VIEW_LIST"; 
	
    // settable via context param
    private int numberOfViewsToStore = -1;
    
    
	public CustomManager(StateManager parent){
		// save current statemanager
	    log.fatal("Initializing the CustomManager");
		this.parentManager = parent;
	}

	/**
	 * Inner class to help with scoping the synchronization checking to 
	 * help with performance. There will be one instance of this class
	 * per user session. The synchronization issues could arize if the 
	 * user has multiple browser instance open sharing the same session.
	 *
	 */
	private class SavedViewList {
		// a list of view ids
		private ArrayList views = null;
		public SavedViewList() {
			views = new ArrayList();
		}
		
		/**
		 * This method will store the view on the session using the unique 
		 * view id as the key. It will also store the view id in a MRU list
		 * to aid in trimming the number of stored views.
		 * 
		 * @param context
		 * @param uniqueViewId
		 * @param viewState
		 */
		synchronized void add(FacesContext context, String uniqueViewId, Object viewState){
			//check if a custom number of view to save has been set, default is 15
			if(numberOfViewsToStore == -1){
				String viewsToSave = context.getExternalContext().getInitParameter(ENHANCED_SERVER_STATE_SAVING_SESSION_STORED_VIEWS);
				if(viewsToSave != null){
					int num = Integer.parseInt(viewsToSave);
					if(num < 1){
						//numberOfViewsToStore = 1;
						savingEnhancedServerState = Boolean.FALSE;
					} else {
						numberOfViewsToStore = num;
					}
				} else {
					numberOfViewsToStore = 15;
				}
			}
			
			//check amount of stored views, trim if greater than allowed
			if(views.size() >= numberOfViewsToStore){
				context.getExternalContext().getSessionMap().remove(views.remove(0));
			}
			context.getExternalContext().getSessionMap().put(uniqueViewId, viewState);
		}
	}

	/**
	 * Make sure there is one SavedViewList on the user's session. This is 
	 * synchronized to prevent another thread from also creating an instance.
	 * 
	 * @param context
	 * @return
	 */
	private synchronized SavedViewList storeOneViewList(FacesContext context){
		// in the unlikely circumstance that another request is made to create a view list 
		// for this session check again once the thread gets unblocked
		SavedViewList views = (SavedViewList) context.getExternalContext().getSessionMap().get(ENHANCED_SERVER_STATE_SAVING_VIEW_LIST);
		if(views == null){
			views = new SavedViewList();
			context.getExternalContext().getSessionMap().put(ENHANCED_SERVER_STATE_SAVING_VIEW_LIST, views);
		}
		return views;
	}
	
	public static boolean isSavingEnhancedServerState(FacesContext context){
		
		if(null == savingEnhancedServerState){
			//only use this for ServletRequest, portlet runtime handles this param its own way
			if (context.getApplication().getStateManager().isSavingStateInClient(context)
				|| !(context.getExternalContext().getRequest() instanceof ServletRequest))
			{
				savingEnhancedServerState = Boolean.FALSE;
			} else {
				savingEnhancedServerState = Boolean.TRUE;			
			}
		}
		return savingEnhancedServerState.booleanValue();
	}
		
	public SerializedView saveSerializedView(FacesContext context) {
	  
		//here
		if(context != null && isSavingEnhancedServerState(context)){
			String viewId = (String)context.getViewRoot().getAttributes().get(ENHANCED_SERVER_STATE_SAVING);
			
			if(viewId != null){
				//saving in enhanced server mode, save a serialized viewRoot
				context.getViewRoot().getAttributes().remove(ENHANCED_SERVER_STATE_SAVING);
				SerializedView serializedView = new SerializedView(getTreeStructureToSave(context), context.getViewRoot().processSaveState(context));
        	
				SavedViewList views = (SavedViewList) context.getExternalContext().getSessionMap().get(ENHANCED_SERVER_STATE_SAVING_VIEW_LIST);
				if(views == null){
					views = storeOneViewList(context);
				}
				views.add(context, viewId, serializedView);
			}
		}
		// nothing to save outside of session
	
		return parentManager.saveSerializedView(context);
	}

	public UIViewRoot restoreView(FacesContext context, String viewId, String renderKitId) {
	  
		// no need to synchronize here even though the user, double clicking 
		// a button, may start two request threads. Each will just parse the
		// view state info creating a new view root
		UIViewRoot viewRoot = null;
		
		// the view key from the request
		ServletRequest request = (ServletRequest)context.getExternalContext().getRequest();
		String postedViewId = request.getParameter(ENHANCED_SERVER_STATE_SAVING);
		
		if (null != postedViewId) {
		   
			//restore viewRoot from serialized view that was saved in session
			SerializedView serializedView = (SerializedView)context.getExternalContext().getSessionMap().get(postedViewId);
			if (null != serializedView){
				ViewStructure viewStructure = (ViewStructure)serializedView.getStructure();
				viewRoot = (UIViewRoot) viewStructure.createComponent();
				viewStructure.restoreStructure(viewStructure,viewRoot);
				if (null != viewRoot) {
					viewRoot.processRestoreState(context,serializedView.getState());
				} 
			}
		} 
		
		return viewRoot;
	}
 
	protected Object getTreeStructureToSave(FacesContext context) {
		ViewStructure structure = new ViewStructure(context.getViewRoot());
		structure.buildStructure(context, context.getViewRoot(), structure);
		return structure;
	}
	
	
	protected Object getComponentStateToSave(FacesContext context) {
		//not used
		return null;
	}
    
	public void writeState(FacesContext context, SerializedView serializedView) throws IOException {
		// the partner custom view handler should have already
		// written the view id as state into page.
	}

	protected UIViewRoot restoreTreeStructure(FacesContext context, String arg1, String arg2) {
		//not used
		return null;
	}

	protected void restoreComponentState(FacesContext context, UIViewRoot arg1, String arg2) {
		//not used
	}
}
