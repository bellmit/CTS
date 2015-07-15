<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>

<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543--%>
<m:div id="divId1" styleClass="moreInfo" rendered="#{logCaseDataBean.renderedCaseEventsflag}">
	<m:div id="divId2" styleClass="moreInfoBar">
		<m:div id="divId3" styleClass="infoTitle">
			<h:outputText id="OutText1" value="#{msg['label.case.caseEvents.newCaseEvent']}" />
		</m:div>
		<m:div id="divId4" styleClass="infoActions">
			<t:commandLink id="saveCaseEventsId" action="#{logCaseControllerBean.saveCaseEvents}" onclick="javascript:flagWarn=false;">
				<h:outputText id="OutText7" value="#{msg['label.case.save']}" styleClass="strong"/>
			</t:commandLink>
			<h:outputText id="OutText8" escape="false" value="&nbsp;"/>
			<h:outputText id="OutText9" value="|"/>
			<h:outputText id="OutText10" escape="false" value="&nbsp;"/>
			<h:panelGroup id="templateTarget">
			<t:commandLink  id="linkId1" onmousedown="javascript:flagWarn=false;" action="#{logCaseControllerBean.createLetterForEvent}" rendered="#{((!LettersAndResponsesDataBean.updateLtrAndRespFlag))&&(logCaseDataBean.caseEventsCreateLetterStatus)}">
				<h:outputText id="OutText2" value="#{msg['label.case.createLetter']}"/>
				  <f:param id="addCaseEventCreateLetterParam1" name="send.CommonLetterInputData.Action" value="SendCommonLetterInputAction"></f:param>
                  <f:param id="addCaseEventCreateLetterParam2" name="funcSK"   value="#{LettersAndResponsesDataBean.funcSK}"></f:param>
                  <f:param id="addCaseEventCreateLetterParam3" name="funcArea" value="#{LettersAndResponsesDataBean.funcArea}"></f:param>
                  <f:param id="addCaseEventCreateLetterParam4" name="LETTER_CATEGORY"     value="#{LettersAndResponsesDataBean.letterCategory}"></f:param>
			</t:commandLink>
<%-- EOF defect ESPRD00340027--%><%-- modified for defect id: ESPRD00340194--%>
			<h:outputText id="OutText3" value="#{msg['label.case.createLetter']}" rendered="#{((LettersAndResponsesDataBean.updateLtrAndRespFlag))|| !(logCaseDataBean.caseEventsCreateLetterStatus)}" />

			</h:panelGroup>
			<hx:ajaxRefreshSubmit id="ajaxRefreshSubmitId1" target="templateTarget"/>
<%--EOF defect ID ESPRD00340194 --%>
			<h:outputText id="OutText4" escape="false" value="&nbsp;"/><h:outputText id="OutText5" value="|"/><h:outputText id="OutText6" escape="false" value="&nbsp;"/>
			<t:commandLink id="resetCaseEventsPageId" action="#{logCaseControllerBean.resetCaseEventsPage}" onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);focusPage('addCaseEventPageFocus');">
				<h:outputText id="OutText11" value="#{msg['label.case.reset']}"/>
			</t:commandLink>
			<h:outputText id="OutText12" escape="false" value="&nbsp;"/><h:outputText id="OutText13" value="|"/><h:outputText id="OutText14" escape="false" value="&nbsp;"/>
			<t:commandLink id="cancelCaseEventsId" action="#{logCaseControllerBean.cancelCaseEvents}" onmousedown="javascript:focusThis('addCaseEventId');focusPage('caseEventsHeader');">
				<h:outputText id="OutText15" value="#{msg['label.case.cancel']}" />
			</t:commandLink>
		</m:div>
	</m:div>
	<m:div id="divId5" styleClass="moreInfoContent">
<%--Modified for defect ESPRD00446264 starts--%>
<%--<m:div id="divId6"
		rendered="#{logCaseDataBean.showCaseEventsMessages}"
		styleClass="msgBox">
		<h:messages layout="table" showDetail="true" styleClass="successMsg"			id="addEventsErrorMessage" showSummary="false">
		</h:messages>
	</m:div>--%>
	
	<m:div id="LCSEEEVENTSGNRICMSG0" styleClass="width:100%">
		<h:inputHidden id="EVENTSGNRICMSGHDN0" value=" "></h:inputHidden>
		<h:message id="PROVIDERM20120731164811285" for="EVENTSGNRICMSGHDN0" styleClass="errorMessage"></h:message>
	</m:div>
    <%--Defect ESPRD00446264 Fix ends--%>
		<m:div id="divId7" styleClass="width:100%">
			<m:table  id="tableId1" cellspacing="0" width="98%">
				<m:tr  id="trId1">
					<m:td  id="tdId1">
						<m:div id="divId8" styleClass="padb">
							<h:outputText id="OutText16" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="addCaseEventsLabel1"								value="#{msg['label.case.caseEvents.createdDate']}" for="createdDate"/>
							<m:br  id="brId1" />

<h:outputText  value="#{logCaseDataBean.caseEventsVO.createDateStr}" id="createdDate"/> 

							<h:message for="createdDate" styleClass="errorMessage" id="addCaseEventMsg4Id"/>
						</m:div>
					</m:td>
					<m:td  id="tdId2">
						<m:div id="divId9" styleClass="padb"> 
							<h:outputText id="OutText17" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="addCaseEventsLabel2" for="caseEventsType"								value="#{msg['label.case.caseEvents.type']}" />
							<m:br  id="brId2" />
							<h:selectOneMenu id="caseEventsType" immediate="true"
								value="#{logCaseDataBean.caseEventsVO.eventTypeCd}"
								valueChangeListener="#{logCaseControllerBean.showDDUAppoint}">
								<%--this.form.submit() --%>
								<f:selectItems value="#{logCaseDataBean.eventTypeList}" />
							</h:selectOneMenu>
							<hx:behavior id="typeAdd" event="onchange" behaviorAction="get"
								target="caseEventsType"
								targetAction="showDDUAppointmentScreenTarget"></hx:behavior>
							<m:br  id="brId3" />
							<h:message for="caseEventsType" styleClass="errorMessage" id="addCaseEventMsg5Id"/>
						</m:div>
					</m:td>
					<m:td  id="tdId3">
						<m:div id="divId10" styleClass="padb">
							<h:outputText id="OutText18" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="addCaseEventsLabel3" for="eventDescription"							value="#{msg['label.case.caseEvents.description']}" />
						<m:br  id="brId4" />
							<h:inputText id="eventDescription" size="30" value="#{logCaseDataBean.caseEventsVO.desc}" style="WIDTH:190px"/>
							<m:br  id="brId5"/>
							<h:message for="eventDescription" styleClass="errorMessage" id="addCaseEventMsg6Id"/>
						</m:div>
					</m:td>
					<m:td  id="tdId4">
						<m:div id="divId11" styleClass="padb">
							<h:outputText id="OutText19" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="addCaseEventsLabel4" value="#{msg['label.case.caseEvents.eventDate']}" for="eventDate"/>
							<m:br  id="brId6" />
							<!--Begin - Added the attribute 'onfocus' and 'onblur' for the defect id ESPRD00678064 -->
							<m:inputCalendar id="eventDate" size="10"
								monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false"
								popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
								value="#{logCaseDataBean.caseEventsVO.eventDateStr}"/>
							<%--End - Added the attribute 'onfocus' and 'onblur' for the defect id ESPRD00678064
								valueChangeListener="#{logCaseControllerBean.disableCaseEventDate}" //for ESPRD00748743
								this.form.submit()
								 --%>
							<m:br  id="brId7" />
							<h:message for="eventDate" styleClass="errorMessage" id="addCaseEventMsg8Id"/>
<!-- Added for ESPRD00357689 -->
<m:br  id="brId8" />
<%--Modified for defect ESPRD00674035--%>
<m:div id="divId12" rendered="#{logCaseDataBean.invalidEventDate}" >
<h:outputText id="OutText20" value="#{msg['error.logcase.caseevents.invalid.dateformate']}" rendered="#{logCaseDataBean.invalidEventDate}" styleClass="errorMessage"/><m:br  id="brId9" />
<h:outputText id="OutText21" value="#{msg['error.logcase.caseevents.invalid.dateformate1']}" rendered="#{logCaseDataBean.invalidEventDate}" styleClass="errorMessage"/><m:br  id="brId10" />
<h:outputText id="OutText22" value="#{msg['error.logcase.caseevents.invalid.dateformate2']}" rendered="#{logCaseDataBean.invalidEventDate}"  styleClass="errorMessage"/><m:br  id="brId11" />
<h:outputText id="OutText23" value="#{msg['error.logcase.caseevents.invalid.dateformate3']}" rendered="#{logCaseDataBean.invalidEventDate}"  styleClass="errorMessage"/>	
</m:div>	
<!-- EOF ESPRD00357689 -->
				</m:div>
					</m:td>
					<m:td  id="tdId5" colspan="2">
						<h:outputLabel id="addCaseEventsLabel5" for="time_1"							value="#{msg['label.case.caseEvents.time']}" />
						<m:br  id="brId12" />
						<h:panelGrid columns="2" width="105%" cellpadding="0"							cellspacing="0" id="caseEventPg1">
							<h:inputText id="time_1" size="4"
								value="#{logCaseDataBean.caseEventsVO.time}" />
							<h:selectOneRadio id="amPm" value="#{logCaseDataBean.caseEventsVO.amPM}">
								<f:selectItem itemValue="AM" itemLabel="#{msg['label.am']} " id="caseEventsSelectItm1"/>
								<f:selectItem itemValue="PM" itemLabel="#{msg['label.pm']} " id="caseEventsSelectItm2"/>
							</h:selectOneRadio>
							<h:message for="time_1" styleClass="errorMessage" id="addCaseEventMsg1Id"/>
							<h:message for="amPm" styleClass="errorMessage" id="addCaseEventAmPmId"/>
						</h:panelGrid>
					</m:td>
				</m:tr>
				<m:tr  id="trId2">
					<m:td  id="tdId6">
						<m:div id="divId13" styleClass="padb">
							<h:outputLabel id="addCaseEventsLabel6" for="notifyViaAlerts"								value="#{msg['label.case.caseEvents.notifyViaAlert']}" />
							<m:br  id="brId13"></m:br>
							<%-- Added for the defect id ESPRD00299552 --%>
							<h:selectOneMenu id="notifyViaAlerts" immediate="true"
								value="#{logCaseDataBean.caseEventsVO.notifyViaAlert}"
								valueChangeListener="#{logCaseControllerBean.disableCaseEventFieldsnotifyViaAlert}">
								<%--this.form.submit()--%>
								<f:selectItem itemValue=" " itemLabel="" />
								<f:selectItems value="#{logCaseDataBean.eventNotifyList}"
									id="caseEventsSelectItm3Id" />
								<hx:behavior id="notifyViaAlertsAdd" event="onchange"
									behaviorAction="get" target="notifyViaAlerts"
									targetAction="alertOrsendAlertDaysTarget"></hx:behavior>
							</h:selectOneMenu>

						</m:div>
					</m:td>
					<m:td  id="tdId7" colspan="2">	
						<h:panelGroup id="alertOrsendAlertDaysTarget">
						<h:panelGrid columns="2" cellspacing="0" id="logCaseaddPNGD02">
						<m:div id="divId14" styleClass="padb">
							<h:outputLabel id="addCaseEventsLabel7" for="alert_Based_On"								value="#{msg['label.case.caseEvents.alertBasedOn']}" />
							<m:br  id="brId14"></m:br>
									<h:selectOneMenu id="alert_Based_On"
										value="#{logCaseDataBean.caseEventsVO.alertBasedOn}"
										disabled="#{logCaseDataBean.disableCaseEventFields}">
										<f:selectItems value="#{logCaseDataBean.eventAlertBasedOn}"
											id="caseEventsSelectItm4Id" />
									</h:selectOneMenu>
								</m:div>
						<%--</m:td><m:td  id="tdId8">--%>
						<m:div id="divId15" styleClass="padb">
							<h:outputLabel id="addCaseEventsLabel8" for="sendAlertDays"								value="#{msg['label.case.caseEvents.alertDaysBefAfter']}" />
							<m:br  id="brId15" />
							<m:div id="divId16">
										<h:panelGrid columns="2" width="100%" cellpadding="0"
											cellspacing="0" id="caseEventPg2">
											<h:selectOneMenu id="sendAlertDays"
												value="#{logCaseDataBean.caseEventsVO.sendAlertDaysCd}"
												disabled="#{logCaseDataBean.disableCaseEventFields}">
												<f:selectItem itemValue=" " itemLabel="" id="iSelectItem" />
												<f:selectItems value="#{logCaseDataBean.eventAlertDaysList}"
													id="caseEventsSelectItm5" />
											</h:selectOneMenu>
											<m:div id="CaseEventsAftrB4DivId">
												<h:selectOneRadio disabled="#{logCaseDataBean.disableCaseEventFields}"
													value="#{logCaseDataBean.caseEventsVO.afterOrBeforeCd}"
													id="beforeAfterId">
													<f:selectItem itemValue="before"
														itemLabel="#{msg['label.caseSteps.before']}"
														id="caseEventsSelectItm6" />
													<f:selectItem itemValue="after"
														itemLabel="#{msg['label.caseSteps.after']}"
														id="caseEventsSelectItm7" />
												</h:selectOneRadio>
												<m:br id="CaseEventsAftB4Id"></m:br>
												<h:message for="beforeAfterId" styleClass="errorMessage"
													id="addCaseEventMsg2Id" />
											</m:div>
										</h:panelGrid>

									</m:div>
						</m:div>
					</h:panelGrid>
					<hx:ajaxRefreshSubmit id="ajaxRefreshSubmitId3" target="alertOrsendAlertDaysTarget"/>
					</h:panelGroup>
					
					</m:td>
					<m:td  id="tdId9">
						<m:div id="divId17" styleClass="padb">
						<h:outputText id="OutText24" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="addCaseEventsLabel9" for="LOGCASEEVENTSTATUS0"								value="#{msg['label.case.caseSteps.status']}" />
							<m:br  id="brId16"></m:br>
							<h:selectOneMenu id="LOGCASEEVENTSTATUS0" 
								value="#{logCaseDataBean.caseEventsVO.statusCd}"
								onchange="javascript:flagWarn=false;dispositionDateForEventAction();">
								<f:selectItems value="#{logCaseDataBean.eventStatus}"
									id="caseEventsSelectItm8" />
							</h:selectOneMenu>
							<%--<hx:behavior id="eventStatusAdd" event="onchange"
								behaviorAction="get" target="LOGCASEEVENTSTATUS0"
								targetAction="dispositionDateTarget"></hx:behavior>--%>
							<m:br  id="brId17" />
							<h:message for="LOGCASEEVENTSTATUS0" styleClass="errorMessage" id="addCaseEventMsg3Id"/>
						</m:div>
					</m:td>
					<m:td  id="tdId10">
						<m:div id="divId18" styleClass="padb">
							<h:outputLabel id="addCaseEventsLabel10"								value="#{msg['label.case.caseEvents.dispositionDate']}" for="dispositionDate"/>
							<m:br  id="brId18" />
							<h:panelGroup id="dispositionDateTarget">
							<m:div rendered="#{logCaseDataBean.caseEventsVO.dateDispositionFlag}">
								<h:inputText id="PROVIDERIT20120731164811286" value="" disabled="#{logCaseDataBean.caseEventsVO.dateDispositionFlag}"></h:inputText>
							</m:div>
							<m:div rendered="#{!logCaseDataBean.caseEventsVO.dateDispositionFlag}">
							<m:inputCalendar id="dispositionDate" size="10"
								monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false"
								popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
								value="#{logCaseDataBean.caseEventsVO.dispositionDateStr}" />
							</m:div>
							</h:panelGroup>
							<%--<hx:ajaxRefreshSubmit id="ajaxRefreshSubmitId4" target="dispositionDateTarget"/>--%>
							<m:br  id="brId19" />
							<h:message for="dispositionDate" styleClass="errorMessage" id="addCaseEventMsg4Id1"/>
						</m:div>
					</m:td>
					<m:td  id="tdId11">
						<m:div id="divId19" styleClass="padb">
							<h:outputLabel id="addCaseEventsLabel11" for="LOGCASETEMPLATE0" value="#{msg['label.case.caseEvents.template']}" />
							<m:br  id="brId20" />
							<h:selectOneMenu id="LOGCASETEMPLATE0"
								value="#{logCaseDataBean.caseEventsVO.template}"
								valueChangeListener="#{logCaseControllerBean.enableDisableCreateLetterLinkForCaseEvents}"
								immediate="true">
								<%--this.form.submit()--%>
								<f:selectItems value="#{logCaseDataBean.templateList}"
									id="caseEventsSelectItm9" />
							</h:selectOneMenu>
							<hx:behavior id="templateIDAdd" event="onchange"
								behaviorAction="get" target="LOGCASETEMPLATE0"
								targetAction="templateTarget"></hx:behavior>
						</m:div>
					</m:td>
				</m:tr>
				
			</m:table>
			
			<h:panelGroup id="showDDUAppointmentScreenTarget">
				<m:table  id="tableId2" cellspacing="0" width="98%">
				<m:tr  id="trId3" rendered="#{logCaseDataBean.showDDUAppointmentScreen}">
					<m:td  id="tdId12">
						<m:div id="divId20" styleClass="padb">
							<h:outputLabel id="addCaseEventsLabel12" for="adddiag1" value="#{msg['label.caseEvents.diagnosis1']}" />
							<m:br  id="brId21" />
							<h:selectOneMenu id="adddiag1" value="#{logCaseDataBean.caseEventsVO.diagnosisCode1}">
								<f:selectItems value="#{logCaseDataBean.diagnosisCode12}" id="caseEventsSelectItm10"/>
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td  id="tdId13">
						<m:div id="divId21" styleClass="padb">
							<h:outputLabel id="addCaseEventsLabel13" for="adddiag2" value="#{msg['label.caseEvents.diagnosis2']}" />
							<m:br  id="brId22" />
							<h:selectOneMenu id="adddiag2" value="#{logCaseDataBean.caseEventsVO.diagnosisCode2}">
								<f:selectItems value="#{logCaseDataBean.diagnosisCode12}" id="caseEventsSelectItm11"/>
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td  id="tdId14">
						<m:div id="divId22" styleClass="padb">
							<h:outputLabel id="addCaseEventsLabel14" for="addExam1" value="#{msg['label.caseEvents.exam1']}" />
							<m:br  id="brId23" />
							<h:selectOneMenu id="addExam1" value="#{logCaseDataBean.caseEventsVO.examCode1}">
								<f:selectItems value="#{logCaseDataBean.examTest12}" id="caseEventsSelectItm12"/>
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td  id="tdId15">
						<m:div id="divId23" styleClass="padb">
							<h:outputLabel id="addCaseEventsLabel15" for="addExam2" value="#{msg['label.caseEvents.exam2']}" />
							<m:br  id="brId24" />
								<m:div id="divId2343" styleClass="padb">
							<h:selectOneMenu id="addExam2" value="#{logCaseDataBean.caseEventsVO.examCode2}">
								<f:selectItems value="#{logCaseDataBean.examTest12}" id="caseEventsSelectItm13"/>
							</h:selectOneMenu></m:div>
						</m:div>
					</m:td>
					<m:td  id="tdId16">
						<m:div id="divId24" styleClass="padb">
						<h:outputText id="OutText2454" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="addCaseEventsLabel16" for="addprov" value="#{msg['label.caseEvents.providerhos']}" />
							<m:br  id="brId25" />
							<h:selectOneMenu id="addprov" value="#{logCaseDataBean.caseEventsVO.providerHospital}">
								<f:selectItem itemLabel="" itemValue=" "/>
								<f:selectItems value="#{logCaseDataBean.providerhos}" id="caseEventsSelectItm14"/>
							</h:selectOneMenu>
						<m:br  id="brId25645" />
							<h:message for="addprov" styleClass="errorMessage" id="addCaseEventMsg25645Id"/>
						</m:div>
					</m:td>
				</m:tr>
				</m:table>
				</h:panelGroup>
				<hx:ajaxRefreshSubmit id="ajaxRefreshSubmitId5"  target="showDDUAppointmentScreenTarget"/>
		</m:div>
	</m:div>
</m:div>
