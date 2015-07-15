  <%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<portlet:defineObjects />

<m:table width="100%" id="NewNonARSFacTab1Id">
	<m:tr id="NewNonARSFacTab1R1Id">		
		<m:td id="NewNonARSFacTab1R1C1Id">
			<m:div styleClass="padb" id="NewNonARSFacTab1R1C1Div1Id">
				<h:outputLabel for="statefiscal" value="#{msg['label.case.caseDetails.stateFiscalYr.End']}" id="NewNonARSFacOutLb1Id"/>
		   <m:br id="NewNonARSFacTBr1Id"/>
				<m:inputCalendar id="statefiscal" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeNonARSFieldVO.stateFiscalYrEnd}"
					disabled="#{logCaseDataBean.disableScreenField}" />
				
					
				
			</m:div>
		</m:td>

		
		<m:td id="NewNonARSFacTab1R1C2Id">
			<m:div styleClass="padb" id="NewNonARSFacTab1R1C2Div1Id">
				<h:outputLabel id="PRGCMGTOLL310" for="facilityFiscal" value="#{msg['label.case.caseDetails.FacilityFiscalYr.End']}" />
		   <m:br id="NewNonARSFacTBr2Id"/>
				<m:inputCalendar id="facilityFiscal" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeNonARSFieldVO.facilityFiscalYrEnd}"
					disabled="#{logCaseDataBean.disableScreenField}" />
				
					
				
			</m:div>
		</m:td>
		
	
		<m:td id="NewNonARSFacTab1R1C3Id">
			<m:div styleClass="padb" id="NewNonARSFacTab1R1C3Div1Id">
				<h:outputLabel id="PRGCMGTOLL311" for="appRq" value="#{msg['label.case.caseDeatils.appealRequest']}" />
				<m:br id="NewNonARSFacTBr3Id"/>
				<h:selectOneRadio id="appRq" 					value="#{logCaseDataBean.caseDetailsVO.caseTypeNonARSFieldVO.appealRequest}" disabled="#{logCaseDataBean.disableScreenField}" >
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" id="NewNonARSFacTabSelItm1Id"/>
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" id="NewNonARSFacTabSelItm2Id"/>
				</h:selectOneRadio>				
			</m:div>
		</m:td>
	</m:tr>
	<%-- 
	<m:tr>			
		<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL312" for="revDtSet" value="#{msg['label.case.caseDeatils.reviewDateSet']}" />
				<m:br />
				<h:selectOneRadio id="revDtSet" 					value="#{logCaseDataBean.caseDetailsVO.caseTypeNonARSFieldVO.reviewDtSet}">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" />
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" />
				</h:selectOneRadio>				
			</m:div>
		</m:td>
	</m:tr>
	--%>
</m:table>
