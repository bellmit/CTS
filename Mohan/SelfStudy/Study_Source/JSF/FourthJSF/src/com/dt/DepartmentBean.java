package com.dt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepartmentBean {
	
	private List<Department> departments;
	private Integer total;
	private Date dateSelected;
	private String tab1Data;
	private String tab2Data;
	
	public List<Department> getDepartments() {
		if(departments==null) {
			departments=new ArrayList<Department>();
			departments.add(new Department(1000, "Sales",100));
			departments.add(new Department(1001, "Finance",200));
		}	
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Integer getTotal() {
		if(total==null) {
			total=300;
		}
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Date getDateSelected() {
		return dateSelected;
	}

	public void setDateSelected(Date dateSelected) {
		System.err.println("Date Selected->" + dateSelected);
		this.dateSelected = dateSelected;
	}

	public String getTab1Data() {
		if(tab1Data==null) {
			tab1Data="Tab 1 Data";
		}
		return tab1Data;
	}

	public void setTab1Data(String tab1Data) {
		this.tab1Data = tab1Data;
	}

	public String getTab2Data() {
		if(tab2Data==null) {
			tab2Data="Tab 2 Data";
		}		
		return tab2Data;
	}

	public void setTab2Data(String tab2Data) {
		this.tab2Data = tab2Data;
	}

}
