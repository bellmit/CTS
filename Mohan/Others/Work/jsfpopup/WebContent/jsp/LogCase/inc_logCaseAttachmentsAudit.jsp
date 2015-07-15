<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:section id="logCaseAttachmentsAuditSec1" styleClass="expand">
	<m:legend  id="logCaseAttachmentsAuditLeg1">
		<h:outputLink onclick="setHiddenValue('CMlogCase:caseAttachments:editAttachAudit:caseAttachAuditHiddenID','minus');	 		 toggle('showhide_AttachAudit',2);			 plusMinusToggle('showhide_AttachAudit',this,'CMlogCase:caseAttachments:editAttachAudit:caseAttachAuditHiddenID');			 return false;"               			 id="plusMinus_caseAttachAuditFalse" styleClass="plus" >
		<h:outputText id="caseAttachAudit_text" value="#{msg['label.audit']}" />
		</h:outputLink>
		<h:inputHidden id="caseAttachAuditHiddenID" value="#{logCaseDataBean.attachmentAuditOpen}" />			
	</m:legend>
	
	<m:div sid="showhide_AttachAudit">
		<m:table id="logCaseAttachmentsAuditTbl1" cellspacing="0" width="50%">
			<m:tr id="logCaseAttachmentsAuditTr1">
				<m:td id="logCaseAttachmentsAuditTd1" colspan="2">
					<h:outputText  id="logCaseAttachmentsAuditopt1" value="#{msg['label.audit.attachmentInfoTable']}" styleClass="strong" />
				</m:td>
			</m:tr>
			<m:tr id="logCaseAttachmentsAuditTr2" styleClass="mousepointer">
				<m:td id="logCaseAttachmentsAuditTd2" styleClass="dataLabel">
					<h:outputText  id="logCaseAttachmentsAuditopt2" value="#{msg['label.audit.lastUpdateDateTime']}"	styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseAttachmentsAuditTd3">
					<t:commandLink id="logCaseAttachmentsAuditCmdLnk1" action="#{logCaseControllerBean.showAttachmentAuditHistory}"  onmousedown="javascript:flagWarn=false;">
						<h:outputText id="logCaseAttachmentsAuditopt3" value="#{logCaseDataBean.attachmentVO.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr id="logCaseAttachmentsAuditTr3">
				<m:td id="logCaseAttachmentsAuditTd4" styleClass="dataLabel">
					<h:outputText id="logCaseAttachmentsAuditopt4" value="#{msg['label.audit.lastUpdatedUserID']}" styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseAttachmentsAuditTd5">
					<h:outputText  id="logCaseAttachmentsAuditopt5" value="#{logCaseDataBean.attachmentVO.auditUserID}"/>
					<m:br id="logCaseAttachmentsAuditBr1" />
					<m:br id="logCaseAttachmentsAuditBr2" />
				</m:td>
			</m:tr>
			<m:tr id="logCaseAttachmentsAuditTr4">
				<m:td id="logCaseAttachmentsAuditTd6" styleClass="dataLabel">
					<h:outputText  id="logCaseAttachmentsAuditopt6" value="#{msg['label.audit.originalEntryDate']}" styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseAttachmentsAuditTd7">
					<h:outputText  id="logCaseAttachmentsAuditopt7" value="#{logCaseDataBean.attachmentVO.addedAuditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</m:td>
			</m:tr>
			<m:tr id="logCaseAttachmentsAuditTr5">
				<m:td id="logCaseAttachmentsAuditTd8" styleClass="dataLabel">
					<h:outputText  id="logCaseAttachmentsAuditopt8" value="#{msg['label.audit.originalEntryUserID']}" styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseAttachmentsAuditTd9">
					<h:outputText  id="logCaseAttachmentsAuditopt9" value="#{logCaseDataBean.attachmentVO.addedAuditUserID}" />
				</m:td>
			</m:tr>
			<m:tr id="logCaseAttachmentsAuditTr6">
				<m:td id="logCaseAttachmentsAuditTd10" colspan="2">
					<m:div id="logCaseAttachmentsAuditdiv1" styleClass="clearl">
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
		<m:br id="logCaseAttachmentsAuditBr3" />
		<m:br id="logCaseAttachmentsAuditBr4" />
		<m:div id="log_detail_attach" styleClass=""
			rendered="#{logCaseDataBean.attachmentAuditHistoryRender}">
			<h:dataTable cellspacing="0" styleClass="dataTable"				id="attachHistoryTable" width="50%"				value="#{logCaseDataBean.attachmentAuditHistoryList}"				var="attachHistory">
				<h:column id="hiscolname_attach">
					<f:facet name="header">
						<h:outputText id="hcolname_attach"							value="#{msg['label.audit.fieldName']}" />
					</f:facet>
					<h:outputText  id="logCaseAttachmentsAuditopt10" value="#{attachHistory.columnName}" />
				</h:column>
				<h:column id="hisbefore_attach">
					<f:facet name="header">
						<h:outputText id="hbefore_attach"							value="#{msg['label.caseSteps.before']}" />
					</f:facet>
					<h:outputText id="logCaseAttachmentsAuditopt11" value="#{attachHistory.columnBeforeValue}" />
				</h:column>
				<h:column id="hisafter_attach">
					<f:facet name="header">
						<h:outputText id="hafter_attach"							value="#{msg['label.caseSteps.after']}" />
					</f:facet>
					<h:outputText id="logCaseAttachmentsAuditopt12" value="#{attachHistory.columnAfterValue}" />
				</h:column>
			</h:dataTable>
		</m:div>
	</m:div>
</m:section>
