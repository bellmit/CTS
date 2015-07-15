/*
 * Created on Jul 10, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.ibm.faces20.portlet.FacesPortlet;
//import com.ibm.faces.webapp.FacesGenericPortlet;

/**
 * @author wipro
 */
public class MyTaskPortlet
        extends FacesPortlet
{

    /** Enterprise Logger for Logging. */
    /*private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(MyTaskPortlet.class);*/
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(MyTaskPortlet.class);

    /**
     * @param request
     *            The request to set. *
     * @param response
     *            The response to set.
     * @throws PortletException
     *             it will throw PortletException.
     */
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException
    {
        // Reterive source action name

        /** Added for My Task */
        String actionNameFromLetterGene = (String) request
                .getParameter("back.from.letter.generation");
        logger.debug("In LogCorrespondence:actionNameMyTaskCorrespondenceSK: "
                        + actionNameFromLetterGene);

        if (actionNameFromLetterGene != null)
        {

            if (actionNameFromLetterGene
                    .equals("recieveFromLetterGeneration"))
            {
                logger.debug("Inside processAction::MyTask");

                String letterSkForMytask = (String) request
                        .getAttribute("LetterSkForMytask");

                logger.debug("Printing LetterSkForMytask--------->"
                        + letterSkForMytask);

                request.getPortletSession().setAttribute(
                        "LetterSkForMytask", letterSkForMytask);

                logger.debug("success --Mytask --");
            }
        }

        // Otherwise invoke default behaviour
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
            throws PortletException, IOException
    {

        
        /** Added for My Task */
        String letterSkForMytask = (String) request.getPortletSession()
                .getAttribute("LetterSkForMytask");
        if (letterSkForMytask != null)
        {
            logger.debug("Inside do view of Mytask Portlet ");

            request.setAttribute("LetterSkForMytask",
                    letterSkForMytask);

            request.getPortletSession().removeAttribute(
                    "LetterSkForMytask");
        }

        super.doView(request, response);
    }
}
