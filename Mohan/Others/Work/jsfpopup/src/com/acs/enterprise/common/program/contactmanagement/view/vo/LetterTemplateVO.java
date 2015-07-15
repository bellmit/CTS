/*
 * Created on Jul 7, 2008
 *  
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * Holds the information about TemplateVO.
 * 
 */
public class LetterTemplateVO
        extends AuditaleEnterpriseBaseVO
{

    /**
     * Holds value for voidIndicator
     */
    private String voidIndicator;

    /**
     * Holds value for optnlTextAllowIndicator
     */
    private String optnlTextAllowIndicator;

    /**
     * Holds value for cotsLtrTmpltKeyData
     */
    private String cotsLtrTmpltKeyData;

    /**
     * Holds value for templateDescription
     */
    private String templateDescription;

    /**
     * Holds value for dfltSprvsrRevwReqdIndicator
     */
    private String dfltSprvsrRevwReqdIndicator;

    /**
     * Holds value for templateName
     */
    private String templateName;

    /**
     * Holds value for associatedKeywordsVO
     */
    private List associatedKeywordsVO;

    /**
     * Holds value for availableKeywordsVO
     */
    private List availableKeywordsVO;

    /**
     * Holds value for dfltDueDtOffsetNum
     */
    private Integer dfltDueDtOffsetNum;
    
    /**
     * Holds value for categoryDescription
     */
    private String categoryDescription;


    /**
     * Holds value for Enterprise Logger
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(LetterTemplateVO.class);

    /**
     * Default Constructor
     */
    public LetterTemplateVO()
    {
        super();
        associatedKeywordsVO = new ArrayList();
        availableKeywordsVO = new ArrayList();
    }

    /**
     * Constructor
     * @param vo : an object of type LetterTemplateVO
     */
    public LetterTemplateVO(LetterTemplateVO vo)
    {
    	System.out.println("LetterTemplateVO categoryDescription Method has started");
        /*super(vo.getAuditUserID(), vo.getAddedAuditTimeStamp(), vo
                .getAddedAuditUserID(), vo.getAddedAuditTimeStamp());*/
        this.voidIndicator = vo.getVoidIndicator();
        this.optnlTextAllowIndicator = vo.getOptnlTextAllowIndicator();
        this.cotsLtrTmpltKeyData = vo.cotsLtrTmpltKeyData;
        this.templateDescription = vo.templateDescription;
        this.dfltSprvsrRevwReqdIndicator = vo.getDfltSprvsrRevwReqdIndicator();
        this.templateName = vo.getTemplateName();
        this.templateDescription = vo.getTemplateDescription();
        this.setVersionNo(vo.getVersionNo());
        //Below code added as part of the Defect ESPRD00938865
        this.categoryDescription = vo.getCategoryDescription();

        associatedKeywordsVO = vo.getAssociatedKeywordsVO();
        availableKeywordsVO = vo.getAvailableKeywordsVO();
        System.out.println("LetterTemplateVO categoryDescription Method has Ended");
    }

    /**
     * @return Returns the associatedKeywor.dsVO.
     */
    public List getAssociatedKeywordsVO()
    {
        return associatedKeywordsVO;
    }

    /**
     * @param associatedKeywordsVO
     *            The associatedKeywordsVO to set.
     */
    public void setAssociatedKeywordsVO(List associatedKeywordsVO)
    {
        this.associatedKeywordsVO = associatedKeywordsVO;
    }

    /**
     * @return Returns the availableKeywordsVO.
     */
    public List getAvailableKeywordsVO()
    {
        return availableKeywordsVO;
    }

    /**
     * @param availableKeywordsVO
     *            The availableKeywordsVO to set.
     */
    public void setAvailableKeywordsVO(List availableKeywordsVO)
    {
        this.availableKeywordsVO = availableKeywordsVO;
    }

    /**
     * @return Returns the cotsLtrTmpltKeyData.
     */
    public String getCotsLtrTmpltKeyData()
    {
        return cotsLtrTmpltKeyData;
    }

    /**
     * @param cotsLtrTmpltKeyData
     *            The cotsLtrTmpltKeyData to set.
     */
    public void setCotsLtrTmpltKeyData(String cotsLtrTmpltKeyData)
    {
        this.cotsLtrTmpltKeyData = cotsLtrTmpltKeyData;
    }

    /**
     * @return Returns the dfltSprvsrRevwReqdIndicator.
     */
    public String getDfltSprvsrRevwReqdIndicator()
    {
        return dfltSprvsrRevwReqdIndicator;
    }

    /**
     * @param dfltSprvsrRevwReqdIndicator
     *            The dfltSprvsrRevwReqdIndicator to set.
     */
    public void setDfltSprvsrRevwReqdIndicator(
            String dfltSprvsrRevwReqdIndicator)
    {
        this.dfltSprvsrRevwReqdIndicator = dfltSprvsrRevwReqdIndicator;
    }

    /**
     * @return Returns the optnlTextAllowIndicator.
     */
    public String getOptnlTextAllowIndicator()
    {
        return optnlTextAllowIndicator;
    }

    /**
     * @param optnlTextAllowIndicator
     *            The optnlTextAllowIndicator to set.
     */
    public void setOptnlTextAllowIndicator(String optnlTextAllowIndicator)
    {
        this.optnlTextAllowIndicator = optnlTextAllowIndicator;
    }

    /**
     * @return Returns the templateDescription.
     */
    public String getTemplateDescription()
    {
        return templateDescription;
    }

    /**
     * @param templateDescription
     *            The templateDescription to set.
     */
    public void setTemplateDescription(String templateDescription)
    {
        this.templateDescription = templateDescription;
    }

    /**
     * @return Returns the templateName.
     */
    public String getTemplateName()
    {
        return templateName;
    }

    /**
     * @param templateName
     *            The templateName to set.
     */
    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    /**
     * @return Returns the voidIndicator.
     */
    public String getVoidIndicator()
    {
        return voidIndicator;
    }

    /**
     * @param voidIndicator
     *            The voidIndicator to set.
     */
    public void setVoidIndicator(String voidIndicator)
    {
        this.voidIndicator = voidIndicator;
    }

    /**
     * @return Returns the dfltDueDtOffsetNum.
     */
    public Integer getDfltDueDtOffsetNum()
    {
        return dfltDueDtOffsetNum;
    }

    /**
     * @param dfltDueDtOffsetNum
     *            The dfltDueDtOffsetNum to set.
     */
    public void setDfltDueDtOffsetNum(Integer dfltDueDtOffsetNum)
    {
        this.dfltDueDtOffsetNum = dfltDueDtOffsetNum;
    }

	/**
	 * @return the categoryDescription
	 */
	public String getCategoryDescription() {
		return categoryDescription;
	}

	/**
	 * @param categoryDescription the categoryDescription to set
	 */
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
    
  
}
