/*
 * Created on Oct 3, 2007
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.internalmessage.common.vo.DepartmentVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * Holds the information about EDMSDefaultsVO.
 */
public class EDMSDefaultsVO
        extends AuditaleEnterpriseBaseVO
{
    /**
     * constructor for EDMSDefaultsDOConverter.
     */
    public EDMSDefaultsVO()
    {
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(EDMSDefaultsVO.class);
      
    }
    
    public EDMSDefaultsVO(Object auditUserID, Object auditTimeStamp,
            Object addedAuditUserID, Object addedAuditTimeStamp)
    {
    			/* super(auditUserID, auditTimeStamp, addedAuditUserID,
		                addedAuditTimeStamp);*/
    	// Find bug issue starts
		       /* auditUserID=super.getAuditUserID();
		        auditTimeStamp=super.getAuditTimeStamp();
		        addedAuditUserID=super.getAddedAuditUserID();
		        addedAuditTimeStamp=super.getAuditTimeStamp();*/
		// Find bug issue ENDs
	  super.setAuditUserID((String) auditUserID);
      super.setAuditTimeStamp((Date) auditTimeStamp);
      super.setAddedAuditUserID((String) addedAuditUserID);
      super.setAddedAuditTimeStamp((Date) addedAuditTimeStamp);
            
    }

    /** Enterprise Logger for Logging */
    /*
     * private transient EnterpriseLogger logger = EnterpriseLogFactory
     * .getLogger(EDMSDefaultsVO.class);
     */

    /**
     * Holds the information about defaul document type that needs to assigned
     * when creating a CR Via EDMS.
     */
    private String documentType;

    /**
     * used for updation or Editing
     */
    private String edmsDefaultTypeFromUI;

    /**
     * @return Returns the documentType.
     */
    public String getDocumentType()
    {
        return documentType;
    }

    /**
     * @param thedocumentType
     *            The documentType to set.
     */
    public void setDocumentType(String thedocumentType)
    {
        documentType = thedocumentType;
    }

    /**
     * This type represents case or category.
     */
    private String anEDMSType;

    /**
     * @return Returns the anEDMSType.
     */
    public String getAnEDMSType()
    {
        return anEDMSType;
    }

    /**
     * @param theanEDMSType
     *            The anEDMSType to set.
     */
    public void setAnEDMSType(String theanEDMSType)
    {
        anEDMSType = theanEDMSType;
    }

    /**
     * Holds the information about default category that needs to assigned when
     * creating a CR Via EDMS.
     */
    private CategoryVO categoryVO;
    
    /**
     * Holds the information about default category that needs to assigned when
     * creating a CR Via EDMS.
     */
    private CaseTypeVO caseTypeVO;

    /**
     * Holds the information about default categorySK.
     */
    private String categorySK;

    /**
     * Holds the information about default categorySK.
     */
    private String categoryDesc;

    /**
     * Holds the information about defaul subject.. that needs to assigned when
     * creating a CR Via EDMS.
     */
    private String subject;

    /**
     * @return Returns the subject.
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @return Returns the edmsDefaultTypeFromUI.
     */
    public String getEdmsDefaultTypeFromUI()
    {
        return edmsDefaultTypeFromUI;
    }

    /**
     * @param edmsDefaultTypeFromUI
     *            The edmsDefaultTypeFromUI to set.
     */
    public void setEdmsDefaultTypeFromUI(String edmsDefaultTypeFromUI)
    {
        this.edmsDefaultTypeFromUI = edmsDefaultTypeFromUI;
    }

    /**
     * @param thesubject
     *            The subject to set.
     */
    public void setSubject(String thesubject)
    {
        subject = thesubject;
    }

    /**
     * Holds the information about defaul status of the CR. that needs to
     * assigned when creating a CR Via EDMS.
     */
    private String status;

    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param thestatus
     *            The status to set.
     */
    public void setStatus(String thestatus)
    {
        status = thestatus;
    }

    /**
     * Holds the subjects code
     */
    private String statusCode;

    /**
     * Holds the subjects code
     */
    private String subjectCode;

    /**
     * Holds the information about defaul routeTo user name that needs to
     * assigned when creating a CR Via EDMS.
     */
    private EdmsWorkUnitVO edmsWorkUnitVO;

    /**
     * Holds the userWorkunitSk in string format.
     */
    private String userWorkUnitSkStr;

    /**
     * Holds the userWorkunitSk in string format.
     */
    private String caseUserWorkUnitSkStr;
    
    /**
     * Holds the information about default department that needs to assigned
     * when creating a CR Via EDMS.
     */
    private DepartmentVO departmentVO;

    /** Holds the WorkUnitSK */
    private String deptWorkUnitSK;
   
    /** Holds the department name */
    private String deptName;
    
    private String routeToString;

	/**
	 * @return Returns the routeToString.
	 */
	public String getRouteToString() {
		return routeToString;
	}
	/**
	 * @param routeToString The routeToString to set.
	 */
	public void setRouteToString(String routeToString) {
		this.routeToString = routeToString;
	}
    /**
     * @return Returns the deptName.
     */
    public String getDeptName()
    {
        return deptName;
    }

    /**
     * @param deptName
     *            The deptName to set.
     */
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    /**
     * @return Returns the deptWorkUnitSK.
     */
    public String getDeptWorkUnitSK()
    {
        return deptWorkUnitSK;
    }

    /**
     * @param deptWorkUnitSK
     *            The deptWorkUnitSK to set.
     */
    public void setDeptWorkUnitSK(String deptWorkUnitSK)
    {
        this.deptWorkUnitSK = deptWorkUnitSK;
    }

    /**
     * Holds the information about caseType.
     */
    private String caseType;

    /**
     * @return Returns the caseType.
     */
    public String getCaseType()
    {
        return caseType;
    }

    /**
     * @param thecaseType
     *            The caseType to set.
     */
    public void setCaseType(String thecaseType)
    {

        caseType = thecaseType;
    }
    
    /**
     * Holds the information about caseType.
     */
    private String caseTypeSk;
    
    

    /**
     * @return Returns the categorySK.
     */
    public String getCategorySK()
    {
        return categorySK;
    }

    /**
     * @param categorySK
     *            The categorySK to set.
     */
    public void setCategorySK(String categorySK)
    {
        this.categorySK = categorySK;
    }

    /**
     * @return Returns the statusCode.
     */
    public String getStatusCode()
    {
        return statusCode;
    }

    /**
     * @param statusCode
     *            The statusCode to set.
     */
    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    /**
     * @return Returns the subjectCode.
     */
    public String getSubjectCode()
    {
        return subjectCode;
    }

    /**
     * @param subjectCode
     *            The subjectCode to set.
     */
    public void setSubjectCode(String subjectCode)
    {
        this.subjectCode = subjectCode;
    }

    /**
     * @return Returns the categoryDesc.
     */
    public String getCategoryDesc()
    {
        return categoryDesc;
    }

    /**
     * @param categoryDesc
     *            The categoryDesc to set.
     */
    public void setCategoryDesc(String categoryDesc)
    {
        this.categoryDesc = categoryDesc;
    }

    /**
     * @return Returns the categoryVO.
     */
    public CategoryVO getCategoryVO()
    {
        return categoryVO;
    }

    /**
     * @param categoryVO
     *            The categoryVO to set.
     */
    public void setCategoryVO(CategoryVO categoryVO)
    {
        this.categoryVO = categoryVO;
    }

    /**
     * @return Returns the departmentVO.
     */
    public DepartmentVO getDepartmentVO()
    {
        return departmentVO;
    }

    /**
     * @param departmentVO
     *            The departmentVO to set.
     */
    public void setDepartmentVO(DepartmentVO departmentVO)
    {
        this.departmentVO = departmentVO;
    }

    /**
     * @return Returns the edmsWorkUnitVO.
     */
    public EdmsWorkUnitVO getEdmsWorkUnitVO()
    {
        return edmsWorkUnitVO;
    }

    /**
     * @param edmsWorkUnitVO
     *            The edmsWorkUnitVO to set.
     */
    public void setEdmsWorkUnitVO(EdmsWorkUnitVO edmsWorkUnitVO)
    {
        this.edmsWorkUnitVO = edmsWorkUnitVO;
    }

    /**
     * @return Returns the userWorkUnitSkStr.
     */
    public String getUserWorkUnitSkStr()
    {
        return userWorkUnitSkStr;
    }

    /**
     * @param userWorkUnitSkStr
     *            The userWorkUnitSkStr to set.
     */
    public void setUserWorkUnitSkStr(String userWorkUnitSkStr)
    {
        this.userWorkUnitSkStr = userWorkUnitSkStr;
    }
    
    /**
     * @return Returns the caseTypeSk.
     */
    public String getCaseTypeSk()
    {
        return caseTypeSk;
    }
    
    /**
     * @param caseTypeSk The caseTypeSk to set.
     */
    public void setCaseTypeSk(String caseTypeSk)
    {
        this.caseTypeSk = caseTypeSk;
    }
    
    /**
     * @return Returns the caseUserWorkUnitSkStr.
     */
    public String getCaseUserWorkUnitSkStr()
    {
        return caseUserWorkUnitSkStr;
    }
    
    /**
     * @param caseUserWorkUnitSkStr The caseUserWorkUnitSkStr to set.
     */
    public void setCaseUserWorkUnitSkStr(String caseUserWorkUnitSkStr)
    {
        this.caseUserWorkUnitSkStr = caseUserWorkUnitSkStr;
    }
    
    /**
     * @return Returns the caseTypeVO.
     */
    public CaseTypeVO getCaseTypeVO()
    {
        return caseTypeVO;
    }
    
    /**
     * @param caseTypeVO The caseTypeVO to set.
     */
    public void setCaseTypeVO(CaseTypeVO caseTypeVO)
    {
        this.caseTypeVO = caseTypeVO;
    }
}
