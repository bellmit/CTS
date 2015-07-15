package com.jsf.javabean;

import java.util.Locale;

public class LanguageBean {

	private String selectLang;
	private String selectCountry;
	private Locale selectLocale;
	
	public LanguageBean()
	{		
		selectLocale=Locale.ENGLISH;
		System.out.println(selectLocale.getDisplayLanguage());
	}
	
	public Locale getSelectLocale() {
		//selectLocale=Locale.ENGLISH;
		return selectLocale;
	}

	public void setSelectLocale(Locale selectLocale) {
		this.selectLocale = selectLocale;
	}

	
	public String getSelectLang() {
		return selectLang;
	}
	public void setSelectLang(String selectLang) {
		this.selectLang = selectLang;
	}
	public String getSelectCountry() {
		return selectCountry;
	}
	public void setSelectCountry(String selectCountry) {
		this.selectCountry = selectCountry;
	}
}
