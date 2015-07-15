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

<t:saveState id="CMGTTOMSS111"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS112"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS113"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS114"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS115" value="#{commonEntityDataBean.contactTypeAuditHistList}"></t:saveState>
<t:saveState id="CMGTTOMSS116" value="#{commonEntityDataBean.contactTypeHistRndr}"></t:saveState>
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonContact"
	var="cmnContactMsg" />

<%-- AUDIT PART --%>
<m:section id="PROVIDERMMS20120731164811183" styleClass="expand">
	<m:legend>


		<h:outputLink			onclick="toggleTest('auditConType',2);                     plusMinusForRefreshTest('auditConType',this,'addlinfoConTy_hidden');return false;"			id="plusMinus_AuditmoreDisp" styleClass="plus">
			<h:outputText id="audit"				value="#{cmnContactMsg['label.commonContact.audit']}" />
		</h:outputLink>
		<h:inputHidden id="addlinfoConTy_hidden" value="plus"></h:inputHidden>
		<h:inputHidden id="audit_open"			value="#{commonEntityDataBean.auditConType}"></h:inputHidden>

	</m:legend>
	<m:div sid="auditConType">
		<m:table id="PROVIDERMMT20120731164811184" cellspacing="0" width="50%">
			<m:tr>
				<m:td colspan="2">
					<h:outputText id="PRGCMGTOT443"						value="#{cmnContactMsg['label.commonContact.contactinfotable']}"						styleClass="strong" />
				</m:td>
			</m:tr>
			<m:tr style="cursor:pointer">
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT444"						value="#{cmnContactMsg['label.commonContact.lastupdatetime']}"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<t:commandLink id="PRGCMGTCL77"						action="#{commonContactControllerBean.showContactTypeAuditHistory}">
						<h:outputText id="PRGCMGTOT445"							value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT446"						value="#{cmnContactMsg['label.commonContact.lastupdateuserid']}"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT447"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.auditUserID}" />
					<m:br />
					<m:br />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT448"						value="#{cmnContactMsg['label.commonContact.originalentrytime']}"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT449"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.addedAuditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT450"						value="#{cmnContactMsg['label.commonContact.originalentryuserid']}"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT451"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.addedAuditUserID}" />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td colspan="2">
					<m:div></m:div>
				</m:td>
			</m:tr>
		</m:table>

		<m:div id="CnI_log_detail" styleClass="" align="left"
			rendered="#{commonEntityDataBean.contactTypeHistRndr}">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="revhistory_table" width="50%"				value="#{commonEntityDataBean.contactTypeAuditHistList}"				var="contactAuditHistory">
				<h:column id="hiscolname">
					<f:facet name="header">
						<h:outputText id="hcolname"							value="#{cmnContactMsg['label.commonContact.fieldname']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT452" value="#{contactAuditHistory.columnName}" />
				</h:column>
				<h:column id="hisbefore">
					<f:facet name="header">
						<h:outputText id="hbefore"							value="#{cmnContactMsg['label.commonContact.before']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT453" value="#{contactAuditHistory.columnBeforeValue}" />
				</h:column>
				<h:column id="hisafter">
					<f:facet name="header">
						<h:outputText id="hafter"							value="#{cmnContactMsg['label.commonContact.after']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT454" value="#{contactAuditHistory.columnAfterValue}" />
				</h:column>
			</t:dataTable>

		</m:div>
	</m:div>
</m:section>
<%-- AUDIT END --%>

