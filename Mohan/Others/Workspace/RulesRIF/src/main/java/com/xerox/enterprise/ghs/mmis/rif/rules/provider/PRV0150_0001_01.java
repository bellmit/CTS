package com.xerox.enterprise.ghs.mmis.rif.rules.provider;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

import java.util.List;

import com.acs.enterprise.mmis.provider.common.domain.Provider;
import com.acs.enterprise.mmis.provider.common.helper.ProviderDataConstants;


/**
 * POJO for the rule "PRV0150.0001.01"
 * @author $authorName
 * @source $ruleDescriptor.getRuleExtractClassName()
 */
public class PRV0150_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(PRV0150_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        Provider providerDO = null;
        List systemList4 = null;
        String prvNumber = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            providerDO = (Provider) inputObjs.get(0);
            if (providerDO == null) {
                LOG.error("Input Object providerDO is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            systemList4 = (List) inputObjs.get(1);
            if (systemList4 == null) {
                LOG.error("Input Object systemList4 is  null during intialization for the rule " + ruleId);
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

            prvNumber = (String) inputObjs.get(2);
            if (prvNumber == null) {
                LOG.error("Input Object prvNumber is  null during intialization for the rule " + ruleId);
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
        // EXTRACT_START PRV0150.0001.01
        // commented as per PRV_ENR_UC_02.1.DEFECT_ESPRD00406442_19FEB10
        // boolean flag4 =getClassSystemValue(systemList3,prvNumber);
        boolean flag4 = getClassSystemValue(systemList4, prvNumber);

        // ends
        if (flag4) {
            providerDO.setTypeClassificationCode(ProviderDataConstants.PRV_ENR_VALUE_FOUR);
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
    
    private static boolean getClassSystemValue(List systemList,String prvNumber){
    	boolean flagClass=false;   

         Iterator itrSysDetailsSet=systemList.iterator();
         while(itrSysDetailsSet.hasNext()){
        	 String endValue=(String) itrSysDetailsSet.next();        	 
         	if(endValue.equals(prvNumber)){
         		flagClass = true;
         		break;
         	}
         }
     
         return flagClass;
    }
	
}
