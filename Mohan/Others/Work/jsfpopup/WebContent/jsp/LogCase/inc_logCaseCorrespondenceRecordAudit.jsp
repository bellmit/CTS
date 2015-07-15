<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:section id="logCaseCrRcrdAudSec1" styleClass="expand">
	<m:legend id="logCaseCrRcrdAudLeg1">
			<h:outputLink				onclick="toggleTest('audit_plusassociatedcase',2);                     plusMinusForRefreshTest('audit_plusassociatedcase',this,'addlinfo_hidden');return false;"				id="plusMinus_AuditmoreforassociatedCase" styleClass="plus">
				<h:outputText id="AssociatedCaseandCorrespondencedetails"					value="#{msg['label.audit']}" />
			</h:outputLink>
			<%--<h:inputHidden id="addlinfo_associatedCase" value="#{logCaseDataBean.assocCaseAndCRHidden}"></h:inputHidden>--%>
	</m:legend>
	<m:div sid="audit_plusassociatedcase">
		<m:table id="logCaseCrRcrdAudTb1" cellspacing="0" width="50%">
			<m:tr id="logCaseCrRcrdAudTr1">
				<m:td id="logCaseCrRcrdAudTd1" colspan="2">
					<h:outputText id="logCaseCrRcrdAudOutTxt1" value="#{msg['label.audit.addressInfoTable']}" styleClass="strong" />
				</m:td>
			</m:tr>
			<m:tr id="logCaseCrRcrdAudTr2" styleClass="mousepointer">
				<m:td id="logCaseCrRcrdAudTd2">
					<h:outputText id="logCaseCrRcrdAudOutTxt2" value="#{msg['label.audit.lastUpdateDateTime']}" styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseCrRcrdAudTd3">
					<t:commandLink id="PRGCMGTCL108" action="#"  onmousedown="javascript:flagWarn=false;">
						<h:outputText id="logCaseCrRcrdAudOutTxt3"/>
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr id="logCaseCrRcrdAudTr3">
				<m:td id="logCaseCrRcrdAudTd4">
					<h:outputText id="logCaseCrRcrdAudOutTxt4" value="#{msg['label.audit.lastUpdatedUserID']}" styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseCrRcrdAudTd5">
					<h:outputText id="logCaseCrRcrdAudOutTxt5"/>
					<m:br />
					<m:br />
				</m:td>
			</m:tr>
			<m:tr id="logCaseCrRcrdAudTr4">
				<m:td id="logCaseCrRcrdAudTd6">
					<h:outputText id="logCaseCrRcrdAudOutTxt6" value="#{msg['label.audit.originalEntryDate']}" styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseCrRcrdAudTd7">
					<h:outputText id="logCaseCrRcrdAudOutTxt7"/>
				</m:td>
			</m:tr>
			<m:tr id="logCaseCrRcrdAudTr5">
				<m:td id="logCaseCrRcrdAudTd8">
					<h:outputText id="logCaseCrRcrdAudOutTxt8" value="#{msg['label.audit.originalEntryUserID']}"	styleClass="outputLabel" />
				</m:td>
				<m:td id="logCaseCrRcrdAudTd9">
					<h:outputText id="logCaseCrRcrdAudOutTxt9" />
				</m:td>
			</m:tr>
			<m:tr id="logCaseCrRcrdAudTr6">
				<m:td id="logCaseCrRcrdAudTd10" colspan="2">
					<m:div  id="logCaseCrRcrdAudOutDiv1" styleClass="clearl">
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
	</m:div>
</m:section>
