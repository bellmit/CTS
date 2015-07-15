  <%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>


<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<portlet:defineObjects />

<m:table width="100%" id="caseTypeFldAudTab1Id">
	<m:tr id="caseTypeFldAudTab1R1Id">		
		<m:td id="caseTypeFldAudTab1R1C1Id">
			<m:div styleClass="padb" id="caseTypeFldAudDiv1Id">
				<h:outputLabel for="statefiscal" value="#{msg['label.case.caseDetails.stateFiscalYr.End']}" 				id="caseTypeFldAudOutLbl1Id"/>
				<m:br id="caseTypeFldAudBr1Id"/>
			<m:inputCalendar id="statefiscal" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeFieldAuditVO.stateFiscalYrEnd}"
					disabled="#{logCaseDataBean.disableScreenField}"/>
				
				
			</m:div>
		</m:td>

	
	
		<m:td id="caseTypeFldAudTab1R1C2Id">
			<m:div styleClass="padb" id="caseTypeFldAudDiv2Id">
			  <h:outputLabel for="facilityfiscal" value="#{msg['label.case.caseDetails.FacilityFiscalyearEnd']}" 			  id="caseTypeFldAudOutLbl2Id"/>
			<m:br  id="caseTypeFldAudBr2Id"/>
			<m:inputCalendar id="facilityfiscal" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
							currentDayCellClass="currentDayCell" renderAsPopup="true"
							addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
							renderPopupButtonAsImage="true"
					    value="#{logCaseDataBean.caseDetailsVO.caseTypeFieldAuditVO.facilityFiscalYearEnd}"
					    disabled="#{logCaseDataBean.disableScreenField}"/>
			</m:div>
		</m:td>
	
		<m:td id="caseTypeFldAudTab1R1C3Id">
		<m:div styleClass="padb" id="caseTypeFldAudDiv3Id">
			<h:outputLabel for="fldAdtDate" value="#{msg['label.case.caseDeatils.fieldAuditDate']}" id="caseTypeFldAudOutLbl3Id"/>
			<m:br id="caseTypeFldAudBr3Id"></m:br>
			<m:inputCalendar id="fldAdtDate" size="10"
				monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
				currentDayCellClass="currentDayCell" renderAsPopup="true"
				addResources="true" readonly="false"
				popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
				value="#{logCaseDataBean.caseDetailsVO.caseTypeFieldAuditVO.fieldAuditDate}"
				disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>
			<h:message id="PRGCMGTM127" for="fldAdtDate" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>				
			<m:br></m:br>
		</m:td>
		<m:td id="caseTypeFldAudTab1R1C4Id">
			<m:div styleClass="padb" id="caseTypeFldAudDiv4Id" >
				<h:outputLabel for="homeoffind" id="caseTypeFldAudOutLbl4Id"					value="#{msg['label.case.caseDetails.homeOfficeIndicator']}" />
				<m:br id="caseTypeFldAudBr4Id"></m:br>
				<h:selectOneRadio id="homeoffind"					value="#{logCaseDataBean.caseDetailsVO.caseTypeFieldAuditVO.homeOfficeInd}"					disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" id="caseTypeFldAudSelItm1Id"/>
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" id="caseTypeFldAudSelItm2Id"/>
				</h:selectOneRadio>
			</m:div>
		</m:td>
		</m:tr>
		
</m:table>
