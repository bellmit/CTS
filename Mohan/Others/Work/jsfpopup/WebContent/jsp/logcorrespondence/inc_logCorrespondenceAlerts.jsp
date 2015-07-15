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
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CommonAlerts"
	var="alert" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<%--<t:saveState id="CMGTTOMSS220" value="#{AlertDataBean}" />
<t:saveState id="CMGTTOMSS221" value="#{AlertDataBean.listOfAlertVOs}" />
<t:saveState id="CMGTTOMSS222" value="#{AlertDataBean.refAlertTypeList}" />
<t:saveState id="CMGTTOMSS223" value="#{AlertDataBean.refAlertStatusList}" />
<t:saveState id="CMGTTOMSS224" value="#{AlertDataBean.listOfUsersToNotify}" />

--%>
<m:div>
	<f:subview id="logCrspdAlertsSubview">
		<m:section id="PROVIDERMMS20120731164811325" styleClass="otherbg">
			<m:legend>
				<h:outputLink
					onclick="setHiddenValue('logCorrespondence:logCrspdAlrtSubview:logCrspdAlertsSubview:alertsHidden','minus');toggle('log_div_alerts',2);							plusMinusToggle('log_div_alerts',this,'logCorrespondence:logCrspdAlrtSubview:logCrspdAlertsSubview:alertsHidden');							 return false;"
					id="plusMinusAlerts" styleClass="plus">

					<h:outputText id="CMGTOT25" value="#{alert['label.alert.alerts']}" />
					<h:inputHidden id="alertsHidden"
						value="#{CorrespondenceDataBean.alertsHidden}"></h:inputHidden>
				</h:outputLink>

			</m:legend>

			<h:inputHidden id="alertErr" value="ErrMsgHolder" />
			<h:message id="CMGTM1" for="alertErr" showDetail="true"
				styleClass="color: red" />
			<m:div sid="log_div_alerts">
				<m:div id="tb_alert">
					<m:table id="PROVIDERMMT20120731164811326" styleClass="tableBar" width="100%">
						<m:tr>
							<m:td styleClass="tableAction">
								<h:commandButton styleClass="formBtn" id="add"
									disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
									value="#{alert['label.alert.addalert']}"
									action="#{AlertControllerBean.addAlert}"
									onmousedown="javascript:flagWarn=false;focusThis(this.id);">
								</h:commandButton>

							</m:td>
						</m:tr>
					</m:table>
					<m:div>
						<h:graphicImage id="PROVIDERGI20120731164811327" styleClass="blankImage" width="1" height="5"
							alt="" url="/images/x.gif" />
						<m:anchor name="searchResultFocusforAlert"></m:anchor>
					</m:div>
					
					<t:dataTable id="alertDataTable" width="100%"
						styleClass="dataTable" rows="10" var="alertDetails"
						columnClasses="columnClass" headerClass="headerClass"
						footerClass="footerClass" rowClasses="row_alt,row"
						first="#{AlertDataBean.startIndexForAlert}"
						value="#{AlertDataBean.listOfAlertVOs}" rowIndexVar="rowIndex"
						rowOnClick="flagWarn=false;firstChild.lastChild.click();"
						onmousedown="javascript:flagWarn=false;focusThis(this.id);"
						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;">

						<h:column id="dueDateCol">
							<f:facet name="header">
								<h:panelGrid columns="2" id="alertTableID1">
									<h:outputLabel id="dueDateLabel" for="dueDateLabelValue"
										value="#{alert['label.alert.duedate']}" />
									<h:panelGroup id="dueDateLabelValue">
										<t:div styleClass="width x;align=left" id="alertTableID2">
											<t:commandLink id="dueDateAscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'dueDateAscCmdLink'}">
												<%-- 	<m:div title="#{alert['title.alert.forAscd']}"
													styleClass="sort_asc" />--%>
													
												<m:div title="for ascending" styleClass="sort_asc" />

												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Due Date" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Ascending" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="width x;align=left" id="alertTableID3">
											<t:commandLink id="dueDateDscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'dueDateDscCmdLink'}">
												<%--<m:div title="#{alert['title.alert.forDsnd']}"
													styleClass="sort_desc" />--%>

										
												<m:div title="for descending" styleClass="sort_desc" />

												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Due Date" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Descending" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<t:commandLink id="editAlert"
								action="#{AlertControllerBean.getAlertDetails}">
								<h:outputText id="valDueDateAlert"
									value="#{alertDetails.dueDate}" />
								<f:param name="indexCode" value="#{rowIndex}"></f:param>
							</t:commandLink>
						</h:column>
						<h:column id="alertTypeCol">
							<f:facet name="header">
								<h:panelGrid columns="2" id="alertTableID4">
									<h:outputLabel id="alertTypeLabel" for="alertTypeLabelValue"
										value="#{alert['label.alert.alerttype']}" />
									<h:panelGroup id="alertTypeLabelValue">
										<t:div styleClass="width x;align=left" id="alertTableID5">
											<t:commandLink id="alertTypeAscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'alertTypeAscCmdLink'}">

												<%--<m:div title="#{alert['title.alert.forAscd']}"
													styleClass="sort_asc" />--%>
												<m:div title="for ascending" styleClass="sort_asc" />


												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Alert Type" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Ascending" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="width x;align=left" id="alertTableID6">
											<t:commandLink id="alertTypeDscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'alertTypeDscCmdLink'}">
												<%--<m:div title="#{alert['title.alert.forDsnd']}"
													styleClass="sort_desc" />--%>
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Alert Type" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Descending" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valAlertType"
								value="#{alertDetails.alertTypeDesc}" />
						</h:column>
						<h:column id="alertDescCol">
							<f:facet name="header">
								<h:panelGrid columns="2" id="alertTableID7">
									<h:outputLabel id="alertDescLabel" for="alertDescLabelValue"
										value="#{alert['label.alert.description']}" />
									<h:panelGroup id="alertDescLabelValue">
										<t:div styleClass="width x;align=left" id="alertTableID8">
											<t:commandLink id="alertDescAscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'alertDescAscCmdLink'}">

												<%--<m:div title="#{alert['title.alert.forAscd']}"
													styleClass="sort_asc" />--%>

												<m:div title="for ascending" styleClass="sort_asc" />

												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Description" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Ascending" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="width x;align=left" id="alertTableID9">
											<t:commandLink id="alertDescDscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'alertDescDscCmdLink'}">
												<%--<m:div title="#{alert['title.alert.forDsnd']}"
													styleClass="sort_desc" /> --%>

												<m:div title="for descending" styleClass="sort_desc" />

												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Description" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Descending" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							
							<%-- Modified for defect ESPRD00720115--%>
							<h:outputText id="valAlertDesc" 
								value="#{alertDetails.description}" />
								
						</h:column>
						<h:column id="workUnitCol">
							<f:facet name="header">
								<h:panelGrid columns="2" id="alertTableID10">
									<h:outputLabel id="workUnitLabel" for="workUnitLabelValue"
										value="#{alert['label.alert.notifyviaalert']}" />
									<h:panelGroup id="workUnitLabelValue">
										<t:div styleClass="width x;align=left">
											<t:commandLink id="workUnitAscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'workUnitAscCmdLink'}">

												<%--<m:div title="#{alert['title.alert.forAscd']}"
													styleClass="sort_asc" />--%>

												<m:div title="for ascending" styleClass="sort_asc" />

												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Notify via Alert" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Ascending" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="width x;align=left" id="alertTableID11">
											<t:commandLink id="workUnitDscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'workUnitDscCmdLink'}">
												<%--<m:div title="#{alert['title.alert.forDsnd']}"
													styleClass="sort_desc" />--%>
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Notify via Alert" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Descending" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>


							<h:outputText id="valWorkUnit"
								value="#{alertDetails.userWorkUnitDesc}" />
							<%-- 
								Author 	: Infinite
								GAP		: 7209
								Date	: 08/01/2009 (dd/MM/yyyy)
							 --%>
							<h:outputText id="alertTableID12" value="-#{alertDetails.userId}"
								rendered="#{alertDetails.userId !=null && alertDetails.userId!=''}" />
						</h:column>
						<h:column id="alertStatusCol">
							<f:facet name="header">
								<h:panelGrid columns="2" id="alertTableID13">
									<h:outputLabel id="alertStatusLabel"
										for="alertStatusLabelValue"
										value="#{alert['label.alert.status']}" />
									<h:panelGroup id="alertStatusLabelValue">
										<t:div styleClass="width x;align=left" id="alertTableID14">
											<t:commandLink id="alertStatusAscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'alertStatusLabelValue'}">

												<%--<m:div title="#{alert['title.alert.forAscd']}"
													styleClass="sort_asc" />--%>

							                   <m:div title="for ascending" styleClass="sort_asc" />

												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Status" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Ascending" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="width x;align=left" id="alertTableID15">
											<t:commandLink id="alertStatusDscCmdLink"
												styleClass="text-decoration: none;"
												actionListener="#{AlertControllerBean.sort}"
												rendered="#{AlertDataBean.imageRender != 'alertStatusDscCmdLink'}">
												<%--<m:div title="#{alert['title.alert.forDsnd']}"
													styleClass="sort_desc" /> --%>

												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="#{alert['id.alert.columnName']}"
													value="Status" />
												<f:attribute name="#{alert['id.alert.sortOrder']}"
													value="Descending" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valAlertStatus"
								value="#{alertDetails.statusDesc}" />
						</h:column>
						<f:facet name="footer">
							<m:div id="nodata" align="center"
								rendered="#{AlertDataBean.renderNoData}">
								<h:outputText value="#{ref['label.ref.noData']}"
									id="alertTableID16" />
							</m:div>
						</f:facet>
					</t:dataTable>
					
					<%--Code modified for fixing the defect ESPRD00725516 --%>
					
					<t:dataScroller id="CMGTTOMDS30" pageCountVar="pageCount"
						pageIndexVar="pageIndex" onclick="flagWarn=false;focusThis('CMGTTOMDS30');"
						paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
						paginatorMaxPages="3" immediate="false" for="alertDataTable"
						firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
						rowsCountVar="rowsCount" style="float:right;position:relative">
						<f:facet name="previous">
							<h:outputText id="CMGTOT26"
								styleClass="text-decoration:underline;"
								value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
						</f:facet>
						<f:facet name="next">
							<h:outputText id="CMGTOT27"
								styleClass="text-decoration:underline;"
								value=" #{ref['label.ref.gt']} "
								rendered="#{pageIndex < pageCount}"></h:outputText>
						</f:facet>
						<h:outputText id="CMGTOT28"
							value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
							styleClass="float:left;position:relative;bottom:-6px;font-weight:bold;"
							rendered="#{rowsCount>0}">
						</h:outputText>
					</t:dataScroller>

					<m:br />
					<m:br />

					<m:div styleClass="moreInfo"
						rendered="#{AlertDataBean.renderAddAlert || AlertDataBean.renderEditAlert}">
						<m:div styleClass="moreInfoBar"
							rendered="#{AlertDataBean.renderAddAlert}">
							<m:div styleClass="infoTitle">
								<h:outputText id="CMGTOT29"
									value="#{alert['label.alert.newalert']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:panelGroup id="CMGTPGP1">
									<m:div>
										<t:commandLink styleClass="strong" id="alertCFsave"
											action="#{AlertControllerBean.saveAlert}"
											rendered="#{CorrespondenceDataBean.controlRequired}"
											onmousedown="javascript:flagWarn=false;focusThis(this.id);">
											<h:outputText id="CMGTOT30" value="#{ent['label-sw-save']}" />
										</t:commandLink>
										<h:outputText id="CMGTOT31" escape="false"
											value="#{ref['label.ref.linkSeperator']}"
											rendered="#{CorrespondenceDataBean.controlRequired}" />

										<t:commandLink id="CMGTCL4" styleClass="commandLink"
											action="#{AlertControllerBean.resetAlert}"
											rendered="#{CorrespondenceDataBean.controlRequired}"
											onmousedown="javascript:flagWarn=false;focusThis(this.id);">
											<h:outputText id="CMGTOT32" value="#{ent['label-sw-reset']}" />
										</t:commandLink>
										<h:outputText id="CMGTOT33" escape="false"
											value="#{ref['label.ref.linkSeperator']}"
											rendered="#{CorrespondenceDataBean.controlRequired}" />

										<%-- <h:commandLink id="CMGTCB3"
											action="#{AlertControllerBean.cancelAlert}"
											value="#{ent['label-sw-cancel']}"
											onmousedown="javascript:flagWarn=true;" />
										--%>
										<%--Modified for the Defect ID : ESPRD00778837 for reset link to work--%>
										<h:commandButton id="CMGTCB3"
											action="#{AlertControllerBean.cancelAlert}"
											value="#{ent['label-sw-cancel']}"
											onmousedown="javascript:flagWarn=true;" 
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:50px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:0px"
											
											/>
											
											<%--<h:commandLink id="CMGTCL5" styleClass="commandLink"										action="#{AlertControllerBean.cancelAlert}" onmousedown="doCommonClickCancelAction(this) javascript:flagWarn=false;">
										<h:outputText id="CMGTOT34" value="#{ent['label-sw-cancel']}" />
									</h:commandLink>--%>
									</m:div>
								</h:panelGroup>
							</m:div>
						</m:div>
						<m:div styleClass="moreInfoBar"
							rendered="#{AlertDataBean.renderEditAlert}">
							<m:div styleClass="infoTitle">
								<h:outputText id="CMGTOT35"
									value="#{alert['label.alert.editalert']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:panelGroup id="CMGTPGP2">
									<m:div>
										<t:commandLink id="CMGTCL6" styleClass="strong"
											rendered="#{(CorrespondenceDataBean.controlRequired) && (!CorrespondenceDataBean.crClosed)}"
											action="#{AlertControllerBean.updateAlert}"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="CMGTOT36" value="#{ent['label-sw-save']}" />
										</t:commandLink>
										<h:outputText id="CMGTOT37" value="#{ent['label-sw-save']}"
											rendered="#{CorrespondenceDataBean.controlRequired && CorrespondenceDataBean.crClosed}" />
										<h:outputText id="CMGTOT38" escape="false"
											value="#{ref['label.ref.linkSeperator']}"
											rendered="#{CorrespondenceDataBean.controlRequired}" />

										<t:commandLink id="CMGTCB4"
											action="#{AlertControllerBean.cancelAlert}"
											value="#{ent['label-sw-cancel']}" />

									</m:div>
									<%-- <h:commandLink id="CMGTCL7" styleClass="commandLink"										action="#{AlertControllerBean.cancelAlert}">
										<h:outputText id="CMGTOT39" value="#{ent['label-sw-cancel']}" />
									</h:commandLink> 
								--%>
								</h:panelGroup>
							</m:div>
						</m:div>

						<m:div styleClass="moreInfoContent">
							<m:div styleClass="padb">
								<m:div styleClass="msgBox"
									rendered="#{AlertDataBean.showSucessMessage}">
									<h:outputText id="CMGTOT40" value="#{ent['label-sw-success']}" />
								</m:div>
								<m:table id="PROVIDERMMT20120731164811328" cellspacing="0" width="94%">
									<m:tr rendered="#{AlertDataBean.renderAddAlert}">
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT41"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel for="dueDate" id="dueDtLabel"
													value="#{alert['label.alert.duedate']}" />
												<m:br />
												<m:inputCalendar id="dueDate" size="10"
													monthYearRowClass="yearMonthHeader"
													weekRowClass="weekHeader" maxlength="10"
													currentDayCellClass="currentDayCell"
													value="#{AlertDataBean.alertVO.dueDate}"
													renderAsPopup="true" addResources="true"
													popupDateFormat="MM/dd/yyyy"
													renderPopupButtonAsImage="true"
													onmouseover="javascript:flagWarn=false;"
													onmouseout="javascript:flagWarn=false;" />
												<%--<m:br />
												<h:message id="CMGTM2" for="dueDate" showDetail="true"													styleClass="color: red" />--%>
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<m:table id="PROVIDERMMT20120731164811329" cellspacing="1">
													<m:tr>
														<m:td>
															<m:span styleClass="required">
																<h:outputText id="CMGTOT42"
																	value="#{ref['label.ref.reqSymbol']}" />
															</m:span>
															<h:outputLabel id="alertTpLabel" for="alertType"
																value="#{alert['label.alert.alerttype']}" />
															<m:br />
															<h:selectOneMenu id="alertType"
																value="#{AlertDataBean.alertVO.alertType}">
																<f:selectItem itemValue=" "
																	itemLabel="#{alert['label.alert.plsSelOne']}" />
																<f:selectItems value="#{AlertDataBean.refAlertTypeList}" />
															</h:selectOneMenu>
														</m:td>
													</m:tr>
													<%--<m:tr><m:td>
												<h:message id="CMGTM3" for="alertType" showDetail="true"													styleClass="color: red" /></m:td></m:tr>--%>
												</m:table>
											</m:div>

										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT43"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="alertDescrLabel" for="alertDesc"
													value="#{alert['label.alert.description']}" />
												<m:br />
												
												<%--Code modified for fixing the defect ESPRD00725516 --%>
												
												<h:inputText id="alertDesc" value="#{AlertDataBean.alertVO.description}" />
												<%--<m:br />
												<h:message id="CMGTM4" for="alertDesc" showDetail="true"													styleClass="color: red" />--%>
											</m:div>
										</m:td>
										<%-- Commented for CR ESPRD00739015 - For Notify Alert IN ADD MODE 
										<m:td>
										
											<m:div styleClass="padb">
											
												<m:span styleClass="required">
													<h:outputText id="CMGTOT44"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												
												<h:outputLabel id="notifyAlertLabel" for="notifyAlertTo"
													value="#{alert['label.alert.notifyviaalert']}"
													style="height:14px" />
												<m:br />
												
												<h:selectOneMenu id="notifyAlertTo"
													value="#{AlertDataBean.alertVO.userWorkUnitSK}"
													style="height:20.8px;">
													<f:selectItem itemValue=" "
														itemLabel="#{alert['label.alert.plsSelOne']}" />
													<f:selectItems value="#{AlertDataBean.listOfUsersToNotify}" />
												</h:selectOneMenu>
												
												<m:br/>
												
												<h:message id="CMGTM5" for="notifyAlertTo" showDetail="true"													styleClass="color: red"/>
											
											</m:div>
											
										</m:td>--%>
										
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT45"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="alertStLabel" for="alertStatus"
													value="#{alert['label.alert.status']}" style="height:14px" />
												<m:br />
												<h:selectOneMenu id="alertStatus"
													value="#{AlertDataBean.alertVO.status}">
													<f:selectItems value="#{AlertDataBean.refAlertStatusList}" />
												</h:selectOneMenu>
												<%--<m:br />
												<h:message id="CMGTM6" for="alertStatus" showDetail="true"													styleClass="color: red" />--%>
											</m:div>
										</m:td>
									</m:tr>
									<%--defect --%>
									<m:tr rendered="#{AlertDataBean.renderAddAlert}">
										<m:td>
											<m:div styleClass="padb">

												<h:message id="CMGTM7" for="dueDate" showDetail="true"
													styleClass="color: red" />
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">

												<h:message id="CMGTM8" for="alertType" showDetail="true"
													styleClass="color: red" />
											</m:div>

										</m:td>
										<m:td>
											<m:div styleClass="padb">

												<h:message id="CMGTM9" for="alertDesc" showDetail="true"
													styleClass="color: red" />
											</m:div>
										</m:td>
										<%-- Commented for CR ESPRD00739015 - For Notify Alert IN ADD MODE 
										<m:td>
											<m:div styleClass="padb">

												<h:message id="CMGTM10" for="notifyAlertTo"
													showDetail="true" styleClass="color: red" />
											</m:div>
										</m:td>--%>
										<m:td>
											<m:div styleClass="padb">

												<h:message id="CMGTM11" for="alertStatus" showDetail="true"
													styleClass="color: red" />
											</m:div>
										</m:td>
									</m:tr>
									<%-- Added for CR ESPRD00739015 - For Notify Alert IN ADD MODE ---%>
									<m:tr rendered="#{AlertDataBean.renderAddAlert}">
										<m:td colspan="3">
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT44"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="notifyAlertLabel" for="notifyAlertTo"
													value="#{alert['label.alert.notifyviaalert']}"
													style="height:14px" />
												<m:br />
												<h:selectOneMenu id="notifyAlertTo"
													value="#{AlertDataBean.alertVO.userWorkUnitSK}"
													style="height:20.8px;">
													<f:selectItem itemValue=" "
														itemLabel="#{alert['label.alert.plsSelOne']}" />
													<f:selectItems value="#{AlertDataBean.listOfUsersToNotify}" />
												</h:selectOneMenu>
											</m:div>
										</m:td>
									</m:tr>
									
									<m:tr rendered="#{AlertDataBean.renderAddAlert}">
										<m:td>
											<m:div styleClass="padb">
												<h:message id="CMGTM10" for="notifyAlertTo"
													showDetail="true" styleClass="color: red" />
											</m:div>
										</m:td>
									</m:tr>
									<%-- END for CR ESPRD00739015 - For Notify Alert IN ADD MODE ---%>
									<%--EOF raja --%>
									<m:tr rendered="#{AlertDataBean.renderEditAlert}">
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT46"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="dueDtLabelEdit" for="dueDateEdit"
													value="#{alert['label.alert.duedate']}" />
												<m:br />
												<h:outputText id="dueDateEdit"
													value="#{AlertDataBean.alertVO.dueDate}" />
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT47"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="alertTpLabelEdit" for="alertTypeEdit"
													value="#{alert['label.alert.alerttype']}" />
												<m:br />
												<h:outputText id="alertTypeEdit"
													value="#{AlertDataBean.alertVO.alertTypeDesc}" />

											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT48"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="alertDescLabelEdit" for="alertDescEdit"
													value="#{alert['label.alert.description']}" />
												<m:br />
												<h:outputText id="alertDescEdit"
													value="#{AlertDataBean.alertVO.description}" />
											</m:div>
										</m:td>
										<%-- Commented for CR ESPRD00739015 - For Notify Alert IN UPDATE/EDIT MODE 
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT49"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="notifyAlertLabelEdit"
													for="notifyAlertToEdit"
													value="#{alert['label.alert.notifyviaalert']}" />
												<m:br />
												<h:outputText id="notifyAlertToEdit"
													value="#{AlertDataBean.alertVO.userWorkUnitDesc}" />
												<!-- 
													Author 	: Infinite
													GAP		: 7209
													Date	: 08/01/2009 (dd/MM/yyyy)
												 -->
												<h:outputText id="CMGTOT50"
													value="-#{AlertDataBean.alertVO.userId}"
													rendered="#{AlertDataBean.alertVO.userId !=null && AlertDataBean.alertVO.userId!=''}" />
											</m:div>
										</m:td>--%>
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT51"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="alertStLabelEdit" for="alertStatusEdit"
													value="#{alert['label.alert.status']}" />
												<m:br />
												<h:selectOneMenu id="alertStatusEdit"
													disabled="#{AlertDataBean.statusProtected || !AlertDataBean.rowEnabled || !CorrespondenceDataBean.controlRequired}"
													value="#{AlertDataBean.alertVO.status}">
													<f:selectItems value="#{AlertDataBean.refAlertStatusList}" />
												</h:selectOneMenu>
											</m:div>
										</m:td>
									</m:tr>

							<%-- Added for CR ESPRD00739015 -START - For Notify Alert IN UPDATE/EDIT MODE --%>
									<%-- Commented for DEFECT ESPRD00772108 - For Notify Alert --%>
									<%--<m:tr rendered="#{AlertDataBean.renderEditAlert}">
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT49"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="notifyAlertLabelEdit"
													for="notifyAlertToEdit"
													value="#{alert['label.alert.notifyviaalert']}" />
												<m:br />
												<h:outputText id="notifyAlertToEdit"
													value="#{AlertDataBean.alertVO.userWorkUnitDesc}" />
												<!-- 
													Author 	: Infinite
													GAP		: 7209
													Date	: 08/01/2009 (dd/MM/yyyy)
												 -->
												<h:outputText id="CMGTOT50"
													value="-#{AlertDataBean.alertVO.userId}"
													rendered="#{AlertDataBean.alertVO.userId !=null && AlertDataBean.alertVO.userId!=''}" />
											</m:div>
										</m:td>
										</m:tr>--%>
									<%-- Added for the Defect ESPRD00772108 -For Notify Alert IN UPDATE/EDIT MODE - STARTED --%>	
									<m:tr rendered="#{AlertDataBean.renderEditAlert}">
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="CMGTOT49"
														value="#{ref['label.ref.reqSymbol']}" />
												</m:span>
												<h:outputLabel id="notifyAlertLabelEdit" for="notifyAlertToEdit"
													value="#{alert['label.alert.notifyviaalert']}"
													style="height:14px" />
												<m:br />
												<h:selectOneMenu id="notifyAlertToEdit"
													value="#{AlertDataBean.alertVO.userWorkUnitSK}"
													style="height:20.8px;">
													<f:selectItem itemValue=" "
														itemLabel="#{alert['label.alert.plsSelOne']}" />
													<f:selectItems value="#{AlertDataBean.listOfUsersToNotify}" />
												</h:selectOneMenu>
											</m:div>
										</m:td>
									</m:tr>
									<%-- Added for the Defect ESPRD00772108 -For Notify Alert IN UPDATE/EDIT MODE - ENDED --%>	
										<%-- END for CR ESPRD00739015 - For Notify Alert IN UPDATE/EDIT MODE --%>
								</m:table>
								<%-- Jsp for Audit Log --%>


								<m:div rendered="#{not empty AlertDataBean.alertVO.alertSK}">
									<f:subview id="alertAuditLogSubview">
										<%--jsp:include
						                    	page="/jsp/logcorrespondence/inc_CRAlertAuditLog.jsp" /---%>
										<m:div rendered="#{CorrespondenceDataBean.auditLogFlag}">

											<audit:auditTableSet id="alertAuditId"
												value="#{AlertDataBean.alertVO.auditKeyList}"
												numOfRecPerPage="10"></audit:auditTableSet>
										</m:div>

									</f:subview>
								</m:div>

								


							</m:div>
						</m:div>
					</m:div>

				</m:div>
			</m:div>
		</m:section>
	</f:subview>
</m:div>
