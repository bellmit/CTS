/*
 * Created on Dec 10, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * Holds the information about Correspondence for provider.
 */
public class CorrespondenceForProviderVO
        extends CorrespondenceForVO
{

    /**
     * Constructor for CorrespondenceForProviderVO
     */
    public CorrespondenceForProviderVO()
    {
        super();
        logger.debug("calling CorrespondenceForProviderVO Constructor");
    }

    /** Enterprise Logger for Logging */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CorrespondenceForProviderVO.class);
    
    /**
     * Holds the information about provider's payerId
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String payerID;

    /**
     * Holds the information about provider's specialty
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String specialty;
   
    private String specialtyStr;
    
    private String statsuSstr;

    /**
     * Holds the information about provider's type e.g Surgen, Physican etc.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String providerType;
    
    private String providerTypeStr;

    /**
     * Holds the information about provider's contact
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String contact;

    /**
     * Holds the information about idType.
     */
    private String idType;
    
    private String idTypeStr;

    /**
       * Holds the information about entityID 
       * Created to hold the value for entitySysId or CurrAltID depending on the type 
       * of EntityType .
       */
    private String entityId;

    /**
     * @return Returns the payerID.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getPayerID()
    {
        return payerID;
    }

    /**
     * @param thepayerID
     *            The payerID to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setPayerID(String thepayerID)
    {
        payerID = thepayerID;
    }

    /**
     * @return Returns the specialty.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getSpecialty()
    {
        return specialty;
    }

    /**
     * @param thespecialty
     *            The specialty to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setSpecialty(String thespecialty)
    {
        specialty = thespecialty;
    }

    /**
     * @return Returns the providerType.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getProviderType()
    {
        return providerType;
    }

    /**
     * @param theproviderType
     *            The providerType to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setProviderType(String theproviderType)
    {
        providerType = theproviderType;
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
     * This method overrides the clone method of the Object Class to clone the
     * CorrespondenceForProviderVO.
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
        CorrespondenceForProviderVO correspondenceForProviderVO = (CorrespondenceForProviderVO) super
                .clone();

        return correspondenceForProviderVO;
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
        logger.debug("idType");
        this.idType = idType;
    }

    /**
     * @return Returns the idTypeStr.
     */
    public String getIdTypeStr() {
        return idTypeStr;
    }
    /**
     * @param idTypeStr The idTypeStr to set.
     */
    public void setIdTypeStr(String idTypeStr) {
        this.idTypeStr = idTypeStr;
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
    /**
     * @return Returns the specialtyStr.
     */
    public String getSpecialtyStr() {
        return specialtyStr;
    }
    /**
     * @param specialtyStr The specialtyStr to set.
     */
    public void setSpecialtyStr(String specialtyStr) {
        this.specialtyStr = specialtyStr;
    }
    /**
     * @return Returns the statsuSstr.
     */
    public String getStatsuSstr() {
        return statsuSstr;
    }
    /**
     * @param statsuSstr The statsuSstr to set.
     */
    public void setStatsuSstr(String statsuSstr) {
        this.statsuSstr = statsuSstr;
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
}
