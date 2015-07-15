package com.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.javaBean.Department;

public class SessionMethod {

	private static Configuration cfg;
	private static SessionFactory sFactory;
	
	private static Session getSessionWithConfig()
	{
		if(cfg==null)
		{
			cfg=new Configuration();
			
			cfg.configure("com/hibernate/cfg/Hibernate.cfg.xml");
			//cfg.addResource("com/hibernate/mapping/Department.hbm.xml");
			cfg.setProperty("hibernate.current_session_context_class", "managed");
		}
		if(sFactory==null)
			sFactory=cfg.buildSessionFactory();
		Session sess=sFactory.openSession();
		return sess;
	}
	
	public void sessionMethods()
	{
		Department dept;
		Session s=getSessionWithConfig();
		Transaction tx=s.beginTransaction();
		System.out.println("GET/LOAD");
		//
		dept=(Department)s.get(Department.class,2);
		System.out.println("Get Department "+dept);
		//System.out.println("Get Department"+dept.getId() +">>>>"+dept.getName());
		//s.clear();
		dept=(Department)s.load(Department.class,1);
		System.out.println("Get Department "+dept);
		System.out.println("Load Department"+dept.getId() +">>>>"+dept.getName());
		//System.out.println("Department"+dept.getId() +">>>>"+dept.getName());
		
		System.out.println(">>>>>>>>>Contains<<<<<<<<<");
		System.out.println(s.contains(dept));
		s.evict(dept);
		System.out.println("After detach"+s.contains(dept));
		
		dept.setName("DeptChange2");
		System.out.println(">>>>>>>>Update<<<<<<<<<<");
		s.update(dept);
		tx.commit();
		//s.close();
		
	}
}
