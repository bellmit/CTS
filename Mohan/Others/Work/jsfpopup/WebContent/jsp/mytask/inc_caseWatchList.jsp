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

<t:dataTable id="caseWatchListTable" border="0" width="100%"
	rowClasses="row_alt,row" cellspacing="0" styleClass="dataTable"
	rowStyleClass="#{myTaskDataBean.itemSelectedRowIndexCaseWL == rowIndex ? 'row_selected' : 'row'}"
	value="#{myTaskDataBean.caseRecWatchList}" var="WatchListDet"
	columnClasses="columnClass" headerClass="headerClass" rows="10"
	rowIndexVar="rowIndex"  first="#{myTaskDataBean.startIndexForGrpCaseWtchList}"
	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
	rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
	rowOnClick="firstChild.lastChild.click();" footerClass="footerClass">

	<%-- CRN --%>

	<h:column id="caseWatchListCol1">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD179" columns="2">
				<h:outputLabel id="PRGCMGTOLL647"
					value="#{messageBean.myTaskMap['label.caseRecsWL.caseRecNo']}"
					for="CaseWatchListCRN" />
				<h:panelGroup id="CaseWatchListCRN">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListcrncmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListcrncmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="caseRecNo" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListcrncmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListcrncmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="caseRecNo" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<t:commandLink id="CSWLCRN"
			action="#{myTaskControllerBean.getCaseWatchListDets}">
			<h:outputText id="PRGCMGTOT1654" value="#{WatchListDet.caseRecNo}" />
			<f:param name="indexCodeCaseWatchList" value="#{rowIndex}"></f:param>
			<f:param name="tab" value="6"></f:param>
		</t:commandLink>
	</h:column>

	<%-- Open Date --%>

	<h:column id="caseWatchListCol2">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD180" columns="2">
				<h:outputLabel id="PRGCMGTOLL648"
					value="#{messageBean.myTaskMap['label.caseRecsWL.openDate']}"
					for="CSWLOPDT" />
				<h:panelGroup id="CSWLOPDT">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListOpenDtcmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListOpenDtcmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListOpenDtcmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListOpenDtcmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
			<h:outputText id="PRGCMGTOT1655" value="#{WatchListDet.openDate}" />
			
	</h:column>


	<%-- Entity --%>

	<h:column id="caseWatchListCol3">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD181" columns="2">
				<h:outputLabel id="PRGCMGTOLL649"
					value="#{messageBean.myTaskMap['label.caseRecsWL.priority']}"
					for="CaseWatchListPrDAt" />
				<h:panelGroup id="CaseWatchListPrDAt">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListPriorityDtcmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListPriorityDtcmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListPriorityDtcmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListPriorityDtcmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1656"
				value="#{WatchListDet.priorityCodeDesc}" />
				
	</h:column>


	<%-- Entity type --%>

	<h:column id="caseWatchListCol4">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD182" columns="2">
				<h:outputLabel id="PRGCMGTOLL650"
					value="#{messageBean.myTaskMap['label.caseRecsWL.entityType']}"
					for="CaseWaTEnt" />
				<h:panelGroup id="CaseWaTEnt">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListEntitycmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListEntitycmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListEntitycmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListEntitycmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1657"
				value="#{WatchListDet.entityTypeDesc}" />
				
	</h:column>
	<%--entity name --%>
	<h:column id="caseWatchListEntyName">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD183" columns="2">
				<h:outputLabel id="PRGCMGTOLL651"
					value="#{messageBean.myTaskMap['label.caseRecsWL.entityName']}"
					for="entityName" />
				<h:panelGroup id="entityName">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListEntityNamecmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListEntitycmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="entityName" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListEntityNamecmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListEntitycmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="entityName" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1658" value="#{WatchListDet.entityName}" />
			
	</h:column>

	<%-- Priority --%>

	<h:column id="caseWatchListCol5">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD184" columns="2">
				<h:outputLabel id="PRGCMGTOLL652"
					value="#{messageBean.myTaskMap['label.caseRecsWL.caseType']}"
					for="CsWListCaseType" />
				<h:panelGroup id="CsWListCaseType">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListCaseTypecmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListCaseTypecmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="caseType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListCaseTypecmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListCaseTypecmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="caseType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
			<h:outputText id="PRGCMGTOT1659" value="#{WatchListDet.caseType}" />
			
	</h:column>



	<%-- Status --%>

	<h:column id="caseWatchListCol6">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD185" columns="2">
				<h:outputLabel id="PRGCMGTOLL653"
					value="#{messageBean.myTaskMap['label.caseRecsWL.status']}"
					for="CaseWatchST" />
				<h:panelGroup id="CaseWatchST">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListStatuscmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListStatuscmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListStatuscmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListStatuscmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
			<h:outputText id="PRGCMGTOT1660"
				value="#{WatchListDet.statusCodeDesc}" />
				
	</h:column>

	<%-- Work Unit --%>

	<h:column id="caseWatchListCol7">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD186" columns="2">
				<h:outputLabel id="PRGCMGTOLL654"
					value="#{messageBean.myTaskMap['label.caseRecsWL.userworkunit']}"
					for="WLUser" />
				<h:panelGroup id="WLUser">
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListUsercmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListUsercmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="userOrWorkUnit" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="CaseWatchListUsercmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseWatchList}"
							rendered="#{myTaskDataBean.imageRender != 'CaseWatchListUsercmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="userOrWorkUnit" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
			<h:outputText id="PRGCMGTOT1661"
				value="#{WatchListDet.userOrWorkUnit}" />
				
	</h:column>
	<f:facet name="footer">

		<%--<m:div id="CaseWLnodata" rendered="#{myTaskDataBean.watchListNoData}"
			align="center">--%>
		<%--Code modified for defect ESPRD00918381: to display No Data Available message --%>
		<m:div id="CaseWLnodata"
			rendered="#{empty myTaskDataBean.caseRecWatchList}" align="center">

			<h:outputText id="PRGCMGTOT1662"
				value="#{messageBean.prgMsgMap['label.ref.noData.available']}"></h:outputText>
		</m:div>

	</f:facet>

</t:dataTable>
<t:dataScroller id="CMGTTOMDS57" pageCountVar="pageCount"
	pageIndexVar="pageIndex" paginator="true"
	paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
	immediate="false" for="caseWatchListTable"
	firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
	rowsCountVar="rowsCount" styleClass="dataScroller">
	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1663" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.lt']} "
			rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1664" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.gt']} "
			rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1665"
		value=" #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>
<m:br />
<m:br />
<m:div>
	<f:subview id="crDet7"
		rendered="#{myTaskDataBean.caseRecordWatchListDetails}">
		<jsp:include page="inc_caseAlertDetails.jsp" />
	</f:subview>
</m:div>





