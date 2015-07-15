package com.test.portlet;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;

public class SearchResultBean {
	public Integer searchNum;

	public Integer getSearchNum() {
		Object object=FacesContext.getCurrentInstance().getExternalContext().getRequest();
		PortletRequest pRequest = (PortletRequest) object;	
		MyNumber myNumber=(MyNumber)pRequest.getPortletSession().getAttribute("MyNum2");
		return myNumber.getMyNumber();		
	}

	public void setSearchNum(Integer searchNum) {
		this.searchNum = searchNum;
	}
	
}
