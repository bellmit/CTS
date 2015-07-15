package com.xerox.enterprise.ghs.mmis.rif.rules.program;

import java.util.concurrent.ConcurrentHashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;



/** 
 * The class PGM9001_0017_01Test contains tests for the class
 * PGM9001_0017_01. 
 * 
 * @author 396662
 * @generatedBy RIF 
 * @since <pre>Wed Jan 14 11:43:06 IST 2015</pre> 
 * @version 1.0 
 */ 
public class TestPGM9001_0017_01 extends TestCase { 
    public TestPGM9001_0017_01(String name) { 
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
    	boolean isNumeric = false;
		String maxMonth = null; 
		String minYr = null;  
		String maxYr = null; 
		String minMonth = null; 
		ConcurrentHashMap<String,String> ageDefault=null;
					  
		  
		
              System.out.println("Started Rule Execution::::");
              
              //Rule Invocation Context object holds object arrays used in rule execution.
			  			    String ruleId = "PGM9001.0017.01";
			    RulesContext ric = new RulesContext();
			    //Adding object into rule invocation context.
			 	ric.addObject(maxMonth);
				ric.addObject(minYr);
				ric.addObject(maxYr);
				ric.addObject(minMonth);
	    
      		                  //Invoke RIF with ruleid and rule invocation objects.
		    RulesResult rulesResult = null;
			                
              try {
              		RulesManager rulesManager = RulesManager.getRIFInstance();
					rulesResult = rulesManager.execute(ruleId, ric);	
					isNumeric=rulesResult.isReturnBooleanValue();
		  					  						
					                } catch (RIFException re){
                     System.out.println("RIFException::::"+re);
              }
              
              ageDefault=(ConcurrentHashMap<String,String>)rulesResult.getReturnValue();
              if(ageDefault.containsKey("minYr"))
              {
            	  System.out.println("Min yr set");
              }
              System.out.println("Rule Execution Ended::::"+ruleId+"\n");
    	assertEquals(false,isNumeric);
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(TestPGM9001_0017_01.class); 
    } 
} 