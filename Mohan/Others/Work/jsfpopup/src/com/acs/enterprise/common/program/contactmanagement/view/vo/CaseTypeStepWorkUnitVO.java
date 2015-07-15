/*
 * Created on Jan 24, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.internalmessage.common.vo.EnterpriseUserVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author durpaam
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CaseTypeStepWorkUnitVO extends EnterpriseBaseVO 
{
	/**
     * Generating object of EnterpriseLogger.
     */
      
	    private static final EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CaseTypeStepWorkUnitVO.class);
     
      
	/**
     * Holds Work Unit Key, a primary key
     */
    private Long workUnitSK;

    /**
     * Holds Work Unit Type Code
     */
    private String workUnitTypeCode;
    
    /** Holds collection of messages objects */
    private EnterpriseUserVO enterpriseUserVO;

    /**
     * @param workUnitSK The workUnitSK to set.
     */
    public void setWorkUnitSK(Long workUnitSK)
    {
        this.workUnitSK = workUnitSK;
        logger.debug("InsideWorkunitsk");
    }

    /**
     * @return Returns the workUnitSK.
     */
    public Long getWorkUnitSK()
    {
        return workUnitSK;
    }

    /**
     * @param workUnitTypeCode The workUnitTypeCode to set.
     */
    public void setWorkUnitTypeCode(String workUnitTypeCode)
    {
        this.workUnitTypeCode = workUnitTypeCode;
    }

    /**
     * @return Returns the workUnitTypeCode.
     */
    public String getWorkUnitTypeCode()
    {
        return workUnitTypeCode;
    }

    /**
     * @param enterpriseUserVO The enterpriseUserVO to set.
     */
    public void setEnterpriseUserVO(EnterpriseUserVO enterpriseUserVO)
    {
        this.enterpriseUserVO = enterpriseUserVO;
    }

    /**
     * @return Returns the enterpriseUserVO.
     */
    public EnterpriseUserVO getEnterpriseUserVO()
    {
        return enterpriseUserVO;
    }
}
