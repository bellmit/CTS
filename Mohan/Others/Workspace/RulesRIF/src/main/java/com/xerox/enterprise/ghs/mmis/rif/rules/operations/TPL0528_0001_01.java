package com.xerox.enterprise.ghs.mmis.rif.rules.operations;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import org.apache.commons.lang.StringUtils;

import java.util.Date;


/**
 * POJO for the rule "TPL0528.0001.01"
 * @author $authorName
 * @source TPLHIPPCommonValidator
 */
public final class TPL0528_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(TPL0528_0001_01.class);
    private TPL0528_0001_01(){
    	super();
    }
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(final RulesContext ctx, final String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
       
        Date hearingHeldDate = null;
        boolean flag = false;
        Date hearingReqDate = null;
        String referenceNumber = null;
        String legalStatus = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            hearingHeldDate = (Date) inputObjs.get(0);
          

            flag = (Boolean) inputObjs.get(1);

            hearingReqDate = (Date) inputObjs.get(2);
           
            referenceNumber = (String) inputObjs.get(3);
         
            legalStatus = (String) inputObjs.get(4);
           

        } else {
        	LOG.log(Level.ERROR,"Input Object systemList is  null during intialization for the rule " + ruleId);
            
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        LOG.log(Level.INFO,"Executing the actions for the rule " + ruleId);
        RulesResult rulesResult = new RulesResult(ruleId);
        
       
         if (StringUtils.isNotEmpty(legalStatus) && (StringUtils.isEmpty(referenceNumber) || hearingReqDate == null && hearingHeldDate == null)) 
         {
   
                flag = true;
              
         }
          
        rulesResult.setReturnBooleanValue(flag);
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
