<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543--%>
<m:div styleClass="moreInfo" rendered="#{logCaseDataBean.renderedalertsFlag}" id="logcaseAlertAddDiv1Id">
	<m:div styleClass="moreInfoBar" id="logcaseAlertAddDiv2Id">
		<m:div styleClass="infoTitle" id="logcaseAlertAddDiv3Id">
			<h:outputText value="#{msg['label.case.alert.newAlert']}" id="logcaseAddAlertOutTxt18Id"/>
		</m:div>
		<m:div styleClass="infoActions" id="logcaseAlertAddDiv4Id">
			<t:commandLink id="saveAlertsId" action="#{logCaseControllerBean.saveAlerts}" onmousedown="javascript:flagWarn=false;">
				<h:outputText value="#{msg['label.case.save']}" id="logcaseAddAlertOutTxt19Id" styleClass="strong"/>
			</t:commandLink>
			<h:outputText escape="false" value="&nbsp;" id="logcaseAlertAddOutTxt1Id"/><h:outputText value="|" id="logcaseAlertAddOutTxt2Id"/><h:outputText escape="false" value="&nbsp;" id="logcaseAlertAddOutTxt3Id"/>
			<t:commandLink id="resetAlertsPageId" action="#{logCaseControllerBean.resetAlertsPage}" onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);focusPage('CaseAlertsAddHeader');" >
				<h:outputText value="#{msg['label.case.reset']}" id="logcaseAlertAddOutTxt17Id"/>
			</t:commandLink>
			<h:outputText escape="false" value="&nbsp;" id="logcaseAlertAddOutTxt4Id"/><h:outputText value="|" id="logcaseAlertAddOutTxt5Id"/><h:outputText escape="false" value="&nbsp;" id="logcaseAlertAddOutTxt6Id"/>
			<t:commandLink id="cancelAlertsPageId" action="#{logCaseControllerBean.cancelAlertsPage}" onmousedown="javascript:focusThis('addAlertsPageId');focusPage('CaseAlertsHeader');">
				<h:outputText value="#{msg['label.case.cancel']}" id="logcaseAlertAddOutTxt7Id"/>
			</t:commandLink>
		</m:div>
	</m:div>
	<m:div styleClass="moreInfoContent" id="logcaseAlertAddDiv5Id">
		<%--Modified for defect ESPRD00446264 starts--%>
		
		<%--Defect ESPRD00446264 Fix ends--%>
		<m:div styleClass="width:100%" id="logcaseAlertAddDiv6d">
			<m:table cellspacing="0" width="98%" id="logcaseAlertAddDiv6Tab1Id">
				<m:tr id="logcaseAlertAddDiv6Tab1R1Id">
					<m:td id="logcaseAlertAddDiv6Tab1R1C1Id">
						<m:div styleClass="padb" id="logcaseAlertAddDiv6Tab1R1C1Div1Id">
							<h:outputText value="#{msg['label.case.astrisk']}" styleClass="required" id="logcaseAlertAddOutTxt8Id"/>
							<h:outputLabel value="#{msg['label.case.alerts.dueDate']}" for="alertcreatedDate" id="logcaseAlertAddOutLbl1Id"/>
							<m:br id="logcaseAlertAddBr1Id"/>
							<m:inputCalendar id="alertcreatedDate" size="10"
								monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false"
								popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
								value="#{logCaseDataBean.alertVO.dueDateStr}" />
							<m:br id="logcaseAlertAddBr22Id"/>
							<h:message for="alertcreatedDate" styleClass="errorMessage" id="logcaseAlertAddOmsg1Id"/>
						</m:div>
					</m:td>
					<m:td id="logcaseAlertAddDiv6Tab1R1C2Id">
						<m:div styleClass="padb" id="logcaseAlertAddDiv6Tab1R1C2Div1Id">
							<h:outputText value="#{msg['label.case.astrisk']}" styleClass="required" id="logcaseAlertAddOutTxt9Id"/>
							<h:outputLabel for="alerttype" id="logcaseAlertAddOutLbl2Id"								value="#{msg['label.case.alerts.alertType']}" />
							<m:br id="logcaseAlertAddBr2Id"/>
							<h:selectOneMenu id="alerttype" value="#{logCaseDataBean.alertVO.alertType}">
								<f:selectItems value="#{logCaseDataBean.alertType}" id="logcaseAlertAddSelItm1Id"/>
							</h:selectOneMenu>
							<m:br />
							<h:message for="alerttype" styleClass="errorMessage" id="logcaseAlertAddOmsg2Id"/>
						</m:div>
					</m:td>
					<m:td id="logcaseAlertAddDiv6Tab1R1C3Id">
						<h:outputText value="#{msg['label.case.astrisk']}" styleClass="required" id="logcaseAlertAddOutTxt10Id"/>
						<h:outputLabel for="Alertdescription" id="logcaseAlertAddOutLbl3Id"							value="#{msg['label.case.alerts.description']}" />
						<m:br />
						<h:inputText id="Alertdescription" size="30" value="#{logCaseDataBean.alertVO.description}"/>
						<m:br id="logcaseAlertAddBr3Id"/>
						<h:message for="Alertdescription" styleClass="errorMessage" id="logcaseAlertAddOmsg3Id"/>
					</m:td>
					<m:td id="logcaseAlertAddDiv6Tab1R1C4Id">
						<m:div styleClass="padb">
							<h:outputText value="#{msg['label.case.astrisk']}" styleClass="required" id="logcaseAlertAddOutTxt11Id"/>
							<h:outputLabel for="AlertnotifyViaAlert" id="logcaseAlertAddOutLbl4Id"								value="#{msg['label.case.alerts.notifyViaAlert']}" />
							<m:br id="logcaseAlertAddBr4Id"/>
							<h:selectOneMenu id="AlertnotifyViaAlert" value="#{logCaseDataBean.alertVO.notifyViaAlert}">
								<f:selectItems value="#{logCaseDataBean.userList}" id="logcaseAlertAddSelItm2Id"/>
							</h:selectOneMenu>
							<m:br />
							<h:message for="AlertnotifyViaAlert" styleClass="errorMessage" id="logcaseAlertAddOmsg4Id"/>
						</m:div>
					</m:td>
					<m:td colspan="2" id="logcaseAlertAddDiv6Tab1R1C5Id">
						<h:outputLabel for="Alertstatus_2" id="logcaseAlertAddOutLbl5Id"							value="#{msg['label.case.alerts.status']}" />
						<m:br id="logcaseAlertAddBr5Id"/>
						<h:selectOneMenu id="Alertstatus_2" value="#{logCaseDataBean.alertVO.status}">
							<f:selectItems value="#{logCaseDataBean.alertStatusList}" id="logcaseAlertAddSelItm3Id"/>
						</h:selectOneMenu>
						<m:br id="logcaseAlertAddBr6Id"/>
						<h:message for="Alertstatus_2" styleClass="errorMessage" id="logcaseAlertAddOmsg5Id"/>
					</m:td>
				</m:tr>
			</m:table>
		</m:div>
	</m:div>
</m:div>
