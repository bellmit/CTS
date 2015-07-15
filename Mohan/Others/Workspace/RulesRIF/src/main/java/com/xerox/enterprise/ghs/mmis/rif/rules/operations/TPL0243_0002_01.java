package com.xerox.enterprise.ghs.mmis.rif.rules.operations;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

import java.util.Date;


/**
 * POJO for the rule "TPL0243.0002.01"
 * @author $authorName
 * @source TPLRecoveryCommonValidator
 */
public class TPL0243_0002_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(TPL0243_0002_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult;

        rulesResult = new RulesResult(ruleId);
        boolean result = false;
        Date hearingReqDt = null;
        Date caseClaimStartDt = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();
            hearingReqDt = (Date) inputObjs.get(0);
           
            if (hearingReqDt == null) {
                LOG.log(Level.ERROR, "Input Object hearingReqDt is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            caseClaimStartDt = (Date) inputObjs.get(1);
            if (caseClaimStartDt == null) {
                LOG.log(Level.ERROR, "Input Object caseClaimStartDt is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

        } else {
            LOG.log(Level.ERROR, "Input Context Object is null during intialization for the rule " + ruleId);
			
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        LOG.log(Level.INFO, "Executing the actions for the rule:" + ruleId);
		
        
        if (hearingReqDt.before(caseClaimStartDt)) {
            // TPLAdministrationHelper.setAddRecoveryInfo(TPLRecoveryCaseConstants.TPL_RECOVERY_LEGAL_LEGALINFO);
            result = true;
        }
        

		
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
