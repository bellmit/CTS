<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:table id="caseRegUnEnrMemTable" width="100%">
	<m:tr id="caseRegUnEnrMemTableTR1">
		<m:td id="caseRegUnEnrMemTableTD1">
			<m:div id="caseRegUnEnrMemTablDiv1" styleClass="padb">
				<h:outputText id="caseRegUnEnrMemCRNLabl"					value="#{msg['label.case.caseRegarding.caseRecordNumber']}"					styleClass="outputLabel" />
				<m:br id="caseRegUnEnrMemTableBR1" />
				<h:outputText id="caseRegUnEnrMemCRNVal" value="#{logCaseDataBean.caseRegardingVO.caseRecordNumber}" />
			</m:div>
		</m:td>
	</m:tr>
	<m:tr id="caseRegUnEnrMemTableTR2">
		<m:td id="caseRegUnEnrMemTableTD2">
			<m:div id="caseRegUnEnrMemTablDiv2" styleClass="padb">
				<h:outputText id="caseRegUnEnrMemEntTypLabl" value="#{msg['label.case.caseRegarding.entityType']}"					styleClass="outputLabel" />
				<m:br  id="caseRegUnEnrMemTableBR2"/>
				<h:outputText id="caseRegUnEnrMemEntTypVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.entityTypeDesc}" />
			</m:div>
		</m:td>
		<m:td id="caseRegUnEnrMemTableTD3">
			<m:div id="caseRegUnEnrMemTablDiv3" styleClass="padb">
				<h:outputText id="caseRegUnEnrMemEntIDLabl" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.cmEntityID']}" />
				<m:br  id="caseRegUnEnrMemTableBR3" />
				<h:outputText id="caseRegUnEnrMemEntIdVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.entityId}" />
			</m:div>
		</m:td>
		<m:td id="caseRegUnEnrMemTableTD4">
			<m:div id="caseRegUnEnrMemTablDiv4" styleClass="padb">
				<h:outputText id="caseRegUnEnrMemSSNLabl" value="#{msg['label.case.caseRegarding.ssn']}"					styleClass="outputLabel" />
				<m:br id="caseRegUnEnrMemTableBR4" />
				<h:outputText id="caseRegUnEnrMemSSNVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.ssn}" />
			</m:div>
		</m:td>
		<m:td id="caseRegUnEnrMemTableTD5">
			<m:div id="caseRegUnEnrMemTablDiv5" styleClass="padb">
				<h:outputText id="caseRegUnEnrMemNameLabl" value="#{msg['label.case.caseRegarding.name']}"					styleClass="outputLabel" />
				<m:br  id="caseRegUnEnrMemTableBR5"/>
				<h:outputText id="caseRegUnEnrMemNameVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.name}" />
			</m:div>
		</m:td>
		<m:td id="caseRegUnEnrMemTableTD6">
			<m:div id="caseRegUnEnrMemTablDiv16" styleClass="padb">
				<h:outputText id="caseRegUnEnrMemEmailLabl" value="#{msg['label.case.caseRegarding.email']}"					styleClass="outputLabel" />
				<m:br  id="caseRegUnEnrMemTableBR6"/>
				<h:outputText id="caseRegUnEnrMemEmailVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.email}" />
			</m:div>
		</m:td>
	</m:tr>
</m:table>


