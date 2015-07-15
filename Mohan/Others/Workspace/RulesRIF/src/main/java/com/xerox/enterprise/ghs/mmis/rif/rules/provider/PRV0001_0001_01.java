package com.xerox.enterprise.ghs.mmis.rif.rules.provider;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

import com.acs.enterprise.common.util.helper.ValidatorConstants;
import com.acs.enterprise.mmis.provider.common.helper.ProviderDataConstants;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * POJO for the rule "PRV0001.0001.01"
 * @author $authorName
 * @source ProviderEnrollmentHelper
 */
public class PRV0001_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(PRV0001_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
    	boolean returnBoolean=false;
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        String expression = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            expression = (String) inputObjs.get(0);
            if (expression == null) {
                LOG.error("Input Object expression is  null during intialization for the rule " + ruleId);
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
        // EXTRACT_START PRV0001.0001.01
        Pattern p;
        Matcher m;
        String SSNFORMAT1 = ValidatorConstants.SSN_FORMAT1_PATTERN;
        String SSNFORMAT2 = ValidatorConstants.SSN_FORMAT2_PATTERN;

        if (expression.indexOf(ProviderDataConstants.PR_ROW_INDEX_HYPHEN) == ProviderDataConstants.PR_THREE) {
            p = Pattern.compile(SSNFORMAT1);
            m = p.matcher(expression);
            returnBoolean= m.matches();
        } else {
            p = Pattern.compile(SSNFORMAT2);
            m = p.matcher(expression);
            returnBoolean= m.matches();
        }
        // EXTRACT_END
        rulesResult.setRuleStatus(true);
        rulesResult.setReturnBooleanValue(returnBoolean);
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
