<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Prop Test Page</h1>

<f:view locale="#{facesContext.externalContext.requestLocale}">
<f:loadBundle var="mymsg" basename="com.test.prop.test"/>
	<h:form>			
		EL-${mymsg.simpleMsg}<br>		
		EL-${mymsg.simple_Msg}<br>
		JSF-<h:outputLabel value="#{mymsg.simpleMsg}" /><br>
		JSF-<h:outputLabel value="#{mymsg['simple_Msg']}" /><br>		
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
<h1>Include Page Start</h1>
<jsp:include page="/pages/testprop/Page2.jsp" />
<h1>Include Page End</h1>	
	</h:form>	
</f:view>
</body>
</html>