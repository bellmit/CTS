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
public class CallScriptCategoryVO
        extends EnterpriseBaseVO
{
    /**
     * Creates a consructor of the CallScriptCategoryVO.
     */
    public CallScriptCategoryVO()
    {
        
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
        .getLogger(CallScriptCategoryVO.class);
        logger.debug("Inside CallScriptCategoryVO construcor ");
       
    }

    /**
     * Variable to hold value of Include Call Script.
     */
    private boolean includecallScript;

    /**
     * Variable to hold Assigned Call Script.
     */
    private Long callScriptSK;

    /**
     * Variable to hold Assigned Call Script.
     */
    private String assignedCallScript;

    /**
     * Variable to hold Status of Category.
     */
    private String status;

    /**
     * Variable to hold reference of CategoryVo.
     */
    private CategoryVO categoryVO;

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
     * @return Returns the callScriptSK.
     */
    public Long getCallScriptSK()
    {
        return callScriptSK;
    }

    /**
     * @param callScriptSK
     *            The callScriptSK to set.
     */
    public void setCallScriptSK(Long callScriptSK)
    {
        this.callScriptSK = callScriptSK;
    }

    /**
     * @return Returns the categoryVO.
     */
    public CategoryVO getCategoryVO()
    {
        return categoryVO;
    }

    /**
     * @param categoryVO
     *            The categoryVO to set.
     */
    public void setCategoryVO(CategoryVO categoryVO)
    {
        this.categoryVO = categoryVO;
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
    
    private int rowcatIndexCount;
	
	/**
	 * @return Returns the rowcatIndexCount.
	 */
	public int getRowcatIndexCount() {
		return rowcatIndexCount;
	}
	/**
	 * @param rowcatIndexCount The rowcatIndexCount to set.
	 */
	public void setRowcatIndexCount(int rowcatIndexCount) {
		this.rowcatIndexCount = rowcatIndexCount;
	}
}
