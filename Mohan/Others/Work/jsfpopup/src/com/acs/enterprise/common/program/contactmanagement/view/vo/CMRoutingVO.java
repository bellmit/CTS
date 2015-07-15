/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * This class will be the Case Routing Details Persistent Data Object that are
 * used in the Log Case.
 * 
 * @author Wipro
 */
public class CMRoutingVO extends AuditaleEnterpriseBaseVO
       // extends EnterpriseBaseVO   //CR_ESPRD00373565_LOGCASE_23JUL2010
{
  
    /** holds the created by info */
    private String createdBy;

    /** holds the createdByWorkUnitSK */
    private Long createdByWorkUnitSK;

    /** holds the assigned To */
    private String assignedTo;

    /** holds the assign this record to info */
    private String assignThisRecordTo;

    /** holds the reporting unit */
    private String reportingUnit;

    /** holds the add this record to watch list */
    private boolean addThisRecordToMyWatchlist;

    /** holds the routed by Info */
    private String routedBy;

    /** holds the routedByWorkUnitSK */
    private Long routedByWorkUntiSK;

    /** holds the routing date as String */
    private String routingDateStr;

    /** holds the routing date */
    private Date routingDate;

    /** holds the routedTo */
    private String routedTo;
    
    /** holds the routedTo name in update page */
    private String assignRoutedToName;

    /** holds the routedToWorkUnitSK */
    private Long routedToWorkUnitSK;

    /** holds the watch list info */
    private String watchList;

    /** holds the Show info */
    private String show;
    
    /** holds the department Name */
    private String deptName;

    /** holds the User Dept */
    private String userDepartment;
    
    /** holds the group type */
    private String groupType;
    
    /** holds the group type */
    private String userWorkUnit = "ICS";
    
    // Added For Defect ID ESPRD00330104
    
    /**
     * This field is used to store routedBySK.
     */
    private String routedBySK;
	
    /**
     * This field is used to store routedToSK.
     */
    private String routedToSK;
    
    /**
     * This field is used to store routedToName.
     */
    private String routedToName;
    
    /**
     * This field is used to store routedByName.
     */
    private String routedByName;
    
    /**
     * This field is used to store userDeptName.
     */
    private String userDeptName;
    
    /**
     * This field is used to store assignedUserName.
     */
    private String assignedUserName;
    
    /**
     * This field is used to store workUnit.
     */
    private String workUnit;
    
    
    /**
     * Default Constructor.
     *
     */
    public CMRoutingVO()
    {
        super();
    }

    /**
     * @return Returns the caseSK.
     */
    public String getCaseSK()
    {
        return caseSK;
    }

    /**
     * @param caseSK
     *            The caseSK to set.
     */
    public void setCaseSK(String caseSK)
    {
        this.caseSK = caseSK;
        //logger.debug("setCaseSK()");
    }

    /** holds the caseSK */
    private String caseSK;

    /**
     * @return Returns the addThisRecordToMyWatchlist.
     */
    public boolean getAddThisRecordToMyWatchlist()
    {
        return addThisRecordToMyWatchlist;
    }

    /**
     * @param addThisRecordToMyWatchlist
     *            The addThisRecordToMyWatchlist to set.
     */
    public void setAddThisRecordToMyWatchlist(boolean addThisRecordToMyWatchlist)
    {
        this.addThisRecordToMyWatchlist = addThisRecordToMyWatchlist;
    }

    /**
     * @return Returns the assignedTo.
     */
    public String getAssignedTo()
    {
        return assignedTo;
    }

    /**
     * @param assignedTo
     *            The assignedTo to set.
     */
    public void setAssignedTo(String assignedTo)
    {
        this.assignedTo = assignedTo;
    }

    /**
     * @return Returns the assignThisRecordTo.
     */
    public String getAssignThisRecordTo()
    {
        return assignThisRecordTo;
    }

    /**
     * @param assignThisRecordTo
     *            The assignThisRecordTo to set.
     */
    public void setAssignThisRecordTo(String assignThisRecordTo)
    {
        this.assignThisRecordTo = assignThisRecordTo;
    }

    /**
     * @return Returns the createdBy.
     */
    public String getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @param createdBy
     *            The createdBy to set.
     */
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    /**
     * @return Returns the reportingUnit.
     */
    public String getReportingUnit()
    {
        return reportingUnit;
    }

    /**
     * @param reportingUnit
     *            The reportingUnit to set.
     */
    public void setReportingUnit(String reportingUnit)
    {
        this.reportingUnit = reportingUnit;
    }

    /**
     * @return Returns the routedBy.
     */
    public String getRoutedBy()
    {
        return routedBy;
    }

    /**
     * @param routedBy
     *            The routedBy to set.
     */
    public void setRoutedBy(String routedBy)
    {
        this.routedBy = routedBy;
    }

    /**
     * @return Returns the routedTo.
     */
    public String getRoutedTo()
    {
        return routedTo;
    }

    /**
     * @param routedTo
     *            The routedTo to set.
     */
    public void setRoutedTo(String routedTo)
    {
        this.routedTo = routedTo;
    }

    /**
     * @return Returns the show.
     */
    public String getShow()
    {
        return show;
    }

    /**
     * @param show
     *            The show to set.
     */
    public void setShow(String show)
    {
        this.show = show;
    }

    /**
     * @return Returns the watchList.
     */
    public String getWatchList()
    {
        return watchList;
    }

    /**
     * @param watchList
     *            The watchList to set.
     */
    public void setWatchList(String watchList)
    {
        this.watchList = watchList;
    }

    /**
     * @return Returns the userDepartment.
     */
    public String getUserDepartment()
    {
        return userDepartment;
    }

    /**
     * @param userDepartment
     *            The userDepartment to set.
     */
    public void setUserDepartment(String userDepartment)
    {
        this.userDepartment = userDepartment;
    }

    /**
     * @return Returns the routingDate.
     */
    public Date getRoutingDate()
    {
        return routingDate;
    }

    /**
     * @param routingDate
     *            The routingDate to set.
     */
    public void setRoutingDate(Date routingDate)
    {
        this.routingDate = routingDate;
    }

    /**
     * @return Returns the routingDateStr.
     */
    public String getRoutingDateStr()
    {
        return routingDateStr;
    }

    /**
     * @param routingDateStr
     *            The routingDateStr to set.
     */
    public void setRoutingDateStr(String routingDateStr)
    {
        this.routingDateStr = routingDateStr;
    }

    /**
     * @return Returns the createdByWorkUnitSK.
     */
    public Long getCreatedByWorkUnitSK()
    {
        return createdByWorkUnitSK;
    }

    /**
     * @param createdByWorkUnitSK
     *            The createdByWorkUnitSK to set.
     */
    public void setCreatedByWorkUnitSK(Long createdByWorkUnitSK)
    {
        this.createdByWorkUnitSK = createdByWorkUnitSK;
    }

    /**
     * @return Returns the routedByWorkUntiSK.
     */
    public Long getRoutedByWorkUntiSK()
    {
        return routedByWorkUntiSK;
    }

    /**
     * @param routedByWorkUntiSK
     *            The routedByWorkUntiSK to set.
     */
    public void setRoutedByWorkUntiSK(Long routedByWorkUntiSK)
    {
        this.routedByWorkUntiSK = routedByWorkUntiSK;
    }

    /**
     * @return Returns the routedToWorkUnitSK.
     */
    public Long getRoutedToWorkUnitSK()
    {
        return routedToWorkUnitSK;
    }

    /**
     * @param routedToWorkUnitSK
     *            The routedToWorkUnitSK to set.
     */
    public void setRoutedToWorkUnitSK(Long routedToWorkUnitSK)
    {
        this.routedToWorkUnitSK = routedToWorkUnitSK;
    }
    /**
     * @return Returns the deptName.
     */
    public String getDeptName()
    {
        return deptName;
    }
    /**
     * @param deptName The deptName to set.
     */
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
    /**
     * @return Returns the groupType.
     */
    public String getGroupType()
    {
        return groupType;
    }
    /**
     * @param groupType The groupType to set.
     */
    public void setGroupType(String groupType)
    {
        this.groupType = groupType;
    }
    /**
     * @return Returns the assignRoutedToName.
     */
    public String getAssignRoutedToName()
    {
        return assignRoutedToName;
    }
    /**
     * @param assignRoutedToName The assignRoutedToName to set.
     */
    public void setAssignRoutedToName(String assignRoutedToName)
    {
        this.assignRoutedToName = assignRoutedToName;
    }
	/**
	 * @return Returns the userWorkUnit.
	 */
	public String getUserWorkUnit() {
		return userWorkUnit;
	}
	/**
	 * @param userWorkUnit The userWorkUnit to set.
	 */
	public void setUserWorkUnit(String userWorkUnit) {
		this.userWorkUnit = userWorkUnit;
	}
	/**
	 * @return Returns the assignedUserName.
	 */
	public String getAssignedUserName() {
		return assignedUserName;
	}
	/**
	 * @param assignedUserName The assignedUserName to set.
	 */
	public void setAssignedUserName(String assignedUserName) {
		this.assignedUserName = assignedUserName;
	}
	/**
	 * @return Returns the routedByName.
	 */
	public String getRoutedByName() {
		return routedByName;
	}
	/**
	 * @param routedByName The routedByName to set.
	 */
	public void setRoutedByName(String routedByName) {
		this.routedByName = routedByName;
	}
	/**
	 * @return Returns the routedBySK.
	 */
	public String getRoutedBySK() {
		return routedBySK;
	}
	/**
	 * @param routedBySK The routedBySK to set.
	 */
	public void setRoutedBySK(String routedBySK) {
		this.routedBySK = routedBySK;
	}
	/**
	 * @return Returns the routedToName.
	 */
	public String getRoutedToName() {
		return routedToName;
	}
	/**
	 * @param routedToName The routedToName to set.
	 */
	public void setRoutedToName(String routedToName) {
		this.routedToName = routedToName;
	}
	/**
	 * @return Returns the routedToSK.
	 */
	public String getRoutedToSK() {
		return routedToSK;
	}
	/**
	 * @param routedToSK The routedToSK to set.
	 */
	public void setRoutedToSK(String routedToSK) {
		this.routedToSK = routedToSK;
	}
	/**
	 * @return Returns the userDeptName.
	 */
	public String getUserDeptName() {
		return userDeptName;
	}
	/**
	 * @param userDeptName The userDeptName to set.
	 */
	public void setUserDeptName(String userDeptName) {
		this.userDeptName = userDeptName;
	}
	/**
	 * @return Returns the workUnit.
	 */
	public String getWorkUnit() {
		return workUnit;
	}
	/**
	 * @param workUnit The workUnit to set.
	 */
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	
	/** holds the businessUnit info */
	private String busineesUnit;
	/**
	 * @return Returns the busineesUnit.
	 */
	public String getBusineesUnit() {
		return busineesUnit;
	}
	/**
	 * @param busineesUnit The busineesUnit to set.
	 */
	public void setBusineesUnit(String busineesUnit) {
		this.busineesUnit = busineesUnit;
	}
	// Begin - Added the code for the defect id ESPRD00702153_30NOV2011
	/**
     * This field is used to store routedByUserID.
     */
    private String routedByUserID;


	/**
	 * @return the routedByUserID
	 */
	public String getRoutedByUserID() {
		return routedByUserID;
	}

	/**
	 * @param routedByUserID the routedByUserID to set
	 */
	public void setRoutedByUserID(String routedByUserID) {
		this.routedByUserID = routedByUserID;
	}
	// End - Added the code for the defect id ESPRD00702153_30NOV2011	
}
