package test.com.cognizant.RIF.rules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
 
/** 
 * CON0428_0001_01 Tester. 
 * 
 * @author 396662
 * @since <pre>Wed Nov 05 18:37:25 IST 2014</pre> 
 * @version 1.0 
 */ 
public class CON0428_0001_01Test_Demo extends TestCase { 
    public CON0428_0001_01Test_Demo(String name) { 
        super(name); 
    } 
 
    public void setUp() throws Exception { 
        super.setUp(); 
    } 
 
    public void tearDown() throws Exception { 
        super.tearDown(); 
    } 
    /** 
     * 
     * Method: void 
     * 
     */ 
	 
    public void testEXECUTE() throws Exception { 
    	RulesContext ctx=new RulesContext();
    	List<Object> contextObject=new ArrayList<Object>();
    	String ruleId="";
    	
    	Date PacketReceivedDate = new Date();
        boolean flag = false;
        Date currentDate = new Date();
        
        contextObject.add(PacketReceivedDate);
    	contextObject.add(flag);
    	contextObject.add(currentDate);
    	
    	ctx.setContextObject(contextObject);
    	   	
    	RulesResult rr=CON0428_0001_01.execute(ctx, ruleId);
    	assertEquals(true,rr.getRuleStatus());
    	
    } 
 
 
 
    public static Test suite() { 
        return new TestSuite(CON0428_0001_01Test_Demo.class); 
    } 
} 