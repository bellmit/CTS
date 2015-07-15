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
					<%-- Modified for the HeapDump Fix --%>
					<h:dataTable id="searchGlobalCaseDataTable" border="1"							cellpadding="0" cellspacing="0" columnClasses="columnClass"							headerClass="headerClass" rows="3" footerClass="footerClass"							rowClasses="row_alt,rowone" styleClass="dataTable" width="100%"							var="globalSearchCaseList"							first="#{globalCaseSearchDataBean.hashTPLHippCaseList}"							value="#{globalCaseSearchDataBean.tplHIPPCaseList}"							rendered="#{globalCaseSearchDataBean.tplHippnoData}"							onmouseover="return tableMouseOver(this, event);"							onmouseout="return tableMouseOut(this, event);">
							<h:column id="typ">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD69" columns="2">
										<h:outputLabel id="PRGCMGTOLL209" for="GlobalCaseSearchType"											value="#{msg['label.searchGlobalCase.type']}" />
										<h:panelGroup id="PRGCMGTPGP44">
											<t:div style="width:px;align=left">
												<t:commandLink id="GlobalCaseSearchType" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'GlobalCaseSearchType'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811235" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="globalCaseSearchType" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="GlobalCaseSearchType1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'GlobalCaseSearchType1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811236" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="globalCaseSearchType" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>											
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
							<%-- 	<h:outputLink id="PRGCMGTOLK22" value="/wps/myportal/PageNotInOurScope">
									<h:outputText id="xx1" value="TPL HIPP" />									
								</h:outputLink>--%>
								<f:verbatim> <a href="/wps/myportal/PageNotInOurScope"  id="PRGCMGTOLK22" ></f:verbatim>
						<h:outputText id="xx1" value="TPL HIPP" />
						<f:verbatim></a></f:verbatim>

							</h:column>
							<h:column id="created">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD70" columns="2">
										<h:outputLabel id="PRGCMGTOLL210" for="CreatedDate"											value="#{msg['label.searchGlobalCase.created']}" />
										<h:panelGroup id="PRGCMGTPGP45">
											<t:div style="width:px;align=left">
												<t:commandLink id="CreatedDate" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'CreatedDate'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811237" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="createdDate" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="CreatedDate1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'CreatedDate1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811238" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="createdDate" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx2"		value="#{globalSearchCaseList.createdDate}" />
							</h:column>
							<h:column id="entitynam">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD71" columns="2">
										<h:outputLabel id="PRGCMGTOLL211" for="EntityName"											value="#{msg['label.searchGlobalCase.entityName']}" />
										<h:panelGroup id="PRGCMGTPGP46">
											<t:div style="width:px;align=left">
												<t:commandLink id="EntityName" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'EntityName'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811239" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="entityName" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="EntityName1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'EntityName1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811240" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="entityName" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx3"									value="#{globalSearchCaseList.entityName}" />
							</h:column>
							<h:column id="entityTyp">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD72" columns="2">
										<h:outputLabel id="PRGCMGTOLL212" for="EntityType"											value="#{msg['label.searchGlobalCase.entityType']}" />
										<h:panelGroup id="PRGCMGTPGP47">
											<t:div style="width:px;align=left">
												<t:commandLink id="EntityType" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'EntityType'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811241" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="entityType" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="EntityType1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'EntityType1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811242" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="entityType" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx4" value="Member" />
							</h:column>
							<h:column id="state">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD73" columns="2">
										<h:outputLabel id="PRGCMGTOLL213" for="Status"											value="#{msg['label.searchGlobalCase.status']}" />
										<h:panelGroup id="PRGCMGTPGP48">
											<t:div style="width:px;align=left">
												<t:commandLink id="Status" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'Status'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811243" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="status" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="Status1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'Status1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811244" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="status" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx5" value="#{globalSearchCaseList.status}" />
							</h:column>
							<h:column id="assigned">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD74" columns="2">
										<h:outputLabel id="PRGCMGTOLL214" for="AssignedTO"											value="#{msg['label.searchGlobalCase.assignedTo']}" />
										<h:panelGroup id="PRGCMGTPGP49">
											<t:div style="width:px;align=left">
												<t:commandLink id="AssignedTO" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'AssignedTO'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811245" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="assignedTO" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="AssignedTO1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLHippCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplHippImageRender != 'AssignedTO1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811246" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="assignedTO" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx6"									value="#{globalSearchCaseList.assignedTo}" />
							</h:column>							
						</h:dataTable>
						
						<t:dataScroller id="CMGTTOMDS18" pageCountVar="pageCount" pageIndexVar="pageIndex"							paginator="true" paginatorActiveColumnStyle='font-weight:bold;'							paginatorMaxPages="3" immediate="false" for="searchGlobalCaseDataTable"							firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"							rowsCountVar="rowsCount"														rendered="#{globalCaseSearchDataBean.tplHippnoData}"							style="float:right;position:relative;bottom:-6px">
							<f:facet name="previous">
								<h:outputText id="PRGCMGTOT540" style="text-decoration:underline;"									value="<<" rendered="#{pageIndex > 1}">
								</h:outputText>
							</f:facet>
							<f:facet name="next">
								<h:outputText id="PRGCMGTOT541" style="text-decoration:underline;" 									value=">>"
									rendered="#{pageIndex < pageCount}">
								</h:outputText>
							</f:facet>
							<h:outputText id="PRGCMGTOT542"								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"								style="float:left;position:relative;bottom:-6px;font-weight:bold;"								rendered="#{rowsCount>0}">
							</h:outputText>
						</t:dataScroller>
									
					<h:dataTable var="globalSearchCaseList3" 						rendered="#{globalCaseSearchDataBean.tplHippnoDataFlag}"						styleClass="dataTable" cellspacing="0" width="100%" border="1"						headerClass="tableHead" rowClasses="rowone,row_alt" id="searchGlobalCaseDataTable3" >
						<t:column id="GlobalCaseSearchType5">
							<f:facet name="header">
									<h:outputText id="PRGCMGTOT543" 										value="#{msg['label.searchGlobalCase.type']}" />
							</f:facet>
						</t:column>
						<t:column id="CreatedDate5">
							<f:facet name="header">
									<h:outputText id="PRGCMGTOT544" 										value="#{msg['label.searchGlobalCase.created']}" />
							</f:facet>
						</t:column>
						<t:column id="EntityName5">
							<f:facet name="header">
									<h:outputText id="PRGCMGTOT545"										value="#{msg['label.searchGlobalCase.entityName']}" />
							</f:facet>
						</t:column>
						<t:column id="EntityTyp5">
							<f:facet name="header">
									<h:outputText id="PRGCMGTOT546" 										value="#{msg['label.searchGlobalCase.entityType']}" />
							</f:facet>
						</t:column>
						<t:column id="Status5">
							<f:facet name="header">
									<h:outputText id="PRGCMGTOT547" 										value="#{msg['label.searchGlobalCase.status']}" />
							</f:facet>
						</t:column>
						<t:column id="AssignedTO5">
							<f:facet name="header">
									<h:outputText id="PRGCMGTOT548" 										value="#{msg['label.searchGlobalCase.assignedTo']}" />
							</f:facet>
						</t:column>
						<t:column id="CaseType5">
							<f:facet name="header">
									<h:outputText id="PRGCMGTOT549" 										value="#{msg['label.searchGlobalCase.caseType']}" />
							</f:facet>
						</t:column>
						<t:column id="LineofBusines5">
							<f:facet name="header">
									<h:outputText id="PRGCMGTOT550" 										value="#{msg['label.serachGlobalCase.lob']}" />
							</f:facet>
						</t:column>
						<f:facet name="footer">
							<f:verbatim>
								<m:div id="nodatabankinfo" align="center">
									<h:outputText id="PRGCMGTOT551" value="#{msg['label.searchGlobalCase.noData']}"></h:outputText>
								</m:div>
							</f:verbatim>
						</f:facet>
					</h:dataTable>
