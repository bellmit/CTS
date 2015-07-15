package com.xerox.enterprise.ghs.mmis.rif.rules.operations;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

import org.apache.commons.lang.StringUtils;


/**
 * POJO for the rule "TPL0428.0001.01"
 * @author $authorName
 * @source TPLBillingCommonValidator
 */
public class TPL0428_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(TPL0428_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        String recoveryReason = null;
        boolean result = false;
        Double recoveryAmount = 0d;
        String recoveryMethod = null;
        String fcn = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            recoveryReason = (String) inputObjs.get(0);
            if (recoveryReason == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object recoveryReason is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            result = (Boolean) inputObjs.get(1);

            recoveryAmount = (Double) inputObjs.get(2);
            if (recoveryAmount == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object recoveryAmount is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            recoveryMethod = (String) inputObjs.get(3);
            if (recoveryMethod == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object recoveryMethod is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            fcn = (String) inputObjs.get(4);
            if (fcn == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object fcn is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

        } else {
            if (LOG.isEnabledFor(Level.ERROR)) {
                LOG.error("Input Context Object is null during intialization for the rule " + ruleId);
            }
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("Executing the actions for the rule " + ruleId);
        }
        // EXTRACT_START TPL0428.0001.01
        if (recoveryReason != null && 'R' == recoveryReason.charAt(0) && !("R99".equalsIgnoreCase(recoveryReason))) {
            if (recoveryReason != null && StringUtils.isEmpty(recoveryMethod) || recoveryAmount == null || StringUtils.isEmpty(fcn)) {
                result = true;
                // Infinite defect Resolution - ESPRD00652073
                /* TPLAdministrationHelper.setUpdateBillingInfo(TPLBillingConstants.TPL_UPDATE_BILL_RR_STARTWITH_R,"recoveryReason");
                 TPLAdministrationHelper.setUpdateBillingInfo(TPLBillingConstants.TPL_UPDATE_BILL_RR_STARTWITH_R,"clmrecoveryReason");
                 TPLAdministrationHelper.setUpdateBillingInfo(TPLBillingConstants.TPL_UPDATE_BILL_RR_STARTWITH_R,"memRecoveryReason");*/ 
            	
                // TPLAdministrationHelper.setUpdateBillingInfo(TPLBillingConstants.TPL_UPDATE_BILL_RR_STARTWITH_R,"clmrecoveryReason");
            }
        }
        // EXTRACT_END

        rulesResult.setReturnBooleanValue(result);
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
