package webServices.com.soap.api.Employee;

import java.util.ArrayList;
import java.util.List;

import com.hibernate.entity.Employee;

public class EmployeeDetails {
	
	private Employee emp;
	
	public Employee[] getAllEmployees()
	{
		List<Employee> l=new ArrayList<Employee>();
		return (Employee[]) l.toArray();
	}
	
	public Employee getEmployee(int empId)
	{
		emp=new Employee();
		return emp;
	}

}
