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


<%-- Holds the name of the property file to be reffered --%>
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonEAddress"
	var="cmnEAddMsg" />

<m:div>
	<f:subview id="maintainEntEAddressSubView">
		<m:section id="PROVIDERMMS20120731164811154" styleClass="otherbg">
			<m:legend>
				<h:outputLink
					onclick="setEntityHiddenValue('frm:updateAdmnsEadd:maintainEntEAddressSubView:eaddressHidden','minus');entityToggle('frm:updateAdmnsEadd:maintainEntEAddressSubView:maintainE_common_Eaddress',2);							entityPlusMinusToggle('frm:updateAdmnsEadd:maintainEntEAddressSubView:maintainE_common_Eaddress',this,'frm:updateAdmnsEadd:maintainEntEAddressSubView:eaddressHidden');return false;"
					id="plusMinusEaddress" styleClass="plus" value="#">

					<h:outputText id="PRGCMGTOT303"
						value="#{cmnEAddMsg['label.eAddress.eaddress']}" />
					<h:inputHidden id="eaddressHidden"
						value="#{CMEntityMaintainDataBean.eaddressHidden}"></h:inputHidden>
				</h:outputLink>

			</m:legend>
			<m:div id="maintainE_common_Eaddress">
				<m:div id="showhide_eaddress">

					<m:table id="PROVIDERMMT20120731164811155" width="100%">
						<m:tr>
							<m:td>
								<m:div id="message_eaddress">
								</m:div>
							</m:td>
						</m:tr>
					</m:table>
					<m:div rendered="#{commonEntityDataBean.eaddrSaveMsg}"
						styleClass="msgbox">
						<h:outputText id="PRGCMGTOT304"
							value="#{cmnEAddMsg['display.save.message']}"
							rendered="#{commonEntityDataBean.eaddrSaveMsg}"></h:outputText>
					</m:div>

					<m:table id="PROVIDERMMT20120731164811156" styleClass="tableBar" width="100%">
						<m:tr>
							<m:td styleClass="tableAction">
								<m:div styleClass="rightCell">
									<%-- Added Disabled Attribute--%>
									<%-- Fix for Defect ID :ESPRD00715890--%>
									<h:commandButton id="PRGCMGTCB10" styleClass="formBtn"
										disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
										value="#{cmnEAddMsg['label.eAddress.addEAddressRecord']}"
										action="#{eAddressControllerBean.addEAddress}"
										id="cursorFocusId"
										onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"></h:commandButton>
								</m:div>
							</m:td>
						</m:tr>
					</m:table>

					<t:dataTable border="0" styleClass="dataTable" width="100%"
						cellspacing="0" rows="10"
						first="#{commonEntityDataBean.startIndexForEAdrs}"
						value="#{commonEntityDataBean.commonEntityVO.eaddressVOList}"
						var="eaddressList" id="eaddressHistory" rowIndexVar="rowIndex"
						columnClasses="columnClass" headerClass="headerClass"
						footerClass="footerClass" rowClasses="row_alt,row"
						rowOnClick="firstChild.lastChild.click();"
						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
						onmousedown="javascript:flagWarn=false;">

						<t:column id="Element1" styleClass="otherbg">


							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD43" columns="2">
									<h:outputLabel for="eAddState" id="stat"
										value="#{cmnEAddMsg['label.eAddress.status']}" />


									<h:panelGroup id="eAddState">

										<t:div styleClass="widthXLeft">
											<t:commandLink id="PRGCMGTCL53" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="Status" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="PRGCMGTCL54" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="Status" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<t:commandLink id="PRGCMGTCL55"
								style="COLOR:black;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);text-decoration:none;vertical-align: bottom;word-spacing: normal"
								action="#{eAddressControllerBean.editEAddress}"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="statCd" value="#{eaddressList.statusStr}" />
								<f:param name="indexCode" value="#{rowIndex}"></f:param>
							</t:commandLink>

						</t:column>
						<t:column id="Element7" styleClass="otherbg">

							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD44" columns="2">
									<h:outputLabel for="CEaddTyp" id="usg"
										value="#{cmnEAddMsg['label.eAddress.eAddressType']}" />


									<h:panelGroup id="CEaddTyp">

										<t:div styleClass="widthXLeft">
											<t:commandLink id="PRGCMGTCL56" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="E-Address Type" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="PRGCMGTCL57" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="E-Address Type" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText
								style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);text-decoration:underline;vertical-align: bottom;word-spacing: normal"
								id="usgTyp" value="#{eaddressList.usageTypeCodeStr}" />

						</t:column>

						<t:column id="Element2" styleClass="otherbg">

							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD45" columns="2">
									<h:outputLabel for="CEaddBDT" id="begDate"
										value="#{cmnEAddMsg['label.eAddress.beginDate']}" />


									<h:panelGroup id="CEaddBDT">

										<t:div styleClass="widthXLeft">
											<t:commandLink id="PRGCMGTCL58" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="Begin Date" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="PRGCMGTCL59" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="Begin Date" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT305"
								value="#{eaddressList.beginDateStr}" />
						</t:column>
						<t:column id="Element3" styleClass="otherbg">

							<f:facet name="header">
								<h:panelGrid id="CMGTPGD1" columns="2">
									<h:outputLabel for="CEaddEdT" id="endDate"
										value="#{cmnEAddMsg['label.eAddress.endDate']}" />
									<h:panelGroup id="CEaddEdT">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="enddateasc" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender != 'enddateasc'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="End Date" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="enddatedesc" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender != 'enddatedesc'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="End Date" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT306"
								value="#{eaddressList.endDateStr}" />
						</t:column>
						<t:column id="Element4" styleClass="otherbg">
							<f:facet name="header">
								<h:panelGrid id="CMGTPGD2" columns="2">
									<h:outputLabel for="CEaddA" id="eadd"
										value="#{cmnEAddMsg['label.eAddress.eAddress']}" />
									<h:panelGroup id="CEaddA">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="eaddressasc" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender != 'eaddressasc'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="E-Address" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="eaddressdesc" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender != 'eaddressdesc'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="E-Address" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valueadd" value="#{eaddressList.eaddress}" />
						</t:column>
						<t:column id="Element5" styleClass="otherbg">
							<f:facet name="header">
								<h:panelGrid id="CMGTPGD3" columns="2">
									<h:outputLabel for="CEaddBounceAD" id="baddress"
										value="#{cmnEAddMsg['label.eAddress.bouncedAddress']}" />
									<h:panelGroup id="CEaddBounceAD">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="baddrasc" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender != 'baddrasc'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="Bounced Address" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="baddrdesc" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{eAddressControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender != 'baddrdesc'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="Bounced Address" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT307"
								value="#{eaddressList.bouncedAddressStr}" />
						</t:column>
						<f:facet name="footer">
							<m:div id="nodata" align="center">
								<h:outputText id="PRGCMGTOT308"
									value="#{cmnEAddMsg['label.eAddress.noData']}"
									rendered="#{empty commonEntityDataBean.commonEntityVO.eaddressVOList}"></h:outputText>
							</m:div>
						</f:facet>
					</t:dataTable>

					<m:div styleClass="searchResults" id="pagenation">
						<t:dataScroller id="PROVIDERMDS20120731164811157" pageCountVar="pageCount" pageIndexVar="pageIndex"
							paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
							paginatorMaxPages="3" immediate="false" for="eaddressHistory"
							firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
							rowsCountVar="rowsCount" styleClass="dataScroller">
							<f:facet name="previous">
								<h:outputText id="CMGTOT3" styleClass="underline"
									value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
							</f:facet>
							<f:facet name="next">
								<h:outputText id="CMGTOT4" styleClass="underline"
									value=" #{ref['label.ref.gt']} "
									rendered="#{pageIndex < pageCount}"></h:outputText>
							</f:facet>
							<%-- <h:outputText id="CMGTOT5"								value="showing #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"								styleClass="dataScrollerText" rendered="#{rowsCount>0}">
							</h:outputText> --%>
							<h:outputText id="CMGTOT6"
								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								styleClass="dataScrollerText" rendered="#{rowsCount>0}">
							</h:outputText>
						</t:dataScroller>
						<m:div>
							<m:br></m:br>
						</m:div>
					</m:div>
					<m:br></m:br>
					<m:br></m:br>
					<m:div>
						<h:graphicImage id="PROVIDERGI20120731164811158" alt="" url="/images/x.gif" width="1" height="10"
							styleClass="blankImage" />
					</m:div>
					<m:div id="newEmailDisp" styleClass="moreInfo"
						rendered="#{commonEntityDataBean.eaddressRendered}">
						<m:div styleClass="moreInfoBar">
							<m:table id="PROVIDERMMT20120731164811159" styleClass="tableBar" width="100%">
								<m:div id="actionsDiv1" styleClass="actions">
									<m:div styleClass="infoTitle">
										<h:outputText id="PRGCMGTOT312"
											value="#{commonEntityDataBean.editEAddress? cmnEAddMsg['label.eAddress.editEAddressRecord']:cmnEAddMsg['label.eAddress.addEAddressRecord']}" />
									</m:div>
									
									
										<%--<h:commandLink id="CMGTCL2" styleClass="strong"											action="#{eAddressControllerBean.saveEAddress}" rendered="#{eAddressControllerBean.controlRequired}"											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="CMGTOT7" value="#{cmnEAddMsg['label.eAddress.save']}">
											</h:outputText>
										</h:commandLink>--%>
										
										<%--Fix for Defect Id :ESPRD00715890...start --%>
										<m:div styleClass="infoActions">
										<h:inputHidden id="commonEAddressSaved"
											value="#{commonEntityDataBean.commonEAddressSaved}"></h:inputHidden>
									    </m:div>
									    
									    <m:div styleClass="infoActions">
										<h:commandButton id="eaddressCancel"
											action="#{eAddressControllerBean.cancelEAddress}"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:43px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal; position:relative;top:0.3px"
											value="#{cmnEAddMsg['label.eAddress.cancel']}"></h:commandButton>
										</m:div>
										
										<m:div styleClass="infoActions">
										<h:outputText id="CMGTOT10" escape="false"
											value="#{ref['label.ref.linkSeperator']}" />
										</m:div>
										
										
										
										<m:div styleClass="infoActions">
										<h:commandButton id="CMGTCB2"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
											onmousedown="javascript:flagWarn=false;"
											disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
											value="#{cmnEAddMsg['label.eAddress.reset']}"
											action="#{eAddressControllerBean.resetEAddress}" />
										</m:div>
										
										<m:div styleClass="infoActions">
										<h:outputText id="CMGTOT8" escape="false"
											value="#{ref['label.ref.linkSeperator']}" />
										</m:div>
										
										<m:div styleClass="infoActions">
										<h:commandButton id="CMGTCB1" styleClass="strong"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
											onmousedown="javascript:flagWarn=false;"
											disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
											value="#{cmnEAddMsg['label.eAddress.save']}"
											action="#{eAddressControllerBean.saveEAddress}" />
										</m:div>
										
										
										
										<%--<h:commandLink id="CMGTCL3"											action="#{eAddressControllerBean.resetEAddress}" rendered="#{eAddressControllerBean.controlRequired}"											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="CMGTOT9" value="#{cmnEAddMsg['label.eAddress.reset']}">
											</h:outputText>
										</h:commandLink>--%>
										   
									</m:div>
									<%--Fix for Defect Id :ESPRD00715890...end --%>
							</m:table>
						</m:div>

						<m:div styleClass="moreInfoContent">
							<m:table id="PROVIDERMMT20120731164811160" styleClass="tableBar" width="95%">
								<m:tr>
									<%--
								<h:messages showDetail="true" id="cmnNoteserrMsg1"									showSummary="false" style="color: red"									rendered="#{commonEntityDataBean.eaddressSaved}" />
							--%>
								</m:tr>
							</m:table>
							<m:table id="PROVIDERMMT20120731164811161" cellspacing="0" width="99%">
								<m:tr>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="CMGTOT11"													value="#{cmnEAddMsg['label.eAddress.required']}" />
											</m:span>
											<h:outputLabel id="PRGCMGTOLL161" for="eaddrtycode"
												value="#{cmnEAddMsg['label.eAddress.eAddressTypeCode']}" />
											<m:br></m:br>
											<%--There were 2 disabled attributes earlier.One disabled attribute is removed for the defect ESPRD00802214--%>
											<h:selectOneMenu id="eaddrtycode"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.eaddressVO.usageTypeCode}">
												<f:selectItems value="#{commonEntityDataBean.usageList}" />
											</h:selectOneMenu>
											<m:br />
											<h:message id="PRGCMGTM67" for="eaddrtycode"
												showDetail="true" styleClass="errorMessage" />
											<m:br />
										</m:div>
									</m:td>
									<m:td>
										<m:div styleClass="padb">
											<f:verbatim>
												<h:outputText id="CMGTOT12" escape="false"
													value="&nbsp;&nbsp;" />
											</f:verbatim>
										</m:div>
									</m:td>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT318"
													value="#{cmnEAddMsg['label.eAddress.required']}"></h:outputText>
											</m:span>
											<h:outputLabel for="bdate" id="beginDate1"
												value="#{cmnEAddMsg['label.eAddress.beginDate']}">
											</h:outputLabel>
											<m:br></m:br>
											<m:inputCalendar id="bdate" size="10"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												monthYearRowClass="yearMonthHeader"
												weekRowClass="weekHeader"
												currentDayCellClass="currentDayCell"
												value="#{commonEntityDataBean.commonEntityVO.eaddressVO.beginDate}"
												renderAsPopup="true" addResources="true" readonly="false"
												popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
												onmouseover="javascript:flagWarn=false;"
												onmouseout="javascript:flagWarn=false;" />

											<m:br />
											<h:message id="PRGCMGTM68" for="bdate"
												styleClass="errorMessage"></h:message>
											<m:br />
										</m:div>
									</m:td>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT319"
													value="#{cmnEAddMsg['label.eAddress.required']}"></h:outputText>
											</m:span>
											<h:outputLabel for="edate" id="enddate"
												value="#{cmnEAddMsg['label.eAddress.endDate']}">
											</h:outputLabel>
											<m:br></m:br>
											<m:inputCalendar id="edate" size="10"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												monthYearRowClass="yearMonthHeader"
												weekRowClass="weekHeader"
												currentDayCellClass="currentDayCell"
												value="#{commonEntityDataBean.commonEntityVO.eaddressVO.endDate}"
												renderAsPopup="true" addResources="true" readonly="false"
												popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
												onmouseover="javascript:flagWarn=false;"
												onmouseout="javascript:flagWarn=false;" />
											<m:br />
											<h:message id="PRGCMGTM69" for="edate" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:div>
									</m:td>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT320"
													value="#{cmnEAddMsg['label.eAddress.required']}"></h:outputText>
											</m:span>
											<h:outputLabel id="PRGCMGTOLL162" for="sgnfcnce"
												value="#{cmnEAddMsg['label.eAddress.significanceType']}" />
											<m:br></m:br>
											<h:selectOneMenu id="sgnfcnce"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.eaddressVO.significance}">
												<f:selectItems value="#{commonEntityDataBean.sigList}" />
											</h:selectOneMenu>
											<m:br />
											<h:message id="PRGCMGTM70" for="sgnfcnce" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:div>
									</m:td>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT321"
													value="#{cmnEAddMsg['label.eAddress.required']}"></h:outputText>
											</m:span>
											<h:outputLabel id="PRGCMGTOLL163" for="stattype"
												value="#{cmnEAddMsg['label.eAddress.statusCode']}" />
											<m:br></m:br>
											<h:selectOneMenu id="stattype"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.eaddressVO.status}">
												<f:selectItems
													value="#{commonEntityDataBean.statusEaddList}" />
											</h:selectOneMenu>
											<m:br />
											<h:message id="PRGCMGTM71" for="stattype" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:div>
									</m:td>
								</m:tr>
							</m:table>

							<m:table id="PROVIDERMMT20120731164811162">
								<m:tr>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT322"
													value="#{cmnEAddMsg['label.eAddress.required']}" />
											</m:span>
											<h:outputLabel id="PRGCMGTOLL164" for="inputEadd"
												value="#{cmnEAddMsg['label.eAddress.eAddress']}" />
											<m:br></m:br>
											<%--There were 2 disabled attributes earlier.One disabled attribute is removed for the defect ESPRD00802214--%>
											<h:inputText size="25" maxlength="64" id="inputEadd"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.eaddressVO.eaddress}"/>
											<m:br />
											<h:message id="PRGCMGTM72" for="inputEadd" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:div>
									</m:td>
									<%--<m:td>
									<m:div styleClass="padb">
											<h:outputLabel id="CMGTOLL1" for="outsrvc" 	value="#{cmnEAddMsg['label.eAddress.bouncedAddress']}" />
		                                      <h:selectOneRadio id="outsrvc" value="#{commonEntityDataBean.commonEntityVO.eaddressVO.bouncedAddress}">
							                       <f:selectItem itemLabel="#{cmnEAddMsg['label.yes']}" itemValue="#{true}" />
						                           <f:selectItem itemLabel="#{cmnEAddMsg['label.no']}" itemValue="#{false}" />
					                          </h:selectOneRadio>
											
									</m:div> 
									</m:td>
								--%>
									<%--Start --%>
									<m:td>
										<m:div styleClass="padb">
											<f:verbatim>
												<h:outputText id="CMGTOT13" escape="false"
													value="&nbsp;&nbsp;" />
											</f:verbatim>
										</m:div>
									</m:td>

									<m:td>
										<m:div styleClass="padb">
											<h:outputLabel id="CMGTOLL2" for="outsrvc"
												value="#{cmnEAddMsg['label.eAddress.bouncedAddress']}">
											</h:outputLabel>
										</m:div>
									</m:td>
									<m:td>
										<m:div styleClass="padb">

											<h:selectOneRadio id="outsrvc"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.eaddressVO.bouncedAddress}">
												<f:selectItem itemLabel="#{cmnEAddMsg['label.yes']}"
													itemValue="#{true}" />
												<f:selectItem itemLabel="#{cmnEAddMsg['label.no']}"
													itemValue="#{false}" />
											</h:selectOneRadio>


										</m:div>
									</m:td>



									<%--END --%>
								</m:tr>
							</m:table>
							<m:div
								rendered="#{not empty commonEntityDataBean.commonEntityVO.eaddressVO.eaddressSK}">
								<f:subview id="EAddressAuditInc">
									<%--<jsp:include
										page="/jsp/commonEntities/inc_EAddressAuditLogCnI.jsp" /--%>
									<m:div rendered="#{CMEntityMaintainDataBean.auditLogFlag}">

										<audit:auditTableSet id="eaddressAuditId"
											value="#{commonEntityDataBean.commonEntityVO.eaddressVO.auditKeyList}"
											numOfRecPerPage="10"></audit:auditTableSet>
									</m:div>
								</f:subview>
							</m:div>
						</m:div>
					</m:div>
				</m:div>
			</m:div>
		</m:section>
	</f:subview>
</m:div>
<t:saveState id="CMGTTOMSS62"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.eaddressSK}" />
<t:saveState id="CMGTTOMSS63"
	value="#{commonEntityDataBean.eaddressRendered}" />
<t:saveState id="CMGTTOMSS64"
	value="#{commonEntityDataBean.eaddressSaved}" />
<t:saveState id="CMGTTOMSS65"
	value="#{commonEntityDataBean.viewingSaved}" />
<t:saveState id="CMGTTOMSS66"
	value="#{commonEntityDataBean.editEAddress}" />
<t:saveState id="CMGTTOMSS67"
	value="#{commonEntityDataBean.savedEndDateGreater}" />
<t:saveState id="CMGTTOMSS68"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.beginDate}" />
<t:saveState id="CMGTTOMSS69"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.endDate}" />
<t:saveState id="CMGTTOMSS70"
	value="#{commonEntityDataBean.eaddressVOIndex}" />
<t:saveState id="CMGTTOMSS71"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVOList}" />
<t:saveState id="CMGTTOMSS72"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.usageList}" />
<t:saveState id="CMGTTOMSS73"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.significanceList}" />
<t:saveState id="CMGTTOMSS74"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.statusList}" />
<t:saveState id="CMGTTOMSS75"
	value="#{commonEntityDataBean.commonEntityVO.commonEntitySK}" />

<t:saveState id="CMGTTOMSS76"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.usageTypeCode}" />
<t:saveState id="CMGTTOMSS77"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.bouncedAddress}" />
<t:saveState id="CMGTTOMSS78"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.significance}" />
<t:saveState id="CMGTTOMSS79"
	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.eaddress}" />
<t:saveState id="CMGTTOMSS80"
	value="#{commonEntityDataBean.disableEAddress}" />
