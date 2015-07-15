<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t"  uri="http://myfaces.apache.org/tomahawk"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Employee</title>
</head>
<body>
	<f:view>
	<h:form id="empForm">
		<h:outputLabel>Enter Employee Details</h:outputLabel>
		<h:messages layout="table" showDetail="true" id="errorMessage" showSummary="false" style="color:red;margin:8px;" globalOnly="true"/>
		<table >
			<tr>
				<td>
					<h:outputLabel>Employee Name:</h:outputLabel>
				</td>
				<td>
					<h:inputText id="name" label="Name" value="#{employeeDetails.employeeBean.empname }"  size="20"  requiredMessage="true">	
					</h:inputText>
					<h:message for="empName" rendered="true" showDetail="true"></h:message>
				</td>
				
			</tr>
			<tr>
				<td>Designation</td>
				<td><h:inputText id="desgn" label="Designation" value="#{employeeDetails.employeeBean.designation}"  size="20"  requiredMessage="true"/>
				<h:message for="empDsgn" rendered="true" showDetail="true"></h:message>
				</td>
			</tr>			
			<tr>
				<td>Address :</td>
				<td><h:inputTextarea value="#{employeeDetails.employeeBean.address }"></h:inputTextarea></td>
			</tr>
			<tr>
				<td>Mobile Number :</td>
				<td><h:inputText value="#{employeeDetails.employeeBean.mobileNo }"></h:inputText></td>
			</tr>
			<tr>
				<td>Residence Number :</td>
				<td><h:inputText value="#{employeeDetails.employeeBean.landlineNo }"></h:inputText></td>
			</tr>
		</table>
		<h:commandButton action="#{employeeDetails.registerEmployee}" value="Submit"></h:commandButton>

	</h:form>
	</f:view>
</body>
</html>