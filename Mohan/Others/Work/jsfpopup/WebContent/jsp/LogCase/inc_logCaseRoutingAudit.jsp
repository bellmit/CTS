<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:section id="logCaseRoutingAuditSec1" styleClass="expand">
	<m:legend id="logCaseRoutingAuditLeg1">
			<h:outputLink 				onclick="javascript:flagWarn=false;setHiddenValue('CMlogCase:routingDetails:routingViewRoutingAssignmentAudit:routing_hiddenID','minus');	 		 	toggle('audit_plusRoutingAudit',2);			 	plusMinusToggle('audit_plusRoutingAudit',this,'CMlogCase:routingDetails:routingViewRoutingAssignmentAudit:routing_hiddenID');			 	return false;" id="routing_plusMinus_Auditmore"  styleClass="plus">
				<h:outputText id="routing_label" value="#{msg['label.audit']}" />
			</h:outputLink>
			<h:inputHidden id="routing_hiddenID" value="#{logCaseDataBean.routingAuditOpen}"/>
	</m:legend>
	<m:div sid="audit_plusRoutingAudit">
		<m:table id="logCaseRoutingAuditTb1" cellspacing="0" width="50%">
			<m:tr id="logCaseRoutingAuditTr1">
				<m:td id="logCaseRoutingAuditTd1" colspan="2">
					<h:outputText id="logCaseRoutingAuditOutTxt1" value="#{msg['label.audit.routingInfoTable']}" styleClass="strong" />
				</m:td>
			</m:tr>
			<m:tr id="logCaseRoutingAuditTr2" styleClass="mousepointer">
				<m:td id="logCaseRoutingAuditTd2" styleClass="dataLabel">
					<h:outputText id="logCaseRoutingAuditOutTxt2" value="#{msg['label.audit.lastUpdateDateTime']}"						styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseRoutingAuditTd3">
					<t:commandLink id="PRGCMGTCL110" action="#{logCaseControllerBean.showRoutingAuditHistory}" onmousedown="javascript:flagWarn=false;">
						<h:outputText id="logCaseRoutingAuditOutTxt3" value="#{logCaseDataBean.routingVO.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr id="logCaseRoutingAuditTr3">
				<m:td id="logCaseRoutingAuditTd4" styleClass="dataLabel">
					<h:outputText id="logCaseRoutingAuditOutTxt4" value="#{msg['label.audit.lastUpdatedUserID']}" styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseRoutingAuditTd5">
					<h:outputText id="logCaseRoutingAuditOutTxt5" value="#{logCaseDataBean.routingVO.auditUserID}" />
					<m:br id="logCaseRoutingAuditBr1"/>
					<m:br id="logCaseRoutingAuditBr2"/>
				</m:td>
			</m:tr>
			<m:tr id="logCaseRoutingAuditTr4">
				<m:td id="logCaseRoutingAuditTd6" styleClass="dataLabel">
					<h:outputText id="logCaseRoutingAuditOutTxt6" value="#{msg['label.audit.originalEntryDate']}"						styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseRoutingAuditTd7">
					<h:outputText id="logCaseRoutingAuditOutTxt7" value="#{logCaseDataBean.routingVO.addedAuditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</m:td>
			</m:tr>
			<m:tr id="logCaseRoutingAuditTr5">
				<m:td id="logCaseRoutingAuditTd8" styleClass="dataLabel">
					<h:outputText id="logCaseRoutingAuditOutTxt8" value="#{msg['label.audit.originalEntryUserID']}"						styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseRoutingAuditTd9">
					<h:outputText id="logCaseRoutingAuditOutTxt9" value="#{logCaseDataBean.routingVO.addedAuditUserID}" />
				</m:td>
			</m:tr>
			<m:tr id="logCaseRoutingAuditTr6">
				<m:td id="logCaseRoutingAuditTd10" colspan="2">
					<m:div id="logCaseRoutingAuditDiv1" styleClass="clearl">
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
		<m:br id="logCaseRoutingAuditBr3"/>
		<m:br id="logCaseRoutingAuditBr4"/>
		<m:div id="log_detail_routing" styleClass=""
			rendered="#{logCaseDataBean.routingAuditHistoryRender}">
			<h:dataTable cellspacing="0" styleClass="dataTable"				id="routinHistoryTable" width="50%"				value="#{logCaseDataBean.routingAuditHistoryList}"				var="routingHistory">
				<h:column id="hiscolname_attach">
					<f:facet name="header">
						<h:outputText id="hcolname_attach"							value="#{msg['label.audit.fieldName']}" />
					</f:facet>
					<h:outputText id="logCaseRoutingAuditOutTxt10" value="#{routingHistory.columnName}" />
				</h:column>
				<h:column id="hisbefore_attach">
					<f:facet name="header">
						<h:outputText id="hbefore_attach"							value="#{msg['label.caseSteps.before']}" />
					</f:facet>
					<h:outputText id="logCaseRoutingAuditOutTxt11" value="#{routingHistory.columnBeforeValue}" />
				</h:column>
				<h:column id="hisafter_attach">
					<f:facet name="header">
						<h:outputText id="hafter_attach"							value="#{msg['label.caseSteps.after']}" />
					</f:facet>
					<h:outputText id="logCaseRoutingAuditOutTxt12" value="#{routingHistory.columnAfterValue}" />
				</h:column>
			</h:dataTable>
		</m:div>
	</m:div>
</m:section>
