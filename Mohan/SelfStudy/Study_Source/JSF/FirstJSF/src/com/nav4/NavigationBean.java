package com.nav4;

import java.util.Map;

import javax.faces.context.FacesContext;


public class NavigationBean {
	public String goToPage2() {
		System.out.println("TEST");
		Map p=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		System.out.println("TEST"+p.size());
		System.out.println("Map value >>>>"+p.get("test"));
		System.out.println("Map value >>>>"+p.get("test1"));
		return "success";
	}
	public String goToPage3() {
		return "success";
	}	
}
