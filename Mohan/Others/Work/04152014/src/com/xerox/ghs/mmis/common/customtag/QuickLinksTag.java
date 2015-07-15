package com.xerox.ghs.mmis.common.customtag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class QuickLinksTag extends TagSupport{
	
	private final static Logger LOGGER = Logger.getLogger(QuickLinksTag.class .getName());
	
	private List<String> quickLinksText;
	private List<String> quickLinks;
	private String quickLinksId;
	
	public List<String> getQuickLinksText() {
		return quickLinksText;
	}
	public void setQuickLinksText(List<String> quickLinksText) {
		this.quickLinksText = quickLinksText;
	}
	public List<String> getQuickLinks() {
		return quickLinks;
	}
	public void setQuickLinks(List<String> quickLinks) {
		this.quickLinks = quickLinks;
	}
	
	
	
	@Override    
	public int doStartTag() throws JspException { 
		try {            
			
			JspWriter out = pageContext.getOut();
			out.println("<div id='"+quickLinksId+"'><ul>");
			out.println("<div class='linkMenu'>");
			out.println("<ul>");
			int counter = 0;
			LOGGER.info(" quickLinksText :"+quickLinksText);
			LOGGER.info(" quickLinks :"+quickLinks);
			for (Iterator iterator = quickLinksText.iterator(); iterator.hasNext();) {
				String quickLinkStr = (String) iterator.next();
				String link = quickLinks.get(counter);
				counter++;
				out.println("<li>");
				out.println("<a href='"+link+"'>"+quickLinkStr+"</a>");
				out.println("</li>");
			}
			out.println("</ul>");
			out.println("</div>");
			out.println("</div>");
			
		} catch (IOException e) {            
			LOGGER.info("Exception:"+e.getMessage());        
		}        
		return SKIP_BODY;    
	}
	public String getQuickLinksId() {
		return quickLinksId;
	}
	public void setQuickLinksId(String quickLinksId) {
		this.quickLinksId = quickLinksId;
	}
}

