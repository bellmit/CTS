<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<f:view locale="#{languageBean.selectLocale}">
	<f:loadBundle var="msg" basename="properties.Message" />

	<h:form>
		<table align="right" width="15%">
			<tr>
				<td>
					<h:outputLabel for="" value="#{msg['test.language'] }"></h:outputLabel>				
					<h:selectOneListbox label="#{msg['test.language'] }" value="#{languageBean.selectLang}" onchange="submit()"
							id="selectLang" size="1" valueChangeListener="#{employeeDetails.localeChanged}">
						<f:selectItem itemLabel="English" itemValue="en"/>
						<f:selectItem itemLabel="Spanish" itemValue="es"/>
					</h:selectOneListbox>
				</td>
				<td>
					<h:outputLabel value="#{languageBean.selectCountry }"></h:outputLabel>
				</td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td>
					<h:column>
						<h:commandLink action="employeeSearch"><h:outputLabel for="" value="#{msg['index.employeeSearch']}"></h:outputLabel></h:commandLink>
					</h:column>
				</td>
			</tr>
			<tr>
			<td>
			<h:column><h:commandLink action="employeeRegister"><h:outputLabel for="" value="#{msg['index.employeeRegister']}"></h:outputLabel></h:commandLink></h:column>
			</tr>
			<tr>
				<td>
				<h:outputLabel value="#{msg['test.message'] }"></h:outputLabel>
				</td>
			</tr>
		</table>
	</h:form>

</f:view>

</body>
</html>