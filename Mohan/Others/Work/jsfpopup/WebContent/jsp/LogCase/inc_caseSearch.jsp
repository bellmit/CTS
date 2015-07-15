<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:section id="caseSearchhSec1" styleClass="otherbg">
	
	<m:div id="caseSearchhDiv1" styleClass="moreInfo">
		<m:div id="caseSearchhDiv2" styleClass="moreInfoBar">
			<m:div id="caseSearchhDiv3" styleClass="infoTitle">
				<h:outputText id="caseSearchhOutText1"					value="#{msg['label.case.associatedCaseAndCorrespondence.caseSearch']}" />
			</m:div>
			<m:div id="caseSearchhDiv4" styleClass="infoActions">
				<%-- Added the focusPage() in onclick for the defect id ESPRD00697332_07DEC2011--%>
				<h:commandButton 
					id="cancelCaseSearchScreenId" 
					onclick="javascript:focusPage('caseAscHeader');"					
					action="#{logCaseControllerBean.cancelCaseSearchScreen}"					
					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:11px;WIDTH:45px;BORDER:0;text-align:left;font-family:Verdana;"					
					disabled="#{logCaseDataBean.disableScreenField}"					
					value="#{msg['label.case.cancel']}">
				</h:commandButton>
			</m:div>
		</m:div>
	</m:div>
	
	<m:div id="caseSearchhDiv5">
	<f:subview id="caseSearchSubview1">
		<jsp:include page="/jsp/LogCase/inc_logCaseSearch.jsp" />
	</f:subview>
		<m:br id="caseSearchhBr1" />
		<m:br id="caseSearchhBr2" />
		
	</m:div>
	
	<%-- 'rowIndexVar' attribute is added in the dataTable for the defect ESPRD00684566 --%>
	<m:anchor name="searchCaseResultDataTableFocusPage"></m:anchor> <%-- Added the anchor tag for the defect id ESPRD00697332_07DEC2011--%>
	<%-- Added the focusPage() in rowOnClick for the defect id ESPRD00697332_07DEC2011--%>
	<t:dataTable id="searchCaseResultDataTable"		
	             rendered="#{caseSearchDataBean.showData}" border="1" cellpadding="0"		
	             cellspacing="0" columnClasses="columnClass" headerClass="headerClass"		
	             rows="10" footerClass="footerClass" rowClasses="row_alt,rowone"		
	             styleClass="dataTable" width="100%" var="searchCaseList"		
	             first="#{logCaseDataBean.caseSearchRowIndex}"		
	             value="#{caseSearchDataBean.searchCaseList}"		
	             onmouseover="return tableMouseOver(this, event);"		
	             onmouseout="return tableMouseOut(this, event);"
	             rowOnClick="javascript:flagWarn=false;focusPage('caseRecAssWithCaseFocusPage');childNodes[0].lastChild.click();"
	             rowIndexVar="index">
		<t:column id="CaseSearchcreatedColumn1">
			<f:facet name="header">
				<h:panelGrid id="caseSearchhPG1" columns="2">
					<h:outputLabel id="caseSearchhLabel1" for="searchCreatedDate1"						value="#{msg['label.case.associatedCaseAndCorrespondence.created']}" />
					<h:panelGroup id="caseSearchhPGRP1">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:div id="CaseSearchAssTDiv1001" styleClass="alignLeft">
						<t:commandLink id="searchCreatedDate1" styleClass="clStyle"	onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"						actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchCreatedDate1'}">
							<m:div id="caseSearchhGPH1" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchcreatedDate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseSearchAssTDiv1002" styleClass="alignLeft">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:commandLink id="searchCreatedDate2" styleClass="clStyle"	onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"						actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchCreatedDate2'}">
							<m:div id="caseSearchhGPH2" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchcreatedDate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
			<t:commandLink id="linkCase" onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseFocusPage');"				action="#{logCaseControllerBean.associateCaseRecord}">
				<h:outputText id="caseSearchhOutText2" value="#{searchCaseList.createdDate}" />
				<f:param name="rowIndex" value="#{index}"></f:param> <%-- Added this code for the defect ESPRD00684566 --%>
			</t:commandLink>
		</t:column>
		<t:column id="CaseSearchEntityNameColumn2">
			<f:facet name="header">
				<h:panelGrid id="caseSearchhPG2" columns="2">
					<h:outputLabel id="caseSearchhLabel2" for="searchentityName2"						value="#{msg['label.case.associatedCaseAndCorrespondence.entityName']}" />
					<h:panelGroup id="caseSearchhPGRP2">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:div id="CaseSearchAssTDiv1003" styleClass="alignLeft">
						<t:commandLink id="searchentityName2" styleClass="clStyle"	onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"						actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchentityName2'}">
							<m:div id="caseSearchhGPH3" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchentityName" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<m:br id="caseSearchhBr4" />
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:div id="CaseSearchAssTDiv1004" styleClass="alignLeft">
						<t:commandLink id="searchentityName3" styleClass="clStyle"	onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"						actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchentityName3'}">
							<m:div id="caseSearchhGPH4" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchentityName" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="caseSearchhOutText3" value="#{searchCaseList.entityName}" />
		</t:column>
		<t:column id="CaseSearchEntityTypeColumn3">
			<f:facet name="header">
				<h:panelGrid id="caseSearchhPG3" columns="2">
					<h:outputLabel id="caseSearchhLabel3" for="searchentityType2"						value="#{msg['label.case.associatedCaseAndCorrespondence.entityType']}" />
					<h:panelGroup id="caseSearchhPGRP3">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:div id="CaseSearchAssTDiv1005" styleClass="alignLeft">
						<t:commandLink id="searchentityType2" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchentityType2'}">
							<m:div id="caseSearchhGPH5" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchentityType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseSearchAssTDiv1006" styleClass="alignLeft">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:commandLink id="searchentityType3" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchentityType3'}">
							<m:div id="caseSearchhGPH6" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchentityType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<%--  Added for the defect ESPRD00687821  --%>
			<h:outputText id="caseSearchhOutText4" value="#{searchCaseList.entityTypeDesc}" />
		</t:column>
		<t:column id="CaseSearchStatusColumn4">
			<f:facet name="header">
				<h:panelGrid id="caseSearchhPG4" columns="2">
					<h:outputLabel id="caseSearchhLabel4" for="searchstatus2"						value="#{msg['label.case.associatedCaseAndCorrespondence.status']}" />
					<h:panelGroup id="caseSearchhPGRP4">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:div id="CaseSearchAssTDiv1007" styleClass="alignLeft">
						<t:commandLink id="searchstatus2" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchstatus2'}">
							<m:div id="caseSearchhGPH7" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchstatus" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseSearchAssTDiv1008" styleClass="alignLeft">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:commandLink id="searchstatus3" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchstatus3'}">
							<m:div id="caseSearchhGPH8" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchstatus" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="caseSearchhOutText5" value="#{searchCaseList.status}" />
		</t:column>
		<t:column id="CaseSearchAssignedColumn5">
			<f:facet name="header">
				<h:panelGrid id="caseSearchhPG5" columns="2">
					<h:outputLabel id="caseSearchhLabel5" for="searchassignedTo2"						value="#{msg['label.case.associatedCaseAndCorrespondence.assignedTo']}" />
					<h:panelGroup id="caseSearchhPGRP5">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:div id="CaseSearchAssTDiv1009" styleClass="alignLeft">
						<t:commandLink id="searchassignedTo2" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');" 							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchassignedTo2'}">
							<m:div id="caseSearchhGPH9" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchassignedTo" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseSearchAssTDiv1010" styleClass="alignLeft">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:commandLink id="searchassignedTo3" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchassignedTo3'}">
							<m:div id="caseSearchhGPH10" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchassignedTo" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="caseSearchhOutText6" value="#{searchCaseList.assignedTo}" />
		</t:column>
		<t:column id="CaseSearchCaseTypeColumn6">
			<f:facet name="header">
				<h:panelGrid id="caseSearchhPG6" columns="2">
					<h:outputLabel id="caseSearchhLabel6" for="searchcaseType2"						value="#{msg['label.case.associatedCaseAndCorrespondence.caseType']}" />
					<h:panelGroup id="caseSearchhPGRP6">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:div id="CaseSearchAssTDiv1011" styleClass="alignLeft">
						<t:commandLink id="searchcaseType2" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchcaseType2'}">
							<m:div id="caseSearchhGPH11" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchcaseType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseSearchAssTDiv1012" styleClass="alignLeft">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:commandLink id="searchcaseType3" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchcaseType3'}">
							<m:div id="caseSearchhGPH12" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchcaseType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="caseSearchhOutText7" value="#{searchCaseList.caseType}" />
		</t:column>
		<t:column id="CaseSearchprioColumn7">
			<f:facet name="header">
				<h:panelGrid id="caseSearchhPG7" columns="2">
					<h:outputLabel id="caseSearchhLabel7" for="searchpriority2"						value="#{msg['label.case.associatedCaseAndCorrespondence.priority']}" />
					<h:panelGroup id="caseSearchhPGRP7">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:div id="CaseSearchAssTDiv1013" styleClass="alignLeft">
						<t:commandLink id="searchpriority2" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchpriority2'}">
							<m:div id="caseSearchhGPH13" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchpriority" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseSearchAssTDiv1014" styleClass="alignLeft">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:commandLink id="searchpriority3" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"							actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchpriority3'}">
							<m:div id="caseSearchhGPH14" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchpriority" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="caseSearchhOutText8" value="#{searchCaseList.priority}" />
		</t:column>
		<t:column id="CaseSearchLOBColumn8">
			<f:facet name="header">
				<h:panelGrid id="caseSearchhPG8" columns="2">
					<h:outputLabel id="caseSearchhLabel8" for="searchlob2"						value="#{msg['label.case.associatedCaseAndCorrespondence.lobs']}" />
					<h:panelGroup id="caseSearchhPGRP8">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:div id="CaseSearchAssTDiv1015" styleClass="alignLeft">
						<t:commandLink id="searchlob2" styleClass="clStyle"	onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"						actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchlob2'}">
							<m:div id="caseSearchhGPH15" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="caseSearchlob" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseSearchAssTDiv1016" styleClass="alignLeft">
						<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
						<t:commandLink id="searchlob3" styleClass="clStyle"	onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"						actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"							rendered="#{caseSearchDataBean.imageRender != 'searchlob3'}">
							<m:div id="caseSearchhGPH16" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="caseSearchlob" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="caseSearchhOutText9" value="#{searchCaseList.lob}" />
		</t:column>
	</t:dataTable>
	
	<!-- Start - Modified the t:datascroller with the customized user defined datascroller  FOR THE DEFECT ESPRD00687819  -->

	 <h:panelGroup id="CMGTTOMDS9" rendered="#{caseSearchDataBean.showData}">
		<h:outputText id="PRGCMGTOT265"
			value="#{caseSearchDataBean.startRecordNo}-#{caseSearchDataBean.endRecord} of #{caseSearchDataBean.count}"
			style="font-weight:bold;float:left;"></h:outputText>
		<t:commandLink id="MEMINFCL73011"
			onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"
			action="#{caseSearchControllerBean.next}"
			rendered="#{caseSearchDataBean.showNext}" style="float:right;CURSOR:hand;">
			<h:outputText id="PRGCMGTOT2651" value=">>" styleClass="underline"/>
		</t:commandLink>
		<h:outputText id="MEMINFOT460412" escape="false" value="&nbsp;"
			style="float:right;" />
		<t:commandLink id="MEMINFCL7311315"
			onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"
			action="#{caseSearchControllerBean.nextOne}"
			rendered="#{caseSearchDataBean.showNextOne}">
			<h:outputText id="MEMINFOT46051315"
				value="#{caseSearchDataBean.currentPage+2}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="PRGCMGTOT26525" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL73112"
			onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"
			action="#{caseSearchControllerBean.next}"
			rendered="#{caseSearchDataBean.showNext}">
			<h:outputText id="PRGCMGTOT2653"
				value="#{caseSearchDataBean.currentPage+1}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="MEMINFOT46061" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL73213"
			onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');">
			<h:outputText id="PRGCMGTOT2654"
				value="#{caseSearchDataBean.currentPage}" 
				style="float:right;CURSOR:hand;font-weight:bold"></h:outputText>
		</t:commandLink>
		<h:outputText id="PRGCMGTOT2655" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL73315"
			onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"
			action="#{caseSearchControllerBean.previous}"
			rendered="#{caseSearchDataBean.showPrevious}">
			<h:outputText id="PRGCMGTOT2656"
				value="#{caseSearchDataBean.currentPage-1}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="PRGCMGTOT2657" escape="false" value="&nbsp;&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL73416"
			onmousedown="javascript:flagWarn=false;focusPage('searchCaseResultDataTableFocusPage');"
			action="#{caseSearchControllerBean.previous}"
			rendered="#{caseSearchDataBean.showPrevious}" style="float:right">
			<h:outputText id="PRGCMGTOT2658" value="<<" styleClass="underline" />
		</t:commandLink>
		<m:br></m:br>
		<m:br></m:br>
	</h:panelGroup>
<!-- End - Modified the t:datascroller with the customized user defined datascroller   FOR THE DEFECT ESPRD00687819 -->
	
	<%--<t:dataScroller pageCountVar="pageCount" pageIndexVar="pageIndex"
		onclick="javascript:flagWarn=false;"
		paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
		paginatorMaxPages="3" immediate="false"
		for="searchCaseResultDataTable" firstRowIndexVar="firstRowIndex"
		lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
		rendered="#{caseSearchDataBean.noDataFlag}" styleClass="dataScroller">
		<f:facet name="previous">
			<h:outputText id="caseSearchhOutText10" styleClass="underline" value="#{msg['label.lessthan']}"				rendered="#{pageIndex> 1}">
			</h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="caseSearchhOutText11" styleClass="underline"				value=" #{msg['label.greaterthan']} "				rendered="#{pageIndex < pageCount}">
			</h:outputText>
		</f:facet>
		<h:outputText id="caseSearchhOutText12"			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"			styleClass="dataScrollerText" rendered="#{rowsCount>0}">
		</h:outputText>
	</t:dataScroller>--%>
	
	
	
	<h:dataTable rendered="#{caseSearchDataBean.noData}"		styleClass="dataTable" cellspacing="0" width="100%" border="1"		headerClass="tableHead" rowClasses="rowone,row_alt"		id="searchCaseDataTable1">
		<h:column id="searchColumn1">
			<f:facet name="header">
				<h:outputText id="caseSearchhOutText13" styleClass="outputLabel"					value="#{msg['label.case.associatedCaseAndCorrespondence.created']}" />
			</f:facet>
		</h:column>
		<h:column id="searchColumn2">
			<f:facet name="header">
				<h:outputText id="caseSearchhOutText14" styleClass="outputLabel"					value="#{msg['label.case.associatedCaseAndCorrespondence.entityName']}" />
			</f:facet>
		</h:column>
		<h:column id="searchColumn3">
			<f:facet name="header">
				<h:outputText id="caseSearchhOutText15" styleClass="outputLabel"					value="#{msg['label.case.associatedCaseAndCorrespondence.entityType']}" />
			</f:facet>
		</h:column>
		<h:column id="searchColumn4">
			<f:facet name="header">
				<h:outputText id="caseSearchhOutText16" styleClass="outputLabel"					value="#{msg['label.case.associatedCaseAndCorrespondence.status']}" />
			</f:facet>
		</h:column>
		<h:column id="searchColumn5">
			<f:facet name="header">
				<h:outputText id="caseSearchhOutText17" styleClass="outputLabel"					value="#{msg['label.case.associatedCaseAndCorrespondence.assignedTo']}" />
			</f:facet>
		</h:column>
		<h:column id="searchColumn6">
			<f:facet name="header">
				<h:outputText id="caseSearchhOutText18" styleClass="outputLabel"					value="#{msg['label.case.associatedCaseAndCorrespondence.caseType']}" />
			</f:facet>
		</h:column>
		<h:column id="searchColumn7">
			<f:facet name="header">
				<h:outputText id="caseSearchhOutText19" styleClass="outputLabel"					value="#{msg['label.case.associatedCaseAndCorrespondence.priority']}" />
			</f:facet>
		</h:column>
		<h:column id="searchColumn8">
			<f:facet name="header">
				<h:outputText id="caseSearchhOutText20" styleClass="outputLabel"					value="#{msg['label.case.associatedCaseAndCorrespondence.lobs']}" />
			</f:facet>
		</h:column>
		<f:facet name="footer">
			<m:div id="nodatabankinfo" align="center">
				<h:outputText id="caseSearchhOutText21" value="#{msg['value.noData']}"					rendered="#{customFieldDataBean.noData}"></h:outputText>
			</m:div>
		</f:facet>
	</h:dataTable>

</m:section>
