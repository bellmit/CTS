package com.xerox.enterprise.ghs.mmis.rif.rules.provider;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;

/**
 * The class PRV0004_0001_01Test contains tests for the class PRV0004_0001_01.
 * 
 * @author 396662
 * @generatedBy RIF
 * @since <pre>
 * Fri Dec 19 12:14:31 IST 2014
 * </pre>
 * @version 1.0
 */
public class PRV0003_4_0001_01Test extends TestCase {
	public PRV0003_4_0001_01Test(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Method Name: execute Param: RulesContext ctx, String ruleId
	 * 
	 */

	public void testEXECUTE() throws Exception { 
        //TODO: Test goes here... 
		
		Calendar cd=Calendar.getInstance();
		cd.set(1914,12 , 27);
		System.out.println(cd.get(Calendar.YEAR));
		
		//TODO: Assignment
		boolean ageFlag = false; 
		Date dob = new Date(cd.getTimeInMillis()); 
		
		String ruleId1 = "PRV0003.0001.01";
		RulesContext ric1 = new RulesContext();
		//Adding object into rule invocation context.
	  	ric1.addObject(dob);
	  	String ruleId2 = "PRV0004.0001.01";
		RulesContext ric2 = new RulesContext();
		//Adding object into rule invocation context.
		ric2.addObject(dob);	
		ConcurrentHashMap<String,RulesContext> ricMap = new ConcurrentHashMap<String, RulesContext>();
		ricMap.put(ruleId1, ric1);
		ricMap.put(ruleId2, ric2);
                    //Invoke RIF with ruleid and rule invocation objects.
		RulesResult[] rulesResult = null;
		                
		try {
	      		RulesManager rulesManager = RulesManager.getRIFInstance();
				rulesResult = rulesManager.execute(ricMap); 
        } catch (RIFException re){
	            
        }
		  for (RulesResult ruleResult: rulesResult) {
			if (ruleResult.isReturnBooleanValue()) {
				ageFlag = true;
			}
		  }
    	assertEquals(true,ageFlag);
    }	
	
	public static Test suite() {
		return new TestSuite(PRV0003_4_0001_01Test.class);
	}
}