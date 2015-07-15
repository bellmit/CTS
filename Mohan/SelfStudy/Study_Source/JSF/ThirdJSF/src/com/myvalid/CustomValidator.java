package com.myvalid;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class CustomValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		Integer integer=(Integer)object;
		if(integer.intValue()>100) {
			throw new ValidatorException(new FacesMessage("Too Much"));			 
		}		 
	}

}
