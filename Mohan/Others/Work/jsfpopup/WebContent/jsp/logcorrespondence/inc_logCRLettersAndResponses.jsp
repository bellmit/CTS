<%-- 
  - Author(s): Wipro
  - Date: Mon Jan 29 14:37:45 IST 2007
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
  --%>
<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceLog"
	var="crspd" />

<m:div>
	<f:subview id="logCrspdlettersNrespSubview">
		<m:section id="PROVIDERMMS20120731164811388" styleClass="otherbg">
			<m:legend>
				<h:outputLink					onclick="setHiddenValue('logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:letNrespHidden','minus');toggle('log_div_lettNresp',2);							plusMinusToggle('log_div_lettNresp',this,'logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:letNrespHidden');							 return false;"					id="plusMinusLettNresp" styleClass="plus">

					<h:outputText id="PRGCMGTOT896" value="#{crspd['label.crspd.lettersresponses']}" />
					<h:inputHidden id="letNrespHidden"						value="#{CorrespondenceDataBean.letNrespHidden}"></h:inputHidden>
				</h:outputLink>

			</m:legend>

			<h:inputHidden id="lnrErr" value="ErrMsgHolder" />
			<h:message id="PRGCMGTM157" for="lnrErr" showDetail="true" style="color: red" />
			<m:div sid="log_div_lettNresp">
				<m:div styleClass="fieldsetActions" align="right">
					<m:div rendered="#{CorrespondenceDataBean.controlRequired}">
						<t:commandLink id="PRGCMGTCL142" styleClass="strong" rendered="#{!CorrespondenceDataBean.crClosed}"							action="#{CorrespondenceControllerBean.newResponse}">
							<h:outputText id="PRGCMGTOT897" value="#{crspd['label.crspd.newresponse']}" />
							<f:param name="send.letter.generation.data"
								value="SendletterGenerationData"></f:param>
						</t:commandLink>
						<h:outputText id="PRGCMGTOT898" rendered="#{CorrespondenceDataBean.crClosed}" value="#{crspd['label.crspd.newresponse']}" />
					</m:div>
				</m:div>
			</m:div>
		</m:section>
	</f:subview>
</m:div>
