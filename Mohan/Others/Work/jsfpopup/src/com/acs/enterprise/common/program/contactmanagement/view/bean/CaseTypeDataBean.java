/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeEventVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeLetterTemplateVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeStepVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseTypeVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;


/**
 * This class will be the Data bean for Maintain Case Type
 * 
 * @author Wipro
 */
public class CaseTypeDataBean
        extends EnterpriseBaseDataBean
{

    /**
     * Generating object of EnterpriseLogger.
     */
    
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(CaseTypeDataBean.class);

	   
    /** holds the user map */
    private Map userMap = new HashMap();
	
    /**
     * Generates a Variable to hold Bean Name.
     */
   
    //public static final String BEAN_NAME = "CaseTypeDataBean";

    /**
     * List to hold values of bussinessunits of caseType valid values .
     */
    private List bussinessUnits = Collections.EMPTY_LIST;

    /**
     * List to hold values of CaseStep DescriptionList valid values .
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private List stepDescriptionList = new ArrayList();

    /**
     * List to hold values of CaseStep Order List valid values .
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private List stepOrderList = new ArrayList();

    /**
     * List to hold values of CaseStep Automatic RoutoTo valid values .
     */
    private List caseStepAutRoutoToList = new ArrayList();

    /**
     * List to hold values of CaseStep completionBasedOn valid values .
     */
    private List completionBasedOnList = Collections.EMPTY_LIST;

    /**
     * List to hold values of CaseStep dfltNotifyAlertList valid values .
     */
    private List dfltNotifyAlertList = new ArrayList();

    /**
     * List to hold values of CaseStep dfltAlertBasedOnList valid values .
     */
    private List dfltAlertBasedOnList = Collections.EMPTY_LIST;

    /**
     * List to hold values of CaseStep dfltAlertSendBasedOnList valid values .
     */
    private List dfltAlertSendBasedOnList = Collections.EMPTY_LIST;

    /**
     * List to hold values of CaseStep dfltTemplateList valid values .
     */
    //FOR THE HEAP DUMP ISSUE
    //private List dfltTemplateList = new ArrayList();

    /**
     * List to hold values of CaseEvent Dflt Alert BasedOn valid values .
     */
    private List eventDfltAlertBasedOnList = Collections.EMPTY_LIST;
    /**
     * TO hold customfield include value
     */
     private boolean customFieldSelected;
     /**
      * To hold boolean value
      */
     private boolean  updateFlag;
     
     /**
      * To hold boolean value
      */
     private boolean  valiValueFlag=true;
     
    /**
     * List to hold values of Case Event Type List valid values .
     */
     // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private List eventTypeList = new ArrayList();

    /**
     * List to hold values of Case Event Send Alert List valid values .
     */
    private List eventDfltSendAlertList = Collections.EMPTY_LIST;

    /**
     * List to hold values of CaseEvent Dflt Notify Alert valid values .
     */
    private List eventDfltNotifyAlertList = new ArrayList();

    /**
     * List to hold values of CaseEvent dflt Template valid values .
     */
    //HEAP DUMP ISSUE
   // private List eventDfltTemplate = new ArrayList();

    /**
     * List to hold radio button vales
     */
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private List includeRadioList = new ArrayList();
    /**
     * List to hold UserWorkunit and Userid value
     */
    private List userWorkUnitList = new ArrayList();

    /**
     * Variable to reneder Edit Case Types Block.
     */
    private boolean showEditCaseTypes = false;

    /**
     * Variable to reneder Add Case Types Block.
     */
    private boolean showAddCaseTypes;

    /**
     * Variable to reneder Edit Case Steps Block.
     */
    private boolean showEditCaseSteps = false;

    /**
     * Variable to reneder Add Case Steps Block.
     */
    private boolean showAddCaseSteps = false;

    /**
     * Variable to reneder Edit Case Events Block.
     */
    private boolean showEditCaseEvents = false;

    /**
     * Variable to reneder Add Case events Block.
     */
    private boolean showAddCaseEvents = false;

    /**
     * Flag for rendering data in case types data table
     */
    private boolean noCaseTypesData = false;

    /**
     * Flag for rendering data in casestep data table
     */
    private boolean noCaseStepsData = false;

    /**
     * Flag for rendering data in case event data table
     */
    private boolean noCaseEventsData = false;

    /**
     * Flag for rendering data in customfields table
     */
    private boolean nocustomfieldsData = true;

    /**
     * Flag for rendering data in templates data table
     */
    private boolean noTemplatesData = false;
    /**
     * Falg for disable childs when casetype not active
     */
    private boolean caseTypeNoActive = false;
    /**
     * Flag to show delete message
     */
    private boolean showDeleteMessage = false;
    /**
     * Flag to Show message when case associated
     */
    private boolean showDeleteFlag = false;
    /**
     * Flag to hold event Errormessage
     */
    private boolean showTypeCodeFlag = false; 
    /**
     * Flag for disabling include radio button
     */
    private boolean incudeflag = false;
    /**
     * Flag for displaying errormesg for order and desc
     */
     private boolean showDescOrderFlag = false;
    /**
     * Flag to hold type desc error message
     */
     private boolean showTypeDescFlag = false;
     /**
      * Flag to hold StepSuccess message
      */
     private boolean showStepSuccessMsg = false;
     /**
      * Flag to render delete message for casestep
      */
     private boolean showStepActMsg = false;
     /**
      * Flag to render delete message for casestep
      */
     private boolean showEventActMsg = false;
    /**
     * Variable to hold Index of edited CaseStep row.
     */
    private int editCaseStepRowIndex;

    /**
     * Variable to hold Index of edited case event row.
     */
    private int editCaseEventRowIndex;

    /**
     * Variable to hold Index of edited Case Type row.
     */
    private int editCaseTypeRowIndex;
    /**
     * List to hold unselected tempaltes
     */
    private List unSelectedTemplates = new ArrayList();
    /**
     * List to hold unselected customfileds
     */
    private List unSelectedCustomFields = new ArrayList();
    /**
     * List to hold includeno casetypes
     */
    private List caseStepNoIncludeList = new ArrayList();
    /**
     * List to hold includeno caseevents
     */
    private List caseEventNoIncludeList = new ArrayList();

    /**
     * List to hold values datatable for maintainCaseType.
     */
    private List maintainCaseTypeList = Collections.EMPTY_LIST;

    /**
     * List to hold values datatable for maintainCaseStep.
     */
    
  //Commented for Heap Dump Issue
    // Commented for unused variables for the defect id ESPRD00723971_21NOV2011.
    //private List maintainCaseStepList = new ArrayList();
    /*private List maintainCaseStepList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/

    /**
     * List to hold values datatable for maintainCaseEvent.
     */
  //Commented for Heap Dump Issue
    private List maintainCaseEventList = new ArrayList();
   /* private List maintainCaseEventList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/

    /**
     * List to hold values datatable for maintain CaseType Custom Filelds
     */
  //Commented for Heap Dump Issue
    private List maintainCaseCustomFieldsList = new ArrayList();
   /* private List maintainCaseCustomFieldsList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/

    /**
     * List to hold values datatable for maintain CaseType templates
     */
  //Commented for Heap Dump Issue
    private  List maintainCaseTemplatesList = new ArrayList();
    /*private  List maintainCaseTemplatesList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/

    /**
     * Reference to hold CaseTypeSearchCriteriaVO .
     */
    
    /**
     * This field is used to store showSucessMessage.
     */

    private boolean showSucessMessage = false;

    /**
     * Reference to hold CaseType VO.
     */
    private CaseTypeVO caseTypeVO;

    /**
     * Reference to hold CaseType Step VO.
     */
    private CaseTypeStepVO caseTypeStepVO;

    /**
     * Reference to hold CustomField VO.
     */
    private CustomFieldVO customFieldVO;

    /**
     * Reference to hold CaseType Event VO.
     */
    private CaseTypeEventVO caseTypeEventVO;

    /**
     * Reference to hold CaseType Templates VO.
     */
    private CaseTypeLetterTemplateVO caseTypeTemplatesVO;

    /**
     * Reference to hold imageRender.
     */
    private String imageRender = null;

    /**
     * Reference to hold caseType AuditRender.
     */

    private boolean caseTypeAuditRender = false;

    /**
     * Reference to hold caseType Audit HistoryList
     */

    private List caseTypeAuditHistoryList = new ArrayList();

    /**
     * this list holds befroeaftercode radio list
     */
    private List beforeAfterRadioList = new ArrayList();

    /**
     * Reference to hold caseType Audit open
     */

    private boolean auditOpen = false;

   

    /**
     * List to hold Casestep Description Valid values
     */
    private List descnCaseStepList = Collections.EMPTY_LIST;

    /**
     * List to hold CasecstepOrder number Validvalues
     */
    private List orderCaseStepList = Collections.EMPTY_LIST;

    /**
     * List to hold eventcode valid values
     */
    private List caseEventTypeList = Collections.EMPTY_LIST;

    /**
     * List to hold casetype events
     */
    private List caseTypeEventsList = new ArrayList();

    /**
     * List to hold casetype steps
     */
    private List caseTypeStepsList = new ArrayList();
     /**
      * USerid holds
      */
   private String userID;
     /**
      * List to hold DropDownTemplate
      */
    private List templateDropDownList = new ArrayList();
    /**
     * This operation is used to load valied values
     */

    /**
     * Holds boolean value of disableAddLink
     */
    private boolean disableAddLink;
    

    /**
     * Holds boolean value of updateLink
     */
    private boolean updateLink;
    

    /**
     * Holds boolean value of deleteLink
     */
    private boolean deleteLink= false;
    
    /**
     * Holds boolean value of resetLink
     */
    private boolean resetLink;
   
    /**
     * @return Returns the caseTypeVO.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public CaseTypeVO getCaseTypeVO()
    {
        return caseTypeVO;
    }

    /**
     * @param thecaseTypeVO
     *            The caseTypeVO to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCaseTypeVO(CaseTypeVO caseTypeVO)
    {
        this.caseTypeVO = caseTypeVO;
    }

    /**
     * @param showEditCaseTypes
     *            The showEditCaseTypes to set.
     */
    public void setShowEditCaseTypes(boolean showEditCaseTypes)
    {
        this.showEditCaseTypes = showEditCaseTypes;
    }

    /**
     * @return Returns the showEditCaseTypes.
     */
    public boolean isShowEditCaseTypes()
    {
        return showEditCaseTypes;
    }

    /**
     * @param showAddCaseTypes
     *            The showAddCaseTypes to set.
     */
    public void setShowAddCaseTypes(boolean showAddCaseTypes)
    {
        this.showAddCaseTypes = showAddCaseTypes;
    }

    /**
     * @return Returns the showAddCaseTypes.
     */
    public boolean isShowAddCaseTypes()
    {
        return showAddCaseTypes;
       
    }

    /**
     * @param showSucessMessage
     *            The showSucessMessage to set.
     */
    public void setShowSucessMessage(boolean showSucessMessage)
    {
        this.showSucessMessage = showSucessMessage;
    }

    /**
     * @return Returns the showSucessMessage.
     */
    public boolean isShowSucessMessage()
    {
        return showSucessMessage;
    }

    /**
     * @param maintainCaseTypeList
     *            The maintainCaseTypeList to set.
     */
    public void setMaintainCaseTypeList(List maintainCaseTypeList)
    {
        this.maintainCaseTypeList = maintainCaseTypeList;
    }

    /**
     * @return Returns the maintainCaseTypeList.
     */
    public List getMaintainCaseTypeList()
    {
        return maintainCaseTypeList;
    }

    /**
     * @param showEditCaseSteps
     *            The showEditCaseSteps to set.
     */
    public void setShowEditCaseSteps(boolean showEditCaseSteps)
    {
        this.showEditCaseSteps = showEditCaseSteps;
    }

    /**
     * @return Returns the showEditCaseSteps.
     */
    public boolean isShowEditCaseSteps()
    {
        return showEditCaseSteps;
    }

    /**
     * @param showAddCaseSteps
     *            The showAddCaseSteps to set.
     */
    public void setShowAddCaseSteps(boolean showAddCaseSteps)
    {
        this.showAddCaseSteps = showAddCaseSteps;
    }

    /**
     * @return Returns the showAddCaseSteps.
     */
    public boolean isShowAddCaseSteps()
    {
        return showAddCaseSteps;
    }

    /**
     * @param showEditCaseEvents
     *            The showEditCaseEvents to set.
     */
    public void setShowEditCaseEvents(boolean showEditCaseEvents)
    {
        this.showEditCaseEvents = showEditCaseEvents;
    }

    /**
     * @return Returns the showEditCaseEvents.
     */
    public boolean isShowEditCaseEvents()
    {
        return showEditCaseEvents;
    }

    /**
     * @param showAddCaseEvents
     *            The showAddCaseEvents to set.
     */
    public void setShowAddCaseEvents(boolean showAddCaseEvents)
    {
        this.showAddCaseEvents = showAddCaseEvents;
    }

    /**
     * @return Returns the showAddCaseEvents.
     */
    public boolean isShowAddCaseEvents()
    {
        return showAddCaseEvents;
    }

    /**
     * @param maintainCaseStepList
     *            The maintainCaseStepList to set.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public void setMaintainCaseStepList(List maintainCaseStepList)
    {
        this.maintainCaseStepList = maintainCaseStepList;
    }

    *//**
     * @return Returns the maintainCaseStepList.
     *//*
    public List getMaintainCaseStepList()
    {
        return maintainCaseStepList;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.

    /**
     * @param maintainCaseEventList
     *            The maintainCaseEventList to set.
     */
    public void setMaintainCaseEventList(List maintainCaseEventList)
    {
        this.maintainCaseEventList = maintainCaseEventList;
    }

    /**
     * @return Returns the maintainCaseEventList.
     */
    public List getMaintainCaseEventList()
    {
        return maintainCaseEventList;
    }

    /**
     * @param editCaseTypeRowIndex
     *            The editCaseTypeRowIndex to set.
     */
    public void setEditCaseTypeRowIndex(int editCaseTypeRowIndex)
    {
        this.editCaseTypeRowIndex = editCaseTypeRowIndex;
    }

    /**
     * @return Returns the editCaseTypeRowIndex.
     */
    public int getEditCaseTypeRowIndex()
    {
        return editCaseTypeRowIndex;
    }

    /**
     * @param bussinessUnits
     *            The bussinessUnits to set.
     */
    public void setBussinessUnits(List bussinessUnits)
    {
        this.bussinessUnits = bussinessUnits;
    }

    /**
     * @return Returns the bussinessUnits.
     */
    public List getBussinessUnits()
    {
        return bussinessUnits;
    }

    /**
     * @param caseTypeStepVO
     *            The caseTypeStepVO to set.
     */
    public void setCaseTypeStepVO(CaseTypeStepVO caseTypeStepVO)
    {
        this.caseTypeStepVO = caseTypeStepVO;
    }

    /**
     * @return Returns the caseTypeStepVO.
     */
    public CaseTypeStepVO getCaseTypeStepVO()
    {
        return caseTypeStepVO;
    }

    /**
     * @param completionBasedOnList
     *            The completionBasedOnList to set.
     */
    public void setCompletionBasedOnList(List completionBasedOnList)
    {
        this.completionBasedOnList = completionBasedOnList;
    }

    /**
     * @return Returns the completionBasedOnList.
     */
    public List getCompletionBasedOnList()
    {
        return completionBasedOnList;
    }

    /**
     * @param dfltNotifyAlertList
     *            The dfltNotifyAlertList to set.
     */
    public void setDfltNotifyAlertList(List dfltNotifyAlertList)
    {
        this.dfltNotifyAlertList = dfltNotifyAlertList;
    }

    /**
     * @return Returns the dfltNotifyAlertList.
     */
    public List getDfltNotifyAlertList()
    {
        return dfltNotifyAlertList;
    }

    /**
     * @param stepOrder
     *            The stepOrder to set.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public void setStepOrderList(List stepOrder)
    {
        this.stepOrderList = stepOrder;
    }

    *//**
     * @return Returns the stepOrder.
     *//*
    public List getStepOrderList()
    {
        return stepOrderList;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.

    /**
     * @param caseStepAutRoutoToList
     *            The caseStepAutRoutoToList to set.
     */
    public void setCaseStepAutRoutoToList(List caseStepAutRoutoToList)
    {
        this.caseStepAutRoutoToList = caseStepAutRoutoToList;
    }

    /**
     * @return Returns the caseStepAutRoutoToList.
     */
    public List getCaseStepAutRoutoToList()
    {
        return caseStepAutRoutoToList;
    }

    /**
     * @param dfltAlertBasedOnList
     *            The dfltAlertBasedOnList to set.
     */
    public void setDfltAlertBasedOnList(List dfltAlertBasedOnList)
    {
        this.dfltAlertBasedOnList = dfltAlertBasedOnList;
    }

    /**
     * @return Returns the dfltAlertBasedOnList.
     */
    public List getDfltAlertBasedOnList()
    {
        return dfltAlertBasedOnList;
    }

    /**
     * @param dfltAlertSendBasedOnList
     *            The dfltAlertSendBasedOnList to set.
     */
    public void setDfltAlertSendBasedOnList(List dfltAlertSendBasedOnList)
    {
        this.dfltAlertSendBasedOnList = dfltAlertSendBasedOnList;
    }

    /**
     * @return Returns the dfltAlertSendBasedOnList.
     */
    public List getDfltAlertSendBasedOnList()
    {
        return dfltAlertSendBasedOnList;
    }

    /**
     * @param dfltTemplate
     *            The dfltTemplate to set.
     */
    //FOR THE HEAP DUMP ISSUE
   /* public void setDfltTemplateList(List dfltTemplate)
    {
        this.dfltTemplateList = dfltTemplate;
    }

    *//**
     * @return Returns the dfltTemplate.
     *//*
    public List getDfltTemplateList()
    {
       

        return dfltTemplateList;
    }*/

    /**
     * @param editCaseStepRowIndex
     *            The editCaseStepRowIndex to set.
     */
    public void setEditCaseStepRowIndex(int editCaseStepRowIndex)
    {
        this.editCaseStepRowIndex = editCaseStepRowIndex;
    }

    /**
     * @return Returns the editCaseStepRowIndex.
     */
    public int getEditCaseStepRowIndex()
    {
        return editCaseStepRowIndex;
    }

    /**
     * @param maintainCaseCustomFieldsList
     *            The maintainCaseCustomFieldsList to set.
     */
    public void setMaintainCaseCustomFieldsList(
             List maintainCaseCustomFieldsList)
    {
        this.maintainCaseCustomFieldsList = maintainCaseCustomFieldsList;
    }

    /**
     * @return Returns the maintainCaseCustomFieldsList.
     */
    public List getMaintainCaseCustomFieldsList()
    {
        return maintainCaseCustomFieldsList;
    }

    /**
     * @param customFieldVO
     *            The customFieldVO to set.
     */
    public void setCustomFieldVO(CustomFieldVO customFieldVO)
    {
        this.customFieldVO = customFieldVO;
    }

    /**
     * @return Returns the customFieldVO.
     */
    public CustomFieldVO getCustomFieldVO()
    {
        return customFieldVO;
    }

    /**
     * @param caseTypeEventVO
     *            The caseTypeEventVO to set.
     */
    public void setCaseTypeEventVO(CaseTypeEventVO caseTypeEventVO)
    {
        this.caseTypeEventVO = caseTypeEventVO;
    }

    /**
     * @return Returns the caseTypeEventVO.
     */
    public CaseTypeEventVO getCaseTypeEventVO()
    {
        return caseTypeEventVO;
    }

    /**
     * @param editCaseEventRowIndex
     *            The editCaseEventRowIndex to set.
     */
    public void setEditCaseEventRowIndex(int editCaseEventRowIndex)
    {
        this.editCaseEventRowIndex = editCaseEventRowIndex;
    }

    /**
     * @return Returns the editCaseEventRowIndex.
     */
    public int getEditCaseEventRowIndex()
    {
        return editCaseEventRowIndex;
    }

    /**
     * @param eventDfltTemplate
     *            The eventDfltTemplate to set.
     */
    //HEAP DUMP ISSUE
  /*  public void setEventDfltTemplate(List eventDfltTemplate)
    {
        this.eventDfltTemplate = eventDfltTemplate;
    }

    *//**
     * @return Returns the eventDfltTemplate.
     *//*
    public List getEventDfltTemplate()
    {
        return eventDfltTemplate;
    }*/

    /**
     * @param eventTypeList
     *            The eventTypeList to set.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public void setEventTypeList(List eventTypeList)
    {
        this.eventTypeList = eventTypeList;
    }

    *//**
     * @return Returns the eventTypeList.
     *//*
    public List getEventTypeList()
    {
        eventTypeList = new ArrayList();
        eventTypeList.add(new SelectItem("1", "Appointment"));
        eventTypeList.add(new SelectItem("2", "DDUAppointment"));
        return eventTypeList;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.

    /**
     * @param eventDfltAlertBasedOnList
     *            The eventDfltAlertBasedOnList to set.
     */
    public void setEventDfltAlertBasedOnList(
             List eventDfltAlertBasedOnList)
    {
        this.eventDfltAlertBasedOnList = eventDfltAlertBasedOnList;
    }

    /**
     * @return Returns the eventDfltAlertBasedOnList.
     */
    public List getEventDfltAlertBasedOnList()
    {
        return eventDfltAlertBasedOnList;
    }

    /**
     * @param imageRender
     *            The imageRender to set.
     */
    public void setImageRender(String imageRender)
    {
        this.imageRender = imageRender;
    }

    /**
     * @return Returns the imageRender.
     */
    public String getImageRender()
    {
        return imageRender;
    }

    /**
     * @param noCaseStepsData
     *            The noCaseStepsData to set.
     */
    public void setNoCaseStepsData(boolean noCaseStepsData)
    {
        this.noCaseStepsData = noCaseStepsData;
    }

    /**
     * @return Returns the noCaseStepsData.
     */
    public boolean isNoCaseStepsData()
    {
        return noCaseStepsData;
    }

    /**
     * @param noCaseEventsData
     *            The noCaseEventsData to set.
     */
    public void setNoCaseEventsData(boolean noCaseEventsData)
    {
        this.noCaseEventsData = noCaseEventsData;
    }

    /**
     * @return Returns the noCaseEventsData.
     */
    public boolean isNoCaseEventsData()
    {
        return noCaseEventsData;
    }

    /**
     * @param nocustomfieldsData
     *            The nocustomfieldsData to set.
     */
    public void setNocustomfieldsData(boolean nocustomfieldsData)
    {
        this.nocustomfieldsData = nocustomfieldsData;
    }

    /**
     * @return Returns the nocustomfieldsData.
     */
    public boolean isNocustomfieldsData()
    {
        return nocustomfieldsData;
    }

    /**
     * @param noCaseTypesData
     *            The noCaseTypesData to set.
     */
    public void setNoCaseTypesData(boolean noCaseTypesData)
    {
        this.noCaseTypesData = noCaseTypesData;
    }

    /**
     * @return Returns the noCaseTypesData.
     */
    public boolean isNoCaseTypesData()
    {
        return noCaseTypesData;
    }

    /**
     * @param caseTypeAuditRender
     *            The caseTypeAuditRender to set.
     */
    public void setCaseTypeAuditRender(boolean caseTypeAuditRender)
    {
        this.caseTypeAuditRender = caseTypeAuditRender;
    }

    /**
     * @return Returns the caseTypeAuditRender.
     */
    public boolean isCaseTypeAuditRender()
    {
        return caseTypeAuditRender;
    }

    /**
     * @param caseTypeAuditHistoryList
     *            The caseTypeAuditHistoryList to set.
     */
    public void setCaseTypeAuditHistoryList(List caseTypeAuditHistoryList)
    {
        this.caseTypeAuditHistoryList = caseTypeAuditHistoryList;
    }

    /**
     * @return Returns the caseTypeAuditHistoryList.
     */
    public List getCaseTypeAuditHistoryList()
    {
        return caseTypeAuditHistoryList;
    }

    /**
     * @param auditOpen
     *            The auditOpen to set.
     */
    public void setAuditOpen(boolean auditOpen)
    {
        this.auditOpen = auditOpen;
    }

    /**
     * @return Returns the auditOpen.
     */
    public boolean isAuditOpen()
    {
        return auditOpen;
    }

    /**
     * @param stepDescriptionList
     *            The stepDescriptionList to set.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public void setStepDescriptionList(List stepDescriptionList)
    {
        this.stepDescriptionList = stepDescriptionList;
    }

    *//**
     * @return Returns the stepDescriptionList.
     *//*
    public List getStepDescriptionList()
    {
        return stepDescriptionList;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.

    /**
     * @param eventDfltSendAlertList
     *            The eventDfltSendAlertList to set.
     */
    public void setEventDfltSendAlertList(List eventDfltSendAlertList)
    {
        this.eventDfltSendAlertList = eventDfltSendAlertList;
    }

    /**
     * @return Returns the eventDfltSendAlertList.
     */
    public List getEventDfltSendAlertList()
    {
        return eventDfltSendAlertList;
    }

    /**
     * @param eventDfltNotifyAlertList
     *            The eventDfltNotifyAlertList to set.
     */
    public void setEventDfltNotifyAlertList(List eventDfltNotifyAlertList)
    {
        this.eventDfltNotifyAlertList = eventDfltNotifyAlertList;
    }

    /**
     * @return Returns the eventDfltNotifyAlertList.
     */
    public List getEventDfltNotifyAlertList()
    {
        return eventDfltNotifyAlertList;
    }

    /**
     * @param maintainCaseTemplatesList
     *            The maintainCaseTemplatesList to set.
     */
    public void setMaintainCaseTemplatesList(
             List maintainCaseTemplatesList)
    {
        this.maintainCaseTemplatesList = maintainCaseTemplatesList;
    }

    /**
     * @return Returns the maintainCaseTemplatesList.
     */
    public List getMaintainCaseTemplatesList()
    {
        return maintainCaseTemplatesList;
    }

    /**
     * @return Returns the caseTypeTemplatesVO.
     */
    public CaseTypeLetterTemplateVO getCaseTypeTemplatesVO()
    {
        return caseTypeTemplatesVO;
    }

    /**
     * @param caseTypeTemplatesVO
     *            The caseTypeTemplatesVO to set.
     */
    public void setCaseTypeTemplatesVO(
            CaseTypeLetterTemplateVO caseTypeTemplatesVO)
    {
        this.caseTypeTemplatesVO = caseTypeTemplatesVO;
    }

    /**
     * @param noTemplatesData
     *            The noTemplatesData to set.
     */
    public void setNoTemplatesData(boolean noTemplatesData)
    {
        this.noTemplatesData = noTemplatesData;
    }

    /**
     * @return Returns the noTemplatesData.
     */
    public boolean isNoTemplatesData()
    {
        return noTemplatesData;
    }

    /**
     * @return Returns the descnCaseStepList.
     */
    public List getDescnCaseStepList()
    {
        return descnCaseStepList;
    }

    /**
     * @param descnCaseStepList
     *            The descnCaseStepList to set.
     */
    public void setDescnCaseStepList(List descnCaseStepList)
    {
        this.descnCaseStepList = descnCaseStepList;
    }

    /**
     * @return Returns the orderCaseStepList.
     */
    public List getOrderCaseStepList()
    {
        return orderCaseStepList;
    }

    /**
     * @param orderCaseStepList
     *            The orderCaseStepList to set.
     */
    public void setOrderCaseStepList(List orderCaseStepList)
    {
        this.orderCaseStepList = orderCaseStepList;
    }

    /**
     * @return Returns the caseEventTypeList.
     */
    public List getCaseEventTypeList()
    {
        return caseEventTypeList;
    }

    /**
     * @param caseEventTypeList
     *            The caseEventTypeList to set.
     */
    public void setCaseEventTypeList(List caseEventTypeList)
    {
        this.caseEventTypeList = caseEventTypeList;
    }

    /**
     * @return Returns the caseTypeEventsList.
     */
    public List getCaseTypeEventsList()
    {
        return caseTypeEventsList;
    }

    /**
     * @param caseTypeEventsList
     *            The caseTypeEventsList to set.
     */
    public void setCaseTypeEventsList(List caseTypeEventsList)
    {
        this.caseTypeEventsList = caseTypeEventsList;
    }

    /**
     * @return Returns the caseTypeStepsList.
     */
    public List getCaseTypeStepsList()
    {
        return caseTypeStepsList;
    }

    /**
     * @param caseTypeStepsList
     *            The caseTypeStepsList to set.
     */
    public void setCaseTypeStepsList(List caseTypeStepsList)
    {
        this.caseTypeStepsList = caseTypeStepsList;
    }

   

   
	
	
	 

	
	/*private boolean isNotNullAndNotEmptyList(List list) 
	{
		return (list != null && (!list.isEmpty()));
	}*/
    /**
     * @return Returns the incudeflag.
     */
    public boolean isIncudeflag()
    {
        return incudeflag;
    }

    /**
     * @param incudeflag
     *            The incudeflag to set.
     */
    public void setIncudeflag(boolean incudeflag)
    {
        this.incudeflag = incudeflag;
    }

    /**
     * @return Returns the includeRadioList.
     */
    // Begin - commented unused method for the defect id ESPRD00723971_21NOV2011.
    /*public List getIncludeRadioList()
    {
        return includeRadioList;
    }

    *//**
     * @param includeRadioList
     *            The includeRadioList to set.
     *//*
    public void setIncludeRadioList(List includeRadioList)
    {
        this.includeRadioList = includeRadioList;
    }*/
    // End - commented unused method for the defect id ESPRD00723971_21NOV2011.

    /**
     * @return Returns the beforeAfterRadioList.
     */
    public List getBeforeAfterRadioList()
    {
        return beforeAfterRadioList;
    }

    /**
     * @param beforeAfterRadioList
     *            The beforeAfterRadioList to set.
     */
    public void setBeforeAfterRadioList(List beforeAfterRadioList)
    {
        this.beforeAfterRadioList = beforeAfterRadioList;
    }

    
    /**
     * @return Returns the unSelectedCustomFields.
     */
    public List getUnSelectedCustomFields()
    {
        return unSelectedCustomFields;
    }
    /**
     * @param unSelectedCustomFields The unSelectedCustomFields to set.
     */
    public void setUnSelectedCustomFields(List unSelectedCustomFields)
    {
        this.unSelectedCustomFields = unSelectedCustomFields;
    }
    /**
     * @return Returns the unSelectedTemplates.
     */
    public List getUnSelectedTemplates()
    {
        return unSelectedTemplates;
    }
    /**
     * @param unSelectedTemplates The unSelectedTemplates to set.
     */
    public void setUnSelectedTemplates(List unSelectedTemplates)
    {
        this.unSelectedTemplates = unSelectedTemplates;
    }
    /**
     * @return Returns the caseEventNoIncludeList.
     */
    public List getCaseEventNoIncludeList()
    {
        return caseEventNoIncludeList;
    }
    /**
     * @param caseEventNoIncludeList The caseEventNoIncludeList to set.
     */
    public void setCaseEventNoIncludeList(List caseEventNoIncludeList)
    {
        this.caseEventNoIncludeList = caseEventNoIncludeList;
    }
   
    /**
     * @return Returns the caseStepNoIncludeList.
     */
    public List getCaseStepNoIncludeList()
    {
        return caseStepNoIncludeList;
    }
    /**
     * @param caseStepNoIncludeList The caseStepNoIncludeList to set.
     */
    public void setCaseStepNoIncludeList(List caseStepNoIncludeList)
    {
        this.caseStepNoIncludeList = caseStepNoIncludeList;
    }
    /**
     * @return Returns the templateDropDownList.
     */
    public List getTemplateDropDownList()
    {
        return templateDropDownList;
    }
    /**
     * @param templateDropDownList The templateDropDownList to set.
     */
    public void setTemplateDropDownList(List templateDropDownList)
    {
        this.templateDropDownList = templateDropDownList;
    }
    /**
     * @return Returns the userID.
     */
    public String getUserID()
    {
        return userID;
    }
    /**
     * @param userID The userID to set.
     */
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    /**
     * @return Returns the customFieldSelected.
     */
    public boolean isCustomFieldSelected()
    {
        return customFieldSelected;
    }
    /**
     * @param customFieldSelected The customFieldSelected to set.
     */
    public void setCustomFieldSelected(boolean customFieldSelected)
    {
        this.customFieldSelected = customFieldSelected;
    }
    /**
     * @return Returns the userWorkUnitList.
     */
    public List getUserWorkUnitList()
    {
        return userWorkUnitList;
    }
    /**
     * @param userWorkUnitList The userWorkUnitList to set.
     */
    public void setUserWorkUnitList(List userWorkUnitList)
    {
        this.userWorkUnitList = userWorkUnitList;
    }
    /**
     * @return Returns the updateFlag.
     */
    public boolean isUpdateFlag()
    {
        return updateFlag;
    }
    /**
     * @param updateFlag The updateFlag to set.
     */
    public void setUpdateFlag(boolean updateFlag)
    {
        this.updateFlag = updateFlag;
    }
    /**
     * @return Returns the showDeleteMessage.
     */
    public boolean isShowDeleteMessage()
    {
        return showDeleteMessage;
    }
    /**
     * @param showDeleteMessage The showDeleteMessage to set.
     */
    public void setShowDeleteMessage(boolean showDeleteMessage)
    {
        this.showDeleteMessage = showDeleteMessage;
    }
    /**
     * @return Returns the showDeleteFlag.
     */
    public boolean isShowDeleteFlag()
    {
        return showDeleteFlag;
    }
    /**
     * @param showDeleteFlag The showDeleteFlag to set.
     */
    public void setShowDeleteFlag(boolean showDeleteFlag)
    {
        this.showDeleteFlag = showDeleteFlag;
    }
    /**
     * @return Returns the showDescOrderFlag.
     */
    public boolean isShowDescOrderFlag()
    {
        return showDescOrderFlag;
    }
    /**
     * @param showDescOrderFlag The showDescOrderFlag to set.
     */
    public void setShowDescOrderFlag(boolean showDescOrderFlag)
    {
        this.showDescOrderFlag = showDescOrderFlag;
    }
    /**
     * @return Returns the showTypeCodeFlag.
     */
    public boolean isShowTypeCodeFlag()
    {
        return showTypeCodeFlag;
    }
    /**
     * @param showTypeCodeFlag The showTypeCodeFlag to set.
     */
    public void setShowTypeCodeFlag(boolean showTypeCodeFlag)
    {
        this.showTypeCodeFlag = showTypeCodeFlag;
    }
    /**
     * @return Returns the showTypeDescFlag.
     */
    public boolean isShowTypeDescFlag()
    {
        return showTypeDescFlag;
    }
    /**
     * @param showTypeDescFlag The showTypeDescFlag to set.
     */
    public void setShowTypeDescFlag(boolean showTypeDescFlag)
    {
        this.showTypeDescFlag = showTypeDescFlag;
    }
    /**
     * @return Returns the showStepSuccessFlag.
     */
    public boolean isShowStepSuccessMsg()
    {
        return showStepSuccessMsg;
    }
    /**
     * @param showStepSuccessFlag The showStepSuccessFlag to set.
     */
    public void setShowStepSuccessMsg(boolean showStepSuccessMsg)
    {
        this.showStepSuccessMsg = showStepSuccessMsg;
    }
    /**
     * @return Returns the caseTypeNoActive.
     */
    public boolean isCaseTypeNoActive()
    {
        return caseTypeNoActive;
    }
    /**
     * @param caseTypeNoActive The caseTypeNoActive to set.
     */
    public void setCaseTypeNoActive(boolean caseTypeNoActive)
    {
        this.caseTypeNoActive = caseTypeNoActive;
    }
    /**
     * @return Returns the showEventActMsg.
     */
    public boolean isShowEventActMsg()
    {
        return showEventActMsg;
    }
    /**
     * @param showEventActMsg The showEventActMsg to set.
     */
    public void setShowEventActMsg(boolean showEventActMsg)
    {
        this.showEventActMsg = showEventActMsg;
    }
    /**
     * @return Returns the showStepActMsg.
     */
    public boolean isShowStepActMsg()
    {
        return showStepActMsg;
    }
    /**
     * @param showStepActMsg The showStepActMsg to set.
     */
    public void setShowStepActMsg(boolean showStepActMsg)
    {
        this.showStepActMsg = showStepActMsg;
    }
	/**
	 * @return Returns the disableAddLink.
	 */
	public boolean isDisableAddLink() {
		return disableAddLink;
	}
	/**
	 * @param disableAddLink The disableAddLink to set.
	 */
	public void setDisableAddLink(boolean disableAddLink) {
		this.disableAddLink = disableAddLink;
	}
	/**
	 * @return Returns the userMap.
	 */
	public Map getUserMap() {
		return userMap;
	}
	/**
	 * @param userMap The userMap to set.
	 */
	public void setUserMap(Map userMap) {
		this.userMap = userMap;
	}
	/**
	 * @return Returns the deleteLink.
	 */
	public boolean isDeleteLink() {
		return deleteLink;
	}
	/**
	 * @param deleteLink The deleteLink to set.
	 */
	public void setDeleteLink(boolean deleteLink) {
		this.deleteLink = deleteLink;
	}
	/**
	 * @return Returns the updateLink.
	 */
	public boolean isUpdateLink() {
		return updateLink;
	}
	/**
	 * @param updateLink The updateLink to set.
	 */
	public void setUpdateLink(boolean updateLink) {
		this.updateLink = updateLink;
	}
	/**
	 * @return Returns the resetLink.
	 */
	public boolean isResetLink() {
		return resetLink;
	}
	/**
	 * @param resetLink The resetLink to set.
	 */
	public void setResetLink(boolean resetLink) {
		this.resetLink = resetLink;
	}
	
	/** used to enable / disable the screen fields */
    private boolean disableScreenField = false;
	/**
	 * @return Returns the disableScreenField.
	 */
	public boolean isDisableScreenField() {
		return disableScreenField;
	}
	/**
	 * @param disableScreenField The disableScreenField to set.
	 */
	public void setDisableScreenField(boolean disableScreenField) {
		this.disableScreenField = disableScreenField;
	}
	// srikanth
	private boolean disableSaveCasTyp = false; 
	
	/**
	 * @return Returns the disableSaveMainCasTyp.
	 */
	public boolean isDisableSaveCasTyp() {
		return disableSaveCasTyp;
	}
	/**
	 * @param disableSaveMainCasTyp The disableSaveMainCasTyp to set.
	 */
	public void setDisableSaveCasTyp(boolean disableSaveCasTyp) {
		this.disableSaveCasTyp = disableSaveCasTyp;
	}
	
	private boolean disableResetCasTyp=false;
	/**
	 * @return Returns the disableResetEditCasTyp.
	 */
	public boolean isDisableResetCasTyp() {
		return disableResetCasTyp;
	}
	/**
	 * @param disableResetEditCasTyp The disableResetEditCasTyp to set.
	 */
	public void setDisableResetCasTyp(boolean disableResetCasTyp) {
		this.disableResetCasTyp = disableResetCasTyp;
	}
	private boolean disableDeleteCasTyp=false;
	
	
	/**
	 * @return Returns the disableDeleteCasTyp.
	 */
	public boolean isDisableDeleteCasTyp() {
		return disableDeleteCasTyp;
	}
	/**
	 * @param disableDeleteCasTyp The disableDeleteCasTyp to set.
	 */
	public void setDisableDeleteCasTyp(boolean disableDeleteCasTyp) {
		this.disableDeleteCasTyp = disableDeleteCasTyp;
	}
	
	private boolean disableAddCaseEvent=false;
	
	/**
	 * @return Returns the disableAddCaseEvent.
	 */
	public boolean isDisableAddCaseEvent() {
		return disableAddCaseEvent;
	}
	/**
	 * @param disableAddCaseEvent The disableAddCaseEvent to set.
	 */
	public void setDisableAddCaseEvent(boolean disableAddCaseEvent) {
		this.disableAddCaseEvent = disableAddCaseEvent;
	}
	
	private boolean disableEditAddCaseEventSave=false;
	/**
	 * @return Returns the disableEditAddCaseEventSave.
	 */
	public boolean isDisableEditAddCaseEventSave() {
		return disableEditAddCaseEventSave;
	}
	/**
	 * @param disableEditAddCaseEventSave The disableEditAddCaseEventSave to set.
	 */
	public void setDisableEditAddCaseEventSave(
			boolean disableEditAddCaseEventSave) {
		this.disableEditAddCaseEventSave = disableEditAddCaseEventSave;
	}
	
	private boolean disableEditCaseStepSave=false;
	
	/**
	 * @return Returns the disableEditCaseStepSave.
	 */
	public boolean isDisableEditCaseStepSave() {
		return disableEditCaseStepSave;
	}
	/**
	 * @param disableEditCaseStepSave The disableEditCaseStepSave to set.
	 */
	public void setDisableEditCaseStepSave(boolean disableEditCaseStepSave) {
		this.disableEditCaseStepSave = disableEditCaseStepSave;
	}
	
	//Ankush
	/**
     * Holds boolean value of disableAddLink
     */
    private boolean disableAddCaseType;

     /**
     * Holds boolean value of disableAddcaseStep
     */
     private boolean disableAddcaseStep;
     /**
      * Holds boolean value of disableaddCaseTypereset
      */
     private boolean disableaddCaseTypereset= false;
     /**
      * Holds boolean value of disableaddSave
      */
     private boolean disableaddSave;




	/**
	 * @return Returns the disableaddSave.
	 */
	public boolean isDisableaddSave() {
		return disableaddSave;
	}
	/**
	 * @param disableaddSave The disableaddSave to set.
	 */
	public void setDisableaddSave(boolean disableaddSave) {
		this.disableaddSave = disableaddSave;
	}


        	/**
	 * @return Returns the disableaddCaseTypereset.
	 */
	public boolean isDisableaddCaseTypereset() {
		return disableaddCaseTypereset;
	}
	/**
	 * @param disableaddCaseTypereset The disableaddCaseTypereset to set.
	 */
	public void setDisableaddCaseTypereset(boolean disableaddCaseTypereset) {
		this.disableaddCaseTypereset = disableaddCaseTypereset;
	}

       /**
	 * @return Returns the disableAddcaseStep.
	 */
	public boolean isDisableAddcaseStep() {
		return disableAddcaseStep;
	}
	/**
	 * @param disableAddcaseStep The disableAddcaseStep to set.
	 */
	public void setDisableAddcaseStep(boolean disableAddcaseStep) {
		this.disableAddcaseStep = disableAddcaseStep;
	}


         /**
	 * @return Returns the disableAddCaseType
	 */
	public boolean isDisableAddCaseType() {
		return disableAddCaseType;
	}


         /**
	 * @param disableAddCaseType The disableAddCaseType to set.
	 */
	public void setDisableAddCaseType(boolean disableAddCaseType) {
		this.disableAddCaseType = disableAddCaseType;
	}
	 /**
     * Flag to hold EventsSuccess message
     */
    private boolean showEventsSuccessMsg = false;

	/**
	 * @return Returns the showEventsSuccessMsg.
	 */
	public boolean isShowEventsSuccessMsg() {
		return showEventsSuccessMsg;
	}
	/**
	 * @param showEventsSuccessMsg The showEventsSuccessMsg to set.
	 */
	public void setShowEventsSuccessMsg(boolean showEventsSuccessMsg) {
		this.showEventsSuccessMsg = showEventsSuccessMsg;
	}
	/**
	 * @return Returns the valiValueFlag.
	 */
	public boolean isValiValueFlag() {
		return valiValueFlag;
	}
	/**
	 * @param valiValueFlag The valiValueFlag to set.
	 */
	public void setValiValueFlag(boolean valiValueFlag) {
		this.valiValueFlag = valiValueFlag;
	}
	/**
     * Flag to hold call to row details 
     */
	private boolean rowDetailsFlag = true;
	
	
	/**
	 * @return Returns the rowDetailsFlag.
	 */
	public boolean isRowDetailsFlag() {
		return rowDetailsFlag;
	}
	/**
	 * @param rowDetailsFlag The rowDetailsFlag to set.
	 */
	public void setRowDetailsFlag(boolean rowDetailsFlag) {
		this.rowDetailsFlag = rowDetailsFlag;
	}
	//CFR 
	private String maintainCaseTypeFocusId;
	
	/**
	 * @return Returns the maintainCaseTypeFocusId.
	 */
	public String getMaintainCaseTypeFocusId() {
		//System.err.println("+++maintainCaseTypeFocusId ::"+maintainCaseTypeFocusId);
		return maintainCaseTypeFocusId;
	}
	/**
	 * @param maintainCaseTypeFocusId The maintainCaseTypeFocusId to set.
	 */
	public void setMaintainCaseTypeFocusId(String maintainCaseTypeFocusId) {
		this.maintainCaseTypeFocusId = maintainCaseTypeFocusId;
	}
	//CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
	private boolean enableMaintainCaseTypeAuditLog = false;

	/**
	 * @return Returns the enableMaintainCaseTypeAuditLog.
	 */
	public boolean isEnableMaintainCaseTypeAuditLog() {
		return enableMaintainCaseTypeAuditLog;
	}
	/**
	 * @param enableMaintainCaseTypeAuditLog The enableMaintainCaseTypeAuditLog to set.
	 */
	public void setEnableMaintainCaseTypeAuditLog(
			boolean enableMaintainCaseTypeAuditLog) {
		this.enableMaintainCaseTypeAuditLog = enableMaintainCaseTypeAuditLog;
	}
	//	EOF CR_ESPRD00373565_MaintainCaseTypes_06AUG2010
		//CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
		private String firstFieldFocusID = "";
	/**
	 * @return Returns the firstFieldFocusID.
	 */
	public String getFirstFieldFocusID() {
		return firstFieldFocusID;
	}
	/**
	 * @param firstFieldFocusID The firstFieldFocusID to set.
	 */
	public void setFirstFieldFocusID(String firstFieldFocusID) {
		this.firstFieldFocusID = firstFieldFocusID;
	}
	//EOF CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
	//ESPRD00527652_UC-PGM-CRM-48_28SEP2010
	private Map usersWithWorkUnits = new Hashtable();
	
	
	/**
	 * @return Returns the usersWithWorkUnits.
	 */
	public Map getUsersWithWorkUnits() {
		return usersWithWorkUnits;
	}
	/**
	 * @param usersWithWorkUnits The usersWithWorkUnits to set.
	 */
	public void setUsersWithWorkUnits(Map usersWithWorkUnits) {
		this.usersWithWorkUnits = usersWithWorkUnits;
	}
	
	private List caseEventTypeListAll = new ArrayList();
	
	//EOF ESPRD00527652_UC-PGM-CRM-48_28SEP2010
	/**
	 * @return Returns the caseEventTypeListAll.
	 */
	public List getCaseEventTypeListAll() {
		return caseEventTypeListAll;
	}
	/**
	 * @param caseEventTypeListAll The caseEventTypeListAll to set.
	 */
	public void setCaseEventTypeListAll(List caseEventTypeListAll) {
		this.caseEventTypeListAll = caseEventTypeListAll;
	}
	
	private List eventDfltAlertBasedOnListAll = new ArrayList();
	/**
	 * @return Returns the eventDfltAlertBasedOnListAll.
	 */
	public List getEventDfltAlertBasedOnListAll() {
		return eventDfltAlertBasedOnListAll;
	}
	/**
	 * @param eventDfltAlertBasedOnListAll The eventDfltAlertBasedOnListAll to set.
	 */
	public void setEventDfltAlertBasedOnListAll(List eventDfltAlertBasedOnListAll) {
		this.eventDfltAlertBasedOnListAll = eventDfltAlertBasedOnListAll;
	}
	
	private List dfltAlertBasedOnListAll = new ArrayList();
	/**
	 * @return Returns the dfltAlertBasedOnListAll.
	 */
	public List getDfltAlertBasedOnListAll() {
		return dfltAlertBasedOnListAll;
	}
	/**
	 * @param dfltAlertBasedOnListAll The dfltAlertBasedOnListAll to set.
	 */
	public void setDfltAlertBasedOnListAll(List dfltAlertBasedOnListAll) {
		this.dfltAlertBasedOnListAll = dfltAlertBasedOnListAll;
	}
	
	private List completionBasedOnListAll = new ArrayList();
	/**
	 * @return Returns the completionBasedOnListAll.
	 */
	public List getCompletionBasedOnListAll() {
		return completionBasedOnListAll;
	}
	/**
	 * @param completionBasedOnListAll The completionBasedOnListAll to set.
	 */
	public void setCompletionBasedOnListAll(List completionBasedOnListAll) {
		this.completionBasedOnListAll = completionBasedOnListAll;
	}
	
	private String warnBeforeExitStatus;
	/**
	 * @return Returns the warnBeforeExitStatus.
	 */
	public String getWarnBeforeExitStatus() {
		return warnBeforeExitStatus;
	}
	/**
	 * @param warnBeforeExitStatus The warnBeforeExitStatus to set.
	 */
	public void setWarnBeforeExitStatus(String warnBeforeExitStatus) {
		this.warnBeforeExitStatus = warnBeforeExitStatus;
	}
	/**
	 * Flag to hold EventsDelete message
	 */
	//added for the defect ESPRD00561174
	private boolean showEventsDeleteMsg = false;
	
	public boolean isShowEventsDeleteMsg() {
		return showEventsDeleteMsg;
	}
	
	public void setShowEventsDeleteMsg(boolean showEventsDeleteMsg) {
		this.showEventsDeleteMsg = showEventsDeleteMsg;
	}
	/**
	 * Flag to hold StepDelete message
	 */
	//added for the defect ESPRD00561174
	private boolean showStepDeleteMsg = false;
	
	
	public boolean isShowStepDeleteMsg() {
		return showStepDeleteMsg;
	}
	
	public void setShowStepDeleteMsg(boolean showStepDeleteMsg) {
		this.showStepDeleteMsg = showStepDeleteMsg;
	}
	/**
	 * Holds boolean value of controlRequired
	 */
	 private boolean controlRequired;
	
	/**
	 * @return the controlRequired
	 */
	public boolean isControlRequired() {
		return controlRequired;
	}
	
	/**
	 * @param controlRequired the controlRequired to set
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}

	// Begin - Modified for the HeapDump Fix.
	/**
	 * Stores a refernce to Case Event Data Table.
	 */
	private int maintainCFDataTable;
	
	/**
	 * Stores a refernce to Maintain CaseType templates Data Table.
	 */
	private int maintainTemplatesDataTable;
	
	/**
	 * @return the maintainCFDataTable
	 */
	public int getMaintainCFDataTable() {
		return maintainCFDataTable;
	}
	
	/**
	 * @param maintainCFDataTable the maintainCFDataTable to set
	 */
	public void setMaintainCFDataTable(int maintainCFDataTable) {
		this.maintainCFDataTable = maintainCFDataTable;
	}
	
	/**
	 * @return the maintainTemplatesDataTable
	 */
	public int getMaintainTemplatesDataTable() {
		return maintainTemplatesDataTable;
	}
	
	/**
	 * @param maintainTemplatesDataTable the maintainTemplatesDataTable to set
	 */
	public void setMaintainTemplatesDataTable(int maintainTemplatesDataTable) {
		this.maintainTemplatesDataTable = maintainTemplatesDataTable;
	}
	//End - Modified for the HeapDump Fix.
	
	// Begin - Added the below code for the defect id ESPRD00723971_05DEC2011
	private boolean caseTypeCaseStepsFlag = true;
	private boolean caseTypeCaseEventsFlag = false;
	private boolean caseTypeCustomFieldsFlag = false;
	private boolean caseTypeTemplatesFlag = false;

	public boolean isCaseTypeCaseStepsFlag() {
		return caseTypeCaseStepsFlag;
	}

	public void setCaseTypeCaseStepsFlag(boolean caseTypeCaseStepsFlag) {
		this.caseTypeCaseStepsFlag = caseTypeCaseStepsFlag;
	}

	public boolean isCaseTypeCaseEventsFlag() {
		return caseTypeCaseEventsFlag;
	}

	public void setCaseTypeCaseEventsFlag(boolean caseTypeCaseEventsFlag) {
		this.caseTypeCaseEventsFlag = caseTypeCaseEventsFlag;
	}

	public boolean isCaseTypeCustomFieldsFlag() {
		return caseTypeCustomFieldsFlag;
	}

	public void setCaseTypeCustomFieldsFlag(boolean caseTypeCustomFieldsFlag) {
		this.caseTypeCustomFieldsFlag = caseTypeCustomFieldsFlag;
	}

	public boolean isCaseTypeTemplatesFlag() {
		return caseTypeTemplatesFlag;
	}

	public void setCaseTypeTemplatesFlag(boolean caseTypeTemplatesFlag) {
		this.caseTypeTemplatesFlag = caseTypeTemplatesFlag;
	}
	
	
	// End - Added the below code for the defect id ESPRD00723971_05DEC2011
	
	/** Holds the first attribute value in DataTable tag for CaseType.
	 * */
	private int startIndexForCaseType;
	
	/** Holds the first attribute value in DataTable tag for Steps.
	 * */
	private int startIndexForCaseTypeSteps;
	
	/** Holds the first attribute value in DataTable tag for Events.
	 * */
	private int startIndexForCaseTypeEvents;

	/**
	 * @return the startIndexForCaseType
	 */
	public int getStartIndexForCaseType() {
		return startIndexForCaseType;
	}

	/**
	 * @param startIndexForCaseType the startIndexForCaseType to set
	 */
	public void setStartIndexForCaseType(int startIndexForCaseType) {
		this.startIndexForCaseType = startIndexForCaseType;
	}

	/**
	 * @return the startIndexForCaseTypeSteps
	 */
	public int getStartIndexForCaseTypeSteps() {
		return startIndexForCaseTypeSteps;
	}

	/**
	 * @param startIndexForCaseTypeSteps the startIndexForCaseTypeSteps to set
	 */
	public void setStartIndexForCaseTypeSteps(int startIndexForCaseTypeSteps) {
		this.startIndexForCaseTypeSteps = startIndexForCaseTypeSteps;
	}

	/**
	 * @return the startIndexForCaseTypeEvents
	 */
	public int getStartIndexForCaseTypeEvents() {
		return startIndexForCaseTypeEvents;
	}

	/**
	 * @param startIndexForCaseTypeEvents the startIndexForCaseTypeEvents to set
	 */
	public void setStartIndexForCaseTypeEvents(int startIndexForCaseTypeEvents) {
		this.startIndexForCaseTypeEvents = startIndexForCaseTypeEvents;
	}

	/**To enable or disable
	 * the UI fields.
	 * */
	private boolean disableUIFields;

	/**
	 * @return the disableUIFields
	 */
	public boolean isDisableUIFields() {
		return disableUIFields;
	}

	/**
	 * @param disableUIFields the disableUIFields to set
	 */
	public void setDisableUIFields(boolean disableUIFields) {
		this.disableUIFields = disableUIFields;
	}

	
	
}
