package com.test.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.ibm.faces.portlet.FacesPortlet;

public class TestPortlet extends FacesPortlet {

	@Override
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
		super.processAction(request, response);
		System.err.println("TestPortlet Action Request Called");
		Object object=request.getAttribute("myNum");
		if(object!=null) {
			MyNumber myNumber=(MyNumber)object;
			request.removeAttribute("myNum");
			response.setEvent("searchNumberPub", myNumber);
		}				
	}

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		System.err.println("TestPortlet doView Called");
		super.doView(request, response);
	}

	@Override
	public void processEvent(EventRequest request, EventResponse response) throws PortletException, IOException {
		System.err.println("TestPortlet processEvent Called");
		super.processEvent(request, response);
	}
	
}
