<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>

<m:div styleClass="moreInfo" id="logCaseEditAlertDiv1Id">
	<m:div styleClass="moreInfoBar" id="logCaseEditAlertDiv2Id">
		<m:div styleClass="infoTitle" id="logCaseEditAlertDiv3Id">
			<h:outputText id="PRGCMGTOT628" value="#{msg['label.case.alert.editAlert']}" />
		</m:div>
		<m:div styleClass="infoActions" id="logCaseEditAlertDiv4Id">
			<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543--%>
			<t:commandLink id="saveAlertsId2" action="#{logCaseControllerBean.saveAlerts}" onmousedown="javascript:flagWarn=false;" rendered="#{!logCaseDataBean.disableAlertSave && !(logCaseDataBean.disableScreenField)}">
				<h:outputText value="#{msg['label.case.save']}" id="logCaseEditAlertOutTxt1Id" styleClass="strong"/>
			</t:commandLink>
			<h:outputText value="#{msg['label.case.save']}" rendered="#{logCaseDataBean.disableAlertSave || logCaseDataBean.disableScreenField}" id="logCaseEditAlertOutTxt2Id"/>
			<h:outputText escape="false" value="&nbsp;" id="logCaseEditAlertOutTxt3Id"/><h:outputText value="|" id="logCaseEditAlertOutTxt4Id"/><h:outputText escape="false" value="&nbsp;" id="logCaseEditAlertOutTxt5Id"/>
			<t:commandLink id="cancelAlertsPage2" action="#{logCaseControllerBean.cancelAlertsPage}" onmousedown="javascript:focusThis('caseAlertsSection');focusPage('CaseAlertsHeader');">
				<h:outputText value="#{msg['label.case.cancel']}" id="logCaseEditAlertOutTxt6Id"/>
			</t:commandLink>
		</m:div>
	</m:div>
	<m:div styleClass="moreInfoContent" id="logCaseEditAlertDiv5Id">
	<%--Modified for defect ESPRD00446264 starts--%>
<%--<m:div id="logCaseEditAlertDiv6Id"
		rendered="#{logCaseDataBean.showCaseAlertsMessages}"
		styleClass="msgBox">
		<h:messages layout="table" showDetail="true" styleClass="successMsg"			id="editAlertErrorMessage" showSummary="false">
		</h:messages>
	</m:div>--%>
	
	<%--Defect ESPRD00446264 Fix ends--%>
		<m:div styleClass="width:100%" id="logCaseEditAlertDiv7Id">
			<m:table cellspacing="0" width="98%" id="logCaseEditAlertTab1Id">
				<m:tr id="logCaseEditAlertTab1R1Id">
					<m:td id="logCaseEditAlertTab1R1C1Id">
						<m:div styleClass="padb" id="logCaseEditAlertDiv17Id">
							<h:outputText value="#{msg['label.case.astrisk']}" styleClass="required" id="logCaseEditAlertOutTxt7Id"/>
							<h:outputText value="#{msg['label.case.alerts.dueDate']}"								styleClass="outputLabel" id="logCaseEditAlertOutTxt8Id"/>
							<m:br id="logCaseEditAlertBr1Id"/>
							<h:outputText value="#{logCaseDataBean.alertVO.dueDateStr}" id="logCaseEditAlertOutTxt9Id"/>
						</m:div>
					</m:td>
					<m:td id="logCaseEditAlertTab1R1C2Id">
						<m:div styleClass="padb" id="logCaseEditAlertDiv8Id">
							<h:outputText value="#{msg['label.case.astrisk']}" styleClass="required" id="logCaseEditAlertOutTxt10Id"/>
							<h:outputText value="#{msg['label.case.alerts.alertType']}"								styleClass="outputLabel" id="logCaseEditAlertOutTxt11Id"/>
							<m:br id="logCaseEditAlertBr2Id"/>
							<h:outputText value="#{logCaseDataBean.alertVO.alertTypeDesc}" id="logCaseEditAlertOutTxt12Id"/>
						</m:div>
					</m:td>
					<m:td id="logCaseEditAlertTab1R1C3Id">
						<m:div styleClass="padb" id="logCaseEditAlertDiv9Id">
							<h:outputText value="#{msg['label.case.astrisk']}" styleClass="required" id="logCaseEditAlertOutTxt13Id"/>
							<h:outputText value="#{msg['label.case.alerts.description']}"								styleClass="outputLabel" id="logCaseEditAlertOutTxt14Id"/>
							<m:br id="logCaseEditAlertBr3Id"/>
							<h:outputText value="#{logCaseDataBean.alertVO.description}" id="logCaseEditAlertOutTxt15Id"/>
						</m:div>
					</m:td>
					<m:td id="logCaseEditAlertTab1R1C4Id">
						<m:div styleClass="padb" id="logCaseEditAlertDiv10Id">
							<h:outputText value="#{msg['label.case.astrisk']}" styleClass="required" id="logCaseEditAlertOutTxt16Id"/>
							<h:outputText value="#{msg['label.case.alerts.notifyViaAlert']}"								styleClass="outputLabel" id="logCaseEditAlertOutTxt17Id"/>
							<m:br id="logCaseEditAlertBr4Id"/>
							<h:outputText value="#{logCaseDataBean.alertVO.notifyUserName}" id="logCaseEditAlertOutTxt18Id"/>
						</m:div>
					</m:td>
					<m:td id="logCaseEditAlertTab1R1C5Id">
						<m:div styleClass="padb" id="logCaseEditAlertDiv11Id">
							<h:outputText value="#{msg['label.case.astrisk']}" styleClass="required" id="logCaseEditAlertOutTxt19Id"/>
							<h:outputLabel for="status_3"								value="#{msg['label.case.alerts.status']}" id="logCaseEditAlertOutLbl1Id"/>
							<m:br id="logCaseEditAlertBr5Id"/>
							<h:selectOneMenu id="status_3" disabled="#{logCaseDataBean.disableScreenField}" value="#{logCaseDataBean.alertVO.status}">
								<f:selectItems value="#{logCaseDataBean.alertStatusList}"/>
							</h:selectOneMenu>
							<m:br />
							<h:message for="status_3" styleClass="errorMessage" id="logCaseEditAlertMsg1Id"/>
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
<m:div id="editAlertAuditDiv" rendered="#{logCaseDataBean.enableAuditLogForLogCase}">
			<f:subview id="editAlertAudit">
	<%--CR_ESPRD00373565_LOGCASE_23JUL2010	<jsp:include page="inc_logCaseAlertsAudit.jsp" />--%>
											<audit:auditTableSet id="alertsAuditId"
												value="#{logCaseDataBean.alertVO.auditKeyList}"
												numOfRecPerPage="10">
											</audit:auditTableSet>
			</f:subview>
</m:div><%--EOF CR_ESPRD00373565_LogCase_04AUG2010 --%>
		</m:div>
	</m:div>
</m:div>
