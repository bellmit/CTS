package com.xerox.enterprise.ghs.mmis.rif.rules.operations;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;


/**
 * POJO for the rule "TPL0747.0001.01"
 * @author $authorName
 * @source TPLRecoveryControllerBean
 */
public class TPL0747_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(TPL0747_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult;

        rulesResult = new RulesResult(ruleId);
        String selectedValue = null;
        boolean flag = false;
        ConcurrentHashMap<String, Object> returnmap=new ConcurrentHashMap<String, Object>();
        List memberIDTypeList = null;
        List providerIDList = null;
        List carrierIDTypeList = null;
        List policyholderTypeList = null;
        List employerIDTypeList = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            selectedValue = (String) inputObjs.get(0);
            
            memberIDTypeList = (List) inputObjs.get(1);
            providerIDList = (List) inputObjs.get(2);
            carrierIDTypeList = (List) inputObjs.get(3);
            policyholderTypeList = (List) inputObjs.get(4);
            employerIDTypeList = (List) inputObjs.get(5);
            
            
            

        } else {
            LOG.log(Level.ERROR, "Input Context Object is null during intialization for the rule " + ruleId);
			
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        LOG.log(Level.INFO, "Executing the actions for the rule:" + ruleId);
		
        // EXTRACT_START TPL0747.0001.01
        if (selectedValue.equalsIgnoreCase("M")) {
            
            returnmap.put("EntityIdType",memberIDTypeList);
            returnmap.put("RenderEntity", true);
            returnmap.put("RenderEntitySE", false);
            flag=true;
        } else if (selectedValue.equalsIgnoreCase("P")) {
            
            returnmap.put("EntityIdType",providerIDList);
            returnmap.put("RenderEntity", true);
            returnmap.put("RenderEntitySE", false);
            flag=true;
        } else if (selectedValue.equalsIgnoreCase("TC")) {
            
            returnmap.put("EntityIdType",carrierIDTypeList);
            returnmap.put("RenderEntity", true);
            returnmap.put("RenderEntitySE", false);
            flag=true;
        } else if (selectedValue.equalsIgnoreCase("TP")) {
            
            returnmap.put("EntityIdType",policyholderTypeList);
            returnmap.put("RenderEntity", true);
            returnmap.put("RenderEntitySE", false);
            flag=true;
        } else if (selectedValue.equalsIgnoreCase("TE")) {
            
            returnmap.put("EntityIdType",employerIDTypeList);
            returnmap.put("RenderEntity", true);
            returnmap.put("RenderEntitySE", false);
            flag=true;
        } else {
        	returnmap.put("RenderEntity", false);
            returnmap.put("RenderEntitySE", true);
            
        }
        // EXTRACT_END
        rulesResult.setReturnValue(returnmap);
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
