package com.xerox.enterprise.ghs.mmis.rif.rules.pojo;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

import com.acs.enterprise.mmis.operations.common.domain.ClaimCurrent;

/**
 * POJO for the rule "TPLRuleDemo"
 * @author $authorName
 * @source TPLPayAndChaseManager
 */
public class TPLRuleDemo extends Rule{
	
	/*Static logger to log a message */
	private static final Logger LOG = Logger.getLogger(TPLRuleDemo.class);
	
	
	
	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method.
	 * return - execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
		//Construct rules result object
		RulesResult rulesResult;
		rulesResult = new RulesResult(ruleId);
		ClaimCurrent claimCurrent = null;
			double reImburesementAmount = 0d;

		if (ctx != null) {
				
			List<Object> inputObjs = ctx.getContextObject();

			claimCurrent = (ClaimCurrent) inputObjs.get(0);
if(claimCurrent==null){
LOG.log(Level.ERROR,"Input Object claimCurrent is  null during intialization for the rule "+ruleId);
throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule "+ruleId);
}

				reImburesementAmount = (Double) inputObjs.get(1);


						
		} else {
			LOG.log(Level.ERROR,"Input Context Object is null during intialization for the rule "+ruleId);
			
			throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule "+ruleId);
		}
		LOG.log(Level.INFO,"Executing the actions for the rule:" + ruleId);
		
		//EXTRACT_START TPLRuleDemo
			if (claimCurrent.getTplPolicyIndicator().booleanValue() && reImburesementAmount!= 0.0) 
			{
			//EXTRACT_END

		
				rulesResult.setRuleStatus(true);
		return rulesResult;
		
	}
	
	/*
	* Method to initialize rule
	*/
	public static void initialize() throws RIFPOJORulesException {
		// TODO Auto-generated method stub
		
	}

	/*
	* Method to shutdown rule
	*/
	public static void shutdown() throws RIFPOJORulesException {
		// TODO Auto-generated method stub
		
	}
	
	}
