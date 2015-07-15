package com.actionlist;

import java.util.LinkedHashMap;
import java.util.Map;

public class Login {
	private String userName;
	private String passWord;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		System.err.println("SET USERNAME");
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		System.err.println("SET PASSWORD");
		this.passWord = passWord;
	}
	public String processLogin() {
		return "success";
	}
	public String cancelLogin() {
		System.err.println("CANCEL");
		return "success";
	}
	public Map<String,String> getLanguage() {
		Map<String, String> map=new LinkedHashMap();	
		map.put("English","en");
		map.put("Spanish","es");				
		return map;
	}
}
