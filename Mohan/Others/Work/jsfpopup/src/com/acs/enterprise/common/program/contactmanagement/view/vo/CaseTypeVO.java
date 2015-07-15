
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CaseTypeDataBean;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author durpaam
 *
 */
/**
 * Holds the case type information
  */
public class CaseTypeVO  extends AuditaleEnterpriseBaseVO //extends EnterpriseBaseVO //CR_ESPRD00373565_MaintainCaseTypes_16JUL2010
{
	/**
     * Generating object of EnterpriseLogger.
     */
    
	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(CaseTypeDataBean.class);
	
	/**
	  * Constructor for CasetypeEventVo
	  */
	  public CaseTypeVO()
	  {
	  	   
	  }
   
   
	/**
	 * This holds casesteps
	 */
   private Set caseSteps;
   
   /**
	* This holds case events
	*/
   private Set caseEvents;
   /**
    * Holds ValueProtected Indicator
    */
   private String valuesProtectedIndicator;
   
   /**
    * @param auditUserID represents Object
    * @param auditTimeStamp represents Object
    * @param addedAuditUserID represents Object
    * @param addedAuditTimeStamp represents Object
    */
   public CaseTypeVO(Object auditUserID, Object auditTimeStamp,
           Object addedAuditUserID , Object addedAuditTimeStamp)
   {
    /*   super(auditUserID , auditTimeStamp , addedAuditUserID,
               addedAuditTimeStamp);*///CR_ESPRD00373565_MaintainCaseTypes_16JUL2010
       logger.debug("Inside Constructor");
   }
   
	/**
	* This holds casetype status code
	*/
	private String caseTypeStatusCode;

	/**
	 * @return Returns the caseTypeStatusCode.
	 */
	public String getCaseTypeStatusCode() 
	{
		return caseTypeStatusCode;
	}

	/**
	 * @param thecaseTypeStatusCode The caseTypeStatusCode to set.
	 */
	public void setCaseTypeStatusCode(String thecaseTypeStatusCode) 
	{
		caseTypeStatusCode = thecaseTypeStatusCode;
		
	}
	/**
	 * Holds CasetypeSk
	 */
    private Long caseTypeSK;
	/**
	 This holds the short description of the casetype
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private String shortDesc;

	/**
	 * @return Returns the shortDesc.
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String getShortDesc() 
	{
		return shortDesc;
	}

	/**
	 * @param theshortDesc The shortDesc to set.
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public void setShortDesc(String theshortDesc)
	{
		shortDesc = theshortDesc;
	}

	/**
	 This holds the description of the casetype
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private String longDesc;

	/**
	 * @return Returns the longDesc.
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public String getLongDesc() 
	{
		return longDesc;
	}

	/**
	 * @param thelongDesc The longDesc to set.
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public void setLongDesc(String thelongDesc) 
	{
		longDesc = thelongDesc;
	}

	/**
	 *Identifies which of the Business Unit Case Type subtype tables each Case Type refers to.  
	 *This value essentially groups case types so that the group will 
	 * relate to one of the subtype tables below.
	 e.g. BCCP, ARS, Appeal, DDU.
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private String busnUnitCaseTypeCode;
	/**This is used to display businesstypeCode description 
	 * in datatable
	 * @return
	 */
    private String busnUnitTypeCodeDesc;
	/**
	 * @return Returns the busnUnitCaseTypeCode
	 */
	public String getBusnUnitCaseTypeCode()
	{
		return busnUnitCaseTypeCode;
	}
	/**
	 * @param thebusnUnitCaseTypeCode The busnUnitCaseTypeCode to set.
	 */
	public void setBusnUnitCaseTypeCode(String busnUnitCaseTypeCode) 
	{
		this.busnUnitCaseTypeCode = busnUnitCaseTypeCode;
	}

	/**
	 * This holds the supervisor review required indicator (Y or N)
	 */
	private String sprvsrRevwReqdInd;

	/**
	 * @return Returns the sprvsrRevwReqdInd.
	 */
	public String getSprvsrRevwReqdInd() 
	{
		return sprvsrRevwReqdInd;
	}

	/**
	 * @param thesprvsrRevwReqdInd The sprvsrRevwReqdInd to set.
	 */
	public void setSprvsrRevwReqdInd(String thesprvsrRevwReqdInd)
	{
		sprvsrRevwReqdInd = thesprvsrRevwReqdInd;
	}

	/**
	 * This represents casetype code of different case types
	 * e.g. Dental Approval, Provider Enrollment, Member Appeal.
	 */
	private String caseTypeCode;

	/**
	 * @return Returns the caseTypeCode.
	 */
	public String getCaseTypeCode() 
	{
		return caseTypeCode;
	}

	/**
	 * @param thecaseTypeCode The caseTypeCode to set.
	 */
	public void setCaseTypeCode(String thecaseTypeCode) 
	{
		caseTypeCode = thecaseTypeCode;
	}
	
	/**
     * This field is used to store list of Included Custom Field.
     */
    private List listOfCustomFields = (List) new ArrayList(
    		ContactManagementConstants.DEFAULT_SIZE);   


    /**
     * This method is used to get the listOfCustomFields.
     * @return List : Returns the listOfCustomFields.
     */
    public List getListOfCustomFields()
    {
        return listOfCustomFields;
    }

    /**
     * This method is used to set the listOfCustomFields.
     * @param listOfCustomFields :
     *            The listOfCustomFields to set.
     */
    public void setListOfCustomFields(List listOfCustomFields)
    {
        this.listOfCustomFields = listOfCustomFields;
    }
    
    /**
     * This field is used to store list of CaseSteps.
     */
    private List listOfCaseSteps = (List) new ArrayList(
    		ContactManagementConstants.DEFAULT_SIZE);   

	/**
     * This field is used to store list of Templates.
     */
	private List listOfTemplates = (List) new ArrayList(
    		ContactManagementConstants.DEFAULT_SIZE);   
	
	/**
	 * @return Returns the listOfTemplates.
	 */
	public List getListOfTemplates()
	{
		return listOfTemplates;
	}
	/**
	 * @param listOfTemplates The listOfTemplates to set.
	 */
	public void setListOfTemplates(List listOfTemplates) 
	{
		this.listOfTemplates = listOfTemplates;
	}

    /**
     * @param caseSteps The caseSteps to set.
     */
    public void setCaseSteps(Set caseSteps)
    {
        this.caseSteps = caseSteps;
    }

    /**
     * @return Returns the caseSteps.
     */
    public Set getCaseSteps()
    {
        return caseSteps;
    }

    /**
     * @param caseEvents The caseEvents to set.
     */
    public void setCaseEvents(Set caseEvents)
    {
        this.caseEvents = caseEvents;
    }

    /**
     * @return Returns the caseEvents.
     */
    public Set getCaseEvents()
    {
        return caseEvents;
    }

    /**
     * @param valuesProtectedIndicator The valuesProtectedIndicator to set.
     */
    public void setValuesProtectedIndicator(String valuesProtectedIndicator)
    {
        this.valuesProtectedIndicator = valuesProtectedIndicator;
    }

    /**
     * @return Returns the valuesProtectedIndicator.
     */
    public String getValuesProtectedIndicator()
    {
        return valuesProtectedIndicator;
    }
	
	/**
	 * @return Returns the caseTypeSK.
	 */
	public Long getCaseTypeSK() 
	{
		return caseTypeSK;
	}
	/**
	 * @param caseTypeSK The caseTypeSK to set.
	 */
	public void setCaseTypeSK(Long caseTypeSK) 
	{
		this.caseTypeSK = caseTypeSK;
	}
    /**
     * Holds the list of customFieldAssignment VOs
     */
    private List customFieldAssignmentVO;

	/**
	 * @return Returns the customFieldAssignmentVO.
	 */
	public List getCustomFieldAssignmentVO() 
	{
		return customFieldAssignmentVO;
	}
	/**
	 * @param customFieldAssignmentVO The customFieldAssignmentVO to set.
	 */
	public void setCustomFieldAssignmentVO(List customFieldAssignmentVO) 
	{
		this.customFieldAssignmentVO = customFieldAssignmentVO;
	}
	/**
	 * @return Returns the listOfCaseSteps.
	 */
	public List getListOfCaseSteps() 
	{
		return listOfCaseSteps;
	}
	/**
	 * @param listOfCaseSteps The listOfCaseSteps to set.
	 */
	public void setListOfCaseSteps(List listOfCaseSteps) 
	{
		this.listOfCaseSteps = listOfCaseSteps;
	}
   
    /**
     * @return Returns the busnUnitTypeCodeDesc.
     */
    public String getBusnUnitTypeCodeDesc()
    {
        return busnUnitTypeCodeDesc;
    }
    /**
     * @param busnUnitTypeCodeDesc The busnUnitTypeCodeDesc to set.
     */
    public void setBusnUnitTypeCodeDesc(String busnUnitTypeCodeDesc)
    {
        this.busnUnitTypeCodeDesc = busnUnitTypeCodeDesc;
    }
    
    private List caseTypeTemplateVO;

	public List getCaseTypeTemplateVO() {
		return caseTypeTemplateVO;
	}

	public void setCaseTypeTemplateVO(List caseTypeTemplateVO) {
		this.caseTypeTemplateVO = caseTypeTemplateVO;
	}
    
    
}
