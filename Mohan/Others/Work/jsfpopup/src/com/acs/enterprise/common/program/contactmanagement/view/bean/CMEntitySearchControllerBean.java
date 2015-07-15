/*
 * Created on Nov 1, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.acs.enterprise.common.address.application.exception.AddressBusinessException;
import com.acs.enterprise.common.address.delegate.CommonAddressDelegate;
import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseBeanHelper;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseControllerBean;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonModuleAddressInfo;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonNotesControllerBean;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMEntityDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CorrespondenceSearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.EntitySearchCriteriaVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.EntitySearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.CMEntityDOConvertor;
import com.acs.enterprise.common.program.contactmanagement.view.helper.ContactManagementHelper;
import com.acs.enterprise.common.program.contactmanagement.view.helper.MaintainContactManagementUIConstants;
import com.acs.enterprise.common.security.services.fieldlevelservice.FieldAccessControl;
import com.acs.enterprise.common.security.services.fieldlevelservice.SecurityFLSServiceException;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLPolicyHolder;
import com.acs.enterprise.mmis.provider.common.domain.TradingPartner;

import com.acs.enterprise.mmis.operations.tpladministration.common.delegate.TPLPolicyDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLPolicyHolderDetailsVO;

import java.util.ResourceBundle;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import com.acs.enterprise.mmis.provider.enrollment.application.exception.ProviderAppMntException;
import com.acs.enterprise.mmis.provider.enrollment.common.delegate.ProviderEnrollmentDelegate;
import com.acs.enterprise.common.base.common.exception.EnterpriseSystemException;
import com.acs.enterprise.mmis.provider.common.vo.ProviderInformationRequestVO;
import com.acs.enterprise.mmis.provider.common.domain.Provider;
import com.acs.enterprise.mmis.provider.common.helper.ProviderDataConstants;
/**
 * @author vijaymai TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * @author vijaymai TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class CMEntitySearchControllerBean
        extends EnterpriseBaseControllerBean
{
    /**
     * Generating object of EnterpriseLogger.
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CMEntitySearchControllerBean.class);

    /** Creates reference of data Bean */
    private CMEntitySearchDataBean cmEntitySearchDataBean = getCmEntitySearchDataBean();

    /** Creates reference of data Bean */
  //  private CommonEntityDataBean cmEntityDataBean = getCommonEntityDataBean();

    /** Creates Reference of CMEntityDOConvertor */
    private CMEntityDOConvertor cmEntityDOConvertor = new CMEntityDOConvertor();




    /**
     * This field is used to store whether user has permission.
     */
    private boolean controlRequired = true; // Newly added

    private String intialData;

    /**
     * This field is used to implement Gray mode changes.
     */
    private String link2Show;


	/**
	 * @return Returns the controlRequired.
	 */
	public boolean isControlRequired() {
		return controlRequired;
	}
	/**
	 * @param controlRequired The controlRequired to set.
	 */
	public void setControlRequired(boolean controlRequired) {
		this.controlRequired = controlRequired;
	}
    /**
     * Newly Added
     * This method gets the permission level for the logged in user
     *
     */
    public void getUserPermission()
    {
    	String userPermission = "";
		String userid = getUserID();
		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		try
		{
			userPermission = fieldAccessControlImpl.getFiledAccessPermission(
					ContactManagementConstants.MAINTAIN_ENTITY_PAGE, userid);
		}
		catch (SecurityFLSServiceException e)
		{
			if(logger.isDebugEnabled())
			{
				logger.debug("error on FilterControllerBean.getUserPermission :"+ e);
			}
		}

		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("displayMode",userPermission);
		userPermission = userPermission != null ? userPermission.trim() : "";
		if(logger.isInfoEnabled())
		{
			logger.info("userPermission in SEARCH ENTIY::::::" + userPermission );
		}

		if (userPermission.equals("r"))
		{
			controlRequired = false;
		}
	}

    /**
     * Newly Added
     * This method get the User ID
     *
     * @return String
     */
    private String getUserID() {
		String userID = null;
		try {
			/*HttpServletRequest renderrequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse renderresponse = null;
			EnterpriseUserProfile enterpriseUserProfile = getUserData(renderrequest, renderresponse);
			if (enterpriseUserProfile != null) {
				userID = enterpriseUserProfile.getUserId();
			}*/
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		userID = getLoggedInUserID(portletRequest);
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("LOGGED_IN_USERID", userID);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			}

		return userID;
	}

    /**
     * This method returns navigates to Maintain Entity page.
     *
     * @return String
     */
    public String addEntity()
    {

    	FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getRequestMap().put("maintainEntityId", null);


        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This Method Performs the Reset Operation in Search page.
     *
     * @return Success
     */

    public String resetSearch()
    {
        CommonEntityDataBean cmEntityDataBean = getCommonEntityDataBean();
        Map appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
		appMap.put("inqEntitySearch","inqEntitySearch");
        cmEntitySearchDataBean
                .setEntitySearchCriteriaVO(new EntitySearchCriteriaVO());

        cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
        cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
        cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
        cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
        cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
        cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
        cmEntityDataBean.setMainNotesRender(false);
        cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);//CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010

		if(getCmEntitySearchDataBean().getSearchResultsList()!=null)
		{
			getCmEntitySearchDataBean().getSearchResultsList().clear();		
		}

        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This Method Renders the Different Search Entity Block based on the Entity
     * Type .
     *
     * @return String
     */
    public String renderRespEntityBlock()
    {
    
    	CommonEntityDataBean cmEntityDataBean = getCommonEntityDataBean();
        String newValue = (String) cmEntitySearchDataBean
                .getEntitySearchCriteriaVO().getEntityType();

        resetSearch();

        cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityType(
                newValue);
        
        /** This will not reset the Search Page on Value Change of Entity Type */
        cmEntitySearchDataBean.setNewSearchEntity(false);

        /** ENTITY TYPE -- MEMBER */

        if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_MEM, newValue))
        {
            
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_MEM);
            /** Populates the Entity ID Type dropdown for Entity Type Member */
            List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber);
            if (entIDTypeReferenceData != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(entIDTypeReferenceData);
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTradingPartner(Boolean.FALSE);
        }
        
        /** ENTITY TYPE -- PROVIDER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_PROV, newValue))
        {

           
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    "G_REP", "PROV_PSTN_CD");
            /** Populates the Entity ID Type dropdown for Entity Type Provider */
            List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.PROVIDER,sysListNumber);
            if (entIDTypeReferenceData != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(entIDTypeReferenceData);
            }

            /** Populates the Provider Type dropdown for Entity Type Provider */
            List provTypeReferenceData = cmEntityDOConvertor.getProvTypeReferenceData();
            if (provTypeReferenceData != null)
            {
                cmEntitySearchDataBean.setProvTypeList(provTypeReferenceData);
            }

            /** Populates the LOB dropdown for Entity Type Provider */
            List lobReferenceData = cmEntityDOConvertor.getLobReferenceData();
            if (lobReferenceData != null)
            {
                cmEntitySearchDataBean.setLobList(lobReferenceData);
            }

            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTradingPartner(Boolean.FALSE);

        }

        /** ENTITY TYPE -- UNENROLLED PROVIDER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_UNPROV, newValue))
        {

            
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_UNPROV);
            /** Populates the Entity ID Type dropdown for Entity Type Unenrolled Provider */
            List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) ;
            if (entIDTypeReferenceData != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(entIDTypeReferenceData);
            }
            /** Clear the Drop Down if any for Unenrolled Provider */

            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            //for fixing defect ESPRD00576206
            cmEntitySearchDataBean.setRenderRequired(Boolean.FALSE);
        }

        /** ENTITY TYPE -- TPL CARRIER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_TPL, newValue))
        {

           
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_TPL);
            /** Populates the Entity ID Type dropdown for Entity Type TPL Carrier */
            List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) ;
            if (entIDTypeReferenceData != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(entIDTypeReferenceData);
            }

            /** Clear the Drop Down if any for TPL */

            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTradingPartner(Boolean.FALSE);
        }
//      added for CR ESPRD00486064 starts
        else if (StringUtils.equalsIgnoreCase(
        		ContactManagementConstants.ENTITY_TYPE_TP, newValue))
        {

            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
           		ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_TP);
            /** Populates the Entity ID Type dropdown for Entity Type County */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.TRUE);
            //for fixing defect ESPRD00576206
            cmEntitySearchDataBean.setRenderRequired(Boolean.TRUE);
        }
        // added for CR ESPRD00486064 ends
        /** ENTITY TYPE -- DISTRICT OFFICE */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_DO, newValue))
        {

            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_DO);
            /** Populates the Entity ID Type dropdown for Entity Type District Office */
            List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber);
            if (entIDTypeReferenceData != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(entIDTypeReferenceData);
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTradingPartner(Boolean.FALSE);
        }
        
        // Below else if Block is added for the CR : ESPRD00808886
        
        /** ENTITY TYPE -- SC - Dynamic Content Information */
        
       else if (StringUtils.equalsIgnoreCase(
    		   ContactManagementConstants.ENTITY_TYPE_SC, newValue))
        {
        	System.out.println("Rendering for SC Type Entity");
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_SC);
            //** Populates the Entity ID Type dropdown for Entity Type District Office *//*
            List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber);
            if (entIDTypeReferenceData != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(entIDTypeReferenceData);
            }
            //** Render the Block that is to be Dispalyed and Disable others *//*
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTradingPartner(Boolean.FALSE);
        }
        
        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_CT, newValue))
        {

        	Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_CT);
            /** Populates the Entity ID Type dropdown for Entity Type County */
            List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber);
            if (entIDTypeReferenceData != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(entIDTypeReferenceData);
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTradingPartner(Boolean.FALSE);
        }
       //ADDED FOR THE CR ESPRD00436016
        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_TD, newValue))
        {
            Long sysListNumberEntityIDType= new Long("0181");
            Long sysListNumberStatus= new Long("0003");
            Long sysListNumberClassification= new Long("1003");
            
            List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumberEntityIDType);
            List statusListReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.EDI_TRADING,sysListNumberStatus);
            List classificationReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.EDI_TRADING,sysListNumberClassification);
            if(null != entIDTypeReferenceData){
            	 cmEntitySearchDataBean.setEntityIDTypeList(entIDTypeReferenceData);  
            }
            if(null != statusListReferenceData){
           	 cmEntitySearchDataBean.setTradingParnterStatusList(statusListReferenceData);  
           
           }
            if(null != classificationReferenceData){
           	 cmEntitySearchDataBean.setTradingPartnerclassificationList(classificationReferenceData);            	 
           }
            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTradingPartner(Boolean.TRUE);
        }
        /*
         * for fixing defect ESPRD00603442
         */
        else if(StringUtils.equalsIgnoreCase(
                ContactManagementConstants.EMPTY_STRING, newValue)){
        	 cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
        	 cmEntitySearchDataBean.setRenderTradingPartner(Boolean.FALSE);
        }
//      END FOR THE CR ESPRD00436016

        else
        {
            /** ENTITY TYPE -- UNENROLLED MEMBER and other Entities */


            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);
            /** Populates the Entity ID Type dropdown for Entity Type Other */
            List entIDTypeReferenceData = cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber);
            if (entIDTypeReferenceData != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(entIDTypeReferenceData);
            }
            /** Clear the Drop Down if any for Unenrolled Member */

            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();

            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            //for fixing defect ESPRD00576206
            cmEntitySearchDataBean.setRenderRequired(Boolean.TRUE);
        }

        cmEntityDataBean.setMainNotesRender(false);
      

        return ContactManagementConstants.RENDER_SUCCESS;
    
    }

    /**
     * Gets reference of CMEntitySearchDataBean.
     *
     * @author Wipro.
     * @return CMEntitySearchDataBean
     */
    public CMEntitySearchDataBean getCmEntitySearchDataBean()
    {

        FacesContext fc = FacesContext.getCurrentInstance();
        CMEntitySearchDataBean cmEntitySearchDataBean = (CMEntitySearchDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + ContactManagementConstants.CMSEARCHENTITY_BEAN_NAME + "}")
                .getValue(fc);

        return cmEntitySearchDataBean;
    }

    /**
     * This method gets the search resluts on performing search operation
     *
     * @return String
     */
    public String getEntities()
    {
    	
        String searchNotes = null;
		searchNotes="/jsp/searchnotes/searchNotes.jsp";
		CommonNotesControllerBean commonNotesControllerBean= new  CommonNotesControllerBean();
		CommonEntityDataBean cmEntityDataBean = getCommonEntityDataBean();

        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();
        Map reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String reqPath=FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo();
		if(logger.isDebugEnabled())
		{
			logger.debug(" Request Path Name "+reqPath);
		}
        boolean flag = validateSearchCriteria(entitySearchCriteriaVO);
        
        entitySearchCriteriaVO.setStartIndex(ProgramNumberConstants.INT_ZERO);
       /* entitySearchCriteriaVO
                .setRowsPerPage(ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);*/
        entitySearchCriteriaVO.setStartIndex(ProgramNumberConstants.INT_ZERO);
       /* entitySearchCriteriaVO
                .setRowsPerPage(ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);*/

        /**
         * Added code for setting the return type in ascending order for  search
         * **/

        EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
        CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
        cmEntityDataBean.setMainNotesRender(false);
        if (flag)
        {

            try
            {

                enterpriseSearchResultsVO = cmEntityDelegate
                        .getEntities(entitySearchCriteriaVO);
                cmEntitySearchDataBean.setStartIndexForSrchEntity(0);
                if (enterpriseSearchResultsVO.getSearchResults().size() == 0)
                {
                    
                    setErrorMessage(
                            ProgramConstants.NO_SEARCH_RECORD_FOUND,
                            new Object[] {}, ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
                            null);

                    cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
                    if(cmEntitySearchDataBean.getSearchResultsList() != null)
                    {
                    	cmEntitySearchDataBean.getSearchResultsList().clear();
                    }
                    return ContactManagementConstants.RENDER_SUCCESS;

                }else{
                	Iterator entities = enterpriseSearchResultsVO.getSearchResults().iterator();
                	Set EntityIDSet = new HashSet();
                	while(entities.hasNext())
                	{
                		EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO)entities.next();
                		if(entitySearchResultsVO.getEntityID() !=null)
                    	{
                		
                		if(EntityIDSet.contains(entitySearchResultsVO.getEntityID().toString()))
                		{
                			entities.remove();
                		
                		}
                	}	
                		EntityIDSet.add(entitySearchResultsVO.getEntityID());
                	}
                	EntityIDSet.clear();
                }

                if (enterpriseSearchResultsVO.getSearchResults().size() == 1)
                {
                	
                    EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO) enterpriseSearchResultsVO
                            .getSearchResults().get(0);

                    String entType = entitySearchCriteriaVO.getEntityType();

                    String entityId = entitySearchResultsVO.getEntityID();
                    if(logger.isDebugEnabled())
                    {
                    	logger.debug("entityId=============dsfd====="+entityId);
                    }
//                  CR_ESPRD00486064_UC-PGM-CRM-013_08JUL2010
                    /*if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TP,entType)){
                    	
                    	showDetailPortlet(entityId);
                    }*/
                    //EOF CR_ESPRD00486064_UC-PGM-CRM-013_08JUL2010

                    if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM,entType)
                            || StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_PROV, entType))
                    {
                        entityId = entitySearchResultsVO.getSystemID()
                                .toString();
                    }
                    else if(StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV,entType)
                    		&& entitySearchResultsVO.getSystemID() != null)
                    {
                        
                    	entityId = entitySearchResultsVO.getSystemID().toString();
                    	//entitySearchCriteriaVO.setEntityType(ContactManagementConstants.ENTITY_TYPE_PROV);
                    }
                    else if(StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TP, entType)
                    		&& entitySearchResultsVO.getCommonEntitySK() != null)
        	        {
        	            
        	        	entityId = entitySearchResultsVO.getCommonEntitySK().toString();
        	        }
					else if(StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TD, entType)
                    		&& entitySearchResultsVO.getCommonEntitySK() != null)
        	        {
        	           
        	        	entityId = entitySearchResultsVO.getEntityID().toString();
        	        	
        	        }

//                  added by ics
                    if (cmEntitySearchDataBean.isCallFromLogCase()) {
						cmEntitySearchDataBean.setCallFromLogCase(false);

						if (entitySearchCriteriaVO != null && entitySearchResultsVO != null) {
							Map dataMap = new HashMap();

							dataMap.put("EntityType", entitySearchCriteriaVO
									.getEntityType());
							dataMap.put("EntityIDType", entitySearchCriteriaVO
									.getEntityIDType());
							//
							
							
							if(entitySearchResultsVO.getCurrentAltIDTypeCode() != null)
							{
								dataMap.put("EntityIDType",entitySearchResultsVO.getCurrentAltIDTypeCode());
								
							}
							//
							dataMap.put("EntityID", entitySearchResultsVO.getEntityID());
							dataMap.put("CommonEntitySK", entitySearchResultsVO
									.getCommonEntitySK());
							dataMap.put("Identification", cmEntitySearchDataBean
									.getDataMapIden());
							dataMap.put("search", cmEntitySearchDataBean
									.getAdditional());
							
							
							/* Fixed for defect ESPRD00448067 Starts */
							dataMap.put("target",getCmEntitySearchDataBean().getTarget());
							
							/* Fixed for defect ESPRD00448067 End */


							ActionRequest actionRequest = (ActionRequest) FacesContext
									.getCurrentInstance().getExternalContext().getRequest();

							PortletSession portletSession = (PortletSession) FacesContext
							.getCurrentInstance().getExternalContext().getSession(false);

							String fromWhichPortlet = portletSession
									.getAttribute("portletName").toString();
							
							if(fromWhichPortlet.equals("Search Correspondence"))
							{
								actionRequest.setAttribute("EntitySearchMap", dataMap);
							}
							else if(fromWhichPortlet.equals("Correspondence"))
							{
								actionRequest.setAttribute("srcEntityMap", dataMap);
							}
							else if(fromWhichPortlet.equals("Search Case")){
								entitySearchCriteriaVO.setMap(dataMap);
								actionRequest.setAttribute("EntitySearchMap2", entitySearchCriteriaVO);
							} else if(fromWhichPortlet.equals("Log Case")){

								
								entitySearchCriteriaVO.setMap(dataMap);
								actionRequest.setAttribute("EntSrchLogCaseMap", entitySearchCriteriaVO);

							}
							//Added for the Defect ESPRD00802462
							else if(fromWhichPortlet.equals("LogCaseAssociatedCR")){

								
								entitySearchCriteriaVO.setMap(dataMap);
								actionRequest.setAttribute("EntSrchLogCaseCRMap", dataMap);
							}
							//Below code commented for fixing the Defect - ESPRD00778018
							if (!(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
				                    ContactManagementConstants.ENTITY_TYPE_MEM))
									&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
											ContactManagementConstants.ENTITY_TYPE_PROV))
									&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
											ContactManagementConstants.ENTITY_TYPE_TPL))
									/*&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
											ContactManagementConstants.ENTITY_TYPE_DO))
									&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
				    	                    ContactManagementConstants.ENTITY_TYPE_CT))*/
									&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
				    	                    ContactManagementConstants.ENTITY_TYPE_TD))
									&& (entitySearchCriteriaVO.getEntityIDType() == null
									|| entitySearchCriteriaVO.getEntityIDType().equals("")))
				            {
								
								dataMap.put("EntityIDType", "CM");
				            }
							portletSession.removeAttribute("portletName");
						}
					}//UC-PGM-CRM-033_ESPRD00624909_09jun2011
                    else if(cmEntitySearchDataBean.isCallFromSearchCase()){
                    	cmEntitySearchDataBean.setCallFromSearchCase(false);
                    		
            			if (entitySearchCriteriaVO != null && entitySearchResultsVO != null) {
            				Map dataMap = new HashMap();
            				
            				dataMap.put("EntityType", entitySearchCriteriaVO
            						.getEntityType());
            				
            				dataMap.put("EntityIDType", entitySearchCriteriaVO.getEntityIDType());
            				
            				dataMap.put("EntityID", entitySearchResultsVO.getEntityID());
            				
            				
            				if(entitySearchResultsVO.getCurrentAltIDTypeCode() != null)
            				{
            					
            					dataMap.put("EntityIDType",entitySearchResultsVO.getCurrentAltIDTypeCode());
            					dataMap.put("EntityID",entitySearchResultsVO.getCurrAltID());
            					
            				}
            				PortletRequest pRequest = (PortletRequest) FacesContext
    						.getCurrentInstance().getExternalContext().getRequest();
            				
            				dataMap.put("CommonEntitySK", entitySearchResultsVO.getCommonEntitySK());
            				
            				dataMap.put("Identification", cmEntitySearchDataBean.getDataMapIden());
            				dataMap.put("search", pRequest.getPortletSession().getAttribute("typeOfEntity"));

            				dataMap.put("target",getCmEntitySearchDataBean().getTarget());

            				
            				String fromWhichPortlet = pRequest.getPortletSession()
            						.getAttribute("portletName").toString();

            				

            				if(fromWhichPortlet.equals("Search Case"))
            				{
            					entitySearchCriteriaVO.setMap(dataMap);
            					pRequest.getPortletSession().setAttribute("EntitySearchMap2", entitySearchCriteriaVO);
            					pRequest.setAttribute("EntitySearchMap2", entitySearchCriteriaVO);
            					
            				}
            				//Below code commented for fixing the Defect - ESPRD00778018
            				if (!(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
            	                    ContactManagementConstants.ENTITY_TYPE_MEM))
            						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
            								ContactManagementConstants.ENTITY_TYPE_PROV))
            						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
            								ContactManagementConstants.ENTITY_TYPE_TPL))
            						/*&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
            								ContactManagementConstants.ENTITY_TYPE_DO))
            						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
            	    	                    ContactManagementConstants.ENTITY_TYPE_CT))*/)
            	            {
            				
            					dataMap.put("EntityIDType", "CM");
            	            }

            				pRequest.getPortletSession().removeAttribute("portletName");
            			}
                    
                    }//EOf UC-PGM-CRM-033_ESPRD00624909_09jun2011




                   else{


					if (cmEntitySearchDataBean.isCallFromSrchCorr()||cmEntitySearchDataBean.isCallFromSrchEDMS()) {
						cmEntitySearchDataBean.setCallFromSrchCorr(false);
						if(logger.isInfoEnabled())
						{
							logger.info("CMEntitySearchControllerBean :: getEntities EntityType "
								+ entitySearchCriteriaVO.getEntityType());
							logger.info("CMEntitySearchControllerBean :: getEntities EntityIDType "
								+ entitySearchCriteriaVO.getEntityIDType());
							logger.info("CMEntitySearchControllerBean :: getEntities EntityID "
								+ entitySearchResultsVO.getEntityID());
							logger.info("CMEntitySearchControllerBean :: getEntities CommonEntitySK "
								+ entitySearchResultsVO.getCommonEntitySK());
						}

						if (entitySearchCriteriaVO != null && entitySearchResultsVO != null) {
							Map dataMap = new HashMap();

							dataMap.put("EntityType", entitySearchCriteriaVO.getEntityType());
							dataMap.put("EntityIDType", entitySearchCriteriaVO.getEntityIDType());
							if(logger.isInfoEnabled())
							{
								logger.info("CurrentAltIDTypeCode======"+entitySearchResultsVO.getCurrentAltIDTypeCode());
							}
							if(entitySearchResultsVO.getCurrentAltIDTypeCode() != null)
							{
								dataMap.put("EntityIDType",entitySearchResultsVO.getCurrentAltIDTypeCode());
							}
							dataMap.put("EntityID", entitySearchResultsVO.getEntityID());
							dataMap.put("CommonEntitySK", entitySearchResultsVO
									.getCommonEntitySK());
							dataMap.put("Identification", cmEntitySearchDataBean
									.getDataMapIden());

							ActionRequest actionRequest = (ActionRequest) FacesContext
									.getCurrentInstance().getExternalContext().getRequest();

							PortletSession portletSession = (PortletSession) FacesContext
							.getCurrentInstance().getExternalContext().getSession(false);

							String fromWhichPortlet = portletSession
									.getAttribute("portletName").toString();
							
							if(fromWhichPortlet.equals("Search Correspondence"))
							{
								actionRequest.setAttribute("EntitySearchMap", dataMap);
							}else if(fromWhichPortlet.equals("reassignCorrespondence"))
							{
								actionRequest.setAttribute("EntitySearchMap1", dataMap);
							}else if(fromWhichPortlet.equals("EntitySearchFromEDMS")){
								
								actionRequest.setAttribute("EntitySearchMap3", dataMap);
							}
							else if(fromWhichPortlet.equals("Correspondence"))
							{
								actionRequest.setAttribute("srcEntityMap", dataMap);
							}
							
							if (!(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
				                    ContactManagementConstants.ENTITY_TYPE_MEM))
									&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
											ContactManagementConstants.ENTITY_TYPE_PROV))
									&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
											ContactManagementConstants.ENTITY_TYPE_TPL))
											
											//Below code commented for fixing the defect ESPRD00778018 on 12-04-2012
											
									/*&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
											ContactManagementConstants.ENTITY_TYPE_DO))
									&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
				    	                    ContactManagementConstants.ENTITY_TYPE_CT))*/
											
									 && !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
				    	                    ContactManagementConstants.ENTITY_TYPE_TD))									
									&& (entitySearchCriteriaVO.getEntityIDType() == null
									|| entitySearchCriteriaVO.getEntityIDType().equals("")))
				            {
								
								dataMap.put("EntityIDType", "CM");
				            }
							portletSession.removeAttribute("portletName");
						}
					}

					else
					{
						if ( reqPath != null && reqPath.equalsIgnoreCase(searchNotes.trim()))

						{
							 FacesContext context = FacesContext.getCurrentInstance();
							 Map map1 = context.getExternalContext().getRequestParameterMap();

							 String entityType = (String) map1.get(ContactManagementConstants.GLOBAL_ENTITY_TYPE);
							 String entID =(String) map1.get(ContactManagementConstants.GLOBAL_ENTITY_NAME);
							 Long  entityID = new Long(entityId.trim());
							 HttpServletRequest req = (HttpServletRequest)context.getExternalContext().getRequest();
							 req.setAttribute("entityType", entitySearchCriteriaVO.getEntityType());
							 req.setAttribute("entityID", entitySearchResultsVO.getEntityID());
							 req.setAttribute("entityIDType ", entitySearchCriteriaVO.getEntityIDType());
							 if(logger.isDebugEnabled())
							 {
								 logger.debug("entityType::::::: "+ entitySearchCriteriaVO.getEntityType());
								 logger.debug("entID :::::"+ entitySearchResultsVO.getEntityID());
							 }
							 if(ContactManagementConstants.ENTITY_TYPE_TP.equalsIgnoreCase(
							 			entitySearchCriteriaVO.getEntityType()))
							{
							 	req.setAttribute("entityID", entitySearchResultsVO.getCommonEntitySK().toString());
							}
							  if (entitySearchCriteriaVO.getEntityType() != null
										&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
										&& entitySearchCriteriaVO.getEntityType()
												.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_PROV))

								{

									cmEntitySearchDataBean.setRenderProvider(Boolean.TRUE);
									cmEntitySearchDataBean.getEntitySearchCriteriaVO()
											.setEntityType(entitySearchCriteriaVO.getEntityType());
									cmEntitySearchDataBean.getEntitySearchCriteriaVO()
											.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
									cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
													entitySearchCriteriaVO.getEntityID());
								}

							 if (entitySearchCriteriaVO.getEntityType() != null
									&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
									&& entitySearchCriteriaVO.getEntityType()
											.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM))

							 {

							 	cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityType(entitySearchCriteriaVO.getEntityType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
												entitySearchCriteriaVO.getEntityID());

							 }
							 if (entitySearchCriteriaVO.getEntityType() != null
									&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
									&& entitySearchCriteriaVO.getEntityType()
											.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TPL))

							{

							 	cmEntitySearchDataBean.setRenderCarrierNam(Boolean.TRUE);
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityType(entitySearchCriteriaVO.getEntityType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
												entitySearchCriteriaVO.getEntityID());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO().setCarrierName(entitySearchCriteriaVO.getCarrierName());

							}
							 //CR_ESPRD00486064_UC-PGM-CRM-013_30JUN2010
							 if (entitySearchCriteriaVO.getEntityType() != null
									&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
									&& entitySearchCriteriaVO.getEntityType()
											.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TP))

							{

							 	//cmEntitySearchDataBean.setRenderCarrierNam(Boolean.TRUE);
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityType(entitySearchCriteriaVO.getEntityType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
												entitySearchCriteriaVO.getEntityID());
								//cmEntitySearchDataBean.getEntitySearchCriteriaVO().setCarrierName(entitySearchCriteriaVO.getCarrierName());

							} //EOF CR_ESPRD00486064_UC-PGM-CRM-013_30JUN2010

							 if (entitySearchCriteriaVO.getEntityType() != null
									&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
									&& entitySearchCriteriaVO.getEntityType()
											.equalsIgnoreCase(ContactManagementConstants.DISTRICT_OFFICE_CODE))

							{
								cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityType(entitySearchCriteriaVO.getEntityType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
												entitySearchCriteriaVO.getEntityID());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
											.setOrganisationName(entitySearchCriteriaVO.getOrganisationName());
							}
							 
							// Below if Block is added for the CR : ESPRD00808886
							 
							 if (entitySearchCriteriaVO.getEntityType() != null
										&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
										&& entitySearchCriteriaVO.getEntityType().equals(ContactManagementConstants.ENTITY_TYPE_SC))

								{
								 	cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
									cmEntitySearchDataBean.getEntitySearchCriteriaVO()
											.setEntityType(entitySearchCriteriaVO.getEntityType());
									cmEntitySearchDataBean.getEntitySearchCriteriaVO()
											.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
									cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
													entitySearchCriteriaVO.getEntityID());
									cmEntitySearchDataBean.getEntitySearchCriteriaVO()
												.setOrganisationName(entitySearchCriteriaVO.getOrganisationName());
								}

							 if (entitySearchCriteriaVO.getEntityType() != null
									&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
									&& entitySearchCriteriaVO.getEntityType()
											.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV))

							 {
							 	cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
							 	cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
							 	cmEntitySearchDataBean.getEntitySearchCriteriaVO()
								.setEntityType(entitySearchCriteriaVO.getEntityType());
						        cmEntitySearchDataBean.getEntitySearchCriteriaVO()
								.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
						        cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
										entitySearchCriteriaVO.getEntityID());
						        cmEntitySearchDataBean.getEntitySearchCriteriaVO()
											.setOrganisationName(entitySearchCriteriaVO
															.getOrganisationName());

							 }
							 if (entitySearchCriteriaVO.getEntityType() != null
										&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
										&& entitySearchCriteriaVO.getEntityType()
												.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TD))

								 {
								 	
								 	cmEntitySearchDataBean.setRenderTradingPartner(Boolean.TRUE);
								 	cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityType(entitySearchCriteriaVO.getEntityType());
							        cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
							        cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
											entitySearchCriteriaVO.getEntityID());
							      
								 }
							 cmEntityDataBean.setFlag(true);
							 
							 //req.setAttribute("entityID",entityId);
					         //req.setAttribute("entityType",entType);
					         String entityName = null;
					         // Code Modified for Defect ESPRD00741205 to display correct Name.
					         if (entitySearchCriteriaVO.getEntityType() != null
										&& entitySearchCriteriaVO
												.getEntityType()
												.equalsIgnoreCase(
														ContactManagementConstants.ENTITY_TYPE_UNPROV)
										&& entitySearchResultsVO
												.getOrganisationName() != null) {
									entityName = entitySearchResultsVO
											.getOrganisationName();
								} else if (entitySearchCriteriaVO
										.getEntityType() != null
										&& !entitySearchCriteriaVO
												.getEntityType()
												.equalsIgnoreCase("")
										&& entitySearchCriteriaVO
												.getEntityType()
												.equalsIgnoreCase(
														ContactManagementConstants.ENTITY_TYPE_PROV)) {

									if (entitySearchResultsVO
											.getIndividualGroupCode() != null
											&& entitySearchResultsVO
													.getIndividualGroupCode()
													.equalsIgnoreCase("")
											&& entitySearchResultsVO
													.getIndividualGroupCode()
													.equalsIgnoreCase("I")) {
										if (entitySearchResultsVO
												.getMiddleName() != null) {
											entityName = entitySearchResultsVO
													.getFirstName()
													+ ContactManagementConstants.SPACE_STRING
													+ entitySearchResultsVO
															.getMiddleName()
													+ ContactManagementConstants.SPACE_STRING
													+ entitySearchResultsVO
															.getLastName();
										} else {
											entityName = entitySearchResultsVO
													.getFirstName()
													+ ContactManagementConstants.SPACE_STRING
													+ entitySearchResultsVO
															.getLastName();
										}

									} else {
										entityName = entitySearchResultsVO
												.getOrganisationName();
									}

								} else if (entitySearchResultsVO
										.getEntityName() != null) {
									entityName = cmEntityDOConvertor
											.setSpecificEntName(entitySearchResultsVO);
								}
					         
					         req.setAttribute("entityName",entityName);

							 commonNotesControllerBean.getGlobalNotes();
							 cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
							 cmEntityDataBean.setMainNotesRender(true);

						}else{
							String searchEntyId = entityId;
							showDetailPortlet(searchEntyId, entitySearchResultsVO);
						}
					}
                }


                    if ( reqPath != null && reqPath.equalsIgnoreCase(searchNotes.trim()))

					{
						 FacesContext context = FacesContext.getCurrentInstance();
						 Map map1 = context.getExternalContext().getRequestParameterMap();

						 String entityType = (String) map1.get(ContactManagementConstants.GLOBAL_ENTITY_TYPE);
						 String entID =(String) map1.get(ContactManagementConstants.GLOBAL_ENTITY_NAME);
						 Long  entityID = new Long(entityId.trim());
						 HttpServletRequest req = (HttpServletRequest)context.getExternalContext().getRequest();
						 req.setAttribute("entityType", entitySearchCriteriaVO.getEntityType());
						 req.setAttribute("entityID", entitySearchCriteriaVO.getEntityID());
						 req.setAttribute("entityIDType ", entitySearchCriteriaVO.getEntityIDType());
						 if(logger.isDebugEnabled())
						 {
							 logger.debug("entityType::::::: "+ entitySearchCriteriaVO.getEntityType());
							 logger.debug("entID :::::"+ entitySearchCriteriaVO.getEntityID());
						 }

						  if (entitySearchCriteriaVO.getEntityType() != null
									&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
									&& entitySearchCriteriaVO.getEntityType()
											.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_PROV))

							{

								cmEntitySearchDataBean.setRenderProvider(Boolean.TRUE);
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityType(entitySearchCriteriaVO.getEntityType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
												entitySearchCriteriaVO.getEntityID());
							}

						 if (entitySearchCriteriaVO.getEntityType() != null
								&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
								&& entitySearchCriteriaVO.getEntityType()
										.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM))

						 {

						 	cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
							cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityType(entitySearchCriteriaVO.getEntityType());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
											entitySearchCriteriaVO.getEntityID());

						 }
						 if (entitySearchCriteriaVO.getEntityType() != null
								&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
								&& entitySearchCriteriaVO.getEntityType()
										.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TPL))

						{

						 	cmEntitySearchDataBean.setRenderCarrierNam(Boolean.TRUE);
							cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityType(entitySearchCriteriaVO.getEntityType());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
											entitySearchCriteriaVO.getEntityID());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO().setCarrierName(entitySearchCriteriaVO.getCarrierName());

						}

						 //CR_ESPRD00486064_UC-PGM-CRM-013_30JUN2010
						 if (entitySearchCriteriaVO.getEntityType() != null
								&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
								&& entitySearchCriteriaVO.getEntityType()
										.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TP))

						{

						 	//cmEntitySearchDataBean.setRenderCarrierNam(Boolean.TRUE);
							cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityType(entitySearchCriteriaVO.getEntityType());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
											entitySearchCriteriaVO.getEntityID());
							//cmEntitySearchDataBean.getEntitySearchCriteriaVO().setCarrierName(entitySearchCriteriaVO.getCarrierName());

						} //EOF CR_ESPRD00486064_UC-PGM-CRM-013_30JUN2010
						 if (entitySearchCriteriaVO.getEntityType() != null
								&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
								&& entitySearchCriteriaVO.getEntityType()
										.equalsIgnoreCase(ContactManagementConstants.DISTRICT_OFFICE_CODE))

						{
							cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
							cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityType(entitySearchCriteriaVO.getEntityType());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO()
									.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
											entitySearchCriteriaVO.getEntityID());
							cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setOrganisationName(entitySearchCriteriaVO.getOrganisationName());
						}

						 if (entitySearchCriteriaVO.getEntityType() != null
								&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
								&& entitySearchCriteriaVO.getEntityType()
										.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV))

						 {
						 	cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
						 	cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
						 	cmEntitySearchDataBean.getEntitySearchCriteriaVO()
							.setEntityType(entitySearchCriteriaVO.getEntityType());
					        cmEntitySearchDataBean.getEntitySearchCriteriaVO()
							.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
					        cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
									entitySearchCriteriaVO.getEntityID());
					        cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setOrganisationName(entitySearchCriteriaVO
														.getOrganisationName());

						 }
						 if (entitySearchCriteriaVO.getEntityType() != null
									&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
									&& entitySearchCriteriaVO.getEntityType()
											.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TD))

							{

								cmEntitySearchDataBean.setRenderTradingPartner(Boolean.TRUE);
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityType(entitySearchCriteriaVO.getEntityType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO()
										.setEntityIDType(entitySearchCriteriaVO.getEntityIDType());
								cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
												entitySearchCriteriaVO.getEntityID());
							}

						 cmEntityDataBean.setFlag(true);


					}
                }


                else
                {
                    
                    List resultsList = new ArrayList();

                    resultsList = cmEntityDOConvertor.getEntities(
                            enterpriseSearchResultsVO.getSearchResults(),
                            entitySearchCriteriaVO);
                    
               // Below Code is added for CR ESPRD00902782 to set the Address for member entity.
                  if (entitySearchCriteriaVO.getEntityType() != null
							&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
							&& entitySearchCriteriaVO.getEntityType()
									.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM))
                 {
                	 setMemberAddress(resultsList);
                 }

                    if (resultsList.isEmpty())
                    {
                        setErrorMessage(
                                ContactManagementConstants.ENTITY_ID_TYPE_ENROLLED,
                                new Object[] {},
                                ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                                null);

                    }

                    else
                    {
                        cmEntitySearchDataBean
                                .setSearchResultsList(resultsList);
                        cmEntitySearchDataBean.setSearchResults(resultsList);
                        cmEntitySearchDataBean.setEntityCount(enterpriseSearchResultsVO.getRecordCount());
                        cmEntitySearchDataBean
                                .setRenderSearchResult(Boolean.TRUE);
                        String entType = entitySearchCriteriaVO.getEntityType();
                        if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV, entType))
                        {

                        	 cmEntitySearchDataBean.setRenderLOB(Boolean.TRUE);
                        }
                        if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_CT, entType))
                        {
                        	cmEntitySearchDataBean.setRenderLOB(Boolean.TRUE);
                        	cmEntitySearchDataBean.setCountyName(Boolean.TRUE);

                        }
                    }
                    if ( reqPath != null && reqPath.equalsIgnoreCase(searchNotes.trim()))
					{
                    	cmEntitySearchDataBean.setItemSelectedRowIndex(-1);
					}
                    setRecordRangeForEntity(enterpriseSearchResultsVO,entitySearchCriteriaVO.getStartIndex());

                }

            }
            catch (CMEntityFetchBusinessException e)
            {

                
                e.printStackTrace();
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

            }
        }
        cmEntityDataBean.setEntityTypeForNote(entitySearchCriteriaVO.getEntityType());
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method gets the search resluts on performing search operation for
     * correspondence.
     *
     * @return String
     */
    public String getEntitiesForCrspd()
    {
        

        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();

        boolean flag = validateSearchCriteria(entitySearchCriteriaVO);
       

        cmEntitySearchDataBean
        .getEntitySearchCriteriaVO().setStartIndex(ProgramNumberConstants.INT_ZERO);
		//for fixing defect ESPRD00576206
        String entityIdType=entitySearchCriteriaVO.getEntityIDType();
        cmEntitySearchDataBean.setStartIndexForSrchEntity(0);
        //"FindBugs" issue fixed
        //EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
        EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
        CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
        if (flag)
        {

            try
            {

                enterpriseSearchResultsVO = cmEntityDelegate
                        .getEntities(entitySearchCriteriaVO);

                if (enterpriseSearchResultsVO.getSearchResults().size() == 0)
                {
                    
                    cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
                    setErrorMessage(
                    		EnterpriseMessageConstants.ERR_SW_SEARCH_NO_ENTITY_FOUND,
                            new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
                            null);
                    cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
                    return ContactManagementConstants.RENDER_SUCCESS;

                }else{
                	cmEntitySearchDataBean.setStartIndexForSrchEntity(0);
                	Iterator entities = enterpriseSearchResultsVO.getSearchResults().iterator();
                	Set EntityIDSet = new HashSet();
                	while(entities.hasNext())
                	{
                		EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO)entities.next();
                		if(entitySearchResultsVO.getEntityID() !=null)
                    	{
                		
                		if(EntityIDSet.contains(entitySearchResultsVO.getEntityID().toString()))
                		{
                			entities.remove();
                		
                		}
                	}	
                		EntityIDSet.add(entitySearchResultsVO.getEntityID());
                	}
                	EntityIDSet.clear();
                }

                if (enterpriseSearchResultsVO.getSearchResults().size() == 1)
                {
                    EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO) enterpriseSearchResultsVO
                            .getSearchResults().get(0);

                    String entityType = entitySearchCriteriaVO.getEntityType();

                    String entityId = entitySearchResultsVO.getEntityID();

//                  CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
                    if (StringUtils.equalsIgnoreCase(
                            ContactManagementConstants.ENTITY_TYPE_MEM,
                            entityType)
                            || StringUtils
                                    .equalsIgnoreCase(
                                            ContactManagementConstants.ENTITY_TYPE_PROV,
                                            entityType)  
											|| StringUtils.equalsIgnoreCase(
                                                    ContactManagementConstants.ENTITY_TYPE_TP,
                                                    entityType)
											|| StringUtils.equalsIgnoreCase(
                                                            ContactManagementConstants.ENTITY_TYPE_TD,
                                                            entityType))
                    {//EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010

                        entityId = entitySearchResultsVO.getSystemID()
                                .toString();
                    }else if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV,entityType)
                    		&& (entitySearchResultsVO.getSystemID() != null))
                    {
                    	
                    	entityId = entitySearchResultsVO.getSystemID().toString();
                    	entityType = "UA";
                    }

                    String crspdEntityType = entityId + ":" + entityType;

                    showDetailPortletForCrspd(crspdEntityType);

                    

                }

                else
                {
                    
                    List resultsList = new ArrayList();

                    resultsList = cmEntityDOConvertor.getEntities(
                            enterpriseSearchResultsVO.getSearchResults(),
                            entitySearchCriteriaVO);
                 // Below Code is added for CR ESPRD00902782 to set the Address for member entity.
                    if (entitySearchCriteriaVO.getEntityType() != null
  							&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
  							&& entitySearchCriteriaVO.getEntityType()
  									.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM))
                   {
                  	 setMemberAddress(resultsList);
                   }

                    if (resultsList.isEmpty())
                    {
                        setErrorMessage(
                                ContactManagementConstants.ENTITY_ID_TYPE_ENROLLED,
                                new Object[] {},
                                ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                                null);

                    }

                    else
                    {
                        cmEntitySearchDataBean
                                .setSearchResultsList(resultsList);
                        cmEntitySearchDataBean
                                .setRenderSearchResult(Boolean.TRUE);
                        String entType = entitySearchCriteriaVO.getEntityType();
                        if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV, entType))
                        {

                        	 cmEntitySearchDataBean.setRenderLOB(Boolean.TRUE);
                        }
                        if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_CT, entType))
                        {
                        	cmEntitySearchDataBean.setRenderLOB(Boolean.TRUE);
                        	cmEntitySearchDataBean.setCountyName(Boolean.TRUE);

                        }
                        if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TD, entType))
                        {

                        	cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
                        }
                    }

                    setRecordRange(enterpriseSearchResultsVO);

                }

            }
            catch (CMEntityFetchBusinessException e)
            {

                if(logger.isErrorEnabled())
                {
                	logger.error(e.getMessage(), e);
                }
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

            }
        }
        //for fixing defect ESPRD00576206
        entitySearchCriteriaVO.setEntityIDType(entityIdType);
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method gets the search resluts on performing search operation for
     * Log Case.
     *
     * @return String
     */
    public String getEntitiesForCase()
    {
		HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderResponse = null;
		EnterpriseUserProfile eup = getUserData(renderRequest, renderResponse);
		// Begin - Modified the code for the HeapDump Fix
		if(cmEntitySearchDataBean.getSearchResultRowIndex() != -1){//		UC-PGM-CRM-072_ESPRD00425145_24FEB2010
			cmEntitySearchDataBean.setSearchResultRowIndex(0);

		}		//EOF UC-PGM-CRM-072_ESPRD00425145_24FEB2010
		// End - Modified the code for the HeapDump Fix
		CaseDelegate caseDelegate = new CaseDelegate();
		boolean caseFilterStatus=false;
		String userId=getUserID();
		
		try{
			caseFilterStatus = caseDelegate.caseFilterStatus(userId);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (eup != null && eup.getCaseFilter() == null
				&& isNullOrEmptyField(eup.getCaseFilter())) {
			ContactManagementHelper
					.setErrorMessage(MaintainContactManagementUIConstants.NO_AUTHORITY_ADD_CASE);
		} /*else if(!caseFilterStatus){
			ContactManagementHelper
			.setErrorMessage(MaintainContactManagementUIConstants.NO_AUTHORITY_ADD_CASE);
		}*/else {
			

			EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
					.getEntitySearchCriteriaVO();

			boolean flag = validateSearchCriteria(entitySearchCriteriaVO);
			

			entitySearchCriteriaVO
					.setStartIndex(ProgramNumberConstants.INT_ZERO);
			/*entitySearchCriteriaVO
					.setRowsPerPage(ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);*///commented for UC-PGM-CRM-072_ESPRD00425145_24FEB2010
			//"FindBugs" issue fixed
			//EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
			EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
			CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
			//for ESPRD00865766
			if(cmEntitySearchDataBean.getSearchResultsList()!=null){
				cmEntitySearchDataBean.getSearchResultsList().clear();
				cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
			}
			if (flag) {

				try {

					enterpriseSearchResultsVO = cmEntityDelegate
							.getEntities(entitySearchCriteriaVO);

					if (enterpriseSearchResultsVO.getSearchResults().size() == 0) {
						
						if(cmEntitySearchDataBean.getSearchResultsList()!=null){//UC-PGM-CRM-072_ESPRD00425145_24FEB2010
							cmEntitySearchDataBean.getSearchResultsList().clear();
							cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
						}//EOF UC-PGM-CRM-072_ESPRD00425145_24FEB2010
						setErrorMessage(
						      EnterpriseMessageConstants.ERR_SW_SEARCH_NO_ENTITY_FOUND,
								new Object[] {},
								MessageUtil.ENTMESSAGES_BUNDLE, null);


					}else{
	                	cmEntitySearchDataBean.setStartIndexForSrchEntity(0);
	                	Iterator entities = enterpriseSearchResultsVO.getSearchResults().iterator();
	                	Set EntityIDSet = new HashSet();
	                	while(entities.hasNext())
	                	{
	                		EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO)entities.next();
	                		if(entitySearchResultsVO.getEntityID() !=null)
	                    	{
	                		
	                		if(EntityIDSet.contains(entitySearchResultsVO.getEntityID().toString()))
	                		{
	                			entities.remove();
	                		
	                		}
	                	}	
	                		EntityIDSet.add(entitySearchResultsVO.getEntityID());
	                	}
	                	EntityIDSet.clear();
	                }

					if (enterpriseSearchResultsVO.getSearchResults()
							.size() == 1) {
						EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO) enterpriseSearchResultsVO
								.getSearchResults().get(0);

						String entityType = entitySearchCriteriaVO
								.getEntityType();

						String entityId = entitySearchResultsVO.getEntityID();

						if (StringUtils.equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_MEM,
								entityType)
								|| StringUtils
										.equalsIgnoreCase(
												ContactManagementConstants.ENTITY_TYPE_PROV,
												entityType)
												|| StringUtils.equalsIgnoreCase(
						                                 ContactManagementConstants.ENTITY_TYPE_TP,
														 entityType)) {

							entityId = entitySearchResultsVO.getSystemID()
									.toString();
						}
						
						// Added for the defect ESPRD00432774
						String menuCode = getMenuCode();
					
						String caseEntityType = entityId + ":" + entityType + ":" + menuCode;
						// Ends
				        //ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
				        String commonEntitySK = null;
				        if(entitySearchResultsVO.getCommonEntitySK()!=null){
				        	commonEntitySK = entitySearchResultsVO.getCommonEntitySK().toString();
				        	caseEntityType = caseEntityType + ":" +commonEntitySK;
				        }
				       
				        //EOf ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
						showDetailPortletForCase(caseEntityType);
						//Modified for ESPRD00865766
						/**Commented below code as for single search record should not display in the search
						 * Results as per UI standard. Only for multiple records should display the Searched Result
						 * */
						/*List resultsList = new ArrayList();

						resultsList = cmEntityDOConvertor.getEntities(
								enterpriseSearchResultsVO.getSearchResults(),
								entitySearchCriteriaVO);
						cmEntitySearchDataBean
						.setSearchResultsList(resultsList);
						cmEntitySearchDataBean.setRenderSearchResult(Boolean.TRUE);*/
					}
					//for ESPRD00865766
					else {
						
						List resultsList;// = new ArrayList();

						resultsList = cmEntityDOConvertor.getEntities(
								enterpriseSearchResultsVO.getSearchResults(),
								entitySearchCriteriaVO);
						if (entitySearchCriteriaVO.getEntityType().equals("TP")){
							cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
						}
						// Below Code is added for CR ESPRD00902782 to set the Address for memebr entity.
		                  if (entitySearchCriteriaVO.getEntityType() != null
									&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
									&& entitySearchCriteriaVO.getEntityType()
											.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM))
		                 {
		                	 setMemberAddress(resultsList);
		                 }
						
						if (resultsList.isEmpty()) {

							if(cmEntitySearchDataBean.getSearchResultsList()!=null){ //UC-PGM-CRM-072_ESPRD00425145_24FEB2010
								cmEntitySearchDataBean.getSearchResultsList().clear();
								cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
							}	//EOF UC-PGM-CRM-072_ESPRD00425145_24FEB2010
							//commented for defect ESPRD00513837 starts
							/*setErrorMessage(
									ContactManagementConstants.ENTITY_ID_TYPE_ENROLLED,
									new Object[] {},
									ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
									null);*/
							//defect ESPRD00513837 ends
						}

						else {
							cmEntitySearchDataBean
									.setSearchResultsList(resultsList);
							cmEntitySearchDataBean
									.setRenderSearchResult(Boolean.TRUE);
						}
						//setRecordRange(enterpriseSearchResultsVO); commented for UC-PGM-CRM-072_ESPRD00425145_24FEB2010
					}
				} catch (CMEntityFetchBusinessException e) {
					e.printStackTrace();
					if(logger.isErrorEnabled())
					{
						logger.error(e.getMessage(), e);
					}
					setErrorMessage(
					      EnterpriseMessageConstants.ERR_SW_SEARCH_NO_ENTITY_FOUND,
							new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
							null);


				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return ContactManagementConstants.RENDER_SUCCESS;
	}

    /**
	 * This method gets the search resluts on performing search operation for
	 * inquiry about entity search.
	 *
	 * @return String
	 */
    public String getInquriyAboutEntitiesForCrspd()
    {
        

        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();

        boolean flag = validateSearchCriteria(entitySearchCriteriaVO);
       
        if(!flag)
        {
        	// Added for defect - ESPRD00920227
            cmEntitySearchDataBean.setRenderErrorMsg(true); 
        }else
        {
        	// Added for defect - ESPRD00920227
            cmEntitySearchDataBean.setRenderErrorMsg(false); 
        }

        entitySearchCriteriaVO.setStartIndex(ProgramNumberConstants.INT_ZERO);
       /* entitySearchCriteriaVO
                .setRowsPerPage(ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);*/
        //"FindBugs" issue fixed
        //EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
        EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
        CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();

        if (flag)
        {

            try
            {

                enterpriseSearchResultsVO = cmEntityDelegate
                        .getEntities(entitySearchCriteriaVO);

                if (enterpriseSearchResultsVO.getSearchResults().size() == 0)
                {
                	// Added for defect - ESPRD00920227
                    cmEntitySearchDataBean.setRenderErrorMsg(true); 
                    
                    setErrorMessage(
                            EnterpriseMessageConstants.ERR_SW_SEARCH_NO_ENTITY_FOUND,
                            new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
                            null);
                    cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
                    return ContactManagementConstants.RENDER_SUCCESS;

                }else{
                	Iterator entities = enterpriseSearchResultsVO.getSearchResults().iterator();
                	Set EntityIDSet = new HashSet();
                	while(entities.hasNext())
                	{
                		EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO)entities.next();
                		if(entitySearchResultsVO.getEntityID() !=null)
                    	{
                		
                		if(EntityIDSet.contains(entitySearchResultsVO.getEntityID().toString()))
                		{
                			entities.remove();
                		
                		}
                	}	
                		EntityIDSet.add(entitySearchResultsVO.getEntityID());
                	}
                	EntityIDSet.clear();
                	
                	// Added for defect - ESPRD00920227
                    cmEntitySearchDataBean.setRenderErrorMsg(false); 
                }

               if (enterpriseSearchResultsVO.getSearchResults().size() == 1)
                {
                    EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO) enterpriseSearchResultsVO
                            .getSearchResults().get(0);

                    String entityType = entitySearchCriteriaVO.getEntityType();

                    String entityId = entitySearchResultsVO.getEntityID();
//CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
                    if (StringUtils.equalsIgnoreCase(
                            ContactManagementConstants.ENTITY_TYPE_MEM,
                            entityType)
                            || StringUtils
                                    .equalsIgnoreCase(
                                            ContactManagementConstants.ENTITY_TYPE_PROV,
                                            entityType)
											|| StringUtils.equalsIgnoreCase(
		                                            ContactManagementConstants.ENTITY_TYPE_TP,
		                                            entityType))
                    {//EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010

                        entityId = entitySearchResultsVO.getSystemID()
                                .toString();
                    }else if (StringUtils.equalsIgnoreCase(
                            ContactManagementConstants.ENTITY_TYPE_TD, entityType))
                    {
                        entityId = entitySearchResultsVO.getCommonEntitySK().toString();
                    }

                    String inquiryAboutEntityType = entityId + ":" + entityType;

                    showInquiryAbtCrspdPortlet(inquiryAboutEntityType);

                  

                }

                else
                {
                    
                    List resultsList = new ArrayList();

                    resultsList = cmEntityDOConvertor.getEntities(
                            enterpriseSearchResultsVO.getSearchResults(),
                            entitySearchCriteriaVO);
                 // Below Code is added for CR ESPRD00902782 to set the Address for member entity.
                    if (entitySearchCriteriaVO.getEntityType() != null
  							&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
  							&& entitySearchCriteriaVO.getEntityType()
  									.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM))
                   {
                  	 setMemberAddress(resultsList);
                   }

                    if (resultsList.isEmpty())
                    {
                        setErrorMessage(
                                ContactManagementConstants.ENTITY_ID_TYPE_ENROLLED,
                                new Object[] {},
                                ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                                null);

                     // Added for defect - ESPRD00920227
                        cmEntitySearchDataBean.setRenderErrorMsg(true); 
                    }

                    else
                    {
                        cmEntitySearchDataBean
                                .setSearchResultsList(resultsList);
                        cmEntitySearchDataBean
                                .setRenderSearchResult(Boolean.TRUE);
                        
                     // Added for defect - ESPRD00920227
                        cmEntitySearchDataBean.setRenderErrorMsg(false); 
                    }
                    setRecordRange(enterpriseSearchResultsVO);

                }

            }
            catch (CMEntityFetchBusinessException e)
            {
               if(logger.isErrorEnabled())
               {
            	   logger.error(e.getMessage(), e);
               }
               /* setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);*/
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_SEVERE_FAILURE,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
                
             // Added for defect - ESPRD00920227
                cmEntitySearchDataBean.setRenderErrorMsg(true); 
            }
        }

        
        
        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method gets the search resluts on performing search operation for
     * inquiry about entity search for LogCase.
     *
     * @return String
     */
    public String getInquriyAboutEntitiesForCase()
    {
       
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();

        boolean flag = validateSearchCriteria(entitySearchCriteriaVO);
        
        if(!flag)
        {
        	// Added for defect - ESPRD00920239
            cmEntitySearchDataBean.setRenderErrorMsg(true); 
        }else
        {
        	// Added for defect - ESPRD00920239
            cmEntitySearchDataBean.setRenderErrorMsg(false); 
        }
        
        entitySearchCriteriaVO.setStartIndex(ProgramNumberConstants.INT_ZERO);
        //for ESPRD00736954entitySearchCriteriaVO
         //       .setRowsPerPage(ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);
        //ESPRD00542804_Fix_04Jan11
        Map appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
		appMap.put("inqEntitySearch","inqEntitySearch");
		//ESPRD00542804_Fix_04Jan11
        //"FindBugs" issue fixed
		//EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
		EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
        CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
        //Modified for ESPRD00865766
        if(cmEntitySearchDataBean.getSearchResultsList()!=null){
                	cmEntitySearchDataBean.getSearchResultsList().clear();
                    cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
                }
        if (flag)
        {
            try
            {
                enterpriseSearchResultsVO = cmEntityDelegate
                        .getEntities(entitySearchCriteriaVO);
                if(enterpriseSearchResultsVO!=null){
                if (enterpriseSearchResultsVO.getSearchResults().size() == 0)
                {
                    
//				Modified by infinite for defect ESPRD00242925
                    setErrorMessage(
                            EnterpriseMessageConstants.ERR_SW_SEARCH_NO_ENTITY_FOUND,
                            new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE,
                            null);
                    
                    // Added for defect - ESPRD00920239
                    cmEntitySearchDataBean.setRenderErrorMsg(true); 
                    
                    return ContactManagementConstants.RENDER_SUCCESS;
                }else{//for ESPRD00736954
                	Iterator entities = enterpriseSearchResultsVO.getSearchResults().iterator();
                	Set EntityIDSet = new HashSet();
                	while(entities.hasNext())
                	{
                		EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO)entities.next();
                		if(entitySearchResultsVO.getEntityID() !=null)
                    	{
                		
                		if(EntityIDSet.contains(entitySearchResultsVO.getEntityID().toString()))
                		{
                			entities.remove();
                			
                		}
                	}	
                		EntityIDSet.add(entitySearchResultsVO.getEntityID());
                	}
                	EntityIDSet.clear();
                	
                	 // Added for defect - ESPRD00920239
                    cmEntitySearchDataBean.setRenderErrorMsg(false);
                }//for ESPRD00736954
                 if (enterpriseSearchResultsVO.getSearchResults().size() == 1)
                    {
                        EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO) enterpriseSearchResultsVO
                                .getSearchResults().get(0);
                        String entityType = entitySearchCriteriaVO.getEntityType();
                        String entityId = entitySearchResultsVO.getEntityID();
                        if (StringUtils.equalsIgnoreCase(
                                ContactManagementConstants.ENTITY_TYPE_MEM,
                                entityType)
                                || StringUtils
                                        .equalsIgnoreCase(
                                                ContactManagementConstants.ENTITY_TYPE_PROV,
                                                entityType)
                                                || StringUtils
                                        .equalsIgnoreCase(
                                                ContactManagementConstants.ENTITY_TYPE_TP,
                                                entityType))
                        {
                            entityId = entitySearchResultsVO.getSystemID()
                                    .toString();
                        }
                        String inquiryAboutEntityType ="";
                        if(entitySearchResultsVO.getCommonEntitySK()!=null){
                        	
                        	String cmentitysk = entitySearchResultsVO.getCommonEntitySK().toString();
                        	 inquiryAboutEntityType = entityId + ":" + entityType+":"+cmentitysk;
                        }else{
                        	 inquiryAboutEntityType = entityId + ":" + entityType;
                        }
                        
                        
                       
                        showInquiryAbtCasePortlet(inquiryAboutEntityType);
                        
                    }
                 //for ESPRD00865766
                 else{
                   
                   
                    List resultsList = new ArrayList();
                    resultsList = cmEntityDOConvertor.getEntities(
                            enterpriseSearchResultsVO.getSearchResults(),
                            entitySearchCriteriaVO);
                 // Below Code is added for CR ESPRD00902782 to set the Address for member entity.
                    if (entitySearchCriteriaVO.getEntityType() != null
  							&& !entitySearchCriteriaVO.getEntityType().equalsIgnoreCase("")
  							&& entitySearchCriteriaVO.getEntityType()
  									.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM))
                   {
                  	 setMemberAddress(resultsList);
                   }
                    if (resultsList.isEmpty())
                    {
                        setErrorMessage(
                                ContactManagementConstants.ENTITY_ID_TYPE_ENROLLED,
                                new Object[] {},
                                ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                                null);
                        
                        // Added for defect - ESPRD00920239
                        cmEntitySearchDataBean.setRenderErrorMsg(true);
                    }
                    else
                    {
                        cmEntitySearchDataBean
                                .setSearchResultsList(resultsList);
                        cmEntitySearchDataBean
                                .setRenderSearchResult(Boolean.TRUE);
                        
                        // Added for defect - ESPRD00920239
                        cmEntitySearchDataBean.setRenderErrorMsg(false);
                    }
                    setRecordRange(enterpriseSearchResultsVO);
                    }
                }
            }
            catch (CMEntityFetchBusinessException e)
            {
                if(logger.isErrorEnabled())
                {
                	logger.error(e.getMessage(), e);
                }
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_SEARCH_NO_ENTITY_FOUND,
                        new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);
                
                // Added for defect - ESPRD00920239
                cmEntitySearchDataBean.setRenderErrorMsg(true);
            }
        }
        
       

        return ContactManagementConstants.RENDER_SUCCESS;
    }

    /**
     * This method will validate the web address.
     *
     * @param expression
     *            Web Address.
     * @return boolean : true if the expression matches the pattern
     *         http://ww.wss.com/ format
     */
    public static boolean validateAlphaNumeric(String expression)
    {

        Pattern p = Pattern.compile(ProgramConstants.ALPHANUMERIC_PATTERN_NAME);
        Matcher m = p.matcher(expression);

        return m.matches();
    }

    
    /**
     * This method will validate the web address.
     *
     * @param expression
     *            Web Address.
     * @return boolean : true if the expression matches the pattern
     *         http://ww.wss.com/ format
     */
    public static boolean validateMiddlename(String expression)
    {

        Pattern p = Pattern.compile(ProgramConstants.ALPHA_PATTERN_MIDDLE_NAME);
        Matcher m = p.matcher(expression);

        return m.matches();
    }
    /**
     * This method Validates Name .
     *
     * @param entitySearchCriteriaVO
     *            Takes entitySearchCriteriaVO as param.
     * @param flag
     *            Takes flag as param.
     * @return boolean value
     */
    private boolean validateEntityName(
            EntitySearchCriteriaVO entitySearchCriteriaVO, boolean flag)
    {
       
        boolean flagInd = flag;

        String firstName = entitySearchCriteriaVO.getFirstName();
        String lastName = entitySearchCriteriaVO.getLastName();
        String middleName = entitySearchCriteriaVO.getMiddleInitial();
        String sortName = entitySearchCriteriaVO.getProviderSortName();
        String Name=entitySearchCriteriaVO.getTradingPartnerName();
        String orgName = entitySearchCriteriaVO.getOrganisationName();
        String carrierName = entitySearchCriteriaVO.getCarrierName();
        String policyHolderFirstName = entitySearchCriteriaVO.getPolicyHolderFirstName();
        String policyHolderLastName = entitySearchCriteriaVO.getPolicyHolderLastName();

        /** Validate First Name */
        if (!isNullOrEmptyField(firstName) && !validateAlphaNumeric(firstName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.FIRST_NAME},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    ContactManagementConstants.ENT_SRCH_F_NAME);
            flagInd = false;
        }
      //ADDED FOR THE CR ESPRD00436016
        /** Validate  Name */
        if (!isNullOrEmptyField(Name)
                && !validateAlphaNumeric(Name))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    "PNameSearch");
            flagInd = false;
        }
        /** Validate Last Name */
        if (!isNullOrEmptyField(lastName) && !validateAlphaNumeric(lastName))
        {

           /* setErrorMessage(EnterpriseMessageConstants.ERR_SW_INVALID,
                    new Object[] {ContactManagementConstants.LAST_NAME},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    ContactManagementConstants.LAST_NAME_SEARCH);*/

        	setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    ContactManagementConstants.LAST_NAME_SEARCH);
            flagInd = false;
        }

        /** Validate Middle Name */
        if (!isNullOrEmptyField(middleName)
                && !validateMiddlename(middleName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.MIDDLE_INITIAL},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    ContactManagementConstants.ENT_SRCH_M_NAME);
            flagInd = false;
        }

        /** Validate Sort Name */
        if (!isNullOrEmptyField(sortName) && !validateAlphaNumeric(sortName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.MIDDLE_INITIAL},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    ContactManagementConstants.ENT_SRCH_PROV_SRT_NAME);
            flagInd = false;
        }
        
        /** Validate Organization Name */
        if (!isNullOrEmptyField(orgName) && !validateAlphaNumeric(orgName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.ORG_NAME_COMPID},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    ContactManagementConstants.ORG_NAME_COMPID);
            flagInd = false;
        }
        
        /** Validate Carrier Name */
        if (!isNullOrEmptyField(carrierName) && !validateAlphaNumeric(carrierName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.CARRIER_NAME_SEARCH},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    ContactManagementConstants.CARRIER_NAME_SEARCH);
            flagInd = false;
        }

        /** Validate PolicyHolderFirst Name */
        if (!isNullOrEmptyField(policyHolderFirstName) && !validateAlphaNumeric(policyHolderFirstName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.POLICY_HOLDER_FIRST_NAME_ID},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    ContactManagementConstants.POLICY_HOLDER_FIRST_NAME_ID);
            flagInd = false;
        }
        
        /** Validate PolicyHolderLastName Name */
        if (!isNullOrEmptyField(policyHolderLastName) && !validateAlphaNumeric(policyHolderLastName))
        {

            setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
                    new Object[] {ContactManagementConstants.POLICY_HOLDER_LAST_NAME_ID},
                    MessageUtil.ENTMESSAGES_BUNDLE,
                    ContactManagementConstants.POLICY_HOLDER_LAST_NAME_ID);
            flagInd = false;
        }
        
        

        return flagInd;
    }

    /**
     * This method Validates the Search Criteria .
     *
     * @param entitySearchCriteriaVO
     *            Takes entitySearchCriteriaVO as param
     * @return boolean
     */
    private boolean validateSearchCriteria(
            EntitySearchCriteriaVO entitySearchCriteriaVO)
    {
    	

        boolean dataFlag = true;
        /** Check for valid Entity Name */

        /** Check If Entity Type is selected */

        /** Checks if Entity type drop Down is empty */
         boolean emptySearchFlag = true;
         if (isNullOrEmptyField(entitySearchCriteriaVO.getEntityType()) && isNullOrEmptyField(entitySearchCriteriaVO.getEntityIDType()))
         {
         	emptySearchFlag = false;

         
//        	Modified by infinite for defect ESPRD00242925
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.ENTITY_TYPE},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
                    ContactManagementConstants.ENTITY_TYPE_SEARCH);

            dataFlag = false;

        
             }


        if (emptySearchFlag && isNullOrEmptyField(entitySearchCriteriaVO.getEntityType()))
        {

        
//        	Modified by infinite for defect ESPRD00242925
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.ENTITY_TYPE},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
                    ContactManagementConstants.ENTITY_TYPE_SEARCH);

            dataFlag = false;

        }

        /** Checks if Entity ID type drop Down is empty */
        if (emptySearchFlag && isNullOrEmptyField(entitySearchCriteriaVO.getEntityIDType()))
        {
        	

            /**
             * Checks the Condition -- IF EntityID Drop Down is not selected
             * Last Name is Required
             */
            	dataFlag = checkNameNotNull(entitySearchCriteriaVO);
        }

        /**
         * IF Entity ID Type Drop Down is not Empty --- Entity ID text becomes
         * mandatory
         */
        else if(emptySearchFlag)
        {
        	
            dataFlag = checkEntityIDNotNUll(entitySearchCriteriaVO
                    .getEntityID());
        }
          if(emptySearchFlag)
          {
        dataFlag = validateEntityName(entitySearchCriteriaVO, dataFlag);
          }

        return dataFlag;
    }

    /**
     * This operation is used to get Contact Management Entity Record details
     * based on the primary key value 'CMEntityID'
     */
    public void getCMEntityDetails()
    {
    	
        String typeOfEntity = null;
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map map = fc.getExternalContext().getRequestParameterMap();
        String indexCode = "0";
        int x = 0;
        if (map != null && map.size() > x)
        {
            indexCode = (String) map.get("indexCode");
            if(logger.isInfoEnabled())
            {
            	logger.info("indexCode--------"+indexCode);  
            }
            if(indexCode != null && StringUtils.isNotEmpty(indexCode))
            {
            	getCmEntitySearchDataBean().setItemSelectedRowIndex(Integer.parseInt(indexCode));
            }
        }
        
        String entityId = ContactManagementConstants.EMPTY_STRING;
        FacesContext context = FacesContext.getCurrentInstance();
        Map map1 = context.getExternalContext().getRequestParameterMap();
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();
        int row=Integer.parseInt(indexCode);
		cmEntitySearchDataBean.setStartIndexForSrchEntity((row/10)*10);
		if(logger.isInfoEnabled())
		{
			logger.info("CMEntitySearchControllerBean :: getCMEntityDetails row value " + row);
		}
		EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO) cmEntitySearchDataBean
														.getSearchResultsList().get(row);
		


        PortletSession portletSession = (PortletSession) FacesContext
		.getCurrentInstance().getExternalContext().getSession(false);
        if(portletSession.getAttribute("typeOfEntity") != null)
        {
        	typeOfEntity = portletSession.getAttribute("typeOfEntity").toString();
        }

        
        if (cmEntitySearchDataBean.isCallFromLogCase()) {
			cmEntitySearchDataBean.setCallFromLogCase(false);

			if (entitySearchCriteriaVO != null && entitySearchResultsVO != null) {
				Map dataMap = new HashMap();

				dataMap.put("EntityType", entitySearchCriteriaVO
						.getEntityType());
				//Changed for the Defect ESPRD00359585
				if(logger.isInfoEnabled())
				{
					logger.info("entitySearchCriteriaVO.getEntityIDType()======"+entitySearchCriteriaVO.getEntityIDType());
				}
				dataMap.put("EntityIDType", entitySearchCriteriaVO.getEntityIDType());
				/*dataMap.put("EntityIDType", entitySearchResultsVO
						.getEntityIDTyp());*/
				dataMap.put("EntityID", entitySearchResultsVO.getEntityID());
//				modified for defect ESPRD00584046 16march2011
				if(logger.isInfoEnabled())
				{
				logger.info("CurrentAltIDTypeCode======"+entitySearchResultsVO.getCurrentAltIDTypeCode());
				}
				if(entitySearchResultsVO.getCurrentAltIDTypeCode() != null)
				{
					
					dataMap.put("EntityIDType",entitySearchResultsVO.getCurrentAltIDTypeCode());
					dataMap.put("EntityID",entitySearchResultsVO.getCurrAltID());
				}
				//defect  ends ESPRD00584046 16march2011

				dataMap.put("CommonEntitySK", entitySearchResultsVO
						.getCommonEntitySK());
				dataMap.put("Identification", cmEntitySearchDataBean
						.getDataMapIden());
				dataMap.put("search", typeOfEntity);

				//ESPRD0033432_20112009
				dataMap.put("target",getCmEntitySearchDataBean().getTarget());

				ActionRequest actionRequest = (ActionRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();



				String fromWhichPortlet = portletSession
						.getAttribute("portletName").toString();

				

				if(fromWhichPortlet.equals("Search Case"))
				{
					entitySearchCriteriaVO.setMap(dataMap);
					actionRequest.setAttribute("EntitySearchMap2", entitySearchCriteriaVO);
				}
				else if(fromWhichPortlet.equals("Correspondence"))
				{
					actionRequest.setAttribute("srcEntityMap", dataMap);

				}
				//Added for the Defect ESPRD00802462
				else if(fromWhichPortlet.equals("LogCaseAssociatedCR")){

					
					entitySearchCriteriaVO.setMap(dataMap);
					actionRequest.setAttribute("EntSrchLogCaseCRMap", dataMap);
				}
				else if (fromWhichPortlet.equals("Log Case")) {

					entitySearchCriteriaVO.setMap(dataMap);
					actionRequest.setAttribute("EntSrchLogCaseMap",
							entitySearchCriteriaVO);
				}
				if(logger.isInfoEnabled())
				{
					logger.info("entitySearchCriteriaVO.getEntityIDType()"+entitySearchCriteriaVO.getEntityIDType());
				}
				//Below code commented for fixing the Defect - ESPRD00778018
				
				if (!(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
	                    ContactManagementConstants.ENTITY_TYPE_MEM))
						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_PROV))
						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_TPL))
						/*&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_DO))
						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
	    	                    ContactManagementConstants.ENTITY_TYPE_CT))*/)
	            {
					
					dataMap.put("EntityIDType", "CM");
	            }

				portletSession.removeAttribute("portletName");
			}
        }else if (cmEntitySearchDataBean.isCallFromSrchCorr()||cmEntitySearchDataBean.isCallFromSrchEDMS()) {
			cmEntitySearchDataBean.setCallFromSrchCorr(false);
			if(logger.isInfoEnabled())
			{
				logger.info("CMEntitySearchControllerBean :: getCMEntityDetails EntityType "
					+ entitySearchCriteriaVO.getEntityType());
				logger.info("CMEntitySearchControllerBean :: getCMEntityDetails EntityIDType "
					+ entitySearchCriteriaVO.getEntityIDType());
			
				logger.info("CMEntitySearchControllerBean :: getCMEntityDetails EntityIDTyp "
					+ entitySearchResultsVO.getEntityIDTyp());
			
			
				logger.info("CMEntitySearchControllerBean :: getCMEntityDetails EntityID "
					+ entitySearchResultsVO.getEntityID());
				logger.info("CMEntitySearchControllerBean :: getCMEntityDetails CommonEntitySK "
					+ entitySearchResultsVO.getCommonEntitySK());
			}

			if (entitySearchCriteriaVO != null && entitySearchResultsVO != null) {
				Map dataMap = new HashMap();

				dataMap.put("EntityType", entitySearchCriteriaVO.getEntityType());
				dataMap.put("EntityIDType", entitySearchCriteriaVO.getEntityIDType());
				dataMap.put("EntityID", entitySearchResultsVO.getEntityID());
				if(logger.isInfoEnabled())
				{
					logger.info("CurrentAltIDTypeCode======"+entitySearchResultsVO.getCurrentAltIDTypeCode());
				}
				if(entitySearchResultsVO.getCurrentAltIDTypeCode() != null)
				{
					dataMap.put("EntityIDType",entitySearchResultsVO.getCurrentAltIDTypeCode());
					dataMap.put("EntityID",entitySearchResultsVO.getCurrAltID());
				}
				
				dataMap.put("CommonEntitySK", entitySearchResultsVO
						.getCommonEntitySK());
				dataMap.put("Identification", cmEntitySearchDataBean
						.getDataMapIden());

				ActionRequest actionRequest = (ActionRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				
				String fromWhichPortlet = portletSession
						.getAttribute("portletName").toString();
				

				if(fromWhichPortlet.equals("Search Correspondence"))
				{
					actionRequest.setAttribute("EntitySearchMap", dataMap);
				}else if(fromWhichPortlet.equals("reassignCorrespondence"))
				{
					actionRequest.setAttribute("EntitySearchMap1", dataMap);
				}else if(fromWhichPortlet.equals("EntitySearchFromEDMS")){
					
					
					actionRequest.setAttribute("EntitySearchMap3", dataMap);
					
				}
				else if(fromWhichPortlet.equals("Correspondence"))
				{
					actionRequest.setAttribute("srcEntityMap", dataMap);
				}
				//Below code commented for fixing the Defect - ESPRD00778018
				if (!(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
	                    ContactManagementConstants.ENTITY_TYPE_MEM))
						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_PROV))
						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_TPL))
						/*&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_DO))
						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
	    	                    ContactManagementConstants.ENTITY_TYPE_CT))*/)
	            {
					
					dataMap.put("EntityIDType", "CM");
	            }
				portletSession.removeAttribute("portletName");
			}
		}//UC-PGM-CRM-033_ESPRD00624909_09jun2011
        else if(cmEntitySearchDataBean.isCallFromSearchCase()){
        	cmEntitySearchDataBean.setCallFromSearchCase(false);
			if (entitySearchCriteriaVO != null && entitySearchResultsVO != null) {
				Map dataMap = new HashMap();
				
				dataMap.put("EntityType", entitySearchCriteriaVO
						.getEntityType());
				
				dataMap.put("EntityIDType", entitySearchCriteriaVO.getEntityIDType());
				
				dataMap.put("EntityID", entitySearchResultsVO.getEntityID());
				
				
				if(entitySearchResultsVO.getCurrentAltIDTypeCode() != null)
				{
					dataMap.put("EntityIDType",entitySearchResultsVO.getCurrentAltIDTypeCode());
					dataMap.put("EntityID",entitySearchResultsVO.getCurrAltID());
						
				}

				dataMap.put("CommonEntitySK", entitySearchResultsVO
						.getCommonEntitySK());
				dataMap.put("Identification", cmEntitySearchDataBean
						.getDataMapIden());
				dataMap.put("search", typeOfEntity);

				dataMap.put("target",getCmEntitySearchDataBean().getTarget());

				PortletRequest pRequest = (PortletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();

				String fromWhichPortlet = portletSession
						.getAttribute("portletName").toString();

				

				if(fromWhichPortlet.equals("Search Case"))
				{
					entitySearchCriteriaVO.setMap(dataMap);
					pRequest.getPortletSession().setAttribute("EntitySearchMap2", entitySearchCriteriaVO);

					pRequest.setAttribute("EntitySearchMap2", entitySearchCriteriaVO);
					
				}
				//Below code commented for fixing the Defect - ESPRD00778018
				if (!(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
	                    ContactManagementConstants.ENTITY_TYPE_MEM))
						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_PROV))
						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_TPL))
						/*&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
								ContactManagementConstants.ENTITY_TYPE_DO))
						&& !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
	    	                    ContactManagementConstants.ENTITY_TYPE_CT))*/
	    	            && !(entitySearchCriteriaVO.getEntityType().equalsIgnoreCase(
	    	    	                    ContactManagementConstants.ENTITY_TYPE_TD))) // Trading Partner
	            {
					
					dataMap.put("EntityIDType", "CM");
	            }

				portletSession.removeAttribute("portletName");
			}
        
        }//EOf UC-PGM-CRM-033_ESPRD00624909_09jun2011

        else
        {
	        String entType = entitySearchCriteriaVO.getEntityType();
	        if (StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_MEM, entType)
	                || StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_PROV, entType))
	        {
	            entityId = (String) map.get(ContactManagementConstants.IPC_SYSTEMID);
	        }
	        else if(StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV, entType)
					&& entitySearchResultsVO.getSystemID() != null)
	        {
	            
	        	entityId = (String) map.get(ContactManagementConstants.IPC_SYSTEMID);
	        	//entitySearchCriteriaVO.setEntityType(ContactManagementConstants.ENTITY_TYPE_PROV);
	        }else if(StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TP, entType))
	        {
	            
	        	entityId = (String) map.get(ContactManagementConstants.GLOBALCOMMON_NOTES_COMMONENTSK);
	        }else if(StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TD, entType)){
	        	
	        	entityId = (String) map.get(ContactManagementConstants.IPC_ENTITYID);
	        	
	        }
	        else
	        {
	            entityId = (String) map.get(ContactManagementConstants.IPC_ENTITYID);
	        }
	        
	        showDetailPortlet(entityId,entitySearchResultsVO);
        }
    }

    /**
     * This operation is used to get Contact Management Entity Record details
     * based on the primary key value 'CMEntityID' for Correspondence.
     */
    public void getCMEntityDetailsForCrspd()
    {
       
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String entityId = ContactManagementConstants.EMPTY_STRING;
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();

        String entType = entitySearchCriteriaVO.getEntityType();
        String sysID = (String) map.get(ContactManagementConstants.IPC_SYSTEMID);
        
        //CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
        if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_MEM, entType)
                || StringUtils.equalsIgnoreCase(
                        ContactManagementConstants.ENTITY_TYPE_PROV, entType)
						 || StringUtils.equalsIgnoreCase(
                                 ContactManagementConstants.ENTITY_TYPE_TP,
								 entType)|| StringUtils.equalsIgnoreCase(
		                                 ContactManagementConstants.ENTITY_TYPE_TD,
										 entType))
        {//EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010

            entityId = (String) map
                    .get(ContactManagementConstants.IPC_SYSTEMID);

        }else if(StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_UNPROV,entType)
        			&& !isNullOrEmptyField(sysID))
        {
        	
        	entityId = sysID.toString();
        	entType = "UA";
        }
        else
        {
            entityId = (String) map.get(ContactManagementConstants.IPC_ENTITYID);
        }
        
        String crspdEntityType = entityId + ":" + entType;
        showDetailPortletForCrspd(crspdEntityType);
    }

    /**
     * This operation is used to get Contact Management Entity Record details
     * based on the primary key value 'CMEntityID' for Log Case.
     */
    public void getCMEntityDetailsForCase()
    {
        

        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String entityId = ContactManagementConstants.EMPTY_STRING;
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();

        String entType = entitySearchCriteriaVO.getEntityType();
        if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_MEM, entType)
                || StringUtils.equalsIgnoreCase(
                        ContactManagementConstants.ENTITY_TYPE_PROV, entType)
						|| StringUtils.equalsIgnoreCase(
                                ContactManagementConstants.ENTITY_TYPE_TP,
								 entType))
        {
            entityId = (String) map
                    .get(ContactManagementConstants.IPC_SYSTEMID);
        }
        else
        {
            entityId = (String) map
                    .get(ContactManagementConstants.IPC_ENTITYID);
        }
        //ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
        String commonEntitySK = null;
        if(map.get("commonEntitySK")!=null){
        	commonEntitySK =(String) map.get("commonEntitySK");
        	
        }       
        
        //EOf ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
        String menuCode = (String)map.get("menuCode");
       
        String caseEntityType = entityId + ":" + entType + ":" + menuCode;
        //ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
        if(commonEntitySK!=null){
        	caseEntityType = caseEntityType + ":" +commonEntitySK;
        }
        //EOF ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
        showDetailPortletForCase(caseEntityType);
    }

    /**
     * This method is used to get the details for Log Case.
     *
     * @param caseEntity
     *            Takes caseEntity as param
     */
    public void showDetailPortletForCase(String caseEntity)
    {
        

        FacesContext fc = FacesContext.getCurrentInstance();
        
        PortletSession pSession = (PortletSession) fc.getExternalContext().getSession(true);
		pSession.setAttribute(ContactManagementConstants.ClearInqEntyData,
				ContactManagementConstants.ClearInqEntyData, pSession.APPLICATION_SCOPE);
        fc.getExternalContext().getRequestMap().put("CaseEntityDetails",
                caseEntity);

    }

    /**
     * This operation is used to get Contact Management Entity Record details
     * based on the primary key value 'CMEntityID' for Correspondence.
     */
    public void getCMInquiryAbtEntityDtls()
    {
       
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String entityId = ContactManagementConstants.EMPTY_STRING;
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();

        String entType = entitySearchCriteriaVO.getEntityType();

        if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_MEM, entType)
                || StringUtils.equalsIgnoreCase(
                        ContactManagementConstants.ENTITY_TYPE_PROV, entType))
        {
            entityId = (String) map
                    .get(ContactManagementConstants.IPC_SYSTEMID);
        }
        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_TP, entType))
        {
            entityId = (String) map
                    .get(ContactManagementConstants.GLOBALCOMMON_NOTES_COMMONENTSK);
        }
        // THE Correspondence ESPRD00436016
        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_TD, entType))
        {
        	
            entityId = (String) map
                    .get(ContactManagementConstants.GLOBALCOMMON_NOTES_COMMONENTSK);
           
        }
        else
        {
            entityId = (String) map
                    .get(ContactManagementConstants.IPC_ENTITYID);
        }
       

        String inquiryAboutEntityType = entityId + ":" + entType;

        showInquiryAbtCrspdPortlet(inquiryAboutEntityType);


    }

    /**
     * This operation is used to get Contact Management Entity Record details
     * based on the primary key value 'CMEntityID' for LogCase.
     */
    public void getCMInquiryAbtCaseEntityDtls()
    {
       
        //ESPRD00542804_Fix_04Jan11
        Map appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
		appMap.put("inqEntitySearch","inqEntitySearch");
		//ESPRD00542804_Fix_04Jan11
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String entityId = ContactManagementConstants.EMPTY_STRING;
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();

        String entType = entitySearchCriteriaVO.getEntityType();

        if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_MEM, entType)
                || StringUtils.equalsIgnoreCase(
                        ContactManagementConstants.ENTITY_TYPE_PROV, entType)
						 || StringUtils.equalsIgnoreCase(
		                        ContactManagementConstants.ENTITY_TYPE_TP, entType))
        {
            entityId = (String) map
                    .get(ContactManagementConstants.IPC_SYSTEMID);
        }
        else
        {
            entityId = (String) map
                    .get(ContactManagementConstants.IPC_ENTITYID);
        }
       
//      ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010 & ESPRD00544128
        String commonEntitySK = null;
        if(map.get("commonEntitySK")!=null){
        	commonEntitySK =(String) map.get("commonEntitySK");
        	
        }       
        
        //EOf ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010
        String inquiryAboutEntityType = entityId + ":" + entType;
        if(commonEntitySK!=null){
        	inquiryAboutEntityType = inquiryAboutEntityType + ":" +commonEntitySK;
        }
        showInquiryAbtCasePortlet(inquiryAboutEntityType);

    }

    /**
     * This method used for setting error display messages.
     *
     * @param errorName :
     *            String errorName.
     * @param arguments :
     *            Array of Object. Parameters to be passed to the message
     * @param messageBundle :
     *            String messageBundle.
     * @param componentId :
     *            String componentId.
     */
    private void setErrorMessage(String errorName, Object[] arguments,
            String messageBundle, String componentId)
    {
       

        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        Locale locale = root.getLocale();
        String clientId = null;

        facesContext.getApplication().setMessageBundle(messageBundle);
        String errorMsg = MessageUtil.format(messageBundle, errorName,
                arguments, locale);
        FacesMessage fc = new FacesMessage();
        fc.setDetail(errorMsg);

        if (componentId != null)
        {
            

            UIComponent uiComponent = findComponentInRoot(componentId);
            clientId = uiComponent.getClientId(facesContext);

            
        }

        facesContext.addMessage(clientId, fc);

    }

    /**
     * This operation is used to find the component in root by passing id.
     *
     * @param id :
     *            String object.
     * @return UIComponent : UIComponent object.
     */
    public UIComponent findComponentInRoot(String id)
    {
       

        UIComponent component = null;
        FacesContext context = FacesContext.getCurrentInstance();

        if (context != null)
        {
            UIComponent root = context.getViewRoot();
            component = findComponent(root, id);
        }

        return component;
    }

    /**
     * This operation is used to find the component by passing id.
     *
     * @param base :
     *            View root component of the jsp.
     * @param id :
     *            Id of the component from jsp.
     * @return UIComponent object.
     */
    public UIComponent findComponent(UIComponent base, String id)
    {
        


        UIComponent result = null;

        if (id.equals(base.getId()))
        {
            result = base;
        }

        else
        {

            UIComponent component = null;

            Iterator cmpIterator = base.getFacetsAndChildren();

            while (cmpIterator.hasNext() && (result == null))
            {
                component = (UIComponent) cmpIterator.next();
                if (id.equals(component.getId()))
                {
                    result = component;
                    break;
                }
                result = findComponent(component, id);
                if (result != null)
                {
                    break;
                }
            }
        }

        return result;
    }

    /**
     * This method Checks the entity Type and sets the faces context for IPC.
     *
     * @param entityId
     *            Takes entityId as param.
     */
    public void showDetailPortlet(String entityId, EntitySearchResultsVO entitySearchResultsVO)
    {

       

        FacesContext fc = FacesContext.getCurrentInstance();

        EntitySearchCriteriaVO entitySearchCriteriaVO;

        entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();

        if (entitySearchCriteriaVO.getEntityType() != null)
        {
            if (entitySearchCriteriaVO.getEntityType().equals(
                    ContactManagementConstants.ENTITY_TYPE_PROV))
					
            {
                fc.getExternalContext().getRequestMap().put("providerEntityId",
                        entityId);

                

            }else if(entitySearchCriteriaVO.getEntityType().equals(
		                    ContactManagementConstants.ENTITY_TYPE_UNPROV)
							&& entitySearchResultsVO.getSystemID() != null)
			
            	//Code uncommented below for fixing the Defect - ESPRD00792981 
            {
            	fc.getExternalContext().getRequestMap().put("providerSysID",
                        entityId);
            }
            /*Below IPC code was added for fixing the defect - ESPRD00779990.
        	 * Later we got an alternative solution to navigate to external enrollment page.
        	 * As IPC Code is not required , below  code is commented.
        	 */
            	/* ProviderEnrollmentDelegate providerEnrollmentDelegate = new ProviderEnrollmentDelegate();
                 ProviderInformationRequestVO providerInfoRequestVO = new ProviderInformationRequestVO();
                 providerInfoRequestVO.setApplicationStatusRequired(true);
                 providerInfoRequestVO.setProviderSysID(Long.valueOf(entityId));
                 Provider provider = null;
					try {
						
						provider = providerEnrollmentDelegate.getAppMntProviderDetails(providerInfoRequestVO);
						
						String statusCode = null;
						
						if(provider != null && provider.getLatestApplicationStatus().getApplicationStatusCode() != null
								&& provider.getLatestApplicationStatus().getApplicationStatusCode().length()==3)
						{
							statusCode = provider.getLatestApplicationStatus().getApplicationStatusCode().substring(0,2);
							
						}
						else
						{
							statusCode = provider.getLatestApplicationStatus().getApplicationStatusCode();
							
						}
            		

						if(!(ProviderDataConstants.PRV_ENR_APPSTATUS_INCOMPLETE_WEB.equals(statusCode)
						|| ProviderDataConstants.PRV_ENR_APPSTATUS_INCOMPLETE_PAPER.equals(statusCode)
						|| ProviderDataConstants.PRV_ENR_APPSTATUS_LOCKED.equals(statusCode)
						|| ProviderDataConstants.PRV_ENR_APPSTATUS_INCOMPLETE_WEB_ONE.equals(statusCode)
						|| ProviderDataConstants.PRV_ENR_APPSTATUS_INCOMPLETE_WEB_TWO.equals(statusCode)
						|| ProviderDataConstants.PRV_ENR_APPSTATUS_INCOMPLETE_WEB_THREE.equals(statusCode)))
						{
							
							fc.getExternalContext().getRequestMap().put("providerSysID",entityId);
						}
						else
						{
							
							if(provider.getIndividualGroupCode().equals(ProviderDataConstants.PRV_INDIVIDUAL))
							{
								
								entityId= entityId+":"+"I";
								
								fc.getExternalContext().getRequestMap().put("providerSysIDforEnrol",entityId);
								
							}
							else if(provider.getIndividualGroupCode().equals(ProviderDataConstants.PR_GROUP))
							{
								
								entityId= entityId+":"+"G";
								
								fc.getExternalContext().getRequestMap().put("providerSysIDforEnrol",entityId);
							}
						}
            	}
			  catch (ProviderAppMntException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Code added below for fixing the Defect -  ESPRD00779990 -End  
			// Code commented  below for fixing the Defect -  ESPRD00792981 -End 
                			
			}*/

            else if (entitySearchCriteriaVO.getEntityType().equals(
                    ContactManagementConstants.ENTITY_TYPE_MEM))
            {
                fc.getExternalContext().getRequestMap().put(
                        "EntityMemberDetail", entityId);
                

            }

            else if (entitySearchCriteriaVO.getEntityType().equals(
                    ContactManagementConstants.ENTITY_TYPE_TPL))
            {
                fc.getExternalContext().getRequestMap().put("tplCarrierId",
                        entityId);
                

            }
            //CR_ESPRD00486064_UC-PGM-CRM-013_08JUL2010
            else if (entitySearchCriteriaVO.getEntityType().equals(
                    ContactManagementConstants.ENTITY_TYPE_TP)){
                String policyTypeFromEntitySearch = "TPLPolicyHolderFromEntitySearch";
               
                // Commented for TPL External-API for CRM. 
                // CaseDelegate caseDelegate = new CaseDelegate();
                // Added for TPL External-API for CRM.
                TPLPolicyDelegate tplPolicyDelegate = new TPLPolicyDelegate();
                ///TPLPolicyHolder tplPolicyHolder;
                TPLPolicyHolderDetailsVO tplPolicyHolderDetailsVO;
                try {
					// / tplPolicyHolder = caseDelegate.getTPLPloicyHolder(new
					// Long(entityId));
					tplPolicyHolderDetailsVO = tplPolicyDelegate
							.getTPLPloicyHolderForCase(new Long(entityId));
					// / if (tplPolicyHolder != null)
					// /{
					// / entityId =
					// String.valueOf(tplPolicyHolder.getTplGroupPolicy().getPolicyReferenceSK());
					// /}
					if (tplPolicyHolderDetailsVO != null) {
						
						entityId = String.valueOf(tplPolicyHolderDetailsVO
								.getPolicyReferenceSK());
					}
				} catch (EnterpriseBaseBusinessException e) {
					if(logger.isErrorEnabled())
					{
						logger.error(e.fillInStackTrace());
					}
				}
                ActionRequest request = (ActionRequest)fc.getExternalContext().getRequest();
                request.setAttribute("CarrierID",entityId + "_" + policyTypeFromEntitySearch);
                if(logger.isDebugEnabled())
                {
                	logger.debug("Priting sending Parameter value---TP: "
                        + ((PortletRequest)fc.getExternalContext().getRequest()).getAttribute("TPLPolicyHolderID"));
                }
            	
            	
            }            //EOF CR_ESPRD00486064_UC-PGM-CRM-013_08JUL2010
            else if(entitySearchCriteriaVO.getEntityType().equals(
                    ContactManagementConstants.ENTITY_TYPE_TD)){
            	CMDelegate caseDelegate = new CMDelegate();
            	
            	
            		try {
						TradingPartner radingPartner=caseDelegate.getTradingPartner(entityId, null);
						
						if(radingPartner != null && radingPartner.getApprovalProcessStatusCode()!=null 
								&& radingPartner.getApprovalProcessStatusCode().equalsIgnoreCase("A")){
							if(logger.isDebugEnabled())
							{
							logger.debug(" Approve radingPartner.getClassification() : "+radingPartner.getApprovalProcessStatusCode());
							}
							ActionRequest request = (ActionRequest)fc.getExternalContext().getRequest();
							//Trading Partner Maintenance
							request.setAttribute("tradingPartnerMID",entityId);
							
						}else{
							//"FindBugs" issue fixed 
							if(radingPartner != null){
								if(logger.isDebugEnabled())
								{
									logger.debug(" NOT Approve radingPartner.getClassification() : "+radingPartner.getApprovalProcessStatusCode());
								}
							}
							ActionRequest request = (ActionRequest)fc.getExternalContext().getRequest();
							//Trading Partner App Maintenance
							request.setAttribute("tradingPartnerAppID",entityId);
						}
					} catch (CorrespondenceRecordFetchBusinessException e) {						
						e.printStackTrace();
					}
				
            	
            }            //EOF CR_ESPRD00486064_UC-PGM-CRM-013_08JUL2010
            else
            {

                fc.getExternalContext().getRequestMap().put("maintainEntityId",
                        entityId);

               
            }

        }


        cmEntitySearchDataBean.setRenderSearchResult(Boolean.FALSE);
    }

    /**
     * This method is used to get the details for Correspondance.
     *
     * @param correspondenceEntity
     *            Takes correspondenceEntity as param
     */
    public void showDetailPortletForCrspd(String correspondenceEntity)
    {
       
        FacesContext fc = FacesContext.getCurrentInstance();
        if(logger.isDebugEnabled())
        {
        	logger.debug("Entity Id:---showDetailPortletForCrspd"
                + correspondenceEntity);
        }

        fc.getExternalContext().getRequestMap().put("correspondenceEntity",
                correspondenceEntity);

        PortletSession pSession = (PortletSession) fc.getExternalContext().getSession(true);
		pSession.setAttribute(ContactManagementConstants.ClearInqEntyData,
				ContactManagementConstants.ClearInqEntyData, pSession.APPLICATION_SCOPE);

    }

    /**
     * This method is used to get the details for Inquiry about Correspondance.
     *
     * @param inquiryaboutEntityData
     *            Takes inquiryaboutEntityData as param
     */
    public void showInquiryAbtCrspdPortlet(String inquiryaboutEntityData)
    {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        if(logger.isDebugEnabled())
        {
        	logger.debug("Entity Id:showInquiryAbtCrspdPortlet" + inquiryaboutEntityData);
        }

        fc.getExternalContext().getRequestMap().put("inquiryaboutEntityData",
                inquiryaboutEntityData);
        //resetSearch();


    }

    /**
     * This method is used to get the details for Inquiry about LogCase.
     *
     * @param inquiryaboutEntityData
     *            Takes inquiryaboutEntityData as param
     */
    public void showInquiryAbtCasePortlet(String inquiryaboutEntityData)
    {
       
        FacesContext fc = FacesContext.getCurrentInstance();
        if(logger.isDebugEnabled())
        {
        	logger.debug("Entity Id:" + inquiryaboutEntityData);
        }
        fc.getExternalContext().getRequestMap().put("inqAbtCaseEntityDetails",
                inquiryaboutEntityData);

    }

    /**
     * This method Checks If Enity ID is Null.
     *
     * @param entidyID
     *            Takes Entity ID as Param
     * @return boolean
     */
    private boolean checkEntityIDNotNUll(String entidyID)
    {
        
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
        .getEntitySearchCriteriaVO();
        boolean flag = true;

        /**
         * Checks the Condition -- IF EntityID Drop Down is selected Entity ID
         * is Required
         */
        if (entidyID == null
                || ContactManagementConstants.EMPTY_STRING.equals(entidyID
                        .trim()))
        {
//        	Modified by infinite for defect ESPRD00242936
            setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                    new Object[] {ContactManagementConstants.ENTITY_ID},
                    ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
                    ContactManagementConstants.ENTITY_ID_SEARCH);

            flag = false;
        }else if(entitySearchCriteriaVO.getEntityIDType()!= null)
        {
        	if (entitySearchCriteriaVO.getEntityIDType().equalsIgnoreCase("SY")
					|| entitySearchCriteriaVO.getEntityIDType()
							.equalsIgnoreCase("TJ")
					|| entitySearchCriteriaVO.getEntityIDType()
							.equalsIgnoreCase("XX")
					|| entitySearchCriteriaVO.getEntityIDType()
							.equalsIgnoreCase("TP")
					|| entitySearchCriteriaVO.getEntityIDType()
							.equalsIgnoreCase("MCR")
					|| entitySearchCriteriaVO.getEntityIDType()
							.equalsIgnoreCase("MID")
					|| entitySearchCriteriaVO.getEntityIDType()
							.equalsIgnoreCase("RID"))
        	{
        	if(!EnterpriseCommonValidator.validateAlphaNumeric(entidyID)){
        	setErrorMessage(EnterpriseMessageConstants.ERR_DATA_INCORRECT,
					new Object[] {},
					MessageUtil.ENTMESSAGES_BUNDLE,
					ContactManagementConstants.ENTITY_ID_SEARCH);
        	flag = false;
        	}
         }else if(!EnterpriseCommonValidator.validateNumeric(entidyID))
		 {
        	setErrorMessage(EnterpriseMessageConstants.ENTITY_ID_INVALID,
					new Object[] {},
					MessageUtil.ENTMESSAGES_BUNDLE,
					ContactManagementConstants.ENTITY_ID_SEARCH);
        	flag = false;
        	}
        }


        return flag;

    }

    /**
     * This Method Checks the Valid Condition when Entity Id type is NULL.
     *
     * @param entitySearchCriteriaVO
     *            Takes entitySearchCriteriaVO as param.
     * @return boolean
     */
    private boolean checkNameNotNull(
            EntitySearchCriteriaVO entitySearchCriteriaVO)
    {
        
        boolean flag = true;

        if (!isNullOrEmptyField(entitySearchCriteriaVO.getEntityID()))
        {
            setErrorMessage(ContactManagementConstants.ENTITY_ID_TYPE_REQUIRED,
                    new Object[] {},
                    ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                    null);
            flag = false;
        }

        /** Entity Type PROVIDER ---Either Provider Sort or last name is Req */
        if (entitySearchCriteriaVO.getEntityType().equals(
                ContactManagementConstants.ENTITY_TYPE_PROV))
        {
            boolean ind1 = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getLastName()));

            boolean ind2 = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getProviderSortName()));
            boolean ind = (ind1 && ind2);

            if (ind)
            {
            	//added by infinite for defect ESPRD00242938
            	setErrorMessage(
            			ContactManagementConstants.LAST_NAME_REQUIRED,
                        new Object[] {},
                        MessageUtil.ENTMESSAGES_BUNDLE,
                        ContactManagementConstants.LAST_NAME_SEARCH);
            	//ends
                flag = false;
            }
        	if(! ind1 && entitySearchCriteriaVO.getLastName().length() < 2 )
        	{
        		setErrorMessage(
        				EnterpriseMessageConstants.ERR_SW_SEARCH_MINIMUM_TWO_CHAR,
                        new Object[] {},
                        MessageUtil.ENTMESSAGES_BUNDLE,
						 ContactManagementConstants.LAST_NAME_SEARCH);
                flag = false;
        	}
            
            

        }
//      ADDED FOR THE CR ESPRD00436016 starts
        /** Entity Type TRADINEPARTNER --- last name is Req */
        else if (entitySearchCriteriaVO.getEntityType().equals("TD"))
         		
         {
            /* boolean ind1 = (isNullOrEmptyField(entitySearchCriteriaVO
                     .getTradingPartnerName()));

             boolean ind2 = (isNullOrEmptyField(entitySearchCriteriaVO
                     .getTradingPartnerStatus()));
           

             if (ind1)
             {

             	//added by infinite for defect ESPRD00242938
             	setErrorMessage(ContactManagementConstants.TD_NAME_REQUIRED,
             			 new Object[] {},
                    
                         MessageUtil.ENTMESSAGES_BUNDLE,
                        "PNameSearch");
             	//ends
                 flag = false;
             }else {
             	if(! ind1 && entitySearchCriteriaVO.getTradingPartnerName().length() < 2 ){
             		setErrorMessage(
             				EnterpriseMessageConstants.ERR_SW_SEARCH_MINIMUM_TWO_CHAR,
                             new Object[] {},
                             MessageUtil.ENTMESSAGES_BUNDLE,
 							 "PNameSearch");
                     flag = false;
             	}
             }
         if (ind2)
             {

             	//added by infinite for defect ESPRD00242938
             	setErrorMessage(ContactManagementConstants.TD_STATUS_REQUIRED,
             			 new Object[] {},
                    
                         MessageUtil.ENTMESSAGES_BUNDLE,
                        "statusap");
             	//ends
                 flag = false;
            
         }*/
        	boolean ind1 = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getTradingPartnerStatus()));
            
            boolean ind2 = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getEntityIDType()));
           
            
            boolean ind4 = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getTradingPartnerName()));
            if(logger.isInfoEnabled())
            {
            logger.info(" validateSearchCriteriaTD getTradingPartnerStatus : "+entitySearchCriteriaVO.getTradingPartnerStatus());
            logger.info(" validateSearchCriteriaTD getEntityIDType : "+entitySearchCriteriaVO.getEntityIDType());
            logger.info(" validateSearchCriteriaTD getEntityID : "+entitySearchCriteriaVO.getEntityID());
            logger.info(" validateSearchCriteriaTD getTradingPartnerName : "+entitySearchCriteriaVO.getTradingPartnerName());
            }
            
            if (ind1)
            {        	
           	 flag = false;
            	
            	setErrorMessage(ContactManagementConstants.TD_STATUS_REQUIRED,
           			 new Object[] {},MessageUtil.ENTMESSAGES_BUNDLE,"statusap");       
            }
            
            if (ind2 && ind4)
            {        	
            	
           	 flag = false;
            	
            	setErrorMessage(ContactManagementConstants.TD_NAME_REQUIRED,
           			 new Object[] {},MessageUtil.ENTMESSAGES_BUNDLE, "PNameSearch");       
            }else if(!ind4 && entitySearchCriteriaVO.getTradingPartnerName().length() < 2 ){
            	setErrorMessage(ContactManagementConstants.TD_NAME_REQUIRED,
              			 new Object[] {},MessageUtil.ENTMESSAGES_BUNDLE, "PNameSearch");  
            	flag = false;
            	
            }
           
        }
        
        //     CR ESPRD00436016 ends
        /**
         * Entity Type UNENROLLED PROVIDER ---Either Organization or last name
         * is Req
         */
        else if (entitySearchCriteriaVO.getEntityType().equals(
                ContactManagementConstants.ENTITY_TYPE_UNPROV))
        {
            boolean ind1 = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getLastName()));

            boolean ind2 = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getOrganisationName()));
            boolean ind = (ind1 && ind2);

            if (ind)
            {
                setErrorMessage(
                        ContactManagementConstants.ORG_NAME_OR_LAST_NAME_REQ,
                        new Object[] {},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        null);
                flag = false;
            }else {
            	if(! ind && (entitySearchCriteriaVO.getLastName().length() < 2 && entitySearchCriteriaVO.getOrganisationName().length()<2)){
            		setErrorMessage(
            				EnterpriseMessageConstants.ERR_SW_SEARCH_MINIMUM_TWO_CHAR,
                            new Object[] {},
                            MessageUtil.ENTMESSAGES_BUNDLE,
                            null);
                    flag = false;
            	}
            	
            }

        }

        /** Entity Type TPL ---Carrier name is Req */
        else if (entitySearchCriteriaVO.getEntityType().equals(
                ContactManagementConstants.ENTITY_TYPE_TPL))
        {
        	
            boolean ind = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getCarrierName()));
            if (ind)
            {
            	
                setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.CARRIER_NAME},
                        MessageUtil.ENTMESSAGES_BUNDLE,
                        ContactManagementConstants.CARRIER_NAME_SEARCH);
                flag = false;
            }else {
            	if(! ind && entitySearchCriteriaVO.getCarrierName().length() < 2 ){
            		setErrorMessage(
            				EnterpriseMessageConstants.ERR_SW_SEARCH_MINIMUM_TWO_CHAR,
                            new Object[] {},
                            MessageUtil.ENTMESSAGES_BUNDLE,
							 ContactManagementConstants.CARRIER_NAME_SEARCH);
                    flag = false;
            	}
            }

        }

        /** Entity Type DO ---Org name is Req */
        else if (entitySearchCriteriaVO.getEntityType().equals(
                ContactManagementConstants.ENTITY_TYPE_DO))
        {
            boolean ind = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getOrganisationName()));
            if (ind)
            {
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.ORGANIZATION_NAME},
                        MessageUtil.ENTMESSAGES_BUNDLE,
                        ContactManagementConstants.ORG_NAME_COMPID);
                flag = false;
            }
            //added by infinite for defect ESPRD00242937
            else{
            	if(entitySearchCriteriaVO.getOrganisationName().length() < 2){
            		setErrorMessage(
            				EnterpriseMessageConstants.ERR_SW_SEARCH_MINIMUM_TWO_CHAR,
                            new Object[] {ContactManagementConstants.ORGANIZATION_NAME},
                            MessageUtil.ENTMESSAGES_BUNDLE,
                            ContactManagementConstants.ORG_NAME_COMPID);
                    flag = false;
            	}
            }
            //ends
        }
        /** Entity Type CT ---Org name is Req */
        else if (entitySearchCriteriaVO.getEntityType().equals(
                ContactManagementConstants.ENTITY_TYPE_CT))
        {
            boolean ind = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getOrganisationName()));
            if (ind)
            {
            	setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.ORGANIZATION_NAME},
                        MessageUtil.ENTMESSAGES_BUNDLE,
                        ContactManagementConstants.ORG_NAME_COMPID);
                flag = false;
            }else {
            	if(! ind && entitySearchCriteriaVO.getOrganisationName().length() < 2 ){
            		setErrorMessage(
            				EnterpriseMessageConstants.ERR_SW_SEARCH_MINIMUM_TWO_CHAR,
                            new Object[] {},
                            MessageUtil.ENTMESSAGES_BUNDLE,
							 ContactManagementConstants.ORG_NAME_COMPID);
                    flag = false;
            	}
            }

        }else if(StringUtils.equalsIgnoreCase(ContactManagementConstants.ENTITY_TYPE_TP, entitySearchCriteriaVO.getEntityType())){
        	boolean ind = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getPolicyHolderLastName()));
        	cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            if (ind)
            {
            	
            	setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.LAST_NAME},
                        ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						ContactManagementConstants.TPL_NAME_SEARCH);
            	
            	flag = false;


            }
            else {
            	if(! ind && entitySearchCriteriaVO.getPolicyHolderLastName().length() < 2 ){
            		setErrorMessage(
            				EnterpriseMessageConstants.ERR_SW_SEARCH_MINIMUM_TWO_CHAR,
                            new Object[] {},
                            MessageUtil.ENTMESSAGES_BUNDLE,
							 ContactManagementConstants.TPL_NAME_SEARCH);
                    flag = false;
            	}
            }
        }
        // Below else if condition is added for CR : ESPRD00808886
        /** Entity Type SC ---Org name is Req */
        else if (entitySearchCriteriaVO.getEntityType().equals(ContactManagementConstants.ENTITY_TYPE_SC))
        {
            boolean ind = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getOrganisationName()));
            if (ind)
            {
                setErrorMessage(
                        EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.ORGANIZATION_NAME},
                        MessageUtil.ENTMESSAGES_BUNDLE,
                        ContactManagementConstants.ORG_NAME_COMPID);
                flag = false;
            }
            //added by infinite for defect ESPRD00242937
            else{
            	if(entitySearchCriteriaVO.getOrganisationName().length() < 2){
            		setErrorMessage(
            				EnterpriseMessageConstants.ERR_SW_SEARCH_MINIMUM_TWO_CHAR,
                            new Object[] {ContactManagementConstants.ORGANIZATION_NAME},
                            MessageUtil.ENTMESSAGES_BUNDLE,
                            ContactManagementConstants.ORG_NAME_COMPID);
                    flag = false;
            	}
            }
            //ends
        }
        
        
        /** Entity Type Other ---Last name is Req */
        else
        {
            boolean ind = (isNullOrEmptyField(entitySearchCriteriaVO
                    .getLastName()));

            if (ind)
            {

            	setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
                        new Object[] {ContactManagementConstants.LAST_NAME},
                        ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
						ContactManagementConstants.LAST_NAME_SEARCH);
            	flag = false;


            }else {
            	if(! ind && entitySearchCriteriaVO.getLastName().length() < 2 ){
            		setErrorMessage(
            				EnterpriseMessageConstants.ERR_SW_SEARCH_MINIMUM_TWO_CHAR,
                            new Object[] {},
                            MessageUtil.ENTMESSAGES_BUNDLE,
							 ContactManagementConstants.LAST_NAME_SEARCH);
                    flag = false;
            	}
            }

        }

        return flag;
    }

    /**
     * This operation will be used to sort the set of records.
     *
     * @param event
     *            Takes event as param
     * @return String
     */
    public String sortSearchResults(ActionEvent event)
    {

        

        final String sortColumn = (String) event.getComponent().getAttributes()
                .get(ContactManagementConstants.COLUMN_NAME);
        

        final String sortOrder = (String) event.getComponent().getAttributes()
                .get(ContactManagementConstants.SORT_ORDER);
        

        //List searchList = new ArrayList();
        List searchList = cmEntitySearchDataBean.getSearchResultsList();
		//focus to first page by default
		cmEntitySearchDataBean.setStartIndexForSrchEntity(0);
		cmEntitySearchDataBean.setStartIndexForNOtes(0);
		//By default focus should come to first page after sort.
		cmEntitySearchDataBean.setSearchResultRowIndex(0);

        //"FindBugs" issue fixed
        /*EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean.getEntitySearchCriteriaVO();*/
                

        cmEntitySearchDataBean.setImageRender(event.getComponent().getId());

        Comparator comparator = new Comparator() {

 			public int compare(Object obj1, Object obj2) {
 				EntitySearchResultsVO data1 = (EntitySearchResultsVO) obj1;
 				EntitySearchResultsVO data2 = (EntitySearchResultsVO) obj2;
 				boolean ascending = false;
 				if ("asc".equals(sortOrder)) {
 					ascending = true;
 				} else {
 					ascending = false;
 				}

 				if (sortColumn == null) {
 					return 0;
 				}

 				if (ContactManagementConstants.ORDER_BY_ENTITYID.equals(sortColumn)) {
 					if (null == data1.getEntityID())
                     {
                         data1.setEntityID(ContactManagementConstants.EMPTY_STRING);
                     }
                     if (null == data2.getEntityID())
                     {
                         data2.setEntityID(ContactManagementConstants.EMPTY_STRING);
                     }
 			
                     Integer firstentID = Integer.valueOf(data1.getEntityID());
                     Integer secondentID = Integer.valueOf(data2.getEntityID());
                     return ascending ? firstentID.compareTo(secondentID)
                             : secondentID.compareTo(firstentID);
 				}
 				if (ContactManagementConstants.ORDER_BY_NAME.equals(sortColumn)) {
 					 if (null == data1.getEntityName() )
 	                    {
 	                        data1.setEntityName(ContactManagementConstants.EMPTY_STRING);
 	                    }
 	                    if (null == data2.getEntityName())
 	                    {
 	                        data2.setEntityName(ContactManagementConstants.EMPTY_STRING);
 	                    }
 	                    return ascending ? data1.getEntityName()
 								.compareToIgnoreCase (data2.getEntityName()): 
 									data2.getEntityName().compareToIgnoreCase(data1.getEntityName());
 				}
 				
 				if (ContactManagementConstants.ORDER_BY_LOB.equals(sortColumn)) {
 					 if (null == data1.getLineOfBusiness())
 	                    {
 	                        data1
 	                                .setLineOfBusiness(ContactManagementConstants.EMPTY_STRING);
 	                    }
 	                    if (null == data2.getLineOfBusiness())
 	                    {
 	                        data2
 	                                .setLineOfBusiness(ContactManagementConstants.EMPTY_STRING);
 	                    }
 					return ascending ? (data1.getLineOfBusiness()
 							.compareToIgnoreCase(data2.getLineOfBusiness())) : (data2
 							.getLineOfBusiness().compareToIgnoreCase(data1.getLineOfBusiness()));
 				}
 				if (ContactManagementConstants.ORDER_BY_ADDRESS.equals(sortColumn)) {
 					 if (null == data1.getAddress())
 	                    {
 	                        data1
 	                                .setAddress(ContactManagementConstants.EMPTY_STRING);
 	                    }
 	                    if (null == data2.getAddress())
 	                    {
 	                        data2
 	                                .setAddress(ContactManagementConstants.EMPTY_STRING);
 	                    }
 					return ascending ? (data1.getAddress()
 							.compareToIgnoreCase(data2.getAddress())) : (data2
 							.getAddress().compareToIgnoreCase(data1.getAddress()));
 				}
 				if (ContactManagementConstants.ORDER_BY_CITY.equals(sortColumn)) {
 					 if (null == data1.getCity())
 	                    {
 	                        data1
 	                                .setCity(ContactManagementConstants.EMPTY_STRING);
 	                    }
 	                    if (null == data2.getCity())
 	                    {
 	                        data2
 	                                .setCity(ContactManagementConstants.EMPTY_STRING);
 	                    }
 					return ascending ? (data1.getCity()
 							.compareToIgnoreCase(data2.getCity())) : (data2
 							.getCity().compareToIgnoreCase(data1.getCity()));
 				}
 				
 				
 				if (ContactManagementConstants.ORDER_BY_ORG_NAME.equals(sortColumn)) {
 					if (null == data1.getOrganisationName())
                     {
                         data1
                                 .setOrganisationName(ContactManagementConstants.EMPTY_STRING);
                     }
                     if (null == data2.getOrganisationName())
                     {
                         data2
                                 .setOrganisationName(ContactManagementConstants.EMPTY_STRING);
                     }
 					return ascending ? (data1.getOrganisationName()
 							.compareToIgnoreCase(data2.getOrganisationName()))
 							: (data2.getOrganisationName().compareToIgnoreCase(data1
 									.getOrganisationName()));
 				}

 				return 0;
 			}

 		};

 		Collections.sort(searchList, comparator);


        return ContactManagementConstants.RENDER_SUCCESS;
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
    private boolean isNullOrEmptyField(String inputField)
    {
       

        return (inputField == null || ContactManagementConstants.EMPTY_STRING
                .equalsIgnoreCase(inputField.trim()));

    }

    /**
     * This operation will be used for navigating to previous page in the
     * pagination.
     *
     * @return String : String ProgramConstants.SEARCH_SUCCESS Navigation
     *         message
     */
    public String previous()
    {
        
        //ESPRD00542804_Fix_04Jan11
        Map appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
		appMap.put("inqEntitySearch","inqEntitySearch");
		//ESPRD00542804_Fix_04Jan11
        CMEntitySearchDataBean cmEntitySearchDataBean = getCmEntitySearchDataBean();
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();

        EnterpriseBaseDataBean entDataBean = previousPage(
                cmEntitySearchDataBean, cmEntitySearchDataBean
                        .getItemsPerPage());
        entitySearchCriteriaVO.setStartIndex(entDataBean.getStartRecord() - 1);
        populateList();

        return ContactManagementConstants.PREVIOUS_SUCCESS;
    }

    /**
     * This operation will be used for navigating to next page in the
     * pagination.
     *
     * @return String : ProgramCodeMaintanaceConstants.SEARCH_SUCCESS Navigation
     *         message
     */
    public String next()
    {
        
//ESPRD00542804_Fix_04Jan11
        Map appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
		appMap.put("inqEntitySearch","inqEntitySearch");
        //ESPRD00542804_Fix_04Jan11
        
        CMEntitySearchDataBean cmEntitySearchDataBean = getCmEntitySearchDataBean();
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();
        EnterpriseBaseDataBean entDataBean = nextPage(cmEntitySearchDataBean,
                cmEntitySearchDataBean.getItemsPerPage());
        entitySearchCriteriaVO.setStartIndex(entDataBean.getStartRecord() - 1);
        
        populateList();
        return ContactManagementConstants.NEXT_SUCCESS;
    }

    public String current()
	{
    	
    	Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
         if(map!=null && map.get("index")!=null){
         	int startIndex = Integer.parseInt((String) map.get("index"));
         	int index = startIndex;
         	
         	List resultList = cmEntitySearchDataBean.getSearchResults();
         	
         	if(index > 3)
         	{
         		index = startIndex%3;
         	}
         	if(index == 0){
         		index = 3;
         	}
         	
         	cmEntitySearchDataBean.setFromIndex((startIndex-1)*10+1);
         	if(index*10 > resultList.size())
         	{
         		cmEntitySearchDataBean.setSearchResultsList(resultList.subList(((index-1)*10),resultList.size()));
                cmEntitySearchDataBean.setToIndex(resultList.size());
         	}else{
         		cmEntitySearchDataBean.setSearchResultsList(resultList.subList(((index-1)*10),(index*10)));
         		cmEntitySearchDataBean.setToIndex(startIndex*10);
         	}
         }
    	return ContactManagementConstants.NEXT_SUCCESS;
	}
    /**
     * This method is used for populating search results for a given search
     * criteria.
     */
    private void populateList()
    {
        

        CMEntitySearchDataBean cmEntitySearchDataBean = getCmEntitySearchDataBean();
        EntitySearchCriteriaVO entitySearchCriteriaVO = cmEntitySearchDataBean
                .getEntitySearchCriteriaVO();
        

        CMEntityDelegate cmEntityDel = new CMEntityDelegate();
        //"FindBugs" issue fixed
        //EnterpriseSearchResultsVO enterpriseSearchResultsVO = new EnterpriseSearchResultsVO();
        EnterpriseSearchResultsVO enterpriseSearchResultsVO = null;
        entitySearchCriteriaVO
                .setRowsPerPage(ProgramNumberConstants.NO_OF_RECORD_TO_FETCH);

        try
        {
            enterpriseSearchResultsVO = cmEntityDel
                    .getEntities(entitySearchCriteriaVO);
            
            //"FindBugs" issue fixed
            //List resultsList = new ArrayList();
            List resultsList = null;
            resultsList = cmEntityDOConvertor.getEntities(
                    enterpriseSearchResultsVO.getSearchResults(),
                    entitySearchCriteriaVO);

            if (resultsList.isEmpty())
            {
                setErrorMessage(
                        ContactManagementConstants.ENTITY_ID_TYPE_ENROLLED,
                        new Object[] {},
                        ContactManagementConstants.CONTACT_MGMT_ERR_MSG_BUNDLE,
                        null);

            }

            else
            {
                cmEntitySearchDataBean.setSearchResultsList(resultsList);
                cmEntitySearchDataBean.setSearchResults(resultsList);
                
                cmEntitySearchDataBean.setRenderSearchResult(Boolean.TRUE);
                setRecordRangeForEntity(enterpriseSearchResultsVO,entitySearchCriteriaVO.getStartIndex());
            }

        }
        catch (CMEntityFetchBusinessException e)
        {
            if(logger.isErrorEnabled())
            {
            logger.error(e.getMessage(), e);
            }
            setErrorMessage(
                    EnterpriseMessageConstants.ERR_SW_SEARCH_NO_RECORD_FOUND,
                    new Object[] {}, MessageUtil.ENTMESSAGES_BUNDLE, null);

        }


    }

    /**
     * This Method is used to set the initial record range.
     *
     * @param enterpriseSearchResultsVO :
     *            EnterpriseSearchResultsVO object
     */
    private void setRecordRange(
            EnterpriseSearchResultsVO enterpriseSearchResultsVO)
    {
        
        long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
        CMEntitySearchDataBean cmEntitySearchDataBean = getCmEntitySearchDataBean();
        int listSize = 0;
        int noOfPages = 0;
        int modNofPages = 0;

        cmEntitySearchDataBean.setCount((int) totalRecordCount);

        if(enterpriseSearchResultsVO.getSearchResults() != null)
        {
        	listSize = enterpriseSearchResultsVO.getSearchResults().size();
        	
        }
        if(listSize > 0)
        {
        	noOfPages = listSize / ProgramNumberConstants.INT_TEN;
        	

        	if((listSize % ProgramNumberConstants.INT_TEN) != 0)
        	{
        		noOfPages = noOfPages + ProgramNumberConstants.INT_ONE;
        	}
        }

        cmEntitySearchDataBean.setPreviousPageIndex(1);
        cmEntitySearchDataBean.setCurrentPage(2);
        cmEntitySearchDataBean.setNextPageIndex(3);
        cmEntitySearchDataBean.setNumberOfPages(noOfPages);
        cmEntitySearchDataBean.setStartRecord(ProgramNumberConstants.INT_ONE);
        cmEntitySearchDataBean.setEndRecord(ProgramNumberConstants.INT_TEN);


        if (listSize <= ProgramNumberConstants.INT_TEN)
        {
            cmEntitySearchDataBean.setEndRecord(listSize);
        }
        //"FindBugs"issue fixed starts
        /*if (listSize > cmEntitySearchDataBean.getItemsPerPage())
        {
            listSize = cmEntitySearchDataBean.getItemsPerPage();
        }*/
        //"FindBugs"issue fixed ends
        if (cmEntitySearchDataBean.getCount() <= cmEntitySearchDataBean
                .getItemsPerPage())
        {
            cmEntitySearchDataBean.setShowNext(false);
        }
        else
        {
            cmEntitySearchDataBean.setShowNext(true);
        }
        cmEntitySearchDataBean.setShowPrevious(false);

    }

    /**
     * @return Returns the cmEntityDOConvertor.
     */
    public CMEntityDOConvertor getCmEntityDOConvertor()
    {
        return cmEntityDOConvertor;
    }

    /**
     * @param cmEntityDOConvertor
     *            The cmEntityDOConvertor to set.
     */
    public void setCmEntityDOConvertor(CMEntityDOConvertor cmEntityDOConvertor)
    {
        this.cmEntityDOConvertor = cmEntityDOConvertor;
    }

    /**
     * @param cmEntitySearchDataBean
     *            The cmEntitySearchDataBean to set.
     */
    public void setCmEntitySearchDataBean(
            CMEntitySearchDataBean cmEntitySearchDataBean)
    {
        this.cmEntitySearchDataBean = cmEntitySearchDataBean;
    }

    /**
     * This method Opens the MaintainEntity Page from Search Corres Entity.
     */
    public void addEntityFromSearchCorrEntity()
    {
       

        FacesContext fc = FacesContext.getCurrentInstance();
        String fromPageName = ContactManagementConstants.CORRESPONDENCE;

        fc.getExternalContext().getRequestMap().put("MaintainEntity",
                fromPageName);
        if(logger.isDebugEnabled())
        {
        	logger.debug("value in req map  for frompagename - " + fc.getExternalContext().getRequestMap().get("MaintainEntity").toString());
        }

    }

    /**
     * This method Opens the MaintainEntity Page from Search  Entity.
     */
    public void addEntityFromSearchEntity()
    {
     
        FacesContext fc = FacesContext.getCurrentInstance();
        String fromPageName = ContactManagementConstants.SEARCH_ENTITY;

        fc.getExternalContext().getRequestMap().put("maintainEntityId",
                fromPageName);

        

    }

    /**
     * This method is used to navigate AddEntity Page Correspondence Entity.
     */
    public void addEntityFromInquiryEntity()
    {
    	
        FacesContext fc = FacesContext.getCurrentInstance();
        String fromPageName = ContactManagementConstants.INQUIRY_ENTITY;

        fc.getExternalContext().getRequestMap().put("MaintainEntity",
                fromPageName);



    }
    /**
     * This method is used to navigate AddEntity Page from Case Entity.
     */
    public void addEntityFromSearchCaseEntity()
    {
    	
        FacesContext fc = FacesContext.getCurrentInstance();
        String fromPageName = "FromSearchCaseEntity";

        fc.getExternalContext().getRequestMap().put("MaintainEntity",
                fromPageName);
    }
    /**
     * This method is used to navigate AddEntity Page  from inquiry about case entity search.
     */
    public void addEntityFromInquiryCaseEntity()
    {
    	
        FacesContext fc = FacesContext.getCurrentInstance();
        String fromPageName = "FromInquiryCaseEntity";

        fc.getExternalContext().getRequestMap().put("MaintainEntity",
                fromPageName);
    }


    /**
     * Gets reference of CMEntityDataBean.
     *
     * @author Wipro.
     * @return CMEntityDataBean
     */
    public CMEntityMaintainDataBean getCmEntityMaintainDataBean()
    {

        

        FacesContext fc = FacesContext.getCurrentInstance();
        CMEntityMaintainDataBean cmEntityMaintainDataBean = (CMEntityMaintainDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + ContactManagementConstants.CMMAINTAINENTITY_BEAN_NAME + "}")
                .getValue(fc);

        return cmEntityMaintainDataBean;
    }

    /**
     * This method returns the menu item value.
     * @author Wipro.
     * @return menu value.
     */
    public String getMenuCode()
    {
        String menuActionCode = ContactManagementConstants.EMPTY_STRING;
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object request = context.getRequest();
        ServletRequest req = (ServletRequest) request;
        
 //       ModelUtil util;
        try
        {
//         util = ModelUtil.from(req);
//         NavigationNode node = (NavigationNode)util.getNavigationSelectionModel().getSelectedNode();
//         menu = node.getContentNode().getObjectID().getUniqueName();
//         getCmEntitySearchDataBean().setMenuActionCode(menu);
			menuActionCode = EnterpriseBaseBeanHelper.getInstance().getMenuActionCode((HttpServletRequest) request);
			getCmEntitySearchDataBean().setMenuActionCode(menuActionCode);
			

        }
        catch (Exception e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error("ModelException", e);
        	}
        }
        return menuActionCode;
    }






    public void doSrchCaseComAction(
    		CaseRecordSearchCriteriaVO caserecordSearchCriteriaVO) {

		
//		ESPRD0033432_20112009



		if ((caserecordSearchCriteriaVO.getEntityType() != null && !caserecordSearchCriteriaVO
				.getEntityType().equals(""))
				|| (caserecordSearchCriteriaVO.getEntityId() != null && !caserecordSearchCriteriaVO
						.getEntityId().equals(""))) {
			//cmEntitySearchDataBean.setAdditional("Entity");
			getCmEntitySearchDataBean().setAdditional("Entity");
			//getCmEntitySearchDataBean().getEntitySearchCriteriaVO().setTarget(caserecordSearchCriteriaVO.getTarget());
			getCmEntitySearchDataBean().setTarget(caserecordSearchCriteriaVO.getTarget());
			//modified cmEntitySearchDataBean as getCmEntitySearchDataBean()

			if (caserecordSearchCriteriaVO.getEntityType() != null
					&& !caserecordSearchCriteriaVO.getEntityType().equals(
							"")) {
				getCmEntitySearchDataBean().getEntitySearchCriteriaVO()
						.setEntityType(
								caserecordSearchCriteriaVO.getEntityType());
				renderRespEntityBlock();
			} 

			if (caserecordSearchCriteriaVO.getEntityIDType() != null
					&& !caserecordSearchCriteriaVO.getEntityIDType()
							.equals("")) {
				getCmEntitySearchDataBean().getEntitySearchCriteriaVO()
						.setEntityIDType(
								caserecordSearchCriteriaVO
										.getEntityIDType());
			}

			if (caserecordSearchCriteriaVO.getEntityId() != null
					&& !caserecordSearchCriteriaVO.getEntityId().equals("")) {
				getCmEntitySearchDataBean().getEntitySearchCriteriaVO().setEntityID(
						caserecordSearchCriteriaVO.getEntityId());
			}

			getCmEntitySearchDataBean().setDataMapIden("C");

		}

		if((caserecordSearchCriteriaVO.getAdditionalEntityType()!=null && !caserecordSearchCriteriaVO.getAdditionalEntityType().trim().equals("")) ||
				(caserecordSearchCriteriaVO.getAdditionalEntityIDType()!=null && !caserecordSearchCriteriaVO.getAdditionalEntityIDType().trim().equals(""))||
				(caserecordSearchCriteriaVO.getAdditionalEntityID()!=null && !caserecordSearchCriteriaVO.getAdditionalEntityID().trim().equals("")) ){
			getCmEntitySearchDataBean().setAdditional("additionalEntity");
			//ESPRD0033432_20112009

			getCmEntitySearchDataBean().setTarget(caserecordSearchCriteriaVO.getTarget());
			if (caserecordSearchCriteriaVO.getAdditionalEntityType() != null
					&& !caserecordSearchCriteriaVO.getAdditionalEntityType().equals(
							"")) {
				getCmEntitySearchDataBean().getEntitySearchCriteriaVO()
						.setEntityType(
								caserecordSearchCriteriaVO.getAdditionalEntityType());
				renderRespEntityBlock();
			}

			if (caserecordSearchCriteriaVO.getAdditionalEntityIDType() != null
					&& !caserecordSearchCriteriaVO.getAdditionalEntityIDType()
							.equals("")) {
				getCmEntitySearchDataBean().getEntitySearchCriteriaVO()
						.setEntityIDType(
								caserecordSearchCriteriaVO
										.getAdditionalEntityIDType());
			}

			if (caserecordSearchCriteriaVO.getAdditionalEntityID() != null
					&& !caserecordSearchCriteriaVO.getAdditionalEntityID().equals("")) {
				getCmEntitySearchDataBean().getEntitySearchCriteriaVO().setEntityID(
						caserecordSearchCriteriaVO.getAdditionalEntityID());
			}


			//getCmEntitySearchDataBean().setDataMapIden("C");//UC-PGM-CRM-033_ESPRD00624909_09jun2011
			getCmEntitySearchDataBean().setDataMapIden("I");//UC-PGM-CRM-033_ESPRD00624909_09jun2011

		}//ESPRD0033432_20112009
		else {
			
			getCmEntitySearchDataBean().setTarget(caserecordSearchCriteriaVO.getTarget());
			
		}


	}



    public CommonEntityDataBean getCommonEntityDataBean()
    {

        FacesContext fc = FacesContext.getCurrentInstance();
        CommonEntityDataBean cmEntityDataBean = (CommonEntityDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + CommonEntityDataBean.BEAN_NAME + "}")
                .getValue(fc);

        return cmEntityDataBean;
    }

    // end

    private boolean renderEntityFlag=true;

    /**
     * This Method Renders the Different Search Entity Block based on the Entity
     * Type .
     *
     * @return boolean
     */
    private boolean ajaxrenderRespEntityBlock = true;
	/**
	 * @return Returns the ajaxSearchEntity.
	 */

	/**
	 * @return Returns the ajaxrenderRespEntityBlock.
	 */
	public boolean isAjaxrenderRespEntityBlock() {


		

        String entityType = null;
        Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
         if(map!=null && map.get("entityTypSrch")!=null){
         	entityType = map.get("entityTypSrch").toString();

         }
       if(entityType!=null && !(entityType.equalsIgnoreCase("")) &&renderEntityFlag)
       {

       
        cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityType(
        		entityType);

        /** This will not reset the Search Page on Value Change of Entity Type */
        cmEntitySearchDataBean.setNewSearchEntity(false);

        /** ENTITY TYPE -- MEMBER */

        if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_MEM, entityType))
        {
            

            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_MEM);
            /** Populates the Entity ID Type dropdown for Entity Type Member */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
        }

        /** ENTITY TYPE -- PROVIDER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_PROV, entityType))
        {

          
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    "G_REP", "PROV_PSTN_CD");
            /** Populates the Entity ID Type dropdown for Entity Type Provider */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.PROVIDER,sysListNumber) != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.PROVIDER,sysListNumber));
            }

            /** Populates the Provider Type dropdown for Entity Type Provider */
            if (cmEntityDOConvertor.getProvTypeReferenceData() != null)
            {
                cmEntitySearchDataBean.setProvTypeList(cmEntityDOConvertor
                        .getProvTypeReferenceData());
            }

            /** Populates the LOB dropdown for Entity Type Provider */
            if (cmEntityDOConvertor.getLobReferenceData() != null)
            {
                cmEntitySearchDataBean.setLobList(cmEntityDOConvertor
                        .getLobReferenceData());
            }

            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
        }

        /** ENTITY TYPE -- UNENROLLED PROVIDER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_UNPROV, entityType))
        {

          

            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_UNPROV);
            /** Populates the Entity ID Type dropdown for Entity Type Unenrolled Provider */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Clear the Drop Down if any for Unenrolled Provider */

            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
        }

        /** ENTITY TYPE -- TPL CARRIER */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_TPL, entityType))
        {

            

            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_TPL);
            /** Populates the Entity ID Type dropdown for Entity Type TPL Carrier */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }

            /** Clear the Drop Down if any for TPL */

            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
        }

        /** ENTITY TYPE -- DISTRICT OFFICE */

        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_DO, entityType))
        {

            

            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_DO);
            /** Populates the Entity ID Type dropdown for Entity Type District Office */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);

        }
        else if (StringUtils.equalsIgnoreCase(
                ContactManagementConstants.ENTITY_TYPE_CT, entityType))
        {

            
            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_CT);
            /** Populates the Entity ID Type dropdown for Entity Type County */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);

        }
        // added for CR ESPRD00486064 starts
        else if (StringUtils.equalsIgnoreCase(
        		ContactManagementConstants.ENTITY_TYPE_TP, entityType))
        {

            
//            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
//                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_CT);
            /** Populates the Entity ID Type dropdown for Entity Type County */
//            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
//                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
//            {
//                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
//                        FunctionalAreaConstants.GENERAL,sysListNumber));
//            }
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderTradingPartner(Boolean.FALSE);
          //for fixing defect ESPRD00576206
            cmEntitySearchDataBean.setRenderRequired(Boolean.TRUE);
        }
        // added for CR ESPRD00486064 ends
//      ADDED FOR THE CR ESPRD00436016
        else if (StringUtils.equalsIgnoreCase(
                "TD", entityType))
        {

            
            cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,new Long("0181")));
           cmEntitySearchDataBean.getEntityIDTypeList().add(new SelectItem("TP","Trading Partner ID"));
            cmEntitySearchDataBean.getEntityIDTypeList().add(new SelectItem("CM","Trading Partner CM ID"));
            cmEntitySearchDataBean. setTradingParnterStatusList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.EDI_TRADING,new Long("0003")));
            cmEntitySearchDataBean.setTradingPartnerclassificationList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.EDI_TRADING,new Long("1003")));

           /* Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_TPL);
            *//** Populates the Entity ID Type dropdown for Entity Type TPL Carrier *//*
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }*/

            /** Clear the Drop Down if any for TPL */

            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();
            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderNameSection(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTradingPartner(Boolean.TRUE);
        }
//      END FOR THE CR ESPRD00436016
        else
        {
            /** ENTITY TYPE -- UNENROLLED MEMBER and other Entities */

            

            Long sysListNumber = ContactManagementHelper.getSystemlistForEntyIdType(
                    ContactManagementConstants.ENTITYIDTYPE, ContactManagementConstants.ENTITY_TYPE_CODE_OTHER);
            /** Populates the Entity ID Type dropdown for Entity Type Other */
            if (cmEntityDOConvertor.getEntIDTypeReferenceData(
                    FunctionalAreaConstants.GENERAL,sysListNumber) != null)
            {
                cmEntitySearchDataBean.setEntityIDTypeList(cmEntityDOConvertor.getEntIDTypeReferenceData(
                        FunctionalAreaConstants.GENERAL,sysListNumber));
            }
            /** Clear the Drop Down if any for Unenrolled Member */

            cmEntitySearchDataBean.getProvTypeList().clear();
            cmEntitySearchDataBean.getLobList().clear();

            /** Render the Block that is to be Dispalyed and Disable others */
            cmEntitySearchDataBean.setRenderNameSection(Boolean.TRUE);
            cmEntitySearchDataBean.setRenderProvider(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderCarrierNam(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderOrganizationName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderLOB(Boolean.FALSE);
            cmEntitySearchDataBean.setCountyName(Boolean.FALSE);
            cmEntitySearchDataBean.setRenderTPLPlcyHldr(Boolean.FALSE);

        }
        renderEntityFlag=false;

		}
       

		return ajaxrenderRespEntityBlock;
	}
	/**
	 * @param ajaxrenderRespEntityBlock The ajaxrenderRespEntityBlock to set.
	 */
	public void setAjaxrenderRespEntityBlock(boolean ajaxrenderRespEntityBlock) {
		this.ajaxrenderRespEntityBlock = ajaxrenderRespEntityBlock;
	}

	public void doSrchCorrComAction(
			CorrespondenceSearchCriteriaVO correspondenceSearchCriteriaVO) {

		


		CMEntitySearchDataBean cmEntitySearchDataBean = getCmEntitySearchDataBean();
		
		//Modified the existing code for wiring issue between Entity Search to Log Correspondence
		
		if ((correspondenceSearchCriteriaVO.getEntityType() != null && !correspondenceSearchCriteriaVO
				.getEntityType().equals(""))
				|| (correspondenceSearchCriteriaVO.getEntityID() != null && correspondenceSearchCriteriaVO.getEntityID().equalsIgnoreCase(" ".trim()))) {

			if (correspondenceSearchCriteriaVO.getEntityType() != null
					&& !correspondenceSearchCriteriaVO.getEntityType().equals(
							"")) {
				cmEntitySearchDataBean.getEntitySearchCriteriaVO()
						.setEntityType(
								correspondenceSearchCriteriaVO.getEntityType());
				renderRespEntityBlock();
			}

			if (correspondenceSearchCriteriaVO.getEntityIDType() != null
					&& !correspondenceSearchCriteriaVO.getEntityIDType()
							.equals("")) {
				cmEntitySearchDataBean.getEntitySearchCriteriaVO()
						.setEntityIDType(
								correspondenceSearchCriteriaVO
										.getEntityIDType());
			}

			if (correspondenceSearchCriteriaVO.getEntityID() != null
					&& !correspondenceSearchCriteriaVO.getEntityID().equals("")) {
				cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
						correspondenceSearchCriteriaVO.getEntityID());
			}

			cmEntitySearchDataBean.setDataMapIden("C");

		}

		else {
			if (correspondenceSearchCriteriaVO.getInqEntityType() != null
					&& !correspondenceSearchCriteriaVO.getInqEntityType()
							.equals("")) {
				cmEntitySearchDataBean.getEntitySearchCriteriaVO()
						.setEntityType(
								correspondenceSearchCriteriaVO
										.getInqEntityType());

				renderRespEntityBlock();
			}

			if (correspondenceSearchCriteriaVO.getInqEntityIDType() != null
					&& !correspondenceSearchCriteriaVO.getInqEntityIDType()
							.equals("")) {
				cmEntitySearchDataBean.getEntitySearchCriteriaVO()
						.setEntityIDType(
								correspondenceSearchCriteriaVO
										.getInqEntityIDType());
			}

			if (correspondenceSearchCriteriaVO.getInqEntityID() != null
					&& !correspondenceSearchCriteriaVO.getInqEntityID()
							.equals("")) {
				cmEntitySearchDataBean.getEntitySearchCriteriaVO().setEntityID(
						correspondenceSearchCriteriaVO.getInqEntityID());
			}

			cmEntitySearchDataBean.setDataMapIden("I");
		}


	}


	/**
	 * @return Returns the intialData.
	 */
	public String getIntialData() {
		
		getUserPermission();
		return intialData;
	}
	/**
	 * @param intialData The intialData to set.
	 */
	public void setIntialData(String intialData) {
		this.intialData = intialData;
	}

	private void setRecordRangeForEntity(
            EnterpriseSearchResultsVO enterpriseSearchResultsVO, int startIndex)
    {
        

        long totalRecordCount = enterpriseSearchResultsVO.getRecordCount();
        CMEntitySearchDataBean cmEntitySearchDataBean = getCmEntitySearchDataBean();
        int listSize = 0;
        int noOfPages = 0;
        int modNofPages = 0;
        int index = 0;
        if(startIndex != 0){
        	index = startIndex/10;
        }
        cmEntitySearchDataBean.setCount((int) totalRecordCount);

        if(enterpriseSearchResultsVO.getSearchResults() != null)
        {
        	listSize = enterpriseSearchResultsVO.getSearchResults().size();
        	
        }
        if(listSize > 0)
        {
        	noOfPages = listSize / ProgramNumberConstants.INT_TEN;
        	

        	if((listSize % ProgramNumberConstants.INT_TEN) != 0)
        	{
        		noOfPages = noOfPages + ProgramNumberConstants.INT_ONE;
        	}
        }

        cmEntitySearchDataBean.setPreviousPageIndex(index+1);
        cmEntitySearchDataBean.setCurrentPage(index+2);
        cmEntitySearchDataBean.setNextPageIndex(index+3);
        cmEntitySearchDataBean.setNumberOfPages(noOfPages);
        cmEntitySearchDataBean.setStartRecord(startIndex+1);
        cmEntitySearchDataBean.setEndRecord(startIndex+10);
        cmEntitySearchDataBean.setFromIndex(startIndex+1);


        if (listSize <= ProgramNumberConstants.INT_TEN)
        {
            cmEntitySearchDataBean.setEndRecord(listSize);
            cmEntitySearchDataBean.setToIndex(startIndex+listSize);
        }else{
        	cmEntitySearchDataBean.setToIndex(startIndex+10);
        }
        //"FindBugs"issue fixed starts
        /*if (listSize > cmEntitySearchDataBean.getItemsPerPage())
        {
            listSize = cmEntitySearchDataBean.getItemsPerPage();
        }*/
        //"FindBugs"issue fixed ends
        if (cmEntitySearchDataBean.getCount() <= startIndex+cmEntitySearchDataBean
                .getItemsPerPage())
        {
            cmEntitySearchDataBean.setShowNext(false);
        }
        else
        {
            cmEntitySearchDataBean.setShowNext(true);
        }
        if(startIndex >= 30)
        {
        	cmEntitySearchDataBean.setShowPrevious(true);
        }else{
        	cmEntitySearchDataBean.setShowPrevious(false);
        }
    }
//	added for ESPRD00330098
	private String initializePage = ContactManagementConstants.EMPTYSTRING;

	/**
	 * @return Returns the initializePage.
	 */
	public String getInitializePage() {
		
		if(entityTypeChangeFlag){
			getCmEntitySearchDataBean().getEntitySearchCriteriaVO().setEntityType(String.valueOf(entityTypeChangeSelectedObject));
			renderRespEntityBlock();
		}
		
		return initializePage;
	}
	/**
	 * @param initializePage The initializePage to set.
	 */
	public void setInitializePage(String initializePage) {
		this.initializePage = initializePage;
	}
	private boolean entityTypeChangeFlag = false;
	private Object entityTypeChangeSelectedObject  = null;// this field holds the selected entity type's value temporarly to set the value to search crieteria vo's EntityType filed in getInitializePage's method

	public void entityTypeChangeAction(ValueChangeEvent event){
		
		entityTypeChangeFlag=true;// based on this flag onchange of entity type's  renderRespEntityBlock() method will invoked.
		if(event.getNewValue()!=null){
			entityTypeChangeSelectedObject = event.getNewValue();
		}else{
			entityTypeChangeSelectedObject = ContactManagementConstants.EMPTYSTRING;
		}
		getCmEntitySearchDataBean().setFocusThisId("inqEntityType");
		Map appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
		appMap.put("inqEntitySearch","inqEntitySearch");
		getCmEntitySearchDataBean().getEntitySearchCriteriaVO().setEntityType(String.valueOf(entityTypeChangeSelectedObject));
		
		 
		
	}
	//EOf ESPRD00330098
	private String loadProviderData=null;
	public String getLoadProviderData(){
		//"FindBugs" issue fixed 
		//String entityType = getCmEntitySearchDataBean().getEntitySearchCriteriaVO().getEntityType();
         if ("Add Provider Appeals".equals(getMenuCode())){         	//
         	if(getCmEntitySearchDataBean().isProviderDefaultFlag()==false){
         		getCmEntitySearchDataBean().getEntitySearchCriteriaVO().setEntityType("P");
         		renderRespEntityBlock();
         		getCmEntitySearchDataBean().setProviderDefaultFlag(true);
         	}
         }else if("AddNewAppeals".equals(getMenuCode())){
         	if(getCmEntitySearchDataBean().isMemeberDefaultFlag()==false){
         		getCmEntitySearchDataBean().getEntitySearchCriteriaVO().setEntityType(ContactManagementConstants.ENTITY_TYPE_MEM);
         		renderRespEntityBlock();
         		getCmEntitySearchDataBean().setMemeberDefaultFlag(true);
         	}
         }
		return "";
	}
	/**
	 * @param loadProviderData The loadProviderData to set.
	 */
	public void setLoadProviderData(String loadProviderData) {
		this.loadProviderData = loadProviderData;
	}
	/**
	 * @return Returns the link2Show.
	 */
	public String getLink2Show() {

		FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
		String userid = getUserID();
		try {
			
			String addProviderAppealValue = fieldAccessControlImpl
			.getFiledAccessPermission("/Enterprise/AddProviderAppeals",
					userid);
			
			String addMemberAppealValue = fieldAccessControlImpl
					.getFiledAccessPermission(
							"/Enterprise/AddNewMemberAppeals", userid);
			
			String returnValue = fieldAccessControlImpl
			.getFiledAccessPermission("/Enterprise/CtMgmtCase", userid);
			
			/*String returnValue = fieldAccessControlImpl
					.getFiledAccessPermission(
							ContactManagementConstants.CTMGMT_ADD_APPEALS, userid);*/
			
			if (("R".equalsIgnoreCase(returnValue))||("R".equalsIgnoreCase(addProviderAppealValue))||("R".equalsIgnoreCase(addMemberAppealValue))) {
				cmEntitySearchDataBean.setDisableAddEntity(ContactManagementConstants.TRUE);
			}
			/*if ("R".equalsIgnoreCase(returnValue)) {
				cmEntitySearchDataBean.setDisableAddEntity(ContactManagementConstants.TRUE);
			}*/
			
		} catch (SecurityFLSServiceException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}catch(Exception e){
			
			e.printStackTrace();
		}


    	return link2Show;
    }

    /**
     * @return Map linksMap.
     */
    public Map getPermissions()
    {
    	Map linksMap = new HashMap();
    	String userid = getUserID();
    	
    	FieldAccessControl fieldAccessControlImpl = new FieldAccessControl();
    	

    	ArrayList linksList2Pass = new ArrayList();
    	linksList2Pass.add(ContactManagementConstants.SEARCH_CASE_ENTITY_ADDENTITY);
    	try {
    		linksMap =  fieldAccessControlImpl.getActionLinkPermission(linksList2Pass,userid);

    	} catch (SecurityFLSServiceException e) {
    		e.getCause();
    		
    	}

    	return linksMap;
    }

	/**
	 * @param link2Show The link2Show to set.
	 */
	public void setLink2Show(String link2Show) {
		this.link2Show = link2Show;
	}
//	UC-PGM-CRM-072_ESPRD00425145_24FEB2010
	private HtmlDataTable searchResultDataTable ;

	/**
	 * @return Returns the searchResultDataTable.
	 */
	public HtmlDataTable getSearchResultDataTable() {
		return searchResultDataTable;
	}
	/**
	 * @param searchResultDataTable The searchResultDataTable to set.
	 */
	public void setSearchResultDataTable(HtmlDataTable searchResultDataTable) {
		this.searchResultDataTable = searchResultDataTable;

	}	//EOF UC-PGM-CRM-072_ESPRD00425145_24FEB2010
	
	private String cancelValue;
	
	/**
	 * @return Returns the cancelValue.
	 */
	public String getCancelValue() 
	{
		
		if(getCmEntitySearchDataBean().getSearchResultsList()!=null && !getCmEntitySearchDataBean().getSearchResultsList().isEmpty() )
		{
			getCmEntitySearchDataBean().setRenderSearchResult(Boolean.TRUE);
		}
		return cancelValue;
	}
	/**
	 * @param cancelValue The cancelValue to set.
	 */
	public void setCancelValue(String cancelValue) {
		this.cancelValue = cancelValue;
	}
	
	 /**
	 * Below method is added for CR ESPRD00902782
     * This Method takes the Search Results and set the address and city name for Member Entity.
     *
     * @param resultsList
     *            Takes resultsList as param.
     
     */
		
	private void setMemberAddress(List resultsList)
	{
		
		if(resultsList!=null && resultsList.size()>0)
		{
        Iterator itr = resultsList.iterator();
        
        while (itr.hasNext())
        {
            EntitySearchResultsVO entitySearchResultsVO = (EntitySearchResultsVO) itr
                    .next();
            	 CommonAddressDelegate commonAddressDelegate = null;
         		 CommonModuleAddressInfo commonModuleAddressInfo = null;
                try {
					commonAddressDelegate = new CommonAddressDelegate();
					if(null !=entitySearchResultsVO.getMemCmnEntSK())
					{
					commonModuleAddressInfo = commonAddressDelegate.getAddressWithCommonSk(
							ContactManagementConstants.ENTITY_TYPE_MEM,entitySearchResultsVO.getMemCmnEntSK()
									.toString(), null,ContactManagementConstants.ADD_USAGE_STATUS_CD_A);
					}
					if(null != commonModuleAddressInfo)
					{
						if(null != commonModuleAddressInfo.getAddress())
						{
							if(null !=commonModuleAddressInfo.getAddress().getAddressLine1())
							{
							entitySearchResultsVO.setAddress(commonModuleAddressInfo.getAddress().getAddressLine1());
							}
							if(null !=commonModuleAddressInfo.getAddress().getCityName())
							{
							entitySearchResultsVO.setCity(commonModuleAddressInfo.getAddress().getCityName());
							}
						}else{
							
							logger.error("Address is null"+ commonModuleAddressInfo.getReturnCode());
						}
						
					}
					
				} catch (AddressBusinessException e) {
					logger
							.error("<CommonLetterControllerBean> getAddressWithCommonSk() AddressBusinessException : "
									+ e.toString());
				} catch (Exception exc) {
					logger
							.error("<CommonLetterControllerBean> getAddressWithCommonSk() Exception : "
									+ exc.toString());
				}
                
            }
         }
     }
  }	
