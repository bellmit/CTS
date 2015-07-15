package com.rules.external;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xerox.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.ghs.mmis.rif.core.RuleHandler;
import com.xerox.ghs.mmis.rif.core.RuleInvocationContext;
import com.xerox.ghs.mmis.rif.core.RulesResult;
import com.xerox.ghs.mmis.rif.rules.operations.SALineItemInfo;

/**
 * Servlet implementation class PojoController
 */
public class PojoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PojoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		request.setAttribute("resultBeforeChange","Approval Date= "+resultBeforeChange);
		request.setAttribute("resultAfterChange", "Approval Date= "+saInfo.getApprovalDate());		
		request.setAttribute("responseData", responseData);
		request.setAttribute("ruleName",ruleName);
		request.setAttribute("errorData",errorData);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);	
	}

}
