package test.com.xerox.enterprise.ghs.mmis.rif.rules.pojo;

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import java.util.ArrayList;
import java.util.List;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;
import com.acs.enterprise.mmis.provider.common.vo.MaintenanceEventsVO; 

import java.util.List; 

import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 

import java.util.Iterator; 


/** 
 * The class PRV0552_0001_01Test contains tests for the class
 * PRV0552_0001_01. 
 * 
 * @author 396662
 * @generatedBy RIF 
 * @since <pre>Thu Dec 18 13:11:24 IST 2014</pre> 
 * @version 1.0 
 */ 
public class PRV0552_0001_01Test extends TestCase { 
    public PRV0552_0001_01Test(String name) { 
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
		RulesContext ric=new RulesContext();
    	List<Object> contextObject=new ArrayList<Object>();
    	String ruleId="";
		
		//TODO: Assignment
    	
		RulesResult rulesResult = null;
		boolean isInvalidEvent = false;
		MaintenanceEventsVO eventVO = null;
		List eventList = null;
		List<Object> inputObjs = null;
 
		
		ric.addObject(isInvalidEvent);
		ric.addObject(eventVO);
		ric.addObject(eventList);
		try {
		RulesManager rulesManager = RulesManager.getRIFInstance();
		rulesResult=rulesManager.execute(ruleId,ric);
	} catch (RIFException re) {
		re.printStackTrace();
	}
    	assertEquals(true,rulesResult.getRuleStatus());
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(PRV0552_0001_01Test.class); 
    } 
} 