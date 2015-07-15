package com.xerox.enterprise.ghs.mmis.rif.rules.provider;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.mmis.provider.common.helper.ProviderDataConstants;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

/**
 * Ruleclass of the rule "PRV0552.0002.01"
 * 
 * @author POJO extracted from Class:PrvMntEventsControllerBean
 */
public class PRV0552_0002_01 extends Rule {

	/* Static logger to log a message */
	private static final Logger LOG = Logger.getLogger(PRV0552_0002_01.class);

	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method. return -
	 * execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId)
			throws RIFPOJORulesException {

		// Construct rules result object
		RulesResult rulesResult = new RulesResult(ruleId);
		Date mostRecentEventVoM580M590EventDate = null;
		Date mostRecentEventVoS509Date = null;
		boolean isInvalidEvent = false;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();

			mostRecentEventVoM580M590EventDate = (Date) inputObjs.get(0);
			if (mostRecentEventVoM580M590EventDate == null) {
				if (LOG.isEnabledFor(Level.ERROR)) {
					LOG.error("Input Object mostRecentEventVoM580_M590_EventDate is  null during intialization for the rule "
						+ ruleId);
				}
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}
			
			mostRecentEventVoS509Date = (Date) inputObjs.get(1);
			if (mostRecentEventVoS509Date == null) {
				
				if(LOG.isDebugEnabled())
				{
					LOG.debug("Input Object mostRecentEventVoS509Date is  null during intialization for the rule "
						+ ruleId);
				}
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

		} else {
			if (LOG.isEnabledFor(Level.ERROR)) {
				LOG.error("Input Context Object is null during intialization for the rule "
					+ ruleId);
			}
			throw new RIFPOJORulesException(
					"Unable to initialize the required rules objects for rule "
							+ ruleId);
		}
		if (LOG.isEnabledFor(Level.INFO)) {
			LOG.info("Executing the actions for the rule " + ruleId);
		}
		if ((mostRecentEventVoM580M590EventDate
				.equals(mostRecentEventVoS509Date) || mostRecentEventVoM580M590EventDate
				.after(mostRecentEventVoS509Date))) {
			if (LOG.isEnabledFor(Level.INFO)) {
				LOG.info("User is adding M580 or M590 and no S509 event exist");
			}
			/*if (logger.isDebugEnabled()) {
				logger.debug("Already system as one M580 or M590 record whose date is greater the most recent S509 event date");
			}*/

			isInvalidEvent = true;
			rulesResult.setReturnValue(ProviderDataConstants.EVENT_OUT_OF_SEQ);
			/*ProviderInformationHelper
					.errorMessage(ProviderDataConstants.EVENT_OUT_OF_SEQ);*/
		}

		rulesResult.setReturnBooleanValue(isInvalidEvent);
		rulesResult.setRuleStatus(true);
		return rulesResult;

	}

	public static void initialize() throws RIFPOJORulesException {
		// TODO Auto-generated method stub

	}

	public static void shutdown() throws RIFPOJORulesException {

		//System.out.println("shutdown called");

	}

}
