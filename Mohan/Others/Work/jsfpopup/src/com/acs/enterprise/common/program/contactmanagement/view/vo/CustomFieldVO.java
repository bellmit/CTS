/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;

/**
 * This class will be the VO for Custom Fields
 * 
 * @author Wipro
 */
public class CustomFieldVO
        extends AuditaleEnterpriseBaseVO
{
    /**
     * Constructor
     */
    public CustomFieldVO()
    {
        super();

        this.requiredInd = "true";
        this.protectedInd = "true";
        this.activeInd = "true";
    }

    /** Holds caseType of type CaseType */
       private CaseType caseType;
       
   
       
    /**
     * Holds checkbox in custom fields data table
     */
    private boolean customFieldSelected;

    /**
     * Holds the custom field column description
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String columnDescription;

    /**
     * Holds customFieldSK
     */
    private Long customFieldSK;

    /**
     * Holds DropDownVO
     */
    private DropDownVO dropDownVO = new DropDownVO();

    /**
     * Holds the list of dropdown VOs
     */
    private List dropDownList;

    /**
     * Holds custom field data type code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String dataType;

    /**
     * Holds length in bytes
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String length;

    /**
     * Holds scope of custom field
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String scope;

    /**
     * Holds active indicator
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String activeInd;

    /**
     * Holds required value indicator
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String requiredInd;

    /**
     * Holds values protected indicator
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String protectedInd;

    /**
     * Holds Drop down item description
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    //commented for unused variables
    //private String dropDownItemDesc;

    /**
     * Holds the sort order of the custom fields
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    //commented for unused variables
    //private Integer dropDownItemSortOrder;

    /**
     * Holds custom field old data type code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String oldScope;
    
    /**
     * Holds custom field Scope Assignment code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    //commented for unused variables
    //private List scopeAssignList;
    
    /**
     * Holds custom field data type code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String oldDataType;
    
    /**
     * Holds custom field scope value
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String scopeValue;
    
    
    
    /**
     * @return Returns the scopeValue.
     */
    public String getScopeValue()
    {
        return scopeValue;
    }
    /**
     * @param scopeValue The scopeValue to set.
     */
    public void setScopeValue(String scopeValue)
    {
        this.scopeValue = scopeValue;
    }
    /**
     * @return Returns the oldDataType.
     */
    public String getOldDataType()
    {
        return oldDataType;
    }
    /**
     * @param oldDataType The oldDataType to set.
     */
    public void setOldDataType(String oldDataType)
    {
        this.oldDataType = oldDataType;
    }
    /**
     * @return Returns the scopeAssignList.
     */
   /* public List getScopeAssignList()
    {
        return scopeAssignList;
    }
    *//**
     * @param scopeAssignList The scopeAssignList to set.
     *//*
    public void setScopeAssignList(List scopeAssignList)
    {
        this.scopeAssignList = scopeAssignList;
    }*/
    
    /**
     * @return Returns the oldScope.
     */
    public String getOldScope()
    {
        return oldScope;
    }
    /**
     * @param oldScope The oldScope to set.
     */
    public void setOldScope(String oldScope)
    {
        this.oldScope = oldScope;
    }
    /**
     * @return Returns the dropDownItemSortOrder.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
   /* public Integer getDropDownItemSortOrder()
    {
        return dropDownItemSortOrder;
    }

    *//**
     * @param thedropDownItemSortOrder
     *            The dropDownItemSortOrder to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     *//*
    public void setDropDownItemSortOrder(Integer thedropDownItemSortOrder)
    {
        dropDownItemSortOrder = thedropDownItemSortOrder;
    }*/

    /**
     * @return Returns the dropDownVO.
     */
    public DropDownVO getDropDownVO()
    {
        return dropDownVO;
    }

    /**
     * @param dropDownVO
     *            The dropDownVO to set.
     */
    public void setDropDownVO(DropDownVO dropDownVO)
    {
        this.dropDownVO = dropDownVO;
    }

    /**
     * @return Returns the customFieldSK.
     */
    public Long getCustomFieldSK()
    {
        return customFieldSK;
    }

    /**
     * @param customFieldSK
     *            The customFieldSK to set.
     */
    public void setCustomFieldSK(Long customFieldSK)
    {
        this.customFieldSK = customFieldSK;
    }

    /**
     * @return Returns the columnDescription.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getColumnDescription()
    {
        return columnDescription;
    }

    /**
     * @param thecolumnDescription
     *            The columnDescription to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setColumnDescription(String thecolumnDescription)
    {
        columnDescription = thecolumnDescription;
    }

    /**
     * @return Returns the dataType.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getDataType()
    {
        return dataType;
    }

    /**
     * @param thedataType
     *            The dataType to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setDataType(String thedataType)
    {
        dataType = thedataType;
    }

    /**
     * @return Returns the length.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getLength()
    {
        return length;
    }

    /**
     * @param thelength
     *            The length to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setLength(String thelength)
    {
        length = thelength;
    }

    /**
     * @return Returns the scope.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * @param thescope
     *            The scope to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setScope(String thescope)
    {
        scope = thescope;
    }

    /**
     * @return Returns the activeInd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getActiveInd()
    {
        return activeInd;
    }

    /**
     * @param theactiveInd
     *            The activeInd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setActiveInd(String theactiveInd)
    {
        activeInd = theactiveInd;
    }

    /**
     * @return Returns the requiredInd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getRequiredInd()
    {
        return requiredInd;
    }

    /**
     * @param therequiredInd
     *            The requiredInd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setRequiredInd(String therequiredInd)
    {
        requiredInd = therequiredInd;
    }

    /**
     * @return Returns the protectedInd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getProtectedInd()
    {
        return protectedInd;
    }

    /**
     * @param theprotectedInd
     *            The protectedInd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setProtectedInd(String theprotectedInd)
    {
        protectedInd = theprotectedInd;
    }

    /**
     * @return Returns the dropDownItemDesc.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
   /* public String getDropDownItemDesc()
    {
        return dropDownItemDesc;
    }

    *//**
     * @param thedropDownItemDesc
     *            The dropDownItemDesc to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     *//*
    public void setDropDownItemDesc(String thedropDownItemDesc)
    {
        dropDownItemDesc = thedropDownItemDesc;
    }*/

    /**
     * @return Returns the dropDownList.
     */
    public List getDropDownList()
    {
        return dropDownList;
    }

    /**
     * @param dropDownList
     *            The dropDownList to set.
     */
    public void setDropDownList(List dropDownList)
    {
        this.dropDownList = dropDownList;
    }

    /**
     * @param customFieldSelected
     *            The customFieldSelected to set.
     */
    public void setCustomFieldSelected(boolean customFieldSelected)
    {
        this.customFieldSelected = customFieldSelected;
    }

    /**
     * @return Returns the customFieldSelected.
     */
    public boolean isCustomFieldSelected()
    {
        return customFieldSelected;
    }
    /**
     * Holds the list of customFieldAssignment VOs
     */
    //commented for unused variables
    //private List customFieldAssignmentVO;

	/**
	 * @return Returns the customFieldAssignmentVO.
	 */
	/*public List getCustomFieldAssignmentVO() 
	{
		return customFieldAssignmentVO;
	}
	*//**
	 * @param customFieldAssignmentVO The customFieldAssignmentVO to set.
	 *//*
	public void setCustomFieldAssignmentVO(List customFieldAssignmentVO) 
	{
		this.customFieldAssignmentVO = customFieldAssignmentVO;
	}*/
    /**
     * @return Returns the caseType.
     */
    public CaseType getCaseType()
    {
        return caseType;
    }
    /**
     * @param caseType The caseType to set.
     */
    public void setCaseType(CaseType caseType)
    {
        this.caseType = caseType;
    }
    
    
    private String dataTypestr; 
	/**
	 * @return Returns the dataTypestr.
	 */
	public String getDataTypestr() {
		return dataTypestr;
	}
	/**
	 * @param dataTypestr The dataTypestr to set.
	 */
	public void setDataTypestr(String dataTypestr) {
		this.dataTypestr = dataTypestr;
	}
	
	private String activeFlag;
	private String requiredFlag = "false"; // Modified for the PanelGrid Fix
	private String protectedFlag = "false"; // Modified for the PanelGrid Fix
	/**
	 * @return Returns the activeFlag.
	 */
	public String getActiveFlag() {
		return activeFlag;
	}
	/**
	 * @param activeFlag The activeFlag to set.
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return Returns the protectedFlag.
	 */
	public String getProtectedFlag() {
		return protectedFlag;
	}
	/**
	 * @param protectedFlag The protectedFlag to set.
	 */
	public void setProtectedFlag(String protectedFlag) {
		this.protectedFlag = protectedFlag;
	}
	/**
	 * @return Returns the requiredFlag.
	 */
	public String getRequiredFlag() {
		return requiredFlag;
	}
	/**
	 * @param requiredFlag The requiredFlag to set.
	 */
	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}
	
	// Begin - Added for the PanelGrid Fix
	private String componentInputData;
	private String labelName;
	private boolean checkBoxValue;
	private List customFieldDDList = new ArrayList();

	/**
	 * @return the componentInputData
	 */
	public String getComponentInputData() {
		return componentInputData;
	}
	/**
	 * @param componentInputData the componentInputData to set
	 */
	public void setComponentInputData(String componentInputData) {
		this.componentInputData = componentInputData;
	}
	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}
	/**
	 * @param labelName the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	/**
	 * @return the checkBoxValue
	 */
	public boolean getCheckBoxValue() {
		return checkBoxValue;
	}
	/**
	 * @param checkBoxValue the checkBoxValue to set
	 */
	public void setCheckBoxValue(boolean checkBoxValue) {
		this.checkBoxValue = checkBoxValue;
	}
	/**
	 * @return the cusomFieldDDList
	 */
	public List getCustomFieldDDList() {
		return customFieldDDList;
	}
	/**
	 * @param cusomFieldDDList the cusomFieldDDList to set
	 */
	public void setCustomFieldDDList(List customFieldDDList) {
		this.customFieldDDList = customFieldDDList;
	}
	private String elementID;

	/**
	 * @return the elementID
	 */
	public String getElementID() {
		return elementID;
	}
	/**
	 * @param elementID the elementID to set
	 */
	public void setElementID(String elementID) {
		this.elementID = elementID;
	}
	
	// End - Added for the PanelGrid Fix
	
}
