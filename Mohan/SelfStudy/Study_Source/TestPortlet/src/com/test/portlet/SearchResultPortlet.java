package com.test.portlet;

import java.io.IOException;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.ibm.faces.portlet.FacesPortlet;

public class SearchResultPortlet extends FacesPortlet {

	private boolean ipcCalled=false;
	
	@Override
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {		
		System.err.println("SearchResultPortlet doView Called");
		PortletSession portletSession = request.getPortletSession();
		System.err.println("Do View->" + portletSession.getAttribute("com.ibm.faces.portlet.page.view"));
		if(!ipcCalled) {			
			portletSession.setAttribute("com.ibm.faces.portlet.page.view","/jsp/blank.jsp");
		}
		super.doView(request, response);
		ipcCalled=false;
	}

	@Override	
	public void processEvent(EventRequest request, EventResponse response) throws PortletException, IOException {
		ipcCalled=true;
		Event event=request.getEvent();
		System.err.println("SearchResultPortlet processEvent Called Event Name->" + event.getName());
		PortletSession portletSession = request.getPortletSession();
		portletSession.setAttribute("com.ibm.faces.portlet.page.view","/jsp/displaygten.jsp");		
		portletSession.setAttribute("MyNum2", event.getValue());
		MyNumber myNumber=(MyNumber)event.getValue();
		System.err.println("My Num Passed is->" + myNumber.getMyNumber());
	}

	
}
