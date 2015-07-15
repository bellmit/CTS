<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/jsp/appeals/AppealDetails.java" --%><%-- /jsf:pagecode --%>
<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<portlet:defineObjects />
<jsp:include page="/jsp/script/scripts.jsp" />



<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactAppealsMaintenance"
	var="msg" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />
<script type="text/javascript">

	/*This method finds an object from the DOM by acception a part of it's ID as parameter.*/
    function findObjectByPartOfIDForAppeal(partOfID)
	{
		for(i=0; i<document.forms.length; i++)
		{
			for(j=0; j<document.forms[i].elements.length; j++)
			{
				var idValue = document.forms[i].elements[j].id;
				if(idValue.indexOf(partOfID)!=-1)
				{
					G_isFirstTime = false;
					G_countObject = document.forms[i].elements[j];
					return document.forms[i].elements[j];
				}
			}
		}		
		return null;
	}

	function renderAudit(id) 
	{ 
		var hiddenValue = document.getElementById('view<portlet:namespace/>:addNewAppealsViewForm:auditlogDetails:'+id);
	    
		if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
			hideMe('audit_plus');
		} else if ((hiddenValue.value == 'false')) 
		{
			hideMe('audit_plus');
		} 
			
	}
	function renderAdminHearingAudit(id) 
	{ 
		var hiddenValue = document.getElementById('view<portlet:namespace/>:addNewAppealsViewForm:administrativeHearing:editAdministrativeHearing:adminHearAuditlogDetails:'+id);
	    
		if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
			hideMe('admin_hear_audit_plus');
		} else if ((hiddenValue.value == 'false')) 
		{
			hideMe('admin_hear_audit_plus');
		} 
			
	}
	
	
	function saDetailChangeAction(e)
	{		
	 if(e != null ){
	  if(e.keyCode == 13 || e.keyCode == 9) {	 	   
		 var hidButtonForSADetail = findObjectByPartOfID('submitSADetails');
	   	 hidButtonForSADetail.click();
		 return false;
		 }else {
		  return true;
		 }
	 }
	}
	
	function saAction(e1)
	{		
		 var hidButtonSADetail = findObjectByPartOfIDForAppeal('submitSADetails');
	   	 hidButtonSADetail.click();	   	 
	}
	function TCNActionblur()
	{
		var submitProvTCNDetails=findObjectByPartOfIDForAppeal('tcnCmdbuttonlinkAppealID');
		submitProvTCNDetails.click();
	}
	/*Added for CR Cursor focus starts*/
		var thisForm = 'view<portlet:namespace/>:addNewAppealsViewForm';
		function focusThis(id) { 
   			document.getElementById(thisForm+':focusId').value=id;  			
	   	}						
		if (window.addEventListener) //DOM method for binding an event
            	window.addEventListener("load", onLoadFunctions, false);
      	else if (window.attachEvent) //IE exclusive method for binding an event
            	window.attachEvent("onload", onLoadFunctions);
      	else if (document.getElementById) //support older modern browsers
           		window.onload=onLoadFunctions;		
		function onLoadFunctions() {
			focusOnLoad();	
		}
		function focusOnLoad() {	
			//starting of java script code for calender in mozilla
			//find the user browser
			var brow=(navigator.appName);

			if(brow == 'Microsoft Internet Explorer'){
				//alert("its internet explorer");
			}else if(brow == 'Netscape'){
				//alert("its Mozilla");
				jscalendarInit();
			}
			//end of java script code for calender in mozilla	
			if(document.getElementById(thisForm+':focusId').value==''){
				document.getElementById('view<portlet:namespace/>:addNewAppealsViewForm:appealDetails_Type').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='adminHearingButton'){
				document.getElementById('view<portlet:namespace/>:addNewAppealsViewForm:administrativeHearingSubViewId:adminHearingButton').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='adminHearingTable'){
				document.getElementById('view<portlet:namespace/>:addNewAppealsViewForm:administrativeHearingSubViewId:adminHearingTable').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='add_AdminHrngDT'){
				document.getElementById('view<portlet:namespace/>:addNewAppealsViewForm:administrativeHearingSubViewId:addAdminHearingFlagID:add_AdminHrngDT').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='edit_AdminHrngDT'){
				document.getElementById('view<portlet:namespace/>:addNewAppealsViewForm:administrativeHearingSubViewId:editAdminHearingFlagID:edit_AdminHrngDT').focus();
			}  
   			var focusPage = '#'+document.getElementById(thisForm+':focusId').value;
			if(focusPage != '' && focusPage != '#') {				
				document.location.href=focusPage;
			}
			<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
			<%--var rightClickInd=document.getElementById(thisForm+':hyperlinkAppealID').value;			
			if(rightClickInd!=null && rightClickInd!=""){
				document.getElementById(thisForm+':hyperlinkAppealID').value="";
				flagWarn=false;			
				document.getElementById(thisForm+':'+rightClickInd).click();
			}--%>
   		}
   		/*Added for CR Cursor focus ends*/  
	</script>

<f:view>
	<%--COMMENTED FOR DEFECT ESPRD00682817 STARTS--%>
	<%-- <t:saveState id="CMGTTOMSS1" value="#{appealDataBean}"></t:saveState>--%>
	<%--COMMENTED FOR DEFECT ESPRD00682817 ENDS--%>
	<t:saveState id="CMGTTOMSS2"
		value="#{appealDataBean.showMemberInfoLabelFlag}"></t:saveState>
	<t:saveState id="CMGTTOMSS3"
		value="#{appealDataBean.showProviderInfoLabelFlag}"></t:saveState>
	<t:saveState id="CMGTTOMSS4" value="#{appealDataBean.smallSaveCount}"></t:saveState>

	<f:subview id="provService">
		<jsp:include page="/jsp/script/scripts.jsp" />
	</f:subview>

	<body
		onload="warnBeforeCancel();renderAudit('audit_open');renderAdminHearingAudit('admin_hear_audit_open')">

	<h:inputHidden id="PRGCMGTIH1"
		value="#{appealControllerBean.addAppealsCritria}"></h:inputHidden>
	<h:inputHidden id="PRGCMGTIH2"
		value="#{appealControllerBean.link2Show}"></h:inputHidden>

	<h:inputHidden id="userHavingUpdateRole"
		value="#{appealDataBean.controlRequired}" />
	<h:inputHidden id="PRGCMGTIH3"
		value="#{appealControllerBean.loadValidValues}"
		rendered="#{appealDataBean.validValueFlag}"></h:inputHidden>
	<h:form id="addNewAppealsViewForm"
		onclick="javascript:setformId('addNewAppealsViewForm');">
		<h:inputHidden id="HDNENTITYID" value="#{appealDataBean.commonCMCaseDetailsVO.entityID}" />
		<h:inputHidden id="HDNENTITYTYPE" value="#{appealDataBean.commonCMCaseDetailsVO.entityType}" />
		<h:inputHidden id="HDNENTITYNAME" value="#{appealDataBean.commonCMCaseDetailsVO.name}" />
		<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
		<%--<h:inputHidden id="hyperlinkAppealID" value="#{appealDataBean.rightClickIndicator}"></h:inputHidden>--%>
		<m:div id="appealDtlsMainDiv01" styleClass="floatContainer">
			<m:div id="appealDtlsMainDiv02" style="width:100%">
				<h:inputHidden id="internalSubmit" value=" " />
				<h:inputHidden id="warnBeforeExitId"
					value="#{appealDataBean.warnBeforeExitStatus}"></h:inputHidden>
				<h:inputHidden id="smallSaveCount"
					value="#{appealDataBean.smallSaveCount}"></h:inputHidden>
				<h:inputHidden id="focusId"
					value="#{appealDataBean.cursorFocusValue}"></h:inputHidden>
				 <%--<m:div styleClass="moreInfoBar">
					<m:div styleClass="actions">
						<m:table id="PROVIDERMMT201207311648111" styleClass="tableBar" width="95%">
							<m:tr>
								<m:td>
									<m:div styleClass="actionBarRequired">
										<m:span styleClass="required">
											<h:outputText id="PRGCMGTOT1"
												value="#{msg['msg.appeals.required']}" />
										</m:span>
									</m:div>
								</m:td>
								<m:td align="right">
									<m:div styleClass="infoActions">
									<h:commandLink id="PRGCMGTCL1" styleClass="strong"
										onmousedown="javascript:flagWarn=false;"
										action="#{appealControllerBean.saveAppeal}"
										rendered="#{!appealDataBean.disableSaveAppeal}">
										<h:outputText id="PRGCMGTOT2"
											value="#{msg['label.appeals.save']}" />
									</h:commandLink>
									<h:outputText id="PRGCMGTOT3"
										value="#{msg['label.appeals.save']}"
										rendered="#{appealDataBean.disableSaveAppeal}" />
									<h:outputText id="PRGCMGTOT4"
										value="#{msg['label.appeals.pipe']}" />


									<h:commandLink id="PRGCMGTCL2"
										action="#{appealControllerBean.resetAppeals}"
										onmousedown="javascript:flagWarn=false;"
										rendered="#{!appealDataBean.disableResetAppeal}">
										<h:outputText id="PRGCMGTOT5"
											value="#{msg['label.appeals.reset']}" />
									</h:commandLink>
									<h:outputText id="PRGCMGTOT6"
										value="#{msg['label.appeals.reset']}"
										rendered="#{appealDataBean.disableResetAppeal}" />
									<h:outputText id="PRGCMGTOT7"
										value="#{msg['label.appeals.pipe']}" />--%>

									<%-- ESPRD00509203_ProviderAppeals_03AUG2010--%>
									<%-- <h:commandLink id="PRGCMGTCL3"
										action="#{appealControllerBean.enableAppealDetailAudit}"
										onmousedown="javascript:flagWarn=false;">
										<h:outputText id="PRGCMGTOT8" value="Audit Log" />
									</h:commandLink>
									<h:outputText id="PRGCMGTOT9"
										value="#{msg['label.appeals.pipe']}" />--%>

									<%--EOF ESPRD00509203_ProviderAppeals_03AUG2010 --%>


									<%--<h:commandLink id="PRGCMGTCL4"
										action="#{logCaseControllerBean.submitCaseDetails}">
										<h:outputText id="PRGCMGTOT10"
											value="#{msg['label.appeals.cancel']}" />
										<f:param id="ipcAction2"
											name="com.ibm.portal.propertybroker.action"
											value="sendCaseDetailsAction" />
										<f:param name="caseID" value="#{appealDataBean.caseSK}"></f:param>
										<f:param name="entityID" value="#{appealDataBean.entityID}"></f:param>
										<f:param name="entityType"
											value="#{appealDataBean.entityType}"></f:param>
									</h:commandLink>
									</m:div>
								</m:td>
							</m:tr>
						</m:table>
					</m:div>
				</m:div>--%>
				
				<m:div styleClass="moreInfoBar">
			<m:div styleClass="infoTitle" align="left">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT1146" value="#{msg['msg.appeals.required']}"								style="color:maroon" />
						</m:span>
					</m:div>
			<m:div styleClass="infoActions">

				<t:commandLink id="PRGCMGTCL1" styleClass="strong"
										onmousedown="javascript:flagWarn=false;"
										onclick="javascript:flagWarn=false;"
										action="#{appealControllerBean.saveAppeal}"
										rendered="#{!appealDataBean.disableSaveAppeal}">
										<h:outputText id="PRGCMGTOT2"
											value="#{msg['label.appeals.save']}" />
									</t:commandLink>
									<h:outputText id="PRGCMGTOT3"
										value="#{msg['label.appeals.save']}"
										rendered="#{appealDataBean.disableSaveAppeal}" />
									<h:outputText id="PRGCMGTOT4"
										value="#{msg['label.appeals.pipe']}" />


									<t:commandLink id="PRGCMGTCL2"
										action="#{appealControllerBean.resetAppeals}"
										onmousedown="javascript:flagWarn=false;"
										onclick="javascript:flagWarn=false;"
										rendered="#{!appealDataBean.disableResetAppeal}">
										<h:outputText id="PRGCMGTOT5"
											value="#{msg['label.appeals.reset']}" />
									</t:commandLink>
									<h:outputText id="PRGCMGTOT6"
										value="#{msg['label.appeals.reset']}"
										rendered="#{appealDataBean.disableResetAppeal}" />
									<h:outputText id="PRGCMGTOT7"
										value="#{msg['label.appeals.pipe']}" />

									<%-- ESPRD00509203_ProviderAppeals_03AUG2010--%>
									 <t:commandLink id="PRGCMGTCL3"
										action="#{appealControllerBean.enableAppealDetailAudit}"
										onmousedown="javascript:flagWarn=false;"
										onclick="javascript:flagWarn=false;">
										<h:outputText id="PRGCMGTOT8" value="Audit Log" />
									</t:commandLink>
									<h:outputText id="PRGCMGTOT9"
										value="#{msg['label.appeals.pipe']}" />

									<%--EOF ESPRD00509203_ProviderAppeals_03AUG2010 --%>


								<t:commandLink id="PRGCMGTCL4"
										action="#{logCaseControllerBean.submitCaseDetails}">
										<h:outputText id="PRGCMGTOT10"
											value="#{msg['label.appeals.cancel']}" />
										<f:param id="ipcAction2"
											name="com.ibm.portal.propertybroker.action"
											value="sendCaseDetailsAction" />
										<f:param name="caseID" value="#{appealDataBean.caseSK}"></f:param>
										<f:param name="entityID" value="#{appealDataBean.entityID}"></f:param>
										<f:param name="entityType"
											value="#{appealDataBean.entityType}"></f:param>
									</t:commandLink>	
			</m:div>
			<m:div styleClass="clear"></m:div>
		</m:div>
				<%--ESPRD00509203_ProviderAppeals_03AUG2010 --%>
				<m:div rendered="#{appealDataBean.enableAppealDetailAudit}">
					<audit:auditTableSet id="appealAuditId"
						value="#{appealDataBean.appealVO.auditKeyList}"
						numOfRecPerPage="10"></audit:auditTableSet>
				</m:div>

				<%--EOF ESPRD00509203_ProviderAppeals_03AUG2010 --%>
				<m:div styleClass="moreInfoContent">
					<m:div rendered="#{appealDataBean.showFinalSuccessMsgFlag}"
										style="width:90%">
						<h:messages layout="table" showDetail="true"
							styleClass="successMsg" id="appealSuccessMessage"
							showSummary="false" style="color: red"></h:messages>
					</m:div>
				</m:div>

				<m:div styleClass="moreInfoContent"
					rendered="#{appealDataBean.showMemberInfoLabelFlag}">

					<m:legend>
						<t:htmlTag value="h4">
							<h:outputText id="memInf"
								value="#{msg['label.appeals.provappeals.memberinformation']}" />
						</t:htmlTag>
					</m:legend>

					<m:section id="PROVIDERMMS201207311648112" styleClass="otherbg">
						<m:table id="PROVIDERMMT201207311648113" cellspacing="0" width="95%">
							<m:tr>
								<m:td>
									<m:div>
										<h:outputLabel id="PRGCMGTOLL1" for="addAppeals_MemName"
											value="#{msg['label.appeals.provappeals.membername']}">
										</h:outputLabel>
										<h:outputText id="addAppeals_MemName"
											value="#{appealDataBean.commonCMCaseDetailsVO.name}"></h:outputText>
									</m:div>
								</m:td>
								<m:td>
									<m:div>
										<h:outputLabel id="PRGCMGTOLL2" for="addAppeals_MemID"
											value="#{msg['label.appeals.provappeals.memberid']}">
										</h:outputLabel>
										<h:outputText id="addAppeals_MemID"
											value="#{appealDataBean.commonCMCaseDetailsVO.entityID}"></h:outputText>
									</m:div>
								</m:td>
							</m:tr>
						</m:table>
					</m:section>
				</m:div>


				<m:div styleClass="moreInfoContent"
					rendered="#{!appealDataBean.showMemberInfoLabelFlag && !appealDataBean.showProviderInfoLabelFlag}">

					<m:legend>
						<t:htmlTag value="h4">
							<h:outputText id="otherInf"
								value="#{msg['label.appeals.provappeals.otherinformation']}" />
						</t:htmlTag>
					</m:legend>

					<m:section id="PROVIDERMMS201207311648114" styleClass="otherbg">
						<m:table id="PROVIDERMMT201207311648115" cellspacing="0" width="95%">
							<m:tr>
								<m:td>
									<m:div>
										<h:outputLabel id="PRGCMGTOLL3" for="addAppeals_OtherName"
											value="#{msg['label.appeals.provappeals.othername']}">
										</h:outputLabel>
										<h:outputText id="addAppeals_OtherName"
											value="#{appealDataBean.commonCMCaseDetailsVO.name}"></h:outputText>
									</m:div>
								</m:td>
								<m:td>
									<m:div>
										<h:outputLabel id="PRGCMGTOLL4" for="addAppeals_OtherID"
											value="#{msg['label.appeals.provappeals.otherid']}">
										</h:outputLabel>
										<h:outputText id="addAppeals_OtherID"
											value="#{appealDataBean.commonCMCaseDetailsVO.entityID}"></h:outputText>
									</m:div>
								</m:td>
							</m:tr>
						</m:table>
					</m:section>
				</m:div>


				<m:div styleClass="moreInfoContent"
					rendered="#{appealDataBean.showProviderInfoLabelFlag}">
					<m:legend>
						<t:htmlTag value="h4">
							<h:outputText id="provInf"
								value="#{msg['label.appeals.provappeals.providerinformation']}" />
						</t:htmlTag>
					</m:legend>

					<m:section id="PROVIDERMMS201207311648116" styleClass="otherbg">
						<m:table id="PROVIDERMMT201207311648117" cellspacing="0" width="95%">
							<m:tr>
								<m:td>
									<m:div>
										<h:outputLabel id="PRGCMGTOLL5" for="addAppeals_ProvName"
											value="#{msg['label.appeals.provappeals.providername']}">
										</h:outputLabel>
										<h:outputText id="addAppeals_ProvName"
											value="#{appealDataBean.commonCMCaseDetailsVO.name}"></h:outputText>
									</m:div>
								</m:td>
								<m:td>
									<m:div>
										<h:outputLabel id="PRGCMGTOLL6" for="addAppeals_ProvID"
											value="#{msg['label.appeals.provappeals.providerid']}">
										</h:outputLabel>
										<h:outputText id="addAppeals_ProvID"
											value="#{appealDataBean.commonCMCaseDetailsVO.entityID}"></h:outputText>
									</m:div>
								</m:td>
							</m:tr>
						</m:table>
					</m:section>
				</m:div>
				<m:table id="TabelIDforAppealAlignment" width="100%">
				<m:tr>
				<m:td width="18%">
					<m:section id="PROVIDERMMS201207311648118" styleClass="otherbg">
						<m:pod styleClass="podbg"
							title="#{msg['label.appeals.quicklinks']}">
							<%-- <h:outputLink id="PRGCMGTOLK1"
								value="/wps/myportal/ProjectControlSearchDocumentRepository"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="link1" value="#{msg['link.appeals.edms']}"></h:outputText>
							</h:outputLink>--%>
							<m:div id="edmsQuickLinkID" rendered="#{appealDataBean.renderEDMSQuickLinks}">
							<f:verbatim> <a href="/wps/myportal/ProjectControlSearchDocumentRepository" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK1" ></f:verbatim>
							<h:outputText id="link1" value="#{msg['link.appeals.edms']}"></h:outputText>
							<f:verbatim></a></f:verbatim>
							<m:br></m:br>
							</m:div>
							
							<m:div id="corrGenQuickLinkID" rendered="#{appealDataBean.renderCorresGenQuickLinks && !appealDataBean.showMemberInfoLabelFlag}">
							<t:commandLink id="PRGCMGTCL5" 
								action="#{appealControllerBean.sendCrspdGenAppeal}"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="link2"
									value="#{msg['link.appeals.corrgeneration']}"></h:outputText>
								<f:param name="com.ibm.portal.propertybroker.action"
									value="sendInternalServiceAuthIDAction"></f:param>
							</t:commandLink>
							<m:br></m:br>
							</m:div>
							
							<%-- <h:outputLink id="PRGCMGTOLK2"
								value="/wps/myportal/ServiceAuthorization"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="link3"
									value="#{msg['link.appeals.serviceauth']}"></h:outputText>
							</h:outputLink>--%>
							<m:div id="SAQuickLinkID" rendered="#{appealDataBean.renderSAQuickLinks}">
							<f:verbatim> <a href="/wps/myportal/ServiceAuthorization" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK2" ></f:verbatim>
							<h:outputText id="link3"
									value="#{msg['link.appeals.serviceauth']}"></h:outputText>
							<f:verbatim></a></f:verbatim>
							<m:br></m:br>
							</m:div>
							
							<m:div id="MemberAppealsQuickLinkID" rendered="#{appealDataBean.renderMemberAppealsInquiryQuickLinks && appealDataBean.showMemberInfoLabelFlag}">
							<f:verbatim> <a href="/wps/myportal/AppealsInquiry" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK7" ></f:verbatim>
							<h:outputText id="PRGCMGTOT111" 
								value="#{msg['link.appeals.appeals']}"></h:outputText>
							<f:verbatim></a></f:verbatim>
							<m:br></m:br>
							</m:div>
							
							<m:div id="ProviderAppealsQuickLinkID" rendered="#{appealDataBean.renderProviderAppealsInquiryQuickLinks && appealDataBean.showProviderInfoLabelFlag}">
							<f:verbatim> <a href="/wps/myportal/ProviderAppealsInquiry" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK8" ></f:verbatim>
							<h:outputText id="PRGCMGTOT112" 
								value="#{msg['link.appeals.appeals']}"></h:outputText>
							<f:verbatim></a></f:verbatim>
							<m:br></m:br>
							</m:div>
							
							<m:div id="AddAppealsQuickLinkID" rendered="#{!appealDataBean.showMemberInfoLabelFlag && !appealDataBean.showProviderInfoLabelFlag}">
							<h:outputText id="PRGCMGTOT113" styleClass="strong"
								value="#{msg['link.appeals.appeals']}"></h:outputText>
							<m:br></m:br>
							</m:div>
							<m:div id="caseTrackingQuickLinkID" rendered="#{appealDataBean.renderCaseTrackingQuickLinks}">
							<t:commandLink id="PRGCMGTCL6" 
								action="#{appealControllerBean.sendCaseTrackingAppeal}"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="link4"
									value="#{msg['link.appeals.casetracking']}"></h:outputText>
								<f:param name="com.ibm.portal.propertybroker.action"
									value="sendInternalServiceAuthIDAction"></f:param>
							</t:commandLink>
							<m:br></m:br>
							</m:div>
							
							<%-- <h:outputLink id="PRGCMGTOLK3"
								value="/wps/myportal/ClaimAdminInquiry"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="link5"
									value="#{msg['link.appeals.claiminquiry']}"></h:outputText>
							</h:outputLink>--%>
							<m:div id="claimInquiryQuickLinkID" rendered="#{appealDataBean.renderClaimsInquiryQuickLinks}">
							<f:verbatim> <a href="/wps/myportal/ClaimAdminInquiry" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK3" ></f:verbatim>
							<h:outputText id="link5"
									value="#{msg['link.appeals.claiminquiry']}"></h:outputText>
							<f:verbatim></a></f:verbatim>
							<m:br></m:br>
							</m:div>
							
							<%-- <h:outputLink id="PRGCMGTOLK4"
								value="/wps/myportal/PlanNavigator"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="link6"
									value="#{msg['link.appeals.benefitplan']}"></h:outputText>
							</h:outputLink>--%>
							<m:div id="benifitPlanQuickLinkID" rendered="#{appealDataBean.renderBenefitPlanQuickLinks && !appealDataBean.showMemberInfoLabelFlag}">
							<f:verbatim> <a href="/wps/myportal/PlanNavigator" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK4" ></f:verbatim>
							<h:outputText id="link6"
									value="#{msg['link.appeals.benefitplan']}"></h:outputText>
							<f:verbatim></a></f:verbatim>
							<m:br></m:br>
							</m:div>
							
							<%-- <h:outputLink id="PRGCMGTOLK5"
								value="/wps/myportal/FinancialFinancialAccountingEntity"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="link7"
									value="#{msg['link.appeals.financialentity']}"></h:outputText>
							</h:outputLink>--%>
							<m:div id="financialEntityQuickLinkID" rendered="#{appealDataBean.renderFinancialEntityQuickLinks && appealDataBean.showMemberInfoLabelFlag}">
							<f:verbatim> <a href="/wps/myportal/FinancialFinancialAccountingEntity" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK5" ></f:verbatim>
							<h:outputText id="link7"
									value="#{msg['link.appeals.financialentity']}"></h:outputText>
							<f:verbatim></a></f:verbatim>
							<m:br></m:br>
							</m:div>
							
							<m:div id="eventTrackingQuickLinkID" rendered="#{appealDataBean.renderEventTrackingQuickLinks && !appealDataBean.showMemberInfoLabelFlag}">
							<f:verbatim> <a href="/wps/myportal/ProjectControlEventLogging" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK6" ></f:verbatim>
							<h:outputText id="link8"
									value="#{msg['link.appeals.eventtracking']}" ></h:outputText>
							<f:verbatim></a></f:verbatim>
							<m:br></m:br>
							</m:div>
							
							
						</m:pod>
					</m:section>
				</m:td>
				<m:td width="1%">
					<m:div>&nbsp;</m:div>
				</m:td>
				
				<m:td width="81%">
					<%--<m:div rendered="#{!appealDataBean.showFinalSuccessMsgFlag}">
						<h:messages layout="table" showDetail="true"
							styleClass="errorMessage" id="errorMessage" showSummary="false"
							style="color: red"></h:messages>
					</m:div>--%>
						<m:br/>
						<m:br/>
					<m:section id="PROVIDERMMS201207311648119" styleClass="otherbg">
						<m:legend>
							<h:outputText id="PRGCMGTOT12"
								value="#{msg['label.appeals.appealdetails']}" />
						</m:legend>

						<m:table id="PROVIDERMMT2012073116481110" cellspacing="0" width="100%">
							<m:tr>
								<m:td>
									<m:div styleClass="padb">
										<m:span styleClass="required">
											<h:outputText id="PRGCMGTOT13"
												value="#{msg['label.appeals.astrik']}" />
										</m:span>
										<h:outputLabel id="PRGCMGTOLL7" for="appealDetails_CRNumber"
											value="#{msg['label.appeals.caserecordnumber']}">
										</h:outputLabel>
										<m:br />
										<h:inputText id="appealDetails_CRNumber" size="10"
											value="#{appealDataBean.commonCMCaseDetailsVO.caseSK}"
											disabled="#{appealDataBean.disableCase}" />
										&nbsp;
						         <%--	<h:commandLink id="PRGCMGTCL7"									action="#{appealControllerBean.submitLogCaseDetails}"									onmousedown="javascript:flagWarn=false;">
									<h:outputText id="PRGCMGTOT14" value="Case Record Details"></h:outputText>
									<f:param id="ipcAction"
										name="com.ibm.portal.propertybroker.action"
										value="sendCaseDetailsAction" />

								</h:commandLink>--%>
										<%-- Added for the defect id ESPRD00529115 --%>
										<hx:requestLink id="requestlinkCRID" value="Case Record Details"
											onclick="javascript:flagWarn=false;"
											action="#{appealControllerBean.submitDetailsForRightClick }">
											<f:param name="linkID"
												value="PRGCMGTCL8"></f:param>
										</hx:requestLink>
										<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
										<%--<h:commandLink id="PRGCMGTCL8" styleClass="hide"
											action="#{logCaseControllerBean.submitCaseDetails}"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT15" value="Case Record Details"></h:outputText>
											<f:param id="ipcAction"
												name="com.ibm.portal.propertybroker.action"
												value="sendCaseDetailsAction" />
											<f:param name="caseID"
												value="#{appealDataBean.appealVO.caseSK}"></f:param>
											<f:param name="entityID" value="#{appealDataBean.providerID}"></f:param>
											<f:param name="entityType"
												value="#{appealDataBean.entityType}"></f:param>
										</h:commandLink>--%>
										<%-- Added for the defect id ESPRD00529115 Ends --%>
									</m:div>
								</m:td>

								<m:td>
									<m:div styleClass="padb">
										<m:span styleClass="required">*</m:span>
										<h:outputLabel id="PRGCMGTOLL8" for="appealDetails_Type"
											value="#{msg['label.appeals.type']}">
										</h:outputLabel>
										<m:br />
										<h:selectOneMenu id="appealDetails_Type"
											value="#{appealDataBean.appealVO.caseAplTyCd}"
											valueChangeListener="#{appealControllerBean.showComponent}"
											onchange="javascript:focusThis(this.id);javascript:internalsubmmit('addNewAppealsViewForm');this.form.submit();javascript:flagWarn=false;"
											onclick="javascript:flagWarn=false;">
											<f:selectItems value="#{appealDataBean.typeList}" />
										</h:selectOneMenu>
										<m:br />
										<h:message id="PRGCMGTM1" for="appealDetails_Type"
											styleClass="errorMessage" />
									</m:div>
								</m:td>

								<m:td colspan="2">
									<m:div styleClass="padb">
										<m:span styleClass="required">
											<h:outputText id="PRGCMGTOT16"
												value="#{msg['label.appeals.astrik']}" />
										</m:span>
										<h:outputLabel id="PRGCMGTOLL9" for="appealDetails_AppType"
											value="#{msg['label.appeals.appealtype']}">
										</h:outputLabel>
										<m:br />
										<h:selectOneMenu id="appealDetails_AppType"
											value="#{appealDataBean.appealVO.aplTyCd}"
											valueChangeListener="#{appealControllerBean.showAppealContReason}"
											onchange="javascript:focusThis(this.id);javascript:addeditrow('addNewAppealsViewForm');this.form.submit();javascript:flagWarn=false;"
											onclick="javascript:flagWarn=false;">

											<f:selectItems value="#{appealDataBean.appealTypeList}" />

										</h:selectOneMenu>
										<m:br />
										<h:message id="PRGCMGTM2" for="appealDetails_AppType"
											styleClass="errorMessage" />
									</m:div>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
									<m:div styleClass="padb">
										<h:outputLabel id="PRGCMGTOLL10" for="appealDetails_RName"
											value="#{msg['label.appeal.reviewername']}">
										</h:outputLabel>
										<m:br />
										<h:inputText id="appealDetails_RName"
											onkeyup="this.value=this.value.toUpperCase();"
											value="#{appealDataBean.appealVO.revwrNam}" />
										<m:br />
										<h:message id="PRGCMGTM3" for="appealDetails_RName"
											styleClass="errorMessage" />
									</m:div>
								</m:td>

								<m:td>
									<m:div styleClass="padb">
										<h:outputLabel id="PRGCMGTOLL11" for="appealDetails_AsgnDate"
											value="#{msg['label.appeals.assigneddate']}">
										</h:outputLabel>
										<m:br />
										<m:inputCalendar monthYearRowClass="yearMonthHeader"
											weekRowClass="weekHeader" id="appealDetails_AsgnDate"
											currentDayCellClass="currentDayCell" size="10"
											renderAsPopup="true" addResources="true"
											renderPopupButtonAsImage="true"
											value="#{appealDataBean.appealVO.asgnDt}"
											popupDateFormat="MM/dd/yyyy">
										</m:inputCalendar>
										<m:br />
										<h:message id="PRGCMGTM4" for="appealDetails_AsgnDate"
											styleClass="errorMessage" />
									</m:div>
								</m:td>

								<m:td>
									<m:div styleClass="padb">
										<h:outputLabel id="PRGCMGTOLL12" for="appealDetails_PANumber"
											value="#{msg['label.appeals.previousappealnumber']}">
										</h:outputLabel>
										<m:br />

										<h:inputText id="appealDetails_PANumber" size="10"
											value="#{appealDataBean.appealVO.prevAplNum}"
											disabled="#{appealDataBean.disableCase}" />
										<m:br />
										<h:message id="PRGCMGTM5" for="appealDetails_PANumber"
											styleClass="errorMessage" />
									</m:div>
								</m:td>

								<m:td>
									<m:div styleClass="padb"
										rendered="#{appealDataBean.showContReasonFlag}">
										<h:outputLabel id="PRGCMGTOLL13" for="appealDetails_contRsnCd"
											value="#{msg['label.appeals.continuanceReason']}">
										</h:outputLabel>
										<m:br />
										<h:selectOneMenu id="appealDetails_contRsnCd"
											onchange="javascript:focusThis(this.id);javascript:selectOne=true;javascript:flagWarn=false;"
											value="#{appealDataBean.appealVO.caseAplContinuanceRsnCd}">

											<f:selectItems value="#{appealDataBean.appealContReasonList}" />

										</h:selectOneMenu>
										<m:br />
									</m:div>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
									<m:div styleClass="padb">
										<h:outputLabel id="PRGCMGTOLL14" for="appealDetails_AplDate"
											value="#{msg['label.appeals.appealdate']}">
										</h:outputLabel>
										<m:br />
										<m:inputCalendar monthYearRowClass="yearMonthHeader"
											weekRowClass="weekHeader" id="appealDetails_AplDate"
											currentDayCellClass="currentDayCell" size="10"
											renderAsPopup="true" addResources="true"
											renderPopupButtonAsImage="true"
											value="#{appealDataBean.appealVO.aplDt}"
											popupDateFormat="MM/dd/yyyy">
										</m:inputCalendar>
										<m:br />
										<h:message id="PRGCMGTM6" for="appealDetails_AplDate"
											styleClass="errorMessage" />
									</m:div>
								</m:td>
								<m:td>
									<m:div styleClass="padb">
										<h:outputLabel id="PRGCMGTOLL15"
											for="appealDetails_AplResults"
											value="#{msg['label.appeals.appealresults']}">
										</h:outputLabel>
										<m:br />
										<h:selectOneMenu id="appealDetails_AplResults"
											onchange="javascript:focusThis(this.id);javascript:selectOne=true;javascript:flagWarn=false;"
											value="#{appealDataBean.appealVO.caseAplRsltsCd}">
											<f:selectItems value="#{appealDataBean.appealResultsList}" />

										</h:selectOneMenu>
									</m:div>
								</m:td>

								<m:td>
									<m:div styleClass="padb">
										<h:outputLabel id="PRGCMGTOLL16" for="appealDetails_AplStatus"
											value="#{msg['label.appeals.appealstatus']}">
										</h:outputLabel>
										<m:br />
										<h:selectOneMenu id="appealDetails_AplStatus"
											onchange="javascript:focusThis(this.id);javascript:selectOne=true;javascript:flagWarn=false;"
											value="#{appealDataBean.appealVO.caseAplStatCd}">
											<f:selectItems value="#{appealDataBean.appealStatusList}" />
										</h:selectOneMenu>
									</m:div>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
									<m:div styleClass="padb">
										<h:outputLabel id="PRGCMGTOLL17" for="appealDetails_AplStUpdtDate"
											value="#{msg['label.appeals.appealstatusupdatedate']}">
										</h:outputLabel>
										<m:br />
										<m:inputCalendar monthYearRowClass="yearMonthHeader"
											weekRowClass="weekHeader" id="appealDetails_AplStUpdtDate"
											currentDayCellClass="currentDayCell" size="10"
											renderAsPopup="true" addResources="true"
											renderPopupButtonAsImage="true"
											value="#{appealDataBean.appealVO.caseAplStatCdDt}"
											popupDateFormat="MM/dd/yyyy">
										</m:inputCalendar>
										<m:br />
										<h:message id="PRGCMGTM7" for="appealDetails_AplStUpdtDate"
											styleClass="errorMessage" />
									</m:div>
								</m:td>

								<m:td colspan="2">
									<m:div styleClass="padb">
										<h:outputLabel id="PRGCMGTOLL18" for="appealDetails_DstOffice"
											value="#{msg['label.appeals.districtoffice']}">
										</h:outputLabel>
										<m:br />
										<h:selectOneMenu id="appealDetails_DstOffice"
											onchange="javascript:focusThis(this.id);javascript:selectOne=true;javascript:flagWarn=false;"
											value="#{appealDataBean.appealVO.caseAplDstctOfcCd}">
											<f:selectItems value="#{appealDataBean.distOfficeList}" />
										</h:selectOneMenu>
									</m:div>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td rendered="#{appealDataBean.showClaimCompFlag}">
									<m:div id="appealDetails_CNumber1" styleClass="padb">

										<%--<m:span styleClass="required">
									<h:outputText id="PRGCMGTOT17" value="#{msg['label.appeals.astrik']}" />
								</m:span>--%>

										<h:outputLabel id="PRGCMGTOLL19" for="appealDetails_CNumber"
											value="#{msg['label.appeals.claimnumber']}">
										</h:outputLabel>
										<m:br />
										<h:inputText id="appealDetails_CNumber" size="10"
											value="#{appealDataBean.appealVO.tcnNum}" onblur="javascript:flagWarn=false;TCNActionblur();"/>
										<%--Modified for defect ESPRD00529011  starts--%>
										<%--<h:commandLink id="PRGCMGTCL9" action="#{appealControllerBean.searchById}"									onmousedown="javascript:flagWarn=false;">
									<h:outputText id="xx1" value="Claims Details"></h:outputText>
									<f:param name="com.ibm.portal.propertybroker.action"
										value="sendInternalServiceAuthIDAction"></f:param>
								</h:commandLink>--%>
										<hx:requestLink id="requestlinkClaimsID"
											value="Claims Details"
											onclick="javascript:flagWarn=false;"
											action="#{appealControllerBean.submitDetailsForRightClick }">
											<f:param name="linkID" value="PRGCMGTCL10"></f:param>
										</hx:requestLink>
										<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
										<%--<h:commandLink id="PRGCMGTCL10" styleClass="hide"
											action="#{appealControllerBean.searchClaimInquiryByTCN}"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="xx1" value="Claims Details"></h:outputText>
											<f:param name="action.param.send.ClaimsInternalInquiryAction"
												value="send.ClaimsInternalInquiryAction"></f:param>
										</h:commandLink>--%>
										<%--Modified for defect ESPRD00529011  ends--%>
										<m:br />
										<h:message id="PRGCMGTM8" for="appealDetails_CNumber"
											styleClass="errorMessage" />
									</m:div>
								</m:td>
								<m:td>
									<h:commandButton id="tcnCmdbuttonlinkAppealID" styleClass="hide"
										action="#{appealControllerBean.sumbitTCNDetails}" value="#{msg['label.appeal.hiddenButtonToClick']}"
										onmousedown="javascript:flagWarn=false;"></h:commandButton>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td rendered="#{appealDataBean.showSACompFlag}">
									<m:div id="appealDetails_SANumber1" styleClass="padb">

										<m:span styleClass="required">
											<h:outputText id="PRGCMGTOT18"
												value="#{msg['label.appeals.astrik']}" />
										</m:span>

										<h:outputLabel id="PRGCMGTOLL20" for="appealDetails_SANumber"
											value="#{msg['label.appeals.sanumber']}">
										</h:outputLabel>
										<m:br />

										<h:inputText id="appealDetails_SANumber" size="10"
											value="#{appealDataBean.appealVO.authID}"
											onkeyup="this.value=this.value.toUpperCase();"
											onblur="javascript:flagWarn=false;saAction(event)">
										</h:inputText>

										<%-- Modified for ESPRD00531190 starts--%>
										<%--<h:commandLink id="PRGCMGTCL11" 									action="#{appealControllerBean.submitSADetailsForIPC}"									onmousedown="javascript:flagWarn=false;">
									<h:outputText id="xx2" value="SA Details"></h:outputText>
									<f:param name="com.ibm.portal.propertybroker.action"
										value="sendInternalServiceAuthIDAction"></f:param>
								</h:commandLink>--%>
										<hx:requestLink id="requestlinkSAID"
											value="SA Details" onclick="javascript:flagWarn=false;"
											action="#{appealControllerBean.submitDetailsForRightClick }">
											<f:param name="linkID" value="PRGCMGTCL12"></f:param>
										</hx:requestLink>
										<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
										<%--<h:commandLink id="PRGCMGTCL12" styleClass="hide"
											action="#{appealControllerBean.submitSADetailsForIPC}"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="xx2" value="SA Details"></h:outputText>
											<f:param name="send.insti.claimCorr.info"
												value="sendInstiClaimCorrInfo" />
										</h:commandLink>--%>
										<%-- Modified for ESPRD00531190 ends--%>
										<m:br />
										<h:message id="PRGCMGTM9" for="appealDetails_SANumber"
											styleClass="errorMessage" />

									</m:div>
								</m:td>
								<m:td>
									<h:commandButton id="submitSADetails" styleClass="hide"
										action="#{appealControllerBean.submitSADetails}" value="#{msg['label.appeal.hiddenButtonToClick']}"
										onmousedown="javascript:flagWarn=false;" />
								</m:td>

							</m:tr>
							<m:div styleClass="clear">
							</m:div>
							<m:div>
								<m:br />
								<m:br />
							</m:div>
						</m:table>
					</m:section>

					<m:div id="saLineitems" rendered="#{appealDataBean.showSACompFlag}">
						<jsp:include page="/jsp/appeals/inc_saLineItems.jsp" />
					</m:div>

					<m:div id="additionalInformation">
						<jsp:include
							page="/jsp/appeals/inc_appealsAdditionalInformation.jsp" />
					</m:div>

					<m:div id="reconsideration">
						<jsp:include page="/jsp/appeals/inc_appealsReconsideration.jsp" />
					</m:div>

					<m:div id="administrativeHearing">
						<f:subview id="administrativeHearingSubViewId">
							<jsp:include
								page="/jsp/appeals/inc_appealsAdministrativeHearing.jsp" />
						</f:subview>
					</m:div>

					<m:div id="informalReview">
						<jsp:include page="/jsp/appeals/inc_appealsInformalReview.jsp" />
					</m:div>

					<m:div id="DHHSFormalReview">
						<jsp:include page="/jsp/appeals/inc_appealsDHHSFormalReview.jsp" />
					</m:div>

					<m:div styleClass="clear">
					</m:div>
					<m:div>
						<m:br />
						<m:br />
					</m:div>

					<m:div>
						<%--commented for ESPRD00509203_ProviderAppeals_03AUG2010
Modified for CR_ESPRD00373565_AuditLog starts --%>
						<%--	<f:subview id="auditlogDetails">
					<jsp:include page="/jsp/appeals/inc_appealDetailsAuditLog.jsp" />
				</f:subview>- - %>

				<audit:auditTableSet id="appealAuditId"
					value="#{appealDataBean.appealVO.auditKeyList}"
					numOfRecPerPage="10"></audit:auditTableSet>
		<% - -Modified for CR_ESPRD00373565_AuditLog ends 
EOF ESPRD00509203_ProviderAppeals_03AUG2010
--%>
					</m:div>
					<m:div styleClass="clear">
					</m:div>
					<m:div>
						<m:br />
						<m:br />
					</m:div>
					</m:td>
				</m:tr>
				</m:table>
			</m:div>
		</m:div>
	</h:form>
	</body>
	<%--
	  var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
	   FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">
	//var flagRole = ((typeof(document.getElementById(thisForm+":userHavingUpdateRole") != "undefined"))&& document.getElementById(thisForm+":userHavingUpdateRole") != null ? flagRole = document.getElementById(thisForm+":userHavingUpdateRole").value:true);
	var flagRole = ((typeof(document.getElementById(thisForm+':userHavingUpdateRole') != 'undefined')) && document.getElementById(thisForm+':userHavingUpdateRole') != null ? flagRole = document.getElementById(thisForm+':userHavingUpdateRole').value:true);
	//alert("flag role ========  "+flagRole);
	</script>
</f:view>
