<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<script type="text/javascript">
	function phoneNumberPattern(obj) {
		if (obj != null && obj.value.length == 10 && !isNaN(obj.value)) {
			var phno = '';
			var given = obj.value;
			for ( var i = 0; i < given.length; i++) {
				if (i == 2 || i == 5) {
					phno = phno + given.charAt(i) + "-";
				} else {
					phno = phno + given.charAt(i);
				}
			}
			obj.value = phno;
		}

	}
	//-->
</script>

<portlet:defineObjects />
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_Phone"
	var="cmnPhoneMsg" />

<m:div>
	<f:subview id="maintainEntPhRecSubView">
		<m:section id="PROVIDERMMS20120731164811191" styleClass="otherbg">
			<m:legend>
				<h:outputLink
					onclick="setEntityHiddenValue('frm:updateAdmnsPhone:maintainEntPhRecSubView:phRecordHidden','minus');entityToggle('frm:updateAdmnsPhone:maintainEntPhRecSubView:maintainE_common_Phone',2);							entityPlusMinusToggle('frm:updateAdmnsPhone:maintainEntPhRecSubView:maintainE_common_Phone',this,'frm:updateAdmnsPhone:maintainEntPhRecSubView:phRecordHidden');return false;"
					id="plusMinusPhone" styleClass="plus" value="#">

					<h:outputText id="PRGCMGTOT495"
						value="#{cmnPhoneMsg['label.phone.phone']}" />
					<h:inputHidden id="phRecordHidden"
						value="#{CMEntityMaintainDataBean.phRecordHidden}"></h:inputHidden>
				</h:outputLink>

			</m:legend>
			<m:div id="maintainE_common_Phone">
				<m:div id="showhide_ph">
					<m:table id="PROVIDERMMT20120731164811192" width="100%">
						<m:tr>
							<m:td>
								<m:div id="message_eaddress">
								</m:div>
							</m:td>
						</m:tr>
					</m:table>
					<m:div rendered="#{commonEntityDataBean.phoneSaveMsg}"
						styleClass="msgbox">
						<h:outputText id="PRGCMGTOT496"
							value="#{cmnPhoneMsg['display.save.message']}">
						</h:outputText>
					</m:div>

					<m:table id="PROVIDERMMT20120731164811193" styleClass="tableBar" width="100%">
						<m:tr>
							<m:td styleClass="tableAction">
								<m:div styleClass="rightCell">
									<h:commandButton id="PRGCMGTCB13" styleClass="formBtn"
										disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
										value="#{cmnPhoneMsg['label.phone.addphoneRecord']}"
										action="#{phoneControllerBean.addPhone}"
										onclick="javascript:flagWarn=false;"></h:commandButton>
								</m:div>
							</m:td>
						</m:tr>
					</m:table>


					<%-- <t:dataTable cellspacing="0" styleClass="dataTable" width="100%"
						rows="10" first="#{commonEntityDataBean.startIndexForPhone}"
						value="#{commonEntityDataBean.commonEntityVO.phoneVOList}"
						var="phoneListinEntity" id="phoneHistory" rowIndexVar="rowIndex"
						columnClasses="columnClass" headerClass="headerClass"
						footerClass="footerClass" rowClasses="row_alt,row"
						rowOnClick="firstChild.lastChild.click();"
						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"> --%>
						
						<t:dataTable cellspacing="0" styleClass="dataTable" width="100%"
						rows="10" 
						value="#{commonEntityDataBean.commonEntityVO.phoneVOList}"
						var="phoneListinEntity" id="phoneHistory" rowIndexVar="rowIndex"
						columnClasses="columnClass" headerClass="headerClass"
						footerClass="footerClass" rowClasses="row_alt,row"
						rowOnClick="firstChild.lastChild.click();"
						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"> 

						<t:column id="Element1" styleClass="otherbg"
							onmousedown="javascript:flagWarn=false;">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD54" columns="2">
									<h:outputLabel for="PhStat" id="stat"
										value="#{cmnPhoneMsg['label.phone.status']}" />
									<h:panelGroup id="PhStat">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="phoneStatus1" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='phoneStatus1'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Status" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="phoneStatus2" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='phoneStatus2'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Status" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<t:commandLink id="PRGCMGTCL84"
								style="COLOR:black;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);text-decoration:none;vertical-align: bottom;word-spacing: normal"
								action="#{phoneControllerBean.editPhone}"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="statTyp"
									value="#{phoneListinEntity.statusstr}" />
								<f:param name="indexCode" value="#{rowIndex}"></f:param>
							</t:commandLink>
						</t:column>
						<t:column id="Element7" styleClass="otherbg"
							onmousedown="javascript:flagWarn=false;">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD55" columns="2">
									<h:outputLabel for="phnTy" id="phUsg"
										value="#{cmnPhoneMsg['label.phone.phoneType']}" />
									<h:panelGroup id="phnTy">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="phoneType1" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='phoneType1'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Phone Type" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="phoneType2" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='phoneType2'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Phone Type" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>

							<h:outputText
								style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);text-decoration:underline;vertical-align: bottom;word-spacing: normal"
								id="phValueEmt" value="#{phoneListinEntity.usageTypeDesc}" />
						</t:column>
						<t:column id="Element2" styleClass="otherbg"
							onmousedown="javascript:flagWarn=false;">

							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD56" columns="2">
									<h:outputLabel for="PhBegDt" id="begDate"
										value="#{cmnPhoneMsg['label.phone.beginDate']}" />
									<h:panelGroup id="PhBegDt">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="beginDate1" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='beginDate1'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Begin Date" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="beginDate2" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='beginDate2'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Begin Date" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="phBeginDt"
								value="#{phoneListinEntity.beginDateStr}" />
						</t:column>
						<t:column id="Element3" styleClass="otherbg"
							onmousedown="javascript:flagWarn=false;">

							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD57" columns="2">
									<h:outputLabel for="phEnddt" id="endDate"
										value="#{cmnPhoneMsg['label.phone.endDate']}" />
									<h:panelGroup id="phEnddt">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="endDate1" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='endDate1'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="End Date" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="endDate2" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='endDate2'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="End Date" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valueElement3"
								value="#{phoneListinEntity.endDateStr}" />
						</t:column>
						<t:column id="Element4" styleClass="otherbg"
							onmousedown="javascript:flagWarn=false;">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD58" columns="2">
									<h:outputLabel for="phHas" id="ph"
										value="#{cmnPhoneMsg['label.phone.phone#']}" />
									<h:panelGroup id="phHas">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="phoneNo1" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='phoneNo1'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Phone #" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="phoneNo2" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='phoneNo2'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Phone #" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="phValue"
								value="#{phoneListinEntity.phoneNumber}" />
						</t:column>
						<t:column id="Element5" styleClass="otherbg"
							onmousedown="javascript:flagWarn=false;">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD59" columns="2">
									<h:outputLabel for="PhExt" id="ext"
										value="#{cmnPhoneMsg['label.phone.extension']}" />
									<h:panelGroup id="PhExt">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="extension1" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='extension1'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Ext." />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="extension2" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='extension2'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Ext." />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="extValue"
								value="#{phoneListinEntity.extension}" />
						</t:column>
						<t:column id="Element8" styleClass="otherbg"
							onmousedown="javascript:flagWarn=false;">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD60" columns="2">
									<h:outputLabel for="PhOutSer" id="ofs"
										value="#{cmnPhoneMsg['label.phone.outofservice']}" />
									<h:panelGroup id="PhOutSer">
										<t:div styleClass="widthXLeft">
											<t:commandLink id="outOfService1" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='outOfService1'}">
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Out of Service" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="widthXLeft">
											<t:commandLink id="outOfService2" styleClass="textNone"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{phoneControllerBean.sort}"
												rendered="#{commonEntityDataBean.imageRender !='outOfService2'}">
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Out of Service" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="ofsValue"
								value="#{phoneListinEntity.outOfServicestr}" />
						</t:column>
						<f:facet name="footer">

							<m:div id="nodata" align="center">
								<h:outputText id="PRGCMGTOT497"
									value="#{cmnPhoneMsg['label.phone.noData']}"
									rendered="#{empty commonEntityDataBean.commonEntityVO.phoneVOList}"></h:outputText>
							</m:div>

						</f:facet>
					</t:dataTable>




					<m:div styleClass="searchResults" id="phonePagenation">

						<t:dataScroller id="PROVIDERMDS20120731164811194" pageCountVar="pageCount" pageIndexVar="pageIndex"
							paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
							paginatorMaxPages="3" immediate="false" for="phoneHistory"
							firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
							rowsCountVar="rowsCount" styleClass="scroller">

							<f:facet name="previous">
								<h:outputText id="PRGCMGTOT498" styleClass="underline"
									value="#{cmnPhoneMsg['label.phone.previous']}"
									rendered="#{pageIndex > 1}"></h:outputText>
							</f:facet>
							<f:facet name="next">
								<h:outputText id="PRGCMGTOT499" styleClass="underline"
									value="#{cmnPhoneMsg['label.phone.next']}"
									rendered="#{pageIndex < pageCount}"></h:outputText>
							</f:facet>




							<h:outputText id="PRGCMGTOT500"
								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								styleClass="scrollerBold" rendered="#{rowsCount>0}">
							</h:outputText>
						</t:dataScroller>
						<m:div>
							<m:br></m:br>
						</m:div>
					</m:div>





					<m:br></m:br>
					<m:br></m:br>
					<m:br></m:br>
					<m:div>
						<h:graphicImage id="PROVIDERGI20120731164811195" alt="" url="/images/x.gif" width="1" height="10"
							styleClass="blankImage" />
					</m:div>

					<%--<h:messages id="PRGCMGTMS2" showDetail="true" layout="table" showSummary="false"							                          styleClass="errorMessage" />--%>

					<m:div id="newDispphone" styleClass="moreInfo"
						rendered="#{commonEntityDataBean.phoneRendered}">
						<m:div styleClass="moreInfoBar">
							<m:table id="PROVIDERMMT20120731164811196" styleClass="tableBar" width="100%">
								<m:div id="actionsDiv1" styleClass="actions">

									<m:div styleClass="infoTitle">
										<h:outputText id="PRGCMGTOT501"
											value="#{commonEntityDataBean.editPhoneRendered ? cmnPhoneMsg['label.phone.editphoneRecord']:cmnPhoneMsg['label.phone.addphoneRecord']}" />
									</m:div>
									
									
										<%--<h:commandLink id="PRGCMGTCL97" styleClass="strong"											action="#{phoneControllerBean.savePhone}"											rendered="#{phoneControllerBean.controlRequired}"											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT502" value="#{cmnPhoneMsg['label.phone.save']}">
											</h:outputText>
										</h:commandLink>--%>
										
										<%--Fix for Defect ID : ESPRD00715890...start   --%>
										<m:div styleClass="infoActions">
										<h:commandButton id="phoneCancel"
											action="#{phoneControllerBean.cancelPhone}"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:43px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal; position:relative;top:0.3px"
											value="#{cmnPhoneMsg['label.phone.cancel']}"></h:commandButton>
										</m:div>
										
										<m:div styleClass="infoActions">
										<h:outputText id="PRGCMGTOT505" escape="false"
											style="position:relative;top:1px"
											value="#{ref['label.ref.linkSeperator']}" />
										</m:div>
										
										<m:div styleClass="infoActions">
										<h:commandButton id="PRGCMGTCB15"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
											onmousedown="javascript:flagWarn=false;"
											disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
											value="#{cmnPhoneMsg['label.phone.reset']}"
											action="#{phoneControllerBean.resetPhone}" />
										</m:div>
										
										<m:div styleClass="infoActions">
										<h:outputText id="PRGCMGTOT503" escape="false"
											style="position:relative;top:1px"
											value="#{ref['label.ref.linkSeperator']}" />
										</m:div>
										
										<m:div styleClass="infoActions">
										<h:commandButton id="PRGCMGTCB14" styleClass="strong"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
											onmousedown="javascript:flagWarn=false;"
											disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
											value="#{cmnPhoneMsg['label.phone.save']}"
											action="#{phoneControllerBean.savePhone}" />
										</m:div>

										
										<%--<h:commandLink id="PRGCMGTCL98" action="#{phoneControllerBean.resetPhone}"											rendered="#{phoneControllerBean.controlRequired}"											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT504" value="#{cmnPhoneMsg['label.phone.reset']}">
											</h:outputText>
										</h:commandLink>--%>
										
										<%--Fix for Defect ID : ESPRD00715890...end   --%>
										
											
										
										<%-- <h:inputHidden id="commonPhoneSaved" value="#{commonEntityDataBean.commonPhoneSaved}"></h:inputHidden> --%>
									
									</m:div>
							</m:table>
						</m:div>

						<m:div styleClass="moreInfoContent">
							<m:div>
								<m:div id="message_addContact">
								</m:div>
							</m:div>
							<m:div styleClass="width100">
								<m:table id="PROVIDERMMT20120731164811197" cellspacing="0" width="100%">
									<m:tr>
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="PRGCMGTOT506"
														value="#{cmnPhoneMsg['label.phone.required']}" />
												</m:span>
												<h:outputLabel id="PRGCMGTOLL179" for="usgTyp2"
													value="#{cmnPhoneMsg['label.phone.phoneType']}" />
												<m:br></m:br>
												<h:selectOneMenu id="usgTyp2"
													value="#{commonEntityDataBean.commonEntityVO.phoneVO.usageTypeCode}"
													disabled="#{commonEntityDataBean.disablePhoneRecord || !CMEntityMaintainControllerBean.controlRequired}">
													<f:selectItems
														value="#{commonEntityDataBean.commonEntityVO.phoneVO.usageList}" />
												</h:selectOneMenu>
												<m:br />
												<h:message id="PRGCMGTM73" for="usgTyp2" showDetail="true"
													styleClass="errorMessage" />
												<m:br />
											</m:div>
										</m:td>
										<m:td>
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT507"
													value="#{cmnPhoneMsg['label.phone.required']}" />
											</m:span>
											<h:outputLabel for="PhBgDT1" id="beginDate1">
												<h:outputText id="PRGCMGTOT508"
													value="#{cmnPhoneMsg['label.phone.beginDate']}" />
											</h:outputLabel>
											<m:br></m:br>
											<m:inputCalendar id="PhBgDT1" size="10"
												disabled="#{commonEntityDataBean.disableBeginDate ||!CMEntityMaintainControllerBean.controlRequired}"
												monthYearRowClass="yearMonthHeader"
												weekRowClass="weekHeader"
												currentDayCellClass="currentDayCell"
												value="#{commonEntityDataBean.commonEntityVO.phoneVO.beginDate}"
												renderAsPopup="true" addResources="true" readonly="false"
												popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
												onmouseover="javascript:flagWarn=false;"
												onmouseout="javascript:flagWarn=false;" />
											<m:br />
											<h:message id="PRGCMGTM74" for="PhBgDT1" showDetail="true"
												styleClass="errorMessage" />
											<m:br />
										</m:td>
										<m:td>
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT509"
													value="#{cmnPhoneMsg['label.phone.required']}" />
											</m:span>
											<h:outputLabel for="PhEndDt1" id="phEnddate">
												<h:outputText id="PRGCMGTOT510"
													value="#{cmnPhoneMsg['label.phone.endDate']}" />
											</h:outputLabel>
											<m:br></m:br>
											<m:inputCalendar id="PhEndDt1" size="10"
												monthYearRowClass="yearMonthHeader"
												weekRowClass="weekHeader"
												currentDayCellClass="currentDayCell"
												value="#{commonEntityDataBean.commonEntityVO.phoneVO.endDate}"
												renderAsPopup="true" addResources="true" readonly="false"
												popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
												disabled="#{commonEntityDataBean.disablePhoneRecord || !CMEntityMaintainControllerBean.controlRequired}"
												onmouseover="javascript:flagWarn=false;"
												onmouseout="javascript:flagWarn=false;" />
											<m:br />
											<h:message id="PRGCMGTM75" for="PhEndDt1" showDetail="true"
												styleClass="errorMessage" />
											<m:br />

										</m:td>

										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL180" for="phSgnfce2"
													value="#{cmnPhoneMsg['label.phone.significanceTypeCode']}" />
												<m:br></m:br>
												<h:selectOneMenu id="phSgnfce2"
													value="#{commonEntityDataBean.commonEntityVO.phoneVO.significance}"
													disabled="#{commonEntityDataBean.disablePhoneRecord || !CMEntityMaintainControllerBean.controlRequired}">
													<f:selectItems
														value="#{commonEntityDataBean.commonEntityVO.phoneVO.significanceList}" />
												</h:selectOneMenu>
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL181" for="phStat"
													value="#{cmnPhoneMsg['label.phone.statusCode']}" />
												<m:br></m:br>
												<h:selectOneMenu id="phStat"
													value="#{commonEntityDataBean.commonEntityVO.phoneVO.status}"
													disabled="#{commonEntityDataBean.disablePhoneRecord || !CMEntityMaintainControllerBean.controlRequired}">
													<f:selectItems
														value="#{commonEntityDataBean.commonEntityVO.phoneVO.statusList}" />
												</h:selectOneMenu>
											</m:div>
										</m:td>
									</m:tr>
									<m:tr>
										<m:td>
											<m:div styleClass="padb">
												<m:span styleClass="required">
													<h:outputText id="PRGCMGTOT511"
														value="#{cmnPhoneMsg['label.phone.required']}" />
												</m:span>
												<h:outputLabel id="PRGCMGTOLL182" for="phno"
													value="#{cmnPhoneMsg['label.phone.phone']}" />
												<h:outputLabel id="PRGCMGTOLL183" for="phno"
													value="#{cmnPhoneMsg['label.phone.hash']}" />
												<m:br></m:br>
												<h:inputText size="10" maxlength="12" id="phno"
													onblur="javascript:phoneNumberPattern(this);"
													value="#{commonEntityDataBean.commonEntityVO.phoneVO.phoneNumber}"
													disabled="#{commonEntityDataBean.disablePhoneRecord || !CMEntityMaintainControllerBean.controlRequired}" />
												<m:br />
												<h:message id="PRGCMGTM76" for="phno" showDetail="true"
													styleClass="errorMessage" />
												<m:br />
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL184" for="extnsn"
													value="#{cmnPhoneMsg['label.phone.extension']}" />
												<m:br></m:br>
												<h:inputText size="7" maxlength="4" id="extnsn"
													value="#{commonEntityDataBean.commonEntityVO.phoneVO.extension}"
													disabled="#{commonEntityDataBean.disablePhoneRecord || !CMEntityMaintainControllerBean.controlRequired}" />
												<m:br />
												<h:message id="PRGCMGTM77" for="extnsn" showDetail="true"
													styleClass="errorMessage" />
												<m:br />
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:selectBooleanCheckbox id="outofsrvc"
													value="#{commonEntityDataBean.commonEntityVO.phoneVO.outOfService}" />
								&nbsp;&nbsp;
												<h:outputLabel id="PRGCMGTOLL185"
													for="outofsrvc">
													<h:outputText
														value="#{cmnPhoneMsg['label.phone.outofservice']}"
														styleClass="outputLabel" id="outofserviceForPhnRecord" />
												</h:outputLabel>
											</m:div>
										</m:td>

										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL186" for="cntryCd4"
													value="#{cmnPhoneMsg['label.phone.countrycode']}" />
												<m:br></m:br>
												<h:selectOneMenu id="cntryCd4" immediate="true"
													value="#{commonEntityDataBean.commonEntityVO.phoneVO.countryCode}"
													disabled="#{commonEntityDataBean.disablePhoneRecord || !CMEntityMaintainControllerBean.controlRequired}">
													<f:selectItems
														value="#{commonEntityDataBean.commonEntityVO.phoneVO.countryCodeList}" />
												</h:selectOneMenu>
												<hx:behavior event="onchange" behaviorAction="get"
													targetAction="group2" target="cntryCd4"></hx:behavior>
											</m:div>
										</m:td>

										<m:td>
											<h:panelGroup id="group2"
												rendered="#{phoneControllerBean.onAjaxChange}">
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL187" for="intrnlphone"
														value="#{cmnPhoneMsg['label.phone.internationalphone#']}" />
													<m:br></m:br>
													<h:inputText size="20" maxlength="20" id="intrnlphone"
														disabled="#{commonEntityDataBean.countryCodeStatus || commonEntityDataBean.disablePhoneRecord}"
														value="#{commonEntityDataBean.commonEntityVO.phoneVO.internationalPhoneNo}" />
													<m:br />
													<h:message id="PRGCMGTM78" for="intrnlphone"
														showDetail="true" styleClass="errorMessage" />
													<m:br />
												</m:div>
											</h:panelGroup>
											<hx:ajaxRefreshRequest id="ajaxRefreshRequest2"
												target="group2" params="cntryCd4"></hx:ajaxRefreshRequest>
										</m:td>
									</m:tr>
								</m:table>
								<m:div
									rendered="#{not empty commonEntityDataBean.commonEntityVO.phoneVO.phoneSK}">
									<f:subview id="PhoneAuditInc">
										<%--jsp:include
											page="/jsp/commonEntities/inc_PhoneAuditLogCnI.jsp" /---%>
										<m:div rendered="#{CMEntityMaintainDataBean.auditLogFlag}">

											<audit:auditTableSet id="phoneAuditId"
												value="#{commonEntityDataBean.commonEntityVO.phoneVO.auditKeyList}"
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
<t:saveState id="CMGTTOMSS129"
	value="#{commonEntityDataBean.phoneRendered}" />
<t:saveState id="CMGTTOMSS130"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.phoneSK}" />
<t:saveState id="CMGTTOMSS131"
	value="#{commonEntityDataBean.phoneSaved}" />
<t:saveState id="CMGTTOMSS132"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.usageList}" />
<t:saveState id="CMGTTOMSS133"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.significanceList}" />
<t:saveState id="CMGTTOMSS134"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.statusList}" />
<t:saveState id="CMGTTOMSS135"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.countryCodeList}" />
<t:saveState id="CMGTTOMSS136"
	value="#{commonEntityDataBean.editPhoneRendered}" />
<t:saveState id="CMGTTOMSS137"
	value="#{commonEntityDataBean.commonEntityVO.phoneVOList}" />
<t:saveState id="CMGTTOMSS138"
	value="#{commonEntityDataBean.phoneVOIndex}" />
<t:saveState id="CMGTTOMSS139"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.beginDate}" />
<t:saveState id="CMGTTOMSS140"
	value="#{commonEntityDataBean.commonEntityVO.commonEntitySK}" />
<t:saveState id="CMGTTOMSS141"
	value="#{commonEntityDataBean.disablePhoneRecord}" />
<t:saveState id="CMGTTOMSS142"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.usageTypeCode}" />
<t:saveState id="CMGTTOMSS143"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.internationalPhoneNo}" />
<t:saveState id="CMGTTOMSS144"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.countryCode}" />
<t:saveState id="CMGTTOMSS145"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.outOfService}" />
<t:saveState id="CMGTTOMSS146"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.significance}" />
<t:saveState id="CMGTTOMSS147"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.status}" />
<t:saveState id="CMGTTOMSS148"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.extension}" />

<t:saveState id="CMGTTOMSS149"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.phoneNumber}" />
<t:saveState id="CMGTTOMSS150"
	value="#{commonEntityDataBean.commonEntityVO.phoneVO.endDate}" />
