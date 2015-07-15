<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMContactManagementCase"
	var="msg" />


<f:view>
 <f:subview id="provService">
	<jsp:include page="/jsp/script/scripts.jsp" />
</f:subview>

<body onload="warnBeforeCancel();">

	<m:section id="logCaseAddAttachSec1" styleClass="otherbg">
		<m:legend id="logCaseAddAttachLeg1">
			<h:outputText id="crAttach_text"				value="#{msg['label.case.attachments.attachments']}" />
		</m:legend>

		<h:form id="caseAttachmentBrowse">
		<m:div id="logcaseAddAttachDiv01" styleClass="floatContainer">
			<m:div id="logcaseAddAttachDiv02" style="width:100%">
			<hx:scriptCollector id="scriptCollector1">

				<t:dataTable value="#{logCaseDataBean.attachmentVOList}"					
				             first="#{logCaseDataBean.attachmentsRowIndex}"					
				             columnClasses="columnClass" var="attachSpanResult"					
				             footerClass="footerClass" styleClass="dataTable" cellspacing="0"					
				             width="100%" border="0" headerClass="headerClass"					
				             rowClasses="row_alt,row" rows="3" id="attachmentsTable"					
				             onmouseover="return tableMouseOver(this, event);"					
				             onmouseout="return tableMouseOut(this, event);"
				             rowOnClick="javascript:childNodes[0].lastChild.click();">
					<t:column id="dateAddedCol1">
						<f:facet name="header">
							<h:panelGrid columns="2" id="logCaseAddAttachPGRD1">
								<h:outputLabel id="logCaseAddAttachLabel1" value="#{msg['label.case.attachment.dateAdded']}"									for="dateAddedCommandLink1" />

								<h:panelGroup id="logCaseAddAttachPGRp1">
									<t:commandLink id="dateAddedCommandLink1" onmousedown="javascript:flagWarn=false;"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}"										rendered="#{logcasedatabean.imagerender != 'dateaddedcommandlink1'}">
										<m:div id="logCaseAddAttachDiv1" title="for ascending" styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_DateAdded" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									<t:commandLink id="dateAddedCommandLink2" onmousedown="javascript:flagWarn=false;"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}"										rendered="#{logcasedatabean.imagerender != 'dateaddedcommandlink2'}">
										<m:div id="logCaseAddAttachDiv2" title="for descending" styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_DateAdded" />
										<f:attribute name="sortOrder" value="desc" />
										<%--<h:outputText id="logCaseAddAttachOutText2" value="#{attachSpanResult.dateAdded}" /> --%>
									</t:commandLink>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<%--Modified ESPRD00723864 starts  --%>
						<h:outputText id="addedByOutTxtIdaDDmODE" value="#{attachSpanResult.dateAdded}" />
						<%-- ESPRD00723864 ends  --%>
						
					</t:column>
					<t:column id="addedBycolumn2">
						<f:facet name="header">
							<h:panelGrid id="logCaseAddAttachPGRD2" columns="2">
								<h:outputLabel id="logCaseAddAttachLabel2" value="#{msg['label.case.attachment.addedBy']}" for="addedByCommandLink1" />
								<h:panelGroup id="logCaseAddAttachPGRp2">
									<t:commandLink id="addedByCommandLink1"	onmousedown="javascript:flagWarn=false;"									actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}"										rendered="#{logCaseDataBean.imageRender != 'addedByCommandLink1'}">
										<m:div id="logCaseAddAttachDiv3" title="for ascending" styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_AddedBy" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									<t:commandLink id="addedByCommandLink2"	onmousedown="javascript:flagWarn=false;"									actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}"										rendered="#{logCaseDataBean.imageRender != 'addedByCommandLink2'}">
										<m:div id="logCaseAddAttachDiv4" title="for descending" styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_AddedBy" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="logCaseAddAttachOutText3" value="#{attachSpanResult.addedBy}" />

					</t:column>
					<t:column id="fileNamecolumn3">
						<f:facet name="header">
							<h:panelGrid id="logCaseAddAttachPGRD3" columns="2">
								<h:outputLabel id="logCaseAddAttachLabel3" value="#{msg['label.case.attachment.fileName']}" for="fileNameCommandLink1" />
								<h:panelGroup id="logCaseAddAttachPGRp3">
									<t:commandLink id="fileNameCommandLink1" onmousedown="javascript:flagWarn=false;"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}"										rendered="#{logCaseDataBean.imageRender != 'fileNameCommandLink1'}">
										<m:div id="logCaseAddAttachDiv5" title="for ascending" styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_FileName" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									<t:commandLink id="fileNameCommandLink2" onmousedown="javascript:flagWarn=false;"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}"										rendered="#{logCaseDataBean.imageRender != 'fileNameCommandLink2'}">
										<m:div id="logCaseAddAttachDiv6" title="for descending" styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_FileName" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="logCaseAddAttachOutText4" value="#{attachSpanResult.fileName}" />
					</t:column>
					<t:column id="descriptioncolumn4">
						<f:facet name="header">
							<h:panelGrid id="logCaseAddAttachPGRD4" columns="2">
								<h:outputLabel id="logCaseAddAttachLabel4" value="Description" for="descriptionCommandLink1" />
								<h:panelGroup id="logCaseAddAttachPGRp4">
									<t:commandLink id="descriptionCommandLink1"	onmousedown="javascript:flagWarn=false;"									styleClass="textNone"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}"										rendered="#{logCaseDataBean.imageRender != 'descriptionCommandLink1'}">
										<m:div id="logCaseAddAttachDiv7" title="for ascending" styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_Desc" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									<t:commandLink id="descriptionCommandLink2"	onmousedown="javascript:flagWarn=false;"									styleClass="textNone"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}"										rendered="#{logCaseDataBean.imageRender != 'descriptionCommandLink2'}">
										<m:div id="logCaseAddAttachDiv8" title="for descending" styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_Desc" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="logCaseAddAttachOutText5" value="#{attachSpanResult.description}" />
					</t:column>

					<f:facet name="footer">
						<m:div id="nodata" rendered="#{logCaseDataBean.noData}"
							align="center">
							<h:outputText id="logCaseAddAttachOutText6" value="#{msg['label.attach.noData']}"></h:outputText>
						</m:div>
					</f:facet>
				</t:dataTable>
				<t:dataScroller id="CMGTTOMDS24" for="attachmentsTable" paginator="true"
					onclick="javascript:flagWarn=false;"
					paginatorActiveColumnStyle='font-weight:bold;'
					paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"
					pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"
					lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
					styleClass="scroller">
					<f:facet name="previous">
						<h:outputText id="logCaseAddAttachOutText7" styleClass="underline"							value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText id="logCaseAddAttachOutText8" styleClass="underline"							value=" #{ref['label.ref.gt']} "							rendered="#{pageIndex < pageCount}"></h:outputText>
					</f:facet>
					<h:outputText id="logCaseAddAttachOutText9" rendered="#{rowsCount > 0}"
						value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
						styleClass="scrollerBold" />
				</t:dataScroller>
                <m:br id="logCaseAddAttachBr1" />
				<m:div id="logCaseAddAttachDiv9" styleClass="moreInfoBar">
					<m:div id="logCaseAddAttachDiv10" styleClass="infoTitle">
						<h:outputText id="logCaseAddAttachOutText10"							value="#{msg['label.case.attachment.addAttachment']}" />
					</m:div>
					<m:div id="logCaseAddAttachDiv11" styleClass="infoActions">
						<t:commandLink id="PRGCMGTCL104" onmousedown="javascript:flagWarn=false;" action="#{logCaseControllerBean.uploadFile}">
							<h:outputText id="logCaseAddAttachOutText11" value="#{msg['label.case.save']}"								styleClass="strong" />
						</t:commandLink>
						<h:outputText id="logCaseAddAttachOutText12" escape="false"							value="#{ref['label.ref.linkSeperator']}" />
						
						<t:commandLink id="PRGCMGTCL105" onmousedown="javascript:flagWarn=false;" action="#{logCaseControllerBean.resetAttachments}">
							<h:outputText id="logCaseAddAttachOutText13" value="#{msg['label.case.reset']}"/>
						</t:commandLink>
						<h:outputText id="logCaseAddAttachOutText14" escape="false"							value="#{ref['label.ref.linkSeperator']}" />
						
						<%-- Added For The Defect ID ESPRD00335149 --%>
						<h:commandButton id="logCaseAddAttachButton1"																	style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana;font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"	action="#{logCaseControllerBean.cancelAttachments}"								value="#{msg['label.case.cancel']}" />
						<%-- Ends For The Defect ID ESPRD00335149 --%>
					</m:div>
				</m:div>
				    <m:div id="logCaseAddAttachDiv12" styleClass="moreInfo">
					<m:div id="logCaseAddAttachDiv13" styleClass="floatContainer">
						<%--<m:div id="logCaseAddAttachDiv14" styleClass="msgBox"
							rendered="#{logCaseDataBean.showSucessMessage}">
							<h:outputText id="logCaseAddAttachOutText15" value="SUCESS" />
						</m:div>--%>
						<m:div>
							<h:messages layout="table" showDetail="true" id="errorMessageWhenCaseAdded" 
							 			showSummary="false"	style="color: red">
							</h:messages>
						</m:div>							
							<m:table id="logCaseAddAttachTb1" width="100%">
								<m:tr id="logCaseAddAttachTr1">
									<m:td id="logCaseAddAttachTd1" colspan="2">
										<m:div id="logCaseAddAttachDiv15" styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="logCaseAddAttachOutText16" value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="logCaseAddAttachOutText17" value="File"	for="fileupload1"		 />
											<m:br id="logCaseAddAttachBr2" />
											<hx:fileupload styleClass="fileupload" id="fileupload1"
												value="#{logCaseDataBean.attachmentVO.file1}" size="100">
												<hx:fileProp name="fileName"
													value="#{logCaseDataBean.attachmentVO.fileName}" />
												<hx:fileProp name="contentType" />
											</hx:fileupload>
										</m:div>
										<m:br id="logCaseAddAttachBr3" />
										<m:div id="messageId2" rendered="#{!(logCaseDataBean.showVirusMessage || logCaseDataBean.showCaseMessage)}">
										<h:message id="logCaseAddAttachMssg1" for="fileupload1" styleClass="errorMessage" />
										</m:div>
									</m:td>
								</m:tr>
								
								<m:tr id="logCaseAddAttachTr2">
									<m:td id="logCaseAddAttachTd2" colspan="2">
										<m:div id="logCaseAddAttachDiv16" styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="logCaseAddAttachOutText18" value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="logCaseAddAttachLabel5" for="attaddDesc"												value="#{msg['label.attachment.description']}"												styleClass="outputLabel" />
											<m:br id="logCaseAddAttachBr4" />
											<h:inputText id="attaddDesc" size="100" value="#{logCaseDataBean.attachmentVO.description}" />
											<m:br id="logCaseAddAttachBr5" />
											<h:message id="logCaseAddAttachMssg2" for="attaddDesc" showDetail="true"												style="color: red" />
										</m:div>
									</m:td>
								</m:tr>
								
								<m:tr id="logCaseAddAttachTr3">
									<m:td id="logCaseAddAttachTd3">
										
											<m:span styleClass="required">
												<h:outputText id="logCaseAddAttachOutText19" value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="logCaseAddAttachLabel6" for="edmsdepartlevel">
												<h:outputText id="logCaseAddAttachOutText20" value="#{msg['label.case.edmsworkunitlevel']}"/>
											</h:outputLabel>
											<m:br id="logCaseAddAttachBr6" />
											<h:selectOneMenu id="edmsdepartlevel"												value="#{logCaseDataBean.attachmentVO.edmsWrkUnitLevel}">
												<f:selectItem itemValue=" " itemLabel="" />
												<f:selectItems value="#{logCaseDataBean.edmsWorkunitLevelList}" />
											</h:selectOneMenu>
											<m:br id="logCaseAddAttachBr7" />
											<h:message id="logCaseAddAttachMssg3" for="edmsdepartlevel" showDetail="true"												style="color: red" />
										
									</m:td>
									<m:td id="logCaseAddAttachTd4" align="left">
										
											<m:span styleClass="required">
												<h:outputText id="logCaseAddAttachOutText21" value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="logCaseAddAttachLabel7" for="edmsdoctype">
												<h:outputText id="logCaseAddAttachOutText22" value="#{msg['label.case.edmsdoctype']}"/>
											</h:outputLabel>
											<m:br id="logCaseAddAttachBr8" />
											<h:selectOneMenu id="edmsdoctype"												value="#{logCaseDataBean.attachmentVO.edmsDocType}">
												<f:selectItem itemValue=" " itemLabel="" />
												<f:selectItems value="#{logCaseDataBean.edmsDocTypeList}" />
											</h:selectOneMenu>
											<m:br id="logCaseAddAttachBr9" />
											<h:message id="logCaseAddAttachMssg4" for="edmsdoctype" showDetail="true"												style="color: red" />
										
									</m:td>
								</m:tr>
							</m:table>
						</m:div>
				</m:div>
			</hx:scriptCollector>
			</m:div>
			</m:div>
		</h:form>
		</m:section>
		</body>
</f:view>
