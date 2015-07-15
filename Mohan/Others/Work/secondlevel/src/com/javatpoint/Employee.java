package com.javatpoint;

public class Employee {
private int id;
private String name;
private String designation;


public Employee() {
	super();
}

public Employee(String name, String designation) {
	super();
	this.name = name;
	this.designation = designation;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}



}
