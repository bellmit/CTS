/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/** Vo for CRsWatchList */
public class CRsWatchListVO
        extends EnterpriseBaseVO
{

    /**
     * Constructor for CRsWatchListVO
     */
    public CRsWatchListVO()
    {
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CRsWatchListVO.class);
        logger.debug("CRsWatchListVO constructor");
    }

    /** Hold the correspondenceSK */
    private String correspondenceSK;

    /** Hold the openDate */
    private String openDate;

    /** Hold the status code */
    private String statusCode;

    /** Hold the workUnitSK */
    private String userOrWorkUnit;

    /** Hold the priorityCode */
    private String priorityCode;

    /** Holds the entity name */
    private String entity;

    /** Holds the entity type name */
    private String entityType;

    /** Holds the entity type name */
    private String crn;

    /** HOlds the Username */
    private String userName;

    /** Holds the CRDetailsVO */
    private CRDetailsVO crDetailsVO;
    
    /** Holds the workUnitSk */
    private Long workUnitSk;

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
     * @return Returns the userOrWorkUnit.
     */
    public String getUserOrWorkUnit()
    {
        return userOrWorkUnit;
    }

    /**
     * @param userOrWorkUnit
     *            The userOrWorkUnit to set.
     */
    public void setUserOrWorkUnit(String userOrWorkUnit)
    {
        this.userOrWorkUnit = userOrWorkUnit;
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
     * @return Returns the userName.
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
	/**
	 * @return Returns the workUnitSk.
	 */
	public Long getWorkUnitSk() {
		return workUnitSk;
	}
	/**
	 * @param workUnitSk The workUnitSk to set.
	 */
	public void setWorkUnitSk(Long workUnitSk) {
		this.workUnitSk = workUnitSk;
	}
}
