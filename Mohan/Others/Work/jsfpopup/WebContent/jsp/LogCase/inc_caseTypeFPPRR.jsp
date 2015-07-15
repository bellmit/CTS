<%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>

<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />
<m:table width="100%" id="caseTypeFpprrTab1Id">
        <m:tr id="caseTypeFpprrTab1R1Id">
             
             <m:td id="caseTypeFpprrTab1R1C1Id">
		     <m:div styleClass="padb" id="caseTypeFpprrDiv1Id">
			<h:outputLabel for="sfyEnd" value="#{msg['label.case.caseDetails.stateFiscalYearEnd']}" id="caseTypeFpprrOutLblId"/>
			<m:br id="caseTypeFpprrBr1Id"/>
			<m:inputCalendar id="sfyEnd" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
								renderPopupButtonAsImage="true"
								value="#{logCaseDataBean.caseDetailsVO.caseTypeFPPRRVO.stateFiscalYearEnd}"
								disabled="#{logCaseDataBean.disableScreenField}" /> 
		</m:div>
				
	         </m:td>  
              <m:tr /> 
          <m:td id="caseTypeFpprrTab1R1C2Id">
				<h:outputLabel id="PRGCMGTOLL309" for="pprLoaded"					value="#{msg['label.case.caseDetails.pprLoaded']}" />
				<m:br id="caseTypeFpprrBr2Id"/>
				<h:selectOneRadio id="pprLoaded" value="#{logCaseDataBean.caseDetailsVO.caseTypeFPPRRVO.privatePayRatesLoadedInd}"				disabled="#{logCaseDataBean.disableScreenField}">
	 				<f:selectItem itemLabel="#{msg['label.yes']}" itemValue="true" id="caseTypeFpprrSel1Id"/>
					<f:selectItem itemLabel="#{msg['label.no']}" itemValue="false" id="caseTypeFpprrSel2Id"/>
				</h:selectOneRadio>
			</m:td>
 
       </m:tr>
</m:table>
