<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>


<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMMaintainEntity"
	var="msg" />


<SCRIPT type="text/javascript">
	/*Small save Big Save start*/
	frmId = 'view<portlet:namespace/>:frm';
	/*Small save Big Save ends*/
	function maintainEntityCancel(link) {
		if (isFormChanged(frmId) == true) {
			var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to navigate?");
			if (answer) {
				var entObject = findObjectByPartOfID(link);
				entObject.click();
			}
		} else {
			var entObject = findObjectByPartOfID(link);
			entObject.click();
		}
	}

	function maintainEntityEaddCancel(link) {
		if (isFormChanged(frmId) == true) {
			var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to navigate?");
			if (answer) {
				var entObject = findObjectByPartOfID(link);
				entObject.click();
			}
		} else {
			var entObject = findObjectByPartOfID(link);
			entObject.click();
		}
	}
	function maintainEntityContactCancel(link) {
		if (isFormChanged(frmId) == true) {
			var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to navigate?");
			if (answer) {
				var entObject = findObjectByPartOfID(link);
				entObject.click();
			}
		} else {
			var entObject = findObjectByPartOfID(link);
			entObject.click();
		}
	}
	function findObjectByPartOfID(partOfID) {
		for (i = 0; i < document.forms.length; i++) {
			for (j = 0; j < document.forms[i].elements.length; j++) {
				var idValue = document.forms[i].elements[j].id;
				if (idValue.indexOf(partOfID) != -1) {
					G_isFirstTime = false;
					G_countObject = document.forms[i].elements[j];
					return document.forms[i].elements[j];
				}
			}
		}
		return null;
	}
	function doMaintainEntityClickAction(link) {
		flagWarn = false;
		var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to navigate?");
		if (answer) {
			link.fireEvent('onclick');
		}

	}

	/*	This method is used to fire the event when the user clicks on the radio 
	 button specifiyng whether the Requesting and Rendering Provider are the
	 same or not.
	 */
	function entityTypeChangeAction(dropDownValue) {
		flagWarn = false;
		var hiddenButtonObject = findObjectByPartOfID('entityTypeChangedHiddenButton');

		hiddenButtonObject.click();
	}

	function countyCodeChangeAction(dropDownValue) {
		flagWarn = false;
		var hiddenButtonObject = findObjectByPartOfID('countyCodeChangeAction');

		hiddenButtonObject.click();
	}

	/*
	 For Audit 
	 */
	function toggleTest(obj, state) {
		var el = document.getElementById(obj);

		if (state == 1) {
			el.style.display = 'block';
		} else if (state == 0) {
			el.style.display = 'none';
		} else if (state == 2) {
			if (el.style.display == 'none') {
				el.style.display = 'block';
			} else if ((el.style.display == 'block')
					|| (el.style.display == '')) {
				el.style.display = 'none';
			}
		}
	}

	/*
	 * Used to display either '+' image or '-' image when a section 
	 * is closed or expanded respectively
	 */
	function plusMinusForRefreshTest(id, obj, hiddenTextId) {
		var hiddenTxt = find(hiddenTextId);
		var el = document.getElementById(id);
		flagWarn = false;

		if (el.style.display == 'none') {
			obj.className = 'plus';
			hiddenTxt.value = 'plus';
		} else if ((el.style.display == 'block') || (el.style.display == '')) {
			obj.className = 'minus';
			hiddenTxt.value = 'minus';
		} else if (el.style.display == '') {
			obj.className = 'minus';
			hiddenTxt.value = 'minus';
		}
	}

	function renderAudit(id1) {

		var hiddenValueContact = document
				.getElementById('view<portlet:namespace/>:frm:updateAdmnsContact:ContactAuditInc:' + id1);
		var hiddenValueConType = document
				.getElementById('view<portlet:namespace/>:frm:updateAdmnsContact:ContactTypeAuditInc:' + id1);
		var hiddenValuePhone = document
				.getElementById('view<portlet:namespace/>:frm:updateAdmnsPhone:PhoneAuditInc:' + id1);
		var hiddenValueEadd = document
				.getElementById('view<portlet:namespace/>:frm:updateAdmnsEadd:EAddressAuditInc:' + id1);
		var hiddenValueAddress = document
				.getElementById('view<portlet:namespace/>:frm:updatecommonAddr:AddressAuditInc:' + id1);
		var hiddenValueCI = document
				.getElementById('view<portlet:namespace/>:frm:viewEnt:dupEntityCodeInc:' + id1);

		if ((hiddenValueCI == null) || (hiddenValueCI == '')
				|| (hiddenValueCI.length == 0)) {
			hideMe('audit_plus');
		} else if ((hiddenValueCI.value == 'false')) {
			hideMe('audit_plus');
		}

		if ((hiddenValueContact == null) || (hiddenValueContact == '')
				|| (hiddenValueContact.length == 0)) {
			hideMe('auditContact');
		} else if ((hiddenValueContact.value == 'false')) {
			hideMe('auditContact');
		}

		if ((hiddenValueConType == null) || (hiddenValueConType == '')
				|| (hiddenValueConType.length == 0)) {
			hideMe('auditConType');
		} else if ((hiddenValueConType.value == 'false')) {
			hideMe('auditConType');
		}

		if ((hiddenValuePhone == null) || (hiddenValuePhone == '')
				|| (hiddenValuePhone.length == 0)) {
			hideMe('auditPhone');
		} else if ((hiddenValuePhone.value == 'false')) {
			hideMe('auditPhone');
		}

		if ((hiddenValueEadd == null) || (hiddenValueEadd == '')
				|| (hiddenValueEadd.length == 0)) {
			hideMe('auditEAdd');
		} else if ((hiddenValueEadd.value == 'false')) {
			hideMe('auditEAdd');
		}

		if ((hiddenValueAddress == null) || (hiddenValueAddress == '')
				|| (hiddenValueAddress.length == 0)) {
			hideMe('auditAdd');
		} else if ((hiddenValueAddress.value == 'false')) {
			hideMe('auditAdd');
		}
	}
</SCRIPT>

<script>
	var thisForm = 'view<portlet:namespace/>:frm';
	function focusThis(id) {
		document.getElementById(thisForm + ':focusId').value = id;
	}
	if (window.addEventListener) //DOM method for binding an event
		window.addEventListener("load", onLoadFunctions, false);
	else if (window.attachEvent) //IE exclusive method for binding an event
		window.attachEvent("onload", onLoadFunctions);
	else if (document.getElementById) //support older modern browsers
		window.onload = onLoadFunctions;

	function onLoadFunctions() {
		//focusOnLoad();
		onLoadFocus();
	}

	function focusOnLoad() {
		thisForm = 'view<portlet:namespace/>:frm';
		if(document.getElementById(thisForm + ':focusId')!=null){
			var focusPage = '#' + document.getElementById(thisForm + ':focusId').value;
			if (focusPage != '' && focusPage != '#') {
				document.location.href = focusPage;
			}
		}
	}

	function onLoadFocus(){
	
		//find the user browser
		var brow=(navigator.appName);

		if(brow == 'Microsoft Internet Explorer'){
			//alert("its internet explorer");
		}else if(brow == 'Netscape'){
			//alert("its Mozilla");
			jscalendarInit();
		}
		
		frmId = 'view<portlet:namespace/>:frm:';
		if(document.getElementById(frmId+'CURSORFOCUSID')!=null 
			|| document.getElementById(frmId+'CURSORFOCUSID').value!=''){
			try{
				if(document.getElementById(frmId+'CURSORFOCUSID').value!=''){//alert(document.getElementById(frmId+'CURSORFOCUSID').value);
					var cursorFocus=findObjectByPartOfID(document.getElementById(frmId+'CURSORFOCUSID').value);
					//alert(cursorFocus);
					if(cursorFocus!=null ){
						cursorFocus.focus();
					}else{
						defaultFocus();
				    }
			  	}else{
					findObjectByPartOfID('entTydropdownMem').focus();
			  	}
			}catch(e){
				defaultFocus();
			}
		}
	}


	function defaultFocus(){//alert("in default");
		var fieldObj=findObjectByPartOfID('entTydropdownMem');
		if(fieldObj!=null)
			fieldObj.focus();
		fieldObj=findObjectByPartOfID('prf');
		if(fieldObj!=null)
			fieldObj.focus();
		fieldObj=findObjectByPartOfID('EntIDType');
		if(fieldObj!=null)
			fieldObj.focus();
		fieldObj=findObjectByPartOfID('distCD');
		if(fieldObj!=null)
			fieldObj.focus();
		fieldObj=findObjectByPartOfID('countyCD');
		if(fieldObj!=null)
			fieldObj.focus();
		fieldObj=findObjectByPartOfID('orgNCD');
		if(fieldObj!=null)
			fieldObj.focus();
	}
</script>

<f:view>

	<t:saveState id="CMGTTOMSS414" value="#{CMEntityMaintainDataBean}"></t:saveState>
	<t:saveState id="CMGTTOMSS415" value="#{CMEntityMaintainDataBean.cmEnityDetailVO}"></t:saveState>
	<t:saveState id="CMGTTOMSS416" value="#{CMEntityMaintainDataBean.renderDefnComm}"></t:saveState>
	<t:saveState id="CMGTTOMSS417" value="#{CMEntityMaintainDataBean.renderUnenrProv}"></t:saveState>
	<t:saveState id="CMGTTOMSS418" value="#{CMEntityMaintainDataBean.renderDistOffice}"></t:saveState>
	<t:saveState id="CMGTTOMSS419" value="#{CMEntityMaintainDataBean.entityTypeListME}"></t:saveState>
	<t:saveState id="CMGTTOMSS420" value="#{CMEntityMaintainDataBean.provTypeListME}"></t:saveState>
	<t:saveState id="CMGTTOMSS421" value="#{CMEntityMaintainDataBean.lobListME}"></t:saveState>
	<t:saveState id="CMGTTOMSS422" value="#{CMEntityMaintainDataBean.prefixListME}"></t:saveState>
	<t:saveState id="CMGTTOMSS423" value="#{CMEntityMaintainDataBean.orgTypeListME}"></t:saveState>
	<t:saveState id="CMGTTOMSS424" value="#{CMEntityMaintainDataBean.duplicateEntityList}"></t:saveState>
	<t:saveState id="CMGTTOMSS425" value="#{CMEntityMaintainDataBean.entityIDTypeME}"></t:saveState>
	<t:saveState id="CMGTTOMSS426" value="#{CMEntityMaintainDataBean.distOffCodeListME}"></t:saveState>
	<t:saveState id="CMGTTOMSS427" value="#{CMEntityMaintainDataBean.urlMappingValue}"></t:saveState>

	<t:saveState id="CMGTTOMSS428"		value="#{CMEntityMaintainDataBean.renderEntityTypeDropDown}"></t:saveState>
	<t:saveState id="CMGTTOMSS429" value="#{CMEntityMaintainDataBean.renderEntityTypeText}"></t:saveState>
	<t:saveState id="CMGTTOMSS430" value="#{CMEntityMaintainDataBean.renderEntityTypeText}"></t:saveState>
	<t:saveState id="CMGTTOMSS431" value="#{CMEntityMaintainDataBean.renderUpdateEntitySave}"></t:saveState>
	<t:saveState id="CMGTTOMSS432" value="#{CMEntityMaintainDataBean.renderAddEntitySave}"></t:saveState>
	<t:saveState id="CMGTTOMSS433" value="#{CMEntityMaintainDataBean.renderName}"></t:saveState>
	<t:saveState id="CMGTTOMSS434" value="#{CMEntityMaintainDataBean.renderOrgName}"></t:saveState>
	<t:saveState id="CMGTTOMSS435" value="#{CMEntityMaintainDataBean.renderViewEntity}"></t:saveState>
	<t:saveState id="CMGTTOMSS436" value="#{CMEntityMaintainDataBean.renderMaintainEntity}"></t:saveState>
	<t:saveState id="CMGTTOMSS437" value="#{CMEntityMaintainDataBean.sourcePorletName}"></t:saveState>
	<t:saveState id="CMGTTOMSS438"		value="#{CMEntityMaintainDataBean.renderCancelCommandLink}"></t:saveState>
	<t:saveState id="CMGTTOMSS439" value="#{CMEntityMaintainDataBean.renderCancelOutputLink}"></t:saveState>
	<t:saveState id="CMGTTOMSS440" value="#{CMEntityMaintainDataBean.contactHidden}"></t:saveState>
	<t:saveState id="CMGTTOMSS441" value="#{CMEntityMaintainDataBean.addressHidden}"></t:saveState>
	<t:saveState id="CMGTTOMSS442" value="#{CMEntityMaintainDataBean.phRecordHidden}"></t:saveState>
	<t:saveState id="CMGTTOMSS443" value="#{CMEntityMaintainDataBean.eaddressHidden}"></t:saveState>

	<%-- Small save Big Save start --%>
	<f:subview id="provService">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>
	<body>
	<%-- Small save Big Save ends --%>
	<hx:scriptCollector>
		<h:form id="frm">
		
			
		
			<h:inputHidden id="controlRequiredForMaintainEntity"				value="#{CMEntityMaintainControllerBean.controlRequired}" />
				
				
			<%-- For Cursor Focus --%>
			<%-- for fixing defect:ESPRD00576305   
			<h:inputHidden id="focusId"				value="#{commonEntityDataBean.currentPageId}" />
		 Small save Big Save start --%>

			<h:inputHidden id="fileSavedFlag"				value="#{CMEntityMaintainDataBean.fileSavedFlag}"></h:inputHidden>
			<h:inputHidden id="smallSaveFlag"				value="#{commonEntityDataBean.smallSaveFlag}"></h:inputHidden>
			<%-- Small save Big Save ends --%>
			

<h:panelGroup id="PRGCMGTPGP111"				rendered="#{CMEntityMaintainControllerBean.receiveEntity}"></h:panelGroup>


			<h:commandButton id="entityTypeChangedHiddenButton" styleClass="hide"				value="Hidden Button To Click"				action="#{CMEntityMaintainControllerBean.showRespectiveEntity}" />

			<h:commandButton id="countyCodeChangeAction" styleClass="hide"				value="Hidden Button To Click"				action="#{CMEntityMaintainControllerBean.getCountryName}" />
			<%-- onload cursor focus --%>
			<h:inputHidden id="CURSORFOCUSID"				value="#{CMEntityMaintainDataBean.cursorFocusId}"></h:inputHidden>
			<%-- onload cursor focus --%>

			<m:inputHidden value="#{CMEntityMaintainDataBean.loadRefernceData}"></m:inputHidden>

			<m:section id="PROVIDERMMS20120731164811593"
				rendered="#{CMEntityMaintainDataBean.renderMaintainEntity}">
				<m:body>
					<m:div styleClass="floatContainer">
						<m:div styleClass="fullwidth">
							<m:div styleClass="moreInfoBar">
								<m:div styleClass="infoTitle" align="left">
									<m:span styleClass="required">
										<h:outputText id="PRGCMGTOT1416"											value="#{ent['label-sw-reqFld']}" styleClass="colorMaroon" />
									</m:span>

								</m:div>

								<m:div>

									<m:div styleClass="infoActions">
										
										<%--<h:commandLink id="CMGTCL9"											rendered="#{!CMEntityMaintainDataBean.renderCancelOutputLink}"											action="#{CMEntityMaintainControllerBean.cancel}">
											<h:outputText id="CMGTOT68" value="#{ent['label-sw-cancel']}"></h:outputText>
											<f:param name="go.to.CR.Entity.Search"
												value="gotoCREntitySearch"></f:param>
										</h:commandLink>--%>
										
										<h:commandButton id = "CMGTCL9" 
											             onclick="flagWarn=true;"
										                 rendered="#{!CMEntityMaintainDataBean.renderCancelOutputLink}" 
										                 action="#{CMEntityMaintainControllerBean.cancel}"
										                 value="#{ent['label-sw-cancel']}"
										                 style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:50px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px">
										                 
										                 <f:attribute name="go.to.CR.Entity.Search" 	value="gotoCREntitySearch"></f:attribute>
										                 
										</h:commandButton>

									<%-- 	<h:outputLink id="CMGTOLK1"											rendered="#{CMEntityMaintainDataBean.renderCORRCancelOutputLink}"											value="/wps/myportal/AddCorrespondence">
											<h:outputText id="CMGTOT69" value="#{ent['label-sw-cancel']}"></h:outputText>
										</h:outputLink>

										<h:outputLink id="CMGTOLK2"											rendered="#{CMEntityMaintainDataBean.renderHIPPCancelOutputLink}"											value="/wps/myportal/TPLHIPPDetail">
											<h:outputText id="CMGTOT70" value="#{ent['label-sw-cancel']}"></h:outputText>
										</h:outputLink>

										<h:outputLink id="CMGTOLK3"											rendered="#{CMEntityMaintainDataBean.renderCancelOutputLinkCaseEnitySrch}"											value="/wps/myportal/CaseEntitySearch">
											<h:outputText id="CMGTOT71" value="#{ent['label-sw-cancel']}"></h:outputText>
										</h:outputLink>
										<h:outputLink id="CMGTOLK4"											rendered="#{CMEntityMaintainDataBean.renderCancelOutputLinkInqCaseEnitySrch}"											value="/wps/myportal/FromLogCase">
											<h:outputText id="CMGTOT72" value="#{ent['label-sw-cancel']}"></h:outputText>
										</h:outputLink>  --%>
										
										<m:div id="cancelId11" rendered="#{CMEntityMaintainDataBean.renderCORRCancelOutputLink}">
											<f:verbatim><a href="/wps/myportal/AddCorrespondence" id="CMGTOLK1" ></f:verbatim>
													<h:outputText id="CMGTOT69" value="#{ent['label-sw-cancel']}"></h:outputText>
											<f:verbatim></a></f:verbatim>
										</m:div>
										
										<m:div id="cancelId12" rendered="#{CMEntityMaintainDataBean.renderHIPPCancelOutputLink}">
											<f:verbatim><a href="/wps/myportal/TPLHIPPDetail"id="CMGTOLK2" ></f:verbatim>
													<h:outputText id="CMGTOT70" value="#{ent['label-sw-cancel']}"></h:outputText>
											<f:verbatim></a></f:verbatim>
										</m:div>
										
										<m:div id="cancelId13" rendered="#{CMEntityMaintainDataBean.renderCancelOutputLinkCaseEnitySrch}">
											<f:verbatim><a href="/wps/myportal/CaseEntitySearch" id="CMGTOLK3"></f:verbatim>
													<h:outputText id="CMGTOT71" value="#{ent['label-sw-cancel']}"></h:outputText>
											<f:verbatim></a></f:verbatim>
										</m:div>
										
										<m:div id="cancelId14" rendered="#{CMEntityMaintainDataBean.renderCancelOutputLinkInqCaseEnitySrch}">
											<f:verbatim><a href="/wps/myportal/FromLogCase" id="CMGTOLK4" ></f:verbatim> 
													<h:outputText id="CMGTOT72" value="#{ent['label-sw-cancel']}"></h:outputText>
											<f:verbatim></a></f:verbatim>
										</m:div>

									</m:div>

									<m:div styleClass="infoActions">
										<h:outputText id="CMGTOT73" escape="false"											value="#{ref['label.ref.linkSeperator']}" />
									</m:div>

									<%--<h:commandLink id="CMGTCL10" action="" onmousedown="javascript:flagWarn=false;">
									<h:outputText id="CMGTOT74" value="#{ref['label.ref.auditLog']}"></h:outputText>
								</h:commandLink>--%>
									<m:div styleClass="infoActions">
										<h:commandButton id="CMGTCB8"											rendered="#{CMEntityMaintainDataBean.renderUpdateEntitySave}"											action="#{CMEntityMaintainControllerBean.doAuditKeyListOperation}"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:55px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"											onmousedown="javascript:flagWarn=false;"											value="#{ref['label.ref.auditLog']}" />
									</m:div>
									<%--<h:outputText id="CMGTOT75" escape="false" value="|" rendered="#{CMEntityMaintainDataBean.renderUpdateEntitySave}"/>--%>
									<m:div styleClass="infoActions">
										<h:outputText id="CMGTOT76" escape="false"											value="#{ref['label.ref.linkSeperator']}"											rendered="#{CMEntityMaintainDataBean.renderUpdateEntitySave}" />
									</m:div>
									<m:div styleClass="infoActions">
										<t:commandLink id="CMGTCL11"											rendered="#{CMEntityMaintainDataBean.renderAddEntitySave}"											action="#{CMEntityMaintainControllerBean.resetMaintainEntity}"											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="CMGTOT77" value="#{ent['label-sw-reset']}"></h:outputText>
										</t:commandLink>
									</m:div>
									<%--<h:commandLink id="CMGTCL12"									rendered="#{CMEntityMaintainDataBean.renderUpdateEntitySave}"									action="#{CMEntityMaintainControllerBean.resetUpdateMaintainEntity}"									onmousedown="javascript:flagWarn=false;">
									<h:outputText id="CMGTOT78" value="#{ent['label-sw-reset']}"></h:outputText>
								</h:commandLink> --%>

									<m:div styleClass="infoActions">
										<h:commandButton id="CMGTCB9"											rendered="#{CMEntityMaintainDataBean.renderUpdateEntitySave}"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"											onmousedown="javascript:flagWarn=false;"											value="#{ent['label-sw-reset']}"											disabled="#{!CMEntityMaintainControllerBean.controlRequired}"											action="#{CMEntityMaintainControllerBean.resetUpdateMaintainEntity}" />
									</m:div>

									<%--<h:outputText id="CMGTOT79" escape="false" value="|"/> --%>

									<m:div styleClass="infoActions">
										<h:outputText id="CMGTOT80" escape="false"											value="#{ref['label.ref.linkSeperator']}" />
									</m:div>

									<m:div styleClass="infoActions">
										<t:commandLink id="CMGTCL13" styleClass="strong"											rendered="#{CMEntityMaintainDataBean.renderAddEntitySave 										&& CMEntityMaintainDataBean.renderCorrespondenceSave}"											action="#{CMEntityMaintainControllerBean.getDuplicateEntities}"											onmousedown="javascript:flagWarn=false;focusThis(this.id);">
											<h:outputText id="CMGTOT81" value="#{ent['label-sw-save']}"></h:outputText>
											<f:param name="send.Correspondence.Entity"
												value="sendCorrespondenceEntity"></f:param>
										</t:commandLink>
										<t:commandLink id="CMGTCL14" styleClass="strong"											rendered="#{CMEntityMaintainDataBean.renderAddEntitySave 											&& CMEntityMaintainDataBean.renderCorresInquiryEntitySave}"											action="#{CMEntityMaintainControllerBean.getDuplicateEntities}"											onmousedown="javascript:flagWarn=false;focusThis(this.id);">
											<h:outputText id="CMGTOT82" value="#{ent['label-sw-save']}"></h:outputText>
											<f:param name="send.InquiryaboutEntity.Data"
												value="sendinquiryaboutEntityData"></f:param>
										</t:commandLink>

										<t:commandLink id="CMGTCL15" styleClass="strong"											rendered="#{CMEntityMaintainDataBean.renderAddEntitySave 											&& CMEntityMaintainDataBean.renderCaseSave}"											action="#{CMEntityMaintainControllerBean.getDuplicateEntities}"											onmousedown="javascript:flagWarn=false;focusThis(this.id);">
											<h:outputText id="CMGTOT83" value="#{ent['label-sw-save']}"></h:outputText>
											<f:param name="send.Search.Id" value="sendSearchId"></f:param>
										</t:commandLink>

										<t:commandLink id="CMGTCL16" styleClass="strong"											rendered="#{CMEntityMaintainDataBean.renderAddEntitySave 											&& CMEntityMaintainDataBean.renderCaseInquiryEntitySave}"											action="#{CMEntityMaintainControllerBean.getDuplicateEntities}"											onmousedown="javascript:flagWarn=false;focusThis(this.id);">
											<h:outputText id="CMGTOT84" value="#{ent['label-sw-save']}"></h:outputText>
											<f:param name="send.InquirySearch.Id"
												value="sendInquirySearchId"></f:param>
										</t:commandLink>

										<%--h:commandLink styleClass="Strong"
									rendered="#{CMEntityMaintainDataBean.renderAddEntitySave 
										&& !CMEntityMaintainDataBean.renderCancelOutputLink}"
									action="#{CMEntityMaintainControllerBean.getDuplicateEntities}"
									onmousedown="javascript:flagWarn=false;">
									<h:outputText id="CMGTOT85" value="#{ent['label-sw-save']}"></h:outputText>
									<f:param name="send.Correspondence.Entity"
										value="sendCorrespondenceEntity"></f:param>
								</h:commandLink>
								<h:commandLink id="CMGTCL17" styleClass="Strong"									rendered="#{CMEntityMaintainDataBean.renderAddEntitySave 											&& CMEntityMaintainDataBean.renderCancelOutputLink}"									action="#{CMEntityMaintainControllerBean.getDuplicateEntities}"									onmousedown="javascript:flagWarn=false;">
									<h:outputText id="CMGTOT86" value="#{ent['label-sw-save']}"></h:outputText>
									<f:param name="send.InquiryaboutEntity.Data"
										value="sendinquiryaboutEntityData"></f:param>
								</h:commandLink>

								<%--<h:commandLink id="CMGTCL18" styleClass="Strong"									rendered="#{CMEntityMaintainDataBean.renderAddEntitySave 											&& CMEntityMaintainDataBean.renderCancelOutputLink}"									action="#{CMEntityMaintainControllerBean.getDuplicateEntities}"									onmousedown="javascript:flagWarn=false;">
									<h:outputText id="CMGTOT87" value="#{ent['label-sw-save']}"></h:outputText>
									<f:param name="send.Search.Id" value="sendSearchId"></f:param>
								</h:commandLink>

								<h:commandLink id="CMGTCL19" styleClass="Strong"									rendered="#{CMEntityMaintainDataBean.renderAddEntitySave 											&& CMEntityMaintainDataBean.renderCancelOutputLink}"									action="#{CMEntityMaintainControllerBean.getDuplicateEntities}"									onmousedown="javascript:flagWarn=false;">
									<h:outputText id="CMGTOT88" value="#{ent['label-sw-save']}"></h:outputText>
									<f:param name="send.InquirySearch.Id" value="sendInquirySearchId"></f:param>
								</h:commandLink--%>
										<h:commandButton id="CMGTCB10"											rendered="#{CMEntityMaintainDataBean.renderUpdateEntitySave}"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:33px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"											onmousedown="javascript:flagWarn=false;focusThis(this.id);"											value="#{ent['label-sw-save']}"											disabled="#{!CMEntityMaintainControllerBean.controlRequired}"											action="#{CMEntityMaintainControllerBean.updateCMEntity}"											styleClass="strong" />
									</m:div>
									<%--<h:outputText id="CMGTOT89" escape="false" value="|"/> --%>



								</m:div>
							</m:div>


							<m:br />

							<h:messages id="PRGCMGTMS18" showDetail="true" layout="table"								showSummary="false" styleClass="errorMessage" />

							<%-- Snippet to show The Message  --%>
							<m:div styleClass="msgBox"
								rendered="#{CMEntityMaintainDataBean.showSuccessMessage}">
								<h:outputText id="PRGCMGTOT1436"									value="#{msg['label.entity.Success']}" />
							</m:div>

							<%-- Snippet to show The  Message  --%>
							<m:div styleClass="msgBox"
								rendered="#{CMEntityMaintainDataBean.showValidatedMessage}">
								<h:outputText id="PRGCMGTOT1437"									value="#{msg['label.entity.validationsuccess']}" />
							</m:div>

							<m:br />
							<m:br />

							<m:div
								rendered="#{not empty CMEntityMaintainDataBean.cmEnityDetailVO.entityType}">
								<f:subview id="commonEntityauditID">
									<%--jsp:include
										page="/jsp/commonEntities/inc_ContactAuditLogCnI.jsp" /--%>
									<m:div rendered="#{CMEntityMaintainDataBean.auditLogFlag}">

										<audit:auditTableSet id="commonEntityaudit"
											value="#{CMEntityMaintainDataBean.cmEnityDetailVO.auditKeyList}"
											numOfRecPerPage="10"></audit:auditTableSet>
									</m:div>
								</f:subview>
							</m:div>

							<m:section id="PROVIDERMMS20120731164811594" styleClass="otherbg">
								<m:legend>

									<h:outputText id="PRGCMGTOT1438" styleClass="strong"										value="#{msg['label.entity.maintainEntity']}" />

								</m:legend>
								<m:table id="PROVIDERMMT20120731164811595" width="100%">
									<m:tr>
										<m:td>
										</m:td>
									</m:tr>
								</m:table>

								<%-- Include Different pages for differnt Enity Types --%>


								<m:div rendered="#{CMEntityMaintainDataBean.renderCounty}">
									<f:subview id="rendercounty">
										<jsp:include page="/jsp/maintainentity/inc_ME_County.jsp" />
									</f:subview>
								</m:div>
								<m:div rendered="#{CMEntityMaintainDataBean.renderDefnComm}">
									<f:subview id="renderdef">
										<jsp:include
											page="/jsp/maintainentity/inc_ME_Unenrolledmember_Other.jsp" />
									</f:subview>
								</m:div>

								<m:div rendered="#{CMEntityMaintainDataBean.renderUnenrProv}">
									<f:subview id="renderUnProv">
										<jsp:include
											page="/jsp/maintainentity/inc_ME_Unenrolledprovider.jsp" />
									</f:subview>
								</m:div>

								<m:div rendered="#{CMEntityMaintainDataBean.renderDistOffice}">
									<f:subview id="renderDO">
										<jsp:include
											page="/jsp/maintainentity/inc_ME_Districtoffice.jsp" />
									</f:subview>
								</m:div>


								<m:div
									rendered="#{CMEntityMaintainDataBean.renderBillingOrganizatione}">
									<f:subview id="renderBO">
										<jsp:include
											page="/jsp/maintainentity/inc_ME_BillingOrganizatione.jsp" />
									</f:subview>
								</m:div>
								
								<%--Below JSP is included for CR : ESPRD00808886 --%>
								
								<m:div
									rendered="#{CMEntityMaintainDataBean.renderDynamicContentInformation}">
									<f:subview id="renderSC">
										<jsp:include
											page="/jsp/maintainentity/inc_ME_DynamicContentInformation.jsp" />
									</f:subview>
								</m:div>


								<m:br />
								<m:br />
								<m:br />
								<%-- for fixing defect:ESPRD00576305   --%>
								<m:anchor name="contactfoucsid"></m:anchor>
								<m:div>
									<f:subview id="updateAdmnsContact">
										<jsp:include page="/jsp/commonEntities/commonContact.jsp" />
									</f:subview>
								</m:div>

								<m:br />
								<m:br />
								<m:div>
									<f:subview id="updatecommonAddr">
										<jsp:include page="/jsp/commonEntities/commonAddress.jsp" />
									</f:subview>
								</m:div>

								<m:br />
								<m:br />
								<%-- To Fix Defect ESPRD00576234 --%>
								<m:anchor name="phoneRecordfocusid"></m:anchor>
								<m:div>
									<f:subview id="updateAdmnsPhone">
										<jsp:include page="/jsp/commonEntities/phoneRecord.jsp" />
									</f:subview>
								</m:div>

								<m:br /><m:br />
								<%-- To Fix Defect ESPRD00576234 --%>
								<m:anchor name="commonEAddressfocusid"></m:anchor>
								<m:div>
									<f:subview id="updateAdmnsEadd">
										<jsp:include page="/jsp/commonEntities/commonEAddress.jsp" />
									</f:subview>
								</m:div>


							</m:section>
						</m:div>
					</m:div>


				</m:body>
			</m:section>
			<m:div rendered="#{CMEntityMaintainDataBean.renderViewEntity}">
				<f:subview id="viewEnt">
					<jsp:include page="/jsp/maintainentity/viewEntity.jsp" />
				</f:subview>
			</m:div>




			<script>
	portletNameSpace = 'view<portlet:namespace/>:';
	renderContact(
			'frm:updateAdmnsContact:maintainEntComContSubView:contactHidden',
			'frm:updateAdmnsContact:maintainEntComContSubView:plusMinusContact',
			'frm:updateAdmnsContact:maintainEntComContSubView:maintainE_common_contact');
	renderContact(
			'frm:updateAdmnsPhone:maintainEntPhRecSubView:phRecordHidden',
			'frm:updateAdmnsPhone:maintainEntPhRecSubView:plusMinusPhone',
			'frm:updateAdmnsPhone:maintainEntPhRecSubView:maintainE_common_Phone');
	renderContact(
			'frm:updateAdmnsEadd:maintainEntEAddressSubView:eaddressHidden',
			'frm:updateAdmnsEadd:maintainEntEAddressSubView:plusMinusEaddress',
			'frm:updateAdmnsEadd:maintainEntEAddressSubView:maintainE_common_Eaddress');
</script>






		</h:form>
	</hx:scriptCollector>
	</body>
</f:view>


	<%-- var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
         FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:frm';

   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForMaintainEntity')) !=  'undefined') && document.getElementById(thisForm+':controlRequiredForMaintainEntity') != null ?  document.getElementById(thisForm+':controlRequiredForMaintainEntity').value:true);

</script>
