package com.hibernate.test.entity;


public class Employee {

	private Integer empId;
	private String empName;
	private String empAddress;
	private String empDesignation;
	private EmployeeTransport employeeTransport;
	private Contact contact;
	
	public String getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	private String food;
	private String color;
	
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public EmployeeTransport getEmployeeTransport() {
		return employeeTransport;
	}
	public void setEmployeeTransport(EmployeeTransport employeeTransport) {
		this.employeeTransport = employeeTransport;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
		
	
}
