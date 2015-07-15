package com.login;

public class LoginBean {
	private boolean invalidLength=false; 
	private String userName;
	private String passWord;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		if(userName==null || userName.length()==0) {
			invalidLength=true;
		}
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		if(passWord==null || passWord.length()==0) {
			invalidLength=true;
		}
		this.passWord = passWord;
	}
	public String login() {
		if(invalidLength) {
			return "failure";
		}
		if(!userName.equals("test") || !passWord.equals("test")) {
			return "failure";
		}
		return "success";
	}
}
