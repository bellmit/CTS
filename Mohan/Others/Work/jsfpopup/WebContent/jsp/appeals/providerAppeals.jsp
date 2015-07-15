<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/jsp/appeals/ProviderAppeals.java" --%><%-- /jsf:pagecode --%>
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
<script type="text/javascript"
	src="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/myFacesExtensionResource/org.apache.myfaces.renderkit.html.util.MyFacesResourceLoader/12742505/calendar.HtmlInputCalendar/popcalendar.js") %>">
</script>
<script type="text/javascript"
	src="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/myFacesExtensionResource/org.apache.myfaces.renderkit.html.util.MyFacesResourceLoader/12742505/calendar.HtmlInputCalendar/popcalendar_init.js") %>">
</script>
<script type="text/javascript"
	src="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/myFacesExtensionResource/org.apache.myfaces.renderkit.html.util.MyFacesResourceLoader/12742812/calendar.HtmlInputCalendar/popcalendar.js") %>">
</script>
<script type="text/javascript"
	src="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/myFacesExtensionResource/org.apache.myfaces.renderkit.html.util.MyFacesResourceLoader/12742812/calendar.HtmlInputCalendar/popcalendar_init.js") %>">
</script>
<script type="text/javascript"
	src="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/myFacesExtensionResource/org.apache.myfaces.renderkit.html.util.MyFacesResourceLoader/12743414/calendar.HtmlInputCalendar/popcalendar.js") %>">
</script>
<script type="text/javascript"
	src="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/myFacesExtensionResource/org.apache.myfaces.renderkit.html.util.MyFacesResourceLoader/12743414/calendar.HtmlInputCalendar/popcalendar_init.js") %>">
</script>
<script type="text/javascript"
	src="<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/ACSExtensionResource/calendar.ACSInputCalendar/11302665/popcalendar_init.js") %>"></script>
<script type="text/javascript"
	src="<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/faces/ACSExtensionResource/calendar.ACSInputCalendar/11302665/popcalendar.js") %>"></script>

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactMemberAppealsInquiry"
	var="msg" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />
<script type="text/javascript">
	function renderAudit(id) 
	{ 
		var hiddenValue = document.getElementById('view<portlet:namespace/>:providerAppealsViewForm:providerAppeals:'+id);
	    
		if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
			hideMe('audit_plus');
		} else if ((hiddenValue.value == 'false')) 
		{
			hideMe('audit_plus');
		} 
			
	}
	function renderAdminHearingAudit(id) 
	{ 
		var hiddenValue = document.getElementById('view<portlet:namespace/>:providerAppealsViewForm:providerAppeals:administrativeHearing:editAdministrativeHearing:adminHearAuditlogDetails:'+id);
	    
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
	function findObjectByPartOfIDForProvAppeal(partOfID)
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
	function saAction(e1)
	{			
		var submitProvSADetails = findObjectByPartOfIDForProvAppeal('submitProvSADetails');
		submitProvSADetails.click();
	}
	function TCNActionblur()
	{
		var submitProvTCNDetails=findObjectByPartOfIDForProvAppeal('tcnCmdbuttonlinkPRVID');
		submitProvTCNDetails.click();
	}
	/*Added for CR Cursor focus starts*/
		var thisForm = 'view<portlet:namespace/>:providerAppealsViewForm';
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
			if(document.getElementById(thisForm+':focusId').value=='adminHearingButton'){
				document.getElementById('view<portlet:namespace/>:providerAppealsViewForm:providerAppealsSubViewId:adminHearingButton').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='adminHearingTable'){
				document.getElementById('view<portlet:namespace/>:providerAppealsViewForm:providerAppealsSubViewId:adminHearingTable').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='appealDetails_Type'){
				document.getElementById('view<portlet:namespace/>:providerAppealsViewForm:providerAppealsSubViewId:appealDetails_Type').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='add_AdminHrngDT'){
				document.getElementById('view<portlet:namespace/>:providerAppealsViewForm:providerAppealsSubViewId:addAdminHearingFlagID:add_AdminHrngDT').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='edit_AdminHrngDT'){
				document.getElementById('view<portlet:namespace/>:providerAppealsViewForm:providerAppealsSubViewId:editAdminHearingFlagID:edit_AdminHrngDT').focus();
			}   

   			var focusPage = '#'+document.getElementById(thisForm+':focusId').value;
			if(focusPage != '' && focusPage != '#') {				
				document.location.href=focusPage;
			}
			<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
			<%--var rightClickIndPRV=document.getElementById(thisForm+':hyperlinkIDPRV').value;			
			if(rightClickIndPRV!=null && rightClickIndPRV!=""){
				document.getElementById(thisForm+':hyperlinkIDPRV').value="";				
				document.getElementById(thisForm+':'+rightClickIndPRV).click();
			}--%>
   		}
   		/*Added for CR Cursor focus ends*/   
	</script>

<f:view>
	<f:subview id="provService">

		<jsp:include page="/jsp/script/scripts.jsp" />

	</f:subview>

	<%--COMMENTED FOR DEFECT ESPRD00682817 STARTS--%>
	<%-- <t:saveState id="CMGTTOMSS9" value="#{appealDataBean}"></t:saveState>--%>
	<%--COMMENTED FOR DEFECT ESPRD00682817 ENDS--%>
	<t:saveState id="CMGTTOMSS10" value="#{appealDataBean.entityID}"></t:saveState>
	<t:saveState id="CMGTTOMSS11"
		value="#{appealDataBean.adminHearingList}"></t:saveState>

	<m:body
		onload="warnBeforeCancel();renderAudit('audit_open');renderAdminHearingAudit('admin_hear_audit_open')">

		<h:form id="providerAppealsViewForm">
			<m:div id="providerappealDiv01" styleClass="floatContainer">
			<h:inputHidden id="PRGCMGTIH7"
						value="#{appealControllerBean.loadValidValues}"
						rendered="#{appealDataBean.validValueFlag}"></h:inputHidden>
					<h:inputHidden id="PRGCMGTIH8"
						value="#{appealControllerBean.link2Show}"></h:inputHidden>

					<h:inputHidden id="userHavingUpdateRole"
						value="#{appealDataBean.controlRequired}" />
					<h:inputHidden id="PRGCMGTIH9"
						value="#{appealControllerBean.providerAppealsSearch}"></h:inputHidden>
					<h:inputHidden id="focusId"
						value="#{appealDataBean.cursorFocusValue}"></h:inputHidden>
					<%--Holds the Entity details for displaying on click of Print Selected--%>
					<h:inputHidden id="HDNENTITYID" value="#{appealDataBean.commonProviderDetailsVO.providerID}" />
					<h:inputHidden id="HDNENTITYTYPE" value="#{appealDataBean.entityType}" />
					<h:inputHidden id="HDNENTITYNAME" value="#{appealDataBean.commonProviderDetailsVO.providerName}" />
					<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
					<%--<h:inputHidden id="hyperlinkIDPRV" value="#{appealDataBean.rightClickIndicator }"></h:inputHidden>--%>
			 <m:div  rendered="#{appealDataBean.cancelFlag}">
				<m:div id="providerappealDiv02" style="width:100%">
					
						
					<m:div styleClass="moreInfoBar">
						<m:div styleClass="actions">
							<m:table id="PROVIDERMMT20120731164811124" styleClass="tableBar" width="100%">
								<m:tr>
									<m:td>
										<m:div styleClass="actionBarRequired">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT181"
													value="#{msg['msg.appeals.required']}" />
											</m:span>
										</m:div>
									</m:td>
									<m:td align="right">
										<t:commandLink id="PRGCMGTCL35" styleClass="strong"
											action="#{appealControllerBean.saveAppeal}"
											rendered="#{!appealDataBean.disableSaveProvApp}"
											onmousedown="javascript:flagWarn=false;"
											onclick="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT182"
												value="#{msg['label.appeals.save']}" />
										</t:commandLink>
										<h:outputText id="PRGCMGTOT183"
											value="#{msg['label.appeals.save']}"
											rendered="#{appealDataBean.disableSaveProvApp}" />
										<h:outputText id="PRGCMGTOT184"
											value="#{msg['label.appeals.pipe']}" />

										<t:commandLink id="PRGCMGTCL36"
											action="#{appealControllerBean.resetProviderAppeals}"
											rendered="#{!appealDataBean.disableResetProvApp}"
											onmousedown="javascript:flagWarn=false;"
											onclick="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT185"
												value="#{msg['label.appeals.reset']}" />
										</t:commandLink>
										<h:outputText id="PRGCMGTOT186"
											value="#{msg['label.appeals.reset']}"
											rendered="#{appealDataBean.disableResetProvApp}" />
										<h:outputText id="PRGCMGTOT187"
											value="#{msg['label.appeals.pipe']}" />
										<%-- NH DEFECT FIX-ESPRD00702416--%>
										<t:commandLink id="PRGCMGTCL37"
											action="#{appealControllerBean.enableAppealDetailAudit}"
											onmousedown="javascript:flagWarn=false;"
											onclick="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT188"
												value="#{msg['label.appeals.audit.log']}" />
										</t:commandLink>
										<h:outputText id="PRGCMGTOT189"
											value="#{msg['label.appeals.pipe']}" />

										<%--EOF ESPRD00509203_ProviderAppeals_03AUG2010 --%>
										<t:commandLink id="PRGCMGTCL38"
											action="#{appealControllerBean.cancelProviderAppeals}">
											<h:outputText id="PRGCMGTOT190"
												value="#{msg['label.appeals.cancel']}" />
										</t:commandLink>
									</m:td>
								</m:tr>
							</m:table>
						</m:div>
					</m:div>
						<m:div rendered="#{appealDataBean.showFinalSuccessMsgFlag}">
							<h:messages layout="table" showDetail="true"
								styleClass="successMsg" id="appealSuccessMessage"
								showSummary="false" style="color: red"></h:messages>
						</m:div>
					<%-- modified for defect ESPRD00335054 --%>
					<m:table id="PROVIDERMMT20120731164811125" width="100%">
						<%--ESPRD00509203_ProviderAppeals_03AUG2010 --%>
						<m:tr>
							<m:td colspan="3">
								<%--m:div id="providerAppealDataFlag" rendered="#{appealDataBean.providerAppealDataFlag}"--%>
								<m:div id="enableAppealDetailAudit"
									rendered="#{appealDataBean.enableAppealDetailAudit}">
									<f:subview id="provAuditlogDetails">
										<audit:auditTableSet id="provAppealAuditId"
											value="#{appealDataBean.appealVO.auditKeyList}"
											numOfRecPerPage="10">
										</audit:auditTableSet>
									</f:subview>
								</m:div>
								<%--/m:div--%>
							</m:td>
						</m:tr>
						<%--EOF ESPRD00509203_ProviderAppeals_03AUG2010 --%>
						<m:tr>

							<m:td width="18%">
								<m:section id="PROVIDERMMS20120731164811126" styleClass="otherbg">
									<m:pod styleClass="podbg"
										title="#{msg['label.appeals.quicklinks']}">
										<%-- <h:outputLink id="PRGCMGTOLK11"
											value="/wps/myportal/ProjectControlSearchDocumentRepository"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="outputTextlink1"
												value="#{msg['link.appeals.edms']}"></h:outputText>
										</h:outputLink>--%>
										<m:div id="edmsQuickLinkID" rendered="#{appealDataBean.renderEDMSQuickLinks}">
										<f:verbatim> <a href="/wps/myportal/ProjectControlSearchDocumentRepository" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK11" ></f:verbatim>
										<h:outputText id="outputTextlink1"
												value="#{msg['link.appeals.edms']}"></h:outputText>
										<f:verbatim></a></f:verbatim>
										<m:br></m:br>
										</m:div>
										<m:div id="corrGenQuickLinkID" rendered="#{appealDataBean.renderCorresGenQuickLinks}">
										<t:commandLink id="PRGCMGTCL39"
											action="#{appealControllerBean.sendCrspdGenAppeal}"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="outputTextlink2"
												value="#{msg['link.appeals.corrgeneration']}"></h:outputText>
											<f:param name="send.member.summary.action"
												value="sendMemberSysIDAction"></f:param>
										</t:commandLink>
										<m:br></m:br>
										</m:div>
										<%-- <h:outputLink id="PRGCMGTOLK12"
											value="/wps/myportal/ServiceAuthorization"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="outputTextlink3"
												value="#{msg['link.appeals.serviceauth']}"></h:outputText>
										</h:outputLink>--%>
										<m:div id="SAQuickLinkID" rendered="#{appealDataBean.renderSAQuickLinks}">
										<f:verbatim> <a href="/wps/myportal/ServiceAuthorization" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK12" ></f:verbatim>
										<h:outputText id="outputTextlink3"
												value="#{msg['link.appeals.serviceauth']}"></h:outputText>
										<f:verbatim></a></f:verbatim>
										
										<m:br></m:br>
										</m:div>
										<h:outputText id="PRGCMGTOT191" styleClass="strong"
											value="#{msg['link.appeals.appeals']}"></h:outputText>
										<m:br></m:br>
										<m:div id="caseTrackingQuickLinkID" rendered="#{appealDataBean.renderCaseTrackingQuickLinks}">
										<t:commandLink id="PRGCMGTCL40"
											action="#{appealControllerBean.sendCaseTrackingAppeal}"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="outputTextlink4"
												value="#{msg['link.appeals.casetracking']}"></h:outputText>
											<f:param name="send.member.summary.action"
												value="sendMemberSysIDAction"></f:param>
										</t:commandLink>
										<m:br></m:br>
										</m:div>
										<%-- <h:outputLink id="PRGCMGTOLK13"
											value="/wps/myportal/ClaimAdminInquiry"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="outputTextlink5"
												value="#{msg['link.appeals.claiminquiry']}"></h:outputText>
										</h:outputLink>--%>
										<m:div id="claimInquiryQuickLinkID" rendered="#{appealDataBean.renderClaimsInquiryQuickLinks}">
										<f:verbatim> <a href="/wps/myportal/ClaimAdminInquiry" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK13" ></f:verbatim>
										<h:outputText id="outputTextlink5"
												value="#{msg['link.appeals.claiminquiry']}"></h:outputText>
										<f:verbatim></a></f:verbatim>
										
										<m:br></m:br>
										</m:div>
										<%-- <h:outputLink id="PRGCMGTOLK14"
											value="/wps/myportal/PlanNavigator"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="outputTextlink6"
												value="#{msg['link.appeals.benefitplan']}"></h:outputText>
										</h:outputLink>--%>
										<m:div id="benifitPlanQuickLinkID" rendered="#{appealDataBean.renderBenefitPlanQuickLinks}">
										<f:verbatim> <a href="/wps/myportal/PlanNavigator" onmousedown="javascript:flagWarn=false;" id="PRGCMGTOLK14" ></f:verbatim>
										<h:outputText id="outputTextlink6"
												value="#{msg['link.appeals.benefitplan']}"></h:outputText>
										<f:verbatim></a></f:verbatim>
										
										<m:br></m:br>
										</m:div>
										<%-- <h:outputLink id="PRGCMGTOLK15"
											value="/wps/myportal/FinancialFinancialAccountingEntity"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="outputTextlink7"
												value="#{msg['link.appeals.financialentity']}"></h:outputText>
										</h:outputLink>--%>
										<m:div id="eventTrackingQuickLinkID" rendered="#{appealDataBean.renderEventTrackingQuickLinks}">
										<f:verbatim> <a href="/wps/myportal/ProjectControlEventLogging" onmousedown="javascript:flagWarn=false;" id="outputTextlink7" ></f:verbatim>
										<h:outputText id="outputTextlink7"
												value="#{msg['link.appeals.eventtracking']}"></h:outputText>
										<f:verbatim></a></f:verbatim>

										<m:br></m:br>
										</m:div>
									</m:pod>
								</m:section>
							</m:td>
							<m:td width="1%">
								<m:div>&nbsp;</m:div>
							</m:td>
							<%-- EOF quick links--%>
							<m:td width="81%">
								<m:div styleClass="moreInfoContent">

									<h4>Provider Information</h4>
									<m:section id="PROVIDERMMS20120731164811127" styleClass="otherbg">
										<m:table id="PROVIDERMMT20120731164811128" cellspacing="0" width="100%">
											<m:tr>
												<m:td>
													<m:div>
														<h:outputText id="PRGCMGTOT192"
															value="#{msg['label.appeals.provappeals.providername']}">
														</h:outputText>
														<h:outputText id="provAppeals_ProvName"
															value="#{appealDataBean.commonProviderDetailsVO.providerName}"></h:outputText>
													</m:div>
												</m:td>
												<m:td>
													<m:div>
														<h:outputText id="PRGCMGTOT193"
															value="#{msg['label.appeals.memappeals.medicaidid']}">
														</h:outputText>
														<h:outputText id="provAppeals_MedID"
															value="#{appealDataBean.commonProviderDetailsVO.providerID}"></h:outputText>
													</m:div>
												</m:td>
											</m:tr>
										</m:table>
									</m:section>
								</m:div>



								<m:div>
									<m:br />
								</m:div>
								<m:table id="PROVIDERMMT20120731164811129" styleClass="tableBar" width="100%">
									<m:tr>
										<m:td styleClass="tableTitle">
											<h:outputText id="PRGCMGTOT194"
												value="#{msg['label.memappeals.appeals']}" />
										</m:td>
									</m:tr>
								</m:table>




								<t:dataTable cellspacing="0" styleClass="dataTable"
									var="providerAppealResult" rowIndexVar="index"
									id="providerAppealTable" width="100%" rows="10"
									value="#{appealDataBean.providerAppealList}"
									rendered="#{appealDataBean.providerAppealDataFlag}"
									columnClasses="columnClass" headerClass="headerClass"
									footerClass="footerClass" rowClasses="row_alt,row"
									onmousedown="javascript:focusThis('appealDetails_Type');"
									rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
									rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
									rowOnClick="javascript:childNodes[0].lastChild.click();flagWarn=false;">
									<t:column id="memAppeal_aplCsRecNo">
										<f:facet name="header">
											<t:panelGrid id="CMGTTOMPGD6" columns="2">
												<t:outputLabel id="CMGTTOMOL6" for="caseRecNoLink1"
													value="#{msg['label.memappeals.appealcaserecordnumber']}" />
												<t:panelGroup id="CMGTTOMPGP6">

													<t:commandLink id="caseRecNoLink1" styleClass="clStyle"
														actionListener="#{appealControllerBean.sortAdminHearing}"
														rendered="#{appealDataBean.memAppealImageRender != 'caseRecNoLink1'}"
														onmousedown="javascript:flagWarn=false;">
														<h:graphicImage id="PROVIDERGI20120731164811130" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" />
														<f:attribute name="columnName" value="aplCaseRecNum" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>

													<t:commandLink id="caseRecNoLink2" styleClass="clStyle"
														actionListener="#{appealControllerBean.sortAdminHearing}"
														rendered="#{appealDataBean.memAppealImageRender != 'caseRecNoLink2'}"
														onmousedown="javascript:flagWarn=false;">
														<h:graphicImage id="PROVIDERGI20120731164811131"
															alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" />
														<f:attribute name="columnName" value="aplCaseRecNum" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:panelGroup>
											</t:panelGrid>
										</f:facet>

										<hx:requestLink id="PRGCMGTCL41"
											action="#{appealControllerBean.getProvAppealsForCaseRecord}"
											onmousedown="javascript:flagWarn=false;">
											<f:param name="rowIndex" value="#{index}"></f:param>
											<h:outputText id="PRGCMGTOT195"
												value="#{providerAppealResult.caseSK}" />
										</hx:requestLink>

									</t:column>

									<t:column id="memAppeal_type">
										<f:facet name="header">
											<t:panelGrid id="CMGTTOMPGD7" columns="2">
												<t:outputLabel id="CMGTTOMOL7" for="typeLink1"
													value="#{msg['label.memappeals.type']}" />
												<t:panelGroup id="CMGTTOMPGP7">

													<t:commandLink id="typeLink1" styleClass="clStyle"
														actionListener="#{appealControllerBean.sortAdminHearing}"
														rendered="#{appealDataBean.memAppealImageRender != 'typeLink1'}"
														onmousedown="javascript:flagWarn=false;">
														<h:graphicImage id="PROVIDERGI20120731164811132" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" />
														<f:attribute name="columnName" value="type" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>

													<t:commandLink id="typeLink2" styleClass="clStyle"
														actionListener="#{appealControllerBean.sortAdminHearing}"
														rendered="#{appealDataBean.memAppealImageRender != 'typeLink2'}"
														onmousedown="javascript:flagWarn=false;">
														<h:graphicImage id="PROVIDERGI20120731164811133"
															alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" />
														<f:attribute name="columnName" value="type" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:panelGroup>
											</t:panelGrid>
										</f:facet>
										<h:outputText id="PRGCMGTOT196"
											value="#{providerAppealResult.caseAplTyCd}" />
									</t:column>

									<t:column id="memAppeals_appealDt">
										<f:facet name="header">
											<t:panelGrid id="CMGTTOMPGD8" columns="2">
												<t:outputLabel id="CMGTTOMOL8" for="applDtLink1"
													value="#{msg['label.memappeals.appealdate']}" />
												<t:panelGroup id="CMGTTOMPGP8">

													<t:commandLink id="applDtLink1" styleClass="clStyle"
														actionListener="#{appealControllerBean.sortAdminHearing}"
														rendered="#{appealDataBean.memAppealImageRender != 'applDtLink1'}"
														onmousedown="javascript:flagWarn=false;">
														<h:graphicImage id="PROVIDERGI20120731164811134" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" />
														<f:attribute name="columnName" value="appealDt" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>

													<t:commandLink id="applDtLink2" styleClass="clStyle"
														actionListener="#{appealControllerBean.sortAdminHearing}"
														rendered="#{appealDataBean.memAppealImageRender != 'applDtLink2'}"
														onmousedown="javascript:flagWarn=false;">
														<h:graphicImage id="PROVIDERGI20120731164811135"
															alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" />
														<f:attribute name="columnName" value="appealDt" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:panelGroup>
											</t:panelGrid>
										</f:facet>
										<h:outputText id="PRGCMGTOT197"
											value="#{providerAppealResult.caseAplStatCdDt}" />
									</t:column>

									<t:column id="memAppeal_appealSts">
										<f:facet name="header">
											<t:panelGrid id="CMGTTOMPGD9" columns="2">
												<t:outputLabel id="CMGTTOMOL9" for="appealstsLink1"
													value="#{msg['label.memappeals.appealstatus']}" />
												<t:panelGroup id="CMGTTOMPGP9">

													<t:commandLink id="appealstsLink1" styleClass="clStyle"
														actionListener="#{appealControllerBean.sortAdminHearing}"
														rendered="#{appealDataBean.memAppealImageRender != 'appealstsLink1'}"
														onmousedown="javascript:flagWarn=false;">
														<h:graphicImage id="PROVIDERGI20120731164811136" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" />
														<f:attribute name="columnName" value="appealStats" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>

													<t:commandLink id="appealstsLink2" styleClass="clStyle"
														actionListener="#{appealControllerBean.sortAdminHearing}"
														rendered="#{appealDataBean.memAppealImageRender != 'appealstsLink2'}"
														onmousedown="javascript:flagWarn=false;">
														<h:graphicImage id="PROVIDERGI20120731164811137"
															alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" />
														<f:attribute name="columnName" value="appealStats" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:panelGroup>
											</t:panelGrid>
										</f:facet>
										<h:outputText id="PRGCMGTOT198"
											value="#{providerAppealResult.caseAplStatCd}" />
									</t:column>

									<m:div styleClass="clear">
									</m:div>
									<m:div>
										<m:br />
										<m:br />
									</m:div>
								</t:dataTable>
								<m:div styleClass="searchResults">

									<t:dataScroller id="CMGTTOMDS5" for="providerAppealTable"
										paginator="true"
										paginatorActiveColumnStyle='font-weight:bold;'
										paginatorMaxPages="3" immediate="false"
										pageCountVar="pageCount" pageIndexVar="pageIndex"
										firstRowIndexVar="firstRowIndex"
										lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
										styleClass="datascrollerStyle"
										onclick="javascript:flagWarn=false;">
										<f:facet name="previous">
											<h:outputText id="PRGCMGTOT199" styleClass="underline"
												value="#{msg['label.appeals.lt']}"
												rendered="#{pageIndex > 1}"></h:outputText>
										</f:facet>
										<f:facet name="next">
											<h:outputText id="PRGCMGTOT200" styleClass="underline"
												value="#{msg['label.appeals.gt']}"
												rendered="#{pageIndex < pageCount}"></h:outputText>
										</f:facet>
										<h:outputText id="PRGCMGTOT201" rendered="#{rowsCount > 0}"
											value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
											styleClass="floatLeft" />
									</t:dataScroller>

									<h:dataTable var="dummyProviderAppeals"
										rendered="#{!appealDataBean.providerAppealDataFlag}"
										styleClass="dataTable" cellspacing="0" width="100%"
										border="1" headerClass="tableHead" rowClasses="rowone,row_alt"
										id="dummyProviderAppealsTable">

										<t:column id="dummydddatacolumn1">
											<f:facet name="header">
												<h:outputText id="PRGCMGTOT202"
													value="#{msg['label.memappeals.appealcaserecordnumber']}" />
											</f:facet>
										</t:column>
										<t:column id="dummydddatacolumn2">
											<f:facet name="header">
												<h:outputText id="PRGCMGTOT203"
													value="#{msg['label.memappeals.type']}" />
											</f:facet>
										</t:column>
										<t:column id="dummydddatacolumn3">
											<f:facet name="header">
												<h:outputText id="PRGCMGTOT204"
													value="#{msg['label.memappeals.appealdate']}" />
											</f:facet>
										</t:column>
										<t:column id="dummydddatacolumn4">
											<f:facet name="header">
												<h:outputText id="PRGCMGTOT205"
													value="#{msg['label.memappeals.appealstatus']}" />
											</f:facet>
										</t:column>
									</h:dataTable>

									<m:table id="PROVIDERMMT20120731164811138" styleClass="dataTableOne" width="100%" border="0"
										rendered="#{!appealDataBean.providerAppealDataFlag}">
										<m:tr>
											<m:td align="center">
												<h:outputText id="PRGCMGTOT206"
													value="#{msg['label.appeals.nodata']}" />
											</m:td>
										</m:tr>
									</m:table>

								</m:div>
								<m:div styleClass="clear">
								</m:div>
								<m:div id="providerAppeals"
									rendered="#{appealDataBean.showProviderAppealsFlag}">
									<f:subview id="providerAppealsSubViewId">
										<jsp:include page="/jsp/appeals/inc_providerAppealDetails.jsp" />
									</f:subview>
								</m:div>
								<m:br/>
							</m:td>
						</m:tr>

					</m:table>
					</m:div>
					<%--EOF defect ESPRD00335054 --%>
				</m:div>
			</m:div>
		</h:form>
	</m:body>

	<%--
	  var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
	   FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">
	var flagRole = ((typeof(document.getElementById(thisForm+':userHavingUpdateRole') != 'undefined')) && document.getElementById(thisForm+':userHavingUpdateRole') != null ? flagRole = document.getElementById(thisForm+':userHavingUpdateRole').value:true);
	</script>
</f:view>

