/*
 * Created on Jan 17, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.Date;
import java.util.List;

import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author sivngan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminHearingVO extends AuditaleEnterpriseBaseVO
//        extends EnterpriseBaseVO
{

    /** Creates an instance of the logger. * */
    /*private transient EnterpriseLogger log = EnterpriseLogFactory
            .getLogger(getClass().getName());*/
	private static final EnterpriseLogger log = EnterpriseLogFactory
    .getLogger(AdminHearingVO.class);

    /**
     * Constructor AdminHearingVO.
     */
    public AdminHearingVO()
    {
        super();
        log.debug("inside AdminHearingVO constructor");
    }

    /** This holds  adminHearingDate*/
    private Date adminHearingDate;

    /** This holds  strAdminHearingDate*/
    private String strAdminHearingDate;

    /** This holds  hearingResults*/
    private String hearingResults;

    /** This holds  hearingStatus*/
    private String hearingStatus;

    /** This holds  docketNumber*/
    private String docketNumber;

    /** This holds  hearingOfficerName*/
    private String hearingOfficerName;

    /** This holds  hearingCitation*/
    private String hearingCitation;

    /** This holds  caseSK*/
    private String caseSK;
    
    /** This holds Seq Num*/
    private Integer hearingSeqNum;
    
    /** This holds adminNotesetVO*/
    private NoteSetVO adminNotesetVO = new NoteSetVO();

    /**
     * @return Returns the adminHearingDate.
     */
    public Date getAdminHearingDate()
    {
        return adminHearingDate;
    }

    /**
     * @param adminHearingDate
     *            The adminHearingDate to set.
     */
    public void setAdminHearingDate(Date adminHearingDate)
    {
        this.adminHearingDate = adminHearingDate;
    }

    /**
     * @return Returns the docketNumber.
     */
    public String getDocketNumber()
    {
        return docketNumber;
    }

    /**
     * @return Returns the hearingCitation.
     */
    public String getHearingCitation()
    {
        return hearingCitation;
    }

    /**
     * @return Returns the hearingOfficerName.
     */
    public String getHearingOfficerName()
    {
        return hearingOfficerName;
    }

    /**
     * @return Returns the hearingResults.
     */
    public String getHearingResults()
    {
        return hearingResults;
    }

    /**
     * @return Returns the hearingStatus.
     */
    public String getHearingStatus()
    {
        return hearingStatus;
    }

    /**
     * @param docketNumber
     *            The docketNumber to set.
     */
    public void setDocketNumber(String docketNumber)
    {
        this.docketNumber = docketNumber;
    }

    /**
     * @param hearingCitation
     *            The hearingCitation to set.
     */
    public void setHearingCitation(String hearingCitation)
    {
        this.hearingCitation = hearingCitation;
    }

    /**
     * @param hearingOfficerName
     *            The hearingOfficerName to set.
     */
    public void setHearingOfficerName(String hearingOfficerName)
    {
        this.hearingOfficerName = hearingOfficerName;
    }

    /**
     * @param hearingResults
     *            The hearingResults to set.
     */
    public void setHearingResults(String hearingResults)
    {
        this.hearingResults = hearingResults;
    }

    /**
     * @param hearingStatus
     *            The hearingStatus to set.
     */
    public void setHearingStatus(String hearingStatus)
    {
        this.hearingStatus = hearingStatus;
    }

    /**
     * @return Returns the strAdminHearingDate.
     */
    public String getStrAdminHearingDate()
    {
        return strAdminHearingDate;
    }

    /**
     * @param strAdminHearingDate
     *            The strAdminHearingDate to set.
     */
    public void setStrAdminHearingDate(String strAdminHearingDate)
    {
        this.strAdminHearingDate = strAdminHearingDate;
    }

    /**
     * @return Returns the caseSK.
     */
    public String getCaseSK()
    {
        return caseSK;
    }

    /**
     * @param caseSK The caseSK to set.
     */
    public void setCaseSK(String caseSK)
    {
        log.debug("caseSK");
        this.caseSK = caseSK;
    }
    /**
     * @return Returns the hearingSeqNum.
     */
    public Integer getHearingSeqNum()
    {
        return hearingSeqNum;
    }
    /**
     * @param hearingSeqNum The hearingSeqNum to set.
     */
    public void setHearingSeqNum(Integer hearingSeqNum)
    {
        this.hearingSeqNum = hearingSeqNum;
    }

    /**
     * @return Returns the adminNotesetVO.
     */
    public NoteSetVO getAdminNotesetVO()
    {
        return adminNotesetVO;
    }
    /**
     * @param adminNotesetVO The adminNotesetVO to set.
     */
    public void setAdminNotesetVO(NoteSetVO adminNotesetVO)
    {
        this.adminNotesetVO = adminNotesetVO;
    }
    
    //ESPRD00514325_UC-PGM-CRM-067_26AUG2010
    /** This holds  hearingResults short/long description*/
    private String hearingResultsDesc;

    /** This holds  hearingStatus short/long description*/
    private String hearingStatusDesc;
	/**
	 * @return Returns the hearingResultsDesc.
	 */
	public String getHearingResultsDesc() {
		return hearingResultsDesc;
	}
	/**
	 * @param hearingResultsDesc The hearingResultsDesc to set.
	 */
	public void setHearingResultsDesc(String hearingResultsDesc) {
		this.hearingResultsDesc = hearingResultsDesc;
	}
	/**
	 * @return Returns the hearingStatusDesc.
	 */
	public String getHearingStatusDesc() {
		return hearingStatusDesc;
	}
	/**
	 * @param hearingStatusDesc The hearingStatusDesc to set.
	 */
	public void setHearingStatusDesc(String hearingStatusDesc) {
		this.hearingStatusDesc = hearingStatusDesc;
	}
	//EOF ESPRD00514325_UC-PGM-CRM-067_26AUG2010
	
	//for ESPRD00782216 defect.
	private List noteList;

	/**
	 * @return the noteList
	 */
	public List getNoteList() {
		return noteList;
	}

	/**
	 * @param noteList the noteList to set
	 */
	public void setNoteList(List noteList) {
		this.noteList = noteList;
	}
	
	
}
