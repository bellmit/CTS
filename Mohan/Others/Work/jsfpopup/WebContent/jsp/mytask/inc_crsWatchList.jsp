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


<t:dataTable id="crWatchListTable" border="0" width="100%"
	styleClass="dataTable" columnClasses="columnClass" first="#{myTaskDataBean.startIndexForWtchList}"
	rowStyleClass="#{myTaskDataBean.itemSelectedRowIndexCrWL == rowIndex ? 'row_selected' : 'row'}"
	headerClass="headerClass" rowClasses="row_alt,row"
	footerClass="footerClass" value="#{myTaskDataBean.crWatchList}"
	var="crWatchListDet" rows="10" rowIndexVar="rowIndex"
	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
	rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
	rowOnClick="firstChild.lastChild.click();">

	<%-- CRN --%>

	<h:column id="PRGCMGTC119">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD187" columns="2">
				<h:outputLabel for="crwatch_panel1" id="CrWatch_crn"
					value="#{messageBean.myTaskMap['label.mycrs.crn']}" />

				<h:panelGroup id="crwatch_panel1">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchCRNAscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchCRNAscCmdLink'}">

							<m:div title="for ascending" styleClass="sort_asc" />

							<f:attribute name="columnName" value="crn" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchCRNDscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchCRNDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />

							<f:attribute name="columnName" value="crn" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<t:commandLink id="CrWatchLst_CRN"
			action="#{myTaskControllerBean.getCrWatchListCrDets}">
			<h:outputText id="PRGCMGTOT1678" value="#{crWatchListDet.crn}" />
			<f:param name="indexCodeCRsWatchList" value="#{rowIndex}"></f:param>
			<f:param name="tab" value="3"></f:param>
		</t:commandLink>
	</h:column>

	<%-- Open Date --%>

	<h:column id="PRGCMGTC120">

		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD188" columns="2">
				<h:outputLabel for="crwatch_panel2" id="CrWatch_crn_opendate"
					value="#{messageBean.myTaskMap['label.mycrs.openDate']}" />
				<h:panelGroup id="crwatch_panel2">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchOpenDateAscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchOpenDateAscCmdLink'}">

							<m:div title="for ascending" styleClass="sort_asc" />

							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchOpenDateDscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchOpenDateDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1679" value="#{crWatchListDet.openDate}" />
	</h:column>




	<h:column id="PRGCMGTC121">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD189" columns="2">
				<h:outputLabel for="crwatch_panel3" id="CrWatch_crn_entity"
					value="#{messageBean.myTaskMap['label.mycrs.entity']}" />
				<h:panelGroup id="crwatch_panel3">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchEntityAscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchEntityAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="entity" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchEntityDscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchEntityDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="entity" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1680" value="#{crWatchListDet.entity}" />
	</h:column>




	<h:column id="PRGCMGTC122">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD190" columns="2">
				<h:outputLabel for="crwatch_panel4" id="CrWatch_crn_entityType"
					value="#{messageBean.myTaskMap['label.mycrs.entityType']}" />
				<h:panelGroup id="crwatch_panel4">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchEntityTypeAscCmdLink"
							styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchEntityTypeAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchEntityTypeDscCmdLink"
							styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchEntityTypeDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1681" value="#{crWatchListDet.entityType}" />
	</h:column>




	<h:column id="PRGCMGTC123">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD191" columns="2">
				<h:outputLabel for="crwatch_panel5" id="CrWatch_crn_priority"
					value="#{messageBean.myTaskMap['label.mycrs.priority']}" />
				<h:panelGroup id="crwatch_panel5">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchPriorityAscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchPriorityAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchPriorityDscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchPriorityDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1682"
			value="#{crWatchListDet.priorityCode}" />
	</h:column>





	<h:column id="PRGCMGTC124">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD192" columns="2">
				<h:outputLabel for="crwatch_panel6" id="CrWatch_status"
					value="#{messageBean.myTaskMap['label.mycrs.status']}" />
				<h:panelGroup id="crwatch_panel6">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchStatusAscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchStatusAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchStatusDscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchStatusDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1683" value="#{crWatchListDet.statusCode}" />
	</h:column>



	<h:column id="PRGCMGTC125">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD193" columns="2">
				<h:outputLabel for="crwatch_panel7" id="CrWatch_wrkUnt"
					value="#{messageBean.myTaskMap['label.crsWatchlist.usrorwrkunit']}" />
				<h:panelGroup id="crwatch_panel7">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchWrkUntAscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchWrkUntAscCmdLink'}">

							<m:div title="for ascending" styleClass="sort_asc" />

							<f:attribute name="columnName" value="userOrWorkUnit" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CrWatchWrkUntDscCmdLink" styleClass="clStyle"
							actionListener="#{myTaskControllerBean.crWatchlistSort}"
							rendered="#{myTaskDataBean.imageRender != 'CrWatchWrkUntDscCmdLink'}">

							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="userOrWorkUnit" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1684" value="#{crWatchListDet.userName}" />
	</h:column>
	<f:facet name="footer">

		<%--<m:div id="nodata" rendered="#{myTaskDataBean.crWatchListNoData}"--%>
		<%--Code modified for defect ESPRD00918381: to display No Data Available message --%>
		<m:div id="nodata" rendered="#{empty myTaskDataBean.crWatchList}"
			align="center">
			<h:outputText id="PRGCMGTOT1685"
				value="#{messageBean.prgMsgMap['label.ref.noData.available']}"></h:outputText>
		</m:div>

	</f:facet>

</t:dataTable>

<t:dataScroller id="CMGTTOMDS58" pageCountVar="pageCount"
	pageIndexVar="pageIndex" paginator="true"
	paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
	immediate="false" for="crWatchListTable"
	firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
	rowsCountVar="rowsCount" styleClass="dataScroller">
	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1686" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.lt']} "
			rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1687" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.gt']} "
			rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1688"
		value=" #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>

<m:br />
<m:br />
<m:div>
	<f:subview id="crDet4" rendered="#{myTaskDataBean.crsWatchListDetails}">
		<jsp:include page="inc_crAlertDetails.jsp" />
	</f:subview>
</m:div>
