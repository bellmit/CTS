/*
 * Created on Mar 15, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.commonentities.view.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Properties;
//import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.application.exception.SystemParameterNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.delegate.SystemParameterDelegate;
import com.acs.enterprise.common.program.administration.common.domain.SystemParameterDetail;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.view.vo.AddressVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonContactVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesSearchCriteriaVO;
import com.acs.enterprise.common.program.commonentities.view.vo.PhoneVO;
import com.acs.enterprise.common.util.config.ConfigurationLoader;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.program.commonentities.view.vo.EAddressVO;

/**
 * This is a Databean used by all the commonentities. It contains
 * commonEntityVO.
 */
public class CommonEntityDataBean extends EnterpriseBaseDataBean {
	/**
	 * Enterprise Logger for Logging.
	 */

	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CommonEntityDataBean.class);

	/**
	 * Constructor.
	 */
	public CommonEntityDataBean() {
		super();

		try {

			getValidValuesForAddress();
			getSystemParameters();
			// createValidValues();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private String imageRender;

	/**
	 * to make onload cursor focus.
	 */
	private String cursorFocusId;

	/**
	 * @return Returns the cursorFocusId.
	 */
	public String getCursorFocusId() {
		return cursorFocusId;
	}

	/**
	 * @param cursorFocusId
	 *            The cursorFocusId to set.
	 */
	public void setCursorFocusId(String cursorFocusId) {
		this.cursorFocusId = cursorFocusId;
	}

	private boolean disableBeginDate;

	/**
	 * @return Returns the disableBeginDate.
	 */
	public boolean isDisableBeginDate() {
		return disableBeginDate;
	}

	/**
	 * @param disableBeginDate
	 *            The disableBeginDate to set.
	 */
	public void setDisableBeginDate(boolean disableBeginDate) {
		this.disableBeginDate = disableBeginDate;
	}

	/**
	 * @return Returns the imageRender.
	 */
	public String getImageRender() {
		return imageRender;
	}

	/**
	 * @param imageRender
	 *            The to set.
	 */
	public void setImageRender(String imageRender) {
		this.imageRender = imageRender;
	}

	private boolean showSuccessMessage = false;

	private boolean editEaddressflag = false;
	
	private int contactstartindex=0;

	/**
	 * @return the contactstartindex
	 */
	public int getContactstartindex() {
		return contactstartindex;
	}

	/**
	 * @param contactstartindex the contactstartindex to set
	 */
	public void setContactstartindex(int contactstartindex) {
		this.contactstartindex = contactstartindex;
	}

	/**
	 * @return Returns the editEaddressflag.
	 */
	public boolean isEditEaddressflag() {
		return editEaddressflag;
	}

	/**
	 * @param editEaddressflag
	 *            The editEaddressflag to set.
	 */
	public void setEditEaddressflag(boolean editEaddressflag) {
		this.editEaddressflag = editEaddressflag;
	}

	/**
	 * @return Returns the showSuccessMessage.
	 */
	public boolean isShowSuccessMessage() {
		return showSuccessMessage;
	}

	/**
	 * @param showSuccessMessage
	 *            The showSuccessMessage to set.
	 */
	public void setShowSuccessMessage(boolean showSuccessMessage) {
		this.showSuccessMessage = showSuccessMessage;
	}

	/**
	 * Holds audit history.
	 */
	private boolean updateEntityFlag = false;

	/** holds the total number of records per page. */
	private int itemsPerPage = 10;

	/**
	 * @return Returns the itemsPerPage.
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * @param itemsPerPage
	 *            The itemsPerPage to set.
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	private List contactAuditHistList = new ArrayList();

	/**
	 * Holds audit history.
	 */
	private boolean contactHistRndr;

	/**
	 * Holds audit history.
	 */
	private List contactTypeAuditHistList = new ArrayList();

	/**
	 * Holds audit history.
	 */
	private boolean contactTypeHistRndr;

	/** To hold additionaladdressHidden. */
	private String additionalAddressHidden = "plus";

	/** To hold verifyAddressHidden. */
	private String verifyAddressHidden = "plus";

	/** To hold commonContactSaved. */
	private boolean commonContactSaved = false;

	private boolean saveNoteflag = false;

	/** to hold noteList for filtering */
	private ArrayList filterNotesList = null;

	/** to hold noteList */
	private List noteList = new ArrayList();

	/** to hold noteList */
	private List tempNoteList = new ArrayList();

	/**
	 * @return the tempNoteList
	 */
	public List getTempNoteList() {
		return tempNoteList;
	}

	/**
	 * @param tempNoteList
	 *            the tempNoteList to set
	 */
	public void setTempNoteList(List tempNoteList) {
		this.tempNoteList = tempNoteList;
	}

	/**
	 * @return Returns the noteList.
	 */
	public List getNoteList() {
		return noteList;
	}

	/**
	 * @param noteList
	 *            The noteList to set.
	 */
	public void setNoteList(List noteList) {
		this.noteList = noteList;
	}

	/**
	 * @return Returns the filterNotesList.
	 */
	public ArrayList getFilterNotesList() {
		return filterNotesList;
	}

	/**
	 * @param filterNotesList
	 *            The filterNotesList to set.
	 */
	public void setFilterNotesList(ArrayList filterNotesList) {
		this.filterNotesList = filterNotesList;
	}

	/**
	 * @return Returns the addressHidden.
	 */
	public String getAddressHidden() {
		return addressHidden;
	}

	/**
	 * @param addressHidden
	 *            The addressHidden to set.
	 */
	public void setAddressHidden(String addressHidden) {
		this.addressHidden = addressHidden;
	}

	/**
	 * @return Returns the contactHidden.
	 */
	public String getContactHidden() {
		return contactHidden;
	}

	/**
	 * @param contactHidden
	 *            The contactHidden to set.
	 */
	public void setContactHidden(String contactHidden) {
		this.contactHidden = contactHidden;
	}

	/**
	 * @return Returns the eaddressHidden.
	 */
	public String getEaddressHidden() {
		return eaddressHidden;
	}

	/**
	 * @param eaddressHidden
	 *            The eaddressHidden to set.
	 */
	public void setEaddressHidden(String eaddressHidden) {
		this.eaddressHidden = eaddressHidden;
	}

	/**
	 * @return Returns the phRecordHidden.
	 */
	public String getPhRecordHidden() {
		return phRecordHidden;
	}

	/**
	 * @param phRecordHidden
	 *            The phRecordHidden to set.
	 */
	public void setPhRecordHidden(String phRecordHidden) {
		this.phRecordHidden = phRecordHidden;
	}

	/** To hold contactHidden. */
	private String contactHidden = "plus";

	/** To hold addressHidden. */
	private String addressHidden = "plus";

	/** To hold phRecordHidden. */
	private String phRecordHidden = "plus";

	/** To hold eAddressHidden. */
	private String eaddressHidden = "plus";

	/**
	 * To hold boolean value for the Filter link whether it should be visible or
	 * not
	 */
	private boolean filterEnabled = true;

	/**
	 * @return Returns the filterEnabled.
	 */
	public boolean isFilterEnabled() {
		return filterEnabled;
	}

	/**
	 * @param filterEnabled
	 *            The filterEnabled to set.
	 */
	public void setFilterEnabled(boolean filterEnabled) {
		this.filterEnabled = filterEnabled;
	}

	/**
	 * @return Returns the additionalAddressHidden.
	 */
	public String getAdditionalAddressHidden() {
		return additionalAddressHidden;
	}

	/**
	 * @param additionalAddressHidden
	 *            The additionalAddressHidden to set.
	 */
	public void setAdditionalAddressHidden(String additionalAddressHidden) {
		this.additionalAddressHidden = additionalAddressHidden;
	}

	/**
	 * @return Returns the phoneAuditHistList.
	 */
	public List getPhoneAuditHistList() {
		return phoneAuditHistList;
	}

	/**
	 * @param phoneAuditHistList
	 *            The phoneAuditHistList to set.
	 */
	public void setPhoneAuditHistList(List phoneAuditHistList) {
		this.phoneAuditHistList = phoneAuditHistList;
	}

	/**
	 * @return Returns the phoneHistRndr.
	 */
	public boolean isPhoneHistRndr() {
		return phoneHistRndr;
	}

	/**
	 * @param phoneHistRndr
	 *            The phoneHistRndr to set.
	 */
	public void setPhoneHistRndr(boolean phoneHistRndr) {
		this.phoneHistRndr = phoneHistRndr;
	}

	/**
	 * Holds audit history.
	 */
	private List phoneAuditHistList = new ArrayList();

	/**
	 * Holds audit history.
	 */
	private boolean phoneHistRndr;

	/**
	 * Holds audit history.
	 */
	private List addressAuditHistList = new ArrayList();

	/**
	 * Holds audit history.
	 */
	private boolean addressHistRndr;

	/**
	 * Holds noteSetSK.
	 */
	private Long noteSetSK;

	/**
	 * Holds audit history.
	 */
	private List auditEAddressAuditHistList;

	/**
	 * Holds audit history.
	 */
	private boolean auditEAddressHistRndr;

	/**
	 * This variable holds voidIndicator for Rendering.
	 */
	private boolean voidIndicatorForRender;

	/**
	 * holds void ind.
	 */
	private boolean voidInd;

	/**
	 * holds status.
	 */
	private boolean status;

	/** To hold phRecordHidden. */
	private String addressRecordHidden = "plus";

	/**
	 * This variable used to disable the phoneRecord fields.
	 */
	private boolean disablePhoneRecord;

	/**
	 * This variable used to disable the eaddress fields.
	 */
	private boolean disableEAddress;

	/**
	 * This variable holds Phone voidIndicator for Rendering.
	 */
	private boolean phoneVoidIndicatorRender;

	/**
	 * This variable holds add Phone voidIndicator for Rendering.
	 */
	private boolean addphoneVoidIndicatorRender;

	/**
	 * This variable holds add eaddress voidIndicator for Rendering.
	 */
	private boolean addeaddressVoidIndicatorRender;

	/**
	 * This variable holds add contact voidIndicator for Rendering.
	 */
	private boolean addcontactVoidIndicatorRender;

	/**
	 * Holds AddressType List.
	 */
	private List addressTypeList = new ArrayList();

	/**
	 * Holds changeReason List.
	 */
	private List changeReasonList = new ArrayList();

	/**
	 * Holds Significance List.
	 */
	private List significanceList = new ArrayList();

	/**
	 * Holds addressStatusList.
	 */
	private List addressStatusList = new ArrayList();

	/**
	 * HoldscityList.
	 */
	/*private List cityList = new ArrayList();*/

	/**
	 * Holds countryList.
	 */
	private List countryList = new ArrayList();

	/**
	 * Holds countyList.
	 */
	private List countyList = new ArrayList();

	/**
	 * Holds countyMap.
	 */
	//hash map performance issue code change
	//private Map countyMap = new HashMap();

	/**
	 * Holds lobList.
	 */
	private List lobList = new ArrayList();

	/**
	 * Holds stateList.
	 */
	private List stateList = new ArrayList();

	/**
	 * Holds default address state
	 */
	private String addressDefaultState;

	/**
	 * Holds eaddressRendered.
	 */
	private boolean eaddressRendered = false;

	/**
	 * Holds eaddressSaved.
	 */
	private boolean eaddressSaved = false;

	/**
	 * Holds commonEAddressSaved.
	 */

	private boolean commonEAddressSaved = false;

	/**
	 * /** Holds commonAddressSaved.
	 */

	private boolean commonAddressSaved = false;

	/**
	 * Holds viewingSaved.
	 */
	private boolean viewingSaved = false;

	/**
	 * Holds savedEndDateGreater.
	 */
	private boolean savedEndDateGreater = false;

	/**
	 * Holds Edit Eaddress Rendered State.
	 */
	private boolean editEAddress = false;

	/**
	 * Holds Edit Phone Rendered State.
	 */
	private boolean editPhoneRendered = false;

	/**
	 * Holds phoneRendered.
	 */

	private boolean phoneRendered = false;

	/**
	 * Holds phoneSaved.
	 */

	private boolean phoneSaved = false;

	/**
	 * Holds eaddressVOIndex.
	 */

	private String eaddressVOIndex = null;

	/**
	 * Holds PhoneVOIndex.
	 */
	private String phoneVOIndex = null;

	/**
	 * This value will be set to true when add Note button is clicked.
	 */
	private boolean saveNotesChk = false;

	/**
	 * This value will be set to true when add Contact button is clicked.
	 */
	private boolean saveContactChk = false;

	/**
	 * This value will be set to true when add Contact Type button is clicked.
	 */
	private boolean saveContactTypeChk = false;

	/**
	 * Holds CountryCodeStatus.
	 */
	private boolean countryCodeStatus = false;

	/**
	 * Holds mainNotesRender.
	 */

	private boolean mainNotesRender = false; // need to set this as false,

	/**
	 * Holds globalNotesRender.
	 */

	private boolean globalNotesRender = false; // need to set this as false,

	/**
	 * Holds globalDataRender.
	 */

	private boolean globalDataRender = false; // need to set this as false,

	// corresponding

	// module ppl would set this to true

	/**
	 * Holds newNotesRender.
	 */
	private boolean newNotesRender = true;

	/**
	 * Holds filterNotesRender.
	 */
	private boolean filterNotesRender = false;

	/**
	 * Holds mainContactRender.
	 */
	private boolean mainContactRender = false;

	/**
	 * Holds newContactRender.
	 */
	private boolean newContactRender = false;

	/**
	 * Holds newContactTypeRender.
	 */
	private boolean newContactTypeRender = false;

	/**
	 * Holds contactTypeSaveMsg boolean value.
	 */
	private boolean contactTypeSaveMsg = false;

	/**
	 * Holds contactSaveMsg boolean value.
	 */
	private boolean contactSaveMsg = false;

	/**
	 * Holds notesSaveMsg boolean value.
	 */
	private boolean notesSaveMsg = false;

	/**
	 * Holds phoneSaveMsg boolean value.
	 */
	private boolean phoneSaveMsg = false;

	/**
	 * Holds addressSaveMsg boolean value.
	 */
	private boolean addressSaveMsg = false;

	/**
	 * Holds eaddrSaveMsg boolean value.
	 */

	private boolean eaddrSaveMsg = false;

	/**
	 * Holds selectedConatctTypIndex.
	 */

	private int selectedConatctTypIndex = 0;

	/**
	 * Holds selectedConatctIndex.
	 */
	private int selectedConatctIndex = 0;

	/**
	 * Holds disableContactType.
	 */

	private boolean disableContactType = false;

	/**
	 * Holds isContactEdit.
	 */

	private boolean isContactEdit = false;

	/**
	 * Holds isContactTypeEdit.
	 */

	private boolean isContactTypeEdit = false;

	/**
	 * Holds noteTypeList.
	 */

	/**
	 * Holds addressHistoryRendered.
	 */
	private boolean addressHistorydisp = false;

	/**
	 * Holds addressRendered.
	 */
	private String addressType;

	/**
	 * Holds addressRendered.
	 */
	private boolean addressSaved = false;

	/**
	 * Holds addressRendered.
	 */
	private boolean addressRendered = false;

	/**
	 * Holds ShowHistoryDetails.
	 */
	private boolean showHistoryDetails = false;

	/**
	 * Holds addressSummaryRendered.
	 */
	private boolean addressSummaryRendered = false;

	/**
	 * Holds addressHistRendered.
	 */

	private boolean addressHistRendered = false;

	/**
	 * Holds isAddressEdit.
	 */

	private boolean isAddressEdit = false;

	/* Small save and Big Save */

	private boolean commonAddressFalg = false;

	private boolean commonContactFlag = false;

	private boolean commonPhoneFalg = false;

	private boolean commonEAddressFlag = false;

	/* End Small save and Big Save */

	private List addressAltList;

	private boolean showAddressAltList;

	private boolean suggestionsAvailable;

	/**
	 * Declared to hold the selected AuditLog.
	 */
	private AuditLog selectedAuditLog;

	/**
	 * Declared to hold the selected AuditLog.
	 */
	private AuditLog selectedAdrAuditLog;

	/**
	 * Declared to hold the selected AuditLog.
	 */
	private AuditLog selectedEAdrAuditLog;

	/**
	 * Declared to hold the selected AuditLog.
	 */
	private AuditLog selectedCntAuditLog;

	/**
	 * Declared to hold the selected AuditLog.
	 */
	private AuditLog selectedPhnAuditLog;

	/**
	 * Declated to hold the selected parent AuditLog.
	 */
	private AuditLog selectedParentAuditLog;

	/**
	 * This is used to show column changes for parent page.
	 */
	private boolean auditParentColumnHistoryRender = false;

	/**
	 * noteSet
	 */
	private NoteSet noteSet = null;

	/**
	 * @return Returns the auditParentColumnHistoryRender.
	 */
	public boolean isAuditParentColumnHistoryRender() {
		return auditParentColumnHistoryRender;
	}

	/**
	 * @param auditParentColumnHistoryRender
	 *            The auditParentColumnHistoryRender to set.
	 */
	public void setAuditParentColumnHistoryRender(
			boolean auditParentColumnHistoryRender) {
		this.auditParentColumnHistoryRender = auditParentColumnHistoryRender;
	}

	/**
	 * @return Returns the selectedAuditLog.
	 */
	public AuditLog getSelectedAuditLog() {
		return selectedAuditLog;
	}

	/**
	 * @param selectedAuditLog
	 *            The selectedAuditLog to set.
	 */
	public void setSelectedAuditLog(AuditLog selectedAuditLog) {
		this.selectedAuditLog = selectedAuditLog;
	}

	/**
	 * @return Returns the isAddressHistoryRendered.
	 */
	public boolean isAddressHistoryRendered() {
		return isAddressHistoryRendered;
	}

	/**
	 * @param isAddressHistoryRendered
	 *            The isAddressHistoryRendered to set.
	 */
	public void setAddressHistoryRendered(boolean isAddressHistoryRendered) {
		this.isAddressHistoryRendered = isAddressHistoryRendered;
	}

	/**
	 * Holds the addressHistoryRendered.
	 */
	private boolean isAddressHistoryRendered = false;

	/**
	 * This value will be set to True when Add Address Command Link is Clicked.
	 */
	private boolean saveAddressChk = false;

	/**
	 * This Variable holds Index information for the Address Funcationality.
	 */

	private String addressVOIndex = null;

	/**
	 * Holds notesCount.
	 */

	private int notesCount = 0;

	/**
	 * Holds boolean value for commonNoteNotSaved .
	 */

	private boolean commonNoteSaved = false;

	/**
	 * Holds boolean value for showSuccessMessage
	 */

	private boolean ShowSuccesMessage = false;

	/**
	 * This is used to show column changes for details page.
	 */
	private boolean auditColumnHistoryRender = false;

	/**
	 * @return Returns the addressHistorydisp.
	 */
	public boolean isAddressHistorydisp() {
		return addressHistorydisp;
	}

	/**
	 * @param addressHistorydisp
	 *            The addressHistorydisp to set.
	 */
	public void setAddressHistorydisp(boolean addressHistorydisp) {
		this.addressHistorydisp = addressHistorydisp;
	}

	/**
	 * @return Returns the addressRendered.
	 */
	public boolean isAddressRendered() {
		return addressRendered;
	}

	/**
	 * @param addressRendered
	 *            The addressRendered to set.
	 */
	public void setAddressRendered(boolean addressRendered) {
		this.addressRendered = addressRendered;
	}

	/**
	 * @return Returns the addressSaved.
	 */
	public boolean isAddressSaved() {
		return addressSaved;
	}

	/**
	 * @param addressSaved
	 *            The addressSaved to set.
	 */
	public void setAddressSaved(boolean addressSaved) {
		this.addressSaved = addressSaved;
	}

	/**
	 * @return Returns the addressSummaryRendered.
	 */
	public boolean isAddressSummaryRendered() {
		return addressSummaryRendered;
	}

	/**
	 * @param addressSummaryRendered
	 *            The addressSummaryRendered to set.
	 */
	public void setAddressSummaryRendered(boolean addressSummaryRendered) {
		this.addressSummaryRendered = addressSummaryRendered;
	}

	/**
	 * @return Returns the addressType.
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType
	 *            The addressType to set.
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * @return Returns the addressVOIndex.
	 */
	public String getAddressVOIndex() {
		return addressVOIndex;
	}

	/**
	 * @param addressVOIndex
	 *            The addressVOIndex to set.
	 */
	public void setAddressVOIndex(String addressVOIndex) {
		this.addressVOIndex = addressVOIndex;
	}

	/**
	 * @return Returns the isAddressEdit.
	 */
	public boolean isAddressEdit() {
		return isAddressEdit;
	}

	/**
	 * @param isAddressEdit
	 *            The isAddressEdit to set.
	 */
	public void setAddressEdit(boolean isAddressEdit) {
		this.isAddressEdit = isAddressEdit;
	}

	/**
	 * @return Returns the saveAddressChk.
	 */
	public boolean isSaveAddressChk() {
		return saveAddressChk;
	}

	/**
	 * @param saveAddressChk
	 *            The saveAddressChk to set.
	 */
	public void setSaveAddressChk(boolean saveAddressChk) {
		this.saveAddressChk = saveAddressChk;
	}

	/**
	 * @param addressAltList
	 *            The addressAltList to set.
	 */
	public void setAddressAltList(List addressAltList) {
		this.addressAltList = addressAltList;
	}

	/**
	 * @return Returns the addressAltList.
	 */
	public List getAddressAltList() {
		return addressAltList;
	}

	/**
	 * @param showAddressAltList
	 *            The showAddressAltList to set.
	 */
	public void setShowAddressAltList(boolean showAddressAltList) {
		this.showAddressAltList = showAddressAltList;
	}

	/**
	 * @return Returns the showAddressAltList.
	 */
	public boolean isShowAddressAltList() {
		return showAddressAltList;
	}

	/**
	 * @param suggestionsAvailable
	 *            The suggestionsAvailable to set.
	 */
	public void setSuggestionsAvailable(boolean suggestionsAvailable) {
		this.suggestionsAvailable = suggestionsAvailable;
	}

	/**
	 * @return Returns the suggestionsAvailable.
	 */
	public boolean isSuggestionsAvailable() {
		return suggestionsAvailable;
	}

	/**
	 * @return Returns the showHistoryDetails.
	 */
	public boolean isShowHistoryDetails() {
		return showHistoryDetails;
	}

	/**
	 * @param showHistoryDetails
	 *            The showHistoryDetails to set.
	 */
	public void setShowHistoryDetails(boolean showHistoryDetails) {
		this.showHistoryDetails = showHistoryDetails;
	}

	/**
	 * holds noteType.
	 */
	private List noteTypeList = new ArrayList();

	/**
	 * Holds genderList.
	 */

	private List genderList = new ArrayList();

	/**
	 * Holds entityTypeList.
	 */

	private List entityTypeList = new ArrayList();

	/**
	 * Holds contactTypeList.
	 */

	private List contactTypeList = new ArrayList();

	/**
	 * Holds contactSigList.
	 */

	private List contactSigList = new ArrayList();

	/**
	 * Holds namePrefixList.
	 */

	private List namePrefixList = new ArrayList();

	/**
	 * Holds nameSuffixList.
	 */

	private List nameSuffixList = new ArrayList();

	/**
	 * Holds statusList.
	 */

	private List statusList = new ArrayList();

	/**
	 * Holds currentPageId
	 */
	private String currentPageId;

	/**
	 * @return the currentPageId
	 */
	public String getCurrentPageId() {
		return currentPageId;
	}

	/**
	 * @param currentPageId
	 *            the currentPageId to set
	 */
	public void setCurrentPageId(String currentPageId) {
		this.currentPageId = currentPageId;
	}

	/**
	 * @return Returns the statusList.
	 */
	public List getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList
	 *            The statusList to set.
	 */
	public void setStatusList(List statusList) {
		this.statusList = statusList;
	}

	/**
	 * Holds BEAN_NAME.
	 */

	public static final String BEAN_NAME = "commonEntityDataBean";

	//private CommonNotesVO commonNotesVO;

	/**
	 * This VO contains ArrayList of all the common entities VO's(Phone,
	 * Address, Eaddress and Notes).
	 */
	private CommonEntityVO commonEntityVO = new CommonEntityVO();

	/**
	 * This VO hold Common Notes information.
	 */
	//private NoteSetVO noteSetVO;
	/**
	 * This VO hold CommonEntity Search information.
	 */
	private CommonNotesSearchCriteriaVO commonNoteSearchVO;

	/**
	 * @return Returns the commonEntityVO.
	 */
	public CommonEntityVO getCommonEntityVO() {
		return commonEntityVO;
	}

	/**
	 * @param commonEntityVO
	 *            The commonEntityVO to set.
	 */
	public void setCommonEntityVO(CommonEntityVO commonEntityVO) {
		this.commonEntityVO = commonEntityVO;
	}

	/**
	 * @return Returns the filterNotesRender.
	 */
	public boolean isFilterNotesRender() {
		return filterNotesRender;
	}

	/**
	 * @param filterNotesRender
	 *            The filterNotesRender to set.
	 */
	public void setFilterNotesRender(boolean filterNotesRender) {
		this.filterNotesRender = filterNotesRender;
	}

	/**
	 * @return Returns the mainNotesRender.
	 */
	public boolean isMainNotesRender() {
		return mainNotesRender;
	}

	/**
	 * @param mainNotesRender
	 *            The mainNotesRender to set.
	 */
	public void setMainNotesRender(boolean mainNotesRender) {
		this.mainNotesRender = mainNotesRender;
	}

	/**
	 * @return Returns the newNotesRender.
	 */
	public boolean isNewNotesRender() {
		return newNotesRender;
	}

	/**
	 * @param newNotesRender
	 *            The newNotesRender to set.
	 */
	public void setNewNotesRender(boolean newNotesRender) {
		this.newNotesRender = newNotesRender;
	}

	/**
	 * @return Returns the commonNoteSearchVO.
	 */
	public CommonNotesSearchCriteriaVO getCommonNoteSearchVO() {
		return commonNoteSearchVO;
	}

	/**
	 * @param commonNoteSearchVO
	 *            The commonNoteSearchVO to set.
	 */
	public void setCommonNoteSearchVO(
			CommonNotesSearchCriteriaVO commonNoteSearchVO) {
		this.commonNoteSearchVO = commonNoteSearchVO;
	}

	/**
	 * @return Returns the mainContactRender.
	 */
	public boolean isMainContactRender() {
		return mainContactRender;
	}

	/**
	 * @param mainContactRender
	 *            The mainContactRender to set.
	 */
	public void setMainContactRender(boolean mainContactRender) {
		this.mainContactRender = mainContactRender;
	}

	/**
	 * @return Returns the newContactRender.
	 */
	public boolean isNewContactRender() {
		return newContactRender;
	}

	/**
	 * @param newContactRender
	 *            The newContactRender to set.
	 */
	public void setNewContactRender(boolean newContactRender) {
		this.newContactRender = newContactRender;
	}

	/**
	 * @return Returns the newContactTypeRender.
	 */
	public boolean isNewContactTypeRender() {
		return newContactTypeRender;
	}

	/**
	 * @param newContactTypeRender
	 *            The newContactTypeRender to set.
	 */
	public void setNewContactTypeRender(boolean newContactTypeRender) {
		this.newContactTypeRender = newContactTypeRender;
	}

	/**
	 * @return Returns the eaddressRendered.
	 */
	public boolean isEaddressRendered() {
		return eaddressRendered;
	}

	/**
	 * @param eaddressRendered
	 *            The eaddressRendered to set.
	 */
	public void setEaddressRendered(boolean eaddressRendered) {
		this.eaddressRendered = eaddressRendered;
	}

	/**
	 * @return Returns the eaddressSaved.
	 */
	public boolean isEaddressSaved() {
		return eaddressSaved;
	}

	/**
	 * @param eaddressSaved
	 *            The eaddressSaved to set.
	 */
	public void setEaddressSaved(boolean eaddressSaved) {
		this.eaddressSaved = eaddressSaved;
	}

	/**
	 * @return Returns the phoneRendered.
	 */
	public boolean isPhoneRendered() {
		return phoneRendered;
	}

	/**
	 * @param phoneRendered
	 *            The phoneRendered to set.
	 */
	public void setPhoneRendered(boolean phoneRendered) {
		this.phoneRendered = phoneRendered;
	}

	/**
	 * @return Returns the phoneSaved.
	 */
	public boolean isPhoneSaved() {
		return phoneSaved;
	}

	/**
	 * @param phoneSaved
	 *            The phoneSaved to set.
	 */
	public void setPhoneSaved(boolean phoneSaved) {
		this.phoneSaved = phoneSaved;
	}

	/**
	 * @return Returns the viewingSaved.
	 */
	public boolean isViewingSaved() {
		return viewingSaved;
	}

	/**
	 * @param viewingSaved
	 *            The viewingSaved to set.
	 */
	public void setViewingSaved(boolean viewingSaved) {
		this.viewingSaved = viewingSaved;
	}

	/**
	 * @return Returns the savedEndDateGreater.
	 */
	public boolean isSavedEndDateGreater() {
		return savedEndDateGreater;
	}

	/**
	 * @param savedEndDateGreater
	 *            The savedEndDateGreater to set.
	 */
	public void setSavedEndDateGreater(boolean savedEndDateGreater) {
		this.savedEndDateGreater = savedEndDateGreater;
	}

	/**
	 * @return Returns the eaddressVOIndex.
	 */
	public String getEaddressVOIndex() {
		return eaddressVOIndex;
	}

	/**
	 * @param eaddressVOIndex
	 *            The eaddressVOIndex to set.
	 */
	public void setEaddressVOIndex(String eaddressVOIndex) {
		this.eaddressVOIndex = eaddressVOIndex;
	}

	/**
	 * @return Returns the selectedConatctTypIndex.
	 */
	public int getSelectedConatctTypIndex() {
		return selectedConatctTypIndex;
	}

	/**
	 * @param selectedConatctTypIndex
	 *            The selectedConatctTypIndex to set.
	 */
	public void setSelectedConatctTypIndex(int selectedConatctTypIndex) {
		this.selectedConatctTypIndex = selectedConatctTypIndex;
	}

	/**
	 * @return Returns the selectedConatctIndex.
	 */
	public int getSelectedConatctIndex() {
		return selectedConatctIndex;
	}

	/**
	 * @param selectedConatctIndex
	 *            The selectedConatctIndex to set.
	 */
	public void setSelectedConatctIndex(int selectedConatctIndex) {
		this.selectedConatctIndex = selectedConatctIndex;
	}

	/**
	 * @return Returns the isContactEdit.
	 */
	public boolean isContactEdit() {
		return isContactEdit;
	}

	/**
	 * @param isContactEdit
	 *            The isContactEdit to set.
	 */
	public void setContactEdit(boolean isContactEdit) {
		this.isContactEdit = isContactEdit;
	}

	/**
	 * @return Returns the isContactTypeEdit.
	 */
	public boolean isContactTypeEdit() {
		return isContactTypeEdit;
	}

	/**
	 * @param isContactTypeEdit
	 *            The isContactTypeEdit to set.
	 */
	public void setContactTypeEdit(boolean isContactTypeEdit) {
		this.isContactTypeEdit = isContactTypeEdit;
	}

	/**
	 * @return Returns the noteTypeList.
	 */
	public List getNoteTypeList() {
		return noteTypeList;
	}

	/**
	 * @param noteTypeList
	 *            The noteTypeList to set.
	 */
	public void setNoteTypeList(List noteTypeList) {
		this.noteTypeList = noteTypeList;
	}

	/**
	 * @return Returns the contactSigList.
	 */
	public List getContactSigList() {
		return contactSigList;
	}

	/**
	 * @param contactSigList
	 *            The contactSigList to set.
	 */
	public void setContactSigList(List contactSigList) {
		this.contactSigList = contactSigList;
	}

	/**
	 * @return Returns the contactTypeList.
	 */
	public List getContactTypeList() {
		return contactTypeList;
	}

	/**
	 * @param contactTypeList
	 *            The contactTypeList to set.
	 */
	public void setContactTypeList(List contactTypeList) {
		this.contactTypeList = contactTypeList;
	}

	/**
	 * @return Returns the entityTypeList.
	 */
	public List getEntityTypeList() {
		return entityTypeList;
	}

	/**
	 * @param entityTypeList
	 *            The entityTypeList to set.
	 */
	public void setEntityTypeList(List entityTypeList) {
		this.entityTypeList = entityTypeList;
	}

	/**
	 * @return Returns the genderList.
	 */
	public List getGenderList() {
		return genderList;
	}

	/**
	 * @param genderList
	 *            The genderList to set.
	 */
	public void setGenderList(List genderList) {
		this.genderList = genderList;
	}

	/**
	 * @return Returns the namePrefixList.
	 */
	public List getNamePrefixList() {
		return namePrefixList;
	}

	/**
	 * @param namePrefixList
	 *            The namePrefixList to set.
	 */
	public void setNamePrefixList(List namePrefixList) {
		this.namePrefixList = namePrefixList;
	}

	/**
	 * @return Returns the disableContactType.
	 */
	public boolean isDisableContactType() {
		return disableContactType;
	}

	/**
	 * @param disableContactType
	 *            The disableContactType to set.
	 */
	public void setDisableContactType(boolean disableContactType) {
		this.disableContactType = disableContactType;
	}

	/**
	 * @return Returns the saveContactChk.
	 */
	public boolean isSaveContactChk() {
		return saveContactChk;
	}

	/**
	 * @param saveContactChk
	 *            The saveContactChk to set.
	 */
	public void setSaveContactChk(boolean saveContactChk) {
		this.saveContactChk = saveContactChk;
	}

	/**
	 * @return Returns the saveContactTypeChk.
	 */
	public boolean isSaveContactTypeChk() {
		return saveContactTypeChk;
	}

	/**
	 * @param saveContactTypeChk
	 *            The saveContactTypeChk to set.
	 */
	public void setSaveContactTypeChk(boolean saveContactTypeChk) {
		this.saveContactTypeChk = saveContactTypeChk;
	}

	/**
	 * @return Returns the saveNotesChk.
	 */
	public boolean isSaveNotesChk() {
		return saveNotesChk;
	}

	/**
	 * @param saveNotesChk
	 *            The saveNotesChk to set.
	 */
	public void setSaveNotesChk(boolean saveNotesChk) {
		this.saveNotesChk = saveNotesChk;
	}

	/**
	 * @return Returns the contactSaveMsg.
	 */
	public boolean isContactSaveMsg() {
		return contactSaveMsg;
	}

	/**
	 * @param contactSaveMsg
	 *            The contactSaveMsg to set.
	 */
	public void setContactSaveMsg(boolean contactSaveMsg) {
		this.contactSaveMsg = contactSaveMsg;
	}

	/**
	 * @return Returns the notesSaveMsg.
	 */
	public boolean isNotesSaveMsg() {
		return notesSaveMsg;
	}

	/**
	 * @param notesSaveMsg
	 *            The notesSaveMsg to set.
	 */
	public void setNotesSaveMsg(boolean notesSaveMsg) {
		this.notesSaveMsg = notesSaveMsg;
	}

	/**
	 * @return Returns the contactTypeSaveMsg.
	 */
	public boolean isContactTypeSaveMsg() {
		return contactTypeSaveMsg;
	}

	/**
	 * @param contactTypeSaveMsg
	 *            The contactTypeSaveMsg to set.
	 */
	public void setContactTypeSaveMsg(boolean contactTypeSaveMsg) {
		this.contactTypeSaveMsg = contactTypeSaveMsg;
	}

	/**
	 * @return Returns the phoneVOIndex.
	 */
	public String getPhoneVOIndex() {
		return phoneVOIndex;
	}

	/**
	 * @param phoneVOIndex
	 *            The phoneVOIndex to set.
	 */
	public void setPhoneVOIndex(String phoneVOIndex) {
		this.phoneVOIndex = phoneVOIndex;
	}

	/**
	 * @return Returns the editEAddress.
	 */
	public boolean isEditEAddress() {
		return editEAddress;
	}

	/**
	 * @param editEAddress
	 *            The editEAddress to set.
	 */
	public void setEditEAddress(boolean editEAddress) {
		this.editEAddress = editEAddress;
	}

	/**
	 * @return Returns the editPhoneRendered.
	 */
	public boolean isEditPhoneRendered() {
		return editPhoneRendered;
	}

	/**
	 * @param editPhoneRendered
	 *            The editPhoneRendered to set.
	 */
	public void setEditPhoneRendered(boolean editPhoneRendered) {
		this.editPhoneRendered = editPhoneRendered;
	}

	/**
	 * @return Returns the countryCodeStatus.
	 */
	public boolean isCountryCodeStatus() {
		return countryCodeStatus;
	}

	/**
	 * @param countryCodeStatus
	 *            The countryCodeStatus to set.
	 */
	public void setCountryCodeStatus(boolean countryCodeStatus) {
		this.countryCodeStatus = countryCodeStatus;
	}

	/**
	 * @return Returns the phoneSaveMsg.
	 */
	public boolean isPhoneSaveMsg() {
		return phoneSaveMsg;
	}

	/**
	 * @param phoneSaveMsg
	 *            The phoneSaveMsg to set.
	 */
	public void setPhoneSaveMsg(boolean phoneSaveMsg) {
		this.phoneSaveMsg = phoneSaveMsg;
	}

	/**
	 * @return Returns the eaddrSaveMsg.
	 */
	public boolean isEaddrSaveMsg() {
		return eaddrSaveMsg;
	}

	/**
	 * @param eaddrSaveMsg
	 *            The eaddrSaveMsg to set.
	 */
	public void setEaddrSaveMsg(boolean eaddrSaveMsg) {
		this.eaddrSaveMsg = eaddrSaveMsg;
	}

	/**
	 * Valid Values for Drop Downs.
	 */

	private void getValidValuesForAddress() {
		logger.debug("entered into get valid values for address");
		InputCriteria inputCriteria = null;
		List list = new ArrayList();
		//FindBug Issue Resolved
		//HashMap map = new HashMap();
		HashMap map = null;
		String select = "Please Select One";
		String selectIndex = "";

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		//FindBug Issue Resolved
		//ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
		ReferenceDataListVO referenceDataListVO = null;

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_US_STATE_CD);
		list = new ArrayList();
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria.setElementName(ReferenceServiceDataConstants.G_CNTRY_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria.setElementName(ReferenceServiceDataConstants.G_COUNTY_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_ADR_USG_STAT_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.REFERENCE);
		inputCriteria.setElementName(ReferenceServiceDataConstants.R_LOB_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_ADR_USG_TY_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.A_HDR_STAT_RSN_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_E_ADR_TY_SIG_CD);
		list.add(inputCriteria);

		inputCriteria = new InputCriteria();
		inputCriteria.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteria
				.setElementName(ReferenceServiceDataConstants.G_ADR_CHG_RSN_CD);
		list.add(inputCriteria);
		referenceDataSearch.setInputList(list);
		//FindBug Issue Resolved
		//referenceServiceDelegate = new ReferenceServiceDelegate();

		//		Pass the list to the delegate
		//FindBug Issue Resolved
		//referenceDataListVO = new ReferenceDataListVO();
		String codesAndDesc = "";

		//  referenceDataSearch.setInputList(list);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			map = referenceDataListVO.getResponseMap();

			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ ReferenceServiceDataConstants.G_US_STATE_CD);
			int stateSize = list.size();
			getStateList().add(new SelectItem(selectIndex, select));

			for (int i = 0; i < stateSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				getStateList()
						.add(
								new SelectItem(refVo.getValidValueCode(),
										codesAndDesc));

			}

			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ ReferenceServiceDataConstants.G_CNTRY_CD);
			int cntrySize = list.size();
			getCountryList().add(new SelectItem(selectIndex, select));
			for (int i = 0; i < cntrySize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				getCountryList()
						.add(
								new SelectItem(refVo.getValidValueCode(),
										codesAndDesc));

			}

			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ ReferenceServiceDataConstants.G_COUNTY_CD);
			int countySize = list.size();
			getCountyList().add(new SelectItem(selectIndex, select));
			for (int i = 0; i < countySize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				getCountyList()
						.add(
								new SelectItem(refVo.getValidValueCode(),
										codesAndDesc));
				/*hash map performance issue code change
				 * getCountyMap().put(refVo.getValidValueCode(),
						refVo.getShortDescription());*/
			}
			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ ReferenceServiceDataConstants.G_ADR_USG_STAT_CD);
			int adrSize = list.size();
			getAddressStatusList().add(new SelectItem(selectIndex, select));
			for (int i = 0; i < adrSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				getAddressStatusList()
						.add(
								new SelectItem(refVo.getValidValueCode(),
										codesAndDesc));
			}

			list = (List) map.get(FunctionalAreaConstants.REFERENCE + "#"
					+ ReferenceServiceDataConstants.R_LOB_CD);
			int lobSize = list.size();
			getLobList().add(new SelectItem(selectIndex, select));
			for (int i = 0; i < lobSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				getLobList()
						.add(
								new SelectItem(refVo.getValidValueCode(),
										codesAndDesc));
			}

			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ ReferenceServiceDataConstants.G_ADR_USG_TY_CD);
			int usgSize = list.size();
			getAddressTypeList().add(new SelectItem(selectIndex, select));
			for (int i = 0; i < usgSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				getAddressTypeList()
						.add(
								new SelectItem(refVo.getValidValueCode(),
										codesAndDesc));
			}
			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ ReferenceServiceDataConstants.G_ADR_CHG_RSN_CD);
			int typeSize = list.size();
			getChangeReasonList().add(new SelectItem(selectIndex, select));
			for (int i = 0; i < typeSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				getChangeReasonList()
						.add(
								new SelectItem(refVo.getValidValueCode(),
										codesAndDesc));
			}

			list = (List) map.get(FunctionalAreaConstants.GENERAL + "#"
					+ ReferenceServiceDataConstants.G_E_ADR_TY_SIG_CD);
			int sigSize = list.size();
			getSignificanceList().add(new SelectItem(selectIndex, select));
			for (int i = 0; i < sigSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) list.get(i);
				codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				getSignificanceList()
						.add(
								new SelectItem(refVo.getValidValueCode(),
										codesAndDesc));
			}

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * Fetch the following System Parameters related to address validation:
	 * <ul>
	 * <li>G1-25 - Client State
	 * </ul>
	 */
	private void getSystemParameters() {
		ConfigurationLoader configLoader = ConfigurationLoader.getInstance();
		Properties props = configLoader.getFunctionalAreaCodeProperties();

		String functionalArea = props
				.getProperty(FunctionalAreaConstants.GENERAL);
		String lobCode = props.getProperty(FunctionalAreaConstants.LOBCODE);

		int index = functionalArea.indexOf("#");
		if (index != -1) {
			functionalArea = functionalArea.substring(0, index);
		}

		SystemParameterDelegate delegate = new SystemParameterDelegate();
		SystemParameterDetail detail = null;

		try {
			logger
					.debug("<AddressControllerBean> getSystemParameters : Fetching SystemParameter "
							+ functionalArea + ":25 - " + lobCode);
			detail = delegate.getSpecificSystemParameter(Long.valueOf(25),
					functionalArea, lobCode, new Date());

			addressDefaultState = detail.getValueData();
		} catch (SystemParameterNotFoundException e) {
			logger
					.error(
							"<AddressControllerBean> getSystemParameters : Error fetching SystemParameter",
							e);

			FacesContext context = FacesContext.getCurrentInstance();
			//FindBug Issue Resolved
			//ResourceBundle bundle =
			// ResourceBundle.getBundle(EnterpriseMessageConstants.COMMON_ERR_MSG_BUNDLE,
			//  context.getViewRoot().getLocale());
			context.addMessage(null, new FacesMessage(
					EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE));
		}

		logger
				.debug("<AddressControllerBean> getSystemParameters : SystemParameter fetched = "
						+ addressDefaultState);
	}

	/**
	 * @return Returns the addressStatusList.
	 */
	public List getAddressStatusList() {
		return addressStatusList;
	}

	/**
	 * @param addressStatusList
	 *            The addressStatusList to set.
	 */
	public void setAddressStatusList(List addressStatusList) {
		this.addressStatusList = addressStatusList;
	}

	/**
	 * @return Returns the addressTypeList.
	 */
	public List getAddressTypeList() {
		return addressTypeList;
	}

	/**
	 * @param addressTypeList
	 *            The addressTypeList to set.
	 */
	public void setAddressTypeList(List addressTypeList) {
		this.addressTypeList = addressTypeList;
	}

	/**
	 * @return Returns the cityList.
	 */
	/*public List getCityList() {
		return cityList;
	}*/

	/**
	 * @param cityList
	 *            The cityList to set.
	 */
	/*public void setCityList(List cityList) {
		this.cityList = cityList;
	}*/

	/**
	 * @return Returns the countryList.
	 */
	public List getCountryList() {
		return countryList;
	}

	/**
	 * @param countryList
	 *            The countryList to set.
	 */
	public void setCountryList(List countryList) {
		this.countryList = countryList;
	}

	/**
	 * @return Returns the countyList.
	 */
	public List getCountyList() {
		return countyList;
	}

	/**
	 * @param countyList
	 *            The countyList to set.
	 */
	public void setCountyList(List countyList) {
		this.countyList = countyList;
	}

	/**
	 * @return Returns the countyMap.
	 */
	/*public Map getCountyMap() {
		return countyMap;
	}

	*//**
	 * @param countyMap
	 *            The countyMap to set.
	 *//*
	public void setCountyMap(Map countyMap) {
		this.countyMap = countyMap;
	}*/

	/**
	 * @return Returns the lobList.
	 */
	public List getLobList() {
		return lobList;
	}

	/**
	 * @param lobList
	 *            The lobList to set.
	 */
	public void setLobList(List lobList) {
		this.lobList = lobList;
	}

	/**
	 * @return Returns the stateList.
	 */
	public List getStateList() {
		return stateList;
	}

	/**
	 * @param stateList
	 *            The stateList to set.
	 */
	public void setStateList(List stateList) {
		this.stateList = stateList;
	}

	/**
	 * @param addressDefaultState
	 *            The addressDefaultState to set.
	 */
	public void setAddressDefaultState(String addressDefaultState) {
		this.addressDefaultState = addressDefaultState;
	}

	/**
	 * @return Returns the addressDefaultState.
	 */
	public String getAddressDefaultState() {
		return addressDefaultState;
	}

	/**
	 * @return Returns the addressHistRendered.
	 */
	public boolean isAddressHistRendered() {
		return addressHistRendered;
	}

	/**
	 * @param addressHistRendered
	 *            The addressHistRendered to set.
	 */
	public void setAddressHistRendered(boolean addressHistRendered) {
		this.addressHistRendered = addressHistRendered;
	}

	/**
	 * @return Returns the notesCount.
	 */
	public int getNotesCount() {
		return notesCount;
	}

	/**
	 * @param notesCount
	 *            The notesCount to set.
	 */
	public void setNotesCount(int notesCount) {
		this.notesCount = notesCount;
	}

	/**
	 * @return Returns the voidIndicatorForRender.
	 */
	public boolean isVoidIndicatorForRender() {
		return voidIndicatorForRender;
	}

	/**
	 * @param voidIndicatorForRender
	 *            The voidIndicatorForRender to set.
	 */
	public void setVoidIndicatorForRender(boolean voidIndicatorForRender) {
		this.voidIndicatorForRender = voidIndicatorForRender;
	}

	/**
	 * @return Returns the phoneVoidIndicatorRender.
	 */
	public boolean isPhoneVoidIndicatorRender() {
		return phoneVoidIndicatorRender;
	}

	/**
	 * @param phoneVoidIndicatorRender
	 *            The phoneVoidIndicatorRender to set.
	 */
	public void setPhoneVoidIndicatorRender(boolean phoneVoidIndicatorRender) {
		this.phoneVoidIndicatorRender = phoneVoidIndicatorRender;
	}

	/**
	 * @return Returns the addphoneVoidIndicatorRender.
	 */
	public boolean isAddphoneVoidIndicatorRender() {
		return addphoneVoidIndicatorRender;
	}

	/**
	 * @param addphoneVoidIndicatorRender
	 *            The addphoneVoidIndicatorRender to set.
	 */
	public void setAddphoneVoidIndicatorRender(
			boolean addphoneVoidIndicatorRender) {
		this.addphoneVoidIndicatorRender = addphoneVoidIndicatorRender;
	}

	/**
	 * @return Returns the addcontactVoidIndicatorRender.
	 */
	public boolean isAddcontactVoidIndicatorRender() {
		return addcontactVoidIndicatorRender;
	}

	/**
	 * @param addcontactVoidIndicatorRender
	 *            The addcontactVoidIndicatorRender to set.
	 */
	public void setAddcontactVoidIndicatorRender(
			boolean addcontactVoidIndicatorRender) {
		this.addcontactVoidIndicatorRender = addcontactVoidIndicatorRender;
	}

	/**
	 * @return Returns the addeaddressVoidIndicatorRender.
	 */
	public boolean isAddeaddressVoidIndicatorRender() {
		return addeaddressVoidIndicatorRender;
	}

	/**
	 * @param addeaddressVoidIndicatorRender
	 *            The addeaddressVoidIndicatorRender to set.
	 */
	public void setAddeaddressVoidIndicatorRender(
			boolean addeaddressVoidIndicatorRender) {
		this.addeaddressVoidIndicatorRender = addeaddressVoidIndicatorRender;
	}

	/**
	 * @return Returns the contactAuditHistList.
	 */
	public List getContactAuditHistList() {
		return contactAuditHistList;
	}

	/**
	 * @param contactAuditHistList
	 *            The contactAuditHistList to set.
	 */
	public void setContactAuditHistList(List contactAuditHistList) {
		this.contactAuditHistList = contactAuditHistList;
	}

	/**
	 * @return Returns the contactHistRndr.
	 */
	public boolean isContactHistRndr() {
		return contactHistRndr;
	}

	/**
	 * @param contactHistRndr
	 *            The contactHistRndr to set.
	 */
	public void setContactHistRndr(boolean contactHistRndr) {
		this.contactHistRndr = contactHistRndr;
	}

	/**
	 * @return Returns the contactTypeAuditHistList.
	 */
	public List getContactTypeAuditHistList() {
		return contactTypeAuditHistList;
	}

	/**
	 * @param contactTypeAuditHistList
	 *            The contactTypeAuditHistList to set.
	 */
	public void setContactTypeAuditHistList(List contactTypeAuditHistList) {
		this.contactTypeAuditHistList = contactTypeAuditHistList;
	}

	/**
	 * @return Returns the contactTypeHistRndr.
	 */
	public boolean isContactTypeHistRndr() {
		return contactTypeHistRndr;
	}

	/**
	 * @param contactTypeHistRndr
	 *            The contactTypeHistRndr to set.
	 */
	public void setContactTypeHistRndr(boolean contactTypeHistRndr) {
		this.contactTypeHistRndr = contactTypeHistRndr;
	}

	/**
	 * @return Returns the auditEAddressHistRndr.
	 */
	public boolean isAuditEAddressHistRndr() {
		return auditEAddressHistRndr;
	}

	/**
	 * @param auditEAddressHistRndr
	 *            The auditEAddressHistRndr to set.
	 */
	public void setAuditEAddressHistRndr(boolean auditEAddressHistRndr) {
		this.auditEAddressHistRndr = auditEAddressHistRndr;
	}

	/**
	 * @return Returns the auditEAddressAuditHistList.
	 */
	public List getAuditEAddressAuditHistList() {
		return auditEAddressAuditHistList;
	}

	/**
	 * @param auditEAddressAuditHistList
	 *            The auditEAddressAuditHistList to set.
	 */
	public void setAuditEAddressAuditHistList(List auditEAddressAuditHistList) {
		this.auditEAddressAuditHistList = auditEAddressAuditHistList;
	}

	/**
	 * @return Returns the addressAuditHistList.
	 */
	public List getAddressAuditHistList() {
		return addressAuditHistList;
	}

	/**
	 * @param addressAuditHistList
	 *            The addressAuditHistList to set.
	 */
	public void setAddressAuditHistList(List addressAuditHistList) {
		this.addressAuditHistList = addressAuditHistList;
	}

	/**
	 * @return Returns the addressHistRndr.
	 */
	public boolean isAddressHistRndr() {
		return addressHistRndr;
	}

	/**
	 * @param addressHistRndr
	 *            The addressHistRndr to set.
	 */
	public void setAddressHistRndr(boolean addressHistRndr) {
		this.addressHistRndr = addressHistRndr;
	}

	/**
	 * @return Returns the noteSetSK.
	 */
	public Long getNoteSetSK() {
		return noteSetSK;
	}

	/**
	 * @param noteSetSK
	 *            The noteSetSK to set.
	 */
	public void setNoteSetSK(Long noteSetSK) {
		this.noteSetSK = noteSetSK;
	}

	/**
	 * This value will be set to true when add Note button is clicked.
	 */
	private boolean detailSaveNotesChk = false;

	/**
	 * Holds mainNotesRender.
	 */

	private boolean detailMainNotesRender = false; // need to set this as false,

	// corresponding

	// module ppl would set this to true

	/**
	 * Holds newNotesRender.
	 */
	private boolean detailNewNotesRender = false;

	/**
	 * Holds filterNotesRender.
	 */
	private boolean detailFilterNotesRender = false;

	/**
	 * Holds EntityType For Note.
	 */
	private String entityTypeForNote;

	public String getEntityTypeForNote() {
		return entityTypeForNote;
	}

	public void setEntityTypeForNote(String entityTypeForNote) {
		this.entityTypeForNote = entityTypeForNote;
	}

	/**
	 * @return Returns the detailFilterNotesRender.
	 */
	public boolean isDetailFilterNotesRender() {
		return detailFilterNotesRender;
	}

	/**
	 * @param detailFilterNotesRender
	 *            The detailFilterNotesRender to set.
	 */
	public void setDetailFilterNotesRender(boolean detailFilterNotesRender) {
		this.detailFilterNotesRender = detailFilterNotesRender;
	}

	/**
	 * @return Returns the detailMainNotesRender.
	 */
	public boolean isDetailMainNotesRender() {
		return detailMainNotesRender;
	}

	/**
	 * @param detailMainNotesRender
	 *            The detailMainNotesRender to set.
	 */
	public void setDetailMainNotesRender(boolean detailMainNotesRender) {
		this.detailMainNotesRender = detailMainNotesRender;
	}

	/**
	 * @return Returns the detailNewNotesRender.
	 */
	public boolean isDetailNewNotesRender() {
		return detailNewNotesRender;
	}

	/**
	 * @param detailNewNotesRender
	 *            The detailNewNotesRender to set.
	 */
	public void setDetailNewNotesRender(boolean detailNewNotesRender) {
		this.detailNewNotesRender = detailNewNotesRender;
	}

	/**
	 * @return Returns the detailSaveNotesChk.
	 */
	public boolean isDetailSaveNotesChk() {
		return detailSaveNotesChk;
	}

	/**
	 * @param detailSaveNotesChk
	 *            The detailSaveNotesChk to set.
	 */
	public void setDetailSaveNotesChk(boolean detailSaveNotesChk) {
		this.detailSaveNotesChk = detailSaveNotesChk;
	}

	/**
	 * @return Returns the detailNotesSaveMsg.
	 */
	public boolean isDetailNotesSaveMsg() {
		return detailNotesSaveMsg;
	}

	/**
	 * @param detailNotesSaveMsg
	 *            The detailNotesSaveMsg to set.
	 */
	public void setDetailNotesSaveMsg(boolean detailNotesSaveMsg) {
		this.detailNotesSaveMsg = detailNotesSaveMsg;
	}

	/**
	 * Holds detailNotesSaveMsg boolean value.
	 */
	private boolean detailNotesSaveMsg = false;

	/**
	 * @return Returns the disableEAddress.
	 */
	public boolean isDisableEAddress() {
		return disableEAddress;
	}

	/**
	 * @param disableEAddress
	 *            The disableEAddress to set.
	 */
	public void setDisableEAddress(boolean disableEAddress) {
		this.disableEAddress = disableEAddress;
	}

	/**
	 * @return Returns the disablePhoneRecord.
	 */
	public boolean isDisablePhoneRecord() {
		return disablePhoneRecord;
	}

	/**
	 * @param disablePhoneRecord
	 *            The disablePhoneRecord to set.
	 */
	public void setDisablePhoneRecord(boolean disablePhoneRecord) {
		this.disablePhoneRecord = disablePhoneRecord;
	}

	/**
	 * @return Returns the voidInd.
	 */
	public boolean isVoidInd() {
		return voidInd;
	}

	/**
	 * @param voidInd
	 *            The voidInd to set.
	 */
	public void setVoidInd(boolean voidInd) {
		this.voidInd = voidInd;
	}

	/**
	 * Holds rendering of Contact Audit.
	 */
	private boolean auditCon = false;

	/**
	 * @return Returns the auditCon.
	 */
	public boolean isAuditCon() {
		return auditCon;
	}

	/**
	 * @param auditCon
	 *            The auditCon to set.
	 */
	public void setAuditCon(boolean auditCon) {
		this.auditCon = auditCon;
	}

	/**
	 * Holds rendering of Contact Type Audit.
	 */
	private boolean auditConType = false;

	/**
	 * @return Returns the auditConType.
	 */
	public boolean isAuditConType() {
		return auditConType;
	}

	/**
	 * @param auditConType
	 *            The auditConType to set.
	 */
	public void setAuditConType(boolean auditConType) {
		this.auditConType = auditConType;
	}

	/**
	 * Holds rendering of Phone Audit.
	 */
	private boolean auditPhone = false;

	/**
	 * @return Returns the auditPhone.
	 */
	public boolean isAuditPhone() {
		return auditPhone;
	}

	/**
	 * @param auditPhone
	 *            The auditPhone to set.
	 */
	public void setAuditPhone(boolean auditPhone) {
		this.auditPhone = auditPhone;
	}

	/**
	 * Holds rendering of E Address Audit.
	 */
	private boolean auditEAddress = false;

	/**
	 * @return Returns the auditEAddress.
	 */
	public boolean isAuditEAddress() {
		return auditEAddress;
	}

	/**
	 * @param auditEAddress
	 *            The auditEAddress to set.
	 */
	public void setAuditEAddress(boolean auditEAddress) {
		this.auditEAddress = auditEAddress;
	}

	/**
	 * Holds rendering of Address Audit.
	 */
	private boolean auditAddress = false;

	/**
	 * @return Returns the auditAddress.
	 */
	public boolean isAuditAddress() {
		return auditAddress;
	}

	/**
	 * @param auditAddress
	 *            The auditAddress to set.
	 */
	public void setAuditAddress(boolean auditAddress) {
		this.auditAddress = auditAddress;
	}

	/**
	 * @return Returns the status.
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return Returns the significanceList.
	 */
	public List getSignificanceList() {
		return significanceList;
	}

	/**
	 * @param significanceList
	 *            The significanceList to set.
	 */
	public void setSignificanceList(List significanceList) {
		this.significanceList = significanceList;
	}

	/**
	 * @return Returns the changeReasonList.
	 */
	public List getChangeReasonList() {
		return changeReasonList;
	}

	/**
	 * @param changeReasonList
	 *            The changeReasonList to set.
	 */
	public void setChangeReasonList(List changeReasonList) {
		this.changeReasonList = changeReasonList;
	}

	/**
	 * @return Returns the addressRecordHidden.
	 */
	public String getAddressRecordHidden() {
		return addressRecordHidden;
	}

	/**
	 * @param addressRecordHidden
	 *            The addressRecordHidden to set.
	 */
	public void setAddressRecordHidden(String addressRecordHidden) {
		this.addressRecordHidden = addressRecordHidden;
	}

	/**
	 * @return Returns the verifyAddressHidden.
	 */
	public String getVerifyAddressHidden() {
		return verifyAddressHidden;
	}

	/**
	 * @param verifyAddressHidden
	 *            The verifyAddressHidden to set.
	 */
	public void setVerifyAddressHidden(String verifyAddressHidden) {
		this.verifyAddressHidden = verifyAddressHidden;
	}

	/**
	 * @return Returns the globalNotesRender.
	 */
	public boolean isGlobalNotesRender() {
		return globalNotesRender;
	}

	/**
	 * @param globalNotesRender
	 *            The globalNotesRender to set.
	 */
	public void setGlobalNotesRender(boolean globalNotesRender) {
		this.globalNotesRender = globalNotesRender;
	}

	/**
	 * @return Returns the globalDataRender.
	 */
	public boolean isGlobalDataRender() {
		return globalDataRender;
	}

	/**
	 * @param globalDataRender
	 *            The globalDataRender to set.
	 */
	public void setGlobalDataRender(boolean globalDataRender) {
		this.globalDataRender = globalDataRender;
	}

	/**
	 * @return Returns the addressSaveMsg.
	 */
	public boolean isAddressSaveMsg() {
		return addressSaveMsg;
	}

	/**
	 * @param addressSaveMsg
	 *            The addressSaveMsg to set.
	 */
	public void setAddressSaveMsg(boolean addressSaveMsg) {
		this.addressSaveMsg = addressSaveMsg;
	}

	/**
	 * @return Returns the commonNoteSaved.
	 */
	public boolean isCommonNoteSaved() {
		return commonNoteSaved;
	}

	/**
	 * @param commonNoteSaved
	 *            The commonNoteSaved to set.
	 */
	public void setCommonNoteSaved(boolean commonNoteSaved) {
		this.commonNoteSaved = commonNoteSaved;
	}

	/**
	 * @return Returns the commonAddressSaved.
	 */
	public boolean isCommonAddressSaved() {
		return commonAddressSaved;
	}

	/**
	 * @param commonAddressSaved
	 *            The commonAddressSaved to set.
	 */
	public void setCommonAddressSaved(boolean commonAddressSaved) {
		this.commonAddressSaved = commonAddressSaved;
	}

	/**
	 * @return Returns the commonContactSaved.
	 */
	public boolean isCommonContactSaved() {
		return commonContactSaved;
	}

	/**
	 * @param commonContactSaved
	 *            The commonContactSaved to set.
	 */
	public void setCommonContactSaved(boolean commonContactSaved) {
		this.commonContactSaved = commonContactSaved;
	}

	/**
	 * @return Returns the commonEAddressSaved.
	 */
	public boolean isCommonEAddressSaved() {
		return commonEAddressSaved;
	}

	/**
	 * @param commonEAddressSaved
	 *            The commonEAddressSaved to set.
	 */
	public void setCommonEAddressSaved(boolean commonEAddressSaved) {
		this.commonEAddressSaved = commonEAddressSaved;
	}

	/**
	 * @return Returns the showSuccesMessage.
	 */
	public boolean isShowSuccesMessage() {
		return ShowSuccesMessage;
	}

	/**
	 * @param showSuccesMessage
	 *            The showSuccesMessage to set.
	 */
	public void setShowSuccesMessage(boolean showSuccesMessage) {
		ShowSuccesMessage = showSuccesMessage;
	}

	/**
	 * @return Returns the editUpdateEntityPrimaryFlag.
	 */

	/**
	 * @return Returns the editUpdateFlag.
	 */

	/**
	 * @return Returns the updateEntityFlag.
	 */
	public boolean isUpdateEntityFlag() {
		return updateEntityFlag;
	}

	/**
	 * @param updateEntityFlag
	 *            The updateEntityFlag to set.
	 */
	public void setUpdateEntityFlag(boolean updateEntityFlag) {
		this.updateEntityFlag = updateEntityFlag;
	}

	private PhoneVO phoneVO;

	/**
	 * @return Returns the phoneVO.
	 */
	public PhoneVO getPhoneVO() {
		return phoneVO;
	}

	/**
	 * @param phoneVO
	 *            The phoneVO to set.
	 */
	public void setPhoneVO(PhoneVO phoneVO) {
		this.phoneVO = phoneVO;
	}

	/**
	 * Holds commonPhoneSaved.
	 */

	private boolean commonPhoneSaved = false;

	/**
	 * @return Returns the commonPhoneSaved.
	 */
	public boolean isCommonPhoneSaved() {
		return commonPhoneSaved;
	}

	/**
	 * @param commonPhoneSaved
	 *            The commonPhoneSaved to set.
	 */
	public void setCommonPhoneSaved(boolean commonPhoneSaved) {
		this.commonPhoneSaved = commonPhoneSaved;
	}

	/**
	 * @return Returns the commonAddressFalg.
	 */
	public boolean isCommonAddressFalg() {
		return commonAddressFalg;
	}

	/**
	 * @param commonAddressFalg
	 *            The commonAddressFalg to set.
	 */
	public void setCommonAddressFalg(boolean commonAddressFalg) {
		this.commonAddressFalg = commonAddressFalg;
	}

	/**
	 * @return Returns the commonContactFlag.
	 */
	public boolean isCommonContactFlag() {
		return commonContactFlag;
	}

	/**
	 * @param commonContactFlag
	 *            The commonContactFlag to set.
	 */
	public void setCommonContactFlag(boolean commonContactFlag) {
		this.commonContactFlag = commonContactFlag;
	}

	/**
	 * @return Returns the commonEAddressFlag.
	 */
	public boolean isCommonEAddressFlag() {
		return commonEAddressFlag;
	}

	/**
	 * @param commonEAddressFlag
	 *            The commonEAddressFlag to set.
	 */
	public void setCommonEAddressFlag(boolean commonEAddressFlag) {
		this.commonEAddressFlag = commonEAddressFlag;
	}

	/**
	 * @return Returns the commonPhoneFalg.
	 */
	public boolean isCommonPhoneFalg() {
		return commonPhoneFalg;
	}

	/**
	 * @param commonPhoneFalg
	 *            The commonPhoneFalg to set.
	 */
	public void setCommonPhoneFalg(boolean commonPhoneFalg) {
		this.commonPhoneFalg = commonPhoneFalg;
	}

	/**
	 * Holds currentNoteRender.
	 */

	private boolean currentNoteRender = false;

	/**
	 * @return Returns the currentNoteRender.
	 */
	public boolean isCurrentNoteRender() {
		return currentNoteRender;
	}

	/**
	 * @param currentNoteRender
	 *            The currentNoteRender to set.
	 */
	public void setCurrentNoteRender(boolean currentNoteRender) {
		this.currentNoteRender = currentNoteRender;
	}

	/**
	 * @return Returns the selectedParentAuditLog.
	 */
	public AuditLog getSelectedParentAuditLog() {
		return selectedParentAuditLog;
	}

	/**
	 * @param selectedParentAuditLog
	 *            The selectedParentAuditLog to set.
	 */
	public void setSelectedParentAuditLog(AuditLog selectedParentAuditLog) {
		this.selectedParentAuditLog = selectedParentAuditLog;
	}

	/**
	 * @return Returns the auditColumnHistoryRender.
	 */
	public boolean isAuditColumnHistoryRender() {
		return auditColumnHistoryRender;
	}

	/**
	 * @param auditColumnHistoryRender
	 *            The auditColumnHistoryRender to set.
	 */
	public void setAuditColumnHistoryRender(boolean auditColumnHistoryRender) {
		this.auditColumnHistoryRender = auditColumnHistoryRender;
	}

	private PhoneVO tempPhoneVO = new PhoneVO();

	/**
	 * @return Returns the tempPhoneVO.
	 */
	public PhoneVO getTempPhoneVO() {
		return tempPhoneVO;
	}

	/**
	 * @param tempPhoneVO
	 *            The tempPhoneVO to set.
	 */
	public void setTempPhoneVO(PhoneVO tempPhoneVO) {
		this.tempPhoneVO = tempPhoneVO;
	}

	/**
	 * Holds Flag for CommonNotes.
	 */
	private boolean flag = false;

	/**
	 * Holds Flag for Editphone.
	 */
	private boolean phoneFlag = true;

	/**
	 * @return Returns the flag.
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            The flag to set.
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private CommonContactVO tempCommonContactVO = new CommonContactVO();

	/**
	 * @return Returns the tempCommonContactVO.
	 */
	public CommonContactVO getTempCommonContactVO() {
		return tempCommonContactVO;
	}

	/**
	 * @param tempCommonContactVO
	 *            The tempCommonContactVO to set.
	 */
	public void setTempCommonContactVO(CommonContactVO tempCommonContactVO) {
		this.tempCommonContactVO = tempCommonContactVO;
	}

	private AddressVO tempAddressVO = new AddressVO();

	/**
	 * @return Returns the tempAddressVO.
	 */
	public AddressVO getTempAddressVO() {
		return tempAddressVO;
	}

	/**
	 * @param tempAddressVO
	 *            The tempAddressVO to set.
	 */
	public void setTempAddressVO(AddressVO tempAddressVO) {
		this.tempAddressVO = tempAddressVO;
	}

	/**
	 * @return Returns the selectedAdrAuditLog.
	 */
	public AuditLog getSelectedAdrAuditLog() {
		return selectedAdrAuditLog;
	}

	/**
	 * @param selectedAdrAuditLog
	 *            The selectedAdrAuditLog to set.
	 */
	public void setSelectedAdrAuditLog(AuditLog selectedAdrAuditLog) {
		this.selectedAdrAuditLog = selectedAdrAuditLog;
	}

	/**
	 * @return Returns the selectedCntAuditLog.
	 */
	public AuditLog getSelectedCntAuditLog() {
		return selectedCntAuditLog;
	}

	/**
	 * @param selectedCntAuditLog
	 *            The selectedCntAuditLog to set.
	 */
	public void setSelectedCntAuditLog(AuditLog selectedCntAuditLog) {
		this.selectedCntAuditLog = selectedCntAuditLog;
	}

	/**
	 * @return Returns the selectedEAdrAuditLog.
	 */
	public AuditLog getSelectedEAdrAuditLog() {
		return selectedEAdrAuditLog;
	}

	/**
	 * @param selectedEAdrAuditLog
	 *            The selectedEAdrAuditLog to set.
	 */
	public void setSelectedEAdrAuditLog(AuditLog selectedEAdrAuditLog) {
		this.selectedEAdrAuditLog = selectedEAdrAuditLog;
	}

	/**
	 * @return Returns the selectedPhnAuditLog.
	 */
	public AuditLog getSelectedPhnAuditLog() {
		return selectedPhnAuditLog;
	}

	/**
	 * @param selectePhnAuditLog
	 *            The selectePhnAuditLog to set.
	 */
	public void setSelectedPhnAuditLog(AuditLog selectedPhnAuditLog) {
		this.selectedPhnAuditLog = selectedPhnAuditLog;
	}

	private EAddressVO tempEAddressVO = new EAddressVO();

	/**
	 * @return Returns the tempEAddressVO.
	 */
	public EAddressVO getTempEAddressVO() {
		return tempEAddressVO;
	}

	/**
	 * @param tempEAddressVO
	 *            The tempEAddressVO to set.
	 */
	public void setTempEAddressVO(EAddressVO tempEAddressVO) {
		this.tempEAddressVO = tempEAddressVO;
	}

	private String smallSaveFlag = "false";

	/**
	 * @return Returns the smallSaveFlag.
	 */
	public String getSmallSaveFlag() {
		return smallSaveFlag;
	}

	/**
	 * @param smallSaveFlag
	 *            The smallSaveFlag to set.
	 */
	public void setSmallSaveFlag(String smallSaveFlag) {
		this.smallSaveFlag = smallSaveFlag;
	}

	private boolean noteFlag = false;

	/**
	 * @return Returns the noteFlag.
	 */
	public boolean isNoteFlag() {
		return noteFlag;
	}

	/**
	 * @param noteFlag
	 *            The noteFlag to set.
	 */
	public void setNoteFlag(boolean noteFlag) {
		this.noteFlag = noteFlag;
	}

	/**
	 * @return Returns the saveNoteflag.
	 */
	public boolean isSaveNoteflag() {
		return saveNoteflag;
	}

	/**
	 * @param saveNoteflag
	 *            The saveNoteflag to set.
	 */
	public void setSaveNoteflag(boolean saveNoteflag) {
		this.saveNoteflag = saveNoteflag;
	}

	/**
	 * @return Returns the nameSuffixList.
	 */
	public List getNameSuffixList() {
		return nameSuffixList;
	}

	/**
	 * @param nameSuffixList
	 *            The nameSuffixList to set.
	 */
	public void setNameSuffixList(List nameSuffixList) {
		this.nameSuffixList = nameSuffixList;
	}

	/**
	 * @return Returns the noteSet.
	 */
	public NoteSet getNoteSet() {
		return noteSet;
	}

	/**
	 * @param noteSet
	 *            The noteSet to set.
	 */
	public void setNoteSet(NoteSet noteSet) {
		this.noteSet = noteSet;
	}

	/**
	 * @return Returns the phoneFlag.
	 */
	public boolean isPhoneFlag() {
		return phoneFlag;
	}

	/**
	 * @param phoneFlag
	 *            The phoneFlag to set.
	 */
	public void setPhoneFlag(boolean phoneFlag) {
		this.phoneFlag = phoneFlag;
	}

	/* for Eaddress Reset/Cancel */

	private List sigList = new ArrayList();

	/**
	 * @return Returns the sigList.
	 */
	public List getSigList() {
		return sigList;
	}

	/**
	 * @param sigList
	 *            The sigList to set.
	 */
	public void setSigList(List sigList) {
		this.sigList = sigList;
	}

	/**
	 * @return Returns the statusEaddList.
	 */
	public List getStatusEaddList() {
		return statusEaddList;
	}

	/**
	 * @param statusEaddList
	 *            The statusEaddList to set.
	 */
	public void setStatusEaddList(List statusEaddList) {
		this.statusEaddList = statusEaddList;
	}

	/**
	 * @return Returns the usageList.
	 */
	public List getUsageList() {
		return usageList;
	}

	/**
	 * @param usageList
	 *            The usageList to set.
	 */
	public void setUsageList(List usageList) {
		this.usageList = usageList;
	}

	private List usageList = new ArrayList();

	private List statusEaddList = new ArrayList();

	private boolean contactFlag;

	private boolean eAddressFlag;

	/**
	 * @return Returns the contactFlag.
	 */
	public boolean isContactFlag() {
		return contactFlag;
	}

	/**
	 * @param contactFlag
	 *            The contactFlag to set.
	 */
	public void setContactFlag(boolean contactFlag) {
		this.contactFlag = contactFlag;
	}

	/**
	 * @return Returns the eAddressFlag.
	 */
	public boolean isEAddressFlag() {
		return eAddressFlag;
	}

	/**
	 * @param addressFlag
	 *            The eAddressFlag to set.
	 */
	public void setEAddressFlag(boolean addressFlag) {
		eAddressFlag = addressFlag;
	}

	private boolean disableaddNewNote;

	/**
	 * @return Returns the disableaddNewNote.
	 */
	public boolean isDisableaddNewNote() {
		return disableaddNewNote;
	}

	/**
	 * @param disableaddNewNote
	 *            The disableaddNewNote to set.
	 */
	public void setDisableaddNewNote(boolean disableaddNewNote) {
		this.disableaddNewNote = disableaddNewNote;
	}

	private int startIndexForCmnNotes = 0;
	private int startIndexForPhone=0;
	private int startIndexForEAdrs=0;
	
	

	/**
	 * @return Returns the startIndexForEAdrs.
	 */
	public int getStartIndexForEAdrs() {
		return startIndexForEAdrs;
	}
	/**
	 * @param startIndexForEAdrs The startIndexForEAdrs to set.
	 */
	public void setStartIndexForEAdrs(int startIndexForEAdrs) {
		this.startIndexForEAdrs = startIndexForEAdrs;
	}
	/**
	 * @return Returns the startIndexForPhone.
	 */
	public int getStartIndexForPhone() {
		return startIndexForPhone;
	}
	/**
	 * @param startIndexForPhone The startIndexForPhone to set.
	 */
	public void setStartIndexForPhone(int startIndexForPhone) {
		this.startIndexForPhone = startIndexForPhone;
	}
	/**
	 * @return Returns the startIndexForCmnNotes.
	 */
	public int getStartIndexForCmnNotes() {
		return startIndexForCmnNotes;
	}

	/**
	 * @param startIndexForCmnNotes
	 *            The startIndexForCmnNotes to set.
	 */
	public void setStartIndexForCmnNotes(int startIndexForCmnNotes) {
		this.startIndexForCmnNotes = startIndexForCmnNotes;
	}
	
	//Added for the defect ESPRD00542028
	public int startRecord;

	public int getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	public int getEndRecord() {
		return endRecord;
	}

	public void setEndRecord(int endRecord) {
		this.endRecord = endRecord;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int endRecord;

	public int count = 0;
	
	private String noteText;

	/**
	 * @return the noteText
	 */
	public String getNoteText() {
		return noteText;
	}

	/**
	 * @param noteText the noteText to set
	 */
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	/**This will hold the logged in userid
	 * */
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
	
	//added for the defect ESPRD00795893
	
	/**
	 * @param renderedreccordsnum holds record count render for notes
	 */
	private boolean renderedreccordsnum;

	public boolean isRenderedreccordsnum() {
		return renderedreccordsnum;
	}

	public int getItemSelectedRowIndeNotes() {
		return itemSelectedRowIndeNotes;
	}

	public void setItemSelectedRowIndeNotes(int itemSelectedRowIndeNotes) {
		this.itemSelectedRowIndeNotes = itemSelectedRowIndeNotes;
	}

	public void setRenderedreccordsnum(boolean renderedreccordsnum) {
		this.renderedreccordsnum = renderedreccordsnum;
	}
	
	//COde added for defect - ESPRD00920192 for row highlight to display the highlighted row in notes section
	/** holds itemSelectedRowIndeNotes */
	private int itemSelectedRowIndeNotes = -1;
	
}