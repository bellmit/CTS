package com.xerox.enterprise.ghs.mmis.rif.rules.program;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.acs.enterprise.common.program.administration.util.helper.ProgramCodeMaintanaceConstants;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;

/**
 * The class PGM9001_0018_01Test contains tests for the class PGM9001_0018_01.
 * 
 * @author 396662
 * @generatedBy RIF
 * @since <pre>
 * Wed Jan 14 11:43:06 IST 2015
 * </pre>
 * @version 1.0
 */
public class TestPGM9001_0018_01 extends TestCase {
	public TestPGM9001_0018_01(String name) {
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
		String maxMonth = "0";
		boolean isNumeric = true;
		String minYr = "11";
		String maxYr ="2";
		String minMonth = "0";

		System.out.println("Started Rule Execution::::");

		// Rule Invocation Context object holds object arrays used in rule
		// execution.
		String ruleId = "PGM9001.0018.01";
		RulesContext ric = new RulesContext();
		// Adding object into rule invocation context.
		ric.addObject(maxMonth);
		ric.addObject(isNumeric);
		ric.addObject(minYr);
		ric.addObject(maxYr);
		ric.addObject(minMonth);

		// Invoke RIF with ruleid and rule invocation objects.
		RulesResult rulesResult = null;

		try {
			RulesManager rulesManager = RulesManager.getRIFInstance();
			rulesResult = rulesManager.execute(ruleId, ric);
			isNumeric = rulesResult.isReturnBooleanValue();
		} catch (RIFException re) {
			System.out.println("RIFException::::" + re);
		}
		System.out.println("Rule Execution Ended::::" + ruleId + "\n");

		assertEquals(ProgramCodeMaintanaceConstants.MINAGE_GREATER_THAN_MAXAGE, rulesResult.getReturnValue());
		
		//assertEquals(true, rulesResult.isReturnBooleanValue());
	}

	public static Test suite() {
		return new TestSuite(TestPGM9001_0018_01.class);
	}
}