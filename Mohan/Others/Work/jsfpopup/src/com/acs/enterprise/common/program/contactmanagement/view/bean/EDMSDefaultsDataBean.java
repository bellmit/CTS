/*
 * Created on Oct 12, 2007 Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;


import com.acs.enterprise.common.base.common.vo.EnterpriseBaseSearchCriteriaVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;

import com.acs.enterprise.common.logging.common.domain.AuditLog;
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
import com.acs.enterprise.common.program.contactmanagement.application.exception.CaseTypeFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CategoryFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseTypeSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CategorySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CategoryDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.EDMSDefaultsDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.EDMSDefaultsVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro This is a controller class used for EDMS Defaults related
 *         functionality functionality in the presentation layer.
 */

public class EDMSDefaultsDataBean
        extends EnterpriseBaseDataBean
{
    

    /**
     * List to hold values of subjects valid values.
     */
    private List subjectValidValues = new ArrayList();
    
    /**
     * List to hold values of routeToList.
     */
    private List routeToList = new ArrayList();
    
    /**
     * List to hold values of caseRouteToList.
     */
    private List caseRouteToList = new ArrayList();
    
    
    private int itemsPerPage = 10;
    
    private boolean auditLogFlag = false;
    
    /**
     * List to hold Case type values.
     */
    private List caseTypes = new ArrayList();
    
    private AuditLog selectedAuditLog;

    private boolean saveFlag;
  //for Fixing defect:ESPRD00584127 
    private boolean ascendingOrder;
    
    private String columnName;
    
    private int edmsStartIndex=0;
    
    /**
	 * @return the edmsStartIndex
	 */
	public int getEdmsStartIndex() {
		return edmsStartIndex;
	}

	/**
	 * @param edmsStartIndex the edmsStartIndex to set
	 */
	public void setEdmsStartIndex(int edmsStartIndex) {
		this.edmsStartIndex = edmsStartIndex;
	}

	/**
	 * @return the ascendingOrder
	 */
	public boolean isAscendingOrder() {
		return ascendingOrder;
	}

	/**
	 * @param ascendingOrder the ascendingOrder to set
	 */
	public void setAscendingOrder(boolean ascendingOrder) {
		this.ascendingOrder = ascendingOrder;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

    /**
     * List to hold values of edms Doc Type Code List.
     */
    private List edmsDocTypeCodeList = new ArrayList();

    /**
     * @return Returns the departmentsList.
     */

    public List getDepartmentsList()
    {
        return departmentsList;
    }

    /**
     * @param departmentsList
     *            The departmentsList to set.
     */
    public void setDepartmentsList(List departmentsList)
    {
        this.departmentsList = departmentsList;
    }

    /**
     * @return Returns the routeToList.
     */
    public List getRouteToList()
    {
        return routeToList;
    }

    /**
     * @param routeToList
     *            The routeToList to set.
     */
    public void setRouteToList(List routeToList)
    {
        this.routeToList = routeToList;
    }

    /**
     * List to hold values of subjects valid values.
     */
    private List departmentsList = new ArrayList();

    /**
     * List to hold values of subjects valid values.
     */
    private List categorySubjectValues = new ArrayList();

    /**
     * List to hold Status code valid values.
     */
    private List statCodeValidValues = new ArrayList();

    /**
     * List to hold categories list.
     */
    private List categoryList = new ArrayList();

    /**
     * List to hold Edms valid values list.
     */
    private List edmsValidValues = new ArrayList();

    /** Enterprise Logger for Logging */
    private static EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(EDMSDefaultsDataBean.class);

    /**
     * This field is used to render case or CR
     */
    private String renderCaseOrCr = ContactManagementConstants.EMPTY_STRING;

    /**
     * @param renderCaseOrCr
     *            The renderCaseOrCr to set.
     */
    public void setRenderCaseOrCr(String renderCaseOrCr)
    {
        this.renderCaseOrCr = renderCaseOrCr;
    }
    
    private boolean auditColumnHistoryRender;
    
    public void setAuditColumnHistoryRender(boolean auditColumnHistoryRender){
    	this.auditColumnHistoryRender = auditColumnHistoryRender;
    }
    
    public boolean isAuditColumnHistoryRender(){
    	return auditColumnHistoryRender;
    }

    /**
     * This field is used to store renderNoDataEDMSDefaults.
     */
    private boolean renderNoDataEDMSDefaults = true;

    /**
     * This field is used to store renderNoDataEDMSDefaults.
     */
    private boolean renderDataTable = true;

    /**
     * This field is used to store renderProtected.
     */
    private boolean renderProtected;

    /**
     * @return Returns the renderProtected.
     */
    public boolean isRenderProtected()
    {
        return renderProtected;
    }

    /**
     * @param renderProtected
     *            The renderProtected to set.
     */
    public void setRenderProtected(boolean renderProtected)
    {
        this.renderProtected = renderProtected;
    }

    /**
     * This field is used to store renderCase.
     */
    private boolean renderCase = false;

    /**
     * This field is used to store renderCorrespondence.
     */
    private boolean renderCorrespondence = false;

    /**
     * This field is used to store renderEditEDMSDefaults.
     */
    private boolean renderEditEDMSDefaults = false;

    /**
     * This field is used to store showSucessMessage.
     */
    private boolean showSucessMessage = false;
    
    /**
     * This field is used to store showSucessMessage.
     */
    private boolean showErrorMessage = false;

    /**
     * Holds the EDMSDefaultsVO.
     */
  //  private EDMSDefaultsVO tempEdmsDefaultsVO;

    /**
     * Holds the index of the records in the data table
     */
    private int rowIndex;

    /**
     * @return Returns the rowIndex.
     */
    public int getRowIndex()
    {
        return rowIndex;
    }

    /**
     * @param rowIndex
     *            The rowIndex to set.
     */
    public void setRowIndex(int rowIndex)
    {
        this.rowIndex = rowIndex;
    }

    /**
     * Holds the EDMSDefaultsVO.
     */
    private EDMSDefaultsVO edmsDefaultsVO;

    /**
     * Holds the EDMSDefaultsVO.
     */
    private List listOfCategoryDOs;
    
    
    /**
     * This is used to render Audit for Dormer cdodes for details page.
     */

    private boolean edmsDefaultsAuditRender = false;

    /**
     * This is used to hold Former codes audit field history list for details
     * page.
     */
    private List edmsDefaultsAuditHistoryList = new ArrayList();

    /**
     * This is used to hold the state of the Audit block open or closed.
     */
    private boolean auditOpen = false;
    
  
    /**
     * Variable to reneder Edit Call Scripts Block.
     */
    private boolean showEditCallScripts = false;
    
 

	/**
	 * @return Returns the showEditCallScripts.
	 */
	public boolean isShowEditCallScripts() {
		return showEditCallScripts;
	}
	/**
	 * @param showEditCallScripts The showEditCallScripts to set.
	 */
	public void setShowEditCallScripts(boolean showEditCallScripts) {
		this.showEditCallScripts = showEditCallScripts;
	}
    /**
     * Constructor for EDMSDefaultsDataBean
     */
    public EDMSDefaultsDataBean()
    {
        super();

        /**
         * method to create the EdmsType valid values,Subjects, Status code,
         * EdmsDocTypeCode list
         */
        getReferenceData();
        /** method to create the categories list */
        getAllCategories();
        
        getAllCaseTypes();
        /** method to get the default EDMS details */
        createEdmsDfltDataTableList();
        /*if(edmsDefaultsList!=null && !(edmsDefaultsList.isEmpty()))
    	{
    	List docType_List = new ArrayList();
    	for(int i = edmsDefaultsList.size()-1 ;i>=0;i--){
        	EDMSDefaultsVO edmsDVO = (EDMSDefaultsVO)edmsDefaultsList.get(i);
        	docType_List.add(edmsDefaultsList.get(i));
        }
    	if(docType_List!=null)
    	edmsDefaultsList = docType_List;
    	}*/
    }

    /**
     * This method is used to create the List for displaying in the data table.
     */
    public final void createEdmsDfltDataTableList()
    {
        //  logger.debug("inside subDataTableList");
        List edmsRefValueList = null;
        List allEdmsList = null;
        int edmsRefValueListSize = 0;
        int allEdmsListSize = 0;

        /**
         * Calls getReferenceData method and sets the list of EdmsDocTypeCode
         * valid Values to data bean list
         */
        //	edmsRefValueList =
        // getEdmsReferenceData(ReferenceServiceDataConstants.C_BATCH_TY_CD_VV);
        edmsRefValueList = this.edmsDocTypeCodeList;

        //	allEdmsList = getEdmsDefaultsList();
        allEdmsList = getEDMSDefaultsDetails();
        List finalEdmsList = new ArrayList();
        if (edmsRefValueList != null && (!edmsRefValueList.isEmpty()))
        {
            edmsRefValueListSize = edmsRefValueList.size();
        }
        if (allEdmsList != null && !allEdmsList.isEmpty())
        {
            allEdmsListSize = allEdmsList.size();
        }
        for (int i = 0; i < edmsRefValueListSize; i++)
        {
            EDMSDefaultsVO edmsDfltVOFinal = new EDMSDefaultsVO();
            SelectItem referenceServiceVO = (SelectItem) edmsRefValueList
                    .get(i);
            boolean indFalg = (referenceServiceVO != null
                    && referenceServiceVO.getLabel() != null && !ContactManagementConstants.EMPTY_STRING
                    .equals(referenceServiceVO.getLabel().trim()));
            if (indFalg)
            {

                edmsDfltVOFinal.setDocumentType(referenceServiceVO.getLabel());
                edmsDfltVOFinal.setAnEDMSType(referenceServiceVO.getValue()
                        .toString());

                int j = 0;
                int pos = 0;
                boolean flagPresent = false;
                for (; j < allEdmsListSize; j++)
                {
                    EDMSDefaultsVO defaultsVO = (EDMSDefaultsVO) allEdmsList
                            .get(j);
                    boolean flag = (defaultsVO != null && referenceServiceVO
                            .getValue().toString().equalsIgnoreCase(
                                    defaultsVO.getAnEDMSType()));
                    if (flag)
                    {
                        flagPresent = true;
                        pos = j;

                        break;
                    }
                }
                if (flagPresent)
                {
                    EDMSDefaultsVO defaultsVO = (EDMSDefaultsVO) allEdmsList
                            .get(pos);
                    defaultsVO.setDocumentType(edmsDfltVOFinal
                            .getDocumentType());
                    finalEdmsList.add(defaultsVO);
                }
                else
                {
                    finalEdmsList.add(edmsDfltVOFinal);
                }
            }
        }
        this.edmsDefaultsList = finalEdmsList;
        if(edmsDefaultsList!=null && !(edmsDefaultsList.isEmpty()))
    	{
    	List docType_List = new ArrayList();
    	for(int i = edmsDefaultsList.size()-1 ;i>=0;i--){
        	EDMSDefaultsVO edmsDVO = (EDMSDefaultsVO)edmsDefaultsList.get(i);
        	docType_List.add(edmsDefaultsList.get(i));
        }
    	if(docType_List!=null)
    	edmsDefaultsList = docType_List;
    	}
    }

    /**
     * This operation is used to get all the Categories.
     */
    private void getAllCategories()
    {

        List listOfCategories = null;
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        List categoryList = new ArrayList();
        CategorySearchCriteriaVO categorySearchCriteriaVO = new CategorySearchCriteriaVO();

        categorySearchCriteriaVO.setVoidindicator(false);
        try
        {
            listOfCategories = contactMaintenanceDelegate.getAllCategoriesList();
        }
        catch (CategoryFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
        }
        if (listOfCategories != null)
        {
        	logger.info("listOfCategories size"+listOfCategories.size());
        	for (int i = 0; i <listOfCategories.size(); i++)
            {
            	Object[] categoyDetails = (Object[]) listOfCategories.get(i);
            	categoryList.add(new SelectItem(categoyDetails[0].toString(), categoyDetails[1].toString()));
            }
        	sortSelectItemSequence(categoryList);
            this.categoryList = categoryList;
        }
    }
    public void sortSelectItemSequence(List sujectList) 
    {
		Comparator selectItemComparator = new Comparator() 
		{
			public int compare(Object obj1, Object obj2) 
			{
				String first = null;
				String second = null;

				SelectItem s1 = (SelectItem) obj1;
				SelectItem s2 = (SelectItem) obj2;

				boolean ascending = true;

				if (s1.getLabel() != null && s2.getLabel() != null) 
				{
					first = s1.getLabel().toLowerCase();
					logger.debug("111111111111111"+ first);
					second = s2.getLabel().toLowerCase();
					logger.debug("111111111111111"+ second);

					return first.compareTo(second);
				}
				return 0;
			}

		};
		Collections.sort(sujectList, selectItemComparator);
	}
    
        
    /**
     * This operation is used to get all the Case types.
     */
    private void getAllCaseTypes()
    {
        List caseTypes = null;
        List caseTypeList = new ArrayList();
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        CaseTypeSearchCriteriaVO caseTypeSearchCriteriaVO = new CaseTypeSearchCriteriaVO();
        try
        {
            logger.debug("***** Before Case type del call *******");
            caseTypes = contactMaintenanceDelegate
                    .getAllCaseTypes(caseTypeSearchCriteriaVO);
        }
        catch (CaseTypeFetchBusinessException exception)
        {
            logger.debug("exception is ---- >" + exception.toString());
        }
        logger.debug("The caseTypes From Data base :" + caseTypes);
        if (caseTypes != null)
        {
            for (Iterator iter = caseTypes.iterator(); iter.hasNext();)
            {
                CaseType caseType = (CaseType) iter.next();

                caseTypeList.add(new SelectItem(caseType.getCaseTypeSK()
                        .toString(), caseType.getDescription()));
                // listOfCategoryVOs.add(categoryVO);
            }
            logger.debug("The caseTypeList size :" + caseTypeList.size());
        }
        this.caseTypes = caseTypeList;
    }

    /**
     * @return Returns the renderCaseOrCr.
     */
    public String getRenderCaseOrCr()
    {
        return renderCaseOrCr;
    }

    /**
     * @return Returns the edmsDefaultsRowIndex.
     */
    public int getEdmsDefaultsRowIndex()
    {
        return edmsDefaultsRowIndex;
    }

    /**
     * @param edmsDefaultsRowIndex
     *            The edmsDefaultsRowIndex to set.
     */
    public void setEdmsDefaultsRowIndex(int edmsDefaultsRowIndex)
    {
        this.edmsDefaultsRowIndex = edmsDefaultsRowIndex;
    }

    /**
     * @return Returns the renderEditEDMSDefaults.
     */
    public boolean isRenderEditEDMSDefaults()
    {
        return renderEditEDMSDefaults;
    }

    /**
     * @param renderEditEDMSDefaults
     *            The renderEditEDMSDefaults to set.
     */
    public void setRenderEditEDMSDefaults(boolean renderEditEDMSDefaults)
    {
        this.renderEditEDMSDefaults = renderEditEDMSDefaults;
    }

    /**
     * @return Returns the renderNoDataEDMSDefaults.
     */
    public boolean isRenderNoDataEDMSDefaults()
    {
        return renderNoDataEDMSDefaults;
    }

    /**
     * @param renderNoDataEDMSDefaults
     *            The renderNoDataEDMSDefaults to set.
     */
    public void setRenderNoDataEDMSDefaults(boolean renderNoDataEDMSDefaults)
    {
        this.renderNoDataEDMSDefaults = renderNoDataEDMSDefaults;
    }

    /**
     * @return Returns the showSucessMessage.
     */
    public boolean isShowSucessMessage()
    {
        return showSucessMessage;
    }

    /**
     * @param showSucessMessage
     *            The showSucessMessage to set.
     */
    public void setShowSucessMessage(boolean showSucessMessage)
    {
        this.showSucessMessage = showSucessMessage;
    }

    /**
     * This field is used to store edmsDefaultsRowIndex.
     */
    private int edmsDefaultsRowIndex;

    /**
     * This field is used for rendering the sorting images.
     */
    private String imageRender;

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
    public void setImageRender(String imageRender)
    {
        this.imageRender = imageRender;
    }

    /**
     * @return Returns the anEDMSDefaultsVO.
     */
    public EDMSDefaultsVO getEdmsDefaultsVO()
    {
        return edmsDefaultsVO;
    }

    /**
     * @param theanEDMSDefaultsVO
     *            The anEDMSDefaultsVO to set.
     */
    public void setEdmsDefaultsVO(EDMSDefaultsVO theanEDMSDefaultsVO)
    {
        edmsDefaultsVO = theanEDMSDefaultsVO;
    }

    /**
     * Used to hold the list of edmsDefaults values
     */
    private List edmsDefaultsList;

    /**
     * @return Returns the edmsDefaultsList.
     */
    public List getEdmsDefaultsList()
    {
        return edmsDefaultsList;
    }

    /**
     * @param edmsDefaultsList
     *            The edmsDefaultsList to set.
     */
    public void setEdmsDefaultsList(List edmsDefaultsList)
    {
        this.edmsDefaultsList = edmsDefaultsList;
    }

    /**
     * This method is used to get the EDMS default values Gets the
     * EDMSDefaultsDetails.
     * 
     * @return String
     */
   public List getEDMSDefaultsDetails()
    {
    	logger.info("Entered into getEDMSDefaultsDetails()");
        List updatedVOsList = new ArrayList();
        EnterpriseBaseSearchCriteriaVO searchCriteriaVO = null;
        try
        {
            List edmsDefaultsList = new ArrayList();

            EDMSDefaultsDOConverter edmsDefaultsHelper = new EDMSDefaultsDOConverter();

            ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

            List edmsDefaultsDOList = new ArrayList();
            edmsDefaultsDOList = contactMaintenanceDelegate
                    .getEdmsDefaultsList(searchCriteriaVO);

            updatedVOsList = edmsDefaultsHelper
                    .convertEDMSDefaultsDOListToVO(edmsDefaultsDOList);
            
          
            EDMSDefaultsVO edmsDefaultsVO=(EDMSDefaultsVO) updatedVOsList.get(0);

            
           
           // EDMSDefaultsVO edmsDefaultsVO=(EDMSDefaultsVO)edmsDefaultsList.get(0);
            if(edmsDefaultsVO == null)
            { 
            	
            	edmsDefaultsVO=  getEdmsDefaultsVO(); 	
            }
           else
           {
           logger.debug("Venkat11111111::"+edmsDefaultsVO.getAuditUserID());
           }
            logger.debug(" Venkat  inside edmsDefaultsVO" +edmsDefaultsVO);
            // setEdmsDefaultsVO(edmsDefaultsVO);
           
           
             if (edmsDefaultsList == null)
            {
                logger.info("NO records found");

            }
           else
            {
                updatedVOsList = addDescForCode(updatedVOsList);

                this.edmsDefaultsList = updatedVOsList;
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
         logger.info("Exit of getEDMSDefaultsDetails()"); 
        return updatedVOsList;
    }

    /**
     * This method is used add Description for the code.
     * 
     * @param edmsDefaultsList :
     *            The edmsDefaultsList to set.
     * @return List.
     */
    private List addDescForCode(List edmsDefaultsList)
    {
        Iterator iterator = null;
        EDMSDefaultsVO edmsDefaultsVO = null;
        List updatedVOsList = new ArrayList();

        if (edmsDefaultsList != null && !edmsDefaultsList.isEmpty())
        {
            iterator = edmsDefaultsList.iterator();
            //	String status = null;
            while (iterator.hasNext())
            {
                edmsDefaultsVO = (EDMSDefaultsVO) iterator.next();
                if ((edmsDefaultsVO != null && edmsDefaultsVO.getStatusCode() != null)
                        || edmsDefaultsVO.getStatusCode() != ContactManagementConstants.EMPTY_STRING)
                {

                    edmsDefaultsVO.setStatus(getDescriptionFromVV(
                            edmsDefaultsVO.getStatusCode(),
                            this.statCodeValidValues));

                }
                if ((edmsDefaultsVO != null && edmsDefaultsVO.getSubjectCode() != null)
                        || edmsDefaultsVO.getSubjectCode() != ContactManagementConstants.EMPTY_STRING)
                {

                    edmsDefaultsVO.setSubject(getDescriptionFromVV(
                            edmsDefaultsVO.getSubjectCode(),
                            this.subjectValidValues));

                }
                if ((edmsDefaultsVO != null && edmsDefaultsVO.getCategoryVO() != null)
                        && edmsDefaultsVO.getCategoryVO().getCategorySK() != null)
                {

                    edmsDefaultsVO.setCategorySK(edmsDefaultsVO.getCategoryVO()
                            .getCategorySK().toString());
                    edmsDefaultsVO.setCategoryDesc(getDescriptionFromVV(
                            edmsDefaultsVO.getCategoryVO().getCategorySK()
                                    .toString(), this.categoryList));

                }
                updatedVOsList.add(edmsDefaultsVO);
            }
        }
        return updatedVOsList;
    }

    /**
     * To get the description based on code provided.
     * 
     * @param code :
     *            Holds the code value.
     * @param vvList :
     *            Holds the List of valid values.
     * @return String : Description of the code provided.
     */
    public final String getDescriptionFromVV(String code, List vvList)
    {
        /*
         * EnterpriseLogger logger = EnterpriseLogFactory
         * .getLogger(CategoryDataBean.class);
         */
        //   logger.info("getDescriptionFromVV");
        String desc = ContactManagementConstants.EMPTY_STRING;
        String valueStr = ContactManagementConstants.EMPTY_STRING;

        if (vvList != null && !vvList.isEmpty())
        {
            for (Iterator iter = vvList.iterator(); iter.hasNext();)
            {
                SelectItem selectItem = (SelectItem) iter.next();
                valueStr = (String) selectItem.getValue();

                if (valueStr != null && valueStr.equalsIgnoreCase(code))
                {
                    desc = selectItem.getLabel();
                    break;
                }
            }
        }
        return desc;
    }

    /**
     * @return Returns the tempEdmsDefaultsVO.
     */
  /**  public EDMSDefaultsVO getTempEdmsDefaultsVO()
    {
        return tempEdmsDefaultsVO;
    }*/

    /**
     * @param tempEdmsDefaultsVO
     *            The tempEdmsDefaultsVO to set.
     */
   /** public void setTempEdmsDefaultsVO(EDMSDefaultsVO tempEdmsDefaultsVO)
    {
        this.tempEdmsDefaultsVO = tempEdmsDefaultsVO;
    }
*/
    /**
     * @return Returns the subjectValidvalues.
     */
    public List getSubjectValidValues()
    {
        return subjectValidValues;
    }

    /**
     * @param subjectValidvalues
     *            The subjectValidvalues to set.
     */
    public void setSubjectValidValues(List subjectValidvalues)
    {
        this.subjectValidValues = subjectValidvalues;
    }

    /**
     * @return Returns the categoryList.
     */
    public List getCategoryList()
    {
        return categoryList;
    }

    /**
     * @param categoryList :
     *            The categoryList to set.
     */
    public void setCategoryList(List categoryList)
    {
        this.categoryList = categoryList;
    }

    /**
     * This operation is used to get the reference data.
     */
    private void getReferenceData()
    {
        logger.info("getReferenceData");

        List referenceList = new ArrayList();
        Map referenceMap = null;

        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

        createInputCriterias(referenceList);

        referenceDataSearch.setInputList(referenceList);

        try
        {
            logger.debug("before the referenceMap call");
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);

            referenceMap = referenceDataListVO.getResponseMap();
            logger.debug("after the referenceMap call");
            if (referenceMap != null)
            {
                List refSubjectList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);

                List refStatusCodeList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_CR_STAT_CD);
                logger.debug("before the C_BATCH_TY_CD call");
                List refEdmsDefaultsValuesList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_EDMS_DOC_TY_CD);
                logger.debug("after the G_EDMS_DOC_TY_CD call");
                List refEdmsDocTyCdList = getReferenceList(referenceMap,
                        FunctionalAreaConstants.CONTACT_MGMT,
                        ReferenceServiceDataConstants.G_EDMS_DOC_DFLT_TY_CD);
                
                sortSelectItemSequence(refStatusCodeList);
                sortSelectItemSequence(refSubjectList);
                this.statCodeValidValues = refStatusCodeList;
                this.subjectValidValues = refSubjectList;
                this.edmsValidValues = refEdmsDocTyCdList;
                this.edmsDocTypeCodeList = refEdmsDefaultsValuesList;
            }
        }
        catch (ReferenceServiceRequestException e)
        {
            //e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        catch (SystemListNotFoundException e)
        {
            // e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        catch (Exception e)
        {
            // e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

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
    private List getReferenceList(Map referenceMap,
            String functionalAreaConstant, String elementName)
    {
        logger.info("getReferenceList>>>>>>>"+elementName);

        List referenceList = null;

        List tempRevenueTypeList = new ArrayList();

        referenceList = (List) referenceMap.get(functionalAreaConstant
                + ProgramConstants.HASH_SEPARATOR + elementName);
        

        if (referenceList != null)
        {
            int size = referenceList.size();
            for (int i = 0; i < size; i++)
            {
                ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                        .get(i);
                
       
                if (elementName
                        .equals(ReferenceServiceDataConstants.G_EDMS_DOC_DFLT_TY_CD))
                        //|| elementName
                         //       .equals(ReferenceServiceDataConstants.G_EDMS_DOC_TY_CD))
                {
                    
                    
                    tempRevenueTypeList.add(new SelectItem(refVo
                            .getValidValueCode().trim(), refVo.getShortDescription()));
                    
                }

                else
                {
                    tempRevenueTypeList.add(new SelectItem(refVo
                            .getValidValueCode(), refVo.getValidValueCode()
                            + ProgramConstants.HYPHEN_SEPARATOR
                            + refVo.getShortDescription()));
                    
                }
            }
        }
        return tempRevenueTypeList;
    }

    /**
     * This method is used to created the input criteria to get the reference
     * date.
     * 
     * @param referenceList :
     *            List of Reference Data.
     */
    private void createInputCriterias(List referenceList)
    {
        logger.info("createInputCriterias");

        InputCriteria edmsDocTyCd = new InputCriteria();
        edmsDocTyCd.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
        edmsDocTyCd
                .setElementName(ReferenceServiceDataConstants.G_EDMS_DOC_DFLT_TY_CD);
        referenceList.add(edmsDocTyCd);
        logger.debug("In createInputCriterias before  C_BATCH_TY_CD");
        InputCriteria edmsDefaultsNames = new InputCriteria();
        edmsDefaultsNames.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
        edmsDefaultsNames
                .setElementName(ReferenceServiceDataConstants.G_EDMS_DOC_TY_CD);
        referenceList.add(edmsDefaultsNames);
        logger.debug("In createInputCriterias AFTER C_BATCH_TY_CD");
        InputCriteria crSubjectsVV = new InputCriteria();
        crSubjectsVV.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
        crSubjectsVV
                .setElementName(ReferenceServiceDataConstants.G_CRSPD_SUBJ_SK);

        referenceList.add(crSubjectsVV);
        logger.debug("In createInputCriterias AFTER G_CRSPD_SUBJ_SK");

        InputCriteria crStatusCodeVV = new InputCriteria();
        crStatusCodeVV.setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
        crStatusCodeVV
                .setElementName(ReferenceServiceDataConstants.G_CR_STAT_CD);

        referenceList.add(crStatusCodeVV);
        logger.debug("In createInputCriterias AFTER G_CR_STAT_CD");
    }

    /**
     * @return Returns the statCodeValidValues.
     */
    public List getStatCodeValidValues()
    {
        return statCodeValidValues;
    }

    /**
     * @param statCodeValidValues
     *            The statCodeValidValues to set.
     */
    public void setStatCodeValidValues(List statCodeValidValues)
    {
        this.statCodeValidValues = statCodeValidValues;
    }

    /**
     * @return Returns the edmsValidValues.
     */
    public List getEdmsValidValues()
    {
        return edmsValidValues;
    }

    /**
     * @param edmsValidValues
     *            The edmsValidValues to set.
     */
    public void setEdmsValidValues(List edmsValidValues)
    {
        this.edmsValidValues = edmsValidValues;
    }

    /**
     * @return Returns the renderCase.
     */
    public boolean isRenderCase()
    {
        return renderCase;
    }

    /**
     * @param renderCase
     *            The renderCase to set.
     */
    public void setRenderCase(boolean renderCase)
    {
        this.renderCase = renderCase;
    }

    /**
     * @return Returns the renderCorrespondence.
     */
    public boolean isRenderCorrespondence()
    {
        return renderCorrespondence;
    }

    /**
     * @param renderCorrespondence
     *            The renderCorrespondence to set.
     */
    public void setRenderCorrespondence(boolean renderCorrespondence)
    {
        this.renderCorrespondence = renderCorrespondence;
    }

    /**
     * @return Returns the listOfCategoryDOs.
     */
    public List getListOfCategoryDOs()
    {
        return listOfCategoryDOs;
    }

    /**
     * @param listOfCategoryDOs
     *            The listOfCategoryDOs to set.
     */
    public void setListOfCategoryDOs(List listOfCategoryDOs)
    {
        this.listOfCategoryDOs = listOfCategoryDOs;
    }

    /**
     * @return Returns the categorySubjectValues.
     */
    public List getCategorySubjectValues()
    {
        return categorySubjectValues;
    }

    /**
     * @param categorySubjectValues
     *            The categorySubjectValues to set.
     */
    public void setCategorySubjectValues(List categorySubjectValues)
    {
        this.categorySubjectValues = categorySubjectValues;
    }

    /**
     * @return Returns the renderDataTable.
     */
    public boolean isRenderDataTable()
    {
        return renderDataTable;
    }

    /**
     * @param renderDataTable
     *            The renderDataTable to set.
     */
    public void setRenderDataTable(boolean renderDataTable)
    {
        this.renderDataTable = renderDataTable;
    }

    /**
     * @return Returns the edmsDocTypeCodeList.
     */
    public List getEdmsDocTypeCodeList()
    {
        return edmsDocTypeCodeList;
    }

    /**
     * @param edmsDocTypeCodeList
     *            The edmsDocTypeCodeList to set.
     */
    public void setEdmsDocTypeCodeList(List edmsDocTypeCodeList)
    {
        this.edmsDocTypeCodeList = edmsDocTypeCodeList;
    }

    /**
     * @return Returns the caseTypes.
     */
    public List getCaseTypes()
    {
        return caseTypes;
    }
    
    /**
     * @param caseTypes The caseTypes to set.
     */
    public void setCaseTypes(List caseTypes)
    {
        this.caseTypes = caseTypes;
    }
    
    /**
     * @return Returns the caseRouteToList.
     */
    public List getCaseRouteToList()
    {
        return caseRouteToList;
    }
    
    /**
     * @param caseRouteToList The caseRouteToList to set.
     */
    public void setCaseRouteToList(List caseRouteToList)
    {
        this.caseRouteToList = caseRouteToList;
    }
	/**
	 * @return Returns the auditOpen.
	 */
	public boolean isAuditOpen() {
		return auditOpen;
	}
	/**
	 * @param auditOpen The auditOpen to set.
	 */
	public void setAuditOpen(boolean auditOpen) {
		this.auditOpen = auditOpen;
	}
	/**
	 * @return Returns the edmsDefaultsAuditHistoryList.
	 */
	public List getEdmsDefaultsAuditHistoryList() {
		return edmsDefaultsAuditHistoryList;
	}
	/**
	 * @param edmsDefaultsAuditHistoryList The edmsDefaultsAuditHistoryList to set.
	 */
	public void setEdmsDefaultsAuditHistoryList(List edmsDefaultsAuditHistoryList) {
		this.edmsDefaultsAuditHistoryList = edmsDefaultsAuditHistoryList;
	}
	/**
	 * @return Returns the edmsDefaultsAuditRender.
	 */
	public boolean isEdmsDefaultsAuditRender() {
		return edmsDefaultsAuditRender;
	}
	/**
	 * @param edmsDefaultsAuditRender The edmsDefaultsAuditRender to set.
	 */
	public void setEdmsDefaultsAuditRender(boolean edmsDefaultsAuditRender) {
		this.edmsDefaultsAuditRender = edmsDefaultsAuditRender;
	}
	/**
	 * @return Returns the selectedAuditLog.
	 */
	public AuditLog getSelectedAuditLog() {
		return selectedAuditLog;
	}
	/**
	 * @param selectedAuditLog The selectedAuditLog to set.
	 */
	public void setSelectedAuditLog(AuditLog selectedAuditLog) {
		this.selectedAuditLog = selectedAuditLog;
	}
	/**
	 * @return Returns the itemsPerPage.
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	/**
	 * @param itemsPerPage The itemsPerPage to set.
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
    /**
     * @return Returns the saveFlag.
     */
    public boolean isSaveFlag() {
        return saveFlag;
    }
    /**
     * @param saveFlag The saveFlag to set.
     */
    public void setSaveFlag(boolean saveFlag) {
        this.saveFlag = saveFlag;
    }
	
	
	/**
	 * @return Returns the auditLogFlag.
	 */
	public boolean isAuditLogFlag() {
		return auditLogFlag;
	}
	/**
	 * @param auditLogFlag The auditLogFlag to set.
	 */
	public void setAuditLogFlag(boolean auditLogFlag) {
		this.auditLogFlag = auditLogFlag;
	}
	/**
	 * @return Returns the showErrorMessage.
	 */
	public boolean isShowErrorMessage() {
		return showErrorMessage;
	}
	/**
	 * @param showErrorMessage The showErrorMessage to set.
	 */
	public void setShowErrorMessage(boolean showErrorMessage) {
		this.showErrorMessage = showErrorMessage;
	}
	
}

























