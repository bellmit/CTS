<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />
<%--added for defect ID ESPRD00304517 --%>
<script type="text/javascript">
function submitClaimCase(id)
{
//alert('1')
if(confirm("Selecting this case will automatically assign\n the task to you.\n Do you wish to continue?")){
	return true;
}else{
	flagWarn=false;
	return false;
	}
}
</script>
<%--EOF defect ID ESPRD00304517 --%>
<m:div styleClass="moreInfo">
	<m:div styleClass="moreInfoBar">
		<m:div styleClass="infoTitle"
			rendered="#{myTaskDataBean.alertDetails}">
			<h:outputText id="PRGCMGTOT1641"
				value="#{messageBean.myTaskMap['label.caseDetails.alertdetails']}" />
		</m:div>
		<m:div styleClass="infoTitle"
			rendered="#{!myTaskDataBean.alertDetails}">
			<h:outputText id="PRGCMGTOT1642"
				value="#{messageBean.myTaskMap['label.caseDetails.casedetails']}" />
		</m:div>

		<m:div styleClass="infoActions">
			<h:panelGroup id="PRGCMGTPGP127">
				<t:commandLink id="PRGCMGTCL228" styleClass="commandLink"
					action="#{myTaskControllerBean.openLetterReq}"
					rendered="#{myTaskDataBean.alertDetails && myTaskDataBean.openLetterReq}">
					<h:outputText id="PRGCMGTOT1643"
						value="#{messageBean.myTaskMap['label.crDetails.openLetter']}" />
					<f:param name="Send.MyAlerts.AlertSk" value="SendMyAlertSk" />
				</t:commandLink>
				<h:outputText id="openLetterReqOP1"  escape="false"
					value="#{messageBean.prgMsgMap['label.ref.linkSeperator']}"
					rendered="#{myTaskDataBean.alertDetails && myTaskDataBean.openLetterReq}" />
				<t:commandLink id="PRGCMGTCL229" styleClass="strong"
					action="#{myTaskControllerBean.openCase}"
					rendered="#{myTaskDataBean.openCaseDets}">
					<h:outputText id="PRGCMGTOT1644"
						value="#{messageBean.myTaskMap['label.caseDetails.openCase']}" />
					<f:param name="Send.MyTask.CorrespondenceSk"
						value="SendMyTaskCorrespondenceSk" />
				</t:commandLink>


				<t:commandLink id="PRGCMGTCL230" styleClass="commandLink"
					rendered="#{myTaskDataBean.claimCaseDets}"
					action="#{myTaskControllerBean.submitClaimCase}" immediate="true"
					onclick="javascript:flagWarn=false;return submitClaimCase(this);">
					<h:outputText id="PRGCMGTOT1645"
						value="#{messageBean.myTaskMap['label.caseDetails.claimCaseRecord']}"></h:outputText>
					<%--Added for defect ESPRD00304474 --%>
					<f:param name="Send.MyTask.CorrespondenceSk"
						value="SendMyTaskCorrespondenceSk"></f:param>
				</t:commandLink>
				<%-- EOF defect ID ESPRD00304517 --%>
				<h:outputText id="PRGCMGTOT1646"  escape="false"
					value="#{messageBean.prgMsgMap['label.ref.linkSeperator']}" />
				<t:commandLink styleClass="commandLink" id="caseCancelId"
					action="#{myTaskControllerBean.caseCancel}">
					<h:outputText id="PRGCMGTOT1647"
						value="#{messageBean.myTaskMap['label.caseDetails.cancel']}"></h:outputText>
				</t:commandLink>

			</h:panelGroup>
		</m:div>

	</m:div>

	<m:div id="showHide_casedetail">
		<m:table id="PROVIDERMMT20120731164811640" border="0" cellpadding="2" cellspacing="2" width="100%">
			<m:tbody>
				<m:tr>
					<m:td colspan="4">
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1648" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.caseDetails.caseRcdNo']}" />
							<h:outputText id="PRGCMGTOT1649"
								value="#{myTaskDataBean.myTaskCaseDetailsVO.caseSK}" />
						</m:div>
					</m:td>

					</m:tr>

				<m:tr>
					<m:td>
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1654" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.caseDetails.caseType']}" />
							<h:outputText id="PRGCMGTOT1655"
								value="#{myTaskDataBean.myTaskCaseDetailsVO.caseType}" />
						</m:div>
					</m:td>
					<m:td>
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1650" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.caseDetails.caseTitle']}" />
							<h:outputText id="PRGCMGTOT1651"
								value="#{myTaskDataBean.myTaskCaseDetailsVO.caseTitle}" />
						</m:div>
					</m:td>
					<m:td>
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1656" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.caseDetails.openDate']}" />
							<h:outputText id="PRGCMGTOT1657"
								value="#{myTaskDataBean.myTaskCaseDetailsVO.createdDate}" />
						</m:div>
					</m:td>

					<m:td>
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1652" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.caseDetails.createdBy']}" />
							<h:outputText id="PRGCMGTOT1653"
								value="#{myTaskDataBean.myTaskCaseDetailsVO.createdBy}" />
						</m:div>
					</m:td>
				</m:tr>
				<m:tr>
					<m:td colspan="4">
						<m:div align="padb">
							<h:outputLabel id="PRGCMGTOLL646" for="case_notes"
								value="#{messageBean.myTaskMap['label.caseDetails.notes']}" />
						</m:div>
						<h:inputTextarea id="case_notes" cols="45"
							disabled="#{myTaskDataBean.disableNotes}" rows="5"
							value="#{myTaskDataBean.myTaskCaseDetailsVO.noteSet}">
						</h:inputTextarea>

					</m:td>
				</m:tr>
			</m:tbody>
		</m:table>
	</m:div>
</m:div>
