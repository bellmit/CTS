<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<script type="text/javascript">
function deleteAdditCaseEntity(id)
{
if(confirm("Are you sure, Do you want to delete this record?")){
	return true;
}else{
	flagWarn=false;
	return false;
	}
}
</script>
<m:div id="inqAbProvDiv1Id">
	<m:div styleClass="moreInfo" id="inqAbProvDiv2Id">
		<m:div styleClass="moreInfoBar" id="inqAbProvDiv3Id" >
			<m:div styleClass="infoActions" id="inqAbProvDiv4Id">
				<h:panelGroup id="inqAbProvPG1Id">
					<%--Modified for defect ESPRD00718080 starts added rendered tag--%>
					<t:commandLink styleClass="strong"  immediate="true" onclick="javascript:flagWarn=false;return deleteAdditCaseEntity(this);" onmousedown="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');" 		rendered="#{!logCaseDataBean.controlRequired}"				action="#{logCaseControllerBean.deleteInquiringAbout}" id="inqAbtProvDelBtn">
						<h:outputText value="#{msg['label.case.delete']}" id="inqAbtProvOutTxt1"/>
					</t:commandLink>
					<%--Modified for defect ESPRD00718080 starts--%>
					<h:outputText value="#{msg['label.case.delete']}" rendered="#{logCaseDataBean.controlRequired}"   id="inqAbtProvOutTxt12"/>
					<%--Modified  End for defect ESPRD00718080 starts--%>
					<h:outputText id="PRGCMGTOT601" escape="false" value="&nbsp;"/><h:outputText id="PRGCMGTOT6012" value="|"/><h:outputText id="PRGCMGTOT6013" escape="false" value="&nbsp;"/>
					<t:commandLink styleClass="commandLink"						action="#{logCaseControllerBean.cancelInquiringAbout}" id="inqAbtProvCancelBtn" onmousedown="javascript:focusPage('additionalCaseEntitiesFocusPage');">
						<h:outputText value="#{msg['label.case.cancel']}" id="inqAbtProvOutTxt2"/>
					</t:commandLink>
				</h:panelGroup>
			</m:div>
		</m:div>

		<m:table styleClass="viewTable" width="90%" id="inqAbtProvTab1Id">
			<m:tr id="inqAbtProvTab1R1Id">
				<m:td id="inqAbtProvTab1R1C1Id">
					<m:div styleClass="padx" id="inqAbProvDiv5Id">
						<h:outputLabel for="inqAbtCodeProv" id="inqAbtProvOutLbl1">
							<h:outputText value="#{msg['label.case.caseRegarding.entityType']}"								id="inqAbtCodeProv" />
						</h:outputLabel>
						<m:br id="inqAbtProvTab1Br1Id"/>
						<h:outputText id="inqAbtProvOutTxt3"							value="#{logCaseDataBean.caseForProviderVO.entityTypeDesc}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtProvTab1R1C2Id">
					<m:div styleClass="padx" id="inqAbProvDiv6Id">
						<h:outputLabel for="inqAbtProvID" id="inqAbtProvOutLbl2">
							<h:outputText value="#{msg['label.case.caseRegarding.providerID']}"								id="inqAbtProvID" />
						</h:outputLabel>
						<m:br id="inqAbtProvTab1Br2Id"/>
						<h:outputText id="inqAbtProvOutTxt4"							value="#{logCaseDataBean.caseForProviderVO.providerId}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtProvTab1R1C3Id">
					<m:div styleClass="padx" id="inqAbProvDiv7Id">
						<h:outputLabel for="inqAbtPayerID" id="inqAbtProvOutLbl3">
							<h:outputText value="#{msg['label.case.caseRegarding.payerID']}"								id="inqAbtPayerID" />
						</h:outputLabel>
						<m:br id="inqAbtProvTab1Br3Id"/>
						<h:outputText id="inqAbtProvOutTxt5"							value="#{logCaseDataBean.caseForProviderVO.payeeId}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtProvTab1R1C4Id">
					<m:div styleClass="padx" id="inqAbProvDiv12Id">
						<h:outputLabel for="inqAbtStatusProv" id="inqAbtProvOutLbl4">
							<h:outputText value="#{msg['label.case.caseRegarding.status']}"								id="inqAbtStatusProv" />
						</h:outputLabel>
						<m:br id="inqAbtProvTab1Br4Id"/>
						<h:outputText id="inqAbtProvOutTxt6"							value="#{logCaseDataBean.caseForProviderVO.statusDesc}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtProvTab1R1C5Id">
					<m:div styleClass="padx" id="inqAbProvDiv8Id">
						<h:outputLabel id="PRGCMGTOLL313" for="inqAbtDTCProv">
							<h:outputText value="Days To Close"								id="inqAbtDTCProv" />
						</h:outputLabel>
						<m:br id="inqAbtProvTab1Br5Id"/>
						<h:outputText id="PRGCMGTOT602" value="">
						</h:outputText>
					</m:div>
				</m:td>
				<m:br />
			</m:tr>
			<m:tr id="inqAbtProvTab1R2Id">
				<m:td id="inqAbtProvTab1R2C1Id">
					<m:div styleClass="padx" id="inqAbProvDiv91Id">
						<h:outputLabel for="inqAbtProvName" id="inqAbtProvOutLbl5">
							<h:outputText value="#{msg['label.case.caseRegarding.name']}"								id="inqAbtProvName" />
						</h:outputLabel>
						<m:br id="inqAbtProvTab1Br6Id"/>
						<h:outputText id="inqAbtProvOutTxt8"							value="#{logCaseDataBean.caseForProviderVO.name}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtProvTab1R2C2Id">
					<m:div styleClass="padx" id="inqAbProvDiv10Id">
						<h:outputLabel for="inqAbtSpecialty" id="inqAbtProvOutLbl6">
							<h:outputText value="#{msg['label.case.caseRegarding.specialty']}"								id="inqAbtSpecialty" />
						</h:outputLabel>
						<m:br id="inqAbtProvTab1Br7Id"/>
						<h:outputText id="inqAbtProvOutTxt9"							value="#{logCaseDataBean.caseForProviderVO.specialtyDesc}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtProvTab1R2C3Id">
					<m:div styleClass="padx" id="inqAbProvDiv11Id">
						<h:outputLabel for="inqAbtProvType" id="inqAbtProvOutLbl7">
							<h:outputText value="#{msg['label.case.caseRegarding.providerType']}"								id="inqAbtProvType" />
						</h:outputLabel>
						<m:br id="inqAbtProvTab1Br8Id"/>    
						<h:outputText id="inqAbtProvOutTxt10"							value="#{logCaseDataBean.caseForProviderVO.providerTypeCodeDesc}">
						</h:outputText>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
	</m:div>
</m:div>

