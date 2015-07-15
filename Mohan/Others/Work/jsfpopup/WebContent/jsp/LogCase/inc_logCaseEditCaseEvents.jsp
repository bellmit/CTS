<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<script type="text/javascript">
function caseEventDeletedeleteConfirmation(){
if(confirm('Are you sure you want to delete?'))
{
	return true;
	}else{
		flagWarn=true;
		return false;
		}
}

</script>
<m:div id="EditCaseEventDivId1" styleClass="moreInfo" >
	<m:div id="EditCaseEventDivId2" styleClass="moreInfoBar">
		<m:div id="EditCaseEventDivId3" styleClass="infoTitle">
			<h:outputText id="EditCaseEventOutTextId1" value="#{msg['label.case.caseEvents.editCaseEvent']}" />
		</m:div>
		<m:div id="LCSEEEVENTSGNRICMSG0" styleClass="width:100%">
			<h:inputHidden id="EVENTSGNRICMSGHDN0" value=" "></h:inputHidden>
			<h:message id="PROVIDERM20120731164811299" for="EVENTSGNRICMSGHDN0" styleClass="errorMessage"></h:message>
		</m:div>
		<m:div id="EditCaseEventDivId4" styleClass="infoActions">
			<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543--%>
			<t:commandLink id="saveCaseEventsId2" action="#{logCaseControllerBean.saveCaseEvents}" onclick="javascript:flagWarn=false;" rendered="#{!logCaseDataBean.disableCaseEventSave && !(logCaseDataBean.disableScreenField)}">
				<h:outputText id="EditCaseEventOutTextId2" value="#{msg['label.case.save']}" styleClass="strong"/>
			</t:commandLink>
			<h:outputText id="EditCaseEventOutTextId3" value="#{msg['label.case.save']}" styleClass="strong" rendered="#{logCaseDataBean.disableCaseEventSave || logCaseDataBean.disableScreenField}"/>
			<h:outputText id="EditCaseEventOutTextId4" escape="false" value="&nbsp;"/>
			<h:outputText id="EditCaseEventOutTextId5" value="|"/>
			<h:outputText id="EditCaseEventOutTextId6" escape="false" value="&nbsp;"/>
			<h:panelGroup id="templateTarget2">
			<t:commandLink id="EditCaseEventLinkId1" action="#{logCaseControllerBean.createLetterForEvent}" rendered="#{((!LettersAndResponsesDataBean.updateLtrAndRespFlag))&&(logCaseDataBean.caseEventsCreateLetterStatus)}"  onmousedown="javascript:flagWarn=false;">
				<h:outputText id="EditCaseEventOutTextId21" value="#{msg['label.case.createLetter']}"/>
				  <f:param id="editCaseEventCreateLetterParam1" name="send.CommonLetterInputData.Action" value="SendCommonLetterInputAction"></f:param>
                  <f:param id="editCaseEventCreateLetterParam2" name="funcSK"   value="#{LettersAndResponsesDataBean.funcSK}"></f:param>
                  <f:param id="editCaseEventCreateLetterParam3" name="funcArea" value="#{LettersAndResponsesDataBean.funcArea}"></f:param>
                  <f:param id="editCaseEventCreateLetterParam4" name="LETTER_CATEGORY"     value="#{LettersAndResponsesDataBean.letterCategory}"></f:param>			
                  <f:param id="editCaseEventCreateLetterParam5" name="LogCaseEventSeqNum"     value="#{logCaseDataBean.caseEventsVO.caseEventSeqNumStr}"></f:param>	
			</t:commandLink>
			<h:outputText id="EditCaseEventOutTextId22" value="#{msg['label.case.createLetter']}" rendered="#{((LettersAndResponsesDataBean.updateLtrAndRespFlag))|| !(logCaseDataBean.caseEventsCreateLetterStatus)}" />

			</h:panelGroup>
			<hx:ajaxRefreshSubmit id="logCaseEditCaseEventSubmitId1" target="templateTarget2"/>
			<h:outputText id="EditCaseEventOutTextId18" escape="false" value="&nbsp;"/><h:outputText id="EditCaseEventOutTextId19" value="|"/><h:outputText id="EditCaseEventOutTextId20" escape="false" value="&nbsp;"/>
			<t:commandLink id="deleteCaseEventsId" action="#{logCaseControllerBean.deleteCaseEvents}" onclick="javascript:flagWarn=false;focusThis('addCaseEventId');focusPage('caseEventsHeader');return caseEventDeletedeleteConfirmation();" rendered="#{!logCaseDataBean.disableCaseEventDelete && !(logCaseDataBean.disableScreenField)}">
				<h:outputText id="EditCaseEventOutTextId12" value="#{msg['label.case.delete']}"/>
			</t:commandLink>
			<h:outputText id="EditCaseEventOutTextId13" value="#{msg['label.case.delete']}" rendered="#{logCaseDataBean.disableCaseEventDelete || logCaseDataBean.disableScreenField}"/>
<%-- EOF modification for defect ID ESPRD00299794 --%>
			<h:outputText id="EditCaseEventOutTextId14" escape="false" value="&nbsp;"/><h:outputText id="EditCaseEventOutTextId15" value="|"/><h:outputText id="EditCaseEventOutTextId16" escape="false" value="&nbsp;"/>
			<t:commandLink id="resetCaseEventsPageId2" action="#{logCaseControllerBean.resetCaseEventsPage}" onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);focusPage('caseEventsEditHeader');" rendered="#{!logCaseDataBean.disableCaseEventReset && !(logCaseDataBean.disableScreenField)}">
				<h:outputText id="EditCaseEventOutTextId7" value="#{msg['label.case.reset']}"/>
			</t:commandLink>
			<h:outputText id="EditCaseEventOutTextId8" value="#{msg['label.case.reset']}" rendered="#{logCaseDataBean.disableCaseEventReset || logCaseDataBean.disableScreenField}"/>
			<h:outputText id="EditCaseEventOutTextId9" escape="false" value="&nbsp;"/><h:outputText id="EditCaseEventOutTextId10" value="|"/><h:outputText id="EditCaseEventOutTextId11" escape="false" value="&nbsp;"/>
			<t:commandLink id="cancelCaseEventsId2" action="#{logCaseControllerBean.cancelCaseEvents}" onmousedown="javascript:focusThis('caseEventsTable');focusPage('caseEventsHeader');">
				<h:outputText id="EditCaseEventOutTextId17" value="#{msg['label.case.cancel']}" />
			</t:commandLink>
			
<%-- EOF defect ESPRD00332961--%>
		</m:div>
	</m:div>
	<m:div id="EditCaseEventDivId5" styleClass="moreInfoContent">
<%--Modified for defect ESPRD00446264 starts--%>
<%--<m:div id="EditCaseEventDivId6"
		rendered="#{logCaseDataBean.showCaseEventsMessages}"
		styleClass="msgBox">
		<h:messages layout="table" showDetail="true" styleClass="successMsg"			id="editEventsErrorMessage" showSummary="false">
		</h:messages>
	</m:div>--%>
	
	<%--Defect ESPRD00446264 Fix ends--%>
		<m:div id="EditCaseEventDivId7" styleClass="width:100%">
			<m:table id="EditCaseEventTableId1" cellspacing="0" width="95%">
				<m:tr id="EditCaseEventTrd1">
					<m:td id="EditCaseEventTdId1">
						<m:div id="EditCaseEventDivId8" styleClass="padb">
							<h:outputText id="EditCaseEventOutTextId23" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputText id="EditCaseEventOutTextId24"								value="#{msg['label.case.caseEvents.createdDate']}" styleClass="outputLabel"/>
							<m:br id="EditCaseEventBrId1" />
							<h:outputText id="EditCaseEventOutTextId25" value="#{logCaseDataBean.caseEventsVO.createDateStr}"/>
						</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId2">
						<m:div id="EditCaseEventDivId9" styleClass="padb">
							<h:outputText id="EditCaseEventOutTextId26" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="editCaseEventsLabel1" for="type2"								value="#{msg['label.case.caseEvents.type']}" />
							<m:br id="EditCaseEventBrId2" />
							<h:selectOneMenu id="type2"  immediate="true" disabled="#{logCaseDataBean.disableScreenField}"
								value="#{logCaseDataBean.caseEventsVO.eventTypeCd}"
								valueChangeListener="#{logCaseControllerBean.showDDUAppoint}">
								<%-- this.form.submit()--%>
								<f:selectItems value="#{logCaseDataBean.eventTypeList}"
									id="editCaseEventSelItm1Id" />
							</h:selectOneMenu>
							<hx:behavior id="type21" event="onchange" behaviorAction="get"
								target="type2" targetAction="showDDUAppointmentScreenEditTarget"></hx:behavior>
							<m:br id="EditCaseEventBrId3" />
							<h:message id="editCaseEvenmsg2Id" for="type2" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId3">
						<h:outputText id="EditCaseEventOutTextId27" value="#{msg['label.case.astrisk']}" styleClass="required" />
						<h:outputLabel id="editCaseEventsLabel2" for="eventDescription2"							value="#{msg['label.case.caseEvents.description']}" />
						<m:br id="EditCaseEventBrId4" />
						<h:inputText id="eventDescription2" size="30" disabled="#{logCaseDataBean.disableScreenField}" value="#{logCaseDataBean.caseEventsVO.desc}"  style="WIDTH:190px"/>
					<%-- Added for the defect UC-PGM-CRM-018_ESPRD00708917 --%>
						<m:br id="EditCaseEventBrId31" />
						<h:message id="editCaseEvenmsg12Id" for="eventDescription2" styleClass="errorMessage"/>
					</m:td>
					<m:td id="EditCaseEventTdId4">
						<m:div id="EditCaseEventDivId10" styleClass="padb">
							<h:outputText id="EditCaseEventOutTextId28" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="editCaseEventsLabel3" value="#{msg['label.case.caseEvents.eventDate']}" for="eventDate2"/>
							<m:br id="EditCaseEventBrId5" />
							<m:inputCalendar id="eventDate2" size="10"
								monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false"
								popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
								value="#{logCaseDataBean.caseEventsVO.eventDateStr}" 
								disabled="#{logCaseDataBean.disableScreenField}" />
							<m:br id="EditCaseEventBrId6" />
							<h:message for="eventDate2" styleClass="errorMessage" id="editCaseEvenmsg1Id1"/>
							<!-- Added for ESPRD00357689 -->
							<m:br id="EditCaseEventBrId7" />
							<%--Modified for defect ESPRD00674035--%>
							<m:div id="EditCaseEventDivId11" rendered="#{logCaseDataBean.invalidEventDate}">
								<h:outputText id="EditCaseEventOutTextId29"									value="#{msg['error.logcase.caseevents.invalid.dateformate']}"									rendered="#{logCaseDataBean.invalidEventDate}"									styleClass="errorMessage" />
								<m:br id="EditCaseEventBrId8" />
								<h:outputText id="EditCaseEventOutTextId30"									value="#{msg['error.logcase.caseevents.invalid.dateformate1']}"									rendered="#{logCaseDataBean.invalidEventDate}"									styleClass="errorMessage" />
								<m:br id="EditCaseEventBrId9" />
								<h:outputText id="EditCaseEventOutTextId31"									value="#{msg['error.logcase.caseevents.invalid.dateformate2']}"									rendered="#{logCaseDataBean.invalidEventDate}"									styleClass="errorMessage" />
								<m:br id="EditCaseEventBrId10" />
								<h:outputText id="EditCaseEventOutTextId32"									value="#{msg['error.logcase.caseevents.invalid.dateformate3']}"									rendered="#{logCaseDataBean.invalidEventDate}"									styleClass="errorMessage" />
							</m:div>
							<!-- EOF ESPRD00357689 -->
					</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId5" colspan="2">
						<h:outputLabel id="editCaseEventsLabel4" for="time_2"							value="#{msg['label.case.caseEvents.time']}" />
						<m:br id="EditCaseEventBrId11" />
						<h:panelGrid columns="2" width="105%" cellpadding="0"							cellspacing="0" id="editCaseEventPg1">
							<h:inputText id="time_2" size="4" disabled="#{logCaseDataBean.disableScreenField}" value="#{logCaseDataBean.caseEventsVO.time}"/>
							<h:selectOneRadio id="PRGCMGTSOR1" disabled="#{logCaseDataBean.disableScreenField}" value="#{logCaseDataBean.caseEventsVO.amPM}">
								<f:selectItem itemValue="AM" itemLabel="#{msg['label.am']} " id="editCaseEventSelItm13Id"/>
								<f:selectItem itemValue="PM" itemLabel="#{msg['label.pm']} " id="editCaseEventSelItm12Id"/>
							</h:selectOneRadio>
							<h:message id="editCaseEvenmsg5Id" for="time_2" styleClass="errorMessage"/>
							<h:message id="editCaseEvenAmPmId" for="PRGCMGTSOR1" styleClass="errorMessage"/>
						</h:panelGrid>
					</m:td>
				</m:tr>
				<m:tr id="EditCaseEventTrd2">
					<m:td id="EditCaseEventTdId6">
						<m:div id="EditCaseEventDivId12" styleClass="padb">
							<h:outputLabel id="editCaseEventsLabel5" for="notifyViaAlerts2"								value="#{msg['label.case.caseEvents.notifyViaAlert']}" />
							<m:br id="EditCaseEventBrId12"></m:br>
							<h:selectOneMenu id="notifyViaAlerts2" disabled="#{logCaseDataBean.disableScreenField}" immediate="true"
								value="#{logCaseDataBean.caseEventsVO.notifyViaAlert}"
								valueChangeListener="#{logCaseControllerBean.disableCaseEventFieldsnotifyViaAlert}">
								<f:selectItem itemLabel="" itemValue=" "/>
								<f:selectItems value="#{logCaseDataBean.eventNotifyList}"
									id="editCaseEventSelItm2Id" />
							</h:selectOneMenu>
							<hx:behavior id="notifyViaAlerts21" event="onchange"
								behaviorAction="get" target="notifyViaAlerts2"
								targetAction="alertOrsendAlertDaysTarget2"></hx:behavior>
						</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId7" colspan="2">
						<h:panelGroup id="alertOrsendAlertDaysTarget2">
						<h:panelGrid columns="2" cellspacing="0" id="logCaseditPNGD02">
						<m:div id="EditCaseEventDivId13" styleClass="padb">
							<h:outputLabel id="editCaseEventsLabel6" for="alert_Based_On"								value="#{msg['label.case.caseEvents.alertBasedOn']}" />
							<m:br id="EditCaseEventBrId13"></m:br>
							<h:selectOneMenu id="alert_Based_On" value="#{logCaseDataBean.caseEventsVO.alertBasedOn}" disabled="#{logCaseDataBean.disableCaseEventFields || logCaseDataBean.disableScreenField}">
								<f:selectItems value="#{logCaseDataBean.eventAlertBasedOn}" id="editCaseEventSelItm3Id"/>
							</h:selectOneMenu>
						</m:div>
		<%---		</m:td>
					<m:td id="EditCaseEventTdId8">--%>
						<m:div id="EditCaseEventDivId14" styleClass="padb">
							<h:outputLabel id="editCaseEventsLabel7"								value="#{msg['label.case.caseEvents.sendalertDaysBefAfter']}" for="sendAlertDays"/>
							<m:br id="EditCaseEventBrId14" />
							<h:panelGrid columns="2" width="100%" cellpadding="0"								cellspacing="0" id="editCaseEventPg2">
								<h:selectOneMenu id="sendAlertDays" value="#{logCaseDataBean.caseEventsVO.sendAlertDaysCd}" disabled="#{logCaseDataBean.disableCaseEventFields || logCaseDataBean.disableScreenField}">
									<f:selectItem itemValue=" " itemLabel="" id="iSelectItem"/>
									<f:selectItems value="#{logCaseDataBean.eventAlertDaysList}" id="editCaseEventSelItm4Id"/>
								</h:selectOneMenu>
								<m:div id="EditCaseEventDivBeforeAfterId">
								<h:selectOneRadio value="#{logCaseDataBean.caseEventsVO.afterOrBeforeCd}" id="editBeforeAfterId" disabled="#{logCaseDataBean.disableCaseEventFields || logCaseDataBean.disableScreenField}">
									<f:selectItem itemValue="before" itemLabel="#{msg['label.caseSteps.before']}" id="editCaseEventSelItm15Id"/>
									<f:selectItem itemValue="after" itemLabel="#{msg['label.caseSteps.after']}" id="editCaseEventSelItm14Id"/>
								</h:selectOneRadio>
								<m:br id="EditCaseEventBrBeforeAfterId"/>
								<h:message for="editBeforeAfterId" styleClass="errorMessage" id="editCaseEventBeforAfterId" />
								</m:div>
							</h:panelGrid>
						</m:div>
						</h:panelGrid>
					</h:panelGroup>
					<hx:ajaxRefreshSubmit id="logCaseEditCaseEventSubmitId3" target="alertOrsendAlertDaysTarget2"/>
					</m:td>
					<m:td id="EditCaseEventTdId9">
						<m:div id="EditCaseEventDivId15" styleClass="padb">
							<h:outputText id="EditCaseEventOutTextId2345" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="editCaseEventsLabel8" for="LOGCASEEVENTSTATUS1"								value="#{msg['label.case.caseSteps.status']}" />
								
							<m:br id="EditCaseEventBrId15"></m:br>
							<h:selectOneMenu id="LOGCASEEVENTSTATUS1" disabled="#{logCaseDataBean.disableScreenField}"
								value="#{logCaseDataBean.caseEventsVO.statusCd}"
								onchange="javascript:flagWarn=false;dispositionDateForEventAction();" >
								<f:selectItems value="#{logCaseDataBean.eventStatus}"
									id="editCaseEventSelItm5Id" />
							</h:selectOneMenu>
							<m:br  id="brId2346712" />
							<h:message for="LOGCASEEVENTSTATUS1" styleClass="errorMessage" id="editCaseEventMsg2346712Id"/>
							<%--<hx:behavior id="status_31" event="onchange" behaviorAction="get"
								target="LOGCASEEVENTSTATUS1"
								targetAction="dispositionDate2Target"></hx:behavior>--%>
						</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId10">
						<m:div id="EditCaseEventDivId16" styleClass="padb">
							<h:outputLabel id="editCaseEventsLabel9"								value="#{msg['label.case.caseEvents.dispositionDate']}" for="dispositionDate2"/>
							<m:br id="EditCaseEventBrId16" />
							<h:panelGroup id="dispositionDate2Target">
							<m:inputCalendar id="dispositionDate2" size="10"
                                disabled="#{logCaseDataBean.caseEventsVO.dateDispositionFlag || logCaseDataBean.disableScreenField}"
								monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false"
								popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
								value="#{logCaseDataBean.caseEventsVO.dispositionDateStr}" />
							</h:panelGroup>
							<%--<hx:ajaxRefreshSubmit id="logCaseEditCaseEventSubmitId4" target="dispositionDate2Target"/>--%>
							<m:br id="EditCaseEventBrId17" />
							<h:message id="editCaseEvenmsg4Id" for="dispositionDate2" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId11">
						<m:div id="EditCaseEventDivId17" styleClass="padb">
							<h:outputLabel id="editCaseEventsLabel10" value="#{msg['label.case.caseEvents.template']}" for="LOGCASETEMPLATE1"/>
							<m:br id="EditCaseEventBrId18" />
							<h:selectOneMenu id="LOGCASETEMPLATE1" disabled="#{logCaseDataBean.disableScreenField}"
								value="#{logCaseDataBean.caseEventsVO.template}"
								valueChangeListener="#{logCaseControllerBean.enableDisableCreateLetterLinkForCaseEvents}"
								immediate="true">
								<%--this.form.submit()--%>
								<f:selectItems value="#{logCaseDataBean.templateList}"
									id="editCaseEventSelItm6Id" />
							</h:selectOneMenu>
							<hx:behavior id="templateID21" event="onchange" behaviorAction="get"  target="LOGCASETEMPLATE1" targetAction="templateTarget2"></hx:behavior>
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
			<h:panelGroup id="showDDUAppointmentScreenEditTarget">
			<m:table id="EditCaseEventTableId2" cellspacing="0" width="95%">
				<m:tr id="EditCaseEventTrd3" rendered="#{logCaseDataBean.showDDUAppointmentScreen}">
					<m:td id="EditCaseEventTdId12">
						<m:div id="EditCaseEventDivId18" styleClass="padb">
							<h:outputLabel id="editCaseEventsLabel11" for="editdiag1" value="#{msg['label.caseEvents.diagnosis1']}" />
							<m:br id="EditCaseEventBrId19" />
							<h:selectOneMenu id="editdiag1" disabled="#{logCaseDataBean.disableScreenField}" value="#{logCaseDataBean.caseEventsVO.diagnosisCode1}">
								<f:selectItems value="#{logCaseDataBean.diagnosisCode12}" id="editCaseEventSelItm7Id"/>
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId13">
						<m:div id="EditCaseEventDivId19" styleClass="padb">
							<h:outputLabel id="editCaseEventsLabel12" for="editdiag2" value="#{msg['label.caseEvents.diagnosis2']}" />
							<m:br id="EditCaseEventBrId20" />
							<h:selectOneMenu id="editdiag2" disabled="#{logCaseDataBean.disableScreenField}" value="#{logCaseDataBean.caseEventsVO.diagnosisCode2}">
								<f:selectItems value="#{logCaseDataBean.diagnosisCode12}" id="editCaseEventSelItm11Id"/>
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId14">
						<m:div id="EditCaseEventDivId20" styleClass="padb">
							<h:outputLabel id="editCaseEventsLabel13" for="editExam1" value="#{msg['label.caseEvents.exam1']}" />
							<m:br id="EditCaseEventBrId21" />
							<h:selectOneMenu id="editExam1" disabled="#{logCaseDataBean.disableScreenField}" value="#{logCaseDataBean.caseEventsVO.examCode1}">
								<f:selectItems value="#{logCaseDataBean.examTest12}" id="editCaseEventSelItm10Id"/>
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId15">
						<m:div id="EditCaseEventDivId21" styleClass="padb">
							<h:outputLabel id="editCaseEventsLabel14" for="editExam2" value="#{msg['label.caseEvents.exam2']}" />
							<m:br id="EditCaseEventBrId22" />
							<h:selectOneMenu id="editExam2" disabled="#{logCaseDataBean.disableScreenField}" value="#{logCaseDataBean.caseEventsVO.examCode2}">
								<f:selectItems value="#{logCaseDataBean.examTest12}" id="editCaseEventSelItm9Id"/>
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td id="EditCaseEventTdId16">
						<m:div id="EditCaseEventDivId22" styleClass="padb">
							<h:outputText id="EditCaseEventOutTextId23467" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="editCaseEventsLabel15" for="editprov" value="#{msg['label.caseEvents.providerhos']}" />
							<m:br id="EditCaseEventBrId23" />
							<h:selectOneMenu id="editprov" disabled="#{logCaseDataBean.disableScreenField}" value="#{logCaseDataBean.caseEventsVO.providerHospital}">
								<f:selectItem itemLabel="" itemValue=" "/>
								<f:selectItems value="#{logCaseDataBean.providerhos}" id="editCaseEventSelItm8Id"/>
							</h:selectOneMenu>
							<m:br  id="brId23467" />
							<h:message for="editprov" styleClass="errorMessage" id="editCaseEventMsg23467Id"/>
						</m:div>
					</m:td>
				</m:tr>
				</m:table>
				</h:panelGroup>
			 <hx:ajaxRefreshSubmit id="logCaseEditCaseEventSubmitId5" target="showDDUAppointmentScreenEditTarget"/>
		</m:div>
<%--CR_ESPRD00373565_LogCase_04AUG2010--%>
<m:div id="editCaseEventAuditDiv" rendered="#{logCaseDataBean.enableAuditLogForLogCase}">
			<f:subview id="editCaseEventsVOAudit">
											<audit:auditTableSet id="caseEventsVOAuditId"
												value="#{logCaseDataBean.caseEventsVO.auditKeyList}"
												numOfRecPerPage="10">
											</audit:auditTableSet>
			</f:subview>
</m:div><%--EOF CR_ESPRD00373565_LogCase_04AUG2010 --%>

	</m:div>
</m:div>
