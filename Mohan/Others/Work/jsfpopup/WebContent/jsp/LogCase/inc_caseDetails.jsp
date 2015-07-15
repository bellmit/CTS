<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<portlet:defineObjects />

<%-- <t:saveState id="CMGTTOMSS170" value="#{logCaseDataBean}"></t:saveState> for ESPRD00682799--%>
<m:section styleClass="casebg" id="caseDetailSec1">
	<m:legend id="caseDetailLeg1">
		<h:outputText id="textOne" styleClass="strong"
			value="#{msg['title.case.caseDetails']}" />
	</m:legend>

	<h:panelGroup id="workUnitGroup">

		<m:table width="100%" id="caseDetailTab1Id">
			<m:tr id="caseDetailTab1Row1Id">
				<m:td id="caseDetailTab1Row1Col1Id">
					<m:div styleClass="padb" id="caseDetailTab1Row1Col1Div1Id">
						<h:outputText id="textTwo"
							value="#{msg['label.case.caseDetails.step']}"
							styleClass="outputLabel" />
						<m:br id="caseDetailTab1Row1Div1Br1Id"></m:br>
						<h:outputText id="textThree"
							value="#{logCaseDataBean.caseDetailsVO.step}" />
					</m:div>
				</m:td>
				<m:td id="caseDetailTab1Row1Col2Id">
				</m:td>
				<m:td id="caseDetailTab1Row1Col3Id">
					<m:div styleClass="padb" id="caseDetailTab1Row1Col3Div1Id">
						<h:outputText id="textfour"
							value="#{msg['label.case.routing.createdBy']}"
							styleClass="outputLabel" />
						<m:br id="caseDetailTab1Row1Col3Div1Br1Id"></m:br>
						<h:outputText id="textFive"
							value="#{logCaseDataBean.caseDetailsVO.createdBy}" />
					</m:div>
				</m:td>
				<m:td id="caseDetailTab1Row1Col4Id">
					<m:div styleClass="padb" id="caseDetailTab1Row1Col4Div1Id">
						<h:outputText id="textSix"
							value="#{msg['label.case.caseDetails.assignedTo']}"
							styleClass="outputLabel" />
						<m:br id="caseDetailTab1Row1Col4Div1Br1Id"></m:br>
						<h:outputText id="textSeven"
							value="#{logCaseDataBean.caseDetailsVO.assignedTo}" />
					</m:div>
				</m:td>
				<m:td id="caseDetailTab1Row1Col5Id">
					<m:div styleClass="padb" id="caseDetailTab1Row1Col5Div1Id">
						<h:outputText id="textEight"
							value="#{msg['label.case.Correspondence.createdate']}"
							styleClass="outputLabel" />
						<m:br></m:br>
						<h:outputText id="caseDetailTab1Row1Col5Div1OutTxt2Id"
							value="#{logCaseDataBean.caseDetailsVO.createdDateStr}" />
					</m:div>
				</m:td>
				<m:td id="caseDetailTab1Row1Col6Id">
					<m:div styleClass="padb" rendered="#{logCaseDataBean.openDays}"
						id="caseDetailTab1Row1Col6Div1Id">
						<h:outputText id="textNine"
							value="#{msg['label.case.caseDetails.daysopened']}"
							styleClass="outputLabel" />
						<m:br id="caseDetailTab1Row1Col6Div1Br1Id"></m:br>
						<h:outputText id="textTen"
							value="#{logCaseDataBean.caseDetailsVO.daysopened}" />
					</m:div>

					<m:div styleClass="padb" rendered="#{!logCaseDataBean.openDays}"
						id="caseDetailTab1Row1Col6Div2Id">
						<h:outputText id="daysClosedLevel"
							value="#{msg['label.case.caseDetails.daysClosed']}"
							styleClass="outputLabel" />
						<m:br id="caseDetailTab1Row1Col6Div2Br1Id"></m:br>
						<h:outputText id="daysClosedValue"
							value="#{logCaseDataBean.caseDetailsVO.closedDays}" />
					</m:div>
				</m:td>
				<m:td id="caseDetailTab1Row1Col7Id">
					<m:div styleClass="padb" id="caseDetailTab1Row1Col7Div1Id">
						<h:outputText id="textEleven" value="#{msg['label.case.astrisk']}"
							styleClass="required" />
						<h:outputText id="textTweleve"
							value="#{msg['label.case.caseDetails.reportingUnit']}"
							styleClass="outputLabel" />
						<m:br id="caseDetailTab1Row1Col7Div1Br1Id"></m:br>
						<h:outputText id="textThirteen"
							value="#{logCaseDataBean.caseDetailsVO.reportingUnit}" />
					</m:div>
				</m:td>
				<m:td id="caseDetailTab1Row1Col8Id">
					<m:div styleClass="padb" id="caseDetailTab1Row1Col8Div1Id">
						<h:outputText value="#{msg['label.case.astrisk']}"
							id="textFourteen" styleClass="required" />
						<h:outputText
							value="#{msg['label.case.caseDetails.businessUnit']}"
							id="textFifteen" styleClass="outputLabel" />
						<m:br id="caseDetailTab1Row1Col8Div1Br1Id"></m:br>
						<h:outputText
							value="#{logCaseDataBean.caseDetailsVO.businessUnit}"
							id="textSixteen" />
					</m:div>
				</m:td>
				<m:td id="caseDetailTab1Row1Col9Id">
					<m:div styleClass="padb" id="caseDetailTab1Row1Col9Div1Id">
						<h:outputText value="#{msg['label.case.astrisk']}"
							id="textSeventeen" styleClass="required" />
						<h:outputLabel value="#{msg['label.case.caseDetails.workUnit']}"
							id="textEighteen" for="LOGCASEWORKUNIT" />
						<m:br id="caseDetailTab1Row1Col9Div1Br1Id"></m:br>
						<h:selectOneMenu id="LOGCASEWORKUNIT"
							disabled="#{logCaseDataBean.disableWorkUnit || logCaseDataBean.disableWorkUnitInAddMode}"
							value="#{logCaseDataBean.caseDetailsVO.workUnit}"
							valueChangeListener="#{logCaseControllerBean.getReportingAndBusinessUnit}"
							onchange="javascript:flagWarn=false;focusThis(this.id);focusPage('caseDetailsPageHeader');"> <%-- Added the focusPage() for the defect id  --%>
							<f:selectItems value="#{logCaseDataBean.listOfDepartments}" />
						</h:selectOneMenu>
						<%--	<h:selectOneMenu id="workunit" disabled="#{logCaseDataBean.disableWorkUnit}"						value="#{logCaseDataBean.caseDetailsVO.workUnit}"												onchange="javascript:focusThis(this.id);">
						<f:selectItems value="#{logCaseDataBean.listOfDepartments}" />
					</h:selectOneMenu> this.form.submit()--%>
				 <hx:behavior id="case3"  event="onchange" behaviorAction="get" targetAction="workUnitGroup" target="LOGCASEWORKUNIT"></hx:behavior>	
				
					</m:div>
					<h:message id="PRGCMGTM119" for="LOGCASEWORKUNIT" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"
						styleClass="errorMessage" />
				</m:td>
			</m:tr>
		</m:table>
	</h:panelGroup>
	<hx:ajaxRefreshSubmit id="workUnitRefreshRequest" target="workUnitGroup"></hx:ajaxRefreshSubmit>

	<m:table width="100%" id="caseDetailTab2Id">
		<m:tr id="caseDetailTab2Row1Id">
			<m:td colspan="2" id="caseDetailTab3rOW1Col1Id">
				<m:div styleClass="padb"
					rendered="#{!(logCaseDataBean.showCaseType)}"
					id="caseDetailTab3rOW1Col1Div1Id">
					<h:outputText value="#{msg['label.case.astrisk']}"
						id="textNineteen" styleClass="required" />
					<h:outputLabel for="casetype" id="textTwenty"
						value="#{msg['label.case.caseDetails.caseType']}" />
					<m:br id="caseDetailTab3rOW1Col1Div1Br1Id" />


					<%--Infinite added for UC-PGM-CRM-018.10, CR 2401 --%>
					<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543--%>

					<%--<h:selectOneMenu id="casetype"						value="#{logCaseDataBean.caseDetailsVO.caseType}"						valueChangeListener="#{logCaseControllerBean.showCaseTypetempScreen}"						onchange="javascript:focusThis(this.id);this.form.submit()"						onmousedown="javascript:flagWarn=false;">
						<f:selectItems value="#{logCaseDataBean.caseTypeList}" />
					</h:selectOneMenu>--%>

					<h:selectOneMenu id="casetype"
						value="#{logCaseDataBean.caseDetailsVO.caseType}"
						valueChangeListener="#{logCaseControllerBean.showCaseTypetempScreen}"
						onchange="javascript:flagWarn=false;focusThis(this.id);focusPage('caseDetailsPageHeader');this.form.submit()">
						<f:selectItem itemValue=" " itemLabel="" />
						<f:selectItems value="#{logCaseDataBean.caseTypeList}" />
					</h:selectOneMenu>
					<%--<hx:behavior id="case2"  event="onchange" behaviorAction="get;get" targetAction="caseDetailGroup;statusrefreshGroup" target="casetype"></hx:behavior>--%>

				</m:div>
				<h:message id="PRGCMGTM118" for="casetype" styleClass="errorMessage"  rendered="#{logCaseDataBean.logCaseErrMsgFlag}" />
				<m:div styleClass="padb" rendered="#{logCaseDataBean.showCaseType}"
					id="caseDetailTab3rOW1Col1Div2Id">
					<h:outputText value="#{msg['label.case.astrisk']}"
						id="textNineteen1" styleClass="required" />
					<h:outputLabel for="casetype1" id="textTwenty1"
						value="#{msg['label.case.caseDetails.caseType']}" />
					<m:br id="caseDetailTab3rOW1Col1Div2Br1Id" />
					<h:selectOneMenu id="casetype1"
						disabled="#{logCaseDataBean.showCaseType}"
						value="#{logCaseDataBean.caseDetailsVO.caseType}">
						<f:selectItems value="#{logCaseDataBean.caseTypeList}" />
					</h:selectOneMenu>
				</m:div>

			</m:td>
			<m:td id="caseDetailTab3rOW1Col2Id">

				<m:div styleClass="padb" id="caseDetailTab3rOW1Col2IdDiv1Id">
					<h:outputText value="#{msg['label.case.astrisk']}"
						id="textTwentyOne" styleClass="required" />
					<h:outputLabel for="LOGCASESTATUS"
						value="#{msg['label.case.caseDetails.status']}" id="textTwentyTwo" />
					<m:br id="caseDetailTab3rOW1Col2IdDiv1Br1Id" />
					<h:selectOneMenu id="LOGCASESTATUS"
						value="#{logCaseDataBean.caseDetailsVO.status}"
						disabled="#{logCaseDataBean.disableScreenField}"
						valueChangeListener="#{logCaseControllerBean.onStatusChange}"
						onchange="javascript:flagWarn=false;focusThis(this.id);focusPage('caseDetailsPageHeader');this.form.submit()"> <%-- Added the focusPage() for the defect id ESPRD00697332_07DEC2011--%>
						<f:selectItems value="#{logCaseDataBean.statusList}" />
					</h:selectOneMenu>
					<%--	 	<h:panelGroup id="statusrefreshGroup" >
						<h:selectOneMenu id="status"							value="#{logCaseDataBean.caseDetailsVO.status}"							disabled="#{logCaseDataBean.disableScreenField}"							valueChangeListener="#{logCaseControllerBean.onStatusChange}"							onchange="javascript:focusThis(this.id);"							onmousedown="javascript:flagWarn=false;">
							<f:selectItems value="#{logCaseDataBean.statusList}" />
						</h:selectOneMenu>
						<hx:behavior id="case7"  event="onchange" behaviorAction="get" targetAction="statusrefreshGroup" target="status"></hx:behavior>	
					</h:panelGroup>
					<hx:ajaxRefreshSubmit id="statusRefreshRequest" target="statusrefreshGroup"></hx:ajaxRefreshSubmit>--%>
				</m:div>
				<h:message id="PRGCMGTM108" for="LOGCASESTATUS" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
				<m:br id="caseDetailTab3rOW1Col2IdDiv1Br2Id"></m:br>



			</m:td>
			<m:td id="caseDetailTab3rOW1Col3Id">
				<m:div styleClass="padb" id="caseDetailTab3rOW1Col3IdDiv1Id">
					<h:outputText value="#{msg['label.case.astrisk']}"
						id="textTwentyThree" styleClass="required" />
					<h:outputLabel for="priority"
						value="#{msg['label.case.caseDetails.priority']}"
						id="textTwentyFour" />
					<m:br id="caseDetailTab3rOW1Col2IdDivBr1Id" />
					<%-- Added the focusPage() in onchange for the defect id ESPRD00697332_07DEC2011--%>
					<h:selectOneMenu id="priority"
						value="#{logCaseDataBean.caseDetailsVO.priority}"
						onchange="javascript:flagWarn=false;focusThis(this.id);focusPage('caseDetailsPageHeader');this.form.submit();"
						disabled="#{logCaseDataBean.disableScreenField}"> 
						<f:selectItems value="#{logCaseDataBean.priorityList}" />
					</h:selectOneMenu>
					<%--	<h:panelGroup id="priorityRefresh">
					<h:selectOneMenu id="priority"						value="#{logCaseDataBean.caseDetailsVO.priority}" 						onchange="javascript:focusThis(this.id);"												onmousedown="javascript:flagWarn=false;"						disabled="#{logCaseDataBean.disableScreenField}">
						<f:selectItems value="#{logCaseDataBean.priorityList}" />
					</h:selectOneMenu>
					 <hx:behavior id="case5"  event="onchange" behaviorAction="get" targetAction="priorityRefresh" target="priority"></hx:behavior>	
					</h:panelGroup>
					<hx:ajaxRefreshSubmit id="priorityRefreshRequest" target="priorityRefresh" ></hx:ajaxRefreshSubmit>--%>

				</m:div>
				<h:message id="forPriority" for="priority" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}" />
			</m:td>
			<m:td id="caseDetailTab3rOW1Col4Id">
				<h:outputLabel for="casetitle"
					value="#{msg['label.case.caseDetails.caseTitle']}"
					id="textTwentyFive" />
				<m:br id="caseDetailTab3rOW1Col4Br1Id" />
				<h:inputText id="casetitle" maxlength="80"
					value="#{logCaseDataBean.caseDetailsVO.caseTitle}"
					disabled="#{logCaseDataBean.disableScreenField}" />
					<%--onkeyup commented for defect ESPRD00754727--%>
					<%-- onkeyup="this.value=this.value.toUpperCase();"--%>

				<%--onblur commented for defect ESPRD00597161--%>
				<%--onblur="this.form.submit();"--%>
				<%--		<h:panelGroup id="titleRefresh">
					<h:inputText id="casetitle" maxlength="80"					value="#{logCaseDataBean.caseDetailsVO.caseTitle}" 									onkeypress="javascript:flagWarn=false;"					disabled="#{logCaseDataBean.disableScreenField}" />
					<hx:behavior id="case6"  event="onblur" behaviorAction="get" targetAction="titleRefresh" target="casetitle"></hx:behavior>	
				   </h:panelGroup>
				   <hx:ajaxRefreshSubmit id="titleRefreshRequest" target="titleRefresh" ></hx:ajaxRefreshSubmit>--%>
				<m:br id="caseDetailTab3rOW1Col4Br2Id" />
				<h:message id="PRGCMGTM109" for="casetitle" style="color: red" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
			</m:td>
			<m:td id="caseDetailTab3rOW1Col5Id">
				<m:div styleClass="padb" id="caseDetailTab3rOW1Col5IdDiv1Id">
					<h:outputLabel for="lineofbusiness"
						value="#{msg['label.case.caseDetails.lineOfBusiness']}"
						id="textTwentySeven" />
					<m:br id="caseDetailTab3rOW1Col5Br1Id" />
					<h:panelGroup id="lineOfBusinessRefresh">
						<%-- Added the focusPage() in onchange for the defect id ESPRD00697332_07DEC2011--%>
						<h:selectOneMenu id="lineofbusiness"
							onchange="javascript:flagWarn=false;focusThis(this.id);focusPage('caseDetailsPageHeader');this.form.submit();"
							value="#{logCaseDataBean.caseDetailsVO.lineOfBusiness}"
							disabled="#{logCaseDataBean.disableScreenField}">
							<f:selectItems value="#{logCaseDataBean.lobCodeList}" />
						</h:selectOneMenu>
						<%--		<hx:behavior id="case8"  event="onblur" behaviorAction="get" targetAction="lineOfBusinessRefresh" target="lineofbusiness"></hx:behavior>	--%>
					</h:panelGroup>
					<%--	   <hx:ajaxRefreshSubmit id="lobRefreshRequest" target="lineOfBusinessRefresh" ></hx:ajaxRefreshSubmit>--%>

				</m:div>
			</m:td>
		</m:tr>
		<m:tr id="caseDetailTab3rOW2Id">
			<m:td id="caseDetailTab3rOW2Col1Id">
				<m:table id="caseDetailTab3rOW2Col1Tab1Id">
					<m:tr id="caseDetailTab3rOW2Col1Tab1Row1Id">
						<m:td id="caseDetailTab3rOW2Col1Tab1Row1Col1Id">
							<h:outputText styleClass="outputLabel"
								value="#{msg['label.case.caseDetails.supervisorsApprovalReqrd']}"
								id="textTwentyEight" />
							<h:outputText escape="false" value="&nbsp;&nbsp"
								id="caseDetailTab3rOW2Col1Tab1Row1OutTxt1Id"></h:outputText>
						</m:td>
						<m:td id="caseDetailTab3rOW2Col1Tab1Row1Col2Id">
							<h:graphicImage alt="#{msg['label.yes']}" title="#{msg['label.yes']}"
								id="caseDetailTab3rOW2Col1Tab1Row1Col2Img1Id"
								styleClass="ind_check_yes" width="15"
								rendered="#{(logCaseDataBean.superviserRevReqIndForCaseType || logCaseDataBean.superviserRevReqInd)}"
								url="/images/x.gif" />
							<t:div id="caseDetailTab3rOW2Col1Tab1Row1Col2tDiv1Id"
								rendered="#{!(logCaseDataBean.superviserRevReqIndForCaseType || logCaseDataBean.superviserRevReqInd)}">
								<%-- <h:graphicImage alt="#{msg['label.no']}" url="/wps/themes/html/ACSDefault/images/icn_x.gif" 
								rendered="#{!(logCaseDataBean.superviserRevReqIndForCaseType || logCaseDataBean.superviserRevReqInd)}"/> --%>
								<m:img src="/wps/themes/html/ACSDefault/images/icn_x.gif"
									id="caseDetailTab3rOW2Col1Tab1Row1Col2tDiv1Img1Id" alt="#{msg['label.no']}" title="#{msg['label.no']}"></m:img>
							</t:div>
						</m:td>
					</m:tr>
				</m:table>
			</m:td>
		</m:tr>
	</m:table>
	<%--<h:panelGroup id="PRGCMGTPGP58" id ="caseDetailGroup" rendered="#{logCaseControllerBean.caseTypeRefresh}">--%>
	<%--<h:panelGroup id="PRGCMGTPGP59" id ="caseDetailGroup" >--%>
	<m:div rendered="#{logCaseDataBean.showDDUTypeScreen}"
		id="casedetailDiv1Id">
		<%--Commented for CR ESPRD00865082 as Un Reachable code--%>
		<%--<m:table width="100%" id="casedetailDiv1tab1Id">
			<m:tr rendered="#{logCaseDataBean.showProviderAppTypeScreen}"
				id="casedetailDiv1tab1tr1Id">
				<m:td width="100%" id="casedetailDiv1tab1tr1td1Id">
					<f:verbatim>
						<t:htmlTag value="h4" id="casedetailDiv1tab1tr1HtmlTag1Id">
							<h:outputText id="casedetailDiv1tab1tr1HtmlTag1OutTxt1Id"
								value="#{msg['label.case.caseDetails.provAppealCasedet']}" />
						</t:htmlTag>
					</f:verbatim>
				</m:td>
			</m:tr>
		</m:table>

		<m:table width="100%" id="casedetailDiv1tab2Id">
			<m:tr rendered="#{logCaseDataBean.showMemAppTypeScreen}"
				id="casedetailDiv1tab2tr1Id">
				<m:td width="100%" id="casedetailDiv1ta21tr1Col11Id">
					<f:verbatim>
						<t:htmlTag value="h4" id="casedetailDiv1tab2tr1HtmlTag1Id">
							<h:outputText id="casedetailDiv1tab2tr1HtmlTag1OutTxt1Id"
								value="#{msg['label.case.caseDetails.memAppealCasedet']}" />
						</t:htmlTag>
					</f:verbatim>
				</m:td>
			</m:tr>
		</m:table> --%>


		<m:anchor name="caseTypeDDUfocusPage"></m:anchor> <%-- Added the anchor tag for the defect id ESPRD00697332_07DEC2011--%>
		<f:subview id="caseTypeDDU">
			<jsp:include page="inc_caseTypeDDU.jsp" />
		</f:subview>
	</m:div>

	<%--Infinite added for UC-PGM-CRM-018.10, CR 2401 
	 start  Here--%>

	<m:div rendered="#{logCaseDataBean.showMQIPTypeScreen}"
		id="casedetailDiv2Id">

		<f:subview id="caseTypeMQIP">
			<jsp:include page="inc_caseTypeQuarterlyMQIPReturns.jsp" />
		</f:subview>
	</m:div>

	<m:div rendered="#{logCaseDataBean.showFCRTypeScreen}"
		id="casedetailDiv3Id">

		<f:subview id="caseTypeFCR">
			<jsp:include page="inc_caseTypeFacilityCostReports.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showNewNonARSTypeScreen}"
		id="casedetailDiv4Id">

		<f:subview id="caseTypeNewNonARS">
			<jsp:include page="inc_caseTypeNewNonARSFacility.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showNFQATypeScreen}"
		id="casedetailDiv5Id">

		<f:subview id="caseTypeNFQA">
			<jsp:include page="inc_caseTypeNFQA.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showNewARSTypeScreen}">

		<f:subview id="caseTypeNewARS">
			<jsp:include page="inc_caseTypeNewARSFacility.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showHOCRTypeScreen}"
		id="casedetailDiv6Id">

		<f:subview id="caseTypeHOCR">
			<jsp:include page="inc_caseTypeHomeOfficeCostReport.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showAcuityRateSettingTypeScreen}"
		id="casedetailDiv7Id">

		<f:subview id="caseTypeAcuityRateSetting">
			<jsp:include page="inc_caseTypeAcuityRateSetting.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showFPPRRTypeScreen}"
		id="casedetailDiv8Id">
		<f:subview id="caseTypeFPPRR">
			<jsp:include page="inc_caseTypeFPPRR.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showCBTypeScreen}"
		id="casedetailDiv9Id">
		<f:subview id="caseTypeCB">
			<jsp:include page="inc_caseTypeCreditBalance.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showFATypeScreen}"
		id="casedetailDiv10Id">
		<f:subview id="caseTypeFA">
			<jsp:include page="inc_caseTypeFieldAudit.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showFCSTypeScreen}"
		id="casedetailDiv11Id">
		<f:subview id="caseTypeFCS">
			<jsp:include page="inc_caseTypeFacilityCensusSubmission.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showARSTypeScreen}"
		id="casedetailDiv12Id">

		<f:subview id="caseTypeARS">
			<jsp:include page="inc_caseTypeARS.jsp" />
		</f:subview>
	</m:div>


	<m:div rendered="#{logCaseDataBean.showAppealScreen}"
		id="casedetailDiv13Id">
		<f:subview id="caseTypeAppeal">
			<jsp:include page="inc_caseTypeAppealRequest.jsp" />
		</f:subview>
	</m:div>

	<%-- End Here --%>


	<m:div rendered="#{logCaseDataBean.showGeneralCaseTypeScreens}"
		id="casedetailDiv14Id">
		<f:subview id="generalCaseType">
			<jsp:include page="inc_generalCaseType.jsp" />
		</f:subview>
	</m:div>


	<m:div id="casedetailDiv15Id">
		<m:table width="100%" id="casedetailDiv15Tab1Id">
			<m:tr id="casedetailDiv15Tab1Row1Id">
				<m:td id="casedetailDiv15Tab1Row1Col1Id">
					<m:div id="customFieldDivId">
						<f:subview id="customFieldViewId">
							<jsp:include page="../customField/inc_dynamicCustomFields.jsp" />
						</f:subview>
					</m:div>
				</m:td>

			</m:tr>
		</m:table>
	</m:div>
	<m:br id="casedetailDiv15AfterBr1Id" />
	<m:anchor name="caseTypeBCCPfocusPage"></m:anchor> <%-- Added the anchor tag for the defect id ESPRD00697332_07DEC2011--%>
	<m:div rendered="#{logCaseDataBean.showBCCPTypeScreen}"
		id="casedetailDiv16Id">
		<f:subview id="caseTypeBCCP">
			<jsp:include page="inc_caseTypeBCCP.jsp" />
		</f:subview>
	</m:div>

	<%-- ADDED BY ICS --%>
	<m:div rendered="#{logCaseDataBean.showMemAppTypeScreen}"
		id="casedetailDiv17Id">
		<f:subview id="caseTypeMemApp">
			<jsp:include page="inc_caseTypeMemberAppeal.jsp" />
		</f:subview>
	</m:div>
	<%--Commented for CR ESPRD00865082 --%>
	<%--<m:div rendered="#{logCaseDataBean.showProviderAppTypeScreen}"
		id="casedetailDiv18Id">
		<f:subview id="caseTypeProvApp">
			<jsp:include page="inc_caseTypeProviderAppeal.jsp" />
		</f:subview>
	</m:div>--%>
	<%--end --%>


	<m:anchor name="additionalCaseEntitiesFocusPage"></m:anchor>
	<t:htmlTag id="tag" value="h3" styleClass="warning">
		<h:outputText styleClass="outputLabel" id="textTwentyNine"
			value="#{msg['label.case.caseDetails.additionalCaseEntities']}" />
	</t:htmlTag>



	<t:dataTable value="#{logCaseDataBean.caseDetailsVO.inqAbtEntityList}"
		rendered="#{logCaseDataBean.showAddiCaseEntitesDataTable}"
		first="#{logCaseDataBean.addiCaseEntityRowIndex}"
		columnClasses="columnClass" headerClass="headerClass"
		footerClass="footerClass" var="additionalCaseEntitesResult"
		styleClass="dataTable" cellspacing="0" width="100%" border="0"
		headerClass="tableHead" rowClasses="rowone,row_alt" rows="10"
		id="aceSpantable"
		rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
		rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
		rowOnClick="javascript:flagWarn=false;childNodes[0].lastChild.click();"
		onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');"
		rowIndexVar="rowID">

		<t:column id="acecolumn1">
			<f:facet name="header">
				<h:panelGrid columns="2" id="aceSpantablePGrid1Id">
					<h:outputLabel id="textThirty"
						value="#{msg['label.case.caseDetails.entityID']}"
						for="aceIDCommandLink1" />
					<h:panelGroup id="T1">
						<t:commandLink id="aceIDCommandLink1" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortAdditionalCaseEntites}"
							onmousedown="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');"
							rendered="#{logCaseDataBean.imageRender != 'aceIDCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811282" alt="#{ent['label.ent.forAscending']}" 
								title="#{ent['label.ent.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="entityID" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						<m:br />
						<t:commandLink id="aceIDCommandLink2" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortAdditionalCaseEntites}"
							onmousedown="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');"
							rendered="#{logCaseDataBean.imageRender != 'aceIDCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811283" alt="#{ent['label.ent.forDescending']}"
								title="#{ent['label.ent.forDescending']}" styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="entityID" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<t:commandLink
				action="#{logCaseControllerBean.getInquiryAboutDetails}"
				onmousedown="javascript:flagWarn=false;"
				id="EDITINQUIRYSECTION">
				<f:param name="rowindex" value="#{rowID}" />
				<h:outputText id="textThirtyOne"
					value="#{additionalCaseEntitesResult.entityId}" />
				<%-- <h:outputText value="#{routingResult.routingDateStr}"
					id="routingTableOutTxtId1" />--%>
			</t:commandLink>

		</t:column>
		<t:column id="acecolumn2">
			<f:facet name="header">
				<h:panelGrid columns="2" id="aceSpantablePGrid2Id">
					<h:outputLabel id="PRGCMGTOLL274"
						value="#{msg['label.case.caseDetails.name']}"
						for="aceNameCommandLink1" />
					<h:panelGroup id="t2">
						<t:commandLink id="aceNameCommandLink1" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortAdditionalCaseEntites}"
							onmousedown="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');"
							rendered="#{logCaseDataBean.imageRender != 'aceNameCommandLink1'}">
							<h:graphicImage alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" id="aceSpantableImg3Id" width="8"
								url="/images/x.gif" />
							<f:attribute name="columnName" value="Name" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						<m:br />
						<t:commandLink id="aceNameCommandLink2" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortAdditionalCaseEntites}"
							onmousedown="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');"
							rendered="#{logCaseDataBean.imageRender != 'aceNameCommandLink2'}">
							<h:graphicImage alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" id="aceSpantableImg4Id" width="8"
								url="/images/x.gif" />
							<f:attribute name="columnName" value="Name" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<t:commandLink id="linkOne" onmousedown="javascript:flagWarn=false;"
				action="#{logCaseControllerBean.getInquiryAboutDetails}">
				<f:param name="rowindex" value="#{rowID}" />
				<h:outputText id="textThirtyTwo"
					value="#{additionalCaseEntitesResult.name}" />
			</t:commandLink>
		</t:column>

		<t:column id="acecolumn3">
			<f:facet name="header">
				<h:panelGrid columns="2" id="aceSpantablePGrid3Id">
					<h:outputLabel id="PRGCMGTOLL275"
						value="#{msg['label.case.caseDetails.entityType']}"
						for="aceTypeCommandLink1" />
					<h:panelGroup id="t3">
						<t:commandLink id="aceTypeCommandLink1" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortAdditionalCaseEntites}"
							onmousedown="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');"
							rendered="#{logCaseDataBean.imageRender != 'aceTypeCommandLink1'}">
							<h:graphicImage alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" id="aceSpantableImg5Id" width="8"
								url="/images/x.gif" />
							<f:attribute name="columnName" value="EntityType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						<m:br />
						<t:commandLink id="aceTypeCommandLink2" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortAdditionalCaseEntites}"
							onmousedown="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');"
							rendered="#{logCaseDataBean.imageRender != 'aceTypeCommandLink2'}">
							<h:graphicImage alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" id="aceSpantableImg6Id" width="8"
								url="/images/x.gif" />
							<f:attribute name="columnName" value="EntityType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText value="#{additionalCaseEntitesResult.entityTypeDesc}"
				id="textThirtyThree" />
		</t:column>
	</t:dataTable>


	<t:dataScroller for="aceSpantable" paginator="true"
		onclick="javascript:flagWarn=false;focusPage('additionalCaseEntitiesFocusPage');"
		rendered="#{logCaseDataBean.showAddiCaseEntitesDataTable}"
		paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
		immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="dataScroller" id="scroller">
		<f:facet name="previous">
			<h:outputText id="CMGTOT16" styleClass="underline"
				value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"id="textThirtyFour"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText styleClass="underline"
				value=" #{msg['label.greaterthan']} "
				rendered="#{pageIndex < pageCount}" id="textThirtyFive"></h:outputText>
		</f:facet>
		<h:outputText id="CMGTOT17" rendered="#{rowsCount > 0}"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="dataScrollerText" id="textThirtySix" />
	</t:dataScroller>
	<h:dataTable var="dummyace"
		rendered="#{!logCaseDataBean.showAddiCaseEntitesDataTable}"
		styleClass="dataTable" cellspacing="0" width="100%" border="0"
		headerClass="tableHead" rowClasses="rowone,row_alt" id="acedummyTable">
		<t:column id="dummyacecolumn1">
			<f:facet name="header">
				<h:outputText value="#{msg['label.case.caseDetails.entityID']}"
					id="textThirtySeven" />
			</f:facet>
		</t:column>
		<t:column id="dummyacecolumn2">
			<f:facet name="header">
				<h:outputText value="#{msg['label.case.caseDetails.name']}"
					id="textThirtyEight" />
			</f:facet>
		</t:column>
		<t:column id="dummyacecolumn3">
			<f:facet name="header">
				<h:outputText value="#{msg['label.case.caseDetails.entityType']}"
					id="textThirtyNine" />
			</f:facet>
		</t:column>
	</h:dataTable>

	<m:table id="PROVIDERMMT20120731164811284" styleClass="dataTable" width="100%" border="0"
		rendered="#{!logCaseDataBean.showAddiCaseEntitesDataTable}">
		<m:tr>
			<m:td align="center">
				<h:outputText id="textThirtseven" value="#{msg['value.noData']}" />
			</m:td>
		</m:tr>
	</m:table>
	<m:br /><m:br /><m:br />
	<m:div styleClass="clearl">
	</m:div>
	<m:div rendered="#{logCaseDataBean.showInqAbtMember}">
		<f:subview id="inqAbrMember">
			<jsp:include page="inc_inqAbtMember.jsp" />
		</f:subview>
	</m:div>
	<m:div rendered="#{logCaseDataBean.showInqAbtProvider}">
		<f:subview id="inqAbrProvider">
			<jsp:include page="inc_inqAbtProvider.jsp" />
		</f:subview>
	</m:div>
	<m:div rendered="#{logCaseDataBean.showInqAbtFor}">
		<f:subview id="inqAbrFor">
			<jsp:include page="inc_inqAbtFor.jsp" />
		</f:subview>
	</m:div>
	<%--</h:panelGroup>--%>
	<%--<hx:ajaxRefreshRequest id="caseDetailRefreshRequest" target="caseDetailGroup" params="casetype"></hx:ajaxRefreshRequest>--%>
	<%-- <hx:ajaxRefreshSubmit id="caseDetailRefreshRequest" target="caseDetailGroup" ></hx:ajaxRefreshSubmit>--%>
</m:section>
