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


<%--commented for defect ESPRD00624909(TSU issue)..Note..Search commandlink is used and replaced by f:param--%>
<%--<m:inputHidden name="com.ibm.portal.propertybroker.action"
			value="sendCaseDetailsAction"></m:inputHidden>--%>

<%-- Added the attribute 'first' for the defect id ESPRD00686628_29Aug2011 --%>
<t:dataTable id="searchCaseDataTable" border="0" cellpadding="2"
	cellspacing="0" columnClasses="columnClass" headerClass="tableHead"
	rows="10" footerClass="footerClass" rowClasses="row,row_alt"
	styleClass="dataTable" width="100%" var="searchCaseList"
	value="#{caseSearchDataBean.searchCaseList}"
	rendered="#{caseSearchDataBean.showData}"
	first="#{caseSearchDataBean.rowIndex}"
	onmouseover="return tableMouseOver(this, event);"
	onmouseout="return tableMouseOut(this, event);"
	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
	rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
	rowIndexVar="index" rowOnClick="javascript:childNodes[0].lastChild.click();">
	<t:column id="CaseSearchcreated">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD10" columns="2">
				<t:outputLabel id="CMGTTOMOL10" for="CaseSearchcreatedDate2"
					value="#{msg['label.searchCase.created']}" />
				<t:panelGroup id="CMGTTOMPGP10">
					<t:div id="CaseSearchResultsTDiv001" styleClass="alignLeft">
						<t:commandLink id="CaseSearchcreatedDate2"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchcreatedDate2'}">
							<m:div id="CaseSearchResultsMDiv0001" title="#{msg['label.customFields.forAscending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchcreatedDate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div id="CaseSearchResultsTDiv002" styleClass="alignLeft">
						<t:commandLink id="CaseSearchcreatedDate3"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchcreatedDate3'}">
							<m:div id="CaseSearchResultsMDiv0002" title="#{msg['label.customFields.forDescending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchcreatedDate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		
		<t:commandLink id="PRGCMGTCL50"
			action="#{caseSearchControllerBean.submitCaseDetails}">
			<f:param id="ipcAction" name="com.ibm.portal.propertybroker.action"
				value="sendCaseDetailsAction" />
			<f:param id="caseSK" name="caseSK" value="#{searchCaseList.caseSK}" />
			<f:param id="entityType" name="entityType"
				value="#{searchCaseList.entityType}" />
			<f:param id="entityID" name="entityID"
				value="#{searchCaseList.commonEntitySK}" />
		<h:outputText id="xx1" value="#{searchCaseList.createdDate}" />
		</t:commandLink>

	</t:column>
	<t:column id="entitynam">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD11" columns="2">
				<t:outputLabel id="CMGTTOMOL11" for="CaseSearchentityName2"
					value="#{msg['label.searchCase.entityName']}" />
				<t:panelGroup id="CMGTTOMPGP11">
					<t:div id="CaseSearchResultsTDiv003" styleClass="alignLeft">
						<t:commandLink id="CaseSearchentityName2"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchentityName2'}">
							<m:div id="CaseSearchResultsMDiv0003" title="#{msg['label.customFields.forAscending']}"
								styleClass="sort_asc" />
							<f:attribute name="columnName" value="caseSearchentityName" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div id="CaseSearchResultsTDiv004" styleClass="alignLeft">
						<t:commandLink id="CaseSearchentityName3"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchentityName3'}">
							<m:div id="CaseSearchResultsMDiv0004" title="#{msg['label.customFields.forDescending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchentityName" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="xx2" value="#{searchCaseList.entityName}" />
	</t:column>
	<t:column id="entityTyp">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD12" columns="2">
				<t:outputLabel id="CMGTTOMOL12" for="CaseSearchentityType2"
					value="#{msg['label.searchCase.entityType']}" />
				<t:panelGroup id="CMGTTOMPGP12">
					<t:div id="CaseSearchResultsTDiv005" styleClass="alignLeft">
						<t:commandLink id="CaseSearchentityType2"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchentityType2'}">
							<m:div id="CaseSearchResultsMDiv0005" title="#{msg['label.customFields.forAscending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchentityType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div id="CaseSearchResultsTDiv006" styleClass="alignLeft">
						<t:commandLink id="CaseSearchentityType3"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchentityType3'}">
							<m:div id="CaseSearchResultsMDiv0006" title="#{msg['label.customFields.forDescending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchentityType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>

				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="xx3" value="#{searchCaseList.entityTypeDesc}" />
	</t:column>
	<t:column id="CaseSearchstate">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD13" columns="2">
				<t:outputLabel id="CMGTTOMOL13" for="CaseSearchstatus2"
					value="#{msg['label.searchCase.status']}" />
				<t:panelGroup id="CMGTTOMPGP13">
					<t:div id="CaseSearchResultsTDiv007" styleClass="alignLeft">
						<t:commandLink id="CaseSearchstatus2"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchstatus2'}">
							<m:div id="CaseSearchResultsMDiv0007" title="#{msg['label.customFields.forAscending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchstatus" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div id="CaseSearchResultsTDiv008" styleClass="alignLeft">
						<t:commandLink id="CaseSearchstatus3"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchstatus3'}">
							<m:div id="CaseSearchResultsMDiv0008" title="#{msg['label.customFields.forDescending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchstatus" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="xx4" value="#{searchCaseList.status}" />
	</t:column>
	<t:column id="CaseSearchassigned">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD14" columns="2">
				<t:outputLabel id="CMGTTOMOL14" for="CaseSearchassignedTo2"
					value="#{msg['label.searchCase.assignedTo']}" />
				<t:panelGroup id="CMGTTOMPGP14">
					<t:div id="CaseSearchResultsTDiv009" styleClass="alignLeft">
						<t:commandLink id="CaseSearchassignedTo2"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchassignedTo2'}">
							<m:div id="CaseSearchResultsMDiv0009" title="#{msg['label.customFields.forAscending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchassignedTo" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div id="CaseSearchResultsTDiv010" styleClass="alignLeft">
						<t:commandLink id="CaseSearchassignedTo3"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchassignedTo3'}">
							<m:div id="CaseSearchResultsMDiv0010" title="#{msg['label.customFields.forDescending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchassignedTo" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="xx5" value="#{searchCaseList.assignedTo}" />
	</t:column>
	<t:column id="caseTy">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD15" columns="2">
				<t:outputLabel id="CMGTTOMOL15" for="CaseSearchcaseType2"
					value="#{msg['label.searchCase.caseType']}" />
				<t:panelGroup id="CMGTTOMPGP15">
					<t:div id="CaseSearchResultsTDiv011" styleClass="alignLeft">
						<t:commandLink id="CaseSearchcaseType2"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchcaseType2'}">
							<m:div id="CaseSearchResultsMDiv0011" title="#{msg['label.customFields.forAscending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchcaseType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div id="CaseSearchResultsTDiv012" styleClass="alignLeft">
						<t:commandLink id="CaseSearchcaseType3"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchcaseType3'}">
							<m:div id="CaseSearchResultsMDiv0012" title="#{msg['label.customFields.forDescending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchcaseType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="xx6" value="#{searchCaseList.caseType}" />
	</t:column>
	<t:column id="prior">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD16" columns="2">
				<t:outputLabel id="CMGTTOMOL16" for="CaseSearchpriority2"
					value="#{msg['label.searchCase.priority']}" />
				<t:panelGroup id="CMGTTOMPGP16">
					<t:div id="CaseSearchResultsTDiv013" styleClass="alignLeft">
						<t:commandLink id="CaseSearchpriority2"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchpriority2'}">
							<m:div id="CaseSearchResultsMDiv0013" title="#{msg['label.customFields.forAscending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchpriority" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div id="CaseSearchResultsTDiv014" styleClass="alignLeft">
						<t:commandLink id="CaseSearchpriority3"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchpriority3'}">
							<m:div id="CaseSearchResultsMDiv0014" title="#{msg['label.customFields.forDescending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchpriority" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="xx7" value="#{searchCaseList.priority}" />
	</t:column>
	<t:column id="lineofBusiness">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD17" columns="2">
				<t:outputLabel id="CMGTTOMOL17" for="CaseSearchlob2"
					value="#{msg['label.serachCase.lob']}" />
				<t:panelGroup id="CMGTTOMPGP17">
					<t:div id="CaseSearchResultsTDiv015" styleClass="alignLeft">
						<t:commandLink id="CaseSearchlob2"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchlob2'}">
							<m:div id="CaseSearchResultsMDiv0015" title="#{msg['label.customFields.forAscending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchlob" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div id="CaseSearchResultsTDiv016" styleClass="alignLeft">
						<t:commandLink id="CaseSearchlob3"
							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"
							rendered="#{caseSearchDataBean.imageRender != 'CaseSearchlob3'}">
							<m:div id="CaseSearchResultsMDiv0016" title="#{msg['label.customFields.forDescending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchlob" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="xx8" value="#{searchCaseList.lob}" />
	</t:column>
</t:dataTable>

<!-- Start - Modified the t:datascroller with the customized user defined datascroller -->
	 <h:panelGroup id="CMGTTOMDS9" rendered="#{caseSearchDataBean.showData}">
		<h:outputText id="PRGCMGTOT265"
			value="#{caseSearchDataBean.startRecordNo}-#{caseSearchDataBean.endRecord} of #{caseSearchDataBean.count}"
			style="font-weight:bold;float:left;"></h:outputText>
		<t:commandLink id="MEMINFCL73011"
			onmousedown="javascript:flagWarn=false;"
			action="#{caseSearchControllerBean.next}"
			rendered="#{caseSearchDataBean.showNext}" style="float:right;CURSOR:hand;">
			<h:outputText id="PRGCMGTOT2651" value=">>" styleClass="underline"/>
		</t:commandLink>
		<h:outputText id="MEMINFOT460412" escape="false" value="&nbsp;"
			style="float:right;" />
		<t:commandLink id="MEMINFCL7311315"
			onmousedown="javascript:flagWarn=false;"
			action="#{caseSearchControllerBean.nextOne}"
			rendered="#{caseSearchDataBean.showNextOne}">
			<h:outputText id="MEMINFOT46051315"
				value="#{caseSearchDataBean.currentPage+2}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="PRGCMGTOT26525" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL73112"
			onmousedown="javascript:flagWarn=false;"
			action="#{caseSearchControllerBean.next}"
			rendered="#{caseSearchDataBean.showNext}">
			<h:outputText id="PRGCMGTOT2653"
				value="#{caseSearchDataBean.currentPage+1}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="MEMINFOT46061" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL73213"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="PRGCMGTOT2654"
				value="#{caseSearchDataBean.currentPage}" 
				style="float:right;CURSOR:hand;font-weight:bold"></h:outputText>
		</t:commandLink>
		<h:outputText id="PRGCMGTOT2655" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL73315"
			onmousedown="javascript:flagWarn=false;"
			action="#{caseSearchControllerBean.previous}"
			rendered="#{caseSearchDataBean.showPrevious}">
			<h:outputText id="PRGCMGTOT2656"
				value="#{caseSearchDataBean.currentPage-1}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="PRGCMGTOT26551" escape="false" value="&nbsp;"
			style="float:right" rendered="#{caseSearchDataBean.showPrevious && ((caseSearchDataBean.currentPage-2>1 && !caseSearchDataBean.showNext)||(!caseSearchDataBean.showNext && caseSearchDataBean.currentPage-2!=0))}"/>
		<t:commandLink id="MEMINFCL733152"
			onmousedown="javascript:flagWarn=false;"
			action="#{caseSearchControllerBean.previousOne}"
			rendered="#{caseSearchDataBean.showPrevious && ((caseSearchDataBean.currentPage-2>1 && !caseSearchDataBean.showNext)||(!caseSearchDataBean.showNext && caseSearchDataBean.currentPage-2!=0))}">
			<h:outputText id="PRGCMGTOT26562"
				value="#{caseSearchDataBean.currentPage-2}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="PRGCMGTOT2657" escape="false" value="&nbsp;&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL73416"
			onmousedown="javascript:flagWarn=false;"
			action="#{caseSearchControllerBean.previous}"
			rendered="#{caseSearchDataBean.showPrevious}" style="float:right">
			<h:outputText id="PRGCMGTOT2658" value="<<" styleClass="underline" />
		</t:commandLink>
		<m:br></m:br>
		<m:br></m:br>
	</h:panelGroup>
<!-- End - Modified the t:datascroller with the customized user defined datascroller -->

<%--<h:dataTable var="searchCaseList"
	rendered="#{caseSearchDataBean.noData}" styleClass="dataTableOne"
	cellspacing="0" width="100%" border="1" headerClass="tableHead"
	rowClasses="rowone,row_alt" id="searchCaseDataTable1">
	<h:column id="CaseSearchcreated1">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT268"
				value="#{msg['label.searchCase.created']}" />
		</f:facet>
	</h:column>
	<h:column id="entitynam1">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT269"
				value="#{msg['label.searchCase.entityName']}" />
		</f:facet>
	</h:column>
	<h:column id="entityTyp1">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT270"
				value="#{msg['label.searchCase.entityType']}" />
		</f:facet>
	</h:column>
	<h:column id="CaseSearchstate1">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT271"
				value="#{msg['label.searchCase.status']}" />
		</f:facet>
	</h:column>
	<h:column id="CaseSearchassigned1">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT272"
				value="#{msg['label.searchCase.assignedTo']}" />
		</f:facet>
	</h:column>
	<h:column id="caseTy1">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT273"
				value="#{msg['label.searchCase.caseType']}" />
		</f:facet>
	</h:column>
	<h:column id="prior1">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT274"
				value="#{msg['label.searchCase.priority']}" />
		</f:facet>
	</h:column>
	<h:column id="lineofBusiness1">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT275"
				value="#{msg['label.serachCase.lob']}" />
		</f:facet>
	</h:column>
</h:dataTable>
<m:table id="caseSearchResultsNoDataId" styleClass="dataTable" width="100%" border="0"
			rendered="#{caseSearchDataBean.noData}">
		<m:tr  id="caseSearchResultsTRId">
			<m:td id="caseSearchResultsTDId" align="center">
				<h:outputText id="caseSearchResultsNoDataTextId" value="#{msg['value.noData']}" />
			</m:td>
		</m:tr>
</m:table>--%>
<m:br /> <m:br />

