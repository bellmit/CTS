package com.xerox.enterprise.ghs.mmis.rif.rules.program;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.acs.enterprise.common.program.administration.util.helper.ProgramCodeMaintanaceConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;




/**
 * POJO for the rule "PGM9001.0017.01"
 * @author $authorName
 * @source DiagnosisCodeControllerBean
 */
public class PGM9001_0017_01 extends Rule {
	
    /* Static logger to log a message */
    private static final Logger LOG = Logger.getLogger(PGM9001_0017_01.class);
	
    /**
     * Execute method of the rule. This holds the rule conditions and actions.
     * The required objects are initialized through init() method.
     * return - execution result of rule either SUCCESS or FAILURE
     */
    public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
		
        // Construct rules result object
        RulesResult rulesResult;

        rulesResult = new RulesResult(ruleId);
        String maxMonth = null;
        String minYr = null;
        String maxYr = null;
        String minMonth = null;
       
        ConcurrentHashMap<String,String> ageDefault=new ConcurrentHashMap<String,String>();

        if (ctx != null) {
				
            List<Object> inputObjs = ctx.getContextObject();

            maxMonth = (String) inputObjs.get(0);
            minYr = (String) inputObjs.get(1);
            maxYr = (String) inputObjs.get(2);
            minMonth = (String) inputObjs.get(3);
            
            

        } else {
            LOG.log(Level.ERROR, "Input Context Object is null during intialization for the rule " + ruleId);
			
            throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule " + ruleId);
        }
        LOG.log(Level.INFO, "Executing the actions for the rule:" + ruleId);
		
        // EXTRACT_START PGM9001.0017.01
        /** Sets minYr to Default if null */
        if (minYr == null || ProgramCodeMaintanaceConstants.EMPTY.equals(minYr.trim())) {
            /*diagnosisCodesAndIndicatorsVO.getAgeRangeVO().setMinYear("0");
            minYr = diagnosisCodesAndIndicatorsVO.getAgeRangeVO().getMinYear();
*/
        	ageDefault.put("minYr","0");
        }

        /** Sets minMonth to Default if null */
        if (minMonth == null || ProgramCodeMaintanaceConstants.EMPTY.equals(minMonth.trim())) {
            /*diagnosisCodesAndIndicatorsVO.getAgeRangeVO().setMinMonth(ProgramNumberConstants.ZERO);
            minMonth = diagnosisCodesAndIndicatorsVO.getAgeRangeVO().getMinMonth();
*/
        	ageDefault.put("minMonth",ProgramNumberConstants.ZERO);
        }
		
        /** Sets maxYr to Default if null */
        if (maxYr == null || ProgramCodeMaintanaceConstants.EMPTY.equals(maxYr.trim())) {

           /* diagnosisCodesAndIndicatorsVO.getAgeRangeVO().setMaxYear(ProgramNumberConstants.DEFAULT_MAX_AGE_YEAR);
            maxYr = diagnosisCodesAndIndicatorsVO.getAgeRangeVO().getMaxYear();
*/
        	ageDefault.put("maxYr",ProgramNumberConstants.DEFAULT_MAX_AGE_YEAR);
        }
		
        /** Sets maxMonth to Default if null */
        if (maxMonth == null || ProgramCodeMaintanaceConstants.EMPTY.equals(maxMonth.trim())) {

            /*diagnosisCodesAndIndicatorsVO.getAgeRangeVO().setMaxMonth(ProgramNumberConstants.ZERO);
            maxMonth = diagnosisCodesAndIndicatorsVO.getAgeRangeVO().getMaxMonth();
*/
        	ageDefault.put("maxMonth",ProgramNumberConstants.ZERO);
        }
        // EXTRACT_END
        rulesResult.setReturnValue(ageDefault);
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
