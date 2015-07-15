<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:section id="CaseRecordAuditSec1" styleClass="expand">
	
<%--modified for ESPRD00345973 --%>
	<m:legend  id="CaseRecordAuditLeg1">
		<h:outputLink  onclick="try{return plusMinusToggleChange(this);}catch(e){return false;}"			id="plusMinus_assocCaseAuditFalse" styleClass="plus">
			<h:outputText id="assocCaseAudit_text"				value="Audit" />
		</h:outputLink>
		<h:inputHidden  id="assocCaseAuditHiddenID" value="#{logCaseDataBean.assocCaseAuditHidden}" />
	</m:legend>
<%--EOF ESPRD00345973 modifcations --%>
	<m:div sid="audit_plusassociatedcaseAudit">
		<m:table id="CaseRecordAudittb1" cellspacing="0" width="50%">
			<m:tr  id="CaseRecordAudittr1">
				<m:td id="CaseRecordAudittd1" colspan="2">
					<h:outputText  id="CaseRecordAudittxt1" value="#{msg['label.audit.caseInfoTable']}"						styleClass="strong" />
				</m:td>
			</m:tr>
			<m:tr  id="CaseRecordAudittr2" style="cursor:pointer">
				<m:td id="CaseRecordAudittd2" styleClass="dataLabel">
					<h:outputText  id="CaseRecordAudittxt2" value="#{msg['label.audit.lastUpdateDateTime']}"						styleClass="dataLabel" />
				</m:td>
				<m:td id="CaseRecordAudittd3">
					<t:commandLink onmousedown="javascript:flagWarn=false;" id="CaseRecordAuditCL1">
						<h:outputText  id="CaseRecordAudittxt3"							value="#{logCaseDataBean.associatedCaseVO.auditTimeStamp}}" />
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr  id="CaseRecordAudittr3">
				<m:td id="CaseRecordAudittd4" styleClass="dataLabel">
					<h:outputText  id="CaseRecordAudittxt4" value="#{msg['label.audit.lastUpdatedUserID']}"						styleClass="dataLabel" />
				</m:td>
				<m:td id="CaseRecordAudittd5">
					<h:outputText  id="CaseRecordAudittxt5"						value="#{logCaseDataBean.associatedCaseVO.auditTimeStamp}" />
					<m:br id="CaseRecordAuditBr1"/>
					<m:br id="CaseRecordAuditBr2"/>
				</m:td>
			</m:tr>
			<m:tr  id="CaseRecordAudittr4">
				<m:td id="CaseRecordAudittd6" styleClass="dataLabel">
					<h:outputText  id="CaseRecordAudittxt6" value="#{msg['label.audit.originalEntryDate']}"						styleClass="dataLabel" />
				</m:td>
				<m:td id="CaseRecordAudittd7">
					<h:outputText  id="CaseRecordAudittxt7"						value="#{logCaseDataBean.associatedCaseVO.auditTimeStamp}" />
				</m:td>
			</m:tr>
			<m:tr  id="CaseRecordAudittr5">
				<m:td id="CaseRecordAudittd8" styleClass="dataLabel">
					<h:outputText  id="CaseRecordAudittxt8" value="#{msg['label.audit.originalEntryUserID']}"						styleClass="dataLabel" />
				</m:td>
				<m:td id="CaseRecordAudittd9">
					<h:outputText  id="CaseRecordAudittxt9"						value="#{logCaseDataBean.associatedCaseVO.auditTimeStamp}" />
				</m:td>
			</m:tr>
			<m:tr  id="CaseRecordAudittr6">
				<m:td id="CaseRecordAudittd10" colspan="2">
					<m:div id="CaseRecordAuditDIV2"></m:div>
				</m:td>
			</m:tr>
		</m:table>
	</m:div>
</m:section>
<%--ESPRD00345973 --%>
<script type="text/javascript">
function plusMinusToggleChange(currObj){

	try{
		toggleTest('audit_plusassociatedcaseAudit',2);
	}catch(e){}
try{
	if(currObj.className == 'plus' || currObj.className == "pluse"){
		
			currObj.className = 'minus';
			
	}else{
			currObj.className = 'plus';
			
	}
}catch(e){}
return false;
}
</script>
<%-- EOF ESPRD00345973--%>
