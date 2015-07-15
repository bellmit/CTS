package com.xerox.enterprise.ghs.mmis.rif.rules.pojo;

import java.util.List;

import org.apache.log4j.Logger;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

/**
 * Ruleclass of the rule "EXTRACT_START"
 * 
 * @author POJO extracted from Class:AddMapDefinitionControllerBean
 */
public class PGM4004_9005_NH01 extends Rule {

	/* Static logger to log a message */
	private static final Logger log = Logger.getLogger(PGM4004_9005_NH01.class);

	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method. return -
	 * execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId)
			throws RIFPOJORulesException {

		// Construct rules result object
		RulesResult rulesResult = new RulesResult(ruleId);
		String value = null;
		List systemList = null;
		boolean checkRange = false;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();

			value = (String) inputObjs.get(0);
			if (value == null) {
				log.error("Input Object value is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			systemList = (List) inputObjs.get(1);
			if (systemList == null) {
				log.error("Input Object systemList is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

		} else {
			log.error("Input Context Object is null during intialization for the rule "
					+ ruleId);
			throw new RIFPOJORulesException(
					"Unable to initialize the required rules objects for rule "
							+ ruleId);
		}
		log.info("Executing the actions for the rule " + ruleId);
		// EXTRACT_START PGM4004.9005.NH01
		checkRange = systemList.contains(value);
		// EXTRACT_END
		rulesResult.setReturnBooleanValue(checkRange);
		rulesResult.setRuleStatus(true);
		return rulesResult;

	}

	public static void initialize() throws RIFPOJORulesException {
		// TODO Auto-generated method stub

	}

	public static void shutdown() throws RIFPOJORulesException {

		System.out.println("shutdown called");

	}

}
