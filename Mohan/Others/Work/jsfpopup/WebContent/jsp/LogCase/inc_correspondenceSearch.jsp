<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:section styleClass="otherbg" id="corrSearSec1Id">
	<t:htmlTag value="h4" styleClass="warning" id="corrSearHtmlTg1Id">
		<h:outputText id="corrSearOutTxt1Id"			value="#{msg['label.case.associatedCaseAndCorrespondence.correspondenceRecordSrch']}" />
	    <%-- Added for Defect ESPRD00650091 --%>
	    <m:div id="corrSearchhDiv0" styleClass="infoActions">
		     <h:commandButton id="cancelC0rrSearchScreenId" onclick="focusPage('caseAscCrspondence');"	
				    action="#{logCaseControllerBean.cancelCorrespondenceSearchScreen}"	
				    style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:11px;WIDTH:45px;BORDER:0;text-align:left;font-family:Verdana;"					
                    disabled="#{logCaseDataBean.disableScreenField}"				
	                 value="#{msg['label.case.cancel']}">
		     </h:commandButton>
		</m:div>   
	</t:htmlTag>
	<m:div id="corrSearDiv1Id">
	<f:subview id="logcaseSearchCrSubview1">
		<jsp:include
			page="inc_logcaseSearchCorrespondence.jsp" />
	</f:subview>
	</m:div>
	
	<m:div rendered="#{logCaseDataBean.showResultsDiv}" id="corrSearDiv2Id">
		<m:table width="100%" styleClass="tableBar" id="corrSearTab1Id">
			<m:div align="right" id="corrSearDiv3Id">
				<m:td id="corrSearTab1C1Id">
                   	<h:selectBooleanCheckbox id="selAllRows4Srch"                     	valueChangeListener="#{logCaseControllerBean.selectAllSubmit}"	                    	value="#{logCaseDataBean.searchSelectAll}"                     	disabled="#{logCaseDataBean.searchSelectAll}"						onclick="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');this.form.submit();">
                   	</h:selectBooleanCheckbox>
					<h:outputLabel for="selAllRows4Srch" id="corrSearOutLbl1Id"						value="#{msg['label.case.associatedCaseAndCorrespondence.selAllRows']}"/>
				</m:td>
				<m:td id="corrSearTab1C2Id">
		           	<h:selectBooleanCheckbox id="deselAllRows4Srch" 			           		value="#{logCaseDataBean.searchDeSelectAll}" 		           		disabled="#{logCaseDataBean.searchDeSelectAll}" 			           	valueChangeListener="#{logCaseControllerBean.deSelectAllSubmit}"						onclick="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');this.form.submit();">					         
                   	</h:selectBooleanCheckbox>
					<h:outputLabel for="deselAllRows4Srch" id="corrSearOutLbl2Id"						value="#{msg['label.case.associatedCaseAndCorrespondence.deSelAllRows']}"/>
				</m:td> 
			</m:div>	
		</m:table> 
		
		<m:anchor name="caseAscCorrSearchSortFocusPage"></m:anchor>
		<%-- 'rowIndexVar' attribute is added in the dataTable for the defect ESPRD00684566 --%>
		<t:dataTable cellspacing="0" styleClass="dataTable" width="99%"			
		             rowClasses="row_alt,row"	value="#{logCaseDataBean.searchResults}" rows="10"			
		             id="crSearchSpanstable" var="crSearchSpanResult"			
		             first="#{logCaseDataBean.htmlSearchResults}"			
		             columnClasses="columnClass" headerClass="headerClass"			
		             footerClass="footerClass"	onmouseover="return tableMouseOver(this, event);"			
		             onmouseout="return tableMouseOut(this, event);"
		             rowOnClick="javascript:flagWarn=false;focusPage('corrAssWithCaseFocusPage');childNodes[0].lastChild.click();"
		             rowIndexVar="index">		

			<t:column id="crSearchcolumn1">
				<f:facet name="header">
					<t:panelGrid columns="2" id="crSearchSpanstablePanGri1Id">
						<h:outputLabel for="crSearchCreatedCommandLink1" id="crSearchSpanstableOutLbl1Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.created']}" />
						<t:panelGrid id="crSearchSpanstablePanGri10Id">
							<t:div id="correspondencSearchTDiv001" styleClass="alignLeft">
							<t:commandLink id="crSearchCreatedCommandLink1"	onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"							styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchCreatedCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="crSearchSpanstableImg1Id"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="CRSearch_Created" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="correspondencSearchTDiv002" styleClass="alignLeft">
							<t:commandLink id="crSearchCreatedCommandLink2"	onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"							styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchCreatedCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="crSearchSpanstableImg2Id"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="CRSearch_Created" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</t:panelGrid>
					</t:panelGrid>
				</f:facet>
				<t:commandLink id="linkr" onmousedown="javascript:flagWarn=false;focusPage('corrAssWithCaseFocusPage');"					action="#{logCaseControllerBean.associateCRToCase}" >
					<h:outputText id="crSearchOutText1" value="#{crSearchSpanResult.createdOn}" />
					<f:param name="rowIndex" value="#{index}"></f:param> <%-- Added this code for the defect ESPRD00684566 --%>
				</t:commandLink>				
			</t:column>

			<t:column id="crSearchcolumn2">
				<f:facet name="header">
					<t:panelGrid columns="2" id="crSearchSpanstablePanGri2Id">
						<h:outputLabel for="crSearchEntityNameCommandLink1" id="crSearchSpanstableOutLbl2Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.entityName']}" />
						<t:panelGrid id="crSearchSpanstablePanGri11Id">
							<t:div id="correspondencSearchTDiv003" styleClass="alignLeft">
							<t:commandLink id="crSearchEntityNameCommandLink1"								styleClass="clStyle"	onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"							actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchEntityNameCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="crSearchSpanstableImg3Id"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="CRSearch_EntityName" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="correspondencSearchTDiv004" styleClass="alignLeft">
							<t:commandLink id="crSearchEntityNameCommandLink2"								styleClass="clStyle"	onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"							actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchEntityNameCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="crSearchSpanstableImg4Id"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="CRSearch_EntityName" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</t:panelGrid>
					</t:panelGrid>
				</f:facet>
				<h:outputText id="crSearchOutText2" value="#{crSearchSpanResult.entityName}" />
			</t:column>

			<t:column id="crSearchcolumn3">
				<f:facet name="header">
					<t:panelGrid columns="2" id="crSearchSpanstablePanGri3Id">
						<h:outputLabel for="crSearchEntityTypeCommandLink1" id="crSearchSpanstableOutLbl3Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.entityType']}" />
						<t:panelGrid id="crSearchSpanstablePanGri12Id">
							<t:div id="correspondencSearchTDiv005" styleClass="alignLeft">
							<t:commandLink id="crSearchEntityTypeCommandLink1"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchEntityTypeCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="crSearchSpanstableImg5Id"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="CRSearch_EntityType" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="correspondencSearchTDiv006" styleClass="alignLeft">
							<t:commandLink id="crSearchEntityTypeCommandLink2"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchEntityTypeCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="crSearchSpanstableImg6Id"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="CRSearch_EntityType" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</t:panelGrid>
					</t:panelGrid>
				</f:facet>
				<h:outputText id="crSearchOutText3" value="#{crSearchSpanResult.entityType}" />
			</t:column>

			<t:column id="crSearchcolumn4">
				<f:facet name="header">
					<t:panelGrid columns="2" id="crSearchSpanstablePanGri4Id">
						<h:outputLabel for="crSearchStatusCommandLink1" id="crSearchSpanstableOutLbl4Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.status']}" />
						<t:panelGrid id="crSearchSpanstablePanGri13Id">
							<t:div id="correspondencSearchTDiv007" styleClass="alignLeft">
							<t:commandLink id="crSearchStatusCommandLink1"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchStatusCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="crSearchSpanstableImg7Id"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="CRSearch_Status" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="correspondencSearchTDiv008" styleClass="alignLeft">
							<t:commandLink id="crSearchStatusCommandLink2"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchStatusCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="crSearchSpanstableImg8Id"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="CRSearch_Status" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</t:panelGrid>
					</t:panelGrid>
				</f:facet>
				<h:outputText id="crSearchOutText4" value="#{crSearchSpanResult.status}" />
			</t:column>
			<t:column id="crSearchcolumn5">
				<f:facet name="header">
					<t:panelGrid columns="2" id="crSearchSpanstablePanGri5Id">
						<h:outputLabel for="crSearchAssignedToCommandLink1" id="crSearchSpanstableOutLbl5Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.assignedTo']}" />
						<t:panelGrid id="crSearchSpanstablePanGri14Id">
							<t:div id="correspondencSearchTDiv009" styleClass="alignLeft">
							<t:commandLink id="crSearchAssignedToCommandLink1"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchAssignedToCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="crSearchSpanstableImg9Id"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="CRSearch_AssignedTo" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="correspondencSearchTDiv010" styleClass="alignLeft">
							<t:commandLink id="crSearchAssignedToCommandLink2"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"							actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchAssignedToCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="crSearchSpanstableImg10Id"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="CRSearch_AssignedTo" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</t:panelGrid>
					</t:panelGrid>
				</f:facet>
				<h:outputText id="crSearchOutText5" value="#{crSearchSpanResult.assignedTo}" />
			</t:column>
		
			<t:column id="crSearchcolumn6">
				<f:facet name="header">
					<t:panelGrid columns="2" id="crSearchSpanstablePanGri6Id">
						<h:outputLabel value="#{msg['label.case.Correspondence.category']}" for="crSearchCaseTypeCommandLink1"						id="crSearchSpanstableOutLbl6Id"/>
						<t:panelGrid id="crSearchSpanstablePanGri15Id">
							<t:div id="correspondencSearchTDiv011" styleClass="alignLeft">
							<t:commandLink id="crSearchCaseTypeCommandLink1"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchCaseTypeCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="crSearchSpanstableImg11Id"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="CRSearch_CategoryDesc" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="correspondencSearchTDiv012" styleClass="alignLeft">
							<t:commandLink id="crSearchCaseTypeCommandLink2"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchCaseTypeCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="crSearchSpanstableImg12Id"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="CRSearch_CategoryDesc" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</t:panelGrid>
					</t:panelGrid>
				</f:facet>
				<h:outputText id="crSearchOutText6" value="#{crSearchSpanResult.categoryDescription}" />
			</t:column>
			<t:column id="crSearchcolumn7">
				<f:facet name="header">
					<t:panelGrid columns="2" id="crSearchSpanstablePanGri7Id">
						<h:outputLabel value="#{msg['label.case.associatedCaseAndCorrespondence.businessUnit']}" 						for="crSearchPriorityCommandLink1" id="crSearchSpanstableOutLbl7Id"/>
						<t:panelGrid id="crSearchSpanstablePanGri16Id">
							<t:div id="correspondencSearchTDiv013" styleClass="alignLeft">
							<t:commandLink id="crSearchPriorityCommandLink1"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchPriorityCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="crSearchSpanstableImg13Id"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="CRSearch_BusinessUnit" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="correspondencSearchTDiv014" styleClass="alignLeft">
							<t:commandLink id="crSearchPriorityCommandLink2"								styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchPriorityCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="crSearchSpanstableImg14Id"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="CRSearch_BusinessUnit" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</t:panelGrid>
					</t:panelGrid>
				</f:facet>
				<h:outputText id="crSearchOutText7" value="#{crSearchSpanResult.businessUnit}" />
			</t:column>
			<t:column id="crSearchcolumn8">
				<f:facet name="header">
					<t:panelGrid columns="2" id="crSearchSpanstablePanGri8Id">
						<h:outputLabel value="#{msg['label.case.associatedCaseAndCorrespondence.crn']}" 						for="crSearchCRNCommandLink1" id="crSearchSpanstableOutLbl8Id"/>
						<t:panelGrid id="crSearchSpanstablePanGri17Id">
							<t:div id="correspondencSearchTDiv015" styleClass="alignLeft">
							<t:commandLink id="crSearchCRNCommandLink1" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchCRNCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="crSearchSpanstableImg15Id"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="CRSearch_CorrNumber" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="correspondencSearchTDiv016" styleClass="alignLeft">
							<t:commandLink id="crSearchCRNCommandLink2" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchCRNCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="crSearchSpanstableImg16Id"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="CRSearch_CorrNumber" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</t:panelGrid>
					</t:panelGrid>
				</f:facet>
				<h:outputText id="crSearchOutText8" value="#{crSearchSpanResult.correspondenceNumber}" />
			</t:column>
			
			<t:column id="crSearchcolumn9">
				<f:facet name="header">
					<t:panelGrid columns="2" id="crSearchSpanstablePanGri9Id">
						<h:outputLabel for="crSearchLobCommandLink1" id="crSearchSpanstableOutLbl9Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.lobs']}" />
						<t:panelGrid id="crSearchSpanstablePanGri18Id">
							<t:div id="correspondencSearchTDiv017" styleClass="alignLeft">
							<t:commandLink id="crSearchLobCommandLink1" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchLobCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}" id="crSearchSpanstableImg17Id"
									styleClass="sort_asc" />
								<f:attribute name="columnName" value="CRSearch_LOB" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="correspondencSearchTDiv018" styleClass="alignLeft">
							<t:commandLink id="crSearchLobCommandLink2" styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"								actionListener="#{logCaseControllerBean.sortCRSearchResults}"								rendered="#{logCaseDataBean.imageRender != 'crSearchLobCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}" id="crSearchSpanstableImg18Id"
									styleClass="sort_desc" />
								<f:attribute name="columnName" value="CRSearch_LOB" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</t:panelGrid>
					</t:panelGrid>
				</f:facet>
				<h:outputText id="crSearchOutText9" value="#{crSearchSpanResult.lobCode}" />
			</t:column>
		</t:dataTable>
		<t:dataScroller for="crSearchSpanstable" paginator="true"
			onclick="javascript:flagWarn=false;focusPage('caseAscCorrSearchSortFocusPage');"
			paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
			immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
			firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
			rowsCountVar="rowsCount" styleClass="dataScroller" id="crSearchSpanstableDatascrollId">
			<f:facet name="previous">
				<h:outputText styleClass="underline" id="crSearchSpanstableDatascrollPrevId"					value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"></h:outputText>
			</f:facet>
			<f:facet name="next">
				<h:outputText styleClass="underline" id="crSearchSpanstableDatascrollNxtId"					value=" #{msg['label.greaterthan']} "					rendered="#{pageIndex < pageCount}"></h:outputText>
			</f:facet>
			<h:outputText rendered="#{rowsCount > 0}" id="crSearchSpanstableDatascrollRowsCoutId"
				value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
				styleClass="dataScrollerText" />
		</t:dataScroller>
	</m:div>
</m:section>
