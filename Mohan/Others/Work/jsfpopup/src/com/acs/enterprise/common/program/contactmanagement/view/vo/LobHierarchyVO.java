/*
 * Created on Dec 18, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author rajvaur TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class LobHierarchyVO
        extends AuditaleEnterpriseBaseVO
{
    /**
     * Constructor for LobHierarchyVO
     */
    public LobHierarchyVO()
    {
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(FilterVO.class);
        logger.debug("Constructor for LobHierarchyVO");
    }

    /**
     * @return Returns the lobDesc.
     */
    public String getLobDesc()
    {
        return lobDesc;
    }

    /**
     * @param lobDesc
     *            The lobDesc to set.
     */
    public void setLobDesc(String lobDesc)
    {
        this.lobDesc = lobDesc;
    }

    /**
     * @return Returns the lobCode.
     */
    public String getLobCode()
    {
        return lobCode;
    }

    /**
     * @param lobCode
     *            The lobCode to set.
     */
    public void setLobCode(String lobCode)
    {
        this.lobCode = lobCode;
    }

    /**
     * Holds the lobDesc
     */
    private String lobDesc;
    
    /**
     * Holds the lobCode
     */
    private String lobCode;

    /**
     * Holds the levelNumber
     */
    private Integer levelNumber;

    /**
     * Holds the hierachySK
     */
    private Long hierachySK;

    /**
     * Holds the voidIndicator
     */
    //commented for unused variables
    //private boolean voidIndicator;

    /**
     * Holds the supervisorName
     */
    private Long supervisorName;

    /**
     * @return Returns the hierachySK.
     */
    public Long getHierachySK()
    {
        return hierachySK;
    }

    /**
     * @param hierachySK
     *            The hierachySK to set.
     */
    public void setHierachySK(Long hierachySK)
    {
        this.hierachySK = hierachySK;
    }

    /**
     * @return Returns the levelNumber.
     */
    public Integer getLevelNumber()
    {
        return levelNumber;
    }

    /**
     * @param levelNumber
     *            The levelNumber to set.
     */
    public void setLevelNumber(Integer levelNumber)
    {
        this.levelNumber = levelNumber;
    }

    /**
     * @return Returns the lobHieracParentVO.
     */
    public LobHierarchyVO getLobHieracParentVO()
    {
        return lobHieracParentVO;
    }

    /**
     * @param lobHieracParentVO
     *            The lobHieracParentVO to set.
     */
    public void setLobHieracParentVO(LobHierarchyVO lobHieracParentVO)
    {
        this.lobHieracParentVO = lobHieracParentVO;
    }

    /**
     * Holds the lobHieracParentVO
     */
    private LobHierarchyVO lobHieracParentVO;

    /**
     * @return Returns the voidIndicator.
     */
    /*public boolean isVoidIndicator()
    {
        return voidIndicator;
    }

    *//**
     * @param voidIndicator
     *            The voidIndicator to set.
     *//*
    public void setVoidIndicator(boolean voidIndicator)
    {
        this.voidIndicator = voidIndicator;
    }*/

    /**
     * @return Returns the supervisorName.
     */
    public Long getSupervisorName()
    {
        return supervisorName;
    }

    /**
     * @param supervisorName
     *            The supervisorName to set.
     */
    public void setSupervisorName(Long supervisorName)
    {
        this.supervisorName = supervisorName;
    }
}
