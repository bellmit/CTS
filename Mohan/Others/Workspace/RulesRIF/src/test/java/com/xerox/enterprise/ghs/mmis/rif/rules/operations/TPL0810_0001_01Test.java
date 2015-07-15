/*package com.xerox.enterprise.ghs.mmis.rif.rules.operations;


import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLRecoveryCaseMemberClaimSelectionVO; 
import com.acs.enterprise.mmis.operations.tpladministration.common.delegate.TPLRecoveryDelegate; 

import java.util.Set; 

import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 


*//**
 * The class TPL0810_0001_01Test contains tests for the class
 * TPL0810_0001_01. 
 * 
 * @author 414774
 * @generatedBy RIF 
 * @since <pre>Mon Dec 22 15:25:45 IST 2014</pre> 
 * @version 1.0 
 *//* 
public class TPL0810_0001_01Test extends TestCase { 
    public TPL0810_0001_01Test(String name) { 
        super(name); 
    } 
 
    public void setUp() throws Exception { 
        super.setUp(); 
    } 
 
    public void tearDown() throws Exception { 
        super.tearDown(); 
    } 

    *//**
     * Method Name: execute
     * Param: RulesContext ctx, String ruleId 
     * 
     *//* 
	 
    public void testEXECUTE() throws Exception { 
        // TODO: Test goes here... 
		
        // TODO: Assignment
        TPLRecoveryCaseMemberClaimSelectionVO tplRecoveryCaseMemberClaimSelectionVO = new TPLRecoveryCaseMemberClaimSelectionVO(); 
  
        TPLRecoveryDelegate tplRecoveryDelegate = new TPLRecoveryDelegate(); 
        
        String caseID = null; 
        String tcn = null; 
        Set listOfLineItems = new HashSet(); 
		
        System.out.println("Started Rule Execution::::");
              
        // Rule Invocation Context object holds object arrays used in rule execution.
        String ruleId = "TPL0810.0001.01";
        RulesContext ric = new RulesContext();

        // Adding object into rule invocation context.
        tplRecoveryCaseMemberClaimSelectionVO.setPricingMethodCode("L");
        ric.addObject(tplRecoveryCaseMemberClaimSelectionVO);
        ric.addObject(tplRecoveryDelegate);
        ric.addObject("23234");
        ric.addObject("12323244");
        ric.addObject(listOfLineItems);
	    
        // Invoke RIF with ruleid and rule invocation objects.
        RulesResult rulesResult = null;
			                
        try {
            RulesManager rulesManager = RulesManager.getRIFInstance();

            rulesResult = rulesManager.execute(ruleId, ric);	
        } catch (RIFException re) {
            System.out.println("RIFException::::" + re);
        }
        System.out.println("Rule Execution Ended::::" + ruleId + "\n");

        assertNotSame(0, listOfLineItems.size() );
    } 
 
    public static Test suite() { 
        return new TestSuite(TPL0810_0001_01Test.class); 
    } 
} 

*/