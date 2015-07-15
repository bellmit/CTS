<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>

<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543--%>
<m:div id="logcaseAddCaseStepsMainDivID" styleClass="moreInfo" rendered="#{logCaseDataBean.renderedCaseStepsflag}">
	<m:div id="logcaseAddCaseStepsSubDivID100"  styleClass="moreInfoBar">
	<h:inputHidden id="inputHidnDisableExpCmplDate"		value="#{logCaseControllerBean.disableExpectedCompletionDate}"></h:inputHidden>

		<m:div id="logcaseAddCaseStepsTitlDiv" styleClass="infoTitle">
			<h:outputText id="logcaseAddCaseStepsTitlOutTxt" value="#{msg['label.case.caseSteps.newCaseStep']}" />
		</m:div>
		<m:div id="logcaseAddCaseStepsSubDivID101" styleClass="infoActions">
			<t:commandLink styleClass="strong" id="saveCaseStepsId"				action="#{logCaseControllerBean.saveCaseSteps}" onmousedown="javascript:flagWarn=false;javascript:focusPage('addCaseStepFocusPage');" onclick="javascript:flagWarn=false;">
				<h:outputText id="saveCaseStepsOutputTextID" value="#{msg['label.case.save']}" />
			</t:commandLink>
			<h:outputText id="cancelCaseStepsPageOutputTextSpace" escape="false" value="&nbsp;"/>
			<h:outputText id="CMGTOT18" value="|"/>
			<h:outputText id="CMGTOT19" escape="false" value="&nbsp;"/>
		<h:panelGroup id="addStepsCreateLtrLinkPnlgrpID">
			<t:commandLink id="enableDisableCreateLtrLinkID" onmousedown="javascript:flagWarn=false;" onclick="javascript:flagWarn=false;" action="#{logCaseControllerBean.createLetterForStep}" rendered="#{((!LettersAndResponsesDataBean.updateLtrAndRespFlag))&&(logCaseDataBean.caseStepsCreateLetterStatus)}">
				  <h:outputText id="enableDisableCreateLtrLinkOutputTxt" value="#{msg['label.case.createLetter']}"/>
				   <f:param id="addCaseStepsCreateLetterParam1" name="send.CommonLetterInputData.Action" value="SendCommonLetterInputAction"></f:param>
                  <f:param id="addCaseStepsCreateLetterParam2" name="funcSK"   value="#{LettersAndResponsesDataBean.funcSK}"></f:param>
                  <f:param id="addCaseStepsCreateLetterParam3" name="funcArea" value="#{LettersAndResponsesDataBean.funcArea}"></f:param>
                  <f:param id="addCaseStepsCreateLetterParam4" name="LETTER_CATEGORY"     value="#{LettersAndResponsesDataBean.letterCategory}"></f:param>
			</t:commandLink>				  
			<h:outputText id="enableDisableCreateLtrLinkOutputTxt2" value="#{msg['label.case.createLetter']}" rendered="#{((LettersAndResponsesDataBean.updateLtrAndRespFlag))|| !(logCaseDataBean.caseStepsCreateLetterStatus)}" />

		</h:panelGroup>
		<hx:ajaxRefreshSubmit id="addStepsCreateLtrLinkPnlgrpIDRefresh" target="addStepsCreateLtrLinkPnlgrpID">
								</hx:ajaxRefreshSubmit>
			<h:outputText id="saveCaseStepsOutputTextSpace" escape="false" value="&nbsp;"/>
			<h:outputText id="CMGTOT20" value="|"/>
			<h:outputText id="CMGTOT21" escape="false" value="&nbsp;"/>
			<t:commandLink id="resetCaseStepsPageId" action="#{logCaseControllerBean.resetCaseStepsPage}" onmousedown="javascript:focusPage('addCaseStepFocusPage');javascript:flagWarn=false;" onclick="javascript:flagWarn=false;">
				<h:outputText id="resetCaseStepsPageOutputTextID" value="#{msg['label.case.reset']}" />
			</t:commandLink>
			<h:outputText id="resetCaseStepsPageOutputTextSpace" escape="false" value="&nbsp;"/><h:outputText value="|"/><h:outputText escape="false" value="&nbsp;"/>
			<t:commandLink id="cancelCaseStepsPageId" action="#{logCaseControllerBean.cancelCaseStepsPage}" onmousedown="javascript:focusPage('caseStepsHeader');">
				<h:outputText id="cancelCaseStepsPageOutputTextID" value="#{msg['label.case.cancel']}" />
			</t:commandLink>
		</m:div>
	</m:div>
	<m:div id="logcaseAddCaseStepsSubDivID200" styleClass="moreInfoContent">
<%--Modified for defect ESPRD00446264 starts--%>
<%--<m:div id="logcaseAddCaseStepsSubDivID201"
		rendered="#{logCaseDataBean.showCaseStepsMessages}"
		styleClass="msgBox">
		<h:messages id="addStepErrorMessage" layout="table" showDetail="true" styleClass="successMsg"			 showSummary="false">
		</h:messages>
	</m:div>--%>
	<%--Added for defect ESPRD00780571, this is useful for the user at the secion level error msg display --%>
	<m:div id="LCASEESTEPSADDERRMESG" styleClass="width:100%">
			<h:inputHidden id="ADDSTEPSERRMSGDIFFROUTE" value=" "></h:inputHidden>
			<h:message id="PROVIDERM20120731164811287" for="ADDSTEPSERRMSGDIFFROUTE" styleClass="errorMessage"></h:message>
	</m:div>
	<%--Defect ESPRD00446264 Fix ends--%>
		<m:div id="logcaseAddCaseStepsSubDivID202">

			<m:table id="addCaseStepsTable100" cellspacing="0" width="95%">
				<m:tr id="addCaseStepsTableTr010" >
					<m:td id="addCaseStepsTableTD001" >
						<m:div id="logcaseAddCaseStepsTdDivId001" styleClass="padb">
							<h:outputText id="logcaseAddCaseStepsReqField001" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="addCasestepsOrderLblId" for="order"								value="#{msg['label.case.caseSteps.order']}" />
							<m:br id="addCaseStepsBR00" />
							<h:inputText id="order"								value="#{logCaseDataBean.caseStepsVO.order}" />
							<m:br id="addCaseStepsBR01"/>
							<h:message id="addCasestepsOrderMsgID" for="order" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="addCaseStepsTableTD002">
						<m:div id="logcaseAddCaseStepsTdDivId002" styleClass="padb">
							<h:outputText id="logcaseAddCaseStepsReqField002" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="addCaseStepsDescriptionLablID" for="description"								value="#{msg['label.case.caseSteps.description']}" />
							<m:br id="addCaseStepsBR02"/>
						
							<h:selectOneMenu id="description"								value="#{logCaseDataBean.caseStepsVO.description}">
								<f:selectItems id="addStepsDescriptionSelectItms"  value="#{logCaseDataBean.caseStepCodeList}"/>
							</h:selectOneMenu>
							<m:br id="addCaseStepsBR03"/>
							<h:message id="addCaseStepsDescriptionMsgId" for="description" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="addCaseStepsTableTD003">
						<m:div id="logcaseAddCaseStepsTdDivId003" styleClass="padb">
							<h:outputLabel id="addCaseStepsRoutToLablID" for="routeTo"								value="#{msg['label.case.caseSteps.routeTo']}" />
							<m:br id="addCaseStepsBR04" />
							<h:selectOneMenu id="routeTo"								value="#{logCaseDataBean.caseStepsVO.routeTo}" >
						<%--	<f:selectItems id="addStepsRouteToSetctItems" value="#{logCaseDataBean.userList}" />---%>
								<f:selectItems id="addStepsRouteToSetctItems" value="#{logCaseDataBean.routeAllDeptUsersList}" />
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td id="addCaseStepsTableTD004" colspan="2">
						<m:div id="logcaseAddCaseStepsTdDivId004" styleClass="padb">
							<h:outputText id="logcaseAddCaseStepsReqField003" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="addCaseStepsStatusLablID" for="LOGCASESTEPSSTATUS0"								value="#{msg['label.case.caseSteps.status']}" />
							<m:br id="addCaseStepsBR05" />
							<h:selectOneMenu id="LOGCASESTEPSSTATUS0"	immediate="true"		value="#{logCaseDataBean.caseStepsVO.status}"								valueChangeListener="#{logCaseControllerBean.showCaseStepStatusDetails}"								onchange="javascript:flagWarn=false;focusThis(this.id);focusPage('addCaseStepFocusPage');">
								<f:selectItems id="addStepsDesc1SetctItems" value="#{logCaseDataBean.stepStatusList}"/>
							</h:selectOneMenu>
							<hx:behavior id="newCaseStepStauts" event="onchange" behaviorAction="get;get"  target="LOGCASESTEPSSTATUS0" targetAction="caseStepsIDofDateStarted;addStepsCompletionDateStrId"></hx:behavior>
							<m:br id="addCaseStepsBR06"/>
							<h:message id="addCaseStepsStatusMsgID" for="LOGCASESTEPSSTATUS0" showDetail="true" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="addCaseStepsTableTD005">
						<m:div id="logcaseAddCaseStepsTdDivId005" styleClass="padb">
						</m:div>
					</m:td>
				</m:tr>
				<m:tr id="addCaseStepsTableTr020">
					<m:td id="addCaseStepsTableTD021">
						<m:div id="logcaseAddCaseStepsTdDivId006" styleClass="padb">
							<h:outputText id="logcaseAddCaseStepsField001" value="#{msg['label.case.caseSteps.dateStarted']}"								styleClass="outputLabel" />
							<m:br id="addCaseStepsBR07" />
						  <h:panelGroup id="caseStepsIDofDateStarted" >
							<h:outputText id="AddCaseStepsVODateStartedStrID" value="#{logCaseDataBean.caseStepsVO.dateStartedStr}"/>
						</h:panelGroup>
						<hx:ajaxRefreshSubmit id="caseStepsIDofDateStartedRefresh" target="caseStepsIDofDateStarted">
						</hx:ajaxRefreshSubmit>
						</m:div>
					</m:td>
					<m:td id="addCaseStepsTableTD022">
						<m:div id="logcaseAddCaseStepsTdDivId007" styleClass="padb">
							<h:outputLabel id="addStepsExpDaysToCmplLablId" for="expectedDaysToComplete"								value="#{msg['label.case.caseSteps.expectedDaysToComplete']}" />
							<m:br id="addCaseStepsBR08" />
							<h:inputText id="expectedDaysToComplete" size="4"								value="#{logCaseDataBean.caseStepsVO.expectedDaysToComplete}" onblur="javascript:flagWarn=false;focusThis('completionBasedOn');focusPage('addCaseStepFocusPage');enableDisableComplAdd();"/>
							<m:br id="addCaseStepsBR09"/>
							<h:message id="addStepsExpDaysToCmplMsgId" for="expectedDaysToComplete" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="addCaseStepsTableTD023">
						<m:div id="logcaseAddCaseStepsTdDivId008" styleClass="padb">
							<h:outputLabel id="addStepsCmpltBasedOnLablId" for="completionBasedOn"								value="#{msg['label.case.caseSteps.completionBasedOn']}"/>
							<m:br id="addCaseStepsBR10" />
							<h:selectOneMenu id="completionBasedOn"								value="#{logCaseDataBean.caseStepsVO.completedBasedOn}" onchange="javascript:flagWarn=false;focusThis(this.id);focusPage('addCaseStepFocusPage');this.form.submit();"								valueChangeListener="#{logCaseControllerBean.expectedCompletionDateForAdd}" disabled="#{logCaseDataBean.disableCompletionBasedOn}">
								<f:selectItems id="addStepsCmplBasdOnSetctItems" value="#{logCaseDataBean.stepCompBasedOn}"/>
							</h:selectOneMenu>
							<m:br id="addCaseStepsBR11" />
							<h:message id="addStepsCmpltBasedOnMsglId" for="completionBasedOn" styleClass="errorMessage" />
						</m:div>
					</m:td>
					<m:td id="addCaseStepsTableTD024">
						<m:div id="logcaseAddCaseStepsTdDivId009" styleClass="padb">
							<h:outputLabel id="addStepsExpCmplDateLablID" for="expectedCompAdd"								value="#{msg['label.case.caseSteps.expectedCompletionDate']}" />
							<m:br id="addCaseStepsBR12" />
							<m:inputCalendar id="expectedCompAdd" size="11"
								monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false"
								popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
								value="#{logCaseDataBean.caseStepsVO.expectedCompletionDateStr}"
								disabled="#{logCaseDataBean.disableExpectedCompletionDateFields}" />
							<m:br id="addCaseStepsBR13"/>
							<h:message id="addStepsExpCmplDateMsgID" for="expectedCompAdd" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="addCaseStepsTableTD025">
						<m:div id="logcaseAddCaseStepsTdDivId010" styleClass="padb">
							<h:outputText id="logcaseAddCaseStepsTdDivId010outputTxt"								value="#{msg['label.case.caseSteps.completionDate']}"								styleClass="outputLabel" />
							<m:br id="addCaseStepsBR14" />
							<h:panelGroup id="addStepsCompletionDateStrId">
							<h:outputText id="addCaseStepsVOCompletionDateStr"								value="#{logCaseDataBean.caseStepsVO.completionDateStr}"/>
							</h:panelGroup>
							<hx:ajaxRefreshSubmit id="addStepsCompletionDateStrRefresh" target="addStepsCompletionDateStrId">
							</hx:ajaxRefreshSubmit>
						</m:div>
					</m:td>
				</m:tr>
				<m:tr  id="addCaseStepsTableTr030">
				
				    <m:td id="addCaseStepsTableTD031">
						<m:div id="logcaseAddCaseStepsTdDivId011" styleClass="padb">
							<h:outputLabel id="addStepsnotifyViaAlertsLablId" for="LOGCASESTEPSALERT0"								value="#{msg['label.case.caseSteps.notifyViaAlert']}" />
							<m:br id="addCaseStepsBR15" />

							<h:selectOneMenu id="LOGCASESTEPSALERT0"		immediate="true"	value="#{logCaseDataBean.caseStepsVO.notifyViaAlert}" onchange="javascript:flagWarn=false;focusThis('alertBasedOn');focusPage('addCaseStepFocusPage');enableDisableAlertBasedOnAndAlertDays('Add');"								 valueChangeListener="#{logCaseControllerBean.disableFieldsnotifyViaAlert}" >
								<f:selectItems id="addStepsNotifyViaAlertsSetctItems"  value="#{logCaseDataBean.userList}" />
							</h:selectOneMenu>
						<hx:behavior id="addCaseStepExpDaysToCmplOnChnge" event="onchange" behaviorAction="get"  target="LOGCASESTEPSALERT0" targetAction="addStepsAlrtBasdOnPGrpId"></hx:behavior>
						</m:div>
					</m:td>
					<m:td  id="tdId233" colspan="2">
					<h:panelGroup id="addStepsAlrtBasdOnPGrpId">
					<h:panelGrid columns="2" cellspacing="0" id="logCaseaddStepPNGD02">
						<m:div id="logcaseAddCaseStepsTdDivId012" styleClass="padb">
							<h:outputLabel id="addStepsalertBasedOnLablId" for="alertBasedOn"								value="#{msg['label.case.caseSteps.alertBasedOn']}" />
							<m:br id="addCaseStepsBR16" />
							<h:selectOneMenu id="alertBasedOn" disabled="#{logCaseDataBean.disableFields}"								value="#{logCaseDataBean.caseStepsVO.alertBasedOnStr}">
								<f:selectItems id="addStepsAlertBasedOnSetctItems"  value="#{logCaseDataBean.stepAlertBasedOn}"/>
							</h:selectOneMenu>
						</m:div>
						<m:div id="logcaseAddCaseStepsTdDivId013" styleClass="padb">
							<h:outputLabel id="addStepssendAlertDaysLablID"								value="#{msg['label.case.caseSteps.sendAlertDays']}" for="sendAlertDays"/>
							<m:br id="addCaseStepsBR17" />
							<m:table id="addCaseStepsTable200">
								<m:tr id="addCaseStepsTableTr220">
									<m:td id="addCaseStepsTableTD221">
										<h:selectOneMenu id="sendAlertDays" disabled="#{logCaseDataBean.disableFields}" value="#{logCaseDataBean.caseStepsVO.sendAlertDaysStr}">
											<f:selectItem itemValue=" " itemLabel="" id="iSelectItem"/>
											<f:selectItems  id="addStepsSendAlertDaysSetctItems" value="#{logCaseDataBean.stepAlertDays}"/>
										</h:selectOneMenu>

									</m:td>

									<m:td id="addCaseStepsTableTD222">
										<h:selectOneRadio id="addCaseStepsVOBeforeOrAfterInd"		disabled="#{logCaseDataBean.disableFields}"		value="#{logCaseDataBean.caseStepsVO.beforeOrAfterInd}">
											<f:selectItem id="addStepsBeforeIndic" itemValue="B" itemLabel="#{msg['label.caseSteps.before']}" />
											<f:selectItem id="addStepsAfterIndic" itemValue="A" itemLabel="#{msg['label.caseSteps.after']}" />
										</h:selectOneRadio>
										<h:message for="addCaseStepsVOBeforeOrAfterInd" styleClass="errorMessage" id="addCaseStepsMsg2Id"/>
									</m:td>
								</m:tr>
							</m:table>
						</m:div>
					</h:panelGrid>
					<hx:ajaxRefreshSubmit id="addStepsAlrtBasdOnPGrpIdRefresh" target="addStepsAlrtBasdOnPGrpId"></hx:ajaxRefreshSubmit>
					</h:panelGroup>
					</m:td>
					<m:td id="addCaseStepsTableTD034">
						<m:div id="logcaseAddCaseStepsTdDivId014" styleClass="padb">
							<h:outputLabel id="addStepstemplateIDLablId" value="#{msg['label.case.caseSteps.template']}" for="templateID" />
							<m:br id="addCaseStepsBR18" />

							<h:selectOneMenu id="templateID" onchange="javascript:flagWarn=false;focusThis(this.id);"		valueChangeListener="#{logCaseControllerBean.enableDisableCreateLetterLinkForCaseSteps}"								value="#{logCaseDataBean.caseStepsVO.template}" immediate="true">
								<f:selectItems id="addStepsTemplateIDSetctItems" value="#{logCaseDataBean.templateList}"/>
							</h:selectOneMenu>
 							<hx:behavior id="addCaseStepTempletOnchng" event="onchange" behaviorAction="get"  target="templateID" targetAction="addStepsCreateLtrLinkPnlgrpID"></hx:behavior>
						</m:div>
					</m:td>
					<m:td id="addCaseStepsTableTD035">
					</m:td>
				</m:tr>
			</m:table>
		</m:div>
	</m:div>
</m:div>
