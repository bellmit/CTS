/*
 * Created on Dec 10, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;

/**
 * Holds the information about correspondenceRecord
 */
public class CorrespondenceRecordVO
        extends AuditaleEnterpriseBaseVO
        implements Cloneable
{

    /**
     * Constructor for CorrespondenceRecordVO
     */
    public CorrespondenceRecordVO()
    {
        super();
        logger.debug("calling CorrespondenceRecordVO Constructor");
    }

    /** Enterprise Logger for Logging */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(CorrespondenceRecordVO.class);
    
    /**
     * The Correspondence Record Number.
     */
    private String correspondenceRecordNumber;

    /**
     * The no. of days taken to close the cr.
     */
    private String daysToClose;

    /**
     * This is a row-level indicator.
     */
    private String sensitiveDataIndicator;

    /**
     * Indicates if there are any response documents associated with this
     * Correspondence. If yes, they must all be considered "closed" before the
     * correspondence can be closed.
     */
    private String responseExistIndicator;

    /**
     * Indicates if this correspondence includes responses that do not include
     * documents.
     */
    private String responseHasFileIndicator;

    /**
     * Indicates all the associated responses in TBD (fka CORR_DOC) have a
     * Closed status. This improves performance by not having to join to that
     * table to determine this when generating reports.
     */
    private String responseAllClosedIndicator;

    /**
     * The supervisor who reviewed the cr.
     */
    private String sprvsrReviewedWorkUnit;

    /**
     * This field is used to store edmsDocTypeCode.
     */
    private String edmsDocTypeCode;

    /**
     * This field is used to store receivedDate.
     */
    private Date receivedDate;

    /**
     * Holds the reference to correspondenceDetails.
     */
    private CorrespondenceDetailsVO correspondenceDetailsVO = new CorrespondenceDetailsVO();

    /**
     * Holds the reference to correspondenceForVO.
     */
    private CorrespondenceForVO correspondenceForVO = new CorrespondenceForVO();

    /**
     * Holds the reference to correspondenceForProviderVO.
     */
    private CorrespondenceForProviderVO correspondenceForProviderVO = new CorrespondenceForProviderVO();
    
  //ADDED FOR THE Correspondence ESPRD00436016
    /**
     * Holds the reference to correspondenceForTradingPartnerVO.
     */
    private CorrespondenceForTradingPartnerVO correspondenceForTradingPartnerVO = new CorrespondenceForTradingPartnerVO();

    /**
     * Holds the reference to correspondenceForMemberVO.
     */
    private CorrespondenceForMemberVO correspondenceForMemberVO = new CorrespondenceForMemberVO();

    /**
     * Holds the reference to listOfAssociatedCRs.
     */
    private List listOfAssociatedCRs = new ArrayList();

    /**
     * This field is used to store existingCRLists.
     */
    private List existingCRLists = new ArrayList();

    /**
     * Holds the reference to listOfCmRoutingVOs.
     */
    private List listOfCmRoutingVOs = new ArrayList();

    /**
     * Holds the reference to listOfAttachments.
     */
    private List listOfAttachments = new ArrayList();

    /**
     * Holds the reference to listOfLettersAndResponses.
     */
    private List listOfLettersAndResponses = new ArrayList();

    /**
     * Holds the reference to listOfCrAlerts.
     */
    private List listOfCrAlerts = new ArrayList();

    /**
     * Holds the reference to callScriptDesc.
     */
    private String callScriptDesc;

    /**
     * Holds the reference to callScriptSK.
     */
    private String callScriptSK;

    /**
     * This field is used to store callScriptText.
     */
    private String callScriptText;

    /**
     * This method is used to get the correspondenceRecordNumber.
     * 
     * @return String : Returns the correspondenceRecordNumber.
     */
    public String getCorrespondenceRecordNumber()
    {
        return correspondenceRecordNumber;
    }

    /**
     * This method is used to set the correspondenceRecordNumber.
     * 
     * @param correspondenceRecordNumber : The correspondenceRecordNumber to set.
     */
    public void setCorrespondenceRecordNumber(String correspondenceRecordNumber)
    {
        this.correspondenceRecordNumber = correspondenceRecordNumber;
    }

    /**
     * This method is used to get the daysToClose.
     * 
     * @return String : Returns the daysToClose.
     */
    public String getDaysToClose()
    {
        return daysToClose;
    }

    /**
     * This method is used to set the daysToClose.
     * 
     * @param daysToClose : The daysToClose to set.
     */
    public void setDaysToClose(String daysToClose)
    {
        this.daysToClose = daysToClose;
    }

    /**
     * This method is used to get the sensitiveDataIndicator.
     * 
     * @return String : Returns the sensitiveDataIndicator.
     */
    public String getSensitiveDataIndicator()
    {
        return sensitiveDataIndicator;
    }

    /**
     * This method is used to set the sensitiveDataIndicator.
     * 
     * @param sensitiveDataIndicator : The sensitiveDataIndicator to set.
     */
    public void setSensitiveDataIndicator(String sensitiveDataIndicator)
    {
        this.sensitiveDataIndicator = sensitiveDataIndicator;
    }

    /**
     * This method is used to get the responseExistIndicator.
     * 
     * @return String : Returns the responseExistIndicator.
     */
    public String getResponseExistIndicator()
    {
        return responseExistIndicator;
    }

    /**
     * This method is used to set the responseExistIndicator.
     * 
     * @param responseExistIndicator : The responseExistIndicator to set.
     */
    public void setResponseExistIndicator(String responseExistIndicator)
    {
        this.responseExistIndicator = responseExistIndicator;
    }

    /**
     * This method is used to get the responseHasFileIndicator.
     * 
     * @return String : Returns the responseHasFileIndicator.
     */
    public String getResponseHasFileIndicator()
    {
        return responseHasFileIndicator;
    }

    /**
     * This method is used to set the responseHasFileIndicator.
     * 
     * @param responseHasFileIndicator : The responseHasFileIndicator to set.
     */
    public void setResponseHasFileIndicator(String responseHasFileIndicator)
    {
        this.responseHasFileIndicator = responseHasFileIndicator;
    }

    /**
     * This method is used to get the responseAllClosedIndicator.
     * 
     * @return String : Returns the responseAllClosedIndicator.
     */
    public String getResponseAllClosedIndicator()
    {
        return responseAllClosedIndicator;
    }

    /**
     * This method is used to set the responseAllClosedIndicator.
     * 
     * @param responseAllClosedIndicator : The responseAllClosedIndicator to set.
     */
    public void setResponseAllClosedIndicator(String responseAllClosedIndicator)
    {
        this.responseAllClosedIndicator = responseAllClosedIndicator;
    }

    /**
     * This method is used to get the sprvsrReviewedWorkUnit.
     * 
     * @return String : Returns the sprvsrReviewedWorkUnit.
     */
    public String getSprvsrReviewedWorkUnit()
    {
        return sprvsrReviewedWorkUnit;
    }

    /**
     * This method is used to set the sprvsrReviewedWorkUnit.
     * 
     * @param sprvsrReviewedWorkUnit : The sprvsrReviewedWorkUnit to set.
     */
    public void setSprvsrReviewedWorkUnit(String sprvsrReviewedWorkUnit)
    {
        this.sprvsrReviewedWorkUnit = sprvsrReviewedWorkUnit;
    }

    /**
     * This method is used to get the edmsDocTypeCode.
     * 
     * @return String : Returns the edmsDocTypeCode.
     */
    public String getEdmsDocTypeCode()
    {
        return edmsDocTypeCode;
    }

    /**
     * This method is used to set the edmsDocTypeCode.
     * 
     * @param edmsDocTypeCode : The edmsDocTypeCode to set.
     */
    public void setEdmsDocTypeCode(String edmsDocTypeCode)
    {
        this.edmsDocTypeCode = edmsDocTypeCode;
    }

    /**
     * This method is used to get the receivedDate.
     * 
     * @return Date : Returns the receivedDate.
     */
    public Date getReceivedDate()
    {
        return receivedDate;
    }

    /**
     * This method is used to set the receivedDate.
     * 
     * @param receivedDate : The receivedDate to set.
     */
    public void setReceivedDate(Date receivedDate)
    {
        logger.debug("receivedDate");
        this.receivedDate = receivedDate;
    }

    /**
     * This method is used to get the correspondenceDetailsVO.
     * 
     * @return CorrespondenceDetailsVO : Returns the correspondenceDetailsVO.
     */
    public CorrespondenceDetailsVO getCorrespondenceDetailsVO()
    {
        return correspondenceDetailsVO;
    }

    /**
     * This method is used to set the correspondenceDetailsVO.
     * 
     * @param correspondenceDetailsVO : The correspondenceDetailsVO to set.
     */
    public void setCorrespondenceDetailsVO(
            CorrespondenceDetailsVO correspondenceDetailsVO)
    {
        this.correspondenceDetailsVO = correspondenceDetailsVO;
    }

    /**
     * This method is used to get the correspondenceForVO.
     * 
     * @return CorrespondenceForVO : Returns the correspondenceForVO.
     */
    public CorrespondenceForVO getCorrespondenceForVO()
    {
        return correspondenceForVO;
    }

    /**
     * This method is used to set the correspondenceForVO.
     * 
     * @param correspondenceForVO : The correspondenceForVO to set.
     */
    public void setCorrespondenceForVO(CorrespondenceForVO correspondenceForVO)
    {
        this.correspondenceForVO = correspondenceForVO;
    }

    /**
     * This method is used to get the correspondenceForProviderVO.
     * 
     * @return CorrespondenceForProviderVO : Returns the correspondenceForProviderVO.
     */
    public CorrespondenceForProviderVO getCorrespondenceForProviderVO()
    {
        return correspondenceForProviderVO;
    }

    /**
     * This method is used to set the correspondenceForProviderVO.
     * 
     * @param correspondenceForProviderVO : The correspondenceForProviderVO to set.
     */
    public void setCorrespondenceForProviderVO(
            CorrespondenceForProviderVO correspondenceForProviderVO)
    {
        this.correspondenceForProviderVO = correspondenceForProviderVO;
    }

    /**
     * This method is used to get the correspondenceForMemberVO.
     * 
     * @return CorrespondenceForMemberVO : Returns the correspondenceForMemberVO.
     */
    public CorrespondenceForMemberVO getCorrespondenceForMemberVO()
    {
        return correspondenceForMemberVO;
    }

    /**
     * This method is used to set the correspondenceForMemberVO.
     * 
     * @param correspondenceForMemberVO : The correspondenceForMemberVO to set.
     */
    public void setCorrespondenceForMemberVO(
            CorrespondenceForMemberVO correspondenceForMemberVO)
    {
        this.correspondenceForMemberVO = correspondenceForMemberVO;
    }

    /**
     * This method is used to get the listOfAssociatedCRs.
     * 
     * @return List : Returns the listOfAssociatedCRs.
     */
    public List getListOfAssociatedCRs()
    {
        return listOfAssociatedCRs;
    }

    /**
     * This method is used to set the listOfAssociatedCRs.
     * 
     * @param listOfAssociatedCRs : The listOfAssociatedCRs to set.
     */
    public void setListOfAssociatedCRs(List listOfAssociatedCRs)
    {
        this.listOfAssociatedCRs = listOfAssociatedCRs;
    }

    /**
     * This method is used to get the existingCRLists.
     * 
     * @return List : Returns the existingCRLists.
     */
    public List getExistingCRLists()
    {
        return existingCRLists;
    }

    /**
     * This method is used to set the existingCRLists.
     * 
     * @param existingCRLists : The existingCRLists to set.
     */
    public void setExistingCRLists(List existingCRLists)
    {
        this.existingCRLists = existingCRLists;
    }

    /**
     * This method is used to get the listOfCmRoutingVOs.
     * 
     * @return List : Returns the listOfCmRoutingVOs.
     */
    public List getListOfCmRoutingVOs()
    {
        return listOfCmRoutingVOs;
    }

    /**
     * This method is used to set the listOfCmRoutingVOs.
     * 
     * @param listOfCmRoutingVOs : The listOfCmRoutingVOs to set.
     */
    public void setListOfCmRoutingVOs(List listOfCmRoutingVOs)
    {
        this.listOfCmRoutingVOs = listOfCmRoutingVOs;
    }

    /**
     * This method is used to get the listOfAttachments.
     * 
     * @return List : Returns the listOfAttachments.
     */
    public List getListOfAttachments()
    {
        return listOfAttachments;
    }

    /**
     * This method is used to set the listOfAttachments.
     * 
     * @param listOfAttachments : The listOfAttachments to set.
     */
    public void setListOfAttachments(List listOfAttachments)
    {
        this.listOfAttachments = listOfAttachments;
    }

    /**
     * This method is used to get the listOfLettersAndResponses.
     * 
     * @return List : Returns the listOfLettersAndResponses.
     */
    public List getListOfLettersAndResponses()
    {
        return listOfLettersAndResponses;
    }

    /**
     * This method is used to set the listOfLettersAndResponses.
     * 
     * @param listOfLettersAndResponses : The listOfLettersAndResponses to set.
     */
    public void setListOfLettersAndResponses(List listOfLettersAndResponses)
    {
        this.listOfLettersAndResponses = listOfLettersAndResponses;
    }

    /**
     * This method is used to get the listOfCrAlerts.
     * 
     * @return List : Returns the listOfCrAlerts.
     */
    public List getListOfCrAlerts()
    {
        return listOfCrAlerts;
    }

    /**
     * This method is used to set the listOfCrAlerts.
     * 
     * @param listOfCrAlerts : The listOfCrAlerts to set.
     */
    public void setListOfCrAlerts(List listOfCrAlerts)
    {
        this.listOfCrAlerts = listOfCrAlerts;
    }

    /**
     * This method is used to get the callScriptDesc.
     * 
     * @return String : Returns the callScriptDesc.
     */
    public String getCallScriptDesc()
    {
        return callScriptDesc;
    }

    /**
     * This method is used to set the callScriptDesc.
     * 
     * @param callScriptDesc : The callScriptDesc to set.
     */
    public void setCallScriptDesc(String callScriptDesc)
    {
        this.callScriptDesc = callScriptDesc;
    }

    /**
     * This method is used to get the callScriptSK.
     * 
     * @return String : Returns the callScriptSK.
     */
    public String getCallScriptSK()
    {
        return callScriptSK;
    }

    /**
     * This method is used to set the callScriptSK.
     * 
     * @param callScriptSK : The callScriptSK to set.
     */
    public void setCallScriptSK(String callScriptSK)
    {
        this.callScriptSK = callScriptSK;
    }

    /**
     * This method is used to get the callScriptText.
     * 
     * @return String : Returns the callScriptText.
     */
    public String getCallScriptText()
    {
        return callScriptText;
    }

    /**
     * This method is used to set the callScriptText.
     * 
     * @param callScriptText : The callScriptText to set.
     */
    public void setCallScriptText(String callScriptText)
    {
        this.callScriptText = callScriptText;
    }

    /**
     * This method overrides the clone method of the Object Class to clone the
     * CorrespondenceRecordVO.
     * 
     * @return Object : a clone of this instance.
     * @exception CloneNotSupportedException :
     *                CloneNotSupportedException if the object's class does not
     *                support the Cloneable interface. Subclasses that override
     *                the clone method can also throw this exception to indicate
     *                that an instance cannot be cloned.
     * @see java.lang.Cloneable
     */
    public Object clone()
            throws CloneNotSupportedException
    {
        CorrespondenceRecordVO correspondenceRecordVO = (CorrespondenceRecordVO) super
                .clone();

        if (this.listOfAssociatedCRs != null)
        {
            correspondenceRecordVO.setListOfAssociatedCRs(new ArrayList(
                    this.listOfAssociatedCRs));
        }
        if (this.listOfAttachments != null)
        {
            correspondenceRecordVO.setListOfAttachments(new ArrayList(
                    this.listOfAttachments));
        }
        if (this.listOfCmRoutingVOs != null)
        {
            correspondenceRecordVO.setListOfCmRoutingVOs(new ArrayList(
                    this.listOfCmRoutingVOs));
        }
        if (this.listOfCrAlerts != null)
        {
            correspondenceRecordVO.setListOfCrAlerts(new ArrayList(
                    this.listOfCrAlerts));
        }
        if (this.listOfLettersAndResponses != null)
        {
            correspondenceRecordVO.setListOfLettersAndResponses(new ArrayList(
                    this.listOfLettersAndResponses));
        }

        return correspondenceRecordVO;

    }

    /**
     * Holds the reference to correspondenceForMemberVO.
     */
    //commented for unused variables
    //private AssociatedCorrespondenceVO associatedCorrespondenceVO = new AssociatedCorrespondenceVO();

    /**
     * @return Returns the associatedCorrespondenceVO.
     */
/*    public AssociatedCorrespondenceVO getAssociatedCorrespondenceVO()
    {
        return associatedCorrespondenceVO;
    }

    *//**
     * @param associatedCorrespondenceVO The associatedCorrespondenceVO to set.
     *//*
    public void setAssociatedCorrespondenceVO(
            AssociatedCorrespondenceVO associatedCorrespondenceVO)
    {
        this.associatedCorrespondenceVO = associatedCorrespondenceVO;
    }
*/
    /**
	 * @return the correspondenceForTradingPartnerVO
	 */
	public CorrespondenceForTradingPartnerVO getCorrespondenceForTradingPartnerVO() {
		return correspondenceForTradingPartnerVO;
	}

	/**
	 * @param correspondenceForTradingPartnerVO the correspondenceForTradingPartnerVO to set
	 */
	public void setCorrespondenceForTradingPartnerVO(
			CorrespondenceForTradingPartnerVO correspondenceForTradingPartnerVO) {
		this.correspondenceForTradingPartnerVO = correspondenceForTradingPartnerVO;
	}
}
