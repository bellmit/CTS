package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@RequestMapping("/hello")
	public ModelAndView sayhello()
	{
		System.out.println("Method >>>>>>sayhello");
		return new ModelAndView("hello", "hello", "Hello Spring");
	}
}
