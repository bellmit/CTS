/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NameVO;

/**
 * Holds the details about the entity
 * 
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
/**
 * @author vijaymai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CMEntityDetailVO
        extends AuditaleEnterpriseBaseVO
         implements Cloneable
    {
    
	/**
     * Construcor of the Class.
     */
    public CMEntityDetailVO()
    {
        
        
    }
   
    /**
     * @param auditUserID
     * Takes auditUserID
     * @param auditTimeStamp
     * Takes auditTimeStamp
     * @param addedAuditUserID
     * Takes addedAuditUserID
     * @param addedAuditTimeStamp
     * Takes addedAuditTimeStamp
     */
    //  	FindBug Issue Starts
    /*public CMEntityDetailVO(Object auditUserID, Object auditTimeStamp,
            Object addedAuditUserID, Object addedAuditTimeStamp)
    {
        //super(auditUserID, auditTimeStamp, addedAuditUserID,
          //      addedAuditTimeStamp);
        auditUserID=super.getAuditUserID();
        auditTimeStamp=super.getAuditTimeStamp();
        addedAuditUserID=super.getAddedAuditUserID();
        addedAuditTimeStamp=super.getAuditTimeStamp();
        // TODO Auto-generated constructor stub
    }*/
    //  FindBug Issue Ends
    public CMEntityDetailVO(Object auditUserID, Object auditTimeStamp,
            Object addedAuditUserID, Object addedAuditTimeStamp)
    {
        /*super(auditUserID, auditTimeStamp, addedAuditUserID,
                addedAuditTimeStamp);*/
    	super.setAuditUserID((String) auditUserID);
    	super.setAuditTimeStamp((Date)auditTimeStamp);
    	super.setAddedAuditUserID((String)addedAuditUserID );
    	super.setAddedAuditTimeStamp((Date)addedAuditTimeStamp);
    	
       /* auditUserID=super.getAuditUserID();
        auditTimeStamp=super.getAuditTimeStamp();
        addedAuditUserID=super.getAddedAuditUserID();
        addedAuditTimeStamp=super.getAuditTimeStamp();*/
        // TODO Auto-generated constructor stub
    }
    /**
     * It represents the valid entity codes like P Provider ,M Member , C
     * Carrier , B Unenrolled Member , Unenrolled Provider , O Other etc
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String entityType;

    /**
     * @return Returns the entityType.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getEntityType()
    {
        return entityType;
    }

    /**
     * @param theentityType
     *            The entityType to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setEntityType(String theentityType)
    {
        entityType = theentityType;
    }

    
    /**
     * It represents the valid entity codes like P Provider ,M Member , C
     * Carrier , B Unenrolled Member , Unenrolled Provider , O Other  with description etc
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String entityTypeWithDesc;
    /**
     * @return Returns the entityTypeWithDesc.
     */
    public String getEntityTypeWithDesc()
    {
        return entityTypeWithDesc;
    }
    /**
     * @param entityTypeWithDesc The entityTypeWithDesc to set.
     */
    public void setEntityTypeWithDesc(String entityTypeWithDesc)
    {
        this.entityTypeWithDesc = entityTypeWithDesc;
    }
    /**
     * CM Entity ID, which is currently known as Specific Entity ID, is an ID
     * created in Contact Management system for Unenrolled Entities.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String cmEntityID;

    /**
     * @return Returns the cmEntityID.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCmEntityID()
    {
        return cmEntityID;
    }

    /**
     * @param thecmEntityID
     *            The cmEntityID to set.
     
     */
    public void setCmEntityID(String thecmEntityID)
    {
        cmEntityID = thecmEntityID;
    }
    
    /**
     * Entity ID Type is an Type of an Enrolled Entity like Provider. 
     
     */
    private String entityIDType;
    

    /**
     * @return Returns the entityIDType.
     */
    public String getEntityIDType()
    {
        return entityIDType;
    }
    /**
     * @param entityIDType The entityIDType to set.
     */
    public void setEntityIDType(String entityIDType)
    {
        this.entityIDType = entityIDType;
    }
    /**
     * Entity ID is an ID of an Enrolled Entity like Provider .
     * 
     
     */
    private String entityID;
    
    private boolean auditLogRender=false;

	/**
	 * @return Returns the auditLogRender.
	 */
	public boolean isAuditLogRender() {
		return auditLogRender;
	}
	/**
	 * @param auditLogRender The auditLogRender to set.
	 */
	public void setAuditLogRender(boolean auditLogRender) {
		this.auditLogRender = auditLogRender;
	}
    /**
     * @return Returns the entityID.
     
     */
    public String getEntityID()
    {
        return entityID;
    }

    /**
     * @param theentityID
     *            The entityID to set.
     
     */
    public void setEntityID(String theentityID)
    {
        entityID = theentityID;
    }

    /**
     * It denotes organization types e.g. Organization, Group, Both
    
     */
    private String organizationType;

    /**
     * @return Returns the organizationType.
     
     */
    public String getOrganizationType()
    {
        return organizationType;
    }

    /**
     * @param theorganizationType
     *            The organizationType to set.
     
     */
    public void setOrganizationType(String theorganizationType)
    {
        organizationType = theorganizationType;
    }

    /**
     * It denotes organization Name .
     
     */
    private String organizationName;
    
    
    /**
     * @return Returns the organizationName.
     */
    public String getOrganizationName()
    {
        return organizationName;
    }
    /**
     * @param organizationName The organizationName to set.
     */
    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }
    /**
     * It denotes countyName .
     
     */
    private String countyName;
    
    
    /**
     * @return Returns the countyName.
     */
    public String getCountyName()
    {
        return countyName;
    }
    /**
     * @param countyName The countyName to set.
     */
    public void setCountyName(String countyName)
    {
        this.countyName = countyName;
    }
    
    /**
     * The field denotes the provoder type code e.g. Chiropractor, Physician,
     * Nurologist, Pediatrician.
     
     */
    private String providerType;

    /**
     * @return Returns the providerType.
     
     */
    public String getProviderType()
    {
        return providerType;
    }

    /**
     * @param theproviderType
     *            The providerType to set.
     
     */
    public void setProviderType(String theproviderType)
    {
        providerType = theproviderType;
    }

    /**
     * The National Provider ID of the entity.
     * 
     
     */
    private String nationalProviderID;

    /**
     * @return Returns the nationalProviderID.
     
     */
    public String getNationalProviderID()
    {
        return nationalProviderID;
    }

    /**
     * @param thenationalProviderID
     *            The nationalProviderID to set.
     
     */
    public void setNationalProviderID(String thenationalProviderID)
    {
        nationalProviderID = thenationalProviderID;
    }

    /**
     * Entity prefix
     * 
     
     */
    private String prefix;

    /**
     * @return Returns the prefix.
     
     */
    public String getPrefix()
    {
        return prefix;
    }

    /**
     * @param theprefix
     *            The prefix to set.
     
     */
    public void setPrefix(String theprefix)
    {
        prefix = theprefix;
    }

    /**
     * Creates reference of nameVo
     * 
    */
    private NameVO nameVO = new NameVO();

   
   
    
    /**
     * @return Returns the nameVO.
     */
    public NameVO getNameVO()
    {
        return nameVO;
    }
    /**
     * @param nameVO The nameVO to set.
     */
    public void setNameVO(NameVO nameVO)
    {
        this.nameVO = nameVO;
    }
    /**
     * The Social Security Number of the entity.
     * 
     
     */
    private String ssnNumber;

    /**
     * @return Returns the ssnNumber.
     
     */
    public String getSsnNumber()
    {
        return ssnNumber;
    }

    /**
     * @param thessnNumber
     *            The ssnNumber to set.
     
     */
    public void setSsnNumber(String thessnNumber)
    {
        ssnNumber = thessnNumber;
    }

    /**
     * The Federal Employer Identification Number of the entity also called as
     * Tax Identification Number and Federal Employee ID Number.
    
     */
    private String employeeIdentificationNumber;

    /**
     * @return Returns the employeeIdentificationNumber.
     
     */
    public String getEmployeeIdentificationNumber()
    {
        return employeeIdentificationNumber;
    }

    /**
     * @param theemployeeIdentificationNumber
     *            The employeeIdentificationNumber to set.
     
     */
    public void setEmployeeIdentificationNumber(
            String theemployeeIdentificationNumber)
    {
        employeeIdentificationNumber = theemployeeIdentificationNumber;
    }

    /**
     This field indicates a line of business code to be used for system processing.
      The line of business is used to identify the entities 
      that have fiscal responsibility for payment 
     of insurance claims on behalf of their respective members.
     
     */
    private String lineOfBusiness;

    /**
     * @return Returns the lineOfBusiness.
     
     */
    public String getLineOfBusiness()
    {
        return lineOfBusiness;
    }

    /**
     * @param thelineOfBusiness The lineOfBusiness to set.
 
     */
    public void setLineOfBusiness(String thelineOfBusiness)
    {
        lineOfBusiness = thelineOfBusiness;
    }
   
    /**
    This field indicates District Office Code. 
    */
   private String districtOfficeCode;
   
    
    
    
   
    /**
     * @return Returns the districtOfficeCode.
     */
    public String getDistrictOfficeCode()
    {
        return districtOfficeCode;
    }
    /**
     * @param districtOfficeCode The districtOfficeCode to set.
     */
    public void setDistrictOfficeCode(String districtOfficeCode)
    {
        this.districtOfficeCode = districtOfficeCode;
    }
    
    /**
     * Holds CommonEntity details
     */
    private CommonEntityVO commonEntityVO;
    /**
     * @return Returns the commonEntityVO.
     */
    public CommonEntityVO getCommonEntityVO()
    {
        return commonEntityVO;
    }
    /**
     * @param commonEntityVO The commonEntityVO to set.
     */
    public void setCommonEntityVO(CommonEntityVO commonEntityVO)
    {
        this.commonEntityVO = commonEntityVO;
    }
    /**
     * The field denotes the county type code e.g. Out Of State, Unknown,
     * Strafford, Hillsborough.
     
     */
    private String countyCode;

    /**
     * @return Returns the countyCode.
     
     */
    public String getCountyCode()
    {
        return countyCode;
    }

    /**
     * @param countyCode
     *            The countyCode to set.
     
     */
    public void setCountyCode(String countyCode)
    {
    	 	this.countyCode = countyCode;
    }
    /**
     * This method overrides the clone method of the Object Class to clone the
     * CMEntityDetailVO.
     * 
     * @return Object : a clone of this instance.
     * @exception CloneNotSupportedException :
     *                CloneNotSupportedException if the object's class does not
     *                support the Cloneable interface. Subclasses that override
     *                the clone method can also throw this exception to indicate
     *                that an instance cannot be cloned.
     * @see java.lang.Cloneable
     */
    
    private int commonEntityVersionNo;
    /**
     * @return Returns the commonEntityVersionNo.
     */
    public int getCommonEntityVersionNo()
    {
        return commonEntityVersionNo;
    }
    /**
     * @param commonEntityVersionNo The commonEntityVersionNo to set.
     */
    public void setCommonEntityVersionNo(int commonEntityVersionNo)
    {
        this.commonEntityVersionNo = commonEntityVersionNo;
    }
}
