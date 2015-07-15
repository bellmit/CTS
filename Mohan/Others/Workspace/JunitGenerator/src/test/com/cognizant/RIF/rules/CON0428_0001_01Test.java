package test.com.cognizant.RIF.rules;

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import java.util.ArrayList;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;

import java.util.Date; 
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 


/** 
 * CON0428_0001_01 Tester. 
 * 
 * @author 396662
 * @since <pre>Thu Nov 06 15:15:41 IST 2014</pre> 
 * @version 1.0 
 */ 
public class CON0428_0001_01Test extends TestCase { 
    public CON0428_0001_01Test(String name) { 
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
		RulesContext ctx=new RulesContext();
    	List<Object> contextObject=new ArrayList<Object>();
    	String ruleId="";
		
		//TODO: Assignment
		RulesResult rulesResult = null;
 
		Date PacketReceivedDate = null;
 
		boolean flag = false;
 
		Date currentDate = null;
 
		List<Object> inputObjs = null;
 
		
		contextObject.add(PacketReceivedDate);
		contextObject.add(flag);
		contextObject.add(currentDate);
		
		ctx.setContextObject(contextObject);
		rulesResult=CON0428_0001_01.execute(ctx, ruleId);
    	assertEquals(true,rulesResult.getRuleStatus());
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(CON0428_0001_01Test.class); 
    } 
} 