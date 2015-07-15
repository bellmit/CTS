package com.dept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Departments {
	private List<Department> departments;
	private Map<String, Department> departmentsByLocation;
	
	public List<Department> getDepartments() {
		if(departments==null) {
			departments=loadData();
		}
		return departments;
	}
	
	public Map<String, Department> getDepartmentsByLocation() {
		if(departmentsByLocation==null) {
			departmentsByLocation=loadData2();
		}
		return departmentsByLocation;
	}

	public void setDepartmentsByLocation(
			Map<String, Department> departmentsByLocation) {
		this.departmentsByLocation = departmentsByLocation;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}	

	private static List<Department> loadData() {
		List<Department> departments=new ArrayList<Department>();
		departments.add(new Department(1000, "Sales"));
		departments.add(new Department(1001, "Finance"));
		return departments;
	}
	
	private static Map<String, Department> loadData2() {
		Map<String, Department> map=new HashMap<String, Department>();
		map.put("Chennai", new Department(1000, "Sales"));
		map.put("Delhi", new Department(1001, "Finance"));
		return map;
	}
}
