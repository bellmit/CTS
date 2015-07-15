package com.hibernate.program;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static Session getSession() {
		Configuration configuration=new Configuration();
		configuration.configure("com/hibernate/cfg/my-hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();		
		return session;
	}	
}
