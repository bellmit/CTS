package com.actionlist;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class MyActionListener2 implements ActionListener {

	@Override
	public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
		System.err.println("MyActionListener2 Button Clicked->" + actionEvent.getComponent().getId());
	}
	
}
