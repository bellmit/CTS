/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * Holds the Call Script Information
 */
public class CallScriptEntityVO
        extends EnterpriseBaseVO
{
    /**
     * Creates a consructor of the CallScriptEntityVO.
     */
    public CallScriptEntityVO()
    {
        
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
        .getLogger(CallScriptEntityVO.class);
        logger.debug("Inside CallScriptEntityVO construcor ");
       
    }
    /**
     * Variable to hold value of Include Call Script.
     */
    private boolean includecallScript;
    
   private int rowIndexCount;

    /**
     * Variable to hold description of Entity.
     */
    private String entityDescription;

    /**
     * Variable to hold Assigned Call Script.
     */
    private String assignedCallScript;

    /**
     * Variable to holds entity type code.
     */
    private String entityTypeCode;

  

    /**
     * Variable to hold Staus of the Enity.
     */
    private String status;

    /**
     * @return Returns the assignedCallScript.
     */
    public String getAssignedCallScript()
    {
        return assignedCallScript;
    }

    /**
     * @param assignedCallScript
     *            The assignedCallScript to set.
     */
    public void setAssignedCallScript(String assignedCallScript)
    {
        this.assignedCallScript = assignedCallScript;
    }

    /**
     * @return Returns the entityDescription.
     */
    public String getEntityDescription()
    {
        return entityDescription;
    }

    /**
     * @param entityDescription
     *            The entityDescription to set.
     */
    public void setEntityDescription(String entityDescription)
    {
        this.entityDescription = entityDescription;
    }

    /**
     * @return Returns the includecallScript.
     */
    public boolean getIncludecallScript()
    {
        return includecallScript;
    }

    /**
     * @param includecallScript
     *            The includecallScript to set.
     */
    public void setIncludecallScript(boolean includecallScript)
    {
        this.includecallScript = includecallScript;
    }

    /**
     * @return Returns the entityTypeCode.
     */
    public String getEntityTypeCode()
    {
        return entityTypeCode;
    }

    /**
     * @param entityTypeCode
     *            The entityTypeCode to set.
     */
    public void setEntityTypeCode(String entityTypeCode)
    {
        this.entityTypeCode = entityTypeCode;
    }

  

    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
/**
 * @return Returns the rowIndexCount.
 */
public int getRowIndexCount() {
	return rowIndexCount;
}
/**
 * @param rowIndexCount The rowIndexCount to set.
 */
public void setRowIndexCount(int rowIndexCount) {
	this.rowIndexCount = rowIndexCount;
}
}
