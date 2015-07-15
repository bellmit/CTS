/*
 * Created on Jul 7, 2008 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * Holds the information about Keyword.
 * 
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class LetterKeywordVO
        extends AuditaleEnterpriseBaseVO
{



    /**
     * Holds the value for keywordId
     */
    private String keywordId;

    /**
     * Holds the value for keywordTypeCode
     */
    private String keywordTypeCode;

    /**
     * Holds the value for keywordLabel
     */
    private String keywordLabel;

    /**
     * Holds the value for keywordShortDesc
     */
    private String keywordShortDesc;

    /**
     * Holds the value for keywordLongDesc
     */
    private String keywordLongDesc;

    /**
     * Holds the value for associatedTemplatesList
     */
    private List associatedTemplatesList;

    /**
     * Default constructor
     */
    public LetterKeywordVO()
    {
        super();
        associatedTemplatesList = new ArrayList();
    }

    /**
     * @return Returns the associatedTemplatesList.
     */
    public List getAssociatedTemplatesList()
    {
        return associatedTemplatesList;
    }

    /**
     * @param associatedTemplatesList
     *            The associatedTemplatesList to set.
     */
    public void setAssociatedTemplatesList(List associatedTemplatesList)
    {
        this.associatedTemplatesList = associatedTemplatesList;
    }

    /**
     * @return Returns the keywordId.
     */
    public String getKeywordId()
    {
        return keywordId;
    }

    /**
     * @param keywordId
     *            The keywordId to set.
     */
    public void setKeywordId(String keywordId)
    {
        this.keywordId = keywordId;
    }

    /**
     * @return Returns the keywordLabel.
     */
    public String getKeywordLabel()
    {
        return keywordLabel;
    }

    /**
     * @param keywordLabel
     *            The keywordLabel to set.
     */
    public void setKeywordLabel(String keywordLabel)
    {
        this.keywordLabel = keywordLabel;
    }

    /**
     * @return Returns the keywordLongDesc.
     */
    public String getKeywordLongDesc()
    {
        return keywordLongDesc;
    }

    /**
     * @param keywordLongDesc
     *            The keywordLongDesc to set.
     */
    public void setKeywordLongDesc(String keywordLongDesc)
    {
        this.keywordLongDesc = keywordLongDesc;
    }

    /**
     * @return Returns the keywordShortDesc.
     */
    public String getKeywordShortDesc()
    {
        return keywordShortDesc;
    }

    /**
     * @param keywordShortDesc
     *            The keywordShortDesc to set.
     */
    public void setKeywordShortDesc(String keywordShortDesc)
    {
        this.keywordShortDesc = keywordShortDesc;
    }

    /**
     * @return Returns the keywordTypeCode.
     */
    public String getKeywordTypeCode()
    {
        return keywordTypeCode;
    }

    /**
     * @param keywordTypeCode
     *            The keywordTypeCode to set.
     */
    public void setKeywordTypeCode(String keywordTypeCode)
    {
        this.keywordTypeCode = keywordTypeCode;
    }

    /**
     * Method to compare by Id
     * @param c : an object of type LetterKeywordVO
     * @return int : result of comparision
     */
    public int compareByKeywordId(LetterKeywordVO c)
    {
        int x = keywordId.compareTo(c.getKeywordId());
        return x;
    }

    /**
     * Method to compare by label
     * @param c : an object of type LetterKeywordVO
     * @return int : result of comparision
     */
    public int compareByLabel(LetterKeywordVO c)
    {
        String current = keywordLabel;
        String passed = c.getKeywordLabel();

        if (current == null) 
        {
            current = "";
        }
        if (passed == null)
        {
            passed = "";
        }
        int x = current.compareTo(passed);
        return x;
    }
    
    private String displaySize;
    
    
	/**
	 * @return Returns the displaySize.
	 */
	public String getDisplaySize() {
		return displaySize;
	}
	/**
	 * @param displaySize The displaySize to set.
	 */
	public void setDisplaySize(String displaySize) {
		this.displaySize = displaySize;
	}
	
	
	 
    /**
     * Holds the value for keyWordTyCd
     */

	private String keyWordDataTyCd;

	public String getKeyWordDataTyCd() {
		return keyWordDataTyCd;
	}

	public void setKeyWordDataTyCd(String keyWordDataTyCd) {
		this.keyWordDataTyCd = keyWordDataTyCd;
	}

	 
}
