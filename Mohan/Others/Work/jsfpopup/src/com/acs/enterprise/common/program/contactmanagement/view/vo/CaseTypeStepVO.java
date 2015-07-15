/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CaseTypeDataBean;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class will be the VO for Maintain Case Type Step
 * 
 * @author Wipro
 */
public class CaseTypeStepVO extends AuditaleEnterpriseBaseVO
        //extends EnterpriseBaseVO //CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
{
	/**
     * Generating object of EnterpriseLogger.
     */
   
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(CaseTypeDataBean.class);
    
   
	 /**
	 * Holds stepSeqNum include
	 */	 
	 private Integer stepSeqNum = null; 
	 
	/**
     * Holds caseTypeStep include
     */
    private String caseTypeStepInclude = ContactManagementConstants.YES;

    /**
     * Flag for imgage include 
     */
    private boolean imgInclude = true;

    /**
     * @return Returns the caseTypeStepInclude.
     */
    public String getCaseTypeStepInclude()
    {
        return caseTypeStepInclude;
    }
    /**
     * @param caseTypeStepInclude The caseTypeStepInclude to set.
     */
    public void setCaseTypeStepInclude(String caseTypeStepInclude)
    {
        this.caseTypeStepInclude = caseTypeStepInclude;
    }
    /**
     * Holds the dfltSendAlertDaysBeforeOrAfter.
     */
    private String dfltSendAlertDaysBeforeOrAfter;

    /**
     * Holds the workunit vo.
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private CaseTypeStepWorkUnitVO workunitvo;

    /**
     * Holds description
     */
    private String description;
    /**
     * Holds description label
     */
    private String descValue;

    /**
     * Holds the availabe case step sk.
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private String availCmCaseStepSk;

    /**
     * @return Returns the availCmCaseStepSk.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public String getAvailCmCaseStepSk()
    {
    	
        return availCmCaseStepSk;
    }

    *//**
     * @param theavailCmCaseStepSk
     *            The availCmCaseStepSk to set.
     *//*
    public void setAvailCmCaseStepSk(String theavailCmCaseStepSk)
    {
        availCmCaseStepSk = theavailCmCaseStepSk;
        logger.debug("Inside casetypeStepVo");
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.
    
    /**
     * Holds the default route to Work unit.
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private WorkUnit dfltRouteToWorkUnit;

    /**
     * @return Returns the dfltRouteToWorkUnit.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public WorkUnit getDfltRouteToWorkUnit()
    {
        return dfltRouteToWorkUnit;
    }

    *//**
     * @param thedfltRouteToWorkUnit
     *            The dfltRouteToWorkUnit to set.
     *//*
    public void setDfltRouteToWorkUnit(WorkUnit thedfltRouteToWorkUnit)
    {
        dfltRouteToWorkUnit = thedfltRouteToWorkUnit;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.

    /**
     * Holds the step order num
     */
    private String stepOrderNum;
    /**
     * Holds StrporderNum desc
     */
    private String stepOrderNumDesc;

    /**
     * @return Returns the stepOrderNum.
     */
    public String getStepOrderNum()
    {
        return stepOrderNum;
    }

    /**
     * @param thestepOrderNum
     *            The stepOrderNum to set.
     */
    public void setStepOrderNum(String thestepOrderNum)
    {
        stepOrderNum = thestepOrderNum;
    }

    /**
     * Holds the days to Completion count.
     */
    private String dfltDaysToCmplCnt;

    /**
     * @return Returns the dfltDaysToCmplCnt.
     */
    public String getDfltDaysToCmplCnt()
    {
        return dfltDaysToCmplCnt;
    }

    /**
     * @param thedfltDaysToCmplCnt
     *            The dfltDaysToCmplCnt to set.
     */
    public void setDfltDaysToCmplCnt(String thedfltDaysToCmplCnt)
    {
        dfltDaysToCmplCnt = thedfltDaysToCmplCnt;
    }

    /**
     * Holds the completion based on Column name
     */
    private String dfltCmpltnBasedOnColName;
    /**
     * Holds the Completion Basedon colname Desc
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private String cmpltnBasedOnColNameDesc;
    /**
     * @return Returns the dfltCmpltnBasedOnColName.
     */
    public String getDfltCmpltnBasedOnColName()
    {
        return dfltCmpltnBasedOnColName;
    }

    /**
     * @param thedfltCmpltnBasedOnColName
     *            The dfltCmpltnBasedOnColName to set.
     */
    public void setDfltCmpltnBasedOnColName(String thedfltCmpltnBasedOnColName)
    {
        dfltCmpltnBasedOnColName = thedfltCmpltnBasedOnColName;
    }

  
    /**
     * Holds the nofify alert user id
     */
    private String dfltNotfyAlertUserId;
    /**
     * Holds the nofify alert user id
     */
    private String dfltNotfyAlertUserName = null;
    /**
     * @return Returns the dfltNotfyAlertUserId.
     */
    public String getDfltNotfyAlertUserId()
    {
        return dfltNotfyAlertUserId;
    }

    /**
     * @param thedfltNotfyAlertUserId
     *            The dfltNotfyAlertUserId to set.
     */
    public void setDfltNotfyAlertUserId(String thedfltNotfyAlertUserId)
    {
        dfltNotfyAlertUserId = thedfltNotfyAlertUserId;
    }

    /**
     * Holds the alert based on Table name
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private String dfltAlertBasedOnTblName;

    /**
     * @return Returns the dfltAlertBasedOnTblName.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public String getDfltAlertBasedOnTblName()
    {
        return dfltAlertBasedOnTblName;
    }

    *//**
     * @param thedfltAlertBasedOnTblName
     *            The dfltAlertBasedOnTblName to set.
     *//*
    public void setDfltAlertBasedOnTblName(String thedfltAlertBasedOnTblName)
    {
        dfltAlertBasedOnTblName = thedfltAlertBasedOnTblName;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.

    /**
     * Holds the alert based on Col name
     */
    private String dfltAlertBasedOnColName;

    /**
     * @return Returns the dfltAlertBasedOnColName.
     */
    public String getDfltAlertBasedOnColName()
    {
        return dfltAlertBasedOnColName;
    }

    /**
     * @param thedfltAlertBasedOnColName
     *            The dfltAlertBasedOnColName to set.
     */
    public void setDfltAlertBasedOnColName(String thedfltAlertBasedOnColName)
    {
        dfltAlertBasedOnColName = thedfltAlertBasedOnColName;
    }

    /**
     * Holds the send alert days code.
     */
    private String dfltSendAlertDaysCode;

    /**
     * @return Returns the dfltSendAlertDaysCode.
     */
    public String getDfltSendAlertDaysCode()
    {
        return dfltSendAlertDaysCode;
    }

    /**
     * @param thedfltSendAlertDaysCode
     *            The dfltSendAlertDaysCode to set.
     */
    public void setDfltSendAlertDaysCode(String thedfltSendAlertDaysCode)
    {
        dfltSendAlertDaysCode = thedfltSendAlertDaysCode;
    }

    /**
     * Holds the before after code
     */
    private String dfltBeforeAfterCode;

    /**
     * @return Returns the dfltBeforeAfterCode.
     */
    public String getDfltBeforeAfterCode()
    {
        return dfltBeforeAfterCode;
    }

    /**
     * @param thedfltBeforeAfterCode
     *            The dfltBeforeAfterCode to set.
     */
    public void setDfltBeforeAfterCode(String thedfltBeforeAfterCode)
    {
        dfltBeforeAfterCode = thedfltBeforeAfterCode;
    }

    /**
     * Holds the default cots letter tempate key data.
     */
    private String dfltCotsLtrTmpltKeyData;

    /**
     * @return Returns the dfltCotsLtrTmpltKeyData.
     */
    public String getDfltCotsLtrTmpltKeyData()
    {
        return dfltCotsLtrTmpltKeyData;
    }

    /**
     * @param thedfltCotsLtrTmpltKeyData
     *            The dfltCotsLtrTmpltKeyData to set.
     */
    public void setDfltCotsLtrTmpltKeyData(String thedfltCotsLtrTmpltKeyData)
    {
        dfltCotsLtrTmpltKeyData = thedfltCotsLtrTmpltKeyData;
    }

    

    /**
     * @param dfltSendAlertDaysBeforeOrAfter
     *            The dfltSendAlertDaysBeforeOrAfter to set.
     */
    public void setDfltSendAlertDaysBeforeOrAfter(
            String dfltSendAlertDaysBeforeOrAfter)
    {
        this.dfltSendAlertDaysBeforeOrAfter = dfltSendAlertDaysBeforeOrAfter;
    }

    /**
     * @return Returns the dfltSendAlertDaysBeforeOrAfter.
     */
    public String getDfltSendAlertDaysBeforeOrAfter()
    {
        return dfltSendAlertDaysBeforeOrAfter;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /**
     * @return Returns the workunitvo.
     */
    /*public CaseTypeStepWorkUnitVO getWorkunitvo()
    {
        return workunitvo;
    }

    *//**
     * @param workunitvo
     *            The workunitvo to set.
     *//*
    public void setWorkunitvo(CaseTypeStepWorkUnitVO workunitvo)
    {
        this.workunitvo = workunitvo;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.    

    /**
     * @param automaticRouteTo
     *            The automaticRouteTo to set.
     */
    public void setAutomaticRouteTo(String automaticRouteTo)
    {
        this.automaticRouteTo = automaticRouteTo;
    }

    /**
     * @return Returns the automaticRouteTo.
     */
    public String getAutomaticRouteTo()
    {
        return automaticRouteTo;
    }

    /**
     * @param imgInclude The imgInclude to set.
     */
    public void setImgInclude(boolean imgInclude)
    {
        this.imgInclude = imgInclude;
    }

    /**
     * @return Returns the imgInclude.
     */
    public boolean isImgInclude()
    {
        return imgInclude;
    }

    /**
     * Holds the default route
     */
     private String automaticRouteTo;
    /**
     * Holds the automatic route Descn
     */
     private String automaticRouteToDesc;
    /**
     * @return Returns the descValue.
     */
    public String getDescValue()
    {
        return descValue;
    }
    /**
     * @param descValue The descValue to set.
     */
    public void setDescValue(String descValue)
    {
        this.descValue = descValue;
    }
    /**
     * @return Returns the stepOrderNumDesc.
     */
    public String getStepOrderNumDesc()
    {
        return stepOrderNumDesc;
    }
    /**
     * @param stepOrderNumDesc The stepOrderNumDesc to set.
     */
    public void setStepOrderNumDesc(String stepOrderNumDesc)
    {
        this.stepOrderNumDesc = stepOrderNumDesc;
    }
    /**
     * @return Returns the automaticRouteToDesc.
     */
    public String getAutomaticRouteToDesc()
    {
        return automaticRouteToDesc;
    }
    /**
     * @param automaticRouteToDesc The automaticRouteToDesc to set.
     */
    public void setAutomaticRouteToDesc(String automaticRouteToDesc)
    {
        this.automaticRouteToDesc = automaticRouteToDesc;
    }
    /**
     * @return Returns the cmpltnBasedOnColNameDesc.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public String getCmpltnBasedOnColNameDesc()
    {
        return cmpltnBasedOnColNameDesc;
    }
    *//**
     * @param cmpltnBasedOnColNameDesc The cmpltnBasedOnColNameDesc to set.
     *//*
    public void setCmpltnBasedOnColNameDesc(String cmpltnBasedOnColNameDesc)
    {
        this.cmpltnBasedOnColNameDesc = cmpltnBasedOnColNameDesc;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.
	/**
	 * @return Returns the dfltNotfyAlertUserName.
	 */
	public String getDfltNotfyAlertUserName() {
		return dfltNotfyAlertUserName;
	}
	/**
	 * @param dfltNotfyAlertUserName The dfltNotfyAlertUserName to set.
	 */
	public void setDfltNotfyAlertUserName(String dfltNotfyAlertUserName) {
		this.dfltNotfyAlertUserName = dfltNotfyAlertUserName;
	}
	
	
	/**
	 * @return Returns the stepSeqNum.
	 */
	public Integer getStepSeqNum() {
		return stepSeqNum;
	}
	/**
	 * @param stepSeqNum The stepSeqNum to set.
	 */
	public void setStepSeqNum(Integer stepSeqNum) {
		this.stepSeqNum = stepSeqNum;
	}
	
//	ESPRD00527655_UC-PGM-CRM-48_29SEP2010
	private String dfltAlertBasedOnDesc;
	
	
	/**
	 * @return Returns the dfltAlertBasedOnDesc.
	 */
	public String getDfltAlertBasedOnDesc() {
		return dfltAlertBasedOnDesc;
	}
	/**
	 * @param dfltAlertBasedOnDesc The dfltAlertBasedOnDesc to set.
	 */
	public void setDfltAlertBasedOnDesc(String dfltAlertBasedOnDesc) {
		this.dfltAlertBasedOnDesc = dfltAlertBasedOnDesc;
	}
	//EOF ESPRD00527655_UC-PGM-CRM-48_29SEP2010
	
	private String dfltCmpltnBasedOnDesc;
	
	/**
	 * @return Returns the dfltCmpltnBasedOnDesc.
	 */
	public String getDfltCmpltnBasedOnDesc() {
		return dfltCmpltnBasedOnDesc;
	}
	/**
	 * @param dfltCmpltnBasedOnDesc The dfltCmpltnBasedOnDesc to set.
	 */
	public void setDfltCmpltnBasedOnDesc(String dfltCmpltnBasedOnDesc) {
		this.dfltCmpltnBasedOnDesc = dfltCmpltnBasedOnDesc;
	}
}
