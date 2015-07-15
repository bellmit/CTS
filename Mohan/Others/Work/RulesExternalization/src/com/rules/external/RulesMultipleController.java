package com.rules.external;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acs.enterprise.mmis.provider.support.application.vo.TPLetterEventRequestVO;
import com.xerox.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.ghs.mmis.rif.core.RuleHandler;
import com.xerox.ghs.mmis.rif.core.RuleInvocationContext;
import com.xerox.ghs.mmis.rif.core.RulesResult;
import com.xerox.ghs.mmis.rif.rules.operations.SALineItemInfo;

/**
 * Servlet implementation class RulesPojoandBlazeController
 */
public class RulesMultipleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RulesMultipleController() {
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
		
		String ruleName1 = "/operations/serviceauth/SALineItemStatusChange";
		String ruleName2 = "/provider/domains/TradingPartnerDeniedLetter";
		String[] ruleName=new String[]{ruleName1,ruleName2};
		RuleInvocationContext ric1 = new RuleInvocationContext(1);
		RuleInvocationContext ric2 = new RuleInvocationContext(1);
		
		RuleInvocationContext[] ric=new RuleInvocationContext[]{ric1,ric2};

		SALineItemInfo saInfo = new SALineItemInfo();
		
		saInfo.setLineItemStatusCode("A");
		ric1.addObject(saInfo);
		
		
		TPLetterEventRequestVO tpLetterVO = new TPLetterEventRequestVO();
		tpLetterVO.setEventCode("D");		
		ric2.addObject(tpLetterVO);
		
		Map<String,RuleInvocationContext> ricMap = new HashMap<String, RuleInvocationContext>();
		for (int i=0;i<ruleName.length;i++){
			ricMap.put(ruleName[i], ric[i]);	
		}
		
		
		RulesResult[] rulesResult = null;
		String errorData="";
		String resultBeforeChange=String.valueOf(saInfo.getApprovalDate());
		String resultBeforeChangeLetter=String.valueOf(tpLetterVO.getLetterID());
		
		try {
			rulesResult = RuleHandler.execute(ricMap);
			
		} catch(RIFException rife){
			errorData=rife.getMessage();
			System.out.println("Unknown exception occured while executing RIF"+rife);
		}
		
		String statusData="";
		int multipleCount=rulesResult.length;
		int n=0;
		request.setAttribute("ruleCount",multipleCount);
		
		for (int i=0;i<multipleCount;i++){
			statusData=String.valueOf(rulesResult[i].getRuleStatus());
			String responseData=statusData.replaceAll("true","success");
			
			n=n+1;
			if(ruleName[i].indexOf("SALineItemStatusChange")>0){
				request.setAttribute("resultBeforeChange"+n,"Approval Date= "+resultBeforeChange);
				request.setAttribute("resultAfterChange"+n, "Approval Date= "+saInfo.getApprovalDate());
			}else if(ruleName[i].indexOf("TradingPartnerDeniedLetter")>0){
				request.setAttribute("resultBeforeChange"+n,"LetterID= "+resultBeforeChangeLetter);
				request.setAttribute("resultAfterChange"+n, "LetterID= "+tpLetterVO.getLetterID());		
			}else{
				request.setAttribute("resultBeforeChange"+n,resultBeforeChange);
				request.setAttribute("resultAfterChange"+n, saInfo.getApprovalDate());
			}
			request.setAttribute("responseData"+n, responseData);
			request.setAttribute("ruleName"+n,ruleName[i]);
			request.setAttribute("errorData"+n,rulesResult[i].getExceptionMsg());
		}		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
