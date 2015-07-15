/*
 * Created on Feb 15, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.WireEventDataCarrierVO;
import com.ibm.faces20.portlet.FacesPortlet;
/**
 * @author Wipro
 */
public class ReassignCorrespondencePortlet
        extends FacesPortlet
{
    /** Creates an instance of the logger. * */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
	.getLogger(SearchCorrespondencePortlet.class);


    /**
     * Constructor AddAppealsPortlet.
     */
    public ReassignCorrespondencePortlet()
    {
        super();
        logger.debug("inside ReassignCorrespondencePortlet constructor");
    }

    /**
     * This method calls process action
     * 
     * @param request
     *            ActionRequest
     * @param response
     *            ActionResponse
     * @throws PortletException
     *             if any portlet exception occurs
     */
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException
    {
    	logger.debug("in Reassign Correspondence");
    	
    	String entSrchActionName = request.getParameter("EntSrchCrsSrch_ACTION");
		logger
				.debug("ReassignCorrespondence Portlet :: processAction action Name from Entity Search "
						+ entSrchActionName);

		if (entSrchActionName != null
				&& entSrchActionName.equals("EntSrchCrsSrch_TARGET_ACTION")) {

			Map dataMap = (Map) request.getAttribute("entitySearchMap1");

			logger
					.debug("ReassignCorrespondence Portlet  :: processAction Receiving Map object "
							+ dataMap);

			if (dataMap != null) {			
				request.getPortletSession().setAttribute("DataMap", dataMap);
			}

		}


		
		// Otherwise invoke default behaviour
		super.processAction(request, response);
	
		CorrespondenceSearchCriteriaVO crSearchCriteriavo = 
			(CorrespondenceSearchCriteriaVO) request.getAttribute("CrsSearchCrtVO");
		if (crSearchCriteriavo != null) {
			response.setEvent("ReassignCRToEntitySearch", crSearchCriteriavo);
		}
		
    
    
    }

    /**
     * mehtod calls do view method
     * 
     * @param request
     *            RenderRequest
     * @param response
     *            RenderResponse
     * @throws PortletException
     *             if any Portlet Exception occurs
     * @throws IOException
     *             if any IO Exception occurs
     */
    public void doView(RenderRequest request, RenderResponse response)
        throws PortletException, IOException
    { 
    	
    	logger.debug(" inside Do view mwthod");
    	
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
		
		//Code added for the WSRP IPC fix from Entity Search to Reassign Correspondence 
		
		Event event = request.getEvent();
		
		if (event.getName().toString().equals("TRGSearchEntityReassignCR")) {
			WireEventDataCarrierVO wireData = (WireEventDataCarrierVO) event
					.getValue();
			
			HashMap reAssignEntitySearch = wireData.getMapData();
			

			if (reAssignEntitySearch != null) {
				request.getPortletSession().setAttribute("DataMap", reAssignEntitySearch);
			}
			
			//actionName = "LogCrsSrchEnt_TARGET_ACTION";
		}
    }
}
