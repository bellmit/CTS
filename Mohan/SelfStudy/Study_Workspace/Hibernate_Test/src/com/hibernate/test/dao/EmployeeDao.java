package com.hibernate.test.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.test.entity.Department;
import com.hibernate.test.entity.Employee;
import com.hibernate.test.entity.EmployeeTransport;
import com.hibernate.test.util.HibernateUtil;

public class EmployeeDao {
	
	public Employee getEmployee(int id)
	{
		Employee emp;
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		emp=(Employee)sess.load(Employee.class, id);
		System.err.println("Dept Id>>"+emp.getEmpId());
		return emp;
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

	public void insertEmployeeTransport(EmployeeTransport empTransport) {
		// TODO Auto-generated method stub
		System.out.println("insertEmployee....");
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		try
		{
			sess.saveOrUpdate(empTransport);
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Exception"+e.toString());
			tx.rollback();
		}
		
	}

}
