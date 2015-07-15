<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<portlet:defineObjects />
<m:table width="100%" id="arsTypeTab1Id">
	<m:tr id="arsTypeTab1R1Id">
		<m:td id="arsTypeTab1R1C1Id">
			<m:div styleClass="padb" id="arsTypeTab1R1C1Div1Id">
				<h:outputLabel for="applrcvd" id="arsTypeOutLbl1Id"					value="#{msg['label.case.caseDetails.appealReceived']}" />
				<m:br id="arsTypeBr1"></m:br>
				<h:selectOneRadio id="applrcvd"					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.appealReceivedInd}"					disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" />
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" />
				</h:selectOneRadio>
			</m:div>
		</m:td>
		<m:td id="arsTypeTab1R1C2Id">
			<m:div styleClass="padb" id="arsTypeTab1R1C2Div1Id">
				<h:outputLabel for="fldadtreqd" id="arsTypeOutLbl2Id"					value="#{msg['label.case.caseDetails.fieldAuditRequired']}" />
				<m:br id="arsTypeBr2"></m:br>
				<%-- Infinite Added for UC-PGM-CRM-018.10 Defect 3030--%>
				<h:selectOneRadio id="fldadtreqd"					valueChangeListener="#{logCaseControllerBean.fieldAuditChange}"					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.fieldAuditRequiredInd}"					onmousedown="javascript:flagWarn=false;"					disabled="#{logCaseDataBean.disableScreenField}"					onclick="this.form.submit();" immediate="true">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" />
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" />
				</h:selectOneRadio>
			</m:div>
		</m:td>
		<m:td id="arsTypeTab1R1C3Id">
			<m:div styleClass="padb" id="arsTypeTab1R1C3Div1Id">
				<%-- Infinite Added for UC-PGM-CRM-018.10 Defect 3030--%>
				<h:outputLabel for="fldadtAudit" id="arsTypeOutLbl3Id"					value="#{msg['label.caseDetails.ARS.fieldAuditDate']}" />
				<m:br id="arsTypeBr3"></m:br>
				<m:inputCalendar id="fldadtAudit" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.fieldAuditDateStr}"
					disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>
			<h:message for="fldadtAudit" styleClass="errorMessage" id="arsTypemsg1Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			<m:br></m:br>
		</m:td>
		<m:td id="arsTypeTab1R1C4Id">
			<m:div styleClass="padb"id="arsTypeTab1R1C4Div1Id">
				<h:outputLabel for="homeoffind" id="arsTypeOutLbl4Id"					value="#{msg['label.case.caseDetails.homeOfficeIndicator']}" />
				<m:br id="arsTypeBr4"></m:br>
				<h:selectOneRadio id="homeoffind"					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.homeOfficeIndicator}"					disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" />
					<f:selectItem itemValue="fasle" itemLabel="#{msg['label.no']}" />
				</h:selectOneRadio>
			</m:div>
		</m:td>

<%-- Infinite Added for Defect ESPRD00344256 --%>
<m:td id="arsTypeTab1R1C5Id">
			<m:div styleClass="padb" id="arsTypeTab1R1C5Div1Id">
				<h:outputLabel for="facilityFiscal" id="arsTypeOutLbl5Id"					value="#{msg['label.case.caseDetails.facilityFiscalYr.End']}" />
				<m:br id="arsTypeBr5"/>
				<m:inputCalendar id="facilityFiscal" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.facilityFiscalYearEndDate}"
					disabled="#{logCaseDataBean.disableScreenField}" />
					<m:br />
				<h:message for="facilityFiscal" style="color: red" id="arsTypemsg2Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:div>

		</m:td>


	</m:tr>
	<m:tr id="arsTypeTab1R2Id">
		<%--<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL280" for="picDt"					value="#{msg['label.case.caseDetails.pictureDate']}" />
				<m:br />
				<h:selectOneMenu id="picDt"					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.pictureDate}"					disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemValue="" itemLabel="" />
					<f:selectItem itemValue="1" itemLabel="Please Select One" />
				</h:selectOneMenu>
			</m:div>
		</m:td>
		--%>
	 <m:td id="arsTypeTab1R2C1Id">
				<m:div styleClass="padb" id="arsTypeTab1R2C1Div1Id">
					<h:outputLabel for="pictureDate" id="arsTypeOutLbl6Id"						value="#{msg['label.case.caseDetails.AcuityRate.picturedate']}"/>
					<m:br id="arsTypeBr6"/>
					<h:selectOneMenu id="pictureDate"						value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.pictureDateStr}" disabled="#{logCaseDataBean.disableScreenField}" >
						<f:selectItems 	value="#{logCaseDataBean.pictureDateList}"/>
					</h:selectOneMenu>
				</m:div>
			</m:td>
	
		
		<m:td id="arsTypeTab1R2C2Id">
			<m:div styleClass="padb" id="arsTypeTab1R2C2Div1Id">
				<h:outputLabel for="ratestngdat" id="arsTypeOutLbl7Id"					value="#{msg['label.case.caseDetails.rateSettingDate']}" />
				<m:br id="arsTypeBr7"/>
				<m:inputCalendar id="ratestngdat" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.rateSettingDateStr}"
					disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>
			<h:message for="ratestngdat" styleClass="errorMessage" id="arsTypemsg3Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			<m:br></m:br>
		</m:td>
		<m:td id="arsTypeTab1R2C3Id">
			<m:div styleClass="padb" id="arsTypeTab1R2C3Div1Id">
				<h:outputLabel for="pvtloded" id="arsTypeOutLbl8Id"					value="#{msg['label.case.caseDetails.privatePayRatesLoaded']}" />
				<m:br id="arsTypeBr8"></m:br>
				<h:selectOneRadio id="pvtloded"					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.privatePayRatesLoadedInd}"					disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" />
					<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" />
				</h:selectOneRadio>
			</m:div>
		</m:td>
	

    <m:td id="arsTypeTab1R2C4Id">
			<m:div styleClass="padb" id="arsTypeTab1R2C4Div1Id">
				<h:outputLabel for="deskrvdate" id="arsTypeOutLbl9Id"					value="#{msg['label.case.caseDetails.deskRvstartDate']}" />
				<m:br id="arsTypeBr9"/>
				<m:inputCalendar id="deskrvdate" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.deskReviewStartDate}"
					disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>
			<h:message for="deskrvdate" styleClass="errorMessage" id="arsTypemsg4Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			<m:br></m:br>
		</m:td>


		

		<%--Infinite added for UC-PGM-CRM-018.10, CR 1841  --%>


		<m:td id="arsTypeTab1R2C5Id">
			<m:div styleClass="padb" id="arsTypeTab1R2C5Div1Id">
				<h:outputLabel for="statefiscal" id="arsTypeOutLbl10Id"					value="#{msg['label.case.caseDetails.stateFiscalYr.End']}" />
				<m:br id="arsTypeBr10"/>
				<m:inputCalendar id="statefiscal" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeARSVO.stateFiscalYearEndDate}"
					disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>

		</m:td>



	</m:tr>
</m:table>
<m:br />
<m:div rendered="#{logCaseDataBean.showCommonTypeScreen}" id="arsTypeDiv1Id">
	<f:subview id="caseTypeCommonARS">
		<jsp:include page="inc_caseTypeCommonPage.jsp" />
	</f:subview>
</m:div>
