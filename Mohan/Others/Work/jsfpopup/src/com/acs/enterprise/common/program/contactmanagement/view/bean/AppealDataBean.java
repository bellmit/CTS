/*
 * Created on Jan 17, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;


import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AppealVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.view.vo.CommonCMCaseDetailsVO;
import com.acs.enterprise.common.view.vo.CommonMemberDetailsVO;
import com.acs.enterprise.common.view.vo.CommonProviderDetailsVO;


/**
 * @author sivngan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class AppealDataBean
        extends EnterpriseBaseDataBean
{

    /** Creates an instance of the logger. * */
    
	private static final EnterpriseLogger log = EnterpriseLogFactory
    .getLogger(AppealDataBean.class);

    /**
     * This field is used to store BEAN_NAME.
     */
	// Moved to ContactManagementConstants.java
    //public static final String BEAN_NAME = "appealDataBean";

    /**
     * This field is used to store COMMON_NOTES_BEAN_NAME.
     */
	// Moved to ContactManagementConstants.java 
    // public static final String COMMON_NOTES_BEAN_NAME = "commonNotesControllerBean";

	 /** holds Small Save Count. */
	private int smallSaveCount = 0;

	private HashMap validValues=  new HashMap();

    /**
     * Holds disableCase flag
     */
    private boolean disableCase = true;
    
    /**
     * Holds disableCase flag
     */
    private boolean validValueFlag = true;
    
    /**
     * Holds typeList Valid values
     */
    private List typeList = Collections.EMPTY_LIST;

    /**
     * Holds appealTypeList valid values
     */
    private List appealTypeList = Collections.EMPTY_LIST;
    /**
     * Holds contReasonList valid values
     */
    private List appealContReasonList = Collections.EMPTY_LIST;
    /**
     * Holds appealResultsList valid values
     */
    private List appealResultsList = Collections.EMPTY_LIST;

    /**
     * Holds appealStatusList valid values
     */
    private List appealStatusList = Collections.EMPTY_LIST;

    /**
     * Holds distOfficeList valid values
     */
    private List distOfficeList = Collections.EMPTY_LIST;

    /**
     * Holds motionTypeList valid values
     */
    private List motionTypeList = Collections.EMPTY_LIST;

    /**
     * Holds appealDHHSDecisionList valid values
     */
    private List appealDHHSDecisionList = Collections.EMPTY_LIST;

    /**
     * Holds decisionList valid values
     */
    private List decisionList = Collections.EMPTY_LIST;
    /**
     * Holds decisionList valid values
     */
    private List hearingResultsList = Collections.EMPTY_LIST;
    /**
     * Holds addlInfoCaseFile valid values
     */
    private List addlInfoFileList = Collections.EMPTY_LIST;
    /**
     * Holds Appeal TempVoList
     */
    //FOR THE HEAPDUMP ISSUE
    //private List appealTempList = new ArrayList();
    /**
     * this flag is used to render continuance reason
     */
    private boolean showContReasonFlag = false;
    /**
     * this flag is used to render member appeals Data table
     */
    private boolean memberAppealDataFlag = false;

    /**
     * this flag is used to render provider appeals Data table
     */
    private boolean providerAppealDataFlag = false;

    /**
     * This flag is used to render Add Appeal
     */
    private boolean addAppealFlag = false;

    /**
     * This flag is used to render edit Appeal
     */
    private boolean editAppealFlag = false;

    /**
     * This flag is used to render Add Administrative Hearing
     */
    private boolean addAdminHearingFlag = false;

    /**
     * This flag is used to render edit Administrative Hearing
     */
    private boolean editAdminHearingFlag = false;

    /**
     * This flag is used to show success msg
     */
    private boolean showSuccessMsgFlag = false;

    /**
     * This flag is used to show final success msg
     */
    private boolean showFinalSuccessMsgFlag = false;

    /**
     * This flag is used to show deleted msg
     */
    private boolean showDeletedMsgFlag = false;

    /**
     * This flag is used to show case component
     */
    private boolean showClaimCompFlag = false;

    /**
     * This flag is used to show SA component
     */
    private boolean showSACompFlag = false;

    /**
     * This flag is used to show Member Information label when entity is Member
     */
    private boolean showMemberInfoLabelFlag = false;

    /**
     * This flag is used to show provider Information label when entity is
     * Provider
     */
    private boolean showProviderInfoLabelFlag = false;

    /**
     * This flag is used to show appeals page for member
     */
    private boolean showMemberAppealsFlag = false;

    /**
     * This flag is used to show appeals page for provider
     */
    private boolean showProviderAppealsFlag = false;

    /**
     * Holds adminHearingList.
     */
    private List adminHearingList = new ArrayList();

    /**
     * Holds deleted adminHearingList.
     */
    private List deletedAdminHearingList = new ArrayList();
    
    /**
     * Holds memberAppealList
     */
    private List memberAppealList = new ArrayList();

    /**
     * Holds providerAppealList
     */
    private List providerAppealList = new ArrayList();

    /** Holds variable for maintaining the index of the record selected */
    private String adminHearingIndex;
    
    /** Holds variable for maintaining the index of the record selected */
    private String memAppealIndex;
    
    /**Holds the variable for maintaining the index of the provider appeal */
    private String prvAppealIndex;
    /** Holds flag to display no data table */
    private boolean adminHearingDataFlag = false;

    /**
     * Holds variable imageRenderer.
     */
    private String imageRender;
    

    /** This holds providerID */
	private String providerID;

	/** This holds providerSysID */
	private String providerSysID;

    /**
	 * Holds variable memAppealImageRender.
	 */
    private String memAppealImageRender;

    /** This is used to show audit log for appeal details page. */
    private boolean appealAuditHistoryRender = false;

    /** This is used to hold audit field history list for appeals. */
    private List appealAuditHistoryList = new ArrayList();

    /** This is used to hold the state of the Audit block open or closed. */
    private boolean appealAuditOpen = false;

    /** This is used to hold the state of the Audit block open or closed. */
    private String appealAuditHidden;

    /** This is used to show audit log for appeal details page. */
    private boolean adminHearAuditHistoryRender = false;

    /** This is used to hold audit field history list for appeals. */
    private List adminHearAuditHistoryList = new ArrayList();

    /** This is used to hold the state of the Audit block open or closed. */
    private boolean adminHearAuditOpen = false;

    /** This is used to hold the state of the Audit block open or closed. */
    private String adminHearAuditHidden;

    /** This holds Common member details VO. */
    private CommonMemberDetailsVO commonMemberDetailsVO;

    /** This holds Common CM Case Details VO. */
    private CommonCMCaseDetailsVO commonCMCaseDetailsVO;

    /** This holds Common CM Case Details VO. */
    private CommonProviderDetailsVO commonProviderDetailsVO;

    /** This holds memberID */
    private String memberID;

    /** This holds memberSysID */
    private String memberSysID;

    /** This holds memberName */
    private String memberName;

    /** This holds entityType */
    private String entityType;
    
    /** This holds entityID */
    private String entityID;
    
    /** This holds entityName */
    private String entityName;
    
    /** This holds caseSK */
    private String caseSK;
    
    /** This holds NotesCount */
    private int notesCount;

    /** This holds userid */
    private String UserID;
    
    /** Holds the logged in used id  */
    private String loggedInUserId;    

    private boolean cancelFlag = true;
    /**
     * Comment for <code>appealVO</code>
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private AppealVO appealVO = new AppealVO();
    /***
     * 
     *
     */
    

    /**
     * This flag is used to select all check boxes of SA Line item for provider
     */

    private boolean selectALLSALineItemFlag = false;
    
    /**
     * Added disable property for Grey mode
     */

    private boolean disableAddLink = false;
    
    /**
     * Disables Save link in Member Appeals
     */

    private boolean disableSaveMemApp = false;
    
    /**
     * Disables Reset link in Member Appeals
     */

    private boolean disableResetMemApp = false;
    
    /**
     * Disables Save link in Provider Appeals
     */

    private boolean disableSaveProvApp = false;
    
    /**
     * Disables Reset link in Provider Appeals
     */

    private boolean disableResetProvApp = false;
    
    /**
     * Disables Add Admin Button in Appeals
     */

    private boolean disableAppAddAdminHear = false;
    
    /**
     * Disables Add Admin Button in Appeals
     */

    private boolean disableAppSaveAdminHear = false;
    
    /**
     * Disables Add Admin Button in Appeals
     */

    private boolean disableAppResetAdminHear = false;
    
    /**
     * Disables Add Admin Button in Appeals
     */

    private boolean disableAppDelAdminHear = false;
    
    /**
     * @return Returns the selectALLSALineItemFlag.
     */
    public boolean isSelectALLSALineItemFlag() {
        return selectALLSALineItemFlag;
    }

    /**
     * @param selectALLSALineItemFlag
     *            The selectALLSALineItemFlag to set.
     */
    public void setSelectALLSALineItemFlag(boolean selectALLSALineItemFlag) {
        this.selectALLSALineItemFlag = selectALLSALineItemFlag;
    }

    /** Holds provider case appeal SA Data item list */
    private List providerSADataItemList = new ArrayList();

    /**
     * @return Returns the providerSADataItemList.
     */
    public List getProviderSADataItemList() {
        return providerSADataItemList;
    }

    /**
     * @param providerSADataItemList
     *            The providerSADataItemList to set.
     */
    public void setProviderSADataItemList(List providerSADataItemList) {
        this.providerSADataItemList = providerSADataItemList;
    }

    
    private AppealVO tempAppealVO;

    
    //ADDED BY ICS GAP-11022
    
    /**
     * Holds decisionList valid values
     */
    private List hearingStatusList = Collections.EMPTY_LIST;

	//END BY ICS GAP-11022
    
    /**
     * @return Returns the appealVO.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public AppealVO getAppealVO()
    {
        return appealVO;
    }

    /**
     * @param theappealVO
     *            The appealVO to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAppealVO(final AppealVO theappealVO)
    {
        appealVO = theappealVO;
    }

    /**
     * @return Returns the addAdminHearingFlag.
     */
    public boolean isAddAdminHearingFlag()
    {
        return addAdminHearingFlag;
    }

    /**
     * @param addAdminHearingFlag
     *            The addAdminHearingFlag to set.
     */
    public void setAddAdminHearingFlag(boolean addAdminHearingFlag)
    {
        this.addAdminHearingFlag = addAdminHearingFlag;
    }

    /**
     * @return Returns the showSuccessMsgFlag.
     */
    public boolean isShowSuccessMsgFlag()
    {
        return showSuccessMsgFlag;
    }

    /**
     * @param showSuccessMsgFlag
     *            The showSuccessMsgFlag to set.
     */
    public void setShowSuccessMsgFlag(boolean showSuccessMsgFlag)
    {
        this.showSuccessMsgFlag = showSuccessMsgFlag;
    }

    /**
     * @return Returns the editAdminHearingFlag.
     */
    public boolean isEditAdminHearingFlag()
    {
        return editAdminHearingFlag;
    }

    /**
     * @param editAdminHearingFlag
     *            The editAdminHearingFlag to set.
     */
    public void setEditAdminHearingFlag(boolean editAdminHearingFlag)
    {
        this.editAdminHearingFlag = editAdminHearingFlag;
    }

    /**
     * @return Returns the adminHearingList.
     */
    public List getAdminHearingList()
    {
        return adminHearingList;
    }

    /**
     * @param adminHearingList
     *            The adminHearingList to set.
     */
    public void setAdminHearingList(List adminHearingList)
    {
        this.adminHearingList = adminHearingList;
    }

    /**
     * @return Returns the adminHearingIndex.
     */
    public String getAdminHearingIndex()
    {
        return adminHearingIndex;
    }

    /**
     * @param adminHearingIndex
     *            The adminHearingIndex to set.
     */
    public void setAdminHearingIndex(final String adminHearingIndex)
    {
        this.adminHearingIndex = adminHearingIndex;
    }

    /**
     * @return Returns the adminHearingDataFlag.
     */
    public boolean isAdminHearingDataFlag()
    {
        return adminHearingDataFlag;
    }

    /**
     * @param adminHearingDataFlag
     *            The adminHearingDataFlag to set.
     */
    public void setAdminHearingDataFlag(boolean adminHearingDataFlag)
    {
        this.adminHearingDataFlag = adminHearingDataFlag;
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
    public void setImageRender(final String imageRender)
    {
        this.imageRender = imageRender;
    }

    /**
     * @return Returns the showDeletedMsgFlag.
     */
    public boolean isShowDeletedMsgFlag()
    {
        return showDeletedMsgFlag;
    }

    /**
     * @param showDeletedMsgFlag
     *            The showDeletedMsgFlag to set.
     */
    public void setShowDeletedMsgFlag(boolean showDeletedMsgFlag)
    {
        this.showDeletedMsgFlag = showDeletedMsgFlag;
    }

    /**
     * @return Returns the showSACompFlag.
     */
    public boolean isShowSACompFlag()
    {
        return showSACompFlag;
    }

    /**
     * @param showSACompFlag
     *            The showSACompFlag to set.
     */
    public void setShowSACompFlag(boolean showSACompFlag)
    {
        this.showSACompFlag = showSACompFlag;
    }

    /**
     * @return Returns the showClaimCompFlag.
     */
    public boolean isShowClaimCompFlag()
    {
        return showClaimCompFlag;
    }

    /**
     * @param showClaimCompFlag
     *            The showClaimCompFlag to set.
     */
    public void setShowClaimCompFlag(boolean showClaimCompFlag)
    {
        this.showClaimCompFlag = showClaimCompFlag;
    }

    /**
     * @return Returns the typeList.
     */
    public List getTypeList()
    {
        return typeList;
    }

    /**
     * @param typeList
     *            The typeList to set.
     */
    public void setTypeList(List typeList)
    {
        this.typeList = typeList;
    }

    /**
     * @return Returns the appealTypeList.
     */
    public List getAppealTypeList()
    {
        return appealTypeList;
    }

    /**
     * @param appealTypeList
     *            The appealTypeList to set.
     */
    public void setAppealTypeList(List appealTypeList)
    {
        this.appealTypeList = appealTypeList;
    }

    /**
     * @return Returns the appealStatusList.
     */
    public List getAppealStatusList()
    {
        return appealStatusList;
    }

    /**
     * @param appealStatusList
     *            The appealStatusList to set.
     */
    public void setAppealStatusList(List appealStatusList)
    {
        this.appealStatusList = appealStatusList;
    }

    /**
     * @return Returns the appealResultsList.
     */
    public List getAppealResultsList()
    {
        return appealResultsList;
    }

    /**
     * @param appealResultsList
     *            The appealResultsList to set.
     */
    public void setAppealResultsList(List appealResultsList)
    {
        this.appealResultsList = appealResultsList;
    }

    /**
     * @return Returns the distOfficeList.
     */
    public List getDistOfficeList()
    {
        return distOfficeList;
    }

    /**
     * @param distOfficeList
     *            The distOfficeList to set.
     */
    public void setDistOfficeList(List distOfficeList)
    {
        this.distOfficeList = distOfficeList;
    }

    /**
     * @return Returns the motionTypeList.
     */
    public List getMotionTypeList()
    {
        return motionTypeList;
    }

    /**
     * @param motionTypeList
     *            The motionTypeList to set.
     */
    public void setMotionTypeList(List motionTypeList)
    {
        this.motionTypeList = motionTypeList;
    }

    /**
     * @return Returns the decisionList.
     */
    public List getDecisionList()
    {
        return decisionList;
    }

    /**
     * @param decisionList
     *            The decisionList to set.
     */
    public void setDecisionList(List decisionList)
    {
        this.decisionList = decisionList;
    }

    /**
     * @return Returns the hearingResultsList.
     */
    public List getHearingResultsList()
    {
        return hearingResultsList;
    }

    /**
     * @param hearingResultsList
     *            The hearingResultsList to set.
     */
    public void setHearingResultsList(List hearingResultsList)
    {
        this.hearingResultsList = hearingResultsList;
    }

    /**
     * @return Returns the appealDHHSDecisionList.
     */
    public List getAppealDHHSDecisionList()
    {
        return appealDHHSDecisionList;
    }

    /**
     * @param appealDHHSDecisionList
     *            The appealDHHSDecisionList to set.
     */
    public void setAppealDHHSDecisionList(List appealDHHSDecisionList)
    {
        this.appealDHHSDecisionList = appealDHHSDecisionList;
    }

    /**
     * @return Returns the addAppealFlag.
     */
    public boolean isAddAppealFlag()
    {
        return addAppealFlag;
    }

    /**
     * @param addAppealFlag
     *            The addAppealFlag to set.
     */
    public void setAddAppealFlag(boolean addAppealFlag)
    {
        this.addAppealFlag = addAppealFlag;
    }

    /**
     * @return Returns the editAppealFlag.
     */
    public boolean isEditAppealFlag()
    {
        return editAppealFlag;
    }

    /**
     * @param editAppealFlag
     *            The editAppealFlag to set.
     */
    public void setEditAppealFlag(boolean editAppealFlag)
    {
        this.editAppealFlag = editAppealFlag;
    }

    /**
     * @return Returns the showFinalSuccessMsgFlag.
     */
    public boolean isShowFinalSuccessMsgFlag()
    {
        return showFinalSuccessMsgFlag;
    }

    /**
     * @param showFinalSuccessMsgFlag
     *            The showFinalSuccessMsgFlag to set.
     */
    public void setShowFinalSuccessMsgFlag(boolean showFinalSuccessMsgFlag)
    {
        this.showFinalSuccessMsgFlag = showFinalSuccessMsgFlag;
    }

    /**
     * @return Returns the memberAppealList.
     */
    public List getMemberAppealList()
    {
        return memberAppealList;
    }

    /**
     * @param memberAppealList
     *            The memberAppealList to set.
     */
    public void setMemberAppealList(List memberAppealList)
    {
        this.memberAppealList = memberAppealList;
    }

    /**
     * @return Returns the memberAppealDataFlag.
     */
    public boolean isMemberAppealDataFlag()
    {
        return memberAppealDataFlag;
    }

    /**
     * @param memberAppealDataFlag
     *            The memberAppealDataFlag to set.
     */
    public void setMemberAppealDataFlag(boolean memberAppealDataFlag)
    {
        this.memberAppealDataFlag = memberAppealDataFlag;
    }

    /**
     * @return Returns the memAppealImageRender.
     */
    public String getMemAppealImageRender()
    {
        return memAppealImageRender;
    }

    /**
     * @param memAppealImageRender
     *            The memAppealImageRender to set.
     */
    public void setMemAppealImageRender(String memAppealImageRender)
    {
        this.memAppealImageRender = memAppealImageRender;
    }

    /**
     * @return Returns the appealAuditHidden.
     */
    public String getAppealAuditHidden()
    {
        return appealAuditHidden;
    }

    /**
     * @return Returns the appealAuditHistoryList.
     */
    public List getAppealAuditHistoryList()
    {
        return appealAuditHistoryList;
    }

    /**
     * @return Returns the appealAuditHistoryRender.
     */
    public boolean isAppealAuditHistoryRender()
    {
        return appealAuditHistoryRender;
    }

    /**
     * @return Returns the appealAuditOpen.
     */
    public boolean isAppealAuditOpen()
    {
        return appealAuditOpen;
    }

    /**
     * @param appealAuditHidden
     *            The appealAuditHidden to set.
     */
    public void setAppealAuditHidden(String appealAuditHidden)
    {
        this.appealAuditHidden = appealAuditHidden;
    }

    /**
     * @param appealAuditHistoryList
     *            The appealAuditHistoryList to set.
     */
    public void setAppealAuditHistoryList(List appealAuditHistoryList)
    {
        this.appealAuditHistoryList = appealAuditHistoryList;
    }

    /**
     * @param appealAuditHistoryRender
     *            The appealAuditHistoryRender to set.
     */
    public void setAppealAuditHistoryRender(boolean appealAuditHistoryRender)
    {
        this.appealAuditHistoryRender = appealAuditHistoryRender;
    }

    /**
     * @param appealAuditOpen
     *            The appealAuditOpen to set.
     */
    public void setAppealAuditOpen(boolean appealAuditOpen)
    {
        this.appealAuditOpen = appealAuditOpen;
    }

    /**
     * @return Returns the adminHearAuditHidden.
     */
    public String getAdminHearAuditHidden()
    {
        return adminHearAuditHidden;
    }

    /**
     * @return Returns the adminHearAuditHistoryList.
     */
    public List getAdminHearAuditHistoryList()
    {
        return adminHearAuditHistoryList;
    }

    /**
     * @return Returns the adminHearAuditHistoryRender.
     */
    public boolean isAdminHearAuditHistoryRender()
    {
        return adminHearAuditHistoryRender;
    }

    /**
     * @return Returns the adminHearAuditOpen.
     */
    public boolean isAdminHearAuditOpen()
    {
        return adminHearAuditOpen;
    }

    /**
     * @param adminHearAuditHidden
     *            The adminHearAuditHidden to set.
     */
    public void setAdminHearAuditHidden(String adminHearAuditHidden)
    {
        this.adminHearAuditHidden = adminHearAuditHidden;
    }

    /**
     * @param adminHearAuditHistoryList
     *            The adminHearAuditHistoryList to set.
     */
    public void setAdminHearAuditHistoryList(List adminHearAuditHistoryList)
    {
        this.adminHearAuditHistoryList = adminHearAuditHistoryList;
    }

    /**
     * @param adminHearAuditHistoryRender
     *            The adminHearAuditHistoryRender to set.
     */
    public void setAdminHearAuditHistoryRender(
            boolean adminHearAuditHistoryRender)
    {
        this.adminHearAuditHistoryRender = adminHearAuditHistoryRender;
    }

    /**
     * @param adminHearAuditOpen
     *            The adminHearAuditOpen to set.
     */
    public void setAdminHearAuditOpen(boolean adminHearAuditOpen)
    {
        this.adminHearAuditOpen = adminHearAuditOpen;
    }

    /**
     * @return Returns the showMemberInfoLabelFlag.
     */
    public boolean isShowMemberInfoLabelFlag()
    {
        return showMemberInfoLabelFlag;
    }

    /**
     * @param showMemberInfoLabelFlag
     *            The showMemberInfoLabelFlag to set.
     */
    public void setShowMemberInfoLabelFlag(boolean showMemberInfoLabelFlag)
    {
        this.showMemberInfoLabelFlag = showMemberInfoLabelFlag;
    }

    /**
     * @return Returns the showProviderInfoLabelFlag.
     */
    public boolean isShowProviderInfoLabelFlag()
    {
        return showProviderInfoLabelFlag;
    }

    /**
     * @param showProviderInfoLabelFlag
     *            The showProviderInfoLabelFlag to set.
     */
    public void setShowProviderInfoLabelFlag(boolean showProviderInfoLabelFlag)
    {
        this.showProviderInfoLabelFlag = showProviderInfoLabelFlag;
    }

    /**
     * @return Returns the showMemberAppealsFlag.
     */
    public boolean isShowMemberAppealsFlag()
    {
        return showMemberAppealsFlag;
    }

    /**
     * @param showMemberAppealsFlag
     *            The showMemberAppealsFlag to set.
     */
    public void setShowMemberAppealsFlag(boolean showMemberAppealsFlag)
    {
        this.showMemberAppealsFlag = showMemberAppealsFlag;
    }

    /**
     * @return Returns the commonMemberDetailsVO.
     */
    public CommonMemberDetailsVO getCommonMemberDetailsVO()
    {
        return commonMemberDetailsVO;
    }

    /**
     * @param commonMemberDetailsVO
     *            The commonMemberDetailsVO to set.
     */
    public void setCommonMemberDetailsVO(
            CommonMemberDetailsVO commonMemberDetailsVO)
    {
        this.commonMemberDetailsVO = commonMemberDetailsVO;
    }

    /**
     * @return Returns the providerAppealList.
     */
    public List getProviderAppealList()
    {
     return providerAppealList;        
    }

    /**
     * @param providerAppealList
     *            The providerAppealList to set.
     */
    public void setProviderAppealList(List providerAppealList)
    {
        this.providerAppealList = providerAppealList;
    }

    /**
     * @return Returns the providerAppealDataFlag.
     */
    public boolean isProviderAppealDataFlag()
    {
        return providerAppealDataFlag;
    }

    /**
     * @param providerAppealDataFlag
     *            The providerAppealDataFlag to set.
     */
    public void setProviderAppealDataFlag(boolean providerAppealDataFlag)
    {
        this.providerAppealDataFlag = providerAppealDataFlag;
    }

    /**
     * @return Returns the showProviderAppealsFlag.
     */
    public boolean isShowProviderAppealsFlag()
    {
        return showProviderAppealsFlag;
    }

    /**
     * @param showProviderAppealsFlag
     *            The showProviderAppealsFlag to set.
     */
    public void setShowProviderAppealsFlag(boolean showProviderAppealsFlag)
    {
        this.showProviderAppealsFlag = showProviderAppealsFlag;
    }

    /**
     * @return Returns the memberID.
     */
    public String getMemberID()
    {
        return memberID;
    }

    /**
     * @return Returns the memberName.
     */
    public String getMemberName()
    {
        return memberName;
    }

    /**
     * @return Returns the memberSysID.
     */
    public String getMemberSysID()
    {
        return memberSysID;
    }

    /**
     * @param memberID
     *            The memberID to set.
     */
    public void setMemberID(String memberID)
    {
        this.memberID = memberID;
    }

    /**
     * @param memberName
     *            The memberName to set.
     */
    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }

    /**
     * @param memberSysID
     *            The memberSysID to set.
     */
    public void setMemberSysID(String memberSysID)
    {
        this.memberSysID = memberSysID;
    }

    /**
     * @return Returns the commonCMCaseDetailsVO.
     */
    public CommonCMCaseDetailsVO getCommonCMCaseDetailsVO()
    {
        return commonCMCaseDetailsVO;
    }

    /**
     * @param commonCMCaseDetailsVO
     *            The commonCMCaseDetailsVO to set.
     */
    public void setCommonCMCaseDetailsVO(
            CommonCMCaseDetailsVO commonCMCaseDetailsVO)
    {
        this.commonCMCaseDetailsVO = commonCMCaseDetailsVO;
    }

    /**
     * @return Returns the commonProviderDetailsVO.
     */
    public CommonProviderDetailsVO getCommonProviderDetailsVO()
    {
        return commonProviderDetailsVO;
    }

    /**
     * @param commonProviderDetailsVO
     *            The commonProviderDetailsVO to set.
     */
    public void setCommonProviderDetailsVO(
            CommonProviderDetailsVO commonProviderDetailsVO)
    {
        this.commonProviderDetailsVO = commonProviderDetailsVO;
    }

    /**
     * @return Returns the entityType.
     */
    public String getEntityType()
    {
        return entityType;
    }

    /**
     * @param entityType
     *            The entityType to set.
     */
    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }
    /**
     * @return Returns the deletedAdminHearingList.
     */
    public List getDeletedAdminHearingList()
    {
        return deletedAdminHearingList;
    }
    /**
     * @param deletedAdminHearingList The deletedAdminHearingList to set.
     */
    public void setDeletedAdminHearingList(List deletedAdminHearingList)
    {
        this.deletedAdminHearingList = deletedAdminHearingList;
    }
    /**
     * @return Returns the notesCount.
     */
    public int getNotesCount()
    {
        return notesCount;
    }
    /**
     * @param notesCount The notesCount to set.
     */
    public void setNotesCount(int notesCount)
    {
        this.notesCount = notesCount;
    }
    /**
     * @return Returns the memAppealIndex.
     */
    public String getMemAppealIndex()
    {
        return memAppealIndex;
    }
    /**
     * @param memAppealIndex The memAppealIndex to set.
     */
    public void setMemAppealIndex(String memAppealIndex)
    {
        this.memAppealIndex = memAppealIndex;
    }
    /**
     * @return Returns the entityID.
     */
    public String getEntityID()
    {
        return entityID;
    }
    /**
     * @return Returns the entityName.
     */
    public String getEntityName()
    {
        return entityName;
    }
    /**
     * @param entityID The entityID to set.
     */
    public void setEntityID(String entityID)
    {
        this.entityID = entityID;
    }
    /**
     * @param entityName The entityName to set.
     */
    public void setEntityName(String entityName)
    {
        this.entityName = entityName;
    }
    /**
     * @return Returns the caseSK.
     */
    public String getCaseSK()
    {
        return caseSK;
    }
    /**
     * @param caseSK The caseSK to set.
     */
    public void setCaseSK(String caseSK)
    {
        this.caseSK = caseSK;
    }
    /**
     * @return Returns the prvAppealIndex.
     */
    public String getPrvAppealIndex()
    {
        return prvAppealIndex;
    }
    /**
     * @param prvAppealIndex The prvAppealIndex to set.
     */
    public void setPrvAppealIndex(String prvAppealIndex)
    {
        this.prvAppealIndex = prvAppealIndex;
    }
    /**
     * @return Returns the appealContReasonList.
     */
    public List getAppealContReasonList()
    {
        return appealContReasonList;
    }
    /**
     * @param appealContReasonList The appealContReasonList to set.
     */
    public void setAppealContReasonList(List appealContReasonList)
    {
        this.appealContReasonList = appealContReasonList;
    }
    /**
     * @return Returns the showContReasonFlag.
     */
    public boolean isShowContReasonFlag()
    {
        return showContReasonFlag;
    }
    /**
     * @param showContReasonFlag The showContReasonFlag to set.
     */
    public void setShowContReasonFlag(boolean showContReasonFlag)
    {
        this.showContReasonFlag = showContReasonFlag;
    }
    /**
     * @return Returns the appealTempList.
     */
    //FOR THE HEAP DUMP ISSUE
   /* public List getAppealTempList()
    {
        return appealTempList;
    }
    *//**
     * @param appealTempList The appealTempList to set.
     *//*
   public void setAppealTempList(List appealTempList)
    {
        this.appealTempList = appealTempList;
    }*/
    /**
     * @return Returns the tempAppealVO.
     */
    public AppealVO getTempAppealVO()
    {
        return tempAppealVO;
    }
    /**
     * @param tempAppealVO The tempAppealVO to set.
     */
    public void setTempAppealVO(AppealVO tempAppealVO)
    {
        this.tempAppealVO = tempAppealVO;
    }
    /**
     * @return Returns the addlInfoFileList.
     */
    public List getAddlInfoFileList()
    {
        return addlInfoFileList;
    }
    /**
     * @param addlInfoFileList The addlInfoFileList to set.
     */
    public void setAddlInfoFileList(List addlInfoFileList)
    {
        this.addlInfoFileList = addlInfoFileList;
    }
    
    
    //Added By ICS-GAP-11022
    /**
     * @return Returns the hearingStatusList.
     */
    public List getHearingStatusList()
    {
        return hearingStatusList;
    }

    /**
     * @param hearingStatusList
     *            The hearingStatusList to set.
     */
    public void setHearingStatusList(List hearingStatusList)
    {
        this.hearingStatusList = hearingStatusList;
    }
    
    private boolean saLineItemDataFlag = false;
    
    /**Holds provider case appeal SA Data item list */    
    private List memberSALineItemList = new ArrayList();
   
	
    /**
     * @return Returns the memberSALineItemList.
     */
    public List getMemberSALineItemList() {
    	return memberSALineItemList;
    }
    /**
     * @param memberSALineItemList The memberSALineItemList to set.
     */
    public void setMemberSALineItemList(List memberSALineItemList) {
    	this.memberSALineItemList = memberSALineItemList;
    }
	
    /**
     * @return Returns the saLineItemDataFlag.
     */
    public boolean isSaLineItemDataFlag() {
    	return saLineItemDataFlag;
    }
    /**
     * @param saLineItemDataFlag The saLineItemDataFlag to set.
     */
    public void setSaLineItemDataFlag(boolean saLineItemDataFlag) {
    	this.saLineItemDataFlag = saLineItemDataFlag;
    }
    /**
	 * @return Returns the smallSaveCount.
	 */


public int getSmallSaveCount() {
	return smallSaveCount;
	}

	/**
	 * @param smallSaveCount The smallSaveCount to set.
	 */
public void setSmallSaveCount(int smallSaveCount) {
this.smallSaveCount = smallSaveCount;
	}    
    
    
	/**
	 * @return Returns the disableAddLink.
	 */
	public boolean isDisableAddLink() {
		return disableAddLink;
	}
	/**
	 * @param disableAddLink The disableAddLink to set.
	 */
	public void setDisableAddLink(boolean disableAddLink) {
		this.disableAddLink = disableAddLink;
	}
	  //   Added by ICS for GAP # 11021
	/**
	 * @return Returns the userID.
	 */
	public String getUserID() {
		return UserID;
	}
	/**
	 * @param userID The userID to set.
	 */
	public void setUserID(String userID) {
		UserID = userID;
	}
   //Ended for GAP # 11021 
	/**
	 * @return Returns the disableCase.
	 */
	public boolean isDisableCase() {
		return disableCase;
	}
	/**
	 * @param disableCase The disableCase to set.
	 */
	public void setDisableCase(boolean disableCase) {
		this.disableCase = disableCase;
	}
	  /**
	   * @return Returns the providerID.
	   */
	public String getProviderID() {
		return providerID;
	}

	/**
	 * @param providerID
	 *            The providerID to set.
	 */
	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	/**
	 * @return Returns the providerSysID.
	 */
	public String getProviderSysID() {
		return providerSysID;
	}

	/**
	 * @param providerSysID
	 *            The providerSysID to set.
	 */
	public void setProviderSysID(String providerSysID) {
		this.providerSysID = providerSysID;
	}
	/**
	 * @return Returns the disableSaveMemApp.
	 */
	public boolean isDisableSaveMemApp() {
		return disableSaveMemApp;
	}
	/**
	 * @param disableSaveMemApp The disableSaveMemApp to set.
	 */
	public void setDisableSaveMemApp(boolean disableSaveMemApp) {
		this.disableSaveMemApp = disableSaveMemApp;
	}
	/**
	 * @return Returns the disableResetMemApp.
	 */
	public boolean isDisableResetMemApp() {
		return disableResetMemApp;
	}
	/**
	 * @param disableResetMemApp The disableResetMemApp to set.
	 */
	public void setDisableResetMemApp(boolean disableResetMemApp) {
		this.disableResetMemApp = disableResetMemApp;
	}
	/**
	 * @return Returns the disableSaveProvApp.
	 */
	public boolean isDisableSaveProvApp() {
		return disableSaveProvApp;
	}
	/**
	 * @param disableSaveProvApp The disableSaveProvApp to set.
	 */
	public void setDisableSaveProvApp(boolean disableSaveProvApp) {
		this.disableSaveProvApp = disableSaveProvApp;
	}
	/**
	 * @return Returns the disableResetProvApp.
	 */
	public boolean isDisableResetProvApp() {
		return disableResetProvApp;
	}
	/**
	 * @param disableResetProvApp The disableResetProvApp to set.
	 */
	public void setDisableResetProvApp(boolean disableResetProvApp) {
		this.disableResetProvApp = disableResetProvApp;
	}
	/**
	 * @return Returns the disableAppAddAdminHear.
	 */
	public boolean isDisableAppAddAdminHear() {
		return disableAppAddAdminHear;
	}
	/**
	 * @param disableAppAddAdminHear The disableAppAddAdminHear to set.
	 */
	public void setDisableAppAddAdminHear(boolean disableAppAddAdminHear) {
		this.disableAppAddAdminHear = disableAppAddAdminHear;
	}
	/**
	 * @return Returns the disableAppDelAdminHear.
	 */
	public boolean isDisableAppDelAdminHear() {
		return disableAppDelAdminHear;
	}
	/**
	 * @param disableAppDelAdminHear The disableAppDelAdminHear to set.
	 */
	public void setDisableAppDelAdminHear(boolean disableAppDelAdminHear) {
		this.disableAppDelAdminHear = disableAppDelAdminHear;
	}
	/**
	 * @return Returns the disableAppResetAdminHear.
	 */
	public boolean isDisableAppResetAdminHear() {
		return disableAppResetAdminHear;
	}
	/**
	 * @param disableAppResetAdminHear The disableAppResetAdminHear to set.
	 */
	public void setDisableAppResetAdminHear(boolean disableAppResetAdminHear) {
		this.disableAppResetAdminHear = disableAppResetAdminHear;
	}
	/**
	 * @return Returns the disableAppSaveAdminHear.
	 */
	public boolean isDisableAppSaveAdminHear() {
		return disableAppSaveAdminHear;
	}
	/**
	 * @param disableAppSaveAdminHear The disableAppSaveAdminHear to set.
	 */
	public void setDisableAppSaveAdminHear(boolean disableAppSaveAdminHear) {
		this.disableAppSaveAdminHear = disableAppSaveAdminHear;
	}
	

	/**
     * Disables save link in Appeals
     */

    private boolean disableSaveAppeal;
    
    /**
     * Disables reset link in Appeals
     */

    private boolean disableResetAppeal;
	/**
	 * @return Returns the disableResetAppeal.
	 */
	public boolean isDisableResetAppeal() {
		return disableResetAppeal;
	}
	/**
	 * @param disableResetAppeal The disableResetAppeal to set.
	 */
	public void setDisableResetAppeal(boolean disableResetAppeal) {
		this.disableResetAppeal = disableResetAppeal;
	}
	/**
	 * @return Returns the disableSaveAppeal.
	 */
	public boolean isDisableSaveAppeal() {
		return disableSaveAppeal;
	}
	/**
	 * @param disableSaveAppeal The disableSaveAppeal to set.
	 */
	public void setDisableSaveAppeal(boolean disableSaveAppeal) {
		this.disableSaveAppeal = disableSaveAppeal;
	}
	
	
	/**
	 * @return Returns the validValues.
	 */
	public HashMap getValidValues() {
		return validValues;
	}
	/**
	 * @param validValues The validValues to set.
	 */
	public void setValidValues(HashMap validValues) {
		this.validValues = validValues;
	}
	
	
	/**
	 * @return Returns the loggedInUserId.
	 */
	public String getLoggedInUserId() {
		return loggedInUserId;
	}
	/**
	 * @param loggedInUserId The loggedInUserId to set.
	 */
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	
	
	/**
	 * @return Returns the validValueFlag.
	 */
	public boolean isValidValueFlag() {
		return validValueFlag;
	}
	/**
	 * @param validValueFlag The validValueFlag to set.
	 */
	public void setValidValueFlag(boolean validValueFlag) {
		this.validValueFlag = validValueFlag;
	}
	//ESPRD00509203_ProviderAppeals_03AUG2010
	private boolean enableAppealDetailAudit = false;
	/**
	 * @return Returns the enableAppealDetailAudit.
	 */
	public boolean isEnableAppealDetailAudit() {
		return enableAppealDetailAudit;
	}
	/**
	 * @param enableAppealDetailAudit The enableAppealDetailAudit to set.
	 */
	public void setEnableAppealDetailAudit(boolean enableAppealDetailAudit) {
		this.enableAppealDetailAudit = enableAppealDetailAudit;
	}
	//EOf ESPRD00509203_ProviderAppeals_03AUG2010
	
	private String cursorFocusValue = MaintainContactManagementUIConstants.EMPTY_STRING;
	/**
	 * @return Returns the cursorFocusValue.
	 */
	public String getCursorFocusValue() {
		return cursorFocusValue;
	}
	/**
	 * @param cursorFocusValue The cursorFocusValue to set.
	 */
	public void setCursorFocusValue(String cursorFocusValue) {
		this.cursorFocusValue = cursorFocusValue;
	}
	
	private String warnBeforeExitStatus;
	
	
	/**
	 * @return Returns the warnBeforeExitStatus.
	 */
	public String getWarnBeforeExitStatus() {
		return warnBeforeExitStatus;
	}
	/**
	 * @param warnBeforeExitStatus The warnBeforeExitStatus to set.
	 */
	public void setWarnBeforeExitStatus(String warnBeforeExitStatus) {
		this.warnBeforeExitStatus = warnBeforeExitStatus;
	}

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	/**
	 * Holds boolean value of controlRequired
	 */
	 private boolean controlRequired;

	/**
	 * @return the controlRequired
	 */
	public boolean isControlRequired() {
		return controlRequired;
	}

	/**
	 * @param controlRequired the controlRequired to set
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}
	
	/** Holds the first attribute value in DataTable tag for AddAppeals.
	 * */
	private int addAppealsRowIndex;

	/**
	 * @return the addAppealsRowIndex
	 */
	public int getAddAppealsRowIndex() {
		return addAppealsRowIndex;
	}

	/**
	 * @param addAppealsRowIndex the addAppealsRowIndex to set
	 */
	public void setAddAppealsRowIndex(int addAppealsRowIndex) {
		this.addAppealsRowIndex = addAppealsRowIndex;
	}
	/**
	 * It'll render the tick mark in edit admin hearing if notes exists.
	 */
	private boolean notesListIndicator;

	/**
	 * @return the notesListIndicator
	 */
	public boolean isNotesListIndicator() {
		return notesListIndicator;
	}

	/**
	 * @param notesListIndicator
	 *            the notesListIndicator to set
	 */
	public void setNotesListIndicator(boolean notesListIndicator) {
		this.notesListIndicator = notesListIndicator;
	}
	/**Holds the id of Command Link
	 * for right click new window option.
	 * */
	private String rightClickIndicator;

	/**
	 * @return the rightClickIndicator
	 */
	public String getRightClickIndicator() {
		return rightClickIndicator;
	}

	/**
	 * @param rightClickIndicator the rightClickIndicator to set
	 */
	public void setRightClickIndicator(String rightClickIndicator) {
		this.rightClickIndicator = rightClickIndicator;
	}
	/**
	 * This Property Added for Hold the Previous ViewRoot,for Defect
	 * ESPRD00828394
	 * */
	private String preView;

	/**
	 * @return the preView
	 */
	public String getPreView() {
		return preView;
	}

	/**
	 * @param preView the preView to set
	 */
	public void setPreView(String preView) {
		this.preView = preView;
	}
	
	//Added for defect ESPRD00873448 Starts
	/** holds renderEDMSQuickLinks */
	private boolean renderEDMSQuickLinks = true;
	
	/** holds renderCorresGenQuickLinks */
	private boolean renderCorresGenQuickLinks = true;
	
	/** holds renderSAQuickLinks */
	private boolean renderSAQuickLinks = true;
	
	/** holds renderMemberAppealsInquiryQuickLinks */
	private boolean renderMemberAppealsInquiryQuickLinks = true;
	
	/** holds renderProviderAppealsInquiryQuickLinks */
	private boolean renderProviderAppealsInquiryQuickLinks = true;
	
	/** holds renderCaseTrackingQuickLinks */
	private boolean renderCaseTrackingQuickLinks = true;
	
	/** holds renderClaimsInquiryQuickLinks */
	private boolean renderClaimsInquiryQuickLinks = true;
	
	/** holds renderBenefitPlanQuickLinks */
	private boolean renderBenefitPlanQuickLinks = true;
	
	/** holds renderFinancialEntityQuickLinks */
	private boolean renderEventTrackingQuickLinks = true;
	
	/** holds renderFinancialEntityQuickLinks */
	private boolean renderFinancialEntityQuickLinks = true;

	/**
	 * @return the renderEDMSQuickLinks
	 */
	public boolean isRenderEDMSQuickLinks() {
		return renderEDMSQuickLinks;
	}

	/**
	 * @param renderEDMSQuickLinks the renderEDMSQuickLinks to set
	 */
	public void setRenderEDMSQuickLinks(boolean renderEDMSQuickLinks) {
		this.renderEDMSQuickLinks = renderEDMSQuickLinks;
	}

	/**
	 * @return the renderCorresGenQuickLinks
	 */
	public boolean isRenderCorresGenQuickLinks() {
		return renderCorresGenQuickLinks;
	}

	/**
	 * @param renderCorresGenQuickLinks the renderCorresGenQuickLinks to set
	 */
	public void setRenderCorresGenQuickLinks(boolean renderCorresGenQuickLinks) {
		this.renderCorresGenQuickLinks = renderCorresGenQuickLinks;
	}

	/**
	 * @return the renderSAQuickLinks
	 */
	public boolean isRenderSAQuickLinks() {
		return renderSAQuickLinks;
	}

	/**
	 * @param renderSAQuickLinks the renderSAQuickLinks to set
	 */
	public void setRenderSAQuickLinks(boolean renderSAQuickLinks) {
		this.renderSAQuickLinks = renderSAQuickLinks;
	}

	
	/**
	 * @return the renderMemberAppealsInquiryQuickLinks
	 */
	public boolean isRenderMemberAppealsInquiryQuickLinks() {
		return renderMemberAppealsInquiryQuickLinks;
	}

	/**
	 * @param renderMemberAppealsInquiryQuickLinks the renderMemberAppealsInquiryQuickLinks to set
	 */
	public void setRenderMemberAppealsInquiryQuickLinks(
			boolean renderMemberAppealsInquiryQuickLinks) {
		this.renderMemberAppealsInquiryQuickLinks = renderMemberAppealsInquiryQuickLinks;
	}

	/**
	 * @return the renderProviderAppealsInquiryQuickLinks
	 */
	public boolean isRenderProviderAppealsInquiryQuickLinks() {
		return renderProviderAppealsInquiryQuickLinks;
	}

	/**
	 * @param renderProviderAppealsInquiryQuickLinks the renderProviderAppealsInquiryQuickLinks to set
	 */
	public void setRenderProviderAppealsInquiryQuickLinks(
			boolean renderProviderAppealsInquiryQuickLinks) {
		this.renderProviderAppealsInquiryQuickLinks = renderProviderAppealsInquiryQuickLinks;
	}

	/**
	 * @return the renderCaseTrackingQuickLinks
	 */
	public boolean isRenderCaseTrackingQuickLinks() {
		return renderCaseTrackingQuickLinks;
	}

	/**
	 * @param renderCaseTrackingQuickLinks the renderCaseTrackingQuickLinks to set
	 */
	public void setRenderCaseTrackingQuickLinks(boolean renderCaseTrackingQuickLinks) {
		this.renderCaseTrackingQuickLinks = renderCaseTrackingQuickLinks;
	}

	/**
	 * @return the renderClaimsInquiryQuickLinks
	 */
	public boolean isRenderClaimsInquiryQuickLinks() {
		return renderClaimsInquiryQuickLinks;
	}

	/**
	 * @param renderClaimsInquiryQuickLinks the renderClaimsInquiryQuickLinks to set
	 */
	public void setRenderClaimsInquiryQuickLinks(
			boolean renderClaimsInquiryQuickLinks) {
		this.renderClaimsInquiryQuickLinks = renderClaimsInquiryQuickLinks;
	}

	/**
	 * @return the renderBenefitPlanQuickLinks
	 */
	public boolean isRenderBenefitPlanQuickLinks() {
		return renderBenefitPlanQuickLinks;
	}

	/**
	 * @param renderBenefitPlanQuickLinks the renderBenefitPlanQuickLinks to set
	 */
	public void setRenderBenefitPlanQuickLinks(boolean renderBenefitPlanQuickLinks) {
		this.renderBenefitPlanQuickLinks = renderBenefitPlanQuickLinks;
	}

	/**
	 * @return the renderEventTrackingQuickLinks
	 */
	public boolean isRenderEventTrackingQuickLinks() {
		return renderEventTrackingQuickLinks;
	}

	/**
	 * @param renderEventTrackingQuickLinks the renderEventTrackingQuickLinks to set
	 */
	public void setRenderEventTrackingQuickLinks(
			boolean renderEventTrackingQuickLinks) {
		this.renderEventTrackingQuickLinks = renderEventTrackingQuickLinks;
	}

	/**
	 * @return the renderFinancialEntityQuickLinks
	 */
	public boolean isRenderFinancialEntityQuickLinks() {
		return renderFinancialEntityQuickLinks;
	}

	/**
	 * @param renderFinancialEntityQuickLinks the renderFinancialEntityQuickLinks to set
	 */
	public void setRenderFinancialEntityQuickLinks(
			boolean renderFinancialEntityQuickLinks) {
		this.renderFinancialEntityQuickLinks = renderFinancialEntityQuickLinks;
	}
	//Defect ESPRD00873448 Ends
}
