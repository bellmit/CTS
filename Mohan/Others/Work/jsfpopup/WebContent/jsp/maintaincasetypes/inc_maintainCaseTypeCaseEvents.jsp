<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<%--Added for the defect ESPRD00853112 starts--%>
<m:div styleClass="msgBox"
	rendered="#{CaseTypeDataBean.showEventsSuccessMsg}">
	<%--Modified for defect ESPRD00357599 ends--%>
	<h:outputText id="PRGCMGTOT1089" value="#{ent['label-sw-success']}" />
</m:div>
<m:div styleClass="msgBox"
	rendered="#{CaseTypeDataBean.showEventsDeleteMsg}">

	<h:outputText id="PRGCMGTOT108911"
		value="#{ent['err-sw-record-delete-success']}" />
</m:div>
<%--Defect ESPRD00853112 ends--%>
<m:table id="PROVIDERMMT20120731164811431" styleClass="tableBar" width="100%">
	<m:tr>
		<%-- Label left --%>
		<%-- changed property from updateAddLink to disableAddLink  --%>
		<m:td styleClass="tableAction">
			<h:commandButton id="PRGCMGTCB40" styleClass="formBtn"
				onclick="javascript:flagWarn=false;javascript:addeditrow('maintaincasetype');"
				onmousedown="javascript:focusPage('maintainCaseTypeAddCaseEvents');"
				disabled="#{CaseTypeDataBean.disableAddCaseEvent}"
				action="#{CaseTypeControllerBean.showAddCaseEvent}"
				value="#{msg['label.casetype.caseevents.addCaseEvent']}" />
		</m:td>
	</m:tr>
</m:table>

<t:dataTable cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass"
	footerClass="footerClass" rowClasses="row_alt,row" id="caseeventtable"
	width="100%" var="caseevent" rows="10"
	value="#{CaseTypeDataBean.caseTypeEventsList}"
	first="#{CaseTypeDataBean.startIndexForCaseTypeEvents}"
	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
	rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
	rowOnClick="javascript:flagWarn=false;firstChild.lastChild.click();"
	rowIndexVar="index" rendered="#{CaseTypeDataBean.noCaseEventsData}">

	<t:column id="includecol1">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD18" columns="2">
				<h:outputLabel id="PRGCMGTOLL492" for="includeCommandLink1"
					value="#{msg['label.casetype.caseevents.include']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP18">
					<m:div>
						<t:commandLink id="includeCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'includeCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811432" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="includeValue" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="includeCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'includeCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811433" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="includeValue" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:graphicImage id="PROVIDERGI20120731164811434" alt="#{cont['label.ref.yes']}" title="#{cont['label.ref.yes']}"
			styleClass="ind_check_yes" width="15"
			rendered="#{caseevent.eventImgInclude}" url="/images/x.gif">
		</h:graphicImage>
		<t:commandLink id="editcaseevent"
			onmousedown="javascript:flagWarn=false;"
			action="#{CaseTypeControllerBean.getCaseEventDetails}">
			<f:param name="rowIndex" value="#{index}"></f:param>
		</t:commandLink>
	</t:column>
	<t:column id="typecol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD19" columns="2">
				<h:outputLabel id="PRGCMGTOLL493" for="typeCommandLink1"
					value="#{msg['label.casetype.caseevents.type']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP19">
					<m:div>
						<t:commandLink id="typeCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'typeCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811435" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="ce_typeValue" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="typeCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'typeCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811436" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="ce_typeValue" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<%--ESPRD00527888_UC-PGM-CRM-48_29SEP2010
		<h:outputText id="editcaseeventVal"				value="#{caseevent.dfltCaseTypeEventTypeCode}"				style="color: blue" />	--%>
		<h:outputText id="editcaseeventVal"
			value="#{caseevent.dfltCaseTypeEventTypeCodeDesc}"
			style="color: blue" />
		<%--EOF ESPRD00527888_UC-PGM-CRM-48_29SEP2010 --%>
		<%--
		<h:commandLink id="editcaseevent1"			action="#{CaseTypeControllerBean.getCaseEventDetails}">
		</h:commandLink>
		 --%>
	</t:column>
	<t:column id="notifyalertcol1">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD20" columns="2">
				<h:outputLabel id="PRGCMGTOLL494" for="notifyalerCommandLink1"
					value="#{msg['label.casetype.caseevents.notifyViaAlert']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP20">
					<m:div>
						<t:commandLink id="notifyalerCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'notifyalerCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811437" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="ce_notifyalert" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="notifyalerCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'notifyalerCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811438" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="ce_notifyalert" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<%--<h:outputText id="PRGCMGTOT1063" value="#{caseevent.dfltNotfyAlertUserId}" />
		<h:outputText id="PRGCMGTOT1064" value="#{caseevent.dfltNotfyAlertUserId}" />
		<h:outputText id="PRGCMGTOT1065" value=" - "			rendered="#{caseevent.dfltNotfyAlertUserName != null}" />--%>
		<h:outputText id="PRGCMGTOT1066"
			value="#{caseevent.dfltNotfyAlertUserName}" />
	</t:column>
	<t:column id="alertbasedcol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD21" columns="2">
				<h:outputLabel id="PRGCMGTOLL495" for="alertbasedCommandLink1"
					value="#{msg['label.casetype.caseevents.alertBasedOn']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP21">
					<m:div>
						<t:commandLink id="alertbasedCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'alertbasedCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811439" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="ce_alertBased" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="alertbasedCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'alertbasedCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811440" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="ce_alertBased" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1067"
			value="#{caseevent.dfltAlertBasedOnDesc}" />

	</t:column>

	<t:column id="defaultsendcol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD22" columns="2">
				<h:outputLabel id="PRGCMGTOLL496" for="defaultsendCommandLink1"
					value="#{msg['label.casetype.casesteps.sendAlert']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP22">
					<m:div>
						<t:commandLink id="defaultsendCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'defaultsendCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811441" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="ce_dfltSendAlertBased" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="defaultsendCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'defaultsendCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811442" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="ce_dfltSendAlertBased" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1068"
			value="#{caseevent.dfltSendAlertDaysBeforeOrAfter}" />
	</t:column>
	<t:column id="templatecol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD23" columns="2">
				<h:outputLabel id="PRGCMGTOLL497" for="templateCommandLink1"
					value="#{msg['label.casetype.casesteps.defaultTemplate']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP23">
					<m:div>
						<t:commandLink id="templateCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'templateCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811443" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="ce_dfltTemplate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="templateCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseEvents}"
							rendered="#{CaseTypeDataBean.imageRender != 'templateCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811444" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="ce_dfltTemplate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1069"
			value="#{caseevent.dfltCotsLtrTmltKeyData}" />

	</t:column>


</t:dataTable>
<%-- If there is No data in table --%>
<h:dataTable id="PRGCMGTDT1" cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass"
	footerClass="footerClass" rowClasses="row_alt,row" width="100%"
	rendered="#{!CaseTypeDataBean.noCaseEventsData}">

	<h:column id="PRGCMGTC43">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1070"
				value="#{msg['label.casetype.caseevents.include']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC44">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1071"
				value="#{msg['label.casetype.caseevents.type']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC45">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1072"
				value="#{msg['label.casetype.caseevents.notifyViaAlert']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC46">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1073"
				value="#{msg['label.casetype.caseevents.alertBasedOn']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC47">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1074"
				value="#{msg['label.casetype.casesteps.sendAlert']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC48">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1075"
				value="#{msg['label.casetype.casesteps.defaultTemplate']}">
			</h:outputText>
		</f:facet>
	</h:column>
</h:dataTable>
<m:table id="PROVIDERMMT20120731164811445" styleClass="dataTable" cellspacing="0" width="100%"
	rendered="#{!CaseTypeDataBean.noCaseEventsData}">
	<m:tr>
		<m:td align="center">
			<h:outputText id="PRGCMGTOT1076"
				value="#{msg['label.casetype.table.notabledata']}" />
		</m:td>
	</m:tr>
</m:table>
<%--  Data Scroller--%>
<m:div onclick="javascript:flagWarn=false;"
	onmousedown="javascript:flagWarn=false;">
	<t:dataScroller id="CMGTTOMDS38" pageCountVar="pageCount"
		pageIndexVar="pageIndex" onclick="javascript:flagWarn=false;"
		paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
		paginatorMaxPages="3" immediate="false" for="caseeventtable"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="dataScroller">
		<f:facet name="previous">
			<h:outputText id="PRGCMGTOT1077" styleClass="underline"
				value="#{cont['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="PRGCMGTOT1078" styleClass="underline"
				value="#{cont['label.ref.gt']}" rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="PRGCMGTOT1079"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="dataScrollerText" rendered="#{rowsCount>0}">
		</h:outputText>
	</t:dataScroller>
</m:div>
<m:br />
<m:br />

<m:div id="edtcv"
	rendered="#{CaseTypeDataBean.showEditCaseEvents || CaseTypeDataBean.showAddCaseEvents}">
	<m:anchor name="maintainCaseTypeCaseEvents" />
	<m:div styleClass="moreInfo">
		<m:div styleClass="moreInfoBar"
			rendered="#{CaseTypeDataBean.showEditCaseEvents}">
			<m:anchor name="maintainCaseTypeEditCaseEvents" />
			<m:div styleClass="infoTitle">
				<h:outputText id="PRGCMGTOT1080"
					value="#{msg['label.casetype.caseevents.editCaseEvent']}" />
			</m:div>
			<%-- srikanth --%>
			<m:div styleClass="infoActions">
				<t:commandLink id="PRGCMGTCL158" styleClass="strong"
					onmousedown="javascript:flagWarn=false;focusPage('addEditCaseTypes');javascript:addeditrow('maintaincasetype');"
					rendered="#{!CaseTypeDataBean.disableEditAddCaseEventSave}"
					action="#{CaseTypeControllerBean.saveEditCaseEvent}">
					<h:outputText id="PRGCMGTOT1081" value="#{ent['label-sw-save']}" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT1082" value="#{ent['label-sw-save']}"
					rendered="#{CaseTypeDataBean.disableEditAddCaseEventSave}" />
				<%-- Endsrikanth --%>
				<h:outputText id="PRGCMGTOT1083"
					value="#{msg['label.casetype.pipe']}" />
				<t:commandLink id="PRGCMGTCL159"
					onmousedown="javascript:flagWarn=true;focusPage('addEditCaseTypes');"
					action="#{CaseTypeControllerBean.cancelEditCaseEvents}">
					<h:outputText id="PRGCMGTOT1084" value="#{ent['label-sw-cancel']}" />
				</t:commandLink>
			</m:div>
		</m:div>
		<%--  ADD CASE EVENTS SECTION --%>
		<m:div styleClass="moreInfoBar"
			rendered="#{CaseTypeDataBean.showAddCaseEvents}">
			<m:anchor name="maintainCaseTypeAddCaseEvents" />
			<m:div styleClass="infoTitle">
				<h:outputText id="PRGCMGTOT1085"
					value="#{msg['label.casetype.caseevents.addCaseEvent']}" />
			</m:div>
			<m:div styleClass="infoActions">
				<t:commandLink id="PRGCMGTCL160" styleClass="strong"
					onmousedown="javascript:flagWarn=false;focusPage('addEditCaseTypes');javascript:addeditrow('maintaincasetype');"
					action="#{CaseTypeControllerBean.addCaseEvent}">
					<h:outputText id="PRGCMGTOT1086" value="#{ent['label-sw-save']}" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT1087"
					value="#{msg['label.casetype.pipe']}" />
				<t:commandLink id="PRGCMGTCL161"
					onmousedown="javascript:flagWarn=true;focusPage('addEditCaseTypes');"
					action="#{CaseTypeControllerBean.cancelAddCaseEvents}">
					<h:outputText id="PRGCMGTOT1088" value="#{ent['label-sw-cancel']}" />
				</t:commandLink>
			</m:div>
		</m:div>
		<%-- Common to both Edit and Add blocks --%>
		<m:div styleClass="moreInfoContent"
			onmouseover="javascript:flagWarn=false;javascript:fileSavedFlag=false;">
			<m:div>
			</m:div>
			<%--Commented for the defect ESPRD00853112 starts and code moved to top of the JSP Page--%>
			<%--Modified for defect ESPRD00357599 starts--%>
			<%--<m:div styleClass="msgBox"
				rendered="#{CaseTypeDataBean.showEventsSuccessMsg}">
				<%--Modified for defect ESPRD00357599 ends--%>
				<%--<h:outputText id="PRGCMGTOT1089" value="#{ent['label-sw-success']}" />
			</m:div>--%>
			<%--Modified for defect ESPRDOO561174 Begin--%>
			<%--<m:div styleClass="msgBox"
				rendered="#{CaseTypeDataBean.showEventsDeleteMsg}">

				<h:outputText id="PRGCMGTOT108911"
					value="#{ent['err-sw-record-delete-success']}" />
			</m:div>--%>
			<%--Defect ESPRD00853112 ends--%>
			<%--Modified for defect ESPRDOO561174 ends--%>
			<m:div rendered="#{CaseTypeDataBean.showTypeCodeFlag}">
				<h:messages id="PRGCMGTMS10" showDetail="true" layout="table"
					showSummary="false" styleClass="errorMessage" />
			</m:div>
			<m:div rendered="#{CaseTypeDataBean.showEventActMsg}">
				<h:messages id="PRGCMGTMS11" showDetail="true" layout="table"
					showSummary="false" styleClass="errorMessage" />
			</m:div>
			<m:div styleClass="fullwidth">
				<m:table id="PROVIDERMMT20120731164811446" cellspacing="0" width="100%">
					<m:tr>
						<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="ceinclude" for="ceincludradioval1"
									value="#{msg['label.casetype.caseevents.includeEvent']}" />

								<%--disabled="#{CaseTypeDataBean.incudeflag}"--%>
								<h:selectOneRadio id="ceincludradioval1"
									disabled="#{CaseTypeDataBean.disableUIFields}"
									value="#{CaseTypeDataBean.caseTypeEventVO.caseTypeEventInclude}">
									<f:selectItem itemLabel="#{msg['label.casetype.Yes']}"
										itemValue="Yes" />
									<f:selectItem itemLabel="#{msg['label.casetype.No']}"
										itemValue="No" />
									<%--<f:selectItems value="#{CaseTypeDataBean.includeRadioList}" />--%>
								</h:selectOneRadio>
							</m:div>
						</m:td>
						<m:td>
							<m:div styleClass="padb">
								<m:span styleClass="required">
									<h:outputText id="PRGCMGTOT1090"
										value="#{cont['label.ref.reqSymbol']}" />
								</m:span>
								<h:outputLabel id="typelabel" for="typeCode"
									value="#{msg['label.casetype.caseevents.type']}" />
								<m:br></m:br>

								<h:selectOneMenu id="typeCode"
									disabled="#{CaseTypeDataBean.disableUIFields}"
									onchange="javascript:selectOne=true;flagWarn=false;"
									value="#{CaseTypeDataBean.caseTypeEventVO.dfltCaseTypeEventTypeCode}">
									<f:selectItems value="#{CaseTypeDataBean.caseEventTypeList}" />

								</h:selectOneMenu>
								<m:br></m:br>
								<h:message id="PRGCMGTM161" for="typeCode"
									styleClass="errorMessage" />

							</m:div>
						</m:td>

						<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="cenotifyalert1" for="cenotifyalertmenu1"
									value="#{msg['label.casetype.caseevents.notifyViaAlert']}" />
								<m:br></m:br>

								<h:selectOneMenu id="cenotifyalertmenu1"
									disabled="#{CaseTypeDataBean.disableUIFields}"
									onchange="javascript:selectOne=true;flagWarn=false;"
									value="#{CaseTypeDataBean.caseTypeEventVO.dfltNotfyAlertUserId}">
									<f:selectItem itemLabel="" itemValue="" />
									<f:selectItems value="#{CaseTypeDataBean.dfltNotifyAlertList}" />
								</h:selectOneMenu>
								<m:br></m:br>
							</m:div>
						</m:td>
						<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="cealertbased1" for="cealertbasedmenu2"
									value="#{msg['label.casetype.caseevents.alertBasedOn']}" />
								<m:br></m:br>
								<h:selectOneMenu id="cealertbasedmenu2"
									disabled="#{CaseTypeDataBean.disableUIFields}"
									onchange="javascript:selectOne=true;flagWarn=false;"
									value="#{CaseTypeDataBean.caseTypeEventVO.dfltAlertBasedOnColName}">
									<%--Modified for defect ESPRD00357594 starts--%>
									<%--<f:selectItems value="#{CaseTypeDataBean.dfltAlertBasedOnList}" />--%>
									<f:selectItems
										value="#{CaseTypeDataBean.eventDfltAlertBasedOnList}" />
									<%--Modified for defect ESPRD00357594 ends--%>
								</h:selectOneMenu>
								<m:br></m:br>
								<h:message id="PRGCMGTM162" for="cealertbasedmenu2"
									styleClass="colorRed" />
							</m:div>
							<m:br />
						</m:td>

					</m:tr>
					<m:tr>
						<m:td>
							<m:div styleClass="padb">
								<h:outputLabel for="cesendalertmenu" id="cesendalertlabel" 
									value="#{msg['label.casetype.caseevents.sendAlert']}" />
								<m:br />
								<h:panelGrid id="PRGCMGTPGD122" columns="2">
									<h:selectOneMenu id="cesendalertmenu"
										disabled="#{CaseTypeDataBean.disableUIFields}"
										onchange="javascript:selectOne=true;flagWarn=false;"
										value="#{CaseTypeDataBean.caseTypeEventVO.dfltSendAlertDaysCode}">
										<f:selectItems
											value="#{CaseTypeDataBean.eventDfltSendAlertList}" />
									</h:selectOneMenu>
									<h:selectOneRadio id="cesendalertradio"
										disabled="#{CaseTypeDataBean.disableUIFields}"
										value="#{CaseTypeDataBean.caseTypeEventVO.dfltBeforeAfterCode}">
										<f:selectItem
											itemLabel="#{msg['label.casetype.caseevents.before']}"
											itemValue="B" />
										<f:selectItem
											itemLabel="#{msg['label.casetype.caseevents.after']}"
											itemValue="A" />
										<%--<f:selectItems
											value="#{CaseTypeDataBean.beforeAfterRadioList}" />--%>
									</h:selectOneRadio>
								</h:panelGrid>
								<h:message id="PRGCMGTM163" for="cesendalertlabel"
									styleClass="colorRed" />
							</m:div>
							<m:br />
						</m:td>
						<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="cedefaulttemp" for="cedefaulttempmenu"
									value="#{msg['label.casetype.caseevents.defaultTemplate']}" />
								<m:br />
								<h:selectOneMenu id="cedefaulttempmenu"
									disabled="#{CaseTypeDataBean.disableUIFields}"
									onchange="javascript:selectOne=true;flagWarn=false;"
									value="#{CaseTypeDataBean.caseTypeEventVO.dfltCotsLtrTmltKeyData}">
									<f:selectItems value="#{CaseTypeDataBean.templateDropDownList}" />
								</h:selectOneMenu>
								<m:br />
							</m:div>
						</m:td>
					</m:tr>
				</m:table>
			</m:div>
			<%--CR_ESPRD00373565_MaintainCaseTypes_06AUG2010 --%>
			<f:subview id="CaseEventsAuditSubViewID">
				<m:div onclick="javascript:flagWarn=false;"
					onmousedown="javascript:flagWarn=false;"
					id="showEditCaseEventsAuditId"
					rendered="#{CaseTypeDataBean.showEditCaseEvents && CaseTypeDataBean.enableMaintainCaseTypeAuditLog}">
					<audit:auditTableSet id="EditCaseEventsAuditId"
						value="#{CaseTypeDataBean.caseTypeEventVO.auditKeyList}"
						numOfRecPerPage="10">
					</audit:auditTableSet>

				</m:div>
			</f:subview>

			<%--EOF CR_ESPRD00373565_MaintainCaseTypes_06AUG2010 --%>
		</m:div>
	</m:div>
</m:div>

