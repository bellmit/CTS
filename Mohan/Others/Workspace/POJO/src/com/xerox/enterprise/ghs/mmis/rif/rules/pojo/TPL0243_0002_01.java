package com.xerox.enterprise.ghs.mmis.rif.rules.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

/**
 * Ruleclass of the rule "TPL0243.0002.01"
 * 
 * @author POJO extracted from Class:TPLRecoveryCommonValidator
 */
public class TPL0243_0002_01 extends Rule {

	/* Static logger to log a message */
	private static final Logger log = Logger.getLogger(TPL0243_0002_01.class);

	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method. return -
	 * execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId)
			throws RIFPOJORulesException {

		// Construct rules result object
		RulesResult rulesResult = new RulesResult(ruleId);
		String caseClaimStartDate = null;
		boolean result = false;
		String hearingReqDate = null;
		SimpleDateFormat sd = null;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();

			caseClaimStartDate = (String) inputObjs.get(0);
			if (caseClaimStartDate == null) {
				log.error("Input Object caseClaimStartDate is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			result = (Boolean) inputObjs.get(1);
			hearingReqDate = (String) inputObjs.get(2);
			if (hearingReqDate == null) {
				log.error("Input Object hearingReqDate is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			sd = (SimpleDateFormat) inputObjs.get(3);
			if (sd == null) {
				log.error("Input Object sd is  null during intialization for the rule "
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
		// EXTRACT_START TPL0243.0002.01
		try {
			Date hearingReqDt = sd.parse(hearingReqDate);
			Date caseClaimStartDt = sd.parse(caseClaimStartDate);
			if (hearingReqDt.before(caseClaimStartDt)) {
				/*TPLAdministrationHelper
						.setAddHIPPInfoMessageToId(
								TPLRecoveryCaseConstants.TPL_RECOVERY_LEGAL_LEGALINFO,
								"hearingRequest",
								false,
								TPLRecoveryCaseConstants.ADDRECOVERYCASEPPortletResource);
			*/	// TPLAdministrationHelper.setAddRecoveryInfo(TPLRecoveryCaseConstants.TPL_RECOVERY_LEGAL_LEGALINFO);
				result = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// EXTRACT_END

		rulesResult.setReturnBooleanValue(result);
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
