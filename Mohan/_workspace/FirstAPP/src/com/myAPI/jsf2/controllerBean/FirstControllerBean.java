package com.myAPI.jsf2.controllerBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="firstControllerBean")
@SessionScoped
public class FirstControllerBean implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	public FirstControllerBean() {
		// TODO Auto-generated constructor stub
		this.name="Mohan First Managed bean";
	}
	
	
	private String name;
 
	public String getName() {
		
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String searchYoutube()
	{
		System.out.println("Called firstControllerBean searchYoutube method");
		return "success";
	}
	
}