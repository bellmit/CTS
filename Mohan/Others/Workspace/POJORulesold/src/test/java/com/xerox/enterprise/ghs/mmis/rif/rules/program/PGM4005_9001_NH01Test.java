package com.xerox.enterprise.ghs.mmis.rif.rules.program;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;
import com.xerox.enterprise.ghs.mmis.rif.core.api.RulesManager;

/**
 * The class <code>PGM4005_9001_NH01Test</code> contains tests for the class
 * {@link <code>PGM4005_9001_NH01</code>}
 * @pattern JUnit Test Case
 * @generatedBy CodePro at 12/9/14 4:42 PM
 * @author 414774
 */
public class PGM4005_9001_NH01Test extends TestCase {

	/**
	 * Construct new test instance
	 * 
	 * @param name
	 *            the test name
	 */
	public PGM4005_9001_NH01Test(String name) {
		super(name);
	}

	/*
	 * junit.framework.TestCase testCase = PGM4005_9001_NH01Test testClassType =
	 * com.rules.PGM4005_9001_NH01
	 */
	@Test
	public void testExecute() throws RIFException {

		// Rule Invocation Context object holds object arrays used in rule
		// execution.
		String ruleId = "PGM4005.9001.NH01";
		RulesContext ric = new RulesContext();
		List<String> capitationList=new ArrayList<String>();
		capitationList.add("TEST");
		
		// Adding object into rule invocation context.
		ric.addObject(true); 
		ric.addObject("HIIF"); // CurrentProcCode
		ric.addObject("DELGI"); // RetroProcCode
		ric.addObject(capitationList ); // CapitationList
		
		boolean flag = true;

		RulesManager rulesManager = RulesManager.getRIFInstance();

		// Invoke RIF with rulename and rule invocation objects.
		RulesResult rulesResult = null;

		try {
			rulesResult = rulesManager.execute(ruleId, ric);
			flag = rulesResult.isReturnBooleanValue();
			//System.out.println("Rule boolean " + flag);	
		} catch (RIFException re) {
			re.printStackTrace();
		}
		if (!flag) {
			//SetErrormsg
		}
		//System.out.println("Rule boolean " + rulesResult.isReturnBooleanValue());
		assertFalse(flag);
	}
	
	@Test(expected=RIFException.class)
	
	public void testExecute1() {

		// Rule Invocation Context object holds object arrays used in rule
		// execution.
		String ruleId = "PGM4005.9001.NH01";
		RulesContext ric = null;
		RulesResult rulesResult = null;

		try {
			RulesManager rulesManager = RulesManager.getRIFInstance();
			rulesResult = rulesManager.execute(ruleId, ric);
				
		} catch (RIFException re) {
			
			re.printStackTrace();
		}
		
	}
	
	@Test
	public void testExecute2() throws RIFException {

		// Rule Invocation Context object holds object arrays used in rule
		// execution.
		String ruleId = "PGM4005.9001.NH01";
		RulesContext ric = new RulesContext();
		List<String> capitationList=new ArrayList<String>();
		capitationList.add("TEST");
		
		// Adding object into rule invocation context.
		ric.addObject(true); 
		ric.addObject(""); // CurrentProcCode
		ric.addObject(""); // RetroProcCode
		ric.addObject(capitationList ); // CapitationList
		
		boolean flag = true;

		RulesManager rulesManager = RulesManager.getRIFInstance();

		// Invoke RIF with rulename and rule invocation objects.
		RulesResult rulesResult = null;

		try {
			rulesResult = rulesManager.execute(ruleId, ric);
			flag = rulesResult.isReturnBooleanValue();
			//System.out.println("Rule boolean " + flag);	
		} catch (RIFException re) {
			re.printStackTrace();
		}
		if (!flag) {
			//SetErrormsg
		}
		//System.out.println("Rule boolean " + rulesResult.isReturnBooleanValue());
		assertFalse(flag);
	}

	
	
	public void testINITIALIZE()
	{
			try {
				PGM4005_9001_NH01.initialize();
			} catch (RIFPOJORulesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void testSHUTDOWN()
	{
			try {
				PGM4005_9001_NH01.shutdown();
			} catch (RIFPOJORulesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

