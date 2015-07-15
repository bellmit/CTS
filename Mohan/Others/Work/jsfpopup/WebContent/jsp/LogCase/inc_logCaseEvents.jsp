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
<m:section id="logCaseEventsSect1" styleClass="casebg">
	<m:legend id="logCaseEventsLegnd1">
		<h:outputLink			onclick="javascript:flagWarn=false;javascript:setHiddenValue('CMlogCase:caseEvents:caseEventsHiddenID','minus');	 				toggle('showhide_caseEvents',2);					plusMinusToggle('showhide_caseEvents',this,'CMlogCase:caseEvents:caseEventsHiddenID');					return false;"			id="plusMinus_caseEventsFalse" styleClass="plus">
			<h:outputText id="caseEvents_text"				value="#{msg['title.case.caseEvents']}" />
		</h:outputLink>
		<h:inputHidden id="caseEventsHiddenID"			value="#{logCaseDataBean.caseEventsHidden}" />
	</m:legend>
	<!-- Begin - Added this code for displaying the delete success message for the defect id ESPRD00712855_19OCT2011 -->
	
	
	<m:div sid="showhide_caseEvents">
	<m:div styleClass="msgBox" id="divId6"
		rendered="#{logCaseDataBean.showCaseEventsMessages}">
		<h:outputText value="#{ent['label-sw-success']}" id="addEventsErrorMessage"/>
	</m:div>
	<m:div styleClass="msgBox" id="logCaseStepsMainDivSectionIDDel122"
				rendered="#{logCaseDataBean.caseEventDeleteMsgFlag}">
				<h:outputText value="#{ent['err-sw-record-delete-success']}" id="deleteStepErrorMessage"/>
	</m:div>
		<m:table id="logCaseEventsTable1" width="100%">
			<m:tr  id="logCaseEventsTr1">
				<m:td id="logCaseEventsTd1" styleClass="rightCell">
					<h:commandButton 
						rendered="#{!logCaseDataBean.disableAddCaseEvents && !logCaseDataBean.disableScreenField}"
						style="color:#fff; background-color:#518ACD; border-color:#ccc #000 #000 #ccc; font-size:11px; font-weight:bold;"
				    	id="addCaseEventId"						
				    	value="#{msg['label.case.caseEvents.addCaseEvent']}"						
				    	action="#{logCaseControllerBean.renderAddCaseEventsPage}" 
				    	onmousedown="javascript:flagWarn=false;focusPage('addCaseEventPageFocus');focusThis('caseEventsType');"/>
					<h:commandButton 
						rendered="#{logCaseDataBean.disableAddCaseEvents || logCaseDataBean.disableScreenField}"
						style="color:GrayText; background-color:#518ACD; border-color:#ccc #000 #000 #ccc; font-size:11px; font-weight:bold;"
					    id="addCaseEventId11"						
					    value="#{msg['label.case.caseEvents.addCaseEvent']}"						
				    	action="#{logCaseControllerBean.renderAddCaseEventsPage}" 
					    onmousedown="javascript:flagWarn=false;focusThis('addCaseEventPageFocus');focusThis('caseEventsType');"						  
				    	disabled="#{logCaseDataBean.disableAddCaseEvents || logCaseDataBean.disableScreenField}"/>
				</m:td>
			</m:tr>
		</m:table>
		<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1545--%>
		<t:dataTable value="#{logCaseDataBean.caseEventsVOList}"  columnClasses="columnClass"
			rendered="#{logCaseDataBean.showCaseEventsDataTable}" footerClass="footerClass"
		    first="#{logCaseDataBean.caseEventsRowIndex}"
			var="caseEventsSpanResult" styleClass="dataTable"
			width="100%" border="0" headerClass="headerClass" 
			rowClasses="row_alt,row"  rows="10" id="caseEventsTable"
			rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
			rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
			rowOnClick="javascript:childNodes[0].lastChild.click();"
			onmousedown="javascript:flagWarn=false;focusPage('caseEventsEditHeader');focusThis('type2');" rowIndexVar="rowIndex">
		<t:column id="caseEventscolumn1">
				<f:facet name="header">
					<h:panelGrid id="logCaseEventsPanelGrid1" columns="2">
						<h:outputLabel id="logCaseEventsLabel1" value="#{msg['label.case.caseEvents.description']}"							for="caseEventDescCommandLink1" />
						<h:panelGroup id="logCaseEventsPanel1">
							<t:div id="CaseEventsTDiv001" styleClass="alignLeft">
							<t:commandLink id="caseEventDescCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}"								rendered="#{logCaseDataBean.imageRender != 'caseEventDescCommandLink1'}"  onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');">
								<m:div id="logCaseEventsGrp1" title="#{msg['alt.for.ascending']}"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseEvent_Desc" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseEventsTDiv002" styleClass="alignLeft">
							<t:commandLink id="caseEventDescCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventDescCommandLink2'}">
								<m:div id="logCaseEventsGrp2" title="#{msg['alt.for.descending']}"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseEvent_Desc" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<t:commandLink id="PRGCMGTCL109" action="#{logCaseControllerBean.viewCaseEventsPage}" onclick="javascript:flagWarn=false;" >
					<f:param name="desc" value="#{caseEventsSpanResult.desc}" />
					<%-- Major Save code Starts --%>
					<f:param name="rowindex" value="#{rowIndex}" />
					<%-- Ends --%>
					<h:outputText id="logCaseEventsOut1" value="#{caseEventsSpanResult.desc}" />
				</t:commandLink>
				
			</t:column>
			<t:column id="caseEventscolumn2">
				<f:facet name="header">
					<h:panelGrid id="logCaseEventsPanelGrid2" columns="2">
						<h:outputLabel id="logCaseEventsLabel2" value="#{msg['label.case.caseEvents.eventDate']}"							for="caseEventDateCommandLink1" />
						<h:panelGroup id="logCaseEventsPanel2">
							<t:div id="CaseEventsTDiv003" styleClass="alignLeft">
							<t:commandLink id="caseEventDateCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventDateCommandLink1'}">
								<m:div id="logCaseEventsGrp3" title="#{msg['alt.for.ascending']}"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseEvent_Date" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseEventsTDiv004" styleClass="alignLeft">
							<t:commandLink id="caseEventDateCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventDateCommandLink2'}">
								<m:div id="logCaseEventsGrp4" title="#{msg['alt.for.descending']}"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseEvent_Date" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="logCaseEventsOut3" value="#{caseEventsSpanResult.eventDateStr}" />
			</t:column>

			<t:column id="caseEventscolumn3">
				<f:facet name="header">
					<h:panelGrid id="logCaseEventsPanelGrid3" columns="2">
						<h:outputLabel id="logCaseEventsLabel3" value="#{msg['label.case.routing.time']}"							for="caseEventstimeCommandLink1" />
						<h:panelGroup id="logCaseEventsPanel3">
							<t:div id="CaseEventsTDiv005" styleClass="alignLeft">
							<t:commandLink id="caseEventstimeCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventstimeCommandLink1'}">
								<m:div id="logCaseEventsGrp5" title="#{msg['alt.for.ascending']}"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseEvents_Time" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseEventsTDiv006" styleClass="alignLeft">
							<t:commandLink id="caseEventstimeCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventstimeCommandLink2'}">
								<m:div id="logCaseEventsGrp6" title="#{msg['alt.for.descending']}"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseEvents_Time" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="logCaseEventsOut4" value="#{caseEventsSpanResult.time}" />	
											
				<h:outputText id="logCaseEventsOut5" value="#{caseEventsSpanResult.amPM}" />				
			</t:column>
			<t:column id="caseEventscolumn4">
				<f:facet name="header">
					<h:panelGrid id="logCaseEventsPanelGrid4" columns="2">
						<h:outputLabel id="logCaseEventsLabel4" value="#{msg['label.case.caseEvents.status']}"							for="caseEventsStatusCommandLink1" />
						<h:panelGroup id="logCaseEventsPanel4">
							<t:div id="CaseEventsTDiv007" styleClass="alignLeft">
							<t:commandLink id="caseEventsStatusCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventsStatusCommandLink1'}">
								<m:div id="logCaseEventsGrp7" title="#{msg['alt.for.ascending']}"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseEvents_Status" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseEventsTDiv008" styleClass="alignLeft">
							<t:commandLink id="caseEventsStatusCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventsStatusCommandLink2'}">
								<m:div id="logCaseEventsGrp8" title="#{msg['alt.for.descending']}"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseEvents_Status" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="logCaseEventsOut6" value="#{caseEventsSpanResult.statusCdDescription}" />
			</t:column>
			<t:column id="caseEventscolumn5">
				<f:facet name="header">
					<h:panelGrid id="logCaseEventsPanelGrid5" columns="2">
						<h:outputLabel id="logCaseEventsLabel5"							value="#{msg['label.case.caseEvents.dispositionDate']}"							for="caseEventsdispositionCommandLink1" />
						<h:panelGroup id="logCaseEventsPanel5">
							<t:div id="CaseEventsTDiv009" styleClass="alignLeft">
							<t:commandLink id="caseEventsdispositionCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventsdispositionCommandLink1'}">
								<m:div id="logCaseEventsGrp9" title="#{msg['alt.for.ascending']}"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseEvents_disDate" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseEventsTDiv010" styleClass="alignLeft">
							<t:commandLink id="caseEventsdispositionCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventsdispositionCommandLink2'}">
								<m:div id="logCaseEventsGrp10" title="#{msg['alt.for.descending']}"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseEvents_disDate" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="logCaseEventsOut7" value="#{caseEventsSpanResult.dispositionDateStr}" />
			</t:column>
			<t:column id="caseEventscolumn6">
				<f:facet name="header">
					<h:panelGrid id="logCaseEventsPanelGrid6" columns="2">
						<h:outputLabel id="logCaseEventsLabel6"							value="#{msg['label.case.caseEvents.notifyViaAlert']}"							for="caseEventsnotifyViaAlertCommandLink1" />
						<h:panelGroup id="logCaseEventsPanel6">
							<t:div id="CaseEventsTDiv011" styleClass="alignLeft">
							<t:commandLink id="caseEventsnotifyViaAlertCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventsnotifyViaAlertCommandLink1'}">
								<m:div id="logCaseEventsGrp11" title="#{msg['alt.for.ascending']}"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseEvents_notifyViaAlert" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseEventsTDiv012" styleClass="alignLeft">
							<t:commandLink id="caseEventsnotifyViaAlertCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventsnotifyViaAlertCommandLink2'}">
								<m:div id="logCaseEventsGrp12" title="#{msg['alt.for.descending']}"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseEvents_notifyViaAlert" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="logCaseEventsOut8" value="#{caseEventsSpanResult.notifyViaAlertName}" />
			</t:column>
			<t:column id="caseEventscolumn7">
				<f:facet name="header">
					<h:panelGrid id="logCaseEventsPanelGrid7" columns="2">
						<h:outputLabel id="logCaseEventsLabel7"							value="#{msg['label.case.caseEvents.alertBasedOn']}"							for="caseEventsalertBasedOnCommandLink1" />
						<h:panelGroup id="logCaseEventsPanel7">
							<t:div id="CaseEventsTDiv013" styleClass="alignLeft">
							<t:commandLink id="caseEventsalertBasedOnCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventsalertBasedOnCommandLink1'}">
								<m:div id="logCaseEventsGrp13" title="#{msg['alt.for.ascending']}"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseEvents_alertBasedOn" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseEventsTDiv014" styleClass="alignLeft">
							<t:commandLink id="caseEventsalertBasedOnCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventsalertBasedOnCommandLink2'}">
								<m:div id="logCaseEventsGrp14" title="#{msg['alt.for.descending']}"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseEvents_alertBasedOn" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="logCaseEventsOut9" value="#{caseEventsSpanResult.alertBasedOnDescription}" />
			</t:column>
			<t:column id="caseEventscolumn8">
				<f:facet name="header">
					<h:panelGrid id="logCaseEventsPanelGrid8" columns="2">
						<h:outputLabel id="logCaseEventsLabel8"							value="#{msg['label.case.caseEvents.sendAlertDays']}"							for="caseEventssendAlertDaysCommandLink1" />
						<h:panelGroup id="logCaseEventsPanel8">
							<t:div id="CaseEventsTDiv015" styleClass="alignLeft">
							<t:commandLink id="caseEventssendAlertDaysCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventssendAlertDaysCommandLink1'}">
								<m:div id="logCaseEventsGrp15" title="#{msg['alt.for.ascending']}"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="caseEvents_sendAlertDays" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseEventsTDiv016" styleClass="alignLeft">
							<t:commandLink id="caseEventssendAlertDaysCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCaseEvents}" onmousedown="javascript:flagWarn=false;focusPage('caseEventsHeader');"								rendered="#{logCaseDataBean.imageRender != 'caseEventssendAlertDaysCommandLink2'}">
								<m:div id="logCaseEventsGrp16" title="#{msg['alt.for.descending']}"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseEvents_sendAlertDays" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="logCaseEventsOut10" value="#{caseEventsSpanResult.sendAlertDaysCd}" />
				<h:outputText id="logCaseEventsOut11" value = " "></h:outputText>
				<h:outputText id="logCaseEventsOut12" value="#{caseEventsSpanResult.afterOrBeforeCd}" />				
			</t:column>
		</t:dataTable>
		<t:dataScroller id="CMGTTOMDS26" for="caseEventsTable" paginator="true"
			onclick="javascript:flagWarn=false;focusPage('caseEventsHeader');"
			paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
			immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
			firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
			rowsCountVar="rowsCount" styleClass="dataScroller">
			<f:facet name="previous">
				<h:outputText id="logCaseEventsOut13" styleClass="underline"					value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"></h:outputText>
			</f:facet>
			<f:facet name="next">
				<h:outputText id="logCaseEventsOut14" styleClass="underline"					value=" #{msg['label.greaterthan']} "					rendered="#{pageIndex < pageCount}"></h:outputText>
			</f:facet>
			<h:outputText id="logCaseEventsOut15" rendered="#{rowsCount > 0}"
				value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
				styleClass="dataScrollerText" />
		</t:dataScroller>
		<m:br id="logCaseEventsBr9" />
		<m:br id="logCaseEventsBr10" />
		<h:dataTable var="dummyCaseEvents"			rendered="#{!logCaseDataBean.showCaseEventsDataTable}"			styleClass="dataTable" cellspacing="0" width="100%" border="0"			headerClass="tableHead" rowClasses="rowone,row_alt"			id="CaseEventsdummyTable">
			<t:column id="dummyCaseEventscolumn1">
				<f:facet name="header">
					<h:outputText id="logCaseEventsOut16" value="#{msg['label.case.caseEvents.description']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyCaseEventscolumn2">
				<f:facet name="header">
					<h:outputText id="logCaseEventsOut17" value="#{msg['label.case.caseEvents.eventDate']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyCaseEventscolumn3">
				<f:facet name="header">
					<h:outputText id="logCaseEventsOut18" value="#{msg['label.case.routing.time']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyCaseEventscolumn4">
				<f:facet name="header">
					<h:outputText id="logCaseEventsOut19" value="#{msg['label.case.caseEvents.status']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyCaseEventscolumn5">
				<f:facet name="header">
					<h:outputText id="logCaseEventsOut20"						value="#{msg['label.case.caseEvents.dispositionDate']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyCaseEventscolumn6">
				<f:facet name="header">
					<h:outputText id="logCaseEventsOut21"						value="#{msg['label.case.caseEvents.notifyViaAlert']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyCaseEventscolumn7">
				<f:facet name="header">
					<h:outputText id="logCaseEventsOut22" value="#{msg['label.case.caseEvents.alertBasedOn']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyCaseEventscolumn8">
				<f:facet name="header">
					<h:outputText id="logCaseEventsOut23" value="#{msg['label.case.caseEvents.sendAlertDays']}" />
				</f:facet>
			</t:column>
		</h:dataTable>
		<m:table id="logCaseEventsTable2" styleClass="dataTable" width="100%" border="0"
			rendered="#{!logCaseDataBean.showCaseEventsDataTable}">
			<m:tr  id="logCaseEventsTr2">
				<m:td id="logCaseEventsTd2" align="center">
					<h:outputText id="logCaseEventsOut24" value="#{msg['value.noData']}" />
				</m:td>
			</m:tr>
		</m:table>
		<m:br id="caseEventAddBR14" />
		<m:div id="logCaseEventAddDiv1" styleClass="clearl">
		</m:div>
		<m:anchor name="addCaseEventPageFocus"></m:anchor>
		<m:div id="caseEventsNewCaseEvent"
			rendered="#{logCaseDataBean.showAddCaseEvents}">
			<f:subview id="addCaseEvents"> 
			<jsp:include page="inc_logCaseAddCaseEvents.jsp" />
			</f:subview>
		</m:div>
		<m:br id="caseEventEditBR14" />
		<m:div id="CaseEventEditMDivClear2" styleClass="clearl">
		</m:div>
		<m:anchor name="caseEventsEditHeader"></m:anchor>
		<m:div id="caseEventsEditCaseEvent"
			rendered="#{logCaseDataBean.showEditCaseEvents}">
			<f:subview id="editCaseEvents"> 
			<jsp:include page="inc_logCaseEditCaseEvents.jsp" />
			</f:subview>
		</m:div>

	</m:div>
</m:section>

