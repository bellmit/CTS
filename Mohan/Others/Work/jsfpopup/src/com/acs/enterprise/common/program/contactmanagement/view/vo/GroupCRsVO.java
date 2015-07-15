/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/** Vo for GroupCRs */
public class GroupCRsVO
        extends EnterpriseBaseVO
{

    /**
     * Constructor for GroupCRsVO
     */
    public GroupCRsVO()
    {
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(GroupCRsVO.class);
        logger.debug("GroupCRsVO constructor");
    }

    /** Hold the correspondenceSK */
    private String correspondenceSK;

    /** Hold the openDate */
    private String openDate;

    /** Hold the status code */
    private String statusCode;

    /** Hold the priorityCode */
    private String priorityCode;

    /** Hold the Work Unit */
    private String workUnit;

    /** Holds the entity name */
    private String entity;

    /** Holds the entity type name */
    private String entityType;

    /** Holds the entity type name */
    private String crn;

    /** Holds the workunit name */
    private String deptName;

    /** Holds the CRDetailsVO */
    private CRDetailsVO crDetailsVO;

    /**
     * @return Returns the correspondenceSK.
     */
    public String getCorrespondenceSK()
    {
        return correspondenceSK;
    }

    /**
     * @param correspondenceSK
     *            The correspondenceSK to set.
     */
    public void setCorrespondenceSK(String correspondenceSK)
    {
        this.correspondenceSK = correspondenceSK;
    }

    /**
     * @return Returns the crn.
     */
    public String getCrn()
    {
        return crn;
    }

    /**
     * @param crn
     *            The crn to set.
     */
    public void setCrn(String crn)
    {
        this.crn = crn;
    }

    /**
     * @return Returns the entity.
     */
    public String getEntity()
    {
        return entity;
    }

    /**
     * @param entity
     *            The entity to set.
     */
    public void setEntity(String entity)
    {
        this.entity = entity;
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
     * @return Returns the crDetailsVO.
     */
    public CRDetailsVO getCrDetailsVO()
    {
        return crDetailsVO;
    }

    /**
     * @param crDetailsVO
     *            The crDetailsVO to set.
     */
    public void setCrDetailsVO(CRDetailsVO crDetailsVO)
    {
        this.crDetailsVO = crDetailsVO;
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
