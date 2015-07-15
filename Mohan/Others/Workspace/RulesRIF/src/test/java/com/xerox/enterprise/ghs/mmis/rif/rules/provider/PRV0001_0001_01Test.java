package com.xerox.enterprise.ghs.mmis.rif.rules.provider;


import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import java.util.ArrayList;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;

import java.util.regex.Pattern; 
import java.util.regex.Matcher; 
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 


/**
 * The class PRV0001_0001_01Test contains tests for the class
 * PRV0001_0001_01. 
 * 
 * @author 396662
 * @generatedBy RIF 
 * @since <pre>Wed Dec 24 18:22:47 IST 2014</pre> 
 * @version 1.0 
 */ 
public class PRV0001_0001_01Test extends TestCase { 
    public PRV0001_0001_01Test(String name) { 
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
        String expression = "111111111"; 
		
        System.out.println("Started Rule Execution::::");
              
        // Rule Invocation Context object holds object arrays used in rule execution.
        String ruleId = "PRV0001.0001.01";
        RulesContext ric = new RulesContext();

        // Adding object into rule invocation context.
        ric.addObject(expression);
	    
        // Invoke RIF with ruleid and rule invocation objects.
        RulesResult rulesResult = null;
			                
        try {
            RulesManager rulesManager = RulesManager.getRIFInstance();

            rulesResult = rulesManager.execute(ruleId, ric);	
        } catch (RIFException re) {
            System.out.println("RIFException::::" + re);
        }
        System.out.println("Rule Execution Ended::::" + ruleId + "\n");

        assertEquals(true, rulesResult.isReturnBooleanValue());
    } 
 
    public static Test suite() { 
        return new TestSuite(PRV0001_0001_01Test.class); 
    } 
} 

