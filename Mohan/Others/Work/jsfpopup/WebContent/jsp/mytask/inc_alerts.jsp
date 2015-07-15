<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />



<t:dataTable id="alertTable" border="0" width="100%"  first="#{myTaskDataBean.startIndexForAlert}"
	styleClass="dataTable" columnClasses="columnClass"
	rowStyleClass="#{myTaskDataBean.itemSelectedRowIndexAlert == rowIndex ? 'row_selected' : 'row'}"
	headerClass="headerClass" rowClasses="row_alt,row"
	rowOnClick="firstChild.lastChild.click();" footerClass="footerClass"
	value="#{myTaskDataBean.alertVoList}" var="alerts" rows="10"
	rowIndexVar="rowIndex"
	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
	rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;">

	<h:column id="PRGCMGTC114">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD174" columns="2">
				<h:outputLabel for="alertTypeAscCmdLink" id="alertType"
					value="#{messageBean.myTaskMap['label.alert.alertType']}" />
				<h:panelGroup id="alerts_panel1">
					<t:commandLink id="alertTypeAscCmdLink" styleClass="clStyle"
						actionListener="#{myTaskControllerBean.sort}"
						rendered="#{myTaskDataBean.imageRender != 'alertTypeAscCmdLink'}">
						<m:div title="for ascending" styleClass="sort_asc" />
						<f:attribute name="columnName" value="alertType" />
						<f:attribute name="sortOrder" value="ascending" />
					</t:commandLink>
					<t:commandLink id="alertTypeDscCmdLink" styleClass="clStyle"
						actionListener="#{myTaskControllerBean.sort}"
						rendered="#{myTaskDataBean.imageRender != 'alertTypeDscCmdLink'}">
						<m:div title="for descending" styleClass="sort_desc" />
						<f:attribute name="columnName" value="alertType" />
						<f:attribute name="sortOrder" value="descending" />
					</t:commandLink>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>


		<t:commandLink id="frAlrtDet"
			action="#{myTaskControllerBean.getAlertCrDets}">
			<h:outputText id="PRGCMGTOT1632" value="#{alerts.alertTypeDesc}" />
			<f:param name="indexCode" value="#{rowIndex}"></f:param>
			<f:param name="tab" value="0"></f:param>
		</t:commandLink>
	</h:column>

	<h:column id="PRGCMGTC115">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD175" columns="2">
				<h:outputLabel for="alerts_panel2" id="description"
					value="#{messageBean.myTaskMap['label.alert.desc']}" />
				<h:panelGroup id="alerts_panel2">
					<t:commandLink id="descAscCmdLink" styleClass="clStyle"
						actionListener="#{myTaskControllerBean.sort}"
						rendered="#{myTaskDataBean.imageRender != 'descAscCmdLink'}">
						<m:div title="for ascending" styleClass="sort_asc" />
						<f:attribute name="columnName" value="description" />
						<f:attribute name="sortOrder" value="ascending" />
					</t:commandLink>
					<t:commandLink id="descDscCmdLink" styleClass="clStyle"
						actionListener="#{myTaskControllerBean.sort}"
						rendered="#{myTaskDataBean.imageRender != 'descDscCmdLink'}">
						<m:div title="for descending" styleClass="sort_desc" />
						<f:attribute name="columnName" value="description" />
						<f:attribute name="sortOrder" value="descending" />
					</t:commandLink>

				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1633" value="#{alerts.description}" />

	</h:column>

	<h:column id="PRGCMGTC116">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD176" columns="2">
				<h:outputLabel for="alerts_panel3" id="dueDate"
					value="#{messageBean.myTaskMap['label.alert.dueDate']}" />
				<h:panelGroup id="alerts_panel3">
					<t:commandLink id="dueDateAscCmdLink" styleClass="clStyle"
						actionListener="#{myTaskControllerBean.sort}"
						rendered="#{myTaskDataBean.imageRender != 'dueDateAscCmdLink'}">
						<m:div title="for ascending" styleClass="sort_asc" />
						<f:attribute name="columnName" value="dueDate" />
						<f:attribute name="sortOrder" value="ascending" />
					</t:commandLink>
					<t:commandLink id="dueDateDscCmdLink" styleClass="clStyle"
						actionListener="#{myTaskControllerBean.sort}"
						rendered="#{myTaskDataBean.imageRender != 'dueDateDscCmdLink'}">
						<m:div title="for descending" styleClass="sort_desc" />
						<f:attribute name="columnName" value="dueDate" />
						<f:attribute name="sortOrder" value="descending" />
					</t:commandLink>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1634" value="#{alerts.dueDate}" />

	</h:column>
	<h:column id="PRGCMGTC117">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD177" columns="2">
				<h:outputLabel for="alerts_panel4" id="scopeAlert"
					value="#{messageBean.myTaskMap['label.alert.scope']}" />
				<h:panelGroup id="alerts_panel4">

					<t:commandLink id="scopeAlertAscCmdLink" styleClass="clStyle"
						actionListener="#{myTaskControllerBean.sort}"
						rendered="#{myTaskDataBean.imageRender != 'scopeAlertAscCmdLink'}">
						<m:div title="for ascending" styleClass="sort_asc" />

						<f:attribute name="columnName" value="scope" />
						<f:attribute name="sortOrder" value="ascending" />
					</t:commandLink>

					<t:commandLink id="scopeAlertDscCmdLink" styleClass="clStyle"
						actionListener="#{myTaskControllerBean.sort}"
						rendered="#{myTaskDataBean.imageRender != 'scopeAlertDscCmdLink'}">
						<m:div title="for descending" styleClass="sort_desc" />

						<f:attribute name="columnName" value="scope" />
						<f:attribute name="sortOrder" value="descending" />
					</t:commandLink>

				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1635" value="#{alerts.scopeDesc}" />

	</h:column>
	<h:column id="PRGCMGTC118">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD178" columns="2">
				<h:outputLabel for="alerts_panel5" id="entityname"
					value="#{messageBean.myTaskMap['label.alert.entityName']}" />
				<h:panelGroup id="alerts_panel5">
					<t:div styleClass="alignLeft">
						<t:commandLink id="entityNameAscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.sort}"
							rendered="#{myTaskDataBean.imageRender != 'entityNameAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />

							<f:attribute name="columnName" value="entityName" />
							<f:attribute name="sortOrder" value="ascending" />

						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="entityNameDscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.sort}"
							rendered="#{myTaskDataBean.imageRender != 'entityNameDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />

							<f:attribute name="columnName" value="entityName" />
							<f:attribute name="sortOrder" value="descending" />

						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1636" value="#{alerts.entityName}" />

	</h:column>
	<f:facet name="footer">
		<%--Modified for defect ESPRD00445659--%>
		<%--rendered="#{myTaskDataBean.alertNoData}"--%>
		<%--Code modified for defect ESPRD00918381: to display No Data Available message --%>
		<m:div id="nodata" rendered="#{empty myTaskDataBean.alertVoList}"
			align="center">
			<h:outputText id="PRGCMGTOT1637"
				value="#{messageBean.prgMsgMap['label.ref.noData.available']}"></h:outputText>
		</m:div>

	</f:facet>
</t:dataTable>
<t:dataScroller id="CMGTTOMDS56" pageCountVar="pageCount"
	pageIndexVar="pageIndex" paginator="true"
	paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
	immediate="false" for="alertTable" firstRowIndexVar="firstRowIndex"
	lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
	styleClass="dataScroller">
	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1638" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.lt']} "
			rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1639" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.gt']} "
			rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1640"
		value=" #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>
<m:br />
<m:br />
<m:div>
	<f:subview id="crDet1" rendered="#{myTaskDataBean.alertCRDetails}">
		<jsp:include page="inc_crAlertDetails.jsp" />
	</f:subview>
	<f:subview id="grps" rendered="#{myTaskDataBean.caseDetails}">
		<jsp:include page="inc_caseAlertDetails.jsp" />
	</f:subview>
	<%--UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010 --%>
	<f:subview id="tplMsqSaDetails"
		rendered="#{myTaskDataBean.showTplRecoeryCaseAlertDetail}">
		<jsp:include page="inc_alertDetails.jsp" />
	</f:subview>
	<%-- EOF UC-PGM-CRM-052.1_ESPRD00435733_18MAR2010--%>

</m:div>
