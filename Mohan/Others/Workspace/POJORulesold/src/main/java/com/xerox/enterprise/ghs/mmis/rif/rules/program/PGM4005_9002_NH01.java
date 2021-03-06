package com.xerox.enterprise.ghs.mmis.rif.rules.program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

/**
 * Ruleclass of the rule "PGM4005.9002.NH01"
 * 
 * @author POJO extracted from Class:BenefitPlanControllerBean
 */
public class PGM4005_9002_NH01 extends Rule {

	/* Static logger to log a message */
	private static final Logger LOG = Logger.getLogger(PGM4005_9002_NH01.class);

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

			
			sdf1 =  (SimpleDateFormat) inputObjs.get(0);
			if (sdf1 == null) {
				if (LOG.isEnabledFor(Level.ERROR)) {
					LOG.error("Input Object sdf1 is  null during intialization for the rule "
						+ ruleId);
				}
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			strdate = (String) inputObjs.get(1);
			if (strdate == null) {
				if (LOG.isEnabledFor(Level.ERROR)) {
					LOG.error("Input Object strdate is  null during intialization for the rule "
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
			LOG.info("Executing the actions for the rule PGM4005.9002.NH01" + ruleId);
		}
		
		if (strdate != null && strdate.indexOf('/') != -1) {
			try {

				date = new Date(sdf1.parse(strdate).getTime());
				//currentDate = new Date();
				// Defect_Fixed_ESPRD00850655_10-09-2012
				// if ((date != null) && (date.before(new Date()))) {
				if ((date != null)
						&& (date.before(sdf1.parse(sdf1.format(new Date()))))) {
					
					// return true;
					rulesResult.setReturnBooleanValue(true);
									}

			} catch (ParseException e) {
				//System.out.println("exception while parsing");
				 LOG.error("ParseException:" + e.getMessage(), e);
			}
		}
		
	     rulesResult.setRuleStatus(true);
	     if(LOG.isInfoEnabled())
			{
	    	 LOG.info("End of execution of PGM4005_9002_NH01");
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
