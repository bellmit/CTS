package com.dt;

public class Department {
	private Integer number;
	private String name;
	private Integer profit;
	
	public Department() {
		super();
	}
	public Department(Integer number, String name, Integer profit) {
		this.number = number;
		this.name = name;
		this.profit = profit;
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
	public Integer getProfit() {
		return profit;
	}
	public void setProfit(Integer profit) {
		this.profit = profit;
	}	
}
