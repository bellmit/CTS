
package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.portlet.PortletSession;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.EntitySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;


import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;





/**
 * @author Wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class CMEntitySearchDataBean
        extends EnterpriseBaseDataBean
{

    /**
     * Generates a Variable to hold Bean Name.
     */
	// Serialization issue Fix Starts
	private static EnterpriseLogger logger = EnterpriseLogFactory.getLogger(CMEntitySearchDataBean.class);
	// Serialization issue Fix Ends
    
    
    /**
     * Constructor for CMEntitySearchDataBean
     */
    public CMEntitySearchDataBean()
    {
    	
        super();
        logger = EnterpriseLogFactory.getLogger(CMEntitySearchDataBean.class);
      
    }

    /**
     * Generating Reference of EntitySearchCriteriaVO .
     */
    private EntitySearchCriteriaVO entitySearchCriteriaVO;

    /** SEARCH ENTITY */
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
	 * @param cursorFocusId The cursorFocusId to set.
	 */
	public void setCursorFocusId(String cursorFocusId) {
		this.cursorFocusId = cursorFocusId;
	}
    /**
     * List to hold values for Dropdown for Entity Type.
     */
   
    private List entityDropDownList = new ArrayList();

    /**
     * List to hold values for Dropdown for Entity ID Type.
     */
   
    private List entityIDTypeList = new ArrayList();
    
    /** holds the enable/disable add entity link */
    private boolean disableAddEntity;

    /**
     * List to hold values for Dropdown for Provider Type.
     */
   
    private List provTypeList = new ArrayList();

    /**
     * List to hold values for Dropdown for LOB.
     */
    
    private List lobList = new ArrayList();

    /**
     * List to hold Search Reslut.
     */
 
    private List searchResultsList = new ArrayList();
    
    /**
     * List to hold Search Reslut.
     */
  
    private List searchResults = new ArrayList();

    /**
     * Variable to reneder Block for Provider Entity.
     */
    private Boolean renderProvider = Boolean.FALSE;
    
    
    /**
     * Variable to reneder Block for LOB.
     */
    private Boolean renderLOB = Boolean.FALSE;
    
    
    /**
     * Variable to reneder Block for CountyName.
     */
    private Boolean countyName = Boolean.FALSE;

    /**
     * Variable to reneder Block for name fields.
     */
    private Boolean renderNameSection = Boolean.FALSE;
    /**
     * Variable to reneder Notes field.
     */
    private Boolean renderNotes = Boolean.FALSE;
    
    /**
     * Variable to reneder entity field.
     */
    private Boolean renderEntity = Boolean.TRUE;

    /** holds the enable/disable status of entity type */
    //FOR HEAP DUMP ISSUE
   /* private boolean disableEntityField = false;*/
    //FOR HEAP DUMP ISSUE
	/**
	 * @return Returns the renderEntity.
	 */
	public Boolean getRenderEntity() {
		return renderEntity;
	}
	/**
	 * @param renderEntity The renderEntity to set.
	 */
	public void setRenderEntity(Boolean renderEntity) {
		this.renderEntity = renderEntity;
	}
    /**
     * @return Returns the renderNotes.
     */
    public Boolean getRenderNotes()
    {
        return renderNotes;
    }
    /**
     * @param renderNotes The renderNotes to set.
     */
    public void setRenderNotes(Boolean renderNotes)
    {
        this.renderNotes = renderNotes;
    }
//  for fixing defect ESPRD00576206
    private Boolean renderRequired=Boolean.TRUE;
    /**
     * Variable to reneder Organization Name field.
     */
    private Boolean renderOrganizationName = Boolean.FALSE;

    /**
     * Variable to reneder Carrier Name field.
     */
    private Boolean renderCarrierNam = Boolean.FALSE;

    /**
     * Variable to reneder Carrier Name field.
     */
    private Boolean renderSearchResult = Boolean.FALSE;

    /**
     * Variable to check if the Search Entity page is fresh page .
     * This is used to reset the values when page navigates from add to search through IPC.
     */
    private boolean newSearchEntity = true;
    
    
    private boolean mainNotesRender = true;

    /**
     * Variable used to render Image for Sorting purpose.
     */
    private String imageRender;

    /**
     * This variable is Used For IPC to Maintain Enity . The Geter method is
     * Called in maintainEnity Portlet.
     */
    private String loadRefernceData;

    /** holds the Menu action code */
    private String menuActionCode;
    /** holds the total number of records per page. * */
    private int itemsPerPage = ProgramNumberConstants.NO_OF_RECORD_TO_FETCH;
    
    private long entityCount;
    
    private int previousPageIndex;
    
    private int nextPageIndex;
    
    private int fromIndex;
    
    private int toIndex;

	/**
	 * @return Returns the entityCount.
	 */
	public long getEntityCount() {
		return entityCount;
	}
	/**
	 * @param entityCount The entityCount to set.
	 */
	public void setEntityCount(long entityCount) {
		this.entityCount = entityCount;
	}
    /**
     * @return Returns the entitySearchCriteriaVO.
     */
    public EntitySearchCriteriaVO getEntitySearchCriteriaVO()
    {

        return entitySearchCriteriaVO;
    }

    /**
     * @param entitySearchCriteriaVO
     *            The entitySearchCriteriaVO to set.
     */
    public void setEntitySearchCriteriaVO(
            EntitySearchCriteriaVO entitySearchCriteriaVO)
    {
        this.entitySearchCriteriaVO = entitySearchCriteriaVO;
    }

    /**
     * @return Returns the entityDropDownList.
     */
    public List getEntityDropDownList()
    {
        return entityDropDownList;
    }

    /**
     * @param entityDropDownList
     *            The entityDropDownList to set.
     */
    public void setEntityDropDownList(List entityDropDownList)
    {
        this.entityDropDownList = entityDropDownList;
    }

    /**
     * @return Returns the entityIDTypeList.
     */
    public List getEntityIDTypeList()
    {
    	
       
        String newValue = null;
        FacesContext fc =  FacesContext.getCurrentInstance();
        Map requestMap = null;
        if(fc!=null)
        	requestMap = fc.getExternalContext().getRequestParameterMap();
	       		
		if(requestMap!=null && requestMap.get("entityTypSrch")!=null)
		{	
			newValue = (String)requestMap.get("entityTypSrch");
         CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();
        getEntitySearchCriteriaVO().setEntityType(
                newValue);

        /** This will not reset the Search Page on Value Change of Entity Type */
        setNewSearchEntity(false);

        /** ENTITY TYPE -- MEMBER */

        if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_MEM, newValue))
        {
            

            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_MEM);
            /** Populates the Entity ID Type dropdown for Entity Type Member */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            setRenderNameSection(Boolean.TRUE);
            setRenderProvider(Boolean.FALSE);
            setRenderCarrierNam(Boolean.FALSE);
            setRenderOrganizationName(Boolean.FALSE);
            setRenderLOB(Boolean.FALSE);
            setCountyName(Boolean.FALSE);
            getProvTypeList().clear();
            getLobList().clear();
            setRenderTradingPartner(Boolean.FALSE);

        }

        /** ENTITY TYPE -- PROVIDER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_PROV, newValue))
        {

            
            //Modified for defect ESPRD00620631 
            Long sysListNumber = new Long("1017");/*ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_PROVIDER);*/
            /** Populates the Entity ID Type dropdown for Entity Type Provider */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.PROVIDER,sysListNumber) != null)
            {
            	
                setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.PROVIDER,sysListNumber));
            }

            /** Populates the Provider Type dropdown for Entity Type Provider */
            if (cmEntityDOConvertor.getProvTypeReferenceData() != null)
            {
                setProvTypeList(cmEntityDOConvertor
                        .getProvTypeReferenceData());
            }

            /** Populates the LOB dropdown for Entity Type Provider */
            if (cmEntityDOConvertor.getLobReferenceData() != null)
            {
                setLobList(cmEntityDOConvertor
                        .getLobReferenceData());
            }

            /** Render the Block that is to be Dispalyed and Disable others */
            setRenderProvider(Boolean.TRUE);
            setRenderNameSection(Boolean.TRUE);
            setRenderCarrierNam(Boolean.FALSE);
            setRenderOrganizationName(Boolean.FALSE);
            setRenderLOB(Boolean.FALSE);
            setCountyName(Boolean.FALSE);
            setRenderTradingPartner(Boolean.FALSE);

        }

        /** ENTITY TYPE -- UNENROLLED PROVIDER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_UNPROV, newValue))
        {

           

            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_UNPROV);
            /** Populates the Entity ID Type dropdown for Entity Type Unenrolled Provider */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Clear the Drop Down if any for Unenrolled Provider */
            
            getProvTypeList().clear();
            getLobList().clear();
            /** Render the Block that is to be Dispalyed and Disable others */
            setRenderProvider(Boolean.FALSE);
            setRenderNameSection(Boolean.TRUE);
            setRenderCarrierNam(Boolean.FALSE);
            setRenderOrganizationName(Boolean.TRUE);
            setRenderLOB(Boolean.FALSE);
            setCountyName(Boolean.FALSE);
            setRenderTradingPartner(Boolean.FALSE);

        }

        /** ENTITY TYPE -- TPL CARRIER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_TPL, newValue))
        {

           
            
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_TPL);
            /** Populates the Entity ID Type dropdown for Entity Type TPL Carrier */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }

            /** Clear the Drop Down if any for TPL */
           
            getProvTypeList().clear();
            getLobList().clear();
            /** Render the Block that is to be Dispalyed and Disable others */
            setRenderProvider(Boolean.FALSE);
            setRenderNameSection(Boolean.FALSE);
            setRenderCarrierNam(Boolean.TRUE);
            setRenderOrganizationName(Boolean.FALSE);
            setRenderLOB(Boolean.FALSE);
            setCountyName(Boolean.FALSE);
            setRenderTradingPartner(Boolean.FALSE);

        }
        
        /** ENTITY TYPE -- DISTRICT OFFICE */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_DO, newValue))
        {

           
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_DO);
            /** Populates the Entity ID Type dropdown for Entity Type District Office */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            setRenderProvider(Boolean.FALSE);
            setRenderNameSection(Boolean.FALSE);
            setRenderCarrierNam(Boolean.FALSE);
            setRenderOrganizationName(Boolean.TRUE);
            setRenderLOB(Boolean.FALSE);
            setCountyName(Boolean.FALSE);
            setRenderTradingPartner(Boolean.FALSE);
        }
        
        // Below else if condition is added for CR : ESPRD00808886
        /** ENTITY TYPE -- SC - Dynamic Content Information */
        
        else if (StringUtils.equalsIgnoreCase(
        		ContactManagementConstants.ENTITY_TYPE_SC, newValue))
        {

           
        	Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_SC);
            /** Populates the Entity ID Type dropdown for Entity Type District Office */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            setRenderProvider(Boolean.FALSE);
            setRenderNameSection(Boolean.FALSE);
            setRenderCarrierNam(Boolean.FALSE);
            setRenderOrganizationName(Boolean.TRUE);
            setRenderLOB(Boolean.FALSE);
            setCountyName(Boolean.FALSE);
            setRenderTradingPartner(Boolean.FALSE);
        }
        
        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_CT, newValue))
        {

           
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_CT);
            /** Populates the Entity ID Type dropdown for Entity Type County */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            setRenderProvider(Boolean.FALSE);
            setRenderNameSection(Boolean.FALSE);
            setRenderCarrierNam(Boolean.FALSE);
            setRenderOrganizationName(Boolean.FALSE);
            setRenderLOB(Boolean.FALSE);
            setCountyName(Boolean.TRUE);
            setRenderTradingPartner(Boolean.FALSE);

        }  else if (StringUtils.equalsIgnoreCase(
        		ContactManagementConstants.ENTITY_TYPE_TP, newValue))
        {

        	
           Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
           		ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_TP);
            /** Populates the Entity ID Type dropdown for Entity Type County */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            setRenderProvider(Boolean.FALSE);
            setRenderNameSection(Boolean.FALSE);
            setRenderCarrierNam(Boolean.FALSE);
            setRenderOrganizationName(Boolean.FALSE);
            setRenderLOB(Boolean.FALSE);
            setCountyName(Boolean.FALSE);
            setRenderTPLPlcyHldr(Boolean.TRUE);
            setRenderTradingPartner(Boolean.FALSE);
        }

        
        /** ENTITY TYPE -- trading partner Added for CR */
        //ADDED FOR THE CR ESPRD00436016 
        else if (StringUtils.equalsIgnoreCase(
        		ContactManagementConstants.ENTITY_TYPE_TD, newValue))
            {

               
                
                /** Populates the Entity ID Type dropdown for Entity Type trading Partner */
                Long sysListNumberEntityIDType= new Long("0181");
                List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumberEntityIDType);
                if(null != entIDTypeReferenceData){
                	
               	    setEntityIDTypeList(entIDTypeReferenceData); 
               		
               }
                             	
                    getProvTypeList().clear();
                    getLobList().clear();

                    /** Render the Block that is to be Dispalyed and Disable others */
                    setRenderTradingPartner(Boolean.TRUE);
                    setRenderNameSection(Boolean.FALSE);
                    setRenderProvider(Boolean.FALSE);
                    setRenderCarrierNam(Boolean.FALSE);
                    setRenderOrganizationName(Boolean.FALSE);
                    setRenderLOB(Boolean.FALSE);
                    setCountyName(Boolean.FALSE);
            } 
      //END FOR THE CR ESPRD00436016 


        else
        {
            /** ENTITY TYPE -- UNENROLLED MEMBER and other Entities */

           
            
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);
            /** Populates the Entity ID Type dropdown for Entity Type Other */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Clear the Drop Down if any for Unenrolled Member */
            
            getProvTypeList().clear();
            getLobList().clear();

            /** Render the Block that is to be Dispalyed and Disable others */
            setRenderNameSection(Boolean.TRUE);
            setRenderProvider(Boolean.FALSE);
            setRenderCarrierNam(Boolean.FALSE);
            setRenderOrganizationName(Boolean.FALSE);
            setRenderLOB(Boolean.FALSE);
            setCountyName(Boolean.FALSE);
            setRenderTradingPartner(Boolean.FALSE);

        }
		}
       
    	return entityIDTypeList;
    }

    /**
     * @param entityIDTypeList
     *            The entityIDTypeList to set.
     */
    public void setEntityIDTypeList(List entityIDTypeList)
    {
        this.entityIDTypeList = entityIDTypeList;
    }

    /**
     * @return Returns the lobList.
     */
    public List getLobList()
    {
        return lobList;
    }

    /**
     * @param lobList
     *            The lobList to set.
     */
    public void setLobList(List lobList)
    {
        this.lobList = lobList;
    }

    /**
     * @return Returns the searchResultsList.
     */
    public List getSearchResultsList()
    {
        return searchResultsList;
    }

    /**
     * @param searchResultsList
     *            The searchResultsList to set.
     */
    public void setSearchResultsList(List searchResultsList)
    {
        this.searchResultsList = searchResultsList;
    }

    /**
     * @return Returns the provTypeList.
     */
    public List getProvTypeList()
    {
        return provTypeList;
    }

    /**
     * @param provTypeList
     *            The provTypeList to set.
     */
    public void setProvTypeList(List provTypeList)
    {
        this.provTypeList = provTypeList;
    }

    /**
     * @return Returns the renderCarrierNam.
     */
    public Boolean getRenderCarrierNam()
    {
        return renderCarrierNam;
    }

    /**
     * @param renderCarrierNam
     *            The renderCarrierNam to set.
     */
    public void setRenderCarrierNam(Boolean renderCarrierNam)
    {
        this.renderCarrierNam = renderCarrierNam;
    }

    /**
     * @return Returns the renderSearchResult.
     */
    public Boolean getRenderSearchResult()
    {
        return renderSearchResult;
    }

    /**
     * @param renderSearchResult
     *            The renderSearchResult to set.
     */
    public void setRenderSearchResult(Boolean renderSearchResult)
    {
        this.renderSearchResult = renderSearchResult;
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
    public void setImageRender(String imageRender)
    {
        this.imageRender = imageRender;
    }

    /**
     * @return Returns the renderNameSection.
     */
    public Boolean getRenderNameSection()
    {
        return renderNameSection;
    }

    /**
     * @param renderNameSection
     *            The renderNameSection to set.
     */
    public void setRenderNameSection(Boolean renderNameSection)
    {
        this.renderNameSection = renderNameSection;
    }

    /**
     * @return Returns the renderOrganizationName.
     */
    public Boolean getRenderOrganizationName()
    {
        return renderOrganizationName;
    }

    /**
     * @param renderOrganizationName
     *            The renderOrganizationName to set.
     */
    public void setRenderOrganizationName(Boolean renderOrganizationName)
    {
        this.renderOrganizationName = renderOrganizationName;
    }

    /**
     * @return Returns the renderProvider.
     */
    public Boolean getRenderProvider()
    {
        return renderProvider;
    }

    /**
     * @param renderProvider
     *            The renderProvider to set.
     */
    public void setRenderProvider(Boolean renderProvider)
    {
        this.renderProvider = renderProvider;
    }

    /**
     * Returns the loadRefernceData.
     * @return  String
     */
    public String getLoadRefernceData()
    {
        CMEntityDOConvertor cmEnityDOConvertor = new CMEntityDOConvertor();
        List entityTypelist= new ArrayList();
        
        FacesContext fc = FacesContext.getCurrentInstance();

        PortletSession pSession = (PortletSession) fc.getExternalContext().getSession(true);
		if(pSession.getAttribute(ContactManagementConstants.ClearInqEntyData,pSession.APPLICATION_SCOPE) != null)
		{
			String ClearInqEntyData = (String) pSession.getAttribute(ContactManagementConstants.ClearInqEntyData,pSession.APPLICATION_SCOPE);
			if(ClearInqEntyData.equalsIgnoreCase(ContactManagementConstants.ClearInqEntyData)){
		          
		        setEntitySearchCriteriaVO(new EntitySearchCriteriaVO());
		        setRenderSearchResult(Boolean.FALSE);
		        setRenderNameSection(Boolean.FALSE);
		        setRenderProvider(Boolean.FALSE);
		        setRenderCarrierNam(Boolean.FALSE);
		        setRenderOrganizationName(Boolean.FALSE);
		        setCountyName(Boolean.FALSE);
		        setMainNotesRender(false);
		        
	            //Code added for defect - ESPRD00920227
			     setRenderErrorMsg(false);
		        
			}
			pSession.removeAttribute(ContactManagementConstants.ClearInqEntyData,pSession.APPLICATION_SCOPE);
		}
		
		 
            
        /**If Search page is Newly opened reset the already exsisting  values in the page */
        if (newSearchEntity)
        {
        	setEntitySearchCriteriaVO(new EntitySearchCriteriaVO());
            setRenderSearchResult(Boolean.FALSE);
           // setRenderNameSection(Boolean.TRUE);
            setRenderProvider(Boolean.FALSE);
            setRenderCarrierNam(Boolean.FALSE);
            setRenderOrganizationName(Boolean.FALSE);
          
        }
        
       
        /** Load Entity Type Drop Down */
        
      /*  if (cmEnityDOConvertor.getEntityTypeReferenceData() != null)
        {
            *//**
             * This method filters Out the Provider and member Valid avlues and
             * Populates the list
             *//*
            setEntityDropDownList(cmEnityDOConvertor
                    .getEntityTypeReferenceData());
        }*/
        
        entityTypelist = cmEnityDOConvertor.getEntityTypeReferenceData();
        if (entityTypelist != null )
        {
        	setEntityDropDownList(entityTypelist);   	
        }
        /** Loading IPC data from Search Correpondence to Entity Search */

		PortletSession portletSession = (PortletSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);

		Object corrSrchCrtVOobj = portletSession
				.getAttribute("CorrespondenceSearchCriteriaVO");
		//ESPRD00333432
		Object caseSrchCrtVOobj = portletSession
		.getAttribute("CaseRecordSearchCriteriaVO");
		
		Object corrSrchCrtVOEDMSobj = portletSession
		.getAttribute("searchCrsToSrchEntityVO");
		
		
		
		Object portletName = portletSession
				.getAttribute("portletName");
		
		
		if(logger.isDebugEnabled())
		{
			logger.debug(" CorrespondenceSearchCriteriaVO:::::::::::;"+corrSrchCrtVOobj);
		
			logger.info("++ portletName:::::::::::;"+portletName);
		}
		if (corrSrchCrtVOobj != null
				&& corrSrchCrtVOobj instanceof CorrespondenceSearchCriteriaVO) {
			
			logger.debug(" inside If Condition::::::::;");
			
			CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO = (CorrespondenceSearchCriteriaVO) corrSrchCrtVOobj;

			if (correspondenceSearchCriteriaVO != null) {
				
				logger.debug(" inside If Condition:::111111111111111111:::::;");
				CMEntitySearchControllerBean cmEntitySearchControllerBean = new CMEntitySearchControllerBean();
				cmEntitySearchControllerBean
						.doSrchCorrComAction(correspondenceSearchCriteriaVO);
				setCallFromSrchCorr(true);		
				setCallFromSrchEDMS(false);
			}
			
			if(portletName != null && (String.valueOf(portletName).equalsIgnoreCase("Search Correspondence")
					|| String.valueOf(portletName).equalsIgnoreCase("reassignCorrespondence")
							|| String.valueOf(portletName).equalsIgnoreCase("EntitySearchFromEDMS") || String.valueOf(portletName).equalsIgnoreCase("LogCaseAssociatedCR")))
			{
				logger.info("++ IPC flag set true inside If Condition:::2222222222222222222:::::;");
				if(String.valueOf(portletName).equalsIgnoreCase("EntitySearchFromEDMS")){
					setCallFromSrchCorr(false);	
					setCallFromSrchEDMS(true);
				}
				//Added for the defect ESPRD00802462
				if(String.valueOf(portletName).equalsIgnoreCase("LogCaseAssociatedCR")){
					setCallFromSrchCorr(false);	
					setCallFromSrchEDMS(false);
					setCallFromLogCase(true);	
				}
				
				setIpcFlag(true);
				
				//Code added for defect - ESPRD00920227
			     setRenderErrorMsg(false);
			}else{
				setIpcFlag(false);
			}
			portletSession.removeAttribute("CorrespondenceSearchCriteriaVO");
			setRenderNameSection(Boolean.FALSE);
		}
		//Below else block is added to show the Add Entity Link for fixing the Defect ESPRD00805206
		/*else
		{
			setCallFromSrchCorr(false);	
		}*/
		//ESPRD00333432
		if (caseSrchCrtVOobj != null
				&& caseSrchCrtVOobj instanceof CaseRecordSearchCriteriaVO) {
			setCallFromSrchEDMS(false);
			CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO = (CaseRecordSearchCriteriaVO) caseSrchCrtVOobj;

			if (caserecordSearchCriteriaVO != null) {
				CMEntitySearchControllerBean cmEntitySearchControllerBean = new CMEntitySearchControllerBean();
				cmEntitySearchControllerBean
						.doSrchCaseComAction(caserecordSearchCriteriaVO);
				setCallFromLogCase(true);				
			}
			
			if(portletName != null && String.valueOf(portletName).equalsIgnoreCase("Search Case"))
			{
				setCallFromSearchCase(true);
				setCallFromLogCase(false);//UC-PGM-CRM-033_ESPRD00624909_09jun2011
				setIpcFlag(true);
			}
			if(portletName != null && String.valueOf(portletName).equalsIgnoreCase("Log Case"))
			{
				setCallFromLogSrchCase(true);
				//ESPRD00333432
				setCallFromSearchCase(false);//UC-PGM-CRM-033_ESPRD00624909_09jun2011
				setCallFromLogCase(true);
				setIpcFlag(true);
			}
			portletSession.removeAttribute("CaseRecordSearchCriteriaVO");
		}else{
			// setCallFromSrchEDMS(false);
			//Below code is added to show the Add Entity Link for fixing the Defect ESPRD00805206
			//setCallFromSearchCase(false);
		}
		
		
		
		if(String.valueOf(portletName).equalsIgnoreCase("EntitySearchFromEDMS")){
			setCallFromSrchEDMS(true);
     		setIpcFlag(true);
		}
		
		//for onload cursor focus
		cursorFocusId="entityTypSrch";
        /** Set the LOB to Default Value in Search page */
        getEntitySearchCriteriaVO().setLineOfBusiness(
                ContactManagementConstants.DFLT_LOB_CODE_FOR_SEARCH_ENTITY);
        return loadRefernceData;
    
    }

    /**
     * @param loadRefernceData
     *            The loadRefernceData to set.
     */
    public void setLoadRefernceData(String loadRefernceData)
    {
        this.loadRefernceData = loadRefernceData;
    }

    /**
     * @return Returns the itemsPerPage.
     */
    public int getItemsPerPage()
    {
        return itemsPerPage;
    }

    /**
     * @param itemsPerPage The itemsPerPage to set.
     */
    public void setItemsPerPage(int itemsPerPage)
    {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * @return Returns the newSearchEntity.
     */
    public boolean isNewSearchEntity()
    {
        return newSearchEntity;
    }

    /**
     * @param newSearchEntity The newSearchEntity to set.
     */
    public void setNewSearchEntity(boolean newSearchEntity)
    {
        this.newSearchEntity = newSearchEntity;
    }
	/**
	 * @return Returns the renderLOB.
	 */
	public Boolean getRenderLOB() 
	{
		return renderLOB;
	}
	/**
	 * @param renderLOB The renderLOB to set.
	 */
	public void setRenderLOB(Boolean renderLOB) 
	{
		this.renderLOB = renderLOB;
	}
	/**
	 * @return Returns the countyName.
	 */
	public Boolean getCountyName() 
	{
		return countyName;
	}
	/**
	 * @param countyName The countyName to set.
	 */
	public void setCountyName(Boolean countyName) 
	{
		this.countyName = countyName;
	}

    /**
     * @return Returns the menuActionCode.
     */
    public String getMenuActionCode()
    {
        return menuActionCode;
    }

    /**
     * @param menuActionCode The menuActionCode to set.
     */
    public void setMenuActionCode(String menuActionCode)
    {
        this.menuActionCode = menuActionCode;
    }
    /**
     * @return Returns the disableEntityField.
     */
    //FOR HEAP DUMP ISSUE
   /* public boolean isDisableEntityField()
    {
        return disableEntityField;
    }
    *//**
     * @param disableEntityField The disableEntityField to set.
     *//*
    public void setDisableEntityField(boolean disableEntityField)
    {
        this.disableEntityField = disableEntityField;
    }*/
    //FOR HEAP DUMP ISSUE
	/**
	 * @return Returns the mainNotesRender.
	 */
	public boolean isMainNotesRender() {
		return mainNotesRender;
	}
	/**
	 * @param mainNotesRender The mainNotesRender to set.
	 */
	public void setMainNotesRender(boolean mainNotesRender) {
		this.mainNotesRender = mainNotesRender;
	}
	
	private boolean callFromSrchCorr = false;

	/**
	 * @return Returns the callFromSrchCorr.
	 */
	public boolean isCallFromSrchCorr() {
		return callFromSrchCorr;
	}

	/**
	 * @param callFromSrchCorr
	 *            The callFromSrchCorr to set.
	 */
	public void setCallFromSrchCorr(boolean callFromSrchCorr) {
		this.callFromSrchCorr = callFromSrchCorr;
	}	
	
	
	/** holds itemSelectedRowIndex */
	private int itemSelectedRowIndex = -1;


	private String dataMapIden;

	/**
	 * @return Returns the dataMapIden.
	 */
	public String getDataMapIden() {
		return dataMapIden;
	}

	/**
	 * @param dataMapIden
	 *            The dataMapIden to set.
	 */
	public void setDataMapIden(String dataMapIden) {
		this.dataMapIden = dataMapIden;
	}
	
	private boolean ipcFlag = false;
	
	/**
	 * @return Returns the ipcFlag.
	 */
	public boolean isIpcFlag() {
		return ipcFlag;
	}
	/**
	 * @param ipcFlag The ipcFlag to set.
	 */
	public void setIpcFlag(boolean ipcFlag) {
		this.ipcFlag = ipcFlag;
	}
	
	/**
	 * @return Returns the previousPageIndex.
	 */
	public int getPreviousPageIndex() {
		return previousPageIndex;
	}
	/**
	 * @param previousPageIndex The previousPageIndex to set.
	 */
	public void setPreviousPageIndex(int previousPageIndex) {
		this.previousPageIndex = previousPageIndex;
	}
	/**
	 * @return Returns the nextPageIndex.
	 */
	public int getNextPageIndex() {
		return nextPageIndex;
	}
	/**
	 * @param nextPageIndex The nextPageIndex to set.
	 */
	public void setNextPageIndex(int nextPageIndex) {
		this.nextPageIndex = nextPageIndex;
	}
	/**
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		return searchResults;
	}
	/**
	 * @param searchResults The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
	}
	/**
	 * @return Returns the fromIndex.
	 */
	public int getFromIndex() {
		return fromIndex;
	}
	/**
	 * @param fromIndex The fromIndex to set.
	 */
	public void setFromIndex(int fromIndex) {
		this.fromIndex = fromIndex;
	}
	/**
	 * @return Returns the toIndex.
	 */
	public int getToIndex() {
		return toIndex;
	}
	/**
	 * @param toIndex The toIndex to set.
	 */
	public void setToIndex(int toIndex) {
		this.toIndex = toIndex;
	}
    
	private boolean callFromLogCase = false;

	/**
	 * @return Returns the callFromSrchCorr.
	 */
	public boolean isCallFromLogCase() {
		return callFromLogCase;
	}
	/**
	 * @param callFromSrchCorr
	 *            The callFromSrchCorr to set.
	 */
	public void setCallFromLogCase(boolean callFromLogCase) {
		this.callFromLogCase = callFromLogCase;
	}	

	
	public String additional;
	
	/**
	 * @return Returns the additional.
	 */
	public String getAdditional() {
		return additional;
	}
	/**
	 * @param additional The additional to set.
	 */
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	
	private boolean providerDefaultFlag = false;

	/**
	 * @return Returns the providerDefaultFlag.
	 */
	public boolean isProviderDefaultFlag() {
		return providerDefaultFlag;
	}
	/**
	 * @param providerDefaultFlag The providerDefaultFlag to set.
	 */
	public void setProviderDefaultFlag(boolean providerDefaultFlag) {
		this.providerDefaultFlag = providerDefaultFlag;
	}
	private boolean callFromSearchCase = false;
	
	/**
	 * @return Returns the callFromSearchCase.
	 */
	public boolean isCallFromSearchCase() {
		return callFromSearchCase;
	}
	/**
	 * @param callFromSearchCase The callFromSearchCase to set.
	 */
	public void setCallFromSearchCase(boolean callFromSearchCase) {
		this.callFromSearchCase = callFromSearchCase;
	}
	private boolean callFromLogSrchCase = false;
	
	/**
	 * @return Returns the callFromLogSrchCase.
	 */
	public boolean isCallFromLogSrchCase() {
		return callFromLogSrchCase;
	}
	/**
	 * @param callFromLogSrchCase The callFromLogSrchCase to set.
	 */
	public void setCallFromLogSrchCase(boolean callFromLogSrchCase) {
		this.callFromLogSrchCase = callFromLogSrchCase;
	}
	/**
	 * @return Returns the disableAddEntity.
	 */
	public boolean isDisableAddEntity() {
		return disableAddEntity;
	}
	/**
	 * @param disableAddEntity The disableAddEntity to set.
	 */
	public void setDisableAddEntity(boolean disableAddEntity) {
		this.disableAddEntity = disableAddEntity;
	}
	private boolean memeberDefaultFlag = false;
	
	/**
	 * @return Returns the memeberDefaultFlag.
	 */
	public boolean isMemeberDefaultFlag() {
		return memeberDefaultFlag;
	}
	/**
	 * @param memeberDefaultFlag The memeberDefaultFlag to set.
	 */
	public void setMemeberDefaultFlag(boolean memeberDefaultFlag) {
		this.memeberDefaultFlag = memeberDefaultFlag;
	}
	
	
	private String target;
	
	/**
	 * @return Returns the target.
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * @param target The target to set.
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
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
	
    // added for CR for CR ESPRD00486064 starts
	/**
     * Variable to reneder Block for TPL Policy Holder.
     */
    private Boolean renderTPLPlcyHldr = Boolean.FALSE;
    
        
	/**
	 * @return Returns the renderTPLPlcyHldr.
	 */
	public Boolean getRenderTPLPlcyHldr() {
		return renderTPLPlcyHldr;
	}
	/**
	 * @param renderTPLPlcyHldr The renderTPLPlcyHldr to set.
	 */
	public void setRenderTPLPlcyHldr(Boolean renderTPLPlcyHldr) {
		this.renderTPLPlcyHldr = renderTPLPlcyHldr;
	}
    // added for CR for CR ESPRD00486064 starts
	
//	 added for CR for CR ESPRD00486064 starts
	
	private boolean callFromSrchEDMS = false;
	/**
	 * @return Returns the callFromSrchEDMS.
	 */
	public boolean isCallFromSrchEDMS() {
		return callFromSrchEDMS;
	}
	/**
	 * @param callFromSrchEDMS The callFromSrchEDMS to set.
	 */
	public void setCallFromSrchEDMS(boolean callFromSrchEDMS) {
		this.callFromSrchEDMS = callFromSrchEDMS;
	}
	
	private String focusThisId;
	/**
	 * @return Returns the focusThisId.
	 */
	public String getFocusThisId() {
		return focusThisId;
	}
	/**
	 * @param focusThisId The focusThisId to set.
	 */
	public void setFocusThisId(String focusThisId) {
		this.focusThisId = focusThisId;
	}
	//
	 private Boolean renderTradingPartner = Boolean.FALSE;
	//
	/**
	 * @return Returns the renderTradingPartner.
	 */
	public Boolean getRenderTradingPartner() {
		return renderTradingPartner;
	}
	/**
	 * @param renderTradingPartner The renderTradingPartner to set.
	 */
	public void setRenderTradingPartner(Boolean renderTradingPartner) {
		this.renderTradingPartner = renderTradingPartner;
	}
	///////
	/**
     * List to hold values for Dropdown for Trading Partner Status list.
     */
   
    private List tradingParnterStatusList = new ArrayList();
	
	///////
	/**
	 * @return Returns the tradingParnterStatusList.
	 */
	public List getTradingParnterStatusList() {
		return tradingParnterStatusList;
	}
	/**
	 * @param tradingParnterStatusList The tradingParnterStatusList to set.
	 */
	public void setTradingParnterStatusList(List tradingParnterStatusList) {
		this.tradingParnterStatusList = tradingParnterStatusList;
	}
	/**
     * List to hold values for Dropdown for Trading Partner Classification list.
     */
  
    private List tradingPartnerclassificationList = new ArrayList();
	/**
	 * @return Returns the tradingPartnerclassificationList.
	 */
	public List getTradingPartnerclassificationList() {
		return tradingPartnerclassificationList;
	}
	/**
	 * @param tradingPartnerclassificationList The tradingPartnerclassificationList to set.
	 */
	public void setTradingPartnerclassificationList(
			List tradingPartnerclassificationList) {
		this.tradingPartnerclassificationList = tradingPartnerclassificationList;
	}
	/**
	 * @return Returns the renderRequired.
	 */
	public Boolean getRenderRequired() {
		return renderRequired;
	}
	/**
	 * @param renderRequired The renderRequired to set.
	 */
	public void setRenderRequired(Boolean renderRequired) {
		this.renderRequired = renderRequired;
	}
	
	private int startIndexForSrchEntity=0;
	
	
	/**
	 * @return Returns the startIndexForSrchEntity.
	 */
	public int getStartIndexForSrchEntity() {
		return startIndexForSrchEntity;
	}
	/**
	 * @param startIndexForSrchEntity The startIndexForSrchEntity to set.
	 */
	public void setStartIndexForSrchEntity(int startIndexForSrchEntity) {
		this.startIndexForSrchEntity = startIndexForSrchEntity;
	}
	
	// Begin - Added this block of code for the HeapDump Fix
	/**
	 * Holds the value of searchResultRowIndex. 
	 */
	private int searchResultRowIndex;
	/**
	 * @return the searchResultRowIndex
	 */
	public int getSearchResultRowIndex() {
		return searchResultRowIndex;
	}
	/**
	 * @param searchResultRowIndex the searchResultRowIndex to set
	 */
	public void setSearchResultRowIndex(int searchResultRowIndex) {
		this.searchResultRowIndex = searchResultRowIndex;
	}
	// End - Added this block of code for the HeapDump Fix
	
	private int startIndexForNOtes=0;
	
	public int getStartIndexForNOtes() {
		return startIndexForNOtes;
	}
	public void setStartIndexForNOtes(int startIndexForNOtes) {
		this.startIndexForNOtes = startIndexForNOtes;
	}
	// Added for CRM_CASE-GAI-RecursiveCall-FIX
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
	/**
	 * @return the userSk
	 */
	public Long getUserSk() {
		return userSk;
	}
	/**
	 * @param userSk the userSk to set
	 */
	public void setUserSk(Long userSk) {
		this.userSk = userSk;
	}

	private Long userSk;
	private String businessUnitDesc;
	/**
	 * @return the businessUnitDesc
	 */
	public String getBusinessUnitDesc() {
		return businessUnitDesc;
	}
	/**
	 * @param businessUnitDesc the businessUnitDesc to set
	 */
	public void setBusinessUnitDesc(String businessUnitDesc) {
		this.businessUnitDesc = businessUnitDesc;
	}
	
	// Code added for defect - ESPRD00920227 to handle the error message.
	private boolean renderErrorMsg = false;
	
	public boolean isRenderErrorMsg() {
		return renderErrorMsg;
	}
	public void setRenderErrorMsg(boolean renderErrorMsg) {
		this.renderErrorMsg = renderErrorMsg;
	}
	
}