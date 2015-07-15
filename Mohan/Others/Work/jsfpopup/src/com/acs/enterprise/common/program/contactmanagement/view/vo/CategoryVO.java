/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * @author Wipro This class is a value object, consists of Category related
 *         elements.
 */
public class CategoryVO
        extends AuditaleEnterpriseBaseVO
        implements Cloneable
{
    /**
     * Constructor
     */
    public CategoryVO()
    {
        super();

        this.activeIndicator = ContactManagementConstants.YES;
        this.supervisorAppReqInd = ContactManagementConstants.NO;
    }

    /** Holds the category typeCode */
    private String categoryTypeCode;

    /**
     * This field is used to store Category Description.
     */
    private String categoryDesc;

    /**
     * This field is used to store Active Indicator.
     */
    private String activeIndicator;

    /**
     * This field is used to store Supervisor Approval Required Indicator.
     */
    private String supervisorAppReqInd;

    /**
     * This field is used to store the Priority.
     */
    private String priority;

    /**
     * This field is used to store the Number of Days to Keep Closed Items in
     * Watchlist.
     */
    private String numOfDaysToKeep;

    /**
     * This field is used to store the Number of Days Before Escalating to
     * Medium.
     */
    private String numOfDaysBeforeEscToMed;

    /**
     * This field is used to store the Number of Days Before Escalating to High.
     */
    private String numOfDaysBeforeEscToHigh;

    /**
     * This field is used to store the Number of Days Before Escalating to
     * Urgent.
     */
    private String numOfDaysBeforeEscToUrg;

    /**
     * This field is used to store Category SK.
     */
    private Long categorySK;

    /**
     * This field is used to store list of Included Subject.
     */
    //Modified for Heap Dump-ArrayList
    /*private List listOfSubjects = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    private List listOfSubjects = new ArrayList();

    /**
     * This field is used to store list of Included Custom Field.
     */
    //Modified for Heap Dump-ArrayList
    /*private List listOfCustomFields = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    private List listOfCustomFields = new ArrayList();

    /**
     * This field is used to store list of Included Templates.
     */
    //Modified for Heap Dump-ArrayList
   /* private List listOfTemplates = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    private List listOfTemplates = new ArrayList();

    /**
     * This field is used to store whether Category is inactive.
     */
    private boolean inactive = false;

    /**
     * This field is used to store callScriptSK.
     */
    private Long callScriptSK;

    /**
     * This field is used to store workUnitSK.
     */
    private Long workUnitSK;

    /**
     * This field is used to store versionNo.
     */
    private int versionNo;

    /**
     * This field is used to store listOfDeletedSubjects.
     */
    //Modified for Heap Dump-ArrayList
    /*private List listOfDeletedSubjects = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    private List listOfDeletedSubjects = new ArrayList();

    /**
     * This field is used to store listOfDeletedCustomFields.
     */
    //Modified for Heap Dump-ArrayList
    /*private List listOfDeletedCustomFields = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    private List listOfDeletedCustomFields = new ArrayList();

    /**
     * This field is used to store listOfDeletedTemplates.
     */
    //Modified for Heap Dump-ArrayList
    /*private List listOfDeletedTemplates = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    private List listOfDeletedTemplates = new ArrayList();

    /**
     * This method is used to get the categoryDesc.
     * 
     * @return String : Returns the categoryDesc.
     */
    public String getCategoryDesc()
    {
        return categoryDesc;
    }

    /**
     * This method is used to set the categoryDesc.
     * 
     * @param categoryDesc :
     *            The categoryDesc to set.
     */
    public void setCategoryDesc(String categoryDesc)
    {
        this.categoryDesc = categoryDesc;
    }

    /**
     * This method is used to get the activeIndicator.
     * 
     * @return String : Returns the activeIndicator.
     */
    public String getActiveIndicator()
    {
        return activeIndicator;
    }

    /**
     * This method is used to set the activeIndicator.
     * 
     * @param activeIndicator :
     *            The activeIndicator to set.
     */
    public void setActiveIndicator(String activeIndicator)
    {
        this.activeIndicator = activeIndicator;
    }

    /**
     * This method is used to get the supervisorAppReqInd.
     * 
     * @return String : Returns the supervisorAppReqInd.
     */
    public String getSupervisorAppReqInd()
    {
        return supervisorAppReqInd;
    }

    /**
     * This method is used to set the supervisorAppReqInd.
     * 
     * @param supervisorAppReqInd :
     *            The supervisorAppReqInd to set.
     */
    public void setSupervisorAppReqInd(String supervisorAppReqInd)
    {
        this.supervisorAppReqInd = supervisorAppReqInd;
    }

    /**
     * This method is used to get the priority.
     * 
     * @return String : Returns the priority.
     */
    public String getPriority()
    {
        return priority;
    }

    /**
     * This method is used to set the priority.
     * 
     * @param priority :
     *            The priority to set.
     */
    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    /**
     * This method is used to get the numOfDaysToKeep.
     * 
     * @return String : Returns the numOfDaysToKeep.
     */
    public String getNumOfDaysToKeep()
    {
        return numOfDaysToKeep;
    }

    /**
     * This method is used to set the numOfDaysToKeep.
     * 
     * @param numOfDaysToKeep :
     *            The numOfDaysToKeep to set.
     */
    public void setNumOfDaysToKeep(String numOfDaysToKeep)
    {
        this.numOfDaysToKeep = numOfDaysToKeep;
    }

    /**
     * This method is used to get the numOfDaysBeforeEscToMed.
     * 
     * @return String : Returns the numOfDaysBeforeEscToMed.
     */
    public String getNumOfDaysBeforeEscToMed()
    {
        return numOfDaysBeforeEscToMed;
    }

    /**
     * This method is used to set the numOfDaysBeforeEscToMed.
     * 
     * @param numOfDaysBeforeEscToMed :
     *            The numOfDaysBeforeEscToMed to set.
     */
    public void setNumOfDaysBeforeEscToMed(String numOfDaysBeforeEscToMed)
    {
        this.numOfDaysBeforeEscToMed = numOfDaysBeforeEscToMed;
    }

    /**
     * This method is used to get the numOfDaysBeforeEscToHigh.
     * 
     * @return String : Returns the numOfDaysBeforeEscToHigh.
     */
    public String getNumOfDaysBeforeEscToHigh()
    {
        return numOfDaysBeforeEscToHigh;
    }

    /**
     * This method is used to set the numOfDaysBeforeEscToHigh.
     * 
     * @param numOfDaysBeforeEscToHigh :
     *            The numOfDaysBeforeEscToHigh to set.
     */
    public void setNumOfDaysBeforeEscToHigh(String numOfDaysBeforeEscToHigh)
    {
        this.numOfDaysBeforeEscToHigh = numOfDaysBeforeEscToHigh;
    }

    /**
     * This method is used to get the numOfDaysBeforeEscToUrg.
     * 
     * @return String : Returns the numOfDaysBeforeEscToUrg.
     */
    public String getNumOfDaysBeforeEscToUrg()
    {
        return numOfDaysBeforeEscToUrg;
    }

    /**
     * This method is used to set the numOfDaysBeforeEscToUrg.
     * 
     * @param numOfDaysBeforeEscToUrg :
     *            The numOfDaysBeforeEscToUrg to set.
     */
    public void setNumOfDaysBeforeEscToUrg(String numOfDaysBeforeEscToUrg)
    {
        this.numOfDaysBeforeEscToUrg = numOfDaysBeforeEscToUrg;
    }

    /**
     * This method is used to get the categorySK.
     * 
     * @return Long : Returns the categorySK.
     */
    public Long getCategorySK()
    {
        return categorySK;
    }

    /**
     * This method is used to set the categorySK.
     * 
     * @param categorySK :
     *            The categorySK to set.
     */
    public void setCategorySK(Long categorySK)
    {
        this.categorySK = categorySK;
    }

    /**
     * This method is used to get the listOfSubjects.
     * 
     * @return List : Returns the listOfSubjects.
     */
    public List getListOfSubjects()
    {
        return listOfSubjects;
    }

    /**
     * This method is used to set the listOfSubjects.
     * 
     * @param listOfSubjects :
     *            The listOfSubjects to set.
     */
    public void setListOfSubjects(List listOfSubjects)
    {
        this.listOfSubjects = listOfSubjects;
    }

    /**
     * This method is used to get the listOfCustomFields.
     * 
     * @return List : Returns the listOfCustomFields.
     */
    public List getListOfCustomFields()
    {
        return listOfCustomFields;
    }

    /**
     * This method is used to set the listOfCustomFields.
     * 
     * @param listOfCustomFields :
     *            The listOfCustomFields to set.
     */
    public void setListOfCustomFields(List listOfCustomFields)
    {
        this.listOfCustomFields = listOfCustomFields;
    }

    /**
     * This method overrides the clone method of the Object Class to clone the
     * CategoryVO.
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
        CategoryVO categoryVO = (CategoryVO) super.clone();

        if (this.listOfSubjects != null)
        {
            categoryVO.setListOfSubjects(new ArrayList(this.listOfSubjects));
        }
        if (this.listOfCustomFields != null)
        {
            categoryVO.setListOfCustomFields(new ArrayList(
                    this.listOfCustomFields));
        }
        if (this.listOfTemplates != null)
        {
            categoryVO.setListOfTemplates(new ArrayList(this.listOfTemplates));
        }

        return categoryVO;
    }

    /**
     * This method is used to get the inactive.
     * 
     * @return boolean : Returns the inactive.
     */
    public boolean isInactive()
    {
        return inactive;
    }

    /**
     * This method is used to set the inactive.
     * 
     * @param inActive :
     *            The inactive to set.
     */
    public void setInactive(boolean inActive)
    {
        this.inactive = inActive;
    }

    /**
     * This method is used to get the callScriptSK.
     * 
     * @return Long : Returns the callScriptSK.
     */
    public Long getCallScriptSK()
    {
        return callScriptSK;
    }

    /**
     * This method is used to set the callScriptSK.
     * 
     * @param callScriptSK :
     *            The callScriptSK to set.
     */
    public void setCallScriptSK(Long callScriptSK)
    {
        this.callScriptSK = callScriptSK;
    }

    /**
     * This method is used to get the workUnitSK.
     * 
     * @return Long : Returns the workUnitSK.
     */
    public Long getWorkUnitSK()
    {
        return workUnitSK;
    }

    /**
     * This method is used to set the workUnitSK.
     * 
     * @param workUnitSK :
     *            The workUnitSK to set.
     */
    public void setWorkUnitSK(Long workUnitSK)
    {
        this.workUnitSK = workUnitSK;
    }

    /**
     * This method is used to get the versionNo.
     * 
     * @return int : Returns the versionNo.
     */
    public int getVersionNo()
    {
        return versionNo;
    }

    /**
     * This method is used to set the versionNo.
     * 
     * @param versionNo :
     *            The versionNo to set.
     */
    public void setVersionNo(int versionNo)
    {
        this.versionNo = versionNo;
    }

    /**
     * This method is used to get the listOfDeletedSubjects.
     * 
     * @return List : Returns the listOfDeletedSubjects.
     */
    public List getListOfDeletedSubjects()
    {
        return listOfDeletedSubjects;
    }

    /**
     * This method is used to set the listOfDeletedSubjects.
     * 
     * @param listOfDeletedSubjects :
     *            The listOfDeletedSubjects to set.
     */
    public void setListOfDeletedSubjects(List listOfDeletedSubjects)
    {
        this.listOfDeletedSubjects = listOfDeletedSubjects;
    }

    /**
     * This method is used to get the listOfDeletedCustomFields.
     * 
     * @return List : Returns the listOfDeletedCustomFields.
     */
    public List getListOfDeletedCustomFields()
    {
        return listOfDeletedCustomFields;
    }

    /**
     * This method is used to set the listOfDeletedCustomFields.
     * 
     * @param listOfDeletedCustomFields :
     *            The listOfDeletedCustomFields to set.
     */
    public void setListOfDeletedCustomFields(List listOfDeletedCustomFields)
    {
        this.listOfDeletedCustomFields = listOfDeletedCustomFields;
    }

    /**
     * @return Returns the listOfTemplates.
     */
    public List getListOfTemplates()
    {
        return listOfTemplates;
    }

    /**
     * @param listOfTemplates
     *            The listOfTemplates to set.
     */
    public void setListOfTemplates(List listOfTemplates)
    {
        this.listOfTemplates = listOfTemplates;
    }

    /**
     * @return Returns the listOfDeletedTemplates.
     */
    public List getListOfDeletedTemplates()
    {
        return listOfDeletedTemplates;
    }

    /**
     * @param listOfDeletedTemplates
     *            The listOfDeletedTemplates to set.
     */
    public void setListOfDeletedTemplates(List listOfDeletedTemplates)
    {
        this.listOfDeletedTemplates = listOfDeletedTemplates;
    }

    /**
     * @return Returns the categoryTypeCode.
     */
    public String getCategoryTypeCode()
    {
        return categoryTypeCode;
    }

    /**
     * @param categoryTypeCode
     *            The categoryTypeCode to set.
     */
    public void setCategoryTypeCode(String categoryTypeCode)
    {
        this.categoryTypeCode = categoryTypeCode;
    }
}
