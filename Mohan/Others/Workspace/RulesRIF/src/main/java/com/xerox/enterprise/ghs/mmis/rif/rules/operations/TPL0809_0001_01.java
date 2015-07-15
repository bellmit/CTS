package com.xerox.enterprise.ghs.mmis.rif.rules.operations;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBillingInvoiceSentClaim;

import java.util.List;

import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBillingLineItem;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBillingResponse;
import com.acs.enterprise.mmis.operations.tpladministration.application.domainmanager.TPLPayAndChaseManager;
import com.acs.enterprise.mmis.operations.tpladministration.application.helper.AddPayAndChaseClaim;
import com.acs.enterprise.mmis.operations.tpladministration.application.helper.TPLAPPRulesConstant;


/**
 * POJO for the rule "TPL0809.0001.01"
 * @author $authorName
 * @source TPLBillingAppRules    //TPLBillingResponseComparator
 */
public class TPL0809_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger log = Logger.getLogger(TPL0809_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        List sentClaimsList = null;
        String princingMetCD = null;
        AddPayAndChaseClaim addPayAndChase = null;
        String reasoncode = null;
        TPLBillingInvoiceSentClaim sentClaim = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            sentClaimsList = (List) inputObjs.get(0);
            if (sentClaimsList == null) {
                log.error("Input Object sentClaimsList is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            princingMetCD = (String) inputObjs.get(1);
            if (princingMetCD == null) {
                log.error("Input Object princingMetCD is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            addPayAndChase = (AddPayAndChaseClaim) inputObjs.get(2);
            if (addPayAndChase == null) {
                log.error("Input Object addPayAndChase is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            reasoncode = (String) inputObjs.get(3);
            if (reasoncode == null) {
                log.error("Input Object reasoncode is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            sentClaim = (TPLBillingInvoiceSentClaim) inputObjs.get(4);
            if (sentClaim == null) {
                log.error("Input Object sentClaim is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

        } else {
            if (log.isEnabledFor(Level.ERROR)) {
                log.error("Input Context Object is null during intialization for the rule " + ruleId);
            }
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        if (log.isInfoEnabled()) {
            log.info("Executing the actions for the rule " + ruleId);
        }
        
        if ("H".equalsIgnoreCase(princingMetCD)) {
            sentClaim = (TPLBillingInvoiceSentClaim) sentClaimsList.get(0);
            if (sentClaim != null) {
                TPLBillingResponse billingResponse = new TPLBillingResponse();
                TPLBillingLineItem billingLineItem = new TPLBillingLineItem();

                billingLineItem.setTplBilling(sentClaim.getTplBillingLineItem().getTplBilling());
                billingLineItem.setLineNumber(Integer.valueOf(0));
                billingResponse.setTplBillingLineItem(billingLineItem);
                billingResponse.setSystemID(sentClaim.getSystemID());
                billingResponse.setBillingResponseDate(TPLAPPRulesConstant.SYSTEM_DATE);
                billingResponse.setBillingResponseReasonCode(reasoncode);
                billingResponse.setInvoiceNumber(sentClaim.getTplBillingIndivSent().getTplBillingInvoiceDetail().getInvoiceNumber());
                billingResponse.setTcn(sentClaim.getTcnNumber());
                billingResponse.setCarrierID(sentClaim.getCarrierID());
                billingResponse.setGroupPolicyNumber(sentClaim.getGroupPolicyNumber());
                billingResponse.setGroupPolicySK(sentClaim.getCoverageGrouppolicySK());
                billingResponse.setPolicyHolderSeqNum(sentClaim.getPolicyHolderSeqNum());
                billingResponse.setAddedAuditUserID(TPLPayAndChaseManager.ADDPayAndChaseClaimBill_USERID);
                billingResponse.setAddedAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
                billingResponse.setAuditUserID(TPLPayAndChaseManager.ADDPayAndChaseClaimBill_USERID);
                billingResponse.setAuditTimeStamp(TPLAPPRulesConstant.SYSTEM_DATE);
                addPayAndChase.getBillingRespUpdateList().add(billingResponse);
            }
        }
		
        rulesResult.setRuleStatus(true);
        return rulesResult;
		
    }
	
    /*
     * Method to initialize rule
     */
    public static void initialize() throws RIFPOJORulesException {// TODO Auto-generated method stub
    }

    /*
     * Method to shutdown rule
     */
    public static void shutdown() throws RIFPOJORulesException {// TODO Auto-generated method stub
    }
	
}
