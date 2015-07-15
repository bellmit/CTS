package com.jsf.Listener;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

import com.jsf.javabean.LanguageBean;

public class LanguageListener implements ValueChangeListener{

	@Override
	public void processValueChange(ValueChangeEvent e)
			throws AbortProcessingException {
		// TODO Auto-generated method stub
		System.out.println("Value change listener Class");
		 LanguageBean userData = (LanguageBean) FacesContext.getCurrentInstance().
			        getExternalContext().getSessionMap().get("languageBean"); 
		 	userData.setSelectLocale(new Locale(e.getNewValue().toString()));
			     userData.setSelectCountry(e.getNewValue().toString());
			     FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(e.getNewValue().toString()));
	}

	
}
