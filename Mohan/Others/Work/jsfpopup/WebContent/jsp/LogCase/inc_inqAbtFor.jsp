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
<m:div id="inqAbtOtherDiv1Id">
	<m:div styleClass="moreInfo" id="inqAbtOtherDiv2Id">
		<m:div styleClass="moreInfoBar" id="inqAbtOtherDiv3Id">
			<m:div styleClass="infoActions" id="inqAbtOtherDiv4Id">
				<h:panelGroup id="inqAbtOtheranGrp1Id">
					<%--Modified for defect ESPRD00718080 starts added rendered tag--%>
					<t:commandLink styleClass="strong"  immediate="true" onclick="javascript:flagWarn=false;return deleteAdditCaseEntity(this);" onmousedown="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');" 		rendered="#{!logCaseDataBean.controlRequired}"				action="#{logCaseControllerBean.deleteInquiringAbout}" id="inqAbtOtherDelBtnId">
						<h:outputText id="PRGCMGTOT599" value="#{msg['label.case.delete']}" />
					</t:commandLink>
					<%--Modified for defect ESPRD00718080 starts--%>
					<h:outputText value="#{msg['label.case.delete']}"  rendered="#{logCaseDataBean.controlRequired}"	id="PRGCMGTOT5999"/>
					<%--Modified  End for defect ESPRD00718080 starts--%>
					<h:outputText escape="false" value="&nbsp;" id="inqAbtOtherOutTxt1Id"/><h:outputText value="|" id="inqAbtOtherOutTxt2Id"/><h:outputText escape="false" value="&nbsp;" id="inqAbtOtherOutTxt3Id"/>
					<t:commandLink styleClass="commandLink"						action="#{logCaseControllerBean.cancelInquiringAbout}" id="inqAbtOtherCancelBtnId" onmousedown="javascript:focusPage('additionalCaseEntitiesFocusPage');">
						<h:outputText id="PRGCMGTOT600" value="#{msg['label.case.cancel']}" />
					</t:commandLink>
				</h:panelGroup>
			</m:div>
		</m:div>

		<m:table styleClass="viewTable" width="90%" id="inqAbtOtherTab1Id">
			<m:tr id="inqAbtOtherTab1R1Id">
				<m:td id="inqAbtOtherTab1R1C1Id">
					<m:div styleClass="padx" id="inqAbtOtherDiv5">
						<h:outputLabel for="inqAbtTypeCode" id="inqAbtOtherOutLbl1Id">
							<h:outputText value="#{msg['label.case.caseRegarding.entityType']}"								id="inqAbtTypeCode" />
						</h:outputLabel>
						<m:br id="inqAbtOtherBr1Id"/>
						<h:outputText id="inqAbtOtherOutTxt4Id"							value="#{logCaseDataBean.caseForTPLVO.entityTypeDesc}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtOtherTab1R1C2Id">
					<m:div styleClass="padx" id="inqAbtOtherDiv6">
						<h:outputLabel for="inqAbtCMId" id="inqAbtOtherOutLbl2Id">
							<h:outputText value="#{msg['label.case.caseRegarding.cmEntityID']}"								id="inqAbtCMId" />
						</h:outputLabel>
						<m:br id="inqAbtOtherBr2Id"/>
						<h:outputText id="inqAbtOtherOutTxt5Id"							value="#{logCaseDataBean.caseForTPLVO.entityId}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtOtherTab1R1C3Id">
					<m:div styleClass="padx" id="inqAbtOtherDiv7">
						<h:outputLabel for="inqAbtName" id="inqAbtOtherOutLbl3Id">
							<h:outputText value="#{msg['label.case.caseRegarding.name']}"								id="inqAbtName" />
						</h:outputLabel>
						<m:br id="inqAbtOtherBr3Id"/>
						<h:outputText id="inqAbtOtherOutTxt6Id"							value="#{logCaseDataBean.caseForTPLVO.name}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtOtherTab1R1C4Id">
					<m:div styleClass="padx" id="inqAbtOtherDiv8">
						<h:outputLabel for="inqAbtStatus" id="inqAbtOtherOutLbl4Id">
							<h:outputText value="#{msg['label.case.caseRegarding.status']}"								id="inqAbtStatus" />
						</h:outputLabel>
						<m:br id="inqAbtOtherBr4Id"/>
						<h:outputText id="inqAbtOtherOutTxt7Id"							value="#{logCaseDataBean.caseForTPLVO.status}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtOtherTab1R1C5Id">
					<m:div styleClass="padx" id="inqAbtOtherDiv9">
							<h:outputText value="Days To Close" id="inqAbtDTC" styleClass="outputLabel" />
						<m:br id="inqAbtOtherBr5Id"/>
						<h:outputText value="" id="inqAbtOtherOutTxt8Id">
						</h:outputText>
					</m:div>
				</m:td>
				<m:br id="inqAbtOtherBr6Id"/>
			</m:tr>
		</m:table>
	</m:div>
</m:div>








