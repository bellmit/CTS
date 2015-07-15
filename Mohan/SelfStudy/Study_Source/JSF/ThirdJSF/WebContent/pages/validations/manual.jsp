<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Manual Validation Page</h1>
<f:view>
	<h:outputText value="#{manualBean.errorMessages}" escape="false" />
	<h:form id="form1">			
		<h:inputText id="inputNumber3" value="#{manualBean.inputNumber}"/>
		<br>
		<h:message for="form1:inputNumber3" style="color:blue"/>		
		<br>
		<h:commandButton value="Submit" action="#{manualBean.processRequest}" />
		<br>
		<h:commandButton value="Submit2" action="#{manualBean.processRequest2}" />
	</h:form>	
</f:view>
</body>
</html>