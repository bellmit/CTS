<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Implicit Validation Page</h1>
<f:view>
	<h:form id="form1">			
		<h:inputText id="inputNumber" value="#{implicitBean.inputNumber}" required="true"/>
		<br>
		<h:message for="form1:inputNumber" style="color:blue"/>		
		<br>
		<h:commandButton value="Submit" action="#{implicitBean.processRequest}" />
	</h:form>	
</f:view>
</body>
</html>