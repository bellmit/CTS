package com.cognizant.rif.servlet.init;

import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value="/controller",loadOnStartup=1,asyncSupported=true)
public class RIFInitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext sc=config.getServletContext();
		Properties errtoRule=(Properties) sc.getAttribute("errtoRule");
		List<String> prop=(List<String>) sc.getAttribute("propertyFiles");
		List<String> constant=(List<String>) sc.getAttribute("constantFiles");
		
		System.out.println("ErrorToRule Map==>"+errtoRule.size());
		System.out.println("propertyFiles ==>"+prop.size());
		System.out.println("constantFiles ==>"+constant.size());
		
		super.init(config);
	}
}
