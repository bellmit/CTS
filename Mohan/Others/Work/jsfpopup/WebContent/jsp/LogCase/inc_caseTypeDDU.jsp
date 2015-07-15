<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:table width="100%" id="dduTab1Id">
	<m:tr id="dduTab1R1Id">
		<m:td id="dduTab1R1C1Id">
			<m:div styleClass="padb" id="dduTab1R1C1Div1Id">
				<h:outputLabel for="applicationTyp"  id="dduTab1R1C1Div1OutLbl1Id"					value="#{msg['label.case.caseDetails.applicationType']}" />
				<m:br id="dduTab1R1C1Div1OutLbl1Br1Id"/>
				<h:selectOneMenu id="applicationTyp"					value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.applicationTypeCd}"					disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItems value="#{logCaseDataBean.applicationTypeList}" />
				</h:selectOneMenu>
			</m:div>
		</m:td>
		<m:td id="dduTab1R1C2Id">
			<m:div styleClass="padb" onmousedown="javascript:flagWarn=false;" id="dduTab1R1C2Div1Id">
				<h:outputLabel for="Acal" id="dduTab1R1C2Div1OutLblId"					value="#{msg['label.case.caseDetails.applicationDate']}" />
				<m:br id="dduBr4Id"/>
				<m:inputCalendar id="Acal" size="10"
					disabled="#{logCaseDataBean.disableScreenField}"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.applicationDateStr}" />
			</m:div>
			<h:message for="Acal" styleClass="errorMessage" id="dduMsg1Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			<m:br id="dduBr1Id"></m:br>
		</m:td>
		<m:td id="dduTab1R1C3Id">
			<m:div styleClass="padb" onmousedown="javascript:flagWarn=false;" id="dduTab1R1C3Div1Id">
				<%--Added for the CR ESPRD00846696--%>
				<h:outputText value="#{msg['label.case.astrisk']}" id="dduOutTxt0"						styleClass="required" />
				<h:outputLabel id="PRGCMGTOLL306" for="subcomdat"					value="#{msg['label.case.caseDetails.substantiallyCompletedDate']}" />
				<m:br id="dduBr2Id"/>
				<m:inputCalendar id="subcomdat" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.substantiallyCompletedDateStr}"
					disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>
			<h:message for="subcomdat" styleClass="errorMessage" id="dduMsg2Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			<m:br id="dduBr25Id"></m:br>
		</m:td>
		<m:td id="dduTab1R1C4Id">
			<m:div styleClass="padb" id="dduTab1R1C4Div1Id">
				<h:outputLabel id="PRGCMGTOLL307" for="selonemen_7"					value="#{msg['label.case.caseDetails.authorizedRep']}" />
				<m:br id="dduBr3Id"/>
				<h:selectOneMenu id="selonemen_7"					value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.authorizedRepCd}"					disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemLabel=" " itemValue=" "/>
					<f:selectItems value="#{logCaseDataBean.authorisedRepList}" />
				</h:selectOneMenu>
			</m:div>
		</m:td>
		<m:td id="dduTab1R1C5Id">
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL308" for="pcktcal"					value="#{msg['label.case.caseDetails.packetReceivedDate']}" />
				<m:br id="dduBr5Id"/>
				<m:inputCalendar id="pcktcal" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.packetReceivedDateStr}"
					disabled="#{logCaseDataBean.disableScreenField}" />
			</m:div>
			<h:message for="pcktcal" styleClass="errorMessage" id="dduMsg3Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			<m:br id="dduBr44Id"></m:br>
		</m:td>
	</m:tr>
	<m:tr id="dduTab1R2Id">
		<m:td id="dduTab1R2C1Id">
			<h:outputText id="dduOutTxt1"				value="#{msg['label.case.caseDetails.unUsualCircumstances']}"				styleClass="outputLabel" />
		</m:td>
		
	</m:tr>
</m:table>
<m:table id="dduTab2Id">
	<m:tr id="dduTab2R1Id">
		<m:td id="dduTab2r1c1Id">
			<h:outputText value="#{msg['label.case.caseDetails.available']}" id="dduOutTxt2"				styleClass="outputLabel" />
		</m:td>
		<m:td id="dduTab1r1c2Id">&nbsp;&nbsp;&nbsp;</m:td>
		<m:td id="dduTab1r1c3Id">
			<h:outputText value="#{msg['label.case.caseDetails.selected']}" id="dduOutTxt3"				styleClass="outputLabel" />
		</m:td>
	</m:tr>
	<m:tr id="dduTab2R2Id">
		<m:td id="dduTab2R2C1Id">
			<h:outputLabel id="manyListBoxlabel1"  for="smlb" styleClass="hide" value="#{msg['label.case.caseDetails.available']}" ></h:outputLabel>
			<t:selectManyListbox id="smlb" size="4"				value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.unusualAvaiSelectedList}"				disabled="#{logCaseDataBean.disableScreenField}">
				<f:selectItems value="#{logCaseDataBean.unusualCircumstancesList}" />
			</t:selectManyListbox>
		</m:td>
		<m:td id="dduTab2R2C3Id">
			<m:table id="dduTab2R2C3Tab1Id">
				<m:tr id="dduTab2R2C3Tab1R1Id">
					<m:td align="center" id="dduTab1R2C3Tab1R1C1Id">
						<%-- Added the focusPage() in onclick for the defect id ESPRD00697332_07DEC2011--%>
						<h:commandButton styleClass="formBtn"							value="#{msg['button.label.moveright']}"							action="#{logCaseControllerBean.showSelectedItemInSelectManyBox}" onclick="javascript:flagWarn=false;focusThis(this.id);fucusPage('caseTypeDDUfocusPage');"							disabled="#{logCaseDataBean.disableScreenField}" id="moveRghtBtnId"/>
					</m:td>
				</m:tr>
				<m:tr id="dduTab2R2C3Tab1R2Id">
					<m:td align="center" id="dduTab1R2C3Tab1R2C1Id">
						<%-- Added the focusPage() in onclick for the defect id ESPRD00697332_07DEC2011--%>
						<h:commandButton styleClass="formBtn"							value="#{msg['button.label.moveleft']}"							action="#{logCaseControllerBean.removeSelectedItemsFromBox}" onclick="javascript:flagWarn=false;focusThis(this.id);fucusPage('caseTypeDDUfocusPage');"							disabled="#{logCaseDataBean.disableScreenField}" id="moveLftBtnId"/>
					</m:td>
				</m:tr>
			</m:table>
		</m:td>
		<m:td id="dduTab1R2C4Id">
			<h:outputLabel id="manyListBoxlabel2"  for="smlb_1" styleClass="hide" value="#{msg['label.case.caseDetails.selected']}" ></h:outputLabel>
			<t:selectManyListbox id="smlb_1" size="4"				value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.unusualSelectedList}"				disabled="#{logCaseDataBean.disableScreenField}">
				<f:selectItems value="#{logCaseDataBean.selectedUnUsualList}" />
			</t:selectManyListbox>
		</m:td>
	</m:tr>
</m:table>
<m:br />
<m:section styleClass="casebg" id="dduSec1Id">
	<m:legend id="leg1">
		<h:outputText styleClass="strong" id="dduOutTxt44Id"			value="#{msg['label.case.caseDetails.review']}" />
	</m:legend>
	<m:table width="100%" id="dduTab3Id">
		<m:tr id="dduTab3R1Id">
			<m:td id="dduTab3R1C1Id">
				<m:div styleClass="padb" onmousedown="javascript:flagWarn=false;" id="dduTab3R1C1Div1Id">
					<h:outputLabel for="reviewReqrd" id="dduLb1Id"						value="#{msg['label.case.caseDetails.reviewRequired']}" />
					<m:br id="dduBr6Id"></m:br>
					<%-- Added the focusPage() in onclick for the defect id ESPRD00697332_07DEC2011--%>
					<h:selectOneRadio id="reviewReqrd"						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.reviewRequiredInd}"						onclick="javascript:revShedateChangeAction();focusThis(this.id);focusPage('caseTypeDDUfocusPage');"						disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" />
						<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" />
					</h:selectOneRadio>
				</m:div>
			</m:td>
			<m:td id="dduTab3R1C2Id">
				<m:div styleClass="padb" onmousedown="javascript:flagWarn=false;" id="dduTab3R1C2Div1Id">
					<h:outputLabel for="reviewInDate" value="Review Initiated Date" id="dduLb2Id"/>
					<m:br id="dduBr7Id" />
					<m:inputCalendar id="reviewInDate" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true"
						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.reviewInitiatedDateStr}"
						disabled="#{logCaseDataBean.disableScreenField}" />
				</m:div>
				<h:message for="reviewInDate" styleClass="errorMessage"  id="dduMsg5Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
				<m:br id="dduBr8Id"></m:br>
			</m:td>
			<m:td id="dduTab3R1C4Id">
				<m:div styleClass="padb" rendered="#{logCaseDataBean.showReviewReq}" id="dduTab3R1C4Div1Id">
					<h:outputText value="#{msg['label.case.astrisk']}" id="dduOutTxt5"						styleClass="required" />
					<h:outputLabel for="scheduledDateOfReview" id="dduLb3Id"						value="Scheduled Date of Review" />
					<m:br id="dduBr9Id" />
					<m:inputCalendar id="scheduledDateOfReview" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true"
						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.schDateOfReviewStr}"
						disabled="#{logCaseDataBean.disableScreenField}" />
				</m:div>
				<h:message for="scheduledDateOfReview" styleClass="errorMessage" id="dduMsg7Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
				<m:br id="dduBr27Id"></m:br>
			</m:td>
			<m:td id="dduTab3R1C5Id">
				<m:div styleClass="padb" onmousedown="javascript:flagWarn=false;" id="dduTab3R1C5Div1Id">
					<%--CR ESPRD00791195 <h:outputText value="#{msg['label.case.astrisk']}" id="dduOutTxt6"						styleClass="required" />--%>
					<h:outputLabel for="receiveddt" id="dduLb4Id"						value="#{msg['label.case.caseDetails.receivedDate']}" />
					<m:br id="dduBr10Id" />
					<m:inputCalendar id="receiveddt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true"
						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.receivedDateStr}"
						disabled="#{logCaseDataBean.disableScreenField}" />
				</m:div>
				<h:message for="receiveddt" styleClass="errorMessage" id="dduMsg8Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
				<m:br id="dduBr28Id"></m:br>
			</m:td>
			<m:td id="dduTab3R1C6Id">
				<m:div styleClass="padb" onmousedown="javascript:flagWarn=false;" id="dduTab3R1C6Div1Id">
					<h:outputLabel for="RcptDt" id="dduLb5Id"						value="#{msg['label.case.caseDetails.receiptDate']}" />
					<m:br id="dduBr11Id" />
					<m:inputCalendar id="RcptDt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true"
						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.receiptDateStr}"
						disabled="#{logCaseDataBean.disableScreenField}" />
				</m:div>
				<h:message for="RcptDt" styleClass="errorMessage" id="dduMsg10Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
				<m:br id="dduBr29Id"></m:br>
			</m:td>
			<m:td id="dduTab3R1C7Id">
				<m:div styleClass="padb" id="dduTab3R1C7Div1Id">
					<h:outputLabel for="evaluationTyp" id="dduLb6Id"						value="#{msg['label.case.caseDetails.evaluationType']}" />
					<m:br id="dduBr12Id" />
					<h:selectOneMenu id="evaluationTyp"						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.evaluationTypeCd}"						disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.evaluationTypeList}" />
					</h:selectOneMenu>
				</m:div>
			</m:td>
		</m:tr>
		<m:tr id="dduTab3R2Id">
			<m:td id="dduTab3R2C1Id">
				<m:div styleClass="padb" id="dduTab3R2C1Div1Id">
					<h:outputLabel for="medDiagnosis" id="dduTab3R2C1Div1OutLbl1Id"						value="#{msg['label.case.caseDetails.medicalDiagnosis']}" />
					<m:br id="dduBr13Id" />
					<h:selectOneMenu id="medDiagnosis"						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.medicalDiagnosisCd}"						disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.medicalDiagnosisList}" />
					</h:selectOneMenu>
				</m:div>
			</m:td>
			<m:td id="dduTab3R2C2Id">
				<m:div styleClass="padb" id="dduTab3R2C2Div1Id">
					<h:outputLabel for="psychiatricDiag" id="dduLb7Id"						value="#{msg['label.case.caseDetails.psychiatricDiagnosis']}" />
					<m:br id="dduBr14Id" />
					<h:selectOneMenu id="psychiatricDiag"						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.psychiatricDiagnosisCd}"						disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.psychiatricDiagnosisList}" />
					</h:selectOneMenu>
				</m:div>
			</m:td>
			<m:td id="dduTab3R2C3Id">
				<m:div styleClass="padb" id="dduTab3R2C3Div1Id">
					<h:outputLabel for="responseIndicator" id="dduLb8Id"						value="#{msg['label.case.caseDetails.responseIndicator']}" />
					<m:br id="dduBr15Id"></m:br>
					<h:selectOneRadio id="responseIndicator"						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.responseIndicator}"						disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItem itemValue="true" itemLabel="#{msg['label.yes']}" />
						<f:selectItem itemValue="false" itemLabel="#{msg['label.no']}" />
					</h:selectOneRadio>
				</m:div>
			</m:td>
		</m:tr>
	</m:table>
</m:section>
<m:br id="dduBr30Id"/>
<m:br id="dduBr26Id"/>
<m:section styleClass="casebg" id="dduSec2">
	<m:legend id="leg3">
		<h:outputText styleClass="strong" id="dduOutTxt12Id"			value="#{msg['label.case.caseDetails.decision']}" />
	</m:legend>
	<m:table width="100%" id="dduTab4Id">
		<m:tr>
			<m:td>
				<m:div styleClass="padb" onmousedown="javascript:flagWarn=false;">
					<h:outputLabel for="descDt" id="dduLb9Id"						value="#{msg['label.case.caseDetails.decisionDate']}" />
					<m:br id="dduBr16Id"/>
					<m:inputCalendar id="descDt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true"
						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.decisionDateStr}"
						readonly="#{logCaseDataBean.disableScreenField || logCaseDataBean.statusClosedAppealUpheld}"
						disabled="#{logCaseDataBean.disableScreenField || logCaseDataBean.statusClosedAppealUpheld}" />
				</m:div>
				<h:message for="descDt" styleClass="errorMessage" id="dduMsg11Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
				<m:br id="dduBr17Id"></m:br>
			</m:td>
			<m:td>
				<m:div styleClass="padb" onmousedown="javascript:flagWarn=false;">
					<h:outputText value="#{msg['label.case.astrisk']}"						id="appBegDtAsterisk" styleClass="required" rendered="#{logCaseDataBean.statusClosedAndApproved}"/>
					<h:outputLabel for="appbegDt" id="dduLb10Id"						value="#{msg['label.case.caseDetails.approvalBeginDate']}" />
					<m:br id="dduBr18Id"/>
					<m:inputCalendar id="appbegDt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true"
						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.approvedBeginDateStr}"
						readonly="#{logCaseDataBean.disableScreenField || logCaseDataBean.statusClosedAppealUpheld}"
						disabled="#{logCaseDataBean.disableScreenField || logCaseDataBean.statusClosedAppealUpheld}" />
				</m:div>
				<h:message for="appbegDt" styleClass="errorMessage" id="dduMsg12Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
				<m:br id="dduBr19Id"></m:br>
			</m:td>
			<m:td>
				<m:div styleClass="padb" onmousedown="javascript:flagWarn=false;">
					<h:outputLabel for="appldesDt" id="dduLb11Id"						value="#{msg['label.case.caseDetails.appealDecisionDate']}" />
					<m:br id="dduBr20Id"/>
					<m:inputCalendar id="appldesDt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true"
						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.appealDecisionDateStr}"
						readonly="#{logCaseDataBean.disableScreenField || logCaseDataBean.statusClosedAppealUpheld}"
						disabled="#{logCaseDataBean.disableScreenField || logCaseDataBean.statusClosedAppealUpheld}"
						 
						/>
				</m:div>
				<h:message for="appldesDt" styleClass="errorMessage" id="dduMsg13Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
				<m:br id="dduBr21Id"></m:br>
			</m:td>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel for="levelOfCare" id="dduLb12Id"						value="#{msg['label.case.caseDetails.levelOfCare']}" />
					<m:br id="dduBr22Id"/>
					<h:selectOneMenu id="levelOfCare"						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.levelOfCareCd}"						disabled="#{logCaseDataBean.disableScreenField || logCaseDataBean.statusClosedAppealUpheld}">
						<f:selectItems value="#{logCaseDataBean.levelOfCareList}" />
					</h:selectOneMenu>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padb">
					<h:outputText value="#{msg['label.case.astrisk']}"						id="denialAsterisk" styleClass="required" rendered="#{logCaseDataBean.statusClosedAppealUpheld}"/>
					<h:outputLabel for="denialReason" id="dduLb13Id"						value="#{msg['label.case.caseDetails.denialReason']}" />
					<m:br id="dduBr23Id"/>
					<h:selectOneMenu id="denialReason"						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.denialReasonCd}"						disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.denialReasonList}" />
					</h:selectOneMenu>
				</m:div>
				<h:message for="denialReason" styleClass="errorMessage" id="dduMsg14Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td>
				<m:div styleClass="padb">
				<h:outputText value="#{msg['label.case.astrisk']}"						id="clCode" styleClass="required" rendered="#{logCaseDataBean.statusClosedAndApproved}"/>
					<h:outputLabel for="closeCode" id="dduLb14Id"						value="#{msg['label.case.caseDetails.closeCode']}" />
					<m:br id="dduBr24Id"/>
					<h:selectOneMenu id="closeCode"						value="#{logCaseDataBean.caseDetailsVO.caseTypeDDUVO.closeCodeCd}"						disabled="#{logCaseDataBean.disableScreenField || logCaseDataBean.statusClosedAppealUpheld}">
						<f:selectItems value="#{logCaseDataBean.closeCodeList}" />
					</h:selectOneMenu>
					<m:br id="dduBr34Id"/>
					<h:message for="closeCode" style="width:250px;color:red;" id="dduMsg15Id" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
				</m:div>
				
			</m:td>
		</m:tr>
	</m:table>
</m:section>
   <%-- Added for the defect id ESPRD00329240  --%>
	<m:br id="dduBr31"/>
	<m:div styleClass="clearl" id="dduDiv">
	</m:div>
	
  <%-- Ends for the defect id ESPRD00329240 --%>
