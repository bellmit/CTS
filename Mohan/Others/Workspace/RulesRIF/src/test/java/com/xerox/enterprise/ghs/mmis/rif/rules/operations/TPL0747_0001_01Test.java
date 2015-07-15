package com.xerox.enterprise.ghs.mmis.rif.rules.operations;

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 


/** 
 * The class TPL0747_0001_01Test contains tests for the class
 * TPL0747_0001_01. 
 * 
 * @author 396662
 * @generatedBy RIF 
 * @since <pre>Wed Jan 21 18:28:14 IST 2015</pre> 
 * @version 1.0 
 */ 
public class TPL0747_0001_01Test extends TestCase { 
    public TPL0747_0001_01Test(String name) { 
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
		String selectedValue = "12"; 
		List memberIDTypeList = new ArrayList<String>();
		memberIDTypeList.add("Member");
        List providerIDList = new ArrayList<String>();
        providerIDList.add("Provider");
        List carrierIDTypeList = new ArrayList<String>();
        carrierIDTypeList.add("Carrier");
        List policyholderTypeList = new ArrayList<String>();
        policyholderTypeList.add("policy");
        List employerIDTypeList = new ArrayList<String>();
        employerIDTypeList.add("emp");
	
              System.out.println("Started Rule Execution::::");
              
              //Rule Invocation Context object holds object arrays used in rule execution.
			  			    String ruleId = "TPL0747.0001.01";
			    RulesContext ric = new RulesContext();
			    //Adding object into rule invocation context.
			 	ric.addObject(selectedValue);
				ric.addObject(memberIDTypeList);
				ric.addObject(providerIDList);
				ric.addObject(carrierIDTypeList);
				ric.addObject(policyholderTypeList);
				ric.addObject(employerIDTypeList);
				
	    
			          		                  //Invoke RIF with ruleid and rule invocation objects.
			  			    RulesResult rulesResult = null;
			                
              try {
              		RulesManager rulesManager = RulesManager.getRIFInstance();
					rulesResult = rulesManager.execute(ruleId, ric);	
            } catch (RIFException re){
                     System.out.println("RIFException::::"+re);
              }
              System.out.println("Rule Execution Ended::::"+ruleId+"\n");
              ConcurrentHashMap<String, Object> returnMap=(ConcurrentHashMap<String, Object>)rulesResult.getReturnValue();
              System.out.println("EntityIdType:"+returnMap.get("EntityIdType"));
              System.out.println("RenderEntity:"+ returnMap.get("RenderEntity"));
              System.out.println("RenderEntitySE:"+returnMap.get("RenderEntitySE"));
    	assertEquals(true,rulesResult.isReturnBooleanValue());
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(TPL0747_0001_01Test.class); 
    } 
} 