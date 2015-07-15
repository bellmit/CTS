<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>

<m:div id="logCaseEditRoutingDiv1" styleClass="moreInfo">
	<m:div id="logCaseEditRoutingDiv2" styleClass="moreInfoBar">
		<m:div id="logCaseEditRoutingDiv3" styleClass="infoTitle">
			<h:outputText id="logCaseEditRoutingOutTxt1" value="#{msg['label.case.routing.viewRoutingAssignment']}" 				styleClass="outputLabel"/>
		</m:div>
		<m:div id="logCaseEditRoutingDiv4" styleClass="infoActions">
		<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543--%>
			<t:commandLink action="#{logCaseControllerBean.renderCancelRoutingInfo}" id="logCaseEditRoutingLink1" onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"				value="#{msg['label.case.routing.close']}"/>
		</m:div>
	</m:div>
	<m:div id="logCaseEditRoutingDiv5" styleClass="moreInfoContent">
	<%--Modified for defect ESPRD00446264 starts--%>
	<%--	<m:div id="logCaseEditRoutingDiv6"
			rendered="#{logCaseDataBean.showCaseRoutingMessages}"
			styleClass="msgBox">
			<h:messages layout="table" showDetail="true" styleClass="successMsg"					id="editRoutingErrorMessage" showSummary="false">
				</h:messages>
		</m:div>--%>
		
		<%--Defect ESPRD00446264 Fix ends--%>
		<m:div id="logCaseEditRoutingDiv7" styleClass="width:100%">
			<m:table id="logCaseEditRoutingTb1" cellspacing="0" width="95%">
				<m:tr id="logCaseEditRoutingTr1">
					<m:td id="logCaseEditRoutingTd1">
						<m:div id="logCaseEditRoutingDiv8" styleClass="padb">
							<h:outputText id="logCaseEditRoutingOutTxt2" value="#{msg['label.case.routing.createdBy']}"								styleClass="outputLabel" />
							<m:br id="logCaseEditRoutingBr1"></m:br>
							<h:outputText id="logCaseEditRoutingOutTxt3" value="#{logCaseDataBean.routingVO.createdBy}" />
							 <%--<h:outputText id="logCaseEditRoutingOutTxt4" value="#{logCaseDataBean.routingVO.routedByName}" rendered="#{logCaseDataBean.routingVO.routedBySK !=null && logCaseDataBean.routingVO.routedBySK!=''}" />--%>
						</m:div>
					</m:td>
					<m:td id="logCaseEditRoutingTd2">
						<m:div id="logCaseEditRoutingDiv9" styleClass="padb">
							<h:outputText id="logCaseEditRoutingOutTxt5" styleClass="outputLabel"								value="#{msg['label.case.routing.assignedTo']}" />
							<m:br id="logCaseEditRoutingBr2"></m:br>
					       	    
						    <h:outputText id="logCaseEditRoutingOutTxt6" value="#{logCaseDataBean.routingVO.routedToName}" />					
						</m:div>
					</m:td>
					<m:td id="logCaseEditRoutingTd3">
						<m:div id="logCaseEditRoutingDiv10" styleClass="padb">
							<h:outputText id="logCaseEditRoutingOutTxt7" styleClass="outputLabel"								value="#{msg['label.case.routing.reportingUnit']}" />
							<m:br id="logCaseEditRoutingBr3"></m:br>
							
							<h:outputText id="logCaseEditRoutingOutTxt8" value="#{logCaseDataBean.caseDetailsVO.reportingUnit}" />
						</m:div>
					</m:td>
				</m:tr>
				<%--<m:tr id="logCaseEditRoutingTr2">
					<m:td id="logCaseEditRoutingTd4">
						<m:div id="logCaseEditRoutingDiv11" styleClass="padb">
							<h:outputText id="logCaseEditRoutingOutTxt9" value="#{msg['label.case.routing.show']}"								styleClass="outputLabel" />
							<m:br id="logCaseEditRoutingBr4"></m:br>
							
							<h:outputText id="logCaseEditRoutingOutTxt10" value="#{logCaseDataBean.routingVO.routedBy}" />
						</m:div>
					</m:td>
					<m:td id="logCaseEditRoutingTd5" rendered="#{logCaseDataBean.showWorkUnit}"> 
						<m:div id="logCaseEditRoutingDiv12" styleClass="padb">
							<h:outputText id="logCaseEditRoutingOutTxt11" value="#{msg['label.case.routing.workunitName']}"								styleClass="outputLabel" />
							<m:br id="logCaseEditRoutingBr5"></m:br>
							<h:outputText id="logCaseEditRoutingOutTxt12" value="#{logCaseDataBean.routingVO.workUnit}" />
						</m:div>
					</m:td>
					<m:td id="logCaseEditRoutingTd6">
						<m:div id="logCaseEditRoutingDiv13" styleClass="padb">
							<h:outputText id="logCaseEditRoutingOutTxt13" styleClass="outputLabel"								value="#{msg['label.case.routing.assignThisRecordTo']}" />
							<m:br id="logCaseEditRoutingBr6"></m:br>
							
							<h:outputText id="logCaseEditRoutingOutTxt14" value="#{logCaseDataBean.routingVO.assignedUserName}" rendered="#{!logCaseDataBean.showAssignedTo}" />
						</m:div>
					</m:td>
					
					<m:td id="logCaseEditRoutingTd7" rendered="#{logCaseDataBean.showUserDept}">
						<m:div id="logCaseEditRoutingDiv14" styleClass="padb" >
							<h:outputText id="logCaseEditRoutingOutTxt15" styleClass="outputLabel"								value="#{msg['label.case.routing.userDepartmentName']}" />
							<m:br id="logCaseEditRoutingBr7"></m:br>
							<h:outputText id="logCaseEditRoutingOutTxt16" value="#{logCaseDataBean.routingVO.userDeptName}" rendered="#{!logCaseDataBean.showUserDeptName}" />
						</m:div>
					</m:td>
				</m:tr>--%>
				<m:tr id="logCaseEditRoutingTr3">
					<m:td id="logCaseEditRoutingTd8">
						<m:div id="logCaseEditRoutingDiv15" styleClass="padb">
							<h:selectBooleanCheckbox id="watchlist_routing" disabled="true" 								value="#{logCaseDataBean.routingVO.addThisRecordToMyWatchlist}"/> &nbsp;&nbsp;
								<h:outputLabel id="logCaseEditRoutingOutTxt17"		for="watchlist_routing"		value="#{msg['label.case.routing.addThisRecordToMyWatchList']}"		 />
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
 <m:div id="viewRoutingAssignmentAuditDiv" rendered="#{logCaseDataBean.enableAuditLogForLogCase}">
			<f:subview id="routingViewRoutingAssignmentAudit">
	<%--CR_ESPRD00373565_LOGCASE_23JUL2010	<jsp:include page="inc_logCaseRoutingAudit.jsp" />--%>
											<audit:auditTableSet id="routingAuditId"
												value="#{logCaseDataBean.routingVO.auditKeyList}"
												numOfRecPerPage="10">
											</audit:auditTableSet>

			</f:subview>
</m:div><%--EOF CR_ESPRD00373565_LogCase_04AUG2010 --%>
		</m:div>
	</m:div>
</m:div>

