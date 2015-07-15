<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>


<f:loadBundle var="srCrspd"
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceSearch" />

<script type="text/javascript">
	function findObjectByPartOfID(partOfID) {
		for (i = 0; i < document.forms.length; i++) {
			for (j = 0; j < document.forms[i].elements.length; j++) {
				var idValue = document.forms[i].elements[j].id;
				if (idValue.indexOf(partOfID) != -1) {
					G_isFirstTime = false;
					G_countObject = document.forms[i].elements[j];
					return document.forms[i].elements[j];
				}
			}
		}
		return null;
	}

	function onChangeReassign() {
		var hiddenButtonObject = findObjectByPartOfID('reassignValueChangeID');
		hiddenButtonObject.click();
		return null;
	}
</script>

<m:div sid="showhide_results"
	rendered="#{cs_searchCorrespondenceDataBean.showResultsDiv}">
	<m:div styleClass="padb" />
	<m:br />
	<m:br />
	<m:inputHidden name="security"
		value="#{cs_correspondenceControllerBean.securityfield}"></m:inputHidden>
	<h:commandButton id="reassignValueChangeID" styleClass="hide"
		value="Hidden Button To Click"
		action="#{cs_searchCorrespondenceControllerBean.onChangeReassignAll}" />
		
		<%-- Code added for CR:ESPRD00798895 start --%>
			 <h:commandButton id="saveNContinueId" styleClass="hide"  onclick="javascript:flagWarn=false;" 
				value="Hidden Button To Click"
				action="#{cs_searchCorrespondenceControllerBean.reassignPageSave}" />
				<h:inputHidden id="continueTypeId" value="#{cs_searchCorrespondenceDataBean.continueType}" />
		<%-- Code added for CR:ESPRD00798895 end--%>	
			
	<m:div align="right">
	<h:outputLabel id="PRGCMGTOLL4412321" for="department" >
		<h:outputText id="PRGCMGTOT1768"
			value="Reassign all Correspondences to "></h:outputText>
	</h:outputLabel>
		<h:selectOneMenu id="department"
			value="#{cs_searchCorrespondenceDataBean.selectedDeptAll}"
			onchange="javascript:onChangeReassign();">
			<f:selectItem itemLabel="" itemValue="" />
			<f:selectItems
				value="#{cs_searchCorrespondenceDataBean.reassignAllDeptUsersList}" />
		</h:selectOneMenu>

		<m:br />
		<m:br />
		<h:dataTable cellspacing="0" styleClass="dataTable" width="99%"
		rendered="#{!cs_searchCorrespondenceDataBean.noDataFlag}"
			rowClasses="row_alt,row" first="#{cs_searchCorrespondenceDataBean.startIndexForSrchRslts}"
			value="#{cs_searchCorrespondenceDataBean.searchResults}" rows="10"
			id="SearchCorrespondenceTable" var="crList"
			columnClasses="columnClass" headerClass="headerClass"
			footerClass="footerClass">

			<h:column id="PRGCMGTC136">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD230" columns="2">
						<h:outputLabel id="createdOn" for="panel1"
							value="#{srCrspd['label.crspd.createdOn']}" />
						<h:panelGroup id="panel1">
							<t:div styleClass="alignLeft">
								<t:commandLink id="crtdOnCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crtdOnCommandLink1'}">
									<m:div title="for ascending" styleClass="sort_asc" />
									<f:attribute name="columnName" value="openDate" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="crtdOnCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crtdOnCommandLink2'}">
									<m:div title="for descending" styleClass="sort_desc" />
									<f:attribute name="columnName" value="openDate" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1769" value="#{crList.createdOn}" />
				<f:param name="createdOnRe" value="#{crList.createdOn}" />
			</h:column>
			<h:column id="PRGCMGTC137">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD231" columns="2">
						<h:outputLabel id="entityName" for="panel2"
							value="#{srCrspd['label.crspd.entityName']}" />
						<h:panelGroup id="panel2">
							<t:div styleClass="alignLeft">
								<t:commandLink id="EntyNmCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='EntyNmCommandLink1'}">
									<m:div title="for ascending" styleClass="sort_asc" />
									<f:attribute name="columnName" value="entityName" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="EntyNmCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='EntyNmCommandLink2'}">
									<m:div title="for descending" styleClass="sort_desc" />
									<f:attribute name="columnName" value="entityName" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1770" value="#{crList.entityName}" />
				<f:param name="entityNameRe" value="#{crList.entityName}" />
			</h:column>
			<h:column id="PRGCMGTC138">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD232" columns="2">
						<h:outputLabel id="entityType" for="panel3"
							value="#{srCrspd['label.crspd.entityType']}" />
						<h:panelGroup id="panel3">
							<t:div styleClass="alignLeft">
								<t:commandLink id="enTypCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='enTypCommandLink1'}">
									<m:div title="for ascending" styleClass="sort_asc" />
									<f:attribute name="columnName" value="cmnEntityTypeCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="enTypCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='enTypCommandLink2'}">
									<m:div title="for descending" styleClass="sort_desc" />
									<f:attribute name="columnName" value="cmnEntityTypeCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1771" value="#{crList.entityType}" />
				<f:param name="entityTypeR" value="#{crList.entityType}" />
			</h:column>
			<h:column id="PRGCMGTC139">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD233" columns="2">
						<h:outputLabel id="status" for="panel4"
							value="#{srCrspd['label.crspd.status']}" />
						<h:panelGroup id="panel4">
							<t:div styleClass="alignLeft">
								<t:commandLink id="namCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='namCommandLink1'}">
									<m:div title="for ascending" styleClass="sort_asc" />
									<f:attribute name="columnName" value="statusCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="namCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='namCommandLink2'}">
									<m:div title="for descending" styleClass="sort_desc" />
									<f:attribute name="columnName" value="statusCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1772" value="#{crList.status}" />
				<f:param name="statusR" value="#{crList.status}" />
			</h:column>
			<h:column id="PRGCMGTC140">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD234" columns="2">
						<h:outputLabel id="assignedTo" for="panel5"
							value="#{srCrspd['label.crspd.assignedTo']}" />
						<h:panelGroup id="panel5">
							<t:div styleClass="alignLeft">
								<t:commandLink id="assToCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='assToCommandLink1'}">
									<m:div title="for ascending" styleClass="sort_asc" />
									<f:attribute name="columnName" value="assignedTo" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="assToCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='assToCommandLink2'}">
									<m:div title="for descending" styleClass="sort_desc" />
									<f:attribute name="columnName" value="assignedTo" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1773" value="#{crList.assignedTo}" />
				<f:param name="assignedToR" value="#{crList.assignedTo}" />
			</h:column>
			<h:column id="PRGCMGTC141">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD235" columns="2">
						<h:outputLabel id="category" for="panel6"
							value="#{srCrspd['label.crspd.category']}" />
						<h:panelGroup id="panel6">
							<t:div styleClass="alignLeft">
								<t:commandLink id="catCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='catCommandLink1'}">
									<m:div title="for ascending" styleClass="sort_asc" />
									<f:attribute name="columnName" value="description" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="catCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='catCommandLink2'}">
									<m:div title="for descending" styleClass="sort_desc" />
									<f:attribute name="columnName" value="description" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1774"
					value="#{crList.categoryDescription}" />
				<f:param name="catDescR" value="#{crList.categoryDescription}" />
			</h:column>
			<h:column id="PRGCMGTC142">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD236" columns="2">
						<h:outputLabel id="businessUnit" for="panel7"
							value="#{srCrspd['label.crspd.businessUnit']}" />
						<h:panelGroup id="panel7">
							<t:div styleClass="alignLeft">
								<t:commandLink id="buCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='buCommandLink1'}">
									<m:div title="for ascending" styleClass="sort_asc" />
									<f:attribute name="columnName" value="lobHierarchyDescription" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="buCommandLink12"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='buCommandLink2'}">
									<m:div title="for descending" styleClass="sort_desc" />
									<f:attribute name="columnName" value="lobHierarchyDescription" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1775" value="#{crList.businessUnit}" />
				<f:param name="businessUnitR" value="#{crList.businessUnit}" />
			</h:column>
			<h:column id="PRGCMGTC143">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD237" columns="2">
						<h:outputLabel id="crn" for="panel8"
							value="#{srCrspd['label.crspd.crn']}" />
						<h:panelGroup id="panel8">
							<t:div styleClass="alignLeft">
								<t:commandLink id="crnCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crnCommandLink1'}">
									<m:div title="for ascending" styleClass="sort_asc" />
									<f:attribute name="columnName" value="correspondenceSK" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="crnCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crnCommandLink2'}">
									<m:div title="for descending" styleClass="sort_desc" />
									<f:attribute name="columnName" value="correspondenceSK" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="PRGCMGTOT1776"
					value="#{crList.correspondenceNumber}" />
				<f:param name="crspdSKR" value="#{crList.correspondenceNumber}" />
				<f:param name="Send.CorrespondenceSk" value="SendCorrespondenceSk" />
			</h:column>
			<h:column id="PRGCMGTC144">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD238" columns="2">
						<h:outputLabel id="reassignTo" for="panel9"
							value="#{srCrspd['label.crspd.reassignto']}" />
						<h:panelGroup id="panel9">
							<t:div styleClass="alignLeft">
								<t:commandLink id="lobCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='lobCommandLink1'}">
									<m:div title="for ascending" styleClass="sort_asc" />
									<f:attribute name="columnName" value="lobCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="lobCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortReassignSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='lobCommandLink2'}">
									<m:div title="for descending" styleClass="sort_desc" />
									<f:attribute name="columnName" value="lobCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<%--Defect fixed below "right" is replaced by "left" --%>
				<%--Code added to render variable and output text is added to display drop down values in text format for CR:ESPRD00798895--%>
				<m:div style="text-align:left">
				<h:outputLabel id="checkboxColumnLabelCR18" for="departmentId" styleClass="hide" value="#{srCrspd['label.crspd.reassignto']}" ></h:outputLabel>
					<h:selectOneMenu id="departmentId" value="#{crList.selectedDept}" rendered="#{empty cs_searchCorrespondenceDataBean.selectedDeptAll}">
						<f:selectItem itemValue="" itemLabel="" />
						<f:selectItems value="#{crList.deptList}" />
					</h:selectOneMenu>
					<h:outputText id="PROVIDEROLT20120731164811670" value="#{crList.reassignAllDesc}" rendered="#{not empty cs_searchCorrespondenceDataBean.selectedDeptAll}"></h:outputText>
				</m:div>
				<f:param name="selectedDeptR"
					value="#{cs_correspondenceControllerBean.selectedDept}" />
			</h:column>
		</h:dataTable>

		<m:div styleClass="floatl">
		</m:div>
		<m:div styleClass="searchResults">

			<!--<t:dataScroller id="CMGTTOMDS64" for="SearchCorrespondenceTable"
				paginator="true" onclick="flagWarn=false;"
				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
				firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
				rowsCountVar="rowsCount" style="text-decoration:underline;">
				<f:facet name="previous">
					<h:outputText id="PRGCMGTOT1777"
						style="float:right;position:relative;bottom:-2px;text-decoration:underline;"
						value="#{srCrspd['label.crspd.prev']}" rendered="#{pageIndex > 1}">
					</h:outputText>
				</f:facet>
				<f:facet name="next">
					<h:outputText id="PRGCMGTOT1778"
						style="float:right;position:relative;bottom:-2px;text-decoration:underline;"
						value="#{srCrspd['label.crspd.nxt']}"
						rendered="#{pageIndex < pageCount}">
					</h:outputText>
				</f:facet>
				<h:outputText id="PRGCMGTOT1779" rendered="#{rowsCount > 0}"
					value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
					style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
						
		-->
		<%--Above code is commented and below code added for CR:ESPRD00798895 for pagination--%>
		<h:panelGroup id="CMGTTOMDS9" rendered="#{!cs_searchCorrespondenceDataBean.noDataFlag}">
				<h:outputText id="PRGCMGTOT265"
					value="#{cs_searchCorrespondenceDataBean.startRecordNo}-#{cs_searchCorrespondenceDataBean.endRecord} of #{cs_searchCorrespondenceDataBean.count}"
					style="font-weight:bold;float:left;"></h:outputText>
				
				<t:commandLink id="MEMINFCL73011"
					onclick="javascript:flagWarn=false;return warnBeforeGoToNext('N');"
					action="#{cs_searchCorrespondenceControllerBean.next}"
					rendered="#{cs_searchCorrespondenceDataBean.showNext}" style="float:right;CURSOR:hand;">
					<h:outputText id="PRGCMGTOT2651" value=">>" styleClass="underline"/>
				</t:commandLink>
				<h:outputText id="MEMINFOT460412" escape="false" value="&nbsp;"
					style="float:right;" />
				
				<t:commandLink id="MEMINFCL7311315"
					onclick="javascript:flagWarn=false;return warnBeforeGoToNext('NO');"
					action="#{cs_searchCorrespondenceControllerBean.nextOne}"
					rendered="#{cs_searchCorrespondenceDataBean.showNextOne}">
					<h:outputText id="MEMINFOT46051315"
						value="#{cs_searchCorrespondenceDataBean.currentPage+2}" style="float:right;CURSOR:hand;font-weight:normal" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT26525" escape="false" value="&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73112"
					onclick="javascript:flagWarn=false;return warnBeforeGoToNext('N');"
					action="#{cs_searchCorrespondenceControllerBean.next}"
					rendered="#{cs_searchCorrespondenceDataBean.showNext}">
					<h:outputText id="PRGCMGTOT2653"
						value="#{cs_searchCorrespondenceDataBean.currentPage+1}" style="float:right;CURSOR:hand;font-weight:normal" />
				</t:commandLink>
				<h:outputText id="MEMINFOT46061" escape="false" value="&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73213"
					onclick="javascript:flagWarn=false;return warnBeforeGoToNext();">
					<h:outputText id="PRGCMGTOT2654"
						value="#{cs_searchCorrespondenceDataBean.currentPage}" 
						style="float:right;CURSOR:hand;font-weight:bold"></h:outputText>
				</t:commandLink>
				<h:outputText id="PRGCMGTOT2655" escape="false" value="&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73315"
					onclick="javascript:flagWarn=false;return warnBeforeGoToNext('P');"
					action="#{cs_searchCorrespondenceControllerBean.previous}"
					rendered="#{cs_searchCorrespondenceDataBean.showPrevious}">
					<h:outputText id="PRGCMGTOT2656"
						value="#{cs_searchCorrespondenceDataBean.currentPage-1}" style="float:right;CURSOR:hand;font-weight:normal" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT2657" escape="false" value="&nbsp;&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73416"
					onclick="javascript:flagWarn=false;return warnBeforeGoToNext('P');"
					action="#{cs_searchCorrespondenceControllerBean.previous}"
					rendered="#{cs_searchCorrespondenceDataBean.showPrevious}" style="float:right">
					<h:outputText id="PRGCMGTOT2658" value="<<" styleClass="underline" />
				</t:commandLink>
				<m:br></m:br>
				<m:br></m:br>
			</h:panelGroup>
		
		
		</m:div>
		
		<h:dataTable var="crList"
		rendered="#{cs_searchCorrespondenceDataBean.noDataFlag}" styleClass="dataTable"
		cellspacing="0" width="100%" border="1" headerClass="tableHead"
		rowClasses="rowone,row_alt" id="searchcrDataTable1">
		<h:column id="reasgnCrSearchcreated1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1757"
					value="#{srCrspd['label.crspd.createdOn']}"/>
			</f:facet>
		</h:column>
		<h:column id="entitynam1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1758"
					value="#{srCrspd['label.crspd.entityName']}" />
			</f:facet>
		</h:column>
		<h:column id="entityTyp1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1759"
					value="#{srCrspd['label.crspd.entityType']}" />
			</f:facet>
		</h:column>
		<h:column id="CrSearchstate1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1760"
					value="#{srCrspd['label.crspd.status']}" />
			</f:facet>
		</h:column>
		<h:column id="CrSearchassigned1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1761"
					value="#{srCrspd['label.crspd.assignedTo']}" />
			</f:facet>
		</h:column>
		<h:column id="caT1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1762"
					value="#{srCrspd['label.crspd.category']}"  />
			</f:facet>
		</h:column>
		<h:column id="bunit1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1763"
					value="#{srCrspd['label.crspd.businessUnit']}" />
			</f:facet>
		</h:column>
		<h:column id="crn1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1764"
					value="#{srCrspd['label.crspd.crn']}"  />
			</f:facet>
		</h:column>

	</h:dataTable>
	<m:table id="PROVIDERMMT20120731164811671" styleClass="dataTable" width="100%" border="0"
		rendered="#{cs_searchCorrespondenceDataBean.noDataFlag}">
		<m:tr>
			<m:td align="center">
				<h:outputText id="PRGCMGTOT1765"
					value="#{srCrspd['label.reassignSearchCr.noData']}" />
			</m:td>
		</m:tr>
	</m:table>
		<m:br />
	</m:div>
</m:div>
