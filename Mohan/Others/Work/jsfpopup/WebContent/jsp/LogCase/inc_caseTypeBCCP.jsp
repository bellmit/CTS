<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:table id = "caseTypeBCCPTable1" width="100%">
	<m:tr id = "caseTypeBCCPTr1">
		<m:td id = "caseTypeBCCPTd1">
			<m:div id = "caseTypeBCCPdiv1Id" styleClass="padb">
				<h:outputLabel id = "caseTypeBCCPlbl1" for="appDt"					value="#{msg['label.case.caseDetails.applicationDate']}" />
				<m:br id="caseTypeBCCPBr1Id"/>
				<m:inputCalendar id="appDt" size="10" disabled="#{logCaseDataBean.disableScreenField}"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.applicationDateStr}" />
			</m:div>
			<m:br id="caseTypeBCCPBr2Id"/>
			  <h:message id = "caseTypeBCCPmsg1" for="appDt" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
		</m:td>
		<m:td id = "caseTypeBCCPTd2">
			<h:outputLabel id = "caseTypeBCCPlbl2" for="bccpID"				value="#{msg['label.case.caseDetails.bccpID']}" />
			<m:br id="caseTypeBCCPBr3"/>
			<%--onkeyup and onblur commented for defect ESPRD00754727--%>
			<%-- onkeyup="this.value=this.value.toUpperCase();"			onblur="this.value=this.value.toUpperCase();"--%>
			<h:inputText id="bccpID" maxlength="15"
						
			value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.bccpID}" 			disabled="#{logCaseDataBean.disableScreenField}"/>
			<m:br id="caseTypeBCCPBr4"/>
		    <h:message id = "caseTypeBCCPmsg2" for="bccpID" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
		   <m:br id="caseTypeBCCPBr5"/>
	    </m:td>
		<m:td id = "caseTypeBCCPTd3">
			<h:outputLabel id = "caseTypeBCCPlbl3" for="contactPerson"				value="#{msg['label.case.caseDetails.contactPerson']}" />
			<m:br id="caseTypeBCCPBr6Id"/>
			<h:inputText id="contactPerson" 
			value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.contactPerson}" 			disabled="#{logCaseDataBean.disableScreenField}"/>
			<%--onkeyup and onblur commented for defect ESPRD00754727--%>
			<%-- onkeyup="this.value=this.value.toUpperCase();"				onblur="this.value=this.value.toUpperCase();"--%>
			<m:br id="caseTypeBCCPBr7Id"/>
			 <h:message id = "caseTypeBCCPmsg3" for="contactPerson" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
		</m:td>
		<m:td id = "caseTypeBCCPTd4">
			<%--Modified for BRC CON0307.0001.01 starts--%>
			<m:div id = "caseTypeBCCPdiv2" styleClass="padb" rendered="#{logCaseDataBean.physicianOverseeingFlag}">
				<h:outputLabel id = "caseTypeBCCPlbl4" for="phyoverseeingcr"					value="#{msg['label.case.caseDetails.physicianOverseeingCare']}" />
				<m:br id="caseTypeBCCPBr8Id"/>
				<h:selectOneMenu id="phyoverseeingcr" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.physicianOverseeingCare}"				disabled="#{logCaseDataBean.disableScreenField}" >
					<f:selectItem itemLabel="Please Select One" itemValue=" " id="caseTypeBCCPSelItm1Id"/>
					<f:selectItems value="#{logCaseDataBean.physicianOverseeingList}" id="caseTypeBCCPSelItm2Id"/>
				</h:selectOneMenu>
			</m:div>
			<m:div id = "caseTypeBCCPdiv3" styleClass="padb" rendered="#{!logCaseDataBean.physicianOverseeingFlag}">
				<h:outputLabel id = "caseTypeBCCPlbl5" 	for="caseTypeBCCPsom1"			value="#{msg['label.case.caseDetails.physicianOverseeingCare']}" />
				<m:br id="caseTypeBCCPBr9Id"/>
				<h:selectOneMenu id = "caseTypeBCCPsom1"  value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.physicianOverseeingCare}" disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemLabel="Please Select One" itemValue=" " id="caseTypeBCCPSelItm3Id"/>
				</h:selectOneMenu>
			</m:div>
			<%--Modified for BRC CON0307.0001.01 ends--%>
		</m:td>
	</m:tr>
</m:table>
<m:br id="caseTypeBCCPBr10Id"/>
<m:br id="caseTypeBCCPBr11Id"/>

<m:section id = "caseTypeBCCPsec1" styleClass="casebg">
	<m:legend  id = "caseTypeBCCPlgnd1">
		<h:outputText id = "caseTypeBCCPot1" styleClass="strong"				value="#{msg['label.case.caseDetails.screening']}" />
	</m:legend>
	<m:table id = "caseTypeBCCPTable2" width="100%">
		<m:tr id = "caseTypeBCCPTr2">
			<m:td id = "caseTypeBCCPTd5">
				<m:div id = "caseTypeBCCPdiv4" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl6" for="enrollDt"						value="#{msg['label.case.caseDetails.enrollmentDate']}" />
					<m:br id="caseTypeBCCPBr12Id"/>
					<m:inputCalendar id="enrollDt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.enrollmentDateStr}" 
						disabled="#{logCaseDataBean.disableScreenField}"/>
				</m:div>
				<m:br id="caseTypeBCCPBr13Id"/>
				<h:message id = "caseTypeBCCPmsg4" for="enrollDt" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd6">
				<m:div id = "caseTypeBCCPdiv5" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl7" for="agncysite"						value="#{msg['label.case.caseDetails.agencySite']}" />
					<m:br id="caseTypeBCCPBr14Id"/>
					<h:selectOneMenu id="agncysite" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.agencySite}"					disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.agencySiteList}" id="caseTypeBCCPSelItm4Id"/>
					</h:selectOneMenu>
				</m:div>
			</m:td>
			<m:td id = "caseTypeBCCPTd7">
				<h:outputLabel id = "caseTypeBCCPlbl8" for="agencyCaseManager"					value="#{msg['label.case.caseDetails.agencyCaseManager']}" />
				<m:br id="caseTypeBCCPBr15Id"/>
				<h:inputText id="agencyCaseManager" 
				value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.agencyCaseManager}" 				disabled="#{logCaseDataBean.disableScreenField}"/>
				<%--onkeyup and onblur commented for defect ESPRD00754727--%>
				<%-- onkeyup="this.value=this.value.toUpperCase();"				onblur="this.value=this.value.toUpperCase();"--%>
				<m:br id="caseTypeBCCPBr16Id"/>
			    <h:message id = "caseTypeBCCPmsg5" for="agencyCaseManager" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd8">
				<h:outputLabel id = "caseTypeBCCPlbl9" for="agencyPhoneNo"					value="#{msg['label.case.caseDetails.agencyPhoneNo']}" />
				<m:br id="caseTypeBCCPBr17Id"/>
				<h:inputText id="agencyPhoneNo" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.agencyPhoneNumber}" 				disabled="#{logCaseDataBean.disableScreenField}" maxlength="10"/>
				<m:br id="caseTypeBCCPBr18Id"/>
			 	<h:message id = "caseTypeBCCPmsg6" for="agencyPhoneNo" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
		</m:tr>
	</m:table>
</m:section>
<m:br id="caseTypeBCCPBr19Id"/>
<m:br id="caseTypeBCCPBr20Id"/>
<m:anchor  name="BCCPbiopsyFocusPage"></m:anchor> <%-- Added the anchor tag for the defect id ESPRD00697332_07DEC2011--%>
<m:section id = "caseTypeBCCPsec2" styleClass="casebg">
	<m:legend  id = "caseTypeBCCPlgnd2">
		<h:outputText id = "caseTypeBCCPot6" styleClass="strong"				value="#{msg['label.case.caseDetails.biopsy']}" />
	</m:legend>

	<m:table id = "caseTypeBCCPTable3" width="100%">
		<m:tr id = "caseTypeBCCPTr3">
			<m:td id = "caseTypeBCCPTd9">
				<m:div id = "caseTypeBCCPdiv6" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl10" value="#{msg['label.case.caseDetails.biopsyDate']}" for="biopsyDt" />
					<m:br id="caseTypeBCCPBr21Id"/>
					<!--Begin - Added the attribute 'onfocus' and 'onblur' for the defect id ESPRD00677719 -->
					<%-- Added the focusPage() in rowblur for the defect id ESPRD00697332_07DEC2011--%>
					<m:inputCalendar id="biopsyDt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true" 
						value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.biopsyDateStr}"
						onfocus="javascript:flagWarn=false;validateBiopsyDate();"
						onblur="javascript:flagWarn=false;focusPage('BCCPbiopsyFocusPage');focusThis('biopsyfndgs');validateBiopsyDate();this.form.submit();"  
						valueChangeListener="#{logCaseControllerBean.showBiopsyFindings}" 
						disabled="#{logCaseDataBean.disableScreenField}"/> 
					<!--End - Added the attribute 'onfocus' and 'onblur' for the defect id ESPRD00677719 -->
				</m:div>
				<m:br id="caseTypeBCCPBr22Id"/>
				<h:message id = "caseTypeBCCPmsg7" for="biopsyDt" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd10">
				<m:div id = "caseTypeBCCPdiv7" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl11" for="biopsyfndgs"						value="#{msg['label.case.caseDetails.biopsyFindings']}" />
					<m:br id="caseTypeBCCPBr23Id"/>
					<h:selectOneMenu id="biopsyfndgs" 					value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.biopsyFindingsCd}"					disabled="#{(!logCaseDataBean.enableBiopsyFindings) || logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.biopsyFindingsList}" id="caseTypeBCCPSelItm5Id"/>
					</h:selectOneMenu>
				</m:div>
			</m:td>
		</m:tr>
	</m:table>
</m:section>
<m:br />
<m:br />
<m:section id = "caseTypeBCCPsec3" styleClass="casebg">
	<m:anchor name="BCCPrecomFocusPage"></m:anchor>
	<m:legend  id = "caseTypeBCCPlgnd3">
		<h:outputText id = "caseTypeBCCPot5" styleClass="strong"				value="#{msg['label.case.caseDetails.recommendation']}" />
	</m:legend>

	<m:table id = "caseTypeBCCPTable4" width="100%">
		<m:tr id = "caseTypeBCCPTr4">
			<m:td id = "caseTypeBCCPTd11">
				<m:div id = "caseTypeBCCPdiv8" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl12" for="recommendation"						value="#{msg['label.case.caseDetails.recommendation']}" />
					<m:br id="caseTypeBCCPBr24Id"/>
					<%-- Added the focusPage() in onchange for the defect id ESPRD00697332_07DEC2011--%>
					<h:selectOneMenu id="recommendation" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.recommendationCd}"					onchange="javascript:flagWarn=false;focusPage('BCCPrecomFocusPage');focusThis(this.id);stFollowAction();" disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.recommenList}" id="caseTypeBCCPSelItm6Id"/>
					</h:selectOneMenu>
				</m:div>
			</m:td>
			<m:td id = "caseTypeBCCPTd12">
				<h:outputLabel id = "caseTypeBCCPlbl13" for="stFollowup"					value="#{msg['label.case.caseDetails.stFollowUp']}" />
				<m:br id="caseTypeBCCPBr25Id"/>
				<h:inputText id="stFollowup" maxlength="2" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.stFollowUPNum}" 					disabled="#{(!logCaseDataBean.enableFollowUpMon) || logCaseDataBean.disableScreenField}"/>
				<m:br id="caseTypeBCCPBr26Id"/>
				<h:message id = "caseTypeBCCPmsg8" for="stFollowup" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd13">
				<m:div id = "caseTypeBCCPdiv9" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl14" for="consultDt" value="#{msg['label.case.caseDetails.consultDate']}" />
					<m:br id="caseTypeBCCPBr27Id"/>
					<m:inputCalendar id="consultDt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.consultDateStr}" 
						disabled="#{logCaseDataBean.disableScreenField}"/>
				</m:div>
				<m:br id="caseTypeBCCPBr28Id"></m:br>
				<h:message id = "caseTypeBCCPmsg9" for="consultDt" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
		</m:tr>
	</m:table>
</m:section>
<m:br />
<m:br />
<m:section id = "caseTypeBCCPsec4" styleClass="casebg">
	<m:legend  id = "caseTypeBCCPlgnd4">
		<h:outputText id = "caseTypeBCCPot4" styleClass="strong"				value="#{msg['label.case.caseDetails.finalCancer']}" />
	</m:legend>

	<m:table id = "caseTypeBCCPTable5" width="100%">
		<m:tr id = "caseTypeBCCPTr5">
			<m:td id = "caseTypeBCCPTd14">
				<m:div id = "caseTypeBCCPdiv10" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl15" for="stage"						value="#{msg['label.case.caseDetails.stage']}" />
					<m:br id="caseTypeBCCPBr29Id"/>
					<h:selectOneMenu id="stage" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.finalStageCd}"					disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.stageBCCPList}" id="caseTypeBCCPSelItm8Id"/>
					</h:selectOneMenu>
				</m:div>
			</m:td>
			<m:td id = "caseTypeBCCPTd15">
				<h:outputLabel id = "caseTypeBCCPlbl16" for="metaStatisArea"					value="#{msg['label.case.caseDetails.metaStatisArea']}" />
				<m:br id="caseTypeBCCPBr30Id"/>
				<%--onkeyup and onblur commented for defect ESPRD00754727--%>
				<%-- onkeyup="this.value=this.value.toUpperCase();" onblur="this.value=this.value.toUpperCase();"--%> 
				<h:inputText id="metaStatisArea" 
				maxlength="2"				value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.metastasisAreaCd}" 				disabled="#{logCaseDataBean.disableScreenField}"/>
				<m:br id="caseTypeBCCPBr31Id"/>
				<h:message id = "caseTypeBCCPmsg10" for="metaStatisArea" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd16">
				<h:outputLabel id = "caseTypeBCCPlbl17" for="unstatedReason"					value="#{msg['label.case.caseDetails.unstatedReason']}" />
				<m:br id="caseTypeBCCPBr32Id"/>
				<%--onkeyup and onblur commented for defect ESPRD00754727--%>
				<%-- onkeyup="this.value=this.value.toUpperCase();"				onblur="this.value=this.value.toUpperCase();"--%> 
				<h:inputText id="unstatedReason" 
				maxlength="2"				value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.unstagedReasonCd}" 				disabled="#{logCaseDataBean.disableScreenField}"/>
				<m:br id="caseTypeBCCPBr33Id"/>
				<h:message id = "caseTypeBCCPmsg11" for="unstatedReason" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd17">
				<h:outputLabel id = "caseTypeBCCPlbl18" for="tumorSize"					value="#{msg['label.case.caseDetails.tumorSize']}" />
				<m:br id="caseTypeBCCPBr34Id"/>
				<h:inputText id="tumorSize" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.tumorSizeNum}" 				disabled="#{(!logCaseDataBean.enableTumorAndNodes) || logCaseDataBean.disableScreenField}"/>
				<m:br id="caseTypeBCCPBr35Id"/>
				<h:message id = "caseTypeBCCPmsg12" for="tumorSize" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd18">
				<h:outputLabel id = "caseTypeBCCPlbl19" for="nodesPositive"					value="#{msg['label.case.caseDetails.nodesPositive']}" />
				<m:br id="caseTypeBCCPBr36Id"/>
				<h:inputText id="nodesPositive" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.nodesPositiveNum}" 				disabled="#{(!logCaseDataBean.enableTumorAndNodes) || logCaseDataBean.disableScreenField}"/>
				<m:br id="caseTypeBCCPBr37Id"/>
		     	<h:message id = "caseTypeBCCPmsg13" for="nodesPositive" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
		</m:tr>
	</m:table>
</m:section>
<m:br id="caseTypeBCCPBr38Id"/>
<m:br id="caseTypeBCCPBr39Id"/>
<m:anchor name="BCCPtreatmentFocusPage"></m:anchor> <%-- Added the anchor tag for the defect id ESPRD00697332_07DEC2011--%>
<m:section id = "caseTypeBCCPsec5" styleClass="casebg">
	<m:legend id = "caseTypeBCCPlgnd9">
		<h:outputText id = "caseTypeBCCPot3" styleClass="strong"				value="#{msg['label.case.caseDetails.treatment']}" />
	</m:legend>
	<m:table id = "caseTypeBCCPTable6" width="100%">
		<m:tr id = "caseTypeBCCPTr6">
			<m:td id = "caseTypeBCCPTd19">
				<m:div id = "caseTypeBCCPdiv11" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl20" for="trmtstrtd"						value="#{msg['label.case.caseDetails.treatmentStarted']}" />
					<m:br id="caseTypeBCCPBr40Id"/>
					<%-- Added the focusPage() in onchange for the defect id ESPRD00697332_07DEC2011--%>
					<h:selectOneMenu id="trmtstrtd" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.treatmentStartedCd}"					onchange="javascript:flagWarn=false;focusThis(this.id);focusPage('BCCPtreatmentFocusPage');javascript:showTreamentStartedDate();" disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.treatmentStartedList}" id="caseTypeBCCPSelItm9Id"/>
					</h:selectOneMenu>
				</m:div>
			</m:td>
			<m:td id = "caseTypeBCCPTd20">
				<m:div id = "caseTypeBCCPdiv12" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl21" for="trtmtstrtDt"						value="#{msg['label.case.caseDetails.treatmentStartedDate']}" />
					<m:br id="caseTypeBCCPBr41Id"/>
					<m:inputCalendar id="trtmtstrtDt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.treatmentStartDateStr}" 
						disabled="#{(!logCaseDataBean.enableTreatementStDates) || logCaseDataBean.disableScreenField}"/>
				</m:div>
				<m:br id="caseTypeBCCPBr42Id"/>
				  <h:message id = "caseTypeBCCPmsg14" for="trtmtstrtDt" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd21">
				<m:div id = "caseTypeBCCPdiv13" styleClass="padb">
					<h:outputLabel id = "caseTypeBCCPlbl22" for="chmoendDt"						value="#{msg['label.case.caseDetails.chemoProjectEndDate']}" />
					<m:br id="caseTypeBCCPBr43Id"/>
					<m:inputCalendar id="chmoendDt" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" readonly="false" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.chemoProjectedEndDateStr}" 
						disabled="#{(!logCaseDataBean.enableTreatementStDates) || logCaseDataBean.disableScreenField}"/>
				</m:div>
				<m:br id="caseTypeBCCPBr44Id"/>
				 <h:message id = "caseTypeBCCPmsg15" for="chmoendDt" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
		</m:tr>
	</m:table>
</m:section>
<m:br id="caseTypeBCCPBr45Id"/>
<m:br id="caseTypeBCCPBr46Id"/>
<m:section id = "caseTypeBCCPsec6" styleClass="casebg">
	<m:legend id = "caseTypeBCCPlgnd6">
		<h:outputText id = "caseTypeBCCPot2" styleClass="strong"				value="#{msg['label.case.caseDetails.homeCareHospice']}" />
	</m:legend>
	<m:table id = "caseTypeBCCPTable7" width="100%">
		<m:tr id = "caseTypeBCCPTr7">
			<m:td id = "caseTypeBCCPTd22">
				<h:outputLabel id = "caseTypeBCCPlbl23" for="careGiverName"					value="#{msg['label.case.caseDetails.careGiverName']}" />
				<m:br id="caseTypeBCCPBr47Id"/>
				<%--onkeyup and onblur commented for defect ESPRD00754727--%>
				<%-- onkeyup="this.value=this.value.toUpperCase();"				onblur="this.value=this.value.toUpperCase();"--%>
				<h:inputText id="careGiverName"	
				value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.caregiverName}" 				disabled="#{logCaseDataBean.disableScreenField}"/>
				<m:br id="caseTypeBCCPBr48Id"/>
				 <h:message id = "caseTypeBCCPmsg16" for="careGiverName" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd23">
				<h:outputLabel id = "caseTypeBCCPlbl24" for="careGiverPhone"					value="#{msg['label.case.caseDetails.careGiverPhone']}" />
				<m:br id="caseTypeBCCPBr49Id"/>
				<h:inputText id="careGiverPhone" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.caregiverPhone}"				disabled="#{logCaseDataBean.disableScreenField}" maxlength="10"/>
				<m:br id="caseTypeBCCPBr50Id"/>
			 	<h:message id = "caseTypeBCCPmsg17" for="careGiverPhone" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id = "caseTypeBCCPTd24">
				<h:outputLabel id = "caseTypeBCCPlbl25" for="frm778"					value="#{msg['label.case.caseDetails.form778']}" />
				<m:br id="caseTypeBCCPBr51Id"/>
				<h:selectOneRadio id="frm778" value="#{logCaseDataBean.caseDetailsVO.caseTypeBCCPVO.form778SignedInd}"				disabled="#{logCaseDataBean.disableScreenField}">
					<f:selectItem itemLabel="#{msg['label.yes']}" itemValue="true" id="caseTypeBCCPSelItm10Id"/>
					<f:selectItem itemLabel="#{msg['label.no']}" itemValue="false" id="caseTypeBCCPSelItm11Id"/>
				</h:selectOneRadio>
			</m:td>
		</m:tr>
	</m:table>
</m:section>
