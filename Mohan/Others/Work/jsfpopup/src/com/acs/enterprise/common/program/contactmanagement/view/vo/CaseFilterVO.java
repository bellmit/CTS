/*
 * Created on May 13, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;


/**
 * @author Wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class CaseFilterVO
        extends EnterpriseBaseVO
{

    

    /** Holds filterVO of type FilterVO */
    private FilterVO filterVO;

    /** Holds category of type CaseTypeVO */
    private CaseTypeVO caseTypeVO;

    /** Holds caseTypeSK of type Long */
    private Long caseTypeSK;

    /**
     * @return Returns the caseTypeVO.
     */
    public CaseTypeVO getCaseTypeVO()
    {
        return caseTypeVO;
    }

    /**
     * @param caseTypeVO
     *            The caseTypeVO to set.
     */
    public void setCaseTypeVO(CaseTypeVO caseTypeVO)
    {
        this.caseTypeVO = caseTypeVO;
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
     * @return Returns the filterName.
     */
    public String getFilterName()
    {
        return filterName;
    }

    /**
     * @param filterName
     *            The filterName to set.
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
     * @param filterVO
     *            The filterVO to set.
     */
    public void setFilterVO(FilterVO filterVO)
    {
        this.filterVO = filterVO;
    }

    /** Holds crFilterName of type String */
    private String filterName;
}
