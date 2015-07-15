<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<script type="text/javascript">
function caseStepDeleteConfirmation(){
	if(confirm('Are you sure you want to Delete?'))
	{
		return true;
	}else{
		flagWarn=false;
		return false;
		}
}
</script>

<portlet:defineObjects />

<m:div id="editStepsMainDiv" styleClass="moreInfo">

<h:inputHidden id="editStepsInputHidnDisblExpCmpDt"		value="#{logCaseControllerBean.disableExpectedCompletionDate}"></h:inputHidden>
	<m:div id="editStpesSubDiv001" styleClass="moreInfoBar">
		<m:div id="editStpesSubDiv011" styleClass="infoTitle">
			<h:outputText id="editStpsLablEditSteps" value="#{msg['label.case.caseSteps.editCaseStep']}" />
		</m:div>
		<m:div id="editStpesSubDiv012" styleClass="infoActions">
			<t:commandLink styleClass="strong" id="saveCaseStepsId2" onmousedown="javascript:flagWarn=false;" onclick="javascript:flagWarn=false;"	action="#{logCaseControllerBean.saveCaseSteps}" rendered="#{!logCaseDataBean.disableCaseStepSave && !(logCaseDataBean.disableScreenField)}">
				<h:outputText id="PRGCMGTOT629" value="#{msg['label.case.save']}" />
			</t:commandLink>
			<h:outputText id="edistStepsSaveTxtID" value="#{msg['label.case.save']}" rendered="#{logCaseDataBean.disableCaseStepSave || logCaseDataBean.disableScreenField}"/>
			<h:outputText id="editStpesEscapTxt3" escape="false" value="&nbsp;"/>
			<h:outputText value="|" id="editStpesEscapTxt56"/>
			<h:outputText escape="false" value="&nbsp;" id="editStpesEscapTxt57"/>
			<h:panelGroup id="editStepsCreateLtrLinkPnlgrpID">			
			<t:commandLink id="editStpesCreateLtrLink"  action="#{logCaseControllerBean.createLetterForStep}"  onmousedown="javascript:flagWarn=false;" onclick="javascript:flagWarn=false;" rendered="#{((!LettersAndResponsesDataBean.updateLtrAndRespFlag))&&(logCaseDataBean.caseStepsCreateLetterStatus)}">
			  <h:outputText id="editStpesCreateLtrLinkTxt1"  value="#{msg['label.case.createLetter']}"/>
			  <f:param id="editCaseStepCreateLetterParam1" name="send.CommonLetterInputData.Action" value="SendCommonLetterInputAction"></f:param>
              <f:param id="editCaseStepCreateLetterParam2" name="funcSK"   value="#{LettersAndResponsesDataBean.funcSK}"></f:param>
              <f:param id="editCaseStepCreateLetterParam3" name="funcArea" value="#{LettersAndResponsesDataBean.funcArea}"></f:param>
              <f:param id="editCaseStepCreateLetterParam4" name="LETTER_CATEGORY"     value="#{LettersAndResponsesDataBean.letterCategory}"></f:param>
              <f:param id="editCaseStepCreateLetterParam5" name="LogCaseStepSeqNum"     value="#{logCaseDataBean.caseStepsVO.caseStepSeqNumStr}"></f:param>	
			</t:commandLink>				
			<h:outputText id="editStpesCreateLtrLinkTxt2" value="#{msg['label.case.createLetter']}" rendered="#{((LettersAndResponsesDataBean.updateLtrAndRespFlag))|| !(logCaseDataBean.caseStepsCreateLetterStatus)}" />

			</h:panelGroup>
	        <hx:ajaxRefreshSubmit id="editStepsCreateLtrLinkPnlgrpIDRefresh" target="editStepsCreateLtrLinkPnlgrpID">
								</hx:ajaxRefreshSubmit>			
			<h:outputText id="editStpesEscapTxt1" escape="false" value="&nbsp;"/><h:outputText value="|" id="editStpesEscapTxt51"/><h:outputText escape="false" value="&nbsp;" id="editStpesEscapTxt52"/>
			<t:commandLink id="deleteCaseStepsId2" onmousedown="javascript:flagWarn=false;javascript:focusPage('caseStepsHeader');" onclick="javascript:flagWarn=false;return caseStepDeleteConfirmation();" action="#{logCaseControllerBean.deleteCaseSteps}" rendered="#{!logCaseDataBean.disableCaseStepDelete && !(logCaseDataBean.disableScreenField)}">
				<h:outputText id="edisStepsDeleteLinkTxt" value="#{msg['label.case.delete']}" />
			</t:commandLink>
			<h:outputText id="edisStepsDeleteOutTxt"  value="#{msg['label.case.delete']}" rendered="#{logCaseDataBean.disableCaseStepDelete || logCaseDataBean.disableScreenField}"/>
			<h:outputText id="editStpesEscapTxt4" escape="false" value="&nbsp;"/><h:outputText value="|" id="editStpesEscapTxt58"/><h:outputText escape="false" value="&nbsp;" id="editStpesEscapTxt59"/>
			<t:commandLink id="resetCaseStepsPageId2" onmousedown="javascript:flagWarn=false;javascript:focusPage('editCaseStepFocusPage');" onclick="javascript:flagWarn=false;" action="#{logCaseControllerBean.resetCaseStepsPage}" rendered="#{!logCaseDataBean.disableCaseStepReset && !(logCaseDataBean.disableScreenField)}">
				<h:outputText id="edisStepsResetLinkTxt" value="#{msg['label.case.reset']}" />
			</t:commandLink>
			<h:outputText id="edisStepsResetTxt" value="#{msg['label.case.reset']}" rendered="#{logCaseDataBean.disableCaseStepReset || logCaseDataBean.disableScreenField}"/>
			<h:outputText id="editStpesEscapTxt2" escape="false" value="&nbsp;" />
			<h:outputText value="|" id="editStpesEscapTxt54"/>
			<h:outputText escape="false" value="&nbsp;" id="editStpesEscapTxt55"/>
			<t:commandLink id="cancelCaseStepsPageId2" onmousedown="javascript:focusPage('caseStepsHeader');" action="#{logCaseControllerBean.cancelCaseStepsPage}">
				<h:outputText id="edisStepsCancelLinkTxt" value="#{msg['label.case.cancel']}" />
			</t:commandLink>
		</m:div>
	</m:div>
	<m:div id="editStpesSubDiv002" styleClass="moreInfoContent">
<%--Modified for defect ESPRD00446264 starts--%>
<%--<m:div id="editStpesSubDiv021"
		rendered="#{logCaseDataBean.showCaseStepsMessages}"
		styleClass="msgBox">
		<h:messages layout="table" showDetail="true" styleClass="successMsg"			id="editStepErrorMessage" showSummary="false">
		</h:messages>
	</m:div>--%>
	<%--Added for defect ESPRD00780571, this is useful for the user at the secion level error msg display --%>
	<m:div id="LCASEESTEPSEDITERRMESG" styleClass="width:100%">
			<h:inputHidden id="EDITSTEPSERRMSGDIFFROUTE" value=" "></h:inputHidden>
			<h:message id="PROVIDERM20120731164811300" for="EDITSTEPSERRMSGDIFFROUTE" styleClass="errorMessage"></h:message>
	</m:div>
	<%--Defect ESPRD00446264 Fix ends--%>
		<m:div id="editStpesSubDiv022">
			<m:table id="editStpesSubDiv022Tabl01" cellspacing="0" width="95%">
				<m:tr id="editStpesSubDiv022Tabl01tr01">
					<m:td id="editStpesSubDiv022Tabl01tr01td01">
						<m:div id="editStpesTabl01tr01Td01Div1" styleClass="padb">
							<h:outputText id="editstepsOrdReq" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="editstepsOrdLabl" for="order2"								value="#{msg['label.case.caseSteps.order']}" />
							<m:br id="editCaseStepsBR00"></m:br>
							<h:inputText id="order2" disabled="#{logCaseDataBean.caseStepVoided || logCaseDataBean.disableScreenField}"								value="#{logCaseDataBean.caseStepsVO.order}" />
							<m:br id="editCaseStepsBR01"/>
							<h:message id="editStepsErrForOrder" for="order2" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr01td02">
						<m:div id="editStpesTabl01tr01td02Div1" styleClass="padb">
							<h:outputText id="editStepsDescReq" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="editStepsDescTxt" for="description2"								value="#{msg['label.case.caseSteps.description']}" />
							<m:br id="editCaseStepsBR02"></m:br>
							<h:selectOneMenu id="description2" disabled="#{logCaseDataBean.caseStepVoided || logCaseDataBean.disableScreenField}"								value="#{logCaseDataBean.caseStepsVO.description}">
								<f:selectItems value="#{logCaseDataBean.caseStepCodeList}"/>
							</h:selectOneMenu>
							<m:br id="editCaseStepsBR03"/>
							<h:message id="editStepsErrForDescription" for="description2" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr01td03">
						<m:div id="editStpesTabl01tr01td03Div1" styleClass="padb">
							<h:outputLabel id="editStepsRouteToLabl" for="routeTo2"								value="#{msg['label.case.caseSteps.routeTo']}" />
							<m:br id="editCaseStepsBR04"></m:br>
							<h:selectOneMenu id="routeTo2"  disabled="#{logCaseDataBean.caseStepVoided || logCaseDataBean.disableScreenField}"								value="#{logCaseDataBean.caseStepsVO.routeTo}"  >
							<%--<f:selectItems id="editStepsRoutToSeltItm" value="#{logCaseDataBean.userList}"/>--%>
								<f:selectItems id="editStepsRoutToSeltItm" value="#{logCaseDataBean.routeAllDeptUsersList}"/>
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td colspan="2" id="editStpesSubDiv022Tabl01tr01td04">
						<m:div id="editStpesTabl01tr01td04Div" styleClass="padb">
							<h:outputText id="editStepsStautsReq" value="#{msg['label.case.astrisk']}" styleClass="required" />
							<h:outputLabel id="editStepsStautsLabl" for="LOGCASESTEPSSTATUSE1"								value="#{msg['label.case.caseSteps.status']}" />
							<m:br id="editCaseStepsBR05"></m:br>
							<h:selectOneMenu id="LOGCASESTEPSSTATUSE1" disabled="#{logCaseDataBean.caseStepVoided}"			immediate="true" 	value="#{logCaseDataBean.caseStepsVO.status}"								valueChangeListener="#{logCaseControllerBean.showCaseStepStatusDetails}"								onchange="javascript:flagWarn=false;focusThis(this.id);focusPage('editCaseStepFocusPage');">
								<f:selectItems id="editStepsStatusSItems" value="#{logCaseDataBean.stepStatusList}"/>
							</h:selectOneMenu>
							<hx:behavior id="editCaseStepStauts" event="onchange" behaviorAction="get;get"  target="LOGCASESTEPSSTATUSE1" targetAction="editCaseStepsIDofDateStarted;editStepsCompletionDateStrId"></hx:behavior>
							<m:br id="editCaseStepsBR06"/>
							<h:message id="editStepsErrForStauts" for="LOGCASESTEPSSTATUSE1" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr01td05"></m:td>
				</m:tr>
				<m:tr id="editStpesSubDiv022Tabl01tr02">
					<m:td id="editStpesSubDiv022Tabl01tr02td01">
						<m:div id="editStpesTabl01tr02td01Div01" styleClass="padb">
							<h:outputText id="editcaseStepsDateStrtdTxt" value="#{msg['label.case.caseSteps.dateStarted']}"								styleClass="outputLabel" />
							<m:br id="editCaseStepsBR07" />
							<h:panelGroup id="editCaseStepsIDofDateStarted" >
								<h:outputText id="editcaseStepsDateStrtd" value="#{logCaseDataBean.caseStepsVO.dateStartedStr}" />
							</h:panelGroup>
							<hx:ajaxRefreshSubmit id="editCaseStepsIDofDateStartedRefresh" target="editCaseStepsIDofDateStarted"></hx:ajaxRefreshSubmit>

						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr02td02">
						<m:div id="editStpesTabl01tr02td02Div01" styleClass="padb">
							<h:outputLabel id="editCaseStepsExpDaysToCmpl" for="expectedDaysToComplete2"								value="#{msg['label.case.caseSteps.expectedDaysToComplete']}"/>
							<m:br id="editCaseStepsBR08" />
							<h:inputText id="expectedDaysToComplete2" disabled="#{logCaseDataBean.caseStepVoided || logCaseDataBean.disableScreenField }"								value="#{logCaseDataBean.caseStepsVO.expectedDaysToComplete}" onchange="javascript:flagWarn=false;" onblur="javascript:javascript:focusThis('completionBasedOn2');focusPage('editCaseStepFocusPage');enableDisableComplEdit();"								size="4" />
							<m:br id="editCaseStepsBR09"/>
							<h:message id="editStepsErrForExpDaysToCmpl" for="expectedDaysToComplete2" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr02td03">
						<m:div id="editStpesTabl01tr02td03Div01" styleClass="padb">
							<h:outputLabel id="editStepsCmplBasdOn" for="completionBasedOn2"								value="#{msg['label.case.caseSteps.completionBasedOn']}">
							</h:outputLabel>
							<m:br id="editCaseStepsBR10"></m:br>
							<h:selectOneMenu id="completionBasedOn2"								value="#{logCaseDataBean.caseStepsVO.completedBasedOn}" onchange="javascript:flagWarn=false;focusThis(this.id);this.form.submit();"								valueChangeListener="#{logCaseControllerBean.expectedCompletionDateForEdit}" disabled="#{logCaseDataBean.disableCompletionBasedOn || logCaseDataBean.caseStepVoided || logCaseDataBean.disableScreenField}">
								<f:selectItems id="editStepsCmplBasdOnSItems" value="#{logCaseDataBean.stepCompBasedOn}"/>
							</h:selectOneMenu>
							<m:br id="editCaseStepsBR11"/>
							<h:message id="editStepsErrforCmplBasdOn" for="completionBasedOn2" styleClass="errorMessage"/><%--added for defect ESPRD00359439 --%>
						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr02td04">
						<m:div id="editStpesTabl01tr02td04Div1" styleClass="padb">
							<h:outputLabel id="editStpesExpCmplDate"								value="#{msg['label.case.caseSteps.expectedCompletionDate']}"								for="expectedCompEdit" />
							<m:br id="editCaseStepsBR12" />
							<m:inputCalendar id="expectedCompEdit" size="10"
								monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
								currentDayCellClass="currentDayCell" renderAsPopup="true"
								addResources="true" readonly="false"
								popupDateFormat="MM/dd/yyyy" renderPopupButtonAsImage="true"
								value="#{logCaseDataBean.caseStepsVO.expectedCompletionDateStr}" 
								disabled="#{logCaseDataBean.disableExpectedCompletionDateFields || logCaseDataBean.caseStepVoided || logCaseDataBean.disableScreenField}" />
							<m:br id="editCaseStepsBR13"/>
							<h:message id="editStepsErrForExpctedCmplDate" for="expectedCompEdit" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr02td05">
						<m:div id="editStpesTabl01tr02td05Div01" styleClass="padb">
							<h:outputText id="editStpesCmpleDateLabl"								value="#{msg['label.case.caseSteps.completionDate']}"								styleClass="outputLabel" />
							<m:br id="editCaseStepsBR14" />
						<h:panelGroup id="editStepsCompletionDateStrId">
							<h:outputText id="editStpesCmpleDate"								value="#{logCaseDataBean.caseStepsVO.completionDateStr}" />
						</h:panelGroup>
						<hx:ajaxRefreshSubmit id="editStepsCompletionDateStrRefresh" target="editStepsCompletionDateStrId"></hx:ajaxRefreshSubmit>
						</m:div>
					</m:td>
				</m:tr>
				<m:tr id="editStpesSubDiv022Tabl01tr03">
				    
					<m:td id="editStpesSubDiv022Tabl01tr03td01">
						<m:div id="editStpesTabl01tr03td01Div1" styleClass="padb">

						<m:table id="editStpesTabl01tr03td01Div1Table2">
							<m:tr id="editStpesTabl1tr3td1Tbl2Tr01">
	                            <m:td id="editStpesTabl1tr3td1Tbl2Tr01TD00">
                                <h:outputLabel id="editStepsNotifyAlrts" for="LOGCASESTEPSALERT1"				value="#{msg['label.case.caseSteps.notifyViaAlert']}" />
							<m:br id="editCaseStepsBR15"></m:br>
							<h:selectOneMenu id="LOGCASESTEPSALERT1" disabled="#{logCaseDataBean.notifyViaAlertFlag || logCaseDataBean.caseStepVoided}"			immediate="true"			onchange="javascript:flagWarn=false;focusThis('alertBasedOn2');focusPage('editCaseStepFocusPage');if(this.value==''){this.value=null};"								valueChangeListener="#{logCaseControllerBean.disableFieldsnotifyViaAlert}" 								value="#{logCaseDataBean.caseStepsVO.notifyViaAlert}" >
								<f:selectItems id="editStepsNotifyAlertsSlctItms" value="#{logCaseDataBean.userList}"/>
							</h:selectOneMenu>

								</m:td>
							</m:tr>
						</m:table>
							<hx:behavior id="editCaseStepExpDaysToCmplOnChnge"
								event="onchange" behaviorAction="get" target="LOGCASESTEPSALERT1"
								targetAction="editStepsAlrtBasdOnPGrpId"></hx:behavior>
						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr03td02" colspan="2">
						<h:panelGroup id="editStepsAlrtBasdOnPGrpId">
						<m:div id="editStpesTabl01tr03td02Div1" styleClass="padb">
						<%--	<h:outputLabel id="editStpesAlrtBasdOn" for="alertBasedOn2"								value="#{msg['label.case.caseSteps.alertBasedOn']}" />
							<m:br id="editCaseStepsBR16"></m:br>



							<h:selectOneMenu id="alertBasedOn2"  disabled="#{logCaseDataBean.disableFields || logCaseDataBean.caseStepVoided}"								value="#{logCaseDataBean.caseStepsVO.alertBasedOn}">
								<f:selectItems id="editStepsAlrtBasdOnSelctItms" value="#{logCaseDataBean.stepAlertBasedOn}"/>
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr03td03">
						<m:div id="editStpesTabl01tr03td03Div1" styleClass="padb">--%>
							
							<m:table id="editStpesTabl01tr03td03Div1Table1">
								<m:tr id="editStpesTabl1tr3td3Tbl1Tr01">
								<m:td id="editStpesTabl1tr3td3Tbl1Tr01TD00">
								<h:outputLabel id="editStpesAlrtBasdOn" for="alertBasedOn2"					value="#{msg['label.case.caseSteps.alertBasedOn']}" />
								<m:br id="editCaseStepsBR16"></m:br>



								<h:selectOneMenu id="alertBasedOn2"  disabled="#{logCaseDataBean.disableFields || logCaseDataBean.caseStepVoided || logCaseDataBean.disableScreenField}"								value="#{logCaseDataBean.caseStepsVO.alertBasedOnStr}">
									<f:selectItems id="editStepsAlrtBasdOnSelctItms" value="#{logCaseDataBean.stepAlertBasedOn}"/>
								</h:selectOneMenu>
								</m:td>
									<m:td id="editStpesTabl1tr3td3Tbl1Tr01Td1">
							<h:outputLabel id="editStepsSendAlrtsDaysLabl"								value="#{msg['label.case.caseSteps.sendAlertDays']}"								for="sendAlertDays2" />
							<m:br id="editCaseStepsBR17" />
										<h:selectOneMenu  id="sendAlertDays2" disabled="#{logCaseDataBean.disableFields || logCaseDataBean.caseStepVoided || logCaseDataBean.disableScreenField}"											value="#{logCaseDataBean.caseStepsVO.sendAlertDaysStr}">
											<f:selectItem itemValue=" " itemLabel="" id="iSelectItem"/>
											<f:selectItems id="editStepsSendAlertDaysSItems" value="#{logCaseDataBean.stepAlertDays}"/>
										</h:selectOneMenu>

									</m:td>
									<m:td id="editStpesTabl1tr3td3Tbl1Tr01Td2">
										<h:selectOneRadio id="editStepsBeforAftrIndic"  disabled="#{logCaseDataBean.disableFields || logCaseDataBean.caseStepVoided || logCaseDataBean.disableScreenField}"											value="#{logCaseDataBean.caseStepsVO.beforeOrAfterInd}">
											<f:selectItem id="editStepsBeforAftrIndicB" itemValue="B" itemLabel="#{msg['label.caseSteps.before']}" />
											<f:selectItem  id="editStepsBeforAftrIndicA" itemValue="A" itemLabel="#{msg['label.caseSteps.after']}" />
										</h:selectOneRadio>
										<h:message id="editStepsErrForBeforeAfter" for="editStepsBeforAftrIndic" styleClass="errorMessage"/>
									</m:td>
								</m:tr>
							</m:table>
						</m:div>
					</h:panelGroup>
					<hx:ajaxRefreshSubmit id="editStepsAlrtBasdOnPGrpIdRefresh" target="editStepsAlrtBasdOnPGrpId"></hx:ajaxRefreshSubmit>

					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr03td04">
						<m:div id="editStpesTabl01tr03td04Div1" styleClass="padb">
							<h:outputLabel id="editStepsTemplet" value="#{msg['label.case.caseSteps.template']}"								for="templateID2" />
							<m:br id="editCaseStepsBR18" />
							<h:selectOneMenu id="templateID2"  disabled="#{logCaseDataBean.caseStepVoided}" onchange="javascript:flagWarn=false;focusThis(this.id);"								 valueChangeListener="#{logCaseControllerBean.enableDisableCreateLetterLinkForCaseSteps}"								value="#{logCaseDataBean.caseStepsVO.template}" immediate="true">
								<f:selectItems id="editStepsTempletSItems" value="#{logCaseDataBean.templateList}"/>
							</h:selectOneMenu>
						 <hx:behavior id="editCaseStepTempletOnchng" event="onchange" behaviorAction="get"  target="templateID2" targetAction="editStepsCreateLtrLinkPnlgrpID"></hx:behavior>							
						</m:div>
					</m:td>
					<m:td id="editStpesSubDiv022Tabl01tr03td05">
					</m:td>
				</m:tr>
			</m:table>
		</m:div>
<%--CR_ESPRD00373565_LogCase_04AUG2010--%>
<m:div id="editCaseStepsAuditDiv" rendered="#{logCaseDataBean.enableAuditLogForLogCase}">
			<f:subview id="editCaseStepsVOAudit">
											<audit:auditTableSet id="caseStepsVOAuditId"
												value="#{logCaseDataBean.caseStepsVO.auditKeyList}"
												numOfRecPerPage="10">
											</audit:auditTableSet>
			</f:subview>
</m:div>
<%--EOF CR_ESPRD00373565_LogCase_04AUG2010 --%>
	</m:div>
</m:div>
