/*
 * Created on Jan 7, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditDataFilter;
import com.acs.enterprise.common.lineitemaudit.common.helper.AuditableField;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldDropDownValue;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldScope;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CustomFieldDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.DropDownVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author sivngan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomFiedDOtoVOConverter
{

    /** Enterprise Logger for Logging */
    private transient EnterpriseLogger log = EnterpriseLogFactory
            .getLogger(getClass().getName());

    /**
     * Default Constructor
     */
    public CustomFiedDOtoVOConverter()
    {
        super();
        
    }

    /**
     * This method converts CustomField DO to VO
     * 
     * @param customFieldSet
     *            holds the set of CustomField
     * @return List it returns List of VOs
     */

    public List convertCustomFieldDOToVO(Set customFieldSet)
    {
       
    	List customFieldVOList = new ArrayList();
        CustomField customFieldDO;
        CustomFieldVO customFieldVO = null;
        Iterator iterator = customFieldSet.iterator();

        while (iterator.hasNext())
        {
            customFieldDO = (CustomField) iterator.next();
            customFieldVO = convertCustomFieldDOToVO(customFieldDO);
            customFieldVOList.add(customFieldVO);
        }

        return customFieldVOList;
    }

    /**
     * This method converts CustomField DO to VO
     * 
     * @param customFieldDO
     *            holds the object of CustomField
     * @return CustomFieldVO it returns the VO
     */
    public CustomFieldVO convertCustomFieldDOToVO(CustomField customFieldDO)
    {
    	
        Set dropDownSet = null;
        CustomFieldVO customFieldVO = new CustomFieldVO();
        CustomFieldDataBean customFieldDataBean=getCustomFieldDataBean();
        customFieldVO.setCustomFieldSK(customFieldDO.getCustomFieldSK());
        customFieldVO.setColumnDescription(customFieldDO.getDescription());
        customFieldVO.setDataType(customFieldDO.getDataTypeCode());
        customFieldVO.setDataTypestr(getDescriptionFromVV(customFieldDO.getDataTypeCode(),customFieldDataBean.getDataTypeList()));
        if (customFieldDO.getTotalLengthInBytes() != null)
        {
            customFieldVO.setLength(customFieldDO.getTotalLengthInBytes()
                    .toString());
        }
        
        if(customFieldDO.getVoidIndicator().toString().equalsIgnoreCase("true"))
        {
        	customFieldVO.setActiveInd("false");
            customFieldVO.setActiveFlag("No");
        }else
        {
        	customFieldVO.setActiveInd("true");
            customFieldVO.setActiveFlag("Yes");
        }
        if(customFieldDO.getRequiredValueIndicator().toString().equalsIgnoreCase("true"))
		{
        	customFieldVO.setRequiredInd("true");
        	customFieldVO.setRequiredFlag("Yes");
		}
		else
		{
			customFieldVO.setRequiredInd("false");
			customFieldVO.setRequiredFlag("No");
		}
		if(customFieldDO.getValueProtectedIndicator().toString().equalsIgnoreCase("true"))
		{
			customFieldVO.setProtectedInd("true");
			customFieldVO.setProtectedFlag("Yes");
		}
		else
		{
			customFieldVO.setProtectedInd("false");
			customFieldVO.setProtectedFlag("No");
		}
        
        /*if(customFieldDO.getVoidIndicator().toString().equalsIgnoreCase("true"))
        {
            customFieldVO.setActiveInd("false");
        }else
        {
            customFieldVO.setActiveInd("true");
        }
        
       customFieldVO.setRequiredInd(customFieldDO.getRequiredValueIndicator()
                .toString());
        customFieldVO.setProtectedInd(customFieldDO
                .getValueProtectedIndicator().toString());*/

        //scope field to be set
		 CustomFieldScope customFieldScope = null;
        if (customFieldDO.getCustomFieldScopes().size() > 1)
        {
            customFieldVO.setScopeValue("BOTH");
            customFieldVO.setScope("B");
        }
        else
        {
            Set scope = customFieldDO.getCustomFieldScopes();
            Iterator scopeItr = scope.iterator();
            while (scopeItr.hasNext())
            {
                customFieldScope = (CustomFieldScope) scopeItr.next();
                /*
                 * Change done for ES2 DO changes
                 */	
                customFieldVO
                        .setScope(customFieldScope.getTableName());
                if (customFieldScope.getTableName().equalsIgnoreCase(
                        "G_CASE_TB"))
                {
                    customFieldVO.setScopeValue("CASE");
                }
                else if (customFieldScope.getTableName()
                        .equalsIgnoreCase("G_CR_TB"))
                {
                    customFieldVO.setScopeValue("CORRESPONDENCE");
                }
                createVOAuditKeysList(customFieldScope, customFieldVO);
            }

        }

        dropDownSet = customFieldDO.getCustomFieldDropDownValues();
        List dropDownList = convertDropDownDOToVO(dropDownSet);
        
        customFieldVO.setDropDownList(dropDownList);
        customFieldVO.setVersionNo(customFieldDO.getVersionNo());
        customFieldVO.setAddedAuditTimeStamp(customFieldDO
                .getAddedAuditTimeStamp());
        customFieldVO.setAddedAuditUserID(customFieldDO.getAddedAuditUserID());
        customFieldVO.setAuditTimeStamp(customFieldDO.getAuditTimeStamp());
        customFieldVO.setAuditUserID(customFieldDO.getAuditUserID());
       
        createVOAuditKeysList(customFieldDO, customFieldVO);
       	
        
        return customFieldVO;
    }

    /**
     * This method converts CustomField DO to VO
     * 
     * @param dropDownSet
     *            holds the set of DropDownSet
     * @return List it returns list of VOs
     */
    public List convertDropDownDOToVO(Set dropDownSet)
    {
        List dropDownList = new ArrayList();
        CustomFieldDropDownValue dropDownDO = null;
        DropDownVO dropDownVO = null;
        Iterator dropDownItr = dropDownSet.iterator();
        while (dropDownItr.hasNext())
        {
            dropDownDO = (CustomFieldDropDownValue) dropDownItr.next();
            dropDownVO = new DropDownVO();
            dropDownVO.setDropDownItemDesc(dropDownDO
                    .getCustomFieldPickLastValue());
            dropDownVO.setSortOrder(String.valueOf(dropDownDO
                    .getDisplaySortOrderNumber()));

            dropDownVO.setVersionNo(dropDownDO.getVersionNo());

            dropDownVO.setAddedAuditTimeStamp(dropDownDO
                    .getAddedAuditTimeStamp());
            dropDownVO.setAddedAuditUserID(dropDownDO.getAddedAuditUserID());
            dropDownVO.setAuditTimeStamp(dropDownDO.getAuditTimeStamp());
            dropDownVO.setAuditUserID(dropDownDO.getAuditUserID());
            dropDownList.add(dropDownVO);
           
        	createVOAuditKeysList(dropDownDO, dropDownVO);
            
        }
        return dropDownList;
    }
    /**
	 * This method is used to get the CustomField Data Bean.
	 * 
	 * @return CustomFieldDataBean : CustomFieldDataBean object.
	 */
	public CustomFieldDataBean getCustomFieldDataBean() {
		
		FacesContext fc = FacesContext.getCurrentInstance();

		CustomFieldDataBean customFieldDataBean = (CustomFieldDataBean) fc
				.getApplication()
				.createValueBinding(
						ContactManagementConstants.BINDING_BEGIN_SEPARATOR
								+ MaintainContactManagementUIConstants.CUSTOMFIELD_BEAN
								+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc);

		return customFieldDataBean;
	}

    /**
     * To get the description based on code.
     * 
     * @param code
     *            Holds the code valus.
     * @param vvList
     *            Holds the List of valid values.
     * @return String
     */
    private String getDescriptionFromVV(String code, List vvList)
    {
        String desc = ProgramConstants.EMPTY_STRING;
        String valueStr = ProgramConstants.EMPTY_STRING;
        int size = vvList.size();
        if (vvList != null && size > 0)
        {
            for (int i = 0; i < size; i++)
            {
                SelectItem selectItem = (SelectItem) vvList.get(i);
                valueStr = ProgramConstants.EMPTY_STRING;
                if (selectItem != null)
                {
                    valueStr = (String) selectItem.getValue();
                    if (valueStr != null && valueStr.equals(code))
                    {
                        desc = selectItem.getLabel();
                        break;
                    }
                }
            }
        }
        return desc;
    }

    private void createVOAuditKeysList(EnterpriseBaseDomain enterpriseBaseDomain,AuditaleEnterpriseBaseVO  auditaleEnterpriseBaseVO)
    {
    	
    	  List fullAuditList = new ArrayList();
    	  LineItemAuditsDelegate auditDelegate;
    	  if(auditaleEnterpriseBaseVO.getAuditKeyList()!= null)
    	  {
    	  	fullAuditList = auditaleEnterpriseBaseVO.getAuditKeyList();
    	  }
          
    	  // Added for defect - ESPRD00785040
    	try 
		{
    		auditDelegate = new LineItemAuditsDelegate();
    		List resultList = auditDelegate.getAuditKeyList(enterpriseBaseDomain);
           
    	       if(resultList!=null){
    	       	fullAuditList.addAll(resultList);
    	        List cfEditList = new ArrayList();

    			cfEditList.add(createAuditableFeild("Column Description","description"));
    			cfEditList.add(createAuditableFeild("Data Type","dataTypeCode"));
    			cfEditList.add(createAuditableFeild("Length","totalLengthInBytes"));
    			cfEditList.add(createAuditableFeild("Scope", "tableName"));
    			cfEditList.add(createAuditableFeild("Active","voidIndicator"));
    			cfEditList.add(createAuditableFeild("Required","requiredValueIndicator"));
    			cfEditList.add(createAuditableFeild("Protected","valueProtectedIndicator"));
    	       	
    	       	auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);
    	       	
    	       	AuditDataFilter.filterAuditKeys(cfEditList,auditaleEnterpriseBaseVO);
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
    
 private AuditableField createAuditableFeild(String feildName,String domainAttributeName){
		
		AuditableField auditableField = new AuditableField();
		auditableField.setFieldName(feildName);
		auditableField.setDomainAttributeName(domainAttributeName);
			
		return auditableField;
    }
}
