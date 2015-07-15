package com.xerox.enterprise.ghs.mmis.rif.rules.pojo;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
	private static final Logger log = Logger.getLogger(PGM4005_9001_NH01.class);

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
		String CurrentProcCode;
		String RetroProcCode;
		List CapitationList;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();
			flag=(Boolean) inputObjs.get(0);
			CurrentProcCode = (String) inputObjs.get(1);
			RetroProcCode = (String) inputObjs.get(2);
			CapitationList = (List) inputObjs.get(3);

			if (CurrentProcCode == null) {
				log.error("Input Object CurrentProcCode is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}
			if (RetroProcCode == null) {
				log.error("Input Object RetroProcCode is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}
			if ((CapitationList).isEmpty()) {
				log.error("Input Object CapitationList is  null during intialization for the rule "
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
		log.info("Executing the actions for the rule PGM4005.9001.NH01"
				+ ruleId);

		if ((CurrentProcCode != null && StringUtils.isNotEmpty(CurrentProcCode))
				|| (RetroProcCode != null && StringUtils
						.isNotEmpty(RetroProcCode))
				|| (CapitationList != null && (CapitationList).size() > 0)) {
			// setErrorMessage(BenefitPlanConstants.CAPT_CASE_MGMT_DATA_ERROR);
			rulesResult.setReturnValue(BenefitPlanConstants.CAPT_CASE_MGMT_DATA_ERROR);
			flag = false;
		}
		
		rulesResult.setReturnBooleanValue(flag);
		rulesResult.setRuleStatus(true);
		log.info("End of execution of PGM4005_9001_NH01");
		return rulesResult;

	}

	public static void initialize() throws RIFPOJORulesException {
		// TODO Auto-generated method stub

	}

	public static void shutdown() throws RIFPOJORulesException {

		System.out.println("shutdown called");

	}

}
