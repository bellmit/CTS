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

<t:saveState id="CMGTTOMSS188"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS189"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS190"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS191"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS192" value="#{commonEntityDataBean.contactTypeAuditHistList}"></t:saveState>
<t:saveState id="CMGTTOMSS193" value="#{commonEntityDataBean.contactTypeHistRndr}"></t:saveState>


<%-- AUDIT PART --%>
<m:section id="PROVIDERMMS20120731164811312" styleClass="expand">
	<m:legend>
		<t:commandLink id="plusMinus_moreContact" styleClass="plus">
			<h:outputText id="audit" value="Audit" />
		</t:commandLink>
	</m:legend>
	<m:div id="CnImoreContact" styleClass="">
		<m:table id="PROVIDERMMT20120731164811313" cellspacing="0" width="50%">
			<m:tr>
				<m:td colspan="2">
					<h:outputText id="PRGCMGTOT664" value="Contact Type Info Table"						styleClass="strong" />
				</m:td>
			</m:tr>
			<m:tr style="cursor:pointer">
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT665" value="Last Update Date / Time:"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<t:commandLink id="PRGCMGTCL114"						action="#{commonContactControllerBean.showContactTypeAuditHistory}">
						<h:outputText id="PRGCMGTOT666"							value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.auditTimeStamp}" />
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT667" value="Last Update User ID:"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT668"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.auditUserID}" />
					<m:br />
					<m:br />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT669" value="Original Entry Date / Time:"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT670"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.addedAuditTimeStamp}" />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT671" value="Original Entry User ID:"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT672"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.addedAuditUserID}" />
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
						<h:outputText id="hcolname" value="Field Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT673" value="#{contactAuditHistory.columnName}" />
				</h:column>
				<h:column id="hisbefore">
					<f:facet name="header">
						<h:outputText id="hbefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT674" value="#{contactAuditHistory.columnBeforeValue}" />
				</h:column>
				<h:column id="hisafter">
					<f:facet name="header">
						<h:outputText id="hafter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT675" value="#{contactAuditHistory.columnAfterValue}" />
				</h:column>
			</t:dataTable>

		</m:div>
	</m:div>
</m:section>
<%-- AUDIT END --%>

