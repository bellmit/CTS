         /*
 * Created on Jul 10, 2007 TODO To change the template for this generated file
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
//import com.ibm.faces.webapp.FacesGenericPortlet;

/**
 * @author wipro
 */
public class SearchCorrespondencePortlet extends FacesPortlet {

	/** Enterprise Logger for Logging. */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(SearchCorrespondencePortlet.class);

	/**
	 * @param request
	 *            The request to set. *
	 * @param response
	 *            The response to set.
	 * @throws PortletException
	 *             it will throw PortletException.
	 */
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException {
		// Reterive source action name
		String actionName = (String) request
				.getParameter("com.ibm.portal.propertybroker.action");
		System.out.println("In SearchCorrespondence:actionName: " + actionName);

		if (actionName != null) {

			if (actionName.equals("receiveMemberCorrespondenceAction")) {
				logger.debug("Inside  SearchCorrespondence : received");

				String correspondence = (String) request
						.getParameter("MemberCorrespondence");

				logger.debug("Printing MemberCorrespondence--------->"
						+ correspondence);

				request.getPortletSession().setAttribute(
						"MemberCorrespondence", correspondence);

				logger.debug("success1");
			}
		}

		String actionName1 = (String) request
				.getParameter("recieve.CrspdGen.AppealSk");
		logger.debug("In SearchCorrespondence:actionName1: " + actionName1);

		if (actionName1 != null) {

			if (actionName1.equals("recieveCrspdGenAppealSk")) {
				logger.debug("Inside  SearchCorrespondence : received");

				String appealEntity = (String) request
						.getAttribute("CrspdGenAppealSK");

				logger.debug("Printing appealEntity--------->" + appealEntity);

				request.getPortletSession().setAttribute("CrspdGenAppealSK",
						appealEntity);

				logger.debug("success1");
			}
		}

		String entSrchActionName = request.getParameter("EntSrchCrsSrch_ACTION");
		logger
				.debug("SearchCorrespondencePortlet :: processAction action Name from Entity Search "
						+ entSrchActionName);

		if (entSrchActionName != null
				&& entSrchActionName.equals("EntSrchCrsSrch_TARGET_ACTION")) {

			Map dataMap = (Map) request.getAttribute("entitySearchMap");

			logger
					.debug("SearchCorrespondencePortlet :: processAction Receiving Map object "
							+ dataMap);

			if (dataMap != null) {			
				request.getPortletSession().setAttribute("DataMap", dataMap);
			}

		}

		// Otherwise invoke default behaviour
		super.processAction(request, response);
		System.out.println("correspondenceSk :>  "+ (String) request
				.getAttribute("correspondenceSk"));
		String searchCorrespondence = (String) request
				.getAttribute("correspondenceSk");
		if (searchCorrespondence != null) {
			response.setEvent("CorrespondenceSearchToLogCR",
					searchCorrespondence);
		}
	
		CorrespondenceSearchCriteriaVO crSearchCriteriavo = (CorrespondenceSearchCriteriaVO) request
				.getAttribute("CrsSearchCrtVO");
		System.out.println("crSearchCriteriavo1 :>  "+ crSearchCriteriavo);	
		if (crSearchCriteriavo != null) {
			System.out.println("crSearchCriteriavo2 :>  "+ crSearchCriteriavo);
			response.setEvent("CRSearchToEntitySearch", crSearchCriteriavo);
		}
	}

	/**
	 * @param request
	 *            The request to set. *
	 * @param response
	 *            The response to set.
	 * @throws PortletException
	 *             it will throw PortletException.
	 * @throws IOException
	 *             it will throw IOException.
	 */
	public void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		String correspondence = (String) request.getPortletSession()
				.getAttribute("MemberCorrespondence");

		if (correspondence != null) {
			logger.debug("Inside do view of Cancel");

			request.setAttribute("MemberCorrespondence", correspondence);

			request.getPortletSession().removeAttribute("MemberCorrespondence");
		}

		String entity = (String) request.getPortletSession().getAttribute(
				"CrspdGenAppealSK");
		if (entity != null) {
			logger.debug("Inside do view of crspdgenappeal");
			request.setAttribute("CrspdGenAppealSK", entity);

			request.getPortletSession().removeAttribute("CrspdGenAppealSK");

		}

		super.doView(request, response);
	}
	public void actionUrl(EventRequest request, EventResponse response,
			String actionName) {
		/*String actionName = (String) request
				.getParameter("com.ibm.portal.propertybroker.action");*/
		logger.debug("In SearchCorrespondence:actionName: " + actionName);

		if (actionName != null) {

			if (actionName.equals("receiveMemberCorrespondenceAction")) {
				logger.debug("Inside  SearchCorrespondence : received");

				String correspondence = (String) request
						.getAttribute("MemberCorrespondence");

				logger.debug("Printing MemberCorrespondence--------->"
						+ correspondence);

				request.getPortletSession().setAttribute(
						"MemberCorrespondence", correspondence);

				logger.debug("success1");
			}
		}

		/*String actionName1 = (String) request
				.getParameter("recieve.CrspdGen.AppealSk");
		logger.debug("In SearchCorrespondence:actionName1: " + actionName1);*/

		if (actionName != null) {

			if (actionName.equals("recieveCrspdGenAppealSk")) {
				logger.debug("Inside  SearchCorrespondence : received");

				String appealEntity = (String) request
						.getAttribute("CrspdGenAppealSK");

				logger.debug("Printing appealEntity--------->" + appealEntity);

				request.getPortletSession().setAttribute("CrspdGenAppealSK",
						appealEntity);

				logger.debug("success1");
			}
		}

		/*String entSrchActionName = request
				.getParameter("EntSrchCrsSrch_ACTION");
		logger
				.debug("SearchCorrespondencePortlet :: processAction action Name from Entity Search "
						+ entSrchActionName);
*/
		if (actionName != null
				&& actionName.equals("EntSrchCrsSrch_TARGET_ACTION")) {

			Map dataMap = (Map) request.getAttribute("entitySearchMap");

			logger
					.debug("SearchCorrespondencePortlet :: processAction Receiving Map object "
							+ dataMap);

			if (dataMap != null) {
				request.getPortletSession().setAttribute("DataMap", dataMap);
			}

		}
	}
	/**
		 * Process an action request.
		 * 
		 * @see javax.portlet.Portlet#processEvent(javax.portlet.EventRequest,
		 *      javax.portlet.EventResponse)
		 */
		public void processEvent(EventRequest request, EventResponse response)
				throws PortletException, java.io.IOException {
			String actionName=null;
			Event event = request.getEvent();
			if (event.getName().toString().equals("CRSearchFromEntitySearch")) {
				System.out.println(" event.getValue() : "+ event.getValue());
				System.out.println(" event.getValue() : "+ event.getValue().getClass().getName());
				WireEventDataCarrierVO mapData = (WireEventDataCarrierVO) event.getValue(); 
				HashMap entitySearch = mapData.getMapData();
				System.out.println("entitySearch.getIdentification : " + entitySearch.get("Identification"));
				System.out.println("entitySearch.getIdentification : " + entitySearch.get("EntityID"));
				System.out.println("entitySearch.getIdentification : " + entitySearch.get("CommonEntitySK"));

				if (entitySearch != null) {
					request.setAttribute("entitySearchMap",
							entitySearch);
				}
				actionName="EntSrchCrsSrch_TARGET_ACTION";
			} else if (event.getName().toString().equals("CRSearchFromAddAppeals")) {
				String addAppeal = (String) event.getValue();
				if (addAppeal != null) {
					request.setAttribute("CrspdGenAppealSK",
							addAppeal);
				}
				actionName="recieveCrspdGenAppealSk";
			} else if (event.getName().toString()
					.equals("CRSearchFromMemAppealInq")) {
				String memAppealInq = (String) event.getValue();
				if (memAppealInq != null) {
					request.setAttribute(
							"CrspdGenAppealSK", memAppealInq);
				}
				actionName="recieveCrspdGenAppealSk";
			} else if (event.getName().toString().equals(
					"fromMemberDetailsMaintenanceToCorrespondence")) {
				String memCR = (String) event.getValue();
				if (memCR != null) {
					request.setAttribute("MemberCorrespondence",
							memCR);
				}
				actionName="receiveMemberCorrespondenceAction";
			}else if (event.getName().toString().equals(
					"ProcessMemberCorrespondanceFromTPLEvent")){
				String memerCorrespondance = (String)event.getValue();
				if (memerCorrespondance != null){
					request.setAttribute("MemberCorrespondence",
							memerCorrespondance);
				}
				actionName="receiveMemberCorrespondenceAction";
			}
		actionUrl(request, response, actionName);
	}
}
