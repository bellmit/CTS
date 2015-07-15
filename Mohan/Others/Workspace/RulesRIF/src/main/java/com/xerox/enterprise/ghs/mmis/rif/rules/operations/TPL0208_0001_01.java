package com.xerox.enterprise.ghs.mmis.rif.rules.operations;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

import java.util.Iterator;


/**
 * POJO for the rule "TPL0208.0001.01"
 * @author $authorName
 * @source TPLBillingManager
 */
public class TPL0208_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(TPL0208_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        boolean ismatched = false;
        Iterator iterator = null;
        String fincia = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            ismatched = (Boolean) inputObjs.get(0);

            iterator = (Iterator) inputObjs.get(1);
            if (iterator == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object iterator is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            fincia = (String) inputObjs.get(2);
            if (fincia == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object fincia is  null during intialization for the rule " + ruleId);
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
        // EXTRACT_START TPL0208.0001.01
        if (fincia.equalsIgnoreCase(iterator.next().toString())) {
            ismatched = true;
        }
 
        rulesResult.setReturnBooleanValue(ismatched);
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
