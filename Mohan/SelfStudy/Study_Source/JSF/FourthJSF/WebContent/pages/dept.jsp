<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<LINK href="<%=request.getContextPath()%>/css/mycss.css" rel="stylesheet" type="text/css">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Data Table Page</h1>
<f:view>
	<h:form id="form1">		
		<h:dataTable value="#{departmentBean.departments}" border="1" var="dept" rowClasses="drowb" headerClass="drowh">
			<h:column>
				<f:facet name="header">
					<f:verbatim>DNumber</f:verbatim>
				</f:facet>			
				<h:outputText value="#{dept.number}" />
				<f:facet name="footer">
					<f:verbatim>Total</f:verbatim>
				</f:facet>				
			</h:column>
			<h:column>
				<f:facet name="header">
					<f:verbatim>DName</f:verbatim>
				</f:facet>		
				<h:outputText value="#{dept.name}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<f:verbatim>Profit</f:verbatim>
				</f:facet>		
				<h:outputText value="#{dept.profit}" />				
				<f:facet name="footer">
					<f:verbatim>DName</f:verbatim>
				</f:facet>							
			</h:column>			
		</h:dataTable>
		<br>
		<t:inputDate type="date" popupCalendar="true" value="#{departmentBean.dateSelected}"/>
		<br>
		<h:commandButton value="Submit" />
		<br>
		<t:panelTabbedPane border="1" width="50%">
			<t:panelTab label="Tab 1">
				<h:outputLabel value="#{departmentBean.tab1Data}" />
			</t:panelTab>
			<t:panelTab label="Tab 2">
				<h:outputLabel value="#{departmentBean.tab2Data}" />
			</t:panelTab>			
		</t:panelTabbedPane>
		<br>
		<t:dataList value="#{departmentBean.departments}" layout="orderedList" var="dept2" itemStyleClass="dlisti">
			<h:column>
				<h:outputText value="#{dept2.number}" />
			</h:column>
			<h:column>
				<h:outputText value="#{dept2.name}" />
			</h:column>			
		</t:dataList>		
	</h:form>	
</f:view>
</body>
</html>