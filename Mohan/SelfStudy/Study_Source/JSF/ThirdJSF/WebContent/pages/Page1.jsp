<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>HTML Library Page</h1>
<f:view>
	<h:form>			
		<h:inputText value="#{hTMLLibBean.inputText}" valueChangeListener="#{hTMLLibBean.inputTextValueChange}" />
		<br>		
		<h:commandButton value="Submit"/>
		<br>
		<h:commandButton image="/images/PerfCenterCpl.ico"/>
		<br>
		<h:commandLink value="Click Link" />
		<br>
		<h:selectOneMenu value="#{hTMLLibBean.selected1}">
			<f:selectItems value="#{hTMLLibBean.select1}"/>
		</h:selectOneMenu>
		<br>
		<h:selectOneMenu value="#{hTMLLibBean.selected2}">
			<f:selectItems value="#{hTMLLibBean.select2}"/>
		</h:selectOneMenu>	
		<br>
		<h:selectManyCheckbox value="#{hTMLLibBean.checkbox1}">
			<f:selectItems value="#{hTMLLibBean.checkboxes1}"/>
		</h:selectManyCheckbox>
					
					
	</h:form>
</f:view>
</body>
</html>