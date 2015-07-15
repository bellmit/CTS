  <%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />
  
<m:table width="100%" id="caseTypeHOCRTab1Id">  
<m:tr id="caseTypeHOCRTab1R1Id">
	   	                
                 
<m:td id="caseTypeHOCRTab1R1C1Id">
	            <m:div styleClass="padb" id="caseTypeHOCRDiv1Id">
				<h:outputLabel for="stateFiscYearend" value="#{msg['label.caseDetails.homeofficecostreporting.StateFiscalYearEnd']}" id="caseTypeHOCROutLbl11Id"/>
				<m:br id="caseTypeHOCROutBr1Id"/>
				<m:inputCalendar id="stateFiscYearend" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeHomeOfficeCostReportingVO.stateFiscalYearEnd}"
					disabled="#{logCaseDataBean.disableScreenField}" /> 
					</m:div>
           </m:td>

           <m:td id="caseTypeHOCRTab1R1C2Id">
			<m:div styleClass="padb" id="caseTypeHOCRDiv2Id">
				<h:outputLabel for="fiscalyearend" value="#{msg['label.caseDetails.homeofficecostreporting.FacilityFiscalYearend']}" id="caseTypeHOCROutLbl12Id"/>
				<m:br id="caseTypeHOCROutBr2Id"/>
				<m:inputCalendar id="fiscalyearend" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false"
					popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeHomeOfficeCostReportingVO.facilityFiscalYearend}"
					disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>
           </m:td>
 










<m:td id="caseTypeHOCRTab1R1C3Id"> 
			<m:div styleClass="padb" id="caseTypeHOCRDiv3Id">
				<h:outputLabel for="filedaudreq" value="#{msg['label.caseDetails.homeofficecostreporting.FieldAuditRequired']}" id="caseTypeHOCROutLbl13Id"/>
				<m:br id="caseTypeHOCROutBr3Id"/>
				<h:selectOneRadio id="filedaudreq" value="#{logCaseDataBean.caseDetailsVO.caseTypeHomeOfficeCostReportingVO.fieldAuditRequired}" disabled="#{logCaseDataBean.disableScreenField}" >
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" id="caseTypeHOCROutSelItm1Id"/>
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" id="caseTypeHOCROutSelItm2Id"/>
				</h:selectOneRadio>
			</m:div>
		   </m:td> 


<m:td id="caseTypeHOCRTab1R1C4Id"> 
			<m:div styleClass="padb" id="caseTypeHOCRDiv4Id">
				<h:outputLabel for="FieldAudDate" value="#{msg['label.caseDetails.homeofficecostreporting.FieldAuditDate']}" id="caseTypeHOCROutLbl14Id"/>
				<m:br id="caseTypeHOCROutBr4Id"/>
				<m:inputCalendar id="FieldAudDate" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false"
					popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeHomeOfficeCostReportingVO.fieldAuditDate}"
					disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>
           </m:td>

</m:tr>





</m:table>



