package com.hibernate.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.test.entity.Department;
import com.hibernate.test.util.HibernateUtil;

public class DepartmentDao {

	
	public Department getDepartment(int id)
	{
		Department dept;
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		dept=(Department)sess.load(Department.class, id);
		System.err.println("Dept Id>>"+dept);
		return dept;
	}
	
	public void insertDepartment(Department dept)
	{
		System.out.println("insertDepartment....");
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		try
		{
			sess.saveOrUpdate(dept);
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Exception"+e.toString());
			tx.rollback();
		}
		
	}
	
	public void updateDept(int id)
	{
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		Department dept=(Department)sess.load(Department.class,id);
		//sess.saveOrUpdate(dept);
		dept.setDeptName("Version21");
		sess.getTransaction().commit();
	}
	public void updateDept1(int id)
	{
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		Department dept=(Department)sess.load(Department.class,id);
		//sess.saveOrUpdate(dept);
		dept.setDeptName("Version12");
		sess.getTransaction().commit();
	}
}
