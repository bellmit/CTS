/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CategorySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CategoryDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryCustomFieldsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategorySubjectVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;

/**
 * @author Wipro This class is a data bean which holds the value objects and
 *         other data related to add/update Maintenance Category functionality.
 */
public class CategoryDataBean
        extends EnterpriseBaseDataBean
{
  
    /**
     * Constructor for CategoryDataBean
     */
    public CategoryDataBean()
    {
        super();
    }
    
    /** for fixing defect ESPRD00587673
     * */
    private String focusId;
    
    

    /**
	 * @return the focusId
	 */
	public String getFocusId() {
		return focusId;
	}
	/**
	 * @param focusId the focusId to set
	 */
	public void setFocusId(String focusId) {
		this.focusId = focusId;
	}

	/** for fixing defect ESPRD00601585
     * */
    private int tabIndex;
    
    
	/**
	 * @return Returns the tabIndex.
	 */
	public int getTabIndex() {
		return tabIndex;
	}
	/**
	 * @param tabIndex The tabIndex to set.
	 */
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
    /**
     * This field is used to store categoryVO.
     */
    private CategoryVO categoryVO;
    
    /**
     * This field is used to store tempCategoryVO.
     */
    private CategoryVO tempCategoryVO;

    /**
     * This field is used to get valid values.
     */
    private String validValuesforBusiUnit;

    /**
     * This field is used to render subject details.
     */
    private boolean renderSubjectDetails = false;

    /**
     * This field is used to render CustomField details.
     */
    private boolean renderCustomFieldDetails = false;

    /**
     * This field is used to store CategorySubject details.
     */
    private CategorySubjectVO subjectVO;

    /**
     * This field is used to store categoryCustomFieldsVO details.
     */

    private CategoryCustomFieldsVO categoryCustomFieldsVO;

    /**
     * This field is used to store category Typecode data.
     */
    private List categoryTypeCodeListME;
    
    private boolean auditLogFlag = false;
    
    private boolean CategorySort = false;

    /**
     * This list is used to store tempaltes
     */
    private List listOfCategoryTemplateVOs = new ArrayList();

    /**
     * This field is used to store list of Category VOs.
     */
    private List listOfCategoryVOs = new ArrayList();

    /**
     * This field is used to store list Of Category Subject VOs.
     */
    private List listOfCategorySubVOs = new ArrayList();

    /**
     * This field is used to store list Of Category Custom Fields VOs.
     */
    private List listOfCategoryCustomFldVOs = new ArrayList();

    /**
     * This field is used to store list of Subject Valid Values.
     */
    private List refSubjectList = new ArrayList();

    /**
     * This field is used to store refPriorityList.
     */
    private List refPriorityList = new ArrayList();

    /**
     * This field is used to store refCustomFieldDataTypeList.
     */
    private List refCustomFieldDataTypeList = new ArrayList();

    /**
     * This field is used for rendering the sorting images.
     */
    private String imageRender;

    /**
     * This field is used to store renderNoDataCategory.
     */
    private boolean renderNoDataCategory = false;

    /**
     * This field is used to store renderNoDataCategoryTempalte. Added by
     * Madhurima
     */
    private boolean renderNoDataTemplate = false;

    /**
     * This field is used to store renderNoDataSub.
     */
    private boolean renderNoDataSub = true;

    /**
     * This field is used to store renderNoDataCF.
     */
    private boolean renderNoDataCF = true;

    /**
     * This field is used to store renderAddCategory.
     */
    private boolean renderAddCategory = false;

    /**
     * This field is used to store rendreEditCategory.
     */
    private boolean renderEditCategory = false;

    /**
     * This field is used to store showSucessMessage.
     */
    private boolean showSucessMessage = false;

    /**
     * This field is used to store category row index.
     */
    private int categoryRowIndex;

    /**
     * This field is used to store categorySearchCriteriaVO.
     */
    private CategorySearchCriteriaVO categorySearchCriteriaVO = new CategorySearchCriteriaVO();

    /**
     * This field is used to store mapOfDeletedSubjects.
     */
    //hash map performance issue code change
    //private Map mapOfDeletedSubjects = new HashMap();

    /**
     * This field is used to store mapOfDeletedCustomFields.
     */
    //hash map performance issue code change
    //private Map mapOfDeletedCustomFields = new HashMap();

    /**
     * This fiels used to store mapOfDeletedTempaltes
     */
    //hash map performance issue code change
    //private Map mapOfDeletedTemplates = new HashMap();

    /**
     * This is used to render Audit for Dormer cdodes for details page.
     */

    private boolean categoryAuditRender = false;

    /**
     * This is used to hold Former codes audit field history list for details
     * page.
     */
    private List categoryAuditHistoryList = new ArrayList();

    /**
     * This is used to hold the state of the Audit block open or closed.
     */
    private boolean auditOpen = false;
    
    /** holds the total number of records per page. */
    private int itemsPerPage = 10;

    private boolean categoryHistoryRender = false;
    
    /**
     * Declated to hold the selected AuditLog.
     */
    private AuditLog selectedAuditLog;

    /**
     * This is used to show column changes for details page.
     */
    private boolean auditColumnHistoryRender = false;

    /**
	 * @return Returns the categoryHistoryRender.
	 */
	public boolean isCategoryHistoryRender() {
		return categoryHistoryRender;
	}
	/**
	 * @param categoryHistoryRender The categoryHistoryRender to set.
	 */
	public void setCategoryHistoryRender(boolean categoryHistoryRender) {
		this.categoryHistoryRender = categoryHistoryRender;
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
     * This method is used to get the categoryVO.
     * 
     * @return CategoryVO : Returns the categoryVO.
     */
    public CategoryVO getCategoryVO()
    {
        return categoryVO;
    }

    /**
     * This method is used to set the categoryVO.
     * 
     * @param categoryVO :
     *            The categoryVO to set.
     */
    public void setCategoryVO(CategoryVO categoryVO)
    {
        this.categoryVO = categoryVO;
    }

    /**
     * This method is used to get the listOfCategoryVOs.
     * 
     * @return List : Returns the listOfCategoryVOs.
     */
    public List getListOfCategoryVOs()
    {
        //listOfCategoryVOs = getAllCategories(categorySearchCriteriaVO);
        return listOfCategoryVOs;
    }

    /**
     * This method is used to set the listOfCategoryVOs.
     * 
     * @param listOfCategoryVOs :
     *            The listOfCategoryVOs to set.
     */
    public void setListOfCategoryVOs(List listOfCategoryVOs)
    {
        this.listOfCategoryVOs = listOfCategoryVOs;
    }

    /**
     * This method is used to get the listOfCategorySubVOs.
     * 
     * @return List : Returns the listOfCategorySubVOs.
     */
    public List getListOfCategorySubVOs()
    {
        return listOfCategorySubVOs;
    }

    /**
     * This method is used to set the listOfCategorySubVOs.
     * 
     * @param listOfCategorySubVOs :
     *            The listOfCategorySubVOs to set.
     */
    public void setListOfCategorySubVOs(List listOfCategorySubVOs)
    {
        this.listOfCategorySubVOs = listOfCategorySubVOs;
    }

    /**
     * This method is used to get the listOfCategoryCustomFldVOs.
     * 
     * @return List : Returns the listOfCategoryCustomFldVOs.
     */
    public List getListOfCategoryCustomFldVOs()
    {
        return listOfCategoryCustomFldVOs;
    }

    /**
     * This method is used to set the listOfCategoryCustomFldVOs.
     * 
     * @param listOfCategoryCustomFldVOs :
     *            The listOfCategoryCustomFldVOs to set.
     */
    public void setListOfCategoryCustomFldVOs(List listOfCategoryCustomFldVOs)
    {
        this.listOfCategoryCustomFldVOs = listOfCategoryCustomFldVOs;
    }

    /**
     * This method is used to get the refSubjectList.
     * 
     * @return List : Returns the refSubjectList.
     */
    public List getRefSubjectList()
    {
        return refSubjectList;
    }

    /**
     * This method is used to set the refSubjectList.
     * 
     * @param refSubjectList :
     *            The refSubjectList to set.
     */
    public void setRefSubjectList(List refSubjectList)
    {
        this.refSubjectList = refSubjectList;
    }

    /**
     * This method is used to get the refPriorityList.
     * 
     * @return List : Returns the refPriorityList.
     */
    public List getRefPriorityList()
    {
        return refPriorityList;
    }

    /**
     * This method is used to set the refPriorityList.
     * 
     * @param refPriorityList :
     *            The refPriorityList to set.
     */
    public void setRefPriorityList(List refPriorityList)
    {
        this.refPriorityList = refPriorityList;
    }

    /**
     * This method is used to get the refCustomFieldDataTypeList.
     * 
     * @return List : Returns the refCustomFieldDataTypeList.
     */
    public List getRefCustomFieldDataTypeList()
    {
        return refCustomFieldDataTypeList;
    }

    /**
     * This method is used to set the refCustomFieldDataTypeList.
     * 
     * @param refCustomFieldDataTypeList :
     *            The refCustomFieldDataTypeList to set.
     */
    public void setRefCustomFieldDataTypeList(List refCustomFieldDataTypeList)
    {
        this.refCustomFieldDataTypeList = refCustomFieldDataTypeList;
    }

    /**
     * This method is used to get the imageRender.
     * 
     * @return String : Returns the imageRender.
     */
    public String getImageRender()
    {
        return imageRender;
    }

    /**
     * This method is used to set the imageRender.
     * 
     * @param imageRender :
     *            The imageRender to set.
     */
    public void setImageRender(String imageRender)
    {
        this.imageRender = imageRender;
    }

    /**
     * This method is used to get the Category Data Bean.
     * 
     * @return CategoryDataBean : CategoryDataBean object
     */
    public CategoryDataBean getCategoryDataBean()
    {

        FacesContext fc = FacesContext.getCurrentInstance();
        return ((CategoryDataBean) fc.getApplication().createValueBinding(
                ContactManagementConstants.BINDING_BEGIN_SEPARATOR
                        + ContactManagementConstants.CATEGORY_BEAN_NAME
                        + ContactManagementConstants.BINDING_END_SEPARATOR)
                .getValue(fc));
    }

    /**
     * This method is used to get the renderNoDataCategory.
     * 
     * @return boolean : Returns the renderNoDataCategory.
     */
    public boolean isRenderNoDataCategory()
    {
        return renderNoDataCategory;
    }

    /**
     * This method is used to set the renderNoDataCategory.
     * 
     * @param renderNoDataCategory :
     *            The renderNoDataCategory to set.
     */
    public void setRenderNoDataCategory(boolean renderNoDataCategory)
    {
        this.renderNoDataCategory = renderNoDataCategory;
    }

    /**
     * This method is used to get the renderNoDataSub.
     * 
     * @return boolean : Returns the renderNoDataSub.
     */
    public boolean isRenderNoDataSub()
    {
        return renderNoDataSub;
    }

    /**
     * This method is used to set the renderNoDataSub.
     * 
     * @param renderNoDataSub :
     *            The renderNoDataSub to set.
     */
    public void setRenderNoDataSub(boolean renderNoDataSub)
    {
        this.renderNoDataSub = renderNoDataSub;
    }

    /**
     * This method is used to get the renderNoDataCF.
     * 
     * @return boolean : Returns the renderNoDataCF.
     */
    public boolean isRenderNoDataCF()
    {
        return renderNoDataCF;
    }

    /**
     * This method is used to set the renderNoDataCF.
     * 
     * @param renderNoDataCF :
     *            The renderNoDataCF to set.
     */
    public void setRenderNoDataCF(boolean renderNoDataCF)
    {
        this.renderNoDataCF = renderNoDataCF;
    }

    /**
     * This method is used to get the renderAddCategory.
     * 
     * @return boolean : Returns the renderAddCategory.
     */
    public boolean isRenderAddCategory()
    {
        return renderAddCategory;
    }

    /**
     * This method is used to set the renderAddCategory.
     * 
     * @param renderAddCategory :
     *            The renderAddCategory to set.
     */
    public void setRenderAddCategory(boolean renderAddCategory)
    {
        this.renderAddCategory = renderAddCategory;
    }

    /**
     * This method is used to get the renderEditCategory.
     * 
     * @return boolean : Returns the renderEditCategory.
     */
    public boolean isRenderEditCategory()
    {
        return renderEditCategory;
    }

    /**
     * This method is used to set the renderEditCategory.
     * 
     * @param renderEditCategory :
     *            The renderEditCategory to set.
     */
    public void setRenderEditCategory(boolean renderEditCategory)
    {
        this.renderEditCategory = renderEditCategory;
    }

    /**
     * This method is used to get the showSucessMessage.
     * 
     * @return boolean : Returns the showSucessMessage.
     */
    public boolean isShowSucessMessage()
    {
        return showSucessMessage;
    }

    /**
     * This method is used to set the showSucessMessage.
     * 
     * @param showSucessMessage :
     *            The showSucessMessage to set.
     */
    public void setShowSucessMessage(boolean showSucessMessage)
    {
        this.showSucessMessage = showSucessMessage;
    }

    /**
     * This method is used to get the categoryRowIndex.
     * 
     * @return int : Returns the categoryRowIndex.
     */
    public int getCategoryRowIndex()
    {
        return categoryRowIndex;
    }

    /**
     * This method is used to set the categoryRowIndex.
     * 
     * @param categoryRowIndex :
     *            The categoryRowIndex to set.
     */
    public void setCategoryRowIndex(int categoryRowIndex)
    {
        this.categoryRowIndex = categoryRowIndex;
    }

    /**
     * This method is used to get the categorySearchCriteriaVO.
     * 
     * @return CategorySearchCriteriaVO : Returns the categorySearchCriteriaVO.
     */
    public CategorySearchCriteriaVO getCategorySearchCriteriaVO()
    {
        return categorySearchCriteriaVO;
    }

    /**
     * This method is used to set the categorySearchCriteriaVO.
     * 
     * @param categorySearchCriteriaVO :
     *            The categorySearchCriteriaVO to set.
     */
    public void setCategorySearchCriteriaVO(
            CategorySearchCriteriaVO categorySearchCriteriaVO)
    {
        this.categorySearchCriteriaVO = categorySearchCriteriaVO;
    }

    /**
     * This operation is used to get all the Categories based on the
     * categorySearchCriteriaVO.
     * 
     * @param categorySearchCriteriaVO :
     *            CategorySearchCriteriaVO object.
     * @return List : List of Categories.
     */
    public final List getAllCategories(
            CategorySearchCriteriaVO categorySearchCriteriaVO)
    {

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
        List listOfCategoryDOs = null;
        this.listOfCategoryVOs.clear();

        try
        {
        	listOfCategoryVOs = contactMaintenanceDelegate.getMaintainCategoryList();
        }
        catch (CategoryFetchBusinessException e)
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_SW_GENERIC_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        }
       /* if (listOfCategoryDOs != null)
        {
            CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();

            for (Iterator iter = listOfCategoryDOs.iterator(); iter.hasNext();)
            {
                CorrespondenceCategory categoryDO = (CorrespondenceCategory) iter
                        .next();
                CategoryVO categoryVO = categoryDOConvertor
                        .convertCategoryDOToVOForCategory(categoryDO);
                listOfCategoryVOs.add(categoryVO);
            }
        }*/

        renderNoDataCategory = listOfCategoryVOs.isEmpty();

        return listOfCategoryVOs;
    }

    /**
     * This method used for setting error display messages.
     * 
     * @param errorName :
     *            String errorName.
     * @param arguments :
     *            Array of Object. Parameters to be passed to the message
     * @param messageBundle :
     *            String messageBundle.
     * @param componentId :
     *            String componentId.
     */
    private void setErrorMessage(String errorName, Object[] arguments,
            String messageBundle, String componentId)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        Locale locale = root.getLocale();
        String clientId = null;

        facesContext.getApplication().setMessageBundle(messageBundle);
        String errorMsg = MessageUtil.format(messageBundle, errorName,
                arguments, locale);
        FacesMessage fc = new FacesMessage();
        fc.setDetail(errorMsg);

        if (componentId != null)
        {
            UIComponent uiComponent = findComponentInRoot(componentId);
            clientId = uiComponent.getClientId(facesContext);
        }

        facesContext.addMessage(clientId, fc);
    }

    /**
     * This operation is used to find the component by passing id.
     * 
     * @param base :
     *            View root component of the jsp.
     * @param id :
     *            Id of the component from jsp.
     * @return UIComponent object.
     */
    private UIComponent findComponent(UIComponent base, String id)
    {
        UIComponent component = null;
        UIComponent result = null;

        // Is the "base" component itself the match we are looking for?
        if (id.equals(base.getId()))
        {
            result = base;
        }
        else
        {
            // Search through our facets and children
            Iterator cmpIterator = base.getFacetsAndChildren();

            while (cmpIterator.hasNext() && (result == null))
            {
                component = (UIComponent) cmpIterator.next();
                if (id.equals(component.getId()))
                {
                    result = component;
                    break;
                }
                result = findComponent(component, id);
                if (result != null)
                {
                    break;
                }
            }
        }

        return result;
    }

    /**
     * This operation is used to find the component in root by passing id.
     * 
     * @param id :
     *            String object.
     * @return UIComponent : UIComponent object.
     */
    private UIComponent findComponentInRoot(String id)
    {
        UIComponent component = null;
        FacesContext context = FacesContext.getCurrentInstance();

        if (context != null)
        {
            UIComponent root = context.getViewRoot();
            component = findComponent(root, id);
        }

        return component;
    }

    /**
     * This method is used to get the mapOfDeletedSubjects.
     * 
     * @return Map : Returns the mapOfDeletedSubjects.
     */
   /* public Map getMapOfDeletedSubjects()
    {
        return mapOfDeletedSubjects;
    }

    *//**
     * This method is used to set the mapOfDeletedSubjects.
     * 
     * @param mapOfDeletedSubjects :
     *            The mapOfDeletedSubjects to set.
     *//*
    public void setMapOfDeletedSubjects(Map mapOfDeletedSubjects)
    {
        this.mapOfDeletedSubjects = mapOfDeletedSubjects;
    }*/

    /**
     * This method is used to get the mapOfDeletedCustomFields.
     * 
     * @return Map : Returns the mapOfDeletedCustomFields.
     */
   /* public Map getMapOfDeletedCustomFields()
    {
        return mapOfDeletedCustomFields;
    }

    *//**
     * This method is used to set the mapOfDeletedCustomFields.
     * 
     * @param mapOfDeletedCustomFields :
     *            The mapOfDeletedCustomFields to set.
     *//*
    public void setMapOfDeletedCustomFields(Map mapOfDeletedCustomFields)
    {
        this.mapOfDeletedCustomFields = mapOfDeletedCustomFields;
    }*/

    /**
     * @return Returns the auditOpen.
     */
    public boolean isAuditOpen()
    {
        return auditOpen;
    }

    /**
     * @param auditOpen
     *            The auditOpen to set.
     */
    public void setAuditOpen(boolean auditOpen)
    {
        this.auditOpen = auditOpen;
    }

    /**
     * @return Returns the categoryAuditHistoryList.
     */
    public List getCategoryAuditHistoryList()
    {
        return categoryAuditHistoryList;
    }

    /**
     * @param categoryAuditHistoryList
     *            The categoryAuditHistoryList to set.
     */
    public void setCategoryAuditHistoryList(List categoryAuditHistoryList)
    {
        this.categoryAuditHistoryList = categoryAuditHistoryList;
    }

    /**
     * @return Returns the categoryAuditRender.
     */
    public boolean isCategoryAuditRender()
    {
        return categoryAuditRender;
    }

    /**
     * @param categoryAuditRender
     *            The categoryAuditRender to set.
     */
    public void setCategoryAuditRender(boolean categoryAuditRender)
    {
        this.categoryAuditRender = categoryAuditRender;
    }

    /**
     * @return Returns the listOfCategoryTemplateVOs.
     */
    public List getListOfCategoryTemplateVOs()
    {
        return listOfCategoryTemplateVOs;
    }

    /**
     * @param listOfCategoryTemplateVOs
     *            The listOfCategoryTemplateVOs to set.
     */
    public void setListOfCategoryTemplateVOs(List listOfCategoryTemplateVOs)
    {
        this.listOfCategoryTemplateVOs = listOfCategoryTemplateVOs;
    }

    /**
     * @return Returns the renderNoDataTemplate.
     */
    public boolean isRenderNoDataTemplate()
    {
        return renderNoDataTemplate;
    }

    /**
     * @param renderNoDataTemplate
     *            The renderNoDataTemplate to set.
     */
    public void setRenderNoDataTemplate(boolean renderNoDataTemplate)
    {
        this.renderNoDataTemplate = renderNoDataTemplate;
    }

    /**
     * @return Returns the mapOfDeletedTemplates.
     */
    /*public Map getMapOfDeletedTemplates()
    {
        return mapOfDeletedTemplates;
    }

    *//**
     * @param mapOfDeletedTemplates
     *            The mapOfDeletedTemplates to set.
     *//*
    public void setMapOfDeletedTemplates(Map mapOfDeletedTemplates)
    {
        this.mapOfDeletedTemplates = mapOfDeletedTemplates;
    }*/

    /**
     * @return categoryTypeCodeListME Returns the categoryTypeCodeListME.
     */
    public List getCategoryTypeCodeListME()
    {
        return categoryTypeCodeListME;
    }

    /**
     * @param categoryTypeCodeListME
     *            The categoryTypeCodeListME to set.
     */
    public void setCategoryTypeCodeListME(List categoryTypeCodeListME)
    {
        this.categoryTypeCodeListME = categoryTypeCodeListME;
    }

    /**
     * This method is used to get the valid values.
     * 
     * @return validValuesforBusiUnit returns the list of businessunit valid
     *         values
     */
    public String getValidValuesforBusiUnit()
    {
        CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();
        setCategoryTypeCodeListME(categoryDOConvertor
                .getBusiUnitReferenceData());

        return validValuesforBusiUnit;

    }

    /**
     * @param validValuesforBusiUnit
     *            The validValuesforBusiUnit to set.
     */
    public void setValidValuesforBusiUnit(String validValuesforBusiUnit)
    {
        this.validValuesforBusiUnit = validValuesforBusiUnit;
    }

    /**
     * @return Returns the renderSubjectDetails.
     */
    public boolean isRenderSubjectDetails()
    {
        return renderSubjectDetails;
    }

    /**
     * @param renderSubjectDetails
     *            The renderSubjectDetails to set.
     */
    public void setRenderSubjectDetails(boolean renderSubjectDetails)
    {
        this.renderSubjectDetails = renderSubjectDetails;
    }

    /**
     * @return Returns the subjectVO.
     */
    public CategorySubjectVO getSubjectVO()
    {
        return subjectVO;
    }

    /**
     * @param subjectVO
     *            The subjectVO to set.
     */
    public void setSubjectVO(CategorySubjectVO subjectVO)
    {
        this.subjectVO = subjectVO;
    }

    /**
     * @return Returns the categoryCustomFieldsVO.
     */
    public CategoryCustomFieldsVO getCategoryCustomFieldsVO()
    {
        return categoryCustomFieldsVO;
    }

    /**
     * @param categoryCustomFieldsVO
     *            The categoryCustomFieldsVO to set.
     */
    public void setCategoryCustomFieldsVO(
            CategoryCustomFieldsVO categoryCustomFieldsVO)
    {
        this.categoryCustomFieldsVO = categoryCustomFieldsVO;
    }

    /**
     * @return Returns the renderCustomFieldDetails.
     */
    public boolean isRenderCustomFieldDetails()
    {
        return renderCustomFieldDetails;
    }

    /**
     * @param renderCustomFieldDetails
     *            The renderCustomFieldDetails to set.
     */
    public void setRenderCustomFieldDetails(boolean renderCustomFieldDetails)
    {
        this.renderCustomFieldDetails = renderCustomFieldDetails;
    }
    
    /* Variable to reneder delete message.
    */
   private boolean showDeletedMessage = false;
	/**
	 * @return Returns the showDeletedMessage.
	 */
	public boolean isShowDeletedMessage() {
		return showDeletedMessage;
	}
	/**
	 * @param showDeletedMessage The showDeletedMessage to set.
	 */
	public void setShowDeletedMessage(boolean showDeletedMessage) {
		this.showDeletedMessage = showDeletedMessage;
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
	 * @return Returns the auditColumnHistoryRender.
	 */
	public boolean isAuditColumnHistoryRender() {
		return auditColumnHistoryRender;
	}
	/**
	 * @param auditColumnHistoryRender The auditColumnHistoryRender to set.
	 */
	public void setAuditColumnHistoryRender(boolean auditColumnHistoryRender) {
		this.auditColumnHistoryRender = auditColumnHistoryRender;
	}
	
	private boolean showEditSucessMessage = false;
	/**
	 * @return Returns the showEditSucessMessage.
	 */
	public boolean isShowEditSucessMessage() {
		return showEditSucessMessage;
	}
	/**
	 * @param showEditSucessMessage The showEditSucessMessage to set.
	 */
	public void setShowEditSucessMessage(boolean showEditSucessMessage) {
		this.showEditSucessMessage = showEditSucessMessage;
	}
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
	/**
	 * @return Returns the categorySort.
	 */
	public boolean isCategorySort() {
		return CategorySort;
	}
	/**
	 * @param categorySort The categorySort to set.
	 */
	public void setCategorySort(boolean categorySort) {
		CategorySort = categorySort;
	}
	/**
	 * @return Returns the tempCategoryVO.
	 */
	public CategoryVO getTempCategoryVO() {
		return tempCategoryVO;
	}
	/**
	 * @param tempCategoryVO The tempCategoryVO to set.
	 */
	public void setTempCategoryVO(CategoryVO tempCategoryVO) {
		this.tempCategoryVO = tempCategoryVO;
	}
	
	private int startIndexForCatgry=0;
	
	
	/**
	 * @return Returns the startIndexForCatgry.
	 */
	public int getStartIndexForCatgry() {
		return startIndexForCatgry;
	}
	/**
	 * @param startIndexForCatgry The startIndexForCatgry to set.
	 */
	public void setStartIndexForCatgry(int startIndexForCatgry) {
		this.startIndexForCatgry = startIndexForCatgry;
	}
}
