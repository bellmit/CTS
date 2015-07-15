/*
 * Created on Jul 9, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.html.HtmlDataTable;

import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.common.vo.MaintainTemplateCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterTemplateVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author WIPRO This class is a data bean which holds the value objects and
 *         other data related to Maintenance Letter Template functionality.
 */

public class MaintainTemplateDataBean
        extends EnterpriseBaseDataBean
{
    /**
     * Enterprise logger
     */
    /*private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(MaintainTemplateDataBean.class);*/
	/* commented for unused variables
	 * private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(MaintainTemplateDataBean.class);*/

    /**
     * This field hold the LatterTemplate Value object.
     */

	public boolean dataBeanFlag = true;
	
	public boolean isDataBeanFlag() {
		return dataBeanFlag;
	}
	
	public void setDataBeanFlag(boolean dataBeanFlag) {
		this.dataBeanFlag = dataBeanFlag;
	}

    private LetterTemplateVO templateVO;
    private boolean fromPagination = false;
    public boolean isFromPagination() {
		return fromPagination;
	}

	public void setFromPagination(boolean fromPagination) {
		this.fromPagination = fromPagination;
	}
	/**
     * This field hold the the list of letter template Value objects
     */

    private List listOfTemplateVOs = null;
	/**
	 * This field hold the LatterTemplate Value object.
	 */
	private boolean allTemplates = true;

	public boolean isAllTemplates() {
		return allTemplates;
	}

	public void setAllTemplates(boolean allTemplates) {
		this.allTemplates = allTemplates;
	}
    /**
     * This field is used to store letter template data table binding
     */

    // Commented following property and methods as part of Heap Dump issue # 163
//    private transient HtmlDataTable templateHtmlDataTable = new HtmlDataTable();
	private int firstPage = 0;
	public int getFirstPage() {
		return firstPage;
	}
    public LetterTemplateVO getLetterTemplateVOEdit() {
		return LetterTemplateVOEdit;
	}

	public void setLetterTemplateVOEdit(LetterTemplateVO letterTemplateVOEdit) {
		LetterTemplateVOEdit = letterTemplateVOEdit;
	}
	private LetterTemplateVO LetterTemplateVOEdit;
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	private boolean renderFirstPage=false;
	public boolean isRenderFirstPage() {
		return renderFirstPage;
	}

	public void setRenderFirstPage(boolean renderFirstPage) {
		this.renderFirstPage = renderFirstPage;
	}
	private boolean renderFirstPagePlusOne=false;
	public boolean isRenderFirstPagePlusOne() {
		return renderFirstPagePlusOne;
	}

	public void setRenderFirstPagePlusOne(boolean renderFirstPagePlusOne) {
		this.renderFirstPagePlusOne = renderFirstPagePlusOne;
	}
	private boolean renderFirstPagePlusTwo=false;
	public boolean isRenderFirstPagePlusTwo() {
		return renderFirstPagePlusTwo;
	}

	public void setRenderFirstPagePlusTwo(boolean renderFirstPagePlusTwo) {
		this.renderFirstPagePlusTwo = renderFirstPagePlusTwo;
	}
	public  boolean[] boldPageNum ={true,false,false};
	public boolean[] getBoldPageNum() {
		return boldPageNum;
	}

	public void setBoldPageNum(boolean[] boldPageNum) {
		this.boldPageNum = boldPageNum;
	} 
    /**
     * This field is used to store showSucessMessage.
     */
    private boolean showSucessMessage = false;
    private int itemsPerPage = 10;
    public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	/** holds the boolean variable to render the datatable. * */
	private boolean searchResultShow = false;
	/**
	* @return Returns the searchResultShow.
	*/
	public boolean isSearchResultShow()
	{
	return searchResultShow;
	}

	/**
	* @param searchResultShow
	*            The searchResultShow to set.
	*/
	public void setSearchResultShow(boolean searchResultShow)
	{
	this.searchResultShow = searchResultShow;
	}
	/**
     * Holds the available list which display in UI
     */

    private List tempAvailableList = null;

    /**
     * Holds the associated list which display in UI
     */
private EnterpriseSearchResultsVO enterpriseSearchResultsVO;
    public EnterpriseSearchResultsVO getEnterpriseSearchResultsVO() {
		return enterpriseSearchResultsVO;
	}

	public void setEnterpriseSearchResultsVO(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		this.enterpriseSearchResultsVO = enterpriseSearchResultsVO;
	}
	private List tempAssociatedList = null;
    private MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = new MaintainTemplateCriteriaVO();
    
    public MaintainTemplateCriteriaVO getMaintainTemplateCriteriaVO() {
		return maintainTemplateCriteriaVO;
	}

	public void setMaintainTemplateCriteriaVO(
			MaintainTemplateCriteriaVO maintainTemplateCriteriaVO) {
		this.maintainTemplateCriteriaVO = maintainTemplateCriteriaVO;
	}

	/**
     * This field holds the renderEditTemplate to control display of edit
     * section
     */
    private boolean renderEditTemplate = false;

    /**
     * This field is used for rendering the sorting images.
     */
    private String imageRender = "templateAscCmdLink";

    /**
     * Holds the selected row index in UI
     */

    private int selectedTemplateIndex = 0;

    /**
     * This holds the removedList List.
     */
    private List removedList = new ArrayList();

    /**
     * This holds the selectedList List.
     */
    private List selectedList = new ArrayList();
    
    /** Used for initial cursor focus */ 
    private String initialCursorFocusValue = "";
    
    private int startIndexForCatgry=0;
    
    public int getStartIndexForCatgry() {
		return startIndexForCatgry;
	}

	public void setStartIndexForCatgry(int startIndexForCatgry) {
		this.startIndexForCatgry = startIndexForCatgry;
	}

	/**
     * Default constructor
     *
     */
    public MaintainTemplateDataBean(){}

    /**
     * @return Returns the listOfTemplateVOs.
     */
    public List getListOfTemplateVOs()
    {
        return listOfTemplateVOs;
    }

    /**
     * @param listOfTemplateVOs
     *            The listOfTemplateVOs to set.
     */
    public void setListOfTemplateVOs(List listOfTemplateVOs)
    {
        this.listOfTemplateVOs = listOfTemplateVOs;
    }

    /**
     * @return Returns the showSucessMessage.
     */
    public boolean isShowSucessMessage()
    {
        return showSucessMessage;
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
     * @return Returns the tempAvailableList.
     */
    public List getTempAvailableList()
    {
        return tempAvailableList;
    }

    /**
     * @param tempAvailableList
     *            The tempAvailableList to set.
     */
    public void setTempAvailableList(List tempAvailableList)
    {
        this.tempAvailableList = tempAvailableList;
    }

    /**
     * @return Returns the templateVO.
     */
    public LetterTemplateVO getTemplateVO()
    {
        return templateVO;
    }

    /**
     * @param templateVO
     *            The templateVO to set.
     */
    public void setTemplateVO(LetterTemplateVO templateVO)
    {
        this.templateVO = templateVO;
    }

    /**
     * @param renderEditTemplate
     *            The renderEditTemplate to set.
     */
    public void setRenderEditTemplate(boolean renderEditTemplate)
    {
        this.renderEditTemplate = renderEditTemplate;
    }

    /**
     * @return Returns the renderEditTemplate.
     */
    public boolean isRenderEditTemplate()
    {
        return renderEditTemplate;
    }

    /**
     * @return Returns the templateHtmlDataTable.
     */
    /*public HtmlDataTable getTemplateHtmlDataTable()
    {
        return templateHtmlDataTable;
    }*/

    /**
     * @param templateHtmlDataTable
     *            The templateHtmlDataTable to set.
     */
    /*public void setTemplateHtmlDataTable(HtmlDataTable templateHtmlDataTable)
    {
        this.templateHtmlDataTable = templateHtmlDataTable;
    }*/

    /**
     * @return Returns the selectedTemplateIndex.
     */
    public int getSelectedTemplateIndex()
    {
        return selectedTemplateIndex;
    }

    /**
     * @param selectedTemplateIndex
     *            The selectedTemplateIndex to set.
     */
    public void setSelectedTemplateIndex(int selectedTemplateIndex)
    {
        this.selectedTemplateIndex = selectedTemplateIndex;
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
     * @return Returns the tempAssociatedList.
     */
    public List getTempAssociatedList()
    {
        return tempAssociatedList;
    }

    /**
     * @param tempAssociatedList
     *            The tempAssociatedList to set.
     */
    public void setTempAssociatedList(List tempAssociatedList)
    {
        this.tempAssociatedList = tempAssociatedList;
    }

    private boolean updateLtrTemplates = false ;
    
	/**
	 * @return Returns the updateLtrTemplates.
	 */
	public boolean isUpdateLtrTemplates() {
		return updateLtrTemplates;
	}
	/**
	 * @param updateLtrTemplates The updateLtrTemplates to set.
	 */
	public void setUpdateLtrTemplates(boolean updateLtrTemplates) {
		this.updateLtrTemplates = updateLtrTemplates;
	}
	
	/**
	 * below varibale is defined for displaying due date off set number value instead of VO value in jsp 
	 * defect ESPRD00377115
	 */
	private String dueDtOffsetNum ;
	
		/**
	 * @return Returns the dueDtOffsetNum.
	 */
	public String getDueDtOffsetNum() {
		return dueDtOffsetNum;
	}
	/**
	 * @param dueDtOffsetNum The dueDtOffsetNum to set.
	 */
	public void setDueDtOffsetNum(String dueDtOffsetNum) {
		this.dueDtOffsetNum = dueDtOffsetNum;
	}
	private boolean auditLogFlag = false;
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
	private String selectedIndex;
	/**
	 * @return Returns the selectedIndex.
	 */
	public String getSelectedIndex() {
		return selectedIndex;
	}
	/**
	 * @param selectedIndex The selectedIndex to set.
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
	 * @param initialCursorFocusValue The initialCursorFocusValue to set.
	 */
	public void setInitialCursorFocusValue(String initialCursorFocusValue) {
		this.initialCursorFocusValue = initialCursorFocusValue;
	}
}
