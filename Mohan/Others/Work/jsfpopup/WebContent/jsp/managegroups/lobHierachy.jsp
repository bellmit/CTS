<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<t:saveState id="CMGTTOMSS498"	value="#{lobHierarchyControlBean.lobHierarchyDataBean.lobHieracVO}"></t:saveState>
<t:saveState id="CMGTTOMSS499" value="#{lobHierarchyDataBean.idName}"></t:saveState>
<t:saveState id="CMGTTOMSS500" value="#{lobHierarchyPlanTree.model}"></t:saveState>
<%--Fix for Defect ID :ESPRD00678595  --%>
<m:div styleClass="otherbg" onmousedown="javascript:flagWarn=false;">
	<m:pod title="#{msg['label.managegroupnav.Navigator']}">
		<t:tree2 id="myTree" value="#{lobHierarchyPlanTree.model}" var="node"
			varNodeToggler="t" clientSideToggle="false" showRootNode="false">

			<f:facet name="base">
				<h:panelGroup id="PRGCMGTPGP121">
					<h:outputText id="PRGCMGTOT1612" value="#{node.description}"						title="#{node.identifier}" />
				</h:panelGroup>
			</f:facet>
			<f:facet name="lobs">
				<h:panelGroup id="PRGCMGTPGP122">
					<t:commandLink id="lb"						action="#{lobHierarchyControlBean.addReport}">
						<h:outputText id="PRGCMGTOT1613" value="#{node.description}"							title="#{node.identifier}" />
						<f:param name="ind" value="#{node.identifier}" />
					</t:commandLink>
				</h:panelGroup>
			</f:facet>
			<f:facet name="reports">
				<h:panelGroup id="PRGCMGTPGP123">
					<t:commandLink id="rep"						action="#{lobHierarchyControlBean.editReport}">
						<h:outputText id="PRGCMGTOT1614" value="#{node.description}"							title="#{node.identifier}" />
						<f:param name="ind" value="#{node.identifier}" />
					</t:commandLink>
				</h:panelGroup>
			</f:facet>
			<f:facet name="business">
				<h:panelGroup id="PRGCMGTPGP124">
					<t:commandLink id="bus"						action="#{lobHierarchyControlBean.editBusiness}">
						<h:outputText id="PRGCMGTOT1615" value="#{node.description}"							title="#{node.identifier}" />
						<f:param name="ind" value="#{node.identifier}" />
					</t:commandLink>
				</h:panelGroup>
			</f:facet>
			<f:facet name="depart">
				<h:panelGroup id="PRGCMGTPGP125">
					<t:commandLink id="dep"						action="#{lobHierarchyControlBean.editDepart}">
						<h:outputText id="PRGCMGTOT1616" value="#{node.description}"							title="#{node.identifier}" />
						<f:param name="ind" value="#{node.identifier}" />
					</t:commandLink>
				</h:panelGroup>
			</f:facet>
		</t:tree2>
	</m:pod>
</m:div>

