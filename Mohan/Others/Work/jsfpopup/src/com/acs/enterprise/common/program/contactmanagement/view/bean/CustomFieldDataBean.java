/*
 * Created on Jan 8, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CustomFieldFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldTable;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.DropDownVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author sivngan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomFieldDataBean extends EnterpriseBaseDataBean {

	/** Enterprise Logger for Logging */
	private transient EnterpriseLogger log = EnterpriseLogFactory
			.getLogger(getClass().getName());

	
	/**
	 * Constructor calls getReferenceData method.
	 */
	public CustomFieldDataBean() {
		
		super();
		loadAllValiedValues();
		loadScope();

	}
	/** For fixing Defect ESPRD00611024
	 * */
	private DropDownVO tempDropDownVO;
		
	/**
	 * @return Returns the tempDropDownVO.
	 */
	public DropDownVO getTempDropDownVO() {
		return tempDropDownVO;
	}
	/**
	 * @param tempDropDownVO The tempDropDownVO to set.
	 */
	public void setTempDropDownVO(DropDownVO tempDropDownVO) {
		this.tempDropDownVO = tempDropDownVO;
	}
	
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
	/**
	 * Holds scopeList Valid values
	 */
	private List scopeList = new ArrayList();

	/**
	 * Holds CustomFieldVO object.
	 */
	private CustomFieldVO customFieldVO;

	/**
	 * Holds customFieldList.
	 */
	/*private List customFieldList = new ArrayList();*/

	/**
	 * Holds dropDownList.
	 */
	private List dropDownList = new ArrayList();

	/** Flag variable to show when there is no data in the dataTable */
	//commented for unused variables
	//private boolean dropDownListData = false;

	/** Flag variable to render add DropDownFlag */
	private boolean addDropDownFlag = false;

	/** Flag variable to render edit DropDownMenuFlag */
	private boolean editDropDownMenuFlag = false;
	
	/** Flag variable to render edit DropDownInfoFlag */
	private boolean editDropDownInfoFlag = false;
	
	/** Holds variable for maintaining the index of the record selected */
	private String dropDownIndex;

	/** Holds variable for rendering the success msg flag */
	private boolean showSuccessMsgFlag = false;

	/** Holds flag to display No Data if no data available in the data table */
	private boolean dropDownDataFlag = false;

	/** Flag variable to render edit DropDownFlag */
	private boolean editDropDownFlag = false;
	
	/** Flag variable to render edit DropDownFlag */
	private boolean lengthFlag = true;

	/**
	 * Holds variable imageRenderer.
	 */
	private String imageRender;

	/**
	 * Holds flag to show DropDownScreen
	 */
	private boolean showDropDownScreenFlag = false;

	/**
	 * Variable to reneder Add Call Scripts Block.
	 */
	private boolean showAddCustomFields = false;

	/**
	 * Variable to reneder Edit Custom Fields Block.
	 */
	private boolean showEditCustomFields = false;
	
	private boolean showCFforAud = false;
	
	private boolean showCFforAuddropdown = false;
	

	/**
	 * Variable to reneder Edit Custom Fields Block.
	 */
	private boolean showSucessMessage = false;

	/**
	 * Stores Reference for Data.
	 */
	//commented for unused variables
	//private boolean dataFlag = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean noData = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean getRecords = false;

	/**
	 * Stores Reference ofLlenght.
	 */
	private String length;

	/**
	 * Variable Edit Custom Fields Block for DataType.
	 */
	private boolean showDataType = false;

	/**
	 * Variable to render Edit Custom Fields Block.
	 */
	private boolean showDeletedMessage = false;
	
	/**
	 * Variable to render Edit Custom Fields Block.
	 */
	private boolean showDDDeletedMessage = false;
	
	
	private int startIndex=0;  
	
	
	// Added for the defect ESPRD00795949
	
	/**
	 * Variable to hold first row index of drop down data table .
	 */

	private int ddstartIndex=0;


	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}
	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * @return Returns the showDDDeletedMessage.
	 */
	public boolean isShowDDDeletedMessage() {
		return showDDDeletedMessage;
	}
	/**
	 * @param showDDDeletedMessage The showDDDeletedMessage to set.
	 */
	public void setShowDDDeletedMessage(boolean showDDDeletedMessage) {
		this.showDDDeletedMessage = showDDDeletedMessage;
	}
	/**
	 * Variable to reneder CustomFieldDropDown Description.
	 */
	private boolean showDescMsgFlag = false;

	/**
	 * Variable to reneder CustomField Description.
	 */
	private boolean showCFDescMsgFlag = false;


	private boolean auditHistoryRender = false;

	private List auditHistoryList = new ArrayList();

	private boolean auditColumnHistoryRender = false;

	private AuditLog selectedAuditLog;
	
	private int ddItemsPerPage = 10;
	
	private AuditLog ddSelectedAuditLog;

	private boolean ddAuditColumnHistoryRender = false;
	/**
	 * @return Returns the ddAuditColumnHistoryRender.
	 */
	public boolean isDdAuditColumnHistoryRender() {
		return ddAuditColumnHistoryRender;
	}
	/**
	 * @param ddAuditColumnHistoryRender The ddAuditColumnHistoryRender to set.
	 */
	public void setDdAuditColumnHistoryRender(boolean ddAuditColumnHistoryRender) {
		this.ddAuditColumnHistoryRender = ddAuditColumnHistoryRender;
	}

	/**
	 * @return Returns the ddSelectedAuditLog.
	 */
	public AuditLog getDdSelectedAuditLog() {
		return ddSelectedAuditLog;
	}
	/**
	 * @param ddSelectedAuditLog The ddSelectedAuditLog to set.
	 */
	public void setDdSelectedAuditLog(AuditLog ddSelectedAuditLog) {
		this.ddSelectedAuditLog = ddSelectedAuditLog;
	}

	/** holds the total number of records per page. */
	private int itemsPerPage = 10;

	/**
	 * @return Returns the ddItemsPerPage.
	 */
	public int getDdItemsPerPage() {
		return ddItemsPerPage;
	}
	/**
	 * @param ddItemsPerPage The ddItemsPerPage to set.
	 */
	public void setDdItemsPerPage(int ddItemsPerPage) {
		this.ddItemsPerPage = ddItemsPerPage;
	}

    /**
	 * @return Returns the itemsPerPage.
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	/**
	 * @param itemsPerPage The itemsPerPage to set.
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	/**
	 * @return Returns the selectedAuditLog.
	 */
	public AuditLog getSelectedAuditLog() {
		return selectedAuditLog;
	}
	/**
	 * @param selectedAuditLog The selectedAuditLog to set.
	 */
	public void setSelectedAuditLog(AuditLog selectedAuditLog) {
		this.selectedAuditLog = selectedAuditLog;
	}
	/**
	 * @return Returns the auditColumnHistoryRender.
	 */
	public boolean isAuditColumnHistoryRender() {
		return auditColumnHistoryRender;
	}
	/**
	 * @param auditColumnHistoryRender The auditColumnHistoryRender to set.
	 */
	public void setAuditColumnHistoryRender(boolean auditColumnHistoryRender) {
		this.auditColumnHistoryRender = auditColumnHistoryRender;
	}
	/**
	 * @return Returns the auditHistoryList.
	 */
	public List getAuditHistoryList() {
		return auditHistoryList;
	}
	/**
	 * @param auditHistoryList The auditHistoryList to set.
	 */
	public void setAuditHistoryList(List auditHistoryList) {
		this.auditHistoryList = auditHistoryList;
	}
	
	/**
	 * @return Returns the auditHistoryRender.
	 */
	public boolean isAuditHistoryRender() {
		return auditHistoryRender;
	}
	/**
	 * @param auditHistoryRender The auditHistoryRender to set.
	 */
	public void setAuditHistoryRender(boolean auditHistoryRender) {
		this.auditHistoryRender = auditHistoryRender;
	}
	/**
	 * @return Returns the auditLogRendered.
	 */
	public boolean isAuditLogRendered() {
		return auditLogRendered;
	}
	/**
	 * @param auditLogRendered The auditLogRendered to set.
	 */
	public void setAuditLogRendered(boolean auditLogRendered) {
		this.auditLogRendered = auditLogRendered;
	}
	private boolean auditLogRendered = false;

	/*Samll Save and Big Save*/
	private boolean ddSaveFlag = false;
	
	private boolean ddFlag=false;
	
	private boolean flagFordd=false;
	
	//commented for unused variables
	//private boolean cfdropdownFalg=false;
	/*Samll Save and Big Save*/
	
	/**
	 * @return Returns the showCFDescMsgFlag.
	 */
	public boolean isShowCFDescMsgFlag() {
		return showCFDescMsgFlag;
	}

	/**
	 * @param showCFDescMsgFlag
	 *            The showCFDescMsgFlag to set.
	 */
	public void setShowCFDescMsgFlag(boolean showCFDescMsgFlag) {
		this.showCFDescMsgFlag = showCFDescMsgFlag;
	}

	/**
	 * @return Returns the showDescMsgFlag.
	 */
	public boolean isShowDescMsgFlag() {
		return showDescMsgFlag;
	}

	/**
	 * @param showDescMsgFlag
	 *            The showDescMsgFlag to set.
	 */
	public void setShowDescMsgFlag(boolean showDescMsgFlag) {
		this.showDescMsgFlag = showDescMsgFlag;
	}

	/**
	 * @return Returns the showDeletedMessage.
	 */
	public boolean isShowDeletedMessage() {
		return showDeletedMessage;
	}

	/**
	 * @param showDeletedMessage
	 *            The showDeletedMessage to set.
	 */
	public void setShowDeletedMessage(boolean showDeletedMessage) {
		this.showDeletedMessage = showDeletedMessage;
	}

	/**
	 * @return Returns the showDataType.
	 */
	public boolean isShowDataType() {
		return showDataType;
	}

	/**
	 * @param showDataType
	 *            The showDataType to set.
	 */
	public void setShowDataType(boolean showDataType) {
		this.showDataType = showDataType;
	}

	/**
	 * @return Returns the length.
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length
	 *            The length to set.
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * @return Returns the getRecords.
	 */
	public boolean isGetRecords() {
		return getRecords;
	}

	/**
	 * @param getRecords
	 *            The getRecords to set.
	 */
	public void setGetRecords(boolean getRecords) {
		this.getRecords = getRecords;
	}

	/**
	 */
	private List maintainCustomFieldsList = new ArrayList();

	/**
	 * This field is used to store category row index.
	 */
	private int rowIndex;

	/**
	 * Holds values protected indicator
	 */
	private List dataTypeList;

	/** holds the audit details list for Eligibility */
	private List cfAuditHistoryList = new ArrayList();

	/** holds the audit details list for SpendDown */
	private List ddAuditHistoryList = new ArrayList();

	/** maintain audit render flag */
	private boolean cfAuditHistoryRender = false;

	/** maintain audit render flag */
	private boolean ddAuditHistoryRender = false;

	/** holds flag value for open audit screen for eligibility */
	private boolean cfAuditOpen = false;

	/** holds flag value for open audit screen for SpendDown */
	private boolean ddAuditOpen = false;
	
	private boolean sortIndicator=false;

	/**
	 * @return the sortIndicator
	 */
	public boolean isSortIndicator() {
		return sortIndicator;
	}
	/**
	 * @param sortIndicator the sortIndicator to set
	 */
	public void setSortIndicator(boolean sortIndicator) {
		this.sortIndicator = sortIndicator;
	}
	/** holds the Active list */
	private List activeList = new ArrayList();

	/** holds the Requited list */
	private List requiredList = new ArrayList();

	/** holds the Protected list */
	private List protectedList = new ArrayList();

	/** holds the previous scope value */
	private String oldScope;

	/** holds the previous dataType value */
	private String oldDataType;

	/** holds the previous dataType value */
	private String oldDropDownItemDesc;

	/**
	 * @return Returns the oldDropDownItemDesc.
	 */
	public String getOldDropDownItemDesc() {
		return oldDropDownItemDesc;
	}

	/**
	 * @param oldDropDownItemDesc
	 *            The oldDropDownItemDesc to set.
	 */
	public void setOldDropDownItemDesc(String oldDropDownItemDesc) {
		this.oldDropDownItemDesc = oldDropDownItemDesc;
	}

	/**
	 * @return Returns the oldDataType.
	 */
	public String getOldDataType() {
		return oldDataType;
	}

	/**
	 * @param oldDataType
	 *            The oldDataType to set.
	 */
	public void setOldDataType(String oldDataType) {
		this.oldDataType = oldDataType;
	}

	/**
	 * @return Returns the oldScope.
	 */
	public String getOldScope() {
		return oldScope;
	}

	/**
	 * @param oldScope
	 *            The oldScope to set.
	 */
	public void setOldScope(String oldScope) {
		this.oldScope = oldScope;
	}

	/**
	 * @return Returns the activeList.
	 */
	public List getActiveList() {
		return activeList;
	}

	/**
	 * @param activeList
	 *            The activeList to set.
	 */
	private void setActiveList(List activeList) {
		this.activeList = activeList;
	}

	/**
	 * @return Returns the protectedList.
	 */
	public List getProtectedList() {
		return protectedList;
	}

	/**
	 * @param protectedList
	 *            The protectedList to set.
	 */
	private void setProtectedList(List protectedList) {
		this.protectedList = protectedList;
	}

	/**
	 * @return Returns the requiredList.
	 */
	public List getRequiredList() {
		return requiredList;
	}

	/**
	 * @param requiredList
	 *            The requiredList to set.
	 */
	private void setRequiredList(List requiredList) {
		this.requiredList = requiredList;
	}

	/**
	 * @return Returns the cfAuditHistoryList.
	 */
	public List getCfAuditHistoryList() {
		return cfAuditHistoryList;
	}

	/**
	 * @param cfAuditHistoryList
	 *            The cfAuditHistoryList to set.
	 */
	public void setCfAuditHistoryList(List cfAuditHistoryList) {
		this.cfAuditHistoryList = cfAuditHistoryList;
	}

	/**
	 * @return Returns the cfAuditHistoryRender.
	 */
	public boolean isCfAuditHistoryRender() {
		return cfAuditHistoryRender;
	}

	/**
	 * @param cfAuditHistoryRender
	 *            The cfAuditHistoryRender to set.
	 */
	public void setCfAuditHistoryRender(boolean cfAuditHistoryRender) {
		this.cfAuditHistoryRender = cfAuditHistoryRender;
	}

	/**
	 * @return Returns the cfAuditOpen.
	 */
	public boolean isCfAuditOpen() {
		return cfAuditOpen;
	}

	/**
	 * @param cfAuditOpen
	 *            The cfAuditOpen to set.
	 */
	public void setCfAuditOpen(boolean cfAuditOpen) {
		this.cfAuditOpen = cfAuditOpen;
	}

	/**
	 * @return Returns the ddAuditHistoryList.
	 */
	public List getDdAuditHistoryList() {
		return ddAuditHistoryList;
	}

	/**
	 * @param ddAuditHistoryList
	 *            The ddAuditHistoryList to set.
	 */
	public void setDdAuditHistoryList(List ddAuditHistoryList) {
		this.ddAuditHistoryList = ddAuditHistoryList;
	}

	/**
	 * @return Returns the ddAuditHistoryRender.
	 */
	public boolean isDdAuditHistoryRender() {
		return ddAuditHistoryRender;
	}

	/**
	 * @param ddAuditHistoryRender
	 *            The ddAuditHistoryRender to set.
	 */
	public void setDdAuditHistoryRender(boolean ddAuditHistoryRender) {
		this.ddAuditHistoryRender = ddAuditHistoryRender;
	}

	/**
	 * @return Returns the ddAuditOpen.
	 */
	public boolean isDdAuditOpen() {
		return ddAuditOpen;
	}

	/**
	 * @param ddAuditOpen
	 *            The ddAuditOpen to set.
	 */
	public void setDdAuditOpen(boolean ddAuditOpen) {
		this.ddAuditOpen = ddAuditOpen;
	}

	/**
	 * @return Returns the dataTypeList.
	 */
	public List getDataTypeList() {
		return dataTypeList;
	}

	/**
	 * @param dataTypeList
	 *            The dataTypeList to set.
	 */
	private void setDataTypeList(List dataTypeList) {
		this.dataTypeList = dataTypeList;
	}

	/**
	 * @return Returns the rowIndex.
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * @param rowIndex
	 *            The rowIndex to set.
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * @return Returns the maintainCustomFieldsList.
	 */
	public List getMaintainCustomFieldsList() {
		return maintainCustomFieldsList;
	}

	/**
	 * @param maintainCustomFieldsList
	 *            The maintainCustomFieldsList to set.
	 */
	public void setMaintainCustomFieldsList(List maintainCustomFieldsList) {
		this.maintainCustomFieldsList = maintainCustomFieldsList;
	}

	/**
	 * @return Returns the scopeList.
	 */
	public List getScopeList() {
		return scopeList;
	}

	/**
	 * @param scopeList
	 *            The scopeList to set.
	 */
	public void setScopeList(List scopeList) {
		this.scopeList = scopeList;
	}

	/**
	 * @return Returns the customFieldVO.
	 */
	public CustomFieldVO getCustomFieldVO() {
		return customFieldVO;
	}

	/**
	 * @param customFieldVO
	 *            The customFieldVO to set.
	 */
	public void setCustomFieldVO(CustomFieldVO customFieldVO) {
		this.customFieldVO = customFieldVO;
	}

	/**
	 * @return Returns the customFieldList.
	 */
	/*public List getCustomFieldList() {
		return customFieldList;
	}*/

	/**
	 * @param customFieldList
	 *            The customFieldList to set.
	 */
	/*public void setCustomFieldList(List customFieldList) {
		this.customFieldList = customFieldList;
	}*/

	/**
	 * @return Returns the dropDownList.
	 */
	public List getDropDownList() {
		return dropDownList;
	}

	/**
	 * @param dropDownList
	 *            The dropDownList to set.
	 */
	public void setDropDownList(List dropDownList) {
		this.dropDownList = dropDownList;
	}

	/**
	 * @return Returns the dropDownListData.
	 *//*
	public boolean isDropDownListData() {
		return dropDownListData;
	}

	*//**
	 * @param dropDownListData
	 *            The dropDownListData to set.
	 *//*
	public void setDropDownListData(boolean dropDownListData) {
		this.dropDownListData = dropDownListData;
	}
*/	
	/**
	 * @return Returns the editDropDownMenuFlag.
	 */
	public boolean isEditDropDownMenuFlag() {
		return editDropDownMenuFlag;
	}
	/**
	 * @param editDropDownMenuFlag The editDropDownMenuFlag to set.
	 */
	public void setEditDropDownMenuFlag(boolean editDropDownMenuFlag) {
		this.editDropDownMenuFlag = editDropDownMenuFlag;
	}
	
	
	
	/**
	 * @return Returns the editDropDownInfoFlag.
	 */
	public boolean isEditDropDownInfoFlag() {
		return editDropDownInfoFlag;
	}
	/**
	 * @param editDropDownInfoFlag The editDropDownInfoFlag to set.
	 */
	public void setEditDropDownInfoFlag(boolean editDropDownInfoFlag) {
		this.editDropDownInfoFlag = editDropDownInfoFlag;
	}
	/**
	 * @return Returns the editDropDownFlag.
	 */
	public boolean isEditDropDownFlag() {
		return editDropDownFlag;
	}

	/**
	 * @param editDropDownFlag
	 *            The editDropDownFlag to set.
	 */
	public void setEditDropDownFlag(boolean editDropDownFlag) {
		this.editDropDownFlag = editDropDownFlag;
	}

	/**
	 * @return Returns the dropDownIndex.
	 */
	public String getDropDownIndex() {
		return dropDownIndex;
	}

	/**
	 * @param dropDownIndex
	 *            The dropDownIndex to set.
	 */
	public void setDropDownIndex(String dropDownIndex) {
		this.dropDownIndex = dropDownIndex;
	}

	/**
	 * @return Returns the showSuccessMsgFlag.
	 */
	public boolean isShowSuccessMsgFlag() {
		return showSuccessMsgFlag;
	}

	/**
	 * @param showSuccessMsgFlag
	 *            The showSuccessMsgFlag to set.
	 */
	public void setShowSuccessMsgFlag(boolean showSuccessMsgFlag) {
		this.showSuccessMsgFlag = showSuccessMsgFlag;
	}

	/**
	 * @return Returns the dropDownDataFlag.
	 */
	public boolean isDropDownDataFlag() {
		return dropDownDataFlag;
	}

	/**
	 * @param dropDownDataFlag
	 *            The dropDownDataFlag to set.
	 */
	public void setDropDownDataFlag(boolean dropDownDataFlag) {
		this.dropDownDataFlag = dropDownDataFlag;
	}

	/**
	 * @return Returns the addDropDownFlag.
	 */
	public boolean isAddDropDownFlag() {
		return addDropDownFlag;
	}

	/**
	 * @param addDropDownFlag
	 *            The addDropDownFlag to set.
	 */
	public void setAddDropDownFlag(boolean addDropDownFlag) {
		this.addDropDownFlag = addDropDownFlag;
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
	 * @return Returns the showAddCustomFields.
	 */
	public boolean isShowAddCustomFields() {
		return showAddCustomFields;
	}

	/**
	 * @param showAddCustomFields
	 *            The showAddCustomFields to set.
	 */
	public void setShowAddCustomFields(boolean showAddCustomFields) {
		this.showAddCustomFields = showAddCustomFields;
	}

	/**
	 * @return Returns the showEditCustomFields.
	 */
	public boolean isShowEditCustomFields() {
		return showEditCustomFields;
	}

	/**
	 * @param showEditCustomFields
	 *            The showEditCustomFields to set.
	 */
	public void setShowEditCustomFields(boolean showEditCustomFields) {
		this.showEditCustomFields = showEditCustomFields;
	}

	/**
	 * @return Returns the showSucessMessage.
	 */
	public boolean isShowSucessMessage() {
		return showSucessMessage;
	}

	/**
	 * @param showSucessMessage
	 *            The showSucessMessage to set.
	 */
	public void setShowSucessMessage(boolean showSucessMessage) {
		this.showSucessMessage = showSucessMessage;
	}

	/**
	 * @return Returns the dataFlag.
	 *//*
	public boolean isDataFlag() {
		return dataFlag;
	}

	*//**
	 * @param dataFlag
	 *            The dataFlag to set.
	 *//*
	public void setDataFlag(boolean dataFlag) {
		this.dataFlag = dataFlag;
	}
*/
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
	 * @return Returns the showDropDownScreenFlag.
	 */
	public boolean isShowDropDownScreenFlag() {
		return showDropDownScreenFlag;
	}

	/**
	 * @param showDropDownScreenFlag
	 *            The showDropDownScreenFlag to set.
	 */
	public void setShowDropDownScreenFlag(boolean showDropDownScreenFlag) {
		this.showDropDownScreenFlag = showDropDownScreenFlag;
	}

	/**
	 * This Method loads all ValiedValues.
	 */
	private void loadAllValiedValues() {
		
		InputCriteria inputCriteria = null;
		List list = new ArrayList();
		HashMap map = new HashMap();

		String validValueCodeDesc = null;
		dataTypeList = new ArrayList();
		activeList = new ArrayList();
		/*requiredList = new ArrayList();
		protectedList = new ArrayList();*/
		dataTypeList.add(new SelectItem("", ""));
		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = null;
		ReferenceDataListVO referenceDataListVO = null;

		//filling the drop down of functional area
		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_CF_DATA_TY_CD);
		list.add(inputCriteria);
		referenceDataSearch.setInputList(list);
		referenceServiceDelegate = new ReferenceServiceDelegate();

		referenceDataListVO = new ReferenceDataListVO();

		try {
			
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);
		} catch (ReferenceServiceRequestException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e1) {
			e1.printStackTrace();
			// TODO Auto-generated catch block
			
		}

		map = referenceDataListVO.getResponseMap();

		list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
				+ ReferenceServiceDataConstants.G_CF_DATA_TY_CD);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
			validValueCodeDesc = refVo.getValidValueCode() + "-"
					+ refVo.getShortDescription();
			dataTypeList.add(new SelectItem(refVo.getValidValueCode(),
					validValueCodeDesc));

		}
		setDataTypeList(dataTypeList);

		activeList.add(new SelectItem(
				MaintainContactManagementUIConstants.DEFAULTTRUE,
				MaintainContactManagementUIConstants.YESLABEL));
		activeList.add(new SelectItem(
				MaintainContactManagementUIConstants.DEFAULTFALSE,
				MaintainContactManagementUIConstants.NOLABEL));

		setActiveList(activeList);

		setRequiredList(activeList);

		setProtectedList(activeList);
	
	
	}

	/**
	 * This Method Get the all Scope values.
	 */
	public void loadScope() {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		List list = null;
		List scope = new ArrayList();
		try {
			list = contactMaintenanceDelegate.getScopeValues();
			scope.add(new SelectItem("", ""));
		} catch (CustomFieldFetchBusinessException e) {
			if(log.isErrorEnabled())
			{
				log.error(e.getMessage(), e);
			}
		}
		int size = list.size();
		for (int i = 0; i < size; i++) {
			CustomFieldTable refVo = (CustomFieldTable) list.get(i);
			/*
			 * Change done for ES2 DO changes
			 */
			scope.add(new SelectItem(refVo.getTableName(), refVo
					.getDescription()));

		}
		scope.add(new SelectItem("B", "BOTH"));
		setScopeList(scope);
	}
	/**
	 * @return Returns the cfdropdownFalg.
	 *//*
	public boolean isCfdropdownFalg() {
		return cfdropdownFalg;
	}
	*//**
	 * @param cfdropdownFalg The cfdropdownFalg to set.
	 *//*
	public void setCfdropdownFalg(boolean cfdropdownFalg) {
		this.cfdropdownFalg = cfdropdownFalg;
	}
	*//**
	 * @return Returns the ddFlag.
	 */
	public boolean isDdFlag() {
		return ddFlag;
	}
	/**
	 * @param ddFlag The ddFlag to set.
	 */
	public void setDdFlag(boolean ddFlag) {
		this.ddFlag = ddFlag;
	}
	/**
	 * @return Returns the ddSaveFlag.
	 */
	public boolean isDdSaveFlag() {
		return ddSaveFlag;
	}
	/**
	 * @param ddSaveFlag The ddSaveFlag to set.
	 */
	public void setDdSaveFlag(boolean ddSaveFlag) {
		this.ddSaveFlag = ddSaveFlag;
	}
	/**
	 * @return Returns the flagFordd.
	 */
	public boolean isFlagFordd() {
		return flagFordd;
	}
	/**
	 * @param flagFordd The flagFordd to set.
	 */
	public void setFlagFordd(boolean flagFordd) {
		this.flagFordd = flagFordd;
	}
	/**
	 * @return Returns the showCFforAud.
	 */
	public boolean isShowCFforAud() {
		return showCFforAud;
	}
	/**
	 * @param showCFforAud The showCFforAud to set.
	 */
	public void setShowCFforAud(boolean showCFforAud) {
		this.showCFforAud = showCFforAud;
	}
	/**
	 * @return Returns the showCFforAuddropdown.
	 */
	public boolean isShowCFforAuddropdown() {
		return showCFforAuddropdown;
	}
	/**
	 * @param showCFforAuddropdown The showCFforAuddropdown to set.
	 */
	public void setShowCFforAuddropdown(boolean showCFforAuddropdown) {
		this.showCFforAuddropdown = showCFforAuddropdown;
	}
	
	/**
	 * @return Returns the lengthFlag.
	 */
	public boolean isLengthFlag() {
		return lengthFlag;
	}
	/**
	 * @param lengthFlag The lengthFlag to set.
	 */
	public void setLengthFlag(boolean lengthFlag) {
		this.lengthFlag = lengthFlag;
	}

	/**
	 * Holds temporary dropDownList.
	 * to fix dropdown list reset issue
	 */
	private List tempDropDownList = new ArrayList();


	/**
	 * @return the tempDropDownList
	 */
	public List getTempDropDownList() {
		return tempDropDownList;
	}
	/**
	 * @param tempDropDownList the tempDropDownList to set
	 */
	public void setTempDropDownList(List tempDropDownList) {
		this.tempDropDownList = tempDropDownList;
	}
	



	public int getDdstartIndex() {
		return ddstartIndex;
	}
	public void setDdstartIndex(int ddstartIndex) {
		this.ddstartIndex = ddstartIndex;
	} 
}
