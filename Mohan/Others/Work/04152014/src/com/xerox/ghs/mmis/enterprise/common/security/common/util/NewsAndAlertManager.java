package com.xerox.ghs.mmis.enterprise.common.security.common.util;

import java.util.ArrayList;
import java.util.List;
import com.xerox.ghs.mmis.enterprise.common.security.common.model.NewsItem;

/**
 * This Method will retrive the Menu list for Members Page
 * @return Listof MenuItems 
 */
public class NewsAndAlertManager {
	
	/**
	 * This Method will retrive the Dynamic list for News And Alerts to show in pages
	 * @return Listof NewsItems 
	 */
	public static List<NewsItem> getLatestNewsAndAlert(){
		
		List<NewsItem> listOfNews = new ArrayList<NewsItem>();
		
		NewsItem n1 = new NewsItem();
		n1.setTitle("News Header 1");
		n1.setContent("News Content1 .News Content1");
		
		NewsItem n2 = new NewsItem();
		n2.setTitle("News Header 2");
		n2.setContent("News Content2 .News Content2");
		
		
		listOfNews.add(n1);
		listOfNews.add(n2);
		
		return listOfNews;
	}

}
