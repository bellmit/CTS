package com.hibernate.test.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static Configuration cfg;
	private static SessionFactory sf;
	
	public Session getSession()
	{
		if(cfg==null)
		{
			cfg=new Configuration();
			cfg.configure("com/hibernate/test/cfg/Hibernate.cfg.xml");
			sf=cfg.buildSessionFactory();
		}
		Session sess=sf.openSession();
		return sess;
	}
	
	
}
