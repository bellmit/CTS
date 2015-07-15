package com.xerox.enterprise.ghs.mmis.rif.rules.program;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;


/** 
 * The class PGM9004_0021_01Test contains tests for the class
 * PGM9004_0021_01. 
 * 
 * @author 396662
 * @generatedBy RIF 
 * @since <pre>Mon Jan 12 12:13:02 IST 2015</pre> 
 * @version 1.0 
 */ 
public class TestPGM9004_0021_01 extends TestCase { 
    public TestPGM9004_0021_01(String name) { 
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
        //TODO: Test goes here... 
		
		//TODO: Assignment
		String maxAllowAmount = null; 
		String factorCode = null; 
		
		System.out.println("Started Rule Execution::::");
              
		//Rule Invocation Context object holds object arrays used in rule execution.
		String ruleId = "PGM9004.0021.01";
		RulesContext ric = new RulesContext();
		//Adding object into rule invocation context.
		ric.addObject(maxAllowAmount);
		ric.addObject(factorCode);
		
		//Invoke RIF with ruleid and rule invocation objects.
	    RulesResult rulesResult = null;
		                
		  try {
		  		RulesManager rulesManager = RulesManager.getRIFInstance();
				rulesResult = rulesManager.execute(ruleId, ric);	
				  					                } catch (RIFException re){
		         System.out.println("RIFException::::"+re);
		         re.printStackTrace();
		  }
		  System.out.println("Rule Execution Ended::::"+ruleId+"\n");

    	assertEquals(true,rulesResult.getRuleStatus());
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(TestPGM9004_0021_01.class); 
    } 
} 