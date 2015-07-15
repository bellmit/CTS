<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Dept Page 1</h1>
<f:view>
	<h:form>
	<table border="10">
		<tr>
			<th>Department Number</th>
			<th>Department Name</th>
			<th>Multiplty Dno By 5</th>
			<th>Positive/Negative</th>
		</tr>
		<c:forEach var="dept" items="${departments.departments}">
			<tr>
				<td align="center">${dept.number}</td>
				<td align="center">${dept.name}</td>
				<td align="center">${dept.number*5}</td>
				<td align="center">${dept.number%2==0?'Postive':'Negative'}</td>
			</tr>			
		</c:forEach>		
	</table>
	<br>
	<h:outputLabel value="#{departments.departments[0].name}" />
	<br>
	<h:outputLabel value="#{departments.departmentsByLocation['Delhi'].name}" />	
	<br>
	<br>
	<br>
	
	</h:form>
</f:view>
</body>
</html>