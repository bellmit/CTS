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

<t:dataTable id="grpCrTable" border="0" width="100%" first="#{myTaskDataBean.startIndexForGrpCr}"
	rowStyleClass="#{myTaskDataBean.itemSelectedRowIndexGrp == rowIndex ? 'row_selected' : 'row'}"
	styleClass="dataTable" columnClasses="columnClass"
	headerClass="headerClass" rowClasses="row_alt,row"
	value="#{myTaskDataBean.myGrpCRsList}" var="grpCrDets" cellspacing="0"
	rowIndexVar="rowIndex"
	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
	rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
	rowOnClick="firstChild.lastChild.click();" footerClass="footerClass"
	rows="10">



	<h:column id="PRGCMGTC126">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD202" columns="2">
				<h:outputLabel id="PRGCMGTOLL670" for="grpCrCRNAscCmdLink"
					value="#{messageBean.myTaskMap['label.mycrs.crn']}" />
				<h:panelGroup id="PRGCMGTPGP129">
					<m:div>
						<t:commandLink id="grpCrCRNAscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrCRNAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />

							<f:attribute name="columnName" value="crn" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="grpCrCRNDscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrCRNDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />

							<f:attribute name="columnName" value="crn" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<t:commandLink id="GrpCr_CRN"
			action="#{myTaskControllerBean.getGrpCrDets}">
			<h:outputText id="PRGCMGTOT1701" value="#{grpCrDets.crn}" />
			<f:param name="indexCodeGrpCRDets" value="#{rowIndex}"></f:param>
			<f:param name="tab" value="2"></f:param>
		</t:commandLink>

	</h:column>



	<h:column id="PRGCMGTC127">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD203" columns="2">
				<h:outputLabel id="PRGCMGTOLL671" for="GrpCrOpenDateAscCmdLink"
					value="#{messageBean.myTaskMap['label.mycrs.openDate']}" />
				<h:panelGroup id="PRGCMGTPGP130">
					<m:div>
						<t:commandLink id="GrpCrOpenDateAscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'GrpCrOpenDateAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="grpCrOpenDateDscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrOpenDateDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1702" value="#{grpCrDets.openDate}" />
	</h:column>



	<h:column id="PRGCMGTC128">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD204" columns="2">
				<h:outputLabel id="PRGCMGTOLL672" for="GrpCrEntityAscCmdLink"
					value="#{messageBean.myTaskMap['label.mycrs.entity']}" />
				<h:panelGroup id="PRGCMGTPGP131">
					<m:div>
						<t:commandLink id="GrpCrEntityAscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'GrpCrEntityAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="entity" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="grpCrEntityDscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrEntityDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="entity" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1703" value="#{grpCrDets.entity}" />
	</h:column>

	<%-- Entity Type --%>

	<h:column id="PRGCMGTC129">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD205" columns="2">
				<h:outputLabel id="PRGCMGTOLL673" for="grpCrEntityTypeAscCmdLink"
					value="#{messageBean.myTaskMap['label.mycrs.entityType']}" />
				<h:panelGroup id="PRGCMGTPGP132">
					<m:div>
						<t:commandLink id="grpCrEntityTypeAscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrEntityTypeAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="grpCrEntityTypeDscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrEntityTypeDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1704" value="#{grpCrDets.entityType}" />
	</h:column>

	<%-- Priority --%>

	<h:column id="PRGCMGTC130">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD206" columns="2">
				<h:outputLabel id="PRGCMGTOLL674" for="grpCrPriorityAscCmdLink"
					value="#{messageBean.myTaskMap['label.mycrs.priority']}" />
				<h:panelGroup id="PRGCMGTPGP133">
					<m:div>
						<t:commandLink id="grpCrPriorityAscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrPriorityAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="grpCrPriorityDscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrPriorityDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1705" value="#{grpCrDets.priorityCode}" />
	</h:column>

	<%-- Status --%>

	<h:column id="PRGCMGTC131">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD207" columns="2">
				<h:outputLabel id="PRGCMGTOLL675" for="grpCrStatusAscCmdLink"
					value="#{messageBean.myTaskMap['label.mycrs.status']}" />
				<h:panelGroup id="PRGCMGTPGP134">
					<m:div>
						<t:commandLink id="grpCrStatusAscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrStatusAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="grpCrStatusDscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpCrStatusDscCmdLink'}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1706" value="#{grpCrDets.statusCode}" />
	</h:column>

	<%-- Work Unit --%>

	<h:column id="PRGCMGTC132">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD208" columns="2">
				<h:outputLabel id="PRGCMGTOLL676" for="grpUserWrkUntAscCmdLink"
					value="#{messageBean.myTaskMap['label.grpcrs.wrkunit']}" />
				<h:panelGroup id="PRGCMGTPGP135">
					<m:div>
						<t:commandLink id="grpUserWrkUntAscCmdLink"
							actionListener="#{myTaskControllerBean.grpCrSort}"
							rendered="#{myTaskDataBean.imageRender != 'grpUserWrkUntAscCmdLink'}">
							<m:div title="for ascending" styleClass="sort_asc" />
							<f:attribute name="columnName" value="workUnit" />
							<f:attribute name="sortOrder" value="ascending" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="grpUserWrkUntDscCmdLink"
							rendered="#{myTaskDataBean.imageRender != 'grpUserWrkUntDscCmdLink'}"
							actionListener="#{myTaskControllerBean.grpCrSort}">
							<m:div title="for descending" styleClass="sort_desc" />
							<f:attribute name="columnName" value="workUnit" />
							<f:attribute name="sortOrder" value="descending" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1707" value="#{grpCrDets.deptName}" />
	</h:column>

	<f:facet name="footer">

		<%--<m:div id="nodata" rendered="#{myTaskDataBean.grpCrNoData}"--%>
		<m:div id="nodata" rendered="#{empty myTaskDataBean.myGrpCRsList}"
			align="center">
			<h:outputText id="PRGCMGTOT1708"
				value="#{messageBean.prgMsgMap['label.ref.noData.available']}"></h:outputText>
				       
		</m:div>

	</f:facet>
</t:dataTable>

<t:dataScroller id="CMGTTOMDS60" pageCountVar="pageCount"
	pageIndexVar="pageIndex" paginator="true"
	paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
	immediate="false" for="grpCrTable" firstRowIndexVar="firstRowIndex"
	lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
	styleClass="dataScroller">
	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1709" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.lt']} "
			rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1710" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.gt']} "
			rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1711"
		value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>
<m:br />
<m:br />
<m:div>
	<f:subview id="crDet3" rendered="#{myTaskDataBean.groupCRDetails}">
		<jsp:include page="inc_crAlertDetails.jsp" />
	</f:subview>
</m:div>
