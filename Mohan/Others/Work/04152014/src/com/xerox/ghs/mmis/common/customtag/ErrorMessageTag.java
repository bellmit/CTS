package com.xerox.ghs.mmis.common.customtag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class ErrorMessageTag  extends TagSupport   {

	private final static Logger LOGGER = Logger.getLogger(ErrorMessageTag.class .getName());
	private String errorCode;    
    private String errorMessage;
    private String id;
    private List<String> errorList;

	@Override    
	public int doStartTag() throws JspException { 
		try {            
			            
			JspWriter out = pageContext.getOut();             
			//out.println((new Date()).toString());
			if(errorList!=null){
				out.println("<div id='"+id+"'><ul>");
				for (Iterator iterator = errorList.iterator(); iterator
						.hasNext();) {
					String errorMessage = (String ) iterator.next();
					out.println("<li>"+errorMessage+"</li>");	
				}
				out.println("</ul></div>");
				
			}else{
				out.println("<div id='"+id+"'><br>"+errorCode+":"+errorMessage+"</div>");	
			}
			
			
		} catch (IOException e) {            
				LOGGER.info("Exception"+e.getMessage());        
		}        
		return SKIP_BODY;    
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}



	

}
