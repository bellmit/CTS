package com.xerox.enterprise.ghs.mmis.rif.rules.provider;


import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import java.util.ArrayList;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;

import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 


/**
 * The class PRV0145_0001_01Test contains tests for the class
 * PRV0145_0001_01. 
 * 
 * @author 396662
 * @generatedBy RIF 
 * @since <pre>Wed Jan 21 20:30:19 IST 2015</pre> 
 * @version 1.0 
 */ 
public class TestPRV0145_0002_01 extends TestCase { 
    public TestPRV0145_0002_01(String name) { 
        super(name); 
    } 
 
    public void setUp() throws Exception { 
        super.setUp(); 
    } 
 
    public void tearDown() throws Exception { 
        super.tearDown(); 
    } 

    /**
     * Method Name: execute
     * Param: RulesContext ctx, String ruleId 
     * 
     */ 
	 
    public void testEXECUTE() throws Exception { 
        // TODO: Test goes here... 
		
        // TODO: Assignment
        boolean validateNewPatientFlag = false; 
        String newPatientDateFlag = "a"; 
        //String newPatientDateFlag = "gg";
        System.out.println("Started Rule Execution::::");
              
        // Rule Invocation Context object holds object arrays used in rule execution.
        String ruleId = "PRV0145.0002.01";
        RulesContext ric = new RulesContext();
        
        
        // Adding object into rule invocation context.
         ric.addObject(newPatientDateFlag);
	    
        
        // Invoke RIF with ruleid and rule invocation objects.
        RulesResult rulesResult = null;
			                
        try {
            RulesManager rulesManager = RulesManager.getRIFInstance();

            rulesResult = rulesManager.execute(ruleId, ric);	
            validateNewPatientFlag = rulesResult.isReturnBooleanValue(); 
        } catch (RIFException re) {
            System.out.println("RIFException::::" + re);
        }
        System.out.println("Rule Execution Ended::::" + ruleId + "\n");

        assertEquals(true,validateNewPatientFlag);
    } 
 
    public static Test suite() { 
        return new TestSuite(TestPRV0145_0002_01.class); 
    } 
} 

