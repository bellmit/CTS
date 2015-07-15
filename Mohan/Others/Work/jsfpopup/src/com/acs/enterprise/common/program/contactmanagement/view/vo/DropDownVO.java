/*
 * Created on Jan 2, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * @author sivngan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DropDownVO extends AuditaleEnterpriseBaseVO{
	
	private String dropDownItemDesc;
	
	private String sortOrder; 

	private String oldDropDownItemDesc;
	
	
    /**
     * @return Returns the oldDropDownItemDesc.
     */
    public String getOldDropDownItemDesc()
    {
        return oldDropDownItemDesc;
    }
    /**
     * @param oldDropDownItemDesc The oldDropDownItemDesc to set.
     */
    public void setOldDropDownItemDesc(String oldDropDownItemDesc)
    {
        this.oldDropDownItemDesc = oldDropDownItemDesc;
    }
	/**
	 * @return Returns the dropDownItemDesc.
	 */
	public String getDropDownItemDesc() {
		return dropDownItemDesc;
	}
	/**
	 * @param dropDownItemDesc The dropDownItemDesc to set.
	 */
	public void setDropDownItemDesc(String dropDownItemDesc) {
		this.dropDownItemDesc = dropDownItemDesc;
	}	
	
    /**
     * @return Returns the sortOrder.
     */
    public String getSortOrder()
    {
        return sortOrder;
    }
    /**
     * @param sortOrder The sortOrder to set.
     */
    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }
}
