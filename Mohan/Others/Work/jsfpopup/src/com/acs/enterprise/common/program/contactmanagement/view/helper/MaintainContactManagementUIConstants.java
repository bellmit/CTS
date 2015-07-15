/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

/**
 * This class contains all the Constants used in ContactManagementPortlet
 * project
 * 
 * @author wipro
 */
public interface MaintainContactManagementUIConstants {
    /** This variable holds the value for the integer one.* */
    int ONE = 1;

    /** This variable holds the value for the integer two.* */
    int TWO = 2;

    /** This variable holds the value for the integer three.* */
    int THREE = 3;

    /** This variable holds the value for the integer four.* */
    int FOUR = 4;

    /** This variable holds the value for the integer five.* */
    int FIVE = 5;

    /** This variable holds the value for the integer six.* */
    int SIX = 6;

    /** This variable holds the value for the integer seven.* */
    int SEVEN = 7;

    /** This variable holds the value for the integer eight.* */
    int EIGHT = 8;

    /** This variable holds the value for the integer nine.* */
    int NINE = 9;

    /** This variable holds the value for the integer ten.* */
    int TEN = 10;

    /** This variable holds the value for the integer Eleven.* */
    int ELEVEN = 11;

    /** This variable holds the value for the integer zero.* */
    int ZERO = 0;

    /** This variable holds the value for the integer SixtyFour.* */
    int SIXTY_FOUR = 64;

    /** This variable holds the value for the String zero.* */
    String STR_ZERO = "0";

    /** holds the plus String */
    String PLUS = "plus";

    /** holds the Log Case Bean Name */
    String LOG_CASE_BEAN_NAME = "logCaseDataBean";

    /** holds the String SSN */
    String SSN = "SSN";

    /** holds the \n */
    String BACKSLAHN = "\n";

    /** This variable holds the value for the string four.* */
    String STR_FOUR = "DD";

    /** This variable holds the value for alpha D */
    String D = "D";

    /** This holds the ColumnName */
    String CLOUMN_NAME = "columnName";

    /** This holds the SortOrder */
    String SORT_ORDER = "sortOrder";

    /** This holds the success */
    String SUCCESS = "success";

    /** It holds the constant for ascending* */
    String SORT_TYPE_ASC = "asc";

    /** It holds the constant for droupdown list item description */
    String DD_ITEM_DESC = "ddItemDesc";

    /**
     * It holds the constant for droupdown list sort order
     */
    String DD_SORT_ORDER = "ddSortOrder";

    /** This holds the empty space */
    String NULL = "";

    /** It holds the constant for Add action* */
    String ADD = "Add";

    /** It holds the constant for Update action* */
    String UPDATE = "Update";

    /** holds the default login user ID */
    String DEFAULT_LOGIN_USERID = "TBOINAPALLY";

       
    /** holds AssgnRecTo  */
    String ASSIGNEDTO = "assgnRecTo";
  
    /** holds Assigned To  */
    String WORKUNITNAME = "useDept";
    
    /**
     * component based ids for jsp errormessags in custom felds
     */
    /** holds add dropdown desc code ID */
    String ADD_DD_DESC_ID = "add_DD";

    /** holds edit Drop Down */
    String EDIT_DD_DESC_ID = "edit_DD";

    /** holds add sort order */
    String ADD_SORT_ORDER_ID = "add_SortOrder";

    /** holds edit sort order */
    String EDIT_SORT_ORDER_ID = "edit_SortOrder";

    /** It holds the initial array size* */
    int INITIAL_ARRAY_SIZE = 10;

    /** This holds the added audit user id */
    String ADDED_AUDIT_USER_ID = "NH";

    /** This holds the audit user id */
    String AUDIT_USER_ID = "NH";

    /** holds dropdown required message*/
    String DD_DESC_REQ = "error.DropDownDescRequired";

    /** holds error message if the char length is morethan 63*/
    String DD_DESC_CHAR = "error.DropDownDescChar";

    /** holds error message for droupdown description length*/
    String DD_DESC_LENGTH = "error.DropDownDescLength";

    /** holds error message for sort order required*/
    String SORT_ORDER_REQ = "error.SortOrderRequired";

    /** holds error message for sort order numeric*/
    String SORT_ORDER_NUMERIC = "error.SortOrderNumeric";

    /** holds error message for sort order length*/
    String SORT_ORDER_LENGTH = "error.SortOrderLength";
    
    /** holds Assign This Record To  */
    String ASSIGNEDTOREQ = "error.assignThisRecordTo";
    
    /** holds the error key for caseType */
    String WORKUNITNAME_REQUIRED = "error.logCase.workUnitNameRequried";
    
    /** Holds error mesg for Appeal saLineItems */
    String SA_LINE_ITEMS_REQ = "error.saLineItem.Needstobeselected"; 
    
    /** Holds error mesg for Appeal Status */
	String APPEAL_STATUS_CHECK = "error.saLineItem.appealStatusCheck";

	/** Holds error mesg for Appeal Results */
	String APPEAL_RESULTS_CHECK = "error.saLineItem.appealResultsCheck";

	/** Holds Constant for Appeal Status */
	String APPEAL_STATUS = "appealDetails_AplStatus";

	/** Holds Constant for Appeal Result */
	String APPEAL_RESULT = "saLineAppResult_list";

    /** Holds Constant for Appeal saLineItems */  
    String SA_LINE_ITEMS = "saLineitems";

    /** This holds the failure */
    String FAILURE = "failure";

    /** This holds default date attribute */
    long DEFAULT_DATE = 12 / 31 / 9999;

    /** holds success message on save*/
    String SAVE_SUCCESS_MSG = "success.saved";
/** ADDED FOR THE CR_ESPRD00249998 */
    
    String ADDENTITY_AGAIN ="9-3210-0455";

	 

    /** holds delete message*/
    String DEL_SUCCESS_MSG = "success.deleted";

    /**
     * Properties file path for jsp pages.
     */
    String MAINTAIN_CM_UI_MESSAGES = "com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactManagementMessages";

    /**
     * ################# Below constants are used in Custom Fields
     * #################
     */

    /** holds the field desc */
    String CUSTOMFIELD_COL_DESC = "ColumnDescription";

    /** holds the error Key for Column Description */
    String COLUMN_DESC_REQ = "error.ColumnDescriptionReq";

    /** holds the error Key for Column Description should be unique */
    String UNIQUE_DESC = "9-3210-0045";

    /** holds the error Key for Column Description should be unique */
    String DELETE_RECORD_FAIL = "9-3210-0375";

    /** holds the error Key for Column Description */
    String COLUMN_DESC_CHAR = "error.ColumnDescriptionChar";

    /** holds the error Key for Column Selection */
    String SELECT_COLUMN = "error.Columselect";

    /** holds the error Key for Column Description */
    String COLUMN_DESC_LENTH = "error.ColumnDescriptionLenth";

    /** holds the error Key for Data Type */
    String DATATYPE_REQ = "error.DataTypeReq";

    /** holds the error Key for Data Type */
    String DROPDOWN_REQ = "9-3210-0050";

    /** holds the error Key for Data Type */
    String Mess_Bundl = "com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactManagementMessages";

    /** holds the error Key for Lenght */
    String LENGTH_REQ = "error.LenghtReq";

    /** holds the error Key for Lenth should be numric */
    String LENGTH_NUMRIC = "error.LengthNumric";

    /** holds the error Key for Lenth should not be decreased */
    String LENGTH_DEC = "error.LengthDecreased";

    /** holds the error Key for Lenth should be numric */
    String LENGTH = "error.LargeNumber";

    /** holds the error Key for Scope */
    String SCOPE_REQ = "error.ScopeReq";

    /** holds the field to Add Column Desctiption */
    String ADD_COLUMN_DESC_ID = "ColumnDesc";

    /** holds the field Edit Column Desc */
    String EDIT_COLUMN_DESC_ID = "ColumnDesc";

    /** holds the field Add Data Tyep */
    String ADD_DATATYPE_ID = "DataTypes";

    /** holds the field Edit Data Type */
    String EDIT_DATATYPE_ID = "DataTypes";

    /** holds the field Add Length */
    String ADD_LENGTH_ID = "Length";

    /** holds the field Edit Length */
    String EDIT_LENGTH_ID = "Length";

    /** holds the field add Scope */
    String ADD_SCOPE_ID = "Scope";

    /** holds the field Edit Scope */
    String EDIT_SCOPE_ID = "Scope";

    /** holds Empty value */
    String DATATYPE = "DataType";

    /** holds Empty value */
    String SCOPE = "scope";

    /** holds Empty value */
    String EMPTY_STRING = "";

    /** holds the emplty String with space */
    String EMPTY_STRING_SPACE = " ";

    /** holds DESCRIPTION value */
    String DESCRIPTION = "Description";

    /** holds Render value */
    String RENDER_SUCCESS = "success";

    /** holds Default value */
    int DEFAULT_SIZE = 50;

    /** holds EntityId Required information */
    String ENTITY_ID_VAL = "error.entiryID"; 
    
    /** holds Assigned to Required information */
    String ASSIGNED_TO_REQ = "error.assignedTo"; 
    
    /** holds Assigned To  */
    String ASSIGNED_TO = "Assigned To";
    
    /** holds Assigned To  */
    String ASSIGNED_TO_FROM_UI = "CaseSearchAssignedTo";
    
    /** holds EntityId Required information */
    String ENTITY_TYPE_REQ = "error.entiryTypeReq";

    /** holds EntityId Required information */
    String ENTITY_ID_REQ = "error.entiryIDReq";

    /** holds CaseRecordNumber Required */
    String CASE_NUMBER_REQ = "error.caseRecordNumber";

    /** holds Add EntityId value */
    String ADD_ENTITY_ID = "EntityID";

    /** holds Edit EntityId value */
    String EDIT_ENTITY_ID = "EntityID";

    /** holds Add CaseRecordNumber value */
    String ENTITY_TYPE = "CaseSearchEntityType";

    /** holds Edit CaseRecordNumber value */
    String ADD_CASE_NUMBER = "CaseRecordNumber";

    /** holds Edit CaseRecordNumber value */
    String EDIT_CASE_NUMBER = "CaseRecordNumber";

    /** holds Edit CaseTitle value */
    String CASE_TITLE_VAL = "error.caseTitle";

    /** holds Edit StatusDate value */
    String STATUS_DATE_VAL = "error.statusDate";

    /** holds Edit FromDate value */
    String FROM_DATE_VAL = "error.fromDate";

    /** holds Edit ToDate value */
    String TO_DATE_VAL = "error.toDate";

    /** holds Edit ToDate compare with FromDate value */
    String TO_DATE_COM = "error.toDateCom";

    /** holds Edit StatusDate compare with From Date value */
    String STATUS_FRDATE_COM = "error.StatusFromDateCom";

    /** holds Edit StatusDate compare with From Date value */
    String STATUS_TODATE_COM = "error.StatusToDateCom";

    /** holds Edit EntityId value */
    String CASE_TITLE = "caseTitle";

    /** holds Edit EntityId value */
    String STATUS_DATE = "CaseSearchStatusDate";

    /** holds Edit EntityId value */
    String FROM_DATE = "CaseSearchCreatedFrom";

    /** holds Edit EntityId value */
    String TO_DATE = "CaseSearchCreatedTo";

    /**
     * ******************appeal constants******************
     */

    /** holds constant for component C */
    String COMP_C = "C";

    /** holds constant for component SA */
    String COMP_SA = "SA";

    /** holds constant for component M */
    String ENT_TYPE_M = "M";

    /** holds constant for component P */
    String ENT_TYPE_P = "P";
    
    /** holds constant for component o*/
    String ENT_TYPE_O = "O";
    
    

    /** holds column value adminHrngDt for sorting */
    String ADMN_HRNG_DT = "adminHrngDt";

    /** holds column value hrngRslts for sorting */
    String HRNG_RSLTS = "hrngRslts";

    /** holds column value hrngSts for sorting */
    String HRNG_STS = "hrngSts";

    /** holds column value hrngOffNm for sorting */
    String HRNG_OFF_NM = "hrngOffNm";

    /** holds column value dcktNm for sorting */
    String DOCKT_NM = "dcktNm";

    /** holds error message for INVALID_ADMIN_HRNG_DT */
    String INVALID_ADMIN_HRNG_DT = "error.InvalidAdminHearingDt";

    /** holds error message for CASE_APL_TYP_CD_ID_RQD */
    String CASE_APL_TYP_CD_ID_RQD = "error.AplTypCdReqd";

    /** holds error message for CASE_APPEAL_TYP_CD_ID_RQD */
    String CASE_APPEAL_TYP_CD_ID_RQD = "error.AppealTypeCodeReqd";
    /** holds error message for APPEAL_DTL_TCN_NUMBER_RQD */
    String APPEAL_DTL_TCN_NUMBER_RQD = "error.ClaimTCNNumberReqd";
    /** holds error message for APPEAL_DTL_TCN_NUMBER_INCRRCT */
    String APPEAL_DTL_TCN_NUMBER_INCRRCT = "error.ClaimTCNNumberIncrrct";

    /** holds error message for PREV_APL_NUM_RQD */
    String PREV_APL_NUM_RQD = "error.PrevAplNumReqd";

    /** holds error message for SAVE_FAILURE */
    String SAVE_FAILURE = "error.SaveFailure";

    /** holds error message for INVALID_APL_DTL_ASGN_DT */
    String INVALID_APL_DTL_ASGN_DT = "error.InvalidAssignedDt";

    /** holds error message for INVALID_APL_DTL_APL_DT */
    String INVALID_APL_DTL_APL_DT = "error.InvalidAppealdDt";

    /** holds error message for INVALID_CASE_APL_STAT_CD_DT */
    String INVALID_CASE_APL_STAT_CD_DT = "error.InvalidAppealStatCdDt";

    /** holds error message for INVALID_REQ_DT */
    String INVALID_REQ_DT = "error.InvalidReqDt";

    /** holds error message for INVALID_ACK_SNT_DT */
    String INVALID_ACK_SNT_DT = "error.InvalidAckSentDt";

    /** holds error message for INVALID_SNT_REV_DT */
    String INVALID_SNT_REV_DT = "error.InvalidSentRevDt";

    /** holds error message for INVALID_DUE_DT */
    String INVALID_DUE_DT = "error.InvalidDueDt";

    /** holds error message for INVALID_ADL_INFO_REQ_DT */
    String INVALID_ADL_INFO_REQ_DT = "error.InvalidAdlInfoRqDt";

    /** holds error message for INVALID_ADL_INFO_DUE_DT */
    String INVALID_ADL_INFO_DUE_DT = "error.InvalidAdlInfoDueDt";

    /** holds error message for INVALID_ADL_INFO_RECD_DT */
    String INVALID_ADL_INFO_RECD_DT = "error.InvalidAdlInfoRecdDt";

    /** holds error message for INVALID_ADL_INFO_REV_DUE_DT */
    String INVALID_ADL_INFO_REV_DUE_DT = "error.InvalidAdlInfoRevDuedDt";

    /** holds error message for INVALID_ADL_INFO_NOTIFY_LTR_SNT_DT */
    String INVALID_ADL_INFO_NOTIFY_LTR_SNT_DT = "error.InvalidAdlInfoNotifyLtrSntDt";

    /** holds error message for INVALID_ADL_INFO_SCND_REV_DUE_DT */
    String INVALID_ADL_INFO_SCND_REV_DUE_DT = "error.InvalidAdlInfoScndRevDueDt";

    /** holds error message for INVALID_ADL_INFO_RCD_SCND_REQ_DT */
    String INVALID_ADL_INFO_RCD_SCND_REQ_DT = "error.InvalidAdlInfoRcvdScndReqDt";

    /** holds error message for INVALID_ADL_INFO_RSP_REQ_DT */
    String INVALID_ADL_INFO_RSP_REQ_DT = "error.InvalidAdlInfoRspReqDt";

    /** holds error message for INVALID_ADL_INFO_DT_MOT_DT */
    String INVALID_ADL_INFO_DT_MOT_DT = "error.InvalidAdlInfoDtMotDt";

    /** holds error message for INVALID_SNT_TO_DHHS_DT */
    String INVALID_SNT_TO_DHHS_DT = "error.InvalidSentToDHHSDt";

    /** holds error message for INVALID_DHHS_DEC_DUE_DT */
    String INVALID_DHHS_DEC_DUE_DT = "error.InvalidDHHSDecDt";

    /** holds error message for INVALID_DHHS_NOTY_LTR_SNT_DT */
    String INVALID_DHHS_NOTY_LTR_SNT_DT = "error.InvalidDHHSNotyLtrSntDt";

    /** holds error message for INVALID_RECONS_SNT_DT */
    String INVALID_RECONS_SNT_DT = "error.InvalidReconsSntDt";

    /** holds error message for INVALID_RECONS_REUTRN_DT */
    String INVALID_RECONS_REUTRN_DT = "error.InvalidReconsRtrnDt";

    /** holds error message for INVALID_RECONS_DEC_DT */
    String INVALID_RECONS_DEC_DT = "error.InvalidReconsDecDt";

    /** holds error message for INVALID_RECONS_NOTY_LTR_SNT_DT */
    String INVALID_RECONS_NOTY_LTR_SNT_DT = "error.InvalidReconsNotyLtrSntDt";

    /** holds  message for ADD_RECONS_SNT_DUE */
    String ADD_RECONS_SNT_DUE = "reconsider_SentDt";

    /** holds  add message for ADD_RECONS_RTN_DT */
    String ADD_RECONS_RTN_DT = "reconsider_ReturndDt";

    /** holds add message for ADD_RECONS_DEC_DT */
    String ADD_RECONS_DEC_DT = "reconsider_DecDt";

    /** holds add message for ADD_RCONS_NOTY_SNT_DT */
    String ADD_RCONS_NOTY_SNT_DT = "reconsider_NotLetterSntDt";

    /** holds add message for ADD_DHHS_DEC_DUE */
    String ADD_DHHS_DEC_DUE = "DHHS_DHHSDecDue";

    /** holds add message for ADD_DHHS_NOTY_SNT_DT */
    String ADD_DHHS_NOTY_SNT_DT = "DHHS_NotificSentDt";

    /** add error message for ADD_DHHS_SNT_TO_DHHS_DT */
    String ADD_DHHS_SNT_TO_DHHS_DT = "DHHS_SentToDHHSDt";

    /** holds add message for ADD_ADL_INFO_DT_MOT_DT */
    String ADD_ADL_INFO_DT_MOT_DT = "adlInfo_DtMotFail";

    /** holds add message for ADD_ADL_INFO_RSP_REQ_DT */
    String ADD_ADL_INFO_RSP_REQ_DT = "adlInfo_ResReqBy";

    /** holds add message for ADD_ADL_INFO_RCD_SCND_REQ_DT */
    String ADD_ADL_INFO_RCD_SCND_REQ_DT = "adlInfo_Rcvd2ndReqDt";

    /** holds add message for ADD_ADL_INFO_SCND_REV_DUE_DT */
    String ADD_ADL_INFO_SCND_REV_DUE_DT = "adlInfo_2ndRevDueDt";

    /** holds add message for ADD_ADL_INFO_NOTIFY_LTR_SNT_DT */
    String ADD_ADL_INFO_NOTIFY_LTR_SNT_DT = "adlInfo_NotifyLtrSntDt";

    /** holds add message for ADD_ADL_INFO_REV_DUE_DT */
    String ADD_ADL_INFO_REV_DUE_DT = "adlInfo_RevisedRevDue";

    /** holds add message for ADD_ADL_INFO_RECD_DT */
    String ADD_ADL_INFO_RECD_DT = "adlInfo_RcvdDt";

    /** holds add message for ADD_ADL_INFO_DUE_DT */
    String ADD_ADL_INFO_DUE_DT = "adlInfo_DueDt";

    /** holds add message for ADD_ADL_INFO_REQ_DT */
    String ADD_ADL_INFO_REQ_DT = "adlInfo_ReqDt";

    /** holds add message for ADD_INF_REV_DUE_DT */
    String ADD_INF_REV_DUE_DT = "infReview_DueDt";

    /** holds add message for ADD_INF_REV_SENT_REV_DT */
    String ADD_INF_REV_SENT_REV_DT = "infReview_SntReviewDt";

    /** holds edit message for EDIT_INF_REV_SENT_REV_DT */
    String EDIT_INF_REV_SENT_REV_DT = "infReview_SntReviewDt";

    /** holds add message for ADD_INF_REV_ACK_SENT_DT */
    String ADD_INF_REV_ACK_SENT_DT = "infReview_AckDt";

    /** holds edit message for EDIT_INF_REV_ACK_SENT_DT */
    String EDIT_INF_REV_ACK_SENT_DT = "infReview_AckDt";

    /** holds add message for ADD_APL_ST_UPDT_DT */
    String ADD_APL_ST_UPDT_DT = "appealDetails_AplStUpdtDate";

    /** holds edit message for EDIT_APL_ST_UPDT_DT */
    String EDIT_APL_ST_UPDT_DT = "appealDetails_AplStUpdtDate";

    /** holds add message for ADD_INF_REV_REQ_DT */
    String ADD_INF_REV_REQ_DT = "infReview_ReqDt";

    /** holds edit message for EDIT_INF_REV_REQ_DT */
    String EDIT_INF_REV_REQ_DT = "infReview_ReqDt";

    /** holds add message for ADD_APL_DT_ID */
    String ADD_APL_DT_ID = "appealDetails_AplDate";

    /** holds edit message for EDIT_APL_DT_ID */
    String EDIT_APL_DT_ID = "appealDetails_AplDate";

    /** holds add message for ADD_ASGN_DT_ID */
    String ADD_ASGN_DT_ID = "appealDetails_AsgnDate";

    /** holds edit message for EDIT_ASGN_DT_ID */
    String EDIT_ASGN_DT_ID = "appealDetails_AsgnDate";

    /** holds add AdminHrngDT */
    String ADD_ADMN_HRNG_DT_ID = "add_AdminHrngDT";

    /** holds edit AdminHrngDT */
    String EDIT_ADMN_HRNG_DT_ID = "edit_AdminHrngDT";

    /** holds AppealDetails_Type */
    String APL_TYPE_CD_ID = "appealDetails_Type";

    /** holds AppealDetails_AppType */
    String APPEAL_TYPE_CD_ID = "appealDetails_AppType";

    /** holds AppealDetails_APL_PRV_NUM_ID */
    String APL_PRV_NUM_ID = "appealDetails_PANumber";

    /** holds Addtional Info AAUOfficer */
    String ADDINFO_AAU_OFFICER_ID = "adlInfo_AAUOfficer";

    /**
     * holds constant for MemberAppealsSearch parameter of Member Appeals
     * portlet
     */
    String MEM_APPLS_SRCH = "MemberAppealsSearch";

    /** holds constant for AddAppealDetails parameter of Add Appeals portlet */
    String ADD_APL_DET = "AddAppealDetails";

    /** holds constant for ProviderAppeals parameter of Provider Appeals portlet */
    String PROV_APLS = "ProviderAppeals";

    /** holds constant for requestScope */
    String REQ_SCOPE = "#{requestScope}";

    /** holds constant for systemID */
    String SYS_ID = "systemID";

    /** holds constant for CMMemberSummary */
    String CM_MEM_SUMMARY = "CMMemberSummary";

    /** holds constant for HASH */
    String HASH = "#";

    /** holds constant for HYPHEN */
    String HYPHEN = "-";

    /** holds constant for receive.AddAppealDetails.Action */
    String REC_ADD_APL_DTL_ACT = "receive.AddAppealDetails.Action";

    /** holds constant for receiveAddAppealDetailsAction */
    String REC_ADD_APL_DTL_ACT_NAME = "receiveAddAppealDetailsAction";

    /** holds constant for receiveProviderAppealsAction */
    String REC_PROV_APL_ACT_NAME = "receiveProviderAppealsAction";

    /** holds constant for com.ibm.faces.portlet.page.view */
    String COM_IBM_FACES_PORT_PG_VIEW = "com.ibm.faces.portlet.page.view";

    /** holds constant for com.ibm.portal.propertybroker.action */
    String COM_IBM_PORT_PROPBROK_ACT = "com.ibm.portal.propertybroker.action";

    /** holds constant for receive.ProviderAppeals.Action */
    String REC_PROV_APLS_ACT = "receive.ProviderAppeals.Action";

    /** holds constant for receiveMemberAppealsSearchAction */
    String REC_MEM_APL_SRCH_ACT = "receiveMemberAppealsSearchAction";

    /** holds constant for Member Add Appeal portlet title */
    String MEM_ADD_APL_TITLE = "Member Add Appeal";

    /** holds constant for Provider Add Appeal portlet title */
    String PROV_ADD_APL_TITLE = "Provider Add Appeal";

    /** holds constant for Other Entity Add Appeal portlet title */
    String OTHER_ADD_APL_TITLE = "Other Entity Add Appeal";

    
    /** holds constant for Provider Add Appeals JSP path */
    String ADD_APLS_JSP_PATH = "/jsp/appeals/appealDetails.jsp";

    /** holds constant for Member Appeals JSP path */
    String MEM_APLS_JSP_PATH = "/jsp/appeals/memberAppeals.jsp";

    /** holds constant for Provider Appeals JSP path */
    String PROV_APLS_JSP_PATH = "/jsp/appeals/providerAppeals.jsp";

    /** holds constant for sessionObject */
    String SES_OBJ = "sessionObject";

    /** Holds error mesg for Appeal reviewname */
    String INVALID_APL_REVIEW_NAME = "error.invalid.appeal.revwname";

    /** Holds error mesg for Appeal Reconsideration reviewname */
    String INVALID_APL_RECONSIDERATION_REVIEW_NAME = "error.invalid.appeal.reconsider.revwname";

    /** Holds error mesg for Appeal Additional Info_AAUOfficer */
    String INVALID_ADDITIONAL_INFO_AAU_OFFICER_NAME = "error.invalid.appeal.addInfoAAUOfficer";

    /** Holds Constant for reviewer name */
    String APL_REVIEW_NAME = "appealDetails_RName";

    /** Holds Constant for Reconsider_Reviewer name */
    String APL_RECONSIDER_REVIEW_NAME = "reconsider_RevName";

    /** Holds constatn for ClientrepName */
    String ADDNL_CLIENT_REP = "adlInfo_ClientRep";

    /** String HOlds ErrorMsg for ClientrepName */
    String INVALID_ADDNL_CLIENT_REP = "error.invalid.addnl.clientrep";

    /** String Holds ErrorMessage for Previous Appeal Number */
    String INVALID_PREV_APPL_NUMBER = "error.invalid.prevoius.Appeal.Number";

    /** String to holds constant for invalid PreVious Appl num */
    String APPL_PREV_NUMBER = "appealDetails_PANumber";

    /** String to holds constant for invalid SA number */
    String INVALID_SA_NUMBER = "error.saDetailNumber.invalied";

    /** String to holds constant for invalid SA_NUMBER_BLANK */
    String INVALID_SA_NUMBER_BLANK = "error.saDetailsNumber.blank";

    /** String to holds constant for APL_SA_NUMBERE*/
    String APL_SA_NUMBERE = "appealDetails_SANumber";

    /** String to holds error message for INVALID_CLAIM_NUMBER_BLANK */
    String INVALID_CLAIM_NUMBER_BLANK = "error.claimNumber.blank";

    /** String to holds error message for INVALID_CLAIM_NUMBER */
    String INVALID_CLAIM_NUMBER = "error.claimNumber";

    /** String to holds constant for APL_CLAIM_NUMBERE */
    String APL_CLAIM_NUMBERE = "appealDetails_Claim";

    /** String to holds error message for VALID_CLAIM_NUMBER */
    String VALID_CLAIM_NUMBER = "erro.claimNumber.valid";

    /** minimum length of auth id */
     int MINIMUM_LENGTH_OF_SA_AUTH_ID = 10;

    /** Holds an integer value which represents the maximun length of an SA ID. */
    int MAXIMUM_LENGTH_OF_SA_AUTH_ID = 12;

    /** this specifies the position of media type in service auth id */
    int POSITION_OF_MEDIA_TYPE = 5;

    /** Holds String MEDIA_TYPE_FOR_INTERNAL */
     String MEDIA_TYPE_FOR_INTERNAL = "I";

    /** Holds MEDIA_TYPE_FOR_PROVIDER */
    String MEDIA_TYPE_FOR_PROVIDER = "P";

    /** Holds MEDIA_TYPE_FOR_BRIDGES_BATCH_FEED */
     String MEDIA_TYPE_FOR_BRIDGES_BATCH_FEED = "B";

    /** Holds MEDIA_TYPE_FOR_MSI_BATCH_FEED */
     String MEDIA_TYPE_FOR_MSI_BATCH_FEED = "M";

    /** Holds MEDIA_TYPE_FOR_OPTIONS_BATCH_FEED */
     String MEDIA_TYPE_FOR_OPTIONS_BATCH_FEED = "O";

    /** Holds MEDIA_TYPE_FOR_PAWS_BATCH_FEED */
     String MEDIA_TYPE_FOR_PAWS_BATCH_FEED = "W";

    /** Holds MEDIA_TYPE_FOR_X278_BATCH */
   String MEDIA_TYPE_FOR_X278_BATCH = "X";

    /** used to tell the user the SA record present in databse is not correct */
     String INVALID_SA_RECORD = "invalid-sa-record";

    /**
     * ################# Below constants are used in Log Case #################
     */
    /**
     * This field is used to store numeric pattern
     */
    String NUMERIC_PATTERN = "[0-9]*";

    /** holds the constant for windows path */
    String WINDOWS_PATH = "windowsFileUploadPath";

    /** holds the constant for UNIX path */
    String UNIX_PATH = "unixFileUploadPath";
    
    /** Holds the value whether virus scan should be skipped */
    String SKIP_SCAN = "skipScan";

    /** holds the success message for update */
    String CASE_UPDATE_SUCCESS = "success.logCase.updated";

    /** holds the success message for create */
    String CASE_CREATE_SUCCESS = "success.logCase.created";
    
    /** holds the success message on create or update case save *///added for defect ESPRD00335106
    String CASE_SAVE_SUCCESS = "success.logCase.save";

    /** holds the error Key for order */
    String ORDER_REQURIED = "error.logCase.caseSteps.orderRequried";

    /** Holds the error key for invalid Order */
    String ORDER_INVALID = "error.logCase.caseSteps.invalidOrder";

    /** Holds the error key for invalid Desc */
    String DESC_REQURIED = "error.logCase.caseSteps.descRequried";

    /** holds the error key for status */
    String STATUS_REQURIED = "error.logCase.caseSteps.status";

    /** holds the error key for Expected Days */
    String INVALID_EXPECTEDDAYS = "error.logCase.caseSteps.invalidExpectedDays";
        
    /** holds the error key for Expected Days */
    String DELETE_SUCCESS = "delete.success";

    /** holds the error key for due Date REQUIRED */
    String DUEDATE_REQUIRED = "error.logCase.alerts.dueDateRequried";

    /** holds the error key for invalid due date */
    String INVALID_DUEDATE = "error.logCase.alerts.dueDateInvalid";

    /** holds the error key for invalid Due Date */
    String DUEDATE_LESSTHAN_SYSDATE = "error.logCase.alerts.dueDateIsLessThanCurrentDate";

    /** holds the error key for Alert type */
    String ALERTTYPE_REQUIRED = "error.logCase.alerts.alertTypeRequired";

    /** holds the error key for Notify via alert required */
    String NOTIFYVIA_REQUIRED = "error.logCase.alerts.notifyViaRequired";

    /** holds the error key for Created Date required */
    String CREATEDDATE_REQUIRED = "error.logCase.events.createDateRequired";

    /** holds the error key for invalid Created Date */
    String INVALID_CREATEDDATE = "error.logCase.events.invalidCreatedDate";

    /** holds the error key for Type required */
    String TYPE_REQUIRED = "error.logCase.events.typeRequired";

    /** holds the error key for Event Type required */
    String EVENTDATE_REQUIRED = "error.logCase.events.eventDateRequired";

    /** holds the error key for invalid Event Date */
    String INVALID_EVENTDATE = "error.logCase.events.invalidEventDate";

    /** holds the error key for invalid desc */
    String INVALID_DESC = "error.logCase.alerts.descInvalid";

    /** holds the error key for invalid disposition date */
    String INVALID_DISPOSITIONDATE = "error.logCase.events.invalidDispositionDate";

    /** holds the error key for invalid expected completion date */
    String INVALID_EXCEPTED_DATE = "error.logCase.caseSteps.invalidExpectedDate";

    /** holds the error key for caseType */
    String CASETYPE_REQUIRED = "error.logCase.caseTypeRequried";

    /** holds the error key for caseType */
    String CASESTATUS_REQUIRED = "error.logCase.statusRequried";

    /** holds the error key for caseType */
    String PRIORITY_REQUIRED = "error.logCase.priorityRequried";

    /** holds the error key for caseType */
    String WORKUNIT_REQUIRED = "error.logCase.workUnitRequried";

    /** holds the error key for caseType */
    String CASETITLE_REQUIRED = "error.logCase.caseTitleRequried";

    /** holds the error key for Case title */
    String CASETITLE_INVALID = "error.logCase.caseTitleInvalid";

    /** holds the error key for Case title */
    String ROUTING_SAVE_ERROR = "error.logCase.routing.save.failure";

    /** holds the error key for Case title */
    String APPEAL_BEFOR_SAVE_ERROR = "Case should be created before entering Appeals";

    /** holds the error key for routing dept */
    String DEPT_REQUIRED = "error.logCase.routing.dept.required";

    /** holds the error message for the superviser review req */
    String SUPERVISER_REV_REQ_IND_ERROR = "Superviser review required for this case";

    /** holds the error message for Notes */
    String NOTES_REQUIRED = "Notes is required";

    /** holds the error message for Event date */
    /*String ASSIGNEDTO_INVALID_EVENTDATE = "Event date is invalid for Case Events";*/
    // Added for the defect id ESPRD00334553
    String ASSIGNEDTO_INVALID_EVENTDATE = "Alert due date must not be less than or equal to the system date";
    // Ends

    /** holds the error message for Event date */
    String EVENTDISPOSITION_REQUIRED = " To close DDU Case, Disposition date is required in Case Events";

    /** holds the error message for appeal validation */
    String INVALID_APPEAL_CREATION = "Appeal Can not be created for a closed case";

    /** holds the error message for appeal validation */
    String APPEAL_EXISTS = "Appeals exists for this case. Can not close the Case";

    /** holds the constants for Excep comp date ID ADD */
    String ADD_EXPECTCOMP = "expectedCompAdd";

    /** holds the constants for Excep comp dept ID ADD */
    String ADD_DEPT = "dept";

    /** holds the constants for Excep comp date ID EDIT */
    String EDIT_EXPECTCOMP = "expectedCompEdit";

    /** holds the constants for Order ID ADD */
    String ADD_ORDER = "order";

    /** holds the constants for Order ID EDIT */
    String EDIT_ORDER2 = "order2";

    /** holds the constants for Desc ID ADD */
    String ADD_DESC = "description";

    /** holds the constants for Order ID EDIT */
    String EDIT_DESC2 = "description2";

    /** holds the constants for Status ID ADD */
    String ADD_STATUS = "LOGCASESTEPSSTATUS0";

    /** holds the constants for Order ID EDIT */
    String EDIT_STATUS2 = "LOGCASESTEPSSTATUSE1";

    /** holds the constants for Status ID ADD */
    String ADD_EXPECTEDDAYS = "expectedDaysToComplete";

    /** holds the constants for Order ID EDIT */
    String EDIT_EXPECTEDDAYS2 = "expectedDaysToComplete2";

    /** holds the id for Due Date in ADD */
    String ADD_DUEDATE = "alertcreatedDate";

    /** holds the id for Description in ADD */
    String ADD_DESCRIPTION = "Alertdescription";

    /** holds the id for Alert Type in ADD */
    String ADD_ALERTTYPE = "alerttype";

    /** holds the id for Notify Via Alert in ADD */
    String ADD_NOTIFYVIA = "AlertnotifyViaAlert";

    /** holds the id for Status in ADD */
    String ADD_ALERTSTATUS = "Alertstatus_2";

    /** holds the id for Status in EDIT */
    String EDIT_ALERTSTATUS = "status_3";

    /** HOLDS THE ID FOR Created Date in ADD */
    String ADD_CREATEDDATE = "createdDate";

    /** holds the ID for Type ADD */
    String ADD_TYPE = "type";

    /** holds the ID for Type EDIT */
    String EDIT_TYPE = "type2";

    /** holds the ID for Desc ADD */
    String ADD_EVENTDESC = "eventDescription";

    /** holds the ID for Desc EDIT */
    String EDIT_EVENTDESC = "eventDescription2";
    
    /** holds the ID for Desc ADD */
    String ADD_EVENTSTATUS = "eventStatus";

    /** holds the ID for Desc EDIT */
    String EDIT_EVENTSTATUS = "eventStatus2";

    /** holds the ID for Event Date ADD */
    String ADD_EVENTDATE = "eventDate";

    /** holds the ID for Event Date EDIT */
    String EDIT_EVENTDATE = "eventDate2";

    /** holds the ID for Disposition Date ADD */
    String ADD_DISPODATE = "dispositionDate";

    /** holds the ID for Disposition Date EDIT */
    String EDIT_DISPODATE = "dispositionDate2";

    /** holds column Name Order in Case Steps */
    String CASESTEPS_ORDER = "caseSteps_Order";

    /** holds column Name Description in Case Steps */
    String CASESTEPS_DESC = "caseSteps_Desc";

    /** holds column Name RoutedTo in Case Steps */
    String CASESTEPS_ROUTEDTO = "caseSteps_RoutedTo";

    /** holds column Name Expected Days To Complete in Case Steps */
    String CASESTEPS_EXPTOCOMP = "caseSteps_ExpToComp";

    /** holds column Name Completion Based On in Case Steps */
    String CASESTEPS_COMBASEDON = "caseSteps_ComBasedOn";

    /** holds column Name Status in Case Steps */
    String CASESTEPS_STATUS = "caseSteps_Status";

    /** holds column Name Date Started in Case Steps */
    String CASESTEPS_DATESTARTED = "caseSteps_DateStarted";

    /** holds column Name Expected Completion Date in Case Steps */
    String CASESTEPS_EXPCOMPDATE = "caseSteps_ExpCompDate";

    /** holds column Name Completion Date in Case Steps */
    String CASESTEPS_COMPDATE = "caseSteps_CompDate";

    /** holds column Name Notify Via Alert in Case Steps */
    String CASESTEPS_NOTIFYALERT = "caseSteps_NotifyAlert";

    /** holds column Name Template in Case Steps */
    String CASESTEPS_TEMPLATE = "caseSteps_Template";

    /** holds column Name Due Date in Case Alerts */
    String CASEALERTS_DUEDATE = "caseAlerts_DueDate";

    /** holds column Name AlertType in Case Alerts */
    String CASEALERTS_ALERTSTYPE = "caseAlerts_Type";

    /** holds column Name Description in Case Alerts */
    String CASEALERTS_DESCRIPTION = "caseAlert_Desc";

    /** holds column Name NotifyViaAlert in Case Alerts */
    String CASEALERTS_NOTIFYVIAALERT = "caseAlert_NotifyviaAlert";

    /** holds column Name Status in Case Alerts */
    String CASEALERT_STATUS = "caseAlert_Status";

    /** holds column Name Description in Case Events */
    String CASEEVENT_DESC = "caseEvent_Desc";

    /** holds column Name EventDate in Case Events */
    String CASEEVENT_DATE = "caseEvent_Date";

    /** holds column Name Time in Case Events */
    String CASEEVENT_TIME = "caseEvents_Time";

    /** holds column Name Status in Case Events */
    String CASEEVENT_STATUS = "caseEvents_Status";

    /** holds column Name DispositionDate in Case Events */
    String CASEEVENTS_DISDATE = "caseEvents_disDate";

    /** holds column Name NotifyViaAlert in Case Events */
    String CASEEVENTS_NOTIFYVIAALERT = "caseEvents_notifyViaAlert";

    /** holds column Name AlertBasedOn in Case Events */
    String CASEEVENTS_ALERTBASEDON = "caseEvents_alertBasedOn";

    /** holds column Name SendAlertDays in Case Events */
    String CASEEVENTS_SENDALERTDAYS = "caseEvents_sendAlertDays";

    /** holds column Name Date in Case Routing */
    String CASEROUTING_DATE = "Date";

    /** holds column Name RoutedBy in Case Routing */
    String CASEROUTING_ROUTEDBY = "routedBy";

    /** holds column Name RoutedTo in Case Routing */
    String CASEROUTING_ROUTEDTO = "routedTo";

    /** holds column Name WatchList in Case Routing */
    String CASEROUTING_WATCHLIST = "watchList";

    /** holds column Name Type for Provider Type in Case Regarding */
    String CASEREGARDING_ALTIDEN_PROV_TYPE = "altIden_Prov_Type";

    /** holds column Name AlternateID for Provider Type in Case Regarding */
    String CASEREGARDING_ALTIDEN_PROV_ALTID = "altIden_Prov_AltID";

    /** holds column Name LineOfBusiness for Provider Type in Case Regarding */
    String CASEREGARDING_ALTIDEN_PROV_LOB = "altIden_Prov_LOB";

    /** holds column Name Type for Member Type in Case Regarding */
    String CASEREGARDING_ALTIDEN_MEM_TYPE = "altIden_Mem_Type";

    /** holds column Name AlternateID for Member Type in Case Regarding */
    String CASEREGARDING_ALTIDEN_MEM_ALTID = "altIden_Mem_AltID";

    /** holds column Name LineOfBusiness for Member Type in Case Regarding */
    String CASEREGARDING_ALTIDEN_MEM_LOB = "altIden_Mem_LOB";

    /** holds column Name DateAdded in Case Attachments */
    String CASEATTACHMENTS_ATTACH_DATEADDED = "Attach_DateAdded";

    /** holds column Name AddedBy in Case Attachments */
    String CASEATTACHMENTS_ATTACH_ADDEDBY = "Attach_AddedBy";

    /** holds column Name FileName in Case Attachments */
    String CASEATTACHMENTS_ATTACH_FILENAME = "Attach_FileName";

    /** holds column Name Description in Case Attachments */
    String CASEATTACHMENTS_ATTACH_DESC = "Attach_Desc";

    /** holds column Name ID in AssociateCaseVO */
    String EXISTINGCASE_ID = "existingCase_ID";

    /** holds column Name Created Date in AssociateCaseVO */
    String EXISTINGCASE_CREATEDDATE = "existingCase_CreatedDate";

    /** holds column Name Status in AssociateCaseVO */
    String EXISTINGCASE_STATUS = "existingCase_Status";

    /** holds column Name Case Type in AssociateCaseVO */
    String EXISTINGCASE_CASETYPE = "existingCase_CaseType";

    /** Holds Case BccpId */
    String CASETYPE_BCCP_ID = "bccpID";

    /** HOlds Errormsg for BCCP */
    String BCCP_ID_NUMERC = "error.logcase.bccpid.numeric";

    /** Holds errormessage for contactperson */
    String CONTACT_PERSON_CHAR = "error.logcase.contact.person.char";

    /** Holds contact personID */
    String CONTACT_PERSON_ID = "contactPerson";

    /** Holds caseApplication DateID */
    String CASE_APPLICATION_DATE = "appDt";

    /** Holds caseApplication date error message */
    String CASE_APPLICATION_DATE_INVALID = "error.logcase.applndate.invalid";

    /** HOlds caseEnrollment Date */
    String CASE_ENROLLMENT_DATE = "enrollDt";

    /** Holds Errormessage for Enrollment date */
    String CASE_ENROLL_DATE_INVALID = "error.logacase.enrolldate.invalid";

    /** Holds Id for Agency manger */
    String CASE_AGENCY_MANGER = "agencyCaseManager";

    /** Holds Errormessage for Agency manger */
    String CASE_AGENCY_MANGER_INVALID = "error.logacase.agencymanager.invalid";

    /** Holds AgencyPhno id */
    String CASE_AGENCY_PHNO = "agencyPhoneNo";

    /** Holds AgencyPhno Erro message */
    String CASE_AGENCY_PHNO_INVALID = "error.logcase.agencyphno.invalid";

    /** Hodls CaseBiopsydate id */
    String CASE_BIOPSY_DATE = "biopsyDt";

    /** Holds casebiopsy date errormesg */
    String CASE_BIOPSY_DATE_INVALID = "error.logcase.biopsydate.invalid";

    /** Holds casest followup id */
    String CASE_ST_FOLLOWUP = "stFollowup";

    /** holds case stfollowup errormessage */
    String CASE_ST_FOLLOWUP_INVALID = "error.logcase.stfollowup.invalid";

    /** Hodls consultdate id */
    String CASE_CONSULT_DATE = "consultDt";

    /** Holds consult date errormessage */
    String CASE_CONSULT_DATE_INVALID = "error.logcase.consultdate.invalid";

    /** Holds metastatis */
    String CASE_METASTATIS_AREA = "metaStatisArea";

    /** Holds metastatis error message */
    String CASE_METASTATIS_AREA_INVALID = "error.logcase.metastatis.invalid";

    /** holds code for unstaged reason */
    String CASE_UNSTATED_REASON = "unstatedReason";

    /** holds error message for unstated reason */
    String CASE_UNSTATED_REASON_INVALID = "error.logcase.unstated.reason.invalid";

    /** holds tumorSize code */
    String CASE_TUMOR_SIZE = "tumorSize";

    /** holds tumorSize error message */
    String CASE_TUMOR_SIZE_INVALID = "error.logcase.tumorsize.invalid";

    /** Holds nodesPositive id */
    String CASE_NODES_POSITIVE = "nodesPositive";

    /** Holds Errormsg nodes positive */
    String CASE_NODES_POSITIVE_INVALID = "error.logcase.nodespositive.invalid";

    /** Holds trtmtstrtDt id */
    String CASE_TREATMENT_DATE = "trtmtstrtDt";

    /** Holds errormsg for trtmtstrtDt */
    String CASE_TREATMENT_DATE_INVALID = "error.logcase.treatmentdate.invalid";

    /** Holds chmoendDt id */
    String CASE_CHEMOPROJECT_DATE = "chmoendDt";

    /** Holds Errormsg fro chmoendDt */
    String CASE_CHEMOPROJECT_DATE_INVALID = "error.logcase.projectenddate.invalid";

    /** Holds careGiverName id */
    String CASE_CAREGIVER_NAME = "careGiverName";

    /** HOlds errormsg for caregiver name */
    String CASE_CAREGIVER_NAME_INVALID = "error.logcase.caregivername.invalid";

    /** Holds careGiverphone id */
    String CASE_CAREGIVER_PHONE = "careGiverPhone";

    /** HOlds errormsg for caregiver phone */
    String CASE_CAREGIVER_PHONE_INVALID = "error.logcase.caregiverphone.invalid";

    /** HOlds Acal id */
    String CASE_DDU_APPLNDATE = "Acal";

    /** holds Acal error message */
    String CASE_DDU_APPLNDATE_INVALID = "error.logcase.dduapplndate.invalid";

    /** holds subcomdatid */
    String CASE_SUBCOMPLETION_DATE = "subcomdat";

    /** Holds errormsg for sub completioon date */
    String CASE_SUBCOMPLETION_DATE_INVALID = "error.logcase.subcompltn.date.invalid";

    /** Holds packet recieved date */
    String CASE_PACKETRECIEVE_DATE = "pcktcal";

    /** Holds errormsg fro pcktcal */
    String CASE_PACKETRECIEVE_DATE_INVALID = "error.logcase.packetrcv.date.invalid";

    /** holds reviewin date */
    String CASE_REVIEWINTIATE_DATE = "reviewInDate";

    /** Holds reviewint error message */
    String CASE_REVIEWINTIATE_DATE_INVALID = "error.logcase.reviewint.date.invalid";

    /** holds scheduledDateOfReview id */
    String CASE_REVIEWSCH_DATE = "scheduledDateOfReview";

    /** holds scheduledDateOfReview error message */
    String CASE_REVIEWSCH_DATE_INVALID = "error.logcase.schDateReview.invalid";

    /** holds scheduledDateOfReview error message */
    String CASE_REVIEWSCH_DATE_REQUIRED = "error.logcase.schDateReview.required";

    /** holds to receiveddt id */
    String CASE_REVIEWRECV_DATE = "receiveddt";

    /** holds to dispaly errorsg for receiveddt */
    String CASE_REVIEWRECV_DATE_INVLAID = "error.logcase.revrecievedate.invalid";

    /** holds RcptDt id */
    String CASE_REVRECPT_DATE = "RcptDt";

    /** Holds RcptDt error message */
    String CASE_REVRECPT_DATE_INVALID = "error.logcase.revrecptdate.invalid";

    /** holds RcptDt id */
    String CASE_DECISION_DATE = "descDt";

    /** Holds RcptDt error message */
    String CASE_DECISION_DATE_INVALID = "error.logcase.descdate.invalid";

    /** holds appbegDt id */
    String CASE_APPBEG_DATE = "appbegDt";

    /** hodls error message applnbeg date */
    String CASE_APPBEG_DATE_INVALID = "error.logcase.approvebeg.date.invalid";

    /** Holds appldesDt id */
    String CASE_APPLDESN_DATE = "appldesDt";

    /** Holds appealdesn date error message */
    String CASE_APPLDESN_DATE_INVALID = "error.logcase.appldesc.date.invalid";

    /** Holds ratestngdat id */
    String CASE_RATESETTING_DATE = "ratestngdat";

    /** Holds appealdesn date error message */
    String CASE_RATESETTING_DATE_INVALID = "error.logcase.fieldrateset.date.invalid";

    /** Holds fieldaudit date id */
    String CASE_FIELDAUDIT_DATE = "fldadtAudit";

    /** holds the fileupload ID*/
    String ATTACH_UPLOAD_ID = "fileupload1";

    /** Holds error message fieldaudit date */
    String CASE_FIELDAUDIT_DATE_INVALID = "error.logcase.fieldaudit.date.invalid";

    /** Holds Error message for CaseStatus */
    String CASE_CANT_CREATE = "error.case.cant.create";

    /** holds the Error message for upload */
    String ATTACH_MESSAGE = "error.file.required";

    /**
     * ################# End of constants used in Log Case #################
     */

    /**
     * Case Type
     */
    String STEP_ORDER_REQ = "error.casetype.casestep.orderRequired";

    /** holds add step order ID */
    String STEP_ORDER_ID = "steporder";

    /** holds STEP_DESC_ORDER_REQ */
    String STEP_DESC_ORDER_REQ = "error.casetype.casestep.descRequired";

    /** holds step description ID */
    String STEP_DESC_ID = "descid";

    /** holds event EVENT_TYPE_ORDER_REQ */
    String EVENT_TYPE_ORDER_REQ = "error.casetype.caseevent.typeRequired";

    /** holds event typecode */
    String EVENT_TYPE_CODE = "typeCode";

    /** holds casetype error code */
    String DELETE_CASETYPE_RECORD_FAIL = "error.casetype.associated.case";

    /** HOlds Desc and order number errorcode */
    String STEP_DESC_ORDER_CANT_SAME = "error.casestep.desc.ordernum.same";

    /** Holds event typecode error code */
    String EVENT_TYPE_CODE_CANT_SAME = "error.caseevent.type.same";

    /** Hodls desc and ordernum */
    String STEP_ORDER_DESC_ID = "descNumId";

    /** Holds Short and LongDesc Error code */
    String TYPE_SHORT_LONGDESC_CANT_SAME = "error.typelong.short.desc.same";

    /** holds CT_SHORT_DESC_REQ */
    String CT_SHORT_DESC_REQ = "error.casetype.shortDescRequired";

    /** holds CT_SHORT_DESC_ID */
    String CT_SHORT_DESC_ID = "casetypesDesc";

    /** holds CT_LONG_DESC_REQ */
    String CT_LONG_DESC_REQ = "error.casetype.longDescRequired";

    /** holds CT_LONG_DESC_ID */
    String CT_LONG_DESC_ID = "casetypelDesc";

    /** Holds save success message */
    String CASETYPE_SAVE_SUCCESS = "success.saved";

    /** holds errormesg for shortdesc */
    String TYPE_SHORT_DESC_CHAR = "error.casetype.shortDesc.char";

    /** Holds errormsg fro long desc */
    String TYPE_LONG_DESC_CHAR = "error.casetype.longDesc.char";

    /** holds errormsg for shortdesc length */
    String TYPE_SHORT_DESC_LENGTH = "error.casetype.shortdesc.length";

    /** Holds errormsg for longdesc length */
    String TYPE_LONG_DESC_LENGTH = "error.casetype.longdesc.length";

    /** Holds Casestep Errormsg */
    String CASE_STEP_EXISTS_ACTCM = "error.casestep.exists.actcm";

    /** Holds caseevene Errormsg */
    String CASE_EVENT_EXISTS_ACTCM = "error.caseevent.exists.actcm";

    /** Holds Case TableID */
    String CASE_TABLE_ID = "G_CASE_TB";

    /** holds */
    String TRUE = "YES";

    /** holds false */
    String FALSE = "NO";
    
    /** holds */
    String DEFAULTTRUE = "true";

    /** holds false */
    String DEFAULTFALSE = "false";

    /** holds YESLable */
    String YESLABEL = "Yes";

    /** holds NOLable */
    String NOLABEL = "No";

    /** holds CUSTOMFIELD_BEAN */
    String CUSTOMFIELD_BEAN = "customFieldDataBean";

    /** Holds before value */
    String BEFORE = "Before";

    /** HOlds Radio B value */
    String BEFVALUE = "B";

    /** Holds Radio A Value */
    String AFTVALUE = "A";

    /** Holds After value */
    String AFTER = "After";

    /** Holds CaserecordNumber for Mytask */
    String MYCASE_REC_NO = "caseRecNo";

    /** Holds Opendate for Mytask */
    String MYCASE_OPEN_DATE = "openDate";

    /** Holds prioritycode for Mytask */
    String MYCASE_PRV_CODE = "priorityCode";

    /** Holds statuscode for Mytask */
    String MYCASE_STATUS_CODE = "statusCode";

    /** Holds entityType for Mytask */
    String MYCASE_ENTY_TYPE = "entityType";

    /** Holds CaseType for Mytask */
    String MYCASE_CASE_TYPE = "caseType";

    /** Holds Caserecnumber for group */
    String GRPCASE_REC_NO = "caseRecNo";

    /** Holds grpOpendate for Mytask */
    String GRPCASE_OPEN_DATE = "openDate";

    /** Holds grpprioritycode for Mytask */
    String GRPCASE_PRV_CODE = "priorityCode";

    /** Holds grpentityType for Mytask */
    String GRPCASE_ENTY_TYPE = "entityType";

    /** Holds GRPCaseType for Mytask */
    String GRPCASE_CASE_TYPE = "caseType";

    /** Holds grpstatuscode for Mytask */
    String GRPCASE_STATUS_CODE = "statusCode";

    /** Holds grpWorkunit for mytask */
    String GRPCASE_WORKUNIT_ = "workUnit";

    /** Holds watchlistCrn for mytask */
    String CASEWL_REC_NO = "caseRecNo";

    /** Holds watchlistOpendate for mytask */
    String CASEWL_OPEN_DATE = "openDate";

    /** Holds CaseWl prvCode */
    String CASEWL_PRV_CODE = "priorityCode";

    /** Holds CaseWl entity type */
    String CASEWL_ENTY_TYPE = "entityType";
    
    /** Holds CaseWl entityName */
    String CASEWL_ENTY_NAME = "entityName";

    /** Holds casewl casetype */
    String CASEWL_CASE_TYPE = "caseType";

    /** HOlds Casewl StatusCode */
    String CASEWL_STATUS_CODE = "statusCode";

    /** HOLDS CaseWl user/workunit */
    String CASEWL_USER_WORKUNIT = "userOrWorkUnit";

    /** Holds CustomField Description error message */
    String COLUMN_DESCRIPTION_UNIQUE = "error.column.desc.unique";
    
    //  Infinite Computer Solutions FOR CR-1825 
    public static final String HIDE_LINK = "label.link";
    public static final String DISABLE_BUTTON = "label.button";

    /**Holds Category Description Error message */
    String CATEGORYDESC_SHOULD_UNIQUE = "error.categorydesc.unique";
    
    /**Holds CustomField Dropdown Description erormessage */
    String DD_DESC_REQ_CF="err.pgm.cm.dropdownval.defined";
    
    /**Holds CallScript Description erormessage */
    String CALLSCRIPTDESC_SHOULD_UNIQUE = "error.callscriptDesc.unique";
    /**holds key for invalid file error message   */
    String INVALID_FILE_TYPE = "err.pgm.cm.incorrect.file.type";
    String INVALID_FILE_SIZE="err.pgm.cm.incorrect.file.size";
    String MAX_ONE_MB_SIZE = "1048576";
    String ENTITY_ALREADY_EXISTS ="err.pgm.cm.dup.entity";
    String COUNTRY_ALREADY_EXISTS="err.pgm.cm.dup.country";
   
    String SCAN_FILE_CLEAN ="msg.scan.file.clean";
    String SCAN_FILE_INFECTED ="msg.scan.file.infected";
    String SCAN_FILE_ERROR ="msg.scan.file.error";
    
    /** CONSTANTS FOR DUPLICATE ENTITY UPDATE */
    String DIST_CODE_ALREADY_EXISTS="9-3210-0410";
    String DIST_ORG_ALREADY_EXISTS="9-3210-0415";
    String COUNTY_CODE_ALREADY_EXISTS="9-3210-0420";
    String COUNTY_NAME_ALREADY_EXISTS="9-3210-0425";
    
    /** Constant for security of SearchDocument Repository*/
    
    String SECURITY_FOR_SRCH_DOC_REPOSITORY = "/Enterprise/SearchDocumentRepository";
    //ADDED BY ICG GAP-11022
    /** holds constant for component SE */
    String COMP_SE = "SE";
    //END BY ICS GAP-11022
    //Added By ICS CR 1622
    /** This holds default date attribute */
    long NULL_DATE = 00 / 00 / 0000;

    /** Holds Provider Case Appeals SA Item LineNumber */
    String PROV_APLS_SA_LINE_NUM = "serviceLineNum";

    /** Holds Provider Case Appeals SA Item ServiceType */
    String PROV_APLS_SA_TY_CD = "serviceTyCd";

    /** Holds Provider Case Appeals SA Item ServiceFromCd */
    String PROV_APLS_SA_FRM_CD = "serviceFrmCd";

    /** Holds Provider Case Appeals SA Item ServiceToCd */
    String PROV_APLS_SA_TO_CD = "serviceToCd";

    /** Holds Provider Case Appeals SA Item ServiceRequestedBeginDate */
    String PROV_APLS_SA_REQ_BEG_DT = "serviceReqBegDt";

    /** Holds Provider Case Appeals SA Item ServiceRequestedEndDate */
    String PROV_APLS_SA_REQ_END_DT = "serviceReqEndDt";

    /** Holds Provider Case Appeals SA Item RequestedUnit */
    String PROV_APLS_SA_REQ_UNIT = "serviceReqUnit";

    /** Holds Provider Case Appeals SA Item RequestedAmount */
    String PROV_APLS_SA_REQ_AMT = "serviceReqAmt";

    /** Holds Provider Case Appeals SA Item RequestedRate */
    String PROV_APLS_SA_REQ_RATE = "serviceReqRate";

    /** Holds Provider Case Appeals SA Item StatusCode */
    String PROV_APLS_SA_STATUS_CD = "serviceStatus";
    //Ended By ICS CR 1622
    
    //Added for 2105 
    String DEL_ENTITY_MSG = "error.del.logcase";//
    
    
    /* Added Constants for CR #ESPRD00114296 */
    
    /** Holds closecode id */
    String CASE_CLOSE_CODE = "closeCode";
    
    /** Holds close code error message */
    String INVALID_CLOSE_CODE = "error.logcase.closecode.invalid";
    
    /** Holds DDD case type code */
    String DDU_CASE_TYPE_CODE = "DDU";
    
    /** Holds Closed Status */
    String CLOSED_CASE_STATUS = "C";
    
    /** Holds Approved case status */
    String APPROVED_CASE_STATUS = "A";
    
    /* Ended CR #ESPRD00114296 */    
    
    //Added for defect ID:ESPRD00299796 & ESPRD00299535
    String AM_PM_REQ = "9_0000_9999";

    //Added to show error message if before after is not selected
    String BEFORE_AFTER_REQ = "error.logcase.beforeafter";
    
    String 	BEFORE_AFTER_ADD = "beforeAfterId";
    
    String BEFORE_AFTER_EDIT = "editBeforeAfterId";    

    String TIME_ADD_ID = "time_1";
    
    String TIME_EDIT_ID = "time_2";    
    
    String INVALID_TIME = "error.logcase.invalidTime";

    //Added for defect ID:ESPRD00299556
    /** holds the error key for invalid Due Date */
    String DUEDATE_LESSTHAN_SYSTEM_DATE = "error.logCase.alerts.dueDateIsLessThanSystemDate";
    
//  Added for defect ID:ESPRD00299866
    /** holds the error key for duplication of the Order */
    String DUPLICATE_ORDER_CASE_STEP_SAVE = "error.logCase.alerts.duplicateCaseStepOrderSave";
    /** holds COMPLETION_BASED_ON_REQ */
    String COMPLETION_BASED_ON_REQ = "error.casetype.completionBasedOnRequired";
    
    /** holds EXPECTED_DAYS_TO_COMPLETE_REQ */
    String EXPECTED_DAYS_TO_COMPLETE_REQ = "error.casetype.expectedDaysToCompleteRequired";
    
    //added for defect ESPRD00327996
    /** holds LOGCASE_ROUTING_SHOW_FIELD_BUSINESS_UNIT_CODE valid value  */
    String LOGCASE_ROUTING_SHOW_FIELD_BUSINESS_UNIT_CODE = "B";
    /** holds LOGCASE_ROUTING_SHOW_FIELD_WORK_UNIT_CODE valid value  */
    String LOGCASE_ROUTING_SHOW_FIELD_WORK_UNIT_CODE = "W";
    /** holds LOGCASE_ROUTING_SHOW_FIELD_USERS_CODE valid value  */
    String LOGCASE_ROUTING_SHOW_FIELD_USERS_CODE = "U";
    //EOF defect ESPRD00327996
    

	//  added for defect ESPRD00309081
	/** holds EXPECTED_DAYS_TO_COMPLETE_REQ */
	String NO_AUTHORITY_ADD_CASE = "9-3210-0175";
	//  EOF defect ESPRD00309081
    
//  Infinite Added for Defect ESPRD00329300
    /** holds the error key for invalid Field Audit Date */
    String FIELD_AUDIT_DATE_INVALID = "error.logCase.fieldAuditDateInvalid";
    
    /** holds the error key for invalid Rate Setting Date */
    String RATE_SETTING_DATE_INVALID = "error.logCase.rateSettingDateInvalid";
    
     /** holds the error key for invalid State Fiscal Date */
    String STATE_FISCAL_YEAR_DATE_INVALID = "error.logCase.stateFiscalDateInvalid";
    	
	/** holds the error key for invalid Superviser Date */
    String FILE_SUPERVISER_DATE_INVALID = "error.logCase.superviserDateInvalid";
//  Code ended for Defect ESPRD00329300  
	
    /** Regular expression for matching the time. */
    String TIME_PATTERN = "[0-2]?[0-9][:][0-5]?[0-9]";
   
   /** Holds the key for the additional case entities */
	String DELETION_INQUIRY_ADDITIONAL_CASE_DETAILS = "DELETION_INQUIRY_ADDITIONAL_CASE_DETAILS";

	 /** holds EntityId Required information */
    String ENTITY_IDTYPE_REQ = "error.entiryIDTypeReq";
	
    /** Holds caseApplication date error message */
    String CASE_SUBSTANTIALLY_COMPLETED_DATE_INVALID = "error.logacase.substantiallyCompletedDate.invalid";
    
    /** Holds caseApplication date error message */
    String CASE_PACKET_RECEIVED_DATE_INVALID = "error.logacase.packetReceivedDate.invalid";

    /** Holds caseApplication date error message */
    String CASE_REVIEW_INITIATED_DATE_INVALID = "error.logacase.reviewInitiatedDate.invalid";

    /** Holds caseApplication date error message */
    String CASE_RECEIVED_DATE_INVALID = "error.logacase.receivedDate.invalid";

    /** Holds caseApplication date error message */
    String CASE_RECEIPT_DATE_INVALID = "error.logacase.receiptDate.invalid";
    
    
    /* Adding constants to Fixed defect id ESPRD00332708 starts */    
    String ENITY_TYPE_REQUIRED = "error.enitytType.required";
        
    String CASE_SEARCH_ENTITY_TYPE_ID = "CaseSearchEntityType";
    String CASE_SEARCH_ENTITY_ID_TYPE_ID = "CaseSearchEntityIDType";
    String CASE_SEARCH_ENTITYID__ID = "EntityID";
    
    String ADD_CASE_SEARCH_ENTITY_TYPE_ID = "AddCaseSearchEntityType";
    String ADD_CASE_SEARCH_ENTITY_ID_TYPE_ID = "CaseSearchEntityIDType2";
    String ADD_CASE_SEARCH_ENTITYID__ID = "AddEntityID"; 
 	
    /* Adding constants to Fixed defect id ESPRD00332708 End */    
    
    /** holds the ID for Desc ALPHA not allowed */
    String ALPHA_EVENTDESC = "error.logCase.caseSteps.descInvalid";
    
    /** Holds Desk review start date error message */
    String DESK_REVIEW_START_DATE_INVALID = "error.logacase.deskRieviewdateinvalid";
 
    /* Adding constants to Fixed defect id ESPRD00343113 starts */ 
    String CASE_RECIEVED_DATE_REQUIRED="error.logacase.receivedDate.required";
    /* Adding constants to Fixed defect id ESPRD00343113 ends */ 
    /* Adding constants to Fixed defect id ESPRD00343146 starts */ 
    String CASE_CLOSE_CODE_INVALID="error.logcase.close.code.invalid";
     /* Adding constants to Fixed defect id ESPRD00343146 ends */
    String CASE_REVIEWSCH_DATE_GREATER="9-3210-0235";
    /** holds constant for Pending */
    String CASE_EVENT_STATUS_TYPE = "P";
    
    /** Holds the valid value for case status code of type closed "C"*/ 
    String CASE_STATUS_CANCEL_CODE_C = "C";
    /** Holds the valid value for case status code of type closed "W"*/ 
    String CASE_STATUS_CANCEL_CODE_W = "W";
    //ESPRD00328556
    /**key property for sucessfull deletion of CaseAssociatedWithThisCase */
    String CASE_ASSOC_WITH_THIS_RECORD_SUCCESS = "delete.caseAssoWithThisReco.success";
    
    /**Holds component id for sucessfull deletion of CaseAssociatedWithThisCase */
    String CASE_ASSOC_WITH_THIS_RECORD_SUCCESS_MSG_ID = "deleteCaseAssoWithThisRecoSuccessMsgID";
    
    String COMPARE_CREATEDATE_DISPOSITIONDATE = "9-3210-0190";
    
    //added for defect ESPRD00343145
    /** Error message key to represents that an open Casetype is Alerady existed */
    String CASE_DDU_OPEN_ALREADY_EXISTED = "9-3210-0185";  //EOf ESPRD00343145
    
    String APPEALSTATCODE_HIRINGSTATCODE_CLOSE = "9-3210-0240";
    
    /** holds the error message key for invalid date formate*///ESPRD00333407
    String INVALID_DATE_FORMATE = "error.logcase.caseevents.invalid.dateformate";
    
    /* Added for the defect ESPRD00357689 */
    
    String INVALID_DATE_FORMATE1 = "error.logcase.caseevents.invalid.dateformate1";
    String INVALID_DATE_FORMATE2 = "error.logcase.caseevents.invalid.dateformate2";
    String INVALID_DATE_FORMATE3 = "error.logcase.caseevents.invalid.dateformate3";
    
    //added for defect ESPRD00357971
    /** Holds Valid value code for letter response status of Appreoved*/
    String CASE_LETTERS_AND_RESPONSE_STATUS_APPROVED_VALID_CD = "AP";
    
    /** Holds Short Description for letter response status of Appreoved*/
    String CASE_LETTERS_AND_RESPONSE_STATUS_APPROVED_SHORT_DISC = "Approved";
    
    /** Holds Valid value code for letter response status of In-Review*/
    String CASE_LETTERS_AND_RESPONSE_STATUS_IN_REVIEW_VALID_CD = "IR";
    
    /** Holds Short Description for letter response status of In-Review*/
    String CASE_LETTERS_AND_RESPONSE_STATUS_IN_REVIEW_SHORT_DISC = "In Review";
    
    /** Holds Valid value code for letter response status of New*/
    String CASE_LETTERS_AND_RESPONSE_STATUS_NEW_VALID_CD = "NE";
    
    /** Holds Short Description for letter response status of New*/
    String CASE_LETTERS_AND_RESPONSE_STATUS_NEW_SHORT_DISC = "New";
    
    /** Holds error message code for out standing letters*/   
    String CASE_CANNOT_CLOSE_DUE_TO_OUT_STATNDING_LETTER = "9-3210-0295";
    //EOF defect ESPRD00357971
    
    /** Holds id for 'COMPLETION BASED ON' filed in add case steps span*/
    String ADD_ID_COMPLETION_BASED_ON = "completionBasedOn";
    
    /** Holds id for 'COMPLETION BASED ON' filed in add case steps span*/
    String EDIT_ID_COMPLETION_BASED_ON = "completionBasedOn2";
    
    /** holds Valid value code B for alertBased On type before */
    String ALERT_BASED_VALID_CODE_B_FOR_BEFORE = "B"; 
    
    /** holds String description for Valid value code B for alertBased On type before */
    String ALERT_BASED_DESC_FOR_VALID_CODE_B_FOR_BEFORE = "before"; 
    
    /** holds Valid value code A for alertBased On type after */
    String ALERT_BASED_VALID_CODE_A_FOR_AFTER = "A";
    
    /** holds String description for Valid value code A for alertBased On type after */
    String ALERT_BASED_DESC_FOR_VALID_CODE_A_FOR_AFTER = "after";
    
    
    /* DEFECT ESPRD00362698 STARTS*/ 
    
    /* holds alert status as CA*/
    String ALERT_STATUS_CA = "CA";
    
    /* DEFECT ESPRD00362698 END*/ 
    
    /* DEFECT ESPRD00362734 STARTS */
    
    /* holds alert status as CD*/
    String ALERT_STATUS_CD = "CD";
    
    /** Holds Approved case status */
    String OPEN_CASE_STATUS = "O";
    
    /* DEFECT ESPRD00362734 END */
    /** holds error code for Additional case entities*/
    String ADDITIONAL_CASE_ASSOCIATED_CANNOT_REMOVE = "error.del.logcase";
   
    /** holds case step's closed status code *///added for ESPRD00362595
    String CASE_STEP_STATUS_CLOSED = "C";
    
    /** holds case step's void/skip status code */
    String CASE_STEP_STATUS_VOID_OR_SKIP = "S"; //EOF ESPRD00362595
    
    /** holds event EVENT_DEFAULT_ALERT_BASED_ON_REQ */
    String EVENT_DEFAULT_ALERT_BASED_ON_REQ = "error.casetype.caseevent.defaultAlertBasedOn";
    
    /** holds event EVENT_DEFAULT_SEND_ALERT_OF_DAY_REQ */
    String EVENT_DEFAULT_SEND_ALERT_OF_DAY_REQ = "error.casetype.caseevent.defaultSendAlertOfDay";
    
	  /** holds step STEP_DEFAULT_ALERT_BASED_ON_REQ */
    String STEP_DEFAULT_ALERT_BASED_ON_REQ = "error.casetype.casestep.defaultAlertBasedOn";
    
    /** holds step STEP_DEFAULT_SEND_ALERT_OF_DAY_REQ */
    String STEP_DEFAULT_SEND_ALERT_OF_DAY_REQ = "error.casetype.casestep.defaultSendAlertOfDay";
    
    /** Holds error message 9-3210-0020 *///added for defect UC-PGM-CRM-018_ESPRD00382973_9JAN10
    String CASE_CANNOT_CLOSE_DUE_TO_NON_SUBMITTED_LETTER = "err.pgm.cm.no.close.record.no.status";//9-3210-0020    //EOF ESPRD00382973
    
    /** holds case step's closed status code *///added for ESPRD00382416
    String CASE_ALERT_STATUS_COMPLETED = "C";
    
    /** holds case step's Completed void status code */
    String CASE_ALERT_STATUS_COMPLETED_VOID = "CV"; //EOF ESPRD00382416
    
    String UNIX_PATH_CASE = "unixFileUploadPathForCase";
    
    /** Holds the value for Non Gpfs File Upload Directory to created in SAN location */
    String NON_GPFS_FILE_UPLOAD_DIR = "nonGpfsFileUploadDir";
    
    /** Holds the value for Non Gpfs File Upload Test File 1 */
    String NON_GPFS_FILE_UPLOAD_TEST_FILE_1 = "nonGpfsFileUploadTestFile1";
    
    /** Holds the value for Non Gpfs File Upload Test File 1 */
    String NON_GPFS_FILE_UPLOAD_TEST_FILE_2 = "nonGpfsFileUploadTestFile2";
    
    /** Holds the valid values for case event's completed status*/
    String CASE_EVENT_STATUS_COMPLETED = "CM"; //added for UC-PGM-CRM-018.4_ESPRD00382315_06FEB2010
    
    /** Holds error message key for detach attachment *///9-3210-0070
    String CLOSED_CASE_CR_CANNOT_DETACH = "err.pgm.cm.cnnt.detach.att.frm.clsd.case"; //UC-PGM-CRM-018.2_ESPRD00433227_12MAR2010
    
    //added for defect UC-PGM-CRM-020_ESPRD00433491_12MAR2010
    /** Holds global error message key for ENTITY ID */ //9-0000-9999
    String CASE_SEARCH_ENTITY_ID_REQ = "error.casesearch.entity.id.req";
    
    /** Holds global error message key for ENTITY ID */ //9-0000-9999
    String CASE_SEARCH_ENTITY_ID_TYPE_REQ = "error.casesearch.entity.type.id.req";
    
    /** Holds global error message key for ENTITY ID */ //9-0000-9999
    String CASE_SEARCH_ENTITY_TYPE_REQ = "error.casesearch.entity.id.type.req";
    
    /**Holds message componant id on UI */
    String ENTITY_ID_UI_MSG_ID = "AddEntityID";
    
    /**Holds message componant id on UI */
    String ENTITY_ID_TYPE_UI_MSG_ID = "CaseSearchEntityIDType";
    
    /**Holds message componant id on UI */
    String ENTITY_ID_TYPE_UI_MSG_ID_2 = "CaseSearchEntityIDType2";//EOf UC-PGM-CRM-020_ESPRD00433491_12MAR2010
    
    /**Holds error message key for zero size file    */
    String CASE_ATTACHED_FILE_SIZE_ZERO = "err.fileattach.file.size.zero";
    
    /**Holds error message key for scanning problem */
    String ERR_FILE_SCAN = "err.file.scanerror";
    
    /**Holds error message key for virus found */
    String ERR_FILE_VIRUS_FOUND="err.file.virusfound";
    
    /** holds the resource bundle location */
    String MESG_BDL_COMN_ATT = "com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CommonAttachment";
    
    
    /** Holds IPC param's Key value */ 
    String MEMBER_DETAIL = "MemberDetail";//UC-PGM-CRM-053_ESPRD00431860_26MAR2010
    
    /** Holds Request Medical Service Questionnaire Maintenance View. */
    String MEM_TPL_MSQ = "Medical Service Questionnaire Maintenance";
    
    String PROVIDER_HOPITAL_REQUIRED = "provider.hospital.required";
    
    String SHOW_ROUTING_REQUIRED = "error.logCase.routing.show.required";
    
    String SHOW_ROUTING = "show";
    
    String BUSINESS_UNIT_ROUTING_REQUIRED = "error.logCase.routing.business.routing.required";
    
    String BUSINESS_UNIT_ROUTING = "businessUnitdept";
    String USER_WORK_UNIT_NAME_REQUIRED = "error.logCase.routing.user.workunit.name.required";

	 /**Holds message componant id on UI */
    String APPEAL_DTL_TCN_NUMBER = "appealDetails_CNumber";
    
    /**
     * Holds Global error message for Invalid number 
     */
    String ERROR_INVALID_NUMBER = "34-3410-0027";
    
    String CLOSE_CODE_REQ = "error.logCase.closeCodeRequired";
  
    //Error Message for nodes positive to be numeric defect ESPRD00699947
    String ERROR_MSG_FOR_NODES_POSITIVE= "nodePositive.numeric";
    
    /** Holds grpentityName for Mytask */
    String GRPCASE_ENTY_NAME = "entityName";
    //Added for the defect ESPRD00709133
    /** Holds entityName for Mytask */
    String MYCASE_ENTY_NAME = "entityName";
    
	/**
	* Holds the value of success message for reassing case.
	*/
    String REASSIGN_SUCCESS_MSG = "label-sw-success";
    
    /**
	* Error Message for add attachment if don't have privileged 
	*/
    String SAVE_ERROR_MSG = "err.pgm.cm.edms.cascade.validate";
    
    //Added for CR ESPRD00761940
    String BR_CON0426="9-3210-0457";
    
    String BR_CON0427="9-3210-0458";
    
    String BR_CON0428="9-3210-0459";
    
    //Added for Defect ESPRD00782377
    String INVALID_DATE_FORMATE_FOR_APPEAL="9-3210-0220";
    //Added for Defect ESPRD00786993
    String AMPM_ADD_ID = "amPm";
    
    String AMPM_EDIT_ID = "PRGCMGTSOR1";
    //Added for the CR ESPRD00846696
    String CASE_SUBSTANTIALY_CMPLETED_DATE_REQUIRED="error.logacase.substantialyCompletedDate.required";
    //Added for defect ESPRD00873448 Starts
    String CT_MGMT_QUICK_LINK_EDMS = "/Enterprise/SearchDocumentRepository";
    String CT_MGMT_QUICK_LINK_CORRES_GEN = "/Enterprise/CorrespondencePage";
    String CT_MGMT_QUICK_LINK_SA = "/Enterprise/ClaimsServiceAuthorizationPage";
    String CT_MGMT_QUICK_LINK_MEM_APPEALS = "/Enterprise/MemberAppealsInquiryPage";
    String CT_MGMT_QUICK_LINK_PRV_APPEALS = "/Enterprise/ProviderAppealsInquiryPage";
    String CT_MGMT_QUICK_LINK_CASE_TRACKING = "/Enterprise/CtMgmtCase";
    String CT_MGMT_QUICK_LINK_CLAIMS_INQ = "/Enterprise/ClaimsInquiryPage";
    String CT_MGMT_QUICK_LINK_BENEFIT_PLAN = "/Enterprise/RulesMgmtBenefitPlanNavigatorPage";
    String CT_MGMT_QUICK_LINK_FIN_ENTITY = "/Enterprise/FinancialAccountingEntityPage";
    String CT_MGMT_QUICK_LINK_EVENT_TRACK = "/Enterprise/SearchEventLogging";
    //Defect ESPRD00873448 Ends
    //Added for the Defect ESPRD00879551
    String BR_TAG="<br/>";
}
