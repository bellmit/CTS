package bean;

import java.util.Date;
import java.util.Map;

public class EmployeeVO {

	private int employeeID;
	private String employeeName;
	private Map<String,Integer> salaryDetails;
	private Date joiningDate;
	private Date resignationDate;
	private char departmentCode;;
	private long mobileNumber;
	private String emailID;
	private int numberOfLeaveDays;
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Date getResignationDate() {
		return resignationDate;
	}
	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}
	public char getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(char departmentCode) {
		this.departmentCode = departmentCode;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public int getNumberOfLeaveDays() {
		return numberOfLeaveDays;
	}
	public void setNumberOfLeaveDays(int numberOfLeaveDays) {
		this.numberOfLeaveDays = numberOfLeaveDays;
	}
	public Map<String, Integer> getSalaryDetails() {
		return salaryDetails;
	}
	public void setSalaryDetails(Map<String, Integer> salaryDetails) {
		this.salaryDetails = salaryDetails;
	}
}
