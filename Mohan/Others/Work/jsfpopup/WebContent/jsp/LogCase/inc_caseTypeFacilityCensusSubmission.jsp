  <%--Infinite added for UC-PGM-CRM-018.10, CR 2401  --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<m:table width="100%" id="caseTypeFacSensSubmTab1Id">
	<m:tr id="caseTypeFacSensSubmTab1R1Id">
	
	   <m:td id="caseTypeFacSensSubmTab1R1C1Id">
		<m:div styleClass="padb" id="caseTypeFacSensSubmDiv1Id">
			<h:outputLabel for="statefiscal" value="#{msg['label.caseDetails.facilitycensussub.stateFiscalYr.End']}"			id="caseTypeFacSensSubmOutLbl1Id" />
			<m:br id="caseTypeFacSensSubmBr1Id" />
		  <m:inputCalendar id="statefiscal" size="10" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
						   currentDayCellClass="currentDayCell" renderAsPopup="true"
					       addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy" 
						  renderPopupButtonAsImage="true"
						value="#{logCaseDataBean.caseDetailsVO.caseTypeFacilityCensusSubmissionVO.stateFiscYrEnd}"
						disabled="#{logCaseDataBean.disableScreenField}"/> 
		</m:div>
				
	         </m:td>
	
		<m:td id="caseTypeFacSensSubmTab1R1C2Id">
			<m:div styleClass="padb" id="caseTypeFacSensSubmDiv2Id">
				<h:outputLabel for="picturedate" value="#{msg['label.caseDetails.facilitycensussub.PictureDate']}"				id="caseTypeFacSensSubmOutLbl2Id"/>
				<m:br id="caseTypeFacSensSubmBr2Id"/>
	  			<h:selectOneMenu id="picturedate" 					value="#{logCaseDataBean.caseDetailsVO.caseTypeFacilityCensusSubmissionVO.pictureDate}" disabled="#{logCaseDataBean.disableScreenField}" >
					<f:selectItem itemLabel="Please Select One" itemValue=" " id="caseTypeFacSensSubmSelItm1Id"/>
					<f:selectItems value="#{logCaseDataBean.pictureDateList}" />
				</h:selectOneMenu>
			</m:div>
	</m:td>
</m:tr>    

<m:tr id="caseTypeFacSensSubmTab1R2Id">
 <m:td id="caseTypeFacSensSubmTab1R2C1Id">
 <m:div  align="left" id="caseTypeFacSensSubmDiv3Id">
<t:htmlTag value="h5" id="caseTypeFacSensSubmHtmlTag1Id">
				<h:outputText  value="#{msg['label.case.caseDetails.facilitycensussub.censusstatus']}" id="caseTypeFacSensSubmOutTxt1Id">
				</h:outputText> 
			</t:htmlTag>
			
                 
</m:div>
 
 </m:td>


</m:tr>      
	
</m:table>

