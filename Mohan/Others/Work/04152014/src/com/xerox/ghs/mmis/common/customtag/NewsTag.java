package com.xerox.ghs.mmis.common.customtag;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class NewsTag extends TagSupport{
	
	private final static Logger LOGGER = Logger.getLogger(NewsTag.class .getName());
	
	private String newsId;
	private String news;
	
	
	public String getNewsId() {
		return newsId;
	}


	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}


	public String getNews() {
		return news;
	}


	public void setNews(String news) {
		this.news = news;
	}


	@Override    
	public int doStartTag() throws JspException { 
		try {            
			
			JspWriter out = pageContext.getOut();
			out.println("<div id='"+newsId+"'>");
			//out.println("<fieldset>");
			//out.println("<legend>News</legend>");
			out.println("<div class='newsMenu'>");
			out.println(news);
			out.println("</div>");
			out.println("</div>");
		} catch (IOException e) {            
			LOGGER.info("Exception"+e.getMessage());        
		}        
		return SKIP_BODY;    
	}

}
