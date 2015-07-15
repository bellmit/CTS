/*
 * Created on Nov 7, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.commonentities.common.domain.SpecificEntity;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMEntityDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CRDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CRsWatchListVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseViewVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.MyAlertsVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.mmis.member.common.application.exception.MemberBusinessException;
import com.acs.enterprise.mmis.member.common.domain.Member;
import com.acs.enterprise.mmis.member.common.vo.MemberInformationRequestVO;
import com.acs.enterprise.mmis.member.information.common.delegate.MemberInformationDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLCarrier;
import com.acs.enterprise.mmis.provider.common.delegate.ProviderInformationDelegate;
import com.acs.enterprise.mmis.provider.common.domain.Provider;
import com.acs.enterprise.mmis.provider.common.vo.ProviderInformationRequestVO;

/** Data Bean Class for MyTask */
public class MyTaskDataBean extends EnterpriseBaseDataBean {
	
	/** Enterprise Logger for Logging */

	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(MyTaskDataBean.class);

	/**
	 * Holds the firstCount.
	 */
	private String firstCount;

	/**
	 * Holds the firstCount.
	 */
	private String secondCount;

	/**
	 * Holds the firstCount.
	 */
	private String thirdCount;

	/**
	 * Holds the firstCount.
	 */
	private String fourCount;

	/**
	 * Holds the myTaskCrSk.
	 */
	private String myTaskCrSk;
	
	private String myTaskAlertSk;

	/**
	 * This field is used for rendering the sorting images.
	 */
	private String imageRender;

	/**
	 * This field is used for rendering the sorting images in CaseTabs.
	 */
	//commented for unused variables
	//private String caseImageRender;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean watchListNoData = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean userListFlag = true;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean crWatchListNoData = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean alertNoData = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean myCrNoData = false;

	/**
	 * Stores Reference for NoData.
	 */
	//commented for unused variables
	//private boolean createdOrOpened = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean disableApplication = false;

	/**
	 * Stores Reference for NoData.
	 */
	private boolean grpCrNoData = false;

	/**
	 * for openletterrequest
	 */
	private boolean openLetterReq = false;

	/**
	 * Stores Reference for Notes.
	 */
	private boolean disableNotes = false;

	/**
	 * List to hold values of subjects valid values.
	 */
	private List subjectValidValues = new ArrayList();

	/**
	 * List to hold values of subjects valid values.
	 */
	private List sourceCodeValidValues = new ArrayList();

	/**
	 * List to hold values of subjects valid values.
	 */
	private List statusValidValues = new ArrayList();

	/**
	 * List to hold values of subjects valid values.
	 */
	private List sourceValidValues = new ArrayList();

	/**
	 * List to hold values of priority valid values.
	 */
	private List priorityValidValues = new ArrayList();

	/**
	 * To hold the List of AlertVOs.
	 */
	private List alertVoList = new ArrayList();

	/**
	 * To hold the List of My Crs.
	 */
	private List myCrsList = new ArrayList();

	/**
	 * To hold the List of My Group Crs.
	 */
	private List myGrpCRsList = new ArrayList();

	/**
	 * To hold the List of CRsWatchList.
	 */
	private List crWatchList = new ArrayList();

	/**
	 * To hold the List of CRsWatchList.
	 */
	private CRsWatchListVO crsWatchListVO;

	/**
	 * To hold the List of CRsWatchList.
	 */
	private CRDetailsVO crDetailsVO;

	/**
	 * To hold the Boolen value.
	 */
	private boolean openCrDets = false;

	/**
	 * To hold the Boolen value.
	 */
	private boolean alertDetails;

	/**
	 * To hold the Boolen value.
	 */
	private boolean crDetails = false;

	/**
	 * To hold the Boolen value.
	 */
	//commented for unused variables
	//private boolean sortImgRender = false;

	/**
	 * @return Returns the crDetails.
	 */
	//commented for unused variables
	//private boolean sortFlag = false;
	
	/** Hold boolean value for method execution */
	private boolean executed = false;
	
	private boolean crExists = false;

	public boolean isCrDetails() {
		return crDetails;
	}

	/**
	 * @param crDetails
	 *            The crDetails to set.
	 */
	public void setCrDetails(boolean crDetails) {
		this.crDetails = crDetails;
	}

	/**
	 * @return Returns the crDetailsVO.
	 */
	public CRDetailsVO getCrDetailsVO() {
		return crDetailsVO;
	}

	/**
	 * @param crDetailsVO
	 *            The crDetailsVO to set.
	 */
	public void setCrDetailsVO(CRDetailsVO crDetailsVO) {
		this.crDetailsVO = crDetailsVO;
	}

	/**
	 * @return Returns the crsWatchListVO.
	 */
	public CRsWatchListVO getCrsWatchListVO() {
		return crsWatchListVO;
	}

	/**
	 * @param crsWatchListVO
	 *            The crsWatchListVO to set.
	 */
	public void setCrsWatchListVO(CRsWatchListVO crsWatchListVO) {
		this.crsWatchListVO = crsWatchListVO;
	}

	/** holds the user map */
	private Map userMap = new HashMap();


	/**
	 * This method is used to get the reference service list from the reference
	 * map based on the fuctional area constant and elementName
	 * 
	 * @param referenceMap :
	 *            Map of reference list.
	 * @param functionalAreaConstant :
	 *            Reference Service Functional Area Constants to get.
	 * @param elementName :
	 *            Reference Service Element to get.
	 * @return tempRevenueTypeList : Temporary Reference Service List.
	 */
	/*private List getReferenceList(Map referenceMap,
			String functionalAreaConstant, String elementName) {
		logger.info("getReferenceList");

		List referenceList = null;

		List tempRevenueTypeList = new ArrayList();

		referenceList = (List) referenceMap.get(functionalAreaConstant
				+ ProgramConstants.HASH_SEPARATOR + elementName);

		if (referenceList != null) {
			int size = referenceList.size();
			for (int i = 0; i < size; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);

				tempRevenueTypeList.add(new SelectItem(refVo
						.getValidValueCode(), refVo.getShortDescription()));
			}
		}

		return tempRevenueTypeList;
	}*/

	/**
	 * This method is used to created the input criteria to get the reference
	 * date.
	 * 
	 * @param referenceList :
	 *            List of Reference Data.
	 */
	/*private void createInputCriterias(List referenceList) {
		logger.info("createInputCriterias");

		InputCriteria crPriorityVV = new InputCriteria();
		crPriorityVV.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		crPriorityVV.setElementName(ReferenceServiceDataConstants.G_CR_PRTY_CD);

		referenceList.add(crPriorityVV);

		InputCriteria crSubjectsVV = new InputCriteria();
		crSubjectsVV.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		crSubjectsVV
				.setElementName(ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);
		referenceList.add(crSubjectsVV);

		InputCriteria crSourceCodeVV = new InputCriteria();
		crSourceCodeVV.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		crSourceCodeVV
				.setElementName(ReferenceServiceDataConstants.G_CRSPD_SRC_CD);
		referenceList.add(crSourceCodeVV);

		InputCriteria crStatusCodeVV = new InputCriteria();
		crStatusCodeVV.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
		crStatusCodeVV
				.setElementName(ReferenceServiceDataConstants.G_CR_STAT_CD);

		referenceList.add(crStatusCodeVV);

	}*/

	/**
	 * To get the description based on code provided.
	 * 
	 * @param code :
	 *            Holds the code value.
	 * @param vvList :
	 *            Holds the List of valid values.
	 * @return String : Description of the code provided.
	 */
	public final String getDescriptionFromVV(String code, List vvList) {

		
		String desc = ContactManagementConstants.EMPTY_STRING;
		String valueStr = ContactManagementConstants.EMPTY_STRING;

		if (vvList != null && !vvList.isEmpty()) {
			for (Iterator iter = vvList.iterator(); iter.hasNext();) {
				SelectItem selectItem = (SelectItem) iter.next();
				valueStr = (String) selectItem.getValue();

				if (valueStr != null && valueStr.equalsIgnoreCase(code)) {

					desc = selectItem.getLabel();
					break;
				}
			}
		}

		return desc;
	}

	/**
	 * This method convert all the Correspondence do's to My group Cr vo's.
	 * 
	 * @param userID
	 *            The countMap to set.
	 */
	/*private void getAlertDates(String userID) {

		Map countMap = null;
		CMDelegate delegate = new CMDelegate();
		try {
			countMap = delegate.getAlertDates(userID);
			logger.debug("getAlertDates After Delegate Call");
		} catch (CorrespondenceRecordFetchBusinessException e) {
			logger.debug("in alert getAlertDates() CATCH ----");
		}

		if (countMap != null) {
			Object firstCount = null;
			Object secondCount = null;
			Object thirdCount = null;
			Object fourCount = null;

			firstCount = countMap.get((new Integer(0)));

			if (firstCount != null) {
				this.firstCount = firstCount.toString();
			}
			secondCount = countMap.get((new Integer(1)));

			if (secondCount != null) {
				this.secondCount = secondCount.toString();
			}
			thirdCount = countMap.get((new Integer(2)));

			if (thirdCount != null) {
				this.thirdCount = thirdCount.toString();
			}
			fourCount = countMap.get(ContactManagementConstants.THREE);

			if (fourCount != null) {
				this.fourCount = fourCount.toString();
			}
		}

	}*/

	/**
	 * @return Returns the alertNoData.
	 */
	public boolean isAlertNoData() {
		return alertNoData;
	}

	/**
	 * @param alertNoData
	 *            The alertNoData to set.
	 */
	public void setAlertNoData(boolean alertNoData) {
		this.alertNoData = alertNoData;
	}

	/**
	 * @return Returns the grpCrNoData.
	 */
	public boolean isGrpCrNoData() {
		return grpCrNoData;
	}

	/**
	 * @param grpCrNoData
	 *            The grpCrNoData to set.
	 */
	public void setGrpCrNoData(boolean grpCrNoData) {
		this.grpCrNoData = grpCrNoData;
	}

	/**
	 * @return Returns the myCrNoData.
	 */
	public boolean isMyCrNoData() {
		return myCrNoData;
	}

	/**
	 * @param myCrNoData
	 *            The myCrNoData to set.
	 */
	public void setMyCrNoData(boolean myCrNoData) {
		this.myCrNoData = myCrNoData;
	}

	/**
	 * @return Returns the watchListNoData.
	 */
	public boolean isWatchListNoData() {
		return watchListNoData;
	}

	/**
	 * @param watchListNoData
	 *            The watchListNoData to set.
	 */
	public void setWatchListNoData(boolean watchListNoData) {
		this.watchListNoData = watchListNoData;
	}

	/**
	 * @return Returns the crWatchList.
	 */
	public List getCrWatchList() {
		return crWatchList;
	}

	/**
	 * @param crWatchList
	 *            The crWatchList to set.
	 */
	public void setCrWatchList(List crWatchList) {
		this.crWatchList = crWatchList;
	}

	/**
	 * @return Returns the alertVoList.
	 */
	public List getAlertVoList() {
		return alertVoList;
	}

	/**
	 * @param alertVoList
	 *            The alertVoList to set.
	 */
	public void setAlertVoList(List alertVoList) {
		this.alertVoList = alertVoList;
	}

	/**
	 * This method is used to create the depts lists based on UserID.
	 * 
	 * @param userSK :
	 *            The userSK to set.
	 * @return List.
	 */
	public List getDepartmentsList(Long userSK) {
		

		List deptsList = new ArrayList();
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		try {
			deptsList = contactMaintenanceDelegate.getDepartmentUsers(userSK);
		} catch (LOBHierarchyFetchBusinessException e) {
			if(logger.isDebugEnabled())
			{
			logger.debug(e);
			}
		}
		if(logger.isDebugEnabled())
		{
		logger.debug("Dept list size" + deptsList.size());
		}
		return deptsList;
	}

	/**
	 * This method is used to get the UserSK given the userId.
	 * 
	 * @param userId :
	 *            String User Id.
	 * @return Long : UserSK.
	 */
	/*private Long getUserSK(String userId) {

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		Long userSK = null;

		try {
			logger.debug("UserId before delegate getWorkUnitSk call" + userId);
			userSK = contactMaintenanceDelegate.getWorkUnitID(userId);
			logger.debug("Usersk  from workunit table:" + userSK);
		} catch (LOBHierarchyFetchBusinessException e) {

			logger.error(e.getMessage(), e);
		}

		return userSK;
	}*/

	/**
	 * @return Returns the alertDetails.
	 */
	public boolean isAlertDetails() {
		return alertDetails;
	}

	/**
	 * @param alertDetails
	 *            The alertDetails to set.
	 */
	public void setAlertDetails(boolean alertDetails) {
		this.alertDetails = alertDetails;
	}

	/**
	 * @return Returns the myTaskCrSk.
	 */
	public String getMyTaskCrSk() {
		return myTaskCrSk;
	}

	/**
	 * @return Returns the openCrDets.
	 */
	public boolean isOpenCrDets() {
		return openCrDets;
	}

	/**
	 * @param openCrDets
	 *            The openCrDets to set.
	 */
	public void setOpenCrDets(boolean openCrDets) {
		this.openCrDets = openCrDets;
	}

	/**
	 * @param myTaskCrSk
	 *            The myTaskCrSk to set.
	 */
	public void setMyTaskCrSk(String myTaskCrSk) {
		this.myTaskCrSk = myTaskCrSk;
	}

	/**
	 * @return Returns the myCrsList.
	 */
	public List getMyCrsList() {
		return myCrsList;
	}

	/**
	 * @param myCrsList
	 *            The myCrsList to set.
	 */
	public void setMyCrsList(List myCrsList) {
		this.myCrsList = myCrsList;
	}

	/**
	 * @return Returns the myGrpCRsList.
	 */
	public List getMyGrpCRsList() {
		return myGrpCRsList;
	}

	/**
	 * @param myGrpCRsList
	 *            The myGrpCRsList to set.
	 */
	public void setMyGrpCRsList(List myGrpCRsList) {
		this.myGrpCRsList = myGrpCRsList;
	}

	/**
	 * @param caseRecWatchList
	 *            The caseRecWatchList to set.
	 */
	public void setCaseRecWatchList(List caseRecWatchList) {
		this.caseRecWatchList = caseRecWatchList;
	}

	/**
	 * @return Returns the caseRecWatchList.
	 */
	public List getCaseRecWatchList() {
		return caseRecWatchList;
	}

	/**
	 * @param groupCaseRecList
	 *            The groupCaseRecList to set.
	 */
	public void setGroupCaseRecList(List groupCaseRecList) {
		this.groupCaseRecList = groupCaseRecList;
	}

	/**
	 * @return Returns the groupCaseRecList.
	 */
	public List getGroupCaseRecList() {
		return groupCaseRecList;
	}

	/**
	 * @param myCaseRecodsNoData
	 *            The myCaseRecodsNoData to set.
	 */
	/*public void setMyCaseRecodsNoData(boolean myCaseRecodsNoData) {
		this.myCaseRecodsNoData = myCaseRecodsNoData;
	}

	*//**
	 * @return Returns the myCaseRecodsNoData.
	 *//*
	public boolean isMyCaseRecodsNoData() {
		return myCaseRecodsNoData;
	}*/

	/**
	 * @param sortImgRender
	 *            The sortImgRender to set.
	 */
	/*public void setSortImgRender(boolean sortImgRender) {
		this.sortImgRender = sortImgRender;
	}

	*//**
	 * @return Returns the sortImgRender.
	 *//*
	public boolean isSortImgRender() {
		return sortImgRender;
	}*/

	/**
	 * This method used for setting error display messages.
	 * 
	 * @param errorName :
	 *            String errorName.
	 * @param arguments :
	 *            Array of Object. Parameters to be passed to the message.
	 * @param messageBundle :
	 *            String messageBundle.
	 * @param componentId :
	 *            String componentId.
	 */
	/*private void setErrorMessage(String errorName, Object[] arguments,
			String messageBundle, String componentId) {
		logger.info("setErrorMessage");

		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		Locale locale = root.getLocale();
		String clientId = null;

		facesContext.getApplication().setMessageBundle(messageBundle);
		String errorMsg = MessageUtil.format(messageBundle, errorName,
				arguments, locale);
		logger.debug("Error mesg in setErrorMessage :" + errorMsg);
		FacesMessage fc = new FacesMessage();
		fc.setDetail(errorMsg);

		if (componentId != null) {
			logger.debug("Component ID " + componentId);

			UIComponent uiComponent = findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);

			logger.debug("Client Id " + clientId);
		}

		facesContext.addMessage(clientId, fc);
	}*/

	/**
	 * This operation is used to find the component in root by passing id.
	 * 
	 * @param id :
	 *            String object.
	 * @return UIComponent : UIComponent object.
	 */
	private UIComponent findComponentInRoot(String id) {
		

		UIComponent component = null;
		FacesContext context = FacesContext.getCurrentInstance();

		if (context != null) {
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
	private UIComponent findComponent(UIComponent base, String id) {
		

		UIComponent dataReturn = null;

		if (id.equals(base.getId())) {
			dataReturn = base;
		} else {

			UIComponent component = null;
			Iterator cmpIterator = base.getFacetsAndChildren();

			while (cmpIterator.hasNext() && (dataReturn == null)) {
				component = (UIComponent) cmpIterator.next();
				if (id.equals(component.getId())) {
					dataReturn = component;
					break;
				}
				dataReturn = findComponent(component, id);
				if (dataReturn != null) {
					break;
				}
			}
		}

		return dataReturn;
	}

	/**
	 * Holds the myTaskCaseSk
	 */
	private String myTaskCaseSK;

	/**
	 * Holds the String Entitytype for ipc
	 */
	private String caseEntityType;

	/**
	 * Holds the String entityiD
	 */
	//commented for unused variables
	//private String caseEntityID;

	/**
	 * Stores Reference for CaseAlertNodata
	 */
	//commented for unused variables
	//private boolean caseAlertNoData = false;

	/**
	 * Stores refernce for CaseNoData
	 */
	private boolean myCaseNoData = false;

	/**
	 * Stores Refernce fro GRPCaseNodata
	 */
	private boolean grpCaseNoData = false;

	/**
	 * Stores reference for watchlist nod ata
	 */
	//commented for unused variables
	//private boolean caseWatchListNoData = false;

	/**
	 * To hold List of MyCase Records
	 */
	private List myCaseRecordList = new ArrayList();

	/**
	 * To hold the List of CaseDetailsVo
	 */
	private CaseViewVO myTaskCaseDetailsVO;

	/**
	 * TO hold case booelan value
	 */
	private boolean openCaseDets = false;

	/**
	 * To hold claim case boolean value
	 */
	private boolean claimCaseDets = false;

	/**
	 * To hold the Boolen value.
	 */
	private boolean caseDetails = false;

	/**
	 * To hold the List of My Case Records.
	 */

	/**
	 * To hold the List of Group Case Records .
	 */
	private List groupCaseRecList = new ArrayList();

	/**
	 * To hold the List of Case Records Watch List.
	 */
	private List caseRecWatchList = new ArrayList();

	/**
	 * To hold the Boolen value.
	 */
	//commented for unused variables
	//private boolean myCaseRecodsNoData = false;

	/**
	 * To hold entitySk value
	 */
	private String entitySk;

	/**
	 * @param userID
	 */

	/**
	 * This method used to create MyCaseRec List .
	 * 
	 * @param userID
	 *            The caseRecordList to set.
	 */

	/*private void createMyCaseRecList(String userID) {
		logger.debug("Inside CreateMyCaseRecList");
		List caseRecordList = null;
		List myCaseRecordList = null;
		CaseDelegate caseDelegate = new CaseDelegate();
		MyTaskDOConverter myTaskDOConverter = new MyTaskDOConverter();
		try {
			caseRecordList = caseDelegate.getMyCaseRecords(userID);
			logger.debug("myCaseVOList size is :" + caseRecordList.size());

			if (caseRecordList.isEmpty()) {
				this.myCaseNoData = true;
			} else {
				myCaseRecordList = myTaskDOConverter
						.getMyCaseVOList(caseRecordList);
				logger
						.debug("myCaseRecordList size:"
								+ myCaseRecordList.size());

				// Added by ICS

				CaseRecord caseRecord = new CaseRecord();

				for (Iterator iter = caseRecordList.iterator(); iter.hasNext();) {
					caseRecord = (CaseRecord) iter.next();

					if (caseRecord != null) {

						MyCaseRecordsVO myCaseRecordsVO = new MyCaseRecordsVO();
						Set entitySet = caseRecord
								.getCaseCommonEntityCrossRefs();
						String entityTypeCd = null;
						Long entitySk = null;
						String entityName = null;

						if (entitySet != null) {
							for (Iterator iter1 = entitySet.iterator(); iter1
									.hasNext();) {
								CaseCommonEntityCrossRef caseCommonEntityCrossRef = (CaseCommonEntityCrossRef) iter1
										.next();

								entityTypeCd = caseCommonEntityCrossRef
										.getCommonEntityTypeCode();

								entitySk = caseCommonEntityCrossRef
										.getCommonEntitySK();
								logger.debug("EntitySk from Crossref:"
										+ entitySk);
								myCaseRecordsVO
										.setEntitySK(entitySk.toString());
								logger.debug("entity Sk from mycase "
										+ myCaseRecordsVO.getEntitySK());
								myCaseRecordsVO.setEntityType(entityTypeCd);
								logger.debug("EntityName From MyCaseVo:"
										+ myCaseRecordsVO.getEntityType());
								String entityTypeDesc = ContactManagementHelper
										.setShortDescription(
												FunctionalAreaConstants.CONTACT_MGMT,
												ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
												myCaseRecordsVO.getEntityType());
								myCaseRecordsVO
										.setEntityTypeDesc(entityTypeDesc);
								myCaseRecordsVO.setEntityName(getEntityName(
										entityTypeCd, entitySk));
							}
						}
						CaseDetailsVO casedetailsVO = myTaskDOConverter
								.addCaseDetails(caseRecord);

						myCaseRecordsVO.setCaseDetailsVO(casedetailsVO);
						myCaseRecordList.add(myCaseRecordsVO);

					}
					// Ends

				}
				this.myCaseRecordList = myCaseRecordList;
			}
		} catch (CaseRecordFetchBusinessException e) {
			logger.debug(e);
		}

	}*/

	/**
	 * This method used to create GroupCaseRecList .
	 * 
	 * @param userID
	 *            The userID to set.
	 */

	/**
	 * @return Returns the caseAlertNoData.
	 */
	/*public boolean isCaseAlertNoData() {
		return caseAlertNoData;
	}

	*//**
	 * @param caseAlertNoData
	 *            The caseAlertNoData to set.
	 *//*
	public void setCaseAlertNoData(boolean caseAlertNoData) {
		this.caseAlertNoData = caseAlertNoData;
	}*/

	/**
	 * @return Returns the caseEntityID.
	 */
	/*public String getCaseEntityID() {
		return caseEntityID;
	}

	*//**
	 * @param caseEntityID
	 *            The caseEntityID to set.
	 *//*
	public void setCaseEntityID(String caseEntityID) {
		this.caseEntityID = caseEntityID;
	}*/

	/**
	 * @return Returns the caseEntityType.
	 */
	public String getCaseEntityType() {
		return caseEntityType;
	}

	/**
	 * @param caseEntityType
	 *            The caseEntityType to set.
	 */
	public void setCaseEntityType(String caseEntityType) {
		this.caseEntityType = caseEntityType;
	}

	/**
	 * @return Returns the claimCaseDets.
	 */
	public boolean isClaimCaseDets() {
		return claimCaseDets;
	}

	/**
	 * @param claimCaseDets
	 *            The claimCaseDets to set.
	 */
	public void setClaimCaseDets(boolean claimCaseDets) {
		this.claimCaseDets = claimCaseDets;
	}

	/**
	 * @return Returns the grpCaseNoData.
	 */
	public boolean isGrpCaseNoData() {
		return grpCaseNoData;
	}

	/**
	 * @param grpCaseNoData
	 *            The grpCaseNoData to set.
	 */
	public void setGrpCaseNoData(boolean grpCaseNoData) {
		this.grpCaseNoData = grpCaseNoData;
	}

	/**
	 * @return Returns the myCaseNoData.
	 */
	public boolean isMyCaseNoData() {
		return myCaseNoData;
	}

	/**
	 * @param myCaseNoData
	 *            The myCaseNoData to set.
	 */
	public void setMyCaseNoData(boolean myCaseNoData) {
		this.myCaseNoData = myCaseNoData;
	}

	/**
	 * @return Returns the myCaseRecordList.
	 */
	public List getMyCaseRecordList() {
		return myCaseRecordList;
	}

	/**
	 * @param myCaseRecordList
	 *            The myCaseRecordList to set.
	 */
	public void setMyCaseRecordList(List myCaseRecordList) {
		this.myCaseRecordList = myCaseRecordList;
	}

	/**
	 * @return Returns the myTaskCaseSK.
	 */
	public String getMyTaskCaseSK() {
		return myTaskCaseSK;
	}

	/**
	 * @param myTaskCaseSK
	 *            The myTaskCaseSK to set.
	 */
	public void setMyTaskCaseSK(String myTaskCaseSK) {
		this.myTaskCaseSK = myTaskCaseSK;
	}

	/**
	 * @return Returns the openCaseDets.
	 */
	public boolean isOpenCaseDets() {
		return openCaseDets;
	}

	/**
	 * @param openCaseDets
	 *            The openCaseDets to set.
	 */
	public void setOpenCaseDets(boolean openCaseDets) {
		this.openCaseDets = openCaseDets;
	}

	/**
	 * @return Returns the caseDetails.
	 */
	public boolean isCaseDetails() {
		return caseDetails;
	}

	/**
	 * @param caseDetails
	 *            The caseDetails to set.
	 */
	public void setCaseDetails(boolean caseDetails) {
		this.caseDetails = caseDetails;
	}

	/**
	 * @return Returns the createdOrOpened.
	 */
	/*public boolean isCreatedOrOpened() {
		return createdOrOpened;
	}

	*//**
	 * @param createdOrOpened
	 *            The createdOrOpened to set.
	 *//*
	public void setCreatedOrOpened(boolean createdOrOpened) {
		this.createdOrOpened = createdOrOpened;
	}*/

	/**
	 * @return Returns the imageRender.
	 */
	public String getImageRender() {
		return imageRender;
	}

	/**
	 * @param imageRender
	 *            The imageRender to set.
	 */
	public void setImageRender(String imageRender) {
		this.imageRender = imageRender;
	}

	/** For Alert Dates Funtionality */

	/**
	 * @return Returns the firstCount.
	 */
	public String getFirstCount() {
		return firstCount;
	}

	/**
	 * @param firstCount
	 *            The firstCount to set.
	 */
	public void setFirstCount(String firstCount) {
		this.firstCount = firstCount;
	}

	/**
	 * @return Returns the fourCount.
	 */
	public String getFourCount() {
		return fourCount;
	}

	/**
	 * @param fourCount
	 *            The fourCount to set.
	 */
	public void setFourCount(String fourCount) {
		this.fourCount = fourCount;
	}

	/**
	 * @return Returns the secondCount.
	 */
	public String getSecondCount() {
		return secondCount;
	}

	/**
	 * @param secondCount
	 *            The secondCount to set.
	 */
	public void setSecondCount(String secondCount) {
		this.secondCount = secondCount;
	}

	/**
	 * @return Returns the thirdCount.
	 */
	public String getThirdCount() {
		return thirdCount;
	}

	/**
	 * @param thirdCount
	 *            The thirdCount to set.
	 */
	public void setThirdCount(String thirdCount) {
		this.thirdCount = thirdCount;
	}

	/**
	 * @return Returns the caseWatchListNoData.
	 */
	/*public boolean isCaseWatchListNoData() {
		return caseWatchListNoData;
	}

	*//**
	 * @param caseWatchListNoData
	 *            The caseWatchListNoData to set.
	 *//*
	public void setCaseWatchListNoData(boolean caseWatchListNoData) {
		this.caseWatchListNoData = caseWatchListNoData;
	}*/

	/**
	 * @return Returns the entitySk.
	 */
	public String getEntitySk() {
		return entitySk;
	}

	/**
	 * @param entitySk
	 *            The entitySk to set.
	 */
	public void setEntitySk(String entitySk) {
		this.entitySk = entitySk;
	}

	/**
	 * @return Returns the priorityValidValues.
	 */
	public List getPriorityValidValues() {
		return priorityValidValues;
	}

	/**
	 * @param priorityValidValues
	 *            The priorityValidValues to set.
	 */
	public void setPriorityValidValues(List priorityValidValues) {
		this.priorityValidValues = priorityValidValues;
	}

	/**
	 * @return Returns the statusValidValues.
	 */
	public List getStatusValidValues() {
		return statusValidValues;
	}

	/**
	 * @param statusValidValues
	 *            The statusValidValues to set.
	 */
	public void setStatusValidValues(List statusValidValues) {
		this.statusValidValues = statusValidValues;
	}

	/**
	 * @return Returns the subjectValidValues.
	 */
	public List getSubjectValidValues() {
		return subjectValidValues;
	}

	/**
	 * @param subjectValidValues
	 *            The subjectValidValues to set.
	 */
	public void setSubjectValidValues(List subjectValidValues) {
		this.subjectValidValues = subjectValidValues;
	}

	/**
	 * @return Returns the sourceValidValues.
	 */
	public List getSourceValidValues() {
		return sourceValidValues;
	}

	/**
	 * @param sourceValidValues
	 *            The sourceValidValues to set.
	 */
	public void setSourceValidValues(List sourceValidValues) {
		this.sourceValidValues = sourceValidValues;
	}

	/**
	 * @return Returns the sourceCodeValidValues.
	 */
	public List getSourceCodeValidValues() {
		return sourceCodeValidValues;
	}

	/**
	 * @param sourceCodeValidValues
	 *            The sourceCodeValidValues to set.
	 */
	public void setSourceCodeValidValues(List sourceCodeValidValues) {
		this.sourceCodeValidValues = sourceCodeValidValues;
	}

	/**
	 * @return Returns the caseImageRender.
	 */
	/*public String getCaseImageRender() {
		return caseImageRender;
	}

	*//**
	 * @param caseImageRender
	 *            The caseImageRender to set.
	 *//*
	public void setCaseImageRender(String caseImageRender) {
		this.caseImageRender = caseImageRender;
	}*/

	/**
	 * @return Returns the crWatchListNoData.
	 */
	public boolean isCrWatchListNoData() {
		return crWatchListNoData;
	}

	/**
	 * @param crWatchListNoData
	 *            The crWatchListNoData to set.
	 */
	public void setCrWatchListNoData(boolean crWatchListNoData) {
		this.crWatchListNoData = crWatchListNoData;
	}

	/**
	 * @return Returns the userMap.
	 */
	public Map getUserMap() {
		return userMap;
	}

	/**
	 * @param userMap
	 *            The userMap to set.
	 */
	public void setUserMap(Map userMap) {
		this.userMap = userMap;
	}


	/**
	 * @return Returns the openLetterReq.
	 */
	public boolean isOpenLetterReq() {
		return openLetterReq;
	}

	/**
	 * @param openLetterReq
	 *            The openLetterReq to set.
	 */
	public void setOpenLetterReq(boolean openLetterReq) {
		this.openLetterReq = openLetterReq;
	}

	
    /** holds itemSelectedRowIndex */
	private int itemSelectedRowIndex = -1;
	/**
	 * @return Returns the sortFlag.
	 */
	/*public boolean isSortFlag() {
		return sortFlag;
	}

	*//**
	 * @param sortFlag The sortFlag to set.
	 *//*
	public void setSortFlag(boolean sortFlag) {
		this.sortFlag = sortFlag;
	}*/

	private boolean supApprovedMyCrFlag = true;

	/**
	 * @return Returns the supApprovedMyCrFlag.
	 */
	public boolean isSupApprovedMyCrFlag() {
		return supApprovedMyCrFlag;
	}

	/**
	 * @param supApprovedMyCrFlag The supApprovedMyCrFlag to set.
	 */
	public void setSupApprovedMyCrFlag(boolean supApprovedMyCrFlag) {
		this.supApprovedMyCrFlag = supApprovedMyCrFlag;
	}

	/**
	 * @return Returns the disableApplication.
	 */
	public boolean isDisableApplication() {
		return disableApplication;
	}

	/**
	 * @param disableApplication The disableApplication to set.
	 */
	public void setDisableApplication(boolean disableApplication) {
		this.disableApplication = disableApplication;
	}

	private boolean alertCRDetails = false;

	private boolean myCrsDetails = false;

	private boolean groupCRDetails = false;

	private boolean crsWatchListDetails = false;

	private boolean myCaseRecordsDetails = false;

	private boolean groupCaseRecordsDetails = false;

	private boolean caseRecordWatchListDetails = false;

	/**
	 * @return Returns the alertCRDetails.
	 */
	public boolean isAlertCRDetails() {
		return alertCRDetails;
	}

	/**
	 * @param alertCRDetails The alertCRDetails to set.
	 */
	public void setAlertCRDetails(boolean alertCRDetails) {
		this.alertCRDetails = alertCRDetails;
	}

	/**
	 * @return Returns the caseRecordWatchListDetails.
	 */
	public boolean isCaseRecordWatchListDetails() {
		return caseRecordWatchListDetails;
	}

	/**
	 * @param caseRecordWatchListDetails The caseRecordWatchListDetails to set.
	 */
	public void setCaseRecordWatchListDetails(boolean caseRecordWatchListDetails) {
		this.caseRecordWatchListDetails = caseRecordWatchListDetails;
	}

	/**
	 * @return Returns the groupCaseRecordsDetails.
	 */
	public boolean isGroupCaseRecordsDetails() {
		return groupCaseRecordsDetails;
	}

	/**
	 * @param groupCaseRecordsDetails The groupCaseRecordsDetails to set.
	 */
	public void setGroupCaseRecordsDetails(boolean groupCaseRecordsDetails) {
		this.groupCaseRecordsDetails = groupCaseRecordsDetails;
	}

	/**
	 * @return Returns the groupCRDetails.
	 */
	public boolean isGroupCRDetails() {
		return groupCRDetails;
	}

	/**
	 * @param groupCRDetails The groupCRDetails to set.
	 */
	public void setGroupCRDetails(boolean groupCRDetails) {
		this.groupCRDetails = groupCRDetails;
	}

	/**
	 * @return Returns the myCaseRecordsDetails.
	 */
	public boolean isMyCaseRecordsDetails() {
		return myCaseRecordsDetails;
	}

	/**
	 * @param myCaseRecordsDetails The myCaseRecordsDetails to set.
	 */
	public void setMyCaseRecordsDetails(boolean myCaseRecordsDetails) {
		this.myCaseRecordsDetails = myCaseRecordsDetails;
	}

	/**
	 * @return Returns the myCrsDetails.
	 */
	public boolean isMyCrsDetails() {
		return myCrsDetails;
	}

	/**
	 * @param myCrsDetails The myCrsDetails to set.
	 */
	public void setMyCrsDetails(boolean myCrsDetails) {
		this.myCrsDetails = myCrsDetails;
	}

	/**
	 * @return Returns the crsWatchListDetails.
	 */
	public boolean isCrsWatchListDetails() {
		return crsWatchListDetails;
	}

	/**
	 * @param crsWatchListDetails The crsWatchListDetails to set.
	 */
	public void setCrsWatchListDetails(boolean crsWatchListDetails) {
		this.crsWatchListDetails = crsWatchListDetails;
	}

//	private transient HtmlPanelTabbedPane htmlPanelTabbedPane;
//
//	/**
//	 * @return Returns the htmlPanelTabbedPane.
//	 */
//	public HtmlPanelTabbedPane getHtmlPanelTabbedPane() {
//		return htmlPanelTabbedPane;
//	}
//
//	/**
//	 * @param htmlPanelTabbedPane The htmlPanelTabbedPane to set.
//	 */
//	public void setHtmlPanelTabbedPane(HtmlPanelTabbedPane htmlPanelTabbedPane) {
//		this.htmlPanelTabbedPane = htmlPanelTabbedPane;
//	}
	/**
	 * @return Returns the itemSelectedRowIndex.
	 */
	public int getItemSelectedRowIndex() {
		return itemSelectedRowIndex;
	}
	/**
	 * @param itemSelectedRowIndex The itemSelectedRowIndex to set.
	 */
	public void setItemSelectedRowIndex(int itemSelectedRowIndex) {
		this.itemSelectedRowIndex = itemSelectedRowIndex;
	}

	/**
	 * @return Returns the disableNotes.
	 */
	public boolean isDisableNotes() {
		return disableNotes;
	}

	/**
	 * @param disableNotes The disableNotes to set.
	 */
	public void setDisableNotes(boolean disableNotes) {
		this.disableNotes = disableNotes;
	}

	/**
	 * @return Returns the userListFlag.
	 */
	public boolean isUserListFlag() {
		return userListFlag;
	}

	/**
	 * @param userListFlag The userListFlag to set.
	 */
	public void setUserListFlag(boolean userListFlag) {
		this.userListFlag = userListFlag;
	}

	/**
	 * @param myCaseRecordsVO
	 * @param entityTypeCd
	 * @return
	 */
	private String getEntityName(String entityTypeCd, Long entitySk) {

		String entityName = null;
		if (entityTypeCd != null) {
			StringBuffer stringBuffer = new StringBuffer();
			if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM)) {

				
				MemberInformationDelegate memberDelegate = new MemberInformationDelegate();
				MemberInformationRequestVO memberVO = new MemberInformationRequestVO();
				try {
					Long systemID = memberDelegate.getMemberID(entitySk);
					if (systemID != null) {
						memberVO.setMemberSysID(systemID);
						Member member = memberDelegate
								.getMemberDetail(memberVO);
						if (member != null) {
							if (member.getDemographicInformation().getName()
									.getFirstName() != null) {
								stringBuffer.append(member
										.getDemographicInformation().getName()
										.getFirstName());
								stringBuffer.append(" ");
							}
							if (member.getDemographicInformation().getName()
									.getMiddleName() != null) {
								stringBuffer.append(member
										.getDemographicInformation().getName()
										.getMiddleName());
								stringBuffer.append(" ");
							}
							if (member.getDemographicInformation().getName()
									.getLastName() != null) {
								stringBuffer.append(member
										.getDemographicInformation().getName()
										.getLastName());
							}
						}
						entityName = stringBuffer.toString();
					}
				} catch (MemberBusinessException e) {
					logger.error(e.getMessage(), e);
				}
			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_PROV)) {

				
				ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
				ProviderInformationRequestVO providerVO = new ProviderInformationRequestVO();
				try {
					Long pSysId = providerInformationDelegate
							.getProviderSysID(entitySk);
					if (pSysId != null) {
						providerVO.setProviderSysID(pSysId);
						Provider provider = providerInformationDelegate
								.getProviderDetails(providerVO);
						if (provider != null
								&& provider.getName().getSortName() != null) {
							entityName = provider.getName().getSortName();
						}
					}
				} catch (EnterpriseBaseBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
				}

			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNMEM)
					|| entityTypeCd
							.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV)) {

				
				CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
				try {
					SpecificEntity specficEntity = cmEntityDelegate
							.getSpecificEntityDetails(entitySk);
					if (specficEntity != null) {
						StringBuffer sb = new StringBuffer();
						if (specficEntity.getName().getFirstName() != null) {
							sb.append(specficEntity.getName().getFirstName());
							sb.append(" ");
						}
						if (specficEntity.getName().getMiddleName() != null) {
							sb.append(specficEntity.getName().getMiddleName());
							sb.append(" ");
						}
						if (specficEntity.getName().getLastName() != null) {
							sb.append(specficEntity.getName().getLastName());
							sb.append(" ");
						}
						if (specficEntity.getOrganizationName() != null) {
							sb.append(specficEntity.getOrganizationName());
						}
						entityName = sb.toString();
					}
				} catch (CMEntityFetchBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
				}

			} else if (entityTypeCd
					.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TPL)) {

				
				CMDelegate cmDelegate = new CMDelegate();
				try {
					TPLCarrier tplCarrier = cmDelegate.getTPLCarrier(entitySk);
					if (tplCarrier != null
							&& tplCarrier.getCarrierName() != null) {
						entityName = tplCarrier.getCarrierName();
					}
				} catch (CorrespondenceRecordFetchBusinessException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}

				}
			}
		}
		return entityName;
	}
	
	/**
	 * @return Returns the executed.
	 */
	public boolean isExecuted() {
		return executed;
	}
	/**
	 * @param executed The executed to set.
	 */
	public void setExecuted(boolean executed) {
		this.executed = executed;
	}
//added for MyTask_Alerts_ESPRD00423256_9MAR2010
	private boolean showTplRecoeryCaseAlertDetail = false;

	/**
	 * @return Returns the showTplRecoeryCaseAlertDetail.
	 */
	public boolean isShowTplRecoeryCaseAlertDetail() {
		return showTplRecoeryCaseAlertDetail;
	}
	/**
	 * @param showTplRecoeryCaseAlertDetail The showTplRecoeryCaseAlertDetail to set.
	 */
	public void setShowTplRecoeryCaseAlertDetail(
			boolean showTplRecoeryCaseAlertDetail) {
		this.showTplRecoeryCaseAlertDetail = showTplRecoeryCaseAlertDetail;
	}
	//EOF MyTask_Alerts_ESPRD00423256_9MAR2010
	//added for UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010
	private boolean openTPLRecoveryCaseRecord = false;
	/**
	 * @return Returns the openTPLRecoveryCaseRecord.
	 */
	public boolean isOpenTPLRecoveryCaseRecord() {
		return openTPLRecoveryCaseRecord;
	}
	/**
	 * @param openTPLRecoveryCaseRecord The openTPLRecoveryCaseRecord to set.
	 */
	public void setOpenTPLRecoveryCaseRecord(boolean openTPLRecoveryCaseRecord) {
		this.openTPLRecoveryCaseRecord = openTPLRecoveryCaseRecord;
	}
	private boolean alertDetailsDescription = true;

	/**
	 * @return Returns the alertDetailsDescription.
	 */
	public boolean isAlertDetailsDescription() {
		return alertDetailsDescription;
	}
	/**
	 * @param alertDetailsDescription The alertDetailsDescription to set.
	 */
	public void setAlertDetailsDescription(boolean alertDetailsDescription) {
		this.alertDetailsDescription = alertDetailsDescription;
	}
	
	private MyAlertsVO alertDetailsVO = null;

	/**
	 * @return Returns the alertDetailsVO.
	 */
	public MyAlertsVO getAlertDetailsVO() {
		return alertDetailsVO;
	}
	/**
	 * @param alertDetailsVO The alertDetailsVO to set.
	 */
	public void setAlertDetailsVO(MyAlertsVO alertDetailsVO) {
		this.alertDetailsVO = alertDetailsVO;
	}////EOF  for UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010
	
	//added for UC-PGM-CRM-052.1_ESPRD00436245_18MAR2010
	
	private boolean openMSQDetails = false;
	
	/**
	 * @return Returns the openMSQDetails.
	 */
	public boolean isOpenMSQDetails() {
		return openMSQDetails;
	}
	/**
	 * @param openMSQDetails The openMSQDetails to set.
	 */
	public void setOpenMSQDetails(boolean openMSQDetails) {
		this.openMSQDetails = openMSQDetails;
	}
	
	private boolean openSADetails = false;

	/**
	 * @return Returns the openSADetails.
	 */
	public boolean isOpenSADetails() {
		return openSADetails;
	}
	/**
	 * @param openSADetails The openSADetails to set.
	 */
	public void setOpenSADetails(boolean openSADetails) {
		this.openSADetails = openSADetails;
	}
	//EOF UC-PGM-CRM-052.1_ESPRD00436245_18MAR2010
	
	/** holds itemSelectedRowIndexGrp */
	private int itemSelectedRowIndexGrp = -1;

	
	/**
	 * @return Returns the itemSelectedRowIndexGrp.
	 */
	public int getItemSelectedRowIndexGrp() {
		return itemSelectedRowIndexGrp;
	}
	/**
	 * @param itemSelectedRowIndexGrp The itemSelectedRowIndexGrp to set.
	 */
	public void setItemSelectedRowIndexGrp(int itemSelectedRowIndexGrp) {
		this.itemSelectedRowIndexGrp = itemSelectedRowIndexGrp;
	}
	
	
	/** holds itemSelectedRowIndexAlert */
	private int itemSelectedRowIndexAlert = -1;
	/**
	 * @return Returns the itemSelectedRowIndexAlert.
	 */
	public int getItemSelectedRowIndexAlert() {
		return itemSelectedRowIndexAlert;
	}
	/**
	 * @param itemSelectedRowIndexAlert The itemSelectedRowIndexAlert to set.
	 */
	public void setItemSelectedRowIndexAlert(int itemSelectedRowIndexAlert) {
		this.itemSelectedRowIndexAlert = itemSelectedRowIndexAlert;
	}
	
	/** holds itemSelectedRowIndexCrWL */
	private int itemSelectedRowIndexCrWL = -1;
	/**
	 * @return Returns the itemSelectedRowIndexCrWL.
	 */
	public int getItemSelectedRowIndexCrWL() {
		return itemSelectedRowIndexCrWL;
	}
	/**
	 * @param itemSelectedRowIndexCrWL The itemSelectedRowIndexCrWL to set.
	 */
	public void setItemSelectedRowIndexCrWL(int itemSelectedRowIndexCrWL) {
		this.itemSelectedRowIndexCrWL = itemSelectedRowIndexCrWL;
	}
	/**
     * to hold the  render status for an Application 
     */
	private boolean applicationNameFlag = false;
	
	
	/**
	 * @return Returns the applicationNameFlag.
	 */
	public boolean isApplicationNameFlag() {
		return applicationNameFlag;
	}
	/**
	 * @param applicationNameFlag The applicationNameFlag to set.
	 */
	public void setApplicationNameFlag(boolean applicationNameFlag) {
		this.applicationNameFlag = applicationNameFlag;
	}
	/**
	 * @return Returns the crExists.
	 */
	public boolean isCrExists() {
		return crExists;
	}
	/**
	 * @param crExists The crExists to set.
	 */
	public void setCrExists(boolean crExists) {
		this.crExists = crExists;
	}
	
	//540203_Performance_Fix_23Dec10 starts:
	
	
	private boolean mytaskAlertFlag=true;
	private boolean mytaskCRsFlag;
	private boolean mytaskGrpCRsFlag;
	private boolean mytaskCRsWatchListFlag;
	private boolean mytaskCaseRecsFlag;
	private boolean mytaskGrpCaseRecsFlag;
	private boolean mytaskCaseRecWatchListFlag;
	
	/**
	 * @return Returns the mytaskAlertFlag.
	 */
	public boolean isMytaskAlertFlag() {
		return mytaskAlertFlag;
	}
	/**
	 * @param mytaskAlertFlag The mytaskAlertFlag to set.
	 */
	public void setMytaskAlertFlag(boolean mytaskAlertFlag) {
		this.mytaskAlertFlag = mytaskAlertFlag;
	}
	/**
	 * @return Returns the mytaskCaseRecsFlag.
	 */
	public boolean isMytaskCaseRecsFlag() {
		return mytaskCaseRecsFlag;
	}
	/**
	 * @param mytaskCaseRecsFlag The mytaskCaseRecsFlag to set.
	 */
	public void setMytaskCaseRecsFlag(boolean mytaskCaseRecsFlag) {
		this.mytaskCaseRecsFlag = mytaskCaseRecsFlag;
	}
	/**
	 * @return Returns the mytaskCaseRecWatchListFlag.
	 */
	public boolean isMytaskCaseRecWatchListFlag() {
		return mytaskCaseRecWatchListFlag;
	}
	/**
	 * @param mytaskCaseRecWatchListFlag The mytaskCaseRecWatchListFlag to set.
	 */
	public void setMytaskCaseRecWatchListFlag(boolean mytaskCaseRecWatchListFlag) {
		this.mytaskCaseRecWatchListFlag = mytaskCaseRecWatchListFlag;
	}
	/**
	 * @return Returns the mytaskCRsFlag.
	 */
	public boolean isMytaskCRsFlag() {
		return mytaskCRsFlag;
	}
	/**
	 * @param mytaskCRsFlag The mytaskCRsFlag to set.
	 */
	public void setMytaskCRsFlag(boolean mytaskCRsFlag) {
		this.mytaskCRsFlag = mytaskCRsFlag;
	}
	/**
	 * @return Returns the mytaskCRsWatchListFlag.
	 */
	public boolean isMytaskCRsWatchListFlag() {
		return mytaskCRsWatchListFlag;
	}
	/**
	 * @param mytaskCRsWatchListFlag The mytaskCRsWatchListFlag to set.
	 */
	public void setMytaskCRsWatchListFlag(boolean mytaskCRsWatchListFlag) {
		this.mytaskCRsWatchListFlag = mytaskCRsWatchListFlag;
	}
	/**
	 * @return Returns the mytaskGrpCaseRecsFlag.
	 */
	public boolean isMytaskGrpCaseRecsFlag() {
		return mytaskGrpCaseRecsFlag;
	}
	/**
	 * @param mytaskGrpCaseRecsFlag The mytaskGrpCaseRecsFlag to set.
	 */
	public void setMytaskGrpCaseRecsFlag(boolean mytaskGrpCaseRecsFlag) {
		this.mytaskGrpCaseRecsFlag = mytaskGrpCaseRecsFlag;
	}
	/**
	 * @return Returns the mytaskGrpCRsFlag.
	 */
	public boolean isMytaskGrpCRsFlag() {
		return mytaskGrpCRsFlag;
	}
	/**
	 * @param mytaskGrpCRsFlag The mytaskGrpCRsFlag to set.
	 */
	public void setMytaskGrpCRsFlag(boolean mytaskGrpCRsFlag) {
		this.mytaskGrpCRsFlag = mytaskGrpCRsFlag;
	}
	
	private boolean mytaskCRsCounter;
	private boolean mytaskGrpCRsCounter;
	private boolean mytaskCRsWatchListCounter;
	private boolean mytaskCaseRecsCounter;
	private boolean mytaskGrpCaseRecsCounter;
	private boolean mytaskCaseRecWatchListCounter;
	
	/**
	 * @return Returns the mytaskCaseRecsCounter.
	 */
	public boolean isMytaskCaseRecsCounter() {
		return mytaskCaseRecsCounter;
	}
	/**
	 * @param mytaskCaseRecsCounter The mytaskCaseRecsCounter to set.
	 */
	public void setMytaskCaseRecsCounter(boolean mytaskCaseRecsCounter) {
		this.mytaskCaseRecsCounter = mytaskCaseRecsCounter;
	}
	/**
	 * @return Returns the mytaskCaseRecWatchListCounter.
	 */
	public boolean isMytaskCaseRecWatchListCounter() {
		return mytaskCaseRecWatchListCounter;
	}
	/**
	 * @param mytaskCaseRecWatchListCounter The mytaskCaseRecWatchListCounter to set.
	 */
	public void setMytaskCaseRecWatchListCounter(
			boolean mytaskCaseRecWatchListCounter) {
		this.mytaskCaseRecWatchListCounter = mytaskCaseRecWatchListCounter;
	}
	/**
	 * @return Returns the mytaskCRsCounter.
	 */
	public boolean isMytaskCRsCounter() {
		return mytaskCRsCounter;
	}
	/**
	 * @param mytaskCRsCounter The mytaskCRsCounter to set.
	 */
	public void setMytaskCRsCounter(boolean mytaskCRsCounter) {
		this.mytaskCRsCounter = mytaskCRsCounter;
	}
	/**
	 * @return Returns the mytaskCRsWatchListCounter.
	 */
	public boolean isMytaskCRsWatchListCounter() {
		return mytaskCRsWatchListCounter;
	}
	/**
	 * @param mytaskCRsWatchListCounter The mytaskCRsWatchListCounter to set.
	 */
	public void setMytaskCRsWatchListCounter(boolean mytaskCRsWatchListCounter) {
		this.mytaskCRsWatchListCounter = mytaskCRsWatchListCounter;
	}
	/**
	 * @return Returns the mytaskGrpCaseRecsCounter.
	 */
	public boolean isMytaskGrpCaseRecsCounter() {
		return mytaskGrpCaseRecsCounter;
	}
	/**
	 * @param mytaskGrpCaseRecsCounter The mytaskGrpCaseRecsCounter to set.
	 */
	public void setMytaskGrpCaseRecsCounter(boolean mytaskGrpCaseRecsCounter) {
		this.mytaskGrpCaseRecsCounter = mytaskGrpCaseRecsCounter;
	}
	/**
	 * @return Returns the mytaskGrpCRsCounter.
	 */
	public boolean isMytaskGrpCRsCounter() {
		return mytaskGrpCRsCounter;
	}
	/**
	 * @param mytaskGrpCRsCounter The mytaskGrpCRsCounter to set.
	 */
	public void setMytaskGrpCRsCounter(boolean mytaskGrpCRsCounter) {
		this.mytaskGrpCRsCounter = mytaskGrpCRsCounter;
	}
	private boolean allData = true;
	/**
	 * @return Returns the allData.
	 */
	public boolean isAllData() {
		return allData;
	}
	/**
	 * @param allData The allData to set.
	 */
	public void setAllData(boolean allData) {
		this.allData = allData;
	}
	
	//540203_Performance_Fix_23Dec10 ends::
	/**
	 * @return Returns the myTaskAlertSk.
	 */
	public String getMyTaskAlertSk() {
		return myTaskAlertSk;
	}
	/**
	 * @param myTaskAlertSk The myTaskAlertSk to set.
	 */
	public void setMyTaskAlertSk(String myTaskAlertSk) {
		this.myTaskAlertSk = myTaskAlertSk;
	}
	//Added for heap dump start
	private int tabIndex=0;

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	//heap dump end
	
	//for fixing ESPRD00680070 defect
	private int startIndexForAlert=0;
	private int startIndexForMyCr=0;
	private int startIndexForGrpCr=0;
	private int startIndexForWtchList=0;
	private int startIndexForMyCase=0;
	private int startIndexForGrpCase=0;
	private int startIndexForGrpCaseWtchList=0;
	
	
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
	/**
	 * @return Returns the startIndexForGrpCase.
	 */
	public int getStartIndexForGrpCase() {
		return startIndexForGrpCase;
	}
	/**
	 * @param startIndexForGrpCase The startIndexForGrpCase to set.
	 */
	public void setStartIndexForGrpCase(int startIndexForGrpCase) {
		this.startIndexForGrpCase = startIndexForGrpCase;
	}
	/**
	 * @return Returns the startIndexForGrpCaseWtchList.
	 */
	public int getStartIndexForGrpCaseWtchList() {
		return startIndexForGrpCaseWtchList;
	}
	/**
	 * @param startIndexForGrpCaseWtchList The startIndexForGrpCaseWtchList to set.
	 */
	public void setStartIndexForGrpCaseWtchList(int startIndexForGrpCaseWtchList) {
		this.startIndexForGrpCaseWtchList = startIndexForGrpCaseWtchList;
	}
	/**
	 * @return Returns the startIndexForGrpCr.
	 */
	public int getStartIndexForGrpCr() {
		return startIndexForGrpCr;
	}
	/**
	 * @param startIndexForGrpCr The startIndexForGrpCr to set.
	 */
	public void setStartIndexForGrpCr(int startIndexForGrpCr) {
		this.startIndexForGrpCr = startIndexForGrpCr;
	}
	/**
	 * @return Returns the startIndexForMyCase.
	 */
	public int getStartIndexForMyCase() {
		return startIndexForMyCase;
	}
	/**
	 * @param startIndexForMyCase The startIndexForMyCase to set.
	 */
	public void setStartIndexForMyCase(int startIndexForMyCase) {
		this.startIndexForMyCase = startIndexForMyCase;
	}
	/**
	 * @return Returns the startIndexForMyCr.
	 */
	public int getStartIndexForMyCr() {
		return startIndexForMyCr;
	}
	/**
	 * @param startIndexForMyCr The startIndexForMyCr to set.
	 */
	public void setStartIndexForMyCr(int startIndexForMyCr) {
		this.startIndexForMyCr = startIndexForMyCr;
	}
	/**
	 * @return Returns the startIndexForWtchList.
	 */
	public int getStartIndexForWtchList() {
		return startIndexForWtchList;
	}
	/**
	 * @param startIndexForWtchList The startIndexForWtchList to set.
	 */
	public void setStartIndexForWtchList(int startIndexForWtchList) {
		this.startIndexForWtchList = startIndexForWtchList;
	}

	//for ESPRD00680070
	private int itemSelectedRowIndexMyCase=-1;
	private int itemSelectedRowIndexGrpCase=-1;
	private int itemSelectedRowIndexCaseWL=-1;
	
	
	/**
	 * @return Returns the itemSelectedRowIndexCaseWL.
	 */
	public int getItemSelectedRowIndexCaseWL() {
		return itemSelectedRowIndexCaseWL;
	}
	/**
	 * @param itemSelectedRowIndexCaseWL The itemSelectedRowIndexCaseWL to set.
	 */
	public void setItemSelectedRowIndexCaseWL(int itemSelectedRowIndexCaseWL) {
		this.itemSelectedRowIndexCaseWL = itemSelectedRowIndexCaseWL;
	}
	/**
	 * @return Returns the itemSelectedRowIndexGrpCase.
	 */
	public int getItemSelectedRowIndexGrpCase() {
		return itemSelectedRowIndexGrpCase;
	}
	/**
	 * @param itemSelectedRowIndexGrpCase The itemSelectedRowIndexGrpCase to set.
	 */
	public void setItemSelectedRowIndexGrpCase(int itemSelectedRowIndexGrpCase) {
		this.itemSelectedRowIndexGrpCase = itemSelectedRowIndexGrpCase;
	}
	/**
	 * @return Returns the itemSelectedRowIndexMyCase.
	 */
	public int getItemSelectedRowIndexMyCase() {
		return itemSelectedRowIndexMyCase;
	}
	/**
	 * @param itemSelectedRowIndexMyCase The itemSelectedRowIndexMyCase to set.
	 */
	public void setItemSelectedRowIndexMyCase(int itemSelectedRowIndexMyCase) {
		this.itemSelectedRowIndexMyCase = itemSelectedRowIndexMyCase;
	}
	
	/*private CaseViewVO caseViewVO;

	*//**
	 * @return the caseViewVO
	 *//*
	public CaseViewVO getCaseViewVO() {
		return caseViewVO;
	}

	*//**
	 * @param caseViewVO the caseViewVO to set
	 *//*
	public void setCaseViewVO(CaseViewVO caseViewVO) {
		this.caseViewVO = caseViewVO;
	}*/

	/**
	 * @return Returns the myTaskCaseDetailsVO.
	 */
	public CaseViewVO getMyTaskCaseDetailsVO() {
		return myTaskCaseDetailsVO;
	}

	/**
	 * @param myTaskCaseDetailsVO
	 *            The myTaskCaseDetailsVO to set.
	 */
	public void setMyTaskCaseDetailsVO(CaseViewVO myTaskCaseDetailsVO) {
		this.myTaskCaseDetailsVO = myTaskCaseDetailsVO;
	}
	
}