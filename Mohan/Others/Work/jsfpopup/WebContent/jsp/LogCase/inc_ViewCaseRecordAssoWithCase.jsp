<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<script type="text/javascript">
function deleteAssCaseRecord(){

	if(confirm('Are you sure you want to Delete?')){
		return true;
	}else{
		flagWarn=true;
		return false;
	}
	
}
</script>
<m:br/><m:br/>
<m:div id="ViewCaseRecordAssoWithCaseDiv1" styleClass="moreInfo">
	<m:div id="ViewCaseRecordAssoWithCaseDiv2" styleClass="moreInfoBar">
		<m:div id="ViewCaseRecordAssoWithCaseDiv3" styleClass="infoTitle">
			<h:outputText				value="#{msg['label.case.associatedCaseAndCorrespondence.caseRecord']}" id="ViewCaseRecordAssoWithCaseOUT1"/>
		</m:div>
		<m:div id="ViewCaseRecordAssoWithCaseDiv4" styleClass="infoActions">
			<t:commandLink id="ViewCaseRecordAssoWithCaseLink2" action="#{logCaseControllerBean.deleteCaseAssociatedWithThisCase}" onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseFocusPage');" onclick="javascript:return deleteAssCaseRecord();" rendered="#{!logCaseDataBean.disableDeleteCaseRecAssocWithCase && !logCaseDataBean.disableScreenField}">
				<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt2" value="#{msg['label.case.delete']}"/>
			</t:commandLink>
			<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt1" value="#{msg['label.case.delete']}" rendered="#{logCaseDataBean.disableDeleteCaseRecAssocWithCase || logCaseDataBean.disableScreenField}"/>
			<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt12" escape="false" value="&nbsp;"/>
			<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt3" value="|"/>
			<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt4" escape="false" value="&nbsp;"/>
			<t:commandLink id="ViewCaseRecordAssoWithCaseLink1" action="#{logCaseControllerBean.renderCancelCaseAssoWithThisCase}" onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseFocusPage');">
				<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt5" value="#{msg['label.case.cancel']}"/>
			</t:commandLink>
		</m:div>
	</m:div>
	<m:div id="ViewCaseRecordAssoWithCaseDiv5" styleClass="moreInfoContent">	
		<m:div id="ViewCaseRecordAssoWithCaseDiv6" styleClass="width:100%">
			<m:table id="ViewCaseRecordAssoWithCaseOutTBL1" cellspacing="0" width="95%">
				<m:tr id="ViewCaseRecordAssoWithCaseOutTR1">
					<m:td id="ViewCaseRecordAssoWithCaseOutTD1">
						<m:div id="ViewCaseRecordAssoWithCaseDiv7" styleClass="padb">
							<h:outputLabel id="ViewCaseRecordAssoWithCaseLabel1" for="caseidtxtbox">
								<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt6"									value="#{msg['label.case.associatedCaseAndCorrespondence.ID']}" />
							</h:outputLabel>
							<m:br id="ViewCaseRecordAssoWithCaseBr1"></m:br>
							<h:inputText id="caseidtxtbox" disabled="true" value="#{logCaseDataBean.associatedCaseVO.caseID}"/>
							<%--onkeyup and onblur commented for defect ESPRD00754727--%>
							<%-- onkeyup="this.value=this.value.toUpperCase();" onblur="this.value=this.value.toUpperCase();" --%>
						</m:div>
					</m:td>
					<m:td id="ViewCaseRecordAssoWithCaseOutTD2">
						<m:div id="ViewCaseRecordAssoWithCaseDiv8" styleClass="padb">
							<h:outputLabel id="ViewCaseRecordAssoWithCaseLabel2" for="caseAsscreatedDatefield">
								<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt7"									value="#{msg['label.case.associatedCaseAndCorrespondence.createdDate']}" />
							</h:outputLabel>
							<m:br id="ViewCaseRecordAssoWithCaseBr2"></m:br>
							<h:inputText id="caseAsscreatedDatefield" disabled="true" value="#{logCaseDataBean.associatedCaseVO.createdDateStr}"/>
						</m:div>
					</m:td>
					<m:td id="ViewCaseRecordAssoWithCaseOutTD3">
						<m:div id="ViewCaseRecordAssoWithCaseDiv9" styleClass="padb">
							<h:outputLabel id="ViewCaseRecordAssoWithCaseLabel3" for="selonemen_status">
								<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt8"									value="#{msg['label.case.associatedCaseAndCorrespondence.status']}" />
							</h:outputLabel>
							<m:br id="ViewCaseRecordAssoWithCaseBr3"></m:br>
							<h:selectOneMenu id="selonemen_status" disabled="true" value="#{logCaseDataBean.associatedCaseVO.status}">
								<%--modified for defect ESPRD00328556 <f:selectItems value="#{logCaseDataBean.statusList}" />--%>
								<f:selectItems value="#{logCaseDataBean.allCaseStatusList}" />
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td id="ViewCaseRecordAssoWithCaseOutTD4">
						<m:div id="ViewCaseRecordAssoWithCaseDiv10" styleClass="padb">
							<h:outputLabel id="ViewCaseRecordAssoWithCaseLabel4" for="caseAssoCaseType">
								<h:outputText id="ViewCaseRecordAssoWithCaseOutTxt9"									value="#{msg['label.case.associatedCaseAndCorrespondence.caseType']}" />
							</h:outputLabel>
							<m:br id="ViewCaseRecordAssoWithCaseBr4"></m:br>
							<h:inputText id="caseAssoCaseType" disabled="true" value="#{logCaseDataBean.associatedCaseVO.caseType}"/>
						</m:div>
					</m:td>
					<m:td id="ViewCaseRecordAssoWithCaseOutTD5">
						<m:div id="ViewCaseRecordAssoWithCaseDiv11" styleClass="padb">
							<h:outputLabel id="ViewCaseRecordAssoWithCaseLabel5" for="caseAssocontactfield"								value="#{msg['label.case.associatedCaseAndCorrespondence.contact']}" />
							<m:br id="ViewCaseRecordAssoWithCaseBr5"></m:br>
							<h:inputText id="caseAssocontactfield" disabled="true" />
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
			<f:subview id="auditing">
				<m:br id="ViewCaseRecordAssoWithCaseBr6" />
				<m:br id="ViewCaseRecordAssoWithCaseBr7" />
<%--CR_ESPRD00373565_LogCase_04AUG2010  <jsp:include page="inc_CaseRecordAudit.jsp" /> --%>
<m:div id="ViewCaseRecordAssoWithCaseAuditDiv" rendered="#{logCaseDataBean.enableAuditLogForLogCase}">
											<audit:auditTableSet id="ViewCaseRecordAssoWithCaseAuditId"
												value="#{logCaseDataBean.associatedCaseVO.auditKeyList}"
												numOfRecPerPage="10">
											</audit:auditTableSet>

</m:div>
<%--EOF CR_ESPRD00373565_LogCase_04AUG2010 --%>
			</f:subview>
		</m:div>
	</m:div>
</m:div>
