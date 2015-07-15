/*
 * Created on Jan 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseSearchCriteriaVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author durpaam
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CaseTypeSearchCriteriaVO  extends EnterpriseBaseSearchCriteriaVO
{
	/**
     * Generating object of EnterpriseLogger.
     */
      
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(CaseTypeSearchCriteriaVO.class);

	
    /** Holds statusCode of type boolean */
    private boolean statusCode;

    /**
     * @param statusCode The statusCode to set.
     */
    public void setStatusCode(boolean statusCode)
    {
        this.statusCode = statusCode;
    }

    /**
     * @return Returns the statusCode.
     */
    public boolean isStatusCode()
    {
        return statusCode;
    }
}
