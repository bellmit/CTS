/**
 * 
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * @author mohammedr
 *
 */
public class CaseViewVO extends AuditaleEnterpriseBaseVO {

	 /** holds the CaseSK */
    private Long caseSK;
    
    /** holds the case Type */
    private String caseType;

    /** holds the Case title */
    private String caseTitle;
    
    /** holds the Create By */
    private String createdBy;
    
    /** holds the Create Date */
    private Date createdDate;
    
    /** Holds noteSet value */
    private String noteSet;

	/**
	 * @return the caseSK
	 */
	public Long getCaseSK() {
		return caseSK;
	}

	/**
	 * @param caseSK the caseSK to set
	 */
	public void setCaseSK(Long caseSK) {
		this.caseSK = caseSK;
	}

	/**
	 * @return the caseType
	 */
	public String getCaseType() {
		return caseType;
	}

	/**
	 * @param caseType the caseType to set
	 */
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	/**
	 * @return the caseTitle
	 */
	public String getCaseTitle() {
		return caseTitle;
	}

	/**
	 * @param caseTitle the caseTitle to set
	 */
	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the noteSet
	 */
	public String getNoteSet() {
		return noteSet;
	}

	/**
	 * @param noteSet the noteSet to set
	 */
	public void setNoteSet(String noteSet) {
		this.noteSet = noteSet;
	}
}
