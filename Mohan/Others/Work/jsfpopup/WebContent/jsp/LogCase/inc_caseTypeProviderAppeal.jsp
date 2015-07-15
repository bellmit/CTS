<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<%-- As per CR ESPRD00865082 ,inc_caseTypeProviderAppeal.jsp is not used because inc_caseTypeMemberAppeal.jsp is reused to display continue button for all Appeal Case Types --%>

	<%-- Added for the defect id ESPRD00329240  --%>
	<m:br id="provApplBr1Id"/>
	<m:div styleClass="clearl" id="provApplDiv1Id">
	</m:div>
	
  <%-- Ends for the defect id ESPRD00329240 --%>
  
<m:table width="100%" id="provApplTab1Id">
	<m:tr id="provApplTab1R1Id">
		<m:td align="left" id="provApplTab1R1C1Id">
			<m:div styleClass="padb" id="provApplTab1R1C1Div1Id">
				<m:inputHidden name="com.ibm.portal.propertybroker.action"
					value="sendLogCaseAction"></m:inputHidden>
				<h:commandButton value="#{msg['button.label.continue']}" id="provApplContBtn1Id" onmousedown="javascript:flagWarn=false;"					action="#{logCaseControllerBean.submitAddAppealDetails}"					styleClass="formbtn" disabled="#{logCaseDataBean.disableScreenField}" rendered="#{!logCaseDataBean.disableLogcaseContiniue}" />
				<h:outputText value="#{msg['button.label.continue']}" rendered="#{logCaseDataBean.disableLogcaseContiniue}" id="provApplContOutTxtId"/>
			</m:div>
		</m:td>
	</m:tr>


</m:table>

