package test.com.xerox.enterprise.ghs.mmis.rif.rules.pojo;

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

import java.util.ArrayList;
import java.util.List;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;

import java.text.SimpleDateFormat; 
import java.util.Date; 
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult; 


/** 
 * PGM4005_9002_NH01 Tester. 
 * 
 * @author 396662
 * @since <pre>Thu Dec 18 12:56:03 IST 2014</pre> 
 * @version 1.0 
 */ 
public class PGM4005_9002_NH01Test extends TestCase { 
    public PGM4005_9002_NH01Test(String name) { 
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
 
		SimpleDateFormat sdf1 = null;
 
		Date date = null;
 
		String strdate = null;
 
		List<Object> inputObjs = null;
 
		
		ric.addObject(sdf1);
		ric.addObject(date);
		ric.addObject(strdate);
		
		RulesManager rulesManager = RulesManager.getRIFInstance();
		rulesResult=rulesManager.execute(ruleId,ric);
    	assertEquals(true,rulesResult.getRuleStatus());
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(PGM4005_9002_NH01Test.class); 
    } 
} 