<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:table id="caseRegTradPartTabl" width="100%">
	<m:tr id="caseRegTradPartTablTR1">
		<m:td id="caseRegTradPartTablTRTD1">
			<m:div id="caseRegTradPartTablDiv1" styleClass="padb">
				<h:outputText id="caseRegTradPartCRNLabl"					value="#{msg['label.case.caseRegarding.caseRecordNumber']}"					styleClass="outputLabel" />
				<m:br id="caseRegTradPartTablBR1" />
				<h:outputText id="caseRegTradPartCRNVal" value="#{logCaseDataBean.caseRegardingVO.caseRecordNumber}" />
			</m:div>
		</m:td>
	</m:tr>
  	<m:tr id="caseRegTradPartTablT2">
		<m:td id="caseRegTradPartTablTRTD2">
			<m:div id="caseRegTradPartTablDiv2" styleClass="padb">
				<h:outputText id="caseRegTradPartEnttyTypLabl" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.entityType']}" />
				<m:br id="caseRegTradPartTablBR2" />
					<h:outputText id="caseRegTradPartEnttyTypVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTradingPartnerVO.entityTypeDesc}" />
			</m:div>
		</m:td>
		
		<m:td id="caseRegTradPartTablTRTD3">
			<m:div id="caseRegTradPartTablDiv3" styleClass="padb">
				<h:outputText id="caseRegTradPartTradingPartnerLabl" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.tradingPartnerID']}" />
				<m:br />
					<%-- Modified for the defect ESPRD00865382 starts--%>
					<h:outputText id="caseRegTradPartTradingPartnerVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTradingPartnerVO.tradingPartnerId}" style="color: blue" rendered="#{!logCaseDataBean.disableScreenField}" />
					<h:outputText id="caseRegTradPartTradingPartnerValInquiry" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTradingPartnerVO.tradingPartnerId}" rendered="#{logCaseDataBean.disableScreenField}" />
					<%--ESPRD00865382 ends--%>

			</m:div>
		</m:td>
		<m:td id="caseRegTradPartTablTRTD4">
				<m:div  id="caseRegTradPartTablDiv4" styleClass="padb">
					<h:outputText id="caseRegTradPartNameLabl" value="#{msg['label.case.caseRegarding.name']}"						styleClass="outputLabel" />
					<m:br id="caseRegTradPartTablBR4"/>
					<h:outputText id="caseRegTradPartNameVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTradingPartnerVO.name}" />
				</m:div>
		</m:td>

		
			<m:td id="caseRegTradPartTablTRTD5">
				<m:div id="caseRegTradPartTablDiv5" styleClass="padb">
					<h:outputText id="caseRegTradPartClassificationLabl" value="#{msg['label.case.caseRegarding.classification']}"						styleClass="outputLabel" />
					<m:br id="caseRegTradPartTablBR5"/>
					<h:outputText id="caseRegTradPartClassificationVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTradingPartnerVO.classification}" />
				</m:div>
			</m:td>
			<m:td id="caseRegTradPartTablTRTD6">
			<m:div id="caseRegTradPartTablDiv6" styleClass="padb">
				<h:outputText id="caseRegTradPartStatusLabl" value="#{msg['label.case.caseRegarding.status']}"					styleClass="outputLabel" />
				<m:br id="caseRegTradPartTablBR6" />
				<h:outputText id="caseRegTradPartStatusVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTradingPartnerVO.status}" />
			</m:div>
		</m:td>
	
			<m:td id="caseRegTradPartTablTRTD7">
				<m:div styleClass="padb" id="caseRegTradPartTablDiv7">
					<h:outputLabel for="contact"						value="#{msg['label.case.caseRegarding.contact']}"						id="caseRegTradPartContactLabl" />
					<m:br id="caseRegTradPartTablBR7"/>
					<h:selectOneMenu id="contact"  						onchange="javascript:flagWarn=false;focusThis(this.id);this.form.submit();"						onmousedown="javascript:flagWarn=false;"						value="#{logCaseDataBean.caseRegardingVO.caseRegardingTradingPartnerVO.contact}">
						<f:selectItems value="#{logCaseDataBean.contact}" />
					</h:selectOneMenu>
				</m:div>
			</m:td>


			
	</m:tr>
</m:table>
