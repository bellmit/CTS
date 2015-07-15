package com.test.portlet;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;

public class SearchBean {
	private Integer searchNumber;

	public Integer getSearchNumber() {
		return searchNumber;
	}
	public void setSearchNumber(Integer searchNumber) {
		this.searchNumber = searchNumber;
	}
	public void doSearch() {
		if(searchNumber>10) {
			Object object=FacesContext.getCurrentInstance().getExternalContext().getRequest();
			PortletRequest pRequest = (PortletRequest) object;			
			pRequest.setAttribute("myNum", new MyNumber(searchNumber));			
		}
		System.err.println("Action Method Called->" + searchNumber);
	}
	
}
