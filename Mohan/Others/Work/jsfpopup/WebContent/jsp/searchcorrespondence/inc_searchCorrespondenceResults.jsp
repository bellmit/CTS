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

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<m:div sid="showhide_results"
	rendered="#{cs_searchCorrespondenceDataBean.showResultsDiv}">
	<m:div styleClass="padb" />
	<m:div styleClass="infoTitle">
		<h:outputText id="PRGCMGTOT1793" value="Search Results" />
		<t:dataTable cellspacing="0" styleClass="dataTable" width="99%"
			rowClasses="row_alt,row"
			first="#{cs_searchCorrespondenceDataBean.startIndexForSrchRslts}"
			value="#{cs_searchCorrespondenceDataBean.searchResults}" rows="10"
			id="SearchCorrespondenceTable" var="crList"
			columnClasses="columnClass" headerClass="headerClass"
			footerClass="footerClass"
			rowOnClick="lastChild.previousSibling.lastChild.click();"
			rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
			rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;">

			<h:column id="PRGCMGTC145">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD244" columns="2">
						<h:outputLabel id="PRGCMGTOLL730" for="panel1"
							value="#{srCrspd['label.crspd.createdOn']}" />
						<h:panelGroup id="panel1">
							<t:div styleClass="alignLeft">
								<t:commandLink id="crtdOnCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crtdOnCommandLink1'}">
									<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
									<f:attribute name="columnName" value="openDate" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="crtdOnCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crtdOnCommandLink2'}">
									<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
									<f:attribute name="columnName" value="openDate" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1794" value="#{crList.createdOnstr}" />
			</h:column>
			<h:column id="PRGCMGTC146">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD245" columns="2">
						<h:outputLabel id="PRGCMGTOLL731" for="panel2"
							value="#{srCrspd['label.crspd.entityName']}" />
						<h:panelGroup id="panel2">
							<t:div styleClass="alignLeft">
								<t:commandLink id="EntyNmCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='EntyNmCommandLink1'}">
									<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
									<f:attribute name="columnName" value="entityName" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="EntyNmCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='EntyNmCommandLink2'}">
									<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
									<f:attribute name="columnName" value="entityName" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1795" value="#{crList.entityName}" />
			</h:column>
			<h:column id="PRGCMGTC147">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD246" columns="2">
						<h:outputLabel id="PRGCMGTOLL732" for="panel3"
							value="#{srCrspd['label.crspd.entityType']}" />
						<h:panelGroup id="panel3">
							<t:div styleClass="alignLeft">
								<t:commandLink id="enTypCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='enTypCommandLink1'}">
									<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
									<f:attribute name="columnName" value="cmnEntityTypeCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="enTypCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='enTypCommandLink2'}">
									<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
									<f:attribute name="columnName" value="cmnEntityTypeCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1796" value="#{crList.entityType}" />
			</h:column>
			<h:column id="PRGCMGTC148">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD247" columns="2">
						<h:outputLabel id="PRGCMGTOLL733" for="panel4"
							value="#{srCrspd['label.crspd.status']}" />
						<h:panelGroup id="panel4">
							<t:div styleClass="alignLeft">
								<t:commandLink id="namCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='namCommandLink1'}">
									<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
									<f:attribute name="columnName" value="statusCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="namCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='namCommandLink2'}">
									<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
									<f:attribute name="columnName" value="statusCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1797" value="#{crList.status}" />
			</h:column>
			<h:column id="PRGCMGTC149">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD248" columns="2">
						<h:outputLabel id="PRGCMGTOLL734" for="panel5"
							value="#{srCrspd['label.crspd.assignedTo']}" />
						<h:panelGroup id="panel5">
							<t:div styleClass="alignLeft">
								<t:commandLink id="assToCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='assToCommandLink1'}">
									<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
									<f:attribute name="columnName" value="assignedTo" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="assToCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='assToCommandLink2'}">
									<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
									<f:attribute name="columnName" value="assignedTo" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1798" value="#{crList.assignedTo}" />
			</h:column>
			<h:column id="PRGCMGTC150">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD249" columns="2">
						<h:outputLabel id="PRGCMGTOLL735" for="panel6"
							value="#{srCrspd['label.crspd.category']}" />
						<h:panelGroup id="panel6">
							<t:div styleClass="alignLeft">
								<t:commandLink id="catCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='catCommandLink1'}">
									<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
									<f:attribute name="columnName" value="description" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="catCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='catCommandLink2'}">
									<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
									<f:attribute name="columnName" value="description" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1799"
					value="#{crList.categoryDescription}" />
			</h:column>
			<h:column id="PRGCMGTC151">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD250" columns="2">
						<h:outputLabel id="PRGCMGTOLL736" for="panel7"
							value="#{srCrspd['label.crspd.businessUnit']}" />
						<h:panelGroup id="panel7">
							<t:div styleClass="alignLeft">
								<t:commandLink id="buCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='buCommandLink1'}">
									<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
									<f:attribute name="columnName" value="lobHierarchyDescription" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="buCommandLink12"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='buCommandLink12'}">
									<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
									<f:attribute name="columnName" value="lobHierarchyDescription" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<h:outputText id="PRGCMGTOT1800" value="#{crList.businessUnit}" />
			</h:column>
			<h:column id="PRGCMGTC152">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD251" columns="2">
						<h:outputLabel id="PRGCMGTOLL737" for="panel8"
							value="#{srCrspd['label.crspd.crn']}" />
						<h:panelGroup id="panel8">
							<t:div styleClass="alignLeft">
								<t:commandLink id="crnCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crnCommandLink1'}">
									<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
									<f:attribute name="columnName" value="correspondenceSK" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="crnCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='crnCommandLink2'}">
									<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
									<f:attribute name="columnName" value="correspondenceSK" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>

				</f:facet>
				<t:commandLink id="PRGCMGTCL245"
					action="#{cs_searchCorrespondenceControllerBean.getCorrespondenceDetails}">

					<h:outputText id="PRGCMGTOT1801"
						value="#{crList.correspondenceNumber}"></h:outputText>

					<f:param name="crspdSK" value="#{crList.correspondenceNumber}" />
					<f:param name="Send.CorrespondenceSk" value="SendCorrespondenceSk" />
				</t:commandLink>
			</h:column>
			<h:column id="PRGCMGTC153">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD252" columns="2">
						<h:outputLabel id="PRGCMGTOLL738" for="panel9"
							value="#{srCrspd['label.crspd.lobRes']}" />
						<h:panelGroup id="panel9">
							<t:div styleClass="alignLeft">
								<t:commandLink id="lobCommandLink1"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='lobCommandLink1'}">
									<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
									<f:attribute name="columnName" value="lobCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div styleClass="alignLeft">
								<t:commandLink id="lobCommandLink2"
									actionListener="#{cs_searchCorrespondenceControllerBean.sortSearchResults}"
									rendered="#{cs_searchCorrespondenceDataBean.imageRender !='lobCommandLink2'}">
									<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
									<f:attribute name="columnName" value="lobCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="PRGCMGTOT1802" value="#{crList.lobCode}" />
			</h:column>
		</t:dataTable>

		<m:div styleClass="floatl">
		</m:div>
		<m:div styleClass="searchResults">

			<!--<t:dataScroller id="CMGTTOMDS67" for="SearchCorrespondenceTable"
				paginator="true" onclick="flagWarn=false;"
				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
				firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
				rowsCountVar="rowsCount"
				style="float:right;position:relative;bottom:-6px;text-decoration:underline;">
				<f:facet name="previous">
					<h:outputText id="PRGCMGTOT1803" style="text-decoration:underline;"
						value="#{ref['label.ref.lt']}" rendered="#{pageIndex > 1}">
					</h:outputText>
				</f:facet>
				<f:facet name="next">
					<h:outputText id="PRGCMGTOT1804" style="text-decoration:underline;"
						value="#{ref['label.ref.gt']}" rendered="#{pageIndex < pageCount}">
					</h:outputText>
				</f:facet>
				<h:outputText id="PRGCMGTOT1805" rendered="#{rowsCount > 0}"
					value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
					style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
			

		-->
		<%-- Above code is commented and below code added for CR:ESPRD00798895 for pagination --%>
		<h:panelGroup id="CMGTTOMDS9" >
				<h:outputText id="PRGCMGTOT265"
					value="#{cs_searchCorrespondenceDataBean.startRecordNo}-#{cs_searchCorrespondenceDataBean.endRecord} of #{cs_searchCorrespondenceDataBean.count}"
					style="font-weight:bold;float:left;"></h:outputText>
				
				<t:commandLink id="MEMINFCL73011"
					onmousedown="javascript:flagWarn=false;"
					action="#{cs_searchCorrespondenceControllerBean.next}"
					rendered="#{cs_searchCorrespondenceDataBean.showNext}" style="float:right;CURSOR:hand;">
					<h:outputText id="PRGCMGTOT2651" value=">>" styleClass="underline"/>
				</t:commandLink>
				<h:outputText id="MEMINFOT460412" escape="false" value="&nbsp;"
					style="float:right;" />
				
				<t:commandLink id="MEMINFCL7311315"
					onmousedown="javascript:flagWarn=false;"
					action="#{cs_searchCorrespondenceControllerBean.nextOne}"
					rendered="#{cs_searchCorrespondenceDataBean.showNextOne}">
					<h:outputText id="MEMINFOT46051315"
						value="#{cs_searchCorrespondenceDataBean.currentPage+2}" style="float:right;CURSOR:hand;font-weight:normal" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT26525" escape="false" value="&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73112"
					onmousedown="javascript:flagWarn=false;"
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
					onmousedown="javascript:flagWarn=false;"
					action="#{cs_searchCorrespondenceControllerBean.previous}"
					rendered="#{cs_searchCorrespondenceDataBean.showPrevious}">
					<h:outputText id="PRGCMGTOT2656"
						value="#{cs_searchCorrespondenceDataBean.currentPage-1}" style="float:right;CURSOR:hand;font-weight:normal" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT2657" escape="false" value="&nbsp;&nbsp;"
					style="float:right" />
				
				<t:commandLink id="MEMINFCL73416"
					onmousedown="javascript:flagWarn=false;"
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
