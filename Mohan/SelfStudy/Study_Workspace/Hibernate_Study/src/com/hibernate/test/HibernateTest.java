package com.hibernate.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.hibernate.dao.HibernateMain;
import com.hibernate.dao.SessionMethod;
import com.hibernate.javaBean.Department;
import com.hibernate.javaBean.Employee;
import com.hibernate.javaBean.EmployeeTransport;

public class HibernateTest {

	@Test
	public void departmentSearch() {
		HibernateMain hm=new HibernateMain();
		//hm.getbyId("Department", 1);
		assertEquals("CTS",hm.getbyId("Department", 2));
		
		//fail("Not yet implemented");
	}
	
	@Test
	
	public void departmentInsert() {
		HibernateMain hm=new HibernateMain();
		Department dept=new Department();
		//dept.setId(1);
		dept.setName("CTS");
		assertEquals(true,hm.Insert(dept));
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void departmentDelete()
	{
		HibernateMain hm=new HibernateMain();
		Department dept=new Department();
		dept=hm.getbyDeptId("Department", 2);
		hm.deleteDepartment(dept);
	}
	
	
	@Test
	public void employeeInsert()
	{
		HibernateMain hm=new HibernateMain();
		Employee emp=new Employee();
		EmployeeTransport empTrans=new EmployeeTransport();
		empTrans.setVehicleId(123);
		empTrans.setVehicleType("Car");
		Department d=hm.getbyDeptId("Department", 1);
		emp.setName("Emp1");
		emp.setAddress("EmpAddress");
		
		//emp.setEmpTransport(empTrans);
		emp.setDeptId(d);
		hm.InsertEmployee(emp);
	}
	
	@Test
	public void getDepartment()
	{
		HibernateMain hm=new HibernateMain();
		Department d=hm.getDepartment(1);
		Set<Employee> semp=d.getEmployee();
		List<Employee> lemp=d.getEmployees();
		//System.out.println(semp);
		System.out.println("Set Emp"+semp.size());
		System.out.println("List Emp"+lemp.size());
		
	}
	
	@Test
	public void getEmployee()
	{
		HibernateMain hm=new HibernateMain();
		//Employee d=hm.getEmployee(1);
		hm.insertAll();
	}
	
	@Test
	public void testSessionMethods()
	{
		SessionMethod sm=new SessionMethod();
		sm.sessionMethods();
	}

}
