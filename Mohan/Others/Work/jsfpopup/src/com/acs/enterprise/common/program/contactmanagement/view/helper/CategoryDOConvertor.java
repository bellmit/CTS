/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;



import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CallScript;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CategorySubjectXref;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategoryTemplate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldAssignment;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldScope;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldTable;
import com.acs.enterprise.common.program.contactmanagement.common.domain.SubjectAuxillary;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CategoryControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryCustomFieldsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryLetterTemplateVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategorySubjectVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro This class will be used for converting CategoryVO to
 *         CorrespondenceCategory domain object and vice versa.
 */
/**
 * @author chiedre
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * @author chiedre
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CategoryDOConvertor extends DataTransferObjectConverter {
	/** Enterprise Logger for Logging. */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CategoryDOConvertor.class);

	/**
	 * Constructor for CategoryDOConvertor.
	 */
	public CategoryDOConvertor() {
		super();
	}

	/**
	 * This method is used to convert Category Value Object to
	 * CorrespondenceCategory Domain object.
	 * 
	 * @param categoryVO :
	 *            CategoryVO Value Object
	 * @return CorrespondenceCategory : CorrespondenceCategory Domain Object
	 */
	public CorrespondenceCategory convertCategoryVOToDO(CategoryVO categoryVO) {
		
		CorrespondenceCategory categoryDO = new CorrespondenceCategory();

		categoryDO.setCategorySK(categoryVO.getCategorySK());
		categoryDO.setDescription(categoryVO.getCategoryDesc());
		if (categoryVO.getActiveIndicator().equalsIgnoreCase(
				ContactManagementConstants.YES)) {
			categoryDO.setVoidIndicator(Boolean.FALSE);
		} else {
			categoryDO.setVoidIndicator(Boolean.TRUE);


		}
		if (categoryVO.getSupervisorAppReqInd().equalsIgnoreCase(
				ContactManagementConstants.YES)) {
			categoryDO.setSupervisorReviewReqIndicator(Boolean.TRUE);
		} else {
			categoryDO.setSupervisorReviewReqIndicator(Boolean.FALSE);
		}

		if (!isNullOrEmptyField(categoryVO.getPriority())) {
			categoryDO.setPriorityCode(categoryVO.getPriority());
		}

		if (!isNullOrEmptyField(categoryVO.getNumOfDaysToKeep())) {
			categoryDO.setCategoryDeletionDays(Integer.valueOf(categoryVO
					.getNumOfDaysToKeep()));
		}
		if (!isNullOrEmptyField(categoryVO.getNumOfDaysBeforeEscToMed())) {
			categoryDO.setCategoryPriorityDay1(Integer.valueOf(categoryVO
					.getNumOfDaysBeforeEscToMed()));
		}
		if (!isNullOrEmptyField(categoryVO.getNumOfDaysBeforeEscToHigh())) {
			categoryDO.setCategoryPriorityDay2(Integer.valueOf(categoryVO
					.getNumOfDaysBeforeEscToHigh()));
		}
		if (!isNullOrEmptyField(categoryVO.getNumOfDaysBeforeEscToUrg())) {
			categoryDO.setCategoryPriorityDay3(Integer.valueOf(categoryVO
					.getNumOfDaysBeforeEscToUrg()));
		}

		Set setOfCatSubjXrefs = getCategorySubjectXrefsSet(categoryVO
				.getListOfSubjects(), categoryDO);

		Set setOfCustFldsAssignments = getCustomFieldAssignmentSet(categoryVO
				.getListOfCustomFields(), categoryDO);

		Set setOfCatTemplates = getCategoryTemplatesSet(categoryVO
				.getListOfTemplates(), categoryDO);
		categoryDO.setCatSubjXrefs(setOfCatSubjXrefs);

		categoryDO.setCustomFields(setOfCustFldsAssignments);
		// ESPRD00061734 Defect
		categoryDO.setTempCustomFieldsAssignments(setOfCustFldsAssignments);

		categoryDO.setCategoryTemplates(setOfCatTemplates);
		if(logger.isDebugEnabled())
		{
			logger.debug("Tempaltes Size fromCatDo :" + categoryDO.getCategoryTemplates().size());
		}

		if (categoryVO.getCallScriptSK() != null) {
			CallScript callScript = new CallScript();
			callScript.setCallScriptSK(categoryVO.getCallScriptSK());
			categoryDO.setCallScript(callScript);
		}

		if (categoryVO.getWorkUnitSK() != null) {
			WorkUnit workUnit = new WorkUnit();
			workUnit.setWorkUnitSK(categoryVO.getWorkUnitSK());
			categoryDO.setDefaultRouteToWorkUnit(workUnit);
		}
		if (categoryVO.getCategoryTypeCode() != null) {
			categoryDO.setCategoryTypeCode(categoryVO.getCategoryTypeCode());
		}

		Map mapOfDeletedObjects = categoryDO.getDeletedObjects();

		Set setOfDeletedCatSubjXrefs = getCategorySubjectXrefsSet(categoryVO
				.getListOfDeletedSubjects(), categoryDO);


		Set setOfDeletedCustFldsAssignments = getCustomFieldAssignmentSet(
				categoryVO.getListOfDeletedCustomFields(), categoryDO);


		Set setOfDeletedCatTemplates = getCategoryTemplatesSet(categoryVO
				.getListOfDeletedTemplates(), categoryDO);


		if(logger.isDebugEnabled())
		{
			logger.debug("setOfDeletedCatTemplates size in Converter:"	+ setOfDeletedCatTemplates.size());
		}

		mapOfDeletedObjects.put(ContactManagementConstants.SUBJECT,
				setOfDeletedCatSubjXrefs);
		mapOfDeletedObjects.put(ContactManagementConstants.CUSTOM_FIELD,
				setOfDeletedCustFldsAssignments);
		mapOfDeletedObjects.put("Templates", setOfDeletedCatTemplates);









		categoryDO.setVersionNo(categoryVO.getVersionNo());
		categoryDO.setAuditUserID(getLoggedInUserID());
		categoryDO.setAddedAuditUserID(getLoggedInUserID());

		return categoryDO;
	}

	/**
	 * This method is used to get the Category Subject X ref Domain objects.
	 * 
	 * @param listOfSubjects :
	 *            List of Subjects.
	 * @param categoryDO :
	 *            CorrespondenceCategory Domain Object.
	 * @return Set : Set of CategorySubjectXref Objects.
	 */
	private Set getCategorySubjectXrefsSet(List listOfSubjects,
			CorrespondenceCategory categoryDO) {
		
		if (listOfSubjects == null) {
			return null;
		}

		Set setOfCatSubjXrefs = new HashSet(
				ContactManagementConstants.DEFAULT_SIZE);

		for (Iterator iter = listOfSubjects.iterator(); iter.hasNext();) {
			CategorySubjectVO catSubjVO = (CategorySubjectVO) iter.next();

			CategorySubjectXref catSubjXref = new CategorySubjectXref();

			SubjectAuxillary subjectAux = new SubjectAuxillary();
			subjectAux.setSubjectCode(catSubjVO.getSubjectCode());

			catSubjXref.setCategory(categoryDO);
			/*
			 * FOR DEFECT ID :: ESPRD00061189, Desc :: Association of subjects
			 * to new category
			 */
			catSubjXref.setSubjectAuxillary(subjectAux);
			/*
			 * FOR DEFECT ID :: ESPRD00061734, Desc :: Association of subjects
			 * to new category
			 */
			if(catSubjVO.getAuditUserID() != null)
			{
				catSubjXref.setAuditUserID(catSubjVO.getAuditUserID());
			}else
			{
				catSubjXref.setAuditUserID(getLoggedInUserID());
			}
			catSubjXref.setAddedAuditUserID(getLoggedInUserID());
			catSubjXref.setVersionNo(catSubjVO.getVersionNo());
			  if (catSubjVO.getAuditTimeStamp() == null)
	            {
			      catSubjXref.setAuditTimeStamp(new Date());
	            }
	            else
	            {
	                catSubjXref.setAuditTimeStamp(catSubjVO.getAuditTimeStamp());
	            }
	           
						
			setOfCatSubjXrefs.add(catSubjXref);

		}

		return setOfCatSubjXrefs;
	}

	/**
	 * This method is used to get the set of Custom Field Assignment Domain
	 * objects.
	 * 
	 * @param listOfCustomFields :
	 *            List of Custom Fields.
	 * @param categoryDO :
	 *            CorrespondenceCategory Domain Object.
	 * @return Set : Set of CustomFieldAssignment Objects.
	 */
	private Set getCustomFieldAssignmentSet(List listOfCustomFields,
			CorrespondenceCategory categoryDO) {
		
		if (listOfCustomFields == null) {
			return null;
		}

		Set setOfCustomFieldAssignment = new HashSet(
				ContactManagementConstants.DEFAULT_SIZE);

		for (Iterator iter = listOfCustomFields.iterator(); iter.hasNext();) {
			CategoryCustomFieldsVO catCustomFieldsVO = (CategoryCustomFieldsVO) iter
					.next();
			CustomFieldAssignment customFieldAssignment = getCustomFieldAssignment(
					categoryDO, catCustomFieldsVO);

			setOfCustomFieldAssignment.add(customFieldAssignment);
		}

		return setOfCustomFieldAssignment;
	}

	/**
	 * This method is used to get the set of Custom Field Assignment Domain
	 * objects.
	 * 
	 * @param listOfTemplates :
	 *            List of Templates.
	 * @param categoryDO :
	 *            CorrespondenceCategory Domain Object.
	 * @return Set : Set of CustomFieldAssignment Objects.
	 */
	private Set getCategoryTemplatesSet(List listOfTemplates,
			CorrespondenceCategory categoryDO) {
		Set setOfTemplates = new HashSet(
				ContactManagementConstants.DEFAULT_SIZE);
		for (Iterator iter = listOfTemplates.iterator(); iter.hasNext();) {
			CategoryLetterTemplateVO catTemplateVO = (CategoryLetterTemplateVO) iter
					.next();
			CorrespondenceCategoryTemplate categoryTemplate = getCategoryTemplate(
					categoryDO, catTemplateVO);
			setOfTemplates.add(categoryTemplate);
		}
		if(logger.isDebugEnabled())
		{
			logger.debug("setOfTemplates size :" + setOfTemplates.size());
		}
		return setOfTemplates;
	}




























































	/**
	 * @param categoryDO
	 *            categoryDO to set
	 * @param catCustomFieldsVO
	 *            catCustomFieldsVO to set
	 * @return customFieldAssignment
	 */
	private CustomFieldAssignment getCustomFieldAssignment(
			CorrespondenceCategory categoryDO,
			CategoryCustomFieldsVO catCustomFieldsVO) {
		CustomFieldAssignment customFieldAssignment = new CustomFieldAssignment();
		CustomFieldScope customFieldScope = new CustomFieldScope();

		//  Generating CustomFieldAssignment Object

		customFieldAssignment.setCorrespondenceCategory(categoryDO);
		customFieldAssignment.setSubjectAreaSK(categoryDO.getCategorySK());

		CustomField customField = new CustomField();
		customField.setCustomFieldSK(catCustomFieldsVO.getCustomFieldSK());
		customFieldScope.setCustomFieldSK(catCustomFieldsVO.getCustomFieldSK());
		customFieldScope.setCustomField(customField);

		CustomFieldTable customFieldTable = new CustomFieldTable();
		/*
		 * Change done for ES2 DO changes
		 */
		customFieldTable.setTableName(ContactManagementConstants.G_CR_TB);
		customFieldScope.setTableName(ContactManagementConstants.G_CR_TB);
		customFieldScope.setCustomFieldTable(customFieldTable);

		customFieldAssignment.setCustomFieldScope(customFieldScope);
		if(logger.isInfoEnabled())
		{
			logger.info("catCustomFieldsVO.getCustomFieldSK()" + catCustomFieldsVO.getCustomFieldSK());
		}
		customFieldAssignment.setCustomFieldSK(catCustomFieldsVO
				.getCustomFieldSK());
		customFieldAssignment.setTableName(ContactManagementConstants.G_CR_TB);

		customFieldAssignment.setDisplaySortOrderNum(Integer.valueOf("1"));
		customFieldAssignment.setModifyAfterSaveIndicator(Boolean
				.valueOf(catCustomFieldsVO.isCfProtectedOnSave()));
		customFieldAssignment.setRequiredIndicator(Boolean
				.valueOf(catCustomFieldsVO.isCfRequired()));

		customFieldAssignment.setAuditUserID(getLoggedInUserID());
		customFieldAssignment.setAddedAuditUserID(getLoggedInUserID());
		if(logger.isInfoEnabled())
		{
			logger.info("catCustomFieldsVO.getVersionNo() "	+ catCustomFieldsVO.getVersionNo());
		}
		customFieldAssignment.setVersionNo(catCustomFieldsVO.getVersionNo());

		return customFieldAssignment;
	}

	/**
	 * @param categoryDO :
	 *            takes the parameter CategoryDO
	 * @param categoryTemplateVO :
	 *            takes the parameter categoryTemplateVO
	 * @return CorrespondenceCategoryTemplate : returns the
	 *         CorrespondenceCategoryTemplate Object.
	 */
	private CorrespondenceCategoryTemplate getCategoryTemplate(
			CorrespondenceCategory categoryDO,
			CategoryLetterTemplateVO categoryTemplateVO) {
		CorrespondenceCategoryTemplate categoryTemplate = new CorrespondenceCategoryTemplate();
		categoryTemplate.setCategorySK(categoryDO.getCategorySK());
		categoryTemplate.setCategory(categoryDO);
		/*
		 * template.setLetterTemplateKeyData(categoryTemplateVO.getLetterTemplateKeyData());
		 * categoryTemplate.setLetterTemplate(template);
		 */
		if(logger.isDebugEnabled())
		{
			logger.debug("LetterTempalteKeyData in Converterwhile saving FromVo" + categoryTemplateVO.getLetterTemplateKeyData());
		}
		categoryTemplate.setLetterTemplateKeyData(categoryTemplateVO
				.getLetterTemplateKeyData());
		if(logger.isDebugEnabled())
		{
			logger.debug("LetterTempalteKeyData in Converterwhile saving:"+ categoryTemplate.getLetterTemplateKeyData());
			logger.debug("categoryTemplate.getCategorySK in Converter:"	+ categoryTemplate.getCategorySK());
		}
		categoryTemplate.setVersionNo(categoryTemplateVO.getVersionNo());
		if(logger.isDebugEnabled())
		{
			logger.debug("Version inGetCategoryTemplate of Converter" + categoryTemplate.getVersionNo());
		}
		categoryTemplate.setAuditUserID(getLoggedInUserID());
		categoryTemplate.setAddedAuditUserID(getLoggedInUserID());

		return categoryTemplate;
	}

	/**
	 * This method is used to convert CorrespondenceCategory Domain Object to
	 * Category Value object.
	 * 
	 * @param categoryDO :
	 *            CorrespondenceCategory Domain Object.
	 * @return CategoryVO : CategoryVO Value Object.
	 */
	public CategoryVO convertCategoryDOToVO(CorrespondenceCategory categoryDO) {
		CategoryVO categoryVO = new CategoryVO();
		convertCommonCategoryDOToVO(categoryVO, categoryDO);
		setLstofTmplates(categoryVO, categoryDO);

		return categoryVO;
	}
	
	
	/**
	 * 
	 * This method is used to convert CorrespondenceCategory Domain Object to
	 * Category Value object.
	 * 
	 * @param categoryDO :
	 *            CorrespondenceCategory Domain Object.
	 * @return CategoryVO : CategoryVO Value Object.
	 */
	public CategoryVO convertCategoryDOToVOForCategory(CorrespondenceCategory categoryDO) {
		CategoryVO categoryVO = new CategoryVO();
		convertCommonCategoryDOToVO(categoryVO, categoryDO);

		List listOfSubjects = getListOfSubjects(categoryDO);
		categoryVO.setListOfSubjects(listOfSubjects);
		List listOfCustomFields = getListOfCustomFields(categoryDO);
		categoryVO.setListOfCustomFields(listOfCustomFields);
		setLstofTmplates(categoryVO, categoryDO);

		return categoryVO;
	}

	public CategoryVO convertCategoryDOToVOForCatList(
			CorrespondenceCategory categoryDO) {
		CategoryVO categoryVO = new CategoryVO();
		if (categoryDO == null) {
			return categoryVO;
		}
		categoryVO.setCategorySK(categoryDO.getCategorySK());
		categoryVO.setCategoryDesc(categoryDO.getDescription());
		return categoryVO;
	}

	public CategoryVO convertCommonCategoryDOToVO(CategoryVO categoryVO,
			CorrespondenceCategory categoryDO) {
		
		if (categoryDO == null) {
			return categoryVO;
		}

		categoryVO.setCategorySK(categoryDO.getCategorySK());
		categoryVO.setCategoryDesc(categoryDO.getDescription());

		if (categoryDO.getVoidIndicator().booleanValue()) {
			categoryVO.setActiveIndicator(ContactManagementConstants.NO);
		} else {
			categoryVO.setActiveIndicator(ContactManagementConstants.YES);


		}

		if (categoryDO.getVoidIndicator().booleanValue()) {
			categoryVO.setInactive(true);
		} else {
			categoryVO.setInactive(false);


		}

		if (categoryDO.getSupervisorReviewReqIndicator().booleanValue()) {
			categoryVO.setSupervisorAppReqInd(ContactManagementConstants.YES);
		} else {
			categoryVO.setSupervisorAppReqInd(ContactManagementConstants.NO);
		}

		categoryVO.setPriority(categoryDO.getPriorityCode());

		if (categoryDO.getCategoryDeletionDays() != null) {
			categoryVO.setNumOfDaysToKeep(categoryDO.getCategoryDeletionDays()
					.toString());
		}
		if (categoryDO.getCategoryPriorityDay1() != null) {
			categoryVO.setNumOfDaysBeforeEscToMed(categoryDO
					.getCategoryPriorityDay1().toString());
		}
		if (categoryDO.getCategoryPriorityDay2() != null) {
			categoryVO.setNumOfDaysBeforeEscToHigh(categoryDO
					.getCategoryPriorityDay2().toString());
		}
		if (categoryDO.getCategoryPriorityDay3() != null) {
			categoryVO.setNumOfDaysBeforeEscToUrg(categoryDO
					.getCategoryPriorityDay3().toString());
		}

		if (categoryDO.getCallScript() != null) {
			categoryVO.setCallScriptSK(categoryDO.getCallScript()
					.getCallScriptSK());
		}
		if (categoryDO.getDefaultRouteToWorkUnit() != null) {
			categoryVO.setCallScriptSK(categoryDO.getDefaultRouteToWorkUnit()
					.getWorkUnitSK());
		}
		if (categoryDO.getCategoryTypeCode() != null) {
			categoryVO.setCategoryTypeCode(categoryDO.getCategoryTypeCode());
		} else {
			categoryVO
					.setCategoryTypeCode(ContactManagementConstants.EMPTY_STRING);
		}

		categoryVO.setVersionNo(categoryDO.getVersionNo());

		/*// DEFECT ID : ESPRD00231724
		List listOfSubjects = getListOfSubjects(categoryDO);
		categoryVO.setListOfSubjects(listOfSubjects);
		List listOfCustomFields = getListOfCustomFields(categoryDO);
		categoryVO.setListOfCustomFields(listOfCustomFields);*/

		categoryVO.setAddedAuditTimeStamp(categoryDO.getAddedAuditTimeStamp());
		categoryVO.setAddedAuditUserID(categoryDO.getAddedAuditUserID());
		categoryVO.setAuditTimeStamp(categoryDO.getAuditTimeStamp());
		categoryVO.setAuditUserID(categoryDO.getAuditUserID());
        
		createVOAuditKeysList(categoryDO,categoryVO);

		return categoryVO;
	}

	 private void createVOAuditKeysList(EnterpriseBaseDomain enterpriseBaseDomain,AuditaleEnterpriseBaseVO  auditaleEnterpriseBaseVO){
    	
    	  List fullAuditList = new ArrayList();
    	  
    	  if(auditaleEnterpriseBaseVO.getAuditKeyList()!= null){
    	  	fullAuditList= auditaleEnterpriseBaseVO.getAuditKeyList();
    	  }
          
    	  
    	  
           LineItemAuditsDelegate auditDelegate;
    	try {
    		auditDelegate = new LineItemAuditsDelegate();
    		List resultList = auditDelegate.getAuditKeyList(enterpriseBaseDomain);
           
    	       if(resultList!=null){
    	       	fullAuditList.addAll(resultList);
    	       	if(logger.isDebugEnabled())
    	       	{
    	       		logger.debug("======LineItemAuditsDelegate====resultList====Size======="+resultList.size());
    	       	}
    	       	auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);
    	       }
    	} catch (LineItemAuditsBusinessException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
          
     
    }
































	public CategoryVO convertLogCorrCategoryDOToVO(
			CorrespondenceCategory categoryDO) {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO = convertCommonCategoryDOToVO(categoryVO, categoryDO);
		List listOfTemplates = new ArrayList();
		categoryVO.setListOfTemplates(listOfTemplates);
		return categoryVO;
	}

	public CategoryVO setLstofTmplates(CategoryVO categoryVO,
			CorrespondenceCategory categoryDO) {
		List listOfTemplates = getListOfTemplates(categoryDO);
		if(logger.isDebugEnabled())
		{
		logger.debug("List of templates size in Converter:"	+ listOfTemplates.size());
		}
		categoryVO.setListOfTemplates(listOfTemplates);



		return categoryVO;
	}

	/**
	 * This method is used to get the List of Subjects in the CategoryVO.
	 * 
	 * @param categoryDO :
	 *            CorrespondenceCategory object to get the subjects from.
	 * @return List : List of Subjects
	 */
	private List getListOfSubjects(CorrespondenceCategory categoryDO) {
		
		Set setOfCatSubjXrefs = categoryDO.getCatSubjXrefs();
		List listOfSubjects = new ArrayList();

		if (setOfCatSubjXrefs == null) {
			return listOfSubjects;
		}

		for (Iterator iter = setOfCatSubjXrefs.iterator(); iter.hasNext();) {
			CategorySubjectXref catSubjXref = (CategorySubjectXref) iter.next();

			CategorySubjectVO catSubjVO = new CategorySubjectVO();

			//catSubjVO.setSubjectDesc(); Will be set in the UI layer
			catSubjVO.setIncludeIndicator("Yes");
			catSubjVO.setIncludeIndicatorforimage(true);
			catSubjVO.setSubjectCode(catSubjXref.getSubjectAuxillary()
					.getSubjectCode());
			catSubjVO.setAddedAuditTimeStamp(catSubjXref
					.getAddedAuditTimeStamp());
			catSubjVO.setAddedAuditUserID(catSubjXref.getAddedAuditUserID());
			catSubjVO.setAuditTimeStamp(catSubjXref.getAuditTimeStamp());
			catSubjVO.setAuditUserID(catSubjXref.getAuditUserID());
			catSubjVO.setVersionNo(catSubjXref.getVersionNo());

			createVOAuditKeysList(catSubjXref, catSubjVO);

			listOfSubjects.add(catSubjVO);
		}

		return listOfSubjects;
	}

	/**
	 * This method is used to get the List of Custom Fields in the CategoryVO.
	 * 
	 * @param categoryDO :
	 *            CorrespondenceCategory object to get the subjects from.
	 * @return List : List of Custom Fields.
	 */
	private List getListOfCustomFields(CorrespondenceCategory categoryDO) {
		
		Set setOfCustFldsAssignments = categoryDO.getCustomFields();
		List listOfCustomFields = new ArrayList();

		if (setOfCustFldsAssignments == null) {
			return listOfCustomFields;
		}

		for (Iterator iter = setOfCustFldsAssignments.iterator(); iter
				.hasNext();) {
			CustomFieldAssignment customFieldAssignment = (CustomFieldAssignment) iter
					.next();

			CategoryCustomFieldsVO catCustomFieldsVO = new CategoryCustomFieldsVO();
			//Below code is modified for  changing the activeIncludeIndicator variable to String type for the Defect ESPRD00802214.
			/*catCustomFieldsVO.setIncludeIndicator(true);*/
			catCustomFieldsVO.setIncludeIndicator(ContactManagementConstants.YES);
			catCustomFieldsVO.setIncludeIndicatorImage(true);
			catCustomFieldsVO.setCfProtectedOnSave(customFieldAssignment
					.getModifyAfterSaveIndicator().booleanValue());
			catCustomFieldsVO.setCfRequired(customFieldAssignment
					.getRequiredIndicator().booleanValue());
			// catCustomFieldsVO.setColumnDesc() - not required.
			
			catCustomFieldsVO.setCustomFieldSK(customFieldAssignment
					.getCustomFieldSK());
			//catCustomFieldsVO.setDataType() - not required.
			// catCustomFieldsVO.setFieldLength() - not required.
			catCustomFieldsVO.setAuditUserID(customFieldAssignment
					.getAuditUserID());
			catCustomFieldsVO.setAddedAuditUserID(customFieldAssignment
					.getAddedAuditUserID());

			catCustomFieldsVO
					.setVersionNo(customFieldAssignment.getVersionNo());
			catCustomFieldsVO.setDbRecord(true);
			 createVOAuditKeysList(customFieldAssignment,catCustomFieldsVO );
			listOfCustomFields.add(catCustomFieldsVO);
		}
		

		return listOfCustomFields;
	}

	/**
	 * This method is used to get the list of Template
	 * 
	 * @param categoryDO :
	 *            Takes the parameter categoryDO
	 * @return List of Template
	 */
	private List getListOfTemplates(CorrespondenceCategory categoryDO) {
		
		Set setOfTemplateAssignments = categoryDO.getCategoryTemplates();
		
		/*logger.debug("setOfTemplates size form categoryDO:"
				+ setOfTemplateAssignments.size());*/ // find bug issue.
		
		List listOfTemplates = new ArrayList();


		if (setOfTemplateAssignments == null) {
			return listOfTemplates;
		}

		for (Iterator iter = setOfTemplateAssignments.iterator(); iter
				.hasNext();) {
			CorrespondenceCategoryTemplate categoryTemplate = (CorrespondenceCategoryTemplate) iter
					.next();

			CategoryLetterTemplateVO categoryTemplateVO = new CategoryLetterTemplateVO();
			categoryTemplateVO.setIncludeTemplate(true);

			categoryTemplateVO.setLetterTemplateKeyData(categoryTemplate
					.getLetterTemplateKeyData());

			if (categoryTemplate.getCategorySK() != null) {
				
				categoryTemplateVO.setCategorySK(categoryTemplate
						.getCategorySK());
			}

			categoryTemplateVO.setVersionNo(categoryTemplate.getVersionNo());
			
			categoryTemplateVO.setAddedAuditUserID(categoryTemplate
					.getAddedAuditUserID());
			categoryTemplateVO
					.setAuditUserID(categoryTemplate.getAuditUserID());
			listOfTemplates.add(categoryTemplateVO);
			
			// Defect ESPRD00674584

			createVOAuditKeysList(categoryTemplate,categoryTemplateVO );
		}



		return listOfTemplates;

	}

	/**
	 * This method is used to get the User ID.
	 * 
	 * @return String : User ID
	 */
	/*public String getUserID() {
		logger.info("getUserID");

		String userId = ContactManagementConstants.TEST_USERID;

		CategoryControllerBean categoryControllerBean = new CategoryControllerBean();

		HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderResponse = null;

		EnterpriseUserProfile eup = categoryControllerBean.getUserData(
				renderRequest, renderResponse);

		if (eup != null && !isNullOrEmptyField(eup.getUserId())) {
			userId = eup.getUserId();
		}

		return userId;
	}*/
	
	public String getLoggedInUserID() {
		String userId = null;
		Principal principal = null ;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		if(portletRequest != null){
			
		 principal= portletRequest.getUserPrincipal();
		}
		if (principal == null) {
			userId = "guestUser";
		}
		else{
			userId = principal.getName();
		}
		
		return userId;
	}

	/**
	 * This method is used to check if the input field is null or is equal to an
	 * empty string.
	 * 
	 * @param inputField :
	 *            String inputField
	 * @return boolean : true if input field is null or equal to an empty string
	 *         else false
	 */
	private boolean isNullOrEmptyField(String inputField) {
		
		return (inputField == null || ContactManagementConstants.EMPTY_STRING
				.equalsIgnoreCase(inputField.trim()));
	}

	/**
	 * This operation is used to get the reference data for County Code Drop
	 * Down .
	 * 
	 * @return List
	 */
	public List getBusiUnitReferenceData() {
		

		List referenceList = new ArrayList();
		Map referenceMap = null;

		int referenceListSize = 0;
		List categoryCodeList = new ArrayList();

		ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
		ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
		ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

		InputCriteria inputCriteriaEntityTyp = new InputCriteria();

		inputCriteriaEntityTyp
				.setFunctionalArea(FunctionalAreaConstants.GENERAL);
		inputCriteriaEntityTyp
				.setElementName(ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_CD);

		referenceList.add(inputCriteriaEntityTyp);

		referenceDataSearch.setInputList(referenceList);

		try {
			referenceDataListVO = referenceServiceDelegate
					.getReferenceData(referenceDataSearch);

			referenceMap = referenceDataListVO.getResponseMap();

			referenceList = (List) referenceMap
					.get(FunctionalAreaConstants.GENERAL
							+ ProgramConstants.HASH_SEPARATOR
							+ ReferenceServiceDataConstants.G_BUSN_UNIT_CAT_CD);
			referenceListSize = referenceList.size();
			for (int i = 0; i < referenceListSize; i++) {
				ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
						.get(i);
				
				String codesAndDesc = refVo.getValidValueCode() + "-"
						+ refVo.getShortDescription();
				categoryCodeList.add(new SelectItem(refVo.getValidValueCode(),
						codesAndDesc));
				

			}

		} catch (ReferenceServiceRequestException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		} catch (SystemListNotFoundException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		}
		return categoryCodeList;
	}
}
