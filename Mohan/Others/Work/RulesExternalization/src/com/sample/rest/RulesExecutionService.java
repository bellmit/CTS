package com.sample.rest;
 
import javax.servlet.RequestDispatcher;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.rules.external.RIFClient;
import com.xerox.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.ghs.mmis.rif.core.RuleHandler;
import com.xerox.ghs.mmis.rif.core.RuleInvocationContext;
import com.xerox.ghs.mmis.rif.core.RulesResult;
import com.xerox.ghs.mmis.rif.rules.operations.SALineItemInfo;

import com.xerox.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.ghs.mmis.rif.core.RuleHandler;
import com.xerox.ghs.mmis.rif.core.RuleInvocationContext;
import com.xerox.ghs.mmis.rif.core.RulesResult;
import com.xerox.ghs.mmis.rif.rules.operations.SALineItemInfo;

@Path("RulesExternalization")
public class RulesExecutionService {
	private static final Logger logger = Logger.getLogger(RIFClient.class); 
	@GET
	@Path("/ExecutePojoService")	
	public Response executePojoService() {
 
		
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
		String resultBeforeChange=String.valueOf(saInfo.getApprovalDate());
		//Invoke RIF with rulename and rule invocation objects.
		RulesResult rulesResult = null;
		String errorData=null; 
		try {
			rulesResult = RuleHandler.execute(ruleName, ric);
		} catch (RIFException e) {
			errorData=e.getMessage();
			e.printStackTrace();
		}
		
		System.out.println("changed date---"+saInfo.getApprovalDate());
		System.out.println("Result of rule execute-"+rulesResult.getRuleStatus());
		System.out.println("Rule Execution Ended::::"+ruleName+"\n");
		String responseData=String.valueOf(rulesResult.getRuleStatus()).replaceAll("true","success");
		ResponseVO responseVo=new ResponseVO();
		responseVo.setResponseData(responseData);
		responseVo.setResultBeforeChange("Approval Date= "+resultBeforeChange);
		responseVo.setResultAfterChange("Approval Date= "+saInfo.getApprovalDate());
		responseVo.setRuleName(ruleName);
		responseVo.setErrorData(errorData);
		if(errorData==null || errorData.length()==0)
			return Response.status(503).entity(errorData).build();
		else			
			return Response.status(200).entity(responseVo).build();
	}
 
}