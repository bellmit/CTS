package com.rules.external;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.acs.enterprise.mmis.provider.support.application.vo.TPLetterEventRequestVO;
import com.xerox.ghs.mmis.rif.common.exception.RIFException;
import com.xerox.ghs.mmis.rif.core.RuleHandler;
import com.xerox.ghs.mmis.rif.core.RuleInvocationContext;
import com.xerox.ghs.mmis.rif.core.RulesResult;

/**
 * Servlet implementation class RulesBlazeController
 */
public class RulesBlazeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RulesBlazeController() {
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
		String ruleName = "/operationservice/ClaimAdj/OperationClaimAdj";
		System.out.println("Started Rule Execution::::"+ruleName);
		Document topNode = null;
		DOMParser parser = new DOMParser();
		Vector<String> vectSysParalistdoc = new Vector<String>();
        Vector<String> vectSyslistdoc = new Vector<String>();
        RuleInvocationContext ctx = new RuleInvocationContext(6);
        String errorData="";
        
		try {
			parser.parse("D:\\inputfiles\\proobj.xml");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			errorData+="/"+e.getMessage();
		}catch (Exception e){
			errorData+="/"+e.getMessage();
		}
		topNode = parser.getDocument();
		ctx.addObject(topNode);
		
		try {
			parser.parse("D:\\inputfiles\\prorules.xml");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			errorData+="/"+e.getMessage();
		}catch (Exception e){
			errorData+="/"+e.getMessage();
		}
		topNode = parser.getDocument();
		ctx.addObject(topNode);	
		
		
		try {
			parser.parse("D:\\inputfiles\\proclaim.xml");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			errorData+="/"+e.getMessage();
		}catch (Exception e){
			errorData+="/"+e.getMessage();
		}
		topNode = parser.getDocument();
		ctx.addObject(topNode);
		
		try {
			parser.parse("D:\\inputfiles\\RuleContextBO.xml");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			errorData+="/"+e.getMessage();
		}catch (Exception e){
			errorData+="/"+e.getMessage();
		}
		topNode = parser.getDocument();
		ctx.addObject(topNode);
		
		ctx.addObject(vectSyslistdoc);
		ctx.addObject(vectSysParalistdoc);
		RulesResult rulesResult = null;
		
		try {
			rulesResult = RuleHandler.execute(ruleName, ctx);
			
		} catch(RIFException rife){
			System.out.println("Unknown exception occured while executing RIF"+rife);
			errorData+="/"+rife.getMessage();
		}catch(Exception ex){
			errorData+="/"+ex.getMessage();
		}
		System.out.println("Rule Execution Ended::::"+ruleName);
		
		
	

		request.setAttribute("ruleName",ruleName);
		request.setAttribute("errorData",errorData);				
		String responseData=String.valueOf(rulesResult.getRuleStatus()).replaceAll("true","success");
//		request.setAttribute("resultBeforeChange","Date= "+resultBeforeChange);
//		request.setAttribute("resultAfterChange", "Date= "+saInfo.getApprovalDate());
		request.setAttribute("responseData", responseData);		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
