package com.xerox.enterprise.ghs.mmis.rif.rules.operations;

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import java.util.ArrayList;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;

import java.util.Date; 
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 


/** 
 * The class TPL0528_0001_01Test contains tests for the class
 * TPL0528_0001_01. 
 * 
 * @author 396662
 * @generatedBy RIF 
 * @since <pre>Thu Jan 08 16:38:33 IST 2015</pre> 
 * @version 1.0 
 */ 
public class TPL0528_0001_01Test extends TestCase { 
    public TPL0528_0001_01Test(String name) { 
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
		Date hearingHeldDate = new Date(); 
					boolean flag = false; 
					Date hearingReqDate = new Date(); 
					String referenceNumber = ""; 
					String legalStatus = ""; 
		
					  
		  
		
              System.out.println("Started Rule Execution::::");
              
              //Rule Invocation Context object holds object arrays used in rule execution.
			  			    String ruleId = "TPL0528.0001.01";
			    RulesContext ric = new RulesContext();
			    //Adding object into rule invocation context.
			 	ric.addObject(hearingHeldDate);
				ric.addObject(flag);
				ric.addObject(hearingReqDate);
				ric.addObject(referenceNumber);
				ric.addObject(legalStatus);
	    
			          		                  //Invoke RIF with ruleid and rule invocation objects.
			  			    RulesResult rulesResult = null;
			                
              try {
              		RulesManager rulesManager = RulesManager.getRIFInstance();
                     						rulesResult = rulesManager.execute(ruleId, ric);	
					  					  						flag=rulesResult.isReturnBooleanValue(); 
					                } catch (RIFException re){
                     System.out.println("RIFException::::"+re);
              }
              System.out.println("Rule Execution Ended::::"+ruleId+"\n");

    	assertEquals(true,rulesResult.getRuleStatus());
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(TPL0528_0001_01Test.class); 
    } 
} 