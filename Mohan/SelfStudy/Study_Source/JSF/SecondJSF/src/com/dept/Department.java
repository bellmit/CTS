package com.dept;

public class Department {
	private Integer number;
	private String name;
	
	public Department(Integer number, String name) {
		super();
		this.number = number;
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
