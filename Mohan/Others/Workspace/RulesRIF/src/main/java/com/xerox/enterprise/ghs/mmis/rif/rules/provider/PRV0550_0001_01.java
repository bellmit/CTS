package com.xerox.enterprise.ghs.mmis.rif.rules.provider;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.acs.enterprise.mmis.provider.common.vo.MaintenanceEventsVO;
import com.acs.enterprise.mmis.provider.common.vo.ProviderVO;


/**
 * POJO for the rule "PRV0550.0001.01"
 * @author $authorName
 * @source AppMntEventsControllerBean
 */
public class PRV0550_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(PRV0550_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        MaintenanceEventsVO eventvo = null;
        ProviderVO inputProvider = null;
        boolean flag=false;
        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            eventvo = (MaintenanceEventsVO) inputObjs.get(0);
            if (eventvo == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object eventvo is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }
            flag = (Boolean) inputObjs.get(1);
            
            inputProvider = (ProviderVO) inputObjs.get(2);
            if (inputProvider == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object inputProvider is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }

        } else {
            if (LOG.isEnabledFor(Level.ERROR)) {
                LOG.error("Input Context Object is null during intialization for the rule " + ruleId);
            }
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("Executing the actions for the rule " + ruleId);
        }
        
        if (eventvo.getEventDescription().equals("M325")) {
         
            if (!checkEventExists1(inputProvider.getMaintanenceEventsList(), eventvo)) {
               flag=true;        
            }
            else
            {
            	flag=false;
            }
        } 
        
        rulesResult.setReturnBooleanValue(flag);
        rulesResult.setRuleStatus(true);
        return rulesResult;
		
    }
	
    private static boolean checkEventExists1(List list, MaintenanceEventsVO eventvo)
    {
        boolean checkExists = false;
     
        if (null != list && !list.isEmpty())
        {
            Iterator eventIter = list.iterator();
            while (eventIter.hasNext())
            {
                MaintenanceEventsVO event = (MaintenanceEventsVO) eventIter
                        .next();
                if ((null != event.getOperatorId())
                        && (null != eventvo.getOperatorId())
                        && (null != event.getDate())
                        && (null != eventvo.getDate())
                        && (null != event.getEventDescription())
                        && (null != eventvo.getEventDescription()))
                {
                	
                	 checkExists = (event.getEventDescription().equals("S315"));
                		
                }

                if (checkExists)
                {
                    break;
                }
            }
        }
        return checkExists;

    }

	/*
     * Method to initialize rule
     */
    public static void initialize() throws RIFPOJORulesException {// TODO Auto-generated method stub
    }

    /*
     * Method to shutdown rule
     */
    public static void shutdown() throws RIFPOJORulesException {// TODO Auto-generated method stub
    }
	
}
