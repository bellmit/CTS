<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t"  uri="http://myfaces.apache.org/tomahawk"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Confirmed</title>
</head>
<body>
<f:view>
test
<h:messages layout="table" showDetail="true" id="errorMessage" showSummary="false" style="color:red;margin:8px;" globalOnly="true"/>
<h:form>
	<h:commandLink value="#{employeeDetails.employeeBean.empId}" action="#{employeeDetails.updateEmployee}">
		<f:param name="empid" value="#{employeeDetails.employeeBean.empId}"></f:param>
	</h:commandLink>
</h:form>
</f:view>
</body>
</html>