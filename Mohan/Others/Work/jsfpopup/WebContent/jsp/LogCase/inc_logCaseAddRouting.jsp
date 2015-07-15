<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<m:div>
	<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543--%>
	 
	<m:div styleClass="moreInfo" rendered="#{logCaseDataBean.showAddRoutingAssignment}">
		<m:div styleClass="moreInfoBar" rendered="#{!logCaseDataBean.renderAddEditRouting}">
			<m:div styleClass="infoTitle">
				<h:outputText id="PRGCMGTOT603"					value="#{msg['label.case.routing.newRoutingAssignment']}" />
			</m:div>
			<m:div styleClass="infoActions">
				<t:commandLink styleClass="strong" id="addRoutingId"
					action="#{logCaseControllerBean.saveRouting}"
					onmousedown="javascript:javascript:flagWarn=false;">
					<h:outputText id="PRGCMGTOT604" value="#{msg['label.case.save']}" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT605" escape="false" value="&nbsp;"/>
				<h:outputText id="PRGCMGTOT6051" value="|"/>
				<h:outputText id="PRGCMGTOT6052" escape="false" value="&nbsp;"/>
				<t:commandLink action="#{logCaseControllerBean.resetRoutingPage}"
					id="resetRoutingPageId"
					onmousedown="javascript:focusThis('logCaseAddRouting');javascript:flagWarn=false;">
					<h:outputText id="PRGCMGTOT606" value="#{msg['label.case.reset']}" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT607" escape="false" value="&nbsp;"/>
				<h:outputText id="PRGCMGTOT60712" value="|"/>
				<h:outputText id="PRGCMGTOT60723" escape="false" value="&nbsp;"/>
				<t:commandLink
					onmousedown="javascript:focusPage('caseRoutingHeader');"
					id="renderCancelRoutingInfoId"
					action="#{logCaseControllerBean.renderCancelRoutingInfo}">
					<h:outputText id="PRGCMGTOT608" value="#{msg['label.case.cancel']}" />
				</t:commandLink>
			</m:div>
		</m:div>
		<m:div id="logCaseAddEditRoutingDiv2" styleClass="moreInfoBar" rendered="#{logCaseDataBean.renderEditRouting}">
		<m:div id="logCaseAddEditRoutingDiv3" styleClass="infoTitle">
			<h:outputText id="logCaseAddEditRoutingOutTxt1" value="#{msg['label.case.routing.viewRoutingAssignment']}" 				styleClass="outputLabel"/>
		</m:div>
		<m:div id="logCaseAddEditRoutingDiv4" styleClass="infoActions">
			<t:commandLink action="#{logCaseControllerBean.renderCancelRoutingInfo}" id="logCaseAddEditRoutingLink1"  onmousedown="javascript:flagWarn=false;focusPage('caseRoutingHeader');"				value="#{msg['label.case.routing.close']}"/>
		</m:div>
	</m:div>
		<m:div styleClass="moreInfoContent">
	<%--Modified for defect ESPRD00446264 starts--%>
	<%--<m:div
			rendered="#{logCaseDataBean.showCaseRoutingMessages}"
			styleClass="msgBox">
				<h:messages layout="table" showDetail="true" styleClass="successMsg"					id="addRoutingSuccessMessage" showSummary="false">
				</h:messages>
		</m:div>--%>
		
		<%--Defect ESPRD00446264 Fix ends--%>
			<h:panelGroup id="assignThisRecordToOrDept">
			<m:div styleClass="width:100%">
				<m:table id="PROVIDERMMT20120731164811288" cellspacing="0" width="95%">
					<m:tr>
						<m:td>
							<m:div styleClass="padb">
								<h:outputText id="PRGCMGTOT609" value="#{msg['label.case.routing.createdBy']}"									styleClass="outputLabel" />
								<m:br/>
								<h:outputText id="PRGCMGTOT610" value="#{logCaseDataBean.routingVO.createdBy}" />
								<h:outputText id="PRGCMGTOT611" value="-#{logCaseDataBean.routingVO.createdByWorkUnitSK}" rendered="#{logCaseDataBean.routingVO.createdByWorkUnitSK !=null && logCaseDataBean.routingVO.createdByWorkUnitSK!=''}" />
								
							</m:div>
						</m:td>
						<m:td>
							<m:div styleClass="padb">
								<h:outputText id="PRGCMGTOT612" styleClass="outputLabel"									value="#{msg['label.case.routing.assignedTo']}" />
								<m:br/>
								<h:outputText id="PRGCMGTOT613" value="#{logCaseDataBean.routingVO.assignedTo}" />
								<%--<h:outputText id="PRGCMGTOT614" value="-#{logCaseDataBean.routingVO.assignThisRecordTo}" rendered="#{logCaseDataBean.routingVO.assignThisRecordTo !=null && logCaseDataBean.routingVO.assignThisRecordTo!=''}" />--%>
							</m:div>
						</m:td>
						<m:td>
							<m:div styleClass="padb">
								<h:outputText id="PRGCMGTOT615" styleClass="outputLabel"									value="#{msg['label.case.routing.reportingUnit']}" />
								<m:br/>
								<h:outputText id="PRGCMGTOT616" value="#{logCaseDataBean.routingVO.reportingUnit}" />
							</m:div>
						</m:td>
					</m:tr>
					<m:tr rendered="#{logCaseDataBean.renderAddRouting}">
						<m:td>
							<m:div styleClass="padb">
								<m:span styleClass="required">
										<h:outputText id="PRGCMGTOT617"
											value="#{msg['label.case.astrisk']}" />
									</m:span>
									<h:outputLabel id="PRGCMGTOLL314" for="show"
										value="#{msg['label.case.routing.show']}" />
									<m:br />
									<h:selectOneMenu id="show"
										value="#{logCaseDataBean.routingVO.show}"
										valueChangeListener="#{logCaseControllerBean.showUserDepartments}"
										onchange="javascript:flagWarn=false;this.form.submit();focusThis(this.id);">
					<%-- modified for defect :ESPRD00327996 --%>
									<f:selectItem itemValue=" "
														itemLabel="Please Select One" />
									<f:selectItems value="#{logCaseDataBean.routingShowList}" />
					<%-- EOF modification ESPRD00327996 --%>
								</h:selectOneMenu>
								<m:br />
							<h:message id="PRGCMGTM128" for="show" showDetail="true" styleClass="errorMessage"/>	
							</m:div>
						</m:td>
						<m:td rendered="#{logCaseDataBean.showBusinessUnitFields}">
							<%-- added for defect  ESPRD00327996--%>
							<m:div styleClass="padb">
								<m:span styleClass="required">
										<h:outputText id="PRGCMGTOT618"
											value="#{msg['label.case.astrisk']}" />
									</m:span>
									<h:outputLabel id="PRGCMGTOLL315" for="businessUnitdept"
										value="Business Unit" />
									<m:br />
									<h:selectOneMenu id="businessUnitdept"
										value="#{logCaseDataBean.routingVO.busineesUnit}"
										valueChangeListener="#{logCaseControllerBean.getDeptsForBsnsUnit}" immediate="true"
										onchange="javascript:flagWarn=false;focusThis(this.id); this.form.submit();">
										<f:selectItem itemValue=" " itemLabel="Please Select One" />
									<%--<f:selectItems value="#{logCaseDataBean.listOfAllDepartments}" />--%>
									<f:selectItems value="#{logCaseDataBean.businessUnitList}" />
								</h:selectOneMenu>
								<m:br />
							<h:message id="PRGCMGTM129" for="businessUnitdept" showDetail="true" styleClass="errorMessage"/>
							</m:div>
							<%--EOF ESPRD00327996 --%>
						</m:td>
						
						<m:td rendered="#{logCaseDataBean.showDepartments}">
							<m:div styleClass="padb">
								<m:span styleClass="required">
										<h:outputText id="PRGCMGTOT619"
											value="#{msg['label.case.astrisk']}" />
									</m:span>
									<h:outputLabel id="PRGCMGTOLL316" for="dept"
										value="#{msg['label.case.routing.workunitName']}" />
									<m:br />
									<h:selectOneMenu id="dept"
										value="#{logCaseDataBean.routingVO.deptName}"
										valueChangeListener="#{logCaseControllerBean.showUser}"
										onchange="javascript:flagWarn=false;this.form.submit();focusThis(this.id);javascript:addeditrow('CMlogCase');">
								<%-- modified for defect  ESPRD00327996	--%>	
								<f:selectItem itemValue=" "
														itemLabel="Please Select One" />
									<f:selectItems value="#{logCaseDataBean.workUnitList}" />
								<%--EOF modification ESPRD00327996 --%>
								</h:selectOneMenu>
								<m:br />
							<h:message id="PRGCMGTM130" for="dept" showDetail="true" styleClass="errorMessage"/>
							</m:div>
							</m:td>
						<m:td rendered="#{logCaseDataBean.showUsers}">
							<h:panelGroup id="assignThisRecordToId" >
							<m:div styleClass="padb">
										<h:outputLabel id="PRGCMGTOLL317" for="assgnRecToName"
											value="#{msg['label.case.routing.assignThisRecordTo']}" />
										<m:br />
										<h:selectOneMenu id="assgnRecToName"
											value="#{logCaseDataBean.routingVO.assignThisRecordTo}">
											<f:selectItem itemValue=" " itemLabel="Please Select One" />
									<f:selectItems value="#{logCaseDataBean.routingUserList}" />
								</h:selectOneMenu>
							</m:div>
							</h:panelGroup> 
							</m:td>	
							<m:td rendered="#{!logCaseDataBean.showDepartments ||(!logCaseDataBean.showUsers && logCaseDataBean.showBusinessUnitFields)}">
							<%-- &&  logCaseDataBean.showBusinessUnitFields==false --%>
							<m:div styleClass="padb">
								<m:span styleClass="required">
										<h:outputText id="PRGCMGTOT620"
											value="#{msg['label.case.astrisk']}" />
									</m:span>
									<h:outputLabel id="PRGCMGTOLL318" for="assgnRecTo"
										value="#{msg['label.case.routing.assignThisRecordTo']}" />
									<m:br />
									<h:selectOneMenu id="assgnRecTo"
										value="#{logCaseDataBean.routingVO.assignThisRecordTo}"
										valueChangeListener="#{logCaseControllerBean.showDept}"
										onchange="javascript:flagWarn=false;focusThis(this.id);javascript:addeditrow('CMlogCase');this.form.submit()">
										<f:selectItem itemValue=" " itemLabel="Please Select One" />
									<f:selectItems value="#{logCaseDataBean.userOfSameBU}" /> 
								</h:selectOneMenu>
									<m:br />
									<h:message id="PRGCMGTM131" for="assgnRecTo" showDetail="true"
										styleClass="errorMessage" />
							</m:div>
						</m:td>	
							<m:td rendered="#{logCaseDataBean.showUserDepartments}">
							<h:panelGroup id="userworkunitNameId" > 
							<m:div styleClass="padb">
								<m:span styleClass="required">
											<h:outputText id="PRGCMGTOT621"
												value="#{msg['label.case.astrisk']}" />
										</m:span>
										<h:outputLabel id="PRGCMGTOLL319" for="useDept"
											value="#{msg['label.case.routing.userworkunitName']}" />
										<m:br />
										<h:selectOneMenu id="useDept"
											value="#{logCaseDataBean.routingVO.userDepartment}"
											disabled="#{logCaseDataBean.disableRoutingUserworkunitName}">
											<f:selectItem itemValue=" " itemLabel="Please Select One" />
									<f:selectItems value="#{logCaseDataBean.routingDeptList}" />
								</h:selectOneMenu>
							<m:br />
										<h:message id="PRGCMGTM132" for="useDept" showDetail="true"
											styleClass="errorMessage" />
									</m:div>
								</h:panelGroup>
							</m:td>
						</m:tr>
						<m:tr>
							<m:td>
								<m:div styleClass="padb">
									<h:selectBooleanCheckbox id="watchlist"
										disabled="#{logCaseDataBean.renderEditRouting}"
										value="#{logCaseDataBean.routingVO.addThisRecordToMyWatchlist}" />
								&nbsp;&nbsp;
									<h:outputLabel id="PRGCMGTOLL320" for="watchlist"
										value="#{msg['label.case.routing.addThisRecordToMyWatchList']}" />
							</m:div>
						</m:td>
					</m:tr>
				</m:table>
			</m:div>
</h:panelGroup>	
		</m:div>
	</m:div>
</m:div>
