

import java.text.SimpleDateFormat;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;

import junit.framework.TestCase;

/**
 * The class <code>PGM4005_9002_NH01Test</code> contains tests for the class
 * {@link <code>PGM4005_9002_NH01</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 12/10/14 12:21 PM
 *
 * @author 414774
 *
 * @version $Revision$
 */
public class PGM4005_9002_NH01Test extends TestCase {

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public PGM4005_9002_NH01Test(String name) {
		super(name);
	}


/*$CPS$ This comment was generated by CodePro. Do not edit it.
 * patternId = com.instantiations.assist.eclipse.pattern.testCasePattern
 * strategyId = com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase
 * additionalTestNames = 
 * assertTrue = false
 * callTestMethod = true
 * createMain = false
 * createSetUp = false
 * createTearDown = false
 * createTestFixture = false
 * createTestStubs = false
 * methods = 
 * package = com.rules
 * package.sourceFolder = MMIS_RIF_Rules/src
 * superclassType = junit.framework.TestCase
 * testCase = PGM4005_9002_NH01Test
 * testClassType = com.rules.PGM4005_9002_NH01
 */

public void testExecute() throws RIFException {
	int objSize = 4;

	// Rule Invocation Context object holds object arrays used in rule
	// execution.
	// Name of the rule to be executed.
	String ruleId = "PGM4005.9002.NH01";
	RulesContext ric = new RulesContext();
	// Adding object into rule invocation context.
	SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
	ric.addObject(dateFormat); 
	ric.addObject("01/12/2015"); 

	boolean result = false;

	RulesManager rulesManager = RulesManager.getRIFInstance();

	// Invoke RIF with rulename and rule invocation objects.
	RulesResult rulesResult = null;

	try {
		rulesResult = rulesManager.execute(ruleId, ric);
		result = rulesResult.isReturnBooleanValue();
		System.out.println("Rule boolean " + result);	
	} catch (RIFException re) {
		re.printStackTrace();
	}
	
	System.out.println("Rule boolean " + rulesResult.isReturnBooleanValue());
	assertTrue(rulesResult.isReturnBooleanValue());
}

}

/*

public boolean isExpired(String strdate) {
	
	Date date = null;
	//Modified for Defect ESPRD00898366
	Date currentDate=null;
	String newDate=null;
	SimpleDateFormat sdf1 = new SimpleDateFormat(BenefitPlanConstants.DATE_FORMAT);
				  
	  
				  
			  int objSize = 5;	
          System.out.println("unused one");

         
          logger.info("Started Rule Execution::::"+ruleName);
          
          //Rule Invocation Context object holds object arrays used in rule execution.
		  			    //Name of the rule to be executed.
		    String ruleId = "PGM4005.9002.NH01";
		    RulesContext ric = new RulesContext(objSize);
		    //Adding object into rule invocation context.
		 	//ric.addObject(currentDate);
			//ric.addObject(logger);
			ric.addObject(sdf1);
			//ric.addObject(date);
			ric.addObject(strdate);
  			
		  RulesManager rulesManager = RulesManager.getRIFInstance();
          
          //Invoke RIF with rulename and rule invocation objects.
		  			    RulesResult rulesResult = null;
		                
          try {
                 						rulesResult = rulesManager.execute(ruleId, ric);	
				  					                } catch (RIFException re){
                 
          }
		  					if (!ruleResult.isReturnBooleanValue()) {
				  flag = false;
				}
		   	}	
          
          logger.info("Rule Execution Ended::::"+ruleName+"\n");
*/