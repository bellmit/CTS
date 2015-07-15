package com.actionlist;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

public class MyActionListener {
	private int buttonsClickedCounter;
	private boolean disableButton;
	
	public boolean isDisableButton() {
		return disableButton;
	}

	public void setDisableButton(boolean disableButton) {
		this.disableButton = disableButton;
	}
	
	public void clickButton(ActionEvent actionEvent) {		
		Iterator<UIComponent> iterator=actionEvent.getComponent().getFacetsAndChildren();
		while(iterator.hasNext()) {
			UIComponent uiComponent=iterator.next();
			System.err.println(uiComponent.getId()); 
		}
		buttonsClickedCounter++;		
		System.err.println("Button Clicked->" + actionEvent.getComponent().getId());
		System.err.println("Counter->" + buttonsClickedCounter);
		
	}
	
	public void activateButton(ActionEvent actionEvent) {
		disableButton=true;
	}
	
	public void textActionListener(ActionEvent actionEvent) {
		System.err.println(actionEvent.getComponent().getId()); 
	}
	
}
