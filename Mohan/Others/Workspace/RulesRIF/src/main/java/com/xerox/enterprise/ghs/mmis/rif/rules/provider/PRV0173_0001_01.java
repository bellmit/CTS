package com.xerox.enterprise.ghs.mmis.rif.rules.provider;

import java.util.Date;

import org.apache.log4j.Logger;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.acs.enterprise.mmis.provider.common.domain.Affiliation;
import com.acs.enterprise.mmis.provider.common.helper.ProviderUtil;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Ruleclass of the rule "PRV0173.0001.01"
 * 
 * @author POJO extracted from Class:ProviderAppMntHelper
 */
public class PRV0173_0001_01 extends Rule {

	/* Static logger to log a message */
	private static final Logger LOG = Logger.getLogger(PRV0173_0001_01.class);

	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method. return -
	 * execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId)
			throws RIFPOJORulesException {

		// Construct rules result object
		RulesResult rulesResult = new RulesResult(ruleId);
		boolean isDuplicate = false;
		Set affiliationSet = null;
		String userID = null;
		Affiliation affiliation = null;
		Date enrollmentEffDate = null;
		Affiliation oldAffl = null;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();


			affiliationSet = (Set) inputObjs.get(0);
			if (affiliationSet == null) {
				LOG.error("Input Object affiliationSet is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			userID =  (String) inputObjs.get(1);
			if (userID == null) {
				LOG.error("Input Object userID is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			affiliation = (Affiliation) inputObjs.get(2);
			if (affiliation == null) {
				LOG.error("Input Object affiliation is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			enrollmentEffDate = (Date) inputObjs.get(3);
			if (enrollmentEffDate == null) {
				LOG.error("Input Object enrollmentEffDate is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			oldAffl = (Affiliation) inputObjs.get(4);
			if (oldAffl == null) {
				LOG.error("Input Object oldAffl is  null during intialization for the rule "
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
		
		if (affiliation.getMemberProviderSysId().equals(oldAffl.getMemberProviderSysId())
				&& affiliation.getGroupProviderSysId().equals(oldAffl.getGroupProviderSysId())
				&& !oldAffl.getAfflEndDate().before(enrollmentEffDate))
		
		{
			oldAffl.setAfflBeginDate(enrollmentEffDate);
			oldAffl.setAfflEndDate(ProviderUtil.endDate());
			oldAffl.setAuditUserID(userID);
			affiliationSet.add(oldAffl);
			isDuplicate = true;
			//break;
		}
		LOG.info("Execution Success for the rule " + ruleId);
		rulesResult.setReturnBooleanValue(isDuplicate);
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
