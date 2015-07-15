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
 * POJO for the rule "TPL0527.0001.01"
 * @author $authorName
 * @source TPLRecoveryCommonValidator
 */
public class TPL0527_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(TPL0527_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        String hearingHeldDate = null;
        boolean result = false;
        String hearingReqDate = null;
        String referenceNumber = null;
        String legalStatus = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            hearingHeldDate = (String) inputObjs.get(0);
           /* if (hearingHeldDate == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object hearingHeldDate is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }*/

            result = (Boolean) inputObjs.get(1);

            hearingReqDate = (String) inputObjs.get(2);
            /*if (hearingReqDate == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object hearingReqDate is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }*/

            referenceNumber = (String) inputObjs.get(3);
           /* if (referenceNumber == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object referenceNumber is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }*/

            legalStatus = (String) inputObjs.get(4);
            /*if (legalStatus == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object legalStatus is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }*/

        } else {
            if (LOG.isEnabledFor(Level.ERROR)) {
                LOG.error("Input Context Object is null during intialization for the rule " + ruleId);
            }
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("Executing the actions for the rule " + ruleId);
        }
   
        try { 
        	
        	if(StringUtils.isNotEmpty(legalStatus) && (StringUtils.isNotEmpty(hearingReqDate) || StringUtils.isNotEmpty(hearingHeldDate) ||StringUtils.isNotEmpty(referenceNumber)))
        	{
        		result = true;
        	}
        } catch (Exception e) {
            e.printStackTrace();
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
