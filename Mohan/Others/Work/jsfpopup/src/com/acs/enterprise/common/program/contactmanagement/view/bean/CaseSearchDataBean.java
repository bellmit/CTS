/*
 * Created on Jan 21, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.timing.TraceTimeManager;
import com.acs.timing.TraceToken;



/**
 * @author jyodlan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class CaseSearchDataBean extends EnterpriseBaseDataBean {
	
	private boolean success = false;
    /** Enterprise Logger for Logging */
 
	
	/**
	  * to make onload cursor focus.
	  */
	private String cursorFocusId;
	/**
	  * to make onload cursor focus.
	  */
	private String cursorFocusIdCaseSearch;


    public String getCursorFocusIdCaseSearch() {
		return cursorFocusIdCaseSearch;
	}

	public void setCursorFocusIdCaseSearch(String cursorFocusIdCaseSearch) {
		this.cursorFocusIdCaseSearch = cursorFocusIdCaseSearch;
	}

	public String getCursorFocusId() {
		return cursorFocusId;
	}

	public void setCursorFocusId(String cursorFocusId) {
		this.cursorFocusId = cursorFocusId;
	}

	/**
     * This field is used to store BEAN_NAME.
     */
    // Moved to ContactManagementConstants.java 
	//public static final String BEAN_NAME = "caseSearchDataBean";

    /**
     * Holds CustomFieldVO object.
     */
    private CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO;

    /**
     * @return Returns the caseRecordSearchCriteriaVO.
     */
    public CaseRecordSearchCriteriaVO getCaseRecordSearchCriteriaVO() {
        return caseRecordSearchCriteriaVO;
    }

    /**
     * @param caseRecordSearchCriteriaVO
     *            The caseRecordSearchCriteriaVO to set.
     */
    public void setCaseRecordSearchCriteriaVO(CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO) {
        this.caseRecordSearchCriteriaVO = caseRecordSearchCriteriaVO;
    }

    /**
     * This field is used to store status List.
     */
    private List status = Collections.EMPTY_LIST;

    /**
     * This field is used to store priority List.
     */
    private List priority = Collections.EMPTY_LIST;

    /**
     * This field is used to store caseType List.
     */
    private List caseType = Collections.EMPTY_LIST;

    /**
     * This field is used to store businessUnit List.
     */
    private List businessUnit = Collections.EMPTY_LIST;

    /**
     * This field is used to store ResponseExists .
     */
    private String responseExists;
    
    /**
     * This field is used to store validValueFlag .
     */
    private boolean validValueFlag=true;

    /**
     * This field is used to store EntityID .
     */
    private String entityID;

    /**
     * This field is used to store AssignedTo .
     */
    private List assignedTo = new ArrayList();

    /**
     * This field is used to store CreatedBy .
     */
    private List createdBy = new ArrayList();

    /**
     * This field is used to store EntityType .
     */
    private List entityType = Collections.EMPTY_LIST;

    /**
     * This field is used to store Case Record Number .
     */
    private String caseRecordNumber;

    /**
     * This field is used to store Status Date .
     */
    private String statusDate;

    /**
     * This field is used to store Case Title .
     */
    private String caseTitle;

    /**
     * This field is used to store Reporting Unit .
     */
    private List reportingUnit =Collections.EMPTY_LIST;

    /**
     * This field is used to store Created From .
     */
    private String createdFrom;

    /**
     * This field is used to store Advance CreatedBy List .
     */
    private List advCreatedBy = new ArrayList();

    /**
     * This field is used to store Business Group .
     */
    private List businessGroup = Collections.EMPTY_LIST;

    /**
     * This field is used to store CreatedTo .
     */
    private String createdTo;

    /**
     * This field is used to store Departments .
     */
    private List departments = Collections.EMPTY_LIST;

    /**
     * This field is used to store Search CaseList List .
     */
    private List searchCaseList = new ArrayList();

    /**
     * Stores Reference for NoData.
     */
    private boolean noData = false;

    /**
     * Stores Reference for repUnintFlag.
     */
    private boolean repUnintFlag = false;

    /**
     * Stores Reference for DepartmentsFlag.
     */
    private boolean busUnintFlag = false;

    /**
     * Stores Reference for DepartmentsFlag.
     */
    private boolean departmentsFlag = false;
    
    /**
     * Holds boolean value of disableReassignLink.
     */
    private boolean disableReassignLink = false;
    
    
    /**
     * Holds boolean value of disableReassignAllCaseDropdown.
     */
    private boolean disableReassignAllCaseDropdown = false;
    
    
    /**
     * Holds boolean value of disableReassignToDeptDropdown.
     */
    private boolean disableReassignToDeptDropdown = false;
    
    

    /**
     * Holds boolean value of noDataFlag
     */
    private boolean noDataFlag = true;

    /**
     * Holds variable imageRenderer.
     */
    private String imageRender;

    /**
     * Holds variable imageRenderer.
     */
    private String plus = "plus";

    /**
     * Holds variable CaseSearch Flag.
     */
    private boolean showCaseSearchMsgFlag = false;

    /**
     * Holds variable User's List.
     */
    private HashMap usersMap;

    /**
     * Holds List of DeprtmentSK's.
     */
    private List maintainGroups = new ArrayList();
    
    /**
     * Holds variable showResults.
     */
    private boolean showResults = true;
    
    /**
     * Holds variable showData.
     */
    private boolean showData = false;
    
    
    /**
     * This field is used to store entityIDType .
     */
    private List entityIDTypeList = new ArrayList();

    /**
     * This field is used to store entityIDType .
     */
    private List additionalEntityIDTypeList = new ArrayList();
    
    /**
     * 
     */
    private Map businessGroupList;
    
    /** Holds Valid Value List for Line of Business Drop down */
    private List lobVVList;
    
    /**
     * @return Returns the maintainGroups.
     */
    public List getMaintainGroups()
    {
        return maintainGroups;
    }

    /**
     * @param maintainGroups
     *            The maintainGroups to set.
     */
    public void setMaintainGroups(List maintainGroups)
    {
        this.maintainGroups = maintainGroups;
    }

    /**
     * @return Returns the usersMap.
     */
    public HashMap getUsersMap() {
        return usersMap;
    }

    /**
     * @param usersMap
     *            The usersMap to set.
     */
    public void setUsersMap(HashMap usersMap) {
        this.usersMap = usersMap;
    }

    /**
     * @return Returns the showCaseSearchMsgFlag.
     */
    public boolean isShowCaseSearchMsgFlag() {
        return showCaseSearchMsgFlag;
    }

    /**
     * @param showCaseSearchMsgFlag
     *            The showCaseSearchMsgFlag to set.
     */
    public void setShowCaseSearchMsgFlag(boolean showCaseSearchMsgFlag) {
        this.showCaseSearchMsgFlag = showCaseSearchMsgFlag;
    }

    /**
     * @return Returns the plus.
     */
    public String getPlus() {
        return plus;
    }

    /**
     * @param plus
     *            The plus to set.
     */
    public void setPlus(String plus) {
        this.plus = plus;
    }

    /**
     * Holds variable responseExistsList.
     */
    private List responseExistsList;

    /**
     * @return Returns the responseExistsList.
     */
    public List getResponseExistsList() {
        return responseExistsList;
    }

    /**
     * @param responseExistsList
     *            The responseExistsList to set.
     */
    public void setResponseExistsList(List responseExistsList) {
        this.responseExistsList = responseExistsList;
    }

    /**
     * @return Returns the imageRender.
     */
    public String getImageRender() {
        return imageRender;
    }

    /**
     * @param imageRender
     *            The imageRender to set.
     */
    public void setImageRender(String imageRender) {
        this.imageRender = imageRender;
    }

    /**
     * @return Returns the noDataFlag.
     */
    public boolean isNoDataFlag() {
        return noDataFlag;
    }

    /**
     * @param noDataFlag
     *            The noDataFlag to set.
     */
    public void setNoDataFlag(boolean noDataFlag) {
        this.noDataFlag = noDataFlag;
    }

    /**
     * @return Returns the busUnintFlag.
     */
    public boolean isBusUnintFlag() {
        return busUnintFlag;
    }

    /**
     * @param busUnintFlag
     *            The busUnintFlag to set.
     */
    public void setBusUnintFlag(boolean busUnintFlag) {
        this.busUnintFlag = busUnintFlag;
    }

    /**
     * @return Returns the departmentsFlag.
     */
    public boolean isDepartmentsFlag() {
        return departmentsFlag;
    }

    /**
     * @param departmentsFlag
     *            The departmentsFlag to set.
     */
    public void setDepartmentsFlag(boolean departmentsFlag) {
        this.departmentsFlag = departmentsFlag;
    }

    /**
     * @return Returns the repUnintFlag.
     */
    public boolean isRepUnintFlag() {
        return repUnintFlag;
    }

    /**
     * @param repUnintFlag
     *            The repUnintFlag to set.
     */
    public void setRepUnintFlag(boolean repUnintFlag) {
        this.repUnintFlag = repUnintFlag;
    }

    /**
     * @return Returns the noData.
     */
    public boolean isNoData() {
        return noData;
    }

    /**
     * @param noData
     *            The noData to set.
     */
    public void setNoData(boolean noData) {
        this.noData = noData;
    }

    /**
     * @return Returns the searchCaseList.
     */
    public List getSearchCaseList() {
        return searchCaseList;
    }

    /**
     * @param searchCaseList
     *            The searchCaseList to set.
     */
    public void setSearchCaseList(List searchCaseList) {
        this.searchCaseList = searchCaseList;
    }

    /**
     * @return Returns the status.
     */
    public List getStatus() {
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(List status) {
        this.status = status;
    }

    /**
     * @return Returns the priority.
     */
    public List getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            The priority to set.
     */
    public void setPriority(List priority) {
        this.priority = priority;
    }

    /**
     * @return Returns the caseType.
     */
    public List getCaseType() {
        return caseType;
    }

    /**
     * @param caseType
     *            The caseType to set.
     */
    public void setCaseType(List caseType) {
        this.caseType = caseType;
    }

    /**
     * @return Returns the businessUnit.
     */
    public List getBusinessUnit() {
        return businessUnit;
    }

    /**
     * @param businessUnit
     *            The businessUnit to set.
     */
    public void setBusinessUnit(List businessUnit) {
        this.businessUnit = businessUnit;
    }

    /**
     * @return Returns the assignedTo.
     */
    public List getAssignedTo() {
        return assignedTo;
    }

    /**
     * @param assignedTo
     *            The assignedTo to set.
     */
    public void setAssignedTo(List assignedTo) {
        this.assignedTo = assignedTo;
    }

    /**
     * @return Returns the createdBy.
     */
    public List getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     *            The createdBy to set.
     */
    public void setCreatedBy(List createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return Returns the businessGroup.
     */
    public List getBusinessGroup() {
        return businessGroup;
    }

    /**
     * @param businessGroup
     *            The businessGroup to set.
     */
    public void setBusinessGroup(List businessGroup) {
        this.businessGroup = businessGroup;
    }

    /**
     * @return Returns the caseRecordNumber.
     */
    public String getCaseRecordNumber() {
        return caseRecordNumber;
    }

    /**
     * @param caseRecordNumber
     *            The caseRecordNumber to set.
     */
    public void setCaseRecordNumber(String caseRecordNumber) {
        this.caseRecordNumber = caseRecordNumber;
    }

    /**
     * @return Returns the caseTitle.
     */
    public String getCaseTitle() {
        return caseTitle;
    }

    /**
     * @param caseTitle
     *            The caseTitle to set.
     */
    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    /**
     * @return Returns the advCreatedBy.
     */
    public List getAdvCreatedBy() {
        return advCreatedBy;
    }

    /**
     * @param advCreatedBy
     *            The advCreatedBy to set.
     */
    public void setAdvCreatedBy(List advCreatedBy) {
        this.advCreatedBy = advCreatedBy;
    }

    /**
     * @return Returns the createdFrom.
     */
    public String getCreatedFrom() {
        return createdFrom;
    }

    /**
     * @param createdFrom
     *            The createdFrom to set.
     */
    public void setCreatedFrom(String createdFrom) {
        this.createdFrom = createdFrom;
    }

    /**
     * @return Returns the createdTo.
     */
    public String getCreatedTo() {
        return createdTo;
    }

    /**
     * @param createdTo
     *            The createdTo to set.
     */
    public void setCreatedTo(String createdTo) {
        this.createdTo = createdTo;
    }

    /**
     * @return Returns the departments.
     */
    public List getDepartments() {
        return departments;
    }

    /**
     * @param departments
     *            The departments to set.
     */
    public void setDepartments(List departments) {
        this.departments = departments;
    }

    /**
     * @return Returns the entityID.
     */
    public String getEntityID() {
        return entityID;
    }

    /**
     * @param entityID
     *            The entityID to set.
     */
    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    /**
     * @return Returns the entityType.
     */
    public List getEntityType() {
        return entityType;
    }

    /**
     * @param entityType
     *            The entityType to set.
     */
    public void setEntityType(List entityType) {
        this.entityType = entityType;
    }

    /**
     * @return Returns the reportingUnit.
     */
    public List getReportingUnit() {
        return reportingUnit;
    }

    /**
     * @param reportingUnit
     *            The reportingUnit to set.
     */
    public void setReportingUnit(List reportingUnit) {
        this.reportingUnit = reportingUnit;
    }

    /**
     * @return Returns the responseExists.
     */
    public String getResponseExists() {
        return responseExists;
    }

    /**
     * @param responseExists
     *            The responseExists to set.
     */
    public void setResponseExists(String responseExists) {
        this.responseExists = responseExists;
    }

    /**
     * @return Returns the statusDate.
     */
    public String getStatusDate() {
        return statusDate;
    }

    /**
     * @param statusDate
     *            The statusDate to set.
     */
    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

   

    /**
     * This method fetches the InputCriteria object with the parmeters set for
     * functional area and reference name.
     * 
     * @param referenceDataConstant -
     *            Element name.
     * @param functionalArea -
     *            Functional Area code.
     * @return - InputCriteria
     */
    /*private InputCriteria getInputCriteria(String referenceDataConstant, String functionalArea) {
        InputCriteria inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(functionalArea);

        inputCriteria.setElementName(referenceDataConstant);

        return inputCriteria;
    }*/

    /**
     * @param map -
     *            Map object containing the area code and element name.
     * @param referenceDataConstant -
     *            Element name.
     * @param functionalArea -
     *            Functional Area code.
     * @return List - with valid values populated.
     */
    /*private List getValidData(Map map, String referenceDataConstant, String functionalArea) {
        List validList = new ArrayList();
        String validValueCodeDesc = null;
        List validValues = (List) map.get(functionalArea + "#" + referenceDataConstant);
        validList.add(new SelectItem("", ""));
        int validValuesSize = validValues.size();
        for (int i = 0; i < validValuesSize; i++) {
            ReferenceServiceVO refVo = (ReferenceServiceVO) validValues.get(i);
            validValueCodeDesc = refVo.getValidValueCode() + "-" + refVo.getShortDescription();
            validList.add(new SelectItem(refVo.getValidValueCode(), validValueCodeDesc));
            
        }
        return validList;
    }*/

   /* private Map getValidDataNames(Map map, String referenceDataConstant, String funcitionalArea) {
    	List validValues = (List) map.get(funcitionalArea+ '#'+ referenceDataConstant);
    	Map mapBusiness = new HashMap();
    	for (int i = 0; i < validValues.size();i ++){
    		ReferenceServiceVO refVO = (ReferenceServiceVO) validValues.get(i);
    		mapBusiness.put(refVO.getShortDescription(),refVO.getValidValueCode());
    	}
    	return mapBusiness;
    }*/
    

    

   
    /**
     * Added by ICS
     */
    private List reassignAll;

	

	/**
	 * @return Returns the reassignAll.
	 */
	public List getReassignAll() {
		return reassignAll;
	}
	/**
	 * @param reassignAll The reassignAll to set.
	 */
	public void setReassignAll(List reassignAll) {
		this.reassignAll = reassignAll;
	}
	/**
	 * Added by ICS
	 */
	private String selectedDeptAll;

	/**
	 * @return
	 */
	public String getSelectedDeptAll() {		
		return selectedDeptAll;
	}
	/**
	 * @param selectedDeptAll The selectedDeptAll to set.
	 */
	public void setSelectedDeptAll(String selectedDeptAll) {
		this.selectedDeptAll = selectedDeptAll;
	}
	/**
	 * Added by ICS
	 */
	private boolean showReassignResultsPage = false;
	
	/**
	 * @return Returns the showReassignResultsPage.
	 */
	public boolean isShowReassignResultsPage() {
		return showReassignResultsPage;
	}
	/**
	 * @param showReassignResultsPage The showReassignResultsPage to set.
	 */
	public void setShowReassignResultsPage(boolean showReassignResultsPage) {
		this.showReassignResultsPage = showReassignResultsPage;
	}
	
	private boolean showSucessMessage;
	
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
	 * @return Returns the showData.
	 */
	public boolean isShowData() {
		return showData;
	}
	/**
	 * @param showData The showData to set.
	 */
	public void setShowData(boolean showData) {
		this.showData = showData;
	}
	/**
	 * @return Returns the showResults.
	 */
	public boolean isShowResults() {
		return showResults;
	}
	/**
	 * @param showResults The showResults to set.
	 */
	public void setShowResults(boolean showResults) {
		this.showResults = showResults;
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
	
	private CaseRecordSearchCriteriaVO caseSearchCriteriaVO;
	
	/**
	 * @return Returns the caseSearchCriteriaVO.
	 */
	public CaseRecordSearchCriteriaVO getCaseSearchCriteriaVO() {
		return caseSearchCriteriaVO;
	}
	/**
	 * @param caseSearchCriteriaVO The caseSearchCriteriaVO to set.
	 */
	public void setCaseSearchCriteriaVO(
			CaseRecordSearchCriteriaVO caseSearchCriteriaVO) {
		this.caseSearchCriteriaVO = caseSearchCriteriaVO;
	}
	//FOR HEAP-DUMP ISSUE
	/*private List provTypeVVList = null;
	
	*//**
	 * @return Returns the provTypeVVList.
	 *//*
	public List getProvTypeVVList() {
		return provTypeVVList;
	}
	*//**
	 * @param provTypeVVList The provTypeVVList to set.
	 *//*
	public void setProvTypeVVList(List provTypeVVList) {
		this.provTypeVVList = provTypeVVList;
	}
	
	 private List additionalEntityType = new ArrayList();
		
	*//**
	 * @return Returns the additionalEntityType.
	 *//*
	public List getAdditionalEntityType() {
		return additionalEntityType;
	}
	*//**
	 * @param additionalEntityType The additionalEntityType to set.
	 *//*
	public void setAdditionalEntityType(List additionalEntityType) {
		this.additionalEntityType = additionalEntityType;
	}*/
	//FOR HEAP-DUMP ISSUE
	/**
	 * @return Returns the additionalEntityIDTypeList.
	 */
	public List getAdditionalEntityIDTypeList() {
		return additionalEntityIDTypeList;
	}
	/**
	 * @param additionalEntityIDTypeList The additionalEntityIDTypeList to set.
	 */
	public void setAdditionalEntityIDTypeList(List additionalEntityIDTypeList) {
		this.additionalEntityIDTypeList = additionalEntityIDTypeList;
	}
	/**
	 * @return Returns the businessGroupList.
	 */
	public Map getBusinessGroupList() {
		return businessGroupList;
	}
	/**
	 * @param businessGroupList The businessGroupList to set.
	 */
	public void setBusinessGroupList(Map businessGroupList) {
		this.businessGroupList = businessGroupList;
	}
	
	 /**
     * This method is used to get the User ID.
     *
     * @return String : User ID
     */
//    public String getUserID()
//    {
//
//        TraceToken tt = null;
//        tt = TraceTimeManager
//                .startMethod("CorrespondenceControllerBean :: getUserID");
//        String userId = "TBOINAPALLY";
//
//        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
//                .getCurrentInstance().getExternalContext().getRequest();
//        HttpServletResponse renderResponse = null;
//
//        EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
//
//        if (eup != null && !isNullOrEmptyField(eup.getUserId()))
//        {
//            userId = eup.getUserId();
//        }
//
//        TraceTimeManager.endMethod(tt);
//        return userId;
//    }
    
    /**
     * This method is used to check if the input field is null or is equal to an
     * empty string.
     *
     * @param inputField :
     *            String inputField.
     * @return boolean : true if input field is null or equal to an empty string
     *         else false.
     */
    private boolean isNullOrEmptyField(String inputField)
    {
        return (inputField == null || ContactManagementConstants.EMPTY_STRING
                .equalsIgnoreCase(inputField.trim()));
    }
	/**
	 * @return Returns the disableReassignLink.
	 */
	public boolean isDisableReassignLink() {
		return disableReassignLink;
	}
	/**
	 * @param disableReassignLink The disableReassignLink to set.
	 */
	public void setDisableReassignLink(boolean disableReassignLink) {
		this.disableReassignLink = disableReassignLink;
	}
	/**
	 * @return Returns the disableReassignAllCaseDropdown.
	 */
	public boolean isDisableReassignAllCaseDropdown() {
		return disableReassignAllCaseDropdown;
	}
	/**
	 * @param disableReassignAllCaseDropdown The disableReassignAllCaseDropdown to set.
	 */
	public void setDisableReassignAllCaseDropdown(
			boolean disableReassignAllCaseDropdown) {
		this.disableReassignAllCaseDropdown = disableReassignAllCaseDropdown;
	}
	/**
	 * @return Returns the disableReassignToDeptDropdown.
	 */
	public boolean isDisableReassignToDeptDropdown() {
		return disableReassignToDeptDropdown;
	}
	/**
	 * @param disableReassignToDeptDropdown The disableReassignToDeptDropdown to set.
	 */
	public void setDisableReassignToDeptDropdown(
			boolean disableReassignToDeptDropdown) {
		this.disableReassignToDeptDropdown = disableReassignToDeptDropdown;
	}
	
	  private boolean disableAddSearch=false; 
	    
		/**
		 * @return Returns the disableAddSearch.
		 */
		public boolean isDisableAddSearch() {
			return disableAddSearch;
		}
		/**
		 * @param disableAddSearch The disableAddSearch to set.
		 */
		public void setDisableAddSearch(boolean disableAddSearch) {
			this.disableAddSearch = disableAddSearch;
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
     * Added by ICS
     */
    	private List allDeptUsersForReassign;
    public void setAllDeptUsersForReassign(List list){
    	this.allDeptUsersForReassign = list;
    }
    /**
     * @return Returns the allDeptUsersForReassign.
     */
    public List getAllDeptUsersForReassign() {
        return allDeptUsersForReassign;
    }
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return Returns the validValueFlag.
	 */
	public boolean isValidValueFlag() {
		return validValueFlag;
	}
	/**
	 * @param validValueFlag The validValueFlag to set.
	 */
	public void setValidValueFlag(boolean validValueFlag) {
		this.validValueFlag = validValueFlag;
	}
	
	
	//UC-PGM-CRM-018_ESPRD00392654_25JAN10
	
	private boolean controlCameFromCMLogCase = false;
	/**
	 * @return Returns the controlCameFromCMLogCase.
	 */
	public boolean isControlCameFromCMLogCase() {
		return controlCameFromCMLogCase;
	}
	/**
	 * @param controlCameFromCMLogCase The controlCameFromCMLogCase to set.
	 */
	public void setControlCameFromCMLogCase(boolean controlCameFromCMLogCase) {
		this.controlCameFromCMLogCase = controlCameFromCMLogCase;
	}
	
	private List showCaseTempList = new ArrayList();
	/**
	 * @return Returns the showCaseTempList.
	 */
	public List getShowCaseTempList() {
		return showCaseTempList;
	}
	/**
	 * @param showCaseTempList The showCaseTempList to set.
	 */
	public void setShowCaseTempList(List showCaseTempList) {
		this.showCaseTempList = showCaseTempList;
	}
	
	private boolean controlRequired;


	public boolean isControlRequired() {
		return controlRequired;
	}

	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}
	
	//end
	
	//Start - Added this block for the heap dump fix
	/** holds the items per Page. */
	private int itemsPerPage = 10;

	/**
	 * @return the itemsPerPage
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * @param itemsPerPage the itemsPerPage to set
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	/** variable to hold the start Record number for the current page. */
    private int startRecordNo;

	/**
	 * @return the startRecordNo
	 */
	public int getStartRecordNo() {
		return startRecordNo;
	}

	/**
	 * @param startRecordNo the startRecordNo to set
	 */
	public void setStartRecordNo(int startRecordNo) {
		this.startRecordNo = startRecordNo;
	}
	//End - Added this block for the heap dump fix
	
	//Begin - Added this block of code for the defect id ESPRD00686628_29Aug2011
	/** variable to hold the rowIndex value. */
    private int rowIndex;

	/**
	 * @return the rowIndex
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * @param rowIndex the rowIndex to set
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	//End - Added this block of code for the defect id ESPRD00686628_29Aug2011
	
	// Begin - Removed the HTMLDataTable class and added the int data type
	// for the HeapDump issue.
	/**
	 * Holds the value of reassignCaseResultTable.
	 */
	 private int reassignCaseResultTable;

	/**
	 * @return the reassignCaseResultTable
	 */
	public int getReassignCaseResultTable() {
		return reassignCaseResultTable;
	}

	/**
	 * @param reassignCaseResultTable the reassignCaseResultTable to set
	 */
	public void setReassignCaseResultTable(int reassignCaseResultTable) {
		this.reassignCaseResultTable = reassignCaseResultTable;
	}
	// End - Removed the HTMLDataTable class and added the int data type
	// for the HeapDump issue.
	
	// Begin - This block of code is added for the Heap Dump Fix
	/**
	* Holds the value of reassignFlag.
	*/
	private boolean reassignFlag = false;
	
	/**
	 * @return the reassignFlag
	 */
	public boolean isReassignFlag() {
		return reassignFlag;
	}

	/**
	 * @param reassignFlag the reassignFlag to set
	 */
	public void setReassignFlag(boolean reassignFlag) {
		this.reassignFlag = reassignFlag;
	}
	// End - This block of code is added for the Heap Dump Fix
	
	// Begin - This block of code is added for the DataScroller Fix
	/**
	* Holds the value of showNextOne.
	*/
	private boolean showNextOne = false;


	/**
	 * @return the showNextOne
	 */
	public boolean isShowNextOne() {
		return showNextOne;
	}

	/**
	 * @param showNextOne the showNextOne to set
	 */
	public void setShowNextOne(boolean showNextOne) {
		this.showNextOne = showNextOne;
	}
	// End - This block of code is added for the DataScroller Fix
	
	// Begin - Added this code for the performance Fix
	/**
	 * Hold the value of validValueCodeDesc
	 */
	private Map entityTypeMap = Collections.EMPTY_MAP;

	/**
	 * @return the entityTypeMap
	 */
	public Map getEntityTypeMap() {
		return entityTypeMap;
	}

	/**
	 * @param entityTypeMap the entityTypeMap to set
	 */
	public void setEntityTypeMap(Map entityTypeMap) {
		this.entityTypeMap = entityTypeMap;
	}
	// End - Added this code for the performance Fix
	// Added for the Defect Id : ESPRD00722628
	private boolean caseAsctErrormsg=false;


	/**
	 * @return the caseAsctErrormsg
	 */
	public boolean isCaseAsctErrormsg() {
		return caseAsctErrormsg;
	}

	/**
	 * @param caseAsctErrormsg the caseAsctErrormsg to set
	 */
	public void setCaseAsctErrormsg(boolean caseAsctErrormsg) {
		this.caseAsctErrormsg = caseAsctErrormsg;
	}
	
	/** To render the condition of
	 *  advance option in case search page
	 * */
	private boolean advOptionsFlag;


	/**
	 * @return the advOptionsFlag
	 */
	public boolean isAdvOptionsFlag() {
		return advOptionsFlag;
	}

	/**
	 * @param advOptionsFlag the advOptionsFlag to set
	 */
	public void setAdvOptionsFlag(boolean advOptionsFlag) {
		this.advOptionsFlag = advOptionsFlag;
	}
	/**
	 * This flag will be used for displaying the error messages related to
	 * Associated Case at Page level in Log Case Page
	 * */
	private boolean searchCaseValidationFlag;

	/**
	 * @return the searchCaseValidationFlag
	 */
	public boolean isSearchCaseValidationFlag() {
		return searchCaseValidationFlag;
	}

	/**
	 * @param searchCaseValidationFlag
	 *            the searchCaseValidationFlag to set
	 */
	public void setSearchCaseValidationFlag(boolean searchCaseValidationFlag) {
		this.searchCaseValidationFlag = searchCaseValidationFlag;
	}
	
	/**Holds the logged in user */
	private String userId;
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	//for CR 798895 for Bulk reassign.
	/**
	 * Holds the Navigation mode when user click navigation in data table
	 * results, popup window is displayed as if 'ok' selected current page data
	 * is saved and navigation should happen or 'cancel' selected then just
	 * navigation. This property will hold navigation mode on selection of 'ok'
	 * either it has go 'next' or 'previous' or 'nextOne'.
	 * */
	private String continueType;
	
	/**
	 * @return the continueType
	 */
	public String getContinueType() {
		return continueType;
	}

	/**
	 * @param continueType the continueType to set
	 */
	public void setContinueType(String continueType) {
		this.continueType = continueType;
	}
}
