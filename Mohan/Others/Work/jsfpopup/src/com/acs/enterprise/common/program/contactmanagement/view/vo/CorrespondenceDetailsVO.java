/*
 * Created on Dec 10, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * Holds the information about Correspondence Details.
 */
public class CorrespondenceDetailsVO
        extends  AuditaleEnterpriseBaseVO
        implements Cloneable
{
    /**
     * constructor for CorrespondenceDetailsVO
     */
    public CorrespondenceDetailsVO()
    {
        super();
        this.anLobCode = "MED";
    }

    /**
     * CR Created by User name.
     */
    private String createdByName;

    /**
     * CR Created by User SK.
     */
    private String createdBySK;

    /**
     * CR Created by User ID.
     */
    private String createdByUserID;

    /**
     * CR Assigned to User SK.
     */
    private String assignedToWorkUnitName;

    /**
     * CR Assigned to User SK.
     */
    private String assignedToWorkUnitSK;

    /**
     * The user who open the cr.
     */
    private String crspdOpenWorkUnit;

    /**
     * The user who update the cr.
     */
    private String crspdLastUpdWorkUnit;

    /**
     * Reporting Unit Name for the User to whom CR is assigned.
     */
    private String reportingUnitName;

    /**
     * Reporting Unit SK for the User to whom CR is assigned.
     */
    private String reportingUnitSK;

    /**
     * Business Unit Name for the User to whom CR is assigned.
     */
    private String businessUnitName;

    /**
     * Business Unit SK for the User to whom CR is assigned.
     */
    private String businessUnitSK;

    /**
     * Date when CR is opened.
     */
    private String openDate;

    /**
     * Under Review, Closed, etc.
     */
    private String statusCode;

    /**
     * The date the status code changed.
     */
    private String statusDate;

    /**
     * The resolution code.
     */
    private String resolutionCode;

    /**
     * e.g. High, Medium, Low
     */
    private String priorityCode;

    /**
     * Defines the source of the correspondence.
     */
    private String sourceCode;

    /**
     * e.g. Supervisor Review Required Indicator.
     */
    private Boolean sprvsrRevwReqdIndicator = Boolean.FALSE;

    /**
     * This field indicates a line of business code to be used for system
     * processing. The line of business is used to identify the entities that
     * have fiscal responsibility for payment of insurance claims on behalf of
     * their respective members.
     */
    private String anLobCode;

    /**
     * The subject key where subject related information is there.
     */
    private String subjectCode;

    /**
     * The correspondence category in which the cr is belongs to.
     */
    private String categorySK;

    /**
     * Custom Field for the CR.
     */
    //commented for unused variables
    //private String customField;

    /**
     * Department for which the CR is raised.
     */
    private String department;

    /**
     * This field is used to store listOfEnquiryAboutEntities.
     */
    private List listOfEnquiryAboutEntities = new ArrayList();

    /**
     * daysToClose field for Correspondence Details section
     */

    private String daysToClose;

    /**
     * receivedDate field for Correspondence Details section
     */
    private String receivedDate;

    /**
     * tcn field for Correspondence Details section
     */
    private String tcn;

    /**
     * authNumber field for Correspondence Details section
     */
    private String authNumber;

    /**
     * daysOpen field for Correspondence Details section
     */
    private String daysOpen;

    /**
     * This field is used to store selectedReferredToList.
     */
    private List selectedReferredToList = new ArrayList();

    /**
     * This field is used to store vipPresent.
     */
    private String vipPresent;

    /**
     * This field is used to store dentalApptMadeBy.
     */
    private String dentalApptMadeBy;

    /**
     * This field is used to store languageSpoken.
     */
    //commented for unused variables
    //private String languageSpoken;

    /**
     * This field is used to store ltdEngProficiency.
     */
    private String ltdEngProficiency;
    
    /**
     * This field is used to store assigned to WorkUnitTypeCode
     * @return String
     */
    private String workUnitTypeCode;
    
    private boolean supervisorInd = false;

    /**
     * @return Returns the sourceCode.
     */
    public String getSourceCode()
    {
        return sourceCode;
    }

    /**
     * @param sourceCode
     *            The sourceCode to set.
     */
    public void setSourceCode(String sourceCode)
    {
        this.sourceCode = sourceCode;
    }

    /**
     * @return Returns the sprvsrRevwReqdIndicator.
     */
    public Boolean getSprvsrRevwReqdIndicator()
    {
        return sprvsrRevwReqdIndicator;
    }

    /**
     * @param sprvsrRevwReqdIndicator
     *            The sprvsrRevwReqdIndicator to set.
     */
    public void setSprvsrRevwReqdIndicator(Boolean sprvsrRevwReqdIndicator)
    {
        this.sprvsrRevwReqdIndicator = sprvsrRevwReqdIndicator;
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
     * @return Returns the statusDate.
     */
    public String getStatusDate()
    {
        return statusDate;
    }

    /**
     * @param statusDate
     *            The statusDate to set.
     */
    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }

    /**
     * @return Returns the subjectCode.
     */
    public String getSubjectCode()
    {
        return subjectCode;
    }

    /**
     * @param subjectSK
     *            The subjectSK to set.
     */
    public void setSubjectCode(String subjectSK)
    {
        this.subjectCode = subjectSK;
    }

    /**
     * @return Returns the openDate.
     */
    public String getOpenDate()
    {
        return openDate;
    }

    /**
     * @param openDate
     *            The openDate to set.
     */
    public void setOpenDate(String openDate)
    {
        this.openDate = openDate;
    }

    /**
     * @return Returns the priorityCode.
     */
    public String getPriorityCode()
    {
        return priorityCode;
    }

    /**
     * @param priorityCode
     *            The priorityCode to set.
     */
    public void setPriorityCode(String priorityCode)
    {
        this.priorityCode = priorityCode;
    }

    /**
     * @return Returns the resolutionCode.
     */
    public String getResolutionCode()
    {
        return resolutionCode;
    }

    /**
     * @param resolutionCode
     *            The resolutionCode to set.
     */
    public void setResolutionCode(String resolutionCode)
    {
        this.resolutionCode = resolutionCode;
    }

    /**
     * @return Returns the categorySK.
     */
    public String getCategorySK()
    {
        return categorySK;
    }

    /**
     * @param categorySK
     *            The categorySK to set.
     */
    public void setCategorySK(String categorySK)
    {
        this.categorySK = categorySK;
    }

    /**
     * @return Returns the customField.
     */
   /* public String getCustomField()
    {
        return customField;
    }

    *//**
     * @param customField
     *            The customField to set.
     *//*
    public void setCustomField(String customField)
    {
        this.customField = customField;
    }*/

    /**
     * @return Returns the anLobCode.
     */
    public String getAnLobCode()
    {
        return anLobCode;
    }

    /**
     * @param anLobCode
     *            The anLobCode to set.
     */
    public void setAnLobCode(String anLobCode)
    {
        this.anLobCode = anLobCode;
    }

    /**
     * @return Returns the assignedToWorkUnitName.
     */
    public String getAssignedToWorkUnitName()
    {
        return assignedToWorkUnitName;
    }

    /**
     * @param assignedToWorkUnitName
     *            The assignedToWorkUnitName to set.
     */
    public void setAssignedToWorkUnitName(String assignedToWorkUnitName)
    {
        this.assignedToWorkUnitName = assignedToWorkUnitName;
    }

    /**
     * This method is used to get the createdByUserID.
     * 
     * @return String : Returns the createdByUserID.
     */
    public String getCreatedByUserID()
    {
        return createdByUserID;
    }

    /**
     * This method is used to set the createdByUserID.
     * 
     * @param createdByUserID :
     *            The createdByUserID to set.
     */
    public void setCreatedByUserID(String createdByUserID)
    {
        this.createdByUserID = createdByUserID;
    }

    /**
     * @return Returns the assignedToWorkUnitSK.
     */
    public String getAssignedToWorkUnitSK()
    {
        return assignedToWorkUnitSK;
    }

    /**
     * @param assignedToWorkUnitSK
     *            The assignedToWorkUnitSK to set.
     */
    public void setAssignedToWorkUnitSK(String assignedToWorkUnitSK)
    {
        this.assignedToWorkUnitSK = assignedToWorkUnitSK;
    }

    /**
     * This method is used to get the crspdOpenWorkUnit.
     * 
     * @return String : Returns the crspdOpenWorkUnit.
     */
    public String getCrspdOpenWorkUnit()
    {
        return crspdOpenWorkUnit;
    }

    /**
     * This method is used to set the crspdOpenWorkUnit.
     * 
     * @param crspdOpenWorkUnit :
     *            The crspdOpenWorkUnit to set.
     */
    public void setCrspdOpenWorkUnit(String crspdOpenWorkUnit)
    {
        this.crspdOpenWorkUnit = crspdOpenWorkUnit;
    }

    /**
     * This method is used to get the crspdLastUpdWorkUnit.
     * 
     * @return String : Returns the crspdLastUpdWorkUnit.
     */
    public String getCrspdLastUpdWorkUnit()
    {
        return crspdLastUpdWorkUnit;
    }

    /**
     * This method is used to set the crspdLastUpdWorkUnit.
     * 
     * @param crspdLastUpdWorkUnit :
     *            The crspdLastUpdWorkUnit to set.
     */
    public void setCrspdLastUpdWorkUnit(String crspdLastUpdWorkUnit)
    {
        this.crspdLastUpdWorkUnit = crspdLastUpdWorkUnit;
    }

    /**
     * @return Returns the businessUnitName.
     */
    public String getBusinessUnitName()
    {
        return businessUnitName;
    }

    /**
     * @param businessUnitName
     *            The businessUnitName to set.
     */
    public void setBusinessUnitName(String businessUnitName)
    {
        this.businessUnitName = businessUnitName;
    }

    /**
     * @return Returns the businessUnitSK.
     */
    public String getBusinessUnitSK()
    {
        return businessUnitSK;
    }

    /**
     * @param businessUnitSK
     *            The businessUnitSK to set.
     */
    public void setBusinessUnitSK(String businessUnitSK)
    {
        this.businessUnitSK = businessUnitSK;
    }

    /**
     * @return Returns the createdByName.
     */
    public String getCreatedByName()
    {
        return createdByName;
    }

    /**
     * @param createdByName
     *            The createdByName to set.
     */
    public void setCreatedByName(String createdByName)
    {
        this.createdByName = createdByName;
    }

    /**
     * @return Returns the createdBySK.
     */
    public String getCreatedBySK()
    {
        return createdBySK;
    }

    /**
     * @param createdBySK
     *            The createdBySK to set.
     */
    public void setCreatedBySK(String createdBySK)
    {
        this.createdBySK = createdBySK;
    }

    /**
     * @return Returns the department.
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * @param department
     *            The department to set.
     */
    public void setDepartment(String department)
    {
        this.department = department;
    }

    /**
     * @return Returns the reportingUnitName.
     */
    public String getReportingUnitName()
    {
        return reportingUnitName;
    }

    /**
     * @param reportingUnitName
     *            The reportingUnitName to set.
     */
    public void setReportingUnitName(String reportingUnitName)
    {
        this.reportingUnitName = reportingUnitName;
    }

    /**
     * @return Returns the reportingUnitSK.
     */
    public String getReportingUnitSK()
    {
        return reportingUnitSK;
    }

    /**
     * @param reportingUnitSK
     *            The reportingUnitSK to set.
     */
    public void setReportingUnitSK(String reportingUnitSK)
    {
        this.reportingUnitSK = reportingUnitSK;
    }

    /**
     * This method is used to get the listOfEnquiryAboutEntities.
     * 
     * @return List : Returns the listOfEnquiryAboutEntities.
     */
    public List getListOfEnquiryAboutEntities()
    {
        return listOfEnquiryAboutEntities;
    }

    /**
     * This method is used to set the listOfEnquiryAboutEntities.
     * 
     * @param listOfEnquiryAboutEntities :
     *            The listOfEnquiryAboutEntities to set.
     */
    public void setListOfEnquiryAboutEntities(List listOfEnquiryAboutEntities)
    {
        this.listOfEnquiryAboutEntities = listOfEnquiryAboutEntities;
    }

    /**
     * This method overrides the clone method of the Object Class to clone the
     * CorrespondenceDetailsVO.
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
        CorrespondenceDetailsVO correspondenceDetailsVO = (CorrespondenceDetailsVO) super
                .clone();

        if (this.listOfEnquiryAboutEntities != null)
        {
            correspondenceDetailsVO
                    .setListOfEnquiryAboutEntities(new ArrayList(
                            this.listOfEnquiryAboutEntities));
        }

        return correspondenceDetailsVO;
    }

    /**
     * @return Returns the daysToClose.
     */
    public String getDaysToClose()
    {
        return daysToClose;
    }

    /**
     * @param daysToClose
     *            The daysToClose to set.
     */
    public void setDaysToClose(String daysToClose)
    {
        this.daysToClose = daysToClose;
    }

    /**
     * @return Returns the receivedDate.
     */
    public String getReceivedDate()
    {
        return receivedDate;
    }

    /**
     * @param receivedDate
     *            The receivedDate to set.
     */
    public void setReceivedDate(String receivedDate)
    {
        this.receivedDate = receivedDate;
    }

    /**
     * @return Returns the authNumber.
     */
    public String getAuthNumber()
    {
        return authNumber;
    }

    /**
     * @param authNumber
     *            The authNumber to set.
     */
    public void setAuthNumber(String authNumber)
    {
        this.authNumber = authNumber;
    }

    /**
     * @return Returns the tcn.
     */
    public String getTcn()
    {
        return tcn;
    }

    /**
     * @param tcn
     *            The tcn to set.
     */
    public void setTcn(String tcn)
    {
        this.tcn = tcn;
    }

    /**
     * @return Returns the daysOpen.
     */
    public String getDaysOpen()
    {
        return daysOpen;
    }

    /**
     * @param daysOpen
     *            The daysOpen to set.
     */
    public void setDaysOpen(String daysOpen)
    {
        this.daysOpen = daysOpen;
    }

    /**
     * @return Returns the dentalApptMadeBy.
     */
    public String getDentalApptMadeBy()
    {
        return dentalApptMadeBy;
    }

    /**
     * @param dentalApptMadeBy
     *            The dentalApptMadeBy to set.
     */
    public void setDentalApptMadeBy(String dentalApptMadeBy)
    {
        this.dentalApptMadeBy = dentalApptMadeBy;
    }

    /**
     * @return Returns the languageSpoken.
     */
    /*public String getLanguageSpoken()
    {
        return languageSpoken;
    }

    *//**
     * @param languageSpoken
     *            The languageSpoken to set.
     *//*
    public void setLanguageSpoken(String languageSpoken)
    {
        this.languageSpoken = languageSpoken;
    }
*/
    /**
     * @return Returns the ltdEngProficiency.
     */
    public String getLtdEngProficiency()
    {
        return ltdEngProficiency;
    }

    /**
     * @param ltdEngProficiency
     *            The ltdEngProficiency to set.
     */
    public void setLtdEngProficiency(String ltdEngProficiency)
    {
        this.ltdEngProficiency = ltdEngProficiency;
    }

    /**
     * @return Returns the selectedReferredToList.
     */
    public List getSelectedReferredToList()
    {
        return selectedReferredToList;
    }

    /**
     * @param selectedReferredToList
     *            The selectedReferredToList to set.
     */
    public void setSelectedReferredToList(List selectedReferredToList)
    {
        this.selectedReferredToList = selectedReferredToList;
    }

    /**
     * @return Returns the vipPresent.
     */
    public String getVipPresent()
    {
        return vipPresent;
    }

    /**
     * @param vipPresent The vipPresent to set.
     */
    public void setVipPresent(String vipPresent)
    {
        this.vipPresent = vipPresent;
    }
    
	/**
	 * @return Returns the workUnitTypeCode.
	 */
	public String getWorkUnitTypeCode() {
		return workUnitTypeCode;
	}
	/**
	 * @param workUnitTypeCode The workUnitTypeCode to set.
	 */
	public void setWorkUnitTypeCode(String workUnitTypeCode) {
		this.workUnitTypeCode = workUnitTypeCode;
	}
	
	/**
	 * @return Returns the supervisorInd.
	 */
	public boolean isSupervisorInd() {
		return supervisorInd;
	}
	/**
	 * @param supervisorInd The supervisorInd to set.
	 */
	public void setSupervisorInd(boolean supervisorInd) {
		this.supervisorInd = supervisorInd;
	}
}
