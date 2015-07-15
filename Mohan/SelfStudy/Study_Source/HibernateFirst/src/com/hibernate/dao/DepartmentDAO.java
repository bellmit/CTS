package com.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hibernate.entity.Department;
import com.hibernate.program.HibernateUtil;

public class DepartmentDAO {

	public void insertDepartment(Department department) {
		Session session=HibernateUtil.getSession();
		Transaction transaction=session.beginTransaction();		
		session.saveOrUpdate(department);		
		transaction.commit();		
	}

	public void deleteDepartment(Integer dno) {
		Session session=HibernateUtil.getSession();
		Transaction transaction=session.beginTransaction();		
		Department department=(Department)session.get(Department.class, dno);
		session.delete(department);		
		session.delete(department);
		transaction.commit();
	}
	
	public void updateDepartment(Department updatedDepartment) {
		Session session=HibernateUtil.getSession();
		Transaction transaction=session.beginTransaction();		
		Department department=(Department)session.get(Department.class, updatedDepartment.getDeptNo());
		department.setDeptName(updatedDepartment.getDeptName());
		department.setLocation(updatedDepartment.getLocation());
		session.update(department);
		transaction.commit();
	}
	
	public Department getDepartmentById(Integer dno) {
		Session session=HibernateUtil.getSession();
		return (Department)session.get(Department.class, dno);
	}
	
}
