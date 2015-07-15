<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:div id="asscocCasecorrDiv1">
	<m:div id="asscocCasecorrDiv2">
		<m:section styleClass="casebg" id="asscocCasecorrSec1">
		
			<m:legend id="asscocCasecorrLeg1">
				<%--<h:outputLink					onclick="javascript:flagWarn=false;setHiddenValue('CMlogCase:associatedCaseandCorrespondence:assocCaseAndCRHiddenID','minus');	 						toggle('showhide_assocCaseAndCR',2);						 plusMinusToggle('showhide_assocCaseAndCR',this,'CMlogCase:associatedCaseandCorrespondence:assocCaseAndCRHiddenID');						 return false;"					id="plusMinus_assocCaseAndCRFalse" styleClass="plus">
					<h:outputText id="assocCaseAndCR_text"						value="#{msg['title.case.associatedCase&Correspondence']}" />
				</h:outputLink>
				<h:inputHidden id="assocCaseAndCRHiddenID"					value="#{logCaseDataBean.assocCaseAndCRHidden}" />--%>
				<t:commandLink
					action="#{logCaseControllerBean.getAssociateOptionsPlus}"
					id="Associateplusid" styleClass="plus"
					rendered="#{logCaseDataBean.associatePlusMinusFlag}"
					onmousedown="javascript:focusThis(this.id);flagWarn=false;focusPage('associatedCsCrHeader');">
					<h:outputText id="Associate_Crspd_SearchPlus"
						value="#{msg['title.case.associatedCase&Correspondence']}">
					</h:outputText>

				</t:commandLink>

				<t:commandLink
					action="#{logCaseControllerBean.getAssociateOptionsMinus}"
					id="Associateminusid" styleClass="minus"
					rendered="#{not logCaseDataBean.associatePlusMinusFlag}"
					onmousedown="javascript:focusThis(this.id);flagWarn=false;focusPage('associatedCsCrHeader');">
					<h:outputText id="Associate_Crspd_SearchMinus"
						value="#{msg['title.case.associatedCase&Correspondence']}">
					</h:outputText>
				</t:commandLink>
			</m:legend>
            <%-- <m:div sid="showhide_assocCaseAndCR" >--%> 
            <m:div id="showhide_assocCaseAndCR" rendered="#{logCaseDataBean.searchForCaseAndCR}">
				<m:div id="asscocCasecorrDiv3">
 				<f:subview id="associatedCrSubView1">
					 <m:anchor name="caseAscCrspondence"></m:anchor>
					<jsp:include page="inc_logCaseAssociatedCorrespondence.jsp" />
				</f:subview>
				</m:div>
		       
					<m:div id="asscocCasecorrDiv4">
					<f:subview id="logCaseAssociatedCase">
						 <m:anchor name="caseAscHeader"></m:anchor>
						<jsp:include page="inc_logCaseAssociatedCase.jsp" />
					</f:subview>
				</m:div>
			</m:div>
		</m:section>
	</m:div>

</m:div>
