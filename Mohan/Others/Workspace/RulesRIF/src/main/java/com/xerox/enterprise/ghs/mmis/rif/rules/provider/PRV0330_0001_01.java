package com.xerox.enterprise.ghs.mmis.rif.rules.provider;

import java.util.List;

import org.apache.log4j.Logger;

import com.acs.enterprise.mmis.provider.common.helper.ProviderDataConstants;
import com.acs.enterprise.mmis.provider.enrollment.application.exception.ApplicationLockedException;
import com.acs.enterprise.mmis.provider.enrollment.common.delegate.ProviderEnrollmentDelegate;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

/**
 * Ruleclass of the rule "PRV0330.0001.01"
 * 
 * @author POJO extracted from Class:RecallApplicationControllerBean
 */
public class PRV0330_0001_01 extends Rule {

	/* Static logger to log a message */
	private static final Logger LOG = Logger.getLogger(PRV0330_0001_01.class);

	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method. return -
	 * execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId)
			throws RIFPOJORulesException {

		// Construct rules result object
		RulesResult rulesResult = new RulesResult(ruleId);
		String applicationNumber=null;
		boolean flag = false;
		
		ProviderEnrollmentDelegate providerEnrollmentDelegate = null;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();

			applicationNumber = (String) inputObjs.get(0);
			if (applicationNumber == null) {
				LOG.error("Input Object recallProviderData is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			providerEnrollmentDelegate = (ProviderEnrollmentDelegate) inputObjs.get(1);
			if (providerEnrollmentDelegate == null) {
				LOG.error("Input Object providerEnrollmentDelegate is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

		} else {
			LOG.error("Input Context Object is null during intialization for the rule "
					+ ruleId);
			throw new RIFPOJORulesException(
					"Unable to initialize the required rules objects for rule "
							+ ruleId);
		}
		LOG.info("Executing the actions for the rule " + ruleId);
		
		
		try {
			flag = providerEnrollmentDelegate
					.getApplicationLockedDetail(applicationNumber);
		
			if (flag == false) {
				rulesResult.setReturnValue(ProviderDataConstants.PRV_ENR_FAILURE);
			}
	
			rulesResult.setRuleStatus(true);
		} catch (ApplicationLockedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rulesResult;

	}

	public static void initialize() throws RIFPOJORulesException {
		// TODO Auto-generated method stub

	}

	public static void shutdown() throws RIFPOJORulesException {

		System.out.println("shutdown called");

	}

}
