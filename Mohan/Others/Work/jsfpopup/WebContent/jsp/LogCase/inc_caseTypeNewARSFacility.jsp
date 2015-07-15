  <%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<portlet:defineObjects />

<m:table width="100%" id="caseTypeNewARSFacTab1Id">
	<m:tr id="caseTypeNewARSFacTab1R1Id">		
		<m:td id="caseTypeNewARSFacTab1R1C1Id">
			<m:div styleClass="padb" id="caseTypeNewARSFacTab1R1C1Div1Id">
				<h:outputLabel for="statefiscal1" value="#{msg['label.case.caseDetails.stateFiscalYr.End']}" id="caseTypeNewARSFacOutLbl1Id"/>
		   <m:br id="caseTypeNewARSFacBr1Id"/>
				<m:inputCalendar id="statefiscal1" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeNewARSFieldVO.stateFiscalYrEnd}"
					disabled="#{logCaseDataBean.disableScreenField}" />
				<m:br id="caseTypeNewARSFacBr2Id"/>
				<h:message for="statefiscal1" style="color: red" id="caseTypeNewARSFacMsg1Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>					
			</m:div>
		</m:td>

		
		<m:td id="caseTypeNewARSFacTab1R1C2Id">
			<m:div styleClass="padb" id="caseTypeNewARSFacTab1R1C2Div1Id">
				<h:outputLabel for="facilityFiscal1" value="#{msg['label.case.caseDetails.FacilityFiscalYr.End']}" id="caseTypeNewARSFacOutLbl2Id"/>
		   <m:br id="caseTypeNewARSFacBr3Id"/>
				<m:inputCalendar id="facilityFiscal1" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeNewARSFieldVO.facilityFiscalYrEnd}"
					disabled="#{logCaseDataBean.disableScreenField}" />
				<m:br id="caseTypeNewARSFacBr4Id"/>
				<h:message for="facilityFiscal1" style="color: red" id="caseTypeNewARSFacMsg2Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>				
			</m:div>
		</m:td>
		

		<m:td id="caseTypeNewARSFacTab1R1C3Id">
			<m:div styleClass="padb" id="caseTypeNewARSFacTab1R1C3Div1Id">
				<h:outputLabel for="appRq" value="#{msg['label.case.caseDeatils.appealRequest']}" id="caseTypeNewARSFacOutLbl3Id"/>
				<m:br id="caseTypeNewARSFacBr5Id"/>
				<h:selectOneRadio id="appRq" 					value="#{logCaseDataBean.caseDetailsVO.caseTypeNewARSFieldVO.appealRequest}" disabled="#{logCaseDataBean.disableScreenField}" >
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" id="caseTypeNewARSFacSelItm1Id"/>
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" id="caseTypeNewARSFacSelItm2Id"/>
				</h:selectOneRadio>				
			</m:div>
		</m:td>
	</m:tr>

</m:table>
