package com.cognizant.rif.servlet.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cognizant.javagrammar.JavaLexer;
import com.cognizant.javagrammar.JavaParser;
import com.cognizant.parser.ANTLR.listeners.RuleExtractionListener;
import com.cognizant.rif.constants.RIFInputConstants;
import com.cognizant.rif.formatter.JacobeFormatter;
import com.cognizant.rif.vo.RuleReportVO;
import com.google.gson.Gson;

@WebServlet("/UploadFile")
public class FileUpload extends HttpServlet {
        private static final long serialVersionUID = 1L;
        

        protected void doPost(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {
        	
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        RuleReportVO ruleRepVo = null;
        String contextPath=request.getServletContext().getRealPath("");
        File file=new File(contextPath.concat("\\"+RIFInputConstants.RULE_EXTRACTION_FOLDER+"\\").concat("396662"));
		file.mkdirs();

        // process only if its multipart content
        if (isMultipart) {
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                try {
                        // Parse the request
                        List<FileItem> multiparts = upload.parseRequest(request);

                        for (FileItem item : multiparts) {
                          if (!item.isFormField()) {
                             String name = new File(item.getName()).getName();
                             InputStream is=item.getInputStream();
                            // FileInputStream fis=(FileInputStream) is;
                             ANTLRInputStream input = new ANTLRInputStream(is);
                     		JavaLexer lexer = new JavaLexer(input);
                     		CommonTokenStream tokens = new CommonTokenStream(lexer);
                     		JavaParser parser = new JavaParser(tokens);
                     		ParseTree tree = parser.compilationUnit();
                     		ParseTreeWalker walker = new ParseTreeWalker();
                     		
                     		/*//To getMethod details need to modified for better approach
                     		Map<String,String> methodMap=new HashMap<String, String>();
                     		ExtractMethodNamesListener ext =new ExtractMethodNamesListener(tokens,methodMap);
                     		walker.walk(ext, tree);	
                     	*/	
                     		ruleRepVo=new RuleReportVO();
                     		RuleExtractionListener extractor1 = new RuleExtractionListener(file.getAbsolutePath()+"//",RIFInputConstants.SOURCE_CODE_ROOT_DIR,
                     				tokens,ruleRepVo);
                     		walker.walk(extractor1, tree);
                     		
                     		System.out.println("RuleExtraction End");
                             /*InputStreamReader isr=new InputStreamReader(is);
                             BufferedReader br=new BufferedReader(isr);
                             String lineStr=br.readLine();
                             while(lineStr!=null)
                             {
                            	 System.out.println(lineStr);
                            	 // item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                            	 System.out.println("Name:"+name);
                            	 lineStr=br.readLine();
                             }*/
                          }
                        }
                } 
                catch (Exception e) 
                {
                  System.out.println("File upload failed");
                  e.printStackTrace();
                }
        }
        
        if(validateNull(ruleRepVo))
		{
			//ruleRepVo.setPatternPath(ruleRepVo.getPatternPath().substring(contextPath.length()+1));
		    String pojoFile=ruleRepVo.getPojoPath();
        	ruleRepVo.setInvokeCodePath(ruleRepVo.getInvokeCodePath().substring(contextPath.length()+1));
		    ruleRepVo.setPojoPath(ruleRepVo.getPojoPath().substring(contextPath.length()+1));
		    
		    //Generating Junit
		    //System.out.println("POJO Path:::>"+pojoFile);
		    //JunitGenerator jGen=new JunitGenerator();
		    //String junitPath=jGen.generate(pojoFile);
		    //ruleRepVo.setJunitPath(junitPath.substring(contextPath.length()+1));
		    if(!ruleRepVo.isRuleBatchMode())
		    ruleRepVo.setJunitPath(ruleRepVo.getJunitPath().substring(contextPath.length()+1));
		    
		    System.out.println("POJO Path:::>"+ruleRepVo.getPojoPath());
		    System.out.println("Invoke Path:::>"+ruleRepVo.getInvokeCodePath());
		    System.out.println("Junit Path:::>"+ruleRepVo.getJunitPath());
		    
		    //Formatting Code
		    formatCode(pojoFile);
		    
		    
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
        
        private boolean validateNull(Object ruleRepVo) {
    		// TODO Auto-generated method stub
        	System.out.println(ruleRepVo);
    		if(ruleRepVo instanceof RuleReportVO)
    		{
    			RuleReportVO r=(RuleReportVO) ruleRepVo;
    			if(ruleRepVo!=null && r.getInvokeCodePath()!=null
    					&& r.getPojoPath()!=null)
    			{
    				return true;
    			}
    		}
    		return false;
    	}
        
        private void formatCode(String sourceCodePath) {
			try{
				JacobeFormatter formatter = JacobeFormatter.getInstance();
				formatter.formatGeneratedCode(sourceCodePath);
				formatter.deleteJacobeFiles(new File(sourceCodePath));
			} catch(Exception e){
				e.printStackTrace();
			}
			
		}
}