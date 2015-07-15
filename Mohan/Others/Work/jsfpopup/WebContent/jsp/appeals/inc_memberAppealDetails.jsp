<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<portlet:defineObjects />




<m:div styleClass="moreInfoContent"
	rendered="#{appealDataBean.showMemberInfoLabelFlag}">

	<m:legend>
		<t:htmlTag value="h4">
			<h:outputText id="memInf"
				value="#{msg['label.appeals.provappeals.memberinformation']}" />
		</t:htmlTag>
	</m:legend>

	<m:section id="PROVIDERMMS2012073116481142" styleClass="otherbg">
		<m:table id="PROVIDERMMT2012073116481143" cellspacing="0" width="100%">
			<m:tr>
				<m:td>
					<m:div>
						<h:outputLabel id="PRGCMGTOLL60" for="addAppeals_MemName"
							value="#{msg['label.appeals.provappeals.membername']}">
						</h:outputLabel>
						<h:outputText id="addAppeals_MemName"
							value="#{appealDataBean.memberName}"></h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div>
						<h:outputLabel id="PRGCMGTOLL61" for="addAppeals_MemID"
							value="#{msg['label.appeals.memappeals.medicaidid']}">
						</h:outputLabel>
						<h:outputText id="addAppeals_MemID"
							value="#{appealDataBean.memberID}"></h:outputText>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
	</m:section>
</m:div>

<m:div styleClass="moreInfoContent"
	rendered="#{appealDataBean.showProviderInfoLabelFlag}">
	<m:legend>
		<t:htmlTag value="h4">
			<h:outputText id="provInf"
				value="#{msg['label.appeals.provappeals.providerinformation']}" />
		</t:htmlTag>
	</m:legend>

	<m:section id="PROVIDERMMS2012073116481144" styleClass="otherbg">
		<m:table id="PROVIDERMMT2012073116481145" cellspacing="0" width="100%">
			<m:tr>
				<m:td>
					<m:div>
						<h:outputLabel id="PRGCMGTOLL62" for="addAppeals_ProvName"
							value="#{msg['label.appeals.provappeals.providername']}">
						</h:outputLabel>
						<h:outputText id="addAppeals_ProvName" value="">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div>
						<h:outputLabel id="PRGCMGTOLL63" for="addAppeals_ProvID"
							value="#{msg['label.appeals.provappeals.providerid']}">
						</h:outputLabel>
						<h:outputText id="addAppeals_ProvID" value="">
						</h:outputText>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
	</m:section>
</m:div>

<m:div>
	<m:br />
	<m:br />
</m:div>
<m:table id="TabelIDforMemberAppealAlignmnt" width="100%">
<m:tr>
<m:td width="18%">
	<m:section id="PROVIDERMMS2012073116481146" styleClass="otherbg">

		<m:pod styleClass="podbg"
			title="#{msg['label.appeals.memappeals.memberdetaillinks']}">
			<m:br></m:br>
			<h:outputText id="PRGCMGTOT76"
				value="#{msg['label.appeals.memappeals.memberid']}"></h:outputText>
			<m:br></m:br>
			<h:outputText id="PRGCMGTOT77" styleClass="strong"
				value="#{appealDataBean.memberID}"></h:outputText>
			<m:br></m:br>
			<m:br></m:br>
			<h:outputText id="PRGCMGTOT78"
				value="#{msg['label.appeals.memappeals.name']}"></h:outputText>
			<m:br></m:br>
			<h:outputText id="PRGCMGTOT79" styleClass="strong"
				value="#{appealDataBean.memberName}" />
			<m:br></m:br>
			<m:br></m:br>
			<t:commandLink id="PRGCMGTCL17"
				action="#{appealControllerBean.submitMemberSummary}"
				onmousedown="javascript:flagWarn=false;">
				<h:outputText id="PRGCMGTOT80"
					value="#{msg['label.appeals.memappeals.membersummary']}"></h:outputText>
				<f:param name="send.member.summary.action"
					value="sendMemberSysIDAction"></f:param>
				<f:param name="systemID"
					value="#{appealDataBean.commonMemberDetailsVO.memberSysID}"></f:param>
			</t:commandLink>


			<m:br></m:br>
			<h:outputText id="PRGCMGTOT81"
				value="#{msg['label.appeals.memappeals.eligibilityquickview']}"></h:outputText>

			<m:br></m:br>

			<h:outputText id="PRGCMGTOT82"
				value="#{msg['label.appeals.memappeals.benefitservicelimits']}"></h:outputText>
			<m:br></m:br>
			<h:outputText id="PRGCMGTOT83"
				value="#{msg['label.appeals.memappeals.additionalinfo']}"></h:outputText>
			<m:br></m:br>
			<h:outputText id="PRGCMGTOT84"
				value="#{msg['label.appeals.memappeals.caseinfo']}"></h:outputText>
			<m:br></m:br>
			<h:outputText id="PRGCMGTOT85"
				value="#{msg['label.appeals.memappeals.unconcatenatedeligibilty']}"></h:outputText>

			<m:br></m:br>
			<h:outputText id="PRGCMGTOT86"
				value="#{msg['label.appeals.memappeals.epsdt']}"></h:outputText>
			<m:br></m:br>
			<h:outputText id="PRGCMGTOT87"
				value="#{msg['label.appeals.memappeals.absentparent']}"></h:outputText>
			<m:br></m:br>
			<h:outputText id="PRGCMGTOT88"
				value="#{msg['label.appeals.memappeals.requestnewcard']}"></h:outputText>
			<m:br></m:br>

		</m:pod>
	</m:section>
	<m:br></m:br>

	<m:section id="PROVIDERMMS2012073116481147" styleClass="otherbg">
		<m:pod styleClass="podbg" title="#{msg['label.appeals.quicklinks']}">
			<%-- <h:outputLink id="PRGCMGTOLK6"
				value="/wps/myportal/ProjectControlSearchDocumentRepository"
				onmousedown="javascript:flagWarn=false;">
				<h:outputText id="link1" value="#{msg['link.appeals.edms']}"></h:outputText>
			</h:outputLink>--%>
			<m:div id="edmsQuickLinkID" rendered="#{appealDataBean.renderEDMSQuickLinks}">
			<f:verbatim> <a href="/wps/myportal/ProjectControlSearchDocumentRepository" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK6" ></f:verbatim>
			<h:outputText id="link1" value="#{msg['link.appeals.edms']}"></h:outputText>
			<f:verbatim></a></f:verbatim>
			<m:br></m:br>
			</m:div>
			<m:div id="corrGenQuickLinkID" rendered="#{appealDataBean.renderCorresGenQuickLinks}">
			<t:commandLink id="PRGCMGTCL18"
				action="#{appealControllerBean.sendCrspdGenAppeal}"
				onmousedown="javascript:flagWarn=false;">
				<h:outputText id="link2"
					value="#{msg['link.appeals.corrgeneration']}"></h:outputText>
				<f:param name="send.member.summary.action"
					value="sendMemberSysIDAction"></f:param>
			</t:commandLink>
			<m:br></m:br>
			</m:div>
			<%-- <h:outputLink id="PRGCMGTOLK7"
				value="/wps/myportal/ServiceAuthorization"
				onmousedown="javascript:flagWarn=false;">
				<h:outputText id="link3" value="#{msg['link.appeals.serviceauth']}"></h:outputText>
			</h:outputLink>--%>
			<m:div id="SAQuickLinkID" rendered="#{appealDataBean.renderSAQuickLinks}">
			<f:verbatim> <a href="/wps/myportal/ServiceAuthorization" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK7" ></f:verbatim>
			<h:outputText id="link3" value="#{msg['link.appeals.serviceauth']}"></h:outputText>
			<f:verbatim></a></f:verbatim>
			
			<m:br></m:br>
			</m:div>
			<h:outputText id="PRGCMGTOT89" styleClass="strong"
				value="#{msg['link.appeals.appeals']}"></h:outputText>
			<m:br></m:br>
			<m:div id="caseTrackingQuickLinkID" rendered="#{appealDataBean.renderCaseTrackingQuickLinks}">
			<t:commandLink id="PRGCMGTCL19"
				action="#{appealControllerBean.sendCaseTrackingAppeal}"
				onmousedown="javascript:flagWarn=false;">
				<h:outputText id="link4" value="#{msg['link.appeals.casetracking']}"></h:outputText>
				<f:param name="send.member.summary.action"
					value="sendMemberSysIDAction"></f:param>
			</t:commandLink>
			<m:br></m:br>
			</m:div>
			<%-- <h:outputLink id="PRGCMGTOLK8"
				value="/wps/myportal/ClaimAdminInquiry"
				onmousedown="javascript:flagWarn=false;">
				<h:outputText id="link5" value="#{msg['link.appeals.claiminquiry']}"></h:outputText>
			</h:outputLink>--%>
			<m:div id="claimInquiryQuickLinkID" rendered="#{appealDataBean.renderClaimsInquiryQuickLinks}">
			<f:verbatim> <a href="/wps/myportal/ClaimAdminInquiry" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK8" ></f:verbatim>
			<h:outputText id="link5" value="#{msg['link.appeals.claiminquiry']}"></h:outputText>
			<f:verbatim></a></f:verbatim>
			
			<m:br></m:br>
			</m:div>
		<%-- 	<h:outputLink id="PRGCMGTOLK9" value="/wps/myportal/PlanNavigator"
				onmousedown="javascript:flagWarn=false;">
				<h:outputText id="link6" value="#{msg['link.appeals.benefitplan']}"></h:outputText>
			</h:outputLink>--%>
			<m:div id="benifitPlanQuickLinkID" rendered="#{appealDataBean.renderBenefitPlanQuickLinks}">
			<f:verbatim> <a href="/wps/myportal/PlanNavigator" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK9" ></f:verbatim>
			<h:outputText id="link6" value="#{msg['link.appeals.benefitplan']}"></h:outputText>
			<f:verbatim></a></f:verbatim>
			<m:br></m:br>
			</m:div>
			<%-- <h:outputLink id="PRGCMGTOLK10"
				value="/wps/myportal/FinancialFinancialAccountingEntity"
				onmousedown="javascript:flagWarn=false;">
				<h:outputText id="link7"
					value="#{msg['link.appeals.financialentity']}"></h:outputText>
			</h:outputLink>--%>
			<m:div id="financialEntityQuickLinkID" rendered="#{appealDataBean.renderFinancialEntityQuickLinks}">
			<f:verbatim> <a href="/wps/myportal/FinancialFinancialAccountingEntity" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK10" ></f:verbatim>
			<h:outputText id="link7"
					value="#{msg['link.appeals.financialentity']}"></h:outputText>
			<f:verbatim></a></f:verbatim>
			

			<m:br></m:br>
			</m:div>
		</m:pod>
	</m:section>
</m:td>
<m:td width="1%">
	<m:div>&nbsp;</m:div>
</m:td>
<m:td width="81%">

	<%--<m:div rendered="#{!appealDataBean.showFinalSuccessMsgFlag}">
		<h:messages layout="table" showDetail="true" styleClass="errorMessage"
			id="errorMessage" showSummary="false" style="color: red"></h:messages>
	</m:div> for ESPRD00696684 --%>
	<m:br />
	<m:br />
	<m:section id="PROVIDERMMS2012073116481148" styleClass="otherbg">
		<m:legend>

			<h:outputText id="PRGCMGTOT90"
				value="#{msg['label.appeals.appealdetails']}" />

		</m:legend>

		<m:table id="PROVIDERMMT2012073116481149" cellspacing="0" width="100%">
			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT91"
								value="#{msg['label.appeals.astrik']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL64" for="appealDetails_CRNumber"
							value="#{msg['label.appeals.caserecordnumber']}">
						</h:outputLabel>
						<m:br />
						<h:inputText id="appealDetails_CRNumber" size="10"
							value="#{appealDataBean.appealVO.caseSK}"
							disabled="#{appealDataBean.disableCase}" />
						&nbsp;
						<hx:requestLink id="requestlinkID" value="Case Record Details"
							onclick="javascript:flagWarn=false;"
							action="#{appealControllerBean.submitDetailsForRightClick }">
							<f:param name="linkID" value="PRGCMGTCL20"></f:param>	
						</hx:requestLink>
						<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
						<%--<h:commandLink id="PRGCMGTCL20" styleClass="hide"
							action="#{logCaseControllerBean.submitCaseDetails}"
							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="PRGCMGTOT92" value="Case Record Details"></h:outputText>
							<f:param id="ipcAction"
								name="com.ibm.portal.propertybroker.action"
								value="sendCaseDetailsAction" />
							<f:param name="caseID" value="#{appealDataBean.appealVO.caseSK}"></f:param>
							<f:param name="entityID" value="#{appealDataBean.memberID}"></f:param>
							<f:param name="entityType" value="#{appealDataBean.entityType}"></f:param>
						</h:commandLink>--%>
						<%-- Code changes for the defect id ESPRD00343176 --%>
					</m:div>
				</m:td>

				<m:td>
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT93"
								value="#{msg['label.appeals.astrik']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL65" for="appealDetails_Type"
							value="#{msg['label.appeals.type']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_Type"
							value="#{appealDataBean.appealVO.caseAplTyCd}"
							valueChangeListener="#{appealControllerBean.showComponent}"
							onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();javascript:flagWarn=false;">
							<f:selectItems value="#{appealDataBean.typeList}" />
						</h:selectOneMenu>
						<m:br />
						<h:message id="PRGCMGTM35" for="appealDetails_Type"
							styleClass="errorMessage" />
					</m:div>
				</m:td>

				<m:td colspan="2">
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT94"
								value="#{msg['label.appeals.astrik']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL66" for="appealDetails_AppType"
							value="#{msg['label.appeals.appealtype']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_AppType"
							value="#{appealDataBean.appealVO.aplTyCd}"
							valueChangeListener="#{appealControllerBean.showAppealContReason}"
							onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();javascript:flagWarn=false;">

							<f:selectItems value="#{appealDataBean.appealTypeList}" />

						</h:selectOneMenu>
						<m:br />
						<h:message id="PRGCMGTM36" for="appealDetails_AppType"
							styleClass="errorMessage" />
					</m:div>
				</m:td>
			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL67" for="appealDetails_RName"
							value="#{msg['label.appeal.reviewername']}">
						</h:outputLabel>
						<m:br />
						<h:inputText id="appealDetails_RName"
							onkeyup="this.value=this.value.toUpperCase();"
							value="#{appealDataBean.appealVO.revwrNam}" />
						<m:br />
						<h:message id="PRGCMGTM37" for="appealDetails_RName"
							styleClass="errorMessage" />
					</m:div>
				</m:td>

				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL68" for="appealDetails_AsgnDate"
							value="#{msg['label.appeals.assigneddate']}">
						</h:outputLabel>
						<m:br />
						<m:inputCalendar monthYearRowClass="yearMonthHeader"
							weekRowClass="weekHeader" id="appealDetails_AsgnDate"
							currentDayCellClass="currentDayCell" size="10"
							renderAsPopup="true" addResources="true"
							renderPopupButtonAsImage="true"
							value="#{appealDataBean.appealVO.asgnDt}"
							popupDateFormat="MM/dd/yyyy">
						</m:inputCalendar>
						<m:br />
					</m:div>
				</m:td>

				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL69" for="appealDetails_PANumber"
							value="#{msg['label.appeals.previousappealnumber']}">
						</h:outputLabel>
						<m:br />
						<h:inputText id="appealDetails_PANumber" size="10"
							value="#{appealDataBean.appealVO.prevAplNum}" disabled="#{true}" />
						<m:br />
						<h:message id="PRGCMGTM38" for="appealDetails_PANumber"
							styleClass="errorMessage" />
					</m:div>

				</m:td>

				<m:td>
					<m:div styleClass="padb"
						rendered="#{appealDataBean.showContReasonFlag}">
						<h:outputLabel id="PRGCMGTOLL70" for="appealDetails_contRsnCd"
							value="#{msg['label.appeals.continuanceReason']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_contRsnCd"
							value="#{appealDataBean.appealVO.caseAplContinuanceRsnCd}"
							onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();javascript:flagWarn=false;">

							<f:selectItems value="#{appealDataBean.appealContReasonList}" />

						</h:selectOneMenu>
						<m:br />
					</m:div>
				</m:td>

			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL71" for="appealDetails_AplDate"
							value="#{msg['label.appeals.appealdate']}">
						</h:outputLabel>
						<m:br />
						<m:inputCalendar monthYearRowClass="yearMonthHeader"
							weekRowClass="weekHeader" id="appealDetails_AplDate"
							currentDayCellClass="currentDayCell" size="10"
							renderAsPopup="true" addResources="true"
							renderPopupButtonAsImage="true"
							value="#{appealDataBean.appealVO.aplDt}"
							popupDateFormat="MM/dd/yyyy">
						</m:inputCalendar>
						<m:br />
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL72" for="appealDetails_AplResults"
							value="#{msg['label.appeals.appealresults']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_AplResults"
							value="#{appealDataBean.appealVO.caseAplRsltsCd}"
							onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();javascript:flagWarn=false;">
							<f:selectItems value="#{appealDataBean.appealResultsList}" />

						</h:selectOneMenu>
					</m:div>
				</m:td>

				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL73" for="appealDetails_AplStatus"
							value="#{msg['label.appeals.appealstatus']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_AplStatus"
							value="#{appealDataBean.appealVO.caseAplStatCd}"
							onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();javascript:flagWarn=false;">
							<f:selectItems value="#{appealDataBean.appealStatusList}" />
						</h:selectOneMenu>
					</m:div>
				</m:td>
			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL74" for="appealDetails_AplStUpdtDate"
							value="#{msg['label.appeals.appealstatusupdatedate']}">
						</h:outputLabel>
						<m:br />
						<m:inputCalendar monthYearRowClass="yearMonthHeader"
							weekRowClass="weekHeader" id="appealDetails_AplStUpdtDate"
							currentDayCellClass="currentDayCell" size="10"
							renderAsPopup="true" addResources="true"
							renderPopupButtonAsImage="true"
							value="#{appealDataBean.appealVO.caseAplStatCdDt}"
							popupDateFormat="MM/dd/yyyy">
						</m:inputCalendar>
						<m:br />
					</m:div>
				</m:td>

				<m:td colspan="2">
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL75" for="appealDetails_DstOffice"
							value="#{msg['label.appeals.districtoffice']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_DstOffice"
							value="#{appealDataBean.appealVO.caseAplDstctOfcCd}"
							onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();javascript:flagWarn=false;">
							<f:selectItems value="#{appealDataBean.distOfficeList}" />
						</h:selectOneMenu>
					</m:div>
				</m:td>
				<m:td rendered="#{appealDataBean.showClaimCompFlag}">
					<m:div id="appealDetails_CNumber3" styleClass="padb">

						<%--<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT95" value="#{msg['label.appeals.astrik']}" />
						</m:span>--%>

						<h:outputLabel id="PRGCMGTOLL76" for="appealDetails_CNumber"
							value="#{msg['label.appeals.claimnumber']}">
						</h:outputLabel>
						<m:br />
						<h:inputText id="appealDetails_CNumber" size="10"
							value="#{appealDataBean.appealVO.tcnNum}" onblur="javascript:flagWarn=false;TCNActionblur();"/>
						<%--Modified for defect ESPRD00529011  starts--%>
						<%--<h:commandLink id="PRGCMGTCL21" action="#{appealControllerBean.searchById}"							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="ssss" value="Claims Details"></h:outputText>
							<f:param name="com.ibm.portal.propertybroker.action"
								value="sendInternalServiceAuthIDAction"></f:param>
						</h:commandLink>--%>
						<hx:requestLink id="requestlinkIDMEMClaim" value="Claims Details"
							onclick="javascript:flagWarn=false;"
							action="#{appealControllerBean.submitDetailsForRightClick}">
							<f:param name="linkID" value="PRGCMGTCL22"></f:param>
						</hx:requestLink>
						<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
						<%--<h:commandLink id="PRGCMGTCL22" styleClass="hide"
							action="#{appealControllerBean.searchClaimInquiryByTCN}"
							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="ssss" value="Claims Details"></h:outputText>
							<f:param name="action.param.send.ClaimsInternalInquiryAction"
								value="send.ClaimsInternalInquiryAction"></f:param>
						</h:commandLink>--%>
						<%--Modified for defect ESPRD00529011  ends--%>
						<m:br />
						<h:message id="PRGCMGTM39" for="appealDetails_CNumber"
							styleClass="errorMessage" />
						<m:div>
							<h:commandButton id="tcnCmdbuttonlinkMEMID" styleClass="hide"
								action="#{appealControllerBean.sumbitTCNDetails}" value="#{msg['label.appeal.hiddenButtonToClick']}"
								onmousedown="javascript:flagWarn=false;"></h:commandButton>
						</m:div>
					</m:div>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td rendered="#{appealDataBean.showSACompFlag}">
					<m:div id="appealDetails_SANumber1" styleClass="padb">

						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT96"
								value="#{msg['label.appeals.astrik']}" />
						</m:span>

						<h:outputLabel id="PRGCMGTOLL77" for="appealDetails_SANumber"
							value="#{msg['label.appeals.sanumber']}">
						</h:outputLabel>
						<m:br />



						<h:inputText id="appealDetails_SANumber" size="10"
							value="#{appealDataBean.appealVO.authID}"
							onkeyup="this.value=this.value.toUpperCase();"
							onblur="javascript:flagWarn=false;saAction(event)">
						</h:inputText>

						<%-- Modified for ESPRD00531190 starts--%>
						<%--<h:commandLink id="PRGCMGTCL23"							action="#{appealControllerBean.submitSADetailsForIPC}"							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="ccc" value="SA Details"></h:outputText>
							<f:param name="com.ibm.portal.propertybroker.action"
								value="sendInternalServiceAuthIDAction"></f:param>
						</h:commandLink>--%>
						<hx:requestLink id="requestlinkIDMEMSA" value="SA Details"
							onclick="javascript:flagWarn=false;"
							action="#{appealControllerBean.submitDetailsForRightClick}">
							<f:param name="linkID" value="PRGCMGTCL24"></f:param>	
						</hx:requestLink>
						<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
						<%--<h:commandLink id="PRGCMGTCL24" styleClass="hide"
							action="#{appealControllerBean.submitSADetailsForIPC}"
							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="ddd" value="SA Details"></h:outputText>
							<f:param name="send.insti.claimCorr.info"
								value="sendInstiClaimCorrInfo" />
						</h:commandLink>--%>
						<%-- Modified for ESPRD00531190 ends--%>
						<m:br />
						<h:message id="PRGCMGTM40" for="appealDetails_SANumber"
							styleClass="errorMessage" />
					</m:div>
				</m:td>
				<m:td>
					<h:commandButton id="submitMemberSADetails"  styleClass="hide"
						action="#{appealControllerBean.submitSADetails}" value="#{msg['label.appeal.hiddenButtonToClick']}"
						onmousedown="javascript:flagWarn=false;" />
				</m:td>
			</m:tr>
			<m:div styleClass="clear">
			</m:div>
			<m:div>
				<m:br />
				<m:br />
			</m:div>
		</m:table>
	</m:section>

	<m:div id="saLineitems" rendered="#{appealDataBean.showSACompFlag}">
		<jsp:include page="/jsp/appeals/inc_saLineItems.jsp" />
	</m:div>


	<m:div id="additionalInformation">
		<jsp:include page="/jsp/appeals/inc_appealsAdditionalInformation.jsp" />
	</m:div>

	<m:div id="reconsideration">
		<jsp:include page="/jsp/appeals/inc_appealsReconsideration.jsp" />
	</m:div>

	<m:div id="administrativeHearing">
		<f:subview id="administrativeHearingSubViewId2">
			<jsp:include page="/jsp/appeals/inc_appealsAdministrativeHearing.jsp" />
		</f:subview>
	</m:div>

	<m:div id="informalReview">
		<jsp:include page="/jsp/appeals/inc_appealsInformalReview.jsp" />
	</m:div>

	<m:div id="DHHSFormalReview">
		<jsp:include page="/jsp/appeals/inc_appealsDHHSFormalReview.jsp" />
	</m:div>

	<m:div>
		<m:br />
		<m:br />
	</m:div>

	<%--ESPRD00509203_ProviderAppeals_03AUG2010
	<m:div rendered="#{appealDataBean.memberAppealDataFlag}">
		<f:subview id="auditlogDetails">
		<% - -	<jsp:include page="/jsp/appeals/inc_appealDetailsAuditLog.jsp" />- - %>
								<audit:auditTableSet id="memberAppealAuditId"	value="#{appealDataBean.appealVO.auditKeyList}"	numOfRecPerPage="10">
								</audit:auditTableSet>  - - %>
		</f:subview>
	</m:div>
EOF  ESPRD00509203_ProviderAppeals_03AUG2010--%>
	<m:div>
		<m:br />
		<m:br />
	</m:div>
</m:td>
</m:tr>
</m:table>
