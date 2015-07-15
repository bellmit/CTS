<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>

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
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMSearchEntity"
	var="msg" />

<script type="text/javascript">
		
		var color;
  	function getRow(thisObj, thisEvent) { 
                 
        if (!thisEvent || !thisObj) return; 
        var p =  ((thisEvent.target) ? thisEvent.target : ((thisEvent.srcElement) ? thisEvent.srcElement : null)); 
        var tr=null; 
        
        while (tr==null && p!=null) { 
                if (p.tagName && p.tagName.toUpperCase()=="TR") tr=p; 
                else p = p.parentNode; 
        } 
        if (tr && tr.parentNode.tagName.toUpperCase()=="TBODY" && tr.parentNode.parentNode.id==thisObj.id) {
              return tr; 
        }      
        return null; 
	}  
	
	function setRowClass (tr, name, name1, name2) { 
               
        if (!tr || !tr.cells || !tr.cells.length) return; 
        //tr.className=name
        for (var i=0; i<tr.cells.length; i++) { 
                //alert(tr.cells[i].className);
                if(i==0) {
                 tr.cells[i].className = name2;
                }else if(i==tr.cells.length-1) {
                 tr.cells[i].className = name1;
                }else {
                  tr.cells[i].className = name; 
                }
        } 
	} 

	function setRowClassOne (tr, name) { 
        if (!tr || !tr.cells || !tr.cells.length) return; 
        //tr.className=name
        for (var i=0; i<tr.cells.length; i++) {                
                  tr.cells[i].className = name;               
        } 
	} 

	function tableMouseOver(thisObj, thisEvent) { 

        var row = getRow(thisObj, thisEvent); 
        if (row) setRowClass(row,"rowonon_mouse","rowonon_mouse_right","rowonon_mouse_left"); 
	} 
	
	function tableMouseOut(thisObj, thisEvent) { 
	//alert("Inside tableMouseOut");
        var row = getRow(thisObj, thisEvent); 
        if (row) setRowClassOne(row,"rowone"); 
	} 
	
/*This method finds an object from the DOM by acception a part of it's ID as parameter.*/
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

/*	This method is used to fire the event when the user clicks on the radio 
	button specifiyng whether the Requesting and Rendering Provider are the
	same or not.
*/
function entityTypeChangeAction()
{
	
	var hiddenButtonObject = findObjectByPartOfID('entityTypeChangedHiddenButton');

	hiddenButtonObject.click();
}	
/*Added for CR Cursor focus starts*/
		var thisForm = 'view<portlet:namespace/>:srchCaseEntity';
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
			if(document.getElementById(thisForm+':focusId').value==''){
				document.getElementById('view<portlet:namespace/>:srchCaseEntity:entityTypSrch').focus();
			}

   			var focusPage = '#'+document.getElementById(thisForm+':focusId').value;
			if(focusPage != '' && focusPage != '#') {				
				document.location.href=focusPage;
			}
   		}
   		/*Added for CR Cursor focus ends*/ 

		function focusButton()
	{
	  var fbid1 ='view<portlet:namespace/>:srchCaseEntity:CMGTTOMCBN3'; 
	  if (fbid1 != null && document.getElementById(fbid1)!= null) 
	  {
		if (window.event.keyCode == 13)
		{	
			document.getElementById(fbid1).focus();
		}
	  }//else{
		 // alert("search case entity button");
	  //}
	}
</script>

<f:view>
	<hx:scriptCollector>
		<h:form id="srchCaseEntity">

			<%--<t:saveState id="CMGTTOMSS510" value="#{CMEntitySearchDataBean}"></t:saveState>

		<t:saveState id="CMGTTOMSS511" value="#{CMEntitySearchDataBean.entitySearchCriteriaVO}"></t:saveState>
		<t:saveState id="CMGTTOMSS512" value="#{CMEntitySearchDataBean.entityDropDownList}"></t:saveState>
		<t:saveState id="CMGTTOMSS513" value="#{CMEntitySearchDataBean.entityIDTypeList}"></t:saveState>
		<t:saveState id="CMGTTOMSS514" value="#{CMEntitySearchDataBean.provTypeList}"></t:saveState>
		<t:saveState id="CMGTTOMSS515" value="#{CMEntitySearchDataBean.lobList}"></t:saveState>
		<t:saveState id="CMGTTOMSS516" value="#{CMEntitySearchDataBean.searchResultsList}"></t:saveState>
		<t:saveState id="CMGTTOMSS517" value="#{CMEntitySearchDataBean.renderProvider}"></t:saveState>
		<t:saveState id="CMGTTOMSS518" value="#{CMEntitySearchDataBean.renderNameSection}"></t:saveState>
		<t:saveState id="CMGTTOMSS519" value="#{CMEntitySearchDataBean.renderOrganizationName}"></t:saveState>
		<t:saveState id="CMGTTOMSS520" value="#{CMEntitySearchDataBean.renderCarrierNam}"></t:saveState>
		<t:saveState id="CMGTTOMSS521" value="#{CMEntitySearchDataBean.renderSearchResult}"></t:saveState>
		<t:saveState id="CMGTTOMSS522" value="#{CMEntitySearchDataBean.itemsPerPage}"></t:saveState>
		<t:saveState id="CMGTTOMSS523" value="#{CMEntitySearchDataBean.currentPage}"></t:saveState>
		<t:saveState id="CMGTTOMSS524" value="#{CMEntitySearchDataBean.numberOfPages}"></t:saveState>
		<t:saveState id="CMGTTOMSS525" value="#{CMEntitySearchDataBean.startRecord}"></t:saveState>
		<t:saveState id="CMGTTOMSS526" value="#{CMEntitySearchDataBean.showPrevious}"></t:saveState>
		<t:saveState id="CMGTTOMSS527" value="#{CMEntitySearchDataBean.showNext}"></t:saveState> 

		--%>
		<%--	<m:inputHidden name="send.case.entity.details"
				   value="SendCaseEntityDetails"></m:inputHidden>--%>
			<h:inputHidden id="focusId" value=" "></h:inputHidden>
			<m:inputHidden value="#{CMEntitySearchDataBean.loadRefernceData}"></m:inputHidden>
			<m:inputHidden value="#{CMEntitySearchControllerBean.menuCode}"></m:inputHidden>
			<m:inputHidden
				value="#{CMEntitySearchControllerBean.loadProviderData}"></m:inputHidden>
			<m:inputHidden value="#{CMEntitySearchControllerBean.link2Show}"></m:inputHidden>
			<m:inputHidden name="send.case.entity.details" value="SendCaseEntityDetails"></m:inputHidden>
			<h:inputHidden id="cursorFocusId" value="#{CMEntitySearchDataBean.cursorFocusId}"></h:inputHidden>
			<h:commandButton id="entityTypeChangedHiddenButton" styleClass="hide"				value="Hidden Button To Click"				action="#{CMEntitySearchControllerBean.renderRespEntityBlock}" />
			<m:body onkeypress="focusButton();" onload="javascript:onLoadFocus();">

				<m:div styleClass="moreInfoBar">
					<m:div styleClass="infoTitle" align="left">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT1780" value="#{ent['label-sw-reqFld']}"								styleClass="colorMaroon" />
						</m:span>

					</m:div>

					<m:div styleClass="infoActions">
						<%--Modified for defect ESPRD00357943 starts --%>

						<t:commandLink id="gotoAE"							rendered="#{!CMEntitySearchDataBean.disableAddEntity}"							action="#{CMEntitySearchControllerBean.addEntityFromSearchCaseEntity}"							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="PRGCMGTOT1781" styleClass="strong"								value="#{msg['title.label.entity.addEntity']}"></h:outputText>
							<f:param name="send.Correspondence.Entity"
								value="sendCorrespondenceEntity"></f:param>
						</t:commandLink>
						<%--Modified for defect ESPRD00357943 ends --%>
						<h:outputText id="PRGCMGTOT1782" styleClass="strong"							value="#{msg['title.label.entity.addEntity']}"							rendered="#{CMEntitySearchDataBean.disableAddEntity}"></h:outputText>

					</m:div>

				</m:div>
				


				<%--<h:panelGroup id="entityType" rendered="#{CMEntitySearchControllerBean.ajaxrenderRespEntityBlock}">--%>
				<m:div styleClass="floatContainer">
					<h:messages id="PRGCMGTMS32" showDetail="true" layout="table" showSummary="false"					styleClass="errorMessage" />
					<m:div styleClass="fullwidth">
						<m:div styleClass="floatContainer">
							<m:div styleClass="leftCol33">
								<m:section id="PROVIDERMMS20120731164811672">
									<m:pod title="#{ref['label.ref.searchCriteria']}"
										styleClass="otherbg">
											<m:div>
												<m:table id="PROVIDERMMT20120731164811673" width="100%">
													<m:tr>
														<m:td colspan="2">
															<m:span styleClass="required">
																<h:outputText id="PRGCMGTOT1783" value="#{ref['label.ref.reqSymbol']}" />
															</m:span>
															<h:outputLabel id="PRGCMGTOLL710" for="entityTypSrch"																value="#{msg['label.entity.entityType']}">
															</h:outputLabel>
															<m:br />
															<%--uncommented the javascript function in onchange attribute. --%>
															<h:selectOneMenu id="entityTypSrch" value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityType}" onchange="javascript:flagWarn=false;focusThis(this.id);entityTypeChangeAction();">
																<f:selectItem itemLabel="Please Select One" itemValue="" />
																<f:selectItems
																	value="#{CMEntitySearchDataBean.entityDropDownList}" />
																<hx:behavior id="entityChangeBehavior" event="onchange"
																	behaviorAction="get" targetAction="entityType"></hx:behavior>
															</h:selectOneMenu>
															<m:br />
															<h:message id="PRGCMGTM232" for="entityTypSrch" showDetail="true"																styleClass="errorMessage" />
														</m:td>
														<%--ADDED FOR THE CR ESPRD00436016 --%>
														<m:td
															rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
															<m:span styleClass="required">
																<h:outputText id="PRGCMGTOT1784" value="#{ref['label.ref.reqSymbol']}" />
															</m:span>
															<h:outputLabel id="PRGCMGTOLL711" for="statusap"																value="#{msg['label.entity.status']}">
															</h:outputLabel>
															<m:br />
															<h:selectOneMenu id="statusap"																onchange="javascript:focusThis(this.id);"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerStatus}">
																<f:selectItem itemLabel="Please Select One" itemValue="" />
																<f:selectItems
																	value="#{CMEntitySearchDataBean.tradingParnterStatusList}" />

															</h:selectOneMenu>
															<m:br />
															<h:message id="PRGCMGTM233" for="statusap" showDetail="ue"																styleClass="errorMessage" />
														</m:td>
															<%--END FOR THE CR ESPRD00436016 --%>				
														<h:graphicImage id="PROVIDERGI20120731164811674" styleClass="blankImage" width="1"
															height="5" alt="" url="/images/x.gif" />

														<m:td rendered="#{CMEntitySearchDataBean.renderProvider}">
															<h:outputLabel id="PRGCMGTOLL712" for="statusprov"																value="#{msg['label.entity.status']}">
															</h:outputLabel>
															<h:selectOneRadio id="statusprov" enabledClass="black"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerStatus}">
																<f:selectItem itemLabel="#{msg['label.entity.active']}"
																	itemValue="A" />
																<f:selectItem
																	itemLabel="#{msg['label.entity.inactive']}"
																	itemValue="I" />
															</h:selectOneRadio>

														</m:td>
													</m:tr>
												</m:table>
											</m:div>

											<%-- Common to all --%>
											<m:div id="showhide_searchby_provunenrolled">
												<m:div>
													<m:table id="PROVIDERMMT20120731164811675">
														<m:tr>
															<m:td>
																<h:outputLabel id="PRGCMGTOLL713" for="enid"																	value="#{msg['label.entity.EntityIDType']}" />
																<m:br></m:br>
																<h:selectOneMenu id="enid"																	value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityIDType}"																	onmousedown="javascript:flagWarn=false;">
																	<f:selectItem itemLabel="Please Select One"
																		itemValue="" />
																	<f:selectItems
																		value="#{CMEntitySearchDataBean.entityIDTypeList}" />
																</h:selectOneMenu>
															</m:td>
															<m:td>
																<h:outputLabel id="PRGCMGTOLL714" for="EntIDSrch"																	value="#{msg['label.entity.EntityID']}" />

																<h:inputText id="EntIDSrch" size="10"																	value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityID}"></h:inputText>
																<m:br />
																<h:message id="PRGCMGTM234" for="EntIDSrch" showDetail="true"																	styleClass="errorMessage" />
															</m:td>
														</m:tr>
													</m:table>
												</m:div>
											</m:div>


											<m:div rendered="#{CMEntitySearchDataBean.renderProvider}">
												<m:table id="PROVIDERMMT20120731164811676" width="95">
													<m:tr>
														<m:td colspan="3">
															<h:outputLabel id="PRGCMGTOLL715" for="selectProviderType"																value="#{msg['label.entity.ProviderType']}" />
															<m:br></m:br>
															<h:selectOneMenu id="selectProviderType"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerType}"																onmousedown="javascript:flagWarn=false;">
																<f:selectItem itemLabel="Please Select One" itemValue="" />
																<f:selectItems
																	value="#{CMEntitySearchDataBean.provTypeList}" />
															</h:selectOneMenu>
														</m:td>
													</m:tr>
													<m:tr>

														<m:td>
															<h:outputLabel id="PRGCMGTOLL716" for="provSrtnam"																value="#{msg['label.entity.ProviderSortNam']}" />
															<m:br></m:br>
															<h:inputText id="provSrtnam" size="6"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerSortName}"																maxlength="35"></h:inputText>

															<h:message id="PRGCMGTM235" for="provSrtnam" showDetail="true"																styleClass="errorMessage" />
														</m:td>
														<m:td>
															<h:outputLabel id="PRGCMGTOLL717" for="provDBA"																value="#{msg['label.entity.ProviderDBA']}" />
															<m:br></m:br>
															<h:inputText id="provDBA" size="5"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerDBA}"																maxlength="35"></h:inputText>
														</m:td>
														<m:td>
															<h:outputLabel id="PRGCMGTOLL718" for="lobdropdowns"																value="#{msg['label.entity.Lob']}" />
															<m:br></m:br>
															<m:br></m:br>
															<h:selectOneMenu id="lobdropdowns"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lineOfBusiness}">

																<f:selectItems value="#{CMEntitySearchDataBean.lobList}" />
															</h:selectOneMenu>
														</m:td>

													</m:tr>
												</m:table>
											</m:div>
											<%-- start of TPL Policy Holder for CR ESPRD00486064--%>
											<m:div id="TPdiv1" rendered="#{CMEntitySearchDataBean.renderTPLPlcyHldr}">
												<m:table id="TPTable" width="95">
												<%--<m:tr id="TPtr1">
														<m:td id="TPtd1">
															<h:outputLabel id="TPlabel1" for="payeeId"																value="#{msg['label.entity.Payee']}" />
															<m:br id="TPbr1"></m:br>
															<h:inputText id="payeeId" size="6"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderPayeeID}"																maxlength="35"></h:inputText>

															<h:message id="TPmsg1" for="payeeId" showDetail="true"																styleClass="errorMessage" />
														</m:td>
													</m:tr>
													<m:tr id="TPtr2">
														<m:td id="TPtd2">
															<h:outputLabel id="TPlabel2" for="PolicyholderId"																value="#{msg['label.entity.policyholder.id']}" />
															<m:br id="TPbr11"></m:br>
															<h:inputText id="PolicyholderId" size="5"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderID}"																maxlength="35"></h:inputText>
														</m:td>

													</m:tr>--%>
													<m:tr id="TPtr3">
														<m:td id="TPtd3">
															<h:outputLabel id="TPlabel3" for="TPfnam"																value="#{msg['label.entity.FirstName']}" />

															<m:br id="TPbr2"></m:br>
															<h:inputText id="TPfnam" size="10"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderFirstName}"																maxlength="25"></h:inputText>
															<h:message id="TPmsg2" for="TPfnam" showDetail="true"																styleClass="errorMessage" />
														</m:td>
														<m:td id="TPtd4">
															<h:outputLabel id="TPlabel4" for="TPLNameSearch"																value="#{msg['label.entity.LastName']}" />

															<m:br id="TPbr3"></m:br>
															<h:inputText id="TPLNameSearch" size="10"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderLastName}"																maxlength="35"></h:inputText>
															<m:br id="TPbr4"></m:br>
															<h:message id="TPmsg3" for="TPLNameSearch" showDetail="true"																styleClass="errorMessage" />
														</m:td>
													</m:tr>
												</m:table>
											</m:div>
											<%--ADDED FOR THE CR ESPRD00436016 --%>
											<m:div id="aPatdiv1" rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
												<m:table id="aPatTable" width="95">
										
														<m:tr>
														<m:td id="TPtd2">
																<h:outputLabel id="PRGCMGTOLL719" for="Pclassi" 																	value="#{msg['label.entity.tradingpartner.classification']}" />
																<m:br></m:br>
																<h:selectOneMenu id="Pclassi"																	value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerClassification}"																	onmousedown="javascript:flagWarn=false;">
																	<f:selectItem itemLabel="Please Select One"
																		itemValue="" />
																	<f:selectItems
																		value="#{CMEntitySearchDataBean.tradingPartnerclassificationList}" />
																</h:selectOneMenu>
														<m:br></m:br>
													<h:message id="PRGCMGTM236" for="Pclassi" showDetail="ue"																styleClass="errorMessage" />
															</m:td>
																		</m:tr>
													<m:tr>
														<m:td id="Ptd1">
															<h:outputLabel id="Plabel1" for="PNameSearch"																value="#{msg['label.entity.tradingpartner.Name']}" />

															<m:br id="Pbr1"></m:br>
															<h:inputText id="PNameSearch" size="10"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerName}"																maxlength="35"></h:inputText>
															<m:br id="Pbr2"></m:br>
															<h:message id="Pmsg1" for="PNameSearch" showDetail="ue"																styleClass="errorMessage" />
														</m:td> 
													</m:tr>
												</m:table>
											</m:div>
											<%--END FOR THE CR ESPRD00436016 --%>
											<%-- End of TPL Policy Holder for CR ESPRD00486064--%>
											<%-- Render this div for  Member ,Provider ,UP ,UM --%>
											<m:div id="showhide_searchby_other"
												rendered="#{CMEntitySearchDataBean.renderNameSection}">
												<m:table id="PROVIDERMMT20120731164811677">
													<m:tr>
														<m:td>
															<h:outputLabel id="PRGCMGTOLL720" for="fnam"																value="#{msg['label.entity.FirstName']}" />

															<m:br></m:br>
															<h:inputText id="fnam" size="10"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.firstName}"																maxlength="25"></h:inputText>
															<h:message id="PRGCMGTM237" for="fnam" showDetail="true"																styleClass="errorMessage" />
														</m:td>
														<m:td>
															<h:outputLabel id="PRGCMGTOLL721" for="mi_na"																value="#{msg['label.entity.MI']}" />

															<m:br></m:br>
															<h:inputText id="mi_na" size="2" maxlength="25"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.middleInitial}"></h:inputText>
															<h:message id="PRGCMGTM238" for="mi_na" showDetail="true"																styleClass="errorMessage" />
														</m:td>
														<m:td>
															<h:outputLabel id="PRGCMGTOLL722" for="LNameSearch"																value="#{msg['label.entity.LastName']}" />

															<m:br></m:br>
															<h:inputText id="LNameSearch" size="10"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lastName}"																maxlength="35"></h:inputText>
															<m:br></m:br>
															<h:message id="PRGCMGTM239" for="LNameSearch" showDetail="true"																styleClass="errorMessage" />
														</m:td>
													</m:tr>
												</m:table>
											</m:div>

											<m:div id="showhide_orgnam"
												rendered="#{CMEntitySearchDataBean.renderOrganizationName}">
												<m:table id="PROVIDERMMT20120731164811678">
													<m:tr>
														<h:outputLabel id="PRGCMGTOLL723" for="orgNCD"															value="#{msg['label.entity.OrgName']}" />
														<h:inputText id="orgNCD" size="12" maxlength="50"															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.organisationName}"></h:inputText>
														<m:br />
														<h:message id="PRGCMGTM240" for="orgNCD" showDetail="true"															styleClass="errorMessage" />
													</m:tr>
												</m:table>
											</m:div>


											<m:div id="showhide_carrnam"
												rendered="#{CMEntitySearchDataBean.renderCarrierNam}">
												<m:table id="PROVIDERMMT20120731164811679">
													<m:tr>
														<h:outputLabel id="PRGCMGTOLL724" for="carriernam"															value="#{msg['label.entity.CarrierName']}" />
														<h:inputText id="carriernam" size="15" maxlength="40"															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.carrierName}"></h:inputText>
														<m:br />
														<h:message id="PRGCMGTM241" for="carriernam" showDetail="true"															styleClass="errorMessage" />
													</m:tr>
												</m:table>
											</m:div>

											<m:div styleClass="buttonRow">
											<%--	<h:commandButton styleClass="formBtn" id="getEntitiesForCaseId"													value="#{ref['label.ref.search']}" onmousedown="javascript:focusThis(this.id);"													action="#{CMEntitySearchControllerBean.getEntitiesForCase}" />--%>
												<%--	<h:commandLink id="getEntitiesForCaseId" style="cursor:default; color:white;height:20px;text-align:center;padding:2px 1px 2px 1px;margin:0px 0 5px 5px;border-right: 2px solid #999999;text-decoration:none;"													rendered="#{!CMEntitySearchDataBean.disableAddEntity}" styleClass="formBtn" 													action="#{CMEntitySearchControllerBean.getEntitiesForCase}"													onmousedown="javascript:flagWarn=false;">
													<h:outputText id="getEntitiesForCaseTxtId"														value="#{ref['label.ref.search']}"></h:outputText>
													<f:param name="send.case.entity.details"
													value="SendCaseEntityDetails"></f:param>
												</h:commandLink>
												<h:outputText id="PRGCMGTOT1785" escape="false"													value="#{ref['label.ref.singleSpace']}" />
												<h:outputText id="PRGCMGTOT1786" escape="false"													value="#{ref['label.ref.singleSpace']}" />
												
												<h:commandButton styleClass="formBtn" id="resetSearchId"													value="#{ent['label-sw-reset']}" onmousedown="javascript:focusThis('srchCaseEntity');"													action="#{CMEntitySearchControllerBean.resetSearch}" />--%>
											<t:commandButton id="CMGTTOMCBN3" styleClass="formBtn"											value="#{ref['label.ref.search']}"											rendered="#{!CMEntitySearchDataBean.disableAddEntity}"											action="#{CMEntitySearchControllerBean.getEntitiesForCase}" />
										<h:outputText id="PRGCMGTOT1785" escape="false"											value="#{ref['label.ref.singleSpace']}" />
										<h:outputText id="PRGCMGTOT1786" escape="false"											value="#{ref['label.ref.singleSpace']}" />
										<t:commandButton id="CMGTTOMCBN4" styleClass="formBtn"											value="#{ent['label-sw-reset']}"										action="#{CMEntitySearchControllerBean.resetSearch}" />
											</m:div>
									</m:pod>
								</m:section>
							</m:div>


							<%-- Search Results Data Table --%>
							<m:div styleClass="rightCol66"
								rendered="#{CMEntitySearchDataBean.renderSearchResult}">
								<m:div id="searchResults">
									<m:br />
									<%-- Modified for the HeapDump Fix --%>
									<t:dataTable cellspacing="0" styleClass="dataTable"										id="entSrch" width="100%" columnClasses="columnClass"										headerClass="headerClass" footerClass="footerClass"										first="#{CMEntitySearchDataBean.searchResultRowIndex}"										rowClasses="row_alt,row" rows="10"										onmouseover="return tableMouseOver(this, event);"										onmouseout="return tableMouseOut(this, event);"										value="#{CMEntitySearchDataBean.searchResultsList}"										var="srchResult" onmousedown="javascript:flagWarn=false;"										rowOnClick="firstChild.lastChild.click();">
										<t:column id="entidcol">
											<f:facet name="header">

												<h:panelGrid id="PRGCMGTPGD239" columns="2">
													<h:outputLabel id="PRGCMGTOLL725" for="dataColEntId"														value="#{msg['label.entity.EntityID']}">
													</h:outputLabel>
													<h:panelGroup id="dataColEntId">
														<t:div styleClass="alignLeft">
															<t:commandLink id="entCommandLink1"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink1'}">
																<m:div title="for ascending" styleClass="sort_asc" />
																<f:attribute name="columnName" value="entIdVal" />
																<f:attribute name="sortOrder" value="asc" />
															</t:commandLink>
														</t:div>
														<t:div styleClass="alignLeft">
															<t:commandLink id="entCommandLink2"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink2'}">
																<m:div title="for descending" styleClass="sort_desc" />
																<f:attribute name="columnName" value="entIdVal" />
																<f:attribute name="sortOrder" value="desc" />
															</t:commandLink>
														</t:div>
													</h:panelGroup>
												</h:panelGrid>
											</f:facet>
											<t:commandLink id="edit"												action="#{CMEntitySearchControllerBean.getCMEntityDetailsForCase}">
												<h:outputText id="a" value="#{srchResult.entityID}" />
												<f:param name="systemIDVal" value="#{srchResult.systemID}"></f:param>
												<f:param name="entiyIDVal" value="#{srchResult.entityID}"></f:param>
							<f:param name="commonEntitySK" value="#{srchResult.commonEntitySK}"></f:param><%--ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010 --%>
												<f:param name="send.case.entity.details"
													value="SendCaseEntityDetails"></f:param>
												<f:param name="menuCode"
													value="#{CMEntitySearchDataBean.menuActionCode}"></f:param>
											</t:commandLink>
										</t:column>

										<t:column id="nameCol">
											<f:facet name="header">

												<h:panelGrid id="PRGCMGTPGD240" columns="2">
													<h:outputLabel id="PRGCMGTOLL726" for="dataColentname"														value="#{msg['label.entity.Name']}">
													</h:outputLabel>
													<h:panelGroup id="dataColentname">
														<t:div styleClass="alignLeft">
															<t:commandLink id="namCommandLink1"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink1'}">
																<m:div title="for ascending" styleClass="sort_asc" />
																<f:attribute name="columnName" value="namValue" />
																<f:attribute name="sortOrder" value="asc" />
															</t:commandLink>
														</t:div>
														<t:div styleClass="alignLeft">
															<t:commandLink id="namCommandLink2"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink2'}">
																<m:div title="for descending" styleClass="sort_desc" />
																<f:attribute name="columnName" value="namValue" />
																<f:attribute name="sortOrder" value="desc" />
															</t:commandLink>
														</t:div>
													</h:panelGroup>
												</h:panelGrid>
											</f:facet>

											<h:outputText id="namOutput" value="#{srchResult.entityName}" />


										</t:column>


										<t:column id="lobCol">
											<f:facet name="header">

												<h:panelGrid id="PRGCMGTPGD241" columns="2">
													<h:outputLabel id="PRGCMGTOLL727" for="dataColLob"														value="#{msg['label.entity.Lob']}">
													</h:outputLabel>
													<h:panelGroup id="dataColLob">
														<t:div styleClass="alignLeft">
															<t:commandLink id="lobCommandLink1"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='lobCommandLink1'}">
																<m:div title="for ascending" styleClass="sort_asc" />
																<f:attribute name="columnName" value="lobValue" />
																<f:attribute name="sortOrder" value="asc" />
															</t:commandLink>
														</t:div>
														<t:div styleClass="alignLeft">
															<t:commandLink id="lobCommandLink2"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='lobCommandLink2'}">
																<m:div title="for descending" styleClass="sort_desc" />
																<f:attribute name="columnName" value="lobValue" />
																<f:attribute name="sortOrder" value="desc" />
															</t:commandLink>
														</t:div>
													</h:panelGroup>
												</h:panelGrid>
											</f:facet>

											<h:outputText id="lobOutput"												value="#{srchResult.lineOfBusiness}" />






										</t:column>


										<t:column id="addCol">
											<f:facet name="header">

												<h:panelGrid id="PRGCMGTPGD242" columns="2">
													<h:outputLabel id="PRGCMGTOLL728" for="dataColAdd"														value="#{msg['label.entity.Address']}">
													</h:outputLabel>
													<h:panelGroup id="dataColAdd">
														<t:div styleClass="alignLeft">
															<t:commandLink id="addCommandLink1"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='addCommandLink1'}">
																<m:div title="for ascending" styleClass="sort_asc" />
																<f:attribute name="columnName" value="addValue" />
																<f:attribute name="sortOrder" value="asc" />
															</t:commandLink>
														</t:div>
														<t:div styleClass="alignLeft">
															<t:commandLink id="addCommandLink2"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='addCommandLink2'}">
																<m:div title="for descending" styleClass="sort_desc" />
																<f:attribute name="columnName" value="addValue" />
																<f:attribute name="sortOrder" value="desc" />
															</t:commandLink>
														</t:div>
													</h:panelGroup>
												</h:panelGrid>
											</f:facet>

											<h:outputText id="addOutput" value="#{srchResult.address}" />




										</t:column>

										<t:column id="cityCol">
											<f:facet name="header">

												<h:panelGrid id="PRGCMGTPGD243" columns="2">
													<h:outputLabel id="PRGCMGTOLL729" for="dataColCity"														value="#{msg['label.entity.City']}">
													</h:outputLabel>
													<h:panelGroup id="dataColCity">
														<t:div styleClass="alignLeft">
															<t:commandLink id="cityCommandLink1"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='cityCommandLink1'}">
																<m:div title="for ascending" styleClass="sort_asc" />
																<f:attribute name="columnName" value="cityValue" />
																<f:attribute name="sortOrder" value="asc" />
															</t:commandLink>
														</t:div>
														<t:div styleClass="alignLeft">
															<t:commandLink id="cityCommandLink2"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='cityCommandLink2'}">
																<m:div title="for descending" styleClass="sort_desc" />
																<f:attribute name="columnName" value="cityValue" />
																<f:attribute name="sortOrder" value="desc" />
															</t:commandLink>
														</t:div>
													</h:panelGroup>
												</h:panelGrid>
											</f:facet>

											<h:outputText id="cityOutput" value="#{srchResult.city}" />
										</t:column>
									</t:dataTable>
									<%-- Commented for defect UC-PGM-CRM-072_ESPRD00425145_24FEB2010
								<t:dataScroller id="CMGTTOMDS65" for="entSrch" paginator="true"									paginatorActiveColumnStyle='font-weight:bold;'									paginatorMaxPages="3" immediate="false"									pageCountVar="pageCount" pageIndexVar="pageIndex"									firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"									rowsCountVar="rowsCount" styleClass="scroller">
									<f:facet name="previous">
										<h:commandLink id="PRGCMGTCL243" styleClass="underline"											action="#{CMEntitySearchControllerBean.previous}"											rendered="#{CMEntitySearchDataBean.showPrevious}">
											<h:outputText id="PRGCMGTOT1787" value="#{ref['label.ref.lt']}">
											</h:outputText>
										</h:commandLink>
									</f:facet>

									<f:facet name="next">
										<h:commandLink id="PRGCMGTCL244" styleClass="underline"											action="#{CMEntitySearchControllerBean.next}"											rendered="#{CMEntitySearchDataBean.showNext}">
											<h:outputText id="PRGCMGTOT1788" value="#{ref['label.ref.gt']}">
											</h:outputText>
										</h:commandLink>
									</f:facet>


									<h:outputText id="PRGCMGTOT1789" rendered="#{rowsCount > 0}"
										value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
										styleClass="scrollerBold" />
								</t:dataScroller>--%>
								<t:dataScroller id="CMGTTOMDS66" for="entSrch" paginator="true"									paginatorActiveColumnStyle='font-weight:bold;'									paginatorMaxPages="3" immediate="false"									pageCountVar="pageCount" pageIndexVar="pageIndex"									firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"									rowsCountVar="rowsCount" styleClass="scroller">
									<f:facet name="previous">
										<h:outputText id="PRGCMGTOT1790" styleClass="underline"												value=" << " rendered="#{pageIndex > 1}"></h:outputText>
									</f:facet>
									<f:facet name="next">
										<h:outputText id="PRGCMGTOT1791" styleClass="underline"											value=" >> "
											rendered="#{pageIndex < pageCount}"></h:outputText>
									</f:facet>
										<h:outputText id="PRGCMGTOT1792"  rendered="#{rowsCount > 0}"
											value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
											styleClass="dataScrollerText" ></h:outputText>
								</t:dataScroller><%--EOF UC-PGM-CRM-072_ESPRD00425145_24FEB2010 --%>

								<m:br />
								<m:br />
								</m:div>
							</m:div>
							<%-- End of 2nd section --%>
						</m:div>
					</m:div>
				</m:div>
				<%--</h:panelGroup>
				<hx:ajaxRefreshRequest id="ajaxRefreshRequest1"
					target="entityType" params="entityTypSrch"></hx:ajaxRefreshRequest>--%>
			</m:body>
		</h:form>
	</hx:scriptCollector>
</f:view>
<script type="text/javascript">
 function onLoadFocus(){
		frmId = 'view<portlet:namespace/>:srchCaseEntity';
		//var pageId ='view<portlet:namespace/>:CaseSearch:caseSrchAsscSubview:';
		if(document.getElementById(frmId+':cursorFocusId')!=null || document.getElementById(frmId+':cursorFocusId').value!=''){
		    var cursorFocus=frmId+':'+document.getElementById(frmId+':cursorFocusId').value;
			if(cursorFocus!=null && document.getElementById(cursorFocus)!= null){
				document.getElementById(cursorFocus).focus();
			}
		}
	}
</script>
