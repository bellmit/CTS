/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;

import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;


/**
 * @author Wipro This class is a data bean which holds the value objects and
 *         other data related to add/update Routing functionality.
 */
public class RoutingDataBean
        extends EnterpriseBaseDataBean
{
    /** Instance of the Enterprise Logger */
    /*private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(RoutingDataBean.class);*/
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(RoutingDataBean.class);

   
    /**
     * This field is used to store cmRoutingVO.
     */
    private CMRoutingVO cmRoutingVO;

    /**
     * This field is used to store listOfCMRoutingVOs.
     */
    private List listOfCMRoutingVOs = new ArrayList();

    /**
     * This field is used for rendering the sorting images.
     */
    private String imageRender;
    
    /**
     * This field is used for security.
     */
    //commented for unused variables
    //private String link2Show;
    
    /**
     * This field is used to store showSucessMessage.
     */
    private boolean showSucessMessage = false;
    
    /**
     * This field is used to disable AddRouting.
     */
    private boolean disableAddRouting;

    /**
     * This field is used to store renderAddRouting.
     */
    private boolean renderAddRouting = false;

    /**
     * This field is used to store renderEditRouting.
     */
    private boolean renderEditRouting = false;

    /**
     * This field is used to store renderUserNames.
     */
    private boolean renderUserNames = false;

    /**
     * This field is used to store renderDeptNames.
     */
    private boolean renderDeptNames = false;

    /**
     * This field is used to store renderBuDeptNames
     */
    private boolean renderBuDeptNames = true;

    /**
     * This field is used to store renderUserDeptNames.
     */
    private boolean renderUserDeptNames = false;

    /**
     * This field is used to store renderDeptUserNames.
     */
    private boolean renderDeptUserNames = false;

    /**
     * This field is used to store listOfUsers.
     */
    private List listOfUsers = new ArrayList();

    /**
     * This field is used to store listOfUserDepts.
     */
    private List listOfUserDepts = new ArrayList();

    /**
     * This field is used to store listOfDepts.
     */
    private List listOfDepts = new ArrayList();

    /**
     * This field is used to store listOfDeptUsers.
     */
    private List listOfDeptUsers = new ArrayList();

    /**
     * This field is used to store refListOfDepts.
     */
    private List refListOfDepts = new ArrayList();

    /**
     * This field is used to store refListOfWorkUnitTypes.
     */
    private List refListOfWorkUnitTypes = new ArrayList();

    /**
     * This field is used to store recordType.
     */
    //private transient HtmlInputHidden recordType;

    /**
     * This field is used to store renderNoData.
     */
    private boolean renderNoData = true;
    
    /**
     * This field is used to store rowIndex.
     */
    private int routingRowIndex = 0;
    
    /**
     * This field is used to store FirstIndex.
     */
    private int routingFirstIndex = 0;

    
    /** This field is used to store workUnitValue */
    private String workUnitValue;
    
    /** This field is used to store businessUnitsList */
    
    private List businessUnitsList = new ArrayList();
    
    /** This field is used to store renderBunitNames */
    private boolean renderBunitNames;
    
	/**
	 * @return Returns the workUnitValue.
	 */
	public String getWorkUnitValue() {
		return workUnitValue;
	}
	/**
	 * @param workUnitValue The workUnitValue to set.
	 */
	public void setWorkUnitValue(String workUnitValue) {
		this.workUnitValue = workUnitValue;
	}
    /**
     * This is used to render alertAuditOpen.
     */
    private boolean routingAuditOpen = false;

    /**
     * This is used to render Audit for Dormer cdodes for details page.
     */

    private boolean routingAuditRender = false;

    /**
     * audit log column value render
     */
    private boolean columnValueRender = false;
    
    /**
     * 
     * @param columnValueRender
     */
    public void setColumnValueRender(boolean columnValueRender){
    	this.columnValueRender = columnValueRender;
    }
    
    /**
     * 
     * @return
     */
    public boolean getColumnValueRender(){
    	return columnValueRender;
    }
    
    /**
     * the selected Audit log
     */
    private AuditLog selectedAuditLog;
    
    /**
     * items per page
     */
    private int itemsPerPage = 10;
    
    /**
     * 
     * @return
     */
    public int getItemsPerPage(){
    	return itemsPerPage;
    }
    
    /**
     * 
     * @param auditLog
     */
    public void setSelectedAuditLog(AuditLog auditLog){
    	this.selectedAuditLog = auditLog;
    }
    
    /**
     * 
     * @return
     */
    public AuditLog getSelectedAuditLog(){
    	return selectedAuditLog;
    }
    
    /**
     * This is used to hold Former codes audit field history list for details
     * page.
     */
    private List routingAuditHistoryList = new ArrayList();

    /**
     * constructor
     */
    public RoutingDataBean()
    {
        super();
        

    }

    /**
     * This method is used to get the cmRoutingVO.
     * 
     * @return CMRoutingVO : Returns the cmRoutingVO.
     */
    public CMRoutingVO getCmRoutingVO()
    {
        return cmRoutingVO;
    }

    /**
     * This method is used to set the cmRoutingVO.
     * 
     * @param cmRoutingVO :
     *            The cmRoutingVO to set.
     */
    public void setCmRoutingVO(CMRoutingVO cmRoutingVO)
    {
        this.cmRoutingVO = cmRoutingVO;
    }

    /**
     * This method is used to get the listOfCMRoutingVOs.
     * 
     * @return List : Returns the listOfCMRoutingVOs.
     */
    public List getListOfCMRoutingVOs()
    {
        return listOfCMRoutingVOs;
    }

    /**
     * This method is used to set the listOfCMRoutingVO.
     * 
     * @param listOfCMRoutingVO :
     *            The listOfCMRoutingVO to set.
     */
    public void setListOfCMRoutingVOs(List listOfCMRoutingVO)
    {
        this.listOfCMRoutingVOs = listOfCMRoutingVO;
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
     * This method is used to get the renderAddRouting.
     * 
     * @return boolean : Returns the renderAddRouting.
     */
    public boolean isRenderAddRouting()
    {
        return renderAddRouting;
    }

    /**
     * This method is used to set the renderAddRouting.
     * 
     * @param renderAddRouting :
     *            The renderAddRouting to set.
     */
    public void setRenderAddRouting(boolean renderAddRouting)
    {
        this.renderAddRouting = renderAddRouting;
    }

    /**
     * This method is used to get the renderEditRouting.
     * 
     * @return boolean : Returns the renderEditRouting.
     */
    public boolean isRenderEditRouting()
    {
        return renderEditRouting;
    }

    /**
     * This method is used to set the renderEditRouting.
     * 
     * @param renderEditRouting :
     *            The renderEditRouting to set.
     */
    public void setRenderEditRouting(boolean renderEditRouting)
    {
        this.renderEditRouting = renderEditRouting;
    }

    /**
     * This method is used to get the renderUserNames.
     * 
     * @return boolean : Returns the renderUserNames.
     */
    public boolean isRenderUserNames()
    {
        return renderUserNames;
    }

    /**
     * This method is used to set the renderUserNames.
     * 
     * @param renderUserNames :
     *            The renderUserNames to set.
     */
    public void setRenderUserNames(boolean renderUserNames)
    {
        this.renderUserNames = renderUserNames;
    }

    /**
     * This method is used to get the renderDeptNames.
     * 
     * @return boolean : Returns the renderDeptNames.
     */
    public boolean isRenderDeptNames()
    {
        return renderDeptNames;
    }

    /**
     * This method is used to set the renderDeptNames.
     * 
     * @param renderDeptNames :
     *            The renderDeptNames to set.
     */
    public void setRenderDeptNames(boolean renderDeptNames)
    {
        this.renderDeptNames = renderDeptNames;
    }

    /**
     * This method is used to get the renderUserDeptNames.
     * 
     * @return boolean : Returns the renderUserDeptNames.
     */
    public boolean isRenderUserDeptNames()
    {
        return renderUserDeptNames;
    }

    /**
     * This method is used to set the renderUserDeptNames.
     * 
     * @param renderUserDeptNames :
     *            The renderUserDeptNames to set.
     */
    public void setRenderUserDeptNames(boolean renderUserDeptNames)
    {
        this.renderUserDeptNames = renderUserDeptNames;
    }

    /**
     * This method is used to get the renderDeptUserNames.
     * 
     * @return boolean : Returns the renderDeptUserNames.
     */
    public boolean isRenderDeptUserNames()
    {
        return renderDeptUserNames;
    }

    /**
     * This method is used to set the renderDeptUserNames.
     * 
     * @param renderDeptUserNames :
     *            The renderDeptUserNames to set.
     */
    public void setRenderDeptUserNames(boolean renderDeptUserNames)
    {
        this.renderDeptUserNames = renderDeptUserNames;
    }

    /**
     * This method is used to get the listOfUsers.
     * 
     * @return List : Returns the listOfUsers.
     */
    public List getListOfUsers()
    {
        return listOfUsers;
    }

    /**
     * This method is used to set the listOfUsers.
     * 
     * @param listOfUsers :
     *            The listOfUsers to set.
     */
    public void setListOfUsers(List listOfUsers)
    {
        this.listOfUsers = listOfUsers;
    }

    /**
     * This method is used to get the listOfUserDepts.
     * 
     * @return List : Returns the listOfUserDepts.
     */
    public List getListOfUserDepts()
    {
        return listOfUserDepts;
    }

    /**
     * This method is used to set the listOfUserDepts.
     * 
     * @param listOfUserDepts :
     *            The listOfUserDepts to set.
     */
    public void setListOfUserDepts(List listOfUserDepts)
    {
        this.listOfUserDepts = listOfUserDepts;
    }

    /**
     * This method is used to get the listOfDepts.
     * 
     * @return List : Returns the listOfDepts.
     */
    public List getListOfDepts()
    {
        return listOfDepts;
    }

    /**
     * This method is used to set the listOfDepts.
     * 
     * @param listOfDepts :
     *            The listOfDepts to set.
     */
    public void setListOfDepts(List listOfDepts)
    {
        this.listOfDepts = listOfDepts;
    }

    /**
     * This method is used to get the listOfDeptUsers.
     * 
     * @return List : Returns the listOfDeptUsers.
     */
    public List getListOfDeptUsers()
    {
        return listOfDeptUsers;
    }

    /**
     * This method is used to set the listOfDeptUsers.
     * 
     * @param listOfDeptUsers :
     *            The listOfDeptUsers to set.
     */
    public void setListOfDeptUsers(List listOfDeptUsers)
    {
        this.listOfDeptUsers = listOfDeptUsers;
    }

    /**
     * This method is used to get the refListOfDepts.
     * 
     * @return List : Returns the refListOfDepts.
     */
    public List getRefListOfDepts()
    {
        return refListOfDepts;
    }

    /**
     * This method is used to set the refListOfDepts.
     * 
     * @param refListOfDepts :
     *            The refListOfDepts to set.
     */
    public void setRefListOfDepts(List refListOfDepts)
    {
        this.refListOfDepts = refListOfDepts;
    }

    /**
     * This method is used to get the refListOfWorkUnitTypes.
     * 
     * @return List : Returns the refListOfWorkUnitTypes.
     */
    public List getRefListOfWorkUnitTypes()
    {
        return refListOfWorkUnitTypes;
    }

    /**
     * This method is used to set the refListOfWorkUnitTypes.
     * 
     * @param refListOfWorkUnitTypes :
     *            The refListOfWorkUnitTypes to set.
     */
    public void setRefListOfWorkUnitTypes(List refListOfWorkUnitTypes)
    {

        this.refListOfWorkUnitTypes = refListOfWorkUnitTypes;
    }

    /**
     * This method is used to get the recordType.
     * 
     * @return HtmlInputHidden : Returns the recordType.
     */
//    public HtmlInputHidden getRecordType()
//    {
//        return recordType;
//    }

    /**
     * This method is used to set the recordType.
     * 
     * @param recordType :
     *            The recordType to set.
     */
//    public void setRecordType(HtmlInputHidden recordType)
//    {
//        this.recordType = recordType;
//    }

    /**
     * This method is used to get the renderNoData.
     * 
     * @return boolean : Returns the renderNoData.
     */
    public boolean isRenderNoData()
    {
        return renderNoData;
    }

    /**
     * This method is used to set the renderNoData.
     * 
     * @param renderNoData :
     *            The renderNoData to set.
     */
    public void setRenderNoData(boolean renderNoData)
    {
        this.renderNoData = renderNoData;
    }

    /**
     * @return Returns the routingAuditOpen.
     */
    public boolean isRoutingAuditOpen()
    {
        return routingAuditOpen;
    }

    /**
     * @param routingAuditOpen
     *            The routingAuditOpen to set.
     */
    public void setRoutingAuditOpen(boolean routingAuditOpen)
    {
        this.routingAuditOpen = routingAuditOpen;
    }

    /**
     * @return Returns the routingAuditRender.
     */
    public boolean isRoutingAuditRender()
    {
        return routingAuditRender;
    }

    /**
     * @param routingAuditRender
     *            The routingAuditRender to set.
     */
    public void setRoutingAuditRender(boolean routingAuditRender)
    {
        this.routingAuditRender = routingAuditRender;
    }

    /**
     * @return Returns the routingAuditHistoryList.
     */
    public List getRoutingAuditHistoryList()
    {
        return routingAuditHistoryList;
    }

    /**
     * @param routingAuditHistoryList
     *            The routingAuditHistoryList to set.
     */
    public void setRoutingAuditHistoryList(List routingAuditHistoryList)
    {
        this.routingAuditHistoryList = routingAuditHistoryList;
    }

    /**
     * @return Returns the renderBuDeptNames.
     */
   public boolean isRenderBuDeptNames()
    {
        return renderBuDeptNames;
    }

    /**
     * @param renderBuDeptNames
     *            The renderBuDeptNames to set.
     */
    public void setRenderBuDeptNames(boolean renderBuDeptNames)
    {
        this.renderBuDeptNames = renderBuDeptNames;
    }
    private boolean  renderCompForOne = false;
	/**
	 * @return Returns the renderCompForOne.
	 */
	public boolean isRenderCompForOne() {
		return renderCompForOne;
	}
	/**
	 * @param renderCompForOne The renderCompForOne to set.
	 */
	public void setRenderCompForOne(boolean renderCompForOne) {
		this.renderCompForOne = renderCompForOne;
	}
	/**
	 * @return Returns the businessUnitsList.
	 */
	public List getBusinessUnitsList() {
		return businessUnitsList;
	}
	/**
	 * @param businessUnitsList The businessUnitsList to set.
	 */
	public void setBusinessUnitsList(List businessUnitsList) {
		this.businessUnitsList = businessUnitsList;
	}
	/**
	 * @return Returns the renderBunitNames.
	 */
	public boolean isRenderBunitNames() {
		return renderBunitNames;
	}
	/**
	 * @param renderBunitNames The renderBunitNames to set.
	 */
	public void setRenderBunitNames(boolean renderBunitNames) {
		this.renderBunitNames = renderBunitNames;
	}
	/**
	 * @return Returns the disableAddRouting.
	 */
	public boolean isDisableAddRouting() {
		return disableAddRouting;
	}
	/**
	 * @param disableAddRouting The disableAddRouting to set.
	 */
	public void setDisableAddRouting(boolean disableAddRouting) {
		this.disableAddRouting = disableAddRouting;
	}
	
	
	/**
	 * @return Returns the routingRowIndex.
	 */
	public int getRoutingRowIndex() {
		return routingRowIndex;
	}
	/**
	 * @param routingRowIndex The routingRowIndex to set.
	 */
	public void setRoutingRowIndex(int routingRowIndex) {
		this.routingRowIndex = routingRowIndex;
	}
	/**
	 * @return the routingFirstIndex
	 */
	public int getRoutingFirstIndex() {
		return routingFirstIndex;
	}
	/**
	 * @param routingFirstIndex the routingFirstIndex to set
	 */
	public void setRoutingFirstIndex(int routingFirstIndex) {
		this.routingFirstIndex = routingFirstIndex;
	}
}
