package com.xerox.enterprise.ghs.mmis.rif.rules.program;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.acs.enterprise.common.program.benefitadministration.util.helper.BenefitPlanConstants;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

/**
 * Ruleclass of the rule "PGM4005.9001.NH01"
 * 
 * @author POJO extracted from Class:AddBenefitPlanControllerBean
 */
public class PGM4005_9001_NH01 extends Rule {

	/* Static logger to log a message */
	private static final Logger LOG = Logger.getLogger(PGM4005_9001_NH01.class);

	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method. return -
	 * execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId)
			throws RIFPOJORulesException {

		// Construct rules result object
		RulesResult rulesResult = new RulesResult(ruleId);
		boolean flag=false;
		String currentProcCode;
		String retroProcCode;
		List capitationList;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();
			flag=(Boolean) inputObjs.get(0);
			currentProcCode = (String) inputObjs.get(1);
			retroProcCode = (String) inputObjs.get(2);
			capitationList = (List) inputObjs.get(3);

			if (currentProcCode == null) {
				LOG.error("Input Object CurrentProcCode is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}
			if (retroProcCode == null) {
				LOG.error("Input Object RetroProcCode is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}
			if ((capitationList).isEmpty()) {
				LOG.error("Input Object CapitationList is  null during intialization for the rule "
						+ ruleId);
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
		LOG.info("Executing the actions for the rule PGM4005.9001.NH01"
				+ ruleId);

		if ((currentProcCode != null && StringUtils.isNotEmpty(currentProcCode))
				|| (retroProcCode != null && StringUtils
						.isNotEmpty(retroProcCode))
				|| (capitationList != null && (capitationList).size() > 0)) {
			// setErrorMessage(BenefitPlanConstants.CAPT_CASE_MGMT_DATA_ERROR);
			rulesResult.setReturnValue(BenefitPlanConstants.CAPT_CASE_MGMT_DATA_ERROR);
			flag = false;
		}
		
		rulesResult.setReturnBooleanValue(flag);
		rulesResult.setRuleStatus(true);
		if(LOG.isInfoEnabled())
		{
			LOG.info("End of execution of PGM4005_9001_NH01");
		}
		return rulesResult;

	}

	public static void initialize() throws RIFPOJORulesException {
		// TODO Auto-generated method stub

	}

	public static void shutdown() throws RIFPOJORulesException {

		//System.out.println("shutdown called");

	}

}
