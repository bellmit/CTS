/*
 * Created on Jul 7, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.List;

//import javax.faces.component.html.HtmlDataTable;

import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.common.vo.MaintainTemplateCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterKeywordVO;

/**
 * This class represents the keyword details populated in the presentation
 * layer.
 * 
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class MaintainKeywordsDataBean extends EnterpriseBaseDataBean {

	/**
	 * Holds renderAddKeyword
	 */
	private boolean renderAddKeyword = false;

	/**
	 * Holds renderEditKeyword
	 */
	private boolean renderEditKeyword = false;
	// Object initialized as part of defect ESPRD00907691
	private MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = new MaintainTemplateCriteriaVO();

	public MaintainTemplateCriteriaVO getMaintainTemplateCriteriaVO() {
		return maintainTemplateCriteriaVO;
	}

	public void setMaintainTemplateCriteriaVO(
			MaintainTemplateCriteriaVO maintainTemplateCriteriaVO) {
		this.maintainTemplateCriteriaVO = maintainTemplateCriteriaVO;
	}

	/**
	 * Holds showResultsDiv
	 */
	private boolean showResultsDiv = true;

	/**
	 * Holds letterKeywordVO
	 */
	private LetterKeywordVO letterKeywordVO = null;

	/**
	 * Holds showSuccessMessage
	 */
	private boolean showSuccessMessage = false;
	
	/*added as part of CR 827474*/
	private List keywordDataTypesList =  new ArrayList();
	

	/**
	 * Holds keywordTypesList
	 */
	private List keywordTypesList = null;

	/**
	 * The below attribute is to hold all non-void letter templates. This will
	 * be loaded just once when user clicks to "Add Letter Keyword"
	 */
	private List nonVoidLetterTemplatesList = new ArrayList();

	/**
	 * Holds renderKeywordDetailsPage
	 */
	private boolean renderKeywordDetailsPage = false;

	/**
	 * Holds selectedTemplatesFromAvailable
	 */
	private List selectedTemplatesFromAvailable = null;

	/**
	 * Holds selectedTemplatesFromAssociated
	 */
	private List selectedTemplatesFromAssociated = null;

	/**
	 * Holds availableTemplatesAsSelectItems
	 */
	private List availableTemplatesAsSelectItems = new ArrayList();

	/**
	 * Holds associatedTemplatesAsSelectItems
	 */
	private List associatedTemplatesAsSelectItems = new ArrayList();

	/**
	 * Holds selectedRow
	 */
	private int selectedRow = -1;

	/**
	 * This field is used to store category data table binding.
	 */
	// Commented property 'keywordHtmlDataTable' as part HeapDump Issue #191
	// private transient HtmlDataTable keywordHtmlDataTable;
	/**
	 * This field is used to store list of Category VOs.
	 */
	private List keywordList = new ArrayList(
			ContactManagementConstants.DEFAULT_SIZE);

	/** Used for initial cursor focus */
	private String initialCursorFocusValue = "";

	/**
	 * This field is used for rendering the sorting images.
	 */
	private String imageRender = "keywordIdAscCommandLink";

	/**
	 * Constructor for CategoryDataBean
	 */
	public MaintainKeywordsDataBean() {
		super();
		// keywordHtmlDataTable = new HtmlDataTable();
		// nonVoidLetterTemplatesList = new ArrayList();
		// availableTemplatesAsSelectItems = new ArrayList();
		// associatedTemplatesAsSelectItems = new ArrayList();
	}

	public boolean dataBeanFlag = true;

	public boolean isDataBeanFlag() {
		return dataBeanFlag;
	}

	public void setDataBeanFlag(boolean dataBeanFlag) {
		this.dataBeanFlag = dataBeanFlag;
	}

	/**
	 * @return Returns the keywordVO.
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform
	 *            )"
	 */
	public LetterKeywordVO getLetterKeywordVO() {
		return letterKeywordVO;
	}

	/**
	 * @param keywordVO
	 *            The keywordVO to set.
	 */
	public void setLetterKeywordVO(LetterKeywordVO keywordVO) {
		letterKeywordVO = keywordVO;
	}

	/**
	 * @return Returns the renderAddKeyword.
	 */
	public boolean isRenderAddKeyword() {
		return renderAddKeyword;
	}

	/**
	 * @param renderAddKeyword
	 *            The renderAddKeyword to set.
	 */
	public void setRenderAddKeyword(boolean renderAddKeyword) {
		this.renderAddKeyword = renderAddKeyword;
	}

	/**
	 * @return Returns the renderEditKeyword.
	 */
	public boolean isRenderEditKeyword() {
		return renderEditKeyword;
	}

	/**
	 * @param renderEditKeyword
	 *            The renderEditKeyword to set.
	 */
	public void setRenderEditKeyword(boolean renderEditKeyword) {
		this.renderEditKeyword = renderEditKeyword;
	}

	private int startIndexForCatgry = 0;

	/**
	 * @return Returns the keywordHtmlDataTable.
	 */
	/*
	 * public HtmlDataTable getKeywordHtmlDataTable() { return
	 * keywordHtmlDataTable; }
	 */

	/**
	 * @param keywordHtmlDataTable
	 *            The keywordHtmlDataTable to set.
	 */
	/*
	 * public void setKeywordHtmlDataTable(HtmlDataTable keywordHtmlDataTable) {
	 * this.keywordHtmlDataTable = keywordHtmlDataTable; }
	 */

	public int getStartIndexForCatgry() {
		return startIndexForCatgry;
	}

	public void setStartIndexForCatgry(int startIndexForCatgry) {
		this.startIndexForCatgry = startIndexForCatgry;
	}

	/**
	 * @return Returns the keywordList.
	 */
	public List getKeywordList() {
		this.showResultsDiv = (this.keywordList != null && this.keywordList
				.size() > 0);
		return keywordList;
	}

	/**
	 * @param keywordList
	 *            The keywordList to set.
	 */
	public void setKeywordList(List keywordList) {
		this.keywordList = keywordList;
	}

	/**
	 * @return Returns the showResultsDiv.
	 */
	public boolean isShowResultsDiv() {
		return showResultsDiv;
	}

	/**
	 * @param showResultsDiv
	 *            The showResultsDiv to set.
	 */
	public void setShowResultsDiv(boolean showResultsDiv) {
		this.showResultsDiv = showResultsDiv;
	}

	/**
	 * @return Returns the keywordTypesList.
	 */
	public List getKeywordTypesList() {
		return keywordTypesList;
	}

	/**
	 * @param keywordTypesList
	 *            The keywordTypesList to set.
	 */
	public void setKeywordTypesList(List keywordTypesList) {

		this.keywordTypesList = keywordTypesList;
	}

	/**
	 * @return Returns the nonVoidLetterTemplatesList.
	 */
	public List getNonVoidLetterTemplatesList() {
		return nonVoidLetterTemplatesList;
	}

	/**
	 * @param nonVoidLetterTemplatesList
	 *            The nonVoidLetterTemplatesList to set.
	 */
	public void setNonVoidLetterTemplatesList(List nonVoidLetterTemplatesList) {
		this.nonVoidLetterTemplatesList = nonVoidLetterTemplatesList;
	}

	/**
	 * @return Returns the renderKeywordDetailsPage.
	 */
	public boolean isRenderKeywordDetailsPage() {
		return renderKeywordDetailsPage;
	}

	/**
	 * @param renderKeywordDetailsPage
	 *            The renderKeywordDetailsPage to set.
	 */
	public void setRenderKeywordDetailsPage(boolean renderKeywordDetailsPage) {
		this.renderKeywordDetailsPage = renderKeywordDetailsPage;
	}

	/**
	 * @return Returns the showSuccessMessage.
	 */
	public boolean isShowSuccessMessage() {
		return showSuccessMessage;
	}

	/**
	 * @param showSuccessMessage
	 *            The showSuccessMessage to set.
	 */
	public void setShowSuccessMessage(boolean showSuccessMessage) {
		this.showSuccessMessage = showSuccessMessage;
	}

	/**
	 * @return Returns the selectedRow.
	 */
	public int getSelectedRow() {
		return selectedRow;
	}

	/**
	 * @param selectedRow
	 *            The selectedRow to set.
	 */
	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	/**
	 * @return Returns the selectedTemplatesFromAssociated.
	 */
	public List getSelectedTemplatesFromAssociated() {
		return selectedTemplatesFromAssociated;
	}

	/**
	 * @param selectedTemplatesFromAssociated
	 *            The selectedTemplatesFromAssociated to set.
	 */
	public void setSelectedTemplatesFromAssociated(
			List selectedTemplatesFromAssociated) {
		this.selectedTemplatesFromAssociated = selectedTemplatesFromAssociated;
	}

	/**
	 * @return Returns the selectedTemplatesFromAvailable.
	 */
	public List getSelectedTemplatesFromAvailable() {
		return selectedTemplatesFromAvailable;
	}

	/**
	 * @param selectedTemplatesFromAvailable
	 *            The selectedTemplatesFromAvailable to set.
	 */
	public void setSelectedTemplatesFromAvailable(
			List selectedTemplatesFromAvailable) {
		this.selectedTemplatesFromAvailable = selectedTemplatesFromAvailable;
	}

	/**
	 * @return Returns the associatedTemplatesAsSelectItems.
	 */
	public List getAssociatedTemplatesAsSelectItems() {
		return associatedTemplatesAsSelectItems;
	}

	/**
	 * @param associatedTemplatesAsSelectItems
	 *            The associatedTemplatesAsSelectItems to set.
	 */
	public void setAssociatedTemplatesAsSelectItems(
			List associatedTemplatesAsSelectItems) {
		this.associatedTemplatesAsSelectItems = associatedTemplatesAsSelectItems;
	}

	/**
	 * @return Returns the availableTemplatesAsSelectItems.
	 */
	public List getAvailableTemplatesAsSelectItems() {
		return availableTemplatesAsSelectItems;
	}

	/**
	 * @param availableTemplatesAsSelectItems
	 *            The availableTemplatesAsSelectItems to set.
	 */
	public void setAvailableTemplatesAsSelectItems(
			List availableTemplatesAsSelectItems) {
		this.availableTemplatesAsSelectItems = availableTemplatesAsSelectItems;
	}

	private boolean updateLtrKeywords = false;

	/**
	 * @return Returns the updateLtrKeywords.
	 */
	public boolean isUpdateLtrKeywords() {
		return updateLtrKeywords;
	}

	/**
	 * @param updateLtrKeywords
	 *            The updateLtrKeywords to set.
	 */
	public void setUpdateLtrKeywords(boolean updateLtrKeywords) {
		this.updateLtrKeywords = updateLtrKeywords;
	}

	private boolean auditLogFlag = false;

	/**
	 * @return Returns the auditLogFlag.
	 */
	public boolean isAuditLogFlag() {
		return auditLogFlag;
	}

	/**
	 * @param auditLogFlag
	 *            The auditLogFlag to set.
	 */
	public void setAuditLogFlag(boolean auditLogFlag) {
		this.auditLogFlag = auditLogFlag;
	}

	private String selectedIndex;

	/**
	 * @return Returns the selectedIndex.
	 */
	public String getSelectedIndex() {
		return selectedIndex;
	}

	/**
	 * @param selectedIndex
	 *            The selectedIndex to set.
	 */
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	/**
	 * @return Returns the initialCursorFocusValue.
	 */
	public String getInitialCursorFocusValue() {
		return initialCursorFocusValue;
	}

	/**
	 * @param initialCursorFocusValue
	 *            The initialCursorFocusValue to set.
	 */
	public void setInitialCursorFocusValue(String initialCursorFocusValue) {
		this.initialCursorFocusValue = initialCursorFocusValue;
	}

	/**
	 * @return the imageRender
	 */
	public String getImageRender() {
		return imageRender;
	}

	/**
	 * @param imageRender
	 *            the imageRender to set
	 */
	public void setImageRender(String imageRender) {
		this.imageRender = imageRender;
	}

	private EnterpriseSearchResultsVO enterpriseSearchResultsVO;

	public EnterpriseSearchResultsVO getEnterpriseSearchResultsVO() {
		return enterpriseSearchResultsVO;
	}

	public void setEnterpriseSearchResultsVO(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		this.enterpriseSearchResultsVO = enterpriseSearchResultsVO;
	}

	private int itemsPerPage = 10;

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	private int firstPage = 0;

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	/** holds the boolean variable to render the datatable. * */
	private boolean searchResultShow = false;

	/**
	 * @return Returns the searchResultShow.
	 */
	public boolean isSearchResultShow() {
		return searchResultShow;
	}

	/**
	 * @param searchResultShow
	 *            The searchResultShow to set.
	 */
	public void setSearchResultShow(boolean searchResultShow) {
		this.searchResultShow = searchResultShow;
	}

	private boolean renderFirstPage = false;

	public boolean isRenderFirstPage() {
		return renderFirstPage;
	}

	public void setRenderFirstPage(boolean renderFirstPage) {
		this.renderFirstPage = renderFirstPage;
	}

	private boolean renderFirstPagePlusOne = false;

	public boolean isRenderFirstPagePlusOne() {
		return renderFirstPagePlusOne;
	}

	public void setRenderFirstPagePlusOne(boolean renderFirstPagePlusOne) {
		this.renderFirstPagePlusOne = renderFirstPagePlusOne;
	}

	private boolean renderFirstPagePlusTwo = false;

	public boolean isRenderFirstPagePlusTwo() {
		return renderFirstPagePlusTwo;
	}

	public void setRenderFirstPagePlusTwo(boolean renderFirstPagePlusTwo) {
		this.renderFirstPagePlusTwo = renderFirstPagePlusTwo;
	}

	public boolean[] boldPageNum = { true, false, false };

	public boolean[] getBoldPageNum() {
		return boldPageNum;
	}

	public void setBoldPageNum(boolean[] boldPageNum) {
		this.boldPageNum = boldPageNum;
	}

	public List getKeywordDataTypesList() {
		return keywordDataTypesList;
	}

	public void setKeywordDataTypesList(List keywordDataTypesList) {
		this.keywordDataTypesList = keywordDataTypesList;
	}
	
	
}
