/*
 * Created on Jan 17, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.List;
import java.util.Set;

//import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;

/**
 * @author sivngan TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class AppealVO
        extends AuditaleEnterpriseBaseVO//EnterpriseBaseVO //Modified for CR ESPRD00373565 AuditLog 
{
    /**
     * Holds AdminHearingVO
     */
    private AdminHearingVO adminHearingVO = new AdminHearingVO();

    /**
     * Holds adminHearingList
     */
    private List adminHearingList;

    /**
     * holds CaseSK
     */

    private String caseSK;

    /**
     * Holds case Appeal type Code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String caseAplTyCd;

    /**
     * Holds Appeal type Code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String aplTyCd;

    /**
     * Holds Case Appeal Continuance reason code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String caseAplContinuanceRsnCd;

    /**
     * Holds Appeal reviewer name
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String revwrNam;

    /**
     * Holds assign Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String asgnDt;

    /**
     * Holds Appeal
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String prevAplNum;

    /**
     * Holds Appeal date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String aplDt;

    /**
     * Holds case Appeal results code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String caseAplRsltsCd;

    /**
     * Holds Case Appeal Status code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String caseAplStatCd;

    /**
     * Holds Case Appeal district office code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String caseAplDstctOfcCd;

    /**
     * Holds claim number
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String tcnNum;

    /**
     * Holds authourization ID
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String authID;

    /**
     * Holds Appeal Status code Update Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String caseAplStatCdDt;

    /**
     * Holds Appeal informal review requested date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String infrmlRevwReqDt;

    /**
     * Holds Appeal informal review Acknowledgement Sent Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String infrmlRevwAckSentDt;

    /**
     * Holds Appeal informal review Sent for Review Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String infrmlRevwSentDt;

    /**
     * Holds Appeal informal review due date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String infrmlRevwDueDt;

    /**
     * Holds Appeals additional information like Requested Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoReqDt;

    /**
     * Holds Appeals additional information like Revised Review Due
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoRvsdRevwDueDt;

    /**
     * Holds Appeals additional information like Notification Letter Sent Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoNotfyLtrSentDt;

    /**
     * Holds Appeals additional information like Received second Request Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoRecd2NdReqDt;

    /**
     * Holds Appeals additional information like second Revised Due Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfo2NdRvsdDueDt;

    /**
     * Holds Appeals additional information like AAU Officer name
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoAAUOfficerNam;

    /**
     * Holds Appeals additional information like Response Requested Due date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoRespReqDueDt;

    /**
     * Holds Appeals additional information like Motion Type
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoMotionTyCd;

    /**
     * Holds Appeals additional information like Filed Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoFiledDt;

    /**
     * Holds Appeals additional information like Case File Location
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoFileLocnCd;

    /**
     * Holds Appeals additional information like Client Representative
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoClientRepNam;

    /**
     * Holds Appeal DHHS sent date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String dhhsSentDt;

    /**
     * Holds Appeal DHHS Decision Due date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String dhhsDecisDueDt;

    /**
     * Holds Appeal DHHS Decision code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String dhhsDecisCd;

    /**
     * Holds Appeal DHHS Notification Letter Sent Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String dhhsNotfyLtrSentDt;

    /**
     * Holds Appeal Reconsideration information like Sent Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String rcnsdrtnSentDt;

    /**
     * Holds Appeal Reconsideration information like Returned Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String rcnsdrtnRtrnDt;

    /**
     * Holds Appeal Reconsideration information like Reviewer Name
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String rcnsdrtnRevwrNam;

    /**
     * Holds Appeal Reconsideration information like Decision code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String rcnsdrtnDecisCd;

    /**
     * Holds Appeal Reconsideration information like Decision Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String rcnsdrtnDecisDt;

    /**
     * Holds Appeal Reconsideration information like Notification Letter Sent
     * Date
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String rcnsdrtnNotfyLtrSentDt;

    /**
     * Comment for <code>commonEntityType</code>
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private CommonEntity commonEntityType;

    /**
     * Holds Case Appeal display code
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String caseAplDispCd;

    /**
     * Comment for <code>caserecord</code>
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private Set caserecord;

    /**
     * Comment for <code>addlInfoDueDt</code>
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoDueDt;

    /**
     * Comment for <code>addlInfoRecdDt</code>
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String addlInfoRecdDt;
    
    /**
     * Comment for <code>addlInfoRecdDt</code>
     * 
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private String claimStatusCode;

    
    /**
     * Holds CommonEntityTypeCode information like Member or Provider
     * Added for defect 412572
     */
    private String commonEntityTypeCode;
    /**
     * @return Returns the caseAplTyCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCaseAplTyCd()
    {
        return caseAplTyCd;
    }

    /**
     * @param thecaseAplTyCd
     *            The caseAplTyCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCaseAplTyCd(final String thecaseAplTyCd)
    {
        caseAplTyCd = thecaseAplTyCd;
    }

    /**
     * @return Returns the aplTyCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAplTyCd()
    {
        return aplTyCd;
    }

    /**
     * @param theaplTyCd
     *            The aplTyCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAplTyCd(final String theaplTyCd)
    {
        aplTyCd = theaplTyCd;
    }

    /**
     * @return Returns the caseAplContinuanceRsnCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCaseAplContinuanceRsnCd()
    {
        return caseAplContinuanceRsnCd;
    }

    /**
     * @param thecaseAplContinuanceRsnCd
     *            The caseAplContinuanceRsnCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCaseAplContinuanceRsnCd(
            final String thecaseAplContinuanceRsnCd)
    {
        caseAplContinuanceRsnCd = thecaseAplContinuanceRsnCd;
    }

    /**
     * @return Returns the revwrNam.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getRevwrNam()
    {
        return revwrNam;
    }

    /**
     * @param therevwrNam
     *            The revwrNam to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setRevwrNam(final String therevwrNam)
    {
        revwrNam = therevwrNam;
    }

    /**
     * @return Returns the asgnDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAsgnDt()
    {
        return asgnDt;
    }

    /**
     * @param theasgnDt
     *            The asgnDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAsgnDt(final String theasgnDt)
    {
        asgnDt = theasgnDt;
    }

    /**
     * @return Returns the prevAplNum.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getPrevAplNum()
    {
        return prevAplNum;
    }

    /**
     * @param theprevAplNum
     *            The prevAplNum to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setPrevAplNum(final String theprevAplNum)
    {
        prevAplNum = theprevAplNum;
    }

    /**
     * @return Returns the aplDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAplDt()
    {
        return aplDt;
    }

    /**
     * @param theaplDt
     *            The aplDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAplDt(final String theaplDt)
    {
        aplDt = theaplDt;
    }

    /**
     * @return Returns the caseAplRsltsCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCaseAplRsltsCd()
    {
        return caseAplRsltsCd;
    }

    /**
     * @param thecaseAplRsltsCd
     *            The caseAplRsltsCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCaseAplRsltsCd(final String thecaseAplRsltsCd)
    {
        caseAplRsltsCd = thecaseAplRsltsCd;
    }

    /**
     * @return Returns the caseAplStatCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCaseAplStatCd()
    {
        return caseAplStatCd;
    }

    /**
     * @param thecaseAplStatCd
     *            The caseAplStatCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCaseAplStatCd(final String thecaseAplStatCd)
    {
        caseAplStatCd = thecaseAplStatCd;
    }

    /**
     * @return Returns the caseAplStatCdDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCaseAplStatCdDt()
    {
        return caseAplStatCdDt;
    }

    /**
     * @param thecaseAplStatCdDt
     *            The caseAplStatCdDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCaseAplStatCdDt(final String thecaseAplStatCdDt)
    {
        caseAplStatCdDt = thecaseAplStatCdDt;
    }

    /**
     * @return Returns the caseAplDstctOfcCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCaseAplDstctOfcCd()
    {
        return caseAplDstctOfcCd;
    }

    /**
     * @param thecaseAplDstctOfcCd
     *            The caseAplDstctOfcCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCaseAplDstctOfcCd(final String thecaseAplDstctOfcCd)
    {
        caseAplDstctOfcCd = thecaseAplDstctOfcCd;
    }

    /**
     * @return Returns the tcnNum.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getTcnNum()
    {
        return tcnNum;
    }

    /**
     * @param thetcnNum
     *            The tcnNum to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setTcnNum(final String thetcnNum)
    {
        tcnNum = thetcnNum;
    }

    /**
     * @return Returns the authID.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAuthID()
    {
        return authID;
    }

    /**
     * @param theauthID
     *            The authID to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAuthID(final String theauthID)
    {
        authID = theauthID;
    }

    /**
     * @return Returns the infrmlRevwReqDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getInfrmlRevwReqDt()
    {
        return infrmlRevwReqDt;
    }

    /**
     * @param theinfrmlRevwReqDt
     *            The infrmlRevwReqDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setInfrmlRevwReqDt(final String theinfrmlRevwReqDt)
    {
        infrmlRevwReqDt = theinfrmlRevwReqDt;
    }

    /**
     * @return Returns the infrmlRevwAckSentDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getInfrmlRevwAckSentDt()
    {
        return infrmlRevwAckSentDt;
    }

    /**
     * @param theinfrmlRevwAckSentDt
     *            The infrmlRevwAckSentDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setInfrmlRevwAckSentDt(final String theinfrmlRevwAckSentDt)
    {
        infrmlRevwAckSentDt = theinfrmlRevwAckSentDt;
    }

    /**
     * @return Returns the infrmlRevwSentDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getInfrmlRevwSentDt()
    {
        return infrmlRevwSentDt;
    }

    /**
     * @param theinfrmlRevwSentDt
     *            The infrmlRevwSentDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setInfrmlRevwSentDt(final String theinfrmlRevwSentDt)
    {
        infrmlRevwSentDt = theinfrmlRevwSentDt;
    }

    /**
     * @return Returns the infrmlRevwDueDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getInfrmlRevwDueDt()
    {
        return infrmlRevwDueDt;
    }

    /**
     * @param theinfrmlRevwDueDt
     *            The infrmlRevwDueDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setInfrmlRevwDueDt(final String theinfrmlRevwDueDt)
    {
        infrmlRevwDueDt = theinfrmlRevwDueDt;
    }

    /**
     * @return Returns the addlInfoReqDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoReqDt()
    {
        return addlInfoReqDt;
    }

    /**
     * @param theaddlInfoReqDt
     *            The addlInfoReqDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoReqDt(final String theaddlInfoReqDt)
    {
        addlInfoReqDt = theaddlInfoReqDt;
    }

    /**
     * @return Returns the addlInfoDueDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoDueDt()
    {
        return addlInfoDueDt;
    }

    /**
     * @param theaddlInfoDueDt
     *            The addlInfoDueDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoDueDt(final String theaddlInfoDueDt)
    {
        addlInfoDueDt = theaddlInfoDueDt;
    }

    /**
     * @return Returns the addlInfoRecdDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoRecdDt()
    {
        return addlInfoRecdDt;
    }

    /**
     * @param theaddlInfoRecdDt
     *            The addlInfoRecdDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoRecdDt(final String theaddlInfoRecdDt)
    {
        addlInfoRecdDt = theaddlInfoRecdDt;
    }

    /**
     * @return Returns the addlInfoRvsdRevwDueDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoRvsdRevwDueDt()
    {
        return addlInfoRvsdRevwDueDt;
    }

    /**
     * @param theaddlInfoRvsdRevwDueDt
     *            The addlInfoRvsdRevwDueDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoRvsdRevwDueDt(final String theaddlInfoRvsdRevwDueDt)
    {
        addlInfoRvsdRevwDueDt = theaddlInfoRvsdRevwDueDt;
    }

    /**
     * @return Returns the addlInfoNotfyLtrSentDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoNotfyLtrSentDt()
    {
        return addlInfoNotfyLtrSentDt;
    }

    /**
     * @param theaddlInfoNotfyLtrSentDt
     *            The addlInfoNotfyLtrSentDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoNotfyLtrSentDt(final String theaddlInfoNotfyLtrSentDt)
    {
        addlInfoNotfyLtrSentDt = theaddlInfoNotfyLtrSentDt;
    }

    /**
     * @return Returns the addlInfoRecd2NdReqDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoRecd2NdReqDt()
    {
        return addlInfoRecd2NdReqDt;
    }

    /**
     * @param theaddlInfoRecd2NdReqDt
     *            The addlInfoRecd2NdReqDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoRecd2NdReqDt(final String theaddlInfoRecd2NdReqDt)
    {
        addlInfoRecd2NdReqDt = theaddlInfoRecd2NdReqDt;
    }

    /**
     * @return Returns the addlInfo2NdRvsdDueDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfo2NdRvsdDueDt()
    {
        return addlInfo2NdRvsdDueDt;
    }

    /**
     * @param theaddlInfo2NdRvsdDueDt
     *            The addlInfo2NdRvsdDueDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfo2NdRvsdDueDt(final String theaddlInfo2NdRvsdDueDt)
    {
        addlInfo2NdRvsdDueDt = theaddlInfo2NdRvsdDueDt;
    }

    /**
     * @return Returns the addlInfoAAUOfficerNam.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoAAUOfficerNam()
    {
        return addlInfoAAUOfficerNam;
    }

    /**
     * @param theaddlInfoAAUOfficerNam
     *            The addlInfoAAUOfficerNam to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoAAUOfficerNam(final String theaddlInfoAAUOfficerNam)
    {
        addlInfoAAUOfficerNam = theaddlInfoAAUOfficerNam;
    }

    /**
     * @return Returns the addlInfoRespReqDueDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoRespReqDueDt()
    {
        return addlInfoRespReqDueDt;
    }

    /**
     * @param theaddlInfoRespReqDueDt
     *            The addlInfoRespReqDueDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoRespReqDueDt(final String theaddlInfoRespReqDueDt)
    {
        addlInfoRespReqDueDt = theaddlInfoRespReqDueDt;
    }

    /**
     * @return Returns the addlInfoMotionTyCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoMotionTyCd()
    {
        return addlInfoMotionTyCd;
    }

    /**
     * @param theaddlInfoMotionTyCd
     *            The addlInfoMotionTyCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoMotionTyCd(final String theaddlInfoMotionTyCd)
    {
        addlInfoMotionTyCd = theaddlInfoMotionTyCd;
    }

    /**
     * @return Returns the addlInfoFiledDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoFiledDt()
    {
        return addlInfoFiledDt;
    }

    /**
     * @param theaddlInfoFiledDt
     *            The addlInfoFiledDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoFiledDt(final String theaddlInfoFiledDt)
    {
        addlInfoFiledDt = theaddlInfoFiledDt;
    }

    /**
     * @return Returns the addlInfoFileLocnCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoFileLocnCd()
    {
        return addlInfoFileLocnCd;
    }

    /**
     * @param theaddlInfoFileLocnCd
     *            The addlInfoFileLocnCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoFileLocnCd(final String theaddlInfoFileLocnCd)
    {
        addlInfoFileLocnCd = theaddlInfoFileLocnCd;
    }

    /**
     * @return Returns the addlInfoClientRepNam.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getAddlInfoClientRepNam()
    {
        return addlInfoClientRepNam;
    }

    /**
     * @param theaddlInfoClientRepNam
     *            The addlInfoClientRepNam to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setAddlInfoClientRepNam(final String theaddlInfoClientRepNam)
    {
        addlInfoClientRepNam = theaddlInfoClientRepNam;
    }

    /**
     * @return Returns the dhhsSentDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getDhhsSentDt()
    {
        return dhhsSentDt;
    }

    /**
     * @param thedhhsSentDt
     *            The dhhsSentDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setDhhsSentDt(final String thedhhsSentDt)
    {
        dhhsSentDt = thedhhsSentDt;
    }

    /**
     * @return Returns the dhhsDecisDueDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getDhhsDecisDueDt()
    {
        return dhhsDecisDueDt;
    }

    /**
     * @param thedhhsDecisDueDt
     *            The dhhsDecisDueDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setDhhsDecisDueDt(final String thedhhsDecisDueDt)
    {
        dhhsDecisDueDt = thedhhsDecisDueDt;
    }

    /**
     * @return Returns the dhhsDecisCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getDhhsDecisCd()
    {
        return dhhsDecisCd;
    }

    /**
     * @param thedhhsDecisCd
     *            The dhhsDecisCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setDhhsDecisCd(final String thedhhsDecisCd)
    {
        dhhsDecisCd = thedhhsDecisCd;
    }

    /**
     * @return Returns the dhhsNotfyLtrSentDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getDhhsNotfyLtrSentDt()
    {
        return dhhsNotfyLtrSentDt;
    }

    /**
     * @param thedhhsNotfyLtrSentDt
     *            The dhhsNotfyLtrSentDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setDhhsNotfyLtrSentDt(final String thedhhsNotfyLtrSentDt)
    {
        dhhsNotfyLtrSentDt = thedhhsNotfyLtrSentDt;
    }

    /**
     * @return Returns the rcnsdrtnSentDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getRcnsdrtnSentDt()
    {
        return rcnsdrtnSentDt;
    }

    /**
     * @param thercnsdrtnSentDt
     *            The rcnsdrtnSentDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setRcnsdrtnSentDt(final String thercnsdrtnSentDt)
    {
        rcnsdrtnSentDt = thercnsdrtnSentDt;
    }

    /**
     * @return Returns the rcnsdrtnRtrnDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getRcnsdrtnRtrnDt()
    {
        return rcnsdrtnRtrnDt;
    }

    /**
     * @param thercnsdrtnRtrnDt
     *            The rcnsdrtnRtrnDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setRcnsdrtnRtrnDt(final String thercnsdrtnRtrnDt)
    {
        rcnsdrtnRtrnDt = thercnsdrtnRtrnDt;
    }

    /**
     * @return Returns the rcnsdrtnRevwrNam.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getRcnsdrtnRevwrNam()
    {
        return rcnsdrtnRevwrNam;
    }

    /**
     * @param thercnsdrtnRevwrNam
     *            The rcnsdrtnRevwrNam to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setRcnsdrtnRevwrNam(final String thercnsdrtnRevwrNam)
    {
        rcnsdrtnRevwrNam = thercnsdrtnRevwrNam;
    }

    /**
     * @return Returns the rcnsdrtnDecisCd.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getRcnsdrtnDecisCd()
    {
        return rcnsdrtnDecisCd;
    }

    /**
     * @param thercnsdrtnDecisCd
     *            The rcnsdrtnDecisCd to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setRcnsdrtnDecisCd(final String thercnsdrtnDecisCd)
    {
        rcnsdrtnDecisCd = thercnsdrtnDecisCd;
    }

    /**
     * @return Returns the rcnsdrtnDecisDt.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getRcnsdrtnDecisDt()
    {
        return rcnsdrtnDecisDt;
    }

    /**
     * @param thercnsdrtnDecisDt
     *            The rcnsdrtnDecisDt to set.
     * @generated "UML to Java
     *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setRcnsdrtnDecisDt(final String thercnsdrtnDecisDt)
    {
        rcnsdrtnDecisDt = thercnsdrtnDecisDt;
    }

    /**
     * @return Returns the rcnsdrtnNotfyLtrSentDt.
     * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getRcnsdrtnNotfyLtrSentDt()
    {
        return rcnsdrtnNotfyLtrSentDt;
    }

    /**
     * @param thercnsdrtnNotfyLtrSentDt The rcnsdrtnNotfyLtrSentDt to set.
     * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setRcnsdrtnNotfyLtrSentDt(final String thercnsdrtnNotfyLtrSentDt)
    {
        rcnsdrtnNotfyLtrSentDt = thercnsdrtnNotfyLtrSentDt;
    }

    /**
     * @return Returns the commonEntityType.
     * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public CommonEntity getCommonEntityType()
    {
        return commonEntityType;
    }

    /**
     * @param thecommonEntityType The commonEntityType to set.
     * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCommonEntityType(final CommonEntity thecommonEntityType)
    {
        commonEntityType = thecommonEntityType;
    }

    /**
     * @return Returns the caseAplDispCd.
     * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public String getCaseAplDispCd()
    {
        return caseAplDispCd;
    }

    /**
     * @param thecaseAplDispCd The caseAplDispCd to set.
     * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCaseAplDispCd(final String thecaseAplDispCd)
    {
        caseAplDispCd = thecaseAplDispCd;
    }

    /**
     * @return Returns the caserecord.
     * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public Set getCaserecord()
    {
        return caserecord;
    }

    /**
     * @param thecaserecord The caserecord to set.
     * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    public void setCaserecord(final Set thecaserecord)
    {
        caserecord = thecaserecord;
    }

    /**
     * @return Returns the adminHearingVO.
     */
    public AdminHearingVO getAdminHearingVO()
    {
        return adminHearingVO;
    }

    /**
     * @param adminHearingVO The adminHearingVO to set.
     */
    public void setAdminHearingVO(final AdminHearingVO adminHearingVO)
    {
        this.adminHearingVO = adminHearingVO;
    }

    /**
     * @return Returns the adminHearingList.
     */
    public List getAdminHearingList()
    {
        return adminHearingList;
    }

    /**
     * @param adminHearingList The adminHearingList to set.
     */
    public void setAdminHearingList(final List adminHearingList)
    {
        this.adminHearingList = adminHearingList;
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
    public void setCaseSK(final String caseSK)
    {
        this.caseSK = caseSK;
    }    
    
    /**
     * @return Returns the claimStatusCode.
     */
    public String getClaimStatusCode()
    {
        return claimStatusCode;
    }
    /**
     * @param claimStatusCode The claimStatusCode to set.
     */
    public void setClaimStatusCode(String claimStatusCode)
    {
        this.claimStatusCode = claimStatusCode;
    }
    
//  ADDED BY ICS GAP-11022 & CR-1622
    
    private List listSALineItemsVO;
    
	/**
	 * @return Returns the listSALineItemsVO.
	 */
	public List getListSALineItemsVO() {
		return listSALineItemsVO;
	}
	/**
	 * @param listSALineItemsVO The listSALineItemsVO to set.
	 */
	public void setListSALineItemsVO(List listSALineItemsVO) {
		this.listSALineItemsVO = listSALineItemsVO;
	}
	
	/**
     * For Service Authorization Line Number
     */
    private String saTyLinNum;
    
    /**
     * For Service Authorization Type Code
     */
    private String saTyCd;
    
    /**
     * For Service Authorization From Code
     */
    
    private String saTyFrmCd;
    
    /**
     * For Service Authorization To Code
     */
    
    private String saTyToCd;
    
    /**
     * For Service Authorization Requested Begin Date
     */
    
    private String saTyReqBegDt;
    
    /**
     * For Service Authorization Requested End Date
     */
    
    private String saTyReqEndDt;
	
    private Double reqAmount;
    
    private Double reqUnits;
    
    private Double reqRate;
    
    
    private String saLineAppStatus;
    
    private String saLineAppResult;
    
    private Boolean include;
	
	/**
	 * @return Returns the include.
	 */
	public Boolean getInclude() {
		return include;
	}
	/**
	 * @param include The include to set.
	 */
	public void setInclude(Boolean include) {
		this.include = include;
	}
    /**
	 * @return Returns the saTyCd.
	 */
	public String getSaTyCd() {
		return saTyCd;
	}
	/**
	 * @param saTyCd The saTyCd to set.
	 */
	public void setSaTyCd(String saTyCd) {
		this.saTyCd = saTyCd;
	}
	/**
	 * @return Returns the saTyFrmCd.
	 */
	public String getSaTyFrmCd() {
		return saTyFrmCd;
	}
	/**
	 * @param saTyFrmCd The saTyFrmCd to set.
	 */
	public void setSaTyFrmCd(String saTyFrmCd) {
		this.saTyFrmCd = saTyFrmCd;
	}
	/**
	 * @return Returns the saTyLinNum.
	 */
	public String getSaTyLinNum() {
		return saTyLinNum;
	}
	/**
	 * @param saTyLinNum The saTyLinNum to set.
	 */
	public void setSaTyLinNum(String saTyLinNum) {
		this.saTyLinNum = saTyLinNum;
	}
	/**
	 * @return Returns the saTyReqBegDt.
	 */
	public String getSaTyReqBegDt() {
		return saTyReqBegDt;
	}
	/**
	 * @param saTyReqBegDt The saTyReqBegDt to set.
	 */
	public void setSaTyReqBegDt(String saTyReqBegDt) {
		this.saTyReqBegDt = saTyReqBegDt;
	}
	/**
	 * @return Returns the saTyReqEndDt.
	 */
	public String getSaTyReqEndDt() {
		return saTyReqEndDt;
	}
	/**
	 * @param saTyReqEndDt The saTyReqEndDt to set.
	 */
	public void setSaTyReqEndDt(String saTyReqEndDt) {
		this.saTyReqEndDt = saTyReqEndDt;
	}
	/**
	 * @return Returns the saTyToCd.
	 */
	public String getSaTyToCd() {
		return saTyToCd;
	}
	/**
	 * @param saTyToCd The saTyToCd to set.
	 */
	public void setSaTyToCd(String saTyToCd) {
		this.saTyToCd = saTyToCd;
	}
	
	/**
	 * @return Returns the reqAmount.
	 */
	public Double getReqAmount() {
		return reqAmount;
	}
	/**
	 * @param reqAmount The reqAmount to set.
	 */
	public void setReqAmount(Double reqAmount) {
		this.reqAmount = reqAmount;
	}
	/**
	 * @return Returns the reqRate.
	 */
	public Double getReqRate() {
		return reqRate;
	}
	/**
	 * @param reqRate The reqRate to set.
	 */
	public void setReqRate(Double reqRate) {
		this.reqRate = reqRate;
	}
	/**
	 * @return Returns the reqUnits.
	 */
	public Double getReqUnits() {
		return reqUnits;
	}
	/**
	 * @param reqUnits The reqUnits to set.
	 */
	public void setReqUnits(Double reqUnits) {
		this.reqUnits = reqUnits;
	}
	
	
	
	/**
	 * @return Returns the saLineAppResult.
	 */
	public String getSaLineAppResult() {
		return saLineAppResult;
	}
	/**
	 * @param saLineAppResult The saLineAppResult to set.
	 */
	public void setSaLineAppResult(String saLineAppResult) {
		this.saLineAppResult = saLineAppResult;
	}
	/**
	 * @return Returns the saLineAppStatus.
	 */
	public String getSaLineAppStatus() {
		return saLineAppStatus;
	}
	/**
	 * @param saLineAppStatus The saLineAppStatus to set.
	 */
	public void setSaLineAppStatus(String saLineAppStatus) {
		this.saLineAppStatus = saLineAppStatus;
	}
	
//END BY ICS GAP-11022 & CR 1622
	
    
    /**
     * @return Returns the commonEntityTypeCode.
     */
    public String getCommonEntityTypeCode() {
        return commonEntityTypeCode;
    }
    /**
     * @param commonEntityTypeCode The commonEntityTypeCode to set.
     */
    public void setCommonEntityTypeCode(String commonEntityTypeCode) {
        this.commonEntityTypeCode = commonEntityTypeCode;
    }
    
    /** for BR CON0387
     * */
    private String previousStatus;
	/**
	 * @return the previousStatus
	 */
	public String getPreviousStatus() {
		return previousStatus;
	}

	/**
	 * @param previousStatus the previousStatus to set
	 */
	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}
    
}
