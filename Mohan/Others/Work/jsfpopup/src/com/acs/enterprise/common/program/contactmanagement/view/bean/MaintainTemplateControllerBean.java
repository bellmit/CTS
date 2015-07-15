/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acs.enterprise.common.audit.common.helper.AuditMessageConstants;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.cots.lettergeneration.vo.LetterTmpltVO;
import com.acs.enterprise.common.cots.util.GlobalLetterConstants;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LetterKeywordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LetterTemplateFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.MaintainTemplateCriteriaVO;

import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LetterKeywordDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LetterTemplateDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterKeywordVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterTemplateKeywordAssociationVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterTemplateVO;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.ui.javax.faces.component.auditHistory.AuditHistoryTable;

/**
 * Created on Jul 8, 2008
 * 
 * @author Wipro This class is a controller bean used for Maintain Letter
 *         Template functionality.
 */

public class MaintainTemplateControllerBean extends
		EnterpriseBaseControllerBean {

	private static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(MaintainTemplateControllerBean.class);

	/**
	 * Default constructor
	 * 
	 */

	public MaintainTemplateControllerBean() {
		super();
		
	}


	public String allTemplateValues;

	public String getAllTemplateValues() {
		logger.debug("getAllTemplateValues():::started");
		MaintainTemplateDataBean letterTemplateDataBean = getLetterTemplateDataBean();
		logger.debug("letterTemplateDataBean:::" + letterTemplateDataBean);
		getAllMaintainLetterTemplates(letterTemplateDataBean);
		letterTemplateDataBean.setAllTemplates(false);		
		return null;
		}

	
	  public void setAllTemplateValues(String allTemplateValues) {
		  this.allTemplateValues = allTemplateValues;
		  }
	/**
	 * Method to get all templates
	 * 
	 * @return String
	 */
	/*public String getAllTemplates(
			MaintainTemplateDataBean letterTemplateDataBean) {
		System.out
				.println("MaintainTemplateControllerBean getAllTemplates():::Started");
		List letterTemplateList = null;
		LetterTemplate letterTemplateSend = null;
		// MaintainTemplateCriteriaVO
		MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = new MaintainTemplateCriteriaVO();
		logger.debug("maintainTemplateCriteriaVO:::"
				+ maintainTemplateCriteriaVO);
		// EnterpriseSearchResultsVO enterpriseSearchResultsVO = new
		// EnterpriseSearchResultsVO();
		if (letterTemplateDataBean != null
				&& letterTemplateDataBean.getListOfTemplateVOs() == null) {
			try {
				maintainTemplateCriteriaVO.setRowsPerPage(10);
				maintainTemplateCriteriaVO.setStartIndex(0);
				logger.debug("in try letterTemplateDataBean is not null");
				ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
				logger.debug("--------------------------------");
				letterTemplateList = contactMaintenanceDelegate
						.getAllLetterTemplates(maintainTemplateCriteriaVO);
				System.out
						.println("letterTemplateList:::" + letterTemplateList);
				// enterpriseSearchResultsVO.setSearchResults((ArrayList)letterTemplateList);
				LetterTemplateDOConvertor letterTemplateDOConvertor = null;
				LetterTemplate letterTemplate = null;
				if (letterTemplateList != null) {
					logger.debug("letterTemplateList size:::"
							+ letterTemplateList.size());
					LetterTemplateVO letterTemplateVO = null;
					letterTemplateDOConvertor = new LetterTemplateDOConvertor();
					List letterTemplateVOList = letterTemplateDOConvertor
							.convertLetterTemplateDOToVOList(letterTemplateList);
					logger.debug("letterTemplateVOList :::"
							+ letterTemplateVOList);
					if (letterTemplateVOList != null) {
						logger.debug("letterTemplateVOList size:::"
								+ letterTemplateVOList.size());
					}

					for (int i = 0; i < letterTemplateVOList.size(); i++) {
						System.out
								.println("in for LOOP letterTemplateVOList.size():::"
										+ letterTemplateVOList.size());
						letterTemplate = (LetterTemplate) letterTemplateList
								.get(i);
						System.out
								.println("letterTemplate:::" + letterTemplate);
						letterTemplateVO = (LetterTemplateVO) letterTemplateVOList
								.get(i);
						logger.debug("letterTemplateVO:::"
								+ letterTemplateVO);
						createVOAuditKeysList(letterTemplate, letterTemplateVO);
					}
					letterTemplateDataBean
							.setListOfTemplateVOs(letterTemplateVOList);

					Collections.sort(letterTemplateVOList,
							new TemplateComparator(
									GlobalLetterConstants.TEMPLATE_ID,
									GlobalLetterConstants.ASCENDING));
					// setRenderingForSearchDisplay(letterTemplateDataBean,letterTemplateList,false);
				}

			} catch (LetterTemplateFetchBusinessException ex) {
				logger.error(ex.getMessage(), ex);
			}

		}
		System.out
				.println("MaintainTemplateControllerBean getAllTemplates():::END");
		return ContactManagementConstants.RENDER_SUCCESS;
	}*/

	/**
	 * Method to get template details for the selected template
	 * 
	 * @return String
	 */
	public String getTemplateDetails() {
		//getTamplateDetailsCount++;
		logger.debug("INSIDE getTemplateDetails()Method:::::::::");
		MaintainTemplateDataBean letterTemplateDataBean = getLetterTemplateDataBean();
		letterTemplateDataBean.setInitialCursorFocusValue("Edit");
		letterTemplateDataBean.setShowSucessMessage(false);
		//if (getTamplateDetailsCount <= 1) {
			letterTemplateDataBean.setDueDtOffsetNum(null);
			letterTemplateDataBean.setRenderEditTemplate(true);
			LetterTemplateVO letterTemplateVO = getSelectedTemplateVO();
			LetterTemplateVO copyLettTempVO = new LetterTemplateVO();
			copyLettTempVO.setAddedAuditTimeStamp(letterTemplateVO
					.getAddedAuditTimeStamp());
			copyLettTempVO.setAddedAuditUserID(letterTemplateVO
					.getAddedAuditUserID());
			copyLettTempVO.setAssociatedKeywordsVO(letterTemplateVO
					.getAssociatedKeywordsVO());
			copyLettTempVO.setAuditTimeStamp(letterTemplateVO
					.getAuditTimeStamp());
			copyLettTempVO.setAuditUserID(letterTemplateVO.getAuditUserID());
			copyLettTempVO.setAvailableKeywordsVO(letterTemplateVO
					.getAvailableKeywordsVO());
			copyLettTempVO.setCotsLtrTmpltKeyData(letterTemplateVO
					.getCotsLtrTmpltKeyData());
			copyLettTempVO.setDbRecord(letterTemplateVO.isDbRecord());
			copyLettTempVO.setDfltDueDtOffsetNum(letterTemplateVO
					.getDfltDueDtOffsetNum());
			copyLettTempVO.setDfltSprvsrRevwReqdIndicator(letterTemplateVO
					.getDfltSprvsrRevwReqdIndicator());
			copyLettTempVO.setOptnlTextAllowIndicator(letterTemplateVO
					.getOptnlTextAllowIndicator());
			copyLettTempVO.setTemplateDescription(letterTemplateVO
					.getTemplateDescription());
			copyLettTempVO.setTemplateName(letterTemplateVO.getTemplateName());
			copyLettTempVO.setVersionNo(letterTemplateVO.getVersionNo());
			copyLettTempVO
					.setVoidIndicator(letterTemplateVO.getVoidIndicator());
			copyLettTempVO.setAuditKeyList(letterTemplateVO.getAuditKeyList());
			//Below code added as part of the Defect ESPRD00938865
			copyLettTempVO.setCategoryDescription (letterTemplateVO.getCategoryDescription());
			letterTemplateDataBean.setTemplateVO(copyLettTempVO);
			letterTemplateDataBean.setLetterTemplateVOEdit(letterTemplateVO);
			if (copyLettTempVO.getDfltDueDtOffsetNum() != null) {
				letterTemplateDataBean.setDueDtOffsetNum(copyLettTempVO
						.getDfltDueDtOffsetNum().toString());
			} else {
				letterTemplateDataBean.setDueDtOffsetNum("");
			}

			UIComponent component = ContactManagementHelper
					.findComponentInRoot("LtrTmpltsDetailsAuditId");
			if (component != null) {
				AuditHistoryTable auditHistoryTable = (AuditHistoryTable) component;
				auditHistoryTable.getAuditLogControllerBean().setPlusMinusFlag(
						false);
			}
			letterTemplateDataBean.setTempAvailableList(this
					.getAvailableKeywords(letterTemplateVO));

			letterTemplateDataBean.setTempAssociatedList(this
					.getAssignedKeywords(letterTemplateVO));
		//}
			logger.debug("END of  getTemplateDetails()Method:::::::::");
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * Method to update template details
	 * 
	 * @return String : page navigation result
	 * @throws LetterTemplateUIException
	 *             : update letter template UI exception
	 */
	public String updateTemplateDetails() throws Exception 
	{
		
		logger.debug("INSIDE updateTemplateDetails()Method:::::::::");
		System.out.println("updateTemplateDetails Methos has started");
		MaintainTemplateDataBean letterTemplateDataBean = getLetterTemplateDataBean();
		letterTemplateDataBean.setRenderEditTemplate(true);
		LetterTemplateVO slctTemplateVO = letterTemplateDataBean
				.getTemplateVO();
		LetterTemplateVO oldTemplateVO = null;
		LetterTemplate slctTemplateDO = null;

		FacesContext facesContext = FacesContext.getCurrentInstance();

		if (validate(letterTemplateDataBean, facesContext)) {
			slctTemplateVO.setDfltDueDtOffsetNum(new Integer(
					letterTemplateDataBean.getDueDtOffsetNum()));
			logger.debug("SlectedTemplate index:::::"
					+ letterTemplateDataBean.getSelectedTemplateIndex());
			int index = letterTemplateDataBean.getSelectedTemplateIndex();
			oldTemplateVO = (LetterTemplateVO) letterTemplateDataBean
					.getListOfTemplateVOs().get(
							letterTemplateDataBean.getSelectedTemplateIndex());
			System.out.println("oldTemplateVO"
					+ oldTemplateVO.getTemplateName());
			System.out.println("slctTemplateVO"
					+ slctTemplateVO.getTemplateName());
			System.out.println("oldTemplateVO.getAssociatedKeywordsVO()"
					+ oldTemplateVO.getAssociatedKeywordsVO());
			EnterpriseSearchResultsVO enterpriseSearchResultsVO = letterTemplateDataBean
					.getEnterpriseSearchResultsVO();
			List removedKeywordList = removedKeywordsList(
					letterTemplateDataBean.getTempAssociatedList(),
					oldTemplateVO.getAssociatedKeywordsVO());
			System.out.println("removedKeywordList size:::"
					+ removedKeywordList.size());
			List newAssociatedList = newAssociatedList(letterTemplateDataBean
					.getTempAssociatedList(), oldTemplateVO);
			System.out.println("newAssociatedList size:::"
					+ newAssociatedList.size());
			List letterTemplateList = null;
			slctTemplateVO.setAssociatedKeywordsVO(newAssociatedList);
			slctTemplateVO.setAuditUserID(getUserIDForAudit());
			slctTemplateVO.setAddedAuditUserID(getUserIDForAudit());
			//
			
			//
			LetterTemplateDOConvertor letterTemplateDOConvertor = new LetterTemplateDOConvertor();
			slctTemplateDO = letterTemplateDOConvertor
					.convertLetterTemplateVOToDO(slctTemplateVO);
			try {
				ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
				contactMaintenanceDelegate.updateTemplateDetails(
						slctTemplateDO, removedKeywordList);
				// To refetch the data.
				//letterTemplateDataBean.setListOfTemplateVOs();
      		LetterTemplateVO letterTemplateVO = null;
      		System.out.println("letterTemplateDataBean.getCurrentPage() "+ letterTemplateDataBean.getCurrentPage());
			// To set the editing template
			/*ArrayList searchList = letterTemplateDataBean.getEnterpriseSearchResultsVO().getSearchResults();
			if (searchList != null && !searchList.isEmpty()) {
				for (int i = 0; i < searchList.size(); i++) {
					letterTemplateVO = (LetterTemplateVO) searchList
							.get(i);
					if (letterTemplateVO.getCotsLtrTmpltKeyData().equals(
							slctTemplateVO.getCotsLtrTmpltKeyData())) {
						searchList.set(i, slctTemplateVO);
					}
				}
			}*/
      		MaintainTemplateCriteriaVO maintainTemplateCriteriaVO =letterTemplateDataBean.getMaintainTemplateCriteriaVO() ;
      		maintainTemplateCriteriaVO.setRowsPerPage(10);
      		maintainTemplateCriteriaVO.setStartIndex((letterTemplateDataBean.getCurrentPage()-1)*10);
      		//contactMaintenanceDelegate.getAllMaintainLetterTemplates(maintainTemplateCriteriaVO);
      		enterpriseSearchResultsVO = contactMaintenanceDelegate
			.getAllMaintainLetterTemplates(maintainTemplateCriteriaVO);
      		letterTemplateList = enterpriseSearchResultsVO.getSearchResults();				
      		LetterTemplate letterTemplate = null;
	if (letterTemplateList != null) {
		System.out.println("letterTemplateList size:::"	+ letterTemplateList.size());
	
		letterTemplateDOConvertor = new LetterTemplateDOConvertor();
		List letterTemplateVOList = letterTemplateDOConvertor
				.convertLetterTemplateDOToVOList(letterTemplateList);
		System.out.println("letterTemplateVOList :::"+ letterTemplateVOList);
		if (letterTemplateVOList != null) {
			System.out.println("letterTemplateVOList size:::"+ letterTemplateVOList.size());
		}

		for (int i = 0; i < letterTemplateVOList.size(); i++) {
			letterTemplate = (LetterTemplate) letterTemplateList.get(i);
			letterTemplateVO = (LetterTemplateVO) letterTemplateVOList.get(i);
			createVOAuditKeysList(letterTemplate, letterTemplateVO);
		}					

		letterTemplateDataBean.setListOfTemplateVOs(letterTemplateVOList);
	}
			
			letterTemplateDataBean.setTemplateVO(findSelectedTemplate(
						letterTemplateDataBean.getListOfTemplateVOs(),
						slctTemplateVO.getCotsLtrTmpltKeyData()));
			System.out.println("slctTemplateVO"
						+ slctTemplateVO.getTemplateName());
				getAvailableKeywords(letterTemplateDataBean.getTemplateVO());
				// To reset the available keywords
				
				letterTemplateDataBean.setTempAvailableList(this
						.getAvailableKeywords(getLetterTemplateDataBean()
								.getTemplateVO()));

				letterTemplateDataBean.setTempAssociatedList(this
						.getAssignedKeywords(getLetterTemplateDataBean()
								.getTemplateVO()));
			} catch (Exception ex) {
				logger.debug(" $$$$$$$$$$$$$$$$$$$$$$  ");
				ex.getStackTrace();
			}
			letterTemplateDataBean.setShowSucessMessage(true);
		} else {
			letterTemplateDataBean.setShowSucessMessage(false);
		}
		logger.debug("END of updateTemplateDetails()Method:::::::::");
		System.out.println("updateTemplateDetails Methos Ended started");
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method fetches the list of newly Asscoiated keyword
	 */
	// this is private
	public List newAssociatedList(List selectedList, LetterTemplateVO templateVO) {
		
		logger.debug("INSIDE of newAssociatedList()Method:::::::::");
		List newAssociatedList = new ArrayList(0);
		Iterator iter = null;
		LetterTemplateKeywordAssociationVO letterTemplateKeywordAssociationVO;
		SelectItem slctItem = null;
		LetterKeywordVO keywordVO = null;
		iter = selectedList.iterator();

		while (iter.hasNext()) {
			letterTemplateKeywordAssociationVO = new LetterTemplateKeywordAssociationVO();
			slctItem = (SelectItem) iter.next();

			letterTemplateKeywordAssociationVO.setLetterKeywordId(slctItem
					.getValue().toString());

			letterTemplateKeywordAssociationVO
					.setLetterKeywordDisplayName(slctItem.getDescription());

			letterTemplateKeywordAssociationVO
					.setAuditUserID(getUserIDForAudit());

			keywordVO = findKeywordInList(templateVO.getAssociatedKeywordsVO(),
					slctItem.getValue().toString());

			if (keywordVO == null) {

				// if not already associated we need to send the added user id
				keywordVO = findKeywordInList(templateVO
						.getAvailableKeywordsVO(), slctItem.getValue()
						.toString());

				if (keywordVO != null) {
					letterTemplateKeywordAssociationVO
							.setLetterKeywordUsageCode(keywordVO
									.getKeywordTypeCode());
				}
				letterTemplateKeywordAssociationVO
						.setAddedAuditUserID(getUserIDForAudit());

			} else {
				// If already associated then we need to send the version number

				letterTemplateKeywordAssociationVO.setVersionNo(keywordVO
						.getVersionNo());
				letterTemplateKeywordAssociationVO
						.setLetterKeywordUsageCode(keywordVO
								.getKeywordTypeCode());

				letterTemplateKeywordAssociationVO
						.setAddedAuditUserID(keywordVO.getAddedAuditUserID());
			}

			newAssociatedList.add(letterTemplateKeywordAssociationVO);
		}

		return newAssociatedList;
	}

	/**
	 * 
	 * @param selectedList
	 * @param currentKeywordList
	 * @return List
	 */
	// this is private
	public List removedKeywordsList(List selectedList, List currentKeywordList) {
		
		logger.debug("INSIDE of removedKeywordsList()Method:::::::::");
		List removedKeywordList = new ArrayList();
		List newTemplateParameter = selectedList;
		Iterator iterForCur = null;
		Iterator iterForNew = null;
		LetterKeywordVO oldLetterTemplateParameter = null;
		SelectItem newLetterTemplateParameter = null;
		LetterKeywordDOConvertor keywordDOConverter = new LetterKeywordDOConvertor();
		boolean foundFlag = false;

		iterForCur = currentKeywordList.iterator();

		while (iterForCur.hasNext()) {
			oldLetterTemplateParameter = (LetterKeywordVO) iterForCur.next();
			iterForNew = newTemplateParameter.iterator();
			foundFlag = false;// indicates that keyword not exist in new list

			while (iterForNew.hasNext()) {
				newLetterTemplateParameter = (SelectItem) iterForNew.next();

				if (oldLetterTemplateParameter.getKeywordId().equals(
						newLetterTemplateParameter.getValue().toString())) {
					foundFlag = true;
					break;
				}

			}
			if (!foundFlag) {
				oldLetterTemplateParameter.setAssociatedTemplatesList(null);

				removedKeywordList.add(keywordDOConverter
						.convertLetterKeywordVOToDO(oldLetterTemplateParameter,
								null));
			}
		}

		return removedKeywordList;
	}

	/**
	 * 
	 * @param tmpltList
	 * @param templateId
	 * @return LetterTemplateVO
	 */

	// this is private
	public LetterTemplateVO findSelectedTemplate(List tmpltList,
			String templateId) {
		logger.debug("in the findSelectedTemplate");
		LetterTemplateVO slctTemplate = null;
		Iterator iter;
		boolean matchFound = false;
		if (tmpltList != null) {
			iter = tmpltList.iterator();
			while (iter.hasNext()) {
				slctTemplate = (LetterTemplateVO) iter.next();
				if (slctTemplate.getCotsLtrTmpltKeyData().equals(templateId)) {
					matchFound = true;
					break;
				}
			}

		}

		return (matchFound) ? slctTemplate : null; // if not find selected
		// template
	}

	/**
	 * This method returns a LetterKeywordVO object based on key from the list
	 */

	/**
	 * @param keywordList
	 * @param key
	 * @return LetterKeywordVO
	 */
	// this is private
	public LetterKeywordVO findKeywordInList(List keywordList, String key) {

		Iterator it = keywordList.iterator();
		LetterKeywordVO keyword = null;
		boolean matchFound = false;
		while (it.hasNext()) {
			keyword = (LetterKeywordVO) it.next();
			if (keyword.getKeywordId().equals(key)) {
				matchFound = true;
				break;
			}
		}

		if (!matchFound) {
			keyword = null;
		}
		return keyword;
	}

	/**
	 * 
	 * @param letterTemplateDataBean
	 * @param facesContext
	 * @return boolean
	 */
	// this is private
	public boolean validate(MaintainTemplateDataBean letterTemplateDataBean,
			FacesContext facesContext) {
		LetterTemplateVO updatedTemplateVO = letterTemplateDataBean
				.getTemplateVO();
		boolean validationSuccess = true;
		if (letterTemplateDataBean.getDueDtOffsetNum() == null) {
			setErrorMessage(GlobalLetterConstants.LTR_REQ_FIELD,
					new Object[] { GlobalLetterConstants.DUE_DT_OFFSET },
					GlobalLetterConstants.LTR_TMPLT_MESSAGES_BUNDLE,
					facesContext);
			validationSuccess = false;
		} else if (EnterpriseCommonValidator
				.validateAlpha(letterTemplateDataBean.getDueDtOffsetNum())) {
			setErrorMessage(
					GlobalLetterConstants.DUE_DT_OFFSET_NUMERIC_VALID_ERROR,
					new Object[] { GlobalLetterConstants.DUE_DT_OFFSET_NO_ERR_MSG },
					GlobalLetterConstants.LTR_TMPLT_MESSAGES_BUNDLE,
					facesContext,
					GlobalLetterConstants.DUE_DT_OFFSET_NO_ERR_MSG);

			validationSuccess = false;

		} else if (((Integer.parseInt(letterTemplateDataBean
				.getDueDtOffsetNum()) < GlobalLetterConstants.DUE_DT_OFFSET_MIN))
				|| ((Integer.parseInt(letterTemplateDataBean
						.getDueDtOffsetNum()) > GlobalLetterConstants.DUE_DT_OFFSET_MAX))) {
			setErrorMessage(GlobalLetterConstants.DUE_DT_OFFSET_VALID_ERROR,
					null, GlobalLetterConstants.LTR_TMPLT_MESSAGES_BUNDLE,
					facesContext);
			validationSuccess = false;
		}
		return validationSuccess;

	}

	/**
	 * 
	 * @return String
	 */
	public String moveSelectedList() {
		MaintainTemplateDataBean letterTemplateDataBean = getLetterTemplateDataBean();

		letterTemplateDataBean.setRenderEditTemplate(true);

		moveSelected(letterTemplateDataBean.getSelectedList(),
				letterTemplateDataBean.getTempAvailableList(),
				letterTemplateDataBean.getTempAssociatedList(),
				letterTemplateDataBean);

		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * Remove the selected List.
	 * 
	 * @return String.
	 */
	public String removeSelectedList() {
		MaintainTemplateDataBean letterTemplateDataBean = getLetterTemplateDataBean();

		letterTemplateDataBean.setShowSucessMessage(false);
		letterTemplateDataBean.setShowSucessMessage(true);

		moveSelected(letterTemplateDataBean.getRemovedList(),
				letterTemplateDataBean.getTempAssociatedList(),
				letterTemplateDataBean.getTempAvailableList(),
				letterTemplateDataBean);

		return ContactManagementConstants.RENDER_SUCCESS;

	}

	/**
	 * This method is used to move the selected items to a list.
	 * 
	 * @param selectedItems
	 *            The selectedItems to set.
	 * @param availableList
	 *            The availableList to set.
	 * @param selectedList
	 *            The selectedList to set.
	 * @return void.
	 */
	// this is private
	public void moveSelected(List selectedItems, List fromList, List toList,
			MaintainTemplateDataBean letterTemplateDataBean) {
		letterTemplateDataBean.setShowSucessMessage(false);
		if (selectedItems != null) {
			for (Iterator iter = selectedItems.iterator(); iter.hasNext();) {
				toList.add(removeItem((String) iter.next(), fromList,
						letterTemplateDataBean));

			}
		}

	}

	/**
	 * 
	 * @return String
	 */
	public String resetTemplateDetails() 
	{
		logger.debug("INSIDE resetTemplateDetails()Method:::::::::");
		MaintainTemplateDataBean letterTemplateDataBean = getLetterTemplateDataBean();
		letterTemplateDataBean.setDueDtOffsetNum(null);

		int rowIndex = (int) letterTemplateDataBean.getSelectedTemplateIndex();
		
		logger.debug("rowIndex" +rowIndex);

		LetterTemplateVO letterTemplateVO = (LetterTemplateVO) getLetterTemplateDataBean()
				.getListOfTemplateVOs().get(rowIndex);

		LetterTemplateVO copyEditLettTempVO = new LetterTemplateVO();
		copyEditLettTempVO.setAddedAuditTimeStamp(letterTemplateVO
				.getAddedAuditTimeStamp());
		copyEditLettTempVO.setAddedAuditUserID(letterTemplateVO
				.getAddedAuditUserID());
		copyEditLettTempVO.setAssociatedKeywordsVO(letterTemplateVO
				.getAssociatedKeywordsVO());
		copyEditLettTempVO.setAuditTimeStamp(letterTemplateVO
				.getAuditTimeStamp());
		copyEditLettTempVO.setAuditUserID(letterTemplateVO.getAuditUserID());
		copyEditLettTempVO.setAvailableKeywordsVO(letterTemplateVO
				.getAvailableKeywordsVO());
		copyEditLettTempVO.setCotsLtrTmpltKeyData(letterTemplateVO
				.getCotsLtrTmpltKeyData());
		copyEditLettTempVO.setDbRecord(letterTemplateVO.isDbRecord());
		//if(letterTemplateVO.getDfltDueDtOffsetNum()!=null)
		//{
		copyEditLettTempVO.setDfltDueDtOffsetNum(letterTemplateVO.getDfltDueDtOffsetNum());
		//}
		copyEditLettTempVO.setDfltSprvsrRevwReqdIndicator(letterTemplateVO
				.getDfltSprvsrRevwReqdIndicator());
		copyEditLettTempVO.setOptnlTextAllowIndicator(letterTemplateVO
				.getOptnlTextAllowIndicator());
		copyEditLettTempVO.setTemplateDescription(letterTemplateVO
				.getTemplateDescription());
		copyEditLettTempVO.setTemplateName(letterTemplateVO.getTemplateName());
		copyEditLettTempVO.setVersionNo(letterTemplateVO.getVersionNo());
		copyEditLettTempVO
				.setVoidIndicator(letterTemplateVO.getVoidIndicator());
		letterTemplateDataBean.setDueDtOffsetNum(letterTemplateVO
				.getDfltDueDtOffsetNum().toString());
		letterTemplateDataBean.setTemplateVO(copyEditLettTempVO);
		letterTemplateDataBean.setTempAvailableList(this
				.getAvailableKeywords(getLetterTemplateDataBean()
						.getTemplateVO()));

		letterTemplateDataBean.setTempAssociatedList(this
				.getAssignedKeywords(getLetterTemplateDataBean()
						.getTemplateVO()));
		// by sur sa 
		letterTemplateDataBean.setShowSucessMessage(false);
		
		logger.debug("END of resetTemplateDetails()Method:::::::::");
	
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method get LetterTemplateDataBean object.
	 * 
	 * @return MaintainTemplateDataBean.
	 */
	public final MaintainTemplateDataBean getLetterTemplateDataBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		MaintainTemplateDataBean letterTemplateDataBean = (MaintainTemplateDataBean) fc
				.getApplication().createValueBinding(
						"#{" + GlobalLetterConstants.LETTER_DATA_BEAN + "}")
				.getValue(fc);
		return letterTemplateDataBean;
	}

	/**
	 * This operation is used to get the value object of the selected row.
	 * 
	 * @return LetterTemplateVO.
	 */
	// this is private
	public LetterTemplateVO getSelectedTemplateVO() {
		LetterTemplateVO templateVO = null;
		int rowIndex;
		FacesContext context = FacesContext.getCurrentInstance();
		Map rowMap = context.getExternalContext().getRequestParameterMap();
		if (rowMap != null && rowMap.get("rowNum") != null) {
			rowIndex = Integer.parseInt((String) rowMap.get("rowNum"));
			getLetterTemplateDataBean().setSelectedIndex(
					(String) rowMap.get("rowNum"));

			getLetterTemplateDataBean().setSelectedTemplateIndex(rowIndex);

			templateVO = (LetterTemplateVO) getLetterTemplateDataBean()
					.getListOfTemplateVOs().get(rowIndex);
		}

		return templateVO;
	}

	/**
	 * 
	 * @param event
	 * @return String
	 */	
	public String getAllSortedTemplates(ActionEvent event) {
		logger.debug("INSIDE getAllSortedTemplates() Method $$$$$$$$$$$$$$$$$");
		MaintainTemplateDataBean letterTemplateDataBean = getLetterTemplateDataBean();
		final String sortColumn = (String) event.getComponent().getAttributes()
				.get("columnName");
		final String sortOrder = (String) event.getComponent().getAttributes()
				.get("sortOrder");
		letterTemplateDataBean.setImageRender(event.getComponent().getId());
		MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = letterTemplateDataBean
				.getMaintainTemplateCriteriaVO();

		if (GlobalLetterConstants.TEMPLATE_ID.equalsIgnoreCase(sortColumn)
				&& GlobalLetterConstants.ASCENDING.equalsIgnoreCase(sortOrder)) {

			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.TEMPLATE_ID);
			maintainTemplateCriteriaVO.setAscending(true);
		}
		if (GlobalLetterConstants.TEMPLATE_ID.equalsIgnoreCase(sortColumn)
				&& GlobalLetterConstants.DESCENDING.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.TEMPLATE_ID);
			maintainTemplateCriteriaVO.setAscending(false);
		}
		if (sortColumn.equals(GlobalLetterConstants.TEMPLATE_NAME)
				&& GlobalLetterConstants.ASCENDING.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.TEMPLATE_NAME);
			maintainTemplateCriteriaVO.setAscending(true);
		}
		if (sortColumn.equals(GlobalLetterConstants.TEMPLATE_NAME)
				&& GlobalLetterConstants.DESCENDING.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.TEMPLATE_NAME);
			maintainTemplateCriteriaVO.setAscending(false);
		}
		if (sortColumn.equals(GlobalLetterConstants.TEMPLATE_DESCRIPTION)
				&& GlobalLetterConstants.ASCENDING.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.TEMPLATE_DESCRIPTION);
			maintainTemplateCriteriaVO.setAscending(true);
		}
		if (sortColumn.equals(GlobalLetterConstants.TEMPLATE_DESCRIPTION)
				&& GlobalLetterConstants.DESCENDING.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.TEMPLATE_DESCRIPTION);
			maintainTemplateCriteriaVO.setAscending(false);
		}
		if (sortColumn.equals(GlobalLetterConstants.SUPERVISOR_REVIEW_REQUIRED)
				&& GlobalLetterConstants.ASCENDING.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.SUPERVISOR_REVIEW_REQUIRED);
			maintainTemplateCriteriaVO.setAscending(true);
		}
		if (sortColumn.equals(GlobalLetterConstants.SUPERVISOR_REVIEW_REQUIRED)
				&& GlobalLetterConstants.DESCENDING.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.SUPERVISOR_REVIEW_REQUIRED);
			maintainTemplateCriteriaVO.setAscending(false);
		}
		if (sortColumn.equals(GlobalLetterConstants.VOID)
				&& GlobalLetterConstants.ASCENDING.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.VOID);
			maintainTemplateCriteriaVO.setAscending(true);
		}
		if (sortColumn.equals(GlobalLetterConstants.VOID)
				&& GlobalLetterConstants.DESCENDING.equalsIgnoreCase(sortOrder)) {
			maintainTemplateCriteriaVO
					.setSortColumn(GlobalLetterConstants.VOID);
			maintainTemplateCriteriaVO.setAscending(false);
		}
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		List letterTemplateList = null;
		try {
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
			maintainTemplateCriteriaVO.setStartIndex(0);
			maintainTemplateCriteriaVO.setRowsPerPage(10);
			enterpriseSearchResultsVO = contactMaintenanceDelegate
					.getAllMaintainLetterTemplates(maintainTemplateCriteriaVO);
			letterTemplateList = enterpriseSearchResultsVO.getSearchResults();
			// enterpriseSearchResultsVO.setSearchResults((ArrayList)letterTemplateList);
			LetterTemplateDOConvertor letterTemplateDOConvertor = null;
			LetterTemplate letterTemplate = null;
			if (letterTemplateList != null) {

				LetterTemplateVO letterTemplateVO = null;
				letterTemplateDOConvertor = new LetterTemplateDOConvertor();
				List letterTemplateVOList = letterTemplateDOConvertor
						.convertLetterTemplateDOToVOList(letterTemplateList);

				if (letterTemplateVOList != null) {
					logger.debug("letterTemplateVOList size:::"
							+ letterTemplateVOList.size());
				}
				for (int i = 0; i < letterTemplateVOList.size(); i++) {
					letterTemplate = (LetterTemplate) letterTemplateList.get(i);
					letterTemplateVO = (LetterTemplateVO) letterTemplateVOList
							.get(i);
					createVOAuditKeysList(letterTemplate, letterTemplateVO);
				}
				letterTemplateDataBean
						.setListOfTemplateVOs(letterTemplateVOList);
				enterpriseSearchResultsVO.setSearchResults(new ArrayList(
						letterTemplateVOList));
				setRenderingForSearchDisplay(letterTemplateDataBean,
						enterpriseSearchResultsVO, false);
			}
		} catch (Exception e) {

		}
		logger.debug("END of getAllSortedTemplates() Method $$$$$$$$$$$$$$$$$");
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * 
	 * @param templateVO
	 * @return List
	 */
	public List getAvailableKeywords(LetterTemplateVO templateVO) {
		logger.debug("getAvailableKeywords started");
		List selectItemKeyList = new ArrayList(0);

		List keywordVOList = templateVO.getAvailableKeywordsVO();
		SelectItem si = null;

		if (keywordVOList == null || keywordVOList.isEmpty()) {
			try {
				ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

				List availableKeywords = contactMaintenanceDelegate
						.getKeywordsNotAssociatedToTemplate(templateVO
								.getCotsLtrTmpltKeyData());

				LetterKeywordDOConvertor letterKeywordDOConvertor = new LetterKeywordDOConvertor();
				List availableKeywordsVO = letterKeywordDOConvertor
						.convertLetterKeywordDOToVOList(availableKeywords);

				templateVO.setAvailableKeywordsVO(availableKeywordsVO);

			} catch (LetterKeywordFetchBusinessException ex) {
				logger.error(ex.getMessage());
			}
		}

		keywordVOList = templateVO.getAvailableKeywordsVO();
		LetterKeywordVO keywordVO = null;
		if (keywordVOList != null) {
			Iterator iterator = keywordVOList.iterator();

			while (iterator.hasNext()) {

				keywordVO = (LetterKeywordVO) iterator.next();

				si = new SelectItem();
				si.setLabel(keywordVO.getKeywordId());
				si.setValue(keywordVO.getKeywordId());
				selectItemKeyList.add(si);
			}

		}
		return selectItemKeyList;
	}

	/**
	 * This operation is used to get the assigned keywords for the given Letter
	 * Template.
	 * 
	 * @param categorieFilterVOList
	 *            The categorieFilterVOList to set.
	 * @return List.
	 */
	public List getAssignedKeywords(LetterTemplateVO templateVO) {
		List selectItemKeyList = new ArrayList(0);
		List keywordVOList = templateVO.getAssociatedKeywordsVO();
		Iterator iterator = keywordVOList.iterator();
		SelectItem si = null;
		LetterKeywordVO keywordVO = null;
		while (iterator.hasNext()) {
			keywordVO = (LetterKeywordVO) iterator.next();
			si = new SelectItem();
			si.setLabel(keywordVO.getKeywordId());
			si.setValue(keywordVO.getKeywordId());
			selectItemKeyList.add(si);
		}

		return selectItemKeyList;
	}

	/**
	 * Method to remove an item from the list.
	 * 
	 * @param value
	 *            The value to set.
	 * @param filterList
	 *            The filterList to set.
	 * @return SelectItem.
	 */
	// this is private
	public SelectItem removeItem(String value, List templateList,
			MaintainTemplateDataBean letterTemplateDataBean) {

		letterTemplateDataBean.setShowSucessMessage(false);
		SelectItem result = null;
		int size = templateList.size();
		SelectItem item = null;
		for (int i = 0; i < size; i++) {
			item = (SelectItem) templateList.get(i);
			if (value.equals(item.getValue())) {
				result = (SelectItem) templateList.remove(i);
				break;
			}
		}
		return result;
	}

	/**
	 * This operation is used to cancel the Category details Add/Edit operation.
	 * 
	 * @return String : Navigation message.
	 */
	public String cancelTemplateDetails() 
	{		
		logger.debug("INSIDE cancelTemplateDetails()Method:::::::::");
		MaintainTemplateDataBean letterTemplateDataBean = getLetterTemplateDataBean();
		//this.resetTemplateDetails();
		letterTemplateDataBean.setRenderEditTemplate(false);
		logger.debug("END of cancelTemplateDetails()Method:::::::::");
		return ContactManagementConstants.RENDER_SUCCESS;
	}

	/**
	 * This method will get userid from Security.
	 * 
	 * @return String
	 */
	public String getUserIDForAudit() {
		String userId = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest) facesContext
				.getExternalContext().getRequest();
		userId = getLoggedInUserID(portletRequest);
		return userId;
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
	private void setErrorMessage(String errorName, Object[] arguments,
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

	private void setErrorMessage(String errorName, Object[] arguments,
			String messageBundle, FacesContext facesContext, String componentId) {
		UIViewRoot root = facesContext.getViewRoot();
		Locale locale = root.getLocale();
		String clientId = null;

		facesContext.getApplication().setMessageBundle(messageBundle);
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale,
				facesContext.getClass().getClassLoader());
		String errorMsg = MessageFormat.format(bundle.getString(errorName),
				arguments);
		FacesMessage fc = new FacesMessage();
		fc.setDetail(errorMsg);

		if (componentId != null) {
			UIComponent uiComponent = findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);
		}

		facesContext.addMessage(clientId, fc);
	}

	public UIComponent findComponentInRoot(String id) {
		UIComponent component = null;
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			UIComponent root = context.getViewRoot();
			component = findComponent(root, id);
		}

		return component;
	}

	public UIComponent findComponent(UIComponent base, String id) {
		// Is the "base" component itself the match we are looking for?
		if (id.equals(base.getId())) {
			return base;
		}
		// Search through our facets and children
		UIComponent component = null;
		UIComponent result = null;
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

		return result;
	}

	final String LETTER_TEMPLATE_PAGE = "/Enterprise/CtMgmtMaintTemplates";

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
						.getFiledAccessPermission(LETTER_TEMPLATE_PAGE, userID);
				map.put("feildAccessUpdate", feildAccessUpdate);

			}

		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (feildAccessUpdate != null
					&& !(feildAccessUpdate.equalsIgnoreCase(""))) {

				if (feildAccessUpdate.equalsIgnoreCase("r")) {
					getLetterTemplateDataBean().setUpdateLtrTemplates(true);
					if (getLetterTemplateDataBean().isRenderEditTemplate()) {

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
		MaintainTemplateDataBean maintainTmpltDataBean = getLetterTemplateDataBean();
		List tmpltsList = maintainTmpltDataBean.getListOfTemplateVOs();
		try {

			if (tmpltsList != null && !(tmpltsList.isEmpty())) {
				if (tmpltsList != null && tmpltsList.size() > 0) {
					Iterator auditKeyIt = tmpltsList.iterator();
					List editableTmplt = new ArrayList();
					editableTmplt.add(createAuditableFeild("Void",
							"voidIndicator"));
					editableTmplt.add(createAuditableFeild("Template Name",
							"name"));
					editableTmplt.add(createAuditableFeild(
							"Template Description", "description"));
					editableTmplt.add(createAuditableFeild(
							"Supervisor Review Required",
							"defaultSupervisorRevwReqInd"));
					editableTmplt.add(createAuditableFeild(
							"Number of Days for Response",
							"defaultDueDateOffsetNum"));
					LetterTemplateVO letterTmpltVO = null;
					while (auditKeyIt.hasNext()) {
						letterTmpltVO = (LetterTemplateVO) auditKeyIt.next();
						if (letterTmpltVO.getAuditKeyList() != null
								&& !(letterTmpltVO.getAuditKeyList().isEmpty())) {
							AuditDataFilter.filterAuditKeys(editableTmplt,
									letterTmpltVO);
						}
						String tmpltindex = maintainTmpltDataBean
								.getSelectedIndex();
						if (tmpltindex != null
								&& tmpltindex.trim().length() > 0) {
							maintainTmpltDataBean
									.setTemplateVO((LetterTemplateVO) tmpltsList
											.get(Integer.parseInt(tmpltindex)));
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		maintainTmpltDataBean.setAuditLogFlag(true);

		return "";
	}

	private AuditableField createAuditableFeild(String feildName,
			String domainAttributeName) {

		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);

		return auditableField;

	}

	
	public  boolean setStartIndexForSearch(
			MaintainTemplateDataBean maintainTemplateDataBean,
			MaintainTemplateCriteriaVO maintainTemplateCriteriaVO) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		String linkID = map.get("param").toString();
		if ("firstPage".equals(linkID)) {
			if (maintainTemplateDataBean.getFirstPage() == maintainTemplateDataBean
					.getCurrentPage()) {
				return false;
			} else {
				maintainTemplateDataBean
						.setCurrentPage(maintainTemplateDataBean.getFirstPage());
			}
		} else if ("firstPagePlusOne".equals(linkID)) {
			if ((maintainTemplateDataBean.getFirstPage() + 1) == maintainTemplateDataBean
					.getCurrentPage()) {
				return false;
			} else {
				maintainTemplateDataBean
						.setCurrentPage(maintainTemplateDataBean.getFirstPage() + 1);
			}
		} else if ("firstPagePlusTwo".equals(linkID)) {
			if ((maintainTemplateDataBean.getFirstPage() + 2) == maintainTemplateDataBean
					.getCurrentPage()) {
				return false;
			} else {
				maintainTemplateDataBean
						.setCurrentPage(maintainTemplateDataBean.getFirstPage() + 2);
			}
		} else if ("showPrevious".equals(linkID)) {
			maintainTemplateDataBean.setCurrentPage(maintainTemplateDataBean
					.getCurrentPage() - 1);
		} else if ("showNext".equals(linkID)) {
			maintainTemplateDataBean.setCurrentPage(maintainTemplateDataBean
					.getCurrentPage() + 1);
		}
		if (maintainTemplateDataBean.getCurrentPage() == 1
				|| maintainTemplateDataBean.getNumberOfPages() == 2) {
			maintainTemplateDataBean.setFirstPage(1);
		} else if (maintainTemplateDataBean.getCurrentPage() == maintainTemplateDataBean
				.getNumberOfPages()) {
			maintainTemplateDataBean.setFirstPage(maintainTemplateDataBean
					.getCurrentPage() - 2);
		} else {
			maintainTemplateDataBean.setFirstPage(maintainTemplateDataBean
					.getCurrentPage() - 1);
		}

		// maintainTemplateCriteriaVO.setStartIndex((maintainTemplateDataBean.getCurrentPage()-1)*GlobalLetterConstants.ITEMS_PER_PAGE);
		maintainTemplateCriteriaVO.setStartIndex((maintainTemplateDataBean
				.getCurrentPage() - 1) * 10);
		return true;
	}

	public  void setRenderingForSearchDisplay(
			MaintainTemplateDataBean maintainTemplateDataBean,
			EnterpriseSearchResultsVO enterpriseSearchResultsVO,
			boolean isFromPagination) {
		if (!isFromPagination) {
			maintainTemplateDataBean.setCount((int) enterpriseSearchResultsVO
					.getRecordCount());
			// maintainTemplateDataBean.setCount((int)
			// letterTemplateList.size());
			int noOfPages = maintainTemplateDataBean.getCount() / 10;
			int modNofPages = maintainTemplateDataBean.getCount() % 10;
			if (modNofPages != 0) {
				noOfPages = noOfPages + 1;
			}
			maintainTemplateDataBean.setFirstPage(1);
			maintainTemplateDataBean.setCurrentPage(1);
			maintainTemplateDataBean.setNumberOfPages(noOfPages);
			maintainTemplateDataBean.setStartRecord(1);
		} else {
			// maintainTemplateDataBean.setStartRecord(((maintainTemplateDataBean.getCurrentPage()-1)*GlobalLetterConstants.ITEMS_PER_PAGE)+1);
			maintainTemplateDataBean.setStartRecord(((maintainTemplateDataBean
					.getCurrentPage() - 1) * 10) + 1);
		}
		if (maintainTemplateDataBean.getCurrentPage() < maintainTemplateDataBean
				.getNumberOfPages()) {
			// maintainTemplateDataBean.setEndRecord(maintainTemplateDataBean.getCurrentPage()*GlobalLetterConstants.ITEMS_PER_PAGE);
			maintainTemplateDataBean.setEndRecord(maintainTemplateDataBean
					.getCurrentPage() * 10);
		} else {
			maintainTemplateDataBean.setEndRecord(maintainTemplateDataBean
					.getCount());
		}
		maintainTemplateDataBean.setSearchResultShow(true);
		if (maintainTemplateDataBean.getNumberOfPages() != 1) {
			maintainTemplateDataBean.setRenderFirstPage(true);
			maintainTemplateDataBean.setRenderFirstPagePlusOne(true);
			maintainTemplateDataBean.setShowNext(false);
			maintainTemplateDataBean.setShowPrevious(false);
			maintainTemplateDataBean.setRenderFirstPagePlusTwo(false);
			if (maintainTemplateDataBean.getCurrentPage() != maintainTemplateDataBean
					.getNumberOfPages()) {
				maintainTemplateDataBean.setShowNext(true);
			}
			if (maintainTemplateDataBean.getCurrentPage() != 1) {
				maintainTemplateDataBean.setShowPrevious(true);
			}
			if (maintainTemplateDataBean.getCurrentPage() <= maintainTemplateDataBean
					.getNumberOfPages()
					&& (maintainTemplateDataBean.getFirstPage() + 2) <= maintainTemplateDataBean
							.getNumberOfPages()) {
				maintainTemplateDataBean.setRenderFirstPagePlusTwo(true);
			}
		} else {
			maintainTemplateDataBean.setRenderFirstPage(false);
			maintainTemplateDataBean.setRenderFirstPagePlusOne(false);
			maintainTemplateDataBean.setRenderFirstPagePlusTwo(false);
			maintainTemplateDataBean.setShowNext(false);
			maintainTemplateDataBean.setShowPrevious(false);
		}
		if (maintainTemplateDataBean.getFirstPage() == maintainTemplateDataBean
				.getCurrentPage()) {
			maintainTemplateDataBean.boldPageNum[0] = true;
			maintainTemplateDataBean.boldPageNum[1] = false;
			maintainTemplateDataBean.boldPageNum[2] = false;
		} else if (maintainTemplateDataBean.getFirstPage() + 1 == maintainTemplateDataBean
				.getCurrentPage()) {
			maintainTemplateDataBean.boldPageNum[0] = false;
			maintainTemplateDataBean.boldPageNum[1] = true;
			maintainTemplateDataBean.boldPageNum[2] = false;
		} else {
			maintainTemplateDataBean.boldPageNum[0] = false;
			maintainTemplateDataBean.boldPageNum[1] = false;
			maintainTemplateDataBean.boldPageNum[2] = true;
		}
	}

	public String searchPageNavigation() {
		MaintainTemplateDataBean letterTemplateDataBean = getLetterTemplateDataBean();
		List letterTemplateList = null;
		MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = letterTemplateDataBean.getMaintainTemplateCriteriaVO();
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		try {
			logger.debug("in try letterTemplateDataBean is not null");
			ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

			if (setStartIndexForSearch(letterTemplateDataBean,
					maintainTemplateCriteriaVO)) {
				maintainTemplateCriteriaVO.setRowsPerPage(10);
				
				enterpriseSearchResultsVO = contactMaintenanceDelegate
						.getAllMaintainLetterTemplates(maintainTemplateCriteriaVO);
				letterTemplateList = enterpriseSearchResultsVO
						.getSearchResults();
				LetterTemplateDOConvertor letterTemplateDOConvertor = null;
				LetterTemplate letterTemplate = null;
				if (letterTemplateList != null) {
					logger.debug("letterTemplateList size:::"
							+ letterTemplateList.size());
					LetterTemplateVO letterTemplateVO = null;
					letterTemplateDOConvertor = new LetterTemplateDOConvertor();
					List letterTemplateVOList = letterTemplateDOConvertor
							.convertLetterTemplateDOToVOList(letterTemplateList);
					logger.debug("letterTemplateVOList :::"
							+ letterTemplateVOList);
					if (letterTemplateVOList != null) {
						logger.debug("letterTemplateVOList size:::"
								+ letterTemplateVOList.size());
					}
					for (int i = 0; i < letterTemplateVOList.size(); i++) 
					{
						letterTemplate = (LetterTemplate) letterTemplateList
								.get(i);
						letterTemplateVO = (LetterTemplateVO) letterTemplateVOList
								.get(i);
						logger.debug("letterTemplateVO:::"
								+ letterTemplateVO);
						createVOAuditKeysList(letterTemplate, letterTemplateVO);
					}
					letterTemplateDataBean
					.setListOfTemplateVOs(letterTemplateVOList);
					enterpriseSearchResultsVO.setSearchResults(new ArrayList(letterTemplateVOList));
					setRenderingForSearchDisplay(letterTemplateDataBean,
							enterpriseSearchResultsVO, true);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception::::::::: " + e.getMessage());
		}

		System.out
				.println("MaintainTemplateControllerBean searchPageNavigation():::END");
		return ContactManagementConstants.RENDER_SUCCESS;
	}
	public  void setRecordRange(
			EnterpriseSearchResultsVO enterpriseSearchResultsVO) {
		
		int listSize;
		long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
		MaintainTemplateDataBean maintainTemplateDataBean =  getLetterTemplateDataBean();
		maintainTemplateDataBean.setCount((int) totalRecordCount);

		int noOfPages = maintainTemplateDataBean.getCount()
				/ maintainTemplateDataBean.getItemsPerPage();

		int modNofPages = maintainTemplateDataBean.getCount()
				% maintainTemplateDataBean.getItemsPerPage();

		if (modNofPages != 0) {
			noOfPages = noOfPages + 1;
		}

		maintainTemplateDataBean
				.setCurrentPage(1);
		maintainTemplateDataBean.setNumberOfPages(noOfPages);
		maintainTemplateDataBean
				.setStartRecord(1);
		maintainTemplateDataBean
				.setEndRecord(10);
		listSize = maintainTemplateDataBean.getCount();

		if (listSize <= maintainTemplateDataBean.getItemsPerPage()) {
			maintainTemplateDataBean.setEndRecord(listSize);
		}

		if (maintainTemplateDataBean.getCount() <= maintainTemplateDataBean
				.getItemsPerPage()) {
			maintainTemplateDataBean.setShowNext(false);
		} else {
			maintainTemplateDataBean.setShowNext(true);
		}
		maintainTemplateDataBean.setShowPrevious(false);
	}
	public String getAllMaintainLetterTemplates(
			MaintainTemplateDataBean letterTemplateDataBean) {
		logger.debug("MaintainTemplateControllerBean getAllMaintainLetterTemplates():::Started");
		List letterTemplateList = null;
		LetterTemplate letterTemplateSend = null;		
		MaintainTemplateCriteriaVO maintainTemplateCriteriaVO = new MaintainTemplateCriteriaVO();
		logger.debug("maintainTemplateCriteriaVO:::"	+ maintainTemplateCriteriaVO);
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
		if (letterTemplateDataBean != null
				&& letterTemplateDataBean.getListOfTemplateVOs() == null) {
			try {
				maintainTemplateCriteriaVO.setRowsPerPage(10);
				maintainTemplateCriteriaVO.setStartIndex(0);
				logger.debug("in try letterTemplateDataBean is not null");
				ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
				logger.debug("--------------------------------");
				enterpriseSearchResultsVO = contactMaintenanceDelegate
						.getAllMaintainLetterTemplates(maintainTemplateCriteriaVO);
				letterTemplateList = enterpriseSearchResultsVO.getSearchResults();
				logger.debug("letterTemplateList:::" + letterTemplateList);
				// enterpriseSearchResultsVO.setSearchResults((ArrayList)letterTemplateList);
				LetterTemplateDOConvertor letterTemplateDOConvertor = null;
				LetterTemplate letterTemplate = null;
				if (letterTemplateList != null) {
					logger.debug("letterTemplateList size:::"	+ letterTemplateList.size());
					LetterTemplateVO letterTemplateVO = null;
					letterTemplateDOConvertor = new LetterTemplateDOConvertor();
					List letterTemplateVOList = letterTemplateDOConvertor
							.convertLetterTemplateDOToVOList(letterTemplateList);
					logger.debug("letterTemplateVOList :::" + letterTemplateVOList);
					if (letterTemplateVOList != null) {
						logger.debug("letterTemplateVOList size:::" + letterTemplateVOList.size());
					}

					for (int i = 0; i < letterTemplateVOList.size(); i++) {
						letterTemplate = (LetterTemplate) letterTemplateList
								.get(i);
						letterTemplateVO = (LetterTemplateVO) letterTemplateVOList
								.get(i);
						createVOAuditKeysList(letterTemplate, letterTemplateVO);
					}

					letterTemplateDataBean
							.setListOfTemplateVOs(letterTemplateVOList);
					enterpriseSearchResultsVO.setSearchResults(new ArrayList(
							letterTemplateVOList));
					setRecordRange(enterpriseSearchResultsVO);
					setRenderingForSearchDisplay(letterTemplateDataBean,
							enterpriseSearchResultsVO, false);
				}

			} catch (LetterTemplateFetchBusinessException ex) {
				logger.error(ex.getMessage(), ex);
			}

		}
		logger.debug("MaintainTemplateControllerBean getAllMaintainLetterTemplates():::END");
		return ContactManagementConstants.RENDER_SUCCESS;
	}
}
