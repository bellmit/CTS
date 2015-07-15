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
<t:saveState id="CMGTTOMSS308"	value="#{CaseTypeDataBean.caseTypeAuditRender}"></t:saveState>
<t:saveState id="CMGTTOMSS309"	value="#{CaseTypeDataBean.caseTypeAuditHistoryList}"></t:saveState>
<t:saveState id="CMGTTOMSS310"	value="#{CaseTypeDataBean.caseTypeVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS311"     value="#{CaseTypeDataBean.caseTypeVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS312"	value="#{CaseTypeDataBean.caseTypeVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS313"	value="#{CaseTypeDataBean.caseTypeVO.addedAuditUserID}"></t:saveState>
	
<m:section id="PROVIDERMMS20120731164811429" styleClass="expand">
  
	<m:legend>
			<h:outputLink onmousedown="javascript:flagWarn=false;"				onclick="toggleTest('audit_plus',2);                     plusMinusForRefreshTest('audit_plus',this,'addlinfo_hidden');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{msg['label.ref.audit']}"/>
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open"				value="#{CaseTypeDataBean.auditOpen}"></h:inputHidden>
	</m:legend>
	<m:div sid="audit_plus">
		<m:table id="PROVIDERMMT20120731164811430" cellspacing="0" width="50%">
			<m:tr>
				<m:td colspan="2">
					<m:span styleClass="strong">
						<h:outputText id="mem" value="#{msg['label.ref.memInfoTable']}" />
					</m:span>
				</m:td>
			</m:tr>
			<m:tr styleClass="mousepointer">
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT1052" value="#{msg['label.ref.lastUpdtTime']}"></h:outputText>
				</m:td>
				<m:td>
					<t:commandLink id="PRGCMGTCL157" onmousedown="javascript:flagWarn=false;"						action="#{CaseTypeControllerBean.showAuditHistory}">
						<h:outputText id="PRGCMGTOT1053"							value="#{CaseTypeDataBean.caseTypeVO.auditTimeStamp}" />
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
				   <h:outputText id="PRGCMGTOT1054" value="#{msg['label.ref.lastUpdtUserId']}"></h:outputText>
					
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT1055"						value="#{CaseTypeDataBean.caseTypeVO.auditUserID}" />
					<m:br />
					<m:br />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
				  <h:outputText id="PRGCMGTOT1056" value="#{msg['label.ref.origEntryTime']}"></h:outputText>
					
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT1057"						value="#{CaseTypeDataBean.caseTypeVO.addedAuditTimeStamp}" />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
				<h:outputText id="PRGCMGTOT1058" value="#{msg['label.ref.origEntryUserId']}"></h:outputText>
					
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT1059"						value="#{CaseTypeDataBean.caseTypeVO.addedAuditUserID}" />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td colspan="2">
					<m:div></m:div>
				</m:td>
			</m:tr>
		</m:table>
		<m:div id="log_detail" styleClass=""
			rendered="#{CaseTypeDataBean.caseTypeAuditRender}">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="cshistory_table" width="100%"				value="#{CaseTypeDataBean.caseTypeAuditHistoryList}" var="cshistory">
				<h:column id="hiscolname">
					<f:facet name="header">
						<h:outputText id="hcolname"							value="#{msg['label.ref.fieldName']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1060" value="#{cshistory.columnName}" />
				</h:column>
				<h:column id="hisbefore">
					<f:facet name="header">
						<h:outputText id="hbefore"							value="#{msg['label.ref.Before']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1061"  value="#{cshistory.columnBeforeValue}" />
				</h:column>
				<h:column id="hisafter">
					<f:facet name="header">
						<h:outputText id="hafter" value="#{msg['lable.ref.After']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1062"  value="#{cshistory.columnAfterValue}" />
				</h:column>
			</t:dataTable>

		</m:div>
	</m:div>
</m:section>


	
