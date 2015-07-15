/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * This VO holds Address information.
 * 
 * @author wipro
 */
public class AddressVO
        extends EnterpriseBaseVO
{

    /** Holds the commonEntitySk(PK). */
    private Long repCommonEntitySK;

    /** Holds the audit user ID. */
    private String auditUserID;

    /** Holds the audit Timestamp. */
    private Date auditTimeStamp;

    /** Holds the user id of the user who added the record. */
    private String addedAuditUserID;

    /** Holds the audit Timestamp when record was first added */
    private Date addedAuditTimeStamp;

    /** Holds the audit user ID. */
    private String addressAuditUserID;

    /** Holds the audit Timestamp. */
    private Date addressAuditTimeStamp;

    /** Holds the user id of the user who added the record. */
    private String addressAddedAuditUserID;

    /** Holds the audit Timestamp when record was first added */
    private Date addressAddedAuditTimeStamp;

    /** Holds the address usage type code(PK). */
    private String addressUsageTypeCode;

    /** Holds the address usage type code(PK) short desc. */
    private String addressUsageTypeCodeShortDesc;

    /** Holds the addressSK(PK). */
    private Long addressSK;

    /** Holds the beginDate. */
    private Date beginDate;

    /** Holds the endDate . */
    private Date endDate;

    /**
     * Holds the beginDate. It is used to bind with the Begin Date field of the
     * jsp page.
     */
    private String strBeginDate;

    /**
     * Holds the EndDate. It is used to bind with the End Date field of the jsp
     * page.
     */
    private String strEndDate;

    /** Hold Address Usage Significance Code */
    private String addressUsageSigCode;

    /** Hold reasonCode */
    private String reasonCode;

    /** Hold statusCode */
    private String statusCode;

    /** Holds the address line1. */
    private String addressLine1;

    /** Holds the uspsAddressLine1. */
    private String uspsAddressLine1;

    /** Holds the uspsAddressLine2. */
    private String uspsAddressLine2;

    /** Holds the uspsAddressVerifyCode. */
    private String uspsAddressVerifyCode;

    /** Holds the address line2. */
    private String addressLine2;

    /** Holds the address line3. */
    private String addressLine3;

    /** Holds the address line4. */
    private String addressLine4;

    /** Holds the city name. */
    private String cityName;

    /** Holds the state code. */
    private String stateCode;

    /** Holds the zip code5. */
    private String zipCode5;

    /** Holds the zip code4. */
    private String zipCode4;

    /** Holds the county code. */
    private String countyCode;

    /** Holds the country code. */
    private String countryCode;

    /** Holds the Town code. */
    private String townCode;

    /** Holds the returnMailDate. */
    private Date returnMailDate;

    /** Holds the returnMailDate String value. */
    private String strReturnMailDate;

    /** Holds the county code and Description. */
    private String countyCodeDesc;

    /** Holds the Town code and Description. */
    private String townCodeDesc;

    /** Holds the latitude number. */
    private Double latitudeNumber;

    /** Holds the longitude number. */
    private Double longitudeNumber;

    /** used for dummy */
    private String dummy;

    /**
     * Default Constructor.
     */
    public AddressVO()
    {
        super();
        dummy = "dummy";
    }

    /**
     * @return Returns the addedAuditTimeStamp.
     */
    public Date getAddedAuditTimeStamp()
    {
        return addedAuditTimeStamp;
    }

    /**
     * @param addedAuditTimeStamp
     *            The addedAuditTimeStamp to set.
     */
    public void setAddedAuditTimeStamp(Date addedAuditTimeStamp)
    {
        this.addedAuditTimeStamp = addedAuditTimeStamp;
        //logger.debug("setAddedAuditTimeStamp().");
    }

    /**
     * @return Returns the addedAuditUserID.
     */
    public String getAddedAuditUserID()
    {
        return addedAuditUserID;
    }

    /**
     * @param addedAuditUserID
     *            The addedAuditUserID to set.
     */
    public void setAddedAuditUserID(String addedAuditUserID)
    {
        this.addedAuditUserID = addedAuditUserID;
    }

    /**
     * @return Returns the addressAddedAuditTimeStamp.
     */
    public Date getAddressAddedAuditTimeStamp()
    {
        return addressAddedAuditTimeStamp;
    }

    /**
     * @param addressAddedAuditTimeStamp
     *            The addressAddedAuditTimeStamp to set.
     */
    public void setAddressAddedAuditTimeStamp(Date addressAddedAuditTimeStamp)
    {
        this.addressAddedAuditTimeStamp = addressAddedAuditTimeStamp;
    }

    /**
     * @return Returns the addressAddedAuditUserID.
     */
    public String getAddressAddedAuditUserID()
    {
        return addressAddedAuditUserID;
    }

    /**
     * @param addressAddedAuditUserID
     *            The addressAddedAuditUserID to set.
     */
    public void setAddressAddedAuditUserID(String addressAddedAuditUserID)
    {
        this.addressAddedAuditUserID = addressAddedAuditUserID;
    }

    /**
     * @return Returns the addressAuditTimeStamp.
     */
    public Date getAddressAuditTimeStamp()
    {
        return addressAuditTimeStamp;
    }

    /**
     * @param addressAuditTimeStamp
     *            The addressAuditTimeStamp to set.
     */
    public void setAddressAuditTimeStamp(Date addressAuditTimeStamp)
    {
        this.addressAuditTimeStamp = addressAuditTimeStamp;
    }

    /**
     * @return Returns the addressAuditUserID.
     */
    public String getAddressAuditUserID()
    {
        return addressAuditUserID;
    }

    /**
     * @param addressAuditUserID
     *            The addressAuditUserID to set.
     */
    public void setAddressAuditUserID(String addressAuditUserID)
    {
        this.addressAuditUserID = addressAuditUserID;
    }

    /**
     * @return Returns the addressLine1.
     */
    public String getAddressLine1()
    {
        return addressLine1;
    }

    /**
     * @param addressLine1
     *            The addressLine1 to set.
     */
    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return Returns the addressLine2.
     */
    public String getAddressLine2()
    {
        return addressLine2;
    }

    /**
     * @param addressLine2
     *            The addressLine2 to set.
     */
    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return Returns the addressLine3.
     */
    public String getAddressLine3()
    {
        return addressLine3;
    }

    /**
     * @param addressLine3
     *            The addressLine3 to set.
     */
    public void setAddressLine3(String addressLine3)
    {
        this.addressLine3 = addressLine3;
    }

    /**
     * @return Returns the addressLine4.
     */
    public String getAddressLine4()
    {
        return addressLine4;
    }

    /**
     * @param addressLine4
     *            The addressLine4 to set.
     */
    public void setAddressLine4(String addressLine4)
    {
        this.addressLine4 = addressLine4;
    }

    /**
     * @return Returns the addressSK.
     */
    public Long getAddressSK()
    {
        return addressSK;
    }

    /**
     * @param addressSK
     *            The addressSK to set.
     */
    public void setAddressSK(Long addressSK)
    {
        this.addressSK = addressSK;
    }

    /**
     * @return Returns the addressUsageSigCode.
     */
    public String getAddressUsageSigCode()
    {
        return addressUsageSigCode;
    }

    /**
     * @param addressUsageSigCode
     *            The addressUsageSigCode to set.
     */
    public void setAddressUsageSigCode(String addressUsageSigCode)
    {
        this.addressUsageSigCode = addressUsageSigCode;
    }

    /**
     * @return Returns the addressUsageTypeCode.
     */
    public String getAddressUsageTypeCode()
    {
        return addressUsageTypeCode;
    }

    /**
     * @param addressUsageTypeCode
     *            The addressUsageTypeCode to set.
     */
    public void setAddressUsageTypeCode(String addressUsageTypeCode)
    {
        this.addressUsageTypeCode = addressUsageTypeCode;
    }

    /**
     * @return Returns the addressUsageTypeCodeShortDesc.
     */
    public String getAddressUsageTypeCodeShortDesc()
    {
        return addressUsageTypeCodeShortDesc;
    }

    /**
     * @param addressUsageTypeCodeShortDesc
     *            The addressUsageTypeCodeShortDesc to set.
     */
    public void setAddressUsageTypeCodeShortDesc(
            String addressUsageTypeCodeShortDesc)
    {
        this.addressUsageTypeCodeShortDesc = addressUsageTypeCodeShortDesc;
    }

    /**
     * @return Returns the auditTimeStamp.
     */
    public Date getAuditTimeStamp()
    {
        return auditTimeStamp;
    }

    /**
     * @param auditTimeStamp
     *            The auditTimeStamp to set.
     */
    public void setAuditTimeStamp(Date auditTimeStamp)
    {
        this.auditTimeStamp = auditTimeStamp;
    }

    /**
     * @return Returns the auditUserID.
     */
    public String getAuditUserID()
    {
        return auditUserID;
    }

    /**
     * @param auditUserID
     *            The auditUserID to set.
     */
    public void setAuditUserID(String auditUserID)
    {
        this.auditUserID = auditUserID;
    }

    /**
     * @return Returns the beginDate.
     */
    public Date getBeginDate()
    {
        return beginDate;
    }

    /**
     * @param beginDate
     *            The beginDate to set.
     */
    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    /**
     * @return Returns the cityName.
     */
    public String getCityName()
    {
        return cityName;
    }

    /**
     * @param cityName
     *            The cityName to set.
     */
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    /**
     * @return Returns the countryCode.
     */
    public String getCountryCode()
    {
        return countryCode;
    }

    /**
     * @param countryCode
     *            The countryCode to set.
     */
    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    /**
     * @return Returns the countyCode.
     */
    public String getCountyCode()
    {
        return countyCode;
    }

    /**
     * @param countyCode
     *            The countyCode to set.
     */
    public void setCountyCode(String countyCode)
    {
        this.countyCode = countyCode;
    }

    /**
     * @return Returns the countyCodeDesc.
     */
    public String getCountyCodeDesc()
    {
        return countyCodeDesc;
    }

    /**
     * @param countyCodeDesc
     *            The countyCodeDesc to set.
     */
    public void setCountyCodeDesc(String countyCodeDesc)
    {
        this.countyCodeDesc = countyCodeDesc;
    }

    /**
     * @return Returns the endDate.
     */
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     * @param endDate
     *            The endDate to set.
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    /**
     * @return Returns the latitudeNumber.
     */
    public Double getLatitudeNumber()
    {
        return latitudeNumber;
    }

    /**
     * @param latitudeNumber
     *            The latitudeNumber to set.
     */
    public void setLatitudeNumber(Double latitudeNumber)
    {
        this.latitudeNumber = latitudeNumber;
    }

    /**
     * @return Returns the longitudeNumber.
     */
    public Double getLongitudeNumber()
    {
        return longitudeNumber;
    }

    /**
     * @param longitudeNumber
     *            The longitudeNumber to set.
     */
    public void setLongitudeNumber(Double longitudeNumber)
    {
        this.longitudeNumber = longitudeNumber;
    }

    /**
     * @return Returns the reasonCode.
     */
    public String getReasonCode()
    {
        return reasonCode;
    }

    /**
     * @param reasonCode
     *            The reasonCode to set.
     */
    public void setReasonCode(String reasonCode)
    {
        this.reasonCode = reasonCode;
    }

    /**
     * @return Returns the repCommonEntitySK.
     */
    public Long getRepCommonEntitySK()
    {
        return repCommonEntitySK;
    }

    /**
     * @param repCommonEntitySK
     *            The repCommonEntitySK to set.
     */
    public void setRepCommonEntitySK(Long repCommonEntitySK)
    {
        this.repCommonEntitySK = repCommonEntitySK;
    }

    /**
     * @return Returns the returnMailDate.
     */
    public Date getReturnMailDate()
    {
        return returnMailDate;
    }

    /**
     * @param returnMailDate
     *            The returnMailDate to set.
     */
    public void setReturnMailDate(Date returnMailDate)
    {
        this.returnMailDate = returnMailDate;
    }

    /**
     * @return Returns the stateCode.
     */
    public String getStateCode()
    {
        return stateCode;
    }

    /**
     * @param stateCode
     *            The stateCode to set.
     */
    public void setStateCode(String stateCode)
    {
        this.stateCode = stateCode;
    }

    /**
     * @return Returns the statusCode.
     */
    public String getStatusCode()
    {
        return statusCode;
    }

    /**
     * @param statusCode
     *            The statusCode to set.
     */
    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    /**
     * @return Returns the strBeginDate.
     */
    public String getStrBeginDate()
    {
        return strBeginDate;
    }

    /**
     * @param strBeginDate
     *            The strBeginDate to set.
     */
    public void setStrBeginDate(String strBeginDate)
    {
        this.strBeginDate = strBeginDate;
    }

    /**
     * @return Returns the strEndDate.
     */
    public String getStrEndDate()
    {
        return strEndDate;
    }

    /**
     * @param strEndDate
     *            The strEndDate to set.
     */
    public void setStrEndDate(String strEndDate)
    {
        this.strEndDate = strEndDate;
    }

    /**
     * @return Returns the strReturnMailDate.
     */
    public String getStrReturnMailDate()
    {
        return strReturnMailDate;
    }

    /**
     * @param strReturnMailDate
     *            The strReturnMailDate to set.
     */
    public void setStrReturnMailDate(String strReturnMailDate)
    {
        this.strReturnMailDate = strReturnMailDate;
    }

    /**
     * @return Returns the townCode.
     */
    public String getTownCode()
    {
        return townCode;
    }

    /**
     * @param townCode
     *            The townCode to set.
     */
    public void setTownCode(String townCode)
    {
        this.townCode = townCode;
    }

    /**
     * @return Returns the townCodeDesc.
     */
    public String getTownCodeDesc()
    {
        return townCodeDesc;
    }

    /**
     * @param townCodeDesc
     *            The townCodeDesc to set.
     */
    public void setTownCodeDesc(String townCodeDesc)
    {
        this.townCodeDesc = townCodeDesc;
    }

    /**
     * @return Returns the uspsAddressLine1.
     */
    public String getUspsAddressLine1()
    {
        return uspsAddressLine1;
    }

    /**
     * @param uspsAddressLine1
     *            The uspsAddressLine1 to set.
     */
    public void setUspsAddressLine1(String uspsAddressLine1)
    {
        this.uspsAddressLine1 = uspsAddressLine1;
    }

    /**
     * @return Returns the uspsAddressLine2.
     */
    public String getUspsAddressLine2()
    {
        return uspsAddressLine2;
    }

    /**
     * @param uspsAddressLine2
     *            The uspsAddressLine2 to set.
     */
    public void setUspsAddressLine2(String uspsAddressLine2)
    {
        this.uspsAddressLine2 = uspsAddressLine2;
    }

    /**
     * @return Returns the uspsAddressVerifyCode.
     */
    public String getUspsAddressVerifyCode()
    {
        return uspsAddressVerifyCode;
    }

    /**
     * @param uspsAddressVerifyCode
     *            The uspsAddressVerifyCode to set.
     */
    public void setUspsAddressVerifyCode(String uspsAddressVerifyCode)
    {
        this.uspsAddressVerifyCode = uspsAddressVerifyCode;
    }

    /**
     * @return Returns the zipCode4.
     */
    public String getZipCode4()
    {
        return zipCode4;
    }

    /**
     * @param zipCode4
     *            The zipCode4 to set.
     */
    public void setZipCode4(String zipCode4)
    {
        this.zipCode4 = zipCode4;
    }

    /**
     * @return Returns the zipCode5.
     */
    public String getZipCode5()
    {
        return zipCode5;
    }

    /**
     * @param zipCode5
     *            The zipCode5 to set.
     */
    public void setZipCode5(String zipCode5)
    {
        this.zipCode5 = zipCode5;
    }

    /**
     * @return Returns the dummy.
     */
    public String getDummy()
    {
        return dummy;
    }

    /**
     * @param dummy
     *            The dummy to set.
     */
    public void setDummy(String dummy)
    {
        this.dummy = dummy;
    }
}
