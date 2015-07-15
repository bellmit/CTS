/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;

/**
 * Holds the Call Script Information
 */
public class CallScriptVO
        extends AuditaleEnterpriseBaseVO
{

    /**
     * 
     */
    public CallScriptVO()
    {
        super();
        setCallScriptStatus(ContactManagementConstants.YES);
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
    //FindBug Issue starts
    /*public CallScriptVO(Object auditUserID, Object auditTimeStamp,
            Object addedAuditUserID, Object addedAuditTimeStamp)
    {
        //super (auditUserID, auditTimeStamp, addedAuditUserID,
          //      addedAuditTimeStamp);
        
      auditUserID=super.getAuditUserID();
      auditTimeStamp=super.getAuditTimeStamp();
      addedAuditUserID=super.getAddedAuditUserID();
      addedAuditTimeStamp=super.getAuditTimeStamp();
        
        // TODO Auto-generated constructor stub
    }*/
    //FindBug Issue Ends
    public CallScriptVO(Object auditUserID, Object auditTimeStamp,
            Object addedAuditUserID, Object addedAuditTimeStamp)
    {
        /*super (auditUserID, auditTimeStamp, addedAuditUserID,
                addedAuditTimeStamp);*/
        
      super.setAuditUserID((String) auditUserID);
      super.setAuditTimeStamp((Date) auditTimeStamp);
      super.setAddedAuditUserID((String) addedAuditUserID);
      super.setAddedAuditTimeStamp((Date) addedAuditTimeStamp);
        
        // TODO Auto-generated constructor stub
    }

    /**
     * Holds the description about call scirpt
     */
    private String description;

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param thedescription
     *            The description to set.
     */
    public void setDescription(String thedescription)
    {
        description = thedescription;
    }

    /**
     * This field is used to store whether CallScript  is inactive.
     */
    private boolean inactive = false;

    /**
     * @return Returns the inactive.
     */
    public boolean isInactive()
    {
        return inactive;
    }

    /**
     * @param inactive The inactive to set.
     */
    public void setInactive(boolean inactive)
    {
        this.inactive = inactive;
    }

    /**
     * Holds the Call script Full Text .
     */
    private String callScriptFullText;
    
    /** Holds the Call script Cex Sk */
    private Long callScriptCexSk;
    
    /** Holds the Call script Cex version no */
    private int callScriptCexVersionNo;

    /**
     * @return Returns the callScriptFullText.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCallScriptFullText()
    {
        return callScriptFullText;
    }

    /**
     * @param thecallScriptFullText
     *            The callScriptFullText to set.
     */
    public void setCallScriptFullText(String thecallScriptFullText)
    {
        callScriptFullText = thecallScriptFullText;
    }

    /**
     * Holds Calll script status.
     */
    private String callScriptStatus;

    /**
     * @return Returns the callScriptStatus.
     */
    public String getCallScriptStatus()
    {
        return callScriptStatus;
    }

    /**
     * @param thecallScriptStatus
     *            The callScriptStatus to set.
     */
    public final void setCallScriptStatus(String thecallScriptStatus)
    {
        callScriptStatus = thecallScriptStatus;
    }

    /**
     * Holds Call scirpt Surogate Key.
     */
    private Long callScriptSK;

    /**
     * @return Returns the callScriptSK.
     */
    public Long getCallScriptSK()
    {
        return callScriptSK;
    }

    /**
     * @param callScriptSK
     *            The callScriptSK to set.
     */
    public void setCallScriptSK(Long callScriptSK)
    {
        this.callScriptSK = callScriptSK;
    }

    /**
     * List to Hold Values of Entity Type.
     */
    private List entType = new ArrayList();

    /**
     * @return Returns the entType.
     */
    public List getEntType()
    {
        return entType;
    }

    /**
     * @param entType
     *            The entType to set.
     */
    public void setEntType(List entType)
    {
        this.entType = entType;
    }

    /**
     * List to Hold Values of Category Type.
     */
    private List category = new ArrayList();

    /**
     * @return Returns the category.
     */
    public List getCategory()
    {
        return category;
    }

    /**
     * @param category
     *            The category to set.
     */
    public void setCategory(List category)
    {
        this.category = category;
    }

    /**
     * List to Hold Values of Category Type.
     */
    private List subject = new ArrayList();

    /**
     * @return Returns the subject.
     */
    public List getSubject()
    {
        return subject;
    }

    /**
     * @param subject
     *            The subject to set.
     */
    public void setSubject(List subject)
    {
        this.subject = subject;
    }

    /**
     * Comment for <code>entityType</code>
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    /*
     * private EntityType entityType;
     *//**
     * @return Returns the entityType.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    /*
     * public EntityType getEntityType() { return entityType; }
     *//**
     * @param theentityType
     *            The entityType to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    /*
     * public void setEntityType(EntityType theentityType) { entityType =
     * theentityType; }
     *//**
     * Comment for <code>category</code>
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    /*
     * private CategoryVO category;
     *//**
     * @return Returns the category.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    /*
     * public CategoryVO getCategory() { return category; }
     *  
     *//**
     * @param thecategory
     *            The category to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    /*
     * public void setCategory(CategoryVO thecategory) { category = thecategory; }
     */

	/**
	 * @return Returns the callScriptCexSk.
	 */
	public Long getCallScriptCexSk() {
		return callScriptCexSk;
	}
	/**
	 * @param callScriptCexSk The callScriptCexSk to set.
	 */
	public void setCallScriptCexSk(Long callScriptCexSk) {
		this.callScriptCexSk = callScriptCexSk;
	}
	/**
	 * @return Returns the callScriptCexVersionNo.
	 */
	public int getCallScriptCexVersionNo() {
		return callScriptCexVersionNo;
	}
	/**
	 * @param callScriptCexVersionNo The callScriptCexVersionNo to set.
	 */
	public void setCallScriptCexVersionNo(int callScriptCexVersionNo) {
		this.callScriptCexVersionNo = callScriptCexVersionNo;
	}
}
