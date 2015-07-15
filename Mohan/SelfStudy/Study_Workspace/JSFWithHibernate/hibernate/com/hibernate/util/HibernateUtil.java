package com.hibernate.util;

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
			try {
			    System.out.println("Loading driver...");
			    Class.forName("com.mysql.jdbc.Driver");
			    System.out.println("Driver loaded!");
			} catch (ClassNotFoundException e) {
			    throw new RuntimeException("Cannot find the driver in the classpath!", e);
			}
			
			
			cfg=new Configuration();
			cfg.configure("com/hibernate/cfg/Hibernate.cfg.xml");
			sf=cfg.buildSessionFactory();
		}
		Session sess=sf.openSession();
		return sess;
	}
	
	
}
