package com.xerox.enterprise.ghs.mmis.rif.rules.program;


import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.acs.enterprise.common.program.administration.util.helper.ProgramCodeMaintanaceConstants;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;


/**
 * POJO for the rule "PGM9004.0021.01"
 * @author $authorName
 * @source AddProcedureCodeControllerBean
 */
public class PGM9004_0021_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(PGM9004_0021_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult;

        rulesResult = new RulesResult(ruleId);
        String maxAllowAmount = null;
        String factorCode = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();
            maxAllowAmount = (String) inputObjs.get(0);
            factorCode = (String) inputObjs.get(1);
            
        } else {
            LOG.log(Level.ERROR, "Input Context Object is null during intialization for the rule " + ruleId);
			
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        LOG.log(Level.INFO, "Executing the actions for the rule:" + ruleId);
        rulesResult.setReturnBooleanValue(true);
        // EXTRACT_START PGM9004.0021.01
        if (factorCode != null && factorCode.equals("G8") && maxAllowAmount != null
                && (Double.valueOf(maxAllowAmount) < Double.valueOf(0.0) || Double.valueOf(maxAllowAmount) > Double.valueOf(1.0))) {
				
            /*procedureCodeDataBean.setValidateFlag(false);
            setErrorMessage(ProgramCodeMaintanaceConstants.PRICING_CODE_AMOUNT_CHECK, new Object[] {}, ProgramConstants.PROC_CODE_PROPERTIES, maxAmount);*/
        	rulesResult.setReturnValue(ProgramCodeMaintanaceConstants.PRICING_CODE_AMOUNT_CHECK);
        	rulesResult.setReturnBooleanValue(false);
        }
        // EXTRACT_END

		
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