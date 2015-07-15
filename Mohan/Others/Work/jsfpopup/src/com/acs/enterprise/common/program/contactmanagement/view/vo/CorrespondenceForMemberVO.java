/*
 * Created on Dec 10, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * Holds the details for correspondence for Member.
 */
public class CorrespondenceForMemberVO
        extends CorrespondenceForVO
{

    /**
     * Constructor for CorrespondenceForMemberVO
     */
    public CorrespondenceForMemberVO()
    {
        super();
        logger.debug("calling CorrespondenceForMemberVO Constructor");
    }

    /** Enterprise Logger for Logging */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CorrespondenceForMemberVO.class);
    
    /**
     * Holds the information about member's SSN.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String socialSecurityNumber;

    /**
     * Holds the information about category Of eligibility for member.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String categoryOfEligibility;

    /**
     * Holds the information about District Office.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String districtOffice;
    
    /**
     * Holds the information about District Office Description.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String districtOfficedesc;

	/**
	 * @return Returns the districtOfficedesc.
	 */
	public String getDistrictOfficedesc() {
		return districtOfficedesc;
	}
	/**
	 * @param districtOfficedesc The districtOfficedesc to set.
	 */
	public void setDistrictOfficedesc(String districtOfficedesc) {
		this.districtOfficedesc = districtOfficedesc;
	}
    /**
     * Holds the information about anb
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    //commented for unused variables
	//private String anb;

    /**
     * Holds the information about dob
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String dob;

    /**
     * Holds the information about previousName
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String previousName;

    /**
     * Holds the information about resAddress
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String resAddress;

    /**
     * Holds the information about mailAddress
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String mailAddress;

    /**
     * Holds the information aboutemail
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String email;

    /**
     * Holds the information about contact.
     */
    private String contact;

    /**
     * Holds the information about idType.
     */
    private String idType;
    /**
     * Holds the information about idTypeDesc.
     */
    private String idTypeDesc;
    
    /**
     * Holds the primaryLanguage.
     */
    private String primaryLanguage;
    

    /**
       * Holds the information about entityID 
        * Created to hold the value for entitySysId or CurrAltID depending on the type 
        * of EntityType .
        */
    private String entityId;

    /**
     * @return Returns the socialSecurityNumber.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getSocialSecurityNumber()
    {
        return socialSecurityNumber;
    }

    /**
     * @param thesocialSecurityNumber
     *            The socialSecurityNumber to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setSocialSecurityNumber(String thesocialSecurityNumber)
    {
        socialSecurityNumber = thesocialSecurityNumber;
    }

    /**
     * @return Returns the categoryOfEligibility.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCategoryOfEligibility()
    {
        return categoryOfEligibility;
    }

    /**
     * @param thecategoryOfEligibility
     *            The categoryOfEligibility to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCategoryOfEligibility(String thecategoryOfEligibility)
    {
        categoryOfEligibility = thecategoryOfEligibility;
    }

    /**
     * @return Returns the districtOffice.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getDistrictOffice()
    {
        return districtOffice;
    }

    /**
     * @param thedistrictOffice
     *            The districtOffice to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setDistrictOffice(String thedistrictOffice)
    {
        districtOffice = thedistrictOffice;
    }

    /**
     * @return Returns the anb.
     */
    /*public String getAnb()
    {
        return anb;
    }

    *//**
     * @param anb
     *            The anb to set.
     *//*
    public void setAnb(String anb)
    {
        this.anb = anb;
    }
*/
    /**
     * @return Returns the dob.
     */
    public String getDob()
    {
        return dob;
    }

    /**
     * @param dob
     *            The dob to set.
     */
    public void setDob(String dob)
    {
        this.dob = dob;
    }

    /**
     * @return Returns the previousName.
     */
    public String getPreviousName()
    {
        return previousName;
    }

    /**
     * @param previousName
     *            The previousName to set.
     */
    public void setPreviousName(String previousName)
    {
        this.previousName = previousName;
    }

    /**
     * @return Returns the mailAddress.
     */
    public String getMailAddress()
    {
        return mailAddress;
    }

    /**
     * @param mailAddress
     *            The mailAddress to set.
     */
    public void setMailAddress(String mailAddress)
    {
        this.mailAddress = mailAddress;
    }

    /**
     * @return Returns the resAddress.
     */
    public String getResAddress()
    {
        return resAddress;
    }

    /**
     * @param resAddress
     *            The resAddress to set.
     */
    public void setResAddress(String resAddress)
    {
        this.resAddress = resAddress;
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
     * This method is used to get the contact.
     * 
     * @return String : Returns the contact.
     */
    public String getContact()
    {
        return contact;
    }

    /**
     * This method is used to set the contact.
     * 
     * @param contact :
     *            The contact to set.
     */
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    /**
     * This method overrides the clone method of the Object Class to clone the
     * CorrespondenceForMemberVO.
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
        CorrespondenceForMemberVO correspondenceForMemberVO = (CorrespondenceForMemberVO) super
                .clone();

        return correspondenceForMemberVO;
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
        logger.debug("idtype");
        this.idType = idType;
    }

	/**
	 * @return Returns the primaryLanguage.
	 */
	public String getPrimaryLanguage() {
		return primaryLanguage;
	}
	/**
	 * @param primaryLanguage The primaryLanguage to set.
	 */
	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
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
	 * @return Returns the idTypeDesc.
	 */
	public String getIdTypeDesc() {
		return idTypeDesc;
	}
	/**
	 * @param idTypeDesc The idTypeDesc to set.
	 */
	public void setIdTypeDesc(String idTypeDesc) {
		this.idTypeDesc = idTypeDesc;
	}
}
