package com.xerox.enterprise.ghs.mmis.rif.rules.program;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.acs.enterprise.common.program.administration.util.helper.ProgramCodeMaintanaceConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

/**
 * POJO for the rule "PGM9001.0018.01"
 * 
 * @author $396662
 * @source DiagnosisCodeControllerBean
 */
public class PGM9001_0018_01 extends Rule {

	/* Static logger to log a message */
	private static final Logger LOG = Logger.getLogger(PGM9001_0018_01.class);

	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method. return -
	 * execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId)
			throws RIFPOJORulesException {

		// Construct rules result object
		RulesResult rulesResult;

		rulesResult = new RulesResult(ruleId);
		String maxMonth = null;
		boolean isNumeric = false;
		String minYr = null;
		String maxYr = null;
		String minMonth = null;
		boolean flag=true;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();

			maxMonth = (String) inputObjs.get(0);
			
			isNumeric = (Boolean) inputObjs.get(1);

			minYr = (String) inputObjs.get(2);

			maxYr = (String) inputObjs.get(3);

			minMonth = (String) inputObjs.get(4);

		} else {
			LOG.log(Level.ERROR,
					"Input Context Object is null during intialization for the rule "
							+ ruleId);

			throw new RIFPOJORulesException(
					"Unable to initialize the required rules objects for rule "
							+ ruleId);
		}
		LOG.log(Level.INFO, "Executing the actions for the rule:" + ruleId);

		
		/** Checks Maximum age to be greater than Minimum Age */
		if (minYr != null && minMonth != null && maxYr != null
				&& maxMonth != null && isNumeric) {

			int minAge = (Integer.parseInt(minYr) * ProgramNumberConstants.INT_TWELVE)
					+ Integer.parseInt(minMonth);
			int maxAge = (Integer.parseInt(maxYr) * ProgramNumberConstants.INT_TWELVE)
					+ Integer.parseInt(maxMonth);
			System.out.println("minage"+minAge+" maxage"+maxAge);
			if (minAge > maxAge) {

				/*
				 * setErrorMessage(ProgramCodeMaintanaceConstants.
				 * MINAGE_GREATER_THAN_MAXAGE, new Object[] {},
				 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
				 * maxMnthCompID); diagnosisCodeDataBean.setDataFlag(false);
				 */
				rulesResult.setReturnValue(ProgramCodeMaintanaceConstants.MINAGE_GREATER_THAN_MAXAGE);
				flag = false;
			}
		}
		rulesResult.setRuleStatus(true);
		rulesResult.setReturnBooleanValue(flag);
		return rulesResult;

	}

	/*
	 * Method to initialize rule
	 */
	public static void initialize() throws RIFPOJORulesException {// TODO
																	// Auto-generated
																	// method
																	// stub
	}

	/*
	 * Method to shutdown rule
	 */
	public static void shutdown() throws RIFPOJORulesException {// TODO
																// Auto-generated
																// method stub
	}

}
