/*
 * Created on Apr 3, 2009
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.ibm.faces20.portlet.FacesPortlet;

public class SearchNotesPortlet extends FacesPortlet {

	/** Enterprise Logger for Logging. */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(SearchNotesPortlet.class);

	/**
	 * This method is to destroy.
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * When the user clicks on Confirgure mode from admninstration console, this
	 * method will be inovked.
	 */
	public void doConfigure(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		super.doConfigure(request, response);
	}

	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException {
		/*System.err
				.println("inside SearchNotesPortlet process action:: helpFor: "
						+ request.getParameter("helpFor"));*/
		String helpFor = request.getParameter("helpFor");
		if (helpFor != null && helpFor.equalsIgnoreCase("notesDetailsHelp")) {
			try {
				
				response.setRenderParameter("helpFor", "notesDetailsHelp");
				response.setPortletMode(PortletMode.HELP);
			} catch (Exception e) {

				if (logger != null) {
					
					logger.error("Exception while changing the mode:: ", e);
				}
				//e.printStackTrace();
			}
		}else{
			try {
				response.setPortletMode(PortletMode.VIEW);
			} catch (Exception e) {

				if (logger != null) {
				
					logger.error("Exception while changing the mode:: ", e);
				}
				//e.printStackTrace();
			}

		}

		super.processAction(request, response);
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

		super.doView(request, response);
	}

	/**
	 * When the user clicks on Help Icon of Portlet menu, this method will be
	 * inovked.
	 */
	public void doHelp(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		
		if ("notesDetailsHelp"
				.equalsIgnoreCase(request.getParameter("helpFor"))) {
			request.getPortletSession().setAttribute(
					"com.ibm.faces.portlet.page.help",
					"/jsp/searchnotes/notesDetailsHelp.jsp");
		} else {
			request.getPortletSession().setAttribute(
					"com.ibm.faces.portlet.page.help",
					"/jsp/searchnotes/searchNotesHelp.jsp");
		}
		

		super.doHelp(request, response);
	}

	public void setUserPermission(RenderRequest request) {
		/*
		 * String userid=""; String userPermission = ""; PortletSession psin =
		 * request.getPortletSession();
		 * 
		 * //FieldAccessControl fieldAccessControlImpl = new
		 * FieldAccessControl();
		 * 
		 * try { userid = (String) psin.getAttribute("LOGGED_IN_USERID");
		 * userPermission = (String) psin.getAttribute("LOG_C_PERM");
		 * //userPermission =
		 * fieldAccessControlImpl.getFiledAccessPermission(ContactManagementConstants.LOG_CORRESPONDENCE_PAGE,
		 * userid); } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } userPermission = userPermission != null ?
		 * userPermission.trim() : "";
		 *  // set the permission level. This will be used to grey out the page
		 * request.setAttribute("displayMode", userPermission);
		 */
	}

}

