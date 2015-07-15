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
function deleteAssCR(){

	if(confirm('Are you sure you want to Delete?')){
		return true;
	}else{
		flagWarn=true;
		return false;
	}
	
}

</script>

<m:br/><m:br/>
<m:div id="ViewCRAssWithCaseDetls1" styleClass="moreInfo">
	<m:div id="ViewCRAssWithCaseDetls2" styleClass="moreInfoBar">
		<m:div id="ViewCRAssWithCaseDetls3" styleClass="infoTitle">
			<h:outputText id="ViewCRAssWithCaseDetls1OutTxt1" value="#{msg['title.case.correspondence.correspondencerecord']}" />
		</m:div>
		<m:div id="ViewCRAssWithCaseDetls4" styleClass="infoActions">
			<t:commandLink id="ViewCRAssWithCaseDetlsinks1" action="#{logCaseControllerBean.deleteCRAssociatedWithThisCase}" onmousedown="javascript:flagWarn=false;focusPage('corrAssWithCaseFocusPage');" onclick="javascript:return deleteAssCR();" rendered="#{!logCaseDataBean.disableDeleteCorrespondingRecAssocWithCase && !logCaseDataBean.disableScreenField}">
				<h:outputText id="ViewCRAssWithCaseDetls1OutTxt2" value="#{msg['label.case.delete']}"/>
			</t:commandLink>
			<h:outputText id="ViewCRAssWithCaseDetls1OutTxt3" value="#{msg['label.case.delete']}" rendered="#{logCaseDataBean.disableDeleteCorrespondingRecAssocWithCase || logCaseDataBean.disableScreenField}"/>
			<h:outputText id="ViewCRAssWithCaseDetls1OutTxt4" escape="false" value="&nbsp;"/>
			<h:outputText id="ViewCRAssWithCaseDetls1OutTxt5" value="|"/>
			<h:outputText id="ViewCRAssWithCaseDetls1OutTxt6" escape="false" value="&nbsp;"/>
			<t:commandLink id="ViewCRAssWithCaseDetlsinks2" action="#{logCaseControllerBean.renderCancelCRAssoWithThisCase}" onmousedown="javascript:flagWarn=false;focusPage('corrAssWithCaseFocusPage');">
				<h:outputText id="ViewCRAssWithCaseDetls1OutTxt7" value="#{msg['label.case.cancel']}"/>
			</t:commandLink>
		</m:div>
	</m:div>
	<m:div id="ViewCRAssWithCaseDetls5" styleClass="moreInfoContent">
		<m:div id="ViewCRAssWithCaseDetls6" styleClass="width:100%">
			<m:table id="ViewCRAssWithCaseDetlsTb1" cellspacing="0" width="98%">
				<m:tr id="ViewCRAssWithCaseDetlsTr1">
					<m:td id="ViewCRAssWithCaseDetlsTd1">
						<m:div id="ViewCRAssWithCaseDetls7" styleClass="padb">
							<h:outputLabel id="ViewCRAssWithCaseDetlLabel1" for="idtxtbox" value="#{msg['label.case.Correspondence.id']}"/>
							<m:br id="ViewCRAssWithCaseDetlBr1"></m:br>
							<h:inputText id="idtxtbox" disabled="true" value="#{logCaseDataBean.associatedCorrespondenceVO.correspondenceRecordNumber}"/>
						</m:div>
					</m:td>
					<m:td id="ViewCRAssWithCaseDetlsTd2">
						<m:div id="ViewCRAssWithCaseDetls8" styleClass="padb">
							<h:outputLabel id="ViewCRAssWithCaseDetlLabel2" for="datefield" value="#{msg['label.case.routing.date']}"/>
							<m:br id="ViewCRAssWithCaseDetlBr2"></m:br>
							<h:inputText id="datefield" disabled="true" value="#{logCaseDataBean.associatedCorrespondenceVO.openDate}"/>
						</m:div>
					</m:td>
					<m:td id="ViewCRAssWithCaseDetlsTd3">
						<m:div id="ViewCRAssWithCaseDetls9" styleClass="padb">
							<h:outputLabel id="ViewCRAssWithCaseDetlLabel3" for="selonemen_8" value="#{msg['label.case.Correspondence.status']}"/>
							<m:br id="ViewCRAssWithCaseDetlBr3"></m:br>
							<h:selectOneMenu id="selonemen_8" disabled="true">
								<f:selectItem itemValue=" " itemLabel="Call Center" /> 
							</h:selectOneMenu>
						</m:div>
					</m:td>
					<m:td id="ViewCRAssWithCaseDetlsTd4">
						<m:div id="ViewCRAssWithCaseDetls10" styleClass="padb">
							<h:outputLabel id="ViewCRAssWithCaseDetlLabel4" for="subjectfield" value="#{msg['label.case.Correspondence.subject']}"/>
							<m:br id="ViewCRAssWithCaseDetlBr4"></m:br>
							<h:inputText id="subjectfield"	disabled="true" value="#{logCaseDataBean.associatedCorrespondenceVO.subject}"/>
						</m:div>
					</m:td>
					<m:td id="ViewCRAssWithCaseDetlsTd5">
						<m:div id="ViewCRAssWithCaseDetls11" styleClass="padb">
							<h:outputLabel id="ViewCRAssWithCaseDetlLabel5" for="contactfield" value="#{msg['label.case.Correspondence.contact']}"/>
							<m:br id="ViewCRAssWithCaseDetlBr5"></m:br>
							<h:inputText id="contactfield" disabled="true" value="#{logCaseDataBean.associatedCorrespondenceVO.contact}"/>
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
			<f:subview id="auditingCR">
				<m:br id="ViewCRAssWithCaseDetlBr6" />
				<m:br id="ViewCRAssWithCaseDetlBr7" />
<%--CR_ESPRD00373565_LogCase_04AUG2010  <jsp:include page="inc_assocCRAudit.jsp" /> --%>
<m:div id="ViewCRAssWithCaseDetAuditDiv" rendered="#{logCaseDataBean.enableAuditLogForLogCase}">
											<audit:auditTableSet id="ViewCRAssWithCaseDetAuditId"
												value="#{logCaseDataBean.associatedCorrespondenceVO.auditKeyList}"
												numOfRecPerPage="10">
											</audit:auditTableSet>

</m:div>
<%--EOF CR_ESPRD00373565_LogCase_04AUG2010 --%>
			</f:subview>
		</m:div>
	</m:div>
	
	
</m:div>
