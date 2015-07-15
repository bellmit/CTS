/*package com.xerox.enterprise.ghs.mmis.rif.rules.operations;


import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import java.util.ArrayList;
import java.util.List;

import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;

import java.util.List; 

import com.acs.enterprise.mmis.operations.tpladministration.application.helper.AddPayAndChaseClaim;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBilling;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBillingIndivSent;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBillingInvoiceDetail;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBillingInvoiceSentClaim; 
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBillingLineItem; 
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLBillingResponse; 
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 


*//**
 * The class TPL0809_0001_01Test contains tests for the class
 * TPL0809_0001_01. 
 * 
 * @author 414774
 * @generatedBy RIF 
 * @since <pre>Mon Dec 22 15:09:55 IST 2014</pre> 
 * @version 1.0 
 *//* 
public class TPL0809_0001_01Test extends TestCase { 
    public TPL0809_0001_01Test(String name) { 
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
        List<TPLBillingInvoiceSentClaim> sentClaimsList= new ArrayList<TPLBillingInvoiceSentClaim>();
        String princingMetCD = null; 
        AddPayAndChaseClaim addPayAndChase = new AddPayAndChaseClaim(); 
        String reasoncode = null; 
        TPLBillingInvoiceSentClaim sentClaim = new TPLBillingInvoiceSentClaim(); 
		
        System.out.println("Started Rule Execution::::");
              
        // Rule Invocation Context object holds object arrays used in rule execution.
        String ruleId = "TPL0809.0001.01";
        RulesContext ric = new RulesContext();
        
        // Adding object into rule invocation context.
        TPLBillingInvoiceSentClaim sentClaim1 = new TPLBillingInvoiceSentClaim();
        TPLBillingLineItem tplBillLineItem=new TPLBillingLineItem();
        tplBillLineItem.setTplBilling(new TPLBilling());
        sentClaim1.setTplBillingLineItem(tplBillLineItem);
        
        
        TPLBillingInvoiceDetail tplBillingInvoiceDetail = new TPLBillingInvoiceDetail();
        tplBillingInvoiceDetail.setInvoiceNumber("4234234");
        
        TPLBillingIndivSent tplBillingIndivSent= new TPLBillingIndivSent();
        tplBillingIndivSent.setTplBillingInvoiceDetail(tplBillingInvoiceDetail);
        sentClaim1.setTplBillingIndivSent(tplBillingIndivSent);
        
        sentClaim1.setTcnNumber("34234");
        sentClaim1.setCarrierID("32432");
        sentClaim1.setGroupPolicyNumber("23423");
        sentClaim1.setCoverageGrouppolicySK((long)234);
        sentClaim1.setPolicyHolderSeqNum(7878);
      
        sentClaimsList.add(sentClaim1);
        
        ric.addObject(sentClaimsList);
        ric.addObject("H");
        ric.addObject(addPayAndChase);
        ric.addObject("FSDF");
        ric.addObject(sentClaim);
	    
        // Invoke RIF with ruleid and rule invocation objects.
        RulesResult rulesResult = null;
			                
        try {
            RulesManager rulesManager = RulesManager.getRIFInstance();

            rulesResult = rulesManager.execute(ruleId, ric);	
        } catch (RIFException re) {
            System.out.println("RIFException::::" + re);
        }
        System.out.println("Rule Execution Ended::::" + ruleId + "\n");

        assertNotSame(0, addPayAndChase.getBillingRespUpdateList().size());
    } 
 
    public static Test suite() { 
        return new TestSuite(TPL0809_0001_01Test.class); 
    } 
} 

*/