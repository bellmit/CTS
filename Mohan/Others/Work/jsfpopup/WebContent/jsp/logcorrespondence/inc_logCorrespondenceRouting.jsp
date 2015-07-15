<%-- 
  - Author(s): Wipro
  - Date: Mon Jan 29 14:37:45 IST 2007
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
  --%>
<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CommonRouting"
	var="rout" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<m:div>
	<f:subview id="logCrspdRoutingSubview">
		<m:section id="PROVIDERMMS20120731164811385" styleClass="otherbg">
			<m:legend>

				<h:outputLink
					onclick="setHiddenValue('logCorrespondence:logCrspdRouSubview:logCrspdRoutingSubview:routingHidden','minus');toggle('log_div_routing',2);							plusMinusToggle('log_div_routing',this,'logCorrespondence:logCrspdRouSubview:logCrspdRoutingSubview:routingHidden');							 return false;"
					onmousedown="javascript:flagWarn=false;" id="plusMinusRouting"
					styleClass="plus">

					<h:outputText id="PRGCMGTOT872"
						value="#{rout['label.routing.routing']}" />
					<h:inputHidden id="routingHidden"
						value="#{CorrespondenceDataBean.routingHidden}"></h:inputHidden>
				</h:outputLink>

			</m:legend>

			<h:inputHidden id="routingErr" value="ErrMsgHolder" />
			<m:inputHidden value="#{RoutingControllerBean.intialData}"></m:inputHidden>

			<%-- <h:message id="PRGCMGTM150" for="routingErr" showDetail="true" styleClass="color: red" /> --%>

			<m:div sid="log_div_routing">

				<m:div id="tb_routing">
					<m:table id="PROVIDERMMT20120731164811386" styleClass="tableBar" width="100%">
						<m:tr>
							<m:td styleClass="tableAction">
								<h:commandButton styleClass="formBtn" id="add"
									value="#{rout['label.routing.addrouting']}"
									action="#{RoutingControllerBean.addRouting}"
									disabled="#{CorrespondenceDataBean.crClosed || !RoutingControllerBean.controlRequired}"
									onmousedown="javascript:focusThis(this.id);flagWarn=false;">
								</h:commandButton>

							</m:td>
						</m:tr>
					</m:table>
					<t:dataTable id="routingDataTable" width="100%"
						rowIndexVar="rowIndex" styleClass="dataTable" rows="10"
						var="routingDetails" columnClasses="columnClass"
						headerClass="headerClass" footerClass="footerClass"
						rowClasses="row_alt,row"
						onmouseover="return tableMouseOver(this, event);"
						onmouseout="return tableMouseOut(this, event);"
						first="#{RoutingDataBean.routingFirstIndex}"
						value="#{RoutingDataBean.listOfCMRoutingVOs}">

						<h:column id="dateCol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD106" columns="2">
									<h:outputLabel id="dateLabel" for="dateLabelGroup"
										value="#{rout['label.routing.date']}" />
									<h:panelGroup id="dateLabelGroup">
										<t:div styleClass="width x;align=left">
											<t:commandLink id="dateAscCmdLink" styleClass="clStyle"
												actionListener="#{RoutingControllerBean.sortRouting}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{RoutingDataBean.imageRender != 'dateAscCmdLink'}">

												<%--Note:Commented for DEFECT ESPRD00061087 --%>
												<%--<m:div title="#{rout['title.routing.forAscd']}"
													styleClass="sort_asc" />--%>
												<%--Note:Added for DEFECT ESPRD00061087 --%>
											<%-- style removed for the defect ESPRD00707670 for all the columns of datatable --%>												
												<%--<h:graphicImage id="routSortAsc1"
													alt="#{rout['title.routing.forAscd']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
												<m:div id="routSortAsc1" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{rout['id.routing.columnName']}"
													value="#{rout['label.routing.date']}" />
												<f:attribute name="#{rout['id.routing.sortOrder']}"
													value="#{rout['value.routing.ascending']}" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="width x;align=left">
											<t:commandLink id="dateDscCmdLink"
												actionListener="#{RoutingControllerBean.sortRouting}"
												onmousedown="javascript:flagWarn=false;"
												style="text-decoration: none;"
												rendered="#{RoutingDataBean.imageRender != 'dateDscCmdLink'}">
												<%--Note:Commented for DEFECT ESPRD00061087 --%>
												<%--<m:div title="#{rout['title.routing.forDsnd']}"
													styleClass="sort_desc" />--%>

												<%--Note:Added for DEFECT ESPRD00061087 --%>
												<%--<h:graphicImage id="routSortDesc1"
													alt="#{rout['title.routing.forDsnd']}"
													styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>

												<m:div id="routSortDesc1" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{rout['id.routing.columnName']}"
													value="#{rout['label.routing.date']}" />
												<f:attribute name="#{rout['id.routing.sortOrder']}"
													value="#{rout['value.routing.descending']}" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<t:commandLink id="editRouting"
								onmousedown="javascript:focusThis(this.id);flagWarn=false;"
								action="#{RoutingControllerBean.getRoutingDetails}">
								<h:outputText id="valDateRtng"
									value="#{routingDetails.dateRoutedString}" />
								<f:param name="indexCode" value="#{rowIndex}"></f:param>
							</t:commandLink>
						</h:column>
						<h:column id="rtByCol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD107" columns="2">
									<h:outputLabel id="rtByLabel" for="rtByLabelVal"
										value="#{rout['label.routing.routedby']}" />
									<h:panelGroup id="rtByLabelVal">
										<t:div styleClass="width x;align=left">
											<t:commandLink id="rtByAscCmdLink"
												actionListener="#{RoutingControllerBean.sortRouting}"
												onmousedown="javascript:flagWarn=false;"
												style="text-decoration: none;"
												rendered="#{RoutingDataBean.imageRender != 'rtByAscCmdLink'}">

												<%--Note:Commented for DEFECT ESPRD00061087 --%>
												<%--<m:div title="#{rout['title.routing.forAscd']}"
													styleClass="sort_asc" />--%>
												<%--Note:Added for DEFECT ESPRD00061087 --%>
												<%-- <h:graphicImage id="routSortAsc2"
													alt="#{rout['title.routing.forAscd']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
												<m:div id="routSortAsc2" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{rout['id.routing.columnName']}"
													value="#{rout['label.routing.routedby']}" />
												<f:attribute name="#{rout['id.routing.sortOrder']}"
													value="#{rout['value.routing.ascending']}" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="width x;align=left">
											<t:commandLink id="rtByDscCmdLink"
												actionListener="#{RoutingControllerBean.sortRouting}"
												onmousedown="javascript:flagWarn=false;"
												style="text-decoration: none;"
												rendered="#{RoutingDataBean.imageRender != 'rtByDscCmdLink'}">

												<%--Note:Commented for DEFECT ESPRD00061087 --%>
												<%--<m:div title="#{rout['title.routing.forDsnd']}"
													styleClass="sort_desc" />--%>
												<%--Note:Added for DEFECT ESPRD00061087 --%>
												<%--  <h:graphicImage id="routSortDesc2"
													alt="#{rout['title.routing.forDsnd']}"
													styleClass="sort_desc" width="8" url="/images/x.gif" />--%>
												<m:div id="routSortDesc2" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{rout['id.routing.columnName']}"
													value="#{rout['label.routing.routedby']}" />
												<f:attribute name="#{rout['id.routing.sortOrder']}"
													value="#{rout['value.routing.descending']}" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valRtBy" value="#{routingDetails.routedByName}" />
							<h:outputText id="PRGCMGTOT873"
								value="-#{routingDetails.routedByUserID}"
								rendered="#{routingDetails.routedByUserID!=null && routingDetails.routedByUserID!=''}"></h:outputText>
						</h:column>
						<h:column id="rtToCol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD108" columns="2">
									<h:outputLabel id="rtToLabel" for="routedtoVal"
										value="#{rout['label.routing.routedto']}" />
									<h:panelGroup id="routedtoVal">
										<t:div styleClass="width x;align=left">
											<t:commandLink id="rtToAscCmdLink" styleClass="clStyle"
												actionListener="#{RoutingControllerBean.sortRouting}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{RoutingDataBean.imageRender != 'rtToAscCmdLink'}">

												<%--Note:Commented for DEFECT ESPRD00061087 --%>
												<%--<m:div title="#{rout['title.routing.forAscd']}"
													styleClass="sort_asc" />--%>
												<%--Note:Added for DEFECT ESPRD00061087 --%>
												<%-- <h:graphicImage id="routSortAsc3"
													alt="#{rout['title.routing.forAscd']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" />--%>
												<m:div id="routSortAsc3" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{rout['id.routing.columnName']}"
													value="#{rout['label.routing.routedto']}" />
												<f:attribute name="#{rout['id.routing.sortOrder']}"
													value="#{rout['value.routing.ascending']}" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="width x;align=left">
											<t:commandLink id="rtToDscCmdLink" styleClass="clStyle"
												actionListener="#{RoutingControllerBean.sortRouting}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{RoutingDataBean.imageRender != 'rtToDscCmdLink'}">
												<%--Note:Commented for DEFECT ESPRD00061087 --%>
												<%--<m:div title="#{rout['title.routing.forDsnd']}"
													styleClass="sort_desc" />--%>
												<%--Note:Added for DEFECT ESPRD00061087 --%>
												<%-- <h:graphicImage id="routSortDesc3"
													alt="#{rout['title.routing.forDsnd']}"
													styleClass="sort_desc" width="8" url="/images/x.gif" />--%>
												<m:div id="routSortDesc3" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{rout['id.routing.columnName']}"
													value="#{rout['label.routing.routedto']}" />
												<f:attribute name="#{rout['id.routing.sortOrder']}"
													value="#{rout['value.routing.descending']}" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valRtTo" value="#{routingDetails.routedToName}" />
							<h:outputText id="PRGCMGTOT874"
								value="-#{routingDetails.routedToUserID}"
								rendered="#{routingDetails.routedToUserID!=null && routingDetails.routedToUserID!=''}"></h:outputText>
						</h:column>
						<h:column id="watchListCol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD109" columns="2">
									<h:outputLabel id="watchListLabel" for="watchListLabelVal"
										value="#{rout['label.routing.watchlist']}" />
									<h:panelGroup id="watchListLabelVal">
										<t:div styleClass="width x;align=left">
											<t:commandLink id="watchListAscCmdLink" styleClass="clStyle"
												actionListener="#{RoutingControllerBean.sortRouting}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{RoutingDataBean.imageRender != 'watchListAscCmdLink'}">

												<%--Note:Commented for DEFECT ESPRD00061087 --%>
												<%--<m:div title="#{rout['title.routing.forAscd']}"
													styleClass="sort_asc" />--%>
												<%--Note:Added for DEFECT ESPRD00061087 --%>
												<%-- <h:graphicImage id="routSortAsc4"
													alt="#{rout['title.routing.forAscd']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" />--%>
												<m:div id="routSortAsc4" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{rout['id.routing.columnName']}"
													value="#{rout['label.routing.watchlist']}" />
												<f:attribute name="#{rout['id.routing.sortOrder']}"
													value="#{rout['value.routing.ascending']}" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="width x;align=left">
											<t:commandLink id="watchListDscCmdLink" styleClass="clStyle"
												actionListener="#{RoutingControllerBean.sortRouting}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{RoutingDataBean.imageRender != 'watchListAscCmdLink'}">
												<%--Note:Commented for DEFECT ESPRD00061087 --%>
												<%--<m:div title="#{rout['title.routing.forDsnd']}"
													styleClass="sort_desc" />--%>
												<%--Note:Added for DEFECT ESPRD00061087 --%>
												<%-- <h:graphicImage id="routSortDesc4"
													alt="#{rout['title.routing.forDsnd']}"
													styleClass="sort_desc" width="8" url="/images/x.gif" />--%>
												<m:div id="routSortDesc4" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{rout['id.routing.columnName']}"
													value="#{rout['label.routing.watchlist']}" />
												<f:attribute name="#{rout['id.routing.sortOrder']}"
													value="#{rout['value.routing.descending']}" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<%--DEFECT FIXED FOR ESPRD00707668--%>
							<%--<h:graphicImage title="true" styleClass="ind_check_yes"
								width="15" alt="#{crspd['title.crspd.true']}"
								rendered="#{routingDetails.addToMyWatchList=='true'}"
								url="/images/x.gif" /> --%>
								
							<t:div rendered="#{routingDetails.addToMyWatchList=='true'}">
								<m:img id="logCorresRoutingWatchListImageID" src="/wps/themes/html/ACSDefault/images/icn_check.gif" alt="#{ref['label.ref.yes']}" title="#{ref['label.ref.yes']}"></m:img>
							</t:div>

							<%--<h:outputText id="valWatchlist"								value="#{routingDetails.addToMyWatchList}" />
						--%>
						</h:column>
						<f:facet name="footer">
							<m:div id="nodata" align="center"
								rendered="#{RoutingDataBean.renderNoData}">
								<h:outputText id="PRGCMGTOT875"
									value="#{ref['label.ref.noData']}" />
							</m:div>
						</f:facet>
					</t:dataTable>

					<%--Defect Fixed Below --%>

					<%--<t:dataScroller id="CMGTTOMDS33" pageCountVar="pageCount" pageIndexVar="pageIndex"						paginator="true" paginatorActiveColumnStyle='font-weight:bold;'						paginatorMaxPages="3" immediate="false" for="routingDataTable"						firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"						rowsCountVar="rowsCount"						styleClass="dataScroller">
						<f:facet name="previous">
							<h:outputText id="CMGTOT52" styleClass="text-decoration:underline;"								value="#{ref['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
						</f:facet>
						<f:facet name="next">
							<h:outputText id="CMGTOT53" styleClass="text-decoration:underline;"								value="#{ref['label.ref.gt']}"								rendered="#{pageIndex < pageCount}"></h:outputText>
						</f:facet>
						<h:outputText id="CMGTOT54"							value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"							styleClass="float:left;position:relative;bottom:-6px;font-weight:bold;"							rendered="#{rowsCount>0}">
						</h:outputText>
					</t:dataScroller>  --%>
					<m:div styleClass="searchResults">
						<t:dataScroller id="CMGTTOMDS34" pageCountVar="pageCount"
							pageIndexVar="pageIndex" onclick="flagWarn=false;"
							paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
							paginatorMaxPages="3" immediate="false" for="routingDataTable"
							firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
							rowsCountVar="rowsCount" styleClass="dataScroller">
							<f:facet name="previous">
								<h:outputText id="CMGTOT55" styleClass="underline"
									value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
							</f:facet>
							<f:facet name="next">
								<h:outputText id="CMGTOT56" styleClass="underline"
									value=" #{ref['label.ref.gt']} "
									rendered="#{pageIndex < pageCount}"></h:outputText>
							</f:facet>
							<h:outputText id="CMGTOT57"
								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								styleClass="dataScrollerText" rendered="#{rowsCount>0}">
							</h:outputText>
						</t:dataScroller>
					</m:div>
					<m:br />
					<m:br />

					<m:div styleClass="moreInfo"
						rendered="#{RoutingDataBean.renderAddRouting || RoutingDataBean.renderEditRouting}">
						<m:div styleClass="moreInfoBar"
							rendered="#{RoutingDataBean.renderAddRouting}">
							<m:div styleClass="infoTitle">
								<h:outputText id="PRGCMGTOT879"
									value="#{rout['label.routing.newrouting']}" />
							</m:div>
							<%--Note:commented for DEFECT ESPRD00576206 --%>
							<%--	<m:div styleClass="infoActions">
								<h:panelGroup id="PRGCMGTPGP68">
									<m:div >
										<%--Defect Fixed Below for save ,reset and cancel link --%>
							<%--<h:commandLink styleClass="strong" id="saveRoutingID"											action="#{RoutingControllerBean.saveRouting}" 											rendered="#{RoutingControllerBean.controlRequired}"											onmousedown="javascript:flagWarn=false;focusThis(this.id);">
											<h:outputText id="CMGTOT58" value="#{ent['label-sw-save']}" />
										</h:commandLink> 
										<h:commandButton id="CMGTCB5"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:32px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;font-weight:bold; clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"											onmousedown="javascript:flagWarn=false;"											disabled="#{!RoutingControllerBean.controlRequired}"											value="#{ent['label-sw-save']}"											action="#{RoutingControllerBean.saveRouting}" />
										<h:outputText id="CMGTOT59" escape="false"	value="#{ref['label.ref.linkSeperator']}" rendered="#{RoutingControllerBean.controlRequired}"/>
										
										<%--<h:commandLink styleClass="commandLink" id= "resetRoutingID"											action="#{RoutingControllerBean.resetRouting}" 											rendered="#{RoutingControllerBean.controlRequired}"											onmousedown="javascript:flagWarn=false;focusThis(this.id);">
											<h:outputText id="CMGTOT60" value="#{ent['label-sw-reset']}" />
										</h:commandLink> 
										<h:commandButton id="CMGTCB6"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"											onmousedown="javascript:flagWarn=false;"											disabled="#{!RoutingControllerBean.controlRequired}"											value="#{ent['label-sw-reset']}"											action="#{RoutingControllerBean.saveRouting}" /> 
										 <h:outputText id="CMGTOT61" escape="false"	value="#{ref['label.ref.linkSeperator']}" rendered="#{RoutingControllerBean.controlRequired}"/>
									
                                       <h:commandButton id="cancel"									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"									value="#{ent['label-sw-cancel']}"									action="#{RoutingControllerBean.cancelRouting}"																		onclick="if (!(confirm('Unsaved changes will be lost.  Are you sure that you want to navigate?'))) return(false);"									onmousedown="javascript:focusThis(this.id);flagWarn=false;">
								</h:commandButton>
									<%--<h:commandLink id="PRGCMGTCL140" styleClass="commandLink"										action="#{RoutingControllerBean.cancelRouting}" onmousedown="if (!(confirm('Unsaved changes will be lost.  Are you sure that you want to navigate?'))) return(false);">
										<h:outputText id="PRGCMGTOT884" value="#{ent['label-sw-cancel']}" />
									</h:commandLink>
							</m:div>
								</h:panelGroup>
								
							</m:div>--%>
							<%--Note:started fixing for DEFECT ESPRD00576206 --%>
							<m:div styleClass="infoActions">
								<%--<h:commandLink id="cancel" value="#{ent['label-sw-cancel']}"
									action="#{RoutingControllerBean.cancelRouting}"
									onmousedown="javascript:focusThis(this.id);">
								</h:commandLink>
							--%>
							<%--Modified for the Defect ID : ESPRD00778837 for reset link to work--%>
							
							<h:commandButton id="cancel" value="#{ent['label-sw-cancel']}"
									action="#{RoutingControllerBean.cancelRouting}"
									onmousedown="javascript:focusThis(this.id);"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:50px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:0px"
									/>
								
							</m:div>
							<m:div styleClass="infoActions">
								<t:commandLink styleClass="strong" id="saveRoutingID"
									action="#{RoutingControllerBean.saveRouting}"
									rendered="#{RoutingControllerBean.controlRequired}"
									onmousedown="javascript:flagWarn=false;focusThis(this.id);">
									<h:outputText id="CMGTOT62" value="#{ent['label-sw-save']}" />
								</t:commandLink>
								<h:outputText id="CMGTOT63" escape="false"
									value="#{ref['label.ref.linkSeperator']}"
									rendered="#{RoutingControllerBean.controlRequired}" />
								<t:commandLink styleClass="commandLink" id="resetRoutingID"
									action="#{RoutingControllerBean.resetRouting}"
									rendered="#{RoutingControllerBean.controlRequired}"
									onmousedown="javascript:flagWarn=false;focusThis(this.id);">
									<h:outputText id="CMGTOT64" value="#{ent['label-sw-reset']}" />
								</t:commandLink>
								<h:outputText id="CMGTOT65" escape="false"
									value="#{ref['label.ref.linkSeperator']}"
									rendered="#{RoutingControllerBean.controlRequired}" />

							</m:div>
							<%--Note:Ended for DEFECT ESPRD00576206 --%>
						</m:div>
						<m:div styleClass="moreInfoBar"
							rendered="#{RoutingDataBean.renderEditRouting}">
							<m:div styleClass="infoTitle">
								<h:outputText id="PRGCMGTOT885"
									value="#{rout['label.routing.viewrouting']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:panelGroup id="PRGCMGTPGP69">
									<h:commandButton id="PRGCMGTCB26"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										value="#{rout['label.routing.close']}"
										action="#{RoutingControllerBean.closeRouting}"
										onmousedown="javascript:flagWarn=false;">
									</h:commandButton>
									<%--<h:commandLink id="PRGCMGTCL141" styleClass="strong"									
									 action="#{RoutingControllerBean.cancelRouting}" onmousedown="if (!(confirm('Unsaved changes will be lost.  Are you sure that you want to navigate?'))) return(false);">
									<h:outputText id="PRGCMGTOT886" value="#{rout['label.routing.close']}" />
									</h:commandLink>--%>
								</h:panelGroup>
							</m:div>
						</m:div>

						<m:div styleClass="moreInfoContent">
							<m:div styleClass="padb">
								<m:div styleClass="msgBox"
									rendered="#{RoutingDataBean.showSucessMessage}">
									<h:outputText id="PRGCMGTOT887"
										value="#{ent['label-sw-success']}" />
								</m:div>
								<m:table id="PROVIDERMMT20120731164811387" cellspacing="0" width="94%">
									<m:tr>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="crtByLabel" for="crtBy"
													value="#{rout['label.routing.createdby']}" />
												<m:br />
												<h:outputText id="crtBy"
													value="#{RoutingDataBean.cmRoutingVO.createdByName}" />
												<h:outputText id="PRGCMGTOT888"
													value="-#{RoutingDataBean.cmRoutingVO.createdByUserID}"
													rendered="#{RoutingDataBean.cmRoutingVO.createdByUserID!=null && RoutingDataBean.cmRoutingVO.createdByUserID!=''}"></h:outputText>
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="assgToLabel" for="assgTo"
													value="#{rout['label.routing.assignedto']}" />
												<m:br />
												<h:outputText id="assgTo"
													value="#{RoutingDataBean.cmRoutingVO.assignedToName}" />
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="repUnitLabel" for="repUnit"
													value="#{rout['label.routing.reportingunit']}" />
												<m:br />
												<h:outputText id="repUnit"
													value="#{RoutingDataBean.cmRoutingVO.reportingUnit}" />
											</m:div>
										</m:td>
									</m:tr>
									<m:tr rendered="#{RoutingDataBean.renderAddRouting}">
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="PRGCMGTOT889"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="showroutLabelAdd" for="showroutAdd"
													value="#{rout['label.routing.show']}" />
												<m:br />
												<h:selectOneMenu id="showroutAdd"
													value="#{RoutingDataBean.cmRoutingVO.groupType}"
													valueChangeListener="#{RoutingControllerBean.showUsersOrDepts}"
													onchange="javascript:flagWarn=false; this.form.submit();"
													immediate="true">
													<f:selectItem itemValue=" " itemLabel="Please Select One" />
													<f:selectItems
														value="#{RoutingDataBean.refListOfWorkUnitTypes}" />
												</h:selectOneMenu>
												<m:br />
												<h:message id="PRGCMGTM151" for="showroutAdd"
													showDetail="true" style="color: red" />
											</m:div>
										</m:td>
										<m:td rendered="#{RoutingDataBean.renderBunitNames}">
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="PRGCMGTOT890"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="showBunitLabelAdd" for="showBunitAdd"
													value="Business Unit" />
												<m:br />
												<h:selectOneMenu id="showBunitAdd"
													value="#{RoutingDataBean.cmRoutingVO.busineesUnit}"
													valueChangeListener="#{RoutingControllerBean.getDeptsForBsnsUnit}"
													onchange="javascript:flagWarn=false; this.form.submit();"
													immediate="true">
													<f:selectItem itemValue=" " itemLabel="Please Select One" />
													<f:selectItems value="#{RoutingDataBean.businessUnitsList}" />
												</h:selectOneMenu>
												<m:br />
												<h:message id="PRGCMGTM152" for="showBunitAdd"
													showDetail="true" style="color: red" />
											</m:div>
										</m:td>
										<m:td rendered="#{RoutingDataBean.renderUserNames}">
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="PRGCMGTOT891"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="assgRecToLabelAdd" for="assgRecToAdd"
													value="#{rout['label.routing.addrecto']}" />
												<m:br />
												<h:selectOneMenu id="assgRecToAdd"
													value="#{RoutingDataBean.cmRoutingVO.assignThisRecordToSK}"
													valueChangeListener="#{RoutingControllerBean.showUserDepts}"
													onchange="javascript:flagWarn=false; this.form.submit();"
													immediate="true">

													<f:selectItem itemValue=" " itemLabel="Please Select One" />
													<f:selectItems value="#{RoutingDataBean.listOfUsers}" />
													<%-- onchange="this.form.submit();" immediate="true" 	valueChangeListener="#{RoutingControllerBean.showUserDepts}" --%>
												</h:selectOneMenu>
												<m:br />
												<h:message id="PRGCMGTM153" for="assgRecToAdd"
													showDetail="true" style="color: red" />
											</m:div>
										</m:td>
										<m:td rendered="#{RoutingDataBean.renderUserDeptNames}">
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="PRGCMGTOT892"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="userDeptNameLabelAdd"
													for="userDeptNameAdd"
													value="#{rout['label.routing.userworkunit']}" />
												<m:br />
												<h:selectOneMenu id="userDeptNameAdd"
													disabled="#{RoutingDataBean.renderCompForOne}"
													value="#{RoutingDataBean.cmRoutingVO.deptSK}">
													<f:selectItem itemValue=" "
														itemLabel="#{RoutingDataBean.workUnitValue}" />
													<f:selectItems value="#{RoutingDataBean.listOfUserDepts}" />
												</h:selectOneMenu>
												<m:br />
												<h:message id="PRGCMGTM154" for="userDeptNameAdd"
													showDetail="true" style="color: red" />
											</m:div>
										</m:td>
										<m:td rendered="#{RoutingDataBean.renderDeptNames}">
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="PRGCMGTOT893"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="deptNameLabelAdd" for="deptNameAdd"
													value="#{rout['label.routing.workunit']}" />
												<m:br />
												<h:selectOneMenu id="deptNameAdd"
													value="#{RoutingDataBean.cmRoutingVO.deptSK}"
													valueChangeListener="#{RoutingControllerBean.showDeptUsers}"
													onchange="javascript:flagWarn=false; this.form.submit();"
													immediate="true">
													<f:selectItem itemValue=" " itemLabel="Please Select One" />
													<f:selectItems value="#{RoutingDataBean.listOfDepts}" />
													<%-- onchange="this.form.submit();" immediate="true" valueChangeListener="#{RoutingControllerBean.showDeptUsers}" --%>
												</h:selectOneMenu>


												<m:br />
												<h:message id="PRGCMGTM155" for="deptNameAdd"
													showDetail="true" style="color: red" />
											</m:div>
										</m:td>

										<m:td rendered="#{RoutingDataBean.renderBuDeptNames}">
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="PRGCMGTOT894"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="BuDeptNameLabelAdd" for="BuDeptNameAdd"
													value="#{rout['label.routing.addrecto']}" />
												<m:br />
												<h:selectOneMenu id="BuDeptNameAdd"
													value="#{RoutingDataBean.cmRoutingVO.deptSK}">
													<f:selectItem itemValue=" " itemLabel="Please Select One" />
													<f:selectItems value="#{RoutingDataBean.listOfDepts}" />
												</h:selectOneMenu>
												<m:br />
												<h:message id="PRGCMGTM156" for="BuDeptNameAdd"
													showDetail="true" style="color: red" />
											</m:div>
										</m:td>

										<m:td rendered="#{RoutingDataBean.renderDeptUserNames}">
											<m:div styleClass="padb">

												<h:outputLabel id="deptAssgRecToLabelAdd"
													for="deptAssgRecToAdd"
													value="#{rout['label.routing.addrecto']}" />
												<m:br />
												<h:selectOneMenu id="deptAssgRecToAdd"
													value="#{RoutingDataBean.cmRoutingVO.assignThisRecordToSK}">
													<f:selectItem itemValue=" " itemLabel="Please Select One" />
													<f:selectItems value="#{RoutingDataBean.listOfDeptUsers}" />
													<%--<f:selectItems
													value="#{RoutingDataBean.listOfDeptUsers}" />--%>
												</h:selectOneMenu>
											</m:div>
										</m:td>
									</m:tr>
									<%--<m:tr rendered="#{RoutingDataBean.renderEditRouting}">
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="showroutLabelEdit" for="showroutEdit"													value="#{rout['label.routing.show']}" />
												<m:br />
												<h:outputText id="showroutEdit"													value="#{RoutingDataBean.cmRoutingVO.groupTypeDesc}" />
											</m:div>
										</m:td>

										<m:td rendered="#{RoutingDataBean.renderUserNames}">
											<m:div styleClass="padb">
												<h:outputLabel id="assgRecToLabelEdit" for="assgRecToEdit"													value="#{rout['label.routing.addrecto']}" />
												<m:br />
												<h:outputText id="assgRecToEdit"													value="#{RoutingDataBean.cmRoutingVO.assThisRecToName}" />
											</m:div>
										</m:td>

										<m:td rendered="#{RoutingDataBean.renderUserDeptNames}">
											<m:div styleClass="padb">
												<h:outputLabel id="userDeptNameLabelEdit"													for="userDeptNameEdit"													value="#{rout['label.routing.userworkunit']}" />
												<m:br />
												<h:outputText id="userDeptNameEdit"													value="#{RoutingDataBean.cmRoutingVO.deptName}" />
											</m:div>
										</m:td>

										<m:td rendered="#{RoutingDataBean.renderDeptNames}">
											<m:div styleClass="padb">
												<h:outputLabel id="deptNameLabelEdit" for="deptNameEdit"													value="#{rout['label.routing.workunit']}" />
												<m:br />
												<h:outputText id="deptNameEdit"													value="#{RoutingDataBean.cmRoutingVO.deptName}" />
											</m:div>
										</m:td>
										
										<m:td rendered="#{RoutingDataBean.renderBuDeptNames}">
											<m:div styleClass="padb">
												<h:outputLabel id="BuNameLabelEdit" for="buDeptNameEdit"													value="#{rout['label.routing.addrecto']}" />
												<m:br/>
												<h:outputText id="buDeptNameEdit"													value="#{RoutingDataBean.cmRoutingVO.deptName}" />
											</m:div>
										</m:td>
										
										<m:td rendered="#{RoutingDataBean.renderDeptUserNames}">
											<m:div styleClass="padb">
												<h:outputLabel id="deptAssgRecToLabelEdit"													for="deptAssgRecToEdit"													value="#{rout['label.routing.addrecto']}" />
												<m:br />
												<h:outputText id="deptAssgRecToEdit"													value="#{RoutingDataBean.cmRoutingVO.assThisRecToName}" />
													<h:outputText id="PRGCMGTOT895" 													value="-#{RoutingDataBean.cmRoutingVO.assignThisRecordToSK}" rendered="#{RoutingDataBean.cmRoutingVO.assignThisRecordToSK!=null && RoutingDataBean.cmRoutingVO.assignThisRecordToSK!=''}"/>
													
											</m:div>
										</m:td>
									</m:tr>--%>
									<m:tr>
										<m:td>
											<m:div styleClass="padb">
												<h:selectBooleanCheckbox id="addToWLInd"
													disabled="#{RoutingDataBean.renderEditRouting}"
													value="#{RoutingDataBean.cmRoutingVO.addToMyWatchList}">
												</h:selectBooleanCheckbox>
												<h:outputLabel id="addToWL" for="addToWLInd"
													value="#{rout['label.routing.addrectowl']}" />
											</m:div>
										</m:td>
									</m:tr>

								</m:table>
								<%-- Jsp for Audit Log 
										<m:div >
				                       	  <f:subview id="routingAuditLogSubview" rendered="#{RoutingDataBean.renderEditRouting}">
						                     <jsp:include
						                    	page="/jsp/logcorrespondence/inc_CRRoutingAuditLog.jsp" />
					                    </f:subview>
				                      </m:div>--%>


								<m:div
									rendered="#{not empty RoutingDataBean.cmRoutingVO.routedToSK}">
									<f:subview id="routingAuditLogSubview">
										<%--jsp:include
						                    	page="/jsp/logcorrespondence/inc_CRAlertAuditLog.jsp" /---%>
										<m:div rendered="#{CorrespondenceDataBean.auditLogFlag}">

											<audit:auditTableSet id="RouteAuditId"
												value="#{RoutingDataBean.cmRoutingVO.auditKeyList}"
												numOfRecPerPage="10"></audit:auditTableSet>
										</m:div>

									</f:subview>
								</m:div>



								<m:br />
								<m:br />
							</m:div>
						</m:div>
					</m:div>
				</m:div>
			</m:div>
		</m:section>
	</f:subview>
</m:div>
