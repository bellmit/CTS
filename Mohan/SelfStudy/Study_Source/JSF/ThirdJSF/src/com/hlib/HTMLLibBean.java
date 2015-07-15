package com.hlib;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class HTMLLibBean {
	private String inputText;
	private String selected1;
	private String selected2;
	private String[] checkbox1;
	private String checkbox2;
	
	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		System.err.println("Value Set->" + inputText);
		this.inputText = inputText;
	}
	
	public void inputTextValueChange(ValueChangeEvent valueChangeEvent) {
		System.err.println("OLD->" + (String)valueChangeEvent.getOldValue()); 
		System.err.println("NEW->" + (String)valueChangeEvent.getNewValue());
	}
	
	public List<SelectItem> getSelect1() {
		List<SelectItem> selectItems=new ArrayList<SelectItem>();
		selectItems.add(new SelectItem("A", "Apple"));
		selectItems.add(new SelectItem("B", "Ball"));
		return selectItems;
	}
	
	public List<SelectItem> getSelect2() {
		List<SelectItem> selectItems=new ArrayList<SelectItem>();
		selectItems.add(new SelectItem("Apple"));
		selectItems.add(new SelectItem("Ball"));
		return selectItems;
	}	

	public List<SelectItem> getCheckboxes1() {
		List<SelectItem> selectItems=new ArrayList<SelectItem>();
		selectItems.add(new SelectItem("A","Apple"));
		selectItems.add(new SelectItem("B","Ball"));
		return selectItems;
	}
	
	public String getSelected1() {
		return selected1;
	}

	public void setSelected1(String selected1) {
		System.err.println("Selected1->" + selected1);
		this.selected1 = selected1;
	}

	public String getSelected2() {
		return selected2;
	}

	public void setSelected2(String selected2) {
		System.err.println("Selected2->" + selected2);
		this.selected2 = selected2;
	}

	public String[] getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(String[] checkbox1) {
		for (String string : checkbox1) {
			System.err.println("CheckBoxes Selected->" + string);	
		}
		
		this.checkbox1 = checkbox1;
	}

	public String getCheckbox2() {
		return checkbox2;
	}

	public void setCheckbox2(String checkbox2) {		
		this.checkbox2 = checkbox2;
	}
	
}
