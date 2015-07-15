<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Nav 2 Page 1</h1>
<f:view>
	<h:form>		
		<h:commandButton action="#{navigation2Bean.fetchNext}" value="Just Submit" />
		<br>
		<br>
		<br>
		<h:inputText value="#{navigation2Bean.inputNumber}" />
		<h:commandButton action="#{navigation2Bean.fetchNextByInputNumber}" value="Submit By Input Number" />
	</h:form>
</f:view>
</body>
</html>