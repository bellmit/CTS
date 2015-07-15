<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<portlet:defineObjects />



<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMContactManagementCase"
	var="msg" />
<%--Modified for defect ESPRD00446264 starts--%>
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />
<%--Defect ESPRD00446264 Fix ends--%>

<script type="text/javascript">


onerror=function(e){

}
		function enableDisableComplEdit()
		{
			var compBasedOnVarEd= findObjectByPartOfID('completionBasedOn2');
			var hidButtonObjCt = findObjectByPartOfID('expectedDaysHiddenBut');
			hidButtonObjCt.click();
				
		}

		function enableDisableComplAdd()
		{
			var compBasedOnVarAd= findObjectByPartOfID('completionBasedOn');
			compBasedOnVarAd.value="";
			var hidButtonObjCt = findObjectByPartOfID('expectedDaysHiddenBut');
			hidButtonObjCt.click();
				
		}

function setHiddenValue(hiddenVariable, hiddenValue) 
{
hiddenVariable.value = hiddenValue;
flagWarn = true; // Added to display the cancel popup for the defect id ESPRD00705914_11OCT2011
flagRole = true; // Added to display the cancel popup for the defect id ESPRD00705914_11OCT2011
}

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

function renderAudit(id) 
{ 
	var hiddenValue = document.getElementById('view<portlet:namespace/>:form1:auditlogDetails:'+id);
    
	if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
		hideMe('audit_plus');
	} else if ((hiddenValue.value == 'false')) 
	{
		hideMe('audit_plus');
	} 
}
		/*Added for Defect ESPRD00360902(Cursor focus) starts*/
		var thisForm = 'view<portlet:namespace/>:CMlogCase';
		function focusThis(id) { 
   			document.getElementById(thisForm+':focusId').value=id;  			
	   	}	
		function focusPage(name) { 
   			document.getElementById(thisForm+':PAGEFOCUSID').value=name;  			
	   	}					
		if (window.addEventListener) //DOM method for binding an event
            	window.addEventListener("load", onLoadFunctions, false);
      	else if (window.attachEvent) //IE exclusive method for binding an event
            	window.attachEvent("onload", onLoadFunctions);
      	else if (document.getElementById) //support older modern browsers
           		window.onload=onLoadFunctions;		
		function onLoadFunctions() 
		{
			var createltrIPC = document.getElementById(thisForm + ':createltrIPCIND').value;
			var viewLetterReqIPC = document.getElementById(thisForm + ':viewLetterReqCaseIND').value;
			
			if(createltrIPC!=null && createltrIPC != "") { 
				//alert('inside if Create Letter');
				flagWarn=false;
				document.getElementById(thisForm + ':createltrIPCIND').value =""; 
				document.getElementById(thisForm+':'+createltrIPC).click();
			}
			//Added as a part of Defect ESPRD00796178,Right Click Issue of 'View Letter Request' Link.
			if(viewLetterReqIPC!=null && viewLetterReqIPC != "") 
			{ 
				flagWarn=false;
				document.getElementById(thisForm + ':viewLetterReqCaseIND').value =""; 
				document.getElementById(thisForm+':'+viewLetterReqIPC).click();
			}
			//Code ended for Defect ESPRD00796178
			if(document.getElementById(thisForm+':cfrInqPage').value=='inqEntitySearch')
			{
				document.getElementById(thisForm+':cfrInqPage').value = 'refreshing:::';
			}
			else
			{
			//focusOnLoad();	// Commented for the defect id ESPRD00697332_07DEC2011
			onLoadPageFocus();
			}
		}
		// Begin - Modified the code for the defect id ESPRD00697332_07DEC2011
		function onLoadPageFocus(){

			//find the user browser
			var brow=(navigator.appName);

			if(brow == 'Microsoft Internet Explorer'){
				//alert("its internet explorer");
			}else if(brow == 'Netscape'){
				//alert("its Mozilla");
				jscalendarInit();
			}
			
			var focusPage = '#'+document.getElementById(thisForm+':PAGEFOCUSID').value;
			var fieldFocus = document.getElementById(thisForm+':focusId').value;
			//alert('focusPage'+focusPage);
			//alert('fieldFocus'+fieldFocus);
			if(focusPage != '' && focusPage != '#') {	
				document.location.href=focusPage;
			}else{
				document.location.href='#portletHeaderFocus';
			}
			if(fieldFocus != null && fieldFocus != '')
			{
				var fieldObject = findObjectByPartOfID(fieldFocus);
				if(fieldObject != null)
				{
					fieldObject.focus();
				}
			}else if(focusPage=='#portletHeaderFocus' || (focusPage=='#')){
				var workUnit=document.getElementById('view<portlet:namespace/>:CMlogCase:caseDetails:LOGCASEWORKUNIT');
				if(workUnit.disabled==false){
					workUnit.focus();
				}else{
					document.getElementById('view<portlet:namespace/>:CMlogCase:caseDetails:LOGCASESTATUS').focus();
				}
			}
			window.scrollBy(0, -28);
			
		}
		
/*This method finds an object from the DOM by acception a part of it's ID as parameter.*/
function findObjectByPartOfID(partOfID)
{
   	formId = "view<portlet:namespace/>:CMlogCase:caseDetails:casedetailDiv16Id:biopsyDt";
   	//alert(document.getElementById('logCaseOutTxt1Id'));
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

// Begin - Added this finction for the defect ESPRD00677719   		
function validateBiopsyDate()
{
	frmId = 'view<portlet:namespace/>:CMlogCase';
	var date = document.getElementById('view<portlet:namespace/>:CMlogCase:caseDetails:caseTypeBCCP:biopsyDt');
	if( date.value != "")
	{
		document.getElementById(frmId+':caseDetails:caseTypeBCCP:biopsyfndgs').disabled = false;
	}
	else
	{
		document.getElementById(frmId+':caseDetails:caseTypeBCCP:biopsyfndgs').disabled = true;
	}
}
// End - Added this finction for the defect ESPRD00677719

// Begin - Added this finction for the defect ESPRD00678064
function validateText()
{
	frmId = 'view<portlet:namespace/>:CMlogCase';
	var date = document.getElementById('view<portlet:namespace/>:CMlogCase:caseEvents:addCaseEvents:eventDate');
	if( date.value != "")
	{
		document.getElementById(frmId+':caseEvents:addCaseEvents:timed_01').disabled = false;
	}
	else
	{
		document.getElementById(frmId+':caseEvents:addCaseEvents:timed_01').value = "";
		document.getElementById(frmId+':caseEvents:addCaseEvents:timed_01').disabled = true;
	}
}
// End - Added this finction for the defect ESPRD00678064

function biopsyChangeAction()
{
	var hiddenButtonObject = findObjectByPartOfID('biopsyDateChangedHiddenButton');
	hiddenButtonObject.click();
}
function revShedateChangeAction()
{
	var hidButtonObject = findObjectByPartOfID('revChangedHiddenButton');
	hidButtonObject.click();
}
function showTreamentStartedDate()
{
	var hidButtonObj = findObjectByPartOfID('treatHiddenBut');
	hidButtonObj.click();
}
function stFollowAction()
{
	var hidButtonObjSt = findObjectByPartOfID('stHiddenBut');
	hidButtonObjSt.click();
}
function caseTypeAction()
{
	var hidButtonObjCt = findObjectByPartOfID('ctHiddenBut');
	hidButtonObjCt.click();
}
// for ESPRD00823632
function dispositionDateForEventAction()
{
	var hidButtonObjCt = findObjectByPartOfID('dispositionDateForEventsId');
	hidButtonObjCt.click();
}
	
</script>
<%-- 
  Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start
  --%>


<script type="text/javascript">
frmId = 'view<portlet:namespace/>:CMlogCase';
<%-- 
  - Infinite change code CR-1543 
  --%>		
   
			
		
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
		
		function renderAudit(id1) 
		{ 
			var hiddenValueCI = document.getElementById('view<portlet:namespace/>:bpcvg:auditlogDetails:'+id1);
			
		    
			if ((hiddenValueCI == null) ||(hiddenValueCI == '')|| (hiddenValueCI.length == 0)) 
			{
			    hideMe('audit_plus');
			} else if ((hiddenValueCI.value == 'false')) 
			{
			    hideMe('audit_plus');
			} 
		
			
		}
		
		

</script>


<f:view>

	<f:subview id="provService">
		<jsp:include page="/jsp/script/scripts.jsp" />
	</f:subview>

	<%-- Added the JavaScript function in the body tag --%>
	<body onload="javascript:warnBeforeCancel();renderAudit('audit_open');">
	<m:anchor name="portletHeaderFocus"></m:anchor>
	<%--Added for ESPRD00357689 --%>
	<%--Modified for defect ESPRD00674035--%>
	<%--<t:saveState id="CMGTTOMSS173" value="#{logCaseControllerBean.invalidEventDate}"></t:saveState>--%>
	<%--End for ESPRD00357689 --%>

	<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end--%>

	<%--<t:saveState value="#{logCaseDataBean}" id="logCaseSav1Id"></t:saveState> for ESPRD00682799--%>

	<t:saveState value="#{logCaseDataBean.caseNotesList}"
		id="logCaseSav10Id"></t:saveState>

	<t:saveState value="#{caseSearchDataBean}" id="logCaseSav2Id"></t:saveState>
	<t:saveState value="#{cs_searchCorrespondenceDataBean}"
		id="logCaseSav3Id" />
	<t:saveState value="#{cs_searchCorrespondenceDataBean.listOfRepUnits}"
		id="logCaseSav4Id" />
	<t:saveState value="#{cs_searchCorrespondenceDataBean.searchResults}"
		id="logCaseSav5Id" />
	<t:saveState value="#{cs_searchCorrespondenceDataBean.showResultsDiv}"
		id="logCaseSav6Id" />
	<t:saveState
		value="#{cs_searchCorrespondenceDataBean.listOfRefBusUnits}"
		id="logCaseSav7Id" />
	<t:saveState value="#{cs_searchCorrespondenceDataBean.lobVVList}"
		id="logCaseSav8Id" />
	<t:saveState
		value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO}"
		id="logCaseSav9Id" />
	<t:saveState value="#{LettersAndResponsesDataBean}" id="lrDataBean" />
	<hx:scriptCollector>
		<h:form id="CMlogCase">
			<m:div id="logcaseMainDiv01" styleClass="floatContainer">
				<m:div id="logcaseMainDiv02" style="width:100%">
					<h:commandButton id="expectedDaysHiddenBut" styleClass="hide"
						value="Hidden Button To" onmousedown="javascript:flagWarn=false;" />

					<h:commandButton id="biopsyDateChangedHiddenButton"
						styleClass="hide" value="Hidden Button To Click"
						action="#{logCaseControllerBean.showBiopsyFindings}"
						onmousedown="javascript:flagWarn=false;" />
					<h:commandButton id="revChangedHiddenButton" styleClass="hide"
						value="Hidden Button To"
						action="#{logCaseControllerBean.showReviewRequredFields}"
						onmousedown="javascript:flagWarn=false;" />
					<h:commandButton id="treatHiddenBut" styleClass="hide"
						value="Hidden Button To"
						action="#{logCaseControllerBean.showTreamentStartedDateFields}"
						onmousedown="javascript:flagWarn=false;" />
					<h:commandButton id="stHiddenBut" styleClass="hide"
						value="Hidden Button To"
						action="#{logCaseControllerBean.showFollowUpMonths}"
						onmousedown="javascript:flagWarn=false;" />
					<h:commandButton id="ctHiddenBut" styleClass="hide"
						value="Hidden Button To"
						action="#{logCaseControllerBean.showCaseTypeScreens}"
						onmousedown="javascript:flagWarn=false;" />
						
						
					<h:commandButton id="dispositionDateForEventsId" styleClass="hide"
						value="Hidden Button To"
						action="#{logCaseControllerBean.dispositionDateForEvents}"
						onmousedown="javascript:flagWarn=false;" />
						
						
					<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 start--%>
					<h:inputHidden id="fileBigSavedFlag"
						value="#{logCaseDataBean.fileBigSavedFlag}"></h:inputHidden>
					<%--Infinite Implementation for UC-PGM-CRM-018.10, CR 1543 end--%>
					<h:inputHidden id="loadValidValueId"
						value="#{logCaseControllerBean.loadValidValue}"
						rendered="#{logCaseDataBean.validValuesFlag}"></h:inputHidden>
					<h:inputHidden id="caseSearchLoadValidValueId"
						value="#{caseSearchControllerBean.loadValidValue}"
						rendered="#{caseSearchDataBean.validValueFlag}"></h:inputHidden>
					<%--Added for Defect ESPRD00360902(Cursor focus) starts--%>
					
					<%--Added for Defect ESPRD00360902(Cursor focus) ends--%>
					<h:inputHidden id="internalSubmit" value=" " />
					<h:inputHidden id="PRGCMGTIH23"
						value="#{logCaseControllerBean.link2Show}"></h:inputHidden>
					<h:inputHidden id="receiveMessageId"
						value="#{logCaseControllerBean.receiveMessage}"></h:inputHidden>
					<h:inputHidden id="PAGEFOCUSID"
						value="#{logCaseDataBean.pageFocusId}"></h:inputHidden>
					<h:inputHidden id="focusId"
						value="#{logCaseDataBean.cursorFocusValue}"></h:inputHidden>
					<h:inputHidden id="cfrInqPage"
						value="#{logCaseDataBean.cursorFocusInquiry}"></h:inputHidden>
					
					<h:inputHidden id="userHavingUpdateRole"
						value="#{logCaseDataBean.controlRequired}" />
					<h:inputHidden id="createltrIPCIND"
						value="#{LettersAndResponsesDataBean.createLetterCase}"></h:inputHidden>
					<%--Added as a part of Defect ESPRD00796178,Right Click issue of 'View Letter Request' link --%>
					<h:inputHidden id="viewLetterReqCaseIND"
						value="#{LettersAndResponsesDataBean.viewLetterReq}"></h:inputHidden>
					<%--Added for load the data from Entity to Log Case Portlet, for ESPRD00802462 --%>
					<h:inputHidden id="loadEntityData"
						value="#{logCaseControllerBean.loadDataFromEntity}" rendered="#{logCaseDataBean.loadEntityDataFlag}"></h:inputHidden>
					<m:div styleClass="moreInfoBar" id="divLogcaseId1">
						<m:div styleClass="infoTitle" align="left" id="divLogcaseId2">
							<m:span styleClass="required" id="divSpanId1">
								<h:outputText id="logCaseOutTx29Id"
									value="#{msg['label.case.requiredfield']}" />
							</m:span>
						</m:div>
						<%-- Begin - Added the condition for the defect id ESPRD00710222_12OCT2011 
						<m:div id="divLogcaseId3" style="width:100%"
							rendered="#{logCaseDataBean.showCaseDetailsMessages
							|| !(logCaseDataBean.showCaseStepsMessages 
							|| logCaseDataBean.showCaseStepsDelteMessage 
							|| logCaseDataBean.showCaseAlertsMessages
							|| logCaseDataBean.showCaseEventsMessages
							|| logCaseDataBean.caseEventDeleteMsgFlag
							|| logCaseDataBean.showCaseMessage
							|| logCaseDataBean.showCaseRoutingMessages
                            || CMLogCaseDataBean.showDeleteMessage
                            || caseSearchDataBean.caseAsctErrormsg
                            || logCaseDataBean.caseCrspnAsctErrormsg)
                            || !(logCaseDataBean.dduExistedRecordErrorMsg)}">
							<h:messages layout="table" showDetail="true"
								styleClass="colorRed" id="errorMessage" showSummary="false">
							</h:messages>
						</m:div>
						 End - Added the condition for the defect id ESPRD00710222_12OCT2011 --%>
						
						<%--Modified for defect ESPRD00674035--%>
						<%--Added for ESPRD00357689 --%>
						<m:div rendered="#{logCaseDataBean.invalidEventDate}"
							id="divLogcaseId4">
							<h:outputText id="outTxtLogcaseId1"
								value="#{msg['error.logcase.caseevents.invalid.dateformate']}"
								rendered="#{logCaseDataBean.invalidEventDate}"
								styleClass="errorMessage" />
							<m:br id="logCaseBr1Id" />
							<h:outputText id="outTxtLogcaseId2"
								value="#{msg['error.logcase.caseevents.invalid.dateformate1']}"
								rendered="#{logCaseDataBean.invalidEventDate}"
								styleClass="errorMessage" />
							<m:br id="logCaseBr2Id" />
							<h:outputText id="outTxtLogcaseId3"
								value="#{msg['error.logcase.caseevents.invalid.dateformate2']}"
								rendered="#{logCaseDataBean.invalidEventDate}"
								styleClass="errorMessage" />
							<m:br id="logCaseBr3Id" />
							<h:outputText id="outTxtLogcaseId4"
								value="#{msg['error.logcase.caseevents.invalid.dateformate3']}"
								rendered="#{logCaseDataBean.invalidEventDate}"
								styleClass="errorMessage" />
						</m:div>
						<%--EOF Added for ESPRD00357689--%>

						<m:div styleClass="infoActions" id="divLogcaseId5">
							<!-- Begin - Added the focusPage function for the defect id ESPRD00710222 -->
							<%--Added for CR ESPRD00908771--%>
							<t:commandLink action="#{logCaseControllerBean.saveCaseRecord}"
								id="caseSaveId"
								disabled="#{logCaseDataBean.saveFlag}"
								onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;focusPage('portletHeaderFocus');"
								onclick="javascript:flagWarn=false;"
								rendered="#{!logCaseDataBean.disableLogCaseSave}"
								styleClass="strong">
								<h:outputText id="PRGCMGTOT632"
									value="#{msg['label.case.save']}" />
							</t:commandLink>
							<!-- End - Added the focusPage function for the defect id ESPRD00710222 -->
							<h:outputText id="logCaseOutTxt1Id"
								value="#{msg['label.case.save']}"
								rendered="#{logCaseDataBean.disableLogCaseSave}" />

							<%--<h:outputText id="logCaseOutTxt2Id" escape="false" value="&nbsp;" />--%>
							<h:outputText id="logCaseOutTxt3Id" value="|" />
							<%--	<h:outputText id="logCaseOutTxt4Id" escape="false" value="&nbsp;" />--%>
							<t:commandLink action="#{logCaseControllerBean.resetCase}"
								rendered="#{!logCaseDataBean.disableLogCaseReset}"
								id="caseResetId"
								onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;"
								onclick="javascript:flagWarn=false;">
								<h:outputText id="logCaseOutTx10Id"
									value="#{msg['label.case.reset']}" />
							</t:commandLink>
							<h:outputText id="logCaseOutTx11Id"
								value="#{msg['label.case.reset']}"
								rendered="#{logCaseDataBean.disableLogCaseReset}" />
							<%--	<h:outputText id="logCaseOutTx12Id" escape="false" value="&nbsp;" />--%>
							<h:outputText id="logCaseOutTx13Id" value="|" />
							
							
							<t:commandLink id="PRGCMGTCL111"
								action="#{logCaseControllerBean.associateCorrespondence}"
								onmousedown="javascript:flagWarn=false;"
								rendered="#{!logCaseDataBean.disableLogAddCaseAssocCrspondence}">
								<h:outputText id="logCaseOutTxt5Id"
									value="#{msg['label.case.addAssociatedCorrespondence']}" />
								<f:param name="com.ibm.portal.propertybroker.action"
										value="sendLogCaseAction" id="logCaseParam1Id" />
							</t:commandLink>
							<h:outputText id="logCaseOutTxt6Id"
								value="#{msg['label.case.addAssociatedCorrespondence']}"
								rendered="#{logCaseDataBean.disableLogAddCaseAssocCrspondence}" />

							<%--<h:outputText id="logCaseOutTxt7Id" escape="false" value="&nbsp;" />--%>
							<h:outputText id="logCaseOutTx87Id" value="|" />
							<%--<h:outputText id="logCaseOutTx9Id" escape="false" value="&nbsp;" />--%>
							
							<%--CR_ESPRD00373565_LogCase_04AUG2010 --%>
							<t:commandLink
								action="#{logCaseControllerBean.enableAuditLogForLogCase}"
								id="caseAuditLogId"
								onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;"
								onclick="javascript:flagWarn=false;">
								<h:outputText id="logCaseAuditLogId"
									value="#{msg['label.case.auditLog']}" />
							</t:commandLink>
							<h:outputText id="logCaseAuditLogOutTxId" value="|" />
							<%-- EOF CR_ESPRD00373565_LogCase_04AUG2010 --%>
							<%--	<h:outputText id="logCaseOutTx14Id" escape="false" value="&nbsp;" />--%>
							<%-- <h:outputLink value="/wps/myportal/Case" id="logCaseOutLnk1Id">
								<h:outputText id="logCaseOutTx15Id"
									value="#{msg['label.case.cancel']}" />
							</h:outputLink>--%>
							<%-- End - Modified for the cancel popup for the defect ESPRD00705914 --%>
							<f:verbatim> <a href="/wps/myportal/Case"  id="logCaseOutLnk1Id" ></f:verbatim>
						<h:outputText id="logCaseOutTx15Id"
									value="#{msg['label.case.cancel']}" />
						<f:verbatim></a></f:verbatim>

						</m:div>
					</m:div>

					
					<%--CR_ESPRD00373565_LogCase_04AUG2010 --%>
					<m:div id="viewCaseDetailsAuditDiv"
						rendered="#{logCaseDataBean.enableAuditLogForLogCase}">
						<audit:auditTableSet id="logcaseDetailAuditId"
							value="#{logCaseDataBean.caseDetailsVO.auditKeyList}"
							numOfRecPerPage="10">
						</audit:auditTableSet>

					</m:div>
					<%--EOF CR_ESPRD00373565_LogCase_04AUG2010 --%>
					<m:div styleClass="portletArea" id="divLogcaseId7">
						<m:div styleClass="tabAnchorLinks" id="divLogcaseId8">
							<m:anchor href="#caseRoutingHeader" onclick="javascript:flagWarn=false;">
								<h:outputText id="logCaseOutTx16Id"
									value="#{msg['link.case.routing']}" />
							</m:anchor>
							<h:outputText id="logCaseOutTx17Id" escape="false"
								value="&nbsp;&nbsp;&nbsp;" />
							<m:anchor href="#associatedCsCrHeader" onclick="javascript:flagWarn=false;">
								<h:outputText id="logCaseOutTx18Id"
									value="#{msg['link.case.associatedCaseCorrespondence']}" />
							</m:anchor>
							<h:outputText id="logCaseOutTx19Id" escape="false"
								value="&nbsp;&nbsp;&nbsp;" />
							<m:anchor href="#caseAttachmentsHeader"
								onclick="javascript:flagWarn=false;">
								<h:outputText id="logCaseOutTx20Id"
									value="#{msg['link.case.attachments']}" />
							</m:anchor>
							<h:outputText id="logCaseOutTx21Id" escape="false"
								value="&nbsp;&nbsp;&nbsp;" />
							<m:anchor href="#caseLettersHeader" onclick="javascript:flagWarn=false;">
								<h:outputText id="logCaseOutTx22Id"
									value="#{msg['link.case.lettersandresponses']}" />
							</m:anchor>
							<h:outputText id="logCaseOutTx23Id" escape="false"
								value="&nbsp;&nbsp;&nbsp;" />
							<m:anchor href="#caseEventsHeader" onclick="javascript:flagWarn=false;">
								<h:outputText id="logCaseOutTx24Id"
									value="#{msg['link.case.caseevents']}" />
							</m:anchor>
							<h:outputText id="logCaseOutTx25Id" escape="false"
								value="&nbsp;&nbsp;&nbsp;" />
							<m:anchor href="#CaseAlertsHeader" onclick="javascript:flagWarn=false;">
								<h:outputText id="logCaseOutTx26Id"
									value="#{msg['link.case.alerts']}" />
							</m:anchor>
							<h:outputText id="logCaseOutTx27Id" escape="false"
								value="&nbsp;&nbsp;&nbsp;" />
							<m:anchor href="#caseStepsHeader" onclick="javascript:flagWarn=false;">
								<h:outputText id="logCaseOutTx28Id"
									value="#{msg['link.case.casesteps']}" />
							</m:anchor>
						</m:div>
						<%-- Added for the defect ESPRD00873143 to display inquiry entity already exists error message--%>
						<m:div id="INQUIRYENTITYEXSITERRMESG" style="width:100%">
							<h:inputHidden id="InquiryEntityExistsHidenMesg" value=" "></h:inputHidden>
							<h:message id="InquiryEntityExistsHidenMessgID" for="InquiryEntityExistsHidenMesg" styleClass="errorMessage"></h:message>
						</m:div>
						<m:div style="width:100%" id="divLogcaseId6">
							<h:messages layout="table" showDetail="true" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"
								styleClass="colorRed" id="errorMessageWhenCaseAdded"
								showSummary="false">
							</h:messages>
						</m:div>
						<m:br id="logCaseBr4Id" />
						<m:br id="logCaseBr5Id" />
						<m:anchor name="lgCsCaseRegFocusPage"></m:anchor>
						<f:subview id="caseRegarding">
							<jsp:include page="inc_logCaseCaseRegarding.jsp" />
						</f:subview>
						<m:div styleClass="clearl" id="divLogcaseId9">
							<h:graphicImage id="logCaseImg1Id" styleClass="blankImage"
								width="1" height="5" alt="" url="/images/x.gif" />
						</m:div>
						<f:subview id="caseDetails">
							<m:anchor name="caseDetailsPageHeader"></m:anchor>
							<jsp:include page="inc_caseDetails.jsp" />
						</f:subview>
						<m:div styleClass="clearl" id="divLogcaseId10">
							<h:graphicImage id="logCaseImg2Id" styleClass="blankImage"
								width="1" height="5" alt="" url="/images/x.gif" />
						</m:div>
						<f:subview id="routingDetails">
							<m:anchor name="caseRoutingHeader"></m:anchor>
							<jsp:include page="inc_caseRouting.jsp" />
						</f:subview>
						<m:div styleClass="clearl" id="divLogcaseId11">
							<h:graphicImage id="logCaseImg3Id" styleClass="blankImage"
								width="1" height="5" alt="" url="/images/x.gif" />
						</m:div>
						<m:anchor name="associatedCsCrHeader"></m:anchor>
						<f:subview id="associatedCaseandCorrespondence">
							<jsp:include page="inc_associatedCaseAndCorrespondence.jsp" />
						</f:subview>
						<m:div styleClass="clearl" id="divLogcaseId12">
							<h:graphicImage id="logCaseImg4Id" styleClass="blankImage"
								width="1" height="5" alt="" url="/images/x.gif" />
						</m:div>
						<m:anchor name="caseAttachmentsHeader"></m:anchor>
						<f:subview id="caseAttachments">
							<jsp:include page="inc_logCaseAttachments.jsp" />
						</f:subview>
						<m:div styleClass="clearl" id="divLogcaseId13">
							<h:graphicImage id="logCaseImg5Id" styleClass="blankImage"
								width="1" height="5" alt="" url="/images/x.gif" />
						</m:div>
						<m:anchor name="caseLettersHeader"></m:anchor>
						<f:subview id="caseLettersAndResponses">
							<%-- Modified for defect ESPRD00334051 --%>
							<jsp:include page="inc_logCaseLettersAndResponses.jsp" />
							<%--EOF modification for defect ESPRD00334051 --%>
						</f:subview>
						<m:div styleClass="clearl" id="divLogcaseId14">
							<h:graphicImage id="logCaseImg6Id" styleClass="blankImage"
								width="1" height="5" alt="" url="/images/x.gif" />
						</m:div>
						<m:anchor name="caseEventsHeader"></m:anchor>
						<f:subview id="caseEvents">
							<jsp:include page="inc_logCaseEvents.jsp" />
						</f:subview>
						<m:div styleClass="clearl" id="divLogcaseId15">
							<h:graphicImage id="logCaseImg7Id" styleClass="blankImage"
								width="1" height="5" alt="" url="/images/x.gif" />
						</m:div>
						<m:anchor name="CaseAlertsHeader"></m:anchor>
						<f:subview id="caseAlerts">
							<jsp:include page="inc_logCaseAlerts.jsp" />
						</f:subview>
						<m:div styleClass="clearl" id="divLogcaseId16">
							<h:graphicImage id="logCaseImg8Id" styleClass="blankImage"
								width="1" height="5" alt="" url="/images/x.gif" />
						</m:div>
						<m:anchor name="caseStepsHeader"></m:anchor>
						<f:subview id="caseSteps">
							<jsp:include page="inc_logCaseSteps.jsp" />
						</f:subview>
						<m:div styleClass="clearl" id="divLogcaseId17">
							<h:graphicImage id="logCaseImg9Id" styleClass="blankImage"
								width="1" height="5" alt="" url="/images/x.gif" />
						</m:div>

						<m:div id="divLogcaseId18">
							<f:subview id="commonNotes">
								<m:anchor name="caseNotesHeader"></m:anchor>
								<jsp:include page="inc_CommonNotes.jsp" />
							</f:subview>
						</m:div>

					</m:div>
					<%--Added for Displaying Adobe Reader Link for 508 Paragraph M ESPRD00860876--%>
					<m:div id="adobeReaderDivID" align="center">
						<h:outputText id="adobeReaderText1ID" value="#{ent['label.ent.adobeReaderText1']}" />
						<h:outputLink value="#{ent['label.ent.adobeReaderDownloadLink']}" target="_blank" title="#{ent['label.ent.adobeReaderTitle']}" >
							<h:outputText id="adobeReaderText2ID" value="#{ent['label.ent.adobeReaderText2']}" />
							<m:img title="#{ent['label.ent.adobeReader']}"
								alt="#{ent['label.ent.adobeReader']}" height="30" width="120"
								id="adobeReaderImageID" src="/wps/themes/html/ACSDefault/images/get_adobe_reader.png" />
						</h:outputLink>
					</m:div>
					<h:graphicImage id="spacerImageID" styleClass="blankImage" width="1" height="5"
									url="/images/x.gif" />
				</m:div>
			</m:div>




		</h:form>

		<%--
	  var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
	   FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
		<script type="text/JavaScript">
	var flagRole = ((typeof(document.getElementById(thisForm+':userHavingUpdateRole') != 'undefined')) && document.getElementById(thisForm+':userHavingUpdateRole') != null ? flagRole = document.getElementById(thisForm+':userHavingUpdateRole').value:true);
	</script>

	</hx:scriptCollector>
	</body>


	<script language="JavaScript">
	
			portletNameSpace = 'view<portlet:namespace/>:';
			
			renderDiv('CMlogCase:routingDetails:routingHiddenID',
            'CMlogCase:routingDetails:plusMinus_routingFalse',
            'showhide_routing');
            
            renderDiv('CMlogCase:routingDetails:routingViewRoutingAssignmentAudit:routing_hiddenID',
            'CMlogCase:routingDetails:routingViewRoutingAssignmentAudit:routing_plusMinus_Auditmore',
            'audit_plusRoutingAudit');	
            
            <%--renderDiv('CMlogCase:associatedCaseandCorrespondence:assocCaseAndCRHiddenID',
            'CMlogCase:associatedCaseandCorrespondence:plusMinus_advancedSearchFalse',
            'showhide_assocCaseAndCR');	--%>
            
            renderDiv('CMlogCase:caseEvents:caseEventsHiddenID',
            'CMlogCase:caseEvents:plusMinus_caseEventsFalse',
            'showhide_caseEvents');	
            
            renderDiv('CMlogCase:caseAlerts:alertsHiddenID',
            'CMlogCase:caseAlerts:plusMinus_alertsFalse',
            'showhide_alerts');	
            
            renderDiv('CMlogCase:caseAlerts:editAlertAudit:alert_audit_open',
            'CMlogCase:caseAlerts:editAlertAudit:alert_plusMinus_Auditmore',
            'audit_plusalertAuditing');	
            
            renderDiv('CMlogCase:caseSteps:caseStepsHiddenID',
            'CMlogCase:caseSteps:plusMinus_caseStepsFalse',
            'showhide_caseSteps');	
            
            renderDiv('CMlogCase:caseAttachments:caseAttachHiddenID',
            'CMlogCase:caseAttachments:plusMinus_caseAttachFalse',
            'showhide_caseAttachments');
            
            renderDiv('CMlogCase:caseLettersAndResponses:caseLettersHiddenID',
            'CMlogCase:caseLettersAndResponses:plusMinus_caseLettersFalse',
            'showhide_caseLandR');
            
            renderDiv('CMlogCase:caseAttachments:editAttachAudit:caseAttachAuditHiddenID',
            'CMlogCase:caseAttachments:editAttachAudit:plusMinus_caseAttachAuditFalse',
            'showhide_AttachAudit');
            
            renderDiv('CMlogCase:caseRegarding:caseRegardingProvider:provAltIden_hiddenID',
            'CMlogCase:caseRegarding:caseRegardingProvider:provIden_plusMinus',
            'prov_alt_iden_plus');
            
            renderDiv('CMlogCase:caseRegarding:caseRegardingMember:memAltIden_hiddenID',
            'CMlogCase:caseRegarding:caseRegardingMember:memIden_plusMinus',
            'mem_alt_iden_plus');
			
			renderDiv('CMlogCase:associatedCaseandCorrespondence:logCaseAssociatedCase:logCaseAssCaseSubView1:caseSearchSubview1:advancedSearchHiddenID1',
            'CMlogCase:associatedCaseandCorrespondence:logCaseAssociatedCase:logCaseAssCaseSubView1:caseSearchSubview1:plusMinus_advancedSearchFalse',
            'showhide_AdvencedOpt');
            
	</script>
</f:view>

