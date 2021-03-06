package com.xerox.enterprise.ghs.mmis.rif.rules.operations;

import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;


/** 
 * The class TPL0208_0001_01Test contains tests for the class
 * TPL0208_0001_01. 
 * 
 * @author 396662
 * @generatedBy RIF 
 * @since <pre>Thu Jan 08 17:52:28 IST 2015</pre> 
 * @version 1.0 
 */ 
public class TPL0208_0001_01Test extends TestCase { 
    public TPL0208_0001_01Test(String name) { 
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
		boolean ismatched = false; 
					Iterator iterator = null; 
					String fincia = null; 
		
					  
		  
		
              System.out.println("Started Rule Execution::::");
              
              //Rule Invocation Context object holds object arrays used in rule execution.
			  			    String ruleId = "TPL0208.0001.01";
			    RulesContext ric = new RulesContext();
			    //Adding object into rule invocation context.
			 	ric.addObject(ismatched);
				ric.addObject(iterator);
				ric.addObject(fincia);
	    
			          		                  //Invoke RIF with ruleid and rule invocation objects.
			  			    RulesResult rulesResult = null;
			                
              try {
              		RulesManager rulesManager = RulesManager.getRIFInstance();
                     						rulesResult = rulesManager.execute(ruleId, ric);	
					  					  						ismatched=rulesResult.isReturnBooleanValue(); 
					                } catch (RIFException re){
                     System.out.println("RIFException::::"+re);
              }
              System.out.println("Rule Execution Ended::::"+ruleId+"\n");

    	assertEquals(false,ismatched);
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(TPL0208_0001_01Test.class); 
    } 
} 