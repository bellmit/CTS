package com.xerox.enterprise.ghs.mmis.rif.rules.program;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.acs.enterprise.common.program.administration.util.helper.ProgramCodeMaintanaceConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

/**
 * POJO for the rule "PGM9001.0016.01"
 * 
 * @author $authorName
 * @source DiagnosisCodeControllerBean
 */
public class PGM9001_0016_01 extends Rule {

	/* Static logger to log a message */
	private static final Logger LOG = Logger.getLogger(PGM9001_0016_01.class);

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
		boolean isNumeric = true;
		String minYr = null;
		String maxYr = null;
		String minMonth = null;
		String minYrCompID = null;
	    String minMonthCompID = null;
	    String maxYrCompID = null;
	    String maxMnthCompID = null;
		ConcurrentHashMap<String,String> errorMsg = new ConcurrentHashMap<String,String>();

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();

			maxMonth = (String) inputObjs.get(0);
			if (maxMonth == null) {
				LOG.log(Level.ERROR,
						"Input Object maxMonth is  null during intialization for the rule "
								+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			minYr = (String) inputObjs.get(1);
			if (minYr == null) {
				LOG.log(Level.ERROR,
						"Input Object minYr is  null during intialization for the rule "
								+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			maxYr = (String) inputObjs.get(2);
			if (maxYr == null) {
				LOG.log(Level.ERROR,
						"Input Object maxYr is  null during intialization for the rule "
								+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			minMonth = (String) inputObjs.get(3);
			if (minMonth == null) {
				LOG.log(Level.ERROR,
						"Input Object minMonth is  null during intialization for the rule "
								+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			minYrCompID = (String) inputObjs.get(4);
    		minMonthCompID = (String) inputObjs.get(5);
    		maxYrCompID = (String) inputObjs.get(6);
    		maxMnthCompID = (String) inputObjs.get(7);
		} else {
			LOG.log(Level.ERROR,
					"Input Context Object is null during intialization for the rule "
							+ ruleId);

			throw new RIFPOJORulesException(
					"Unable to initialize the required rules objects for rule "
							+ ruleId);
		}
		LOG.log(Level.INFO, "Executing the actions for the rule:" + ruleId);

		// EXTRACT_START PGM9001.0016.01
		/** Checks minYr to be Numeric */
		if (!EnterpriseCommonValidator.validateNumeric(minYr)) {

			if (EnterpriseCommonValidator.validateNegNumericValue(minYr)) {

				/*
				 * setErrorMessage(ProgramCodeMaintanaceConstants.
				 * MIN_YEAR_NOT_IN_RAGE, new Object[] {},
				 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
				 * minYrCompID);
				 */
				errorMsg.put(minYrCompID,ProgramCodeMaintanaceConstants.MIN_YEAR_NOT_IN_RAGE);
			} else {

				/*
				 * setErrorMessage(ProgramCodeMaintanaceConstants.
				 * MINIMUM_YEAR_NOT_INTEGER, new Object[] {},
				 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
				 * minYrCompID);
				 */
				errorMsg.put(minYrCompID,ProgramCodeMaintanaceConstants.MINIMUM_YEAR_NOT_INTEGER);
			}

			// diagnosisCodeDataBean.setDataFlag(false);
			isNumeric = false;
		}/** If Numeric Check for the Range 0 to 999 */
		else if (Integer.parseInt(minYr) > ProgramNumberConstants.MAX_AGE_YEAR) {

			/*
			 * setErrorMessage(ProgramCodeMaintanaceConstants.MIN_YEAR_NOT_IN_RAGE
			 * , new Object[] {},
			 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
			 * minYrCompID); diagnosisCodeDataBean.setDataFlag(false);
			 */
			errorMsg.put(minYrCompID,ProgramCodeMaintanaceConstants.MIN_YEAR_NOT_IN_RAGE);
			isNumeric = false;
		}

		/** Checks minMonth to be Numeric */
		if (!EnterpriseCommonValidator.validateNumeric(minMonth)) {

			if (EnterpriseCommonValidator.validateNegNumericValue(minMonth)) {

				/*
				 * setErrorMessage(ProgramCodeMaintanaceConstants.
				 * MIN_MONTHS_NOT_IN_RANGE, new Object[] {},
				 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
				 * minMonthCompID);
				 */
				errorMsg.put(minMonthCompID,ProgramCodeMaintanaceConstants.MIN_MONTHS_NOT_IN_RANGE);
			}
			else
			{
				errorMsg.put(minMonthCompID,ProgramCodeMaintanaceConstants.MIN_MONTHS_NOT_IN_RANGE);
			}

			// diagnosisCodeDataBean.setDataFlag(false);
			isNumeric = false;
		}
		/** Checks minMonth to be in range 0 to 11 */
		else if (Integer.parseInt(minMonth) > ProgramNumberConstants.MAX_AGE_MONTH) {

			/*
			 * setErrorMessage(ProgramCodeMaintanaceConstants.
			 * MIN_MONTHS_NOT_IN_RANGE, new Object[] {},
			 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
			 * minMonthCompID); diagnosisCodeDataBean.setDataFlag(false);
			 */
			errorMsg.put(minMonthCompID,ProgramCodeMaintanaceConstants.MIN_MONTHS_NOT_IN_RANGE);
			isNumeric = false;
		}

		/** Checks maxYr to be Numeric */
		if (!EnterpriseCommonValidator.validateNumeric(maxYr)) {

			if (EnterpriseCommonValidator.validateNegNumericValue(maxYr)) {

				/*
				 * setErrorMessage(ProgramCodeMaintanaceConstants.
				 * MAX_YEAR_NOT_IN_RAGE, new Object[] {},
				 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
				 * maxYrCompID);
				 */
				errorMsg.put(maxYrCompID,ProgramCodeMaintanaceConstants.MAX_YEAR_NOT_IN_RAGE);
			} else {

				/*
				 * setErrorMessage(ProgramCodeMaintanaceConstants.
				 * MAXIMUM_YEAR_NOT_INTEGER, new Object[] {},
				 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
				 * maxYrCompID);
				 */
				errorMsg.put(maxYrCompID,ProgramCodeMaintanaceConstants.MAXIMUM_YEAR_NOT_INTEGER);
			}
			// diagnosisCodeDataBean.setDataFlag(false);
			isNumeric = false;
		}/** Checks maxYr to be less than Max Default Yr */
		else if (Integer.parseInt(maxYr) > ProgramNumberConstants.MAX_AGE_YEAR) {

			/*
			 * setErrorMessage(ProgramCodeMaintanaceConstants.
			 * MAXIMUM_YEAR_NOT_INTEGER, new Object[] {},
			 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
			 * maxYrCompID);
			 * 
			 * diagnosisCodeDataBean.setDataFlag(false);
			 */
			errorMsg.put(maxYrCompID,ProgramCodeMaintanaceConstants.MAXIMUM_YEAR_NOT_INTEGER);
			isNumeric = false;
		}

		/** Checks maxMonth to be Numeric */
		if (!EnterpriseCommonValidator.validateNumeric(maxMonth)) {

			if (EnterpriseCommonValidator.validateNegNumericValue(maxMonth)) {

				/*
				 * setErrorMessage(ProgramCodeMaintanaceConstants.
				 * MAX_MONTHS_NOT_IN_RANGE, new Object[] {},
				 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
				 * maxMnthCompID);
				 */
				errorMsg.put(maxMnthCompID,ProgramCodeMaintanaceConstants.MAX_MONTHS_NOT_IN_RANGE);
			}
			else
			{
				errorMsg.put(maxMnthCompID,ProgramCodeMaintanaceConstants.MIN_MONTHS_NOT_IN_RANGE);
			}

			// diagnosisCodeDataBean.setDataFlag(false);
			isNumeric = false;
		}/** Checks maxMonth to be less than twelve */
		else if (Integer.parseInt(maxMonth) > ProgramNumberConstants.MAX_AGE_MONTH) {

			/*
			 * setErrorMessage(ProgramCodeMaintanaceConstants.
			 * MAX_MONTHS_NOT_IN_RANGE, new Object[] {},
			 * ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
			 * maxMnthCompID);
			 * 
			 * diagnosisCodeDataBean.setDataFlag(false);
			 */
			errorMsg.put(maxMnthCompID,ProgramCodeMaintanaceConstants.MAX_MONTHS_NOT_IN_RANGE);
			isNumeric = false;
		}
		// EXTRACT_END

		rulesResult.setReturnBooleanValue(isNumeric);
		System.out.println("error msg:"+errorMsg);
		rulesResult.setReturnValue(errorMsg);
		rulesResult.setRuleStatus(true);
		return rulesResult;

	}

	/*
	 * Method to initialize rule
	 */
	public static void initialize() throws RIFPOJORulesException {
		// TODO
		// Auto-generated
																	// method
																	// stub
	}

	/*
	 * Method to shutdown rule
	 */
	public static void shutdown() throws RIFPOJORulesException {
		// TODO
																// Auto-generated
																// method stub
	}

}
