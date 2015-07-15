<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<portlet:defineObjects />
<script type="text/javascript">
function autoFocus() {
if(document.forms.length>0){
forms=document.forms;
for(j=0;j<forms.length;j++){
for(i=0;i<forms[j].length;i++){
                     if(!forms[j][i].readonly != undefined && forms[j][i].type != "hidden" && forms[j][i].disabled != true 
&& forms[j][i].style.display!= 'none' && forms[j][i].type!="submit" && forms[j][i].type!=undefined){
                           forms[j][i].focus();
                           return;
                    }
                 }
            }
        }
     }
</script>
<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_SearchGlobalCase"
	var="msg" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />
<f:view>
	<t:saveState id="CMGTTOMSS151" value="#{globalCaseSearchDataBean}"></t:saveState>
	<m:body onload="javascript:autoFocus();">
	<h:inputHidden id="GCSHelpFlagID" value="#{globalCaseSearchControllerBean.helpFlagSettings}"/>
	<h:inputHidden id="ValidValues" value="#{globalCaseSearchControllerBean.loadValidValues}"          rendered="#{globalCaseSearchDataBean.validValuesFlag}"/>

		<m:div id="wrapper">
			<m:div id="content">
				<m:div styleClass="floatContainer">
					<h:form id="GlobalCaseSearch">
						<m:div styleClass="moreInfoBar">
							<m:div styleClass="infoActions">
								<%--<h:outputLink id="PRGCMGTOLK21" value="#{msg['label.searchGlobalCase.linkCancel']}">
									<h:outputText id="PRGCMGTOT524" styleClass="strong"										value="#{msg['button.searchGlobalCase.cancel']}"></h:outputText>
								</h:outputLink>--%>															
							</m:div>
						</m:div>
						<h:messages showDetail="true" layout="table" id="caseSearchErrorMessage" showSummary="false"				styleClass="errorMessage" />
				<h:outputText id="PRGCMGTOT525" value ="#{msg['label.user.authentication']}" rendered="#{globalCaseSearchDataBean.authUser}" style="color: red"/>
						<m:div styleClass="strong">
							<m:br></m:br>
							<m:br></m:br>
							<m:section id="PROVIDERMMS20120731164811232" styleClass="otherbg">
								<m:legend>
									<f:verbatim>
										<h:outputText id="PRGCMGTOT526" 											value="#{msg['label.searchGlobalCase.searchForGlobalCase']}" />
									</f:verbatim>
								</m:legend>
								<m:div rendered="#{globalCaseSearchDataBean.showGlobalCaseSearchMsgFlag}">
								</m:div>
								<m:table id="PROVIDERMMT20120731164811233" cellspacing="0" width="95%">
									<m:tr>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL196" for="EntityID">
													<h:outputText id="PRGCMGTOT527"														value="#{msg['label.searchGlobalCase.entityID']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:inputText id="EntityID" size="10"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.entityId}" />
												<m:br />
                                       				<h:message id="PRGCMGTM78" for="EntityID"	styleClass="errorMessage" />						
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL197" for="CaseSearchEntityType">
													<h:outputText id="PRGCMGTOT528"														value="#{msg['label.searchGlobalCase.entityType']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:selectOneMenu id="CaseSearchEntityType"													valueChangeListener="#{globalCaseSearchControllerBean.entityTypeValueChange}"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.entityType}" onchange="this.form.submit()">
													
													<f:selectItems value="#{globalCaseSearchDataBean.entityType}" />
												</h:selectOneMenu>
												<m:br />
												<h:message id="PRGCMGTM79" for="CaseSearchEntityType"	styleClass="errorMessage" />
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL198" for="AssignedTo">
													<h:outputText id="PRGCMGTOT529"														value="#{msg['label.searchGlobalCase.assignedTo']}" />
												</h:outputLabel>
												<m:br />
												<h:selectOneMenu id="AssignedTo"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.assignedTo}">
													<f:selectItems
														value="#{globalCaseSearchDataBean.assignedTo}" />
												</h:selectOneMenu>
												<m:br />

											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL199" for="Status">
													<h:outputText id="PRGCMGTOT530"														value="#{msg['label.searchGlobalCase.status']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:selectOneMenu id="Status"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.status}">
													<f:selectItems value="#{globalCaseSearchDataBean.status}" />
												</h:selectOneMenu>

												<m:br></m:br>

											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL200" for="CreatedBy">
													<h:outputText id="PRGCMGTOT531"														value="#{msg['label.searchGlobalCase.createdBy']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:selectOneMenu id="CreatedBy"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.createdBy}">
													<f:selectItems
														value="#{globalCaseSearchDataBean.createdBy}" />
												</h:selectOneMenu>
												<m:br></m:br>

											</m:div>
										</m:td>
									</m:tr>
									<m:tr>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL201" for="CaseSearchEntityIDType">
													<h:outputText id="PRGCMGTOT532"														value="#{msg['label.searchGlobalCase.entityIDType']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:selectOneMenu id="CaseSearchEntityIDType"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.entityIDType}">
								
													<f:selectItems value="#{globalCaseSearchDataBean.entityIDType}" />
												</h:selectOneMenu>
												<m:br />
												<h:message id="PRGCMGTM80" for="CaseSearchEntityIDType"	styleClass="errorMessage" />
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL202" for="CaseRecordNumber">
													<h:outputText id="PRGCMGTOT533"														value="#{msg['label.searchGlobalCase.caseRecordNumber']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:inputText id="CaseRecordNumber" size="10" maxlength="80"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.caseRecordNumber}" />
												<m:br></m:br>
												<h:message id="PRGCMGTM81" for="CaseRecordNumber"	styleClass="errorMessage"    />											
											</m:div>
										</m:td>
									</m:tr>
										<m:tr>
											<m:td colspan="3">
											<m:div styleClass="padb" rendered="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.providerSortInd}">
												<h:outputLabel id="PRGCMGTOLL203" for="providerSortName">
													<h:outputText id="PRGCMGTOT534"														value="#{msg['label.searchGlobalCase.ProviderSortName']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:inputText id="providerSortName" size="53"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.providerSortName}" maxlength="35" />
												<m:br></m:br>
												<h:message id="PRGCMGTM82" for="providerSortName"styleClass="errorMessage"    />											
											</m:div>
										</m:td>
									</m:tr>
									<m:tr>
										<m:div styleClass="padb" rendered="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.memberInd}">
											<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL204" for="firstName">
													<h:outputText id="PRGCMGTOT535"														value="#{msg['label.searchGlobalCase.firstName']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:inputText id="firstName" size="10"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.firstName}" maxlength="25" />
												<m:br></m:br>
												<h:message id="PRGCMGTM83" for="firstName" styleClass="errorMessage"    />											
											</m:div>
										</m:td>
										<m:div styleClass="padb" rendered="#{!globalCaseSearchDataBean.caseRecordSearchCriteriaVO.TPLPolicyInd}">
										<m:td>
											
												<h:outputLabel id="PRGCMGTOLL205" for="middleName">
													<h:outputText id="PRGCMGTOT536"														value="#{msg['label.searchGlobalCase.middleName']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:inputText id="middleName" size="2"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.middleName}"  maxlength="25" />
												<m:br></m:br>
												<h:message id="PRGCMGTM84" for="middleName"styleClass="errorMessage"    />											
										</m:td>
										</m:div>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL206" for="LastName">
													<h:outputText id="PRGCMGTOT537"														value="#{msg['label.searchGlobalCase.LastName']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:inputText id="LastName" size="10"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.lastName}" maxlength="35" />
												<m:br></m:br>
												<h:message id="PRGCMGTM85" for="LastName"styleClass="errorMessage"    />											
											</m:div>
										</m:td>
										<m:td  >
											<m:div styleClass="padb" rendered="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.organizationNameInd}">
												<h:outputLabel id="PRGCMGTOLL207" for="OrganizationName">
														<h:outputText id="PRGCMGTOT538"														value="#{msg['label.searchGlobalCase.OrganizationName']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:inputText id="OrganizationName" size="10"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.organizationName}" maxlength="35" />
												<m:br></m:br>
												<h:message id="PRGCMGTM86" for="OrganizationName" styleClass="errorMessage"    />											
										</m:div>
										</m:td>
									</m:div>
									</m:tr>
									<m:tr>
										<m:td>
											<m:div styleClass="padb" rendered="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.carrierInd}">
												<h:outputLabel id="PRGCMGTOLL208" for="carrierName">
													<h:outputText id="PRGCMGTOT539"														value="#{msg['label.searchGlobalCase.carrierName']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:inputText id="carrierName" size="15" maxlength="40"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.carrierName}" />
												<m:br></m:br>
												<h:message id="PRGCMGTM87" for="carrierName"styleClass="errorMessage"/>											
											</m:div>
										</m:td>
											
								</m:tr>
								<%--Added for Trading Partner, for defect ESPRD00807684 starts--%>
								<m:tr>
									<m:div styleClass="padb" rendered="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.tradingPartnerInd}">
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL209"	for="CaseSearchTradingPartnerStatus">
													<h:outputText id="PRGCMGTOT540"		value="#{msg['label.searchGlobalCase.TradingPartnerStatus']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:selectOneMenu id="CaseSearchTradingPartnerStatus"  value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.tradingPartnerStatus}">
												<f:selectItems		value="#{globalCaseSearchDataBean.TPStatusList}" />
												</h:selectOneMenu>
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL210"	for="CaseSearchClassification">
													<h:outputText id="PRGCMGTOT541"		value="#{msg['label.searchGlobalCase.Classification']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:selectOneMenu id="CaseSearchClassification"  value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.classification}">
												<f:selectItems		value="#{globalCaseSearchDataBean.classficationList}" />
												</h:selectOneMenu>
											</m:div>
										</m:td>
										<m:td>
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOLL211" for="Name">
													<h:outputText id="PRGCMGTOT542"				value="#{msg['label.searchGlobalCase.Name']}" />
												</h:outputLabel>
												<m:br></m:br>
												<h:inputText id="Name" size="10"													value="#{globalCaseSearchDataBean.caseRecordSearchCriteriaVO.name}" maxlength="35" />
												<m:br></m:br>
												<h:message id="PRGCMGTM88" for="Name"styleClass="errorMessage"/>	
											</m:div>
										</m:td>
									</m:div>
								</m:tr>
								<%--Added for Trading Partner, for defectESPRD00807684 ends--%>
								</m:table>
								<m:table id="PROVIDERMMT20120731164811234" styleClass="tableBar" width="100%">
									<m:tr>
										<m:td styleClass="tableAction">
											<h:commandButton id="PRGCMGTCB16" styleClass="formBtn"												value="#{msg['button.searchGlobalCase.search']}"												action="#{globalCaseSearchControllerBean.searchGlobalCase}" />
											<h:commandButton id="PRGCMGTCB17" styleClass="formBtn"												value="#{msg['button.searchGlobalCase.reset']}"												action="#{globalCaseSearchControllerBean.resetGlobalSerachCase}" />
										</m:td>
									</m:tr>
								</m:table>
							</m:section>
						</m:div>
						<m:div>
							<m:br/>
							<f:subview id="contactManagemetCaseSearchResults">
								<jsp:include
									page="/jsp/globalCaseSearch/contactManagemetCaseSearchResults.jsp" />
							</f:subview>
							<m:br/>	<m:br/>
						</m:div>
						
						<m:div>
							<f:subview id="tplRecoveryCaseSearchResults">
								<jsp:include
									page="/jsp/globalCaseSearch/tplRecoveryCaseSearchResults.jsp" />
							</f:subview>
							<m:br/>	<m:br/>
						</m:div>
						
						<m:div>
							<f:subview id="tplHippCaseSearchResults">
								<jsp:include
									page="/jsp/globalCaseSearch/tplHippCaseSearchResults.jsp" />
							</f:subview>
						</m:div>
					</h:form>					
				</m:div>				
			</m:div>			
		</m:div>		
	</m:body>
</f:view>


