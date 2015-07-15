
/*
 * Created on Oct 22, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acs.enterprise.common.program.contactmanagement.view.bean.AttachmentDataBean;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.ibm.websphere.cache.DistributedMap;
import com.ibm.ws.cache.spi.DistributedMapFactory;

/**
 * @author pradeepc
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileDownLoadServlet extends HttpServlet{


    /** Instance of the logger */

    private transient EnterpriseLogger log = EnterpriseLogFactory
            .getLogger(getClass().getName());

    /** Instance of the cache */
    private static DistributedMap cache = null;

    private static DistributedMap fileCache = null;

   /**
    * Calls the init method of servlet.
    * @param config 
    * Takes ServletConfig as param
    * @throws ServletException 
    * Throws ServletException in case of error .
    */
    public void init(ServletConfig config)
            throws ServletException
    {
        log.debug("Inside Init=======");
        cache = DistributedMapFactory.getMap("statistics");
        fileCache = DistributedMapFactory.getMap("fileDownLoad");
    }

   
   /**
    * Calls the DoPost method .
    * @param  req 
    * Takes HttpServletRequest as param
    * @param res
    * Takes HttpServletResponse as param
    * @throws ServletException 
    * Throws ServletException as exp
    * @throws IOException
    * Throws IOException 
    */
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        log.debug("In do get of FileDownLoadServlet");
        doPost(req, res);
    }

    /**
     * Performs the functionality and calls delegate .
     * @param  req 
     * Takes HttpServletRequest as param
     * @param res
     * Takes HttpServletResponse as param
     * @throws ServletException 
     * Throws ServletException as exp
     * @throws IOException
     * Throws IOException 
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
    	
    	log.info("In do post of FileDownLoadServlet");
        HttpSession session = req.getSession(false);
        AttachmentDataBean attachmentDataBean = (AttachmentDataBean) cache
        .get(ContactManagementHelper.getKey("ATTACHMENT", session));
        log.info("attachmentDataBean.getAttachmentVO()"+attachmentDataBean.getAttachmentVO().getFile1());
        byte[] file = attachmentDataBean.getAttachmentVO().getFile1();
        String fileName = attachmentDataBean.getAttachmentVO().getFileName();
        log.info("filename===="+attachmentDataBean.getAttachmentVO().getFileName());
        cache.remove(ContactManagementHelper.getKey("ATTACHMENT", session));
        try
        {
            downloadStream(res,file,fileName);
            fileCache.put("ATTACHDWNLD","ATTACHDWNLD");
    		log.info("Preview Status changed to Generated...");
        }
        catch (Exception e)
        {
            log.debug("message :" + e.getMessage());
            log.debug("ERROR WHILE DOWNLOADING STREAM = " + e.getMessage());
            e.printStackTrace();
            handleException(req, res);
            return;
        }
    }
    
    /**
     * This method converts the Byte array to bytes and write the servlet output stream. 
     * @param byteBuf
     * Takes byteBuf as param.
     * @param response
     * Takes response as param
     * @throws Exception
     * Throws Exception as exception.
     */
    public void downloadStream(HttpServletResponse response,byte[] file, String fileName)
            throws Exception
    {
        log.debug("Inside downloadStream-------->"+fileName);
        	      
		log.debug("Character Encoding = " + response.getCharacterEncoding());
        response.setContentType("text/html");
        response.setContentLength(file.length);
        response.setHeader("Content-Disposition","inline;filename=\""
        			+fileName+ "\"");
        ServletOutputStream out = response.getOutputStream();
        out.write(file);

        out.flush();

    }
    
    
    /**
     * Used to forward control back to originating page
     *
     */
    private void handleException(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
	    /*
	     * code to forward back to the originating page
	     */
    	log.info("Inside handleException()");
    	StringBuffer strBuffer = new StringBuffer ();
    	strBuffer.append("<HTML><HEAD><TITLE>Failed displaying PDF</TITLE></HEAD>");
    	strBuffer.append("<BODY><DIV align='center'><FONT color='red'>");
    	strBuffer.append("There was an error processing the request. ");
    	strBuffer.append("Please retry the transaction.</FONT></DIV></BODY></HTML>");
    	
    	PrintWriter writer = res.getWriter();
    	writer.write(strBuffer.toString());
    }

}
