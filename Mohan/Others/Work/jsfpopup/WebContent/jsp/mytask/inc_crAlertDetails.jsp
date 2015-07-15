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

<m:div styleClass="moreInfo">
	<m:div styleClass="moreInfoBar">
		<m:div styleClass="infoTitle"
			rendered="#{myTaskDataBean.alertDetails}">
			<h:outputText id="PRGCMGTOT1666"
				value="#{messageBean.myTaskMap['label.crDetails.alertDetails']}" />
		</m:div>
		<m:div styleClass="infoTitle"
			rendered="#{!myTaskDataBean.alertDetails}">
			<h:outputText id="PRGCMGTOT1667"
				value="#{messageBean.myTaskMap['label.crDetails.crDetails']}" />
		</m:div>

		<m:div styleClass="infoActions">
			<h:panelGroup id="PRGCMGTPGP128">
				<t:commandLink id="PRGCMGTCL237" styleClass="commandLink"
					action="#{myTaskControllerBean.openLetterReq}"
					rendered="#{myTaskDataBean.alertDetails && myTaskDataBean.openLetterReq}">
					<h:outputText id="PRGCMGTOT1668"
						value="#{messageBean.myTaskMap['label.crDetails.openLetter']}" />
					<f:param name="Send.MyAlerts.AlertSk" value="SendMyAlertSk" />
				</t:commandLink>
				<h:outputText id="PROVIDEROLT20120731164811641" escape="false"
					value="#{messageBean.prgMsgMap['label.ref.linkSeperator']}"
					rendered="#{myTaskDataBean.alertDetails && myTaskDataBean.openLetterReq}" />

				<t:commandLink id="PRGCMGTCL238" styleClass="strong"
					action="#{myTaskControllerBean.openCR}"
					rendered="#{myTaskDataBean.openCrDets}">
					<h:outputText id="PRGCMGTOT1669"
						value="#{messageBean.myTaskMap['label.crDetails.openCR']}" />
					<f:param name="Send.MyTask.CorrespondenceSk"
						value="SendMyTaskCorrespondenceSk" />
				</t:commandLink>

				<t:commandLink id="PRGCMGTCL239" styleClass="strong"  
					onclick="if (!confirm('Selecting this Correspondence Record will automatically assign the task to you. Do you wish to continue?')) return(false);" 
					action="#{myTaskControllerBean.claimCR}"
					rendered="#{!myTaskDataBean.openCrDets}">
					<h:outputText id="PRGCMGTOT1670"
						value="#{messageBean.myTaskMap['label.crDetails.claimCR']}" />
					<f:param name="Send.MyTask.CorrespondenceSk"
						value="SendMyTaskCorrespondenceSk" />
				</t:commandLink>
				<%--
				<m:inputHidden name="Send.MyTask.CorrespondenceSk"
					value="SendMyTaskCorrespondenceSk" />

				<h:commandButton id="PRGCMGTCB84"					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:60px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"					onclick="if (!(confirm('Are you sure you want to Claim the CR?'))) return(false);"					value="#{messageBean.myTaskMap['label.crDetails.claimCR']}"					rendered="#{!myTaskDataBean.openCrDets}"					action="#{myTaskControllerBean.claimCR}">
					
				</h:commandButton>--%>



				<h:outputText id="PRGCMGTOT1671" escape="false"
					value="#{messageBean.prgMsgMap['label.ref.linkSeperator']}" />

				<t:commandLink styleClass="commandLink"
					action="#{myTaskControllerBean.cancel}" id="crAlertCancelId">
					<h:outputText id="PRGCMGTOT1672"
						value="#{messageBean.myTaskMap['label.crDetails.cancel']}" />
				</t:commandLink>
			</h:panelGroup>
		</m:div>
	</m:div>


	<m:div id="showHide_crdetail">
		<m:table id="PROVIDERMMT20120731164811642" border="0" cellpadding="2" cellspacing="2" width="100%">
			<m:tbody>
				<m:tr>
					<m:td>
						<m:div align="padb">
							<h:outputLabel id="PRGCMGTOLL655" for="cr_category_desc"
								value="#{messageBean.myTaskMap['label.crDetails.category']}" />
							<h:outputText id="cr_category_desc"
								value="#{myTaskDataBean.crDetailsVO.catDescription}" />
						</m:div>
					</m:td>
					<m:td>
						<m:div align="padb">
							<h:outputLabel id="PRGCMGTOLL656" for="cr_subjCode"
								value="#{messageBean.myTaskMap['label.crDetails.subject']}" />
							<h:outputText id="cr_subjCode"
								value="#{myTaskDataBean.crDetailsVO.subjectCode}" />
						</m:div>
					</m:td>
					<m:td>
						<m:div align="padb">
							<h:outputLabel id="PRGCMGTOLL657" for="cr_source"
								value="#{messageBean.myTaskMap['label.crDetails.source']}" />
							<h:outputText id="cr_source"
								value="#{myTaskDataBean.crDetailsVO.source}" />
						</m:div>
					</m:td>
					<m:td>
						<m:div align="padb">
							<h:outputLabel id="PRGCMGTOLL658" for="cr_contact_name"
								value="#{messageBean.myTaskMap['label.crDetails.contactName']}" />
							<h:outputText id="cr_contact_name"
								value="#{myTaskDataBean.crDetailsVO.contactName}" />
						</m:div>
					</m:td>
				</m:tr>
				<m:tr>
					<m:td>
						<m:div align="padb" rendered="#{myTaskDataBean.alertDetails}">
							<h:outputLabel id="PRGCMGTOLL659" for="cr_created_by"
								value="#{messageBean.myTaskMap['label.crDetails.createdBy']}" />
							<h:outputText id="cr_created_by"
								value="#{myTaskDataBean.crDetailsVO.createdBy}" />
						</m:div>
						<m:div align="padb" rendered="#{!myTaskDataBean.alertDetails}">
							<h:outputLabel id="PRGCMGTOLL660" for="cr_opened_by"
								value="#{messageBean.myTaskMap['label.crDetails.createdBy']}" />
							<h:outputText id="cr_opened_by"
								value="#{myTaskDataBean.crDetailsVO.createdBy}" />
						</m:div>
					</m:td>
					<m:td>
						<m:div align="padb">
							<h:outputLabel id="SAR" for="cr_supvisor_req"
								value="#{messageBean.myTaskMap['label.crDetails.supAppReq']}" />
							<h:selectBooleanCheckbox id="cr_supvisor_req"
								disabled="#{myTaskDataBean.disableApplication}"
								value="#{myTaskDataBean.crDetailsVO.supervisorAppReq}" />
						</m:div>
					</m:td>
					<m:td>
						<h:outputText id="PRGCMGTOT1673" escape="false"
							value="#{messageBean.prgMsgMap['label.ref.singleSpace']}" />
					</m:td>
					<m:td>
						<h:outputText id="PRGCMGTOT1674" escape="false"
							value="#{messageBean.prgMsgMap['label.ref.singleSpace']}" />
					</m:td>

				</m:tr>
				<m:tr>
					<m:td colspan="4">
						<m:div align="padb">
							<m:b>
								<h:outputLabel id="PRGCMGTOLL661" for="cr_notes_set"
									value="#{messageBean.myTaskMap['label.crDetails.notes']}" />
							</m:b>
						</m:div>

						<h:inputTextarea id="cr_notes_set"
							value="#{myTaskDataBean.crDetailsVO.noteSet}" readonly="true"></h:inputTextarea>
					</m:td>
					<m:td>
						<h:outputText id="PRGCMGTOT1675" escape="false"
							value="#{messageBean.prgMsgMap['label.ref.singleSpace']}" />
					</m:td>
					<m:td>
						<h:outputText id="PRGCMGTOT1676" escape="false"
							value="#{messageBean.prgMsgMap['label.ref.singleSpace']}" />
					</m:td>
					<m:td>
						<h:outputText id="PRGCMGTOT1677" escape="false"
							value="#{messageBean.prgMsgMap['label.ref.singleSpace']}" />
					</m:td>
				</m:tr>
			</m:tbody>
		</m:table>
	</m:div>
</m:div>








