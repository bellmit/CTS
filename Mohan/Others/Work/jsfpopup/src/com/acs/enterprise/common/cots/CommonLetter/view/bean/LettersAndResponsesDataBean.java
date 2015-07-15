/*
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.cots.CommonLetter.view.bean;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.faces.component.html.HtmlDataTable;
import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.cots.lettergeneration.vo.LetterGenerationInputVO;


/**
 * @author WIPRO This is Data Bean
 */
public class LettersAndResponsesDataBean
        extends EnterpriseBaseDataBean
{
    /**
     * Constructor
     */
    public LettersAndResponsesDataBean()
    {
        super();
    }

    /**
     * Holds the list of all Letter Requests
     */
    private List letterGenerationRequests;

    /**
     * Holds a particular selected VO
     */

    private LetterGenerationInputVO letterGenerationInputVO;

    /**
     * This field is used to store showSucessMessage.
     */
    private boolean showSucessMessage = false;


    /**
     * This field holds the renderEditTemplate to control display of edit
     * section
     */
    
    /**
     * Below two  fields added for right click issue.
     */
    private String createLetter;
    
    private String createLetterCase;
  
   
	public String getCreateLetterCase() {
		return createLetterCase;
	}

	public void setCreateLetterCase(String createLetterCase) {
		this.createLetterCase = createLetterCase;
	}

	

	public String getCreateLetter() {
		return createLetter;
	}

	public void setCreateLetter(String createLetter) {
		this.createLetter = createLetter;
	}
    private boolean renderEditSection = false;   

	/**
     * This field is used for rendering the sorting images.
     */
    private String imageRender;    
    
    /**
	 * Added as a part of Defect ESPRD00796178 ,Right Click Issue of 'View
	 * Letter Request' link of case and Correspondence.
	 */
	private String viewLetterReq;

    /**
     * This field is used to store letter template data table binding
     */

//    private transient HtmlDataTable htmlDataTable = new HtmlDataTable();

    /**
     * Holds the selected row index in UI
     */
    private int selectedLetterRequestRowIndex;

    /**
     * Holds the list of Alert to notify
     */
    private List notifyViaAlert = new ArrayList();

    /**
     * Holds the list Alert is based on
     */

    private List alertBasedOn = null;

    /**
     * Holds the list for Number of Days
     */

    private List alertNumberOfDays = null;

    /**
     * Holds the letter request list
     */

    private List letterRequestList = null;    

    /** Holds the functional Area */
    private String funcArea = null;

    /**
     * Holds the functional SK
     */
    private Long funcSK = null;

    /**
     * Holds the letterCategory
     */
    private String letterCategory = null;

    /** Holds user Departments */
    private Hashtable userDepartments = new Hashtable();

    /** Holds department users */
    private List departmentUsers = null;

    /**
     * @return Returns the alertBasedOn.
     */
    public List getAlertBasedOn()
    {
        return alertBasedOn;
    }

    /**
     * @param alertBasedOn
     *            The alertBasedOn to set.
     */
    public void setAlertBasedOn(List alertBasedOn)
    {
        this.alertBasedOn = alertBasedOn;
    }

    /**
     * @return Returns the alertNumberOfDays.
     */
    public List getAlertNumberOfDays()
    {
        return alertNumberOfDays;
    }

    /**
     * @param alertNumberOfDays
     *            The alertNumberOfDays to set.
     */
    public void setAlertNumberOfDays(List alertNumberOfDays)
    {
        this.alertNumberOfDays = alertNumberOfDays;
    }

    /**
     * @return Returns the imageRender.
     */
    public String getImageRender()
    {
        return imageRender;
    }

    /**
     * @param imageRender
     *            The imageRender to set.
     */
    public void setImageRender(String imageRender)
    {
        this.imageRender = imageRender;
    }

    /**
     * @return Returns the letterGenerationInputVO.
     */
    public LetterGenerationInputVO getLetterGenerationInputVO()
    {
        return letterGenerationInputVO;
    }

    /**
     * @param letterGenerationInputVO
     *            The letterGenerationInputVO to set.
     */
    public void setLetterGenerationInputVO(
            LetterGenerationInputVO letterGenerationInputVO)
    {
        this.letterGenerationInputVO = letterGenerationInputVO;
    }

    /**
     * @return Returns the letterGenerationRequests.
     */
    public List getLetterGenerationRequests()
    {
        return letterGenerationRequests;
    }

    /**
     * @param letterGenerationRequests
     *            The letterGenerationRequests to set.
     */
    public void setLetterGenerationRequests(List letterGenerationRequests)
    {
        this.letterGenerationRequests = letterGenerationRequests;
    }

    /**
     * @return Returns the notifyViaAlert.
     */
    public List getNotifyViaAlert()
    {
        return notifyViaAlert;
    }

    /**
     * @param notifyViaAlert
     *            The notifyViaAlert to set.
     */
    public void setNotifyViaAlert(List notifyViaAlert)
    {
        this.notifyViaAlert = notifyViaAlert;
    }

    /**
     * @return Returns the renderEditSection.
     */
    public boolean isRenderEditSection()
    {
        return renderEditSection;
    }

    /**
     * @param renderEditSection
     *            The renderEditSection to set.
     */
    public void setRenderEditSection(boolean renderEditSection)
    {
        this.renderEditSection = renderEditSection;
    }

    /**
     * @return Returns the selectedLetterRequestRowIndex.
     */
    public int getSelectedLetterRequestRowIndex()
    {
        return selectedLetterRequestRowIndex;
    }

    /**
     * @param selectedLetterRequestRowIndex
     *            The selectedLetterRequestRowIndex to set.
     */
    public void setSelectedLetterRequestRowIndex(
            int selectedLetterRequestRowIndex)
    {
        this.selectedLetterRequestRowIndex = selectedLetterRequestRowIndex;
    }

    /**
     * @return Returns the showSucessMessage.
     */
    public boolean isShowSucessMessage()
    {
        return showSucessMessage;
    }

    /**
     * @param showSucessMessage
     *            The showSucessMessage to set.
     */
    public void setShowSucessMessage(boolean showSucessMessage)
    {
        this.showSucessMessage = showSucessMessage;
    }

    /**
     * @return Returns the htmlDataTable.
     */
    /*public HtmlDataTable getHtmlDataTable()
    {
        return htmlDataTable;
    }*/

    /**
     * @param htmlDataTable
     *            The htmlDataTable to set.
     */
    /*public void setHtmlDataTable(HtmlDataTable htmlDataTable)
    {
        this.htmlDataTable = htmlDataTable;
    }*/

    /**
     * @return Returns the letterRequestList.
     */
    public List getLetterRequestList()
    {
        return letterRequestList;
    }

    /**
     * @param letterRequestList
     *            The letterRequestList to set.
     */
    public void setLetterRequestList(List letterRequestList)
    {
        this.letterRequestList = letterRequestList;
    }

    /**
     * @return Returns the funcArea.
     */
    public String getFuncArea()
    {
        return funcArea;
    }

    /**
     * @param funcArea
     *            The funcArea to set.
     */
    public void setFuncArea(String funcArea)
    {
        this.funcArea = funcArea;
    }

    /**
     * @return Returns the funcSK.
     */
    public Long getFuncSK()
    {
        return funcSK;
    }

    /**
     * @param funcSK
     *            The funcSK to set.
     */
    public void setFuncSK(Long funcSK)
    {
        this.funcSK = funcSK;
    }

    /**
     * @return Returns the departmentUsers.
     */
    public List getDepartmentUsers()
    {
        return departmentUsers;
    }

    /**
     * @param departmentUsers
     *            The departmentUsers to set.
     */
    public void setDepartmentUsers(List departmentUsers)
    {
        this.departmentUsers = departmentUsers;
    }

    /**
     * @return Returns the userDepartments.
     */
    public Hashtable getUserDepartments()
    {
        return userDepartments;
    }

    /**
     * @param userDepartments
     *            The userDepartments to set.
     */
    public void setUserDepartments(Hashtable userDepartments)
    {
        this.userDepartments = userDepartments;
    }

    /**
     * @return Returns the letterCategory.
     */
    public String getLetterCategory()
    {
        return letterCategory;
    }

    /**
     * @param letterCategory
     *            The letterCategory to set.
     */
    public void setLetterCategory(String letterCategory)
    {
        this.letterCategory = letterCategory;
    }

    /**
	 * This field is used to store duedateoffsetnumRender.
	 */
	private boolean duedateoffsetnumRender = true;

	/**
	 * @return Returns the duedateoffsetnumRender.
	 */
	public boolean isDuedateoffsetnumRender() {
		return duedateoffsetnumRender;
	}
	/**
	 * @param duedateoffsetnumRender The duedateoffsetnumRender to set.
	 */
	public void setDuedateoffsetnumRender(boolean duedateoffsetnumRender) {
		this.duedateoffsetnumRender = duedateoffsetnumRender;
	}

	private boolean sortLetterAndRespFlag = false;
	/**
	 * @return Returns the sortLetterAndRespFlag.
	 */
	public boolean isSortLetterAndRespFlag() {
		return sortLetterAndRespFlag;
	}
	/**
	 * @param sortLetterAndRespFlag The sortLetterAndRespFlag to set.
	 */
	public void setSortLetterAndRespFlag(boolean sortLetterAndRespFlag) {
		this.sortLetterAndRespFlag = sortLetterAndRespFlag;
	}

	private boolean updateLtrAndRespFlag = false ;
	/**
	 * @return Returns the updateLtrAndRespFlag.
	 */
	public boolean isUpdateLtrAndRespFlag() {
		return updateLtrAndRespFlag;
	}
	/**
	 * @param updateLtrAndRespFlag The updateLtrAndRespFlag to set.
	 */
	public void setUpdateLtrAndRespFlag(boolean updateLtrAndRespFlag) {
		this.updateLtrAndRespFlag = updateLtrAndRespFlag;
	}
	private String selectedIndex;
	/**
	 * @return Returns the selectedIndex.
	 */
	public String getSelectedIndex() {
		return selectedIndex;
	}
	/**
	 * @param selectedIndex The selectedIndex to set.
	 */
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	private boolean accessForLGRFlag = false ;

	public boolean isAccessForLGRFlag() {
		return accessForLGRFlag;
	}

	public void setAccessForLGRFlag(boolean accessForLGRFlag) {
		this.accessForLGRFlag = accessForLGRFlag;
	}
	
	/** Holds the first attribute value in DataTable tag for Letters and Responses.
	 * */
	private int lettersRowIndex;

	/**
	 * @return the lettersRowIndex
	 */
	public int getLettersRowIndex() {
		return lettersRowIndex;
	}

	/**
	 * @param lettersRowIndex the lettersRowIndex to set
	 */
	public void setLettersRowIndex(int lettersRowIndex) {
		this.lettersRowIndex = lettersRowIndex;
	}
	
	/**
	 * @return the viewLetterReq
	 */
	public String getViewLetterReq() {
		return viewLetterReq;
	}

	/**
	 * @param viewLetterReq the viewLetterReq to set
	 */
	public void setViewLetterReq(String viewLetterReq) {
		this.viewLetterReq = viewLetterReq;
	}
	
	private String preView;
	
	private boolean createLtrCase;


	/**
	 * @return the preView
	 */
	public String getPreView() {
		return preView;
	}

	/**
	 * @param preView the preView to set
	 */
	public void setPreView(String preView) {
		this.preView = preView;
	}

	/**
	 * @return the createLtrCase
	 */
	public boolean isCreateLtrCase() {
		return createLtrCase;
	}

	/**
	 * @param createLtrCase the createLtrCase to set
	 */
	public void setCreateLtrCase(boolean createLtrCase) {
		this.createLtrCase = createLtrCase;
	}
}

