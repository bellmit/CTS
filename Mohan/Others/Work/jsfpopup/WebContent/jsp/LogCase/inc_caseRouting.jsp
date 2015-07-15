<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:div id="routingDetailsDiv1">
	<m:div id="routingDetailsDiv2">
		<m:section styleClass="casebg" id="sec1">
			<m:legend id="routingDetailsLeg1">
				 <h:outputLink onclick="javascript:flagWarn=false;setHiddenValue('CMlogCase:routingDetails:routingHiddenID','minus');	 						toggle('showhide_routing',2);						 plusMinusToggle('showhide_routing',this,'CMlogCase:routingDetails:routingHiddenID');						 return false;"               						 id="plusMinus_routingFalse" styleClass="plus" >
						<h:outputText id="routing_text"  value="#{msg['title.case.routing']}" />
					</h:outputLink>
					<h:inputHidden id="routingHiddenID" value="#{logCaseDataBean.routingHidden}" />
			</m:legend>
			<m:div sid="showhide_routing">
			<m:div styleClass="msgBox"
				rendered="#{logCaseDataBean.showCaseRoutingMessages}">
				<h:outputText value="#{ent['label-sw-success']}"
					id="addRoutingSuccessMessage" />
			</m:div>
				<m:table width="100%" id="routingDetailsTab1">
					<m:tr id="routingDetailsTab1Row1">
						<m:td styleClass="rightCell" id="routingDetailsTab1Row1Col1">
                          

                             <%-- Infinite Computer Solutions FOR CR -1825--%>
                            <h:commandButton
                            style="color:#fff; background-color:#518ACD; border-color:#ccc #000 #000 #ccc; font-size:11px; font-weight:bold;"
                            rendered="#{!logCaseDataBean.disableAddRouting && !logCaseDataBean.disableScreenField}" 
                            value="#{msg['label.case.routing.addRoutingAssignment']}"										
                            action="#{logCaseControllerBean.renderAddRouting}"																			
                            id="addRoutingAssignmentButton"										
                            onclick="javascript:focusThis('show');javascript:flagWarn=false;focusPage('logCaseAddRouting');" /> 
                            <h:commandButton
                            style="color:GrayText; background-color:#518ACD; border-color:#ccc #000 #000 #ccc; font-size:11px; font-weight:bold;"
                            rendered="#{logCaseDataBean.disableAddRouting || logCaseDataBean.disableScreenField}" 
                            value="#{msg['label.case.routing.addRoutingAssignment']}"										
                            action="#{logCaseControllerBean.renderAddRouting}"																			
                            id="addRoutingAssignmentButton11"										
                            onclick="javascript:focusThis('show');javascript:flagWarn=false;focusPage('logCaseAddRouting');"										
                            disabled="#{logCaseDataBean.disableAddRouting || logCaseDataBean.disableScreenField}"/>
						</m:td>
					</m:tr>
				</m:table>
				<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1545--%>
				<t:dataTable value="#{logCaseDataBean.routingInfoList}" 
					rendered="#{logCaseDataBean.showRoutingDataTable}"
					first="#{logCaseDataBean.routingRowIndex}"
					var="routingResult" styleClass="dataTable" cellspacing="0"
					width="100%" border="0" headerClass="tableHead"
					rowClasses="rowone,row_alt" rows="10" id="routingTable"
					rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
					rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
					rowOnClick="javascript:flagWarn=false;firstChild.lastChild.click();"
					onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);focusPage('logCaseEditRouting');"  rowIndexVar="rowIndex">
					<t:column id="routingcolumn1">
						<f:facet name="header">
							<h:panelGrid columns="2" id="routingTableGridId1">
								<h:outputLabel id="routingTableOutLbl1" value="#{msg['label.case.routing.date']}" for="routingDateCommandLink1"/>
								<h:panelGroup id="routingTableGrpId1">
								<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
								<t:div id="CaseRoutingTDiv001" styleClass="alignLeft">
									<t:commandLink id="routingDateCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortRoutingInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"										rendered="#{logCaseDataBean.imageRender != 'routingDateCommandLink1'}">
										<m:div id="CaseRoutingMDiv0001" title="#{msg['alt.for.ascending']}" styleClass="sort_asc"/>
										<f:attribute name="columnName" value="Date" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div id="CaseRoutingTDiv002" styleClass="alignLeft">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:commandLink id="routingDateCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortRoutingInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"										rendered="#{logCaseDataBean.imageRender != 'routingDateCommandLink2'}">
										<m:div id="CaseRoutingMDiv0002" title="#{msg['alt.for.descending']}" styleClass="sort_desc"/>
										<f:attribute name="columnName" value="Date" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<t:commandLink action="#{logCaseControllerBean.viewRoutingInfo}" onmousedown="javascript:flagWarn=false;focusPage('logCaseEditRouting');" rendered="#{!logCaseDataBean.disableScreenField}"						id="routingDateCommandLink3">
						
						<f:param name="rowindex" value="#{rowIndex}" />
						
							<h:outputText value="#{routingResult.routingDateStr}" id="routingTableOutTxtId1"/>
						</t:commandLink>
							<h:outputText id="routingTableOutTxtId2" value="#{routingResult.routingDateStr}" rendered="#{logCaseDataBean.disableScreenField}"/>
					</t:column>

					<t:column id="routingcolumn2">
						<f:facet name="header">
							<h:panelGrid columns="2" id="routingTableGridId2">
								<h:outputLabel id="routingTableOutLbl2" value="#{msg['label.case.routing.routedBy']}" for="routedByCommandLink1"/>
								<h:panelGroup id="routingTableGrpId2">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:div id="CaseRoutingTDiv003" styleClass="alignLeft">
									<t:commandLink id="routedByCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortRoutingInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"										rendered="#{logCaseDataBean.imageRender != 'routedByCommandLink1'}">
										<m:div title="#{msg['alt.for.ascending']}" styleClass="sort_asc" 
											id="routingTableImgId1" />
										<f:attribute name="columnName" value="routedBy" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:div id="CaseRoutingTDiv004" styleClass="alignLeft">
									<t:commandLink id="routedByCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortRoutingInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"										rendered="#{logCaseDataBean.imageRender != 'routedByCommandLink2'}">
										<m:div title="#{msg['alt.for.descending']}" styleClass="sort_desc"
											id="routingTableImgId2" />
										<f:attribute name="columnName" value="routedBy" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
				
						<h:outputText value="#{routingResult.routedByName}" id="routingTableOutTxtId3"/> <%-- Modified for the defect id ESPRD00702153_30NOV2011 --%>
						<%-- Modified the below statement for the defect id ESPRD00702153_30NOV2011 --%>
						<h:outputText id="routingTableOutTxtId4" value=" - #{routingResult.routedByUserID}" rendered="#{routingResult.routedByUserID !=null && routingResult.routedByUserID !=''}" />
					</t:column>

					<t:column id="routingcolumn3">
						<f:facet name="header">
							<h:panelGrid columns="2" id="routingTableGridId3">
								<h:outputLabel id="routingTableOutLbl3" value="#{msg['label.case.routing.routedTo']}" for="routedToCommandLink1"/>
								<h:panelGroup id="routingTableGrpId3">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:div id="CaseRoutingTDiv005" styleClass="alignLeft">
									<t:commandLink id="routedToCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortRoutingInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"										rendered="#{logCaseDataBean.imageRender != 'routedToCommandLink1'}">
										<m:div title="#{msg['alt.for.ascending']}" styleClass="sort_asc"
										id="routingTableImgId3"	/>
										<f:attribute name="columnName" value="routedTo" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:div id="CaseRoutingTDiv006" styleClass="alignLeft">
									<t:commandLink id="routedToCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortRoutingInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"										rendered="#{logCaseDataBean.imageRender != 'routedToCommandLink2'}">
										<m:div title="#{msg['alt.for.descending']}" styleClass="sort_desc"
										 id="routingTableImgId4"	/>
										<f:attribute name="columnName" value="routedTo" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
				 
						<h:outputText value="#{routingResult.routedToName}" id="routingTableOutTxtId5"/>
					</t:column>
					<t:column id="routingcolumn4">
						<f:facet name="header">
							<h:panelGrid columns="2" id="routingTableGridId4">
								<h:outputLabel id="routingTableOutLbl4" value="#{msg['label.case.routing.watchList']}" for="routingWatchListCommandLink1"/>
								<h:panelGroup id="routingTableGrpId4">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:div id="CaseRoutingTDiv007" styleClass="alignLeft">
									<t:commandLink id="routingWatchListCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortRoutingInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"										rendered="#{logCaseDataBean.imageRender != 'routingWatchListCommandLink1'}">
										<m:div title="#{msg['alt.for.ascending']}" styleClass="sort_asc"
										id="routingTableImgId5"	/>
										<f:attribute name="columnName" value="watchList" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:div id="CaseRoutingTDiv008" styleClass="alignLeft">
									<t:commandLink id="routingWatchListCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortRoutingInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"										rendered="#{logCaseDataBean.imageRender != 'routingWatchListCommandLink2'}">
										<m:div title="#{msg['alt.for.descending']}" styleClass="sort_desc"
										id="routingTableImgId6"	/>
										<f:attribute name="columnName" value="watchList" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<%--Modified for defect ESPRD00778600 starts --%>
						<t:div rendered="#{routingResult.addThisRecordToMyWatchlist=='true'}">
								<m:img id="logCaseRoutingWatchListImageID" src="/wps/themes/html/ACSDefault/images/icn_check.gif" alt="#{msg['label.yes']}" title="#{msg['label.yes']}"></m:img>
							</t:div>
						<%-- <h:outputText value="#{routingResult.addThisRecordToMyWatchlist}" id="routingTableOutTxtId6"/>--%>
						<%--Modified for defect ESPRD00778600 ends --%>
					</t:column>
				</t:dataTable>
				<t:dataScroller for="routingTable" paginator="true"
					onclick="javascript:flagWarn=false;focusPage('caseRoutingHeader');"
					paginatorActiveColumnStyle='font-weight:bold;'
					paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"
					pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"
					lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
					styleClass="dataScroller" id="routingTableScrollerId">
					<f:facet name="previous">
						<h:outputText styleClass="underline" value=" #{msg['label.lessthan']} "							rendered="#{pageIndex > 1}" id="routingTableScrollerPrevid"></h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText styleClass="underline" value=" #{msg['label.greaterthan']} "							rendered="#{pageIndex < pageCount}" id="routingTableScrollerNxtId"></h:outputText>
					</f:facet>
					<h:outputText id="PRGCMGTOT592" rendered="#{rowsCount > 0}"
						value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
						styleClass="dataScrollerText" id="routingTableScrollerRowsCountId"/>
				</t:dataScroller>
				<m:br/>
				<h:dataTable var="dummyRouting"						rendered="#{!logCaseDataBean.showRoutingDataTable}"						styleClass="dataTable" cellspacing="0" width="100%" border="0"						headerClass="tableHead" rowClasses="rowone,row_alt"						id="routingdummyTable">
						<t:column id="dummyroutingcolumn1">
							<f:facet name="header">
								<h:outputText value="#{msg['label.case.routing.date']}" id="routingdummyTableDateId"/>
							</f:facet>
						</t:column>
						<t:column id="dummyroutingcolumn2">
							<f:facet name="header">
								<h:outputText value="#{msg['label.case.routing.routedBy']}" id="routingdummyTableRoutedById"/>
							</f:facet>
						</t:column>
						<t:column id="dummyroutingcolumn3">
							<f:facet name="header">
								<h:outputText value="#{msg['label.case.routing.routedTo']}" id="routingdummyTableRoutedToId"/>
							</f:facet>
						</t:column>
						<t:column id="dummyroutingcolumn4">
							<f:facet name="header">
								<h:outputText value="#{msg['label.case.routing.watchList']}" id="routingdummyTableWatchListId"/>
							</f:facet>
						</t:column>
				</h:dataTable>
				<m:table styleClass="dataTable" width="100%" border="0"
					rendered="#{!logCaseDataBean.showRoutingDataTable}" id="showRoutingNoDataTable">
					<m:tr id="showRoutingNoDataTableRow">
						<m:td align="center" id="showRoutingNoDataTableColmn">
							<h:outputText value="#{msg['value.noData']}" id="showRoutingNoDataMsgId"/>
						</m:td>
					</m:tr>
				</m:table>
				<m:br/>
				<m:div styleClass="clearl" >
				</m:div>
				<m:div rendered="#{logCaseDataBean.showAddRoutingAssignment}">
					<m:anchor name="logCaseAddRouting"></m:anchor>
					<f:subview id="logCaseAddRoutingSubView1">
					 <jsp:include page="inc_logCaseAddRouting.jsp" />
					</f:subview>
				</m:div>
				<m:div styleClass="clearl">
				</m:div>
				<m:div rendered="#{logCaseDataBean.showEditRoutingAssignment}">
					<m:anchor name="logCaseEditRouting"></m:anchor>
					<jsp:include page="inc_logCaseEditRouting.jsp" />
				</m:div>
			</m:div>
		</m:section>
	</m:div>
</m:div>


