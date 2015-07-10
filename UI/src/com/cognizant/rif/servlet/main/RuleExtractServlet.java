package com.cognizant.rif.servlet.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.cognizant.rif.constants.RIFInputConstants;

/**
 * Servlet implementation class RuleExtractServlet
 */
@WebServlet("/ruleExtract")
@MultipartConfig
public class RuleExtractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RuleExtractServlet() {
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
		String userName=(String) request.getParameter("userName");
		userName="396662";
		request.getSession().setAttribute("userName", userName);
		
		String contextPath=request.getServletContext().getRealPath("");
		File file=new File(contextPath.concat("\\"+RIFInputConstants.RULE_EXTRACTION_FOLDER+"\\").concat(userName));
		file.mkdirs();
		
		System.out.println("File Path"+file.getAbsolutePath());
	
		if(ServletFileUpload.isMultipartContent(request)){
	          try {
	              List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	            for(FileItem item : multiparts){
	                  if(!item.isFormField()){
	                      String name = new File(item.getName()).getName();
	                      System.out.println("name"+name);
	                     // item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
	                  }
	              }
	            //File uploaded successfully
	             request.setAttribute("message", "File Uploaded Successfully");
	          } catch (Exception ex) {

	             request.setAttribute("message", "File Upload Failed due to " + ex);
	          }         
	      }else{
	          request.setAttribute("message",
	                               "Sorry this Servlet only handles file upload request");
	          System.out.println("Not multpart");
	      }

	}

}
