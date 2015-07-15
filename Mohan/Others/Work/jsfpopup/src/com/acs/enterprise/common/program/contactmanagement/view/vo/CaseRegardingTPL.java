/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * This class will be the Case Regarding for TPL Details Persistent Data
 * Object that are used in the Log Case.
 * 
 * @author Wipro
 */
public class CaseRegardingTPL
        extends EnterpriseBaseVO implements Cloneable
{
   
    /** holds the carrier Name */
    private String name;

    /** holds the entity Type */
    private String entityType;
    
    /** holds the entity Type Desc*/
    private String entityTypeDesc;

    /** holds the carrierID */
    private String entityId;
    
    /** holds the commonEntity SK */
    private String commonEntitySK;
    
    /** holds the SSN */
    private String ssn;
    
    /** holds the LOB */
    private String lobCode;
    
    /** holds the District Office */
    private String districOffice;
    
    /** holds the providerTypeCode */
    private String provTypeCode;
    
    /** holds the providerTypeCode Desc*/
    private String provTypeCodeDesc;
    
    /** holds the eMail */
    private String email;
    
    /** holds the status */
    private String status;
    
    /** holds the specialty */
    private String specialty;
    
    /** used for dummy */
    private String dummy = "dummy";

   
    private String policyHolderID;

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
     * @return Returns the entityId.
     */
    public String getEntityId()
    {
        return entityId;
    }
    /**
     * @param entityId The entityId to set.
     */
    public void setEntityId(String entityId)
    {
        this.entityId = entityId;
    }
    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return Returns the dummy.
     */
    public String getDummy()
    {
        return dummy;
    }
    /**
     * @param dummy The dummy to set.
     */
    public void setDummy(String dummy)
    {
        this.dummy = dummy;
    }
    
    /**
     * @return Returns the districOffice.
     */
    public String getDistricOffice()
    {
        return districOffice;
    }
    /**
     * @param districOffice The districOffice to set.
     */
    public void setDistricOffice(String districOffice)
    {
        this.districOffice = districOffice;
    }
    /**
     * @return Returns the lobCode.
     */
    public String getLobCode()
    {
        return lobCode;
    }
    /**
     * @param lobCode The lobCode to set.
     */
    public void setLobCode(String lobCode)
    {
        this.lobCode = lobCode;
    }
    /**
     * @return Returns the ssn.
     */
    public String getSsn()
    {
        return ssn;
    }
    /**
     * @param ssn The ssn to set.
     */
    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }
    /**
     * @return Returns the provTypeCode.
     */
    public String getProvTypeCode()
    {
        return provTypeCode;
    }
    /**
     * @param provTypeCode The provTypeCode to set.
     */
    public void setProvTypeCode(String provTypeCode)
    {
        this.provTypeCode = provTypeCode;
    }
    /**
     * @return Returns the email.
     */
    public String getEmail()
    {
        return email;
    }
    /**
     * @param email The email to set.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    /**
     * @return Returns the specialty.
     */
    public String getSpecialty()
    {
        return specialty;
    }
    /**
     * @param specialty The specialty to set.
     */
    public void setSpecialty(String specialty)
    {
        this.specialty = specialty;
    }
    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    /**
     * This method overrides the clone method of the Object Class to clone the
     * CorrespondenceForVO.
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
        CaseRegardingTPL regardingTPL = (CaseRegardingTPL) super.clone();        

        return regardingTPL;
    }
    /**
     * @return Returns the commonEntitySK.
     */
    public String getCommonEntitySK()
    {
        return commonEntitySK;
    }
    /**
     * @param commonEntitySK The commonEntitySK to set.
     */
    public void setCommonEntitySK(String commonEntitySK)
    {
        this.commonEntitySK = commonEntitySK;
    }
    /**
     * @return Returns the entityTypeDesc.
     */
    public String getEntityTypeDesc()
    {
        return entityTypeDesc;
    }
    /**
     * @param entityTypeDesc The entityTypeDesc to set.
     */
    public void setEntityTypeDesc(String entityTypeDesc)
    {
        this.entityTypeDesc = entityTypeDesc;
    }
    /**
     * @return Returns the provTypeCodeDesc.
     */
    public String getProvTypeCodeDesc()
    {
        return provTypeCodeDesc;
    }
    /**
     * @param provTypeCodeDesc The provTypeCodeDesc to set.
     */
    public void setProvTypeCodeDesc(String provTypeCodeDesc)
    {
        this.provTypeCodeDesc = provTypeCodeDesc;
    }
    
    /** holds the tplGroupId */
    private String tplGroupId;
    
	/**
	 * @return Returns the tplGroupId.
	 */
	public String getTplGroupId() {
		return tplGroupId;
	}
	/**
	 * @param tplGroupId The tplGroupId to set.
	 */
	public void setTplGroupId(String tplGroupId) {
		this.tplGroupId = tplGroupId;
	}
	/**
	 * @return Returns the policyHolderID.
	 */
	public String getPolicyHolderID() {
		return policyHolderID;
	}
	/**
	 * @param policyHolderID The policyHolderID to set.
	 */
	public void setPolicyHolderID(String policyHolderID) {
		this.policyHolderID = policyHolderID;
	}
}
