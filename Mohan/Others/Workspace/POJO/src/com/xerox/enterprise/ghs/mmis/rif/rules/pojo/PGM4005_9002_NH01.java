package com.xerox.enterprise.ghs.mmis.rif.rules.pojo;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.acs.enterprise.util.log.EnterpriseLogger;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Ruleclass of the rule "PGM4005.9002.NH01"
 * 
 * @author POJO extracted from Class:BenefitPlanControllerBean
 */
public class PGM4005_9002_NH01 extends Rule {

	/* Static logger to log a message */
	private static final Logger log = Logger.getLogger(PGM4005_9002_NH01.class);

	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method. return -
	 * execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId)
			throws RIFPOJORulesException {

		// Construct rules result object
		RulesResult rulesResult = new RulesResult(ruleId);
		SimpleDateFormat sdf1 = null;
		Date date = null;
		String strdate = null;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();

			/*currentDate = (Date) inputObjs.get(0);
			if (currentDate == null) {
				log.error("Input Object currentDate is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}*/

			/*
			 * logger = (EnterpriseLogger) inputObjs.get(1); if (logger == null)
			 * { log.error(
			 * "Input Object logger is  null during intialization for the rule "
			 * + ruleId); throw new RIFPOJORulesException(
			 * "Unable to initialize the required rules objects for rule " +
			 * ruleId); }
			 */

			sdf1 =  (SimpleDateFormat) inputObjs.get(0);
			if (sdf1 == null) {
				log.error("Input Object sdf1 is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			/*date = (Date) inputObjs.get(2);
			if (date == null) {
				log.error("Input Object date is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}*/

			strdate = (String) inputObjs.get(1);
			if (strdate == null) {
				log.error("Input Object strdate is  null during intialization for the rule "
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
		log.info("Executing the actions for the rule PGM4005.9002.NH01" + ruleId);
		// EXTRACT_START PGM4005.9002.NH01
		if (strdate != null && strdate.indexOf('/') != -1) {
			try {

				date = new Date(sdf1.parse(strdate).getTime());
				//currentDate = new Date();
				// Defect_Fixed_ESPRD00850655_10-09-2012
				// if ((date != null) && (date.before(new Date()))) {
				if ((date != null)
						&& (date.before(sdf1.parse(sdf1.format(new Date()))))) {
					System.out.println("checking date " +date);
					// return true;
					rulesResult.setReturnBooleanValue(true);
									}

			} catch (ParseException e) {
				System.out.println("exception while parsing");
				 log.error("ParseException:" + e.getMessage(), e);
			}
		}
		// EXTRACT_END
	     rulesResult.setRuleStatus(true);
		log.info("End of execution of PGM4005_9002_NH01");
		return rulesResult;

	}

	public static void initialize() throws RIFPOJORulesException {
		// TODO Auto-generated method stub

	}

	public static void shutdown() throws RIFPOJORulesException {

		System.out.println("shutdown called");

	}

}
