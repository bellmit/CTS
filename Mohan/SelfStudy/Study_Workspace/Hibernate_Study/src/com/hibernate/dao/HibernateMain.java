package com.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.javaBean.Department;
import com.hibernate.javaBean.Employee;
import com.hibernate.javaBean.EmployeeTransport;

public class HibernateMain {
	
	
	
	public String getbyId(String type,int id)
	{
		if("department".equalsIgnoreCase(type))
		{
			Session s=getSessionWithConfig();
			s.beginTransaction();
			
			Department dept=(Department)s.get(Department.class,id);
			System.out.println("Department"+dept.getId() +">>>>"+dept.getName());
			return dept.getName();
			//Insert Query
		/*	Department dt=new Department();
			dt.setName("CTS");
			s.saveOrUpdate(dt);
			Transaction tx=s.getTransaction();
			tx.commit();*/
		}
		return null;
	}
	
	public Department getbyDeptId(String type,int id)
	{
		Department dept=null;
		if("department".equalsIgnoreCase(type))
		{
			Session s=getSession();
			s.beginTransaction();
			
			dept=(Department)s.get(Department.class,id);
			System.out.println("Department"+dept.getId() +">>>>"+dept.getName());
			s.close();
		}
		return dept;
	}
	public boolean Insert(Department dept)
	{
		try
		{
			Session s=getSession();
			s.beginTransaction();
			s.saveOrUpdate(dept);
			Transaction tx=s.getTransaction();
			tx.commit();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteDepartment(Department d)
	{
		Session s=getSession();
		Transaction tx=s.beginTransaction();
		s.delete(d);
		tx.commit();
	}
	
	//Employee
	public void InsertEmployee(Employee emp)
	{
		
		
		Session s=getSession();
		Transaction tx=s.beginTransaction();
		//Department dnew=(Department)s.get(Department.class, 1);
		//emp.setDeptId(dnew);
		s.saveOrUpdate(emp);
		System.out.println("Emp obj Dirty "+s.isDirty());
		//s.persist(emp);
		//tx.commit();
		/*Department d=emp.getDeptId();
		d.setName("Cas");
		emp.setName("Test Dirty");
		System.out.println("Emp obj Dirty "+s.isDirty());*/
		tx.commit();
	}
	
	public Department getDepartment(int id)
	{
		Department dept;
		Session s=getSessionWithConfig();
		s.beginTransaction();
		
		dept=(Department)s.get(Department.class,id);
		System.out.println("Department"+dept.getId() +">>>>"+dept.getName());
		//s.close();
		return dept;
	}
	
	private static Session getSession()
	{
		Configuration cfg=new Configuration();
		cfg.configure("com/hibernate/cfg/Hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		return s;
	}
	
	private static Session getSessionWithConfig()
	{
		Configuration cfg=new Configuration();
		
		cfg.configure("com/hibernate/cfg/Hibernate.cfg.xml");
		//cfg.addResource("com/hibernate/mapping/Department.hbm.xml");
		cfg.setProperty("hibernate.current_session_context_class", "managed");
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		return s;
	}

	public Employee getEmployee(int i) {
		// TODO Auto-generated method stub
		
		Session s=getSession();
		s.beginTransaction();
		
		Employee emp=(Employee)s.get(Employee.class,i);
		System.out.println("Department"+emp.getId() +">>>>"+emp.getName());
		return emp;
	}
	
	public void insertAll()
	{
		
		Session s=getSession();
		Transaction tx=s.beginTransaction();
		Employee employee=new Employee();
		employee.setColor("Black");
		employee.setName("Test");
			
		EmployeeTransport employeeTransport=new EmployeeTransport();
		employeeTransport.setVehicleId(1);
		employeeTransport.setVehicleType("ss");
		employee.setEmpTransport(employeeTransport);
		s.saveOrUpdate(employee);
		//employee.setEmpTransport(employeeTransport);
		tx.commit();
		
	}
	
	

}
