<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:section styleClass="expand" id="logCaseAlertAuditSec1">
	<m:legend id="logCaseAlertAuditLeg1">
			<h:outputLink				onclick="setHiddenValue('CMlogCase:caseAlerts:editAlertAudit:alert_audit_open','minus');	 				 toggle('audit_plusalertAuditing',2);					 plusMinusToggle('audit_plusalertAuditing',this,'CMlogCase:caseAlerts:editAlertAudit:alert_audit_open');					 return false;"	id="alert_plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="caseAlertAudit" value="#{msg['label.audit']}" />
			</h:outputLink>
			<h:inputHidden id="alert_audit_open" value="#{logCaseDataBean.alertAuditOpen}"/>
	</m:legend>
	<m:div sid="audit_plusalertAuditing">
		<m:table cellspacing="0" width="50%" id="logCaseAlertAuditTab1Id">
			<m:tr id="logCaseAlertAuditTab1Tr1Id">
				<m:td colspan="2" id="logCaseAlertAuditTab1Tr1C1Id">
					<h:outputText value="#{msg['label.audit.alertInfoTable']}" styleClass="strong" id="logCaseAlertAuditOutTxt1Id"/>
				</m:td>
			</m:tr>
			<m:tr styleClass="mousepointer" id="logCaseAlertAuditTab1R2Id">
				<m:td styleClass="dataLabel" id="logCaseAlertAuditTab1R2C1Id">
					<h:outputText value="#{msg['label.audit.lastUpdateDateTime']}" styleClass="outputLabel" id="logCaseAlertAuditOutTxt2Id"/>
				</m:td>
				<m:td id="logCaseAlertAuditTab1R2C2Id">
					<t:commandLink id="PRGCMGTCL106" onmousedown="javascript:flagWarn=false;" action="#{logCaseControllerBean.showAlertAuditHistory}">
						<h:outputText value="#{logCaseDataBean.alertVO.auditTimeStamp}" id="logCaseAlertAuditOutTxt3Id">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr id="logCaseAlertAuditTab1R3Id">
				<m:td styleClass="dataLabel" id="logCaseAlertAuditTab1R3C1Id">
					<h:outputText value="#{msg['label.audit.lastUpdatedUserID']}" styleClass="outputLabel" id="logCaseAlertAuditOutTxt4Id"/>
				</m:td>
				<m:td id="logCaseAlertAuditTab1R3C2Id">
					<h:outputText value="#{logCaseDataBean.alertVO.auditUserID}" id="logCaseAlertAuditOutTxt5Id"/>
					<m:br />
					<m:br />
				</m:td>
			</m:tr>
			<m:tr id="logCaseAlertAuditTab1R4Id">
				<m:td styleClass="dataLabel" id="logCaseAlertAuditTab1R4C1Id">
					<h:outputText value="#{msg['label.audit.originalEntryDate']}" styleClass="outputLabel" id="logCaseAlertAuditOutTxt6Id"/>
				</m:td>
				<m:td id="logCaseAlertAuditTab1R4C2Id">
					<h:outputText value="#{logCaseDataBean.alertVO.addedAuditTimeStamp}" id="logCaseAlertAuditOutTxt7Id">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</m:td>
			</m:tr>
			<m:tr id="logCaseAlertAuditTab1R5Id">
				<m:td styleClass="dataLabel" id="logCaseAlertAuditTab1R5C1Id">
					<h:outputText value="#{msg['label.audit.originalEntryUserID']}"	styleClass="outputLabel" id="logCaseAlertAuditOutTxt8Id"/>
				</m:td>
				<m:td id="logCaseAlertAuditTab1R5C2Id">
					<h:outputText value="#{logCaseDataBean.alertVO.addedAuditUserID}" id="logCaseAlertAuditOutTxt9Id"/>
				</m:td>
			</m:tr>
			<m:tr id="logCaseAlertAuditTab1R6Id">
				<m:td colspan="2" id="logCaseAlertAuditTab1R6C1Id">
					<m:div id="logCaseAlertAuditTab1R6C1Div1Id">
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
		<m:br />
		<m:br />
		<m:div id="log_detail" styleClass=""
			rendered="#{logCaseDataBean.alertAuditHistoryRender}">
			<h:dataTable cellspacing="0" styleClass="dataTable"				id="pcpHistoryTable" width="50%"				value="#{logCaseDataBean.alertAuditHistoryList}"				var="alertHistory">
				<h:column id="hiscolname">
					<f:facet name="header">
						<h:outputText id="hcolname"							value="#{msg['label.audit.fieldName']" />
					</f:facet>
					<h:outputText value="#{alertHistory.columnName}" id="logCaseAlertAuditOutTxt10Id"/>
				</h:column>
				<h:column id="hisbefore">
					<f:facet name="header">
						<h:outputText id="hbefore"							value="#{msg['label.caseSteps.before']}" />
					</f:facet>
					<h:outputText value="#{alertHistory.columnBeforeValue}" id="logCaseAlertAuditOutTxt11Id"/>
				</h:column>
				<h:column id="hisafter">
					<f:facet name="header">
						<h:outputText id="hafter"							value="#{msg['label.caseSteps.after']}" />
					</f:facet>
					<h:outputText value="#{alertHistory.columnAfterValue}" id="logCaseAlertAuditOutTxt12Id"/>
				</h:column>
			</h:dataTable>
		</m:div>
	</m:div>
</m:section>
