/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro
 *
 * This class is a value object, consists of Subject data table related elements.
 */
public class CategorySubjectVO
        extends AuditaleEnterpriseBaseVO
        implements Cloneable
{
    /** Enterprise Logger for Logging. */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CategorySubjectVO.class);
    
    /**
     * Constructor for CategorySubjectVO.
     */
    public CategorySubjectVO()
    {
        super();
    }
    
    /**
     * This field is used to store the Include Indicator.
     */
    private String includeIndicator;
    
    private boolean includeIndicatorforimage;

    /**
     * This field is used to store Subject Description.
     */
    private String subjectDesc;

    /**
     * This field is used to store Subject Valid Value Code.
     */
    private String subjectCode;
    
    /**
     * This method is used to get the includeIndicator.
     * 
     * @return boolean : Returns the includeIndicator.
     */
   
    /**
     * This method is used to get the subjectDesc.
     * 
     * @return String : Returns the subjectDesc.
     */
    public String getSubjectDesc()
    {
        return subjectDesc;
    }

    /**
     * This method is used to set the subjectDesc.
     * 
     * @param subjectDesc : The subjectDesc to set.
     */
    public void setSubjectDesc(String subjectDesc)
    {
        this.subjectDesc = subjectDesc;
    }

    /**
     * This method is used to get the subjectCode.
     * 
     * @return String : Returns the subjectCode.
     */
    public String getSubjectCode()
    {
        return subjectCode;
    }

    /**
     * This method is used to set the subjectCode.
     * 
     * @param subjectCode : The subjectCode to set.
     */
    public void setSubjectCode(String subjectCode)
    {
        this.subjectCode = subjectCode;
    }

    /**
     * This method overrides the clone method of the Object Class to clone the
     * CategorySubjectVO.
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
        if (!(other instanceof CategorySubjectVO))
        {
            return false;
        }
        final CategorySubjectVO categorySubjectVO = (CategorySubjectVO) other;

        if ((categorySubjectVO.getSubjectCode() != null)
                && (getSubjectCode() != null)
                && categorySubjectVO.getSubjectCode().equals(getSubjectCode())
                || ((categorySubjectVO.getSubjectDesc() != null)
                        && (getSubjectDesc() != null) && categorySubjectVO
                        .getSubjectDesc().equals(getSubjectDesc())))
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

        if (getSubjectCode() != null && getSubjectDesc() != null)
        {
            result = (getSubjectCode() + getSubjectDesc()).hashCode();
        }

        return result * ContactManagementConstants.HASH_CODE_MULTIPLIER;
    }
	/**
	 * @return Returns the includeIndicator.
	 */
	public String getIncludeIndicator() {
		return includeIndicator;
	}
	/**
	 * @param includeIndicator The includeIndicator to set.
	 */
	public void setIncludeIndicator(String includeIndicator) {
		this.includeIndicator = includeIndicator;
	}
	/**
	 * @return Returns the logger.
	 */
	public EnterpriseLogger getLogger() {
		return logger;
	}
	/**
	 * @param logger The logger to set.
	 */
	public void setLogger(EnterpriseLogger logger) {
		this.logger = logger;
	}
	/**
	 * @return Returns the includeIndicatorforimage.
	 */
	public boolean isIncludeIndicatorforimage() {
		return includeIndicatorforimage;
	}
	/**
	 * @param includeIndicatorforimage The includeIndicatorforimage to set.
	 */
	public void setIncludeIndicatorforimage(boolean includeIndicatorforimage) {
		this.includeIndicatorforimage = includeIndicatorforimage;
	}
}
