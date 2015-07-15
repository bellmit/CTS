package com.xerox.enterprise.ghs.mmis.rif.rules.provider;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;


/**
 * POJO for the rule "PRV0003.0001.01"
 * @author $authorName
 * @source $ruleDescriptor.getRuleExtractClassName()
 */
public class PRV0003_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(PRV0003_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        Date dob = null;

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            dob = (Date) inputObjs.get(0);
            if (dob == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object dob is  null during intialization for the rule " + ruleId);
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
        
        int ageYears, cdMonths, cdDays;
        boolean ageFlag = false;
        Calendar cd = Calendar.getInstance();
        GregorianCalendar bd = new GregorianCalendar();

        bd.setTime(dob);
        ageYears = cd.get(Calendar.YEAR) - bd.get(Calendar.YEAR);
        int dobMonths = bd.get(Calendar.MONTH);
        int dobDays = bd.get(Calendar.DAY_OF_MONTH);

        cdMonths = cd.get(Calendar.MONTH) + 1;
        cdDays = cd.get(Calendar.DAY_OF_MONTH);
    	
        if (ageYears < 18) {
            ageFlag = true;
        } else if (ageYears == 18) {
            if (cdMonths != (dobMonths + 1) && ((dobMonths + 1) - cdMonths) > 0) {
                ageFlag = true;	
            } else if (cdMonths == (dobMonths + 1) && dobDays > cdDays) {
                ageFlag = true;
            }
        }
       

		
        rulesResult.setReturnBooleanValue(ageFlag);
        System.out.println("Rule PRV0003.0001.01 Flage==>"+ageFlag);
        rulesResult.setRuleStatus(true);
        return rulesResult;
		
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
