package com.xerox.ghs.mmis.enterprise.common.security.common.model;

import java.util.List;

public class MenuItem {

	private String title;
	private String link;
	private String domClass;
	private String domId;
	private List<MenuItem> subMenus;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDomClass() {
		return domClass;
	}
	public void setDomClass(String domClass) {
		this.domClass = domClass;
	}
	public String getDomId() {
		return domId;
	}
	public void setDomId(String domId) {
		this.domId = domId;
	}
	public List<MenuItem> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<MenuItem> subMenus) {
		this.subMenus = subMenus;
	}
	
}
