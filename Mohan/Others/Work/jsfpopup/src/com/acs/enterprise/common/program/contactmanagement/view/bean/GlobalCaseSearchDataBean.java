/*
 * Created on Mar 8, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author jyodlan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class GlobalCaseSearchDataBean extends EnterpriseBaseDataBean {
	/** Creates an instance of the logger. * */
	static final EnterpriseLogger log = EnterpriseLogFactory
			.getLogger(GlobalCaseSearchDataBean.class.getName());

	/**
	 * This field is used to store BEAN_NAME.
	 */
	// Moved to ContactManagementConstants.java
	//public static final String BEAN_NAME = "globalCaseSearchDataBean";

	/**
	 * Constructor calls loadAllValiedValues & getUsersList methods.
	 */
	public GlobalCaseSearchDataBean() {
		super();
	}

	/**
	 * Holds providerSortInd object.
	 */
	private boolean providerSortInd;

	/**
	 * Holds MemberInd object.
	 */
	private boolean memberInd;

	/**
	 * Holds carrierInd object.
	 */
	private boolean carrierInd;

	/**
	 * Holds CaseRecordSearchCriteriaVO object.
	 */
	private CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO;

	/**
	 * This field is used to store EntityID .
	 */

	private String entityID;

	/**
	 * This field is used to store AssignedTo .
	 */
	private List assignedTo = Collections.EMPTY_LIST;

	/**
	 * This field is used to store Status Date .
	 */
	private List status = Collections.EMPTY_LIST;

	/**
	 * This field is used to store CreatedBy .
	 */
	private List createdBy = Collections.EMPTY_LIST;

	/**
	 * This field is used to store EntityType .
	 */
	private List entityType = Collections.EMPTY_LIST;

	/**
	 * This field is used to store EntityIDType .
	 */
	private List entityIDType = Collections.EMPTY_LIST;

	/**
	 * This field is used to store Case Record Number .
	 */
	private String caseRecordNumber;

	/**
	 * This field is used to store Search GlobalCaseList List .
	 */
	private List contactManagemtCaseList = new ArrayList();

	/**
	 * This field is used to store Search GlobalCaseList List .
	 */
	private List tplRecoveryCaseList = new ArrayList();

	/**
	 * This field is used to store Search GlobalCaseList List .
	 */
	private List tplHIPPCaseList = new ArrayList();

	/**
	 * Stores Reference for NoData.
	 */
	private boolean contctManagementnoDataFlag = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean tplRecoverynoDataFlag = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean tplHippnoDataFlag = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean contctManagementnoData = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean tplRecoverynoData = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean tplHippnoData = false;

	/**
	 * Holds variable imageRenderer.
	 */
	private String contctManagementImageRender;

	/**
	 * Holds variable imageRenderer.
	 */
	private String tplRecoveryImageRender;

	/**
	 * Holds variable imageRenderer.
	 */
	private String tplHippImageRender;

	/**
	 * This field is used to store Search CaseList List .
	 */
	private List searchCaseList = new ArrayList();

	/**
	 * Holds variable CaseSearch Flag.
	 */
	private boolean showGlobalCaseSearchMsgFlag = false;

	/**
	 * Holds variable validValuesFlag.
	 */
	private boolean validValuesFlag = true;

	/** Holds common message to log at begin of every method. */
	// Moved to ContactManagementConstants.java
	//protected static final String BEGINMETHOD = "Begin of the GlobalCaseSearchDataBean";
	
	/** Holds common message to log at end of every method. */
	// Moved to ContactManagementConstants.java
	//protected static final String ENDMETHOD = "End of the GlobalCaseSearchDataBean";

	/**
	 * ReferenceServiceDelegate Object
	 */
	private ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();

	/**
	 * CaseDelegate Object
	 */
	private CaseDelegate caseDelegate = new CaseDelegate();
	
	private boolean authUser = false;
	
	private Map userIdNameMap = new HashMap();
	
	private Map userSkNameMap = new HashMap();

	/**
	 * @return Returns the userSkNameMap.
	 */
	public Map getUserSkNameMap() {
		return userSkNameMap;
	}
	/**
	 * @param userSkNameMap The userSkNameMap to set.
	 */
	public void setUserSkNameMap(Map userSkNameMap) {
		this.userSkNameMap = userSkNameMap;
	}
	/**
	 * @return Returns the contctManagementImageRender.
	 */
	public String getContctManagementImageRender() {
		return contctManagementImageRender;
	}

	/**
	 * @param contctManagementImageRender
	 *            The contctManagementImageRender to set.
	 */
	public void setContctManagementImageRender(
			String contctManagementImageRender) {
		this.contctManagementImageRender = contctManagementImageRender;
	}

	/**
	 * @return Returns the tplHippImageRender.
	 */
	public String getTplHippImageRender() {
		return tplHippImageRender;
	}

	/**
	 * @param tplHippImageRender
	 *            The tplHippImageRender to set.
	 */
	public void setTplHippImageRender(String tplHippImageRender) {
		this.tplHippImageRender = tplHippImageRender;
	}

	/**
	 * @return Returns the tplRecoveryImageRender.
	 */
	public String getTplRecoveryImageRender() {
		return tplRecoveryImageRender;
	}

	/**
	 * @param tplRecoveryImageRender
	 *            The tplRecoveryImageRender to set.
	 */
	public void setTplRecoveryImageRender(String tplRecoveryImageRender) {
		this.tplRecoveryImageRender = tplRecoveryImageRender;
	}

	/**
	 * @return Returns the showGlobalCaseSearchMsgFlag.
	 */
	public boolean isShowGlobalCaseSearchMsgFlag() {
		return showGlobalCaseSearchMsgFlag;
	}

	/**
	 * @param showGlobalCaseSearchMsgFlag
	 *            The showGlobalCaseSearchMsgFlag to set.
	 */
	public void setShowGlobalCaseSearchMsgFlag(
			boolean showGlobalCaseSearchMsgFlag) {
		this.showGlobalCaseSearchMsgFlag = showGlobalCaseSearchMsgFlag;
	}

	/**
	 * @return Returns the contctManagementnoData.
	 */
	public boolean isContctManagementnoData() {
		return contctManagementnoData;
	}

	/**
	 * @param contctManagementnoData
	 *            The contctManagementnoData to set.
	 */
	public void setContctManagementnoData(boolean contctManagementnoData) {
		this.contctManagementnoData = contctManagementnoData;
	}

	/**
	 * @return Returns the contctManagementnoDataFlag.
	 */
	public boolean isContctManagementnoDataFlag() {
		return contctManagementnoDataFlag;
	}

	/**
	 * @param contctManagementnoDataFlag
	 *            The contctManagementnoDataFlag to set.
	 */
	public void setContctManagementnoDataFlag(boolean contctManagementnoDataFlag) {
		this.contctManagementnoDataFlag = contctManagementnoDataFlag;
	}

	/**
	 * @return Returns the tplHippnoDataFlag.
	 */
	public boolean isTplHippnoDataFlag() {
		return tplHippnoDataFlag;
	}

	/**
	 * @param tplHippnoDataFlag
	 *            The tplHippnoDataFlag to set.
	 */
	public void setTplHippnoDataFlag(boolean tplHippnoDataFlag) {
		this.tplHippnoDataFlag = tplHippnoDataFlag;
	}

	/**
	 * @return Returns the tplRecoverynoDataFlag.
	 */
	public boolean isTplRecoverynoDataFlag() {
		return tplRecoverynoDataFlag;
	}

	/**
	 * @param tplRecoverynoDataFlag
	 *            The tplRecoverynoDataFlag to set.
	 */
	public void setTplRecoverynoDataFlag(boolean tplRecoverynoDataFlag) {
		this.tplRecoverynoDataFlag = tplRecoverynoDataFlag;
	}

	/**
	 * @return Returns the tplHippnoData.
	 */
	public boolean isTplHippnoData() {
		return tplHippnoData;
	}

	/**
	 * @param tplHippnoData
	 *            The tplHippnoData to set.
	 */
	public void setTplHippnoData(boolean tplHippnoData) {
		this.tplHippnoData = tplHippnoData;
	}

	/**
	 * @return Returns the tplRecoverynoData.
	 */
	public boolean isTplRecoverynoData() {
		return tplRecoverynoData;
	}

	/**
	 * @param tplRecoverynoData
	 *            The tplRecoverynoData to set.
	 */
	public void setTplRecoverynoData(boolean tplRecoverynoData) {
		this.tplRecoverynoData = tplRecoverynoData;
	}

	/**
	 * @return Returns the contactManagemtCaseList.
	 */
	public List getContactManagemtCaseList() {
		return contactManagemtCaseList;
	}

	/**
	 * @param contactManagemtCaseList
	 *            The contactManagemtCaseList to set.
	 */
	public void setContactManagemtCaseList(List contactManagemtCaseList) {
		this.contactManagemtCaseList = contactManagemtCaseList;
	}

	/**
	 * @return Returns the tplHIPPCaseList.
	 */
	public List getTplHIPPCaseList() {
		return tplHIPPCaseList;
	}

	/**
	 * @param tplHIPPCaseList
	 *            The tplHIPPCaseList to set.
	 */
	public void setTplHIPPCaseList(List tplHIPPCaseList) {
		this.tplHIPPCaseList = tplHIPPCaseList;
	}

	/**
	 * @return Returns the tplRecoveryCaseList.
	 */
	public List getTplRecoveryCaseList() {
		return tplRecoveryCaseList;
	}

	/**
	 * @param tplRecoveryCaseList
	 *            The tplRecoveryCaseList to set.
	 */
	public void setTplRecoveryCaseList(List tplRecoveryCaseList) {
		this.tplRecoveryCaseList = tplRecoveryCaseList;
	}

	/**
	 * @return Returns the searchCaseList.
	 */
	public List getSearchCaseList() {
		return searchCaseList;
	}

	/**
	 * @param searchCaseList
	 *            The searchCaseList to set.
	 */
	public void setSearchCaseList(List searchCaseList) {
		this.searchCaseList = searchCaseList;
	}

	/**
	 * @return Returns the entityType.
	 */
	public List getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType
	 *            The entityType to set.
	 */
	public void setEntityType(List entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return Returns the assignedTo.
	 */
	public List getAssignedTo() {
		return assignedTo;
	}

	/**
	 * @param assignedTo
	 *            The assignedTo to set.
	 */
	public void setAssignedTo(List assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**
	 * @return Returns the caseRecordNumber.
	 */
	public String getCaseRecordNumber() {
		return caseRecordNumber;
	}

	/**
	 * @param caseRecordNumber
	 *            The caseRecordNumber to set.
	 */
	public void setCaseRecordNumber(String caseRecordNumber) {
		this.caseRecordNumber = caseRecordNumber;
	}

	/**
	 * @return Returns the caseRecordSearchCriteriaVO.
	 */
	public CaseRecordSearchCriteriaVO getCaseRecordSearchCriteriaVO() {
		return caseRecordSearchCriteriaVO;
	}

	/**
	 * @param caseRecordSearchCriteriaVO
	 *            The caseRecordSearchCriteriaVO to set.
	 */
	public void setCaseRecordSearchCriteriaVO(
			CaseRecordSearchCriteriaVO caseRecordSearchCriteriaVO) {
		this.caseRecordSearchCriteriaVO = caseRecordSearchCriteriaVO;
	}

	/**
	 * @return Returns the createdBy.
	 */
	public List getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            The createdBy to set.
	 */
	public void setCreatedBy(List createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return Returns the entityID.
	 */
	public String getEntityID() {
		return entityID;
	}

	/**
	 * @param entityID
	 *            The entityID to set.
	 */
	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}

	/**
	 * @return Returns the status.
	 */
	public List getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(List status) {
		this.status = status;
	}

	/**
	 * @return Returns the entityIDType.
	 */
	public List getEntityIDType() {
		return entityIDType;
	}

	/**
	 * @param entityIDType
	 *            The entityIDType to set.
	 */
	public void setEntityIDType(List entityIDType) {
		this.entityIDType = entityIDType;
	}

	/**
	 * @return Returns the carrierInd.
	 */
	public boolean isCarrierInd() {
		return carrierInd;
	}

	/**
	 * @param carrierInd
	 *            The carrierInd to set.
	 */
	public void setCarrierInd(boolean carrierInd) {
		this.carrierInd = carrierInd;
	}

	/**
	 * @return Returns the memberInd.
	 */
	public boolean isMemberInd() {
		return memberInd;
	}

	/**
	 * @param memberInd
	 *            The memberInd to set.
	 */
	public void setMemberInd(boolean memberInd) {
		this.memberInd = memberInd;
	}

	/**
	 * @return Returns the providerSortInd.
	 */
	public boolean isProviderSortInd() {
		return providerSortInd;
	}

	/**
	 * @param providerSortInd
	 *            The providerSortInd to set.
	 */
	public void setProviderSortInd(boolean providerSortInd) {
		this.providerSortInd = providerSortInd;
	}

	/**
	 * @return Returns the validValuesFlag.
	 */
	public boolean isValidValuesFlag() {
		return validValuesFlag;
	}

	/**
	 * @param validValuesFlag
	 *            The validValuesFlag to set.
	 */
	public void setValidValuesFlag(boolean validValuesFlag) {
		this.validValuesFlag = validValuesFlag;
	}
	
	/**
	 * @return Returns the authUser.
	 */
	public boolean isAuthUser() {
		return authUser;
	}
	/**
	 * @param authUser The authUser to set.
	 */
	public void setAuthUser(boolean authUser) {
		this.authUser = authUser;
	}
	
	
	/**
	 * @return Returns the userIdNameMap.
	 */
	public Map getUserIdNameMap() {
		return userIdNameMap;
	}
	/**
	 * @param userIdNameMap The userIdNameMap to set.
	 */
	public void setUserIdNameMap(Map userIdNameMap) {
		this.userIdNameMap = userIdNameMap;
	}
	private List departmentList = new ArrayList();
	/**
	 * @return Returns the departmentList.
	 */
	public List getDepartmentList() {
		return departmentList;
	}
	/**
	 * @param departmentList The departmentList to set.
	 */
	public void setDepartmentList(List departmentList) {
		this.departmentList = departmentList;
	}
	
	// Begin - Modified for the HeapDump Fix.
	/**
	 * A variable of type int to hold the ContactManagementList.
	 */
	private int hashContactManagmentCaseList;

	/**
	 * A variable of type int to hold the ContactManagementList.
	 */
	private int hashTPLRecoveryCaseList;

	/**
	 * A variable of type int to hold the ContactManagementList.
	 */
	private int hashTPLHippCaseList;

	/**
	 * @return the hashContactManagmentCaseList
	 */
	public int getHashContactManagmentCaseList() {
		return hashContactManagmentCaseList;
	}
	/**
	 * @param hashContactManagmentCaseList the hashContactManagmentCaseList to set
	 */
	public void setHashContactManagmentCaseList(int hashContactManagmentCaseList) {
		this.hashContactManagmentCaseList = hashContactManagmentCaseList;
	}
	/**
	 * @return the hashTPLRecoveryCaseList
	 */
	public int getHashTPLRecoveryCaseList() {
		return hashTPLRecoveryCaseList;
	}
	/**
	 * @param hashTPLRecoveryCaseList the hashTPLRecoveryCaseList to set
	 */
	public void setHashTPLRecoveryCaseList(int hashTPLRecoveryCaseList) {
		this.hashTPLRecoveryCaseList = hashTPLRecoveryCaseList;
	}
	/**
	 * @return the hashTPLHippCaseList
	 */
	public int getHashTPLHippCaseList() {
		return hashTPLHippCaseList;
	}
	/**
	 * @param hashTPLHippCaseList the hashTPLHippCaseList to set
	 */
	public void setHashTPLHippCaseList(int hashTPLHippCaseList) {
		this.hashTPLHippCaseList = hashTPLHippCaseList;
	}
	// End - Moved this code from GlobalCaseSearchControllerBean to here
	// and modified HTMLDataTable with int data type for the HeapDump Fix.
	
	/** Holds the logged in user id*/
	private String userId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//Added for Defect ESPRD00807684 Starts
	/**
	 * This List is used to store Classification Values
	 * */
	private List classficationList = Collections.EMPTY_LIST;

	
	/**
	 * @return the classficationList
	 */
	public List getClassficationList() {
		return classficationList;
	}
	/**
	 * @param classficationList the classficationList to set
	 */
	public void setClassficationList(List classficationList) {
		this.classficationList = classficationList;
	}
	
	/**
	 * This List is used to store Trading Partner Status Values
	 * */
	private List TPStatusList = Collections.EMPTY_LIST;

	/**
	 * @return the tPStatusList
	 */
	public List getTPStatusList() {
		return TPStatusList;
	}
	/**
	 * @param statusList the tPStatusList to set
	 */
	public void setTPStatusList(List statusList) {
		TPStatusList = statusList;
	}
	//Added for Defect ESPRD00807684 Ends
}