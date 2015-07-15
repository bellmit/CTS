/*
 * Created on Jul 7, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterGenerationKeyword;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.cots.util.GlobalLetterConstants;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.MaintainTemplateCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LetterKeywordDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LetterTemplateDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterKeywordVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterTemplateKeywordAssociationVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;

/**
 * This is a controller class used for keywords related functionality in the
 * presentation layer.
 * 
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class MaintainKeywordsControllerBean extends
		EnterpriseBaseControllerBean {
	/** Instance of the Enterprise Logger */
// Serialization Issue (JSF 2.0 Migration) fix Starts
private static final EnterpriseLogger logger = EnterpriseLogFactory.getLogger(MaintainKeywordsControllerBean.class);
//	private EnterpriseLogger logger = EnterpriseLogFactory
//			.getLogger(MaintainKeywordsControllerBean.class);
// Serialization Issue (JSF 2.0 Migration) fix Ends

	/**
	 * Default constructor
	 */
	public MaintainKeywordsControllerBean() {
		super();
		/*
		 * MaintainKeywordsDataBean maintainKeywordsDataBean = this
		 * .getMaintainKeywordsDataBeanFromFacesContext();
		 * 
		 * if(maintainKeywordsDataBean.getKeywordList() == null ||
		 * maintainKeywordsDataBean.getKeywordList().isEmpty()) {
		 * logger.info("Retrieving Keywords List");
		 * maintainKeywordsDataBean.setKeywordList(new ArrayList());
		 * this.getAllLetterKeywords(maintainKeywordsDataBean);
		 * logger.info("Keywords List Size::" +
		 * maintainKeywordsDataBean.getKeywordList().size()); } else {
		 * logger.info("Keywords Already Retrieved");
		 * logger.info("Keywords Size ::" +
		 * maintainKeywordsDataBean.getKeywordList().size()); }
		 */

	}

	private String allKeywords;

	/**
	 * \ below variable added for restrict to hit DB from dataTable request in
	 * maintainLetterKeywords.jsp
	 */
	// private int getKeywordDetailsCount=0;
	public String getAllKeywords() {
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();
		
		List letterDataTYList = new ArrayList();
		letterDataTYList.add(new SelectItem(""));
		letterDataTYList.add(new SelectItem(GlobalLetterConstants.LETTER_DATA_TYPE_CURRENCY));
		letterDataTYList.add(new SelectItem(GlobalLetterConstants.LETTER_DATA_TYPE_DATE));
		letterDataTYList.add(new SelectItem(GlobalLetterConstants.LETTER_DATA_TYPE_OTHER));
		maintainKeywordsDataBean.setKeywordDataTypesList(letterDataTYList);

		if (maintainKeywordsDataBean.getKeywordList() == null
				|| maintainKeywordsDataBean.getKeywordList().isEmpty()) {
			logger.info("Retrieving Keywords List");
			maintainKeywordsDataBean.setKeywordList(new ArrayList());
			this.getAllLetterKeywords(maintainKeywordsDataBean);
			logger.info("Keywords List Size::"
					+ maintainKeywordsDataBean.getKeywordList().size());
		} else {
			logger.info("Keywords Already Retrieved");
			logger.info("Keywords Size ::"
					+ maintainKeywordsDataBean.getKeywordList().size());
		}

		maintainKeywordsDataBean.setDataBeanFlag(false);
		return null;
	}

	public void setAllKeywords(String allKeywords) {
		this.allKeywords = allKeywords;
	}

	/**
	 * This method is to get all keywords available in the system
	 */
	private void getAllLetterKeywords(
			MaintainKeywordsDataBean maintainKeywordsDataBean) {
		try {
			MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = new MaintainTemplateCriteriaVO();
			EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
			maintainTemplateCriteriaVO.setStartIndex(0);
			maintainTemplateCriteriaVO.setRowsPerPage(10);
			// List letterKeywordDOList = getLetterKeywords();
			enterpriseSearchResultsVO = getMaintainLetterKeywords(maintainTemplateCriteriaVO);
			List letterKeywordDOList = enterpriseSearchResultsVO
					.getSearchResults();
			List letterKeywordVOList = this
					.convertKeywordsListToVOs(letterKeywordDOList);
			maintainKeywordsDataBean.setKeywordList(letterKeywordVOList);
			enterpriseSearchResultsVO.setSearchResults(new ArrayList(
					letterKeywordDOList));
			maintainKeywordsDataBean
					.setEnterpriseSearchResultsVO(enterpriseSearchResultsVO);
			setRecordRange(enterpriseSearchResultsVO);
			setRenderingForSearchDisplay(maintainKeywordsDataBean,
					enterpriseSearchResultsVO, false);

		} catch (Exception ex) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
		}
		maintainKeywordsDataBean.setShowSuccessMessage(false);
	}

	/**
	 * Moved for mock purpose
	 * 
	 * @return
	 * @throws Exception
	 */
	protected List getLetterKeywords() throws Exception {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		return contactMaintenanceDelegate.getAllLetterKeywords();
	}

	/**
	 * This method is to convert list of keyword DO's to list of keyword VO's
	 * 
	 * @param letterKeywordDOList
	 *            , a list containing DOs
	 * @return List letterKeywordVOList, a list containing VOs
	 */
	private List convertKeywordsListToVOs(List letterKeywordDOList) {
		List letterKeywordVOList = new ArrayList();
		LetterKeywordDOConvertor letterKeywordDOConvertor = new LetterKeywordDOConvertor();
		List letterKeywordList = letterKeywordDOConvertor
				.convertLetterKeywordDOToVOList(letterKeywordDOList);
		LetterGenerationKeyword keyword = null;
		LetterKeywordVO letterKeywordVO = null;
		for (int i = 0; i < letterKeywordList.size(); i++) {
			keyword = (LetterGenerationKeyword) letterKeywordDOList.get(i);
			letterKeywordVO = (LetterKeywordVO) letterKeywordList.get(i);
			createVOAuditKeysList(keyword, letterKeywordVO);
			letterKeywordVOList.add(letterKeywordVO);
		}

		long endTime = System.currentTimeMillis();
		return letterKeywordVOList;
	}

	/**
	 * This method is to display the details of selected keyword.
	 * 
	 * @return String
	 */
	public String getKeywordDetails() throws Exception {
		// getKeywordDetailsCount++;
		// if(getKeywordDetailsCount<=1){
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();
		maintainKeywordsDataBean.setInitialCursorFocusValue("Edit");
		int rowIndex;
		FacesContext context = FacesContext.getCurrentInstance();
		Map rowMap = context.getExternalContext().getRequestParameterMap();
		if (rowMap != null && rowMap.get("rowNum") != null) {
			rowIndex = Integer.parseInt((String) rowMap.get("rowNum"));
			maintainKeywordsDataBean.setSelectedRow(rowIndex);
			maintainKeywordsDataBean.setRenderKeywordDetailsPage(true);
			maintainKeywordsDataBean.setRenderAddKeyword(false);
			maintainKeywordsDataBean.setRenderEditKeyword(true);
			maintainKeywordsDataBean.setShowSuccessMessage(false);
			maintainKeywordsDataBean.setSelectedIndex((String) rowMap
					.get("rowNum"));
			LetterKeywordVO letterKeywordVO = (LetterKeywordVO) maintainKeywordsDataBean
					.getKeywordList().get(rowIndex);
			LetterKeywordVO copyLettKywdVO = new LetterKeywordVO();
			copyLettKywdVO.setKeywordId(letterKeywordVO.getKeywordId());
			copyLettKywdVO.setKeywordLabel(letterKeywordVO.getKeywordLabel());
			copyLettKywdVO.setKeywordLongDesc(letterKeywordVO
					.getKeywordLongDesc());
			copyLettKywdVO.setKeywordShortDesc(letterKeywordVO
					.getKeywordShortDesc());
			copyLettKywdVO.setKeywordTypeCode(letterKeywordVO
					.getKeywordTypeCode());
			copyLettKywdVO.setAddedAuditUserID(letterKeywordVO
					.getAddedAuditUserID());
			copyLettKywdVO.setAddedAuditUserID(letterKeywordVO
					.getAddedAuditUserID());
			copyLettKywdVO.setAssociatedTemplatesList(letterKeywordVO
					.getAssociatedTemplatesList());
			copyLettKywdVO.setAuditTimeStamp(letterKeywordVO
					.getAuditTimeStamp());
			copyLettKywdVO.setAuditUserID(letterKeywordVO.getAuditUserID());
			copyLettKywdVO.setDbRecord(letterKeywordVO.isDbRecord());
			copyLettKywdVO.setVersionNo(letterKeywordVO.getVersionNo());
			copyLettKywdVO.setDisplaySize(letterKeywordVO.getDisplaySize());
			copyLettKywdVO.setAuditKeyList(letterKeywordVO.getAuditKeyList());
			/**added as part of CR 827474*/
				if (GlobalLetterConstants.LETTER_DTTYPE_CD_D.equals(letterKeywordVO.getKeyWordDataTyCd()))
					copyLettKywdVO.setKeyWordDataTyCd(GlobalLetterConstants.LETTER_DATA_TYPE_DATE);
				else if (GlobalLetterConstants.LETTER_DTTYPE_CD_O.equals(letterKeywordVO.getKeyWordDataTyCd()))
					copyLettKywdVO.setKeyWordDataTyCd(GlobalLetterConstants.LETTER_DATA_TYPE_OTHER);
				else if (GlobalLetterConstants.LETTER_DTTYPE_CD_C.equals(letterKeywordVO.getKeyWordDataTyCd()))
					copyLettKywdVO.setKeyWordDataTyCd(GlobalLetterConstants.LETTER_DATA_TYPE_CURRENCY);
			/**added as part of CR 827474*/
		
			maintainKeywordsDataBean.setLetterKeywordVO(copyLettKywdVO);
			UIComponent component = ContactManagementHelper
					.findComponentInRoot("LtrKywdsDetailsAuditId");
			if (component != null) {
				AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
				auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
						false);

			}
			this.populateAvailableTemplatesAsSelectItemsForKeyword(
					letterKeywordVO, maintainKeywordsDataBean);
			this
					.populateAssociatedTemplatesAsSelectItemsForKeyword(maintainKeywordsDataBean);
			populateKeywordTypeFromSystemList(maintainKeywordsDataBean);
		}
		// }
		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is save new/existing letter keyword.
	 * 
	 * @return String
	 */
	public String saveKeywordDetails() {
		logger.info("Inside saveKeywordDetails Method::::::::::::");
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();
		LetterKeywordDOConvertor letterKeywordDOConvertor = new LetterKeywordDOConvertor();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		List templatesTobeDisassociated = null;
		if (valiadateKeywordDetails(maintainKeywordsDataBean, facesContext)) {
			try {
				if (maintainKeywordsDataBean.isRenderEditKeyword()) {
					templatesTobeDisassociated = letterKeywordDOConvertor
							.identifyTemplatesToBeDisassociated(
									maintainKeywordsDataBean
											.getAssociatedTemplatesAsSelectItems(),
									maintainKeywordsDataBean
											.getLetterKeywordVO()
											.getAssociatedTemplatesList());
				}
				String auditUserId = userID();
				maintainKeywordsDataBean.getLetterKeywordVO().setAuditUserID(
						auditUserId);
				if (maintainKeywordsDataBean.isRenderAddKeyword()) {
					maintainKeywordsDataBean.getLetterKeywordVO()
							.setAddedAuditUserID(auditUserId);
				}
				LetterGenerationKeyword keyword = letterKeywordDOConvertor
						.convertLetterKeywordVOToDO(maintainKeywordsDataBean
								.getLetterKeywordVO(), maintainKeywordsDataBean
								.getAssociatedTemplatesAsSelectItems());

				if (maintainKeywordsDataBean.isRenderAddKeyword()) {
					this.createLetterKeywordExt(keyword);

				} else {
					this.updateLetterKeywordExt(keyword,
							templatesTobeDisassociated);
				}
				// this.getAllLetterKeywords(maintainKeywordsDataBean);
				MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = new MaintainTemplateCriteriaVO();
				EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
				maintainTemplateCriteriaVO
						.setStartIndex((maintainKeywordsDataBean
								.getCurrentPage() - 1) * 10);
				maintainTemplateCriteriaVO.setRowsPerPage(10);
				enterpriseSearchResultsVO = getMaintainLetterKeywords(maintainTemplateCriteriaVO);
				List letterKeywordDOList = enterpriseSearchResultsVO
						.getSearchResults();
				List letterKeywordVOList = this
						.convertKeywordsListToVOs(letterKeywordDOList);
				maintainKeywordsDataBean.setKeywordList(letterKeywordVOList);
				if (maintainKeywordsDataBean.isRenderEditKeyword()) {
					int rowIndex = Integer
							.parseInt((String) maintainKeywordsDataBean
									.getSelectedIndex());
					LetterKeywordVO letterKeywordVO = (LetterKeywordVO) maintainKeywordsDataBean
							.getKeywordList().get(rowIndex);
					LetterKeywordVO lettKywdVO = new LetterKeywordVO();
					lettKywdVO.setKeywordId(letterKeywordVO.getKeywordId());
					lettKywdVO.setKeywordLabel(letterKeywordVO
							.getKeywordLabel());
					lettKywdVO.setKeywordLongDesc(letterKeywordVO
							.getKeywordLongDesc());
					lettKywdVO.setKeywordShortDesc(letterKeywordVO
							.getKeywordShortDesc());
					lettKywdVO.setKeywordTypeCode(letterKeywordVO
							.getKeywordTypeCode());
					lettKywdVO.setAddedAuditUserID(letterKeywordVO
							.getAddedAuditUserID());
					lettKywdVO.setAddedAuditUserID(letterKeywordVO
							.getAddedAuditUserID());
					lettKywdVO.setAssociatedTemplatesList(letterKeywordVO
							.getAssociatedTemplatesList());
					lettKywdVO.setAuditTimeStamp(letterKeywordVO
							.getAuditTimeStamp());
					lettKywdVO.setAuditUserID(letterKeywordVO.getAuditUserID());
					lettKywdVO.setDbRecord(letterKeywordVO.isDbRecord());
					lettKywdVO.setVersionNo(letterKeywordVO.getVersionNo());
					lettKywdVO.setDisplaySize(letterKeywordVO.getDisplaySize());
					lettKywdVO.setAuditKeyList(letterKeywordVO
							.getAuditKeyList());
					/**added as part of CR 827474*/
					if (GlobalLetterConstants.LETTER_DTTYPE_CD_D.equals(letterKeywordVO.getKeyWordDataTyCd()))
						lettKywdVO.setKeyWordDataTyCd(GlobalLetterConstants.LETTER_DATA_TYPE_DATE);
					else if (GlobalLetterConstants.LETTER_DTTYPE_CD_O.equals(letterKeywordVO.getKeyWordDataTyCd()))
						lettKywdVO.setKeyWordDataTyCd(GlobalLetterConstants.LETTER_DATA_TYPE_OTHER);
					else if (GlobalLetterConstants.LETTER_DTTYPE_CD_C.equals(letterKeywordVO.getKeyWordDataTyCd()))
						lettKywdVO.setKeyWordDataTyCd(GlobalLetterConstants.LETTER_DATA_TYPE_CURRENCY);
				/**added as part of CR 827474*/
					maintainKeywordsDataBean.setLetterKeywordVO(lettKywdVO);
					this.populateAvailableTemplatesAsSelectItemsForKeyword(
							letterKeywordVO, maintainKeywordsDataBean);
					this
							.populateAssociatedTemplatesAsSelectItemsForKeyword(maintainKeywordsDataBean);
				}
				maintainKeywordsDataBean.setShowSuccessMessage(true);
				
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
				setErrorMessage(EnterpriseMessageConstants.ERR_SW_SAVE_FAILURE,
						new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

			}

		}
		logger.info("End of saveKeywordDetails Method::::::::::::");
		return GlobalLetterConstants.RENDER_SUCCESS;

	}

	/**
	 * External class call for creating new keyword
	 * 
	 * @param keyword
	 * @throws Exception
	 */
	protected void createLetterKeywordExt(LetterGenerationKeyword keyword)
			throws Exception {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		contactMaintenanceDelegate.createLetterKeyword(keyword);
	}

	/**
	 * External service call for updating keyword
	 * 
	 * @param keyword
	 * @param templatesTobeDisassociated
	 * @throws Exception
	 */
	protected void updateLetterKeywordExt(LetterGenerationKeyword keyword,
			List templatesTobeDisassociated) throws Exception {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		contactMaintenanceDelegate.updateLetterKeyword(keyword,
				templatesTobeDisassociated);
	}

	/**
	 * This method is to move selected templates from available templates
	 * section to associated templates section
	 * 
	 * @return String
	 */
	public String associateSelectedTemplatesWithKeyword() {
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();

		this
				.processAssociateSelectedTemplatesWithKeyword(maintainKeywordsDataBean);
		return GlobalLetterConstants.RENDER_SUCCESS;

	}

	/**
	 * This method is to move selected templates from available templates
	 * section to associated templates section
	 * 
	 * @param maintainKeywordsDataBean
	 */
	private void processAssociateSelectedTemplatesWithKeyword(
			MaintainKeywordsDataBean maintainKeywordsDataBean) {
		List templatesToBeAssociatedAsList = maintainKeywordsDataBean
				.getSelectedTemplatesFromAvailable();
		Iterator templatesToBeAssociatedAsListIter = templatesToBeAssociatedAsList
				.iterator();
		SelectItem temp = null;
		String value = null;
		while (templatesToBeAssociatedAsListIter.hasNext()) {
			value = (String) templatesToBeAssociatedAsListIter.next();
			temp = this.getSelectItemForValue(value, maintainKeywordsDataBean
					.getAvailableTemplatesAsSelectItems());
			maintainKeywordsDataBean.getAssociatedTemplatesAsSelectItems().add(
					temp);
			maintainKeywordsDataBean.getAvailableTemplatesAsSelectItems()
					.remove(temp);
		}
	}

	/**
	 * Get select item based on value
	 * 
	 * @param value
	 *            : a string
	 * @param selectItemList
	 *            : list containing selectItems
	 * @return SelectItem : object matching to selectItem 'value'
	 */
	private SelectItem getSelectItemForValue(String value, List selectItemList) {
		Iterator iter = selectItemList.iterator();
		SelectItem tempItem = null;
		boolean matchFound = false;
		while (iter.hasNext()) {
			tempItem = (SelectItem) iter.next();
			if (tempItem.getValue().equals(value)) {
				matchFound = true;
				break;
			}
		}

		if (!matchFound) {
			tempItem = null;
		}
		return tempItem;
	}

	/**
	 * This method is to move selected templates from associated templates
	 * section to available templates section
	 * 
	 * @return String
	 */
	public String disAssociateSelectedTemplatesWithKeyword() {
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();

		this
				.processDisAssociateSelectedTemplatesWithKeyword(maintainKeywordsDataBean);

		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * Add the selected templates to available templates list for the keyword
	 * and remove from the associated templates list
	 * 
	 * @param maintainKeywordsDataBean
	 */
	private void processDisAssociateSelectedTemplatesWithKeyword(
			MaintainKeywordsDataBean maintainKeywordsDataBean) {
		List templatesToBeDisassociatedAsList = maintainKeywordsDataBean
				.getSelectedTemplatesFromAssociated();

		Iterator templatesToBeDisassociatedAsListIter = templatesToBeDisassociatedAsList
				.iterator();
		String value = null;
		SelectItem temp = null;
		while (templatesToBeDisassociatedAsListIter.hasNext()) {
			value = (String) templatesToBeDisassociatedAsListIter.next();
			temp = this.getSelectItemForValue(value, maintainKeywordsDataBean
					.getAssociatedTemplatesAsSelectItems());
			maintainKeywordsDataBean.getAvailableTemplatesAsSelectItems().add(
					temp);
			maintainKeywordsDataBean.getAssociatedTemplatesAsSelectItems()
					.remove(temp);
		}
	}

	/**
	 * This method is to display add keyword section
	 * 
	 * @return String
	 */
	public String displayAddKeywordSection() throws Exception {
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();
		maintainKeywordsDataBean.setInitialCursorFocusValue("Add");
		this.processDisplayAddKeywordSection(maintainKeywordsDataBean);
		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * 
	 * @param maintainKeywordsDataBean
	 * @throws Exception
	 */
	private void processDisplayAddKeywordSection(
			MaintainKeywordsDataBean maintainKeywordsDataBean) throws Exception {
		maintainKeywordsDataBean.setRenderAddKeyword(true);
		maintainKeywordsDataBean.setRenderEditKeyword(false);
		maintainKeywordsDataBean.setRenderKeywordDetailsPage(true);
		maintainKeywordsDataBean.setLetterKeywordVO(new LetterKeywordVO());
		maintainKeywordsDataBean
				.setAssociatedTemplatesAsSelectItems(new ArrayList());
		populateKeywordTypeFromSystemList(maintainKeywordsDataBean);
		populateAvailableTemplatesAsSelectItemsForKeyword(
				maintainKeywordsDataBean.getLetterKeywordVO(),
				maintainKeywordsDataBean);
	}

	/**
	 * Method to populate available templates as SelectItem objects
	 * 
	 * @param letterKeywordVO
	 *            : an object of type LetterKeywordVO
	 */
	private void populateAvailableTemplatesAsSelectItemsForKeyword(
			LetterKeywordVO letterKeywordVO,
			MaintainKeywordsDataBean maintainKeywordsDataBean) {
		try {
			List availableTemplates = null;
			if (letterKeywordVO.getKeywordId() == null
					|| letterKeywordVO.getKeywordId().equals("")) {
				availableTemplates = getAllTemplatesExt(letterKeywordVO
						.getKeywordId());
			} else {
				availableTemplates = getAvailableTemplatesExt(letterKeywordVO
						.getKeywordId());
			}
			List availableTemplatesAsSelectItems = new ArrayList();
			LetterTemplate letterTemplate = null;
			SelectItem item = null;
			Iterator iter = availableTemplates.iterator();
			while (iter.hasNext()) {
				letterTemplate = (LetterTemplate) iter.next();

				item = getLetterTemplateAsSelectItem(letterTemplate);
				availableTemplatesAsSelectItems.add(item);
			}
			maintainKeywordsDataBean
					.setAvailableTemplatesAsSelectItems(availableTemplatesAsSelectItems);
		} catch (Exception ex) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

		}
	}

	/**
	 * 
	 * @param keywordId
	 * @return
	 */
	protected List getAvailableTemplatesExt(String keywordId) throws Exception {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		return contactMaintenanceDelegate.getAvailableTemplates(keywordId,
				Boolean.FALSE);
	}

	/**
	 * 
	 * @param keywordId
	 * @return
	 */
	protected List getAllTemplatesExt(String keywordId) throws Exception {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		return contactMaintenanceDelegate.getAllLetterTemplates(false);
	}

	/**
	 * @param letterKeywordVO
	 */
	private void populateAssociatedTemplatesAsSelectItemsForKeyword(
			MaintainKeywordsDataBean maintainKeywordsDataBean) {
		try {
			List asociatedTemplates = maintainKeywordsDataBean
					.getLetterKeywordVO().getAssociatedTemplatesList();
			List asociatedTemplatesAsSelectItems = new ArrayList();
			LetterTemplateKeywordAssociationVO associationVO = null;
			SelectItem item = null;
			Iterator iter = asociatedTemplates.iterator();
			while (iter.hasNext()) {
				associationVO = (LetterTemplateKeywordAssociationVO) iter
						.next();

				item = new SelectItem();
				item.setLabel(associationVO.getLetterTemplateName());
				item.setValue(associationVO.getLetterTemplateId());
				asociatedTemplatesAsSelectItems.add(item);
			}
			maintainKeywordsDataBean
					.setAssociatedTemplatesAsSelectItems(asociatedTemplatesAsSelectItems);
		} catch (Exception ex) {
			setErrorMessage(
					EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
					new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

		}
	}

	/**
	 * Method to convert letter template to SelectItem
	 * 
	 * @param letterTemplate
	 *            : an object of type LetterTemplate
	 * @return SelectItem : lettertemplate converted as SelectItem
	 */
	private SelectItem getLetterTemplateAsSelectItem(
			LetterTemplate letterTemplate) {
		SelectItem item = new SelectItem();
		String name = letterTemplate.getName();
		if (name == null) {
			name = "<No Name>";
		}
		item.setLabel(name);
		item.setValue(letterTemplate.getLetterTemplateKeyData());
		return item;
	}

	/**
	 * Method to populate keywordtype from system list
	 */
	private void populateKeywordTypeFromSystemList(
			MaintainKeywordsDataBean maintainKeywordsDataBean) throws Exception {
		if (maintainKeywordsDataBean.getKeywordTypesList() != null
				&& maintainKeywordsDataBean.getKeywordTypesList().size() > 0) {
			return;
		}
		InputCriteria keywordTypeCriteria = new InputCriteria();
		keywordTypeCriteria
				.setFunctionalArea(GlobalLetterConstants.FUNC_AREA_GENERAL);
		keywordTypeCriteria
				.setElementName(GlobalLetterConstants.ELEMENT_NM_KYWD_TY_CD);
		keywordTypeCriteria.setListNumber(new Long(
				GlobalLetterConstants.KYWD_TY_CD_LIST_NUM));
		List criteriaList = new ArrayList();
		criteriaList.add(keywordTypeCriteria);
		ReferenceDataSearchVO referenceDataSearchVO = new ReferenceDataSearchVO();
		referenceDataSearchVO.setInputList(criteriaList);
		ReferenceDataListVO referenceDataListVO = getReferenceDataExt(referenceDataSearchVO);
		Map referenceMap = referenceDataListVO.getResponseMap();
		List keywordTypeList = getReferenceListAsSelectItems(referenceMap,
				GlobalLetterConstants.FUNC_AREA_GENERAL,
				GlobalLetterConstants.ELEMENT_NM_KYWD_TY_CD);
		maintainKeywordsDataBean.setKeywordTypesList(keywordTypeList);
	}

	/**
	 * 
	 * @param referenceDataSearchVO
	 * @return
	 * @throws Exception
	 */
	protected ReferenceDataListVO getReferenceDataExt(
			ReferenceDataSearchVO referenceDataSearchVO) throws Exception {
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = referenceServiceDelegate
				.getReferenceData(referenceDataSearchVO);
		return referenceDataListVO;
	}

	/**
	 * This method is used to get the reference service list from the reference
	 * map based on the fuctional area constant and elementName
	 * 
	 * @param referenceMap
	 *            : Map of reference list.
	 * @param functionalAreaConstant
	 *            : Reference Service Functional Area Constants to get.
	 * @param elementName
	 *            : Reference Service Element to get.
	 * @return tempRevenueTypeList : Temporary Reference Service List.
	 */
	private List getReferenceListAsSelectItems(Map referenceMap,
			String functionalAreaConstant, String elementName) {
		List referenceListAsSelectItems = new ArrayList();
		List referenceList = (List) referenceMap.get(functionalAreaConstant
				+ ProgramConstants.HASH_SEPARATOR
				+ GlobalLetterConstants.KYWD_TY_CD_LIST_NUM);
		ReferenceServiceVO refVo = null;
		if (referenceList != null) {
			int size = referenceList.size();
			for (int i = 0; i < size; i++) {
				refVo = (ReferenceServiceVO) referenceList.get(i);
				referenceListAsSelectItems.add(new SelectItem(refVo
						.getValidValueCode(), refVo.getShortDescription()));
			}
		}

		return referenceListAsSelectItems;
	}

	/**
	 * Sort the keywords based on the column
	 * 
	 * @param event
	 *            : an object of type ActionEvent
	 */
	public void sortColumn(ActionEvent event) {
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get(GlobalLetterConstants.COLUMN_NAME);
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get(GlobalLetterConstants.SORT_ORDER);
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();
		maintainKeywordsDataBean.setImageRender(event.getComponent().getId());
		MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = maintainKeywordsDataBean.getMaintainTemplateCriteriaVO();
		
		logger.debug("sortColumn = " + sortColumn + " $$ sortOrder = " + sortOrder);
		if (GlobalLetterConstants.SORT_COL_KEYWORD_ID.equalsIgnoreCase(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO.setSortColumn(GlobalLetterConstants.SORT_COL_KEYWORD_ID);
			maintainTemplateCriteriaVO.setAscending(true);
		}
		if (GlobalLetterConstants.SORT_COL_KEYWORD_ID.equalsIgnoreCase(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO.setSortColumn(GlobalLetterConstants.SORT_COL_KEYWORD_ID);
			maintainTemplateCriteriaVO.setAscending(false);
		}
		if (GlobalLetterConstants.SORT_COL_KEYWORD_LABEL.equalsIgnoreCase(sortColumn)
				&& ContactManagementConstants.SORT_ASC.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO.setSortColumn(GlobalLetterConstants.SORT_COL_KEYWORD_LABEL);
			maintainTemplateCriteriaVO.setAscending(true);
		}
		if (GlobalLetterConstants.SORT_COL_KEYWORD_LABEL.equalsIgnoreCase(sortColumn)
				&& ContactManagementConstants.SORT_DESC.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO.setSortColumn(GlobalLetterConstants.SORT_COL_KEYWORD_LABEL);
			maintainTemplateCriteriaVO.setAscending(false);
		}
		/*List keywordsList = maintainKeywordsDataBean.getKeywordList();

		sortBy(sortColumn, sortOrder, keywordsList);

		maintainKeywordsDataBean.setKeywordList(keywordsList);*/		
		
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		List letterKeywordsList = null;
		try {
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			maintainTemplateCriteriaVO.setStartIndex(0);
			maintainTemplateCriteriaVO.setRowsPerPage(10);
			enterpriseSearchResultsVO = contactMaintenanceDelegate
					.getAllMaintainLetterKeywords(maintainTemplateCriteriaVO);
			if (enterpriseSearchResultsVO != null) {
				letterKeywordsList = enterpriseSearchResultsVO.getSearchResults();
			}
			// enterpriseSearchResultsVO.setSearchResults((ArrayList)letterTemplateList);
			LetterKeywordDOConvertor letterKeywordDOConvertor = null;
			LetterGenerationKeyword letterKeyword = null;
			if (letterKeywordsList != null) {

				LetterKeywordVO letterKeywordVO = null;
				letterKeywordDOConvertor = new LetterKeywordDOConvertor();
				List letterKeywordVOList = letterKeywordDOConvertor
						.convertLetterKeywordDOToVOList(letterKeywordsList);

				if (letterKeywordVOList != null) {
					logger.debug("letterKeywordVOList size::: " + letterKeywordVOList.size());
				}
				for (int i = 0; i < letterKeywordVOList.size(); i++) {
					letterKeyword = (LetterGenerationKeyword) letterKeywordsList.get(i);
					letterKeywordVO = (LetterKeywordVO) letterKeywordVOList
							.get(i);
					createVOAuditKeysList(letterKeyword, letterKeywordVO);
				}
				enterpriseSearchResultsVO.setSearchResults(new ArrayList(
						letterKeywordVOList));
				maintainKeywordsDataBean.setKeywordList(letterKeywordVOList);
				setRenderingForSearchDisplay(maintainKeywordsDataBean,
						enterpriseSearchResultsVO, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in sortColumn --> " + e.getMessage());
		}
		logger.debug("END of getAllSortedTemplates() Method $$$$$$$$$$$$$$$$$");

	}

	/**
	 * Sort by column
	 * 
	 * @param sortColumn
	 *            : sort column
	 * @param sortOrder
	 *            : sort order
	 * @param keywordsList
	 *            : keyword list to sort
	 * @return List : sorted keywords based on column and order
	 */
	private List sortBy(String sortColumn, String sortOrder, List keywordsList) {
		List sortedKeywordList = new ArrayList();
		int keywordListSize = keywordsList.size();
		LetterKeywordVO temp = null;
		for (int i = 1; i < keywordListSize; i++) {

			int j;
			LetterKeywordVO val = (LetterKeywordVO) keywordsList.get(i);
			for (j = i - 1; j > -1; j--) {
				temp = (LetterKeywordVO) keywordsList.get(j);
				if (GlobalLetterConstants.SORT_COL_KEYWORD_ID
						.equals(sortColumn)) {
					if (GlobalLetterConstants.SORT_ASC.equals(sortOrder)) {
						if (temp.compareByKeywordId(val) <= 0) {
							break;
						}
					} else {
						if (!(temp.compareByKeywordId(val) <= 0)) {
							break;
						}
					}
				} else if (GlobalLetterConstants.SORT_COL_KEYWORD_LABEL
						.equals(sortColumn)) {
					if (GlobalLetterConstants.SORT_ASC.equals(sortOrder)) {
						if (temp.compareByLabel(val) <= 0) {
							break;
						}
					} else {
						if (!(temp.compareByLabel(val) <= 0)) {
							break;
						}
					}
				}
				keywordsList.set(j + 1, temp);
			}
			keywordsList.set(j + 1, val);
		}
		return sortedKeywordList;
	}

	/**
	 * Method for cancel
	 * 
	 * @return String
	 */
	public String cancelPage() {
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();
		maintainKeywordsDataBean.setRenderKeywordDetailsPage(false);
		maintainKeywordsDataBean.setRenderAddKeyword(false);
		maintainKeywordsDataBean.setRenderEditKeyword(false);
		maintainKeywordsDataBean.setShowSuccessMessage(false);
		maintainKeywordsDataBean.setSelectedTemplatesFromAssociated(null);
		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * Method for reset
	 * 
	 * @return String
	 */
	public String resetPage() throws Exception {
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();
		maintainKeywordsDataBean.setRenderKeywordDetailsPage(true);
		if (maintainKeywordsDataBean.isRenderAddKeyword()) {
			displayAddKeywordSection();
		}
		if (maintainKeywordsDataBean.isRenderEditKeyword()) {
			int rowIndex = maintainKeywordsDataBean.getSelectedRow();
			LetterKeywordVO letterKeywordVO = (LetterKeywordVO) maintainKeywordsDataBean
					.getKeywordList().get(rowIndex);
			LetterKeywordVO copyEditLettKywdVO = new LetterKeywordVO();
			copyEditLettKywdVO.setKeywordId(letterKeywordVO.getKeywordId());
			copyEditLettKywdVO.setKeywordLabel(letterKeywordVO
					.getKeywordLabel());
			copyEditLettKywdVO.setKeywordLongDesc(letterKeywordVO
					.getKeywordLongDesc());
			copyEditLettKywdVO.setKeywordShortDesc(letterKeywordVO
					.getKeywordShortDesc());
			copyEditLettKywdVO.setKeywordTypeCode(letterKeywordVO
					.getKeywordTypeCode());
			copyEditLettKywdVO.setAddedAuditUserID(letterKeywordVO
					.getAddedAuditUserID());
			copyEditLettKywdVO.setAddedAuditUserID(letterKeywordVO
					.getAddedAuditUserID());
			copyEditLettKywdVO.setAssociatedTemplatesList(letterKeywordVO
					.getAssociatedTemplatesList());
			copyEditLettKywdVO.setAuditTimeStamp(letterKeywordVO
					.getAuditTimeStamp());
			copyEditLettKywdVO.setAuditUserID(letterKeywordVO.getAuditUserID());
			copyEditLettKywdVO.setDbRecord(letterKeywordVO.isDbRecord());
			copyEditLettKywdVO.setVersionNo(letterKeywordVO.getVersionNo());
			copyEditLettKywdVO.setDisplaySize(letterKeywordVO.getDisplaySize());
			maintainKeywordsDataBean.setLetterKeywordVO(copyEditLettKywdVO);
			this.populateAvailableTemplatesAsSelectItemsForKeyword(
					letterKeywordVO, maintainKeywordsDataBean);
			this
					.populateAssociatedTemplatesAsSelectItemsForKeyword(maintainKeywordsDataBean);
			populateKeywordTypeFromSystemList(maintainKeywordsDataBean);

		}
		maintainKeywordsDataBean.setShowSuccessMessage(false);
		return GlobalLetterConstants.RENDER_SUCCESS;
	}

	/**
	 * This method is to get "MaintainKeywordsDataBean" from faces context.
	 * 
	 * @return MaintainKeywordsDataBean the data bean
	 */
	protected MaintainKeywordsDataBean getMaintainKeywordsDataBeanFromFacesContext() {
		FacesContext fc = FacesContext.getCurrentInstance();
		MaintainKeywordsDataBean maintainKeywordsDataBean = ((MaintainKeywordsDataBean) fc
				.getApplication()
				.createValueBinding(
						GlobalLetterConstants.BINDING_BEGIN_SEPARATOR
								+ GlobalLetterConstants.LETTER_KEYWORD_DATA_BEAN
								+ GlobalLetterConstants.BINDING_END_SEPARATOR)
				.getValue(fc));
		return maintainKeywordsDataBean;

	}

	/**
	 * This method used for setting error display messages.
	 * 
	 * @param errorName
	 *            : String errorName.
	 * @param arguments
	 *            : Array of Object. Parameters to be passed to the message
	 * @param messageBundle
	 *            : String messageBundle.
	 * @param componentId
	 *            : String componentId.
	 */
	protected void setErrorMessage(String errorName, Object[] arguments,
			String messageBundle, String componentId) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		Locale locale = root.getLocale();
		String clientId = null;

		facesContext.getApplication().setMessageBundle(messageBundle);
		String errorMsg = MessageUtil.format(messageBundle, errorName,
				arguments, locale);
		FacesMessage fc = new FacesMessage();
		fc.setDetail(errorMsg);

		if (componentId != null) {
			UIComponent uiComponent = findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);
		}

		facesContext.addMessage(clientId, fc);
	}

	/**
	 * This operation is used to find the component in root by passing id.
	 * 
	 * @param id
	 *            : String object.
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
	 * @param base
	 *            : View root component of the jsp.
	 * @param id
	 *            : Id of the component from jsp.
	 * @return UIComponent object.
	 */
	private UIComponent findComponent(UIComponent base, String id) {
		UIComponent component = null;
		UIComponent result = null;
		if (id.equals(base.getId())) {
			result = base;
		} else {
			Iterator cmpIterator = base.getFacetsAndChildren();
			while (cmpIterator.hasNext() && (result == null)) {
				component = (UIComponent) cmpIterator.next();
				if (id.equals(component.getId())) {
					result = component;
					break;
				}
				result = findComponent(component, id);
				if (result != null) {
					break;
				}
			}
		}

		return result;
	}

	/**
	 * below methos is added for validating MaintainKeywords UI
	 */
	public boolean valiadateKeywordDetails(
			MaintainKeywordsDataBean maintainKeywordsDataBean,
			FacesContext facesContext) {

		LetterKeywordVO updateKeywordVO = maintainKeywordsDataBean
				.getLetterKeywordVO();
		boolean validationSuccess = true;
		if (updateKeywordVO.getKeywordTypeCode().equalsIgnoreCase(
				GlobalLetterConstants.LTR_KYWD_TY_CD_VAL)
				&& (updateKeywordVO.getKeywordLabel() == null || updateKeywordVO
						.getKeywordLabel().equals(""))) {
			setErrorMessages(
					GlobalLetterConstants.LABEL_REQ_FOR_LTR_KYWD_TY_CD_ERROR,
					null, GlobalLetterConstants.LTR_KYWD_MESSAGES_BUNDLE,
					facesContext);
			validationSuccess = false;
		}
		if (updateKeywordVO.getKeywordTypeCode().equalsIgnoreCase(
				GlobalLetterConstants.LTR_KYWD_TY_CD_VAL)
				&& (updateKeywordVO.getDisplaySize() == null || updateKeywordVO
						.getDisplaySize().equals(""))) {
			setErrorMessages(GlobalLetterConstants.LTR_REQ_FIELD,
					new Object[] { GlobalLetterConstants.INPUTLENGTH },
					GlobalLetterConstants.LTR_KYWD_MESSAGES_BUNDLE,
					facesContext);
			validationSuccess = false;
		} else if (updateKeywordVO.getKeywordTypeCode().equalsIgnoreCase(
				GlobalLetterConstants.LTR_KYWD_TY_CD_VAL)
				&& (EnterpriseCommonValidator.validateAlpha(updateKeywordVO
						.getDisplaySize()) || EnterpriseCommonValidator
						.validateAlphaNumeric(updateKeywordVO.getDisplaySize()))) {
			if (EnterpriseCommonValidator.validateNumeric(updateKeywordVO
					.getDisplaySize())) {
				validationSuccess = true;
			} else {
				setErrorMessages(
						GlobalLetterConstants.INPUTLENGTH_REQ_NUMERIC_ERROR,
						new Object[] { GlobalLetterConstants.INPUTLENGTH },
						GlobalLetterConstants.LTR_KYWD_MESSAGES_BUNDLE,
						facesContext);
				validationSuccess = false;
			}
		}
		return validationSuccess;
	}

	/**
	 * This method used for setting error display messages.
	 * 
	 * @param errorName
	 *            : String errorName.
	 * @param arguments
	 *            : Array of Object. Parameters to be passed to the message
	 * @param messageBundle
	 *            : String messageBundle.
	 */
	private void setErrorMessages(String errorName, Object[] arguments,
			String messageBundle, FacesContext facesContext) {
		UIViewRoot root = facesContext.getViewRoot();
		String clientId = null;
		Locale locale = root.getLocale();
		facesContext.getApplication().setMessageBundle(messageBundle);
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale,
				facesContext.getClass().getClassLoader());
		String errorMsg = MessageFormat.format(bundle.getString(errorName),
				arguments);
		FacesMessage fc = new FacesMessage();
		fc.setDetail(errorMsg);
		facesContext.addMessage(clientId, fc);
	}

	final String LETTER_KEYWORDS_PAGE = "/Enterprise/CtMgmtMaintKeywords";

	private String pageAccessPermission = GlobalLetterConstants.EMPTY_STRING;

	/**
	 * @return Returns the pageAccessPermission.
	 */

	// Security
	public String getPageAccessPermission() {
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		String feildAccessUpdate = null;
		RenderRequest request = null;
		String currentPage = null;
		  try {
			request = getRequest();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String userID = userID();
			Map map = (Map) facesContext.getApplication().createValueBinding(
					"#{requestScope}").getValue(facesContext);
			if (map.get("feildAccessUpdate") != null) {
				feildAccessUpdate = map.get("feildAccessUpdate").toString();
				map.put("feildAccessUpdate", map.get("feildAccessUpdate"));
			}

			if (feildAccessUpdate == null
					|| (feildAccessUpdate.equalsIgnoreCase(""))) {
				feildAccessUpdate = fieldAccessControlImpl
						.getFiledAccessPermission(LETTER_KEYWORDS_PAGE, userID);
				map.put("feildAccessUpdate", feildAccessUpdate);

			}

		} catch (SecurityFLSServiceException e) {
			e.printStackTrace();
		} finally {
			if (feildAccessUpdate != null
					&& !(feildAccessUpdate.equalsIgnoreCase(""))) {
				MaintainKeywordsDataBean maintainKeywordsDataBean = this
						.getMaintainKeywordsDataBeanFromFacesContext();
				if (feildAccessUpdate.equalsIgnoreCase("r")) {
					maintainKeywordsDataBean.setUpdateLtrKeywords(true);
					if (maintainKeywordsDataBean.isRenderEditKeyword()) {
						request.setAttribute("displayMode", feildAccessUpdate);
					}
				}
			}

		}  
		return pageAccessPermission;
	}

	/**
	 * @param pageAccessPermission
	 *            The pageAccessPermission to set.
	 */
	public void setPageAccessPermission(String pageAccessPermission) {
		this.pageAccessPermission = pageAccessPermission;
	}

	private String userID() {
		String userID = null;
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			PortletRequest portletRequest = (PortletRequest) facesContext
					.getExternalContext().getRequest();
			userID = getLoggedInUserID(portletRequest);
		} catch (Exception e1) {
			e1.getCause();
			e1.getMessage();
		}

		return userID;

	}

	private RenderRequest getRequest() {

		return (RenderRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	/**
	 * @param enterpriseBaseDomain
	 * @param auditaleEnterpriseBaseVO
	 *            author: Venkat J
	 */
	private void createVOAuditKeysList(
			EnterpriseBaseDomain enterpriseBaseDomain,
			AuditaleEnterpriseBaseVO auditaleEnterpriseBaseVO) {
		List fullAuditList = new ArrayList();

		if (auditaleEnterpriseBaseVO.getAuditKeyList() != null) {
			fullAuditList = auditaleEnterpriseBaseVO.getAuditKeyList();
		}
		LineItemAuditsDelegate auditDelegate;
		try {
			auditDelegate = new LineItemAuditsDelegate();

			List resultList = auditDelegate
					.getAuditKeyList(enterpriseBaseDomain);

			if (resultList != null) {
				fullAuditList.addAll(resultList);
				auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);
			}
		} catch (LineItemAuditsBusinessException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public String doAuditKeyListOperation() {
		MaintainKeywordsDataBean maintainKywdDataBean = getMaintainKeywordsDataBeanFromFacesContext();
		List keywordList = maintainKywdDataBean.getKeywordList();

		try {
			Iterator auditKeyIt = null;
			List editableKywd = new ArrayList();
			if (keywordList != null && !(keywordList.isEmpty())) {
				if (keywordList != null && keywordList.size() > 0) {
					auditKeyIt = keywordList.iterator();

					editableKywd.add(createAuditableFeild("Keyword Type",
							"letterKeyWordTypeCode"));
					editableKywd
							.add(createAuditableFeild("Label", "labelText"));
					editableKywd.add(createAuditableFeild("Short Description",
							"shortDescription"));
					editableKywd.add(createAuditableFeild("Long Description",
							"longDescription"));
					while (auditKeyIt.hasNext()) {
						LetterKeywordVO letterKeywordVO = (LetterKeywordVO) auditKeyIt
								.next();
						if (letterKeywordVO.getAuditKeyList() != null
								&& !(letterKeywordVO.getAuditKeyList()
										.isEmpty())) {
							AuditDataFilter.filterAuditKeys(editableKywd,
									letterKeywordVO);
						}
						String kywdindex = maintainKywdDataBean
								.getSelectedIndex();
						if (kywdindex != null && kywdindex.trim().length() > 0) {
							maintainKywdDataBean
									.setLetterKeywordVO((LetterKeywordVO) keywordList
											.get(Integer.parseInt(kywdindex)));
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		maintainKywdDataBean.setAuditLogFlag(true);
		return "";
	}

	private AuditableField createAuditableFeild(String feildName,
			String domainAttributeName) {
		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);
		return auditableField;

	}

	public EnterpriseSearchResultsVO getMaintainLetterKeywords(
			MaintainTemplateCriteriaVO maintainTemplateCriteriaVO)
			throws Exception {
		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		return contactMaintenanceDelegate
				.getMaintainLetterKeywords(maintainTemplateCriteriaVO);

	}

	public void setRecordRange(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {

		int listSize;
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();
		maintainKeywordsDataBean.setCount((int) totalRecordCount);

		int noOfPages = maintainKeywordsDataBean.getCount()
				/ maintainKeywordsDataBean.getItemsPerPage();

		int modNofPages = maintainKeywordsDataBean.getCount()
				% maintainKeywordsDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}

		maintainKeywordsDataBean.setCurrentPage(1);
		maintainKeywordsDataBean.setNumberOfPages(noOfPages);
		maintainKeywordsDataBean.setStartRecord(1);
		maintainKeywordsDataBean.setEndRecord(10);
		listSize = maintainKeywordsDataBean.getCount();

		if (listSize <= maintainKeywordsDataBean.getItemsPerPage()) {
			maintainKeywordsDataBean.setEndRecord(listSize);
		}

		if (maintainKeywordsDataBean.getCount() <= maintainKeywordsDataBean
				.getItemsPerPage()) {
			maintainKeywordsDataBean.setShowNext(false);
		} else {
			maintainKeywordsDataBean.setShowNext(true);
		}
		maintainKeywordsDataBean.setShowPrevious(false);
	}

	public static void setRenderingForSearchDisplay(
			MaintainKeywordsDataBean maintainKeywordsDataBean,
			EnterpriseSearchResultsVO enterpriseSearchResultsVO,
			boolean isFromPagination) {
		if (!isFromPagination) {
			maintainKeywordsDataBean.setCount((int) enterpriseSearchResultsVO
					.getRecordCount());
			// maintainTemplateDataBean.setCount((int)
			// letterTemplateList.size());
			int noOfPages = maintainKeywordsDataBean.getCount() / 10;
			int modNofPages = maintainKeywordsDataBean.getCount() % 10;
			if (modNofPages != 0) {
				noOfPages = noOfPages + 1;
			}
			maintainKeywordsDataBean.setFirstPage(1);
			maintainKeywordsDataBean.setCurrentPage(1);
			maintainKeywordsDataBean.setNumberOfPages(noOfPages);
			maintainKeywordsDataBean.setStartRecord(1);
		} else {
			// maintainTemplateDataBean.setStartRecord(((maintainTemplateDataBean.getCurrentPage()-1)*GlobalLetterConstants.ITEMS_PER_PAGE)+1);
			maintainKeywordsDataBean.setStartRecord(((maintainKeywordsDataBean
					.getCurrentPage() - 1) * 10) + 1);
		}
		if (maintainKeywordsDataBean.getCurrentPage() < maintainKeywordsDataBean
				.getNumberOfPages()) {
			// maintainTemplateDataBean.setEndRecord(maintainTemplateDataBean.getCurrentPage()*GlobalLetterConstants.ITEMS_PER_PAGE);
			maintainKeywordsDataBean.setEndRecord(maintainKeywordsDataBean
					.getCurrentPage() * 10);
		} else {
			maintainKeywordsDataBean.setEndRecord(maintainKeywordsDataBean
					.getCount());
		}
		maintainKeywordsDataBean.setSearchResultShow(true);
		if (maintainKeywordsDataBean.getNumberOfPages() != 1) {
			maintainKeywordsDataBean.setRenderFirstPage(true);
			maintainKeywordsDataBean.setRenderFirstPagePlusOne(true);
			maintainKeywordsDataBean.setShowNext(false);
			maintainKeywordsDataBean.setShowPrevious(false);
			maintainKeywordsDataBean.setRenderFirstPagePlusTwo(false);
			if (maintainKeywordsDataBean.getCurrentPage() != maintainKeywordsDataBean
					.getNumberOfPages()) {
				maintainKeywordsDataBean.setShowNext(true);
			}
			if (maintainKeywordsDataBean.getCurrentPage() != 1) {
				maintainKeywordsDataBean.setShowPrevious(true);
			}
			if (maintainKeywordsDataBean.getCurrentPage() <= maintainKeywordsDataBean
					.getNumberOfPages()
					&& (maintainKeywordsDataBean.getFirstPage() + 2) <= maintainKeywordsDataBean
							.getNumberOfPages()) {
				maintainKeywordsDataBean.setRenderFirstPagePlusTwo(true);
			}
		} else {
			maintainKeywordsDataBean.setRenderFirstPage(false);
			maintainKeywordsDataBean.setRenderFirstPagePlusOne(false);
			maintainKeywordsDataBean.setRenderFirstPagePlusTwo(false);
			maintainKeywordsDataBean.setShowNext(false);
			maintainKeywordsDataBean.setShowPrevious(false);
		}
		if (maintainKeywordsDataBean.getFirstPage() == maintainKeywordsDataBean
				.getCurrentPage()) {
			maintainKeywordsDataBean.boldPageNum[0] = true;
			maintainKeywordsDataBean.boldPageNum[1] = false;
			maintainKeywordsDataBean.boldPageNum[2] = false;
		} else if (maintainKeywordsDataBean.getFirstPage() + 1 == maintainKeywordsDataBean
				.getCurrentPage()) {
			maintainKeywordsDataBean.boldPageNum[0] = false;
			maintainKeywordsDataBean.boldPageNum[1] = true;
			maintainKeywordsDataBean.boldPageNum[2] = false;
		} else {
			maintainKeywordsDataBean.boldPageNum[0] = false;
			maintainKeywordsDataBean.boldPageNum[1] = false;
			maintainKeywordsDataBean.boldPageNum[2] = true;
		}
	}

	public String searchPageNavigation() {
		logger.info("SEARCHPAGENAVIGATION():::STARTED");
		MaintainKeywordsDataBean maintainKeywordsDataBean = this
				.getMaintainKeywordsDataBeanFromFacesContext();
		List letterTemplateList = null;
		MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = maintainKeywordsDataBean.getMaintainTemplateCriteriaVO();
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		try {
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			if (setStartIndexForSearch(maintainKeywordsDataBean,
					maintainTemplateCriteriaVO)) {
				maintainTemplateCriteriaVO.setRowsPerPage(10);
				enterpriseSearchResultsVO = contactMaintenanceDelegate
						.getAllMaintainLetterKeywords(maintainTemplateCriteriaVO);
				//enterpriseSearchResultsVO = getMaintainLetterKeywords(maintainTemplateCriteriaVO);
				List letterKeywordDOList = enterpriseSearchResultsVO
						.getSearchResults();
				List letterKeywordVOList = this
						.convertKeywordsListToVOs(letterKeywordDOList);
				maintainKeywordsDataBean.setKeywordList(letterKeywordVOList);
				enterpriseSearchResultsVO.setSearchResults(new ArrayList(
						letterKeywordVOList));
				maintainKeywordsDataBean
						.setEnterpriseSearchResultsVO(enterpriseSearchResultsVO);
				setRenderingForSearchDisplay(maintainKeywordsDataBean,
						enterpriseSearchResultsVO, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception::::::::: " + e.getMessage());

		}		
		
		logger
				.info("MaintainKeywordsControllerBean searchPageNavigation():::END");
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	public boolean setStartIndexForSearch(
			MaintainKeywordsDataBean maintainKeywordsDataBean,
			MaintainTemplateCriteriaVO maintainTemplateCriteriaVO) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		String linkID = map.get("param").toString();
		if ("firstPage".equals(linkID)) {
			if (maintainKeywordsDataBean.getFirstPage() == maintainKeywordsDataBean
					.getCurrentPage()) {
				return false;
			} else {
				maintainKeywordsDataBean
						.setCurrentPage(maintainKeywordsDataBean.getFirstPage());
			}
		} else if ("firstPagePlusOne".equals(linkID)) {
			if ((maintainKeywordsDataBean.getFirstPage() + 1) == maintainKeywordsDataBean
					.getCurrentPage()) {
				return false;
			} else {
				maintainKeywordsDataBean
						.setCurrentPage(maintainKeywordsDataBean.getFirstPage() + 1);
			}
		} else if ("firstPagePlusTwo".equals(linkID)) {
			if ((maintainKeywordsDataBean.getFirstPage() + 2) == maintainKeywordsDataBean
					.getCurrentPage()) {
				return false;
			} else {
				maintainKeywordsDataBean
						.setCurrentPage(maintainKeywordsDataBean.getFirstPage() + 2);
			}
		} else if ("showPrevious".equals(linkID)) {
			maintainKeywordsDataBean.setCurrentPage(maintainKeywordsDataBean
					.getCurrentPage() - 1);
		} else if ("showNext".equals(linkID)) {
			maintainKeywordsDataBean.setCurrentPage(maintainKeywordsDataBean
					.getCurrentPage() + 1);
		}
		if (maintainKeywordsDataBean.getCurrentPage() == 1
				|| maintainKeywordsDataBean.getNumberOfPages() == 2) {
			maintainKeywordsDataBean.setFirstPage(1);
		} else if (maintainKeywordsDataBean.getCurrentPage() == maintainKeywordsDataBean
				.getNumberOfPages()) {
			maintainKeywordsDataBean.setFirstPage(maintainKeywordsDataBean
					.getCurrentPage() - 2);
		} else {
			maintainKeywordsDataBean.setFirstPage(maintainKeywordsDataBean
					.getCurrentPage() - 1);
		}
		maintainTemplateCriteriaVO.setStartIndex((maintainKeywordsDataBean
				.getCurrentPage() - 1) * 10);
		return true;
	}
}
