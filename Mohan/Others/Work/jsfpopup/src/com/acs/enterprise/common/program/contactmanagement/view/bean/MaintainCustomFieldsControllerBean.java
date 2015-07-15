/*
 * Created on Dec 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.DropDownVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author sivngan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MaintainCustomFieldsControllerBean extends
		EnterpriseBaseControllerBean {

	/** Creates an instance of the logger. * */
// Serialization Issue (JSF 2.0 Migration) fix Starts
private static final EnterpriseLogger log = EnterpriseLogFactory.getLogger(MaintainCustomFieldsControllerBean.class);
//	private EnterpriseLogger log = EnterpriseLogFactory.getLogger(getClass()
//			.getName());
// Serialization Issue (JSF 2.0 Migration) fix Ends

	/**
     * Holds dropDownRowIndex of HtmlDataTable.
     */
    private HtmlDataTable dropDownRowIndex;
    
    
	/**
	 * This method is used to get the CustomFields Data Bean.
	 * 
	 * @return maintainCustomFieldsDataBean : maintainCustomFieldsDataBean object.
	 */
	public MaintainCustomFieldsDataBean getMaintainCustomFieldsDataBean() {
		
		FacesContext fc = FacesContext.getCurrentInstance();

		MaintainCustomFieldsDataBean maintainCustomFieldsDataBean = (MaintainCustomFieldsDataBean) fc
				.getApplication()
				.createValueBinding(
						ContactManagementConstants.BINDING_BEGIN_SEPARATOR
								+ ContactManagementConstants.MAINTAIN_CUSTOM_FIELD_DATA_BEAN_NAME
								+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc);

		return maintainCustomFieldsDataBean;
	}
	
	 /**
     * This method returns String to editDropDownTable
     * 
     * @return String
     */
    public String editDropDownTable()
    {
    	DropDownVO tempDropDownVO = new DropDownVO();
    	FacesContext fc = FacesContext.getCurrentInstance(); 
    	getMaintainCustomFieldsDataBean().setEditDropDownFlag(true);
    	getMaintainCustomFieldsDataBean().setAddDropDownFlag(false);
    	getMaintainCustomFieldsDataBean().setShowSuccessMsgFlag(false);
    	int intDropDownRowIndex = getDropDownRowIndex().getRowIndex();
        String index = new Integer(intDropDownRowIndex).toString();
        if (index != null)
        {
        	getMaintainCustomFieldsDataBean().setDropDownIndex(index);
        	DropDownVO dropDownVO = (DropDownVO) getMaintainCustomFieldsDataBean()
										.getDropDownList().get(intDropDownRowIndex);
        	try
			{
        		 BeanUtils.copyProperties(tempDropDownVO, dropDownVO);
			}
        	catch (IllegalAccessException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        catch (InvocationTargetException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
        }
        getMaintainCustomFieldsDataBean().getCustomFieldVO().setDropDownVO(tempDropDownVO);
    	return "success";
    }

    /**
     * This method returns String to addDropDownTable
     * 
     * @return String
     */
    public String addDropDownTable()
    {    	
    	getMaintainCustomFieldsDataBean().getCustomFieldVO().setDropDownVO(new DropDownVO());
    	getMaintainCustomFieldsDataBean().setAddDropDownFlag(true);
    	getMaintainCustomFieldsDataBean().setEditDropDownFlag(false);
    	getMaintainCustomFieldsDataBean().setShowSuccessMsgFlag(false);
    	return "success";
    }
    
	/**
	 * This method returns string to cancel Custom fields
	 * 
	 * @return String
	 */
	public String cancelCustomFields() {
		log.debug("cancelCustomFields called");
		return "success";
	}

	/**
	 * This method returns string to reset AddCustomfields table 
	 * 
	 * @return String
	 */
	public String resetAddCustomFields() {
		log.debug("resetAddCustomFields called");
		return "success";
	}

	/**
	 * This method returns string to reset EditCustomfields table 
	 * 
	 * @return String
	 */
	public String resetEditCustomFields() {
		log.debug("resetEditCustomFields called");
		return "success";
	}

	/**
	 * This method returns string to reset AddDropDown table 
	 * 
	 * @return String
	 */
	public String resetAddDropDown() 
	{
		log.debug("reset Add DropDown called");
		getMaintainCustomFieldsDataBean().getCustomFieldVO().setDropDownVO(new DropDownVO());
		return "success";
	}
	
	/**
	 * This method returns string to reset EditDropDown table 
	 * 
	 * @return String
	 */
	public String resetEditDropDown() 
	{
		log.debug("reset Edit DropDown called");
		DropDownVO dropDownVO = null;
		DropDownVO tempDropDownVO = new DropDownVO();
	    String dropDownIndex = getMaintainCustomFieldsDataBean()
								.getDropDownIndex();
	    dropDownVO = (DropDownVO) getMaintainCustomFieldsDataBean()
						.getDropDownList().get(Integer.parseInt(dropDownIndex));
	    try
		{
	    	BeanUtils.copyProperties(tempDropDownVO, dropDownVO);
	    }
	    catch (IllegalAccessException e)
		{
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
		}
	    catch (InvocationTargetException e)
		{
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
		}
	    getMaintainCustomFieldsDataBean().getCustomFieldVO().setDropDownVO(tempDropDownVO);
		return "success";
	}
	
	/**
	 * This method returns string to reset AddDropDown table 
	 * 
	 * @return String
	 */
	public String saveDropDown() 
	{
		log.debug("saveDropDown called");
		DropDownVO dropDownVO = getMaintainCustomFieldsDataBean().getCustomFieldVO().getDropDownVO();
		
		if(validateDropDownInfo(dropDownVO))
		{
			if(getMaintainCustomFieldsDataBean().isAddDropDownFlag())
			{
				if(dropDownVO != null)
				{
					getMaintainCustomFieldsDataBean().getDropDownList().add(dropDownVO);
				}
				DropDownVO tempDropDownVO = new DropDownVO();
				getMaintainCustomFieldsDataBean().getCustomFieldVO().setDropDownVO(tempDropDownVO);
				ContactManagementHelper.setErrorMessage("success.saved");
				getMaintainCustomFieldsDataBean().setShowSuccessMsgFlag(true);
				getMaintainCustomFieldsDataBean().setDropDownDataFlag(true);
			}
			else if(getMaintainCustomFieldsDataBean().isEditDropDownFlag())
			{
				DropDownVO tempDropDownVO;
				tempDropDownVO = getMaintainCustomFieldsDataBean().getCustomFieldVO().getDropDownVO();
				getMaintainCustomFieldsDataBean().getDropDownList().add(Integer.parseInt(getMaintainCustomFieldsDataBean()
						.getDropDownIndex()), tempDropDownVO);
				getMaintainCustomFieldsDataBean().getDropDownList().remove(Integer.parseInt(getMaintainCustomFieldsDataBean()
						.getDropDownIndex()) + 1);
				
				ContactManagementHelper.setErrorMessage("success.saved");
				getMaintainCustomFieldsDataBean().setShowSuccessMsgFlag(true);
				getMaintainCustomFieldsDataBean().setDropDownDataFlag(true);
			}
		}
		return "success";
	}
	
	 
    
	/**
	 * @return Returns the dropDownRowIndex.
	 */
	public HtmlDataTable getDropDownRowIndex()
	{
		return dropDownRowIndex;
	}
	/**
	 * @param dropDownRowIndex The dropDownRowIndex to set.
	 */
	public void setDropDownRowIndex(HtmlDataTable dropDownRowIndex)
	{
		this.dropDownRowIndex = dropDownRowIndex;
	}
	
	 /**
     * This method is used to sort CustomFields information based on sorted
     * column and order.
     * 
     * @param event
     *            ActionEvent
     * @return String
     */

    public String sortCustomFields(ActionEvent event)
    {
        log.debug("inside sortCustomFields");

        String sortColumn = (String) event.getComponent().getAttributes().get(
        		MaintainContactManagementUIConstants.CLOUMN_NAME);

        String sortOrder = (String) event.getComponent().getAttributes().get(
        		MaintainContactManagementUIConstants.SORT_ORDER);

        log.debug("The Sort column is--->" + sortColumn);
        log.debug("the sort order is--->" + sortOrder);

        getMaintainCustomFieldsDataBean().setImageRender(
                event.getComponent().getId());
        sortDDComparator(sortColumn, sortOrder,
        		getMaintainCustomFieldsDataBean().getDropDownList());

        return MaintainContactManagementUIConstants.SUCCESS;
    }

    /**
     * Comparator to the LTC list to sort the list according to the sort order
     * selected by the user.
     * 
     * @param sortColumn -
     *            Column to be sorted.
     * @param sortOrder -
     *            Order by which the column should be started.
     * @param dataList -
     *            List to be sorted.
     */
    private void sortDDComparator(final String sortColumn,
            final String sortOrder, List dataList)
    {
        log.debug("inside sortDDComparator");

        Comparator comparator = new Comparator()
        {
            public int compare(Object obj1, Object obj2)
            {
                DropDownVO data1 = (DropDownVO) obj1;
                DropDownVO data2 = (DropDownVO) obj2;
                boolean ascending = false;
                if (MaintainContactManagementUIConstants.SORT_TYPE_ASC.equals(sortOrder))
                {
                    ascending = true;
                }
                else
                {
                    ascending = false;
                }

                if (sortColumn == null)
                {
                    return 0;
                }
                if (MaintainContactManagementUIConstants.DD_ITEM_DESC.equals(sortColumn))
                {
                    if (null == data1.getDropDownItemDesc())
                    {
                        data1.setDropDownItemDesc(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == data2.getDropDownItemDesc())
                    {
                        data2.setDropDownItemDesc(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? data1.getDropDownItemDesc().trim().compareTo(
                            data2.getDropDownItemDesc().trim()) : data2.getDropDownItemDesc()
                            .trim().compareTo(data1.getDropDownItemDesc().trim());
                }
                if (MaintainContactManagementUIConstants.DD_SORT_ORDER.equals(sortColumn))
                {
                    if (null == data1.getSortOrder())
                    {
                        data1.setSortOrder(String.valueOf(MaintainContactManagementUIConstants.ZERO));
                    }
                    if (null == data2.getSortOrder())
                    {
                        data2.setSortOrder(String.valueOf(MaintainContactManagementUIConstants.ZERO));
                    }
                    return ascending ? data1.getSortOrder()
                            .compareTo(data2.getSortOrder())
                            : data2.getSortOrder().compareTo(
                                    data1.getSortOrder());
                }
                return 0;
            }

        };

        Collections.sort(dataList, comparator);

    }
    
    /**
     * This method is for validating the fields in DropDown Table
     * 
     * @param ltcSpansVO
     *            LTCSpansVO
     * @return boolean
     */

    public boolean validateDropDownInfo(DropDownVO dropDownVO)
    {
        boolean flag = true;
        
        String actionCode = null;
        if (getMaintainCustomFieldsDataBean().isAddDropDownFlag())
        {
            actionCode = MaintainContactManagementUIConstants.ADD;
        }
        else
        {
            actionCode = MaintainContactManagementUIConstants.UPDATE;
        }

        if (dropDownVO.getDropDownItemDesc() == null
                || dropDownVO.getDropDownItemDesc().length() == 0)
        {
            flag = false;
            ContactManagementHelper.setErrorMessage(MaintainContactManagementUIConstants.DD_DESC_REQ,
            		MaintainContactManagementUIConstants.ADD_DD_DESC_ID,
            		MaintainContactManagementUIConstants.EDIT_DD_DESC_ID, actionCode);
        }
        if (dropDownVO.getSortOrder() == null)
        {
            flag = false;
            ContactManagementHelper.setErrorMessage(MaintainContactManagementUIConstants.SORT_ORDER_REQ,
            		MaintainContactManagementUIConstants.ADD_SORT_ORDER_ID,
            		MaintainContactManagementUIConstants.EDIT_SORT_ORDER_ID, actionCode);
        }
        return flag;
    }

}
