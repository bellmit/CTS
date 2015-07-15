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

<t:dataTable id="caseRecordtable" border="0" width="100%"
	rowClasses="row_alt,row" cellspacing="0" styleClass="dataTable"
	rowStyleClass="#{myTaskDataBean.itemSelectedRowIndexMyCase == rowIndex ? 'row_selected' : 'row'}"
	value="#{myTaskDataBean.myCaseRecordList}" var="myCaseDets"
	columnClasses="columnClass" headerClass="headerClass" rows="10"
	rowIndexVar="rowIndex" footerClass="footerClass" first="#{myTaskDataBean.startIndexForMyCase}"
	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
	rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
	rowOnClick="firstChild.lastChild.click();">

	<%-- CRN --%>
	<h:column id="PRGCMGTC133">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD209" columns="2">
				<h:outputLabel id="PRGCMGTOLL677"
					value="#{messageBean.myTaskMap['label.caseDetails.caseRcdNo']}"
					for="myCasecrn" />
				<h:panelGroup id="myCasecrn">
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCasecrncmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCasecrncmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="caseRecNo" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCasecrncmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCasecrncmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="caseRecNo" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<t:commandLink id="myCaseCRN"
			action="#{myTaskControllerBean.getMyCaseDetails}">
			<h:outputText id="PRGCMGTOT1712" value="#{myCaseDets.caseRecNo}" />
			<f:param name="indexCodeMyCaseRecords" value="#{rowIndex}"></f:param>
			<f:param name="tab" value="4"></f:param>
		</t:commandLink>
	</h:column>

	<%-- Open Date --%>
	<h:column id="PRGCMGTC134">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD210" columns="2">
				<h:outputLabel id="PRGCMGTOLL678"
					value="#{messageBean.myTaskMap['label.myCaseRecs.openDate']}"
					for="myCaseOpenDate" />
				<h:panelGroup id="myCaseOpenDate">
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseOpenDatecmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseOpenDatecmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseOpenDatecmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseOpenDatecmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="openDate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		
			<h:outputText id="PRGCMGTOT1713" value="#{myCaseDets.openDate}" />
			
	</h:column>
	<%-- Entity --%>
	<h:column id="mycaserecCol3">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD211" columns="2">
				<h:outputLabel id="PRGCMGTOLL679"
					value="#{messageBean.myTaskMap['label.myCaseRecs.priority']}"
					for="myCasePriority" />
				<h:panelGroup id="myCasePriority">
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCasePrioritycmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCasePrioritycmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCasePrioritycmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCasePrioritycmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="priorityCode" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		
			<h:outputText id="PRGCMGTOT1714"
				value="#{myCaseDets.priorityCodeDesc}" />
				
	</h:column>
	<%-- Entity type --%>
	<h:column id="mycaserecCol4">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD212" columns="2">
				<h:outputLabel id="PRGCMGTOLL680"
					value="#{messageBean.myTaskMap['label.myCaseRecs.status']}"
					for="myCaseStatus" />
				<h:panelGroup id="myCaseStatus">
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseStatuscmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseStatuscmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseStatuscmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseStatuscmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="statusCode" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		
			<h:outputText id="PRGCMGTOT1715" value="#{myCaseDets.statusCodeDesc}" />
			
	</h:column>


	<h:column id="mycaserecCol5">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD213" columns="2">
				<h:outputLabel id="PRGCMGTOLL681"
					value="#{messageBean.myTaskMap['label.myCaseRecs.entityType']}"
					for="myCaseEntity" />
				<h:panelGroup id="myCaseEntity">
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseEntitycmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseEntitycmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseEntitycmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseEntitycmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="entityType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		
			<h:outputText id="PRGCMGTOT1716" value="#{myCaseDets.entityTypeDesc}" />
		
	</h:column>

	<%--Entity Name --%>


	<h:column id="mycaserecEntName">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD214" columns="2">
				<h:outputLabel id="PRGCMGTOLL682"
					value="#{messageBean.myTaskMap['label.myCaseRecs.entityName']}"
					for="myCaseEntity" />
				<h:panelGroup id="myCaseEntityname">
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseEntityNamecmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseEntityNamecmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="entityName" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseEntitycmdNameLink2"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseEntitycmdNameLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="entityName" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		
			<h:outputText id="PRGCMGTOT1717" value="#{myCaseDets.entityName}" />
		
	</h:column>

	<%-- Status --%>

	<h:column id="mycaserecCol6">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD215" columns="2">
				<h:outputLabel id="PRGCMGTOLL683"
					value="#{messageBean.myTaskMap['label.myCaseRecs.caseType']}"
					for="myCaseType" />
				<h:panelGroup id="myCaseType">
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseTypecmdLink1"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseTypecmdLink1'}">
							<m:div title="#{messageBean.myTaskMap['label.for.ascending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="caseType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="myCaseTypecmdLink2"
							actionListener="#{myTaskControllerBean.sortCaseRecordNum}"
							rendered="#{myTaskDataBean.imageRender != 'myCaseTypecmdLink2'}">
							<m:div title="#{messageBean.myTaskMap['label.for.descending']}"
								styleClass="sort_desc" />
							<f:attribute name="columnName" value="caseType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		
			<h:outputText id="PRGCMGTOT1718" value="#{myCaseDets.caseType}" />
		
	</h:column>
	<f:facet name="footer">

		<%--<m:div id="Casenodata" rendered="#{myTaskDataBean.myCaseNoData}"
			align="center">--%>
		<%--Code modified for defect ESPRD00918381: to display No Data Available message --%>
		<m:div id="Casenodata"
			rendered="#{empty myTaskDataBean.myCaseRecordList}" align="center">

			<h:outputText id="PRGCMGTOT1719"
				value="#{messageBean.prgMsgMap['label.ref.noData.available']}"></h:outputText>
		</m:div>

	</f:facet>
</t:dataTable>
<t:dataScroller id="CMGTTOMDS61" pageCountVar="pageCount"
	pageIndexVar="pageIndex" paginator="true"
	paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
	immediate="false" for="caseRecordtable"
	firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
	rowsCountVar="rowsCount" styleClass="dataScroller">
	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1720" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.lt']} "
			rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1721" styleClass="underline"
			value="#{messageBean.prgMsgMap['label.ref.gt']} "
			rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1722"
		value="showing #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>
<m:br />
<m:br />
<m:div>
	<f:subview id="crDet5"
		rendered="#{myTaskDataBean.myCaseRecordsDetails}">
		<jsp:include page="inc_caseAlertDetails.jsp" />
	</f:subview>
</m:div>










