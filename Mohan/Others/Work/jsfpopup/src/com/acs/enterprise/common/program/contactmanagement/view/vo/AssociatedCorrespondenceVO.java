/*
 * Created on Dec 10, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * Holds the associated correspondence information
 */
public class AssociatedCorrespondenceVO  extends AuditaleEnterpriseBaseVO 
       // extends EnterpriseBaseVO //CR_ESPRD00373565_LogCase_04AUG2010
{
    

    /** Enterprise Logger for Logging */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(AssociatedCorrespondenceVO.class);
    
    /**
     * The date the correspondence was opened.
     */
    private String openDate;

    /**
     * The subject key where subject related information is there
     */
    private String subject;

    /**
     * Under Review, Closed, etc.
     */
    private String status;
    
    private String statusstr;

    public String getStatusstr() {
		return statusstr;
	}
	public void setStatusstr(String statusstr) {
		this.statusstr = statusstr;
	}
	
	private String subjectstr;
	
	public String getSubjectstr() {
		return subjectstr;
	}
	public void setSubjectstr(String subjectstr) {
		this.subjectstr = subjectstr;
	}

	/**
     * The correspondence record number
     */
    private String correspondenceRecordNumber;

    /**
     * The prior correspondence
     */
    private Boolean linkToCR;
    
    private boolean linksToCR;
    
    private boolean dbAssocRecord;
	/**
	 * @return Returns the linksToCR.
	 */
	public boolean isLinksToCR() {
		return linksToCR;
	}
	/**
	 * @param linksToCR The linksToCR to set.
	 */
	public void setLinksToCR(boolean linksToCR) {
		this.linksToCR = linksToCR;
	}
    /**
     * The representative key in which the address related information is there.
     */
    private String contact;

    /**
     * The correspondence category in which the cr is belongs to.
     */
    private String category;

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
     * @return Returns the subject.
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @param thesubject
     *            The subject to set.
     */
    public void setSubject(String thesubject)
    {
        subject = thesubject;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param thestatus
     *            The status to set.
     */
    public void setStatus(String thestatus)
    {
        logger.debug("status");
        status = thestatus;
    }

    /**
     * @return Returns the correspondenceRecordNumber.
     */
    public String getCorrespondenceRecordNumber()
    {
        return correspondenceRecordNumber;
    }

    /**
     * @param correspondenceRecordNumber
     *            The correspondenceRecordNumber to set.
     */
    public void setCorrespondenceRecordNumber(String correspondenceRecordNumber)
    {
        this.correspondenceRecordNumber = correspondenceRecordNumber;
    }

    /**
     * @param linkToCR
     *            The linkToCR to set.
     */
    public void setLinkToCR(Boolean linkToCR)
    {
        this.linkToCR = linkToCR;
    }

    /**
     * @return Returns the linkToCR.
     */
    public Boolean getLinkToCR()
    {
        return linkToCR;
    }

    /**
     * @return Returns the contact.
     */
    public String getContact()
    {
        return contact;
    }

    /**
     * @param contact
     *            The contact to set.
     */
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    /**
     * @return Returns the category.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * @param category The category to set.
     */
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    /**
     * @param existing to differentaite the DB record
     */
    private boolean existing;
	/**
	 * @return Returns the existing.
	 */
	public boolean isExisting() {
		return existing;
	}
	/**
	 * @param existing The existing to set.
	 */
	public void setExisting(boolean existing) {
		this.existing = existing;
	}
	
	private Integer caseCRVersionNumber;
			
	/**
	 * @return Returns the caseCRVersionNumber.
	 */
	public Integer getCaseCRVersionNumber() {
		return caseCRVersionNumber;
	}
	/**
	 * @param caseCRVersionNumber The caseCRVersionNumber to set.
	 */
	public void setCaseCRVersionNumber(Integer caseCRVersionNumber) {
		this.caseCRVersionNumber = caseCRVersionNumber;
	}
	/**
	 * @return Returns the dbAssocRecord.
	 */
	public boolean isDbAssocRecord() {
		return dbAssocRecord;
	}
	/**
	 * @param dbAssocRecord The dbAssocRecord to set.
	 */
	public void setDbAssocRecord(boolean dbAssocRecord) {
		this.dbAssocRecord = dbAssocRecord;
	}
}
