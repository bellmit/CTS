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

<t:saveState id="CMGTTOMSS182"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS183"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS184"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS185"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS186" value="#{commonEntityDataBean.contactAuditHistList}"></t:saveState>
<t:saveState id="CMGTTOMSS187" value="#{commonEntityDataBean.contactHistRndr}"></t:saveState>


<!-- AUDIT PART -->
<m:section id="PROVIDERMMS20120731164811310" styleClass="expand">
	<m:legend>
		<t:commandLink id="plusMinus_moreContact" styleClass="plus">
			<h:outputText id="audit" value="Audit" />
		</t:commandLink>
	</m:legend>
	<m:div id="CnImoreContact" styleClass="">
		<m:table id="PROVIDERMMT20120731164811311" cellspacing="0" width="50%">
			<m:tr>
				<m:td colspan="2">
					<h:outputText id="PRGCMGTOT652" value="Contact Info Table"						styleClass="strong" />
				</m:td>
			</m:tr>
			<m:tr style="cursor:pointer">
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT653" value="Last Update Date / Time:"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<t:commandLink id="PRGCMGTCL113"						action="#{commonContactControllerBean.showContactAuditHistory}">
						<h:outputText id="PRGCMGTOT654"							value="#{commonEntityDataBean.commonEntityVO.commonContactVO.auditTimeStamp}" />
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT655" value="Last Update User ID:"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT656"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.auditUserID}" />
					<m:br />
					<m:br />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT657" value="Original Entry Date / Time:"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT658"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.addedAuditTimeStamp}" />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT659" value="Original Entry User ID:"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT660"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.addedAuditUserID}" />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td colspan="2">
					<m:div></m:div>
				</m:td>
			</m:tr>
		</m:table>

		<m:div id="CnI_log_detail" styleClass="" align="left" 
					rendered="#{commonEntityDataBean.contactHistRndr}">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="revhistory_table" width="50%"				value="#{commonEntityDataBean.contactAuditHistList}"				var="contactAuditHistory">
				<h:column id="hiscolname">
					<f:facet name="header">
						<h:outputText id="hcolname" value="Field Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT661" value="#{contactAuditHistory.columnName}" />
				</h:column>
				<h:column id="hisbefore">
					<f:facet name="header">
						<h:outputText id="hbefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT662" value="#{contactAuditHistory.columnBeforeValue}" />
				</h:column>
				<h:column id="hisafter">
					<f:facet name="header">
						<h:outputText id="hafter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT663" value="#{contactAuditHistory.columnAfterValue}" />
				</h:column>
			</t:dataTable>

		</m:div>
	</m:div>
</m:section>
<!-- AUDIT END -->

