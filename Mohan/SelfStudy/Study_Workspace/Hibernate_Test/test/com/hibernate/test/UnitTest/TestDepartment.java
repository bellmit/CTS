/**
 * 
 */
package com.hibernate.test.UnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate.test.dao.DepartmentDao;
import com.hibernate.test.entity.Department;

/**
 * @author 396662
 *
 */
public class TestDepartment {

	private DepartmentDao deptDAO;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		deptDAO=new DepartmentDao();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInsertDepartment()
	{
		Department dept=new Department();
		dept.setDeptName("CTS1");
		deptDAO.insertDepartment(dept);
	}
	
	@Test
	public void testUpdateDepartment()
	{
		deptDAO.updateDept(1);
	}
	
	@Test
	public void testUpdateDepartment1()
	{
		deptDAO.updateDept1(1);
	}
}
