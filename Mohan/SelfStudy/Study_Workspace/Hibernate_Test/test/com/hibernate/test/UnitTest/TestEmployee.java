/**
 * 
 */
package com.hibernate.test.UnitTest;

import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate.test.dao.EmployeeDao;
import com.hibernate.test.dao.PersonDao;
import com.hibernate.test.entity.Contact;
import com.hibernate.test.entity.Employee;
import com.hibernate.test.entity.EmployeeTransport;
import com.hibernate.test.entity.Person;

/**
 * @author 396662
 *
 */
public class TestEmployee {

	private EmployeeDao empDAO;
	private PersonDao personDAO;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		empDAO=new EmployeeDao();
		personDAO=new PersonDao();
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
	public void testInsertEmployee()
	{
		Employee emp=new Employee();
		emp.setEmpName("TEST");
		emp.setEmpAddress("TEST Address");
		emp.setColor("orange");
		emp.setFood("rice");
		Contact contact=new Contact();
		contact.setLandLine(951111);
		contact.setMobile(212121);
		emp.setContact(contact);
		empDAO.insertEmployee(emp);
	}
	
	@Test
	public void testInsertEmployeeOnetoOne()
	{
		Employee emp=new Employee();
		emp.setEmpName("TEST");
		emp.setEmpAddress("TEST Address");
		EmployeeTransport empTransport=new EmployeeTransport();
		//empTransport.setVehicleId(1);
		empTransport.setVehicletype("TRUCK");
		empTransport.setEmployee(emp);
		emp.setEmployeeTransport(empTransport);
		Contact contact=new Contact();
		contact.setLandLine(951111);
		contact.setMobile(212121);
		emp.setContact(contact);
		empDAO.insertEmployee(emp);
	}
	
	@Test
	public void testInsertEmployeeTransport()
	{
		EmployeeTransport empTransport=new EmployeeTransport();
		empTransport.setVehicleId(1);
		empTransport.setVehicletype("Car");
		
		empDAO.insertEmployeeTransport(empTransport);
	}
	
	@Test
	public void testPerson()
	{
		Person per=new Person();
		per.setFirstName("Mohan");
		per.setLastName("P");
		per.setAge(23);
		per.setDob(new Date(1988, 00, 23));
		personDAO.insertPerson(per);
	}
	@Test
	public void tesPersonVersion()
	{
		//Person per=personDAO.getPerson("Mohan","P");
		//per.setFirstName("Mohan2");
		personDAO.updatePerson();
		
	}
	
	@Test
	public void testgetPerson()
	{
		personDAO.getPerson("Mohan1", "P");
	}
}
