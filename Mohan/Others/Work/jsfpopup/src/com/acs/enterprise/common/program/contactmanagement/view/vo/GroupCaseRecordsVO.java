/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class will be the VO for GroupCaseREcord tab of Mytasks page
 * 
 * @author Wipro
 */
public class GroupCaseRecordsVO
        extends EnterpriseBaseVO
{
    /** Constructor for GrpCaseVO */
    public GroupCaseRecordsVO()
    {
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(GroupCaseRecordsVO.class);
        logger.debug("Constructor for LobHierarchyVO");
    }

    /** Holds the Case Record Number */
    private String caseRecNo;

    /** Hold the openDate */
    private String openDate;

    /** Hold the priorityCode */
    private String priorityCode;

    /** Holds priorityCode Desc */
    private String priorityCodeDesc;

    /** Hold the status code */
    private String statusCode;

    /** Holds statusCode Desc */
    private String statusCodeDesc;

    /** Holds the entity type name */
    private String entityType;

    /** Hold the caseType */
    private String caseType;

    /** Hold the Work Unit */
    private String workUnit;

    /** Hold the String Workunit name */
    private String deptName;

    /** Hold the CaseDetailsVO */
    private CaseViewVO caseDetailsVO;

    /** Holds the entitySk */
    private String entitySk;

    /** Holds the entityTypeDesc */
    private String entityTypeDesc;
    /**
     * Holds entityName value
     */
    private String entityName;

    /**
     * @return Returns the caseDetailsVO.
     */
    public CaseViewVO getCaseDetailsVO()
    {
        return caseDetailsVO;
    }

    /**
     * @param caseDetailsVO
     *            The caseDetailsVO to set.
     */
    public void setCaseDetailsVO(CaseViewVO caseDetailsVO)
    {
        this.caseDetailsVO = caseDetailsVO;
    }

    /**
     * @return Returns the caseRecNo.
     */
    public String getCaseRecNo()
    {
        return caseRecNo;
    }

    /**
     * @param caseRecNo
     *            The caseRecNo to set.
     */
    public void setCaseRecNo(String caseRecNo)
    {
        this.caseRecNo = caseRecNo;
    }

    /**
     * @return Returns the caseType.
     */
    public String getCaseType()
    {
        return caseType;
    }

    /**
     * @param caseType
     *            The caseType to set.
     */
    public void setCaseType(String caseType)
    {
        this.caseType = caseType;
    }

    /**
     * @return Returns the entityType.
     */
    public String getEntityType()
    {
        return entityType;
    }

    /**
     * @param entityType
     *            The entityType to set.
     */
    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }

    /**
     * @return Returns the openDate.
     */
    public String getOpenDate()
    {
        return openDate;
    }

    /**
     * @param openDate
     *            The openDate to set.
     */
    public void setOpenDate(String openDate)
    {
        this.openDate = openDate;
    }

    /**
     * @return Returns the priorityCode.
     */
    public String getPriorityCode()
    {
        return priorityCode;
    }

    /**
     * @param priorityCode
     *            The priorityCode to set.
     */
    public void setPriorityCode(String priorityCode)
    {
        this.priorityCode = priorityCode;
    }

    /**
     * @return Returns the statusCode.
     */
    public String getStatusCode()
    {
        return statusCode;
    }

    /**
     * @param statusCode
     *            The statusCode to set.
     */
    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    /**
     * @return Returns the workUnit.
     */
    public String getWorkUnit()
    {
        return workUnit;
    }

    /**
     * @param workUnit
     *            The workUnit to set.
     */
    public void setWorkUnit(String workUnit)
    {
        this.workUnit = workUnit;
    }

    /**
     * @return Returns the entitySk.
     */
    public String getEntitySk()
    {
        return entitySk;
    }

    /**
     * @param entitySk
     *            The entitySk to set.
     */
    public void setEntitySk(String entitySk)
    {
        this.entitySk = entitySk;
    }

    /**
     * @return Returns the statusCodeDesc.
     */
    public String getStatusCodeDesc()
    {
        return statusCodeDesc;
    }

    /**
     * @param statusCodeDesc
     *            The statusCodeDesc to set.
     */
    public void setStatusCodeDesc(String statusCodeDesc)
    {
        this.statusCodeDesc = statusCodeDesc;
    }

    /**
     * @return Returns the priorityCodeDesc.
     */
    public String getPriorityCodeDesc()
    {
        return priorityCodeDesc;
    }

    /**
     * @param priorityCodeDesc
     *            The priorityCodeDesc to set.
     */
    public void setPriorityCodeDesc(String priorityCodeDesc)
    {
        this.priorityCodeDesc = priorityCodeDesc;
    }

    /**
     * @return Returns the entityTypeDesc.
     */
    public String getEntityTypeDesc()
    {
        return entityTypeDesc;
    }

    /**
     * @param entityTypeDesc
     *            The entityTypeDesc to set.
     */
    public void setEntityTypeDesc(String entityTypeDesc)
    {
        this.entityTypeDesc = entityTypeDesc;
    }
    /**
	 * @return Returns the entityName.
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName The entityName to set.
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

    /**
     * @return Returns the deptName.
     */
    public String getDeptName()
    {
        return deptName;
    }

    /**
     * @param deptName
     *            The deptName to set.
     */
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
}
