/*
 * Created on Oct 22, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
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
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntityCallScript;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntityType;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CallScript;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CallScriptCex;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.SubjectAuxillary;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CallScriptControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CallScriptCategoryVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CallScriptEntityVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CallScriptSubjectVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CallScriptVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CategoryVO;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class CallScriptDOConvertor
        extends DataTransferObjectConverter
{

    /** Enterprise Logger for Logging. */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CallScriptDOConvertor.class);

    /**
     * Creates a consructor of the CallScriptDOConvertor.
     */
    public CallScriptDOConvertor()
    {

        super();
        
    }

    /**
     * This method returns List of callScriptCategoryVO.
     * 
     * @param categoryDOList
     *            Takes List of categoryDo's as param.
     * @return List
     */
    public List getAllCallScriptCatVOList(List categoryDOList)
    {
        List categoryVOList = new ArrayList();
        //FindBug Issue Resolved
        //CorrespondenceCategory category = new CorrespondenceCategory();
        CorrespondenceCategory category =null;
        if (categoryDOList != null)
        {
            int categoryDOListSize = categoryDOList.size();

            /** Iterates Through the List of Do's */

            for (int i = 0; i < categoryDOListSize; i++)
            {
                /** Creates a Reference of CallScriptCategoryVO */
                CallScriptCategoryVO callScriptCategoryVO = new CallScriptCategoryVO();

                category = (CorrespondenceCategory) categoryDOList.get(i);
                callScriptCategoryVO.setRowcatIndexCount(i);
                CategoryVO categoryVO = convertCategoryDOToVO(category);

                callScriptCategoryVO.setCategoryVO(categoryVO);
                if (category.getCallScript() != null)
                {

                    callScriptCategoryVO.setCallScriptSK(category
                            .getCallScript().getCallScriptSK());

                    callScriptCategoryVO.setAssignedCallScript(category
                            .getCallScript().getDescription());
                    callScriptCategoryVO
                            .setStatus(ContactManagementConstants.STATUS_NONE);
                    callScriptCategoryVO.setVersionNo(category.getVersionNo());

                }

                categoryVOList.add(callScriptCategoryVO);

            }
        }

        return categoryVOList;

    }
   
    /**
     * This method returns List of all entity Vo's.
     * 
     * @param entityDOList
     *            Takes List of entityDo's as param.
     * @return List
     */
    public List getAllEntityVOList(List entityDOList)
    {
        List entityVOList = new ArrayList();
        //FindBug Issue Resolved
       // int entityDOListSize = entityDOList.size();
        /** Iterates Through the List of Do's */
        if (entityDOList != null)
        {
        //FindBug Issue Resolved
           // for (int i = 0; i < entityDOListSize; i++)
        	for (int i = 0; i < entityDOList.size(); i++)
            {
                /** Creates a Reference of CallScriptCategoryVO */
                CallScriptEntityVO callScriptEntityVO = new CallScriptEntityVO();

                CommonEntityType commonEntityType = (CommonEntityType) entityDOList
                        .get(i);

                if (commonEntityType.getCommonEntityTypeCode() != null)
                {
                    callScriptEntityVO.setEntityTypeCode(commonEntityType
                            .getCommonEntityTypeCode());
                }

                if (commonEntityType.getDescription() != null)
                {
                    callScriptEntityVO.setEntityDescription(commonEntityType
                            .getDescription());
                }
                callScriptEntityVO
                        .setVersionNo(commonEntityType.getVersionNo());

                entityVOList.add(callScriptEntityVO);
            }
        }

        return entityVOList;

    }

    /**
     * This method returns the list of entity from Valid values . And adds the
     * Ent Desc to CallscriptVO.
     * 
     * @return List
     */
    public List getAllEntityVVList()
    {
        List entityVVList = new ArrayList();

        /** Get the Entiy Type Valid values */
        List referenceList = new ArrayList();
        Map referenceMap = null;

        int referenceListSize = 0;
        boolean voidInd = false;

        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        //FindBug Issue Resolved
        //ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
        ReferenceDataListVO referenceDataListVO =null;
        InputCriteria inputCriteriaEntityTyp = new InputCriteria();
        inputCriteriaEntityTyp
                .setFunctionalArea(FunctionalAreaConstants.CONTACT_MGMT);
        inputCriteriaEntityTyp
                .setElementName(ReferenceServiceDataConstants.G_CE_TY_OR_SE_TY_CD);

        referenceList.add(inputCriteriaEntityTyp);

        referenceDataSearch.setInputList(referenceList);

        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);

            referenceMap = referenceDataListVO.getResponseMap();

            referenceList = (List) referenceMap
                    .get(FunctionalAreaConstants.CONTACT_MGMT
                            + ProgramConstants.HASH_SEPARATOR
                            + ReferenceServiceDataConstants.G_CE_TY_OR_SE_TY_CD);
            referenceListSize = referenceList.size();
            if(logger.isDebugEnabled())
            {
            	logger.debug("referenceListSize--->" + referenceListSize);
            }
            for (int i = 0; i < referenceListSize; i++)
            {
                CallScriptEntityVO callScriptEntityVO = new CallScriptEntityVO();
                ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                        .get(i);
               
                    /** Add the Code and desc to the Vo */
                    callScriptEntityVO.setEntityTypeCode(refVo
                            .getValidValueCode());
                    callScriptEntityVO.setEntityDescription(refVo
                            .getLongDescription());
                    entityVVList.add(callScriptEntityVO);
            }
            if(logger.isDebugEnabled())
            {
            	logger.debug(" Inside helper  size of entity valid values -----> " + entityVVList.size());
            }
        }
        catch (ReferenceServiceRequestException e)
        {
        	if(logger.isErrorEnabled())
        	{
        		logger.error(e.getMessage(), e);
        	}
        }
        catch (SystemListNotFoundException e)
        {
        	if(logger.isErrorEnabled())
        	{
        		logger.error(e.getMessage(), e);
        	}
        }
        return entityVVList;

    }

    /**
     * This method returns List of all CallScript entity Vo's.
     * 
     * @param callScriptEntityDOList
     *            Takes List of callScriptEntityDOList's as param.
     * @return List
     */
    public List getAllCallScriptEntityVOList(List callScriptEntityDOList)
    {

        
        List callScriptEntityVOList = new ArrayList();
       //FindBug Issue Resolved
        //int callScriptEntityDOListSize = callScriptEntityDOList.size();
     
        /** Iterates Through the List of Do's */
        if (callScriptEntityDOList != null && !callScriptEntityDOList.isEmpty())
        {
        	//FindBug Issue Resolved
            //for (int i = 0; i < callScriptEntityDOListSize; i++)
            for (int i = 0; i < callScriptEntityDOList.size(); i++)
            {
                /** Creates a Reference of CallScriptCategoryVO */
            	//FindBug Issue Resolved
                //CallScriptEntityVO callScriptEntityVO = new CallScriptEntityVO();
            	CallScriptEntityVO callScriptEntityVO =null;
                CommonEntityCallScript commonEntityCallScript = (CommonEntityCallScript) callScriptEntityDOList
                        .get(i);

                callScriptEntityVO = convertCallScriptEntityDOtoVO(commonEntityCallScript);

                callScriptEntityVOList.add(callScriptEntityVO);
            }
        }

        return callScriptEntityVOList;

    }

    /**
     * This method returns List of all CallScriptSubject Vo's.
     * 
     * @param subjectDOList
     *            Takes List of SubjectDO's as param.
     * @return List
     */
    public List getAllCallScriptSubVOList(List subjectDOList)
    {
        List subjectVOList = new ArrayList();
        //FindBug Issue Resolved
        //int subjectDOListSize = subjectDOList.size();
        /** Iterates Through the List of Do's */
        if (subjectDOList != null)
        {
        	 //FindBug Issue Resolved
            //for (int i = 0; i < subjectDOListSize; i++)
        	for (int i = 0; i < subjectDOList.size(); i++)
            {
                /** Creates a Reference of CallScriptCategoryVO */
                CallScriptSubjectVO callScriptSubjectVO = new CallScriptSubjectVO();

                SubjectAuxillary subject = (SubjectAuxillary) subjectDOList
                        .get(i);

                if (subject.getSubjectCode() != null)
                {
                    callScriptSubjectVO
                            .setSubjectCode(subject.getSubjectCode());
                }

                if (subject.getCallScript() != null)
                {

                    callScriptSubjectVO.setCallScriptSK(subject.getCallScript()
                            .getCallScriptSK());

                    callScriptSubjectVO.setAssignedCallScript(subject
                            .getCallScript().getDescription());
                }

                subjectVOList.add(callScriptSubjectVO);
            }
        }

        return subjectVOList;

    }

    /**
     * Converts the CallScript DO to VO.
     * 
     * @param callScriptDO
     *            Holds the callscriptDO.
     * @return Returns the Callscript VO.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public CallScriptVO convertCallScriptDOToVO(CallScript callScriptDO)
    {
        CallScriptVO callScriptVO = new CallScriptVO();

        /** Sets Audit Info in to the Main VO */
        callScriptVO.setAddedAuditTimeStamp(callScriptDO
                .getAddedAuditTimeStamp());
        callScriptVO.setAddedAuditUserID(callScriptDO.getAddedAuditUserID());

        callScriptVO.setAuditUserID(callScriptDO.getAuditUserID());

        callScriptVO.setAuditTimeStamp(callScriptDO.getAuditTimeStamp());
        
        callScriptVO.setVersionNo(callScriptDO.getVersionNo());

        /** Sets the Main Vo Values */
        if (callScriptDO.getStatusCode() != null
                && callScriptDO.getStatusCode().equalsIgnoreCase(
                        ContactManagementConstants.Y))
        {
            callScriptVO.setCallScriptStatus(ContactManagementConstants.YES);
        }
        else
        {
            callScriptVO.setCallScriptStatus(ContactManagementConstants.NO);
       
            callScriptVO.setInactive(true);
            
        }
        
        if (callScriptDO.getCallScriptSK() != null
                && callScriptDO.getCallScriptSK().toString().length() > 0)
        {
            callScriptVO.setCallScriptSK(callScriptDO.getCallScriptSK());
        }
        if (callScriptDO.getDescription() != null
                && callScriptDO.getDescription().toString().length() > 0)
        {
            callScriptVO.setDescription(callScriptDO.getDescription());
        }

        if (callScriptDO.getCex() != null && callScriptDO.getCex().getDescriptionClob() != null
                && callScriptDO.getCex().getDescriptionClob().length() > 0)
        {
            callScriptVO.setCallScriptFullText(callScriptDO.getCex().getDescriptionClob());
            if(logger.isInfoEnabled())
            {
            	logger.info("FullTextValue"+callScriptDO.getCex().getCallScriptFullTextValue());
            }
            callScriptVO.setCallScriptCexSk(callScriptDO.getCex().getCallScriptFullTextValue());
            callScriptVO.setCallScriptCexVersionNo(callScriptDO.getCex().getVersionNo());
        }
        /** Populates the Assigned Category List from Do to VO */

        if (callScriptDO.getCategories() != null
                && callScriptDO.getCategories().size() > 0)
        {

            List categoryVOList = new ArrayList();
            /** Iterates through the set of Assigined Category DO */
            Iterator itr = callScriptDO.getCategories().iterator();
            while (itr.hasNext())
            {
                CategoryVO categoryVO = new CategoryVO();
                CallScriptCategoryVO callScriptCategoryVO = new CallScriptCategoryVO();
                CorrespondenceCategory category = (CorrespondenceCategory) itr
                        .next();

                /** Calss Method to convert Category Do to Category VO */
                categoryVO = convertCategoryDOToVO(category);

                callScriptCategoryVO.setCategoryVO(categoryVO);
               
                callScriptCategoryVO
                        .setStatus(ContactManagementConstants.STATUS_ASSIGNED);
                
                categoryVOList.add(callScriptCategoryVO);
            }

            /** Add the Temporay Category Vo Set to callscript VoList */
            callScriptVO.setCategory(categoryVOList);

        }

        /** Populates the Entity List from Do to VO */

        if (callScriptDO.getCommonEntityCallScripts() != null
                && callScriptDO.getCommonEntityCallScripts().size() > 0)
        {

            Set entityVOSet = new HashSet(
                    ContactManagementConstants.DEFAULT_SIZE);
            /** Iterates through the set of Assigined CommonEntityCallScript DO */
            Iterator itr = callScriptDO.getCommonEntityCallScripts().iterator();
            while (itr.hasNext())
            {
                CallScriptEntityVO callScriptEntityVO = new CallScriptEntityVO();
                CommonEntityCallScript commonEntityCallScript = (CommonEntityCallScript) itr
                        .next();
                /**
                 * Calss Method to convert CommonEntityCallScript Do to
                 * CallScriptEntityVO VO
                 */
                callScriptEntityVO = convertCallScriptEntityDOtoVO(commonEntityCallScript);
                callScriptEntityVO
                        .setStatus(ContactManagementConstants.STATUS_ASSIGNED);
                entityVOSet.add(callScriptEntityVO);
            }

            /** Add the Temporay Category Vo Set to callscript VoList */
            callScriptVO.setEntType(new ArrayList(entityVOSet));

        }

        /** Populates the Subjects List from Do to VO */

        if (callScriptDO.getSubjects() != null
                && callScriptDO.getSubjects().size() > 0)
        {

            Set subjectVOSet = new HashSet(
                    ContactManagementConstants.DEFAULT_SIZE);
            /** Iterates through the set of Assigined CommonEntityCallScript DO */
            Iterator itr = callScriptDO.getSubjects().iterator();
            while (itr.hasNext())
            {
                CallScriptSubjectVO callScriptSubjectVO = new CallScriptSubjectVO();
                SubjectAuxillary subjectAuxillary = (SubjectAuxillary) itr
                        .next();
                /**
                 * Calss Method to convert SubjectAuxillary Do to
                 * CallScriptSubjectVO VO
                 */
                callScriptSubjectVO = convertCallScriptSubjectDOtoVO(subjectAuxillary);
                callScriptSubjectVO
                        .setStatus(ContactManagementConstants.STATUS_ASSIGNED);
                subjectVOSet.add(callScriptSubjectVO);
            }
            
            //Code commented for the defect ID : ESPRD00785090
            //createVOAuditKeysList(callScriptDO,callScriptVO);
                   

            /** Add the Temporay Category Vo Set to callscript VoList */
            callScriptVO.setSubject(new ArrayList(subjectVOSet));

        }
        //Code added for the defect ID : ESPRD00785090 to display Audit details for Call Script
        createVOAuditKeysList(callScriptDO,callScriptVO);

        return callScriptVO;
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

    /**
     * This Method Converts Entity Do to Category VO.
     * 
     * @param commonEntityCallScript
     *            Takes CommonEntityCallScript DO as Param
     * @return CallScriptEntityVO
     */
    public CallScriptEntityVO convertCallScriptEntityDOtoVO(
            CommonEntityCallScript commonEntityCallScript)
    {
        CallScriptEntityVO callScriptEntityVO = new CallScriptEntityVO();
        /** Converts Audit Part */
        callScriptEntityVO.setAddedAuditTimeStamp(commonEntityCallScript
                .getAddedAuditTimeStamp());
        callScriptEntityVO.setAddedAuditUserID(commonEntityCallScript
                .getAddedAuditUserID());
        callScriptEntityVO.setAuditTimeStamp(commonEntityCallScript
                .getAuditTimeStamp());
        callScriptEntityVO.setAuditUserID(commonEntityCallScript
                .getAuditUserID());
        callScriptEntityVO.setVersionNo(commonEntityCallScript.getVersionNo());

        /** Populate Each Field From DO TO VO */

        /* Sets EntityCode and Entiy Desc */
        /*
         * Change done for ES2 DO changes
         */
        if (commonEntityCallScript.getCommonEntityTypeCode() != null)
        {

            callScriptEntityVO.setEntityTypeCode(commonEntityCallScript
                    .getCommonEntityTypeCode());
        }
        /** Sets CallScript Desc */
        if (commonEntityCallScript.getCallScript() != null)
        {
            callScriptEntityVO.setAssignedCallScript(commonEntityCallScript
                    .getCallScriptObj().getDescription());

        }

        return callScriptEntityVO;
    }

    /**
     * This Method Converts Subject Do to Category VO.
     * 
     * @param subjectAuxillary
     *            Takes SubjectAuxillary DO as Param
     * @return CallScriptSubjectVO
     */
    public CallScriptSubjectVO convertCallScriptSubjectDOtoVO(
            SubjectAuxillary subjectAuxillary)
    {
       
        CallScriptSubjectVO callScriptSubjectVO = new CallScriptSubjectVO();
        /** Converts Audit Part */
        callScriptSubjectVO.setAddedAuditTimeStamp(subjectAuxillary
                .getAddedAuditTimeStamp());
        callScriptSubjectVO.setAddedAuditUserID(subjectAuxillary
                .getAddedAuditUserID());
        callScriptSubjectVO.setAuditTimeStamp(subjectAuxillary
                .getAuditTimeStamp());
        callScriptSubjectVO.setAuditUserID(subjectAuxillary.getAuditUserID());
        callScriptSubjectVO.setVersionNo(subjectAuxillary.getVersionNo());

        /** Populate Each Field From DO TO VO */

        /* Sets SubjectCode */
        if (subjectAuxillary.getSubjectCode() != null
                && subjectAuxillary.getSubjectCode().toString().length() > 0)
        {
            callScriptSubjectVO.setSubjectCode(subjectAuxillary
                    .getSubjectCode());
        }
        /* Sets CallScript Description */
        if (subjectAuxillary.getCallScript() != null)
        {
            callScriptSubjectVO.setAssignedCallScript(subjectAuxillary
                    .getCallScript().getDescription());
            callScriptSubjectVO.setCallScriptSK(subjectAuxillary
                    .getCallScript().getCallScriptSK());
        }

        return callScriptSubjectVO;
    }

    /**
     * Converts the CallScript VO to DO.
     * 
     * @param callScriptVO
     *            Holds the callScriptVO which it needs to be converted to DO.
     * @return Returns the call script Object.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public CallScript convertCallScriptVOToDO(CallScriptVO callScriptVO)
    {

        CallScript callScript = new CallScript();
        /** Audit Information */
        /* callScript.setAddedAuditTimeStamp(getTimeStamp()); */
        callScript.setAddedAuditUserID(getLoggedInUserID());
        /* callScript.setAuditTimeStamp(getTimeStamp()); */
        callScript.setAuditUserID(getLoggedInUserID());
        callScript.setVersionNo(callScriptVO.getVersionNo());

        /** Sets Call script Status from VO to DO */
        if (callScriptVO.getCallScriptStatus() != null
                && callScriptVO.getCallScriptStatus().length() > 0)
        {
            if (callScriptVO.getCallScriptStatus().equalsIgnoreCase(
                    ContactManagementConstants.YES))
            {
                callScript.setStatusCode(ContactManagementConstants.Y);
            }
            else
            {
                callScript.setStatusCode(ContactManagementConstants.N);
                callScriptVO.setInactive(true);
            }
        }
        /** Sets call Script Sk from DO to VO */
        if (callScriptVO.getCallScriptSK() != null
                && callScriptVO.getCallScriptSK().toString().length() > 0)
        {
            callScript.setCallScriptSK(callScriptVO.getCallScriptSK());
        }

        /** Sets call Script Description from DO to VO */
        if (callScriptVO.getDescription() != null
                && callScriptVO.getDescription().toString().length() > 0)
        {
            callScript.setDescription(callScriptVO.getDescription());
        }

        /** Sets Call Script Full Text from DO to VO */
        if (callScriptVO.getCallScriptFullText() != null
                && callScriptVO.getCallScriptFullText().toString().length() > 0)
        {
        	if(logger.isInfoEnabled())
        	{
        		logger.info("callScriptVO.getCallScriptCexSk("+callScriptVO.getCallScriptCexSk());
        	}
        	if(callScript.getCex() == null) {
        		CallScriptCex callScriptCex = new CallScriptCex();
        		callScriptCex.setAddedAuditTimeStamp(getTimeStamp());
        		callScriptCex.setAddedAuditUserID(getLoggedInUserID());
        		callScriptCex.setAuditTimeStamp(getTimeStamp());
        		callScriptCex.setAuditUserID(getLoggedInUserID());
        		callScriptCex.setCallScriptFullTextValue(callScriptVO.getCallScriptCexSk());
        		callScriptCex.setVersionNo(callScriptVO.getCallScriptCexVersionNo());
        		callScript.setCex(callScriptCex);
        	}
            callScript.getCex().setDescriptionClob(callScriptVO.getCallScriptFullText());
        }

        /** Sets the List of Assigned Category from VO to DO */
        if (callScriptVO.getCategory() != null
                && callScriptVO.getCategory().size() > 0)
        {
            Set callScriptCategoryDoSet = new HashSet(
                    ContactManagementConstants.DEFAULT_SIZE);
            /** Calls a method which converts categoryVo to category do */
            callScriptCategoryDoSet = convertCategoryVOListTODO(callScriptVO
                    .getCategory(), callScript);
            logger.debug("Inside helper size of cat set assined to callscript "
                    + callScriptCategoryDoSet.size());
            callScript.setCategories(callScriptCategoryDoSet);

        }

        /** Sets the List of Assigned Entities from VO to DO */

        if (callScriptVO.getEntType() != null
                && callScriptVO.getEntType().size() > 0)
        {
            Set callScriptEntityDoSet = new HashSet(
                    ContactManagementConstants.DEFAULT_SIZE);

            /** Calls a method which converts categoryVo to category do */
            callScriptEntityDoSet = convertEntityVOListTODO(callScriptVO
                    .getEntType(), callScript);
            if(logger.isDebugEnabled())
            {
            	logger.debug("Inside helper size of entity set assined to callscript " + callScriptEntityDoSet.size());
            }
            callScript.setCommonEntityCallScripts(callScriptEntityDoSet);

        }
        
        /** Sets the List of Assigned Subjects from VO to DO */

        if (callScriptVO.getSubject() != null
                && callScriptVO.getSubject().size() > 0)
        {
            Set callScriptSubjectDoSet = new HashSet(
                    ContactManagementConstants.DEFAULT_SIZE);

            callScriptSubjectDoSet = convertSubjectVOListTODO(callScriptVO
                    .getSubject(), callScript);
            if(logger.isDebugEnabled())
            {
            	logger.debug("Inside helper size of subject set assined to callscript " + callScriptSubjectDoSet.size());
            }
            callScript.setSubjects(callScriptSubjectDoSet);
        }

        return callScript;
    }

    /**
     * * This method Converts the EntityVoList to EntityDO .
     * 
     * @param entityVOList :
     *            Takes List of entityVOList that are assigned to the Specific
     *            CallScript.
     * @param callScript :
     *            Takes List of callScript as param.
     * @return entityDOSet
     */
    private Set convertEntityVOListTODO(List entityVOList, CallScript callScript)
    {
        Set entityDOSet = new HashSet(ContactManagementConstants.DEFAULT_SIZE);
        //CallScript callScript = new CallScript();
        int entityVOListSize = entityVOList.size();
        /** Iterate through List and Populate the Set of Category DO */
        for (int i = 0; i < entityVOListSize; i++)
        {
            CommonEntityCallScript callEntityType = new CommonEntityCallScript();
            /** Audit Information */
            callEntityType.setAddedAuditTimeStamp(getTimeStamp());
            callEntityType.setAddedAuditUserID(getLoggedInUserID());
            callEntityType.setAuditTimeStamp(getTimeStamp());
            callEntityType.setAuditUserID(getLoggedInUserID());

            CallScriptEntityVO callScriptEntityVO = (CallScriptEntityVO) entityVOList
                    .get(i);
            
            if (callScriptEntityVO.getEntityTypeCode() != null)
            {
                CommonEntityType commonEntityType = new CommonEntityType();

                commonEntityType.setCommonEntityTypeCode(callScriptEntityVO
                        .getEntityTypeCode());
                /*
                 * Change done for ES2 DO changes
                 */
                callEntityType.setCommonEntityTypeCode(callScriptEntityVO
                        .getEntityTypeCode());
                /** Stores the Association of Enities with Call Script */
                
                /**
                 * Removes the Association of the entity with callscript on
                 * Deselection
                 */
                callEntityType.setCallScriptObj(callScript);
             
            }
            if(callScriptEntityVO.getIncludecallScript() == true)
            {
            	callScriptEntityVO.setAssignedCallScript(callScript.getDescription());
				
            }
            
            entityDOSet.add(callEntityType);
        }

        return entityDOSet;
    }

    /**
     * * This method Converts the CategoryVoList to CategoryDO .
     * 
     * @param categoryVOList :
     *            Takes List of CategoryVoList that are assigned to the Specific
     *            CallScript.
     * @param callScript :
     *            Takes callScript as param.
     * @return set of CategoryDO .
     */
    private Set convertCategoryVOListTODO(List categoryVOList,
            CallScript callScript)
    {
        Set categoryDOSet = new HashSet(ContactManagementConstants.DEFAULT_SIZE);
        CategoryDOConvertor categoryDOConvertor = new CategoryDOConvertor();
        int categoryVOListSize = categoryVOList.size();
        /** Iterate through List and Populate the Set of Category DO */
        for (int i = 0; i < categoryVOListSize; i++)
        {
        	//FindBug Issue Resolved
            //CorrespondenceCategory category = new CorrespondenceCategory();
            CorrespondenceCategory category = null;
            CallScriptCategoryVO callScriptCategoryVO = (CallScriptCategoryVO) categoryVOList
                    .get(i);
            category = categoryDOConvertor
                    .convertCategoryVOToDO(callScriptCategoryVO.getCategoryVO());
            /** Stores the Association of categories with Call Script */
            if (callScriptCategoryVO.getStatus().equalsIgnoreCase(
                    ContactManagementConstants.STATUS_DELETED))
            {
                category.setCallScript(null);
            }
            /**
             * Removes the Association of the Category with callscript on
             * Deselection
             */
            else
            {
                category.setCallScript(callScript);
            
            }
            if(callScriptCategoryVO.getIncludecallScript() == true)
            {
            	callScriptCategoryVO.setAssignedCallScript(callScript.getDescription());
            }
            categoryDOSet.add(category);
        }

        return categoryDOSet;
    }

    /**
	 * @param string
	 * @return
	 */
	private Object ValueOf(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * * This method Converts the EntityVoList to EntityDO .
     * 
     * @param subjectVOList :
     *            Takes List of subjectVOList that are assigned to the Specific
     *            CallScript.
     * @param callScript :
     *            Takes callScript as param.
     * @return entityDOSet
     */
    private Set convertSubjectVOListTODO(List subjectVOList,
            CallScript callScript)
    {
        Set subjectDOSet = new HashSet(ContactManagementConstants.DEFAULT_SIZE);
        int subjectVOListSize = subjectVOList.size();
        /** Iterate through List and Populate the Set of Category DO */
        for (int i = 0; i < subjectVOListSize; i++)
        {
            SubjectAuxillary subjectAuxillary = new SubjectAuxillary();
            /** Audit Information */
            subjectAuxillary.setAddedAuditTimeStamp(getTimeStamp());
            subjectAuxillary.setAddedAuditUserID(getLoggedInUserID());
            subjectAuxillary.setAuditTimeStamp(getTimeStamp());
            subjectAuxillary.setAuditUserID(getLoggedInUserID());

            CallScriptSubjectVO callScriptSubjectVO = (CallScriptSubjectVO) subjectVOList
                    .get(i);

           // subjectAuxillary.setVersionNo(callScriptSubjectVO.getVersionNo());
            subjectAuxillary.setVersionNo(callScript.getVersionNo());
            
            if (callScriptSubjectVO.getSubjectCode() != null)
            {
                subjectAuxillary.setSubjectCode(callScriptSubjectVO
                        .getSubjectCode());

                /** Stores the Association of Subject with Call Script */
                if (callScriptSubjectVO.getStatus().equalsIgnoreCase(
                        ContactManagementConstants.STATUS_DELETED))
                {
                    subjectAuxillary.setCallScript(null);
                }
                /**
                 * Removes the Association of the Subject with callscript on
                 * Deselection
                 */
                else
                {
                    subjectAuxillary.setCallScript(callScript);
                }
                if(callScriptSubjectVO.getIncludecallScript() == true)
                {
                	callScriptSubjectVO.setAssignedCallScript(callScript.getDescription());
    				
                }
            }

            subjectDOSet.add(subjectAuxillary);
        }

        return subjectDOSet;
    }

    /**
     * This method converts Category Do to Vo.
     * 
     * @param categoryDO :
     *            takes CorrespondenceCategory as param.
     * @return CategoryVO
     */
    public CategoryVO convertCategoryDOToVO(CorrespondenceCategory categoryDO)
    {
        
        CategoryVO categoryVO = new CategoryVO();

        if (categoryDO != null)
        {

            categoryVO.setCategorySK(categoryDO.getCategorySK());
            categoryVO.setCategoryDesc(categoryDO.getDescription());
            if (categoryDO.getVoidIndicator().booleanValue())
            {
                categoryVO.setActiveIndicator(ContactManagementConstants.NO);

            }
            else
            {
                categoryVO.setActiveIndicator(ContactManagementConstants.YES);
            }

            if (categoryDO.getVoidIndicator().booleanValue())
            {
                categoryVO.setInactive(true);

            }
            else
            {
                categoryVO.setInactive(false);
            }

            if (categoryDO.getSupervisorReviewReqIndicator().booleanValue())
            {
                categoryVO
                        .setSupervisorAppReqInd(ContactManagementConstants.YES);
            }
            else
            {
                categoryVO
                        .setSupervisorAppReqInd(ContactManagementConstants.NO);
            }

            categoryVO.setPriority(categoryDO.getPriorityCode());

            if (categoryDO.getCategoryDeletionDays() != null)
            {
                categoryVO.setNumOfDaysToKeep(categoryDO
                        .getCategoryDeletionDays().toString());
            }
            if (categoryDO.getCategoryPriorityDay1() != null)
            {
                categoryVO.setNumOfDaysBeforeEscToMed(categoryDO
                        .getCategoryPriorityDay1().toString());
            }
            if (categoryDO.getCategoryPriorityDay2() != null)
            {
                categoryVO.setNumOfDaysBeforeEscToHigh(categoryDO
                        .getCategoryPriorityDay2().toString());
            }
            if (categoryDO.getCategoryPriorityDay3() != null)
            {
                categoryVO.setNumOfDaysBeforeEscToUrg(categoryDO
                        .getCategoryPriorityDay3().toString());
            }

            if (categoryDO.getCallScript() != null)
            {
                categoryVO.setCallScriptSK(categoryDO.getCallScript()
                        .getCallScriptSK());
            }
                      
            if (categoryDO.getDefaultRouteToWorkUnit() != null)
            {
                categoryVO.setCallScriptSK(categoryDO
                        .getDefaultRouteToWorkUnit().getWorkUnitSK());
            }

            categoryVO.setVersionNo(categoryDO.getVersionNo());

            categoryVO.setAddedAuditTimeStamp(categoryDO
                    .getAddedAuditTimeStamp());
            categoryVO.setAddedAuditUserID(categoryDO.getAddedAuditUserID());
            categoryVO.setAuditTimeStamp(categoryDO.getAuditTimeStamp());
            categoryVO.setAuditUserID(categoryDO.getAuditUserID());
            
        }
        return categoryVO;
    }

    /**
     * This method will get userid from Security.
     * 
     * @return String
     */
    /*public String getUserID()
    {
        String userId = ContactManagementConstants.TEST_USERID;
        // FacesContext fc = FacesContext.getCurrentInstance();
        CallScriptControllerBean callScriptControllerBean = new CallScriptControllerBean();

        HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse renderResponse = null;

        EnterpriseUserProfile eup = callScriptControllerBean.getUserData(
                renderRequest, renderResponse);

        if (eup != null && !isNullOrEmptyField(eup.getUserId()))
        {
            userId = eup.getUserId();
        }
        logger.debug("userid is ----->" + userId);
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
        
        Calendar cal = Calendar.getInstance();

        return new Timestamp(cal.getTimeInMillis());
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

}
