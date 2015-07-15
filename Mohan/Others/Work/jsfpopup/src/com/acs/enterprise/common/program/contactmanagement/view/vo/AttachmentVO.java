/*
 * Created on Oct 3, 2007 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;



import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;


/**
 * Holds the information about attachmentVO.
 */
public class AttachmentVO extends AuditaleEnterpriseBaseVO
        //extends EnterpriseBaseVO //CR_ESPRD00373565_LOGCASE_23JUL2010
{
    /** holds the AttachmentSK */
    private Long attachmentSK;

    /** Holds the pageID */
    private String attachmentPageID;

    /** holds the final path to WPS */
    private String finalPath;

    /** holds the detach indicator */
    private boolean detachInd = false;

    /** Holds historyIndicator of type Boolean */
    private boolean historyIndicator;
    
    /** Holds showDeleteForSearched documents of type Boolean */
    private boolean showDeleteForSearched;

    /**  for Fixing Defect ESPRD00611913
     * */
    private boolean newAttachment=true;
    

	/**
	 * @return Returns the newAttachment.
	 */
	public boolean isNewAttachment() {
		return newAttachment;
	}
	/**
	 * @param newAttachment The newAttachment to set.
	 */
	public void setNewAttachment(boolean newAttachment) {
		this.newAttachment = newAttachment;
	}
    /**
     * attachmentIndicator Added.
     */
    private String attachmentIndicator;

    /**
     * Date Added.
     */
    private String dateAdded;

    /**
     * Attachment URL
     */
    private String fileUrl;
    
    /**
     * Holds Cascade Key information used by EDMS to update Document Management Products.
     */
    private String cascadeKey;

    

    /**
     * @return Returns the dateAdded.
     */
    public String getDateAdded()
    {
        return dateAdded;
    }

    /**
     * @param dateAdded
     *            The dateAdded to set.
     */
    public void setDateAdded(String dateAdded)
    {
        this.dateAdded = dateAdded;
    }

    /**
     * Holds the information about the user who added the Attachment
     */
    private String addedBy;

    /**
     * @return Returns the addedBy.
     */
    public String getAddedBy()
    {
        return addedBy;
    }

    /**
     * @param addedBy
     *            The addedBy to set.
     */
    public void setAddedBy(String addedBy)
    {
        this.addedBy = addedBy;
    }

    /**
     * Description of the attachment
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
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
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Date in which the document is stored at EDMS
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String edmsStoredDate;

    /**
     * @return Returns the edmsStoredDate.
     */
    public String getEdmsStoredDate()
    {
        return edmsStoredDate;
    }

    /**
     * @param edmsStoredDate
     *            The edmsStoredDate to set.
     */
    public void setEdmsStoredDate(String edmsStoredDate)
    {
        this.edmsStoredDate = edmsStoredDate;
    }

    /**
     * holds the page id information retrived from EDMS.
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private Double anEDMSPageId;

    /**
     * @return Returns the anEDMSPageId.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public Double getAnEDMSPageId()
    {
        return anEDMSPageId;
    }

    /**
     * @param theanEDMSPageId
     *            The anEDMSPageId to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAnEDMSPageId(Double theanEDMSPageId)
    {
        anEDMSPageId = theanEDMSPageId;
    }

    /**
     * The fileName along with path
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String fileName;

    /**
     * @return Returns the fileName.
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * @param fileName
     *            The fileName to set.
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Used to store the file contents.
     */
    protected byte[] file1;

    /**
     * @return Returns the file1.
     */
    public byte[] getFile1()
    {
        return file1;
    }

    /**
     * @param file1
     *            The file1 to set.
     */
    public void setFile1(byte[] file1)
    {
        this.file1 = file1;
    }

    /**
     * @return Returns the attachmentSK.
     */
    public Long getAttachmentSK()
    {
        return attachmentSK;
    }

    /**
     * @param attachmentSK The attachmentSK to set.
     */
    public void setAttachmentSK(Long attachmentSK)
    {
        this.attachmentSK = attachmentSK;
    }

    /**
     * @return Returns the attachmentPageID.
     */
    public String getAttachmentPageID()
    {
        return attachmentPageID;
    }

    /**
     * @param attachmentPageID The attachmentPageID to set.
     */
    public void setAttachmentPageID(String attachmentPageID)
    {
        this.attachmentPageID = attachmentPageID;
    }

    /**
     * @return Returns the finalPath.
     */
    public String getFinalPath()
    {
        return finalPath;
    }

    /**
     * @param finalPath The finalPath to set.
     */
    public void setFinalPath(String finalPath)
    {
        this.finalPath = finalPath;
    }

    
    /**
     * @return Returns the attachmentIndicator.
     */
    public String getAttachmentIndicator()
    {
        return attachmentIndicator;
    }

    /**
     * @param attachmentIndicator The attachmentIndicator to set.
     */
    public void setAttachmentIndicator(String attachmentIndicator)
    {
        this.attachmentIndicator = attachmentIndicator;
    }

    /**
     * @return Returns the fileUrl.
     */
    public String getFileUrl()
    {
        return fileUrl;
    }

    /**
     * @param fileUrl The fileUrl to set.
     */
    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }

    /**
     * @return Returns the detachInd.
     */
    public boolean isDetachInd()
    {
        return detachInd;
    }

    /**
     * @param detachInd The detachInd to set.
     */
    public void setDetachInd(boolean detachInd)
    {
        this.detachInd = detachInd;
    }

    /**
     * @return Returns the historyIndicator.
     */
    public boolean isHistoryIndicator()
    {
        return historyIndicator;
    }

    /**
     * @param historyIndicator The historyIndicator to set.
     */
    public void setHistoryIndicator(boolean historyIndicator)
    {
        this.historyIndicator = historyIndicator;
    }
    
	/**
	 * @return Returns the showDeleteForSearched.
	 */
	public boolean isShowDeleteForSearched() {
		return showDeleteForSearched;
	}
	/**
	 * @param showDeleteForSearched The showDeleteForSearched to set.
	 */
	public void setShowDeleteForSearched(boolean showDeleteForSearched) {
		this.showDeleteForSearched = showDeleteForSearched;
	}
	
    /**
     * holds the edmsWrkUnitLevel
     */
    private String edmsWrkUnitLevel;

    /**
     * holds the edmsDocType
     */
    private String edmsDocType;

    /**
     * @return Returns the edmsDocType.
     */
    public String getEdmsDocType()
    {
        return edmsDocType;
    }

    /**
     * @param edmsDocType The edmsDocType to set.
     */
    public void setEdmsDocType(String edmsDocType)
    {
        this.edmsDocType = edmsDocType;
    }

    /**
     * @return Returns the edmsWrkUnitLevel.
     */
    public String getEdmsWrkUnitLevel()
    {
        return edmsWrkUnitLevel;
    }

    /**
     * @param edmsWrkUnitLevel The edmsWrkUnitLevel to set.
     */
    public void setEdmsWrkUnitLevel(String edmsWrkUnitLevel)
    {
        this.edmsWrkUnitLevel = edmsWrkUnitLevel;
    }
       
	/**
	 * @return Returns the cascadeKey.
	 */
	public String getCascadeKey() {
		return cascadeKey;
	}
	/**
	 * @param cascadeKey The cascadeKey to set.
	 */
	public void setCascadeKey(String cascadeKey) {
		this.cascadeKey = cascadeKey;
	}
	
	private boolean existDoc;
	
	/**
	 * @return Returns the existDoc.
	 */
	public boolean isExistDoc() {
		return existDoc;
	}
	/**
	 * @param cascadeKey The existDoc to set.
	 */
	public void setExistDoc(boolean existDoc) {
		this.existDoc = existDoc;
	}

	/**
	 *  caseCrossRefVersionNo holds version no.
	 */
	private int caseCrossRefVersionNo;
	
	/**
	 * @return Returns the caseCrossRefVersionNo.
	 */
	public int getCaseCrossRefVersionNo() {
		return caseCrossRefVersionNo;
	}
	/**
	 * @param caseCrossRefVersionNo.
	 */
	public void setCaseCrossRefVersionNo(int caseCrossRefVersionNo) {
		this.caseCrossRefVersionNo = caseCrossRefVersionNo;
	}
	
	/**for displaying delete link in 
	 * attachment section with out page level save
	 */
	private boolean showDetachAttachment;


	/**
	 * @return the showDetachAttachment
	 */
	public boolean isShowDetachAttachment() {
		return showDetachAttachment;
	}
	/**
	 * @param showDetachAttachment the showDetachAttachment to set
	 */
	public void setShowDetachAttachment(boolean showDetachAttachment) {
		this.showDetachAttachment = showDetachAttachment;
	}

	
}
