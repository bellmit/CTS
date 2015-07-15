package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.contactmanagement.application.exception.FilterFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.FilterSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.FilterDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryFilterVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.FilterVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This is a controller class used for Filters related functionality
 * functionality in the presentation layer.
 *  
 */
public class FilterDataBean extends EnterpriseBaseDataBean 
{
    /**
	 * Generating object of EnterpriseLogger.
	 */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(FilterDataBean.class);
	
    /**
     * This is used to hold Former codes audit field history list for details
     * page.
     */
    private List filterAuditHistoryList = new ArrayList();
	
    /**
	 * This holds the filterTypeArrayList List.
	 */
	private List filterTypeArrayList = new ArrayList();
	
	/**
	 * Value will be set to true if the Audit link is click on the jsp page
	 */
	private boolean filterAuditRender = false;
	
    /**
     * This is used to hold the state of the Audit block open or closed.
     */
    private boolean auditOpen = false;
    
    private int filterstartIndex=0;
    

    /**
	 * @return the filterstartIndex
	 */
	public int getFilterstartIndex() {
		return filterstartIndex;
	}

	/**
	 * @param filterstartIndex the filterstartIndex to set
	 */
	public void setFilterstartIndex(int filterstartIndex) {
		this.filterstartIndex = filterstartIndex;
	}

	/**
     * Declated to hold the selected AuditLog.
     */
    private AuditLog selectedAuditLog;
    
    
	/**
	 * This holds the filterVOArrayList List.
	 */
	private List filterArrayList = new ArrayList();

	/**
	 * This holds the selectedList List.
	 */
	private List selectedList = new ArrayList();

	/**
	 * This holds the availableList List.
	 */
	private List availableList = new ArrayList();

	/**
	 * This holds the tempAvailableList List.
	 */
	private List tempAvailableList = new ArrayList();

	/**
	 * This holds the removedList List.
	 */
	private List removedList = new ArrayList();

	/**
	 * This holds the assignedList List.
	 */
	private List assignedList = new ArrayList();

	/**
	 * This holds the deletedList List.
	 */
	private List deletedList = new ArrayList();

	/**
	 * Holds the boolean cancelFilter List.
	 */
	private Boolean cancelFilter = Boolean.FALSE;

	/**
	 * Holds the boolean value.
	 */
	private Boolean showEditFilterBar = Boolean.FALSE;
	
	
	private Boolean showAuditLog = Boolean.FALSE;

	/**
	 * Holds the boolean value.
	 */
	private Boolean showNewFilterBar = Boolean.FALSE;

	/**
	 * Holds the boolean value.
	 */
	private Boolean showFilterDetails = Boolean.FALSE;

	/**
	 * Holds the boolean value.
	 */
	private boolean showSucessMessage = false;

	/**
	 * Holds the boolean value.
	 */
	private Boolean showCategoryList = Boolean.FALSE;

	/**
	 * Holds the boolean value.
	 */
	private Boolean showCaseList = Boolean.FALSE;
	
    /** holds the total number of records per page. */
    private int itemsPerPage = 10;
    
    /**
     * Holds the information about filter's scope CR or Case.
     */
    private String filterScope;

    
    
	/**
	 * @return Returns the showSucessMessage.
	 *//*
	public Boolean getShowSucessMessage()
	{
		return showSucessMessage;
	}

	*//**
	 * @param showSucessMessage
	 *            The showSucessMessage to set.
	 *//*
	public void setShowSucessMessage(Boolean showSucessMessage) 
	{
		this.showSucessMessage = showSucessMessage;
	}*/

	/**
	 * Holds the bean name.
	 *//*
	public static String beanName;*/

	/**
	 * Holds the CategoryFilterVO object.
	 */
	private CategoryFilterVO categoryFilterVO = null;

	/**
	 * Holds the FilterVO.
	 */
	private FilterVO tempFilterVO = null;

	/**
	 * Holds the int value.
	 */
	private int selectedFilterIndex = 0;

	/**
	 * Holds the boolean value.
	 */
	private Boolean editFilter = Boolean.FALSE;

	/**
	 * Holds the filter VO.
	 *  
	 */
	private FilterVO filterVO;
	
	 /**
     * Holds the information about filter's type.
     */
    private String filterType;

	/**
	 * Stores Reference for NoData.
	 *  
	 */
	private boolean noData = false;
	private boolean auditLogFlagforFilter = false;
	
	/**
	 * Holds the filterSearchCriteria VO.
	 *  
	 */
	private FilterSearchCriteriaVO filterSearchCriteriaVO = new FilterSearchCriteriaVO();
	
	/**
     * Variable used to render Image for Sorting purpose.
     */
    private String imageRender;

	/**
	 * Constructor for FilterDataBean.
	 */
	public FilterDataBean()
	{
		super();

		//filterArrayList = getFilterVOArrayList();
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
	 * @return Returns the availableList.
	 */
	public List getAvailableList()
	{
		return availableList;
	}

	/**
	 * @param availableList
	 *            The availableList to set.
	 */
	public void setAvailableList(List availableList) 
	{
		this.availableList = availableList;
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
	 * @return Returns the categoryFilterVO.
	 */
	public CategoryFilterVO getCategoryFilterVO() 
	{
		return categoryFilterVO;
	}

	/**
	 * @param categoryFilterVO
	 *            The categoryFilterVO to set.
	 */
	public void setCategoryFilterVO(CategoryFilterVO categoryFilterVO) 
	{
		this.categoryFilterVO = categoryFilterVO;
	}

	/**
	 * @return Returns the filterTypeArrayList.
	 */
	public List getFilterTypeArrayList() 
	{
		filterTypeArrayList = new ArrayList();
		filterTypeArrayList.add(new SelectItem(
				ContactManagementConstants.FILTER_TYPE_CASE,
				ContactManagementConstants.FILTER_TYPE_CASE));
		filterTypeArrayList.add(new SelectItem(
				ContactManagementConstants.FILTER_TYPE_CR,
				ContactManagementConstants.FILTER_TYPE_CR));
		return filterTypeArrayList;
	}

	/**
	 * @param filterTypeArrayList
	 *            The filterTypeArrayList to set.
	 */
	public void setFilterTypeArrayList(List filterTypeArrayList)
	{
	    
		this.filterTypeArrayList = filterTypeArrayList;
	}

	/**
	 * This method is used to get the filterVOArrayList.
	 * 
	 * @return ArrayList.
	 */

	public final List getFilterVOArrayList()
	{
		List filterVOArrayList = new ArrayList();
		List caseFilterVOArrayList =null;
		/*List crFilterVOArrayList =null;*/
		FilterSearchCriteriaVO filterSearchCriteriaVO = getFilterSearchCriteriaVO();
		FilterDOConvertor filterDOConvertor = new FilterDOConvertor();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try
		{
			Map filterMap = contactMaintenanceDelegate.getAllFilters();
			if(filterMap != null)
			{
				filterVOArrayList = filterDOConvertor.getFilterVOList
										((List) filterMap.get("CRFILTERLIST"),ContactManagementConstants.FILTER_TYPE_CR);
				caseFilterVOArrayList = filterDOConvertor.getFilterVOList
											((List) filterMap.get("CASEFILTERLIST"),ContactManagementConstants.FILTER_TYPE_CASE);
				
				if(filterVOArrayList !=null)
				{
					filterVOArrayList.addAll(caseFilterVOArrayList);
				}else{
					filterVOArrayList = caseFilterVOArrayList;
				}
			}
		} 
		catch (FilterFetchBusinessException e)
		{
			e.printStackTrace();
		}
		
		if (filterVOArrayList != null && filterVOArrayList.isEmpty())
		{
			this.noData = true;
		}
		filterComparator(filterSearchCriteriaVO.getSortColumn(),
					filterSearchCriteriaVO.isAscending(),filterVOArrayList);
		return filterVOArrayList;
	}

	/**
	 * @return Returns the showEditFilterBar.
	 */
	public Boolean getShowEditFilterBar() 
	{
		return showEditFilterBar;
	}

	/**
	 * @param showEditFilterBar
	 *            The showEditFilterBar to set.
	 */
	public void setShowEditFilterBar(Boolean showEditFilterBar) 
	{
		this.showEditFilterBar = showEditFilterBar;
	}

	/**
	 * @return Returns the showFilterDetails.
	 */
	public Boolean getShowFilterDetails()
	{

		return showFilterDetails;
	}

	/**
	 * @param showFilterDetails
	 *            The showFilterDetails to set.
	 */
	public void setShowFilterDetails(Boolean showFilterDetails) 
	{
		this.showFilterDetails = showFilterDetails;
	}

	/**
	 * @return Returns the showNewFilterBar.
	 */
	public Boolean getShowNewFilterBar()
	{
		return showNewFilterBar;
	}

	/**
	 * @param showNewFilterBar
	 *            The showNewFilterBar to set.
	 */
	public void setShowNewFilterBar(Boolean showNewFilterBar) 
	{
		this.showNewFilterBar = showNewFilterBar;
	}

	/**
	 * @param filterVOArrayList
	 *            The filterVOArrayList to set.
	 *//*
	public void setFilterVOArrayList(List filterVOArrayList)
	{
		this.filterVOArrayList = filterVOArrayList;
	}
*/
	/**
	 * @return Returns the filterVO.
	 */
	public FilterVO getFilterVO()
	{

		return filterVO;
	}

	/**
	 * @param filterVO
	 *            The filterVO to set.
	 */
	public void setFilterVO(FilterVO filterVO) 
	{

		this.filterVO = filterVO;
	}

	/**
	 * @return Returns the deletedList.
	 */
	public List getDeletedList()
	{
		return deletedList;
	}

	/**
	 * @param deletedList
	 *            The deletedList to set.
	 */
	public void setDeletedList(List deletedList) 
	{
		this.deletedList = deletedList;
	}

	/**
	 * @return Returns the selectedFilterIndex.
	 */
	public int getSelectedFilterIndex()
	{
		return selectedFilterIndex;
	}

	/**
	 * @param selectedFilterIndex
	 *            The selectedFilterIndex to set.
	 */
	public void setSelectedFilterIndex(int selectedFilterIndex)
	{
		this.selectedFilterIndex = selectedFilterIndex;
	}

	/**
	 * @return Returns the noData.
	 */
	public boolean isNoData()
	{
		return noData;
	}

	/**
	 * @param noData
	 *            The noData to set.
	 */
	public void setNoData(boolean noData) 
	{
		this.noData = noData;
	}

	/**
	 * @return Returns the tempFilterVO.
	 */
	public FilterVO getTempFilterVO() 
	{
		return tempFilterVO;
	}

	/**
	 * @param tempFilterVO
	 *            The tempFilterVO to set.
	 */
	public void setTempFilterVO(FilterVO tempFilterVO)
	{
		this.tempFilterVO = tempFilterVO;
	}

	/**
	 * @return Returns the editFilter.
	 */
	public Boolean getEditFilter() 
	{
		return editFilter;
	}

	/**
	 * @param editFilter
	 *            The editFilter to set.
	 */
	public void setEditFilter(Boolean editFilter)
	{
		this.editFilter = editFilter;
	}

	/**
	 * @return Returns the showCategoryList.
	 */
	public Boolean getShowCategoryList()
	{
		return showCategoryList;
	}

	/**
	 * @param showCategoryList
	 *            The showCategoryList to set.
	 */
	public void setShowCategoryList(Boolean showCategoryList) 
	{
		this.showCategoryList = showCategoryList;
	}

	/**
	 * @return Returns the showCaseList.
	 */
	public Boolean getShowCaseList() 
	{
		return showCaseList;
	}

	/**
	 * @param showCaseList
	 *            The showCaseList to set.
	 */
	public void setShowCaseList(Boolean showCaseList) 
	{
		this.showCaseList = showCaseList;
	}

	/**
	 * @return Returns the cancelFilter.
	 */
	public Boolean getCancelFilter() 
	{
		return cancelFilter;
	}

	/**
	 * @param cancelFilter
	 *            The cancelFilter to set.
	 */
	public void setCancelFilter(Boolean cancelFilter)
	{
		this.cancelFilter = cancelFilter;
	}
	
	/**
	 * @return Returns the showSucessMessage.
	 */
	public boolean isShowSucessMessage() 
	{
		return showSucessMessage;
	}
	
	/**
	 * @param showSucessMessage The showSucessMessage to set.
	 */
	public void setShowSucessMessage(boolean showSucessMessage) 
	{
		this.showSucessMessage = showSucessMessage;
	}
	
    /**
     * @return Returns the filterArrayList.
     */
    public List getFilterArrayList()
    {
        filterArrayList = getFilterVOArrayList();
        return filterArrayList;
    }
    
    /**
     * @param filterArrayList The filterArrayList to set.
     */
    public void setFilterArrayList(List filterArrayList)
    {
        this.filterArrayList = filterArrayList;
    }
    /**
     * @return Returns the filterSearchCriteriaVO.
     */
    public FilterSearchCriteriaVO getFilterSearchCriteriaVO()
    {
        return filterSearchCriteriaVO;
    }
    /**
     * @param filterSearchCriteriaVO The filterSearchCriteriaVO to set.
     */
    public void setFilterSearchCriteriaVO(
            FilterSearchCriteriaVO filterSearchCriteriaVO)
    {
        this.filterSearchCriteriaVO = filterSearchCriteriaVO;
    }
    /**
     * @return Returns the imageRender.
     */
    public String getImageRender()
    {
        return imageRender;
    }
    /**
     * @param imageRender The imageRender to set.
     */
    public void setImageRender(String imageRender)
    {
        this.imageRender = imageRender;
    }
	/**
	 * @return Returns the showAuditLog.
	 */
	public Boolean getShowAuditLog() {
		return showAuditLog;
	}
	/**
	 * @param showAuditLog The showAuditLog to set.
	 */
	public void setShowAuditLog(Boolean showAuditLog) {
		this.showAuditLog = showAuditLog;
	}
	/**
	 * @return Returns the filterAuditRender.
	 */
	public boolean isFilterAuditRender() {
		return filterAuditRender;
	}
	/**
	 * @param filterAuditRender The filterAuditRender to set.
	 */
	public void setFilterAuditRender(boolean filterAuditRender) {
		this.filterAuditRender = filterAuditRender;
	}
	/**
	 * @return Returns the filterAuditHistoryList.
	 */
	public List getFilterAuditHistoryList() {
		return filterAuditHistoryList;
	}
	/**
	 * @param filterAuditHistoryList The filterAuditHistoryList to set.
	 */
	public void setFilterAuditHistoryList(List filterAuditHistoryList) {
		this.filterAuditHistoryList = filterAuditHistoryList;
	}
	/**
	 * @return Returns the auditOpen.
	 */
	public boolean isAuditOpen() {
		return auditOpen;
	}
	/**
	 * @param auditOpen The auditOpen to set.
	 */
	public void setAuditOpen(boolean auditOpen) {
		this.auditOpen = auditOpen;
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
	 * @return Returns the auditLogFlagforFilter.
	 */
	public boolean isAuditLogFlagforFilter() {
		return auditLogFlagforFilter;
	}
	/**
	 * @param auditLogFlagforFilter The auditLogFlagforFilter to set.
	 */
	public void setAuditLogFlagforFilter(boolean auditLogFlagforFilter) {
		this.auditLogFlagforFilter = auditLogFlagforFilter;
	}
	/**
	 * @return Returns the filterType.
	 */
	public String getFilterType() {
		return filterType;
	}
	/**
	 * @param filterType The filterType to set.
	 */
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	/**
	 * @return Returns the filterScope.
	 */
	public String getFilterScope() {
		return filterScope;
	}
	/**
	 * @param filterScope The filterScope to set.
	 */
	public void setFilterScope(String filterScope) {
		this.filterScope = filterScope;
	}
	
	private void filterComparator(final String sortColumn,
            final boolean sortOrder, List dataList)
    {
       
        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                FilterVO data1 = (FilterVO) obj1;
                FilterVO data2 = (FilterVO) obj2;
                boolean ascending = false;
                if (sortOrder) {
					ascending = true;
				} else {
					ascending = false;
				}
               
				if (sortColumn == null) {
					return 0;
				}
                
				if (null == data1.getFilterName())
                {
                    data1
                            .setFilterName(ContactManagementConstants.EMPTY_STRING);
                }
                if (null == data2.getFilterName())
                {
                    data2
                            .setFilterName(ContactManagementConstants.EMPTY_STRING);
                }                  

                return ascending ? data1.getFilterName().toLowerCase().trim().compareTo(
                        data2.getFilterName().toLowerCase().trim()) : data2.getFilterName().toLowerCase().trim()
                        .compareTo(data1.getFilterName().toLowerCase().trim());          
            }
        };
        Collections.sort(dataList, comparator);
    }
	
	// Variable added for defect ESPRD00796737 
	/**
     * Variable used to hold the rowindex value in reset edit filter.
     */
	private int maintainfilterIndex=0;


	public int getMaintainfilterIndex() {
		return maintainfilterIndex;
	}

	public void setMaintainfilterIndex(int maintainfilterIndex) {
		this.maintainfilterIndex = maintainfilterIndex;
	}
	

}