<%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />
<m:table width="100%" id="caseTypeAcuRaSetTab1Id">
<m:tr id="caseTypeAcuRaSetTab1R1IdId">
		<m:td id="caseTypeAcuRaSetTab1R1C1IdId">
			<m:div styleClass="padb" id="caseTypeAcuRaSetDiv1Id">
				<h:outputLabel id="PRGCMGTOLL276" for="rateEffDt" value="#{msg['label.caseDetails.AcuityRate.RateEffDate']}"/>				
				<m:br id="caseTypeAcuRaSetBr1Id"/>
				<m:inputCalendar id="rateEffDt" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeAcuityRateSettingVO.rateEffectiveDate}"
					disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>
           </m:td>

       
          
           <m:td id="caseTypeAcuRaSetTab1R1C2IdId">
		      <m:div styleClass="padb" id="caseTypeAcuRaSetDiv2Id">
			       <h:outputLabel id="PRGCMGTOLL277" for="statefiscal" value="#{msg['label.caseDetails.RateReport.StateFiscalYearEnd']}"/>
			         <m:br id="caseTypeAcuRaSetBr2Id"/>
					 <m:inputCalendar id="statefiscal" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
								value="#{logCaseDataBean.caseDetailsVO.caseTypeAcuityRateSettingVO.stateFiscYearEnd}"
								disabled="#{logCaseDataBean.disableScreenField}"/> 
		      </m:div>
	         </m:td> 
	         
	         <m:td id="caseTypeAcuRaSetTab1R1C3IdId">
				<m:div styleClass="padb" id="caseTypeAcuRaSetDiv3Id">
					<h:outputLabel id="PRGCMGTOLL278" for="pictureDate"						value="#{msg['label.case.caseDetails.AcuityRate.picturedate']}"/>
					<m:br id="caseTypeAcuRaSetBr3Id"/>
					<h:selectOneMenu id="pictureDate"						value="#{logCaseDataBean.caseDetailsVO.caseTypeAcuityRateSettingVO.pictureDate}" disabled="#{logCaseDataBean.disableScreenField}" >
						<f:selectItems 	value="#{logCaseDataBean.pictureDateList}" id="caseTypeAcuRaSetSelItm3Id"/>
					</h:selectOneMenu>
				</m:div>
			</m:td>
   

<m:td id="caseTypeAcuRaSetTab1R1C4IdId">
	<m:div styleClass="padb" id="caseTypeAcuRaSetDiv4Id">
				<h:outputLabel id="PRGCMGTOLL279" for="prvpayrates" value="#{msg['label.case.caseDetails.appealReceived']}"/>
                <m:br id="caseTypeAcuRaSetBr4Id"/>
				<h:selectOneRadio id="prvpayrates" value="#{logCaseDataBean.caseDetailsVO.caseTypeAcuityRateSettingVO.appealReceived}"				disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" id="caseTypeAcuRaSetSelItm1Id"/>
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" id="caseTypeAcuRaSetSelItm2Id"/>
				</h:selectOneRadio>
			</m:div>
		</m:td>
</m:tr>
</m:table>
