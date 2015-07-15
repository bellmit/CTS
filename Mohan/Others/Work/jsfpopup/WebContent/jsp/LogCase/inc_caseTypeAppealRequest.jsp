  <%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<portlet:defineObjects />

<m:table width="100%" id="appealReqTab1Id"> 
	 <m:tr id="appealReqTab1R1Id">
		
		<m:td id="appealReqTab1R1C1Id">
			<m:div styleClass="padb" id="appealReqTab1R1C1Div1Id">
				<h:outputLabel for="statefiscal" value="#{msg['label.case.caseDetails.stateFiscalYr.End']}" id="appealReqOutLbl1Id"/>
				<m:br id="appealReqBr1Id"/>
				<m:inputCalendar id="statefiscal" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeAppealRequestVO.stateFiscalYrEnd}"
					disabled="#{logCaseDataBean.disableScreenField}"/>
				</m:div>
		</m:td>
		
		<m:td id="appealReqTab1R1C2Id">
			<m:div styleClass="padb" id="appealReqTab1R1C2Div1Id">
				<h:outputLabel for="rateSettingDate" value="#{msg['label.case.caseDetails.RateSettingDate']}" id="appealReqOutLbl2Id"/>
				<m:br id="appealReqBr2Id"/>
				<m:inputCalendar id="rateSettingDate" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeAppealRequestVO.rateSettingDate}"
					disabled="#{logCaseDataBean.disableScreenField}"/>
				</m:div>
		</m:td>
		
	</m:tr>
	
</m:table>
