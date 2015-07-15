<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />


<m:div styleClass="moreInfo">
	<m:div styleClass="otherbg">
		<m:br></m:br>
		<m:section id="PROVIDERMMS20120731164811535">
			<m:div styleClass="clear"></m:div>
			<m:div>
				<h:graphicImage id="PROVIDERGI20120731164811536" styleClass="blankImage" width="1" height="5" alt=""
					url="/images/x.gif" />
			</m:div>
			<m:legend>

				<h:outputText id="PRGCMGTOT1261"
					value="#{msg['label.customFields.addDropDownItems']}" />

			</m:legend>
			<m:div styleClass="msgBox"
				rendered="#{customFieldDataBean.showDDDeletedMessage}">
				<h:outputText id="PRGCMGTOT1262"
					value="#{msg['label.customFields.deleteMessage']}" />
			</m:div>
			<m:table id="PROVIDERMMT20120731164811537" styleClass="tableBar" width="100%">
				<m:tr>
					<m:td styleClass="rightCell">
						<h:commandButton id="PRGCMGTCB51" styleClass="formBtn"
							onmousedown="javascript:flagWarn=false;"
							disabled="#{!customFieldControllerBean.controlRequired}"
							value="#{msg['label.customFields.addDropDown']}"
							action="#{customFieldControllerBean.addDropDownTable}" />
					</m:td>
				</m:tr>
			</m:table>
			
		<%-- Changed first attribute to : ddstartIndex from startIndex for the defect ESPRD00795949 --%>
			<t:dataTable border="1" cellpadding="0" cellspacing="0"
				styleClass="dataTableOne" id="dropDown_table" width="100%" rows="10"
				columnClasses="columnClass" headerClass="headerClass"
				footerClass="footerClass" rowClasses="row_alt,rowone"
				var="addDropDownResult" value="#{customFieldDataBean.dropDownList}"
				rowIndexVar="rowIndex"
				rendered="#{customFieldDataBean.dropDownDataFlag}"
				first="#{customFieldDataBean.ddstartIndex}" 
				onmouseover="return tableMouseOver(this, event);"
				onmouseout="return tableMouseOut(this, event);">
				<h:column id="add_dropDown">
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD152" columns="2">
							<h:outputLabel id="PRGCMGTOLL548" for="description"
								value="#{msg['label.customFields.dropDownItemDescription']}" />
							<h:panelGroup id="PRGCMGTPGP102">
								<t:div>
									<t:commandLink id="dropDownCommandLink1"
										onmousedown="if(event.button==1) flagWarn=false;"
										actionListener="#{customFieldControllerBean.sortCustomFields}"
										rendered="#{customFieldDataBean.imageRender != 'dropDownCommandLink1'}">
										
										<%--<h:graphicImage
											alt="#{msg['label.customFields.forAscending']}"
											styleClass="sort_asc" width="8" url="/images/x.gif" />  --%>
											
										<m:div title="#{msg['label.customFields.forAscending']}" styleClass="sort_asc" />
										<f:attribute name="columnName" value="ddItemDesc" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div>
									<t:commandLink id="dropDownCommandLink2"
										onmousedown="if(event.button==1) flagWarn=false;"
										actionListener="#{customFieldControllerBean.sortCustomFields}"
										rendered="#{customFieldDataBean.imageRender != 'dropDownCommandLink2'}">
										
										<%--<h:graphicImage
											alt="#{msg['label.customFields.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />  --%>
											
										<m:div title="#{msg['label.customFields.forDescending']}"	styleClass="sort_desc" />
										<f:attribute name="columnName" value="ddItemDesc" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>

					<t:commandLink id="focusSortOrder"
						onmousedown="javascript:focusThis(this.id);flagWarn=false;"
						action="#{customFieldControllerBean.editDropDownTable}">
						<h:outputText id="PRGCMGTOT1263"
							value="#{addDropDownResult.dropDownItemDesc}" />
					<f:param name="cfddRowIndex" value="#{rowIndex}" />
					</t:commandLink>
				</h:column>
				<h:column id="enddate">
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD153" columns="2">
							<h:outputLabel id="PRGCMGTOLL549" for="sortOrder"
								value="#{msg['label.customFields.sortOrder']}" />
							<h:panelGroup id="PRGCMGTPGP103">
								<t:div>
									<t:commandLink id="ddSortOrderCommandLink1"
										actionListener="#{customFieldControllerBean.sortCustomFields}"
										rendered="#{customFieldDataBean.imageRender != 'ddSortOrderCommandLink1'}">
										
										<%--<h:graphicImage
											alt="#{msg['label.customFields.forAscending']}"
											styleClass="sort_asc" width="8" url="/images/x.gif" />  --%>
											
										<m:div title="#{msg['label.customFields.forAscending']}" styleClass="sort_asc" />
										<f:attribute name="columnName" value="ddSortOrder" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div>
									<t:commandLink id="ddSortOrderCommandLink2"
										actionListener="#{customFieldControllerBean.sortCustomFields}"
										rendered="#{customFieldDataBean.imageRender != 'ddSortOrderCommandLink2'}">
										
										<%--<h:graphicImage
											alt="#{msg['label.customFields.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />  --%>
											
										<m:div title="#{msg['label.customFields.forDescending']}"	styleClass="sort_desc" />
										<f:attribute name="columnName" value="ddSortOrder" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="PRGCMGTOT1264"
						value="#{addDropDownResult.sortOrder}" />
				</h:column>
			</t:dataTable>
			<m:div styleClass="searchResults">
				<t:dataScroller id="CMGTTOMDS44" pageCountVar="pageCount"
					pageIndexVar="pageIndex"
					onclick="javascript:focusThis(this.id); flagWarn=false;" 
					paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
					paginatorMaxPages="3" immediate="false" for="dropDown_table"
					firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
					rowsCountVar="rowsCount" styleClass="dataScroller">
					<f:facet name="previous">
						<h:outputText id="PRGCMGTOT1265"
							value="#{msg['label.customFields.pageLeft']}"
							rendered="#{pageIndex > 1}">
						</h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText id="PRGCMGTOT1266"
							value="#{msg['label.customFields.pageRight']}"
							rendered="#{pageIndex < pageCount}">
						</h:outputText>
					</f:facet>
					<h:outputText id="PRGCMGTOT1267" styleClass="dataScrollerText"
						value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
						rendered="#{rowsCount>0}">
					</h:outputText>
				</t:dataScroller>

				<h:dataTable var="dummyDropDown"
					rendered="#{!customFieldDataBean.dropDownDataFlag}"
					styleClass="dataTableOne" cellspacing="0" width="100%" border="1"
					headerClass="tableHead" rowClasses="rowone,row_alt"
					id="dummyDropDownTable">
					<t:column id="dummydddatacolumn1">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT1268"
								value="#{msg['label.customFields.dropDownItemDescription']}" />
						</f:facet>
					</t:column>
					<t:column id="dummydddatacolumn2">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT1269"
								value="#{msg['label.customFields.sortOrder']}" />
						</f:facet>
					</t:column>
				</h:dataTable>
				<m:table id="PROVIDERMMT20120731164811538" styleClass="dataTable" width="100%" border="0"
					rendered="#{!customFieldDataBean.dropDownDataFlag}">
					<m:tr>
						<m:td align="center">
							<h:outputText id="PRGCMGTOT1270"
								value="#{msg['label.customFields.noData']}" />
						</m:td>
					</m:tr>
				</m:table>
				<m:div styleClass="clear"></m:div>
				<m:div>
					<h:graphicImage id="PROVIDERGI20120731164811539" styleClass="blankImage" width="1" height="5" alt=""
						url="/images/x.gif" />
				</m:div>
			</m:div>
			<%--Add Drop Down --%>

			<m:div id="addDD" styleClass="moreInfo"
				rendered="#{customFieldDataBean.addDropDownFlag}">
				<m:div styleClass="moreInfoBar">
					<m:div styleClass="infoTitle">
						<h:outputText id="PRGCMGTOT1271"
							value="#{msg['label.customFields.addDropDownItem']}" />
					</m:div>
					<m:div styleClass="infoActions">
						<t:commandLink id="PRGCMGTCL185" styleClass="strong"
							onmousedown="javascript:flagWarn=false;"
							action="#{customFieldControllerBean.saveDropDown}">
							<h:outputText id="PRGCMGTOT1272"
								value="#{msg['label.customFields.save']}" />
						</t:commandLink>
						<h:outputText id="PRGCMGTOT1273"
							value="#{msg['label.customFields.space']}" />
						<t:commandLink id="PRGCMGTCL186"
							onmousedown="if(event.button==1) flagWarn=false;"
							action="#{customFieldControllerBean.resetAddDropDown}">
							<h:outputText id="PRGCMGTOT1274"
								value="#{msg['label.customFields.reset']}" />
						</t:commandLink>
						<h:outputText id="PRGCMGTOT1275"
							value="#{msg['label.customFields.space']}" />

						<h:commandButton id="PRGCMGTCB52"
							style="font-weight:bold;COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
							value="#{msg['label.customFields.cancel']}"
							action="#{customFieldControllerBean.cancelAddDropDown}" />
					</m:div>
					<m:div styleClass="clear"></m:div>
				</m:div>

				<m:div>
					<h:graphicImage id="PROVIDERGI20120731164811540" styleClass="blankImage" width="1" height="5" alt=""
						url="/images/x.gif" />
				</m:div>


				<m:div rendered="#{customFieldDataBean.showSuccessMsgFlag}"
					styleClass="msgBox">
					<h:outputText id="PRGCMGTOT1276" value="#{ent['label-sw-success']}" />
				</m:div>






				<m:div rendered="#{customFieldDataBean.showDescMsgFlag}">
					<h:messages layout="table" showDetail="true"
						id="addDropDownDescMessage" showSummary="false"
						styleClass="errorMessage"></h:messages>
				</m:div>
				<m:div styleClass="moreInfoContent">
					<m:table id="PROVIDERMMT20120731164811541" cellspacing="0" width="95%">
						<m:tr>
							<m:td>
								<m:div styleClass="padb">
									<h:outputText id="PRGCMGTOT1277"
										value="#{msg['label.customFields.star']}"
										styleClass="errorMessage" />
									<h:outputLabel id="PRGCMGTOLL550" for="add_DD"
										value="#{msg['label.customFields.dropDownItemDescription']}">
									</h:outputLabel>
									<m:br></m:br>
									<h:inputText id="add_DD"
										value="#{customFieldDataBean.customFieldVO.dropDownVO.dropDownItemDesc}" />
									<m:br />
									<h:message id="PRGCMGTM177" for="add_DD"
										styleClass="errorMessage" />
								</m:div>
							</m:td>
							<m:td>
								<m:div styleClass="padb">
									<h:outputText id="PRGCMGTOT1278"
										value="#{msg['label.customFields.star']}"
										styleClass="errorMessage" />
									<h:outputLabel id="PRGCMGTOLL551" for="add_SortOrder"
										value="#{msg['label.customFields.sortOrder']}">
									</h:outputLabel>
									<m:br></m:br>
									<h:inputText id="add_SortOrder"
										value="#{customFieldDataBean.customFieldVO.dropDownVO.sortOrder}" />
									<m:br />
									<h:message id="PRGCMGTM178" for="add_SortOrder"
										styleClass="errorMessage" />
								</m:div>
							</m:td>
						</m:tr>
					</m:table>
				</m:div>
			</m:div>


			<m:div id="editDD" styleClass="moreInfo"
				rendered="#{customFieldDataBean.editDropDownFlag}">
				<!-- Start menu bar-->
				<m:div rendered="#{customFieldDataBean.editDropDownMenuFlag}"
					styleClass="moreInfoBar">
					<m:div styleClass="infoTitle">
						<h:outputText id="PRGCMGTOT1279"
							value="#{msg['label.customFields.editDropDownItem']}" />
					</m:div>
					<m:div styleClass="infoActions">
						<h:commandButton
							style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:34px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
							value="#{msg['label.customFields.save']}"
							onmousedown="javascript:flagWarn=false;" id="saveDrpDwn"
							disabled="#{!customFieldControllerBean.controlRequired}"
							action="#{customFieldControllerBean.saveDropDown}" />

						<h:outputText id="PRGCMGTOT1280" escape="false" value="|" />
						<h:commandButton
							style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:40px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
							value="#{msg['label.customFields.reset']}"
							onmousedown="if(event.button==1) flagWarn=false;"
							id="resetDrpDwn"
							disabled="#{!customFieldControllerBean.controlRequired}"
							action="#{customFieldControllerBean.resetEditDropDown}" />

						<h:outputText id="PRGCMGTOT1281" escape="false" value="|" />
						<h:commandButton id="PRGCMGTCB53"
							style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
							onclick="flagWarn=false; if (!(confirm('Are you sure you want to Delete?'))) return(false);"
							disabled="#{!customFieldControllerBean.controlRequired || !customFieldControllerBean.controlRequiredFOrDelete}"
							value="#{msg['label.customFields.delete']}"
							action="#{customFieldControllerBean.deleteEditDropDown}" />
						<h:outputText id="PRGCMGTOT1282"
							value="#{msg['label.customFields.space']}" />

						<h:commandButton id="PRGCMGTCB54"
							onmousedown="if(event.button==1) flagWarn=false;"
							style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:58px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
							disabled="#{!customFieldControllerBean.controlRequired}"
							value="Audit Log"
							action="#{customFieldControllerBean.doAuditforcfDropDown}" />
						<h:outputText id="PRGCMGTOT1283"
							value="#{msg['label.customFields.space']}" />
						<h:commandButton id="PRGCMGTCB55"
							style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
							value="#{msg['label.customFields.cancel']}"
							action="#{customFieldControllerBean.cancelAddDropDown}" />
					</m:div>
					<m:div styleClass="clear"></m:div>
				</m:div>
				<!-- End menu bar-->
				<m:div>
					<h:graphicImage id="PROVIDERGI20120731164811542" styleClass="blankImage" width="1" height="5" alt=""
						url="/images/x.gif" />
				</m:div>

				<m:div rendered="#{customFieldDataBean.showSuccessMsgFlag}"
					styleClass="msgBox">
					<h:outputText id="PRGCMGTOT1284" value="#{ent['label-sw-success']}" />
				</m:div>

				<m:div rendered="#{customFieldDataBean.showDescMsgFlag}">
					<h:messages layout="table" showDetail="true"
						id="editDropDownDescMessage" showSummary="false"
						styleClass="errorMessage"></h:messages>
				</m:div>
				<!-- Start Drop down Information  -->
				<m:div rendered="#{customFieldDataBean.editDropDownInfoFlag}"
					styleClass="moreInfoContent">
					<m:table id="PROVIDERMMT20120731164811543" cellspacing="0" width="95%">
						<m:tr>
							<m:td>
								<m:div styleClass="padb">
									<h:outputText id="PRGCMGTOT1285"
										value="#{msg['label.customFields.star']}"
										styleClass="errorMessage" />
									<h:outputLabel id="PRGCMGTOLL552" for="edit_DD"
										value="#{msg['label.customFields.dropDownItemDescription']}">
									</h:outputLabel>
									<m:br></m:br>
									<h:inputText id="edit_DD"
										value="#{customFieldDataBean.customFieldVO.dropDownVO.dropDownItemDesc}" />
									<m:br />
									<h:message id="PRGCMGTM179" for="edit_DD"
										styleClass="errorMessage" />
								</m:div>
							</m:td>
							<m:td>
								<m:div styleClass="padb">
									<h:outputText id="PRGCMGTOT1286"
										value="#{msg['label.customFields.star']}"
										styleClass="errorMessage" />
									<h:outputLabel id="PRGCMGTOLL553" for="edit_SortOrder"
										value="#{msg['label.customFields.sortOrder']}"></h:outputLabel>
									<m:br></m:br>
									<h:inputText id="edit_SortOrder"
										value="#{customFieldDataBean.customFieldVO.dropDownVO.sortOrder}" />
									<m:br />
									<h:message id="PRGCMGTM180" for="edit_SortOrder"
										styleClass="errorMessage" />
								</m:div>
							</m:td>
						</m:tr>
					</m:table>
					<m:br></m:br>


					<m:div
						rendered="#{not empty customFieldDataBean.customFieldVO.dropDownVO.dropDownItemDesc}">
						<f:subview id="auditlogDetails">
							<m:div rendered="#{customFieldDataBean.showCFforAuddropdown}">
								<audit:auditTableSet id="auditlogDetailsforcfDDId"
									value="#{customFieldDataBean.customFieldVO.auditKeyList}"
									numOfRecPerPage="10">
								</audit:auditTableSet>
							</m:div>
						</f:subview>
					</m:div>


				</m:div>
				<!-- Start Drop down Information  -->
			</m:div>
		</m:section>
	</m:div>
</m:div>


