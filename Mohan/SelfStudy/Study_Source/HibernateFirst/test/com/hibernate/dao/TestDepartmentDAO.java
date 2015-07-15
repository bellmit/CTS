package com.hibernate.dao;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate.entity.Department;
import com.hibernate.entity.Employee;
import com.hibernate.entity.EmployeeTransport;

public class TestDepartmentDAO {

	private DepartmentDAO departmentDAO;
	
	@Before
	public void setUp() throws Exception {
		departmentDAO=new DepartmentDAO();
	}

	@After
	public void tearDown() throws Exception {
		departmentDAO=null;
	}

	@Test
	public void testInsertDepartment() {
		Department department=new Department("Sales","Chennai");
		departmentDAO.insertDepartment(department);
	}

	@Test
	public void testDeleteDepartment() {
		departmentDAO.deleteDepartment(7);
	}
	
	@Test
	public void testUpdateDepartment() {
		Department department=new Department("Updated Name", "Updated Location");
		department.setDeptNo(6);
		departmentDAO.updateDepartment(department);
	}
	
	@Test
	public void testGetDepartmentById() {
		Department department=departmentDAO.getDepartmentById(1);
		assertTrue("Sales".equals(department.getDeptName()));
	}
	
	@Test
	public void testInsertDepartmentAndEmployees() {
		Department department=new Department("Finance","Delhi");
		Employee employee=new Employee(department, "Test1");
		department.getEmployees().add(employee);
		employee=new Employee(department, "Test2");
		department.getEmployees().add(employee);
		departmentDAO.insertDepartment(department);
	}
	
	@Test
	public void testInsertAll() {
		Department department=new Department("Finance","Delhi");
		Employee employee=new Employee(department, "Test");
		employee.setColor("Black");
		employee.setFood("Pasta");
		EmployeeTransport employeeTransport=new EmployeeTransport(employee, "ABC");
		employee.setEmployeeTransport(employeeTransport);
		department.getEmployees().add(employee);
		departmentDAO.insertDepartment(department);
	}
	
}
