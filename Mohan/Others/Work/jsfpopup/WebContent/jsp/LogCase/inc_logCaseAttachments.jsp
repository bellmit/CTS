<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<m:div id="logCaseAttachmntDiv1Id">
	<m:div id="logCaseAttachmntDiv2Id">
		<m:section styleClass="casebg" id="logCaseAttachmntSec1Id">
			<m:legend id="logCaseAttachmntLeg1Id">
				<h:outputLink					onclick="javascript:flagWarn=false;javascript:setHiddenValue('CMlogCase:caseAttachments:caseAttachHiddenID','minus');	 					 toggle('showhide_caseAttachments',2);						 plusMinusToggle('showhide_caseAttachments',this,'CMlogCase:caseAttachments:caseAttachHiddenID');						 return false;"					id="plusMinus_caseAttachFalse" styleClass="plus">
					<h:outputText id="caseAttach_text"						value="#{msg['label.case.attachments.attachments']}" />
				</h:outputLink>
				<h:inputHidden id="caseAttachHiddenID"					value="#{logCaseDataBean.caseAttachmentsHidden}" />
			</m:legend>
			<m:div sid="showhide_caseAttachments">
			       <m:div styleClass="padb" id="logCaseAttachmntDiv3Id">
						<m:div styleClass="msgBox" id="logCaseAttachmntDiv4Id"
							rendered="#{logCaseDataBean.showAttachmentDelMsg}">
							<h:outputText value="#{ent['label.workUnit.deleteMessage']}" id="logCaseAttachmntOutTxt1Id"/>
						</m:div>
				  <m:table width="100%" id="logCaseAttachmntTab1Id">
					<m:tr id="logCaseAttachmntTab1R1Id">
						<m:td styleClass="rightCell" id="logCaseAttachmntTab1R1C1Id">

								<t:commandLink
									action="#{logCaseControllerBean.renderAddAttachmentPage}"
									onmousedown="javascript:flagWarn=false;"
									styleClass="formBtn" id="addAttachment"
									disabled="#{logCaseDataBean.disableAddAttachment || logCaseDataBean.disableScreenField}">
									<h:outputText style="cursor:default;color:white;height:20px;text-align:center;padding:2px 1px 2px 1px;margin:0px 0 5px 5px;border-right: 2px solid #999999;text-decoration:none;width: 200px;" 
									rendered="#{!logCaseDataBean.disableAddAttachment && !logCaseDataBean.disableScreenField}" id="logCaseAddAttachmntOutTxt21Id"
										value="#{msg['label.case.attachment.addAttachment']}" />
									<h:outputText style="cursor:default;color:GrayText;height:20px;text-align:center;padding:2px 1px 2px 1px;margin:0px 0 5px 5px;border-right: 2px solid #999999;text-decoration:none;width: 200px;"  
									rendered="#{logCaseDataBean.disableAddAttachment || logCaseDataBean.disableScreenField}" id="logCaseAddAttachmntOutTxt22Id"
										value="#{msg['label.case.attachment.addAttachment']}" />
								</t:commandLink>
								<t:commandLink id="openDocumentRepositoryId"
									action="#{logCaseControllerBean.openDocumentRepository}"
									onmousedown="javascript:flagWarn=false;"
									styleClass="formBtn"
									disabled="#{logCaseDataBean.disableScreenField ||logCaseDataBean.disableSearchDocumentRepository}">
									<h:outputText style="cursor:default;color:white;height:20px;text-align:center;padding:2px 1px 2px 1px;margin:0px 0 5px 5px;border-right: 2px solid #999999;text-decoration:none;width: 200px;"
									rendered="#{!logCaseDataBean.disableSearchDocumentRepository && !logCaseDataBean.disableScreenField}" id="logCaseAttachmntOutTxt2Id" 
										value="#{msg['label.case.attachment.searchDocumentRepository']}" />
									<h:outputText style="cursor:default;color:GrayText;height:20px;text-align:center;padding:2px 1px 2px 1px;margin:0px 0 5px 5px;border-right: 2px solid #999999;text-decoration:none;width: 200px;"
									rendered="#{logCaseDataBean.disableSearchDocumentRepository || logCaseDataBean.disableScreenField}" id="logCaseAttachmntOutTxt21Id" 
										value="#{msg['label.case.attachment.searchDocumentRepository']}" />
									<f:param name="com.ibm.portal.propertybroker.action"
										value="sendLogCaseAction" id="logCaseAttachmntParam1Id" />
								</t:commandLink>

							</m:td>
					</m:tr>
				</m:table>
	
				<t:dataTable value="#{logCaseDataBean.attachmentVOList}"					
				             rendered="#{logCaseDataBean.showAttachmentDataTable}"					
				             first="#{logCaseDataBean.attachmentsRowIndex}" columnClasses="columnClass" 					
				             var="attachSpanResult" footerClass="footerClass" styleClass="dataTable" cellspacing="0"							
				             width="100%" border="0" headerClass="headerClass"							
				             rowClasses="row_alt,row" rows="10" id="attachmentsTable"	
				              rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"								
				             onmouseover="return tableMouseOver(this, event);"							
				             onmouseout="return tableMouseOut(this, event);"
				             rowOnClick="javascript:childNodes[0].lastChild.click();javascript:flagWarn=false;" 
				               onmousedown="javascript:flagWarn=false;focusThis('attach_desc1');focusPage('caseEditAttachmentsHeader');" 
				             rowIndexVar="index">
					<t:column id="dateAddedCol1">
						<f:facet name="header">
							<h:panelGrid columns="2" id="dtAddPanGrid">
								<h:outputLabel value="#{msg['label.case.attachment.dateAdded']}" id="dtAddOutLb"									for="dateAddedCommandLink1" />
								<h:panelGroup id="dtAddPanGrp">
									<t:div id="CaseAttchmentTDiv001" styleClass="alignLeft">
									<t:commandLink id="dateAddedCommandLink1" styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}"			 onmousedown="javascript:flagWarn=false;focusPage('caseAttachmentsHeader');"							rendered="#{logCaseDataBean.imageRender != 'dateAddedCommandLink1'}">
										<m:div title="#{msg['alt.for.ascending']}" id="dtAddAscImgId"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_DateAdded" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									
									<t:div id="CaseAttchmentTDiv002" styleClass="alignLeft">
									<t:commandLink id="dateAddedCommandLink2" styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseAttachmentsHeader');"										rendered="#{logCaseDataBean.imageRender != 'dateAddedCommandLink2'}">
										<m:div title="#{msg['alt.for.descending']}" id="dtAddDscImgId"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_DateAdded" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<t:commandLink action="#{logCaseControllerBean.viewAttachments}" onclick="javascript:flagWarn=false;"							 id="viewAttachMentId">
							<h:outputText value="#{attachSpanResult.dateAdded}" id="viewAttachMentOutTxtId"/>
								<f:param name="rowIndex" value="#{index}"></f:param> 
						</t:commandLink>
						<%--removed for defect ESPRD00512715 rendered="#{!logCaseDataBean.disableScreenField}"--%>
						<%--rendered="#{!logCaseDataBean.disableScreenField}" removed from the above link for the defect ESPRD00429773
							<h:outputText id="PRGCMGTOT626" value="#{attachSpanResult.dateAdded}"							rendered="#{logCaseDataBean.disableScreenField}" />--%>							
					</t:column>
				 	<t:column id="addedBycolumn2">
						<f:facet name="header">
							<h:panelGrid columns="2" id="addedByPanGrd">
								<h:outputLabel value="#{msg['label.case.attachment.addedBy']}" id="addedByOutLb"									for="addedByCommandLink1" />
								<h:panelGroup id="addedByPanGrp">
									<t:div id="CaseAttchmentTDiv003" styleClass="alignLeft">
									<t:commandLink id="addedByCommandLink1" styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseAttachmentsHeader');"										rendered="#{logCaseDataBean.imageRender != 'addedByCommandLink1'}">
										<m:div title="#{msg['alt.for.ascending']}" id="addedByAscImgId"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_AddedBy" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseAttchmentTDiv004" styleClass="alignLeft">
									<t:commandLink id="addedByCommandLink2" styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseAttachmentsHeader');"										rendered="#{logCaseDataBean.imageRender != 'addedByCommandLink2'}">
										<m:div title="#{msg['alt.for.descending']}" id="addedByDscImgId"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_AddedBy" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<t:commandLink action="#{logCaseControllerBean.viewAttachments}" onclick="javascript:flagWarn=false;"							 id="viewAttachMentId2">
							<h:outputText value="#{attachSpanResult.addedBy}" id="addedByOutTxtId"/>
							<f:param name="rowIndex" value="#{index}"></f:param> 
						</t:commandLink>
						<%--removed for defect ESPRD00512715 rendered="#{!logCaseDataBean.disableScreenField}"--%>
					<%--	<h:outputText value="#{attachSpanResult.addedBy}" id="addedByOutTxtId"/>--%>
					</t:column>
					<t:column id="fileNamecolumn3">
						<f:facet name="header">
							<h:panelGrid columns="2" id="FilNamPanGrdId">
								<h:outputLabel value="#{msg['label.case.attachment.fileName']}" id="FilNamOutLbId"									for="fileNameCommandLink1" />
								<h:panelGroup id="FilNamPanGrpId">
									<t:div id="CaseAttchmentTDiv005" styleClass="alignLeft">
									<t:commandLink id="fileNameCommandLink1" styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseAttachmentsHeader');"										rendered="#{logCaseDataBean.imageRender != 'fileNameCommandLink1'}">
										<m:div title="#{msg['alt.for.ascending']}" id="addedByfileNameAscImgId"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_FileName" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseAttchmentTDiv006" styleClass="alignLeft">
									<t:commandLink id="fileNameCommandLink2" styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}" onmousedown="javascript:flagWarn=false;focusPage('caseAttachmentsHeader');"										rendered="#{logCaseDataBean.imageRender != 'fileNameCommandLink2'}">
										<m:div title="#{msg['alt.for.descending']}" id="addedByfileNameDscImgId"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_FileName" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<t:commandLink action="#{logCaseControllerBean.viewAttachments}" onclick="javascript:flagWarn=false;"							 id="viewAttachMentId3">
							<h:outputText value="#{attachSpanResult.fileName}" id="FilNamOutTxtId"/>
							<f:param name="rowIndex" value="#{index}"></f:param> 
						</t:commandLink>
						<%--removed for defect ESPRD00512715 rendered="#{!logCaseDataBean.disableScreenField}"--%>
				<%--		<h:outputText value="#{attachSpanResult.fileName}" id="FilNamOutTxtId"/>--%>
					</t:column>
					<t:column id="descriptioncolumn4">
						<f:facet name="header">
							<h:panelGrid columns="2" id="descrPanGrdId">
								<h:outputLabel id="descrOutLbId"									value="#{msg['label.case.attachment.description']}"									for="descriptionCommandLink1" />
								<h:panelGroup id="descrPanGrpId">
									<t:div id="CaseAttchmentTDiv007" styleClass="alignLeft">
									<t:commandLink id="descriptionCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}" onmousedown="javascript:flagWarn=false;"										rendered="#{logCaseDataBean.imageRender != 'descriptionCommandLink1'}">
										<m:div title="#{msg['alt.for.ascending']}" id="addedByDescrtiptionAscImgId"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_Desc" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseAttchmentTDiv008" styleClass="alignLeft">
									<t:commandLink id="descriptionCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseAttachmentsInfo}" onmousedown="javascript:flagWarn=false;"										rendered="#{logCaseDataBean.imageRender != 'descriptionCommandLink2'}">
										<m:div title="#{msg['alt.for.descending']}" id="addedByDescrtiptionDscImgId"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_Desc" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<t:commandLink action="#{logCaseControllerBean.viewAttachments}" onclick="javascript:flagWarn=false;"							 id="viewAttachMentId4">
							<h:outputText value="#{attachSpanResult.description}" id="descrOutTxtId"/>
							<f:param name="rowIndex" value="#{index}"></f:param> 
						</t:commandLink>
						<%--removed for defect ESPRD00512715 rendered="#{!logCaseDataBean.disableScreenField}"--%>
			<%--			<h:outputText value="#{attachSpanResult.description}" id="descrOutTxtId"/>--%>
					</t:column>
				</t:dataTable>
				<t:dataScroller for="attachmentsTable" paginator="true"
					onclick="javascript:flagWarn=false;focusPage('caseAttachmentsHeader');"
					paginatorActiveColumnStyle='font-weight:bold;'
					paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"
					pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"
					lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
					styleClass="dataScroller" id="attachmentsTableDataScrol">
					<f:facet name="previous">
						<h:outputText styleClass="dataScrollerText" id="attachmentsTablePrev"							value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"></h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText styleClass="underline" id="attachmentsTableNxt"							value=" #{msg['label.greaterthan']} "							rendered="#{pageIndex < pageCount}"></h:outputText>
					</f:facet>
					<h:outputText rendered="#{rowsCount > 0}" id="attachmentsTableRowsCount"
						value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
						styleClass="dataScrollerText" />
				</t:dataScroller>
				<m:br id="attachmentsTableBr5"/>
				<h:dataTable var="dummycaseAttach"					rendered="#{!logCaseDataBean.showAttachmentDataTable}"					styleClass="dataTable" cellspacing="0" width="100%" border="0"					headerClass="tableHead" rowClasses="rowone,row_alt"					id="caseAttachdummyTable">
					<t:column id="dummycaseAttachcolumn1">
						<f:facet name="header">
							<h:outputText value="#{msg['label.case.attachment.dateAdded']}" id="caseAttachdummyTableDtAdded" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseAttachcolumn2">
						<f:facet name="header">
							<h:outputText value="#{msg['label.case.attachment.addedBy']}" id="caseAttachdummyTableaddedBy"/>
						</f:facet>
					</t:column>
					<t:column id="dummycaseAttachcolumn3">
						<f:facet name="header">
							<h:outputText value="#{msg['label.case.attachment.fileName']}" id="caseAttachdummyTableFileName"/>
						</f:facet>
					</t:column>
					<t:column id="dummycaseAttachcolumn4">
						<f:facet name="header">
							<h:outputText value="#{msg['label.case.attachment.description']}" id="caseAttachdummyTableDesc"/>
						</f:facet>
					</t:column>
				</h:dataTable>
				<m:table styleClass="dataTable" width="100%" border="0"
					rendered="#{!logCaseDataBean.showAttachmentDataTable}" id="noDataAttachId">
					<m:tr id="noDataAttachIdRow">
						<m:td align="center" id="noDataAttachIdCol">
							<h:outputText value="#{msg['value.noData']}" id="noAttachDatamsgId"/>
						</m:td>
					</m:tr>
				</m:table>
				<m:br />
				<m:div styleClass="clearl" id="afterAttachmentsDiv1id">
				</m:div>
				<m:div id="editAttachments"
					rendered="#{logCaseDataBean.showEditAttachments}">
					<m:anchor name="caseEditAttachmentsHeader"></m:anchor>
					
					<f:subview id="logCaseEditAttachmentId">
					<jsp:include page="inc_logCaseEditAttachments.jsp" />
					</f:subview>
				</m:div>
				
			</m:div>
			</m:div>
		</m:section>
	</m:div>
</m:div>
