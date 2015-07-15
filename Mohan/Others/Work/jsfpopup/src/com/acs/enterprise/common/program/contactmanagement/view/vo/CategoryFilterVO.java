/*
 * Created on Dec 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;



/**
 * @author mohamabb
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CategoryFilterVO extends EnterpriseBaseVO
{

    /**
     * constructor for CategoryFilterVO.
     */
    public CategoryFilterVO()
    {
    	super();
    	EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CategoryFilterVO.class);
    	logger.debug("CategoryFilterVO");
    }
    
	/** Holds filterVO of type FilterVO */
    private FilterVO filterVO;

    /** Holds category of type CategoryVO */
    private CategoryVO category;

    /** Holds categorySK of type Long */
    private Long categorySK;

	/**
	 * @return Returns the category.
	 */
	public CategoryVO getCategory() 
	{
		return category;
	}
	/**
	 * @param category The category to set.
	 */
	public void setCategory(CategoryVO category)
	{
		this.category = category;
	}
	/**
	 * @return Returns the categorySK.
	 */
	public Long getCategorySK()
	{
		return categorySK;
	}
	/**
	 * @param categorySK The categorySK to set.
	 */
	public void setCategorySK(Long categorySK) 
	{
		this.categorySK = categorySK;
	}
	/**
	 * @return Returns the filterName.
	 */
	public String getFilterName() 
	{
		return filterName;
	}
	/**
	 * @param filterName The filterName to set.
	 */
	public void setFilterName(String filterName) 
	{
		this.filterName = filterName;
	}
	/**
	 * @return Returns the filterVO.
	 */
	public FilterVO getFilterVO()
	{
		return filterVO;
	}
	/**
	 * @param filterVO The filterVO to set.
	 */
	public void setFilterVO(FilterVO filterVO) 
	{
		this.filterVO = filterVO;
	}
	
    /** Holds crFilterName of type String */
    private String filterName;
}
