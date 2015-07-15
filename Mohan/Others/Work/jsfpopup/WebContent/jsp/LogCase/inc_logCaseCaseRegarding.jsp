<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<m:section id="caseRegSectionId1" styleClass="casebg">
	<m:legend id="caseRegLegndId1">
			<h:outputText id="PRGCMGTOT627" styleClass="strong"				value="#{msg['title.case.caseRegarding']}" />
	</m:legend>
	<m:br id="caseRegBRId1" />
	<f:subview id="caseRegardingProvider" rendered="#{logCaseDataBean.showCaseRegardingProvider}">
		<jsp:include page="inc_logCaseCaseRegardingProvider.jsp" />
	</f:subview>
	<f:subview id="caseRegardingMember" rendered="#{logCaseDataBean.showCaseRegardingMember}">
		<jsp:include page="inc_logCaseCaseRegardingMember.jsp" />
	</f:subview> 
	<f:subview id="caseRegardingUnEnrolledMember" rendered="#{logCaseDataBean.showCaseRegardingUnEnrolMem}">
		<jsp:include page="inc_logCaseCaseRegardingUnEnrolledMem.jsp" />
	</f:subview> 
	<f:subview id="caseRegardingUnEnrolledProvider" rendered="#{logCaseDataBean.showCaseRegardingUnEnrolProv}">
		<jsp:include page="inc_logCaseCaseRegardingUnEnrolledProv.jsp" />
	</f:subview>
	<f:subview id="caseRegardingOther" rendered="#{logCaseDataBean.showCaseRegardingOther}">
		<jsp:include page="inc_logCaseCaseRegardingOthers.jsp" />
	</f:subview>
	<f:subview id="caseRegardingTradPart" rendered="#{logCaseDataBean.showCaseRegardingTradPart}">
		<jsp:include page="inc_logCaseCaseRegardingTradingPartner.jsp" />
	</f:subview>
</m:section>
