<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:table width="100%" id="caseTypeCommonTab1Id">
	<m:tr id="caseTypeCommonTab1R1Id">
			<m:td id="caseTypeCommonTab1R1C1Id">
			<m:div styleClass="padb" id="caseTypeCommonTab1R1C1Div1Id">
				<h:outputLabel for="sendtoRegOff" value="#{msg['label.case.caseDetails.sendtoRegionalOff']}" id="caseTypeCmnOutLbl1Id"/>
				<h:outputText value=":" id="caseTypeCmnOutTxt1Id"></h:outputText>				
				
				<h:selectOneRadio id="sendtoRegOff"					disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" />
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" />
				</h:selectOneRadio>
				
			</m:div>

		</m:td>
	
	</m:tr>
	<m:tr id="caseTypeCommonTab1R2Id">
		<m:td id="caseTypeCommonTab1R2C1Id">
			<m:div styleClass="padb" id="caseTypeCommonTab1R2C1Div1Id">
				<h:outputLabel for="supDate" id="caseTypeCmnOutLbl2Id"					value="#{msg['label.case.caseDetails.fileSupDate']}" />
				<h:outputText id="colon1" value=":"></h:outputText>&nbsp;
				<m:inputCalendar id="supDate" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					disabled="#{logCaseDataBean.disableScreenField}"  value="#{logCaseDataBean.caseDetailsVO.caseTypeMemberAppealVO.fileSupDate}" /> 
			</m:div>
			<h:message for="supDate1" styleClass="errorMessage" id="caseTypeCommonMsg1Id"/>
			<m:br id="caseTypeCommonBr1Id"></m:br>
		</m:td>
	</m:tr>

	<m:tr id="caseTypeCommonTab1R3Id">
		<m:td id="caseTypeCommonTab1R3C1Id">
			<m:div styleClass="padb" id="caseTypeCommonTab1R3C1Div1Id">
				<h:outputLabel for="findings" id="caseTypeCmnOutLbl3Id"					value="#{msg['label.case.caseDetails.Findings']}" />
				<h:outputText id="colon2" value=":"></h:outputText>&nbsp;
				<h:selectOneMenu id="findings"										disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemLabel="Reviewed" itemValue="Reviewed"/>
					<f:selectItem itemLabel="Pending" itemValue="Pending" />
					
				</h:selectOneMenu>
			</m:div>
		</m:td>
	</m:tr>
	<m:tr id="caseTypeCommonTab1R4Id">
		<m:td id="caseTypeCommonTab1R4C1Id">
			<m:div styleClass="padb" id="caseTypeCommonTab1R4C1Div1Id">
				<h:outputLabel for="othInsurInd" id="caseTypeCmnOutLbl4Id"					value="#{msg['label.case.caseDetails.othInsInd']}" />
					<h:outputText id="colon3" value=":"></h:outputText>&nbsp;
					<h:selectBooleanCheckbox id="othInsurInd" /> 
			</m:div>
		</m:td>
	</m:tr>

	<m:tr id="caseTypeCommonTab1R5Id">
		<m:td id="caseTypeCommonTab1R5C1Id">
			<m:div styleClass="padb" id="caseTypeCommonTab1R5C1Div1Id">
				<h:outputLabel for="projLocation" id="caseTypeCmnOutLbl5Id"					value="#{msg['label.case.caseDetails.ProjLoc']}" />
				<h:outputText id="colon4" value=":"></h:outputText>&nbsp;
				<h:selectOneMenu id="projLocation"										disabled="#{logCaseDataBean.disableScreenField}">
					
					<f:selectItem itemLabel="Atlanda" itemValue="Atlanda"/>
					<f:selectItem itemLabel="McRae" itemValue="McRae" />
				</h:selectOneMenu>
			</m:div>
		</m:td>
	</m:tr>
	<m:tr id="caseTypeCommonTab1R6Id">
		<m:td id="caseTypeCommonTab1R6C1Id">
			<m:div styleClass="padb" id="caseTypeCommonTab1R6C1Div1Id">
				<h:outputLabel for="typeOfAction" id="caseTypeCmnOutLbl6Id"					value="#{msg['label.case.caseDetails.TypeofAction']}" />
				<h:outputText id="colon5" value=":"></h:outputText>&nbsp;
				<h:selectOneMenu id="typeOfAction"										disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemLabel="O-Open" itemValue="O-Open"/>
					<f:selectItem itemLabel="C-Closed" itemValue="C-Closed" />
				
				</h:selectOneMenu>
			</m:div>
		</m:td>
	</m:tr>
	

</m:table>
