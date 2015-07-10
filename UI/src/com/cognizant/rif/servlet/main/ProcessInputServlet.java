package com.cognizant.rif.servlet.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import com.cognizant.rif.constants.RIFInputConstants;
import com.cognizant.rif.rules.ConditionsExtractor;
import com.cognizant.rif.rules.MessageConstantExtractor;
import com.cognizant.rif.utilities.ValidatorUtil;
import com.cognizant.rif.vo.RuleReportVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class ProcessInputServlet
 */
@WebServlet("/processInput")
public class ProcessInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		System.out.println("Servlet GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ProcessInputServlet Post method Invoked");
		
		String userName=(String) request.getParameter("userName");
		userName="396662";
		request.getSession().setAttribute("userName", userName);
		
		String contextPath=request.getServletContext().getRealPath("");
		File file=new File(contextPath.concat("\\"+RIFInputConstants.RULE_EXTRACTION_FOLDER+"\\").concat(userName));
		file.mkdirs();
		
		System.out.println("File Path"+file.getAbsolutePath());
	
		String ruleid=(String) request.getParameter("ruleId");
		String messageId=(String) request.getParameter("messageId");
		String module=(String) request.getParameter("module");
		
		System.out.println("Rule:"+ruleid);
		System.out.println("MessageId:"+messageId);
		System.out.println("Module:"+module);
		String sourceCodepath=RIFInputConstants.SOURCE_CODE_ROOT_DIR;
		if(!(module.isEmpty() || module.equalsIgnoreCase("All")))
		{
			sourceCodepath=sourceCodepath+"\\"+module;
		}
		System.out.println("Module source:"+sourceCodepath);
		
		List<String> ruleIDList=new ArrayList<>();
		RuleReportVO ruleRepVo=new RuleReportVO();
		
		if(ruleid!=null && ! ruleid.isEmpty())
		{
			ruleIDList.add(ruleid);
		    try {
				ConditionsExtractor.extractAllDataKeyWordSearch(sourceCodepath, file.getAbsolutePath(),ruleIDList,ruleRepVo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		    
		}
		else if(messageId!=null && !messageId.isEmpty())
		{
			Properties errorMessage=(Properties) request.getServletContext().getAttribute("errorMessage");
			List<String> propertyFiles=(List<String>) request.getServletContext().getAttribute("propertyFiles");
			List<String> constantFiles=(List<String>) request.getServletContext().getAttribute("constantFiles");
			Properties errtoRule=(Properties) request.getServletContext().getAttribute("errtoRule");
			
			System.out.println(errorMessage);
			if(errorMessage.containsKey(messageId))
			{
				System.out.println("Inside if block");
				Map<String, String> messageMap = new HashMap<String, String>();
				List<String> messageIdList=new ArrayList<>();
				messageIdList.add(errorMessage.getProperty(messageId));
				Map<String, String> constantsInputMap = new HashMap<String, String>();
				try 
				{
					MessageConstantExtractor.extractPropertyKeys(propertyFiles,
							messageIdList, messageMap);
					System.out.println("MessageMap Data :" + messageMap.toString());
					MessageConstantExtractor.extractConstants(constantsInputMap,
							constantFiles, messageMap, null, "D:\\Logs\\Output");
					System.out.println("ConstantsInputMap Map Data :"
							+ constantsInputMap.toString());
					if (constantsInputMap.size() == 0) {
						System.out
								.println("No Constants Are Loaded From Java File for this Input Message"
										+ messageIdList.toString());
					}
					
					ConditionsExtractor
							.extractAllDataMessagesBased(
									sourceCodepath,
									file.getAbsolutePath(), constantsInputMap, errorMessage,errtoRule,ruleRepVo );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			}
			else
			{
				System.out.println("Inside else block");
				response.setStatus(0);
			}
		}
		
		if(ValidatorUtil.validateNull(ruleRepVo))
		{
			ruleRepVo.setPatternPath(ruleRepVo.getPatternPath().substring(contextPath.length()+1));
		    ruleRepVo.setInvokeCodePath(ruleRepVo.getInvokeCodePath().substring(contextPath.length()+1));
		    ruleRepVo.setPojoPath(ruleRepVo.getPojoPath().substring(contextPath.length()+1));
		    
		    System.out.println("Pattern Path:::>"+ruleRepVo.getPatternPath());
		    System.out.println("POJO Path:::>"+ruleRepVo.getPojoPath());
		    System.out.println("Invoke Path:::>"+ruleRepVo.getInvokeCodePath());
		    
		    //Setting response
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    PrintWriter pw=response.getWriter();
		    Gson g=new Gson();
		    pw.write(g.toJson(ruleRepVo));
		    pw.flush();
		}
		else
		{
			response.setStatus(0);
		}
	}


}
