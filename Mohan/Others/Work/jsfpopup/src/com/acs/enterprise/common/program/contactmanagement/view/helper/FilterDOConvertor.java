
package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseFilter;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CaseFilterXref;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceFilter;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceFilterXref;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.FilterControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.FilterDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CaseFilterVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryFilterVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.FilterVO;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/** Class for converting Vo To Do And From Do to Vo */
public class FilterDOConvertor
        extends DataTransferObjectConverter
{
    /** Enterprise Logger for Logging. */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(FilterDOConvertor.class);

    /**
     * constructor for EDMSDefaultsDOConverter.
     */
    public FilterDOConvertor()
    {
        super();
        logger.debug("FilterDOConvertor constructor");
    }

    /**
     * This method convert all the Correspondence filter do's to filter vo's.
     * 
     * @param filterDOList
     *            The filterDOList to set.
     * @return List.
     */
    public List getAllCrFilterVOList(List filterDOList)
    {
        /** Creates a Reference of CorrespondenceFilter */
        CorrespondenceFilter correspondenceFilter = null;
        List filterVOList = new ArrayList();
      //FindBug Issue Resolved
        //Iterator iterator = filterDOList.iterator();
        /** Creates a Reference of FilterVO */
        //	FilterVO filterVO = new FilterVO();
        if (filterDOList != null)
        {
        	Iterator iterator = filterDOList.iterator();
        	/** Iterates Through the List of Do's */
            while (iterator.hasNext())
            {
                correspondenceFilter = (CorrespondenceFilter) iterator.next();
                filterVOList.add(convertCrFilterDOToVO(correspondenceFilter));
            }
        }
        return filterVOList;
    }

    /**
     * This method convert all the Case filter do's to filter vo's.
     * 
     * @param filterDOList
     *            The filterDOList to set.
     * @return List.
     */
    public List getAllCaseFilterVOList(List filterDOList)
    {
        /** Creates a Reference of CaseFilter */
        CaseFilter caseFilter = null;
        List filterVOList = new ArrayList();

        if (filterDOList != null)
        {
            int filterDOListSize = filterDOList.size();

            /** Iterates Through the List of Do's */

            for (int i = 0; i < filterDOListSize; i++)
            {
                caseFilter = (CaseFilter) filterDOList.get(i);
                filterVOList.add(convertCaseFilterDOToVO(caseFilter));
            }
        }
        return filterVOList;
    }

    /**
     * This method convert the filter do to filter vo.
     * 
     * @param correspondenceFilterDO
     *            The correspondenceFilterDO to set.
     * @return FilterVO.
     */
    public FilterVO convertCrFilterDOToVO(
            CorrespondenceFilter correspondenceFilterDO)
    {
        FilterVO filterVO = new FilterVO();
        Set selectedCategories = null;

        /** Sets the Main Vo Values */
        if (correspondenceFilterDO.getDescription() != null)

        {
            filterVO.setDescription(correspondenceFilterDO.getDescription());
        }
        if (correspondenceFilterDO.getFilterName() != null)
        {
            filterVO.setFilterName(correspondenceFilterDO.getFilterName());
        }

        if (correspondenceFilterDO.getFilterScopeCode() != null)
        {
            filterVO
                    .setFilterScope(correspondenceFilterDO.getFilterScopeCode());
        }
        filterVO.setFilterType(ContactManagementConstants.FILTER_TYPE_CR);
        filterVO.setVersionNo(correspondenceFilterDO.getVersionNo());
        filterVO.setAuditUserID(correspondenceFilterDO.getAuditUserID());
        filterVO.setAuditTimeStamp(correspondenceFilterDO.getAuditTimeStamp());
        filterVO.setAddedAuditUserID(correspondenceFilterDO
                .getAddedAuditUserID());
        filterVO.setAddedAuditTimeStamp(correspondenceFilterDO
                .getAddedAuditTimeStamp());
        selectedCategories = correspondenceFilterDO.getCatFilterXrefs();
        filterVO.setCategoryFilterVO(getCategoryFilterVOList(selectedCategories));
        Iterator it=selectedCategories.iterator();
        CorrespondenceFilterXref correspondenceFilterXreDO = null;
        //for defect ESPRD00659339 fix
         //CategoryFilterVO categoryFilterVO = null;
        List filterEditList = new ArrayList();
        filterEditList.add(createAuditableFeild("CR Category","categorySK"));
		filterEditList.add(createAuditableFeild("Case Types","caseTypeSK"));
        while (it.hasNext())
        {
            correspondenceFilterXreDO = (CorrespondenceFilterXref) it
                    .next();
            //categoryFilterVO = (CategoryFilterVO) convertCorrespondenceFilterDOToVO(correspondenceFilterXreDO);
            createVOAuditKeysList(correspondenceFilterXreDO,filterVO);
           
            //selectedCategoryVOList.add(categoryFilterVO);
        }
		AuditDataFilter.filterAuditKeys(filterEditList,filterVO);
        List auditKeylist=new ArrayList(filterVO.getAuditKeyList());
        
        
        createVOAuditKeysList(correspondenceFilterDO,filterVO);
        filterEditList.add(createAuditableFeild("Filter Description","filterName"));
		filterEditList.add(createAuditableFeild("Action","filterScopeCode"));
		
		AuditDataFilter.filterAuditKeys(filterEditList,filterVO);
		
		
		filterVO.getAuditKeyList().addAll(new ArrayList(auditKeylist));
		//for defect ESPRD00659339 fix
        return filterVO;
    }

    /**
     * Method to convert the CategoryFilterVOList Do to Vo.
     * 
     * @param selectedCategories
     *            The selectedCategories to set.
     * @return List.
     */
    public List getCategoryFilterVOList(Set selectedCategories)
    {
        List selectedCategoryVOList = new ArrayList();
        if (selectedCategories != null)
        {
            Iterator iterator = selectedCategories.iterator();
            CorrespondenceFilterXref correspondenceFilterXreDO = null;
            CategoryFilterVO categoryFilterVO = null;
            while (iterator.hasNext())
            {
                correspondenceFilterXreDO = (CorrespondenceFilterXref) iterator
                        .next();
                categoryFilterVO = (CategoryFilterVO) convertCorrespondenceFilterDOToVO(correspondenceFilterXreDO);
                selectedCategoryVOList.add(categoryFilterVO);
            }
        }

        return selectedCategoryVOList;
    }

    /**
     * Method to convert CorrespondenceFilter Do to Vo.
     * 
     * @param correspondenceFilterXrefDO
     *            The correspondenceFilterXrefDO to set.
     * @return CategoryFilterVO.
     */
    public CategoryFilterVO convertCorrespondenceFilterDOToVO(
            CorrespondenceFilterXref correspondenceFilterXrefDO )
    {
    	
        CategoryFilterVO categoryFilterVO = new CategoryFilterVO();

        categoryFilterVO.setCategorySK(correspondenceFilterXrefDO
                .getCategorySK());
        categoryFilterVO.setFilterName(correspondenceFilterXrefDO
                .getFilterName());
        categoryFilterVO
                .setVersionNo(correspondenceFilterXrefDO.getVersionNo());
        categoryFilterVO.setAuditUserID(correspondenceFilterXrefDO
                .getAuditUserID());
        categoryFilterVO.setAuditTimeStamp(correspondenceFilterXrefDO
                .getAuditTimeStamp());
        categoryFilterVO.setAddedAuditUserID(correspondenceFilterXrefDO
                .getAddedAuditUserID());
        categoryFilterVO.setAddedAuditTimeStamp(correspondenceFilterXrefDO
                .getAddedAuditTimeStamp());
        
   
        return categoryFilterVO;
    }

    /**
     * This method convert the case filter do to filter vo.
     * 
     * @param caseFilter
     *            The caseFilter to set.
     * @return FilterVO.
     */
    public FilterVO convertCaseFilterDOToVO(CaseFilter caseFilter)
    {
        FilterVO filterVO = new FilterVO();
        Set selectedCaseTypes = null;

        /** Sets the Main Vo Values */
        if (caseFilter.getDescription() != null)
        {
            filterVO.setDescription(caseFilter.getDescription());
        }
        if (caseFilter.getFilterName() != null)
        {
            filterVO.setFilterName(caseFilter.getFilterName());
        }

        if (caseFilter.getFilterScopeCode() != null)
        {
            filterVO.setFilterScope(caseFilter.getFilterScopeCode());
        }
        filterVO.setFilterType(ContactManagementConstants.FILTER_TYPE_CASE);
        filterVO.setVersionNo(caseFilter.getVersionNo());
        filterVO.setAuditUserID(caseFilter.getAuditUserID());
        filterVO.setAuditTimeStamp(caseFilter.getAuditTimeStamp());
        filterVO.setAddedAuditUserID(caseFilter.getAddedAuditUserID());
        filterVO.setAddedAuditTimeStamp(caseFilter.getAddedAuditTimeStamp());
        selectedCaseTypes = caseFilter.getCaseFilterXrefs();
        filterVO.setCaseTypeFilterVO(getCaseFilterVOList(selectedCaseTypes));
		CaseFilterXref caseFilterXref = null;
		//for defect ESPRD00659339 fix
        //CaseFilterVO caseFilterVO = null;
        Iterator selectedCaseTypesIt=selectedCaseTypes.iterator();
		 List filterEditList = new ArrayList();
        filterEditList.add(createAuditableFeild("Case Types","caseTypeSK"));
		filterEditList.add(createAuditableFeild("CR Category","categorySK"));
        while(selectedCaseTypesIt.hasNext()){
        	caseFilterXref = (CaseFilterXref) selectedCaseTypesIt.next();
           // caseFilterVO = (CaseFilterVO) convertCaseTypeFilterDOToVO(caseFilterXref );
            createVOAuditKeysList(caseFilterXref, filterVO);
			
        }
        AuditDataFilter.filterAuditKeys(filterEditList,filterVO);
        List auditKeylist=new ArrayList(filterVO.getAuditKeyList());
        
    	createVOAuditKeysList(caseFilter, filterVO);
		filterEditList.add(createAuditableFeild("Filter Description","filterName"));
		filterEditList.add(createAuditableFeild("Action","filterScopeCode"));
		 
		AuditDataFilter.filterAuditKeys(filterEditList,filterVO);
		
		filterVO.getAuditKeyList().addAll(new ArrayList(auditKeylist));
		//for defect ESPRD00659339 fix
        return filterVO;
    }

    /**
     * Method to convert the CaseFilterVOList Do to Vo.
     * 
     * @param selectedCaseTypes
     *            The selectedCaseTypes to set.
     * @return List.
     */
    public List getCaseFilterVOList(Set selectedCaseTypes)
    {
        List selectedCaseTypesVOList = new ArrayList();
        if (selectedCaseTypes != null)
        {
            Iterator iterator = selectedCaseTypes.iterator();
            CaseFilterXref caseFilterXref = null;
            CaseFilterVO caseFilterVO = null;
            while (iterator.hasNext())
            {
                caseFilterXref = (CaseFilterXref) iterator.next();
                caseFilterVO = (CaseFilterVO) convertCaseTypeFilterDOToVO(caseFilterXref );
                selectedCaseTypesVOList.add(caseFilterVO);
            }
        }

        return selectedCaseTypesVOList;
    }

    /**
     * Method to convert CaseFilter Do to Vo.
     * 
     * @param caseFilterXref
     *            The caseFilterXref to set.
     * @return CaseFilterVO.
     */
    public CaseFilterVO convertCaseTypeFilterDOToVO(
            CaseFilterXref caseFilterXref)
    {

        CaseFilterVO caseFilterVO = new CaseFilterVO();

        caseFilterVO.setCaseTypeSK(caseFilterXref.getCaseTypeSK());
        caseFilterVO.setFilterName(caseFilterXref.getCaseFilterName());
        caseFilterVO.setVersionNo(caseFilterXref.getVersionNo());
        caseFilterVO.setAuditUserID(caseFilterXref.getAuditUserID());
        caseFilterVO.setAuditTimeStamp(caseFilterXref.getAuditTimeStamp());
        caseFilterVO.setAddedAuditUserID(caseFilterXref.getAddedAuditUserID());
        caseFilterVO.setAddedAuditTimeStamp(caseFilterXref
                .getAddedAuditTimeStamp());
        
        
        return caseFilterVO;
    }

    /**
     * Method to get the CategoryFilter DO List.
     * 
     * @param selectedCategories
     *            The selectedCategories to set.
     * @param filterName
     *            The filterName to set.
     * @return Set.
     */
    public Set getCategoryFilterDOList(List selectedCategories,
            String filterName)
    {
        Set selectedCategoryDOList = new HashSet();
        Iterator iterator = selectedCategories.iterator();
        CorrespondenceFilterXref correspondenceFilterDO = null;
        CategoryFilterVO categoryFilterVO = new CategoryFilterVO();
        while (iterator.hasNext())
        {
            SelectItem si = (SelectItem) iterator.next();
            categoryFilterVO.setCategorySK(Long.valueOf(si.getValue()
                    .toString()));
            categoryFilterVO.setFilterName(filterName);
            correspondenceFilterDO = (CorrespondenceFilterXref) convertCorrespondenceFilterVOToDO(categoryFilterVO);
            selectedCategoryDOList.add(correspondenceFilterDO);
        }

        return selectedCategoryDOList;
    }
/**
 * Method to get the CaseFilter DO list
 * @param categoryFilterVO
 * @return
 */
    public Set getCaseFilterDOList(List selectedCaseTypes,
            String filterName)
    {
        logger.debug("Inside getCaseFilterDOList");
        Set selectedCaseDOList = new HashSet();
        Iterator iterator = selectedCaseTypes.iterator();
        CaseFilterXref caseFilterXrefDO = null;
        CaseFilterVO caseFilterVO = new CaseFilterVO();
        while (iterator.hasNext())
        {
            SelectItem si = (SelectItem) iterator.next();
            caseFilterVO.setCaseTypeSK(Long.valueOf(si.getValue()
                    .toString()));
            caseFilterVO.setFilterName(filterName);
            caseFilterXrefDO = (CaseFilterXref) convertCaseFilterVOToDO(caseFilterVO);
            selectedCaseDOList.add(caseFilterXrefDO);
        }
        logger.debug("End getCaseFilterDOList");
        return selectedCaseDOList;
    }
    /**
     * Method to convert the CorrespondenceFilterXref Vo to Do.
     * 
     * @param categoryFilterVO
     *            The categoryFilterVO to set.
     * @return CorrespondenceFilterXref.
     */
    public CaseFilterXref convertCaseFilterVOToDO(
            CaseFilterVO caseFilterVO)
    {
        logger.debug("Inside CaseFilterXrefVOTODO");
        CaseFilterXref caseFilterXrefDO = new CaseFilterXref();
        caseFilterXrefDO.setCaseTypeSK(caseFilterVO.getCaseTypeSK());
        caseFilterXrefDO.setCaseFilterName(caseFilterVO.getFilterName());
        caseFilterXrefDO.setVersionNo(caseFilterVO.getVersionNo());
        caseFilterXrefDO.setAuditUserID(getLoggedInUserID());
        caseFilterXrefDO.setAuditTimeStamp(getTimeStamp());
        caseFilterXrefDO.setAddedAuditUserID(getLoggedInUserID());
        caseFilterXrefDO.setAddedAuditTimeStamp(getTimeStamp());
        logger.debug("End CaseFilterXrefVOTODO");
        return caseFilterXrefDO;
    }
    /**
     * Method to convert the CorrespondenceFilterXref Vo to Do.
     * 
     * @param categoryFilterVO
     *            The categoryFilterVO to set.
     * @return CorrespondenceFilterXref.
     */
    public CorrespondenceFilterXref convertCorrespondenceFilterVOToDO(
            CategoryFilterVO categoryFilterVO)
    {
        CorrespondenceFilterXref correspondenceFilterDO = new CorrespondenceFilterXref();
        correspondenceFilterDO.setCategorySK(categoryFilterVO.getCategorySK());
        correspondenceFilterDO.setFilterName(categoryFilterVO.getFilterName());
        correspondenceFilterDO.setVersionNo(categoryFilterVO.getVersionNo());
        correspondenceFilterDO.setAuditUserID(getLoggedInUserID());
        correspondenceFilterDO.setAuditTimeStamp(getTimeStamp());
        correspondenceFilterDO.setAddedAuditUserID(getLoggedInUserID());
        correspondenceFilterDO.setAddedAuditTimeStamp(getTimeStamp());
        return correspondenceFilterDO;
    }
    

    /**
     * This method conver all the filter vo to filter do.
     * 
     * @param filterVO
     *            The filterVO to set.
     * @return CorrespondenceFilter.
     */
    public CorrespondenceFilter convertCorrespondenceFilterVOToDO(
            FilterVO filterVO)
    {
        CorrespondenceFilter correspondenceFilterDO = new CorrespondenceFilter();
        /** Sets Audit Info in to the Main VO */
        correspondenceFilterDO.setAddedAuditTimeStamp(filterVO
                .getAddedAuditTimeStamp());
        correspondenceFilterDO.setAddedAuditUserID(filterVO
                .getAddedAuditUserID());
        correspondenceFilterDO.setAuditUserID(filterVO.getAuditUserID());
        correspondenceFilterDO.setAuditTimeStamp(filterVO.getAuditTimeStamp());

        if (filterVO.getDescription() != null)
        {
            correspondenceFilterDO.setDescription(filterVO.getDescription());
        }

        if (filterVO.getFilterName() != null)
        {
            correspondenceFilterDO.setFilterName(filterVO.getFilterName());
        }

        if (filterVO.getFilterScope() != null)
        {
            correspondenceFilterDO
                    .setFilterScopeCode(filterVO.getFilterScope());
        }

        correspondenceFilterDO.setCatFilterXrefs(getCategoryFilterDOList(
                filterVO.getCategoryFilterVO(), filterVO.getFilterName()));
        correspondenceFilterDO.setVersionNo(filterVO.getVersionNo());
        correspondenceFilterDO.setAuditUserID(getLoggedInUserID());
        correspondenceFilterDO.setAuditTimeStamp(getTimeStamp());
        correspondenceFilterDO.setAddedAuditUserID(getLoggedInUserID());
        correspondenceFilterDO.setAddedAuditTimeStamp(getTimeStamp());
        return correspondenceFilterDO;
    }

    /**
     * This method conver all the filter vo to filter do.
     * 
     * @param filterVO
     *            The filterVO to set.
     * @return CaseFilter.
     */
    public CaseFilter convertCaseFilterVOToDO(FilterVO filterVO)
    {
        CaseFilter caseFilterDO = new CaseFilter();
        //	List csEntityVOList = new ArrayList();
        /** Sets Audit Info in to the Main VO */
        caseFilterDO.setAddedAuditTimeStamp(filterVO.getAddedAuditTimeStamp());
        caseFilterDO.setAddedAuditUserID(filterVO.getAddedAuditUserID());
        caseFilterDO.setAuditUserID(filterVO.getAuditUserID());
        caseFilterDO.setAuditTimeStamp(filterVO.getAuditTimeStamp());
        logger.debug("Inside convertCaseFilterVOToDO");
        
        if (filterVO.getDescription() != null)
        {
            caseFilterDO.setDescription(filterVO.getDescription());
        }
        if (filterVO.getFilterName() != null)
        {
            caseFilterDO.setFilterName(filterVO.getFilterName());
        }

        if (filterVO.getFilterScope() != null)
        {
            caseFilterDO.setFilterScopeCode(filterVO.getFilterScope());
        }
        caseFilterDO.setCaseFilterXrefs(getCaseFilterDOList(
                filterVO.getCaseTypeFilterVO(), filterVO.getFilterName()));
        caseFilterDO.setVersionNo(filterVO.getVersionNo());
        logger.debug("Version number from CaseFilter in ConvertVOTODO:" +caseFilterDO.getVersionNo());
        caseFilterDO.setAuditUserID(getLoggedInUserID());
        caseFilterDO.setAuditTimeStamp(getTimeStamp());
        caseFilterDO.setAddedAuditUserID(getLoggedInUserID());
        caseFilterDO.setAddedAuditTimeStamp(getTimeStamp());
        return caseFilterDO;
    }

    /**
     * This method is used to get the User ID.
     * 
     * @return String : User ID
     */
    /*public String getUserID()
    {
        logger.info("getUserID");

        String userId = ContactManagementConstants.TEST_USERID;
        //FacesContext fc = FacesContext.getCurrentInstance();
        FilterControllerBean filterControllerBean = new FilterControllerBean();

        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = filterControllerBean.getUserData(
                renderRequest, renderResponse);

        if (eup != null
                && eup.getUserId() != null
                && !eup.getUserId().trim().equals(
                        ContactManagementConstants.EMPTY_STRING))
        {
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
     * This method is used to get the Current Timestamp.
     * 
     * @return Timestamp : Current Timestamp.
     */
    private Timestamp getTimeStamp()
    {
        logger.info("getTimeStamp");

        Calendar cal = Calendar.getInstance();

        return new Timestamp(cal.getTimeInMillis());
    }
    
    private void createVOAuditKeysList(EnterpriseBaseDomain enterpriseBaseDomain,AuditaleEnterpriseBaseVO  auditaleEnterpriseBaseVO)
    {
    	
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
    	       	
    	       	logger.debug("======LineItemAuditsDelegate====resultList====Size======="+resultList.size());
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
    
    public List getFilterVOList(List filterDOList, String filterType)
    {
    	List filterVOList = null;
    	Iterator iterator = filterDOList.iterator();
    	if (filterDOList != null)
        {
    		filterVOList = new ArrayList();
    		while (iterator.hasNext())
            {
            	FilterVO filterVO = new FilterVO();
            	filterVO.setFilterName((String) iterator.next());
            	filterVO.setFilterType(filterType);
            	filterVOList.add(filterVO);
            }
        }
        return filterVOList;
    }
	
	 private AuditableField createAuditableFeild(String feildName,String domainAttributeName)
	{
		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);
		return auditableField;

	}

}