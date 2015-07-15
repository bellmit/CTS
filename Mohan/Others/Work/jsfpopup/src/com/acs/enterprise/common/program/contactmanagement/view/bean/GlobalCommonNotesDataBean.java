/*
 * Created on Jun 9, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.List;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.common.vo.EntityNotesSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GlobalCommonNotesDataBean extends EnterpriseBaseDataBean
{
    /**
     * Generating object of EnterpriseLogger.
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(GlobalCommonNotesDataBean.class);
    
    /**
     * Generates a Variable to hold Bean Name.
     */
    public static final String BEAN_NAME = ContactManagementConstants.GLOBALCOMMON_NOTES_BEAN_NAME;
  
    
    /**
     * Constructor for CMEntitySearchDataBean
     */
    public GlobalCommonNotesDataBean()
    {
        super();
        getLoadRefernceData();
       
      logger.debug("inside const Of  GlobalCommonNotesDataBean");

    }
    
    
    /**
     * Generating Reference of EntityNotesSearchCriteriaVO .
     */
    private EntityNotesSearchCriteriaVO  entityNotesSearchCriteriaVO = new EntityNotesSearchCriteriaVO();

    
    /**
     * List to hold values for Dropdown for Entity Type.
     */
  
    private  List entityDropDownList = new ArrayList();

    /**
     * List to hold values for Dropdown for Entity ID Type.
     */
  
    private  List entityIDTypeList = new ArrayList();
    
    /**
     * This variable is Used For IPC to Maintain Enity . The Geter method is
     * Called in maintainEnity Portlet.
     */
    private String loadRefernceData;
    
    
    /**
     * Variable to reneder Carrier Name field.
     */
    private Boolean renderSearchResult = Boolean.FALSE;
    
    /**
     * List to hold Search Reslut.
     */
    
    private List searchResultsList = new ArrayList();
    
    /**
     * Variable used to render Image for Sorting purpose.
     */
    private String imageRender;
    
    /** holds the total number of records per page. * */
    private int itemsPerPage = ContactManagementConstants.NO_OF_RECORD_TO_FETCH;
    
    /** holds the showEntIDType */
    
    private boolean showEntIDType = false;
    /**
     * @return Returns the entityDropDownList.
     */
    public List getEntityDropDownList()
    {
        return entityDropDownList;
    }
    /**
     * @param entityDropDownList The entityDropDownList to set.
     */
    public void setEntityDropDownList(List entityDropDownList)
    {
        this.entityDropDownList = entityDropDownList;
    }
    /**
     * @return Returns the entityIDTypeList.
     */
    public List getEntityIDTypeList()
    {
        return entityIDTypeList;
    }
    /**
     * @param entityIDTypeList The entityIDTypeList to set.
     */
    public void setEntityIDTypeList(List entityIDTypeList)
    {
        this.entityIDTypeList = entityIDTypeList;
    }
    /**
     * @return Returns the loadRefernceData.
     */
   public String getLoadRefernceData()
    {
      
       CMEntityDOConvertor cmEnityDOConvertor = new CMEntityDOConvertor();
        

       /** Load Entity Type Drop Down */
        if (cmEnityDOConvertor.getEntityTypeReferenceData() != null)
        {
           
           /**
             * This method filters Out the Provider and member Valid avlues and
             * Populates the list
             */
            setEntityDropDownList(cmEnityDOConvertor
                    .getEntityTypeReferenceData());
           
        }
        return loadRefernceData;
    }
    /**
     * @param loadRefernceData The loadRefernceData to set.
     */
    public void setLoadRefernceData(String loadRefernceData)
    {
        this.loadRefernceData = loadRefernceData;
    }
    
    /**
     * @return Returns the entityNotesSearchCriteriaVO.
     */
    public EntityNotesSearchCriteriaVO getEntityNotesSearchCriteriaVO()
    {
        return entityNotesSearchCriteriaVO;
    }
    /**
     * @param entityNotesSearchCriteriaVO The entityNotesSearchCriteriaVO to set.
     */
    public void setEntityNotesSearchCriteriaVO(
            EntityNotesSearchCriteriaVO entityNotesSearchCriteriaVO)
    {
        this.entityNotesSearchCriteriaVO = entityNotesSearchCriteriaVO;
    }
    /**
     * @return Returns the renderSearchResult.
     */
    public Boolean getRenderSearchResult()
    {
        return renderSearchResult;
    }
    /**
     * @param renderSearchResult The renderSearchResult to set.
     */
    public void setRenderSearchResult(Boolean renderSearchResult)
    {
        this.renderSearchResult = renderSearchResult;
    }
    /**
     * @return Returns the searchResultsList.
     */
    public List getSearchResultsList()
    {
        return searchResultsList;
    }
    /**
     * @param searchResultsList The searchResultsList to set.
     */
    public void setSearchResultsList(List searchResultsList)
    {
        this.searchResultsList = searchResultsList;
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
     * @return Returns the itemsPerPage.
     */
    public int getItemsPerPage()
    {
        return itemsPerPage;
    }
    /**
     * @param itemsPerPage The itemsPerPage to set.
     */
    public void setItemsPerPage(int itemsPerPage)
    {
        this.itemsPerPage = itemsPerPage;
    }
    /**
     * @return Returns the showEntIDType.
     */
    public boolean isShowEntIDType()
    {
        return showEntIDType;
    }
    /**
     * @param showEntIDType The showEntIDType to set.
     */
    public void setShowEntIDType(boolean showEntIDType)
    {
        this.showEntIDType = showEntIDType;
    }
}
