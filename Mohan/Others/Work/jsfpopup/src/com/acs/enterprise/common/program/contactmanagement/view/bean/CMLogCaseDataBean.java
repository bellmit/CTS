/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AlertVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AlternateIdentifiersVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCaseVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCorrespondenceVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseEventsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingMemberVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingProviderVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingTPL;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseRegardingVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseStepsVO;

import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class is used to hold the value objects and flags used in UI and other
 * information related to Log Case.
 * 
 * @author Wipro
 */
public class CMLogCaseDataBean
        extends EnterpriseBaseDataBean
{
    

    private Integer firstCaseStepSeqNum = Integer.valueOf(0); /*FIND BUGS_FIX*/
    private Integer firstCaseEventSeqNum = Integer.valueOf(0);  /*FIND BUGS_FIX*/
    private boolean validValuesFlag=true;
    private boolean  SaveRoutingFlag = false;
    private boolean  fileBigSavedFlag = false;
    private boolean  SaveAlertsFlag = false;
	private boolean  SaveCaseEventFlag = false;
	private boolean  SaveCaseStepFlag = false;
    private boolean renderedroutingFlag = false;
	private boolean renderedalertsFlag = false;
    private boolean renderedCaseEventsflag=false;
    private boolean renderedCaseStepsflag=false;
    private boolean renderInputDateOnRadioYes= false;
    /** holds the boolean flag for showing that the record is saved */
    private boolean showSucessMessage = false;
    
    /** holds the boolean flag for showing that the record is saved */
    private boolean showCaseType = false;
    
    private boolean  disableLogCaseSave;
    private boolean  disableLogCaseReset;
    private boolean  disableLogAddCaseAssocCrspondence;
    private boolean  disableDeleteCorrespondingRecAssocWithCase;
    private boolean  disableDeleteCaseRecAssocWithCase;
    
    private boolean disableAddAttachment;
    private boolean disableAttachmentSave;
    private boolean disableAttachmentReset;
    private boolean disableAttachmentDetach;
    private boolean disableAttachmentDelete;
    
    private boolean disableAddCaseEvents;
    private boolean disableCaseEventSave;
    private boolean disableCaseEventReset;
    private boolean disableCaseEventDelete;
    
    private boolean disableAddAlert;
    private boolean disableAlertSave;
    
    private boolean disableAddCaseSteps;
    private boolean disableCaseStepDelete;
    private boolean disableCaseStepReset;
    private boolean disableCaseStepSave;
	//Added for the Defect ESPRD00734517 Start
    private boolean disableSearchDocumentRepository;
    
    public boolean isDisableSearchDocumentRepository() {
		return disableSearchDocumentRepository;
	}
	public void setDisableSearchDocumentRepository(
			boolean disableSearchDocumentRepository) {
		this.disableSearchDocumentRepository = disableSearchDocumentRepository;
	}
	//Added for the Defect ESPRD00734517 Ends
	private boolean openDays = true;
    
    /**
     * to hold the  edmsWrkUnitLevelList 
     */
    private List edmsWrkUnitLevelList;
    /**
     * Holds noData.
     */
    private boolean noData = false;
    
    /** holds the boolean flag for showing the UserDept */
    private boolean showUserDept = false;
    
    /** holds the boolean flag for showing the WorkUnit */
    private boolean showWorkUnit = false;
    
    /**
     * List to hold values of eventToRemove.
     */
    private List eventToRemove = new ArrayList();
    
    /**
     * List to hold values of stepToRemove.
     */
    private List stepToRemove = new ArrayList();

    
    /** Added  For the defect ID ESPRD00334100*/
    
    /**
     * List to hold values of Dropdown Entity Type.
     */
    private List entityTypeVVList = new ArrayList();
  //Commented for HeapDump Issue
    /*private List entityTypeVVList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    	
	 /**
     * List to hold values of Dropdown Status.
     */
    private List statusVVList = new ArrayList();
  //Commented for HeapDump Issue
    /* private List statusVVList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/

    /**
     * List to hold values of Dropdown Priority.
     */
    private List priorityVVList = new ArrayList();
  //Commented for HeapDump Issue
    /* private List priorityVVList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/

    /**
     * List to hold values of Dropdown Resolution.
     */
    private List resolnVVList = new ArrayList();
  //Commented for HeapDump Issue
    /*private List resolnVVList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/

    /**
     * List to hold values of Dropdown Subject.
     */
    private List subjectVVList = new ArrayList();
  //Commented for HeapDump Issue
    /*private List subjectVVList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    /**
     * List to hold values of Dropdown Category.
     */
    
    private List categoryList = new ArrayList();
  //Commented for HeapDump Issue
    /*private List categoryList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    /**
     * List to hold values of Dropdown catListToBeSentInSearchCritVO. It Hold
     * list of cat assig to user same as categoryList Butwhile we sent the list
     * inSearch Criteraia vo if no cat selected we use this one.
     */
    
    private List catListToBeSentInSearchCritVO = new ArrayList();
  //Commented for HeapDump Issue
    /* private List catListToBeSentInSearchCritVO = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    
    /** holds the case type SK and short desc */
    //hash map performance issue code change
    //private Map caseTypeSKAndBusinessUnit = new HashMap();
    
    /**
     * This field is used to store entityIDType .
     */
    private List entityIDTypeList = new ArrayList();

    /**
     * This field is used to store entityIDType .
     */
    private List InqEntityIDTypeList = new ArrayList();
    
    /**
     * htmlSearchResults
     */
    private int htmlSearchResults;
    
    /**
     * caseSearchRowIndex
     */
    private int caseSearchRowIndex;
    
    /**
     * existingCaseSearchRowIndex
     */
    private int existingCaseSearchRowIndex;
    
    /**
     * searchSelectAll
     */
    private boolean searchSelectAll;
    
    /**
     * searchDeSelectAll
     */
    private boolean searchDeSelectAll;
    
    /**
     * existCrsSelectAll
     */
    private boolean existCrsSelectAll;
    
    /**
     * existCrsDeSelectAll
     */
    private boolean existCrsDeSelectAll;
    
    /**
     * searchCaseSelectAll
     */
    private boolean searchCaseSelectAll;
    
    /**
     * existCaseSelectAll
     */
    private boolean existCaseSelectAll;
    
    /**
     * existCaseDeSelectAll
     */
    private boolean existCaseDeSelectAll;
    
    //Commented for defect ESPRD00935080
    //private Map caseStatusValidValues;
    
    /**
     * searchCaseDeSelectAll
     */
    private boolean searchCaseDeSelectAll;
    
    /** Holds common message to log at begin of every method. */
    protected static final String BEGINMETHOD = "Begin of the CMLogCaseDataBean";

    /** Holds common message to log at end of every method. */
    protected static final String ENDMETHOD = "End of the CMLogCaseDataBean";
    
    /** Holds rowindex of Correspondence associated with the Case Record */
    private int crAssociatedCaseRowIndex;
    
    /** used to enable / disable the Add New Note Link */
    private boolean displayAddNoteLink = false;

    /** Holds boolean value for Automatic Alert Completed Approved Status */
    private boolean completedApprFlag = false;
    
    /** Holds boolean value for Automatic Alert Completed Denied Status */
    private boolean completedDeniFlag = false;
    
    /**
     * existCrsDeSelectAll
     */
    private boolean renderAddRouting = false;
    
    // Added following property for defect 681457
    /**
     * Used to control the conditional load of letters, when Case is saved
     */
    private boolean loadLetterData = false;
    
	/**
	 * @return Returns the searchCaseDeSelectAll.
	 */
	public boolean isSearchCaseDeSelectAll() {
		return searchCaseDeSelectAll;
	}
	/**
	 * @param searchCaseDeSelectAll The searchCaseDeSelectAll to set.
	 */
	public void setSearchCaseDeSelectAll(boolean searchCaseDeSelectAll) {
		this.searchCaseDeSelectAll = searchCaseDeSelectAll;
	}
	/**
	 * @return Returns the caseSearchRowIndex.
	 */
	public int getCaseSearchRowIndex() {
		return caseSearchRowIndex;
	}
	/**
	 * @param caseSearchRowIndex The caseSearchRowIndex to set.
	 */
	public void setCaseSearchRowIndex(int caseSearchRowIndex) {
		this.caseSearchRowIndex = caseSearchRowIndex;
	}
	/**
	 * @return Returns the existCaseDeSelectAll.
	 */
	public boolean isExistCaseDeSelectAll() {
		return existCaseDeSelectAll;
	}
	/**
	 * @param existCaseDeSelectAll The existCaseDeSelectAll to set.
	 */
	public void setExistCaseDeSelectAll(boolean existCaseDeSelectAll) {
		this.existCaseDeSelectAll = existCaseDeSelectAll;
	}
	/**
	 * @return Returns the existCaseSelectAll.
	 */
	public boolean isExistCaseSelectAll() {
		return existCaseSelectAll;
	}
	/**
	 * @param existCaseSelectAll The existCaseSelectAll to set.
	 */
	public void setExistCaseSelectAll(boolean existCaseSelectAll) {
		this.existCaseSelectAll = existCaseSelectAll;
	}
	/**
	 * @return Returns the existingCaseSearchRowIndex.
	 */
	public int getExistingCaseSearchRowIndex() {
		return existingCaseSearchRowIndex;
	}
	/**
	 * @param existingCaseSearchRowIndex The existingCaseSearchRowIndex to set.
	 */
	public void setExistingCaseSearchRowIndex(
			int existingCaseSearchRowIndex) {
		this.existingCaseSearchRowIndex = existingCaseSearchRowIndex;
	}
	/**
	 * @return Returns the searchCaseSelectAll.
	 */
	public boolean isSearchCaseSelectAll() {
		return searchCaseSelectAll;
	}
	/**
	 * @param searchCaseSelectAll The searchCaseSelectAll to set.
	 */
	public void setSearchCaseSelectAll(boolean searchCaseSelectAll) {
		this.searchCaseSelectAll = searchCaseSelectAll;
	}
    /** Ends  */
    
	/**
	 * @return Returns the firstCaseEventSeqNum.
	 */
	public Integer getFirstCaseEventSeqNum() {
		return firstCaseEventSeqNum;
	}
	/**
	 * @param firstCaseEventSeqNum The firstCaseEventSeqNum to set.
	 */
	public void setFirstCaseEventSeqNum(Integer firstCaseEventSeqNum) {
		this.firstCaseEventSeqNum = firstCaseEventSeqNum;
	}
	
	/**
	 * @return Returns the firstCaseStepSeqNum.
	 */
	public Integer getFirstCaseStepSeqNum() {
		return firstCaseStepSeqNum;
	}
	/**
	 * @param firstCaseStepSeqNum The firstCaseStepSeqNum to set.
	 */
	public void setFirstCaseStepSeqNum(Integer firstCaseStepSeqNum) {
		this.firstCaseStepSeqNum = firstCaseStepSeqNum;
	}
	/**
	 * @return Returns the renderedCaseEventsflag.
	 */
	public boolean isRenderedCaseEventsflag() {
		return renderedCaseEventsflag;
	}
	/**
	 * @param renderedCaseEventsflag The renderedCaseEventsflag to set.
	 */
	public void setRenderedCaseEventsflag(boolean renderedCaseEventsflag) {
		this.renderedCaseEventsflag = renderedCaseEventsflag;
	}
	/**
	 * @return Returns the renderedalertsFlag.
	 */
	public boolean isRenderedalertsFlag() {
		return renderedalertsFlag;
	}
	/**
	 * @param renderedalertsFlag The renderedalertsFlag to set.
	 */
	public void setRenderedalertsFlag(boolean renderedalertsFlag) {
		this.renderedalertsFlag = renderedalertsFlag;
	}
    /**
	 * @return Returns the renderedroutingFlag.
	 */
	public boolean isRenderedroutingFlag() {
		return renderedroutingFlag;
	}
	/**
	 * @param renderedroutingFlag The renderedroutingFlag to set.
	 */
	public void setRenderedroutingFlag(boolean renderedroutingFlag) 
	{
		this.renderedroutingFlag = renderedroutingFlag;
	}
	/**
	 * @return Returns the saveCaseEventFlag.
	 */
	public boolean isSaveCaseEventFlag() {
		return SaveCaseEventFlag;
	}
	/**
	 * @param saveCaseEventFlag The saveCaseEventFlag to set.
	 */
	public void setSaveCaseEventFlag(boolean saveCaseEventFlag) {
		SaveCaseEventFlag = saveCaseEventFlag;
	}
    /**
	 * @return Returns the saveAlertsFlag.
	 */
	public boolean isSaveAlertsFlag() {
		return SaveAlertsFlag;
	}
	/**
	 * @param saveAlertsFlag The saveAlertsFlag to set.
	 */
	public void setSaveAlertsFlag(boolean saveAlertsFlag) {
		this.SaveAlertsFlag = saveAlertsFlag;
	}
    /** Enterprise Logger for Logging. */
	static final EnterpriseLogger log = EnterpriseLogFactory
            .getLogger(CMLogCaseDataBean.class.getName());

    /** holds the user id */
    private String loggedInUserID; // Modified for the performance fix
    
    /**
     * This List holds the notesList Added By Madhurima
     */
    private List caseNotesList = new ArrayList();

    /** holds the notes of cr temporarily */
    private List caseNotesSetTempList = new ArrayList();

    /** holds the form elements */
    //Commented for HeapDump Issue
    //private transient HtmlPanelGrid formElements = new HtmlPanelGrid();

    /** holds the flag status for Save link disable/enable */
    private boolean enableSaveLink = false;

    /** holds the flag status for Attachments Detach indicator */
    private boolean enableDetachInd = false;

    /** it will enable/disable letter link in events */
    private boolean enableCaseEventsLetterLink = false;

    /** it will enable/disable letter link in steps */
    private boolean enableCaseStepsLetterLink = false;

    /** holds the entityID */
    private String entityID;

    /** holds the entity Type */
    private String entityType;

    /** holds the createdBy */
    private String createdBy;

    /** holds the createdBySK */
    private String createdBySK;

    /** holds the assignedTo */
    private String assignedTo;

    /** holds the assignedToWorkUnitSK */
    private String assignedToWorkUnitSK;

    /** holds the user map */
    //Commented for Heap dump fix defect ESPRD00935080
    //private Map userMap = new HashMap();

    /** holds the dept map */
    private Map deptMap = new HashMap();

    /** holds the routing dept map */
    private Map routingDeptMap = new HashMap();

    /** holds the LOBHierarchy DO */
    private Map lobDOMap = new HashMap();

    /** holds the LOBHierarchy DO and deptSK */
    private Map lobSKAndDeptSKMap = new HashMap();

    /** holds the case type SK and short desc */
    //hash map performance issue code change
    //private Map caseTypeSKAndShortDesc = new HashMap();

    /** holds the physicianOverseeing list */
    private List physicianOverseeingList = Collections.EMPTY_LIST;

    /** holds the businessUnit */
    private String businessUnit;

    /** holds the reportingUnit */
    private String reportingUnit;

    /** holds the lobSK */
    private Long lobSK;

    /** holds the caseSK */
    private String caseSK;

    /** holds the caseTypeSK */
    private Long caseTypeSK;

    // Major Save code starts
    
    /** holds the caseCmnEntyXrefVersionNo */
    
    private int caseCmnEntyXrefVersionNo=0;  
    
    
    /* Varible adding to fix defect   ESPRD00334100 Starts*/
    
    /** Holds the boolean Variable advOptionsFl*/
    private boolean advOptionsFl = false;
	
    /** Holds class name. */
    private String advancedOptionsHidden = "plus";
    
    /**
     * List to hold values of Dropdown CreatedBy.
     */
    
    private List assignedToList = new ArrayList();
  //Commented for HeapDump Issue
    /*private List assignedToList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    
    /**
     * List to hold values of Dropdown Provider Type.
     */
    
    private List provTypeVVList = new ArrayList();
  //Commented for HeapDump Issue
    /*  private List provTypeVVList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    
    /**
     * List to hold values of Dropdown CreatedBy.
     */
    
    private List createdByList = new ArrayList();
  //Commented for HeapDump Issue
    /*private List createdByList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);  */   
    
    /**
     * List to hold values of Dropdown Source.
     */
   
    private List sourceVVList = new ArrayList();
  //Commented for HeapDump Issue
    /* private List sourceVVList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    
    /**
     * List to hold values of Dropdown Line Of Business.
     */
   
    private List lobVVList = new ArrayList();
   
  //Commented for HeapDump Issue
    /* private List lobVVList = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    
    /**
     * List to hold values of Dropdown Reporting Unit.
     */
    
    private List listOfRepUnits = new ArrayList();
  //Commented for HeapDump Issue
    /*private List listOfRepUnits = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    
    /**
     * List to hold values of Dropdown Business Unit.
     */
   
    private List listOfRefBusUnits = new ArrayList();
  //Commented for HeapDump Issue
    /* private List listOfRefBusUnits = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
    
    /**
     * List to hold values of Dropdown Departments.
     */
    private List listOfRefDeptUnits = new ArrayList();
  //Commented for HeapDump Issue
    /*private List listOfRefDeptUnits = new ArrayList(
            ContactManagementConstants.DEFAULT_SIZE);*/
   /** 
    * holds SearchAssocCR
    */
   private boolean searchAssocCR = false;
   
   /** Holds searchCorrespondenceListSize. */
   private int searchCorrespondenceListSize;
   
   /**
    * Boolean value for showing the result set
    */
   private boolean showResultsDiv = false;
   
   /**
    * List to hold search results values.
    */
   private List searchResults = new ArrayList();
   
    
    /*  Varible adding to fix defect   ESPRD00334100 End*/
    
   /*variable added to fix the defect ESPRD00333005 */
   private boolean showTPLlabel = false;
   
   
	
	/**
	 * @return Returns the showTPLlabel.
	 */
	public boolean isShowTPLlabel() {
		return showTPLlabel;
	}
	/**
	 * @param showTPLlabel The showTPLlabel to set.
	 */
	public void setShowTPLlabel(boolean showTPLlabel) {
		this.showTPLlabel = showTPLlabel;
	}
	/**
	 * @return Returns the caseCmnEntyXrefVersionNo.
	 */
	public int getCaseCmnEntyXrefVersionNo() {
		return caseCmnEntyXrefVersionNo;
	}	

	/**
	 * @param caseCmnEntyXrefVersionNo The caseCmnEntyXrefVersionNo to set.
	 */
	public void setCaseCmnEntyXrefVersionNo(int caseCmnEntyXrefVersionNo) {
		this.caseCmnEntyXrefVersionNo = caseCmnEntyXrefVersionNo;
	}
   
	//	Major Save code ends
	
    /** holds the userDept list */
    private List routingDeptList = new ArrayList();

    /** holds the userList for routing */
    private List routingUserList = new ArrayList();

    /** holds the case Type DO list */
    private List caseTypeDOList = new ArrayList();

    /** holds the dept list */
    private List listOfDepartments = new ArrayList();

    /** holds the action code */
    private String actionCode;

    /** holds the CustomField DO objects */
    private List customFieldDOList = new ArrayList();
    
    /** holds the status */
    private boolean fromIPC = false;

    /** it will show/hide general case type screens */
    private boolean showGeneralCaseTypeScreens = false;

    /** it will show/hide caseType */
    private boolean disableCaseType = false;
    
    /** holds the set of Case Type Events */
    private List caseTypeEventDOList = new ArrayList();

    /** holds the set of CaseTypeSteps */
    private List caseTypeStepDOList = new ArrayList();

    /** it will show/hide CaseSteps error messages */
    private boolean showCaseStepsMessages = false;

    /** it will show/hide Alerts error messages */
    private boolean showCaseAlertsMessages = false;

    /** it will show/hide Case success messages */
    private boolean showCaseMessage = false;

    /** it will show/hide Case Events error messages */
    private boolean showCaseEventsMessages = false;

    /** it will show/hide Case attachment error messages */
    private boolean showCaseAttachMessages = false;

    /** it will show/hide Case Routing error messages */
    private boolean showCaseRoutingMessages = false;

    /** it will show/hide Field Audit Date field */
    private boolean showReviewReq = false;

    /** it will show/hide cancel link */
    private boolean cancelLinkUpdate = false;

    /** it will holds the superviser approval ind */
    private boolean superviserRevReqInd = false;

    /** it will holds the superviser approval ind */
    private boolean superviserRevReqIndForCaseType = false;

    /** holds the logged in user superviser or not */
    private boolean superviser = false;

    /** it will show or hide inq abt member */
    private boolean showInqAbtMember = false;

    /** it will show or hide inq abt provider */
    private boolean showInqAbtProvider = false;

    /** it will show or hide inq abt */
    private boolean showInqAbtFor = false;

    /** it will enable Follow-up months */
    private boolean enableFollowUpMon = false;

    /** it will enable Tumor size and Nodes Positive fields */
    private boolean enableTumorAndNodes = false;

    /** it will enable Treatement Started dates */
    private boolean enableTreatementStDates = false;

    /** it will enable fieldAudit fields */
    private boolean enableFieldAuditFields = false;

    /** it will enable homeOffice */
    private boolean enableHomeOfficeInd = false;

    /** it will enable Pay Rates fields */
    private boolean enablePayRatesFields = false;

    /** it will enable picture date */
    private boolean enablePictureDate = false;

    /** it will enable Rate setting date */
    private boolean enableRaterSetting = false;

    /** it will enable State Fiscal */
    private boolean enableStateFiscal = false;

    /** it will enable biopsy Findings */
    private boolean enableBiopsyFindings = false;

    private boolean autoPopulatedDept = false;

    /** holds the CaseDetails VO object */
    private CaseDetailsVO caseDetailsVO = new CaseDetailsVO();

    /** holds the Case Regarding VO object */
    private CaseRegardingVO caseRegardingVO = new CaseRegardingVO();

    /** holds the Routing VO object */
    private CMRoutingVO routingVO = new CMRoutingVO();

    /** holds the Attachments VO object */
    private AttachmentVO attachmentVO = new AttachmentVO();

    /** holds the CaseEvents VO */
    private CaseEventsVO caseEventsVO = new CaseEventsVO();

    /** holds the CaseSteps VO */
    private CaseStepsVO caseStepsVO = new CaseStepsVO();

    /** holds the AlertsVO */
    private AlertVO alertVO = new AlertVO();

    /** holds the member alternate Identifier info */
    private AlternateIdentifiersVO alternateIdentifiersVO = new AlternateIdentifiersVO();

    /** holds the AssociatedCR VO */
    private AssociatedCorrespondenceVO associatedCorrespondenceVO = new AssociatedCorrespondenceVO();

    /** holds the Associated Case VO */
    private AssociatedCaseVO associatedCaseVO = new AssociatedCaseVO();

    /** holds the custom field values VO */
    private CustomFieldValueVO customFieldValueVO = new CustomFieldValueVO();     
    
    /** This field is used to store correspondenceSearchCriteriaVO. and added to fix defect  ESPRD00334100*/
    private CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = new CorrespondenceSearchCriteriaVO();

    /** holds the CustomFieldValueVO list */
    private Map cfValueSKMap = new HashMap();

    /** Holds Image Render */
    private String imageRender;

    /** Holds bean name. */
    public static final String BEAN_NAME = MaintainContactManagementUIConstants.LOG_CASE_BEAN_NAME;

    /** holds the boolean flag for showing the Add screen for Routing */
    private boolean showAddRoutingAssignment = false;

    /** holds the boolean flag for showing the Edit screen for Routing */
    private boolean showEditRoutingAssignment = false;

    /** maintain audit render flag */
    private boolean routingAuditHistoryRender = false;

    /** holds the audit details list for Routing */
    private List routingAuditHistoryList = new ArrayList();

    /** holds the boolean flag for showing the Add screen for Attachments */
    private boolean showAddAttachments = false;

    /** holds the boolean flag for showing the Edit screen for Attachments */
    private boolean showEditAttachments = false;

    /** maintain audit render flag */
    private boolean attachmentAuditHistoryRender = false;

    /** holds the audit details list for Routing */
    private List attachmentAuditHistoryList = new ArrayList();

    /** holds the status of Case Events Add screen */
    private boolean showAddCaseEvents = false;

    /** holds the status of Case Events Edit screen */
    private boolean showEditCaseEvents = false;

    /** holds the Case Events VO object */
    private List caseEventsVOList = new ArrayList();

    /** it will show/hide the Case Events DataTable */
    private boolean showCaseEventsDataTable = false;

    /** holds the Case Events VO object */
    private List caseStepsVOList = new ArrayList();

    /** it will show/hide the Case Events DataTable */
    private boolean showCaseStepsDataTable = false;

    /** holds the status of Alerts Edit screen */
    private boolean showEditAlers = false;

    /** holds the status of Alerts Add screen */
    private boolean showAddAlers = false;

    /** holds the Alerts VO objects */
    private List alertsVOList = new ArrayList();

    /** it will show/hide the Alerts DataTable */
    private boolean showAlertsDataTable = false;

    /** holds the status of Case Steps Edit screen */
    private boolean showEditCaseSteps = false;

    /** holds the status of Correspondence records with this Case Edit screen */
    private boolean showEditCRAssocWithThisCase = false;

    /** holds the status of Case records Associated with this Case Edit screen */
    private boolean showEditAssocCaseWithThisCase = false;

    /** holds the status of Case Steps Add screen */
    private boolean showAddCaseSteps = false;

    /** it shows/hide case type BCCP screens */
    private boolean showBCCPTypeScreen = false;

    /** it shows/hide case type DDU screens */
    private boolean showDDUTypeScreen = false;
    
    /** holds the Selected Un Usual List*/
    private List selectedUnUsualList = new ArrayList();

    /** it shows/hide case type DDU Appointment screens */
    private boolean showDDUAppointmentScreen = false;
	/** it shows/hide case type FAT screens */
	private boolean showFATypeScreen = false; 
	
	/** it shows/hide case type CB screens */
	private boolean showCBTypeScreen = false;
	/** it shows/hide case type MQIP screens */
	private boolean showMQIPTypeScreen = false;
	/** it shows/hide case type Non ARS screens */
	private boolean showNewNonARSTypeScreen = false;
	/** it shows/hide case type FCR screens */
	private boolean showFCRTypeScreen = false;
	/** it shows/hide case type NFQA screens */
	private boolean showNFQATypeScreen = false;

    /** it shows/hide case type ARS screens */
    private boolean showARSTypeScreen = false;
	/** it shows/hide case type New ARS type screens */
	private boolean showNewARSTypeScreen = false;	

    /** it show/hide case type Appeal */
    private boolean showAppealScreen = false;
	/** it shows/hide case type HOCR type screens */
	private boolean showHOCRTypeScreen = false;	
	
	/** it shows/hide case type AcuityRateSetting type screens */
	private boolean showAcuityRateSettingTypeScreen = false;	
	
	/** it shows/hide case type FPPRR type screens */
	private boolean showFPPRRTypeScreen = false;	
	
	/** it shows/hide case type FCS type screens */
	private boolean showFCSTypeScreen = false;

    /** holds the routing info for displaying routing dataTable */
    private List routingInfoList = new ArrayList();

    /** it shows/hide the routing DataTable */
    private boolean showRoutingDataTable = false;

    /**
     * holds the Additional Case Entites info for displaying Additional Case
     * Entites dataTable
     */
    private List addiCaseEntitesInfoList = new ArrayList();

    /** it shows/hide the routing DataTable */
    private boolean showAddiCaseEntitesDataTable = false;

    /** it shows/hide the showExistingCRDataTable */
    private boolean showExistingCRDataTable = false;

    /**
     * holds the Existing Correspondence Records info for displaying Existing CR
     * dataTable
     */
    private List existingCRInfoList = new ArrayList();

    /** it shows/hide the showExistingCRDataTable */
    private boolean showCRAssociatedWithCaseDataTable = false;

    /**
     * holds the CRAssociatedWithCase info for displaying CR Associated With
     * This Case dataTable
     */
    private List crAssociatedWithCaseInfoList = new ArrayList();

    /** holds the Existing Case Records VO list */
    private List existingCaseRecordsList = new ArrayList();

    /** holds the Associated CR list */
    private List assoCRList = new ArrayList();

    /** holds the Associated Case list */
    private List assoCaseList = new ArrayList();

    /** it will Show/hide the Existing Case Records DataTable */
    private boolean showExistingCaseRecordsDataTable = false;

    /** holds the Case Records Associated with this case list */
    private List caseRecordsAssoWithCaseList = new ArrayList();

    /** it will Show/hide the Case Records associated with this Case DataTable */
    private boolean showCaseRecordsAssoWithCaseDataTable = false;

    /**
     * it show/hide the Alternate Identifiers datatable for provider case
     * details
     */
    private boolean showAlternateIdentifiersProv = false;

    /** holds the Alternate Identifiers details for provider */
    private List alternateIdentiProvList = new ArrayList();

    /** it show/hide the Alternate Identifiers datatable for Memberr case details */
    private boolean showAlternateIdentifiersMem = false;

    /** holds the Alternate Identifiers details for Member */
    private List alternateIdentiMemList = new ArrayList();

    /** it show/hide the Member details regarding Case */
    private boolean showCaseRegardingMember = false;

    /** it show/hide the Provider details regarding Case */
    private boolean showCaseRegardingProvider = false;

    /** it show/hide the Un-Enrolled Provider details regarding Case */
    private boolean showCaseRegardingUnEnrolProv = false;

    /** it show/hide the Un-Enrolled Member details regarding Case */
    private boolean showCaseRegardingUnEnrolMem = false;

    /**
     * it show/hide the Other(TPL, District Office, etc..) details regarding
     * Case
     */
    private boolean showCaseRegardingOther = false;

    /** it will show/hide the Correspondence Search Scereen */
    private boolean showCorrespondenceSearchScreen = false;

    /** it will holds the Correspondence Search results */
    private List correspondenceSearchResultsList = new ArrayList();

    /** it will show/hide the Correspondence Search DataTable */
    private boolean showCorrespondenceSearchDataTable = false;

    /** it will show/hide the Correspondence Search Results */
    private boolean showCorrespondenceSearchResults = false;

    /** it will show/hide the Case Search Scereen */
    private boolean showCaseSearchScreen = false;

    /** it will holds the Correspondence Search results */
    private List caseSearchResultsList = new ArrayList();

    /** it will show/hide the Case Search DataTable */
    private boolean showCaseSearchDataTable = false;

    /** it will show/hide the Case Search Results */
    private boolean showCaseSearchResults = false;

    /** holds the index of CaseSteps dataTable */
    private String caseStepsIndex;

    /** holds the index of CaseAlerts dataTable */
    private String caseAlertsIndex;

    /** holds the index of CaseAlerts dataTable */
    private String caseEventsIndex;

    /** holds the index of Routing dataTable */
    private String routingIndex;

    /** holds the index of Case Attachment DataTable */
    private String attachmentIndex;

    /** This is to set the routing info section. */
    private String routingHidden = MaintainContactManagementUIConstants.PLUS;

    /** This is to set the assocCaseAndCR info section. */
    //Modified for the defect ESPRD00903176
   // private String assocCaseAndCRHidden = MaintainContactManagementUIConstants.PLUS;

    /** This is to set the Case Events info section. */
    private String caseEventsHidden = MaintainContactManagementUIConstants.PLUS;

    /** This is to set the Case Alerts info section. */
    private String caseAlertsHidden = MaintainContactManagementUIConstants.PLUS;
    
    /** This is used to hold the state of the Audit block open or closed. */
    private boolean alertAuditOpen = false;

    /** This is to set the Case Steps info section. */
    private String caseStepsHidden = MaintainContactManagementUIConstants.PLUS;

    /**
     * This is used to hold the state of the Provider alternate identifiers
     * block open or closed.
     * property should hold the provider alternate identifier section 
     * fieldset mode like collapse or expand.
     */
    private String provAltIdentifierHidden = MaintainContactManagementUIConstants.PLUS;

    /**
	 * @return the provAltIdentifierHidden
	 */
	public String getProvAltIdentifierHidden() {
		return provAltIdentifierHidden;
	}
	/**
	 * @param provAltIdentifierHidden the provAltIdentifierHidden to set
	 */
	public void setProvAltIdentifierHidden(String provAltIdentifierHidden) {
		this.provAltIdentifierHidden = provAltIdentifierHidden;
	}
	/**
     * This is used to hold the state of the Provider alternate identifiers
     * block open or closed.
     * property should hold the member alternate identifier section 
     * fieldset mode like collapse or expand.
     */
    private String memAltIdentifierHidden = MaintainContactManagementUIConstants.PLUS;

    /**
	 * @return the memAltIdentifierHidden
	 */
	public String getMemAltIdentifierHidden() {
		return memAltIdentifierHidden;
	}
	/**
	 * @param memAltIdentifierHidden the memAltIdentifierHidden to set
	 */
	public void setMemAltIdentifierHidden(String memAltIdentifierHidden) {
		this.memAltIdentifierHidden = memAltIdentifierHidden;
	}
	/** This is to set the Case Attachments info section. */
    private String caseAttachmentsHidden = MaintainContactManagementUIConstants.PLUS;
    
    /** This is used to hold the state of the Audit block open or closed. */
    private boolean attachmentAuditOpen = false;
    
    /** This is used to hold the state of the Audit block open or closed. */
    private boolean routingAuditOpen = false;

    /** This is to set the Case Letters and Responses info section. */
    private String caseLandResponse = MaintainContactManagementUIConstants.PLUS;

    /** This will holds the Attachments VOs */
    private List attachmentVOList = new ArrayList();

    /** This will show/hide the Attachment DataTable */
    private boolean showAttachmentDataTable = false;

    /** this is to set to show the Dept Names */
    private boolean showDepartments = false;

    /** this is to set to show the Dept user Names */
    private boolean showUserDepartments = false;

    /** this will show/hide the the user list box in UI */
    private boolean showUsers = false;

    /** This will holds the User list */
    private List userList = new ArrayList();

    /** This will holds the UserIDs list */
    private List userIDsList = new ArrayList();

    /** This will holds the UserIDs Map */
    //Commented for Heap dump fix defect ESPRD00935080
    //private Map userIDsMap = new HashMap();

    /** This will holds the UserIDs and WU Map */
    //private Map userIDAndWUMap = new HashMap();

    /** holds the status of alert audit history */
    private boolean alertAuditHistoryRender = false;

    /** holds the alert audit history records */
    private List alertAuditHistoryList = new ArrayList();
    
    /** used to enable / disable the screen fields */
    private boolean disableScreenField = false;
    
    /** holds the commonEntitySK for MyTask */
    private Long commonEntitySKForMyTask = null;

    /** used to enable/disable attachment link to view */
    private boolean showAttachmentLink = false;

   /** used to  show the delete message*/
    private boolean showDeleteMessage = false;
    
    /** Added For The Defect ID ESPRD00301018*/
    
    /** holds the Existing Correspondence Records VO list */
    //private List existingCorrespondenceRecordsList = new ArrayList();//unused
    
    /** holds the Associated Correspondence Records VO list */
   // private List crAssociatedWithCaseRecordsList = new ArrayList(); //unused
    
    /** it will Show/hide the Existing Correspondence Records DataTable */
    private boolean showExistingCorrespondenceDataTable = false;
    
    /** it will Show/hide the Existing Correspondence Records DataTable */
    private boolean showCorrSearchScreen = false;
    
    
    /** Holds boolean value for Status type ClosedAppealUpheld  */
    private boolean statusClosedAppealUpheld = false;
    
    /** Holds boolean value for Status Closed And Approved or both */
    private boolean statusClosedAndApproved = false;     
    
    /** This is to set the assocCaseAudit info section. */
    private String assocCaseAuditHidden = MaintainContactManagementUIConstants.PLUS;
    
    /** Holds boolean value for showing virus scan message to User  */
    private boolean showVirusMessage = false;
    
    
	/**
	 * @return Returns the statusClosedAndApproved.
	 */
	public boolean isStatusClosedAndApproved() {
		return statusClosedAndApproved;
	}
	/**
	 * @param statusClosedAndApproved The statusClosedAndApproved to set.
	 */
	public void setStatusClosedAndApproved(boolean statusClosedAndApproved) {
		this.statusClosedAndApproved = statusClosedAndApproved;
	}
    /** holds the caseTypeMemList codes */
   // private List caseTypeMemList = new ArrayList(); // unused
    
	/** holds the menuCode */
    private String menuCode;
    
    /** holds the case type SK and short desc */
    //hash map performance issue code change
    //private Map caseTypeMemSKAndShortDes = new HashMap();
    
    
   
    /**

     * @return Returns the attachmentAuditHistoryList.
     */
    public List getAttachmentAuditHistoryList()
    {
        return attachmentAuditHistoryList;
    }

    /**
     * @param attachmentAuditHistoryList
     *            The attachmentAuditHistoryList to set.
     */
    public void setAttachmentAuditHistoryList(List attachmentAuditHistoryList)
    {
        this.attachmentAuditHistoryList = attachmentAuditHistoryList;
    }

    /**
     * @return Returns the attachmentAuditHistoryRender.
     */
    public boolean isAttachmentAuditHistoryRender()
    {
        return attachmentAuditHistoryRender;
    }

    /**
     * @param attachmentAuditHistoryRender
     *            The attachmentAuditHistoryRender to set.
     */
    public void setAttachmentAuditHistoryRender(
            boolean attachmentAuditHistoryRender)
    {
        this.attachmentAuditHistoryRender = attachmentAuditHistoryRender;
    }

    /**
     * @return Returns the routingAuditHistoryList.
     */
    public List getRoutingAuditHistoryList()
    {
        return routingAuditHistoryList;
    }

    /**
     * @param routingAuditHistoryList
     *            The routingAuditHistoryList to set.
     */
    public void setRoutingAuditHistoryList(List routingAuditHistoryList)
    {
        this.routingAuditHistoryList = routingAuditHistoryList;
    }

    /**
     * @return Returns the routingAuditHistoryRender.
     */
    public boolean isRoutingAuditHistoryRender()
    {
        return routingAuditHistoryRender;
    }

    /**
     * @param routingAuditHistoryRender
     *            The routingAuditHistoryRender to set.
     */
    public void setRoutingAuditHistoryRender(boolean routingAuditHistoryRender)
    {
        this.routingAuditHistoryRender = routingAuditHistoryRender;
    }

    /**
     * @return Returns the showAddAlers.
     */
    public boolean isShowAddAlers()
    {
        return showAddAlers;
    }

    /**
     * @param showAddAlers
     *            The showAddAlers to set.
     */
    public void setShowAddAlers(boolean showAddAlers)
    {
        this.showAddAlers = showAddAlers;
    }

    /**
     * @return Returns the showAddAttachments.
     */
    public boolean isShowAddAttachments()
    {
        return showAddAttachments;
    }

    /**
     * @param showAddAttachments
     *            The showAddAttachments to set.
     */
    public void setShowAddAttachments(boolean showAddAttachments)
    {
        this.showAddAttachments = showAddAttachments;
    }

    /**
     * @return Returns the showAddCaseEvents.
     */
    public boolean isShowAddCaseEvents()
    {
        return showAddCaseEvents;
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
     * @return Returns the showAddCaseSteps.
     */
    public boolean isShowAddCaseSteps()
    {
        return showAddCaseSteps;
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
     * @return Returns the showAddRoutingAssignment.
     */
    public boolean isShowAddRoutingAssignment()
    {
        return showAddRoutingAssignment;
    }

    /**
     * @param showAddRoutingAssignment
     *            The showAddRoutingAssignment to set.
     */
    public void setShowAddRoutingAssignment(boolean showAddRoutingAssignment)
    {
        this.showAddRoutingAssignment = showAddRoutingAssignment;
    }

    /**
     * @return Returns the showEditAlers.
     */
    public boolean isShowEditAlers()
    {
        return showEditAlers;
    }

    /**
     * @param showEditAlers
     *            The showEditAlers to set.
     */
    public void setShowEditAlers(boolean showEditAlers)
    {
        this.showEditAlers = showEditAlers;
    }

    /**
     * @return Returns the showEditAttachments.
     */
    public boolean isShowEditAttachments()
    {
        return showEditAttachments;
    }

    /**
     * @param showEditAttachments
     *            The showEditAttachments to set.
     */
    public void setShowEditAttachments(boolean showEditAttachments)
    {
        this.showEditAttachments = showEditAttachments;
    }

    /**
     * @return Returns the showEditCaseEvents.
     */
    public boolean isShowEditCaseEvents()
    {
        return showEditCaseEvents;
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
     * @return Returns the showEditCaseSteps.
     */
    public boolean isShowEditCaseSteps()
    {
        return showEditCaseSteps;
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
     * @return Returns the showEditRoutingAssignment.
     */
    public boolean isShowEditRoutingAssignment()
    {
        return showEditRoutingAssignment;
    }

    /**
     * @param showEditRoutingAssignment
     *            The showEditRoutingAssignment to set.
     */
    public void setShowEditRoutingAssignment(boolean showEditRoutingAssignment)
    {
        this.showEditRoutingAssignment = showEditRoutingAssignment;
    }
    
	/*Infinite added for UC-PGM-CRM-018.10, CR 2401
	 * start here
	 */

	/**
	 * @return Returns the showAcuityRateSettingTypeScreen.
	 */
	public boolean isShowAcuityRateSettingTypeScreen() {
		return showAcuityRateSettingTypeScreen;
	}
	/**
	 * @param showAcuityRateSettingTypeScreen The showAcuityRateSettingTypeScreen to set.
	 */
	public void setShowAcuityRateSettingTypeScreen(
			boolean showAcuityRateSettingTypeScreen) {
		this.showAcuityRateSettingTypeScreen = showAcuityRateSettingTypeScreen;
	}
	/**
	 * @return Returns the showFCRTypeScreen.
	 */
	public boolean isShowFCRTypeScreen() {
		return showFCRTypeScreen;
	}
	/**
	 * @param showFCRTypeScreen The showFCRTypeScreen to set.
	 */
	public void setShowFCRTypeScreen(boolean showFCRTypeScreen) {
		this.showFCRTypeScreen = showFCRTypeScreen;
	}
	/**
	 * @return Returns the showFCSTypeScreen.
	 */
	public boolean isShowFCSTypeScreen() {
		return showFCSTypeScreen;
	}
	/**
	 * @param showFCSTypeScreen The showFCSTypeScreen to set.
	 */
	public void setShowFCSTypeScreen(boolean showFCSTypeScreen) {
		this.showFCSTypeScreen = showFCSTypeScreen;
	}
	/**
	 * @return Returns the showFPPRRTypeScreen.
	 */
	public boolean isShowFPPRRTypeScreen() {
		return showFPPRRTypeScreen;
	}
	/**
	 * @param showFPPRRTypeScreen The showFPPRRTypeScreen to set.
	 */
	public void setShowFPPRRTypeScreen(boolean showFPPRRTypeScreen) {
		this.showFPPRRTypeScreen = showFPPRRTypeScreen;
	}
	/**
	 * @return Returns the showHOCRTypeScreen.
     */
	public boolean isShowHOCRTypeScreen() {
		return showHOCRTypeScreen;
	}
	/**
	 * @param showHOCRTypeScreen The showHOCRTypeScreen to set.
	 */
	public void setShowHOCRTypeScreen(boolean showHOCRTypeScreen) {
		this.showHOCRTypeScreen = showHOCRTypeScreen;
	}
	/**
	 * @return Returns the showNFQATypeScreen.
	 */
	public boolean isShowNFQATypeScreen() {
		return showNFQATypeScreen;
	}
	/**
	 * @param showNFQATypeScreen The showNFQATypeScreen to set.
	 */
	public void setShowNFQATypeScreen(boolean showNFQATypeScreen) {
		this.showNFQATypeScreen = showNFQATypeScreen;
	}
	
	/**
	 * @return Returns the showMQIPTypeScreen.
	 */
	public boolean isShowMQIPTypeScreen() {
		return showMQIPTypeScreen;
	}
	/**
	 * @param showMQIPTypeScreen The showMQIPTypeScreen to set.
	 */
	public void setShowMQIPTypeScreen(boolean showMQIPTypeScreen) {
		this.showMQIPTypeScreen = showMQIPTypeScreen;
	}
	/**
	 * @return Returns the showNewARSTypeScreen.
	 */
	public boolean isShowNewARSTypeScreen() {
		return showNewARSTypeScreen;
	}
	/**
	 * @param showNewARSTypeScreen The showNewARSTypeScreen to set.
	 */
	public void setShowNewARSTypeScreen(boolean showNewARSTypeScreen) {
		this.showNewARSTypeScreen = showNewARSTypeScreen;
	}
	/**
	 * @return Returns the showNewNonARSTypeScreen.
	 */
	public boolean isShowNewNonARSTypeScreen() {
		return showNewNonARSTypeScreen;
	}
	/**
	 * @param showNewNonARSTypeScreen The showNewNonARSTypeScreen to set.
	 */
	public void setShowNewNonARSTypeScreen(boolean showNewNonARSTypeScreen) {
		this.showNewNonARSTypeScreen = showNewNonARSTypeScreen;
	}


	/** End Here **/
	/**
	 * @return Returns the showCBTypeScreen.
	 */
	public boolean isShowCBTypeScreen() {
		return showCBTypeScreen;
	}
	/**
	 * @param showCBTypeScreen The showCBTypeScreen to set.
	 */
	public void setShowCBTypeScreen(boolean showCBTypeScreen) {
		this.showCBTypeScreen = showCBTypeScreen;
	}
	/**
	 * @return Returns the showFATypeScreen.
	 */
	public boolean isShowFATypeScreen() {
		return showFATypeScreen;
	}
	/**
	 * @param showFATypeScreen The showFATypeScreen to set.
	 */
	public void setShowFATypeScreen(boolean showFATypeScreen) {
		this.showFATypeScreen = showFATypeScreen;
	}

    /**
     * @return Returns the showBCCPTypeScreen.
     */
    public boolean isShowBCCPTypeScreen()
    {
        return showBCCPTypeScreen;
    }

    /**
     * @param showBCCPTypeScreen
     *            The showBCCPTypeScreen to set.
     */
    public void setShowBCCPTypeScreen(boolean showBCCPTypeScreen)
    {
        this.showBCCPTypeScreen = showBCCPTypeScreen;
    }

    /**
     * @return Returns the showARSTypeScreen.
     */
    public boolean isShowARSTypeScreen()
    {
        return showARSTypeScreen;
    }

    /**
     * @param showARSTypeScreen
     *            The showARSTypeScreen to set.
     */
    public void setShowARSTypeScreen(boolean showARSTypeScreen)
    {
        this.showARSTypeScreen = showARSTypeScreen;
    }

    /**
     * @return Returns the showDDUTypeScreen.
     */
    public boolean isShowDDUTypeScreen()
    {
        return showDDUTypeScreen;
    }

    /**
     * @param showDDUTypeScreen
     *            The showDDUTypeScreen to set.
     */
    public void setShowDDUTypeScreen(boolean showDDUTypeScreen)
    {
        this.showDDUTypeScreen = showDDUTypeScreen;
    }

    /**
     * @return Returns the imageRender.
     */
    public String getImageRender()
    {
        return imageRender;
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
     * @return Returns the routingInfoList.
     */
    public List getRoutingInfoList()
    {
        return routingInfoList;
    }

    /**
     * @param routingInfoList
     *            The routingInfoList to set.
     */
    public void setRoutingInfoList(List routingInfoList)
    {
        this.routingInfoList = routingInfoList;
    }

    /**
     * @return Returns the showRoutingDataTable.
     */
    public boolean isShowRoutingDataTable()
    {
        return showRoutingDataTable;
    }

    /**
     * @param showRoutingDataTable
     *            The showRoutingDataTable to set.
     */
    public void setShowRoutingDataTable(boolean showRoutingDataTable)
    {
        this.showRoutingDataTable = showRoutingDataTable;
    }

    /**
     * @return Returns the addiCaseEntitesInfoList.
     */
    public List getAddiCaseEntitesInfoList()
    {
        return addiCaseEntitesInfoList;
    }

    /**
     * @param addiCaseEntitesInfoList
     *            The addiCaseEntitesInfoList to set.
     */
    public void setAddiCaseEntitesInfoList(List addiCaseEntitesInfoList)
    {
        this.addiCaseEntitesInfoList = addiCaseEntitesInfoList;
    }

    /**
     * @return Returns the showAddiCaseEntitesDataTable.
     */
    public boolean isShowAddiCaseEntitesDataTable()
    {
        return showAddiCaseEntitesDataTable;
    }

    /**
     * @param showAddiCaseEntitesDataTable
     *            The showAddiCaseEntitesDataTable to set.
     */
    public void setShowAddiCaseEntitesDataTable(
            boolean showAddiCaseEntitesDataTable)
    {
        this.showAddiCaseEntitesDataTable = showAddiCaseEntitesDataTable;
    }

    /**
     * @return Returns the crAssociatedWithCaseInfoList.
     */
    public List getCrAssociatedWithCaseInfoList()
    {
        return crAssociatedWithCaseInfoList;
    }

    /**
     * @param crAssociatedWithCaseInfoList
     *            The crAssociatedWithCaseInfoList to set.
     */
    public void setCrAssociatedWithCaseInfoList(
            List crAssociatedWithCaseInfoList)
    {
        this.crAssociatedWithCaseInfoList = crAssociatedWithCaseInfoList;
    }

    /**
     * @return Returns the existingCRInfoList.
     */
    public List getExistingCRInfoList()
    {
        return existingCRInfoList;
    }

    /**
     * @param existingCRInfoList
     *            The existingCRInfoList to set.
     */
    public void setExistingCRInfoList(List existingCRInfoList)
    {
        this.existingCRInfoList = existingCRInfoList;
    }

    /**
     * @return Returns the showCRAssociatedWithCaseDataTable.
     */
    public boolean isShowCRAssociatedWithCaseDataTable()
    {
        return showCRAssociatedWithCaseDataTable;
    }

    /**
     * @param showCRAssociatedWithCaseDataTable
     *            The showCRAssociatedWithCaseDataTable to set.
     */
    public void setShowCRAssociatedWithCaseDataTable(
            boolean showCRAssociatedWithCaseDataTable)
    {
        this.showCRAssociatedWithCaseDataTable = showCRAssociatedWithCaseDataTable;
    }

    /**
     * @return Returns the showExistingCRDataTable.
     */
    public boolean isShowExistingCRDataTable()
    {
        return showExistingCRDataTable;
    }

    /**
     * @param showExistingCRDataTable
     *            The showExistingCRDataTable to set.
     */
    public void setShowExistingCRDataTable(boolean showExistingCRDataTable)
    {
        this.showExistingCRDataTable = showExistingCRDataTable;
    }

    /**
     * @return Returns the showEditCRAssocWithThisCase.
     */
    public boolean isShowEditCRAssocWithThisCase()
    {
        return showEditCRAssocWithThisCase;
    }

    /**
     * @param showEditCRAssocWithThisCase
     *            The showEditCRAssocWithThisCase to set.
     */
    public void setShowEditCRAssocWithThisCase(
            boolean showEditCRAssocWithThisCase)
    {
        this.showEditCRAssocWithThisCase = showEditCRAssocWithThisCase;
    }

    /**
     * @return Returns the alternateIdentiMemList.
     */
    public List getAlternateIdentiMemList()
    {
        return alternateIdentiMemList;
    }

    /**
     * @param alternateIdentiMemList
     *            The alternateIdentiMemList to set.
     */
    public void setAlternateIdentiMemList(List alternateIdentiMemList)
    {
        this.alternateIdentiMemList = alternateIdentiMemList;
    }

    /**
     * @return Returns the alternateIdentiProvList.
     */
    public List getAlternateIdentiProvList()
    {
        return alternateIdentiProvList;
    }

    /**
     * @param alternateIdentiProvList
     *            The alternateIdentiProvList to set.
     */
    public void setAlternateIdentiProvList(List alternateIdentiProvList)
    {
        this.alternateIdentiProvList = alternateIdentiProvList;
    }

    /**
     * @return Returns the showAlternateIdentifiersMem.
     */
    public boolean isShowAlternateIdentifiersMem()
    {
        return showAlternateIdentifiersMem;
    }

    /**
     * @param showAlternateIdentifiersMem
     *            The showAlternateIdentifiersMem to set.
     */
    public void setShowAlternateIdentifiersMem(
            boolean showAlternateIdentifiersMem)
    {
        this.showAlternateIdentifiersMem = showAlternateIdentifiersMem;
    }

    /**
     * @return Returns the showAlternateIdentifiersProv.
     */
    public boolean isShowAlternateIdentifiersProv()
    {
        return showAlternateIdentifiersProv;
    }

    /**
     * @param showAlternateIdentifiersProv
     *            The showAlternateIdentifiersProv to set.
     */
    public void setShowAlternateIdentifiersProv(
            boolean showAlternateIdentifiersProv)
    {
        this.showAlternateIdentifiersProv = showAlternateIdentifiersProv;
    }

    /**
     * @return Returns the showCaseRegardingMember.
     */
    public boolean isShowCaseRegardingMember()
    {
        return showCaseRegardingMember;
    }

    /**
     * @param showCaseRegardingMember
     *            The showCaseRegardingMember to set.
     */
    public void setShowCaseRegardingMember(boolean showCaseRegardingMember)
    {
        this.showCaseRegardingMember = showCaseRegardingMember;
    }

    /**
     * @return Returns the showCaseRegardingOther.
     */
    public boolean isShowCaseRegardingOther()
    {
        return showCaseRegardingOther;
    }

    /**
     * @param showCaseRegardingOther
     *            The showCaseRegardingOther to set.
     */
    public void setShowCaseRegardingOther(boolean showCaseRegardingOther)
    {
        this.showCaseRegardingOther = showCaseRegardingOther;
    }

    /**
     * @return Returns the showCaseRegardingProvider.
     */
    public boolean isShowCaseRegardingProvider()
    {
        return showCaseRegardingProvider;
    }

    /**
     * @param showCaseRegardingProvider
     *            The showCaseRegardingProvider to set.
     */
    public void setShowCaseRegardingProvider(boolean showCaseRegardingProvider)
    {
        this.showCaseRegardingProvider = showCaseRegardingProvider;
    }

    /**
     * @return Returns the showCaseRegardingUnEnrolMem.
     */
    public boolean isShowCaseRegardingUnEnrolMem()
    {
        return showCaseRegardingUnEnrolMem;
    }

    /**
     * @param showCaseRegardingUnEnrolMem
     *            The showCaseRegardingUnEnrolMem to set.
     */
    public void setShowCaseRegardingUnEnrolMem(
            boolean showCaseRegardingUnEnrolMem)
    {
        this.showCaseRegardingUnEnrolMem = showCaseRegardingUnEnrolMem;
    }

    /**
     * @return Returns the showCaseRegardingUnEnrolProv.
     */
    public boolean isShowCaseRegardingUnEnrolProv()
    {
        return showCaseRegardingUnEnrolProv;
    }

    /**
     * @param showCaseRegardingUnEnrolProv
     *            The showCaseRegardingUnEnrolProv to set.
     */
    public void setShowCaseRegardingUnEnrolProv(
            boolean showCaseRegardingUnEnrolProv)
    {
        this.showCaseRegardingUnEnrolProv = showCaseRegardingUnEnrolProv;
    }

    /**
     * @return Returns the attachmentVO.
     */
    public AttachmentVO getAttachmentVO()
    {
        return attachmentVO;
    }

    /**
     * @param attachmentVO
     *            The attachmentVO to set.
     */
    public void setAttachmentVO(AttachmentVO attachmentVO)
    {
        this.attachmentVO = attachmentVO;
    }

    /**
     * @return Returns the caseDetailsVO.
     */
    public CaseDetailsVO getCaseDetailsVO()
    {
        return caseDetailsVO;
    }

    /**
     * @param caseDetailsVO
     *            The caseDetailsVO to set.
     */
    public void setCaseDetailsVO(CaseDetailsVO caseDetailsVO)
    {
        this.caseDetailsVO = caseDetailsVO;
    }

    /**
     * @return Returns the caseRegardingVO.
     */
    public CaseRegardingVO getCaseRegardingVO()
    {
        return caseRegardingVO;
    }

    /**
     * @param caseRegardingVO
     *            The caseRegardingVO to set.
     */
    public void setCaseRegardingVO(CaseRegardingVO caseRegardingVO)
    {
        this.caseRegardingVO = caseRegardingVO;
    }

    /**
     * @return Returns the routingVO.
     */
    public CMRoutingVO getRoutingVO()
    {
        return routingVO;
    }

    /**
     * @param routingVO
     *            The routingVO to set.
     */
    public void setRoutingVO(CMRoutingVO routingVO)
    {
        this.routingVO = routingVO;
    }

    /**
     * @return Returns the correspondenceSearchResultsList.
     */
    public List getCorrespondenceSearchResultsList()
    {
        return correspondenceSearchResultsList;
    }

    /**
     * @param correspondenceSearchResultsList
     *            The correspondenceSearchResultsList to set.
     */
    public void setCorrespondenceSearchResultsList(
            List correspondenceSearchResultsList)
    {
        this.correspondenceSearchResultsList = correspondenceSearchResultsList;
    }

    /**
     * @return Returns the showCorrespondenceSearchDataTable.
     */
    public boolean isShowCorrespondenceSearchDataTable()
    {
        return showCorrespondenceSearchDataTable;
    }

    /**
     * @param showCorrespondenceSearchDataTable
     *            The showCorrespondenceSearchDataTable to set.
     */
    public void setShowCorrespondenceSearchDataTable(
            boolean showCorrespondenceSearchDataTable)
    {
        this.showCorrespondenceSearchDataTable = showCorrespondenceSearchDataTable;
    }

    /**
     * @return Returns the showCorrespondenceSearchScreen.
     */
    public boolean isShowCorrespondenceSearchScreen()
    {
        return showCorrespondenceSearchScreen;
    }

    /**
     * @param showCorrespondenceSearchScreen
     *            The showCorrespondenceSearchScreen to set.
     */
    public void setShowCorrespondenceSearchScreen(
            boolean showCorrespondenceSearchScreen)
    {
        this.showCorrespondenceSearchScreen = showCorrespondenceSearchScreen;
    }

    /**
     * @return Returns the showCorrespondenceSearchResults.
     */
    public boolean isShowCorrespondenceSearchResults()
    {
        return showCorrespondenceSearchResults;
    }

    /**
     * @param showCorrespondenceSearchResults
     *            The showCorrespondenceSearchResults to set.
     */
    public void setShowCorrespondenceSearchResults(
            boolean showCorrespondenceSearchResults)
    {
        this.showCorrespondenceSearchResults = showCorrespondenceSearchResults;
    }

    /**
     * @return Returns the caseSearchResultsList.
     */
    public List getCaseSearchResultsList()
    {
        return caseSearchResultsList;
    }

    /**
     * @param caseSearchResultsList
     *            The caseSearchResultsList to set.
     */
    public void setCaseSearchResultsList(List caseSearchResultsList)
    {
        this.caseSearchResultsList = caseSearchResultsList;
    }

    /**
     * @return Returns the showCaseSearchDataTable.
     */
    public boolean isShowCaseSearchDataTable()
    {
        return showCaseSearchDataTable;
    }

    /**
     * @param showCaseSearchDataTable
     *            The showCaseSearchDataTable to set.
     */
    public void setShowCaseSearchDataTable(boolean showCaseSearchDataTable)
    {
        this.showCaseSearchDataTable = showCaseSearchDataTable;
    }

    /**
     * @return Returns the showCaseSearchResults.
     */
    public boolean isShowCaseSearchResults()
    {
        return showCaseSearchResults;
    }

    /**
     * @param showCaseSearchResults
     *            The showCaseSearchResults to set.
     */
    public void setShowCaseSearchResults(boolean showCaseSearchResults)
    {
        this.showCaseSearchResults = showCaseSearchResults;
    }

    /**
     * @return Returns the showCaseSearchScreen.
     */
    public boolean isShowCaseSearchScreen()
    {
        return showCaseSearchScreen;
    }

    /**
     * @param showCaseSearchScreen
     *            The showCaseSearchScreen to set.
     */
    public void setShowCaseSearchScreen(boolean showCaseSearchScreen)
    {
        this.showCaseSearchScreen = showCaseSearchScreen;
    }

    /**
     * @return Returns the caseRecordsAssoWithCaseList.
     */
    public List getCaseRecordsAssoWithCaseList()
    {
        return caseRecordsAssoWithCaseList;
    }

    /**
     * @param caseRecordsAssoWithCaseList
     *            The caseRecordsAssoWithCaseList to set.
     */
    public void setCaseRecordsAssoWithCaseList(List caseRecordsAssoWithCaseList)
    {
        this.caseRecordsAssoWithCaseList = caseRecordsAssoWithCaseList;
    }

    /**
     * @return Returns the existingCaseRecordsList.
     */
    public List getExistingCaseRecordsList()
    {
        return existingCaseRecordsList;
    }

    /**
     * @param existingCaseRecordsList
     *            The existingCaseRecordsList to set.
     */
    public void setExistingCaseRecordsList(List existingCaseRecordsList)
    {
        this.existingCaseRecordsList = existingCaseRecordsList;
    }

    /**
     * @return Returns the showCaseRecordsAssoWithCaseDataTable.
     */
    public boolean isShowCaseRecordsAssoWithCaseDataTable()
    {
        return showCaseRecordsAssoWithCaseDataTable;
    }

    /**
     * @param showCaseRecordsAssoWithCaseDataTable
     *            The showCaseRecordsAssoWithCaseDataTable to set.
     */
    public void setShowCaseRecordsAssoWithCaseDataTable(
            boolean showCaseRecordsAssoWithCaseDataTable)
    {
        this.showCaseRecordsAssoWithCaseDataTable = showCaseRecordsAssoWithCaseDataTable;
    }

    /**
     * @return Returns the showExistingCaseRecordsDataTable.
     */
    public boolean isShowExistingCaseRecordsDataTable()
    {
        return showExistingCaseRecordsDataTable;
    }

    /**
     * @param showExistingCaseRecordsDataTable
     *            The showExistingCaseRecordsDataTable to set.
     */
    public void setShowExistingCaseRecordsDataTable(
            boolean showExistingCaseRecordsDataTable)
    {
        this.showExistingCaseRecordsDataTable = showExistingCaseRecordsDataTable;
    }

    /**
     * @return Returns the showEditAssocCaseWithThisCase.
     */
    public boolean isShowEditAssocCaseWithThisCase()
    {
        return showEditAssocCaseWithThisCase;
    }

    /**
     * @param showEditAssocCaseWithThisCase
     *            The showEditAssocCaseWithThisCase to set.
     */
    public void setShowEditAssocCaseWithThisCase(
            boolean showEditAssocCaseWithThisCase)
    {
        this.showEditAssocCaseWithThisCase = showEditAssocCaseWithThisCase;
    }

    /**
     * @return Returns the caseEventsVOList.
     */
    public List getCaseEventsVOList()
    {
        return caseEventsVOList;
    }

    /**
     * @param caseEventsVOList
     *            The caseEventsVOList to set.
     */
    public void setCaseEventsVOList(List caseEventsVOList)
    {
        this.caseEventsVOList = caseEventsVOList;
    }

    /**
     * @return Returns the caseStepsVOList.
     */
    public List getCaseStepsVOList()
    {
        return caseStepsVOList;
    }

    /**
     * @param caseStepsVOList
     *            The caseStepsVOList to set.
     */
    public void setCaseStepsVOList(List caseStepsVOList)
    {
        this.caseStepsVOList = caseStepsVOList;
    }

    /**
     * @return Returns the showCaseEventsDataTable.
     */
    public boolean isShowCaseEventsDataTable()
    {
        return showCaseEventsDataTable;
    }

    /**
     * @param showCaseEventsDataTable
     *            The showCaseEventsDataTable to set.
     */
    public void setShowCaseEventsDataTable(boolean showCaseEventsDataTable)
    {
        this.showCaseEventsDataTable = showCaseEventsDataTable;
    }

    /**
     * @return Returns the showCaseStepsDataTable.
     */
    public boolean isShowCaseStepsDataTable()
    {
        return showCaseStepsDataTable;
    }

    /**
     * @param showCaseStepsDataTable
     *            The showCaseStepsDataTable to set.
     */
    public void setShowCaseStepsDataTable(boolean showCaseStepsDataTable)
    {
        this.showCaseStepsDataTable = showCaseStepsDataTable;
    }

    /**
     * @return Returns the caseEventsVO.
     */
    public CaseEventsVO getCaseEventsVO()
    {
        return caseEventsVO;
    }

    /**
     * @param caseEventsVO
     *            The caseEventsVO to set.
     */
    public void setCaseEventsVO(CaseEventsVO caseEventsVO)
    {
        this.caseEventsVO = caseEventsVO;
    }

    /**
     * @return Returns the caseStepsVO.
     */
    public CaseStepsVO getCaseStepsVO()
    {
        return caseStepsVO;
    }

    /**
     * @param caseStepsVO
     *            The caseStepsVO to set.
     */
    public void setCaseStepsVO(CaseStepsVO caseStepsVO)
    {
        this.caseStepsVO = caseStepsVO;
    }

    /**
     * @return Returns the alertsVOList.
     */
    public List getAlertsVOList()
    {
        return alertsVOList;
    }

    /**
     * @param alertsVOList
     *            The alertsVOList to set.
     */
    public void setAlertsVOList(List alertsVOList)
    {
        this.alertsVOList = alertsVOList;
    }

    /**
     * @return Returns the showAlertsDataTable.
     */
    public boolean isShowAlertsDataTable()
    {
        return showAlertsDataTable;
    }

    /**
     * @param showAlertsDataTable
     *            The showAlertsDataTable to set.
     */
    public void setShowAlertsDataTable(boolean showAlertsDataTable)
    {
        this.showAlertsDataTable = showAlertsDataTable;
    }

    /**
     * @return Returns the alertVO.
     */
    public AlertVO getAlertVO()
    {
        return alertVO;
    }

    /**
     * @param alertVO
     *            The alertVO to set.
     */
    public void setAlertVO(AlertVO alertVO)
    {
        this.alertVO = alertVO;
    }

    /**
     * @return Returns the caseStepsIndex.
     */
    public String getCaseStepsIndex()
    {
        return caseStepsIndex;
    }

    /**
     * @param caseStepsIndex
     *            The caseStepsIndex to set.
     */
    public void setCaseStepsIndex(String caseStepsIndex)
    {
        this.caseStepsIndex = caseStepsIndex;
    }

    /**
     * @return Returns the showCaseStepsMessages.
     */
    public boolean isShowCaseStepsMessages()
    {
        return showCaseStepsMessages;
    }

    /**
     * @param showCaseStepsMessages
     *            The showCaseStepsMessages to set.
     */
    public void setShowCaseStepsMessages(boolean showCaseStepsMessages)
    {
        this.showCaseStepsMessages = showCaseStepsMessages;
    }

    /**
     * @return Returns the caseAlertsIndex.
     */
    public String getCaseAlertsIndex()
    {
        return caseAlertsIndex;
    }

    /**
     * @param caseAlertsIndex
     *            The caseAlertsIndex to set.
     */
    public void setCaseAlertsIndex(String caseAlertsIndex)
    {
        this.caseAlertsIndex = caseAlertsIndex;
    }

    /**
     * @return Returns the showCaseAlertsMessages.
     */
    public boolean isShowCaseAlertsMessages()
    {
        return showCaseAlertsMessages;
    }

    /**
     * @param showCaseAlertsMessages
     *            The showCaseAlertsMessages to set.
     */
    public void setShowCaseAlertsMessages(boolean showCaseAlertsMessages)
    {
        this.showCaseAlertsMessages = showCaseAlertsMessages;
    }

    /**
     * @return Returns the caseEventsIndex.
     */
    public String getCaseEventsIndex()
    {
        return caseEventsIndex;
    }

    /**
     * @param caseEventsIndex
     *            The caseEventsIndex to set.
     */
    public void setCaseEventsIndex(String caseEventsIndex)
    {
        this.caseEventsIndex = caseEventsIndex;
    }

    /**
     * @return Returns the showCaseEventsMessages.
     */
    public boolean isShowCaseEventsMessages()
    {
        return showCaseEventsMessages;
    }

    /**
     * @param showCaseEventsMessages
     *            The showCaseEventsMessages to set.
     */
    public void setShowCaseEventsMessages(boolean showCaseEventsMessages)
    {
        this.showCaseEventsMessages = showCaseEventsMessages;
    }

    /**
     * @return Returns the routingHidden.
     */
    public String getRoutingHidden()
    {
        return routingHidden;
    }

    /**
     * @param routingHidden
     *            The routingHidden to set.
     */
    public void setRoutingHidden(String routingHidden)
    {
        this.routingHidden = routingHidden;
    }

    /**
     * @return Returns the assocCaseAndCRHidden.
     */
   /* public String getAssocCaseAndCRHidden()
    {
        return assocCaseAndCRHidden;
    }*/

    /**
     * @param assocCaseAndCRHidden
     *            The assocCaseAndCRHidden to set.
     */
    /*public void setAssocCaseAndCRHidden(String assocCaseAndCRHidden)
    {
        this.assocCaseAndCRHidden = assocCaseAndCRHidden;
    }*/

    /**
     * @return Returns the caseEventsHidden.
     */
    public String getCaseEventsHidden()
    {
        return caseEventsHidden;
    }

    /**
     * @param caseEventsHidden
     *            The caseEventsHidden to set.
     */
    public void setCaseEventsHidden(String caseEventsHidden)
    {
        this.caseEventsHidden = caseEventsHidden;
    }

    /**
     * @return Returns the caseAlertsHidden.
     */
    public String getCaseAlertsHidden()
    {
        return caseAlertsHidden;
    }

    /**
     * @param caseAlertsHidden
     *            The caseAlertsHidden to set.
     */
    public void setCaseAlertsHidden(String caseAlertsHidden)
    {
        this.caseAlertsHidden = caseAlertsHidden;
    }

    /**
     * @return Returns the caseStepsHidden.
     */
    public String getCaseStepsHidden()
    {
        return caseStepsHidden;
    }

    /**
     * @param caseStepsHidden
     *            The caseStepsHidden to set.
     */
    public void setCaseStepsHidden(String caseStepsHidden)
    {
        this.caseStepsHidden = caseStepsHidden;
    }

    /**
     * @return Returns the showDepartments.
     */
    public boolean isShowDepartments()
    {
        return showDepartments;
    }

    /**
     * @param showDepartments
     *            The showDepartments to set.
     */
    public void setShowDepartments(boolean showDepartments)
    {
        this.showDepartments = showDepartments;
    }

    /**
     * @return Returns the showUserDepartments.
     */
    public boolean isShowUserDepartments()
    {
        return showUserDepartments;
    }

    /**
     * @param showUserDepartments
     *            The showUserDepartments to set.
     */
    public void setShowUserDepartments(boolean showUserDepartments)
    {
        this.showUserDepartments = showUserDepartments;
    }

    /**
     * @return Returns the showUsers.
     */
    public boolean isShowUsers()
    {
        return showUsers;
    }

    /**
     * @param showUsers
     *            The showUsers to set.
     */
    public void setShowUsers(boolean showUsers)
    {
        this.showUsers = showUsers;
    }

    /**
     * @return Returns the routingIndex.
     */
    public String getRoutingIndex()
    {
        return routingIndex;
    }

    /**
     * @param routingIndex
     *            The routingIndex to set.
     */
    public void setRoutingIndex(String routingIndex)
    {
        this.routingIndex = routingIndex;
    }

    /**
     * @return Returns the attachmentVOList.
     */
    public List getAttachmentVOList()
    {
        return attachmentVOList;
    }

    /**
     * @param attachmentVOList
     *            The attachmentVOList to set.
     */
    public void setAttachmentVOList(List attachmentVOList)
    {
        this.attachmentVOList = attachmentVOList;
    }

    /**
     * @return Returns the caseAttachmentsHidden.
     */
    public String getCaseAttachmentsHidden()
    {
        return caseAttachmentsHidden;
    }

    /**
     * @param caseAttachmentsHidden
     *            The caseAttachmentsHidden to set.
     */
    public void setCaseAttachmentsHidden(String caseAttachmentsHidden)
    {
        this.caseAttachmentsHidden = caseAttachmentsHidden;
    }

    /**
     * @return Returns the showAttachmentDataTable.
     */
    public boolean isShowAttachmentDataTable()
    {
        return showAttachmentDataTable;
    }

    /**
     * @param showAttachmentDataTable
     *            The showAttachmentDataTable to set.
     */
    public void setShowAttachmentDataTable(boolean showAttachmentDataTable)
    {
        this.showAttachmentDataTable = showAttachmentDataTable;
    }

    /** holds the LOB codes */
    private List lobCodeList = Collections.EMPTY_LIST;

    /** holds the CaseType codes */
    private List caseTypeList = new ArrayList();

    /** holds the Agency site code */
    private List agencySiteList = Collections.EMPTY_LIST;

    /** holds the Priority codes */
    private List priorityList = Collections.EMPTY_LIST;

    /** holds the Status codes */
    private List statusList = new ArrayList();

    /** holds the Biopsy Findings */
    private List biopsyFindingsList = Collections.EMPTY_LIST;

    /** holds the Recommandation codes */
    private List recommenList = Collections.EMPTY_LIST;

    /** holds the Stage */
    private List stageBCCPList =Collections.EMPTY_LIST;

    /** holds the Treatment date */
    private List treatmentStartedList = Collections.EMPTY_LIST;

    /** holds the Application Type */
    private List applicationTypeList = Collections.EMPTY_LIST;

    /** holds the Authorised Rep */
    private List authorisedRepList = Collections.EMPTY_LIST;

    /** holds the close code */
    private List closeCodeList = Collections.EMPTY_LIST;

    /** holds hte Denial Reason */
    private List denialReasonList = Collections.EMPTY_LIST;

    /** holds the Evaluation Type codes */
    private List evaluationTypeList = Collections.EMPTY_LIST;

    /** holds the Unusual Circumstances codes */
    private List unusualCircumstancesList = Collections.EMPTY_LIST;

    /** holds the Medical diagnosis */
    private List medicalDiagnosisList = Collections.EMPTY_LIST;

    /** holds the Psychiatric Diagnosis */
    private List psychiatricDiagnosisList = Collections.EMPTY_LIST;

    /** holds the event Type codes */
    private List eventTypeList = new ArrayList();

    /** holds the Event status codes */
    private List eventStatus = Collections.EMPTY_LIST;

    /** holds the Event alert days */
    private List eventAlertDaysList = Collections.EMPTY_LIST;

    /** holds the Event alert based on */
    private List eventAlertBasedOn = Collections.EMPTY_LIST;

    /** holds the Alert status codes */
    private List alertStatusList = Collections.EMPTY_LIST;

    /** holds the Alert type codes */
    private List alertType = Collections.EMPTY_LIST;

    /** holds the Step alert days */
    private List stepAlertDays = Collections.EMPTY_LIST;

    /** holds the completion base on codes */
    private List stepCompBasedOn = Collections.EMPTY_LIST;

    /** holds the case Step status codes */
    private List stepStatusList = Collections.EMPTY_LIST;

    /** holds the case DDU level of care */
    private List levelOfCareList = Collections.EMPTY_LIST;

    /** holds the Diagnosis code 1 & 2 valid valies */
    private List diagnosisCode12 = Collections.EMPTY_LIST;

    /** holds the Exam/Test 1 & 2 valid values */
    private List examTest12 = Collections.EMPTY_LIST;

    /** holds the Templates */
    private List templateList = new ArrayList();

    /** holds the Short desc of case Type */
    private List caseTypeShortDesc = new ArrayList();
    
    /** holds the holidays list */
    private List holidaysList = new ArrayList();
    
    /** holds the Picture date list */
    private List pictureDateList = new ArrayList();
    
    /** holds the Picture Date map */
    private Map picIDMap = new HashMap();
    
    /** holds the Picture Date Value map */
    private Map picValMap = new HashMap();

    /** holds the Case Step code */
    private List caseStepCodeList = Collections.EMPTY_LIST;
    
    /** Holds alert based on list for Case Type Step */
    private List stepAlertBasedOn = new ArrayList();

    /**
     * @return Returns the caseStepCodeList.
     */
    public List getCaseStepCodeList()
    {
        return caseStepCodeList;
    }

    /**
     * @param caseStepCodeList
     *            The caseStepCodeList to set.
     */
    public void setCaseStepCodeList(List caseStepCodeList)
    {
        this.caseStepCodeList = caseStepCodeList;
    }
    /**
     * @return Returns the levelOfCareList.
     */
    public List getLevelOfCareList()
    {
        return levelOfCareList;
    }

    /**
     * @param levelOfCareList
     *            The levelOfCareList to set.
     */
    public void setLevelOfCareList(List levelOfCareList)
    {
        this.levelOfCareList = levelOfCareList;
    }

    /**
     * @return Returns the stepStatusList.
     */
    public List getStepStatusList()
    {
        return stepStatusList;
    }

    /**
     * @param stepStatusList
     *            The stepStatusList to set.
     */
    public void setStepStatusList(List stepStatusList)
    {
        this.stepStatusList = stepStatusList;
    }

    /**
     * @return Returns the stepCompBasedOn.
     */
    public List getStepCompBasedOn()
    {
        return stepCompBasedOn;
    }

    /**
     * @param stepCompBasedOn
     *            The stepCompBasedOn to set.
     */
    public void setStepCompBasedOn(List stepCompBasedOn)
    {
        this.stepCompBasedOn = stepCompBasedOn;
    }

    /**
     * @return Returns the stepAlertDays.
     */
    public List getStepAlertDays()
    {
        return stepAlertDays;
    }

    /**
     * @param stepAlertDays
     *            The stepAlertDays to set.
     */
    public void setStepAlertDays(List stepAlertDays)
    {
        this.stepAlertDays = stepAlertDays;
    }

    /**
     * @return Returns the alertType.
     */
    public List getAlertType()
    {
        return alertType;
    }

    /**
     * @param alertType
     *            The alertType to set.
     */
    public void setAlertType(List alertType)
    {
        this.alertType = alertType;
    }

    /**
     * @return Returns the alertStatusList.
     */
    public List getAlertStatusList()
    {
        return alertStatusList;
    }

    /**
     * @param alertStatusList
     *            The alertStatusList to set.
     */
    public void setAlertStatusList(List alertStatusList)
    {
        this.alertStatusList = alertStatusList;
    }

    /**
     * @return Returns the eventAlertBasedOn.
     */
    public List getEventAlertBasedOn()
    {
        return eventAlertBasedOn;
    }

    /**
     * @param eventAlertBasedOn
     *            The eventAlertBasedOn to set.
     */
    public void setEventAlertBasedOn(List eventAlertBasedOn)
    {
        this.eventAlertBasedOn = eventAlertBasedOn;
    }

    /**
     * @return Returns the eventAlertDaysList.
     */
    public List getEventAlertDaysList()
    {
        return eventAlertDaysList;
    }

    /**
     * @param eventAlertDaysList
     *            The eventAlertDaysList to set.
     */
    public void setEventAlertDaysList(List eventAlertDaysList)
    {
        this.eventAlertDaysList = eventAlertDaysList;
    }

    /**
     * @return Returns the eventStatus.
     */
    public List getEventStatus()
    {
        return eventStatus;
    }

    /**
     * @param eventStatus
     *            The eventStatus to set.
     */
    public void setEventStatus(List eventStatus)
    {
        this.eventStatus = eventStatus;
    }

    /**
     * @return Returns the eventTypeList.
     */
    public List getEventTypeList()
    {
        return eventTypeList;
    }

    /**
     * @param eventTypeList
     *            The eventTypeList to set.
     */
    public void setEventTypeList(List eventTypeList)
    {
        this.eventTypeList = eventTypeList;
    }

    /**
     * @return Returns the psychiatricDiagnosisList.
     */
    public List getPsychiatricDiagnosisList()
    {
        return psychiatricDiagnosisList;
    }

    /**
     * @param psychiatricDiagnosisList
     *            The psychiatricDiagnosisList to set.
     */
    public void setPsychiatricDiagnosisList(List psychiatricDiagnosisList)
    {
        this.psychiatricDiagnosisList = psychiatricDiagnosisList;
    }

    /**
     * @return Returns the medicalDiagnosisList.
     */
    public List getMedicalDiagnosisList()
    {
        return medicalDiagnosisList;
    }

    /**
     * @param medicalDiagnosisList
     *            The medicalDiagnosisList to set.
     */
    public void setMedicalDiagnosisList(List medicalDiagnosisList)
    {
        this.medicalDiagnosisList = medicalDiagnosisList;
    }

    /**
     * @return Returns the unusualCircumstancesList.
     */
    public List getUnusualCircumstancesList()
    {
        return unusualCircumstancesList;
    }

    /**
     * @param unusualCircumstancesList
     *            The unusualCircumstancesList to set.
     */
    public void setUnusualCircumstancesList(List unusualCircumstancesList)
    {
        this.unusualCircumstancesList = unusualCircumstancesList;
    }

    /**
     * @return Returns the evaluationTypeList.
     */
    public List getEvaluationTypeList()
    {
        return evaluationTypeList;
    }

    /**
     * @param evaluationTypeList
     *            The evaluationTypeList to set.
     */
    public void setEvaluationTypeList(List evaluationTypeList)
    {
        this.evaluationTypeList = evaluationTypeList;
    }

    /**
     * @return Returns the denialReasonList.
     */
    public List getDenialReasonList()
    {
        return denialReasonList;
    }

    /**
     * @param denialReasonList
     *            The denialReasonList to set.
     */
    public void setDenialReasonList(List denialReasonList)
    {
        this.denialReasonList = denialReasonList;
    }

    /**
     * @return Returns the closeCodeList.
     */
    public List getCloseCodeList()
    {
        return closeCodeList;
    }

    /**
     * @param closeCodeList
     *            The closeCodeList to set.
     */
    public void setCloseCodeList(List closeCodeList)
    {
        this.closeCodeList = closeCodeList;
    }

    /**
     * @return Returns the authorisedRepList.
     */
    public List getAuthorisedRepList()
    {
        return authorisedRepList;
    }

    /**
     * @param authorisedRepList
     *            The authorisedRepList to set.
     */
    public void setAuthorisedRepList(List authorisedRepList)
    {
        this.authorisedRepList = authorisedRepList;
    }

    /**
     * @return Returns the applicationTypeList.
     */
    public List getApplicationTypeList()
    {
        return applicationTypeList;
    }

    /**
     * @param applicationTypeList
     *            The applicationTypeList to set.
     */
    public void setApplicationTypeList(List applicationTypeList)
    {
        this.applicationTypeList = applicationTypeList;
    }

    /**
     * @return Returns the treatmentStartedList.
     */
    public List getTreatmentStartedList()
    {
        return treatmentStartedList;
    }

    /**
     * @param treatmentStartedList
     *            The treatmentStartedList to set.
     */
    public void setTreatmentStartedList(List treatmentStartedList)
    {
        this.treatmentStartedList = treatmentStartedList;
    }

    /**
     * @return Returns the stageBCCPList.
     */
    public List getStageBCCPList()
    {
        return stageBCCPList;
    }

    /**
     * @param stageBCCPList
     *            The stageBCCPList to set.
     */
    public void setStageBCCPList(List stageBCCPList)
    {
        this.stageBCCPList = stageBCCPList;
    }

    /**
     * @return Returns the recommenList.
     */
    public List getRecommenList()
    {
        return recommenList;
    }

    /**
     * @param recommenList
     *            The recommenList to set.
     */
    public void setRecommenList(List recommenList)
    {
        this.recommenList = recommenList;
    }

    /**
     * @return Returns the biopsyFindingsList.
     */
    public List getBiopsyFindingsList()
    {
        return biopsyFindingsList;
    }

    /**
     * @param biopsyFindingsList
     *            The biopsyFindingsList to set.
     */
    public void setBiopsyFindingsList(List biopsyFindingsList)
    {
        this.biopsyFindingsList = biopsyFindingsList;
    }

    /**
     * @return Returns the statusList.
     */
    public List getStatusList()
    {
        return statusList;
    }

    /**
     * @param statusList
     *            The statusList to set.
     */
    public void setStatusList(List statusList)
    {
        this.statusList = statusList;
    }

    /**
     * @return Returns the priorityList.
     */
    public List getPriorityList()
    {
        return priorityList;
    }

    /**
     * @param priorityList
     *            The priorityList to set.
     */
    public void setPriorityList(List priorityList)
    {
        this.priorityList = priorityList;
    }

    /**
     * @return Returns the caseTypeList.
     */
    public List getCaseTypeList()
    {
        return caseTypeList;
    }

    /**
     * @param caseTypeList
     *            The caseTypeList to set.
     */
    public void setCaseTypeList(List caseTypeList)
    {
        this.caseTypeList = caseTypeList;
    }

    /**
     * @return Returns the lobCodeList.
     */
    public List getLobCodeList()
    {
        return lobCodeList;
    }

    /**
     * @param lobCodeList
     *            The lobCodeList to set.
     */
    public void setLobCodeList(List lobCodeList)
    {
        this.lobCodeList = lobCodeList;
    }

   

    /**
     * @param map -
     *            Map object containing the area code and element name.
     * @param referenceDataConstant -
     *            Element name.
     * @param functionalArea -
     *            Functional Area code.
     * @return List - with valid values populated.
     */
    private List getValidData(Map map, String referenceDataConstant,
            String functionalArea)
    {
    	log.info(BEGINMETHOD + " getValidData");
        List validList = new ArrayList();
        if (!referenceDataConstant
                .equals(ReferenceServiceDataConstants.A_RSN_CD_APP))
        {
            validList.add(new SelectItem(
                    MaintainContactManagementUIConstants.EMPTY_STRING,
                    MaintainContactManagementUIConstants.EMPTY_STRING));
        }
        String validValueCodeDesc = null;        
        List validValuesList = (List) map.get(functionalArea + "#"
                + referenceDataConstant);

        int validValuesSize = validValuesList.size();
        for (int i = 0; i < validValuesSize; i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) validValuesList
                    .get(i);
            validValueCodeDesc = refVo.getValidValueCode() + "-"
                    + refVo.getShortDescription();
            validList.add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeDesc));
        }
    	log.info(ENDMETHOD + " getValidData");
        return validList;
    }
    
    

    /**
     * @return Returns the attachmentIndex.
     */
    public String getAttachmentIndex()
    {
        return attachmentIndex;
    }

    /**
     * @param attachmentIndex
     *            The attachmentIndex to set.
     */
    public void setAttachmentIndex(String attachmentIndex)
    {
        this.attachmentIndex = attachmentIndex;
    }

    /**
     * @return Returns the actionCode.
     */
    public String getActionCode()
    {
        return actionCode;
    }

    /**
     * @param actionCode
     *            The actionCode to set.
     */
    public void setActionCode(String actionCode)
    {
        this.actionCode = actionCode;
    }

    /**
     * @return Returns the userList.
     */
    public List getUserList()
    {
        return userList;
    }

    /**
     * @param userList
     *            The userList to set.
     */
    public void setUserList(List userList)
    {
        this.userList = userList;
    }

    /**
     * @return Returns the alternateIdentifiersVO.
     */
    public AlternateIdentifiersVO getAlternateIdentifiersVO()
    {
        return alternateIdentifiersVO;
    }

    /**
     * @param alternateIdentifiersVO
     *            The alternateIdentifiersVO to set.
     */
    public void setAlternateIdentifiersVO(
            AlternateIdentifiersVO alternateIdentifiersVO)
    {
        this.alternateIdentifiersVO = alternateIdentifiersVO;
    }

    /**
     * @return Returns the caseTypeDOList.
     */
    public List getCaseTypeDOList()
    {
        return caseTypeDOList;
    }

    /**
     * @param caseTypeDOList
     *            The caseTypeDOList to set.
     */
    public void setCaseTypeDOList(List caseTypeDOList)
    {
        this.caseTypeDOList = caseTypeDOList;
    }

    /**
     * @return Returns the entityID.
     */
    public String getEntityID()
    {
        return entityID;
    }

    /**
     * @param entityID
     *            The entityID to set.
     */
    public void setEntityID(String entityID)
    {
        this.entityID = entityID;
    }

    /**
     * @return Returns the entityType.
     */
    public String getEntityType()
    {
        return entityType;
    }

    /**
     * @param entityType
     *            The entityType to set.
     */
    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }

    /**
     * @return Returns the listOfDepartments.
     */
    public List getListOfDepartments()
    {
        return listOfDepartments;
    }

    /**
     * @param listOfDepartments
     *            The listOfDepartments to set.
     */
    public void setListOfDepartments(List listOfDepartments)
    {
        this.listOfDepartments = listOfDepartments;
    }

    /**
     * @return Returns the disableCaseType.
     */
    public boolean isDisableCaseType()
    {
        return disableCaseType;
    }

    /**
     * @param disableCaseType
     *            The disableCaseType to set.
     */
    public void setDisableCaseType(boolean disableCaseType)
    {
        this.disableCaseType = disableCaseType;
    }

    /**
     * @return Returns the showCaseMessage.
     */
    public boolean isShowCaseMessage()
    {
        return showCaseMessage;
    }

    /**
     * @param showCaseMessage
     *            The showCaseMessage to set.
     */
    public void setShowCaseMessage(boolean showCaseMessage)
    {
        this.showCaseMessage = showCaseMessage;
    }

    /**
     * @return Returns the showCaseRoutingMessages.
     */
    public boolean isShowCaseRoutingMessages()
    {
        return showCaseRoutingMessages;
    }

    /**
     * @param showCaseRoutingMessages
     *            The showCaseRoutingMessages to set.
     */
    public void setShowCaseRoutingMessages(boolean showCaseRoutingMessages)
    {
        this.showCaseRoutingMessages = showCaseRoutingMessages;
    }

    /**
     * @return Returns the showAppealScreen.
     */
    public boolean isShowAppealScreen()
    {
        return showAppealScreen;
    }

    /**
     * @param showAppealScreen
     *            The showAppealScreen to set.
     */
    public void setShowAppealScreen(boolean showAppealScreen)
    {
        this.showAppealScreen = showAppealScreen;
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
     * @return Returns the caseLandResponse.
     */
    public String getCaseLandResponse()
    {
        return caseLandResponse;
    }

    /**
     * @param caseLandResponse
     *            The caseLandResponse to set.
     */
    public void setCaseLandResponse(String caseLandResponse)
    {
        this.caseLandResponse = caseLandResponse;
    }

    /**
     * @return Returns the agencySiteList.
     */
    public List getAgencySiteList()
    {
        return agencySiteList;
    }

    /**
     * @param agencySiteList
     *            The agencySiteList to set.
     */
    public void setAgencySiteList(List agencySiteList)
    {
        this.agencySiteList = agencySiteList;
    }

    /**
     * @return Returns the showReviewReq.
     */
    public boolean isShowReviewReq()
    {
        return showReviewReq;
    }

    /**
     * @param showReviewReq
     *            The showReviewReq to set.
     */
    public void setShowReviewReq(boolean showReviewReq)
    {
        this.showReviewReq = showReviewReq;
    }

    /**
     * @return Returns the assoCaseList.
     */
    public List getAssoCaseList()
    {
        return assoCaseList;
    }

    /**
     * @param assoCaseList
     *            The assoCaseList to set.
     */
    public void setAssoCaseList(List assoCaseList)
    {
        this.assoCaseList = assoCaseList;
    }

    /**
     * @return Returns the assoCRList.
     */
    public List getAssoCRList()
    {
        return assoCRList;
    }

    /**
     * @param assoCRList
     *            The assoCRList to set.
     */
    public void setAssoCRList(List assoCRList)
    {
        this.assoCRList = assoCRList;
    }

    /**
     * @return Returns the diagnosisCode12.
     */
    public List getDiagnosisCode12()
    {
        return diagnosisCode12;
    }

    /**
     * @param diagnosisCode12
     *            The diagnosisCode12 to set.
     */
    public void setDiagnosisCode12(List diagnosisCode12)
    {
        this.diagnosisCode12 = diagnosisCode12;
    }

    /**
     * @return Returns the examTest12.
     */
    public List getExamTest12()
    {
        return examTest12;
    }

    /**
     * @param examTest12
     *            The examTest12 to set.
     */
    public void setExamTest12(List examTest12)
    {
        this.examTest12 = examTest12;
    }

    /**
     * @return Returns the showDDUAppointmentScreen.
     */
    public boolean isShowDDUAppointmentScreen()
    {
        return showDDUAppointmentScreen;
    }

    /**
     * @param showDDUAppointmentScreen
     *            The showDDUAppointmentScreen to set.
     */
    public void setShowDDUAppointmentScreen(boolean showDDUAppointmentScreen)
    {
        this.showDDUAppointmentScreen = showDDUAppointmentScreen;
    }

    /**
     * @return Returns the templateList.
     */
    public List getTemplateList()
    {
        return templateList;
    }

    /**
     * @param templateList
     *            The templateList to set.
     */
    public void setTemplateList(List templateList)
    {
        this.templateList = templateList;
    }

    /**
     * @return Returns the customFieldDOList.
     */
    public List getCustomFieldDOList()
    {
        return customFieldDOList;
    }

    /**
     * @param customFieldDOList
     *            The customFieldDOList to set.
     */
    public void setCustomFieldDOList(List customFieldDOList)
    {
        this.customFieldDOList = customFieldDOList;
    }

    /**
     * @return Returns the showGeneralCaseTypeScreens.
     */
    public boolean isShowGeneralCaseTypeScreens()
    {
        return showGeneralCaseTypeScreens;
    }

    /**
     * @param showGeneralCaseTypeScreens
     *            The showGeneralCaseTypeScreens to set.
     */
    public void setShowGeneralCaseTypeScreens(boolean showGeneralCaseTypeScreens)
    {
        this.showGeneralCaseTypeScreens = showGeneralCaseTypeScreens;
    }

    /**
     * @return Returns the userMap.
     */
   /* public Map getUserMap()
    {
        return userMap;
    }*/

    /**
     * @param userMap
     *            The userMap to set.
     */
    /*public void setUserMap(Map userMap)
    {
        this.userMap = userMap;
    }*/

    /**
     * @return Returns the lobDOMap.
     */
    public Map getLobDOMap()
    {
        return lobDOMap;
    }

    /**
     * @param lobDOMap
     *            The lobDOMap to set.
     */
    public void setLobDOMap(Map lobDOMap)
    {
        this.lobDOMap = lobDOMap;
    }

    /**
     * @return Returns the businessUnit.
     */
    public String getBusinessUnit()
    {
        return businessUnit;
    }

    /**
     * @param businessUnit
     *            The businessUnit to set.
     */
    public void setBusinessUnit(String businessUnit)
    {
        this.businessUnit = businessUnit;
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
    }

    /**
     * @return Returns the lobSK.
     */
    public Long getLobSK()
    {
        return lobSK;
    }

    /**
     * @param lobSK
     *            The lobSK to set.
     */
    public void setLobSK(Long lobSK)
    {
        this.lobSK = lobSK;
    }

    /**
     * @return Returns the associatedCaseVO.
     */
    public AssociatedCaseVO getAssociatedCaseVO()
    {
        return associatedCaseVO;
    }

    /**
     * @param associatedCaseVO
     *            The associatedCaseVO to set.
     */
    public void setAssociatedCaseVO(AssociatedCaseVO associatedCaseVO)
    {
        this.associatedCaseVO = associatedCaseVO;
    }

    /**
     * @return Returns the associatedCorrespondenceVO.
     */
    public AssociatedCorrespondenceVO getAssociatedCorrespondenceVO()
    {
        return associatedCorrespondenceVO;
    }

    /**
     * @param associatedCorrespondenceVO
     *            The associatedCorrespondenceVO to set.
     */
    public void setAssociatedCorrespondenceVO(
            AssociatedCorrespondenceVO associatedCorrespondenceVO)
    {
        this.associatedCorrespondenceVO = associatedCorrespondenceVO;
    }

    /**
     * @return Returns the cancelLinkUpdate.
     */
    public boolean isCancelLinkUpdate()
    {
        return cancelLinkUpdate;
    }

    /**
     * @param cancelLinkUpdate
     *            The cancelLinkUpdate to set.
     */
    public void setCancelLinkUpdate(boolean cancelLinkUpdate)
    {
        this.cancelLinkUpdate = cancelLinkUpdate;
    }

    /**
     * @return Returns the superviser.
     */
    public boolean isSuperviser()
    {
        return superviser;
    }

    /**
     * @param superviser
     *            The superviser to set.
     */
    public void setSuperviser(boolean superviser)
    {
        this.superviser = superviser;
    }

    /**
     * @return Returns the superviserRevReqInd.
     */
    public boolean isSuperviserRevReqInd()
    {
        return superviserRevReqInd;
    }

    /**
     * @param superviserRevReqInd
     *            The superviserRevReqInd to set.
     */
    public void setSuperviserRevReqInd(boolean superviserRevReqInd)
    {
        this.superviserRevReqInd = superviserRevReqInd;
    }

    /**
     * @return Returns the superviserRevReqIndForCaseType.
     */
    public boolean isSuperviserRevReqIndForCaseType()
    {
        return superviserRevReqIndForCaseType;
    }

    /**
     * @param superviserRevReqIndForCaseType
     *            The superviserRevReqIndForCaseType to set.
     */
    public void setSuperviserRevReqIndForCaseType(
            boolean superviserRevReqIndForCaseType)
    {
        this.superviserRevReqIndForCaseType = superviserRevReqIndForCaseType;
    }

    /**
     * @return Returns the caseTypeSK.
     */
    public Long getCaseTypeSK()
    {
        return caseTypeSK;
    }

    /**
     * @param caseTypeSK
     *            The caseTypeSK to set.
     */
    public void setCaseTypeSK(Long caseTypeSK)
    {
        this.caseTypeSK = caseTypeSK;
    }

    /**
     * @return Returns the routingDeptList.
     */
    public List getRoutingDeptList()
    {
        return routingDeptList;
    }

    /**
     * @param routingDeptList
     *            The routingDeptList to set.
     */
    public void setRoutingDeptList(List routingDeptList)
    {
        this.routingDeptList = routingDeptList;
    }

    /**
     * @return Returns the routingUserList.
     */
    public List getRoutingUserList()
    {
        return routingUserList;
    }

    /**
     * @param routingUserList
     *            The routingUserList to set.
     */
    public void setRoutingUserList(List routingUserList)
    {
        this.routingUserList = routingUserList;
    }

    /**
     * @return Returns the deptMap.
     */
    public Map getDeptMap()
    {
        return deptMap;
    }

    /**
     * @param deptMap
     *            The deptMap to set.
     */
    public void setDeptMap(Map deptMap)
    {
        this.deptMap = deptMap;
    }

    /**
     * @return Returns the showInqAbtFor.
     */
    public boolean isShowInqAbtFor()
    {
        return showInqAbtFor;
    }

    /**
     * @param showInqAbtFor
     *            The showInqAbtFor to set.
     */
    public void setShowInqAbtFor(boolean showInqAbtFor)
    {
        this.showInqAbtFor = showInqAbtFor;
    }

    /**
     * @return Returns the showInqAbtMember.
     */
    public boolean isShowInqAbtMember()
    {
        return showInqAbtMember;
    }

    /**
     * @param showInqAbtMember
     *            The showInqAbtMember to set.
     */
    public void setShowInqAbtMember(boolean showInqAbtMember)
    {
        this.showInqAbtMember = showInqAbtMember;
    }

    /**
     * @return Returns the showInqAbtProvider.
     */
    public boolean isShowInqAbtProvider()
    {
        return showInqAbtProvider;
    }

    /**
     * @param showInqAbtProvider
     *            The showInqAbtProvider to set.
     */
    public void setShowInqAbtProvider(boolean showInqAbtProvider)
    {
        this.showInqAbtProvider = showInqAbtProvider;
    }

    /**
     * @return Returns the routingDeptMap.
     */
    public Map getRoutingDeptMap()
    {
        return routingDeptMap;
    }

    /**
     * @param routingDeptMap
     *            The routingDeptMap to set.
     */
    public void setRoutingDeptMap(Map routingDeptMap)
    {
        this.routingDeptMap = routingDeptMap;
    }

    /**
     * @return Returns the enableFollowUpMon.
     */
    public boolean isEnableFollowUpMon()
    {
        return enableFollowUpMon;
    }

    /**
     * @param enableFollowUpMon
     *            The enableFollowUpMon to set.
     */
    public void setEnableFollowUpMon(boolean enableFollowUpMon)
    {
        this.enableFollowUpMon = enableFollowUpMon;
    }

    /**
     * @return Returns the enableTumorAndNodes.
     */
    public boolean isEnableTumorAndNodes()
    {
        return enableTumorAndNodes;
    }

    /**
     * @param enableTumorAndNodes
     *            The enableTumorAndNodes to set.
     */
    public void setEnableTumorAndNodes(boolean enableTumorAndNodes)
    {
        this.enableTumorAndNodes = enableTumorAndNodes;
    }

    /**
     * @return Returns the enableTreatementStDates.
     */
    public boolean isEnableTreatementStDates()
    {
        return enableTreatementStDates;
    }

    /**
     * @param enableTreatementStDates
     *            The enableTreatementStDates to set.
     */
    public void setEnableTreatementStDates(boolean enableTreatementStDates)
    {
        this.enableTreatementStDates = enableTreatementStDates;
    }

    /**
     * @return Returns the enableFieldAuditFields.
     */
    public boolean isEnableFieldAuditFields()
    {
        return enableFieldAuditFields;
    }

    /**
     * @param enableFieldAuditFields
     *            The enableFieldAuditFields to set.
     */
    public void setEnableFieldAuditFields(boolean enableFieldAuditFields)
    {
        this.enableFieldAuditFields = enableFieldAuditFields;
    }

    /**
     * @return Returns the enableHomeOfficeInd.
     */
    public boolean isEnableHomeOfficeInd()
    {
        return enableHomeOfficeInd;
    }

    /**
     * @param enableHomeOfficeInd
     *            The enableHomeOfficeInd to set.
     */
    public void setEnableHomeOfficeInd(boolean enableHomeOfficeInd)
    {
        this.enableHomeOfficeInd = enableHomeOfficeInd;
    }

    /**
     * @return Returns the enablePayRatesFields.
     */
    public boolean isEnablePayRatesFields()
    {
        return enablePayRatesFields;
    }

    /**
     * @param enablePayRatesFields
     *            The enablePayRatesFields to set.
     */
    public void setEnablePayRatesFields(boolean enablePayRatesFields)
    {
        this.enablePayRatesFields = enablePayRatesFields;
    }

    /**
     * @return Returns the enablePictureDate.
     */
    public boolean isEnablePictureDate()
    {
        return enablePictureDate;
    }

    /**
     * @param enablePictureDate
     *            The enablePictureDate to set.
     */
    public void setEnablePictureDate(boolean enablePictureDate)
    {
        this.enablePictureDate = enablePictureDate;
    }

    /**
     * @return Returns the enableRaterSetting.
     */
    public boolean isEnableRaterSetting()
    {
        return enableRaterSetting;
    }

    /**
     * @param enableRaterSetting
     *            The enableRaterSetting to set.
     */
    public void setEnableRaterSetting(boolean enableRaterSetting)
    {
        this.enableRaterSetting = enableRaterSetting;
    }

    /**
     * @return Returns the enableStateFiscal.
     */
    public boolean isEnableStateFiscal()
    {
        return enableStateFiscal;
    }

    /**
     * @param enableStateFiscal
     *            The enableStateFiscal to set.
     */
    public void setEnableStateFiscal(boolean enableStateFiscal)
    {
        this.enableStateFiscal = enableStateFiscal;
    }

    /**
     * @return Returns the lobSKAndDeptSKMap.
     */
    public Map getLobSKAndDeptSKMap()
    {
        return lobSKAndDeptSKMap;
    }

    /**
     * @param lobSKAndDeptSKMap
     *            The lobSKAndDeptSKMap to set.
     */
    public void setLobSKAndDeptSKMap(Map lobSKAndDeptSKMap)
    {
        this.lobSKAndDeptSKMap = lobSKAndDeptSKMap;
    }

    /**
     * @return Returns the userIDsList.
     */
    public List getUserIDsList()
    {
        return userIDsList;
    }

    /**
     * @param userIDsList
     *            The userIDsList to set.
     */
    public void setUserIDsList(List userIDsList)
    {
        this.userIDsList = userIDsList;
    }

    /**
     * @return Returns the userIDsMap.
     */
   /* public Map getUserIDsMap()
    {
        return userIDsMap;
    }*/

    /**
     * @param userIDsMap
     *            The userIDsMap to set.
     */
    /*public void setUserIDsMap(Map userIDsMap)
    {
        this.userIDsMap = userIDsMap;
    }*/

    /**
     * @return Returns the enableBiopsyFindings.
     */
    public boolean isEnableBiopsyFindings()
    {
        return enableBiopsyFindings;
    }

    /**
     * @param enableBiopsyFindings
     *            The enableBiopsyFindings to set.
     */
    public void setEnableBiopsyFindings(boolean enableBiopsyFindings)
    {
        this.enableBiopsyFindings = enableBiopsyFindings;
    }

    /**
     * @return Returns the userIDAndWUMap.
     */
   /* public Map getUserIDAndWUMap()
    {
        return userIDAndWUMap;
    }*/

    /**
     * @param userIDAndWUMap
     *            The userIDAndWUMap to set.
     */
   /* public void setUserIDAndWUMap(Map userIDAndWUMap)
    {
        this.userIDAndWUMap = userIDAndWUMap;
    }*/

    /**
     * @return Returns the caseNotesList.
     */
    public List getCaseNotesList()
    {
        return caseNotesList;
    }

    /**
     * @param caseNotesList
     *            The caseNotesList to set.
     */
    public void setCaseNotesList(List caseNotesList)
    {
        this.caseNotesList = caseNotesList;
    }

    /**
     * @return Returns the enableSaveLink.
     */
    public boolean isEnableSaveLink()
    {
        return enableSaveLink;
    }

    /**
     * @param enableSaveLink
     *            The enableSaveLink to set.
     */
    public void setEnableSaveLink(boolean enableSaveLink)
    {
        this.enableSaveLink = enableSaveLink;
    }

    /**
     * @return Returns the customFieldValueVO.
     */
    public CustomFieldValueVO getCustomFieldValueVO()
    {
        return customFieldValueVO;
    }

    /**
     * @param customFieldValueVO
     *            The customFieldValueVO to set.
     */
    public void setCustomFieldValueVO(CustomFieldValueVO customFieldValueVO)
    {
        this.customFieldValueVO = customFieldValueVO;
    }

    /**
     * @return Returns the showCaseAttachMessages.
     */
    public boolean isShowCaseAttachMessages()
    {
        return showCaseAttachMessages;
    }

    /**
     * @param showCaseAttachMessages
     *            The showCaseAttachMessages to set.
     */
    public void setShowCaseAttachMessages(boolean showCaseAttachMessages)
    {
        this.showCaseAttachMessages = showCaseAttachMessages;
    }

    //Commented for HeapDump Issue
    /**
     * @return Returns the formElements.
     */
   /* public HtmlPanelGrid getFormElements()
    {
        return formElements;
    }*/

    /**
     * @param formElements
     *            The formElements to set.
     */
    /*public void setFormElements(HtmlPanelGrid formElements)
    {
        this.formElements = formElements;
    }*/

    /**
     * @return Returns the enableDetachInd.
     */
    public boolean isEnableDetachInd()
    {
        return enableDetachInd;
    }

    /**
     * @param enableDetachInd
     *            The enableDetachInd to set.
     */
    public void setEnableDetachInd(boolean enableDetachInd)
    {
        this.enableDetachInd = enableDetachInd;
    }

    /**
     * @return Returns the enableCaseEventsLetterLink.
     */
    public boolean isEnableCaseEventsLetterLink()
    {
        return enableCaseEventsLetterLink;
    }

    /**
     * @param enableCaseEventsLetterLink
     *            The enableCaseEventsLetterLink to set.
     */
    public void setEnableCaseEventsLetterLink(boolean enableCaseEventsLetterLink)
    {
        this.enableCaseEventsLetterLink = enableCaseEventsLetterLink;
    }

    /**
     * @return Returns the enableCaseStepsLetterLink.
     */
    public boolean isEnableCaseStepsLetterLink()
    {
        return enableCaseStepsLetterLink;
    }

    /**
     * @param enableCaseStepsLetterLink
     *            The enableCaseStepsLetterLink to set.
     */
    public void setEnableCaseStepsLetterLink(boolean enableCaseStepsLetterLink)
    {
        this.enableCaseStepsLetterLink = enableCaseStepsLetterLink;
    }

    /**
     * @return Returns the cfValueSKMap.
     */
    public Map getCfValueSKMap()
    {
        return cfValueSKMap;
    }

    /**
     * @param cfValueSKMap
     *            The cfValueSKMap to set.
     */
    public void setCfValueSKMap(Map cfValueSKMap)
    {
        this.cfValueSKMap = cfValueSKMap;
    }

    /**
     * @return Returns the physicianOverseeingList.
     */
    public List getPhysicianOverseeingList()
    {
        return physicianOverseeingList;
    }

    /**
     * @param physicianOverseeingList
     *            The physicianOverseeingList to set.
     */
    public void setPhysicianOverseeingList(List physicianOverseeingList)
    {
        this.physicianOverseeingList = physicianOverseeingList;
    }

    /**
     * @return Returns the alertAuditHistoryRender.
     */
    public boolean isAlertAuditHistoryRender()
    {
        return alertAuditHistoryRender;
    }

    /**
     * @param alertAuditHistoryRender
     *            The alertAuditHistoryRender to set.
     */
    public void setAlertAuditHistoryRender(boolean alertAuditHistoryRender)
    {
        this.alertAuditHistoryRender = alertAuditHistoryRender;
    }

    /**
     * @return Returns the alertAuditHistoryList.
     */
    public List getAlertAuditHistoryList()
    {
        return alertAuditHistoryList;
    }

    /**
     * @param alertAuditHistoryList
     *            The alertAuditHistoryList to set.
     */
    public void setAlertAuditHistoryList(List alertAuditHistoryList)
    {
        this.alertAuditHistoryList = alertAuditHistoryList;
    }
    /**
     * @return Returns the caseTypeEventDOList.
     */
    public List getCaseTypeEventDOList()
    {
        return caseTypeEventDOList;
    }

    /**
     * @param caseTypeEventDOList
     *            The caseTypeEventDOList to set.
     */
    public void setCaseTypeEventDOList(List caseTypeEventDOList)
    {
        this.caseTypeEventDOList = caseTypeEventDOList;
    }
    /**
     * @return Returns the caseTypeStepDOList.
     */
    public List getCaseTypeStepDOList()
    {
        return caseTypeStepDOList;
    }

    /**
     * @param caseTypeStepDOList
     *            The caseTypeStepDOList to set.
     */
    public void setCaseTypeStepDOList(List caseTypeStepDOList)
    {
        this.caseTypeStepDOList = caseTypeStepDOList;
    }
    /**
     * @return Returns the caseTypeShortDesc.
     */
    public List getCaseTypeShortDesc()
    {
        return caseTypeShortDesc;
    }

    /**
     * @param caseTypeShortDesc
     *            The caseTypeShortDesc to set.
     */
    public void setCaseTypeShortDesc(List caseTypeShortDesc)
    {
        this.caseTypeShortDesc = caseTypeShortDesc;
    }
    /**
     * @return Returns the alertAuditOpen.
     */
    public boolean isAlertAuditOpen()
    {
        return alertAuditOpen;
    }

    /**
     * @param alertAuditOpen
     *            The alertAuditOpen to set.
     */
    public void setAlertAuditOpen(boolean alertAuditOpen)
    {
        this.alertAuditOpen = alertAuditOpen;
    }
    /**
     * @return Returns the attachmentAuditOpen.
     */
    public boolean isAttachmentAuditOpen()
    {
        return attachmentAuditOpen;
    }

    /**
     * @param attachmentAuditOpen
     *            The attachmentAuditOpen to set.
     */
    public void setAttachmentAuditOpen(boolean attachmentAuditOpen)
    {
        this.attachmentAuditOpen = attachmentAuditOpen;
    }
    /**
     * @return Returns the routingAuditOpen.
     */
    public boolean isRoutingAuditOpen()
    {
        return routingAuditOpen;
    }

    /**
     * @param routingAuditOpen
     *            The routingAuditOpen to set.
     */
    public void setRoutingAuditOpen(boolean routingAuditOpen)
    {
        this.routingAuditOpen = routingAuditOpen;
    }

    /**
     * @return Returns the userID.
     */
	 // Begin - Modified for the Performance fix
    public String getLoggedInUserID()
    {
        return loggedInUserID;
    }

    /**
     * @param userID
     *            The userID to set.
     */
    public void setLoggedInUserID(String loggedInUserID)
    {
        this.loggedInUserID = loggedInUserID;
    }
	 // End - Modified for the Performance fix

    /**
     * @return Returns the disableScreenField.
     */
    public boolean isDisableScreenField()
    {
        return disableScreenField;
    }

    /**
     * @param disableScreenField
     *            The disableScreenField to set.
     */
    public void setDisableScreenField(boolean disableScreenField)
    {
        this.disableScreenField = disableScreenField;
    }
    /**
     * @return Returns the commonEntitySKForMyTask.
     */
    public Long getCommonEntitySKForMyTask()
    {
        return commonEntitySKForMyTask;
    }

    /**
     * @param commonEntitySKForMyTask
     *            The commonEntitySKForMyTask to set.
     */
    public void setCommonEntitySKForMyTask(Long commonEntitySKForMyTask)
    {
        this.commonEntitySKForMyTask = commonEntitySKForMyTask;
    }
    /**
     * @return Returns the showAttachmentLink.
     */
    public boolean isShowAttachmentLink()
    {
        return showAttachmentLink;
    }

    /**
     * @param showAttachmentLink
     *            The showAttachmentLink to set.
     */
    public void setShowAttachmentLink(boolean showAttachmentLink)
    {
        this.showAttachmentLink = showAttachmentLink;
    }
    /**
     * @return Returns the fromIPC.
     */
    public boolean isFromIPC()
    {
        return fromIPC;
    }

    /**
     * @param fromIPC
     *            The fromIPC to set.
     */
    public void setFromIPC(boolean fromIPC)
    {
        this.fromIPC = fromIPC;
    }
/**
 * @return Returns the showDeleteMessage.
 */
public boolean isShowDeleteMessage() {
	return showDeleteMessage;
}
/**
 * @param showDeleteMessage The showDeleteMessage to set.
 */
public void setShowDeleteMessage(boolean showDeleteMessage) {
	this.showDeleteMessage = showDeleteMessage;
}

/** Added by ics GAP- 6493 */
/** holds the edmsWorkunitLevel codes */
private List edmsWorkunitLevelList = Collections.EMPTY_LIST;

/** holds the edmsDocType codes */
private List edmsDocTypeList = Collections.EMPTY_LIST;

/**
 * @return Returns the edmsDocTypeList.
 */
public List getEdmsDocTypeList() {
	return edmsDocTypeList;
}

/**
 * @param edmsDocTypeList
 *            The edmsDocTypeList to set.
 */
public void setEdmsDocTypeList(List edmsDocTypeList) {
	
	this.edmsDocTypeList = edmsDocTypeList;
}

/**
 * @return Returns the edmsWorkunitLevelList.
 */
public List getEdmsWorkunitLevelList() {
	
	return edmsWorkunitLevelList;
}

/**
 * @param edmsWorkunitLevelList
 *            The edmsWorkunitLevelList to set.
 */
public void setEdmsWorkunitLevelList(List edmsWorkunitLevelList) {
	this.edmsWorkunitLevelList = edmsWorkunitLevelList;
	
}

/** END BY ICS GAP-6493 */



    /**
     * @return Returns the selectedUnUsualList.
     */
    public List getSelectedUnUsualList()
    {
        return selectedUnUsualList;
    }

    /**
     * @param selectedUnUsualList
     *            The selectedUnUsualList to set.
     */
    public void setSelectedUnUsualList(List selectedUnUsualList)
    {
        this.selectedUnUsualList = selectedUnUsualList;
    }

    /**
     * @return Returns the autoPopulatedDept.
     */
    public boolean isAutoPopulatedDept()
    {
        return autoPopulatedDept;
    }

    /**
     * @param autoPopulatedDept
     *            The autoPopulatedDept to set.
     */
    public void setAutoPopulatedDept(boolean autoPopulatedDept)
    {
        this.autoPopulatedDept = autoPopulatedDept;
    }

    /**
     * @return Returns the caseNotesSetTempList.
     */
    public List getCaseNotesSetTempList()
    {
        return caseNotesSetTempList;
    }

    /**
     * @param caseNotesSetTempList
     *            The caseNotesSetTempList to set.
     */
    public void setCaseNotesSetTempList(List caseNotesSetTempList)
    {
        this.caseNotesSetTempList = caseNotesSetTempList;
    }

    /**
     * @return Returns the caseTypeSKAndShortDesc.
     */
    /*public Map getCaseTypeSKAndShortDesc()
    {
        return caseTypeSKAndShortDesc;
    }

    *//**
     * @param caseTypeSKAndShortDesc
     *            The caseTypeSKAndShortDesc to set.
     *//*
    public void setCaseTypeSKAndShortDesc(Map caseTypeSKAndShortDesc)
    {
        this.caseTypeSKAndShortDesc = caseTypeSKAndShortDesc;
    }*/
    /**
     * @return Returns the holidaysList.
     */
    public List getHolidaysList()
    {
        return holidaysList;
    }
    /**
     * @param holidaysList The holidaysList to set.
     */
    public void setHolidaysList(List holidaysList)
    {
        this.holidaysList = holidaysList;
    }
    /**
     * @return Returns the pictureDateList.
     */
    public List getPictureDateList()
    {
        return pictureDateList;
    }
    /**
     * @param pictureDateList The pictureDateList to set.
     */
    public void setPictureDateList(List pictureDateList)
    {
        this.pictureDateList = pictureDateList;
    }
    
    /**
     * @return Returns the picIDMap.
     */
    public Map getPicIDMap()
    {
        return picIDMap;
    }
    /**
     * @param picIDMap The picIDMap to set.
     */
    public void setPicIDMap(Map picIDMap)
    {
        this.picIDMap = picIDMap;
    }
    /**
     * @return Returns the picValMap.
     */
    public Map getPicValMap()
    {
        return picValMap;
    }
    /**
     * @param picValMap The picValMap to set.
     */
    public void setPicValMap(Map picValMap)
    {
        this.picValMap = picValMap;
    }
	/**
	 * @return Returns the saveRoutingFlag.
	 */
	public boolean isSaveRoutingFlag() {
		return SaveRoutingFlag;
	}
	/**
	 * @param saveRoutingFlag The saveRoutingFlag to set.
	 */
	public void setSaveRoutingFlag(boolean saveRoutingFlag) {
		this.SaveRoutingFlag = saveRoutingFlag;
	}
	/**Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start */
	/**
	 * @return Returns the fileBigSavedFlag.	
	 */
	public boolean isFileBigSavedFlag() {
		return fileBigSavedFlag;
	}
	/**
	 * @param fileBigSavedFlag The fileBigSavedFlag to set.
	 */
	public void setFileBigSavedFlag(boolean fileBigSavedFlag) {
		this.fileBigSavedFlag = fileBigSavedFlag;
	}
	/**
	 * @return Returns the saveCaseStepFlag.
	 */
	public boolean isSaveCaseStepFlag() {
		return SaveCaseStepFlag;
	}
	/**
	 * @param saveCaseStepFlag The saveCaseStepFlag to set.
	 */
	public void setSaveCaseStepFlag(boolean saveCaseStepFlag) {
		SaveCaseStepFlag = saveCaseStepFlag;
	}
	/**
	 * @return Returns the renderedCaseStepsflag.
	 */
	public boolean isRenderedCaseStepsflag() {
		return renderedCaseStepsflag;
	}
	/**
	 * @param renderedCaseStepsflag The renderedCaseStepsflag to set.
	 */
	public void setRenderedCaseStepsflag(boolean renderedCaseStepsflag) {
		this.renderedCaseStepsflag = renderedCaseStepsflag;
	}
	/**
	 *   Infinite added for UC-PGM-CRM-018.10 Defect 3033
	 * @return Returns the renderInputDateOnRadioYes.
	 */
	public boolean isRenderInputDateOnRadioYes() {
		return renderInputDateOnRadioYes;
	}
	/**
	 * @param renderInputDateOnRadioYes The renderInputDateOnRadioYes to set.
	 */
	public void setRenderInputDateOnRadioYes(boolean renderInputDateOnRadioYes) {
		this.renderInputDateOnRadioYes = renderInputDateOnRadioYes;
	}

	boolean hideLink = true;
	
   
	/**
	 * @return Returns the hideLink.
	 */
	public boolean isHideLink() {
		return hideLink;
	}
	/**
	 * @param hideLink The hideLink to set.
	 */
	public void setHideLink(boolean hideLink) {
		this.hideLink = hideLink;
	}
	boolean disableButton = true;
	/**
	 * @return Returns the disableButton.
	 */
	public boolean isDisableButton() {
		return disableButton;
	}
	/**
	 * @param disableButton The disableButton to set.
	 */
	public void setDisableButton(boolean disableButton) {
		this.disableButton = disableButton;
	}
	/**
	 * @return Returns the showSucessMessage.
	 */
	public boolean isShowSucessMessage() {
		return showSucessMessage;
	}
	/**
	 * @param showSucessMessage The showSucessMessage to set.
	 */
	public void setShowSucessMessage(boolean showSucessMessage) {
		this.showSucessMessage = showSucessMessage;
	}
	/**
	 * @return Returns the edmsWrkUnitLevelList.
	 */
	public List getEdmsWrkUnitLevelList() {
		return edmsWrkUnitLevelList;
	}
	/**
	 * @param edmsWrkUnitLevelList The edmsWrkUnitLevelList to set.
	 */
	public void setEdmsWrkUnitLevelList(List edmsWrkUnitLevelList) {
		this.edmsWrkUnitLevelList = edmsWrkUnitLevelList;
	}
	/**
	 * @return Returns the noData.
	 */
	public boolean isNoData() {
		return noData;
	}
	/**
	 * @param noData The noData to set.
	 */
	public void setNoData(boolean noData) {
		this.noData = noData;
	}
	

    
    /** it shows/hide case type MemberAppeal screens */
	private boolean showMemAppTypeScreen = false;
	
	/**
	 * Commented for CR ESPRD00865082 as showMemAppTypeScreen variable is reused
	 * for all appeals case type to display continue button
	 */
	/*private boolean showProviderAppTypeScreen = false;
	*//**
	 * @return Returns the showProviderAppTypeScreen.
	 *//*
	public boolean isShowProviderAppTypeScreen() {
		return showProviderAppTypeScreen;
	}
	*//**
	 * @param showProviderAppTypeScreen The showProviderAppTypeScreen to set.
	 *//*
	public void setShowProviderAppTypeScreen(boolean showProviderAppTypeScreen) {
		this.showProviderAppTypeScreen = showProviderAppTypeScreen;
	}*/

	
/**
 * @return Returns the showMemAppTypeScreen.
 */
public boolean isShowMemAppTypeScreen() {
	return showMemAppTypeScreen;
}
/**
 * @param showMemAppTypeScreen The showMemAppTypeScreen to set.
 */
public void setShowMemAppTypeScreen(boolean showMemAppTypeScreen) {
	this.showMemAppTypeScreen = showMemAppTypeScreen;
}

private boolean showCommonTypeScreen = false;

/**
 * @return Returns the showCommonTypeScreen.
 */
public boolean isShowCommonTypeScreen() {
	return showCommonTypeScreen;
}
/**
 * @param showCommonTypeScreen The showCommonTypeScreen to set.
 */
public void setShowCommonTypeScreen(boolean showCommonTypeScreen) {
	this.showCommonTypeScreen = showCommonTypeScreen;
}
/**
 * @param disableFields The disable fileds  to set.
 */
private boolean disableFields = true;

//end
/**
 * @return Returns the disableFields.
 */
public boolean isDisableFields() {
	return disableFields;
}
/**
 * @param disableFields The disableFields to set.
 */
public void setDisableFields(boolean disableFields) {
	this.disableFields = disableFields;
}
/**
 * @param disableFields The disable fileds  to set for Expected Completion Date.
 */
private boolean disableExpectedCompletionDateFields = true;
/**
 * @return Returns the disableExpectedCompletionDateFields.
 */
public boolean isDisableExpectedCompletionDateFields() {
	return disableExpectedCompletionDateFields;
}
/**
 * @param disableExpectedCompletionDateFields The disableExpectedCompletionDateFields to set.
 */
public void setDisableExpectedCompletionDateFields(
		boolean disableExpectedCompletionDateFields) {
	this.disableExpectedCompletionDateFields = disableExpectedCompletionDateFields;
}

	
	
//	Added for defect ID ESPRD00299552
	private boolean disableCaseEventFields =true;
	
	/**
	 * @return Returns the disableCaseEventFields.
	 */
	public boolean isDisableCaseEventFields() {
		return disableCaseEventFields;
	}
	/**
	 * @param disableCaseEventFields The disableCaseEventFields to set.
	 */
	public void setDisableCaseEventFields(boolean disableCaseEventFields) {
		this.disableCaseEventFields = disableCaseEventFields;
	}
//	EOF defect ID ESPRD00299552

//	added for defect ID : ESPRD00327996 
	List routingShowList = new ArrayList();

	/**
	 * @return Returns the routingShowList.
	 */
	public List getRoutingShowList() {
		return routingShowList;
	}

	/**
	 * @param routingShowList
	 *            The routingShowList to set.
	 */
	public void setRoutingShowList(List routingShowList) {
		this.routingShowList = routingShowList;
	}
	
    private boolean showBusinessUnitFields = false;
	
	/**
	 * @return Returns the showBusinessUnitFields.
	 */
	public boolean isShowBusinessUnitFields() {
		return showBusinessUnitFields;
	}
	/**
	 * @param showBusinessUnitFields The showBusinessUnitFields to set.
	 */
	public void setShowBusinessUnitFields(boolean showBusinessUnitFields) {
		this.showBusinessUnitFields = showBusinessUnitFields;
	}
	private List listOfAllDepartments = new ArrayList();
	
	/**
	 * @return Returns the listOfAllDepartments.
	 */
	public List getListOfAllDepartments() {
		return listOfAllDepartments;
	}
	/**
	 * @param listOfAllDepartments The listOfAllDepartments to set.
	 */
	public void setListOfAllDepartments(List listOfAllDepartments) {
		this.listOfAllDepartments = listOfAllDepartments;
	}
//	EOF defect ID ESPRD00327996 
	/**
	 * @return Returns the showCaseType.
	 */
	public boolean isShowCaseType() {
		return showCaseType;
	}
	/**
	 * @param showCaseType The showCaseType to set.
	 */
	public void setShowCaseType(boolean showCaseType) {
		this.showCaseType = showCaseType;
	}
	/**
	 * @return Returns the showUserDept.
	 */
	public boolean isShowUserDept() {
		return showUserDept;
	}
	/**
	 * @param showUserDept The showUserDept to set.
	 */
	public void setShowUserDept(boolean showUserDept) {
		this.showUserDept = showUserDept;
	}
	/**
	 * @return Returns the showWorkUnit.
	 */
	public boolean isShowWorkUnit() {
		return showWorkUnit;
	}
	/**
	 * @param showWorkUnit The showWorkUnit to set.
	 */
	public void setShowWorkUnit(boolean showWorkUnit) {
		this.showWorkUnit = showWorkUnit;
	}
	/**
	 * @return Returns the crAssociatedWithCaseRecordsList.
	 */
	/*public List getCrAssociatedWithCaseRecordsList() {
		return crAssociatedWithCaseRecordsList;
	}
	*//**
	 * @param crAssociatedWithCaseRecordsList The crAssociatedWithCaseRecordsList to set.
	 *//*
	public void setCrAssociatedWithCaseRecordsList(
			List crAssociatedWithCaseRecordsList) {
		this.crAssociatedWithCaseRecordsList = crAssociatedWithCaseRecordsList;
	}*/
	/**
	 * @return Returns the existingCorrespondenceRecordsList.
	 */
	/*public List getExistingCorrespondenceRecordsList() {
		return existingCorrespondenceRecordsList;
	}
	*//**
	 * @param existingCorrespondenceRecordsList The existingCorrespondenceRecordsList to set.
	 *//*
	public void setExistingCorrespondenceRecordsList(
			List existingCorrespondenceRecordsList) {
		this.existingCorrespondenceRecordsList = existingCorrespondenceRecordsList;
	}*/
	/**
	 * @return Returns the showCorrSearchScreen.
	 */
	public boolean isShowCorrSearchScreen() {
		return showCorrSearchScreen;
	}
	/**
	 * @param showCorrSearchScreen The showCorrSearchScreen to set.
	 */
	public void setShowCorrSearchScreen(boolean showCorrSearchScreen) {
		this.showCorrSearchScreen = showCorrSearchScreen;
	}
	/**
	 * @return Returns the showExistingCorrespondenceDataTable.
	 */
	public boolean isShowExistingCorrespondenceDataTable() {
		return showExistingCorrespondenceDataTable;
	}
	/**
	 * @param showExistingCorrespondenceDataTable The showExistingCorrespondenceDataTable to set.
	 */
	public void setShowExistingCorrespondenceDataTable(
			boolean showExistingCorrespondenceDataTable) {
		this.showExistingCorrespondenceDataTable = showExistingCorrespondenceDataTable;
	}
	/**
	 * @return Returns the categoryList.
	 */
	public List getCategoryList() {
		return categoryList;
	}
	/**
	 * @param categoryList The categoryList to set.
	 */
	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}
	/**
	 * @return Returns the catListToBeSentInSearchCritVO.
	 */
	public List getCatListToBeSentInSearchCritVO() {
		return catListToBeSentInSearchCritVO;
	}
	/**
	 * @param catListToBeSentInSearchCritVO The catListToBeSentInSearchCritVO to set.
	 */
	public void setCatListToBeSentInSearchCritVO(
			List catListToBeSentInSearchCritVO) {
		this.catListToBeSentInSearchCritVO = catListToBeSentInSearchCritVO;
	}
	/**
	 * @return Returns the entityIDTypeList.
	 */
	public List getEntityIDTypeList() {
		return entityIDTypeList;
	}
	/**
	 * @param entityIDTypeList The entityIDTypeList to set.
	 */
	public void setEntityIDTypeList(List entityIDTypeList) {
		this.entityIDTypeList = entityIDTypeList;
	}
	/**
	 * @return Returns the entityTypeVVList.
	 */
	public List getEntityTypeVVList() {
		return entityTypeVVList;
	}
	/**
	 * @param entityTypeVVList The entityTypeVVList to set.
	 */
	public void setEntityTypeVVList(List entityTypeVVList) {
		this.entityTypeVVList = entityTypeVVList;
	}
	/**
	 * @return Returns the inqEntityIDTypeList.
	 */
	public List getInqEntityIDTypeList() {
		return InqEntityIDTypeList;
	}
	/**
	 * @param inqEntityIDTypeList The inqEntityIDTypeList to set.
	 */
	public void setInqEntityIDTypeList(List inqEntityIDTypeList) {
		InqEntityIDTypeList = inqEntityIDTypeList;
	}
	/**
	 * @return Returns the priorityVVList.
	 */
	public List getPriorityVVList() {
		return priorityVVList;
	}
	/**
	 * @param priorityVVList The priorityVVList to set.
	 */
	public void setPriorityVVList(List priorityVVList) {
		this.priorityVVList = priorityVVList;
	}
	/**
	 * @return Returns the resolnVVList.
	 */
	public List getResolnVVList() {
		return resolnVVList;
	}
	/**
	 * @param resolnVVList The resolnVVList to set.
	 */
	public void setResolnVVList(List resolnVVList) {
		this.resolnVVList = resolnVVList;
	}
	/**
	 * @return Returns the statusVVList.
	 */
	public List getStatusVVList() {
		return statusVVList;
	}
	/**
	 * @param statusVVList The statusVVList to set.
	 */
	public void setStatusVVList(List statusVVList) {
		this.statusVVList = statusVVList;
	}
	/**
	 * @return Returns the subjectVVList.
	 */
	public List getSubjectVVList() {
		return subjectVVList;
	}
	/**
	 * @param subjectVVList The subjectVVList to set.
	 */
	public void setSubjectVVList(List subjectVVList) {
		this.subjectVVList = subjectVVList;
	}
	
	/**
	 * @return Returns the correspondenceSearchCriteriaVO.
	 */
	public CorrespondenceSearchCriteriaVO getCorrespondenceSearchCriteriaVO() {
		return correspondenceSearchCriteriaVO;
	}
	/**
	 * @param correspondenceSearchCriteriaVO The correspondenceSearchCriteriaVO to set.
	 */
	public void setCorrespondenceSearchCriteriaVO(
			CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO) {
		this.correspondenceSearchCriteriaVO = correspondenceSearchCriteriaVO;
	}
	
	
	public boolean isAdvOptionsFl(){
		return this.advOptionsFl;
	}
	
	
	 
	 /**
	  * @return Returns the advancedOptionsHidden.
	  */
	public String getAdvancedOptionsHidden() {
		return advancedOptionsHidden;
	}

	/**
	 * @param advancedOptionsHidden
	 *            The advancedOptionsHidden to set.
	 */
	public void setAdvancedOptionsHidden(String advancedOptionsHidden) {
		this.advancedOptionsHidden = advancedOptionsHidden;
	}
	/**
	 * @return Returns the provTypeVVList.
	 */
	public List getProvTypeVVList() {
		return provTypeVVList;
	}
	/**
	 * @param provTypeVVList The provTypeVVList to set.
	 */
	public void setProvTypeVVList(List provTypeVVList) {
		this.provTypeVVList = provTypeVVList;
	}
    
	/**
	 * @return Returns the createdByList.
	 */
	public List getCreatedByList() {
		return createdByList;
	}
	/**
	 * @param createdByList The createdByList to set.
	 */
	public void setCreatedByList(List createdByList) {
		this.createdByList = createdByList;
	}
    
    /**
	 * @return Returns the lobVVList.
	 */
	public List getLobVVList() {
		return lobVVList;
	}
	/**
	 * @param lobVVList The lobVVList to set.
	 */
	public void setLobVVList(List lobVVList) {
		this.lobVVList = lobVVList;
	}
	/**
	 * @return Returns the sourceVVList.
	 */
	public List getSourceVVList() {
		return sourceVVList;
	}
	/**
	 * @param sourceVVList The sourceVVList to set.
	 */
	public void setSourceVVList(List sourceVVList) {
		this.sourceVVList = sourceVVList;
	}
	
    /**
	 * @return Returns the assignedToList.
	 */
	public List getAssignedToList() {
		return assignedToList;
	}
	/**
	 * @param assignedToList The assignedToList to set.
	 */
	public void setAssignedToList(List assignedToList) {
		this.assignedToList = assignedToList;
	}
	/**
	 * @return Returns the listOfRepUnits.
	 */
	public List getListOfRepUnits() {
		return listOfRepUnits;
	}
	/**
	 * @param listOfRepUnits The listOfRepUnits to set.
	 */
	public void setListOfRepUnits(List listOfRepUnits) {
		this.listOfRepUnits = listOfRepUnits;
	}
	
	/**
	 * @return Returns the listOfRefBusUnits.
	 */
	public List getListOfRefBusUnits() {
		return listOfRefBusUnits;
	}
	/**
	 * @param listOfRefBusUnits The listOfRefBusUnits to set.
	 */
	public void setListOfRefBusUnits(List listOfRefBusUnits) {
		this.listOfRefBusUnits = listOfRefBusUnits;
	}
	
	/**
	 * @return Returns the listOfRefDeptUnits.
	 */
	public List getListOfRefDeptUnits() {
		return listOfRefDeptUnits;
	}
	/**
	 * @param listOfRefDeptUnits The listOfRefDeptUnits to set.
	 */
	public void setListOfRefDeptUnits(List listOfRefDeptUnits) {
		this.listOfRefDeptUnits = listOfRefDeptUnits;
	}
	    
	/**
	 * @return Returns the searchAssocCR.
	 */
	public boolean isSearchAssocCR() {
		return searchAssocCR;
	}
	/**
	 * @param searchAssocCR The searchAssocCR to set.
	 */
	public void setSearchAssocCR(boolean searchAssocCR) {
		this.searchAssocCR = searchAssocCR;
	}

	/**
	 * @return Returns the searchCorrespondenceListSize.
	 */
	public int getSearchCorrespondenceListSize() {
		return searchCorrespondenceListSize;
	}
	/**
	 * @param searchCorrespondenceListSize The searchCorrespondenceListSize to set.
	 */
	public void setSearchCorrespondenceListSize(int searchCorrespondenceListSize) {
		this.searchCorrespondenceListSize = searchCorrespondenceListSize;
	}
	/**
	 * @return Returns the showResultsDiv.
	 */
	public boolean isShowResultsDiv() {
		return showResultsDiv;
	}
	/**
	 * @param showResultsDiv The showResultsDiv to set.
	 */
	public void setShowResultsDiv(boolean showResultsDiv) {
		this.showResultsDiv = showResultsDiv;
	}

	/**
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		return searchResults;
	}
	/**
	 * @param searchResults The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
	}
    /** Ends  For the defect ID ESPRD00334100*/	

	//added for defect ID ESPRD00334051
	 /** To hold letNrespHidden. */
   private String letNrespHidden = "plus";
   /**
    * @return Returns the letNrespHidden.
    */
   public String getLetNrespHidden() {
   	return letNrespHidden;
   }
   /**
    * @param letNrespHidden The letNrespHidden to set.
    */
   public void setLetNrespHidden(String letNrespHidden) {
   	this.letNrespHidden = letNrespHidden;
   }
	//EOF defect ID ESPRD00334051
   //Added for defect ESPRD00299822
   private String lettersAndRespStyleVal="none";


   /**
    * @return Returns the lettersAndRespStyleVal.
    */
   public String getLettersAndRespStyleVal() {
   	return lettersAndRespStyleVal;
   }
   /**
    * @param lettersAndRespStyleVal The lettersAndRespStyleVal to set.
    */
   public void setLettersAndRespStyleVal(String lettersAndRespStyleVal) {
   	this.lettersAndRespStyleVal = lettersAndRespStyleVal;
   }
//EOF defect ESPRD00299822
	/**
	 * @return Returns the caseTypeSKAndBusinessUnit.
	 */
	/*public Map getCaseTypeSKAndBusinessUnit() {
		return caseTypeSKAndBusinessUnit;
	}
	*//**
	 * @param caseTypeSKAndBusinessUnit The caseTypeSKAndBusinessUnit to set.
	 *//*
	public void setCaseTypeSKAndBusinessUnit(Map caseTypeSKAndBusinessUnit) {
		this.caseTypeSKAndBusinessUnit = caseTypeSKAndBusinessUnit;
	}*/
	/**
	 * @return Returns the htmlSearchResults.
	 */
	public int getHtmlSearchResults() {
		return htmlSearchResults;
	}
	/**
	 * @param htmlSearchResults The htmlSearchResults to set.
	 */
	public void setHtmlSearchResults(int htmlSearchResults) {
		this.htmlSearchResults = htmlSearchResults;
	}
	/**
	 * @return Returns the searchDeSelectAll.
	 */
	public boolean isSearchDeSelectAll() {
		return searchDeSelectAll;
	}
	/**
	 * @param searchDeSelectAll The searchDeSelectAll to set.
	 */
	public void setSearchDeSelectAll(boolean searchDeSelectAll) {
		this.searchDeSelectAll = searchDeSelectAll;
	}
	/**
	 * @return Returns the searchSelectAll.
	 */
	public boolean isSearchSelectAll() {
		return searchSelectAll;
	}
	/**
	 * @param searchSelectAll The searchSelectAll to set.
	 */
	public void setSearchSelectAll(boolean searchSelectAll) {
		this.searchSelectAll = searchSelectAll;
	}
	/**
	 * @return Returns the existCrsDeSelectAll.
	 */
	public boolean isExistCrsDeSelectAll() {
		return existCrsDeSelectAll;
	}
	/**
	 * @param existCrsDeSelectAll The existCrsDeSelectAll to set.
	 */
	public void setExistCrsDeSelectAll(boolean existCrsDeSelectAll) {
		this.existCrsDeSelectAll = existCrsDeSelectAll;
	}
	/**
	 * @return Returns the existCrsSelectAll.
	 */
	public boolean isExistCrsSelectAll() {
		return existCrsSelectAll;
	}
	/**
	 * @param existCrsSelectAll The existCrsSelectAll to set.
	 */
	public void setExistCrsSelectAll(boolean existCrsSelectAll) {
		this.existCrsSelectAll = existCrsSelectAll;
	}

	private boolean caseEventsCreateLetterStatus = false;
	private boolean caseStepsCreateLetterStatus = false;

	/**
	 * @return Returns the caseEventsCreateLetterStatus.
	 */
	public boolean isCaseEventsCreateLetterStatus() {
		return caseEventsCreateLetterStatus;
	}
	/**
	 * @param caseEventsCreateLetterStatus The caseEventsCreateLetterStatus to set.
	 */
	public void setCaseEventsCreateLetterStatus(boolean caseEventsCreateLetterStatus) {
		this.caseEventsCreateLetterStatus = caseEventsCreateLetterStatus;
	}
	/**
	 * @return Returns the caseStepsCreateLetterStatus.
	 */
	public boolean isCaseStepsCreateLetterStatus() {
		return caseStepsCreateLetterStatus;
	}
	/**
	 * @param caseStepsCreateLetterStatus The caseStepsCreateLetterStatus to set.
	 */
	public void setCaseStepsCreateLetterStatus(
			boolean caseStepsCreateLetterStatus) {
		this.caseStepsCreateLetterStatus = caseStepsCreateLetterStatus;
	}
	private boolean createLetterLinkFlag =false;
	
	/**
	 * @return Returns the createLetterLinkFlag.
	 */
	public boolean isCreateLetterLinkFlag() {
		return createLetterLinkFlag;
	}
	/**
	 * @param createLetterLinkFlag The createLetterLinkFlag to set.
	 */
	public void setCreateLetterLinkFlag(boolean createLetterLinkFlag) {
		this.createLetterLinkFlag = createLetterLinkFlag;
	}
//	EOF defects ESPRD00340194,ESPRD00307362
	
	
	/**
	 * @return Returns the statusClosedAppealUpheld.
	 */
	public boolean isStatusClosedAppealUpheld() {
		return statusClosedAppealUpheld;
	}
	/**
	 * @param statusClosedAppealUpheld The statusClosedAppealUpheld to set.
	 */
	public void setStatusClosedAppealUpheld(boolean statusClosedAppealUpheld) {
		this.statusClosedAppealUpheld = statusClosedAppealUpheld;
	}
	/**
	 * @return Returns the caseTypeMemList.
	 */
	/*public List getCaseTypeMemList() {
		return caseTypeMemList;
	}
	*//**
	 * @param caseTypeMemList The caseTypeMemList to set.
	 *//*
	public void setCaseTypeMemList(List caseTypeMemList) {
		this.caseTypeMemList = caseTypeMemList;
	}*/
	/**
	 * @return Returns the caseTypeMemSKAndShortDes.
	 */
	/*public Map getCaseTypeMemSKAndShortDes() {
		return caseTypeMemSKAndShortDes;
	}
	*//**
	 * @param caseTypeMemSKAndShortDes The caseTypeMemSKAndShortDes to set.
	 *//*
	public void setCaseTypeMemSKAndShortDes(Map caseTypeMemSKAndShortDes) {
		this.caseTypeMemSKAndShortDes = caseTypeMemSKAndShortDes;
	}*/
	/**
	 * @return Returns the menuCode.
	 */
	public String getMenuCode() {
		return menuCode;
	}
	/**
	 * @param menuCode The menuCode to set.
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
//	added for ESPRD00327996
	/** Holds Business Unit list for routing*/
	private List businessUnitList;
	
	/** Holds Work Unit list for routing*/
	private List workUnitList;
	
	
	/**
	 * @return Returns the businessUnitList.
	 */
	public List getBusinessUnitList() {
		return businessUnitList;
	}
	/**
	 * @param businessUnitList The businessUnitList to set.
	 */
	public void setBusinessUnitList(List businessUnitList) {
		this.businessUnitList = businessUnitList;
	}
	/**
	 * @return Returns the workUnitList.
	 */
	public List getWorkUnitList() {
		return workUnitList;
	}
	/**
	 * @param workUnitList The workUnitList to set.
	 */
	public void setWorkUnitList(List workUnitList) {
		this.workUnitList = workUnitList;
	}
	//EOF ESPRD00327996
	/**
	 * @return Returns the assocCaseAuditHidden.
	 */
	public String getAssocCaseAuditHidden() {
		return assocCaseAuditHidden;
	}
	/**
	 * @param assocCaseAuditHidden The assocCaseAuditHidden to set.
	 */
	public void setAssocCaseAuditHidden(String assocCaseAuditHidden) {
		this.assocCaseAuditHidden = assocCaseAuditHidden;
	}
	/** Added For The Defect ID ESPRD00351598 */
	
	/** This will holds the BusineesUnit Map */
    private Map busineesUnitMap = new HashMap();
    
    /** This will holds the WorkUnit Map */
    private Map workUnitMap = new HashMap();
    
    /** it shows/hide the showAssignedTo */
    private boolean showAssignedTo = false;
    
    /** it shows/hide the showUserDeptName */
    private boolean showUserDeptName = false;
    
    /** This will holds the userDeptName */
    private String userDeptName ;
    
    //Commented for Heap dump fix defect ESPRD00935080
    /** Holds User Id and User Name */
    //private Map userNameMap = new HashMap();
    
    /** This will holds the WorkUnitSK Map */
    private Map workUnitSKMap = new HashMap();
    
	/**
	 * @return Returns the busineesUnitMap.
	 */
	public Map getBusineesUnitMap() {
		return busineesUnitMap;
	}
	/**
	 * @param busineesUnitMap The busineesUnitMap to set.
	 */
	public void setBusineesUnitMap(Map busineesUnitMap) {
		this.busineesUnitMap = busineesUnitMap;
	}
	/**
	 * @return Returns the showAssignedTo.
	 */
	public boolean isShowAssignedTo() {
		return showAssignedTo;
	}
	/**
	 * @param showAssignedTo The showAssignedTo to set.
	 */
	public void setShowAssignedTo(boolean showAssignedTo) {
		this.showAssignedTo = showAssignedTo;
	}
	/**
	 * @return Returns the workUnitMap.
	 */
	public Map getWorkUnitMap() {
		return workUnitMap;
	}
	/**
	 * @param workUnitMap The workUnitMap to set.
	 */
	public void setWorkUnitMap(Map workUnitMap) {
		this.workUnitMap = workUnitMap;
	}
	
	/**
	 * @return Returns the showUserDeptName.
	 */
	public boolean isShowUserDeptName() {
		return showUserDeptName;
	}
	/**
	 * @param showUserDeptName The showUserDeptName to set.
	 */
	public void setShowUserDeptName(boolean showUserDeptName) {
		this.showUserDeptName = showUserDeptName;
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
	 * @return Returns the disableLogAddCaseAssocCrspondence.
	 */
	public boolean isDisableLogAddCaseAssocCrspondence() {
		return disableLogAddCaseAssocCrspondence;
	}
	/**
	 * @param disableLogAddCaseAssocCrspondence The disableLogAddCaseAssocCrspondence to set.
	 */
	public void setDisableLogAddCaseAssocCrspondence(
			boolean disableLogAddCaseAssocCrspondence) {
		this.disableLogAddCaseAssocCrspondence = disableLogAddCaseAssocCrspondence;
	}
	/**
	 * @return Returns the disableLogCaseReset.
	 */
	public boolean isDisableLogCaseReset() {
		return disableLogCaseReset;
	}
	/**
	 * @param disableLogCaseReset The disableLogCaseReset to set.
	 */
	public void setDisableLogCaseReset(boolean disableLogCaseReset) {
		this.disableLogCaseReset = disableLogCaseReset;
	}
	/**
	 * @return Returns the disableLogCaseSave.
	 */
	public boolean isDisableLogCaseSave() {
		return disableLogCaseSave;
	}
	/**
	 * @param disableLogCaseSave The disableLogCaseSave to set.
	 */
	public void setDisableLogCaseSave(boolean disableLogCaseSave) {
		this.disableLogCaseSave = disableLogCaseSave;
	}
	/**
	 * @return Returns the disableDeleteCaseRecAssocWithCase.
	 */
	public boolean isDisableDeleteCaseRecAssocWithCase() {
		return disableDeleteCaseRecAssocWithCase;
	}
	/**
	 * @param disableDeleteCaseRecAssocWithCase The disableDeleteCaseRecAssocWithCase to set.
	 */
	public void setDisableDeleteCaseRecAssocWithCase(
			boolean disableDeleteCaseRecAssocWithCase) {
		this.disableDeleteCaseRecAssocWithCase = disableDeleteCaseRecAssocWithCase;
	}
	/**
	 * @return Returns the disableDeleteCorrespondingRecAssocWithCase.
	 */
	public boolean isDisableDeleteCorrespondingRecAssocWithCase() {
		return disableDeleteCorrespondingRecAssocWithCase;
	}
	/**
	 * @param disableDeleteCorrespondingRecAssocWithCase The disableDeleteCorrespondingRecAssocWithCase to set.
	 */
	public void setDisableDeleteCorrespondingRecAssocWithCase(
			boolean disableDeleteCorrespondingRecAssocWithCase) {
		this.disableDeleteCorrespondingRecAssocWithCase = disableDeleteCorrespondingRecAssocWithCase;
	}
	/**
	 * @return Returns the disableAddAttachment.
	 */
	public boolean isDisableAddAttachment() {
		return disableAddAttachment;
	}
	/**
	 * @param disableAddAttachment The disableAddAttachment to set.
	 */
	public void setDisableAddAttachment(boolean disableAddAttachment) {
		this.disableAddAttachment = disableAddAttachment;
	}
	/**
	 * @return Returns the disableAttachmentDetach.
	 */
	public boolean isDisableAttachmentDetach() {
		return disableAttachmentDetach;
	}
	/**
	 * @param disableAttachmentDetach The disableAttachmentDetach to set.
	 */
	public void setDisableAttachmentDetach(boolean disableAttachmentDetach) {
		this.disableAttachmentDetach = disableAttachmentDetach;
	}
	/**
	 * @return Returns the disableAttachmentReset.
	 */
	public boolean isDisableAttachmentReset() {
		return disableAttachmentReset;
	}
	/**
	 * @param disableAttachmentReset The disableAttachmentReset to set.
	 */
	public void setDisableAttachmentReset(boolean disableAttachmentReset) {
		this.disableAttachmentReset = disableAttachmentReset;
	}
	/**
	 * @return Returns the disableAttachmentSave.
	 */
	public boolean isDisableAttachmentSave() {
		return disableAttachmentSave;
	}
	/**
	 * @param disableAttachmentSave The disableAttachmentSave to set.
	 */
	public void setDisableAttachmentSave(boolean disableAttachmentSave) {
		this.disableAttachmentSave = disableAttachmentSave;
	}
	/**
	 * @return Returns the disableAttachmentDelete.
	 */
	public boolean isDisableAttachmentDelete() {
		return disableAttachmentDelete;
	}
	/**
	 * @param disableAttachmentDelete The disableAttachmentDelete to set.
	 */
	public void setDisableAttachmentDelete(boolean disableAttachmentDelete) {
		this.disableAttachmentDelete = disableAttachmentDelete;
	}
	/**
	 * @return Returns the disableAddCaseEvents.
	 */
	public boolean isDisableAddCaseEvents() {
		return disableAddCaseEvents;
	}
	/**
	 * @param disableAddCaseEvents The disableAddCaseEvents to set.
	 */
	public void setDisableAddCaseEvents(boolean disableAddCaseEvents) {
		this.disableAddCaseEvents = disableAddCaseEvents;
	}
	/**
	 * @return Returns the disableCaseEventDelete.
	 */
	public boolean isDisableCaseEventDelete() {
		return disableCaseEventDelete;
	}
	/**
	 * @param disableCaseEventDelete The disableCaseEventDelete to set.
	 */
	public void setDisableCaseEventDelete(boolean disableCaseEventDelete) {
		this.disableCaseEventDelete = disableCaseEventDelete;
	}
	/**
	 * @return Returns the disableCaseEventReset.
	 */
	public boolean isDisableCaseEventReset() {
		return disableCaseEventReset;
	}
	/**
	 * @param disableCaseEventReset The disableCaseEventReset to set.
	 */
	public void setDisableCaseEventReset(boolean disableCaseEventReset) {
		this.disableCaseEventReset = disableCaseEventReset;
	}
	/**
	 * @return Returns the disableCaseEventSave.
	 */
	public boolean isDisableCaseEventSave() {
		return disableCaseEventSave;
	}
	/**
	 * @param disableCaseEventSave The disableCaseEventSave to set.
	 */
	public void setDisableCaseEventSave(boolean disableCaseEventSave) {
		this.disableCaseEventSave = disableCaseEventSave;
	}
	/**
	 * @return Returns the disableAddAlert.
	 */
	public boolean isDisableAddAlert() {
		return disableAddAlert;
	}
	/**
	 * @param disableAddAlert The disableAddAlert to set.
	 */
	public void setDisableAddAlert(boolean disableAddAlert) {
		this.disableAddAlert = disableAddAlert;
	}
	/**
	 * @return Returns the disableAlertSave.
	 */
	public boolean isDisableAlertSave() {
		return disableAlertSave;
	}
	/**
	 * @param disableAlertSave The disableAlertSave to set.
	 */
	public void setDisableAlertSave(boolean disableAlertSave) {
		this.disableAlertSave = disableAlertSave;
	}
	/**
	 * @return Returns the disableAddCaseSteps.
	 */
	public boolean isDisableAddCaseSteps() {
		return disableAddCaseSteps;
	}
	/**
	 * @param disableAddCaseSteps The disableAddCaseSteps to set.
	 */
	public void setDisableAddCaseSteps(boolean disableAddCaseSteps) {
		this.disableAddCaseSteps = disableAddCaseSteps;
	}
	/**
	 * @return Returns the disableCaseStepDelete.
	 */
	public boolean isDisableCaseStepDelete() {
		return disableCaseStepDelete;
	}
	/**
	 * @param disableCaseStepDelete The disableCaseStepDelete to set.
	 */
	public void setDisableCaseStepDelete(boolean disableCaseStepDelete) {
		this.disableCaseStepDelete = disableCaseStepDelete;
	}
	/**
	 * @return Returns the disableCaseStepReset.
	 */
	public boolean isDisableCaseStepReset() {
		return disableCaseStepReset;
	}
	/**
	 * @param disableCaseStepReset The disableCaseStepReset to set.
	 */
	public void setDisableCaseStepReset(boolean disableCaseStepReset) {
		this.disableCaseStepReset = disableCaseStepReset;
	}
	/**
	 * @return Returns the disableCaseStepSave.
	 */
	public boolean isDisableCaseStepSave() {
		return disableCaseStepSave;
	}
	/**
	 * @param disableCaseStepSave The disableCaseStepSave to set.
	 */
	public void setDisableCaseStepSave(boolean disableCaseStepSave) {
		this.disableCaseStepSave = disableCaseStepSave;
		}
	/** Added For The Defect ID ESPRD00351598 Ends */
	//UC-PGM-CRM-018.3_ESPRD00328556_07NOV09
	/** To hold the currently selected index for CaseAssociatedWithThisCase data table*/
	private int viewCaseAssociatedWithThisCaseIndex = -1;
	/**
	 * @return Returns the viewCaseAssociatedWithThisCaseIndex.
	 */
	public int getViewCaseAssociatedWithThisCaseIndex() {
		return viewCaseAssociatedWithThisCaseIndex;
	}
	/**
	 * @param viewCaseAssociatedWithThisCaseIndex The viewCaseAssociatedWithThisCaseIndex to set.
	 */
	public void setViewCaseAssociatedWithThisCaseIndex(
			int viewCaseAssociatedWithThisCaseIndex) {
		this.viewCaseAssociatedWithThisCaseIndex = viewCaseAssociatedWithThisCaseIndex;
	}
	
	
	
//	ESPRD00328556
	private List allCaseStatusList = Collections.EMPTY_LIST;
	/**
	 * @return Returns the allCaseStatusList.
	 */
	public List getAllCaseStatusList() {
		return allCaseStatusList;
	}
	/**
	 * @param allCaseStatusList The allCaseStatusList to set.
	 */
	public void setAllCaseStatusList(List allCaseStatusList) {
		this.allCaseStatusList = allCaseStatusList;
	}
	//EOF ESPRD00328556
    
	/** This will holds the User list */
    private List stepUserList = new ArrayList();
	

	/**
	 * @return Returns the stepUserList.
	 */
	public List getStepUserList() {
		return stepUserList;
	}
	/**
	 * @param stepUserList The stepUserList to set.
	 */
	public void setStepUserList(List stepUserList) {
		this.stepUserList = stepUserList;
	}
	
	
    /**
     * @return Returns the userNameMap.
     */
    /*public Map getUserNameMap() {
        return userNameMap;
    }*/
    /**
     * @param userNameMap The userNameMap to set.
     */
    /*public void setUserNameMap(Map userNameMap) {
        this.userNameMap = userNameMap;
    }*/
    
    
    /**
     * @return Returns the stepAlertBasedOn.
     */
    public List getStepAlertBasedOn() {
        return stepAlertBasedOn;
    }
    /**
     * @param stepAlertBasedOn The stepAlertBasedOn to set.
     */
    public void setStepAlertBasedOn(List stepAlertBasedOn) {
        this.stepAlertBasedOn = stepAlertBasedOn;
    }
    
    
    private boolean disableCompletionBasedOn = true;
	
	/**
	 * @return Returns the disableCompletionBasedOn.
	 */
	public boolean isDisableCompletionBasedOn() {
		return disableCompletionBasedOn;
	}
	/**
	 * @param disableCompletionBasedOn The disableCompletionBasedOn to set.
	 */
	public void setDisableCompletionBasedOn(boolean disableCompletionBasedOn) {
		this.disableCompletionBasedOn = disableCompletionBasedOn;
	}
	
	/**
	 *  Holds the list of Provider/Hospital details.
	 */
	private List hospitalAndProvider;
	
    
	/**
	 * @return Returns the hospitalAndProvider.
	 */
	public List getHospitalAndProvider() {
		return hospitalAndProvider;
	}
	/**
	 * @param hospitalAndProvider The hospitalAndProvider to set.
	 */
	public void setHospitalAndProvider(List hospitalAndProvider) {
		this.hospitalAndProvider = hospitalAndProvider;
	}
	
	/**
     * This field is used to disable AddRouting.
     */
    private boolean disableAddRouting;
    
    /**
     * This field is used to disable AddRouting.
     */
    private boolean disableLogcaseContiniue;
    
    
	/**
	 * @return Returns the disableAddRouting.
	 */
	public boolean isDisableAddRouting() {
		return disableAddRouting;
	}
	/**
	 * @param disableAddRouting The disableAddRouting to set.
	 */
	public void setDisableAddRouting(boolean disableAddRouting) {
		this.disableAddRouting = disableAddRouting;
	}

	/**
	 * @return Returns the disableLogcaseContiniue.
	 */
	public boolean isDisableLogcaseContiniue() {
		return disableLogcaseContiniue;
	}
	/**
	 * @param disableLogcaseContiniue The disableLogcaseContiniue to set.
	 */
	public void setDisableLogcaseContiniue(boolean disableLogcaseContiniue) {
		this.disableLogcaseContiniue = disableLogcaseContiniue;
	}
	/** holds the provider/Hosp values */
    private List providerhos = new ArrayList();
	/**
	 * @return Returns the providerhos.
	 */
	public List getProviderhos() {
		return providerhos;
	}
	/**
	 * @param providerhos The providerhos to set.
	 */
	public void setProviderhos(List providerhos) {
		this.providerhos = providerhos;
	}
	
	
	
	 /** This will holds the notifyViaAlertFlag */
    private boolean notifyViaAlertFlag;
    
	/**
	 * @return Returns the notifyViaAlertFlag.
	 */
	public boolean isNotifyViaAlertFlag() {
		return notifyViaAlertFlag;
	}
	/**
	 * @param notifyViaAlertFlag The notifyViaAlertFlag to set.
	 */
	public void setNotifyViaAlertFlag(boolean notifyViaAlertFlag) {
		this.notifyViaAlertFlag = notifyViaAlertFlag;
	}
	
	
    /**
     * @return Returns the crAssociatedCaseRowIndex.
     */
    public int getCrAssociatedCaseRowIndex() {
        return crAssociatedCaseRowIndex;
    }
    /**
     * @param crAssociatedCaseRowIndex The crAssociatedCaseRowIndex to set.
     */
    public void setCrAssociatedCaseRowIndex(int crAssociatedCaseRowIndex) {
        this.crAssociatedCaseRowIndex = crAssociatedCaseRowIndex;
    }
    //added for ESPRD00358412
    /**
     * Holds the Additional Case Entities list currently added/Associated to the case before major save
     *  from InquringAboutCaseEntity portlet
     */
    List inqAbtEntityListBeforeSave = new ArrayList();
   
	/**
	 * @return Returns the inqAbtEntityListBeforeSave.
	 */
	public List getInqAbtEntityListBeforeSave() {
		return inqAbtEntityListBeforeSave;
	}
	/**
	 * @param inqAbtEntityListBeforeSave The inqAbtEntityListBeforeSave to set.
	 */
	public void setInqAbtEntityListBeforeSave(List inqAbtEntityListBeforeSave) {
		this.inqAbtEntityListBeforeSave = inqAbtEntityListBeforeSave;
	}
	private int selectedAdditionaCaseEntityIndex = -1;

	/**
	 * @return Returns the selectedAdditionaCaseEntityIndex.
	 */
	public int getSelectedAdditionaCaseEntityIndex() {
		return selectedAdditionaCaseEntityIndex;
	}
	/**
	 * @param selectedAdditionaCaseEntityIndex The selectedAdditionaCaseEntityIndex to set.
	 */
	public void setSelectedAdditionaCaseEntityIndex(
			int selectedAdditionaCaseEntityIndex) {
		this.selectedAdditionaCaseEntityIndex = selectedAdditionaCaseEntityIndex;
	}
    //EOF ESPRD00358412

	/** holds bolean flag to represents that all case events are closed(having disposition date) or not*/	//added for ESPRD00362595 
	private boolean allDDUCaseEventesAreClosed =false;
	/**
	 * returns true if all the case events are having disposition dates else return false
	 * @return Returns the allCaseEventesAreClosed.
	 */
	public boolean isAllDDUCaseEventesAreClosed() {
		return allDDUCaseEventesAreClosed;
	}
	/**
	 * @param allCaseEventesAreClosed The allCaseEventesAreClosed to set.
	 */
	public void setAllDDUCaseEventesAreClosed(boolean allDDUCaseEventesAreClosed) {
		this.allDDUCaseEventesAreClosed = allDDUCaseEventesAreClosed;
	}
	
	/** holds currently selected case status flag voided or not*/
	private boolean caseStepVoided = false; 
	
	/**
	 * @return Returns the caseStepVoided.
	 */
	public boolean isCaseStepVoided() {
		return caseStepVoided;
	}
	/**
	 * @param caseStepVoided The caseStepVoided to set.
	 */
	public void setCaseStepVoided(boolean caseStepVoided) {
		this.caseStepVoided = caseStepVoided;
	}//	EOF ESPRD00362595
	
	/** holds Physician Overseeing Flag for BRC CON0307.0001.01*/
    private boolean physicianOverseeingFlag= false;
	/**
	 * @return Returns the physicianOverseeingFlag.
	 */
	public boolean isPhysicianOverseeingFlag() {
		return physicianOverseeingFlag;
	}
	/**
	 * @param physicianOverseeingFlag The physicianOverseeingFlag to set.
	 */
	public void setPhysicianOverseeingFlag(boolean physicianOverseeingFlag) {
		this.physicianOverseeingFlag = physicianOverseeingFlag;
	}
	/**
	 * @return Returns the displayAddNoteLink.
	 */
	public boolean isDisplayAddNoteLink() {
		return displayAddNoteLink;
	}
	/**
	 * @param displayAddNoteLink The displayAddNoteLink to set.
	 */
	public void setDisplayAddNoteLink(boolean displayAddNoteLink) {
		this.displayAddNoteLink = displayAddNoteLink;
	}
	private String cursorFocusValue = MaintainContactManagementUIConstants.EMPTY_STRING;
	/**
	 * @return Returns the cursorFocusValue.
	 */
	public String getCursorFocusValue() {
		return cursorFocusValue;
	}
	/**
	 * @param cursorFocusValue The cursorFocusValue to set.
	 */
	public void setCursorFocusValue(String cursorFocusValue) {
		this.cursorFocusValue = cursorFocusValue;
	}
	/**
	 * @return Returns the workUnitSKMap.
	 */
	public Map getWorkUnitSKMap() {
		return workUnitSKMap;
	}
	/**
	 * @param workUnitSKMap The workUnitSKMap to set.
	 */
	public void setWorkUnitSKMap(Map workUnitSKMap) {
		this.workUnitSKMap = workUnitSKMap;
	}
	
	
	/**
	 * @return Returns the openDays.
	 */
	public boolean isOpenDays() {
		return openDays;
	}
	/**
	 * @param openDays The openDays to set.
	 */
	public void setOpenDays(boolean openDays) {
		this.openDays = openDays;

	}
	//UC-PGM-CRM-18_ESPRD00367808_30DEC09
	private boolean disableWorkUnit = false;
	/**
	 * @return Returns the disableWorkUnit.
	 */
	public boolean isDisableWorkUnit() {
		return disableWorkUnit;
	}
	/**
	 * @param disableWorkUnit The disableWorkUnit to set.
	 */
	public void setDisableWorkUnit(boolean disableWorkUnit) {
		this.disableWorkUnit = disableWorkUnit;
	}	//EOF UC-PGM-CRM-18_ESPRD00367808_30DEC09
	
	
	//UC-PGM-CRM-018.4_ESPRD00379771_05JAN10
	private List deletedEventsList = new ArrayList();
	
	/**
	 * @return Returns the deletedEventsList.
	 */
	public List getDeletedEventsList() {
		return deletedEventsList;
	}
	/**
	 * @param deletedEventsList The deletedEventsList to set.
	 */
	public void setDeletedEventsList(List deletedEventsList) {
		this.deletedEventsList = deletedEventsList;
	}
	private List deletedActCMCaseEventsList = new ArrayList();

	/**
	 * @return Returns the deletedActCMCaseEventsList.
	 */
	public List getDeletedActCMCaseEventsList() {
		return deletedActCMCaseEventsList;
	}
	/**
	 * @param deletedActCMCaseEventsList The deletedActCMCaseEventsList to set.
	 */
	public void setDeletedActCMCaseEventsList(List deletedActCMCaseEventsList) {
		this.deletedActCMCaseEventsList = deletedActCMCaseEventsList;
	}
//	EOF UC-PGM-CRM-018.4_ESPRD00379771_05JAN10
	//Commented for Heap Dump ESPRD00935080
	/**
	 * @return Returns the caseStatusValidValues.
	 */
	/*public Map getCaseStatusValidValues() {
		return caseStatusValidValues;
	}
	*//**
	 * @param caseStatusValidValues The caseStatusValidValues to set.
	 *//*
	public void setCaseStatusValidValues(Map caseStatusValidValues) {
		this.caseStatusValidValues = caseStatusValidValues;
	}*/
	/**
	 * @return Returns the validValuesFlag.
	 */
	public boolean isValidValuesFlag() {
		return validValuesFlag;
	}
	/**
	 * @param validValuesFlag The validValuesFlag to set.
	 */
	public void setValidValuesFlag(boolean validValuesFlag) {
		this.validValuesFlag = validValuesFlag;
	}
	/**
	 * @param advOptionsFl The advOptionsFl to set.
	 */
	public void setAdvOptionsFl(boolean advOptionsFl) {
		this.advOptionsFl = advOptionsFl;
	}
//	UC-PGM-CRM-018_ESPRD00367808_18JAN10
	private boolean caseStatusSetFlag = false;
	/**
	 * @return Returns the caseStatusSetFlag.
	 */
	public boolean isCaseStatusSetFlag() {
		return caseStatusSetFlag;
	}
	/**
	 * @param caseStatusSetFlag The caseStatusSetFlag to set.
	 */
	public void setCaseStatusSetFlag(boolean caseStatusSetFlag) {
		this.caseStatusSetFlag = caseStatusSetFlag;
	}	//EOF UC-PGM-CRM-018_ESPRD00367808_18JAN10
	private List tempAssociatedCaseRecordsList = new ArrayList();//UC-PGM-CRM-018_ESPRD00388733_19JAN10

	/**
	 * @return Returns the tempAssociatedCaseRecordsList.
	 */
	public List getTempAssociatedCaseRecordsList() {
		return tempAssociatedCaseRecordsList;
	}
	/**
	 * @param tempAssociatedCaseRecordsList The tempAssociatedCaseRecordsList to set.
	 */
	public void setTempAssociatedCaseRecordsList(
			List tempAssociatedCaseRecordsList) {
		this.tempAssociatedCaseRecordsList = tempAssociatedCaseRecordsList;
	}//EOF UC-PGM-CRM-018_ESPRD00388733_19JAN10
	
	
	/**
	 * @return Returns the completedApprFlag.
	 */
	public boolean isCompletedApprFlag() {
		return completedApprFlag;
	}
	/**
	 * @param completedApprFlag The completedApprFlag to set.
	 */
	public void setCompletedApprFlag(boolean completedApprFlag) {
		this.completedApprFlag = completedApprFlag;
	}
	/**
	 * @return Returns the completedDeniFlag.
	 */
	public boolean isCompletedDeniFlag() {
		return completedDeniFlag;
	}
	/**
	 * @param completedDeniFlag The completedDeniFlag to set.
	 */
	public void setCompletedDeniFlag(boolean completedDeniFlag) {
		this.completedDeniFlag = completedDeniFlag;
	}
	/**
	 * @return Returns the eventToRemove.
	 */
	public List getEventToRemove() {
		return eventToRemove;
	}
	/**
	 * @param eventToRemove The eventToRemove to set.
	 */
	public void setEventToRemove(List eventToRemove) {
		this.eventToRemove = eventToRemove;
	}
	
	/**
	 * @return Returns the stepToRemove.
	 */
	public List getStepToRemove() {
		return stepToRemove;
	}

	/**
	 * @param stepToRemove The stepToRemove to set.
	 */
	public void setStepToRemove(List stepToRemove) {
		this.stepToRemove = stepToRemove;
	}
//	UC-PGM-CRM-018.2_ESPRD00433227_12MAR2010
	private boolean caseStatusClosed =false;
	
	/**
	 * @return Returns the caseStatusClosed.
	 */
	public boolean isCaseStatusClosed() {
		return caseStatusClosed;
	}
	/**
	 * @param caseStatusClosed The caseStatusClosed to set.
	 */
	public void setCaseStatusClosed(boolean caseStatusClosed) {
		this.caseStatusClosed = caseStatusClosed;
	}//	EOf UC-PGM-CRM-018.2_ESPRD00433227_12MAR2010
	/**
	 * @return Returns the showVirusMessage.
	 */
	public boolean isShowVirusMessage() {
		return showVirusMessage;
	}
	/**
	 * @param showVirusMessage The showVirusMessage to set.
	 */
	public void setShowVirusMessage(boolean showVirusMessage) {
		this.showVirusMessage = showVirusMessage;
	}
	 /** holds the lobSK */
    private Long lobHierarchySK;
	/**
	 * @return Returns the lobHierarchySK.
	 */
	public Long getLobHierarchySK() {
		return lobHierarchySK;
	}
	/**
	 * @param lobHierarchySK The lobHierarchySK to set.
	 */
	public void setLobHierarchySK(Long lobHierarchySK) {
		this.lobHierarchySK = lobHierarchySK;
	}
	
	private boolean viewCreateLetter;
	
	/**
	 * @return Returns the viewCreateLetter.
	 */
	public boolean isViewCreateLetter() {
		return viewCreateLetter;
	}
	/**
	 * @param viewCreateLetter The viewCreateLetter to set.
	 */
	public void setViewCreateLetter(boolean viewCreateLetter) {
		this.viewCreateLetter = viewCreateLetter;
	}
	
		
		//Added for CR Tpl Policy Holder starts
		private boolean showTPLpolcyHldrlabel = false;
		
		
		/**
		 * @return Returns the showTPLpolcyHldrlabel.
		 */
		public boolean isShowTPLpolcyHldrlabel() {
			return showTPLpolcyHldrlabel;
		}
		/**
		 * @param showTPLpolcyHldrlabel The showTPLpolcyHldrlabel to set.
		 */
		public void setShowTPLpolcyHldrlabel(boolean showTPLpolcyHldrlabel) {
			this.showTPLpolcyHldrlabel = showTPLpolcyHldrlabel;
		}
		//Added for CR Tpl Policy Holder ends
		//CR_ESPRD00373565_LogCase_04AUG2010
		private boolean enableAuditLogForLogCase = false;

		/**
		 * @return Returns the enableAuditLogForLogCase.
		 */
		public boolean isEnableAuditLogForLogCase() {
			return enableAuditLogForLogCase;
		}
		/**
		 * @param enableAuditLogForLogCase The enableAuditLogForLogCase to set.
		 */
		public void setEnableAuditLogForLogCase(boolean enableAuditLogForLogCase) {
			this.enableAuditLogForLogCase = enableAuditLogForLogCase;
		}
		//EOF CR_ESPRD00373565_LogCase_04AUG2010
		//ESPRD00513826_UC-PGM-CRM-18_25AUG2010
		private boolean disableWorkUnitInAddMode =false;

		/**
		 * @return Returns the disableWorkUnitInAddMode.
		 */
		public boolean isDisableWorkUnitInAddMode() {
			return disableWorkUnitInAddMode;
		}
		/**
		 * @param disableWorkUnitInAddMode The disableWorkUnitInAddMode to set.
		 */
		public void setDisableWorkUnitInAddMode(boolean disableWorkUnitInAddMode) {
			this.disableWorkUnitInAddMode = disableWorkUnitInAddMode;
		}

		//EOF ESPRD00513826_UC-PGM-CRM-18_25AUG2010
		//Added for defect ESPRD00510702 starts
		private boolean disableRoutingUserworkunitName = true;
		/**
		 * @return Returns the disableRoutingUserworkunitName.
		 */
		public boolean isDisableRoutingUserworkunitName() {
			return disableRoutingUserworkunitName;
		}
		/**
		 * @param disableRoutingUserworkunitName The disableRoutingUserworkunitName to set.
		 */
		public void setDisableRoutingUserworkunitName(
				boolean disableRoutingUserworkunitName) {
			this.disableRoutingUserworkunitName = disableRoutingUserworkunitName;
		}
		//defect ESPRD00510702 ends
		//ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010

		private String commonEntitySK = null;
		/**
		 * @return Returns the commonEntitySK.
		 */
		public String getCommonEntitySK() {
			return commonEntitySK;
		}
		/**
		 * @param commonEntitySK The commonEntitySK to set.
		 */
		public void setCommonEntitySK(String commonEntitySK) {
			this.commonEntitySK = commonEntitySK;
		}
		//EOf ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
		/**
	     * List to hold reassignAllDeptUsersList values.
	     */
	    private List routeAllDeptUsersList = new ArrayList();
		
		/**
		 * @return Returns the routeAllDeptUsersList.
		 */
		public List getRouteAllDeptUsersList() {
			return routeAllDeptUsersList;
		}
		/**
		 * @param routeAllDeptUsersList The routeAllDeptUsersList to set.
		 */
		public void setRouteAllDeptUsersList(List routeAllDeptUsersList) {
			this.routeAllDeptUsersList = routeAllDeptUsersList;
		}
//		added for defect ESPRD00549039 starts

		private boolean  navToOtherPortletFlag = false;
		
		/**
		 * @return Returns the navToOtherPortletFlag.
		 */
		public boolean isNavToOtherPortletFlag() {
			return navToOtherPortletFlag;
		}
		/**
		 * @param navToOtherPortletFlag The navToOtherPortletFlag to set.
		 */
		public void setNavToOtherPortletFlag(boolean navToOtherPortletFlag) {
			this.navToOtherPortletFlag = navToOtherPortletFlag;
		}
		//defect ESPRD00549039 ends
		
		private String cursorFocusInquiry;
		/**
		 * @return Returns the cursorFocusInquiry.
		 */
		public String getCursorFocusInquiry() {
			return cursorFocusInquiry;
		}
		/**
		 * @param cursorFocusInquiry The cursorFocusInquiry to set.
		 */
		public void setCursorFocusInquiry(String cursorFocusInquiry) {
			this.cursorFocusInquiry = cursorFocusInquiry;
		}
		/** it will show/hide CaseSteps error messages */
	    private boolean showCaseStepsDelteMessage = false;
		/**
		 * @return Returns the showCaseStepsDelteMessage.
		 */
		public boolean isShowCaseStepsDelteMessage() {
			return showCaseStepsDelteMessage;
		}
		/**
		 * @param showCaseStepsDelteMessage The showCaseStepsDelteMessage to set.
		 */
		public void setShowCaseStepsDelteMessage(
				boolean showCaseStepsDelteMessage) {
			this.showCaseStepsDelteMessage = showCaseStepsDelteMessage;
		}
		
		
		/** CR_ESPRD00436016 */
		private boolean showCaseRegardingTradPart = false;
		
		
		/**
		 * @return Returns the showCaseRegardingTradPart.
		 */
		public boolean isShowCaseRegardingTradPart() {
			return showCaseRegardingTradPart;
		}
		/**
		 * @param showCaseRegardingTradPart The showCaseRegardingTradPart to set.
		 */
		public void setShowCaseRegardingTradPart(
				boolean showCaseRegardingTradPart) {
			this.showCaseRegardingTradPart = showCaseRegardingTradPart;
		}
		
		 private List contact = Collections.EMPTY_LIST;
		
		/**
		 * @return Returns the contact.
		 */
		public List getContact() {
			return contact;
		}
		/**
		 * @param contact The contact to set.
		 */
		public void setContact(List contact) {
			this.contact = contact;
		}
		
		private boolean  disableAuditLogCaseRecAssocWithCase;
		/**
		 * @return Returns the disableAuditLogCaseRecAssocWithCase.
		 */
		public boolean isDisableAuditLogCaseRecAssocWithCase() {
			return disableAuditLogCaseRecAssocWithCase;
		}
		/**
		 * @param disableAuditLogCaseRecAssocWithCase The disableAuditLogCaseRecAssocWithCase to set.
		 */
		public void setDisableAuditLogCaseRecAssocWithCase(
				boolean disableAuditLogCaseRecAssocWithCase) {
			this.disableAuditLogCaseRecAssocWithCase = disableAuditLogCaseRecAssocWithCase;
		}
		//Modification for Defect ESPRD00603234 Starts
		
		private boolean disableEventDate=false;
		
		public boolean isdisableEventDate() {
			return disableEventDate;
		}
		public void setDisableEventDate(boolean disableEventDate) {
			this.disableEventDate = disableEventDate;
		}
//ADD FOR THE DEFECT ESPRD00651718
		private String caseAttachHiddenID="plus";

		public String getCaseAttachHiddenID() {
			return caseAttachHiddenID;
		}
		public void setCaseAttachHiddenID(String caseAttachHiddenID) {
			this.caseAttachHiddenID = caseAttachHiddenID;
		}
		// Modification for Defect ESPRD00603234 ends
		
		/**
		 * @return the renderAddRouting
		 */		
		public boolean isRenderAddRouting() {
			return renderAddRouting;
		}
		/**
		 * @param renderAddRouting the renderAddRouting to set
		 */
		public void setRenderAddRouting(boolean renderAddRouting) {
			this.renderAddRouting = renderAddRouting;
		}
		
		/**
		 * It holds the value of controlRequired. 
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
		
		/**
		 * Holds the value of caseStepsRowIndex
		 */
		private int caseStepsRowIndex;
		 /**
	     * @return Returns the caseStepsRowIndex.
	     */
	    public int getCaseStepsRowIndex()
	    {
	        return caseStepsRowIndex;
	    }

	    /**
	     * @param caseStepsRowIndex
	     *            The caseStepsRowIndex to set.
	     */
	    public void setCaseStepsRowIndex(int caseStepsRowIndex)
	    {
	        this.caseStepsRowIndex = caseStepsRowIndex;
	    }
	    
	    /**
		 * Holds the value of caseEventsRowIndex
		 */
	    private int caseEventsRowIndex;

		/**
		 * @return the caseEventsRowIndex
		 */
		public int getCaseEventsRowIndex() {
			return caseEventsRowIndex;
		}
		/**
		 * @param caseEventsRowIndex the caseEventsRowIndex to set
		 */
		public void setCaseEventsRowIndex(int caseEventsRowIndex) {
			this.caseEventsRowIndex = caseEventsRowIndex;
		}

		/**
		 * Holds the value of alertsRowIndex
		 */
		private int alertsRowIndex;

		/**
		 * @return the alertsRowIndex
		 */
		public int getAlertsRowIndex() {
			return alertsRowIndex;
		}
		/**
		 * @param alertsRowIndex the alertsRowIndex to set
		 */
		public void setAlertsRowIndex(int alertsRowIndex) {
			this.alertsRowIndex = alertsRowIndex;
		}
		
		/**
		 * Holds the value of caseRecordsWithThisCaeRowIndex
		 */
		private int caseRecordsWithThisCaeRowIndex;

		/**
		 * @return the caseRecordsWithThisCaeRowIndex
		 */
		public int getCaseRecordsWithThisCaeRowIndex() {
			return caseRecordsWithThisCaeRowIndex;
		}
		/**
		 * @param caseRecordsWithThisCaeRowIndex the caseRecordsWithThisCaeRowIndex to set
		 */
		public void setCaseRecordsWithThisCaeRowIndex(int caseRecordsWithThisCaeRowIndex) {
			this.caseRecordsWithThisCaeRowIndex = caseRecordsWithThisCaeRowIndex;
		}
		
		/**
		 * Holds the value of caseExistingCaseRecordsRowIndex
		 */
		private int caseExistingCaseRecordsRowIndex;

		/**
		 * @return the caseExistingCaseRecordsRowIndex
		 */
		public int getCaseExistingCaseRecordsRowIndex() {
			return caseExistingCaseRecordsRowIndex;
		}
		/**
		 * @param caseExistingCaseRecordsRowIndex the caseExistingCaseRecordsRowIndex to set
		 */
		public void setCaseExistingCaseRecordsRowIndex(
				int caseExistingCaseRecordsRowIndex) {
			this.caseExistingCaseRecordsRowIndex = caseExistingCaseRecordsRowIndex;
		}

		/**
		 * Holds the value of crRecordsAssocWithCaseRowIndex
		 */
		private int crRecordsAssocWithCaseRowIndex;

		/**
		 * @return the crRecordsAssocWithCaseRowIndex
		 */
		public int getCrRecordsAssocWithCaseRowIndex() {
			return crRecordsAssocWithCaseRowIndex;
		}
		/**
		 * @param crRecordsAssocWithCaseRowIndex the crRecordsAssocWithCaseRowIndex to set
		 */
		public void setCrRecordsAssocWithCaseRowIndex(int crRecordsAssocWithCaseRowIndex) {
			this.crRecordsAssocWithCaseRowIndex = crRecordsAssocWithCaseRowIndex;
		}
		
		/**
		 * Holds the value of routingRowIndex
		 */
		private int routingRowIndex;

		/**
		 * @return the routingRowIndex
		 */
		public int getRoutingRowIndex() {
			return routingRowIndex;
		}
		/**
		 * @param routingRowIndex the routingRowIndex to set
		 */
		public void setRoutingRowIndex(int routingRowIndex) {
			this.routingRowIndex = routingRowIndex;
		}
		
		/**
		 * Holds the value of attachmentsRowIndex
		 */
		private int attachmentsRowIndex;

		/**
		 * @return the attachmentsRowIndex
		 */
		public int getAttachmentsRowIndex() {
			return attachmentsRowIndex;
		}
		/**
		 * @param attachmentsRowIndex the attachmentsRowIndex to set
		 */
		public void setAttachmentsRowIndex(int attachmentsRowIndex) {
			this.attachmentsRowIndex = attachmentsRowIndex;
		}
		
		/**
		 * Holds the value of altIdentifiersMemRowIndex
		 */
		private int altIdentifiersMemRowIndex;

		/**
		 * @return the altIdentifiersMemRowIndex
		 */
		public int getAltIdentifiersMemRowIndex() {
			return altIdentifiersMemRowIndex;
		}
		/**
		 * @param altIdentifiersMemRowIndex the altIdentifiersMemRowIndex to set
		 */
		public void setAltIdentifiersMemRowIndex(int altIdentifiersMemRowIndex) {
			this.altIdentifiersMemRowIndex = altIdentifiersMemRowIndex;
		}
		
		/**
		 * Holds the value of altIdentifiersProvRowIndex
		 */
		private int altIdentifiersProvRowIndex;

		/**
		 * @return the altIdentifiersProvRowIndex
		 */
		public int getAltIdentifiersProvRowIndex() {
			return altIdentifiersProvRowIndex;
		}
		/**
		 * @param altIdentifiersProvRowIndex the altIdentifiersProvRowIndex to set
		 */
		public void setAltIdentifiersProvRowIndex(int altIdentifiersProvRowIndex) {
			this.altIdentifiersProvRowIndex = altIdentifiersProvRowIndex;
		}
		
		/**
		 * Holds the value of addiCaseEntityRowIndex
		 */
		private int addiCaseEntityRowIndex;

		/**
		 * @return the addiCaseEntityRowIndex
		 */
		public int getAddiCaseEntityRowIndex() {
			return addiCaseEntityRowIndex;
		}
		/**
		 * @param addiCaseEntityRowIndex the addiCaseEntityRowIndex to set
		 */
		public void setAddiCaseEntityRowIndex(int addiCaseEntityRowIndex) {
			this.addiCaseEntityRowIndex = addiCaseEntityRowIndex;
		}
		//  Added for ESPRD00357689
	    private boolean invalidEventDate = false;

	    	/**
	    	 * @return Returns the invalidEventDate.
	    	 */
	    	public boolean isInvalidEventDate() {
	    		return invalidEventDate;
	    	}
	    	/**
	    	 * @param invalidEventDate The invalidEventDate to set.
	    	 */
	    	public void setInvalidEventDate(boolean invalidEventDate) {
	    		this.invalidEventDate = invalidEventDate;
	    	}
	    	//EOF for ESPRD00357689
	    	
	    	// Added for defect ESPRD00674473 starts
	    	private List deletedStepsList = new ArrayList();

	    	public List getDeletedStepsList() {
	    		return deletedStepsList;
	    	}

	    	public void setDeletedStepsList(List deletedStepsList) {
	    		this.deletedStepsList = deletedStepsList;
	    	}

	    	private List deletedActCMCaseStepsList = new ArrayList();

	    	public List getDeletedActCMCaseStepsList() {
	    		return deletedActCMCaseStepsList;
	    	}

	    	public void setDeletedActCMCaseStepsList(List deletedActCMCaseStepsList) {
	    		this.deletedActCMCaseStepsList = deletedActCMCaseStepsList;
	    	}
	    	// defect ESPRD00674473 ends
			/**
			 * @return the loadLetterData
			 */
			public boolean isLoadLetterData() {
				return loadLetterData;
			}
			/**
			 * @param loadLetterData the loadLetterData to set
			 */
			public void setLoadLetterData(boolean loadLetterData) {
				this.loadLetterData = loadLetterData;
			}
			
			 private boolean renderEditRouting = false;

			public boolean isRenderEditRouting() {
				return renderEditRouting;
			}
			public void setRenderEditRouting(boolean renderEditRouting) {
				this.renderEditRouting = renderEditRouting;
			}
			
			private boolean renderAddEditRouting = false;

			public boolean isRenderAddEditRouting() {
				return renderAddEditRouting;
			}
			public void setRenderAddEditRouting(boolean renderAddEditRouting) {
				this.renderAddEditRouting = renderAddEditRouting;
			}

			private boolean caseCrspnAsctErrormsg=false;
			
			 
			/**
			 * @return Returns the caseCrspnAsctErrormsg.
			 */
			public boolean isCaseCrspnAsctErrormsg() {
				return caseCrspnAsctErrormsg;
			}
			/**
			 * @param caseCrspnAsctErrormsg The caseCrspnAsctErrormsg to set.
			 */
			public void setCaseCrspnAsctErrormsg(boolean caseCrspnAsctErrormsg) {
				this.caseCrspnAsctErrormsg = caseCrspnAsctErrormsg;
			}
			 
		/* hash map removed for performance
		   private  Map statusMap = new HashMap();

		public Map getStatusMap() {
			return statusMap;
		}
		public void setStatusMap(Map statusMap) {
			this.statusMap = statusMap;
		}	*/
		// Added for the defect ESPRD00687821
		/*private Map entityTypeMap = new HashMap();
		
		public Map getEntityTypeMap() {
			return entityTypeMap;
		}
		public void setEntityTypeMap(Map entityTypeMap) {
			this.entityTypeMap = entityTypeMap;
		}*/
		private String pageFocusId;
		
		
		/**
		 * @return Returns the pageFocusId.
		 */
		public String getPageFocusId() {
			return pageFocusId;
		}
		/**
		 * @param pageFocusId The pageFocusId to set.
		 */
		public void setPageFocusId(String pageFocusId) {
			this.pageFocusId = pageFocusId;
		}
		
		// Begin - Added the code for the PanelGrid Fix
		/**
		 * Holds the value of customFieldVOList.
		 */
		private List customFieldVOList;
		
		/**
		 * @return the customFieldVOList
		 */
		public List getCustomFieldVOList() {
			return customFieldVOList;
		}
		/**
		 * @param customFieldVOList the customFieldVOList to set
		 */
		public void setCustomFieldVOList(List customFieldVOList) {
			this.customFieldVOList = customFieldVOList;
		}
		// End - Added the code for the PanelGrid Fix
		
		// Begin - Added the code for the defect id ESPRD00710222_12OCT2011
		/**
		 * Hold the value of dduExistedRecordErrorMsg.
		 */
		private boolean dduExistedRecordErrorMsg = true;

		/**
		 * @return the dduExistedRecordErrorMsg
		 */
		public boolean isDduExistedRecordErrorMsg() {
			return dduExistedRecordErrorMsg;
		}
		/**
		 * @param dduExistedRecordErrorMsg the dduExistedRecordErrorMsg to set
		 */
		public void setDduExistedRecordErrorMsg(boolean dduExistedRecordErrorMsg) {
			this.dduExistedRecordErrorMsg = dduExistedRecordErrorMsg;
		}
		// End - Added the code for the defect id ESPRD00710222_12OCT2011
		
		// Begin - Added this code for displaying the delete successful message for the defect id ESPRD00712855_19OCT2011
		/**
		 * Holds the value of showCaseStepsDelteMessage.
		 */
		private boolean CaseEventDeleteMsgFlag = false;

		/**
		 * @return the caseEventDeleteMsgFlag
		 */
		public boolean isCaseEventDeleteMsgFlag() {
			return CaseEventDeleteMsgFlag;
		}
		/**
		 * @param caseEventDeleteMsgFlag the caseEventDeleteMsgFlag to set
		 */
		public void setCaseEventDeleteMsgFlag(boolean caseEventDeleteMsgFlag) {
			CaseEventDeleteMsgFlag = caseEventDeleteMsgFlag;
		} 
		// End - Added this code for displaying the delete successful message for the defect id ESPRD00712855_19OCT2011

		// Begin - Added this code for displaying the error message for the defect id ESPRD00712855_19OCT2011		
		/**
		 * Holds the value of showCaseDetailsMessages
		 */
		private boolean showCaseDetailsMessages = false;

		/**
		 * @return the showCaseDetailsMessages
		 */
		public boolean isShowCaseDetailsMessages() {
			return showCaseDetailsMessages;
		}
		/**
		 * @param showCaseDetailsMessages the showCaseDetailsMessages to set
		 */
		public void setShowCaseDetailsMessages(boolean showCaseDetailsMessages) {
			this.showCaseDetailsMessages = showCaseDetailsMessages;
		}
		// End - Added this code for displaying the error message for the defect id ESPRD00712855_19OCT2011
		
		// Begin - Added new property for the defect id UC-PGM-CRM-018.1_ESPRD00709390_02NOV2011
		
		public List userOfSameBU = new ArrayList();

		/**
		 * @return the userOfSameBU
		 */
		public List getUserOfSameBU() {
			return userOfSameBU;
		}
		/**
		 * @param userOfSameBU the userOfSameBU to set
		 */
		public void setUserOfSameBU(List userOfSameBU) {
			this.userOfSameBU = userOfSameBU;
		}
		// End - Added new property for the defect id UC-PGM-CRM-018.1_ESPRD00709390_02NOV2011
		//Added for the performance issue 
		private String businessUnitDesc;

		public String getBusinessUnitDesc() {
			return businessUnitDesc;
		}

		public void setBusinessUnitDesc(String businessUnitDesc) {
			this.businessUnitDesc = businessUnitDesc;
		}
		// Added for the Defect: ESPRD00709390
		private boolean  assignedToUser = false;

		public boolean isAssignedToUser() {
			return assignedToUser;
		}
		public void setAssignedToUser(boolean assignedToUser) {
			this.assignedToUser = assignedToUser;
		}
		
		// Begin - Added the code for the defect id ESPRD00725239_16NOV2011
		/**
		 * Hold the value of entityIdForNote.
		 */
		private String entityIdForNote;
		
		/**
		 * Hold the value of entityNameForNote.
		 */
		private String entityNameForNote;

		/**
		 * @return the entityIdForNote
		 */
		public String getEntityIdForNote() {
			return entityIdForNote;
		}
		/**
		 * @param entityIdForNote the entityIdForNote to set
		 */
		public void setEntityIdForNote(String entityIdForNote) {
			this.entityIdForNote = entityIdForNote;
		}
		/**
		 * @return the entityNameForNote
		 */
		public String getEntityNameForNote() {
			return entityNameForNote;
		}
		/**
		 * @param entityNameForNote the entityNameForNote to set
		 */
		public void setEntityNameForNote(String entityNameForNote) {
			this.entityNameForNote = entityNameForNote;
		}
		// End - Added the code for the defect id ESPRD00725239_16NOV2011
		
		// Start - Added the code for the defect ESPRD00688792

		private boolean navigatedToCorrespondence = false;

		public boolean isNavigatedToCorrespondence() {
			return navigatedToCorrespondence;
		}
		public void setNavigatedToCorrespondence(boolean navigatedToCorrespondence) {
			this.navigatedToCorrespondence = navigatedToCorrespondence;
		}
		// End - Added the code for the defect ESPRD00688792
		
		// Begin - Added the code for the defect id ESPRD00702153_30NOV2011.
		/**
		*  Holds the value of loggedInUserSK to get the userSK.
		*/
		private Long loggedInUserSK;

		/**
		 * @return the loggedInUserSK
		 */
		public Long getLoggedInUserSK() {
			return loggedInUserSK;
		}
		/**
		 * @param loggedInUserSK the loggedInUserSK to set
		 */
		public void setLoggedInUserSK(Long loggedInUserSK) {
			this.loggedInUserSK = loggedInUserSK;
		}
		// End - Added the code for the defect id ESPRD00702153_30NOV2011.	
		
		//ADDED FOR THE DEFECT ESPRD00736967
		private boolean advOptionsF2 = false;

		public boolean isAdvOptionsF2() {
			return advOptionsF2;
		}
		public void setAdvOptionsF2(boolean advOptionsF2) {
			this.advOptionsF2 = advOptionsF2;
			
		}
		//ADDED END FOR THE DEFECT ESPRD00736967
		
		// Modified for the defect ESPRD00688792 start
		private String sysIdForUP;

		public String getSysIdForUP() {
			return sysIdForUP;
		}
		public void setSysIdForUP(String sysIdForUP) {
			this.sysIdForUP = sysIdForUP;
		}

		//Modified for the defect ESPRD00688792 end
		
		//for ESPRD00747731
		private boolean showAttachmentDelMsg;

		/**
		 * @return the showAttachmentDelMsg
		 */
		public boolean isShowAttachmentDelMsg() {
			return showAttachmentDelMsg;
		}
		/**
		 * @param showAttachmentDelMsg the showAttachmentDelMsg to set
		 */
		public void setShowAttachmentDelMsg(boolean showAttachmentDelMsg) {
			this.showAttachmentDelMsg = showAttachmentDelMsg;
		}
		

		private boolean disableSaveAttachment;

		/**
		 * @return the disableSaveAttachment
		 */
		public boolean isDisableSaveAttachment() {
			return disableSaveAttachment;
		}
		/**
		 * @param disableSaveAttachment the disableSaveAttachment to set
		 */
		public void setDisableSaveAttachment(boolean disableSaveAttachment) {
			this.disableSaveAttachment = disableSaveAttachment;
		}
		
		private List eventNotifyList=new ArrayList();

		/**
		 * @return the eventNotifyList
		 */
		public List getEventNotifyList() {
			return eventNotifyList;
		}
		/**
		 * @param eventNotifyList the eventNotifyList to set
		 */
		public void setEventNotifyList(List eventNotifyList) {
			this.eventNotifyList = eventNotifyList;
		}
		
		/**
		 * to render error or save messages in jsp
		 */
		private boolean logCaseErrMsgFlag;

		/**
		 * @return the logCaseErrMsgFlag
		 */
		public boolean isLogCaseErrMsgFlag() {
			return logCaseErrMsgFlag;
		}
		/**
		 * @param logCaseErrMsgFlag the logCaseErrMsgFlag to set
		 */
		public void setLogCaseErrMsgFlag(boolean logCaseErrMsgFlag) {
			this.logCaseErrMsgFlag = logCaseErrMsgFlag;
		}
		
		private boolean resetCaseInAddModeFlag;
		private String caseEntityDetails;

		/**
		 * @return the caseEntityDetails
		 */
		public String getCaseEntityDetails() {
			return caseEntityDetails;
		}
		/**
		 * @param caseEntityDetails the caseEntityDetails to set
		 */
		public void setCaseEntityDetails(String caseEntityDetails) {
			this.caseEntityDetails = caseEntityDetails;
		}
		/**
		 * @return the resetCaseInAddModeFlag
		 */
		public boolean isResetCaseInAddModeFlag() {
			return resetCaseInAddModeFlag;
		}
		/**
		 * @param resetCaseInAddModeFlag the resetCaseInAddModeFlag to set
		 */
		public void setResetCaseInAddModeFlag(boolean resetCaseInAddModeFlag) {
			this.resetCaseInAddModeFlag = resetCaseInAddModeFlag;
		}
		
		/** This flag is true for inquiry mode users.
		 *  added for defect ESPRD00774844
		 * */
		private boolean inInquiryModeFlag;

		/**
		 * @return the inInquiryModeFlag
		 */
		public boolean isInInquiryModeFlag() {
			return inInquiryModeFlag;
		}
		/**
		 * @param inInquiryModeFlag the inInquiryModeFlag to set
		 */
		public void setInInquiryModeFlag(boolean inInquiryModeFlag) {
			this.inInquiryModeFlag = inInquiryModeFlag;
		}
        //  Added for the Defect:ESPRD00790505
		private boolean enableAuditLogs;

		/**
		 * @return the enableAuditLogs
		 */
		public boolean isEnableAuditLogs() {
			return enableAuditLogs;
		}
		/**
		 * @param enableAuditLogs the enableAuditLogs to set
		 */
		public void setEnableAuditLogs(boolean enableAuditLogs) {
			this.enableAuditLogs = enableAuditLogs;
		}
	   // Start for the Defect ID: ESPRD00795389
		/**
		 * This field is used to store CaseRegardingProviderVO for Additional Case entities.
		 */
	    private CaseRegardingProviderVO caseForProviderVO = new CaseRegardingProviderVO();
	    /**
		 * @return the caseForProviderVO
		 */
		public CaseRegardingProviderVO getCaseForProviderVO() {
			return caseForProviderVO;
		}
		/**
		 * @param caseForProviderVO the caseForProviderVO to set
		 */
		public void setCaseForProviderVO(CaseRegardingProviderVO caseForProviderVO) {
			this.caseForProviderVO = caseForProviderVO;
		}
		/**
		 * This field is used to store CaseRegardingMemberVO for Additional Case entities.
		 */
		private CaseRegardingMemberVO caseForMemberVO = new CaseRegardingMemberVO();
	    /**
		 * @return the caseForMemberVO
		 */
		public CaseRegardingMemberVO getCaseForMemberVO() {
			return caseForMemberVO;
		}
		/**
		 * @param caseForMemberVO the caseForMemberVO to set
		 */
		public void setCaseForMemberVO(CaseRegardingMemberVO caseForMemberVO) {
			this.caseForMemberVO = caseForMemberVO;
		}
		/**
		 * This field is used to store CaseRegardingTPL for Additional Case entities.
		 */
		private CaseRegardingTPL  caseForTPLVO = new CaseRegardingTPL();

		/**
		 * @return the caseForTPLVO
		 */
		public CaseRegardingTPL getCaseForTPLVO() {
			return caseForTPLVO;
		}
		/**
		 * @param caseForTPLVO the caseForTPLVO to set
		 */
		public void setCaseForTPLVO(CaseRegardingTPL caseForTPLVO) {
			this.caseForTPLVO = caseForTPLVO;
		}
		// End for the Defect ID: ESPRD00795389
		/**
		 * This flag will be useful for avoiding every time calling input hidden
		 * method getLoadDataFromEntity() while page loads, for Defect ESPRD00802462
		 * */
		private boolean loadEntityDataFlag;

		/**
		 * @return the loadEntityDataFlag
		 */
		public boolean isLoadEntityDataFlag() {
			return loadEntityDataFlag;
		}
		/**
		 * @param loadEntityDataFlag the loadEntityDataFlag to set
		 */
		public void setLoadEntityDataFlag(boolean loadEntityDataFlag) {
			this.loadEntityDataFlag = loadEntityDataFlag;
		}
		
		/** ESPRD00790505 This flag is to hold the value for
		 *  validating whether attachment is added or modified. hence
		 *  the docufinity server call is managed accordingly.
		 * */
		private Boolean attachmentAddOrUpdate;
		private int attachmentListSize;

		public int getAttachmentListSize() {
			return attachmentListSize;
		}
		public void setAttachmentListSize(int attachmentListSize) {
			this.attachmentListSize = attachmentListSize;
		}
		/**
		 * @return the attachmentAddOrUpdate
		 */
		public Boolean getAttachmentAddOrUpdate() {
			return attachmentAddOrUpdate;
		}
		/**
		 * @param attachmentAddOrUpdate the attachmentAddOrUpdate to set
		 */
		public void setAttachmentAddOrUpdate(Boolean attachmentAddOrUpdate) {
			this.attachmentAddOrUpdate = attachmentAddOrUpdate;
		}
		
		/**Modified for ESPRD00864837 
		 * Holds the sequence number of event & step 
		 *  which is edited latest.
		 * */
		private Integer editEventSeq;
		private Integer editStepSeq;
		private Integer addEventSeq;
		private Integer addStepSeq;
		

		/**
		 * @return the addEventSeq
		 */
		public Integer getAddEventSeq() {
			return addEventSeq;
		}
		/**
		 * @param addEventSeq the addEventSeq to set
		 */
		public void setAddEventSeq(Integer addEventSeq) {
			this.addEventSeq = addEventSeq;
		}
		/**
		 * @return the addStepSeq
		 */
		public Integer getAddStepSeq() {
			return addStepSeq;
		}
		/**
		 * @param addStepSeq the addStepSeq to set
		 */
		public void setAddStepSeq(Integer addStepSeq) {
			this.addStepSeq = addStepSeq;
		}
		/**
		 * @return the editStepSeq
		 */
		public Integer getEditStepSeq() {
			return editStepSeq;
		}
		/**
		 * @param editStepSeq the editStepSeq to set
		 */
		public void setEditStepSeq(Integer editStepSeq) {
			this.editStepSeq = editStepSeq;
		}
		/**
		 * @return the editEventSeq
		 */
		public Integer getEditEventSeq() {
			return editEventSeq;
		}
		/**
		 * @param editEventSeq the editEventSeq to set
		 */
		public void setEditEventSeq(Integer editEventSeq) {
			this.editEventSeq = editEventSeq;
		}
		
		//Code added for CR_908771
		private boolean saveFlag = false;

		public boolean isSaveFlag() {
			return saveFlag;
		}
		public void setSaveFlag(boolean saveFlag) {
			this.saveFlag = saveFlag;
		}
		
		/**
	     * This field is used to store searchForCaseAndCR.
	     * Added for DEFECT ESPRD00903176
	     */
	    private boolean searchForCaseAndCR = false;

		public boolean isSearchForCaseAndCR() {
			return searchForCaseAndCR;
		}
		public void setSearchForCaseAndCR(boolean searchForCaseAndCR) {
			this.searchForCaseAndCR = searchForCaseAndCR;
		}
		/**
	     * This field is used to store associatePlusMinusFlag.
	     * Added for DEFECT ESPRD00903176
	     */
		private boolean associatePlusMinusFlag = true;

		public boolean isAssociatePlusMinusFlag() {
			return associatePlusMinusFlag;
		}
		public void setAssociatePlusMinusFlag(boolean associatePlusMinusFlag) {
			this.associatePlusMinusFlag = associatePlusMinusFlag;
		}
}
