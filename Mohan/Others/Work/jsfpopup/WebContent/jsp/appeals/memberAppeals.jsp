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
<%-- Small save Big Save starts --%>
<script language="javascript">
   	flagWarn=true;  
	formId = 'view<portlet:namespace/>:';    
	selectOne=false; 
    fileSavedFlag=false;
    datechanged=false;
  /** Added this function for Big Save/Little Save CR
  This function will tell if there is any data changed on the form
  */
  
  
   function isFormChanged(formId) {     
	 var ele = document.forms[formId].length;
	 for ( i=0; i < ele; i++ ) {
           if (document.forms[formId].elements[i].type != undefined){	      
			 //var isEleChanged = false; 
			
			 switch ( document.forms[formId].elements[i].type ) { 			
			  case "text" : 			    
                if ( document.forms[formId].elements[i].value != document.forms[formId].elements[i].defaultValue ){			    
			    return true;
			   }
			  break;
			
			  case "textarea" : 
			   if ( document.forms[formId].elements[i].value != document.forms[formId].elements[i].defaultValue ){
			
			    return true;
			   }
			  break;
			
			  case "radio" :
			   val = "";
               if ( document.forms[formId].elements[i].checked != document.forms[formId].elements[i].defaultChecked ){
			   	 
			    return true;
			   }
			  break;

			  			
			  case "select-multiple" :
			   for ( var x =0 ; x <document.forms[formId].elements[i].length; x++ ) {

                 if ( document.forms[formId].elements[i].options[ x ].selected 
			      != document.forms[formId].elements[i].options[ x ].defaultSelected ) {
			   		   return true;
			    }
			   }
			  break;
			
			  case "checkbox" :
			    if ( document.forms[formId].elements[i].checked != document.forms[formId].elements[i].defaultChecked ){
			   	
			     return true;
			    }
				break;
				
				case "select-one" :
			    if ( document.forms[formId].elements[i].checked != document.forms[formId].elements[i].defaultChecked ){
			   	
			     return true;
			    }
				break;
				
				case "hidden" : 				
				break;		  
			 }		  
		}		  
	 }
		formId = 'view<portlet:namespace/>:'; 
	 	return false;
	 }
   
 	function warnBeforeExit(frmId) 
 	{
 	    if(formId == 'view<portlet:namespace/>:')
 	    {
 	    	formId=formId+frmId;
 	    }
 	    var smallSaveCount = 0; 	     
		var rowadd=document.getElementById(formId+':internalSubmit').value; 	 
		smallSaveCount=document.getElementById(formId+':smallSaveCount').value;
		if(fileSavedFlag == true)
		{
		     flagWarn=false;
		}
		if(flagWarn==true ){	
		  	if (selectOne || rowadd == 'true' || isFormChanged(formId) == true || smallSaveCount > 0)
		  	{		  		
		  		formId = 'view<portlet:namespace/>:'; 
			    event.returnValue="The data on the page has been modified\n Do you want to discard your changes?";
		  	}
		}		
 	} 
   function addeditrow(frmId)
   {	      
   		if(formId == 'view<portlet:namespace/>:')
 	    {
 	     	formId=formId+frmId;
 	    }
       	document.getElementById(formId+':internalSubmit').value='true';
      	fileSavedFlag=true;
   }
   </script>
<%-- Small save Big Save ends --%>

<script type="text/javascript">
	function renderAudit(id) 
	{ 
		var hiddenValue = document.getElementById('view<portlet:namespace/>:memberAppealsViewForm:memberAppeals:'+id);
	    
		if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
			hideMe('audit_plus');
		} else if ((hiddenValue.value == 'false')) 
		{
			hideMe('audit_plus');
		} 
			
	}
	function renderAdminHearingAudit(id) 
	{ 
		var hiddenValue = document.getElementById('view<portlet:namespace/>:memberAppealsViewForm:memberAppeals:administrativeHearing:editAdministrativeHearing:adminHearAuditlogDetails:'+id);
	    
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
	
	function findObjectByPartOfIDForMemAppeal(partOfID)
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
		var submitMemberSADetails = findObjectByPartOfIDForMemAppeal('submitMemberSADetails');
		submitMemberSADetails.click();
	}
	function TCNActionblur()
	{
		var submitMemberTCNDetails=findObjectByPartOfIDForMemAppeal('tcnCmdbuttonlinkMEMID');
		submitMemberTCNDetails.click();
	}
	/*Added for CR Cursor focus starts*/
		var thisForm = 'view<portlet:namespace/>:memberAppealsViewForm';
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
				document.getElementById('view<portlet:namespace/>:memberAppealsViewForm:memberAppealsSubViewId:administrativeHearingSubViewId2:adminHearingButton').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='adminHearingTable'){
				document.getElementById('view<portlet:namespace/>:memberAppealsViewForm:memberAppealsSubViewId:administrativeHearingSubViewId2:adminHearingTable').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='appealDetails_Type'){
				document.getElementById('view<portlet:namespace/>:memberAppealsViewForm:memberAppealsSubViewId:appealDetails_Type').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='add_AdminHrngDT'){
				document.getElementById('view<portlet:namespace/>:memberAppealsViewForm:memberAppealsSubViewId:administrativeHearingSubViewId2:addAdminHearingFlagID:add_AdminHrngDT').focus();
			}else if(document.getElementById(thisForm+':focusId').value=='edit_AdminHrngDT'){
				document.getElementById('view<portlet:namespace/>:memberAppealsViewForm:memberAppealsSubViewId:administrativeHearingSubViewId2:editAdminHearingFlagID:edit_AdminHrngDT').focus();
			} 
   			var focusPage = '#'+document.getElementById(thisForm+':focusId').value;
			if(focusPage != '' && focusPage != '#') {				
				document.location.href=focusPage;
			}{
				document.location.href = '#memberAppealFocus';
				window.scrollBy(0, -28);
			}
			<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
			<%--var rightClickInd=document.getElementById(thisForm+':hyperLinkID').value;			
			if(rightClickInd!=null && rightClickInd!=""){
				document.getElementById(thisForm+':hyperLinkID').value="";				
				document.getElementById(thisForm+':'+rightClickInd).click();
			}--%>
   		}
   		/*Added for CR Cursor focus ends*/  
	</script>

<f:view>
	<f:subview id="provService">

		<jsp:include page="/jsp/script/scripts.jsp" />

	</f:subview>
	<%--COMMENTED FOR DEFECT ESPRD00682817 STARTS--%>
	<%-- <t:saveState id="CMGTTOMSS7" value="#{appealDataBean}"></t:saveState>--%>
	<%--COMMENTED FOR DEFECT ESPRD00682817 ENDS--%>

	<t:saveState id="CMGTTOMSS8" value="#{appealDataBean.smallSaveCount}" />


	<body
		onload="warnBeforeCancel();renderAudit('audit_open');renderAdminHearingAudit('admin_hear_audit_open')">
			<m:anchor name="memberAppealFocus"></m:anchor>
	<h:form id="memberAppealsViewForm">
		<m:div id="memberAppealDiv01" styleClass="floatContainer">
				<h:inputHidden id="internalSubmit" value=" "></h:inputHidden>
				<h:inputHidden id="smallSaveCount"
					value="#{appealDataBean.smallSaveCount}"></h:inputHidden>
				<!--  Added for Defect 412572 implementation -->
				<h:inputHidden id="PRGCMGTIH4"
					value="#{appealControllerBean.loadValidValues}"
					rendered="#{appealDataBean.validValueFlag}"></h:inputHidden>
				<h:inputHidden id="PRGCMGTIH5"
					value="#{appealControllerBean.link2Show}"></h:inputHidden>
				<h:inputHidden id="userHavingUpdateRole"
					value="#{appealDataBean.controlRequired}" />
				<h:inputHidden id="PRGCMGTIH6"
					value="#{appealControllerBean.memberAppealsSearch}"></h:inputHidden>
				<h:inputHidden id="focusId"
					value="#{appealDataBean.cursorFocusValue}"></h:inputHidden>
				<%--Holds the Entity details for displaying on click of Print Selected--%>
				<h:inputHidden id="HDNENTITYID" value="#{appealDataBean.commonMemberDetailsVO.altID}" />
				<h:inputHidden id="HDNENTITYTYPE" value="#{appealDataBean.entityType}" />
				<h:inputHidden id="HDNENTITYNAME" value="#{appealDataBean.commonMemberDetailsVO.memberName}" />
				<%-- Commented for defect ESPRD00828394 and moved to rightClickProgress.jsp --%>
				<%--<h:inputHidden id="hyperLinkID" value="#{appealDataBean.rightClickIndicator }"></h:inputHidden>--%>
			<m:div rendered="#{appealDataBean.cancelFlag}">
			<m:div id="memberAppealDiv02" style="width:100%">
				<%-- Small save Big Save starts --%>
			
					<m:div styleClass="moreInfoBar">
						<m:div styleClass="actions">
							<m:table id="PROVIDERMMT20120731164811111" styleClass="tableBar" width="100%">
								<m:tr>
									<m:td>
										<m:div styleClass="actionBarRequired">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT160"
													value="#{msg['msg.appeals.required']}" />
											</m:span>
										</m:div>
									</m:td>

									<m:td align="right">

										<t:commandLink id="PRGCMGTCL30" styleClass="strong"
											action="#{appealControllerBean.saveAppeal}"
											onmousedown="javascript:flagWarn=false;"
											onclick="javascript:flagWarn=false;"
											rendered="#{!appealDataBean.disableSaveMemApp}">
											<h:outputText id="PRGCMGTOT161"
												value="#{msg['label.appeals.save']}" />
										</t:commandLink>
										<h:outputText id="PRGCMGTOT162"
											value="#{msg['label.appeals.save']}"
											rendered="#{appealDataBean.disableSaveMemApp}" />
										<h:outputText id="PRGCMGTOT163"
											value="#{msg['label.appeals.pipe']}" />

										<t:commandLink id="PRGCMGTCL31"
											action="#{appealControllerBean.resetMemberAppeals}"
											onmousedown="javascript:flagWarn=false;"
											onclick="javascript:flagWarn=false;"
											rendered="#{!appealDataBean.disableResetMemApp}">
											<h:outputText id="PRGCMGTOT164"
												value="#{msg['label.appeals.reset']}" />
										</t:commandLink>
										<h:outputText id="PRGCMGTOT165"
											value="#{msg['label.appeals.reset']}"
											rendered="#{appealDataBean.disableResetMemApp}" />
										<h:outputText id="PRGCMGTOT166"
											value="#{msg['label.appeals.pipe']}" />
										<%-- ESPRD00509203_ProviderAppeals_03AUG2010--%>
										<t:commandLink id="PRGCMGTCL32"
											action="#{appealControllerBean.enableAppealDetailAudit}"
											onmousedown="javascript:flagWarn=false;"
											onclick="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT167"
												value="#{msg['label.appeals.auditLog']}" />
										</t:commandLink>
										<h:outputText id="PRGCMGTOT168"
											value="#{msg['label.appeals.pipe']}" />

										<%--EOF ESPRD00509203_ProviderAppeals_03AUG2010 --%>

										<t:commandLink id="PRGCMGTCL33"
											action="#{appealControllerBean.cancelMemberAppeals}">
											<h:outputText id="PRGCMGTOT169"
												value="#{msg['label.appeals.cancel']}" />
										</t:commandLink>

									</m:td>

								</m:tr>
							</m:table>
						</m:div>
					</m:div>

						<m:div rendered="#{appealDataBean.showFinalSuccessMsgFlag}">
							<h:messages layout="table" showDetail="true"
								styleClass="successMsg	" id="appealSuccessMessage"
								showSummary="false" style="color: red"></h:messages>
						</m:div>

					<%--ESPRD00509203_ProviderAppeals_03AUG2010 
<m:div id="memberAppealDataFlag" rendered="#{appealDataBean.memberAppealDataFlag}">--%>
					<m:div id="memEnableAppealDetailAudit"
						rendered="#{appealDataBean.enableAppealDetailAudit}">
						<audit:auditTableSet id="memAppealAuditId"
							value="#{appealDataBean.appealVO.auditKeyList}"
							numOfRecPerPage="10"></audit:auditTableSet>
					</m:div>
					<%--/m:div--%>

					<%--EOF ESPRD00509203_ProviderAppeals_03AUG2010 --%>
					<m:div styleClass="moreInfoContent">

						<h4>Member Information</h4>
						<m:section id="PROVIDERMMS20120731164811112" styleClass="otherbg">
							<m:table id="PROVIDERMMT20120731164811113" cellspacing="0" width="100%">
								<m:tr>
									<m:td>
										<m:div>
											<h:outputText id="PRGCMGTOT170"
												value="#{msg['label.appeals.provappeals.membername']}">
											</h:outputText>
											<h:outputText id="memAppeals_MemName"
												value="#{appealDataBean.commonMemberDetailsVO.memberName}"></h:outputText>
										</m:div>
									</m:td>
									<m:td>
										<m:div>
											<h:outputText id="PRGCMGTOT171"
												value="#{msg['label.appeals.memappeals.medicaidid']}">
											</h:outputText>
											<h:outputText id="memAppeals_MemID"
												value="#{appealDataBean.commonMemberDetailsVO.altID}"></h:outputText>
										</m:div>
									</m:td>
								</m:tr>
							</m:table>
						</m:section>
					</m:div>



					<m:div>
						<m:br />
					</m:div>
					<m:table id="PROVIDERMMT20120731164811114" styleClass="tableBar" width="100%">
						<m:tr>
							<m:td styleClass="tableTitle">
								<h:outputText id="PRGCMGTOT172"
									value="#{msg['label.memappeals.appeals']}" />
							</m:td>
						</m:tr>
					</m:table>


					<t:dataTable id="memberAppealTable" border="1" cellpadding="0"
						cellspacing="0" columnClasses="columnClass"
						headerClass="headerClass" rows="3" footerClass="footerClass"
						rowClasses="row_alt,rowone" styleClass="dataTable" width="100%"
						var="memberAppealResult"
						value="#{appealDataBean.memberAppealList}"
						rendered="#{appealDataBean.memberAppealDataFlag}"
						onmouseover="return tableMouseOver(this, event);"
						onmouseout="return tableMouseOut(this, event);"
						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
						rowIndexVar="index"
						onmousedown="javascript:focusThis('appealDetails_Type');"
						rowOnClick="javascript:childNodes[0].lastChild.click();flagWarn=false;">


						<h:column id="memAppeal_aplCsRecNo">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD27" columns="2">
									<h:outputLabel id="PRGCMGTOLL122" for="memApl_caseNo"
										value="#{msg['label.memappeals.appealcaserecordnumber']}" />
									<h:panelGroup id="PRGCMGTPGP27">
									<m:div style="width:px;align=center">
										<t:commandLink id="caseRecNoLink1" styleClass="clStyle"
											actionListener="#{appealControllerBean.sortAdminHearing}"
											rendered="#{appealDataBean.memAppealImageRender != 'caseRecNoLink1'}"
											onmousedown="javascript:flagWarn=false;">
											<h:graphicImage id="PROVIDERGI20120731164811115" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
												styleClass="sort_asc" width="8" url="/images/x.gif" />
											<f:attribute name="columnName" value="aplCaseRecNum" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</m:div>
										<t:commandLink id="caseRecNoLink2" styleClass="clStyle"
											actionListener="#{appealControllerBean.sortAdminHearing}"
											rendered="#{appealDataBean.memAppealImageRender != 'caseRecNoLink2'}"
											onmousedown="javascript:flagWarn=false;">
											<h:graphicImage id="PROVIDERGI20120731164811116" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
												styleClass="sort_desc" width="8" url="/images/x.gif" />
											<f:attribute name="columnName" value="aplCaseRecNum" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>

									</h:panelGroup>
								</h:panelGrid>
							</f:facet>



							<hx:requestLink id="PRGCMGTCL34"
								action="#{appealControllerBean.getMemAppealsForCaseRecord}"
								onmousedown="javascript:flagWarn=false;">
								<f:param name="rowIndex" value="#{index}"></f:param>
								<h:outputText id="memApl_caseNo"
									value="#{memberAppealResult.caseSK}" />
							</hx:requestLink>

						</h:column>

						<h:column id="memAppeal_type">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD28" columns="2">
									<h:outputLabel id="PRGCMGTOLL123" for="memApl_type"
										value="#{msg['label.memappeals.type']}" />
									<h:panelGroup id="PRGCMGTPGP28">
										<m:div style="width:px;align=center">

											<t:commandLink id="typeLink1" styleClass="clStyle"
												actionListener="#{appealControllerBean.sortAdminHearing}"
												rendered="#{appealDataBean.memAppealImageRender != 'typeLink1'}"
												onmousedown="javascript:flagWarn=false;">
												<h:graphicImage id="PROVIDERGI20120731164811117" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="type" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>

										</m:div>

										<t:commandLink id="typeLink2" style="text-decoration: none;"
											actionListener="#{appealControllerBean.sortAdminHearing}"
											rendered="#{appealDataBean.memAppealImageRender != 'typeLink2'}"
											onmousedown="javascript:flagWarn=false;">
											<h:graphicImage id="PROVIDERGI20120731164811118" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
												styleClass="sort_desc" width="8" url="/images/x.gif" />
											<f:attribute name="columnName" value="type" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>

									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="memApl_type"
								value="#{memberAppealResult.caseAplTyCd}" />
						</h:column>

						<h:column id="memAppeals_appealDt">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD29" columns="2">
									<h:outputLabel id="PRGCMGTOLL124" for="memApl_aplDt"
										value="#{msg['label.memappeals.appealdate']}" />
									<h:panelGroup id="PRGCMGTPGP29">
										<m:div style="width:px;align=center">

											<t:commandLink id="applDtLink1" styleClass="clStyle"
												actionListener="#{appealControllerBean.sortAdminHearing}"
												rendered="#{appealDataBean.memAppealImageRender != 'applDtLink1'}"
												onmousedown="javascript:flagWarn=false;">
												<h:graphicImage id="PROVIDERGI20120731164811119" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="appealDt" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>

										</m:div>

										<t:commandLink id="applDtLink2" style="text-decoration: none;"
											actionListener="#{appealControllerBean.sortAdminHearing}"
											rendered="#{appealDataBean.memAppealImageRender != 'applDtLink2'}"
											onmousedown="javascript:flagWarn=false;">
											<h:graphicImage id="PROVIDERGI20120731164811120" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
												styleClass="sort_desc" width="8" url="/images/x.gif" />
											<f:attribute name="columnName" value="appealDt" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>

									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="memApl_aplDt"
								value="#{memberAppealResult.aplDt}" />
						</h:column>

						<h:column id="memAppeal_appealSts">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD30" columns="2">
									<h:outputLabel id="PRGCMGTOLL125" for="memApl_aplSts"
										value="#{msg['label.memappeals.appealstatus']}" />
									<h:panelGroup id="PRGCMGTPGP30">
										<m:div style="width:px;align=center">

											<t:commandLink id="appealstsLink1" styleClass="clStyle"
												actionListener="#{appealControllerBean.sortAdminHearing}"
												rendered="#{appealDataBean.memAppealImageRender != 'appealstsLink1'}"
												onmousedown="javascript:flagWarn=false;">
												<h:graphicImage id="PROVIDERGI20120731164811121" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="appealStats" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>

										</m:div>

										<t:commandLink id="appealstsLink2" styleClass="clStyle"
											actionListener="#{appealControllerBean.sortAdminHearing}"
											rendered="#{appealDataBean.memAppealImageRender != 'appealstsLink2'}"
											onmousedown="javascript:flagWarn=false;">
											<h:graphicImage id="PROVIDERGI20120731164811122" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
												styleClass="sort_desc" width="8" url="/images/x.gif" />
											<f:attribute name="columnName" value="appealStats" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>

									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="memApl_aplSts"
								value="#{memberAppealResult.caseAplStatCd}" />
						</h:column>

						<m:div styleClass="clear">
						</m:div>
						<m:div>
							<m:br />
							<m:br />
						</m:div>

					</t:dataTable>
					<m:div styleClass="searchResults">

						<t:dataScroller id="CMGTTOMDS4" for="memberAppealTable"
							paginator="true" onclick="javascript:flagWarn=false;"
							paginatorActiveColumnStyle='font-weight:bold;'
							paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"
							pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"
							lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
							styleClass="datascrollerStyle"
							onclick="javascript:flagWarn=false;">
							<f:facet name="previous">
								<h:outputText id="PRGCMGTOT173" styleClass="underline"
									value="#{msg['label.appeals.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
							</f:facet>
							<f:facet name="next">
								<h:outputText id="PRGCMGTOT174" styleClass="underline"
									value="#{msg['label.appeals.gt']}"
									rendered="#{pageIndex < pageCount}"></h:outputText>
							</f:facet>
							<h:outputText id="PRGCMGTOT175" rendered="#{rowsCount > 0}"
								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								styleClass="floatLeft" />
						</t:dataScroller>

						<t:dataTable var="dummyMemberAppeals"
							rendered="#{!appealDataBean.memberAppealDataFlag}"
							styleClass="dataTable" cellspacing="0" width="100%" border="1"
							headerClass="tableHead" rowClasses="rowone,row_alt"
							id="dummyMemberAppealsTable">

							<t:column id="dummydddatacolumn1">
								<f:facet name="header">
									<h:outputText id="PRGCMGTOT176"
										value="#{msg['label.memappeals.appealcaserecordnumber']}" />
								</f:facet>
							</t:column>
							<t:column id="dummydddatacolumn2">
								<f:facet name="header">
									<h:outputText id="PRGCMGTOT177"
										value="#{msg['label.memappeals.type']}" />
								</f:facet>
							</t:column>
							<t:column id="dummydddatacolumn3">
								<f:facet name="header">
									<h:outputText id="PRGCMGTOT178"
										value="#{msg['label.memappeals.appealdate']}" />
								</f:facet>
							</t:column>
							<t:column id="dummydddatacolumn4">
								<f:facet name="header">
									<h:outputText id="PRGCMGTOT179"
										value="#{msg['label.memappeals.appealstatus']}" />
								</f:facet>
							</t:column>
						</t:dataTable>

						<m:table id="PROVIDERMMT20120731164811123" styleClass="dataTable" width="100%" border="0"
							rendered="#{!appealDataBean.memberAppealDataFlag}">
							<m:tr>
								<m:td align="center">
									<h:outputText id="PRGCMGTOT180"
										value="#{msg['label.appeals.nodata']}" />
								</m:td>
							</m:tr>
						</m:table>

					</m:div>
					<m:div styleClass="clear">
					</m:div>
					<m:div id="memberAppeals"
						rendered="#{appealDataBean.showMemberAppealsFlag}">
						<f:subview id="memberAppealsSubViewId">
							<jsp:include page="/jsp/appeals/inc_memberAppealDetails.jsp" />
						</f:subview>
					</m:div>
					<m:br/>
				</m:div>
			</m:div>
		</m:div>
	</h:form>
	</body>
	<%--
	  var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
	   FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">
	var flagRole = ((typeof(document.getElementById(thisForm+':userHavingUpdateRole') != 'undefined')) && document.getElementById(thisForm+':userHavingUpdateRole') != null ? flagRole = document.getElementById(thisForm+':userHavingUpdateRole').value:true);
	</script>
</f:view>

