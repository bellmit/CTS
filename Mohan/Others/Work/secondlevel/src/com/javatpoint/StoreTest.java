package com.javatpoint;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class StoreTest {
public static void main(String[] args) {
	Session session=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
	
	Transaction tx=session.beginTransaction();
	
	session.persist(new Employee("Rahul","PA"));
	session.persist(new Employee("Ajay","A"));
	
	tx.commit();
	session.close();
}
}
