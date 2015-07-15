package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Media;

import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.model.User;

@Controller
public class JqueryController {

	@RequestMapping(value="/getAjaxData")
	@ResponseBody
	public Map<String,List<User>> getAjaxCall()
	{
		System.out.println("JqueryCtlr");
		Map<String, List<User>> mp=new HashMap<String, List<User>>();
		List l;
		l=getUserList();
		mp.put("Name", l);
		return mp;
	}
	
	@RequestMapping(value="/getAjaxDataGet")
	
	@ResponseBody
	public String getAjaxCallGET(@RequestParam("name")String name)
	{
		System.out.println("JqueryCtlr");
		Map<String, List<User>> mp=new HashMap<String, List<User>>();
		System.out.println("Name:::>"+name);
		List l;
		l=getUserList();
		mp.put("Name", l);
		return "Get Method Call";
	}
	
	@RequestMapping(value="/getAjaxDataPost",produces={"application/xml"})
	
	@ResponseBody
	public String getAjaxDataPost()
	{
		System.out.println("JqueryCtlr POST Method");
		Map<String, List<User>> mp=new HashMap<String, List<User>>();
		//System.out.println("Name:::>"+name);
		List l;
		l=getUserList();
		mp.put("Name", l);
		return "<Name>Post method Call</Name>";
	}

	private List<User> getUserList() {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUserName("Mohan");
		user.setDesgination("A");
		user.setProjectName("MT");
		user.setIsUser("true");
		user.setPassword("");
		
		user.setSpecialization("Java");
		
		List l=new ArrayList<User>();
		l.add(user);
		user=new User();
		user.setUserName("Mohan");
		user.setDesgination("A");
		user.setProjectName("MT");
		user.setIsUser("true");
		user.setPassword("");
		user.setSpecialization("Java");
		l.add(user);
		return l;
	}
}
