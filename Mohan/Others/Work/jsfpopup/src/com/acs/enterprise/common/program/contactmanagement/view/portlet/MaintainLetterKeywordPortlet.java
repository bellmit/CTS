package com.acs.enterprise.common.program.contactmanagement.view.portlet;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.MaintainKeywordsControllerBean;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.ibm.faces20.portlet.FacesPortlet;
//import com.ibm.faces.webapp.FacesGenericPortlet;

/**
 * @author Administrator TODO To change the template for this generated type
 *         comment go to Window - Preferences - Java - Code Style - Code
 *         Templates
 */
public class MaintainLetterKeywordPortlet
        extends FacesPortlet
{

    /**
     * Generating object of EnterpriseLogger.
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(MaintainLetterKeywordPortlet.class);

    /**
     * This is constructor of MaintainEntityPortlet.
     */
    public MaintainLetterKeywordPortlet()
    {
        super();
        logger.debug("Inside the constructor - MaintainLetterKeywordPortlet");
    }

    /**
     * Calls Do Process
     * 
     * @param request
     *            Takes request as param
     * @param response
     *            Takes response as param
     * @throws PortletException
     *             Throws PortletException
     */

    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException
    {

        logger.debug("Inside process action  of maintain letter keyword class");

        super.processAction(request, response);
    }

    /**
     * This method is to get "MaintainKeywordsDataBean" from faces context.
     * 
     * @return
     */
    public MaintainKeywordsControllerBean getMaintainKeywordsControllerBeanFromFacesContext()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        MaintainKeywordsControllerBean maintainKeywordsControllerBean = ((MaintainKeywordsControllerBean) fc
                .getApplication()
                .createValueBinding(
                        ContactManagementConstants.BINDING_BEGIN_SEPARATOR
                                + "MaintainKeywordsControllerBean"
                                + ContactManagementConstants.BINDING_END_SEPARATOR)
                .getValue(fc));
        return maintainKeywordsControllerBean;

    }

    /**
     * Calls Do view
     * 
     * @param request
     *            Takes request as param
     * @param response
     *            Takes response as param
     * @throws PortletException
     *             Throws PortletException
     * @throws IOException
     *             Throws ioException
     */

    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {
        logger.debug("Inside do view of maintain entity portlet class");

        //	     String maintainEntityId = (String)
        // request.getPortletSession().getAttribute("maintainEntityId");
        //         if (maintainEntityId != null)
        //         {
        //             logger.debug("Inside do view of Cancel");
        //            
        //              request.getPortletSession().removeAttribute("maintainEntityId");
        //              request.setAttribute("maintainEntityId", maintainEntityId);
        //          }
        //       
        //         
        //         
        //         /** Added for oher ipc from search correspondence portlet */
        //         String fromPageName = (String)
        // request.getPortletSession().getAttribute("MaintainEntity");
        //         if (fromPageName != null)
        //         {
        //             logger.debug("Inside do view of Cancel ---for fromPageName");
        //            
        //              request.getPortletSession().removeAttribute("MaintainEntity");
        //              request.setAttribute("MaintainEntity", fromPageName);
        //          }
        super.doView(request, response);

    }

}
