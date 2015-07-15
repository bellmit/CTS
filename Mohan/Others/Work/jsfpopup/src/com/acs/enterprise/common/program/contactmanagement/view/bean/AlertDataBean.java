/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.List;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.contactmanagement.common.vo.AlertVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro This class is a data bean which holds the value objects and
 *         other data related to add/update Alerts functionality.
 */
public class AlertDataBean extends EnterpriseBaseDataBean {
	/** Instance of the Enterprise Logger */
	/*
	 * private transient EnterpriseLogger logger = EnterpriseLogFactory
	 * .getLogger(AlertDataBean.class);
	 */
	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(AlertDataBean.class);

	
	/**
	 * This field is used to store alertVO.
	 */
	private AlertVO alertVO;

	/**
	 * This field is used to store listOfAlertVOs.
	 */
	private List listOfAlertVOs = new ArrayList();

	/**
	 * This field is used for rendering the sorting images.
	 */
	private String imageRender;

	/**
	 * This field is used to store showSucessMessage.
	 */
	private boolean showSucessMessage = false;

	/**
	 * This field is used to store renderAddAlert.
	 */
	private boolean renderAddAlert = false;

	/**
	 * This field is used to store renderEditAlert.
	 */
	private boolean renderEditAlert = false;

	/**
	 * This field is used to store refAlertTypeList.
	 */
	private List refAlertTypeList = new ArrayList();

	/**
	 * This field is used to store refAlertStatusList.
	 */
	private List refAlertStatusList = new ArrayList();

	/**
	 * This field is used to store rowEnabled.
	 */
	private boolean rowEnabled = true;

	/**
	 * This field is used to store listOfUsersToNotify.
	 */
	private List listOfUsersToNotify = new ArrayList();

	/**
	 * This field is used to store renderNoData.
	 */
	private boolean renderNoData = true;

	/**
	 * This field is used to store rowIndex.
	 */
	private int rowIndex;

	/**
	 * This field is used to store statusProtected.
	 */
	private boolean statusProtected = false;

	/**
	 * This is used to render alertAuditOpen.
	 */
	private boolean alertAuditOpen = false;

	/**
	 * This is used to render Audit for Dormer cdodes for details page.
	 */

	private boolean alertAuditRender = false;

	/**
	 * audit log column value render
	 */
	private boolean columnValueRender = false;

	/**
	 * 
	 * @param columnValueRender
	 */
	public void setColumnValueRender(boolean columnValueRender) {
		this.columnValueRender = columnValueRender;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getColumnValueRender() {
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
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * 
	 * @param auditLog
	 */
	public void setSelectedAuditLog(AuditLog auditLog) {
		this.selectedAuditLog = auditLog;
	}

	/**
	 * 
	 * @return
	 */
	public AuditLog getSelectedAuditLog() {
		return selectedAuditLog;
	}

	/**
	 * This is used to hold Former codes audit field history list for details
	 * page.
	 */
	
	private List alertAuditHistoryList = new ArrayList();

	/**
	 * sorting Flag for MYCRSort
	 */
	private boolean myCrSort = false;

	/**
	 * 
	 * constructor
	 */

	public AlertDataBean() {
		super();
		}

	/**
	 * This method is used to get the alertVO.
	 * 
	 * @return AlertVO : Returns the alertVO.
	 */
	public AlertVO getAlertVO() {
		return alertVO;
	}

	/**
	 * This method is used to set the alertVO.
	 * 
	 * @param alertVO :
	 *            The alertVO to set.
	 */
	public void setAlertVO(AlertVO alertVO) {
		this.alertVO = alertVO;
	}

	/**
	 * This method is used to get the listOfAlertVOs.
	 * 
	 * @return List : Returns the listOfAlertVOs.
	 */
	public List getListOfAlertVOs() {
		return listOfAlertVOs;
	}

	/**
	 * This method is used to set the listOfCMRoutingVO.
	 * 
	 * @param listOfCMRoutingVO :
	 *            The listOfCMRoutingVO to set.
	 */
	public void setListOfAlertVOs(List listOfCMRoutingVO) {
		this.listOfAlertVOs = listOfCMRoutingVO;
	}

	/**
	 * This method is used to get the imageRender.
	 * 
	 * @return String : Returns the imageRender.
	 */
	public String getImageRender() {
		return imageRender;
	}

	/**
	 * This method is used to set the imageRender.
	 * 
	 * @param imageRender :
	 *            The imageRender to set.
	 */
	public void setImageRender(String imageRender) {
		this.imageRender = imageRender;
	}

	/**
	 * This method is used to get the showSucessMessage.
	 * 
	 * @return boolean : Returns the showSucessMessage.
	 */
	public boolean isShowSucessMessage() {
		return showSucessMessage;
	}

	/**
	 * This method is used to set the showSucessMessage.
	 * 
	 * @param showSucessMessage :
	 *            The showSucessMessage to set.
	 */
	public void setShowSucessMessage(boolean showSucessMessage) {
		this.showSucessMessage = showSucessMessage;
	}

	/**
	 * This method is used to get the renderAddAlert.
	 * 
	 * @return boolean : Returns the renderAddAlert.
	 */
	public boolean isRenderAddAlert() {
		return renderAddAlert;
	}

	/**
	 * This method is used to set the renderAddRouting.
	 * 
	 * @param renderAddRouting :
	 *            The renderAddRouting to set.
	 */
	public void setRenderAddAlert(boolean renderAddRouting) {
		this.renderAddAlert = renderAddRouting;
	}

	/**
	 * This method is used to get the renderEditAlert.
	 * 
	 * @return boolean : Returns the renderEditAlert.
	 */
	public boolean isRenderEditAlert() {
		return renderEditAlert;
	}

	/**
	 * This method is used to set the renderEditRouting.
	 * 
	 * @param renderEditRouting :
	 *            The renderEditRouting to set.
	 */
	public void setRenderEditAlert(boolean renderEditRouting) {
		this.renderEditAlert = renderEditRouting;
	}

	/**
	 * This method is used to get the refAlertTypeList.
	 * 
	 * @return List : Returns the refAlertTypeList.
	 */
	public List getRefAlertTypeList() {
		return refAlertTypeList;
	}

	/**
	 * This method is used to set the refAlertTypeList.
	 * 
	 * @param refAlertTypeList :
	 *            The refAlertTypeList to set.
	 */
	public void setRefAlertTypeList(List refAlertTypeList) {
		this.refAlertTypeList = refAlertTypeList;
	}

	/**
	 * This method is used to get the refAlertStatusList.
	 * 
	 * @return List : Returns the refAlertStatusList.
	 */
	public List getRefAlertStatusList() {
		return refAlertStatusList;
	}

	/**
	 * This method is used to set the refAlertStatusList.
	 * 
	 * @param refAlertStatusList :
	 *            The refAlertStatusList to set.
	 */
	public void setRefAlertStatusList(List refAlertStatusList) {
		this.refAlertStatusList = refAlertStatusList;
	}

	/**
	 * This method is used to get the rowEnabled.
	 * 
	 * @return boolean : Returns the rowEnabled.
	 */
	public boolean isRowEnabled() {
		return rowEnabled;
	}

	/**
	 * This method is used to set the rowEnabled.
	 * 
	 * @param rowEnabled :
	 *            The rowEnabled to set.
	 */
	public void setRowEnabled(boolean rowEnabled) {
		this.rowEnabled = rowEnabled;
	}

	/**
	 * This method is used to get the listOfUsersToNotify.
	 * 
	 * @return List : Returns the listOfUsersToNotify.
	 */
	public List getListOfUsersToNotify() {
		return listOfUsersToNotify;
	}

	/**
	 * This method is used to set the listOfUsersToNotify.
	 * 
	 * @param listOfUsersToNotify :
	 *            The listOfUsersToNotify to set.
	 */
	public void setListOfUsersToNotify(List listOfUsersToNotify) {
		this.listOfUsersToNotify = listOfUsersToNotify;
	}

	/**
	 * This method is used to get the renderNoData.
	 * 
	 * @return boolean : Returns the renderNoData.
	 */
	public boolean isRenderNoData() {
		return renderNoData;
	}

	/**
	 * This method is used to set the renderNoData.
	 * 
	 * @param renderNoData :
	 *            The renderNoData to set.
	 */
	public void setRenderNoData(boolean renderNoData) {
		this.renderNoData = renderNoData;
	}

	/**
	 * This method is used to get the rowIndex.
	 * 
	 * @return int : Returns the rowIndex.
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * This method is used to set the rowIndex.
	 * 
	 * @param rowIndex :
	 *            The rowIndex to set.
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * This method is used to get the statusProtected.
	 * 
	 * @return boolean : Returns the statusProtected.
	 */
	public boolean isStatusProtected() {
		return statusProtected;
	}

	/**
	 * This method is used to set the statusProtected.
	 * 
	 * @param statusProtected :
	 *            The statusProtected to set.
	 */
	public void setStatusProtected(boolean statusProtected) {
		this.statusProtected = statusProtected;
	}

	/**
	 * @return Returns the alertAuditHistoryList.
	 */
	public List getAlertAuditHistoryList() {
		return alertAuditHistoryList;
	}

	/**
	 * @param alertAuditHistoryList
	 *            The alertAuditHistoryList to set.
	 */
	public void setAlertAuditHistoryList(List alertAuditHistoryList) {
		this.alertAuditHistoryList = alertAuditHistoryList;
	}

	/**
	 * @return Returns the alertAuditRender.
	 */
	public boolean isAlertAuditRender() {
		return alertAuditRender;
	}

	/**
	 * @param alertAuditRender
	 *            The alertAuditRender to set.
	 */
	public void setAlertAuditRender(boolean alertAuditRender) {
		this.alertAuditRender = alertAuditRender;
	}

	/**
	 * @return Returns the alertAuditOpen.
	 */
	public boolean isAlertAuditOpen() {
		return alertAuditOpen;
	}

	/**
	 * @param alertAuditOpen
	 *            The alertAuditOpen to set.
	 */
	public void setAlertAuditOpen(boolean alertAuditOpen) {
		this.alertAuditOpen = alertAuditOpen;
	}

	/**
	 * @return Returns the myCrSort.
	 */
	public boolean isMyCrSort() {
		return myCrSort;
	}

	/**
	 * @param myCrSort
	 *            The myCrSort to set.
	 */
	public void setMyCrSort(boolean myCrSort) {
		this.myCrSort = myCrSort;
	}

	//heap dump issue fix
	private int startIndexForAlert=0;
	
	/**
	 * @return Returns the startIndexForAlert.
	 */
	public int getStartIndexForAlert() {
		return startIndexForAlert;
	}
	/**
	 * @param startIndexForAlert The startIndexForAlert to set.
	 */
	public void setStartIndexForAlert(int startIndexForAlert) {
		this.startIndexForAlert = startIndexForAlert;
	}
}