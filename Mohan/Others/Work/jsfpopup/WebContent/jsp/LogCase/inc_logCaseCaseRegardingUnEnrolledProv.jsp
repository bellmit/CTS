<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:table id="caseRegUnEnrProvTabl" width="100%">
	<m:tr id="caseRegUnEnrProvTablTR1">
		<m:td id="caseRegUnEnrProvTablTRTD1">
			<m:div id="caseRegUnEnrProvTablDiv1" styleClass="padb">
				<h:outputText id="caseRegUnEnrProvCRNLabl"					value="#{msg['label.case.caseRegarding.caseRecordNumber']}"					styleClass="outputLabel" />
				<m:br id="caseRegUnEnrProvTablBR1" />
				<h:outputText id="caseRegUnEnrProvCRNVal" value="#{logCaseDataBean.caseRegardingVO.caseRecordNumber}" />
			</m:div>
		</m:td>
	</m:tr>
	<m:tr id="caseRegUnEnrProvTablT2">
		<m:td id="caseRegUnEnrProvTablTRTD2">
			<m:div id="caseRegUnEnrProvTablDiv2" styleClass="padb">
				<h:outputText id="caseRegUnEnrProvEnttyIDLabl" value="#{msg['label.case.caseRegarding.entityID']}"					styleClass="outputLabel" />
				<m:br id="caseRegUnEnrProvTablBR2" />
				<h:outputText id="caseRegUnEnrProvEnttyIDVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.entityId}" />
			</m:div>
		</m:td>
		<m:td id="caseRegUnEnrProvTablTRTD3">
			<m:div id="caseRegUnEnrProvTablDiv3" styleClass="padb">
				<h:outputText id="caseRegUnEnrProvEnttyTypLabl" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.entityType']}" />
				<m:br id="caseRegUnEnrProvTablBR3" />
					<h:outputText id="caseRegUnEnrProvEnttyTypVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.entityTypeDesc}" />
			</m:div>
		</m:td>
		<m:td id="caseRegUnEnrProvTablTRTD4">
			<m:div id="caseRegUnEnrProvTablDiv4" styleClass="padb">
				<h:outputText id="caseRegUnEnrProvStatusLabl" value="#{msg['label.case.caseRegarding.status']}"					styleClass="outputLabel" />
				<m:br id="caseRegUnEnrProvTablBR4" />
				<h:outputText id="caseRegUnEnrProvStatusVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.status}" />
			</m:div>
		</m:td>
		<m:tr id="caseRegUnEnrProvTablTR3">
			<m:td id="caseRegUnEnrProvTablTRTD5">
				<m:div  id="caseRegUnEnrProvTablDiv5" styleClass="padb">
					<h:outputText id="caseRegUnEnrProvNameLabl" value="#{msg['label.case.caseRegarding.name']}"						styleClass="outputLabel" />
					<m:br id="caseRegUnEnrProvTablBR5"/>
					<h:outputText id="caseRegUnEnrProvNameVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.name}" />
				</m:div>
			</m:td>
			<m:td id="caseRegUnEnrProvTablTRTD6">
				<m:div id="caseRegUnEnrProvTablDiv6" styleClass="padb">
					<h:outputText id="caseRegUnEnrProvSpeciltyLabl" value="#{msg['label.case.caseRegarding.speciality']}"						styleClass="outputLabel" />
					<m:br id="caseRegUnEnrProvTablBR6"/>
					<h:outputText id="caseRegUnEnrProvSpeciltyVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.specialty}" />
				</m:div>
			</m:td>
			<m:td id="caseRegUnEnrProvTablTRTD7">
				<m:div id="caseRegUnEnrProvTablDiv7" styleClass="padb">
					<h:outputText id="caseRegUnEnrProvTypeLabl"						value="#{msg['label.case.caseRegarding.providerType']}"						styleClass="outputLabel" />
					<m:br id="caseRegUnEnrProvTablBR17"/>
					<h:outputText id="caseRegUnEnrProvTypeVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.provTypeCodeDesc}" />
				</m:div>
			</m:td>
		</m:tr>
	</m:tr>
</m:table>
