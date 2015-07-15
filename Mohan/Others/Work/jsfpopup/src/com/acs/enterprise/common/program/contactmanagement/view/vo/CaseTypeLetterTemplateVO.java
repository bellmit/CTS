/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;

/**
 * This class will be the VO for Maintain Case Type Templates
 * 
 * @author Wipro
 */
public class CaseTypeLetterTemplateVO extends AuditaleEnterpriseBaseVO
{
    
    private boolean includeTemplate;

    private String template;

    private String templateDescription;

    private boolean optionalText;

    private boolean supervisorApprRqd;
    
    /** Holds caseTypeSK */
    private Long caseTypeSK;

    /** Holds caseTypeSK */
    private String letterTemplateKeyData;

    /** Holds caseType */
    private CaseType caseType;

    /** Holds letterTemplate */
    private LetterTemplate letterTemplate;

    /**
     * @param supervisorApprRqd
     *            The supervisorApprRqd to set.
     */
    public void setSupervisorApprRqd(boolean supervisorApprRqd)
    {
        this.supervisorApprRqd = supervisorApprRqd;
    }

    /**
     * @return Returns the supervisorApprRqd.
     */
    public boolean getSupervisorApprRqd()
    {
        return supervisorApprRqd;
    }

    /**
     * @param template
     *            The template to set.
     */
    public void setTemplate(String template)
    {
        this.template = template;
    }

    /**
     * @return Returns the template.
     */
    public String getTemplate()
    {
        return template;
    }

    /**
     * @param templateDescription
     *            The templateDescription to set.
     */
    public void setTemplateDescription(String templateDescription)
    {
        this.templateDescription = templateDescription;
    }

    /**
     * @return Returns the templateDescription.
     */
    public String getTemplateDescription()
    {
        return templateDescription;
    }

    /**
     * @param optionalText
     *            The optionalText to set.
     */
    public void setOptionalText(boolean optionalText)
    {
        this.optionalText = optionalText;
    }

    /**
     * @return Returns the optionalText.
     */
    public boolean getOptionalText()
    {
        return optionalText;
    }

    /**
     * @param includeTemplate The includeTemplate to set.
     */
    public void setIncludeTemplate(boolean includeTemplate)
    {
        this.includeTemplate = includeTemplate;
    }

    /**
     * @return Returns the includeTemplate.
     */
    public boolean isIncludeTemplate()
    {
        return includeTemplate;
    }
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
	 * @return Returns the caseTypeSK.
	 */
	public Long getCaseTypeSK() {
		return caseTypeSK;
	}
	/**
	 * @param caseTypeSK The caseTypeSK to set.
	 */
	public void setCaseTypeSK(Long caseTypeSK) {
		this.caseTypeSK = caseTypeSK;
	}
	/**
	 * @return Returns the letterTemplate.
	 */
	public LetterTemplate getLetterTemplate() {
		return letterTemplate;
	}
	/**
	 * @param letterTemplate The letterTemplate to set.
	 */
	public void setLetterTemplate(LetterTemplate letterTemplate) {
		this.letterTemplate = letterTemplate;
	}
	/**
	 * @return Returns the letterTemplateKeyData.
	 */
	public String getLetterTemplateKeyData() {
		return letterTemplateKeyData;
	}
	/**
	 * @param letterTemplateKeyData The letterTemplateKeyData to set.
	 */
	public void setLetterTemplateKeyData(String letterTemplateKeyData) {
		this.letterTemplateKeyData = letterTemplateKeyData;
	}
}
