package com.rules.external;

import java.io.IOException;

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

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.xerces.parsers.DOMParser;

/**
 * Servlet implementation class RulesExceptionController
 */
public class RulesExceptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RIFClient.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RulesExceptionController() {
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
        // Number of objects to be added to Rule context
        int objSize = 1;
        
        //Name of the rule to be executed.
        String ruleName = "/provider/domains/TradingPartnerDeniedLetter";
        logger.info("Started Rule Execution::::"+ruleName);
        
        //Rule Invocation Context object holds object arrays used in rule execution.
        RuleInvocationContext ric = new RuleInvocationContext(objSize);
        
        //Objects used in rule execution
        TPLetterEventRequestVO tpLetterVO = new TPLetterEventRequestVO();
        tpLetterVO.setEventCode("D");            
        
        //Adding object into rule invocation context.
        //ric.addObject(tpLetterVO);
                     
        logger.info("LetterId before rule Execute ---"+tpLetterVO.getLetterID());
        
        //Invoke RIF with rule name and rule invocation objects.
        String resultBeforeChange=tpLetterVO.getLetterID();
        RulesResult rulesResult=null;
		String errorData=null; 
		try {
			rulesResult = RuleHandler.execute(ruleName, ric);
		} catch (RIFException e) {
			// TODO Auto-generated catch block
			errorData=e.getMessage();
			e.printStackTrace();
		}
        logger.info("LetterId after rule Execute ---"+tpLetterVO.getLetterID());
        logger.info("Result of rule execute-"+rulesResult.getRuleStatus());
        if(!rulesResult.getRuleStatus()) {
               logger.error("Exception of this rule execution::"+rulesResult.getExceptionMsg());
        }
        logger.info("Rule Execution Ended::::"+ruleName);
        
		String responseData=String.valueOf(rulesResult.getRuleStatus()).replaceAll("true","success");
		request.setAttribute("resultBeforeChange","LetterID= "+resultBeforeChange);
		request.setAttribute("resultAfterChange", "LetterID= "+tpLetterVO.getLetterID());			
		request.setAttribute("responseData", responseData);
		request.setAttribute("ruleName",ruleName);
		request.setAttribute("errorData",rulesResult.getExceptionMsg());		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);	

	}

}
