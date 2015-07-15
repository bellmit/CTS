/**
 * 
 */
package com.acs.enterprise.mmis.operations.tpladministration.application.domainmanager;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.acs.enterprise.common.base.application.dataaccess.exception.EnterpriseBaseDataException;
import com.acs.enterprise.common.base.application.dataaccess.factory.EnterpriseDAOFactory;
import com.acs.enterprise.common.base.application.domainmanager.EnterpriseBaseDomainManager;
import com.acs.enterprise.common.base.common.exception.EnterpriseBaseException;
import com.acs.enterprise.common.batch.application.delegate.EnterpriseBatchTrackingDelegate;
import com.acs.enterprise.common.batch.application.domainmanager.EnterpriseBatchTrackingManager;
import com.acs.enterprise.common.batch.process.helper.BatchTrackingConstants;
import com.acs.enterprise.common.program.administration.application.dataaccess.dao.SystemParameterDAO;
import com.acs.enterprise.common.program.administration.application.domainmanager.SystemParameterManager;
import com.acs.enterprise.common.program.administration.application.exception.SystemParameterNotFoundException;
import com.acs.enterprise.common.program.administration.common.vo.SystemParameterInfo;
import com.acs.enterprise.common.program.contactmanagement.common.domain.TMSQRequest;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.mmis.operations.claimpayment.common.util.UserTransactionUtil;
import com.acs.enterprise.mmis.operations.common.domain.ClaimCurrent;
import com.acs.enterprise.mmis.operations.common.domain.EnterpriseClaimLineItem;
import com.acs.enterprise.mmis.operations.common.helper.TPLAdministrationConstants;
import com.acs.enterprise.mmis.operations.tpladministration.application.dataaccess.dao.DataCursor;
import com.acs.enterprise.mmis.operations.tpladministration.application.dataaccess.dao.TPLBillingDAO;
import com.acs.enterprise.mmis.operations.tpladministration.application.dataaccess.daoimpl.TPLBillingDAOImpl;
import com.acs.enterprise.mmis.operations.tpladministration.application.dataaccess.daoimpl.TPLPayandChaseDAOImpl;
import com.acs.enterprise.mmis.operations.tpladministration.application.dataaccess.exception.TPLBillingDBException;
import com.acs.enterprise.mmis.operations.tpladministration.application.exception.TPLBillingBusinessException;
import com.acs.enterprise.mmis.operations.tpladministration.application.exception.TPLHIPPBusinessException;
import com.acs.enterprise.mmis.operations.tpladministration.application.helper.AddPayAndChaseClaim;
import com.acs.enterprise.mmis.operations.tpladministration.application.helper.TPLAPPRulesConstant;
import com.acs.enterprise.mmis.operations.tpladministration.application.helper.TPLBillingAppRules;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBilling;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBillingLineItem;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLCarrier;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLCoveredIndividual;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLRecoveryCaseClaim;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLRecoveryCaseDetail;
import com.acs.enterprise.mmis.operations.tpladministration.common.helper.TPLAdministrationBusinessConstants;

/**
 * This is the domain manager class used for creation, updation of TPL Billing
 * Invoice and search TPL Billing Invoice based on different search criteria. It
 * calls the suitable DAO.
 * 
 * @generated "UML to Java V1.4
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TPLPayAndChaseManager extends EnterpriseBaseDomainManager {
	//	 Methods Written for Add Pay And Chase Claim (10.1) Bill by
	// Madhubabu.A
	private UserTransactionUtil userTrasactionUtil;

	public static String ADDPayAndChaseClaimBill_USERID = "";

	private TPLPayandChaseDAOImpl tplPayandChaseDAO = null;

	private TPLBillingDAO tplBillingDAO = null;

	EnterpriseBatchTrackingDelegate ent = new EnterpriseBatchTrackingDelegate();
	private static EnterpriseLogger logger = EnterpriseLogFactory.getLogger(TPLPayAndChaseManager.class.getName());
	
	/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Starts */
	private static final String ERR_IN_ORIGINAL_CLAIMS = "ERROR IN PROCESSING ORIGINAL CLAIMS";
	/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Starts */
	/**
	 * @return Returns the userTrasactionUtil.
	 */
	public UserTransactionUtil getUserTrasactionUtil() {
		return userTrasactionUtil;
	}

	/**
	 * @return tplBillingDAO TPLBillingDAO
	 * @throws EnterpriseBaseDataException
	 *             EnterpriseBaseDataException
	 */
	private TPLBillingDAO getBillingDAO() throws EnterpriseBaseDataException {
		TPLBillingDAO tplBillingDAO = null;
		try

		{
			tplBillingDAO = (TPLBillingDAOImpl) EnterpriseDAOFactory
					.getDAO(TPLBillingDAO.class);

		} catch (EnterpriseBaseDataException e) {
			throw new EnterpriseBaseDataException(e.getErrorCode(), e);
		}

		return tplBillingDAO;
	}

	/**
	 * @param userTrasactionUtil
	 *            The userTrasactionUtil to set.
	 */
	public void setUserTrasactionUtil(UserTransactionUtil userTrasactionUtil) {
		this.userTrasactionUtil = userTrasactionUtil;
	}

	public String addPayAndChaseClaimBill(String runID) throws TPLBillingBusinessException 
	{
	    logger.debug("Entering to the addPayAndChaseClaimBill in the Manager");
	    String status = "Success";
	    int count = 0;
//	    Integer recordTrackID =Integer.valueOf(0);
	    DataCursor forExceptionClaimIterator = null;
	    String transactionID = TPLAdministrationConstants.EMPTY;
	    String transactionKeyID = TPLAdministrationConstants.EMPTY;
	    String batchSplitID = null;
	    Date date = new Date();
	    TPLBillingAppRules tplBillingAppRules = new TPLBillingAppRules();
	    ClaimCurrent claimCurrent = null;
	    AddPayAndChaseClaim addPayAndChase = null;
	    ADDPayAndChaseClaimBill_USERID = "B-TPL-Pay-and-Chase_" + runID;
	    int enterpriseClaimListSize = 0;
	    int caseId = 0;	
	    try {
		userTrasactionUtil.begin();
		tplBillingDAO = getBillingDAO();
		SystemParameterManager sysParamMngr = new SystemParameterManager();
		Date sysPramDate = null;
		//External -API Modified for System Parameter
		List systemParamList = sysParamMngr.getSpecificSystemParameterForTPL(Long.valueOf(TPLAdministrationBusinessConstants.DAILY_CYCLE_DATE),
			TPLAdministrationBusinessConstants.FA,TPLAdministrationBusinessConstants.LOBCODE,date);
		if(systemParamList != null && !systemParamList.isEmpty()) {
		    SystemParameterInfo parameterInfo = (SystemParameterInfo)  systemParamList.get(0);
		    if (parameterInfo !=null && parameterInfo.getValueDate() != null)
		    {
			sysPramDate = parameterInfo.getValueDate();

		    }
		}			
		if (sysPramDate != null) {
		    transactionKeyID = runID + TPLAdministrationConstants.EMPTY+ enterpriseClaimListSize;
		    transactionID = runID;
		    transactionID = ent.addTransactionInfo(batchSplitID, runID,transactionKeyID,Long.valueOf(enterpriseClaimListSize));
		    //		    recordTrackID = ent.addRecordInfo(new Long(transactionID),"T", "L", BatchTrackingConstants.INPROGRESS);
		    caseId = tplBillingDAO.getNextRecvryCaseSeq();
		    userTrasactionUtil.commit();

		    addPayAndChase = new AddPayAndChaseClaim();
		    addPayAndChase.setCaseUserID(caseId);
		    tplPayandChaseDAO = new TPLPayandChaseDAOImpl();
		    //Modified the passing parmater
		    forExceptionClaimIterator = tplPayandChaseDAO.getAddPayChaseClaimDtl(sysPramDate,TPLAdministrationConstants.ORG_CLAM_IND);
		    userTrasactionUtil.begin();
		    List systemCodeList = tplBillingAppRules.getSystemList(TPLAPPRulesConstant.param8023,TPLAPPRulesConstant.TPL_FUNCT_AREA_CODE);
		    while (forExceptionClaimIterator.hasNext()) 
		    {
			enterpriseClaimListSize++;
			claimCurrent = (ClaimCurrent) forExceptionClaimIterator.next();
			processOriginalClaims(claimCurrent,tplBillingAppRules, addPayAndChase,systemCodeList);
			count++;
			//Defect ESPRD00944210 - Modified the count of records to commit
			if (count == TPLAdministrationBusinessConstants.MAX_COMMIT_SIZE) 
			{
			    addPayAndChaseBatchUpdates(addPayAndChase,ADDPayAndChaseClaimBill_USERID);
			    count = 0;
			    userTrasactionUtil.commit();
			    userTrasactionUtil.begin();
			    addPayAndChase = resetAddPayAndChase(addPayAndChase);
			}
		    }
		    addPayAndChaseBatchUpdates(addPayAndChase,ADDPayAndChaseClaimBill_USERID);
		    userTrasactionUtil.commit();		   

		    count = 0;
		    forExceptionClaimIterator = null;
		    addPayAndChase = resetAddPayAndChase(addPayAndChase);
		    //Modified the code for system parameter
		    forExceptionClaimIterator = tplPayandChaseDAO.getAddPayChaseClaimDtl(sysPramDate,TPLAdministrationConstants.RPLC_CLAM_IND);
		    userTrasactionUtil
		    .begin();
		    while (forExceptionClaimIterator.hasNext()) 
		    {
			enterpriseClaimListSize++;			
			claimCurrent = (ClaimCurrent) forExceptionClaimIterator.next();
			processReplacementClaim(claimCurrent,tplBillingAppRules, addPayAndChase,systemCodeList);
			count++;
			//Defect ESPRD00944210 - Modified the count of records to commit
			if (count == TPLAdministrationBusinessConstants.MIN_COMMIT_SIZE) {
			    addPayAndChaseBatchUpdates(addPayAndChase,ADDPayAndChaseClaimBill_USERID);
			    userTrasactionUtil.commit();
			    userTrasactionUtil.begin();
			    count = 0;
			    addPayAndChase = resetAddPayAndChase(addPayAndChase);
			}
		    }
		    addPayAndChaseBatchUpdates(addPayAndChase,ADDPayAndChaseClaimBill_USERID);
		    userTrasactionUtil.commit();
		    
		    logger.debug("Replacement Claims Completed Successfully.........");

		    //Void Claims
		    logger.debug("For Void Claims:::::::::::::::::::::::::::::::");
		    count = 0;
		    forExceptionClaimIterator = null;
		    addPayAndChase = resetAddPayAndChase(addPayAndChase);
		    logger.debug("111-Before calling processVoidClaim");
		    forExceptionClaimIterator = tplPayandChaseDAO.getAddPayChaseClaimDtl(sysPramDate,TPLAdministrationConstants.VOID_CLAM_IND);
		    userTrasactionUtil.begin();
		    while (forExceptionClaimIterator.hasNext()) {
			enterpriseClaimListSize++;
			claimCurrent = (ClaimCurrent) forExceptionClaimIterator.next();	
			processVoidClaim(claimCurrent, tplBillingAppRules,addPayAndChase);
			count++;
			//Defect ESPRD00944210 - Modified the count of records to commit
			if (count == TPLAdministrationBusinessConstants.MAX_COMMIT_SIZE) {
			    addPayAndChaseBatchUpdates(addPayAndChase,ADDPayAndChaseClaimBill_USERID);
			    count = 0;
			    userTrasactionUtil.commit();
			    userTrasactionUtil.begin();
			    addPayAndChase = resetAddPayAndChase(addPayAndChase);
			}
		    }
		    addPayAndChaseBatchUpdates(addPayAndChase,ADDPayAndChaseClaimBill_USERID);
		}
//		ent.updateRecordInfo(recordTrackID,TPLAdministrationConstants.EMPTY, new Long(transactionID),BatchTrackingConstants.SUCCESS,TPLAdministrationConstants.EMPTY);
		ent.updateTransactionInfo(new Long(transactionID),BatchTrackingConstants.SUCCESS);
		userTrasactionUtil.commit();
	    } catch (Exception e) {
	    	/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Starts */
	    	if(logger.isErrorEnabled()){
	    		logger.error("Exception from addPayAndChaseClaimBill() : ",e);
	    	}
	    	/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Ends */
		try {
		    ent.updateTransactionInfo(new Long(transactionID),BatchTrackingConstants.FAILURE);
		} catch (Exception e1) {
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Starts */
			if(logger.isErrorEnabled()){
				logger.error("Exception from addPayAndChaseClaimBill() : ",e1);
			}
		    /* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Ends */
		}
		throw new TPLBillingBusinessException(e.getMessage(),e);
	    }
	    return status;
	}
	
	private AddPayAndChaseClaim resetAddPayAndChase(AddPayAndChaseClaim addPayAndChase)
	{
	    int caseId = addPayAndChase.getCaseUserID();
	    Map msqSeqMap = addPayAndChase.getMsqSentMap();
	    addPayAndChase = new AddPayAndChaseClaim();
	    addPayAndChase.setCaseUserID(caseId);
	    addPayAndChase.setMsqSentMap(msqSeqMap);
	    return addPayAndChase;
	}

	//Added by Madhu
	public void processOriginalClaims(ClaimCurrent claimCurrent,TPLBillingAppRules tplBillingAppRules,
			AddPayAndChaseClaim addPayAndChase,List systemCodeList)throws TPLBillingBusinessException 
   	{
		logger.debug("999-inside processOriginalClaims");
		TPLCarrier tplCarrier = null;
		TPLCoveredIndividual tplCoveredIndividual = null;
		TPLBilling billing = null;
		List recoveryCaseList = null;
		TPLRecoveryCaseDetail recoveryCase = null;
		//CR- 647377
		double reImburesementAmount =0.0; 
		if(claimCurrent.getClaimParent().getReimbursementAmount()!=null)
		{
			reImburesementAmount = 	claimCurrent.getClaimParent().getReimbursementAmount();
		}
		
		boolean isOrthoCase = false;
		try 
		{
			/*SystemList systemList = systemListManager.getSystemListDetails(new Long(8023),TPLAPPRulesConstant.TPL_FUNCT_AREA_CODE);  
            Set sysLisdetails = systemList.getSystemListDetails();
            List systemCodeList = null;
            if(sysLisdetails!=null)
        	{
            	systemCodeList = new ArrayList();
        		Iterator itr=sysLisdetails.iterator();
        		while(itr.hasNext())
        		{
        		SystemListDetail sysListDetail=(SystemListDetail)itr.next();  
        		systemCodeList.add(sysListDetail.getStartingValue()); 
        		}
        	}*/
			TPLBillingDAO tplBillingDAO = getBillingDAO();
			// 1.Rule TPL0311.0001.01
			tplBillingAppRules.ruleTplBillingAddPayChase0001(claimCurrent.getClaimParent().getMemberSystemID()
					.longValue(), claimCurrent.getClaimParent().getTcn(),claimCurrent.getFDOS(), claimCurrent.getLDOS());
			// 2.Rule TPL0540.0001.01 and 3.Rule TPL0541.0001.01
			//CR-647377
						  
					             //Rule Invocation Context object holds object arrays used in rule execution.
			 
			  			    String ruleId = "TPLRuleDemo";
			    RulesDelegate ruleDelegate = new RulesDelegate(ruleId);
			    //Adding object into rule invocation context.
			 	ruleDelegate.addObject(claimCurrent);
				ruleDelegate.addObject(reImburesementAmount);
	    
			          		   
              //Invoke RIF with ruleid and rule invocation objects.
			  			    RulesResultVO rulesResult = null;
			                
              try {
              		
                     						rulesResult = ruleDelegate.invokeRule();	
					  					  					  
				  					if (!ruleResult.isReturnBooleanValue()) {
					  flag = false;
					}
			   	                } catch (RulesDelegateException re){
                     if("300".equals(re.getErrorCode()))
					 {
						logger.warn("Rule Expired::::"+);
						//If needed perform expires logic here  
					 }
					  logger.info("RulesDelegateException::::"+re);
              }
			  
		
              
              logger.info("Rule Execution Ended::::"+ruleId+"\n");
				List tplCoveredIndividualList = tplBillingDAO.getCoveredIndividuals(claimCurrent.getFDOS(),
						claimCurrent.getLDOS(),claimCurrent.getClaimParent().getMemberSystemID());
				//logger.info("tplCoveredIndividualList.size()"+ tplCoveredIndividualList.size());
				if (tplCoveredIndividualList != null && !tplCoveredIndividualList.isEmpty()) 
				{
					for (int j = 0; tplCoveredIndividualList != null && j < tplCoveredIndividualList.size(); j++) 
					{
						tplCoveredIndividual = (TPLCoveredIndividual) tplCoveredIndividualList.get(j);
						if (tplCoveredIndividual != null && tplCoveredIndividual.getTplPolicyCommonDomain() != null) 
						{
			                isOrthoCase = tplBillingDAO.isBillExistingOrthoCase(systemCodeList,claimCurrent.getClaimParent().getTcn(),claimCurrent.getClaimParent().getMemberSystemID());
			                if(logger.isDebugEnabled()) {
				                logger.debug("OrthoCase Value:::"+isOrthoCase);

			                }

			                if (TPLAdministrationConstants.CLAIM_FRM_CD_63.equalsIgnoreCase(claimCurrent.getClaimFormCode())) 
			                {
								tplCarrier = tplBillingDAO.isCarrierHasOrthoExc(tplCoveredIndividual.getTplPolicyCommonDomain().getCarrierID(),
												           TPLAPPRulesConstant.ORTHO_EXC_IND_YES);
								if (tplCarrier != null &&  isOrthoCase) 
								{
									logger.debug("In the tplCarrier not Null::::");
									if (addPayAndChase.getRecoveryCaseMap().containsKey(claimCurrent.getClaimParent().getMemberSystemID())) 
									{
										logger.debug("Contains Key Value:::");
										recoveryCase = (TPLRecoveryCaseDetail) addPayAndChase.getRecoveryCaseMap().get(claimCurrent.getClaimParent().getMemberSystemID());
									} 
									else 
									{
										recoveryCaseList = tplBillingDAO.getOrthoRecoveryCase1(claimCurrent.getClaimParent().getMemberSystemID().longValue(),
														TPLAPPRulesConstant.RECOVERY_TYPE_CD_ORTHO,claimCurrent.getFDOS(),claimCurrent.getLDOS());
										if (recoveryCaseList != null && !recoveryCaseList.isEmpty())
										{
											recoveryCase = (TPLRecoveryCaseDetail) recoveryCaseList.get(0);
											addPayAndChase.getRecoveryCaseMap().put(claimCurrent.getClaimParent().getMemberSystemID(),recoveryCase);
										}
									}
									if (recoveryCase != null) 
									{
										SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("MM/dd/yyyy");
										if (recoveryCase.getClaimStartDate() != null && claimCurrent.getFDOS() != null) 
										{
											String FDOS = dateformatYYYYMMDD.format( claimCurrent.getFDOS());
											String claimStartDate = dateformatYYYYMMDD.format( recoveryCase.getClaimStartDate());
												Date fDosDate = dateformatYYYYMMDD.parse(FDOS);
												Date claimStartDate1 = dateformatYYYYMMDD.parse(claimStartDate);
												if(fDosDate.before(claimStartDate1))
												{
													logger.info("In the date Update::::");
													recoveryCase.setClaimStartDate(claimCurrent.getFDOS());
													recoveryCase.setAuditUserID(ADDPayAndChaseClaimBill_USERID);
													addPayAndChase.getRecoverCaseUpdateList().add(recoveryCase);
												}
										}
										tplBillingAppRules.genUserAlertMsgForOrtho(recoveryCase);
									} 
									//Modified the code for defect ESPRD00736896
									else 
									 {	

										tplBillingAppRules.ruleTplBillingAddPayChase0004(claimCurrent,addPayAndChase,tplCarrier);

									}
								}
								else 
								{
									if(claimCurrent != null && tplCoveredIndividual != null)
				                	{	
				                		boolean billingExist = tplBillingDAO.checkCarrierIDExist(tplCoveredIndividual.getTplPolicyCommonDomain().getCarrierID(), 
				                				claimCurrent.getClaimParent().getTcn());
				                		if(!billingExist)
				                		{	
				                		billing = setBillingInfo(claimCurrent,tplCoveredIndividual,ADDPayAndChaseClaimBill_USERID);
										if (billing != null) {
											addPayAndChase.getBillingInsList().add(billing);
										}
				                		}
				                	}
								}
							} 
			                else 
			                {
			                	//ESPRD00898228 - to check the billing exist based on tcn number, carrier id and line number to
			               	   // avoid the duplicate records created in the biling table
			                	
			                	if(claimCurrent != null && tplCoveredIndividual != null)
			                	{	
			                		boolean billingExist = tplBillingDAO.checkCarrierIDExist(tplCoveredIndividual.getTplPolicyCommonDomain().getCarrierID(), 
			                				claimCurrent.getClaimParent().getTcn());
			                		if(!billingExist)
			                		{	
			                		billing = setBillingInfo(claimCurrent,tplCoveredIndividual,ADDPayAndChaseClaimBill_USERID);
									if (billing != null) {
										addPayAndChase.getBillingInsList().add(billing);
									}
			                		}
			                	}
							}
						}
					}
				}
			}
			// TplTraumaIndicator set y then we need to do MSQ
			if (claimCurrent.getTplTraumaIndicator() != null && claimCurrent.getTplTraumaIndicator().booleanValue()) 
			{
				tplBillingAppRules.ruleTplBillingAddPayChase0005And0006(addPayAndChase, claimCurrent);
			}
		} 
		catch (TPLBillingBusinessException e) 
		{
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Starts */
			if(logger.isErrorEnabled()){
				logger.error("TPLBillingBusinessException from processOriginalClaims() : ",e);
			}
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Ends */
			/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Starts */
			throw new TPLBillingBusinessException(ERR_IN_ORIGINAL_CLAIMS, e.getMessage());
			/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Ends */
		} 
		catch (TPLBillingDBException e) 
		{
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Starts */
			if(logger.isErrorEnabled()){
				logger.error("TPLBillingDBException from processOriginalClaims() : ",e);
			}
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Ends */
			/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Starts */
			throw new TPLBillingBusinessException(ERR_IN_ORIGINAL_CLAIMS, e.getMessage());
			/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Ends */
		} 
		catch (EnterpriseBaseDataException e) 
		{
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Starts */
			if(logger.isErrorEnabled()){
				logger.error("EnterpriseBaseDataException from processOriginalClaims() : ",e);
			}
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Ends */
			/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Starts */
			throw new TPLBillingBusinessException(ERR_IN_ORIGINAL_CLAIMS, e.getMessage());
			/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Ends */
		}
		/*catch (SystemListNotFoundException e) {
			e.printStackTrace();
			throw new TPLBillingBusinessException("ERROR IN PROCESSING ORIGINAL CLAIMS", e.getMessage());
		}*/ 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Starts */
			if(logger.isErrorEnabled()){
				logger.error("ParseException from processOriginalClaims() : ",e);
			}
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Ends */
			/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Starts */
			throw new TPLBillingBusinessException(ERR_IN_ORIGINAL_CLAIMS, e.getMessage());
			/* PMD Violations - P1 Fix - AvoidDuplicateLiterals - Changed By 428342 Ends */
		}

	}

	/**
	 * System searches for the existence of the original TCN for the replacement
	 * claim in TPL Billing records, Recovery tables and MSQ Request table.
	 * 
	 * @throws TPLBillingBusinessException
	 *  
	 */
	private void processReplacementClaim(ClaimCurrent claimCurrent,TPLBillingAppRules tplBillingAppRules,AddPayAndChaseClaim addPayAndChase,List systemCodeList) throws TPLBillingBusinessException 
	{
		logger.debug("replacement processReplacementClaim");
		List orignlBillRcvryMsqList = null;
		TPLRecoveryCaseClaim rcvryClaim = null;
		List recoveryCaseList = null;
		List msqList = null;
		Long memberSysId = claimCurrent.getClaimParent().getMemberSystemID();
		String excludeResaonCode =null;
		TPLRecoveryCaseDetail recoveryCaseDetail = null;
		//CR-647377
		double reImburesementAmount =0.0; 
		if(claimCurrent.getClaimParent().getReimbursementAmount()!=null)
		{
			reImburesementAmount = 	claimCurrent.getClaimParent().getReimbursementAmount();
		}
		try 
		{
			excludeResaonCode = getSystemParameterForPayAndChase( Long.valueOf(16),TPLAdministrationBusinessConstants.FA);
			/*SystemList systemList = systemListManager.getSystemListDetails(new Long(8023),TPLAPPRulesConstant.TPL_FUNCT_AREA_CODE);  
            Set sysLisdetails = systemList.getSystemListDetails();
            List systemCodeList = null;
            if(sysLisdetails!=null)
        	{
            	systemCodeList = new ArrayList();
        		Iterator itr=sysLisdetails.iterator();
        		while(itr.hasNext())
        		{
        		SystemListDetail sysListDetail=(SystemListDetail)itr.next();  
        		systemCodeList.add(sysListDetail.getStartingValue()); 
        		}
        	}*/
			tplBillingDAO = getBillingDAO();
			orignlBillRcvryMsqList = tplBillingDAO.getBillRcvryMsqOrgnlTcn(claimCurrent.getClaimParent().getTcn());
			if (orignlBillRcvryMsqList != null) 
			{
				if (orignlBillRcvryMsqList.get(0) != null) 
				{
					//added by sunil for CR-ESPRD00516027 - 28.08.10
					billingRecordsForAdjustedSysId(claimCurrent,(List) orignlBillRcvryMsqList
							.get(0), addPayAndChase,systemCodeList,ADDPayAndChaseClaimBill_USERID,excludeResaonCode);
				} 
				//CR-647377
				else if (claimCurrent.getTplPolicyIndicator().booleanValue() && reImburesementAmount != 0.0) 
				{
					logger.debug("111-Insert New Billing Record-->");
					prepareBillingRecordsToInsert(addPayAndChase,systemCodeList,claimCurrent);
				}
				if (orignlBillRcvryMsqList.get(1) != null) 
				{
					String orignlTcnNum = null;
					recoveryCaseList = (List) orignlBillRcvryMsqList.get(1);
					Iterator recoveryCaseListItr = recoveryCaseList.iterator();
					if(logger.isDebugEnabled()) {
						logger.debug("111-recoveryCaseList size is "+recoveryCaseList.size());

					}

					String recoveryCaseId = "";
					while (recoveryCaseListItr.hasNext()) 
					{
						//String recoveryCaseId = null;
						rcvryClaim = (TPLRecoveryCaseClaim) recoveryCaseListItr.next();
						orignlTcnNum =  rcvryClaim.getClaimParent().getTcn();
						recoveryCaseDetail = rcvryClaim.getTplRecoveryCaseDetail();
						if(memberSysId.longValue() == rcvryClaim.getClaimParent().getMemberSystemID().longValue())
						{
							rcvryClaim.setReplacementTCN(claimCurrent.getClaimParent().getTcn());
							addPayAndChase.getRcvryClaimUpdateList().add(rcvryClaim);
							//generate user alert
							if(!recoveryCaseId.equals(recoveryCaseDetail.getCaseUserID()))
							{
								tplBillingAppRules.genUserAlertMsgForRecovery(recoveryCaseDetail);
							}
						}
						else if(memberSysId.longValue()!=rcvryClaim.getClaimParent().getMemberSystemID().longValue())
						{
							if(tplBillingDAO.isRecryHasRespOrRecoveryCaseBillGenerated(orignlTcnNum,rcvryClaim.getLineNumber().intValue(),
									rcvryClaim.getTplRecoveryCaseDetail().getCaseUserID()))
							{   
								//response posted or recovery case bill record generated
								logger.debug("111-response posted is true");
								//exclude matching claim /claim line items with appropriate user code T1-16
								if(excludeResaonCode!=null)
								{
								        rcvryClaim.setClaimInclusiveOrExclusiveCode(TPLAPPRulesConstant.CLAM_INCL_EXC_CD);
									rcvryClaim.setClaimExlusiveReasonCode(excludeResaonCode);
									rcvryClaim.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
									rcvryClaim.setAuditUserID(this.ADDPayAndChaseClaimBill_USERID);
								}
								addPayAndChase.getRcvryClaimUpdateList().add(rcvryClaim);
								
								//generate user alert message.
								if(!recoveryCaseId.equals(recoveryCaseDetail.getCaseUserID()))
								{	
									tplBillingAppRules.genUserAlertMsgForRecovery(recoveryCaseDetail);
								}
							}
							else
							{
								//does not have response posted
								logger.debug("111-does not have response posted");
								addPayAndChase.getRcvryCaseDeleteList().add(rcvryClaim);
								if(!recoveryCaseId.equals(recoveryCaseDetail.getCaseUserID()))
								{
									tplBillingAppRules.genUserAlertMsgForRecovery(recoveryCaseDetail);
								}
							}
						}
						recoveryCaseId  = recoveryCaseDetail.getCaseUserID();
					}
				} 
				else 
				{
					// if associated open recovery case present, sent user alert message.
					logger.debug("111-recovery list is empty");
					tplBillingAppRules.ruleTplBillingAddPayChase0001(claimCurrent.getClaimParent().getMemberSystemID().longValue(),
							claimCurrent.getClaimParent().getTcn(),claimCurrent.getFDOS(),claimCurrent.getLDOS());
				}
				if (orignlBillRcvryMsqList.get(2) != null) 
				{
					msqList = (List) orignlBillRcvryMsqList.get(2);
					Iterator msqlistItr = msqList.iterator();
					while (msqlistItr.hasNext()) 
					{
						addPayAndChase.getTplMSQReqDelList().add((TMSQRequest) msqlistItr.next());
					}
				} 
				if (claimCurrent.getTplTraumaIndicator().booleanValue()) 
				{
					List list = tplBillingDAO.getOpenAccOrTraumaRecCase(claimCurrent.getClaimParent().getMemberSystemID().longValue(),
							TPLAPPRulesConstant.ACC_TRAUMA_RCVRY_TY_CD,TPLAPPRulesConstant.OPEN_CASE_STATUS_CODE,
							claimCurrent.getFDOS(),claimCurrent.getLDOS());
					if (list == null || list.isEmpty()) 
					{
						addPayAndChase.getTplMSQReqInsList().add(tplBillingAppRules.addTplMSQReqInfo(claimCurrent, addPayAndChase));
					}
				}
			} 
			else 
			{
				processOriginalClaims(claimCurrent, tplBillingAppRules,addPayAndChase,systemCodeList);
			}
		} catch (TPLBillingDBException e) {
			throw new TPLBillingBusinessException(
					"ERROR IN PROCESSING REPLACEMENT CLAIMS", e.getMessage());
		} catch (EnterpriseBaseDataException e) {
			throw new TPLBillingBusinessException(
					"ERROR IN PROCESSING REPLACEMENT CLAIMS", e.getMessage());
		}/*catch (SystemListNotFoundException e) {
			e.printStackTrace();
		}*/

	}

	private TPLBilling setBillingInfo(ClaimCurrent claimCurrent,TPLCoveredIndividual tplCoveredIndividual,String userID)
	throws TPLBillingBusinessException 
	{
	    logger.debug("TPLPayAndChaseManager::setBillingInfo()::Starting");
	    TPLBilling billingDO = null;
	    List claimLineList = null;
	    List statusCodes=new ArrayList(2);
	    TPLBillingAppRules billingAppRules=new TPLBillingAppRules();
	   		
	    try {
	    

		tplBillingDAO=getBillingDAO();
		if (tplCoveredIndividual != null && tplCoveredIndividual.getTplPolicyCommonDomain() != null) {
		    //ESPRD00843954 CR
		    boolean coverageCheck  =  tplBillingDAO.getCoveredIndividualCoverageCheck(claimCurrent.getClaimParent().getMemberSystemID(),
			    claimCurrent.getClaimParent().getTcn(),
			    tplCoveredIndividual.getCoverageMemberSK());
		    if(coverageCheck){
			billingDO = new TPLBilling();
			billingDO.setBillingID(tplBillingDAO.getBillingMaxSequence());
			billingDO.setCoveredIndividual(tplCoveredIndividual);
			billingDO.setSystemId(claimCurrent.getClaimParent().getMemberSystemID());
			billingDO.setTcnNum(claimCurrent.getClaimParent().getTcn());
			billingDO.setPolicyHolderID(tplCoveredIndividual.getPolicyHolderID());
			billingDO.setCarrierID(tplCoveredIndividual.getTplPolicyCommonDomain().getCarrierID());
			billingDO.setGroupPolicySeqNum(tplCoveredIndividual.getTplPolicyHolder().getPolicyHolderSeqNumber());
			billingDO.setGroupPolicyNumber(tplCoveredIndividual.getTplPolicyCommonDomain().getGroupPolicyNumber());
			billingDO.setCoverageGrouppolicySK(tplCoveredIndividual.getTplPolicyHolder()
				.getTplGroupPolicy().getPolicyReferenceSK());
			billingDO.setAddedAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
			billingDO.setAddedAuditUserID(userID);
			billingDO.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
			billingDO.setAuditUserID(userID);
			billingDO.setOverageAmount(new Double(0.00));

			String pricingmethCD = tplBillingDAO.getPricingMethodCD(billingDO.getSystemId(),billingDO.getTcnNum());

			//if(TPLAdministrationConstants.CLAIM_FRM_CD_61.equals(claimCurrent.getClaimFormCode()))
			if("H".equals(pricingmethCD))
			{
				//Added for ESPRD00898228 - to avoid the duplicate reocrds created in the Billing line item table for same criteria
				boolean billingLineExist = tplBillingDAO.checkBillingLineExist(billingDO.getCarrierID(), claimCurrent.getClaimParent().getTcn(), 
		    			Integer.valueOf(0));

			    if(!billingLineExist)
			    {
				billingDO.getTplBillingLineItems().add(billingAppRules.setBillingLineItemForPharmacy(billingDO));	
			    }
			}
			else
			{
			    // We need to check paid and to be paid
			    statusCodes.add(TPLAdministrationConstants.CLAIM_PAID_STAT_CD);
			    statusCodes.add(TPLAdministrationConstants.CLAIM_TO_BE_PAID_STAT_CD);
			    
			    claimLineList = tplBillingDAO.getEnterpriseClaimLine(claimCurrent.getClaimParent().getMemberSystemID().longValue(),
				    claimCurrent.getClaimParent().getTcn(),statusCodes);


			    if (claimLineList != null ) 
			    {
				int claimLineListSize = claimLineList.size();
				if(claimLineListSize > 0)
				{
					boolean billingLineExist;
				    for (int i = 0; i < claimLineListSize; i++) 
				    {
				    	//Added for ESPRD00898228 - to avoid the duplicate reocrds created in the Billing line item table for same criteria
				    	 billingLineExist = tplBillingDAO.checkBillingLineExist(billingDO.getCarrierID(), claimCurrent.getClaimParent().getTcn(), 
				    			((EnterpriseClaimLineItem) claimLineList.get(i)).getLineNumber());

				    	if(!billingLineExist)
				    	{
						billingDO.getTplBillingLineItems()
						.add(setBillingLineInfo(billingDO,(EnterpriseClaimLineItem) claimLineList.get(i),
							claimCurrent.getClaimParent().getTcn(),claimCurrent.getClaimParent().getMemberSystemID(),userID));
				    	}
				    }
				}	
			    }
			}
		    }	
		}
	    } catch (EnterpriseBaseDataException e) {
		throw new TPLBillingBusinessException(e);
	    }
	    logger.debug("TPLPayAndChaseManager::setBillingInfo()::Ending");

	    return billingDO;
	}

	private TPLBillingLineItem setBillingLineInfo(TPLBilling billing,
			EnterpriseClaimLineItem claimLine, String tcn, Long memberID,
			String userID) {
		TPLBillingLineItem billingLine = new TPLBillingLineItem();
		billingLine.setLineNumber(claimLine.getLineNumber());
		billingLine.setTplBilling(billing);
		billingLine.setTcnNumber(tcn);
		billingLine.setSystemID(memberID);
		billingLine.setCarrierID(billing.getCarrierID());
		billingLine.setGroupPolicyNumber(billing.getGroupPolicyNumber());
		billingLine.setGrouppolicySK(billing.getCoverageGrouppolicySK());
		billingLine.setPolicyholderSeqNum(billing.getGroupPolicySeqNum());
		billingLine.setAddedAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
		billingLine.setAddedAuditUserID(userID);
		billingLine.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
		billingLine.setAuditUserID(userID);
		return billingLine;
	}

	private void addPayAndChaseBatchUpdates(AddPayAndChaseClaim addPayAndChase,String userID) throws TPLBillingBusinessException 
	{
		List addPayAndChaselist = new ArrayList();
		try {
			tplBillingDAO = getBillingDAO();

			addPayAndChaselist.add(0, addPayAndChase.getBillingInsList());

			addPayAndChaselist.add(1, addPayAndChase.getRcvryCaseInsList());

			addPayAndChaselist.add(2, addPayAndChase.getRcvryPayerInsList());

			addPayAndChaselist.add(3, addPayAndChase.getTplMSQReqInsList());

			addPayAndChaselist.add(4, addPayAndChase.getBillingUpdateList());

			addPayAndChaselist.add(5, addPayAndChase.getRecoverCaseUpdateList());

			// Defect :ESPRD00896928 : Code to remove duplicates
			//addPayAndChaselist.add(6, removeDuplicates(addPayAndChase.getRcvryClaimUpdateList()));
			addPayAndChaselist.add(6, new ArrayList(new LinkedHashSet((addPayAndChase.getRcvryClaimUpdateList()))));

			addPayAndChaselist.add(7, addPayAndChase.getInvcDtlUpdateList());

			addPayAndChaselist.add(8, addPayAndChase.getBillingRespUpdateList());

			addPayAndChaselist.add(9, addPayAndChase.getTplMSQReqDelList());
			if(logger.isDebugEnabled()) {
				logger.debug("addPayAndChase.getBillingDelList() :> "+ addPayAndChase.getBillingDelList().size());

			}

			addPayAndChaselist.add(10, addPayAndChase.getBillingDelList());
			if(logger.isDebugEnabled()) {
				logger.debug("addPayAndChase.getRcvryCaseDeleteList() :> "+ addPayAndChase.getRcvryCaseDeleteList().size());

			}

			addPayAndChaselist.add(11, addPayAndChase.getRcvryCaseDeleteList());

			tplBillingDAO.addPayAndChaseBatchUpdates(addPayAndChaselist,
					ADDPayAndChaseClaimBill_USERID);
		} catch (TPLBillingDBException e) {
			throw new TPLBillingBusinessException(
					"ERROR IN UPDATING PROCCESED RECORDS", e.getMessage());
		}catch (EnterpriseBaseDataException e) {
            
			logger.debug(e.getErrorCode());
			logger.debug(e.getMessage());
		}
	}

	/**
	 * @param enterpriseClaim
	 * @param tplBillingAppRules
	 * @param addPayAndChase
	 * @throws TPLBillingBusinessException
	 */
	public void processVoidClaim(ClaimCurrent claimcurrent,TPLBillingAppRules tplBillingAppRules,
				AddPayAndChaseClaim addPayAndChase) throws TPLBillingBusinessException 
	{
		logger.debug("inside processVoidClaim");
		List orignlBillRcvryMsqList = null;
		List billingList = null;
		List recoveryCaseList = null;
		List tplMsqRequestList = null;
		TPLRecoveryCaseClaim rcvryClaim = null;
		TMSQRequest tplmsqrequest = null;
		String replacedTcnNum = null;
		Map recoveryCaseMap = new HashMap();
		try {
			tplBillingDAO = getBillingDAO();
			//this need to verify.
			orignlBillRcvryMsqList = tplBillingDAO.getBillRcvryMsqOrgnlTcn(claimcurrent.getClaimParent().getTcn());
			if (orignlBillRcvryMsqList != null) 
			{
				if (orignlBillRcvryMsqList.get(0) != null) 
				{
					logger.debug(" void claims orignlBillRcvryMsqList::0");
					billingList = (List) orignlBillRcvryMsqList.get(0);
					Iterator billingListItr = billingList.iterator();
					List billingSkList = new ArrayList();
					while (billingListItr.hasNext()) 
					{
						TPLBilling billing = (TPLBilling) billingListItr.next();
						if (billing.getInvoiceNumber() != null && billing.getBillingClosureReasonCode() == null) 
						{
							// update billing record
							billing.setBillingClosureReasonCode(TPLAPPRulesConstant.BILLING_CLOSURE_RSN_CD_);
							billing.setBillingClosureReasonDate(TPLAPPRulesConstant.SYSTEM_DATE);
							billing.setAuditUserID(TPLPayAndChaseManager.ADDPayAndChaseClaimBill_USERID);
							billing.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
							addPayAndChase.getBillingUpdateList().add(billing);
							billingSkList.add(billing.getBillingID());
						} else if (billing.getInvoiceNumber() == null) {
							// delete billing record
							addPayAndChase.getBillingDelList().add(billing);
						}
					}
					if (!billingSkList.isEmpty()) {
						String tcn = claimcurrent.getClaimParent().getTcn();
						Long memSysID = claimcurrent.getClaimParent().getMemberSystemID();
						String princingMetCD = tplBillingDAO.getPricingMethodCD(memSysID, tcn);
						tplBillingAppRules.createRespForClosedInvoiceSentClaims(addPayAndChase,
								billingSkList,TPLAPPRulesConstant.BILLING_CLOSE_RSN_CD_VOID,princingMetCD);
					}
				}
				if (orignlBillRcvryMsqList.get(1) != null) {
					logger.debug(" void claims orignlBillRcvryMsqList::1");
					recoveryCaseList = (List) orignlBillRcvryMsqList.get(1);
					Iterator recoveryCaseListItr = recoveryCaseList
					.iterator();
					while (recoveryCaseListItr.hasNext()) {
						rcvryClaim = (TPLRecoveryCaseClaim) recoveryCaseListItr
						.next();

							rcvryClaim.setClaimInclusiveOrExclusiveCode(TPLAPPRulesConstant.CLAM_INCL_EXC_CD);
							rcvryClaim.setClaimExlusiveReasonCode(TPLAPPRulesConstant.RCVRY_CLOSE_RSN_CD);
							rcvryClaim.setClaimRequestAmount(new Double(0.0));
							rcvryClaim.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
							rcvryClaim.setAuditUserID(this.ADDPayAndChaseClaimBill_USERID);
							addPayAndChase.getRcvryClaimUpdateList().add(rcvryClaim);

							TPLRecoveryCaseDetail tplRecoveryCaseDetail = rcvryClaim.getTplRecoveryCaseDetail();
							List listOfResponse = tplBillingDAO.getTplRecoveryCaseResponses(tplRecoveryCaseDetail.getCaseUserID(),
											rcvryClaim.getClaimParent().getTcn());
							if (listOfResponse != null) 
							{
								if (listOfResponse.size() == 0) 
								{
								if (recoveryCaseMap.isEmpty()) {
									logger.debug("recoveryCaseMap is Empty:::");
									tplRecoveryCaseDetail.setCaseStateCode("C");
									tplRecoveryCaseDetail.setCaseClosureDate(new Date());
									tplRecoveryCaseDetail.setCaseClosureReasonCode("C45");
									tplRecoveryCaseDetail.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
									tplRecoveryCaseDetail.setAuditUserID(this.ADDPayAndChaseClaimBill_USERID);
									tplBillingAppRules.genUserAlertMsgForRecoveryClose(tplRecoveryCaseDetail);
									recoveryCaseMap.put(tplRecoveryCaseDetail.getCaseUserID(),
											tplRecoveryCaseDetail.getCaseUserID());
									addPayAndChase.getRecoverCaseUpdateList().add(tplRecoveryCaseDetail);
								} else if (!recoveryCaseMap.containsKey(tplRecoveryCaseDetail.getCaseUserID())) {
									logger.debug("recoveryCaseMap is NotEmpty:::");
									tplRecoveryCaseDetail.setCaseStateCode("C");
									tplRecoveryCaseDetail.setCaseClosureDate(new Date());
									tplRecoveryCaseDetail.setCaseClosureReasonCode("C45");
									tplRecoveryCaseDetail.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
									tplRecoveryCaseDetail.setAuditUserID(this.ADDPayAndChaseClaimBill_USERID);
									tplBillingAppRules.genUserAlertMsgForRecoveryClose(tplRecoveryCaseDetail);
									recoveryCaseMap.put(tplRecoveryCaseDetail.getCaseUserID(),
											tplRecoveryCaseDetail.getCaseUserID());
									addPayAndChase.getRecoverCaseUpdateList().add(tplRecoveryCaseDetail);
								}
							}
							}
						}
						if(logger.isInfoEnabled()) {
							logger.info("------------Recovery Update List::::::"+addPayAndChase.getRecoverCaseUpdateList().size());

						}

					}
					if (orignlBillRcvryMsqList.get(2) != null) {
						logger.debug(" void claims orignlBillRcvryMsqList::2");
						tplMsqRequestList = (List) orignlBillRcvryMsqList.get(2);
						if (tplMsqRequestList.size() != 0 && logger.isDebugEnabled()) {
								logger.debug("tplMsqRequestList2 size::"+ tplMsqRequestList.size());

						}
						Iterator tplMsqRequestListItr = tplMsqRequestList.iterator();
						while (tplMsqRequestListItr.hasNext()) {
							tplmsqrequest = (TMSQRequest) tplMsqRequestListItr.next();
							addPayAndChase.getTplMSQReqDelList().add(tplmsqrequest);
						}
					}
				}
		} catch (TPLBillingDBException e) {
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Starts */
			if(logger.isErrorEnabled()){
				logger.error("Exception from processVoidClaim() : ",e);
			}
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Ends */
			throw new TPLBillingBusinessException(
					"ERROR IN PROCESSING VOID CLAIMS", e.getMessage());
		} catch (EnterpriseBaseDataException e) {
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Starts */
			if(logger.isErrorEnabled()){
				logger.error("Exception from processVoidClaim() : ",e);
			}
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Ends */
			throw new TPLBillingBusinessException(
					"ERROR IN PROCESSING VOID CLAIMS", e.getMessage());
		}
	}
	private void billingRecordsForAdjustedSysId(ClaimCurrent claimCurrent,
			List billingList,AddPayAndChaseClaim addPayAndChase,List systemCodeList,String userId,String excludeResaonCode) throws RuntimeException, TPLBillingBusinessException 
	   {
		Long memberSysid = claimCurrent.getClaimParent().getMemberSystemID();
		TPLCoveredIndividual tplCoveredIndividual = null;
		String closureReasonCode=null;
		TPLBillingAppRules billingAppRules=new TPLBillingAppRules();
		boolean insertNewBilling = false;
		boolean isOrthoCase = false;
		TPLCarrier tplCarrier = null;
		List recoveryCaseList = null;
		TPLRecoveryCaseDetail recoveryCase = null;
		String invoiceNumber = null;
		String orignlTcnNum = null;
		//CR-647377
		double reImburesementAmount =0.0; 
		if(claimCurrent.getClaimParent().getReimbursementAmount()!=null)
		{
			reImburesementAmount = 	claimCurrent.getClaimParent().getReimbursementAmount();
		}
		
		try
		{
			tplBillingDAO = getBillingDAO();
			//closure reason code - T1 -15
			closureReasonCode = getSystemParameterForPayAndChase(Long.valueOf(15),TPLAdministrationBusinessConstants.FA);
			Iterator billingListItr=billingList.iterator();
			List billingSkList=new ArrayList();
			if(logger.isDebugEnabled()) {
				logger.debug("000-memberSysid is "+memberSysid);

			}

			//int billingListSize = billingList.size();
			int closedBillingCount =0;
			/*boolean insertBillingInvoice = false;*/
			while(billingListItr.hasNext())
			{
			TPLBilling billingObj=(TPLBilling)billingListItr.next();
			orignlTcnNum = billingObj.getTcnNum();
			if(memberSysid.longValue() != billingObj.getSystemId().longValue())
			{
				if(logger.isDebugEnabled()) {
					logger.debug("111-sysids are not same and billingObj.getSystemId() is -->"+billingObj.getSystemId());

				}

				if(billingObj.getInvoiceNumber() != null && billingObj.getBillingClosureReasonCode() == null)
				{
					    logger.debug("111-invoice number generated and it is not closed->");
					    if(closureReasonCode!=null)
						billingObj.setBillingClosureReasonCode(closureReasonCode);
						billingObj.setBillingClosureReasonDate(TPLAPPRulesConstant.SYSTEM_DATE);
						billingObj.setAuditUserID(userId);
						billingObj.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
						addPayAndChase.getBillingUpdateList().add(billingObj);
						billingSkList.add(billingObj.getBillingID());
						invoiceNumber = billingObj.getInvoiceNumber();
						
				}
				else if(billingObj.getInvoiceNumber()==null)
				{
					logger.debug("111-invoice number not generated");
					addPayAndChase.getBillingDelList().add(billingObj);
				}
				if(claimCurrent.getTplPolicyIndicator().booleanValue() && reImburesementAmount!=0.0 )
				{
					logger.debug("111-policy indicator is true->");
					//insert billing records.
					insertNewBilling = true;		
				}
			}
			else
			{
				if(logger.isDebugEnabled()) {
					logger.debug("222-sysids are same and billingObj.getSystemId() is -->"+billingObj.getSystemId());

				}

				if(claimCurrent.getTplPolicyIndicator().booleanValue() && reImburesementAmount!=0.0)
				{
					logger.debug("222-policy indicator is true->");
					if(billingObj.getInvoiceNumber() == null)
					{
						logger.debug("222-invoice number is not generated");
						addPayAndChase.getBillingDelList().add(billingObj);
						insertNewBilling = true;
					}
					else if(billingObj.getInvoiceNumber() != null && billingObj.getBillingClosureReasonCode() == null)
					{
						billingObj.setReplacedTCNNum(claimCurrent.getClaimParent().getTcn());
						if(closureReasonCode!=null)
						billingObj.setBillingClosureReasonCode(closureReasonCode);
						billingObj.setBillingClosureReasonDate(TPLAPPRulesConstant.SYSTEM_DATE);
						billingObj.setAuditUserID(userId);
						billingObj.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
						addPayAndChase.getBillingUpdateList().add(billingObj);
						billingSkList.add(billingObj.getBillingID());
						insertNewBilling=true;
						invoiceNumber = billingObj.getInvoiceNumber();
					}
				}
				else
				{
					logger.debug("222-policy indicator is false->");
					if(billingObj.getInvoiceNumber() != null && billingObj.getBillingClosureReasonCode() == null)
					{
						logger.debug("222-invoice number is generated");
							if(closureReasonCode!=null)
							billingObj.setBillingClosureReasonCode(closureReasonCode);
							billingObj.setBillingClosureReasonDate(TPLAPPRulesConstant.SYSTEM_DATE);
							billingObj.setAuditUserID(userId);
							billingObj.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
							addPayAndChase.getBillingUpdateList().add(billingObj);
							billingSkList.add(billingObj.getBillingID());
							invoiceNumber = billingObj.getInvoiceNumber();
					}
					
					else if(billingObj.getInvoiceNumber() == null)
					{
						logger.debug("222-invoice number is not generated");
						addPayAndChase.getBillingDelList().add(billingObj);	
					}
				 }
			}
			}
			if(invoiceNumber!=null)
			{
				//check the billing recordList to close invoice
				if(tplBillingDAO.checkBillingRecordsforSameInovieNumber(invoiceNumber,orignlTcnNum))
				{
					tplBillingDAO.updateTplBillingInvoiceDetails(invoiceNumber,TPLAPPRulesConstant.SYSTEM_DATE,TPLAPPRulesConstant.SYSTEM_DATE,closureReasonCode,"C",userId);	
				}
			}
			if(!billingSkList.isEmpty())
			{
				String tcn = claimCurrent.getClaimParent().getTcn();
				Long memSysID = claimCurrent.getClaimParent().getMemberSystemID();
				String princingMetCD = tplBillingDAO.getPricingMethodCD(memSysID, tcn);
				
				billingAppRules.createRespForClosedInvoiceSentClaims(addPayAndChase,billingSkList,
					excludeResaonCode,princingMetCD);
			}
		 if(insertNewBilling) {
		 	 prepareBillingRecordsToInsert(addPayAndChase,systemCodeList,claimCurrent);
		 	}
		 /* PMD Violations - P1 Fix - EmptyIfStmt- Removed emptyIfStmt - Changed By 428342 */
		}
	   catch (EnterpriseBaseDataException e) {
		throw new TPLBillingBusinessException("ERROR IN PROCESSING billingRecordsForAdjustedSysId", e.getMessage());
	} 
	   }
	
	private String getSystemParameterForPayAndChase(Long paramNum, String paramName){
		logger.debug("Method Start ::: TPLPayAndChaseManager.getSystemParameterForPayAndChase");
		String value = null;
		try{
			SystemParameterDAO sysDAO = getSystemParamDAO();			
			//Created new code for system parameter to get only value.
			List systemParameterDetail =sysDAO.getSystemParameterDetailForTPL(paramNum,paramName);
			if(systemParameterDetail != null && systemParameterDetail.size() > 0 ){
				SystemParameterInfo parameterInfo=(SystemParameterInfo)systemParameterDetail.get(0);	
			if (parameterInfo !=null && parameterInfo.getValueData()!= null)
			{
				value = parameterInfo.getValueData();
			}			
			}	
		}catch(EnterpriseBaseException e){
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Starts */
			if(logger.isErrorEnabled()){
				logger.error("Exception from getSystemParameterForPayAndChase() : ",e);
			}
			/* PMD Violations - P1 Fix - AvoidPrintStackTrace - Changed By 428342 Ends */
		}
		logger.debug("Method End ::: TPLPayAndChaseManager.getSystemParameterForPayAndChase");
		return value;
	}
	private SystemParameterDAO getSystemParamDAO() throws TPLHIPPBusinessException 
	{
		SystemParameterDAO tplPayAndChaseDAO = null;
		try 
		{
			tplPayAndChaseDAO = (SystemParameterDAO) EnterpriseDAOFactory
					.getDAO(SystemParameterDAO.class);

	} catch (EnterpriseBaseDataException e) {
		if(logger.isErrorEnabled()) {
			logger.error("Error in getting TPLHIPPCaseDAO " + e);

		}

		throw new TPLHIPPBusinessException(e.getErrorCode(), e);
	}

	return tplPayAndChaseDAO;
}
	private void prepareBillingRecordsToInsert(AddPayAndChaseClaim addPayAndChase,List systemCodeList,ClaimCurrent claimCurrent)throws TPLBillingBusinessException
	{
		boolean isOrthoCase = false;
		TPLCarrier tplCarrier = null;
		List recoveryCaseList = null;
		TPLBilling billing = null;
		TPLCoveredIndividual tplCoveredIndividual = null;
		TPLRecoveryCaseDetail recoveryCase = null;
		TPLBillingAppRules billingAppRules=new TPLBillingAppRules();
		try
		{
			isOrthoCase = tplBillingDAO.isBillExistingOrthoCase(systemCodeList,claimCurrent.getClaimParent().getTcn(),claimCurrent.getClaimParent().getMemberSystemID());
			List tplCoveredIndividualList = tplBillingDAO.getCoveredIndividuals(claimCurrent.getFDOS(), claimCurrent.getLDOS(),claimCurrent.getClaimParent().getMemberSystemID());
			if (tplCoveredIndividualList != null && !tplCoveredIndividualList.isEmpty()) 
			{
				for (int j = 0; tplCoveredIndividualList != null && j < tplCoveredIndividualList.size(); j++) 
				{
					tplCoveredIndividual = (TPLCoveredIndividual) tplCoveredIndividualList.get(j);
					if (tplCoveredIndividual != null && tplCoveredIndividual.getTplPolicyCommonDomain() != null) 
					{
						if (TPLAdministrationConstants.CLAIM_FRM_CD_63.equalsIgnoreCase(claimCurrent.getClaimFormCode())) 
						{
							tplCarrier = tplBillingDAO.isCarrierHasOrthoExc(tplCoveredIndividual.getTplPolicyCommonDomain().getCarrierID(),TPLAPPRulesConstant.ORTHO_EXC_IND_YES);
							if (tplCarrier != null && isOrthoCase) 
							{
								logger.debug("In the tplCarrier not Null::::");
								if (addPayAndChase.getRecoveryCaseMap().containsKey(claimCurrent.getClaimParent().getMemberSystemID())) 
								{
									recoveryCase = (TPLRecoveryCaseDetail) addPayAndChase.getRecoveryCaseMap().get(claimCurrent.getClaimParent().getMemberSystemID());
								} 
								else 
								{
									recoveryCaseList = tplBillingDAO.getOrthoRecoveryCase1(claimCurrent.getClaimParent().getMemberSystemID().longValue(),TPLAPPRulesConstant.RECOVERY_TYPE_CD_ORTHO,claimCurrent.getFDOS(),claimCurrent.getLDOS());
									if (recoveryCaseList != null&& !recoveryCaseList.isEmpty()) 
									{
										recoveryCase = (TPLRecoveryCaseDetail) recoveryCaseList.get(0);
										addPayAndChase.getRecoveryCaseMap().put(claimCurrent.getClaimParent().getMemberSystemID(),recoveryCase);
									}
								}
								if (recoveryCase != null) 
								{
									logger.debug("In the recoveryCase not Null::::");
									if (recoveryCase.getClaimStartDate() != null && claimCurrent.getFDOS().before(recoveryCase.getClaimStartDate())) 
									{
										logger.debug("========---three ------============");
										recoveryCase.setClaimStartDate(claimCurrent.getFDOS());
										addPayAndChase.getRecoverCaseUpdateList().add(recoveryCase);
									}
									billingAppRules.genUserAlertMsgForOrtho(recoveryCase);
								} 
								else if(claimCurrent.getServiceAuthID() != null)
								{	

									billingAppRules.ruleTplBillingAddPayChase0004(claimCurrent,addPayAndChase,tplCarrier);

								}
							} 
							else 
							{
								//ESPRD00898228 - to check the billing exist based on tcn number, carrier id and line number to
				               	   // avoid the duplicate records created in the biling table
				                	
				                	if(claimCurrent != null && tplCoveredIndividual != null)
				                	{	
				                		boolean billingExist = tplBillingDAO.checkCarrierIDExist(tplCoveredIndividual.getTplPolicyCommonDomain().getCarrierID(), 
				                				claimCurrent.getClaimParent().getTcn());
				                		if(!billingExist)
				                		{	
				                		billing = setBillingInfo(claimCurrent,tplCoveredIndividual,ADDPayAndChaseClaimBill_USERID);
										if (billing != null) {
											addPayAndChase.getBillingInsList().add(billing);
										}
				                		}
				                	}
							}
						} 
						else 
						{
							//ESPRD00898228 - to check the billing exist based on tcn number, carrier id and line number to
			               	   // avoid the duplicate records created in the biling table
			                	
			                	if(claimCurrent != null && tplCoveredIndividual != null)
			                	{	
			                		boolean billingExist = tplBillingDAO.checkCarrierIDExist(tplCoveredIndividual.getTplPolicyCommonDomain().getCarrierID(), 
			                				claimCurrent.getClaimParent().getTcn());
			                		if(!billingExist)
			                		{	
			                		billing = setBillingInfo(claimCurrent,tplCoveredIndividual,ADDPayAndChaseClaimBill_USERID);
									if (billing != null) {
										addPayAndChase.getBillingInsList().add(billing);
									}
			                		}
			                	}
						}
					}
				}
			}
		}
		  catch(EnterpriseBaseDataException e){
			logger.debug(e.getErrorCode());
			logger.debug(e.getMessage());
		}
		
	}
	public String invokeGenerateBillStoredProcedure(String runId) throws TPLBillingBusinessException
	 {
		String processStaus = "false";
		try 	
		{
			userTrasactionUtil.begin();
			tplBillingDAO = getBillingDAO();
			int status =tplBillingDAO.invokeGenerateBillStoredProcedure(runId);
			if(status == 0)
			{
				processStaus = "sucess";
			}else if(status == 1)
			{
				logger.debug(" FAILED IN STORED PROCEDURE EXECUTION");
			}else{
				logger.debug(" FAILED IN BEFORE INVOKING STORED PROCEDURE ");
			}
			userTrasactionUtil.commit();
		} 
		catch (EnterpriseBaseDataException e) {
	    	logger.error(e.getMessage(), e);
		 	throw new TPLBillingBusinessException(e.getErrorCode(),e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new TPLBillingBusinessException(e.getMessage(),e.getMessage());
		}
		return processStaus;
	 }
	
	/**
	 * Remove Duplicate entries from List and keeps the last updated entry
	 * @param incomingList
	 * @return uniqueList: List
	 */
	private List removeDuplicates(List incomingList)
	{
		List uniqueList = new ArrayList();
		if (incomingList != null)
		{
			if(logger.isDebugEnabled()) {
				logger.debug(" Size before removing duplicates : " + incomingList.size());

			}

			Iterator itr = incomingList.iterator();
			Map claimMap = new HashMap();
			TPLRecoveryCaseClaim obj = null;
			while (itr.hasNext())
			{
				obj = (TPLRecoveryCaseClaim) itr.next();
				claimMap.put(obj.getClaimParent().getTcn(), obj);
			}
			uniqueList.addAll(claimMap.values());
		}
		if(logger.isDebugEnabled()) {
			logger.debug(" Size after removing Duplicates " + uniqueList.size());

		}

		return uniqueList;
	}
	
  }
