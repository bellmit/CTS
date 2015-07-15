<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:section id= "assocCRAudit1" styleClass="expand">
	<m:legend id="assocCRAudit11">
			<h:outputLink				onclick="toggleTest('audit_plusassociatedCRAudit',2);                     plusMinusForRefreshTest('audit_plusassociatedCRAudit',this,'addlinfo_associatedCR');return false;"				id="plusMinus_AuditmoreforassociatedCase" styleClass="plus">
				<h:outputText id="AssociatedCRdetails"					value="Audit" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_associatedCR" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_associatedCR" value="false"></h:inputHidden>
	</m:legend>
	<m:div sid="audit_plusassociatedCRAudit">
		<m:table  id="assocCRAudit" cellspacing="0" width="50%">
			<m:tr id="assocCRAudittr1">
				<m:td id="assocCRAudittd1" colspan="2">
					<h:outputText  id="assocCRAuditot1" value="#{msg['label.audit.correspondenceInfoTable']}" styleClass="strong" />
				</m:td>
			</m:tr>
			<m:tr id="assocCRAudit2" style="cursor:pointer">
				<m:td id="assocCRAudittd2" styleClass="dataLabel">
					<h:outputText  id="assocCRAuditot2" value="#{msg['label.audit.lastUpdateDateTime']}"						styleClass="dataLabel" />
				</m:td>
				<m:td id="assocCRAudittd3">
					<t:commandLink onmousedown="javascript:flagWarn=false;" id="assocCRAuditcl1" >
						<h:outputText  id="assocCRAuditot3" value="#{logCaseDataBean.associatedCorrespondenceVO.auditTimeStamp}" />
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr id="assocCRAudittr2">
				<m:td id="assocCRAudittd4" styleClass="dataLabel">
					<h:outputText  id="assocCRAuditot4" value="#{msg['label.audit.lastUpdatedUserID']}" styleClass="dataLabel" />
				</m:td>
				<m:td id="assocCRAudittd5">
					<h:outputText  id="assocCRAuditot5" value="#{logCaseDataBean.associatedCorrespondenceVO.auditUserID}" />
					<m:br id="assocCRAudit1br1Id"/>
					<m:br id="assocCRAudit1br2Id"/>
				</m:td>
			</m:tr>
			<m:tr id="assocCRAudittr3">
				<m:td id="assocCRAudittd6" styleClass="dataLabel">
					<h:outputText  id="assocCRAuditot6" value="#{msg['label.audit.originalEntryDate']}"						styleClass="dataLabel" />
				</m:td>
				<m:td id="assocCRAudittd7">
					<h:outputText  id="assocCRAuditot7" value="#{logCaseDataBean.associatedCorrespondenceVO.addedAuditTimeStamp}" />
				</m:td>
			</m:tr>
			<m:tr id="assocCRAudittr4">
				<m:td id="assocCRAudittd8" styleClass="dataLabel">
					<h:outputText  id="assocCRAuditot8" value="#{msg['label.audit.originalEntryUserID']}"						styleClass="dataLabel" />
				</m:td>
				<m:td id="assocCRAudittd9">
					<h:outputText  id="assocCRAuditot9" value="#{logCaseDataBean.associatedCorrespondenceVO.addedAuditUserID}" />
				</m:td>
			</m:tr>
			<m:tr id="assocCRAudittr5">
				<m:td id="assocCRAudittd109" colspan="2">
					<m:div id="assocCRAudittd1Div1Id"></m:div>
				</m:td>
			</m:tr>
		</m:table>
	</m:div>
</m:section>
