<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<f:verbatim>
<script>
	
//Code added as a part of Defect ESPRD00796178
//This will make sure that onmousedown attribute used will not call the same page/URL 
//2times (1 in new child browser(onclick event) and another in the same window (onmousedown event))
//This will set the page not to load with new URL & retain the focus
function openNewWindow(docURL) 
{
	var hrefComponentId = document.getElementById('attachFileURLLink');
	if (hrefComponentId != null && hrefComponentId != undefined) 
	{
			hrefComponentId.href = "#editAttchment123";
			hrefComponentId.focus;
	}
		window.open(docURL, '_blank', 'width=400, height=450, resizable=yes');
}
function setURLToAttachment(event, docURL) 
{
	if (event.button == 2 || event.which == 3) 
	{
		var hrefComponentId = document.getElementById('attachFileURLLink');
		if (hrefComponentId != null && hrefComponentId != undefined) 
		{
			hrefComponentId.href = docURL;
		}
	}
}
//Code ended for Defect ESPRD00796178
</script>
</f:verbatim>

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />
<t:saveState id="CMGTTOMSS34" value="#{AttachmentDataBean.attachmentIndex}" />

	<m:div styleClass="moreInfoBar">
	
		<m:div styleClass="infoTitle">
			<h:outputText id="PRGCMGTOT256"
				value="#{msg['label.attach.editAttachment']}" />
		</m:div>
		<%--Fixed for Defect ESPRD00707689....start --%>

			<m:div styleClass="infoActions">
			
			<t:commandLink id="PRGCMGTCB3"
					rendered="#{(!CorrespondenceDataBean.crClosed) && (!(AttachmentDataBean.disableSave || !CorrespondenceDataBean.controlRequired))}"
					action="#{AttachmentControllerBean.saveEditedAttachment}"
					styleClass="strong"
					onmousedown="javascript:flagWarn=false;"
					value="#{msg['label.attach.save']}" />
				<h:outputText id="SAVE123ID" styleClass="strong"
					rendered="#{(AttachmentDataBean.disableSave || !CorrespondenceDataBean.controlRequired)}"
					value="#{msg['label.attach.save']}" />
					
					<h:outputText id="PRGCMGTOT258" escape="false"
					value="#{ref['label.ref.linkSeperator']}" />
					
				<t:commandLink id="PRGCMGTCB4"
					onclick="if (!(confirm('Are you sure you want to Detach?'))) return(false);"
					onmousedown="javascript:flagWarn=false;"
					value="#{msg['label.attach.detach']}"
					action="#{AttachmentControllerBean.detachEditedAttachment}"
					rendered="#{!AttachmentDataBean.attachmentVO.newAttachment && !(AttachmentDataBean.disableDetach || CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired)}" />
					
				<h:outputText id="DETACH123ID" escape="false"
					value="#{msg['label.attach.detach']}" rendered="#{!(AttachmentDataBean.attachmentVO.newAttachment )&& (AttachmentDataBean.disableDetach || CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired) }"/>
				
				<t:commandLink id="PRGCMGTCB5"
					onclick="if (!(confirm('Are you sure you want to Delete?'))) return(false);"
					onmousedown="javascript:flagWarn=false;"
					value="#{msg['label.attach.delete']}"
					action="#{AttachmentControllerBean.deleteEditedAttachment}"
					rendered="#{AttachmentDataBean.attachmentVO.newAttachment && !(AttachmentDataBean.disableDetach || CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired)}" />
					
					<h:outputText id="DELETE123ID" escape="false"
					value="#{msg['label.attach.delete']}" rendered="#{(AttachmentDataBean.attachmentVO.newAttachment )&& (AttachmentDataBean.disableDetach || CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired) }"/>
				
				<h:outputText id="LINKSEPERATOR123ID" escape="false"
					value="#{ref['label.ref.linkSeperator']}" />
			
			
				<t:commandLink id="PROVIDERCL20120731164811147" onmousedown="javascript:flagWarn=false;"
					rendered="#{CorrespondenceDataBean.controlRequired}"
					action="#{AttachmentControllerBean.resetEditAttachment}"
					value="#{msg['label.attach.reset']}"/>
				<h:outputText id="RESET123" escape="false"
					value="#{msg['label.attach.reset']}" rendered="#{!CorrespondenceDataBean.controlRequired}"/>
			

			
				<h:outputText id="PRGCMGTOT259" escape="false"
					value="#{ref['label.ref.linkSeperator']}" />
					
				
				
				<t:commandLink id="attchCloseId" immediate="true"
					action="#{AttachmentControllerBean.cancelAttachments}"
					onclick="if (!confirm('Are you sure that you want to Close?')) return(false);"
					onmousedown="javascript:flagWarn=false;"
					value="#{msg['label.attach.close']}" />
			

			
				

			
				<%--<h:commandButton id="PRGCMGTCB6"
					onmousedown="javascript:flagWarn=false;"
					disabled="#{!CorrespondenceDataBean.controlRequired}"
					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing:1px;"
					action="#{AttachmentControllerBean.resetEditAttachment}"
					value="#{msg['label.attach.reset']}" /> --%>
					
			
			


			
				<%--<h:commandButton id="PRGCMGTCB4"
					disabled="#{AttachmentDataBean.disableDetach || CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing:1px;"
					onclick="if (!(confirm('Are you sure you want to Detach?'))) return(false);"
					onmousedown="javascript:flagWarn=false;"
					value="#{msg['label.attach.detach']}"
					action="#{AttachmentControllerBean.detachEditedAttachment}"
					rendered="#{!AttachmentDataBean.attachmentVO.newAttachment}" /> --%>
				
			<%--<h:commandButton id="PRGCMGTCB5"
					disabled="#{AttachmentDataBean.disableDetach || CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing:1px;"
					onclick="if (!(confirm('Are you sure you want to Delete?'))) return(false);"
					onmousedown="javascript:flagWarn=false;"
					value="#{msg['label.attach.delete']}"
					action="#{AttachmentControllerBean.deleteEditedAttachment}"
					rendered="#{AttachmentDataBean.attachmentVO.newAttachment}" /> --%>
					
					
			

			
			
			

			
				<%--<h:commandButton id="PRGCMGTCB3"
					rendered="#{!CorrespondenceDataBean.crClosed}"
					disabled="#{AttachmentDataBean.disableSave || !CorrespondenceDataBean.controlRequired}"
					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:32px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing:1px;"
					action="#{AttachmentControllerBean.saveEditedAttachment}"
					onmousedown="javascript:flagWarn=false;"
					value="#{msg['label.attach.save']}" /> --%>
					
				
			
		</m:div>


		<%--Fixed for Defect ESPRD00707689....end --%>
	</m:div>
	
	<m:div id="editAttchment123" styleClass="moreInfoContent">
		<m:div styleClass="padb">
			<m:div styleClass="msgBox"
				rendered="#{AttachmentDataBean.showSucessMessage}">
				<h:outputText id="PRGCMGTOT261" value="#{ent['label-sw-success']}" />
			</m:div>
			<m:div styleClass="width:100%">
				<m:table id="PROVIDERMMT20120731164811148" cellspacing="0" width="98%">
					<m:tr>
						<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="PRGCMGTOLL134" for="dtAdd"									value="#{msg['label.attach.dataAdded']}"									styleClass="outputLabel" />
								<m:br />
								<h:outputText id="dtAdd"									value="#{AttachmentDataBean.attachmentVO.dateAdded}" />
							</m:div>
						</m:td>
						<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="PRGCMGTOLL135" for="dtaddBy"									value="#{msg['label.attach.addedBy']}" styleClass="outputLabel" />

								<m:br />
								<h:outputText id="dtaddBy"									value="#{AttachmentDataBean.attachmentVO.addedBy}" />
									
							</m:div>
						</m:td>
						<m:td>
							<m:div styleClass="padb" rendered="#{!AttachmentDataBean.disableDetach}">
								<h:outputLabel id="PRGCMGTOLL136" for="edmsValElement1"									value="#{msg['label.attach.fileName']}"									styleClass="outputLabel" />
								<m:br />
								<%--<h:commandLink action="#{AttachmentControllerBean.downLoadFile}" 										immediate="true" id="downloadfile">
									<h:outputText id="flNam"										value="#{AttachmentDataBean.attachmentVO.fileName}" />
								</h:commandLink>--%>
								<%-- m:anchor
									href="javascript:newWindow('#{AttachmentDataBean.attachmentVO.fileUrl}')">
									<h:outputText id="edmsValElement1"										value="#{AttachmentDataBean.attachmentVO.fileName}" />
								</m:anchor --%>
							<%--Modified <h:outputLabel> to <a> as a part of Defect ESPRD00796178 --%>
							<a href="#" id="attachFileURLLink" style="COLOR: #00f;" 
								onmousedown="setURLToAttachment(event, '#{AttachmentDataBean.attachmentVO.fileUrl}');"
								onclick="openNewWindow('#{AttachmentDataBean.attachmentVO.fileUrl}');return false;">
							    <h:outputText id="edmsValElement11"	value="#{AttachmentDataBean.attachmentVO.fileName}"></h:outputText>
							</a>
							<%--Modified code ended for Defect ESPRD00796178 --%>
						</m:div>
							<m:div styleClass="padb" rendered="#{AttachmentDataBean.disableDetach}">
								<h:outputLabel id="PRGCMGTOLL137" for="edmsValElement12"									value="#{msg['label.attach.fileName']}"									styleClass="outputLabel" />
								<m:br />
								<h:outputText id="edmsValElement13"										value="#{AttachmentDataBean.attachmentVO.fileName}" />
							</m:div>
						</m:td>
						<m:td>
							<m:div styleClass="padb">
								<m:span styleClass="required">
										<h:outputText id="PRGCMGTOT262" value="#{ref['label.ref.reqSymbol']}" />
								</m:span>
								<h:outputLabel id="PRGCMGTOLL138" for="attaddDesc"									value="#{msg['label.attach.description']}" />
								<m:br />
								<h:inputText size="50" id="attaddDesc"									value="#{AttachmentDataBean.attachmentVO.description}"									disabled="#{AttachmentDataBean.showDescReadonly}" />
								<m:br></m:br>
								<h:message id="PRGCMGTM51" for="attaddDesc" showDetail="true"												style="color: red" />
							</m:div>
						</m:td>
					</m:tr>
					<m:tr>
						<%--<m:td>
							<m:div styleClass="padb">
								
								<h:outputLabel id="PRGCMGTOLL139" for="edmswrkunitlevel"									value="#{msg['label.edms.wrkunitlevel']}"									styleClass="outputLabel" />
								<m:br />						
									<h:selectOneMenu id="edmswrkunitlevel" disabled="#{AttachmentDataBean.attachmentTrueVar}"										value="#{AttachmentDataBean.attachmentVO.edmsWrkUnitLevel}">
										<f:selectItems value="#{AttachmentDataBean.edmsWrkUnitLevelList}" />			
									</h:selectOneMenu>
							</m:div>							
						</m:td> --%>
						<%--<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="PRGCMGTOLL140" for="edmsdoctype"									value="#{msg['label.edms.docType']}"									styleClass="outputLabel" />
								<m:br />
								<h:selectOneMenu id="edmsdoctype" disabled="#{AttachmentDataBean.attachmentTrueVar}"										value="#{AttachmentDataBean.attachmentVO.edmsDocType}">
										<f:selectItems value="#{AttachmentDataBean.edmsDocTypeList}" />			
									</h:selectOneMenu>
							</m:div>
						</m:td>	--%>				
					</m:tr>
					<m:tr>
						<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="PRGCMGTOLL141" for="dtadd"									value="#{msg['label.attach.attached']}"									styleClass="outputLabel" />

								<m:br />
								<h:outputText id="dtadd"									value="#{AttachmentDataBean.attachmentVO.attachmentIndicator}" />
							</m:div>
						</m:td>
					</m:tr>
				</m:table>
				
			<%--Audit log --%>	
			
		    <%--<f:subview id="editAttachAudit_cr">
				<jsp:include page="inc_CRAttachmentAuditLog.jsp" />
			</f:subview> --%>

			<m:div
				rendered="#{not empty AttachmentDataBean.attachmentVO.attachmentSK}">
				<f:subview id="editAttachAudit_cr">
					<m:div rendered="#{CorrespondenceDataBean.auditLogFlag}">
						<audit:auditTableSet id="attchmtAuditId"
							value="#{AttachmentDataBean.attachmentVO.auditKeyList}"
							numOfRecPerPage="10"></audit:auditTableSet>
					</m:div>

				</f:subview>
			</m:div>
			
			</m:div>
		</m:div>
</m:div>
