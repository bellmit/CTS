/*
 * Created on Jan 9, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.manager;
import java.io.IOException;
import java.util.Locale;
import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


/**
 * @author C7500378
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomViewHandler extends ViewHandler {
	
	private ViewHandler parentHandler = null;
	
    
    private UniqueIdCounter uniqueIdCounter = new UniqueIdCounter(0);
    
	
	
	public CustomViewHandler(ViewHandler handler){
		this.parentHandler = handler;
	}

	public Locale calculateLocale(FacesContext context) {
		return parentHandler.calculateLocale(context);
	}

	public String calculateRenderKitId(FacesContext context) {
		return parentHandler.calculateRenderKitId(context);
	}

	public UIViewRoot createView(FacesContext context, String viewId) {
		return parentHandler.createView(context,viewId);
	}

	public String getActionURL(FacesContext context, String url) {
		return parentHandler.getActionURL(context,url);
	}

	public String getResourceURL(FacesContext context, String url) {
		return parentHandler.getResourceURL(context,url);
	}

	public void renderView(FacesContext context, UIViewRoot viewRoot) throws IOException, FacesException {
		createUniqueViewId(context,context.getViewRoot().getViewId());
		parentHandler.renderView(context,viewRoot);
	}

	public UIViewRoot restoreView(FacesContext context, String view) {
		return parentHandler.restoreView(context,view);
	}

	public void writeState(FacesContext context) throws IOException {
		//here
		if(context != null && CustomManager.isSavingEnhancedServerState(context)){
			addHiddenViewId (context);
		}
		
		parentHandler.writeState(context);
	}
	
    //class for generating a unique id int for back button
    public class UniqueIdCounter {
    	
    	private int counter;
    	
    	public UniqueIdCounter(int i){
    		this.counter = i;
    	}
    	
    	public int increment(){
    		if(++this.counter == Integer.MAX_VALUE) this.counter = 0;
    		return this.counter;
    	}
    }
    

	
    private boolean addHiddenViewId (FacesContext context){
		ResponseWriter writer = context.getResponseWriter();
		try{
			if(writer != null){
		        writer.startElement("input", null);
		        writer.writeAttribute("type", "hidden", "type");
		        writer.writeAttribute("name", CustomManager.ENHANCED_SERVER_STATE_SAVING, null);
		        writer.writeAttribute("value", context.getViewRoot().getAttributes().get(CustomManager.ENHANCED_SERVER_STATE_SAVING), "value");
		        writer.endElement("input");
			}
			
		}catch (IOException io){
			context.getViewRoot().getAttributes().remove(CustomManager.ENHANCED_SERVER_STATE_SAVING);
			return false;
		}
		return true;
	}
    
    private String createUniqueViewId (FacesContext context, String viewId){
    	
    	if(!CustomManager.isSavingEnhancedServerState(context)){
    			return viewId;
    	}

    	String uniqueId = null;
    	synchronized(uniqueIdCounter){
    		uniqueId  = viewId + uniqueIdCounter.increment();
    	}
    	context.getViewRoot().getAttributes().put(CustomManager.ENHANCED_SERVER_STATE_SAVING, uniqueId );
    	return uniqueId;
    }

}
