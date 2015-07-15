/*
 * Created on Jan 5, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;

import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldDropDownValue;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldScope;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.DropDownVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author sivngan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomFieldDOConverter
{

    /** Creates an instance of the logger. * */
    private transient EnterpriseLogger log = EnterpriseLogFactory
            .getLogger(getClass().getName());

    /**
     * Default Constructor
     */
    public CustomFieldDOConverter()
    {
        super();
        
    }
    
    /*Below method is added for Defect ESPRD00806739 
    to update the userid column in G_AUD_LOG_TB table for Delete functionality */
    
    /**
     * This method will get userid from Security.
     * 
     * @return String
     */
    
    public String getLoggedInUserID() {
		String userId = null;
		Principal principal = null ;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		if(portletRequest != null){
			
		 principal= portletRequest.getUserPrincipal();
		}
		if (principal == null) {
			userId = ContactManagementConstants.GUEST_USERID;
		}
		else{
			userId = principal.getName();
		}
		
		return userId;
	}

    /**
     * This method is for converting the Custom Field VO to DO
     * 
     * @param customFieldVO
     *            CustomFieldVO
     * @param dropDownList
     *            dropDownList gets List of VOs
     * @return CustomField
     */
    public CustomField convertCustomFieldVOToDO(CustomFieldVO customFieldVO,
            List dropDownList)
    {
    	CustomField customFieldDO = new CustomField();
        CustomFieldScope customFieldScope;
        DropDownVO dropDownVO = null;
        Set scopeSet = new HashSet();
        Map scopeAssignmentMap = new HashMap();
        List ddValuesList = new ArrayList();

        

        customFieldDO.setCustomFieldSK(customFieldVO.getCustomFieldSK());
        customFieldDO.setDescription(customFieldVO.getColumnDescription());

        if (customFieldVO.getOldDataType() != null)
        {
            if (customFieldVO.getOldDataType().equalsIgnoreCase("DD"))
            {
                if (!customFieldVO.getOldDataType().equalsIgnoreCase(
                        customFieldVO.getDataType()))
                {
                    CustomFieldDropDownValue customFieldDropDownValue = null;

                    if (dropDownList.size() > 0)
                    {
                        for (int i = 0; dropDownList.size() > i; i++)
                        {
                            customFieldDropDownValue = new CustomFieldDropDownValue();
                            dropDownVO = (DropDownVO) dropDownList.get(i);

                            customFieldDropDownValue
                                    .setCustomFieldSK(customFieldVO
                                            .getCustomFieldSK());
                            customFieldDropDownValue
                                    .setCustomFieldPickLastValue(dropDownVO
                                            .getDropDownItemDesc());
                            customFieldDropDownValue
                                    .setDisplaySortOrderNumber(Integer
                                            .valueOf(dropDownVO.getSortOrder()));
                            customFieldDropDownValue.setVoidIndicator(Boolean
                                    .valueOf(customFieldVO.getActiveInd()));
                            customFieldDropDownValue.setVersionNo(dropDownVO
                                    .getVersionNo());
                            
                            /*Below code is modified for Defect ESPRD00806739 
                            to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                            
                           /* customFieldDropDownValue.setAuditUserID(dropDownVO
                                    .getAuditUserID());
                            customFieldDropDownValue
                                    .setAddedAuditUserID(dropDownVO
                                            .getAddedAuditUserID());*/
                            
                            customFieldDropDownValue.setAuditUserID(getLoggedInUserID());
                            customFieldDropDownValue.setAddedAuditUserID(getLoggedInUserID());
                            
                            customFieldDropDownValue
                                    .setAuditTimeStamp(dropDownVO
                                            .getAuditTimeStamp());
                            customFieldDropDownValue
                                    .setAddedAuditTimeStamp(dropDownVO
                                            .getAddedAuditTimeStamp());
                            customFieldDropDownValue
                                    .setCustomField(customFieldDO);

                            ddValuesList.add(customFieldDropDownValue);

                        }
                        scopeAssignmentMap.put("cfDropDownValueList",
                                ddValuesList);
                        dropDownList.clear();
                    }
                }
            }
        }
        customFieldDO.setDataTypeCode(customFieldVO.getDataType());
       
        if( customFieldVO.getLength().length() > 0)
        {
        customFieldDO.setTotalLengthInBytes(new Integer(customFieldVO
                .getLength().trim()));
        }
        if (customFieldVO.getActiveInd().equalsIgnoreCase("true"))
        {
            customFieldDO.setVoidIndicator(Boolean.valueOf(false));
        }
        else
        {
            customFieldDO.setVoidIndicator(Boolean.valueOf(true));
        }

        customFieldDO.setRequiredValueIndicator(Boolean.valueOf(customFieldVO
                .getRequiredInd().toString()));
        customFieldDO.setValueProtectedIndicator(Boolean.valueOf(customFieldVO
                .getProtectedInd().toString()));
        
        /*Below code is modified for Defect ESPRD00806739 
        to update the userid column in G_AUD_LOG_TB table for Delete functionality */
        
        /*customFieldDO.setAuditUserID(customFieldVO.getAuditUserID());
        customFieldDO.setAddedAuditUserID(customFieldVO.getAddedAuditUserID());*/
        
        customFieldDO.setAuditUserID(getLoggedInUserID());
        customFieldDO.setAddedAuditUserID(getLoggedInUserID());
        customFieldDO.setAuditTimeStamp(new Date());
        customFieldDO.setAddedAuditTimeStamp(new Date());
        customFieldDO.setVersionNo(customFieldVO.getVersionNo());

        //      scope
        if (customFieldVO.getOldScope() == null
                || customFieldVO.getOldScope().equalsIgnoreCase(""))
        {
            if (customFieldVO.getScope().equalsIgnoreCase("B"))
            {
                customFieldScope = new CustomFieldScope();
                /*
                 * Change done for ES2
                 * DO changes
                 */
                customFieldScope.setTableName("G_CR_TB");
                
                /*Below code is modified for Defect ESPRD00806739 
                to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                
               /* customFieldScope.setAuditUserID(customFieldVO.getAuditUserID());
                customFieldScope.setAddedAuditUserID(customFieldVO
                        .getAddedAuditUserID());*/
                
                customFieldScope.setAuditUserID(getLoggedInUserID());
                customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                customFieldScope.setAuditTimeStamp(new Date());
                customFieldScope.setAddedAuditTimeStamp(new Date());
                customFieldScope.setCustomField(customFieldDO);

                scopeSet.add(customFieldScope);
                customFieldScope = new CustomFieldScope();
                customFieldScope.setTableName("G_CASE_TB");
                
                /*Below code is modified for Defect ESPRD00806739 
                to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                
                /*customFieldScope.setAuditUserID(customFieldVO.getAuditUserID());
                customFieldScope.setAddedAuditUserID(customFieldVO
                        .getAddedAuditUserID());*/
                
                customFieldScope.setAuditUserID(getLoggedInUserID());
                customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                customFieldScope.setAuditTimeStamp(new Date());
                customFieldScope.setAddedAuditTimeStamp(new Date());
                customFieldScope.setCustomField(customFieldDO);

                scopeSet.add(customFieldScope);
                customFieldDO.setCustomFieldScopes(scopeSet);
            }
            else
            {
                customFieldScope = new CustomFieldScope();
                customFieldScope.setTableName(customFieldVO.getScope());
                
                /*Below code is modified for Defect ESPRD00806739 
                to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                
                /*customFieldScope.setAuditUserID(customFieldVO.getAuditUserID());
                customFieldScope.setAddedAuditUserID(customFieldVO
                        .getAddedAuditUserID());*/
                
                customFieldScope.setAuditUserID(getLoggedInUserID());
                customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                customFieldScope.setAuditTimeStamp(new Date());
                customFieldScope.setAddedAuditTimeStamp(new Date());
                customFieldScope.setCustomField(customFieldDO);

                scopeSet.add(customFieldScope);
                customFieldDO.setCustomFieldScopes(scopeSet);
            }
        }
        else
        {
            String oldScope = customFieldVO.getOldScope();
            String newScope = customFieldVO.getScope();
            if (!oldScope.equalsIgnoreCase(newScope))
            {
                if (oldScope.equalsIgnoreCase("B"))
                {
                    customFieldScope = new CustomFieldScope();
                    customFieldScope.setCustomFieldSK(customFieldVO
                            .getCustomFieldSK());
                    if (newScope.equalsIgnoreCase("G_CASE_TB"))
                    {
                        customFieldScope.setTableName("G_CR_TB");
                     }
                    else
                    {
                        customFieldScope.setTableName("G_CASE_TB");
                    }
                    
                    /*Below code is modified for Defect ESPRD00806739 
                    to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                    
                    /*customFieldScope.setAuditUserID(customFieldVO
                            .getAuditUserID());
                    customFieldScope.setAddedAuditUserID(customFieldVO
                            .getAddedAuditUserID());*/
                    
                    customFieldScope.setAuditUserID(getLoggedInUserID());
                    customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                    customFieldScope.setAuditTimeStamp(new Date());
                    customFieldScope.setAddedAuditTimeStamp(new Date());
                    customFieldScope.setVersionNo(customFieldVO.getVersionNo());
                    scopeAssignmentMap
                            .put("customFieldScope", customFieldScope);

                }
                else if (oldScope.equalsIgnoreCase("G_CASE_TB"))
                {
                    if (newScope.equalsIgnoreCase("B"))
                    {
                        customFieldScope = new CustomFieldScope();
                        customFieldScope.setCustomFieldSK(customFieldVO
                                .getCustomFieldSK());
                        customFieldScope.setTableName("G_CR_TB");
                        
                        /*Below code is modified for Defect ESPRD00806739 
                        to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                        
                        /*customFieldScope.setAuditUserID(customFieldVO
                                .getAuditUserID());
                        customFieldScope.setAddedAuditUserID(customFieldVO
                                .getAddedAuditUserID());*/
                        
                        customFieldScope.setAuditUserID(getLoggedInUserID());
                        customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                        customFieldScope.setAuditTimeStamp(new Date());
                        customFieldScope.setAddedAuditTimeStamp(new Date());
                        customFieldScope.setCustomField(customFieldDO);
                        scopeSet.add(customFieldScope);
                        customFieldDO.setCustomFieldScopes(scopeSet);
                    }
                    else
                    {
                        customFieldScope = new CustomFieldScope();
                        customFieldScope.setCustomFieldSK(customFieldVO
                                .getCustomFieldSK());
                        customFieldScope.setTableName("G_CASE_TB");
                        
                        /*Below code is modified for Defect ESPRD00806739 
                        to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                        
                        /*customFieldScope.setAuditUserID(customFieldVO
                                .getAuditUserID());
                        customFieldScope.setAddedAuditUserID(customFieldVO
                                .getAddedAuditUserID());*/
                        
                        customFieldScope.setAuditUserID(getLoggedInUserID());
                        customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                        customFieldScope.setAuditTimeStamp(new Date());
                        customFieldScope.setAddedAuditTimeStamp(new Date());
                        customFieldScope.setVersionNo(customFieldVO
                                .getVersionNo());
                        scopeAssignmentMap.put("customFieldScope",
                                customFieldScope);

                        customFieldScope = new CustomFieldScope();
                        customFieldScope.setCustomFieldSK(customFieldVO
                                .getCustomFieldSK());
                        customFieldScope.setTableName("G_CR_TB");
                        
                        /*Below code is modified for Defect ESPRD00806739 
                        to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                        
                        /*customFieldScope.setAuditUserID(customFieldVO
                                .getAuditUserID());
                        customFieldScope.setAddedAuditUserID(customFieldVO
                                .getAddedAuditUserID());*/
                        
                        customFieldScope.setAuditUserID(getLoggedInUserID());
                        customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                        customFieldScope.setAuditTimeStamp(new Date());
                        customFieldScope.setAddedAuditTimeStamp(new Date());
                        customFieldScope.setCustomField(customFieldDO);
                        scopeSet.add(customFieldScope);
                        customFieldDO.setCustomFieldScopes(scopeSet);
                    }

                }
                else
                {
                    if (newScope.equalsIgnoreCase("B"))
                    {
                        customFieldScope = new CustomFieldScope();
                        customFieldScope.setCustomFieldSK(customFieldVO
                                .getCustomFieldSK());
                        customFieldScope.setTableName("G_CASE_TB");
                        
                        /*Below code is modified for Defect ESPRD00806739 
                        to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                        
                        /*customFieldScope.setAuditUserID(customFieldVO
                                .getAuditUserID());
                        customFieldScope.setAddedAuditUserID(customFieldVO
                                .getAddedAuditUserID());*/
                        
                        customFieldScope.setAuditUserID(getLoggedInUserID());
                        customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                        customFieldScope.setAuditTimeStamp(new Date());
                        customFieldScope.setAddedAuditTimeStamp(new Date());
                        customFieldScope.setCustomField(customFieldDO);
                        scopeSet.add(customFieldScope);
                        customFieldDO.setCustomFieldScopes(scopeSet);
                    }
                    else
                    {
                        customFieldScope = new CustomFieldScope();
                        customFieldScope.setCustomFieldSK(customFieldVO
                                .getCustomFieldSK());
                        customFieldScope.setTableName("G_CR_TB");
                        
                        /*Below code is modified for Defect ESPRD00806739 
                        to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                        
                        /*customFieldScope.setAuditUserID(customFieldVO
                                .getAuditUserID());
                        customFieldScope.setAddedAuditUserID(customFieldVO
                                .getAddedAuditUserID());*/
                        
                        customFieldScope.setAuditUserID(getLoggedInUserID());
                        customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                        customFieldScope.setAuditTimeStamp(new Date());
                        customFieldScope.setAddedAuditTimeStamp(new Date());
                        customFieldScope.setVersionNo(customFieldVO
                                .getVersionNo());
                        scopeAssignmentMap.put("customFieldScope",
                                customFieldScope);

                        customFieldScope = new CustomFieldScope();
                        customFieldScope.setCustomFieldSK(customFieldVO
                                .getCustomFieldSK());
                        customFieldScope.setTableName("G_CASE_TB");
                        
                        /*Below code is modified for Defect ESPRD00806739 
                        to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                        
                        /*customFieldScope.setAuditUserID(customFieldVO
                                .getAuditUserID());
                        customFieldScope.setAddedAuditUserID(customFieldVO
                                .getAddedAuditUserID());*/
                        
                        customFieldScope.setAuditUserID(getLoggedInUserID());
                        customFieldScope.setAddedAuditUserID(getLoggedInUserID());
                        customFieldScope.setAuditTimeStamp(new Date());
                        customFieldScope.setAddedAuditTimeStamp(new Date());
                        customFieldScope.setCustomField(customFieldDO);
                        scopeSet.add(customFieldScope);
                        customFieldDO.setCustomFieldScopes(scopeSet);
                    }

                }

            }
            if (!customFieldVO.getOldDataType().equalsIgnoreCase(
                    customFieldVO.getDataType()))
            {
                if (!customFieldVO.getDataType().equalsIgnoreCase("DD"))
                {
                    if (dropDownList != null && dropDownList.size() != 0)
                    {
                        CustomFieldDropDownValue dropDownValueDO = null;
                        CustomFieldDropDownValue customFieldDropDownValue = null;
                        Set dropDownSet = new HashSet();
                        int dropDownListSize = dropDownList.size();

                        for (int i = 0; i < dropDownListSize; i++)
                        {
                            customFieldDropDownValue = new CustomFieldDropDownValue();
                            dropDownValueDO = new CustomFieldDropDownValue();
                            dropDownVO = (DropDownVO) dropDownList.get(i);

                            if (dropDownVO.getOldDropDownItemDesc() != null)
                            {
                                if (!dropDownVO.getOldDropDownItemDesc()
                                        .equalsIgnoreCase(
                                                dropDownVO
                                                        .getDropDownItemDesc()))
                                {
                                    customFieldDropDownValue
                                            .setCustomFieldSK(customFieldVO
                                                    .getCustomFieldSK());
                                    customFieldDropDownValue
                                            .setCustomFieldPickLastValue(dropDownVO
                                                    .getOldDropDownItemDesc());
                                    customFieldDropDownValue
                                            .setDisplaySortOrderNumber(Integer
                                                    .valueOf(dropDownVO
                                                            .getSortOrder()));
                                    customFieldDropDownValue
                                            .setVoidIndicator(Boolean
                                                    .valueOf(customFieldVO
                                                            .getActiveInd()));
                                    customFieldDropDownValue
                                            .setVersionNo(dropDownVO
                                                    .getVersionNo());
                                    
                                    /*Below code is modified for Defect ESPRD00806739 
                                    to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                                    
                                    /*customFieldDropDownValue
                                            .setAuditUserID(dropDownVO
                                                    .getAuditUserID());
                                    customFieldDropDownValue
                                            .setAddedAuditUserID(dropDownVO
                                                    .getAddedAuditUserID());*/
                                    
                                    customFieldDropDownValue
                                    		.setAuditUserID(getLoggedInUserID());
                                    customFieldDropDownValue
                                    		.setAddedAuditUserID(getLoggedInUserID());
                                    customFieldDropDownValue
                                            .setAuditTimeStamp(dropDownVO
                                                    .getAuditTimeStamp());
                                    customFieldDropDownValue
                                            .setAddedAuditTimeStamp(dropDownVO
                                                    .getAddedAuditTimeStamp());
                                    customFieldDropDownValue
                                            .setCustomField(customFieldDO);

                                    ddValuesList.add(customFieldDropDownValue);

                                    dropDownValueDO
                                            .setCustomField(customFieldDO);
                                    dropDownValueDO
                                            .setCustomFieldPickLastValue(dropDownVO
                                                    .getDropDownItemDesc());
                                    dropDownValueDO
                                            .setDisplaySortOrderNumber(Integer
                                                    .valueOf(dropDownVO
                                                            .getSortOrder()));
                                    dropDownValueDO
                                            .setVoidIndicator(customFieldDO
                                                    .getVoidIndicator());
                                    dropDownValueDO.setVersionNo(dropDownVO
                                            .getVersionNo() + 1);
                                    
                                    /*Below code is modified for Defect ESPRD00806739 
                                    to update the userid column in G_AUD_LOG_TB table for Delete functionality */
                                    
                                    /*dropDownValueDO
                                            .setAuditUserID(customFieldDO
                                                    .getAuditUserID());
                                    dropDownValueDO
                                            .setAddedAuditUserID(customFieldDO
                                                    .getAddedAuditUserID());*/
                                    
                                    dropDownValueDO
                                    		.setAuditUserID(getLoggedInUserID());
                                    dropDownValueDO
                                    		.setAddedAuditUserID(getLoggedInUserID());
                                    dropDownValueDO
                                            .setAuditTimeStamp(new Date());
                                    dropDownValueDO
                                            .setAddedAuditTimeStamp(dropDownVO.getAddedAuditTimeStamp());

                                    dropDownSet.add(dropDownValueDO);
                                }
                            }
                            else
                            {
                                dropDownValueDO.setCustomField(customFieldDO);
                                dropDownValueDO
                                        .setCustomFieldPickLastValue(dropDownVO
                                                .getDropDownItemDesc());
                                dropDownValueDO
                                        .setDisplaySortOrderNumber(Integer
                                                .valueOf(dropDownVO
                                                        .getSortOrder()));
                                dropDownValueDO.setVoidIndicator(customFieldDO
                                        .getVoidIndicator());
                                
                                /*Below code is modified for Defect ESPRD00806739 
                                to update the userid column in G_AUD_LOG_TB table for Delete functionality */

                                /*dropDownValueDO.setAuditUserID(customFieldDO
                                        .getAuditUserID());
                                dropDownValueDO
                                        .setAddedAuditUserID(customFieldDO
                                                .getAddedAuditUserID());*/
                                
                                dropDownValueDO.setAuditUserID(getLoggedInUserID());
                                dropDownValueDO
                                        .setAddedAuditUserID(getLoggedInUserID());
                                dropDownValueDO.setAuditTimeStamp(new Date());
                                dropDownValueDO
                                        .setAddedAuditTimeStamp(dropDownVO.getAddedAuditTimeStamp());
                                dropDownValueDO.setVersionNo(dropDownVO
                                        .getVersionNo());

                                dropDownSet.add(dropDownValueDO);
                            }
                        }
                        scopeAssignmentMap.put("cfDropDownValueList",
                                ddValuesList);
                        customFieldDO.setCustomFieldDropDownValues(dropDownSet);
                        dropDownList.clear();
                    }
                }
            }

        }
        customFieldDO.setDeletedObjects(scopeAssignmentMap);

        if (dropDownList != null && dropDownList.size() != 0)
        {
            customFieldDO
                    .setCustomFieldDropDownValues(convertDropDownValuesVOToDO(
                            customFieldDO, dropDownList));

        }

        
        return customFieldDO;
    }

    /**
     * This method is for converting the Custom Field VO to DO
     * 
     * @param customFieldDO
     *            customFieldDO
     * @param dropDownList
     *            dropDownList gets List of VOs
     * @return Set holds set of DropDownDOs
     */
    private Set convertDropDownValuesVOToDO(CustomField customFieldDO,
            List dropDownList)
    {
    	CustomFieldDropDownValue dropDownValueDO = null;
        DropDownVO dropDownVO = null;
        Set dropDownSet = new HashSet();
        int dropDownListSize = dropDownList.size();

        for (int i = 0; i < dropDownListSize; i++)
        {
            dropDownValueDO = new CustomFieldDropDownValue();
            dropDownVO = (DropDownVO) dropDownList.get(i);

            dropDownValueDO.setCustomField(customFieldDO);
            dropDownValueDO.setCustomFieldPickLastValue(dropDownVO
                    .getDropDownItemDesc());
            dropDownValueDO.setDisplaySortOrderNumber(Integer
                    .valueOf(dropDownVO.getSortOrder()));
            dropDownValueDO.setVoidIndicator(customFieldDO.getVoidIndicator());
            
            /*Below code is modified for Defect ESPRD00806739 
            to update the userid column in G_AUD_LOG_TB table for Delete functionality */
            
            /*dropDownValueDO.setAuditUserID(customFieldDO.getAuditUserID());
            dropDownValueDO.setAddedAuditUserID(customFieldDO
                    .getAddedAuditUserID());*/
            
            dropDownValueDO.setAuditUserID(getLoggedInUserID());
            dropDownValueDO.setAddedAuditUserID(getLoggedInUserID());
            dropDownValueDO.setAuditTimeStamp(new Date());
            dropDownValueDO.setAddedAuditTimeStamp(new Date());
            dropDownValueDO.setVersionNo(dropDownVO.getVersionNo());

            dropDownSet.add(dropDownValueDO);

        }
        
        return dropDownSet;
    }

}
