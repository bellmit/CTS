package com.hibernate.dao;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.Employee;
import com.hibernate.util.HibernateUtil;
import com.jsf.javabean.EmployeeBean;



public class EmployeeDao {
	
	private static Logger logger=Logger.getLogger(Employee.class);
	
	
	
	public List<Employee> getEmployee(EmployeeBean empbean)
	{
			
		logger.warn("My first logger TEST");
		System.out.println("Employee DAO");
		Employee emp;
		List<Employee> empList=new ArrayList<Employee>();
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		
		System.out.println("EMP DAO ====>ID ===>"+empbean.getEmpId());
		if(empbean.getEmpId()==0 && empbean.getEmpname()!="")
		{
			Query qry=sess.createQuery("from Employee as e where e.empName like ?");
			qry.setString(0,"%"+ empbean.getEmpname()+"%");
			empList=qry.list();
		}
		else if(empbean.getEmpId()!=0)
		{
			emp=(Employee)sess.get(Employee.class, empbean.getEmpId());
			empList.add(emp);
		}
		
		
		return empList;
	}
	
	public void insertEmployee(Employee emp)
	{
		System.out.println("insertEmployee....");
		HibernateUtil hbUtil=new HibernateUtil();
		try
		{
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		
			sess.saveOrUpdate(emp);
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Exception"+e.toString());
			e.printStackTrace();
			//tx.rollback();
		}
		
	}
	
	public void updateEmployee(Employee emp)
	{
		System.out.println("insertEmployee....");
		HibernateUtil hbUtil=new HibernateUtil();
		try
		{
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		
			sess.saveOrUpdate(emp);
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Exception"+e.toString());
			e.printStackTrace();
			//tx.rollback();
		}
		
	}

	public Employee register(Employee emp) {
		// TODO Auto-generated method stub
		System.out.println("insertEmployee....");
		HibernateUtil hbUtil=new HibernateUtil();
		try
		{
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		
			sess.saveOrUpdate(emp);
			System.out.println("Employee Id"+emp.getEmpId());
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Exception"+e.toString());
			e.printStackTrace();
			//tx.rollback();
		}
		return emp;
	}



}
