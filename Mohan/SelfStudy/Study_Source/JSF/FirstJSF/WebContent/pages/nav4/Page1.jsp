<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Nav 4 Page 1</h1>
<f:view>
	<h:form>
		<f:param name="test1" value="map1" > </f:param>
		<h:commandLink action="#{navigation4Bean.goToPage2}" value="test">
		<f:param name="test" value="map" > </f:param>
</h:commandLink>
	
			
		<h:commandButton action="#{navigation4Bean.goToPage2}" value="Page 2" />
		<br>		
		<h:commandButton action="#{navigation4Bean.goToPage3}" value="Page 3" />
	</h:form>
</f:view>
</body>
</html>