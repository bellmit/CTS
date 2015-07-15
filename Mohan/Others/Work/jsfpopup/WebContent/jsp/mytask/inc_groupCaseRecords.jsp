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


<t:dataTable id="grpCasetable" border="0" width="100%"
	rowClasses="row_alt,row" cellspacing="0" styleClass="dataTable"
	rowStyleClass="#{myTaskDataBean.itemSelectedRowIndexGrpCase == rowIndex ? 'row_selected' : 'row'}"
	value="#{myTaskDataBean.groupCaseRecList}" var="grpCaseDets"
	columnClasses="columnClass" headerClass="headerClass" rows="10"
	rowIndexVar="rowIndex"  first="#{myTaskDataBean.startIndexForGrpCase}"
	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
	rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
	rowOnClick="firstChild.lastChild.click();" footerClass="footerClass">


	<%-- CRN --%>
	<h:column id="grpcaserecCol1">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD194" columns="2">
				<h:outputLabel id="PRGCMGTOLL662"
					value="#{messageBean.myTaskMap['label.groupCaseRecs.caseRecNo']}"
					for="GPCsCmd" />
				<h:panelGroup id="GPCsCmd">
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpCasecrncmdLink1"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpCasecrncmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="caseRecNo" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpCasecrncmdLink2"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpCasecrncmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="caseRecNo" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<t:commandLink id="PRGCMGTCL240"
			action="#{myTaskControllerBean.getGrpCaseDets}">
			<h:outputText id="PRGCMGTOT1689" value="#{grpCaseDets.caseRecNo}" />
			<f:param name="indexCodegroupCaseRecords" value="#{rowIndex}"></f:param>
			<f:param name="tab" value="5"></f:param>
		</t:commandLink>

	</h:column>

	<%-- Open Date --%>

	<h:column id="grpcaserecCol2">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD195" columns="2">
				<h:outputLabel id="PRGCMGTOLL663"
					value="#{messageBean.myTaskMap['label.groupCaseRecs.openDate']}"
					for="GRPOpDAT" />
				<h:panelGroup id="GRPOpDAT">
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpOpenDatecmdLink1"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpOpenDatecmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpOpenDatecmdLink2"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpOpenDatecmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		
			<h:outputText id="PRGCMGTOT1690" value="#{grpCaseDets.openDate}" />
			
	</h:column>

	<%-- Entity --%>

	<h:column id="grpcaserecCol3">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD196" columns="2">
				<h:outputLabel id="PRGCMGTOLL664"
					value="#{messageBean.myTaskMap['label.groupCaseRecs.priority']}"
					for="GRPPRTY" />
				<h:panelGroup id="GRPPRTY">
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpPrioritycmdLink1"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpPrioritycmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpPrioritycmdLink2"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpPrioritycmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>

				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		
			<h:outputText id="PRGCMGTOT1691"
				value="#{grpCaseDets.priorityCodeDesc}" />
			
	</h:column>

	<%-- Entity Type --%>

	<h:column id="grpcaserecCol4">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD197" columns="2">
				<h:outputLabel id="PRGCMGTOLL665"
					value="#{messageBean.myTaskMap['label.groupCaseRecs.entityType']}"
					for="grpEtyCMD" />
				<h:panelGroup id="grpEtyCMD">
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpEntitycmdLink1"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpEntitycmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpEntitycmdLink2"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpEntitycmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
	
			<h:outputText id="PRGCMGTOT1692"
				value="#{grpCaseDets.entityTypeDesc}" />
			
	</h:column>
	<%--Entity Name --%>

	<h:column id="grpcaserecentityname">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD198" columns="2">
				<h:outputLabel id="PRGCMGTOLL666"
					value="#{messageBean.myTaskMap['label.groupCaseRecs.entityName']}"
					for="grpEtyNm" />
				<h:panelGroup id="grpEtyNm">
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpEntityNmcmdLink1"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpEntityNmcmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="entityName" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpEntityNmcmdLink2"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpEntityNmcmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="entityName" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1693" value="#{grpCaseDets.entityName}" />
			
	</h:column>

	<%-- Priority --%>

	<h:column id="grpcaserecCol5">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD199" columns="2">
				<h:outputLabel id="PRGCMGTOLL667"
					value="#{messageBean.myTaskMap['label.groupCaseRecs.caseType']}"
					for="GPCaseType" />
				<h:panelGroup id="GPCaseType">
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpCaseTypecmdLink1"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpCaseTypecmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="caseType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpCaseTypecmdLink2"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpCaseTypecmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="caseType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
			<h:outputText id="PRGCMGTOT1694" value="#{grpCaseDets.caseType}" />
			
	</h:column>

	<%-- Status --%>

	<h:column id="grpcaserecCol6">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD200" columns="2">
				<h:outputLabel id="PRGCMGTOLL668"
					value="#{messageBean.myTaskMap['label.groupCaseRecs.status']}"
					for="GrpStatus" />
				<h:panelGroup id="GrpStatus">
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpStatuscmdLink1"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpStatuscmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpStatuscmdLink2"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpStatuscmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1695"
				value="#{grpCaseDets.statusCodeDesc}" />
			
	</h:column>

	<%-- Work Unit --%>

	<h:column id="grpcaserecCol7">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD201" columns="2">
				<h:outputLabel id="PRGCMGTOLL669"
					value="#{messageBean.myTaskMap['label.groupCaseRecs.workunit']}"
					for="grpWORKUNIT" />
				<h:panelGroup id="grpWORKUNIT">
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpWorkUnitcmdLink1"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpWorkUnitcmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="workUnit" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="grpWorkUnitcmdLink2"
							actionListener="#{myTaskControllerBean.sortGrpCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'grpWorkUnitcmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="workUnit" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1696" value="#{grpCaseDets.workUnit}" />
			
	</h:column>

	<f:facet name="footer">

		<%--<m:div id="grpCasenodata" rendered="#{myTaskDataBean.grpCaseNoData}"
			align="center">--%>
		<%--Code modified for defect ESPRD00918381: to display No Data Available message --%>
		<m:div id="grpCasenodata"
			rendered="#{empty myTaskDataBean.groupCaseRecList}" align="center">

			<h:outputText id="PRGCMGTOT1697"
				value="#{messageBean.prgMsgMap['label.ref.noData.available']}"></h:outputText>
		</m:div>

	</f:facet>
</t:dataTable>
<t:dataScroller id="CMGTTOMDS59" pageCountVar="pageCount"
	pageIndexVar="pageIndex" paginator="true"
	paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
	immediate="false" for="grpCasetable" firstRowIndexVar="firstRowIndex"
	lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
	styleClass="dataScroller">
	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1698" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.lt']} "
			rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1699" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.gt']} "
			rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1700"
		value="showing #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>
<m:br />
<m:br />
<m:div>
	<f:subview id="crDet6"
		rendered="#{myTaskDataBean.groupCaseRecordsDetails}">
		<jsp:include page="inc_caseAlertDetails.jsp" />
	</f:subview>
</m:div>
