<%-- 
  - Author(s): Wipro
  - Date: Mon Sep 10 16:10:14 IST 2007
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
  --%>
 <%--
  
  Portlet 8.0 Migration Activity: h:commandButton with param is not supported in portal 8.0, replacing with myfaces t:commandButton tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>


<portlet:defineObjects />



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceLog"
	var="crspd" />
	
<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ClientServices"
	var="clientServ" />

<script type="text/javascript">

<%-- Small save Big Save start --%>
	frmId = 'view<portlet:namespace/>:logCorrespondence';
<%-- Small save Big Save ends --%>

/* This method is used to fire the event when the user changes the Category value. */
function categoryChangeAction(formID, id)
{
	document.getElementById('view<portlet:namespace/>:'+formID+':'+id).click();
}

function focusComponent()
{
    var fid = 'view<portlet:namespace/>:logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:messageExpln';
	var fid1 ='view<portlet:namespace/>:logCorrespondence:commonNotes:cmnNoteHist:notescmd6'; 
    var noteFocus='view<portlet:namespace/>:logCorrespondence:notesFocusflag'; 
	if (fid != null && document.getElementById(fid)!=null) {
		document.getElementById(fid).focus();
	}
	else if (fid1 != null && document.getElementById(fid1)!=null&&document.getElementById(noteFocus)!=null ) {
		document.getElementById(fid1).focus();
	}
}


/** This method confirms the Delete Operation */    
function confirmDelete(formID, id) 
{ 
	var box = confirm("Are you sure that you want to Delete?");
           
    if(box == true){
    	document.getElementById('view<portlet:namespace/>:'+formID+':'+id).click();                         
    }          
}

/** This method calls the respective operation */    
function callMethod(formID, id) 
{ 
	document.getElementById('view<portlet:namespace/>:'+formID+':'+id).click();
}


/** 
*Used for Audit 
**/

function toggleTest(obj,state){
 var el = document.getElementById(obj);

 if (state==1){
  el.style.display = 'block';
 }
 else if (state==0){
  el.style.display = 'none';
 }
 else if (state==2){
  if (el.style.display == 'none'){
   el.style.display = 'block'; 
  }
  else if ((el.style.display == 'block') || (el.style.display == '')){
   el.style.display = 'none';
  }
 }
}
 
/*
 * Used to display either '+' image or '-' image when a section 
 * is closed or expanded respectively
 */
function plusMinusForRefreshTest(id,obj,hiddenTextId)
{
 var hiddenTxt = find(hiddenTextId);
 var el = document.getElementById(id);


 if (el.style.display == 'none')
 {
  obj.className = 'plus';
  hiddenTxt.value = 'plus';
 }
 else if ((el.style.display == 'block')  || (el.style.display == ''))
 {
  obj.className = 'minus';
  hiddenTxt.value = 'minus';
 }
 else if (el.style.display == '')
 {
  obj.className = 'minus';
  hiddenTxt.value = 'minus';
 }
}
 

function renderAudit(id1,id2,id3) 
{ 
   
	
    var hiddenValueForAlert = document.getElementById('view<portlet:namespace/>:logCorrespondence:logCrspdAlrtSubview:logCrspdAlertsSubview:alertAuditLogSubview:'+id1);
    var hiddenValueForRouting = document.getElementById('view<portlet:namespace/>:logCorrespondence:logCrspdRouSubview:logCrspdRoutingSubview:routingAuditLogSubview'+id2); 
	var hiddenValueForAttachment = document.getElementById('view<portlet:namespace/>:logCorrespondence:logCrspdAttmntsSubview:logCrspdAttachmentsSubview:editAttachments:editAttachAudit_cr'+id3); 
	
	
    
	    
	/** FOR ALERTS AUDIT */
	if ((hiddenValueForAlert == null) ||(hiddenValueForAlert == '')|| (hiddenValueForAlert.length == 0)) {
	  
	   
	    hideMe('audit_plus_alert');
	} 
	else if ((hiddenValueForAlert.value == 'false')) 
	{
	  
		hideMe('audit_plus_alert');
	} 
	
	/** FOR ROUTING AUDIT */
	
	if ((hiddenValueForRouting == null) ||(hiddenValueForRouting == '')|| (hiddenValueForRouting.length == 0)) {
	    
	   
	    hideMe('audit_plus_routing');
	} 
	else if ((hiddenValueForRouting.value == 'false')) 
	{
	   
		hideMe('audit_plus_routing');
	} 
	
	/** FOR ATTACHMENT AUDIT */
	
	if ((hiddenValueForAttachment == null) ||(hiddenValueForAttachment == '')|| (hiddenValueForAttachment.length == 0)) {
	 
	   
	    hideMe('audit_plus_attach');
	} 
	else if ((hiddenValueForAttachment.value == 'false')) 
	{
	  
		hideMe('audit_plus_attach');
	} 
		
}
function doMaintainEntityClickAction(link)
		{						
					var answer = confirm("Are you sure you want to navigate away from this page?");				
					if(answer)
					{						
						link.fireEvent('onclick');
					}
							
		}
		
function doCommonClickCancelAction(link)
		{						
					var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to navigate?");				
					if(answer)
					{
						link.fireEvent('onclick');
					}
							
		}
		function focusInqComponent()
		{
		    var fid = 'view<portlet:namespace/>:logCorrespondence:logCrspdDtls:focusInqHereAftrAdding';
			var objVal = findObjectByPartOfID('focusInqHidVal');
			//alert('objVal'+objVal.value)
			if(objVal.value=='1')
			{
				
				if (document.getElementById(fid)!=null) {
					//alert('fid not null');
					document.getElementById(fid).focus();
				}
				else
				{
					//alert('fid null');
					var focusPage = '#'+document.getElementById(fid).value;
					if(focusPage != '' && focusPage != '#') {				
						document.location.href=focusPage;
					}
				}
			}
		}
		function findObjectByPartOfID(partOfID)
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

</script>

<script>
		var thisForm = 'view<portlet:namespace/>:logCorrespondence';
		function focusThis(id) 
		{
			if(id == 'CMGTTOMDS30')
			{
				//alert('Inside alert');
				var nextId = 'view<portlet:namespace/>:logCorrespondence:logCrspdAlrtSubview:logCrspdAlertsSubview:CMGTTOMDS30next';
				//alert(nextId);
				var previousId = 'view<portlet:namespace/>:logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:CMGTTOMDS30previous';
				//alert(previousId);
				if (document.getElementById(nextId) != null || document.getElementById(previousId) != null)
				{
					id = 'searchResultFocusforAlert';
				}  
			}
			
			if(id == 'cat1')
			{
				//id = document.getElementById(thisForm+':logCrspdDtls:workUnitGroup').id;
				id = document.getElementById(thisForm+':categoryFocus').id;
				document.getElementById(thisForm+':logCrSmallSaveFlag').value='true'
			}
			if(id == 'focusInqHereAftrAdding')
			{
				id = document.getElementById(thisForm+':logCrspdDtls:focusInqHereAftrAdding').id;
				document.getElementById(thisForm+':logCrSmallSaveFlag').value='true'
			}
			if(id == 'LtrResDataScr')
			{
				var nextId = 'view<portlet:namespace/>:logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:LtrResDataScrnext';
				var previousId = 'view<portlet:namespace/>:logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:LtrResDataScrprevious';
				if (document.getElementById(nextId) != null || document.getElementById(previousId) != null)
				{
					id = 'searchResultFocus';
				}  
			}
	   		document.getElementById(thisForm+':focusId').value=id; 
	   	}						
		if (window.addEventListener) //DOM method for binding an event
            	window.addEventListener("load", onLoadFunctions, false);
      	else if (window.attachEvent) //IE exclusive method for binding an event
            	window.attachEvent("onload", onLoadFunctions);
      	else if (document.getElementById) //support older modern browsers
           		window.onload=onLoadFunctions;
		
		function onLoadFunctions() {
			
			var createltrIPC = document.getElementById(thisForm + ':createltrIPCIND').value;
			var viewLetterRequestIPC = document.getElementById(thisForm + ':viewLetterReqIPCIND').value;
			if(createltrIPC!=null && createltrIPC != "") { 
				//alert('inside if Create Letter');
				flagWarn=false;
				document.getElementById(thisForm + ':createltrIPCIND').value =""; 
				document.getElementById(thisForm+':'+createltrIPC).click();
			}
			 //Added as a part of Defect ESPRD00796178, Right Click Issue of 'View Letter Request' link.
			if(viewLetterRequestIPC != null && viewLetterRequestIPC != "") 
			{ 
				flagWarn = false;
				document.getElementById(thisForm + ':viewLetterReqIPCIND').value = ""; 
				document.getElementById(thisForm + ':' + viewLetterRequestIPC).click();
			}
			//Code ended for Defect ESPRD00796178
			focusOnLoad();
			cursorFocus();	
		}

		function focusOnLoad() {
		//find the user browser
		var brow=(navigator.appName);

		if(brow == 'Microsoft Internet Explorer'){
			//alert("its internet explorer");
		}else if(brow == 'Netscape'){
			//alert("its Mozilla");
			jscalendarInit();
		}
		
   		var focusPage = '#'+document.getElementById(thisForm+':focusId').value;
			if(focusPage != '' && focusPage != '#') {				
				document.location.href=focusPage;
			}
   		}
   		function cursorFocus(){
			thisForm = 'view<portlet:namespace/>:logCorrespondence';
			//otherForm = 'view<portlet:namespace/>:inqAbtEntity';
			//var cursorFocusInq=document.getElementById(otherForm+':testingId');
			//alert(otherForm+':testingId'+''+cursorFocusInq)

			var cursorFocus=document.getElementById(thisForm+':CURSORFOCUSID');
			if(cursorFocus!=null && cursorFocus!=''){
				var cursorId=findObjectByPartOfID(cursorFocus.value);
				if(cursorId!=null){
					//alert(cursorId.value);
					cursorId.focus();
					cursorFocus.value='';
				}
			}
		}
	</script>
	


<f:view>




	<%--<t:saveState id="CMGTTOMSS266" value="#{CorrespondenceDataBean}" />
	<t:saveState id="CMGTTOMSS267" value="#{CorrespondenceDataBean.renderCrspdForVO}" />
	<t:saveState id="CMGTTOMSS268" value="#{CorrespondenceDataBean.renderCrspdMemberForVO}" />
	<t:saveState id="CMGTTOMSS269" value="#{CorrespondenceDataBean.renderCrspdProviderForVO}" />
	<t:saveState id="CMGTTOMSS270" value="#{CorrespondenceDataBean.departmentsList}" />
	<t:saveState id="CMGTTOMSS271" value="#{CorrespondenceDataBean.categoryList}" />
	<t:saveState id="CMGTTOMSS272" value="#{CorrespondenceDataBean.priorityVVList}" />
	<t:saveState id="CMGTTOMSS273" value="#{CorrespondenceDataBean.lobVVList}" />
	<t:saveState id="CMGTTOMSS274" value="#{CorrespondenceDataBean.statValidValues}" />
	<t:saveState id="CMGTTOMSS275" value="#{CorrespondenceDataBean.sourceVVList}" />
	<t:saveState id="CMGTTOMSS276" value="#{CorrespondenceDataBean.resolnVVList}" />
	<t:saveState id="CMGTTOMSS277" value="#{CorrespondenceDataBean.subjectValidValues}" />
	<t:saveState id="CMGTTOMSS278" value="#{CorrespondenceDataBean.departmentsList}" />
	<t:saveState id="CMGTTOMSS279" value="#{CorrespondenceDataBean.categorySubjectValues}" />
	<t:saveState id="CMGTTOMSS280" value="#{CorrespondenceDataBean.listOfContacts}" />
	<t:saveState id="CMGTTOMSS281" value="#{CorrespondenceDataBean.listOfAlternateIds}" />	
	<t:saveState id="CMGTTOMSS282" value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.listOfEnquiryAboutEntities}" />
	
	--%>
	<t:saveState id="CMGTTOMSS283" value="#{CorrespondenceDataBean.crNotesList}" />
	<t:saveState id="CMGTTOMSS284" value="#{CorrespondenceDataBean.crContactList}" />
	<t:saveState id="CMGTTOMSS285"		value="#{CorrespondenceDataBean.correspondenceRecordVO.listOfAssociatedCRs}" />
	<t:saveState id="CMGTTOMSS286"		value="#{CorrespondenceDataBean.correspondenceRecordVO.existingCRLists}" />
	
		
<%-- Small save Big Save start --%>
	<f:subview id="provService">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>
	<body onload="focusComponent();">
<%-- Small save Big Save ends --%>


	<h:panelGroup id="PRGCMGTPGP70" rendered="#{CorrespondenceControllerBean.receiveMessage}"></h:panelGroup>
<hx:scriptCollector>
	 <h:form id="logCorrespondence">
	  <%--Below code is added for 508-Para M complience for CR ESPRD00860876 --%>
	 	 	<m:div id="logcrMainDiv01" styleClass="floatContainer">
				<m:div id="logcrMainDiv02" style="width:100%">
	        <h:inputHidden id="controlRequiredForLogCr" value="#{CorrespondenceDataBean.controlRequired}" />
			 <h:inputHidden id="CURSORFOCUSID" value="#{CorrespondenceDataBean.cursorFocus}" />
			<h:inputHidden id="logCrSmallSaveFlag" value="#{commonEntityDataBean.smallSaveFlag}"></h:inputHidden>
			<h:inputHidden id="focusId" value="#{CorrespondenceDataBean.inputHiddenId}" />
			<h:inputHidden id="focusInqHidVal" value="#{CorrespondenceControllerBean.focusInqHidVal}" />
			<h:inputHidden id="createltrIPCIND"
				value="#{LettersAndResponsesDataBean.createLetter}"></h:inputHidden>
			<%--Added as a part of Defect ESPRD00796178,Right Click issue of 'View Letter Request' link --%>
			<h:inputHidden id="viewLetterReqIPCIND" value="#{LettersAndResponsesDataBean.viewLetterReq}"></h:inputHidden>
			<m:body onload="renderAudit('audit_open_alert','audit_open_routing','audit_open_attach');" >
			<%-- <hx:scriptCollector id="scriptCollector1"> --%>
			
			<m:div id="categoryFocus">
				<m:div styleClass="moreInfoBar">
					<m:div styleClass="infoTitle" align="left">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT950" value="#{ent['label-sw-reqFld']}"></h:outputText>
						</m:span>
					</m:div>
					<m:div  align="right" rendered="#{CorrespondenceDataBean.controlRequired}">

						<m:div styleClass="infoActions">
							<m:div id="crCancelId114" rendered="#{!CorrespondenceDataBean.updateMode && !CorrespondenceDataBean.navigateTOLogCase}">
								<f:verbatim><a href="/wps/myportal/SearchCREntity" id="CrTOCrSr1" ></f:verbatim>
									<h:outputText id="PRGCMGTOT951" value="#{ent['label-sw-cancel']}"></h:outputText>
								<f:verbatim></a></f:verbatim>
							</m:div>
							<m:div id="crCancelId115" rendered="#{CorrespondenceDataBean.updateMode && !CorrespondenceDataBean.navigateTOLogCase}">
								<f:verbatim><a href="/wps/myportal/SearchCorrespondence" id="CrTOLC2" ></f:verbatim>
									<h:outputText id="PRGCMGTOT952" value="#{ent['label-sw-cancel']}"></h:outputText>
								<f:verbatim></a></f:verbatim>
							</m:div>
							<m:div id="crCancelId116" rendered="#{CorrespondenceDataBean.navigateTOLogCase}">
								<f:verbatim><a href="/wps/myportal/FromLogCase" id="ToLogCase3" ></f:verbatim>
									<h:outputText id="PRGCMGTOT664" value="#{ent['label-sw-cancel']}"></h:outputText>
								<f:verbatim></a></f:verbatim>
							</m:div>
						</m:div>

						<m:div styleClass="infoActions">
							<h:outputText id="PRGCMGTOT953" escape="false" value="#{ref['label.ref.linkSeperator']}" />
						</m:div>
						<%--<h:commandLink id="PRGCMGTCL146" rendered="#{CorrespondenceDataBean.crAssignedTo}"						action="#{CorrespondenceControllerBean.resetCorrespondence}" 						onmousedown="javascript:flagWarn=false;">
						<h:outputText id="PRGCMGTOT954" value="#{ent['label-sw-reset']}" />
						</h:commandLink>--%>
						<m:div styleClass="infoActions">
						<h:commandButton id="PRGCMGTCB32"			rendered="#{CorrespondenceDataBean.crAssignedTo}"									onmousedown="javascript:flagWarn=false;"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						disabled="#{!CorrespondenceDataBean.controlRequired}"						value="#{ent['label-sw-reset']}"						action="#{CorrespondenceControllerBean.resetCorrespondence}"  />
						<h:outputText id="PRGCMGTOT955" value="#{ent['label-sw-reset']}"							 rendered="#{!CorrespondenceDataBean.crAssignedTo}" /> 
						</m:div>
						<m:div styleClass="infoActions">
						<h:outputText id="PRGCMGTOT956" escape="false"							value="#{ref['label.ref.linkSeperator']}" />
						</m:div>

						<%--<h:commandLink id="PRGCMGTCL147" action="#{CorrespondenceControllerBean.showAuditLog}"						onmousedown="javascript:flagWarn=false;">
						<h:outputText id="PRGCMGTOT957" value="#{ref['label.ref.auditLog']}" />
						</h:commandLink>--%>
						<m:div styleClass="infoActions">
								<h:commandButton id="PRGCMGTCB33"								onmousedown="javascript:flagWarn=false;"								style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:56px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"																value="#{ref['label.ref.auditLog']}"								action="#{CorrespondenceControllerBean.doAuditKeyListOperation}" />
						</m:div>
						<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT958" escape="false" value="#{ref['label.ref.linkSeperator']}" />
						</m:div>

						<%--<h:outputLink value="#{crspd['link.crspd.SearchCREntity']}" id="sssss"  rendered="#{CorrespondenceDataBean.controlRequired >




















						<h:outputText value="#{crspd['label.crspd.new']}" id="kkk" />
						</h:outputLink>
						<h:outputLabel  id="ssssss"						value="#{crspd['label.crspd.new']}" rendered="#{!CorrespondenceDataBean.controlRequired}"/>--%>
						<m:div styleClass="infoActions">
						<m:div id="newLinkId11" rendered="#{CorrespondenceDataBean.controlRequired}">
							<f:verbatim><a href="/wps/myportal/SearchCREntity" id="CrTONewCr11" ></f:verbatim>
								<h:outputText id="srchLink2aaa"								value="#{crspd['label.crspd.new']}"></h:outputText>
							<f:verbatim></a></f:verbatim>
						</m:div>
						<h:outputText  id="srchLink3aaa"								value="#{crspd['label.crspd.new']}" rendered="#{!CorrespondenceDataBean.controlRequired}"/>
						</m:div>
						<m:div styleClass="infoActions">
						<h:outputText id="PRGCMGTOT959" escape="false"							value="#{ref['label.ref.linkSeperator']}" />                           
						</m:div>

						<%--<h:commandLink id="PRGCMGTCL148" rendered="#{CorrespondenceDataBean.updateMode}"							action="#{CorrespondenceControllerBean.additionalCorrespondence}">
							<h:outputText id="PRGCMGTOT960" value="#{crspd['label.crspd.addnlcrspd']}" />
						</h:commandLink>--%>
						<m:div styleClass="infoActions">
						<h:commandButton id="PRGCMGTCB34"						rendered="#{CorrespondenceDataBean.updateMode}"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:157px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						disabled="#{!CorrespondenceDataBean.controlRequired || CorrespondenceDataBean.crClosed}"						value="#{crspd['label.crspd.addnlcrspd']}"						action="#{CorrespondenceControllerBean.additionalCorrespondence}"/>
						<h:outputText id="PRGCMGTOT961" value="#{crspd['label.crspd.addnlcrspd']}"							rendered="#{!CorrespondenceDataBean.updateMode}" />
						</m:div>
						<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT962" escape="false"									value="#{ref['label.ref.linkSeperator']}" />
						</m:div>

						<%--<h:commandLink id="PRGCMGTCL149" styleClass="strong"							rendered="#{CorrespondenceDataBean.crAssignedTo}"							action="#{CorrespondenceControllerBean.saveCorrespondence}"							onmousedown="javascript:flagWarn=false;focusThis(this.id);">
							<h:outputText id="PRGCMGTOT963" value="#{ent['label-sw-save']}" />
							<f:param name="com.ibm.portal.propertybroker.action"
								value="#{crspd['label.crspd.sendEDMSSearchCriteriaDataType']}">
							</f:param>
						</h:commandLink>--%>
						<m:div styleClass="infoActions">
						<t:commandButton id="PRGCMGTCB35" styleClass="strong"						onmousedown="javascript:flagWarn=false;focusThis(this.id);"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal; position:relative;top:2px"						disabled="#{!CorrespondenceDataBean.controlRequired}"						value="#{ent['label-sw-save']}"						action="#{CorrespondenceControllerBean.saveCorrespondence}" >
							<f:param name="com.ibm.portal.propertybroker.action"
								value="#{crspd['label.crspd.sendEDMSSearchCriteriaDataType']}">
							</f:param>
						</t:commandButton>
						</m:div>
				
			</m:div>
			
			<m:div align="right"
							rendered="#{!CorrespondenceDataBean.controlRequired}">
							<m:div styleClass="infoActions">
								<m:div id="crCancelId111" rendered="#{!CorrespondenceDataBean.updateMode && !CorrespondenceDataBean.navigateTOLogCase}">
									<f:verbatim><a href="/wps/myportal/SearchCREntity" id="CrTOSrCrEntity4" ></f:verbatim>
										<h:outputText id="PRGCMGTOT964" value="#{ent['label-sw-cancel']}"></h:outputText>
									<f:verbatim></a></f:verbatim>
								</m:div>
								<m:div id="crCancelId112" rendered="#{CorrespondenceDataBean.updateMode && !CorrespondenceDataBean.navigateTOLogCase}">
									<f:verbatim><a href="/wps/myportal/SearchCorrespondence" id="CrToSrCr5" ></f:verbatim>
										<h:outputText id="PRGCMGTOT965" value="#{ent['label-sw-cancel']}"></h:outputText>
									<f:verbatim></a></f:verbatim>
								</m:div>
								<m:div id="crCancelId113" rendered="#{CorrespondenceDataBean.navigateTOLogCase}">
									<f:verbatim><a href="/wps/myportal/FromLogCase" id="CrToLogCase6" ></f:verbatim>
										<h:outputText id="PRGCMGTOT64" value="#{ent['label-sw-cancel']}"></h:outputText>
									<f:verbatim></a></f:verbatim>
								</m:div>
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT966" escape="false"									value="#{ref['label.ref.linkSeperator']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT967" value="#{ent['label-sw-reset']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT968" escape="false"									value="#{ref['label.ref.linkSeperator']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:commandButton id="CMGTCB7"    onmousedown="javascript:flagWarn=false;"   style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:54px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px" 		value="#{ref['label.ref.auditLog']}"  		action="#{CorrespondenceControllerBean.doAuditKeyListOperation}" />
						    </m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT970" escape="false"									value="#{ref['label.ref.linkSeperator']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT971" value="#{crspd['label.crspd.new']}"									rendered="#{!CorrespondenceDataBean.controlRequired}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT972" escape="false"									value="#{ref['label.ref.linkSeperator']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT973" value="#{crspd['label.crspd.addnlcrspd']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT974" escape="false"									value="#{ref['label.ref.linkSeperator']}" />
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT975" value="#{ent['label-sw-save']}">
								</h:outputText>
							</m:div>
						</m:div>
			</m:div>
</m:div>

			<%--rendered attribute is added below for not retaining the error messages on Log Correspondence page for the Defect ESPRD00852896--%>
			
			<h:messages id="PRGCMGTMS6" layout="table" showDetail="true" showSummary="false"				style="color: red"   rendered="#{CorrespondenceDataBean.logCRErrMsgFlag}"/>
				
			<m:div rendered="#{CorrespondenceDataBean.crSaved}">
				<m:br/>
				<h:outputText id="PRGCMGTOT976" value="#{crspd['msg.crspd.savedSuccessfully']}" style="color: red" ></h:outputText>
			</m:div>
			<m:br clear="all" />

			<h:commandButton id="categoryChangeHiddenButton" styleClass="hide"				value="#{crspd['label.crspd.hiddenButtonToClick']}"				action="#{CorrespondenceControllerBean.getSubjectsForCategory}" />
			<%-- Audit LOg -parent  
			
			<f:subview id="crParentAuditLog">
						<jsp:include
							page="/jsp/logcorrespondence/inc_logCRParentAuditLog.jsp" />
					</f:subview>--%>
					
					<m:div 	rendered="#{not empty CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber}">
					 <f:subview id="CorrespondenceInc">
						<m:div rendered="#{CorrespondenceDataBean.auditLogFlag}">
						<audit:auditTableSet id="CorrespondenceId"
						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.auditKeyList}"
						numOfRecPerPage="10"></audit:auditTableSet>
					</m:div>
					</f:subview>
					</m:div>
			
			
			<m:div styleClass="portletArea">
				<m:div styleClass="tabAnchorLinks borders">
					<m:div styleClass="leftCell">

						<%-- Fixed the defect #ESPRD00060504


















































						<m:anchor href="#contacts">
							<h:outputText id="PRGCMGTOT977" value="#{crspd['label.crspd.contacts']}" />
						</m:anchor>
						<h:outputText id="PRGCMGTOT978" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<h:outputText id="PRGCMGTOT979" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						--%>
						<m:anchor href="#routing">
							<h:outputText id="PRGCMGTOT980" value="#{crspd['label.crspd.routing']}" />
						</m:anchor>
						<h:outputText id="PRGCMGTOT981" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<h:outputText id="PRGCMGTOT982" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<m:anchor href="#asscrspd">
							<h:outputText id="PRGCMGTOT983" value="#{crspd['label.crspd.asscrspd']}" />
						</m:anchor>
						<h:outputText id="PRGCMGTOT984" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<h:outputText id="PRGCMGTOT985" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<m:anchor href="#attachments">
							<h:outputText id="PRGCMGTOT986" value="#{crspd['label.crspd.attachments']}" />
						</m:anchor>
						<h:outputText id="PRGCMGTOT987" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<h:outputText id="PRGCMGTOT988" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<m:anchor href="#lettersresponses">
							<h:outputText id="PRGCMGTOT989" value="#{crspd['label.crspd.lettersresponses']}" />
						</m:anchor>
						<h:outputText id="PRGCMGTOT990" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<h:outputText id="PRGCMGTOT991" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<m:anchor href="#alerts">
							<h:outputText id="PRGCMGTOT992" value="#{crspd['label.crspd.alerts']}" />
						</m:anchor>
						<h:outputText id="PRGCMGTOT993" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<h:outputText id="PRGCMGTOT994" escape="false"							value="#{ref['label.ref.singleSpace']}" />
						<m:anchor href="#callscript">
							<h:outputText id="PRGCMGTOT995" value="#{crspd['label.crspd.callscript']}" />
						</m:anchor>
					</m:div>
				</m:div>
				<m:br/><m:br/>
				<m:div styleClass="Col100"
					rendered="#{CorrespondenceDataBean.renderCrspdForVO}">
					<jsp:include
						page="/jsp/logcorrespondence/inc_logCorrespondenceFor.jsp" />
				</m:div>
				<%--  ADDED FOR THE Correspondence ESPRD00436016 --%>
				<m:div styleClass="Col100"
					rendered="#{CorrespondenceDataBean.renderCrspdTrdPartForVO}">
					<jsp:include
						page="/jsp/logcorrespondence/inc_logCorrespondenceForTradingPartner.jsp" />
				</m:div>
				
				<m:div styleClass="Col100"
					rendered="#{CorrespondenceDataBean.renderCrspdMemberForVO}">
					<f:subview id="logCrspdForMemSubview">
						<jsp:include
							page="/jsp/logcorrespondence/inc_logCorrespondenceForMember.jsp" />
					</f:subview>
				</m:div>
				<m:div styleClass="Col100"
					rendered="#{CorrespondenceDataBean.renderCrspdUnEnrolledProviderForVO}">
					<jsp:include
						page="/jsp/logcorrespondence/inc_logCorrespondenceForUnenrolledProvider.jsp" />
				</m:div>
				
				<m:div styleClass="Col100"
					rendered="#{CorrespondenceDataBean.renderCrspdUnEnrolledMemberForVO}">
					<jsp:include
						page="/jsp/logcorrespondence/inc_logCorrespondenceForUnenrolledMember.jsp" />
				</m:div> 
				<m:div styleClass="Col100"
					rendered="#{CorrespondenceDataBean.renderCrspdTPLForVO}">
					<jsp:include
						page="inc_logCorrespondenceForTPLCarrier.jsp" />
				</m:div>
				<m:div styleClass="Col100"
					rendered="#{CorrespondenceDataBean.renderCrspdProviderForVO}">
					<f:subview id="logCrspdForProvSubview">
						<jsp:include
							page="/jsp/logcorrespondence/inc_logCorrespondenceForProvider.jsp" />
					</f:subview>
				</m:div>

				<m:br />
				<m:br />

				<m:div styleClass="Col100">
					<f:subview id="logCrspdDtls">					
						<jsp:include
							page="/jsp/logcorrespondence/inc_logCorrespondenceDetails.jsp" />
						</f:subview>
				</m:div>

				<m:br />
				<m:br />

				<%-- <m:anchor name="contacts"></m:anchor>

				<m:div styleClass="Col100">
					<f:subview id="logCrspdConSubview">
						<jsp:include
							page="/jsp/logcorrespondence/inc_logCorrespondenceContact.jsp" />
					</f:subview>
				</m:div>

				<m:br />
				<m:br /> --%>

				<m:anchor name="routing"></m:anchor>
				<m:div styleClass="Col100">
					<f:subview id="logCrspdRouSubview">
						<jsp:include
							page="/jsp/logcorrespondence/inc_logCorrespondenceRouting.jsp" />
					</f:subview>
				</m:div>

				<m:br />
				<m:br />

				<m:anchor name="asscrspd"></m:anchor>
				<m:div styleClass="Col100">
					<f:subview id="logCrspdAsscSubview">
						<jsp:include
							page="/jsp/logcorrespondence/inc_associatedCorrespondence.jsp" />
					</f:subview>
				</m:div>


				<m:br />
				<m:br />

				<m:anchor name="attachments"></m:anchor>
				<m:div styleClass="Col100">
					<f:subview id="logCrspdAttmntsSubview">
						<jsp:include page="/jsp/attachments/inc_attachment.jsp" />
					</f:subview>
				</m:div>

				<m:br />
				<m:br />

			    <m:anchor name="lettersresponses"></m:anchor>
				<m:div styleClass="Col100">
				 <f:subview id="logCrspdlettNrespSubview">
						<jsp:include
							page="/jsp/lettersandresponses/inc_lettersAndResponses.jsp" />
					</f:subview>	
				</m:div>

				<m:br />
				<m:br /> 

				<m:anchor name="alerts"></m:anchor>
				<m:div styleClass="Col100">
					<f:subview id="logCrspdAlrtSubview">
						<jsp:include
							page="/jsp/logcorrespondence/inc_logCorrespondenceAlerts.jsp" />
					</f:subview>
				</m:div>

				<m:br />
				<m:br />


				<m:anchor name="callscript"></m:anchor>
 				<h:panelGroup id="logCrspdCallScriptSection">
				<m:div styleClass="Col100" id="logCrspdCallScriptID">
					<f:subview id="logCrspdCallScrptSubview">
						<jsp:include page="/jsp/logcorrespondence/inc_logCRCallScript.jsp" />
					</f:subview>
				</m:div>
				</h:panelGroup>
				<hx:ajaxRefreshSubmit id="subjectRefreshRequest" target="logCrspdCallScriptSection"></hx:ajaxRefreshSubmit>
               
				<m:div id="commonNotesID">
					<f:subview id="commonNotes">
						<jsp:include page="/jsp/logcorrespondence/inc_CommonNotes.jsp" />
					</f:subview>
				</m:div>
				</m:div>
			 
		</m:body>
		<script>


			portletNameSpace = 'view<portlet:namespace/>:';
			renderDiv('logCorrespondence:logCrspdForMemSubview:logCrspdForMemberSubview:memeberHidden','logCorrespondence:logCrspdForMemSubview:logCrspdForMemberSubview:plusMinusMember','corrForMem');
			renderDiv('logCorrespondence:logCrspdForProvSubview:logCrspdForProviderSubview:providerHidden','logCorrespondence:logCrspdForProvSubview:logCrspdForProviderSubview:plusMinusProvider','corrForProv');
			renderDiv('logCorrespondence:logCrspdConSubview:logCrspdContactSubview:contactHidden','logCorrespondence:logCrspdConSubview:logCrspdContactSubview:plusMinusContact','log_div_contact');
			renderDiv('logCorrespondence:logCrspdRouSubview:logCrspdRoutingSubview:routingHidden','logCorrespondence:logCrspdRouSubview:logCrspdRoutingSubview:plusMinusRouting','log_div_routing');
			renderDiv('logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:asscCrspdHidden','logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:plusMinusAssCrspd','log_div_assCrspd');
			renderDiv('logCorrespondence:logCrspdAttmntsSubview:logCrspdAttachmentsSubview:attachmentsHidden','logCorrespondence:logCrspdAttmntsSubview:logCrspdAttachmentsSubview:plusMinusAttachments','log_div_attachments');
			renderDiv('logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:letNrespHidden','logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:plusMinusLettNresp','log_div_lettNresp');			
			renderDiv('logCorrespondence:logCrspdAlrtSubview:logCrspdAlertsSubview:alertsHidden','logCorrespondence:logCrspdAlrtSubview:logCrspdAlertsSubview:plusMinusAlerts','log_div_alerts');
			renderDiv('logCorrespondence:logCrspdCallScrptSubview:logCrspdCallScriptSubview:callScriptHidden','logCorrespondence:logCrspdCallScrptSubview:logCrspdCallScriptSubview:plusMinuscallScript','log_div_callScript');
			renderDiv('logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:advancedCrspdSrchSubview:advancedOptionsHidden','logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:advancedCrspdSrchSubview:plusMinusAdvCrspdSearch','srchCrspd_div_advcSrchOptns');
			
		</script>
		<%--	</hx:scriptCollector>  --%>
		
		<m:div id="notesFocusflagID" rendered="#{commonEntityDataBean.noteFlag}">
        <h:inputText id="notesFocusflag" style="display:none"></h:inputText>
        </m:div>
        <%--Below code is added for 508-Para M complience for CR ESPRD00860876 --%>
<m:div id="adobeReaderDivID11" align="center">
						<h:outputText id="adobeReaderText1ID11" value="#{ent['label.ent.adobeReaderText1']}" />
						<h:outputLink value="#{ent['label.ent.adobeReaderDownloadLink']}" target="_blank" title="#{ent['label.ent.adobeReaderTitle']}" >
							<h:outputText id="adobeReaderText2ID11" value="#{ent['label.ent.adobeReaderText2']}" />
							<m:img title="#{ent['label.ent.adobeReader']}"
								alt="#{ent['label.ent.adobeReader']}" height="30" width="120"
								id="adobeReaderImageID11" src="/wps/themes/html/ACSDefault/images/get_adobe_reader.png" />
						</h:outputLink>
					</m:div>
					<h:graphicImage id="spacerCRImageID" width="1" height="5" url="/images/x.gif" />
					</m:div>
					</m:div>
		</h:form>
</hx:scriptCollector>
<%-- var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
         FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:logCorrespondence';

   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForLogCr')) !=  'undefined') && document.getElementById(thisForm+':controlRequiredForLogCr') != null ?  document.getElementById(thisForm+':controlRequiredForLogCr').value:true);

</script>
</body>

</f:view>

 
