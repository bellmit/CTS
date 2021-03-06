/*package com.xerox.enterprise.ghs.mmis.rif.rules.provider;

import java.util.Iterator;

import org.apache.log4j.Logger;




import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.acs.enterprise.mmis.provider.common.vo.MaintenanceEventsVO;
import com.acs.enterprise.mmis.provider.common.helper.ProviderDataConstants;

import java.util.List;


*//**
 * Ruleclass of the rule "PRV0552.0001.01"
 * 
 * @author POJO extracted from Class:PrvMntEventsControllerBean
 *//*
public class PRV0552_0001_01 extends Rule {

	 Static logger to log a message 
	private static final Logger LOG = Logger.getLogger(PRV0552_0001_01.class);

	*//**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method. return -
	 * execution result of rule either SUCCESS or FAILURE
	 *//*
	public static RulesResult execute(RulesContext ctx, String ruleId)
			throws RIFPOJORulesException {

		// Construct rules result object
		RulesResult rulesResult = new RulesResult(ruleId);
		//EnterpriseLogger logger = null;

		boolean isInvalidEvent = false;
		MaintenanceEventsVO eventVO = null;
		List eventList = null;

		if (ctx != null) {

			List<Object> inputObjs = ctx.getContextObject();

			eventVO = (MaintenanceEventsVO) inputObjs.get(0);
			if (eventVO == null) {
				LOG.error("Input Object eventVO is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}

			eventList = (List) inputObjs.get(1);
			if (eventList == null) {
				LOG.error("Input Object eventList is  null during intialization for the rule "
						+ ruleId);
				throw new RIFPOJORulesException(
						"Unable to initialize the required rules objects for rule "
								+ ruleId);
			}
			isInvalidEvent = (Boolean) inputObjs.get(2);

		} else {
			LOG.error("Input Context Object is null during intialization for the rule "
					+ ruleId);
			throw new RIFPOJORulesException(
					"Unable to initialize the required rules objects for rule "
							+ ruleId);
		}
		LOG.info("Executing the actions for the rule " + ruleId);
		if ((eventVO.getEventDescription().trim()
				.equals(ProviderDataConstants.MANUAL_EVENT_M580) || eventVO
				.getEventDescription().trim()
				.equals(ProviderDataConstants.MANUAL_EVENT_M590))
				&& !isEventExists(eventList,
						ProviderDataConstants.SYSTEM_EVENT_S509)) {
			isInvalidEvent = true;
			
			if (logger.isDebugEnabled()) {
				logger.debug("User is adding M580 or M590 and no S509 event exist ");
			}
			LOG.info("User is adding M580 or M590 and no S509 event exist");
			ProviderInformationHelper
					.errorMessage(ProviderDataConstants.EVENT_OUT_OF_SEQ);
			rulesResult.setReturnValue(ProviderDataConstants.EVENT_OUT_OF_SEQ);
		}

		rulesResult.setReturnBooleanValue(isInvalidEvent);
		rulesResult.setRuleStatus(true);
		return rulesResult;

	}
	
	private static boolean isEventExists(List providerEvent, String eventCode) {
		boolean isEventExists = false;
		
		if (providerEvent != null && !providerEvent.isEmpty()
				&& eventCode != null && !eventCode.trim().equals("")) {		

			Iterator eventIterator = providerEvent.iterator();

			while (eventIterator.hasNext()) {
				MaintenanceEventsVO event = (MaintenanceEventsVO) eventIterator
						.next();				
				if (event.getEventDescription() != null
						&& !event.getEventDescription().trim().equals("")
						&& event.getEventDescription().equals(eventCode)) {					
					
//					if(logger.isDebugEnabled()){						
//						logger.debug("List Event Code "+event.getEventDescription()+" matches with current Event code "+eventCode);						
//					}
					
					isEventExists = true;
					break;
				}
			}
		}
		return isEventExists;
	}
    

	public static void initialize() throws RIFPOJORulesException {
		// TODO Auto-generated method stub

	}

	public static void shutdown() throws RIFPOJORulesException {

		System.out.println("shutdown called");

	}

}
*/