package com.hibernate.test.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.test.entity.Person;
import com.hibernate.test.util.HibernateUtil;

public class PersonDao {

	
	public Person getPerson(String firstname,String lastname)
	{
		Person per;
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		//dept=(Person)sess.load(Person.class, );
		Query query=sess.createQuery("from Person where firstName=:firstname and lastName=:lastname");
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname);
		per=(Person)query.list().get(0);
		System.err.println("Person Id>>"+per.getFirstName());
		return per;
	}
	
	public void insertPerson(Person per)
	{
		System.out.println("insertPerson....");
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		try
		{
			sess.saveOrUpdate(per);
			//sess.merge(per);
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Exception"+e.toString());
			tx.rollback();
		}
		
	}
	
	public void updatePerson()
	{
		System.out.println("insertPerson....");
		HibernateUtil hbUtil=new HibernateUtil();
		Session sess=hbUtil.getSession();
		Transaction tx=sess.beginTransaction();
		try
		{
			Query query=sess.createQuery("from Person where firstName=:firstname and lastName=:lastname");
			query.setParameter("firstname", "Mohan");
			query.setParameter("lastname", "P");
			Person per=(Person)query.list().get(0);
			//sess.update(per);
			System.out.println("Identifier"+sess.getIdentifier(per)+per.getVersion());
			
			per.setLastName("Pversion");
			sess.persist(per);
			System.out.println(sess.isDirty());
			sess.merge(per);
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Exception"+e.toString());
			tx.rollback();
		}
		
	}
	
	
	
}
