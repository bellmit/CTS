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

<m:inputHidden name="send.Global.Case.Search.Results"
			value="sendGlobalCaseSearchResults"></m:inputHidden>
			<%-- Modified for the HeapDump Fix --%>
					<t:dataTable id="searchGlobalCaseDataTable" border="1"							cellpadding="0" cellspacing="0" columnClasses="columnClass"							headerClass="headerClass" rows="10" footerClass="footerClass"							rowClasses="row_alt,rowone" styleClass="dataTable" width="100%"							var="globalSearchCaseList"							first="#{globalCaseSearchDataBean.hashContactManagmentCaseList}"							value="#{globalCaseSearchDataBean.contactManagemtCaseList}"							rendered="#{globalCaseSearchDataBean.contctManagementnoData}"							rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"		                    rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"		                    rowOnClick="firstChild.lastChild.click();">
							<t:column id="typ">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD61" columns="2">
										<h:outputLabel id="PRGCMGTOLL188" for="GlobalCaseSearchType"											value="#{msg['label.searchGlobalCase.type']}" />
										<h:panelGroup id="PRGCMGTPGP36">
											<t:div style="width:px;align=left">
												<t:commandLink id="GlobalCaseSearchType" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'GlobalCaseSearchType'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811216" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="globalCaseSearchType" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="GlobalCaseSearchType1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'GlobalCaseSearchType1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811217" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="globalCaseSearchType" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>											
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<t:commandLink id="PRGCMGTCL99" action="#{globalCaseSearchControllerBean.submitCaseDetails}">
									<h:outputText id="xx1" value="#{globalSearchCaseList.moduleType}" />
									<f:param id="ipcAction1" name="moduleType" value="#{globalSearchCaseList.moduleType}"/>
									<f:param id="ipcAction12" name="#{globalSearchCaseList.paramNameForIPC}" value="#{globalSearchCaseList.paramValueForIPC}"/>
									<f:param id="caseSK" name="caseSK" value="#{globalSearchCaseList.caseSK}"/>
									<f:param id="entityType" name="entityType" value="#{globalSearchCaseList.entityType}"/>
									<%--f:param id="entityID" name="entityID" value="#{globalSearchCaseList.commonEntitySK}"/--%>
									<f:param id="entityID" name="entityID" value="#{globalSearchCaseList.entityID}"/>
								</t:commandLink>

							</t:column>
							<t:column id="created">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD62" columns="2">
										<h:outputLabel id="PRGCMGTOLL189" for="CreatedDate"											value="#{msg['label.searchGlobalCase.created']}" />
										<h:panelGroup id="PRGCMGTPGP37">
											<t:div style="width:px;align=left">
												<t:commandLink id="CreatedDate" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'CreatedDate'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811218" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="createdDate" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="CreatedDate1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'CreatedDate1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811219" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="createdDate" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx2" value="#{globalSearchCaseList.createdDate}" />
							</t:column>
							<t:column id="entitynam">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD63" columns="2">
										<h:outputLabel id="PRGCMGTOLL190" for="EntityName"											value="#{msg['label.searchGlobalCase.entityName']}" />
										<h:panelGroup id="PRGCMGTPGP38">
											<t:div style="width:px;align=left">
												<t:commandLink id="EntityName" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'EntityName'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811220" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="entityName" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="EntityName1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'EntityName1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811221" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="entityName" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx3"									value="#{globalSearchCaseList.entityName}" />
							</t:column>
							<t:column id="entityTyp">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD64" columns="2">
										<h:outputLabel id="PRGCMGTOLL191" for="EntityType"											value="#{msg['label.searchGlobalCase.entityType']}" />
										<h:panelGroup id="PRGCMGTPGP39">
											<t:div style="width:px;align=left">
												<t:commandLink id="EntityType" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'EntityType'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811222" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="entityType" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="EntityType1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'EntityType1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811223" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="entityType" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx4"									value="#{globalSearchCaseList.entityTypeDesc}" />
							</t:column>
							<t:column id="state">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD65" columns="2">
										<h:outputLabel id="PRGCMGTOLL192" for="Status"											value="#{msg['label.searchGlobalCase.status']}" />
										<h:panelGroup id="PRGCMGTPGP40">
											<t:div style="width:px;align=left">
												<t:commandLink id="Status" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'Status'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811224" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="status" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="Status1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'Status1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811225" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="status" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx5" value="#{globalSearchCaseList.status}" />
							</t:column>
							<t:column id="assigned">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD66" columns="2">
										<h:outputLabel id="PRGCMGTOLL193" for="AssignedTO"											value="#{msg['label.searchGlobalCase.assignedTo']}" />
										<h:panelGroup id="PRGCMGTPGP41">
											<t:div style="width:px;align=left">
												<t:commandLink id="AssignedTO" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'AssignedTO'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811226" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="assignedTO" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="AssignedTO1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'AssignedTO1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811227" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="assignedTO" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx6"									value="#{globalSearchCaseList.assignedTo}" />
							</t:column>
							<t:column id="caseTy">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD67" columns="2">
										<h:outputLabel id="PRGCMGTOLL194" for="CaseType"											value="#{msg['label.searchGlobalCase.caseType']}" />
										<h:panelGroup id="PRGCMGTPGP42">
											<t:div style="width:px;align=left">
												<t:commandLink id="CaseType" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'CaseType'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811228" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="caseType" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="CaseType1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'CaseType1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811229" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="caseType" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx7" value="#{globalSearchCaseList.caseType}" />
							</t:column>
							<t:column id="lineofBusiness">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD68" columns="2">
										<h:outputLabel id="PRGCMGTOLL195" for="LineofBusiness"											value="#{msg['label.serachGlobalCase.lob']}" />
										<h:panelGroup id="PRGCMGTPGP43">
											<t:div style="width:px;align=left">
												<t:commandLink id="LineofBusiness" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'LineofBusiness'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811230" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="lineofBusiness" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="LineofBusiness1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedGlobalCaseRecords}" 												rendered="#{globalCaseSearchDataBean.contctManagementImageRender != 'LineofBusiness1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811231" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="lineofBusiness" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>

										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx8" value="#{globalSearchCaseList.lob}" />
							</t:column>
						</t:dataTable>
				<t:dataScroller id="CMGTTOMDS17" pageCountVar="pageCount" pageIndexVar="pageIndex"					paginator="true" paginatorActiveColumnStyle='font-weight:bold;'					paginatorMaxPages="3" immediate="false" for="searchGlobalCaseDataTable"					firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"					rowsCountVar="rowsCount"					rendered="#{globalCaseSearchDataBean.contctManagementnoData}"					style="float:right;position:relative;bottom:-6px">
					<f:facet name="previous">
						<h:outputText id="PRGCMGTOT512" style="text-decoration:underline;"							value="<<" rendered="#{pageIndex > 1}">
						</h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText id="PRGCMGTOT513" style="text-decoration:underline;" 							value=">>"
							rendered="#{pageIndex < pageCount}">
						</h:outputText>
					</f:facet>
					<h:outputText id="PRGCMGTOT514"						value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"						style="float:left;position:relative;bottom:-6px;font-weight:bold;"						rendered="#{rowsCount>0}">
					</h:outputText>
				</t:dataScroller>
								
				<h:dataTable var="globalSearchCaseList1" 					rendered="#{globalCaseSearchDataBean.contctManagementnoDataFlag}"					styleClass="dataTable" cellspacing="0" width="100%" border="1"					headerClass="tableHead" rowClasses="rowone,row_alt" id="searchGlobalCaseDataTable1" >
					<t:column id="GlobalCaseSearchType3">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT515"									value="#{msg['label.searchGlobalCase.type']}" />
						</f:facet>
					</t:column>
					<t:column id="CreatedDate3">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT516"									value="#{msg['label.searchGlobalCase.created']}" />
						</f:facet>
					</t:column>
					<t:column id="EntityName3">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT517"									value="#{msg['label.searchGlobalCase.entityName']}" />
						</f:facet>
					</t:column>
					<t:column id="EntityTyp3">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT518"									value="#{msg['label.searchGlobalCase.entityType']}" />
						</f:facet>
					</t:column>
					<t:column id="Status3">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT519"									value="#{msg['label.searchGlobalCase.status']}" />
						</f:facet>
					</t:column>
					<t:column id="AssignedTO3">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT520"									value="#{msg['label.searchGlobalCase.assignedTo']}" />
						</f:facet>
					</t:column>
					<t:column id="CaseType3">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT521"									value="#{msg['label.searchGlobalCase.caseType']}" />
						</f:facet>
					</t:column>
					<t:column id="LineofBusines3">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT522"									value="#{msg['label.serachGlobalCase.lob']}" />
						</f:facet>
					</t:column>
					<f:facet name="footer">
						<f:verbatim>
							<m:div id="nodatabankinfo" align="center">
								<h:outputText id="PRGCMGTOT523" value="#{msg['label.searchGlobalCase.noData']}"></h:outputText>
							</m:div>
						</f:verbatim>
					</f:facet>
				</h:dataTable>
