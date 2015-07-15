package com.hibernate.program;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.hibernate.entity.Department;

public class HibernateUtil {

	public static void main(String[] args) {
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		Department department =new Department();
		department.setDepartmentID(1);
		department.setName("HC1");
		session.saveOrUpdate(department);
		//tx.commit();
		Department dept=(Department)session.get(Department.class,1);
		
		if(dept!=null)
			System.out.println("Department Name >>>>> "+dept.getName());
		else
			System.out.println("No Data Found");
		tx.commit();
	}

	private static Session getSession() {
		Configuration configuration=new Configuration();
		configuration.configure("com/hibernate/cfg/my-hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.getCurrentSession();		
		return session;
	}
	
}
