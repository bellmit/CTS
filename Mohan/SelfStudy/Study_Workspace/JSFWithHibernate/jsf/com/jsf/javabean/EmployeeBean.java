package com.jsf.javabean;

import java.io.Serializable;

public class EmployeeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8334449688324747074L;
	private Integer empId;
	private String empname;
	private String designation;
	private Integer landlineNo;
	private Integer mobileNo;
	private String address;
	private String fav_color;
	private String fav_book;
	private EmployeeTransport empTransport;
	
	public Integer getLandlineNo() {
		return landlineNo;
	}
	public void setLandlineNo(Integer landlineNo) {
		this.landlineNo = landlineNo;
	}
	public Integer getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Integer mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	
	public EmployeeTransport getEmpTransport() {
		return empTransport;
	}
	public void setEmpTransport(EmployeeTransport empTransport) {
		this.empTransport = empTransport;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFav_color() {
		return fav_color;
	}
	public void setFav_color(String fav_color) {
		this.fav_color = fav_color;
	}
	public String getFav_book() {
		return fav_book;
	}
	public void setFav_book(String fav_book) {
		this.fav_book = fav_book;
	}
	
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}
