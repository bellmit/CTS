<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Action Listener Page</h1>
<f:view>
	<h:form>			
		<h:inputText value="#{actionlistNavigationBean.inputNumber}" />
		<br>
		<h:commandButton id="properId" actionListener="#{myActionListener.clickButton}" action="#{actionlistNavigationBean.nextPage}" value="Submit Immediate" immediate="true" />
		<br>
		<h:commandButton action="#{actionlistNavigationBean.nextPage}" value="Submit">
			<f:actionListener type="com.actionlist.MyActionListener2" />
		</h:commandButton>
		<br>
		<h:selectOneMenu value="#{actionlistNavigationBean.selectedName}" disabled="#{myActionListener.disableButton}">
			<f:selectItems value="#{actionlistNavigationBean.nameMap}"/>
		</h:selectOneMenu>
		<h:commandButton actionListener="#{myActionListener.activateButton}" value="Disable Dropdown" immediate="true" />
		<h:commandButton value="Submit" action="#{actionlistNavigationBean.nextPage}"/>
	</h:form>
</f:view>
</body>
</html>