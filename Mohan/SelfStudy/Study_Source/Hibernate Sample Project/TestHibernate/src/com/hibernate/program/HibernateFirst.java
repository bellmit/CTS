package com.hibernate.program;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Department;

public class HibernateFirst {
	
	
	public static void main(String []args)
	{
		//SessionFactory sessions =getSession();
		List l=getMessages(1);
		
		System.out.println(((Department)l.get(0)).getName());
	}
	
	private static Session getSession() {
		Configuration configuration=new Configuration();
		configuration.configure("com/hibernate/cfg/my-hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.getCurrentSession();		
		return session;
	}
	
	public static List getMessages(int messageId)
			
			{
			
			Session session = getSession();
			Transaction tx = null;
			try {
			tx = session.beginTransaction();
			List list = session.createQuery("from Department").list();
			tx.commit();
			tx = null;
			return list;
			} catch ( HibernateException e ) {
			if ( tx != null ) tx.rollback();
			//log.log(Level.SEVERE, "Could not acquire message", e);
			
			} finally {
			//session.close();
			}
			return null;
			}
}
