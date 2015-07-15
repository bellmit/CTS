package test.com.cognizant.RIF.rules;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;


/**
 * Ruleclass of the rule "CON0428.0001.01"
 * @author
 * POJO extracted from Class:CMLogCaseControllerBean
 */
public class CON0428_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(CON0428_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
	
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        Date PacketReceivedDate = null;
        boolean flag = false;
        Date currentDate = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            PacketReceivedDate = (Date) inputObjs.get(0);
            if (PacketReceivedDate == null) {
                LOG.error("Input Object PacketReceivedDate is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            flag = (boolean) inputObjs.get(1);
           

            currentDate = (Date) inputObjs.get(2);
            if (currentDate == null) {
                LOG.error("Input Object currentDate is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

        } else {
            LOG.error("Input Context Object is null during intialization for the rule " + ruleId);
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        LOG.info("Executing the actions for the rule " + ruleId);
        if (currentDate != null && PacketReceivedDate.after(currentDate)) {
            flag = false;
            //ContactManagementHelper.setErrorMessage(MaintainContactManagementUIConstants.BR_CON0428, "pcktcal", null, null);
							
        }
		
        rulesResult.setReturnBooleanValue(flag);
        rulesResult.setRuleStatus(true);
        return rulesResult;
		
    }
	
    public static void initialize() throws RIFPOJORulesException {// TODO Auto-generated method stub
    }

    public static void shutdown() throws RIFPOJORulesException {

        System.out.println("shutdown called");
		
    }
	
}
