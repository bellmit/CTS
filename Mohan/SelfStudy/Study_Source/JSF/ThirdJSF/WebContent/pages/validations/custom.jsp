<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Custom Validation Page</h1>
<f:view>
	<h:form id="form1">			
		<h:inputText id="inputNumber" value="#{customBean.inputNumber}">
			<f:validator validatorId="myvalid"/>
		</h:inputText>		
		<br>
		<h:message for="form1:inputNumber" style="color:blue"/>		
		<br>
		<h:commandButton value="Submit" action="#{customBean.processRequest}" />
		<br>
		<br>
		<h:inputText id="inputNumber2" value="#{customBean.inputNumber2}" validator="#{customBean.someValidate}" />
		<h:message for="form1:inputNumber2" style="color:blue"/>
	</h:form>	
</f:view>
</body>
</html>