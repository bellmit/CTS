package com.xerox.enterprise.ghs.mmis.rif.rules.provider;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.acs.enterprise.mmis.provider.common.helper.ProviderDataConstants;
import com.acs.enterprise.mmis.provider.common.vo.PatientVO;


/**
 * POJO for the rule "PRV0145.0001.01"
 * @author $authorName
 * @source PrvMntGeneralInfoControllerBean
 */
public class PRV0145_0001_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(PRV0145_0001_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult = new RulesResult(ruleId);
        PatientVO inputPatient = null;
        boolean validateReasonCodeFlag=false;
        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            inputPatient = (PatientVO) inputObjs.get(0);
            if (inputPatient == null) {
                if (LOG.isEnabledFor(Level.ERROR)) {
                    LOG.error("Input Object inputPatient is  null during intialization for the rule " + ruleId);
                }
                throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
            }
            
            /*validateReasonCodeFlag = (Boolean)inputObjs.get(1);*/
            

        } else {
            if (LOG.isEnabledFor(Level.ERROR)) {
                LOG.error("Input Context Object is null during intialization for the rule " + ruleId);
            }
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("Executing the actions for the rule " + ruleId);
        }
      
        validateReasonCodeFlag = errorForNullFields(inputPatient.getNewPatientServiceReason());
        System.out.println("validateReasonCodeFlag value"+validateReasonCodeFlag);

		
        rulesResult.setReturnBooleanValue(validateReasonCodeFlag);
        rulesResult.setRuleStatus(true);
        return rulesResult;
		
    }
	
    private static boolean errorForNullFields(String newPatientServiceReason) {
    	boolean validationFlag = false;
		if (checkForNullFields(newPatientServiceReason))
		{
		 
			validationFlag = true;

		}
		return validationFlag;
		
	}
    
    private static boolean checkForNullFields(String componentValue)
	{
		return (null == componentValue || (ProviderDataConstants.PRV_ENR_VALUE_BLANK)
				.equals(componentValue.trim())) ? true : false;
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
