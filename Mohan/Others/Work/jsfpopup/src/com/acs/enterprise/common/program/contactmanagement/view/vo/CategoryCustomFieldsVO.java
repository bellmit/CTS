/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;

/**
 * @author Wipro
 *
 * This class is a value object, consists of Custom Fields datatable related elements.
 */
public class CategoryCustomFieldsVO
        extends AuditaleEnterpriseBaseVO
        implements Cloneable
{
    /**
     * Constructor for CategoryCustomFieldsVO.
     */
    public CategoryCustomFieldsVO()
    {
        super();
      //Below code is commented for changed the activeIncludeIndicator variable type from boolean to String  for the Defect ESPRD00802214.
        /*includeIndicator = false;*/
        includeIndicator = ContactManagementConstants.NO;
        cfRequired = false;
        cfProtectedOnSave = false;
    }
    /**
     * This field is used to store the Custome Field Include Indicator.
     */
    
    //Below code is commented  for changing the activeIncludeIndicator variable type from boolean to String  for the Defect ESPRD00802214.
    
   /* private boolean includeIndicator;*/

    private String includeIndicator;
    
    /**
	 * @return the includeIndicator
	 */
	public String getIncludeIndicator() {
		return includeIndicator;
	}

	/**
	 * @param includeIndicator the includeIndicator to set
	 */
	public void setIncludeIndicator(String includeIndicator) {
		this.includeIndicator = includeIndicator;
	}
	
	//Below code is added for the right mark image for the Defect ESPRD00802214
	
	private boolean includeIndicatorImage;
	/**
	 * @return the includeIndicatorImage
	 */
	public boolean isIncludeIndicatorImage() {
		return includeIndicatorImage;
	}

	/**
	 * @param includeIndicatorImage the includeIndicatorImage to set
	 */
	public void setIncludeIndicatorImage(boolean includeIndicatorImage) {
		this.includeIndicatorImage = includeIndicatorImage;
	}
	/**
     * This field is used to store Custome Field Column Description.
     */
    private String columnDesc;

    /**
     * This field is used to store Custome Field Data Type.
     */
    private String dataType;

    /**
     * This field is used to store Custome Field Length.
     */
    private int fieldLength;

    /**
     * This field is used to store whether Custome Field  is required.
     */
    private boolean cfRequired;

    /**
     * This field is used to store whether Custome Field is protected on save.
     */
    private boolean cfProtectedOnSave;
    
    /**
     * This field is used to store customFieldSK.
     */
    private Long customFieldSK;

    /**
     * This method is used to get the includeIndicator.
     * 
     * @return boolean : Returns the includeIndicator.
     */
    
    //Below code is commented  for changed the activeIncludeIndicator variable type from boolean to String  for the Defect ESPRD00802214.
    
   /* public boolean isIncludeIndicator()
    {
        return includeIndicator;
    }*/

    /**
     * This method is used to set the includeIndicator.
     * 
     * @param includeIndicator : The includeIndicator to set.
     */
 
    //Below code is commented  for changed the activeIncludeIndicator variable type from boolean to String  for the Defect ESPRD00802214.
    
   /* public void setIncludeIndicator(boolean includeIndicator)
    {
        this.includeIndicator = includeIndicator;
    }*/

    /**
     * This method is used to get the columnDesc.
     * 
     * @return String : Returns the columnDesc.
     */
    public String getColumnDesc()
    {
        return columnDesc;
    }

    /**
     * This method is used to set the columnDesc.
     * 
     * @param columnDesc : The columnDesc to set.
     */
    public void setColumnDesc(String columnDesc)
    {
        this.columnDesc = columnDesc;
    }

    /**
     * This method is used to get the dataType.
     * 
     * @return String : Returns the dataType.
     */
    public String getDataType()
    {
        return dataType;
    }

    /**
     * This method is used to set the dataType.
     * 
     * @param dataType : The dataType to set.
     */
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    /**
     * This method is used to get the fieldLength.
     * 
     * @return int : Returns the fieldLength.
     */
    public int getFieldLength()
    {
        return fieldLength;
    }

    /**
     * This method is used to set the fieldLength.
     * 
     * @param fieldLength : The fieldLength to set.
     */
    public void setFieldLength(int fieldLength)
    {
        this.fieldLength = fieldLength;
    }

    /**
     * This method is used to get the cfRequired.
     * 
     * @return boolean : Returns the cfRequired.
     */
    public boolean isCfRequired()
    {
        return cfRequired;
    }

    /**
     * This method is used to set the cfRequired.
     * 
     * @param cfRequired : The cfRequired to set.
     */
    public void setCfRequired(boolean cfRequired)
    {
        this.cfRequired = cfRequired;
    }

    /**
     * This method is used to get the cfProtectedOnSave.
     * 
     * @return boolean : Returns the cfProtectedOnSave.
     */
    public boolean isCfProtectedOnSave()
    {
        return cfProtectedOnSave;
    }

    /**
     * This method is used to set the cfProtectedOnSave.
     * 
     * @param cfProtectedOnSave : The cfProtectedOnSave to set.
     */
    public void setCfProtectedOnSave(boolean cfProtectedOnSave)
    {
        this.cfProtectedOnSave = cfProtectedOnSave;
    }
    
    /**
     * This method is used to get the customFieldSK.
     * 
     * @return Long : Returns the customFieldSK.
     */
    public Long getCustomFieldSK()
    {
        return customFieldSK;
    }
    
    /**
     * This method is used to set the customFieldSK.
     * 
     * @param customFieldSK : The customFieldSK to set.
     */
    public void setCustomFieldSK(Long customFieldSK)
    {
        this.customFieldSK = customFieldSK;
    }
    
    /**
     * This method overrides the clone method of the Object Class to clone the
     * CategoryCustomFieldsVO.
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
        if (!(other instanceof CategoryCustomFieldsVO))
        {
            return false;
        }
        final CategoryCustomFieldsVO categoryCustomFieldsVO = (CategoryCustomFieldsVO) other;

        if ((categoryCustomFieldsVO.getCustomFieldSK() != null)
                && (getCustomFieldSK() != null)
                && (categoryCustomFieldsVO.getCustomFieldSK().compareTo(getCustomFieldSK()) == 0))
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

        if (getCustomFieldSK() != null)
        {
            result = getCustomFieldSK().hashCode();
        }

        return result * ContactManagementConstants.HASH_CODE_MULTIPLIER;
    }
    
//  for fixing defect 673618
    /**
     * This field is used to store and display
     * Custome Field Column Description.
     */
    private String columnDescDisp;
    
    
	public String getColumnDescDisp() {
		return columnDescDisp;
	}
	public void setColumnDescDisp(String columnDescDisp) {
		this.columnDescDisp = columnDescDisp;
	}
}
