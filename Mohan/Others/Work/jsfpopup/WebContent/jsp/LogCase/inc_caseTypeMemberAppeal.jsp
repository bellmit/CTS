<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>


	<%-- Added for the defect id ESPRD00329240  --%>
	<m:br id="membBr1Id"/>
	<m:div styleClass="clearl" id="membDiv1Id">
	</m:div>
	
  <%-- Ends for the defect id ESPRD00329240 --%>
<m:table width="100%" id="membTab1Id">
	<m:tr id="membTab1R1Id">
		<m:td align="left" id="membTab1R1C1Id">
			<m:div styleClass="padb" id="membTab1R1C1Div1Id">
				<m:inputHidden name="com.ibm.portal.propertybroker.action"
					value="sendLogCaseAction" ></m:inputHidden>
				<h:commandButton value="#{msg['button.label.continue']}" id="membContBtnId"	onmousedown="javascript:flagWarn=false;"				action="#{logCaseControllerBean.submitAddAppealDetails}"					styleClass="formbtn" disabled="#{logCaseDataBean.disableScreenField}" rendered="#{!logCaseDataBean.disableLogcaseContiniue}" />
				<h:outputText value="#{msg['button.label.continue']}" rendered="#{logCaseDataBean.disableLogcaseContiniue}" id="membContBtnOutTxtId"/>
			</m:div>
		</m:td>
	</m:tr>


</m:table>

