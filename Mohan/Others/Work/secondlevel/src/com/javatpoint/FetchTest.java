package com.javatpoint;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchTest {
public static void main(String[] args) {
Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
SessionFactory factory=cfg.buildSessionFactory();
	
	Session session1=factory.openSession();
	Employee emp1=(Employee)session1.load(Employee.class,1);
	System.out.println(emp1.getId()+" "+emp1.getName()+" "+emp1.getDesignation());
	session1.close();
	
	Session session2=factory.openSession();
	Employee emp2=(Employee)session2.load(Employee.class,1);
	System.out.println(emp2.getId()+" "+emp2.getName()+" "+emp2.getDesignation());
	session2.close();
	
	Session sess=factory.openSession();
	Query query = sess.getNamedQuery("employee.select").setInteger("id", 1);
	
	List<Employee> emp3=query.list();
	
	for (Employee emp :emp3) {
		
		System.out.println(emp.getId()+" "+emp.getName()+" "+emp.getDesignation());
	}

	Session sess1=factory.openSession();
	Query query1 = sess.getNamedQuery("employee.select").setInteger("id", 1);
	
	List<Employee> emp4=query.list();
	
	for (Employee emp :emp4) {
		
		System.out.println(emp.getId()+" "+emp.getName()+" "+emp.getDesignation());
	}
}
}
