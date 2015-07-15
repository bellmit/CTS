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
					<h:dataTable id="searchGlobalCaseDataTable" border="1"							cellpadding="0" cellspacing="0" columnClasses="columnClass"							headerClass="headerClass" rows="3" footerClass="footerClass"							rowClasses="row_alt,rowone" styleClass="dataTable" width="100%"							var="globalSearchCaseList"							first="#{globalCaseSearchDataBean.hashTPLRecoveryCaseList}"							value="#{globalCaseSearchDataBean.tplRecoveryCaseList}"							rendered="#{globalCaseSearchDataBean.tplRecoverynoData}"							onmouseover="return tableMouseOver(this, event);"							onmouseout="return tableMouseOut(this, event);">
							<h:column id="typ">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD75" columns="2">
										<h:outputLabel id="PRGCMGTOLL215" for="GlobalCaseSearchType"											value="#{msg['label.searchGlobalCase.type']}" />
										<h:panelGroup id="PRGCMGTPGP50">
											<t:div style="width:px;align=left">
												<t:commandLink id="GlobalCaseSearchType" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'GlobalCaseSearchType'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811247" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="globalCaseSearchType" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="GlobalCaseSearchType1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'GlobalCaseSearchType1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811248" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="globalCaseSearchType" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>											
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<%-- <h:outputLink id="PRGCMGTOLK23" value="/wps/myportal/PageNotInOurScope">
									<h:outputText id="xx1" value="TPL Recovery" />									
								</h:outputLink>--%>
								<f:verbatim> <a href="/wps/myportal/PageNotInOurScope"  id="PRGCMGTOLK23" ></f:verbatim>
										<h:outputText id="xx1" value="TPL Recovery" />
										<f:verbatim></a></f:verbatim>

							</h:column>
							<h:column id="created">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD76" columns="2">
										<h:outputLabel id="PRGCMGTOLL216" for="CreatedDate"											value="#{msg['label.searchGlobalCase.created']}" />
										<h:panelGroup id="PRGCMGTPGP51">
											<t:div style="width:px;align=left">
												<t:commandLink id="CreatedDate" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'CreatedDate'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811249" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="createdDate" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="CreatedDate1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'CreatedDate1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811250" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
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
									<h:panelGrid id="PRGCMGTPGD77" columns="2">
										<h:outputLabel id="PRGCMGTOLL217" for="EntityName"											value="#{msg['label.searchGlobalCase.entityName']}" />
										<h:panelGroup id="PRGCMGTPGP52">
											<t:div style="width:px;align=left">
												<t:commandLink id="EntityName" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'EntityName'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811251" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="entityName" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="EntityName1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'EntityName1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811252" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
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
									<h:panelGrid id="PRGCMGTPGD78" columns="2">
										<h:outputLabel id="PRGCMGTOLL218" for="EntityType"											value="#{msg['label.searchGlobalCase.entityType']}" />
										<h:panelGroup id="PRGCMGTPGP53">
											<t:div style="width:px;align=left">
												<t:commandLink id="EntityType" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'EntityType'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811253" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="entityType" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="EntityType1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'EntityType1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811254" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="entityType" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx4"									value="Member" />
							</h:column>
							<h:column id="state">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD79" columns="2">
										<h:outputLabel id="PRGCMGTOLL219" for="Status"											value="#{msg['label.searchGlobalCase.status']}" />
										<h:panelGroup id="PRGCMGTPGP54">
											<t:div style="width:px;align=left">
												<t:commandLink id="Status" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'Status'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811255" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="status" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="Status1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'Status1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811256" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
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
									<h:panelGrid id="PRGCMGTPGD80" columns="2">
										<h:outputLabel id="PRGCMGTOLL220" for="AssignedTO"											value="#{msg['label.searchGlobalCase.assignedTo']}" />
										<h:panelGroup id="PRGCMGTPGP55">
											<t:div style="width:px;align=left">
												<t:commandLink id="AssignedTO" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'AssignedTO'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811257" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="assignedTO" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="AssignedTO1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'AssignedTO1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811258" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="assignedTO" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx6"									value="#{globalSearchCaseList.assignedTo}" />
							</h:column>
							<h:column id="caseTy">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD81" columns="2">
										<h:outputLabel id="PRGCMGTOLL221" for="CaseType"											value="#{msg['label.searchGlobalCase.caseType']}" />
										<h:panelGroup id="PRGCMGTPGP56">
											<t:div style="width:px;align=left">
												<t:commandLink id="CaseType" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'CaseType'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811259" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="caseType" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="CaseType1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'CaseType1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811260" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="caseType" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx7" value="#{globalSearchCaseList.caseType}" />
							</h:column>
							<h:column id="lineofBusiness">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD82" columns="2">
										<h:outputLabel id="PRGCMGTOLL222" for="LineofBusiness"											value="#{msg['label.serachGlobalCase.lob']}" />
										<h:panelGroup id="PRGCMGTPGP57">
											<t:div style="width:px;align=left">
												<t:commandLink id="LineofBusiness" style="text-decoration: none;" 												actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'LineofBusiness'}">
									      			<h:graphicImage id="PROVIDERGI20120731164811261" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif"/>
								         		<f:attribute name="columnName" value="lineofBusiness" />
									         		<f:attribute name="sortOrder" value="asc" />
					                			</t:commandLink>
					                		</t:div>
											<t:div style="width:px;align=left">
					                			<t:commandLink id="LineofBusiness1" style="text-decoration: none;" 					                			actionListener="#{globalCaseSearchControllerBean.getAllSortedTPLRecoveryCaseRecords}" 												rendered="#{globalCaseSearchDataBean.tplRecoveryImageRender != 'LineofBusiness1'}"> 
		 									        <h:graphicImage id="PROVIDERGI20120731164811262" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif"/>
										            <f:attribute name="columnName" value="lineofBusiness" />
										            <f:attribute name="sortOrder" value="desc" />
									            </t:commandLink>
						            		</t:div>

										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<h:outputText id="xx8" value="#{globalSearchCaseList.lob}" />
							</h:column>
						</h:dataTable>
						
						<t:dataScroller id="CMGTTOMDS19" pageCountVar="pageCount" pageIndexVar="pageIndex"					paginator="true" paginatorActiveColumnStyle='font-weight:bold;'					paginatorMaxPages="3" immediate="false" for="searchGlobalCaseDataTable"					firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"					rowsCountVar="rowsCount"					rendered="#{globalCaseSearchDataBean.tplRecoverynoData}"					style="float:right;position:relative;bottom:-6px">
					<f:facet name="previous">
						<h:outputText id="PRGCMGTOT552" style="text-decoration:underline;"							value="<<" rendered="#{pageIndex > 1}">
						</h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText id="PRGCMGTOT553" style="text-decoration:underline;" 							value=">>"
							rendered="#{pageIndex < pageCount}">
						</h:outputText>
					</f:facet>
					<h:outputText id="PRGCMGTOT554"						value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"						style="float:left;position:relative;bottom:-6px;font-weight:bold;"						rendered="#{rowsCount>0}">
					</h:outputText>
				</t:dataScroller>
								
				<h:dataTable var="globalSearchCaseList2" 					rendered="#{globalCaseSearchDataBean.tplRecoverynoDataFlag}"					styleClass="dataTable" cellspacing="0" width="100%" border="1"					headerClass="tableHead" rowClasses="rowone,row_alt" id="searchGlobalCaseDataTable2" >
					<h:column id="GlobalCaseSearchType4">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT555"									value="#{msg['label.searchGlobalCase.type']}" />
						</f:facet>
					</h:column>
					<h:column id="CreatedDate4">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT556"									value="#{msg['label.searchGlobalCase.created']}" />
						</f:facet>
					</h:column>
					<h:column id="EntityName4">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT557"									value="#{msg['label.searchGlobalCase.entityName']}" />
						</f:facet>
					</h:column>
					<h:column id="EntityTyp4">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT558"									value="#{msg['label.searchGlobalCase.entityType']}" />
						</f:facet>
					</h:column>
					<h:column id="Status4">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT559"									value="#{msg['label.searchGlobalCase.status']}" />
						</f:facet>
					</h:column>
					<h:column id="AssignedTO4">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT560"									value="#{msg['label.searchGlobalCase.assignedTo']}" />
						</f:facet>
					</h:column>
					<h:column id="CaseType4">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT561"									value="#{msg['label.searchGlobalCase.caseType']}" />
						</f:facet>
					</h:column>
					<h:column id="LineofBusines4">
						<f:facet name="header">
								<h:outputText id="PRGCMGTOT562"									value="#{msg['label.serachGlobalCase.lob']}" />
						</f:facet>
					</h:column>
					<f:facet name="footer">
						<f:verbatim>
							<m:div id="nodatabankinfo" align="center">
								<h:outputText id="PRGCMGTOT563" value="#{msg['label.searchGlobalCase.noData']}"></h:outputText>
							</m:div>
						</f:verbatim>
					</f:facet>
				</h:dataTable>
