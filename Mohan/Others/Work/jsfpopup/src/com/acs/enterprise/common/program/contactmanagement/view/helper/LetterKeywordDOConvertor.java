/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterGenerationKeyword;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterGenerationTemplateParameter;
import com.acs.enterprise.common.cots.util.GlobalLetterConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterKeywordVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterTemplateKeywordAssociationVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class is used to convert all Domain Objects into Value objects of Log
 * Case.
 * 
 * @author Wipro
 */
public class LetterKeywordDOConvertor extends DataTransferObjectConverter {

	/** Enterprise Logger for Logging. */
	private EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CorrespondenceRoutingDOConvertor.class);

	/*
	 * /** Method to convert DO List to VO List
	 * 
	 * @param letterKeywordDOList : a list of LetterGenerationKeywords
	 * 
	 * @return LetterKeywordVOList
	 */
	public List convertLetterKeywordDOToVOList(List letterKeywordDOList) {
		List letterKeywordVOList = new ArrayList();
		LetterGenerationKeyword letterKeyword = null;
		LetterKeywordVO letterKeywordVO = null;
		Iterator iterator = null;
		LetterGenerationTemplateParameter letterTemplateParam = null;
		LetterTemplateKeywordAssociationVO associatedTemplateVO = null;
		for (Iterator iter = letterKeywordDOList.iterator(); iter.hasNext();) {
			letterKeyword = (LetterGenerationKeyword) iter.next();
			letterKeywordVO = new LetterKeywordVO();
			letterKeywordVO.setKeywordId(letterKeyword.getLetterKeyWordID());
			letterKeywordVO.setKeywordLabel(letterKeyword.getLabelText());
			letterKeywordVO.setKeywordLongDesc(letterKeyword
					.getLongDescription());
			letterKeywordVO.setKeywordShortDesc(letterKeyword
					.getShortDescription());
			letterKeywordVO.setKeywordTypeCode(letterKeyword
					.getLetterKeyWordTypeCode());
			letterKeywordVO.setAddedAuditTimeStamp(letterKeyword
					.getAddedAuditTimeStamp());
			letterKeywordVO.setAddedAuditUserID(letterKeyword
					.getAddedAuditUserID());
			letterKeywordVO
					.setAuditTimeStamp(letterKeyword.getAuditTimeStamp());
			letterKeywordVO.setAuditUserID(letterKeyword.getAuditUserID());
			letterKeywordVO.setVersionNo(letterKeyword.getVersionNo());
			letterKeywordVO.setDisplaySize(letterKeyword.getDisplaySize());
			/**added as part of CR 827474*/
			letterKeywordVO.setKeyWordDataTyCd(letterKeyword.getLetterKeyWordDataTypeCode());
			if (letterKeyword.getLetterGenerationTemplateParameters() != null
					&& letterKeyword.getLetterGenerationTemplateParameters()
							.size() > 0) {
				iterator = letterKeyword
						.getLetterGenerationTemplateParameters().iterator();

				while (iterator.hasNext()) {
					letterTemplateParam = (LetterGenerationTemplateParameter) iterator
							.next();
					if (letterTemplateParam != null
							&& letterTemplateParam.getLetterTemplate() != null) {
						associatedTemplateVO = this
								.convertLetterTemplateParameterDOToVO(letterTemplateParam);
						letterKeywordVO.getAssociatedTemplatesList().add(
								associatedTemplateVO);
					}

				}

			}
			letterKeywordVOList.add(letterKeywordVO);
		}

		return letterKeywordVOList;
	}

	/**
	 * Method to convert VO to DO
	 * 
	 * @param letterKeywordVO
	 *            : an object of type LetterKeywordVO
	 * @param associatedTemplatesAsSelectItems
	 *            : a list containing templates as SelectItem objects
	 * @return LetterGenerationKeyword : a domain object
	 */
	public LetterGenerationKeyword convertLetterKeywordVOToDO(
			LetterKeywordVO letterKeywordVO,
			List associatedTemplatesAsSelectItems) {
 		LetterGenerationKeyword letterKeyword = new LetterGenerationKeyword();
		if (letterKeywordVO.getKeywordId() != null
				&& StringUtils.isNotBlank(letterKeywordVO.getKeywordId())) {
			letterKeyword.setLetterKeyWordID(letterKeywordVO.getKeywordId());
		}
		if (letterKeywordVO.getKeywordTypeCode() != null
				&& StringUtils.isNotBlank(letterKeywordVO.getKeywordTypeCode())) {
			letterKeyword.setLetterKeyWordTypeCode(letterKeywordVO
					.getKeywordTypeCode());
		}

		if (letterKeywordVO.getKeywordTypeCode() != null
				&& StringUtils.isNotBlank(letterKeywordVO.getKeywordTypeCode())) {
			
			//System.out.println("111111"+letterKeywordVO.getLabelRequiredIndicator());
			// Following logic changes so that always, in any case, if end user selects 'Input' or '02' then 
			// per the UIS doc, record will be updated for LabelRequiredIndicator setting to 'TRUE'
			if (letterKeywordVO.getKeywordTypeCode().equals("IN")
					|| letterKeywordVO.getKeywordTypeCode().equals("02")
					|| letterKeywordVO.getKeywordTypeCode().equals("2")) {

				letterKeyword.setLabelRequiredIndicator(Boolean.TRUE);
			} else {
				letterKeyword.setLabelRequiredIndicator(Boolean.FALSE);
			}
		}

		// audit update end 03052012
		// letterKeyword.setLabelText(letterKeywordVO.getKeywordLabel());
		// audit update start 03052012
		if (letterKeywordVO.getKeywordLabel() != null
				&& StringUtils.isNotBlank(letterKeywordVO.getKeywordLabel())) {
			letterKeyword.setLabelText(letterKeywordVO.getKeywordLabel());
		}
		// audit update end 03052012
		if (letterKeywordVO.getKeywordLongDesc() != null
				&& StringUtils.isNotBlank(letterKeywordVO.getKeywordLongDesc())) {
			letterKeyword.setLongDescription(letterKeywordVO
					.getKeywordLongDesc());
		}
		if (letterKeywordVO.getKeywordShortDesc() != null
				&& StringUtils
						.isNotBlank(letterKeywordVO.getKeywordShortDesc())) {
			letterKeyword.setShortDescription(letterKeywordVO
					.getKeywordShortDesc());
		}
		/**added as part of CR 827474*/
			if (GlobalLetterConstants.LETTER_DATA_TYPE_CURRENCY.equals(letterKeywordVO.getKeyWordDataTyCd())) {
				letterKeyword.setLetterKeyWordDataTypeCode(GlobalLetterConstants.LETTER_DTTYPE_CD_C);
			}
			if (GlobalLetterConstants.LETTER_DATA_TYPE_DATE.equals(letterKeywordVO.getKeyWordDataTyCd())) {
				letterKeyword.setLetterKeyWordDataTypeCode(GlobalLetterConstants.LETTER_DTTYPE_CD_D);
			}
			if (GlobalLetterConstants.LETTER_DATA_TYPE_OTHER.equals(letterKeywordVO.getKeyWordDataTyCd())) {
				letterKeyword.setLetterKeyWordDataTypeCode(GlobalLetterConstants.LETTER_DTTYPE_CD_O);
			}
		/**added as part of CR 827474*/
		letterKeyword.setVersionNo(letterKeywordVO.getVersionNo());
		letterKeyword.setAuditUserID(letterKeywordVO.getAuditUserID());
		letterKeyword
				.setAddedAuditUserID(letterKeywordVO.getAddedAuditUserID());
		letterKeyword.setAddedAuditTimeStamp(letterKeywordVO
				.getAddedAuditTimeStamp());
		letterKeyword.setAuditTimeStamp(letterKeywordVO.getAuditTimeStamp());
		// letterKeyword.setDisplaySize(letterKeywordVO.getDisplaySize());
		// audit update start 03052012
		if (letterKeywordVO.getDisplaySize() != null
				&& StringUtils.isNotBlank(letterKeywordVO.getDisplaySize())) {
			letterKeyword.setDisplaySize(letterKeywordVO.getDisplaySize());
		}
		// audit update end 03052012
		if (associatedTemplatesAsSelectItems != null) {

			identifyAssociatedLetterTemplates(associatedTemplatesAsSelectItems,
					letterKeywordVO.getAssociatedTemplatesList(), letterKeyword);
		}

		return letterKeyword;

	}

	/**
	 * Method to identify templates to be disassociated
	 * 
	 * @param latestAssociatedLetterTemplates
	 *            : a list containing templates that are to be associated
	 * @param existingAssociatedLetterTemplates
	 *            : a list containing templates that are currently associated
	 * @return List : containing LetterGenerationTemplateParameter domain
	 *         objects
	 */
	public List identifyTemplatesToBeDisassociated(
			List latestAssociatedLetterTemplates,
			List existingAssociatedLetterTemplates) {
		List templatesToBeDisassociated = new ArrayList();
		int existingAssociatedLetterTemplatesSize = existingAssociatedLetterTemplates
				.size();
		int latestAssociatedLetterTemplatesSize = latestAssociatedLetterTemplates
				.size();
		LetterTemplateKeywordAssociationVO existingAssociationVO = null;
		SelectItem currentAssociatedTemplate = null;
		LetterGenerationTemplateParameter param = null;
		for (int i = 0; i < existingAssociatedLetterTemplatesSize; i++) {
			existingAssociationVO = (LetterTemplateKeywordAssociationVO) existingAssociatedLetterTemplates
					.get(i);
			boolean templateAssociated = false;

			for (int j = 0; j < latestAssociatedLetterTemplatesSize; j++) {
				currentAssociatedTemplate = (SelectItem) latestAssociatedLetterTemplates
						.get(j);
				if (currentAssociatedTemplate.getValue().equals(
						existingAssociationVO.getLetterTemplateId())) {
					templateAssociated = true;
					break;
				}
			}

			if (!templateAssociated) {
				param = new LetterGenerationTemplateParameter();
				param.setLetterKeywordID(existingAssociationVO
						.getLetterKeywordId());
				param.setLetterTemplateKeyData(existingAssociationVO
						.getLetterTemplateId());
				templatesToBeDisassociated.add(param);
			}

		}
		return templatesToBeDisassociated;
	}

	/**
	 * Method to identify templates to be associated.
	 * 
	 * @param latestAssociatedLetterTemplates
	 *            : a list containing templates for association
	 * @param existingAssociatedLetterTemplates
	 *            : a list containing existing templates
	 * @param letterKeyword
	 *            : an object(DO) of type LetterGenerationKeyword
	 */
	private void identifyAssociatedLetterTemplates(
			List latestAssociatedLetterTemplates,
			List existingAssociatedLetterTemplates,
			LetterGenerationKeyword letterKeyword) {
		Iterator iterLatestAssociatedLetterTemplates = latestAssociatedLetterTemplates
				.iterator();
		Iterator iterExistingAssociatedLetterTemplates = existingAssociatedLetterTemplates
				.iterator();
		LetterGenerationTemplateParameter letterGenerationTemplateParameter = null;
		SelectItem item = null;
		LetterTemplateKeywordAssociationVO existingAssociationVO = null;
		while (iterLatestAssociatedLetterTemplates.hasNext()) {
			item = (SelectItem) iterLatestAssociatedLetterTemplates.next();

			boolean foundMatch = false;
			while (iterExistingAssociatedLetterTemplates.hasNext()) {
				existingAssociationVO = (LetterTemplateKeywordAssociationVO) iterExistingAssociatedLetterTemplates
						.next();
				if (existingAssociationVO.getLetterTemplateId().equals(
						item.getValue())) {
					foundMatch = true;
					break;
				}
			}

			if (foundMatch) {
				letterGenerationTemplateParameter = this
						.convertLetterTemplateParameterVOToDO(existingAssociationVO);
			} else {
				LetterTemplateKeywordAssociationVO newAssociationVO = new LetterTemplateKeywordAssociationVO();
				newAssociationVO
						.setLetterTemplateId(item.getValue().toString());
				newAssociationVO.setLetterKeywordId(letterKeyword
						.getLetterKeyWordID());
				newAssociationVO.setLetterKeywordUsageCode(letterKeyword
						.getLetterKeyWordTypeCode());
				newAssociationVO.setAddedAuditUserID(letterKeyword
						.getAddedAuditUserID());
				newAssociationVO.setAddedAuditTimeStamp(letterKeyword
						.getAddedAuditTimeStamp());

				newAssociationVO.setAuditUserID(letterKeyword.getAuditUserID());
				newAssociationVO.setAuditTimeStamp(letterKeyword
						.getAuditTimeStamp());

				letterGenerationTemplateParameter = this
						.convertLetterTemplateParameterVOToDO(newAssociationVO);
			}
			letterKeyword
					.addletterTemplateParameters(letterGenerationTemplateParameter);
			foundMatch = false;
		}
	}

	/**
	 * This method is to convert LetterGenerationTemplateParameter to
	 * LetterTemplateKeywordAssociationVO
	 * 
	 * @param letterGenerationTemplateParameter
	 *            : an object of type letterGenerationTemplateParameter
	 * @return LetterTemplateKeywordAssociationVO : a value object
	 */
	private LetterTemplateKeywordAssociationVO convertLetterTemplateParameterDOToVO(
			LetterGenerationTemplateParameter letterGenerationTemplateParameter) {
		LetterTemplateKeywordAssociationVO letterTemplateKeywordAssociationVO = new LetterTemplateKeywordAssociationVO();

		if (letterGenerationTemplateParameter.getLetterTemplate() != null) {
			letterTemplateKeywordAssociationVO
					.setLetterTemplateId(letterGenerationTemplateParameter
							.getLetterTemplate().getLetterTemplateKeyData());
			letterTemplateKeywordAssociationVO
					.setLetterTemplateName(letterGenerationTemplateParameter
							.getLetterTemplate().getName());
		}
		if (letterGenerationTemplateParameter.getLetterGenerationKeyword() != null) {
			letterTemplateKeywordAssociationVO
					.setLetterKeywordId(letterGenerationTemplateParameter
							.getLetterGenerationKeyword().getLetterKeyWordID());
			letterTemplateKeywordAssociationVO
					.setLetterKeywordDisplayName(letterGenerationTemplateParameter
							.getLetterGenerationKeyword().getLetterKeyWordID());
		}
		letterTemplateKeywordAssociationVO
				.setLetterKeywordUsageCode(letterGenerationTemplateParameter
						.getLetterGenerationKeywordUsageCode());
		letterTemplateKeywordAssociationVO
				.setVersionNo(letterGenerationTemplateParameter.getVersionNo());
		letterTemplateKeywordAssociationVO
				.setAddedAuditTimeStamp(letterGenerationTemplateParameter
						.getAddedAuditTimeStamp());
		letterTemplateKeywordAssociationVO
				.setAddedAuditUserID(letterGenerationTemplateParameter
						.getAddedAuditUserID());
		letterTemplateKeywordAssociationVO
				.setAuditTimeStamp(letterGenerationTemplateParameter
						.getAuditTimeStamp());
		letterTemplateKeywordAssociationVO
				.setAuditUserID(letterGenerationTemplateParameter
						.getAuditUserID());

		return letterTemplateKeywordAssociationVO;
	}

	/**
	 * This method is to convert LetterTemplateKeywordAssociationVO to
	 * LetterGenerationTemplateParameter
	 * 
	 * @param letterTemplateKeywordAssociationVO
	 *            : an object of type LetterTemplateKeywordAssociationVO
	 * @return LetterGenerationTemplateParameter : a domain object
	 */
	private LetterGenerationTemplateParameter convertLetterTemplateParameterVOToDO(
			LetterTemplateKeywordAssociationVO letterTemplateKeywordAssociationVO) {
		LetterGenerationTemplateParameter letterGenerationTemplateParameter = new LetterGenerationTemplateParameter();

		if (letterTemplateKeywordAssociationVO.getLetterKeywordId() != null) {
			LetterGenerationKeyword letterKeyword = new LetterGenerationKeyword();
			letterKeyword.setLetterKeyWordID(letterTemplateKeywordAssociationVO
					.getLetterKeywordId());
			letterGenerationTemplateParameter
					.setLetterGenerationKeyword(letterKeyword);
		}
		if (letterTemplateKeywordAssociationVO.getLetterTemplateId() != null) {
			LetterTemplate letterTemplate = new LetterTemplate();
			letterTemplate
					.setLetterTemplateKeyData(letterTemplateKeywordAssociationVO
							.getLetterTemplateId());
			letterGenerationTemplateParameter.setLetterTemplate(letterTemplate);
		}
		letterGenerationTemplateParameter
				.setLetterTemplateKeyData(letterTemplateKeywordAssociationVO
						.getLetterTemplateId());
		letterGenerationTemplateParameter
				.setLetterKeywordID(letterTemplateKeywordAssociationVO
						.getLetterKeywordId());
		letterGenerationTemplateParameter
				.setLetterGenerationKeywordUsageCode(letterTemplateKeywordAssociationVO
						.getLetterKeywordUsageCode());
		letterGenerationTemplateParameter
				.setVersionNo(letterTemplateKeywordAssociationVO.getVersionNo());

		letterGenerationTemplateParameter
				.setAddedAuditTimeStamp(letterTemplateKeywordAssociationVO
						.getAddedAuditTimeStamp());
		letterGenerationTemplateParameter
				.setAddedAuditUserID(letterTemplateKeywordAssociationVO
						.getAddedAuditUserID());
		letterGenerationTemplateParameter
				.setAuditTimeStamp(letterTemplateKeywordAssociationVO
						.getAuditTimeStamp());
		letterGenerationTemplateParameter
				.setAuditUserID(letterTemplateKeywordAssociationVO
						.getAuditUserID());

		return letterGenerationTemplateParameter;
	}

}
