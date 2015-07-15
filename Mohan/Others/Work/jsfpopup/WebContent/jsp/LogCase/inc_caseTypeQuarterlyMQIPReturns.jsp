  <%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>


<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<portlet:defineObjects />

<m:table width="100%" id="MQIPReturnsTab1Id">
	<m:tr id="MQIPReturnsTab1R1Id">
		<m:td id="MQIPReturnsTab1R1C1Id">
			<m:div styleClass="padb" id="MQIPReturnsTab1R1C1Div1Id">
				<h:outputLabel for="statefiscal"  id="MQIPReturnsOutlbl"					value="#{msg['label.case.casedetails.stateFiscalYearEnd']}"/>
				<m:br id="MQIPReturnsBr1"/>
				<m:inputCalendar id="statefiscal" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
			 value="#{logCaseDataBean.caseDetailsVO.caseTypeQuarterlyMQIPReturnsVO.stateFiscalYearEnd}"
			 disabled="#{logCaseDataBean.disableScreenField}" />
					
			</m:div>
		</m:td>
	</m:tr>	
	
</m:table>
