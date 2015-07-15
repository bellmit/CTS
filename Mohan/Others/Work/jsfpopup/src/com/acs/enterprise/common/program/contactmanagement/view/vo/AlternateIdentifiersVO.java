/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * This class will be the Alternate Identifiers Persistent Data Object that are
 * used in the Log Case.
 * 
 * @author Wipro
 */
public class AlternateIdentifiersVO
        extends EnterpriseBaseVO
{
    /** This will holds the alternate Identifiers Type */
    private String alternateIDTypeCode;
    
    /** This will holds the alternate Identifiers Type */
    private String alternateIDTypeCodestr;
    
    /** This will holds the alternate Identifiers Type Desc*/
    private String alternateIDTypeCodeDesc;

    /** This will holds the Alternate ID */
    private String alternateID;

    /** This will holds the LOB */
    private String lineOfBusiness;
    //Added property for defect ESPRD00882669
    private String lineOfBusinessDesc;
    
    /**
	 * @return the lineOfBusinessDesc
	 */
	public String getLineOfBusinessDesc() {
		return lineOfBusinessDesc;
	}

	/**
	 * @param lineOfBusinessDesc the lineOfBusinessDesc to set
	 */
	public void setLineOfBusinessDesc(String lineOfBusinessDesc) {
		this.lineOfBusinessDesc = lineOfBusinessDesc;
	}
	/** used for dummy */
    private String dummy;
    
    public AlternateIdentifiersVO()
    {
        super();
        dummy = "dummy";
    }

    /**
     * @return Returns the alternateID.
     */
    public String getAlternateID()
    {
        return alternateID;
    }
    /**
     * @param alternateID The alternateID to set.
     */
    public void setAlternateID(String alternateID)
    {
        this.alternateID = alternateID;
    }
    /**
     * @return Returns the alternateIDTypeCode.
     */
    public String getAlternateIDTypeCode()
    {
        return alternateIDTypeCode;
    }
    /**
     * @param alternateIDTypeCode The alternateIDTypeCode to set.
     */
    public void setAlternateIDTypeCode(String alternateIDTypeCode)
    {
        this.alternateIDTypeCode = alternateIDTypeCode;
    }
    /**
     * @return Returns the lineOfBusiness.
     */
    public String getLineOfBusiness()
    {
        return lineOfBusiness;
    }
    /**
     * @param lineOfBusiness The lineOfBusiness to set.
     */
    public void setLineOfBusiness(String lineOfBusiness)
    {
        this.lineOfBusiness = lineOfBusiness;
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
     * @return Returns the alternateIDTypeCodeDesc.
     */
    public String getAlternateIDTypeCodeDesc()
    {
        return alternateIDTypeCodeDesc;
    }
    /**
     * @param alternateIDTypeCodeDesc The alternateIDTypeCodeDesc to set.
     */
    public void setAlternateIDTypeCodeDesc(String alternateIDTypeCodeDesc)
    {
        this.alternateIDTypeCodeDesc = alternateIDTypeCodeDesc;
    }
	/**
	 * @return Returns the alternateIDTypeCodestr.
	 */
	public String getAlternateIDTypeCodestr() {
		return alternateIDTypeCodestr;
	}
	/**
	 * @param alternateIDTypeCodestr The alternateIDTypeCodestr to set.
	 */
	public void setAlternateIDTypeCodestr(String alternateIDTypeCodestr) {
		this.alternateIDTypeCodestr = alternateIDTypeCodestr;
	}
}
