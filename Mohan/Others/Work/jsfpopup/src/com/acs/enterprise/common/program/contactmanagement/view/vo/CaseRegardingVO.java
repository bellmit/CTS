/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;

/**
 * This class will be the Case Regarding Details Persistent Data Object that are
 * used in the Log Case.
 * 
 * @author Wipro
 */
public class CaseRegardingVO
        extends EnterpriseBaseVO
{
   
    /** holds the Case Record number */
    private String caseRecordNumber;

    /** holds the CaseRegardingMember VO object */
    private CaseRegardingMemberVO caseRegardingMemberVO = new CaseRegardingMemberVO();

    /** holds the CaseRegardingProvider VO object */
    private CaseRegardingProviderVO caseRegardingProviderVO = new CaseRegardingProviderVO();

    /** holds the CaseRegardingTPL VO object */
    private CaseRegardingTPL caseRegardingTPLVO = new CaseRegardingTPL();
    
    /** used for dummy */
    private String dummy = "dummy";
    
    
    /**
     * @return Returns the caseRecordNumber.
     */
    public String getCaseRecordNumber()
    {
        return caseRecordNumber;
    }

    /**
     * @param caseRecordNumber
     *            The caseRecordNumber to set.
     */
    public void setCaseRecordNumber(String caseRecordNumber)
    {
        this.caseRecordNumber = caseRecordNumber;
       
    }

    
    /**
     * @return Returns the caseRegardingMemberVO.
     */
    public CaseRegardingMemberVO getCaseRegardingMemberVO()
    {
        return caseRegardingMemberVO;
    }
    /**
     * @param caseRegardingMemberVO The caseRegardingMemberVO to set.
     */
    public void setCaseRegardingMemberVO(
            CaseRegardingMemberVO caseRegardingMemberVO)
    {
        this.caseRegardingMemberVO = caseRegardingMemberVO;
    }
    /**
     * @return Returns the caseRegardingProviderVO.
     */
    public CaseRegardingProviderVO getCaseRegardingProviderVO()
    {
        return caseRegardingProviderVO;
    }
    /**
     * @param caseRegardingProviderVO The caseRegardingProviderVO to set.
     */
    public void setCaseRegardingProviderVO(
            CaseRegardingProviderVO caseRegardingProviderVO)
    {
        this.caseRegardingProviderVO = caseRegardingProviderVO;
    }
    /**
     * @return Returns the caseRegardingTPLVO.
     */
    public CaseRegardingTPL getCaseRegardingTPLVO()
    {
        return caseRegardingTPLVO;
    }
    /**
     * @param caseRegardingTPLVO The caseRegardingTPLVO to set.
     */
    public void setCaseRegardingTPLVO(CaseRegardingTPL caseRegardingTPLVO)
    {
        this.caseRegardingTPLVO = caseRegardingTPLVO;
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
    
    
   /** CR_ESPRD00436016 */
        /** holds the CaseRegardingTradingPartner VO object */
    private CaseRegardingTradingPartnerVO caseRegardingTradingPartnerVO = new CaseRegardingTradingPartnerVO();
    
    
	/**
	 * @return Returns the caseRegardingTradingPartnerVO.
	 */
	public CaseRegardingTradingPartnerVO getCaseRegardingTradingPartnerVO() {
		return caseRegardingTradingPartnerVO;
	}
	/**
	 * @param caseRegardingTradingPartnerVO The caseRegardingTradingPartnerVO to set.
	 */
	public void setCaseRegardingTradingPartnerVO(
			CaseRegardingTradingPartnerVO caseRegardingTradingPartnerVO) {
		this.caseRegardingTradingPartnerVO = caseRegardingTradingPartnerVO;
	}
}
