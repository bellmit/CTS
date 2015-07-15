<%-- Added This JSP for displaying instead of Loading Source Page again while navigating to Target Page for defect ESPRD00828394 --%>
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
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<portlet:defineObjects />

<f:view>
	<body style="overflow:hidden">
		<m:anchor name="submitPageFocus"></m:anchor>
		<h:form id="rightClickForm">
		
		<h:inputHidden id="hyperlinkAppealID" value="#{appealDataBean.rightClickIndicator}"></h:inputHidden>
		 <h:inputHidden id="preView" value="#{appealDataBean.preView}"/>
		<m:div id="processDiv" align="center">
		<m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/>
		
		<f:verbatim escape="false">
			<h3>Please wait while your page is processing...</h3>
		</f:verbatim>
		<m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/>
		
		</m:div>
		<%-- Add Appeals --%>
		<t:commandLink id="PRGCMGTCL8" styleClass="hide"
			action="#{logCaseControllerBean.submitCaseDetails}"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="PRGCMGTOT15" value="Case Record Details"></h:outputText>
			<f:param id="ipcActionAppeals" name="com.ibm.portal.propertybroker.action"
				value="sendCaseDetailsAction" />
			<f:param name="caseID" value="#{appealDataBean.caseSK}"></f:param>
			<f:param name="entityID" value="#{appealDataBean.providerID}"></f:param>
			<f:param name="entityType" value="#{appealDataBean.entityType}"></f:param>
			<f:param name="preView" value="#{appealDataBean.preView}"></f:param>
		</t:commandLink>
		<t:commandLink id="PRGCMGTCL10" styleClass="hide"
			action="#{appealControllerBean.searchClaimInquiryByTCN}"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="xx1" value="Claims Details"></h:outputText>
			<f:param name="action.param.send.ClaimsInternalInquiryAction"
				value="send.ClaimsInternalInquiryAction"></f:param>
			<f:param name="preView" value="#{appealDataBean.preView}"></f:param>
		</t:commandLink>
		<t:commandLink id="PRGCMGTCL12" styleClass="hide"
			action="#{appealControllerBean.submitSADetailsForIPC}"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="xx2" value="SA Details"></h:outputText>
			<f:param name="send.insti.claimCorr.info"
				value="sendInstiClaimCorrInfo" />
			<f:param name="preView" value="#{appealDataBean.preView}"></f:param>
		</t:commandLink>
		<%-- Member Appeals --%>
		<t:commandLink id="PRGCMGTCL20" styleClass="hide"
			action="#{logCaseControllerBean.submitCaseDetails}"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="PRGCMGTOT92" value="Case Record Details"></h:outputText>
			<f:param id="ipcActionMemAppeals" name="com.ibm.portal.propertybroker.action"
				value="sendCaseDetailsAction" />
			<f:param name="caseID" value="#{appealDataBean.appealVO.caseSK}"></f:param>
			<f:param name="entityID" value="#{appealDataBean.memberID}"></f:param>
			<f:param name="entityType" value="#{appealDataBean.entityType}"></f:param>
			<f:param name="preView" value="#{appealDataBean.preView}"></f:param>
		</t:commandLink>
		<t:commandLink id="PRGCMGTCL22" styleClass="hide"
			action="#{appealControllerBean.searchClaimInquiryByTCN}"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="ssss" value="Claims Details"></h:outputText>
			<f:param name="action.param.send.ClaimsInternalInquiryAction"
				value="send.ClaimsInternalInquiryAction"></f:param>
			<f:param name="preView" value="#{appealDataBean.preView}"></f:param>
		</t:commandLink>
		<t:commandLink id="PRGCMGTCL24" styleClass="hide"
			action="#{appealControllerBean.submitSADetailsForIPC}"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="ddd" value="SA Details"></h:outputText>
			<f:param name="send.insti.claimCorr.info"
				value="sendInstiClaimCorrInfo" />
			<f:param name="preView" value="#{appealDataBean.preView}"></f:param>
		</t:commandLink>
		<%-- Provider Appeals --%>
		<t:commandLink id="PRGCMGTCL25" styleClass="hide"
			action="#{logCaseControllerBean.submitCaseDetails}"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="PRGCMGTOT99" value="Case Record Details"></h:outputText>
			<f:param id="ipcActionProvAppeals" name="com.ibm.portal.propertybroker.action"
				value="sendCaseDetailsAction" />
			<f:param name="caseID" value="#{appealDataBean.appealVO.caseSK}"></f:param>
			<f:param name="entityID" value="#{appealDataBean.providerID}"></f:param>
			<f:param name="entityType" value="#{appealDataBean.entityType}"></f:param>
			<f:param name="preView" value="#{appealDataBean.preView}"></f:param>
		</t:commandLink>
		<t:commandLink id="PRGCMGTCL27" styleClass="hide"
			action="#{appealControllerBean.searchClaimInquiryByTCN}"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="xxxx" value="Claims Details"></h:outputText>
			<f:param name="action.param.send.ClaimsInternalInquiryAction"
				value="send.ClaimsInternalInquiryAction"></f:param>
			<f:param name="preView" value="#{appealDataBean.preView}"></f:param>
		</t:commandLink>
		<t:commandLink id="PRGCMGTCL29" styleClass="hide"
			action="#{appealControllerBean.submitSADetailsForIPC}"
			onmousedown="javascript:flagWarn=false;">
			<h:outputText id="ccc" value="SA Details"></h:outputText>
			<f:param name="send.insti.claimCorr.info"
				value="sendInstiClaimCorrInfo" />
			<f:param name="preView" value="#{appealDataBean.preView}"></f:param>
		</t:commandLink>
	</h:form>
	</body>
	<script>
	window.onload = onLoadExecute;
	function onLoadExecute() {
		var thisForm = 'view<portlet:namespace/>:rightClickForm';
		setTimeout( function() {document.location.href = '#submitPageFocus'; }, 100);
		window.scrollBy(10, 200);
		var rightClickInd = document
				.getElementById(thisForm + ':hyperlinkAppealID').value;
		if (rightClickInd != null && rightClickInd != "") {
			document.getElementById(thisForm + ':hyperlinkAppealID').value = "";
			flagWarn = false;
			document.getElementById(thisForm + ':' + rightClickInd).click();
		}
	}
</script>	
</f:view>
