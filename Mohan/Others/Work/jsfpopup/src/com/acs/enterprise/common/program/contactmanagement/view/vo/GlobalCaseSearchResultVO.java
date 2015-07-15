
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;

/**
 * Holds the global case search results.
 * 
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class GlobalCaseSearchResultVO
        extends EnterpriseSearchResultsVO
{
    /**
     * Constructor
     */
    public GlobalCaseSearchResultVO()
    {
        super();
    }

    /**
     * Infinite Added for Defect ESPRD00304489
     * Holds commonEntitySK
     */
    
    private String commonEntitySK;
    
    /**
     * Holds entityID
     */
    private String entityID;

    /**
     * Holds CaseSk
     */
    private String caseSK;

    /**
     * Holds the created date.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String createdDate;

    /**
     * Holds the entity Name.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String entityName;

    /**
     * Holds the entity type
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String entityType;

    /**
     * Holds the entityTypeDesc;
     */
    private String entityTypeDesc;

    /**
     * Status
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String status;

    /**
     * Holds the assigned to. The case record whom it is assigned to
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String assignedTo;

    /**
     * Holds the case type.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String caseType;

    /**
     * Holds the Lob
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String lob;

    /**
     * Holds the type.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String type;

    /**
     * @return Returns the assignedTo.
     */
    public String getAssignedTo()
    {
        return assignedTo;
    }

    /**
     * @param assignedTo
     *            The assignedTo to set.
     */
    public void setAssignedTo(String assignedTo)
    {
        this.assignedTo = assignedTo;
    }

    /**
     * @return Returns the caseType.
     */
    public String getCaseType()
    {
        return caseType;
    }

    /**
     * @param caseType
     *            The caseType to set.
     */
    public void setCaseType(String caseType)
    {
        this.caseType = caseType;
    }

    /**
     * @return Returns the createdDate.
     */
    public String getCreatedDate()
    {
        return createdDate;
    }

    /**
     * @param createdDate
     *            The createdDate to set.
     */
    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    /**
     * @return Returns the entityName.
     */
    public String getEntityName()
    {
        return entityName;
    }

    /**
     * @param entityName
     *            The entityName to set.
     */
    public void setEntityName(String entityName)
    {
        this.entityName = entityName;
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
     * @return Returns the lob.
     */
    public String getLob()
    {
        return lob;
    }

    /**
     * @param lob
     *            The lob to set.
     */
    public void setLob(String lob)
    {
        this.lob = lob;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return Returns the entityTypeDesc.
     */
    public String getEntityTypeDesc()
    {
        return entityTypeDesc;
    }

    /**
     * @param entityTypeDesc
     *            The entityTypeDesc to set.
     */
    public void setEntityTypeDesc(String entityTypeDesc)
    {
        this.entityTypeDesc = entityTypeDesc;
    }

    /**
     * @return Returns the caseSK.
     */
    public String getCaseSK()
    {
        return caseSK;
    }

    /**
     * @param caseSK
     *            The caseSK to set.
     */
    public void setCaseSK(String caseSK)
    {
        this.caseSK = caseSK;
    }

    /**
     * @return Returns the entityID.
     */
    public String getEntityID()
    {
        return entityID;
    }

    /**
     * @param entityID
     *            The entityID to set.
     */
    public void setEntityID(String entityID)
    {
        this.entityID = entityID;
    }
    
	/**
	 * @return Returns the commonEntitySK.
	 */
	public String getCommonEntitySK() {
		return commonEntitySK;
	}
	/**
	 * @param commonEntitySK The commonEntitySK to set.
	 */
	public void setCommonEntitySK(String commonEntitySK) {
		this.commonEntitySK = commonEntitySK;
	}
	private String moduleType;
	/**
	 * @return Returns the moduleType.
	 */
	public String getModuleType() {
		return moduleType;
	}
	/**
	 * @param moduleType The moduleType to set.
	 */
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	private String paramNameForIPC;
	private String paramValueForIPC;
	/**
	 * @return Returns the paramNameForIPC.
	 */
	public String getParamNameForIPC() {
		return paramNameForIPC;
	}
	/**
	 * @param paramNameForIPC The paramNameForIPC to set.
	 */
	public void setParamNameForIPC(String paramNameForIPC) {
		this.paramNameForIPC = paramNameForIPC;
	}
	/**
	 * @return Returns the paramValueForIPC.
	 */
	public String getParamValueForIPC() {
		return paramValueForIPC;
	}
	/**
	 * @param paramValueForIPC The paramValueForIPC to set.
	 */
	public void setParamValueForIPC(String paramValueForIPC) {
		this.paramValueForIPC = paramValueForIPC;
	}
}