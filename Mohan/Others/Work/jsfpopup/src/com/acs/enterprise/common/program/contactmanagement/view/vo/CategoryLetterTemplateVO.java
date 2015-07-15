/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;


import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;

/**
 * This class will be the VO for Maintain Case Type Templates
 * 
 * @author Wipro
 */
public class CategoryLetterTemplateVO extends AuditaleEnterpriseBaseVO
                 implements Cloneable
{
    /**
     * 
     */
    /**
     * Constructor for CategoryLetterTemplateVO.
     */
    public CategoryLetterTemplateVO()
    {
        super();
        includeTemplate = false;
        optionalText = false;
        supervisorApprRqd = false;
    }
    private boolean includeTemplate;

    private String template;

    private String templateDescription;

    private boolean optionalText;

    private boolean supervisorApprRqd;
    
    /** Holds categorySK */
    private Long categorySK;

    /** Holds caseTypeSK */
    private String letterTemplateKeyData;

    
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
    /**
     * @return Returns the categorySK.
     */
    public Long getCategorySK()
    {
        return categorySK;
    }
    /**
     * @param categorySK The categorySK to set.
     */
    public void setCategorySK(Long categorySK)
    {
        this.categorySK = categorySK;
    }
    /**
     * This method overrides the clone method of the Object Class to clone the
     * CategoryLetterTempaltesVO.
     * 
     * @return Object : a clone of this instance.
     * @exception CloneNotSupportedException :
     *                CloneNotSupportedException if the object's class does not
     *                support the Cloneable interface. Subclasses that override
     *                the clone method can also throw this exception to indicate
     *                that an instance cannot be cloned.
     * @see java.lang.Cloneable
     */
    public Object clone()
            throws CloneNotSupportedException
    {
        return super.clone();
    }
    /**
     * Override equals method. 
     * (Indicates whether some other object is "equal to" this one)
     * 
     * @param other : Object
     * @return boolean : true if the both objects are same
     */
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (!(other instanceof CategoryLetterTemplateVO))
        {
            return false;
        }
        final CategoryLetterTemplateVO categoryTemplatesVO = (CategoryLetterTemplateVO) other;

        if ((categoryTemplatesVO.getLetterTemplateKeyData() != null)
                && (getLetterTemplateKeyData() != null)
                && (categoryTemplatesVO.getLetterTemplateKeyData().compareTo(getLetterTemplateKeyData()) == 0))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Override Hashcode method. This method returns a hash code value for the
     * object.
     * 
     * @return int. HashCode value
     */
    public int hashCode()
    {
        int result = -1;

        if (getLetterTemplateKeyData()!= null)
        {
            result = getLetterTemplateKeyData().hashCode();
        }

        return result * ContactManagementConstants.HASH_CODE_MULTIPLIER;
    }
}
