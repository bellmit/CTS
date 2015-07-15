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
<m:inputHidden name="com.ibm.portal.propertybroker.action"
	value="sendCaseDetailsAction"></m:inputHidden>
<m:div rendered="#{caseSearchDataBean.showReassignResultsPage}">
	<m:div styleClass="padb" />
	<m:div styleClass="msgBox"
		rendered="#{caseSearchDataBean.showSucessMessage}">
		<h:outputText id="PRGCMGTOT1753" value="#{ent['label-sw-success']}" />
	</m:div>
	<m:br />
	<m:br />	
	<m:div align="right">
		<h:outputLabel id="PRGCMGTOLL702" for="department"			value="#{msg['lable.reassignCase.reassignAll']}"></h:outputLabel>

		<h:selectOneMenu id="department" 			value="#{caseSearchDataBean.selectedDeptAll}"				disabled="#{caseSearchDataBean.disableReassignAllCaseDropdown}"										onchange="javascript:flagWarn=false;onChangReassign()">

			<f:selectItem itemLabel="" itemValue="" />
			<f:selectItems value="#{caseSearchDataBean.allDeptUsersForReassign}" />
		</h:selectOneMenu>

		<m:br />
		<m:br />
	</m:div>

	<%-- Modified for the HeapDump Fix --%>
	<h:dataTable id="searchCaseDataTable22" border="1" cellpadding="0"		cellspacing="0" columnClasses="columnClass" headerClass="headerClass"		rows="10" footerClass="footerClass" rowClasses="row_alt,rowone"		styleClass="dataTable" width="100%" var="searchCaseList"		value="#{caseSearchDataBean.searchCaseList}"		first="#{caseSearchDataBean.reassignCaseResultTable}" 		onmouseover="return tableMouseOver(this, event);"		onmouseout="return tableMouseOut(this, event);"
	rendered="#{caseSearchDataBean.noDataFlag}">
		<h:column id="CaseSearchcreated">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD222" columns="2">
					<h:outputLabel id="PRGCMGTOLL703" for="CaseSearchcreatedDate2"						value="#{msg['label.searchCase.created']}" />
					<h:panelGroup id="PRGCMGTPGP136">
						<t:div>
							<t:commandLink id="CaseSearchcreatedDate2"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchcreatedDate2'}" onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811651" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
									url="/images/x.gif" styleClass="sort_asc" width="8" />
								<f:attribute name="columnName" value="caseSearchcreatedDate" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
						</t:div>
						<t:div>
							<t:commandLink id="CaseSearchcreatedDate3"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchcreatedDate3'}" onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811652" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
									url="/images/x.gif" styleClass="sort_desc" width="8" />
								<f:attribute name="columnName" value="caseSearchcreatedDate" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="xx1" value="#{searchCaseList.createdDate}" />
		</h:column>
		<h:column id="entitynam">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD223" columns="2">
					<h:outputLabel id="PRGCMGTOLL704" for="CaseSearchentityName2"						value="#{msg['label.searchCase.entityName']}" />
					<h:panelGroup id="PRGCMGTPGP137">
						<t:div>
							<t:commandLink id="CaseSearchentityName2"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchentityName2'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811653" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
									url="/images/x.gif" styleClass="sort_asc" width="8" />
								<f:attribute name="columnName" value="caseSearchentityName" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
						</t:div>
						<t:div>
							<t:commandLink id="CaseSearchentityName3"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchentityName3'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811654" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
									url="/images/x.gif" styleClass="sort_desc" width="8" />
								<f:attribute name="columnName" value="caseSearchentityName" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="xx2" value="#{searchCaseList.entityName}" />
		</h:column>
		<h:column id="entityTyp">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD224" columns="2">
					<h:outputLabel id="PRGCMGTOLL705" for="CaseSearchentityType2"						value="#{msg['label.searchCase.entityType']}" />
					<h:panelGroup id="PRGCMGTPGP138">
						<t:div>
							<t:commandLink id="CaseSearchentityType2"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchentityType2'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811655" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
									url="/images/x.gif" styleClass="sort_asc" width="8" />
								<f:attribute name="columnName" value="caseSearchentityType" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
						</t:div>
						<t:div>
							<t:commandLink id="CaseSearchentityType3"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchentityType3'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811656" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
									url="/images/x.gif" styleClass="sort_desc" width="8" />
								<f:attribute name="columnName" value="caseSearchentityType" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</t:div>

					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="xx3" value="#{searchCaseList.entityTypeDesc}" />
		</h:column>
		<h:column id="CaseSearchstate">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD225" columns="2">
					<h:outputLabel id="PRGCMGTOLL706" for="CaseSearchstatus2"						value="#{msg['label.searchCase.status']}" />
					<h:panelGroup id="PRGCMGTPGP139">
						<t:div>
							<t:commandLink id="CaseSearchstatus2"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchstatus2'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811657" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								   url="/images/x.gif" styleClass="sort_asc" width="8" />
								<f:attribute name="columnName" value="caseSearchstatus" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
						</t:div>
						<t:div>
							<t:commandLink id="CaseSearchstatus3"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchstatus3'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811658" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
									url="/images/x.gif" styleClass="sort_desc" width="8" />
								<f:attribute name="columnName" value="caseSearchstatus" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="xx4" value="#{searchCaseList.status}" />
		</h:column>
		<h:column id="CaseSearchassigned">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD226" columns="2">
					<h:outputLabel id="PRGCMGTOLL707" for="CaseSearchassignedTo2"						value="#{msg['label.searchCase.assignedTo']}" />
					<h:panelGroup id="PRGCMGTPGP140">
						<t:div>
							<t:commandLink id="CaseSearchassignedTo2"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchassignedTo2'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811659" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
									url="/images/x.gif" styleClass="sort_asc" width="8" /> 
								<f:attribute name="columnName" value="caseSearchassignedTo" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
						</t:div>
						<t:div>
							<t:commandLink id="CaseSearchassignedTo3"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchassignedTo3'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811660" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
									url="/images/x.gif" styleClass="sort_desc" width="8" />
								<f:attribute name="columnName" value="caseSearchassignedTo" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="xx5" value="#{searchCaseList.assignedTo}" />
		</h:column>
		<h:column id="caseTy">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD227" columns="2">
					<h:outputLabel id="PRGCMGTOLL708" for="CaseSearchcaseType2"						value="#{msg['label.searchCase.caseType']}" />
					<h:panelGroup id="PRGCMGTPGP141">
						<t:div>
							<t:commandLink id="CaseSearchcaseType2"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchcaseType2'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811661" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
									url="/images/x.gif" styleClass="sort_asc" width="8" />
								<f:attribute name="columnName" value="caseSearchcaseType" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
						</t:div>
						<t:div>
							<t:commandLink id="CaseSearchcaseType3"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchcaseType3'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811662" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
									url="/images/x.gif" styleClass="sort_desc" width="8" />
								<f:attribute name="columnName" value="caseSearchcaseType" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="xx6" value="#{searchCaseList.caseType}" />
		</h:column>
		<h:column id="prior">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD228" columns="2">
					<h:outputLabel id="PRGCMGTOLL709" for="CaseSearchpriority2"						value="#{msg['label.searchCase.priority']}" />
					<h:panelGroup id="PRGCMGTPGP142">
						<t:div>
							<t:commandLink id="CaseSearchpriority2"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchpriority2'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811663" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
									url="/images/x.gif" styleClass="sort_asc" width="8" />
								<f:attribute name="columnName" value="caseSearchpriority" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
						</t:div>
						<t:div>
							<t:commandLink id="CaseSearchpriority3"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender != 'CaseSearchpriority3'}"  onmousedown="javascript:flagWarn=false;" >
								<h:graphicImage id="PROVIDERGI20120731164811664" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
									url="/images/x.gif" styleClass="sort_desc" width="8" />
								<f:attribute name="columnName" value="caseSearchpriority" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="xx7" value="#{searchCaseList.priority}" />
		</h:column>

		<h:column id="PRGCMGTC135">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD229" columns="2">
					<h:outputLabel id="panel9" for="CaseSearchReassign2"						value="#{msg['lable.reassignCase.reassignTo']}" />
					<h:panelGroup id="PRGCMGTPGP143">
						<t:div styleClass="alignLeft">
							<t:commandLink id="CaseSearchReassign2"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender !='CaseSearchReassign2'}"  onmousedown="javascript:flagWarn=false;" >
								<m:div title="#{ent['label.ent.forAscending']}" styleClass="sort_asc" />
								<f:attribute name="columnName" value="lobCode" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
						</t:div>
						<t:div styleClass="alignLeft">
							<t:commandLink id="CaseSearchReassign3"								actionListener="#{caseSearchControllerBean.getAllSortedCaseRecords}"								rendered="#{caseSearchDataBean.imageRender !='CaseSearchReassign3'}"  onmousedown="javascript:flagWarn=false;" >
								<m:div title="#{ent['label.ent.forDescending']}" styleClass="sort_desc" />
								<f:attribute name="columnName" value="caseSearchReassign" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>				
			
				<%-- modified for CR 798895 for Bulk reassign.
				Drop down will be rendered in individual re assignment mode.
			 --%>
			 <h:outputLabel id="checkboxRCColumnLabel1"  for="departmentId" styleClass="hide" value="#{msg['lable.reassignCase.reassignTo']}" ></h:outputLabel>
			<h:selectOneMenu id="departmentId"
				rendered="#{empty caseSearchDataBean.selectedDeptAll}"
				disabled="#{caseSearchDataBean.disableReassignToDeptDropdown}"
				value="#{searchCaseList.selectedDept}"
				onmousedown="javascript:flagWarn=false;">
				<f:selectItem itemValue=" " itemLabel="" />
				<f:selectItems value="#{searchCaseList.deptList}" />
				<%--<f:selectItems value="#{caseSearchControllerBean.allDeptUsersForReassign}" />--%>
			</h:selectOneMenu>
			<%-- Reassign method modified for CR 798895 for Bulk reassign. 
				Output text will be rendered if user selected the reassign all cases to value.
			--%>
			<h:outputText id="routedvalueId" value="#{searchCaseList.selectedDeptDesc}"
				rendered="#{not empty caseSearchDataBean.selectedDeptAll}"></h:outputText>
			
		</h:column>
	</h:dataTable>

	<%-- Added and modified the code for the DataScroller Fix --%>
	<!-- Start - Modified the t:datascroller with the customized user defined datascroller -->
	 <h:panelGroup id="MEMINFPGP6091" rendered="#{caseSearchDataBean.noDataFlag}">
		<h:outputText id="MEMINFOT46021"
			value="#{caseSearchDataBean.startRecordNo}-#{caseSearchDataBean.endRecord} of #{caseSearchDataBean.count}"
			style="float:left"></h:outputText>
		<t:commandLink id="MEMINFCL7301"
			onclick="javascript:flagWarn=false;return warnBeforeGoToNext('N');"
			action="#{caseSearchControllerBean.next}"
			rendered="#{caseSearchDataBean.showNext}" style="float:right;CURSOR:hand;font-weight:normal">
			<h:outputText id="MEMINFOT46031" value=">>" styleClass="underline"/>
		</t:commandLink>
		<h:outputText id="MEMINFOT46060601" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL73113"
			onclick="javascript:flagWarn=false;return warnBeforeGoToNext('NO');"
			action="#{caseSearchControllerBean.nextOne}"
			rendered="#{caseSearchDataBean.showNextOne}">
			<h:outputText id="MEMINFOT460513"
				value="#{caseSearchDataBean.currentPage+2}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="MEMINFOT460612" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL7311"
			onclick="javascript:flagWarn=false;return warnBeforeGoToNext('N');"
			action="#{caseSearchControllerBean.next}"
			rendered="#{caseSearchDataBean.showNext}">
			<h:outputText id="MEMINFOT46051"
				value="#{caseSearchDataBean.currentPage+1}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="MEMINFOT46061" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL7321"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="CurrentPage"
				value="#{caseSearchDataBean.currentPage}" 
				style="float:right;CURSOR:hand;font-weight:bold"></h:outputText>
		</t:commandLink>
		<h:outputText id="MEMINFOT46071" escape="false" value="&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL7331"
			onclick="javascript:flagWarn=false;return warnBeforeGoToNext('P');"
			action="#{caseSearchControllerBean.previous}"
			rendered="#{caseSearchDataBean.showPrevious}">
			<h:outputText id="MEMINFOT46081"
				value="#{caseSearchDataBean.currentPage-1}" style="float:right;CURSOR:hand;font-weight:normal" />
		</t:commandLink>
		<h:outputText id="MEMINFOT46091" escape="false" value="&nbsp;&nbsp;"
			style="float:right" />
		<t:commandLink id="MEMINFCL7341"
			onclick="javascript:flagWarn=false;return warnBeforeGoToNext('P');"
			action="#{caseSearchControllerBean.previous}"
			rendered="#{caseSearchDataBean.showPrevious}" style="float:right;font-weight:normal">
			<h:outputText id="MEMINFOT46101" value="<<" styleClass="underline" />
		</t:commandLink>
		<m:br></m:br>
		<m:br></m:br>
	</h:panelGroup>
<!-- End - Modified the t:datascroller with the customized user defined datascroller -->

	<h:dataTable var="searchCaseList"		rendered="#{!caseSearchDataBean.noDataFlag}" styleClass="dataTable"		cellspacing="0" width="100%" border="1" headerClass="tableHead"		rowClasses="rowone,row_alt" id="searchCaseDataTable1">
		<h:column id="CaseSearchcreated1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1757" value="#{msg['label.searchCase.created']}" />
			</f:facet>
		</h:column>
		<h:column id="entitynam1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1758" value="#{msg['label.searchCase.entityName']}" />
			</f:facet>
		</h:column>
		<h:column id="entityTyp1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1759" value="#{msg['label.searchCase.entityType']}" />
			</f:facet>
		</h:column>
		<h:column id="CaseSearchstate1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1760" value="#{msg['label.searchCase.status']}" />
			</f:facet>
		</h:column>
		<h:column id="CaseSearchassigned1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1761" value="#{msg['label.searchCase.assignedTo']}" />
			</f:facet>
		</h:column>
		<h:column id="caseTy1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1762" value="#{msg['label.searchCase.caseType']}" />
			</f:facet>
		</h:column>
		<h:column id="prior1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1763" value="#{msg['label.searchCase.priority']}" />
			</f:facet>
		</h:column>
		<h:column id="lineofBusiness1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT1764" value="#{msg['label.serachCase.lob']}" />
			</f:facet>
		</h:column>

	</h:dataTable>
	<m:table id="PROVIDERMMT20120731164811665" styleClass="dataTable" width="100%" border="0"
		rendered="#{!caseSearchDataBean.noDataFlag}">
		<m:tr>
			<m:td align="center">
				<h:outputText id="PRGCMGTOT1765" value="#{msg['label.searchCase.noData']}" />
			</m:td>
		</m:tr>
	</m:table>
</m:div>
