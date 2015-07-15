package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CMEntityDetailVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * @author vijaymai
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CMEntityMaintainDataBean extends EnterpriseBaseDataBean {


	/**
	 * Constructor for CategoryDataBean
	 */
	public CMEntityMaintainDataBean() {
		super();
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CMEntityMaintainDataBean.class);
		}

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

	/**
	 * Generating reference of CMEntityDetailVO for maintain entity page.
	 */
	private CMEntityDetailVO cmEnityDetailVO = new CMEntityDetailVO();

	/**
	 * Generating reference of CMEntityDetailVO for view entity page.
	 */
	private CMEntityDetailVO duplicateCMEnityDetailVO = new CMEntityDetailVO();

	/**
	 * Variable to reneder JSP Page for Unenrolled Provider.
	 */
	private Boolean renderUnenrProv = Boolean.FALSE;

	/**
	 * Variable to reneder JSP Page for Unenrolled Provider.
	 */
	private Boolean renderDistOffice = Boolean.FALSE;

	/**
	 * Variable to reneder JSP Page for Default and common Entity .
	 */
	private Boolean renderDefnComm = Boolean.TRUE;

	/**
	 * Variable to reneder JSP Page for Billing Organizatione .
	 */
	private Boolean renderBillingOrganizatione = Boolean.FALSE;
	
	
	// Boolean variable renderDynamicContentInformation and setter/getter are added for CR : ESPRD00808886
	/**
	 * Variable to reneder JSP Page for Dynamic Content Information .
	 */
	private Boolean renderDynamicContentInformation = Boolean.FALSE;
	

	/**
	 * @return the renderDynamicContentInformation
	 */
	public Boolean getRenderDynamicContentInformation() {
		return renderDynamicContentInformation;
	}

	/**
	 * @param renderDynamicContentInformation the renderDynamicContentInformation to set
	 */
	public void setRenderDynamicContentInformation(
			Boolean renderDynamicContentInformation) {
		this.renderDynamicContentInformation = renderDynamicContentInformation;
	}

	/**
	 * Variable to reneder Entity Type drop down for Add Entity .
	 */
	private Boolean renderEntityTypeDropDown = Boolean.TRUE;

	/**
	 * Variable to reneder Entity Type text for Maintain entity .
	 */
	private Boolean renderEntityTypeText = Boolean.FALSE;

	/**
	 * Variable to reneder Save for Update Maintain entity .
	 */
	private Boolean renderUpdateEntitySave = Boolean.FALSE;

	/**
	 * Variable to reneder save link for add Maintain entity .
	 */
	private Boolean renderAddEntitySave = Boolean.TRUE;

	/**
	 * Variable to reneder Correspondenc save link for add Maintain entity .
	 */
	private boolean renderCorrespondenceSave = true;

	/**
	 * Variable to reneder Case save link for add Maintain entity .
	 */
	private boolean renderCaseSave = true;

	/**
	 * Variable to reneder Correspondence Inquiry Entity save link for add
	 * Maintain entity .
	 */
	private boolean renderCorresInquiryEntitySave = true;

	/**
	 * Variable to reneder Casee Inquiry Entity save link for add Maintain
	 * entity .
	 */
	private boolean renderCaseInquiryEntitySave = true;

	/**
	 * Variable to reneder name .
	 */
	private Boolean renderName = Boolean.TRUE;

	/**
	 * Variable to reneder renderOrgName .
	 */
	private Boolean renderOrgName = Boolean.FALSE;

	/**
	 * Variable to reneder JSP Page for County.
	 */
	private Boolean renderCounty = Boolean.FALSE;

	/**
	 * Variable to reneder Page View Entity .
	 */
	private Boolean renderViewEntity = Boolean.FALSE;

	/**
	 * Variable to reneder Page Maintain Entity .
	 */
	private Boolean renderMaintainEntity = Boolean.TRUE;

	/**
	 * Variable to reneder Hold if the Unenrolled provider is Validated .
	 */
	private boolean validUP = false;

	/**
	 * Variable used to render Image for Sorting purpose.
	 */
	private String imageRender;

	/**
	 * List to hold values for Dropdown for Entity Type.
	 */
	private List entityTypeListME = new ArrayList();

	/**
	 * List to hold values for Dropdown for Entity Type .
	 */
	private List provTypeListME = new ArrayList();

	/**
	 * List to hold values for Dropdown for Entity ID TYPE.
	 */
	private List entityIDTypeME = new ArrayList();

	/**
	 * List to hold values for Dropdown for Entity Type.
	 */
	private List lobListME = new ArrayList();

	/**
	 * List to hold values for Dropdown for Prefix .
	 */
	private List prefixListME = new ArrayList();

	/**
	 * List to hold values for Dropdown for Organization type .
	 */
	private List orgTypeListME = new ArrayList();

	/**
	 * List to hold values for Dropdown for Organization type .
	 */
	private List distOffCodeListME = new ArrayList();

	/**
	 * List to hold values for Duplicate Entities.
	 */
	private List duplicateEntityList = new ArrayList();

	/**
	 * List to hold values for Dropdown for County Code.
	 */
	private List countyCodeListME = new ArrayList();

	/**
	 * This field is used to store showMessage.
	 */
	private boolean showMessage = false;

	/**
	 * This field is used to store showSuccessMessage.
	 */
	private boolean showSuccessMessage = false;

	/**
	 * This field is used to render the detail block of View Entity .
	 */
	private boolean renderViewEntityDetail = false;

	/**
	 * This field is used to render the detail block of View Entity .
	 */
	//commented for unused variables
	//private boolean viewEntityDetailFlag = false;

	/**
	 * This field is used to render the detail block of View Entity .
	 */
	//commented for unused variables
	//private boolean updateMaintainEntity = false;

	/**
	 * This field is used to render the Validate Message .
	 */
	private boolean showValidatedMessage = false;

	/**
	 * This field is used to Store the provider system ID after validating
	 * Entity Id and Entity ID Type .
	 */
	private Long provSysIDAfterValidation;

	/**
	 * This feild stores the name of the source porlet.
	 */
	private String sourcePorletName = null;

	/**
	 * This variable is Used For IPC to Maintain Enity . The Geter method is
	 * Called in maintainEnity Portlet.
	 */
	private String loadRefernceData;

	/**
	 * This field is used to render Cancel Command link .
	 */
	private boolean renderCancelCommandLink = false;

	/**
	 * This field is used to render thecancel output link .
	 */
	private boolean renderCancelOutputLink = false;

	/** To hold contactHidden. */
	private String contactHidden = "plus";

	/** To hold addressHidden. */
	private String addressHidden = "plus";

	/** To hold phRecordHidden. */
	private String phRecordHidden = "plus";

	/** To hold eAddressHidden. */
	private String eaddressHidden = "plus";

	/**
	 * This is used to hold audit field history list for details page.
	 */
	private ArrayList auditHistoryList = new ArrayList();

	/**
	 * This is used to show audit log for details page.
	 */
	private boolean auditHistoryRender = false;

	/**
	 * This is used to hold the state of the Audit block open or closed.
	 */
	private boolean auditOpen = false;
	
	/**
	 * This is used to hold the state of InquirySave in MaintainEntity Page.
	 */

	private boolean InquirySave = false;

	/**
	 * @return the inquirySave
	 */
	public boolean isInquirySave() {
		return InquirySave;
	}

	/**
	 * @param inquirySave the inquirySave to set
	 */
	public void setInquirySave(boolean inquirySave) {
		InquirySave = inquirySave;
	}

	/**
	 * This is used to show column changes for details page.
	 */
	private boolean auditColumnHistoryRender = false;

	/**
	 * Declated to hold the selected AuditLog.
	 */
	private AuditLog selectedAuditLog;

	/** holds the total number of records per page. */
	private int itemsPerPage = 10;

	/** holds the ParamName for continue with add button */
	private String continueParamName = " ";

	/** holds the ParamValue for continue with add button */
	private String continueParamValue = " ";

	/**
	 * @return Returns the itemsPerPage.
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * @return Returns the cmEnityDetailVO.
	 */
	public CMEntityDetailVO getCmEnityDetailVO() {
		return cmEnityDetailVO;
	}

	/**
	 * @param cmEnityDetailVO
	 *            The cmEnityDetailVO to set.
	 */
	public void setCmEnityDetailVO(CMEntityDetailVO cmEnityDetailVO) {
		this.cmEnityDetailVO = cmEnityDetailVO;
	}

	/**
	 * @return Returns the duplicateCMEnityDetailVO.
	 */
	public CMEntityDetailVO getDuplicateCMEnityDetailVO() {
		return duplicateCMEnityDetailVO;
	}

	/**
	 * @param duplicateCMEnityDetailVO
	 *            The duplicateCMEnityDetailVO to set.
	 */
	public void setDuplicateCMEnityDetailVO(
			CMEntityDetailVO duplicateCMEnityDetailVO) {
		this.duplicateCMEnityDetailVO = duplicateCMEnityDetailVO;
	}

	/**
	 * @return Returns the showMessage.
	 */
	public boolean isShowMessage() {
		return showMessage;
	}

	/**
	 * @param showMessage
	 *            The showMessage to set.
	 */
	public void setShowMessage(boolean showMessage) {
		this.showMessage = showMessage;
	}

	/**
	 * @return Returns the renderViewEntityDetail.
	 */
	public boolean isRenderViewEntityDetail() {
		return renderViewEntityDetail;
	}

	/**
	 * @param renderViewEntityDetail
	 *            The renderViewEntityDetail to set.
	 */
	public void setRenderViewEntityDetail(boolean renderViewEntityDetail) {
		this.renderViewEntityDetail = renderViewEntityDetail;
	}

	/**
	 * @return Returns the renderDefnComm.
	 */
	public Boolean getRenderDefnComm() {
		return renderDefnComm;
	}

	/**
	 * @param renderDefnComm
	 *            The renderDefnComm to set.
	 */
	public void setRenderDefnComm(Boolean renderDefnComm) {
		this.renderDefnComm = renderDefnComm;
	}

	/**
	 * @return Returns the renderCounty.
	 */
	public Boolean getRenderCounty() {
		return renderCounty;
	}

	/**
	 * @param renderCounty
	 *            The renderCounty to set.
	 */
	public void setRenderCounty(Boolean renderCounty) {
		this.renderCounty = renderCounty;
	}

	/**
	 * @return Returns the renderEntityTypeDropDown.
	 */
	public Boolean getRenderEntityTypeDropDown() {
		return renderEntityTypeDropDown;
	}

	/**
	 * @param renderEntityTypeDropDown
	 *            The renderEntityTypeDropDown to set.
	 */
	public void setRenderEntityTypeDropDown(Boolean renderEntityTypeDropDown) {
		this.renderEntityTypeDropDown = renderEntityTypeDropDown;
	}

	/**
	 * @return Returns the renderEntityTypeText.
	 */
	public Boolean getRenderEntityTypeText() {
		return renderEntityTypeText;
	}

	/**
	 * @param renderEntityTypeText
	 *            The renderEntityTypeText to set.
	 */
	public void setRenderEntityTypeText(Boolean renderEntityTypeText) {
		this.renderEntityTypeText = renderEntityTypeText;
	}

	/**
	 * @return Returns the renderAddEntitySave.
	 */
	public Boolean getRenderAddEntitySave() {
		return renderAddEntitySave;
	}

	/**
	 * @param renderAddEntitySave
	 *            The renderAddEntitySave to set.
	 */
	public void setRenderAddEntitySave(Boolean renderAddEntitySave) {
		this.renderAddEntitySave = renderAddEntitySave;
	}

	/**
	 * @return Returns the renderName.
	 */
	public Boolean getRenderName() {
		return renderName;
	}

	/**
	 * @param renderName
	 *            The renderName to set.
	 */
	public void setRenderName(Boolean renderName) {
		this.renderName = renderName;
	}

	/**
	 * @return Returns the renderOrgName.
	 */
	public Boolean getRenderOrgName() {
		return renderOrgName;
	}

	/**
	 * @param renderOrgName
	 *            The renderOrgName to set.
	 */
	public void setRenderOrgName(Boolean renderOrgName) {
		this.renderOrgName = renderOrgName;
	}

	/**
	 * @return Returns the renderMaintainEntity.
	 */
	public Boolean getRenderMaintainEntity() {
		return renderMaintainEntity;
	}

	/**
	 * @param renderMaintainEntity
	 *            The renderMaintainEntity to set.
	 */
	public void setRenderMaintainEntity(Boolean renderMaintainEntity) {
		this.renderMaintainEntity = renderMaintainEntity;
	}

	/**
	 * @return Returns the validUP.
	 */
	public boolean isValidUP() {
		return validUP;
	}

	/**
	 * @param validUP
	 *            The validUP to set.
	 */
	public void setValidUP(boolean validUP) {
		this.validUP = validUP;
	}

	/**
	 * @return Returns the renderViewEntity.
	 */
	public Boolean getRenderViewEntity() {
		return renderViewEntity;
	}

	/**
	 * @param renderViewEntity
	 *            The renderViewEntity to set.
	 */
	public void setRenderViewEntity(Boolean renderViewEntity) {
		this.renderViewEntity = renderViewEntity;
	}

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

	/**
	 * @return Returns the renderUpdateEntitySave.
	 */
	public Boolean getRenderUpdateEntitySave() {
		return renderUpdateEntitySave;
	}

	/**
	 * @param renderUpdateEntitySave
	 *            The renderUpdateEntitySave to set.
	 */
	public void setRenderUpdateEntitySave(Boolean renderUpdateEntitySave) {
		this.renderUpdateEntitySave = renderUpdateEntitySave;
	}

	/**
	 * @return Returns the renderDistOffice.
	 */
	public Boolean getRenderDistOffice() {
		return renderDistOffice;
	}

	/**
	 * @param renderDistOffice
	 *            The renderDistOffice to set.
	 */
	public void setRenderDistOffice(Boolean renderDistOffice) {
		this.renderDistOffice = renderDistOffice;
	}

	/**
	 * @return Returns the renderUnenrProv.
	 */
	public Boolean getRenderUnenrProv() {
		return renderUnenrProv;
	}

	/**
	 * @param renderUnenrProv
	 *            The renderUnenrProv to set.
	 */
	public void setRenderUnenrProv(Boolean renderUnenrProv) {
		this.renderUnenrProv = renderUnenrProv;
	}

	/**
	 * @return Returns the entityTypeListME.
	 */
	public List getEntityTypeListME() {

		return entityTypeListME;
	}

	/**
	 * @param entityTypeListME
	 *            The entityTypeListME to set.
	 */
	public void setEntityTypeListME(List entityTypeListME) {
		this.entityTypeListME = entityTypeListME;
	}

	/**
	 * @return Returns the lobListME.
	 */
	public List getLobListME() {
		return lobListME;
	}

	/**
	 * @param lobListME
	 *            The lobListME to set.
	 */
	public void setLobListME(List lobListME) {
		this.lobListME = lobListME;
	}

	/**
	 * @return Returns the provTypeListME.
	 */
	public List getProvTypeListME() {

		return provTypeListME;
	}

	/**
	 * @param provTypeListME
	 *            The provTypeListME to set.
	 */
	public void setProvTypeListME(List provTypeListME) {
		this.provTypeListME = provTypeListME;
	}

	/**
	 * @return Returns the entityIDTypeME.
	 */
	public List getEntityIDTypeME() {
		return entityIDTypeME;
	}

	/**
	 * @param entityIDTypeME
	 *            The entityIDTypeME to set.
	 */
	public void setEntityIDTypeME(List entityIDTypeME) {
		this.entityIDTypeME = entityIDTypeME;
	}

	/**
	 * @return Returns the prefixListME.
	 */
	public List getPrefixListME() {
		return prefixListME;
	}

	/**
	 * @param prefixListME
	 *            The prefixListME to set.
	 */
	public void setPrefixListME(List prefixListME) {
		this.prefixListME = prefixListME;
	}

	/**
	 * @return Returns the orgTypeListME.
	 */
	public List getOrgTypeListME() {
		return orgTypeListME;
	}

	/**
	 * @param orgTypeListME
	 *            The orgTypeListME to set.
	 */
	public void setOrgTypeListME(List orgTypeListME) {
		this.orgTypeListME = orgTypeListME;
	}

	/**
	 * @return Returns the distOffCodeListME.
	 */
	public List getDistOffCodeListME() {
		return distOffCodeListME;
	}

	/**
	 * @param distOffCodeListME
	 *            The distOffCodeListME to set.
	 */
	public void setDistOffCodeListME(List distOffCodeListME) {
		this.distOffCodeListME = distOffCodeListME;
	}

	/**
	 * @return Returns the duplicateEntityList.
	 */
	public List getDuplicateEntityList() {
		return duplicateEntityList;
	}

	/**
	 * @param duplicateEntityList
	 *            The duplicateEntityList to set.
	 */
	public void setDuplicateEntityList(List duplicateEntityList) {
		this.duplicateEntityList = duplicateEntityList;
	}

	/**
	 * /**
	 * 
	 * @return Returns the countyCodeListME.
	 */
	public List getCountyCodeListME() {

		return countyCodeListME;
	}

	/**
	 * @param countyCodeListME
	 *            The countyCodeListME to set.
	 */
	public void setCountyCodeListME(List countyCodeListME) {
		this.countyCodeListME = countyCodeListME;
	}

	/**
	 * @return Returns the showSuccessMessage.
	 *  
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
	 * @return Returns the updateMaintainEntity.
	 */
	/*public boolean isUpdateMaintainEntity() {
		return updateMaintainEntity;
	}

	*//**
	 * @param updateMaintainEntity
	 *            The updateMaintainEntity to set.
	 *//*
	public void setUpdateMaintainEntity(boolean updateMaintainEntity) {
		this.updateMaintainEntity = updateMaintainEntity;
	}*/

	/**
	 * @return Returns the viewEntityDetailFlag.
	 */
	/*public boolean isViewEntityDetailFlag() {
		return viewEntityDetailFlag;
	}

	*//**
	 * @param viewEntityDetailFlag
	 *            The viewEntityDetailFlag to set.
	 *//*
	public void setViewEntityDetailFlag(boolean viewEntityDetailFlag) {
		this.viewEntityDetailFlag = viewEntityDetailFlag;
	}*/

	/**
	 * @return Returns the loadRefernceData.
	 */
	public String getLoadRefernceData() {
		//logger.info("Inside getloadRefernceData");

		/**
		 * Creates Reference of CMEntiyDOConvertor.
		 *  
		 */

		CMEntityDOConvertor cmEnityDOConvertor = new CMEntityDOConvertor();
		/** Load Entity Type Drop Down */
		if (cmEnityDOConvertor.getSpecEntityTypeReferenceData() != null) {
			/**
			 * This method filters Out the Provider and member Valid avlues and
			 * Populates the list
			 */
			setEntityTypeListME(cmEnityDOConvertor
					.getSpecEntityTypeReferenceData());
		}
		/** Load Prefix Drop Down */
		if (cmEnityDOConvertor.getPrefixReferenceData() != null) {
			setPrefixListME(cmEnityDOConvertor.getPrefixReferenceData());
		}

		/** Load LOB Drop Down */
		if (cmEnityDOConvertor.getLobReferenceData() != null) {
			setLobListME(cmEnityDOConvertor.getLobReferenceData());
		}

		/** Load Provider Type Drop Down */
		if (cmEnityDOConvertor.getProvTypeReferenceData() != null) {
			setProvTypeListME(cmEnityDOConvertor.getProvTypeReferenceData());

		}

		/** Set the Valid values for Provider Type */
		if (cmEnityDOConvertor.getProvTypeReferenceData() != null) {
			setProvTypeListME(cmEnityDOConvertor.getProvTypeReferenceData());

		}

		/** Set the Valid values forOrganization Type */

		if (cmEnityDOConvertor.getOrgTypeReferenceData() != null) {
			setOrgTypeListME(cmEnityDOConvertor.getOrgTypeReferenceData());
		}

		/** Set the Valid values for District Office Code */

		if (cmEnityDOConvertor.getDistrictOfficeReferenceData() != null) {
			setDistOffCodeListME(cmEnityDOConvertor
					.getDistrictOfficeReferenceData());
		}

		/** Set the Valid values forOrganization Type */

		/*
		 * Long sysListNumber =
		 * ContactManagementHelper.getSystemlistForEntyIdType(
		 * ContactManagementConstants.ENTITYIDTYPE,
		 * ContactManagementConstants.ENTITY_TYPE_PROV); if
		 * (cmEnityDOConvertor.getEntIDTypeReferenceData(
		 * FunctionalAreaConstants.PROVIDER,sysListNumber) != null) {
		 * setEntityIDTypeME(cmEnityDOConvertor.getEntIDTypeReferenceData(
		 * FunctionalAreaConstants.PROVIDER,sysListNumber)); }
		 */
		/** Load county code Drop Down */
		if (cmEnityDOConvertor.getCountyCodeReferenceData() != null) {
			setCountyCodeListME(cmEnityDOConvertor.getCountyCodeReferenceData());

		}
		String entityType = cmEnityDetailVO.getEntityType();
		if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_MEM, entityType)) {

			//        	on load cursor focus
			cursorFocusId = "prf";
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_MEM);
			/** Populates the Entity ID Type dropdown for Entity Type Member */
			if (cmEnityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				setEntityIDTypeME(cmEnityDOConvertor.getEntIDTypeReferenceData(
						FunctionalAreaConstants.GENERAL, sysListNumber));
			}
			/** Render the Block that is to be Dispalyed and Disable others */

		}

		/** ENTITY TYPE -- PROVIDER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_PROV, entityType)) {

			//        	on load cursor focus
			//for CR ESPRD00703521
			cursorFocusId = "prf";
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							"G_REP",
							"PROV_PSTN_CD");
			/** Populates the Entity ID Type dropdown for Entity Type Provider */
			if (cmEnityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.PROVIDER, sysListNumber) != null) {
				setEntityIDTypeME(cmEnityDOConvertor.getEntIDTypeReferenceData(
						FunctionalAreaConstants.PROVIDER, sysListNumber));
			}

			/** Render the Block that is to be Dispalyed and Disable others */

		}

		/** ENTITY TYPE -- UNENROLLED PROVIDER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_UNPROV, entityType)) {

			//        	on load cursor focus
			cursorFocusId = "EntIDType";
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_PROVIDER);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type Unenrolled
			 * Provider
			 */
			if (cmEnityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.PROVIDER, sysListNumber) != null) {
				setEntityIDTypeME(cmEnityDOConvertor.getEntIDTypeReferenceData(
						FunctionalAreaConstants.PROVIDER, sysListNumber));

			}
			/** Clear the Drop Down if any for Unenrolled Provider */

		}

		/** ENTITY TYPE -- TPL CARRIER */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_TPL, entityType)) {

			//        	on load cursor focus
			cursorFocusId = "EntIDType";
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_TPL);
			/** Populates the Entity ID Type dropdown for Entity Type TPL Carrier */
			if (cmEnityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				setEntityIDTypeME(cmEnityDOConvertor.getEntIDTypeReferenceData(
						FunctionalAreaConstants.GENERAL, sysListNumber));
			}

			/** Clear the Drop Down if any for TPL */

		}

		/** ENTITY TYPE -- DISTRICT OFFICE */

		else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_DO, entityType)) {

			//        	on load cursor focus
			cursorFocusId = "distCD";
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_DO);
			/**
			 * Populates the Entity ID Type dropdown for Entity Type District
			 * Office
			 */
			if (cmEnityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				setEntityIDTypeME(cmEnityDOConvertor.getEntIDTypeReferenceData(
						FunctionalAreaConstants.GENERAL, sysListNumber));
			}
			/** Render the Block that is to be Dispalyed and Disable others */

		} else if (StringUtils.equalsIgnoreCase(
				ContactManagementConstants.ENTITY_TYPE_CT, entityType)) {

			//        	on load cursor focus
			cursorFocusId = "countyCD";
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CT);
			/** Populates the Entity ID Type dropdown for Entity Type County */
			if (cmEnityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				setEntityIDTypeME(cmEnityDOConvertor.getEntIDTypeReferenceData(
						FunctionalAreaConstants.GENERAL, sysListNumber));
			}
			/** Render the Block that is to be Dispalyed and Disable others */

		}

		else {
			/** ENTITY TYPE -- UNENROLLED MEMBER and other Entities */

			//        	on load cursor focus
			cursorFocusId = "entTydropdownMem";
			Long sysListNumber = ContactManagementHelper
					.getSystemlistForEntyIdType(
							ContactManagementConstants.ENTITYIDTYPE,
							ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);
			/** Populates the Entity ID Type dropdown for Entity Type Other */
			if (cmEnityDOConvertor.getEntIDTypeReferenceData(
					FunctionalAreaConstants.GENERAL, sysListNumber) != null) {
				setEntityIDTypeME(cmEnityDOConvertor.getEntIDTypeReferenceData(
						FunctionalAreaConstants.GENERAL, sysListNumber));
			}
			/** Clear the Drop Down if any for Unenrolled Member */

		}

		return loadRefernceData;
	}

	/**
	 * @param loadRefernceData
	 *            The loadRefernceData to set.
	 */
	public void setLoadRefernceData(String loadRefernceData) {
		this.loadRefernceData = loadRefernceData;
	}

	/**
	 * @return Returns the showValidatedMessage.
	 */
	public boolean isShowValidatedMessage() {
		return showValidatedMessage;
	}

	/**
	 * @param showValidatedMessage
	 *            The showValidatedMessage to set.
	 */
	public void setShowValidatedMessage(boolean showValidatedMessage) {
		this.showValidatedMessage = showValidatedMessage;
	}

	/**
	 * @return Returns the provSysIDAfterValidation.
	 */
	public Long getProvSysIDAfterValidation() {
		return provSysIDAfterValidation;
	}

	/**
	 * @param provSysIDAfterValidation
	 *            The provSysIDAfterValidation to set.
	 */
	public void setProvSysIDAfterValidation(Long provSysIDAfterValidation) {
		this.provSysIDAfterValidation = provSysIDAfterValidation;
	}

	/**
	 * @return Returns the sourcePorletName.
	 */
	public String getSourcePorletName() {
		return sourcePorletName;
	}

	/**
	 * @param sourcePorletName
	 *            The sourcePorletName to set.
	 */
	public void setSourcePorletName(String sourcePorletName) {
		this.sourcePorletName = sourcePorletName;
	}

	/**
	 * @return Returns the renderCancelCommandLink.
	 */
	public boolean isRenderCancelCommandLink() {
		return renderCancelCommandLink;
	}

	/**
	 * @param renderCancelCommandLink
	 *            The renderCancelCommandLink to set.
	 */
	public void setRenderCancelCommandLink(boolean renderCancelCommandLink) {
		this.renderCancelCommandLink = renderCancelCommandLink;
	}

	/**
	 * @return Returns the renderCancelOutputLink.
	 */
	public boolean isRenderCancelOutputLink() {
		return renderCancelOutputLink;
	}

	/**
	 * @param renderCancelOutputLink
	 *            The renderCancelOutputLink to set.
	 */
	public void setRenderCancelOutputLink(boolean renderCancelOutputLink) {
		this.renderCancelOutputLink = renderCancelOutputLink;
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
	 * 
	 * /**
	 * 
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
	 * @return Returns the auditHistoryList.
	 */
	public ArrayList getAuditHistoryList() {
		return auditHistoryList;
	}

	/**
	 * @param auditHistoryList
	 *            The auditHistoryList to set.
	 */
	public void setAuditHistoryList(ArrayList auditHistoryList) {
		this.auditHistoryList = auditHistoryList;
	}

	/**
	 * @return Returns the auditHistoryRender.
	 */
	public boolean isAuditHistoryRender() {
		return auditHistoryRender;
	}

	/**
	 * @param auditHistoryRender
	 *            The auditHistoryRender to set.
	 */
	public void setAuditHistoryRender(boolean auditHistoryRender) {
		this.auditHistoryRender = auditHistoryRender;
	}

	/**
	 * @return Returns the auditOpen.
	 */
	public boolean isAuditOpen() {
		return auditOpen;
	}

	/**
	 * @param auditOpen
	 *            The auditOpen to set.
	 */
	public void setAuditOpen(boolean auditOpen) {
		this.auditOpen = auditOpen;
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

	/* Small save Big Save start */
	private String fileSavedFlag = "false";

	/**
	 * @return Returns the fileSavedFlag.
	 */
	public String getFileSavedFlag() {
		return fileSavedFlag;
	}

	/**
	 * @param fileSavedFlag
	 *            The fileSavedFlag to set.
	 */
	public void setFileSavedFlag(String fileSavedFlag) {
		this.fileSavedFlag = fileSavedFlag;
	}

	/**
	 * Variable used to hold URL value for URL mapping.
	 */
	private String urlMappingValue;

	/**
	 * @return Returns the urlMappingValue.
	 */
	public String getUrlMappingValue() {
		return urlMappingValue;
	}

	/**
	 * @param urlMappingValue
	 *            The urlMappingValue to set.
	 */
	public void setUrlMappingValue(String urlMappingValue) {
		this.urlMappingValue = urlMappingValue;
	}

	/**
	 * @return Returns the renderCaseInquiryEntitySave.
	 */
	public boolean isRenderCaseInquiryEntitySave() {
		return renderCaseInquiryEntitySave;
	}

	/**
	 * @param renderCaseInquiryEntitySave
	 *            The renderCaseInquiryEntitySave to set.
	 */
	public void setRenderCaseInquiryEntitySave(
			boolean renderCaseInquiryEntitySave) {
		this.renderCaseInquiryEntitySave = renderCaseInquiryEntitySave;
	}

	/**
	 * @return Returns the renderCaseSave.
	 */
	public boolean isRenderCaseSave() {
		return renderCaseSave;
	}

	/**
	 * @param renderCaseSave
	 *            The renderCaseSave to set.
	 */
	public void setRenderCaseSave(boolean renderCaseSave) {
		this.renderCaseSave = renderCaseSave;
	}

	/**
	 * @return Returns the renderCorresInquiryEntitySave.
	 */
	public boolean isRenderCorresInquiryEntitySave() {
		return renderCorresInquiryEntitySave;
	}

	/**
	 * @param renderCorresInquiryEntitySave
	 *            The renderCorresInquiryEntitySave to set.
	 */
	public void setRenderCorresInquiryEntitySave(
			boolean renderCorresInquiryEntitySave) {
		this.renderCorresInquiryEntitySave = renderCorresInquiryEntitySave;
	}

	/**
	 * @return Returns the renderCorrespondenceSave.
	 */
	public boolean isRenderCorrespondenceSave() {
		return renderCorrespondenceSave;
	}

	/**
	 * @param renderCorrespondenceSave
	 *            The renderCorrespondenceSave to set.
	 */
	public void setRenderCorrespondenceSave(boolean renderCorrespondenceSave) {
		this.renderCorrespondenceSave = renderCorrespondenceSave;
	}

	/**
	 * @return Returns the continueParamName.
	 */
	public String getContinueParamName() {
		return continueParamName;
	}

	/**
	 * @param continueParamName
	 *            The continueParamName to set.
	 */
	public void setContinueParamName(String continueParamName) {
		this.continueParamName = continueParamName;
	}

	/**
	 * @return Returns the continueParamValue.
	 */
	public String getContinueParamValue() {
		return continueParamValue;
	}

	/**
	 * @param continueParamValue
	 *            The continueParamValue to set.
	 */
	public void setContinueParamValue(String continueParamValue) {
		this.continueParamValue = continueParamValue;
	}

	/**
	 * @return Returns the renderBillingOrganizatione.
	 */
	public Boolean getRenderBillingOrganizatione() {
		return renderBillingOrganizatione;
	}

	/**
	 * @param renderBillingOrganizatione
	 *            The renderBillingOrganizatione to set.
	 */
	public void setRenderBillingOrganizatione(Boolean renderBillingOrganizatione) {
		this.renderBillingOrganizatione = renderBillingOrganizatione;
	}

	private List auditKeyList = new ArrayList();

	/**
	 * @return Returns the auditKeyList.
	 */
	public List getAuditKeyList() {
		return auditKeyList;
	}

	/**
	 * @param auditKeyList
	 *            The auditKeyList to set.
	 */
	public void setAuditKeyList(List auditKeyList) {
		this.auditKeyList = auditKeyList;
	}

	private boolean auditLogFlag = false;

	/**
	 * @return Returns the auditLogFlag.
	 */
	public boolean isAuditLogFlag() {
		return auditLogFlag;
	}

	/**
	 * @param auditLogFlag
	 *            The auditLogFlag to set.
	 */
	public void setAuditLogFlag(boolean auditLogFlag) {
		this.auditLogFlag = auditLogFlag;
	}

	private boolean renderHIPPCancelOutputLink;

	/**
	 * @return Returns the renderHIPPCancelOutputLink.
	 */
	public boolean isRenderHIPPCancelOutputLink() {
		return renderHIPPCancelOutputLink;
	}

	/**
	 * @param renderHIPPCancelOutputLink
	 *            The renderHIPPCancelOutputLink to set.
	 */
	public void setRenderHIPPCancelOutputLink(boolean renderHIPPCancelOutputLink) {
		this.renderHIPPCancelOutputLink = renderHIPPCancelOutputLink;
	}

	private boolean renderCORRCancelOutputLink;

	/**
	 * @return Returns the renderCORRCancelOutputLink.
	 */
	public boolean isRenderCORRCancelOutputLink() {
		return renderCORRCancelOutputLink;
	}

	/**
	 * @param renderCORRCancelOutputLink
	 *            The renderCORRCancelOutputLink to set.
	 */
	public void setRenderCORRCancelOutputLink(boolean renderCORRCancelOutputLink) {
		this.renderCORRCancelOutputLink = renderCORRCancelOutputLink;
	}

	private boolean renderCancelOutputLinkCaseEnitySrch = false;

	private boolean renderCancelOutputLinkInqCaseEnitySrch = false;

	/**
	 * @return Returns the renderCancelOutputLinkCaseEnitySrch.
	 */
	public boolean isRenderCancelOutputLinkCaseEnitySrch() {
		return renderCancelOutputLinkCaseEnitySrch;
	}

	/**
	 * @param renderCancelOutputLinkCaseEnitySrch
	 *            The renderCancelOutputLinkCaseEnitySrch to set.
	 */
	public void setRenderCancelOutputLinkCaseEnitySrch(
			boolean renderCancelOutputLinkCaseEnitySrch) {
		this.renderCancelOutputLinkCaseEnitySrch = renderCancelOutputLinkCaseEnitySrch;
	}

	/**
	 * @return Returns the renderCancelOutputLinkInqCaseEnitySrch.
	 */
	public boolean isRenderCancelOutputLinkInqCaseEnitySrch() {
		return renderCancelOutputLinkInqCaseEnitySrch;
	}

	/**
	 * @param renderCancelOutputLinkInqCaseEnitySrch
	 *            The renderCancelOutputLinkInqCaseEnitySrch to set.
	 */
	public void setRenderCancelOutputLinkInqCaseEnitySrch(
			boolean renderCancelOutputLinkInqCaseEnitySrch) {
		this.renderCancelOutputLinkInqCaseEnitySrch = renderCancelOutputLinkInqCaseEnitySrch;
	}

	private int startIndexForViewEntity = 0;

	/**
	 * @return Returns the startIndexForViewEntity.
	 */
	public int getStartIndexForViewEntity() {
		return startIndexForViewEntity;
	}

	/**
	 * @param startIndexForViewEntity
	 *            The startIndexForViewEntity to set.
	 */
	public void setStartIndexForViewEntity(int startIndexForViewEntity) {
		this.startIndexForViewEntity = startIndexForViewEntity;
	}
	
	// Modified for the defect ESPRD00743642 Starts
	
	/**
	 * This is used to hold the state of CaseSave in MaintainEntity Page.
	 */
	private boolean caseSave = false;

	public boolean isCaseSave() {
		return caseSave;
	}

	public void setCaseSave(boolean caseSave) {
		this.caseSave = caseSave;
	}
	
	/**
	 * This is used to hold the state of inquiryCaseSave in MaintainEntity Page.
	 */
	private boolean inquiryCaseSave = false;

	public boolean isInquiryCaseSave() {
		return inquiryCaseSave;
	}

	public void setInquiryCaseSave(boolean inquiryCaseSave) {
		this.inquiryCaseSave = inquiryCaseSave;
	}
	
	// Modified for the defect ESPRD00743642 Ends

	
}