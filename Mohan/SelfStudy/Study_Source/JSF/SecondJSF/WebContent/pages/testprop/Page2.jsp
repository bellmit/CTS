<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Include Prop Test Page</h1>			
		EL-${mymsg.simpleMsg}<br>		
		EL-${mymsg['simple.Msg']}<br>
		JSF-<h:outputLabel value="#{mymsg.simpleMsg}" /><br>
		JSF-<h:outputLabel value="#{mymsg['simple.Msg']}" /><br>		
		<h:outputFormat value="#{mymsg['welcome.msg']}">
			<f:param value="Test" />
			<f:param value="12/12/12" />
		</h:outputFormat>
		<br>
		<h:outputFormat value="#{mymsg['welcome.msg.html']}" escape="false">
			<f:param value="Test" />
			<f:param value="12/12/12" />
		</h:outputFormat>
		<h:outputFormat value="#{mymsg['welcome.msg.beanpop']}">
			<f:param value="#{propBean.userName}" />
			<f:param value="#{propBean.currentDate}" />
		</h:outputFormat>				
</body>
</html>