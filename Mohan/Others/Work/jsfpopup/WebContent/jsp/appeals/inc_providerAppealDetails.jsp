<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/jsp/appeals/Inc_providerAppealDetails.java" --%><%-- /jsf:pagecode --%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<portlet:defineObjects />

<script type="text/javascript"
	src="<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/ACSExtensionResource/calendar.ACSInputCalendar/11302665/popcalendar_init.js") %>"></script>
<script type="text/javascript"
	src="<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/ACSExtensionResource/calendar.ACSInputCalendar/11302665/popcalendar.js") %>"></script>




<m:div styleClass="moreInfoContent"
	rendered="#{appealDataBean.showMemberInfoLabelFlag}">
	<m:legend>
		<t:htmlTag value="h4">
			<h:outputText id="memInf"
				value="#{msg['label.appeals.provappeals.memberinformation']}" />
		</t:htmlTag>
	</m:legend>

	<m:section id="PROVIDERMMS2012073116481150" styleClass="otherbg">
		<m:table id="PROVIDERMMT2012073116481151" cellspacing="0" width="100%">
			<m:tr>
				<m:td>
					<m:div>
						<h:outputLabel id="PRGCMGTOLL78" for="addAppeals_MemName"
							value="#{msg['label.appeals.provappeals.membername']}">
						</h:outputLabel>
						<h:outputText id="addAppeals_MemName" value="">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div>
						<h:outputLabel id="PRGCMGTOLL79" for="addAppeals_MemID"
							value="#{msg['label.appeals.provappeals.memberid']}">
						</h:outputLabel>
						<h:outputText id="addAppeals_MemID" value="">
						</h:outputText>
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

	<m:section id="PROVIDERMMS2012073116481152" styleClass="otherbg">
		<m:table id="PROVIDERMMT2012073116481153" cellspacing="0" width="100%">
			<m:tr>
				<m:td>
					<m:div>
						<h:outputLabel id="PRGCMGTOLL80" for="addAppeals_ProvName"
							value="#{msg['label.appeals.provappeals.providername']}">
						</h:outputLabel>
						<h:outputText id="addAppeals_ProvName" value="">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div>
						<h:outputLabel id="PRGCMGTOLL81" for="addAppeals_ProvID"
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

<m:div>
	<m:br />
	<m:br />
</m:div>

<m:div>

	<%--<m:div rendered="#{!appealDataBean.showFinalSuccessMsgFlag}">
		<h:messages layout="table" showDetail="true" styleClass="errorMessage"
			id="errorMessage" showSummary="false" style="color: red"></h:messages>
	</m:div> for ESPRD00696684 --%>

	<m:section id="PROVIDERMMS2012073116481154" styleClass="otherbg">
		<m:legend>

			<h:outputText id="PRGCMGTOT97"
				value="#{msg['label.appeals.appealdetails']}" />

		</m:legend>

		<m:table id="PROVIDERMMT2012073116481155" cellspacing="0" width="100%">
			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT98"
								value="#{msg['label.appeals.astrik']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL82" for="appealDetails_CRNumber"
							value="#{msg['label.appeals.caserecordnumber']}">
						</h:outputLabel>
						<m:br />
						<h:inputText id="appealDetails_CRNumber" size="10"
							value="#{appealDataBean.appealVO.caseSK}"
							disabled="#{appealDataBean.disableCase}" />
							&nbsp;
 				<%-- Added for the defect id ESPRD00343176 --%>
						<hx:requestLink id="requestlinkIDPRVCR" value="Case Record Details"
							onclick="javascript:flagWarn=false;"
							action="#{appealControllerBean.submitDetailsForRightClick }">
							<f:param name="linkID" value="PRGCMGTCL25"></f:param>	
						</hx:requestLink>
						<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
						<%--<h:commandLink id="PRGCMGTCL25" styleClass="hide"
							action="#{logCaseControllerBean.submitCaseDetails}"
							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="PRGCMGTOT99" value="Case Record Details"></h:outputText>
							<f:param id="ipcAction"
								name="com.ibm.portal.propertybroker.action"
								value="sendCaseDetailsAction" />
							<f:param name="caseID" value="#{appealDataBean.appealVO.caseSK}"></f:param>
							<f:param name="entityID" value="#{appealDataBean.providerID}"></f:param>
							<f:param name="entityType" value="#{appealDataBean.entityType}"></f:param>
						</h:commandLink>--%>
						<%-- Added for the defect id ESPRD00343176 Ends --%>
					</m:div>
				</m:td>

				<m:td>
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT100"
								value="#{msg['label.appeals.astrik']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL83" for="appealDetails_Type"
							value="#{msg['label.appeals.type']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_Type"
							value="#{appealDataBean.appealVO.caseAplTyCd}"
							valueChangeListener="#{appealControllerBean.showComponent}"
							onchange="javascript:focusThis(this.id);this.form.submit();javascript:flagWarn=false;">
							<f:selectItems value="#{appealDataBean.typeList}" />
						</h:selectOneMenu>
						<m:br />
						<h:message id="PRGCMGTM41" for="appealDetails_Type"
							styleClass="errorMessage" />
					</m:div>
				</m:td>

				<m:td colspan="2">
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT101"
								value="#{msg['label.appeals.astrik']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL84" for="appealDetails_AppType"
							value="#{msg['label.appeals.appealtype']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_AppType"
							value="#{appealDataBean.appealVO.aplTyCd}"
							valueChangeListener="#{appealControllerBean.showAppealContReason}"
							onchange="javascript:focusThis(this.id);this.form.submit();javascript:flagWarn=false;">

							<f:selectItems value="#{appealDataBean.appealTypeList}" />
						</h:selectOneMenu>
						<m:br />
						<h:message id="PRGCMGTM42" for="appealDetails_AppType"
							styleClass="errorMessage" />
					</m:div>
				</m:td>
			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL85" for="appealDetails_RName"
							value="#{msg['label.appeal.reviewername']}">
						</h:outputLabel>
						<m:br />
						<h:inputText id="appealDetails_RName"
							onkeyup="this.value=this.value.toUpperCase();"
							value="#{appealDataBean.appealVO.revwrNam}" />
						<m:br />
						<h:message id="PRGCMGTM43" for="appealDetails_RName"
							styleClass="errorMessage" />
					</m:div>
				</m:td>

				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL86" for="appealDetails_AsgnDate"
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
						<h:outputLabel id="PRGCMGTOLL87" for="appealDetails_PANumber"
							value="#{msg['label.appeals.previousappealnumber']}">
						</h:outputLabel>
						<m:br />
						<h:inputText id="appealDetails_PANumber" size="10"
							value="#{appealDataBean.appealVO.prevAplNum}"
							disabled="#{appealDataBean.disableCase}" />
						<m:br />
						<h:message id="PRGCMGTM44" for="appealDetails_PANumber"
							styleClass="errorMessage" />
					</m:div>

				</m:td>

				<m:td>
					<m:div styleClass="padb"
						rendered="#{appealDataBean.showContReasonFlag}">
						<h:outputLabel id="PRGCMGTOLL88" for="appealDetails_contRsnCd"
							value="#{msg['label.appeals.continuanceReason']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_contRsnCd"
							value="#{appealDataBean.appealVO.caseAplContinuanceRsnCd}"
							onmousedown="javascript:flagWarn=false;">

							<f:selectItems value="#{appealDataBean.appealContReasonList}" />

						</h:selectOneMenu>
						<m:br />
					</m:div>
				</m:td>


			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL89" for="appealDetails_AplDate"
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
						<h:outputLabel id="PRGCMGTOLL90" for="appealDetails_AplResults"
							value="#{msg['label.appeals.appealresults']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_AplResults"
							value="#{appealDataBean.appealVO.caseAplRsltsCd}"
							onmousedown="javascript:flagWarn=false;">
							<f:selectItems value="#{appealDataBean.appealResultsList}" />

						</h:selectOneMenu>
					</m:div>
				</m:td>

				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL91" for="appealDetails_AplStatus"
							value="#{msg['label.appeals.appealstatus']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_AplStatus"
							value="#{appealDataBean.appealVO.caseAplStatCd}"
							onmousedown="javascript:flagWarn=false;">
							<f:selectItems value="#{appealDataBean.appealStatusList}" />
						</h:selectOneMenu>
					</m:div>
				</m:td>
			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL92" for="appealDetails_AplStUpdtDate"
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
						<h:outputLabel id="PRGCMGTOLL93" for="appealDetails_DstOffice"
							value="#{msg['label.appeals.districtoffice']}">
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="appealDetails_DstOffice"
							value="#{appealDataBean.appealVO.caseAplDstctOfcCd}"
							onmousedown="javascript:flagWarn=false;">
							<f:selectItems value="#{appealDataBean.distOfficeList}" />
						</h:selectOneMenu>
					</m:div>
				</m:td>
				<m:tr>
					<m:td rendered="#{appealDataBean.showClaimCompFlag}">
						<m:div id="appealDetails_ClNum1" styleClass="padb">

							<%--<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT102" value="#{msg['label.appeals.astrik']}" />
							</m:span>--%>

							<h:outputLabel id="PRGCMGTOLL94" for="appealDetails_CNumber"
								value="#{msg['label.appeals.claimnumber']}">
							</h:outputLabel>
							<m:br />
							<h:inputText id="appealDetails_CNumber" size="10"
								value="#{appealDataBean.appealVO.tcnNum}" onblur="javascript:flagWarn=false;TCNActionblur();" />
							<%--Modified for defect ESPRD00529011  starts--%>
							<%--<h:commandLink id="PRGCMGTCL26" action="#{appealControllerBean.searchById}">
								<h:outputText id="xxxx" value="Claims Details"></h:outputText>
								<f:param name="com.ibm.portal.propertybroker.action"
									value="sendInternalServiceAuthIDAction"></f:param>
							</h:commandLink>--%>
							<hx:requestLink id="requestlinkIDPRVClaim" value="Claims Details"
							onclick="javascript:flagWarn=false;"
							action="#{appealControllerBean.submitDetailsForRightClick}">
								<f:param name="linkID" value="PRGCMGTCL27"></f:param>	
							</hx:requestLink>
							<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
							<%--<h:commandLink id="PRGCMGTCL27" styleClass="hide"
								action="#{appealControllerBean.searchClaimInquiryByTCN}"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="xxxx" value="Claims Details"></h:outputText>
								<f:param name="action.param.send.ClaimsInternalInquiryAction"
									value="send.ClaimsInternalInquiryAction"></f:param>
							</h:commandLink>--%>
							<%--Modified for defect ESPRD00529011  ends--%>
							<m:br />
							<h:message id="PRGCMGTM45" for="appealDetails_CNumber"
								styleClass="errorMessage" />
						</m:div>
					</m:td>
					<m:td>
						<h:commandButton id="tcnCmdbuttonlinkPRVID" styleClass="hide"
							action="#{appealControllerBean.sumbitTCNDetails}" value="#{msg['label.appeal.hiddenButtonToClick']}"
							onmousedown="javascript:flagWarn=false;"></h:commandButton>
					</m:td>	
				</m:tr>

				<m:tr>
					<m:td rendered="#{appealDataBean.showSACompFlag}">
						<m:div id="appealDetails_SANumber1" styleClass="padb">

							<m:span styleClass="required">
								<h:outputText id="PRGCMGTOT103"
									value="#{msg['label.appeals.astrik']}" />
							</m:span>

							<h:outputLabel id="PRGCMGTOLL95" for="appealDetails_SANumber"
								value="#{msg['label.appeals.sanumber']}">
							</h:outputLabel>
							<m:br />

							<h:inputText id="appealDetails_SANumber" size="10"
								value="#{appealDataBean.appealVO.authID}"
								onkeyup="this.value=this.value.toUpperCase();"
								onblur="javascript:flagWarn=false;saAction(event)">
							</h:inputText>



							<%-- Modified for ESPRD00531190 starts--%>
							<%--<h:commandLink id="PRGCMGTCL28" 								action="#{appealControllerBean.submitSADetailsForIPC}"								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="ccc" value="SA Details"></h:outputText>
								<f:param name="com.ibm.portal.propertybroker.action"
									value="sendInternalServiceAuthIDAction"></f:param>
							</h:commandLink>--%>
							<hx:requestLink id="requestlinkIDPRVSA" value="SA Details"
							onclick="javascript:flagWarn=false;"
							action="#{appealControllerBean.submitDetailsForRightClick }">
								<f:param name="linkID" value="PRGCMGTCL29"></f:param>	
							</hx:requestLink>
							<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
							<%--<h:commandLink id="PRGCMGTCL29" styleClass="hide"
								action="#{appealControllerBean.submitSADetailsForIPC}"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="ccc" value="SA Details"></h:outputText>
								<f:param name="send.insti.claimCorr.info"
									value="sendInstiClaimCorrInfo" />
							</h:commandLink>--%>
							<%-- Modified for ESPRD00531190 ends--%>
							<m:br />
							<h:message id="PRGCMGTM46" for="appealDetails_SANumber"
								styleClass="errorMessage" />
						</m:div>
					</m:td>
					<m:td>
						<h:commandButton id="submitProvSADetails" styleClass="hide"
							action="#{appealControllerBean.submitSADetails}" value="#{msg['label.appeal.hiddenButtonToClick']}"
							onmousedown="javascript:flagWarn=false;" />
					</m:td>
				</m:tr>
				<m:div styleClass="clear">
				</m:div>

				<m:tr>
					<m:div id="saLineitems" styleClass="padb">
						<m:td colspan="3" rendered="#{appealDataBean.showSACompFlag}">
							<jsp:include page="/jsp/appeals/inc_providerAppealSADetails.jsp" />
						</m:td>
					</m:div>
				</m:tr>

			</m:tr>
			<m:div>
				<m:br />
				<m:br />
			</m:div>
		</m:table>
	</m:section>

	<m:div id="additionalInformation">
		<jsp:include page="/jsp/appeals/inc_appealsAdditionalInformation.jsp" />
	</m:div>

	<m:div id="reconsideration">
		<jsp:include page="/jsp/appeals/inc_appealsReconsideration.jsp" />
	</m:div>

	<m:div id="administrativeHearing">
		<jsp:include page="/jsp/appeals/inc_appealsAdministrativeHearing.jsp" />
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
	<m:div rendered="#{appealDataBean.providerAppealDataFlag}">
		<f:subview id="auditlogDetails">
<% - -		<jsp:include page="/jsp/appeals/inc_appealDetailsAuditLog.jsp" />- - - % >
								<audit:auditTableSet id="provAppealAuditId"	value="#{appealDataBean.appealVO.auditKeyList}"	numOfRecPerPage="10">
								</audit:auditTableSet>
		</f:subview>
	</m:div>
EOF ESPRD00509203_ProviderAppeals_03AUG2010 --%>
	<m:div>
		<m:br />
		<m:br />
	</m:div>
</m:div>



