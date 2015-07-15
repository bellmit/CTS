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
			<h:outputText id="PRGCMGTOT1617"
				value="#{messageBean.myTaskMap['label.caseDetails.alertdetails']}" />
		</m:div>


		<m:div styleClass="infoActions">
			<h:panelGroup id="PRGCMGTPGP126">
				<t:commandLink id="PRGCMGTCL225" styleClass="commandLink"
					action="#{myTaskControllerBean.openLetterReq}"
					rendered="#{myTaskDataBean.alertDetails && myTaskDataBean.openLetterReq}">
					<h:outputText id="PRGCMGTOT1618"
						value="#{messageBean.myTaskMap['label.crDetails.openLetter']}" />
					<f:param name="Send.MyAlerts.AlertSk" value="SendMyAlertSk" />
				</t:commandLink>
				<%-- <f:verbatim>&nbsp;|&nbsp;</f:verbatim>--%>
				<h:outputText id="seperator4Letter" escape="false" rendered="#{myTaskDataBean.alertDetails && myTaskDataBean.openLetterReq}"
					value="#{messageBean.prgMsgMap['label.ref.linkSeperator']}"/>


				<t:commandLink styleClass="commandLink"
					id="openTPLRecoveryCaseRecordCMD"
					action="#{myTaskControllerBean.openTPLRecoveryCaseRecord}"
					rendered="#{myTaskDataBean.openTPLRecoveryCaseRecord}">
					<h:outputText id="TxtOpenTPLRecoveryCaseRecord" 
						value="#{messageBean.myTaskMap['label.alrtDetails.openTPLRecoveryCaseRecord']}" />
					<f:param name="ACTION_NAME" value="sourceAction1"></f:param>
					<%--UC-PGM-CRM-052.1_ESPRD00437600_25MAR2010 --%>
				</t:commandLink>
				
				<h:outputText id="seperator4TPL" escape="false" rendered="#{myTaskDataBean.openTPLRecoveryCaseRecord}"
					value="#{messageBean.prgMsgMap['label.ref.linkSeperator']}"/>

				<t:commandLink id="PRGCMGTCL226" styleClass="strong"
					action="#{myTaskControllerBean.openMSQDetails}"
					rendered="#{myTaskDataBean.openMSQDetails}">
					<h:outputText id="PRGCMGTOT1619"
						value="#{messageBean.myTaskMap['label.alrtDetails.openMSQDetails']}" />
					<f:param id="ipcAction" name="com.ibm.portal.propertybroker.action"
						value="sendMSQDetails"></f:param>
					<%--UC-PGM-CRM-053_ESPRD00431860_26MAR2010 --%>
				</t:commandLink>

				<h:outputText id="seperator4MSQ" escape="false" rendered="#{myTaskDataBean.openMSQDetails}"
					value="#{messageBean.prgMsgMap['label.ref.linkSeperator']}"/>
					
				<t:commandLink id="PRGCMGTCL227" styleClass="commandLink"
					rendered="#{myTaskDataBean.openSADetails}"
					action="#{myTaskControllerBean.openSADetails}">
					<h:outputText id="PRGCMGTOT1620"
						value="#{messageBean.myTaskMap['label.alrtDetails.openSADetails']}"></h:outputText>
					<f:param name="ACTION_NAME" value="sourceAction" />
					<%--UC-PGM-CRM-052.1_ESPRD00437600_25MAR2010 --%>
				</t:commandLink>

				<%--<f:verbatim>&nbsp;|&nbsp;</f:verbatim>--%>

				<h:outputText id="seperator4SA" escape="false" rendered="#{myTaskDataBean.openSADetails}"
					value="#{messageBean.prgMsgMap['label.ref.linkSeperator']}"/>

				<t:commandLink styleClass="commandLink"
					id="tplRecoveryCaseAlertCancelId"
					action="#{myTaskControllerBean.tplMsqSaAlertDetailsCancel}">
					<h:outputText id="PRGCMGTOT1621"
						value="#{messageBean.myTaskMap['label.caseDetails.cancel']}"></h:outputText>
				</t:commandLink>

			</h:panelGroup>
		</m:div>

	</m:div>

	<m:div id="showHide_casedetail">
		<m:table id="PROVIDERMMT20120731164811639" border="0" cellpadding="2" cellspacing="2" width="100%">
			<m:tbody>
				<m:tr>
					<m:td>
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1622" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.alertDetails.AlertNumber']}" />
							<h:outputText id="PRGCMGTOT1623"
								value="#{myTaskDataBean.alertDetailsVO.alertSKStr}" />
						</m:div>
					</m:td>
					<m:td colspan="4">
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1624" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.alertDetails.AlertType']}" />
							<h:outputText id="PRGCMGTOT1625"
								value="#{myTaskDataBean.alertDetailsVO.alertTypeDesc}" />
						</m:div>
					</m:td>

					<m:td>
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1626" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.alertDetails.AlertStatus']}" />
							<h:outputText id="PRGCMGTOT1627"
								value="#{myTaskDataBean.alertDetailsVO.status}" />
						</m:div>
					</m:td>

					<m:td>
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1628" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.alertDetails.OpenDate']}" />
							<h:outputText id="PRGCMGTOT1629"
								value="#{myTaskDataBean.alertDetailsVO.openDateStr}" />
						</m:div>
					</m:td>


					<m:td>
						<m:div align="padb">
							<h:outputText id="PRGCMGTOT1630" styleClass="outputLabel"
								value="#{messageBean.myTaskMap['label.caseDetails.createdBy']}" />
							<h:outputText id="PRGCMGTOT1631"
								value="#{myTaskDataBean.alertDetailsVO.createdBy} " />
						</m:div>
					</m:td>
				</m:tr>
				<m:tr>
					<m:td colspan="5">
						<m:div align="padb">
							<h:outputLabel id="alert_det_descLabl" for="alert_det_desc"
								value="#{messageBean.myTaskMap['label.alrtDetails.Description']}" />
						</m:div>
						<h:inputTextarea id="alert_det_desc" cols="45"
							disabled="#{myTaskDataBean.alertDetailsDescription}" rows="5"
							value="#{myTaskDataBean.alertDetailsVO.description}">
						</h:inputTextarea>

					</m:td>
				</m:tr>
			</m:tbody>
		</m:table>
	</m:div>
</m:div>
