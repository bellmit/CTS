package com.hibernate.javaBean;

public class Employee {

	private Integer id;
	private String name;
	private String address;
	private Department deptId;
	private String color;
	private String book;
	private String subject;
	private EmployeeTransport empTransport;

		public EmployeeTransport getEmpTransport() {
		return empTransport;
	}
	public void setEmpTransport(EmployeeTransport empTransport) {
		this.empTransport = empTransport;
	}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getBook() {
			return book;
		}
		public void setBook(String book) {
			this.book = book;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Department getDeptId() {
		return deptId;
	}
	public void setDeptId(Department deptId) {
		this.deptId = deptId;
	}
	
	
	
}
