/*
 * Created on Jul 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.program.contactmanagement.view.bean.CallScriptControllerBean;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.ibm.faces20.portlet.FacesPortlet;
//import com.ibm.faces.webapp.FacesGenericPortlet;


/**
 * @author vijaymai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorrespondenceEntitySearchPortlet extends FacesPortlet
{
    /**
     * Generating object of EnterpriseLogger.
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CallScriptControllerBean.class);
    
	
    /**
     * This is constructor of MaintainEntityPortlet.
     */
    public CorrespondenceEntitySearchPortlet()
    {
        super();
        logger.debug("Inside the constructor - CorrespondenceEntitySearchPortlet");
    }
    
    /**
	 * Calls Do Process 
	 * @param request
	 * Takes request as param
	 * @param response
	 * Takes response as param
	 * @throws PortletException
	 * Throws PortletException
	 
	 */
	
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException
	{

	    logger.debug("Inside process action  of CorrespondenceEntitySearchPortlet class");
		// Reterive source action name
		
		 String actionName = (String) request.getParameter("GO.TO.CR.ENTITY.SEARCH.PAGE");
			logger.debug("In CorrespondenceEntitySearchPortlet:actionName: " + actionName);
		 
		 	
				if (actionName != null && actionName.equals("goToCrEntitySearchPage"))
				{
				    logger.debug("Inside  CorrespondenceEntitySearchPortlet:");
					
				    String searchCREntity = (String) request.getAttribute("SearchCorrespondenceEntity");
					
				    logger.debug("Printing CorrespondenceEntitySearchPortlet--------->" + searchCREntity);
					
					request.getPortletSession().setAttribute("SearchCorrespondenceEntity", searchCREntity);
					
					logger.debug("success");
				}
		
				
		
		// Otherwise invoke default behaviour 
		super.processAction(request, response);
		logger
				.debug(" result: "
						+ request.getAttribute("correspondenceEntity"));
		String correspondentEntity = (String) request
				.getAttribute("correspondenceEntity");
		if (correspondentEntity != null) {
			logger.debug(" correspondenceEntity: " + correspondentEntity);
			response.setEvent("EntityDetailsToLogCR", correspondentEntity);
	}
		String maintainEntity = (String) request.getAttribute("MaintainEntity");
		if (maintainEntity != null) {
			logger.debug(" MaintainEntity: " + maintainEntity);
			response.setEvent("CREntitySearchToMaintainEntity", maintainEntity);
		}
	}
	/**
	 * Calls Do view 
	 * @param request
	 * Takes request as param
	 * @param response
	 * Takes response as param
	 * @throws PortletException
	 * Throws PortletException
	 * @throws IOException
	 * Throws ioException
	 */
	
	public void doView(RenderRequest request, RenderResponse response)
     throws PortletException, IOException
    {
        logger.debug("Inside do view of CorrespondenceEntitySearchPortlet class");
	     
	     String searchCREntity = (String) request.getPortletSession().getAttribute("SearchCorrespondenceEntity");
         if (searchCREntity != null)
         {
             logger.debug("Inside do view of Cancel");
            
              request.getPortletSession().removeAttribute("SearchCorrespondenceEntity");
              request.setAttribute("SearchCorrespondenceEntity", searchCREntity);
          }
       
         
         
       
	 super.doView(request, response);
	}
	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processEvent(javax.portlet.EventRequest,
	 *      javax.portlet.EventResponse)
	 */
	public void processEvent(EventRequest request, EventResponse response)
			throws PortletException, java.io.IOException {
		String actionName = null;
		Event event = request.getEvent();
		if (event.getName().toString().equals(
				"CREntitySearchFromMaintainEntity")) {
			String maintainEntity = (String) event.getValue();
			if (maintainEntity != null) {
				request.setAttribute("SearchCorrespondenceEntity",
						maintainEntity);
    		}
		}
		actionUrl(request, response, actionName);
	}
	public void actionUrl(EventRequest request, EventResponse response,
			String actionName) {
// String actionName = (String) request.getParameter("GO.TO.CR.ENTITY.SEARCH.PAGE");
			logger.debug("In CorrespondenceEntitySearchPortlet:actionName: " + actionName);
		 
		 	
				if (actionName != null && actionName.equals("goToCrEntitySearchPage"))
				{
				    logger.debug("Inside  CorrespondenceEntitySearchPortlet:");
					
				    String searchCREntity = (String) request.getAttribute("SearchCorrespondenceEntity");
					
				    logger.debug("Printing CorrespondenceEntitySearchPortlet--------->" + searchCREntity);
					
					request.getPortletSession().setAttribute("SearchCorrespondenceEntity", searchCREntity);
					
					logger.debug("success");
				}
		}

}
