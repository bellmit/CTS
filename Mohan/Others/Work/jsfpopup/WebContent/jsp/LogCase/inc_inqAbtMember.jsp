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
<m:div id="inqAbtmemDiv1">
	<m:div styleClass="moreInfo" id="inqAbtmemDiv2">
		<m:div styleClass="moreInfoBar" id="inqAbtmemDiv3">
			<m:div styleClass="infoActions" id="inqAbtmemDiv4">
				<h:panelGroup id="inqAbtmemPgrp1Id">
					<%--Modified for defect ESPRD00718080 starts added rendered tag--%>
					<t:commandLink styleClass="strong"  immediate="true" onclick="javascript:flagWarn=false;return deleteAdditCaseEntity(this);" onmousedown="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');" 		rendered="#{!logCaseDataBean.controlRequired}"				action="#{logCaseControllerBean.deleteInquiringAbout}" id="inqAbtMemDelBtnId">
						<h:outputText value="#{msg['label.case.delete']}" id="inqAbtmemOutTxt1Id"/>
					</t:commandLink>
					<%--Modified for defect ESPRD00718080 starts--%>
					<h:outputText value="#{msg['label.case.delete']}"  rendered="#{logCaseDataBean.controlRequired}"	id="inqAbtmemOutTxt1Id12"/>
					<%--Modified  End for defect ESPRD00718080 starts--%>
					<h:outputText escape="false" value="&nbsp;" id="inqAbtmemOutTxt2Id"/>
					<h:outputText value="|" id="inqAbtmemOutTxt3Id"/>
					<h:outputText escape="false" value="&nbsp;" id="inqAbtmemOutTxt4Id"/>
					<t:commandLink styleClass="commandLink"						action="#{logCaseControllerBean.cancelInquiringAbout}" id="indAbtMemcancelBtnId" onmousedown="javascript:focusPage('additionalCaseEntitiesFocusPage');">
						<h:outputText value="#{msg['label.case.cancel']}" id="inqAbtmemOutTxt5Id"/>
					</t:commandLink>
				</h:panelGroup>
			</m:div>
		</m:div>

		<m:table styleClass="viewTable" width="90%" id="inqAbtmemTab1Id">
			<m:tr id="inqAbtmemTab1R1Id">
				<m:td id="inqAbtmemTab1R1C1Id">
					<m:div styleClass="padx" id="inqAbtmemDiv5">
						<h:outputLabel for="inqAbtCodeMem" id="inqAbtmemOutLbl1Id">
							<h:outputText value="#{msg['label.case.caseRegarding.entityType']}"								id="inqAbtCodeMem" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr2Id"/>
						<h:outputText							value="#{logCaseDataBean.caseForMemberVO.entityTypeDesc}" id="inqAbtmemOutTxt6Id">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtmemTab1R1C2Id">
					<m:div styleClass="padx" id="inqAbtmemDiv6">
						<h:outputLabel for="inqAbtMemberId" id="inqAbtmemOutLbl2Id">
							<h:outputText value="#{msg['label.case.caseRegarding.memberID']}"								id="inqAbtMemberId" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr3Id"/>
						<h:outputText id="inqAbtmemOutTxt7Id"							value="#{logCaseDataBean.caseForMemberVO.entityId}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtmemTab1R1C3Id">
					<m:div styleClass="padx" id="inqAbtmemDiv7">
						<h:outputLabel for="inqAbtSSN" id="inqAbtmemOutLbl3Id">
							<h:outputText value="#{msg['label.case.caseRegarding.ssn']}" id="inqAbtSSN" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr4Id"/>
						<h:outputText id="inqAbtmemOutTxt8Id"							value="#{logCaseDataBean.caseForMemberVO.memberSSN}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtmemTab1R1C4Id">
					<m:div styleClass="padx" id="inqAbtmemDiv8Id">
						<h:outputLabel for="inqAbtStatusMem" id="inqAbtmemOutLbl4Id">
							<h:outputText value="#{msg['label.case.caseRegarding.status']}"								id="inqAbtStatusMem" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr5Id"/>
						<h:outputText id="inqAbtmemOutTxt9Id"							value="#{logCaseDataBean.caseForMemberVO.status}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtmemTab1R1C5Id">
					<m:div styleClass="padx" id="inqAbtmemDiv9Id">
						<h:outputLabel for="inqAbtDTCMem" id="inqAbtmemOutLbl5Id">
							<h:outputText value="Days to Close"								id="inqAbtDTCMem" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr6Id"/>
						<h:outputText value="" id="inqAbtmemOutTxt10Id">
						</h:outputText>
					</m:div>
				</m:td>
				<m:br />
			</m:tr>
			<m:tr id="inqAbtmemTab1R2Id">
				<m:td id="inqAbtmemTab1R2C1Id">
					<m:div styleClass="padx" id="inqAbtmemDiv10Id">
						<h:outputLabel for="inqAbtNameMem" id="inqAbtmemOutLbl6Id">
							<h:outputText value="#{msg['label.case.caseRegarding.name']}"								id="inqAbtNameMem" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr7Id"/>
						<h:outputText id="inqAbtmemOutTxt11Id"							value="#{logCaseDataBean.caseForMemberVO.name}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtmemTab1R2C2Id">
					<m:div styleClass="padx" id="inqAbtmemDiv11Id">
						<h:outputLabel for="inqAbtEmail" id="inqAbtmemOutLbl7Id">
							<h:outputText value="#{msg['label.case.caseRegarding.email']}"								id="inqAbtEmail" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr8Id"/>
						<h:outputText id="inqAbtmemOutTxt12Id"							value="#{logCaseDataBean.caseForMemberVO.email}">
						</h:outputText>
					</m:div>
				</m:td>
			</m:tr>
			<m:tr id="inqAbtmemTab1R3Id">
				<m:td id="inqAbtmemTab1R3C1Id">
					<m:div styleClass="padx" id="inqAbtmemDiv12Id">
						<h:outputLabel for="inqAbtAnb" id="inqAbtmemOutLbl8Id">
							<h:outputText value="#{msg['label.case.caseRegarding.catageoryOfEligibility']}" id="inqAbtAnb" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr9Id"/>
						<h:outputText id="inqAbtmemOutTxt13Id"							value="#{logCaseDataBean.caseForMemberVO.categoryOfEligibility}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtmemTab1R3C2Id">
					<m:div styleClass="padx" id="inqAbtmemDiv13Id">
						<h:outputLabel for="inqAbtDO" id="inqAbtmemOutLbl9Id">
							<h:outputText value="#{msg['label.case.caseRegarding.districtOffice']}"								id="inqAbtDO" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr10Id"/>
						<h:outputText id="inqAbtmemOutTxt14Id"							value="#{logCaseDataBean.caseForMemberVO.districtOffice}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtmemTab1R3C3Id">
					<m:div styleClass="padx" id="inqAbtmemDiv14Id">
						<h:outputLabel for="inqAbtDOB" id="inqAbtmemOutLbl10Id">
							<h:outputText value="#{msg['label.case.caseRegarding.dateOfBirth']}" id="inqAbtDOB" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr11Id"/>
						<h:outputText id="inqAbtmemOutTxt15Id"							value="#{logCaseDataBean.caseForMemberVO.dateofBirth}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtmemTab1R3C4Id">
					<m:div styleClass="padx" id="inqAbtmemDiv15Id">
						<h:outputLabel for="inqAbtPrevName" id="inqAbtmemOutLbl11Id">
							<h:outputText value="#{msg['label.case.caseRegarding.previousName']}"								id="inqAbtPrevName" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr12Id"/>
						<h:outputText id="inqAbtmemOutTxt16Id"							value="#{logCaseDataBean.caseForMemberVO.previousName}">
						</h:outputText>
					</m:div>
				</m:td>
			</m:tr>
			<m:tr id="inqAbtmemTab1R4Id">
				<m:td id="inqAbtmemTab1R4C1Id">
					<m:div styleClass="padx" id="inqAbtmemDiv16Id">
						<h:outputLabel for="inqAbtResAddr" id="inqAbtmemOutLbl12Id">
							<h:outputText value="#{msg['label.case.caseRegarding.resedentialAddress']}"								id="inqAbtResAddr" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr13Id"/>
						<h:outputText id="inqAbtmemOutTxt17Id"	escape="false"	value="#{logCaseDataBean.caseForMemberVO.residentialAddress}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td id="inqAbtmemTab1R4C2Id">
					<m:div styleClass="padx" id="inqAbtmemDiv17Id">
						<h:outputLabel for="inqAbtmailAddr" id="inqAbtmemOutLbl13Id">
							<h:outputText value="#{msg['label.case.caseRegarding.mailingAddress']}"								id="inqAbtmailAddr" />
						</h:outputLabel>
						<m:br id="inqAbtmemBr14Id"/>
						<h:outputText id="inqAbtmemOutTxt18Id"	escape="false"  	value="#{logCaseDataBean.caseForMemberVO.mailingAddress}">
						</h:outputText>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
	</m:div>
</m:div>

