<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>


<script>

//Code added as a part of Defect ESPRD00796178
//This will make sure that onmousedown attribute used will not call the same page/URL 
//2times (1 in new child browser(onclick event) and another in the same window (onmousedown event))
//This will set the page not to load with new URL & retain the focus
function openNewWindow(docURL) 
{
	var hrefComponentId = document.getElementById('logCaseEditAttachmentsOT23');
	if (hrefComponentId != null && hrefComponentId != undefined) 
	{
			hrefComponentId.href = "#logCaseEditAttachmentsdiv5";
			hrefComponentId.focus;
	}
		window.open(docURL, '_blank', 'width=400, height=450, resizable=yes');
}
function setURLToAttachment(event, docURL) 
{
	if (event.button == 2 || event.which == 3) 
	{
		var hrefComponentId = document.getElementById('logCaseEditAttachmentsOT23');
		if (hrefComponentId != null && hrefComponentId != undefined) 
		{
			hrefComponentId.href = docURL;
		}
	}
}
//Code ended for Defect ESPRD00796178
</script>

<m:div id="logcaseEditAttachDiv01" styleClass="floatContainer">
			<m:div id="logcaseEditAttachDiv02" style="width:100%">
<m:div id="logCaseEditAttachmentsdiv1" styleClass="moreInfo">
	<m:div id="logCaseEditAttachmentsdiv2" styleClass="moreInfoBar">
		<m:div id="logCaseEditAttachmentsdiv3" styleClass="infoTitle">
			<h:outputText  id="logCaseEditAttachmentsOT1" value="#{msg['label.case.attachment.editAttachment']}" />
		</m:div>
		<%-- Major changes done in edit attachment section with respect to defect ESPRD00774844 with activity Name : DEFECT_ESPRD00774844_CRM_UC-PGM-CRM-018.2_03-04-12
		     For Case record closed status,newly added Attachment, Detached Attachments for Normal user and Inquiry users.  --%>
		<m:div id="logCaseEditAttachmentsdiv4" styleClass="infoActions" rendered="#{!logCaseDataBean.caseStatusClosed && !logCaseDataBean.inInquiryModeFlag}">
			<%--<h:commandLink id="uploadFileId2" onmousedown="javascript:focusThis('attachmentsTable');" action="#{logCaseControllerBean.uploadFile}" rendered="#{!logCaseDataBean.disableAttachmentSave}">--%>
					<t:commandLink id="uploadFileId2"
						rendered="#{((logCaseDataBean.showAttachmentLink) && !logCaseDataBean.disableSaveAttachment) && !(logCaseDataBean.disableScreenField)}"
						onmousedown="javascript:focusThis('attachmentsTable');javascript:flagWarn=false;"
						action="#{logCaseControllerBean.uploadFile}">
						<h:outputText id="logCaseEditAttachmentsOT2"
							value="#{msg['label.case.save']}" styleClass="strong" />
					</t:commandLink>
					<h:outputText id="logCaseEditAttachmentsOT3"
						value="#{msg['label.case.save']}" styleClass="strong"
						rendered="#{logCaseDataBean.disableSaveAttachment || logCaseDataBean.disableScreenField}" />
					<h:outputText id="logCaseEditAttachmentsOT10" escape="false"
						value="&nbsp;" />
					<h:outputText id="logCaseEditAttachmentsOT11" value="|" />
					<h:outputText id="logCaseEditAttachmentsOT12" escape="false"
						value="&nbsp;" />
					<h:commandButton id="detachId2"
						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
						rendered="#{logCaseDataBean.attachmentVO.showDetachAttachment}"
						onclick="if (!confirm('Are you sure you want to Detach?')) return(false);"
						action="#{logCaseControllerBean.detachAttachment}"
						value="#{msg['label.case.attachment.detach']}"
						onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;"
						disabled="#{!logCaseDataBean.showAttachmentLink || logCaseDataBean.disableLogCaseSave}" />
					<h:commandButton id="deleteAttachmentId2"
						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
						rendered="#{!logCaseDataBean.attachmentVO.showDetachAttachment}"
						onclick="if (!confirm('Are you sure that you want to Delete?')) return(false);"
						action="#{logCaseControllerBean.deleteAttachment}"
						value="#{msg['label.case.delete']}"
						onmousedown="javascript:focusThis('attachmentsTable');javascript:flagWarn=false;"
						disabled="#{logCaseDataBean.disableAttachmentDelete}" />
					<h:outputText  id="logCaseEditAttachmentsOT13" escape="false" value="&nbsp;"/><h:outputText  id="logCaseEditAttachmentsOT14" value="|"/><h:outputText  id="logCaseEditAttachmentsOT15" escape="false" value="&nbsp;"/>
			<t:commandLink id="resetAttachmentsId2" onmousedown="javascript:focusThis('attachmentsTable');javascript:flagWarn=false;" action="#{logCaseControllerBean.resetAttachments}"  rendered="#{!logCaseDataBean.disableAttachmentReset && !(logCaseDataBean.disableScreenField)}">
				<h:outputText  id="logCaseEditAttachmentsOT16" value="#{msg['label.case.reset']}"/>
			</t:commandLink>
			<h:outputText  id="logCaseEditAttachmentsOT17" value="#{msg['label.case.reset']}" rendered="#{logCaseDataBean.disableAttachmentReset || logCaseDataBean.disableScreenField}"/>
			<h:outputText  id="logCaseEditAttachmentsOT4" escape="false" value="&nbsp;"/>
			<h:outputText  id="logCaseEditAttachmentsOT5" value="|"/>
			<h:outputText id="CMGTOT24" escape="false" value="&nbsp;"/>
			 <%--<h:commandLink id="cancelAttachmentsId2" onmousedown="javascript:focusThis('caseAttachmentsHeader');" action="#{logCaseControllerBean.cancelAttachments}" >
				<h:outputText  id="logCaseEditAttachmentsOT6" value="#{msg['label.case.close']}Rams"/>
			</h:commandLink> --%>
			<t:commandLink id="cancelAttachmentsId2" onmousedown="javascript:focusPage('caseAttachmentsHeader');" action="#{logCaseControllerBean.cancelAttachments}">
				<h:outputText id="logCaseEditAttachmentsOT6" value="#{msg['label.case.close']}" />
			</t:commandLink>
		</m:div>
		
		<m:div id="logCaseCLSDEditAttachmentsdiv4" styleClass="infoActions"
					rendered="#{logCaseDataBean.caseStatusClosed || logCaseDataBean.inInquiryModeFlag}">

					<h:outputText id="logCaseCLSDEditAttachmentsOT3"
						value="#{msg['label.case.save']}" styleClass="strong"/>
						<h:outputText id="logCaseCLSDEditAttachmentsOT10" escape="false"
						value="&nbsp;" />
					<h:outputText id="logCaseCLSDEditAttachmentsOT11" value="|" />
					<h:outputText id="logCaseCLSDEditAttachmentsOT12" escape="false"
						value="&nbsp;" />
						<h:commandButton id="CLSDdetachId2"
						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
						action="#{logCaseControllerBean.detachAttachment}"
						value="#{msg['label.case.attachment.detach']}"
						onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;"
						disabled="#{logCaseDataBean.caseStatusClosed || logCaseDataBean.inInquiryModeFlag}" />
						<h:outputText id="logCaseCLSDEditAttachmentsOT13" escape="false"
						value="&nbsp;" />
					<h:outputText id="logCaseCLSDEditAttachmentsOT14" value="|" />
					<h:outputText id="logCaseCLSDEditAttachmentsOT15" escape="false"
						value="&nbsp;" />
						<h:outputText id="logCaseCLSDEditAttachmentsOT17"
						value="#{msg['label.case.reset']}" />
					<h:outputText id="logCaseCLSDEditAttachmentsOT4" escape="false"
						value="&nbsp;" />
					<h:outputText id="logCaseCLSDEditAttachmentsOT5" value="|" />
					<h:outputText id="CLSDCMGTOT24" escape="false" value="&nbsp;" />
					<t:commandLink id="CLSDcancelAttachmentsId2"
						onmousedown="javascript:focusPage('caseAttachmentsHeader');"
						action="#{logCaseControllerBean.cancelAttachments}">
						<h:outputText id="logCaseCLSDEditAttachmentsOT6"
							value="#{msg['label.case.close']}" />
					</t:commandLink>
				</m:div>
	</m:div>
	<m:div id="logCaseEditAttachmentsdiv5" styleClass="moreInfoContent">
	<m:div id="logCaseEditAttachmentsdiv6"
		rendered="#{logCaseDataBean.showCaseAttachMessages}"
		styleClass="msgBox">
		<h:messages layout="table" showDetail="true" styleClass="successMsg"			id="editAttachErrorMessage" showSummary="false">
		</h:messages>
	</m:div>
		<m:div id="logCaseEditAttachmentsdiv7" styleClass="width:100%">
			<m:table id="logCaseEditAttachmentsTbl1" cellspacing="0" width="98%" rendered="#{!logCaseDataBean.caseStatusClosed && !logCaseDataBean.inInquiryModeFlag}">
				<m:tr id="logCaseEditAttachmentstr1">
					<m:td  id="logCaseEditAttachmentstd1">
						<m:div id="logCaseEditAttachmentsdiv8" styleClass="padb">
							<h:outputText  id="logCaseEditAttachmentsOT18" value="#{msg['label.case.attachment.dateAdded']}"								styleClass="outputLabel" />
							<m:br />
							<h:outputText  id="logCaseEditAttachmentsOT19" value="#{logCaseDataBean.attachmentVO.dateAdded}" />
						</m:div>
					</m:td>
					<m:td  id="logCaseEditAttachmentstd2">
						<m:div id="logCaseEditAttachmentsdiv9" styleClass="padb">
							<h:outputText  id="logCaseEditAttachmentsOT20" value="#{msg['label.case.attachment.addedBy']}"								styleClass="outputLabel" />
							<m:br id ="logCaseEditAttachmentsbr1" />
							<h:outputText  id="logCaseEditAttachmentsOT21" value="#{logCaseDataBean.attachmentVO.addedBy}" />
						</m:div>
					</m:td>
					<m:td  id="logCaseEditAttachmentstd3">
						<m:div id="logCaseEditAttachmentsdiv10" styleClass="padb" rendered="#{logCaseDataBean.showAttachmentLink}">
							<h:outputText  id="logCaseEditAttachmentsOT22" value="#{msg['label.case.attachment.fileName']}"								styleClass="outputLabel" />
							<m:br id ="logCaseEditAttachmentsbr2" />
							<%-- m:anchor 
									href="javascript:newWindow('#{logCaseDataBean.attachmentVO.fileUrl}')">
								<h:outputText  id="logCaseEditAttachmentsOT23" value="#{logCaseDataBean.attachmentVO.fileName}" />
							</m:anchor --%>
							<%--Modified <h:outputLabel> to <a> as a part of Defect ESPRD00796178 --%>
							<a href="#" id="logCaseEditAttachmentsOT23" style="COLOR: #00f;"
								onmousedown="setURLToAttachment(event, '#{logCaseDataBean.attachmentVO.fileUrl}');"
								onclick="openNewWindow('#{logCaseDataBean.attachmentVO.fileUrl}');return false;">
							    <h:outputText id="edmsValElement111" value="#{logCaseDataBean.attachmentVO.fileName}"></h:outputText>
							</a>
							<%--Modified code ended for Defect ESPRD00796178 --%>
						</m:div>
						<m:div id="logCaseEditAttachmentsdiv11" styleClass="padb" rendered="#{!logCaseDataBean.showAttachmentLink}">
							<h:outputText  id="logCaseEditAttachmentsOT24" value="#{msg['label.case.attachment.fileName']}"								styleClass="outputLabel" />
							<m:br id ="logCaseEditAttachmentsbr3" />
								<h:outputText  id="logCaseEditAttachmentsOT25" value="#{logCaseDataBean.attachmentVO.fileName}" />
						</m:div>
					</m:td>
					<m:td  id="logCaseEditAttachmentstd4">
						<m:div id="logCaseEditAttachmentsdiv12" styleClass="padb">
							<h:outputLabel id="CMGTOLL3" for="attach_desc1"								value="#{msg['label.case.attachment.description']}" />
							<m:br id ="logCaseEditAttachmentsbr4" />
							<h:inputText size="35" maxlength="35" id="attach_desc1" value="#{logCaseDataBean.attachmentVO.description}" disabled="#{!logCaseDataBean.showAttachmentLink || logCaseDataBean.disableScreenField}"/>
						</m:div>
					</m:td>
				</m:tr>
				
				<m:tr id="logCaseEditAttachmentstr2">
				 
			<%--	<m:td  id="logCaseEditAttachmentstd5">
						<m:div id="logCaseEditAttachmentsdiv13" styleClass="padb">
							<h:outputText  id="logCaseEditAttachmentsOT26" value="#{msg['label.case.edmsworkunitlevel']}"								styleClass="outputLabel" />
							<m:br id ="logCaseEditAttachmentsbr5" />
							<h:outputText  id="logCaseEditAttachmentsOT27" value="#{logCaseDataBean.attachmentVO.edmsWrkUnitLevel}" />
						</m:div>
					</m:td>
					<m:td  id="logCaseEditAttachmentstd6">
						<m:div id="logCaseEditAttachmentsdiv14" styleClass="padb">
							<h:outputText  id="logCaseEditAttachmentsOT28" value="#{msg['label.case.edmsdoctype']}"								styleClass="outputLabel" />
							<m:br id ="logCaseEditAttachmentsbr6" />
							<h:outputText  id="logCaseEditAttachmentsOT29" value="#{logCaseDataBean.attachmentVO.edmsDocType}" />
						</m:div>
					</m:td>--%>
					<m:td>
							<m:div styleClass="padb" id="logCaseEditAttachmentsdivInd1" rendered="#{logCaseDataBean.showAttachmentLink}">
								<h:outputText id="dtaddCaseAttached1"									value="Attached" 									styleClass="outputLabel" />
								<m:br />
								<h:outputText id="dtaddCaseAttached2"									value="YES" />
							</m:div>
							<m:div styleClass="padb" id="logCaseEditAttachmentsdivInd2" rendered="#{!logCaseDataBean.showAttachmentLink}">
								<h:outputText id="dtaddCaseDetached1"									value="Attached"									styleClass="outputLabel" />
								<m:br />
								<h:outputText id="dtaddCaseDetached2"									value="NO" />
							</m:div>
						</m:td>
			  </m:tr>
			</m:table>

					<m:table id="logCaseCLSDEditAttachmentsTbl1" cellspacing="0"
						width="98%" rendered="#{logCaseDataBean.caseStatusClosed || logCaseDataBean.inInquiryModeFlag}">
						<m:tr id="logCaseCLSDEditAttachmentstr1">
							<m:td id="logCaseCLSDEditAttachmentstd1">
								<m:div id="logCaseCLSDEditAttachmentsdiv8" styleClass="padb">
									<h:outputText id="logCaseCLSDEditAttachmentsOT18"
										value="#{msg['label.case.attachment.dateAdded']}"
										styleClass="outputLabel" />
									<m:br />
									<h:outputText id="logCaseCLSDEditAttachmentsOT19"
										value="#{logCaseDataBean.attachmentVO.dateAdded}" />
								</m:div>
							</m:td>
							<m:td id="logCaseCLSDEditAttachmentstd2">
								<m:div id="logCaseCLSDEditAttachmentsdiv9" styleClass="padb">
									<h:outputText id="logCaseCLSDEditAttachmentsOT20"
										value="#{msg['label.case.attachment.addedBy']}"
										styleClass="outputLabel" />
									<m:br id="logCaseCLSDEditAttachmentsbr1" />
									<h:outputText id="logCaseCLSDEditAttachmentsOT21"
										value="#{logCaseDataBean.attachmentVO.addedBy}" />
								</m:div>
							</m:td>
							<m:td id="logCaseCLSDEditAttachmentstd3">
								<m:div id="logCaseCLSDEditAttachmentsdiv10" styleClass="padb"
									rendered="#{logCaseDataBean.showAttachmentLink}">
									<h:outputText id="logCaseCLSDEditAttachmentsOT22"
										value="#{msg['label.case.attachment.fileName']}"
										styleClass="outputLabel" />
									<m:br id="logCaseCLSDEditAttachmentsbr2" />

									<h:outputLabel id="logCaseCLSDEditAttachmentsOT23"
										for="logCaseCLSDEditAttachmentsOT22"
										value="#{logCaseDataBean.attachmentVO.fileName}"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										onclick="openNewWindow('#{logCaseDataBean.attachmentVO.fileUrl}');return false;" />
								</m:div>
								<m:div id="logCaseCLSDEditAttachmentsdiv11" styleClass="padb"
									rendered="#{!logCaseDataBean.showAttachmentLink}">
									<h:outputText id="logCaseCLSDEditAttachmentsOT24"
										value="#{msg['label.case.attachment.fileName']}"
										styleClass="outputLabel" />
									<m:br id="logCaseCLSDEditAttachmentsbr3" />
									<h:outputText id="logCaseCLSDEditAttachmentsOT25"
										value="#{logCaseDataBean.attachmentVO.fileName}" />
								</m:div>
							</m:td>
							<m:td id="logCaseCLSDEditAttachmentstd4">
								<m:div id="logCaseCLSDEditAttachmentsdiv12" styleClass="padb">
									<h:outputLabel id="CLSDCMGTOLL3" for="CLSDattach_desc1"
										value="#{msg['label.case.attachment.description']}" />
									<m:br id="logCaseCLSDEditAttachmentsbr4" />
									<h:inputText size="35" maxlength="35" id="CLSDattach_desc1"
										value="#{logCaseDataBean.attachmentVO.description}"
										disabled="#{logCaseDataBean.caseStatusClosed || logCaseDataBean.disableScreenField}" />
								</m:div>
							</m:td>
						</m:tr>
						<m:tr id="logCaseCLSDEditAttachmentstr2">
							<m:td>
								<m:div styleClass="padb" id="logCaseCLSDEditAttachmentsdivInd1"
									rendered="#{logCaseDataBean.showAttachmentLink}">
									<h:outputText id="CLSDdtaddCaseDetached1" value="Attached"
										styleClass="outputLabel" />
									<m:br />
									<h:outputText id="CLSDdtaddCaseDetached2" value="YES" />
								</m:div>
								<m:div styleClass="padb" id="logCaseCLSDEditAttachmentsdivInd2"
									rendered="#{!logCaseDataBean.showAttachmentLink}">
									<h:outputText id="CLSDdtaddCaseDetached3" value="Attached"
										styleClass="outputLabel" />
									<m:br />
									<h:outputText id="CLSDdtaddCaseDetached4" value="NO" />
								</m:div>
							</m:td>
						</m:tr>
					</m:table>
					<m:div id="editAttachAuditDiv" rendered="#{logCaseDataBean.enableAuditLogForLogCase}">
			<f:subview id="editAttachAudit">
	<%--CR_ESPRD00373565_LOGCASE_23JUL2010	<jsp:include page="inc_logCaseAttachmentsAudit.jsp" />--%>
											<audit:auditTableSet id="attachmentAuditId"
												value="#{logCaseDataBean.attachmentVO.auditKeyList}"
												numOfRecPerPage="10">
											</audit:auditTableSet>
			</f:subview> 
</m:div><%--EOf CR_ESPRD00373565_LogCase_04AUG2010 --%>
		</m:div>
	</m:div>
</m:div>
</m:div>
</m:div>
