package com.valuelist;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class MyValList implements ValueChangeListener {

	@Override
	public void processValueChange(ValueChangeEvent valueChangeEvent) throws AbortProcessingException {
		if("selLang".equals(valueChangeEvent.getComponent().getId())) {
			String langSelected=(String)valueChangeEvent.getNewValue();
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(langSelected));
			FacesContext.getCurrentInstance().renderResponse();
		}		 
	}

}
