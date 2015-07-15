/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
//import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
//import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCorrespondenceVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForMemberVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForProviderVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForTradingPartnerVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceRecordVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.util.helper.MessageUtil;

/**
 * @author Wipro This class is a data bean which holds the value objects and
 *         other data related to add/update Correspondence functionality.
 */
public class CorrespondenceDataBean
        extends EnterpriseBaseDataBean
{
    /**
     * Constructor for CorrespondenceDataBean.
     */
    public CorrespondenceDataBean()
    {
        super();
    }

  
    /** Enterprise Logger for Logging */
    /*private transient EnterpriseLogger logger = EnterpriseLogFactory
     .getLogger(CorrespondenceDataBean.class);*/

    /**
     * This field is used to store correspondenceRecordVO
     */
    private CorrespondenceRecordVO correspondenceRecordVO = new CorrespondenceRecordVO();

    /**
     * This field is used to store correspondenceRecordVO
     */
    private CustomFieldValueVO customFieldValueVO = new CustomFieldValueVO();

    /**
     * This field is used to store categoryList.
     */
    private List categoryList = new ArrayList();

    /**
     * This field is used to store CustomFieldDOList.
     */
    private List customFieldDOList = new ArrayList();

    /**
     * This field is used to store listOfCategoryDOs.
     */
    private List listOfCategoryDOs = new ArrayList();

    /**
     * This field is used to store priorityVVList.
     */
    private List priorityVVList = new ArrayList();

    /**
     * This field is used to store lobVVList.
     */
    private List lobVVList = new ArrayList();

    /**
     * This field is used to store statValidValues.
     */
    private List statValidValues = new ArrayList();

    /**
     * This field is used to store sourceVVList.
     */
    private List sourceVVList = new ArrayList();

    /**
     * This field is used to store resolnVVList.
     */
    private List resolnVVList = new ArrayList();

    /**
     * This field is used to store subjectValidValues.
     */
    private List subjectValidValues = new ArrayList();

    /**
     * This field is used to store departmentsList.
     */
    private List departmentsList = new ArrayList();

    /**
     * This field is used to store categorySubjectValues.
     */
    private List categorySubjectValues = new ArrayList();

    /**
     * This field is used to store listOfContacts.
     */
    private List listOfContacts = new ArrayList();

    /**
     * This field is used to store listOfAlternateIds.
     */
    private List listOfAlternateIds = new ArrayList();

    /**
     * This field is used to store assocCRList.
     */
    private List assocCRList = new ArrayList();

    /**
     * This field is used to store existCRList.
     */
    private List existCRList = new ArrayList();

    /**
     * This field is used to store contactList.
     */
    private List contactList = new ArrayList();

    /** holds the form elements */
    //Heap Dump private HtmlPanelGrid formElements = new HtmlPanelGrid();

    /** holds the CustomFieldValueVO list
     *  hash map performance issue code change */
    private Map cfValueSKMap;

    /**
     * This field is used to store correspondenceForVO.
     */
    private CorrespondenceForVO correspondenceForVO;

    /**
     * This field is used to store correspondenceForMemberVO.
     */
    private CorrespondenceForMemberVO correspondenceForMemberVO;

    /**
     * This field is used to store correspondenceForProviderVO.
     */
    private CorrespondenceForProviderVO correspondenceForProviderVO;

    /**
     * This field is used to store correspondenceForProviderVO. ADDED FOR THE Correspondence ESPRD00436016
     */
    private CorrespondenceForTradingPartnerVO correspondenceForTradingPartnerVO;
    /**
     * This field is used to Render Custom fields section.
     */
    private boolean renderCustomFlds = false;

    /**
     * This field is used to store renderInqAbtForVO.
     */
    private boolean renderInqAbtForVO = false;
    
    /**
     * This field is used to store InqAbtForProvider.
     */
    private boolean InqAbtForProvider = false;

    /**
     * This field is used to store renderInqAbtForMemberVO.
     */
    private boolean renderInqAbtForMemberVO = false;

    /**
     * This field is used to store renderInqAbtForProviderVO.
     */
    private boolean renderInqAbtForProviderVO = false;

    /**
     * This field is used to store renderInqAbtForProviderVO.
     */
    private boolean renderInqAbtForTradingPartnerVO = false;
    /**
     * This field is used to store inquiringAboutIndex.
     */
    private int inquiringAboutIndex;

    /**
     * Variable for sorting.
     */
    private String imageRender;

    /**
     * This field is used to store renderCrspdForVO.
     */
    private boolean renderCrspdForVO = false;

    /**
     * This field is used to store renderCrspdProviderForVO.
     */
    private boolean renderCrspdProviderForVO = false;

    /**
     * This field is used to store renderCrspdMemberForVO.
     */
    private boolean renderCrspdMemberForVO = false;

    /**
     * This field is used to store renderCrspdUnEnrolledProviderForVO.
     */
    private boolean renderCrspdUnEnrolledProviderForVO = false;

    /**
     * This field is used to store renderCrspdUnEnrolledMemberForVO.
     */
    private boolean renderCrspdUnEnrolledMemberForVO = false;

    /**
     * This field is used to store renderCrspdTPLForVO.
     */
    private boolean renderCrspdTPLForVO = false;
    
    //ADDED FOR THE Correspondence ESPRD00436016
    /**
     * This field is used to store renderCrspdTrdPartForVO.
     */
    private boolean renderCrspdTrdPartForVO = false;

    /**
     * This field is used to store renderDaystoCloseFlag.
     */
    private boolean renderDaystoCloseFlag = false;

    /**
     * This field is used to store renderDaysOpenFlag.
     */
    private boolean renderDaysOpenFlag = false;

    /**
     * This field is used to store noDataExistCR.
     */
    private boolean noDataExistCR = true;

    /**
     * This field is used to store noDataAssocCR.
     */
    private boolean noDataAssocCR = true;

    /**
     * This field is used to store searchForCR.
     */
    private boolean searchForCR = false;

    /**
     * This field is used to store updateMode.
     */
    private boolean updateMode = false;

    /**
     * This field is used to store associatedCorrespondenceVO.
     */
    private AssociatedCorrespondenceVO associatedCorrespondenceVO;

    /**
     * This field is used to store showResultsDiv.
     */
    private boolean showResultsDiv = false;

    /**
     * This field is used to store existCR.
     */
    private boolean existCR = false;

    /**
     * This field is used to store existCR.
     */
    private boolean linkToCR = false;

    /**
     * This field is used to store crAssignedTo.
     */
    private boolean crAssignedTo = true;

    
    private boolean MessageFlag = true;
    
    /**
     * This field is used to store renderSearchCRPage.
     */
    private boolean renderSearchCRPage = false;
    
    /**
     * This field is used to store correspondenceForInquiry.
     */
    private boolean correspondenceForInquiry = false;

    /**
     * This field is used to store mapOfDeptAndLobHier.
     */
    private Map mapOfDeptAndLobHier = new HashMap();

    /**
     * This field is used to store Referred To List.
     */
    private List referredToVOList;

    /**
     * This field is used to store crNotesList.
     */
    private List crNotesList = new ArrayList();
    
    /** holds the notes of cr temporarily */
    private List crNotesSetTempList = new ArrayList();

    /**
     * This field is used to store crContactList.
     */
    private List crContactList = new ArrayList();

    /**
     * This field is used to store crContactTypeList.
     */
    private List crContactTypeList = new ArrayList();

    /**
     * This field is used to store automaticAlertForUserReq.
     */
    private boolean automaticAlertForUserReq = false;

    /**
     * This field is used to store automaticAlertForCategoryReq.
     */
    private boolean automaticAlertForCategoryReq = false;

    /**
     * This field is used to store underReview.
     */
    private boolean underReview = false;

    /** This is used tor render ipc from Case */
    private boolean caseFlag = false;

    /** This field is used to store crClosed status. */
    private boolean crClosed = false;

    /** This field is used to store autoPopulatedDept. */
    private boolean autoPopulatedDept = false;

    /** To hold contactHidden. */
    private String contactHidden = "plus";

    /** To hold routingHidden. */
    private String routingHidden = "plus";

    /** To hold attachmentsHidden. */
    private String attachmentsHidden = "plus";

    /** To hold letNrespHidden. */
    private String letNrespHidden = "plus";

    /** To hold alertsHidden. */
    private String alertsHidden = "plus";

    /** To hold callScriptHidden. */
    private String callScriptHidden = "plus";

    /** To hold callScriptHidden. */
    //commented for unused variables
    //private String asscCrspdHidden = "plus";
    
    /** To hold crspdProvHidden. */
    private String crspdProvHidden = "plus";
    
    /** To hold crspdMemHidden. */
    private String crspdMemHidden = "plus";

    /**
     * This is used to show audit log for details page.
     */
    private boolean auditLogRendered = false;

    private int itemsPerPage = 10;
    
    private String inputHiddenId;  
    
	/**
	 * @return Returns the inputHiddenId.
	 */
	public String getInputHiddenId() {
		return inputHiddenId;
	}
	/**
	 * @param inputHiddenId The inputHiddenId to set.
	 */
	public void setInputHiddenId(String inputHiddenId) {
		this.inputHiddenId = inputHiddenId;
	}
    public int getItemsPerPage(){
    	return itemsPerPage;
    }
    
    private AuditLog selectedAuditLog;
    
    public void setSelectedAuditLog(AuditLog auditLog){
    	this.selectedAuditLog = auditLog;
    }
    
    public AuditLog getSelectedAuditLog(){
    	return selectedAuditLog;
    }

    /**
     * This is used to show parent audit log for details page.
     */
    private boolean auditParentHistoryRender = false;

    private boolean associatePlusMinusFlag = true;

    /**
     * This is used to hold parent audit field history list for details page.
     */
    private List auditParentHistoryList = new ArrayList();

    /** Holds existingCRLists size. */
    private int existingCRListsSize;

    /** Holds businessCode. */
    //commented for unused variables
    //private String businessCode;

    /** Holds busUnit. */
    private String busUnit;

    /** Holds prevPriorityCode. */
    private String prevPriorityCode;

    /**
     * This field is used to store renderClientServices.
     */
    private boolean renderClientServices = false;

    /**
     * This field is used to store renderLangSpoken.
     */
    private boolean renderLangSpoken = false;

    /**
     * This field is used to store Dental Appointment Made By List.
     */
    private List dentalApptMadeByList;

    /**
     * This field is used to store Referred To List.
     */
    private List referredToList;

    /**
     * This field is used to store selectedList.
     */
    private List selectedList;

    /**
     * This field is used to store removedList.
     */
    private List removedList;

    /**
     * This field is used to store AssignedList.
     */
    private List assignedList;

    /**
     * This field is used to store selAllExisRecs.
     */
    private boolean selAllExisRecs = false;
    
    /**
     * This field is used to store deSelAllExisRecs.
     */
    private boolean deSelAllExisRecs = false;
    
    /**
     * This field is used to store enableSubjects.
     */
    private boolean enableSubjects = true;
    
    /**
     * This field is used to store renderShowAssocRec.
     */
    private boolean renderShowAssocRec = false;
    
    /**
     * This field is used to store disableAssocRec.
     */
    private boolean disableAssocRec = true;
    
    /** Holds variable for maintaining the index of the record selected */
    private String assocRecIndex;
    
    /**
     * This field is used to store alertValidated.
     */
    private boolean alertValidated = false;
    
    /**
     * This field is used to store routingValidated.
     */
    private boolean routingValidated = false;
    
    /**
     * This field is used to store alertSaved.
     */
    private boolean alertSaved = false;
    
    /**
     * This field is used to store routingSaved.
     */
    private boolean routingSaved = false;
    
    /**
     * This field is used to store targetId.
     */
    private String targetId;
    
    private Set crWatchList = null;
    
    private boolean auditLogFlag = false;
    
    private boolean controlRequired = true;
    
    private boolean loadUserPermissions = false;
    
    // Added following property for defect 681457
    /**
     * Used to control the conditional load of letters, when CR is saved
     */
    private boolean loadLetterData = false;
    
    /**
     * @return Returns the alertsHidden.
     */
    public String getAlertsHidden()
    {
        return alertsHidden;
    }

    /**
     * @param alertsHidden
     *            The alertsHidden to set.
     */
    public void setAlertsHidden(String alertsHidden)
    {
        this.alertsHidden = alertsHidden;
    }

    /**
     * @return Returns the asscCrspdHidden.
     *//*
    public String getAsscCrspdHidden()
    {
        return asscCrspdHidden;
    }

    *//**
     * @param asscCrspdHidden
     *            The asscCrspdHidden to set.
     *//*
    public void setAsscCrspdHidden(String asscCrspdHidden)
    {
        this.asscCrspdHidden = asscCrspdHidden;
    }*/

    /**
     * @return Returns the attachmentsHidden.
     */
    public String getAttachmentsHidden()
    {
        return attachmentsHidden;
    }

    /**
     * @param attachmentsHidden
     *            The attachmentsHidden to set.
     */
    public void setAttachmentsHidden(String attachmentsHidden)
    {
        this.attachmentsHidden = attachmentsHidden;
    }

    /**
     * @return Returns the callScriptHidden.
     */
    public String getCallScriptHidden()
    {
        return callScriptHidden;
    }

    /**
     * @param callScriptHidden
     *            The callScriptHidden to set.
     */
    public void setCallScriptHidden(String callScriptHidden)
    {
        this.callScriptHidden = callScriptHidden;
    }

    /**
     * @return Returns the contactHidden.
     */
    public String getContactHidden()
    {
        return contactHidden;
    }

    /**
     * @param contactHidden
     *            The contactHidden to set.
     */
    public void setContactHidden(String contactHidden)
    {
        this.contactHidden = contactHidden;
    }

    /**
     * @return Returns the letNrespHidden.
     */
    public String getLetNrespHidden()
    {
        return letNrespHidden;
    }

    /**
     * @param letNrespHidden
     *            The letNrespHidden to set.
     */
    public void setLetNrespHidden(String letNrespHidden)
    {
        this.letNrespHidden = letNrespHidden;
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
     * This method is used to get the correspondenceRecordVO.
     * 
     * @return CorrespondenceRecordVO : Returns the correspondenceRecordVO.
     */
    public CorrespondenceRecordVO getCorrespondenceRecordVO()
    {
        return correspondenceRecordVO;
    }

    /**
     * This method is used to set the correspondenceRecordVO.
     * 
     * @param correspondenceRecordVO :
     *            The correspondenceRecordVO to set.
     */
    public void setCorrespondenceRecordVO(
            CorrespondenceRecordVO correspondenceRecordVO)
    {
        this.correspondenceRecordVO = correspondenceRecordVO;
    }

    /**
     * This method is used to get the categoryList.
     * 
     * @return List : Returns the categoryList.
     */
    public List getCategoryList()
    {
        return categoryList;
    }

    /**
     * This method is used to set the categoryList.
     * 
     * @param categoryList :
     *            The categoryList to set.
     */
    public void setCategoryList(List categoryList)
    {
        this.categoryList = categoryList;
    }

    /**
     * This method is used to get the listOfCategoryDOs.
     * 
     * @return List : Returns the listOfCategoryDOs.
     */
    public List getListOfCategoryDOs()
    {
        return listOfCategoryDOs;
    }

    /**
     * This method is used to set the listOfCategoryDOs.
     * 
     * @param listOfCategoryDOs :
     *            The listOfCategoryDOs to set.
     */
    public void setListOfCategoryDOs(List listOfCategoryDOs)
    {
        this.listOfCategoryDOs = listOfCategoryDOs;
    }

    /**
     * This method is used to get the priorityVVList.
     * 
     * @return List : Returns the priorityVVList.
     */
    public List getPriorityVVList()
    {
        return priorityVVList;
    }

    /**
     * This method is used to set the priorityVVList.
     * 
     * @param priorityVVList :
     *            The priorityVVList to set.
     */
    public void setPriorityVVList(List priorityVVList)
    {
        this.priorityVVList = priorityVVList;
    }

    /**
     * This method is used to get the lobVVList.
     * 
     * @return List : Returns the lobVVList.
     */
    public List getLobVVList()
    {
        return lobVVList;
    }

    /**
     * This method is used to set the lobVVList.
     * 
     * @param lobVVList :
     *            The lobVVList to set.
     */
    public void setLobVVList(List lobVVList)
    {
        this.lobVVList = lobVVList;
    }

    /**
     * This method is used to get the statValidValues.
     * 
     * @return List : Returns the statValidValues.
     */
    public List getStatValidValues()
    {
        return statValidValues;
    }

    /**
     * This method is used to set the statValidValues.
     * 
     * @param statValidValues :
     *            The statValidValues to set.
     */
    public void setStatValidValues(List statValidValues)
    {
        this.statValidValues = statValidValues;
    }

    /**
     * This method is used to get the sourceVVList.
     * 
     * @return List : Returns the sourceVVList.
     */
    public List getSourceVVList()
    {
        return sourceVVList;
    }

    /**
     * This method is used to set the sourceVVList.
     * 
     * @param sourceVVList :
     *            The sourceVVList to set.
     */
    public void setSourceVVList(List sourceVVList)
    {
        this.sourceVVList = sourceVVList;
    }

    /**
     * This method is used to get the resolnVVList.
     * 
     * @return List : Returns the resolnVVList.
     */
    public List getResolnVVList()
    {
        return resolnVVList;
    }

    /**
     * This method is used to set the resolnVVList.
     * 
     * @param resolnVVList :
     *            The resolnVVList to set.
     */
    public void setResolnVVList(List resolnVVList)
    {
        this.resolnVVList = resolnVVList;
    }

    /**
     * This method is used to get the subjectValidValues.
     * 
     * @return List : Returns the subjectValidValues.
     */
    public List getSubjectValidValues()
    {
        return subjectValidValues;
    }

    /**
     * This method is used to set the subjectValidValues.
     * 
     * @param subjectValidValues :
     *            The subjectValidValues to set.
     */
    public void setSubjectValidValues(List subjectValidValues)
    {
        this.subjectValidValues = subjectValidValues;
    }

    /**
     * This method is used to get the departmentsList.
     * 
     * @return List : Returns the departmentsList.
     */
    public List getDepartmentsList()
    {
        return departmentsList;
    }

    /**
     * This method is used to set the departmentsList.
     * 
     * @param departmentsList :
     *            The departmentsList to set.
     */
    public void setDepartmentsList(List departmentsList)
    {
        this.departmentsList = departmentsList;
    }

    /**
     * This method is used to get the categorySubjectValues.
     * 
     * @return List : Returns the categorySubjectValues.
     */
    public List getCategorySubjectValues()
    {
        return categorySubjectValues;
    }

    /**
     * This method is used to set the categorySubjectValues.
     * 
     * @param categorySubjectValues :
     *            The categorySubjectValues to set.
     */
    public void setCategorySubjectValues(List categorySubjectValues)
    {
        this.categorySubjectValues = categorySubjectValues;
    }

    /**
     * This method is used to get the listOfContacts.
     * 
     * @return List : Returns the listOfContacts.
     */
    public List getListOfContacts()
    {
        return listOfContacts;
    }

    /**
     * This method is used to set the listOfContacts.
     * 
     * @param listOfEntityCommonContacts :
     *            The listOfEntityCommonContacts to set.
     */
    public void setListOfContacts(List listOfEntityCommonContacts)
    {
        this.listOfContacts = listOfEntityCommonContacts;
    }

    /**
     * This method is used to get the listOfAlternateIds.
     * 
     * @return List : Returns the listOfAlternateIds.
     */
    public List getListOfAlternateIds()
    {
        return listOfAlternateIds;
    }

    /**
     * This method is used to set the listOfAlternateIds.
     * 
     * @param listOfAlternateIds :
     *            The listOfAlternateIds to set.
     */
    public void setListOfAlternateIds(List listOfAlternateIds)
    {
        this.listOfAlternateIds = listOfAlternateIds;
    }

    /**
     * This method is used to get the assocCRList.
     * 
     * @return List : Returns the assocCRList.
     */
    public List getAssocCRList()
    {
        return assocCRList;
    }

    /**
     * This method is used to set the assocCRList.
     * 
     * @param assocCRList :
     *            The assocCRList to set.
     */
    public void setAssocCRList(List assocCRList)
    {
        this.assocCRList = assocCRList;
    }

    /**
     * This method is used to get the existCRList.
     * 
     * @return List : Returns the existCRList.
     */
    public List getExistCRList()
    {
        return existCRList;
    }

    /**
     * This method is used to set the existCRList.
     * 
     * @param existCRList :
     *            The existCRList to set.
     */
    public void setExistCRList(List existCRList)
    {
        this.existCRList = existCRList;
    }

    /**
     * This method is used to get the correspondenceForVO.
     * 
     * @return CorrespondenceForVO : Returns the correspondenceForVO.
     */
    public CorrespondenceForVO getCorrespondenceForVO()
    {
        return correspondenceForVO;
    }

    /**
     * This method is used to set the correspondenceForVO.
     * 
     * @param correspondenceForVO :
     *            The correspondenceForVO to set.
     */
    public void setCorrespondenceForVO(CorrespondenceForVO correspondenceForVO)
    {
        this.correspondenceForVO = correspondenceForVO;
    }

    /**
     * This method is used to get the correspondenceForMemberVO.
     * 
     * @return CorrespondenceForMemberVO : Returns the
     *         correspondenceForMemberVO.
     */
    public CorrespondenceForMemberVO getCorrespondenceForMemberVO()
    {
        return correspondenceForMemberVO;
    }

    /**
     * This method is used to set the correspondenceForMemberVO.
     * 
     * @param correspondenceForMemberVO :
     *            The correspondenceForMemberVO to set.
     */
    public void setCorrespondenceForMemberVO(
            CorrespondenceForMemberVO correspondenceForMemberVO)
    {
        this.correspondenceForMemberVO = correspondenceForMemberVO;
    }

    /**
     * This method is used to get the correspondenceForProviderVO.
     * 
     * @return CorrespondenceForProviderVO : Returns the
     *         correspondenceForProviderVO.
     */
    public CorrespondenceForProviderVO getCorrespondenceForProviderVO()
    {
        return correspondenceForProviderVO;
    }

    /**
     * This method is used to set the correspondenceForProviderVO.
     * 
     * @param correspondenceForProviderVO :
     *            The correspondenceForProviderVO to set.
     */
    public void setCorrespondenceForProviderVO(
            CorrespondenceForProviderVO correspondenceForProviderVO)
    {
        this.correspondenceForProviderVO = correspondenceForProviderVO;
    }

    /**
     * This method is used to get the renderInqAbtForVO.
     * 
     * @return boolean : Returns the renderInqAbtForVO.
     */
    public boolean isRenderInqAbtForVO()
    {
        return renderInqAbtForVO;
    }

    /**
     * This method is used to set the renderInqAbtForVO.
     * 
     * @param renderInqAbtForVO :
     *            The renderInqAbtForVO to set.
     */
    public void setRenderInqAbtForVO(boolean renderInqAbtForVO)
    {
        this.renderInqAbtForVO = renderInqAbtForVO;
    }

    /**
     * This method is used to get the renderInqAbtForMemberVO.
     * 
     * @return boolean : Returns the renderInqAbtForMemberVO.
     */
    public boolean isRenderInqAbtForMemberVO()
    {
        return renderInqAbtForMemberVO;
    }

    /**
     * This method is used to set the renderInqAbtForMemberVO.
     * 
     * @param renderInqAbtForMemberVO :
     *            The renderInqAbtForMemberVO to set.
     */
    public void setRenderInqAbtForMemberVO(boolean renderInqAbtForMemberVO)
    {
        this.renderInqAbtForMemberVO = renderInqAbtForMemberVO;
    }

    /**
     * This method is used to get the renderInqAbtForProviderVO.
     * 
     * @return boolean : Returns the renderInqAbtForProviderVO.
     */
    public boolean isRenderInqAbtForProviderVO()
    {
        return renderInqAbtForProviderVO;
    }

    /**
     * This method is used to set the renderInqAbtForProviderVO.
     * 
     * @param renderInqAbtForProviderVO :
     *            The renderInqAbtForProviderVO to set.
     */
    public void setRenderInqAbtForProviderVO(boolean renderInqAbtForProviderVO)
    {
        this.renderInqAbtForProviderVO = renderInqAbtForProviderVO;
    }

    /**
     * This method is used to get the inquiringAboutIndex.
     * 
     * @return int : Returns the inquiringAboutIndex.
     */
    public int getInquiringAboutIndex()
    {
        return inquiringAboutIndex;
    }

    /**
     * This method is used to set the inquiringAboutIndex.
     * 
     * @param inquiringAboutIndex :
     *            The inquiringAboutIndex to set.
     */
    public void setInquiringAboutIndex(int inquiringAboutIndex)
    {
        this.inquiringAboutIndex = inquiringAboutIndex;
    }

    /**
     * This method is used to get the imageRender.
     * 
     * @return String : Returns the imageRender.
     */
    public String getImageRender()
    {
        return imageRender;
    }

    /**
     * This method is used to set the imageRender.
     * 
     * @param imageRender :
     *            The imageRender to set.
     */
    public void setImageRender(String imageRender)
    {
        this.imageRender = imageRender;
    }

    /**
     * This method is used to get the renderCrspdForVO.
     * 
     * @return boolean : Returns the renderCrspdForVO.
     */
    public boolean isRenderCrspdForVO()
    {
        return renderCrspdForVO;
    }

    /**
     * This method is used to set the renderCrspdForVO.
     * 
     * @param renderCrspdForVO :
     *            The renderCrspdForVO to set.
     */
    public void setRenderCrspdForVO(boolean renderCrspdForVO)
    {
        this.renderCrspdForVO = renderCrspdForVO;
    }

    /**
     * This method is used to get the renderCrspdProviderForVO.
     * 
     * @return boolean : Returns the renderCrspdProviderForVO.
     */
    public boolean isRenderCrspdProviderForVO()
    {
        return renderCrspdProviderForVO;
    }

    /**
     * This method is used to set the renderCrspdProviderForVO.
     * 
     * @param renderCrspdProviderForVO :
     *            The renderCrspdProviderForVO to set.
     */
    public void setRenderCrspdProviderForVO(boolean renderCrspdProviderForVO)
    {
        this.renderCrspdProviderForVO = renderCrspdProviderForVO;
    }

    /**
     * This method is used to get the renderCrspdMemberForVO.
     * 
     * @return boolean : Returns the renderCrspdMemberForVO.
     */
    public boolean isRenderCrspdMemberForVO()
    {
        return renderCrspdMemberForVO;
    }

    /**
     * This method is used to set the renderCrspdMemberForVO.
     * 
     * @param renderCrspdMemberForVO :
     *            The renderCrspdMemberForVO to set.
     */
    public void setRenderCrspdMemberForVO(boolean renderCrspdMemberForVO)
    {
        this.renderCrspdMemberForVO = renderCrspdMemberForVO;
    }

    /**
     * This method is used to get the noDataExistCR.
     * 
     * @return boolean : Returns the noDataExistCR.
     */
    public boolean isNoDataExistCR()
    {
        return noDataExistCR;
    }

    /**
     * This method is used to set the noDataExistCR.
     * 
     * @param noDataExistCR :
     *            The noDataExistCR to set.
     */
    public void setNoDataExistCR(boolean noDataExistCR)
    {
        this.noDataExistCR = noDataExistCR;
    }

    /**
     * This method is used to get the noDataAssocCR.
     * 
     * @return boolean : Returns the noDataAssocCR.
     */
    public boolean isNoDataAssocCR()
    {
        return noDataAssocCR;
    }

    /**
     * This method is used to set the noDataAssocCR.
     * 
     * @param noDataAssocCR :
     *            The noDataAssocCR to set.
     */
    public void setNoDataAssocCR(boolean noDataAssocCR)
    {
        this.noDataAssocCR = noDataAssocCR;
    }

    /**
     * This method is used to get the searchForCR.
     * 
     * @return boolean : Returns the searchForCR.
     */
    public boolean isSearchForCR()
    {
        return searchForCR;
    }

    /**
     * This method is used to set the searchForCR.
     * 
     * @param searchForCR :
     *            The searchForCR to set.
     */
    public void setSearchForCR(boolean searchForCR)
    {
        this.searchForCR = searchForCR;
    }

    /**
     * This method used for setting error display messages.
     * 
     * @param errorName :
     *            String errorName.
     * @param arguments :
     *            Array of Object. Parameters to be passed to the message
     * @param messageBundle :
     *            String messageBundle.
     * @param componentId :
     *            String componentId.
     */
    private void setErrorMessage(String errorName, Object[] arguments,
            String messageBundle, String componentId)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        Locale locale = root.getLocale();
        String clientId = null;

        facesContext.getApplication().setMessageBundle(messageBundle);
        String errorMsg = MessageUtil.format(messageBundle, errorName,
                arguments, locale);
        FacesMessage fc = new FacesMessage();
        fc.setDetail(errorMsg);

        if (componentId != null)
        {
            UIComponent uiComponent = findComponentInRoot(componentId);
            clientId = uiComponent.getClientId(facesContext);
        }

        facesContext.addMessage(clientId, fc);
    }

    /**
     * This operation is used to find the component by passing id.
     * 
     * @param base :
     *            View root component of the jsp.
     * @param id :
     *            Id of the component from jsp.
     * @return UIComponent object.
     */
    public UIComponent findComponent(UIComponent base, String id)
    {
        // Is the "base" component itself the match we are looking for?
        if (id.equals(base.getId()))
        {
            return base;
        }

        // Search through our facets and children
        UIComponent component = null;
        UIComponent result = null;
        Iterator cmpIterator = base.getFacetsAndChildren();

        while (cmpIterator.hasNext() && (result == null))
        {
            component = (UIComponent) cmpIterator.next();
            if (id.equals(component.getId()))
            {
                result = component;
                break;
            }
            result = findComponent(component, id);
            if (result != null)
            {
                break;
            }
        }
        return result;
    }

    /**
     * This operation is used to find the component in root by passing id.
     * 
     * @param id :
     *            String object.
     * @return UIComponent : UIComponent object.
     */
    public UIComponent findComponentInRoot(String id)
    {
        UIComponent component = null;
        FacesContext context = FacesContext.getCurrentInstance();

        if (context != null)
        {
            UIComponent root = context.getViewRoot();
            component = findComponent(root, id);
        }
        return component;
    }

    /**
     * This method is used to get the updateMode.
     * 
     * @return boolean : Returns the updateMode.
     */
    public boolean isUpdateMode()
    {
        return updateMode;
    }

    /**
     * This method is used to set the updateMode.
     * 
     * @param updateMode :
     *            The updateMode to set.
     */
    public void setUpdateMode(boolean updateMode)
    {
        this.updateMode = updateMode;
    }

    /**
     * This method is used to get the associatedCorrespondenceVO.
     * 
     * @return AssociatedCorrespondenceVO : Returns the
     *         associatedCorrespondenceVO.
     */
    public AssociatedCorrespondenceVO getAssociatedCorrespondenceVO()
    {
        return associatedCorrespondenceVO;
    }

    /**
     * This method is used to set the associatedCorrespondenceVO.
     * 
     * @param associatedCorrespondenceVO :
     *            The associatedCorrespondenceVO to set.
     */
    public void setAssociatedCorrespondenceVO(
            AssociatedCorrespondenceVO associatedCorrespondenceVO)
    {
        this.associatedCorrespondenceVO = associatedCorrespondenceVO;
    }

    /**
     * This method is used to get the showResultsDiv.
     * 
     * @return boolean : Returns the showResultsDiv.
     */
    public boolean isShowResultsDiv()
    {
        return showResultsDiv;
    }

    /**
     * This method is used to set the showResultsDiv.
     * 
     * @param showResultsDiv :
     *            The showResultsDiv to set.
     */
    public void setShowResultsDiv(boolean showResultsDiv)
    {
        this.showResultsDiv = showResultsDiv;
    }

    /**
     * This method is used to get the existCR.
     * 
     * @return boolean : Returns the existCR.
     */
    public boolean isExistCR()
    {
        return existCR;
    }

    /**
     * This method is used to set the existCR.
     * 
     * @param existCR :
     *            The existCR to set.
     */
    public void setExistCR(boolean existCR)
    {
        this.existCR = existCR;
    }

    /**
     * This method is used to get the linkToCR.
     * 
     * @return boolean : Returns the linkToCR.
     */
    public boolean isLinkToCR()
    {
        return linkToCR;
    }

    /**
     * This method is used to set the linkToCR.
     * 
     * @param linkToCR :
     *            The linkToCR to set.
     */
    public void setLinkToCR(boolean linkToCR)
    {
        this.linkToCR = linkToCR;
    }

    /**
     * This method is used to get the crAssignedTo.
     * 
     * @return boolean : Returns the crAssignedTo.
     */
    public boolean isCrAssignedTo()
    {
        return crAssignedTo;
    }

    /**
     * This method is used to set the crAssignedTo.
     * 
     * @param crAssignedTo :
     *            The crAssignedTo to set.
     */
    public void setCrAssignedTo(boolean crAssignedTo)
    {
        this.crAssignedTo = crAssignedTo;
    }

    /**
     * This method is used to get the mapOfDeptAndLobHier.
     * 
     * @return Map : Returns the mapOfDeptAndLobHier.
     */
    public Map getMapOfDeptAndLobHier()
    {
        return mapOfDeptAndLobHier;
    }

    /**
     * This method is used to set the mapOfDeptAndLobHier.
     * 
     * @param mapOfDeptAndLOBHier :
     *            The mapOfDeptAndLOBHier to set.
     */
    public void setMapOfDeptAndLobHier(Map mapOfDeptAndLOBHier)
    {
        this.mapOfDeptAndLobHier = mapOfDeptAndLOBHier;
    }

    /**
     * This method is used to get the crNotesList.
     * 
     * @return List : Returns the crNotesList.
     */
    public List getCrNotesList()
    {
        return crNotesList;
    }

    /**
     * This method is used to set the crNotesList.
     * 
     * @param crNotesList :
     *            The crNotesList to set.
     */
    public void setCrNotesList(List crNotesList)
    {
        this.crNotesList = crNotesList;
    }

    /**
     * This method is used to get the crContactList.
     * 
     * @return List : Returns the crContactList.
     */
    public List getCrContactList()
    {
        return crContactList;
    }

    /**
     * This method is used to set the crContactList.
     * 
     * @param crContactList :
     *            The crContactList to set.
     */
    public void setCrContactList(List crContactList)
    {
        this.crContactList = crContactList;
    }

    /**
     * This method is used to get the crContactTypeList.
     * 
     * @return List : Returns the crContactTypeList.
     */
    public List getCrContactTypeList()
    {
        return crContactTypeList;
    }

    /**
     * This method is used to set the crContactTypeList.
     * 
     * @param crContactTypeList :
     *            The crContactTypeList to set.
     */
    public void setCrContactTypeList(List crContactTypeList)
    {
        this.crContactTypeList = crContactTypeList;
    }

    /**
     * This method is used to get the automaticAlertForUserReq.
     * 
     * @return boolean : Returns the automaticAlertForUserReq.
     */
    public boolean isAutomaticAlertForUserReq()
    {
        return automaticAlertForUserReq;
    }

    /**
     * This method is used to set the automaticAlertForUserReq.
     * 
     * @param automaticAlertForUserReq :
     *            The automaticAlertForUserReq to set.
     */
    public void setAutomaticAlertForUserReq(boolean automaticAlertForUserReq)
    {
        this.automaticAlertForUserReq = automaticAlertForUserReq;
    }

    /**
     * This method is used to get the automaticAlertForCategoryReq.
     * 
     * @return boolean : Returns the automaticAlertForCategoryReq.
     */
    public boolean isAutomaticAlertForCategoryReq()
    {
        return automaticAlertForCategoryReq;
    }

    /**
     * This method is used to set the automaticAlertForCategoryReq.
     * 
     * @param automaticAlertForCategoryReq :
     *            The automaticAlertForCategoryReq to set.
     */
    public void setAutomaticAlertForCategoryReq(
            boolean automaticAlertForCategoryReq)
    {
        this.automaticAlertForCategoryReq = automaticAlertForCategoryReq;
    }

    /**
     * This method is used to get the underReview.
     * 
     * @return boolean : Returns the underReview.
     */
    public boolean isUnderReview()
    {
        return underReview;
    }

    /**
     * This method is used to set the underReview.
     * 
     * @param underReview :
     *            The underReview to set.
     */
    public void setUnderReview(boolean underReview)
    {
        this.underReview = underReview;
    }

    /**
     * This method is used to get the crClosed.
     * 
     * @return boolean : Returns the crClosed.
     */
    public boolean isCrClosed()
    {
        return crClosed;
    }

    /**
     * This method is used to set the crClosed.
     * 
     * @param crClosed :
     *            The crClosed to set.
     */
    public void setCrClosed(boolean crClosed)
    {
        this.crClosed = crClosed;
    }

    /**
     * This method is used to get the autoPopulatedDept.
     * 
     * @return boolean : Returns the autoPopulatedDept.
     */
    public boolean isAutoPopulatedDept()
    {
        return autoPopulatedDept;
    }

    /**
     * This method is used to set the autoPopulatedDept.
     * 
     * @param autoPopulatedDept :
     *            The autoPopulatedDept to set.
     */
    public void setAutoPopulatedDept(boolean autoPopulatedDept)
    {
        this.autoPopulatedDept = autoPopulatedDept;
    }

    /**
     * @return Returns the caseFlag.
     */
    public boolean isCaseFlag()
    {
        return caseFlag;
    }

    /**
     * @param caseFlag
     *            The caseFlag to set.
     */
    public void setCaseFlag(boolean caseFlag)
    {
        this.caseFlag = caseFlag;
    }

    /**
     * @return Returns the auditLogRendered.
     */
    public boolean isAuditLogRendered()
    {
        return auditLogRendered;
    }

    /**
     * @param auditLogRendered
     *            The auditLogRendered to set.
     */
    public void setAuditLogRendered(boolean auditLogRendered)
    {
        this.auditLogRendered = auditLogRendered;
    }

    /**
     * @return Returns the auditParentHistoryList.
     */
    public List getAuditParentHistoryList()
    {
        return auditParentHistoryList;
    }

    /**
     * @param auditParentHistoryList
     *            The auditParentHistoryList to set.
     */
    public void setAuditParentHistoryList(List auditParentHistoryList)
    {
        this.auditParentHistoryList = auditParentHistoryList;
    }

    /**
     * @return Returns the auditParentHistoryRender.
     */
    public boolean isAuditParentHistoryRender()
    {
        return auditParentHistoryRender;
    }

    /**
     * @param auditParentHistoryRender
     *            The auditParentHistoryRender to set.
     */
    public void setAuditParentHistoryRender(boolean auditParentHistoryRender)
    {
        this.auditParentHistoryRender = auditParentHistoryRender;
    }

    /**
     * @return Returns the formElements.
     */
    /* Heap Dump issue fix
     * public HtmlPanelGrid getFormElements()
    {
        return formElements;
    }

    *//**
     * @param formElements
     *            The formElements to set.
     *//*
    public void setFormElements(HtmlPanelGrid formElements)
    {
        this.formElements = formElements;
    }*/

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
     * @return Returns the renderCustomFlds.
     */
    public boolean isRenderCustomFlds()
    {
        return renderCustomFlds;
    }

    /**
     * @param renderCustomFlds
     *            The renderCustomFlds to set.
     */
    public void setRenderCustomFlds(boolean renderCustomFlds)
    {
        this.renderCustomFlds = renderCustomFlds;
    }

    /**
     * @return Returns the renderCrspdUnEnrolledProviderForVO.
     */
    public boolean isRenderCrspdUnEnrolledProviderForVO()
    {
        return renderCrspdUnEnrolledProviderForVO;
    }

    /**
     * @param renderCrspdUnEnrolledProviderForVO
     *            The renderCrspdUnEnrolledProviderForVO to set.
     */
    public void setRenderCrspdUnEnrolledProviderForVO(
            boolean renderCrspdUnEnrolledProviderForVO)
    {
        this.renderCrspdUnEnrolledProviderForVO = renderCrspdUnEnrolledProviderForVO;
    }

    /**
     * @return Returns the renderCrspdUnEnrolledMemberForVO.
     */
    public boolean isRenderCrspdUnEnrolledMemberForVO()
    {
        return renderCrspdUnEnrolledMemberForVO;
    }

    /**
     * @param renderCrspdUnEnrolledMemberForVO
     *            The renderCrspdUnEnrolledMemberForVO to set.
     */
    public void setRenderCrspdUnEnrolledMemberForVO(
            boolean renderCrspdUnEnrolledMemberForVO)
    {
        this.renderCrspdUnEnrolledMemberForVO = renderCrspdUnEnrolledMemberForVO;
    }

    /**
     * @return Returns the renderDaystoCloseFlag.
     */
    public boolean isRenderDaystoCloseFlag()
    {
        return renderDaystoCloseFlag;
    }

    /**
     * @param renderDaystoCloseFlag
     *            The renderDaystoCloseFlag to set.
     */
    public void setRenderDaystoCloseFlag(boolean renderDaystoCloseFlag)
    {
        this.renderDaystoCloseFlag = renderDaystoCloseFlag;
    }

    /**
     * @return Returns the renderCrspdTPLForVO.
     */
    public boolean isRenderCrspdTPLForVO()
    {
        return renderCrspdTPLForVO;
    }

    /**
     * @param renderCrspdTPLForVO
     *            The renderCrspdTPLForVO to set.
     */
    public void setRenderCrspdTPLForVO(boolean renderCrspdTPLForVO)
    {
        this.renderCrspdTPLForVO = renderCrspdTPLForVO;
    }

    /**
     * @return Returns the contactList.
     */
    public List getContactList()
    {
        return contactList;
    }

    /**
     * @param contactList
     *            The contactList to set.
     */
    public void setContactList(List contactList)
    {
        this.contactList = contactList;
    }

    /**
     * @return Returns the renderDaysOpenFlag.
     */
    public boolean isRenderDaysOpenFlag()
    {
        return renderDaysOpenFlag;
    }

    /**
     * @param renderDaysOpenFlag
     *            The renderDaysOpenFlag to set.
     */
    public void setRenderDaysOpenFlag(boolean renderDaysOpenFlag)
    {
        this.renderDaysOpenFlag = renderDaysOpenFlag;
    }

    /**
     * @return Returns the existingCRListsSize.
     */
    public int getExistingCRListsSize()
    {
        return existingCRListsSize;
    }

    /**
     * @param existingCRListsSize
     *            The existingCRListsSize to set.
     */
    public void setExistingCRListsSize(int existingCRListsSize)
    {
        this.existingCRListsSize = existingCRListsSize;
    }

    /**
     * @return Returns the businessCode.
     *//*
    public String getBusinessCode()
    {
        return businessCode;
    }

    *//**
     * @param businessCode
     *            The businessCode to set.
     *//*
    public void setBusinessCode(String businessCode)
    {
        this.businessCode = businessCode;
    }
*/
    /**
     * @return Returns the busUnit.
     */
    public String getBusUnit()
    {
        return busUnit;
    }

    /**
     * @param busUnit
     *            The busUnit to set.
     */
    public void setBusUnit(String busUnit)
    {
        this.busUnit = busUnit;
    }

    /**
     * @return Returns the renderClientServices.
     */
    public boolean isRenderClientServices()
    {
        return renderClientServices;
    }

    /**
     * @param renderClientServices
     *            The renderClientServices to set.
     */
    public void setRenderClientServices(boolean renderClientServices)
    {
        this.renderClientServices = renderClientServices;
    }

    /**
     * @return Returns the dentalApptMadeByList.
     */
    public List getDentalApptMadeByList()
    {
        return dentalApptMadeByList;
    }

    /**
     * @param dentalApptMadeByList
     *            The dentalApptMadeByList to set.
     */
    public void setDentalApptMadeByList(List dentalApptMadeByList)
    {
        this.dentalApptMadeByList = dentalApptMadeByList;
    }

    /**
     * @return Returns the referredToList.
     */
    public List getReferredToList()
    {
        return referredToList;
    }

    /**
     * @param referredToList
     *            The referredToList to set.
     */
    public void setReferredToList(List referredToList)
    {
        this.referredToList = referredToList;
    }

    /**
     * @return Returns the removedList.
     */
    public List getRemovedList()
    {
        return removedList;
    }

    /**
     * @param removedList
     *            The removedList to set.
     */
    public void setRemovedList(List removedList)
    {
        this.removedList = removedList;
    }

    /**
     * @return Returns the selectedList.
     */
    public List getSelectedList()
    {
        return selectedList;
    }

    /**
     * @param selectedList
     *            The selectedList to set.
     */
    public void setSelectedList(List selectedList)
    {
        this.selectedList = selectedList;
    }

    /**
     * @return Returns the assignedList.
     */
    public List getAssignedList()
    {
        return assignedList;
    }

    /**
     * @param assignedList
     *            The assignedList to set.
     */
    public void setAssignedList(List assignedList)
    {
        this.assignedList = assignedList;
    }

    /**
     * @return Returns the renderLangSpoken.
     */
    public boolean isRenderLangSpoken()
    {
        return renderLangSpoken;
    }

    /**
     * @param renderLangSpoken The renderLangSpoken to set.
     */
    public void setRenderLangSpoken(boolean renderLangSpoken)
    {
        this.renderLangSpoken = renderLangSpoken;
    }

    /**
     * @return Returns the prevPriorityCode.
     */
    public String getPrevPriorityCode()
    {
        return prevPriorityCode;
    }

    /**
     * @param prevPriorityCode The prevPriorityCode to set.
     */
    public void setPrevPriorityCode(String prevPriorityCode)
    {
        this.prevPriorityCode = prevPriorityCode;
    }
    
    /**
     * @return Returns the deSelAllExisRecs.
     */
    public boolean isDeSelAllExisRecs()
    {
        return deSelAllExisRecs;
    }
    /**
     * @param deSelAllExisRecs The deSelAllExisRecs to set.
     */
    public void setDeSelAllExisRecs(boolean deSelAllExisRecs)
    {
        this.deSelAllExisRecs = deSelAllExisRecs;
    }
    /**
     * @return Returns the selAllExisRecs.
     */
    public boolean isSelAllExisRecs()
    {
        return selAllExisRecs;
    }
    /**
     * @param selAllExisRecs The selAllExisRecs to set.
     */
    public void setSelAllExisRecs(boolean selAllExisRecs)
    {
        this.selAllExisRecs = selAllExisRecs;
    }
    /**
     * @return Returns the crNotesSetTempList.
     */
    public List getCrNotesSetTempList()
    {
        return crNotesSetTempList;
    }
    /**
     * @param crNotesSetTempList The crNotesSetTempList to set.
     */
    public void setCrNotesSetTempList(List crNotesSetTempList)
    {
        this.crNotesSetTempList = crNotesSetTempList;
    }
    /**
     * 
     */
    private String userID;
    
    private Long userSk;
	/**
	 * @return Returns the userID.
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID The userID to set.
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * versionMap
	 */
	private Map versionMap;
	/**
	 * @return Returns the versionMap.
	 */
	public Map getVersionMap() {
		return versionMap;
	}
	/**
	 * @param versionMap The versionMap to set.
	 */
	public void setVersionMap(Map versionMap) {
		this.versionMap = versionMap;
	}
	/**
	 * @return Returns the enableSubjects.
	 */
	public boolean isEnableSubjects() {
		return enableSubjects;
	}
	/**
	 * @param enableSubjects The enableSubjects to set.
	 */
	public void setEnableSubjects(boolean enableSubjects) {
		this.enableSubjects = enableSubjects;
	}

	public boolean isMessageFlag() {
		return MessageFlag;
	}

	public void setMessageFlag(boolean messageFlag) {
		MessageFlag = messageFlag;
	}
	
	/**
	 * @return Returns the associatePlusMinusFlag.
	 */
	public boolean isAssociatePlusMinusFlag() {
		return associatePlusMinusFlag;
	}
	/**
	 * @param associatePlusMinusFlag The associatePlusMinusFlag to set.
	 */
	public void setAssociatePlusMinusFlag(boolean associatePlusMinusFlag) {
		this.associatePlusMinusFlag = associatePlusMinusFlag;
	}
	
	/**
	 * @return Returns the renderShowAssocRec.
	 */
	public boolean isRenderShowAssocRec() {
		return renderShowAssocRec;
	}
	/**
	 * @param renderShowAssocRec The renderShowAssocRec to set.
	 */
	public void setRenderShowAssocRec(boolean renderShowAssocRec) {
		this.renderShowAssocRec = renderShowAssocRec;
	}
	/**
	 * @return Returns the disableAssocRec.
	 */
	public boolean isDisableAssocRec() {
		return disableAssocRec;
	}
	/**
	 * @param disableAssocRec The disableAssocRec to set.
	 */
	public void setDisableAssocRec(boolean disableAssocRec) {
		this.disableAssocRec = disableAssocRec;
	}
	/**
	 * @return Returns the assocRecIndex.
	 */
	public String getAssocRecIndex() {
		return assocRecIndex;
	}
	/**
	 * @param assocRecIndex The assocRecIndex to set.
	 */
	public void setAssocRecIndex(String assocRecIndex) {
		this.assocRecIndex = assocRecIndex;
	}
	
	/**
	 * @return Returns the alertSaved.
	 */
	public boolean isAlertSaved() {
		return alertSaved;
	}
	/**
	 * @param alertSaved The alertSaved to set.
	 */
	public void setAlertSaved(boolean alertSaved) {
		this.alertSaved = alertSaved;
	}
	/**
	 * @return Returns the routingSaved.
	 */
	public boolean isRoutingSaved() {
		return routingSaved;
	}
	/**
	 * @param routingSaved The routingSaved to set.
	 */
	public void setRoutingSaved(boolean routingSaved) {
		this.routingSaved = routingSaved;
	}
	/**
	 * @return Returns the alertValidated.
	 */
	public boolean isAlertValidated() {
		return alertValidated;
	}
	/**
	 * @param alertValidated The alertValidated to set.
	 */
	public void setAlertValidated(boolean alertValidated) {
		this.alertValidated = alertValidated;
	}
	/**
	 * @return Returns the routingValidated.
	 */
	public boolean isRoutingValidated() {
		return routingValidated;
	}
	/**
	 * @param routingValidated The routingValidated to set.
	 */
	public void setRoutingValidated(boolean routingValidated) {
		this.routingValidated = routingValidated;
	}
	//commented for unused variables
	//private String catRefresh=""; 

	/**
	 * @return Returns the isCatRefresh.
	 */
	
	/**
	 * @return Returns the catRefresh.
	 *//*
	public String getCatRefresh() {
		return catRefresh;
	}
	*//**
	 * @param catRefresh The catRefresh to set.
	 *//*
	public void setCatRefresh(String catRefresh) {
		this.catRefresh = catRefresh;
	}*/
	
	/**
	 * @param listOfAssoCrsToDelete.
	 */
	private List listOfAssoCrsToDelete = new ArrayList();
	/**
	 * @return Returns the listOfAssoCrsToDelete.
	 */
	public List getListOfAssoCrsToDelete() {
		return listOfAssoCrsToDelete;
	}
	/**
	 * @param listOfAssoCrsToDelete The listOfAssoCrsToDelete to set.
	 */
	public void setListOfAssoCrsToDelete(List listOfAssoCrsToDelete) {
		this.listOfAssoCrsToDelete = listOfAssoCrsToDelete;
	}
	
	/**
	 * @return Returns the targetId.
	 */
	public String getTargetId() {
		return targetId;
	}
	/**
	 * @param targetId The targetId to set.
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	private boolean crSaved;
	/**
	 * @return Returns the crSaved.
	 */
	public boolean isCrSaved() {
		return crSaved;
	}
	/**
	 * @param crSaved The crSaved to set.
	 */
	public void setCrSaved(boolean crSaved) {
		this.crSaved = crSaved;
	}
	
	private boolean srchDocRepSecurity  = false;
	/**
	 * @return Returns the srchDocRepSecurity.
	 */
	public boolean isSrchDocRepSecurity() {
		return srchDocRepSecurity;
	}
	/**
	 * @param srchDocRepSecurity The srchDocRepSecurity to set.
	 */
	public void setSrchDocRepSecurity(boolean srchDocRepSecurity) {
		this.srchDocRepSecurity = srchDocRepSecurity;
	}
	
	//commented for unused variables
	//private boolean exstSelAlFlag = false;
	/**
	 * @return Returns the exstSelAlFlag.
	 *//*
	public boolean isExstSelAlFlag() {
		return exstSelAlFlag;
	}
	*//**
	 * @param exstSelAlFlag The exstSelAlFlag to set.
	 *//*
	public void setExstSelAlFlag(boolean exstSelAlFlag) {
		this.exstSelAlFlag = exstSelAlFlag;
	}*/
	
	//commented for unused variables
	//private boolean extDeselAllFlag = true;
	/**
	 * @return Returns the extDeselAllFlag.
	 *//*
	public boolean isExtDeselAllFlag() {
		return extDeselAllFlag;
	}
	*//**
	 * @param extDeselAllFlag The extDeselAllFlag to set.
	 *//*
	public void setExtDeselAllFlag(boolean extDeselAllFlag) {
		this.extDeselAllFlag = extDeselAllFlag;
	}
*/	
	/**
	 * @return Returns the referredToVOList.
	 */
	public List getReferredToVOList() {
		return referredToVOList;
	}
	/**
	 * @param referredToVOList The referredToVOList to set.
	 */
	public void setReferredToVOList(List referredToVOList) {
		this.referredToVOList = referredToVOList;
	}
	/**
	 * @return Returns the renderSearchCRPage.
	 */
	public boolean isRenderSearchCRPage() {
		return renderSearchCRPage;
	}
	/**
	 * @param renderSearchCRPage The renderSearchCRPage to set.
	 */
	public void setRenderSearchCRPage(boolean renderSearchCRPage) {
		this.renderSearchCRPage = renderSearchCRPage;
	}
	/**
	 * @return Returns the correspondenceForInquiry.
	 */
	public boolean isCorrespondenceForInquiry() {
		return correspondenceForInquiry;
	}
	/**
	 * @param correspondenceForInquiry The correspondenceForInquiry to set.
	 */
	public void setCorrespondenceForInquiry(boolean correspondenceForInquiry) {
		this.correspondenceForInquiry = correspondenceForInquiry;
	}
	/**
	 * @return Returns the crspdProvHidden.
	 */
	public String getCrspdProvHidden() {
		return crspdProvHidden;
	}
	/**
	 * @param crspdProvHidden The crspdProvHidden to set.
	 */
	public void setCrspdProvHidden(String crspdProvHidden) {
		this.crspdProvHidden = crspdProvHidden;
	}
	/**
	 * @return Returns the crspdMemHidden.
	 */
	public String getCrspdMemHidden() {
		return crspdMemHidden;
	}
	/**
	 * @param crspdMemHidden The crspdMemHidden to set.
	 */
	public void setCrspdMemHidden(String crspdMemHidden) {
		this.crspdMemHidden = crspdMemHidden;
	}
	/**
	 * @return Returns the crWatchList.
	 */
	public Set getCrWatchList() {
		return crWatchList;
	}
	/**
	 * @param crWatchList The crWatchList to set.
	 */
	public void setCrWatchList(Set crWatchList) {
		this.crWatchList = crWatchList;
	}
	
//	CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010

	private boolean tplPolicyHolder= false;

/**
 * @return Returns the tplPolicyHolder.
 */
public boolean isTplPolicyHolder() {
	return tplPolicyHolder;
}
/**
 * @param tplPolicyHolder The tplPolicyHolder to set.
 */
public void setTplPolicyHolder(boolean tplPolicyHolder) {
	this.tplPolicyHolder = tplPolicyHolder;
}
//EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010

	/**
	 * @return Returns the auditLogFlag.
	 */
	public boolean isAuditLogFlag() {
		return auditLogFlag;
	}
	/**
	 * @param auditLogFlag The auditLogFlag to set.
	 */
	public void setAuditLogFlag(boolean auditLogFlag) {
		this.auditLogFlag = auditLogFlag;
	}
	/**
	 * @return Returns the userSk.
	 */
	public Long getUserSk() {
		return userSk;
	}
	/**
	 * @param userSk The userSk to set.
	 */
	public void setUserSk(Long userSk) {
		this.userSk = userSk;
	}
	/**
	 * @return Returns the controlRequired.
	 */
	public boolean isControlRequired() {
		return controlRequired;
	}
	/**
	 * @param controlRequired The controlRequired to set.
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}
	/**
	 * @return Returns the loadUserPermissions.
	 */
	public boolean isLoadUserPermissions() {
		return loadUserPermissions;
	}
	/**
	 * @param loadUserPermissions The loadUserPermissions to set.
	 */
	public void setLoadUserPermissions(boolean loadUserPermissions) {
		this.loadUserPermissions = loadUserPermissions;
	}
	/**
	 * @return the renderCrspdTrdPartForVO
	 */
	public boolean isRenderCrspdTrdPartForVO() {
		return renderCrspdTrdPartForVO;
	}
	/**
	 * @param renderCrspdTrdPartForVO the renderCrspdTrdPartForVO to set
	 */
	public void setRenderCrspdTrdPartForVO(boolean renderCrspdTrdPartForVO) {
		this.renderCrspdTrdPartForVO = renderCrspdTrdPartForVO;
	}
	/**
	 * @return the renderInqAbtForTradingPartnerVO
	 */
	public boolean isInqAbtForProvider() {
		return InqAbtForProvider;
	}
	/**
	 * @param inqAbtForProvider The inqAbtForProvider to set.
	 */
	public void setInqAbtForProvider(boolean inqAbtForProvider) {
		InqAbtForProvider = inqAbtForProvider;
	}
	public boolean isRenderInqAbtForTradingPartnerVO() {
		return renderInqAbtForTradingPartnerVO;
	}
	/**
	 * @param renderInqAbtForTradingPartnerVO the renderInqAbtForTradingPartnerVO to set
	 */
	public void setRenderInqAbtForTradingPartnerVO(
			boolean renderInqAbtForTradingPartnerVO) {
		this.renderInqAbtForTradingPartnerVO = renderInqAbtForTradingPartnerVO;
	}
	/**
	 * @return the correspondenceForTradingPartnerVO
	 */
	public CorrespondenceForTradingPartnerVO getCorrespondenceForTradingPartnerVO() {
		return correspondenceForTradingPartnerVO;
	}
	/**
	 * @param correspondenceForTradingPartnerVO the correspondenceForTradingPartnerVO to set
	 */
	public void setCorrespondenceForTradingPartnerVO(
			CorrespondenceForTradingPartnerVO correspondenceForTradingPartnerVO) {
		this.correspondenceForTradingPartnerVO = correspondenceForTradingPartnerVO;
	}
	
	private int startIndexForInqAbt=0;
	private int startIndexForAsc=0;
	private int startIndexForexistAsc=0;
	
	
	/**
	 * @return Returns the startIndexForAsc.
	 */
	public int getStartIndexForAsc() {
		return startIndexForAsc;
	}
	/**
	 * @param startIndexForAsc The startIndexForAsc to set.
	 */
	public void setStartIndexForAsc(int startIndexForAsc) {
		this.startIndexForAsc = startIndexForAsc;
	}
	/**
	 * @return Returns the startIndexForexistAsc.
	 */
	public int getStartIndexForexistAsc() {
		return startIndexForexistAsc;
	}
	/**
	 * @param startIndexForexistAsc The startIndexForexistAsc to set.
	 */
	public void setStartIndexForexistAsc(int startIndexForexistAsc) {
		this.startIndexForexistAsc = startIndexForexistAsc;
	}
	/**
	 * @return Returns the startIndexForInqAbt.
	 */
	public int getStartIndexForInqAbt() {
		return startIndexForInqAbt;
	}
	/**
	 * @param startIndexForInqAbt The startIndexForInqAbt to set.
	 */
	public void setStartIndexForInqAbt(int startIndexForInqAbt) {
		this.startIndexForInqAbt = startIndexForInqAbt;
	}
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

	private String cursorFocus;
	
	
	/**
	 * @return Returns the cursorFocus.
	 */
	public String getCursorFocus() {
		return cursorFocus;
	}
	/**
	 * @param cursorFocus The cursorFocus to set.
	 */
	public void setCursorFocus(String cursorFocus) {
		this.cursorFocus = cursorFocus;
	}

	/** panel grid issue fix*/
	private List customFieldVOList= new ArrayList();
	
	
	/**
	 * @return Returns the customFieldVOList.
	 */
	public List getCustomFieldVOList() {
		return customFieldVOList;
	}
	/**
	 * @param customFieldVOList The customFieldVOList to set.
	 */
	public void setCustomFieldVOList(List customFieldVOList) {
		this.customFieldVOList = customFieldVOList;
	}
	

	// Start - Added the code for the defect ESPRD00688792
	private String caseSK;

	/**
	 * @return the caseSK
	 */
	public String getCaseSK() {
		return caseSK;
	}
	/**
	 * @param caseSK the caseSK to set
	 */
	public void setCaseSK(String caseSK) {
		this.caseSK = caseSK;
	}
	
	private boolean navigateTOLogCase = false;

	/**
	 * @return the navigateTOLogCase
	 */
	public boolean isNavigateTOLogCase() {
		return navigateTOLogCase;
	}
	/**
	 * @param navigateTOLogCase the navigateTOLogCase to set
	 */
	public void setNavigateTOLogCase(boolean navigateTOLogCase) {
		this.navigateTOLogCase = navigateTOLogCase;
	}
	
	// End - Added the code for the defect ESPRD00688792
	
	/**
	 * Code is added below for not retaining the error messages 
	 * on Log Correspondence page for the Defect ESPRD00852896
	 */
	
	private boolean logCRErrMsgFlag;

	/**
	 * @return the logCRErrMsgFlag
	 */
	public boolean isLogCRErrMsgFlag() {
		return logCRErrMsgFlag;
	}
	/**
	 * @param logCRErrMsgFlag the logCRErrMsgFlag to set
	 */
	public void setLogCRErrMsgFlag(boolean logCRErrMsgFlag) {
		this.logCRErrMsgFlag = logCRErrMsgFlag;
	}
	
	//Below Variable added for defect ESPRD00897359
	private String correspondenceEntity;

	public String getCorrespondenceEntity() {
		return correspondenceEntity;
	}
	public void setCorrespondenceEntity(String correspondenceEntity) {
		this.correspondenceEntity = correspondenceEntity;
	}
}
