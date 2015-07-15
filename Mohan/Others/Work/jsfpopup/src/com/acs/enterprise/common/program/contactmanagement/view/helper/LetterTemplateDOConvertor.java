/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterGenerationKeyword;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterTemplate;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterGenerationTemplateParameter;
import com.acs.enterprise.common.cots.util.GlobalLetterConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterKeywordVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterTemplateKeywordAssociationVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LetterTemplateVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * This class is used to convert all Domain Objects into Value objects of Log
 * Case.
 * 
 * @author Wipro
 */
public class LetterTemplateDOConvertor
        extends DataTransferObjectConverter
{

    /** Enterprise Logger for Logging. */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(LetterTemplateDOConvertor.class);

    /**
     * Methdos to convert DO to VO object
     * 
     * @param letterTemplateList :
     *           List of LetterTemplate objects
     * @return List of LetterTemplateVO  VO Objects
     */
  

    public List convertLetterTemplateDOToVOList(List letterTemplateList){
    	System.out.println("convertLetterTemplateDOToVOList Method has started in LetterTemplateDOConvertor");
    	List letterTemplateVoList = new ArrayList();
    	LetterTemplateVO letterTemplateVO = null;
    	LetterTemplate letterTemplate = null;
    	for (Iterator iter = letterTemplateList.iterator(); iter.hasNext();) {
    		letterTemplateVO = new LetterTemplateVO();
    		letterTemplate = (LetterTemplate)iter.next();
    		letterTemplateVO.setCotsLtrTmpltKeyData(letterTemplate
                    .getLetterTemplateKeyData());

            if (letterTemplate.getDefaultSupervisorRevwReqInd() != null)
            {
                letterTemplateVO
                        .setDfltSprvsrRevwReqdIndicator(letterTemplate
                                .getDefaultSupervisorRevwReqInd().equals(
                                        Boolean.TRUE) ? GlobalLetterConstants.YES
                                : GlobalLetterConstants.NO);
            }
            //Below code added as part of the Defect ESPRD00938865
            if (letterTemplate.getCategoryDescription() != null){
            	letterTemplateVO.setCategoryDescription(letterTemplate.getCategoryDescription());  
            }
            //

            if (letterTemplate.getOptionalTextAllowIndicator() != null)
            {
                letterTemplateVO.setOptnlTextAllowIndicator(letterTemplate
                        .getOptionalTextAllowIndicator().toString());
            }
            
            letterTemplateVO
                    .setTemplateDescription(letterTemplate.getDescription());

            letterTemplateVO.setTemplateName(letterTemplate.getName());

            letterTemplateVO.setVersionNo(letterTemplate.getVersionNo());

            letterTemplateVO.setDfltDueDtOffsetNum(letterTemplate
                    .getDefaultDueDateOffsetNum());


            letterTemplateVO.setAuditUserID(letterTemplate.getAuditUserID());

            letterTemplateVO.setAddedAuditUserID(letterTemplate
                    .getAddedAuditUserID());

            if (letterTemplate.getVoidIndicator() != null)
            {
                letterTemplateVO
                        .setVoidIndicator(letterTemplate.getVoidIndicator().equals(Boolean.TRUE) ? GlobalLetterConstants.YES
                                : GlobalLetterConstants.NO);
            }


            if (letterTemplate.getLetterGenerationTemplateParameters() != null)
            {
                Iterator it = letterTemplate.getLetterGenerationTemplateParameters()
                        .iterator();

                List associatedKeywords = new ArrayList();
                LetterGenerationTemplateParameter letterTemplateParam = null;
                LetterKeywordVO keywordVO = null;
                while (it.hasNext())
                {
                    letterTemplateParam = (LetterGenerationTemplateParameter) it
                            .next();

                    keywordVO = new LetterKeywordVO();

                    if (letterTemplateParam.getLetterGenerationKeyword() != null)
                    {
                        keywordVO.setKeywordId(letterTemplateParam
                                .getLetterGenerationKeyword().getLetterKeyWordID());

                        keywordVO.setKeywordLabel(letterTemplateParam
                                .getLetterGenerationKeyword().getLabelText());

                        keywordVO.setVersionNo(letterTemplateParam.getVersionNo());

                        keywordVO.setKeywordTypeCode(letterTemplateParam
                                .getLetterGenerationKeyword()
                                .getLetterKeyWordTypeCode());

                        keywordVO.setAuditUserID(letterTemplateParam
                                .getAuditUserID());

                        keywordVO.setAddedAuditUserID(letterTemplateParam
                                .getAddedAuditUserID());
                        associatedKeywords.add(keywordVO);
                    }

                }
                letterTemplateVO.setAssociatedKeywordsVO(associatedKeywords);
            }
            letterTemplateVoList.add(letterTemplateVO);
    	}
    	System.out.println("convertLetterTemplateDOToVOList Method has Ended in LetterTemplateDOConvertor"+letterTemplateVoList);
    	return letterTemplateVoList;
    }
    /**
     * This method is to convert letter template VO to DO
     * 
     * @param letterTemplateVO :
     *            an object of type LetterTemplateVO
     * @return LetterTemplate : a DO object
     */
    public LetterTemplate convertLetterTemplateVOToDO(
            LetterTemplateVO letterTemplateVO)
    {
    	System.out.println("convertLetterTemplateVOToDO Method has Started in LetterTemplateDOConvertor");
        LetterTemplate letterTemplate = new LetterTemplate();

        letterTemplate.setLetterTemplateKeyData(letterTemplateVO
                .getCotsLtrTmpltKeyData());
        if (letterTemplateVO.getDfltSprvsrRevwReqdIndicator() != null)
        {

            if(letterTemplateVO.getDfltSprvsrRevwReqdIndicator().equals(GlobalLetterConstants.YES)){
                letterTemplate.setDefaultSupervisorRevwReqInd(Boolean.TRUE); 
            }
            else
            {
                letterTemplate.setDefaultSupervisorRevwReqInd(Boolean.FALSE);
            }
            
        }
        //Below code added as part of the Defect ESPRD00938865
        if (letterTemplateVO.getCategoryDescription()!= null){
        	 letterTemplate.setCategoryDescription(letterTemplateVO.getCategoryDescription());
        }
       
        //
        
        letterTemplate
                .setDescription(letterTemplateVO.getTemplateDescription());
        letterTemplate.setName(letterTemplateVO.getTemplateName());
        letterTemplate.setDefaultDueDateOffsetNum(letterTemplateVO
                .getDfltDueDtOffsetNum());

        letterTemplate.setVersionNo(letterTemplateVO.getVersionNo());
        letterTemplate.setAuditUserID(letterTemplateVO.getAuditUserID());
        letterTemplate.setAddedAuditUserID(letterTemplateVO
                .getAddedAuditUserID());

        
        if (letterTemplateVO.getVoidIndicator() != null)
        {

            if(letterTemplateVO.getVoidIndicator().equals(GlobalLetterConstants.YES)){
                letterTemplate.setVoidIndicator(Boolean.TRUE); 
            }
            else
            {
                letterTemplate.setVoidIndicator(Boolean.FALSE);
            }
            
        }
       
        if (letterTemplateVO.getAssociatedKeywordsVO() != null)
        {
            Iterator iter = letterTemplateVO.getAssociatedKeywordsVO()
                    .iterator();
            LetterTemplateKeywordAssociationVO letterTemplateKeywordAssociationVO = null;
            LetterGenerationTemplateParameter letterGenerationTemplateParameter = null;
            while (iter.hasNext())
            {
               

                letterTemplateKeywordAssociationVO = (LetterTemplateKeywordAssociationVO) iter
                        .next();
                letterGenerationTemplateParameter = this
                        .convertLetterTemplateParameterVOToDO(letterTemplateKeywordAssociationVO);

                letterTemplate
                        .addLetterTemplateParameters(letterGenerationTemplateParameter);
            }

        }

        System.out.println("convertLetterTemplateVOToDO Method has Ended in LetterTemplateDOConvertor----->>"+letterTemplate);
        return letterTemplate;
    }

    /**
     * This method is to convert LetterTemplateKeywordAssociationVO to
     * LetterGenerationTemplateParameter
     * 
     * @param letterTemplateKeywordAssociationVO :
     *            an object of type LetterTemplateKeywordAssociationVO
     * @return LetterGenerationTemplateParameter : a DO object
     */
    private LetterGenerationTemplateParameter convertLetterTemplateParameterVOToDO(
            LetterTemplateKeywordAssociationVO letterTemplateKeywordAssociationVO)
    {
        LetterGenerationTemplateParameter letterTemplateParameter = new LetterGenerationTemplateParameter();

        if (letterTemplateKeywordAssociationVO.getLetterKeywordId() != null)
        {
            LetterGenerationKeyword letterKeyword = new LetterGenerationKeyword();
            letterKeyword.setLetterKeyWordID(letterTemplateKeywordAssociationVO
                    .getLetterKeywordId());
            letterTemplateParameter.setLetterGenerationKeyword(letterKeyword);

            letterTemplateParameter
                    .setVersionNo(letterTemplateKeywordAssociationVO
                            .getVersionNo());
            letterTemplateParameter
                    .setAuditUserID(letterTemplateKeywordAssociationVO
                            .getAuditUserID());
            letterTemplateParameter
                    .setAddedAuditUserID(letterTemplateKeywordAssociationVO
                            .getAddedAuditUserID());

        }
        if (letterTemplateKeywordAssociationVO.getLetterTemplateId() != null)
        {
            LetterTemplate letterTemplate = new LetterTemplate();
            letterTemplate
                    .setLetterTemplateKeyData(letterTemplateKeywordAssociationVO
                            .getLetterTemplateId());
            letterTemplateParameter.setLetterTemplate(letterTemplate);
        }
        letterTemplateParameter
                .setLetterGenerationKeywordUsageCode(letterTemplateKeywordAssociationVO
                        .getLetterKeywordUsageCode());
        letterTemplateParameter.setVersionNo(letterTemplateKeywordAssociationVO
                .getVersionNo());

        return letterTemplateParameter;
    }

}
