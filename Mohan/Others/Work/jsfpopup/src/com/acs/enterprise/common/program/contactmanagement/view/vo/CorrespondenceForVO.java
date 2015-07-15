/*
 * Created on Dec 10, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * Holds the information about the correspondence for section.
 */
public class CorrespondenceForVO
        extends EnterpriseBaseVO
        implements Cloneable
{
    /**
     * Constructor for CorrespondenceRecordVO
     */
    public CorrespondenceForVO()
    {
        super();
        logger.debug("calling CorrespondenceForVO Constructor");
    }
    
    private String entityTypeCodeForNote;
    
    private String nameForNote;

	/**
	 * @return Returns the entityTypeCodeForNote.
	 */
	public String getEntityTypeCodeForNote() {
		return entityTypeCodeForNote;
	}
	/**
	 * @param entityTypeCodeForNote The entityTypeCodeForNote to set.
	 */
	public void setEntityTypeCodeForNote(String entityTypeCodeForNote) {
		this.entityTypeCodeForNote = entityTypeCodeForNote;
	}
	/**
	 * @return Returns the nameForNote.
	 */
	public String getNameForNote() {
		return nameForNote;
	}
	/**
	 * @param nameForNote The nameForNote to set.
	 */
	public void setNameForNote(String nameForNote) {
		this.nameForNote = nameForNote;
	}
    /** Enterprise Logger for Logging */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CorrespondenceForVO.class);
    
    /**
     * Holds the information about entity type code.
     */
    private String entityTypeCode;

    /**
     * Holds the information about entity type desc.
     */
    private String entityTypeDesc;

    /**
     * Holds the information about status such as open closed etc.
     */
    private String status;

    /**
     * Holds the information about status such as open closed etc.
     */
    private String statusStr;
    
    /**
     * This field is used to store cmEntityID.
     */
    private String cmEntityID;

    /**
     * CM Entity ID, which is currently known as Specific Entity ID, is an ID
     * created in Contact Management system for Unenrolled Entities.
     */
    private String entitySysId;

    /**
        * Holds the information about entityID 
        * Created to hold the value for entitySysId or CurrAltID depending on the type 
        * of EntityType .
        */
    private String entityId;

    /**
     * This is the Entity name
     */
    
    private String name;

    /**
     * This field is used to store providerType.
     */
    private String providerType;
    
    private String providerTypeStr;

    /**
     * This field is used to store ssn.
     */
    private String ssn;

    /**
     * This field is used to store email.
     */
    private String email;

    /**
     * This field is used to store specialityCode.
     */
    private String specialityCode;

    /**
     * This field is used to store carrierID.
     */
    private String carrierID;

    /**
     * This field is used to store carrierName.
     */
    private String carrierName;

    /**
     * Holds the information about contact.
     */
    private String contact;
    
    /**
     * Holds the information about currAltID.
     */
    private String currAltID;


    /**
     * @return Returns the entityTypeCode.
     */
    public String getEntityTypeCode()
    {
        return entityTypeCode;
    }

    /**
     * @param entityTypeCode
     *            The entityTypeCode to set.
     */
    public void setEntityTypeCode(String entityTypeCode)
    {
        this.entityTypeCode = entityTypeCode;
    }

    /**
     * This method is used to get the entityTypeDesc.
     * 
     * @return String : Returns the entityTypeDesc.
     */
    public String getEntityTypeDesc()
    {
        return entityTypeDesc;
    }

    /**
     * This method is used to set the entityTypeDesc.
     * 
     * @param entityTypeDesc :
     *            The entityTypeDesc to set.
     */
    public void setEntityTypeDesc(String entityTypeDesc)
    {
        this.entityTypeDesc = entityTypeDesc;
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
     * @return Returns the cmEntityID.
     */
    public String getCmEntityID()
    {
        return cmEntityID;
    }

    /**
     * @param thecmEntityID
     *            The cmEntityID to set.
     */
    public void setCmEntityID(String thecmEntityID)
    {
        cmEntityID = thecmEntityID;
    }

    /**
     * This method is used to get the entitySysId.
     * 
     * @return String : Returns the entitySysId.
     */
    public String getEntitySysId()
    {
        return entitySysId;
    }

    /**
     * This method is used to set the entitySysId.
     * 
     * @param entitySysId :
     *            The entitySysId to set.
     */
    public void setEntitySysId(String entitySysId)
    {
        this.entitySysId = entitySysId;
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
        CorrespondenceForVO correspondenceForVO = (CorrespondenceForVO) super
                .clone();

        return correspondenceForVO;
    }

    /**
     * @return Returns the providerType.
     */
    public String getProviderType()
    {
        return providerType;
    }

    /**
     * @param providerType
     *            The providerType to set.
     */
    public void setProviderType(String providerType)
    {
        this.providerType = providerType;
    }

    /**
     * @return Returns the ssn.
     */
    public String getSsn()
    {
        return ssn;
    }

    /**
     * @param ssn
     *            The ssn to set.
     */
    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return Returns the specialityCode.
     */
    public String getSpecialityCode()
    {
        return specialityCode;
    }

    /**
     * @param specialityCode
     *            The specialityCode to set.
     */
    public void setSpecialityCode(String specialityCode)
    {
        this.specialityCode = specialityCode;
    }

    /**
     * @return Returns the carrierID.
     */
    public String getCarrierID()
    {
        return carrierID;
    }

    /**
     * @param carrierID
     *            The carrierID to set.
     */
    public void setCarrierID(String carrierID)
    {
        this.carrierID = carrierID;
    }

    /**
     * @return Returns the carrierName.
     */
    public String getCarrierName()
    {
        return carrierName;
    }

    /**
     * @param carrierName
     *            The carrierName to set.
     */
    public void setCarrierName(String carrierName)
    {
        logger.debug("carrierName");
        this.carrierName = carrierName;
    }

    /**
     * @return Returns the contact.
     */
    public String getContact()
    {
        return contact;
    }

    /**
     * @param contact
     *            The contact to set.
     */
    public void setContact(String contact)
    {
        this.contact = contact;
    }
    
	/**
	 * @return Returns the currAltID.
	 */
	public String getCurrAltID() {
		return currAltID;
	}
	/**
	 * @param currAltID The currAltID to set.
	 */
	public void setCurrAltID(String currAltID) {
		this.currAltID = currAltID;
	}
	/**
	 * @return Returns the entityId.
	 */
	public String getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
    /**
     * @return Returns the providerTypeStr.
     */
    public String getProviderTypeStr() {
        return providerTypeStr;
    }
    /**
     * @param providerTypeStr The providerTypeStr to set.
     */
    public void setProviderTypeStr(String providerTypeStr) {
        this.providerTypeStr = providerTypeStr;
    }
    
    //CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
    private String tplPolicyHolderId;

	private String policyHolderName;

	private String tplPolicyGroupId;
	/**
	 * @return Returns the policyHolderName.
	 */
	public String getPolicyHolderName() {
		return policyHolderName;
	}
	/**
	 * @param policyHolderName The policyHolderName to set.
	 */
	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}
	/**
	 * @return Returns the tplPolicyGroupId.
	 */
	public String getTplPolicyGroupId() {
		return tplPolicyGroupId;
	}
	/**
	 * @param tplPolicyGroupId The tplPolicyGroupId to set.
	 */
	public void setTplPolicyGroupId(String tplPolicyGroupId) {
		this.tplPolicyGroupId = tplPolicyGroupId;
	}
	/**
	 * @return Returns the tplPolicyHolderId.
	 */
	public String getTplPolicyHolderId() {
		return tplPolicyHolderId;
	}
	/**
	 * @param tplPolicyHolderId The tplPolicyHolderId to set.
	 */
	public void setTplPolicyHolderId(String tplPolicyHolderId) {
		this.tplPolicyHolderId = tplPolicyHolderId;
	}
	//EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
	/**
	 * @return Returns the statusStr.
	 */
	public String getStatusStr() {
		return statusStr;
	}
	/**
	 * @param statusStr The statusStr to set.
	 */
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
}
