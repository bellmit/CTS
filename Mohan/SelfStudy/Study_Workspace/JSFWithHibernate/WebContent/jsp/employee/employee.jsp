<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t"  uri="http://myfaces.apache.org/tomahawk"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee</title>
</head>
<body>
	<f:view locale="#{languageBean.selectLocale}">
	<f:loadBundle var="msg" basename="properties.Message" />
	<t:saveState id="emp" value="#{employeeDetails.employeeBean}"></t:saveState>
	<h:form id="empForm">
		<h:outputLabel for="" value="#{msg['index.employeeSearch']}"></h:outputLabel>
		<h:messages layout="table" showDetail="true" id="errorMessage" showSummary="false" style="color:red;margin:8px;" globalOnly="true"/>
		
		<table align="right" width="15%">
			<tr>
				<td>
					<h:outputLabel for="" value="#{msg['test.language'] }"></h:outputLabel>				
					<h:selectOneListbox label="#{msg['test.language'] }" value="#{languageBean.selectLang}" onchange="submit()"
							id="selectLang" size="1" >
							<f:valueChangeListener type="com.jsf.Listener.LanguageListener"/>
						<f:selectItem itemLabel="English" itemValue="en"/>
						<f:selectItem itemLabel="Spanish" itemValue="es"/>
					</h:selectOneListbox>
				</td>
				<td>
					<h:outputLabel value="#{languageBean.selectCountry }"></h:outputLabel>
				</td>
			</tr>
		</table>		
		
		<table >
			<tr>
				<td>
					<h:outputLabel>Enter Employee Id</h:outputLabel>
				</td>
				<td>
					<h:inputText id="empId" label="Employee Id" value="#{employeeDetails.employeeBean.empId }"  size="6"  requiredMessage="true">
						<f:validateLength  maximum="8"  >
						</f:validateLength>
					</h:inputText>
					<h:message for="empId" rendered="true" showDetail="true"></h:message>
				</td>
				
			</tr>
			<tr>
				<td>OR</td>
			</tr>
			<tr>
				<td>
					<h:outputLabel>Name Contains</h:outputLabel>
				</td>
				<td>
					<h:inputText id="empName" label="Employee Name" value="#{employeeDetails.employeeBean.empname }" size="16"  requiredMessage="true">
						<f:validateLength  maximum="16"  >
						</f:validateLength>
					</h:inputText>
					<h:message for="empName" rendered="true" showDetail="true"></h:message>
				</td>
			</tr>
			<tr>
				<td>
					<h:commandButton action="#{employeeDetails.getEmployee}" value="Search"></h:commandButton>
				</td>
			</tr>
			
		</table>
		<h:commandLink action="#{employeeDetails.updateEmployee}" >
					<f:param name="empid" value="1"></f:param>
					<h:outputText value="emp"></h:outputText>
				</h:commandLink>
		
		<div>
		<t:dataTable value="#{employeeDetails.employeeBeanList}" var="emp" border="1" rendered="#{employeeDetails.employeeRendered }" 
		 preserveRowStates="true" previousRowDataVar="true">	
			<f:facet name="header"><h:outputText value="EMPLOYEE LIST"/></f:facet> 
			<t:column>
				<f:facet name="header"><h:outputText value="ID"/></f:facet> 
					<h:commandButton action="#{employeeDetails.updateEmployee}">
						<f:param name="empid" value="#{emp.empId}"></f:param>
						<h:outputText value="#{emp.empId}"></h:outputText>
					</h:commandButton>
					<%-- <h:commandLink action="#{employeeDetails.updateEmployee}" value="212" >
					<f:param name="empId" value="#{emp.empId}"></f:param>
					<h:outputText value="#{emp.empId}"></h:outputText>
					</h:commandLink> --%>
					
				</t:column>
			<t:column>
				<f:facet name="header"><h:outputText value="Name" /></f:facet> 
				<t:commandLink action="#{employeeDetails.updateEmployee}">
						<f:param name="empid" value="#{emp.empId}"></f:param>
					<h:outputText value="#{emp.empname}"></h:outputText>
				</t:commandLink>
				</t:column>
			<t:column>
				<f:facet name="header"><h:outputText value="Designation" /></f:facet> 
				<h:outputText value="#{emp.designation}"></h:outputText>
			</t:column>
		</t:dataTable>
		</div>
	</h:form>
	</f:view>
</body>
</html>