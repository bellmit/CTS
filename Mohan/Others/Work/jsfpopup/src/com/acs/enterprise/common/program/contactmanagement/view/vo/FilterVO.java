/*
 * Created on Oct 3, 2007
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * Holds the information about the Filters.
 */
public class FilterVO
        extends AuditaleEnterpriseBaseVO
{
    /**
     * constructor for EDMSDefaultsDOConverter.
     */
    public FilterVO()
    {
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(FilterVO.class);
        logger.debug("FilterVO constructor");
    }

    /**
     * Holds the information about filter's description.
     */
    private String description;

    /**
     * Holds the information about filter's Name.
     */
    private String filterName;

    /**
     * @return Returns the filterName.
     */
    public String getFilterName()
    {
        return filterName;
    }

    /**
     * @param thefilterName
     *            The filterName to set.
     */
    public void setFilterName(String thefilterName)
    {
        filterName = thefilterName;
    }

    /**
     * Holds the information about filter's scope CR or Case.
     */
    private String filterScope;

    /**
     * @return Returns the filterScope.
     */
    public String getFilterScope()
    {
        return filterScope;
    }

    /**
     * @param thefilterScope
     *            The filterScope to set.
     */
    public void setFilterScope(String thefilterScope)
    {
        filterScope = thefilterScope;
    }

    /**
     * Holds the information about filter's type.
     */
    private String filterType;

    /**
     * Holds the information about selected categories.
     */
    private List categoryFilterVO = new ArrayList();

    /**
     * @return Returns the categoryFilterVO.
     */
    public List getCategoryFilterVO()
    {
        return categoryFilterVO;
    }

    /**
     * @param categoryFilterVO
     *            The categoryFilterVO to set.
     */
    public void setCategoryFilterVO(List categoryFilterVO)
    {
        this.categoryFilterVO = categoryFilterVO;
    }

    /**
     * Holds the information about selected casetypes.
     */
    private List caseTypeFilterVO = new ArrayList();

    /**
     * @return Returns the caseTypeFilterVO.
     */
    public List getCaseTypeFilterVO()
    {
        return caseTypeFilterVO;
    }

    /**
     * @param caseTypeFilterVO
     *            The caseTypeFilterVO to set.
     */
    public void setCaseTypeFilterVO(List caseTypeFilterVO)
    {
        this.caseTypeFilterVO = caseTypeFilterVO;
    }

    /**
     * @return Returns the filterType.
     */
    public String getFilterType()
    {
        return filterType;
    }

    /**
     * @param thefilterType
     *            The filterType to set.
     */
    public void setFilterType(String thefilterType)
    {
        filterType = thefilterType;
    }

    /**
     * Holds the information about filterTypeDataItem eg. category or case type.
     */
    private String filterTypeDataItem;

    /**
     * @return Returns the filterTypeDataItem.
     */
    public String getFilterTypeDataItem()
    {
        return filterTypeDataItem;
    }

    /**
     * @param thefilterTypeDataItem
     *            The filterTypeDataItem to set.
     */
    public void setFilterTypeDataItem(String thefilterTypeDataItem)
    {
        filterTypeDataItem = thefilterTypeDataItem;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Override equals method.
     * 
     * @param other
     *            Object.
     * @return boolean true/false.
     */
    public boolean equals(Object other)
    {
        boolean flag = false;
        if (this == other)
        {
            flag = true;
        }
        if (!(other instanceof FilterVO))
        {
            flag = false;
        }

        final FilterVO filterVO = (FilterVO) other;

        boolean sameFilterType = (filterVO.getFilterType() != null)
                && (getFilterType() != null)
                && filterVO.getFilterType().equals(getFilterType());

        boolean sameFilterScope = (filterVO.getFilterScope() != null)
                && (getFilterScope() != null)
                && filterVO.getFilterScope().equals(getFilterScope());
        boolean sameList = false;
        if(filterVO.getFilterType().equalsIgnoreCase(ContactManagementConstants.FILTER_TYPE_CR))
        {   
            sameList = isListOfCategoriesSame(filterVO);
        }
        if(filterVO.getFilterType().equalsIgnoreCase(ContactManagementConstants.FILTER_TYPE_CASE))
        {   
            sameList = isListOfCaseTypesSame(filterVO);
        }
        if (sameFilterType && sameFilterScope && sameList)
        {
            flag = true;
        }

        return flag;
    }

    /**
     * This method is to check the list of categories for equality.
     * 
     * @param filterVO
     *            The filterVO to set.
     * @return boolean.
     */
    private boolean isListOfCategoriesSame(FilterVO filterVO)
    {
        boolean sameList = false;

        boolean sameListSize = (filterVO.getCategoryFilterVO() != null)
                && (getCategoryFilterVO() != null)
                && (filterVO.getCategoryFilterVO().size() == getCategoryFilterVO()
                        .size());

        if (sameListSize)
        {
            sameList = matchCategories(getCategoryFilterVO(), filterVO
                    .getCategoryFilterVO());
        }

        return sameList;
    }

    /**
     * This method is to check the list of categories for equality.
     * 
     * @param filterVO
     *            The filterVO to set.
     * @return boolean.
     */
    private boolean isListOfCaseTypesSame(FilterVO filterVO)
    {
        boolean sameList = false;

        boolean sameListSize = (filterVO.getCaseTypeFilterVO() != null)
                && (getCaseTypeFilterVO() != null)
                && (filterVO.getCaseTypeFilterVO().size() == getCaseTypeFilterVO()
                        .size());

        if (sameListSize)
        {
            sameList = matchCaseTypes(getCaseTypeFilterVO(), filterVO
                    .getCaseTypeFilterVO());
        }

        return sameList;
    }

    /**
     * This method is used to match Categories.
     * 
     * @param categoryFilterVOList2
     *            The categoryFilterVOList2 to set.
     * @param categoryFilterVOList3
     *            The categoryFilterVOList3 to set.
     * @return boolean.
     */
    private boolean matchCategories(List categoryFilterVOList2,
            List categoryFilterVOList3)
    {
        int j = 0;
        boolean flag = false;
        for (Iterator iter = categoryFilterVOList2.iterator(); iter.hasNext();)
        {
            SelectItem categoryFilterVO = (SelectItem) iter.next();
            for (Iterator iterator = categoryFilterVOList3.iterator(); iterator
                    .hasNext();)
            {
                CategoryFilterVO categoryFilterVO2 = (CategoryFilterVO) iterator
                        .next();

                if (categoryFilterVO.getValue().toString().equals(
                        categoryFilterVO2.getCategorySK().toString()))
                {
                    j++;
                }
            }
        }

        if (j == categoryFilterVOList2.size())
        {
            flag = true;
        }
        return flag;
    }

    /**
     * This method is used to match Categories.
     * 
     * @param categoryFilterVOList2
     *            The categoryFilterVOList2 to set.
     * @param categoryFilterVOList3
     *            The categoryFilterVOList3 to set.
     * @return boolean.
     */
    private boolean matchCaseTypes(List caseTypeFilterVOList2,
            List caseTypeFilterVOList3)
    {
        int j = 0;
        boolean flag = false;
        for (Iterator iter = caseTypeFilterVOList2.iterator(); iter.hasNext();)
        {
            SelectItem caseTypeFilterVO = (SelectItem) iter.next();
            for (Iterator iterator = caseTypeFilterVOList3.iterator(); iterator
                    .hasNext();)
            {
                CaseFilterVO caseTypeFilterVO2 = (CaseFilterVO) iterator
                        .next();

                if (caseTypeFilterVO.getValue().toString().equals(
                        caseTypeFilterVO2.getCaseTypeSK().toString()))
                {
                    j++;
                }
            }
        }

        if (j == caseTypeFilterVOList2.size())
        {
            flag = true;
        }
        return flag;
    }

    /**
     * Override Hashcode method.
     * 
     * @return int hashCode value.
     */
    public int hashCode()
    {
        int result = 0;

        int filterTypeHashCode = (getFilterType() == null ? ContactManagementConstants.EMPTY_STRING
                : getFilterType().toString()).hashCode();
        int filterScopeHashCode = (getFilterScope() == null ? ContactManagementConstants.EMPTY_STRING
                : getFilterScope().toString()).hashCode();

        int listHashCode = getCategoryListHashCode();

        result = ContactManagementConstants.HASH_CODE_MULTIPLIER
                * (filterTypeHashCode + filterScopeHashCode + listHashCode);
        return result;
    }

    /**
     * @return int.
     */
    private int getCategoryListHashCode()
    {
        int listHashCode = 0;

        List listOfCategory = getCategoryFilterVO();

        if (listOfCategory != null)
        {
            for (Iterator iter = listOfCategory.iterator(); iter.hasNext();)
            {
                SelectItem item = (SelectItem) iter.next();

                listHashCode += item.getValue().toString().hashCode();
            }
        }

        return listHashCode;
    }
}
