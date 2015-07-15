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
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonContact"
	var="cmnContactMsg" />

<m:div>
	<f:subview id="maintainEntComContSubView">
		<m:section id="PROVIDERMMS20120731164811150" styleClass="otherbg">
			<m:legend>
				<h:outputLink
					onclick="setEntityHiddenValue('frm:updateAdmnsContact:maintainEntComContSubView:contactHidden','minus');entityToggle('frm:updateAdmnsContact:maintainEntComContSubView:maintainE_common_contact',2);							entityPlusMinusToggle('frm:updateAdmnsContact:maintainEntComContSubView:maintainE_common_contact',this,'frm:updateAdmnsContact:maintainEntComContSubView:contactHidden');return false;"
					id="plusMinusContact" styleClass="plus" value="#">
					<h:outputText id="PRGCMGTOT281"
						value="#{cmnContactMsg['label.commonContact.Contact']}" />
					<h:inputHidden id="contactHidden"
						value="#{CMEntityMaintainDataBean.contactHidden}"></h:inputHidden>
				</h:outputLink>

			</m:legend>
			<m:div id="maintainE_common_contact">

				<m:div>
					<m:table id="PROVIDERMMT20120731164811151" width="100%">
						<m:tr>
							<m:td>
							</m:td>
						</m:tr>
					</m:table>
					<m:div rendered="#{commonEntityDataBean.contactSaveMsg}"
						styleClass="msgbox">
						<h:outputText id="PRGCMGTOT282"
							value="#{cmnContactMsg['info.Contact_Save_Succ']}"></h:outputText>
					</m:div>
					<m:table id="PROVIDERMMT20120731164811152" styleClass="tableBar" width="100%">
						<m:tr>
							<m:td styleClass="tableTitle">
							</m:td>
							<m:td styleClass="tableAction">
								<m:div styleClass="rightCell">
									<%--Added Disabled attribute  --%>
									<h:commandButton id="PRGCMGTCB7" styleClass="formBtn"
										disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
										action="#{commonContactControllerBean.addContact}"
										onclick="javascript:flagWarn=false;"
										value="#{cmnContactMsg['label.commonContact.addContact']}"></h:commandButton>
								</m:div>
							</m:td>
						</m:tr>
					</m:table>
					<t:dataTable cellspacing="0" styleClass="dataTable" width="100%"
						rows="10" first="#{commonEntityDataBean.contactstartindex}"
						value="#{commonEntityDataBean.commonEntityVO.contactVOList}"
						var="contact" id="contactHist" rowIndexVar="rowIndex"
						columnClasses="columnClass" headerClass="headerClass"
						footerClass="footerClass" rowClasses="row_alt,row"
						rowOnClick="firstChild.lastChild.click();"
						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
						onmousedown="javascript:flagWarn=false">
						<t:column id="conatctHistClm7" styleClass="otherbg">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD35" columns="2">
									<h:outputLabel id="PRGCMGTOLL142"
										value="#{cmnContactMsg['label.commonContact.status']}" />
									<h:panelGroup id="contpanel14">
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd17"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}"
												rendered="#{commonEntityDataBean.imageRender !='cmd17'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Status" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd18"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}"
												rendered="#{commonEntityDataBean.imageRender !='cmd18'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Status" />
												<f:attribute name="sortOrder" value="desc" />
												</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>

							<%-- <h:commandLink id="cmd7"								action="#{commonContactControllerBean.displayDetailedContactInfo}"								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="cmdcontType" value="#{contact.contactType}" />
								<f:param name="indexCode" value="#{rowIndex}"></f:param>
							</h:commandLink> --%>
							<t:commandLink id="CMGTCL1"
								style="COLOR:black;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);text-decoration:none;vertical-align: bottom;word-spacing: normal"
								action="#{commonContactControllerBean.displayDetailedContactInfo}"
								onmousedown="javascript:flagWarn=false">
								<h:outputText id="CMGTOT2" value="#{contact.statusDesc}" />
								<f:param name="indexCode" value="#{rowIndex}"></f:param>
							</t:commandLink>
						</t:column>
						<t:column id="conatctHistClm6" styleClass="otherbg">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD36" columns="2">
									<h:outputLabel id="PRGCMGTOLL143"
										value="#{cmnContactMsg['label.commonContact.contactType']}" />
									<h:panelGroup id="contpanel13">
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd15"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}"
												rendered="#{commonEntityDataBean.imageRender !='cmd15'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Contact Type" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd16"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}"
												rendered="#{commonEntityDataBean.imageRender !='cmd16'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="Contact Type" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>

							<h:outputText id="PRGCMGTOT283"
								style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);text-decoration:underline;vertical-align: bottom;word-spacing: normal"
								value="#{contact.contactTypeDesc}" />

						</t:column>
						<t:column id="conatctHistClm0" styleClass="otherbg">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD37" columns="2">
									<h:outputLabel id="PRGCMGTOLL144"
										value="#{cmnContactMsg['label.commonContact.beginDate']}" />
									<h:panelGroup id="contpanel11">
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd11"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												
												<m:div title="for ascending" styleClass="sort_asc" />
													<f:attribute name="columnName"
													value="Begin Date" />
												<f:attribute name="sortOrder" value="asc" />
												
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd12"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												
												<m:div title="for descending" styleClass="sort_desc" />
												
												<f:attribute name="columnName"
													value="Begin Date" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT284" value="#{contact.beginDate}" />
						</t:column>
						<t:column id="conatctHistClm5" styleClass="otherbg">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD38" columns="2">
									<h:outputLabel id="PRGCMGTOLL145"
										value="#{cmnContactMsg['label.commonContact.endDate']}" />
									<h:panelGroup id="contpanel12">
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd13"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for ascending" styleClass="sort_asc" />
												
												<f:attribute name="columnName"
													value="End Date" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd14"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="End Date" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT285" value="#{contact.endDate}" />
						</t:column>
						<t:column id="conatctHistClm1" styleClass="otherbg">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD39" columns="2">
									<h:outputLabel id="PRGCMGTOLL146" for="fstname">
										<h:outputText id="fstname"
											value="#{cmnContactMsg['label.commonContact.firstName']}"></h:outputText>
									</h:outputLabel>

									<h:panelGroup id="contactHistpanel">
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd1"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="First Name" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd2"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="First Name" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="firstNm" value="#{contact.nameVO.firstName}" />
						</t:column>
						<t:column id="conatctHistClm2" styleClass="otherbg">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD40" columns="2">

									<h:outputLabel id="PRGCMGTOLL147" for="mdname">
										<h:outputText id="mdname"
											value="#{cmnContactMsg['label.commonContact.middleName']}"></h:outputText>
									</h:outputLabel>

									<h:panelGroup id="panel1">
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd4"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="Middle Name" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd5"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="Middle Name" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT286"
								value="#{contact.nameVO.middleName}" />
						</t:column>
						<t:column id="conatctHistClm3" styleClass="otherbg">

							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD41" columns="2">
									<h:outputLabel id="PRGCMGTOLL148" for="lname">
										<h:outputText id="lname"
											value="#{cmnContactMsg['label.commonContact.lastName']}"></h:outputText>
									</h:outputLabel>

									<h:panelGroup id="panel2">
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cnd"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="Last Name" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd6"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="Last Name" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valuelastName"
								value="#{contact.nameVO.lastName}" />
						</t:column>
						<t:column id="conatctHistClm4" styleClass="otherbg">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD42" columns="2">
									<h:outputLabel id="PRGCMGTOLL149"
										value="#{cmnContactMsg['label.commonContact.suffix']}" />


									<h:panelGroup id="PRGCMGTPGP35">

										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="aa"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for ascending" styleClass="sort_asc" />

												<f:attribute name="columnName"
													value="Suffix" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink styleClass="textNone" id="cmd9"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{commonContactControllerBean.sortContact}">
												<m:div title="for descending" styleClass="sort_desc" />

												<f:attribute name="columnName"
													value="Suffix" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="cmd10" value="#{contact.nameVO.suffixNameDesc}" />
						</t:column>
						<f:facet name="footer">
							<m:div id="nodata" align="center">
								<h:outputText id="PRGCMGTOT287"
									value="#{cmnContactMsg['label.commonContact.noData']}"
									rendered="#{empty commonEntityDataBean.commonEntityVO.contactVOList}"></h:outputText>
							</m:div>
						</f:facet>
					</t:dataTable>

					<m:div styleClass="searchResults" id="div5">
						<t:dataScroller id="CMGTTOMDS10" pageCountVar="pageCount"
							pageIndexVar="pageIndex" paginator="true"
							paginatorActiveColumnStyle='font-weight:bold;'
							paginatorMaxPages="3" immediate="false" for="contactHist"
							firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
							rowsCountVar="rowsCount" styleClass="dataScroller"
							onclick="javascript:flagWarn=false;">
							<f:facet name="previous">
								<h:outputText id="PRGCMGTOT288" styleClass="underline"
									value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
							</f:facet>
							<f:facet name="next">
								<h:outputText id="PRGCMGTOT289" styleClass="underline"
									value=" #{ref['label.ref.gt']} "
									rendered="#{pageIndex < pageCount}"></h:outputText>
							</f:facet>

							<h:outputText id="PRGCMGTOT290"
								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								styleClass="dataScrollerText" rendered="#{rowsCount>0}">
							</h:outputText>
						</t:dataScroller>
						<m:div>
							<m:br></m:br>
							<m:br></m:br>
						</m:div>

						<m:div>
							<m:br></m:br>
						</m:div>
					</m:div>
					<m:div>
						<m:br></m:br>
					</m:div>

					<m:div styleClass="" id="newcontact"
						rendered="#{commonEntityDataBean.newContactRender}">

						<m:div styleClass="moreInfo">
							<m:div styleClass="moreInfoBar">
								<m:div styleClass="infoTitle" align="left">
									<h:outputText id="PRGCMGTOT291"
										value="#{commonEntityDataBean.contactEdit? cmnContactMsg['label.commonContact.editContact'] : cmnContactMsg['label.commonContact.addContact'] }" />
								</m:div>
								
									<%--<h:commandLink id="PRGCMGTCL51" styleClass="strong"										action="#{commonContactControllerBean.saveContact}" rendered="#{commonContactControllerBean.controlRequired}"										onmousedown="javascript:flagWarn=false;">
										<h:outputText id="PRGCMGTOT292"											value="#{cmnContactMsg['label.commonContact.save']}"></h:outputText>
									</h:commandLink>--%>
									<%-- for fixing defect:ESPRD00576305   --%>
									<%--Fix for Defect Id : ESPRD00715890....start--%>
									
									<m:div styleClass="infoActions">
									<h:commandButton id="contactCancel"
										action="#{commonContactControllerBean.cancelContact}"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal; position:relative;top:0.3px"
										value="#{cmnContactMsg['label.commonContact.cancel']}">
									</h:commandButton>
									</m:div>
									
									 <m:div styleClass="infoActions">
									<h:outputText id="PRGCMGTOT295" escape="false"
										value="#{ref['label.ref.linkSeperator']}" />
									</m:div>
									
										<m:div styleClass="infoActions">
									    <h:commandButton id="PRGCMGTCB9"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										onmousedown="javascript:flagWarn=false;"
										disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
										value="#{cmnContactMsg['label.commonContact.reset']}"
										action="#{commonContactControllerBean.resetContact}" />
										</m:div>
								    
								    <m:div styleClass="infoActions">
									<h:outputText id="PRGCMGTOT293" escape="false"
									value="#{ref['label.ref.linkSeperator']}" />
									</m:div>
									
									<m:div styleClass="infoActions">
									<h:commandButton id="PRGCMGTCB8" styleClass="strong"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										onmousedown="javascript:flagWarn=false;"
										disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
										value="#{cmnContactMsg['label.commonContact.save']}"
										action="#{commonContactControllerBean.saveContact}" />
									</m:div>
										
								

									<%--<h:commandLink id="PRGCMGTCL52"										action="#{commonContactControllerBean.resetContact}" rendered="#{commonContactControllerBean.controlRequired}"										onmousedown="javascript:flagWarn=false;">
										<h:outputText id="PRGCMGTOT294"											value="#{cmnContactMsg['label.commonContact.reset']}"></h:outputText>
									</h:commandLink>--%>
								
								   
									<%--Fix for Defect Id : ESPRD00715890....end--%>	
									
								   
								
											<%-- <h:inputHidden id="commonContactSaved"	value="#{commonEntityDataBean.commonContactSaved}"></h:inputHidden> --%>
							</m:div>
							<m:div styleClass="moreInfoContent">
								<m:div>
								</m:div>
								<m:div>
									<m:div>
									</m:div>
								</m:div>
								<m:br />
								<m:br />

								<m:br />
								<m:table id="PROVIDERMMT20120731164811153" cellspacing="0" width="90%">
									<m:tr>
										<m:td>
											<m:span styleClass="required">
												<f:verbatim>
													<h:outputText id="PRGCMGTOT296"
														value="#{cmnContactMsg['label.commonContact.required']}"></h:outputText>
												</f:verbatim>
											</m:span>
											<h:outputLabel id="PRGCMGTOLL150" for="status"
												value="#{cmnContactMsg['label.commonContact.status']}"></h:outputLabel>
											<m:br></m:br>
											<h:selectOneMenu id="status"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.status}">
												<f:selectItems value="#{commonEntityDataBean.statusList}" />
											</h:selectOneMenu>
											<m:br />
											<h:message id="PRGCMGTM55" for="status" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<m:span styleClass="required">
												<f:verbatim>
													<h:outputText id="PRGCMGTOT297"
														value="#{cmnContactMsg['label.commonContact.required']}"></h:outputText>
												</f:verbatim>
											</m:span>
											<h:outputLabel id="PRGCMGTOLL151" for="contyp"
												value="#{cmnContactMsg['label.commonContact.contactType']}"></h:outputLabel>
											<m:br></m:br>
											<h:selectOneMenu id="contyp"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.contactType}">
												<f:selectItems
													value="#{commonEntityDataBean.contactTypeList}" />
											</h:selectOneMenu>
											<m:br />
											<h:message id="PRGCMGTM56" for="contyp" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<m:span styleClass="required">
												<f:verbatim>
													<h:outputText id="PRGCMGTOT298"
														value="#{cmnContactMsg['label.commonContact.required']}"></h:outputText>
												</f:verbatim>
											</m:span>
											<h:outputLabel for="dob3" id="beginDateCnt"
												value="#{cmnContactMsg['label.commonContact.beginDate']}"></h:outputLabel>
											<m:br></m:br>

											<m:inputCalendar monthYearRowClass="yearMonthHeader"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												size="10" weekRowClass="weekHeader"
												currentDayCellClass="currentDayCell"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.beginDate}"
												renderAsPopup="true" addResources="true"
												renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
												readonly="false" id="dob3"
												onmouseover="javascript:flagWarn=false;"
												onmouseout="javascript:flagWarn=false;">
											</m:inputCalendar>

											<m:br />
											<h:message id="PRGCMGTM57" for="dob3" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT299"
													value="#{cmnContactMsg['label.commonContact.required']}"></h:outputText>
											</m:span>
											<h:outputLabel for="dob4" id="endDateCnt"
												value="#{cmnContactMsg['label.commonContact.endDate']}"></h:outputLabel>
											<m:br></m:br>

											<m:inputCalendar monthYearRowClass="yearMonthHeader"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												size="10" weekRowClass="weekHeader"
												currentDayCellClass="currentDayCell"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.endDate}"
												renderAsPopup="true" addResources="true"
												renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
												readonly="false" id="dob4"
												onmouseover="javascript:flagWarn=false;"
												onmouseout="javascript:flagWarn=false;">
											</m:inputCalendar>

											<m:br />
											<h:message id="PRGCMGTM58" for="dob4" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<m:span styleClass="required">
												<f:verbatim>
													<h:outputText id="PRGCMGTOT300"
														value="#{cmnContactMsg['label.commonContact.required']}"></h:outputText>
												</f:verbatim>
											</m:span>
											<h:outputLabel id="PRGCMGTOLL152" for="significance"
												value="#{cmnContactMsg['label.commonContact.significance']}"></h:outputLabel>
											<m:br></m:br>
											<h:selectOneMenu id="significance"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.significance}">
												<f:selectItems
													value="#{commonEntityDataBean.contactSigList}" />
											</h:selectOneMenu>
											<m:br />
											<h:message id="PRGCMGTM59" for="significance"
												showDetail="true" styleClass="errorMessage" />
											<m:br />
										</m:td>
									</m:tr>
									<m:tr>
										<m:td>
											<h:outputLabel id="PRGCMGTOLL153" for="title"
												value="#{cmnContactMsg['label.commonContact.title']}"></h:outputLabel>
											<m:br></m:br>
											<h:inputText id="title" size="15" maxlength="20"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.titleName}"></h:inputText>
											<m:br />
											<h:message id="PRGCMGTM60" for="title" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<h:outputLabel id="PRGCMGTOLL154" for="prfix"
												value="#{cmnContactMsg['label.commonContact.prefix']}"></h:outputLabel>
											<m:br></m:br>
											<h:selectOneMenu id="prfix"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.prefix}">
												<f:selectItems
													value="#{commonEntityDataBean.namePrefixList}" />

											</h:selectOneMenu>
										</m:td>
										<m:td>
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT301"
													value="#{cmnContactMsg['label.commonContact.required']}"></h:outputText>
											</m:span>
											<h:outputLabel id="PRGCMGTOLL155" for="fstNam"
												value="#{cmnContactMsg['label.commonContact.firstName']}"></h:outputLabel>
											<m:br></m:br>
											<h:inputText id="fstNam" size="15" maxlength="25"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.firstName}"></h:inputText>
											<m:br />
											<h:message id="PRGCMGTM61" for="fstNam" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<h:outputLabel id="PRGCMGTOLL156" for="mdlName"
												value="#{cmnContactMsg['label.commonContact.middleName']}"></h:outputLabel>
											<m:br></m:br>
											<h:inputText id="mdlName" size="15" maxlength="25"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.middleName}"></h:inputText>
											<m:br />
											<h:message id="PRGCMGTM62" for="mdlName" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<m:span styleClass="required">

												<h:outputText id="PRGCMGTOT302"
													value="#{cmnContactMsg['label.commonContact.required']}"></h:outputText>
											</m:span>
											<h:outputLabel id="PRGCMGTOLL157" for="lstNam"
												value="#{cmnContactMsg['label.commonContact.lastName']}"></h:outputLabel>
											<m:br></m:br>
											<h:inputText id="lstNam" size="20" maxlength="35"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.lastName}"></h:inputText>
											<m:br />
											<h:message id="PRGCMGTM63" for="lstNam" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<h:outputLabel id="PRGCMGTOLL158" for="sffxname"
												value="#{cmnContactMsg['label.commonContact.suffix']}"></h:outputLabel>
											<m:br></m:br>
											<h:selectOneMenu id="sffxname"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.suffixName}">
												<%--<f:selectItem itemLabel="Please Select" itemValue="" />
												<f:selectItem id="jr" itemLabel="Jr" itemValue="Jr" />
												<f:selectItem id="sr" itemLabel="Sr" itemValue="Sr" />--%>

												<f:selectItems
													value="#{commonEntityDataBean.nameSuffixList}" />


											</h:selectOneMenu>
										</m:td>
									</m:tr>
									<m:tr>
										<m:td>
											<h:outputLabel id="PRGCMGTOLL159" for="ssn"
												value="#{cmnContactMsg['label.commonContact.ssn']}"></h:outputLabel>
											<m:br></m:br>
											<h:inputText id="ssn" size="15" maxlength="11"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.SSN}"></h:inputText>
											<m:br />
											<h:message id="PRGCMGTM64" for="ssn" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<h:outputLabel for="dob2" id="dobCnt"
												value="#{cmnContactMsg['label.commonContact.dateOfBirth']}"></h:outputLabel>
											<m:br></m:br>
											<m:div styleClass="padb">
												<m:inputCalendar monthYearRowClass="yearMonthHeader"
													disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
													size="10" weekRowClass="weekHeader"
													currentDayCellClass="currentDayCell"
													value="#{commonEntityDataBean.commonEntityVO.commonContactVO.dateOfBirth}"
													renderAsPopup="true" addResources="true"
													renderPopupButtonAsImage="true"
													popupDateFormat="MM/dd/yyyy" readonly="false" id="dob2"
													onmouseover="javascript:flagWarn=false;"
													onmouseout="javascript:flagWarn=false;">
												</m:inputCalendar>
											</m:div>
											<m:br />
											<h:message id="PRGCMGTM65" for="dob21" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<h:outputLabel for="dod" id="dodCnt"
												value="#{cmnContactMsg['label.commonContact.dateOfDeath']}"></h:outputLabel>
											<m:br></m:br>
											<m:div styleClass="padb">
												<m:inputCalendar monthYearRowClass="yearMonthHeader"
													disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
													size="10" weekRowClass="weekHeader"
													currentDayCellClass="currentDayCell"
													value="#{commonEntityDataBean.commonEntityVO.commonContactVO.dateOfDeath}"
													renderAsPopup="true" addResources="true"
													renderPopupButtonAsImage="true"
													popupDateFormat="MM/dd/yyyy" readonly="false" id="dod"
													onmouseover="javascript:flagWarn=false;"
													onmouseout="javascript:flagWarn=false;">
												</m:inputCalendar>
											</m:div>
											<m:br />
											<h:message id="PRGCMGTM66" for="dod01" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>

										<m:td>
											<h:outputLabel id="PRGCMGTOLL160" for="gender"
												value="#{cmnContactMsg['label.commonContact.gender']}"></h:outputLabel>
											<m:br></m:br>
											<h:selectOneMenu id="gender"
												disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
												value="#{commonEntityDataBean.commonEntityVO.commonContactVO.gender}">
												<f:selectItems value="#{commonEntityDataBean.genderList}" />
											</h:selectOneMenu>
										</m:td>
									</m:tr>

								</m:table>
							</m:div>
							<m:div
								rendered="#{not empty commonEntityDataBean.commonEntityVO.commonContactVO.contactSK}">
								<f:subview id="ContactAuditInc">
									<%--jsp:include
										page="/jsp/commonEntities/inc_ContactAuditLogCnI.jsp" /--%>
									<m:div rendered="#{CMEntityMaintainDataBean.auditLogFlag}">

										<audit:auditTableSet id="contactAuditId"
											value="#{commonEntityDataBean.commonEntityVO.commonContactVO.auditKeyList}"
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
<t:saveState id="CMGTTOMSS37"
	value="#{commonEntityDataBean.commonEntityVO.contactVOList}" />
<t:saveState id="CMGTTOMSS38"
	value="#{commonEntityDataBean.mainContactRender}" />
<t:saveState id="CMGTTOMSS39"
	value="#{commonEntityDataBean.commonEntityVO.commonEntitySK}" />

<t:saveState id="CMGTTOMSS40"
	value="#{commonEntityDataBean.newContactRender}" />
<t:saveState id="CMGTTOMSS41"
	value="#{commonEntityDataBean.contactTypeEdit}" />
<t:saveState id="CMGTTOMSS42"
	value="#{commonEntityDataBean.contactEdit}" />
<t:saveState id="CMGTTOMSS43"
	value="#{commonEntityDataBean.newContactTypeRender}" />
<t:saveState id="CMGTTOMSS44"
	value="#{commonEntityDataBean.selectedConatctIndex}" />
<t:saveState id="CMGTTOMSS45"
	value="#{commonEntityDataBean.selectedConatctTypIndex}" />
<t:saveState id="CMGTTOMSS46"
	value="#{commonEntityDataBean.saveContactTypeChk}" />
<t:saveState id="CMGTTOMSS47"
	value="#{commonEntityDataBean.disableContactType}" />

<t:saveState id="CMGTTOMSS48"
	value="#{commonEntityDataBean.namePrefixList}" />
<t:saveState id="CMGTTOMSS49" value="#{commonEntityDataBean.genderList}" />
<t:saveState id="CMGTTOMSS50"
	value="#{commonEntityDataBean.entityTypeList}" />
<t:saveState id="CMGTTOMSS51"
	value="#{commonEntityDataBean.contactTypeList}" />
<t:saveState id="CMGTTOMSS52" value="#{commonEntityDataBean.statusList}" />
<t:saveState id="CMGTTOMSS53"
	value="#{commonEntityDataBean.contactSigList}" />

<t:saveState id="CMGTTOMSS54"
	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.voidIndicator}" />
<t:saveState id="CMGTTOMSS55"
	value="#{commonEntityDataBean.addcontactVoidIndicatorRender}" />
<t:saveState id="CMGTTOMSS56"
	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.beginDate}" />
<t:saveState id="CMGTTOMSS57"
	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.endDate}" />
<t:saveState id="CMGTTOMSS58"
	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.significance}" />
<t:saveState id="CMGTTOMSS59"
	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.contactType}" />
<t:saveState id="CMGTTOMSS60"
	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.status}" />


<t:saveState id="CMGTTOMSS61"
	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.contactSK}" />


