/*
 * Created on Mar 6, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldTable;

/**
 * @author arcndnd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomFieldAssignmentVO extends AuditaleEnterpriseBaseVO {
	
	 /** Holds the subjectAreaSK */
    private Long subjectAreaSK;
    
    /** Holds the CustomFieldSK */
    private Long customFieldSK;

    /** Holds the CustomFieldTableID */
    private String customFieldTableID;

   /** Holds displaySortOrderNum of type Integer */
    private Integer displaySortOrderNum;

    /** Holds requiredIndicator of type Boolean */
    private Boolean requiredIndicator;

    /** Holds the modifyAfterSave Indicator */
    private Boolean modifyAfterSaveIndicator;

    /** Holds the CustomFieldTable object */
    //commented for unused variables
    //private CustomFieldTable customFieldTable;

    /** Holds the CustomField object */
    //commented for unused variables
    //private CustomField customField;

    /** Holds correspondenceCategory of type CorrespondenceCategory */
    //commented for unused variables
    //private CorrespondenceCategory correspondenceCategory;

    /** Holds caseType of type CaseType */
    private CaseType caseType;
    
    


	/**
	 * @return Returns the caseType.
	 */
	public CaseType getCaseType() {
		return caseType;
	}
	/**
	 * @param caseType The caseType to set.
	 */
	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}
	/**
	 * @return Returns the correspondenceCategory.
	 */
	/*public CorrespondenceCategory getCorrespondenceCategory() {
		return correspondenceCategory;
	}
	*//**
	 * @param correspondenceCategory The correspondenceCategory to set.
	 *//*
	public void setCorrespondenceCategory(
			CorrespondenceCategory correspondenceCategory) {
		this.correspondenceCategory = correspondenceCategory;
	}*/
	/**
	 * @return Returns the customField.
	 */
	/*public CustomField getCustomField() {
		return customField;
	}
	*//**
	 * @param customField The customField to set.
	 *//*
	public void setCustomField(CustomField customField) {
		this.customField = customField;
	}*/
	/**
	 * @return Returns the customFieldSK.
	 */
	public Long getCustomFieldSK() {
		return customFieldSK;
	}
	/**
	 * @param customFieldSK The customFieldSK to set.
	 */
	public void setCustomFieldSK(Long customFieldSK) {
		this.customFieldSK = customFieldSK;
	}
	/**
	 * @return Returns the customFieldTable.
	 */
	/*public CustomFieldTable getCustomFieldTable() {
		return customFieldTable;
	}
	*//**
	 * @param customFieldTable The customFieldTable to set.
	 *//*
	public void setCustomFieldTable(CustomFieldTable customFieldTable) {
		this.customFieldTable = customFieldTable;
	}*/
	/**
	 * @return Returns the customFieldTableID.
	 */
	public String getCustomFieldTableID() {
		return customFieldTableID;
	}
	/**
	 * @param customFieldTableID The customFieldTableID to set.
	 */
	public void setCustomFieldTableID(String customFieldTableID) {
		this.customFieldTableID = customFieldTableID;
	}
/**
 * @return Returns the displaySortOrderNum.
 */
public Integer getDisplaySortOrderNum() {
	return displaySortOrderNum;
}
/**
 * @param displaySortOrderNum The displaySortOrderNum to set.
 */
public void setDisplaySortOrderNum(Integer displaySortOrderNum) {
	this.displaySortOrderNum = displaySortOrderNum;
}
	/**
	 * @return Returns the modifyAfterSaveIndicator.
	 */
	public Boolean getModifyAfterSaveIndicator() {
		return modifyAfterSaveIndicator;
	}
	/**
	 * @param modifyAfterSaveIndicator The modifyAfterSaveIndicator to set.
	 */
	public void setModifyAfterSaveIndicator(Boolean modifyAfterSaveIndicator) {
		this.modifyAfterSaveIndicator = modifyAfterSaveIndicator;
	}
	/**
	 * @return Returns the requiredIndicator.
	 */
	public Boolean getRequiredIndicator() {
		return requiredIndicator;
	}
	/**
	 * @param requiredIndicator The requiredIndicator to set.
	 */
	public void setRequiredIndicator(Boolean requiredIndicator) {
		this.requiredIndicator = requiredIndicator;
	}
	/**
	 * @return Returns the subjectAreaSK.
	 */
	public Long getSubjectAreaSK() {
		return subjectAreaSK;
	}
	/**
	 * @param subjectAreaSK The subjectAreaSK to set.
	 */
	public void setSubjectAreaSK(Long subjectAreaSK) {
		this.subjectAreaSK = subjectAreaSK;
	}
}
