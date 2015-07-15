package com.actionlist;

import java.util.HashMap;
import java.util.Map;

public class NavigationBean {
	
	
	private Integer inputNumber;
	private String selectedName;
	private Map<String,String> nameMap; 
	
	public Map<String,String> getNameMap() {
		if(nameMap==null) {
			nameMap=new HashMap<String, String>();
			nameMap.put("A", "A");
			nameMap.put("B", "B");
		}
		return nameMap;
	}

	public void setNameMap(Map<String,String> map) {
		this.nameMap = map;
	}

	public String getSelectedName() {
		return selectedName;
	}

	public void setSelectedName(String selectedName) {
		System.err.println("SEL NAME->" + selectedName);
		this.selectedName = selectedName;
	}
	
	public Integer getInputNumber() {
		System.err.println("Getter Called");
		return inputNumber;
	}

	public void setInputNumber(Integer inputNumber) {
		System.err.println("Setter Called");
		this.inputNumber = inputNumber;
	}

	public String nextPage() {
		System.err.println("Next Page Called->" + inputNumber);
		if(selectedName==null) {
			System.err.println("Selected Name is NULL");
		} else {
			System.err.println("Selected Name is " + selectedName);
		}
		return "success";
	}
}
