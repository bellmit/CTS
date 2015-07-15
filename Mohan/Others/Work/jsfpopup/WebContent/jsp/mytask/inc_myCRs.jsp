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


<t:dataTable id="myCrTable" border="0" width="100%" first="#{myTaskDataBean.startIndexForMyCr}"
	rowStyleClass="#{myTaskDataBean.itemSelectedRowIndex == rowIndex ? 'row_selected' : 'row'}"
	rowClasses="row_alt,row" cellspacing="0" styleClass="dataTable"
	value="#{myTaskDataBean.myCrsList}" var="myCrDets"
	columnClasses="columnClass" headerClass="headerClass" rows="10"
	rowIndexVar="rowIndex"
	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
	rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
	rowOnClick="firstChild.lastChild.click();">

	<%-- CRN --%>

	<t:column id="CMGTTOMCS27">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD216" columns="2">
				<h:outputLabel for="myCr_panel1" id="mycr_crn"
					value="#{messageBean.myTaskMap['label.mycrs.crn']}" />
				<h:panelGroup id="myCr_panel1">

					<t:div styleClass="alignLeft">
						<t:commandLink id="MyCrCRNAscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyCrCRNAscCmdLink'}">

							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="crn" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="MyCrCRNDscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyCrCRNDscCmdLink'}">

							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="crn" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<t:commandLink id="MyCr_CRN"
			action="#{myTaskControllerBean.getMyCrDets}">
			<h:outputText id="PRGCMGTOT1723" value="#{myCrDets.crn}" />
			<f:param name="indexCodeMyCRs" value="#{rowIndex}"></f:param>
			<f:param name="tab" value="1"></f:param>
		</t:commandLink>
	</t:column>

	<%-- Open Date --%>

	<t:column id="CMGTTOMCS28">

		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD217" columns="2">
				<h:outputLabel for="myCr_panel2" id="mycr_crn_opendate"
					value="#{messageBean.myTaskMap['label.mycrs.openDate']}" />
				<h:panelGroup id="myCr_panel2">
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCrOpenDateAscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'myCrOpenDateAscCmdLink'}">
							<%--  <h:graphicImage	alt="for ascending"	styleClass="sort_asc" width="8" />--%>
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCrOpenDateDscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'myCrOpenDateDscCmdLink'}">

							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1724" value="#{myCrDets.openDate}" />
	</t:column>

	<%-- Entity --%>

	<t:column id="CMGTTOMCS29">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD218" columns="2">
				<h:outputLabel for="myCr_panel3" id="mycr_crn_entity"
					value="#{messageBean.myTaskMap['label.mycrs.entity']}" />
				<h:panelGroup id="myCr_panel3">
					<t:div styleClass="alignLeft">
						<t:commandLink id="MyCrEntityAscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyCrEntityAscCmdLink'}">

							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="entity" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="MyCrEntityDscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyCrEntityDscCmdLink'}">

							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="entity" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1725" value="#{myCrDets.entity}" />
	</t:column>

	<%-- Entity type --%>

	<t:column id="CMGTTOMCS30">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD219" columns="2">
				<h:outputLabel for="myCr_panel4" id="mycr_crn_entityType"
					value="#{messageBean.myTaskMap['label.mycrs.entityType']}" />
				<h:panelGroup id="myCr_panel4">
					<t:div styleClass="alignLeft">
						<t:commandLink id="MyCrEntityTypeAscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyCrEntityTypeAscCmdLink'}">
							<%--   <h:graphicImage	alt="for ascending"	styleClass="sort_asc" width="8" />--%>
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="MyCrEntityTypeDscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyCrEntityTypeDscCmdLink'}">
							<%--   <h:graphicImage	alt="for dscending"	styleClass="sort_desc" width="8" />--%>
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1726" value="#{myCrDets.entityType}" />
	</t:column>

	<%-- Priority --%>

	<t:column id="CMGTTOMCS31">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD220" columns="2">
				<h:outputLabel for="myCr_panel5" id="mycr_crn_priority"
					value="#{messageBean.myTaskMap['label.mycrs.priority']}" />
				<h:panelGroup id="myCr_panel5">
					<t:div styleClass="alignLeft">
						<t:commandLink id="MyCrPriorityAscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyCrPriorityAscCmdLink'}">
							<%--  <h:graphicImage	alt="for ascending"	styleClass="sort_asc" width="8" />--%>
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="MyCrPriorityDscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyCrPriorityDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="descending" />

						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1727" value="#{myCrDets.priorityCode}" />
	</t:column>

	<%-- Status --%>

	<t:column id="CMGTTOMCS32">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD221" columns="2">
				<h:outputLabel for="myCr_panel6" id="my_status"
					value="#{messageBean.myTaskMap['label.mycrs.status']}" />
				<h:panelGroup id="myCr_panel6">
					<t:div styleClass="alignLeft">
						<t:commandLink id="MyStatusAscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyStatusAscCmdLink'}">

							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="MyStatusDscCmdLink"
							actionListener="#{myTaskControllerBean.myCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'MyStatusDscCmdLink'}">

							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1728" value="#{myCrDets.statusCode}" />
	</t:column>
	<f:facet name="footer">

		<%--<m:div id="nodata" rendered="#{myTaskDataBean.myCrNoData}"--%>
		<%--Code modified for defect ESPRD00896859: to display No Data Available message --%>
		<m:div id="nodata" rendered="#{empty myTaskDataBean.myCrsList}"
			align="center">
			<h:outputText id="PRGCMGTOT1729"
				value="#{messageBean.prgMsgMap['label.ref.noData.available']}"></h:outputText>
		</m:div>

	</f:facet>
</t:dataTable>
<t:dataScroller id="CMGTTOMDS62" pageCountVar="pageCount"
	pageIndexVar="pageIndex" paginator="true"
	paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
	immediate="false" for="myCrTable" firstRowIndexVar="firstRowIndex"
	lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
	styleClass="scroller">

	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1730" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.lt']}"
			rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1731" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.gt']}"
			rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>




	<h:outputText id="PRGCMGTOT1732"
		value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
		styleClass="scrollerBold" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>

<m:br />
<m:br />
<m:div>
	<f:subview id="crDet2" rendered="#{myTaskDataBean.myCrsDetails}">
		<jsp:include page="inc_crAlertDetails.jsp" />
	</f:subview>
</m:div>
