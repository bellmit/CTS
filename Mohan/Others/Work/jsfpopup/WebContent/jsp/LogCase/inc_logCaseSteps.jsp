<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />
<script type="text/javascript">

function enableDisableAlertBasedOnAndAlertDays(mode){

		var notifyViaAlertsIDObj;
		var alertBasedOnIDObj;
		var sendAlertDaysIDObj;
		var caseStepsNotifyAltrsDisableFieldsIDObj;
			
			if(mode == "Add" || mode == 'Add'){
					notifyViaAlertsIDObj = findObjectByPartOfID('LOGCASESTEPSALERT0');
					alertBasedOnIDObj = findObjectByPartOfID('alertBasedOn');
					sendAlertDaysIDObj = findObjectByPartOfID('sendAlertDays');
					caseStepsNotifyAltrsDisableFieldsIDObj = findObjectByPartOfID('caseStepsNotifyAltrsDisableFieldsID');
			}else{
					notifyViaAlertsIDObj = findObjectByPartOfID('LOGCASESTEPSALERT1');
					alertBasedOnIDObj = findObjectByPartOfID('alertBasedOn2');
					sendAlertDaysIDObj = findObjectByPartOfID('sendAlertDays2');
					caseStepsNotifyAltrsDisableFieldsIDObj = findObjectByPartOfID('caseStepsNotifyAltrsDisableFieldsID');

			}

		//alert(notifyViaAlertsIDObj.value);
		if(notifyViaAlertsIDObj!=null || notifyViaAlertsIDObj!= 'null'){
			if(	notifyViaAlertsIDObj.value == null || notifyViaAlertsIDObj.value == 'null' || notifyViaAlertsIDObj.value == "" || notifyViaAlertsIDObj.value==''){
				//				disable all dependent fields
			//	alert("selected empty value");
				try{
				alertBasedOnIDObj.value="";
				alertBasedOnIDObj.disabled = "none";
				}catch(e){}

				try{
				sendAlertDaysIDObj.value="";
				sendAlertDaysIDObj.disabled = "none";
				}catch(e){}
				caseStepsNotifyAltrsDisableFieldsIDObj.value = true;
			}else{
				// enable all dependent fields
				try{
				//alertBasedOnIDObj.value="";
				alertBasedOnIDObj.disabled = "";
				}catch(e){}

				try{
				//sendAlertDaysIDObj.value="";
				sendAlertDaysIDObj.disabled = "";
				}catch(e){}
				caseStepsNotifyAltrsDisableFieldsIDObj.value = false;
			}
		}

}

</script>
<m:div id="logCaseStepsMainDivSectionID">
	<m:div id="logCaseStepsMainDivSectionID2">
		<m:section id="logCaseStepsMSectionID" styleClass="casebg">
			<m:legend id="logCaseStepsLegendID">
				<h:outputLink					onclick="javascript:flagWarn=false;javascript:setHiddenValue('CMlogCase:caseSteps:caseStepsHiddenID','minus');	 					 toggle('showhide_caseSteps',2);						 plusMinusToggle('showhide_caseSteps',this,'CMlogCase:caseSteps:caseStepsHiddenID');						 return false;"					id="plusMinus_caseStepsFalse" styleClass="plus">
					<h:outputText id="caseSteps_text"						value="#{msg['link.case.casesteps']}" />
				</h:outputLink>
				<h:inputHidden id="caseStepsHiddenID"					value="#{logCaseDataBean.caseStepsHidden}" />
			<h:inputHidden id="caseStepsNotifyAltrsDisableFieldsID" value="#{logCaseDataBean.disableFields}"></h:inputHidden>
			</m:legend>
			<m:div styleClass="msgBox" id="logcaseAddCaseStepsSubDivID201"
				rendered="#{logCaseDataBean.showCaseStepsMessages}">
				<h:outputText value="#{ent['label-sw-success']}"
					id="addStepErrorMessage" />
			</m:div>
			
			
			<m:div sid="showhide_caseSteps"> 
				<%--Defect ESPRD00540071  start--%>
			<m:div styleClass="msgBox" id="logCaseStepsMainDivSectionIDDel1"
				rendered="#{logCaseDataBean.showCaseStepsDelteMessage}">
				<h:outputText value="#{ent['err-sw-record-delete-success']}" id="deleteStepErrorMessage"/>
			</m:div>
			<%--Defect ESPRD00540071  end--%>
				<m:table id="logCaseStepsMtable001" width="100%">
					<m:tr id="logCaseStepsMtable001TR01">
						<m:td id="logCaseStepsMtable001TR01TD01" styleClass="rightCell">
							<h:commandButton
								rendered="#{!logCaseDataBean.disableAddCaseSteps && !logCaseDataBean.disableScreenField}"
								style="color:#fff; background-color:#518ACD; border-color:#ccc #000 #000 #ccc; font-size:11px; font-weight:bold;" 
								id="addCaseStepsId"								
								action="#{logCaseControllerBean.renderAddCaseStepsPage}"
								onmousedown="javascript:flagWarn=false;focusPage('addCaseStepFocusPage');focusThis('order');"								
								value="#{msg['label.case.caseSteps.addCaseSteps']}"	/>
							<h:commandButton
								rendered="#{logCaseDataBean.disableAddCaseSteps || logCaseDataBean.disableScreenField}"
								style="color:GrayText; background-color:#518ACD; border-color:#ccc #000 #000 #ccc; font-size:11px; font-weight:bold;" 
								id="addCaseStepsId11"								
								action="#{logCaseControllerBean.renderAddCaseStepsPage}"
								onmousedown="javascript:flagWarn=false;focusPage('addCaseStepFocusPage');focusThis('order');"								
								value="#{msg['label.case.caseSteps.addCaseSteps']}"	
								disabled="#{logCaseDataBean.disableAddCaseSteps || logCaseDataBean.disableScreenField}" />
						</m:td>
					</m:tr>
				</m:table>
				<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1545--%>

				<t:dataTable  value="#{logCaseDataBean.caseStepsVOList}" columnClasses="columnClass"
					rendered="#{logCaseDataBean.showCaseStepsDataTable}" footerClass="footerClass"
					first="#{logCaseDataBean.caseStepsRowIndex}"
					var="caseStepsSpanResult" styleClass="dataTable"
					width="100%" border="0" headerClass="headerClass" 
					rowClasses="row_alt,row" rows="10" id="caseStepsTable"
					rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
					rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
					rowOnClick="javascript:childNodes[0].lastChild.click();"
					onmousedown="javascript:flagWarn=false;focusThis('order2');focusPage('editCaseStepFocusPage');"  rowIndexVar="rowIndex">
					<t:column id="caseStepscolumn1">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid001" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLable" value="#{msg['label.case.caseSteps.order']}"									for="caseStepsOrderCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup001">
									<t:div id="CaseStepsTDiv001" styleClass="alignLeft">
									<t:commandLink id="caseStepsOrderCommandLink1" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}"										rendered="#{logCaseDataBean.imageRender != 'caseStepsOrderCommandLink1'}">
										<m:div id="caseStepsGIFImag01" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_Order" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv002" styleClass="alignLeft">
									<t:commandLink id="caseStepsOrderCommandLink2" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}"										rendered="#{logCaseDataBean.imageRender != 'caseStepsOrderCommandLink2'}">
										<m:div  id="caseStepsGIFImag11" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_Order" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<t:commandLink id="caseStepsViewCaseStepsPage" action="#{logCaseControllerBean.viewCaseStepsPage}"  onclick="javascript:flagWarn=false;">
							<f:param id="caseStepsRowIndexID" name="rowindex" value="#{rowIndex}" />
							<h:outputText id="CaseStepsOrdeOutTxtLink" value="#{caseStepsSpanResult.order}" />
						</t:commandLink>	
					</t:column>

					<t:column id="caseStepscolumn2">
						<f:facet  name="header">
							<h:panelGrid id="casestepsPanelGrid002" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLable2"									value="#{msg['label.case.caseSteps.description']}"									for="caseStepsDescCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup002">
									<t:div id="CaseStepsTDiv003" styleClass="alignLeft">
									<t:commandLink id="caseStepsDescCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}"  onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsDescCommandLink1'}">
										<m:div id="caseStepsGIFImg02" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_Desc" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv004" styleClass="alignLeft">
									<t:commandLink id="caseStepsDescCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}"  onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsDescCommandLink2'}">
										<m:div id="caseStepsGIFImg22" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_Desc" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="caseStepsDescOutTxt" value="#{caseStepsSpanResult.caseStepsDescription}" />
					</t:column>

					<t:column id="caseStepscolumn3">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid003" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLabl3" value="#{msg['label.case.caseSteps.routeTo']}"									for="caseStepsRoutedToCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup003">
									<t:div id="CaseStepsTDiv005" styleClass="alignLeft">
									<t:commandLink id="caseStepsRoutedToCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsRoutedToCommandLink1'}">
										<m:div id="caseStepsGIFImg03" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_RoutedTo" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv006" styleClass="alignLeft">
									<t:commandLink id="caseStepsRoutedToCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsRoutedToCommandLink2'}">
										<m:div id="caseStepsGIFImg33" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_RoutedTo" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="casStepsRouteToDescriptionoutText" value="#{caseStepsSpanResult.routeToDescription}" />
					</t:column>

					<t:column id="caseStepscolumn4">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid004" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLable4"									value="#{msg['label.case.caseSteps.expectedDaysToComplete']}"									for="caseStepsExpToCompCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup004">
									<t:div id="CaseStepsTDiv007" styleClass="alignLeft">
								
									<t:commandLink id="caseStepsExpToCompCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsExpToCompCommandLink1'}">
										<m:div id="caseStepsGIFImg04" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_ExpToComp" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv008" styleClass="alignLeft">
									<t:commandLink id="caseStepsExpToCompCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsExpToCompCommandLink2'}">
										<m:div id="caseStepsGIFImg44" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_ExpToComp" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="caseStepsexpectedDaysToCompleteOutTxt"							value="#{caseStepsSpanResult.expectedDaysToComplete}" />
					</t:column>

					<t:column id="caseStepscolumn5">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid005" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLable5"									value="#{msg['label.case.caseSteps.completionBasedOn']}"									for="caseStepsComBasedOnCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup005">
									<t:div id="CaseStepsTDiv009" styleClass="alignLeft">
									<t:commandLink id="caseStepsComBasedOnCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}"  onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsComBasedOnCommandLink1'}">
										<m:div id="caseStepsGIFImg05" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_ComBasedOn" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv010" styleClass="alignLeft">
									<t:commandLink id="caseStepsComBasedOnCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsComBasedOnCommandLink2'}">
										<m:div id="caseStepsGIFImg55" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_ComBasedOn" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="caseStepsCmplBasdOnDescOutTxt" value="#{caseStepsSpanResult.completionBasedOnDescription}" />
					</t:column>
					<t:column id="caseStepscolumn6">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid006" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLabl6" value="#{msg['label.case.caseSteps.status']}"									for="caseStepsStatusCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup006">
									<t:div id="CaseStepsTDiv011" styleClass="alignLeft">
									<t:commandLink id="caseStepsStatusCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsStatusCommandLink1'}">
										<m:div id="caseStepsGIFImg06" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_Status" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv012" styleClass="alignLeft">
									<t:commandLink id="caseStepsStatusCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsStatusCommandLink2'}">
										<m:div  id="caseStepsGIFImg66" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_Status" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="caseStepsStatusDescOutTxt" value="#{caseStepsSpanResult.statusDescription}" />
					</t:column>
					<t:column id="caseStepscolumn7">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid007" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLabl7"									value="#{msg['label.case.caseSteps.dateStarted']}"									for="caseStepsDateStartedCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup007">
									<t:div id="CaseStepsTDiv013" styleClass="alignLeft">
									<t:commandLink id="caseStepsDateStartedCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsDateStartedCommandLink1'}">
										<m:div id="caseStepsGIFImg07" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_DateStarted" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv014" styleClass="alignLeft">
									<t:commandLink id="caseStepsDateStartedCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsDateStartedCommandLink2'}">
										<m:div id="caseStepsGIFImg77" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_DateStarted" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="caseStepsdateStartedStrOutTxt" value="#{caseStepsSpanResult.dateStartedStr}" />
					</t:column>
					<t:column id="caseStepscolumn8">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid008" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLable8"									value="#{msg['label.case.caseSteps.expectedCompletionDate']}"									for="caseStepsExpCompDateCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup008">
									<t:div id="CaseStepsTDiv015" styleClass="alignLeft">
									<t:commandLink id="caseStepsExpCompDateCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsExpCompDateCommandLink1'}">
										<m:div id="caseStepsGIFImg08" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_ExpCompDate" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv016" styleClass="alignLeft">
									<t:commandLink id="caseStepsExpCompDateCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsExpCompDateCommandLink2'}">
										<m:div id="caseStepsGIFImg88" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_ExpCompDate" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="caseStepsexpCompDateStrOuttxt"							value="#{caseStepsSpanResult.expectedCompletionDateStr}" />
					</t:column>

					<t:column id="caseStepscolumn9">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid009" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLable9"									value="#{msg['label.case.caseSteps.completionDate']}"									for="caseStepsCompDateCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup009">
									<t:div id="CaseStepsTDiv017" styleClass="alignLeft">
									<t:commandLink id="caseStepsCompDateCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsCompDateCommandLink2'}">
										<m:div id="caseStepsGIFImg09" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_CompDate" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv018" styleClass="alignLeft">
									<t:commandLink id="caseStepsCompDateCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsCompDateCommandLink2'}">
										<m:div id="caseStepsGIFImg99" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_CompDate" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="caseStepscompletionDateStrOutTxt" value="#{caseStepsSpanResult.completionDateStr}" />
					</t:column>

					<t:column id="caseStepscolumn10">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid010" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLable10"									value="#{msg['label.case.caseSteps.notifyViaAlert']}"									for="caseStepsNotifyAlertCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup010">
									<t:div id="CaseStepsTDiv019" styleClass="alignLeft">
									<t:commandLink id="caseStepsNotifyAlertCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsNotifyAlertCommandLink1'}">
										<m:div id="caseStepsGIFImg001" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_NotifyAlert" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv020" styleClass="alignLeft">
									<t:commandLink id="caseStepsNotifyAlertCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsNotifyAlertCommandLink2'}">
										<m:div id="caseStepsGIFImg011" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_NotifyAlert" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="caseStepsNotfyAlrtDescOutTxt" value="#{caseStepsSpanResult.notifyAlertDescription}" />
					</t:column>
					<t:column id="caseStepscolumn11">
						<f:facet name="header">
							<h:panelGrid id="casestepsPanelGrid011" columns="2">
								<h:outputLabel id="caseStepsOrderCommandLinkLable11" value="#{msg['label.case.caseSteps.template']}"									for="caseStepsTemplateCommandLink1" />
								<h:panelGroup id="casestepsPanelGroup011">
									<t:div id="CaseStepsTDiv021" styleClass="alignLeft">
									<t:commandLink id="caseStepsTemplateCommandLink1"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsTemplateCommandLink1'}">
										<m:div id="caseStepsGIFImg002" title="#{msg['alt.for.ascending']}"
											styleClass="sort_asc" />
										<f:attribute name="columnName" value="caseSteps_Template" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									</t:div>
									<t:div id="CaseStepsTDiv022" styleClass="alignLeft">
									<t:commandLink id="caseStepsTemplateCommandLink2"										styleClass="clStyle"										actionListener="#{logCaseControllerBean.sortCaseSteps}" onmousedown="javascript:flagWarn=false;focusPage('caseStepsHeader');"										rendered="#{logCaseDataBean.imageRender != 'caseStepsTemplateCommandLink2'}">
										<m:div id="caseStepsGIFImg022" title="#{msg['alt.for.descending']}"
											styleClass="sort_desc" />
										<f:attribute name="columnName" value="caseSteps_Template" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="caseStepsTempletOutTxt" value="#{caseStepsSpanResult.template}" />
					</t:column>
				</t:dataTable>
				<%-- Modified for defect ESPRD00668535--%>
				<t:dataScroller for="caseStepsTable" paginator="true"
					onclick="javascript:flagWarn=false;focusPage('caseStepsHeader');"
					paginatorActiveColumnStyle='font-weight:bold;'
					paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"
					pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"
					lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
					styleClass="dataScroller" id="caseStepsTableDataScrolerID">
					<f:facet name="previous">
						<h:outputText styleClass="underline"
							id="caseStepsTblDtaScrolroutTxt1"
							value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"></h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText styleClass="underline"
							id="caseStepsTblDtaScrolroutTxt2"
							value=" #{msg['label.greaterthan']} "
							rendered="#{pageIndex < pageCount}"></h:outputText>
					</f:facet>
					<h:outputText id="PROVIDEROLT20120731164811306" rendered="#{rowsCount > 0}"
						id="caseStepsTblDtaScrolroutTxt3"
						value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
						styleClass="dataScrollerText" />
				</t:dataScroller>

				<m:br id="caseStepsBR12" />
				<h:dataTable var="dummycaseSteps"					rendered="#{!logCaseDataBean.showCaseStepsDataTable}"					styleClass="dataTable" cellspacing="0" width="100%" border="0"					headerClass="tableHead" rowClasses="rowone,row_alt"					id="caseStepsdummyTable">
					<t:column id="dummycaseStepscolumn1">
						<f:facet name="header">
							<h:outputText id="caseStepsorderoutTxt1"  value="#{msg['label.case.caseSteps.order']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn2">
						<f:facet name="header">
							<h:outputText id="caseStepdescriptionoutTxt1"  value="#{msg['label.case.caseSteps.description']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn3">
						<f:facet name="header">
							<h:outputText id="caseSteprouteToTxt1" value="#{msg['label.case.caseSteps.routeTo']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn4">
						<f:facet name="header">
							<h:outputText id="caseStepexpectedDaysToCompleteTxt1"								value="#{msg['label.case.caseSteps.expectedDaysToComplete']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn5">
						<f:facet name="header">
							<h:outputText id="caseStepcompletionBasedOnoutTxt1"								value="#{msg['label.case.caseSteps.completionBasedOn']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn6">
						<f:facet name="header">
							<h:outputText id="caseStepstatusoutTxt1" value="#{msg['label.case.caseSteps.status']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn7">
						<f:facet name="header">
							<h:outputText id="caseStepdateStartedoutTxt1" value="#{msg['label.case.caseSteps.dateStarted']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn8">
						<f:facet name="header">
							<h:outputText id="caseStepexpectedCompletionDateoutTxt1"								value="#{msg['label.case.caseSteps.expectedCompletionDate']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn9">
						<f:facet name="header">
							<h:outputText id="caseStepcompletionDateoutTxt1"								value="#{msg['label.case.caseSteps.completionDate']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn10">
						<f:facet name="header">
							<h:outputText id="caseStepnotifyViaAlertoutTxt1"								value="#{msg['label.case.caseSteps.notifyViaAlert']}" />
						</f:facet>
					</t:column>
					<t:column id="dummycaseStepscolumn11">
						<f:facet name="header">
							<h:outputText id="caseSteptemplateoutTxt1" value="#{msg['label.case.caseSteps.template']}" />
						</f:facet>
					</t:column>
				</h:dataTable>
				<m:table id="mTablShowCaseStepsDataTable" styleClass="dataTable" width="100%" border="0"
					rendered="#{!logCaseDataBean.showCaseStepsDataTable}">
					<m:tr id="mTablShowCaseStepsDataTableTR1">
						<m:td id="mTablShowCaseStepsDataTableTR1TD1" align="center">
							<h:outputText id="casestepsDataTableNodataTxt" value="#{msg['value.noData']}" />
						</m:td>
					</m:tr>
				</m:table>
				<m:br id="caseStepsBR13" />
				<m:div id="CaseStepsMDivClear1" styleClass="clearl">
				</m:div>
				
				<m:anchor name="addCaseStepFocusPage"></m:anchor>				
				<m:div id="newCaseStep"
					rendered="#{logCaseDataBean.showAddCaseSteps}">
					<f:subview id="addCaseSteps">
					<jsp:include page="inc_logCaseAddCaseSteps.jsp" />
					</f:subview>
				</m:div>
				<m:br id="caseStepsBR14" />
				<m:div id="CaseStepsMDivClear2" styleClass="clearl">
				</m:div>
				<m:anchor name="editCaseStepFocusPage"></m:anchor>
				<m:div id="editCaseStep"
					rendered="#{logCaseDataBean.showEditCaseSteps}">
					<f:subview id="editCaseSteps">
					<jsp:include page="inc_logCaseEditCaseSteps.jsp" />
					</f:subview>
				</m:div>
			</m:div>
		</m:section>
	</m:div>
</m:div>
