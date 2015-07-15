/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

/**
 * This class will be the Case Regarding for Member Details Persistent Data
 * Object that are used in the Log Case.
 * 
 * @author Wipro
 */
public class CaseRegardingMemberVO
        extends CaseRegardingTPL
{

    /** holds the entity Type */
    private String entityType;

    /** holds the entity Type desc*/
    private String entityTypeDesc;

    /** holds the Member category of Eligibility */
    private String categoryOfEligibility;

    /** holds the Member category of Eligibility Desc*/
    private String coeCodeDesc;

    /** holds the status */
    private String status;

    /** holds the email address */
    private String email;

    /** holds the name */
    private String name;

    /** holds the district Office */
    private String districtOffice;

    /** holds the district Office Desc*/
    private String districtOfficeDesc;

    /** holds the DOB */
    private String dateofBirth;

    /** holds the previous name */
    private String previousName;

    /** holds the Member ID */
    private String entityId;

    /** holds the Member ID Type */
    private String memberIDType;

    /** holds the Member ID Type Desc*/
    private String memberIDTypeDesc;

    /** holds the member SSN */
    private String memberSSN;

    /** holds the residential Address */
    private String residentialAddress;

    /** holds the Mailing address */
    private String mailingAddress;

    /** holds the commonentitySK */
    private String commonEntitySK;

    /** used for dummy */
    private String dummy = "dummy";

   

    /**
     * @return Returns the categoryOfEligibility.
     */
    public String getCategoryOfEligibility()
    {
        return categoryOfEligibility;
    }

    /**
     * @param categoryOfEligibility
     *            The categoryOfEligibility to set.
     */
    public void setCategoryOfEligibility(String categoryOfEligibility)
    {
        this.categoryOfEligibility = categoryOfEligibility;
       
    }

    /**
     * @return Returns the dateofBirth.
     */
    public String getDateofBirth()
    {
        return dateofBirth;
    }

    /**
     * @param dateofBirth
     *            The dateofBirth to set.
     */
    public void setDateofBirth(String dateofBirth)
    {
        this.dateofBirth = dateofBirth;
    }

    /**
     * @return Returns the districtOffice.
     */
    public String getDistrictOffice()
    {
        return districtOffice;
    }

    /**
     * @param districtOffice
     *            The districtOffice to set.
     */
    public void setDistrictOffice(String districtOffice)
    {
        this.districtOffice = districtOffice;
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
     * @return Returns the memberSSN.
     */
    public String getMemberSSN()
    {
        return memberSSN;
    }

    /**
     * @param memberSSN
     *            The memberSSN to set.
     */
    public void setMemberSSN(String memberSSN)
    {
        this.memberSSN = memberSSN;
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
     * @return Returns the memberIDType.
     */
    public String getMemberIDType()
    {
        return memberIDType;
    }

    /**
     * @param memberIDType
     *            The memberIDType to set.
     */
    public void setMemberIDType(String memberIDType)
    {
        this.memberIDType = memberIDType;
    }

    /**
     * @return Returns the entityType.
     */
    public String getEntityType()
    {
        return entityType;
    }

    /**
     * @param entityType The entityType to set.
     */
    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }

    /**
     * @return Returns the mailingAddress.
     */
    public String getMailingAddress()
    {
        return mailingAddress;
    }

    /**
     * @param mailingAddress The mailingAddress to set.
     */
    public void setMailingAddress(String mailingAddress)
    {
        this.mailingAddress = mailingAddress;
    }

    /**
     * @return Returns the residentialAddress.
     */
    public String getResidentialAddress()
    {
        return residentialAddress;
    }

    /**
     * @param residentialAddress The residentialAddress to set.
     */
    public void setResidentialAddress(String residentialAddress)
    {
        this.residentialAddress = residentialAddress;
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
     * @return Returns the coeCodeDesc.
     */
    public String getCoeCodeDesc()
    {
        return coeCodeDesc;
    }

    /**
     * @param coeCodeDesc The coeCodeDesc to set.
     */
    public void setCoeCodeDesc(String coeCodeDesc)
    {
        this.coeCodeDesc = coeCodeDesc;
    }

    /**
     * @return Returns the districtOfficeDesc.
     */
    public String getDistrictOfficeDesc()
    {
        return districtOfficeDesc;
    }

    /**
     * @param districtOfficeDesc The districtOfficeDesc to set.
     */
    public void setDistrictOfficeDesc(String districtOfficeDesc)
    {
        this.districtOfficeDesc = districtOfficeDesc;
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
     * @return Returns the memberIDTypeDesc.
     */
    public String getMemberIDTypeDesc()
    {
        return memberIDTypeDesc;
    }

    /**
     * @param memberIDTypeDesc The memberIDTypeDesc to set.
     */
    public void setMemberIDTypeDesc(String memberIDTypeDesc)
    {
        this.memberIDTypeDesc = memberIDTypeDesc;
    }
    /** holds the member ID */
    private String memberId;
    
	/**
	 * @return Returns the memberId.
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId The memberId to set.
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
