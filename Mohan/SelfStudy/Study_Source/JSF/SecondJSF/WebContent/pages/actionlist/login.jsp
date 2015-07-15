<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Action Listener Login Page</h1>
<f:view>
	<f:loadBundle var="mymsg" basename="com.actionlist.login"/>
	<h:form>			
		<h:selectOneMenu id="selLang" onchange="submit()" immediate="true" value="">
			<f:selectItems value="#{login.language}"/>
			<f:valueChangeListener type="com.valuelist.MyValList"/>
		</h:selectOneMenu>
		<br>
		<h:outputLabel value="#{mymsg.userName}" />
		<h:inputText id="userName" value="#{login.userName}" required="true">
			<f:validateLength minimum="5" maximum="10"/>
		</h:inputText>
		<h:message for="userName" showSummary="true" showDetail="false"/>
		<br>
		<h:outputLabel value="#{mymsg.passWord}" />
		<h:inputText id="passWord" value="#{login.passWord}" required="true">
			<f:validateLength minimum="5" maximum="10"/>
		</h:inputText>
		<h:message for="passWord" showSummary="true" showDetail="false"/>
		<br>
		<h:commandButton action="#{login.processLogin}" value="Login"/>
		<br>
		<h:commandButton action="#{login.cancelLogin}" value="Cancel" immediate="true"/>
	</h:form>
</f:view>
</body>
</html>