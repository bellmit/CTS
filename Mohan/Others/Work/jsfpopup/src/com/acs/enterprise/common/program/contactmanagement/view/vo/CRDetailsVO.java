/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/** Vo for Crdetails */
public class CRDetailsVO
        extends EnterpriseBaseVO
{
    /**
     * Constructor for CRsWatchListVO
     */
    public CRDetailsVO()
    {
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CRDetailsVO.class);
        logger.debug("CRDetailsVO constructor");
    }

    /** Hold the createdBy */
    private String createdBy;

    /** Hold the contactName */
    private String contactName;

    /** Hold the description */
    private String catDescription;

    /** Holds the source name */
    private String source;

    /** Holds subjectCode of type String */
    private String subjectCode;

    /** Holds noteSet value */
    private String noteSet;

    /** Holds the supervisorAppReq flag */
    private boolean supervisorAppReq = false;

    /**
     * @return Returns the catDescription.
     */
    public String getCatDescription()
    {
        return catDescription;
    }

    /**
     * @param catDescription
     *            The catDescription to set.
     */
    public void setCatDescription(String catDescription)
    {
        this.catDescription = catDescription;
    }

    /**
     * @return Returns the contactName.
     */
    public String getContactName()
    {
        return contactName;
    }

    /**
     * @param contactName
     *            The contactName to set.
     */
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    /**
     * @return Returns the createdBy.
     */
    public String getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @param createdBy
     *            The createdBy to set.
     */
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    /**
     * @return Returns the noteSet.
     */
    public String getNoteSet()
    {
        return noteSet;
    }

    /**
     * @param noteSet
     *            The noteSet to set.
     */
    public void setNoteSet(String noteSet)
    {
        this.noteSet = noteSet;
    }

    /**
     * @return Returns the source.
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param source
     *            The source to set.
     */
    public void setSource(String source)
    {
        this.source = source;
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
     * @return Returns the supervisorAppReq.
     */
    public boolean isSupervisorAppReq()
    {
        return supervisorAppReq;
    }

    /**
     * @param supervisorAppReq The supervisorAppReq to set.
     */
    public void setSupervisorAppReq(boolean supervisorAppReq)
    {
        this.supervisorAppReq = supervisorAppReq;
    }
}
