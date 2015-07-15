<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Login Page 1</h1>
<f:view>
	<h:form>
		<h:inputText value="#{loginBean.userName}" />
		<br>	
		<h:inputSecret value="#{loginBean.passWord}" />
		<br>
		<h:commandButton action="#{loginBean.login}" value="Login" />
	</h:form>
</f:view>
</body>
</html>