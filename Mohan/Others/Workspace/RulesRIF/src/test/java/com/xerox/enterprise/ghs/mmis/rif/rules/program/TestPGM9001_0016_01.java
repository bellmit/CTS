package com.xerox.enterprise.ghs.mmis.rif.rules.program;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.acs.enterprise.common.program.administration.util.helper.ProgramCodeMaintanaceConstants;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;

/**
 * The class PGM9001_0016_01Test contains tests for the class PGM9001_0016_01.
 * 
 * @author 396662
 * @generatedBy RIF
 * @since <pre>
 * Wed Jan 14 11:43:06 IST 2015
 * </pre>
 * @version 1.0
 */
public class TestPGM9001_0016_01 extends TestCase {
	private Object minMonthCompID;






	public TestPGM9001_0016_01(String name) {
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
		// TODO: Test goes here...

		// TODO: Assignment
		String maxMonth = "1";
		boolean isNumeric = false;
		String minYr = "0";
		String maxYr = "0";
		String minMonth = "1";

		System.out.println("Started Rule Execution::::");

		// Rule Invocation Context object holds object arrays used in rule
		// execution.
		String ruleId = "PGM9001.0016.01";
		RulesContext ric = new RulesContext();
		// Adding object into rule invocation context.
		ric.addObject(maxMonth);
		ric.addObject(minYr);
		ric.addObject(maxYr);
		ric.addObject(minMonth);
		String minYrCompID = null;
		ric.addObject(minYrCompID);
		ric.addObject(minMonthCompID);
		String maxYrCompID = null;
		ric.addObject(maxYrCompID);
		String maxMnthCompID = null;
		ric.addObject(maxMnthCompID);
		// Invoke RIF with ruleid and rule invocation objects.
		RulesResult rulesResult = null;

		try {
			RulesManager rulesManager = RulesManager.getRIFInstance();
			rulesResult = rulesManager.execute(ruleId, ric);
			isNumeric = rulesResult.isReturnBooleanValue();
		} catch (RIFException re) {
			System.out.println("RIFException::::" + re);
			re.printStackTrace();
		}
		ConcurrentHashMap<String,String> errorMsgMap=(ConcurrentHashMap<String,String>) rulesResult.getReturnValue();
		System.out.println("errormsg"+errorMsgMap);
		System.out.println("Rule Execution Ended::::" + ruleId + "\n");

		assertEquals(true, rulesResult.isReturnBooleanValue());
	}

	public void testEXECUTE1() throws Exception {
		// TODO: Test goes here...

		// TODO: Assignment
		String maxMonth = "1";
		boolean isNumeric = false;
		String minYr = "1";
		String maxYr = "1";
		String minMonth = "1";

		System.out.println("Started Rule Execution::::");

		// Rule Invocation Context object holds object arrays used in rule
		// execution.
		String ruleId = "PGM9001.0016.01";
		RulesContext ric = new RulesContext();
		// Adding object into rule invocation context.
		ric.addObject(maxMonth);
		ric.addObject(minYr);
		ric.addObject(maxYr);
		ric.addObject(minMonth);
		String minYrCompID = null;
		ric.addObject(minYrCompID);
		ric.addObject(minMonthCompID);
		String maxYrCompID = null;
		ric.addObject(maxYrCompID);
		String maxMnthCompID = null;
		ric.addObject(maxMnthCompID);

		// Invoke RIF with ruleid and rule invocation objects.
		RulesResult rulesResult = null;

		try {
			RulesManager rulesManager = RulesManager.getRIFInstance();
			rulesResult = rulesManager.execute(ruleId, ric);
			isNumeric = rulesResult.isReturnBooleanValue();
		} catch (RIFException re) {
			System.out.println("RIFException::::" + re);
			re.printStackTrace();
		}
		ConcurrentHashMap<String,String> errorMsgMap=(ConcurrentHashMap<String,String>) rulesResult.getReturnValue();
		System.out.println("errormsg"+errorMsgMap);
		
		if(errorMsgMap!=null)
		{
			
			if(errorMsgMap.containsKey("minYrCompID"))
			{
				System.out.println("1"+errorMsgMap.get("minYrCompID"));
			}
			if(errorMsgMap.containsKey("minMonthCompID"))
			{
				System.out.println("2"+errorMsgMap.get("minMonthCompID"));
			}
			if(errorMsgMap.containsKey("maxYrCompID"))
			{
				System.out.println("3"+errorMsgMap.get("maxYrCompID"));
			}
			if(errorMsgMap.containsKey("maxMnthCompID"))
			{
				System.out.println("4"+errorMsgMap.get("maxMnthCompID"));
			}
			
			
			/*if(errorMsgMap.contains(ProgramCodeMaintanaceConstants.MIN_YEAR_NOT_IN_RAGE))
			{
				System.out.println("1");
				setErrorMessage(
						ProgramCodeMaintanaceConstants.MIN_YEAR_NOT_IN_RAGE,
						new Object[] {},
						ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
						minYrCompID);
			}
			if(errorMsgMap.contains(ProgramCodeMaintanaceConstants.MINIMUM_YEAR_NOT_INTEGER))
			{
				System.out.println("2");
				setErrorMessage(ProgramCodeMaintanaceConstants.
						 MINIMUM_YEAR_NOT_INTEGER, new Object[] {},
						 ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
						 minYrCompID);
			}
			if(errorMsgMap.contains(ProgramCodeMaintanaceConstants.MIN_MONTHS_NOT_IN_RANGE))
			{
				System.out.println("3");
				setErrorMessage(ProgramCodeMaintanaceConstants.
						 MIN_MONTHS_NOT_IN_RANGE, new Object[] {},
						 ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
						 minMonthCompID);
			}
			if(errorMsgMap.contains(ProgramCodeMaintanaceConstants.MAX_YEAR_NOT_IN_RAGE))
			{
				System.out.println("4");
				setErrorMessage(ProgramCodeMaintanaceConstants.
						 MAX_YEAR_NOT_IN_RAGE, new Object[] {},
						 ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
						 maxYrCompID);
			}
			if(errorMsgSet.contains(ProgramCodeMaintanaceConstants.MAXIMUM_YEAR_NOT_INTEGER))
			{
				System.out.println("5");
				setErrorMessage(
						ProgramCodeMaintanaceConstants.MAXIMUM_YEAR_NOT_INTEGER,
						new Object[] {},
						ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
						maxYrCompID);
			}
			if(errorMsgSet.contains(ProgramCodeMaintanaceConstants.MAX_MONTHS_NOT_IN_RANGE))
			{
				System.out.println("6");
				setErrorMessage(ProgramCodeMaintanaceConstants.
						 MAX_MONTHS_NOT_IN_RANGE, new Object[] {},
						 ProgramCodeMaintanaceConstants.DIAGNOSIS_PROPERTIES_DETAILS,
						 maxMnthCompID);
			}*/
		}
		
		
		System.out.println("Rule Execution Ended::::" + ruleId + "\n");

		
		
		
		assertEquals(true, rulesResult.isReturnBooleanValue());
	}
	
	
	
	
	
	
	public static Test suite() {
		return new TestSuite(TestPGM9001_0016_01.class);
	}
}