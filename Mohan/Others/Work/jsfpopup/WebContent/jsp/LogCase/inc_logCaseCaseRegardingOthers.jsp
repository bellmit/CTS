<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<m:table id="caseRegOthrTable" width="100%">
	<m:tr id="caseRegOthrTableTR1">
		<m:td id="caseRegOthrTableTR1TD1">
			<m:div id="caseRegOthrTableTR1TD1Div1" styleClass="padb">
				<h:outputText id="caseRegOthrCrNumbrLabl"					value="#{msg['label.case.caseRegarding.caseRecordNumber']}"					styleClass="outputLabel" />
				<m:br id="caseRegOthrCrNumbrBR" />
				<h:outputText id="caseRegOthrCrNumbrVal" value="#{logCaseDataBean.caseRegardingVO.caseRecordNumber}" />
			</m:div>
		</m:td> 
	</m:tr>
	<m:tr  id="caseRegOthrTableTR2">
		<m:td  id="caseRegOthrTableTR2TD1">
			<m:div  id="caseRegOthrTableTR2TD1Div" styleClass="padb">
				<h:outputText id="caseRegOthrEntyTypLabl" value="#{msg['label.case.caseRegarding.entityType']}"					styleClass="outputLabel" />
				<m:br id="caseRegOthrEntyTypBR" />
				<h:outputText id="caseRegOthrEntyTypVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.entityTypeDesc}" />
			</m:div>
		</m:td>
		<m:td  id="caseRegOthrTableTR2TD2">
			<m:div id="caseRegOthrTableTR2TD2Div" styleClass="padb" rendered="#{!logCaseDataBean.showTPLpolcyHldrlabel}">
				<h:outputText id="caseRegOthrEntIDLabl" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.cmEntityID']}"  rendered="#{!logCaseDataBean.showTPLlabel && !logCaseDataBean.showTPLpolcyHldrlabel}"/>
				
				<h:outputText id="caseRegOthrCrriIDLabl" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.tplCarrierID']}" rendered="#{logCaseDataBean.showTPLlabel}"/>

				<m:br id="caseRegOthrEntIDBR" />
					<%-- Modified for the defect ESPRD00865382 starts--%>
					<h:outputText id="caseRegOthrEntIDVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.entityId}" style="color: blue" rendered="#{!logCaseDataBean.disableScreenField}" />
					<h:outputText id="caseRegOthrEntIDValInquiry" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.entityId}" rendered="#{logCaseDataBean.disableScreenField}" />
					<%--ESPRD00865382 ends--%>
			</m:div>
			<m:div id="caseRegOthrTableTR2TD2Div1" styleClass="padb" rendered="#{logCaseDataBean.showTPLpolcyHldrlabel}">
					<h:outputText id="caseRegTplPolcyHldrLabl2" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.tplPolcyHldrID']}" />

				<m:br id="caseRegOthrEntIDBR2" />
					<h:outputText id="caseRegOthrEntIDVal2" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.policyHolderID}" />
			</m:div>
		</m:td>
		<m:td id="caseRegOthrTableTR2TD3">
			<m:div id="caseRegOthrTableTR2TD3Div" styleClass="padb">

				<h:outputText id="caseRegOthrNameLabl" value="#{msg['label.case.caseRegarding.name']}"					styleClass="outputLabel"  rendered="#{!logCaseDataBean.showTPLlabel && !logCaseDataBean.showTPLpolcyHldrlabel}" />

				<h:outputText id="caseRegOthrCrrNameLabl" value="#{msg['label.case.caseRegarding.carrierName']}"					styleClass="outputLabel" rendered="#{logCaseDataBean.showTPLlabel}" />

				<h:outputText id="caseRegTplPolcyHldrCrrNameLabl" value="#{msg['label.case.caseRegarding.tplPolcyHldrName']}"					styleClass="outputLabel" rendered="#{logCaseDataBean.showTPLpolcyHldrlabel}" />

				<m:br id="caseRegOthrNameBR" />
				<h:outputText id="caseRegOthrNameVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.name}" />
			</m:div>
		</m:td>
		<m:td id="GroupIdTd1">
			<m:div id="GroupIdDiv1" styleClass="padb" rendered="#{logCaseDataBean.showTPLpolcyHldrlabel}">

				<h:outputText id="GroupIdLabel1" value="#{msg['label.case.caseRegarding.tplPolcyGrupId']}"					styleClass="outputLabel" />

				<m:br id="GroupIdBr1" />
				<t:commandLink id="caseRegOthrEntIDLink2" action="#"  onmousedown="javascript:flagWarn=false;">
				<h:outputText id="GroupIdValue12" value="#{logCaseDataBean.caseRegardingVO.caseRegardingTPLVO.tplGroupId}" />
				</t:commandLink>
			</m:div>
		</m:td>

	</m:tr>
</m:table>


