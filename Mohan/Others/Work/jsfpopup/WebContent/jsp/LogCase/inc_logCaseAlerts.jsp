<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<m:section styleClass="casebg" id="alertSec1Id">
	<m:legend id="alertLeg1Id">
		<h:outputLink			onclick="javascript:flagWarn=false;javascript:setHiddenValue('CMlogCase:caseAlerts:alertsHiddenID','minus');	 			toggle('showhide_alerts',2);				plusMinusToggle('showhide_alerts',this,'CMlogCase:caseAlerts:alertsHiddenID');				return false;"			id="plusMinus_alertsFalse" styleClass="plus">
			<h:outputText id="alerts_text"				value="#{msg['label.case.alerts.alerts']}" />
		</h:outputLink>
		<h:inputHidden id="alertsHiddenID"			value="#{logCaseDataBean.caseAlertsHidden}" />
	</m:legend>
 <%--<m:div
		rendered="#{logCaseDataBean.showCaseAlertsMessages}"
		styleClass="msgBox" id="alertDiv1Id">
		<h:messages layout="table" showDetail="true" styleClass="successMsg"			id="addAlertErrorMessage" showSummary="false">
		</h:messages>
	</m:div>--%>
	<m:div sid="showhide_alerts">
<m:div styleClass="msgBox"
		rendered="#{logCaseDataBean.showCaseAlertsMessages}">
		<h:outputText value="#{ent['label-sw-success']}"
			id="logcaseAddAlertMessage1" />
	</m:div>
		<m:table width="100%" id="alertTab1">
			<m:tr id="alertTab1R1Id">
				<m:td styleClass="rightCell" id="alertTab1R1C1Id">
                       
                        <%-- Infinite Computer Solutions FOR CR -1825--%>
                           <h:commandButton
                           		rendered="#{!logCaseDataBean.disableAddAlert && !logCaseDataBean.disableScreenField}"
                           		style="color:#fff; background-color:#518ACD; border-color:#ccc #000 #000 #ccc; font-size:11px; font-weight:bold;" 
                           		value="#{msg['label.case.alert.addalerts']}"					
                           		action="#{logCaseControllerBean.renderAddAlertsPage}" 
                           		id="addAlertsPageId"					 
                           		onmousedown="javascript:focusThis('alertcreatedDate');flagWarn=false;focusPage('CaseAlertsAddHeader');"	/>
						   <h:commandButton
                           		rendered="#{logCaseDataBean.disableAddAlert || logCaseDataBean.disableScreenField}"
                           		style="color:GrayText; background-color:#518ACD; border-color:#ccc #000 #000 #ccc; font-size:11px; font-weight:bold;" 
                           		value="#{msg['label.case.alert.addalerts']}"					
                           		action="#{logCaseControllerBean.renderAddAlertsPage}" 
                           		id="addAlertsPageId11"					 
                           		onmousedown="javascript:focusThis('alertcreatedDate');flagWarn=false;focusPage('CaseAlertsAddHeader');"	
                           		disabled="#{logCaseDataBean.disableAddAlert || logCaseDataBean.disableScreenField}"	/>                           
				</m:td>
			</m:tr>
		</m:table>
		<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1545--%>
		<t:dataTable value="#{logCaseDataBean.alertsVOList}"
			rendered="#{logCaseDataBean.showAlertsDataTable}"
			first="#{logCaseDataBean.alertsRowIndex}"
			var="caseAlertsSpanResult" styleClass="dataTable" cellspacing="0"
			width="100%" border="0" headerClass="tableHead"
			rowClasses="rowone,row_alt" rows="10" id="caseAlertsSection"
			rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
			rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
			rowOnClick="javascript:childNodes[0].lastChild.click();javascript:flagWarn=false;"
			onmousedown="javascript:flagWarn=false;javascript:focusThis('status_3');"
			rowIndexVar="rowIndex">
			<t:column id="dueDatecolumn1">
				<f:facet name="header">
					<h:panelGrid columns="2" id="caseAlertsSectionPanGrd1">
						<h:outputLabel value="#{msg['label.case.alert.dueDate']}" id="caseAlertsSectionOutLbl1"							for="caseAlertsDueDateCommandLink1" />
						<h:panelGroup id="caseAlertsSectionPanGrp1">
							<t:div id="CaseAlertsTDiv001" styleClass="alignLeft">
							<t:commandLink id="caseAlertsDueDateCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertsDueDateCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="caseAlertsSectiondueImg1"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseAlerts_DueDate" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAlertsTDiv002" styleClass="alignLeft">
							<t:commandLink id="caseAlertsDueDateCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertsDueDateCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="caseAlertsSectiondueImg2"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseAlerts_DueDate" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<t:commandLink id="caseAlertsDueDateLink3"					action="#{logCaseControllerBean.viewAlertsPage}" onclick="javascript:flagWarn=false;focusPage('CaseAlertsEditHeader');"	>
					<h:outputText value="#{caseAlertsSpanResult.dueDateStr}" id="caseAlertsSectiondueOutTxt1"/>
					<f:param name="alertIndexCode" value="#{rowIndex}"></f:param>
				</t:commandLink>
				</t:column>

			<t:column id="alertTypecolumn2">
				<f:facet name="header">
					<h:panelGrid columns="2" id="caseAlertsSectionPanGrd2">
						<h:outputLabel value="#{msg['label.case.alert.alertType']}"  id="caseAlertsSectionOutLbl2"							for="caseAlertsalertCommandLink1" />
						<h:panelGroup id="caseAlertsSectionPanGrp2">
							<t:div id="CaseAlertsTDiv003" styleClass="alignLeft">
							<t:commandLink id="caseAlertsalertCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertsalertCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="caseAlertsSectiondueImg3"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseAlerts_Type" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAlertsTDiv004" styleClass="alignLeft">
							<t:commandLink id="caseAlertsalertCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertsalertCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="caseAlertsSectiondueImg4"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseAlerts_Type" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{caseAlertsSpanResult.alertTypeDesc}" id="caseAlertsSectiondueOutTxt3"/>
			</t:column>

			<t:column id="descriptioncolumn3" >
				<f:facet name="header">
					<h:panelGrid columns="2" id="caseAlertsSectionPanGrd3">
						<h:outputLabel value="#{msg['label.case.alert.description']}"  id="caseAlertsSectionOutLbl3"							for="caseAlertdescriptionCommandLink1" />
						<h:panelGroup id="caseAlertsSectionPanGrp3">
							<t:div id="CaseAlertsTDiv005" styleClass="alignLeft">
							<t:commandLink id="caseAlertdescriptionCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertdescriptionCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="caseAlertsSectiondueImg5"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseAlert_Desc" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAlertsTDiv006" styleClass="alignLeft">
							<t:commandLink id="caseAlertdescriptionCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertdescriptionCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="caseAlertsSectiondueImg6"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseAlert_Desc" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{caseAlertsSpanResult.description}" id="caseAlertsSectiondueOutTxt4"/>
			</t:column>
			<t:column id="notifyViaAlertcolumn4" >
				<f:facet name="header">
					<h:panelGrid columns="2" id="caseAlertsSectionPanGrd4">
						<h:outputLabel value="#{msg['label.case.alerts.notifyViaAlert']}" id="caseAlertsSectionOutLbl4"							for="caseAlertnotifyviaAlertCommandLink1" />
						<h:panelGroup id="caseAlertsSectionPanGrp4">
							<t:div id="CaseAlertsTDiv007" styleClass="alignLeft">
							<t:commandLink id="caseAlertnotifyviaAlertCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertnotifyviaAlertCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="caseAlertsSectiondueImg7"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseAlert_NotifyviaAlert" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAlertsTDiv008" styleClass="alignLeft">
							<t:commandLink id="caseAlertnotifyviaAlertCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertnotifyviaAlertCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="caseAlertsSectiondueImg8"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseAlert_NotifyviaAlert" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{caseAlertsSpanResult.notifyUserName}" id="caseAlertsSectiondueOutTxt5"/>
			</t:column>
			<t:column id="statuscolumn5" >
				<f:facet name="header">
					<h:panelGrid columns="2" id="caseAlertsSectionPanGrd5">
						<h:outputLabel value="#{msg['label.case.alerts.status']}" id="caseAlertsSectionOutLbl5"							for="caseAlertstatusCommandLink1" />
						<h:panelGroup id="caseAlertsSectionPanGrp5">
							<t:div id="CaseAlertsTDiv009" styleClass="alignLeft">
							<t:commandLink id="caseAlertstatusCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertstatusCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="caseAlertsSectiondueImg10"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseAlert_Status" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAlertsTDiv010" styleClass="alignLeft">
							<t:commandLink id="caseAlertstatusCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseAlerts}" onmousedown="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseAlertstatusCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="caseAlertsSectiondueImg11"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseAlert_Status" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{caseAlertsSpanResult.statusDesc}" id="caseAlertsSectiondueOutTxt6"/>
			</t:column>
		</t:dataTable>
		<t:dataScroller for="caseAlertsSection" paginator="true"
			onclick="javascript:flagWarn=false;focusPage('CaseAlertsHeader');"
			paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
			immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
			firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
			rowsCountVar="rowsCount" styleClass="dataScroller" id="caseAlertsSectionScrolId">
			<f:facet name="previous">
				<h:outputText styleClass="underline" id="caseAlertsSectionScrolPrevId"					value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"></h:outputText>
			</f:facet>
			<f:facet name="next">
				<h:outputText styleClass="underline" id="caseAlertsSectionScrolNxtId"					value=" #{msg['label.greaterthan']} "					rendered="#{pageIndex < pageCount}"></h:outputText>
			</f:facet>
			<h:outputText rendered="#{rowsCount > 0}" id="caseAlertsSectionScrolRowsCntId"
				value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
				styleClass="dataScrollerText" />
		</t:dataScroller>
		<m:br id="caseAlertBr111"/>
		<h:dataTable var="dummyCaseAlerts"			rendered="#{!logCaseDataBean.showAlertsDataTable}"			styleClass="dataTable" cellspacing="0" width="100%" border="0"			headerClass="tableHead" rowClasses="rowone,row_alt"			id="CaseAlertsdummyTable">
			<t:column id="dummyCaseAlertscolumn1">
				<f:facet name="header">
					<h:outputText value="#{msg['label.case.alert.dueDate']}" id="CaseAlertsdummyTableDueDtId"/>
				</f:facet>
			</t:column>
			<t:column id="dummyCaseAlertscolumn2">
				<f:facet name="header">
					<h:outputText value="#{msg['label.case.alert.alertType']}" id="CaseAlertsdummyTableAlertTypeId"/>
				</f:facet>
			</t:column>
			<t:column id="dummyCaseAlertscolumn3">
				<f:facet name="header">
					<h:outputText value="#{msg['label.case.alert.description']}" id="CaseAlertsdummyTableDescId"/>
				</f:facet>
			</t:column>
			<t:column id="dummyCaseAlertscolumn4">
				<f:facet name="header">
					<h:outputText value="#{msg['label.case.alerts.notifyViaAlert']}" id="CaseAlertsdummyTableNotById"/>
				</f:facet>
			</t:column>
			<t:column id="dummyCaseAlertscolumn5">
				<f:facet name="header">
					<h:outputText value="#{msg['label.case.alerts.status']}" id="CaseAlertsdummyTableStatusId"/>
				</f:facet>
			</t:column>
		</h:dataTable>
		<m:table styleClass="dataTable" width="100%" border="0"
			rendered="#{!logCaseDataBean.showAlertsDataTable}" id="noDataDisplayTabId"> 
			<m:tr id="noDataDisplayTabRowId">
				<m:td align="center" id="noDataDisplayTabColId">
					<h:outputText value="#{msg['value.noData']}" id="noDataDisplayTabMsgId"/>
				</m:td>
			</m:tr>
		</m:table>
		<m:br id="caseAlertBr112"/>
		<m:div styleClass="clearl" id="Div111">
		</m:div>
		<m:div id="addalert" rendered="#{logCaseDataBean.showAddAlers}">
		<f:subview id="logCaseAddAlertId">
			<m:anchor name="CaseAlertsAddHeader"></m:anchor>
			<jsp:include page="inc_logCaseAddAlerts.jsp" />
		</f:subview>
		</m:div>
		<m:div id="editAlert" rendered="#{logCaseDataBean.showEditAlers}">
		<f:subview  id="logCaseeditAlertId">
			<m:anchor name="CaseAlertsEditHeader"></m:anchor>
			<jsp:include page="inc_logCaseEditAlerts.jsp" />
		</f:subview>	
		</m:div>
	</m:div>
</m:section>
