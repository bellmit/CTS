/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

/**
 * This class will be the Case Regarding for Provider Details Persistent Data
 * Object that are used in the Log Case.
 * 
 * @author Wipro
 */
public class CaseRegardingProviderVO
        extends CaseRegardingTPL
{

    /** holds the Entity Type Code */
    private String providerTypeCode;

    /** holds the Entity Type Code Desc*/
    private String providerTypeCodeDesc;

    /** holds the Status */
    private String status;

    /** holds the Status Desc*/
    private String statusDesc;

    /** holds the ID type */
    private String idType;

    /** holds the entity Type */
    private String entityType;

    /** holds the entity type desc */
    private String entityTypeDesc;

    /** holds the provider ID */
    private String providerId;

    /** holds the Payee ID */
    private String payeeId;

    /** holds the name */
    private String name;

    /** holds the entity ID */
    private String entityId;

    /** holds the specialty */
    private String specialty;

    /** holds the specialty Desc*/
    private String specialtyDesc;

    /** holds the commonEntitySK */
    private String commonEntitySK;

    /** used for dummy */
    private String dummy = "dummy";

    
    /**
     * @return Returns the entityId.
     */
    public String getEntityId()
    {
        return entityId;
    }

    /**
     * @param entityId
     *            The entityId to set.
     */
    public void setEntityId(String entityId)
    {
        this.entityId = entityId;
        
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
     * @return Returns the providerTypeCode.
     */
    public String getProviderTypeCode()
    {
        return providerTypeCode;
    }

    /**
     * @param providerTypeCode The providerTypeCode to set.
     */
    public void setProviderTypeCode(String providerTypeCode)
    {
        this.providerTypeCode = providerTypeCode;
    }

    /**
     * @return Returns the idType.
     */
    public String getIdType()
    {
        return idType;
    }

    /**
     * @param idType
     *            The idType to set.
     */
    public void setIdType(String idType)
    {
        this.idType = idType;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the payeeId.
     */
    public String getPayeeId()
    {
        return payeeId;
    }

    /**
     * @param payeeId
     *            The payeeId to set.
     */
    public void setPayeeId(String payeeId)
    {
        this.payeeId = payeeId;
    }

    /**
     * @return Returns the providerId.
     */
    public String getProviderId()
    {
        return providerId;
    }

    /**
     * @param providerId
     *            The providerId to set.
     */
    public void setProviderId(String providerId)
    {
        this.providerId = providerId;
    }

    /**
     * @return Returns the specialty.
     */
    public String getSpecialty()
    {
        return specialty;
    }

    /**
     * @param specialty
     *            The specialty to set.
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
     * @param status
     *            The status to set.
     */
    public void setStatus(String status)
    {
        this.status = status;
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
     * @return Returns the providerTypeCodeDesc.
     */
    public String getProviderTypeCodeDesc()
    {
        return providerTypeCodeDesc;
    }

    /**
     * @param providerTypeCodeDesc The providerTypeCodeDesc to set.
     */
    public void setProviderTypeCodeDesc(String providerTypeCodeDesc)
    {
        this.providerTypeCodeDesc = providerTypeCodeDesc;
    }

    /**
     * @return Returns the specialtyDesc.
     */
    public String getSpecialtyDesc()
    {
        return specialtyDesc;
    }

    /**
     * @param specialtyDesc The specialtyDesc to set.
     */
    public void setSpecialtyDesc(String specialtyDesc)
    {
        this.specialtyDesc = specialtyDesc;
    }

    /**
     * @return Returns the statusDesc.
     */
    public String getStatusDesc()
    {
        return statusDesc;
    }

    /**
     * @param statusDesc The statusDesc to set.
     */
    public void setStatusDesc(String statusDesc)
    {
        this.statusDesc = statusDesc;
    }
}
