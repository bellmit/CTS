/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPane;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CallscriptFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CallScript;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CallScriptSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CallScriptDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CallScriptVO;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class represents the Call script details populated in the presentation
 * layer.
 */
public class CallScriptDataBean
        extends EnterpriseBaseDataBean
{
	
    /**
     * Declated to hold the selected AuditLog.
     */
    private AuditLog selectedAuditLog;
    
    private boolean auditLogFlag = false;
    
    /**
     * Generating object of EnterpriseLogger.
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CallScriptDataBean.class);

    
    /**
     * Reference to hold callscript vo .
     */
    private CallScriptVO callScriptVO = new CallScriptVO();

    /**
     * Reference to hold CallScriptSearchCriteriaVO .
     */
    private CallScriptSearchCriteriaVO callScriptSearchCriteriaVO = new CallScriptSearchCriteriaVO();

    /**
     * List to hold values for datatable for Entity.
     */
    private List entityList = new ArrayList();

    /**
     * List to hold values for datatable for category.
     */
    private List categoryList = new ArrayList();

    /**
     * List to hold values for datatable for category.
     */
    private List subjectList = new ArrayList();

    /**
     * List to hold values datatable for maintainCallScript.
     */
    private List maintainCallScriptList = new ArrayList();

    /**
     * List to hold values of subjects valid values .
     */
    private List subjectValidvalues = new ArrayList();

    /**
     * List to hold values of all subjects from subjectsAuxiliary Table .
     */
    private List allSubjectList = new ArrayList();

    /**
     * List to hold values of all categories from category table .
     */
    private List allCategoryList = new ArrayList();

    /**
     * List to hold values of all Subjects from category table .
     */
    private List allEntityTypeList = new ArrayList();
    /**
     * Variable to hold description message
     */
    private boolean showCallScriptDesc = false;

    /**
     * Variable to reneder Add Call Scripts Block.
     */
    private boolean showAddCallScripts = false;

    /**
     * Variable to reneder Edit Call Scripts Block.
     */
    private boolean showEditCallScripts = false;

    /**
     * Variable to hold boolean flag indicator for Edit.
     */
    private boolean editCallScritFlag = false;

    /**
     * Variable to hold Index of edited row.
     */
    private int editCallScritRowIndex = 0;

    /**
     * Variable used to render Image for Sorting purpose.
     */
    private String imageRender;

    /**
     * Variable to reneder Edit Call Scripts Block.
     */
    private boolean dataFlag = true;

    /**
     * Variable to reneder Edit Call Scripts Block.
     */
    private boolean flagIndicator = false;

    /**
     * Stores a csRowIndex of Data Table.
     */
    private int csRowIndex = 0;

    /**
     * Stores Reference for NoData.
     */
    private boolean noData = false;
    
    /**
     * This is used to show column changes for details page.
     */
    private boolean auditColumnHistoryRender = false;

    /**
     * This field is used to store showSucessMessage.
     */
    private boolean showSucessMessage = false;

    /**
     * This is used to render Audit for Dormer cdodes for details page.
     */

    private boolean callScriptAuditRender = false;

    /**
     * This is used to hold Former codes audit field history list for details
     * page.
     */
    private List callScriptAuditHistoryList = new ArrayList();

    /**
     * This is used to hold the state of the Audit block open or closed.
     */
    private boolean auditOpen = false;
    
    /** holds the total number of records per page. */
    private int itemsPerPage = 10;
    
    /**
	 * @return Returns the callVersionNo.
	 */
	public int getCallVersionNo() {
		return callVersionNo;
	}
	/**
	 * @param callVersionNo The callVersionNo to set.
	 */
	public void setCallVersionNo(int callVersionNo) {
		this.callVersionNo = callVersionNo;
	}
    private int callVersionNo;

    /**
     * Constructor of CallScriptDataBean.
     */
    public CallScriptDataBean()
    {
        super();
        
        //loadAllCallScripts(callScriptSearchCriteriaVO);
    }

    /**
     * @return Returns the callScriptVO.
     */
    public CallScriptVO getCallScriptVO()
    {
        return callScriptVO;
    }

    /**
     * @param callScriptVO
     *            The callScriptVO to set.
     */
    public void setCallScriptVO(CallScriptVO callScriptVO)
    {
        this.callScriptVO = callScriptVO;
    }

    /**
     * @return Returns the callScriptSearchCriteriaVO.
     */
    public CallScriptSearchCriteriaVO getCallScriptSearchCriteriaVO()
    {
        return callScriptSearchCriteriaVO;
    }

    /**
     * @param callScriptSearchCriteriaVO
     *            The callScriptSearchCriteriaVO to set.
     */
    public void setCallScriptSearchCriteriaVO(
            CallScriptSearchCriteriaVO callScriptSearchCriteriaVO)
    {
        this.callScriptSearchCriteriaVO = callScriptSearchCriteriaVO;
    }

    /**
     * @return Returns the showAddCallScripts.
     */
    public boolean isShowAddCallScripts()
    {
        return showAddCallScripts;
    }

    /**
     * @param showAddCallScripts
     *            The showAddCallScripts to set.
     */
    public void setShowAddCallScripts(boolean showAddCallScripts)
    {
        this.showAddCallScripts = showAddCallScripts;
    }

    /**
     * @return Returns the showEditCallScripts.
     */
    public boolean isShowEditCallScripts()
    {
        return showEditCallScripts;
    }

    /**
     * @param showEditCallScripts
     *            The showEditCallScripts to set.
     */
    public void setShowEditCallScripts(boolean showEditCallScripts)
    {
        this.showEditCallScripts = showEditCallScripts;
    }

    /**
     * @return Returns the imageRender.
     */
    public String getImageRender()
    {
        return imageRender;
    }

    /**
     * @param imageRender
     *            The imageRender to set.
     */
    public void setImageRender(String imageRender)
    {
        this.imageRender = imageRender;
    }

    /**
     * @return Returns the dataFlag.
     */
    public boolean isDataFlag()
    {
        return dataFlag;
    }

    /**
     * @param dataFlag
     *            The dataFlag to set.
     */
    public void setDataFlag(boolean dataFlag)
    {
        this.dataFlag = dataFlag;
    }

    /**
     * @return Returns the flagIndicator.
     */
    public boolean isFlagIndicator()
    {
        return flagIndicator;
    }

    /**
     * @param flagIndicator
     *            The flagIndicator to set.
     */
    public void setFlagIndicator(boolean flagIndicator)
    {
        this.flagIndicator = flagIndicator;
    }

    /**
     * @return Returns the categoryList.
     */
    public List getCategoryList()
    {
        return categoryList;
    }

    /**
     * @param categoryList
     *            The categoryList to set.
     */
    public void setCategoryList(List categoryList)
    {
        this.categoryList = categoryList;
    }

    /**
     * @return Returns the subjectList.
     */
    public List getSubjectList()
    {
        return subjectList;
    }

    /**
     * @param subjectList
     *            The subjectList to set.
     */
    public void setSubjectList(List subjectList)
    {
        this.subjectList = subjectList;
    }

    /**
     * @return Returns the entityList.
     */
    public List getEntityList()
    {
        return entityList;
    }

    /**
     * @param entityList
     *            The entityList to set.
     */
    public void setEntityList(List entityList)
    {
        this.entityList = entityList;
    }

    /**
     * @return Returns the maintainCallScriptList.
     */
    public List getMaintainCallScriptList()
    {
        maintainCallScriptList = getAllCallScripts(callScriptSearchCriteriaVO);
        return maintainCallScriptList;
    }

    /**
     * @param maintainCallScriptList
     *            The maintainCallScriptList to set.
     */
    public void setMaintainCallScriptList(List maintainCallScriptList)
    {
        this.maintainCallScriptList = maintainCallScriptList;
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
     * @return Returns the showSucessMessage.
     */
    public boolean isShowSucessMessage()
    {
        return showSucessMessage;
    }

    /**
     * @param showSucessMessage
     *            The showSucessMessage to set.
     */
    public void setShowSucessMessage(boolean showSucessMessage)
    {
        this.showSucessMessage = showSucessMessage;
    }

    /**
     * @return Returns the allSubjectList.
     */
    public List getAllSubjectList()
    {
        return allSubjectList;
    }

    /**
     * @param allSubjectList
     *            The allSubjectList to set.
     */
    public void setAllSubjectList(List allSubjectList)
    {
        this.allSubjectList = allSubjectList;
    }

    /**
     * @return Returns the allCategoryList.
     */
    public List getAllCategoryList()
    {
        return allCategoryList;
    }

    /**
     * @param allCategoryList
     *            The allCategoryList to set.
     */
    public void setAllCategoryList(List allCategoryList)
    {
        this.allCategoryList = allCategoryList;
    }

    /**
     * @return Returns the allEntityTypeList.
     */
    public List getAllEntityTypeList()
    {
        return allEntityTypeList;
    }

    /**
     * @param allEntityTypeList
     *            The allEntityTypeList to set.
     */
    public void setAllEntityTypeList(List allEntityTypeList)
    {
        this.allEntityTypeList = allEntityTypeList;
    }

    /**
     * @return Returns the editCallScritFlag.
     */
    public boolean isEditCallScritFlag()
    {
        return editCallScritFlag;
    }

    /**
     * @param editCallScritFlag
     *            The editCallScritFlag to set.
     */
    public void setEditCallScritFlag(boolean editCallScritFlag)
    {
        this.editCallScritFlag = editCallScritFlag;
    }

    /**
     * @return Returns the editCallScritRowIndex.
     */
    public int getEditCallScritRowIndex()
    {
        return editCallScritRowIndex;
    }

    /**
     * @param editCallScritRowIndex
     *            The editCallScritRowIndex to set.
     */
    public void setEditCallScritRowIndex(int editCallScritRowIndex)
    {
        this.editCallScritRowIndex = editCallScritRowIndex;
    }

    /**
     * @return Returns the subjectValidvalues.
     */
    public List getSubjectValidvalues()
    {
        return subjectValidvalues;
    }

    /**
     * @param subjectValidvalues
     *            The subjectValidvalues to set.
     */
    public void setSubjectValidvalues(List subjectValidvalues)
    {
        this.subjectValidvalues = subjectValidvalues;
    }

    /**
     * This method returns the List of all Callscript Vo's.
     * 
     * @param callScriptSearchCriteriaVO :
     *            Takes CallScriptSearchCriteriaVO as param.
     * @return List of all callScripts
     */
    public List getAllCallScripts(
            CallScriptSearchCriteriaVO callScriptSearchCriteriaVO)
    {

        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        List callScriptDoList = new ArrayList();
        maintainCallScriptList.clear();
        try
        {
            callScriptDoList = contactMaintenanceDelegate
                    .getAllCallScripts(callScriptSearchCriteriaVO);

        }

        catch (CallscriptFetchBusinessException e)
        {
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_GENERIC_FAILURE,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
        }

        if (callScriptDoList != null)

        {
            CallScriptDOConvertor callScriptDOConvertor = new CallScriptDOConvertor();

            for (Iterator iter = callScriptDoList.iterator(); iter.hasNext();)
            {
                CallScript callScript = (CallScript) iter.next();
                CallScriptVO callScriptVO = callScriptDOConvertor
                        .convertCallScriptDOToVO(callScript);
                
                maintainCallScriptList.add(callScriptVO);
            }

        }

        noData = maintainCallScriptList.isEmpty();

        return maintainCallScriptList;

    }

    /**
     * @return Returns the callScriptAuditRender.
     */
    public boolean isCallScriptAuditRender()
    {
        return callScriptAuditRender;
    }

    /**
     * @param callScriptAuditRender
     *            The callScriptAuditRender to set.
     */
    public void setCallScriptAuditRender(boolean callScriptAuditRender)
    {
        this.callScriptAuditRender = callScriptAuditRender;
    }

    /**
     * @return Returns the callScriptAuditHistoryList.
     */
    public List getCallScriptAuditHistoryList()
    {
        return callScriptAuditHistoryList;
    }

    /**
     * @param callScriptAuditHistoryList
     *            The callScriptAuditHistoryList to set.
     */
    public void setCallScriptAuditHistoryList(List callScriptAuditHistoryList)
    {
        this.callScriptAuditHistoryList = callScriptAuditHistoryList;
    }

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
        	if(logger.isDebugEnabled())
        	{
        		logger.debug("Component ID " + componentId);
        	}

            UIComponent uiComponent = findComponentInRoot(componentId);
            clientId = uiComponent.getClientId(facesContext);
            if(logger.isDebugEnabled())
        	{
            	logger.debug("Client Id " + clientId);
        	}
        }

        facesContext.addMessage(clientId, fc);
    }

    /**
     * This operation is used to find the component in root by passing id.
     * 
     * @param id :
     *            String object.
     * @return UIComponent : UIComponent object.
     */
    public UIComponent findComponentInRoot(String id)
    {
        logger.info("findComponentInRoot");

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
     * This operation is used to find the component by passing id.
     * 
     * @param base :
     *            View root component of the jsp.
     * @param id :
     *            Id of the component from jsp.
     * @return UIComponent object.
     */
    public UIComponent findComponent(UIComponent base, String id)
    {
        
        UIComponent result = null;

        // Is the "base" component itself the match we are looking for?
        if (id.equals(base.getId()))
        {
            result = base;

        }

        else
        {
            // Search through our facets and children
            UIComponent component = null;

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
     * @return Returns the showCallScriptDesc.
     */
    public boolean isShowCallScriptDesc()
    {
        return showCallScriptDesc;
    }
    /**
     * @param showCallScriptDesc The showCallScriptDesc to set.
     */
    public void setShowCallScriptDesc(boolean showCallScriptDesc)
    {
        this.showCallScriptDesc = showCallScriptDesc;
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
	   
	private boolean renderAddorEdit = false;
	/**
	 * @return Returns the renderAddorEdit.
	 */
	public boolean isRenderAddorEdit() {
		return renderAddorEdit;
	}
	/**
	 * @param renderAddorEdit The renderAddorEdit to set.
	 */
	public void setRenderAddorEdit(boolean renderAddorEdit) {
		this.renderAddorEdit = renderAddorEdit;
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
	private String inputHiddenId; 
	/**
	 * @return Returns the inputHiddenId.
	 */
	public String getInputHiddenId() {
		return inputHiddenId;
	}
	/**
	 * @param inputHiddenId The inputHiddenId to set.
	 */
	public void setInputHiddenId(String inputHiddenId) {
		this.inputHiddenId = inputHiddenId;
	}
	/**
	 * @return the csRowIndex
	 */
	public int getCsRowIndex() {
		return csRowIndex;
	}
	/**
	 * @param csRowIndex the csRowIndex to set
	 */
	public void setCsRowIndex(int csRowIndex) {
		this.csRowIndex = csRowIndex;
	}

	//heap dump paneltab issue fix
	private int selectedIndex=0;
	
	
	/**
	 * @return Returns the selectedIndex.
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}
	/**
	 * @param selectedIndex The selectedIndex to set.
	 */
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
}
