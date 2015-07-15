package com.xerox.enterprise.ghs.mmis.rif.rules.pojo;

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import com.acs.enterprise.common.rif.rules.delegate.RulesDelegate;
import com.acs.enterprise.common.util.rif.rules.exception.RulesDelegateException;
import com.acs.enterprise.common.util.rif.rules.vo.RulesResultVO;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 
import com.acs.enterprise.mmis.operations.common.domain.ClaimCurrent; 


/** 
 * The class TestTPLRuleDemo contains tests for the class
 * TPLRuleDemo. 
 * 
 * @author 396662
 * @generatedBy RIF 
 * @since <pre>Tue Jul 14 12:06:58 IST 2015</pre> 
 * @version 1.0 
 */ 
public class TestTPLRuleDemo extends TestCase { 

	private static final Logger LOG = Logger.getLogger(TestTPLRuleDemo.class);
	
    public TestTPLRuleDemo(String name) { 
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
		ClaimCurrent claimCurrent = null; 
					double reImburesementAmount = 0d; 
		
					  
 				
              LOG.log(Level.INFO,"Started Rule Execution::::");
              
              //Rule Invocation Context object holds object arrays used in rule execution.
			  			    String ruleId = "TPLRuleDemo";
			    RulesDelegate ruleDelegate = new RulesDelegate(ruleId);
			    //Adding object into rule invocation context.
			 	ruleDelegate.addObject(claimCurrent);
				ruleDelegate.addObject(reImburesementAmount);
	    
			          		                  //Invoke RIF with ruleid and rule invocation objects.
			  			    RulesResultVO rulesResult = null;
			                
              try {
              		  						rulesResult = ruleDelegate.invokeRule();	
					  					                } catch (RulesDelegateException re){
		            if("300".equals(re.getErrorCode()))
					{
						LOG.log(Level.INFO,"Rule Expired...");
					}
                    LOG.log(Level.ERROR,"RulesDelegateException::::"+re);
              }
              LOG.log(Level.INFO,"Rule Execution Ended::::"+ruleId+"\n");

    	assertEquals(true,rulesResult.getRuleStatus());
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(TestTPLRuleDemo.class); 
    } 
} 