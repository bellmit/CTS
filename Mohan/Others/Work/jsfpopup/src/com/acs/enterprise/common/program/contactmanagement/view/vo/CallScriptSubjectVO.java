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
public class CallScriptSubjectVO
        extends EnterpriseBaseVO
        {
    /**
     * Creates a consructor of the CallScriptSubjectVO.
     */
    public CallScriptSubjectVO()
    {
        
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
        .getLogger(CallScriptSubjectVO.class);
        logger.debug("Inside CallScriptSubjectVO construcor ");
       
    }
    /**
     * @param auditUserID
     * Takes auditUserID as param.
     * @param auditTimeStamp
     * Takes auditUserID as param.
     * @param addedAuditUserID
     * Takes auditUserID as param.
     * @param addedAuditTimeStamp
     * Takes auditUserID as param.
     */
    public CallScriptSubjectVO(Object auditUserID, Object auditTimeStamp,
            Object addedAuditUserID, Object addedAuditTimeStamp)
    {
        super(auditUserID, auditTimeStamp, addedAuditUserID,
                addedAuditTimeStamp);
        // TODO Auto-generated constructor stub
    }
    /**
     * Variable to hold value of Include Call Script.
     */
    private boolean includecallScript;

    /**
     * Variable to hold description of Entity.
     */
    private String subjectDescription;

    /**
     * Variable to hold Assigned Call Script.
     */
    private String assignedCallScript;

    /**
     * Variable to holds entity type code.
     */
    private String subjectCode;

    /**
     * Variable to hold Assigned Call Script.
     */
    private Long callScriptSK;

    /**
     * Variable to hold value of active subjects.
     */
    private boolean voidIndicator;

    /**
     * Variable to holds status of Subject.
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
     * @return Returns the subjectDescription.
     */
    public String getSubjectDescription()
    {
        return subjectDescription;
    }

    /**
     * @param subjectDescription
     *            The subjectDescription to set.
     */
    public void setSubjectDescription(String subjectDescription)
    {
        this.subjectDescription = subjectDescription;
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
     * @return Returns the subjectCode.
     */
    public String getSubjectCode()
    {
        return subjectCode;
    }

    /**
     * @param subjectCode
     *            The subjectCode to set.
     */
    public void setSubjectCode(String subjectCode)
    {
        this.subjectCode = subjectCode;
    }

    /**
     * @return Returns the voidIndicator.
     */
    public boolean isVoidIndicator()
    {
        return voidIndicator;
    }

    /**
     * @param voidIndicator
     *            The voidIndicator to set.
     */
    public void setVoidIndicator(boolean voidIndicator)
    {
        this.voidIndicator = voidIndicator;
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
    
    private int rowsubIndexCount;
	/**
	 * @return Returns the rowsubIndexCount.
	 */
	public int getRowsubIndexCount() {
		return rowsubIndexCount;
	}
	/**
	 * @param rowsubIndexCount The rowsubIndexCount to set.
	 */
	public void setRowsubIndexCount(int rowsubIndexCount) {
		this.rowsubIndexCount = rowsubIndexCount;
	}
}
