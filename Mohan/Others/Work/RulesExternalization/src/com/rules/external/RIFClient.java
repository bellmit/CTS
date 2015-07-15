package com.rules.external;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import com.acs.enterprise.mmis.provider.support.application.vo.TPLetterEventRequestVO;
import com.xerox.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.ghs.mmis.rif.core.RuleHandler;
import com.xerox.ghs.mmis.rif.core.RuleInvocationContext;
import com.xerox.ghs.mmis.rif.core.RulesResult;
import com.xerox.ghs.mmis.rif.rules.operations.SALineItemInfo;


public class RIFClient {
	
	public static void main(String[] args) throws RIFException {
		while(true) {
		
			System.out.println("Options to execute this test program:::"+"\n");
			System.out.println("1. Execute POJO rule- /operations/serviceauth/SALineItemStatusChange:"+"\n");
			System.out.println("2. Execute POJO rule- /provider/domains/TradingPartnerDeniedLetter:"+"\n");
			System.out.println("3. Execute Blaze rule- /ProviderService/Enroll/ProviderEnrRule:"+"\n");
			System.out.println("4. Execute POJO rule in batch mode:"+"\n");
			System.out.println("5. Quit:"+"\n");
			
			RIFClient testRIF= new RIFClient();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		    int execInput = 1;
	
		    //  read the input from the command-line; 
		    try {
		      execInput = Integer.parseInt(br.readLine());
		    } catch (IOException ioe) {
		      System.out.println("IO error trying to read the input!");
		      System.exit(1);
		    }
		    switch(execInput) {
		    	case 1:
		    		testRIF.invokePOJORule1();
		    		break;
		    	case 2:
		    		testRIF.invokePOJORule2();
		    		break;
		    	case 3:
		    		testRIF.invokeBlazeRule2();
		    		break;
		    	case 4:
		    		testRIF.invokeMultipleRules();
		    		break;
		    	case 5:
		    		System.out.println("Good Bye !!!");
		    		System.exit(1);		    		
		    		break;
		    	default:
		    		testRIF.invokePOJORule1();
		    		break;
		    }
		}
	}
	

	
	private void invokePOJORule1() throws RIFException {
		// Number of objects to be added to Rule context
		int objSize = 1;
		
		//Name of the rule to be executed.
		String ruleName = "/operations/serviceauth/SALineItemStatusChange";
		System.out.println("Started Rule Execution::::"+ruleName);
		
		//Rule Invocation Context object holds object arrays used in rule execution.
		RuleInvocationContext ric = new RuleInvocationContext(objSize);
		
		//Objects used in rule execution
		SALineItemInfo saInfo = new SALineItemInfo();		
		saInfo.setLineItemStatusCode("A");
		
		//Adding object into rule invocation context.
		ric.addObject(saInfo);
		System.out.println("old date---"+saInfo.getApprovalDate());		
		
		//Invoke RIF with rulename and rule invocation objects.
		RulesResult rulesResult = RuleHandler.execute(ruleName, ric);
		
		System.out.println("changed date---"+saInfo.getApprovalDate());
		System.out.println("Result of rule execute-"+rulesResult.getRuleStatus());
		System.out.println("Rule Execution Ended::::"+ruleName+"\n");
	}
	
	
	private void invokePOJORule2() throws RIFException {
		// Number of objects to be added to Rule context
		int objSize = 1;
		
		//Name of the rule to be executed.
		String ruleName = "/provider/domains/TradingPartnerDeniedLetter";
		System.out.println("Started Rule Execution::::"+ruleName);
		
		//Rule Invocation Context object holds object arrays used in rule execution.
		RuleInvocationContext ric = new RuleInvocationContext(objSize);
		
		//Objects used in rule execution
		TPLetterEventRequestVO tpLetterVO = new TPLetterEventRequestVO();
		tpLetterVO.setEventCode("D");		
		
		//Adding object into rule invocation context.
		ric.addObject(tpLetterVO);
				
		System.out.println("LetterId before rule Execute ---"+tpLetterVO.getLetterID());
		
		//Invoke RIF with rulename and rule invocation objects.
		RulesResult rulesResult = RuleHandler.execute(ruleName, ric);
		System.out.println("LetterId after rule Execute ---"+tpLetterVO.getLetterID());
		System.out.println("Result of rule execute-"+rulesResult.getRuleStatus());
		System.out.println("Rule Execution Ended::::"+ruleName);
	}
	
	private void invokeBlazeRule1() throws SAXException, IOException  {	
	
		String ruleName = "/operationservice/ClaimAdj/OperationClaimAdj";
		System.out.println("Started Rule Execution::::"+ruleName);
		Document topNode = null;
		DOMParser parser = new DOMParser();
		Vector<String> vectSysParalistdoc = new Vector<String>();
        Vector<String> vectSyslistdoc = new Vector<String>();
        RuleInvocationContext ctx = new RuleInvocationContext(6);
        
		parser.parse("D:\\MMIS\\Rule-Extraction\\BlazeObjectXMLS\\proobj.xml");
		topNode = parser.getDocument();
		ctx.addObject(topNode);
		
		parser.parse("D:\\MMIS\\Rule-Extraction\\BlazeObjectXMLS\\prorules.xml");
		topNode = parser.getDocument();
		ctx.addObject(topNode);	
		
		
		parser.parse("D:\\MMIS\\Rule-Extraction\\BlazeObjectXMLS\\proclaim.xml");
		topNode = parser.getDocument();
		ctx.addObject(topNode);
		
		parser.parse("D:\\MMIS\\Rule-Extraction\\BlazeObjectXMLS\\RuleContextBO.xml");
		topNode = parser.getDocument();
		ctx.addObject(topNode);
		
		ctx.addObject(vectSyslistdoc);
		ctx.addObject(vectSysParalistdoc);
		RulesResult rulesResult = null;
		
		try {
			rulesResult = RuleHandler.execute(ruleName, ctx);
			
		} catch(RIFException rife){
			System.out.println("Unknown exception occured while executing RIF"+rife);
		}		
		System.out.println("Rule Execution Ended::::"+ruleName);
	}
	
	private void invokeMultipleRules() {
		String ruleName1 = "/operations/serviceauth/SALineItemStatusChange";
		
		RuleInvocationContext ric1 = new RuleInvocationContext(1);		
		SALineItemInfo saInfo = new SALineItemInfo();
		
		saInfo.setLineItemStatusCode("A");
		ric1.addObject(saInfo);
		String ruleName2 = "/provider/domains/TradingPartnerDeniedLetter";
		RuleInvocationContext ric2 = new RuleInvocationContext(1);
		TPLetterEventRequestVO tpLetterVO = new TPLetterEventRequestVO();
		tpLetterVO.setEventCode("D");		
		ric2.addObject(tpLetterVO);
		
		Map<String,RuleInvocationContext> ricMap = new HashMap<String, RuleInvocationContext>();
		ricMap.put(ruleName1, ric1);
		ricMap.put(ruleName2, ric2);
		RulesResult[] rulesResult = null;
		
		try {
			rulesResult = RuleHandler.execute(ricMap);
			
		} catch(RIFException rife){
			System.out.println("Unknown exception occured while executing RIF"+rife);
		}
	}
	
	private void invokeBlazeRule2() {
	
		// Number of objects to be added to Rule context
		int objSize = 2;	
	
		//Name of the rule to be executed.
		String ruleName = "/ProviderService/Enroll/ProviderEnrRule";
		
		//Rule Invocation Context object holds object arrays used in rule execution.
		RuleInvocationContext ctx = new RuleInvocationContext(objSize);
		Document topNode = null;
		DOMParser parser = new DOMParser();
		try {
			parser.parse("D:\\MMIS\\Rule-Extraction\\BlazeObjectXMLS\\Rules.xml");
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		topNode = parser.getDocument();
		
		//Adding objects into rule invocation context.
		ctx.addObject(topNode);
		ctx.addObject(null);
		
		//Invoke RIF by rulename with rule invocation objects.
		RulesResult rulesResult = null;
		try {
			rulesResult = RuleHandler.execute(ruleName, ctx);
		} catch (RIFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		com.acs.enterprise.common.rules.vo.RulesResult result = (com.acs.enterprise.common.rules.vo.RulesResult)rulesResult.getReturnValue();
		
		String ruleResultObj = result.getReturnValue()[0];
			
        System.out.println("RulesResult" + ruleResultObj);
		
	}

}
