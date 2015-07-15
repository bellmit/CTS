/*
 * Created on Dec 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.List;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;

/**
 * @author sivngan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MaintainCustomFieldsDataBean extends EnterpriseBaseDataBean{

	
    /**
     * Constructor calls getReferenceData method.
     */
    /*public MaintainCustomFieldsDataBean()
    {
        super();
        try
        {
            getReferenceData();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/
    
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
    
    private List dropDownList = new ArrayList();
    
       
    /** Flag variable to show when there is no data in the dataTable*/
    private boolean dropDownListData = false;
    
    /** Flag variable to render add DropDownFlag*/
    private boolean addDropDownFlag = false;
    
    /** Flag variable to render edit DropDownFlag*/
    private boolean editDropDownFlag = false;
    
    /** Holds variable for maintaining the index of the record selected */
    private String dropDownIndex;
    
    /** Holds variable for rendering the success msg flag */
    private boolean showSuccessMsgFlag = false;
    
    /** Holds flag to display No Data if no data available in the data table */
    private boolean dropDownDataFlag = false;
    
    /**
     * Holds variable imageRenderer.
     */
    private String imageRender;
    
    /** This method is used to load valid values */

  /*  private void getReferenceData()
    {
    	InputCriteria inputCriteria = null;
    	List list = new ArrayList();
    	String validValueCodeAndDesc = null;
    	
    	HashMap map = new HashMap();
    	ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
    	
    	inputCriteria = new InputCriteria();
        inputCriteria.setFunctionalArea(FunctionalAreaConstants.REFERENCE);
        inputCriteria.setElementName(ReferenceServiceDataConstants.R_LOB_CD);
        list.add(inputCriteria);
        
        referenceDataSearch.setInputList(list);
        
        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);
        }
        catch (ReferenceServiceRequestException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SystemListNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        map = referenceDataListVO.getResponseMap();

        //scopeList
        list = (List) map.get(FunctionalAreaConstants.REFERENCE + "#"
                + ReferenceServiceDataConstants.R_LOB_CD);

        for (int i = 0; i < list.size(); i++)
        {
            ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
            validValueCodeAndDesc = refVo.getValidValueCode() + "-"
                    + refVo.getShortDescription();
            scopeList.add(new SelectItem(refVo.getValidValueCode(),
                    validValueCodeAndDesc));
        }
        setScopeList(scopeList);
    }*/
	/**
	 * @return Returns the scopeList.
	 */
	public List getScopeList() {
		return scopeList;
	}
	/**
	 * @param scopeList The scopeList to set.
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
	 * @param customFieldVO The customFieldVO to set.
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
	 * @param customFieldList The customFieldList to set.
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
	 * @param dropDownList The dropDownList to set.
	 */
	public void setDropDownList(List dropDownList) {
		this.dropDownList = dropDownList;
	}
	
	/**
	 * @return Returns the dropDownListData.
	 */
	public boolean isDropDownListData() {
		return dropDownListData;
	}
	/**
	 * @param dropDownListData The dropDownListData to set.
	 */
	public void setDropDownListData(boolean dropDownListData) {
		this.dropDownListData = dropDownListData;
	}
	/**
	 * @return Returns the editDropDownFlag.
	 */
	public boolean isEditDropDownFlag() {
		return editDropDownFlag;
	}
	/**
	 * @param editDropDownFlag The editDropDownFlag to set.
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
	 * @param dropDownIndex The dropDownIndex to set.
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
	 * @param showSuccessMsgFlag The showSuccessMsgFlag to set.
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
	 * @param dropDownDataFlag The dropDownDataFlag to set.
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
	 * @param addDropDownFlag The addDropDownFlag to set.
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
	 * @param imageRender The imageRender to set.
	 */
	public void setImageRender(String imageRender) {
		this.imageRender = imageRender;
	}
}
