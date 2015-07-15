<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<portlet:defineObjects />


<f:loadBundle var="crsrch"
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceSearch" />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceLog"
	var="crspd" />

<h:inputHidden id="srchCorresListSize"
	value="#{cs_searchCorrespondenceDataBean.searchCorrespondenceListSize}"></h:inputHidden>
<m:div sid="showhide_results"
	rendered="#{cs_searchCorrespondenceDataBean.showResultsDiv}">
	<h:commandButton id="checkAllSubmit"
		value="#{crspd['label.crspd.click']}" styleClass="hide"
		action="#{CorrespondenceControllerBean.selectAllSubmit}" />
	<h:commandButton id="unCheckAllSubmit"
		value="#{crspd['label.crspd.click']}" styleClass="hide"
		action="#{CorrespondenceControllerBean.deSelectAllSubmit}" />

	<m:div styleClass="padb" />
	<m:div styleClass="infoTitle">
		<h:outputText id="PRGCMGTOT915"
			value="#{crspd['label.crspd.searchResults']}"></h:outputText>

		<m:table id="PROVIDERMMT20120731164811392" width="100%" styleClass="tableBar">
			<m:div align="right">
				<m:td>
					<h:selectBooleanCheckbox id="selAllRows4Srch"
						onclick="javascript:flagWarn=false;focusThis(this.id);callMethod('logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:searchforAssCRSPDResultsSubview', 'checkAllSubmit')"
						value="#{cs_searchCorrespondenceDataBean.searchSelectAll}"
						disabled="#{cs_searchCorrespondenceDataBean.searchSelectAll}">
					</h:selectBooleanCheckbox>
					<h:outputLabel id="PRGCMGTOLL472" for="selAllRows4Srch"
						value="Select All Rows" />
				</m:td>
				<m:td>
					<h:selectBooleanCheckbox id="deselAllRows4Srch"
						onclick="javascript:flagWarn=false;focusThis(this.id);callMethod('logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:searchforAssCRSPDResultsSubview', 'unCheckAllSubmit')"
						value="#{cs_searchCorrespondenceDataBean.searchDeSelectAll}"
						disabled="#{cs_searchCorrespondenceDataBean.searchDeSelectAll}">
					</h:selectBooleanCheckbox>
					<h:outputLabel id="PRGCMGTOLL473" for="deselAllRows4Srch"
						value="Deselect All Rows" />
				</m:td>
			</m:div>
		</m:table>


		<h:dataTable cellspacing="0" styleClass="dataTable" width="99%"
			rowClasses="row_alt,row"
			first="#{cs_searchCorrespondenceDataBean.startIndexForAscSrchRslts}"
			value="#{cs_searchCorrespondenceDataBean.searchResults}" rows="10"
			id="SearchCorrespondenceTable" var="crList"
			columnClasses="columnClass" headerClass="headerClass"
			footerClass="footerClass"
			onmouseover="return tableMouseOver(this, event);"
			onmouseout="return tableMouseOut(this, event);">
			<h:column id="SearchCrTableId1">

				<f:facet name="header">
					<h:panelGrid columns="2" id="SearchCrTableId2">
						<h:outputLabel for="panel8" value="#{crsrch['label.crspd.crn']}"
							id="SearchCrTableId3" />
						<h:panelGroup id="panel8">
							<t:div styleClass="alignLeft" id="SearchCrTableId4">
								<t:commandLink id="crnCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crnCommandLink1'}">

									<h:graphicImage id="PROVIDERGI20120731164811393" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="correspondenceSK" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft" id="SearchCrTableId5">
								<t:commandLink id="crnCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crnCommandLink2'}">

									<h:graphicImage id="PROVIDERGI20120731164811394" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="correspondenceSK" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT916"
					value="#{crList.correspondenceNumber}"></h:outputText>
			</h:column>
			<h:column id="PRGCMGTC35">
				<f:facet name="header">
					<h:panelGrid columns="2" id="SearchCrTableId6">
						<h:outputLabel for="panel1" id="SearchCrTableId7"
							value="#{crspd['label.crspd.openDate']}" />
						<h:panelGroup id="panel1">
							<t:div styleClass="alignLeft" id="SearchCrTableId8">
								<t:commandLink id="crtdOnCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crtdOnCommandLink1'}">

									<h:graphicImage id="PROVIDERGI20120731164811395" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="openDate" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft" id="SearchCrTableId9">
								<t:commandLink id="crtdOnCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crtdOnCommandLink2'}">

									<h:graphicImage id="PROVIDERGI20120731164811396" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="openDate" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="crtdOnr" value="#{crList.createdOnstr}" />
			</h:column>
			<h:column id="SearchCrTableId10">

				<f:facet name="header">
					<h:panelGrid columns="2" id="SearchCrTableId11">
						<h:outputLabel id="PRGCMGTOLL474" for="panel2"
							value="#{crsrch['label.crspd.entityName']}" />
						<h:panelGroup id="panel2">
							<t:div styleClass="alignLeft" id="SearchCrTableId12">
								<t:commandLink id="EntyNmCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='EntyNmCommandLink1'}">

									<h:graphicImage id="PROVIDERGI20120731164811397" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="entityName" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft" id="SearchCrTableId13">
								<t:commandLink id="EntyNmCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='EntyNmCommandLink2'}">

									<h:graphicImage id="PROVIDERGI20120731164811398" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="entityName" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="enityNmr" value="#{crList.entityName}" />
			</h:column>
			<h:column id="SearchCrTableId14">

				<f:facet name="header">
					<h:panelGrid columns="2" id="SearchCrTableId15">
						<h:outputLabel for="panel3" id="SearchCrTableId16"
							value="#{crsrch['label.crspd.entityType']}" />
						<h:panelGroup id="panel3">
							<t:div styleClass="alignLeft" id="SearchCrTableId17">
								<t:commandLink id="enTypCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='enTypCommandLink1'}">

									<h:graphicImage id="PROVIDERGI20120731164811399" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="cmnEntityTypeCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft" id="SearchCrTableId18">
								<t:commandLink id="enTypCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='enTypCommandLink2'}">

									<h:graphicImage id="PROVIDERGI20120731164811400" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="cmnEntityTypeCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="entitytyr" value="#{crList.entityType}" />
			</h:column>

			<h:column id="SearchCrTableId19">

				<f:facet name="header">
					<h:panelGrid columns="2" id="SearchCrTableId20">
						<h:outputLabel for="panel4" id="SearchCrTableId21"
							value="#{crsrch['label.crspd.status']}" />
						<h:panelGroup id="panel4">
							<t:div styleClass="alignLeft" id="SearchCrTableId22">
								<t:commandLink id="namCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='namCommandLink1'}">

									<h:graphicImage id="PROVIDERGI20120731164811401" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="statusCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft" id="SearchCrTableId23">
								<t:commandLink id="namCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='namCommandLink2'}">

									<h:graphicImage id="PROVIDERGI20120731164811402" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="statusCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="statusr" value="#{crList.status}" />
			</h:column>
			<h:column id="SearchCrTableId24">

				<f:facet name="header">
					<h:panelGrid columns="2" id="SearchCrTableId25">
						<h:outputLabel for="panel5" id="SearchCrTableId26"
							value="#{crsrch['label.crspd.assignedTo']}" />
						<h:panelGroup id="panel5">
							<t:div styleClass="alignLeft" id="SearchCrTableId27">
								<t:commandLink id="assToCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='assToCommandLink1'}">

									<h:graphicImage id="PROVIDERGI20120731164811403" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="assignedTo" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft" id="SearchCrTableId28">
								<t:commandLink id="assToCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='assToCommandLink2'}">

									<h:graphicImage id="PROVIDERGI20120731164811404" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="assignedTo" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="assTor" value="#{crList.assignedTo}" />
			</h:column>

			<h:column id="SearchCrTableId29">

				<f:facet name="header">
					<h:panelGrid columns="2" id="SearchCrTableId30">
						<h:outputLabel for="panel6" id="SearchCrTableId31"
							value="#{crsrch['label.crspd.category']}" />
						<h:panelGroup id="panel6">
							<t:div styleClass="alignLeft" id="SearchCrTableId32">
								<t:commandLink id="catCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='catCommandLink1'}">

									<h:graphicImage id="PROVIDERGI20120731164811405" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="description" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft" id="SearchCrTableId33">
								<t:commandLink id="catCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='catCommandLink2'}">

									<h:graphicImage id="PROVIDERGI20120731164811406" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="description" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="categoryr" value="#{crList.categoryDescription}" />
			</h:column>

			<h:column id="SearchCrTableId34">

				<f:facet name="header">
					<h:panelGrid columns="2" id="SearchCrTableId35">
						<h:outputLabel for="panel6" id="SearchCrTableId36"
							value="#{crsrch['label.crspd.subject']}" />
						<h:panelGroup id="panel7">
							<t:div styleClass="alignLeft" id="SearchCrTableId37">
								<t:commandLink id="subCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='subCommandLink1'}">

									<h:graphicImage id="PROVIDERGI20120731164811407" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="subjectCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft" id="SearchCrTableId38">
								<t:commandLink id="subCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='subCommandLink2'}">

									<h:graphicImage id="PROVIDERGI20120731164811408" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif" />
									<f:attribute name="columnName" value="subjectCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="subr" value="#{crList.subjectCode}" />
			</h:column>

			<h:column id="SearchCrTableId39">
				<%-- <f:facet name="header">
					<h:selectBooleanCheckbox id="chk" value="Link" />
				</f:facet> --%>
				<f:facet name="header">
					<h:outputText id="chk" value="#{crspd['label.crspd.linkToCR']}" />
				</f:facet>
				<h:outputLabel id="checkboxColumnLabelCR15" for="checkBoxr" 
							styleClass="hide" value="#{crspd['label.crspd.linkToCR']}" ></h:outputLabel>
				<h:selectBooleanCheckbox id="checkBoxr" value="#{crList.checkBox}"
					disabled="#{cs_searchCorrespondenceDataBean.searchSelectAll}">
				</h:selectBooleanCheckbox>

			</h:column>
		</h:dataTable>

		<m:div styleClass="floatl">
		</m:div>
		
		<m:div styleClass="searchResults">

			<!--<t:dataScroller id="CMGTTOMDS36" for="SearchCorrespondenceTable"
				paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
				paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"
				pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"
				lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
				style="float:right;position:relative;bottom:-6px;text-decoration:underline;"
				onclick="javascript:flagWarn=false;">


				<f:facet name="previous">
					<h:outputText id="PRGCMGTOT917" style="text-decoration:underline;"
						value="#{ref['label.ref.lt']}" rendered="#{pageIndex > 1}">
					</h:outputText>
				</f:facet>
				<f:facet name="next">
					<h:outputText id="PRGCMGTOT918" style="text-decoration:underline;"
						value="#{ref['label.ref.gt']}" rendered="#{pageIndex < pageCount}">
					</h:outputText>
				</f:facet>
				<h:outputText id="PRGCMGTOT919" rendered="#{rowsCount > 0}"
					value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
					style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>

		-->
		<%--Above code is commented and below code added for CR:ESPRD00798895 for pagination --%>
			<h:panelGroup id="CMGTTOMDS9" >
				<h:outputText id="PRGCMGTOT265"
					value="#{cs_searchCorrespondenceDataBean.startRecordNo}-#{cs_searchCorrespondenceDataBean.endRecord} of #{cs_searchCorrespondenceDataBean.count}"
					style="font-weight:bold;float:left;"></h:outputText>
				
				<t:commandLink id="MEMINFCL73011"
					onclick="javascript:flagWarn=false;"
					action="#{cs_searchCorrespondenceControllerBean.next}"
					rendered="#{cs_searchCorrespondenceDataBean.showNext}" style="float:right;CURSOR:hand;">
					<h:outputText id="PRGCMGTOT2651" value=">>" styleClass="underline"/>
				</t:commandLink>
				<h:outputText id="MEMINFOT460412" escape="false" value="&nbsp;"
					style="float:right;" />
				
				<t:commandLink id="MEMINFCL7311315"
					onclick="javascript:flagWarn=false;"
					action="#{cs_searchCorrespondenceControllerBean.nextOne}"
					rendered="#{cs_searchCorrespondenceDataBean.showNextOne}">
					<h:outputText id="MEMINFOT46051315"
						value="#{cs_searchCorrespondenceDataBean.currentPage+2}" style="float:right;CURSOR:hand;font-weight:normal" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT26525" escape="false" value="&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73112"
					onclick="javascript:flagWarn=false;"
					action="#{cs_searchCorrespondenceControllerBean.next}"
					rendered="#{cs_searchCorrespondenceDataBean.showNext}">
					<h:outputText id="PRGCMGTOT2653"
						value="#{cs_searchCorrespondenceDataBean.currentPage+1}" style="float:right;CURSOR:hand;font-weight:normal" />
				</t:commandLink>
				<h:outputText id="MEMINFOT46061" escape="false" value="&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73213"
					onclick="javascript:flagWarn=false;">
					<h:outputText id="PRGCMGTOT2654"
						value="#{cs_searchCorrespondenceDataBean.currentPage}" 
						style="float:right;CURSOR:hand;font-weight:bold"></h:outputText>
				</t:commandLink>
				<h:outputText id="PRGCMGTOT2655" escape="false" value="&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73315"
					onclick="javascript:flagWarn=false;"
					action="#{cs_searchCorrespondenceControllerBean.previous}"
					rendered="#{cs_searchCorrespondenceDataBean.showPrevious}">
					<h:outputText id="PRGCMGTOT2656"
						value="#{cs_searchCorrespondenceDataBean.currentPage-1}" style="float:right;CURSOR:hand;font-weight:normal" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT2657" escape="false" value="&nbsp;&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73416"
					onclick="javascript:flagWarn=false;"
					action="#{cs_searchCorrespondenceControllerBean.previous}"
					rendered="#{cs_searchCorrespondenceDataBean.showPrevious}" style="float:right">
					<h:outputText id="PRGCMGTOT2658" value="<<" styleClass="underline" />
				</t:commandLink>
				<m:br></m:br>
				<m:br></m:br>
			</h:panelGroup>
		
		</m:div>
		<m:br />

	</m:div>
</m:div>
