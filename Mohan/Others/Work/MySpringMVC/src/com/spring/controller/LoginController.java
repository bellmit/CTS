package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.util.MySpringInitializer;

@Controller
public class LoginController {

	public static Log log;
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView loginCheck()
	{
		MySpringInitializer myInit=new MySpringInitializer();
		log=myInit.getLogger(LoginController.class);
		myInit.loadProperties();		
		
		log.debug("LOG>>> LoginCheck");
		System.out.println("Method >>>>>>Login");
		return new ModelAndView("login", "login", "Welcome please Login");
	}
	
	@RequestMapping(value="/loginResponse",method=RequestMethod.POST)
	public ModelAndView verifyLogin(HttpServletRequest request)
	{
		System.out.println("Method >>>>>>Response");
		ModelAndView loginView=new ModelAndView();
		String user=request.getParameter("userId");
		if(user.equalsIgnoreCase("mohan"))
		{
			loginView.setViewName("jqyvalidation");
			loginView.addObject("header", "Jquery");
		}
		else
		{
			loginView.setViewName("login");
			loginView.addObject("error", "Invalid Login");
		}
		
		
		return loginView;
	}
	
}
