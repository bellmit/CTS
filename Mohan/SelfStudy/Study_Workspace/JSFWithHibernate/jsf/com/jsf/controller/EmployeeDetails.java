package com.jsf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import com.general.Exception.RecordNotFoundException;
import com.hibernate.dao.EmployeeDao;
import com.hibernate.entity.Employee;
import com.jsf.javabean.EmployeeBean;
import com.jsf.javabean.LanguageBean;

public class EmployeeDetails {
	
	private static Logger log=Logger.getLogger(EmployeeDetails.class);
	
	private EmployeeBean employeeBean;
	
	private boolean employeeRendered=false;
	
	private List<EmployeeBean> employeeBeanList;
	
	
	public List<EmployeeBean> getEmployeeBeanList() {
		return employeeBeanList;
	}

	public void setEmployeeBeanList(List<EmployeeBean> employeeBeanList) {
		this.employeeBeanList = employeeBeanList;
	}

	
	
	public boolean isEmployeeRendered() {
		return employeeRendered;
	}

	public void setEmployeeRendered(boolean employeeRendered) {
		this.employeeRendered = employeeRendered;
	}

	public EmployeeBean getEmployeeBean() {
		return employeeBean;
	}

	public void setEmployeeBean(EmployeeBean employeeBean) {
		this.employeeBean = employeeBean;
	}

	public String getEmployee()
	{
		log.debug("Employee Details >> getEmployee() call...");
		
		List<Employee> empVOList;
		EmployeeBean empBean;
		EmployeeDao empDao=new EmployeeDao();
		
		FacesContext fc=FacesContext.getCurrentInstance();
		EmployeeBean reqBean=(EmployeeBean)fc.getExternalContext().getRequestMap().get("employeeBean");
		//EmployeeDetails empdet=(EmployeeDetails)fc.getExternalContext().getRequestMap().get("employeeDetails");
		
		System.out.println("Employee Controller Bean");
		System.out.println("Emp ID>>>>"+reqBean.getEmpId());
		//System.out.println("Emp Det>>>>"+empdet.getEmployeeBean().getEmpId());
		
		employeeBeanList=new ArrayList<EmployeeBean>();
		try {
			
			empVOList=empDao.getEmployee(employeeBean);
			if(empVOList.size()==0)
				throw new RecordNotFoundException("Employee");
			
			for (Employee empVO : empVOList) {
				if(empVO!=null)
				{
					empBean=new EmployeeBean();
					empBean.setEmpId(empVO.getEmpId());
					empBean.setDesignation(empVO.getDesignation());
					empBean.setEmpname(empVO.getEmpName());
					System.out.println("Empl Name"+empBean.getEmpname());
					employeeBeanList.add(empBean);
					
					//empdet.setEmployeeBeanList(this.employeeBeanList);
					//fc.getExternalContext().getRequestMap().put("employeeDetails", empdet);
				}
			}		
			
			this.employeeRendered=true;
		}
		catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			fc.addMessage(null, new FacesMessage("General Message >>>> "+e.getMessage()));
			
			fc.addMessage("empId", new FacesMessage("Specific Message for EMPID"));
			e.printStackTrace();
		}
		log.debug("Employee Details >> getEmployee() call... End");
		return "search";
	}
	
	public String updateEmployee()
	{
		log.debug("Employee Details >> updateEmployee() call...");
		FacesContext fc=FacesContext.getCurrentInstance();
		String id=(String)fc.getExternalContext().getRequestParameterMap().get("empid");
		System.out.println("Emp ID>>>>"+id);
		//this.employeeBean=new EmployeeBean();
		this.employeeBean.setEmpId(Integer.valueOf(id));
		//fc.getExternalContext().getRequestMap().put("employeeBean", employeeBean);
		return "update";
	}
	
	public String registerEmployee()
	{
		System.out.println("Register Employee");
		log.debug("Employee Details >> registerEmployee() call...");
		FacesContext fc=FacesContext.getCurrentInstance();
		EmployeeBean reqBean=(EmployeeBean)fc.getExternalContext().getRequestMap().get("employeeBean");
		//EmployeeDetails empdet=(EmployeeDetails)fc.getExternalContext().getRequestMap().get("employeeDetails");
		
		System.out.println("Emp Name>>>>"+reqBean.getEmpname());
		
		EmployeeDao empDao=new EmployeeDao();
		
		Employee emp=empDao.register(setbeanToEntity(reqBean));
		fc.addMessage(null, new FacesMessage("Employee Created in DB /\n Generated Employee Id is "));
		reqBean.setEmpId(emp.getEmpId());
		//employeeBean=reqBean;
		return "success";
	}
	
	private static Employee setbeanToEntity(EmployeeBean employeeBean)
	{
		log.debug("Employee Details >> setBeantoEntity() call...");
		Employee emp=new Employee();
		emp.setEmpName(employeeBean.getEmpname());
		emp.setEmpAddress(employeeBean.getAddress());
		emp.setDesignation(employeeBean.getDesignation());
		emp.setLandLine(employeeBean.getLandlineNo());
		emp.setMobile(employeeBean.getMobileNo());
		return emp; 
	}
	
	public void localeChanged(ValueChangeEvent e){
		   //assign new value to country
		System.out.println("Value Change Listener Method");
		 LanguageBean userData = (LanguageBean) FacesContext.getCurrentInstance().
			        getExternalContext().getSessionMap().get("languageBean"); 
		 	userData.setSelectLocale(new Locale(e.getNewValue().toString()));
			     userData.setSelectCountry(e.getNewValue().toString());
		   FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(e.getNewValue().toString()));
		   //FacesContext.getCurrentInstance().getApplication().setDefaultLocale(userData.getSelectLocale());
		}
	
	
}
