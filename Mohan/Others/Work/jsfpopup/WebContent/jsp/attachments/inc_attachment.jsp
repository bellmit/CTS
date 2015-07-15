<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<%-- <h:inputHidden id="PRGCMGTIH10" value="#{AttachmentControllerBean.edmsSearchResultsList}" /> --%>
<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CommonAttachment"
	var="msg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<%--t:saveState value="#{AttachmentDataBean.showEditAttachments}" />
<t:saveState id="CMGTTOMSS12" value="#{AttachmentDataBean.showAddAttachments}" />
<t:saveState id="CMGTTOMSS13" value="#{AttachmentDataBean.attachmentList}" />
<t:saveState id="CMGTTOMSS14" value="#{AttachmentDataBean.attachmentVO}" />
<t:saveState id="CMGTTOMSS15" value="#{AttachmentDataBean.attachmentList}" />
<t:saveState id="CMGTTOMSS16" value="#{AttachmentDataBean}" /--%>
<m:inputHidden value="#{AttachmentControllerBean.intialData}"></m:inputHidden>
<m:div>
	<m:div>
		<f:subview id="logCrspdAttachmentsSubview">
			<m:section id="PROVIDERMMS20120731164811141" styleClass="otherbg">
				<m:legend>
					<h:outputLink
						onclick="setHiddenValue('logCorrespondence:logCrspdAttmntsSubview:logCrspdAttachmentsSubview:attachmentsHidden','minus');toggle('log_div_attachments',2);							plusMinusToggle('log_div_attachments',this,'logCorrespondence:logCrspdAttmntsSubview:logCrspdAttachmentsSubview:attachmentsHidden');							 return false;"
						id="plusMinusAttachments" styleClass="plus">
						<h:outputText id="PRGCMGTOT232"
							value="#{msg['label.attach.attachment']}" />
					</h:outputLink>
					<h:inputHidden id="attachmentsHidden"
						value="#{CorrespondenceDataBean.attachmentsHidden}"></h:inputHidden>
				</m:legend>
				<m:div sid="log_div_attachments">
					<m:div styleClass="padb">
						<m:div styleClass="msgBox"
							rendered="#{AttachmentDataBean.showDelSucessMessage}">
							<h:outputText id="PRGCMGTOT233"
								value="#{ent['label.workUnit.deleteMessage']}" />
						</m:div>
						<m:table id="PROVIDERMMT20120731164811142" width="100%">
							<m:tr>
								<m:td styleClass="rightCell">
									<%-- added code for UC-PGM-CRM-054_ESPRD00374707_01APR2010--%>
									<%--removed rendered to display add attachment after WSRP changes --%>
									<h:commandButton value="#{msg['label.attach.addAttachment']}"
										onmousedown="javascript:flagWarn=false;focusThis(this.id);"
										action="#{AttachmentControllerBean.addAttachment}"
										styleClass="formBtn" id="addAttachment"
										disabled="#{CorrespondenceDataBean.crClosed ||!CorrespondenceDataBean.controlRequired }" />
									<%--Modified for defect ESPRD00495386--%>
									<%--    rendered="#{AttachmentControllerBean.appName == 'mmis'}"--%>
									<t:commandLink id="openDocumentRepositoryCorrId"
										onmousedown="javascript:flagWarn=false;focusThis(this.id);"
										action="#{AttachmentControllerBean.openDocumentRepository }"
										rendered="#{!CorrespondenceDataBean.crClosed && CorrespondenceDataBean.controlRequired}"
										styleClass="formBtn"
										style="cursor:default; color:white;height:20px;text-align:center;padding:2px 1px 2px 1px;margin:0px 0 5px 5px;border-right: 2px solid #999999;text-decoration:none;">
										<h:outputText id="PRGCMGTOT234"
											value="#{msg['label.attach.searchDocRep']}" />
										<f:param name="com.ibm.portal.propertybroker.action"
											value="SendEDMSSearchCriteriaDataType" />
									</t:commandLink>



									<h:commandButton value="#{msg['label.attach.searchDocRep']}"
										rendered="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
										action="#{AttachmentControllerBean.openDocumentRepository}"
										styleClass="formBtn" id="openDocumentRepositoryCorrId2"
										disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}" />
									<%--and commented below code for defect UC-PGM-CRM-054_ESPRD00374707_01APR2010
									<h:commandLink id="PRGCMGTCL46" rendered="#{!CorrespondenceDataBean.crClosed}"										action="#{AttachmentControllerBean.addAttachment}" >
										<h:outputText id="PRGCMGTOT235" value="#{msg['label.attach.addAttachment']}" />
									</h:commandLink>
									<h:outputText id="PRGCMGTOT236" value="#{msg['label.attach.addAttachment']}"										rendered="#{CorrespondenceDataBean.crClosed}" />
									<h:outputText id="PRGCMGTOT237" escape="false"										value="#{ref['label.ref.linkSeperator']}" />
										 
									<h:commandLink id="PRGCMGTCL47"										action="#{AttachmentControllerBean.openDocumentRepository}" onmousedown="javascript:flagWarn=false;"										rendered="#{!CorrespondenceDataBean.crClosed}">
										<h:outputText id="PRGCMGTOT238" value="#{msg['label.attach.searchDocRep']}" />
										<f:param name="com.ibm.portal.propertybroker.action"
											value="SendEDMSSearchCriteriaDataType" />
									</h:commandLink>
									<h:outputText id="PRGCMGTOT239" value="#{msg['label.attach.searchDocRep']}"										rendered="#{CorrespondenceDataBean.crClosed}" />
EOF UC-PGM-CRM-054_ESPRD00374707_01APR2010 --%>
								</m:td>
							</m:tr>
						</m:table>
						
						<t:dataTable value="#{AttachmentDataBean.attachmentList}"
							first="#{AttachmentDataBean.attachmentsRowIndex}"
							columnClasses="columnClass" var="attachSpanResult" rowIndexVar="rowIndex"
							footerClass="footerClass" styleClass="dataTable" cellspacing="0"
							width="100%" border="0" headerClass="headerClass"
							rowClasses="row_alt,row" rows="10" id="attachmentsTable"
							onmouseover="return tableMouseOver(this, event);"
							onmouseout="return tableMouseOut(this, event);">
							<h:column id="dateAddedCol1">
								<f:facet name="header">
									<h:panelGrid columns="2" id="attachmentsID1">
										<h:outputLabel value="#{msg['label.attach.dataAdded']}"
											id="attachmentsID2" for="dateAddedCommandLink1" />
										<h:panelGroup id="attachmentsID19">
											<t:div styleClass="alignLeft">
												<t:commandLink id="dateAddedCommandLink1"
													actionListener="#{AttachmentControllerBean.sortCorrespondenceAttachmentsInfo}"
													onmousedown="javascript:flagWarn=false;"
													rendered="#{AttachmentDataBean.imageRender != 'dateAddedCommandLink1'}">
													<%-- h:graphicImage tag is replaced by m:div for the defect ESPRD00707685 for all the columns of datatable --%>
													<%-- <h:graphicImage id="sortdescid1" alt="for ascending"
														styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
													<m:div id="sortdescid1" title="for ascending" styleClass="sort_asc" />
													<f:attribute name="columnName" value="Attach_DateAdded" />
													<f:attribute name="sortOrder" value="asc" />
												</t:commandLink>
											</t:div>
											<t:div styleClass="alignLeft">
												<t:commandLink id="dateAddedCommandLink2"
													actionListener="#{AttachmentControllerBean.sortCorrespondenceAttachmentsInfo}"
													onmousedown="javascript:flagWarn=false;"
													rendered="#{AttachmentDataBean.imageRender != 'dateAddedCommandLink2'}">
													<%-- <h:graphicImage id="sortdescid2" alt="for descending"
														styleClass="sort_desc" width="8" url="/images/x.gif" />--%>
													<m:div id="sortdescid2" title="for descending" styleClass="sort_desc" />
													<f:attribute name="columnName" value="Attach_DateAdded" />
													<f:attribute name="sortOrder" value="desc" />
												</t:commandLink>
											</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<t:commandLink id="attachmentsID3"
									onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
									action="#{AttachmentControllerBean.viewAttachments}">
									<h:outputText value="#{attachSpanResult.dateAdded}"
										id="attachmentsID4" />
									<f:param name="indexCode" value="#{rowIndex}"></f:param>
								</t:commandLink>
							</h:column>
							<h:column id="addedBycolumn2">
								<f:facet name="header">
									<h:panelGrid columns="2" id="attachmentsID5">
										<h:outputLabel value="#{msg['label.attach.addedBy']}"
											id="attachmentsID6" for="addedByCommandLink1" />
										<h:panelGroup id="attachmentsID18">
											<t:div styleClass="alignLeft">
												<t:commandLink id="addedByCommandLink1"
													actionListener="#{AttachmentControllerBean.sortCorrespondenceAttachmentsInfo}"
													onmousedown="javascript:flagWarn=false;"
													rendered="#{AttachmentDataBean.imageRender != 'addedByCommandLink1'}">
													<%-- <h:graphicImage id="sortdescid3" alt="for ascending"
														styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
													<m:div id="sortdescid3" title="for ascending" styleClass="sort_asc" />
													<f:attribute name="columnName" value="Attach_AddedBy" />
													<f:attribute name="sortOrder" value="asc" />
												</t:commandLink>
											</t:div>
											<t:div styleClass="alignLeft">
												<t:commandLink id="addedByCommandLink2"
													actionListener="#{AttachmentControllerBean.sortCorrespondenceAttachmentsInfo}"
													onmousedown="javascript:flagWarn=false;"
													rendered="#{AttachmentDataBean.imageRender != 'addedByCommandLink2'}">
													<%-- <h:graphicImage id="sortdescid4" alt="for descending"
														styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
													<m:div id="sortdescid4" title="for descending" styleClass="sort_desc" />
													<f:attribute name="columnName" value="Attach_AddedBy" />
													<f:attribute name="sortOrder" value="desc" />
												</t:commandLink>
											</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								
								<%--Code modified for fixing the defect ESPRD00744842--%>
								
								<t:commandLink id="attachmentsID7"
									onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
									action="#{AttachmentControllerBean.viewAttachments}">
									<h:outputText value="#{attachSpanResult.addedBy}" id="attachmentsID8" />
									<f:param name="indexCode" value="#{rowIndex}"></f:param>
								</t:commandLink>
							</h:column>
							<h:column id="fileNamecolumn3">
								<f:facet name="header">
									<h:panelGrid columns="2" id="attachmentsID9">
										<h:outputLabel value="#{msg['label.attach.fileName']}"
											id="attachmentsID10" for="fileNameCommandLink1" />
										<h:panelGroup id="attachmentsID17">
											<t:div styleClass="alignLeft">
												<t:commandLink id="fileNameCommandLink1"
													actionListener="#{AttachmentControllerBean.sortCorrespondenceAttachmentsInfo}"
													onmousedown="javascript:flagWarn=false;"
													rendered="#{AttachmentDataBean.imageRender != 'fileNameCommandLink1'}">
													<%-- <h:graphicImage id="sortdescid5" alt="for ascending"
														styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
													<m:div id="sortdescid5" title="for ascending" styleClass="sort_asc" />	
													<f:attribute name="columnName" value="Attach_FileName" />
													<f:attribute name="sortOrder" value="asc" />
												</t:commandLink>
											</t:div>
											<t:div styleClass="alignLeft">
												<t:commandLink id="fileNameCommandLink2"
													actionListener="#{AttachmentControllerBean.sortCorrespondenceAttachmentsInfo}"
													onmousedown="javascript:flagWarn=false;"
													rendered="#{AttachmentDataBean.imageRender != 'fileNameCommandLink2'}">
													<%--<h:graphicImage id="sortdescid6" alt="for descending"
														styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
													<m:div id="sortdescid6" title="for descending" styleClass="sort_desc" />
													<f:attribute name="columnName" value="Attach_FileName" />
													<f:attribute name="sortOrder" value="desc" />
												</t:commandLink>
											</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								
								<%--Code modified for fixing the defect ESPRD00744842--%>
								
								<t:commandLink id="attachmentsID11"
									onmousedown="javascript:flagWarn=false;focusThis(this.id);"
									action="#{AttachmentControllerBean.viewAttachments}">
									<h:outputText value="#{attachSpanResult.fileName}"	id="attachmentsID12" />
									<f:param name="indexCode" value="#{rowIndex}"></f:param>
								</t:commandLink>

							</h:column>
							<h:column id="descriptioncolumn4">
								<f:facet name="header">
									<h:panelGrid columns="2" id="attachmentsID13">
										<h:outputLabel id="PRGCMGTOLL133"
											value="#{msg['label.attach.description']}"
											for="descriptionCommandLink1" />
										<h:panelGroup id="attachmentsID16">
											<t:div styleClass="alignLeft">
												<t:commandLink id="descriptionCommandLink1"
													actionListener="#{AttachmentControllerBean.sortCorrespondenceAttachmentsInfo}"
													onmousedown="javascript:flagWarn=false;"
													rendered="#{AttachmentDataBean.imageRender != 'descriptionCommandLink1'}">
													<%--<h:graphicImage id="sortdescid7" alt="for ascending"
														styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
													<m:div id="sortdescid7" title="for ascending" styleClass="sort_asc" />
													<f:attribute name="columnName" value="Attach_Desc" />
													<f:attribute name="sortOrder" value="asc" />
												</t:commandLink>
											</t:div>
											<t:div styleClass="alignLeft">
												<t:commandLink id="descriptionCommandLink2"
													actionListener="#{AttachmentControllerBean.sortCorrespondenceAttachmentsInfo}"
													onmousedown="javascript:flagWarn=false;"
													rendered="#{AttachmentDataBean.imageRender != 'descriptionCommandLink2'}">
													<%-- <h:graphicImage id="sortdescid8" alt="for descending"
														styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
													<m:div id="sortdescid8" title="for descending" styleClass="sort_desc" />	
													<f:attribute name="columnName" value="Attach_Desc" />
													<f:attribute name="sortOrder" value="desc" />
												</t:commandLink>
											</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								
								<%--Code modified for fixing the defect ESPRD00744842--%>
								
								<t:commandLink id="attachmentsID14"
									onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
									action="#{AttachmentControllerBean.viewAttachments}">
									<h:outputText value="#{attachSpanResult.description}" id="attachmentsID15" />
									<f:param name="indexCode" value="#{rowIndex}"></f:param>
								</t:commandLink>
							</h:column>
							<%-- Snippet for  displaying  No Data --%>
							<f:facet name="footer">
								<m:div id="nodata" rendered="#{AttachmentDataBean.noData}"
									align="center">
									<h:outputText value="#{msg['label.attach.noData']}"
										id="attachmentsID20"></h:outputText>
								</m:div>
							</f:facet>
						</t:dataTable>
						<t:dataScroller id="CMGTTOMDS7" for="attachmentsTable"
							paginator="true" onclick="flagWarn=false;"
							paginatorActiveColumnStyle='font-weight:bold;'
							paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"
							pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"
							lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
							styleClass="scroller">
							<f:facet name="previous">
								<h:outputText id="PRGCMGTOT240" styleClass="underline"
									value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
							</f:facet>
							<f:facet name="next">
								<h:outputText id="PRGCMGTOT241" styleClass="underline"
									value=" #{ref['label.ref.gt']} "
									rendered="#{pageIndex < pageCount}"></h:outputText>
							</f:facet>
							<h:outputText id="PRGCMGTOT242" rendered="#{rowsCount > 0}"
								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								styleClass="scrollerBold" />
						</t:dataScroller>
						<m:br />
						<%-- Removed the dummy data table  --%>
						<m:div styleClass="clearl" id="blankImageID">
							<h:graphicImage id="PROVIDERGI20120731164811143" styleClass="blankImage" width="1" height="5"
								alt="" url="/images/x.gif" />
						</m:div>
						<f:subview id="editAttachments"
							rendered="#{AttachmentDataBean.showEditAttachments}">
							<jsp:include page="inc_editAttachment.jsp" />
						</f:subview>
						<%-- <f:subview id="addAttachments"
							rendered="#{AttachmentDataBean.showAddAttachments}">
							<jsp:include page="inc_addAttachment.jsp" />
						</f:subview> --%>
					</m:div>
				</m:div>
			</m:section>
		</f:subview>
	</m:div>
</m:div>
