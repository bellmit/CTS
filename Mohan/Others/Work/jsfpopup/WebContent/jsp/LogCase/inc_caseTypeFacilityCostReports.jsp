  <%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<m:table width="100%" id="fcrepTab1Id">
      <m:tr id="fcrepTab1R1Id">
		  <m:td id="fcrepTab1R1C1Id">
			 <m:div styleClass="padb" id="fcrepTab1R1C1Div1Id">
				<h:outputLabel for="stateFiscYearend"  id="fcrepOutLbl1Id"                    value="#{msg['label.caseDetails.FacilityCostReports.StateFiscalYearEnd']}"/>
				<m:br id="fcrepBr1Id"></m:br>
				<m:inputCalendar id="stateFiscYearend" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false"
					popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeFCRVO.stateFiscalYearEnd}" 
					disabled="#{logCaseDataBean.disableScreenField}"/>
			 </m:div>
          </m:td>
           <%--Commented for Defect ID: ESPRD00675183 --%>
           
           <%--  <m:td id="fcrepTab1R1C2Id"> 
			    <h:outputLabel for="ffyEnd"  id="fcrepOutLbl2"                    value="#{msg['label.caseDetails.FacilityCostReports.FacilityFiscalYearEnd']}"/>
				 <m:br id="fcrepBr2Id"/>
				 <m:inputCalendar id="ffyEnd" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false"
					popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeFCRVO.facilityFiscalYearEnd}" 
					disabled="#{logCaseDataBean.disableScreenField}"/>
			</m:td> --%>
      
            <m:td id="fcrepTab1R1C3Id"> 
               <m:div styleClass="padb" id="fcrepTab1R1C3Div1Id">
				  <h:outputLabel for="homeoffind" id="fcrepOutLbl3"					 value="#{msg['label.caseDetails.FacilityCostReports.HomeOfficeIndicator']}" />
				<m:br id="fcrepBr3Id"/>
				  <h:selectOneRadio id="homeoffind" value="#{logCaseDataBean.caseDetailsVO.caseTypeFCRVO.homeOfficeIndicator}"				     disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" id="fcrepSelItm1"/>
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" id="fcrepSelItm2"/>
				  </h:selectOneRadio>
                </m:div>
            </m:td>
</m:tr>
          
		<m:tr id="fcrepTab1R2Id">
         <m:td id="fcrepTab1R2C1Id">
			<m:div styleClass="padb" id="fcrepTab1R2C1Div1Id">
				<h:outputLabel for="filedaudreq" id="fcrepOutLbl4"                    value="#{msg['label.caseDetails.FacilityCostReports.FieldAuditRequired']}" />
				<m:br id="fcrepBr4Id"></m:br>
				<h:selectOneRadio id="filedaudreq"                      value="#{logCaseDataBean.caseDetailsVO.caseTypeFCRVO.fieldAuditRequired}"				disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" />
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" />
				</h:selectOneRadio>
			</m:div>
		   </m:td>
               <m:td id="fcrepTab1R2C2Id">
			      <m:div styleClass="padb" id="fcrepTab1R2C2Div1Id">
				<h:outputLabel for="FieldAuditDate" id="fcrepOutLbl5"                    value="#{msg['label.caseDetails.FacilityCostReports.FieldAuditDate']}"/>
				<m:br id="fcrepBr5Id"></m:br>
				<m:inputCalendar id="FieldAuditDate" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false"
					popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeFCRVO.fieldAuditDate}" 
					disabled="#{logCaseDataBean.disableScreenField}"/>
			 </m:div>
          </m:td>
        
          <m:td id="fcrepTab1R2C3Id">
			  <m:div styleClass="padb" id="fcrepTab1R2C3Div1Id">
				<h:outputLabel for="DeskReviewStartDate" id="fcrepOutLbl6"                    value="#{msg['label.caseDetails.FacilityCostReports.DeskReviewStartDate']}"/>
				<m:br id="fcrepBr6Id"></m:br>
				<m:inputCalendar id="DeskReviewStartDate" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false"
					popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeFCRVO.deskReviewStartDate}" 
					disabled="#{logCaseDataBean.disableScreenField}"/>
			 </m:div>
          </m:td>
           </m:tr>
  

</m:table>

  



